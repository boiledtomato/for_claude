# Microsoft Learn — Microsoft Entra / External ID・Verified ID (part 1)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 64

---

<!-- MSL-PAGE {"url":"entra/external-id"} -->
## Microsoft Entra External ID のドキュメント - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id
- Service: entra-external-id / external
- Article date: 2025-05-16
- Summary: 従業員の B2B コラボレーション、コンシューマー アプリの ID とアクセス管理など、すべての外部 ID シナリオに Microsoft Entra External ID を使用する方法について説明します。

Microsoft Entra 外部 IDを使用して、ゲスト ユーザー アクセスやアプリの顧客 ID およびアクセス管理 (CIAM) など、外部 ID のシナリオを管理する方法について説明します。

新機能
[最新のドキュメントを検索する](https://learn.microsoft.com/ja-jp/entra/external-id/whats-new-docs)

概要
[Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview)

概念
[課金モデル](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing)

video
[エキスパートから学ぶ](https://www.youtube.com/playlist?list=PL3ZTgFEc7Lythpts59O9KOVuEDLWJLLmA)

### 概要

シナリオを選択し、外部アクセスをセキュリティで保護するための主要な機能、セットアップ ガイダンス、ベスト プラクティスについて説明します。

#### 従業員とのゲスト コラボレーションを管理する (B2B)

- [外部ゲストとの B2B コラボレーションとは](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)
- [クロステナント アクセス設定とは何ですか?](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)
- [ゲストを招待するにはどうすればよいですか?](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)
- [招待を受け入れる方法](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience)
- [セルフサービス サインアップを使用する方法](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-user-flow)
- [ユーザーの種類は何ですか?](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)

#### コンシューマー アプリ (CIAM) の ID を管理する

- [外部テナントの外部 ID とは](https://learn.microsoft.com/ja-jp/entra/external-id/customers/overview-customers-ciam)
- [アプリを登録する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)
- [アプリのサンプルとチュートリアルは何ですか?](https://learn.microsoft.com/ja-jp/entra/external-id/customers/samples-ciam-all)
- [顧客向けのユーザー フローを作成するにはどうすればよいですか?](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)
- [アプリをユーザー フローに追加するにはどうすればよいですか?](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)

### リソース

[Microsoft Entra 外部 ID デベロッパー センター](https://aka.ms/ciam/dev)
コード サンプル、ビデオ、コミュニティ リンクを調べます。

[Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/)
開発者が Microsoft ID プラットフォームを使用して、ユーザーをサインインさせ、外部 ID のサポートを含む Microsoft API にアクセスするアプリを作成する方法について説明します。

[Microsoft Entra PowerShell](https://learn.microsoft.com/ja-jp/powershell/entra-powershell)
外部 ID リソースをプログラムで管理します。

[Microsoft Entra ID のサポートを受ける](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-get-support)
Microsoft Entra ID のヘルプとサポートを受けるために、いくつかのオプションについて説明します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/add-users-administrator"} -->
## B2B コラボレーション ユーザーの追加 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator
- Service: entra-external-id / external
- Article date: 2026-03-20
- Summary: Microsoft Entra 管理センターで B2B コラボレーション ユーザーを追加する方法について説明します。 ゲスト ユーザーをディレクトリ、グループ、またはアプリケーションに招待し、リソースへのアクセスを管理します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

制限付き管理者ディレクトリ ロールのいずれかを割り当てられたユーザーは、Microsoft Entra 管理センターを使用して B2B コラボレーション ユーザーを招待できます。 ゲスト ユーザーをディレクトリ、グループ、またはアプリケーションに招待できます。 これらの方法のいずれかを使用してユーザーを招待すると、招待されたユーザーのアカウントが、ユーザーの種類が *Guest* の Microsoft Entra ID に追加されます。 次に、ゲスト ユーザーは、招待に応じてリソースにアクセスします。 ユーザーの招待が期限切れになることはありません。

ゲスト ユーザーをディレクトリに追加すると、共有アプリへの直接リンクをゲスト ユーザーに送信するか、ゲスト ユーザーが招待メール内の引き換えの URL を選択できます。 引き換えプロセスの詳細については、「[B2B コラボレーション招待の引き換え](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience)」を参照してください。

重要

[「方法: Microsoft Entra ID に組織のプライバシー情報を追加して、組織のプライバシー](https://learn.microsoft.com/ja-jp/entra/fundamentals/properties-area)に関する声明の URL を追加する」の手順に従う必要があります。 最初の招待の利用プロセスの一環として、招待したユーザーは続行するために、プライバシー条項に同意する必要があります。

このトピックの手順では、外部ユーザーを招待するための基本的な手順について説明します。 外部ユーザーを招待するときに含めることができるすべてのプロパティと設定については、「ユーザーを [作成および削除する方法](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)」を参照してください。

### 前提条件

組織の外部コラボレーション設定が、ゲストを招待できるように構成されていることを確認します。 既定では、すべてのユーザーと管理者がゲストを招待できます。 ただし、組織の外部コラボレーション ポリシーが、特定の種類のユーザーまたは管理者がゲストを招待できないように構成されている場合があります。 これらのポリシーを表示および設定する方法については、「 [B2B 外部コラボレーションを有効にして、ゲストを招待できるユーザーを管理する](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)」を参照してください。

### ゲスト ユーザーをディレクトリに追加する

B2B コラボレーション ユーザーをディレクトリに追加するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Microsoft Entra ID**&gt;**Users** に移動します。

[Image: [すべてのユーザー] ページのスクリーンショット。]

1. メニューから [ **新しいユーザー**&gt;**外部ユーザー** を招待する] を選択します。

[Image: [外部ユーザーの招待] メニュー オプションのスクリーンショット。]

#### 基本

このセクションでは、"ゲストのメール アドレス" を使ってゲストをテナントに招待します。 ドメイン アカウントでゲスト ユーザーを作成する必要がある場合は、 [新しいユーザーの作成プロセス](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users#create-a-new-user) を使用しますが、[ **ユーザーの種類]** を **[ゲスト**] に変更します。

- **電子メール**: 招待するゲスト ユーザーのメール アドレスを入力します。
- **表示名: 表示**名を指定します。
- **招待メッセージ**: ゲストに簡単なメッセージをカスタマイズするには、[ **招待メッセージの送信** ] チェック ボックスをオンにします。 必要に応じて CC 受信者を指定します。

[Image: 外部ユーザーの招待の [基本] タブのスクリーンショット。]

[ **確認と招待** ] ボタンを選択して新しいユーザーを作成するか、[ **次へ: プロパティ]** を選択して次のセクションを完了します。

#### プロパティ

指定できるユーザー プロパティには、6 つのカテゴリがあります。 これらのプロパティは、ユーザーの作成後に追加または更新できます。 これらの詳細を管理するには、 **Microsoft Entra ID**&gt;**Users** に移動し、更新するユーザーを選択します。

- **同一性：** ユーザーの名とファミリ名を入力します。 [ユーザーの種類] を [メンバー] または [ゲスト] に設定します。 外部ゲストとメンバーの違いの詳細については、 [B2B コラボレーション ユーザーのプロパティ](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)に関するページを参照してください。
- **ジョブ情報:** ユーザーの役職、部署、マネージャーなど、ジョブ関連の情報を追加します。
- **連絡先情報:** ユーザーに関連する連絡先情報を追加します。
- **保護者によるコントロール:** K-12 学区などの組織では、ユーザーの年齢グループを指定する必要がある場合があります。 "年少者" は12歳以下、"未成年" は 13 から 18歳、"大人" は 18 歳より上です。 年齢グループと親オプションによって提供される同意の組み合わせによって、法的年齢グループの分類が決まります。 法的年齢グループの分類により、ユーザーのアクセス権と権限が制限される場合があります。
- **設定：** ユーザーのグローバルな場所を指定します。

[ **確認と招待** ] ボタンを選択して新しいユーザーを作成するか、[ **次へ: 割り当て]** を選択して次のセクションを完了します。

#### 課題

アカウントの作成時に、外部ユーザーをグループまたは Microsoft Entra ロールに割り当てることができます。 ユーザーを最大 20 のグループまたはロールに割り当てることができます。 ユーザーの作成後にグループおよびロールの割り当てを追加できます。 Microsoft Entra ロールを割り当てるには、 **特権ロール管理者** ロールが必要です。

**新しいユーザーにグループを割り当てるには**:

1. [ **+ グループの追加] を選択します**。
2. 表示されるメニューから、リストから最大 20 個のグループを選択し、[選択] ボタンを **選択** します。
3. [ **確認と作成** ] ボタンを選択します。

[Image: グループ割り当ての追加プロセスのスクリーンショット。]

**新しいユーザーにロールを割り当てるには**:

1. [ **+ ロールの追加] を選択します**。
2. 表示されるメニューから、一覧から最大 20 個のロールを選択し、[選択] ボタンを **選択** します。
3. [ **確認と招待** ] ボタンを選択します。

#### 確認と作成

最後のタブでは、ユーザー作成プロセスからいくつかの重要な詳細がキャプチャされます。 詳細を確認し、すべてが適切な場合は [ **招待** ] ボタンを選択します。 ユーザーに招待メールが自動的に送信されます。 招待を送信すると、ユーザー アカウントがディレクトリにゲストとして自動的に追加されます。

[Image: 新しいゲスト ユーザーを含むユーザーリストを示すスクリーンショット。]

#### 外部ユーザーの招待

招待メールを送信して外部ゲスト ユーザーを招待したとき、ユーザーの詳細から招待の状態をチェックできます。 まだ招待に応じていない場合、招待メールを再送信できます。

1. **Microsoft Entra ID**&gt;**Users** に移動し、招待されたゲスト ユーザーを選択します。
2. [ **マイ フィード** ] セクションで、 **B2B コラボレーション** タイルを見つけます。

    - 招待の状態が **[承諾待ち] の**場合は、[ **招待の再送信** ] リンクを選択して別のメールを送信し、プロンプトに従います。
    - ユーザーの **プロパティ** を選択し、 **招待の状態**を表示することもできます。

    [Image: ユーザー概要ページの [マイ フィード] セクションのスクリーンショット。]

    注

    グループのメール アドレスはサポートされていません。個人のメール アドレスを入力してください。 また、一部の電子メール プロバイダーでは、ユーザーはプラス記号 (+) と追加テキストを電子メール アドレスに付け加えて、受信ボックスのフィルター処理などに役立てることができます。 ただし、Microsoft Entra では現在、電子メール アドレスのプラス記号はサポートされていません。 配信の問題を回避するために、プラス記号と、それに続く @ 記号より前の任意の文字を含めません。

    ユーザーは、 *emailaddress* #EXT#@*domain* という形式のユーザー プリンシパル名 (UPN) を使用してディレクトリに追加されます。 たとえば、fabrikam.onmicrosoft.com はあなたが招待を送信した組織であり、例として john\_contoso.com#EXT#@fabrikam.onmicrosoft.com があります。 ([B2B コラボレーション ユーザー プロパティの詳細については、こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties))。

### ゲスト ユーザーをグループに追加する

ユーザーが招待された後、B2B コラボレーション ユーザーをグループに手動で追加する必要がある場合は、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Microsoft Entra ID**&gt;**グループ**&gt;**すべてのグループ**にアクセスします。
3. グループを選択します (または、[ **新しいグループ** ] を選択して新しいグループを作成します)。 グループに B2B ゲスト ユーザーが含まれていることをグループの説明に含めることをお勧めします。
4. [ **管理**] で [メンバー] を選択 **します**。
5. [ **メンバーの追加] を選択します**。
6. 次の一連の手順を実行します。

    - *ゲスト ユーザーが既にディレクトリに存在する場合:*

        a. [ **メンバーの追加** ] ページで、ゲスト ユーザーの名前またはメール アドレスの入力を開始します。

        b。 検索結果でユーザーを選択し、[選択] を **選択します**。

    Microsoft Entra B2B Collaboration で動的メンバーシップ グループを使用することもできます。 詳細については、「 [動的グループと Microsoft Entra B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/use-dynamic-groups)」を参照してください。

### ゲスト ユーザーをアプリケーションに追加する

B2B コラボレーション ユーザーをアプリケーションに追加するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Microsoft Entra ID**&gt;**Enterprise アプリ**に移動します。
3. [ **すべてのアプリケーション** ] ページで、ゲスト ユーザーを追加するアプリケーションを選択します。
4. [ **管理**] で、[ **ユーザーとグループ**] を選択します。
5. [ **ユーザー/グループの追加]** を選択します。
6. [ **割り当ての追加]** ページで、[ **ユーザー**] の下にあるリンクを選択します。
7. 次の一連の手順を実行します。

    - *ゲスト ユーザーが既にディレクトリに存在する場合:*

        a. [ **ユーザー** ] ページで、ゲスト ユーザーの名前またはメール アドレスの入力を開始します。

        b。 検索結果でユーザーを選択し、[選択] を **選択します**。

        c. [ **割り当ての追加]** ページで、[ **割り当て** ] を選択してアプリにユーザーを追加します。
8. ゲスト ユーザーは、**既定のアクセス**のロールが割り当てられているアプリケーションの**ユーザーとグループ**の一覧に表示されます。 アプリケーションが別のロールを提供する場合、ユーザーのロールを変更したいのであれば、次の操作を行います。

    a. ゲスト ユーザーの横にあるチェック ボックスをオンにし、[ **編集** ] ボタンを選択します。

    b。 [ **割り当ての編集** ] ページで、[ **ロールの選択**] の下にあるリンクを選択し、ユーザーに割り当てるロールを選択します。

    c. **選択**を選択します。

    d. **[割り当て]**を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/add-users-information-worker"} -->
## インフォメーション ワーカーとして B2B コラボレーション ユーザーを追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/add-users-information-worker
- Service: entra-external-id / external
- Article date: 2025-04-09
- Summary: B2B コラボレーションを使用すると、インフォメーション ワーカーとアプリ所有者は、アクセスのためにゲスト ユーザーを Microsoft Entra ID に追加できます。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ゲスト ユーザーが Microsoft Entra ID のディレクトリに追加されると、アプリケーション所有者は、共有するアプリへの直接リンクをゲスト ユーザーに送信します。 Microsoft Entra 管理者は、Microsoft Entra テナント内のギャラリーまたは SAML ベースのアプリのセルフサービス管理を設定できます。 これにより、ゲスト ユーザーがまだディレクトリに追加されていない場合でも、アプリケーション所有者がゲスト ユーザーを管理できます。 アプリがセルフサービス用に構成されたら、アプリケーション所有者はアクセス パネルを使用して、アプリにゲスト ユーザーを招待するか、またはアプリにアクセスできるグループにゲスト ユーザーを追加します。

ギャラリーおよび SAML ベースのアプリのセルフサービス アプリ管理には、管理者によるいくつかの初期セットアップが必要です。セットアップ手順の概要に従います (詳細な手順については、このページの後の 「前提条件 」を参照してください)。

- テナントのセルフサービス グループ管理を有効にする
- アプリに割り当てるグループを作成して、ユーザーを所有者にする
- セルフサービス用にアプリを設定し、アプリにグループを割り当てる

メモ

- この記事では、Microsoft Entra テナントに追加したギャラリーおよび SAML ベースのアプリのセルフサービス管理を設定する方法について説明します。 ユーザーが自分の [Microsoft 365 グループ](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management) へのアクセスを管理できるように、セルフサービスの Microsoft 365 グループを設定することもできます。 ユーザーが Office ファイルやアプリをゲスト ユーザーと共有するその他の方法については、「 [Microsoft 365 グループおよび](https://support.office.com/article/guest-access-in-office-365-groups-bfc7a840-868f-4fd6-a390-f347bf51aff6)[SharePoint ファイルまたはフォルダー](https://support.office.com/article/share-sharepoint-files-or-folders-1fe37332-0f9a-4719-970e-d2578da4941c)でのゲスト アクセス」を参照してください。
- ユーザーは、ゲスト招待元ロールを持っている場合にのみ **ゲストを招待** できます。

### アプリにアクセスできるグループに参加する人を招待する

セルフサービス用にアプリを構成したら、共有するアプリにアクセスできる管理グループにゲスト ユーザーを招待できます。 ゲスト ユーザーは、ディレクトリに既に存在している必要はありません。 アプリケーション所有者は次の手順に従って、アプリにアクセスできるようにゲスト ユーザーをグループに招待します。

1. 共有するアプリにアクセスできるセルフサービス グループの所有者であることを確認します。
2. `https://myapps.microsoft.com` に移動して、アクセス パネルを開きます。
3. **グループ** アプリを選択します。

[Image: アクセス パネルの [グループ] アプリを示すスクリーンショット。]

1. **[自分が所有するグループ**] で、共有するアプリにアクセスできるグループを選択します。

[Image: [所有しているグループ] でグループを選択する場所を示すスクリーンショット。]

1. グループ メンバーの一覧の上部にある [ **+** ] ボタンを選択します。

[Image: グループにメンバーを追加するためのプラス記号を示すスクリーンショット。]

1. [ **メンバーの追加** ] 検索ボックスに、ゲスト ユーザーのメール アドレスを入力します。 必要に応じて、ようこそメッセージを含めます。

[Image: ゲストを追加するための [メンバーの追加] ウィンドウを示すスクリーンショット。]

1. 招待をゲスト ユーザーに自動的に送信するには、[ **追加]** を選択します。 招待を送信すると、ユーザー アカウントがディレクトリにゲストとして自動的に追加されます。

### 前提条件

セルフサービス アプリ管理には、Microsoft Entra 管理者による初期セットアップが必要です。このセットアップの一環として、セルフサービス用にアプリを構成し、アプリケーション所有者が管理できるグループをアプリに割り当てます。 また、すべてのユーザーがメンバーシップを要求できるようにグループを設定することもできますが、グループ所有者の承認が必要です。 ( [セルフサービス グループ管理](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management)の詳細については、こちらを参照してください)。

メモ

ゲスト ユーザーを、動的グループまたはオンプレミスの Active Directory と同期されているグループに追加することはできません。

#### テナント用にセルフサービスのグループ管理を有効化する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All groups** に移動します。
3. **設定**で**全般**を選択します。
4. [ **セルフサービス グループ管理**] の [ **所有者はアクセス パネルでグループ メンバーシップ要求を管理できます**] の横にある [ **はい**] を選択します。
5. **[保存] を選択します**。

#### アプリに割り当てるグループを作成して、ユーザーを所有者にする

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All groups** に移動します。
3. [ **新しいグループ]** を選択します。
4. [ **グループの種類**] で、[セキュリティ] を選択 **します**。
5. **グループ名とグループ**の**説明**を入力します。
6. [ **メンバーシップの種類**] で 、[ **割り当て済み**] を選択します。
7. [ **作成]** を選択し、[ **グループ** ] ページを閉じます。
8. [ **グループ - すべてのグループ** ] ページで、グループを開きます。
9. [**管理**] で、[**所有者**] &gt; [**所有者の追加**] を選択します。 アプリケーションへのアクセスの管理を担うユーザーを検索します。 ユーザーを選択し、[選択] をクリックします。

#### セルフサービス用にアプリを構成して、グループをアプリに割り当てる

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**にアクセスします。
3. **[すべてのアプリケーション] を**選択し、アプリケーションの一覧でアプリを見つけて開きます。
4. [ **管理**] で [ **シングル サインオン**] を選択し、シングル サインオン用にアプリケーションを設定します。 (詳細については、 [エンタープライズ アプリのシングル サインオンを管理する方法を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso)参照してください)。
5. [ **管理**] で [ **セルフサービス**] を選択し、セルフサービス アプリ アクセスを設定します。 (詳細については、 [セルフサービス アプリ アクセスの使用方法を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-self-service-access)参照してください)。

    メモ

    **どのグループに割り当てられたユーザーを追加するか** 設定では、前のセクションで作成したグループを選択します。
6. [ **管理**] で [ **ユーザーとグループ**] を選択し、作成したセルフサービス グループが一覧に表示されることを確認します。
7. グループ所有者のアクセス パネルにアプリを追加するには、[**ユーザーの追加**]、[**ユーザー&gt;グループ]** の順に選択します。 グループ所有者を検索し、ユーザーを選択し、[ **選択**] を選択し、[ **割り当て]** を選択してユーザーをアプリに追加します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/allow-deny-list"} -->
## 招待を許可またはブロックする - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/allow-deny-list
- Service: entra-external-id / external
- Article date: 2026-04-24
- Summary: 管理者が Microsoft Entra 管理センターを使用して、特定のドメインとの B2B コラボレーションを許可またはブロックするリストを作成する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

許可リストまたはブロック リストを使用して、B2B コラボレーションの招待を受け取ることができる組織を制御します。 たとえば、個人用メール ドメインをブロックするには、Gmail.com や Outlook.com などのドメインをブロック リストに追加します。 パートナー組織にのみ招待を許可するには、Contoso.com、Fabrikam.com、Litware.com などのドメインを許可リストに追加します。

この記事では、B2B コラボレーションの許可リストまたはブロック リストを構成する方法について説明します。

- ポータルで、組織の[外部コラボレーション設定](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)でコラボレーションの制限を構成する

### 重要な考慮事項

- 許可リストまたはブロック リストを作成できます。 両方の種類のリストを設定することはできません。 既定では、許可リストに含まれていないドメインはブロック リストに含まれます。その逆も同様です。
- 各組織に作成できるポリシーは 1 つだけです。 ポリシーを更新してより多くのドメインを含めることも、ポリシーを削除して新規に作成することもできます。
- 許可リストまたはブロック リストに追加できるドメインの数は、ポリシーのサイズによってのみ制限されます。 この制限は文字数に適用されるため、多数の短いドメインを使用するか、少数の長いドメインを使用できます。 ポリシー全体の最大サイズは 25 KB (25,000 文字) です。これには、許可リストまたはブロック リスト、およびその他の機能用に構成されたその他のパラメーターが含まれます。 たとえば、ドメイン エントリがそれぞれ平均 15 文字の場合は、約 1,600 個のドメインをポリシーに追加できます。
- このリストは、OneDrive や SharePoint Online の許可/ブロック リストとは無関係に機能します。 SharePoint Online で個々のファイル共有を制限する場合は、OneDriveと SharePoint Online の許可またはブロック リストを設定する必要があります。 詳細については、「[ドメインによる SharePoint コンテンツと OneDrive コンテンツの共有を制限する](https://support.office.com/article/restricted-domains-sharing-in-sharepoint-online-and-onedrive-for-business-5d7589cd-0997-4a00-a2ba-2320ec49c4e9)」を参照してください。
- このリストは、招待を既に使用した外部ユーザーには適用されません。 リストは、リストの設定後に適用されます。 ユーザーの招待が保留中の状態にあり、ユーザーのドメインをブロックするポリシーを設定した場合、ユーザーが招待の使用を試みると失敗します。
- 許可/ブロック リストとテナント間アクセスの両方の設定は、招待時に確認されます。

### ポータルで許可またはブロック リスト ポリシーを設定する

既定では、**[Allow invitations to be sent to any domain (most inclusive) (どのドメインに送信される招待も許可する (最も包括的)]** の設定が有効になっています。 この場合、任意の組織から B2B ユーザーを招待できます。

重要

Microsoft は、アクセス許可が最も少ないロールを使用することを推奨しています。 このプラクティスは、組織のセキュリティを強化するのに役立ちます。 グローバル管理者は、緊急シナリオに限定する必要がある、または既存のロールを使用できない場合に、高い特権を持つロールです。

#### ブロック リストを追加する

これは最も一般的なシナリオです。組織はほぼすべての組織と連携したいと考えていますが、特定のドメインのユーザーが B2B ユーザーとして招待されないようにしたいと考えています。

ブロック リストを追加するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **Entra ID**&gt;**外部 ID**&gt;**外部協力設定**に移動します。
3. **[Collaboration restrictions (コラボレーション制限)]** で、**[Deny invitations to the specified domains (指定したドメインへの招待を拒否)]** を選択します。
4. **[ターゲット ドメイン]** で、ブロックするドメイン名を 1 つ入力します。 複数ドメインの場合は、それぞれのドメインを新しい行に入力します。 次に例を示します。

    [Image: 追加したドメインと共に拒否するオプションを示すスクリーンショット。]
5. 終了したら、 **[保存]** を選択します。

ポリシーを保存すると、ブロックされたドメインへの招待が失敗し、招待元にブロックされたドメイン メッセージが表示されます。

#### 許可リストを追加する

これは、より制限の厳しい構成です。 招待を受け取ることができるのは、許可リスト内のドメインのみです。

許可リストを使用する場合は、ビジネス ニーズを完全に評価するために時間がかかります。 このポリシーを制限しすぎると、ユーザーは電子メールでドキュメントを送信したり、承認されていない他の方法を使用して共同作業を行ったりすることがあります。

許可リストを追加するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **Entra ID**&gt;**外部 ID**&gt;**外部協力設定**に移動します。
3. **[Collaboration restrictions](https://learn.microsoft.com/ja-jp/entra/external-id/コラボレーション制限)** の **[Allow invitations only to the specified domains (most restrictive)](指定したドメインへの招待を許可 (制限が最も厳しい))** を選択します。
4. **[ターゲット ドメイン]** で、許可するドメイン名を 1 つ入力します。 複数ドメインの場合は、それぞれのドメインを新しい行に入力します。 次に例を示します。

    [Image: 追加したドメインと共に許可オプションを示すスクリーンショット。]
5. 終了したら、 **[保存]** を選択します。

ポリシーを保存すると、許可リストにないドメインへの招待が失敗し、招待元にブロックされたドメイン メッセージが表示されます。

#### 許可リストからブロック リストへの切り替え、またはその逆

あるポリシーから別のポリシーに切り替えると、既存のポリシー構成は破棄されます。 切り替えを実行する前に、構成の詳細情報をバックアップしてください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/api-connectors-overview"} -->
## セルフサービスのサインアップ フローでの API コネクタについて - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/api-connectors-overview
- Service: entra-external-id / external
- Article date: 2026-04-17
- Summary: Microsoft Entra API コネクタを使用して、Web API を使用してセルフサービス サインアップ ユーザー フローをカスタマイズおよび拡張します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

### 概要

開発者または IT 管理者は、[API コネクタ](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-add-api-connector#create-an-api-connector)を使用し、[セルフサービス サインアップ ユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-overview)と Web API を統合してサインアップ体験をカスタマイズしたり、外部システムと統合したりできます。 たとえば、API コネクタを使用すると、次のことができます。

- [**カスタム承認ワークフローと統合します**](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-add-approvals)。 アカウントの作成を管理したり、制限したりするには、カスタム承認システムに接続します。
- [**本人確認**](https://learn.microsoft.com/ja-jp/entra/external-id/code-samples-self-service-sign-up#identity-verification)を実行します。 本人確認サービスを使用して、アカウント作成の決定のセキュリティ レベルを向上させます。
- **ユーザー入力データの検証**。 不正な、または無効なユーザー データに対する検証を行います。 たとえば、ユーザーが提供したデータを外部データ ストア内の既存のデータまたは許可されている値の一覧と照らし合わせて検証することができます。 無効な場合は、有効なデータを提供するようユーザーに求めることも、ユーザーがサインアップ フローを続行できないようにすることもできます。
- **ユーザー属性を上書する** ユーザーから収集された属性を再フォーマットし、それに値を割り当てます。 たとえば、ユーザーが名をすべて小文字または大文字で入力する場合に、名の最初の文字だけを大文字にするように書式設定できます。
- **カスタム ビジネス ロジックを実行します**。 ご利用のクラウド システム内で下流イベントをトリガーすれば、プッシュ通知の送信、企業データベースの更新、アクセス許可の管理、データベースの監査、およびその他のカスタム アクションの実行を行うことができます。

API コネクタは、API 呼び出しの HTTP エンドポイント URL と認証を定義することで、API エンドポイントを呼び出すために必要な情報をMicrosoft Entra IDに提供します。 API コネクタを構成したら、それをユーザーフローの特定のステップに対して有効にすることができます。 ユーザーがサインアップ フローでそのステップに到達すると、API コネクタが呼び出され、HTTP POST 要求として API に送信されます。ユーザー情報 (要求) は、JSON 本文のキーと値のペアとして使用されます。 API 応答は、ユーザー フローの実行に影響を与える可能性があります。 たとえば、API 応答によって、ユーザーのサインアップがブロックされたり、ユーザーが情報の再入力を求められたり、ユーザー属性が上書きおよび追加されたりする可能性があります。

### ユーザー フロー内で API コネクタを有効にできる場所

ユーザー フロー内には、API コネクタを有効にできる場所が 2 か所あります。

- サインアップ時に ID プロバイダーとのフェデレーションを行った後
- ユーザーを作成する前

重要

どちらの場合も、API コネクタは、サインイン時ではなく、ユーザーの**サインアップ**時に呼び出されます。

#### サインアップ時に ID プロバイダーとのフェデレーションを行った後

サインアップ プロセスのこの手順の API コネクタは、ユーザーが ID プロバイダー (Google、Facebook、Microsoft Entra ID など) で認証した直後に呼び出されます。 この手順は、 [ユーザー属性を収集](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-user-flow#select-the-layout-of-the-attribute-collection-form)するためにユーザーに表示されるフォームである属性コレクション ページの前にあります。 ユーザーがローカル アカウントを使用して登録している場合、このステップは呼び出されません。 このステップでお客様が有効する可能性がある API コネクタのシナリオの例を次に示します。

- ユーザーが入力した電子メールまたはフェデレーション ID を使用して、既存のシステム内でクレームを検索します。 既存のシステムからこれらのクレームを返し、属性コレクション ページを事前に入力して、それらをトークンで返すことができるようにします。
- ソーシャル ID に基づいて許可または禁止リストを実装します。

#### ユーザーを作成する前

サインアップ プロセスのこのステップでの API コネクタは、属性コレクション ページが含まれている場合、その後に呼び出されます。 このステップは、ユーザー アカウントが作成される前に必ず呼び出されます。 お客様がサインアップ中にこのポイントで有効にする可能性があるシナリオの例を次に示します。

- ユーザー入力データを検証し、データの再送信をユーザーに求めます。
- ユーザーが入力したデータに基づいてユーザーのサインアップをブロックします。
- 本人確認を実行します。
- 外部システムにクエリを実行して、ユーザーに関する既存のデータをアプリケーション トークンに返すか、Microsoft Entra IDに格納します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/auditing-and-reporting"} -->
## B2B コラボレーション ユーザーの監査およびレポート - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/auditing-and-reporting
- Service: entra-external-id / external
- Article date: 2024-10-21
- Summary: ゲスト ユーザーは、Microsoft Entra B2B コラボレーションで構成できます

**適用対象**: [Image: 白いチェック マークが付いた緑色の円。] ワークフォース テナント [Image: 灰色の X 記号が付いた白い円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ゲスト ユーザーに対しても、メンバー ユーザーに対する場合と同様に、監査機能を使用できます。

### アクセス レビュー

アクセス レビューを使用すると、ゲスト ユーザーがリソースへのアクセスをまだ必要としているかどうかを定期的に確認できます。 **アクセス レビュー**機能は、**ID ガバナンス**&gt;の**下の Microsoft Entra ID** で利用できます。 アクセス レビューを使用する方法については、「 [Microsoft Entra アクセス レビューを使用してゲスト アクセスを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-guest-access-with-access-reviews)」を参照してください。

### 監査ログ

Microsoft Entra 監査ログはシステムとユーザーのアクティビティのレコードを提供しますが、これにはゲスト ユーザーによって開始されたアクティビティも含まれます。 監査ログにアクセスするには、**Entra ID**&gt;**監視と健康**&gt;**監査記録**を参照します。 特定のユーザーの監査ログにアクセスするには、**Entra ID**&gt;**ユーザー**を選択し、該当するユーザーを選択してから&gt;&gt;を選択します。

[Image: 監査ログ出力の例を示すスクリーンショット。]

各イベントに立ち入って、詳細を取得することができます。 たとえば、ユーザー管理の詳細を確認しましょう。

[Image: アクティビティの詳細出力の例を示すスクリーンショット。]

これらのログを Microsoft Entra ID からエクスポートし、任意のレポート ツールを使用してレポートをカスタマイズすることもできます。

### B2B ユーザーのスポンサー フィールド

スポンサー機能を使用して、組織のゲスト ユーザーを管理および追跡することもできます。 ユーザー アカウント **の [スポンサー** ] フィールドには、ゲスト ユーザーの責任者が表示されます。 スポンサーには、ユーザーまたはグループを指定できます。 スポンサー機能の詳細については、「 [ゲスト ユーザーにスポンサーを追加する」を](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-sponsors)参照してください。

#### 関連するコンテンツ

- [B2B コラボレーションのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/external-id/troubleshoot)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/authentication-conditional-access"} -->
## B2B ユーザーの認証と条件付きアクセス - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access
- Service: entra-external-id / external
- Article date: 2026-03-27
- Summary: Microsoft Entra B2B ユーザーに多要素認証ポリシーを適用する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ヒント

この記事は、従業員テナントの B2B Collaboration と B2B 直接接続を対象としています。 外部テナントの詳細については、「[Microsoft Entra 外部 ID のセキュリティとガバナンス](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-security-customers)」を参照してください。

外部ユーザーが組織内のリソースにアクセスする場合、認証フローは、コラボレーション方法 (B2B コラボレーションまたは B2B 直接接続)、ユーザーの ID プロバイダー (外部の Microsoft Entra テナントまたはソーシャル ID プロバイダーなど)、条件付きアクセス ポリシー、およびユーザーのホーム テナントとテナント ホスティング リソースの両方で構成された [テナント間アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview) によって決定されます。

この記事では、組織内のリソースにアクセスしている外部ユーザーの認証フローについて説明します。 組織は、外部ユーザーに対して複数の条件付きアクセス ポリシーを適用できます。これは、組織のフルタイムの従業員とメンバーに対して有効にしたのと同じ方法で、テナント、アプリ、または個々のユーザー レベルで適用できます。

### 外部 Microsoft Entra ユーザーの認証フロー

次の図は、Microsoft Entra 組織がリソースを他の Microsoft Entra 組織からのユーザーと共有する場合の認証フローを示しています。 この図は、テナント間のアクセス設定が多要素認証などの条件付きアクセス ポリシーと連携して、ユーザーがリソースにアクセスできるかどうかを判断する方法を示しています。 このフローは、手順 6 で示されている場合を除き、B2B コラボレーションと B2B 直接接続の両方に適用されます。

[Image: テナント間認証プロセスを示すダイアグラム。]

| 手順 | 説明 |
| --- | --- |
| **1** | Fabrikam (ユーザーの*ホーム テナント*) のユーザーが、Contoso (*リソース テナント*) のリソースへのサインインを開始します。 |
| **2** | サインイン中に、Microsoft Entra セキュリティ トークン サービス (STS) によって Contoso の条件付きアクセス ポリシーが評価されます。 また、テナント間アクセス設定 (Fabrikam の送信設定と Contoso の受信設定) を評価することで、Fabrikam ユーザーがアクセスを許可されているかどうかを確認します。 |
| **3** | Microsoft Entra ID は Contoso の受信信頼設定を検査して、Contoso が Fabrikam からの MFA とデバイス要求 (デバイス コンプライアンス、Microsoft Entra ハイブリッド参加済み状態) を信頼しているかどうかを確認します。 必要ない場合は、手順 6 に進みます。 |
| **4** | Contoso が Fabrikam からの MFA とデバイスの信頼性情報を信頼している場合、Microsoft Entra ID はユーザーの認証セッションを検査して、ユーザーが MFA を完了したことを確認します。 Contoso が Fabrikam からのデバイス情報を信頼している場合、Microsoft Entra ID は、デバイスの状態 (準拠または Microsoft Entra ハイブリッド参加済み) を示す要求を認証セッションで検索します。 |
| **5** | MFA が必須であるが完了していない場合、またはデバイス要求が指定されていない場合、Microsoft Entra ID は必要に応じてユーザーのホーム テナントで MFA とデバイス チャレンジを発行します。 Fabrikam で MFA とデバイスの要件が満たされている場合、ユーザーは Contoso のリソースへのアクセスを許可されます。 その確認事項を満たすことができない場合、アクセスはブロックされます。 |
| **6** | 信頼設定が構成されておらず、MFA が必要な場合、B2B コラボレーション ユーザーは MFA を要求されます。 リソース テナントで MFA を満たす必要があります。 B2B 直接接続ユーザーのアクセスはブロックされます。 デバイスのコンプライアンスが必要だが評価できない場合、B2B コラボレーションと B2B 直接接続の両方のユーザーのアクセスがブロックされます。 |

詳細については、「外部ユーザーの条件付きアクセス」セクションを参照してください。

### Microsoft Entra ID 以外の外部ユーザーの認証フローAuthentication flow for non-Microsoft Entra ID external users

Microsoft Entra 組織が Microsoft Entra ID 以外の ID プロバイダーを使用してリソースを外部ユーザーと共有する場合、認証フローは、ユーザーが ID プロバイダーまたは電子メール ワンタイム パスコード認証を使用して認証を行うかどうかによって異なります。 どちらの場合でも、リソース テナントは、使用する認証方法を識別し、ユーザーを ID プロバイダーにリダイレクトするか、ワンタイム パスコードを発行します。

#### 例 1: Microsoft Entra ID 以外の外部ユーザーの認証フローとトークン

次の図は、外部ユーザーが Microsoft Entra ID 以外の ID プロバイダー (Google、Facebook、フェデレーション SAML/WS-Fed ID プロバイダーなど) のアカウントでサインインする場合の認証フローを示しています。

[Image: 外部ディレクトリからの B2B ゲスト ユーザーの認証フローを示すダイアグラム。]

| 手順 | 説明 |
| --- | --- |
| **1** | B2B ゲスト ユーザーが、あるリソースへのアクセスを要求します。 このリソースで、ユーザーをそのリソース テナント (信頼された IdP) にリダイレクトします。 |
| **2** | リソース テナントで、ユーザーを外部として識別し、B2B ゲスト ユーザーの IdP にリダイレクトします。 ユーザーは IdP でプライマリ認証を実行します。 |
| **3** | 承認ポリシーは、B2B ゲスト ユーザーの IdP で評価されます。 ユーザーがこれらのポリシーを満たす場合、B2B ゲスト ユーザーの IdP はユーザーへトークンを発行します。 ユーザーはトークンとともにリソース テナントにリダイレクトされます。 リソース テナントはトークンを検証した後、ユーザーをその条件付きアクセス ポリシーに照らして評価します。 たとえば、リソース テナントでは、ユーザーが Microsoft Entra 多要素認証を実行するように要求する場合があります。 |
| **4** | テナント間のアクセス設定と条件付きアクセス ポリシーが評価されます。 すべてのポリシーが満たされる場合、リソース テナントで独自のトークンを発行し、ユーザーをそのリソースにリダイレクトします。 |

#### 例 2: ワンタイム パスコード ユーザーの認証フローとトークン

次の図は、電子メール ワンタイム パスコード認証が有効で、外部ユーザーが Microsoft Entra ID、Microsoft アカウント (MSA)、ソーシャル ID プロバイダーなどの他の方法では認証されない場合のフローを示しています。

[Image: ワンタイム パスコードを使用する B2B ゲスト ユーザーの認証フローを示すダイアグラム。]

| 手順 | 説明 |
| --- | --- |
| **1** | ユーザーが、別のテナントのリソースへのアクセスを要求します。 このリソースで、ユーザーをそのリソース テナント (信頼された IdP) にリダイレクトします。 |
| **2** | リソース テナントで、ユーザーを外部のメール ワンタイム パスコード (OTP) ユーザーとして識別し、OTP を含むメールをユーザーに送信します。 |
| **3** | ユーザーが OTP を取得し、コードを送信します。 リソース テナントで、ユーザーをその条件付きアクセス ポリシーに照らして評価します。 |
| **4** | すべての条件付きアクセス ポリシーが満たされると、リソース テナントでトークンを発行し、ユーザーをそのリソースにリダイレクトします。 |

### 外部ユーザーの条件付きアクセス

組織は、組織のフルタイムの従業員とメンバーに対して有効にしたのと同じ方法で、外部の B2B コラボレーションおよび B2B 直接接続ユーザーに条件付きアクセス ポリシーを適用できます。 テナント間アクセス設定を導入すると、外部の Microsoft Entra 組織からの MFA とデバイス要求を信頼することもできます。 このセクションでは、組織外のユーザーに条件付きアクセスを適用する際の重要な考慮事項について説明します。

注

条件付きアクセスを使用したカスタム コントロールは、テナント間の信頼ではサポートされていません。

#### 条件付きアクセス ポリシーを外部ユーザーの種類に割り当てる

条件付きアクセス ポリシーを構成する際に、ポリシーを適用する外部ユーザーの種類をきめ細かく制御できます。 外部ユーザーは、認証方法 (内部または外部) および組織との関係 (ゲストまたはメンバー) に基づいて分類されます。

- **B2B コラボレーション ゲスト ユーザー** - 一般的にゲストと見なされるほとんどのユーザーは、このカテゴリに分類されます。 この B2B コラボレーション ユーザーは、外部 Microsoft Entra 組織または外部 ID プロバイダー (ソーシャル ID など) にアカウントを持ち、組織内でゲストレベルのアクセス許可を持っています。 Microsoft Entra ディレクトリに作成されるユーザー オブジェクトの UserType は "ゲスト" です。 このカテゴリには、招待された、およびセルフサービス サインアップを使用した、B2B コラボレーション ユーザーが含まれます。
- **B2B コラボレーション メンバー ユーザー** - この B2B コラボレーション ユーザーは、外部 Microsoft Entra 組織または外部 ID プロバイダー (ソーシャル ID など) にアカウントを持ち、組織内のリソースに対するメンバーレベルのアクセス権を持っています。 このシナリオがよく見られるのは、複数のテナントで構成される組織において、ユーザーが大規模な組織の一部と見なされ、組織の他のテナント内のリソースに対するメンバーレベルのアクセス許可を必要とする場合です。 リソース Microsoft Entra ディレクトリに作成されるユーザー オブジェクトの UserType は "メンバー" です。
- **B2B 直接接続ユーザー** - 特定の Microsoft アプリケーション (現在、Microsoft Teams Connect 共有チャネル) へのシングル サインオン アクセスを許可する別の Microsoft Entra 組織との相互の双方向接続である B2B 直接接続を介してリソースにアクセスできる外部ユーザー。 B2B 直接接続ユーザーは、Microsoft Entra 組織にプレゼンスを持たず、代わりにアプリケーション内から (Teams 共有チャネル所有者によるなどして) 管理されます。
- **ローカル ゲスト ユーザー** - ローカル ゲスト ユーザーには、ディレクトリ内で管理される資格情報があります。 Microsoft Entra B2B コラボレーションが利用できるようになる前は、販売代理店、仕入先、製造元、およびその他のユーザーと共同作業を行うには、これらのユーザーの内部資格情報を設定し、ユーザー オブジェクトの UserType を "ゲスト" に設定することで、ゲストとして指定するのが一般的でした。
- **サービス プロバイダー ユーザー** - 組織のクラウド サービス プロバイダーとして機能する組織 (Microsoft Graph [パートナー固有の構成](https://learn.microsoft.com/ja-jp/graph/api/resources/crosstenantaccesspolicyconfigurationpartner)の isServiceProvider プロパティは true です)。
- **その他の外部ユーザー** - これらのカテゴリに該当せず、さらに組織の内部メンバーと見なされないユーザー (つまり、Microsoft Entra ID を介して内部的に認証されず、リソース Microsoft Entra ディレクトリに作成されるユーザー オブジェクトの UserType が "メンバー" ではないユーザー) に適用されます。

注

[すべてのゲストと外部ユーザー] の選択は、[Guest and external users] (ゲストと外部ユーザー) とそのすべてのサブタイプに置き換えられました。 以前に "すべてのゲストと外部ユーザー" が選択された条件付きアクセス ポリシーを使用していたお客様の場合は、すべてのサブ タイプが選択された状態で "ゲストと外部ユーザー" が表示されるようになります。 UX でのこの変更は、条件付きアクセス バックエンドによるポリシーの評価方法に機能的な影響を与えることはありません。 この新しい選択により、条件付きアクセス ポリシーの作成時にユーザー スコープに含める/除外する特定の種類のゲストと外部ユーザーを選択するために必要な細分性が得られます。

[条件付きアクセスのユーザー割り当て](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-users-groups)の詳細について説明します。

#### 外部 ID 条件付きアクセス ポリシーの比較

次の表は、Microsoft Entra 外部 ID のセキュリティ ポリシーとコンプライアンス オプションの詳細な比較を示しています。 セキュリティ ポリシーとコンプライアンスは、条件付きアクセス ポリシーの下でホスト/招待元の組織によって管理されます。

| **ポリシー** | **B2B コラボレーション ユーザー** | **B2B 直接接続ユーザー** |
| --- | --- | --- |
| **制御の許可 - アクセスをブロックする** | サポートされています | サポートされています |
| **制御の許可 - 多要素認証を要求する** | サポートされています | サポートされています。外部組織からの MFA 要求を受け入れるように[受信信頼設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-direct-connect#to-change-inbound-trust-settings-for-mfa-and-device-state)を構成する必要があります |
| **制御の許可 - 準拠したデバイスが必要です** | サポートされています。外部組織からの準拠デバイス要求を受け入れるように[受信信頼設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#to-change-inbound-trust-settings-for-mfa-and-device-claims)を構成する必要があります。 | サポートされています。外部組織からの準拠デバイス要求を受け入れるように[受信信頼設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-direct-connect#to-change-inbound-trust-settings-for-mfa-and-device-state)を構成する必要があります。 |
| **アクセス許可制御 - Microsoft Entra ハイブリッド参加済みデバイスが必要** | サポートされています。外部組織からの Microsoft Entra ハイブリッド参加済みデバイス要求を受け入れるように[受信信頼設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#to-change-inbound-trust-settings-for-mfa-and-device-claims)を構成する必要があります | サポートされています。外部組織からの Microsoft Entra ハイブリッド参加済みデバイス要求を受け入れるように[受信信頼設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-direct-connect#to-change-inbound-trust-settings-for-mfa-and-device-state)を構成する必要があります |
| **アクセス制御を許可 - 承認済みのクライアントアプリが必要です** | サポート対象外 | サポート対象外 |
| **制御の許可 - アプリ保護ポリシーが必要** | サポート対象外 | サポート対象外 |
| **制御の許可 - パスワードの変更を要求する** | サポート対象外 | サポート対象外 |
| **アクセス権の管理 - 使用条件** | サポートされています | サポート対象外 |
| **セッション制御 - アプリによって適用される制限を使用** | サポートされています | サポート対象外 |
| **セッション制御 - アプリの条件付きアクセス制御を使う** | サポートされています | サポート対象外 |
| **セッション制御 - サインインの頻度** | サポートされています | サポート対象外 |
| **セッション制御 - 永続的なブラウザー セッション** | サポートされています | サポート対象外 |

#### Microsoft Entra の外部ユーザー向け多要素認証 (MFA)

Microsoft Entra テナント間シナリオでは、リソース組織は、すべてのゲスト ユーザーと外部ユーザーに対して MFA またはデバイスのコンプライアンスを要求する条件付きアクセス ポリシーを作成できます。 一般的に、リソースにアクセスする B2B コラボレーション ユーザーは、リソース テナントを使用して Microsoft Entra 多要素認証を設定する必要があります。 ただし、Microsoft Entra ID では、他の Microsoft Entra テナントからの MFA 要求を信頼する機能が提供されるようになりました。 別のテナントとの MFA 信頼を有効にすると、B2B コラボレーション ユーザーのサインイン プロセスが合理化され、B2B 直接接続ユーザーにアクセスできるようになります。

B2B コラボレーションまたは B2B 直接接続ユーザーのホーム テナントからの MFA 要求を受け入れるように受信信頼設定を構成した場合、Microsoft Entra ID はそのユーザーの認証セッションを確認します。 ユーザーのホーム テナントで MFA ポリシーが既に満たされていることを示す要求がセッションに含まれている場合、ユーザーには共有リソースへのシームレスなサインオンが付与されます。

MFA 信頼が有効になっていない場合、B2B コラボレーション ユーザーと B2B 直接接続ユーザーのユーザー エクスペリエンスは異なります。

- **B2B コラボレーション ユーザー**: リソース組織がユーザーのホーム テナントとの MFA 信頼を有効にしていない場合、ユーザーにはリソース組織からの MFA チャレンジが表示されます。 (このフローは、 Microsoft Entra ID 以外の外部ユーザーの MFA フローと同じです)。
- **B2B 直接接続ユーザー**: リソース組織がユーザーのホーム テナントとの MFA 信頼を有効にしていない場合、ユーザーはアクセスしているリソースからブロックされます。 外部組織との B2B 直接接続を許可する必要があり、条件付きアクセス ポリシーで MFA が必要な場合は、組織からの MFA 要求を受け入れるように、受信信頼設定を構成する*必要があります*。

[MFA の受信信頼設定を構成する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#to-change-inbound-trust-settings-for-mfa-and-device-claims)方法の詳細について説明します。

#### Microsoft Entra ID 以外の外部ユーザー向けの MFA

Microsoft Entra ID 以外の外部ユーザーの場合、リソース テナントは常に MFA を担当します。 次の例は、一般的な MFA フローを示しています。 このシナリオは、Microsoft アカウント (MSA) やソーシャル ID などの任意の ID に対して機能します。 また、このフローは、ユーザーのホーム Microsoft Entra 組織で信頼設定が構成されていない場合に、その Microsoft Entra 外部ユーザーにも適用されます。

1. Fabrikam という会社の管理者またはインフォメーション ワーカーが、Contoso という別の会社のユーザーをFabrikamのアプリに招待します。
2. Fabrikam のアプリは、アクセス時に Microsoft Entra 多要素認証を必要とするように構成されています。
3. Contoso の B2B コラボレーション ユーザーが Fabrikam のアプリにアクセスしようとする場合、Microsoft Entra 多要素認証のチャレンジの完了が求められます。
4. その後、ゲスト ユーザーは Fabrikam で Microsoft Entra 多要素認証を設定し、オプションを選択できます。

Fabrikam には、Microsoft Entra 多要素認証をサポートするのに十分な Microsoft Entra ID の Premium ライセンスが必要です。 その後、Contoso のユーザーはこの Fabrikam のライセンスを使用します。 B2B ライセンスの詳細については、「[Microsoft Entra 外部 ID の課金モデル](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing)」を参照してください。

注

MFA は、予測可能性を確保するために、リソース テナントで完了します。 ゲスト ユーザーがサインインすると、リソース テナントのサインイン ページが背景に、独自のホーム テナントのサインイン ページと会社のロゴが前景に表示されます。

##### B2B コラボレーション ユーザー向けの Microsoft Entra 多要素認証のリセット（追加の確認ステップ）

B2B コラボレーション ユーザーから MFA 登録を*証明*または要求するには、次の PowerShell コマンドレットを使用できます。

1. Microsoft Entra ID に接続します。

    ```powershell
    Connect-Entra -Scopes 'User.Read.All'
    ```
2. すべてのユーザーとその確認手法を取得します。

    ```powershell
    Get-EntraUser | where { $_.StrongAuthenticationMethods} | select userPrincipalName, @{n="Methods";e={($_.StrongAuthenticationMethods).MethodType}}
    ```
3. Microsoft Entra 多要素認証方法の設定をリセットし、特定のユーザーに証明方法の設定を再度行うことが要求されます。例:

    ```powershell
    Connect-Entra -Scopes 'UserAuthenticationMethod.ReadWrite.All'
    Reset-EntraStrongAuthenticationMethodByUpn -UserPrincipalName jmorgan_fabrikam.com#EXT#@woodgrovebank.onmicrosoft.com
    ```

#### 外部ユーザーの認証強度ポリシー

認証強度は、外部ユーザーがリソースへのアクセスで実施する必要がある多要素認証方法の特定の組み合わせを定義できる、条件付きアクセス制御です。 この制御は、組織内の機密性の高いアプリへの外部アクセスを制限する場合に特に役立ちます。外部ユーザーに対して、フィッシングに強い方法などの特定の認証方法を適用できるためです。

また、共同作業や接続を行うさまざまな種類のゲストまたは外部ユーザーに認証強度を適用することもできます。 つまり、B2B コラボレーション、B2B 直接接続、およびその他の外部アクセス シナリオに固有の認証強度要件を適用できます。

Microsoft Entra ID には、3 つの[組み込みの認証強度](https://aka.ms/b2b-auth-strengths)が用意されています。

- 多要素認証強度
- パスワードレス MFA 強度
- フィッシングに強い MFA 強度

これらの組み込み強度のいずれかを使用するか、必要な認証方法に基づいてカスタム認証強度ポリシーを作成できます。

注

現時点では、Microsoft Entra ID で認証する外部ユーザーにのみ認証強度ポリシーを適用できます。 メールのワンタイム パスコード、SAML/WS-Fed、Google フェデレーション ユーザーの場合は、MFA 許可コントロールを使用して MFA を要求します。

認証強度ポリシーを外部の Microsoft Entra ユーザーに適用すると、ポリシーはテナント間アクセス設定に含まれる [MFA 信頼設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#to-change-inbound-trust-settings-for-mfa-and-device-claims)と連携して、外部ユーザーが MFA を実行する必要がある場所と方法を決定します。 Microsoft Entra ユーザーは、最初にホーム Microsoft Entra テナントで自分のアカウントを使用して認証を行います。 その後、このユーザーがリソースへのアクセスを試みると、Microsoft Entra ID は認証強度の条件付きアクセス ポリシーを適用し、MFA 信頼が有効になっているかどうかを確認します。

外部ユーザー シナリオでは、ユーザーがホーム テナントとリソース テナントのどちらで MFA を完了しているかによって、認証強度を満たすために許容される認証方法が異なります。 次の表は、各テナントで許容される方法を示しています。 リソース テナントが外部の Microsoft Entra 組織からの要求を信頼することを選んでいる場合、リソース テナントは、MFA の履行のために [ホーム テナント] 列に一覧表示されている要求のみを受け入れます。 リソース テナントで MFA 信頼が無効になっている場合、外部ユーザーは、[リソース テナント] 列に一覧表示されているいずれかの方法を使って、リソース テナントで MFA を完了する必要があります。

###### 表 1 外部ユーザー向けの認証強度用MFA手法

| 認証方法 | ホームテナント | リソース テナント |
| --- | --- | --- |
| 第 2 の要素としての SMS | ✅ | ✅ |
| 音声通話 | ✅ | ✅ |
| Microsoft Authenticator プッシュ通知 | ✅ | ✅ |
| Microsoft Authenticator の電話によるサインイン | ✅ |  |
| OATH ソフトウェア トークン | ✅ | ✅ |
| OATH ハードウェア トークン | ✅ |  |
| FIDO2 セキュリティ キー | ✅ |  |
| Windows Hello for Business | ✅ |  |
| 証明書ベースの認証 | ✅ |  |

外部ユーザーまたはゲストに認証強度の要件を適用する条件付きアクセス ポリシーを構成するには、「[条件付きアクセス: 外部ユーザーの認証強度を要求する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-guests-mfa-strength)」を参照してください。

##### 外部 Microsoft Entra ユーザーのユーザー エクスペリエンス

認証強度ポリシーは、クロステナント アクセス設定の [MFA 信頼設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#to-change-inbound-trust-settings-for-mfa-and-device-claims)と連携して、外部ユーザーが MFA を実行する場所と方法を決定します。

Microsoft Entra ユーザーは、まず、ホーム テナントで自分のアカウントを使って認証を行います。 その後、このユーザーがリソースへのアクセスを試みると、Microsoft Entra ID は認証強度の条件付きアクセス ポリシーを適用し、MFA 信頼が有効になっているかどうかを確認します。

- **MFA 信頼が有効になっている場合**、Microsoft Entra ID は、ユーザーのホーム テナントで MFA が履行されたことを示す要求がないか、ユーザーの認証セッションを確認します (MFA の履行が外部ユーザーのホーム テナントで完了した場合に許容される認証方法については、「表 1」を参照してください)。ユーザーのホーム テナントで MFA ポリシーが既に満たされていることを示す要求がセッションに含まれていて、その方法が認証強度要件を満たしている場合、ユーザーはアクセスを許可されます。 それ以外の場合、Microsoft Entra ID は、受け入れ可能な認証方法を使用して、ホーム テナントで MFA を完了するチャレンジをユーザーに提示します。 ホーム テナントで MFA 方法を有効にし、ユーザーが登録できるようにする必要があります。
- **MFA 信頼が無効な場合**、Microsoft Entra ID は、受け入れ可能な認証方法を使用して、リソース テナントで MFA を完了するチャレンジをユーザーに提示します。 (外部ユーザーによる MFA フルフィルメントに許容される認証方法については、表 1 を参照してください。)

ユーザーが MFA を完了できない場合、または条件付きアクセス ポリシー (準拠しているデバイス ポリシーなど) が登録を妨げる場合、アクセスはブロックされます。

#### デバイス コンプライアンスと Microsoft Entra ハイブリッド参加済みデバイス ポリシー

組織は、条件付きアクセス ポリシーを使用して、ユーザーのデバイスを Microsoft Intune で管理するように要求できます。 このようなポリシーは、外部ユーザーがリソース組織にアンマネージド デバイスを登録できないため、外部ユーザー アクセスをブロックできます。 デバイスは、ユーザーのホーム テナントによってのみ管理できます。

ただし、デバイス信頼設定を使用すると、管理対象デバイスを必要としながらも外部ユーザーのブロックを解除できます。 テナント間アクセス設定では、ユーザーのデバイスがデバイス コンプライアンス ポリシーを満たしているか、または [Microsoft Entra ハイブリッド参加済み](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-alt-all-users-compliant-hybrid-or-mfa)であるかに関する、外部ユーザーのホーム テナントからの要求を信頼することを選択できます。 すべての Microsoft Entra 組織または個々の組織に対してデバイス信頼設定を設定できます。

デバイス信頼設定が有効になっている場合、Microsoft Entra ID はユーザーの認証セッションでデバイス要求を確認します。 ユーザーのホーム テナントでポリシーが既に満たされていることを示すデバイスの信頼性情報がセッションに含まれている場合、外部ユーザーには共有リソースへのシームレスなサインオンが付与されます。

重要

- 外部ユーザーのホーム テナントからのデバイス コンプライアンスまたは Microsoft Entra ハイブリッド参加済み状態に関する要求を信頼する場合を除き、外部ユーザーがマネージド デバイスを使用することを要求する条件付きアクセス ポリシーを適用することはお勧めしません。

#### デバイス フィルター

外部ユーザーの条件付きアクセス ポリシーを作成する場合は、Microsoft Entra ID に登録されているデバイスのデバイス属性に基づいてポリシーを評価できます。 *デバイスにフィルター*を使用することにより、サポートされている演算子とプロパティ、条件付きアクセスポリシーで使用可能なその他の割り当て条件を使用して、特定のデバイスを対象にすることができるようになりました。

デバイス フィルターは、テナント間アクセス設定と共に使用して、他の組織で管理されているデバイスの基本ポリシーに使用できます。 たとえば、特定のデバイス属性に基づいて、外部の Microsoft Entra テナントからデバイスをブロックするとします。 次の手順を実行して、デバイス属性ベースのポリシーを設定できます。

- テナント間のアクセス設定を構成し、その組織からのデバイス クレームを信頼するように設定します。
- フィルター処理に使用するデバイス属性を、[サポートされているデバイス拡張属性](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-condition-filters-for-devices#supported-operators-and-device-properties-for-filters)のいずれかに割り当てます。
- その属性を含むデバイスへのアクセスをブロックするデバイス フィルターを使用して条件付きアクセス ポリシーを作成します。

[条件付きアクセスを使用したデバイスのフィルター処理](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-condition-filters-for-devices)の詳細について説明します。

#### モバイル アプリケーション管理ポリシー

外部ユーザーに対してアプリ保護ポリシーを要求はお勧めしません。 **承認済みクライアント アプリを必須にするアプリ**、および**アプリの保護ポリシーを必須にするポリシー**などの条件付きアクセスの付与管理制御では、リソース テナントにデバイスを登録する必要があります。 これらの制御は、[iOS および Android デバイス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#device-platforms)にのみ適用できます。 ユーザーのデバイスはホーム テナントによってのみ管理できるため、これらのコントロールを外部ゲスト ユーザーに適用することはできません。

#### 場所ベースの条件付きアクセス

招待側組織でパートナー組織を定義する信頼された IP アドレス範囲を作成できる場合は、IP 範囲に基づく[場所ベースの条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#locations)を適用できます。

**地理的な場所**に基づいてポリシーを適用することもできます。

#### リスクベースの条件付きアクセス

外部ゲスト ユーザーが許可制御を満たしている場合は、[サインイン リスク ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#sign-in-risk)が適用されます。 たとえば、組織で、サインイン リスクが中または高のときに Microsoft Entra 多要素認証を要求できます。 ただし、ユーザーがリソース テナントで以前に Microsoft Entra 多要素認証に登録したことがない場合、そのユーザーはブロックされます。 これは、正当なユーザーのパスワードが侵害された場合に、悪意のあるユーザーが Microsoft Entra 多要素認証の独自の資格情報を登録するのを防ぐために行われます。

ただし、[ユーザー リスク ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#user-risk)はリソース テナントで解決できません。 たとえば、リスクの高い外部ゲスト ユーザーに対してパスワードの変更を要求した場合、リソース ディレクトリ内のパスワードをリセットできないため、そのユーザーはブロックされます。

#### 条件付きアクセスのクライアント アプリの条件

[クライアント アプリの条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#client-apps)は、B2B ゲスト ユーザーに対しても、他の種類のユーザーの場合と同じように動作します。 たとえば、ゲスト ユーザーがレガシ認証プロトコルを使用するのを防ぐことができます。

#### 条件付きアクセのセッション制御

[セッション制御](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-session)は、B2B ゲスト ユーザーに対しても、他の種類のユーザーの場合と同じように動作します。

### Microsoft Entra ID 保護とユーザー リスク ポリシー

Microsoft Entra ID 保護は、Microsoft Entra ユーザーの侵害された認証情報を検出し、侵害される可能性のあるユーザー アカウントを "リスクあり" とマークします。これにより、リソース テナントはユーザー リスク ポリシーを外部ユーザーに適用してリスクのあるサインインをブロックできます。外部ユーザーの場合、ユーザー リスクはそのユーザーのホーム ディレクトリで評価されます。 これらのユーザーのリアルタイムなサインイン リスクは、ユーザーがリソースにアクセスしようとしたときに、リソース ディレクトリで評価されます。 ただし、外部ユーザーの ID はそのユーザーのホーム ディレクトリに存在するため、以下の制限事項が適用されます。

- 外部ユーザーがパスワードのリセットを強制するよう ID 保護ユーザー リスク ポリシーをトリガーした場合、リソース組織内でパスワードをリセットできないため、そのトリガーはブロックされます。
- リスク評価は外部ユーザーのホーム ディレクトリで行われるため、リソース組織のリスクありユーザー レポートには外部ユーザーは反映されません。
- リソース組織内の管理者は、B2B ユーザーのホーム ディレクトリにアクセスできないため、危険な外部ユーザーを無視または修復することはできません。

組織のすべての外部ユーザーを含むグループを Microsoft Entra ID に作成することで、リスクベースのポリシーが外部ユーザーに影響を与えないようにすることができます。 次に、このグループをユーザー リスクおよびサインイン リスクベースの条件付きアクセス ポリシーの除外対象として追加します。

詳しくは、「[Microsoft Entra ID 保護と B2B ユーザーについて](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-b2b)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/b2b-direct-connect-overview"} -->
## B2B 直接接続 Microsoft Entra の概要 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/b2b-direct-connect-overview
- Service: entra-external-id / external
- Article date: 2026-04-24
- Summary: Microsoft Entra B2B 直接接続を使用すると、他の Microsoft Entra テナントのユーザーに、Teams 共有チャネルを介して共有リソースへのシームレスなサインイン手段を提供できます。 Microsoft Entra ディレクトリ内にゲスト ユーザー オブジェクトを用意する必要はありません。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

B2B 直接接続は、他の Microsoft Entra 組織との間に相互信頼関係をセットアップし、シームレスなコラボレーションを実現できる Microsoft Entra 外部 ID に備わっている機能です。 この機能は現在、Microsoft Teams共有チャネルで動作します。 B2B直接接続を使用すると、両方の組織のユーザーは、ホーム資格情報とTeamsの共有チャネルを使用して連携でき、互いの組織にゲストとして追加される必要はありません。 B2B 直接接続は、外部の Microsoft Entra 組織とリソースを共有する手段になります。 また、同じ組織に属する複数の Microsoft Entra テナント間でリソースを共有する手段にもなります。

[Image: B2B 直接接続を示す図。]

B2B 直接接続を使用するには、2 つの Microsoft Entra 組織間で相互に相手先リソースへのアクセスを可能にするために、相互の信頼関係を確立する必要があります。 リソース組織と外部組織は両方とも、クロステナント アクセス設定で B2B 直接接続を相互に有効にする必要があります。 信頼関係を確立すると、B2B 直接接続のユーザーが、ホーム Microsoft Entra 組織内の資格情報を使用して、自組織の外にあるリソースにシングル サインオン アクセスできるようになります。

現在、B2B直接接続機能はTeams共有チャネルで動作します。 2つの組織間でB2B直接接続が確立されると、1つの組織のユーザーがTeamsで共有チャネルを作成し、外部B2B直接接続ユーザーを招待できます。 このとき、B2B 直接接続ユーザーは Teams 内から、自身のホーム テナント Teams インスタンス内の共有チャネルにシームレスにアクセスでき、その際、共有チャネルをホストする組織に手動でサインインする必要はありません。

### B2B 直接接続のテナント間アクセスの管理

Microsoft Entra 組織では、受信と送信の[クロステナント アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)を定義することにより、他の Microsoft Entra 組織との信頼関係を管理できます。 クロステナント アクセス設定を使用すると、他の組織がお客様とコラボレーションを行う方法 (受信アクセス) と、お客様のユーザーが他の組織とコラボレーションする方法 (送信アクセス) をきめ細かく制御できます。

- **受信アクセス設定**は、外部の組織のユーザーがお客様の組織のリソースにアクセスできるかどうかを制御します。 これらの設定は、誰にでも適用できます。また、個々のユーザー、グループ、アプリケーションを指定することもできます。
- **送信アクセス設定**は、ユーザーが外部組織のリソースにアクセスできるかどうかを制御します。 これらの設定は、誰にでも適用できます。また、個々のユーザー、グループ、アプリケーションを指定することもできます。
- **テナント制限**は、ユーザーがデバイスとネットワークを使用しているが、外部組織によって発行されたアカウントを使用してサインインするときに外部組織にアクセスする方法を決定します。
- **信頼の設定**は、外部組織のユーザーがお客様のリソースにアクセスする場合に関し、お客様の条件付きアクセス ポリシーにおいて、その外部組織の多要素認証 (MFA)、準拠デバイス、Microsoft Entra ハイブリッド参加済みデバイス要求を信頼するかどうかを決定します。

重要

B2B 直接接続は、両方の組織で他方の組織との相互のアクセスが許可している場合にのみ可能になります。 たとえば、Contoso が Fabrikam からの受信 B2B 直接接続を許可することはできますが、Fabrikam でも Contoso との送信 B2B 直接接続を有効にするまで、共有はできません。 そのため、外部組織の管理者と連携して、クロステナント アクセス設定で共有が許可されるようにする必要があります。 B2B 直接接続では、B2B 直接接続を有効にしたユーザーのデータの共有の制限が有効になっているため、この相互の合意が重要となります。

#### 既定の設定

既定のクロステナント アクセス設定は、個別設定の構成対象となる組織を除くすべての外部 Microsoft Entra 組織に適用されます。 初期設定の Microsoft Entra ID は、すべての外部 Microsoft Entra テナントに対し、既定ですべての受信および送信 B2B 直接接続機能をブロックします。 これらの既定の設定は変更できますが、通常はそのままにして、個々の組織との B2B 直接接続アクセスを有効にすることができます。

#### 組織固有の設定

組織固有の設定を構成するには、組織を追加して、クロステナント アクセス設定を変更します。 これらの設定は、この組織の既定の設定よりも優先されます。

#### 例 1: Fabrikam との B2B 直接接続を許可し、他のすべてをブロックする

この例では、Contoso は、既定ですべての外部組織との B2B 直接接続をブロックしていますが、Fabrikam のすべてのユーザー、グループ、アプリに対して B2B 直接接続を許可するものとします。

[Image: 既定で B2B 直接接続をブロックするが 1 つの組織を許可する例。]

Contoso では、クロステナント アクセスに次の**既定の設定**を設定します。

- すべての外部ユーザーおよびグループに対して、B2B 直接接続への受信アクセスをブロックします。
- すべての Contoso ユーザーおよびグループに対して、B2B 直接接続への送信アクセスをブロックします。

次に Contoso が Fabrikam 組織を追加し、Fabrikam の次の**組織設定**を構成します。

- すべての Fabrikam ユーザーおよびグループに対して、B2B 直接接続への受信アクセスを許可します。
- Fabrikam B2B 直接接続ユーザーによるすべての内部 Contoso アプリケーションへの受信アクセスを許可します。
- すべての Contoso ユーザー、または選んだユーザーとグループに、B2B 直接接続を使った Fabrikam への送信アクセスを許可します。
- Contoso B2B 直接接続ユーザーに、すべての Fabrikam アプリケーションへの送信アクセスを許可します。

このシナリオがうまくいくには、Fabrikam は、Contoso と Fabrikam 自体のユーザーおよびアプリケーションとにこれらの同じクロステナント アクセス設定を構成することによって、Contoso との B2B 直接接続を許可する必要もあります。 構成が完了したら、Teams 共有チャネルを管理する Contoso ユーザーは、Fabrikam の完全なメール アドレスを検索して、Fabrikam ユーザーを追加できます。

#### 例 2: Fabrikam のマーケティング グループのみとの B2B 直接接続を有効にする

上記の例から始め、Contoso は、Fabrikam のマーケティング グループのみが B2B 直接接続を通じて Contoso のユーザーと共同作業できるようにすることもできました。 この場合、Contoso は Fabrikam からマーケティング グループのオブジェクト ID を取得する必要があります。 この場合、Fabrikam のすべてのユーザーへの受信アクセスを許可するのではなく、次のように Fabrikam 固有のアクセス設定を構成します。

- Fabrikam のマーケティング グループに対してのみ B2B 直接接続への受信アクセスを許可します。 Contoso は、[許可されたユーザーとグループ] の一覧で Fabrikam のマーケティング グループ オブジェクト ID を指定します。
- Fabrikam B2B 直接接続ユーザーによるすべての内部 Contoso アプリケーションへの受信アクセスを許可します。
- すべての Contoso ユーザーおよびグループに、B2B 直接接続を使用した Fabrikam への送信アクセスを許可します。
- Contoso B2B 直接接続ユーザーに、すべての Fabrikam アプリケーションへの送信アクセスを許可します。

Fabrikam は、マーケティング グループが B2B ダイレクト コネクトを通じて Contoso と共同作業できるように、クロステナントの発信アクセス設定を構成する必要があります。 構成が完了したら、Teams 共有チャネルを管理する Contoso ユーザーは、Fabrikam の完全なメール アドレスを検索して、Fabrikam マーケティング グループ ユーザーのみを追加できます。

### 認証

B2B 直接接続のシナリオにおける認証処理では、Microsoft Entra 組織 (ホーム テナント) のユーザーによる、別の Microsoft Entra 組織 (リソース テナント) のファイルやアプリへのサインイン試行を扱うことになります。 ユーザーは、ホーム テナントの Microsoft Entra から取得した資格情報でサインインします。 サインインの試行は、ユーザーのホーム テナントとリソース テナントの両方でのクロステナント アクセス設定に対して評価されます。 すべてのアクセス要件が満たされた場合、リソースにアクセスできるようにするトークンがユーザーに発行されます。 このトークンは 1 時間有効です。

条件付きアクセス ポリシーを使用したクロステナント シナリオでの認証の仕組みの詳細については、[クロステナント シナリオでの認証と条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access)に関するページを参照してください。

### 多要素認証 (MFA)

外部組織との B2B 直接接続を許可する必要があり、条件付きアクセス ポリシーで MFA が必要な場合は、条件付きアクセス ポリシーが外部組織からの MFA 要求を受け入れるように、受信の**信頼の設定**を構成する[*必要があります*](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-direct-connect#to-change-inbound-trust-settings-for-mfa-and-device-state)。 この構成により、外部組織からの B2B 直接接続ユーザーが条件付きアクセス ポリシーに準拠し、よりシームレスなユーザー エクスペリエンスがもたらされるようになります。

たとえば、Contoso (リソース テナント) が Fabrikam からの MFA 要求を信頼しているとします。 Contoso には、MFA を必要とする条件付きアクセス ポリシーがあります。 このポリシーのスコープは、すべてのゲスト、外部ユーザー、および SharePoint オンラインに設定されます。 B2B 直接接続の前提条件として、Contoso は、Fabrikam からの MFA 要求を受け入れるようにクロステナント アクセス設定の信頼設定を構成する必要があります。 Fabrikam ユーザーが B2B 直接接続対応アプリ (たとえば、Teams Connect 共有チャネル) にアクセスする場合、ユーザーは Contoso から適用される MFA 要件に従います。

- Fabrikam ユーザーは、既にホーム テナントで MFA を実行している場合、共有チャネル内のリソースにアクセスできます。
- Fabrikam ユーザーが MFA を完了していない場合は、リソースへのアクセスがブロックされます。

条件付きアクセスと Teams の詳細については、Microsoft Teams のドキュメントの[セキュリティとコンプライアンスの概要](https://learn.microsoft.com/ja-jp/microsoftteams/security-compliance-overview)に関するページを参照してください。

### デバイスコンプライアンスの信頼設定

クロステナント アクセス設定では、外部のユーザーによってそのユーザーのホーム テナントから送られた要求に対し、**信頼の設定**に基づいて、そのユーザーのデバイスがデバイス コンプライアンス ポリシーを満たしていること、または Microsoft Entra ハイブリッド参加済みであることの信頼を付与します。 デバイス信頼設定が有効になっている場合、Microsoft Entra ID はユーザーの認証セッションでデバイス要求を確認します。 ユーザーのホーム テナントでポリシーが既に満たされていることを示すデバイス要求がセッションに含まれている場合、外部ユーザーには共有リソースへのシームレスなサインオンが付与されます。 デバイス信頼設定は、すべての Microsoft Entra 組織に対して有効にすることも、個別の組織に対して有効にすることもできます。 ([詳細はこちら](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access#device-compliance-and-hybrid-azure-ad-joined-device-policies))

### B2B 直接接続のユーザー エクスペリエンス

現在、B2B 直接接続では、Teams Connect 共有チャネル機能が有効になっています。 B2B 直接接続ユーザーは、テナントの切り替えや別のアカウントでのサインインを行わなくても、外部組織の Teams 共有チャネルにアクセスできます。 B2B 直接接続ユーザーのアクセスは、共有チャネルのポリシーによって決定されます。

リソース組織では、Teams 共有チャネル所有者は、Teams 内で外部組織のユーザーを検索し、共有チャネルに追加することができます。 追加された B2B 直接接続ユーザーは、Teams のホーム インスタンス内から共有チャネルにアクセスでき、チャット、通話、ファイル共有、アプリ共有などの機能を使用して共同作業を行うことができます。 詳細については、「[Microsoft Teams のチームとチャネルの概要](https://learn.microsoft.com/ja-jp/microsoftteams/teams-channels-overview)」を参照してください。 B2B が Teams 共有チャネルを介してユーザーを直接接続するために使用できるリソース、ファイル、アプリケーションの詳細については、「Microsoft Teams の Chat、チーム、チャネル、アプリ」を参照してください。

#### ユーザー アクセスと管理

B2B 直接接続ユーザーは、2 つの組織間の相互接続を介して共同作業を行いますが、B2B コラボレーション ユーザーは組織に招待され、ユーザー オブジェクトを介して管理されます。

- B2B 直接接続では、両方の組織の管理者によって構成された相互の双方向接続を介して、別のMicrosoft Entra組織のユーザーと共同作業を行う方法が提供されます。 ユーザーは、B2B 直接接続対応の Microsoft アプリケーションにシングル サインオンでアクセスできます。 現在、B2B 直接接続では Teams Connect 共有チャネルがサポートされています。
- B2B コラボレーションを使用すると、外部パートナーを招待して、Microsoft、SaaS、またはカスタム開発されたアプリにアクセスさせることができます。 B2B コラボレーションは、外部パートナーが Microsoft Entra ID を使用していない場合や、B2B 直接接続をセットアップすることが不可能または非現実的な場合に、特に役立ちます。 B2B コラボレーションでは、外部ユーザーにとって好都合な ID (Microsoft Entra アカウント、コンシューマー Microsoft アカウント、管理者が有効にした Google などのソーシャル ID) によるサインインを許可できます。 B2B コラボレーションを使用すると、Microsoft アプリケーション、SaaS アプリ、カスタム開発アプリなどに外部ユーザーがサインインできるようにすることができます。

#### B2B 直接接続と B2B コラボレーションでの Teams の使用

Teams のコンテキストでは、B2B 直接接続と B2B コラボレーションのどちらを使用して誰かと共同作業をしているかにより、リソースを共有する方法に違いがあります。

- B2B 直接接続では、外部ユーザーをチーム内の共有チャネルに追加します。 このユーザーは共有チャネル内のリソースにアクセスできますが、チーム全体にも、共有チャネル外の他のリソースにもアクセスできません。 たとえば、Azure portal にアクセスできません。 ただし、マイ アプリ ポータルにはアクセスできます。 B2B 直接接続ユーザーは、Microsoft Entra 組織内にはアカウントが存在せず、共有チャネルの所有者によって Teams クライアント内で管理されます。 詳細については、「[Microsoft Teams でチーム所有者とメンバーを割り当てます](https://learn.microsoft.com/ja-jp/microsoftteams/assign-roles-permissions)」を参照してください。
- B2B コラボレーションを使用すると、ゲスト ユーザーをチームに招待できます。 B2B コラボレーション ゲスト ユーザーは、招待に使用された電子メール アドレスを使用してリソース テナントにサインインします。 アクセス権は、リソース テナント内のゲスト ユーザーに割り当てられたアクセス許可によって決まります。 ゲスト ユーザーは、チーム内の共有チャネルを表示することも、そこに参加することもできません。

B2B コラボレーションと B2B 直接接続の違いの詳細については、「[Microsoft Teams でのゲスト アクセス](https://learn.microsoft.com/ja-jp/microsoftteams/guest-access)」を参照してください。

### 監視と監査

B2B 直接接続アクティビティの監視と監査に関するレポートは、Azure portal と Microsoft Teams 管理センターの両方で入手できます。

#### Microsoft Entra の監視と監査ログ

Microsoft Entra ID は、クロステナント アクセスと B2B 直接接続に関する情報を、組織の監査ログとサインイン ログに記録します。 これらのログは、Azure potal の **[監視]** で確認できます。

- **Microsoft Entra 監査ログ**: Microsoft Entra 監査ログには、受信ポリシーと送信ポリシーが作成、更新、または削除された日時が記録されます。

    [Image: 監査ログを示すスクリーンショット。]
- **Microsoft Entra サインイン ログ**: Microsoft Entra サインイン ログは、ホーム組織とリソース組織の両方で使用できます。 B2B 直接接続が有効になると、サインイン ログに、他のテナントからの B2B 直接接続ユーザーのユーザー オブジェクト ID が含まれるようになります。 各組織で報告される情報は、たとえば次のように異なります。

    - どちらの組織でも、B2B 直接接続サインインには、B2B 直接接続のクロステナント アクセスの種類でラベルが付けられます。 サインイン イベントは、B2B 直接接続ユーザーが最初にリソース組織にアクセスしときに記録され、ユーザーに対して更新トークンが発行されたときに再び記録されます。 ユーザーは自分のサインイン ログにアクセスできます。 管理者は、組織全体のサインインを表示して、B2B 直接接続ユーザーがテナント内のリソースにアクセスしている方法を確認できます。
    - ホーム組織では、ログにクライアント アプリケーション情報が含まれます。
    - リソース組織ではログの [条件付きアクセス] タブに conditionalAccessPolicies が含まれます。

    [Image: サインイン ログを示すスクリーンショット。]
- **Microsoft Entra アクセス レビュー**: テナント管理者は、外部ユーザーに関する 1 回限りのアクセス レビューや定期的なアクセス レビューを構成し、外部ゲスト ユーザーによるアプリやリソースへのアクセスが必要以上の長時間にわたって行われるのを防ぐことができます。 [アクセス レビューの詳細について確認してください](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)。

#### Microsoft Teamsの監視ログおよび監査ログ

Microsoft Teams 管理センターには、チームごとの外部 B2B 直接接続メンバーを含む共有チャネルのレポートが表示されます。

- **Teams 監査ログ**: Teams では、共有チャネルをホストするテナントでの次の監査イベントがサポートされています: 共有チャネル ライフサイクル (チャネルの作成/削除)、テナント内/クロステナント メンバー ライフサイクル (メンバーの追加/削除/昇格/降格)。 これらの監査ログは、管理者が Teams 共有チャネルにアクセスできるユーザーを判断できるように、リソース テナントで使用できます。 外部ユーザーのホーム テナントには、外部共有チャネルでのアクティビティに関連する監査ログはありません。
- **Teams アクセス レビュー**: Teams であるグループのアクセス レビューでは、Teams 共有チャネルを使用している B2B 直接接続ユーザーを検出できるようになりました。 アクセス レビューを作成するときに、レビューのスコープを、共有チャネルに直接追加されたすべての内部ユーザー、ゲスト ユーザー、外部 B2B 直接接続ユーザーに設定できます。 その後、レビュー担当者には、共有チャネルに直接アクセスできるユーザーが表示されます。
- **現在の制限**: アクセス レビューでは、内部ユーザーと外部の B2B 直接接続ユーザーを検出できますが、共有チャネルに追加された他のチームは検出できません。 共有チャネルに追加されたチームを表示および削除するために、共有チャネル所有者は Teams 内からメンバーシップを管理できます。

Microsoft Teams 監査ログの詳細については、[Microsoft Teams 監査に関するドキュメント](https://learn.microsoft.com/ja-jp/purview/audit-teams-audit-log-events)を参照してください。

### プライバシーとデータの処理

B2B 直接接続を使用すると、ユーザーとグループは、外部組織によってホストされているアプリとリソースにアクセスできます。 接続を確立するには、外部組織の管理者も B2B 直接接続を有効にする必要があります。

外部組織との B2B 直接接続を有効にすると、送信設定を有効にした外部組織が、ユーザーに関する制限付き連絡先データにアクセスできるようになります。 Microsoft では、これらの組織とこのデータを共有して、ユーザーとの接続要求を送信できるようにします。 限定的な連絡先データを含む、外部組織によって収集されたデータは、それらの組織のプライバシー ポリシーとプラクティスに従います。

特定のターゲット リソース テナントを使用してポリシーを構成する場合、そのポリシーがテナントとターゲット リソース テナントの両方に格納されることを同意します。 これには、ターゲット リソース テナントがテナントとは異なるリージョンにある場合に、ポリシーが地理的リージョンの外部に保存されるという同意が含まれます。 既定のテナント間アクセス ポリシーを構成すると、そのポリシーが他のテナントからアクセスすることに同意します。 つまり、ポリシーは他のMicrosoft テナントに対する一般提供のために格納され、テナントの境界外に格納される可能性があります。 クロス geo レプリケート パターンに従って、妥当なパフォーマンスを持つ潜在的なコラボレーション パートナー テナントによるアクセスを確保します。 テナント間ポリシーまたは既定のポリシーを個別に実装できます。それぞれに、前述のポリシー共有条件が記載されています。

#### 外部アクセス

外部組織との B2B 直接接続を有効にすると、外部組織のユーザーは完全なメール アドレスでユーザーを検索できます。 検索結果が一致すると、名や姓などユーザーに関する限定的なデータが返されます。 ユーザーは、より多くのデータが共有される前に、外部組織のプライバシー ポリシーに同意する必要があります。 組織から提供され、ユーザーに提示されるプライバシー情報を確認することをお勧めします。

#### 受信アクセス

社内の従業員と外部のゲストがポリシーを確認できるように、グローバル プライバシー連絡先と組織のプライバシーに関する声明の両方を追加することを強くお勧めします。 手順に従って、[組織のプライバシー情報を追加](https://learn.microsoft.com/ja-jp/entra/fundamentals/properties-area)します。

#### ユーザーとグループへのアクセスの制限

クロステナント アクセス設定を使用して、組織内および外部組織内の特定のユーザーとグループへの B2B 直接接続を制限することを検討できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/b2b-fundamentals"} -->
## Microsoft Entra B2B のベスト プラクティスと推奨事項 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/b2b-fundamentals
- Service: entra-external-id / external
- Article date: 2025-05-07
- Summary: Microsoft Entra ID での企業間 (B2B) ゲスト ユーザー アクセスのベスト プラクティスと推奨事項について学習します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、Microsoft Entra 外部 ID での企業間 (B2B) コラボレーションに関するレコメンデーションとベストプラクティスを取り上げます。

重要

すべての新しいテナントと、明示的に無効にしていない既存のテナントに対して、[電子メール ワンタイム パスコード](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode)機能が既定で有効になりました。 この機能をオフにすると、フォールバック認証方法は、Microsoft アカウントの作成を招待者に求める方法です。

### B2B の推奨事項

| 推奨 | コメント |
| --- | --- |
| 外部パートナーとのコラボレーションをセキュリティで保護するには、Microsoft Entra ガイダンスを参照してください | 「[Microsoft Entra ID および Microsoft 365 での外部コラボレーションのセキュリティ保護](https://learn.microsoft.com/ja-jp/entra/architecture/secure-external-access-resources)」の推奨事項に沿って、組織の外部パートナーとのコラボレーションに対して、包括的なガバナンス アプローチをどのようにとるべきかを説明しています。 |
| テナント間のアクセスと外部コラボレーションの設定を慎重に計画する | Microsoft Entra 外部 ID により、外部ユーザーや組織とのコラボレーションを管理するための柔軟なコントロール セットが提供されます。 すべてのコラボレーションを許可またはブロックしたり、または特定の組織、ユーザー、アプリに対してのみコラボレーションを構成することができます。 テナント間アクセスと外部コラボレーションの設定を構成する前に、勤務していたり、パートナーである組織の慎重なインベントリを作成してください。 次に、他の Microsoft Entra テナントとの [B2B 直接接続](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-direct-connect-overview)または [B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)を有効にする必要があるかどうかを判断します。 |
| ゲスト ユーザーのディレクトリへのアクセスを制限する | 既定では、ゲスト ユーザーは Microsoft Entra ディレクトリへのアクセスが制限されています。 自身のプロファイルを管理し、他のユーザー、グループ、アプリに関する一部の情報を確認することはできます。 ゲストが自身のプロファイル情報のみを表示できるように、さらにアクセスを制限することができます。 詳細については、[既定のゲストのアクセス許可](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions)と、[外部コラボレーション設定](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)の構成方法に関する記事を参照してください。 |
| ゲストを招待できるユーザーを決定する | 既定では、組織内のすべてのユーザー (B2B コラボレーションのゲスト ユーザーを含む) が、B2B コラボレーションに外部ユーザーを招待できます。 招待を送信する機能を制限する場合は、[外部コラボレーション設定](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)を構成して、全員に対して招待をオンまたはオフにすることや、特定のロールへの招待を制限することができます。 |
| ネットワークやマネージド デバイスでの外部アカウントの使用方法を制御するには、テナント制限を使います。 | テナント制限を使うと、ユーザーが不明なテナントで作成したアカウントや、外部組織から受け取ったアカウントを使用できないようにすることができます。 このようなアカウントは禁止し、代わりに B2B コラボレーションを使うことをお勧めします。 |
| 最適なサインイン エクスペリエンスを実現するには、ID プロバイダーとフェデレーションする | 可能な限り、ID プロバイダーと直接フェデレーションすることで、招待されたユーザーが Microsoft アカウント (MSA) や Microsoft Entra アカウントを作成しなくても、共有するアプリやリソースにサインインできるようにします。 [Google フェデレーション機能](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation)を使用すると、B2B ゲスト ユーザーが自分の Google アカウントでサインインできるようにすることができます。 または、 [SAML/WS-Fed ID プロバイダー機能](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation) を使用して、ID プロバイダー (IdP) が SAML 2.0 または WS-Fed プロトコルをサポートしている任意の組織とのフェデレーションを設定できます。 |
| 他の手段で認証できない B2B ゲストに電子メール ワンタイム パスコード機能を使用する | [メールによるワンタイム パスコード](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode)機能では、B2B ゲスト ユーザーが Microsoft Entra ID、Microsoft アカウント (MSA)、Google フェデレーションなどの他の手段で認証できない場合に、ユーザーの認証を行います。 ゲスト ユーザーは、招待に応じるか、共有リソースにアクセスするときに、自分のメール アドレスに送信される一時的なコードを要求することができます。 その後は、このコードを入力してサインインを続けます。 |
| サインイン ページに会社のブランドを追加する | B2B ゲスト ユーザー向けに、より直感的になるようにサインイン ページをカスタマイズできます。 [サインイン ページとアクセス パネル ページに会社のブランドを追加する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding)方法に関するページを参照してください。 |
| B2B ゲスト ユーザーの利用エクスペリエンスにプライバシーに関する声明を追加する | 組織のプライバシーに関する声明の URL を最初の招待の利用プロセスに追加することができます。これにより、招待されたユーザーは続行するために、プライバシー条項に同意する必要があります。 [Microsoft Entra ID に組織のプライバシー ポリシーを追加する方法](https://learn.microsoft.com/ja-jp/entra/fundamentals/properties-area)に関する記事を参照してください。 |
| 一括招待 (プレビュー) 機能を使用して複数の B2B ゲスト ユーザーを同時に招待する | Azure portal の一括招待のプレビュー機能を使用して、複数のゲスト ユーザーを組織に同時に招待します。 この機能を使用すると、CSV ファイルをアップロードして B2B ゲスト ユーザーを作成し、招待状を一括で送信することができます。 [B2B ユーザーを一括招待するためのチュートリアル](https://learn.microsoft.com/ja-jp/entra/external-id/tutorial-bulk-invite)を参照してください。 |
| Microsoft Entra の多要素認証の条件付きアクセス ポリシーを適用します | パートナーの B2B ユーザーと共有するアプリに MFA ポリシーを適用することをお勧めします。 これにより、パートナー組織が MFA を使用しているかどうかに関係なく、テナント内のアプリに MFA が一貫して適用されます。 「[B2B コラボレーション ユーザーの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access)」を参照してください。 ある組織と緊密なビジネス関係があり、それらの MFA プラクティスを確認済みである場合は、それらの MFA 要求を受け入れるようにテナント間アクセス設定を構成できます ([詳細情報](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview#organizational-settings))。 |
| ゲストに対して認証強度の条件付きアクセス ポリシーを使用する | 認証強度は、外部の Microsoft Entra ユーザーがリソースにアクセスするために完了する必要がある多要素認証 (MFA) 方法の特定の組み合わせを定義できる条件付きアクセス制御です。 これは、テナント間アクセス設定の MFA 信頼設定と連携して、外部ユーザーが MFA を実行する場所と方法を決定します。 「[外部ユーザーの認証強度ポリシー](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access#authentication-strength-policies-for-external-users)」を参照してください |
| デバイスベースの条件付きアクセス ポリシーを適用している場合、除外リストを使用して B2B ユーザーへのアクセスを許可する | 組織でデバイスベースの条件付きアクセス ポリシーが有効になっている場合、B2B ゲスト ユーザーのデバイスは組織で管理されていないため、ブロックされます。 特定のパートナー ユーザーを含む除外リストを作成して、デバイスベースの条件付きアクセス ポリシーから除外することができます。 「[B2B コラボレーション ユーザーの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access)」を参照してください。 |
| B2B ゲスト ユーザーに直接リンクを提供するときにテナント固有の URL を使用する | 招待メールの代わりに、アプリまたはポータルへの直接リンクをゲストに提供することができます。 この直接リンクはテナント固有である必要があります。つまり、共有アプリが配置されているテナントでゲストを認証できるように、テナント ID または確認済みドメインが含まれている必要があります。 [ゲスト ユーザーの利用エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience)に関するページを参照してください。 |
| アプリの開発時に UserType を使用してゲスト ユーザーエクスペリエンスを決定する | アプリケーションを開発しているときに、テナント ユーザーとゲスト ユーザーに異なるエクスペリエンスを提供する場合は、UserType プロパティを使用します。 UserType 要求は、現在トークンに含まれていません。 アプリケーションで Microsoft Graph API を使用して、ディレクトリでユーザーを照会して、UserType を取得する必要があります。 |
| ユーザーと組織の関係が変更された場合*にのみ* UserType プロパティを変更する | PowerShell を使用してユーザーの UserType プロパティをメンバーからゲスト (またはその逆) に変換できますが、ユーザーと組織の関係が変更された場合にのみ、このプロパティを変更するようにしてください。 [B2B ゲスト ユーザーのプロパティ](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)に関するページを参照してください。 |
| 環境が Microsoft Entra ディレクトリの制限の影響を受けるかどうかを確認します | Microsoft Entra B2B には、Microsoft Entra サービス ディレクトリの制限適用されます。 ユーザーが作成できるディレクトリ数と、ユーザーまたはゲスト ユーザーが所属できるディレクトリ数の詳細については、「[Microsoft Entra サービスの制限と制約](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions)」を参照してください。 |
| スポンサー機能を使用して B2B アカウントのライフサイクルを管理する | スポンサーは、ゲスト ユーザーを担当するユーザーまたはグループです。 この新機能の詳細については、「[B2B ユーザーのスポンサー フィールド](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-sponsors)」を参照してください。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/b2b-government-national-clouds"} -->
## 米国政府機関および各国のクラウド Microsoft Entra B2B - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/b2b-government-national-clouds
- Service: entra-external-id / external
- Article date: 2025-07-07
- Summary: 米国政府機関および各国のクラウドの Microsoft Entra B2B collaboration で、どのような機能を使用できるかについて学習します

Microsoft Azure の[各国のクラウド](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud)は、物理的に分離された Azure のインスタンスです。 B2B コラボレーションは、国内のクラウド境界を越えて既定では有効になっていませんが、Microsoft クラウド設定を使用して、次の Microsoft Azure クラウド間の相互 B2B コラボレーションを確立できます。

- Microsoft Azure グローバル クラウドと Microsoft Azure Government
- Microsoft Azure グローバル クラウドと Microsoft Azure China (21Vianet が運用)

Microsoft クラウド間で複数のテナントを持つ組織は、 [クロスクラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview) を使用して、B2B ユーザーの作成、更新、削除を自動化できます。

### Microsoft クラウド間の B2B コラボレーション

異なるクラウド内のテナント間で B2B コラボレーションを設定するには、両方のテナントで Microsoft クラウド設定を構成して、他のクラウドとのコラボレーションを有効にする必要があります。 その後、各テナントは、もう一方のクラウド内のテナントとの間で、受信と送信のテナント間 アクセスを構成する必要があります。 詳細については、[Microsoft クラウドの設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-cloud-settings)に関するページを参照してください。

### Microsoft Azure Government クラウド内の B2B コラボレーション

Azure US Government クラウド内では、次の場所にあるテナント間で B2B Collaboration が有効になります。

- どちらのテナントも、Azure US Government クラウド、* および * 内にあります
- どちらのテナントも B2B Collaboration をサポートしています。

B2B collaboration をサポートする Azure US Government テナントは、次を使用してソーシャル ユーザーと共同作業を行うこともできます。

- Microsoft アカウント
- Google アカウント
- 電子メール ワンタイム パスコード アカウント

これらのグループ外のユーザー (たとえばユーザーが Azure US Government クラウドの一部ではないテナント、または B2B コラボレーションをまだサポートしていないテナントにいる場合) を招待すると、その招待は失敗するか、またはその招待を履行できなくなります。

Microsoft アカウントの場合、Microsoft Entra 管理センターへのアクセスには既知の制限があります。

- 新しく招待された MSA ゲストは、Microsoft Entra 管理センターへの直接リンクを利用できません
- 既存の MSA ゲストは、Microsoft Entra 管理センターにサインインできません。

その他の制限事項の詳細については、「[Microsoft Entra ID P1 と P2 のバリエーション](https://learn.microsoft.com/ja-jp/azure/azure-government/compare-azure-government-global-azure#azure-active-directory-premium-p1-and-p2)」を参照してください。

#### B2B コラボレーションが Azure US Government テナントで利用可能かどうかを確認するにはどうすればよいですか？

Azure US Government クラウド テナントが B2B コラボレーションをサポートしているかどうかを確認するには、次の手順を実行します。

1. ブラウザーで次の URL にアクセスし、*&lt;tenantname&gt;*のテナント名に置き換えます。

    `https://login.microsoftonline.com/<tenantname>/v2.0/.well-known/openid-configuration`
2. JSON の応答で `"tenant_region_scope"` を検索します。

    - `"tenant_region_scope":"USGOV”` が表示される場合は、B2B がサポートされています。
    - `"tenant_region_scope":"USG"` が表示される場合、B2B はサポートされていません。

### 21Vianet が運用する Microsoft Azure の B2B Collaboration

21Vianet が運営する Microsoft Azure では、B2B Collaboration 用に次の ID プロバイダーがサポートされています。

- Microsoft Entra ID
- SAML/WS-Fed

21Vianet が運営する Microsoft Azure の詳細については、「[サービスの可用性とロードマップ](https://learn.microsoft.com/ja-jp/azure/china/concepts-service-availability)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/b2b-quickstart-add-guest-users-portal"} -->
## クイック スタート: ゲスト ユーザーを追加して招待を送信する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/b2b-quickstart-add-guest-users-portal
- Service: entra-external-id / external
- Article date: 2026-04-24
- Summary: このクイックスタートでは、Microsoft Entra 管理者が Microsoft Entra 管理センター で B2B ゲスト ユーザーを追加する方法と B2B 招待ワークフローについて説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra [B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)を使用すると、自分の職場、学校、またはソーシャル アカウントを使用して、組織と共同作業するユーザーを招待できます。

このクイックスタートでは、Microsoft Entra 管理センター の Microsoft Entra ディレクトリに新しいゲスト ユーザーを追加する方法について説明します。 また、招待状を送信し、ゲスト ユーザーの招待の受諾プロセスがどのようになるかを確認します。

このガイドでは、外部ユーザーを招待するための基本的な手順について説明します。 外部ユーザーを招待するときに含めることができるすべてのプロパティと設定については、「ユーザーを [作成および削除する方法](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)」を参照してください。

Azure サブスクリプションをお持ちでない場合は、開始する前に [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を作成してください。

注

onmicrosoft の既定のドメインから送信された B2B 招待メールには、Exchange Onlineの送信制限が適用されます。 詳細については、 [電子メールを送信するための onmicrosoft ドメインの使用の制限](https://learn.microsoft.com/ja-jp/office365/servicedescriptions/exchange-online-service-description/exchange-online-limits#sending-limits) を参照してください。 より高い制限が必要な場合は、カスタム ドメインへの更新を検討してください。 詳細については、「 [カスタム ドメイン名をテナントに追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)。

### 前提条件

このクイック スタートのシナリオを完了するための要件を次に示します。

- 少なくとも [ゲスト招待者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#guest-inviter) ロールやユーザー管理者など、テナント ディレクトリに [ユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)を作成できるロール。
- 別の職場、学校、ソーシャル メール アドレスなど、Microsoft Entra テナント外の有効なメール アドレスへのアクセス。 このメールを使用して、テナント ディレクトリにゲスト アカウントを作成し、招待にアクセスします。

### 外部ゲスト ユーザーを招待する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。

    [Image: [すべてのユーザー] ページのスクリーンショット。]
3. メニューから [ **外部ユーザーの招待** ] を選択します。

    [Image: [外部ユーザーの招待] メニュー オプションのスクリーンショット。]

#### 外部ユーザーについての基本事項

このセクションでは、"ゲストのメール アドレス" を使ってゲストをテナントに招待します。 このクイックスタートでは、アクセスできるメール アドレスを入力します。

- **電子メール**: 招待するゲスト ユーザーのメール アドレスを入力します。
- **表示名: 表示**名を指定します。
- **招待メッセージ**: **招待メッセージを送信するには、[招待メッセージ** の送信] チェック ボックスをオンにします。 このチェック ボックスをオンにすると、カスタマイズされた短いメッセージと別の CC 受信者を設定することもできます。

[Image: 外部ユーザーの招待の [基本] タブのスクリーンショット。]

[ **確認と招待** ] ボタンを選択してプロセスを完了します。

#### 確認と招待

最後のタブでは、ユーザー作成プロセスからいくつかの重要な詳細がキャプチャされます。 詳細を確認し、すべてが適切な場合は [ **招待** ] ボタンを選択します。

招待メールが自動的に送信されます。

1. 招待を送信すると、ユーザー アカウントがディレクトリにゲストとして自動的に追加されます。

    [Image: ディレクトリ内の新しいゲスト ユーザーを示すスクリーンショット。]

### 招待を承認する

次に、ゲスト ユーザーとしてサインインして、招待を確認します。

1. テスト用のゲスト ユーザーの電子メール アカウントにサインインします。
2. 受信トレイで、"Microsoft Invitations (Contoso の代理)" からのメールを開きます。

    [Image: B2B 招待メールを示すスクリーンショット。]
3. 電子メールの本文で、[ **招待を承諾する**] を選択します。 **によって要求された許可**ページがブラウザーで開きます。

    [Image: [アクセス許可の確認] ページを示すスクリーンショット。]
4. [ **承諾]** を選択します。
5. [ **マイ アプリ]** ページが開きます。 このゲスト ユーザーにアプリを割り当てていないため、"表示するアプリがありません" というメッセージが表示されます。実際のシナリオでは、アプリがここに表示されるように [、ゲスト ユーザーをアプリに追加](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator#add-guest-users-to-an-application) します。

### リソースをクリーンアップする

不要になったら、テスト用のゲスト ユーザーを削除します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**ユーザー**&gt;**ユーザー設定**に移動します。
3. テスト ユーザーを選択し、[ユーザーの **削除**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/b2b-quickstart-invite-powershell"} -->
## クイック スタート: PowerShell を使用してゲスト ユーザーを追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/b2b-quickstart-invite-powershell
- Service: entra-external-id / external
- Article date: 2026-04-17
- Summary: このクイック スタートでは、PowerShell を使用して、Microsoft Entra B2B コラボレーション ユーザーに招待を送信する方法について説明します。 Microsoft Graph ID サインインと Microsoft Graph Users PowerShell モジュールを使用します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra B2B コラボレーションを使用して、外部パートナーをアプリやサービスに招待する方法は多数あります。 前のクイック スタートでは、ゲスト ユーザーをMicrosoft Entra 管理センターに直接追加する方法について説明しました。 ゲスト ユーザーは、PowerShell を使用して、一度に 1 人づつ、または一括で追加することもできます。 このクイック スタートでは、`New-MgInvitation` コマンドを使用して、Microsoft Entra テナントに 1 人のゲスト ユーザーを追加します。

この記事では、Microsoft Graph PowerShell を使用してゲスト ユーザーを招待する方法について説明します。 [Microsoft Entra PowerShell](https://learn.microsoft.com/ja-jp/powershell/entra-powershell/manage-guest-users) を使用してゲスト ユーザーを管理することもできます。

### 前提条件

このクイック スタートのシナリオを完了するための要件を次に示します。

- Azure サブスクリプション。 Azure サブスクリプションがない場合は、開始する前に [free アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成します。
- テナント ディレクトリでユーザーを作成できるロール ([ゲスト招待者ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#guest-inviter)や[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)など) が必要です。
- [Microsoft Graph Identity Sign-ins モジュール](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/?viewFallbackFrom=graph-powershell-beta&preserve-view=true&view=graph-powershell-1.0) (Microsoft.Graph.Identity.SignIns) と [Microsoft Graph Users モジュール](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/?viewFallbackFrom=graph-powershell-beta&preserve-view=true&view=graph-powershell-1.0) (Microsoft.Graph.Users) をインストールします。 `#Requires` ステートメントを使用して、必要な PowerShell モジュールが満たされない限り、スクリプトが実行されないようにできます。

```powershell
#Requires -Modules Microsoft.Graph.Identity.SignIns, Microsoft.Graph.Users
```

- テスト用の電子メール アカウントを取得します。 招待状の送信先となるテスト用の電子メール アカウントが必要です。 このアカウントは、組織外にある必要があります。 Gmail.com や Outlook.com アドレスなどのソーシャル アカウントを含め、任意の種類のアカウントを使用できます。

注

この記事では、Microsoft Graph PowerShell を使用します。これは、廃止された Azure AD モジュールと MSOnline PowerShell モジュールを置き換えます。

### テナントにサインインする

次のコマンドを実行して、テナントに接続します。

```powershell
Connect-MgGraph -Scopes 'User.Invite.All','User.Read.All'
```

メッセージが表示されたら、資格情報を入力します。

### 招待状を送信する

1. テスト用の電子メール アカウントに招待状を送信するには、次の PowerShell コマンドを実行します (**"Henry Ross"** と **henry@contoso.com** をテスト用の電子メール アカウント名と電子メール アドレスに置き換えます)。

    ```powershell
    New-MgInvitation -InvitedUserDisplayName "Henry Ross" -InvitedUserEmailAddress henry@contoso.com -InviteRedirectUrl "https://myapplications.microsoft.com" -SendInvitationMessage:$true
    ```
2. このコマンドは、指定した電子メール アドレスに招待状を送信します。 出力をチェックします。出力は次の例のようになります。

    ```Output
    Id                                   InviteRedeemUrl                                                                                                   
    --                                   ---------------                                                                                                   
    00aa00aa-bb11-cc22-dd33-44ee44ee44ee https://login.microsoftonline.com/redeem?...
    ```

### ユーザーがディレクトリ内に存在することを確認する

1. 招待されたユーザーがMicrosoft Entra IDに追加されたことを確認するには、次のコマンドを実行します (**henry@contoso.com** を招待されたメールに置き換えます)。

    ```powershell
    Get-MgUser -Filter "Mail eq 'henry@contoso.com'"
    ```
2. 出力をチェックして、招待したユーザーが表示されていることを確認します。*emailaddress*#EXT#@*domain* 形式のユーザー プリンシパル名 (UPN) になっています。 たとえば、*henry\_contoso.com#EXT#@fabrikam.onmicrosoft.com* では、fabrikam.onmicrosoft.com が招待状を送信した組織になります。

    ```Output
    Id                                   DisplayName              Mail                           UserPrincipalName        
    --                                   -----------              ----                           -----------------               
    00aa00aa-bb11-cc22-dd33-44ee44ee44ee Henry Ross               henry@contoso.com              henry_contoso.com#EXT#@fabrikam.onmicrosoft.com
    ```

### リソースをクリーンアップする

不要になったら、ディレクトリ内のテスト用のユーザー アカウントを削除できます。 ユーザー アカウントを削除するには、次のコマンドを使用します。

```powershell
Remove-MgUser -UserId '<String>'
```

例えば次が挙げられます。

```powershell
Remove-MgUser -UserId 'henry_contoso.com#EXT#@fabrikam.onmicrosoft.com'
```

または

```powershell
Remove-MgUser -UserId '00aa00aa-bb11-cc22-dd33-44ee44ee44ee'
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/b2b-sponsors"} -->
## Microsoft Entra 管理センターでゲスト ユーザーにスポンサーを追加する - 外部 ID - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/b2b-sponsors
- Service: entra-external-id / external
- Article date: 2026-04-24
- Summary: 管理者が Microsoft Entra B2B コラボレーションでゲスト ユーザーにスポンサーを追加する方法を紹介します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

スポンサー機能は、ディレクトリ内の B2B ユーザーを管理するのに役立ちます。 これにより、各ゲスト ユーザーの責任者を追跡できます。 [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)では特定のドメインのゲストを追跡できますが、これらの領域以外のゲストは含まれません。 スポンサー機能を使用すると、各ゲスト ユーザーにユーザーまたはグループを割り当てることができます。 これは、招待したユーザーを追跡し、アカウンタビリティをサポートするのに役立ちます。

この記事では、スポンサー機能の概要と、B2B シナリオでの使用方法について説明します。

### ユーザー オブジェクトのスポンサー フィールド

ユーザー オブジェクト **の [スポンサー** ] フィールドは、ユーザーのライフサイクルを管理および監視するユーザーまたはグループを参照し、適切なリソースにアクセスできるようにします。 スポンサーは、スポンサー ユーザーまたはグループの管理権限を付与しませんが、エンタイトルメント管理の承認プロセスに使用できます。 カスタム ソリューションにも使用できますが、他の組み込みのディレクトリ機能は提供されません。

[Image: [スポンサー] フィールドに 1 人のスポンサーが表示されているゲスト ユーザー プロファイルのスクリーンショット。]

### スポンサーになれるのは誰ですか？

ゲスト ユーザーを招待すると、招待プロセス中に他のユーザーを指定しない限り、自動的にスポンサーになります。 ユーザー名は、ユーザー オブジェクトの **[スポンサー** ] フィールドに自動的に追加されます。 ゲスト ユーザーを招待するときに、別のスポンサー、ユーザー、またはグループを指定することもできます。 スポンサーが組織を離れた場合、テナント管理者はオフボード中に **[スポンサー** ] フィールドを別のユーザーまたはグループに変更できます。 この切り替えにより、ゲスト ユーザーのアカウントが適切に追跡されます。

### B2B スポンサー機能を使用するその他のシナリオ

Microsoft Entra B2B コラボレーション スポンサー機能は、外部パートナーに完全なガバナンス ライフサイクルを提供することを目的とする他のシナリオの基盤として機能します。 これらのシナリオはスポンサー機能の一部ではありませんが、ゲスト ユーザーを管理するため依存しています。

- 管理者は、ゲスト ユーザーが別のプロジェクトで作業を開始した場合に、スポンサーシップを別のユーザーまたはグループに移行できます。
- 新しいアクセス パッケージを要求するときに、スポンサーをエンタイトルメント管理の承認者として追加して、レビュー担当者のワークロードを減らすことができます。

### 新しいゲスト ユーザーを招待するときにスポンサーを追加する

新しいゲスト ユーザーを招待するときに、最大 5 人のスポンサーを追加できます。 スポンサーを指定しない場合、招待者がスポンサーとして追加されます。 ゲスト ユーザーを招待するには、少なくとも [ゲスト招待者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#guest-inviter) または [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) ロールが必要です。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. メニューから [ **新しいユーザー**&gt;**外部ユーザー** を招待する] を選択します。
4. [ **基本** ] タブに詳細を入力し、[ **次へ: プロパティ**] を選択します。
5. スポンサーは、[**プロパティ**] タブの [**ジョブ情報**] で追加できます。

    [Image: [プロパティ] タブの [ジョブ情報] の下にスポンサーを追加する場所を示す招待外部ユーザー フローのスクリーンショット。]
6. [ **確認と招待** ] ボタンを選択してプロセスを完了します。

新しいゲスト ユーザーの招待マネージャーを使用して、ペイロードに含めることで、Microsoft Graph API でスポンサーを追加することもできます。 ペイロードにスポンサーが存在しない場合、招待元はスポンサーとしてマークされます。 詳細については、「スポンサーの [割り当て](https://learn.microsoft.com/ja-jp/graph/api/user-post-sponsors)」を参照してください。

注

現在、外部ユーザーが SharePoint 経由で招待されると (既存ではない外部ユーザーとファイルを共有するときなど)、その外部ユーザーにはスポンサーが追加されません。 これは既知の問題です。 現時点では、上記の手順に従ってスポンサーを手動で追加できます。

### Microsoft Entra 管理センターの [スポンサー] フィールドを編集する

ゲスト ユーザーを招待すると、既定でスポンサーになります。 ゲスト ユーザーのスポンサーを手動で変更する必要がある場合は、次の手順に従ってください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 一覧でユーザーの名前を選択して、ユーザー プロファイルを開きます。
4. [**プロパティ**]&gt;**[ジョブ情報]**で**[スポンサー**]フィールドを確認します。 ゲスト ユーザーが既にスポンサーを持っている場合は、[ **表示** ] を選択してスポンサーの名前を表示できます。

    [Image: ジョブ情報の下の [スポンサー] フィールドのスクリーンショット。]
5. [ **スポンサー** ] フィールドを編集する場合は、スポンサー名の一覧でウィンドウを閉じます。
6. **[スポンサー**] フィールドを編集するには、2 つの方法があります。 **ジョブ情報**の横にある鉛筆アイコンを選択するか、ページの上部にある **[プロパティの編集**] を選択して [**ジョブ情報**] タブに移動します。
7. ユーザーのスポンサーが 1 人のみの場合、スポンサーの名前を確認できます。 ユーザーが複数のスポンサーを持っている場合、個々の名前は表示されません。

    [Image: 複数のスポンサー オプションのスクリーンショット。]
8. スポンサーを追加または削除するには、[**編集]** を選択し、ユーザーまたはグループを選択または削除し、[**ジョブ情報**] タブで **[保存**] を選択します。
9. ゲスト ユーザーにスポンサーがない場合は、[ **スポンサーの追加]** を選択します。

    [Image: 既存のユーザーにスポンサーを追加するスクリーンショット。]
10. スポンサー ユーザーまたはグループを選択したら、[ **ジョブ情報** ] タブで変更を保存します。

### PowerShell を使用して [スポンサー] フィールドを編集する

**Microsoft Identity Tools モジュール**の [Update-MsIdInvitedUserSponsorsFromInvitedBy](https://azuread.github.io/MSIdentityTools/commands/Update-MsIdInvitedUserSponsorsFromInvitedBy) PowerShell スクリプトを使用して、既存のすべてのユーザーの[スポンサー](https://azuread.github.io/MSIdentityTools) フィールドを管理できます。 このスクリプトは、 `InvitedBy` プロパティを使用してテナントに最初に招待したユーザーを含むようにスポンサー属性を更新します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/b2b-tutorial-require-mfa"} -->
## チュートリアル: B2B の多要素認証 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/b2b-tutorial-require-mfa
- Service: entra-external-id / external
- Article date: 2026-04-24
- Summary: このチュートリアルでは、Microsoft Entra B2B を使って外部ユーザーやパートナー組織とコラボレーションするときに多要素認証を要求する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

外部 B2B ゲスト ユーザーと共同作業する場合は、多要素認証ポリシーを使用してアプリを保護します。 外部ユーザーは、リソースにアクセスするためにユーザー名とパスワード以上のものが必要です。 Microsoft Entra ID では、アクセスで MFA を要求する条件付きアクセス ポリシーを使ってこの目標を達成できます。 自分の組織のメンバーと同様に、テナント、アプリ、または個々のゲスト ユーザー レベルで MFA ポリシーを適用できます。 リソース テナントは、ゲスト ユーザーの組織に多要素認証機能がある場合でも、ユーザーに対する Microsoft Entra 多要素認証を担当します。

例:

[Image: 会社のアプリにサインインしているゲスト ユーザーを示す図。]

1. 会社 A の管理者または従業員は、アクセスで MFA を要求するように構成されたクラウドまたはオンプレミスのアプリケーションを使用するようにゲスト ユーザーを招待します。
2. ゲスト ユーザーは、自分の職場、学校、またはソーシャルの ID を使用してサインインします。
3. ユーザーは、MFA チャレンジを完了するように求められます。
4. ユーザーは、会社 A で MFA を設定し、自分の MFA オプションを選択します。 ユーザーは、アプリケーションへのアクセスを許可されます。

注

Microsoft Entra多要素認証は、予測可能性を確保するためにリソース テナントによって実行されます。 ゲスト ユーザーがサインインすると、リソース テナントのサインイン ページがバックグラウンドで表示され、独自のホーム テナント サインイン ページと会社のロゴがフォアグラウンドに表示されます。

このチュートリアルでは、次のことについて説明します。

- MFA を設定する前に、サインイン エクスペリエンスをテストします。
- 環境内のクラウド アプリへのアクセスで MFA を要求する条件付きアクセス ポリシーを作成します。 このチュートリアルでは、Azure Resource Manager アプリを使用してプロセスを説明します。
- What If ツールを使用して MFA サインインをシミュレートします。
- 条件付きアクセス ポリシーをテストします。
- テスト ユーザーとポリシーをクリーンアップします。

Azure サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を作成して開始してください。

### 前提条件

このチュートリアルのシナリオを完了するための要件を次に示します。

- **Microsoft Entra ID P1 または P2 エディションへのアクセス**。これには条件付きアクセス ポリシー機能が含まれます。 MFA を適用するには、Microsoft Entra 条件付きアクセス ポリシーを作成します。 パートナーに MFA 機能がない場合でも、MFA ポリシーは常に組織に適用されます。
- **有効な外部電子メール アカウント**。ゲスト ユーザーとしてテナント ディレクトリに追加し、サインインするために使用できます。 ゲスト アカウントを作成する方法がわからない場合は、「 [Microsoft Entra 管理センターで B2B ゲスト ユーザーを追加](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)する」の手順に従います。

### Microsoft Entra ID でテスト ゲスト ユーザーを作成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. [ **新しいユーザー** ] を選択し、[ **外部ユーザーの招待**] を選択します。

    [Image: 新しいゲスト ユーザー オプションを選択する場所のスクリーンショット。]
4. **[Basic]** タブの **[ID]** に、外部ユーザーのメール アドレスを入力します。 必要に応じて、表示名とウェルカム メッセージを含めることができます。

    [Image: ゲスト メールを入力する場所のスクリーンショット。]
5. 必要に応じて、[ **プロパティ** ] タブと [ **割り当て]** タブでユーザーにさらに詳細を追加できます。
6. **[レビュー + 招待]** を選択して、招待をゲスト ユーザーに自動的に送信します。 **ユーザーが正常に招待された**ことを示すメッセージが表示されます。
7. 招待を送信すると、ユーザー アカウントがゲストとしてディレクトリに追加されます。

### MFA を設定する前にサインイン エクスペリエンスをテストする

1. テスト ユーザー名とパスワードを使用して [、Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. サインイン資格情報のみを使用して Microsoft Entra 管理センターにアクセスします。 他の認証は必要ありません。
3. Microsoft Entra 管理センターからサインアウトします。

### MFA を要求する条件付きアクセス ポリシーを作成する

1. [条件付きアクセス管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)にサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Policies** に移動します。
3. **[新しいポリシー]** を選択します。
4. **B2B ポータルアクセスに MFA を要求するなどのポリシーに名前を付けます**。 名前付けポリシーのわかりやすい標準を作成します。
5. **[割り当て]** で、 **[ユーザーまたはワークロード ID]** を選択します。

    1. **[含める]** で、**[ユーザーとグループを選択]** をオンにし、**[ゲストまたは外部ユーザー]** をオンにします。 ポリシーは、さまざまな[外部ユーザーの種類](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access#assign-conditional-access-policies-to-external-user-types)、組み込みのディレクトリ ロール、またはユーザーとグループに割り当てることができます。

    [Image: すべてのゲスト ユーザーを選択する方法を示すスクリーンショット。]
6. [**ターゲット リソース**&gt;**リソース (旧称クラウド アプリ)**&gt;**を含める**&gt;**リソースの選択**で、**Azure Resource Manager**を選択し、リソースを**選択**します。]

    [Image: [クラウド アプリ] ページと [選択] オプションを示すスクリーンショット。]
7. **[アクセス制御]**&gt;**[許可]** で、**[アクセス権の付与]**、**[多要素認証を要求する]** の順に選択し、**[選択する]** を選択します。

    [Image: 多要素認証を必要とするオプションを示すスクリーンショット。]
8. **[ポリシーの有効化]** で、 **[オン]** を選択します。
9. **[作成]** を選択します。

### What If オプションを使用してサインインをシミュレートする

**条件付きアクセス What If ポリシー ツール**は、環境内の条件付きアクセス ポリシーの効果を理解するのに役立ちます。 複数のサインインを使用してポリシーを手動でテストする代わりに、このツールを使用してユーザーのサインインをシミュレートできます。 シミュレーションでは、このサインインがポリシーに与える影響を予測し、レポートを生成します。 詳細については、「 [What If ツールを使用して条件付きアクセス ポリシーを理解する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/what-if-tool)」を参照してください。

### 条件付きアクセス ポリシーをテストする

1. テスト ユーザー名とパスワードを使用して、[Microsoft Entra管理センター](https://entra.microsoft.com) にサインインします。
2. 他の認証方法を要求するメッセージが表示されます。 ポリシーが有効になるまで少々時間がかかる場合があります。

    [Image: [詳細情報が必要です] メッセージのスクリーンショット。]

    注

    Microsoft Entra ホーム テナントから MFA を信頼するように [テナント間アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview) を構成することもできます。 これにより、外部の Microsoft Entra ユーザーは、リソース テナントに登録するのではなく、自分のテナントに登録した MFA を使用できるようになります。
3. サインアウトします。

### リソースをクリーンアップする

不要になったら、テスト ユーザーとテスト用の条件付きアクセス ポリシーを削除します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. テスト ユーザーを選択し、 **[ユーザーの削除]** を選択します。
4. [条件付きアクセス管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)にサインインします。
5. **Entra ID**&gt;**Conditional Access**&gt;**Policies** に移動します。
6. **ポリシー名**の一覧で、テスト ポリシーのコンテキスト メニュー (...) を選択し、[**削除**] を選択し、[**はい**] を選択して確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/bulk-invite-powershell"} -->
## B2B コラボレーション ユーザーを一括で招待するためのチュートリアル - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/bulk-invite-powershell
- Service: entra-external-id / external
- Article date: 2025-03-13
- Summary: このチュートリアルでは、PowerShell と CSV ファイルを使用して、外部の Microsoft Entra B2B コラボレーション ゲスト ユーザーに招待状を一括送信する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra B2B コラボレーションを使用して外部パートナーと連携する場合は、ポータルまたは PowerShell を使用して複数のゲスト ユーザーを同時に組織に招待できます。 このチュートリアルでは、PowerShell を使用して、外部ユーザーに招待状を一括送信する方法について説明します。 具体的には、以下を実行します。

- ユーザー情報を含むコンマ区切り値 (.csv) ファイルを準備する
- PowerShell スクリプトを実行して招待状を送信する
- ユーザーがディレクトリに追加されていることを確認する

Azure サブスクリプションをお持ちでない場合は、開始する前に [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を作成してください。

### 前提条件

#### 最新の Microsoft Graph PowerShell モジュールをインストールする

最新バージョンの Microsoft Graph PowerShell モジュールをインストールしてください。

最初に、インストールされているモジュールを調べます。 管理者特権のユーザーとして PowerShell を開き (管理者として実行)、次のコマンドを実行します。

```powershell
Get-InstalledModule Microsoft.Graph
```

PowerShell Core または Windows PowerShell に SDK の v1 モジュールをインストールするには、次のコマンドを実行します。

```powershell
Install-Module Microsoft.Graph -Scope CurrentUser
```

必要に応じて、 `-Scope` パラメーターを使用してインストールのスコープを変更します。 これには管理者のアクセス許可が必要です。

```powershell
Install-Module Microsoft.Graph -Scope AllUsers
```

ベータ モジュールをインストールするには、次のコマンドを実行します。

```powershell
Install-Module Microsoft.Graph.Beta
```

信頼されていないリポジトリからモジュールをインストールすることを求めるメッセージが表示される場合があります。 これは、PSGallery リポジトリを信頼されたリポジトリとして事前に設定していない場合に発生します。 `Y` を押してモジュールをインストールします。

#### テスト用の電子メール アカウントを取得する

招待を送信するには、2 つ以上のテスト電子メール アカウントが必要です。 このアカウントは、組織外にある必要があります。 `gmail.com` や `outlook.com` のアドレスなどのソーシャル アカウントを含む任意の種類のアカウントを使用できます。

### CSV ファイルを準備する

Microsoft Excel で、招待されたユーザー名とメール アドレスの一覧を含む CSV ファイルを作成します。 列見出しとして **Name** と **InvitedUserEmailAddress** を必ず含めてください。

たとえば、次の形式のワークシートを作成します。

[Image: csv ファイルの Name と InvitedUserEmailAddress の列を示すスクリーンショット。]

ファイルを **C:\BulkInvite\Invitations.csv** として保存します。

Excel がない場合は、メモ帳などの任意のテキスト エディターで CSV ファイルを作成します。 行ごとに値をコンマで区切って入力します。

### テナントにサインインする

次のコマンドを実行してテナントに接続します。

```powershell
Connect-MgGraph -TenantId "<YOUR_TENANT_ID>"
```

たとえば、`Connect-MgGraph -TenantId "aaaabbbb-0000-cccc-1111-dddd2222eeee"` のようにします。 テナント ドメインも使用できますが、パラメーターは `-TenantId` のままです。 たとえば、`Connect-MgGraph -TenantId "contoso.onmicrosoft.com"` のようにします。

メッセージが表示されたら、資格情報を入力します。

### 招待状を一括送信する

招待を送信するには、次の PowerShell スクリプトを実行します (ここで\* \* *c:\bulkinvite\invitations.csv* は CSV ファイルのパスです)。

```powershell
$invitations = import-csv c:\bulkinvite\invitations.csv

$messageInfo = New-Object Microsoft.Graph.PowerShell.Models.MicrosoftGraphInvitedUserMessageInfo

$messageInfo.customizedMessageBody = "Hello. You are invited to the Contoso organization."

foreach ($email in $invitations) {New-MgInvitation ` 
      -InvitedUserEmailAddress $email.InvitedUserEmailAddress `	-InvitedUserDisplayName $email.Name `	-InviteRedirectUrl https://myapplications.microsoft.com/?tenantid=aaaabbbb-0000-cccc-1111-dddd2222eeee `	-InvitedUserMessageInfo $messageInfo `	-SendInvitationMessage
}
```

スクリプトは、 *invitations.csv* ファイル内の電子メール アドレスに招待を送信します。 ユーザーごとに次のような出力が表示されます。

[Image: 保留中のユーザー受け入れを含む PowerShell 出力を示すスクリーンショット。]

### ユーザーがディレクトリに存在することを確認する

招待されたユーザーが Microsoft Entra ID に追加されたことを確認するには、次のコマンドを実行します。

```powershell
 Get-MgUser -Filter "UserType eq 'Guest'"
```

招待したユーザーが表示されていることを確認します。*emailaddress*#EXT#@*domain* 形式のユーザー プリンシパル名 (UPN) になっています。 たとえば、*msullivan\_fabrikam.com#EXT#@contoso.onmicrosoft.com* では、`contoso.onmicrosoft.com` が招待状を送信した組織になります。

### リソースをクリーンアップする

不要になったら、ディレクトリ内のテスト用のユーザー アカウントを削除できます。 ユーザー アカウントを削除するには、次のコマンドを使用します。

```powershell
 Remove-MgUser -UserId "<String>"
```

例: `Remove-MgUser -UserId "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"`
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/claims-mapping"} -->
## B2B コラボレーション ユーザーの要求マッピング - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/claims-mapping
- Service: entra-external-id / external
- Article date: 2025-04-09
- Summary: Microsoft Entra B2B ユーザーの SAML トークンで発行されたユーザー要求をカスタマイズします。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra External ID を使用すると、 [B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) ユーザーの SAML トークンで発行される要求をカスタマイズできます。 アプリケーションに対するユーザーの認証時に、Microsoft Entra ID は、ユーザーを一意に識別する情報 (要求) を含む SAML トークンをアプリに発行します。 既定では、この要求にはユーザーのユーザー名、電子メール アドレス、名、および姓が含まれます。

[Microsoft Entra 管理センター](https://entra.microsoft.com)では、SAML トークンでアプリケーションに送信された要求を表示または編集できます。 設定にアクセスするには、**Entra ID**&gt;で構成されたシングルサインオン用アプリケーション&gt;を参照します。 「 **ユーザー属性** 」セクションの SAML トークン設定を参照してください。

[Image: UI の SAML トークン属性のスクリーンショット。]

SAML トークンで発行されたクレームは、次の 2 つの理由で編集する必要がある場合があります。

1. アプリケーションで、別の要求 URI または要求値のセットが必要である。
2. アプリケーションでは、NameIdentifier 要求が Microsoft Entra ID に格納されているユーザー プリンシパル名 [(UPN)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-userprincipalname#what-is-userprincipalname) とは異なる必要があります。

[Microsoft Entra ID のエンタープライズ アプリケーションの SAML トークンで発行された要求のカスタマイズで要求を](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)追加および編集する方法について説明します。

### B2B ユーザーの UPN 要求の動作

UPN 値をアプリケーション トークン要求として発行する必要がある場合は、B2B ユーザーに対して実際の要求マッピングの動作が異なる場合があります。 B2B ユーザーが外部の Microsoft Entra ID で認証を行い、ソース属性として `user.userprincipalname` を発行すると、Microsoft Entra ID はこのユーザーのホーム テナントから UPN 属性を発行します。

要求としてを使用する場合、SAML/WS-Fed、Google、電子メール ワンタイム パスコード (OTP) など、`user.userprincipalname`について、システムはユーザーの電子メール アドレスではなく UPN を発行します。 すべての B2B ユーザーのトークン要求で実際の UPN を発行する場合は、代わりにソース属性として `user.localuserprincipalname` 設定します。

注

このセクションで説明する動作は、クラウドのみの B2B ユーザーと、 [B2B コラボレーションに招待または変換](https://learn.microsoft.com/ja-jp/entra/external-id/invite-internal-users)された同期されたユーザーの両方で同じです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/code-samples"} -->
## B2B コラボレーション コードと PowerShell サンプル - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/code-samples
- Service: entra-external-id / external
- Article date: 2025-04-15
- Summary: Microsoft Entra B2B コラボレーション用のコードと PowerShell のサンプル

### PowerShell の例

.csv ファイルに保存したメール アドレスから、外部ユーザーを組織に一括招待できます。

1. .csv ファイルを準備する

    新しい .csv ファイルを作成し、invitations.csv名前を付けます。 この例では、ファイルは、C:\data に保存され、次の情報が格納されています。

    | 名前 | 招待されたユーザーのメールアドレス |
    | --- | --- |
    | Gmail B2B 招待者 | b2binvitee@gmail.com |
    | Outlook B2B 招待者 | b2binvitee@outlook.com |
2. 最新の Microsoft Graph PowerShell を入手する

    新しいコマンドレットを使用するには、更新された Microsoft Graph PowerShell モジュールをインストールする必要があります。 詳細については、「[Microsoft Graph PowerShell SDK のインストール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)」を参照してください。
3. テナントにサインインします。

    ```powershell
    Connect-MgGraph -Scopes "User.Invite.All"
    ```
4. PowerShell コマンドレットを実行します。

    ```powershell
    $invitations = import-csv C:\data\invitations.csv
    $messageInfo = New-Object Microsoft.Open.MSGraph.Model.InvitedUserMessageInfo
    $messageInfo.customizedMessageBody = "Hey there! Check this out. I created an invitation through PowerShell"
    foreach ($email in $invitations) {
       New-MgInvitation -InviteRedirectUrl "https://wingtiptoysonline-dev-ed.my.woodgrove.com" `
          -InvitedUserDisplayName $email.Name -InvitedUserEmailAddress $email.InvitedUserEmailAddress `
          -InvitedUserMessageInfo $messageInfo -SendInvitationMessage:$true
    }
    ```

このコマンドレットは、invitations.csv の電子メール アドレスに招待を送信します。 このコマンドレットの追加機能には、次のようなものがあります。

- 電子メール メッセージでのテキストのカスタマイズ
- 招待されるユーザーの表示名の付記
- CC へのメッセージの送信、またはすべての電子メール メッセージの送信の停止

### コード サンプル

このコード サンプルは、招待 API を呼び出して引き換え URL を取得する方法を示しています。 引き換え URL を使用して、カスタム招待メールを送信します。 電子メールは HTTP クライアントで構成できるので、メールの外観をカスタマイズし、Microsoft Graph API を通じて送信することができます。

## [HTTP](#tab/http)
```http
POST https://graph.microsoft.com/v1.0/invitations
Content-type: application/json
{
  "invitedUserEmailAddress": "david@fabrikam.com",
  "invitedUserDisplayName": "David",
  "inviteRedirectUrl": "https://myapp.contoso.com",
  "sendInvitationMessage": true
}
```

## [C#](#tab/csharp)
```csharp
using System;
using System.Threading.Tasks;
using Microsoft.Graph;
using Azure.Identity;

namespace SampleInviteApp
{
    class Program
    {
        /// <summary>
        /// This is the tenant ID of the tenant you want to invite users to.
        /// </summary>
        private static readonly string TenantID = "";

        /// <summary>
        /// This is the application id of the application that is registered in the above tenant.
        /// </summary>
        private static readonly string TestAppClientId = "";

        /// <summary>
        /// Client secret of the application.
        /// </summary>
        private static readonly string TestAppClientSecret = @"";

        /// <summary>
        /// This is the email address of the user you want to invite.
        /// </summary>
        private static readonly string InvitedUserEmailAddress = @"";

        /// <summary>
        /// This is the display name of the user you want to invite.
        /// </summary>
        private static readonly string InvitedUserDisplayName = @"";

        /// <summary>
        /// Main method.
        /// </summary>
        /// <param name="args">Optional arguments</param>
        static async Task Main(string[] args)
        {
            string InviteRedeemUrl = await SendInvitation();
        }

        /// <summary>
        /// Send the guest user invite request.
        /// </summary>
        private static async string SendInvitation()
        {
            /// Get the access token for our application to talk to Microsoft Graph.
            var scopes = new[] { "https://graph.microsoft.com/.default" };
            var clientSecretCredential = new ClientSecretCredential(TenantID, TestAppClientId, TestAppClientSecret);
            var graphClient = new GraphServiceClient(clientSecretCredential, scopes);

            // Create the invitation object.
            var invitation = new Invitation
            {
                InvitedUserEmailAddress = InvitedUserEmailAddress,
                InvitedUserDisplayName = InvitedUserDisplayName,
                InviteRedirectUrl = "https://www.microsoft.com",
                SendInvitationMessage = true
            };

            // Send the invitation 
            var GraphResponse = await graphClient.Invitations
                .Request()
                .AddAsync(invitation);

            // Return the invite redeem URL
            return GraphResponse.InviteRedeemUrl;
        }
    }
}
```

## [JavaScript](#tab/javascript)
次の NPM パッケージをインストールします。

```bash
npm install express
npm install isomorphic-fetch
npm install @azure/identity
npm install @microsoft/microsoft-graph-client
```

```javascript
const express = require('express')
const app = express()

const { Client } = require("@microsoft/microsoft-graph-client");
const { TokenCredentialAuthenticationProvider } = require("@microsoft/microsoft-graph-client/authProviders/azureTokenCredentials");
const { ClientSecretCredential } = require("@azure/identity");
require("isomorphic-fetch");

// This is the application id of the application that is registered in the above tenant.
const CLIENT_ID = ""

// Client secret of the application.
const CLIENT_SECRET = ""

// This is the tenant ID of the tenant you want to invite users to. For example fabrikam.onmicrosoft.com
const TENANT_ID = ""

async function sendInvite() {

  // Initialize a confidential client application. For more info, visit: https://github.com/Azure/azure-sdk-for-js/blob/main/sdk/identity/identity/samples/AzureIdentityExamples.md#authenticating-a-service-principal-with-a-client-secret
  const credential = new ClientSecretCredential(TENANT_ID, CLIENT_ID, CLIENT_SECRET);

  // Initialize the Microsoft Graph authentication provider. For more info, visit: https://learn.microsoft.com/graph/sdks/choose-authentication-providers?tabs=Javascript#using--for-server-side-applications
  const authProvider = new TokenCredentialAuthenticationProvider(credential, { scopes: ['https://graph.microsoft.com/.default'] });

  // Create MS Graph client instance. For more info, visit: https://github.com/microsoftgraph/msgraph-sdk-javascript/blob/dev/docs/CreatingClientInstance.md
  const client = Client.initWithMiddleware({
    debugLogging: true,
    authProvider,
  });

  // Create invitation object
  const invitation = {
    invitedUserEmailAddress: 'david@fabrikam.com',
    invitedUserDisplayName: 'David',
    inviteRedirectUrl: 'https://www.microsoft.com',
    sendInvitationMessage: true
  };
  
  // Execute the MS Graph command. For more information, visit: https://learn.microsoft.com/graph/api/invitation-post
  graphResponse = await client.api('/invitations')
    .post(invitation);
  
  // Return the invite redeem URL
  return graphResponse.inviteRedeemUrl
}

const inviteRedeemUrl = await sendInvite();

```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/code-samples-self-service-sign-up"} -->
## ユーザー フローの API コネクタ コード サンプル - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/code-samples-self-service-sign-up
- Service: entra-external-id / external
- Article date: 2025-04-14
- Summary: Microsoft Entra 外部 ID のセルフサービス サインアップ フローにおける API コネクタのコード サンプルです。

次の表は、[API コネクタ](https://learn.microsoft.com/ja-jp/entra/external-id/api-connectors-overview)を使用したセルフサービス サインアップ ユーザー フローで Web API を適用するためのコード サンプルへのリンクを示しています。

### API コネクタを使用した Azure 関数のクイックスタート

| サンプル | 説明 |
| --- | --- |
| [.NET Core](https://github.com/Azure-Samples/active-directory-dotnet-external-identities-api-connector-azure-function-validate) | この .NET Core Azure 関数サンプルには、特定のテナント ドメインへのサインアップを制限し、ユーザー指定の情報を検証する方法が示されています。 |
| [Node.js](https://github.com/Azure-Samples/active-directory-nodejs-external-identities-api-connector-azure-function-validate) | この Node.js Azure 関数サンプルには、特定のテナント ドメインへのサインアップを制限し、ユーザー指定の情報を検証する方法が示されています。 |
| [ニシキヘビ](https://github.com/Azure-Samples/active-directory-python-external-identities-api-connector-azure-function-validate) | この Python Azure 関数サンプルには、特定のテナント ドメインへのサインアップを制限し、ユーザー指定の情報を検証する方法が示されています。 |

### カスタム承認ワークフロー

| サンプル | 説明 |
| --- | --- |
| [手動承認ワークフロー](https://github.com/Azure-Samples/active-directory-dotnet-external-identities-api-connectors-approvals) | このサンプルでは、セルフサービス サインアップでゲスト ユーザー アカウントの作成を管理するためのエンド ツー エンドの承認ワークフローを示します |

### 本人確認

| サンプル | 説明 |
| --- | --- |
| [ID学](https://github.com/Azure-Samples/active-directory-dotnet-external-identities-idology-identity-verification) | このサンプルでは、API コネクタを使用して IDology と統合することで、セルフサービス サインアップの一部としてユーザー ID を確認する方法を示します。 |
| [エクスペリアン](https://github.com/Azure-Samples/active-directory-dotnet-external-identities-experian-identity-verification) | このサンプルでは、API コネクタを使用して Experian と統合することで、セルフサービス サインアップの一部としてユーザー ID を確認する方法を示します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/cross-cloud-settings"} -->
## クラウド間の設定 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/cross-cloud-settings
- Service: entra-external-id / external
- Article date: 2026-07-27
- Summary: Microsoft クラウド設定を構成することで、異なるソブリン (国内) Microsoft Azure クラウド内の組織間でセキュリティで保護されたクラウド間 B2B コラボレーションを有効にします。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

別のMicrosoft Azure クラウド内Microsoft Entra組織が共同作業を行う必要がある場合は、Microsoft クラウド設定を使用して、Microsoft Entra B2B コラボレーションを相互に有効にすることができます。 B2B コラボレーションは、次のグローバル クラウドとソブリン Microsoft Azure クラウドの間で利用できます。

- Microsoft Azure 商用クラウドおよびMicrosoft Azure Government
- 21Vianet が運用するMicrosoft Azure商用クラウドとMicrosoft Azure

重要

この記事で説明されているように、両方の組織が相互にコラボレーションを有効にする必要があります。 その後、「[B2B Collaboration のためにテナント間アクセス設定を構成する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)」の説明に従って、各組織は必要に応じて受信および送信のアクセス設定を変更できます。

異なる Microsoft クラウド内の 2 つの組織間のコラボレーションを有効にするには、各組織の管理者が次の手順を実行します。

1. パートナーのクラウドとのコラボレーションを有効にするように、Microsoft クラウド設定を構成します。
2. パートナーのテナント ID を使用して、パートナーを検索し、組織の設定に追加します。
3. パートナー組織の受信と送信の設定を構成します。 管理者は、既定の設定を適用するか、パートナーの特定の設定を構成できます。

各組織がこれらの手順を完了すると、Microsoft Entra組織間の B2B コラボレーションが有効になります。

注

B2B 直接接続は、別の Microsoft クラウド内のMicrosoft Entra テナントとのコラボレーションではサポートされていません。

### はじめに

- **パートナーのテナント ID を取得します。** 別のMicrosoft Azure クラウドでパートナーのMicrosoft Entra組織との B2B コラボレーションを有効にするには、パートナーのテナント ID が必要です。 組織のドメイン名を検索に使用することは、クラウド間のシナリオでは使用できません。
- **パートナーの受信および送信アクセス設定を決定します。** Microsoft クラウド設定でクラウドを選択しても、B2B コラボレーションは自動的に有効になりません。 別のMicrosoft Azure クラウドを有効にすると、そのクラウド内の組織では、すべての B2B コラボレーションが既定でブロックされます。 共同作業するテナントを組織の設定に追加する必要があります。 その時点で、既定の設定はそのテナントに対してのみ有効になります。 既定の設定を引き続き有効にすることができます。 または、組織の受信と送信の設定を変更できます。
- **必要なオブジェクト ID またはアプリ ID を取得します。** パートナー組織の特定のユーザー、グループ、またはアプリケーションにアクセス設定を適用する場合は、設定を構成する前に、その組織に情報を問い合わせる必要があります。 設定の対象を正しく指定できるように、ユーザー オブジェクト ID、グループ オブジェクト ID、アプリケーション ID ("クライアント アプリ ID" または "リソース アプリ ID) を入手します。

注

別の Microsoft クラウドのユーザーは、ユーザー プリンシパル名 (UPN) を使用して招待する必要があります。 [サインインとしてのメール](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-use-email-signin#b2b-guest-user-sign-in-with-an-email-address)は、現在、別の Microsoft クラウドのユーザーと共同作業する場合はサポートされていません。

### Microsoft クラウド設定でクラウドを有効にする

Microsoft クラウドの設定で、共同作業するMicrosoft Azureクラウドを有効にします。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [Security Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) としてサインインします。
2. **Entra ID**&gt;**外部 ID**&gt;**Cross-tenant アクセス設定**に移動し、**Microsoft クラウド設定**を選択します。
3. 有効にする外部Microsoft Azureクラウドの横にあるチェックボックスをオンにします。

    [Image: 外部クラウド オプションが選択されている Microsoft クラウド設定ページ。]

    [ **クロスクラウド同期設定** ] チェックボックスは、クラウド間の同期に適用されます。 詳細については、「 [クラウド間同期の構成」を](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure?pivots=cross-cloud-synchronization)参照してください。

注

クラウドを選択しても、そのクラウド内の組織との B2B コラボレーションは自動的には有効になりません。 次のセクションで説明するように、共同作業する組織を追加する必要があります。

### 組織の設定にテナントを追加する

次の手順に従って、共同作業するテナントを組織の設定に追加します。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [Security Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) としてサインインします。
2. **Entra ID**&gt;**External Identities**&gt;**Cross-tenant アクセス設定**に移動し、[**組織の設定**] を選択します。
3. [ **組織の追加] を選択します**。
4. **[組織の追加]** ペインで、組織のテナント ID を入力します (ドメイン名によるクラウド間検索は現在使用できません)。

    [Image: クロスクラウド テナント ID が入力された組織ウィンドウを追加します。]
5. 検索結果で組織を選択し、[ **追加**] を選択します。
6. 組織が [ **組織設定** ] の一覧に表示されます。 この時点で、この組織のすべてのアクセス設定が、既定の設定から継承されます。

    [Image: 既定のアクセス設定を継承する追加された組織を示す組織設定の一覧。]
7. この組織のテナント間アクセス設定を変更する場合は、**[受信アクセス]** または **[送信アクセス]** 列の下にある **[既定値から継承]** リンクを選択します。 次に、これらのセクションの詳細な手順に従います。

    - [受信アクセス設定を変更する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#modify-inbound-access-settings)
    - [送信アクセス設定を変更する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#modify-outbound-access-settings)

### サインインのエンドポイント

別の Microsoft クラウドの組織とのコラボレーションを有効にした後、クロスクラウド Microsoft Entra ゲスト ユーザーは、[common エンドポイント](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience#redemption-process-and-sign-in-through-a-common-endpoint) (つまり、テナント コンテキストを含まない一般的なアプリ URL) を使用してマルチテナント アプリまたは Microsoft ファースト パーティ アプリにサインインできるようになりました。 サインイン プロセス中に、ゲスト ユーザーは **[サインイン オプション]** を選択してから、 **[組織にサインイン]** を選択します。 その後、ユーザーは組織の名前を入力し、Microsoft Entra資格情報を使用してサインインを続行します。

クラウド間Microsoft Entraゲスト ユーザーは、テナント情報を含むアプリケーション エンドポイントを使用することもできます。次に例を示します。

- `https://myapps.microsoft.com/?tenantid=<your tenant ID>`
- `https://myapps.microsoft.com/<your verified domain>.onmicrosoft.com`
- `https://contoso.sharepoint.com/sites/testsite`

また、テナント情報 (`https://myapps.microsoft.com/signin/X/<application ID>?tenantId=<your tenant ID>` など) を含めることで、クロスクラウド Microsoft Entraゲスト ユーザーにアプリケーションまたはリソースへの直接リンクを付与することもできます。

### クロスクラウド Microsoft Entra ゲスト ユーザーでサポートされるシナリオ

別の Microsoft クラウドからの組織と共同作業する場合は、次のシナリオがサポートされます。

- B2B コラボレーションを使用して、パートナー テナント内のユーザーを招待して、Web 基幹業務アプリ、SaaS アプリ、SharePoint Online サイト、ドキュメント、ファイルなど、組織内のリソースにアクセスします。
- B2B コラボレーションを使用して[パートナー テナントのユーザーにPower BIコンテンツを共有します](https://learn.microsoft.com/ja-jp/fabric/enterprise/powerbi/service-admin-entra-b2b)。
- B2B コラボレーション ユーザーに条件付きアクセス ポリシーを適用し、ユーザーのホーム テナントから多要素認証またはデバイス要求 (準拠している要求とハイブリッド参加済み要求Microsoft Entra) を信頼することを選択します。

注

SharePointと Microsoft Entra B2BSharePointおよびOneDrive内の別の Microsoft クラウドからユーザーを招待するのに最適なエクスペリエンスが提供されます。

### クロスクラウド B2B 認証での既知の動作

クラウド間 B2B ゲスト ユーザーの場合、 `login_hint` は、ユーザーのホーム UPN ではなく、ゲスト オブジェクトのメール属性から設定されます。 この動作は、クラウド間および同じクラウド B2B シナリオの ID 境界とデータ可用性が異なるためです。 同じクラウド B2B シナリオでは、サービスは同じクラウド エコシステム内でユーザーのホーム ID 情報にアクセスできるため、必要に応じてホーム サインイン識別子を解決して使用できます。

ただし、クラウド間 B2B シナリオでは、リソース テナントには、ゲストの不変のホーム クラウド識別子 (PUID) と招待された電子メール アドレスのみが格納されます。 ホーム UPN はゲスト オブジェクトに保持されず、初期認証リダイレクトが生成されるときに使用できないため、 `login_hint` はゲストのメール属性から設計によって設定されます。 シームレスな SSO エクスペリエンスを必要とする組織では、リソース テナント内のゲスト ユーザーのメール属性が、ホーム テナント内のユーザーの UPN と一致することを確認する必要があります。 login\_hintはゲストのメール属性から設定されるため、ゲスト メールの値とホーム テナント UPN の間で不一致が発生すると、ホーム テナントが SSO のユーザーを正しく識別できなくなり、サインイン プロンプトが追加されたり、アカウントの解決に失敗したり、認証エクスペリエンスが低下したりする可能性があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/cross-tenant-access-overview"} -->
## テナント間アクセスの概要 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview
- Service: entra-external-id / external
- Article date: 2025-03-28
- Summary: Microsoft Entra 外部 ID でテナント間アクセスを管理する方法について説明します。 B2B コラボレーションと直接接続の設定を構成して、外部組織のアクセスと信頼を制御します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra 組織は、外部 ID テナント間アクセス設定を使用して、B2B コラボレーションと [B2B 直接接続](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-direct-connect)による他の Microsoft Entra 組織や他の Microsoft Azure クラウドとのコラボレーション方法を管理できます。 [テナント間アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)では、受信アクセスと送信アクセスをきめ細かく制御し、多要素認証 (MFA) と他の組織からのデバイス要求を信頼できるようになります。

この記事では、Microsoft クラウド全体を含む外部の Microsoft Entra 組織との B2B コラボレーションと B2B 直接接続を管理するためのテナント間アクセス設定について説明します。 その他の設定は、Microsoft Microsoft Entra 以外の ID (ソーシャル ID や IT 以外の管理対象外部アカウントなど) との B2B コラボレーションで使用できます。 この[外部コラボレーションの設定](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)には、ゲスト ユーザーのアクセスを制限したり、ゲストを招待できるユーザーを指定したり、ドメインを許可またはブロックしたりするためのオプションが含まれます。

テナント間アクセス設定で追加できる組織の数に制限はありません。

### 受信と送信の設定を使用して外部アクセスを管理する

外部 ID のクロステナント アクセス設定によって、他の Microsoft Entra 組織とコラボレーションを行う方法を管理します。 これらの設定によって、お客様のリソースに対して外部 Microsoft Entra 組織の受信アクセス ユーザーが持つレベルと、お客様のユーザーが外部組織に対して持つ送信アクセスのレベルの両方が決まります。

次の図は、クロステナント アクセスの受信と送信の設定を示しています。 **Resource Microsoft Entra テナント**は、共有するリソースを含むテナントです。 B2B コラボレーションの場合、リソース テナントは招待元のテナント (たとえば、外部ユーザーを招待する企業テナント) です。 **ユーザーのホームの Microsoft Entra テナント**は、外部ユーザーが管理されているテナントです。

[Image: 受信コラボレーションと送信コラボレーションのクロステナント アクセス設定を示す図のスクリーンショット。]

既定では、他の Microsoft Entra 組織との B2B コラボレーションは有効で、B2B 直接接続はブロックされます。 しかし、次の包括的な管理者設定を使用すると、これらの機能の両方を管理できます。

- **送信アクセス設定**は、ユーザーが外部組織のリソースにアクセスできるかどうかを制御します。 これらの設定は、すべての人に適用することも、また、個々のユーザー、グループ、アプリケーションを指定することもできます。
- **受信アクセス設定**は、外部の Microsoft Entra 組織のユーザーが、自分の組織のリソースにアクセスできるかどうかを制御します。 これらの設定は、すべての人に適用することも、また、個々のユーザー、グループ、アプリケーションを指定することもできます。
- **信頼の設定**(受信) は、あなたの条件付きアクセス ポリシーが外部組織からの多要素認証 (MFA)、準拠デバイス、[Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)の要求を、そのユーザーがホーム テナントでこれらの要件を既に満たしている場合に、信頼するかどうかを決定します。 たとえば、MFA を信頼するように信頼設定を構成すると、MFA ポリシーは依然として外部ユーザーに適用されますが、自身のホーム テナントで MFA を既に完了しているユーザーは、あなたのテナントで MFA を再度完了する必要はありません。

### 既定の設定

既定のテナント間アクセス設定は、カスタム設定を構成する組織を除き、テナント外部のすべての Microsoft Entra 組織に適用されます。 既定の設定は変更できますが、B2B コラボレーションと B2B 直接接続の初期設定は次のようになります。

- **B2B コラボレーション**: あなたの内部ユーザーはすべて、 既定で B2B コラボレーションに対して有効になっています。 この設定は、あなたのユーザーは外部のゲストをあなたのリソースにアクセスするよう招待でき、外部組織に彼らをゲストとして招待できることを意味します。 他の Microsoft Entra 組織からの MFA とデバイス要求は信頼されません。
- **B2B 直接接続**: 既定では、B2B 直接接続の信頼関係は確立されていません。 Microsoft Entra ID は、すべての外部 Microsoft Entra テナントのすべての受信および送信 B2B 直接接続機能をブロックします。
- **組織設定**: あなたの組織設定に追加される組織は、既定ではありません。 したがって、すべての外部 Microsoft Entra 組織は、組織との B2B コラボレーションが可能になります。
- **テナント間同期 (プレビュー)**: テナント間同期により、他のテナントのユーザーが自分のテナントに同期されることはありません。

これらの既定の設定は、同じ Microsoft Azure クラウド内の他の Microsoft Entra テナントとの B2B コラボレーションに適用されます。 クラウド間のシナリオでは、既定の設定の動作が少し異なります。 この記事で後述する Microsoft クラウド設定 を参照してください。

### 組織の設定

組織固有の設定を構成するには、組織を追加し、その組織の受信と送信の設定を変更します。 組織の設定は、既定の設定よりも優先されます。

- **B2B コラボレーション**: テナント間アクセス設定を使用して、受信および送信の B2B コラボレーションを管理したり、特定のユーザー、グループ、およびアプリケーションへのアクセスのスコープを設定したりします。 すべての外部組織に適用される既定の構成を設定してから、組織に固有の個別設定を必要に応じて作成することができます。 クロステナント アクセス設定を使用して、他の Microsoft Entra 組織からの多要素 (MFA) およびデバイス クレーム (準拠クレームおよび Microsoft Entra ハイブリッド参加済みクレーム) を信頼することもできます。

    ヒント

    外部ユーザーの MFA を信頼する場合は、 [Microsoft Entra ID 保護 MFA 登録ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-mfa-policy)から [外部ユーザー](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access#mfa-for-azure-ad-external-users)を除外することを検討してください。 両方のポリシーが存在する場合、外部ユーザーはアクセスの要件を満たすことはできません。
- **B2B 直接接続**: B2B 直接接続では、組織の設定を使用して、別の Microsoft Entra 組織との相互信頼関係を設定します。 お客様の組織と外部組織の両方で、受信および送信のテナント間アクセス設定を構成することで、B2B 直接接続を相互に有効にする必要があります。
- **[外部コラボレーションの設定]** を使って、外部ユーザーを招待できるユーザーの制限、B2B 固有ドメインの許可またはブロック、自分のディレクトリへのゲスト ユーザー アクセスの制限の設定を行うことができます。

#### 自動引き換えの設定

自動引き換え設定は、招待を自動的に引き換える受信および送信の組織の信頼設定です。 この設定では、ユーザーがリソースまたはターゲット テナントに初めてアクセスする際に同意プロンプトを受け入れる必要はありません。 この設定は、**[テナントで招待を自動的に引き換える]***テナント名*という名前のチェックボックスです。

[Image: 受信自動引き換えのチェック ボックスを示すスクリーンショット。]

#### この設定はさまざまなシナリオでどのように機能しますか?

自動引き換え設定は、次の状況でテナント間同期、B2B コラボレーション、B2B 直接接続に適用されます。

- クロステナント同期を使用してターゲット テナントにユーザーを作成する場合。
- ユーザーが B2B コラボレーションを通じてリソース テナントに追加されたとき。
- ユーザーが B2B 直接接続を使用してリソース テナント内のリソースにアクセスする場合。

次の表は、これらのシナリオでこの設定を有効にしたときの動作を示しています。

| Item | テナント間同期 | B2B コラボレーション | B2B 直接接続 |
| --- | --- | --- | --- |
| 自動引き換えの設定 | 必須 | オプション | オプション |
| ユーザーが [B2B コラボレーションの招待メール](https://learn.microsoft.com/ja-jp/entra/external-id/invitation-email-elements)を受け取る | いいえ | いいえ | 適用なし |
| ユーザーは[同意プロンプト](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience#consent-experience-for-the-guest)に同意する必要がある | いいえ | いいえ | いいえ |
| ユーザーが [B2B コラボレーションの通知メール](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience#automatic-redemption-process-setting)を受け取る | いいえ | あり | 適用なし |

この設定は、アプリケーションの同意エクスペリエンスには影響しません。 詳細については、「[Microsoft Entra ID でのアプリケーションの同意エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/application-consent-experience)」を参照してください。

この設定は、Azure 商用や Azure Government など、さまざまな Microsoft クラウド環境の組織でサポートされています。 詳しくは、「[テナント間同期を構成する](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure?pivots=cross-cloud-synchronization)」をご覧ください。

#### 同意プロンプトが抑制されるのはいつですか?

自動引き換え設定では、ホーム/ソース テナント (送信) とリソース/ターゲット テナント (受信) の両方に対してこの設定を選択した場合にのみ、同意プロンプトと招待メールが抑制されます。

[Image: 送信と受信の両方の自動引き換え設定を示す図。]

次の表は、テナント間アクセス設定のさまざまな組み合わせに対して自動引き換え設定が選択されている場合の、ソース テナント ユーザーに対する同意プロンプトの動作を示しています。

| ホーム/ソース テナント | リソース/ターゲット テナント | ソース テナント ユーザーに対する同意プロンプトの動作 |
| --- | --- | --- |
| **アウトバウンド** | **インバウンド** |  |
| [Image: オンのチェック マークのアイコン。] | [Image: オンのチェック マークのアイコン。] | 抑制される |
| [Image: オンのチェック マークのアイコン。] | [Image: オフのチェック マークのアイコン。] | 抑制されない |
| [Image: オフのチェック マークのアイコン。] | [Image: オンのチェック マークのアイコン。] | 抑制されない |
| [Image: オフのチェック マークのアイコン。] | [Image: オフのチェック マークのアイコン。] | 抑制されない |
| **インバウンド** | **アウトバウンド** |  |
| [Image: オンのチェック マークのアイコン。] | [Image: オンのチェック マークのアイコン。] | 抑制されない |
| [Image: オンのチェック マークのアイコン。] | [Image: オフのチェック マークのアイコン。] | 抑制されない |
| [Image: オフのチェック マークのアイコン。] | [Image: オンのチェック マークのアイコン。] | 抑制されない |
| [Image: オフのチェック マークのアイコン。] | [Image: オフのチェック マークのアイコン。] | 抑制されない |

Microsoft Graph を使ってこの設定を構成するには、「[crossTenantAccessPolicyConfigurationPartner の更新](https://learn.microsoft.com/ja-jp/graph/api/crosstenantaccesspolicyconfigurationpartner-update)」 API をご覧ください。 独自のオンボード エクスペリエンスの構築については、「[B2B コラボレーションの招待マネージャー](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview#azure-ad-microsoft-graph-api-for-b2b-collaboration)」をご覧ください。

詳しくは、「[テナント間同期を構成する](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)」、「[B2B コラボレーションのためにテナント間アクセス設定を構成する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)」、「[B2B 直接接続のためにクロステナント アクセス設定を構成する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-direct-connect)」をご覧ください。

#### 構成可能な引き換え

構成可能な引き換えを使用すると、ゲスト ユーザーが招待を承諾したときにサインインできる ID プロバイダーの順序をカスタマイズできます。 この機能を有効にして、**[引き換え順序]** タブで引き換え順序を指定できます。

[Image: [引き換え順序] タブのスクリーンショット。]

ゲスト ユーザーが招待メールの **[招待の承諾]** リンクを選択すると、Microsoft Entra ID で[既定の引き換え順序](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience#invitation-redemption-flow)に基づいて招待が自動的に引き換えられます。 新しい [引き換え順序] タブで ID プロバイダーの順序を変更すると、新しい順序によってデフォルトの引き換え順序が上書きされます。

プライマリ ID プロバイダーとフォールバック ID プロバイダーの両方が **[引き換え順序]** タブにあります。

プライマリ ID プロバイダーは、他の認証ソースとのフェデレーションを持つプロバイダーです。 フォールバック ID プロバイダーは、ユーザーがプライマリ ID プロバイダーで一致しない場合に使用されるプロバイダーです。

フォールバック ID プロバイダーには、Microsoft アカウント (MSA)、電子メール ワンタイム パスコード、またはその両方を指定できます。 両方のフォールバック ID プロバイダーを無効にすることはできませんが、すべてのプライマリ ID プロバイダーを無効にして、引き換えオプションにフォールバック ID プロバイダーのみを使用することはできます。

この機能を使用する場合、次の既知の制限事項を考慮してください。

- 既存のシングル サインオン (SSO) セッションを持つ Microsoft Entra ID ユーザーがメールのワンタイム パスコード (OTP) を使用して認証している場合は、**[別のアカウントを使用する]** を選択して、ユーザー名を再入力して OTP フローをトリガーする必要があります。 そうでない場合、ユーザーのアカウントがリソース テナントに存在しないことを示すエラーが表示されます。
- ユーザーが Microsoft Entra ID と Microsoft アカウントの両方に同じメール アドレスを持っている場合、管理者が引き換え方法として Microsoft アカウントを無効にした後でも、Microsoft Entra ID を使用するか Microsoft アカウントを使用するかを選択するように求められます。 引き換え方法が無効になっている場合でも、Microsoft アカウントを引き換えオプションとして選択することは許可されます。

#### Microsoft Entra ID 検証済みドメインの直接フェデレーション

SAML/WS-Fed ID プロバイダー フェデレーション (直接フェデレーション) が、Microsoft Entra ID 検証済みドメインでサポートされるようになりました。 この機能を使用すると、別の Microsoft Entra テナントで検証されたドメインの外部 ID プロバイダーとの直接フェデレーションを設定できます。

注

直接フェデレーション構成を設定しようとしているのと同じテナントでドメインが検証されていないことを確認します。 直接フェデレーションを設定したら、テナントの引き換え優先設定を構成し、構成可能な新しいテナント間アクセス設定を通じて SAML/WS-Fed ID プロバイダーを Microsoft Entra ID 経由で移動できます。

ゲスト ユーザーが招待を引き換えると、従来の同意画面が表示され、[マイ アプリ] ページにリダイレクトされます。 リソース テナントでは、この直接フェデレーション ユーザーのプロファイルに、招待が正常に引き換えられ、外部フェデレーションが発行者としてリストされていることが示されます。

[Image: ユーザー ID の下の直接フェデレーション プロバイダーのスクリーンショット。]

#### B2B ユーザーが Microsoft アカウントを使用した招待の引き換えをできないようにする

B2B ゲスト ユーザーが Microsoft アカウントを使用して招待を利用することを禁止できるようになりました。 代わりに、ゲスト ユーザーは、フォールバックの ID プロバイダーとして、電子メールで送信されるワンタイム パスコードを使用することになります。 既存の Microsoft アカウントを使用して招待を引き換えることはできません。また、新しいアカウントの作成を求められることもありません。 フォールバック ID プロバイダー オプションで Microsoft アカウントをオフにすることで、引き換え注文設定でこの機能を有効にすることができます。

[Image: フォールバック ID プロバイダー オプションのスクリーンショット。]

常に少なくとも 1 つのフォールバック ID プロバイダーを有効にする必要があります。 そのため、Microsoft アカウントを無効にする場合は、代わりにメールで送信されるワンタイム パスコードによる認証を有効にする必要があります。 すでに Microsoft アカウントでサインインしているゲスト ユーザーは、今後も引き続きそのアカウントを使用できます。新しい設定を適用するには、[引き換えの状態をリセット](https://learn.microsoft.com/ja-jp/entra/external-id/reset-redemption-status)する必要があります。

#### テナント間同期の設定

クロステナント同期設定は、ソース テナントの管理者がユーザーとグループをターゲット テナントに同期できるようにするための受信専用の組織設定です。 これらの設定は、この **テナントへのユーザー同期を許可** し、ターゲット テナントで指定されている **このテナントへのグループ同期を許可** するという名前のチェック ボックスです。 これらの設定は、手動の招待やMicrosoft Entraの特権管理など、他のプロセスを通じて作成されたB2B招待状には影響しません。

[Image: クロステナント同期のタブを示すスクリーンショット。ユーザーとグループをターゲット テナントに同期するためのチェック ボックスが表示されています。]

Microsoft Graph を使用してこれらの設定を構成するには、 [crossTenantIdentitySyncPolicyPartner](https://learn.microsoft.com/ja-jp/graph/api/crosstenantidentitysyncpolicypartner-update) API の更新に関する記事を参照してください。 詳しくは、「[テナント間同期を構成する](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)」をご覧ください。

### テナント制限

**テナント制限**の設定を使用すると、以下のような、ユーザーが管理するデバイスでユーザーが使用できる外部アカウントの種類を制御できます。

- ユーザーが不明なテナントで作成したアカウント。
- 外部組織が、その組織のリソースにアクセスできるようにユーザーに付与したアカウント。

テナントの制限を構成して、これらの種類の外部アカウントを禁止し、代わりに B2B コラボレーションを使用します。 B2B コラボレーションを使うと、次の機能を利用できます。

- 条件付きアクセスを使って、B2B コラボレーション ユーザーに多要素認証を強制します。
- インバウンドとアウトバウンドのアクセスを管理します。
- B2B コラボレーション ユーザーの雇用状態が変更された場合、または資格情報が侵害された場合にセッションと資格情報を終了します。
- サインイン ログを使用して、B2B コラボレーション ユーザーに関する詳細を表示します。

テナント制限は他のテナント間アクセス設定とは独立しているため、構成した受信、送信、または信頼設定はテナント制限に影響しません。 テナント制限の構成の詳細については、「[テナント制限 V2 の設定](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-v2)」を参照してください。

### Microsoft クラウド設定

Microsoft クラウド設定を使用すると、さまざまな Microsoft Azure クラウドからの組織とコラボレーションを行うことができます。 Microsoft クラウド設定を使用すると、以下のクラウド間で B2B の相互コラボレーションを確立できます。

- Microsoft Azure 商用クラウドと Microsoft Azure Government (Office GCC-High および DoD クラウドを含む)
- Microsoft Azure 商用クラウドと 21Vianet が運用する Microsoft Azure (21Vianet が運用)

注

B2B 直接接続は、別の Microsoft クラウド内の Microsoft Entra テナントとのコラボレーションではサポートされていません。

詳細については、「[B2B コラボレーションの Microsoft クラウド設定を構成する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-cloud-settings)」についての記事を参照してください。

[クラウド間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview) 設定を使用すると、さまざまな Microsoft クラウドから組織内のテナント間でユーザーのライフサイクルを管理できます。 テナント間の同期設定を有効にすると、そのクラウドからのユーザー データの同期を開始できます。

### 重要な考慮事項

重要

既定の受信または送信の設定を変更してアクセスをブロックするようにすると、お客様の組織内またはパートナー組織内のアプリに対する既存のビジネス クリティカルなアクセスがブロックされる可能性があります。 この記事で説明されているツールを必ず使用し、ビジネスの利害関係者と相談して、必要なアクセスを特定してください。

- Azure portal でテナント間アクセス設定を構成するには、少なくとも[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)であるか、またはカスタム ロールを持つアカウントが必要になります。
- 信頼設定を構成したり特定のユーザー、グループ、またはアプリケーションにアクセス設定を適用したりするには、Microsoft Entra ID P1 ライセンスが必要になります。 構成するテナントにはライセンスが必要です。 別の Microsoft Entra 組織との相互信頼関係が必要な B2B 直接接続の場合、両方のテナントに Microsoft Entra ID P1 ライセンスが必要です。
- テナント間アクセス設定は、他の Microsoft Entra 組織との B2B コラボレーションと B2B 直接接続を管理するために使用されます。 Microsoft Entra 以外の ID (ソーシャル ID や IT で管理されていない外部アカウントなど) との B2B コラボレーションの場合は、 [外部コラボレーション設定](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)を使用します。 外部コラボレーションの設定には、ゲスト ユーザーのアクセスを制限したり、ゲストを招待できるユーザーを指定したり、ドメインを許可またはブロックしたりするための B2B コラボレーションオプションが含まれます。
- 外部組織の特定のユーザー、グループ、またはアプリケーションにアクセス設定を適用する場合は、設定を構成する前に、その組織に情報を問い合わせる必要があります。 設定の対象を正しく指定できるように、ユーザー オブジェクト ID、グループ オブジェクト ID、アプリケーション ID ("クライアント アプリ ID" または "リソース アプリ ID) を入手します。

    ヒント

    サインイン ログを調べると、外部組織のアプリのアプリケーションの ID が見つかる場合があります。 「受信サインインと送信サインインを識別する」セクションを参照してください。
- ユーザーとグループに対して構成するアクセス設定は、アプリケーションのアクセス設定と一致している必要があります。 競合する設定は許可されません。それらを構成しようとすると警告メッセージが表示されます。

    - **例 1**: すべての外部のユーザーとグループに対して受信 アクセスをブロックする場合は、すべてのアプリケーションへのアクセスもブロックする必要があります。
    - **例 2**: すべてのユーザー (または特定のユーザーまたはグループ) に対して送信アクセスを許可すると、外部アプリケーションへのすべてのアクセスをブロックすることはできません。少なくとも 1 つのアプリケーションへのアクセスを許可する必要があります。
- 外部組織との B2B 直接接続を許可する必要があり、条件付きアクセス ポリシーで MFA が必要な場合は、外部組織からの MFA 要求を受け入れるように、信頼設定を構成する必要があります。
- すべてのアプリへのアクセスを既定でブロックすると、ユーザーは Microsoft Rights Management Service (Office 365 Message Encryption、OME とも呼ばれる) で暗号化された電子メールを読み取れなくなります。 この問題を回避するには、ユーザーがこのアプリ ID (000000012-0000-0000-c000-0000000000000) にアクセスできるように送信設定を構成します。 このアプリケーションのみを許可すると、他のすべてのアプリへのアクセスは既定でブロックされます。
- MFA または利用規約 (ToU) を必要とする条件付きアクセス ポリシーは、ユーザーが MFA 登録または ToU の同意を完了できないようにすることができます。 この問題を回避するには、送信設定 (ホーム テナント) と受信設定 (リソース テナント) を構成し、ユーザーが MFA 登録のためにアプリ ID 0000000c-0000-0000-c000-000000000000 (Microsoft アプリ アクセス パネル) にアクセスできるようにし、TOU のためにアプリ ID d52792f4-ba38-424d-8140-ada5b883f293 (Microsoft Entra 利用規約) にアクセスできるようにします。 送信設定の構成は、Microsoft Entra 管理センターで [他のアプリケーションの追加] を選択し、アプリ ID を指定することで実現できます。 現在のユーザー インターフェイス (UI) の制限により、受信設定の構成は [Microsoft Graph API を](https://learn.microsoft.com/ja-jp/graph/api/resources/crosstenantaccesspolicy-overview)使用して実行する必要があります。

### クロステナント アクセス設定を管理するためのカスタム ロール

カスタム ロールを作成して、テナント間のアクセス設定を管理できます。 推奨されるカスタム ロールの詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/external-id/reference-cross-tenant-custom-roles)を参照してください。

### クロステナント アクセスの管理アクションの保護

テナント間のアクセス設定を変更するアクションはすべて保護されたアクションと見なされ、条件付きアクセス ポリシーでさらに保護できます。 構成手順の詳細については、「[保護されたアクション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-overview)」を参照してください。

### 受信サインインと送信サインインを識別する

受信アクセスと送信アクセス設定を設定する前にユーザーやパートナーが必要とするアクセスを識別するのに役立つさまざまなツールが用意されています。 ユーザーとパートナーが必要とするアクセス削除しないようにするため、現在のサインイン動作を調べる必要があります。 この準備手順を行うことで、エンド ユーザーとパートナー ユーザーに必要なアクセスが失われるのを防ぐのに役立ちます。 ただし、場合によってはこれらのログが保持されるのは 30 日間だけなので、ビジネスの利害関係者と話をして必要なアクセスが失われないようにすることを強く推奨します。

| ツール | メソッド |
| --- | --- |
| テナント間サインイン アクティビティの PowerShell スクリプト | 外部組織に関連付けられているユーザー サインイン アクティビティを確認するために、[MSIdentityTools](https://www.powershellgallery.com/packages/MSIdentityTools/2.0.1/Content/Get-MSIDCrossTenantAccessActivity.ps1) から[テナント間ユーザー サインイン アクティビティ](https://www.powershellgallery.com/packages/MSIdentityTools) PowerShell スクリプトを使用します。 |
| サインイン ログの PowerShell スクリプト | ユーザーの外部 Microsoft Entra 組織へのアクセスを確認するには、[Get-MgAuditLogSignIn](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.reports/get-mgauditlogsignin) コマンドレットを使用します。 |
| Azure Monitor | 組織が Azure Monitor サービスをサブスクライブしている場合は、[テナント間アクセス アクティビティ ブック](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-cross-tenant-access-activity)を使用します。 |
| セキュリティ情報イベント管理 (SIEM) システム | 組織がサインイン ログをセキュリティ情報イベント管理 (SIEM) システムにサインイン ログをエクスポートすると、必要な情報を SIEM システムから取得できます。 |

### クロステナントアクセス設定に対する変更の特定

Microsoft Entra 監査ログでは、テナント間のアクセス設定の変更とアクティビティに関するすべてのアクティビティがキャプチャされます。 クロステナント アクセス設定の変更を監査するには、***CrossTenantAccessSettingsのカテゴリカテゴリ***を使用してすべてのアクティビティをフィルター処理し、クロステナント アクセス設定の変更を表示します。

[Image: クロステナント アクセス設定の監査ログのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/cross-tenant-access-settings-b2b-collaboration"} -->
## クロステナント アクセス設定 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration
- Service: entra-external-id / external
- Article date: 2026-04-24
- Summary: Microsoft Entra 外部 IDで B2B コラボレーションと直接接続のテナント間アクセス設定を管理する方法について説明します。 他の組織からの受信アクセスと送信アクセス、信頼 MFA、デバイス要求を制御します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

External Identities クロステナント アクセス設定を使用して、B2B コラボレーションを通じて他の Microsoft Entra 組織とコラボレーションを行う方法を管理します。 これらの設定により、外部の Microsoft Entra 組織の *受信* アクセス ユーザーのレベルと、ユーザーが外部組織に対して持つ *送信* アクセスのレベルの両方が決まります。 また、他の Microsoft Entra 組織からの多要素認証 (MFA) とデバイス[要求 (準拠している要求と Microsoft Entra ハイブリッド参加済みクレーム](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-alt-all-users-compliant-hybrid-or-mfa)) を信頼することもできます。 詳細と計画に関する考慮事項については、「 [Microsoft Entra External ID のテナント間アクセス」を](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)参照してください。

**クラウド間のコラボレーション:** 異なる Microsoft クラウドのパートナー組織は、互いに B2B コラボレーションを設定できます。 まず、「 [Microsoft クラウド設定の構成](https://learn.microsoft.com/ja-jp/entra/external-id/cross-cloud-settings)」の説明に従って、両方の組織が相互にコラボレーションを有効にする必要があります。 その後、各組織は、必要に応じて 、受信アクセス設定 と 送信アクセス設定を以下のように変更できます。

重要

2023 年 8 月 30 日から、Microsoft は、クロステナント アクセス設定をお使いのお客様を新しいストレージ モデルへと移行し始めています。 自動タスクによって設定が移行されると、テナント間アクセス設定が更新されたことを通知するエントリが監査ログに表示されることがあります。 移行プロセス中の短い期間は、設定を変更できません。 変更できない場合は、しばらく待ってからもう一度変更を試してください。 移行が完了 [すると、25 kb のストレージ領域が上限](https://learn.microsoft.com/ja-jp/entra/external-id/faq#how-many-organizations-can-i-add-in-cross-tenant-access-settings-) に達しなくなり、追加できるパートナーの数に制限がなくなります。

### 前提条件

注意事項

既定の受信設定または送信設定を **[アクセスのブロック** ] に変更すると、組織内またはパートナー組織のアプリへの既存のビジネス クリティカルなアクセスがブロックされる可能性があります。 [Microsoft Entra External ID のクロステナント アクセスで](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)説明されているツールを必ず使用し、ビジネス関係者に相談して必要なアクセスを特定してください。

- テナント間アクセス設定を構成する前に、[テナント間アクセスの概要](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview#important-considerations)の[重要な考慮事項に関する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)セクションを確認してください。
- ツールを使用し、「 [受信サインインと送信サインインの識別](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview#identify-inbound-and-outbound-sign-ins) 」の推奨事項に従って、現在アクセスしている外部の Microsoft Entra 組織とリソースを把握します。
- すべての外部 Microsoft Entra 組織に適用する既定のアクセスのレベルを決定します。
- 組織の設定を構成できるように、カスタマイズされた設定が必要な Microsoft Entra **組織** を特定します。
- 外部組織の特定のユーザー、グループ、またはアプリケーションにアクセス設定を適用する場合は、設定を構成する前に、その組織に情報を問い合わせる必要があります。 設定の対象を正しく指定できるように、ユーザー オブジェクト ID、グループ オブジェクト ID、アプリケーション ID ("クライアント アプリ ID" または "リソース アプリ ID) を入手します。
- 外部の Microsoft Azure クラウドでパートナー組織との B2B コラボレーションを設定する場合は、「 [Microsoft クラウド設定の構成」](https://learn.microsoft.com/ja-jp/entra/external-id/cross-cloud-settings)の手順に従います。 パートナー組織の管理者は、テナントに対して同じ操作を行う必要があります。
- 許可/ブロック リストとテナント間アクセスの両方の設定は、招待時に確認されます。 ユーザーのドメインが許可リストにある場合は、テナント間アクセス設定でドメインが明示的にブロックされていない限り、ユーザーを招待できます。 ユーザーのドメインがブロックリストにある場合、テナント間アクセス設定に関係なく、ユーザーを招待することはできません。 ユーザーがどちらのリストにも含まれていない場合は、テナント間アクセス設定をチェックして、ユーザーを招待できるかどうかを判断します。

### 既定の設定を構成する

既定のクロステナント アクセス設定は、組織固有のカスタマイズした設定を作成していない、すべての外部組織に適用されます。 Microsoft Entra ID で提供されている既定の設定を変更する場合は、以下の手順に従います。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**External Ids**&gt;**Cross-tenant アクセス設定**に移動し、**テナント間アクセス設定**を選択します。
3. [ **既定の設定** ] タブを選択し、概要ページを確認します。

    [Image: [クロステナント アクセス設定] の [既定の設定] タブを示すスクリーンショット。]
4. 設定を変更するには、[ **受信の既定値の編集]** リンクまたは [ **送信の既定値の編集] リンクを** 選択します。

    [Image: 既定の設定の編集ボタンを示すスクリーンショット。]
5. 以下のセクションの詳細な手順に従って、既定の設定を変更します。

    - 受信アクセス設定を変更する
    - 送信アクセス設定を変更する

### 組織を追加する

以下の手順に従って、特定の組織向けにカスタマイズした設定を構成します。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**External Ids**&gt;**Cross-tenant アクセス設定**に移動し、[**組織の設定**] を選択します。
3. [ **組織の追加] を選択します**。
4. [ **組織の追加** ] ウィンドウで、組織の完全なドメイン名 (またはテナント ID) を入力します。

    [Image: 組織の追加を示すスクリーンショット。]
5. 検索結果で組織を選択し、[ **追加**] を選択します。
6. 組織が [ **組織設定** ] の一覧に表示されます。 この時点で、この組織のすべてのアクセス設定が、既定の設定から継承されます。 この組織の設定を変更するには、[**受信アクセス**] 列または [**送信アクセス**] 列**の下にある [既定から継承**] リンクを選択します。

    [Image: 既定の設定で追加された組織を示すスクリーンショット。]
7. 以下のセクションの詳細な手順に従って、組織の設定を変更します。

    - 受信アクセス設定を変更する
    - 送信アクセス設定を変更する

### 受信アクセス設定を変更する

受信設定を使用して、選んだ内部アプリケーションに、どの外部ユーザーとグループがアクセスできるかを選びます。 構成しようとしているのが既定の設定であるか、組織固有の設定であるかを問わず、クロステナントの受信アクセス設定を変更するための手順は同一です。 このセクションで説明されているように、[**組織の設定**] タブで **[既定**] タブまたは組織に移動し、変更を加えます。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**External Ids**&gt;**Cross-tenant アクセス設定**に移動します。
3. 変更しようとしている設定に移動します。

    - **既定の設定**: 既定の受信設定を変更するには、[ **既定の設定** ] タブを選択し、[ **受信アクセス設定**] で [ **受信の既定値の編集]** を選択します。
    - **組織の設定**: 特定の組織の設定を変更するには、[ **組織の設定** ] タブを選択し、一覧から組織を検索 (または 追加) してから、[ **受信アクセス** ] 列でリンクを選択します。
4. 変更しようとしている受信設定については、以下の詳細な手順に従います。

    - 受信 B2B コラボレーション設定を変更するには
    - MFA とデバイスの要求を受け入れるための受信信頼設定を変更するには

注意

Microsoft [Entra B2B 統合](https://learn.microsoft.com/ja-jp/sharepoint/sharepoint-azureb2b-integration) を有効にして Microsoft SharePoint と Microsoft OneDrive のネイティブ共有機能を使用している場合は、外部 [コラボレーション設定に外部](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)ドメインを追加する必要があります。 そうしないと、外部テナントがテナント間アクセス設定に追加されている場合でも、これらのアプリケーションからの招待が失敗する可能性があります。

#### B2B Collaboration の受信設定を変更するには

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**External Ids**&gt;**Cross-tenant アクセス設定**に移動し、[**組織の設定**] を選択します
3. **[受信アクセス**] 列と **[B2B コラボレーション**] でリンクを選択します。すべての外部ユーザーとグループのアクセスをブロックする場合は、すべての内部アプリケーションへのアクセスもブロックする必要があります。
4. 特定の組織の受信アクセス設定を構成している場合は、オプションを選びます。

    - **既定の設定**: 組織で既定の受信設定を使用する場合は、このオプションを選択します ( **[既定の** 設定] で構成されているように、すべての外部ユーザーとグループのアクセスをブロックする場合は、すべての内部アプリケーションへのアクセスもブロックする必要があります。 この組織に対してカスタマイズされた設定が既に構成されている場合は、[ **はい** ] を選択して、すべての設定を既定の設定に置き換えることを確認する必要があります。 次に、[ **保存]** を選択し、この手順の残りの手順をスキップします。
    - **設定のカスタマイズ**: 既定の設定ではなく、この組織に適用する設定をカスタマイズする場合は、このオプションを選択します。 この手順の残りの部分を続けます。
5. **[外部ユーザーとグループ] を選択します**。
6. [ **アクセスの状態]** で、次のいずれかを選択します。

    - **アクセスを許可**する: [ **適用** ] で指定されたユーザーとグループを B2B コラボレーションに招待できるようにします。
    - **アクセスをブロック**する: [ **適用** 対象] で指定されたユーザーとグループが B2B コラボレーションに招待されないようにブロックします。

    [Image: B2B コラボレーションのユーザー アクセス状態の選択を示すスクリーンショット。]
7. [ **適用対象**] で、次のいずれかを選択します。

    - **すべての外部ユーザーとグループ**: **[アクセスの状態** ] で選択したアクションを、外部の Microsoft Entra 組織のすべてのユーザーとグループに適用します。
    - **外部ユーザーとグループを選択** します (Microsoft Entra ID P1 または P2 サブスクリプションが必要): **[アクセスの状態** ] で選択したアクションを外部組織内の特定のユーザーとグループに適用できます。

    注意

    すべての外部ユーザーとグループのアクセスをブロックする場合は、([ **アプリケーション** ] タブで) すべての内部アプリケーションへのアクセスをブロックする必要もあります。 テナント間同期を構成している場合、すべての外部ユーザーとグループのアクセスをブロックすると、テナント間の同期がブロックされる可能性があります。

    [Image: ターゲット ユーザーとグループの選択を示すスクリーンショット。]
8. **[外部ユーザーとグループの選択**] を選択した場合は、追加するユーザーまたはグループごとに次の操作を行います。

    - [ **外部ユーザーとグループの追加]** を選択します。
    - [ **他のユーザーとグループの追加** ] ウィンドウの検索ボックスに、パートナー組織から取得したユーザー オブジェクト ID またはグループ オブジェクト ID を入力します。
    - 検索ボックスの横にあるメニューで、 **ユーザー** または **グループ**を選択します。
    - [ **追加] を選択します**。

    注意

    受信の既定の設定では、ユーザーまたはグループをターゲットにすることはできません。

    [Image: ユーザーとグループの追加を示すスクリーンショット。]
9. ユーザーとグループの追加が完了したら、[ **送信]** を選択します。

    [Image: ユーザーとグループの送信を示すスクリーンショット。]
10. [アプリケーション] タブ **を** 選択します。
11. [ **アクセスの状態]** で、次のいずれかを選択します。

    - **アクセスを許可**する: [ **適用** ] で指定されたアプリケーションに B2B コラボレーション ユーザーがアクセスできるようにします。
    - **アクセスをブロック**する: [ **適用** 対象] で指定されたアプリケーションが B2B コラボレーション ユーザーによってアクセスされないようにブロックします。

    [Image: アプリケーションのアクセス状態を示すスクリーンショット。]
12. [ **適用対象**] で、次のいずれかを選択します。

    - **すべてのアプリケーション**: **[アクセスの状態** ] で選択したアクションをすべてのアプリケーションに適用します。
    - **アプリケーションの選択** (Microsoft Entra ID P1 または P2 サブスクリプションが必要): [ **アクセスの状態** ] で選択したアクションを組織内の特定のアプリケーションに適用できます。

    注意

    すべてのアプリケーションへのアクセスをブロックする場合は、([外部ユーザーと **グループ] タブ** で) すべての外部ユーザーとグループのアクセスをブロックする必要もあります。

    [Image: ターゲット アプリケーションを示すスクリーンショット。]
13. **[アプリケーションの選択**] を選択した場合は、追加するアプリケーションごとに次の操作を行います。

    - [ **Microsoft アプリケーションの追加]** または **[他のアプリケーションの追加] を選択します**。
    - **[選択**] ウィンドウで、検索ボックスにアプリケーション名またはアプリケーション ID (*クライアント アプリ ID* または*リソース アプリ ID) を*入力します。 次に、検索結果でアプリケーションを選択します。 追加するアプリケーションごとに繰り返します。
    - アプリケーションの選択が完了したら、[選択] を **選択します**。

    [Image: アプリケーションの選択を示すスクリーンショット。]
14. **[保存] を選択します**。

#### Microsoft アプリケーションを許可するための考慮事項

指定された一連のアプリケーションのみを許可するように **テナント間アクセス設定** を構成する場合は、次の表に示す Microsoft アプリケーションを追加することを検討してください。 たとえば、許可リストを構成し、SharePoint Online のみを許可する場合、ユーザーはマイ アプリにアクセスしたり、リソース テナントで MFA に登録したりすることはできません。 スムーズなエンド ユーザー エクスペリエンスを実現するには、受信と送信のコラボレーション設定に次のアプリケーションを含めます。

| アプリケーション | リソース ID | ポータルで使用可能 | 詳細 |
| --- | --- | --- | --- |
| マイ アプリ | 2793995E-0A7D-40D7-BD35-6968BA142197 | はい | 招待を引き換えた後の既定のランディング ページ。 へのアクセスを定義します `myapplications.microsoft.com`。 |
| Microsoft アプリ アクセス パネル | 0000000c-0000-0000-c000-0000000000000000 | いいえ | マイ サインイン内の特定のページを読み込むときに、遅延バインディング呼び出しで使用されます。たとえば、[セキュリティ情報] ブレードや [組織] スイッチャーなどです。 |
| 自分のプロファイル | 8C59EAD7-D703-4A27-9E55-C96A0054C8D2 | はい | マイ グループとマイ アクセス ポータルを `myaccount.microsoft.com` 含めるアクセスを定義します。 マイ プロファイル内の一部のタブでは、機能するためにここに記載されている他のアプリが必要です。 |
| マイ サインイン | 19DB86C3 - B2B9 - 44CC - B339 - 36DA233A3BEの | いいえ | セキュリティ情報へのアクセスを `mysignins.microsoft.com` 含めるアクセスを定義します。 ユーザーにリソース テナントでの MFA の登録と使用を要求する場合は、このアプリを許可します (たとえば、MFA はホーム テナントから信頼されていません)。 |

前の表の一部のアプリケーションでは、Microsoft Entra 管理センターからの選択が許可されていません。 許可するには、次の例に示すように Microsoft Graph API で追加します。

```json
PATCH https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/partners/<insert partner’s tenant id> 
{ 
    "b2bCollaborationInbound": { 
        "applications": { 
            "accessType": "allowed", 
            "targets": [ 
                { 
                    "target": "2793995e-0a7d-40d7-bd35-6968ba142197", 
                    "targetType": "application" 
                }, 
                { 
                    "target": "0000000c-0000-0000-c000-000000000000", 
                    "targetType": "application" 
                }, 
                { 
                    "target": "8c59ead7-d703-4a27-9e55-c96a0054c8d2", 
                    "targetType": "application" 
                }, 
                { 
                    "target": "19db86c3-b2b9-44cc-b339-36da233a3be2", 
                    "targetType": "application" 
                } 
            ] 
        } 
    } 
}
```

注意

PATCH 要求には、以前に構成したアプリケーションが上書きされるため、許可する追加のアプリケーションを必ず含めるようにしてください。 既に構成されているアプリケーションは、ポータルから手動で取得するか、パートナー ポリシーで GET 要求を実行することで取得できます。 たとえば、`GET https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/partners/<insert partner's tenant id>` のように指定します。

注意

Microsoft Entra 管理センターで使用できるアプリケーションにマップされていない Microsoft Graph API を介して追加されたアプリケーションは、アプリ ID として表示されます。

Microsoft Entra 管理センターの受信および送信のクロステナント アクセス設定に Microsoft 管理ポータル アプリを追加することはできません。 Microsoft 管理ポータルへの外部アクセスを許可するには、Microsoft Graph API を使用して、Microsoft 管理ポータル アプリ グループに含まれる次のアプリを個別に追加します。

- Azure ポータル (c44b4083-3bb0-49c1-b47d-974e53cbdf3c)
- Microsoft Entra 管理センター (c44b4083-3bb0-49c1-b47d-974e53cbdf3c)
- Microsoft 365 Defender ポータル (80ccca67-54bd-44ab-8625-4b79c4dc7775)
- Microsoft Intune 管理センター (80ccca67-54bd-44ab-8625-4b79c4dc7775)
- Microsoft Purview ポータル (80ccca67-54bd-44ab-8625-4b79c4dc7775)

#### 引き換え順序を構成する

ゲスト ユーザーが招待を受けるときにサインインに使用できる ID プロバイダーの順序をカスタマイズするには、以下の手順を実行します。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com/)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**External Ids**&gt;**Cross-tenant アクセス設定**に移動します。
3. [ **既定の設定** ] タブの [ **受信アクセス設定**] で、[ **受信の既定値の編集]** を選択します。
4. **[B2B コラボレーション**] タブで、[**引き換え注文**] タブを選択します。
5. ゲスト ユーザーが招待を受けたときにサインインできる ID プロバイダーを上下に動かして、順序を変更します。 引き換え順序を既定の設定にリセットすることもここでできます。

    [Image: [引き換え注文] タブを示すスクリーンショット。]
6. **[保存] を選択します**。

Microsoft Graph API を使用して引き換え順序をカスタマイズすることもできます。

1. [Microsoft Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)を開きます。
2. 少なくとも [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) としてリソース テナントにサインインします。
3. 次のクエリを実行して、現在の引き換え順序を取得します。

```http
GET https://graph.microsoft.com/beta/policies/crossTenantAccessPolicy/default
```

1. この例では、SAML/WS-Fed IdP フェデレーションを Microsoft Entra ID プロバイダーの上の引き換え順序に移動して、先頭にします。 次の要求本文で同じ URI にパッチを適用します。

```http
{
  "invitationRedemptionIdentityProviderConfiguration":
  {
  "primaryIdentityProviderPrecedenceOrder": ["ExternalFederation ","AzureActiveDirectory"],
  "fallbackIdentityProvider": "defaultConfiguredIdp "
  }
}
```

1. 変更を確認するには、GET クエリをもう一度実行します。
2. 引き換え順序を既定の設定にリセットするには、次のクエリを実行します。

```http
    {
    "invitationRedemptionIdentityProviderConfiguration": {
    "primaryIdentityProviderPrecedenceOrder": [
    "azureActiveDirectory",
    "externalFederation",
    "socialIdentityProviders"
    ],
    "fallbackIdentityProvider": "defaultConfiguredIdp"
    }
    }
```

#### Microsoft Entra ID 検証済みドメインの SAML/WS-Fed フェデレーション (直接フェデレーション)

参加している Microsoft Entra ID 検証済みドメインを追加して、直接フェデレーション関係を設定できるようになりました。 まず、 [管理センター](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation) または [API](https://learn.microsoft.com/ja-jp/graph/api/resources/samlorwsfedexternaldomainfederation) を使用して、直接フェデレーション構成を設定する必要があります。 ドメインが同じテナントで検証されていないことを確認します。 構成を設定したら、引き換え順序をカスタマイズできます。 SAML/WS-Fed IdP が、最後のエントリとして引き換え順序に追加されます。 引き換え順序で上に移動して、Microsoft Entra ID プロバイダーの上に設定できます。

#### B2B ユーザーが Microsoft アカウントを使用した招待の引き換えをできないようにする

B2B ゲスト ユーザーが既存の Microsoft アカウントを使用して招待を引き換えたり、招待を受けるための新しいアカウントを作成したりできないようにするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)に少なくとも[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)としてサインインする
2. **Entra ID**&gt;**External Ids**&gt;**Cross-tenant アクセス設定**に移動します。
3. [ **既定の設定** ] タブの [ **受信アクセス設定**] で、[ **受信の既定値の編集]** を選択します。
4. **[B2B コラボレーション**] タブで、[**引き換え注文**] タブを選択します。
5. [ **フォールバック ID プロバイダー] で、** Microsoft サービス アカウント (MSA) を無効にします。

    [Image: フォールバック ID プロバイダー オプションのスクリーンショット。]
6. **[保存] を選択します**。

任意の時点で少なくとも 1 つのフォールバック ID プロバイダーが有効になっている必要があります。 Microsoft アカウントを無効にする場合は、電子メールのワンタイム パスコードを有効にする必要があります。 両方のフォールバック ID プロバイダーを無効にすることはできません。 Microsoft アカウントでサインインしている既存のゲスト ユーザーは、以降のサインイン中も引き続き使用します。この設定を適用するには、 [引き換えの状態をリセット](https://learn.microsoft.com/ja-jp/entra/external-id/reset-redemption-status) する必要があります。

#### MFA およびデバイスの信頼性情報に関する受信の信頼の設定を変更するには

1. [ **信頼** 設定] タブを選択します。
2. (この手順は **組織の設定** にのみ適用されます)。組織の設定を構成する場合は、次のいずれかを選択します。

    - **既定の設定**: 組織は、[ **既定** の設定] タブで構成された設定を使用します。この組織に対してカスタマイズされた設定が既に構成されている場合は、[ **はい** ] を選択して、すべての設定を既定の設定に置き換えることを確認します。 次に、[ **保存]** を選択し、この手順の残りの手順をスキップします。
    - **設定のカスタマイズ**: 既定の設定ではなく、この組織に適用する設定をカスタマイズできます。 この手順の残りの部分を続けます。
3. 次のオプションの中から、1つまたは複数選択します。

    - **Microsoft Entra テナントからの多要素認証を信頼する**: 条件付きアクセス ポリシーが外部組織からの MFA 要求を信頼できるようにするには、このチェック ボックスをオンにします。 ユーザーが MFA を完了したことを示す要求については、認証時に Microsoft Entra ID はユーザーの資格情報を確認します。 それ以外の場合は、ユーザーのホーム テナントで MFA チャレンジが開始されます。 この設定は、テナント内のサービスを管理するクラウド サービス プロバイダーの技術者が使用するなど、詳細 [な委任された管理者特権 (GDAP)](https://learn.microsoft.com/ja-jp/partner-center/customers/gdap-introduction) を使用して外部ユーザーがサインインする場合は適用されません。 外部ユーザーが GDAP を使用してサインインする場合、MFA は常にユーザーのホーム テナントで必要であり、リソース テナントでは常に信頼されます。 GDAP ユーザーの MFA 登録は、ユーザーのホーム テナントの外部ではサポートされていません。 組織で、ユーザーのホーム テナントの MFA に基づいてサービス プロバイダーの技術者へのアクセスを禁止する必要がある場合は、 [Microsoft 365 管理センター](https://admin.microsoft.com/Adminportal/Home#/partners)で GDAP 関係を削除できます。
    - **準拠デバイスを信頼**する: 条件付きアクセス ポリシーが、ユーザーがリソースにアクセスするときに外部組織からの [準拠デバイス要求](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-device-compliance) を信頼できるようにします。
    - **Microsoft Entra ハイブリッド参加済みデバイスを信頼**する: 条件付きアクセス ポリシーが、ユーザーがリソースにアクセスするときに外部組織からの Microsoft Entra ハイブリッド参加済みデバイス要求を信頼できるようにします。

    [Image: 信頼設定を示すスクリーンショット。]
4. (この手順は **組織の設定** にのみ適用されます)。 **自動引き換え** オプションを確認します。

    - **テナントで招待を自動的に引き換えます**&lt;tenant&gt;: 招待を自動的に使用する場合は、この設定をオンにします。 その場合、指定したテナントのユーザーは、テナント間同期、B2B コラボレーション、または B2B 直接接続を使用して、このテナントに初めてアクセスする際に同意プロンプトで同意する必要はありません。 この設定では、指定したテナントが、この設定で送信アクセスも確認する場合にのみ同意プロンプトが表示されなくなります。

    [Image: 受信の [自動引き換え] チェック ボックスを示すスクリーンショット。]
5. **[保存] を選択します**。

#### このテナントへの同期をユーザーに許可する

追加した組織の **受信アクセス** を選択すると、[ **テナント間の同期** ] タブと [ **ユーザーがこのテナントへの同期を許可** する] チェック ボックスが表示されます。 テナント間同期は、Microsoft Entra ID 一方向同期サービスであり、組織のテナント間で B2B コラボレーション ユーザーの作成、更新、削除を自動化します。 詳細については、 [テナント間同期の構成](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure) と [マルチテナント組織のドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/)。

[Image: [ユーザーがこのテナントに同期することを許可する] チェック ボックスが表示された [クロステナント同期] タブを示すスクリーンショット。]

### 送信アクセス設定を変更する

送信設定を使用して、選んだ外部アプリケーションに、どのユーザーとグループがアクセスできるかを選びます。 構成しようとしているのが既定の設定であるか、組織固有の設定であるかを問わず、クロステナントの送信アクセス設定を変更するための手順は同一です。 このセクションで説明されているように、[**組織の設定**] タブで **[既定**] タブまたは組織に移動し、変更を加えます。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**External Ids**&gt;**Cross-tenant アクセス設定**に移動します。
3. 変更しようとしている設定に移動します。

    - 既定の送信設定を変更するには、[ **既定の設定** ] タブを選択し、[ **送信アクセス設定**] で [ **送信の既定値の編集]** を選択します。
    - 特定の組織の設定を変更するには、[ **組織の設定** ] タブを選択し、一覧から組織を見つけて (または 追加)、[ **送信アクセス** ] 列でリンクを選択します。
4. **[B2B コラボレーション**] タブを選択します。
5. (この手順は **組織の設定** にのみ適用されます)。組織の設定を構成する場合は、次のオプションを選択します。

    - **既定の設定**: 組織は、[ **既定** の設定] タブで構成された設定を使用します。この組織に対してカスタマイズされた設定が既に構成されている場合は、[ **はい** ] を選択して、すべての設定を既定の設定に置き換えることを確認する必要があります。 次に、[ **保存]** を選択し、この手順の残りの手順をスキップします。
    - **設定のカスタマイズ**: 既定の設定ではなく、この組織に適用する設定をカスタマイズできます。 この手順の残りの部分を続けます。
6. [ **ユーザーとグループ] を選択します**。
7. [ **アクセスの状態]** で、次のいずれかを選択します。

    - **アクセスを許可**する: [ **適用** 対象] で指定したユーザーとグループを、B2B コラボレーションのために外部組織に招待できるようにします。
    - **アクセスをブロック**する: [ **適用** 対象] で指定されたユーザーとグループが B2B コラボレーションに招待されないようにブロックします。 すべてのユーザーとグループに対してアクセスをブロックすると、B2B コラボレーションを介したすべての外部アプリケーションへのアクセスもブロックされます。

    [Image: b2b コラボレーションのユーザーとグループのアクセス状態を示すスクリーンショット。]
8. [ **適用対象**] で、次のいずれかを選択します。

    - **すべての &lt;組織&gt; ユーザー**: **[アクセスの状態** ] で選択したアクションを、すべてのユーザーとグループに適用します。
    - **組織&lt;ユーザーとグループ&gt;選択**します (Microsoft Entra ID P1 または P2 サブスクリプションが必要): [**アクセスの状態**] で選択したアクションを特定のユーザーとグループに適用できます。

    注意

    すべてのユーザーとグループのアクセスをブロックする場合は、([外部アプリケーション] タブで) すべての外部アプリケーションへのアクセスをブロック **する** 必要もあります。

    [Image: b2b コラボレーションのターゲット ユーザーの選択を示すスクリーンショット。]
9. [ **組織 &lt;選択&gt; ユーザーとグループを選択**した場合は、追加するユーザーまたはグループごとに次の操作を行います。

    - [**組織&lt;ユーザーとグループ&gt;追加**] を選択します。
    - **[選択**] ウィンドウで、検索ボックスにユーザー名またはグループ名を入力します。
    - 検索結果で、ユーザーまたはグループを選択します。
    - 追加するユーザーとグループの選択が完了したら、[選択] を **選択します**。

    注意

    ユーザーとグループを対象にすると、 [SMS ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-sms-signin)を構成したユーザーを選択することはできません。 これは、外部ユーザーが送信アクセス設定に追加されないように、ユーザー オブジェクトに "フェデレーション資格情報" を持つユーザーがブロックされるためです。 回避策として、 [Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/crosstenantaccesspolicy-overview) を使用して、ユーザーのオブジェクト ID を直接追加するか、ユーザーが属するグループをターゲットにすることができます。
10. [ **外部アプリケーション** ] タブを選択します。
11. [ **アクセスの状態]** で、次のいずれかを選択します。

    - **アクセスを許可**する: [ **適用** ] で指定された外部アプリケーションに、B2B コラボレーションを介してユーザーがアクセスできるようにします。
    - **アクセスをブロック**する: [ **適用** 対象] で指定された外部アプリケーションが、B2B コラボレーションを介してユーザーによってアクセスされないようにブロックします。

    [Image: b2b コラボレーションのアプリケーション アクセス状態を示すスクリーンショット。]
12. [ **適用対象**] で、次のいずれかを選択します。

    - **すべての外部アプリケーション**: **[アクセスの状態** ] で選択したアクションをすべての外部アプリケーションに適用します。
    - **外部アプリケーションの選択**: **[アクセスの状態** ] で選択したアクションをすべての外部アプリケーションに適用します。

    注意

    すべての外部アプリケーションへのアクセスをブロックする場合は、([ **ユーザーとグループ** ] タブで) すべてのユーザーとグループのアクセスをブロックする必要もあります。

    [Image: b2b コラボレーションのアプリケーション ターゲットを示すスクリーンショット。]
13. **[外部アプリケーションの選択**] を選択した場合は、追加するアプリケーションごとに次の操作を行います。

    - [ **Microsoft アプリケーションの追加]** または **[他のアプリケーションの追加] を選択します**。
    - 検索ボックスに、アプリケーション名またはアプリケーション ID ("クライアント アプリ ID" または "リソース アプリ ID") を入力します。 次に、検索結果でアプリケーションを選択します。 追加するアプリケーションごとに繰り返します。
    - アプリケーションの選択が完了したら、[選択] を **選択します**。

    [Image: b2b コラボレーション用のアプリケーションの選択を示すスクリーンショット。]
14. **[保存] を選択します**。

#### 送信の信頼の設定を変更するには

(このセクションは **組織の設定** にのみ適用されます)。

1. [ **信頼** 設定] タブを選択します。
2. **自動引き換え**オプションを確認します。

    - **テナントで招待を自動的に引き換えます**&lt;tenant&gt;: 招待を自動的に使用する場合は、この設定をオンにします。 その場合、このテナントのユーザーは、テナント間同期、B2B コラボレーション、または B2B 直接接続を使用して、指定したテナントに初めてアクセスする際に同意プロンプトで同意する必要はありません。 この設定では、指定したテナントが、この設定で受信アクセスも確認する場合にのみ同意プロンプトが表示されなくなります。

        [Image: [送信自動引き換え] チェック ボックスを示すスクリーンショット。]
3. **[保存] を選択します**。

### 組織を削除する

組織の設定から組織を削除すると、その組織で、既定のテナント間アクセス設定が有効になります。

注意

組織が組織のクラウド サービス プロバイダーである場合 (Microsoft Graph [パートナー固有の構成](https://learn.microsoft.com/ja-jp/graph/api/resources/crosstenantaccesspolicyconfigurationpartner) の isServiceProvider プロパティが true の場合)、組織を削除することはできません。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**External Ids**&gt;**Cross-tenant アクセス設定**に移動します。
3. [ **組織の設定** ] タブを選択します。
4. 一覧で組織を見つけ、その行のごみ箱アイコンを選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/cross-tenant-access-settings-b2b-direct-connect"} -->
## B2B 直接接続を設定する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-direct-connect
- Service: entra-external-id / external
- Article date: 2026-09-08
- Summary: テナント間アクセス設定を利用して、他の Microsoft Entra 組織との B2B 直接接続を構成し、送信アクセスと受信アクセスを管理する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

テナント間アクセス設定を使用して、 [B2B 直接接続](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-direct-connect-overview)を介して他の Microsoft Entra 組織と共同作業する方法を管理します。 これらの設定を使用すると、外部組織に対してユーザーが持つ送信アクセスのレベルを決定できます。 また、外部 Microsoft Entra 組織内のユーザーが内部リソースに対して持つ受信アクセスのレベルを制御することもできます。

- **既定の設定**: テナント間アクセスの既定の設定は、個々の設定を構成する組織を除き、すべての外部 Microsoft Entra 組織に適用されます。 これらの既定の設定を変更できます。 B2B 直接接続の場合、通常、既定の設定をそのままにして、組織固有の設定を使用して B2B 直接接続アクセスを有効にします。 初期状態では、既定値は次のとおりです。

    - **B2B 直接接続の初期設定** - 既定では、送信 B2B 直接接続はテナント全体でブロックされ、受信 B2B 直接接続はすべての外部 Microsoft Entra 組織でブロックされます。
    - **組織の設定** - 既定では組織は追加されません。
- **組織固有の設定**: 組織を追加し、その組織の受信と送信の設定を変更することで、組織固有の設定を構成できます。 組織の設定は、既定の設定よりも優先されます。

テナント間アクセス設定を使用して [B2B 直接接続を管理](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-direct-connect-overview#managing-cross-tenant-access-for-b2b-direct-connect)する方法について説明します。

重要

Microsoft 2023 年 8 月 30 日に、テナント間アクセス設定を使用する顧客を新しいストレージ モデルに移行し始めました。 自動タスクによって設定が移行されると、クロステナント アクセス設定が更新されたことを示す監査ログ エントリが表示されることがあります。 移行処理中に短い期間、設定を変更できない場合があります。 変更できない場合は、しばらく待ってから、もう一度やり直してください。 移行が完了すると、 [25 KB のストレージ領域が上限](https://learn.microsoft.com/ja-jp/entra/external-id/faq#how-many-organizations-can-i-add-in-cross-tenant-access-settings-)に達しなくなり、追加できるパートナーの数に制限はありません。

### はじめに

- テナント間アクセス設定を構成する前に、[テナント間アクセスの概要](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview#important-considerations)の[重要な考慮事項に関する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)セクションを確認してください。
- すべての外部 Microsoft Entra 組織に適用する既定のアクセスのレベルを決定します。
- カスタマイズが必要な Microsoft Entra 組織を特定します。
- B2B 直接接続を設定する組織に連絡してください。 B2B 直接接続は相互の信頼によって確立されるため、自組織と相手組織の両方が、クロステナント アクセス設定で互いとの B2B 直接接続を有効にする必要があります。
- 外部組織から必要な情報を取得します。 外部組織の特定のユーザー、グループ、またはアプリケーションにアクセス設定を適用する場合は、アクセス設定を構成する前に、その組織からこれらの ID を取得する必要があります。
- Microsoft Entra 管理センターでテナント間アクセス設定を構成するには、少なくとも [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロールを持つアカウントが必要です。 Teams 管理者は、クロステナント アクセス設定を読み取ることはできますが、これらの設定を更新することはできません。

### 既定の設定を構成する

既定のクロステナント アクセス設定は、組織固有のカスタマイズした設定を作成していない、すべての外部組織に適用されます。 Microsoft Entra ID が提供する既定の設定を変更したい場合は、以下の手順に従います。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**外部アイデンティティ**&gt;**テナント間のアクセス設定**に移動します。
3. [ **既定の設定** ] タブを選択し、概要ページを確認します。

    [Image: [クロステナント アクセス設定] の [既定の設定] タブを示すスクリーンショット]
4. 設定を変更するには、[ **受信の既定値の編集]** リンクまたは [ **送信の既定値の編集] リンクを** 選択します。

    [Image: 既定の設定の編集ボタンを示すスクリーンショット]
5. 以下のセクションの詳細な手順に従って、既定の設定を変更します。

    - 受信アクセス設定を変更する
    - 送信アクセス設定を変更する

### 組織を追加する

以下の手順に従って、特定の組織向けにカスタマイズした設定を構成します。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**外部アイデンティティ**&gt;**テナント間のアクセス設定**に移動します。
3. [ **組織の設定] を選択します**。
4. [ **組織の追加] を選択します**。
5. [ **組織の追加** ] ウィンドウで、組織の完全なドメイン名 (またはテナント ID) を入力します。

    [Image: 組織の追加を示すスクリーンショット]
6. 検索結果で組織を選択し、[ **追加**] を選択します。
7. 組織が [ **組織設定** ] の一覧に表示されます。 この時点で、この組織のすべてのアクセス設定が、既定の設定から継承されます。 この組織の設定を変更するには、[**受信アクセス**] 列または [**送信アクセス**] 列**の下にある [既定から継承**] リンクを選択します。

    [Image: 既定の設定で追加された組織を示すスクリーンショット]
8. 以下のセクションの詳細な手順に従って、組織の設定を変更します。

    - 受信アクセス設定を変更する
    - 送信アクセス設定を変更する

### 受信アクセス設定を変更する

受信設定を使用して、選択した内部アプリケーションに、どの外部ユーザーとグループがアクセスできるかを選択します。 構成しようとしているのが既定の設定であるか、組織固有の設定であるかを問わず、クロステナントの受信アクセス設定を変更するための手順は同一です。 このセクションで説明されているように、[**組織の設定**] タブで **[既定**] タブまたは組織に移動し、変更を加えます。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**外部アイデンティティ**&gt;**テナント間のアクセス設定**に移動します。
3. 変更しようとしている設定に移動します。

    - 既定の受信設定を変更するには、[ **既定の設定** ] タブを選択し、[ **受信アクセス設定**] で [ **受信の既定値の編集]** を選択します。
    - 特定の組織の設定を変更するには、[ **組織の設定** ] タブを選択し、一覧から組織を見つけて (または 追加)、[ **受信アクセス** ] 列でリンクを選択します。
4. 変更しようとしている設定については、以下の詳細な手順に従います。

    - B2Bの受信直接接続設定を変更するには
    - MFA とデバイスの状態の受信信頼設定を変更するには

#### B2B 直接接続の受信設定を変更するには

1. **[B2B 直接接続**] タブを選択する
2. *組織の設定を構成する場合は、* 次のいずれかのオプションを選択します。

    - **既定の設定**: 組織は、[ **既定** の設定] タブで構成された設定を使用します。この組織に対してカスタマイズされた設定が既に構成されている場合は、[ **はい** ] を選択して、すべての設定を既定の設定に置き換えることを確認する必要があります。 次に、[ **保存]** を選択し、この手順の残りの手順をスキップします。
    - **設定のカスタマイズ**: 既定の設定ではなく、この組織に適用する設定をカスタマイズできます。 この手順の残りの部分を続けます。
3. **[外部ユーザーとグループ] を選択します**。
4. [ **アクセスの状態]** で、次のいずれかのオプションを選択します。

    - **アクセスを許可**する: [ **適用** 先] で指定されたユーザーとグループが B2B 直接接続にアクセスできるようにします。
    - **アクセスをブロック**する: [ **適用** 対象] で指定されたユーザーとグループが B2B 直接接続にアクセスできないようにブロックします。 すべての外部ユーザーとグループに対してアクセスをブロックすると、B2B 直接接続を介したすべての内部アプリケーションの共有がブロックされます。

    [Image: b2b 直接接続ユーザーの受信アクセス状態を示すスクリーンショット]
5. [ **適用対象**] で、次のいずれかを選択します。

    - **すべての外部ユーザーとグループ**: **[アクセスの状態** ] で選択したアクションを、外部の Microsoft Entra 組織のすべてのユーザーとグループに適用します。
    - **外部ユーザーとグループの選択**: [ **アクセスの状態** ] で選択したアクションを、外部組織内の特定のユーザーとグループに適用できます。 構成対象のテナントには、Microsoft Entra ID P1 ライセンスが必要です。

    [Image: b2b 直接接続のターゲット ユーザーの選択を示すスクリーンショット]
6. **[外部ユーザーとグループの選択**] を選択した場合は、追加するユーザーまたはグループごとに次の操作を行います。

    - [ **外部ユーザーとグループの追加]** を選択します。
    - [ **他のユーザーとグループの追加** ] ウィンドウで、検索ボックスにユーザー オブジェクト ID またはグループ オブジェクト ID を入力します。
    - 検索ボックスの横にあるメニューで、 **ユーザー** または **グループ**を選択します。
    - **「追加」を選択します**。

    注

    受信の既定設定では、ユーザーまたはグループを対象にすることはできません。

    [Image: 受信 b2b 直接接続用の外部ユーザーの追加を示すスクリーンショット]
7. ユーザーとグループの追加が完了したら、[ **送信]** を選択します。
8. [アプリケーション] タブ **を** 選択します。
9. [ **アクセスの状態]** で、次のいずれかを選択します。

    - **アクセスを許可**する: [適用] で指定されたアプリケーション **に** 、B2B 直接接続ユーザーがアクセスできるようにします。
    - **アクセスをブロック**する: [ **適用** 先] で指定されたアプリケーションが B2B 直接接続ユーザーによってアクセスされないようにブロックします。

    [Image: b2b 直接接続の受信アプリケーションのアクセス状態を示すスクリーンショット]
10. [ **適用対象**] で、次のいずれかを選択します。

    - **すべてのアプリケーション**: **[アクセスの状態** ] で選択したアクションをすべてのアプリケーションに適用します。
    - **アプリケーションの選択** (Microsoft Entra ID P1 または P2 サブスクリプションが必要): [ **アクセスの状態** ] で選択したアクションを組織内の特定のアプリケーションに適用できます。

    [Image: 受信アクセスのアプリケーション ターゲットを示すスクリーンショット]
11. **[アプリケーションの選択**] を選択した場合は、追加するアプリケーションごとに次の操作を行います。

    - [ **Microsoft アプリケーションの追加]** を選択します。
    - アプリケーション ウィンドウで、[検索] ボックスにアプリケーション名を入力し、検索結果でアプリケーションを選択します。
    - アプリケーションの選択が完了したら、[選択] を **選択します**。

    [Image: 受信 b2b 直接接続用のアプリケーションの追加を示すスクリーンショット]
12. **[保存] を選択します**。

#### MFA およびデバイスの状態に関する受信の信頼の設定を変更するには

1. [ **信頼** 設定] タブを選択します。
2. *組織の設定を構成する場合は*、次のいずれかのオプションを選択します。

    - **既定の設定**: 組織は、[ **既定** の設定] タブで構成された設定を使用します。この組織に対してカスタマイズされた設定が既に構成されている場合は、[ **はい** ] を選択して、すべての設定を既定の設定に置き換えることを確認する必要があります。 次に、[ **保存]** を選択し、この手順の残りの手順をスキップします。
    - **設定のカスタマイズ**: 既定の設定ではなく、この組織に適用する設定をカスタマイズできます。 この手順の残りの部分を続けます。
3. 次のオプションの中から、1つまたは複数選択します。

    - **Microsoft Entra テナントからの多要素認証を信頼する**: 条件付きアクセス ポリシーで多要素認証 (MFA) が必要な場合は、このチェック ボックスをオンにします。 この設定では、条件付きアクセス ポリシーで外部組織からの MFA クレームを信頼できるようにします。 認証中に、Microsoft Entra ID はユーザーが MFA を完了したことを示すクレームをユーザー資格情報で確認します。 それ以外の場合は、ユーザーのホーム テナントで MFA チャレンジが開始されます。
    - **準拠デバイスの信頼**: ユーザーがリソースにアクセスするときに、Microsoft Entra IDが外部Microsoft Entra組織からの準拠デバイス要求を信頼できるようにします。 この設定を有効にすると、外部ユーザーは、自宅組織によって評価されたコンプライアンス状態を使用して、準拠デバイスを必要とする条件付きアクセス ポリシーを満たすことができます。

        外部ユーザーがサインインすると、Microsoft Entra IDはユーザーのホーム テナントからデバイス コンプライアンス要求を受け取ることができます。 **信頼に準拠しているデバイス**が有効になっている場合、Microsoft Entra IDは要求を受け入れます。 準拠デバイスを必要とする条件付きアクセス ポリシーは、外部組織によって実行されたコンプライアンス評価に基づいて正常に評価できます。

        この設定は、信頼できるデバイス コンプライアンス ポリシーを持つ組織に対してのみ有効にします。 テナントは、外部組織のデバイス管理およびコンプライアンス ソリューションによって実行されるコンプライアンス評価に依存します。

        重要

        **信頼に準拠しているデバイス**が有効になっていない場合、Microsoft Entra IDは外部ユーザーのホーム テナントからの準拠デバイス要求を信頼しません。 その結果、外部ユーザーは、自分のデバイスが自宅組織に準拠している場合でも、準拠しているデバイスを必要とする条件付きアクセス ポリシーに失敗する可能性があります。 たとえば、準拠している iOS、Android、Windows、または macOS デバイスからリソースにアクセスするゲスト ユーザーは、条件付きアクセス ポリシーでデバイスのコンプライアンスが必要であり、**信頼に準拠しているデバイス**がユーザーのホーム テナントで有効になっていない場合にブロックされる可能性があります。
    - **Microsoft Entra ハイブリッド参加済みデバイスを信頼**する: 条件付きアクセス ポリシーが、ユーザーがリソースにアクセスするときに外部組織からの Microsoft Entra ハイブリッド参加済みデバイス要求を信頼できるようにします。

    [Image: 受信信頼設定を示すスクリーンショット。]
4. (この手順は **組織の設定** にのみ適用されます)。 **自動引き換え** オプションを確認します。

    - **テナントと一緒に招待を自動で受け取ります**&lt;tenant&gt;: 招待を自動的に受け取りたい場合は、この設定をオンにします。 その場合、指定されたテナントのユーザーは、このテナントにテナント間同期、B2B コラボレーション、または B2B 直接接続を使用して初めてアクセスする際に、同意プロンプトを承認する必要がなくなります。 この設定では、指定したテナントが、この設定で送信アクセスも確認する場合にのみ同意プロンプトが表示されなくなります。

    [Image: [受信自動引き換え] チェックボックスを示すスクリーンショット。]
5. **[保存] を選択します**。

注

組織の設定を構成すると、[ **クロステナント同期** ] タブが表示されます。このタブは、B2B 直接接続の構成には適用されません。 代わりに、この機能はマルチテナント組織がテナント間で B2B コラボレーションを有効にするために使用されます。 詳細については、 [マルチテナント組織のドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/)。

### 送信アクセス設定を変更する

送信設定を使用して、選択した外部アプリケーションに、お客様のどのユーザーとグループがアクセスできるかを選択します。 構成しようとしているのが既定の設定であるか、組織固有の設定であるかを問わず、クロステナント送信アクセス設定を変更する詳細な手順は同一です。 このセクションで説明されているように、[**組織の設定**] タブで **[既定**] タブまたは組織に移動し、変更を加えます。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**外部アイデンティティ**&gt;**テナント間のアクセス設定**に移動します。
3. 変更しようとしている設定に移動します。

    - 既定の送信設定を変更するには、[ **既定の設定** ] タブを選択し、[ **送信アクセス設定**] で [ **送信の既定値の編集]** を選択します。
    - 特定の組織の設定を変更するには、[ **組織の設定** ] タブを選択し、一覧から組織を見つけて (または 追加)、[ **送信アクセス** ] 列でリンクを選択します。

#### 送信アクセスの設定を変更するには

1. **[B2B 直接接続**] タブを選択します。
2. *組織の設定を構成する場合は、* 次のいずれかのオプションを選択します。

    - **既定の設定**: 組織は、[ **既定** の設定] タブで構成された設定を使用します。この組織に対してカスタマイズされた設定が既に構成されている場合は、[ **はい** ] を選択して、すべての設定を既定の設定に置き換えることを確認する必要があります。 次に、[ **保存]** を選択し、この手順の残りの手順をスキップします。
    - **設定のカスタマイズ**: この組織の設定をカスタマイズできます。この設定は、既定の設定ではなく、この組織に適用されます。 この手順の残りの部分を続けます。
3. [ **ユーザーとグループ] を選択します**。
4. [ **アクセスの状態]** で、次のいずれかを選択します。

    - **アクセスを許可**する: [ **適用** 先] で指定したユーザーとグループが B2B 直接接続にアクセスできるようにします。
    - **アクセスをブロック**する: [ **適用** 対象] で指定されたユーザーとグループが B2B 直接接続にアクセスできないようにブロックします。 すべてのユーザーとグループに対してアクセスをブロックすると、B2B 直接接続を介したすべての外部アプリケーションの共有がブロックされます。

    [Image: b2b 直接接続の発信用ユーザーとグループのアクセス状態を示すスクリーンショット]
5. [ **適用対象**] で、次のいずれかを選択します。

    - **すべての &lt;組織&gt; ユーザー**: **[アクセスの状態** ] で選択したアクションを、すべてのユーザーとグループに適用します。
    - **組織&lt;ユーザーとグループ&gt;選択**します (Microsoft Entra ID P1 または P2 サブスクリプションが必要): [**アクセスの状態**] で選択したアクションを特定のユーザーとグループに適用できます。

    スクリーンショットは、b2b の直接接続送信アクセスにおけるターゲットユーザーの選択を示しています。
6. [ **組織 &lt;選択&gt; ユーザーとグループを選択**した場合は、追加するユーザーまたはグループごとに次の操作を行います。

    - [**組織&lt;ユーザーとグループ&gt;追加**] を選択します。
    - **[選択**] ウィンドウで、検索ボックスにユーザー名またはグループ名を入力します。
    - ユーザーとグループの選択が完了したら、[選択] を **選択します**。

    注

    ユーザーとグループを対象にすると、 [SMS ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-sms-signin)を構成したユーザーを選択することはできません。 これは、外部ユーザーが送信アクセス設定に追加されないように、ユーザー オブジェクトに "フェデレーション資格情報" を持つユーザーがブロックされるためです。 回避策として、 [Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/crosstenantaccesspolicy-overview) を使用して、ユーザーのオブジェクト ID を直接追加するか、ユーザーが属するグループをターゲットにすることができます。
7. **[保存] を選択します**。
8. [ **外部アプリケーション** ] タブを選択します。
9. [ **アクセスの状態]** で、次のいずれかを選択します。

    - **アクセスを許可**する: [適用] で指定されたアプリケーション **に** 、B2B 直接接続ユーザーがアクセスできるようにします。
    - **アクセスをブロック**する: [ **適用** 先] で指定されたアプリケーションが B2B 直接接続ユーザーによってアクセスされないようにブロックします。

    [Image: アウトバウンドb2b直接接続のアプリケーションアクセス状態を示すスクリーンショット]
10. [ **適用対象**] で、次のいずれかを選択します。

    - **すべての外部アプリケーション**: **[アクセスの状態** ] で選択したアクションをすべての外部アプリケーションに適用します。
    - **アプリケーションの選択** (Microsoft Entra ID P1 または P2 サブスクリプションが必要): **[アクセスの状態** ] で選択したアクションを特定の外部アプリケーションに適用できます。

    [Image: 送信される B2B 直接接続のアプリケーションターゲットを示すスクリーンショット]
11. **[外部アプリケーションの選択**] を選択した場合は、追加するアプリケーションごとに次の操作を行います。

    - [ **Microsoft アプリケーションの追加]** または **[他のアプリケーションの追加] を選択します**。
    - アプリケーション ウィンドウで、[検索] ボックスにアプリケーション名を入力し、検索結果でアプリケーションを選択します。
    - アプリケーションの選択が完了したら、[選択] を **選択します**。

    [Image: 送信 B2B 直接接続の外部アプリケーションの追加を示すスクリーンショット。]
12. **[保存] を選択します**。

#### 送信の信頼の設定を変更するには

(このセクションは **組織の設定** にのみ適用されます)。

1. [ **信頼** 設定] タブを選択します。
2. **自動引き換え**オプションを確認します。

    - **テナントと一緒に招待を自動で受け取ります**&lt;tenant&gt;: 招待を自動的に受け取りたい場合は、この設定をオンにします。 その場合、このテナントのユーザーは、指定されたテナントにテナント間同期、B2B コラボレーション、または B2B 直接接続を使用して初めてアクセスする際に、同意プロンプトを承認する必要がなくなります。 この設定では、指定したテナントが、この設定で受信アクセスも確認する場合にのみ同意プロンプトが表示されなくなります。

    [Image: [送信自動引き換え] チェックボックスを示すスクリーンショット。]
3. **[保存] を選択します**。

### 組織を削除する

組織の設定から組織を削除すると、その組織で、既定のクロステナント アクセス設定が有効になります。

注

組織が組織のクラウド サービス プロバイダーである場合 (Microsoft Graph [パートナー固有の構成](https://learn.microsoft.com/ja-jp/graph/api/resources/crosstenantaccesspolicyconfigurationpartner) の isServiceProvider プロパティが true の場合)、組織を削除することはできません。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。
2. **Entra ID**&gt;**外部アイデンティティ**&gt;**テナント間のアクセス設定**に移動します。
3. [ **組織の設定** ] タブを選択します。
4. 一覧で組織を見つけ、その行のごみ箱アイコンを選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/current-limitations"} -->
## B2B コラボレーションの制限 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/current-limitations
- Service: entra-external-id / external
- Article date: 2026-04-24
- Summary: Microsoft Entra B2B コラボレーションの現在の制限事項

現在、Microsoft Entra B2B コラボレーションには、この記事に記載されている制限が適用されます。

### 可能な二重多要素認証

Microsoft Entra B2B を使用すると、リソース組織 (招待元組織) で多要素認証を適用できます。 このアプローチの理由については、「[B2B コラボレーション ユーザーの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access)」を参照してください。 パートナーが既に多要素認証を設定して適用している場合、ユーザーはホーム組織で 1 回、もう一度自分の組織で認証を実行する必要があります。

### クイック起動

B2B コラボレーションのフローでは、ユーザーをディレクトリに追加し、招待の使用、アプリ割り当てなどの際に動的に更新します。 更新と書き込みは、通常、1 つのディレクトリ インスタンスで行い、すべてのインスタンス間でレプリケートする必要があります。 すべてのインスタンスが更新されると、レプリケーションが完了します。 いずれかのインスタンスでオブジェクトの書き込みまたは更新が行われ、そのオブジェクトを取得する呼び出しが別のインスタンスに対して行われた場合は、レプリケーションの遅延が発生する可能性があります。 その場合は、更新または再試行が役立つことがあります。 API を使用してアプリを記述する場合は、バックオフの再試行がこの問題を軽減するための推奨される防御的な方法です。

### Microsoft Entra ディレクトリ

Microsoft Entra B2B には、Microsoft Entra サービス ディレクトリの制限適用されます。 ユーザーが作成できるディレクトリ数と、ユーザーまたはゲスト ユーザーが所属できるディレクトリ数の詳細については、「[Microsoft Entra サービスの制限と制約](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers"} -->
## 外部テナントにおける Microsoft Entra 外部 ID のドキュメント - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers
- Service: entra-external-id / external
- Article date: 2025-02-06
- Summary: Microsoft Entra 外部 ID は顧客 ID アクセス管理 (CIAM) ソリューションであり、外部向けのアプリやサービスに対してセキュリティで保護されたカスタマイズされたサインイン エクスペリエンスを作成できます。

Microsoft Entra 外部 ID は顧客 ID アクセス管理 (CIAM) ソリューションであり、外部向けのアプリやサービスに対してセキュリティで保護されたカスタマイズされたサインイン エクスペリエンスを作成できます。

### 外部テナントの Microsoft Entra 外部 ID について

#### 概要

- [Microsoft Entra 外部 ID について](https://learn.microsoft.com/ja-jp/entra/external-id/customers/overview-customers-ciam)
- [よく寄せられる質問](https://learn.microsoft.com/ja-jp/entra/external-id/customers/faq-customers)

#### 概念

- [顧客 ID とアクセス管理の計画](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-planning-your-solution)
- [外部テナントでの機能サポート](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-supported-features-customers)
- [セキュリティとガバナンス](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-security-customers)

### アプリケーションにサインインを追加する

#### 概念

- [認証方法を選択する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach)
- [サインイン方法](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers)
- [ネイティブ認証を使用したサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication)

#### 攻略ガイド

- [外部テナントを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)
- [アプリを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)
- [サインアップとサインインフローを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)
- [アプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)

### サインイン エクスペリエンスをカスタマイズする

#### 概念

- [サインインの外観をカスタマイズする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-branding-customers)

#### 攻略ガイド

- [サインイン ページのブランド化をカスタマイズする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers)
- [サインイン ページの言語をカスタマイズする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-languages-customers)
- [サインアップ時にユーザー情報を収集する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes)
- [トークンにユーザー属性を追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-attributes-to-token)

### サンプルの開発ガイド

#### sample

- [JavaScript SPA サインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/sample-single-page-app-vanillajs-sign-in)
- [React SPA サインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/sample-single-page-app-react-sign-in)
- [Node.js Web アプリのサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/sample-web-app-node-sign-in)
- [Web アプリ Node.js サインインして API を呼び出す](https://learn.microsoft.com/ja-jp/entra/external-id/customers/sample-web-app-node-sign-in-call-api)
- [ASP.NET Web アプリのサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/sample-web-app-dotnet-sign-in)
- [その他のコード サンプル ガイド](https://learn.microsoft.com/ja-jp/entra/external-id/customers/samples-ciam-all)

### アプリのビルドと統合に関するガイド

#### チュートリアル

- [Vanilla JS SPA](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-javascript-prepare-app)
- [React SPA](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-react-prepare-app)
- [Node.js Web アプリ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-web-app-node-sign-in-prepare-tenant)
- [ASP.NET Web アプリ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-web-app-dotnet-sign-in-prepare-tenant)
- [ASP.NET Web API をセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-protect-web-api-dotnet-core-build-app)
- [その他のビルドと統合ガイド](https://learn.microsoft.com/ja-jp/entra/external-id/customers/samples-ciam-all)

### アプリの種類と言語/プラットフォーム別のサンプル

#### 概要

- [アプリの種類別のコード サンプル ガイド](https://learn.microsoft.com/ja-jp/entra/external-id/customers/samples-ciam-all?tabs=apptype)
- [n言語別のコード サンプル ガイド](https://learn.microsoft.com/ja-jp/entra/external-id/customers/samples-ciam-all?tabs=language)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/azure-rest-api-operations-tenant-management"} -->
## Azure REST API を使用したテナント管理 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/azure-rest-api-operations-tenant-management
- Service: entra-external-id / external
- Article date: 2026-04-24
- Summary: Azure REST API を呼び出して外部テナントを管理する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Azure REST API を使用して外部テナントを管理できます。 テナント管理リソースは、次の API 操作をサポートします。 次のセクションの各リンクは、その操作の Azure REST API リファレンスの対応するページを対象とします。

### テナント管理操作

次の操作を使用して、外部テナントのテナント管理操作を実行できます。

- [作成または更新](https://learn.microsoft.com/ja-jp/rest/api/activedirectory/ciam-tenants/create)
- [削除](https://learn.microsoft.com/ja-jp/rest/api/activedirectory/ciam-tenants/delete)
- [Get](https://learn.microsoft.com/ja-jp/rest/api/activedirectory/ciam-tenants/get)
- [Resource Group 別のリスト](https://learn.microsoft.com/ja-jp/rest/api/activedirectory/ciam-tenants/list-by-resource-group)
- [サブスクリプション別のリスト](https://learn.microsoft.com/ja-jp/rest/api/activedirectory/ciam-tenants/list-by-subscription)
- [更新](https://learn.microsoft.com/ja-jp/rest/api/activedirectory/ciam-tenants/update)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/concept-authentication-methods-customers"} -->
## 外部テナントのための ID プロバイダー - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers
- Service: entra-external-id / external
- Article date: 2026-03-27
- Summary: 電子メール、ワンタイム パスコード、ソーシャル プロバイダー、SAML/WS-Fed、OIDC など、顧客 ID とアクセス管理 (CIAM) のサインインと MFA のオプションについて説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Tip

この記事は、外部テナントでの外部 ID に関するものです。 従業員テナントについては、「[従業員テナントでの外部 ID の ID プロバイダー](https://learn.microsoft.com/ja-jp/entra/external-id/identity-providers)」をご覧ください。

Microsoft Entra 外部 IDを使用すると、コンシューマー向けアプリとビジネス用の顧客向けアプリ用に、セキュリティで保護されたカスタマイズされたサインイン エクスペリエンスを作成できます。 外部テナントでは、ユーザーがアプリにサインアップする方法が複数あります。 アカウントを作成するには、メールアドレスと、パスワードかワンタイム パスコードを使用します。 または、Facebook、Google、Apple、Microsoft Entra ID テナント、またはカスタム OIDC または SAML/WS-Fed ID プロバイダー (IdP) でサインインを有効にした場合、ユーザーは外部 ID プロバイダーの資格情報を使用してサインインできます。 ユーザー オブジェクトは、サインアップ時に収集された ID 情報を使用してディレクトリに作成されます。

Note

フェデレーション ID プロバイダー (Facebook、Google、Apple、Microsoft Entra ID、カスタム OIDC または SAML/WS-Fed プロバイダー) は、**ブラウザー委任認証**でのみ使用できます。 **ネイティブ認証** では、ローカル アカウントの方法 (ワンタイム パスコード (OTP) の電子メールとパスワード付きの電子メール) のみがサポートされます。 アプリでソーシャル サインインまたはフェデレーション サインインが必要な場合は、ブラウザー委任認証を使用します。 詳細については、「 [認証方法の選択」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach)参照してください。

この記事では、外部テナント内のアプリにサインアップしてサインインするときにプライマリ認証に使用できる ID プロバイダーについて説明します。 また、セキュリティを強化するために多要素認証 (MFA) ポリシーを適用して、ユーザーがサインインするたびに第 2 の形態での確認を要求することもできます ([詳細については、こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers))。

### メールとパスワードのサインイン

ローカル アカウント ID プロバイダーの設定では、メール アドレスでのサインアップが既定で有効になっています。 電子メール オプションを使用すると、ユーザーは自分のメール アドレスとパスワードでサインアップしてサインインできます。

- **サインアップ**: ユーザーは電子メール アドレスの入力を求められます。電子メール アドレスは、サインアップ時にワンタイム パスコードで確認されます。 ユーザーは、サインアップ ページで要求されたその他の情報 (表示名、名前、姓など) を入力します。 次に、 [続行] を選択してアカウントを作成します。
- **サインイン**: ユーザーはサインアップしてアカウントを作成した後、メール アドレスとパスワードを入力してサインインできます。
- **パスワードのリセット**: 電子メールとパスワードのサインインを有効にすると、パスワードのリセット リンクがパスワード ページに表示されます。 ユーザーが自分のパスワードを忘れた場合、このリンクを選択すると、電子メール アドレスにワンタイム パスコードが送信されます。 確認後、ユーザーは新しいパスワードを選択できます。

    [Image: ローカル アカウントのサインアップとサインイン中に表示される電子メールとパスワードの画面。]

[サインアップとサインインのユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers#create-and-customize-a-user-flow)場合、**[パスワード付きメール]** が既定のオプションです。

### ユーザー名またはエイリアスのサインイン (プレビュー)

ローカル アカウント (メールとパスワード) を使用してサインインするユーザーが、メール アドレスに加えて [エイリアスまたはユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias) でサインインできるようにすることができます。 これにより、ユーザーは自分の電子メール アドレスまたは代替識別子、またはその両方を使用して認証できます。 代替識別子には、顧客 ID、メンバーシップ ID、保険番号、またはフリークエント チラシ番号、またはユーザー名として使用する類似の ID を指定できます。

ユーザー名のサインインを有効にすると、ユーザーは自分のメール アドレスまたはユーザー名でサインインすることを選択できます。 ユーザー名を使用してサインインすることを選択した場合は、電子メールとパスワードのサインインと同様に、パスワードの入力を求められます。 [パスワード リセットを有効に](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers#to-customize-self-service-password-reset)した場合、ユーザーはサインイン ページでパスワード リセット リンクを選択してパスワードをリセットできます。

[Image: サインイン ページのユーザー名サインイン オプション。]

### メールでワンタイムパスコード付きサインイン

ワンタイム パスコード付きメールは、ローカル アカウント ID プロバイダー設定のオプションです。 このオプションを使用すると、ユーザーはサインインするたびに、保存されているパスワードではなく一時的なパスコードでサインインします。

- **サインアップ**: ユーザーは自分のメール アドレスでサインアップし、電子メール アドレスに送信される一時的なコードを要求できます。 その後は、このコードを入力してサインインを続けます。
- **サインイン**: ユーザーがサインアップしてアカウントを作成すると、サインインするたびにメール アドレスを入力し、一時的なパスコードを受け取ります。

    [Image: サインアップとサインイン時に表示されるワンタイム パスコード画面を電子メールで送信します。]

サインイン ページでセルフサービス パスワード リセットのリンクを表示、非表示、またはカスタマイズするためのオプションも構成できます ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers#to-customize-self-service-password-reset))。

[サインアップとサインインのユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers#create-and-customize-a-user-flow)場合、**[メールのワンタイム パスコード]** はローカル アカウントのオプションの 1 つです。

### ソーシャル ID プロバイダー: Facebook、Google、Apple

最適なサインイン エクスペリエンスを実現するには、可能な限りソーシャル ID プロバイダーとフェデレーションして、ユーザーにシームレスなサインアップとサインインエクスペリエンスを提供できるようにします。 外部テナントでは、ユーザーが自分の Facebook、Google、または Apple アカウントを使用してサインアップしてサインインすることを許可できます。

ソーシャル ID プロバイダーを有効にすると、ユーザーはサインアップ ページで使用可能にするソーシャル ID プロバイダーのオプションから選択できます。 外部テナントでソーシャル ID プロバイダーを設定するには、その ID プロバイダーでアプリケーションを作成し、資格情報を構成します。 クライアントまたはアプリ ID、クライアントまたはアプリ シークレット、または証明書を取得します。証明書を使用して外部テナントを構成できます。

#### Google サインイン

Google とのフェデレーションを設定すると、ユーザーが自分の Gmail アカウントを使用してアプリケーションにサインインできるようになります。 アプリケーションのサインイン オプションの 1 つとして Google を追加した後、サインイン ページで、ユーザーは Google アカウントを使用してMicrosoft Entra 外部 IDにサインインできます。

次のスクリーンショットは、Google エクスペリエンスでのサインインを示しています。 サインイン ページで、ユーザーは **[Google でサインイン**] を選択します。 その時点で、ユーザーは Google ID プロバイダーにリダイレクトされ、サインインが完了します。

[Image: ユーザーが [Google でサインイン] を選択した後の Google サインイン フロー。]

[ID プロバイダーとして Google を追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers)方法に関するページを確認してください。

#### Facebook サインイン

Facebook とのフェデレーションを設定すると、ユーザーが自分の Facebook アカウントを使用してアプリケーションにサインインできるようになります。 アプリケーションのサインイン オプションの 1 つとして Facebook を追加した後、サインイン ページで、ユーザーは Facebook アカウントを使用してMicrosoft Entra 外部 IDにサインインできます。

次のスクリーンショットは、Facebook エクスペリエンスでのサインインを示しています。 サインイン ページで、ユーザーは **[Facebook でのサインイン**] を選択します。 その後、ユーザーは Facebook ID プロバイダーにリダイレクトされ、サインインが完了します。

[Image: ユーザーが [Facebook でサインイン] を選択した後の Facebook サインイン フロー。]

[ID プロバイダーとして Facebook を追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-facebook-federation-customers)方法に関するページを確認してください。

#### Apple サインイン

Apple とのフェデレーションを設定すると、ユーザーが自分の Apple アカウントを使用してアプリケーションにサインインできるようになります。 アプリケーションのサインイン オプションの 1 つとして Apple を追加した後、サインイン ページで、ユーザーは Apple アカウントを使用してMicrosoft Entra 外部 IDにサインインできます。

次のスクリーンショットは、Apple エクスペリエンスでのサインインを示しています。 サインイン ページで、ユーザーは **[Apple でのサインイン**] を選択します。 その後、ユーザーは Apple ID プロバイダーにリダイレクトされ、サインインが完了します。 Apple を ID プロバイダーとして追加 方法について説明します。

### Microsoft Entra ID フェデレーション

Microsoft Entra ID テナントとの OpenID Connect (OIDC) フェデレーションを設定すると、そのテナントのユーザーが既存の組織アカウントを使用してアプリケーションにサインアップしてサインインできるようになります。 この方法では、カスタム OIDC ID プロバイダー機能を使用して、Microsoft Entra ID テナントとフェデレーションします。

OIDC ID プロバイダーとして[Microsoft Entra ID テナントを追加する方法](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-entra-id-federation-customers)について説明します。

### カスタム OIDC ID プロバイダー

カスタム OpenID Connect (OIDC) ID プロバイダーを設定して、ユーザーが外部 ID プロバイダーの資格情報を使用してアプリケーションにサインアップしてサインインできるようにすることができます。 OIDC プロトコルを使用して、Azure AD B2C テナントとサインインフローとサインアップ フローをフェデレーションすることもできます。 フェデレーションではなく Azure AD B2C から移行する場合は、「[AZURE AD B2C から外部 ID への移行を計画する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/plan-your-migration-from-b2c-to-external-id)を参照してください。

カスタム OIDC ID プロバイダー設定する方法について説明します。

### カスタム SAML/WS-Fed ID プロバイダー

SAML または WS-Fed ID プロバイダーを設定して、ユーザーが ID プロバイダーで自分のアカウントを使用してアプリケーションにサインアップしてサインインできるようにすることができます。 ユーザーは、サインアップまたはサインインオプションを選択して **サインアップ** または **サインイン** できます。 ID プロバイダーにリダイレクトされ、正常にサインインするとMicrosoft Entraに戻ります。 外部テナントの場合、ユーザーのサインイン電子メールは、SAML フェデレーション中に設定された定義済みのドメインと一致する必要はありません。 その結果、ドメインを追加、変更、または削除してフェデレーションセットアップを更新しても、既存のユーザーのエクスペリエンスには影響しません。

いずれかの外部 ID プロバイダーの定義済みドメインと一致する電子メール アドレスをサインイン ページに入力したユーザーは、その ID プロバイダーで認証するようにリダイレクトされます。 アカウントがない場合は、追加の詳細を求められる場合があり、アカウントが作成されます。

詳細については、「 [SAML/WS-Fed ID プロバイダー」を](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation-overview)参照してください。 詳細なセットアップ手順については、「 [SAML/WS-Fed ID プロバイダーとのフェデレーションを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation)」を参照してください。

#### ドメインアクセラレーション

カスタム SAML/WS-Fed IdP とフェデレーションする場合、通常、ユーザーには最初に Microsoft サインイン ページが表示され、次に ID プロバイダーが選択されます。 これらの IdP は、1 つ以上のドメインに関連付けることができます。 サインイン URL に `domain_hint` パラメーターを含めると、ユーザーは指定されたドメインに関連付けられている ID プロバイダーのサインイン ページに直接移動できます。

カスタム SAML ID プロバイダーの場合は、構文の [`domain_hint`] フィールドで指定されたドメインを使用します。`domain_hint=<domain name of federating idp>`

[Image: SAML 構成でのdomain_hintに使用されるフェデレーション ID プロバイダーのドメイン名。]

### 発行者アクセラレーション

Facebook、Google、Apple、カスタム OpenID Connect IdP などの他の外部 ID プロバイダーとフェデレーションすると、通常、ユーザーは最初にMicrosoftサインイン ページを表示し、その ID プロバイダーを選択します。 サインイン URL に `domain_hint` パラメーターを含めると、ユーザーは指定されたドメインに関連付けられている ID プロバイダーのサインイン ページに直接移動できます。

次の `domain_hint` 値を使用して、これらの ID プロバイダーのサインイン ページに直接移動できます。

- **Facebook**: `domain_hint=facebook`。
- **Google**: `domain_hint=google`。
- **Apple**: `domain_hint=apple`。
- **カスタム OIDC**: `domain_hint=<issuer URI>`。 カスタム OIDC ID プロバイダーの場合は、 構文の `domain_hint` のドメイン部分 (LinkedIn の `"www.linkedin.com"` など) を使用します。
- **Custom OIDC - Entra ID**: カスタム OIDC Entra ID プロバイダーの場合は、`domain_hint=contoso.onmicrosoft.com` などのEntra ID テナントのドメイン名を使用します。

    [Image: カスタム OpenID Connect 構成のdomain_hintに使用される発行者 URI ドメイン セグメント。]

### サインイン方法の更新

アプリのサインイン オプションはいつでも更新できます。 たとえば、ソーシャル ID プロバイダーを追加したり、ローカル アカウントのサインイン方法を変更したりすることができます。

サインイン方法を変更すると、変更は新しいユーザーにのみ影響します。 既存のユーザーは、元の方法を使用して引き続きサインインします。 たとえば、メールとパスワードのサインイン方法から始めて、後にワンタイム パスコードを使用したメールに変更するとします。 新しいユーザーはワンタイム パスコードを使用してサインインしますが、メールとパスワードで既にサインアップしているユーザーは、引き続きメールとパスワードの入力を求められます。

### Microsoft Graph API

Microsoft Entra 外部 IDで ID プロバイダーと認証方法を管理するために、次の Microsoft Graph API操作がサポートされています。

- サポート対象の ID プロバイダーと認証方法を特定するには、[List availableProviderTypes](https://learn.microsoft.com/ja-jp/graph/api/identityproviderbase-availableprovidertypes) API を呼び出します。
- テナントで既に構成され有効になっている ID プロバイダーと認証方法を特定するには、[List identityProviders](https://learn.microsoft.com/ja-jp/graph/api/identitycontainer-list-identityproviders) API を呼び出します。
- サポート対象の ID プロバイダーまたは認証方法を有効にするには、[Create identityProvider](https://learn.microsoft.com/ja-jp/graph/api/identitycontainer-post-identityproviders) API を呼び出します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/concept-branding-customers"} -->
## 会社のブランド化をカスタマイズする - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-branding-customers
- Service: entra-external-id / external
- Article date: 2025-01-07
- Summary: 顧客のサインインとサインアップのエクスペリエンスをカスタマイズする方法について説明します。

Note

この記事で説明するブランド化のカスタマイズは、ユーザーがMicrosoftホスト型サインイン ページを使用してサインインする**ブラウザー委任認証**に適用されます。 [ネイティブ認証](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach)を使用する場合は、アプリでサインイン UI を直接ビルドして制御します。そのため、ブランド化はアプリケーション コードで管理されます。

新しい外部テナントを作成した後、サインイン、サインアップ、またはサインアウトする顧客向けに Web ベース アプリケーションの外観をカスタマイズして、そのエンドユーザー エクスペリエンスをパーソナル化できます。 外部テナントには、Microsoft の既存のブランド化を含まない既定の中立的なブランド化が付属しています。 ただし、この中立的な既定のブランド化は、会社の特定のニーズを満たすようにカスタマイズできます。 カスタムの背景画像または色、ファビコン、レイアウト、ヘッダー、フッターを、認証エクスペリエンスに柔軟に追加できます。 各カスタム ブランド化プロパティをカスタム サインイン ページに個別に追加したり、カスタム CSS をアップロードしたりできます。 詳しくは、「[外部テナントで中立的なブランドをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers)」をご覧ください。

会社のカスタム ブランド化の読み込みが失敗した場合、サインイン ページは中立的ブランド化に戻ります。

次の一覧と画像は、中立的ブランド化サインイン エクスペリエンスの要素の概要です。

1. 背景画像と色。
2. ファビコン。
3. バナー ロゴ。
4. ページ レイアウト要素としてのフッター。
5. フッターのハイパーリンク (プライバシーと Cookie、利用規約、トラブルシューティングの詳細など、画面の右下隅の省略記号とも呼ばれるもの)。

    [Image: 中立的ブランド化のスクリーンショット。]

比較のため、Microsoft Entra ID テナントでの[既定の Microsoft サインイン エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding)の外観を次に示します。

[Image: Microsoft Entra ID の既定の Microsoft ブランドのスクリーンショット。]

### テキストのカスタマイズ

サインアップとサインインの際に収集する必要がある情報の要件が異なる場合があります。 外部テナントには、名、姓、市区町村、郵便番号など、属性に格納されている一連の組み込み情報が用意されています。 外部テナントには、サインアップとサインインのエクスペリエンスにカスタム テキストを追加するための 2 つのオプションがあります。 この機能は、言語のカスタマイズ中の各ユーザー フロー、さらに**会社のブランド化**でも使用できます。 文字列をカスタマイズする方法は 2 つありますが、どちらの方法でも同じ JSON ファイルが変更されます。 **ユーザー フロー**または**会社のブランド化**のいずれかで行われた最新の変更が、常に前の変更をオーバーライドします。

### 言語のカスタマイズ

ブランド化要素をカスタマイズすることで、特定のブラウザー言語を使用してサインインするユーザー向けにパーソナライズされたサインイン エクスペリエンスを作成できます。 要素に変更を加えない場合は、既定の要素が表示されます。 テナントでは、Company Branding のサインイン エクスペリエンスにカスタム言語を追加したり、ユーザー フロー特定のユーザー フローに追加したりできます。 言語のカスタマイズは、言語の一覧に対して使用できます。 詳細については、「[認証エクスペリエンスの言語をカスタマイズする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-languages-customers)」を参照してください。

### Microsoft Graph API

会社の商標を管理し、すべての資産をプログラムで構成することもできます。

- 既定の商標には、[organizationalBranding リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/organizationalbranding)とそれに関連付けられているメソッドを使用します。
- ロケールに基づいて商標をカスタマイズするには、[organizationalBrandingLocalization リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/organizationalbrandinglocalization)とそれに関連付けられているメソッドを使用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/concept-choose-authentication-approach"} -->
## 認証方法を選択する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach
- Service: entra-external-id / external
- Article date: 2026-04-16
- Summary: Microsoft Entra 外部 IDでのブラウザー委任認証とネイティブ認証を比較し、顧客向けアプリに適したアプローチを選択します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ブラウザー委任認証とネイティブ認証は、Microsoft Entra 外部 IDでの 2 つのサインイン 方法であり、顧客向けアプリによる認証エクスペリエンスの処理方法を定義します。 どちらの方法も完全にサポートされていますが、ユーザー エクスペリエンス、開発作業、セキュリティ モデルが異なります。 これらの違いを理解することは、アプリに最適なアプローチを選択するのに役立ちます。

**browser-delegated authentication** を使用すると、アプリはユーザーをシステム ブラウザーまたは埋め込み Web ビューのMicrosoftホスト型サインイン ページにリダイレクトします。 Microsoft Entraは認証フロー全体を処理し、サインインの完了後にアプリがトークンを受け取ります。 この方法では最小限のコードが必要であり、組み込みのブランド化のカスタマイズが提供されます。

**認証**では、Microsoft Authentication Library (MSAL) SDK またはネイティブ認証 API を使用して、サインイン UI エクスペリエンスをアプリに直接構築します。 ユーザーがアプリを離れることはありません。 UI のすべての側面を制御しますが、チームは認証エクスペリエンスの構築と維持を担当します。

### ブラウザーで委任された認証を使用する場合

ブラウザー委任認証は、次の場合に適しています。

- アプリは、ユーザー エクスペリエンスを中断することなく、サインイン中にブラウザーのリダイレクトに対応できます。
- 実装とメンテナンスの労力を減らした方が好きです。 Microsoftは、セキュリティ更新プログラムと新機能を自動的に管理します。
- コードを少なくして、幅広いプラットフォームと言語をサポートしたいと考えています。

特定の機能の可用性については、 機能の比較を参照してください。

### ネイティブ認証を使用する場合

ネイティブ認証は、次の場合に適しています。

- アプリにシームレスに組み込まれるように、サインイン UI を完全に制御する必要があります。
- ブラウザーのリダイレクトにより、ターゲット プラットフォームのユーザー エクスペリエンスが中断されます。
- 組織はアプリと承認サーバーの両方を運用しており、ユーザーはそれらを 1 つのエンティティとして認識します。
- 開発チームは、追加の実装作業と継続的なメンテナンスに取り組むことができます。

特定の機能の可用性については、 機能の比較を参照してください。

### 機能の比較

次の表に、各アプローチで使用できる機能を示します。

| 特徴 | ブラウザー委任認証 | ネイティブ認証 |
| --- | --- | --- |
| メールのワンタイムパスコード (OTP) を使って登録し、ログインする | ✔️ | ✔️ |
| サインアップして電子メールとパスワードでサインインする | ✔️ | ✔️ |
| メールとパスワードでサインインするには、ユーザー名 (エイリアス) とパスワードを使用できます | ✔️ | ✔️ |
| セルフサービス パスワード リセット (SSPR) | ✔️ | ✔️ |
| カスタム クレーム プロバイダー | ✔️ | ✔️ |
| メールでのワンタイム パスコード (OTP) を使った多要素認証 | ✔️ | ✔️ |
| SMS ワンタイム パスコード (OTP) を使用した多要素認証 | ✔️ | ✔️ |
| ソーシャル ID プロバイダーのサインイン (Apple、Facebook、Google)^1^ | ✔️ | ✔️ |
| シングル サインオン (SSO)^2^ | ✔️ | ✔️ |

^1^ ネイティブ認証の場合でも、ソーシャル サインインでは ID プロバイダーの手順にブラウザー ウィンドウが引き続き使用されます。

^2^ ネイティブ認証では、埋め込み Web ビューに対してのみ SSO がサポートされます。 システム ブラウザーを介したアプリ間 SSO は、ネイティブ認証では使用できません。

### サポートされている言語とフレームワーク

各アプローチでは、次の言語とフレームワークがサポートされています。

| Approach | サポートされている言語とフレームワーク |
| --- | --- |
| ブラウザー委任認証 | - ASP.NET Core<br>- Android (Kotlin、Java)<br>- iOS/macOS (Swift、Objective-C)<br>- JavaScript<br>- React<br>- Angular<br>- Node.js<br>- Python<br>- Java |
| ネイティブ認証 | - Android (Kotlin、Java)<br>- iOS/macOS (Swift、Objective-C)<br>- Web (JavaScript、React、Angular)<br><br> その他の言語とプラットフォームでは、 [ネイティブ認証 API](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api) を使用できます。 |

### セキュリティの考慮事項

ブラウザー委任認証は、より安全なオプションです。 Microsoftはサインイン画面を管理します。これにより、アプリがフィッシング攻撃や資格情報収集攻撃にさらされるのを減らすことができます。

ネイティブ認証を使用すると、開発チームはセキュリティの責任をMicrosoft Entraと共有します。 チームは、ユーザー資格情報を処理するためのセキュリティのベスト プラクティスに従う必要があります。 ネイティブ認証を選択する前に、アプリのビジネス所有者および開発チームとセキュリティへの影響について説明します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/concept-custom-extensions"} -->
## カスタム認証拡張機能 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-extensions
- Service: entra-external-id / external
- Article date: 2025-04-10
- Summary: Microsoft Entra 外部 ID でカスタム認証拡張機能を使用する方法について説明します。 外部システムと統合し、認証フローにカスタム ロジックを追加し、ユーザー エクスペリエンスを強化します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra 外部 ID ユーザー フローは、柔軟性を考慮して設計されています。 サインアップとサインインのユーザー フロー内には、組み込みの認証イベントがあります。 認証フロー内の特定のポイントでカスタム認証拡張機能を追加することもできます。 カスタム認証拡張機能は、基本的にイベント リスナーであり、アクティブ化されると、ワークフロー アクションを定義する REST API エンドポイントへの HTTP 呼び出しを行います。 たとえば、サインアップ時にユーザーが入力する属性を検証する属性収集ワークフローの追加や、カスタム クレーム プロバイダーの使用による、発行前のトークンへの外部ユーザー データの追加を行うことができます。

Note

トークン発行拡張機能 ( **OnTokenIssuanceStart** イベント) は、トークンが発行される直前にサーバー側で実行されるため、ブラウザー委任認証とネイティブ認証の両方に適用されます。 属性コレクション拡張機能 (**OnAttributeCollectionStart** イベントと **OnAttributeCollectionSubmit** イベント) は、Microsoftホスト属性コレクション ページで起動されるため、**ブラウザー委任認証**にのみ適用されます。 方法の選択については、「 [認証方法の選択」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach)参照してください。

構成する必要があるコンポーネントには、カスタム認証拡張機能と REST API の 2 つがあります。 カスタム認証拡張機能によって、REST API エンドポイント、REST API を呼び出すタイミング、REST API を呼び出すための資格情報が指定されます。 カスタム認証拡張機能は、認証フロー内の次のポイントで作成できます。

- サインアップ時、属性の収集の前または後:
    - **OnAttributeCollectionStart** イベントは、属性コレクション ステップの開始時、かつ属性コレクション ページがレンダリングされる前に発生します。
    - **OnAttributeCollectionSubmit** イベントは、ユーザーが属性を入力して送信した後に発生します。
- **OnTokenIssuanceStart** イベントを使用してトークンが発行されるとき。これは、アプリケーションにトークンが発行される直前にトリガーされます。

[Image: 認証フローの拡張ポイントを示す図。]

これらのポイントのいずれかでカスタム認証拡張機能が構成されている場合、定義する REST API への呼び出しを Microsoft Entra ID が実行します。 REST API への要求には、イベント、ユーザー プロファイル、認証要求データ、およびその他のコンテキスト情報に関する情報が含まれています。 さらに、REST API でワークフロー アクションが実行されます。

この記事では、Microsoft Entra 外部 ID のカスタム認証拡張機能の概要を示します。

### 属性収集の開始イベントと送信イベント

カスタム認証拡張機能を使用して、セルフサービス サインアップ ユーザー フローの属性コレクションにワークフローを追加できます。 たとえば、属性フィールドにカスタム値を事前入力したり、ユーザーのエントリを検証したり、属性を変更したり、エラーを表示したりできます。 次の 2 つのイベントが有効になります。

- **OnAttributeCollectionStart** - OnAttributeCollectionStart イベントは、属性コレクション プロセスの開始時、かつ属性コレクション ページがレンダリングされる前に発生します。 このイベントは、ユーザーがドメインに基づいてサインアップできないようにしたり、収集する属性を追加したりするシナリオに使用できます。 OnAttributeCollectionStart イベントに対して、次のシナリオを構成できます。

    - **continueWithDefaultBehavior** - 属性コレクション ページを通常どおりにレンダリングします。
    - **setPreFillValues** - サインアップ フォームの属性を事前入力します。
    - **showBlockPage** - エラー メッセージを表示し、ユーザーのサインアップをブロックします。
- **OnAttributeCollectionSubmit** - OnAttributeCollectionSubmit イベントは、ユーザーが属性を入力して送信した後に発生します。 このイベントは、ユーザーが提供する情報の検証や変更などのシナリオで使用できます。 たとえば、招待コードやパートナー番号を検証したり、アドレス形式を変更したり、エラーを返したりすることができます。

    - **continueWithDefaultBehavior** - サインアップ フローを続行します。
    - **modifyAttributeValues** - サインアップ フォームでユーザーが送信した値を上書きします。
    - **showValidationError** - 送信された値に基づいてエラーを返します。
    - **showBlockPage** - エラー メッセージを表示し、ユーザーのサインアップをブロックします。

属性コレクションの開始イベントと送信イベントを構成するには、カスタム認証拡張機能 REST API を作成します。 イベントが発生すると、Microsoft Entra ID から REST API エンドポイントに HTTP 要求が送信されます。 REST API には、Azure 関数、Azure ロジック アプリ、または別の一般公開されている API エンドポイントを指定できます。 REST API エンドポイントは、実行するワークフロー アクションを定義する役割を担います。

詳細については、「[属性コレクションのカスタム拡張機能をユーザー フローに追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-attribute-collection?context=/entra/external-id/customers/context/customers-context)」を参照してください。

### トークン発行開始イベント

トークン発行開始イベントは、ユーザーがすべての認証チャレンジを完了し、セキュリティ トークンがまもなく発行されるときにトリガーされます。

ユーザーが Microsoft Entra ID を使用してアプリケーションに対して認証を行うと、セキュリティ トークンがアプリケーションに返されます。 セキュリティ トークンには、名前、一意の識別子、アプリケーション ロールなど、ユーザーに関する情報を表すクレームが含まれています。 セキュリティ トークンに含まれるクレームの既定のセット以外に、開発した REST API を使用して外部システムから独自のカスタム クレームを定義できます。

場合によっては、セカンダリ メール、課金レベル、機密情報などの、Microsoft Entra 外部のシステムに主要なデータが格納される場合があります。 外部システムの情報を Microsoft Entra ディレクトリに格納することが可能であるとは限りません。 このようなシナリオでは、カスタム認証拡張機能とカスタム クレーム プロバイダーを使用して、アプリケーションに返されるトークンにこの外部データを追加できます。

トークン発行イベント拡張機能には、次のコンポーネントが含まれます。

- **カスタム クレーム プロバイダー**。 カスタム クレーム プロバイダーは、外部システムからデータを取得するカスタム認証拡張機能の一種です。 アプリケーションに返されるセキュリティ トークンに追加する属性がカスタム クレーム プロバイダーによって指定されます。 複数のクレーム プロバイダーで同じカスタム拡張機能を共有できるため、異なる属性セットを各アプリケーションのセキュリティ トークンに追加できます。
- **REST API エンドポイント**。 イベントが発生すると、Microsoft Entra ID から REST API エンドポイントに HTTP 要求が送信されます。 REST API には、Azure 関数、Azure ロジック アプリ、またはその他の一般公開されている API エンドポイントを指定できます。 REST API エンドポイントは、ダウンストリーム データベース、既存の API、ライトウェイト ディレクトリ アクセス プロトコル (LDAP) ディレクトリ、またはトークン構成に追加する属性を含む他のストアなどの、さまざまなデータ ストアに接続します。

    REST API からは、属性を含む Microsoft Entra ID に HTTP 応答、またはアクションが返されます。 これらの属性は、トークンに自動的には追加されません。 代わりに、任意の属性をトークンに含めるために、アプリケーションの要求マッピング ポリシーを構成する必要があります。

詳細については、次の情報を参照してください。

- [カスタム認証拡張機能について](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview?context=/entra/external-id/customers/context/customers-context)。
- カスタム クレーム プロバイダーを使用して、[トークン発行イベント用にカスタム クレーム プロバイダーを構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration?context=/azure/active-directory/external-identities/customers/context/customers-context)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/concept-custom-url-domain"} -->
## 外部 ID のカスタム URL ドメインの概要 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-url-domain
- Service: entra-external-id / external
- Article date: 2025-09-16
- Summary: カスタム URL ドメインを設定して、アプリの外部顧客およびコンシューマーの認証サインイン エンドポイントをカスタマイズする方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

カスタム URL ドメインを使用すると、Microsoft の既定のドメイン名ではなく、独自のカスタム URL ドメインを使用してアプリケーションのサインイン エンドポイントをブランド化できます。

[Image: スクリーンショットでは、External ID カスタム URL ドメインのユーザー エクスペリエンスを示しています。]

検証済みのカスタム URL ドメインを使用すると、次のような利点がいくつかあります。

- より一貫性のあるユーザー エクスペリエンスが提供されます。 ユーザーの視点では、サインイン プロセス中、ユーザーは Azure AD B2C の既定ドメイン *&lt;tenant-name&gt;.ciamlogin.com* にリダイレクトするのでなく、ドメインにとどまります。
- サインイン時にアプリケーションを同じドメインに維持することで、[サードパーティの Cookie ブロック](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-third-party-cookies-spas)の影響を軽減します。

### カスタム URL ドメインのしくみ

カスタム URL ドメインを使用すると、検証済みのカスタム URL ドメイン名をアプリケーションのサインイン認証エンドポイントとして使用できます。 新しいカスタム URL ドメイン名を追加すると、カスタム URL ドメインに関連付けることができます。 その後、[Azure Front Door](https://azure.microsoft.com/services/frontdoor/) などのリバース プロキシ サービスは、カスタム URL ドメインを使用して、サインインをアプリケーションに誘導できます。

次の図は、Azure Front Door の統合を示しています。

[Image: Azure Front Door と外部 ID の統合を示す図。]

1. ユーザーは、アプリケーションからサインイン ボタンを選択すると、サインイン ページに移動します。 このページでカスタム URL ドメインが指定されます。
2. Web ブラウザーによって、カスタム URL ドメインは Azure Front Door の IP アドレスに解決されます。 ドメイン ネーム システム (DNS) 解決中に、カスタム URL ドメインを持つ正規名 (CNAME) レコードは、Front Door の既定のフロントエンド ホスト (`contoso-frontend.azurefd.net` など) を指します。
3. カスタム URL ドメイン (`login.contoso.com` など) 宛てのトラフィックは、指定された Front Door の既定のフロントエンド ホスト (`contoso-frontend.azurefd.net`) にルーティングされます。
4. Azure Front Door は、`<tenant-name>.ciamlogin.com` の既定のドメインを使用してコンテンツを呼び出します。 Azure AD URL エンドポイントに対する要求に、元のカスタム URL ドメイン名が含まれます。
5. 外部 ID は、関連するコンテンツと元のカスタム URL ドメインを表示して、カスタム URL ドメイン要求に応答します。

Azure Front Door は、ユーザーの元の IP アドレス (監査レポートに表示される IP アドレス) を渡します。

重要

クライアントが Azure Front Door に `x-forwarded-for` ヘッダーを送信すると、外部 ID は、条件付きアクセス評価と `x-forwarded-for` クレーム リゾルバーのユーザーの IP アドレスとして発信元の `{Context:IPAddress}` を使用します。

### 考慮事項と制限事項

カスタム URL ドメインを使用しているとき:

- 複数のカスタム URL ドメインを設定できます。 サポートされているカスタム URL ドメインの最大数については、Microsoft Entra のサービス制限と制約について  のページを参照し、Azure Front Door の Azure サブスクリプションとサービスの制限、クォータ、制約については  のページを参照してください。
- 追加料金が発生する別の Azure サービスである Azure Front Door を使用できます。 詳細については、「[Front Door の価格](https://azure.microsoft.com/pricing/details/frontdoor)」を参照してください。 Azure Front Door インスタンスは、外部テナントとは異なるサブスクリプションでホストできます。
- 複数のアプリケーションがある場合は、それらすべてをカスタム URL ドメインに移行してください (ブラウザーが、現在使用されているドメイン名の下にセッションを格納するため)。

重要

- Azure Front Door: ブラウザーから Azure Front Door への接続では、常に IPv6 ではなく IPv4 を使用する必要があります。
- ソーシャル ID プロバイダー: カスタム URL ドメインでは、Apple に加えて Google と Facebook がサポートされるようになりました。

### 既定のドメインをブロックする

セキュリティを強化するために、既定のドメインをブロックすることをお勧めします。 カスタム URL ドメインを構成した後も、ユーザーは引き続き既定のドメイン名 *&lt;tenant-name&gt;.ciamlogin.com* にアクセスできます。 攻撃者が既定のドメインを使用してアプリにアクセスしたり、分散型サービス拒否 (DDoS) 攻撃を実行したりできないように、既定のドメインへのアクセスをブロックする必要があります。 既定のドメインへのアクセスをブロックするには、サポート チケット を開き、要求を送信 。

注意

既定のドメインをブロックする要求を送信する前に、カスタム URL ドメインが正しく動作することを確認します。

#### 機能への影響と回避策

既定のドメインをブロックすると、それに依存する特定の機能が無効になります。 ただし、次の表に示す機能は、カスタム URL ドメインで構成することで維持できます。

| 特徴 | 回避策 |
| --- | --- |
| 今すぐ実行 | Microsoft Entra 管理センターで、概要ガイドの 「今すぐ実行」機能で使用される URL と、カスタム URL ドメインを使用してユーザー フロー ウィンドウを更新します。 ブラウザーの URL で、`{your_domain}.ciamlogin.com` をカスタム URL ドメイン `{your_custom_URL_domain}/{your_tenant_ID}`に置き換えます。 |
| 開始するためのサンプル | カスタム URL ドメインを使用して、概要ガイドのサンプルを構成します。 詳細な手順については、各サンプルのドキュメントを参照してください。 たとえば、[Vanilla JavaScript シングルページ アプリチュートリアルの「カスタム URL ドメインの使用」セクション](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-javascript-configure-authentication)参照してください。 |
| 外部 ID を持つ Power Pages | Power Pages サイトで外部 ID 使用する場合は、カスタム URL ドメインでサイト設定を更新します。 Power Pages ID プロバイダーの構成ページで、`{your_domain}.ciamlogin.com`を含む [機関 URL] フィールドをカスタム URL ドメイン `{your_custom_URL_domain}/{your_tenant_ID}`に置き換えます。 |
| 外部 ID を持つ Azure App Service | Azure App Serviceで外部 ID 使用する場合は、ID プロバイダーを編集し、発行者 URL フィールドを  からカスタム URL ドメイン に変更します。 |
| Visual Studio Code 拡張機能 | [Visual Studio Code 拡張機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/visual-studio-code-extension)で、カスタム URL ドメインをアプリケーションの MSAL 構成に追加して、アプリケーションと "今すぐ実行" 機能が正常に動作するようにします。 authconfig ファイルの権限を `{your_domain}.ciamlogin.com` から `{your_custom_URL_domain}/{your_tenant_ID}`に変更し、カスタム URL ドメインに既知の機関を追加します。 |
| 外部 ID を持つ Visual Studio | appsettings.json ファイルで、カスタム URL ドメインの後にテナント ID を追加し、カスタム URL ドメインで既知の機関を追加します。 |
| GitHub のサンプル | 特定のサンプル、例えば [OpenAI チャット アプリケーション（Microsoft Entra 認証(Python)](https://github.com/Azure-Samples/openai-chat-app-entra-auth-builtin/blob/main/README.md)）などには、カスタム URL ドメインが必要です。 サンプルを設定するときに、AZURE\_AUTH\_LOGIN\_ENDPOINTをカスタム URL ドメインに設定します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/concept-guide-explained"} -->
## 作業の開始ガイドの機能 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-guide-explained
- Service: entra-external-id / external
- Article date: 2025-02-06
- Summary: 作業の開始ガイドで設定した機能について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

[作業開始ガイド](https://learn.microsoft.com/ja-jp/entra/external-id/customers/quickstart-get-started-guide)を完了すると、自社のニーズに合わせて初期構成を再作成、編集、カスタマイズできます。 これにより、Microsoft Entra 外部 ID の機能を理解し、その使用方法をよりよく把握し、提供される価値を評価できます。 このプロセスを通じ、使用したい新しい機能が見つかることもあります。

作業の開始ガイドは以下の機能を自動的に設定しました。 この記事ではこれらの機能について説明し、手動で構成する方法について解説します。

[Image: ガイドの手順を示すフローチャート。]

### お試しテナントの作成

[Image: ガイドの試用版テナントの作成手順を示すフローチャート。]

外部テナントは、Microsoft Entra 外部 ID の使用を開始するために作成する必要がある最初のリソースです。 Azure サブスクリプションがある場合は、[これらの手順](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)に従うことで、Microsoft Entra 管理センターに新しいテナントを作成できます。

### アプリの登録

[Image: ガイドのアプリ登録手順を示すフローチャート。]

アプリケーションが Microsoft Entra 外部 ID でサインインできるようにするには、Microsoft Entra 外部 ID でアプリを登録する必要があります。 作業の開始ガイドは、サンプル アプリとテナントの間でこの信頼関係を作成します。 アプリを登録するだけでなく、エンドポイントとリダイレクト URI を作成し、サインイン プロセスをテストできるように、基本的な委任されたアクセス許可をアプリに追加します。

アプリを手動で登録する場合は、アプリが API を呼び出す必要がある場合に API アクセス許可を付与することもできます。 アプリの種類に基づき、適切な登録プロセスを選択する必要があります。 アプリを登録する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)をご覧ください。

### ユーザー フロー

[Image: ガイドのユーザー フローの手順を示すフローチャート。]

作業の開始ガイドでは、ユーザー フローが自動的に作成されます。 ユーザー フローによって、顧客がアプリケーションにサインインするために使用できる認証方法と、サインアップ中に指定する必要のある情報が定義されます。 既存のユーザー フローを構成することも、新しいユーザー フローを作成することもできます。 すべてのアプリに同じサインイン エクスペリエンスが必要な場合は、同じユーザー フローに複数のアプリを追加できます。 ただし、アプリケーションに必要なサインイン エクスペリエンスは 1 つだけのため、各アプリケーションは 1 つのユーザー フローにしか追加できません。

ユーザー フローを作成する方法の詳細については[こちら](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)を参照し、ユーザー フローにアプリケーションを追加する方法の詳細については[こちら](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)を参照してください。 ユーザー フローを作成したら、[ユーザー フローの実行機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-test-user-flows)を使用してサインアップとサインインのエクスペリエンスをテストできます。

アプリで、組み込みのユーザー属性よりも多くの情報が必要な場合は、独自の属性を追加できます。 これらの属性を、"カスタム ユーザー属性" と呼びます。 手動でカスタム ユーザー属性を作成し、ユーザー フローに追加できます。 カスタム属性を作成する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes#create-custom-user-attributes)をご覧ください。

### ブランディング

[Image: ガイドのブランディングの手順を示すフローチャート。]

作業の開始ガイドは、会社のロゴの追加、背景色の変更、レイアウトの調整など、サインイン ページをカスタマイズするためのいくつかの基本的なオプションを提供しました。

初期セットアップ後、これらの設定を手動で編集し、ブランディング オプションを追加できます。 レイアウトを調整し、ヘッダーとフッターを追加し、テキスト、画像、ハイパーリンクを構成し、サインイン ページとサインアップ ページに言語を追加できます。 新たな外部テナントで使用できるさまざまなブランディング オプションの詳細については、「[ブランディング オプション](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers)」のページを参照してください。

### 最初のユーザーによるサインインのプレビュー

[Image: ガイドのサインインのプレビュー手順を示すフローチャート。]

作業の開始ガイドを使用して、最初のユーザーのサインイン エクスペリエンスをプレビューすることができました。 ガイドのこの手順では、サインアップ手順をテストするためだけに新しいユーザーを作成する必要がありました。 ガイドでは、新しく作成したユーザーがアプリではなく JWT.ms にリダイレクトされました。

ガイドのセットアップ中に作成したユーザーを確認するには、[管理センター](https://entra.microsoft.com/)に移動し、ユーザーの一覧でユーザーを探します。 ユーザー一覧のユーザーを[顧客ユーザー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-customer-accounts)として確認でき、自分のアカウントを[テナント管理](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-admin-accounts)として管理することもできます。テナントで登録済みのアプリケーションのユーザー アクティビティとエンゲージメントに関するデータを参照したい場合は、[アプリケーション ユーザー アクティビティ ダッシュボード](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-insights)を使用できます。 アプリケーション ユーザー アクティビティ ダッシュボードは、2026 年 8 月 31 日に廃止されます。新しいデプロイでは、[Azure Monitor](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-azure-monitor) を使用します。 詳細については、「 [User Insights からの移行](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-insights#migrate-from-user-insights)」を参照してください。

ガイドでは、顧客ユーザーの認証方法を設定しました。電子メールとパスワード、またはワンタイム パスコード サインインのいずれかを選択します。 Facebook、Google、Apple などのソーシャル アカウントでのサインインの有効化やカスタム OpenID Connect ID プロバイダーの使用など、アプリケーションのユーザーを認証するための他のオプションを手動で構成することもできます。 これらのオプションの構成方法の詳細については、「[認証方法と ID プロバイダー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers)」のページを参照してください。 顧客向けに[セルフサービス パスワード リセットを有効化する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers)こともできます。

### アプリのサンプル

[Image: ガイドのアプリ サンプルの手順を示すフローチャート。]

作業の開始ガイドは、新しいテナントの機能をテストするためのダウンロード可能なサンプル アプリを提供します。 アプリを手動で登録すると、Microsoft Entra ID によって**アプリケーション (クライアント) ID**と呼ばれる一意の識別子が生成されます。 この値を使用すると、認証要求を作成する際にアプリを識別できるため、アプリとテナントの間の信頼関係を有効化できます。 サンプルは **clientId** を使用して自動的に構成され、**権限**が `<trialtenant>.ciamlogin.com` に設定されます。

アプリ サンプルとガイドの包括的な一覧については、プロセスの詳細を説明する[こちら](https://learn.microsoft.com/ja-jp/entra/external-id/customers/samples-ciam-all)をご覧ください。

**コード サンプル ガイド**のリンクは関連するサンプル記事を示すもので、アプリの登録、ユーザー フローの作成、アプリとユーザー フローの関連付け、サインインするプロジェクトの実行のプロセスについて説明します。 場合によっては、API を呼び出す方法についても説明します。

認証用にアプリを構成する方法の詳細については、**ビルドと統合のガイド**のリンクを参照してください。 これらのチュートリアルは、独自のアプリを構築し、Microsoft Entra 外部 ID と統合する際に役立ちます。 認証フロー内の特定のポイントで[カスタム認証拡張機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-extensions)を追加することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/concept-multifactor-authentication-customers"} -->
## 外部テナントでの MFA - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers
- Service: entra-external-id / external
- Article date: 2026-05-21
- Summary: MFA を使用して外部テナントのアプリをセキュリティで保護し、サインアップとサインインの 2 番目の検証方法として電子メール ワンタイム パスコード (EOTP)、SMS、またはパスキー (FIDO2) を有効にする方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

多要素認証 (MFA) では、ユーザーがサインアップまたはサインイン中に ID を検証するための 2 つ目の方法を提供するように要求することで、アプリケーションにセキュリティのレイヤーが追加されます。 外部テナントでは、2 番目の要素として次の認証方法がサポートされています。

- メールワンタイムパスコード
- SMS ベースの認証。アドオンとして使用できます (詳細を参照)。
- Passkey (FIDO2)。 パスキーは、1 つのジェスチャで MFA を満たし、パスワードレス サインインを有効にできるフィッシング対策認証方法です (詳細を参照)。

MFA を適用すると、検証のレイヤーが追加され、承認されていないユーザーがアクセスすることが困難になり、組織のセキュリティが強化されます。

Note

MFA は、ブラウザーによる委任認証とネイティブ認証の両方でサポートされています。 ブラウザーによる委任された認証では、MFA チャレンジは Microsoft ホスト型サインイン ページで処理されます。 ネイティブ認証では、アプリは MSAL SDK を使用して MFA プロンプトをインラインで表示します。アプリが `mfa_required` 機能をアドバタイズしない場合、ネイティブ認証 API は [Web フォールバック](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-web-fallback) を開始してチャレンジを完了します。 方法の選択の詳細については、「 [認証方法の選択」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach)参照してください。

### MFA ポリシーの作成

外部テナントでは、Microsoft Entra 条件付きアクセスを使用して、ユーザーがアプリにサインアップまたはサインインするときに、ユーザーに MFA を求めるポリシーを作成できます。 このポリシーは、Microsoft Entra 管理センターの [保護] セクションの [条件付きアクセス] で作成します。 すべてのユーザーを含めて、緊急アクセス用または非常用アカウントを除外して、ポリシーを適用するユーザーとグループを指定できます。

ポリシーでは、MFA を必要とするアプリケーションを定義します。 すべてのクラウド アプリにポリシーを適用することも、MFA を必要としないアプリケーションを除外しながら、特定のアプリを選択することもできます。 次に、ユーザーが MFA 要件を完了した場合にのみアクセス権を付与するようにポリシーを構成します。

詳細については、 [外部テナントで条件付きアクセス ポリシーを作成する方法を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers#create-a-conditional-access-policy)参照してください。

#### 条件付きアクセス認証コンテキストを使用した MFA のステップアップ

認証コンテキストを使用した多要素認証 (MFA) を使用すると、ユーザーが機密データにアクセスしたり、重要なアクションを実行したりする場合にのみ、より強力なセキュリティを適用できます。 アプリ全体に MFA を適用する必要はありません。 Microsoft Entra [条件付きアクセス認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-guide-conditional-access-authentication-context)を使用すると、開発者はアプリ内に MFA などのステップアップ認証を追加できます。 これは、価値の高いトランザクションや個人情報の表示などのシナリオに使用します。 このアプローチでは、ゼロ トラストの原則がサポートされています。 これにより、最小限の特権アクセスが保証され、ユーザーの摩擦が軽減されます。 ユーザーは、セキュリティで保護されたシームレスなエクスペリエンスを実現します。

### MFA 方法の有効化

ユーザー フローで ID プロバイダー オプションを選択する場合は、サインアップとサインインの第 1 要素認証方法を定義します。 MFA の第 2 要素検証方法は、Microsoft Entra 管理センターの **Entra ID**&gt;**Authentication メソッド**で構成されます。

最初の要素として選択するオプションに応じて、 [多要素認証 (MFA)](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers) に使用できる第 2 要素検証方法が異なります。

- **パスワードまたはパスワード付きのユーザー名を含む電子メール**: これらの第 1 要素の方法では、メールワンタイム パスコード、SMS、パスキー (FIDO2)、または MFA の第 2 要素検証方法としての組み合わせを有効にすることができます。
- **外部 ID プロバイダー**: 電子メールワンタイム パスコード、SMS、または組み合わせを MFA の第 2 要素検証方法として有効にすることができます。 パスキー (FIDO2) は、現在、外部 ID プロバイダーでサインインするユーザーには使用できません。
- **電子メール ワンタイム パスコード**: ワンタイム パスコードを含む電子メールが第 1 要素認証方法として選択されている場合、第 2 要素認証には使用できません。 そのため、MFA 用に有効にできるのは SMS ベースの検証のみです。 パスキー (FIDO2) は現在、電子メール ワンタイム パスコード ユーザーには使用できません。

詳細については、 [外部テナントで MFA メソッドを有効にする方法を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers#enable-email-one-time-passcode-as-an-mfa-method)参照してください。

### メールワンタイムパスコード

メール ワンタイム パスコード認証は、第 1 要素と第 2 要素の両方の検証方法として外部テナントで使用できます。 MFA の電子メール ワンタイム パスコードの使用を許可するには、ローカル アカウントの認証方法を *[パスワード付きの電子メール]* に設定する必要があります。 *ワンタイム パスコードを含む電子メール*を選択した場合、プライマリ サインインにこの方法を使用しているお客様は、MFA のセカンダリ検証に使用できません。

MFA 用にメール ワンタイム パスコードが有効になっている場合、ユーザーは第 1 のサインイン方法でサインインし、ユーザーのメール アドレスにコードが送信されることが知らされます。 ユーザーは、コードの送信を選択し、メールの受信トレイからパスコードを取得し、サインイン ウィンドウにパスコードを入力します。 ユーザーは、この検証プロセスを 10 分以内に完了する必要があります。

### SMS ベースの認証

SMS は、第 2 要素認証と外部テナントでのセルフサービス パスワード リセットに対して追加コストで利用できます。 現在、第 1 要素認証ではサポートされていません。

MFA 用に SMS が有効になっている場合、ユーザーは第 1 の方法でサインインし、SMS 経由で送信されたコードを使用して ID を確認するように求められます。 電話番号を入力し、確認コード付きの SMS を受信します。

[Image: MFA の SMS テキストのスクリーンショット。]

次の対策を適用することで、外部 ID は SMS による不正なサインアップを軽減します。

- テレフォニーのスロットリング制限は、停止や速度低下を防ぐのに役立ちます。 [サービスの制限と制限を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-service-limits)参照してください。
- SMS による MFA 用の CAPTCHA は、人間のユーザーと自動ボットを区別することで、自動攻撃を防ぐのに役立ちます。 危険なユーザーが検出された場合、ユーザーがサインインするのをブロックするか、SMS による検証コードを送信する前に CAPTCHA を完了するようにユーザーに依頼します。

#### 国や地域別の SMS 価格帯

次の表は、さまざまな国または地域にわたる SMS ベースの認証サービスのさまざまな価格帯の詳細を示しています。 価格の詳細については、 [Microsoft Entra 外部 ID の価格](https://aka.ms/ExternalIDPricing)に関するページを参照してください。

SMS はアドオン機能であり、 [リンクされたサブスクリプション](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing#link-an-external-tenant-to-a-subscription)が必要です。 サブスクリプションの有効期限が切れた場合、またはサブスクリプションが取り消された場合、エンド ユーザーは SMS を使用して認証できなくなります。そのため、MFA ポリシーに応じてユーザーのサインインがブロックされる可能性があります。

| レベル | 国/地域 |
| --- | --- |
| 電話認証低コスト | オーストラリア、ブラジル、ブルネイ、カナダ、チリ、中国、コロンビア、キプロス、北マケドニア、ポーランド、ポルトガル、韓国、タイ、Türkiye、米国 |
| 電話認証中低コスト | グリーンランド、アルバニア、アメリカ領サモア、オーストリア、バハマ、バーレーン、ボスニア & ヘルツェゴビナ、 Botswana、コスタリカ、チェコ共和国、デンマーク、エストニア、フェロー諸島、フィンランド、フランス、ギリシャ、香港特別行政区、ハンガリー、アイスランド、アイルランド、イタリア、日本、ラトビア、ラトビア、リトアニア、ルクセンブルク、マカオ SAR、マルタ、メキシコ、ミクロネシア、モルドバ、ナミビア、ニュージーランド、ニカラグア、ノルウェー、ルーマニア、サントメ、プリンシペ、セーシェル共和国、シンガポール、シンガポール、スロバキア、ソロモン諸島、スペイン、スペイン、 スウェーデン、スイス、台湾、英国、米国領バージン諸島、ウルグアイ |
| 電話認証中高コスト | アンドラ、アンゴラ、アンギラ、南極、アンティグア・バーブーダ、アルゼンチン、アルメニア、アルバ、バルバドス、ベルギー、ベナン、ボリビア、ボネール島、キュラソー、サバ島、シント ユースタティウス島、シント・マールテン、英領ヴァージン諸島、ブルガリア、ブルキナファソ、カメルーン、ケイマン諸島、中央アフリカ共和国、クック諸島、コートジボワール、クロアチア、ディエゴ ガルシア、ジブチ、ドミニカ共和国、エクアドル、エルサルバドル、エリトリア、フォークランド諸島、フィジー、仏領ギアナ、仏領ポリネシア、ガンビア、ジョージア、ドイツ、ジブラルタル、グレナダ、グアドループ、グアム、ギニア、ガイアナ、ホンジュラス、インド、ケニア、キリバス、ラオス、リベリア、マレーシア、マーシャル諸島、マルティニーク、モーリシャス、モナコ、モンテネグロ、モントセラト、オランダ、ニューカレドニア、ニウエ、オマーン、パラオ、パナマ、パラグアイ、ペルー、プエルトリコ、レユニオン、ルワンダ、セントヘレナ、アセンションおよびトリスタンダクーニャ、セントクリストファー ネーヴィス、セントルシア、サンピエール島 ミクロン島、セントビンセントおよびグレナディーン諸島、サイパン、サモア、サンマリノ、サウジアラビア、スロベニア、南アフリカ、南スーダン、スリナム、スワジランド (新名称はエスワティニ王国)、ティモール レステ、トケラウ、トンガ、タークス カイコス、ツバル、アラブ首長国連邦、バヌアツ、ベネズエラ、ベトナム、ウォリス・フツナ |
| 電話認証高コスト | リヒテンシュタイン、バミューダ、カーボベルデ、カンボジア、コンゴ民主共和国、ドミニカ国、エジプト、赤道ギニア、ガーナ、グアテマラシティ、ギニアビサウ、イスラエル、ジャマイカ、ジャマイカ、コソボ、レソト、モルディブ、マリ、モーリタニア、モロッコ、モザンビーク、パプアニューギニア、フィリピン、カタール、シエラレオネ、トリニダード トバゴ、ウクライナ、ジンバブエ、アフガニスタン、アルジェリア、アゼルバイジャン、バングラデシュ、ベラルーシ、ベリーズ、ブータン、ブルンジ、チャド、コモロ、コンゴ共和国、エチオピア、ガボン共和国、ハイチ、インドネシア、イラク、ヨルダン、クウェート、キルギス、レバノン、リビア、マダガスカル、マラウイ、モンゴル、ミャンマー、ナウル、ネパール、ニジェール、ナイジェリア、パキスタン、パレスチナ自治政府、ロシア、セネガル、セルビア、ソマリア、スリランカ、スーダン、タジキスタン、タンザニア、トーゴ共和国、チュニジア、トルクメニスタン、ウガンダ、ウズベキスタン、イエメン、ザンビア |

#### SMS のオプトイン リージョン

2025 年 1 月以降、一部の国番号は、SMS による認証は既定で非アクティブ化されます。 非アクティブ化されたリージョンからのトラフィックを許可する場合は、Microsoft Graph `onPhoneMethodLoadStartevent` ポリシーを使用して、それらをアプリケーションに対してアクティブ化する必要があります。 [SMS 検証のオプトインが必要なリージョンを](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-region-code-opt-in)参照してください。

### パスキー (FIDO2)

パスキー (FIDO2) は、公開キー暗号化を使用する、フィッシングに強いパスワードレス認証を提供します。 パスキーは、1 つのジェスチャ (顔、指紋、PIN、またはセキュリティ キー) またはプライマリのパスワードレス サインイン方法として MFA を満たすために使用できます。 パスキーを登録できるのは、電子メール + パスワードとユーザー名 + パスワードのローカル アカウントユーザーだけです。 パスキーの登録には [カスタム URL ドメイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain)が必要であり、ユーザーはパスキーを登録する前に MFA を完了する必要があります。

セットアップ手順については、「 [パスキーを使用したサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-with-passkey)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/concept-planning-your-solution"} -->
## CIAM のデプロイを計画する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-planning-your-solution
- Service: entra-external-id / external
- Article date: 2026-05-21
- Summary: テナントの作成、アプリの登録、サインイン用のユーザー フローの設定など、外部テナントで顧客 ID およびアクセス管理 (CIAM) ソリューションを設定する手順について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra 外部 IDは、Microsoft Entra プラットフォーム上のアプリに顧客 ID とアクセス管理 (CIAM) を追加するため、従業員と顧客のシナリオ全体で一貫したアプリ統合、テナント管理、運用を実現できます。

この記事は、6 つの計画手順の意思決定ガイドです。 各セクションでは、主要な選択肢と、標準的なハウツーとリファレンス ドキュメントへのリンクをまとめます。

[Image: 水平方向のフローとして、外部テナントの作成、認証アプローチの選択、アプリケーションの登録、サインイン フローの統合、サインインのセキュリティ保護、サインインのカスタマイズの 6 つのセットアップ手順を示す図。]

詳細な手順に進むか、 **ハウツー ガイド**に直接移動します。

| 手順 | 操作方法ガイド |
| --- | --- |
| **手順 1: 外部テナントの作成** | • [外部テナントを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)• [または無料試用版を開始する](https://aka.ms/ciam-free-trial?wt.mc_id=ciamcustomertenantfreetrial_linkclick_content_cnl) |
| **手順 2: 認証方法を選択する** | • [認証方法を選択する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach) |
| **手順 3: アプリケーションを登録する** | • [アプリケーションを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) |
| **手順 4: サインイン フローをアプリと統合する** | • [ユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)• [アプリをユーザー フローに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application) |
| **手順 5: サインインをセキュリティで保護する** | • [多要素認証 (MFA)](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers)を追加する• [セキュリティとガバナンスを確認](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-security-customers)する• [サードパーティのボット保護](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-third-party-bot-protection-native-api-sign-up)*(ネイティブ認証)*を統合する• [サードパーティの ATO 保護](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-third-party-account-take-over-protection-native-api)*(ネイティブ認証)* を統合する |
| **手順 6: サインインをカスタマイズする** | • [ブランド化をカスタマイズ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-branding-customers)*する (ブラウザー委任)*• [カスタム URL ドメインを使用](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-url-domain)する• [カスタム認証拡張機能を追加](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-extensions)する |

### 手順 1: 外部テナントの作成

[Image: 手順 1 で[外部テナントを作成する]が強調表示されたセットアップ フローを示す図。]

外部テナントは、従業員テナントとは別に、アプリを登録し、顧客 ID を管理するリソースです。 作成するときは、その地理的な場所とドメイン名を選択します。 現在 Azure AD B2C を使用している場合、既存の B2C テナントは影響を受けません。「[Azure AD B2C から外部 ID への移行を計画する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/plan-your-migration-from-b2c-to-external-id)」を参照してください。

Von Bedeutung

2025 年 5 月 1 日より、Azure Active Directory B2C (Azure AD B2C) は新規のお客様が購入できなくなります。 詳細については、「[AZURE AD B2C は引き続き購入できますか?](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale)に関する FAQ を参照してください。

ディレクトリには、 [管理者アカウントと顧客アカウントの](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-admin-accounts) 両方が含まれています。 通常、顧客は自己登録します。 [ローカル アカウントを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-customer-accounts)することもできます。 顧客アカウントには制限付きの [既定のアクセス許可セット](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-user-permissions) があり、他のユーザー、グループ、またはデバイスを表示することはできません。

#### 外部テナントを作成する方法

- [Microsoft Entra 管理センターに外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)を作成します。
- テナントがまだない場合 [無料試用版を開始します](https://aka.ms/ciam-free-trial?wt.mc_id=ciamcustomertenantfreetrial_linkclick_content_cnl)。
- VS Code の使用 [Microsoft Entra 外部 ID拡張機能](https://aka.ms/ciamvscode/quickstarts/marketplace)を使用[します (詳細を参照)。](https://aka.ms/ciamvscode/quickstartguide)

### 手順 2: 認証方法を選択する

[Image: 手順 2 のセットアップ フローを示す図。認証方法を選択し、強調表示されています。]

アプリを登録する前に、サインイン エクスペリエンスを構築する方法を決定します。 この選択によって、統合の残りの部分が促進されます。

- **ブラウザー委任認証** — サインイン ページをホストMicrosoft。アプリはユーザーをそれにリダイレクトします。 広範なプラットフォーム サポート、システム ブラウザー SSO、低いメンテナンス。
- **ネイティブ認証** — アプリはサインイン UI をホストし、MSAL またはネイティブ認証 API を直接呼び出します。 完全な UI コントロール、より多くの開発とセキュリティの責任。

#### 認証方法を選択する方法

- [認証アプローチ (](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach) 機能の比較とトレードオフ) を選択します。
- [ネイティブ認証の概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication) - ネイティブを検討している場合の詳細。

### 手順 3: アプリケーションを登録する

[Image: 手順 3 のセットアップ フローを示す図。アプリケーションの登録が強調表示されています。]

外部テナントにアプリを登録して、Microsoft Entra IDとの信頼関係を確立します。 構成する設定は、手順 2 で選択した認証方法によって異なります。

| Setting | ブラウザー委任 | ネイティブ認証 |
| --- | --- | --- |
| リダイレクト URI | 必須 (アプリのサインイン コールバックと一致します) | [Web フォールバック](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-web-fallback)としてのみ必要 |
| パブリック クライアント フロー | 必須ではない | Enabled |
| ネイティブ認証 | 必須ではない | Enabled |

アプリを登録したら、アプリケーション (クライアント) ID、テナント サブドメイン、および (該当する場合) クライアント シークレットを使用してコードを更新します。

#### アプリケーションを登録する方法

- プラットフォーム固有のガイダンスは、 [アプリの種類と言語別のサンプル ページ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/samples-ciam-all)で確認できます。
- プラットフォームが一覧にない場合は、一般的な [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) クイックスタートに従ってください。
- ネイティブ認証アプリの設定については、「 [ネイティブ認証を有効にする方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication#how-to-enable-native-authentication)」を参照してください。

### 手順 4: サインイン フローをアプリと統合する

[Image: 手順 4 のセットアップ フローを示す図。サインイン フローをアプリと統合します。強調表示されています。]

アプリのサインイン方法、収集する属性、ID プロバイダーを定義するサインアップとサインインのユーザー フローを作成します。 両方の認証方法で同じ方法でユーザー フローを作成します。違いは、アプリが実行時にそれを駆動する方法です。

| - | ブラウザー委任 | ネイティブ認証 |
| --- | --- | --- |
| ランタイムの動作 | アプリが Microsoft ホスト型サインイン ページにリダイレクトされる | アプリが独自の UI から MSAL ネイティブ認証 API を呼び出す |
| サポートされるアプリの種類 | Web、SPA、モバイル、デーモン | モバイル、SPA |
| フェデレーション ID プロバイダー (ソーシャル、外部 IdP) | サポートされている | サポートされていません - 必要に応じてブラウザー委任を使用する |
| 会社のブランド化 | Microsoft ホストされているページに適用されます | アプリの UI/ローカライズで管理される |
| 属性コレクション | ユーザー フローで構成済み | ユーザー フローで構成されます。MSAL [ユーザー属性ビルダー](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-user-attribute-builder)を介して送信された |

#### ユーザー フローを計画する

- **ユーザー フローの数。** 各アプリは、1 つのユーザー フローを使用します。 アプリ間で 1 つのフローを共有したり、テナントごとに最大 10 個のフローを作成して、差別化されたエクスペリエンスを実現したりできます。
- **収集対象の属性。** 必要な組み込み属性と [、カスタム属性](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes)が必要かどうかを決定します。
- **使用条件の同意。** カスタム属性を使用して、使用条件とプライバシー ポリシーへのリンクを使用して同意を取得します。
- **トークン要求。**アプリ[がトークンに依存している場合は、トークンに必要な属性を追加](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-attributes-to-token)します。
- **サインイン メソッド。** ローカル アカウント (電子メール OTP、電子メール + パスワード) は両方の方法で動作します。 フェデレーション プロバイダー ([Google](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers)、[Facebook](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-facebook-federation-customers)、[Apple](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-apple-federation-customers)、[別のMicrosoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-entra-id-federation-customers)、[カスタム OIDC](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers)) には、ブラウザーで委任された認証が必要です。

#### ユーザー フローをアプリと統合する方法

- [カスタム属性を定義します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes) (必要な場合)。
- [サインアップとサインインのユーザー フローを作成します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)。
- [アプリケーションをユーザー フローに追加](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)します。
- アプリ コードを接続する:
    - **ブラウザー委任: アプリの** 種類の [サンプルまたはクイック スタート](https://learn.microsoft.com/ja-jp/entra/external-id/customers/samples-ciam-all) に従います。
    - **ネイティブ認証:**[ネイティブ認証の概要とチュートリアルに](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication)従います。

### 手順 5: サインインをセキュリティで保護する

[Image: 手順 5 でサインインをセキュリティで保護し、強調表示されているセットアップ フローを示す図。]

すべての顧客向けアプリには、MFA とベースライン セキュリティ レビューが必要です。 ネイティブ認証アプリには追加の作業があります。アプリは公開されているサインイン画面であるため、Web アプリケーション ファイアウォール (WAF) を使用してアプリを前面に表示します。 ブラウザーによって委任されたアプリは、ホストされているサインイン ページでMicrosoftのプラットフォーム レベルの保護を継承します。

- **MFA を有効にします。**[使用可能な MFA メソッド](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers)。
- **セキュリティとガバナンスを確認します。** 条件付きアクセス、リスクベースのポリシー、監査。 [「セキュリティとガバナンス」](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-security-customers)を参照してください。
- **ボット保護を追加***する (ネイティブ認証のみ)。*[カスタム URL ドメイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-url-domain)が必要です。 [サードパーティのボット保護の統合に](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-third-party-bot-protection-native-api-sign-up)関する説明を参照してください。
- **アカウント引き継ぎ (ATO) 保護を追加***する (ネイティブ認証のみ)。*[カスタム URL ドメイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-url-domain)が必要です。 [サードパーティの ATO 保護の統合に](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-third-party-account-take-over-protection-native-api)関する説明を参照してください。

### 手順 6: サインインをカスタマイズする

[Image: 手順 6 でサインインをカスタマイズし、強調表示されているセットアップ フローを示す図。]

サインインの外観をカスタマイズし、独自のビジネス ロジックを使用して拡張します。 ネイティブ認証では、アプリが UI を所有しているため、Microsoft Entra会社のブランド化機能は適用されません。アプリ コードでビジュアルとローカライズを管理します。

- **ブランドをカスタマイズ***する (ブラウザー委任のみ)。* ロゴ、色、言語文字列を、Microsoftホスト型サインイン ページに適用します。 「 [サインインの外観をカスタマイズ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-branding-customers)する」を参照してください。
- **カスタム URL ドメインを使用します。** 既定の `ciamlogin.com` ホストを独自のドメインに置き換えます。 手順 5 のネイティブ認証ボット/ATO 保護の前提条件でもあります。 [カスタム URL ドメイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-url-domain)を参照してください。
- **カスタム認証拡張機能を追加します。** サーバー側ロジックを使用してフローを拡張します。 トークン発行拡張機能は両方のアプローチに対応します。属性収集拡張機能は、ブラウザー委任方式でのみ利用されます。 [カスタム認証拡張機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-extensions)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/concept-security-customers"} -->
## 外部テナントのセキュリティ機能 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-security-customers
- Service: entra-external-id / external
- Article date: 2026-01-28
- Summary: 外部テナント構成における Microsoft Entra 外部 ID 顧客 ID およびアクセス管理 (CIAM) のセキュリティ機能と基礎について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

外部向け ID システムは、さまざまなカスタマー エクスペリエンスをサポートします。 その範囲が広いため、資格情報の詰め込み、ボットの自動サインアップ、アカウント引き継ぎの試行、大量のトラフィックの急増など、一般的な顧客 ID とアクセス管理 (CIAM) 攻撃パターンの魅力的なターゲットにもなります。 これらの脅威を理解することは、明確で階層化されたセキュリティ アプローチが不可欠な理由を説明するのに役立ちます。 Microsoft Entra 外部 ID には、基に構築できる基本的な機能が用意されています。 このガイドは、一般的な CIAM 脅威パターンに基づいて、推奨される制御と統合を使用してその基盤を強化する方法を理解するのに役立ちます。

この記事では、次の概要について説明します。

- 一般的に CIAM システムを対象とする攻撃ベクトル。
- 対処に役立つセキュリティコントロール。
- 実装をガイドするための優先順位付けされたロードマップ。
- 不正行為、ボット、DDoS 保護に使用できるサードパーティの統合。
- 外部テナントに固有の既知の制限。

私たちの目標は、顧客向けのエクスペリエンスをセキュリティで保護する方法に関する情報に基づいた意思決定を行うのに役立つ、実用的で実用的なロードマップを提供することです。

### 外部テナントのセキュリティ モデル

多層防御戦略では、複数のコントロール レイヤーを組み合わせる必要があります。

- フロントドア保護（WAF、ボット対策、IP制御）
- ID セキュリティ (MFA、条件付きアクセス、アクセス制御)
- 不正アクセス防止 (3P プロバイダーによるサインアップとサインインの検出)
- アプリケーション レベルの承認
- 監視とアラート

各レイヤーは異なる種類の攻撃に対処し、侵害の可能性を減らし、爆発半径を制限します。

Important

認証方法の選択は、セキュリティ モデルに影響します。 **ブラウザー委任認証**を使用すると、Microsoftがサインイン画面を管理します。これにより、アプリがフィッシング攻撃や資格情報収集攻撃にさらされるのを減らすことができます。 **ネイティブ認証**では、アプリが資格情報を直接処理するため、開発チームはセキュリティ責任をMicrosoft Entraと共有します。 ネイティブ認証を選択する前に、「 [認証方法の選択」のセキュリティに関する考慮事項を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach#security-considerations)確認してください。

#### 優先順位 1: 即時実装

これらの保護は基本的です。 外部 ID フローのセキュリティが確保されたベースラインを確立するために、すばやく有効化され、重要です。 Priority 2 で導入された追加の保護は、不正行為、ボット、DDoS、およびより高度な攻撃パターンに対処します。

| **セキュリティ機能** | **説明** | **対処された脅威** | **ユーザーへの影響** | **実装作業** |
| --- | --- | --- | --- | --- |
| **[ブルート フォース保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)** | パスワードの推測を繰り返すことで、不正アクセスを防ぐためのサインイン試行回数を制限することで、ブルート フォース攻撃を軽減します。 **メモ：** スマートロックアウトは、パスワードの誤用に焦点を当てています。より広範なボットと不正行為のアクティビティについては、この記事の後半で追加の制御を通じて対処します。 | ブルートフォース攻撃やアカウント乗っ取りを防止します。 | なし - エンドユーザーへの影響なし | なし – 既定で有効 |
| **[一般的なネットワーク HTTP 保護](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-service-limits)** | これらの組み込み保護は、不正なトラフィックのフィルター処理、不正な要求パターンの制限、一般的なプロトコル レベルの攻撃の軽減に役立ちます。**メモ：** これらのセーフガードはアプリケーションの安定性をサポートしますが、大量の DDoS と高度なボットには、後で説明する WAF またはボット軽減の統合が必要です。 | 一般的なネットワーク攻撃から保護します。 | 低 – 機密性の高いアクションのみ | なし – 既定で有効 |
| **[アクセス制御](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-use-app-roles-customers)** | アプリのロールと承認規則を適用することによって、ユーザーが必要な内容にのみアクセスできるようにします。**メモ：** アクセス制御では、ユーザー検証ではなく、承認が適用 -so MFA とアダプティブコントロールを組み合わせることで、全体的なセキュリティが強化されます。 | アプリケーションで承認を強制します。 | 中程度 – ユーザー採用 | 中 – セットアップが必要 |
| **[すべてのユーザーに対して多要素認証 (MFA) を有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers)** | MFA では、パスワード以外の 2 番目の検証手順が追加され、アカウント引き継ぎのリスクが大幅に軽減されます。**メモ：** MFA は、盗まれた資格情報攻撃を制限しますが、自動または悪意のあるサインアップの試行を防ぐために、不正行為の検出と監視と組み合わせて使用する必要があります。 | 第 2 要素認証を追加します。 | 高いユーザーの採用率 | 中 – セットアップが必要 |
| **[Passkeys (FIDO2)](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-with-passkey)** | 顔、指紋、PIN、またはセキュリティ キーを使用する、フィッシングに強いパスワードレス認証。 パスキーは、1 つのジェスチャで MFA を満たし、プライマリのパスワードレス サインイン方法としても機能します。 | フィッシング、パスワードの盗難、資格情報の再生を防止します。 | 低 – 使い慣れたジェスチャ | 中 – セットアップと資格情報管理の UX が必要 |
| **[アクティビティ ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ups) と [ユーザー分析情報](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-insights)** | アクティビティ ログと分析情報により、サインアップ、サインイン、エラー パターン、異常を可視化できます。 これらは、異常な傾向を特定し、問題をすばやく調査し、早期検出と情報に基づいた意思決定をサポートするのに役立ちます。 | 脅威検出を有効にします。 | なし - エンドユーザーへの影響なし | 中 – セットアップが必要 |

#### 優先順位 2: 短期的な実装

ベースライン制御が実施されたら、脅威の回復性を大幅に向上させる保護を実装します。

| **セキュリティ機能** | **説明** | **対処された脅威** | **ユーザーへの影響** | **実装作業** |
| --- | --- | --- | --- | --- |
| **[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-supported-features-customers#conditional-access)** | フィッシングやアカウントの引き継ぎなどの脅威から防御するために MFA をトリガーするカスタマイズ可能なポリシー。 詳細については、 [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) とは何か、および [条件付きアクセス認証コンテキストに関する開発者ガイド](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-guide-conditional-access-authentication-context) を参照してください。 | リスクベースのアクセス制御を提供します。 | 中程度のユーザー採用 | 中 – セットアップが必要 |
| **[資格情報とシークレットの管理](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-external-operations#application-security-credential-and-secret-management)** | 証明書はセキュリティで保護され、セキュリティを侵害するのが難しいので、外部 ID に対するアプリ認証ではクライアント シークレットよりも証明書を優先します。 | アプリの資格情報をセキュリティで保護します。 | なし - エンドユーザーへの影響なし | 中 – セットアップが必要 |
| **[トークンの有効期間の管理](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-external-operations#risk-reduction-with-token-lifetime-management)** | トークンの有効期間を構成して、侵害されたトークンに対するアプリの露出を減らします。 | 露出を制限します。 | 高 – 頻繁な更新が必要 | 中 – セットアップが必要 |
| **Web アプリケーション ファイアウォール (WAF) を使用したカスタム ドメイン** | DDoS 攻撃に対するテナント セキュリティには [、Cloudflare](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-configure-waf-integration)、 [Akamai](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-configure-akamai-integration) 、または [Azure WAF](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-configure-external-id-web-app-firewall) を使用します。 | DDoS とボット保護を提供します。 | 中程度のユーザー採用 | 高 – WAF の統合 |
| **サインアップ詐欺防止** | [Arkose Labs](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-integrate-fraud-protection?pivots=arkose) と [HUMAN Security](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-integrate-fraud-protection?pivots=human) を使用して、サインアップ詐欺から保護し、ボットの自動攻撃をブロックします。 | 不正行為の防止を提供します。 | 中程度のユーザー採用 | 高 – サインアップ保護の統合 |
| **監視とアラートの強化** | [Azure Monitor と Microsoft Sentinel](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-azure-monitor) を使用して、組み込みの監視、ログ分析、高度な脅威検出を有効にします。 | 早期の脅威検出を提供します。 | なし - エンドユーザーへの影響なし | High – Azure Monitor の統合 |

#### 優先順位 3: 長期的な実装

これらは、時間の経過とともに姿勢を強化するための反復的で継続的な改善です。

| **セキュリティ機能** | **説明** | **対処された脅威** | **ユーザーへの影響** | **実装作業** |
| --- | --- | --- | --- | --- |
| **[監視とアラート](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-azure-monitor)** | 精度を向上させるために、監視とアラートのしきい値を微調整します。 | 正確な監視を提供します。 | なし - エンドユーザーへの影響なし | 中 – セットアップが必要 |
| **継続的なセキュリティ強化と新機能** | 3 ~ 12 か月の期間を使用して、セキュリティコントロールを反復処理し、 [新しい拡張機能を採用します](https://learn.microsoft.com/ja-jp/entra/external-id/whats-new-docs?tabs=external-tenants)。 | セキュリティの脅威と脆弱性を防ぐのに役立ちます。 | 新機能に依存します。 | 新機能に依存します。 |
| **[ゼロ トラスト評価](https://learn.microsoft.com/ja-jp/security/zero-trust/assessment/overview)** | セキュリティで保護された Future Initiative (SFI) とゼロ トラストの原則に基づいて、何百ものセキュリティ設定をテストします。 | 徹底的なセキュリティ構成テストを提供します。 | なし - エンドユーザーへの影響なし | 中 – セットアップが必要 |

### 既知の制限事項

これらのセキュリティ機能には制限があります。外部テナントでは使用できません。 次の表に、これらの制限事項を示し、考えられる回避策を示します。

| **特徴** | **現在の制限事項** | **Workaround** |
| --- | --- | --- |
| **[ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)** | 外部テナントでは ID 保護はサポートされていません。 | 外部テナントでは使用できません。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/concept-supported-features-customers"} -->
## 外部テナントの機能 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-supported-features-customers
- Service: entra-external-id / external
- Article date: 2026-03-30
- Summary: 従業員の機能と外部テナント構成を比較します。 外部 ID の各シナリオにどのテナントの種類が該当するかを確認します。

Microsoft Entra テナントを構成するには、組織がテナントを使用する方法と管理するリソースに応じて、次の 2 つの方法があります。

- *従業員*テナント構成は、従業員、社内ビジネス アプリ、およびその他の組織リソースを対象としています。 従業員テナントは、外部のビジネス パートナーやゲストとのコラボレーションのために、Microsoft Entra 外部 ID の B2B コラボレーションを使用します。
- *外部*テナント構成は、コンシューマーまたはビジネスユーザーにアプリを発行する外部 ID シナリオ専用です。

この記事では、従業員と外部テナントの機能の詳細な比較を示します。 これらのテナントの詳細については、「 [Microsoft Entra 外部 ID のワークフォースおよび外部テナントの構成」を](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations)参照してください。

Note

プレビュー期間中、Premium ライセンスを必要とする機能は、外部テナントでは使用できません。

### 一般的な機能の比較

次の表は、従業員と外部テナントの一般的な機能を比較しています。

| Feature | 従業員テナント | 外部テナント |
| --- | --- | --- |
| 外部アイデンティティのシナリオ | ビジネス パートナーやその他の外部ユーザーが従業員と共同作業できるようにします。 ゲストは、招待またはセルフサービス サインアップを通じて、ビジネス アプリケーションに安全にアクセスできます。 | 外部 ID を使用して、アプリケーションをセキュリティで保護します。 コンシューマーとビジネスのお客様は、セルフサービス サインアップを通じてコンシューマー アプリにアクセスできます。 招待もサポートされています。 |
| ローカル アカウント | ローカル アカウントは、組織の*内部*メンバーに対してのみサポートされます。 | ローカル アカウントは、次の目的でサポートされています。<br>- セルフサービス サインアップを使用するコンシューマーとビジネス 顧客。<br>- 管理者が作成した内部アカウント (管理者ロールの有無にかかわらず)。<br><br> 外部テナントのすべてのユーザーには、[管理者ロールが割り当てられている](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-user-permissions)場合を除き、[既定のアクセス許可](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-admin-accounts)があります。 |
| グループ | [グループ](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)を使用して、管理アカウントとユーザー アカウントを管理します。 | グループを使用して管理アカウントを管理します。 Microsoft Entra のグループと[アプリケーション ロール](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-use-app-roles-customers)のサポートは、顧客テナントへと段階的に移行されています。 最新の情報を確認するには、「[グループとアプリケーション ロールのサポート](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-group-app-roles-support)」を参照してください。 |
| ロールと管理者 | [ロールと管理者](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-subscriptions-associated-directory)は、管理アカウントとユーザー アカウントで完全にサポートされています。 | ロールは、すべてのユーザーでサポートされています。 外部テナントのすべてのユーザーには、[管理者ロール](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-user-permissions)が割り当てられている場合を除き、[既定のアクセス許可](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-admin-accounts)があります。 |
| Microsoft Entra ID 保護（マイクロソフト エントラ ID 保護） | この製品は、Microsoft Entra テナントの継続的なリスク検出を提供します。 これにより、組織は ID ベースのリスクを検出、調査し、修復できます。 | 未提供 |
| Microsoft Entra ID ガバナンス | この製品を使用すると、組織は、セキュリティで保護された特権アクセスと共に、ID とアクセスのライフサイクルを管理できます。 [詳細については、こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview)。 | 未提供 |
| セルフサービスパスワードリセット | ユーザーが最大 2 つの認証方法を使用してパスワードをリセットできるようにします。 | ワンタイム パスコードまたは SMS を使用して電子メールを使用して、ユーザーが自分のパスワードをリセットできるようにします。 [詳細については、こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers)。 |
| 言語のカスタマイズ | ユーザーが企業イントラネットまたは Web ベースのアプリケーションに対して認証を行うときに、ブラウザーの言語に基づいてサインイン エクスペリエンスをカスタマイズします。 | サインインおよびサインアップ プロセスの一環として顧客に表示される文字列を言語に応じて変更します。 [詳細については、こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-branding-customers)。 |
| カスタム属性 | ディレクトリ拡張属性を使用すると、ユーザー オブジェクト、グループ、テナントの詳細、およびサービス プリンシパルに関して、より多くのデータを Microsoft Entra ディレクトリに格納できます。 | ディレクトリ拡張属性を使用すると、ユーザー オブジェクトに関して、より多くのデータを顧客ディレクトリに格納できます。 カスタム ユーザー属性を作成して、サインアップ ユーザー フローに追加できます。 [詳細については、こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes)。 |
| 料金 | B2B コラボレーション () を通じて、外部ゲストの`UserType=Guest`を取得します。 | ロールや値に関係なく、外部テナント内のすべてのユーザーの `UserType`を取得します。 |

### インターフェイスのカスタマイズ

次の表は、従業員と外部テナントのインターフェイスカスタマイズの機能を比較しています。

| Feature | 従業員テナント | 外部テナント |
| --- | --- | --- |
| 会社のブランド化 | すべてのサインイン エクスペリエンスに適用される[会社のブランド](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding)を追加して、ユーザーに一貫したエクスペリエンスを提供できます。 | 従業員テナントと同じ。 [詳細については、こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers)。 |
| 言語のカスタマイズ | [ブラウザー言語でサインイン エクスペリエンスをカスタマイズ](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding)できます。 | 従業員テナントと同じ。 [詳細については、こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-languages-customers)。 |
| カスタム ドメイン名 | 管理アカウントに対してのみ、[カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)を使用できます。 | 外部テナントの [カスタム URL ドメイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-url-domain) 機能を使用して、独自のドメイン名でアプリのサインイン エンドポイントをブランド化できます。 |
| モバイル アプリのネイティブ認証 | 未提供 | Microsoft Entra [ネイティブ認証](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication) を使用すると、モバイル アプリケーションのサインイン エクスペリエンスの設計を完全に制御できます。 |

### 独自のビジネス ロジックの追加

[カスタム認証拡張機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-extensions)を使用して、外部システムと統合することで、Microsoft Entra 認証エクスペリエンスをカスタマイズできます。 カスタム認証拡張機能は、基本的にイベント リスナーです。 アクティブ化すると、独自のビジネス ロジックを定義する REST API エンドポイントへの HTTP 呼び出しが行われます。

次の表では、従業員と外部テナントのカスタム認証拡張機能のイベントを比較します。

| Event | 従業員テナント | 外部テナント |
| --- | --- | --- |
| `TokenIssuanceStart` | [外部システムからの要求を追加します](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview)。 | [外部システムからの要求を追加します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-extensions)。 |
| `OnAttributeCollectionStart` | 未提供 | このイベントは、サインアップの属性コレクション ステップの開始時に、属性コレクション ページがレンダリングされる前に発生します。 値の事前入力やブロッキング エラーの表示などのアクションを追加できます。 [詳細については、こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-attribute-collection?tabs=start-continue,submit-continue)。 |
| `OnAttributeCollectionSubmit` | 未提供 | このイベントは、ユーザーが属性を入力して送信した後、サインアップ フロー中に発生します。 ユーザーの入力の検証や変更などのアクションを追加できます。 [詳細については、こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-attribute-collection?tabs=start-continue,submit-continue)。 |
| `OnOtpSend` | 未提供 | ワンタイム パスコード送信イベント用にカスタム 電子メール プロバイダーを構成します。 [詳細については、こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-email-otp-get-started?tabs=azure-communication-services,azure-portal)。 |

### ID プロバイダーと認証方法

次の表では、従業員と外部テナントのプライマリ認証と多要素認証 (MFA) の [ID プロバイダー](https://learn.microsoft.com/ja-jp/entra/external-id/identity-providers) と方法を比較します。

| Feature | 従業員テナント | 外部テナント |
| --- | --- | --- |
| 外部ユーザーの ID プロバイダー (プライマリ認証) | セルフサービス サインアップ ゲストの場合:<br>- Microsoft Entra アカウント<br>- Microsoft アカウント<br>- 電子メールで送信されたワンタイム パスコード<br>- Google のフェデレーション<br>- Facebook フェデレーション<br><br>招待されたゲストの場合:<br>- Microsoft Entra アカウント<br>- Microsoft アカウント<br>- 電子メールで送信されたワンタイム パスコード<br>- Google のフェデレーション<br>- SAML/WS-Fed フェデレーション | セルフサービス サインアップ ユーザー (コンシューマー、ビジネス ユーザー) の場合:<br>- 外部 ID で使用できる認証方法<br><br>ディレクトリ ロール (管理者など) を介して招待されたゲスト (プレビュー) の場合:<br>- Microsoft Entra アカウント<br>- Microsoft アカウント<br>- [電子メールで送信されたワンタイム パスコード](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers#email-with-one-time-passcode-sign-in)<br>- [SAML/WS-Fed フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation)<br><br> 外部ユーザーは管理目的でのみ招待できます。 この機能を使用して、アプリにサインインするように顧客を招待することはできません。 この機能は、顧客 ID およびアクセス管理 (CIAM) ユーザー フローと互換性がありません。 |
| MFA の認証方法 | 内部ユーザー (従業員と管理者) の場合:<br>- [認証と検証の方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)<br><br>ゲスト (招待またはセルフサービス サインアップ) の場合:<br>- [ゲスト MFA の認証方法](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access#table-1-authentication-strength-mfa-methods-for-external-users) | セルフサービス サインアップ ユーザー (コンシューマー、ビジネス ユーザー) の場合:<br>- [電子メールで送信されたワンタイム パスコード](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers#email-one-time-passcode)<br>- [SMS ベースの認証](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers#sms-based-authentication)<br>- [Passkey (FIDO2)](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-with-passkey)<br><br>招待されたユーザーの場合 (プレビュー):<br>- [電子メールで送信されたワンタイム パスコード](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers#email-one-time-passcode)<br>- [SMS ベースの認証](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers#sms-based-authentication) |

#### 外部 ID で使用できる認証方法

ユーザーがアプリケーションにサインインする際の主な要素として、ユーザー名やパスワードなど、いくつかの認証方法を使用できます。 その他の認証方法は、セカンダリ要素としてのみ使用できます。 次の表は、サインイン中に認証方法を使用できる場合、セルフサービス サインアップ、セルフサービス パスワード リセット、および外部 ID の MFA を使用できる場合の概要を示しています。

| Method | Sign-in | Sign-up | パスワードのリセット | MFA |
| --- | --- | --- | --- | --- |
| [メールとパスワード](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers#email-and-password-sign-in) |  |  |  |  |
| [ワンタイム パスコードの電子メール送信](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers#email-with-one-time-passcode-sign-in) |  |  |  |  |
| [SMS ベースの認証](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers#sms-based-authentication) |  |  |  |  |
| [Passkey (FIDO2)](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-with-passkey) |  |  |  |  |
| [Apple フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-apple-federation-customers) |  |  |  |  |
| [Facebook フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-facebook-federation-customers) |  |  |  |  |
| [Google フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers) |  |  |  |  |
| Microsoft 個人用アカウント ([OpenID Connect](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers)) |  |  |  |  |
| [Microsoft Entra ID フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-entra-id-federation-customers) |  |  |  |  |
| [OpenID Connect フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers) |  |  |  |  |
| [SAML/WS-Fed フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation) |  |  |  |  |

### アプリケーションの登録

次の表は、テナントの種類ごとの [アプリケーション登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) の機能を比較しています。

| Feature | 従業員テナント | 外部テナント |
| --- | --- | --- |
| プロトコル | プロトコルには、SAML 証明書利用者、OpenID Connect、OAuth2 が含まれます。 | プロトコルには、 [SAML 証明書利用者](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-register-saml-app)、 [OpenID Connect](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-register-ciam-app)、OAuth2 が含まれます。 |
| サポートされているアカウントの種類 | 次の [種類のアカウント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#register-an-application) を使用できます。 <br>- この組織ディレクトリ内のアカウントのみ (シングル テナント)<br>- 任意の組織ディレクトリ内のアカウント (マルチテナント構成内の任意の Microsoft Entra テナント)<br>- 任意の組織ディレクトリ内のアカウント (マルチテナント構成の Microsoft Entra テナント) と個人の Microsoft アカウント (Skype や Xbox など)<br>- 個人用の Microsoft アカウントのみ | 常に、この組織のディレクトリ内のアカウントのみを使用します (シングル テナント)。 |
| プラットフォーム | 次 [のプラットフォームを](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri) 使用できます。 <br>- パブリック クライアント/ネイティブ (モバイルおよびデスクトップ)<br>- Web<br>- シングルページ アプリケーション (SPA) | 次 [のプラットフォームを](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri) 使用できます。 <br>- パブリック クライアント (モバイルとデスクトップ)<br>- Web<br>- SPA<br>- [モバイル](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication) アプリケーションと[シングルページ アプリケーションの](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-native-authentication-cors-solution-production-environment)ネイティブ認証 |
| 認証用のリダイレクト URI | Microsoft Entra ID は、ユーザーの認証またはサインアウトに成功した後に認証応答 (トークン) を返すときに、これらの URI を宛先として受け入れます。 | 従業員テナントと同じ。 |
| 認証用のフロント チャネル ログアウト URL | この URL では、アプリケーションがユーザーのセッション データをクリアするように Microsoft Entra ID が要求を送信します。 シングル サインアウトが正しく機能するためには、フロント チャネルログアウト URL が必要です。 | 従業員テナントと同じ。 |
| 認証のためのインプリシットグラントとハイブリッドフロー | アプリケーションが承認エンドポイントからトークンを直接要求します。 | 従業員テナントと同じ。 |
| 証明書とシークレット | 複数の資格情報を使用できます。 <br>- [Certificates](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=certificate)<br>- [クライアント シークレット](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=client-secret)<br>- [フェデレーション資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=federated-credential) | 従業員テナントと同じ。 |
| 証明書とシークレットのローテーション | ユーザーが引き続きサインインできるように、クライアント資格情報を更新して、有効で安全な状態を維持できるようにします。 [証明書](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=certificate)、シークレット、[フェデレーション資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=client-secret)[を](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=federated-credential)ローテーションするには、新しい資格情報を追加してから古い資格情報を削除します。 | 従業員テナントと同じ。 |
| 証明書とシークレットのポリシー | シークレットと証明書の制限を適用するように [アプリケーション管理ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-enforce-secret-standards) を構成します。 | 未提供 |
| API のアクセス許可 | アプリケーションへのアクセス許可を追加、削除、または置換できます。 アプリケーションにアクセス許可が追加された後は、ユーザーまたは管理者が新しいアクセス許可に同意する必要があります。 [Microsoft Entra ID でアプリの要求されたアクセス許可を更新する方法について説明します](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-update-permissions)。 | 許可されているアクセス許可は、Microsoft Graph `offline_access`、 `openid`、 `User.Read`、 **およびマイ API の** 委任されたアクセス許可です。 管理者のみが組織を代表して同意できます。 |
| API の公開 | API が保護するのに役立つデータと機能へのアクセスを制限する[カスタム スコープを定義](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-expose-web-apis)します。 この API の一部へのアクセスを必要とするアプリケーションは、これらのスコープの 1 つ以上に対してユーザーまたは管理者の同意を要求できます。 | 従業員テナントと同じ。 |
| 所有者 | アプリケーション所有者は、アプリケーションの登録を表示および編集できます。 また、アプリケーションを管理するための管理者特権を持つ任意のユーザー (一覧に示されていない場合もあります) も、アプリケーションの登録を表示および編集できます ([クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)など)。 | 従業員テナントと同じ。 |
| ロールと管理者 | [管理者ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)は、Microsoft Entra ID の特権アクションへのアクセスを許可するために使用されます。 | 外部テナントのアプリには、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)ロールのみを使用できます。 このロールは、アプリケーションの登録とエンタープライズ アプリケーションに関するすべての側面について、作成と管理の権限を付与します。 |

#### アプリケーションのアクセス制御

次の表は、テナントの種類ごとのアプリケーション承認の機能を比較したものです。

| Feature | 従業員テナント | 外部テナント |
| --- | --- | --- |
| ロールベースのアクセス制御（RBAC） | [アプリケーションのアプリケーション ロール](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-rbac-for-developers#app-roles)を定義し、それらのロールをユーザーとグループに割り当てることができます。 Microsoft Entra ID には、セキュリティ トークンにユーザー ロールが含まれています。 その後、アプリケーションは、セキュリティ トークンの値に基づいて承認の決定を行うことができます。 | 従業員テナントと同じ。 [外部テナントのアプリケーションにロールベースのアクセス制御を使用する方法について説明します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-use-app-roles-customers)。 使用可能な機能については、「 [グループとアプリケーション ロールのサポート](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-group-app-roles-support)」を参照してください。 |
| セキュリティ グループ | [セキュリティ グループ](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-rbac-for-developers#groups)を使用して、特定のグループ内のユーザーのメンバーシップがロール メンバーシップとして解釈されるアプリケーションに RBAC を実装できます。 Microsoft Entra ID には、セキュリティ トークンにユーザー グループ メンバーシップが含まれています。 その後、アプリケーションは、セキュリティ トークンの値に基づいて承認の決定を行うことができます。 | 従業員テナントと同じ。 [グループの省略可能な要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims#configure-groups-optional-claims)は、グループ オブジェクト ID に制限されます。 |
| 属性ベースのアクセス制御 (ABAC) | アクセス トークンにユーザー属性を含むようにアプリを構成できます。 その後、アプリケーションは、セキュリティ トークンの値に基づいて承認の決定を行うことができます。 詳細については、「トークンの カスタマイズ」を参照してください。 | 従業員テナントと同じ。 |
| ユーザーの割り当てを要求する | ユーザーの割り当てが必要な場合は、アプリケーションに割り当てたユーザー (直接ユーザーの割り当てまたはグループ メンバーシップに基づく) のみがサインインできます。 詳細については、「 [アプリケーションへのユーザーとグループの割り当てを管理する」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-restrict-your-app-to-a-set-of-users)参照してください。 | 従業員テナントと同じ。 詳細については、「 [グループとアプリケーション ロールのサポート](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-group-app-roles-support)」を参照してください。 |

### エンタープライズ アプリケーション

次の表は、従業員と外部テナントでの [エンタープライズ アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/) 登録の一意の機能を比較しています。

| Feature | 従業員テナント | 外部テナント |
| --- | --- | --- |
| アプリケーション ギャラリー | [アプリケーション ギャラリー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/overview-application-gallery)には、Microsoft Entra ID に統合された何千ものアプリケーションが含まれています。 | 統合されたアプリの範囲から選択します。 パートナー アプリを検索するには、検索バーを使用します。 アプリケーション ギャラリー カタログは使用できません。 |
| カスタム エンタープライズ アプリケーションを登録する | [エンタープライズ アプリケーションを追加します。](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal) | [外部テナントに SAML アプリを登録します。](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-register-saml-app) |
| セルフサービス アプリケーションの割り当て | ユーザー [がアプリを自己検出できるようにします](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-self-service-access)。 | [マイ アプリ ポータル](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/myapps-overview)でのセルフサービス アプリケーションの割り当ては使用できません。 |
| アプリケーション プロキシ | [Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy) は、オンプレミスの Web アプリケーションへの安全なリモート アクセスを提供します。 | 未提供 |
| アプリの登録を非アクティブ化する | 構成を保持しながらトークンの発行を防ぐために[、アプリの登録を非アクティブ化](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/deactivate-app-registration)します。 | 従業員テナントと同じ。 |

#### エンタープライズ アプリケーションの同意とアクセス許可の機能

次の表は、テナントの種類ごとにエンタープライズ アプリケーションで使用できる同意とアクセス許可の機能を示しています。

| Feature | 従業員テナント | 外部テナント |
| --- | --- | --- |
| エンタープライズ アプリケーションの管理者の同意 | [テナント全体の管理者アクセス許可を付与](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent)できます。 また、 [それらを確認して取り消](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-application-permissions?pivots=portal) すこともできます。 | 従業員テナントと同じ。 |
| エンタープライズ アプリケーションに対するユーザーの同意 | [ユーザーがアプリケーションに同意する方法を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent)構成し、[これらのアクセス許可を更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-update-permissions)できます。 | [管理者の同意](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent)を必要としないアクセス許可に限定されます。 |
| 管理者の同意を確認または取り消す | アクセス許可[を確認して取り消します](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-application-permissions)。 | [Microsoft Entra 管理センターを使用して、管理者](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-application-permissions?pivots=portal) の同意を取り消します。 |
| ユーザーの同意を確認または取り消す | アクセス許可[を確認して取り消します](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-application-permissions)。 | [Microsoft Graph API](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-application-permissions?pivots=ms-graph) または [PowerShell](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-application-permissions?pivots=entra-powershell) を使用して、ユーザーの同意を取り消します。 |
| アプリにユーザーまたはグループを割り当てる | 個人またはグループベースの割り当てで [アプリへのアクセスを管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-access-management) できます。 [入れ子になったグループ](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups) メンバーシップは現在サポートされていません。 | 従業員テナントと同じ。 |
| アプリ ロールの RBAC | きめ細かな [アクセス制御](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-use-app-roles-customers)のためにロールを定義して割り当てることができます。 | 従業員テナントと同じ。 |

### OpenID Connect と OAuth2 のフロー

次の表は、テナントの種類ごとに OAuth 2.0 と OpenID Connect 承認フローの機能を比較したものです。

| Feature | 従業員テナント | 外部テナント |
| --- | --- | --- |
| [OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc) | Yes | Yes |
| [承認コード](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) | Yes | Yes |
| [Code Exchange (PKCE) の証明キーを使用した承認コード](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) | Yes | Yes |
| [クライアントの資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow) | Yes | [v2.0 アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest) |
| [デバイスの承認](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code) | Yes | Yes |
| [On-Behalf-Of フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow) | Yes | Yes |
| [暗黙的な許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow) | Yes | Yes |
| [リソース所有者のパスワード資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc) | Yes | いいえ;モバイル アプリケーションの場合は、[ネイティブ認証](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication)を使用する |

#### OpenID Connect と OAuth2 のフローでの機関 URL

機関 URL は、Microsoft Authentication Library (MSAL) がトークンを要求できるディレクトリを示します。 外部テナントのアプリの場合は、常に次の形式を使用します: `<tenant-name>.ciamlogin.com`。

次の JSON は、機関 URL を持つ .NET アプリケーション `appsettings.json` ファイルの例を示しています。

```json
{
    "AzureAd": {
        "Authority": "https://<Enter_the_Tenant_Subdomain_Here>.ciamlogin.com/",
        "ClientId": "<Enter_the_Application_Id_Here>"
    }
}
```

### 条件付きアクセス

次の表は、テナントの種類ごとの Microsoft Entra 条件付きアクセスの機能を比較しています。

| Feature | 従業員テナント | 外部テナント |
| --- | --- | --- |
| Assignments | [ユーザー、グループ](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-users-groups)、 [およびワークロード ID](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-users-groups#workload-identities)。 | すべてのユーザーを含め、ユーザーとグループを除外します。 詳細については、「[多要素認証 (MFA) をアプリに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers)」を参照してください。 |
| ターゲット リソース | - [クラウド アプリ](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps)<br>- [ユーザー アクション](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#user-actions)<br>- [グローバル セキュア アクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#traffic-forwarding-profiles)<br>- [認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#authentication-context) | - [すべてのリソース、選択したアプリ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers)、または [アプリケーションのフィルター処理](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-filter-for-applications)<br>- [認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#authentication-context) |
| Conditions | - [サインイン リスク](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#sign-in-risk)<br>- [ユーザー リスク](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#user-risk)<br>- [デバイス プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#device-platforms)<br>- [Locations](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#locations)<br>- [クライアント アプリ](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#client-apps)<br>- [デバイスのフィルター](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#filter-for-devices) | - [デバイス プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#device-platforms)<br>- [Locations](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#locations) |
| 付与 | [リソースへのアクセスの許可またはブロック](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant) | - [\[アクセスのブロック\]](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant#block-access)<br>- [多要素認証の要求](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers)<br>- [パスワードのリセットの要求](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers) |
| セッション | [セッション コントロール](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-session) | 次のセッション コントロールを使用できます。 <br>- サインイン頻度<br>- 永続的なブラウザー セッション |

### 使用条件ポリシー

次の表は、テナントの種類ごとに使用条件ポリシーの機能を比較しています。

| Feature | 従業員テナント | 外部テナント |
| --- | --- | --- |
| 条件付きアクセス ポリシー | [Microsoft Entra の使用条件を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use)参照してください。 | 未提供 |
| セルフサービス サインアップ | 未提供 | サインアップ ページで、使用条件ポリシーにリンクされている [必要な属性](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes#configure-a-single-select-checkbox-checkboxsingleselect) を追加します。 ハイパーリンクをカスタマイズして、さまざまな言語をサポートできます。 |
| サインイン ページ | [会社のブランドを](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding)使用して、プライバシー情報の右下隅にリンクを追加できます。 | [労働力と同じです](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers#to-customize-the-logo-privacy-link-and-terms-of-use)。 |

### アカウント管理

次の表は、テナントの種類ごとのユーザー管理の機能を比較しています。 表に記載されているように、特定のアカウントの種類は、招待またはセルフサービス サインアップを通じて作成されます。 テナントのユーザー管理者は、管理センターを使用してアカウントを作成することもできます。

| Feature | 従業員テナント | 外部テナント |
| --- | --- | --- |
| アカウントの種類 | - 従業員や管理者などの内部メンバー。<br>- [招待された](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)、またはセルフサービス サインアップを使用する外部ユーザー。 | - セルフサービス サインアップまたは [管理者によって作成された](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-customer-accounts)外部ユーザー。<br>- 管理者ロールの有無にかかわらず、内部ユーザー。<br>- 管理者ロールの有無にかかわらず、招待されたユーザー (プレビュー)。<br><br> 外部テナントのすべてのユーザーには、[管理者ロール](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-user-permissions)が割り当てられている場合を除き、[既定のアクセス許可](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-admin-accounts)があります。 |
| ユーザー プロファイル情報を管理する | - プログラムで管理し、 [管理センターを使用してユーザーを管理します](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-user-profile-info)。<br>- [テナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)を使用して、テナント間でゲスト ユーザーを管理します。 | 従業員の場合と同様に、ただしテナント間同期は利用できません。 |
| ユーザーのパスワードをリセットする | 管理者は、 [ユーザーがパスワードを](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-reset-password-azure-portal) 忘れた場合、デバイスからロックアウトされている場合、またはパスワードを受信しなかった場合に、ユーザーのパスワードをリセットできます。 | 従業員テナントと同じ。 |
| 最近削除したユーザーを復元または削除する | ユーザーを削除した後、アカウントは 30 日間、中断状態のままになります。 その 30 日の期間中は、ユーザー アカウントをそのすべてのプロパティと共に復元することができます。 | 従業員テナントと同じ。 |
| アカウントを無効にする | 新しいユーザーがサインインできないようにします。 | 従業員テナントと同じ。 |

### パスワード保護

次の表は、テナントの種類ごとのパスワード保護の機能を比較しています。

| Feature | 従業員テナント | 外部テナント |
| --- | --- | --- |
| スマート ロックアウト | [スマート ロックアウト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout) は、ユーザーのパスワードを推測したり、ブルート フォースメソッドを使用してアクセスしようとする不適切なアクターをロックアウトするのに役立ちます。 | 従業員テナントと同じ。 |
| グローバル禁止パスワード | [グローバル禁止パスワード リスト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad#global-banned-password-list)は、Microsoft Entra セキュリティ データの分析に基づいて、一般的に使用される脆弱なパスワードまたは侵害されたパスワードを自動的にブロックします。 | 従業員テナントと同じ。 |
| カスタム禁止パスワード | [カスタム禁止パスワード リスト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-configure-custom-password-protection)を使用して、パスワードの作成とリセット中に評価およびブロックする特定の文字列を追加します。 | 従業員テナントと同じ。 |

### トークンのカスタマイズ

次の表は、テナントの種類ごとのトークンカスタマイズの機能を比較しています。

| Feature | 従業員テナント | 外部テナント |
| --- | --- | --- |
| クレーム マッピング | エンタープライズ アプリケーション用に JSON Web Token (JWT) で発行される[要求をカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity-platform/jwt-claims-customization)できます。 | 従業員テナントと同じ。 オプションの要求は、[\[属性と要求\]](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-attributes-to-token) を使用して構成する必要があります。 |
| クレームの変換 | エンタープライズ アプリケーションの JWT で発行された[ユーザー属性に変換を適用](https://learn.microsoft.com/ja-jp/entra/identity-platform/jwt-claims-customization)します。 | 従業員テナントと同じ。 |
| カスタム クレーム プロバイダー | 外部 REST API を呼び出して外部システムから要求をフェッチする [カスタム認証拡張機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview) を使用します。 | 従業員テナントと同じ。 [詳細については、こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview)。 |
| セキュリティ グループ | [グループのオプションの要求事項を設定します](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims#configure-groups-optional-claims)。 | グループ オブジェクト ID に限定して、[グループの省略可能な要求を構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims#configure-groups-optional-claims)します。 |
| トークンの有効期間 | Microsoft Entra ID によって発行されるセキュリティ トークンの[有効期間を指定](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes)します。 | 従業員テナントと同じ。 |
| セッションとトークンの失効 | 管理者は、ユーザー [のすべての更新トークンとセッションを無効](https://learn.microsoft.com/ja-jp/graph/api/user-revokesigninsessions) にすることができます。 | 従業員テナントと同じ。 |

### 単一サインイン

[シングル サインオン (SSO)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-sso) を使用すると、ユーザーが資格情報を要求される回数を減らすことで、よりシームレスなエクスペリエンスを実現できます。 ユーザーは資格情報を 1 回入力します。 他のアプリケーションは、さらにプロンプトを表示することなく、同じデバイスと Web ブラウザーで確立されたセッションを再利用できます。

次の表は、テナントの種類ごとの SSO の機能を比較しています。

| Feature | 従業員テナント | 外部テナント |
| --- | --- | --- |
| アプリケーション登録の種類 | - OpenID Connect<br>- OAuth 2.0<br>- SAML (エンタープライズ アプリケーション)<br><br>エンタープライズ アプリケーションでは、パスワードベース、リンク済み、ヘッダーベースの登録など、 [より多くのオプション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-sso-deployment)が提供されます。 | - OpenID Connect<br>- OAuth 2.0<br>- SAML (エンタープライズ アプリケーション) |
| ドメイン名 | ユーザーが認証されると、Web ブラウザーの Microsoft Entra ドメイン `login.microsoftonline.com` にセッション Cookie が設定されます。 | ユーザーが認証されると、Microsoft Entra 外部 ID ドメイン `<tenant-name>.ciamlogin.com` または Web ブラウザーの [カスタム URL ドメイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-url-domain) にセッション Cookie が設定されます。 SSO が正しく機能することを確認するには、1 つの URL ドメインを使用します。 |
| サインインしたままにする | [サインインを維持](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-stay-signed-in-prompt)するオプションをオンまたはオフにすることができます。 | [ **サインインしたままにする]** プロンプトが既定で表示されます。 変更または抑制するには、条件付きアクセス ポリシーで **永続的なブラウザー セッション** セッション制御を使用します。 [詳細については、こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers#control-the-stay-signed-in-prompt)。 |
| ユーザー プロビジョニング | System for Cross-domain Identity Management (SCIM) での [自動ユーザー プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/architecture/sync-scim) 使用して、外部 ID とサポートされているアプリの間でユーザー アカウントを同期します。 この方法では、ユーザー データを自動的に最新の状態に保ちます。 ユーザー プロビジョニングでは、差分クエリがサポートされます。 これらのクエリは、前回の更新以降の変更のみを同期します。 この動作により、パフォーマンスが向上し、システムの負荷が軽減されます。 | 従業員テナントと同じ。 |
| セッションの無効化 | SSO が無効になる可能性があり、再認証が必要なシナリオ: <br>- セッションの有効期間<br>- ブラウザーの Cookie やキャッシュのクリアなどのブラウザーの問題<br>- 条件付きアクセス ポリシー (多要素認証要件など)<br>- [セッション失効](https://learn.microsoft.com/ja-jp/graph/api/user-revokesigninsessions)<br>- 疑わしいアクティビティなどのセキュリティの問題<br><br>アプリケーションは、OpenID Connect の `login=prompt` クエリ文字列パラメーターと SAML 要求の `ForceAuthn` 属性を使用して、ユーザーに資格情報の入力を求める認証要求を指定します。 | 従業員テナントと同じ。 |
| 条件付きアクセス | [条件付きアクセス] セクションを確認します。 | [条件付きアクセス] セクションを確認します。 |
| Microsoft Entra ネイティブ認証 | 未提供 | [ネイティブ認証](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication) では、埋め込み Web ビューの SSO がサポートされます。 システム ブラウザーを介したアプリ間 SSO は、ネイティブ認証では使用できません。 |
| サインアウト | [SAML](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-out-saml-protocol) または [OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc#send-a-sign-out-request) アプリケーションがユーザーをログアウト エンドポイントに誘導すると、Microsoft Entra ID によってユーザーのセッションがブラウザーから削除され、無効になります。 | 従業員テナントと同じ。 |
| シングル サインアウト | サインアウトが成功すると、Microsoft Entra ID は、ユーザーがサインインしている他のすべての [SAML](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-out-saml-protocol) および [OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc#single-sign-out) アプリケーションにサインアウト通知を送信します。 | 従業員テナントと同じ。 |

### 統合セキュリティ ソリューション

Microsoft Entra 外部 ID は、ライフサイクル全体で ID を保護するために役立つ統合セキュリティ機能とパートナー ソリューションをサポートしています。 これらの機能には、分散型サービス拒否 (DDoS) 攻撃に対する保護、サインアップ詐欺の防止、統合監視が含まれます。

これらのソリューションは外部 ID で直接有効にし、 [Microsoft セキュリティ ストア](https://securitystore.microsoft.com/)を通じてパートナー統合にアクセスできます。 このアプローチにより、組織は複雑なセットアップを行わずに、信頼できるセキュリティ ツールを迅速に展開できます。

| Feature | 従業員テナント | 外部テナント |
| --- | --- | --- |
| サインアップ詐欺防止 | セキュリティ ストア ウィザードのエクスペリエンスは使用できません。 | [Arkose Labs](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-integrate-fraud-protection?pivots=arkose) と [HUMAN Security](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-integrate-fraud-protection?pivots=human) を使用して、サインアップ詐欺から保護し、ボットの自動攻撃をブロックします。 |
| DDoS と Web アプリケーション ファイアウォール (WAF) 保護 | セキュリティ ストア ウィザードのエクスペリエンスは使用できません。 | [Cloudflare](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-configure-waf-integration) と [Akamai](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-configure-akamai-integration) を使用して、DDoS 攻撃から保護し、WAF を使用してアプリをセキュリティで保護します。 |
| セキュリティ分析 | セキュリティ ストア ウィザードのエクスペリエンスは使用できません。 | [Azure Monitor と Microsoft Sentinel](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-azure-monitor) を使用して、ワンクリック監視、Log Analytics、高度な脅威検出を有効にします。 |

#### Akamai と Cloudflare

[Akamai](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-configure-akamai-integration) と [Cloudflare](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-configure-waf-integration) は、DDoS 保護、ボットの軽減策、WAF の機能を提供します。 これらの機能は、悪意のあるトラフィック、不正な自動化、SQL インジェクション、クロスサイト スクリプティング、API ベースの攻撃などの一般的な Web 脆弱性からアプリケーションを保護するのに役立ちます。

いずれかのサービスを外部 ID と統合すると、顧客向けの ID フローの前にこれらのセキュリティ制御を適用できます。 このアクションにより、回復性が向上し、資格情報の詰め込みやその他の ID を対象とする脅威にさらされるリスクが軽減されます。

### アクティビティ ログとレポート

次の表は、さまざまな種類のテナントのアクティビティ ログとレポートの機能を比較しています。

| Feature | 従業員テナント | 外部テナント |
| --- | --- | --- |
| [監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs) | これらのログには、アプリケーション、グループ、ユーザーへの変更など、Microsoft Entra ID に記録されたすべてのイベントの詳細なレポートが表示されます。 | 従業員テナントと同じ。 |
| [サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins) | サインイン ログは、アプリケーションやリソースへのアクセスを含め、Microsoft Entra テナント内のすべてのサインイン アクティビティを追跡します。 | 従業員テナントと同じ。 |
| [サインアップ ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ups) (プレビュー) | 未提供 | Microsoft Entra 外部 ID は、サインアップの成功と失敗した試行の両方を含め、すべてのセルフサービス サインアップ イベントをログに記録します。 |
| [プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs) | プロビジョニング ログには、ユーザー アカウントの作成、更新、削除など、テナント内のプロビジョニング イベントの詳細なレコードが表示されます。 | 未提供 |
| [保持ポリシーのアクティビティ ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs) | Microsoft Entra データ保持ポリシーは、さまざまな種類のログ (監査、サインイン、プロビジョニング ログなど) の保存期間を決定します。 | 7 日間。 |
| [アクティビティ ログのエクスポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-diagnostic-settings) | Microsoft Entra ID の診断設定を使用すると、ログを Azure Monitor と統合したり、ログをイベント ハブにストリーミングしたり、セキュリティ情報およびイベント管理 (SIEM) ツールと統合したりできます。 | [外部テナント用の Azure Monitor (プレビュー)。](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-azure-monitor) |
| [アプリケーション ユーザー アクティビティのレポート](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-insights) (2026 年 8 月 31 日廃止、 [移行ガイダンス](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-insights#migrate-from-user-insights)を参照) | 未提供 | アプリケーション ユーザー アクティビティは、ユーザーがテナント内の登録済みアプリケーションと対話する方法に関する分析を提供します。 アクティブ ユーザー、新しいユーザー、サインイン、MFA 成功率などのメトリックを追跡します。 |

### Microsoft Graph API

外部テナントでサポートされているすべての機能は、Microsoft Graph API を使用した自動化でもサポートされています。 外部テナントでプレビュー段階にある機能の一部が、Microsoft Graph を通じて一般提供される場合があります。 詳細については、「[Microsoft Graph を使用して Microsoft Entra ID とネットワーク アクセス機能を管理する](https://learn.microsoft.com/ja-jp/graph/api/resources/identity-network-access-overview)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/concept-user-attributes"} -->
## ユーザー プロファイルの属性 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes
- Service: entra-external-id / external
- Article date: 2025-04-28
- Summary: サインアップ時にユーザーから収集できるユーザー プロファイル属性と、カスタム ユーザー属性を使用してユーザー プロファイル属性を拡張する方法。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

サインアップ時に収集するユーザー属性は、ユーザーのプロファイルと共にディレクトリに格納されます。 組み込みユーザー属性から選択することも、カスタム ユーザー属性を作成することもできます。

- 市区町村、国/地域、電子メール アドレスなどの組み込みのユーザー属性は、Microsoft Entra 外部 ID で使用できます。 サインアップ中に収集する組み込みユーザー属性を選択できます。
- 収集する追加情報については、 カスタム ユーザー属性を作成できます。 テキスト ボックス、ラジオ ボタン、チェック ボックスなど、いくつかのカスタム入力コントロールをサインアップ ページに追加して属性を収集できます。 次の例は、カスタム入力コントロールを使用して、ロイヤルティ番号の属性、使用条件の使用条件の同意、およびプライバシー ポリシーの同意を収集する方法を示しています。

    [Image: 利用規約とプライバシー ポリシーのチェック ボックスが付いたサインアップ ページのスクリーンショット。]

### 組み込みユーザー属性

Microsoft Entra 外部 ID には、サインアップ中に収集可能な組み込みユーザー属性があります。 これらの属性は、 [Microsoft Entra 管理センターでユーザー フローを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)するときに構成します。

次の表は、サインアップ フロー中に収集できる組み込みユーザー属性をまとめたものです。

- *Microsoft Entra 管理センターのラベルは、Microsoft Entra 管理* センターに表示されるユーザー属性の名前です。
- *プログラム可能な名前* は、Microsoft Graph API のユーザー リソースで使用される [ユーザー](https://learn.microsoft.com/ja-jp/graph/api/resources/user/#properties) 属性の名前です。 この名前は、 [ネイティブ認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-overview)など、プログラムでこのユーザー属性を使用する場合に使用します。
- *データ型* は、ユーザー属性のデータ型です。

| Microsoft Entra 管理センターのラベル | プログラミング可能な名前 | データの種類 | 解説 |
| --- | --- | --- | --- |
| 都市 | 都市 | 糸 | 最大 128 文字までです。 |
| 国/地域 | 国 | 糸 | 最大 128 文字までです。 |
| 表示名 | 表示名 | 糸 | 最大 256 文字までです。 |
| メール アドレス | メール | 糸 | このプロパティにアクセント文字を含めることはできません。 [ネイティブ認証 API](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-overview) では、この属性は*ユーザー名*として参照されます。 |
| 姓 | givenName | 糸 | 最大文字数は 64 文字です。 |
| 役職 | 役職 | 糸 | 最大 128 文字までです。 |
| 郵便番号 | 郵便番号 | 糸 | 最大文字数は 40 文字です。 |
| 都道府県 | 状態 | 糸 | 最大 128 文字までです。 |
| 住所 | 住所 | 糸 | 最大長は 1024 文字です。 |
| 姓 | 姓 | 糸 | 最大文字数は 64 文字です。 |

### カスタムのユーザー属性

アプリで、組み込みのユーザー属性よりも多くの情報が必要な場合は、独自の属性を追加できます。 これらの属性を、"カスタム ユーザー属性" と呼びます。

カスタム ユーザー属性を定義するには、まずテナント レベルで属性を作成し、テナント内の任意のユーザー フローでその属性を使用できるようにします。 次に、その属性をサインアップ ユーザー フローに割り当てて、サインアップ ページに属性を表示する方法を構成します。

カスタム ユーザー属性を作成する方法については、 [カスタム ユーザー属性の作成に関する記事を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes#create-custom-user-attributes) 参照してください。

#### カスタム ユーザー属性の入力の種類

カスタム ユーザー属性を使用する前に、収集するカスタム属性ごとにユーザー入力を収集する最適な方法を決定します。 サインアップ時にユーザーから情報を収集するには、次の入力の種類のコントロールを使用します。

- 文字列テキスト ボックス
- ラジオ ボタン
- 複数選択チェックボックス
- 数値テキスト ボックス
- 単一選択チェック ボックス

適切なデータ型とユーザー入力の種類については、次の表を参照してください。

| データの種類 | ユーザー入力の種類 | 説明 |
| --- | --- | --- |
| 糸 | テキストボックス | 自由形式のテキスト入力フィールド。 |
| 糸 | ラジオシングルセレクト | 1 つだけ選択できる一連のラジオ ボタン。 個々のラジオ ボタンの **テキスト** には、Markdown 言語で書式設定されたハイパーリンクを含めることができます。 |
| 糸 | チェックボックス複数選択 | 複数選択が可能な一連の 1 つ以上のチェック ボックス。 個々のチェック ボックスの **テキスト** には、Markdown 言語で書式設定されたハイパーリンクを含めることができます。 |
| ブール値 | 単一選択チェックボックス | ラベル付きの単一のブール値チェック ボックス。 チェック ボックスの **ラベル** には、Markdown 言語で書式設定されたハイパーリンクを含めることができます。 |
| int | 数値テキストボックス | 自由形式の整数入力。 |

チェックボックスとラジオ ボタンには、利用規約やプライバシー ポリシーなど、他のコンテンツへのハイパーリンクを含めることができます。 この記事の冒頭の例は、組み込みの属性とカスタム属性を組み合わせたサインアップ ページを示しています。 この例では次のとおりです。

- **[表示名]** フィールドは組み込みの属性です。
- **ロイヤルティ番号**は、数値整数を受け入れる自由形式の入力フィールドを持つカスタム属性です。 この形式は、 **Int** データ型と **NumericTextBox** ユーザー入力型を使用して構成できます。
- **使用条件**と**プライバシー ポリシー**のカスタム属性は、ハイパーリンクを含むラベルを含む個別の単一選択のチェック ボックスです。 **Boolean** データ型を使用して 1 つのチェック ボックスを構成できます。既定では**、CheckboxSingleSelect** ユーザー入力の種類です。 Markdown 言語を使用して、チェックボックスのラベルにハイパーリンクを追加します。

ユーザー属性の入力の種類を構成する方法については、「 [ユーザー入力の種類の構成」の記事を参照](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes#configure-the-user-input-types-and-page-layout) してください。

#### カスタム ユーザー属性が格納されている場所

カスタム ユーザー属性は、ディレクトリに格納されているユーザー プロファイル情報を拡張するため、ディレクトリ拡張属性とも呼ばれます。 外部テナントのすべての拡張機能属性は、 *b2c-extensions-app* という名前のアプリに格納されます。 ユーザーがサインアップ時にカスタム属性の値を入力すると、その値はユーザー オブジェクトに追加され、名前付け規則 `extension_{appId-without-hyphens}_{custom-attribute-name}` を使用して Microsoft Graph API 経由で呼び出すことができます。ここでの条件は次のとおりです。

- `{appId-without-hyphens}` は、 *b2c-extensions-app* のクライアント ID の削除されたバージョンです。
- `{custom-attribute-name}` は、カスタム属性に割り当てた名前です。

たとえば、 *b2c-extensions-app* のクライアント ID が `2588a-bcdwh-tfeehj-jeeqw-ertc` され、属性名が次の場合です。

- *loyaltyNumber*、カスタム属性の名前は`extension_2588abcdwhtfeehjjeeqwertc_loyaltyNumber`。
- *その後、ロイヤルティ番号* はカスタム属性の名前として`extension_2588abcdwhtfeehjjeeqwertc_LoyaltyNumber`されます。 スペースを削除し、キャメル ケースを使用して単語を区切ります。

外部テナントに登録されている [b2c-extensions-app](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes) のアプリケーション ID を検索する方法については、*拡張機能アプリ*のアプリケーション ID の検索に関する記事を参照してください。

### Microsoft Graph API

ユーザー属性は、Microsoft Graph では *ユーザー フロー属性* と呼ばれます。 [identityUserFlowAttribute リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/identityuserflowattribute)とそれに関連付けられているメソッドを使用して、組み込みユーザー フロー属性とカスタム ユーザー フロー属性の両方を管理します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/enable-external-id-high-scale-compatibility-mode"} -->
## 外部 ID ハイ スケール互換性モード (HSC) を有効にする - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/enable-external-id-high-scale-compatibility-mode
- Service: entra-external-id / external
- Article date: 2026-03-13
- Summary: Azure AD B2C テナントで HSC モードを有効にして、既存のユーザーと資格情報を維持しながら、Microsoft Entra 外部 ID エンドポイントを採用します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

高スケール互換性 (HSC) モードを有効にして、既存の B2C ユーザー資格情報を維持しながら、中断を最小限に抑えてアプリケーションを Azure AD B2C からMicrosoft Entra 外部 IDに移行します。 大規模なMicrosoft Entra 外部 IDを評価する新しい顧客は、[ソリューションの計画](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-planning-your-solution)を参照する必要があります。

AZURE AD B2C のお客様で、移行に使用できるオプションをまだ確認していない場合は、「[Azure AD B2C から外部 ID への移行を計画する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/plan-your-migration-from-b2c-to-external-id)を参照してください。

この記事では、次の方法について説明します。

- Microsoft Graphを使用して HSC モードを有効にする
- 共存の ID スキーマを確認する
- 外部 ID エンドポイントで最初のアプリケーションをオンボードして検証する
- 追加のアプリケーションをロールアウトし、Azure AD B2C の提供終了に備える

### 前提条件

この記事では、 **高スケール互換性 (HSC) モードの移行アプローチ**を既に選択していることを前提としています。 方法 (標準モードと HSC モード) を決定する必要がある場合は、[Azure AD B2C から External ID への移行を計画](https://learn.microsoft.com/ja-jp/entra/external-id/customers/plan-your-migration-from-b2c-to-external-id)から始めてください。

Important

HSC モードの有効化は、テナント レベルの大幅な変更であり、Microsoftサポートに問い合わせてのみ元に戻すことができます。 enable API を呼び出す前に、 [HSC モードの制限](https://learn.microsoft.com/ja-jp/entra/external-id/customers/plan-your-migration-from-b2c-to-external-id#hsc-mode-limitations) (ソーシャル ID プロバイダー、パスキー、年齢制限、管理ポータル エクスペリエンス、条件付きアクセスのギャップなど) を確認し、シナリオがサポートされていることを確認します。

開始する前に、Microsoft アカウント チームに問い合わせるか、サポート チケットを発行して HSC モードの許可リストを要求してください。 このプロセスの完了には数日かかる場合があります。 テナントが許可リストに登録されるまで、ステージ 1 に進むことはありません。

Azure AD B2C テナントでカスタム属性が使用されている場合は、HSC モードを有効にする前に、すべてのカスタム属性に nonempty `description` 値があることを確認します。 enable API はカスタム属性を外部 ID コンテキストに同期し、属性に null または空の説明がある場合は失敗します。 属性の説明を確認して修正するには、「 [カスタム属性の同期が失敗する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/troubleshoot-high-scale-compatibility-mode#custom-attribute-sync-fails)」を参照してください。

### ステージ 1: HSC モードを有効にする

テナントが HSC モードの許可リストに登録されたら、次の Microsoft Graph APIを呼び出して HSC モードを有効にすることができます。 呼び出し元アカウントには、 [`Policy.ReadWrite.AuthenticationFlows`](https://learn.microsoft.com/ja-jp/graph/permissions-reference#policyreadwriteauthenticationflows) アクセス許可が必要です。

**POST**: `https://graph.microsoft.com/beta/policies/authenticationFlowsPolicy/externalIdHybridModeConfiguration`**本文**: `{}`

Important

HSC モードを有効にした後、ステージ 2 に進む前に、すべてのサービスで変更が有効になるまで最大 1 時間かかります。 HSC モードを無効にする必要がある場合は、Microsoftサポートにお問い合わせください。

### ステージ 2: 共存のためのトークン要求を確認する

共存中、アプリケーションは B2C または外部 ID エンドポイントを介してユーザーを認証します。 アプリケーションを移行する前に、重大な変更を回避するために、トークン要求の設定方法を確認します。

ほとんどのテナントでは、ID データを変更する必要はありません。 ただし、アプリケーションが特定の要求 (最も一般的に `email` または `sub` ) に依存している場合は、それらの属性が正しく設定され、出力されていることを確認します。 予期される要求が見つからない場合、または外部 ID で異なる要求名を使用すると、アプリケーションが中断する可能性があります。

**確認する一般的なシナリオ**

- `email` または `sub` に依存するアプリケーション
- `mail`が設定されていないローカル アカウント
- サインイン名またはカスタム ポリシーを使用してのみ設定された要求

Important

外部 ID では、 `sub` 要求は `oid`と同じ値に設定されていません。 アプリケーションがユーザーの`sub`と一致する`oid`要求に依存している場合は、`profile`要求を取得し、代わりに安定したユーザー識別子として使用するように`oid` スコープを要求します。

アプリケーションを移行する前に、属性がアプリケーションによって使用されるトークン要求にどのようにマップされるかを検証します。 [JWT](https://learn.microsoft.com/ja-jp/entra/identity-platform/jwt-claims-customization) 要求のカスタマイズを使用して省略可能な要求を構成することも、[カスタム拡張機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview)を使用して外部ユーザー データをトークンに追加してから発行することもできます。

アプリケーションが特定のトークン要求に依存しない場合は、ステージ 3 に進みます。

### ステージ 3: 最初の外部 ID アプリケーションをビルドする (既存の B2C ユーザーを使用)

既存の Azure AD B2C ユーザー ベースを引き続き使用しながら、外部 ID エンドポイントを使用するアプリケーションを作成して構成するには、次の手順に従います。 ネイティブ認証は省略可能であり、ネイティブ認証 API を使用するアプリにのみ適用されます。

#### アプリケーションの登録

[アプリケーションを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)方法に従って、アプリケーションを登録します。

外部 ID テナントにアプリケーションを登録する場合は、[ *この組織のディレクトリ内のアカウントのみ*] を選択します。 アプリケーションを B2C テナントに登録する場合は、 *任意の組織のディレクトリで [アカウント]* を選択します。

注

既存の Azure AD B2C アプリの登録は、アプリケーションのプロパティ、シングルテナント要件、ネイティブ認証のサポートの違いにより、外部 ID エンドポイントでは再利用できません。これには専用アプリの登録が必要です。

#### (省略可能)ネイティブ認証

ネイティブ認証を使用している場合は、ネイティブ認証のガイダンスに従って有効[にします。](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication)

#### ユーザー フローを構成する

次に、外部 ID ユーザー フローを作成し、アプリケーションに関連付けます。 Microsoft Graph APIを使用してこれを行うことができます。 詳細については、「 [認証イベント フローの作成](https://learn.microsoft.com/ja-jp/graph/api/identitycontainer-post-authenticationeventsflows?view=graph-rest-beta&tabs=http&preserve-view=true)」を参照してください。

#### (省略可能)カスタム URL ドメインとAzure Front Doorを構成する

外部 ID は、ブランド化と一貫性を維持するためにカスタム認証ドメイン ( `login.contoso.com` など) をサポートします。 [Custom URL ドメイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-url-domain)は、カスタム ドメインから基になる外部 ID エンドポイントにトラフィックをルーティングするAzure Front Doorなどのリバース プロキシを使用して実装されます。

Important

Azure Front Doorを使用する場合は、認証トラフィックが正しい ID プラットフォームに確実に送信されるように、ルーティング規則を明確に定義する必要があります。 Azure Front Doorはホスト名とパスに基づいて要求をルーティングし、各カスタム ドメインは特定のバックエンドの配信元に関連付けられます。

アプリケーションで Azure AD B2C と Microsoft Entra 外部 ID の両方の認証トラフィックを同時にサポートする必要がある場合は、同じカスタム認証ドメインを共有することはお勧めしません。 [Azure Front Door ルーティング方法](https://learn.microsoft.com/ja-jp/azure/frontdoor/routing-methods)は一度に 1 つの配信元にのみカスタム ドメインをルーティングできるため、通常、このシナリオでは、ルーティングの競合や意図しないトラフィックの混在を回避するために、外部 ID エンドポイント (B2C の場合は `login.contoso.com`、外部 ID の場合は `login-ext.contoso.com`) 用に個別のカスタム ドメインが必要になります。

#### (省略可能)カスタム認証拡張機能を追加する

一部のアプリケーションでは、属性の検証やトークン エンリッチメントなど、認証時に軽量ロジックが必要です。 カスタム認証拡張機能を使用すると、完全なカスタム ポリシーを構築することなく、認証フロー内の特定のポイントで外部 REST API を呼び出すことができます。 詳細については、 [カスタム認証拡張機能とカスタム拡張機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-extensions) の [概要に関するページを](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview)参照してください。

### ステージ 4: エンド ツー エンドのシナリオを検証する

広範にロールアウトする前に、重要な認証シナリオ全体で外部 ID 対応アプリケーションを検証します。

**推奨される検証**

- 既存の B2C 資格情報を使用して新しいアプリにサインインします。
- 発行されたトークンを調べて、次のことを確認します。
    - `oid` は B2C と同じままです。
    - `issuer` と `sub` は B2C とは異なります。
- 外部 ID 認証エンドポイント (Web またはネイティブ認証 API) を使用して、新しいアプリで新しいユーザーをサインアップします。
- レガシ B2C アプリで同じユーザーとサインインし、同じ `oid`を確認します。
- 検証後、ホストされた Web フローと外部 ID 機能で新しいアプリを使用します。

**確認する内容**

- 既存のユーザーと新しく作成されたユーザー。
- バックエンド API によって使用されるトークン要求。
- サインイン ログとエラー処理。

### ステージ 5: アプリケーションの導入を段階的に拡大し、提供終了の準備をする

最初のアプリケーションが検証された後:

- 追加のアプリケーションを一度に 1 つずつ外部 ID にオンボードします。
- 残りのアプリケーションは、準備ができるまで Azure AD B2C に保持します。
- 必要に応じて、B2C アプリケーションと外部 ID アプリケーションの共存を維持します。
- Azure AD B2C サービスの提供終了日前までに、すべての Azure AD B2C アプリケーションが Microsoft Entra 外部 ID に移行されていることを確認する必要があります。
- すべてのアプリケーションが外部 ID エンドポイントで実行されたら、それ以上の操作は必要ありません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/faq-customers"} -->
## よく寄せられる質問 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/faq-customers
- Service: entra-external-id / external
- Article date: 2026-05-20
- Summary: Microsoft Entra 外部 IDについてよく寄せられる質問に対する回答を見つけます。 Azure AD B2C と外部 ID の価格、機能、および将来について説明します。

この記事では、Microsoft Entra 外部 IDに関してよく寄せられる質問に回答します。 Microsoftの現在の外部 ID 機能と次世代プラットフォーム (Microsoft Entra 外部 ID) の取り組みをより深く理解するのに役立つガイダンスを提供します。

この FAQ では、顧客 ID およびアクセス管理 (CIAM) について記載しています。 CIAM は、外部 ID のユース ケース (パートナー、顧客、市民) の ID、認証、認可を管理するソリューションを対象とする、業界公認のカテゴリです。 一般的な機能には、セルフサービス機能、アダプティブ アクセス、シングル サインオン (SSO)、独自 ID 持ち込み (BYOI) などがあります。

### External ID の価格

#### 外部 ID はどのように課金されますか?

Microsoft Entra 外部 ID価格は、月間アクティブ ユーザー (MAU) に基づきます。これは、カレンダー月内の認証アクティビティを持つ一意のユーザーの数です。 External ID は、コア オファーとプレミアム アドオンで構成されています。 Microsoft Entra 外部 IDのコアオファリングは、最初の50,000 MAUまで無料です。 使用量の課金と価格に関する最新情報については、 Microsoft Entra 外部 IDを参照してください。

注

以前に Azure Active Directory B2C (Azure AD B2C) または Azure AD 外部 ID P1/P2 SKU で B2B コラボレーションをサブスクライブしている場合は、現在の価格オプションと使用可能なアップグレード パスの詳細については、「[外部 ID の価格](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing)」ページを参照してください。

#### 50,000 MAU Free レベルはアドオンに適用されますか?

いいえ。外部 ID アドオンには Free レベルはありません。 価格の詳細については、 [外部 ID の価格に関するページを参照](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing)してください。

#### 外部 ID には SMS による電話認証がありますか?

現在、SMS は、外部テナントでの第 1 要素認証またはセルフサービス パスワード リセットには使用できません。 ただし、SMS は、追加コストで外部テナントの第 2 要素検証に使用できるようになりました。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers#sms-based-authentication)

#### 外部テナントをサブスクリプションにリンクしましたが、ライセンスの状態は引き続き "無料" と表示されます

外部テナントをサブスクリプションにリンクすると、外部テナントのホーム ページ (**ホーム**&gt;**ビリング**) で表示できます。 ただし、外部テナントの概要ページ (**Home**&gt;**Tenant overview**&gt;**Overview**) のライセンスには、引き続き **Microsoft Entra ID Free** が表示されます。 この既知の問題の解決に取り組んでいます。

#### Azure AD 外部 ID の請求書に "Microsoft Entra 外部 ID?" という名前の電話料金が表示される理由

Azure AD External Identities SMS Phone Authentication の新しい[ビリング モデル](https://azure.microsoft.com/pricing/details/active-directory-b2c/)に続いて、請求書に電話 MFA の新しい名前が表示されることがあります。 これで、 [国または地域の価格レベル](https://aka.ms/ExternalIDSMSCountries)に基づいて、次の名前が表示されます。

- Microsoft Entra 外部 ID - 電話認証 低コスト 1 件のトランザクション
- Microsoft Entra 外部 ID - 電話認証 中低コスト 1件のトランザクション
- Microsoft Entra 外部 ID - 電話認証 中程度-高コスト 1件のトランザクション
- Microsoft Entra 外部 ID - 1 トランザクションあたりの電話認証の高コスト

新しい請求書にはMicrosoft Entra 外部 IDが記載されていますが、Azure AD 外部 ID の顧客の場合は、**コア MAU 数**に基づいて、Azure AD B2B に対して引き続き課金されます。

### 外部 ID について

#### Microsoft Entra 外部 IDとは

Microsoft Entra 外部 IDは次世代 CIAM プラットフォームです。 これは、お客様、パートナー、市民など、すべての外部 ID にわたる安全かつ魅力的なエクスペリエンスを 1 つの統合プラットフォームに統合するときの進化的な手順を表しています。

#### Microsoft Entra 外部 ID は Azure AD B2C の新しい名前ですか?

いいえ、Azure AD B2C の新しい名前ではありません。 Microsoft Entra 外部 IDは、CIAM のユース ケースと B2B コラボレーション機能を 1 つの統合プラットフォームに組み合わせた次世代 CIAM ソリューションです。

#### 管理センターと Web サイトの両方で、いくつかの名前が変更されていることに気付きました

はい。管理センターとメッセージングで、外部 ID のビジョンに最も適合するように、いくつかの項目のブランドを変更しました。 次の表に変更をまとめています。

| 以前の名前 | 新しい名前 |
| --- | --- |
| Azure AD External Identities | Azure AD B2C |
| Azure AD B2B | Microsoft Entra 外部 IDの一部になりました |
| 顧客向けの Azure AD | Microsoft Entra 外部 ID |
| Azure AD B2B コラボレーション | 外部 ID B2B コラボレーション |
| Azure AD B2B 直接接続 | 外部 ID B2B 直接接続 |
| 顧客テナント | 外部テナント |

注

Microsoft Entra テナントは、ワークフォース テナント構成または外部テナント構成で作成できます。 [テナント構成の詳細を確認します](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations)。

#### 外部 ID の使用を開始するにはどうすればよいですか?

Microsoft Entra 管理センターで外部テナントをを作成して、コンシューマーおよびビジネス顧客のアプリのセキュリティ保護を開始します。

### AZURE AD B2C と Azure AD 外部 ID

#### Azure AD B2C と Azure AD 外部 ID はどうなっていますか?

2025 年 5 月 1 日より、AZURE AD B2C P1 と P2 は新規顧客向けに購入できなくなりますが、現在の Azure AD B2C のお客様は引き続き製品を使用できます。 新しいテナントやユーザー フローの作成を含む製品エクスペリエンスは変更されません。 サービス レベル アグリーメント (SLA)、セキュリティ更新プログラム、コンプライアンスなどの運用コミットメントも変更されません。 少なくとも 2030 年 5 月まで、AZURE AD B2C のサポートを継続します。 移行の詳細については、「[AZURE AD B2C から外部 ID への移行を計画する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/plan-your-migration-from-b2c-to-external-id)を参照してください。 詳細とMicrosoft Entra 外部 IDの詳細については、アカウント担当者にお問い合わせください。

#### Azure AD B2B コラボレーションと B2B 直接接続に何が起こっていますか?

Azure AD B2B コラボレーションと B2B 直接接続は、外部 ID B2B コラボレーションと B2B 直接接続としてMicrosoft Entra 外部 IDの一部になりました。 これらは、従業員テナント内の Microsoft Entra 管理センター内の同じ場所に残ります。

#### コード成果物や CI/CD パイプラインなど、カスタム ポリシーでこれまで構築した多大な資産があります。 今後の集中型プラットフォームをどう考えたらよいですか?

カスタム ポリシーの構築と管理に多大な投資が行われていることは認識しています。 従来のカスタム ポリシーの構築と管理が難しすぎるというお客様の声が寄せられています。 新しい外部 ID プラットフォームでは、カスタム ポリシーが不要になるようにエクスペリエンスを簡素化しています。 B2C の既存のカスタム ポリシーのための移行パスが用意でき次第、ご提供いたします。

### 製品の機能

#### 外部テナントと従業員テナントの違いは何ですか?

どちらもテナントMicrosoft Entraですが、既定の構成が異なります。 [テナント構成の詳細を確認します](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations)。

#### 外部 ID にカスタム ポリシーはありますか?

次世代 CIAM プラットフォームは、複雑なカスタム ポリシーを必要とせずに、同等の機能を提供するように設計されています。

#### 外部 ID はどの ID プロバイダーをサポートしていますか?

外部 ID は、Microsoft Entra アカウント (招待経由)、Facebook、Google、Apple、Microsoft Entra ID フェデレーション、カスタム OIDC、SAML/WS-Fed ID プロバイダーフェデレーションなど、さまざまな ID プロバイダーをサポートします。 ID プロバイダーは、テナント構成と、外部ユーザーが招待されたか、セルフサービス サインアップを使用しているかに基づいています。 外部 [ID の ID プロバイダーの詳細については](https://learn.microsoft.com/ja-jp/entra/external-id/identity-providers)、[サポートされている機能の比較](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-supported-features-customers)を参照してください。

#### 外部 ID 機能の一覧はどこで確認できますか?

外部 ID の機能の詳細な一覧については、「 [従業員と外部テナントでサポートされる機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-supported-features-customers)」を参照してください。

#### 外部 ID は米国政府機関向けクラウドMicrosoft Entraサポートされますか?

外部 ID は現在、パブリック クラウドのみをサポートしています。 パブリック クラウドの外部 ID は、 [Federal Risk and Authorization Management Program](https://learn.microsoft.com/ja-jp/azure/compliance/offerings/offering-fedramp) (FedRAMP) High および [Department of Defense (DoD) Impact Level 2 (IL2)](https://learn.microsoft.com/ja-jp/azure/compliance/offerings/offering-dod-il2) の認定を受けています。

### 開発者エクスペリエンス

#### 開発者はどこで外部 ID を使い始めることができますか?

[デベロッパー センター](https://aka.ms/ciam/dev)では、開発者向けの最新のリソースと情報を確認できます。

- [外部テナントを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/quickstart-tenant-setup) し、ガイドに従ってテナントを設定し、最初のサンプルを実行します。
- このチュートリアルを、外部 ID と連動するコンシューマー アプリとビジネス顧客アプリを構築して統合する方法の学習にお役立てください。
- 最新のニュースや分析情報に対応するために [、ID ブログ](https://devblogs.microsoft.com/identity/tag/external-id/) の電子メールの更新にサインアップします。
- ビデオの概要、チュートリアル、詳細については、 [YouTube](https://www.youtube.com/playlist?list=PL3ZTgFEc7Lythpts59O9KOVuEDLWJLLmA) をご覧ください。

これらのリソースに加えて、パブリック プレビューには開発者向けの機能がいくつかあります。

- Microsoft Entra 外部 IDは、[Azure App Service の組み込み認証](https://devblogs.microsoft.com/identity/app-service-external-id/)の ID プロバイダーとして使用します。
- Visual Studio Code 用 Microsoft Entra 外部 ID 拡張機能を使用する。 この拡張機能は、VS Code 内からサンプル外部 ID アプリケーション全体を構築して構成できる、シームレスなガイド付きエクスペリエンスを提供します。 詳細については、 [ブログ](https://devblogs.microsoft.com/identity/external-id-extension/) と [ドキュメント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/visual-studio-code-extension) を参照してください。

#### 外部 ID を使用した認証をアプリ コードに追加するにはどうすればよいですか?

統一された 1 つの [Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) があり、同じアプリケーション コードが職場向けと顧客向けの両方のシナリオで機能します。 次の 3 つの手順で、ユーザーをサインアップまたはサインインさせることができます。

1. MSAL をテナントとアプリケーションで使用するように構成する
2. MSAL を呼び出して Web ベースのサインイン フローを開始するサインイン機能を作成する
3. 返されたトークンから顧客情報を抽出できる応答ハンドラーを作成する

[サンプル アプリケーション](https://learn.microsoft.com/ja-jp/entra/external-id/customers/samples-ciam-all)では、これらの各手順のコード例を確認できます。

#### 完全にカスタムの認証サインイン エクスペリエンスを構築できますか?

Yes. Microsoft Entra 外部 IDでは、2 つの認証方法がサポートされています。**ブラウザー委任認証**は、ユーザーをMicrosoftホスト型サインイン ページにリダイレクトします。**ネイティブ認証**を使用すると、サインイン UI をアプリに直接構築できます。 ネイティブ認証を使用すると、モバイル アプリケーションとシングルページ アプリケーションのサインイン エクスペリエンスを完全に制御できますが、開発作業とセキュリティ責任の共有が必要になります。 両方の方法を比較し、アプリに適した方法を決定するには、「 [認証方法を選択する」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach)参照してください。

#### 開発者向けに外部 ID がサポートする統合に何がありますか?

外部 ID は、 [カスタム認証拡張機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview)を介した外部システムとのサーバー側統合をサポートします。 この機能により、開発者は独自のロジックを実装し、サインインまたはサインアップ フロー中にリアルタイムの API 呼び出しを介してそのロジックを呼び出せます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-add-attributes-to-token"} -->
## トークン要求に属性を追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-attributes-to-token
- Service: entra-external-id / external
- Article date: 2025-09-16
- Summary: 組み込みのユーザー属性とカスタム属性を要求としてアプリケーション トークンに追加する方法について説明します。 トークン要求でユーザー データをアプリケーションに送信するために、ディレクトリ拡張属性を使用します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ユーザー属性は、セルフサービス サインアップ時にユーザーから収集される値です。 組み込みのユーザー属性に加えて、追加情報を収集する必要がある場合は、カスタム属性を作成できます。 お使いのアプリケーションによっては、設計どおりに機能するために特定のユーザー属性が必要とされる場合があります。Microsoft Entra ID からアプリケーションへと送られるトークンには、そのような属性を追加することができます。

Microsoft Entra ID からアプリケーションに送信されるトークン内にどの組み込み属性やカスタム属性を要求として含めるかは、指定できます。

### 前提条件

- [Microsoft Entra ID にアプリケーションを登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)します。
- [サインアップとサインインのユーザー フローを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)し、サインアップ時に収集する属性を選択します。
- 含める[カスタム属性を作成します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes)。

### 組み込み属性またはカスタム属性をトークンに追加する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**アプリ登録**に移動します。
3. 一覧からアプリケーションを選択して、アプリケーションの **[概要]** ページを開きます。

    [Image: アプリ登録の [概要] ページのスクリーンショット。]
4. **[要点]** セクションの **[ローカル ディレクトリでのマネージド アプリケーション]** で、アプリケーションの名前を示すリンクを選択します。

    [Image: [ローカル ディレクトリでのマネージド アプリケーション] リンクのスクリーンショット。]
5. **[管理]** で **[シングル サインオン]** を選びます。
6. **[属性と要求]** セクションで、**[編集]** アイコンを選択します。

    [Image: [属性と要求] セクションと [編集] アイコンのスクリーンショット。]

#### 組み込み属性を要求としてトークンに追加するには

1. **[属性とクレーム]** ページで、**[新しいクレームの追加]** を選択します。
2. **[名前]** を入力します。
3. **[ソース]** の横で **[属性]** を選択します。 次に、ドロップダウン リストを使用して組み込み属性を選択します。

    [Image: 組み込み属性のドロップダウン リストのスクリーンショット。]
4. **[保存]** を選択します。 追加するすべての組み込み属性に対して繰り返します。

#### カスタム属性を要求としてトークンに追加するには

1. **[属性とクレーム]** ページで、**[新しいクレームの追加]** を選択します。
2. **[名前]** を入力します。
3. **[ソース]** の横で **[ディレクトリ スキーマ拡張機能]** を選択します。

    [Image: [ディレクトリ スキーマ拡張機能] オプションのスクリーンショット。]
4. **[アプリケーションの選択]** ウィンドウで、**b2c-extensions-app** (外部テナントのすべての拡張属性を含むアプリ) を選択してから、**[選択]** を選びます。
5. **[拡張属性の追加]** ウィンドウで、トークンに要求として追加するカスタム属性を見つけて、それを選択します。
6. **[追加]** を選択します。
7. **[保存]** を選択します。 追加するカスタム属性ごとに繰り返します。

#### Microsoft Graph アプリ マニフェストでマップされた要求を受け入れるようにアプリケーション マニフェストを更新する (新規)

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**アプリ登録**に移動します。
3. 一覧からアプリケーションを選択して、アプリケーションの **[概要]** ページを開きます。
4. 左側のメニューの **[管理]** で **[マニフェスト]** を選択し、アプリケーション マニフェストを開きます。
5. **acceptMappedClaims** キーを見つけ、その値を **true** に設定します。
6. **isFallbackPublicClient** キーを検索し、その値を **true** に設定します。
7. **[保存]** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-add-enterprise-application"} -->
## エンタープライズ アプリケーションの追加 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-enterprise-application
- Service: entra-external-id / external
- Article date: 2025-07-17
- Summary: 管理センターを使用して Microsoft Entra 外部テナントにエンタープライズ アプリケーションを追加する方法について説明します。 ギャラリー アプリ、構成手順、デプロイのヒントについて説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

エンタープライズ アプリケーションは、Microsoft Entra ID と事前に統合されたサービスとしてのソフトウェア (SaaS) アプリです。 これらのアプリは、アクセス管理とシングル サインオン (SSO) をサポートします。 これらのアプリは、事前に統合されたさまざまな SaaS アプリケーションを含む Microsoft Entra アプリケーション ギャラリーにあります。 この記事では、 **Microsoft Entra SAML Toolkit** という名前のアプリケーションを例として使用しますが、概念は [ギャラリー内のほとんどのエンタープライズ アプリケーションに](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)適用されます。

### Prerequisites

外部テナントにエンタープライズ アプリケーションを追加するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)または [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)。

### エンタープライズ アプリケーションの追加

Microsoft Entra 外部テナントにエンタープライズ アプリケーションを追加するには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。
3. [**新しいアプリケーション**] を選択&gt;**独自のアプリケーションを作成します**。
4. 追加するアプリケーションの名前の入力を開始します。 アプリケーションが既にギャラリーに存在する場合は、一覧に表示されます。 この記事では、 **例として Microsoft Entra SAML Toolkit** を使用します。

    [Image: 外部テナントにエンタープライズ アプリケーションを追加する方法を示すスクリーンショット。]
5. 一覧からアプリケーションを選択し、[ **作成**] を選択します。
6. [ **作成]** を選択すると、登録したアプリケーションが表示されます。
7. この時点で、ベスト プラクティスとして [所有者をアプリケーションに割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-app-owners?pivots=portal#assign-an-owner) 必要があります。

### リソースをクリーンアップする

アプリケーションは、今後使用するためにテナントに保持することも、不要になった場合は [削除](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-application-portal?pivots=portal) することもできます。 アプリケーションを削除すると、関連付けられているすべてのユーザー割り当てと構成も削除されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-add-identity-provider-to-user-flow-customers"} -->
## ID プロバイダーをユーザー フローに追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-identity-provider-to-user-flow-customers
- Service: entra-external-id / external
- Article date: 2026-05-29
- Summary: 構成された外部 ID プロバイダー (OIDC、SAML/WS-Fed、またはソーシャル) をMicrosoft Entra 外部 IDのユーザー フローに追加して、セルフサービス サインアップのサインイン ページに表示されるようにする方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

外部テナントで外部 ID プロバイダーを構成したら、それをユーザー フローに追加して、サインイン ページで使用できるようにする必要があります。

ID プロバイダーを構成する手順については、以下を参照してください。

- [カスタム OIDC ID プロバイダーを構成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers)
- [OIDC ID プロバイダーとして Microsoft Entra ID テナントを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-entra-id-federation-customers)
- [SAML/WS-Fed IdP フェデレーションを構成する](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation)
- [Google を ID プロバイダーとして追加](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers)
- [Facebook を ID プロバイダーとして追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-facebook-federation-customers)
- [APPLE を ID プロバイダーとして追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-apple-federation-customers)
- [ID プロバイダーとして Microsoft アカウントを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-microsoft-accounts-federation-customers)

### 前提条件

- [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
- テナントに登録されているアプリケーション。
- 構成済みの ID プロバイダー (上記のリンクを参照)。
- [サインアップとサインインのユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)。

### ID プロバイダーをユーザー フローに追加する

構成済みの ID プロバイダーをユーザー フローに追加するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[外部 ID ユーザー フロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-id-user-flow-administrator)としてサインインします。
2. 上部メニューの **[設定]** アイコンを選択し、外部テナントを選択して、外部テナントに切り替えます。
3. **Entra ID**&gt;**外部アイデンティティ**&gt;**ユーザーフロー**を参照します。
4. ID プロバイダーを追加するユーザー フローを選択します。

    [Image: ユーザー フローの一覧を示す [外部 ID ユーザー フロー] ページのスクリーンショット。]
5. [ **設定]** で、[ **ID プロバイダー] を選択します**。
6. [ **その他の ID プロバイダー**] で、追加する ID プロバイダーを選択します。

    [Image: [その他の ID プロバイダー] セクションを示す [ID プロバイダー] ページのスクリーンショット。]
7. **保存**を選びます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-analyze-azure-ad-b2c-custom-policies"} -->
## Microsoft Entra 外部 ID への移行に向けて Azure AD B2C のカスタム ポリシーを分析する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-analyze-azure-ad-b2c-custom-policies
- Service: entra-external-id / external
- Article date: 2026-07-02
- Summary: 移行ポリシー アナライザーを使用して、Azure AD B2C のカスタム ポリシーをスキャンし、Microsoft Entra 外部 ID 向けの詳細な移行評価を生成します。 今すぐ移行を開始します。

移行ポリシー アナライザーは、Azure AD B2C テナントで直接使用できます。 カスタム ポリシーをスキャンし、Microsoft Entra 外部 IDの移行評価を生成し、検出された各機能を移行パスにマッピングして、Azure AD B2C から外部 ID に移動するために必要な作業のスコープを設定できるようにします。

この記事では、次の方法について説明します。

- Azure AD B2C テナントに対してアナライザーを実行します。
- 機能検出の結果と移行の状態を解釈します。
- 検出された機能ごとに適切な移行パスを選択します。
- アナライザーの制限事項と既知の問題を考慮します。

### Prerequisites

- カスタム ポリシーがアップロードされた有効なAzure AD B2C テナント。
- B2C IEF ポリシー管理者またはグローバル管理者ロールで Azure portal にアクセスします。

Note

アナライザーはカスタム ポリシーでのみ機能します。 ユーザー フローは外部 ID ユーザー フローに直接マップされるため、分析は必要ありません。

### アナライザーを実行する

カスタム ポリシーを分析するには、次の手順に従います。

1. [Azure ポータル](https://portal.azure.com)にサインインし、Azure AD B2C テナントに移動します。
2. 左側のメニューから **[Identity Experience Framework** ] を選択します。
3. ツール バーから **[移行ポリシー アナライザー** ] を選択します。
4. 分析するポリシーを選択します。 分析を正常に完了するには、選択内容に依拠当事者 (RP) ポリシーを含める必要があります。
5. [ **ポリシーの分析** ] を選択して評価を開始します。

分析が完了すると、レポートに次の内容が表示されます。

- ポリシーで検出された機能の合計数。
- 各機能の移行状態と推奨パス。

### アナライザーが検出するもの

アナライザーはカスタム ポリシーをスキャンし、検出された機能を、サインアップ、サインイン、セッションとアクセス制御、パスワード管理、トークンとクレーム、UX とブランディングなどのカテゴリに分類します。 カテゴリは、ツールによって新しい検出が追加されるにつれて進化する可能性があります。

重要なのは、そのレポートから何を読み取るかです:

| 学習内容 | 共同作業の重要性 |
| --- | --- |
| **ポリシーで使用する機能** | 移行する必要がある内容の完全な範囲を理解します。 |
| **各機能の移行状態** | 現在の外部 ID で動作するものと、カスタム開発が必要なもの、またはまだサポートされていないものをすぐに把握します。 |
| **推奨される移行パス** | 各機能は、ネイティブの構成、カスタム認証拡張機能を使用したビルド、ネイティブ認証 SDK の使用、Microsoft Graph APIの呼び出しなどの特定のアクションにマップされます。 |
| **ドキュメント のリンク** | すべての移行パスは、関連する Microsoft Learn ガイドに直接リンクされるため、実装を開始できます。 |

結果を処理する準備ができたら、エンド ツー エンド ツール、ユーザー移行パターン、ステップ バイ ステップのカットオーバー ガイダンスについては、[Azure AD B2C から外部 ID への](https://aka.ms/b2c-migration-guide)移行を計画する方法に関するページを参照してください。

### 分析レポートを理解する

レポートには、次の 2 つのセクションがあります。

| Section | 表示される内容 |
| --- | --- |
| 移行の概要 | 検出された機能の合計数と、外部 ID でネイティブに使用できる数。 |
| 機能の詳細 | 検出された各機能とその移行状態、推奨パス、および関連するドキュメント。 |

概要出力の例:

```text
Migration Summary
---
Features detected: 42
Available (External ID built-in): 28
Custom Development Required: 12
Not Currently Supported: 2
Architecture Incompatible: 0
```

Note

示されている数値は例示です。 結果は、カスタム ポリシーの複雑さによって異なります。

Important

アナライザーの出力は、可能な範囲で行われた評価です。 検出された各機能とその推奨される移行パスを手動で確認してから対処し、移行の決定を行う前に、独自のポリシーとビジネス要件に照らして結果を検証します。

API 応答から結果を JSON としてダウンロードするか、ポータル UI から書式設定されたレポートをコピーできます。

### 移行の状態を解釈する

検出された各機能には、次の 4 つの移行状態のいずれかが割り当てられます。

| 地位 | Meaning | アクションが必要 |
| --- | --- | --- |
| Available | External ID で現時点でネイティブに動作します。GA で、ドキュメント化済み、本番運用に対応しています。 | 外部 ID で構成する。カスタム開発は必要ありません。 |
| カスタム開発が必要 | カスタム認証拡張機能、ネイティブ認証 SDK、または Microsoft Graph APIを使用して実現できます。 | 推奨される移行パスに従います。実装を所有している。 |
| 現在サポートされていません | 現時点で同等のものはないか、あってもプレビュー段階にとどまっており、正式に確約された提供時期はありません。 | 更新プログラムのMicrosoft Entraロードマップを監視します。 |
| アーキテクチャに互換性がありません | 基本的なパターンの不一致— Azure AD B2C アプローチは直接変換されません。 | 代替アーキテクチャのドキュメントを確認します。別の設計を計画します。 |

### 移行パスを選択する

#### 組み込みの外部 ID 機能

これらの機能は、カスタム コードなしでMicrosoft Entra 外部 IDですぐに使用できます。 Microsoft Entra 管理センターまたは Microsoft Graph APIを使用して構成します。 例: 電子メール/パスワードサインアップ、ソーシャル ID プロバイダー フェデレーション (Google、Facebook、Apple)、エンタープライズ SAML/OIDC、電子メール OTP、カスタム属性、ブランド化、カスタム ドメイン、条件付きアクセス、サインインしたままにする (KMSI)。

#### ネイティブ認証 SDK またはカスタム UI

カスタム HTML/CSS/JS ページ、マルチステップ登録ウィザード、埋め込み認証フローなど、組み込みのホストエクスペリエンスを超えた完全な UX 制御が必要な場合は、このパスを使用します。

| Platform | ソリューション | Documentation |
| --- | --- | --- |
| モバイル (iOS/Android) | ネイティブ認証 SDK | [ネイティブ認証の概念](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication) |
| シングルページアプリケーション | カスタム UI を使用した MSAL.js | [SPA 認証の構成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-single-page-app-vanillajs-configure-authentication) |

#### カスタム認証拡張機能

[カスタム認証拡張機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-extensions)を使用して、B2C REST API 技術プロファイルまたは複雑な要求変換で実行されたサーバー側ロジックを置き換えます。 トークン発行イベント中に外部 API を呼び出し、要求エンリッチメント、カスタム MFA プロバイダー、検証ロジック、オーケストレーション分岐、条件付き要求発行、および強制パスワード リセットをサポートします。

#### Microsoft Graph APIまたはアプリ側ロジック

[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/identityuserflowattribute)を使用して、ユーザー管理とアプリケーション レベルのロジックを B2C ポリシーからアプリケーションに移動します。 一般的なシナリオ: プロファイル編集、プログレッシブ プロファイル、アカウント リンク、ユーザー名の変更、属性管理、アカウント ロックアウト、偽装。

#### プラットフォームのロードマップ

一部の機能には、外部 ID に対応するものがまだありません。通常は、新しい認証方法やニッチ プロトコル (QR コード認証、WS フェデレーション、SAML アーティファクト バインド、受信 SAML 暗号化、サインイン時の CAPTCHA など)。 サインインと MFA にパスキー (FIDO2) を使用できるようになりました。 [Microsoft Entraロードマップ](https://aka.ms/entra-roadmap)の更新を追跡します。

### 制限事項と既知の問題

| Limitation | Details | 対処法 |
| --- | --- | --- |
| カスタム ポリシーのみ | 組み込みのユーザー フローは分析されません。 | ユーザー フローは外部 ID に直接マップされるため、分析は必要ありません。 |
| 一度に 1 つのテナント | アナライザーは、1 つのセッションで複数の B2C テナント間でポリシーを処理できません。 | テナントごとにアナライザーを個別に実行します。 |
| ランタイム動作分析なし | アナライザーは、ランタイム実行ではなく XML 構造を読み取ります。 | 移行後に外部 ID で重要なフローをテストします。 |
| 英語の出力のみ | 結果は、ポリシー言語に関係なく英語で表示されます。 | ローカライズが計画されています。 |
| 拡張プロパティ | カスタム拡張属性は検出されますが、スキーマの互換性は検証されません。 | 外部 ID で拡張機能属性マッピングを手動で確認します。 |

### トラブルシューティング

#### 分析から CallerError が返される

**現象**: API は `CallerError` 結果の型を返します。

**原因**: 選択したポリシーに、テナントにアップロードされていない無効な XML または参照不足の基本ポリシーが含まれています。

**解決方法**:

- 分析する前に、すべてのポリシーが Identity Experience Framework で正常にコンパイルされることを確認します。
- 基本ポリシー (`TrustFrameworkBase`、 `TrustFrameworkExtensions`) がアップロードされていることを確認します。
- ポリシー ファイルで XML エンコードの問題を確認します。

#### 本来検出されるはずの機能が検出されない

**現象**: ポリシーに機能が存在することはわかっていますが、アナライザーではレポートされません。

**原因**: この機能では、非標準の実装パターンが使用されている場合や、検出が分析に含まれていない基本ポリシーに依存している可能性があります。

**解決方法**:

- すべてのポリシー ファイル（ベース、拡張、依拠当事者）を分析に含めます。
- この機能で、インライン JavaScript やカスタム ハンドラーではなく、標準の B2C XML パターンが使用されていることを確認します。

#### レポートには、使用していない機能が表示されます

**現象**: このレポートには、 *オーケストレーション分岐* や *クレーム変換* などの一般的な機能が一覧表示されます。

**原因**: これらの機能は、ほとんどのカスタム ポリシーに存在する構造パターンです。 アナライザーは、XML で検出された内容を報告します。これには、すべてのカスタム ポリシーで使用される基本パターンが含まれます。

**解決方法**:

- *カスタム開発が必須*または*現在サポートされていない状態の機能に焦点を*当てます。
- 移行パス列を使用して、計画に優先順位を付けます。

#### 権限不十分エラー

**現象**: アナライザーを実行しようとすると、アクセスが拒否されます。

**解決方法**:

- B2C IEF ポリシー管理者またはグローバル管理者ロールがあることを確認します。
- アカウントが正しい B2C テナント ディレクトリにあることを確認します。
- 条件付きアクセス ポリシーによってポータルのアクセスがブロックされていないことを確認します。

### よく寄せられる質問

#### アナライザーは無料ですか?

Yes. 移行ポリシー アナライザーは、追加コストなしで Azure AD B2C テナントに含まれています。 個別のサインアップ、インストール、またはライセンスは必要ありません。

#### アナライザーはポリシーまたはテナントを変更しますか?

No. アナライザーは読み取り専用モードで動作します。 ポリシー XML ファイルを読み取り、メモリ内で処理し、結果を返します。 テナント、ポリシー、またはユーザー データは変更されません。

#### アナライザーはどのくらいの頻度で実行する必要がありますか?

ベースラインの移行計画の開始時、移行スコープに影響する方法で B2C ポリシーを変更した後、および外部 ID 製品の更新後に定期的にアナライザーを実行して、新機能が使用可能になったかどうかを確認します。

#### 分析レポートをエクスポートできますか?

Yes. 分析結果は、API 応答から JSON としてダウンロードすることも、ポータル UI から書式設定されたレポートをコピーすることもできます。

#### 機能が *[現在サポートされていません*] と表示された場合はどうしますか?

この状態の機能には、現在の移行パスがありません。 次のようにすることができます。

- 更新については、[Microsoft Entra ロードマップ](https://aka.ms/entra-roadmap)を確認してください。
- この機能が起動にとって重要であるか、延期できるかを評価します。
- 特定の機能に関するタイムライン ガイダンスについては、Microsoft アカウント チームにお問い合わせください。

#### アナライザーはマルチテナント分析をサポートしていますか?

No. アナライザーは、一度に 1 つの B2C テナントに対して実行されます。 複数のテナントを管理する場合は、それぞれの分析を個別に実行します。

### 移行を開始する準備はできましたか?

アナライザーの結果を確認し、検出された機能の移行パスを理解すると、次の手順に進むのに役立つこれらのリソースが役立ちます。

| 必要なもの | 資源 |
| --- | --- |
| エンドツーエンドの移行計画 (標準モードと HSC モード) | [AZURE AD B2C から外部 ID への移行を計画します](https://aka.ms/b2c-migration-guide) |
| 外部 ID テナントを作成する | [外部テナントを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal) |
| ブラウザーで委任された認証とネイティブ認証を選択する | [認証方法を選択する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach) |
| カスタム ロジック用のカスタム認証拡張機能を構築する | [カスタム認証拡張機能の概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview) |
| ユーザーと資格情報を移行する | [AZURE AD B2C から外部 ID への移行を計画します](https://aka.ms/b2c-migration-guide) |
| サービスの制限とレートの制約について | [サービスの制限と制約](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-service-limits) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-apple-federation-customers"} -->
## 顧客サインイン用に Apple を追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-apple-federation-customers
- Service: entra-external-id / external
- Article date: 2026-04-17
- Summary: Apple を外部テナントの ID プロバイダーとして追加する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Apple とのフェデレーションを設定すると、顧客が独自の Apple アカウントを使用してアプリケーションにサインインできるようになります。 [顧客向けの認証方法と ID プロバイダーの](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers)詳細について説明します。

### Apple アプリケーションを作成する

Apple ID を持つ顧客のサインインを有効にするには、 [Apple 開発者ポータル](https://developer.apple.com/)でアプリケーションを作成する必要があります。 Apple ID をまだ持っていない場合は、[証明書]、[ **ID]、[プロファイル]** セクションで作成できます。

Note

このドキュメントは、作成時にプロバイダーの開発者ページの状態を使用して作成され、変更が発生する可能性があります。

1. アカウントの資格情報を使用して Apple 開発者ポータルにサインインします。
2. メニューから、**証明書、ID、& プロファイル**を選択し、**(+)**を選択します。
3. [新しい識別子の登録] セクションで、**アプリ ID**を選択し、次に **続行**を選択します。
4. [種類の選択]\(Select a type\) で **[App]\(アプリ\)** を選択し、**[続行]** を選択します。
5. アプリ ID を登録するには:

    1. 説明を入力します。
    2. `com.contoso.azure-ad`など、バンドル ID を入力します。 `com.myappdomain.myappname` などの明示的な名前付けをお勧めします。
    3. [機能] については、機能の一覧から **[Apple でサインイン]** を選択します。
    4. この手順でチーム ID (アプリ ID プレフィックス) を書き留めます。 後で必要になります。
    5. **[続行]**、**[登録]** の順に選択します。
6. メニューから、**証明書、ID、& プロファイル**を選択し、**(+)**を選択します。
7. [**新しい識別子** の登録]セクションで、**[サービス ID**] を選択してから、**[続行]**を選択します。
8. [サービス ID の登録] で、次の手順を実行します。

    1. **[Description]\(説明\)** を入力します。 説明は、同意画面にユーザーに表示されます。
    2. など、`com.contoso.entra-service`を入力します。 `com.myappdomain.myappname.service` などの明示的な名前付けをお勧めします。 サービス ID 識別子を書き留めます。 識別子はクライアント ID です。
    3. **[続行]** を選択し、次に **[登録]** を選択します。
9. [**識別子]**で、作成したサービス ID 識別子を選択します。
10. **[Apple でサインイン]** を選択し、**[構成]** を選択します。

    1. Apple でのサインインを構成するプライマリ アプリ ID を選択します。
    2. **[Domains and Subdomains]\(ドメインとサブドメイン\)** で、次のように置き換えて入力します

    - `<tenant-id>` をテナント ID またはプライマリ ドメイン名に置き換えます。
    - `<tenant-name>`を実際のテナント名に置き換えます。 すべての文字は小文字にする必要があります。 例として次に示します。
        - `<tenant-name>.ciamlogin.com`
        - `<tenant-id>.ciamlogin.com`

    1. **戻り URL**で、`<tenant-id>`をテナント ID またはプライマリ ドメイン名に置き換え、`<tenant-name>` をテナント名に置き換えて、次のように入力します。 すべての文字は小文字にする必要があります。

        例として次に示します。

        - `https://<tenant-id>.ciamlogin.com/<tenant-id>/federation/oauth2`
        - `https://<tenant-id>.ciamlogin.com/<tenant-name>/federation/oauth2`
        - `https://<tenant-name>.ciamlogin.com/<tenant-id>/federation/oauth2`
    2. [次 ] を選択し、そして [完了 ] を選択します。
    3. ポップアップ ウィンドウが閉じたら、[続行] 選択し、[**保存]**選択します。

### Apple クライアント シークレットを作成する

1. Apple 開発者ポータル のメニューで、[キー ] を選択し、[**(+)**を選択します。
2. 新しいキーを登録するには:
    1. **キー名**を入力してください。
    2. **[Apple でサインイン]** を選択し、**[構成]** を選択します。
    3. [プライマリ アプリ ID] で、前に作成したアプリを選択し、**保存**を選択します。
3. **[続行**] を選択し、その後 **[登録**] を選択してキーの登録プロセスを完了します。
4. **キー ID**をメモします。 このキーは、ID プロバイダーを構成するときに必要です。
5. キーをダウンロードするには、[ ダウンロード] を選択して、キーを含む `.p8` ファイルをダウンロードします。
6. **完了**を選択します。

Important

Apple でサインインするには、管理者がクライアント シークレットを 6 か月ごとに更新する必要があります。 Apple クライアント シークレットの有効期限が切れた場合は、手動で更新し、新しい値をポリシー キーに格納する必要があります。 新しいクライアント シークレットを生成するには、6 か月以内に独自のアラームを設定することをお勧めします。

### Microsoft Entra 外部 IDで Apple フェデレーションを構成する

Apple アプリを作成した後、この手順では、Microsoft Entra 外部 IDで Apple アプリの詳細を設定します。 Microsoft Entra 管理センターを使用してこれを行うことができます。 Microsoft Entra 管理センターで Apple フェデレーションを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**External Identities**&gt;**すべての ID プロバイダー** に移動します。
3. [組み込み] タブで、**Apple**を選択します。

    [Image: クライアント ID、チーム ID、キー ID、クライアント シークレット キーのフィールドを含むMicrosoft Entra 管理センターの Apple ID プロバイダー構成ページのスクリーンショット。]
4. **[名前]***Apple* が自動的に設定されます。 変更できません。
5. 次の詳細を入力します。

    - **クライアント (Apple サービス) ID**: 前の手順で作成した Apple アプリケーションのクライアント ID。
    - **Apple 開発者チーム ID**: 前の手順で作成した Apple アプリケーションに関連する Apple 開発者チーム ID。
    - **キー ID**: 前の手順で作成した Apple アプリケーションのキー ID。
    - **クライアント シークレット (.p8) キー**: 前の手順で作成した Apple アプリケーションのクライアント シークレット キー。
6. **保存** を選択します。 Apple が構成済みの ID プロバイダーとして一覧表示されます。

    [Image: Apple が構成済みの組み込み ID プロバイダーであることを示す [すべての ID プロバイダー] リストのスクリーンショット。]

### ユーザーがサインインして ID プロバイダーにサインアップできるようにする

Apple を ID プロバイダーとして構成したら、それをユーザー フローに追加して、ID プロバイダーへのサインインとサインアップを許可します。 [ユーザー フローへの ID プロバイダーの追加を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-identity-provider-to-user-flow-customers)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-azure-monitor"} -->
## 外部テナントでの Azure Monitor - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-azure-monitor
- Service: entra-external-id / external
- Article date: 2025-10-01
- Summary: 外部テナントで Azure Monitor を設定して、テナント内のデータを収集して分析する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

[Azure Monitor](https://learn.microsoft.com/ja-jp/azure/azure-monitor/overview) は、クラウドおよびオンプレミス環境から監視データを収集、分析、対応するための包括的なソリューションを提供します。 監視対象リソースの診断設定では、送信するデータと送信先を指定します。 Microsoft Entra の場合は、 [Azure Storage](https://learn.microsoft.com/ja-jp/azure/storage/blobs/storage-blobs-introduction)、 [Log Analytics](https://learn.microsoft.com/ja-jp/azure/azure-monitor/essentials/resource-logs#send-to-log-analytics-workspace)、または [Azure Event Hubs](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-about) にデータを送信できます。

外部テナント ログを他の監視ソリューションまたはストレージの場所に転送する場合は、これらのログに個人データが含まれている可能性があることに注意してください。 個人データを処理する場合は、適切なセキュリティ対策を使用して保護します。 これらの対策は、適切な技術的および組織上のセーフガードを使用して、不正または違法な処理を防止する必要があります。

この記事では、テナント内のデータを収集して分析できるように、外部テナントで Azure Monitor を構成する方法について説明します。 また、従業員テナントの Log Analytics ワークスペースにログとメトリックを送信するように診断設定を構成する方法についても説明します。

### デプロイの概要

外部テナントは [Microsoft Entra 監視](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health)を使用します。 従業員テナントとは異なり、外部テナントには関連付けられたサブスクリプションを持つことはできません。 外部テナントで監視を有効にするには、従業員テナントにサインインして、構成中にサブスクリプションを認証します。[また、Azure Lighthouse](https://learn.microsoft.com/ja-jp/azure/lighthouse/overview) を使用して、外部テナント (サービス プロバイダー) 内の従業員テナント (顧客) の診断設定を有効にすることもできます。

この構成では、ウィザードを使用します。 **[診断設定**] ページまたは **[セキュリティ ストア]** ページのいずれかのエントリ ポイントからウィザードを開始できます。 この記事では、両方の方法について説明します。

### 前提条件

- Azure サブスクリプション。 アカウントをお持ちでない場合は、開始する前に [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を作成してください。
- Microsoft Entra サブスクリプションの [所有者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner) ロールを持つ Microsoft Entra アカウント。
- [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)または[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)ロールが割り当てられている外部テナントのアカウント。

Important

この機能では、クラシック管理者ロールではなく、新しい [Azure Role-Based アクセス制御 (RBAC) 所有者ロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles/privileged#owner)のみがサポートされます。 クラシック管理者ロールを Azure RBAC に変換する手順については、 [Azure クラシック サブスクリプション管理者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/classic-administrators?tabs=azure-portal)に関するページを参照してください。 変換が完了したら、ページを更新して変更を適用します。

### ウィザードを起動して Azure Lighthouse を設定する

外部テナントで Azure Lighthouse を構成するには、[ **診断設定** ] ページまたは **[セキュリティ ストア** ] ページからウィザードを開始します。 開始するには、エントリ ポイントを含む次のいずれかのタブを選択します。

## [診断設定](#tab/diagnostic-settings)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用し、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. 外部テナントで **Entra ID** を参照し、**監視と正常性**&gt;**診断設定**を選択します。
4. [ **セットアップの開始]** を選択してウィザードを起動します。

## [セキュリティ ストア](#tab/security-store)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用し、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Home**&gt;**Security Store**&gt;に移動し、Azure Monitor によるログの監視を行います。
4. [ **はじめに** ] を選択して **、[診断設定]** ページを開きます。 Azure 監視が既に構成されている場合は、[ **ログの表示** ] を選択してログ エクスペリエンスを開くことができます。
5. [ **セットアップの開始]** を選択してウィザードを起動します。

---

[Image: ウィザードを開始する方法を示すスクリーンショット。]

### ウィザードで Azure Lighthouse 構成を設定する

次の手順では、ウィザードを使用して、外部テナントで Azure Lighthouse 構成を設定します。

#### 手順 1: 勤務テナントにサインインする

Azure Lighthouse を設定するには、外部構成テナントを所有するサブスクリプションにアクセスできるアカウントでサインインします。

[Image: 従業員テナントにサインインする方法を示すスクリーンショット。]

#### 手順 2: プロジェクトの詳細を入力する

この手順では、プロジェクトの詳細を指定します。 リソース グループと Log Analytics ワークスペースを同時に作成する場合は、1 つの [場所](https://azure.microsoft.com/explore/global-infrastructure/products-by-region/)のみを選択できます。 この場所は、リソース グループと Log Analytics ワークスペースの両方で使用できるリージョンに制限されます。 場所の完全な一覧にアクセスするには、リソース グループと Log Analytics ワークスペースを事前に個別に作成します。

1. ドロップダウンから **サブスクリプション** を選択します。
2. 既存のリソース グループを使用するか、新しい **リソース グループ** を作成します。
3. 新しい **Log Analytics ワークスペース**の名前を指定します。 この名前は、リソース グループごとに一意である必要があります。
4. 使用可能なリージョンを選択 **します**。
5. [**次へ**] を選択します。

[Image: サブスクリプションを選択する方法を示すスクリーンショット。]

#### 手順 3: ユーザー アクセスを選択する

[Log Analytics ワークスペース](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/log-analytics-workspace-overview)にアクセスできる外部テナントのユーザーまたはグループを選択します。 選択したユーザーは、診断設定を設定するために、少なくとも [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロールが必要です。

**[選択**] ボタンを使用して選択内容を確認します。 ユーザーまたはグループを選択したら、ロールを割り当てます。 次のロールから選択できます。

- **[共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles/privileged#contributor)**: 監視データと構成を読み取ることができます。
- **[Log Analytics 共同作成者](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/manage-access?tabs=portal#log-analytics-contributor)**: 監視データと構成を読み書きできます。
- **[監視共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles/monitor#monitoring-contributor)**: すべての監視データを読み取り、監視設定を編集できます。
- **監視ポリシー共同作成者**: セキュリティ アラートとレポートの表示と管理など、セキュリティ関連の機能を管理できます。

ユーザーまたはグループを選択してロールを割り当てた後、[ **次へ** ] を選択して続行します。

[Image: ユーザー、グループ、ロールを追加する方法を示すスクリーンショット。]

##### 省略可能: Log Analytics ワークスペースにタグを追加する

Log Analytics ワークスペースにタグを追加できます。 タグは、複数のリソースとリソース グループに同じタグを適用することで、リソースを分類し、統合請求を表示するのに役立つ名前と値のペアです。 詳細については、「 [タグを使用して Azure リソースを整理する」を参照してください](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/tag-resources)。

#### 手順 4: Log Analytics ワークスペースを確認して作成する

構成を確認します。 変更する必要がある場合は、[ **戻る** ] ボタンを使用して前の手順に戻ります。 すべてが正しければ、[ **作成** ] を選択して Log Analytics ワークスペースを設定し、選択したユーザーまたはグループに指定したロールを割り当てます。 Log Analytics ワークスペースの設定とロールの割り当てには数分かかる場合があるため、ブラウザー ウィンドウを閉じないでください。

[Image: Log Analytics ワークスペースを確認して作成する方法を示すスクリーンショット。]

セットアップが完了すると、確認メッセージが表示されます。 [ **完了] を** 選択し、診断設定を構成して、Log Analytics ワークスペースへのログとメトリックの送信を開始します。

[Image: セットアップ完了メッセージを示すスクリーンショット。]

### 診断設定を構成する

[診断設定](https://learn.microsoft.com/ja-jp/azure/azure-monitor/platform/diagnostic-settings?tabs=portal) を使用すると、 [リソース ログ](https://learn.microsoft.com/ja-jp/azure/azure-monitor/platform/resource-logs?tabs=log-analyticsd) を収集し、 [プラットフォーム メトリック](https://learn.microsoft.com/ja-jp/azure/azure-monitor/reference/metrics-index) と [アクティビティ ログ](https://learn.microsoft.com/ja-jp/azure/azure-monitor/platform/activity-log?tabs=log-analytics) をさまざまな宛先に送信できます。 最大 5 つの異なる診断設定を作成して、さまざまなログとメトリックをさまざまな宛先に送信できます。 外部テナントで診断設定を構成するには、次の手順に従います。

1. **診断設定の追加** の下にある **設定の追加** を選択します。
2. 設定を追加する前に **[確認** ] を選択すると、右側に **サブスクリプション** と **リソース グループ** が表示されます。 これらのフィールドは読み取り専用です。 変更するには、既存のサービス プロバイダー情報を削除し、ウィザードをもう一度開始します。 選択内容に問題がなければ、[ **完了]** を選択して次の手順に進みます。 このステップはオプションです。

注

設定を追加する前に **[確認** ] を選択すると、右側に **サブスクリプション** と **リソース グループ** が表示されます。 これらのフィールドは読み取り専用です。 変更するには、既存のサービス プロバイダー情報を削除し、ウィザードを再起動します。 バックグラウンド サブスクリプション チェックの実行中は、ウィンドウを開いたままにしておきます。 チェックが完了する前にウィンドウを閉じたり更新したりする場合は、 **セットアップの開始**からウィザードを再起動することが必要になる場合があります。

[Image: [診断設定の追加] ページを示すスクリーンショット。]

1. [ **診断設定の追加]** を選択して新しい設定を追加するか **、編集設定** を選択して既存の設定を編集します。 同じ種類の複数の宛先にデータを送信する場合は、リソースに対して複数の診断設定が必要になる場合があります。
2. 設定にわかりやすい名前を付けます。
3. **ルーティングするログとメトリック**: ログの場合は、 [カテゴリ グループ](https://learn.microsoft.com/ja-jp/azure/azure-monitor/platform/diagnostic-settings?tabs=portal#category-groups) を選択するか、後で指定した宛先に送信するデータのカテゴリごとに個々のチェック ボックスをオンにします。 カテゴリの一覧は、Azure サービスごとに異なります。 プラットフォーム メトリックを収集する場合 **は、[AllMetrics** ] を選択します。
4. **宛先の詳細**: 診断設定に含める必要がある各宛先のチェック ボックスをオンにし、それぞれの詳細を指定します。 宛先として Log Analytics ワークスペースを選択した場合は、収集モードの指定が必要になる場合があります。 詳細については、 [コレクション モード](https://learn.microsoft.com/ja-jp/azure/azure-monitor/platform/resource-logs?tabs=log-analytics#collection-mode) を参照してください。

### ログ クエリを使用してデータを視覚化する

診断設定と Log Analytics ワークスペースへのデータ フローを構成したら、ログ クエリを使用してデータを分析および視覚化します。 ログ クエリは Kusto クエリ言語 (KQL) で記述され、収集されたログとメトリックから分析情報を得るのに役立ちます。 これらの構成は、従業員と外部テナントの両方で行うことができます。

#### クエリを作成する

ログ クエリは、Azure Monitor ログで収集されたデータから最大限の価値を得るのに役立ちます。 強力なクエリ言語を使用すると、複数のテーブルからのデータを結合し、大量のデータを集約し、最小限のコードで複雑な操作を実行できます。 サポート データを収集し、適切なクエリを構築する方法を理解している限り、事実上あらゆる質問に回答し、分析を実行できます。 詳細については、「 [Azure Monitor でのログ クエリの概要](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/get-started-queries)」を参照してください。

1. [Azure portal](https://portal.azure.com) にサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコンを選択して、[ **ディレクトリ + サブスクリプション** ] メニューから従業員テナントに切り替えます。
3. **Log Analytics ワークスペース ウィンドウで**、[ログ] を選択**します**
4. クエリ エディターで、次の [Kusto クエリ言語](https://learn.microsoft.com/ja-jp/azure/data-explorer/kusto/query/) クエリを貼り付けます。 このクエリは、過去 x 日間の操作によるポリシーの使用状況を示します。 既定の期間は、90 日間 (90d) に設定されています。 クエリは、ポリシーによってトークンまたはコードが発行される操作にのみ焦点を当てていることに注意してください。

```kusto
AuditLogs
| where TimeGenerated  > ago(90d)
| where OperationName contains "issue"
| extend  UserId=extractjson("$.[0].id",tostring(TargetResources))
| extend Policy=extractjson("$.[1].value",tostring(AdditionalDetails))
| summarize SignInCount = count() by Policy, OperationName
| order by SignInCount desc  nulls last
```

1. [ **実行**] を選択します。 クエリの結果が画面の下部に表示されます。
2. 後で使用するためにクエリを保存するには、[ **保存]** を選択します。

[Image: Log Analytics ログ エディターのスクリーンショット。]

1. 次の詳細情報を入力します。

- **[名前]** - クエリの名前を入力します。
- **[名前を付けて保存]** - [ `query`を選択します。
- **カテゴリ** - `Log`を選択します。

1. **[保存] を選択します**。

[render](https://learn.microsoft.com/ja-jp/azure/data-explorer/kusto/query/renderoperator?pivots=azuremonitor) 演算子を使用して、クエリを変更してデータを視覚化することもできます。

```kusto
  AuditLogs
  | where TimeGenerated  > ago(90d)
  | where OperationName contains "issue"
  | extend  UserId=extractjson("$.[0].id",tostring(TargetResources))
  | extend Policy=extractjson("$.[1].value",tostring(AdditionalDetails))
  | summarize SignInCount = count() by Policy
  | order by SignInCount desc  nulls last
  | render  piechart
```

[Image: Log Analytics ログ エディターの円グラフのスクリーンショット。]

### データ保持期間の変更

Azure Monitor ログはスケーリングされ、企業内の任意のソースから、または Azure にデプロイされた大量のデータの収集、インデックス作成、格納を毎日サポートします。 既定では、ログは 30 日間保持されますが、保持期間を最大 2 年間に増やすことができます。 詳細については、 [Azure Monitor ログを使用した使用状況とコストの管理に関するページを](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/cost-logs)参照してください。 価格レベルを選択したら、 [データ保有期間を変更](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/data-retention-configure)できます。

### 監視データの収集を無効にする

Log Analytics ワークスペースへのログ収集を停止するには、作成した診断設定を削除します。 ワークスペースに既に収集したログ データを保持するための料金は引き続き発生します。 収集した監視データが不要になった場合は、Log Analytics ワークスペースと、Azure Monitor 用に作成したリソース グループを削除できます。 Log Analytics ワークスペースを削除すると、ワークスペース内のすべてのデータが削除され、他のデータ保持料金が発生するのを防ぐことができます。

### 外部 ID での Microsoft Sentinel の使用

外部テナントからの外部 ID ログがワークフォース テナントの Log Analytics ワークスペースに送信されたら、監視、インシデントルール、アラート、ワークブックのためにMicrosoft Sentinelに取り込むことができます。 外部テナントからの直接セットアップはサポートされていないため、従業員テナントから Sentinel を構成する必要があります。 Sentinel を使用するには:

1. Azure Monitor 診断設定を使用して、従業員テナントの Log Analytics ワークスペースにログを送信します。 外部テナントからの直接構成はサポートされていません。
2. Azure portal で、Log Analytics ワークスペースに Microsoft Sentinel を追加します。 詳細については、「 [Microsoft Sentinel へのオンボード」を](https://learn.microsoft.com/ja-jp/azure/sentinel/quickstart-onboard?tabs=defender-portal#add-microsoft-sentinel-to-your-log-analytics-workspace)参照してください。
3. Defender ポータルで、Microsoft Sentinel コンテンツ ハブを開き、Entra ID コンテンツ パックをインストールします。

#### サポートされている機能

- **分析とアラート:** 事前構築済みのテンプレートを使用してインシデント ルールを構成する。トリガーされたアラートが正しく表示されます。
- **ワークブック：** 事前に構築されたワークブックを使用して、収集されたログを視覚化および分析します。

詳細については、 [Microsoft Sentinel のドキュメントを参照してください](https://learn.microsoft.com/ja-jp/azure/sentinel)。

これらの手順により、サポートされている従業員テナントのセットアップを使用しながら、外部 ID ログの一元的な監視、インシデント管理、視覚化が可能になります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-b2c-federation-customers"} -->
## Azure AD B2C を使用して顧客のサインインを追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-b2c-federation-customers
- Service: entra-external-id / external
- Article date: 2025-05-20
- Summary: Microsoft Entra 外部 IDで外部 ID プロバイダーとして Azure AD B2C テナントを構成し、ユーザーが既存のアカウントを使用してサインインできるようにする方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Von Bedeutung

2025 年 5 月 1 日より、Azure AD B2C は新規のお客様に対して購入できなくなります。 詳細については、[AZURE AD B2C を引き続き購入できますか?](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale)に関する FAQ を参照してください。

ヒント

この記事では、既存の Azure AD B2C テナントとのフェデレーションについて説明します。 代わりに、ユーザーとアプリケーションを Azure AD B2C から外部 ID に移行する場合は、「[Azure AD B2C から外部 ID への移行を計画する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/plan-your-migration-from-b2c-to-external-id)を参照してください。

AZURE AD B2C テナントを ID プロバイダーとして構成するには、Azure AD B2C カスタム ポリシーを作成してから、アプリケーションを作成する必要があります。

### 前提 条件

- カスタム ポリシー スターター パックで構成された Azure AD B2C テナント。 「[Tutorial - ユーザー フローとカスタム ポリシーの作成 - Azure Active Directory B2C |Microsoft Learn](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/tutorial-create-user-flows?pivots=b2c-custom-policy)
    - 電子メールが ID トークンで必要な要求である場合、電子メール要求を受信するには、Azure AD B2C テナントでカスタム ポリシーを使用することが必要になる場合があります。
    - [カスタム ポリシー展開ツールを使用できます](https://aka.ms/iefsetup)

### カスタム ポリシーを構成する

ユーザー フローで有効になっている場合、外部テナントは、Azure AD B2C カスタム ポリシーからトークンで電子メール要求を返す必要がある場合があります。

カスタム ポリシー スターター パックをプロビジョニングした後、Azure AD B2C テナント内の `B2C_1A_signup_signin` ブレードから  ファイルをダウンロードします。

1. [Azure ポータル](https://portal.azure.com)にサインインし、**Azure AD B2C** を選択します。
2. 概要ページの **Policies**で、**Identity Experience Framework**を選択します。
3. `B2C_1A_signup_signin` ファイルを検索して選択します。
4. `B2C_1A_signup_signin`をダウンロード.

テキスト エディターで `B2C_1A_signup_signin.xml` ファイルを開きます。 `<OutputClaims>` ノードの下に、次の出力要求を追加します。

```xml
<OutputClaim ClaimTypeReferenceId="signInName" PartnerClaimType="email"/>
```

ファイルを `B2C_1A_signup_signin.xml` として保存し、Azure AD B2C テナント内の **Identity Experience Framework** ブレードでアップロードします。 **を選択して、既存のポリシー**を上書きします。 この手順により、Azure AD B2C で認証された後に、電子メールアドレスが Microsoft Entra ID に対するクレームとして発行されます。

### Microsoft Entra IDをアプリケーションとして登録する

Microsoft Entra IDをアプリケーションとして Azure AD B2C テナントに登録する必要があります。 この手順により、AZURE AD B2C はフェデレーションのためにMicrosoft Entra IDにトークンを発行できます。

アプリケーションを作成するには:

1. [Azure ポータル](https://portal.azure.com)にサインインし、**Azure AD B2C** を選択します。
2. **アプリの登録** を選択し、**New registration** を選択します。
3. **Name**に「Microsoft Entra ID とのフェデレーション」と入力します。
4. **[サポートされているアカウントの種類]** で、**[(ユーザー フローを使用してユーザーを認証するための) 任意の ID プロバイダーまたは組織のディレクトリのアカウント]** を選択します。
5. [ **リダイレクト URI**] で [ **Web**] を選択し、すべての小文字で次の URL を入力します。ここで、 `tenant-subdomain` は Entra テナントの名前 (Contoso など) に置き換えられます。

    `https://<tenant-subdomain>.ciamlogin.com/<tenant-ID>/federation/oauth2`

    `https://<tenant-subdomain>.ciamlogin.com/<tenant-subdomain>.onmicrosoft.com/federation/oauth2`

    例えば：

    `https://contoso.ciamlogin.com/00aa00aa-bb11-cc22-dd33-44ee44ee44ee/federation/oauth2`

    `https://contoso.ciamlogin.com/contoso.onmicrosoft.com/federation/oauth2`

    カスタム ドメインを使用する場合は、次のように入力します。

    `https://<your-domain-name>/<your-tenant-name>.onmicrosoft.com/oauth2/authresp`

    `your-domain-name` をカスタム ドメインに置き換え、`your-tenant-name` をテナントの名前に置き換えます。
6. **許可**の下にある、**「openid」と「offline\_access」権限に対する管理者の同意を付与する」** チェックボックスを選択します。
7. **[登録]** を選択します。
8. Azure AD B2C - アプリの登録 ページで、作成したアプリケーションを選択し、アプリケーションの概要ページに表示される **Application (クライアント) ID** を記録します。 この ID は、次のセクションで ID プロバイダーを構成するときに必要です。
9. 左側のメニューの [**管理**] で、[**証明書 & シークレット**] を選択します。
10. 新しいクライアントシークレット を選択します。
11. [**説明]** ボックスに、クライアント シークレットの説明を入力します。 例: "FederationWithEntraID"。
12. **[有効期限]** で、[シークレットが有効](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/policy-keys-overview)な期間を選択してから、**[追加]** を選択します。
13. シークレットの **Value**を記録します。 この値は、次のセクションで ID プロバイダーを構成するときに必要になります。

### Azure AD B2C テナントを外部テナントの ID プロバイダーとして構成する

OpenID Connect `well-known` エンドポイントを構築します。`<your-B2C-tenant-name>` をAzure AD B2C テナントの名前に置き換えます。

カスタム ドメイン名を使用している場合は、`<custom-domain-name>` をカスタム ドメインに置き換えます。 `<policy>` を、B2C テナントで構成したポリシー名に置き換えます。 スターター パックを使用している場合は、`B2C_1A_signup_signin` ファイルです。

`https://<your-B2C-tenant-name>.b2clogin.com/<your-B2C-tenant-name>.onmicrosoft.com/<policy>/v2.0/.well-known/openid-configuration`

または

`https://<custom-domain-name>/<your-B2C-tenant-name>.onmicrosoft.com/<policy>/v2.0/.well-known/openid-configuration`

1. 発行者 URI を `https://<your-b2c-tenant-name>.b2clogin.com/<your-b2c-tenant-id>/v2.0/`として構成するか、カスタム ドメインを使用している場合は、`your-b2c-tenant-name.b2clogin.com`の代わりにカスタム ドメイン、ドメインを使用します。
2. **クライアント ID**には、前に記録したアプリケーション ID を入力します。
3. に `client_secret` を選択します。
4. **クライアント シークレットの**には、前に記録したクライアント シークレットを入力します。
5. **スコープ**に「`openid profile email offline_access`」と入力します。
6. 応答の種類として `code` を選択します。
7. クレームマッピングに次の設定を行います。

- **サブ**: sub
- **氏名**: name
- **名**: given\_name
- **家族名**: family\_name
- **電子メール** (必須): 電子メール

サインインとサインアップのために、ID プロバイダーを作成し、アプリケーションに関連付けられているユーザー フローにアタッチします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-configure-akamai-integration"} -->
## Microsoft Entra 外部 ID を使用して Akamai WAF を構成する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-configure-akamai-integration
- Service: entra-external-id / external
- Article date: 2026-01-29
- Summary: Microsoft Entra 外部 ID テナントの攻撃から保護するように Akamai Web Application Firewall (WAF) を構成する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

サード パーティの Web アプリケーション ファイアウォール (WAF) ソリューションを Microsoft Entra External ID と統合して、全体的なセキュリティを向上させることができます。 WAF は、分散型サービス拒否 (DDoS)、悪意のあるボット、Open Worldwide Application Security Project [(OWASP) Top-10](https://owasp.org/www-project-top-ten/) セキュリティ リスクなどの攻撃から組織を保護するのに役立ちます。

Akamai Web Application Firewall ([Akamai WAF](https://www.akamai.com/glossary/what-is-a-waf)) は、一般的な悪用や脆弱性から Web アプリを保護します。 Akamai WAF と Microsoft Entra External ID を統合することで、アプリケーションのセキュリティレイヤーを追加できます。

この記事では、Akamai WAF を使用して外部テナントを構成するための詳細なガイダンスを提供します。

### ソリューションの概要

このソリューションでは、次の 3 つの主要コンポーネントを使用します。

- **外部テナント** – ID プロバイダー (IdP) および承認サーバーとして機能し、認証用のカスタム ポリシーを適用します。
- **Azure Front Door (AFD)** – カスタム ドメイン ルーティングを処理し、Microsoft Entra 外部 ID にトラフィックを転送します。
- **Akamai WAF** – 承認サーバーに送信されるトラフィックを管理する [Web アプリケーション保護機能](https://www.akamai.com/us/en/resources/waf.jsp) ファイアウォール。

### [前提条件]

開始するには、次のものが必要です。

- [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
- Microsoft [Azure Front Door (AFD)](https://learn.microsoft.com/ja-jp/azure/frontdoor/front-door-overview) 構成。 Akamai WAF からのトラフィックは Azure Front Door にルーティングされ、外部テナントにルーティングされます。
- Akamai アカウント。 お持ちでない場合は、 [セキュリティ ストア](https://securitystore.microsoft.com/solutions/akamai-technologies.akamai_wapplusion_public) にアクセスしてアカウントを作成して購入してください。
- 承認サーバーに送信されるトラフィックを管理する [Akamai WAF](https://www.akamai.com/glossary/what-is-a-waf) 。
- Azure Front Door (AFD) で有効になっている外部テナントの [カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain) 。

### Akamai のセットアップ手順

まず、Microsoft Entra 外部 ID のカスタム URL ドメインを保護するように Akamai WAF を設定します。 Akamai WAF を構成するには、次の手順に従います。

#### Akamai WAF の構成

Akamai と契約した後、 [ポータル](https://control.akamai.com/)にアクセスして、Akamai WAF のすべての設定などを管理できます。 初期セットアップをビルドするには、次の 2 つのオプションから選択できます。

- **クイック スタート ウィザード**には、WAF を使用してトラフィックを保護する構成要素をデプロイするためのガイド付きワークフローが用意されています。Akamai を初めて使用する場合にお勧めします。 これらの設定は、後で要件を満たすように更新できます。
- **詳細モード**では、個々のインターフェイスを構成して詳細なカスタマイズを行い、きめ細かく制御できます。

Akamai WAF ソリューションのすべての機能を調べるには、 [ユーザー ガイド](https://techdocs.akamai.com/app-api-protector/docs/welcome)を参照してください。

## [クイック スタート ウィザード](#tab/quick-start-wizard)
Akamai には、新しいホスト名をオンボードし、**アプリと API 保護機能**と呼ばれる WAF ソリューションで保護するための[クイック スタート ウィザード](https://www.akamai.com/products/app-and-api-protector)が用意されています。

[Ion Standard](https://www.akamai.com/products/web-performance-optimization) は、アプリケーションのパフォーマンスを向上させ、Akamai プラットフォームでのコンテンツ配信を最適化する追加ソリューションです。

ウィザードにアクセスするには、 **Get Started**&gt;**App > API 保護機能 + Ion Standard** を選択します。 初期画面には、オンボードを完了するために必要な手順が表示されます。 **[開始]** を選択して開始します。

詳細については、**Akamai ドキュメント**の [Akamai WAF の構成](https://techdocs.akamai.com/initial-aap-setup/docs/welcome)の手順を参照してください。

## [詳細モード](#tab/advanced-mode)
高度な方法を使用する場合は、まず [プロパティ マネージャー](https://control.akamai.com/apps/property-manager/)でプロパティを作成して構成します。 プロパティは、エンド ユーザーからの受信要求を処理して応答する方法を Akamai エッジ サーバーに指示する構成ファイルです。

詳細については、「 [プロパティとは」を](https://techdocs.akamai.com/start/docs/prop)参照してください。 プロパティを作成して構成するには、次の手順に従います。

### プロパティの作成と構成

1. [Akamai コントロール センター](https://control.akamai.com/)に移動してサインインします。
2. **プロパティ マネージャー**に移動します。
3. **[プロパティ バージョン**] で、[**標準** **] または [拡張 TLS** (推奨)] を選択します。
4. **プロパティ ホスト名の場合は**、カスタム ドメインのプロパティ ホスト名を追加します。 例: `login.domain.com`

Important

適切なカスタム ドメイン名設定を使用して証明書を作成または変更します。 詳細については、「 [HTTPS ホスト名の構成」](https://techdocs.akamai.com/property-mgr/docs/serve-content-over-https)を参照してください。

### オリジンサーバーのプロパティ構成設定

配信元サーバーには、次の設定を使用します。

1. [ **配信元の種類]** に、配信元の種類を入力します。
2. **[配信元サーバーのホスト名**] に、ホスト名を入力します。 例: `yourafddomain.azurefd.net`
3. **[Forward host header**]\(ホスト ヘッダーの転送\) で、[**Incoming Host Header]\(受信ホスト ヘッダー\**) を選択します。
4. **[キャッシュ キーのホスト名**] で、[**受信ホスト ヘッダー**] を選択します。

### DNS の構成

`login.domain.com`のホスト名] フィールドの Microsoft Edge ホスト名を指す正規名 (CNAME) レコード ( など) を DNS に作成します。

### Akamai WAF の構成

1. [Akamai コントロール センター](https://control.akamai.com/)に移動してサインインします。
2. **[セキュリティ構成]** に移動します。
3. 新しいセキュリティ構成を作成するには、[ **既存のプロパティの保護**] を選択します。
4. **[構成の詳細]** に構成名を入力し、[作成] を選択**し、運用ネットワークで構成をアクティブにします**。
5. セキュリティ構成の概要については、 https://techdocs.akamai.com/cloud-security/docs/app-api-protectorを参照してください。
6. 以降のセキュリティ構成バージョンでは、**攻撃グループ**の **Web アプリケーション ファイアウォール**でアクションを変更し、**グループ アクション**を **[拒否**] に設定します。

[Image: [グループ アクション] 列内のアクセスが拒否された攻撃グループのスクリーンショット。]

---

### 外部 ID で Akamai WAF を確認する

構成手順を完了したら、認証資格情報を WAF 構成に接続して、Akamai WAF が外部テナントを保護していることを確認します。

## [Microsoft Entra 管理センター](#tab/admin-center)
### WAF プロバイダーの構成

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから前に作成した外部テナントに切り替えます。
3. **Entra ID**&gt;**Security Store** に移動します。
4. [**作業の開始**] を選択して、[**WAF を使用して DDoS からアプリを保護**する] タイルを選択します。
5. [ **WAF プロバイダーの選択** ] で **Akamai** を選択し、[ **次へ**] を選択します。

    [Image: [WAF プロバイダーの選択] ページのスクリーンショット。]
6. Akamai アカウントを作成します。 アカウントをまだお持ちでない場合は、 [セキュリティ ストア](https://securitystore.microsoft.com/solutions/akamai-technologies.akamai_wapplusion_public)でアカウントを作成して購入してください。
7. アクションを実行する Akamai API へのアクセスを許可するには、 [EdgeGrid 認証資格情報](https://techdocs.akamai.com/developer/docs/set-up-authentication-credentials) を作成し、生成されたすべての情報 (`client_secret`、 `host`、 `access_token`、 `client_token`) をメモします。 これらの値は、セットアップ プロセスの後半で再利用します。

さらに、アクションの **API 制限** を、次の表に示す適切なアクセス レベルに更新します。

| Title | Description | アクセス レベル |
| --- | --- | --- |
| [Microsoft Edge 診断](https://developer.akamai.com/) | Microsoft Edge 診断 | 読み取り書き込み |
| [プロパティ マネージャー (PAPI)](https://developer.akamai.com/api/luna/papi/overview.html) | プロパティ マネージャー (PAPI)。 PAPI には、Microsoft Edge ホスト名へのアクセスが必要です。 承認を編集して、API クライアントに HAPI を追加します。 | 読み取り専用 |

1. Microsoft Entra 管理センターに戻り、セットアップを完了します。
2. [ **Akamai WAF の構成]**で、既存の構成を選択するか、新しい構成を作成できます。 新しい構成を作成する場合は、次の情報を追加します。
    - **構成名**: WAF 構成の名前。
    - **ホスト プレフィックス**: Akamai EdgeGrid API 資格情報のホスト プレフィックス。
    - **クライアント シークレット**: Akamai EdgeGrid API 資格情報からのクライアント シークレット。
    - **アクセス トークン**: Akamai EdgeGrid API 資格情報からのアクセス トークン。
    - **クライアント トークン**: Akamai EdgeGrid API 資格情報からのクライアント トークン。

[Image: [WAF プロバイダーの構成] ページのスクリーンショット。]

1. それ以外の場合は、**[次へ]** を選択して次の手順に進みます。

### ドメインの検証

Azure Front Door (AFD) で確認して Akamai WAF 構成に接続できるカスタム URL ドメインを選択します。 この手順により、選択したドメインが高度なセキュリティ機能で保護されます。

1. [ **ドメインの確認** ] を選択して、検証プロセスを開始します。
2. Akamai WAF で保護するカスタム URL ドメインを選択し、[ **確認**] を選択します。

[Image: [ドメインの確認] ページのスクリーンショット。]

1. 確認後、[ **完了]** を選択してプロセスを完了します。

## [マイクロソフト グラフ API](#tab/graph-api)
[Graph Explorer](https://developer.microsoft.com/en-us/graph/graph-explorer) から Microsoft Graph API を使用して、Akamai WAF 統合を構成できます。

呼び出し元が [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) ロールを持ち、 [RiskPreventionProviders.Read.All](https://learn.microsoft.com/ja-jp/graph/permissions-reference#riskpreventionprovidersreadall) アクセス許可に同意していることを確認します。

[Image: アクセス許可への同意を示すスクリーンショット。]

[Image: 同意ボタンを示すスクリーンショット。]

このアクセス許可を使用すると、 `POST .../riskPrevention/webApplicationFirewallProviders` を呼び出してプロバイダーを作成し、 `POST .../riskPrevention/webApplicationFirewallProviders/{webApplicationFirewallProviderId}/verify` を呼び出して確認できます。

### 手順 1: API を使用して Akamai WAF プロバイダーを作成する

Akamai で作成した API クライアントがあることを確認します。 アクションを実行する Akamai API へのアクセスを許可するには、 [EdgeGrid 認証資格情報](https://techdocs.akamai.com/developer/docs/set-up-authentication-credentials) を作成し、生成されたすべての情報 (`client_secret`、 `host`、 `access_token`、 `client_token`) をメモします。

さらに、アクションの **API 制限** を、次の表に示す適切なアクセス レベルに更新します。

| Title | Description | アクセス レベル |
| --- | --- | --- |
| [Microsoft Edge 診断](https://developer.akamai.com/) | Microsoft Edge 診断 | 読み取り書き込み |
| [プロパティ マネージャー (PAPI)](https://developer.akamai.com/api/luna/papi/overview.html) | プロパティ マネージャー (PAPI)。 PAPI には、Microsoft Edge ホスト名へのアクセスが必要です。 承認を編集して、API クライアントに HAPI を追加します。 | 読み取り専用 |

この情報は、バックエンドが WAF 構成をプルして検証するために、ユーザーに代わって Akamai を呼び出すことができるようにするために必要です。

#### リクエスト

次の例は要求を示しています。

```http
POST https://graph.microsoft.com/beta/identity/riskPrevention/webApplicationFirewallProviders
Content-Type: application/json
{
    "@odata.type": "#microsoft.graph.akamaiWebApplicationFirewallProvider",
    "displayName": "Akamai Provider Example",
    "hostPrefix": "akab-exampleprefix",
    "clientSecret": "akamai_example_secret_123",
    "clientToken": "akamai_example_token_456",
    "accessToken": "akamai_example_token_789"
}
```

#### [応答]

次の例は応答を示しています。 ここに示す応答オブジェクトは、読みやすさのために短縮されている可能性があります。

```http
HTTP/1.1 201 Created
Content-Type: application/json
{
    "@odata.context": "https://graph.microsoft.com/beta/$metadata#identity/riskPrevention/webApplicationFirewallProviders/$entity",
    "@odata.type": "#microsoft.graph.akamaiWebApplicationFirewallProvider",
    "id": "00000000-0000-0000-0000-000000000002",
    "displayName": "Akamai Provider Example",
    "hostPrefix": "akab-exampleprefix"
}
```

### 手順 2: API を使用して Akamai WAF プロバイダーを確認する

次の例は、`webApplicationFirewallProvider`を使用して`hostName`を使用してドメインを確認する方法を示しています。

##### リクエスト

次の例は要求を示しています。

```http
POST https://graph.microsoft.com/v1.0/identity/riskPrevention/webApplicationFirewallProviders/{webApplicationFirewallProviderId}/verify
Content-Type: application/json
{
  "hostName": "www.contoso.com"
}identity\authentication\tutorial-enable-cloud-sync-sspr-writeback
```

##### [応答]

次の例は応答を示しています。 ここに示す応答オブジェクトは、読みやすさのために短縮されている可能性があります。

```http
HTTP/1.1 200 OK
Content-Type: application/json
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#microsoft.graph.webApplicationFirewallVerificationModel",
    "id": "00000000-0000-0000-0000-000000000000",
    "verifiedHost": "www.contoso.com",
    "providerType": "akamai",
    "verificationResult": {
        "status": "success",
        "verifiedOnDateTime": "2025-10-04T00:50:26.4909654Z",
        "errors": [],
        "warnings": []
    },
    "verifiedDetails": {
        "@odata.type": "#microsoft.graph.akamaiVerifiedDetailsModel",
        "zoneId": "11111111111111111111111111111111",
        "dnsConfiguration": {
            "name": "www.contoso.com",
            "isProxied": true,
            "recordType": "cname",
            "value": "contoso.azurefd.net",
            "isDomainVerified": true
        },
        "enabledRecommendedRulesets": [
            {
                "rulesetId": "22222222222222222222222222222222",
                "name": "akamai Managed Ruleset",
                "phaseName": "http_request_firewall_managed"
            }
        ],
        "enabledCustomRules": [
            {
                "ruleId": "33333333333333333333333333333333",
                "name": "Block SQL Injection",
                "action": "block"
            },
            {
                "ruleId": "44444444444444444444444444444444",
                "name": "Block XSS",
                "action": "block"
            }
        ]
    }
}
```

---

注

プロバイダーの検証の詳細に対する CRUD (作成、読み取り、更新、削除) 操作では、最大 15 分の遅延が発生する場合があります。 ドメインを削除した場合、確認に最大 15 分かかる場合があります。その後、ドメインを追加し直すことができます。

### トラブルシューティング

| **シナリオ** | **詳細** |
| --- | --- |
| Akamai WAF によってブロックされた要求 | Akamai WAF が要求をブロックすると、 `18.6f64d440.1318965461.2f2b078`などの Akamai 参照コードが返されます。 このコードは、 [セキュリティ イベント エラー Translator](https://control.akamai.com/apps/appsec-security-center/#/security-error-translator) を使用してデバッグできます。 |
| その他のトラブルシューティング ツール | トラブルシューティングにはさまざまなツールが用意されています。 これらのツール [については、こちらをご覧ください](https://techdocs.akamai.com/edge-diagnostics/docs/tools-scenarios-descriptions)。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-configure-waf-integration"} -->
## Microsoft Entra 外部 ID を使用して Cloudflare WAF を構成する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-configure-waf-integration
- Service: entra-external-id / external
- Article date: 2025-11-04
- Summary: 攻撃から保護するために Cloudflare Web Application Firewall (WAF) を構成する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

サード パーティの Web アプリケーション ファイアウォール (WAF) ソリューションを Microsoft Entra External ID と統合して、全体的なセキュリティを向上させることができます。 WAF は、分散型サービス拒否 (DDoS)、悪意のあるボット、Open Worldwide Application Security Project [(OWASP) Top-10](https://owasp.org/www-project-top-ten/) セキュリティ リスクなどの攻撃から組織を保護するのに役立ちます。

Cloudflare Web Application Firewall ([Cloudflare WAF](https://www.cloudflare.com/application-services/products/waf/)) は、Web アプリを一般的な悪用や脆弱性から保護します。 Cloudflare WAF と Microsoft Entra External ID を統合することで、アプリケーションのセキュリティレイヤーを追加できます。

この記事では、Cloudflare WAF を使用して外部テナントを構成するための詳細なガイダンスを提供します。

### ソリューションの概要

このソリューションでは、次の 3 つの主要コンポーネントを使用します。

- **外部テナント** – ID プロバイダー (IdP) および承認サーバーとして機能し、認証用のカスタム ポリシーを適用します。
- **Azure Front Door (AFD)** – カスタム ドメイン ルーティングを処理し、Microsoft Entra 外部 ID にトラフィックを転送します。
- **Cloudflare WAF** – 承認サーバーに送信されるトラフィックを管理する WAF。

### [前提条件]

開始するには、次のものが必要です。

- [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
- Microsoft [Azure Front Door (AFD)](https://learn.microsoft.com/ja-jp/azure/frontdoor/front-door-overview) 構成。 Cloudflare WAF からのトラフィックは、Azure Front Door にルーティングされ、外部テナントにルーティングされます。
- 承認サーバーに送信されるトラフィックを管理する [Cloudflare WAF](https://www.cloudflare.com/application-services/products/waf/) 。
- Azure Front Door (AFD) で有効になっている外部テナントの [カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain) 。

[Microsoft Entra External ID](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview) を使用したコンシューマーと顧客向けのテナントとアプリのセキュリティ保護について説明します。

### Cloudflare のセットアップ手順

まず、Microsoft Entra External ID のカスタム URL ドメインを保護するように Cloudflare WAF を設定します。 Cloudflare WAF を構成するには、次の手順に従います。

#### カスタム URL ドメインを有効にする

最初の手順では、AFD でカスタム ドメインを有効にします。 [「外部テナントのアプリのカスタム URL ドメインを有効にする」の手順を使用します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain)。

#### Cloudflare アカウントを作成する

1. [Cloudflare.com/plans](https://www.cloudflare.com/plans/) に移動してアカウントを作成します。
2. WAF を有効にするには、[ **Application Services** ] タブで [ **Pro**] を選択します。

#### ドメイン ネーム サーバー (DNS) を構成する

ドメインの WAF を有効にします。

1. DNS コンソールで、CNAME の場合、プロキシ設定を有効にします。

    [Image: CNAME オプションのスクリーンショット。]
2. [DNS] の [ **プロキシの状態**] で、[ **プロキシ]** を選択します。
3. 状態がオレンジ色に変わります。

    [Image: プロキシされた状態のスクリーンショット。]

注

カスタム ドメインの CNAME レコードが Azure Front Door エンドポイントのドメイン以外の DNS レコードを指している場合 (たとえば、Cloudflare などのサードパーティの DNS サービスを使用している場合) は、Azure Front Door で管理される証明書は自動的に更新されません。 このような場合に証明書を更新するには、 [Azure Front Door で管理される証明書の更新](https://learn.microsoft.com/ja-jp/azure/frontdoor/domain#renew-azure-front-door-managed-certificates) に関する記事の手順に従います。

#### Cloudflare のセキュリティ コントロール

最適な保護を実現するには、Cloudflare のセキュリティ制御を有効にします。

#### DDoS 保護

1. [Cloudflare ダッシュボード](https://developers.cloudflare.com/workers/get-started/dashboard/)に移動します。
2. [セキュリティ] セクションを展開します。
3. **[DDoS]** を選択します。
4. メッセージが表示されます。

[Image: ボット保護オプションのスクリーンショット。]

#### ボット保護

1. [Cloudflare ダッシュボード](https://developers.cloudflare.com/workers/get-started/dashboard/)に移動します。
2. [セキュリティ] セクションを展開します。
3. [ **スーパーボットの戦いモードの構成**] で、[ **確実に自動化]** で [ **ブロック**] を選択します。
4. [ **自動である可能性が高い**] で、[ **マネージド チャレンジ**] を選択します。
5. **検証済みボットの場合は**、[許可] を選択**します**。

[Image: ボット保護オプションのスクリーンショット。]

#### ファイアウォール規則: Tor ネットワークからのトラフィック

組織でトラフィックをサポートする必要がない限り、Tor プロキシ ネットワークから送信されたトラフィックをブロックします。

注

Tor トラフィックをブロックできない場合は、[**ブロック] ではなく** [**対話型チャレンジ**] を選択します。

#### Tor ネットワークからのトラフィックをブロックする

1. [Cloudflare ダッシュボード](https://developers.cloudflare.com/workers/get-started/dashboard/)に移動します。
2. [セキュリティ] セクションを展開します。
3. **WAF** を選択します。
4. [ **ルールの作成] を選択します**。
5. [ **ルール名]** に、関連する名前を入力します。
6. [**受信要求が一致する場合** **] で、[フィールド]** で [**大陸**] を選択します。
7. **「演算子」**で「**イコール**」を選択します。
8. **値** で **Tor** を選択します。
9. [ **次にアクションを実行**する] で、[ **ブロック**] を選択します。
10. **場所**で**最初**を選択します。
11. **[デプロイ]** を選択します。

[Image: [ルールの作成] ダイアログのスクリーンショット。]

注

訪問者用のカスタム HTML ページを追加できます。

#### ファイアウォール規則: 国または地域からのトラフィック

組織がすべての国または地域からのトラフィックをサポートするビジネス上の理由がない限り、ビジネスが発生する可能性が低い国または地域からのトラフィックに対して厳格なセキュリティ制御をお勧めします。

注

国または地域からのトラフィックをブロックできない場合は、[**ブロック**] ではなく [**対話型チャレンジ**] を選択します。

#### 国または地域からのトラフィックをブロックする

次の手順では、訪問者用のカスタム HTML ページを追加できます。

1. [Cloudflare ダッシュボード](https://developers.cloudflare.com/workers/get-started/dashboard/)に移動します。
2. [セキュリティ] セクションを展開します。
3. **WAF** を選択します。
4. [ **ルールの作成] を選択します**。
5. [ **ルール名]** に、関連する名前を入力します。
6. [**受信要求が一致する場合** **] で、[フィールド]** で **[国/地域**] または **[大陸**] を選択します。
7. **「演算子」**で「**イコール**」を選択します。
8. [ **値]** で、ブロックする国/地域または大陸を選択します。
9. [ **次にアクションを実行**する] で、[ **ブロック**] を選択します。
10. **場所に**、**最後**を選択します。
11. **[デプロイ]** を選択します。

[Image: [ルールの作成] ダイアログの [名前] フィールドのスクリーンショット。]

#### OWASP とマネージド ルールセット

1. [ **マネージド ルール] を選択します**。
2. **Cloudflare Managed Ruleset** の場合は、[**有効]** を選択します。
3. **Cloudflare OWASP コア ルールセット**の場合は、[**有効]** を選択します。

[Image: ルール セットのスクリーンショット。]

### 外部 ID で Cloudflare WAF を確認する

Cloudflare アカウントを設定したら、それを Microsoft Entra External ID に接続します。 Cloudflare [API トークン](https://developers.cloudflare.com/fundamentals/api/get-started/create-token/) と [ゾーン ID を](https://developers.cloudflare.com/fundamentals/account/find-account-and-zone-ids/#copy-your-zone-id) 使用して接続を完了します。 管理センターで、または Microsoft Graph API を使用してこれを行うことができます。

## [Microsoft Entra 管理センター](#tab/admin-center)
### WAF プロバイダーの構成

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから前に作成した外部テナントに切り替えます。
3. **Entra ID**&gt;**Security Store** に移動します。
4. [**作業の開始**] を選択して、[**WAF を使用して DDoS からアプリを保護**する] タイルを選択します。
5. [ **WAF プロバイダーの選択** ] で **[Cloudflare** ] を選択し、[ **次へ**] を選択します。

    [Image: [WAF プロバイダーの選択] ページのスクリーンショット。]
6. [ **Cloudflare WAF の構成]** で、既存の構成を選択するか、新しい構成を作成できます。 新しい構成を作成する場合は、次の情報を追加します。

    - **構成名**: WAF 構成の名前。
    - **API トークン**: Cloudflare ダッシュボードからの API トークン。
    - **ゾーン ID**: Cloudflare ダッシュボードからのドメインのゾーン ID。

    [Image: [WAF プロバイダーの構成] ページのスクリーンショット。]
7. [ **次へ** ] を選択して変更を保存します。

### ドメインの検証

Azure Front Door (AFD) が検証および接続できるカスタム URL ドメインを選択し、Cloudflare WAF 構成に接続します。 この手順により、選択したドメインが高度なセキュリティ機能で保護されます。

1. [ **ドメインの確認** ] を選択して、検証プロセスを開始します。
2. Cloudflare WAF で保護するカスタム URL ドメインを選択し、[ **確認**] を選択します。

    [Image: [ドメインの確認] ページのスクリーンショット。]
3. 確認後、[ **完了]** を選択します。

## [マイクロソフト グラフ API](#tab/graph-api)
[Graph Explorer](https://developer.microsoft.com/en-us/graph/graph-explorer) から Microsoft Graph API を使用して、Cloudflare WAF 統合を構成できます。

呼び出し元が [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) ロールを持ち、 [RiskPreventionProviders.Read.All](https://learn.microsoft.com/ja-jp/graph/permissions-reference#riskpreventionprovidersreadall) アクセス許可に同意していることを確認します。

[Image: アクセス許可への同意を示すスクリーンショット。]

[Image: 同意ボタンを示すスクリーンショット。]

このアクセス許可を使用すると、 `POST .../riskPrevention/webApplicationFirewallProviders` を呼び出してプロバイダーを作成し、 `POST .../riskPrevention/webApplicationFirewallProviders/{webApplicationFirewallProviderId}/verify` を呼び出して確認できます。

### 手順 1: API を使用して Cloudflare WAF プロバイダーを作成する

Cloudflare で作成した **API トークン** と、ドメインの **ゾーン ID があることを** 確認します。 この情報は、バックエンドがユーザーに代わって Cloudflare を呼び出して WAF 構成をプルして検証できるようにするために必要です。

#### リクエスト

次の例は、新しい Cloudflare WAF オブジェクトを作成する要求を示しています。

```http
POST https://graph.microsoft.com/beta/identity/riskPrevention/webApplicationFirewallProviders
Content-Type: application/json
{
    "@odata.type": "#microsoft.graph.cloudFlareWebApplicationFirewallProvider",
    "displayName": "Cloudflare Provider Example",
    "zoneId": "11111111111111111111111111111111",
    "apiToken": "cf_example_token_123"
}
```

#### [応答]

次の例は、Cloudflare WAF オブジェクトを使用した応答を示しています。

```http
HTTP/1.1 201 Created
Content-Type: application/json
{
    "@odata.context": "https://graph.microsoft.com/beta/$metadata#identity/riskPrevention/webApplicationFirewallProviders/$entity",
    "@odata.type": "#microsoft.graph.cloudFlareWebApplicationFirewallProvider",
    "id": "00000000-0000-0000-0000-000000000001",
    "displayName": "Cloudflare Provider Example",
    "zoneId": "11111111111111111111111111111111"
}
```

### 手順 2: API を使用して Cloudflare WAF プロバイダーを確認する

次の例は、`webApplicationFirewallProvider`を使用して`hostName`を使用してドメインを確認する方法を示しています。

##### リクエスト

次の例は要求を示しています。

```http
POST https://graph.microsoft.com/v1.0/identity/riskPrevention/webApplicationFirewallProviders/{webApplicationFirewallProviderId}/verify
Content-Type: application/json
{
  "hostName": "www.contoso.com"
}
```

##### [応答]

次の例は応答を示しています。

```http
HTTP/1.1 200 OK
Content-Type: application/json
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#microsoft.graph.webApplicationFirewallVerificationModel",
    "id": "00000000-0000-0000-0000-000000000000",
    "verifiedHost": "www.contoso.com",
    "providerType": "cloudflare",
    "verificationResult": {
        "status": "success",
        "verifiedOnDateTime": "2025-10-04T00:50:26.4909654Z",
        "errors": [],
        "warnings": []
    },
    "verifiedDetails": {
        "@odata.type": "#microsoft.graph.cloudFlareVerifiedDetailsModel",
        "zoneId": "11111111111111111111111111111111",
        "dnsConfiguration": {
            "name": "www.contoso.com",
            "isProxied": true,
            "recordType": "cname",
            "value": "contoso.azurefd.net",
            "isDomainVerified": true
        },
        "enabledRecommendedRulesets": [
            {
                "rulesetId": "22222222222222222222222222222222",
                "name": "CloudFlare Managed Ruleset",
                "phaseName": "http_request_firewall_managed"
            }
        ],
        "enabledCustomRules": [
            {
                "ruleId": "33333333333333333333333333333333",
                "name": "Block SQL Injection",
                "action": "block"
            },
            {
                "ruleId": "44444444444444444444444444444444",
                "name": "Block XSS",
                "action": "block"
            }
        ]
    }
}
```

---

注

プロバイダーの検証の詳細に対する CRUD (作成、読み取り、更新、削除) 操作では、最大 15 分の遅延が発生する場合があります。 ドメインを削除した場合、確認に最大 15 分かかる場合があります。その後、ドメインを追加し直すことができます。

#### 構成をテストする

Cloudflare WAF を Microsoft Entra External ID に接続した後、構成をテストして、すべてが期待どおりに動作することを確認します。

[Image: 構成テストの結果を示すスクリーンショット。]

### トラブルシューティング

次の表に、Cloudflare WAF と Microsoft Entra External ID を統合するときに発生する可能性がある一般的な問題とその詳細と解決策を示します。

| **問題** | **詳細** | **Resolution** |
| --- | --- | --- |
| 無効な要求の応答 | "指定された API キーには十分なアクセス許可がありません。 再認証して、もう一度やり直してください。\r\n CloudFlare 要求 ID: {CF-Ray-value}\r\n関連付け ID: random-id-entry-value\r\nTimestamp: 2024-08-25 21:32:40Z" | 前の手順で説明したアクセス許可レベルを確認します。 |
| 外部テナントの既知のエンドポイントに到達できませんでした | "カスタム ドメイン経由でテナントの既知のエンドポイントに到達できませんでした。 カスタム ドメインがトラフィックをルーティングするように適切に構成されていることを確認してください。Graph レベルでは、Cloudflare がエラー コード **403** を返すと、**HTTP 200 OK** と状態**エラー**が表示されることがあります。 このチェックは、Cloudflare への API 呼び出しの前に、Microsoft 側で実行されます。 | Cloudflare ポータルで captcha を無効にしてから (ワイルドカードを無効に変更して)、POST 要求を再実行します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-create-external-tenant-portal"} -->
## 外部テナントを作成する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal
- Service: entra-external-id / external
- Article date: 2025-11-06
- Summary: 外部テナントを作成して、顧客 ID およびアクセス管理 (CIAM) サービスとして Microsoft Entra 外部 IDを開始します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra 外部 ID には、アプリとサービス用に安全でカスタマイズされたサインイン エクスペリエンスを作成できる顧客 ID アクセス管理 (CIAM) ソリューションが用意されています。 これらの組み込みの CIAM 機能を使用すると、Microsoft Entra 外部 ID は顧客シナリオの ID プロバイダーおよびアクセス管理サービスとして機能できます。 作業を開始するには、Microsoft Entra 管理センターで外部テナントを作成する必要があります。 外部テナントが作成されたら、Microsoft Entra 管理センターと Azure portal の両方でそれにアクセスできます。

この記事では、次のことについて説明します。

- 外部テナントの作成
- 外部テナントが含まれているディレクトリに切り替える
- Microsoft Entra 管理センターで外部テナントの名前と ID を見つける

### 前提条件

- Azure サブスクリプション。 お持ちでない場合は、開始する前に[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。
- サブスクリプション、またはサブスクリプション内のリソース グループを対象とする[テナント作成者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#tenant-creator)以上のロールが割り当てられている Azure アカウント。

### 新しい外部テナントの作成

1. 少なくとも[テナント作成者](https://entra.microsoft.com/)として、組織の [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#tenant-creator)にサインインします。
2. **Entra ID**&gt;**概要**&gt;**テナントを管理**を参照します。
3. **［作成］** を選択します

    [Image: テナントの作成オプションのスクリーンショット。]
4. [ **外部**] を選択し、[ **続行] を選択**します。

    [Image: テナントの種類の選択画面のスクリーンショット。]
5. 外部テナントを初めて作成する場合は、Azure サブスクリプションを必要としない試用版テナントを作成できます。 それ以外の場合は、[Azure サブスクリプション] オプションを使用して次の手順に進みます。
6. 30 日間の無料試用版を選択した場合、Azure サブスクリプションは必要ありません。
7. **[Azure サブスクリプションの使用]** オプションを選択すると、管理センターにテナント作成ページが表示されます。 **[顧客用のテナントの作成]** ページの **[基本]** タブで、次の情報を入力します。

    [Image: [基本] タブのスクリーンショット。]

    - 目的の **[テナント名]** (例: *Contoso Customers*) を入力します。
    - 目的の **[ドメイン名]** (例: *Contosocustomers*) を入力します。
8. 目的の **国/地域**を選択します。 この選択を後から変更することはできません。

    オーストラリアや日本などの Go-Local アドオンをサポートする国/地域を選択すると、 **Go-Local データ所在地** オプションが表示されます。 Microsoft Entra ID Core Store データと Microsoft Entra ID コンポーネントとサービス データを選択した場所に格納するように選択できます。 詳細については、「 [Go-Local アドオン](https://learn.microsoft.com/ja-jp/entra/fundamentals/data-residency#go-local-add-on)」を参照してください。

    [Image: [基本] タブと [データ所在地の Go-Local] オプションのスクリーンショット。]
9. **[次へ: サブスクリプションの追加]** を選択します。
10. **[サブスクリプションの追加]** タブで、次の情報を入力します。

    - **[サブスクリプション]** の横にあるメニューから自分のサブスクリプションを選択します。
    - **[リソース グループ]** の横にあるメニューからリソース グループを選択します。 使用可能なリソース グループがない場合は、**[新規作成]** を選択し、**[名前]** を入力して、**[OK]** を選択します。
    - **[リソース グループの場所]** が表示されたら、メニューからリソース グループの地理的な場所を選択します。

    [Image: サブスクリプションの設定を示すスクリーンショット。]
11. **次へ: 確認 + 作成** を選択します。 入力した情報が正しい場合は、**[作成]** を選択します。 テナント作成プロセスは、最長で 30 分かかる場合があります。 テナント作成プロセスの進行状況は、**[通知]** ウィンドウで監視できます。 外部テナントが作成されたら、Microsoft Entra 管理センターと Azure portal の両方でそれにアクセスできます。

    [Image: 新しい外部テナントへのリンクを示すスクリーンショット。]

注記

Visual Studio Code の [Microsoft Entra 外部 ID 拡張機能](https://aka.ms/ciamvscode/quickstarts/marketplace)を使用して、Visual Studio Code 内で直接試用版または有料の外部テナントを設定することもできます ([詳細はこちら](https://aka.ms/ciamvscode/quickstartguide))。

### 外部テナントの詳細を取得する

外部テナントを含むディレクトリがわからない場合は、Microsoft Entra 管理センターと Azure portal の両方でテナント名と ID を確認できます。

1. 複数のテナントにアクセスできる場合、上部のメニューの **[設定]** アイコン  を選択し、**[ディレクトリとサブスクリプション]** メニューから外部テナントに切り替えます。

    [Image: [Directories + subscriptions](ディレクトリ + サブスクリプション) アイコンのスクリーンショット。]
2. **[ポータルの設定] | [Directories + subscriptions]\(ディレクトリ + サブスクリプション\)** ページの **[ディレクトリ名]** の一覧で外部テナントを見つけて、**[切り替え]** を選択します。 この手順により、テナントのホーム ページに移動します。
3. **クイック ナビゲーション**で**テナントの概要**を選択します。 テナントの **[名前]**、**[テナント ID]**、**[プライマリ ドメイン]** は、**[概要]** タブの下にあります。

    [Image: テナントの詳細のスクリーンショット。]

Azure portal で **Microsoft Entra ID** に移動した場合も、同じ詳細を確認できます。 **[Microsoft Entra ID]** ページの **[概要]** **[基本情報]** で、テナントの **[名前]**、&gt;、**[プライマリ ドメイン]** を見つけることができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-custom-oidc-federation-customers"} -->
## 顧客サインイン用に OIDC を追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers
- Service: entra-external-id / external
- Article date: 2026-07-29
- Summary: Microsoft Entra 外部 ID で OpenID Connect を外部 ID プロバイダーとして設定し、ユーザーが既存のアカウントを使用してサインインできるようにする方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

カスタム構成の OpenID Connect (OIDC) ID プロバイダーとのフェデレーションを設定すると、ユーザーはフェデレーション外部プロバイダーの既存のアカウントを使用してアプリケーションにサインインできるようになります。 この OIDC フェデレーションにより、OpenID Connect プロトコルに準拠するさまざまなプロバイダーとの認証が可能になります。 (顧客向けの認証方法と ID プロバイダー の詳細については、こちらを参照してください)。

### 前提 条件

- [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
- 外部テナントに [登録されているアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 。
- [サインアップとサインインのユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)。

### OpenID Connect ID プロバイダーを設定する

ユーザーを ID プロバイダーにフェデレーションするには、まず、外部テナントからのフェデレーション要求を受け入れるように ID プロバイダーを準備します。 この準備を行うには、リダイレクト URI を追加し、認識されるように ID プロバイダーを登録します。

次の手順に進む前に、次のようにリダイレクト URI を追加します。

`https://<tenant-subdomain>.ciamlogin.com/<tenant-ID>/federation/oauth2`

`https://<tenant-subdomain>.ciamlogin.com/<tenant-subdomain>.onmicrosoft.com/federation/oauth2`

### ID プロバイダーでのサインインとサインアップを有効にする

ID プロバイダーのアカウントを持つユーザーのサインインとサインアップを有効にするには、Microsoft Entra ID をアプリケーションとして ID プロバイダーに登録する必要があります。 この手順により、ID プロバイダーはフェデレーションのために Microsoft Entra ID を認識してトークンを発行できます。 設定されたリダイレクト URI を使用してアプリケーションを登録します。 ID プロバイダー構成の詳細を保存して、外部テナントにフェデレーションを設定します。

#### フェデレーション設定

Microsoft Entra 外部 ID で ID プロバイダーとの OpenID Connect フェデレーションを構成するには、次の設定が必要です。

- **既知のエンドポイント**
- **発行者 URI**
- **クライアント ID**
- **クライアント認証方法**
- **クライアント シークレット**
- **スコープ**
- **応答の種類**
- **クレームマッピング**
    - Sub
    - 名前
    - 指定された名前
    - 姓
    - 電子メール (既定では必須。 省略可能)
    - メールが確認されました
    - 電話番号
    - 電話番号が確認されました
    - 番地
    - 地域
    - 地域
    - 郵便番号
    - Country

### 管理センターで新しい OpenID Connect ID プロバイダーを構成する

ID プロバイダーを構成したら、この手順を完了して、Microsoft Entra 管理センターで新しい OpenID Connect フェデレーションを構成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[外部 ID]**&gt;**[すべての ID プロバイダー]** に移動します。
3. [**カスタム**] タブを選択し、[**新規追加**]&gt;**Open ID Connect** を選択します。

    [Image: 新しいカスタム ID プロバイダーの追加のスクリーンショット。]
4. ID プロバイダーの次の詳細を入力します。

    - **表示名**: サインインおよびサインアップ フロー中にユーザーに表示する ID プロバイダーの名前。 たとえば、 *IdP 名でサインイン* するか、 *IdP 名でサインアップします*。
    - 既知のエンドポイント (メタデータ URI とも呼ばれます) は、ID プロバイダーの構成情報 を取得 OIDC 検出 URI です。 応答は、OAuth 2.0 エンドポイントの場所を含む JSON ドキュメントです。 少なくとも、メタデータ ドキュメントには、 `issuer`、 `authorization_endpoint`、 `token_endpoint`、 `token_endpoint_auth_methods_supported`、 `response_types_supported`、 `subject_types_supported`、 `jwks_uri`の各プロパティが含まれている必要があります。 詳細については、「 [OpenID Connect Discovery](https://openid.net/specs/openid-connect-discovery-1_0.html) の仕様」を参照してください。
    - **OpenID 発行者 URI**: アプリケーションのアクセス トークンを発行する ID プロバイダーのエンティティ。 たとえば、OpenID Connect を使用して [Azure AD B2C とフェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-b2c-federation-customers)する場合、発行者 URI は `https://login.b2clogin.com/{tenant}/v2.0/` のようになります。 発行者 URI は、https スキームを使用する大文字と小文字が区別される URL です。 これにはスキーム、ホスト、および必要に応じてポート番号とパスのコンポーネントが含まれますが、クエリコンポーネントやフラグメントコンポーネントはありません。

    手記

    Microsoft Entra ID テナントとフェデレーションするには、「 [Microsoft Entra ID テナントを OpenID Connect ID プロバイダーとして追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-entra-id-federation-customers)」を参照してください。 OIDC フェデレーションは、 [外部ユーザーの招待 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-supported-features-customers#identity-providers-and-authentication-methods) 機能とも互換性がありません。

    - **クライアント ID** と **クライアント シークレット** は、ID プロバイダーが登録済みのアプリケーション サービスを識別するために使用する識別子です。 `client_secret` ベースの認証方法を選択するときに、クライアント シークレットを指定します。
    - **クライアント認証** は、トークン エンドポイントを使用して ID プロバイダーで認証するために使用されるクライアント認証方法の種類です。 `client_secret_post` および `client_secret_jwt` 認証方法がサポートされています。 管理センターの UI にはオプションとして `private_key_jwt` 表示される場合がありますが、このメソッドは現在サポートされていないため、選択しないでください。

    手記

    セキュリティの問題が発生する可能性があるため、 `client_secret_basic` クライアント認証方法はサポートされていません。

    - **スコープ** は、ID プロバイダーから収集する情報とアクセス許可 ( `openid profile`など) を定義します。 OpenID Connect 要求には、ID プロバイダーから ID トークンを受け取るために、 `openid` スコープ値が含まれている必要があります。 その他のスコープは、スペースで区切って追加できます。 、`profile`など、他の使用可能なスコープについては、`email`を参照してください。
    - **応答の種類** は、ID プロバイダーの `authorization_endpoint` への最初の呼び出しで返される情報の種類を表します。 現時点では、`code` 応答の種類のみがサポートされています。 `id_token` と `token` はサポートされません。
5. [ **次へ: 要求マッピング]** を選択して [要求マッピング](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-oidc-claims-mapping-customers) を構成するか、 **確認と作成** を選択して ID プロバイダーを追加します。

手記

マイクロソフトは*暗黙的な許可フロー*や[ROPC フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow#security-concerns-with-implicit-grant-flow)を[使用しないようにする](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc)ことをお勧めします。 そのため、OpenID Connect 外部 ID プロバイダーの構成では、これらのフローはサポートされません。 SPA をサポートする推奨される方法は、OIDC フェデレーション構成でサポートされている OAuth 2.0 Authorization コード フロー (PKCE を使用)です。

### ユーザーがサインインして ID プロバイダーにサインアップできるようにする

OIDC ID プロバイダーを構成したら、それをユーザー フローに追加して、ID プロバイダーへのサインインとサインアップを許可します。 [ユーザー フローへの ID プロバイダーの追加を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-identity-provider-to-user-flow-customers)参照してください。

### 外部 ID プロバイダーのサインアップに電子メールを省略可能にする

既定では、ユーザーが外部 ID プロバイダー (IdP) にサインアップするときに電子メール アドレスが必要です。 外部 IdP が電子メール要求を送信しない場合、ユーザーはサインアップ中にエラー `AADSTS901011: No email address was obtained from the external oidc identity provider` が発生します。 このエラーを回避するには、電子メール属性を省略可能にするようにユーザー フローを構成します。 その後、ユーザーは、電子メール アドレスを指定せずに、外部 IdP ID のみでサインアップを完了できます。

Important

電子メールを省略可能にすることは、ユーザー フロー レベルの設定です。 この変更は、ユーザー フローに関連付けられている **すべてのアプリケーションの** サインアップに適用されます。

Tip

通常、アカウント ピッカーにはユーザーのメール アドレスが表示されます。 電子メール アドレスが収集されない場合は、代わりに表示名が表示されます。 ユーザーが自分のアカウントを簡単に識別できるようにするには、[`name`] で要求をマップするか、サインアップ時に表示名を収集します。

#### 電子メールを省略可能にするようにユーザー フローを更新する

ユーザー フローで電子メール属性を省略可能にするには、Microsoft Graph API を使用して、ユーザー フローの `onAttributeCollection` プロパティを更新します。

1. 更新するユーザー フローの ID を見つけます。 これを行う 1 つの方法は、 [Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer) を使用して、すべてのユーザー フローを一覧表示することです。

    ```http
    GET https://graph.microsoft.com/v1.0/identity/authenticationEventsFlows
    ```

    応答で、ユーザー フローの `id` と `onAttributeCollection` プロパティを見つけます。
2. 応答から `onAttributeCollection` プロパティをコピーし、それを使用してユーザー フローを `PATCH` 要求で更新します。 必要な変更は、電子メール属性の `required` プロパティを `false` に設定することだけです。

    ```http
    PATCH https://graph.microsoft.com/v1.0/identity/authenticationEventsFlows/{user-flow-id}
    Content-Type: application/json
    
    {
        "@odata.type": "#microsoft.graph.externalUsersSelfServiceSignUpEventsFlow",
        "onAttributeCollection": {
            "@odata.type": "#microsoft.graph.onAttributeCollectionExternalUsersSelfServiceSignUp",
            "attributeCollectionPage": {
                "views": [
                    {
                        "title": null,
                        "description": null,
                        "inputs": [
                            {
                                "attribute": "email",
                                "label": "Email Address",
                                "inputType": "text",
                                "defaultValue": null,
                                "hidden": false,
                                "editable": true,
                                "writeToDirectory": true,
                                "required": false,
                                "validationRegEx": "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
                                "options": []
                            }
                        ]
                    }
                ]
            }
        }
    }
    ```

    手記

    電子メール属性だけでなく、既存のユーザー フローからのすべての属性入力を `PATCH` 要求に含めます。 前の例では電子メール入力のみを示していますが、ユーザー フローには追加の属性が含まれている可能性があります。 完全なスキーマについては、 [authenticationAttributeCollectionPage リソースの種類に関するページを参照してください](https://learn.microsoft.com/ja-jp/graph/api/resources/authenticationattributecollectionpage)。

### 既知の制限

#### 発行者 URI の更新

既存の OIDC ID プロバイダー (IdP) の発行者 URI を更新すると、更新された構成がユーザー フローで自動的に有効にならない場合があります。 その結果、IdP サインイン オプションがサインイン ページに表示されない可能性があります。

変更を適用するには:

1. ユーザー フローで IdP を無効にします。
2. ユーザー フローを保存します。
3. IdP を再度有効にします。
4. ユーザー フローをもう一度保存します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-custom-url-domain"} -->
## 外部 ID 向けのカスタム URL ドメインを有効にする方法 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain
- Service: entra-external-id / external
- Article date: 2024-12-03
- Summary: アプリの外部顧客や一般消費者向けに、認証サインインエンドポイントをパーソナライズするためのカスタム URL ドメインの設定方法を説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、外部テナントで Microsoft Entra 外部 ID アプリケーションの [カスタム URL ドメイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-url-domain) を有効にする方法について説明します。 カスタム URL ドメインを使用すると、Microsoft の既定のドメイン名ではなく、独自のカスタム URL ドメインを使用してアプリケーションのサインイン エンドポイントをブランド化できます。

### 前提条件

- [カスタム URL ドメインが外部 ID でどのように機能するかについて説明](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-url-domain) します。
- 外部テナントをまだ作成していない場合は、 [ここで作成します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
- [ユーザーが](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) サインアップしてアプリケーションにサインインできるように、ユーザー フローを作成します。
- [Web アプリケーションを登録します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。

### ステップ 1:カスタム ドメイン名をテナントに追加する

外部テナントを作成すると、初期ドメイン名として &lt;domainname&gt;.onmicrosoft.com が付与されます。 初期ドメイン名は変更したり削除したりできませんが、自分自身のカスタム ドメイン名を追加することができます。 これらの手順では、必ず、Microsoft Entra 管理センターの *"外部"* テナント構成にサインインしてください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ドメイン名管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#domain-name-administrator)としてサインインします。
2. *外部*テナントを選択する: 上部メニューの **[設定]** アイコンを選択し、外部テナントに切り替えます。
3. **Identity**&gt;**Settings**&gt;**Domain names**&gt;**Custom domain names**に移動します。
4. [カスタム ドメイン名を](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain#add-your-custom-domain-name) Microsoft Entra ID に追加します。
5. [DNS 情報をドメイン レジストラーに追加します](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain#add-your-dns-information-to-the-domain-registrar)。 テナントにカスタム ドメイン名を追加した後、ドメインの DNS `TXT` または `MX` レコードを作成します。 ドメインのこの DNS レコードを作成することで、ドメイン名の所有権が検証されます。

    login.contoso.com *と* *account.contoso.com* の TXT レコードの例を次に示します。

    | 名前 (ホスト名) | タイプ | データ |
    | --- | --- | --- |
    | ログイン (login) | TXT | MS=ms12345678 |
    | アカウント | TXT | MS=ms87654321 |

    TXT レコードは、ドメインのサブドメインまたはホスト名 (たとえば、*contoso.com* ドメインの*ログイン*部分) に関連付ける必要があります。 ホスト名が空または `@` の場合、Microsoft Entra ID は、追加したカスタム ドメイン名を確認できません。

    ヒント

    GoDaddy などの一般公開されている DNS サービスを使用して、カスタム ドメイン名を管理できます。 DNS サーバーがない場合は、 [Azure DNS ゾーン](https://learn.microsoft.com/ja-jp/azure/dns/dns-getstarted-portal)または [App Service ドメインを](https://learn.microsoft.com/ja-jp/azure/app-service/manage-custom-dns-buy-domain)使用できます。
6. [カスタム ドメイン名を確認します](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain#verify-your-custom-domain-name)。 使用する予定の各サブドメインまたはホスト名を検証します。 たとえば、 *login.contoso.com* と *account.contoso.com* でサインインできるようにするには、最上位ドメイン contoso.com だけでなく、両方の *サブドメインを*確認する必要があります。

    重要

    ドメインが検証された後で、作成した DNS TXT レコードを削除します。

### ステップ 2: カスタム ドメイン 名をカスタム URL ドメイン に関連付ける

外部テナントにカスタム ドメイン名を追加して確認した後、カスタム ドメイン名をカスタム URL ドメインに関連付けます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. *外部*テナントを選択する: 上部メニューの **[設定]** アイコンを選択し、外部テナントに切り替えます。
3. **Entra ID**&gt;**ドメイン名**&gt;**Custom URL ドメイン**に移動します。
4. [ **カスタム URL ドメインの追加] を選択します**。
5. [ **カスタム URL ドメインの追加** ] ウィンドウで、 手順 1 で入力したカスタム ドメイン名を選択します。

    [Image: [カスタム URL ドメインの追加] ウィンドウを示すスクリーンショット。]
6. **追加**を選択します。

### ステップ 3: 新規 Azure Front Door インスタンスを作成する

Azure Front Door を作成するには、こちらの手順に従います。

1. [Azure portal](https://portal.azure.com) にサインインします。
2. Azure Front Door サブスクリプションを含むテナントを選択します。上部メニューの **[設定]** アイコンを選択し、Azure Front Door サブスクリプションを含むテナントに切り替えます。
3. 次の設定を使用して、テナントの Front Door を作成するには、「 [Front Door プロファイルの作成 - 簡易作成](https://learn.microsoft.com/ja-jp/azure/frontdoor/create-front-door-portal#create-front-door-profile---quick-create) 」の手順に従います。 キャッシュと **WAF のポリシー**設定**は**空のままにします。

    | 鍵 | [値] |
    | --- | --- |
    | サブスクリプション | Azure サブスクリプションを選択します。 |
    | リソースグループ | 既存のリソース グループを選択するか、新しいものを作成します。 |
    | 名前 | プロファイルに `ciamazurefrontdoor` などの名前を付けます。 |
    | 階層 | Standard と Premium のいずれかのレベルを選択します。 Standard レベルは、コンテンツ配信に最適化されています。 Premium レベルは、Standard レベルをベースに、セキュリティにも重点が置かれています。 [階層の比較を](https://learn.microsoft.com/ja-jp/azure/frontdoor/standard-premium/tier-comparison)参照してください。 |
    | エンドポイント名 | グローバルに一意のエンドポイント名 (`ciamazurefrontdoor`など) を入力します。 **エンドポイント ホスト名**は自動的に生成されます。 |
    | 配信元の種類 | [`Custom`]を選択します。 |
    | 配信元のホスト名 | 「`<tenant-name>.ciamlogin.com`」と入力します。 `<tenant-name>` を自分のテナントの名前に置き換えます (例: `contoso.ciamlogin.com`)。 |
4. Azure Front Door リソースが作成されたら、[ **概要**] を選択し、後の手順で使用する **エンドポイントホスト名** をコピーします。 これは、`ciamazurefrontdoor-ab123e.z01.azurefd.net` のように表示されます。
5. 配信元の **ホスト名** と **配信元のホスト ヘッダー** の値が同じであることを確認します。

    1. [ **設定] で**、[ **配信元グループ**] を選択します。
    2. 一覧から配信元グループ ( **default-origin-group** など) を選択します。
    3. 右側のウィンドウで  など `contoso.ciamlogin.com` を選択します。
    4. [ **配信元の更新** ] ウィンドウで、 **ホスト名** と **配信元のホスト ヘッダー** を同じ値に更新します。

    [Image: ホスト名と配信元のホスト ヘッダー フィールドを示すスクリーンショット。]

### ステップ 4: Azure Front Door でカスタム URL ドメインを設定する

この手順では、 手順 1 で登録したカスタム URL ドメインを Azure Front Door に追加します。

#### 4.1. CNAME DNS レコードを作成する

カスタム URL ドメインを追加するには、ドメイン プロバイダーで正規名 (CNAME) レコードを作成します。 CNAME レコードは、ソース ドメイン名を宛先ドメイン名 (別名) にマップする DNS レコードの一種です。 Azure Front Door では、ソース ドメイン名はカスタム URL ドメイン名であり、宛先ドメイン名は手順 2 で構成した Front Door の既定のホスト名です。(例: `ciamazurefrontdoor-ab123e.z01.azurefd.net`)

作成した CNAME レコードが Front Door によって検証されると、ソース カスタム URL ドメイン (`login.contoso.com`など) 宛てのトラフィックは、指定された宛先の Front Door 既定フロントエンド ホスト (`contoso-frontend.azurefd.net`など) にルーティングされます。 詳細については、「 [Front Door にカスタム ドメインを追加する」を参照してください](https://learn.microsoft.com/ja-jp/azure/frontdoor/front-door-custom-domain)。

カスタム ドメインの CNAME レコードを作成するには:

1. カスタム ドメインのドメイン プロバイダーの Web サイトにサインインします。
2. プロバイダーのドキュメントを参照するか、**ドメイン名**、DNS、または**ネーム サーバー管理**というラベルの付いた Web サイトの領域を検索して、**DNS** レコードを管理するためのページを見つけます。
3. カスタム URL ドメインの CNAME レコード エントリを作成し、次の表に示すようにフィールドを入力します (フィールド名は異なる場合があります)。

    | Source | タイプ | 宛先 |
    | --- | --- | --- |
    | `<login.contoso.com>` | CNAME | `contoso-frontend.azurefd.net` |

    - ソース: カスタム URL ドメイン名 (例: login.contoso.com) を入力します。
    - 型: *CNAME* を入力します。
    - 宛先: 手順 2 で作成した既定の Front Door フロントエンド ホストを入力します。 名前は、 *&lt;ホスト名&gt;* .azurefd.net の形式である必要があります（例: `contoso-frontend.azurefd.net`）。
4. 変更内容を保存します。

#### 4.2. カスタム URL ドメインを Front Door と関連付けます。

1. Azure portal ホームで、Azure Front Door リソース `ciamazurefrontdoor` を検索して選択し、開きます。
2. 左側のメニューの **[設定]** で [ **ドメイン**] を選択します。
3. [ **ドメインの追加] を選択します**。
4. **[DNS 管理**] で、[**その他すべての DNS サービス**] を選択します。
5. **[カスタム ドメイン**] には、`login.contoso.com`などのカスタム ドメインを入力します。
6. その他の値は既定値のままにし、[ **追加**] を選択します。 入力したカスタム ドメインが一覧に追加されます。
7. 追加したドメインの **検証状態** で、[ **保留中]** を選択します。 TXT レコード情報を含むペインが開きます。

    1. カスタム ドメインのドメイン プロバイダーの Web サイトにサインインします。
    2. プロバイダーのドキュメントを参照するか、**ドメイン名**、DNS、または**ネーム サーバー管理**というラベルの付いた Web サイトの領域を検索して、**DNS** レコードを管理するためのページを見つけます。
    3. 新しい TXT DNS レコードを作成し、次のフィールドに入力します。

        - **名前**: `_dnsauth.contoso.com`のサブドメイン部分のみを入力します (例: `_dnsauth`
        - **型**: `TXT`
        - **値**: 次に例を示します。 `75abc123t48y2qrtsz2bvk......`

        TXT DNS レコードを追加すると、Front Door リソースの **検証状態** が最終的に **[保留中]** から **[承認済み]** に変わります。 外観の変化は、ページを更新しなければ反映されない場合があります。
8. Azure portal。 追加したドメインの **[エンドポイントの関連付け** ] で、[ **関連付け解除**] を選択します。
9. [ **エンドポイントの選択**] で、ドロップダウンからホスト名エンドポイントを選択します。
10. **「ルートの選択」リストで** **「既定のルート」** を選択し、次に **「関連付け」** を選択します。

#### 4.3. ルートを有効にする

**既定のルート**では、クライアントから Azure Front Door にトラフィックがルーティングされます。 次に、Azure Front Door は構成を使用して、トラフィックを外部テナントに送信する配信元を決定します。 既定のルートを有効にするには、これらの手順に従います。

1. **[Front Door マネージャー]** を選択します。
2. **既定のルート**を有効にするには、まず Front Door マネージャーのエンドポイントの一覧からエンドポイントを展開します。 次に、 **既定のルート**を選択します。
3. [ **有効なルート** ] チェック ボックスをオンにします。
4. [ **更新]** を選択して変更を保存します。

### カスタム URL メインのテスト

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. *外部*テナントを選択する: 上部メニューの **[設定]** アイコンを選択し、外部テナントに切り替えます。
3. **外部 ID** で、**ユーザー フロー** を選択します。
4. ユーザー フローを選択し、[ **ユーザー フローの実行**] を選択します。
5. **[アプリケーション]** で、以前に登録した *webapp1* という名前の Web アプリケーションを選択します。 **応答 URL** に`https://jwt.ms`が表示されます。
6. [ **ユーザー フロー エンドポイントの実行**] の下の URL をコピーします。

    [Image: ユーザー フローの実行オプションを示すスクリーンショット。]
7. カスタム ドメインを使用したサインインをシミュレートするには、Web ブラウザーを開き、コピーした URL を使用します。 ドメイン (*&lt;テナント名&gt;*.ciamlogin.com) を自分のカスタム ドメインに置き換えます。

    たとえば、次の表記の代わりに、

    ```http
    https://contoso.ciamlogin.com/contoso.onmicrosoft.com/oauth2/v2.0/authorize?p=B2C_1_susi&client_id=00001111-aaaa-2222-bbbb-3333cccc4444&nonce=defaultNonce&redirect_uri=https%3A%2F%2Fjwt.ms&scope=openid&response_type=id_token&prompt=login
    ```

    を使う代わりに、

    ```http
    https://login.contoso.com/contoso.onmicrosoft.com/oauth2/v2.0/authorize?p=B2C_1_susi&client_id=00001111-aaaa-2222-bbbb-3333cccc4444&nonce=defaultNonce&redirect_uri=https%3A%2F%2Fjwt.ms&scope=openid&response_type=id_token&prompt=login
    ```
8. サインイン ページが正しく読み込まれることを確認します。 次に、ローカル アカウントでサインインします。

### アプリケーションの構成

カスタム URL ドメインを構成してテストしたら、既定のドメインではなくカスタム URL ドメインをホスト名とする URL を読み込むように、アプリケーションを更新します。

カスタム URL ドメイン統合は、外部 ID をユーザー フローを使用してユーザーを認証する認証エンドポイントに適用されます。 これらのエンドポイントの形式は次のとおりです。

- `https://<custom-url-domain>/<tenant-name>/v2.0/.well-known/openid-configuration`
- `https://<custom-url-domain>/<tenant-name>/oauth2/v2.0/authorize`
- `https://<custom-url-domain>/<tenant-name>/oauth2/v2.0/token`

置換前のコード:

- **custom-url-domain** を自分のカスタム URL ドメインに置換
- 「**tenant-name**」をあなたのテナント名またはテナント ID に置き換えてください。

SAML サービス プロバイダーのメタデータは、次の例のようになります:

```html
https://custom-url-domain-name/tenant-name/Samlp/metadata
```

#### (省略可) テナント ID を使用する

URL 内の外部テナント名を自分のテナント ID GUID に置き換えて、URL 内の "onmicrosoft.com" へのすべての参照が削除されるようにできます。 テナント ID GUID は、Azure portal または Microsoft Entra 管理センターの **[概要** ] ページで確認できます。 たとえば、`https://account.contosobank.co.uk/contosobank.onmicrosoft.com/` を `https://account.contosobank.co.uk/<tenant-ID-GUID>/` に変更します。

テナント名の代わりにテナント ID を使用する場合は、それに応じて ID プロバイダー **の OAuth リダイレクト URI を** 更新してください。 テナント名の代わりにテナント ID を使用する場合、有効な OAuth リダイレクト URI は、次の例のようになります。

```html
https://login.contoso.com/00001111-aaaa-2222-bbbb-3333cccc4444/oauth2/authresp 
```

### (省略可能) Azure Front Door の高度な構成

Azure Front Door の高度な構成 (Azure Web Application Firewall (WAF) など) を使用できます。 Azure WAF では、一般的な悪用や脆弱性からの Web アプリケーションの一元的な保護が提供されます。

カスタム ドメインを使用するときは、次の点を考慮してください。

- WAF ポリシーは、Azure Front Door プロファイルと同じレベルに配置する必要があります。 Azure Front Door で使用する WAF ポリシーを作成する方法の詳細については、「 [WAF ポリシーの構成](https://learn.microsoft.com/ja-jp/azure/frontdoor/how-to-configure-endpoints)」を参照してください。
- WAF で管理される規則の機能は、擬陽性を引き起こし、正当な要求の通過を妨げる可能性があるため、公式にはサポートされていません。したがって、WAF カスタム規則を使用するのは、必要な場合のみにしてください。

### (省略可能)既定のドメインをブロックする

カスタム URL ドメインを構成した後も、ユーザーは .ciamlogin.comテナント名 既定のドメイン名にアクセスできます。 攻撃者が既定のドメインを使用してアプリにアクセスしたり、分散型サービス拒否 (DDoS) 攻撃を実行したりできないように、既定のドメインへのアクセスをブロックする必要があります。 サポート チケットを送信して、既定のドメインへのアクセスのブロックを要求します。

注意

既定のドメインをブロックすることを要求する前に、カスタム ドメインが正しく動作することを確認してください。 既定のドメインがブロックされると、特定の機能は機能しなくなります。 [「既定のドメインのブロック」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-custom-url-domain#blocking-the-default-domain)参照してください。

### トラブルシューティング

- **「ページが見つかりません」メッセージ。** カスタム URL ドメインでサインインしようとすると、HTTP 404 エラー メッセージが表示されます。 この問題は、DNS 構成または Azure Front Door バックエンド構成に関連している可能性があります。 次の手順を試してみてください。

    - カスタム URL ドメインが自分のテナントに登録されていて正常に検証されていることを確認します。
    - [カスタム ドメイン](https://learn.microsoft.com/ja-jp/azure/frontdoor/front-door-custom-domain)が正しく構成されていることを確認します。 カスタム ドメインの `CNAME` レコードは、Azure Front Door の既定のフロントエンド ホスト (例: contoso-frontend.azurefd.net) をポイントしていなければなりません。
- **Our services aren't available right now (現在、サービスは利用できません) メッセージ** カスタム URL ドメインでサインインしようとすると、次のエラー メッセージが *表示されます。すべてのサービスをできるだけ早く復元できるように取り組んでいます。しばらくお待ちください。* この問題は、Azure Front Door ルートの構成に関連している可能性があります。 **既定のルート**の状態を確認します。 無効になっている場合は、 ルートを有効にします。
- **リソースが削除されたか、名前が変更されたか、または一時的に利用できません。** カスタム URL ドメインでサインインしようとすると、 *"お探しのリソースは削除されたか、名前が変更されたか、または一時的に利用できません"*というエラー メッセージが表示されます。 この問題は、Microsoft Entra カスタム ドメインの検証に関連している可能性があります。 カスタム ドメインが登録され、テナントで **正常に検証されていることを** 確認します。
- **エラー コード 399265: RoutingFromInvalidHost。** このエラー コードは、テナントが、検証されていないドメインから要求を行おうとしたときに表示されます。 DNS レコードに TXT レコードの詳細を必ず追加してください。 次 [に、カスタム ドメイン名をもう一度確認します](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain#verify-your-custom-domain-name) 。
- **エラー コード 399280: InvalidCustomUrlDomain。** このエラー コードは、テナントが、カスタム URL ドメインではないドメインから要求を行おうとしたときに表示されます。 カスタム ドメイン名をカスタム URL ドメインに関連付けます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-customize-branding-customers"} -->
## 顧客向けにブランドをカスタマイズする - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers
- Service: entra-external-id / external
- Article date: 2025-09-16
- Summary: 顧客のサインイン エクスペリエンスの外観をカスタマイズする方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Note

この記事で説明するブランド化のカスタマイズは、ユーザーがMicrosoftホスト型サインイン ページを使用してサインインする**ブラウザー委任認証**に適用されます。 [ネイティブ認証](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach)を使用する場合は、サインイン UI を所有し、アプリケーション コードでブランド化を直接管理します。

新しい外部テナントを作成したら、エンド ユーザー エクスペリエンスをカスタマイズできます。 テナントの **[会社のブランド化]** 設定を構成して、アプリにサインインしているユーザーのカスタム外観を作成します。 これらの設定を使用すると、独自の背景画像、色、会社のロゴ、テキストを追加して、アプリ全体のサインイン エクスペリエンスをカスタマイズできます。 Company Branding Graph API を使用して、プログラムでユーザー フローを作成することもできます。

### 前提 条件

- 独自の Microsoft Entra 外部テナントをまだ作成していない場合は、今すぐ作成します。
- [アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)を登録します。
- [ユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) を作成する
- 追加する各イメージのファイル サイズ要件を確認します。 適切なサイズの画像を作成するには、フォト エディターを使用する必要がある場合があります。 すべての画像に推奨される画像の種類は PNG ですが、JPG は受け入れられます。

### ブランド化要素

既定では、Microsoft は、会社の特定の要件に合わせてカスタマイズできる、テナントに中立的なブランドを提供します。 この既定のブランドには、既存の Microsoft ブランドは含まれません。 カスタム企業ブランドの読み込みに失敗した場合、サインイン ページはこのニュートラル ブランドに自動的に切り替わります。 さらに、各カスタム ブランド化プロパティをカスタム サインイン ページに手動で追加できます。

このニュートラル ブランドは、カスタムの背景画像または色、favicon、レイアウト、ヘッダー、フッターを使用してカスタマイズできます。 サインイン フォームをカスタマイズしたり、カスタム テキストをさまざまなインスタンスに追加したり、カスタム CSSアップロードしたりすることもできます。 次の図は、テナントのニュートラルな既定のブランドを示しています。 番号付きブランド化要素とそれに対応する説明は、画像の後で確認できます。

[Image: ニュートラル ブランドのスクリーンショット。]

1. ニュートラル背景。
2. Favicon（ファビコン）。
3. バナー ロゴ。
4. ページ レイアウト要素としてのフッター。
5. プライバシーと Cookie、使用条件などのフッター ハイパーリンク。

### 既定のサインイン エクスペリエンスをカスタマイズする方法

設定をカスタマイズする前に、ニュートラルな既定のブランドがサインイン、サインアップ、サインアウトの各ページに表示されます。 カスタムの背景画像または色、favicon、レイアウト、ヘッダー、フッターを使用して、この既定のエクスペリエンスをカスタマイズできます。 [カスタム CSS](https://learn.microsoft.com/ja-jp/entra/fundamentals/reference-company-branding-css-template)をアップロードすることもできます。

1. 少なくとも [組織ブランド管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#organizational-branding-administrator) にサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニュー [設定] アイコンを使用して、[**ディレクトリとサブスクリプション]** メニューから先ほど作成した外部テナントに切り替えます。
3. 検索バーを使用するか、**Entra ID**&gt; に移動して、**会社**のブランドを参照します。
4. **[既定のサインイン]** タブで、**[編集]** を選択します。

    [Image: 会社のブランド編集ボタンのスクリーンショット。]

#### サインイン ページの背景とレイアウトをカスタマイズするには

1. [**基本**] タブで、背景要素のいずれかを変更します。

    - **Favicon** – Web ブラウザー タブに表示されるアイコン。
    - **背景画像** – サインイン ページに表示される大きな画像。 画像をアップロードすると、ブラウザー ウィンドウ全体に合わせて拡大縮小とトリミングが行われます。
    - **ページの背景色** – 接続の待機時間などで、イメージを読み込めなかったときに背景イメージを置き換える色。

    [Image: 会社のブランド化の [基本] タブのスクリーンショット。]
2. カスタマイズを続行したい場合は **[次へ: レイアウト]** を選択し、変更を保存したい場合は **[確認と保存**] を選択します。
3. [**レイアウト**] タブで、サインイン ページ上の Web ページ要素の配置を選択します。

    - **テンプレート** - 背景に全画面表示と部分的な画面のどちらを表示するかを選択します。
    - **ヘッダー** – ヘッダーを表示または非表示にします。
    - **フッター** – フッターを表示または非表示にします。
    - **カスタム CSS** – 独自の CSS ファイルをアップロードして、既定の Microsoft のスタイルを独自のスタイルに置き換えます。色、フォント、テキスト サイズ、要素の位置、さまざまなデバイスと画面サイズの表示。

    [Image: 会社のブランド 化レイアウト タブのスクリーンショット。]
4. **次へを選択: カスタマイズを続行する場合はヘッダー** を選択します。または、変更を保存する場合は **[確認と保存]** を選択します。

#### ロゴ、プライバシー リンク、使用条件をカスタマイズするには

1. [**ヘッダー**] タブで、サインイン ページのヘッダーに表示するロゴを選択します。

    [Image: 会社のブランド化ヘッダー タブのスクリーンショット。]
2. カスタマイズを続行する場合は **[次へ: フッター]** を選択し、変更を保存する場合は **[確認と保存]** を選択します。
3. [**フッター**] タブでは、サインイン ページのフッターに表示されるプライバシーと使用条件のハイパーリンクの URL とリンク テキストをカスタマイズできます。

    - **プライバシー & クッキー** – このハイパーリンクをフッターに表示するには、[プライバシー & クッキー] の横にあるチェックボックスをオンにします。 独自のハイパーリンクの表示テキストと URL を入力しない限り、Microsoft の既定のプライバシー リンクが表示されます。
    - **使用条件** – フッターにこのハイパーリンクを表示するには、[使用条件] の横にあるチェック ボックスをオンにします。 独自のハイパーリンクの表示テキストと URL を入力しない限り、Microsoft の利用規約リンクが表示されます。

    [Image: 会社のブランド フッター タブのスクリーンショット。]
4. カスタマイズを続行する場合は **[次へ: サインイン フォーム]** を選択し、変更を保存する場合は **[確認と保存]** を選択します。

#### サインイン フォームをカスタマイズするには

1. [**サインイン フォーム**] タブで、サインイン フォームの要素を構成します。

    - **バナー ロゴ** – サインイン ページとユーザーのアクセス パネルに表示されます。
    - **Square ロゴ (ライト テーマ)** – 組織内のユーザー アカウントを表します。
    - **Square ロゴ (ダークテーマ)** – 明るいテーマの四角形ロゴが暗い背景で見えにくい場合には、暗い背景で使用する別のロゴをアップロードできます。

    [Image: 会社のブランド化サインイン フォーム タブのスクリーンショット。]
2. ページの下半分までスクロールし、サインイン フォームのその他の要素を構成します。

    - **ユーザー名ヒント テキスト** – サインイン ページのユーザー名入力フィールドに表示されるヒント テキストです (ゲスト ユーザーがアプリにサインインする場合は推奨されません)。
    - **サインイン ページのテキスト** – サインイン ページとサインアップ ページの下部に表示されます。 ガイドライン：

        - 最大 1,024 文字
        - 機密情報を含めない
        - テキストの書式を設定するには、次の構文を使用します。
            - ハイパーリンク: `[text](https://learn.microsoft.com/ja-jp/entra/external-id/customers/link)`
            - 太字: `**text** or __text__`
            - 斜体: `*text* or _text_`
            - 下線: `++text++`

#### セルフサービス パスワード リセットをカスタマイズするには

1. [**セルフサービス パスワード リセット** セクションまでスクロールして、サインイン ページでセルフサービス パスワード リセット リンクを表示、非表示、またはカスタマイズするためのオプションを構成します。

    - **セルフサービスパスワードリセット** を表示する - セルフサービスパスワードリンクを表示するには、このチェックボックスをオンにします。
    - **共通 URL** – 既定の Microsoft リンクの代わりに使用するパスワード リセット URL を入力します。
    - **アカウント コレクションの表示テキスト** – Microsoft の既定のテキスト "アカウントにアクセスできません" テキストの代わりに表示するリンク テキストを入力します。
    - **パスワード コレクションの表示テキスト** – Microsoft の既定の "パスワードを忘れた場合" テキストの代わりに表示するリンク テキストを入力します。

    [Image: セルフサービス パスワード リセットをブランド化した会社のスクリーンショット。]
2. カスタマイズを続行する場合は **[次へ: テキスト]** を選択し、変更を保存する場合は **[確認と保存]** を選択します。

#### ユーザー属性をカスタマイズするには

テナントの場合、サインアップとサインイン時に収集する情報の要件が異なる場合があります。 テナントには、特定の名前、姓、市区町村、郵便番号などの属性に格納されている情報の組み込みセットが付属しています。 テナントでカスタム属性を作成するには、Microsoft Graph API か、ポータルの **[会社のブランド化]** の **[テキスト]** タブを使用します。

1. [**テキスト**] タブで、[**カスタム テキスト**追加] を選択します。
2. 次のいずれかのオプションを選択します。

    - 既定値をオーバーライドするには、属性 **を選択してください** 。
    - [属性コレクション  を選択して、サインアップ プロセス中に収集する新しい属性オプションを追加します。
    - **[サインイン]** を選択して、サインイン ページのカスタム テキストを追加します。
    - **サインアップ** を選択して、サインインページにカスタムテキストを追加します。
    - **[サインイン/アップのワンタイム コード (SISU OTC)]** を選択して、カスタム タイトルを追加します。

    [Image: 会社のブランド化テキスト タブのスクリーンショット。]
3. 「**」を選択します。次へ: 「**」を確認して、変更をすべて見直します。 続いて、変更を保存する場合は **[保存]** を、カスタマイズを続行する場合は **[前へ]** を選択します。

重要

外部テナントには、サインアップエクスペリエンスとサインイン エクスペリエンスにカスタム テキストを追加する 2 つのオプションがあります。 この機能は、言語のカスタマイズ中に各ユーザー フローで使用でき、会社のブランド化でも使用できます。 文字列をカスタマイズする方法は 2 つありますが (会社のブランド化とユーザー フローを使用して)、どちらの方法でも同じ JSON ファイルが変更されます。 ユーザー フローまたは会社のブランドを使用して行われた最新の変更は、常に前の変更をオーバーライドします。

### サインアウト エクスペリエンスをカスタマイズする

外部テナントのサインアウト エクスペリエンスをカスタマイズする必要はありません。 サインイン エクスペリエンスのブランドをカスタマイズした場合、サインアウト エクスペリエンスは自動的にサインイン エクスペリエンスと一致します。 サインイン エクスペリエンスをカスタマイズしていない場合、サインアウト エクスペリエンスは外部テナントの既定のニュートラル ブランドと一致します。

### テナント名をカスタマイズする方法

Microsoft Entra 管理センターでテナント名をカスタマイズして、ニュートラルな既定のサインイン エクスペリエンスの Microsoft バナー ロゴを置き換えることができます。 新しいテナント名は、ユーザーに送信された確認メールにも表示されます。

[Image: テナント名のスクリーンショット。]

1. 少なくとも [組織ブランド管理者](https://entra.microsoft.com/)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#organizational-branding-administrator) にサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニュー [設定] アイコンを使用して、[**ディレクトリとサブスクリプション]** メニューから先ほど作成した外部テナントに切り替えます。
3. 検索バーに**テナントのプロパティ**と入力して選択します。
4. **[名前]** フィールドを編集します。

    [Image: テナント名の編集のスクリーンショット。]
5. **保存**を選択します。

### Microsoft Graph API を使用してブランドをカスタマイズする

Microsoft Graph API を使用して、いくつかの項目をプログラムでカスタマイズできます。 たとえば、API を使用してカスタム背景画像をアップロードしたり、サインイン ページの色を変更したり、カスタム ロゴを追加したりできます。 詳細については、[の既定のブランド](https://learn.microsoft.com/ja-jp/graph/api/organizationalbranding-update) の更新に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-customize-languages-customers"} -->
## ブラウザーの言語をカスタマイズする - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-languages-customers
- Service: entra-external-id / external
- Article date: 2026-04-24
- Summary: アプリの認証エクスペリエンスのブラウザー言語をカスタマイズして、パーソナライズされたサインインを提供する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ヒント

この記事は、外部テナントのユーザー フローに当てはまります。 ワークフォース テナントの情報については、「[Microsoft Entra External IDでの言語カスタマイズ」を](https://learn.microsoft.com/ja-jp/entra/external-id/user-flow-customize-language)参照してください。

この記事では、アプリの認証エクスペリエンスに合わせてブラウザー言語をカスタマイズする方法について説明します。 ブラウザーの言語に基づいてサインイン プロセスをカスタマイズすることで、ユーザー向けにカスタマイズされたエクスペリエンスを提供し、既定のブランド設定をオーバーライドできます。

### 前提条件

- まだ独自の Microsoft Entra 外部テナントを作成していない場合は、ここで作成してください。
- [アプリケーションを登録します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。
- [ユーザー フローを作成します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)。
- 追加する各イメージのファイル サイズ要件を確認します。 写真エディターを使用して、適切なサイズの画像を作成することが必要な場合があります。 すべての画像に推奨される画像タイプは PNG ですが、JPG も使用できます。

### [会社のブランド化] の下にブラウザー言語を追加する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[組織のブランド管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#organizational-branding-administrator)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから前に作成した外部テナントに切り替えます。
3. 会社の&gt;&gt;に移動します。

    [Image: ブラウザー言語のカスタマイズと [ブラウザー言語の追加] アクションを使用した会社のブランド化のスクリーンショット。]
4. [ **基本** ] タブの [ **言語固有の UI カスタマイズ**] で、カスタマイズするブラウザー言語をメニューから選択します。

    [Image: ブラウザーの言語カスタマイズの [基本] タブの言語セレクターのスクリーンショット。]

外部テナントでは、次の言語がサポートされています。

- アラビア語 (サウジアラビア)
- バスク語 (バスク)
- ブルガリア語 (ブルガリア)
- カタルニア語 (カタルニア)
- 中国語 (中国)
- 中国語 (香港特別行政区)
- クロアチア語 (クロアチア)
- チェコ語 (チェコ)
- デンマーク語 (デンマーク)
- オランダ語 (オランダ)
- 英語 (米国)
- エストニア語 (エストニア)
- フィンランド語 (フィンランド)
- フランス語 (フランス)
- ガリシア語 (ガリシア)
- ドイツ語 (ドイツ)
- ギリシャ語 (ギリシャ)
- ヘブライ語 (イスラエル)
- ハンガリー語 (ハンガリー)
- イタリア語 (イタリア)
- 日本語 (日本)
- カザフ語 (カザフスタン)
- 韓国語 (韓国)
- ラトビア語 (ラトビア)
- リトアニア語 (リトアニア)
- ノルウェー語 (ブークモール) (ノルウェー)
- ポーランド語 (ポーランド)
- ポルトガル語 (ブラジル)
- ポルトガル語 (ポルトガル)
- ルーマニア語 (ルーマニア)
- ロシア語 (ロシア)
- セルビア語 (ラテン、セルビア)
- スロバキア語 (スロバキア)
- スロベニア語 (スロベニア)
- スペイン語 (スペイン)
- スウェーデン語 (スウェーデン)
- タイ語 (タイ)
- トルコ語 (Türkiye)
- ウクライナ語 (ウクライナ)

1. **[基本**]、[**レイアウト]**、[**ヘッダー**]、[**フッター**]、[**サインイン フォーム**]、[**テキスト]** タブの各要素をカスタマイズします。 詳細な手順については、「 [ブランド化とエンドユーザー エクスペリエンスのカスタマイズ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers)」を参照してください。
2. 完了したら、[ **校閲** ] タブを選択し、すべての言語のカスタマイズに移動します。 次に、[ **追加]** を選択して変更を保存するか、[ **前へ** ] を選択して編集を続行します。

### ユーザー フローに言語のカスタマイズを追加する

外部テナントでの言語のカスタマイズにより、ユーザー フローは顧客のニーズに合わせてさまざまな言語に対応できます。 言語を使用して、サインアップ中に属性コレクション プロセスの一部として顧客に表示される文字列を変更できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[組織のブランド管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#organizational-branding-administrator)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから前に作成した外部テナントに切り替えます。
3. **[Entra ID]**&gt;**[外部 ID]**&gt;**[ユーザー フロー]** に移動します。
4. 翻訳を有効にするユーザー フローを選択します。
5. [ **言語] を選択します**。
6. ユーザー フロー **の [言語** ] ページで、カスタマイズする言語を選択します。
7. **「サインアップとサインイン」を展開します**。
8. [ **既定値のダウンロード** ] を選択します (以前にこの言語を編集した場合は、オーバーライドを **ダウンロード** します)。

    [Image: ユーザー フローの下に言語を追加する方法を示すスクリーンショット。]

ダウンロードされたファイルは JSON 形式であり、組み込み属性とカスタム属性の両方、および他のページ レベルおよびエラー文字列が含まれます。

```http
{"AttributeCollection_Description": "Wir benötigen nur ein paar weitere Informationen, um Ihr Konto einzurichten.","AttributeCollection_Title": "Details hinzufügen","Attribute_City": "Ort","Attribute_Country": "Land/Region","Attribute_DisplayName": "Anzeigename","Attribute_Email": "E-Mail-Adresse","Attribute_Generic_ConfirmationLabel": "{0} erneut eingeben","Attribute_GivenName": "Vorname","Attribute_JobTitle": "Position","Attribute_Password": "Kennwort","Attribute_Password_MismatchErrorString": "Kennwörter stimmen nicht überein.","Attribute_PostalCode": "Postleitzahl","Attribute_State": "Bundesland/Kanton","Attribute_StreetAddress": "Straße","Attribute_Surname": "Nachname","SignIn_Description": "Melden Sie sich an, um auf {0} zuzugreifen.","SignIn_Title": "Anmelden","SignUp_Description": "Registrieren Sie sich, um auf {0} zuzugreifen.","SignUp_Title": "Konto erstellen","SisuOtc_Title": "Code eingeben","Attribute_extension_a235ca9a0a7c4d33bd69e07bed81c8b1_Shoesize": "Shoe size"
}  
```

ダウンロードしたファイル内のこれらの属性の一部またはすべてを変更できます。 たとえば、組み込みの属性 **City とカスタム** 属性 **の Shoesize** を変更できます。

```http
{"AttributeCollection_Description": "Wir benötigen nur ein paar weitere Informationen, um Ihr Konto einzurichten.","AttributeCollection_Title": "Details hinzufügen","Attribute_City": "Ort2","Attribute_Country": "Land/Region","Attribute_DisplayName": "Anzeigename","Attribute_Email": "E-Mail-Adresse","Attribute_Generic_ConfirmationLabel": "{0} erneut eingeben","Attribute_GivenName": "Vorname","Attribute_JobTitle": "Position","Attribute_Password": "Kennwort","Attribute_Password_MismatchErrorString": "Kennwörter stimmen nicht überein.","Attribute_PostalCode": "Postleitzahl","Attribute_State": "Bundesland/Kanton","Attribute_StreetAddress": "Straße","Attribute_Surname": "Nachname","SignIn_Description": "Melden Sie sich an, um auf {0} zuzugreifen.","SignIn_Title": "Anmelden","SignUp_Description": "Registrieren Sie sich, um auf {0} zuzugreifen.","SignUp_Title": "Konto erstellen","SisuOtc_Title": "Code eingeben","Attribute_extension_a235ca9a0a7c4d33bd69e07bed81c8b1_Shoesize": "Schuhgröße"
}  
```

1. 必要な変更を加えたら、新しいオーバーライド ファイルをアップロードできます。 変更がユーザー フローに自動的に保存されます。 オーバーライドが [ **構成済み** ] タブに表示されます。
2. 変更を再確認するには、[ **構成済み** ] タブで言語を選択し、[ **サインアップとサインイン** ] オプションを展開します。 カスタマイズした言語ファイルを表示するには、[ **オーバーライドのダウンロード**] を選択します。 カスタマイズしたオーバーライド ファイルを削除するには、[ **オーバーライドの削除**] を選択します。

[Image: 変更された JSON ファイルを削除またはダウンロードする方法を示すスクリーンショット。]

1. 外部テナントのサインイン ページに移動します。 URL に適切なロケールと市場があることを確認します (例: `ui_locales=de-DE` と `mkt=de-DE`)。 サインアップ ページに更新された属性が次のように表示されます。

[Image: 変更されたサインアップ ページ属性のスクリーンショット。]

重要

外部テナントには、サインアップとサインインのエクスペリエンスにカスタム テキストを追加するための 2 つのオプションがあります。 この関数は、言語のカスタマイズ中と [会社のブランド化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers)の下で、各ユーザー フローで使用できます。 (会社のブランド化とユーザー フローを使用して) 文字列をカスタマイズする方法は 2 つありますが、どちらの方法でも同じ JSON ファイルが変更されます。 ユーザー フローまたは会社のブランドを使用して行われた最新の変更は、常に前の変更よりも優先されます。

### 右から左へ記述する言語のサポート

アラビア語やヘブライ語など、右から左に読む言語は、左から右に読む言語と比較して反対方向に表示されます。 外部テナントは、データの入力および表示のために、右から左の環境で動作する言語とその機能をサポートしています。 右から左に読むユーザーは、自然な読み取り方法で対話できます。

[Image: 右から左への言語のサポートを示すスクリーンショット。]

### ブラウザー言語のカスタマイズを削除する

不要になったら、管理センターでまたは Microsoft Graph API を使用して外部テナントから言語のカスタマイズを削除できます。

#### 管理センターで言語のカスタマイズを削除する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. &gt;
3. 削除する言語を選択し、[ **削除** ] と **[OK]** を選択します。

    [Image: ブラウザーの言語のカスタマイズ タブと [削除] ボタンのスクリーンショット。]

#### Microsoft Graph API を使用して言語のカスタマイズを削除する

1. 外部テナント アカウント () を使用して `https://developer.microsoft.com/en-us/graph/graph-explorer?tenant=<your-tenant-name.onmicrosoft.com>`にサインインします。
2. Microsoft Graph API を使用して、既定のブランド化オブジェクトのクエリを実行します: `https://graph.microsoft.com/v1.0/organization/<your-tenant-ID>/branding/localizations`。 外部テナントにサインインしていることを確認するには、画面の右側にあるテナント名を確認します。
3. [ローカライズされたブランド化オブジェクトを削除します](https://learn.microsoft.com/ja-jp/graph/api/organizationalbrandinglocalization-delete)。

    [Image: CIAM テナントがログインしている MS Graph API のスクリーンショット。]
4. 変更が有効になるまで、数分待ちます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-define-custom-attributes"} -->
## カスタム属性を定義する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes
- Service: entra-external-id / external
- Article date: 2026-03-27
- Summary: サインアップとサインイン時にユーザーから収集される新しいカスタム属性を作成および定義する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ヒント

この記事は、外部テナントのユーザー フローに当てはまります。 従業員テナントの詳細については、「 [B2B コラボレーションのサインアップ時にカスタム ユーザー属性を収集](https://learn.microsoft.com/ja-jp/entra/external-id/user-flow-add-custom-attributes)する」を参照してください。

Note

この記事では、ユーザー フローを使用 **したブラウザー委任認証** の属性コレクションについて説明します。 **ネイティブ認証**を使用する場合は、[ネイティブ認証ユーザー属性ビルダー](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-user-attribute-builder)を使用して MSAL SDK を使用して属性を収集します。 方法の選択については、「 [認証方法の選択」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach)参照してください。

アプリで、組み込みのユーザー属性よりも多くの情報が必要な場合は、独自の属性を追加できます。 これらの属性は、 *カスタム ユーザー属性と*呼ばれます。

カスタム ユーザー属性を定義するには、まずテナント レベルで属性を作成し、テナント内の任意のユーザー フローでその属性を使用できるようにします。 次に、その属性をサインアップ ユーザー フローに割り当てて、サインアップ ページに属性を表示する方法を構成します。

カスタム ユーザー属性の詳細については、 [ユーザー プロファイル属性](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes) に関する記事を参照してください。

### カスタム ユーザー属性の作成

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 複数のテナントにアクセスできる場合、上部のメニューの **[設定]** アイコン  を使用して、**[ディレクトリとサブスクリプション]** メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**外部アイデンティティ**&gt;**概要** に移動します。
4. **[カスタムのユーザー属性]** を選択します。 一覧には、作成されたカスタム ユーザー属性を含む、テナントで使用できるすべてのユーザー属性が含まれます。 **[属性の種類]** 列は、属性が組み込み属性またはカスタム属性のどちらであるかを示します。
5. **[追加]** を選択します。 **[属性の追加]** ペインで、カスタム属性の**名前** (例: "使用条件") を入力します。
6. **[データ型]**で、作成する**データ型とユーザー入力コントロールの種類**に応じて、**[文字列]**、**[ブール値]**、または [\[整数\]](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes#custom-user-attributes-input-types) を選択します。 **[文字列]** 属性の既定のユーザー入力の種類は **TextBox** 値ですが、後の手順でこれを変更できます (たとえば、ラジオ ボタンや複数選択チェック ボックスを構成する場合)。
7. (省略可能) **[説明]**には、内部使用のためのカスタム属性の説明を入力します。 この説明は、ユーザーには表示されません。

    [Image: 属性を追加するウィンドウのスクリーンショット。]
8. **［作成］** を選択します これで、このカスタム属性をユーザー属性の一覧で使用したり、ユーザー フローに追加したりできるようになります。

### サインアップ フローにカスタム ユーザー属性を含める

既に作成したユーザー フローにカスタム ユーザー属性を追加するには、次の手順に従います。 (新しいユーザー フローの作成方法については、「[顧客向けのサインアップとサインインのユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)」を参照してください。)

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 複数のテナントにアクセスできる場合、上部のメニューの **[設定]** アイコン  を使用して、**[ディレクトリとサブスクリプション]** メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**外部アイデンティティ**&gt;**ユーザーフロー**を参照します。
4. 一覧からユーザー フローを選択します。
5. **[ユーザー属性]** を選択します。 前のセクションで説明したように、この一覧には定義したカスタム ユーザー属性が含まれています。 たとえば、新しい **[使用条件]** 属性がリストに表示されます。 サインアップ時にユーザーから収集するすべての属性を選択します。

    [Image: [ユーザー フローを作成する] ページのユーザー属性オプションのスクリーンショット。]
6. **[保存]** を選択します。

#### ユーザー入力の種類とページ レイアウトを構成する

**[ページ レイアウト]** ページでは、必要な属性を指定したり、表示の順序を変更したりできます。 属性ラベルを編集したり、ラジオ ボタンやチェック ボックスを作成したり、その他のコンテンツ (使用条件やプライバシー ポリシーなど) へのハイパーリンクを追加したりすることもできます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**外部アイデンティティ**&gt;**ユーザーフロー**を参照します。
3. 一覧から、ユーザー フローを選択します。
4. **[カスタマイズ]** で **[ページ レイアウト]** を選択します。 収集することを選択した属性が表示されます。
5. **[ラベル]** 列の値を選択し、テキストを変更して、属性の ラベルを編集します。
6. チェックボックスまたはラジオ ボタンを構成します。

    - **単一選択チェックボックス**: ブール属性の種類は、サインアップ ページで単一選択チェック ボックスとして表示されます。 チェック ボックスの横に表示するテキストを構成するには、 **[ラベル]** 列で値を選択して編集します。 Markdown 言語を使用してハイパーリンクを追加します。 詳細については、「単一選択チェックボックスを構成するには (CheckboxSingleSelect)」 を参照してください。
    - **複数選択チェックボックス**: 構成する**文字列**データ型属性を検索し、**[ユーザー入力の種類]** 列の値を選択してエディター ペインを開きます。 **[CheckboxMultiSelect]** ユーザー入力の種類を選択し、値を入力します。 詳細については、「複数選択チェックボックスを構成するには (CheckboxMultiSelect)」を参照してください。
    - **ラジオ ボタン**: 構成する**文字列**データ型属性を探し、**[ユーザー入力の種類]** 列の値を選択してエディタ ペインを開きます。 **[RadioSingleSelect]** ユーザー入力の種類を選択し、値を入力します。 詳細については、「ラジオ ボタン (RadioSingleSelect) を構成するには」を参照してください
7. 表示順序を変更するには、属性を選択した後、**[上に移動]**、**[下に移動]**、**[先頭へ移動]**、または **[一番下へ移動]** を選択します。
8. [必須] 列の チェック ボックスを選択して、必要な属性を作成します。 すべての属性を必須としてマークできます。 複数選択チェックボックスの場合、"必須" は、ユーザーが少なくとも 1 つのチェックボックスを選択する必要があることを意味します。
9. すべての変更が完了したら、**[保存]** を選択します。

#### 単一選択チェック ボックス (CheckboxSingleSelect) を構成する

ブール型のデータ型を持つ属性には、CheckboxSingleSelect のユーザー入力型があります。 チェックボックスの横に表示されるテキストを変更し、ハイパーリンクを含めることができます。

単一選択チェック ボックスを構成するには、次の手順に従います。

1. **[ページ レイアウト]** ページで、構成する**ブール値**のデータ型を持つ属性を見つけます。
2. **[ラベル]** 列で値を選択し、チェック ボックスの横に表示するテキストを入力します。 Markdown 言語を使用してハイパーリンクを追加します。 次に例を示します。

    - **利用規約**属性のラベルを構成するには、次のように入力します。

        `I have read and agree to the [terms of use](https://woodgrove.com/terms-of-use)`
    - または、利用規約とプライバシー ポリシーを 1 つの必須チェックボックスに組み合わせることもできます。

        `I have read and agree to the [terms of use](https://woodgrove.com/terms-of-use) and the [privacy policy](https://woodgrove.com/privacy)`
3. **[OK]** を選択します。

    [Image: ページ レイアウト オプションのチェック ボックス ラベルを更新するスクリーンショット。]
4. **[ページ レイアウト]** ページで、**[保存]** を選択します。

#### 複数選択チェック ボックス (CheckboxMultiSelect) を構成する

文字列データ型を持つ属性は、属性ラベルの下に表示される一連の 1 つ以上のチェック ボックスである CheckboxMultiSelect ユーザー入力の種類として構成できます。 ユーザーは 1 つ以上のチェック ボックスを選択できます。 個々のチェック ボックスのテキストを定義し、他のコンテンツへのハイパーリンクを含めることができます。 この属性を "必須" にすると、ユーザーは少なくともチェックボックスを 1 つ選択する必要があります。

1. **[ページ レイアウト]** ページで、一連のチェック ボックスとして構成する**文字列**データ型を持つ属性を見つけます。
2. **[ラベル]** 列で値を選択して、一連のチェック ボックスの上に表示する見出しを入力します (例: `How did you hear about us?`)。
3. **[ユーザー入力の種類]** 列で値を選択して、エディター ペインを開きます。
4. エディター ペインの **[ユーザー入力の種類]** で、**[CheckboxMultiSelect]** を選択します。
5. 追加するチェック ボックスごとに、新しい行で始めて次の情報を入力します。

    - **[テキスト]** で、チェック ボックスの横に表示するテキストを入力します。 Markdown 言語を使用してハイパーリンクを追加します。
    - **[値]**で、ユーザー オブジェクトに書き込まれ、ユーザーがチェック ボックスをオンにした場合に要求として返される値を入力します。
6. **[OK]** を選択します。

    [Image: ページ レイアウト オプションで文字列属性に複数選択チェック ボックスを追加するスクリーンショット。]
7. **[ページ レイアウト]** ページで、**[保存]** を選択します。

#### ラジオ ボタン (RadioSingleSelect) を構成する

文字列データ型を持つ属性は、属性ラベルの下に表示される一連のラジオ ボタンである RadioSingleSelect ユーザー入力の種類として構成できます。 ユーザーはラジオ ボタンを 1 つだけ選択できます。 個々のラジオ ボタンのテキストを定義し、他のコンテンツへのハイパーリンクを含めることができます。

1. **[ページ レイアウト]** ページで、1 つのラジオ ボタンまたは一連のラジオ ボタンとして構成する**文字列**データ型を持つ属性を見つけます。
2. **[ラベル]** 列で値を選択して、一連のラジオ ボタンの上に表示する見出しを入力します (例: `Sweatshirt size`)。
3. **[ユーザー入力の種類]** 列で値を選択して、エディター ペインを開きます。
4. エディター ペインの **[ユーザー入力の種類]** で、**RadioSingleSelect** を選択します。
5. 追加するラジオ ボタンごとに、新しい行で始めて次の情報を入力します。

    - **[テキスト]** で、ラジオ ボタンの横に表示するテキストを入力します。 Markdown 言語を使用してハイパーリンクを追加します。
    - **[値]**で、ユーザー オブジェクトに書き込まれ、ユーザーがラジオ ボタンを選択した場合に要求として返される値を入力します。
6. **[OK]** を選択します。

    [Image: ページ レイアウト オプションで文字列属性にラジオ ボタンを追加するスクリーンショット。]
7. **[ページ レイアウト]** ページで、**[保存]** を選択します。

### Microsoft Graph を使用して属性の可視性と編集可能性を構成する

各属性の非表示フラグと編集可能フラグを構成することで、サインアップ時にユーザーから表示または収集される属性を制御できます。 現在、これらの設定は管理センター UI では使用できませんが、Microsoft Graph を使用して構成できます。

各属性は、次のフラグをサポートしています。

- `hidden`: このフラグは既定で `false` されるため、属性はサインアップ ページに表示されますが、 `true` に設定して属性を非表示にすることができます。
- `editable`: このフラグは、ユーザーが属性を編集できるようにするために既定で `true` されますが、属性を読み取り専用にするには `false` に設定できます。

例：

- ページに属性を表示し、ユーザーが編集できないようにするには、 `hidden` を `false` に設定し、 `editable` を `false` に設定します。
- プログラムによる設定を許可したままページから属性を非表示にするには、 `hidden` を `true` に設定し、 `editable` を `true` に設定します。 たとえば、属性 [コレクション送信イベントのカスタム認証拡張機能を作成](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-attribute-collection)することで、属性に値を割り当てることができます。

Microsoft Graph を使用して非表示および編集可能なフラグを設定するには、 [authenticationAttributeCollectionInputConfiguration](https://learn.microsoft.com/ja-jp/graph/api/resources/authenticationattributecollectioninputconfiguration) リソースの種類を使用します。 参照については、 [セルフサービス サインアップ ユーザー フローのページ レイアウトを更新する例を](https://learn.microsoft.com/ja-jp/graph/api/authenticationeventsflow-update#example-2-update-the-page-layout-of-a-self-service-sign-up-user-flow)参照してください。

### 拡張アプリのアプリケーション ID を見つける

[カスタム ユーザー属性](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes#custom-user-attributes)は、[*b2c-extensions-app* という名前のアプリに保存](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes#where-custom-user-attributes-are-stored)されます。 ユーザーがサインアップ時にカスタム属性の値を入力すると、その値はユーザー オブジェクトに追加され、名前付け規則 `extension_{appId-without-hyphens}_{custom-attribute-name}` を使用して Microsoft Graph API 経由で呼び出すことができます。ここでの条件は次のとおりです。

- `{appId-without-hyphens}` は、*b2c-extensions-app* のクライアント ID の削除されたバージョンです。
- `{custom-attribute-name}` は、カスタム属性に割り当てた名前です。

拡張アプリのアプリケーション ID を見つけるには、次の手順を使用します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**アプリケーションの登録**&gt;**すべてのアプリケーション**に移動します。
3. アプリケーション**b2c-extensions-app**を選択します。変更しないでください。これは、AADB2Cがユーザーデータを保存するために使用します。
4. **[概要]** ページで、**アプリケーション (クライアント) ID** 値 (例: `12345678-abcd-1234-1234-ab123456789`) を使用します。ただし、ハイフンは削除します。

たとえば、**loyaltyNumber** という名前のカスタム属性を作成した場合は、それを `extension_12345678abcd12341234ab123456789_loyaltyNumber` として参照します

### カスタム ユーザー属性を ID トークンに追加する

ユーザーがアプリにサインインすると、アプリではユーザーの詳細を含む ID トークンを受け取ります。 これらの詳細はトークン クレームと呼ばれます。 必要に応じて、クレームとして使用できるカスタム ユーザー属性を、アプリに返される ID トークンに含めることができます。 それを行うには、[アプリケーションに返される ID トークンへの属性の追加](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-attributes-to-token)に関する記事の手順に従ってください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-delete-external-tenant-portal"} -->
## 外部テナントを削除する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-delete-external-tenant-portal
- Service: entra-external-id / external
- Article date: 2025-02-06
- Summary: Microsoft Entra 管理センターで外部テナントを削除する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

いくつかのチェックに合格するまで、外部テナントを削除することはできません。 これらのチェックにより、外部テナントの削除によるユーザー アクセスへの悪影響のリスクが軽減されます。 たとえば、サブスクリプションに関連付けられたテナントを誤って削除した場合、ユーザーはそのサブスクリプションの Azure リソースにアクセスできなくなります。

### 前提条件

- 削除したい Microsoft Entra の外部 ID テナント。
- テナントを削除する 1 人の [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) を除き、外部テナントにはユーザーはいません。 テナントを削除するには、その前に他のユーザーをすべて削除する必要があります。
- テナント内にアプリケーションが存在しない。 **b2c-extensions-app** を含むすべてのアプリケーションを必ず削除してください。 削除を続行する前に、[すべてのアプリケーション] セクションの [ **アプリの登録** ] に表示されているすべての **アプリ** を削除する必要があります。

### 外部テナントを削除する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**概要**&gt;**テナントを管理**を参照します。
4. 削除するテナントを選択し、[削除] を選択 **します**。

    [Image: テナントを削除する方法を示すスクリーンショット。]
5. テナントを削除する前に、必要なアクションを完了することが必要な場合があります。 たとえば、テナント内のすべてのユーザー フローを削除することが必要な場合があります。 テナントを削除する準備ができたら、[削除] を選択 **します**。

テナントとその関連情報は削除されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-disable-sign-up-user-flow"} -->
## サインアップおよびサインインのユーザー フローでサインアップを無効にする - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-disable-sign-up-user-flow
- Service: entra-external-id / external
- Article date: 2025-06-30
- Summary: Microsoft Graph API を使用してユーザー フローでのサインアップを無効にします。 新しい登録を禁止し、外部ユーザーのサインインのみを許可します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

既存の外部ユーザーのみがサインインできるようにアクセスを制限するには、サインアップとサインインのユーザー フローでサインアップ オプションを無効にすることができます。 この記事では、Microsoft Graph API を使用してユーザー フロー設定を更新し、現在のユーザーのサインインを許可しながら新しい登録を防ぐ方法について説明します。

[Microsoft Graph の Update authenticationEventsFlow API](https://learn.microsoft.com/ja-jp/graph/api/authenticationeventsflow-update) を使用して**、onInteractiveAuthFlowStart** プロパティ &gt;**isSignUpAllowed** プロパティを`false`に更新します。

### [前提条件]

- **サインアップとサインインのユーザー フロー**: 開始する前に、アプリケーションに関連付ける[ユーザー フローを作成します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)。
- **アプリケーションの登録**: 外部テナントに、[アプリケーションを登録します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。

### サインアップ フローを無効にする

サインアップ フローを無効にするには、サインアップを無効にするユーザー フローの ID を知っている必要があります。 Microsoft Entra 管理センターからユーザー フロー ID を読み取ることはできませんが、関連付けられているアプリがわかっている場合は、Microsoft Graph API を使用して取得できます。

サインアップ フローを無効にするには、次の手順に従います。

1. ユーザー フローに関連づけられたアプリケーション ID を読み取ります。

    1. **Entra ID**&gt;**外部アイデンティティ**&gt;**ユーザーフロー**を参照します。
    2. 一覧から、ユーザー フローを選択します。
    3. 左側のメニューで、 **[使用]** の下の **[アプリケーション]** を選択します。
    4. 一覧の [ **アプリケーション (クライアント) ID]** 列で、アプリケーション (クライアント) ID をコピーします。
2. サインアップを無効にするユーザー フローの ID を特定します。 これを行うには、 [特定のアプリケーションに関連付けられているユーザー フローを一覧表示](https://learn.microsoft.com/ja-jp/graph/api/identitycontainer-list-authenticationeventsflows#example-4-list-user-flow-associated-with-specific-application-id)します。 この Microsoft Graph API エンドポイントでは、前の手順で取得したアプリケーション ID を知っている必要があります。
3. [サインアップを](https://learn.microsoft.com/ja-jp/graph/api/authenticationeventsflow-update) 無効にするようにユーザー フローを更新します。

    **例**:

    ```http
    PATCH https://graph.microsoft.com/beta/identity/authenticationEventsFlows/{user-flow-id} 
    ```

    **リクエスト本文**

    ```json
        {    
            "@odata.type": "#microsoft.graph.externalUsersSelfServiceSignUpEventsFlow",    
            "onInteractiveAuthFlowStart": {    
                "@odata.type": "#microsoft.graph.onInteractiveAuthFlowStartExternalUsersSelfServiceSignUp",    
                "isSignUpAllowed": false    
          }    
        }
    ```

    `{user-flow-id}` を、前の手順で取得したユーザー フロー ID に置き換えます。 `isSignUpAllowed` パラメーターが false に設定されていることに注意*してください*。 サインアップを再度有効にするには、Microsoft Graph API エンドポイントを呼び出しますが、 `isSignUpAllowed` パラメーターを *true* に設定します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-enable-password-reset-customers"} -->
## セルフサービス パスワード リセットを有効にする - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers
- Service: entra-external-id / external
- Article date: 2025-09-16
- Summary: 管理者の助けなしに顧客が自分のパスワードをリセットできるように、セルフサービス パスワード リセットを有効にする方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra 外部 ID でのセルフサービス パスワード リセット (SSPR) により、顧客は管理者やヘルプ デスクに頼らなくても、自分のパスワードを変更またはリセットできるようになります。 顧客はアカウントがロックされた場合やパスワードを忘れた場合でも、画面の指示に従って自分自身のブロックを解除して、作業に戻ることができます。

### パスワード リセット プロセスのしくみ

セルフサービス パスワード リセット (SSPR) では、電子メール ワンタイム パスコード (電子メール OTP) と SMS の 2 つの認証方法がサポートされています。 SSPR が有効になっている場合、パスワードを忘れたユーザーは、電子メール OTP または SMS を使用して自分の ID を確認できます。 ワンタイム パスコード認証では、電子メールまたは SMS でパスコードが送信されます。 パスコードを入力すると、ユーザーは新しいパスワードを作成するように求められます。

このプロセスは次のようになります。

1. アプリから、ユーザーが **[サインイン**] を選択します。
2. サインイン ページで、メール アドレスを入力し、[ **次へ**] を選択します。
3. ユーザーがパスワードを忘れた場合は、[ **パスワードを忘れた場合]** を選択します。
4. ユーザーは、自分の ID を確認する方法を選択するように求められます。 登録した方法に基づいて、メールまたは電話に送信されるワンタイム パスコードを選択できます。
5. ワンタイム パスコードは、最初のページで入力したメール アドレスまたは登録済みの電話番号に送信されます。
6. ユーザーがパスコードを入力して続行します。
7. ID が正常に確認されると、ユーザーは新しいパスワードを作成するように求められます。

### 前提条件

- 独自の外部テナントをまだ作成していない場合は、ここで作成します。
- 少なくとも [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロールを持っている。
- ユーザー フローをまだ作成していない場合は、ここで [作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) します。

### 顧客に対してセルフサービス パスワード リセットを有効にする

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから前に作成した外部テナントに切り替えます。
3. **Entra ID**&gt;**外部ID**&gt;**ユーザー フロー**を参照します。
4. **ユーザー フロー**の一覧から、SSPR を有効にするユーザー フローを選択します。
5. サインアップ ユーザー フローで**、ID プロバイダー**の認証方法として**パスワードを使用して電子メール**が登録されていることを確認します。

    [Image: 電子メール認証を有効にする方法を示すスクリーンショット。]

#### パスワード リセットの認証方法を有効にする

セルフサービス パスワード リセットを有効にするには、すべてのユーザーまたはテナント内の特定のグループに対して認証方法を構成します。 次のいずれかのタブを選択して、各メソッドの手順を確認します。

## [電子メール OTP](#tab/emailotp)
次の手順では、セルフサービス パスワード リセットの認証方法として **Email OTP** を有効にする方法を示します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用し、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
2. **Entra ID**&gt;**Authentication メソッド**に移動します。
3. **ポリシー**&gt;**メソッド**で、**電子メール OTP**を選択します。

    [Image: 認証方法を示すスクリーンショット。]
4. **[有効] と [ターゲット]** で、[電子メール OTP] をオンにします。
5. [ **含める**] で、[ **すべてのユーザー** ] または **[グループの選択] を選択** して、この方法を使用できるユーザーを指定します。

    [Image: OTP を有効にするスクリーンショット。]
6. **[保存] を選択します**。

## [SMS](#tab/sms)
セルフサービス パスワード リセットに SMS を使用するには、ユーザーは多要素認証 (MFA) 方法として電話番号を登録する必要があります。 これを行うには、次の 2 つの方法があります。

- MFA 登録は、管理者が [MFA](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength) を必要とする条件付きアクセス ポリシーを設定すると自動的に行われます。
- 管理者は、 [認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-userdevicesettings#add-or-change-authentication-methods-for-a-user)に自分の電話番号を手動で追加できます。

次の手順では、セルフサービス パスワード リセットの認証方法として **SMS** を有効にする方法を示します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用し、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
2. **Entra ID**&gt;**Authentication メソッド**に移動します。
3. **ポリシー**&gt;**メソッド**で**SMS**を選択します。

    [Image: SMS を含む認証方法を示すスクリーンショット。]
4. [ **有効]と[ターゲット]**で、SMS をオンにします。
5. [ **含める**] で、[ **すべてのユーザー** ] または **[グループの選択] を選択** して、この方法を使用できるユーザーを指定します。

    [Image: SMS を有効にするスクリーンショット。]

注

電話 SMS によるセルフサービス パスワード リセットには、電話評判プラットフォームとの組み込みの統合が含まれており、テレフォニー詐欺をリアルタイムで検出できます。 各要求は、ユーザーの保護に役立つ *許可*、 *ブロック*、または *チャレンジ* の決定を返します。 SMS ベースのパスワード リセットは、場所またはリージョンに基づいて [価格が階層化された](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-multifactor-authentication-customers#sms-pricing-tiers-by-countryregion) アドオン機能の一部です。 SMS あたりの料金には、不正行為防止サービスが含まれます。

1. [ **確認する** ] を選択して SMS 使用条件に同意します。
2. **[保存] を選択します**。

---

#### パスワード リセットのリンクを有効にする (オプション)

サインイン ページでは、セルフサービス パスワード リセット リンクの非表示、表示、カスタマイズを行うことができます。

1. 検索バーで、「 **会社のブランド」**と入力して選択します。
2. [ **既定のサインイン] で** [ **編集]** を選択します。
3. [ **サインイン フォーム** ] タブで、[ **セルフサービス パスワード リセット** ] セクションまでスクロールし、[ **セルフサービス パスワード リセットの表示**] を選択します。

    [Image: 会社のブランドのセルフサービス パスワード リセットのスクリーンショット。]
4. **校閲 + 保存** および **保存** を**校閲**タブで選択します。

詳細については、外部テナントのニュートラル [ブランドのカスタマイズに関する記事を参照してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers#to-customize-self-service-password-reset) 。

### セルフサービス パスワード リセット をテストする

セルフサービス パスワード リセット フローを実行するには、次の手順を実行します。

1. アプリケーションを開き、[ **サインイン**] を選択します。
2. サインイン ページで、 **メール アドレス** を入力し、[ **次へ**] を選択します。

    [Image: サインイン ページを示すスクリーンショット。]
3. [ **パスワードを忘れた場合]** リンクを選択します。

    [Image: パスワードを忘れた場合のリンクを示すスクリーンショット。]
4. セルフサービスパスワードリセットでSMSが利用可能な場合は、電子メールまたは電話でワンタイムパスコードを受け取ることができます。 メール アドレスまたは電話番号に送信されたパスコードを入力します。
5. 認証が完了すると、新しいパスワードを入力するように求められます。 **[新しいパスワード**] と [**パスワードの確認**] を入力し、[**パスワードのリセット**] を選択してアプリケーションにサインインします。

    [Image: パスワードの更新画面を示すスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-entra-id-federation-customers"} -->
## 顧客サインインのMicrosoft Entra IDを追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-entra-id-federation-customers
- Service: entra-external-id / external
- Article date: 2026-03-09
- Summary: Microsoft Entra 外部 IDで Microsoft Entra ID テナントを OpenID Connect ID プロバイダーとして構成し、ユーザーが既存の組織アカウントを使用してサインインできるようにする方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra ID テナントとの OpenID Connect (OIDC) フェデレーションを設定すると、そのテナントのユーザーが既存の組織アカウントを使用してアプリケーションにサインインできるようになります。 この方法では、カスタム OIDC ID プロバイダー機能を使用して、Microsoft Entra ID テナントとフェデレーションします。 ( [顧客向けの認証方法と ID プロバイダーの](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers)詳細については、こちらを参照してください)。

### 前提条件

- [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
- ID プロバイダーとして使用するMicrosoft Entra ID テナント。 お持ちでない場合は、 [新しいテナントを作成します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 外部テナントに [登録されているアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 。
- [サインアップとサインインのユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)。

### Microsoft Entra ID テナントに外部テナントを登録する

Microsoft Entra ID テナントからユーザーをフェデレーションするには、まず、ID プロバイダーとして機能するMicrosoft Entra ID テナントに外部テナントをアプリケーションとして登録します。

アプリケーションを登録するときは、次のフェデレーション固有の設定を使用します。

1. **[サポートされているアカウントの種類]** で、 **[この組織のディレクトリ内のアカウントのみ]** を選択します。
2. [ **リダイレクト URI**] で [ **Web** ] を選択し、次の URI を追加します。

    `https://<tenant-subdomain>.ciamlogin.com/<tenant-ID>/federation/oauth2`

    `https://<tenant-subdomain>.ciamlogin.com/<tenant-subdomain>.onmicrosoft.com/federation/oauth2`

    `<tenant-subdomain>`と`<tenant-ID>`を外部テナントの値に置き換えます。 外部テナントがカスタム ドメインを使用している場合は、カスタム ドメインと共にリダイレクト URI も追加します。次に例を示します。

    `https://<tenant-subdomain>.ciamlogin.com/<custom-domain>/federation/oauth2`

詳細なガイダンスについては、「 [アプリケーションの登録」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)参照してください。

アプリが登録されたら、次の構成を完了します。

1. [クライアント シークレット](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=client-secret)を追加し、(シークレット ID ではなく) シークレット値を記録します。 この値は、外部テナントで ID プロバイダーを構成するときに必要です。
2. [ **トークンの構成**] で、ID プロバイダーに送信する省略可能な要求を追加します。
3. **API のアクセス許可**で、Microsoft Graph [委任されたアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-update-permissions): `email`、`openid`、`profile`、および `User.Read`を追加します。 次に、ID プロバイダー テナントの管理者の同意を付与します。
4. [ **概要**] で、 **アプリケーション (クライアント) ID** と **ディレクトリ (テナント) ID を**記録します。 外部テナントでフェデレーションを構成するには、これらの値が必要です。

### 外部テナントで ID プロバイダーを構成する

Microsoft Entra ID テナントに外部テナントを登録したら、それを外部テナントのカスタム OIDC ID プロバイダーとして追加します。 [管理センターで新しい OpenID Connect ID プロバイダーを構成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers#configure-a-new-openid-connect-identity-provider-in-the-admin-center)の手順に従い、次のMicrosoft Entra ID固有の値を使用します。

| Setting | 価値 |
| --- | --- |
| **表示名** | サインイン時にユーザーに表示される名前 ( *例: Contoso でのサインイン*)。 |
| **既知のエンドポイント** | `https://login.microsoftonline.com/organizations/v2.0/.well-known/openid-configuration` |
| **OpenID 発行者 URI** | `https://login.microsoftonline.com/<tenant-ID>/v2.0`。ここで、`<tenant-ID>` は、Microsoft Entra ID テナントのディレクトリ (テナント) ID です。 IdP アクセラレーションに `domain_hint` を使用する場合は、テナント ID ではなくドメイン ベースの発行者形式 `https://login.microsoftonline.com/<domain-name>/v2.0` を使用します。`<domain-name>` は、Microsoft Entra ID テナントのプライマリ ドメイン名です。 |
| **クライアント ID** | Microsoft Entra ID テナントで作成したアプリ登録のアプリケーション (クライアント) ID。 |
| **クライアント認証** | `client_secret` |
| **クライアント シークレット** | アプリの登録から記録したクライアント シークレットの値。 |
| **Scope** | `openid profile` |
| **応答の種類** | `code` |

### ユーザーがサインインして ID プロバイダーにサインアップできるようにする

Microsoft Entra ID ID プロバイダーを構成した後、ユーザーがサインインしてサインアップできるようにする方法は複数あります。

#### ID プロバイダーをユーザー フローに追加する

ID プロバイダーを [サインアップおよびサインイン ユーザー フローに](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) 追加して、ID プロバイダーへのサインインとサインアップを許可します。 [ユーザー フローへの ID プロバイダーの追加を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-identity-provider-to-user-flow-customers)参照してください。 外部ユーザーは、外部 ID テナントに自己登録できます。 ユーザーがサインイン ページでフェデレーション Microsoft Entra ID ID プロバイダーを選択し、組織のアカウントで認証すると、ユーザー アカウントが外部テナントに自動的に作成されます。

#### Microsoft Graph API を使用してユーザーを作成する

管理者は、[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/user-post-users?tabs=http#example-3-create-a-customer-account-in-external-tenants) を使用して、外部 ID テナントに直接ユーザーを作成できます。 この方法は、自動プロビジョニングまたは移行のシナリオに役立ちます。

次の例では、ソース Microsoft Entra ID テナントにリンクされた ID を持つフェデレーション ユーザーを作成します。

```http
POST https://graph.microsoft.com/v1.0/users
Content-type: application/json

{
  "accountEnabled": true,
  "displayName": "Test User",
  "givenName": "Test",
  "mail": "testuser@contoso.com",
  "surname": "Test User",
  "identities": [
    {
      "signInType": "federated",
      "issuer": "https://login.microsoftonline.com/<entra-tenant-id>/v2.0/<entra-external-tenant-id>",
      "issuerAssignedId": "<entra-tenant-user-object-id>"
    }
  ]
}
```

次の値を置き換えます。

- `<entra-tenant-id>`: ソース Microsoft Entra ID テナントのディレクトリ (テナント) ID。
- `<entra-external-tenant-id>`: 外部 ID テナントのディレクトリ (テナント) ID。
- `<entra-tenant-user-object-id>`: ソース Microsoft Entra ID テナント内のユーザーのオブジェクト ID。

### よく寄せられる質問

**"外部 OIDC ID プロバイダーから電子メール アドレスが取得されませんでした" というエラーが表示されます。どのように修正すればよいですか?**

外部 ID フェデレーション シナリオでは、電子メール要求が必要です。 `email` 要求が、外部 ID プロバイダーとして使用されるMicrosoft Entra ID テナントのアプリケーションの **Token 構成**に含まれていることを確認します。

**フェデレーション ID プロバイダーとしてMicrosoft Entra ID構成しましたが、サインイン ページには表示されません。 何を確認する必要がありますか?**

発行者 URI と既知の OpenID 構成エンドポイントが正しく構成されていることを確認します。 発行者または検出エンドポイントが正しくないと、サインイン中に ID プロバイダーが表示されなくなります。 また、次の点も確認します。

- Microsoft Entra ID テナントは、カスタム OIDC ID プロバイダーとして完全に構成されています。
- 必要なリダイレクトURI、発行者の値、スコープが存在し、正しい。
- ID プロバイダーは、テナントだけでなく、ユーザー フローに追加されます。
- 構成の変更が完全に反映されました。 構成の変更後にユーザー フローを再保存または再作成すると、多くの場合、この問題が解決されます。

**エラー `AADSTS500208: The domain is not a valid login domain for the account type` とはどういう意味ですか?**

このエラーは、アカウントの種類がアクセス対象のログイン URL またはテナントの使用を許可されていないため、サインインに失敗したことを示します。 正しいサインイン エンドポイントが使用されていること、およびアカウントがターゲット テナントにアクセスしていることを確認します。

**カスタム OIDC ID プロバイダーを使用する場合のエラー `40015` の意味**

エラー `40015` は、外部 ID プロバイダーでの認証が成功したが、カスタム OIDC ID プロバイダーの構成または返されたトークンを検証できなかったため、外部 ID が応答を拒否したことを意味します。 一般的な原因には、次のようなものがあります。

- 発行者 URI が、ID プロバイダーの検出ドキュメントの発行者の値と正確に一致しません。
- 認証、トークン、または JWKS のエンドポイントが正しくないか、アクセスできません。
- 必須の属性 (件名や電子メールなど) は、ID プロバイダーによって返されません。

**Microsoft Entra ID ユーザーを B2B ゲストとして招待するのとは異なるカスタム OIDC フェデレーションとしてMicrosoft Entra IDを追加する方法**

Microsoft Entra ID フェデレーションの場合:

- ユーザー認証は、常にホーム Microsoft Entra ID テナントで行われます。
- 従業員の条件付きアクセスと MFA ポリシーが適用されます。
- サインイン エクスペリエンスは、B2B ゲスト サインインに関連付けられた混合ブランドエクスペリエンスではなく、ホーム テナントへの完全なリダイレクトです。

**Microsoft Entra の条件付きアクセスと MFA ポリシーは適用されますか?**

Yes. すべての認証はユーザーのホーム Microsoft Entra ID テナントで行われるため、ネイティブ Microsoft Entra ID サインインの場合とまったく同じように、以下が適用されます。

- 条件付きアクセス ポリシー
- MFA の要件
- デバイスベースおよびリスクベースのコントロール

注

外部 ID は現在、Microsoft Entraで実行された MFA を信頼していないため、外部 ID テナントで MFA が必要な場合、ユーザーは MFA を再度完了するように求められる場合があります。

**domain\_hintを使用しているときにドメイン確認ダイアログが表示されるのはなぜですか?**

`domain_hint`を使用すると、ドメインの確認ダイアログが表示され、ユーザーが意図的に正しい組織にサインインしていることを確認し、承認されていないリダイレクトや予期しないリダイレクトから保護します。 このセキュリティ チェックは、リダイレクトが予想される場合でも、今日は抑制できません。

**新しいユーザーは、サインイン ページでメール アドレスを入力したときに、メール ドメインに基づいて自動的にリダイレクトできますか?**

現在、サポートは限られています。 `domain_hint`を使用したドメイン ベースの高速化は特定の構成でサポートされていますが、新しいユーザーの電子メール ドメインのみに基づく完全自動リダイレクトはまだサポートされていません。 ドメイン ベースのルーティングが必要な場合は、サインインを開始するときに、明示的な ID プロバイダー ボタンを使用するか、 `domain_hint` パラメーターを渡すことを検討してください。 Microsoft Entra ID の `domain_hint` 値は、 `domain_hint=contoso.onmicrosoft.com`などのドメイン名にする必要があります。 詳細については、「[発行者アクセラレーション](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers#issuer-acceleration)」を参照してください。

**他の ID プロバイダー ボタンを非表示にして、Microsoft Entra IDのみを表示できますか?**

ID プロバイダーのボタンはユーザー フローに含めずに非表示にできますが、新しいユーザーは `domain_hint` が使用されている場合にのみ登録できます。

**ID トークンは不透明な値として返されますか?**

No. Microsoft Entra IDは、標準の署名付き JWT トークンを発行します。 ID トークンは読み取り可能であり、OpenID Connect の仕様に準拠しています。

** 1 つの外部テナントで複数のMicrosoft Entra ID テナントを使用できますか?**

Yes. 複数のMicrosoft Entra ID テナントを個別のカスタム OIDC ID プロバイダーとして構成し、外部 ID ユーザー フロー内で公開することができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-facebook-federation-customers"} -->
## 顧客のサインイン用に Facebook を追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-facebook-federation-customers
- Service: entra-external-id / external
- Article date: 2025-09-16
- Summary: Facebook を外部テナントの ID プロバイダーとして追加し、顧客が Facebook アカウントを使用してアプリケーションにサインインできるようにする方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Facebook とのフェデレーションを設定すると、顧客が独自の Facebook アカウントを使用してアプリケーションにサインインできるようになります。 ( [顧客向けの認証方法と ID プロバイダーの](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers)詳細については、こちらを参照してください)。

### Facebook アプリケーションを作成する

Facebook アカウントを持つ顧客のサインインを有効にするには、 [Facebook アプリ ダッシュボード](https://developers.facebook.com/)でアプリケーションを作成する必要があります。 詳細については、「 [アプリ開発」](https://developers.facebook.com/docs/development)を参照してください。

まだ Facebook アカウントを持っていない場合は、https://www.facebook.com でサインアップしてください。 Facebook アカウントでサインアップまたはサインインしたら、 [Facebook 開発者アカウントの登録プロセス](https://developers.facebook.com/async/registration)を開始します。 詳細については、「 [Facebook 開発者として登録する」を](https://developers.facebook.com/docs/development/register)参照してください。

注記

このドキュメントは、作成した時点でのプロバイダーの開発者ページの状態を使って作成されたものであり、変更が発生する可能性があります。

1. Facebook 開発者アカウントの資格情報を使用して [、開発者向けに](https://developers.facebook.com/apps) Facebook にサインインします。
2. まだ登録していない場合は、Facebook 開発者として登録します。ページの右上隅にある [ **作業の開始** ] を選択し、Facebook のポリシーに同意して、登録手順を完了します。
3. [ **アプリの作成] を選択します**。 この手順では、Facebook プラットフォームのポリシーを受け入れてオンライン セキュリティ チェックを完了することが必要な場合があります。
4. [**認証] を選択し、Facebook ログインを使用してユーザーにデータを要求**します&gt;**次へ**。
5. [ **ゲームをビルドしていますか?** ] で [ **いいえ、 ゲームをビルドしていない** ]、[ **次へ**] の順に選択します。
6. アプリ名と有効なアプリの連絡先メール アドレスを追加します。 ビジネス アカウントがある場合は、それを追加することもできます。
7. [ **アプリの作成] を選択します**。
8. アプリが作成されたら、ダッシュボードに移動します。
9. [ **アプリの設定]**&gt;**[基本**]を選択します。
    1. **アプリ ID** の値をコピーします。 次に、[ **表示** ] を選択し、 **アプリ シークレット**の値をコピーします。 これらの値の両方を使用して Facebook をテナントの ID プロバイダーとして構成します。 **アプリ シークレット** は重要なセキュリティ資格情報です。
    2. **プライバシー ポリシー URL の URL を**入力します (例: `https://www.contoso.com/privacy`)。 ポリシーの URL は、アプリケーションのプライバシーに関する情報を提供するために維持されるページです。
    3. **サービス利用規約 URL の URL を**入力します (例: `https://www.contoso.com/tos`)。 ポリシーの URL は、アプリケーションの利用規約を提供するために維持されるページです。
    4. **ユーザー データ**削除の URL を入力します (例: `https://www.contoso.com/delete_my_data`)。 ユーザー データ削除 URL は、自分のデータの削除を要求する手段をユーザーに提供するために維持するページです。
    5. **カテゴリ** (**ビジネスやページなど)** を選択します。 Facebook ではこの値が必要ですが、Microsoft Entra ID では使用されません。
10. ページの下部にある [ **プラットフォームの追加**] を選択し、[ **Web サイト**] を選択して、[ **次へ**] を選択します。
11. **[サイト URL]** に、Web サイトのアドレスを入力します (例: `https://contoso.com`)。
12. [ **変更の保存] を選択します**。
13. 左側の **[ユース ケース**] を選択し、[**認証とアカウントの作成**] の横にある **[カスタマイズ**] を選択します。
14. **[Facebook ログイン**] の [**設定に移動**] を選択します。
15. **[有効な OAuth リダイレクト URI] に**次の URI を入力し、`<tenant-ID>`を**外部テナント ID** に置き換え、`<tenant-name>`を**外部テナント名**に置き換えます。

- `https://login.microsoftonline.com/te/<tenant-ID>/oauth2/authresp`
- `https://login.microsoftonline.com/te/<tenant-name>.onmicrosoft.com/oauth2/authresp`
- `https://<tenant-name>.ciamlogin.com/<tenant-ID>/federation/oidc/www.facebook.com`
- `https://<tenant-name>.ciamlogin.com/<tenant-name>.onmicrosoft.com/federation/oidc/www.facebook.com`
- `https://<tenant-name>.ciamlogin.com/<tenant-ID>/federation/oauth2`
- `https://<tenant-name>.ciamlogin.com/<tenant-name>.onmicrosoft.com/federation/oauth2`

1. [ **変更の保存]** を選択し、ページの上部にある [ **アプリ** ] を選択し、先ほど作成したアプリを選択します。
2. ページの左側にある **[ユース ケース**] を選択し、[**認証とアカウントの作成**] の横にある **[カスタマイズ**] を選択します。
3. [アクセス許可] で [ **追加** ] を選択して、電子メールの **アクセス許可**を追加します。
4. ページの上部にある [ **戻る** ] を選択します。
5. この時点では、Facebook アプリケーションの所有者のみがサインインできます。 アプリを登録したため、Facebook アカウントを使用してサインインできます。 Facebook アプリケーションをユーザーが使用できるようにするには、メニューから [ **ライブに移動**] を選択します。 一覧表示されているすべての手順に従って、すべての要件を完了します。 ID をビジネス エンティティまたは組織として確認するため、データ処理の質問とビジネス検証を完了することが必要な場合があります。 詳細については、「 [メタ アプリ開発」を](https://developers.facebook.com/docs/development/release)参照してください。

### Microsoft Entra 外部 ID で Facebook フェデレーションを構成する

Facebook アプリケーションを作成したら、この手順では、Microsoft Entra ID で Facebook のクライアント ID とクライアント シークレットを設定します。 これを行うには、Microsoft Entra 管理センターまたは PowerShell を使用できます。 Microsoft Entra 管理センターで Facebook フェデレーションを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[Entra ID]**&gt;**[外部 ID]**&gt;**[すべての ID プロバイダー]** に移動します。
3. [ **組み込み** ] タブの **[Facebook**] の横にある [ **構成**] を選択します。
4. **[名前]** を入力します。 たとえば、 *Facebook* です。
5. **クライアント ID** には、先ほど作成した Facebook アプリケーションのアプリ ID を入力します。
6. **クライアント シークレット**の場合は、記録したアプリ シークレットを入力します。
7. **[保存] を選択します**。

PowerShell を使用して Facebook フェデレーションを構成するには、次の手順に従います。

1. 最新バージョンの [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) をインストールします。
2. 次のコマンドを実行します。

    ```powershell
    Connect-MgGraph -Scopes "IdentityProvider.ReadWrite.All"
    ```
3. サインイン プロンプトで、少なくとも [外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)としてサインインします。
4. 次のコマンドを実行します。

    ```powershell
    $params = @{
       "@odata.type" = "microsoft.graph.socialIdentityProvider"
       displayName = "Facebook"
       identityProviderType = "Facebook"
       clientId = "[Client ID]"
       clientSecret = "[Client secret]"
    }
    
    New-MgIdentityProvider -BodyParameter $params
    ```

Facebook アプリケーションの作成手順で作成したアプリのクライアント ID とクライアント シークレットを使用します。

### ユーザーがサインインして ID プロバイダーにサインアップできるようにする

Facebook を ID プロバイダーとして構成したら、それをユーザー フローに追加して、ID プロバイダーへのサインインとサインアップを許可します。 [ユーザー フローへの ID プロバイダーの追加を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-identity-provider-to-user-flow-customers)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-google-federation-customers"} -->
## Google を ID プロバイダーとして追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers
- Service: entra-external-id / external
- Article date: 2026-03-27
- Summary: 外部テナント用の ID プロバイダーとして Google を追加する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Google とのフェデレーションを設定することで、顧客が自分自身の Google アカウントを使用してアプリケーションにサインインできるようにします。 ( [顧客向けの認証方法と ID プロバイダーの](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-authentication-methods-customers)詳細については、こちらを参照してください)。

### 前提条件

- [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
- [サインアップとサインインのユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)。

### Google アプリケーションを作成する

Google アカウントを使用して顧客のサインインを有効にするには、 [Google Cloud コンソール](https://console.cloud.google.com/)でアプリケーションを作成する必要があります。 詳細については、「 [OAuth クライアントの管理](https://support.google.com/cloud/answer/15549257)」を参照してください。 まだ Google アカウントを持っていない場合は、[`https://accounts.google.com/signup`](https://accounts.google.com/signup) でサインアップできます。

1. Google アカウントの資格情報を使用して [Google Cloud コンソール](https://console.cloud.google.com/) にサインインします。
2. サービスの使用条件への同意を求めるメッセージが表示されたらそのようにします。
3. ページの左上隅で、プロジェクトの一覧を選択し、[ **新しいプロジェクト**] を選択します。
4. **プロジェクト名**を入力し、[**作成**] を選択します。
5. 画面左上にあるプロジェクト ドロップダウンを選択して、新しいプロジェクトを使用していることを確認します。 名前でプロジェクトを選択し、[ **開く**] を選択します。
6. **クイック アクセス**または左側のメニューで、**API とサービス**を選択し、**OAuth 同意画面**を選択します。
7. **[ユーザーの種類] で** [**外部**] を選択し、[**作成**] を選択します。
8. **OAuth 同意画面**の **アプリ情報セクション**

    1. アプリケーションの **名前** を入力します。
    2. **ユーザー サポートの電子メール** アドレスを選択します。
9. [ **承認済みドメイン** ] セクションで、[ **ドメインの追加**] を選択し、 `ciamlogin.com` と `microsoftonline.com`を追加します。
10. [ **開発者の連絡先情報** ] セクションに、プロジェクトの変更について通知する Google のメールアドレスをコンマで区切って入力します。
11. [ **保存して続行] を選択します**。
12. 左側のメニューから [資格情報] を選択 **します**
13. [ **資格情報の作成**] を選択し、[ **OAuth クライアント ID] を選択します**。
14. [ **アプリケーションの種類] で**、[ **Web アプリケーション**] を選択します。

    1. アプリケーションに適した **名前** ("Microsoft Entra 外部 ID" など) を入力します。
    2. **[有効な OAuth リダイレクト URI] に**、次の URI を入力します。 `<tenant-ID>` を自分の顧客ディレクトリ (テナント) ID に、`<tenant-subdomain>` を自分の顧客ディレクトリ (テナント) サブドメインに置き換えます。 テナント名がない場合は、 [テナントの詳細を読み取る方法について説明](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)します。

    - `https://login.microsoftonline.com`
    - `https://login.microsoftonline.com/te/<tenant-ID>/oauth2/authresp`
    - `https://login.microsoftonline.com/te/<tenant-subdomain>.onmicrosoft.com/oauth2/authresp`
    - `https://<tenant-ID>.ciamlogin.com/<tenant-ID>/federation/oidc/accounts.google.com`
    - `https://<tenant-ID>.ciamlogin.com/<tenant-subdomain>.onmicrosoft.com/federation/oidc/accounts.google.com`
    - `https://<tenant-subdomain>.ciamlogin.com/<tenant-ID>/federation/oauth2`
    - `https://<tenant-subdomain>.ciamlogin.com/<tenant-subdomain>.onmicrosoft.com/federation/oauth2`
15. **[作成]**を選択します。
16. **クライアント ID とクライアント** シークレットの値を記録**します**。 テナントで Google を ID プロバイダーとして構成するには、両方の値が必要です。

メモ

場合によっては、アプリで Google による確認が必要になる場合があります (たとえば、アプリケーションのロゴを更新した場合)。 詳細については、 [Google の確認ステータス ガイド](https://support.google.com/cloud/answer/10311615#verification-status)を参照してください。

### Microsoft Entra 外部 IDで Google フェデレーションを構成する

Google アプリケーションを作成した後、この手順では、Microsoft Entra ID で Google クライアント ID とクライアント シークレットを設定します。 これを行うには、Microsoft Entra 管理センターまたは PowerShell を使用できます。 Microsoft Entra 管理センターで Google フェデレーションを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[Entra ID]**&gt;**[外部 ID]**&gt;**[すべての ID プロバイダー]** に移動します。
3. [ **組み込み** ] タブの **[Google**] の横にある [ **構成**] を選択します。
4. **名前**を入力します。 たとえば、 *Google* などです。
5. **[クライアント ID**] に、先ほど作成した Google アプリケーションのクライアント ID を入力します。
6. **クライアント シークレット**の場合は、記録したクライアント シークレットを入力します。
7. **[保存] を選択します**。

PowerShell を使用して Google フェデレーションを構成するには、次の手順に従います。

1. 最新バージョンの [Microsoft Graph PowerShell for Graph モジュールをインストールします](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)。
2. 次のコマンドを実行します。`Connect-MgGraph`
3. サインイン プロンプトで、少なくとも [外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)としてサインインします。
4. 次のコマンドを実行します。

    ```powershell
    Import-Module Microsoft.Graph.Identity.SignIns
    $params = @{
    "@odata.type" = "microsoft.graph.socialIdentityProvider"
    displayName = "Login with Google"
    identityProviderType = "Google"
    clientId = "00001111-aaaa-2222-bbbb-3333cccc4444"
    clientSecret = "000000000000"
    }
    New-MgIdentityProvider -BodyParameter $params
    ```

Google アプリケーションの作成手順で作成したアプリのクライアント ID とクライアント シークレットを使用します。

### ユーザーがサインインして ID プロバイダーにサインアップできるようにする

Google を ID プロバイダーとして構成したら、それをユーザー フローに追加して、ID プロバイダーへのサインインとサインアップを許可します。 [ユーザー フローへの ID プロバイダーの追加を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-identity-provider-to-user-flow-customers)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-integrate-fraud-protection"} -->
## 不正アクセス防止の統合 - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-integrate-fraud-protection
- Service: entra-external-id / external
- Article date: 2025-09-24
- Summary: Microsoft Entra External ID を使用して Arkose Labs と Human fraud Protection を構成し、ユーザーのサインアップ フロー中にボット攻撃と偽のアカウント作成をブロックする方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra External ID は、 [Arkose Labs](https://www.arkoselabs.com/solutions/fake-account-creation/) や [HUMAN](https://www.humansecurity.com/) などのサードパーティの不正行為防止プロバイダーとの統合をサポートし、偽のアカウントサインアップやボット攻撃を防ぎます。 これらのプロバイダーは、ユーザーのサインアップ プロセス中に、ボット主導の登録などの自動攻撃を検出してブロックできるようにする包括的な不正行為防止ソリューションを提供します。

Arkose Labs、HUMAN、またはその両方を Microsoft Entra External ID と統合することで、高度なリスク評価機能を利用して、正当なユーザーのみがアカウントを作成できるようにします。

この記事では、不正行為防止プロバイダーと Microsoft Entra External ID を統合する方法について説明します。

### おすすめの統合について

Arkose Labs と HUMAN Security は、Security Store を介したサインアップ保護のネイティブ統合として利用できます。 Microsoft は、ユーザー登録時にボットの検出と偽のアカウント防止をサポートするために、各プロバイダーと連携してこれらの統合を開発し、デプロイしました。

ネイティブ セキュリティ ストアの統合は、Microsoft の製品エンジニアリング チームとの直接のパートナーシップを通じて、ケース バイ ケースで開発されます。 Microsoft Entra External ID を使用した統合の機会に関心があるプロバイダーの場合は、Microsoft パートナーの開発担当者にお問い合わせください。

::: zone pivot="arkose"

### Arkose Labs のしくみ

ユーザーがサインアップしようとすると、Arkose Labs サービスによって要求が評価され、不正である可能性が高いかどうかを判断します。 要求に疑わしいフラグが設定されている場合、ユーザーには、人間であることを確認するためのチャレンジ (CAPTCHA など) が表示されます。 ユーザーがチャレンジを正常に完了した場合は、サインアップ プロセスに進むことができます。 この機能を有効にすると、Arkose Labs ダッシュボードで Arkose チャレンジ メトリックを表示できるようになります。 さらに、Arkose Labs のサポートと連携することで、リスク ポリシーを微調整できます。

Microsoft Entra 管理センターと Microsoft Graph API の両方で統合を完了できます。 この記事では、Microsoft Entra 管理センターの手順について説明します。

### [前提条件]

- [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
- テナントの[登録アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。
- 外部テナントの [認証機能拡張管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-extensibility-administrator) または [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロールを少なくとも持つアカウント。
- Arkose Labs アカウント。 お持ちでない場合は、 [セキュリティ ストア](https://securitystore.microsoft.com/solutions/arkoselabs1589934191756.arkose_securitystore) にアクセスしてアカウントを作成して購入してください。
- Arkose の次の構成値:
    - 公開キー (GUID 形式)。
    - 秘密キー (GUID 形式)。
    - クライアント サブドメイン: 完全なドメインではなく、サブドメイン プレフィックス ("client-api" など) のみを使用します。
    - サブドメインの確認: 完全なドメインではなく、サブドメイン プレフィックス ("verify-api" など) のみを使用します。

### Microsoft Entra 管理センターで Arkose Labs を構成する

Arkose Labs と Microsoft Entra External ID を統合するには、Microsoft Entra 管理センターのセキュリティ ストア ウィザードを使用して、不正行為防止プロバイダー ポリシーを作成できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[認証機能拡張管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-extensibility-administrator)または[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Home**&gt;**Security Store**&gt;**Sign-up Protection** に移動してウィザードを開始します。

    [Image: サインアップ保護ページを示すスクリーンショット。]
4. *ArkosePolicy* などのポリシーの名前を入力します。
5. 不正アクセス防止ポリシーを適用するシナリオを選択し、[ **次へ**] を選択します。

    [Image: 保護ポリシーの設定を示すスクリーンショット。]
6. サインアップ手順の **[不正アクセス防止プロバイダーの選択** ] で、プロバイダーとして **[Arkose Labs** ] を選択し、[ **次へ**] を選択します。

    [Image: Arkose Labs の不正アクセス防止プロバイダーの選択を示すスクリーンショット。]
7. Arkose アカウントを作成します。 アカウントをまだお持ちでない場合は、 [セキュリティ ストア](https://securitystore.microsoft.com/solutions/arkoselabs1589934191756.arkose_securitystore)でアカウントを作成して購入してください。 次に、ここに戻ってセットアップを完了します。

    [Image: Arkose Labs プロバイダーの構成を示すスクリーンショット。]
8. サインアップ **保護のための Arkose Labs の構成** 手順で、既存の構成を選択するか、[ **新しい** 構成の作成] を選択し、Arkose Labs から受け取った構成値を入力します。

    - **公開キー: 公開**キー (GUID 形式) を入力します。
    - **秘密キー: 秘密**キー (GUID 形式) を入力します。
    - **クライアント サブドメイン**: 完全なドメインではなく、サブドメイン プレフィックス ("client-api" など) のみを入力します。
    - **サブドメインの確認**: 完全なドメインではなく、サブドメイン プレフィックス ("verify-api" など) のみを入力します。
9. それ以外の場合は、**[次へ]** を選択して次の手順に進みます。
10. Arkose Labs の不正アクセス防止で保護するアプリを選択します。 外部テナントに登録した 1 つ以上のアプリケーションを選択できます。

    [Image: 保護するアプリケーションの選択を示すスクリーンショット。]
11. 構成を確認し、[ **ポリシーの作成** ] を選択して不正アクセス防止ポリシーを作成します。

    [Image: Arkose Labs のポリシーの作成を示すスクリーンショット。]
12. ポリシーが正常に作成されたことを確認するメッセージが表示されたら、[ **完了]** を選択してウィザードを完了します。

ポリシーが作成されると、選択したアプリケーションに適用されます。 ユーザーがサインアップしようとすると、Arkose Labs サービスによって要求が評価され、不正である可能性が高いかどうかを判断します。 要求に疑わしいフラグが設定されている場合、ユーザーには、人間であることを確認するためのチャレンジ (CAPTCHA など) が表示されます。 ユーザーがチャレンジを正常に完了した場合は、サインアップ プロセスに進むことができます。

### Microsoft Entra 管理センターで Arkose Labs 構成を編集する

1. **Home**&gt;**Security Store**&gt;**Sign-up Protection** に移動して、構成の一覧を表示します。
2. [ **プロバイダー構成の編集]** オプションを選択して、Arkose Labs ポリシーを編集します。 不正アクセス防止ポリシーを編集する場合は、鉛筆アイコンを選択します。
3. [ **サインアップ保護用に Arkose Labs を構成** する] 手順で、編集する構成を選択し、[ **次へ**] を選択します。
4. Arkose Labs の不正アクセス防止で保護するアプリを選択するか、既存のアプリを削除します。 外部テナントに登録した 1 つ以上のアプリケーションを選択できます。 アプリを選択したら、[ **次へ**] を選択します。
5. [ **完了] を** 選択してウィザードを完了します。

### Microsoft Graph API を使用して Arkose Labs を構成する

Microsoft Graph API を使用して Arkose Labs の不正アクセス防止を構成します。 この方法は、構成を自動化する場合や、Microsoft Entra 管理センターよりも API を使用する場合に便利です。

#### 手順 1: テナントにサインインする

Arkose Labs の不正アクセス保護を構成するには、外部テナントにサインインし、必要なアクセス許可に同意する必要があります。

1. [Microsoft Graph エクスプローラー ツール](https://aka.ms/ge)を起動します。
2. 外部テナントにサインインしてください: `https://developer.microsoft.com/en-us/graph/graph-explorer?tenant=<your-tenant-name.onmicrosoft.com>`。
3. プロファイルを選択し、[ **アクセス許可に同意する**] を選択します。
4. 次の必要なアクセス許可に同意します。

    - `RiskPreventionProviders.ReadWrite.All`

#### 手順 2: Arkose Labs を不正行為防止プロバイダーとして登録する

Arkose Labs を不正行為防止プロバイダーとして登録するには、外部テナントに [fraudProtectionProvider ポリシーを作成](https://learn.microsoft.com/ja-jp/graph/api/riskpreventioncontainer-post-fraudprotectionproviders) します。 このポリシーには、Arkose Labs から受け取った Arkose 構成値が含まれています。

1. Microsoft Graph エクスプローラーで **POST** メソッドを選択し、次の URL を入力します。

    ```http
    https://graph.microsoft.com/beta/identity/riskPrevention/fraudProtectionProviders
    ```
2. [ **要求本文** ] セクションで、プレースホルダーを Arkose 構成値に置き換えて、次の JSON ペイロードを入力します。

    ```json
    {
        "@odata.type": "#microsoft.graph.arkoseFraudProtectionProvider",
        "displayName": "<your-arkose-configuration-name>",
        "publicKey": "<your-arkose-public-key>",
        "privateKey": "<your-arkose-private-key>",
        "clientSubDomain": "<your-client-api>",
        "verifySubDomain": "<your-verify-api>"
    }
    ```
3. [ **クエリの実行]** を選択して、不正アクセス防止プロバイダーを作成します。
4. 要求が成功すると、 **201 Created** 応答が返されます。 次の手順の応答から `id` 値をコピーします。

#### 手順 3: Arkose 保護をアプリケーションにリンクする

この手順では、サインアップ フロー中に Arkose チャレンジをトリガーする新しい [authenticationEventListener](https://learn.microsoft.com/ja-jp/graph/api/identitycontainer-post-authenticationeventlisteners) を作成して、Arkose 詐欺防止プロバイダーをアプリケーションにリンクします。 不正アクセス防止を有効にするアプリケーションの appId があることを確認します。 `<your-app-id>`をアプリケーションの ID に置き換え、`<id-from-previous-step>`を前の手順の Arkose プロバイダー ID に置き換えます。

1. Microsoft Graph エクスプローラーで **POST** メソッドを選択し、次の URL を入力します。

    ```http
    https://graph.microsoft.com/beta/identity/authenticationEventListeners
    ```
2. [ **要求本文** ] セクションで、次の JSON ペイロードを入力します。

    ```json
    { 
      "@odata.type": "#microsoft.graph.onFraudProtectionLoadStartListener", 
      "conditions": { 
        "applications": { 
          "includeApplications": [ 
            { 
              "appId": "<your-app-id>" 
            } 
          ] 
        } 
      }, 
      "handler": { 
        "@odata.type": "#microsoft.graph.onFraudProtectionLoadStartExternalUsersAuthHandler", 
        "signUp": { 
          "@odata.type": "#microsoft.graph.fraudProtectionProviderConfiguration", 
          "fraudProtectionProvider": { 
            "@odata.type": "#microsoft.graph.arkoseFraudProtectionProvider", 
            "id": "<id-from-previous-step>" 
          } 
        } 
      } 
    }
    ```
3. **[クエリの実行]** を選択します。
4. 要求が成功すると、 **201 Created** 応答が返されます。 アプリケーションのサインアップ フローをテストして、疑わしいサインアップが検出されたときに Arkose チャレンジが表示されることを確認します。

#### トラブルシューティングのヒント

| 問題点 | 考えられる原因 | 解決策 |
| --- | --- | --- |
| チャレンジは表示されません。 | イベント リスナーがアプリにリンクされていません。 | アプリ ID とリスナーの構成を確認します。 |
| ユーザーはチャレンジ時にブロックされました。 | 厳格なArkoseのしきい値。 | Arkose と協力してチャレンジ動作を調整します。 |

::: zone-end

::: zone pivot="human"

### HUMAN セキュリティのしくみ

ユーザーがサインアップ プロセスを開始するときは、自動化されたボットや悪意のあるアクターが不正なアカウントを作成できないようにすることが重要です。 これに対処するために、Entra External ID では、HUMAN Security などのサードパーティの不正行為検出プロバイダーとの統合がサポートされています。 この統合により、サインアップ試行のリアルタイム分析が可能になり、HUMAN Security の高度な検出アルゴリズムを利用して、アカウントの作成が完了する前に疑わしいアクティビティを特定してブロックできます。 このソリューションは Microsoft ID インフラストラクチャとネイティブに統合されているため、デプロイと管理を合理化できます。 このアプローチを実装することで、組織は詳細なデータと実用的な分析情報にアクセスしてサインアップ フローを監視し、検出のしきい値を微調整し、新たな脅威に積極的に対応し、ユーザー ベースの整合性を維持することができます。

### [前提条件]

- [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
- テナントの[登録アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。
- 外部テナントの [認証機能拡張管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-extensibility-administrator) または [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロールを少なくとも持つアカウント。
- HUMAN セキュリティ アカウント。 お持ちでない場合は、 [セキュリティ ストア](https://securitystore.microsoft.com/solutions/human_security.human_sightline_fake_account_defense) にアクセスしてアカウントを作成して購入してください。
- HUMAN Security の次の構成値:
    - アプリケーション識別子
    - サーバー トークン

これらの値は、 [アプリケーション設定](https://docs.humansecurity.com/applications-and-accounts/docs/managing-applications#adding-server-tokens) ページの HUMAN Security 管理コンソールで確認できます。 値がわからない場合は、HUMAN Security にお問い合わせください。

### Microsoft Entra 管理センターで HUMAN セキュリティを構成する

HUMAN Security と Microsoft Entra External ID を統合するには、Microsoft Entra 管理センターのセキュリティ ストア ウィザードを使用して、不正行為防止プロバイダー ポリシーを作成できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[認証機能拡張管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-extensibility-administrator)または[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Home**&gt;**Security Store**&gt;**Sign-up Protection** に移動してウィザードを開始します。

    [Image: サインアップ保護ページを示すスクリーンショット。]
4. *HUMANPolicy* などのポリシーの名前を入力します。
5. 不正アクセス防止ポリシーを適用するシナリオを選択し、[ **次へ**] を選択します。

    [Image: 保護ポリシーの設定を示すスクリーンショット。]
6. サインアップ手順 **の [不正アクセス防止プロバイダーの選択** ] で、プロバイダーとして **[HUMAN Security** ] を選択し、[ **次へ**] を選択します。

    [Image: HUMAN Security Fraud Protection プロバイダーの選択を示すスクリーンショット。]
7. HUMAN セキュリティ アカウントを作成します。 アカウントをまだお持ちでない場合は、 [セキュリティ ストア](https://securitystore.microsoft.com/solutions/human_security.human_sightline_fake_account_defense)でアカウントを作成して購入してください。 次に、ここに戻ってセットアップを完了します。

    [Image: HUMAN Security Fraud Protection プロバイダーの構成を示すスクリーンショット。]
8. サインアップ **保護の HUMAN Security の構成** 手順で、[ **新しい** 構成の作成] を選択し、HUMAN Security から受け取った構成値を入力します。

    - **アプリ ID**: HUMAN Security のアプリケーション ID を入力します。
    - **サーバー トークン**: HUMAN Security 管理コンソールで見つけることができる HUMAN Security のサーバー トークンを入力します。
9. または、既存の構成を既に設定している場合は選択し、[ **次へ**] を選択します。
10. HUMAN Security の不正アクセス防止で保護するアプリを選択します。 外部テナントに登録した 1 つ以上のアプリケーションを選択できます。

    [Image: 保護するアプリケーションの選択を示すスクリーンショット。]
11. 構成を確認し、[ **ポリシーの作成** ] を選択して不正アクセス防止ポリシーを作成します。
12. ポリシーが正常に作成されたことを確認するメッセージが表示されたら、[ **完了]** を選択してウィザードを完了します。

ポリシーが作成されると、選択したアプリケーションに適用されます。 ユーザーがサインアップしようとすると、HUMAN Security は要求をリアルタイムで評価して、不正な可能性があるかどうかを判断します。 要求に疑わしいフラグが設定されている場合は、サインアップの試行をブロックするための適切な対策が講じられ、偽のアカウントの登録が防止されます。

### Microsoft Entra 管理センターで HUMAN セキュリティ構成を編集する

1. **Home**&gt;**Security Store**&gt;**Sign-up Protection** に移動して、構成の一覧を表示します。
2. [ **プロバイダー構成の編集] オプションを** 選択して、HUMAN セキュリティ ポリシーを編集します。 不正アクセス防止ポリシーを編集する場合は、鉛筆アイコンを選択します。
3. サインアップ保護のために**HUMANセキュリティを構成する**手順で、編集する構成を選択し、[**次へ**] を選択します。
4. HUMAN Security Fraud Protection で保護するアプリを選択するか、既存のアプリを削除します。 外部テナントに登録した 1 つ以上のアプリケーションを選択できます。 アプリを選択したら、[ **次へ**] を選択します。
5. [ **完了] を** 選択してウィザードを完了します。

### Microsoft Graph API を使用して HUMAN Security を構成する

Microsoft Graph API を使用して、HUMAN Security Fraud Protection を構成します。 この方法は、構成を自動化する場合や、Microsoft Entra 管理センターよりも API を使用する場合に便利です。

#### 手順 1: テナントにサインインする

HUMAN Security Fraud Protection を構成するには、外部テナントにサインインし、必要なアクセス許可に同意する必要があります。

1. [Microsoft Graph エクスプローラー ツール](https://aka.ms/ge)を起動します。
2. 外部テナントにサインインしてください: `https://developer.microsoft.com/en-us/graph/graph-explorer?tenant=<your-tenant-name.onmicrosoft.com>`。
3. プロファイルを選択し、[ **アクセス許可に同意する**] を選択します。
4. 次の必要なアクセス許可に同意します。

    - `RiskPreventionProviders.ReadWrite.All`

#### 手順 2: 不正行為防止プロバイダーとして HUMAN セキュリティを登録する

HUMAN Security を不正行為防止プロバイダーとして登録するには、外部テナントに [fraudProtectionProvider ポリシーを作成](https://learn.microsoft.com/ja-jp/graph/api/riskpreventioncontainer-post-fraudprotectionproviders) します。 このポリシーには、HUMAN セキュリティ アカウントの設定中に受け取った HUMAN セキュリティ構成値が含まれています。

1. Microsoft Graph エクスプローラーで **POST** メソッドを選択し、次の URL を入力します。

    ```http
    https://graph.microsoft.com/beta/identity/riskPrevention/fraudProtectionProviders
    ```
2. [ **要求本文** ] セクションで、次の JSON ペイロードを入力し、プレースホルダーを実際の HUMAN 構成値に置き換えます。

    ```json
    {
        "@odata.type": "#microsoft.graph.HUMANFraudProtectionProvider",
        "displayName": "<your-human-configuration-name>",
        "appId": "<your-human-appid>",  
        "serverToken": "<your-human-server-token>",
    }
    ```

`displayName`は、この特定の HUMAN セキュリティ構成 ("HUMAN Config 1" など) の表示名です。 `appId`は HUMAN Security のアプリケーション ID であり、`serverToken`は HUMAN Security 管理コンソールで見つけることができる HUMAN Security のサーバー トークンです。 値がわからない場合は、HUMAN Security にお問い合わせください。

1. [ **クエリの実行]** を選択して、不正アクセス防止プロバイダーを作成します。
2. 要求が成功すると、 **201 Created** 応答が返されます。 次の手順の応答から `id` 値をコピーします。

#### 手順 3: HUMAN セキュリティ保護をアプリケーションにリンクする

この手順では、サインアップ フロー中に HUMAN Security 不正アクセス防止を使用する新しい [authenticationEventListener](https://learn.microsoft.com/ja-jp/graph/api/identitycontainer-post-authenticationeventlisteners) を作成して、HUMAN Security 不正防止プロバイダーをアプリケーションにリンクします。 不正アクセス防止を有効にするアプリケーションの appId があることを確認します。 `<your-app-id>`をアプリケーションの ID に置き換え、`<id-from-previous-step>`を前の手順の HUMAN セキュリティ プロバイダー ID に置き換えます。

1. Microsoft Graph エクスプローラーで **POST** メソッドを選択し、次の URL を入力します。

    ```http
    https://graph.microsoft.com/beta/identity/authenticationEventListeners
    ```
2. [ **要求本文** ] セクションで、次の JSON ペイロードを入力します。

    ```json
    { 
      "@odata.type": "#microsoft.graph.onFraudProtectionLoadStartListener", 
      "conditions": { 
        "applications": { 
          "includeApplications": [ 
            { 
              "appId": "<your-app-id>" 
            } 
          ] 
        } 
      }, 
      "handler": { 
        "@odata.type": "#microsoft.graph.onFraudProtectionLoadStartExternalUsersAuthHandler", 
        "signUp": { 
          "@odata.type": "#microsoft.graph.fraudProtectionProviderConfiguration", 
          "fraudProtectionProvider": { 
            "@odata.type": "#microsoft.graph.HUMANFraudProtectionProvider", 
            "id": "<id-from-previous-step>" 
          } 
        } 
      } 
    }
    ```
3. **[クエリの実行]** を選択します。
4. 要求が成功すると、 **201 Created** 応答が返されます。 アプリケーションのサインアップ フローをテストして、不審なサインアップが検出されたときに HUMAN チャレンジが表示されることを確認します。

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-manage-admin-accounts"} -->
## 管理者アカウントを追加して管理する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-admin-accounts
- Service: entra-external-id / external
- Article date: 2026-04-17
- Summary: Microsoft Entra External IDを使用して外部テナントに管理者アカウントを追加および管理する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

管理者アカウントは、管理者ロールが割り当てられているMicrosoft Entra外部テナントのユーザーです。 テナントに管理者アカウントを追加するには、Microsoft Entra admin centerまたはMicrosoft Graphを使用してユーザーを作成または招待し、管理者ロールを割り当てます。 管理者ロールを割り当てない場合、ユーザーには [既定のユーザー アクセス許可があります](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-user-permissions)。

この記事では、Microsoft Entra admin centerを使用した管理者アカウントの管理に重点を置いています。 ユーザーを追加または削除するには、少なくとも [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) のアクセス許可が必要です。

アプリのエンド ユーザーに関する情報については、「 [コンシューマーおよびビジネスユーザーのユーザー アカウントを管理](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-customer-accounts) する」も参照してください。 通常、これらのユーザーには管理者ロールが割り当てられないため、 [既定のユーザーアクセス許可](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-user-permissions)が保持されます。

### 前提条件

- 独自のMicrosoft Entra外部テナントをまだ作成していない場合は、[今すぐ作成します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
- Microsoft Entra External IDのユーザー アカウントについて説明します。
- リソース アクセスを制御するユーザー ロールについて理解する。

### 管理者アカウントを追加する

新しいユーザー アカウントを作成し、Microsoft Entra ロールを追加してアカウントに管理者アクセス許可を付与するには、次の手順に従います。 (ここでは、必要な手順のみを説明します。すべてのプロパティの詳細については、Microsoft Entra ID記事 [ユーザーの作成方法](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users#create-a-new-user)を参照してください。

1. 少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)として[Microsoft Entra admin center](https://entra.microsoft.com)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**Users** に移動します。
4. [**新しいユーザー**] を選択&gt;**新しいユーザーを作成します**。
5. [ **基本** ] タブの [ **ID**] で、この管理者の情報を入力します。

    - **ユーザー プリンシパル名**: 一意のユーザー名を入力し、@ 記号の後のメニューからドメインを選択します。
    - **表示名**: Chris Green や Chris A. Green など、ユーザーの名前を入力します。
    - **パスワード**: 自動生成されたパスワードをコピーするか、[ **パスワードの自動生成** ] オプションをオフにして別のパスワードを入力します。 初めてサインインするには、管理者にこのパスワードを指定する必要があります。
6. [ **割り当て** ] タブを選択し、次の手順に従ってロールをユーザーに割り当てます。 (グループの追加は省略可能です)。

    - [ **+ ロールの追加] を選択します**。
    - 表示されるメニューから、一覧から最大 20 個のロールを選択します。 ユーザーは、Microsoft Entra IDの 1 つ以上の [administrator ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に割り当てることができます。
    - [選択] ボタンを **選択** します。
7. [ **確認と作成** ] ボタンを選択します。

管理者が作成され、あなたの外部テナントに追加されます。

### 管理者 (ゲスト アカウント) を招待する

テナントを管理するために、新しいゲスト ユーザーを招待することもできます。 管理者アクセス許可を持つ新しいゲスト ユーザーを招待するには、次の手順に従います。

1. 少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)として[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**Users** に移動します。
4. [**新しいユーザー**]&gt;**[外部ユーザーを招待する (プレビュー)]を**選択します。
5. [ **基本** ] タブで、ユーザーの情報を入力します。

    - **電子メール**。 *必須*。 招待するユーザーの電子メール アドレス。
    - **表示名**. 新しいユーザーの名と姓。 たとえば、 *Mary Parker* などです。
    - [ **招待メッセージ**] の下:
        - 招待メールをユーザーに送信する場合は、[ **招待メッセージ** の送信] チェック ボックスをオンにします。 それ以外の場合は、チェック ボックスをオフにします。
        - **[メッセージ]** で、招待メールに含める個人用メッセージを追加します。
        - 招待メールのコピーを他のユーザーに送信するには、[ **CC 受信者** ] テキスト ボックスにメール アドレスを追加します。
        - **招待リダイレクト URL** は既定で MyApplications に設定され、ユーザーは招待を引き換えるときにリダイレクトされます。 別の URL に変更できます。
6. [ **割り当て** ] タブを選択し、次の手順に従ってロールをユーザーに割り当てます。 (グループの追加は省略可能です)。

    - [ **+ ロールの追加] を選択します**。
    - 表示されるメニューから、一覧から最大 20 個のロールを選択します。 ユーザーは、Microsoft Entra IDの 1 つ以上の [administrator ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に割り当てることができます。
    - [選択] ボタンを **選択** します。
7. [ **確認と招待** ] ボタンを選択します。

招待メールがユーザーに送信されます。 ユーザーは、招待に同意すると、サインインできるようになります。

注

外部ユーザーは管理目的でのみ招待できます。 この機能を使用して、アプリにサインインするように顧客を招待することはできません。 [外部ユーザーの招待 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-supported-features-customers#identity-providers-and-authentication-methods) は、顧客 ID およびアクセス管理 (CIAM) ユーザー フローと互換性がありません。

### ロールの割り当てを変更または追加する

ユーザーを作成するか、ゲスト ユーザーを招待するときにロールを割り当てることができます。 ユーザーに対してロールの追加、変更、または削除を実行できます。

1. 少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)として[Microsoft Entra admin center](https://entra.microsoft.com)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**Users** に移動します。
4. ロールを変更するユーザーを選択します。 次に **割り当てられたロール** を選択します。
5. [ **割り当ての追加]** を選択し、割り当てるロール (アプリケーション *管理者*など) を選択し、[ **追加]** を選択します。

### ロールの割り当てを削除する

ユーザーに割り当てたロールを削除する必要がある場合は、次の手順を実行します。

1. 少なくとも「Privileged Role Administrator」として「Microsoft Entra admin center」にサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**Users** に移動します。
4. ロールを変更するユーザーを選択します。 次に **割り当てられたロール** を選択します。
5. 削除するロール ( *アプリケーション管理者*など) を選択し、[ **割り当ての削除**] を選択します。

### 管理者アカウントのロールの割り当てを確認する

監査プロセスの一環として、通常は、顧客ディレクトリ内の特定のロールにどのユーザーが割り当てられているかを確認します。 現在、どのユーザーに特権ロールが割り当てられているかを監査するには、次の手順に従います。

1. [Microsoft Entra admin center](https://entra.microsoft.com)で、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. Entra IDRoles & admins に移動します。
4. **ユーザー管理者**などのロールを選択します。 [ **割り当て]** ページには、そのロールを持つユーザーが一覧表示されます。

### 管理者アカウントを削除する

既存のユーザーを削除するには、少なくとも [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) ロールの割り当てが必要です。 [特権認証管理者は、他の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) を含むすべてのユーザーを削除できます。 "ユーザー管理者" は、管理者以外のユーザーを削除できます。

1. 少なくとも [Microsoft Entra admin center](https://entra.microsoft.com)[特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**Users** に移動します。
4. 削除するユーザーを選びます。
5. [ **削除]** を選択し、[ **はい** ] を選択して削除を確定します。

ユーザーは削除され、[ **すべてのユーザー** ] ページに表示されなくなります。 ユーザーは、次の 30 日間、[ **削除されたユーザー** ] ページに表示され、その間に復元できます。 ユーザーの復元の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-restore) を使用して最近削除されたユーザーを復元または削除する」を参照してください。

### 管理アカウントを保護する

多要素認証 (MFA) を使用して、すべての管理者アカウントを保護することをお勧めします。 MFA は、ユーザーにワンタイム パスコードの入力を求めるサインイン時の ID 検証プロセスです。

Microsoftは、組織に、[Global Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールが永続的に割り当てられた 2 つのクラウド専用緊急アクセス アカウントを持っていることをお勧めします。 このようなアカウントは高い特権を持っており、特定のユーザーには割り当てられません。 アカウントは、通常のアカウントが使用できない、あるいは全ての管理者が誤ってロックアウトされた際の緊急事態または「ブレイクグラス」シナリオに限定されます。これらのアカウントは、[緊急アクセスアカウントの推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)に従って作成する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-manage-customer-accounts"} -->
## 顧客アカウントを追加および管理する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-customer-accounts
- Service: entra-external-id / external
- Article date: 2025-03-10
- Summary: Microsoft Entra 外部 ID で顧客アカウントを追加および管理する方法について説明します。

**適用対象**: [Image: 灰色の X 記号がある白い円。] 従業員テナント [Image: 内側に白いチェック マーク記号がある緑の円。] 外部テナント ([詳細情報](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

コンシューマーとビジネス顧客のユーザー アカウントは、ユーザーがアプリケーションにサインアップするときに最も一般的に作成されます。 ただし、Microsoft Entra 管理センターまたは Microsoft Graph を使用して、ユーザー アカウントを作成することもできます。 コンシューマーとビジネスのお客様はエンド ユーザーと見なされるため、通常は管理者ロールを割り当てないため、既定のユーザーアクセス許可 [保持](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-user-permissions)。

この記事では、Microsoft Entra 管理センターを使用したユーザー アカウントの管理に重点を置いています。 ユーザーを追加または削除するには、少なくとも [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) アクセス許可が必要です。

外部テナント管理者 [ユーザー アカウントの詳細については、「](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-admin-accounts) 管理者アカウントの追加と管理」も参照してください。

### 前提条件

- 独自の Microsoft Entra 外部テナントをまだ作成していない場合は、[今すぐ作成してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
- Microsoft Entra 外部 ID でのユーザー アカウントについて理解している。
- リソース アクセスを制御するユーザー ロールについて理解する。

### 顧客アカウントを作成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. 複数のテナントにアクセスできる場合、上部のメニューの **[設定]** アイコン  を使用し、**[ディレクトリとサブスクリプション]** メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**Users** に移動します。
4. **[新しいユーザー]**&gt;**[新しい外部ユーザーの作成]** の順に選択します。
5. **Identities**の横にあります。
    1. **[サインイン方法]** で、**[メール]** を選択します。
    2. [**値**で、ユーザーの電子メール アドレスを入力します。これはサインイン名になります。
    3. ユーザーに複数のメールアドレスを追加するには、**[+ 追加]** ボタンを選択します。
6. **表示名** (必須) の横に、ユーザーの姓と名を入力します (例: mary Parker )。
7. **[クリップボードにコピー]** ボタンを使って、**[パスワード]** ボックスに表示される自動生成されたパスワードをコピーします。 このパスワードを初めてサインインするユーザーに提供します。
8. **[Review + create](レビュー + 作成)** を選択します。

これで、指定したサインイン方法を使ってユーザーがサインインできるようになりました。

### ユーザーのパスワードをリセットする

管理者は、ユーザーが自分のパスワードを忘れた場合に、ユーザーのパスワードをリセットできます。 ユーザーのパスワードをリセットすると、そのユーザーに対して一時パスワードが自動生成されます。 一時パスワードに期限はありません。 次回ユーザーがサインインすると、一時パスワードが生成されてから経過している時間にかかわらず、パスワードは引き続き機能します。 その後、ユーザーはパスワードを永続的なパスワードに再設定する必要があります。

ユーザーのパスワードをリセットするには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. 複数のテナントにアクセスできる場合、上部のメニューの **[設定]** アイコン  を使用し、**[ディレクトリとサブスクリプション]** メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**Users** に移動します。
4. リセットを必要としているユーザーを検索して選択し、 **[パスワードのリセット]** を選択します。
5. **[パスワードのリセット]** ページで、 **[パスワードのリセット]** を選択します。
6. そのパスワードをコピーして、ユーザーに付与します。 ユーザーは次のサインイン プロセス中にパスワードを変更するように求められます。

### ユーザー アカウントを削除する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. 複数のテナントにアクセスできる場合、上部のメニューの **[設定]** アイコン  を使用し、**[ディレクトリとサブスクリプション]** メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**Users** に移動します。
4. 削除するユーザーを検索して選択します。
5. **[削除]** を選択し、 **[はい]** を選択して削除を確定します。

削除後 30 日以内にユーザーを復元する方法、またはユーザーを完全に削除する方法の詳細については、「[Microsoft Entra ID を使用して最近削除されたユーザーを復元または削除する](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-restore)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-microsoft-accounts-federation-customers"} -->
## 顧客サインイン用に MSA を追加する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-microsoft-accounts-federation-customers
- Service: entra-external-id / external
- Article date: 2026-04-17
- Summary: 外部テナントの ID プロバイダーとして MSA を追加する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

OpenID Connect (OIDC) ID プロバイダーを使用してMicrosoft アカウント (live.com) とのフェデレーションを設定し、それをユーザー フローに追加すると、ユーザーは既存のMicrosoft アカウント (MSA) を使用してアプリケーションにサインアップしてサインインできます。

### [前提条件]

- [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
- [サインアップとサインインのユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)。
- マイクロソフトアカウント (live.com). まだお持ちでない場合は、 https://www.live.com/にサインアップしてください。

注

この機能は、Microsoft アカウント (MSA) にサインアップしたユーザーのみが使用できます。 テナントに招待された [B2B ゲスト ユーザー](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties) は使用できません。

### Microsoft アカウント アプリケーションを作成する

Microsoft アカウントを持つユーザーのサインインを有効にするには、Microsoft Entra ID テナントにアプリケーションを作成する必要があります。 アプリケーションのリソース テナントには、従業員や外部テナントなどの任意のMicrosoft Entra IDテナントを指定できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDアプリの登録新しい登録を選択します。
3. アプリケーションに名前を付 *けます (例: ContosoApp*)。
4. **[サポートされているアカウントの種類]** で、*[任意の組織のディレクトリ (Microsoft Entra ID テナント - マルチテナント) 内のアカウントと、個人用の Microsoft アカウント (Skype、Xbox など)]* を選択します。
5. [ **リダイレクト URI**] で [ **Web** ] を選択し、「 [OpenID Connect ID プロバイダーの設定](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-oidc-federation-customers#set-up-your-openid-connect-identity-provider)」で説明されている設定済みのリダイレクト URI を入力します。
6. [ **登録**] を選択します。

    登録が完了すると、Microsoft Entra 管理センターにアプリ登録の **Overview** ペインが表示されます。 **アプリケーション (クライアント) ID が**表示されます。 後で必要に応じて、この値を記録します。
7. [ **管理**] で[ **証明書とシークレット**] を参照し、[ **新しいクライアント シークレット**] を選択します。
8. シークレットにキー *1* などの名前を付 **け、[追加**] を選択します。
9. 後で必要に応じて、シークレットの **値** を記録します。 ページを離れる前にシークレットを保存してください。 クライアント シークレットの値は、作成直後を除いて表示できません。

#### オプションクレームの設定

*family\_name*や*given\_name*など、アプリケーションに提供する省略可能な要求を構成することもできます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**アプリの登録** に移動します。
3. 前に作成した MSA アプリケーションを選択します。
4. [ **管理**] で、[ **トークンの構成**] を選択します。
5. [ **省略可能な要求の追加]** を選択します。
6. *ID* など、構成するトークンの種類を選択します。
7. 追加する省略可能な要求を選択します。
8. **追加**を選択します。

### OpenID Connect ID プロバイダーとして Microsoft アカウント (live.com) を構成する

Microsoft アカウント (live.com) をアプリケーションとして構成したら、外部テナントで OIDC ID プロバイダーとして設定できます。

1. 少なくとも[外部 ID プロバイダー管理者](https://entra.microsoft.com)として[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator)にサインインします。
2. **Entra ID**&gt;**外部 ID**&gt;**すべての ID プロバイダー** に移動します。
3. [**カスタム**] タブを選択し、[**新規追加**]&gt;**Open ID Connect** を選択します。

    [Image: [カスタム] タブと [Open ID Connect] が選択された [新しい追加] メニューが表示されている [すべての ID プロバイダー] ページのスクリーンショット。]
4. ID プロバイダーの次の詳細を [ **基本** ] タブに入力します。

    - **Display name**: ID プロバイダーの名前を入力します (例: *Microsoft アカウント* この名前は、サインインおよびサインアップ フロー中にユーザーに表示されます。 たとえば、*Microsoft アカウントでサインインする* または *Microsoft アカウントでサインアップする*。
    - 既知のエンドポイント: Microsoft アカウント用の共通のオーソリティ URL の検出 URI である  としてエンドポイント URI を入力してください。
    - **OpenID 発行者 URI**: 発行者 URI を `https://login.live.com`として入力します。
    - **クライアント ID** と **クライアント シークレット**: 前に作成したクライアント シークレットの **アプリケーション (クライアント) ID** と **値** を入力します。
    - **クライアント認証**: **client\_secret** を選択し、スコープに `openid profile email` を追加 **します**。
    - **応答の種類**: **コード**を選択します。
5. **[次へ: 要求マッピング**] を選択して[要求マッピング](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-oidc-claims-mapping-customers)を構成するか、**確認と作成**を選択して ID プロバイダーを追加できます。

    [Image: エンドポイント、発行者、クライアント ID、シークレット、スコープ値など、Microsoft アカウント用に構成された Open ID Connect ID プロバイダーの [基本] タブのスクリーンショット。]

### ユーザーがサインインして ID プロバイダーにサインアップできるようにする

Microsoft アカウントを ID プロバイダーとして構成したら、それをユーザー フローに追加して、ID プロバイダーへのサインインとサインアップを許可します。 [ユーザー フローへの ID プロバイダーの追加を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-add-identity-provider-to-user-flow-customers)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/external-id/customers/how-to-migrate-passwords-just-in-time"} -->
## Just-In-Time パスワードを Microsoft Entra 外部 ID に移行する - Microsoft Entra External ID

- Source: https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-migrate-passwords-just-in-time
- Service: entra-external-id / external
- Article date: 2026-04-03
- Summary: Just-In-Time (JIT) 移行を使用して、別の ID プロバイダーからMicrosoft Entra 外部 IDにパスワードを移行する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このガイドでは、Just-In-Time (JIT) パスワード移行を実装して、ユーザー資格情報をレガシ ID プロバイダーから Microsoft Entra 外部 ID に移行する方法について説明します。 ユーザー ID の管理を担当する開発者または管理者の場合、このガイドは移行プロセスに関連する手順を理解するのに役立ちます。

AZURE AD B2C のお客様で、移行に使用できるオプションをまだ確認していない場合は、「[Azure AD B2C から外部 ID への移行を計画する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/plan-your-migration-from-b2c-to-external-id)を参照してください。

注

レガシ システムのユーザー パスワード (保存時または実行時) にアクセスできる場合は、事前に設定することもできます。 詳細については、「 [ユーザーと資格情報を外部 ID に移行する」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-migrate-users)参照してください。

Important

2025 年 5 月 1 日より、Azure AD B2C は新規のお客様に対して購入できなくなります。 詳細については、[AZURE AD B2C を引き続き購入できますか?](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale)に関する FAQ を参照してください。

### [前提条件]

開始する前に、次のことを確認します。

- **Microsoft Entra 外部 ID テナント**: アクティブな外部 ID テナント。 お持ちでない場合は、「[get started with Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/customers/quickstart-tenant-setup)」を参照してください。
- **従来の ID プロバイダー アクセス**: 既存の ID プロバイダーに対してユーザーの資格情報を検証する機能。
- **開発環境**: 運用環境にデプロイする前に、移行の実装をテストします。
- **次の概念について理解します**。
    - Microsoft Entra ID: ユーザー アカウントの作成と管理
    - [アプリの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app): Microsoft Entra IDでのアプリケーションの登録
    - [Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/user-post-users): プログラムによるユーザーとディレクトリの管理
    - [ディレクトリ拡張機能: ディレクトリ](https://learn.microsoft.com/ja-jp/graph/extensibility-overview) オブジェクトへのカスタム プロパティの追加
    - [Azure Functions](https://learn.microsoft.com/ja-jp/azure/azure-functions/functions-overview): カスタム ロジックをホストするためのサーバーレス コンピューティング
- 次のロールが割り当てられているアカウント:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)
    - [認証機能拡張パスワード管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-extensibility-password-administrator)。 この組み込みロールは、Azure ポータルで使用でき、パスワード移行用のカスタム認証拡張機能を作成および管理するために必要なアクセス許可を付与します。 ロールの割り当ての詳細については、「[assign Microsoft Entra roles](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)」を参照してください。

### JIT 移行プロセスの概要

JIT 移行は、サインイン プロセス中にカスタム API を呼び出して、レガシ ID プロバイダーに対してユーザー資格情報を検証することで機能します。 Microsoft Entra 外部 IDでは、統合を容易にするために、[custom 認証拡張機能](https://learn.microsoft.com/ja-jp/graph/api/resources/customauthenticationextension)を使用して、このプロセスをサポートしています。 これらの拡張機能を使用すると、認証プロセス中に実行されるカスタム ロジックを定義できるため、外部システムと対話し、サインイン フローの一部としてより多くの処理を実行できます。

ユーザーの観点からは、移行は完全にシームレスです。 ユーザーは、レガシ システムの既存の資格情報でサインインします。 資格情報が正しい場合は、正常に認証され、アプリケーションにアクセスできます。 バックグラウンドでパスワードは安全にMicrosoft Entra 外部 IDに移行され、その後のサインインはレガシ システムを呼び出さずに Entra に対して直接認証されます。 この方法では、移行中の中断を最小限に抑え、ユーザーが自分のパスワードをリセットしたり、新しい資格情報を学習したりする必要がなくなります。

注

このプロセスはパスワードの移行を目的としており、パスワードの検証ではありません。 レガシ システムは、最初のサインイン時のパスワードの検証にのみ使用されます。 その後、パスワードはMicrosoft Entra 外部 IDに直接保存され、検証されます。

JIT 移行プロセスを次の図に示します。

[Image: 従来の資格情報を使用したユーザー サインイン、移行フラグのチェック、カスタム API を使用したパスワード検証を示す JIT 移行プロセスの図。]

#### JIT 移行プロセスのしくみ

移行フラグが `true` に設定されたコンシューマー ユーザー アカウントがサインインすると、次のプロセスが発生します。

- **コンシューマー ユーザーがサインイン** する - ユーザーはレガシ ID プロバイダーから資格情報を入力します。
- **移行フラグのチェック**- 入力されたパスワードに応じて、次の 2 つの結果が考えられます。
    - 入力したパスワードがユーザーのレコードのパスワードと一致しない場合、外部 ID はカスタム拡張プロパティをチェックし、移行が必要な場合は OnPasswordSubmit リスナーを呼び出します。
    - パスワードが記録されているパスワードと一致する場合、認証は正常に続行され、ユーザーは自動的に移行済みとしてマークされます。
- **パスワード暗号化** - 外部 ID は公開キー (RSA JWE 形式) を使用してパスワードを暗号化し、プレーンテキストが送信されないようにします。 秘密キーはAzure Key Vaultに残り、関数コードでは公開されません。
- **カスタム拡張機能の呼び出し** - 外部 ID は、暗号化されたペイロード、ユーザー情報、および認証コンテキストを使用してコードを呼び出します。
- **復号化と検証** - 関数は秘密キーを使用してパスワードの暗号化を解除し、レガシ ID プロバイダーに対して資格情報を検証します。
- **応答アクション**- 関数は、次の 4 つのアクションのいずれかを返します。
    - **MigratePassword**: パスワードは有効です。外部 ID はそれを格納し、移行フラグを 〗〗に設定します。 `false`
    - **UpdatePassword**: パスワードは正しいが脆弱です。ユーザーがパスワードをリセットする必要がある
    - **再試行**: パスワードが正しくありません。ユーザーはもう一度試すことができます
    - **ブロック**: 認証がブロックされました (レガシ システムでロックされているアカウントなど)
- **認証の完了** - 成功した場合、ユーザーは認証され、今後のサインインではカスタム拡張機能がバイパスされます。

#### レガシーな複雑性要件との不一致には、disableStrongPassword オプションを使用する

Microsoft Entra 外部 IDは、独自のパスワードの複雑さの要件を適用します。 複雑さのルールが外部 ID と異なるレガシ ID プロバイダーからユーザーを移行する場合、レガシ システムで有効だったパスワードが外部 ID の強力なパスワード ポリシーを満たしていない可能性があります。 既定では、これらのユーザーは `UpdatePassword` フローを介してルーティングされ、資格情報が正しい場合でも、サインイン時にパスワードをリセットする必要があります。

JIT パスワード移行の `disableStrongPassword` オプションによって、この動作が変更されます。 ブラウザー ベースの認証フローやネイティブ認証フローなど、 `OnPasswordSubmit` カスタム認証拡張機能を呼び出すすべてのサインイン サーフェイスに適用されます。

- `disableStrongPassword`が有効になっている場合、既存のパスワードが外部 ID の強力なパスワードの複雑さの規則を満たしていないため、ユーザーはパスワードのリセットを強制されません。
- パスワードの最小文字数は 8 文字のままです。 8 文字未満のパスワードは引き続きリセットが必要です。
- 期限切れのパスワードは引き続きリセットが必要です。 このオプションは、有効期限やその他の資格情報の有効性シグナルではなく、複雑さのチェックのみを緩和します。
- `Retry`や`Block`応答アクションなど、他のすべての認証制御は変更されずに適用されます。

このオプションは、異なる複雑さのルールを持つレガシ ID プロバイダーから大規模にユーザーを移行する場合に、共存期間中に影響を受けるすべてのユーザーに対して強制的にリセットされないようにする場合に使用します。 このオプションは、JIT 移行シナリオにのみ影響します。既に移行されているユーザーや、外部 ID で直接作成された新しいアカウントの認証が弱まることはありません。

**セキュリティのトレードオフ**: このオプションを有効にすると、外部 ID は、ユーザーが次にパスワードを変更するまで、標準の複雑さのルールを満たしていない移行されたパスワードを格納して受け入れます。 JIT 共存期間を時間ボックスに設定し、最終的にパスワードローテーションまたは強制リセットを計画して、保存されているすべての資格情報が最終的に外部 ID ポリシーを満たすようにします。

レガシ プロバイダーの複雑さの規則が外部 ID の強力なパスワード ポリシーと同等または厳格である場合は、 `disableStrongPassword` 無効のままにして、脆弱なパスワードが `UpdatePassword` フローを通じてキャッチおよびリセットされるようにします。

注

`disableStrongPassword`の構成手順は、ステージ 2 とステージ 3 のカスタム認証拡張機能のセットアップと共に維持されます。 運用環境でこのオプションを有効にする前に、JIT 移行機能のドキュメントで現在の構成画面を確認します。

### スロットリングとサービス制限

JIT パスワードの移行は、Microsoft Entraサービスの制限と調整の対象となる[カスタム認証拡張機能](https://learn.microsoft.com/ja-jp/graph/api/resources/customauthenticationextension)に依存します。 大量の移行または大規模な初期サインインウェーブの場合は、ロールアウトをバッチでステージングし、カスタム拡張機能エラーのサインイン テレメトリを監視します。

- 信頼できる制限 (呼び出しごとのタイムアウト、自動再試行、テナントごとの呼び出しの上限) については、[サービスの制限Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-service-limits)参照してください。
- `CustomExtensionThrottlingError`や`CustomExtensionTimedOut`など、拡張機能の呼び出しが失敗または調整されたときに表示されるエラー コードについては、「[カスタム認証拡張機能のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-troubleshoot#error-codes-reference)」を参照してください。

カスタム拡張機能からの発信呼び出し (Microsoft Graphやレガシ ID プロバイダーなど) は、ターゲット サービス独自の調整によって制御され、Microsoft Entra拡張機能の制限の一部ではありません。 これらの一時的なエラーを個別に処理するように関数を設計します。

### ステージ 1: 移行のためのユーザーの準備

JIT 移行を実装する前に、「 [ユーザーと資格情報を外部 ID に移行する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-migrate-users)」のステージ 1 と 2 を完了してください。 これにより、以下のことが指定されます。

- 外部 ID テナント (ステージ 1) にユーザー アカウントが存在します。
- 移行拡張機能プロパティが定義され、各ユーザーに `toBeMigrated: true` (ステージ 2) のフラグが設定されます。

ユーザーの準備ができたら、カスタム認証拡張機能の構成に進みます。

### ステージ 2: カスタム認証拡張機能を構成する

ユーザーを準備したら、認証プロセス中に JIT 移行を有効にするコンポーネントを構成します。 これには、証明書の安全な格納、カスタム拡張機能のホスト、認証拡張機能アプリケーションの構成が含まれます。

#### 2.1 証明書を安全に保存する

Azure Key Vaultで暗号化証明書を作成し、Azure関数のセキュリティで保護されたアクセスを構成します。

##### 2.1.1 マネージド ID を有効にする

Azure関数は、パスワード ペイロードを復号化するために、Key Vaultに格納されている秘密キーにアクセスする必要があります。 証明書を生成する前に、Azure関数のマネージド ID を設定します。

1. [Azure portal](https://portal.azure.com) にサインインし、**Function App** に移動します。
2. 関数アプリを選択します。
3. 左側のメニューの **[設定]** で 、[ **ID] を**選択します。
4. [ **システム割り当て済み** ] タブで、[ **状態]** を **[オン] に**設定します。
5. **保存** を選択します。
6. ID が作成されたら、 **オブジェクト (プリンシパル) ID** の値をコピーします。 次の手順で使用します。

##### 2.1.2 Key Vault アクセス権を付与する

1. Azure ポータルで、Key Vaultに移動します (または、[必要に応じて新しいもの](https://learn.microsoft.com/ja-jp/azure/key-vault/general/quick-create-portal)を作成します)。
2. 左側のメニューの **[設定]** で、[ **アクセス ポリシー**] を選択します。
3. **を選択して**を作成します。
4. [ **シークレットのアクセス許可**] で 、[ **取得**] を選択し、[ **次へ**] を選択します。
5. [ **プリンシパル** ] ページで、前の手順でコピーしたオブジェクト ID を貼り付けます。
6. 検索結果から関数アプリのマネージド ID を選択します。
7. **次へ** を選択して、もう一度 **次へ** を選択します。
8. [ **作成]**を選択して、次のアクセス許可を持つアクセス ポリシーを作成します。
    - **シークレットのアクセス許可**: 取得
    - **プリンシパル**: 関数アプリ名

これらのアクセス許可は、ロール ベースのアクセス制御を使用して付与することもできます。

##### 2.1.3 Azure Key Vaultで証明書を生成する

Azure Key Vaultで暗号化証明書を生成します。 公開キーは外部 ID アプリの登録で構成されますが、秘密キーはKey Vaultのままであり、マネージド ID を使用して Azure 関数によってアクセスされます。

1. Azure ポータルで、Key Vaultに移動します。
2. 左側のメニューの [ **オブジェクト**] で [ **証明書**] を選択します。
3. **[Generate/Import](https://learn.microsoft.com/ja-jp/entra/external-id/customers/生成/インポート)** を選択します。
4. [ **証明書の作成**] ページで、次の値を入力します。
    - **証明書の作成方法**: [ **生成**] を選択します。
    - **証明書名**: **JitMigrationEncryptionCert** を入力します。
    - **証明機関 (CA) の種類**: **自己署名証明書を**選択します。
    - **件名**: **CN=JitMigration** と入力します。
    - **コンテンツ タイプ**: **PKCS #12** を選択します。
5. [ **詳細ポリシーの構成] を**展開し、次の値を入力します。
    - **キーの種類**: **RSA** を選択します。
    - **キー サイズ**: セキュリティを強化するために **2048** または **4096** を選択します。
    - **キーの再利用**: オフのままにします。
    - **エクスポート可能な秘密キー**: このオプションを選択します (関数アクセスに必要)。
6. **を選択して**を作成します。

ヒント

Azure Key Vaultで証明書を直接生成することで、秘密キーがセキュリティで保護された環境から離れることがないようにし、キーの自動ローテーション、アクセス監査、HSM 保護などのエンタープライズ機能を提供します。

#### 2.2 カスタム拡張機能をホストする

レガシ ID プロバイダーに対してユーザー資格情報を検証するAzure関数を作成します。

Important

OnPasswordSubmit カスタム認証拡張機能用に構成されたカスタマー ホステッド エンドポイントは、通常、Azure関数として実装される、カスタマー マネージド HTTPS エンドポイントである必要があります。 このエンドポイントは、サインイン中にMicrosoft Entra 外部 IDによって呼び出され、レガシ ID システムに対してユーザーのパスワードが検証され、移行結果が返されます。

URL は、Microsoft Graph、Microsoft Entra サービス エンドポイント、またはレガシ ID プロバイダーの対話型サインイン URL を指してはなりません。 検証ロジックを実装する Function App 関数エンドポイントを参照する必要があります。 このエンドポイントをセキュリティで保護する必要があります。

##### 2.2.1 要求スキーマ

カスタム認証拡張機能に要求を送信すると、Entra には次のスキーマを含むペイロードが含まれます。 このサンプル ペイロードには、例示のみを目的としたダミー データが含まれています。

```json
{  
  "type": "microsoft.graph.authenticationEvent.passwordSubmit",  
  "source": "/tenants/aaaabbbb-0000-cccc-1111-dddd2222eeee/applications/00001111-aaaa-2222-bbbb-3333cccc4444",  
  "data": {  
    "@odata.type": "microsoft.graph.onPasswordSubmitCalloutData",  
    "tenantId": "aaaabbbb-0000-cccc-1111-dddd2222eeee",  
    "authenticationEventListenerId": "11112222-bbbb-3333-cccc-4444dddd5555",  
    "customAuthenticationExtensionId": "22223333-cccc-4444-dddd-5555eeee6666", 
    "encryptedPasswordContext": "{5-part-JWE}", 
    "authenticationContext": {  
      "correlationId": "aaaa0000-bb11-2222-33cc-444444dddddd",  
      "client": {  
        "ip": "127.0.0.1",  
        "locale": "en-us",  
        "market": "en-us"  
      },  
      "protocol": "OAUTH2.0",  
      "clientServicePrincipal": {  
        "id": "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",  
        "appId": "00001111-aaaa-2222-bbbb-3333cccc4444",  
        "appDisplayName": "My Test application",  
        "displayName": "My Test application"  
      },  
      "user": {  
        "companyName": "Casey Jensen",  
        "createdDateTime": "2023-08-16T00:00:00Z",  
        "displayName": "Casey Jensen",  
        "givenName": "Casey",  
        "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",  
        "mail": "casey@contoso.com",  
        "onPremisesSamAccountName": "Casey Jensen",  
        "onPremisesSecurityIdentifier": "<Enter Security Identifier>",  
        "onPremisesUserPrincipalName": "Casey Jensen",  
        "preferredLanguage": "en-us",  
        "surname": "Jensen",  
        "userPrincipalName": "casey@contoso.com",  
        "userType": "Member"   
      } 
    }  
  }  
} 

```

要求の `encryptedPasswordContext` フィールドには、暗号化された JWE 形式の次の要求が含まれています。

- **user-password**: ユーザーがサインイン時に入力したパスワード テキスト
- **username**: ユーザーがサインイン時に入力したサインイン識別子 (電子メール/ユーザー名)
- **nonce**: 要求に固有であり、検証のために応答に含める必要がある GUID

##### 2.2.2 応答スキーマ

Entra は、カスタム拡張機能からの応答を次の形式で受け取ります。

```json
{  
  "data": {  
    "@odata.type": "microsoft.graph.onPasswordSubmitResponseData",  
    "actions": [  
      {  
        "@odata.type": "microsoft.graph.passwordSubmit.MigratePassword"  
      } 
    ],
    "nonce": "{nonce-value-from-external-id}"
  }  
}  
```

注

外部 ID は、要求ペイロードから encryptedPasswordContext の要求として nonce を送信し、検証のために拡張機能からの応答で要求を受け取ります。

使用可能な各応答アクションは、認証プロセス中の特定のシナリオに対応します。 次の表では、考えられる応答アクションとその使用方法について説明します。

| 応答アクション | Scenario | Entra の動作 |
| --- | --- | --- |
| microsoft.graph.passwordSubmit.MigratePassword | パスワードの検証に成功しました。Entra は認証を続行します。 パスワードが弱い場合は、UpdatePassword フローをトリガーします。 | パスワードが基本的な検証を満たしているが強度要件が満たされない場合に使用します。 |
| microsoft.graph.passwordSubmit.UpdatePassword | パスワードは正しいですが、弱いか期限切れです。 | パスワード リセット フローを介してユーザーをルーティングします。 |
| microsoft.graph.passwordSubmit.Retry | パスワードが正しくありません。 | 許可されている場合、ユーザーが認証を再試行できるようにします。 |
| Microsoft.Graph.パスワード送信.ブロック | 認証はブロックする必要があります。 | アプリによって提供されるカスタム メッセージを含むブロック画面を表示します。 |

##### 2.2.3 テンプレート コード

独自のコードを使用するか、次のサンプル Azure関数をAzure環境にデプロイできます。 この関数例では、次の方法を示します。

- マネージド ID を使用してKey Vaultから暗号化証明書を取得する
- パスワード ペイロードの暗号化を解除する
- レガシ ID プロバイダーに対して資格情報を検証する
- 適切な応答アクションを返す

注

**必須パッケージ**: この関数には、次の NuGet パッケージが必要です。

- **Azure。Identity** (バージョン 1.12.0 以降)
- **Azure。Security.KeyVault.Secrets** (バージョン 4.6.0 以降)
- **Jose-jwt** (JWT 復号化用)
- **Newtonsoft.Json** (JSON 解析用)

**必要なアプリケーション設定**:

- **KeyVaultUrl**: Key Vault URLを入力してください (例: `https://your-keyvault-name.vault.azure.net/`)
- **CiamJitMigrationEncryptionCertName**: Key Vault内の証明書の名前 (例: `JitMigrationEncryptionCert`)

```csharp
using Jose;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Extensions.Http;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Primitives;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Security.Cryptography;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;
using Azure.Identity;
using Azure.Security.KeyVault.Secrets;

namespace dev_functions
{
    public static class JitMigrationEndpoint
    {
        #region Configuration Constants

        /// <summary>
        /// Static cache for RSA private key retrieved from Key Vault
        /// </summary>
        private static RSA _cachedRsa = null;
        private static readonly object _lockObject = new object();

        /// <summary>
        /// Key Vault URL from environment configuration (fetches the environment variable from local.settings.json)
        /// </summary>
        private static readonly string KeyVaultUrl =
            Environment.GetEnvironmentVariable("KeyVaultUrl");

        /// <summary>
        /// Certificate name in Key Vault (fetches the environment variable from local.settings.json)
        /// </summary>
        private static readonly string CertificateName =
            Environment.GetEnvironmentVariable("CiamJitMigrationEncryptionCertName");

        #endregion

        /// <summary>
        /// Retrieves RSA private key from Key Vault certificate stored as secret with caching
        /// </summary>
        private static async Task<RSA> GetRsaFromKeyVaultAsync(ILogger log)
        {
            // Return cached RSA if available
            if (_cachedRsa != null)
            {
                log.LogDebug("Using cached RSA private key");
                return _cachedRsa;
            }

            // Thread-safe retrieval
            lock (_lockObject)
            {
                // Double-check after acquiring lock
                if (_cachedRsa != null)
                    return _cachedRsa;

                try
                {
                    log.LogInformation($"Retrieving certificate '{CertificateName}' from Key Vault");

                    // Validate configuration
                    if (string.IsNullOrEmpty(KeyVaultUrl))
                    {
                        throw new InvalidOperationException(
                            "KeyVaultUrl environment variable is not configured"
                        );
                    }

                    if (string.IsNullOrEmpty(CertificateName))
                    {
                        throw new InvalidOperationException(
                            "CiamJitMigrationEncryptionCertName environment variable is not configured"
                        );
                    }

                    // Use DefaultAzureCredential for authentication
                    var credential = new DefaultAzureCredential();
                    var secretClient = new SecretClient(new Uri(KeyVaultUrl), credential);

                    // Retrieve the certificate stored as a secret (synchronous for thread safety in lock)
                    KeyVaultSecret secret = secretClient.GetSecret(CertificateName);

                    if (string.IsNullOrEmpty(secret.Value))
                    {
                        throw new InvalidOperationException("Secret value is empty");
                    }

                    // Secret value is base64-encoded certificate (PFX/PKCS12)
                    byte[] certBytes = Convert.FromBase64String(secret.Value);

                    // Load certificate with private key
                    X509Certificate2 certificate = new X509Certificate2(
                        certBytes,
                        (string)null, // No password
                        X509KeyStorageFlags.MachineKeySet | X509KeyStorageFlags.Exportable
                    );

                    if (!certificate.HasPrivateKey)
                    {
                        throw new InvalidOperationException(
                            "Certificate does not contain a private key"
                        );
                    }

                    // Extract RSA private key
                    _cachedRsa = certificate.GetRSAPrivateKey();

                    if (_cachedRsa == null)
                    {
                        throw new InvalidOperationException(
                            "Failed to extract RSA private key from certificate"
                        );
                    }

                    log.LogInformation("RSA private key retrieved and cached successfully");
                    return _cachedRsa;
                }
                catch (Exception ex)
                {
                    log.LogError($"Failed to retrieve certificate from Key Vault: {ex.Message}");
                    log.LogError($"Stack trace: {ex.StackTrace}");
                    throw;
                }
            }
        }

        /// <summary>
        /// Main Azure Function entry point for handling JIT migration requests
        /// </summary>
        /// <param name="req">The HTTP request from Entra External ID</param>
        /// <param name="log">Logger instance for tracking execution</param>
        /// <returns>Action result containing the migration response</returns>
        [FunctionName("JitMigrationEndpoint")]
        public static async Task<IActionResult> Run(
            [HttpTrigger(AuthorizationLevel.Anonymous, "get", "post", Route = null)]
            HttpRequest req,
            ILogger log)
        {
            log.LogInformation($"Processing {req.Method} request for JIT migration.");

            // Handle GET requests (health check)
            if (req.Method == HttpMethods.Get)
            {
                log.LogInformation("GET request received. Returning 200 OK for health check.");
                return new OkResult();
            }

            // Validate request body
            if (req.Body == null || req.Body.Length == 0)
            {
                log.LogError("Request body is empty or null.");
                return new BadRequestObjectResult("Request body is required for POST requests.");
            }

            try
            {
                // Parse the incoming request to extract user information
                var userInfo = await ParseRequestAsync(req, log);

                if (userInfo == null)
                {
                    log.LogError("Failed to parse user information from request.");
                    return new BadRequestObjectResult("Failed to parse request body.");
                }

                if (string.IsNullOrEmpty(userInfo.UserId))
                {
                    log.LogError("User ID is missing from the request.");
                    return new BadRequestObjectResult("User ID is required in the authentication context.");
                }

                if (string.IsNullOrEmpty(userInfo.Password))
                {
                    log.LogError("User password is missing from the request.");
                    return new BadRequestObjectResult("User password is required for migration.");
                }

                // Process the response based on legacy system validation
                ResponseContent response = await ProcessResponse(req, userInfo.UserId, userInfo.Password, userInfo.Nonce, log);

                log.LogInformation($"Returning response action: {response.Data.Actions[0].OdataType}.");

                return new OkObjectResult(response);
            }
            catch (Exception ex)
            {
                log.LogError($"Unexpected error during JIT migration processing: {ex.Message}");
                log.LogError($"Stack trace: {ex.StackTrace}");

                // Return a generic error response to avoid exposing internal details
                return new StatusCodeResult(StatusCodes.Status500InternalServerError);
            }
        }

        #region Core Processing Methods

        /// <summary>
        /// Processes the migration response by validating credentials against a legacy authentication system.
        /// This example demonstrates how to integrate with your existing user store to determine migration actions.
        /// </summary>
        /// <param name="req">The HTTP request containing query parameters</param>
        /// <param name="userId">The user ID to validate</param>
        /// <param name="password">The user's password to validate</param>
        /// <param name="nonce">The nonce from the request</param>
        /// <param name="log">Logger instance</param>
        /// <returns>ResponseContent with appropriate action based on legacy system validation</returns>
        private static async Task<ResponseContent> ProcessResponse(HttpRequest req, string userId, string password, string nonce, ILogger log)
        {
            log.LogInformation($"Processing JIT migration response for user: {userId}");

            // TODO: Call your legacy authentication provider here
            //
            // Then based on the response from your legacy provider:
            // - If authentication successful AND password strong: return MigratePassword
            // - If authentication successful BUT password weak: return UpdatePassword  
            // - If authentication failed: return Retry
            // - If system error: return Block

            // PLACEHOLDER: Always return Retry for now
            log.LogInformation("Using placeholder implementation - returning Retry action");

            return CreateResponse(ResponseActionType.Retry, nonce, "Authentication Pending",
                "Please implement legacy authentication integration.");
        }

        /// <summary>
        /// Creates a response with the specified action type and user-facing messages
        /// </summary>
        /// <param name="actionType">The response action type</param>
        /// <param name="nonce">The nonce from the request</param>
        /// <param name="title">User-facing title (optional)</param>
        /// <param name="message">User-facing message (optional)</param>
        /// <returns>ResponseContent with the specified action and messages</returns>
        private static ResponseContent CreateResponse(ResponseActionType actionType, string nonce, string title = null, string message = null)
        {
            var response = new ResponseContent(actionType, nonce);

            if (!string.IsNullOrEmpty(title))
                response.Data.Actions[0].Title = title;

            if (!string.IsNullOrEmpty(message))
                response.Data.Actions[0].Message = message;

            return response;
        }

        /// <summary>
        /// Parses the incoming request to extract user information including ID, email, password, and nonce
        /// </summary>
        private static async Task<PasswordSubmitUserInfo> ParseRequestAsync(HttpRequest req, ILogger log)
        {
            log.LogInformation($"Parsing request from URL: {req.Path}{req.QueryString}");

            string requestBody = await new StreamReader(req.Body).ReadToEndAsync();
            if (string.IsNullOrWhiteSpace(requestBody))
            {
                log.LogError("Request body is empty or whitespace.");
                return null;
            }

            try
            {
                JObject jObject = JObject.Parse(requestBody);
                log.LogDebug($"Parsed request body: {jObject}");

                // Extract user information from authentication context
                string userId = jObject["data"]?["authenticationContext"]?["user"]?["id"]?.ToString();
                string email = jObject["data"]?["authenticationContext"]?["user"]?["mail"]?.ToString();
                string userPrincipalName = jObject["data"]?["authenticationContext"]?["user"]?["userPrincipalName"]?.ToString();

                // Handle both encrypted and plain text password contexts
                string encryptedPasswordContext = jObject["data"]?["encryptedPasswordContext"]?.ToString();

                (string userPassword, string nonce) = await ExtractPasswordAndNonce(encryptedPasswordContext, log);

                log.LogInformation($"Extracted User Info - UserId: {userId}, Email: {email}, UPN: {userPrincipalName}");

                return new PasswordSubmitUserInfo
                {
                    UserId = userId,
                    Email = email,
                    UserPrincipalName = userPrincipalName,
                    Password = userPassword,
                    Nonce = nonce
                };
            }
            catch (JsonReaderException ex)
            {
                log.LogError($"Failed to parse request body as JSON: {ex.Message}");
                return null;
            }
        }

        /// <summary>
        /// Extracts password and nonce from encrypted password context using Key Vault certificate
        /// </summary>
        private static async Task<(string password, string nonce)> ExtractPasswordAndNonce(
            string encryptedContext,
            ILogger log
        )
        {
            try
            {
                log.LogInformation("Starting password and nonce extraction from encrypted context");

                // Validate input
                if (string.IsNullOrEmpty(encryptedContext))
                {
                    log.LogError("Encrypted context is null or empty");
                    return (string.Empty, string.Empty);
                }

                RSA rsa = null;
                try
                {
                    // Get RSA private key from Key Vault (cached after first call)
                    rsa = await GetRsaFromKeyVaultAsync(log);
                    log.LogDebug("RSA private key obtained from Key Vault successfully");
                }
                catch (Exception ex)
                {
                    log.LogError($"Failed to retrieve RSA key from Key Vault: {ex.Message}");
                    return (string.Empty, string.Empty);
                }

                string decryptedPayload;
                try
                {
                    // Decrypt the JWT
                    log.LogDebug("Attempting JWT decryption");
                    decryptedPayload = JWT.Decode(encryptedContext, rsa);
                    log.LogDebug($"JWT decrypted successfully, payload length: {decryptedPayload?.Length ?? 0}");

                    if (string.IsNullOrEmpty(decryptedPayload))
                    {
                        log.LogError("JWT decryption resulted in empty payload");
                        return (string.Empty, string.Empty);
                    }
                }
                catch (Jose.JoseException ex)
                {
                    log.LogError($"Jose JWT library error during decryption: {ex.Message}");
                    return (string.Empty, string.Empty);
                }

                JObject payloadObj;
                try
                {
                    // Parse the decrypted JSON payload
                    log.LogDebug("Parsing decrypted JSON payload");
                    string jsonPayload = Jose.JWT.Decode(decryptedPayload, null, JwsAlgorithm.none);
                    payloadObj = JObject.Parse(jsonPayload);
                    log.LogDebug("JSON payload parsed successfully");
                }
                catch (JsonReaderException ex)
                {
                    log.LogError($"Failed to parse decrypted payload as JSON: {ex.Message}");
                    return (string.Empty, string.Empty);
                }

                // Extract password and nonce from the payload
                string password = payloadObj["user-password"]?.ToString();
                string nonce = payloadObj["nonce"]?.ToString();

                log.LogInformation($"Password extraction: {(string.IsNullOrEmpty(password) ? "FAILED" : "SUCCESS")}");
                log.LogInformation($"Nonce extraction: {(string.IsNullOrEmpty(nonce) ? "FAILED" : "SUCCESS")}");

                return (password ?? string.Empty, nonce ?? string.Empty);
            }
            catch (Exception ex)
            {
                log.LogError($"Critical error in ExtractPasswordAndNonce: {ex.Message}");
                log.LogError($"Stack trace: {ex.StackTrace}");

                // Return empty values to allow the function to continue with fallback behavior
                return (string.Empty, string.Empty);
            }
        }

        #endregion

        #region Response Models

        /// <summary>
        /// Root response object for JIT migration
        /// </summary>
        public class ResponseContent
        {
            [JsonProperty("data")]
            public Data Data { get; set; }

            public ResponseContent(ResponseActionType actionType, string nonce = null)
            {
                Data = new Data(actionType, nonce);
            }
        }

        /// <summary>
        /// Data payload containing actions and metadata
        /// </summary>
        public class Data
        {
            [JsonProperty("@odata.type")]
            public string OdataType { get; set; }

            [JsonProperty("actions")]
            public List<ActionItem> Actions { get; set; }

            [JsonProperty("nonce")]
            public string Nonce { get; set; }

            public Data(ResponseActionType actionType, string nonce = null)
            {
                OdataType = "microsoft.graph.onPasswordSubmitResponseData";
                Nonce = nonce;
                Actions = new List<ActionItem> { new ActionItem(actionType) };
            }
        }

        /// <summary>
        /// Individual action item in the response
        /// </summary>
        public class ActionItem
        {
            [JsonProperty("@odata.type")]
            public string OdataType { get; set; }

            [JsonProperty("title", DefaultValueHandling = DefaultValueHandling.Ignore)]
            public string Title { get; set; }

            [JsonProperty("message", DefaultValueHandling = DefaultValueHandling.Ignore)]
            public string Message { get; set; }

            public ActionItem(ResponseActionType type)
            {
                OdataType = type switch
                {
                    ResponseActionType.MigratePassword => "microsoft.graph.passwordsubmit.MigratePassword",
                    ResponseActionType.UpdatePassword => "microsoft.graph.passwordsubmit.UpdatePassword",
                    ResponseActionType.Block => "microsoft.graph.passwordsubmit.Block",
                    ResponseActionType.Retry => "microsoft.graph.passwordsubmit.Retry",
                    _ => throw new ArgumentOutOfRangeException(nameof(type), type, null)
                };

                // Set user-facing messages for block actions
                if (type == ResponseActionType.Block)
                {
                    Title = "Sign-in blocked";
                    Message = "Admin has blocked your sign-in attempt. Please contact support.";
                }
            }
        }

        /// <summary>
        /// Available response action types for JIT migration
        /// </summary>
        public enum ResponseActionType
        {
            /// <summary>
            /// Migrate the user's password to Azure AD
            /// </summary>
            MigratePassword,

            /// <summary>
            /// Update the user's existing password
            /// </summary>
            UpdatePassword,

            /// <summary>
            /// Block the user's sign-in attempt
            /// </summary>
            Block,

            /// <summary>
            /// Retry the authentication process
            /// </summary>
            Retry
        }

        /// <summary>
        /// Data Transfer Object containing user information extracted from password submit request
        /// </summary>
        public class PasswordSubmitUserInfo
        {
            /// <summary>
            /// The user's unique identifier from the authentication context
            /// </summary>
            public string UserId { get; set; }

            /// <summary>
            /// The user's email address from the authentication context
            /// </summary>
            public string Email { get; set; }

            /// <summary>
            /// The user's User Principal Name from the authentication context
            /// </summary>
            public string UserPrincipalName { get; set; }

            /// <summary>
            /// The user's password extracted from the encrypted context
            /// </summary>
            public string Password { get; set; }

            /// <summary>
            /// The nonce value from the encrypted context
            /// </summary>
            public string Nonce { get; set; }
        }

        #endregion
    }
}
```

##### 2.2.4 関数をデプロイする

関数コードを構成したら、Visual Studio 2022 を使用してAzureにデプロイします。

1. ソリューション エクスプローラーで、関数プロジェクトを右クリックします。
2. **公開**を選択します。
3. 発行プロファイルを設定していない場合:
    1. **Azure** を選択し、 **Next** を選択します。
    2. **Azure Function App (Windows)** を選択し、 **Next** を選択します。
    3. メッセージが表示されたら、Azure アカウントにサインインします。
    4. サブスクリプションと関数アプリを選択します。
    5. **完了** を選択します。
4. **[発行]** ページで **[発行]** を選択します。
5. デプロイが完了するまで待ちます。 デプロイが完了すると、"発行に成功しました" というメッセージが表示されます。

### ステージ 3: カスタム拡張機能アプリケーションを構成する

カスタム認証拡張機能を表すアプリケーション登録を作成し、暗号化証明書で構成します。

#### 3.1 EEID 認証拡張機能アプリケーションを作成する

カスタム認証拡張機能を表すアプリケーション登録を作成します。 このアプリケーションは、Microsoft Entra 外部 IDと Azure 関数の間の呼び出しを認証します。 アプリの登録に関する一般的なガイダンスについては、「 [アプリケーションの登録」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)参照してください。

次の設定を使用して、[Microsoft Entra 管理センター](https://entra.microsoft.com/) に新しいアプリケーションを登録します。

- **名前**: JIT 移行拡張機能のわかりやすい名前を入力します
- **サポートされているアカウントの種類**: この組織のディレクトリ内のアカウントのみ

##### 3.1.1 識別子 URI の作成

識別子 URI は、カスタム認証拡張機能 API を一意に識別します。 プレースホルダーを実際の値に置き換えて、アプリケーション マニフェストに次のコードを追加します。

```json
"identifierUris": [  
    "api://[Function_URL_Hostname]/[App_ID]"  
], 
```

- **Function\_URL\_Hostname**: Azure関数のホスト名 (例: `contoso.azurewebsites.net`)
- **App\_ID**: アプリ登録 **の [概要** ] ページのアプリケーション (クライアント) ID

例: `"api://contoso.azurewebsites.net/aaaabbbb-0000-cccc-1111-dddd2222eeee"`

##### 3.1.2 API のアクセス許可

カスタム認証拡張機能では、認証イベントから HTTP 要求を受信するために、 `CustomAuthenticationExtension.Receive.Payload` アプリケーションのアクセス許可が必要です。 このアクセス許可を設定するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)で、**Entra ID**&gt;**アプリの登録** に移動し、カスタム認証拡張機能アプリケーションを選択します。
2. API の**アクセス許可**に移動します&gt;**アクセス許可を追加します**。
3. **Microsoft Graph**&gt;**アプリケーションのアクセス許可**を選択します。
4. **CustomAuthenticationExtension.Receive.Payload** を検索して選択し、[**アクセス許可の追加**] をクリックします。
5. 最後に、[ **テナント] に管理者の同意を与** える] をクリックしてアクセス許可を付与します。

#### 3.2 暗号化キーを構成する

カスタム拡張機能に送信する前に、外部 ID がパスワード ペイロードを暗号化できるように、証明書の公開キーを構成します。

##### 3.2.1 公開キーをエクスポートする

1. Key Vaultの **Certificates** で、**JitMigrationEncryptionCert** を選択します。
2. 現在のバージョン (GUID として表示) を選択します。
3. **[CER 形式でダウンロード]** を選択します。
4. ファイルを保存します (例: `jitmigrationencryptioncert.cer`)。
5. PowerShell を開きます。
6. ファイル パスを証明書の場所に置き換えて、次のコマンドを実行します。

    ```powershell
    $cert = New-Object System.Security.Cryptography.X509Certificates.X509Certificate2("{path-to-your-cer-file}\jitmigrationencryptioncert.cer")
    $certBase64 = [Convert]::ToBase64String($cert.RawData)
    $certBase64
    ```
7. 出力から base64 でエンコードされた公開キーをコピーします。

##### 3.2.2 アプリケーションにキーを追加する

Microsoft Graph APIを使用して、カスタム認証拡張機能アプリの登録で証明書を構成します。 `keyId`と`tokenEncryptionKeyId`の両方に同じ GUID を使用していることを確認します。 次の情報を使用して PATCH 要求を行います。

プレースホルダーを実際の値に置き換えます。

- `{object-id}`: カスタム認証拡張機能アプリのオブジェクト ID (アプリケーション ID ではありません)。
- `{end-date}`: 証明書の有効期限 ( `2026-11-25T17:44:47Z`など)。
- `{key-guid}`: 新しい GUID。 PowerShell を使用して生成できます: `[guid]::NewGuid()`。
- `{start-date}`: 証明書の開始日 (例: `2025-11-25T17:44:47Z`)。
- `{base64-encoded-public-key}`: 前の手順の base64 文字列。

```http
PATCH https://graph.microsoft.com/v1.0/applications/{object-id}
Content-Type: application/json
Authorization: Bearer {access-token}

{
  "keyCredentials": [
    {
      "endDateTime": "{end-date}",
      "keyId": "{key-guid}",
      "startDateTime": "{start-date}",
      "type": "AsymmetricX509Cert",
      "usage": "Encrypt",
      "key": "{base64-encoded-public-key}",
      "displayName": "CN=JitMigration"
    }
  ],
  "tokenEncryptionKeyId": "{key-guid}"
}
```

この構成では、カスタム拡張機能のパスワード コンテキストを暗号化するときに公開キーを使用するように外部 ID に指示します。

#### 3.3 カスタム拡張機能ポリシー

ユーザーのサインイン時に JIT 移行プロセスを実行する方法を定義するカスタム拡張機能ポリシーを作成します。 このポリシーは、サインイン プロセス中にカスタム認証アプリケーションを呼び出します。

次の例を使用して、Microsoft Graph API を使用してカスタム拡張機能ポリシーを作成します。

```http
POST https://graph.microsoft.com/beta/identity/customAuthenticationExtensions 
{   
    "@odata.type": "#microsoft.graph.onPasswordSubmitCustomExtension",   
    "displayName": "OnPasswordSubmitCustomExtension",   
    "description": "Validate password",   
    "endpointConfiguration": {   
        "@odata.type": "#microsoft.graph.httpRequestEndpoint",   
        "targetUrl": "{extension-url-from-create-custom-extension-section}"   
    },   
    "authenticationConfiguration": {   
        "@odata.type": "#microsoft.graph.azureAdTokenAuthentication",   
        "resourceId": "{identifierUri-used-in-above-section}"   
    } , 
    "clientConfiguration": { 
       "timeoutInMilliseconds": 2000, 
       "maximumRetries": 1 
   }, 
}  
```

### ステージ 4: レガシ アプリを外部 ID に切り替える

カスタム認証拡張機能を構成したら、クライアント アプリケーションを登録し、JIT 移行をアクティブにするリスナー ポリシーを作成して、運用カットオーバーの準備をします。

#### 4.1 アプリを作成する

JIT 移行プロセスをテストするためのクライアント アプリケーションを登録します。 このアプリケーションは、サインイン中にカスタム認証拡張機能をトリガーします。 アプリケーションの登録の詳細な手順については、「 [クイック スタート: アプリケーションを登録する」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)参照してください。

次の JIT 移行固有の設定で新しい Web アプリケーションを登録します。

- **リダイレクト URI**: テスト用に `https://jwt.ms` (Web プラットフォーム) に設定
- **認証**: 暗黙的な許可フローとハイブリッド フローで **ID トークン** を有効にする
- **API アクセス許可**: **User.Read** (委任されたMicrosoft Graphアクセス許可) に管理者の同意を付与します。 アクセス許可が既に存在する場合でも、適切な構成のために同意を再付与する必要があります。

ヒント

テスト用に構成されている場合は、既存のアプリケーション登録を使用できます。

#### 4.2 リスナー ポリシーを作成する

カスタム拡張機能ポリシーをクライアント アプリケーションにリンクするリスナー ポリシーを作成します。 このポリシーにより、ユーザーのサインイン時にカスタム認証拡張機能が確実に呼び出されます。

Microsoft Graph API を使用してリスナー ポリシーを作成するには、次の例を使用します。 このポリシーにより、クライアント アプリケーションがカスタム認証拡張機能に関連付けられます。

これらのプレースホルダーを、構成の次の情報に置き換えます。

- *App\_ID*: 登録したクライアント アプリケーションのアプリケーション ID。 アプリケーション ID は、アプリケーションの **[概要]** ページで確認できます。
- *migrationPropertyId*: ユーザーの移行状態を追跡するために、このチュートリアルで前に作成した拡張機能属性 ID。
- *customExtensionObjectId*: このチュートリアルで前に作成したカスタム認証拡張機能のポリシー ID。

```http
POST https://graph.microsoft.com/beta/identity/authenticationEventListeners 

Content-type: application/json  

{  
    "@odata.type": "#microsoft.graph.onPasswordSubmitListener",  
    "conditions": {  
        "applications": {  
            "includeAllApplications": false,  
            "includeApplications": [  
                {  
                    "appId": "{client-appid-you-created-above }"  
                }  
            ]  
        }  
    },  
    "priority": 500,  
    "handler": {  
        "@odata.type": "#microsoft.graph.onPasswordMigrationCustomExtensionHandler",  
        "migrationPropertyId": "{migrationPropertyId}",  
        "customExtension": {  
            "id": "{customExtensionObjectId}"  
        }  
    }  
}  
```

### ステージ 5: 運用環境にデプロイする前にテストと検証を行う

JIT 移行を運用環境にデプロイする前に、実装を十分にテストして、正しく安全に動作することを確認してください。 カスタム拡張機能のテストの詳細については、「 [カスタム認証拡張機能をテストする」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration#step-5-test-the-application)。

次のテスト チェックリストを検討してください。

- **ユーザーのサブセットを使用してテスト**する: ユーザー ベース全体を移行する前に、テスト ユーザーの少数のグループから開始します。
- **資格情報の検証**: Azure関数がレガシ ID プロバイダーに対して資格情報を正しく検証していることを確認します。
- **移行フラグの更新を確認**する: 移行が成功した後、移行フラグが `false` に設定されていることを確認します。
- **さまざまな応答アクションをテスト**する: すべての応答アクション (MigratePassword、UpdatePassword、Retry、Block) が期待どおりに動作することを確認します。
- **Monitor Azure関数ログ**: 認証プロセス中のエラーや問題を特定するためにログを確認します。
- **暗号化を検証**する: パスワードがエンドツーエンドで暗号化され、ログやエラー メッセージで公開されないようにします。

テスト中に問題が発生した場合は、一般的な問題と解決策に関するガイダンスについては、 [カスタム認証拡張機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-troubleshoot) のトラブルシューティングを参照してください。

### 既知の問題

**ネイティブ認証でのパスワードの複雑さの不一致**: ネイティブ認証フロー中に、ユーザーが従来の ID プロバイダーに従って正しいパスワードを入力したが、外部 ID パスワードの複雑さの標準では脆弱と見なされた場合、SSPR にリダイレクトするのではなく、エラーが返されます。

これは、レガシ パスワードが外部 ID 要件を満たしていないユーザーにのみ影響します。 既に外部 ID 標準を満たしているパスワードを持つユーザーは、追加のプロンプトなしでシームレスに移行できます。 レガシ パスワードが外部 ID の複雑さの規則を満たしていないが有効なユーザーに対してパスワードのリセットを強制しないようにするには、「 従来の複雑さの不一致に対して disableStrongPassword オプションを使用する」を参照してください。

### よく寄せられる質問

#### OnPasswordSubmit カスタム認証拡張機能には、どの URL を使用する必要がありますか?

レガシ ID システムに対してパスワードを検証する、カスタマー ホステッド HTTPS エンドポイント (通常は Azure 関数) を使用します。 URL は、Microsoft Graph、Microsoft Entra サービス、またはレガシ ID プロバイダーの対話型サインイン エンドポイントを参照してはなりません。 検証ロジックを実装する Function App 関数エンドポイントを指す必要があります。

#### オンプレミスの属性がユーザー スキーマに表示される理由

外部 ID は、オンプレミスの属性を含む共有Microsoft Entraユーザー モデルを使用します。 外部 ID では、これらの属性は読み取り専用であり、JIT パスワードの移行中の ID 照合やライトバックには使用されません。 ユーザー解決は、UPN や電子メールなどの構成済みのサインイン識別子を使用して、サインイン フローの前に行われます。

#### JIT 移行コンポーネントをデプロイする必要がある場所

制限付きの RBAC を使用して、安全な ID サブスクリプションに JIT 移行コンポーネントをデプロイします。 管理アクセスを厳密に制御して、顧客がホストする認証ロジックに対する未承認の変更を防ぎます。 この分離は、認証フローとユーザー アカウントを侵害から保護するのに役立ちます。

### 完全な検証と移行

JIT パスワード移行のセットアップとテストが完了したら、移行ガイドに戻り、エンドツーエンドの認証フローを検証し、アプリケーションのカットオーバーを計画します。 [ステージ 4: カットオーバーの検証、監視、計画](https://learn.microsoft.com/ja-jp/entra/external-id/customers/migrate-from-b2c-to-external-id#stage-4-validate-monitor-and-plan-cutover)。
<!-- /MSL-PAGE -->
