# Microsoft Learn — Microsoft Entra / ID ガバナンス (PIM・アクセスレビュー等) (part 2)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 63

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-access-package-resources"} -->
## エンタイトルメント管理でaccess パッケージのリソース ロールを変更する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources
- Service: entra-id-governance / entitlement-management
- Article date: 2025-06-25
- Summary: エンタイトルメント管理で既存のaccess パッケージのリソース ロールを変更する方法について説明します。

アクセスパッケージマネージャーとして、ユーザーのアクセスを新しいリソースに割り当てたり、前のリソースからアクセスを削除したりすることを心配することなく、いつでもアクセスパッケージ内のリソースを変更できます。 この記事では、既存のaccess パッケージのリソース ロールを変更する方法について説明します。

このビデオでは、access パッケージを変更する方法の概要について説明します。

### リソースのカタログの確認

グループやアプリなどのリソースをaccess パッケージに追加する必要がある場合は、必要なリソースがaccess パッケージのカタログで使用できるかどうかを確認する必要があります。 access package managerの場合、リソースを所有している場合でも、カタログにリソースを追加することはできません。 カタログで利用可能なリソースの使用に制限されます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Identity Governance Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) としてサインインします。

    ヒント

    このタスクを完了できるその他の最小限の特権ロールには、カタログ所有者とAccess package managerが含まれます。
2. **ID ガバナンス**&gt;**権限管理**&gt;**アクセスパッケージ**に移動します。
3. **Access パッケージ** ページで、チェックするaccess パッケージを開き、カタログに必要なリソースがあることを確認します。
4. 左側のメニューで、[ **カタログ** ] を選択し、カタログを開きます。
5. 左側のメニューで[ **リソース** ]を選択すると、このカタログ内のリソースの一覧が表示されます。

    [Image: カタログ内のリソースのリスト]
6. リソースがまだカタログに存在せず、管理者またはカタログ所有者である場合は、 [カタログにリソースを追加](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#add-resources-to-a-catalog)できます。 追加できるリソースの種類は、グループ、ディレクトリに統合されたアプリケーション、および SharePoint Online サイトです。 次に例を示します。

    - グループは、クラウドで作成されたMicrosoft 365 グループまたはクラウドで作成された Microsoft Entra セキュリティ グループにすることができます。 オンプレミスの Active Directoryで生成されたグループは、所有者またはメンバーの属性をMicrosoft Entra IDで変更できないため、リソースとして割り当てることはできません。 AD セキュリティ グループ メンバーシップを利用するアプリケーションに ID にアクセス権を付与するには、Microsoft Entra ID で新しいグループを作成し、[グループ ライトバックを AD に構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)し、[そのグループが AD に書き込まれるように設定します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-group-writeback)。 Exchange Online で配布グループとして作成されたグループは、Microsoft Entra IDでも変更できません。
    - アプリケーションには、サービスとしてのソフトウェア (SaaS) アプリケーション、別のディレクトリまたはデータベースを使用するオンプレミス アプリケーション、Microsoft Entra IDと統合された独自のアプリケーションなど、Microsoft Entra エンタープライズ アプリケーションを使用できます。 アプリケーションが Microsoft Entra ディレクトリにまだ統合されていない場合は、環境内のアプリケーションの[govern access](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-prepare) および [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-integrate) を使用してアプリケーションを統合する方法に関する説明を参照してください。
    - サイトとしては、SharePoint Online サイトまたは SharePoint Online サイト コレクションを指定できます。
7. access package managerであり、カタログにリソースを追加する必要がある場合は、カタログ所有者に追加を依頼できます。

### access パッケージに含めるリソース ロールを決定する

リソース ロールは、リソースに関連付けられ、定義付けられたアクセス許可を集めたものです。 各カタログのリソースから「access パッケージ」にリソースロールを追加すると、IDを割り当てるためのリソースが利用可能になります。 グループ、チーム、アプリケーション、および SharePoint サイトによって提供されるリソース ロールを追加できます。 ユーザーは、access パッケージへの割り当てを受け取ると、access パッケージ内のすべてのリソース ロールに追加されます。

access パッケージの割り当てが失われると、access パッケージ内のすべてのリソース ロールから削除されます。

注

ID がエンタイトルメント管理の外部のリソースに追加され、後でaccessパッケージの割り当てを受け取り、そのaccessパッケージの割り当てが期限切れになった場合でも、accessを保持する必要がある場合は、リソース ロールをaccess パッケージに追加しないでください。

一部の ID が他の ID とは異なるリソース ロールを受け取る場合は、カタログに複数のaccess パッケージを作成し、リソース ロールごとに個別のaccess パッケージを作成する必要があります。 たとえば、エージェント ID に API アクセス許可を割り当てる場合、メンバーまたはゲスト ユーザーには API アクセス許可を割り当てることができないので、メンバーまたはゲスト ユーザーとは別のアクセス パッケージに含める必要があります。 また、アクセス パッケージを互いに[非互換性](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-incompatible)としてマークすることで、過剰なアクセスを与えるパッケージへのアクセスを ID が要求できないようにすることもできます。

特に、アプリケーションは複数のアプリ ロールを持つことができます。 アプリケーションのアプリ ロールをリソース ロールとして access パッケージに追加する場合、そのアプリケーションに複数のアプリ ロールがある場合は、access パッケージ内のそれらの ID に適切なロールを指定する必要があります。

注

アプリケーションに複数のアプリ ロールがあり、そのアプリケーションの複数のロールが access パッケージ内にある場合、ユーザーはそれらのアプリケーションに含まれるすべてのロールを受け取ります。 代わりに、ID にアプリケーションのロールの一部のみを含める場合は、カタログに複数のaccess パッケージを作成し、アプリ ロールごとに個別のaccess パッケージを作成する必要があります。

さらに、アプリケーションは、アクセス許可を表すためにセキュリティ グループに依存することもできます。 たとえば、アプリケーションには 1 つのアプリ ロール `User` があり、2 つのグループ (`Ordinary Users` グループと `Administrative Access` グループ) のメンバーシップも確認できます。 アプリケーションのユーザーは、これら 2 つのグループのうちの 1 つだけのメンバーである必要があります。 ID がいずれかのアクセス許可を要求できるように構成する場合は、アプリケーション、グループ `Ordinary Users`、グループ `Administrative Access`の 3 つのリソースをカタログに配置します。 次に、そのカタログに 2 つの access パッケージを作成し、各 access パッケージが他のパッケージと[互換性がない](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-incompatible#scenarios-for-separation-of-duties-checks)ことを示します。

- アプリケーションのアプリ ロール `User` とグループ `Ordinary Users` のメンバーシップの 2 つのリソース ロールを持つ最初のaccess パッケージ
- アプリケーションのアプリ ロール `User` とグループ `Administrative Access` のメンバーシップの 2 つのリソース ロールを持つ 2 つ目のaccess パッケージ

### リソース ロールに ID が既に割り当てられているかどうかを確認する

リソース ロールが管理者によってaccess パッケージに追加されると、そのリソース ロールに既に属しているが、access パッケージへの割り当てがない ID はリソース ロールに残りますが、access パッケージには割り当てられません。 たとえば、ID がグループのメンバーであり、access パッケージが作成され、そのグループのメンバー ロールがaccess パッケージに追加された場合、ID はaccess パッケージへの割り当てを自動的に受け取りません。

リソース ロール メンバーシップを持つ ID を access パッケージにも割り当てるには、Microsoft Entra 管理センターを使用してアクセス パッケージに ID を直接割り当てるか、Graph または PowerShell を使用して一括で割り当てることができます。 アクセス パッケージに割り当てた ID は、アクセス パッケージ内の他のリソース ロールへのアクセスも受け取ります。 ただし、リソース ロールに属していた ID は、access パッケージに追加される前に既にaccessしているため、access パッケージの割り当てが削除されると、そのリソース ロールから削除されます。

### リソースロールの追加

注

Microsoft Entra ロールをカタログに追加するには、Global Administratorまたはカタログ所有者アクセス許可を持つ特権ロール管理者である必要があります。 Microsoft Entra ロールがカタログに追加されると、ID ガバナンス管理者とAccess パッケージ マネージャーは、その Microsoft Entra ロールを含むaccess パッケージを作成できます。また、access パッケージを管理するアクセス許可を持つ他の ID は、その Microsoft Entra ロールに ID を割り当てることができます。 同様に、EntitlementManagement.RW.All アクセス許可を持つアプリケーションは、必要なエンタイトルメント管理アクセス許可に加えて、グローバル管理者または特権ロール管理者のロールも持っていない限り、Microsoft Entra ロールをカタログに追加できません。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Identity Governance Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) としてサインインします。

    ヒント

    このタスクを完了できるその他の最小特権ロールには、カタログ所有者とAccess package managerが含まれます。
2. **ID ガバナンス**&gt;**権限管理**&gt;**アクセスパッケージ**に移動します。
3. **Access パッケージ** ページで、リソース ロールを追加するaccess パッケージを開きます。
4. 左側のメニューで、[ **リソース ロール**] を選択します。
5. **[リソース ロールの追加** を選択して、access パッケージへのリソース ロールの追加] ページを開きます。

    [Image: Access パッケージ - リソース ロールの追加]
6. グループまたはチームのメンバーシップ、アプリケーションへのアクセス、SharePoint サイト、Microsoft Entra ロール (プレビュー)、API アクセス許可、または SAP IAG アクセス権 (プレビュー)のいずれかを追加する場合、次のいずれかのリソースロールセクションの手順を実行します。

### グループまたはチームのリソース ロールを追加する

エンタイトルメント管理では、access パッケージが割り当てられているときに、Microsoft Teamsのグループまたはチームに ID を自動的に追加できます。

- グループまたはチームのメンバーシップがaccess パッケージの一部であるリソース ロールであり、そのaccess パッケージにユーザーが割り当てられている場合、ユーザーはメンバーとしてそのグループまたはチームに追加されます (まだ存在しない場合)。
- ユーザーのaccess パッケージの割り当てが期限切れになると、同じグループまたはチームを含む別のaccess パッケージに現在割り当てがない限り、ユーザーはグループまたはチームから削除されます。

任意の[Microsoft Entra セキュリティ グループまたは Microsoft 365 グループ](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)を選択できます。 グループを管理できる管理者ロールの ID は、任意のグループをカタログに追加できます。カタログ所有者は、グループの所有者である場合、カタログに任意のグループを追加できます。 グループを選択する際は、次の Microsoft Entra の制約に注意してください。

- メンバーとしてグループまたはチームに追加されたユーザー (ゲストを含む) は、そのグループまたはチームの他のすべてのメンバーを表示できます。
- Microsoft Entra ID、Microsoft Entra Connect を使用して Windows Server Active Directoryから同期されたグループ、または配布グループとして Exchange Online で作成されたグループのメンバーシップを変更することはできません。 AD セキュリティ グループを使用するアプリケーションへのaccessを管理する場合は、「[エンタイトルメント管理を使用してグループ の書き戻しを設定する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-group-writeback)を参照してください。
- 動的メンバーシップ グループはメンバーの追加または削除によって更新することはできないため、エンタイトルメント管理での使用には適していません。
- Microsoft 365 groups、[管理者向けのMicrosoft 365 グループの概要](https://learn.microsoft.com/ja-jp/microsoft-365/admin/create-groups/office-365-groups)で説明されている追加の制約があります。これには、グループごとの所有者数の制限、グループの会話を同時にaccessできるメンバーの数の制限、メンバーあたり 7,000 グループが含まれます。

詳細については、「[Compare groups](https://learn.microsoft.com/ja-jp/office365/admin/create-groups/compare-groups) および [Microsoft 365 グループ および Microsoft Teams](https://learn.microsoft.com/ja-jp/microsoftteams/office-365-groups) を参照してください。

1. **リソース ロールをアクセス パッケージに追加** ページで、**グループおよびチーム** を選択して、[グループの選択] ウィンドウを開きます。
2. access パッケージに含めるグループとチームを選択します。

    [Image: Access パッケージ - リソース ロールの追加 - グループの選択]
3. **選択**

    グループまたはチームを選択すると、[ **サブタイプ]** 列に次のいずれかのサブタイプが一覧表示されます。

    | サブタイプ | 説明 |
    | --- | --- |
    | セキュリティ | リソースにアクセスを付与するために使用されます。 |
    | 流通 | ユーザー グループに通知を送信するために使用されます。 |
    | Microsoft 365 | Microsoft 365 Teams 対応ではないグループ。 社内外の両方の ID 間のコラボレーションに使用されます。 |
    | チーム | Teams 対応のMicrosoft 365 グループ。 社内外の両方の ID 間のコラボレーションに使用されます。 |
4. [ **ロール** ] ボックスの一覧で、割り当てるロールを選択します。 [グループがPrivileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-discover-groups)によって管理されている場合は、**Eligible 所有者**や**Eligible メンバー**などの資格のあるメンバーシップも選択できます。

    通常は [メンバー] ロールを選択します。 所有者ロールを選択すると、ID がグループの所有者になり、それらの ID で他のメンバーまたは所有者を追加または削除できるようになります。

    アクセス パッケージ内のグループ リソースに対して PIM に割り当てられる使用可能なロールのスクリーンショットです。
5. **追加**を選択します。

    追加された後、アクセス パッケージに既存の割り当てがあるユーザー ID は、自動的にこのグループまたはチームのメンバー（または所有者）になります。 詳細については、 変更が適用されるタイミングを参照してください。

注

アクセス パッケージの有効期限が PIM マネージド グループの "*Expire eligible assignments after*" ポリシー設定を超えた場合、エンタイトルメント管理 (EM) と Privileged Identity Management の間に不一致が発生し、EM が割り当てられていることを示している間に ID がアクセスを失う可能性があります。 詳細については、「[Privileged Identity Management管理下のグループを使用する際のアクセスパッケージリファレンス](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-pim-reference)」を参照してください。

### アプリケーションのリソース ロールを追加する

アクセス パッケージがユーザーに割り当てられると、Microsoft Entra IDは、SaaSアプリケーションやオンプレミスアプリケーション、Microsoft Entra IDと統合された組織のアプリケーションを含むMicrosoft EntraエンタープライズアプリケーションへのユーザーIDのアクセスを自動的に割り当てることができます。 フェデレーション シングル サインオンを使用してMicrosoft Entra IDと統合するアプリケーションの場合、Microsoft Entra IDは、アプリケーションに割り当てられた ID のフェデレーション トークンを発行します。

アプリケーションが Microsoft Entra ディレクトリにまだ統合されていない場合は、環境内のアプリケーションの[govern access](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-prepare) および [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-integrate) を使用してアプリケーションを統合する方法に関する説明を参照してください。

アプリケーションは、マニフェストで複数のアプリ ロールを定義し、 [アプリ ロール UI](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) を介して管理できます。 アプリケーションのアプリ ロールをリソース ロールとして access パッケージに追加する場合、そのアプリケーションに複数のアプリ ロールがある場合は、そのaccess パッケージ内のそれらの ID に適切なロールを指定する必要があります。 アプリケーションを開発している場合は、「 [方法: エンタープライズ アプリケーションの SAML トークンで発行されたロール要求を構成する」](https://learn.microsoft.com/ja-jp/entra/identity-platform/enterprise-app-role-management)で、これらのロールをアプリケーションに追加する方法の詳細を確認できます。 Microsoft 認証ライブラリを使用している場合は、access controlにアプリ ロールを使用する方法に関する [code サンプル](https://learn.microsoft.com/ja-jp/entra/identity-platform/sample-v2-code)もあります。

注

アプリケーションに複数のアプリ ロールがあり、そのアプリケーションの複数のロールが access パッケージ内にある場合、ユーザーはそれらのアプリケーションに含まれるすべてのロールを受け取ります。 代わりに、ID にアプリケーションのロールの一部のみを含める場合は、カタログに複数のaccess パッケージを作成し、アプリ ロールごとに個別のaccess パッケージを作成する必要があります。

アプリ ロールが access パッケージのリソースになったら:

- ユーザーがそのaccess パッケージに割り当てられると、そのユーザーがまだ存在しない場合は、そのアプリ ロールに追加されます。 アプリケーションに属性が必要な場合は、要求から収集された属性の値がユーザーに書き込まれます。
- ユーザーのaccess パッケージの割り当てが期限切れになると、そのアプリ ロールを含む別のaccess パッケージへの割り当てがない限り、ユーザーのaccessはアプリケーションから削除されます。 アプリケーションで必要な属性の場合、それらの属性はユーザーから削除されます。

アプリケーションを選択する際は、次の点を考慮します。

- アプリケーションでは、アプリ ロールにグループを割り当てることもできます。 アプリケーションとそのロールの代わりにaccess パッケージにグループを追加することもできますが、アプリケーションはマイ Access ポータルのaccess パッケージの一部としてユーザーに表示されません。
- Microsoft Entra 管理センターでは、アプリケーションとして選択できないサービスのサービス プリンシパルを表示することもできます。 特に、**Exchange Online** と **SharePoint Online** はサービスであり、ディレクトリにリソース ロールを持つアプリケーションではないため、access パッケージに含めることはできません。 代わりに、グループベースのライセンスを使用して、それらのサービスにaccessする必要があるユーザーに適切なライセンスを確立します。
- 認証で個人の Microsoft アカウント ユーザーのみをサポートし、ディレクトリ内の組織アカウントをサポートしていないアプリケーションには、アプリケーション ロールがなく、access パッケージ カタログに追加できません。
- access パッケージがエージェント ID またはサービス プリンシパル用の場合は、アプリケーションがそれらの ID からの対話をサポートしていることを確認します。 アプリケーションが OAuth アクセス許可を持つ API を提供する場合は、アプリ ロールを追加するのではなく、API アクセス許可を access パッケージに追加します。 詳細については、 [アプリケーションへのエージェント ID の割り当ての管理を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-agent-identities-to-applications)参照してください。

1. **リソース ロールを accessパッケージに追加する** ページで、**Applications** を選択して、[アプリケーションの選択] ウィンドウを開きます。
2. access パッケージに含めるアプリケーションを選択します。

    [Image: Access パッケージ - リソース ロールの追加 - アプリケーションの選択]
3. **選択**
4. [ **ロール** ] ボックスの一覧で、アプリ ロールを選択します。

    [Image: Access パッケージ - アプリケーションのリソース ロールを追加します]
5. **追加**を選択します。

    access パッケージに既存の割り当てを持つIDは、このアプリケーションが追加されると、自動的にアクセス権が付与されます。 詳細については、 変更が適用されるタイミングを参照してください。

### SharePoint サイトのリソース ロールを追加する

Microsoft Entra IDは、アクセス パッケージが割り当てられたときに、IDへのアクセスをSharePoint OnlineサイトまたはSharePoint Onlineサイトコレクションに自動的に割り当てることができます。

1. **リソース ロールをアクセス パッケージに追加** ページで、**SharePoint サイト**を選択して、[ SharePoint Online サイトの選択 ] ウィンドウを開きます。

    [Image: Access パッケージ - リソース ロールの追加 - SharePoint サイトの選択 - ポータル ビュー]
2. access パッケージに含める SharePoint Online サイトを選択します。

    [Image: Access パッケージ - リソース ロールの追加 - SharePoint Online サイトの選択]
3. **選択**
4. **役割**の一覧で、SharePoint Online サイトの役割を選択します。

    [Image: Access パッケージ - SharePoint Online サイトのリソース ロールを追加します]

    多数のロールを持つ SharePoint Online サイトの場合は、検索ボックスを使用して、アクセス パッケージに追加するロールを見つけます。 検索では、使用可能なすべてのロールが最初に一覧に表示されない場合でも、一致するSharePointロールが返されます。
5. **追加**を選択します。

    アクセスパッケージに既存の割り当てがあるIDは、追加されると自動的にこのSharePoint Onlineサイトへのアクセスが付与されます。 詳細については、 変更が適用されるタイミングを参照してください。

### Microsoft Entra のロール割り当てを追加する

ID に組織のリソースをaccessするための追加のアクセス許可が必要な場合は、access パッケージを通じて Microsoft Entra ロールを割り当てることで、それらのアクセス許可を管理できます。 エンタイトルメント管理を使用して、Microsoft Entra ロールを従業員やゲストに割り当てることで、ユーザーのエンタイトルメントを確認して、どのロールがそのユーザーに割り当てられているかをすぐに判断できます。 access パッケージにリソースとして Microsoft Entra ロールを含める場合、そのロールの割り当てが **eligible** または **active** かどうかを指定することもできます。

access パッケージを使用して Microsoft Entra ロールを割り当てることは、ロールの割り当てを大規模に効率的に管理し、ロールの割り当てのライフサイクルを改善するのに役立ちます。

注

Privileged Identity Managementを使用して、特権が必要なタスクを実行するために、必要な時にアクセスをユーザーに提供することを推奨します。 これらのアクセス許可は、Microsoft Entra 組み込みロールのドキュメントで "特権" としてタグ付けされた Microsoft Entra ロールを通じて提供されます。 エンタイトルメント管理は、Microsoft Entra ロールを含め、ユーザーが業務を遂行するために必要なリソースのバンドルを割り当てるのに適しています。 アクセスパッケージに割り当てられたユーザーは、リソースに対してより長期間のアクセスを持つ傾向があります。 Privileged Identity Managementを使用して高い特権を持つロールを管理することをお勧めしますが、エンタイトルメント管理のaccess パッケージを使用して、それらのロールの資格を設定できます。

access パッケージにリソースとして Microsoft Entra ロールを含めるには、次の手順に従います。

1. カタログ所有者のアクセス許可を持つ [Microsoft Entra 管理センター](https://entra.microsoft.com)に[Global Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)または[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**アクセス パッケージ** に移動します。
3. [Access パッケージ] ページで、リソース ロールを追加するaccess パッケージを開き、**Resource roles** を選択します。
4. **[リソースのロールをアクセス パッケージに追加する] ページ** で、**[Microsoft Entra ロール (プレビュー)]** を選んで [Microsoft Entra ロールの選択] ペインを開きます。
5. access パッケージに含める Microsoft Entra ロールを選択します。 [Image: アクセスパッケージのロールを選択するスクリーンショット]
6. [ **ロール** ] ボックスの一覧で、[ **資格のあるメンバー** ] または [ **アクティブ なメンバー**] を選択します。 [Image: アクセス パッケージでリソース ロールの役割を選択する画面のスクリーンショット]
7. **追加**を選択します。

注

**Eligible** を選択すると、ID はそのロールの対象となり、Microsoft Entra 管理センターのPrivileged Identity Managementを使用して割り当てをアクティブ化できます。 **Active** を選択した場合、アイデンティティは、アクセス パッケージにアクセスしなくなるまで、アクティブな役割の割り当てを持ちます。 *"特権"* としてタグ付けされている Entra ロールの場合は、[**対象]** のみを選択できます。 特権ロールの一覧については、[Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)を参照してください。

プログラムで Microsoft Entra ロールを追加するには、「[Access パッケージ内のリソースとして Microsoft Entra ロールをプログラムで追加する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-roles#add-a-microsoft-entra-role-as-a-resource-in-an-access-package-programmatically)を参照してください。

### API アクセス許可を追加する

このリソース ロールは、Microsoft Entra エージェント ID の一部として、サービス プリンシパルまたはエージェント ID に API アクセス許可を割り当てるために使用されます。

API アクセス許可の場合、リソース所有権の検証は、アクセス パッケージへのアクセス許可のオンボード中と、アクセス パッケージへのアクセス許可の追加時の両方で行われます。 この追加の検証により、承認されたリソース所有者のみがアクセス パッケージを通じて API アクセス許可へのアクセスを導入または拡張できるようになります。

エージェント ID [にMicrosoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を使用するには、次のいずれかのライセンス プランが必要です。

- **Microsoft 365 E7** (エージェント 365 とMicrosoft Entra スイートを含む) は、ユーザー ID とエージェント ID のガバナンスを提供します。
- **Microsoftエージェント 365** ライセンスは、少なくとも Microsoft Entra P1 または Microsoft 365 E3 とペアリングされています。

詳細については、「[Microsoft Agent 365 のプランと価格](https://www.microsoft.com/microsoft-agent-365#plans-and-pricing)」を参照してください。 エージェント固有の機能の完全な一覧については、**Microsoft Entra ID ガバナンス ライセンス表**の [Microsoft Agent 365](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals) 列を参照してください。

access パッケージに API アクセス許可を含める前に、ユーザーが API アクセス許可を受け取ることができないので、access パッケージ ポリシーのスコープが、すべてのサービス プリンシパルまたはすべてのエージェント ID に設定されていることを確認してください。 次に、[ **API のアクセス許可**] を選択します。 API を提供するソース アプリケーション (Microsoft Graph、別の Microsoft 機能、または組織が使用する API) を、組織独自のアプリケーションから選択します。 Microsoft Graphを選択した場合は、エージェントに委任されたアクセス許可とアプリケーションアクセス許可のどちらを必要とするかを選択します。 次に、必要なアクセス許可のチェック ボックスをオンにし、[ **アクセス許可の更新**] を選択します。

[Image: アクセス パッケージにリソース ロールとして API アクセス許可を追加するスクリーンショット]

注

エージェントの自律的な性質と潜在的なリスクにより、特定のリスクの高い Microsoft Graph API アクセス許可は、エージェントが機密データへの誤用や意図しないaccessを防ぐために明示的にブロックされます。 [Microsoft Graphエージェントに対してブロックされるアクセス許可](https://learn.microsoft.com/ja-jp/graph/api/resources/agentid-platform-overview?view=graph-rest-beta&preserve-view=true#microsoft-graph-permissions-blocked-for-agents)に一覧表示されているアクセス許可をエージェント ID に割り当てることはできません。

### SAP IAGアクセス権限を追加する (プレビュー)

SAP IAG と統合し、SAP IAG をリソースとしてカタログに追加したら、SAP IAG のアクセス権限を選択してアクセスパッケージに含めることができます。

1. [リソース ロール] タブで、SAP IAG を選択します。
2. リソース テーブルで、access パッケージに含める特定のビジネス ロールを選択し、**Next** を選択できます。 [Image: SAP IAG リソースのロールの設定のスクリーンショット。]

### プログラムによるリソース ロールの追加

Microsoft GraphとMicrosoft Graph用の PowerShell コマンドレットを使用して、access パッケージにリソース ロールをプログラムで追加する方法は 2 つあります。

#### Microsoft Graphを使用してaccess パッケージにリソース ロールを追加する

Microsoft Graphを使用して、access パッケージにリソース ロールを追加できます。 委任された `EntitlementManagement.ReadWrite.All` アクセス許可を持つアプリケーションを有する適切なロールのユーザーは、API を呼び出して、次のことを行うことができます。

1. [カタログ内のリソースを一覧表示](https://learn.microsoft.com/ja-jp/graph/api/accesspackagecatalog-list-resources?view=graph-rest-1.0&tabs=http&preserve-view=true) し、カタログにまだ含まれていないリソースの [accessPackageResourceRequest を作成](https://learn.microsoft.com/ja-jp/graph/api/entitlementmanagement-post-resourcerequests?view=graph-rest-1.0&tabs=http&preserve-view=true) します。
2. [カタログ内の各リソースのロールとスコープを取得します](https://learn.microsoft.com/ja-jp/graph/api/accesspackagecatalog-list-resources?view=graph-rest-1.0&tabs=http&preserve-view=true#example-2-retrieve-the-roles-and-scopes-of-a-single-resource-in-a-catalog)。 このロールの一覧は、後で resourceRoleScope を作成するときにロールを選択するために使用されます。
3. [access パッケージに必要なリソース ロールごとに resourceRoleScope](https://learn.microsoft.com/ja-jp/graph/api/accesspackage-post-resourcerolescopes?view=graph-rest-1.0&preserve-view=true) を作成します。

#### Microsoft PowerShell を使用してaccess パッケージにリソース ロールを追加する

また、[Microsoft Graph Identity Governance 用 PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph.Identity.Governance/) モジュール バージョン 2.1.x 以降のモジュール バージョンのコマンドレットを使用して、PowerShell のaccess パッケージにリソース ロールを追加することもできます。

まず、access パッケージに含めるカタログの ID と、そのカタログ内のリソースとそのスコープとロールを取得します。 次の例のようなスクリプトを使用します。 これは、カタログに 1 つのアプリケーション リソースがあることを前提としています。

```powershell
Connect-MgGraph -Scopes "EntitlementManagement.ReadWrite.All"

$catalog = Get-MgEntitlementManagementCatalog -Filter "displayName eq 'Marketing'" -All
if ($catalog -eq $null) { throw "catalog not found" }
$rsc = Get-MgEntitlementManagementCatalogResource -AccessPackageCatalogId $catalog.id -Filter "originSystem eq 'AadApplication'" -ExpandProperty scopes
if ($rsc -eq $null) { throw "resource not found" }
$filt = "(id eq '" + $rsc.Id + "')"
$rrs = Get-MgEntitlementManagementCatalogResource -AccessPackageCatalogId $catalog.id -Filter $filt -ExpandProperty roles,scopes
```

次に、そのリソースから access パッケージにリソース ロールを割り当てます。 たとえば、前に返されたリソースの最初のリソース ロールを access パッケージのリソース ロールとして含める場合は、次のようなスクリプトを使用します。

```powershell
$apid = "00001111-aaaa-2222-bbbb-3333cccc4444"

$rparams = @{
    role = @{
        id =  $rrs.Roles[0].Id
        displayName =  $rrs.Roles[0].DisplayName
        description =  $rrs.Roles[0].Description
        originSystem =  $rrs.Roles[0].OriginSystem
        originId =  $rrs.Roles[0].OriginId
        resource = @{
            id = $rrs.Id
            originId = $rrs.OriginId
            originSystem = $rrs.OriginSystem
        }
    }
    scope = @{
        id = $rsc.Scopes[0].Id
        originId = $rsc.Scopes[0].OriginId
        originSystem = $rsc.Scopes[0].OriginSystem
    }
}

New-MgEntitlementManagementAccessPackageResourceRoleScope -AccessPackageId $apid -BodyParameter $rparams
```

ロールに ID がない場合は、要求ペイロードに `id` 構造体の `role` パラメーターを含めないでください。

詳細については、「[PowerShell を使用して単一のロールを持つアプリケーションのエンタイトルメント管理でaccess パッケージを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create-app)を参照してください。

### リソース ロールを削除する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Identity Governance Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) としてサインインします。

    ヒント

    このタスクを完了できるその他の最小限の特権ロールには、カタログ所有者とAccess package managerが含まれます。
2. **ID ガバナンス**&gt;**権限管理**&gt;**アクセスパッケージ**に移動します。
3. **Access パッケージ** ページで、リソース ロールを削除するaccess パッケージを開きます。
4. 左側のメニューで、[ **リソース ロール**] を選択します。
5. リソース ロールの一覧で、削除するリソース ロールを見つけます。
6. 省略記号 (**...**) を選択し、[ **リソース ロールの削除**] を選択します。

    access パッケージに既存の割り当てを持つ ID は、リソースの役割が削除されると、そのアクセスが自動的に取り消されます。

### 変更が適用される場合

エンタイトルメント管理では、Microsoft Entra IDは、access パッケージ内の割り当てとリソースの一括変更を 1 日に数回処理します。 そのため、割り当てを行うか、access パッケージのリソース ロールを変更した場合、その変更がMicrosoft Entra IDで行われるまでに最大 24 時間かかる場合があります。また、それらの変更を他の Microsoft Online Services または接続された SaaS アプリケーションに反映するのにかかる時間がかかることがあります。 変更が少数のオブジェクトにのみ影響する場合、変更がMicrosoft Entra IDに適用されるまでに数分しかかからなくなる可能性があります。その後、他の Microsoft Entra コンポーネントがその変更を検出し、SaaS アプリケーションを更新します。 変更が何千ものオブジェクトに影響する場合、変更にはさらに長い時間がかかります。 たとえば、2 つのアプリケーションと 100 個のユーザー割り当てを持つaccess パッケージがあり、Access パッケージに SharePoint サイトの役割を追加することにした場合、すべての ID がその SharePoint サイトロールに含まれるまで遅延が発生する可能性があります。 Microsoft Entra 監査ログ、Microsoft Entra プロビジョニング ログ、および SharePoint サイトの監査ログで、進行状況を監視することができます。

チームのメンバーを削除すると、Microsoft 365 グループからも削除されます。 チームのチャット機能から削除されるタイミングは遅れる場合があります。 詳細については、「 [グループ メンバーシップ](https://learn.microsoft.com/ja-jp/microsoftteams/office-365-groups#group-membership)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-access-package-settings"} -->
## エンタイトルメント管理でアクセス パッケージを要求するリンクを共有する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-settings
- Service: entra-id-governance / entitlement-management
- Article date: 2025-06-25
- Summary: エンタイトルメント管理でアクセス パッケージを要求するリンクを共有する方法について説明します。

この記事では、マイ アクセス ポータルでユーザーをアクセス パッケージの要求フローに直接誘導するために使用する特定のアクセス パッケージのリンクを共有する方法について説明します。これにより、マイ アクセス ポータル内でユーザーがそのアクセス パッケージを見つけて選択する必要がなくなります。

アクセス パッケージを作成すると、既定で検出可能になります。 つまり、ポリシーでユーザーによるアクセス パッケージの要求が許可されている場合は、マイ アクセス ポータルにアクセス パッケージが自動的に一覧表示されます。 ただし、[非表示] 設定を変更して、アクセス パッケージがユーザーのマイ アクセス ポータルに一覧表示されないようにすることもできます。 この記事で説明しているマイ アクセス ポータルへ直接移動できるリンクがある場合にのみ、ユーザーは非表示のアクセス パッケージを表示できます。 詳細については、「[エンタイトルメント管理でアクセス パッケージを非表示または削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-edit)」を参照してください。

ユーザーには、マイ アクセス ポータル内にある特定のテナントからのアクセス パッケージのみが表示されます。 この記事で説明するリンクには、マイアクセスポータルが正しいテナントに対して確実にロードされるテナントヒントが含まれています。 ユーザーが URL にテナント ヒントが含まれていないマイ アクセス ポータルにアクセスしている場合は、マイ アクセス ポータルの右上にある組織とテナントのスイッチャーを使用することもできます。

別のテナントの外部ユーザーがマイ アクセス ポータル リンクを使用してアクセス パッケージを要求するには、アクセス パッケージのカタログを[外部ユーザーに対して有効にする](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create)必要があるとともに、アクセス パッケージに[外部ユーザーのディレクトリのポリシー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy)が設定されている必要があります。

### リンクを共有してアクセス パッケージを要求する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に [Identity Governance 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)以上としてサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者とアクセス パッケージ マネージャーがあります。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**アクセスパッケージ**に移動します。
3. **[アクセス パッケージ]** ページで、アクセス パッケージを要求するためのリンクを共有するアクセス パッケージを開きます。
4. [概要] ページで、**[非表示]** 設定をチェックします。 **[非表示]** 設定が **[はい]** の場合は、マイ アクセス ポータルのリンクを持っていないユーザーでも、アクセス パッケージを参照して要求できます。 アクセス パッケージを参照させたくない場合は、設定を **[いいえ]** に変更します。
5. [概要] ページで、**[My Access portal link]\(マイ アクセス ポータルのリンク)** をコピーします。

    [Image: アクセス パッケージの概要 - マイ アクセス ポータルのリンク]

    マイ アクセス ポータルのリンクを内部のビジネス パートナーに送信するときは、リンク全体をコピーすることが大切です。 そうすることで、パートナーが確実にディレクトリのポータルにアクセスして要求を行うことができます。 リンクは `myaccess` で始まり、ディレクトリ ヒントを含み、アクセス パッケージ ID で終了します。 米国政府の場合、マイ アクセス ポータルのリンクのドメインは `myaccess.microsoft.us` になります。

    `https://myaccess.microsoft.com/@<directory_hint>#/access-packages/<access_package_id>`
6. リンクを外部のビジネス パートナーにメールで送信します。 これらの人たちは、自分の組織内のユーザーとリンクを共有して、アクセス パッケージを要求できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-access-package-visibility"} -->
## マイ アクセス ポータルでのアクセス パッケージの可視性について - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-visibility
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2025-06-12
- Summary: マイ アクセス ポータルでのアクセス パッケージの可視性について説明する概念的な記事。

Von Bedeutung

2025 年 7 月に、"特定のユーザーとグループ" を対象とするアクセス パッケージの表示動作が変更されると発表しました。 アクセス パッケージの可視性に関して以前に発表された変更は取り消されました。 現時点では、アクションは必要ありません。

[マイ アクセス ポータル](https://myaccess.microsoft.com)は、ユーザーが Microsoft Entra 内のリソースへのアクセスを要求、承認、およびレビューするための中心的な場所です。 管理者向けに、Microsoft Entra 管理センターには追加の機能が用意されており、アクセス パッケージの構成とアクセス レビューを実施できます。

Microsoft Entra でリソースへのアクセスを管理する場合は、 [マイ アクセス ポータル](https://myaccess.microsoft.com) でユーザーにアクセス パッケージがどのように表示されるかを理解することが不可欠です。 アクセス パッケージの可視性は、ユーザーが検出して要求できるパッケージを決定し、いくつかの構成設定と計画された変更の影響を受けます。 この記事では、マイ アクセス ポータルでアクセス パッケージの可視性を制御する要因の詳細な概要を説明し、現在どのように機能するかについて説明し、2025 年 10 月 10 日に有効な重要な変更を強調します。

### 要求可能なアクセス パッケージを検出する

ユーザーが [*利用可能]* タブに移動し、要求可能なパッケージを検索するか、[*すべて表示]* を選択すると、Microsoft Entra は、表示できるアクセス パッケージと要求する可能性のあるアクセス パッケージを評価します。 この可視性は、特定の一連のチェックによって決定されます。

拡大するために選択できる次のフロー図は、特定のユーザーの参照/検索ビューにアクセス パッケージが表示されるかどうかを判断するために使用される現在のロジックを示しています。

[Image: 10 月の変更前のアクセス パッケージの可視性の図。]

**現在の可視性フローの説明**

この図のロジックは次のとおりです。

1. **カタログは有効になっていますか?** 最初に、アクセス パッケージを含むカタログが有効になっているかどうかを確認します。 カタログ全体が無効になっている場合、そのパッケージは検出に表示されません。
2. **エンド ユーザーは外部ユーザーですか?** ユーザーが外部ユーザーか内部ユーザーかがシステムによってチェックされます。 これは次の手順に影響します。

    - **(外部ユーザーの場合)カタログは外部ユーザーに対して有効になっていますか?** 外部ユーザーの場合は、その設定で外部ユーザーに対 **しても** カタログを有効にする必要があります。 そうでない場合、外部ユーザーにはこのカタログのパッケージは表示されません。 内部ユーザーはこのチェックをスキップします。
3. **アクセス パッケージは非表示になっていますか?** これにより、アクセス パッケージのプロパティ ([編集] の下) で特定の "非表示" 設定が直接チェックされます。 [はい] に設定すると、ポリシーに関係なく、パッケージは参照/検索ビューに表示されません。
4. **"要求できるユーザー" がエンド ユーザーと一致するアクセス パッケージに対して、少なくとも 1 つの有効なポリシーが存在しますか?** これが最後の重要なポリシー チェックです。 システムは、次のすべての条件を満たすアクセス パッケージに関連付けられている *少なくとも 1 つのポリシー* を検索します。

    1. ポリシーの "*アクセス権を取得できるユーザー*" 設定には、ID、グループ メンバーシップ、または接続されている組織の所属に基づいて、現在のユーザーが論理的に含まれます。 [なし (管理者の直接割り当てのみ)]に設定されているポリシーでは、マイ アクセス ポータルにパッケージが表示されません。
    2. ユーザーが自分でアクセスを要求できるように、ポリシーの [*アクセスを要求できる*ユーザー] 設定で [自己] オプションをオンにする必要があります。 上司が直属の部下の 1 つにアクセスを要求しようとしている場合は、[マネージャー] オプションをオンにする必要があります。

**これらすべてのチェック**に合格すると、アクセス パッケージはユーザーの参照/検索ビューに表示されます。 それ以外の場合は、表示されません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-access-reviews-create"} -->
## エンタイトルメント管理でのアクセス パッケージに対するアクセス レビューを作成する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-reviews-create
- Service: entra-id-governance / entitlement-management
- Article date: 2026-03-12
- Summary: Microsoft Entra の一部である Microsoft Entra ID でエンタイトルメント管理アクセス パッケージのポリシーにアクセス レビューを設定する方法について説明します。

古くなったアクセス権のリスクを軽減するには、エンタイトルメント管理で、アクセス パッケージに対するアクティブな割り当てを持つユーザーの定期的レビューを有効にする必要があります。 レビューを有効にできるのは、新しいアクセス パッケージを作成するとき、または既存のアクセス パッケージ割り当てポリシーを編集するときです。 この記事では、アクセス パッケージのアクセス レビューを有効にする方法について説明します。

### 前提条件

この機能を使用するには、Microsoft Entra ID ガバナンスまたは Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、 [Microsoft Entra ID ガバナンス のライセンスの基礎を](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)参照してください。

### アクセス パッケージのレビューを作成する

[新しいアクセス パッケージを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)するとき、または既存のアクセス パッケージ[の割り当てポリシー ポリシーを編集するときに、アクセス レビューを](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-lifecycle-policy)有効にすることができます。 複数のポリシーがある場合、アクセスを要求するユーザーのコミュニティごとに、ポリシーごとに独立したアクセス レビュー スケジュールを設定できます。 アクセス パッケージ割り当てのアクセス レビューを有効にするには、次の手順に従います。

1. 少なくとも [ID ガバナンス管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。
2. **ID ガバナンス**&gt;**アクセスレビュー**&gt;**アクセスパッケージ**に移動します。
3. 新しいアクセス パッケージを作成するには、[ **新しいアクセス パッケージ**] を選択します。
4. 既存のアクセス ポリシーを編集するには、左側のメニューで [ **アクセス パッケージ** ] を選択し、編集するアクセス パッケージを開きます。 次に、左側のメニューで [ **ポリシー**] を選択し、編集するライフサイクル設定を含むポリシーを選択します。
5. アクセス パッケージの割り当てポリシーの [ **ライフサイクル** ] タブを開き、アクセス パッケージへのユーザーの割り当ての有効期限を指定します。 ユーザーが割り当てを延長できるかどうかを指定することもできます。
6. [ **有効期限** ] セクションで、[Access パッケージの割り当ての有効期限] を **[日付]**、[ **日数**]、[ **時間数**]、[ **なし**] に設定します。

    **[日付] で**、将来の有効期限を選択します。

    [ **日数**] には、0 ~ 3660 日の数値を指定します。

    [ **時間数**] には、時間数を指定します。

    選択内容に基づき、アクセス パッケージに対するユーザーの割り当ては、特定の日付または承認後の特定の日数の経過後に期限切れになるか、または期限切れになりません。

    [Image: アクセス パッケージ - ライフサイクルの有効期限の設定]
7. [ **有効期限の詳細設定を表示** ] を選択して、他の設定を表示します。
8. ユーザーが自分の割り当てを拡張できるようにするには、[ **ユーザーがアクセスを拡張することを許可する]** を **[はい**] に設定します。

    ポリシーで拡張機能が許可されている場合、ユーザーはアクセス パッケージの割り当てが期限切れに設定される 14 日前と 1 日前に電子メールを受け取り、割り当てを延長するように求められます。 ユーザーは、延長を求めるときに、引き続きポリシーのスコープ内にいる必要があります。 また、ポリシーで割り当ての明示的な終了日が設定されていて、ユーザーがアクセス権の延長を求める要求を送信する場合、アクセス パッケージへのアクセス権をユーザーに付与する際に使用したポリシーに定義されているように、要求内の延長の日付は割り当ての有効期限が切れる日か、それより前とする必要があります。 たとえば、割り当ての有効期限が 6 月 30 日に切れることがポリシーで示されている場合、ユーザーが要求できる最大の延長は 6 月 30 日です。

    ユーザーのアクセスが延長された場合、そのユーザーは、指定された延長日付 (ポリシーを作成したユーザーのタイムゾーンで設定された日付) より後にアクセス パッケージを要求することはできません。
9. 拡張機能を付与するために承認を要求するには、[延長を **許可するには承認を要求** する] を **[はい**] に設定します。

    [要求] タブに指定されたのと同じ承認設定が使用されます。
10. 次に、[ **アクセス レビューを要求する** ] トグルを **[はい**] に移動します。

    [Image: アクセス レビューを追加する]
11. [開始日] の横にあるレビューの開始日 **を指定します**。
12. 次に、[ **レビュー** 頻度] を **[年単位**]、[ **年 2 回**]、[ **四半期ごと]** 、または **[月単位]** に設定します。 この設定は、アクセス レビューを実行する頻度を決定します。
13. **[期間**] を設定して、定期的な系列の各レビューがレビュー担当者からの入力に対して開く日数を定義します。 たとえば、1 月 1 日に始まり、レビューのために 30 日間開いている年次レビューをスケジュールして、レビュー担当者がその月の終わりまで対応できるようにすることができます。
14. **レビュー担当者**の横で、ユーザーが自分のアクセス レビューを実行する場合は **[自己レビュー**] を選択し、レビュー担当者を指定する場合は [**特定の校閲**者] を選択します。 レビュー担当者 **のマネージャー** をレビュー担当者に指定する場合は、[マネージャー] を選択することもできます。 このオプションを選択した場合、管理者がシステムで見つからない場合に備えて、レビューを転送するための **フォールバック** を追加する必要があります。
15. **[特定の校閲者**] を選択した場合は、アクセス レビューを実行するユーザーを指定します。

    [Image: [レビュー担当者の追加] を選択する]

    1. [ **レビュー担当者の追加] を選択します**。
    2. [ **校閲者の選択** ] ウィンドウで、校閲者にするユーザーを検索して選択します。
    3. レビュー担当者を選んだら、**選択** ボタンを押します。

    [Image: レビュー担当者を指定する]
16. **[マネージャー**] を選択した場合は、フォールバック レビュー担当者を指定します。

    1. **フォールバック レビュー担当者を追加**を選択します。
    2. [フォールバック レビュー担当者の選択] ペインで、レビュー担当者のマネージャーに対するフォールバック レビュー担当者として指定するユーザーを検索して選択します。
    3. フォールバックレビュー担当者を選んだら、**[選択]** ボタンを押してください。

    [Image: フォールバック レビュー担当者を追加する]
17. 構成できる詳細設定は、他にもあります。 その他の高度なアクセス レビュー設定を構成するには、[ **アクセス レビューの詳細設定を表示**する] を選択します。

    1. 校閲者が応答しない場合にユーザーのアクセスに対する動作を指定する場合は、[ **レビュー担当者が応答しない場合**] を選択し、次のいずれかを選択します。

        - ユーザーのアクセスに関する決定を行いたくない場合は**変更されません**。
        - ユーザーのアクセス権を削除したい場合は、「アクセス権を削除」を選択してください。
        - MyAccess **からの推奨事項**に基づいて決定を行う場合は、推奨事項を実行します。

        [Image: アクセス レビューの詳細設定を追加する]
    2. システムの推奨事項を表示する場合は、[ **レビュー担当者の意思決定ヘルパーの表示**] を選択します。 システムの推奨事項は、ユーザーのアクティビティに基づいています。 レビュー担当者には、次のいずれかの推奨事項が表示されます。

        - ユーザーが過去 30 日間に少なくとも 1 回サインインした場合は、レビューを**承認**します。
        - ユーザーが過去 30 日間サインインしていない場合は、レビューを**拒否**します。
    3. レビュー担当者が承認決定の理由を共有する場合は、[ **レビュー担当者の正当な理由を要求する**] を選択します。 理由は、他のレビュー担当者と要求者に表示されます。
18. 新しいアクセス パッケージを作成する場合は、[ **確認と作成** ] を選択するか **、[次へ** ] を選択します。 ページの下部にあるアクセス パッケージを編集する場合は、[ **更新** ] を選択します。

### アクセス レビューの状態を表示する

開始日の後、アクセス レビューが [ **アクセス レビュー** ] セクションに一覧表示されます。 アクセス レビューの状態を表示するには、次の手順に従います。

1. **Identity Governance** で、[**アクセス パッケージ**] を選択し、確認するアクセス レビューの状態を含むアクセス パッケージを選択します。
2. アクセス パッケージの概要が表示されたら、左側のメニューで **[アクセス レビュー** ] を選択します。

    [Image: アクセス レビューを選択する]
3. アクセス レビューが関連付けられているすべてのポリシーが含まれる一覧が表示されます。 レビューを選択し、そのレポートを表示します。

    [Image: アクセス レビューの一覧]
4. レポートを表示すると、レビューされたユーザーの数と、レビュー担当者が行ったアクションが表示されます。

    [Image: レビューの状態を表示する]

### アクセス レビューのメール通知

レビュー担当者を指定することも、ユーザーが自分のアクセスを自分自身で確認することもできます。 既定では、レビューの開始後間もなく、Microsoft Entra ID からレビュー担当者や自己レビュー担当者にメールが送信されます。

このメールには、アクセス パッケージへのアクセスを確認する方法に関する指示が記載されます。 レビューがユーザー自身のアクセスをレビューする場合は、アクセス パッケージの自己レビューを実行する方法の手順をユーザーに示します。

レビュー担当者として割り当てたゲスト ユーザーが Microsoft Entra ゲスト招待を承諾していない場合、彼らはアクセス レビューからのメールを受信しません。 電子メールを受信する前に、まず招待を受け入れて、Microsoft Entra ID でアカウントを作成する必要があります。

注

レビュー サイクルが開かれている間、レビュー担当者はいつでもアクセス レビューの決定を変更できます。 レビュー担当者が既に決定を行った場合でも、アクセス レビューの途中であれば、アクセス レビュー サイクルがまだ開かれていることを通知するリマインダー メールがレビュー担当者に送信されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-access-reviews-review-access"} -->
## エンタイトルメント管理でのアクセス パッケージのアクセスのレビュー - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-reviews-review-access
- Service: entra-id-governance / entitlement-management
- Article date: 2026-03-12
- Summary: アクセス レビューで、エンタイトルメント管理アクセス パッケージのアクセス レビューを行う方法について説明します。

エンタイトルメント管理により、企業がグループ、アプリケーション、SharePoint サイトへのアクセスを管理する方法が簡略化されます。 この記事では、指定された校閲者がパッケージにアクセスするためのユーザー割り当てをレビューする方法について説明します。

### マイ アクセス ポータルを使用してアクセス レビューを実行する

[マイ アクセス ポータル](https://myaccess.microsoft.com/)は、アクセスニーズを手動で許可、承認、確認するためのわかりやすいポータルです。

#### アクセス レビューを開く

アクセス レビューを見つけて開くには、次の手順に従います。

1. アクセスの確認を求める電子メールがMicrosoftから届く場合があります。 メールを探してアクセス レビューを開きます。 アクセスをレビューするためのメールの例を次に示します。

    [Image: アクセスレビュー担当者のメール]
2. **[ユーザー アクセスのレビュー]** リンクを選択してアクセス レビューを開きます。
3. メールが届いていない場合は、https://myaccess.microsoft.com に直接移動して、保留中のアクセス レビューを見つけることができます。 (米国政府の場合は、代わりに `https://myaccess.microsoft.us` を使用します)。
4. 左側のナビゲーション バーの **[アクセス レビュー]** を選択して、割り当てられている保留中のアクセス レビューの一覧を表示します。

    [Image: マイ アクセスでアクセス レビューを選択する]
5. 開始するレビューを選択します。

    [Image: アクセス レビューを選択する]

#### マイ アクセス ポータルを使用して 1 人以上のユーザーのアクセスを手動で承認または拒否する

1. ユーザーの一覧を確認し、アクセスを継続する必要があるユーザーを特定します。

    [Image: レビューするユーザーの一覧]
2. アクセスを承認または拒否するには、ユーザー名の左側にあるラジオ ボタンを選択します。
3. ユーザー名の上のバーで **[承認]** または **[拒否]** を選択します。

    [Image: ユーザーを選択する]
4. わからない場合は、**[不明]** ボタンを選択できます。

    **[不明**] の選択を行うと、ユーザーはアクセスを維持し、この選択は監査ログに記録されます。 ログには、他のレビュアーがいる場合でも、レビューを完了したことが記録されます。
5. 決定の理由を示す必要がある場合があります。 理由を入力し、**[送信]** を選択します。

    [Image: アクセスの承認または拒否]
6. レビューが終了する前に、いつでも決定を変更することができます。 これを行うには、一覧からユーザーを選択し、決定を変更します。 たとえば、以前に拒否したユーザーのアクセスを承認できます。

レビュー担当者が複数いる場合、最後に送信された応答内容が記録されます。 管理者が Alice と Bob という 2 人のレビュー担当者を指名した場合を考えてみましょう。 最初に Alice がレビューを開いて、アクセスを承認します。 レビューが終了する前に、Bob がレビューを開き、アクセスを拒否します。 この場合、最後のアクセス拒否の決定が記録されます。

注

ユーザーは、レビューでアクセスを拒否されても、すぐにアクセス パッケージから削除されるわけではありません。 レビューが閉じられた後にレビュー結果が適用されると、ユーザーはアクセス パッケージから削除されます。 レビューは、レビュー期間の終了時または管理者が手動でレビューを停止した場合に自動的に閉じます。

#### システムによって生成された推奨事項を使用してアクセスを承認または拒否する

複数のユーザーのアクセスをより迅速にレビューするには、システムによって生成された推奨事項を使用して、1 回の選択で推奨事項を承認することができます。 推奨事項は、ユーザーのサインイン アクティビティに基づいて生成されます。

1. ページの上部にあるバーで、**[推奨事項の承認]** を選択します。

    [Image: 推奨事項の承認を選択する]

    推奨されているアクションの概要が表示されます。
2. **[送信]** を選択して、推奨事項を承認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-access-reviews-self-review"} -->
## エンタイトルメント管理でのアクセス パッケージのセルフレビュー - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-reviews-self-review
- Service: entra-id-governance / entitlement-management
- Article date: 2026-03-12
- Summary: アクセス レビューで、エンタイトルメント管理アクセス パッケージのユーザー アクセスを確認する方法について説明します。

エンタイトルメント管理により、企業がグループ、アプリケーション、および SharePoint サイトへのアクセスを管理する方法が簡単になります。 この記事では、割り当てられたアクセス パッケージのセルフレビューを行う方法について説明します。

### アクセス レビューを開く

アクセス レビューを行うには、まずアクセス レビューを開く必要があります。 アクセス レビューを見つけて開くには、次の手順を行います。

1. アクセスの確認を求めるメールが Microsoft から届く場合があります。 メールを探してアクセス レビューを開きます。 アクセスのレビューを求めるメールの例を次に示します。

    [Image: アクセス レビュー セルフレビュワーのメール]
2. **「アクセスをレビュー」リンクを選択します。**
3. メールが届かない場合は、https://myaccess.microsoft.com に直接アクセスして保留中のアクセス レビューを見つけることもできます。 (米国政府の場合は、代わりに https://myaccess.microsoft.us を使用します)。
4. 左側のナビゲーション バーの **[アクセス レビュー]** を選択して、割り当てられている保留中のアクセス レビューの一覧を表示します。
5. 開始するレビューを選択します。

### アクセス レビューを実行する

アクセス レビューを開くと、自分のアクセス権が表示されます。 アクセス レビューを行うには、次の手順を行います。

1. アクセス パッケージに引き続きアクセスする必要があるかどうかを判断します。 たとえば、作業中のプロジェクトが完了していない場合でも、作業を続行するにはアクセス権が必要です。
2. **[はい]** を選択してご自分のアクセスを維持するか、 **[いいえ]** を選択してご自分のアクセスを削除します。

    注意

    アクセスが不要になったと指定しても、アクセス パッケージからすぐには削除されません。 レビューが終了したとき、または管理者がレビューを停止すると、アクセス パッケージから削除されます。
3. **[はい]** を選択した場合は、**[理由]** ボックスに理由の説明を入力することが必要な場合があります。
4. **送信**を選択します。

レビューを終了する前に気が変わって回答を変更する場合は、レビューに戻ることができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-azure-role-assignments"} -->
## Azure ロールベースのアクセス制御 (RBAC) ロールを割り当てる - エンタイトルメント管理 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-azure-role-assignments
- Service: entra-id-governance / entitlement-management
- Article date: 2026-04-07
- Summary: Microsoft Entra エンタイトルメント管理で、アクセス パッケージおよびカタログに対して Azure RBAC ロールを割り当てます。 最小限の特権原則でアクセスを管理する方法について説明します。

エンタイトルメント管理では、アプリケーション、SharePoint サイト、グループ、Teams、[Microsoft Entra ロール](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-roles)など、さまざまなリソースの種類のアクセス ライフサイクルがサポートされます。 Azure リソースへのアクセスを管理するには、Azure RBAC ロールへのアクセスを Access パッケージとカタログに直接割り当てることができます。

Azure RBAC ロールを従業員とゲストに割り当てることで、エンタイトルメント管理を使用して、ID の権利を調べて、その ID に割り当てられているAzureロールをすばやく特定できます。 [最小限の特権アクセス](https://learn.microsoft.com/ja-jp/entra/identity-platform/secure-least-privileged-access)のセキュリティ原則に従って、**アクティブ**ロールと**有資格**ロールの両方の種類を割り当てることができ、Azureリソースへのアクセスが必要になったときに、権限をジャストインタイムでアクティブ化できます。

Azureリソースを割り当てることで、管理者は次のことができます。

- カタログ リソース オプション (管理グループ、サブスクリプション、またはリソース グループ) に合わせたターゲット スコープを選択します。
- ユーザーがアクセス パッケージに割り当てられたときに付与される、適格ロールまたはアクティブ ロールの種類を選択します。
- 適切なAzure RBAC ロールを選択します (カスタム Azure ロールと組み込みロールをサポートします)。 アクセス パッケージに対して要求して承認または割り当てられたエンド ユーザーは、Azureロールの割り当てを自動的に受け取ります。

### サポートされているシナリオ

| リソースが追加されるカタログ スコープ | アクセス パッケージの範囲の許可 | エンド ユーザーが受け取ることができるもの |
| --- | --- | --- |
| 管理グループ | 管理グループ | 管理グループ スコープで割り当てられた適格またはアクティブな Azure ロール |
| サブスクリプション | サブスクリプション | サブスクリプション スコープで割り当てられた有資格またはアクティブなAzure ロール |
| サブスクリプション | リソース グループ (サブスクリプションに関連付けられています) | リソース グループ スコープで割り当てられた、有資格またはアクティブなAzure ロール |

### 前提条件

この機能を使用するには、Microsoft Entra ID ガバナンス または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を参照してください。

#### カタログのオンボードに必要な Azure RBAC の要件

Azure サブスクリプションまたは管理グループをエンタイトルメント管理カタログに追加するには、この操作を実行する管理者が、そのスコープでロール割り当てを管理できる Azure RBAC のアクセス許可を持っている必要があります。

具体的には、エンタイトルメント管理では、次のAzure RBAC チェックが実行されます。

- `Microsoft.Authorization/roleassignments/read`
- `Microsoft.Authorization/roleassignments/write`
- `Microsoft.Authorization/roleassignments/delete`

選択した管理グループまたはサブスクリプションで このチェックでは、管理者が、承認済みのアクセス パッケージ割り当てに代わって、後でそのスコープで Azure RBAC ロールを割り当てるために、エンタイトルメント管理に必要な十分なアクセス許可を持っていることを確認します。

これらのチェックのいずれかが失敗した場合、Azure リソースのカタログへのオンボードは失敗します。

#### アクセス パッケージにロールを追加するための Azure RBAC の要件

サブスクリプションまたは管理グループがカタログにオンボードされた後、アクセス パッケージ管理者は、次の時点Azure RBAC ロールを追加できます。

- 管理グループ
- サブスクリプション
- リソース グループ (オンボードされたサブスクリプション内)

Azure RBAC ロールをアクセス パッケージに追加すると、エンタイトルメント管理は、選択した割り当てスコープで使用可能なロール定義を動的に列挙します。 その結果、アクセス パッケージを構成する管理者には次のものが必要です。

- `Microsoft.Authorization/roleDefinitions/read`

ロールの割り当て用に選択した特定のスコープ (管理グループ、サブスクリプション、またはリソース グループ) で、ロールの表示可否を照会します。

### Azure RBAC ロールをカタログに追加する

エンタイトルメント管理内のカタログにAzure RBAC ロールを割り当てるには、次の手順を実行します。

1. カタログ所有者のアクセス許可を持つ [ID ガバナンス管理者](https://entra.microsoft.com)または[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)として[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)にサインインします。
2. **ID ガバナンス**&gt;**Catalogs** に移動します。
3. [カタログ] ページで、Azure リソース ロールを追加するカタログを開き、[**リソースの追加]** を選択します。
4. [リソースの追加] ページで、**[Azure リソース]** を選択します。 [Image: カタログの [リソースの追加] ページで使用可能なリソース内のAzureリソースのスクリーンショット。]
5. [**リソースAzure選択**] ウィンドウで、[**サブスクリプション**] または [**管理グループ**] を選択します。 [Image: Azure リソースの種類を選択するスクリーンショット。]
6. 一覧から、カタログに追加するサブスクリプションまたは管理グループAzureを選択します。 [Image: 使用可能なAzure サブスクリプションの一覧のスクリーンショット。]
7. リソースが選択された状態で、[追加] を選択 **します**。 [Image: カタログに追加Azureリソースのスクリーンショット。]

### Azure RBAC ロールをアクセス パッケージに追加する

Azure リソースをカタログに追加すると、そのカタログ内のパッケージにアクセスするためのリソースとして追加できるようになります。 Azureリソースは、新しいアクセス パッケージと既存のアクセス パッケージの両方に追加できます。 このセクションでは、Azure RBAC ロールを既存のアクセス パッケージに追加する方法について説明します。 既存のアクセス パッケージにAzure RBAC ロールを追加するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com) に、少なくとも [Identity Governance Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) としてサインインします。

    ヒント

    このタスクを完了できるその他の最小限の特権ロールには、カタログ所有者またはアクセス パッケージ マネージャーが含まれます。
2. **ID ガバナンス**&gt;**権限管理**&gt;**アクセスパッケージ**に移動します。
3. Azure RBAC ロールを追加する既存のアクセス パッケージを選択します。
4. アクセス パッケージの概要ページで、[リソース] を選択 **します**。 [Image: 既存のアクセス パッケージのリソース ロール オプションのスクリーンショット。]
5. [リソースの追加] ページで、**[Azure リソース]** を選択します。
6. [**リソースAzure選択**] ウィンドウで、[**サブスクリプション**] または [**管理グループ**] を選択します。 [Image: Azure リソースの種類を選択するスクリーンショット。]
7. 一覧から、アクセス パッケージに追加する Azure サブスクリプションまたは管理グループを選択します。 [Image: 使用可能なAzure サブスクリプションの一覧のスクリーンショット。]
8. サブスクリプションを選択する場合は、[スコープ] でロールの割り当てが適用される場所を選択します。 [Image: Azure スコープのスクリーンショット。]
9. **[ロールの種類]** では、次の種類を選択できます。**アクティブ**: 永続的に割り当てる必要があるロールの場合。 **対象**: 必要に応じて[、Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) 経由で昇格を必要とするロールの場合。 [Image: Azure ロールのロールの種類を選択するスクリーンショット。]
10. 割り当てるAzure RBAC ロールを選択します。 組み込みロールとAzureカスタム ロールの両方を使用できます。 [Image: Azure ロールを選択するスクリーンショット。]
11. リソースが選択された状態で、[ **追加** ] を選択してアクセス パッケージに追加します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-catalog-create"} -->
## エンタイトルメント管理でリソースのカタログを作成して管理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create
- Service: entra-id-governance / entitlement-management
- Article date: 2024-07-15
- Summary: エンタイトルメント管理でリソースとアクセス パッケージの新しいコンテナーを作成する方法について説明します。

この記事では、エンタイトルメント管理でリソースやアクセス パッケージのカタログを作成して管理する方法について説明します。 カタログは、 [アクセス レビュー (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/catalog-access-reviews) でも使用されます。

### カタログを作成する

カタログは、リソースとアクセス パッケージのコンテナーです。 関連するリソースとアクセス パッケージをグループ化するときは、カタログを作成します。 管理者はカタログを作成できます。 さらに、 [カタログ作成者](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate) ロールに委任されたユーザーは、自分が所有するリソースのカタログを作成できます。 管理者以外のユーザーがカタログを作成すると、そのユーザーが最初のカタログ所有者になります。 カタログ所有者は、カタログ所有者としてユーザー、ユーザーのグループ、またはアプリケーション サービス プリンシパルを追加できます。

カタログを作成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com) に、少なくとも [Identity Governance Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) の権限としてサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ作成者があります。 ユーザー管理者ロールが割り当てられた ID は、カタログを作成したり、所有していないカタログ内のアクセス パッケージを管理したりできなくなります。 組織内の ID に、エンタイトルメント管理でカタログ、アクセス パッケージ、またはポリシーを構成するためのユーザー管理者ロールが割り当てられている場合は、代わりにこれらの ID に IDENTITY Governance Administrator ロールを割り当てる必要があります。
2. **ID ガバナンス**&gt;**Catalogs** に移動します。

    [Image: Microsoft Entra 管理センターでのエンタイトルメント管理カタログを示すスクリーンショット。]
3. [ **新しいカタログ]** を選択します。
4. カタログの一意の名前と説明を入力します。

    この情報は、アクセス パッケージの詳細に表示されます。
5. このカタログ内のアクセス パッケージをユーザーが作成後すぐに要求できるようにする場合は、[ **有効]** を **[はい**] に設定します。
6. 接続された組織の外部ディレクトリのユーザーがこのカタログのアクセス パッケージを要求できるようにする場合は、[ **外部ユーザーに対して有効]** を **[はい**] に設定します。 アクセス パッケージには、接続された組織のユーザーが要求できるようにするポリシーも必要です。 このカタログ内のアクセス パッケージがディレクトリに既に存在するユーザーのみを対象としている場合は、[ **外部ユーザーに対して有効]** を **[いいえ**] に設定します。

    注意

    この設定では、外部ユーザーがセルフサービス経由でアクセス パッケージを **要求** できるかどうかを制御します。 この設定は、管理者が外部ユーザーをアクセス パッケージに [直接割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments) 必要はありません。アクセス パッケージのポリシーによって制御されます。

    [Image: [新しいカタログ] ウィンドウを示すスクリーンショット。]
7. [ **作成]** を選択してカタログを作成します。

### カタログをプログラミングで作成する

プログラムでカタログを作成するには 2 つの方法があります。

#### Microsoft Graphを使用してカタログを作成する

Microsoft Graphを使用してカタログを作成できます。 委任された `EntitlementManagement.ReadWrite.All` アクセス許可を持つアプリケーションを持つ適切なロールのユーザー、または `EntitlementManagement.ReadWrite.All` アプリケーションのアクセス許可を持つアプリケーションは、API を呼び出して [カタログを作成](https://learn.microsoft.com/ja-jp/graph/api/entitlementmanagement-post-catalogs?view=graph-rest-1.0&preserve-view=true)できます。

#### PowerShell でカタログを作成する

また、`New-MgEntitlementManagementCatalog` モジュール バージョン 2.2.0 以降の PowerShell コマンドレットの コマンドレットを使用して、PowerShell でカタログを作成することもできます。

```powershell
Connect-MgGraph -Scopes "EntitlementManagement.ReadWrite.All"
$catalog = New-MgEntitlementManagementCatalog -DisplayName "Marketing"
```

### カタログにリソースを追加する

アクセス パッケージにリソースを含めるには、リソースがカタログ内に存在している必要があります。 アクセス パッケージで使用するためにカタログに追加できるリソースの種類には、グループ、アプリケーション、SharePoint Online サイト、および (プレビュー段階の) SAP IAG リソースが含まれます。

- グループは、クラウドで作成されたMicrosoft 365 グループでも、クラウドで作成されたMicrosoft Entraセキュリティ グループでもかまいません。

    - オンプレミスの Active Directoryで生成されたグループは、Microsoft Entra IDで所有者属性またはメンバー属性を変更できないため、リソースとして割り当てることはできません。 AD セキュリティ グループ メンバーシップを使用するアプリケーションへのアクセス権をユーザーに付与するには、既存のグループの権限のソースを変更してグループ ライトバック用に構成するか、Microsoft Entra IDで新しいセキュリティ グループを作成するか、group writeback to AD、そのグループを AD に書き込むことができる クラウドで作成されたグループを AD ベースのアプリケーションで使用できるようにします。
    - 配布グループとしてExchange Onlineに由来するグループは、Microsoft Entra IDでも変更できないため、カタログに追加できません。
- アプリケーションには、Microsoft Entra エンタープライズ アプリケーションが含まれます。これには、サービスとしてのソフトウェア (SaaS) アプリケーション、オンプレミス アプリケーション、および Microsoft Entra ID と統合された独自のアプリケーションが含まれます。

    - アプリケーションがまだMicrosoft Entra IDと統合されていない場合は、[環境内のアプリケーションへのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-prepare)および[Microsoft Entra IDとアプリケーションを統合する](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-integrate)という手順を確認し、カタログに追加する前に、まずディレクトリにそのアプリケーションを追加してください。
    - 複数のロールを持つアプリケーションに適したリソースを選択する方法の詳細については、 [アクセス パッケージに含めるリソース ロールを決定する方法を](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources#determine-which-resource-roles-to-include-in-an-access-package)参照してください。
- SharePoint オンライン サイトまたはSharePoint オンライン サイト コレクションにすることができます。

注意

サイト名または正確な URL で SharePoint サイトを検索します。検索ボックスでは大文字と小文字が区別されます。

- [カタログ アクセス レビュー (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/catalog-access-reviews) では、 [カスタム データ提供のリソース](https://learn.microsoft.com/ja-jp/entra/id-governance/custom-data-resource-access-reviews) をカタログに含めることもできます。

\*\*前提条件ロール:\*\* [カタログにリソースを追加するために必要なロールを](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate#required-roles-to-add-resources-to-a-catalog)参照してください。

カタログにリソースを追加するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com) に、少なくとも [Identity Governance Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) としてサインインします。
2. **ID ガバナンス**&gt;**Catalogs** に移動します。
3. [カタログ] ページで、リソースを追加するカタログを開きます。
4. 左側のメニューで、[リソース] を選択 **します**。
5. **[リソースの追加]** を選択します。
6. リソースの種類 **Groups と Teams**、**Applications**、または **SharePoint sites** を選択します。

    追加するリソースが表示されない場合、またはリソースを追加できない場合は、必要なMicrosoft Entraディレクトリ ロールとエンタイトルメント管理ロールがあることを確認してください。 必要なロールを持つ人物に、カタログへのリソース追加を依頼することが必要な場合があります。 詳細については、「 [リソースをカタログに追加するために必要なロール」を](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate#required-roles-to-add-resources-to-a-catalog)参照してください。
7. カタログに追加する種類の 1 つ以上のリソースを選択します。

    [Image: [カタログへのリソースの追加] ウィンドウを示すスクリーンショット。]
8. 完了したら、[追加] を選択 **します**。

    これらのリソースをカタログ内のアクセス パッケージに含めることができるようになりました。

#### カタログでリソース属性を追加する

属性とは、要求元がアクセス要求を送信する前に回答するよう求められる必須フィールドです。 これらの属性に対する回答は承認者に表示され、Microsoft Entra IDのユーザー オブジェクトにもスタンプが付けられます。

注意

リソースが含まれているアクセス パッケージへの要求を送信するには、その前に、そのリソースに設定されているすべての属性への回答が必要です。 要求元が回答を指定しない場合、その要求は処理されません。

アクセス要求のための属性を必要とするには:

1. 左側のメニューで [ **リソース** ] を選択すると、カタログ内のリソースの一覧が表示されます。
2. 属性を追加するリソースの横にある省略記号を選択し、[ **属性を必須にする**] を選択します。

    [Image: [必須属性] の選択を示すスクリーンショット]
3. 属性の種類を選択します。

    1. **Built-in** にはMicrosoft Entraユーザー プロファイル属性が含まれています。
    2. **Directory スキーマ拡張機能**は、Microsoft Entraユーザーにさらに多くのデータを格納する方法を提供します。 [拡張属性を作成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping#create-an-extension-attribute-in-a-tenant-with-cloud-only-users)することで、スキーマを拡張できます。 これらのユーザー オブジェクトの拡張機能属性は、プロビジョニングまたはシングル サイン オン中にアプリケーションに対する要求の送信に使用できます。
4. **[組み込み]** を選択した場合は、ドロップダウン リストから属性を選択します。 **ディレクトリ スキーマ拡張機能**を選択した場合は、テキスト ボックスに属性名を入力します。

    注意

    User.mobilePhone 属性は、一部の管理者のみが更新できる機密性の高いプロパティです。 詳細については、「 [機密性の高いユーザー属性を更新できるユーザー」を参照してください](https://learn.microsoft.com/ja-jp/graph/api/resources/users#who-can-update-sensitive-attributes)。
5. 要求元がその回答のために使用する回答形式を選択します。 回答形式には、 **短いテキスト**、 **複数の選択肢**、 **長いテキストが含まれます**。
6. 複数の選択肢を選択した場合は、[ **編集とローカライズ]** を選択して回答オプションを構成します。

    1. 表示される [ **質問の表示/編集** ] ウィンドウで、[回答 **の値** ] ボックスに質問に回答するときに要求者に提供する応答オプションを入力します。
    2. 回答のオプションの言語を選択します。 追加の言語を選択した場合は、回答のオプションをローカライズできます。
    3. 必要な数の応答を入力し、[保存] を選択 **します**。
7. 直接割り当ておよびセルフサービス要求中に属性値を編集可能にする場合は、[ **はい**] を選択します。

    注意

    [Image: 属性を編集可能にする方法を示すスクリーンショット。]

    - [**属性値は編集可能**] ボックスで [**いいえ**] を選択し、属性値*が空の*場合、ユーザーはその属性の値を入力できます。 保存した後、この値を編集することはできません。
    - [**属性値は編集可能]** ボックスで [**いいえ**] を選択し、属性値*が空でない*場合、ユーザーは直接割り当てとセルフサービス要求中に既存の値を編集できません。

    [Image: ローカライズの追加を示すスクリーンショット。]
8. ローカライズを追加する場合は、[ **ローカライズの追加]** を選択します。

    1. [ **質問のローカライズの追加** ] ウィンドウで、選択した属性に関連する質問をローカライズする言語の言語コードを選択します。
    2. 構成した言語で、[ **ローカライズされたテキスト** ] ボックスに質問を入力します。
    3. 必要なすべてのローカライズを追加したら、[ **保存]** を選択します。

        [Image: ローカライズの保存を示すスクリーンショット。]
9. [属性を **必須にする** ] ページですべての属性情報が完了したら、[ **保存]** を選択します。

#### 複数地域SharePointサイトを追加する

1. [Multi-Geo](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/multi-geo-capabilities-in-onedrive-and-sharepoint-online-in-microsoft-365) SharePointが有効になっている場合は、サイトを選択する環境を選択します。

    [Image: SharePoint Online サイトの選択ウィンドウを示すスクリーンショット。]
2. 次に、カタログに追加するサイトを選択します。

#### プログラムでカタログにリソースを追加する

Microsoft Graphを使用して、カタログにリソースを追加することもできます。 委任された `EntitlementManagement.ReadWrite.All` アクセス許可を持つアプリケーションを持つ適切なロールのユーザー、またはカタログとリソースの所有者は、API を呼び出して [resourceRequest を作成](https://learn.microsoft.com/ja-jp/graph/api/entitlementmanagement-post-resourcerequests?view=graph-rest-1.0&preserve-view=true)できます。 アプリケーションのアクセス許可 `EntitlementManagement.ReadWrite.All` とリソースを変更するアクセス許可 (`Group.ReadWrite.All` など) を持つアプリケーションによって、カタログにリソースを追加することもできます。

#### PowerShell を使用してカタログにリソースを追加する

また、`New-MgEntitlementManagementResourceRequest` モジュール バージョン 2.1.x 以降のモジュール バージョンの  コマンドレットを使用して、PowerShell のカタログにリソースを追加することもできます。 次の例では、PowerShell コマンドレット モジュール バージョン 2.4.0 Microsoft Graph使用して、リソースとしてカタログにグループを追加する方法を示します。

```powershell
Connect-MgGraph -Scopes "EntitlementManagement.ReadWrite.All,Group.ReadWrite.All"

$g = Get-MgGroup -Filter "displayName eq 'Marketing'"
if ($null -eq $g) {throw "no group" }

$catalog = Get-MgEntitlementManagementCatalog -Filter "displayName eq 'Marketing'"
if ($null -eq $catalog) { throw "no catalog" }
$params = @{
  requestType = "adminAdd"
  resource = @{
    originId = $g.Id
    originSystem = "AadGroup"
  }
  catalog = @{ id = $catalog.id }
}

New-MgEntitlementManagementResourceRequest -BodyParameter $params
sleep 5
$ar = Get-MgEntitlementManagementCatalog -AccessPackageCatalogId $catalog.Id -ExpandProperty resources
$ar.resources
```

### カタログからリソースを削除する

カタログからリソースを削除できます。 カタログからリソースを削除できるのは、それが、そのカタログのどのアクセス パッケージでも使用されていない場合だけです。

**前提条件ロール:**[カタログにリソースを追加するために必要なロールを](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate#required-roles-to-add-resources-to-a-catalog)参照してください。

カタログからリソースを削除するには:

1. 少なくとも [ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)として[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **ID ガバナンス**&gt;**Catalogs** に移動します。
3. [カタログ] ページで、リソースを削除するカタログを開きます。
4. 左側のメニューで、[リソース] を選択 **します**。
5. 削除するリソースを選択します。
6. [**を選択し、**を削除します。 必要に応じて、省略記号 (**...**) を選択し、[ **リソースの削除**] を選択します。

### カタログ所有者を追加する

カタログを作成したユーザーが最初のカタログ所有者になります。 カタログの管理を委任するには、カタログ所有者ロールにユーザーを追加します。 カタログ所有者を追加すると、カタログ管理の責任を共有するのに役立ちます。

カタログ所有者ロールにユーザーを割り当てるには:

1. 少なくとも[アイデンティティ ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)として[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者があります。
2. **ID ガバナンス**&gt;**Catalogs** に移動します。
3. [カタログ] ページで、管理者を追加するカタログを開きます。
4. 左側のメニューで、[ **ロールと管理者**] を選択します。

    [Image: カタログ ロールと管理者を示すスクリーンショット。]
5. **所有者を追加**を選択して、これらの役割のメンバーを選びます。
6. **選択** を選択して、これらのメンバーを追加します。

### カタログを編集する

カタログの名前と説明を編集できます。 この情報は、アクセス パッケージの詳細に表示されます。

カタログを編集するには:

1. 少なくとも[Identity Governance Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)として[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ作成者があります。
2. **ID ガバナンス**&gt;**Catalogs** に移動します。
3. [カタログ] ページで、編集するカタログを開きます。
4. カタログの **[概要** ] ページで、[ **編集]** を選択します。
5. カタログの名前、説明、または有効になっている設定を編集します。

    [Image: カタログ設定の編集を示すスクリーンショット。]
6. **保存**を選びます。

### カタログを削除する

カタログを削除できるのは、そのカタログにどのアクセス パッケージも含まれていない場合に限られます。

カタログを削除するには:

1. あなたは少なくとも[Identity Governance Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)として[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ作成者があります。
2. **ID ガバナンス**&gt;**Catalogs** に移動します。
3. [カタログ] ページで、削除するカタログを開きます。
4. カタログの **[概要** ] ページで、[削除] を選択 **します**。
5. 表示されたメッセージ ボックスで、[ **はい**] を選択します。

#### プログラムでカタログを削除する

Microsoft Graphを使用してカタログを削除することもできます。 委任された `EntitlementManagement.ReadWrite.All` アクセス許可を持つアプリケーションを使用する適切なロールのユーザーは、API を呼び出して [accessPackageCatalog を削除する](https://learn.microsoft.com/ja-jp/graph/api/accesspackagecatalog-delete) ことができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-configure-id-protection-approvals"} -->
## エンタイトルメント管理 (プレビュー) でアクセス パッケージ要求の ID 保護ベースの承認を構成する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-configure-id-protection-approvals
- Service: entra-id-governance / entitlement-management
- Article date: 2025-11-04
- Summary: この記事では、アクセス パッケージ要求に対して ID 保護ベースの承認を構成する方法について説明します。

危険なユーザーが機密性の高いリソースにアクセスできないようにすることは、環境を保護する上で重要な部分です。 [Microsoft Entra ID Protection (IDP) シグナルを Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) Governance エンタイトルメント管理のアクセス パッケージ承認ワークフローに統合することで、エンタイトルメント管理要求プロセスをさらにセキュリティで保護できます。 ID 保護を使用すると、リスクの高いユーザーがアクセス パッケージへのアクセスを要求すると、エンタイトルメント管理によって新しい最初の承認ステージが自動的に追加されます。 この機能により、アクセス要求が標準の承認ルーティング用にルーティングされる前に、侵害された可能性があるユーザーまたは危険にさらされている可能性のあるユーザーが、承認されたセキュリティまたはコンプライアンスの承認者によって確認されます。 この記事では、ID Protection を使用してエンタイトルメント要求プロセスをさらにセキュリティで保護する方法について説明します。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID ガバナンスまたは Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、 [Microsoft Entra ID ガバナンス のライセンスの基礎を](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)参照してください。

### [前提条件]

エンタイトルメント管理で ID 保護を使用するには、まず [ID 保護を展開](https://learn.microsoft.com/ja-jp/entra/id-protection/how-to-deploy-identity-protection)する必要があります。

### リスクベースの承認のしくみ

注

お客様が IDP オプションと IRM オプションの両方を有効にしている場合、アクセス パッケージ要求は最初に IDP 承認者、次に IRM 承認者、最後にアクセス パッケージ ポリシー承認者にルーティングされます。

ユーザーが **マイ** アクセス ポータルを使用してアクセス パッケージへのアクセスを要求した場合:

1. **リスク評価**: エンタイトルメント管理は、ユーザーの現在の userRiskLevel に対して Microsoft Entra ID Protection を照会します
2. **構成チェック**: ユーザーのリスク レベルが管理者が選択したしきい値 (中、高など) のいずれかに一致する場合、エンタイトルメント管理では、標準承認プロセスの前にリスクベースの承認ステージが自動的に追加されます。
3. **自動承認者の割り当て**:

    - 要求は、Microsoft Entra ID でセキュリティ管理者ロールが割り当てられているユーザーにルーティングされます。
4. **セキュリティ レビュー**: 割り当てられた承認者は、ユーザーのリスクの詳細を確認し、要求承認ルーティングのこのステージを承認するか拒否するかを決定します。

    - 承認された場合、要求は通常のアクセス パッケージ承認手順の残りの部分を通じて続行されます。
    - 拒否された場合、要求は閉じられ、監査ログに記録され、それ以上の承認ルーティングは行われません。
5. **監査ログ**: レポートとコンプライアンスの可視性のために、すべてのアクション (承認と拒否) と結果が [エンタイトルメント管理ログ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logs-and-reporting) にキャプチャされます。

### Microsoft Entra 管理センターを使用してアクセス パッケージの ID 保護ベースの承認を構成する

Microsoft Entra 管理センターでアクセス パッケージの ID 保護ベースの承認を構成するには、次の手順を実行します。

1. 少なくとも [ID ガバナンス管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**制御構成**を参照します。
3. コントロールの構成画面で、オプションを確認できます。[Image: エンタイトルメント管理のコントロール構成カードのスクリーンショット。]
4. カードの **リスクベースの承認 (プレビュー)** で、[ **設定の表示**] を選択します。
5. リスクベースの承認ページで、[ **ID 保護リスクを持つユーザーの承認を要求する (プレビュー)]**の横にある [ **カスタマイズ**] を選択します。 ( [インサイダー リスク管理ベースの承認](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-configure-insider-risk-management-approvals)も構成する場合は、別の記事を参照してください)。 [Image: リスクベースの承認の概要画面のスクリーンショット。]
6. ID 保護のユーザー リスク レベルを設定し、[ **保存]** を選択できます。[Image: エンタイトルメント管理の ID 保護リスク設定のスクリーンショット。]

### 危険なユーザーの要求を確認する

危険なユーザーからの保留中の要求を確認するには、承認者に [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロールが必要です。

危険なユーザーがアクセス パッケージの要求を送信すると、管理者はアクセス パッケージ内の要求ページを介して保留中の状態を確認できます。

[Image: 危険なユーザーによるアクセス パッケージに対する保留中の要求のスクリーンショット。]

危険なユーザーの承認者またはフォールバック承認者として設定されたユーザーは、アクセス ポータルを使用して要求を表示し、承認または拒否できます。 [Image: 危険なユーザーを示すアクセス許可の [承認] ページのスクリーンショット。]

注

承認者は、アクションを実行するために最大 14 日間です。 その期間内にアクションを実行しない場合、要求は自動的に拒否されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-configure-insider-risk-management-approvals"} -->
## エンタイトルメント管理 (プレビュー) でアクセス パッケージ要求の Insider リスク管理ベースの承認を構成する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-configure-insider-risk-management-approvals
- Service: entra-id-governance / entitlement-management
- Article date: 2025-11-04
- Summary: この記事では、アクセス パッケージ要求に対して Insider リスク管理ベースの承認を構成する方法について説明します。

危険なユーザーが機密性の高いリソースにアクセスできないようにすることは、環境を保護する上で重要な部分です。 [Microsoft Purview Insider Risk Management (IRM)](https://learn.microsoft.com/ja-jp/purview/insider-risk-management-configure) シグナルを Microsoft Entra ID ガバナンスのエンタイトルメント管理のアクセス パッケージ承認ワークフローに統合することで、エンタイトルメント管理要求プロセスをさらにセキュリティで保護できます。 リスク管理ベースの承認では、リスクの高いユーザーがアクセス パッケージへのアクセスを要求すると、エンタイトルメント管理によって新しい最初の承認ステージが自動的に追加されます。 これにより、アクセス要求が標準の承認ルーティング用にルーティングされる前に、侵害または危険にさらされる可能性があると特定されたユーザーが、承認されたセキュリティまたはコンプライアンスの承認者によって確認されます。 この記事では、Insider リスク管理を使用してエンタイトルメント要求プロセスをさらにセキュリティで保護する方法について説明します。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID ガバナンスまたは Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、 [Microsoft Entra ID ガバナンス のライセンスの基礎を](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)参照してください。 [また、Microsoft Purview の適切なライセンスも必要です](https://learn.microsoft.com/ja-jp/purview/insider-risk-management-configure#subscriptions-and-licensing)。

### [前提条件]

エンタイトルメント管理で Insider Risk Management の承認を使用するには、まず [Insider Risk Management ポリシーを作成する](https://learn.microsoft.com/ja-jp/purview/insider-risk-management-plan)必要があります。

### リスクベースの承認のしくみ

ユーザーが **マイ** アクセス ポータルを使用してアクセス パッケージへのアクセスを要求した場合:

1. **リスク評価**: エンタイトルメント管理は、ユーザーの現在の userRiskLevel について Microsoft Purview Insider Risk Management にクエリを実行します
2. **構成チェック**: ユーザーのリスク レベルが管理者が選択したしきい値 (モデレートや昇格など) のいずれかに一致する場合、エンタイトルメント管理では、標準承認プロセスの前にリスクベースの承認ステージが自動的に追加されます。
3. **自動承認者の割り当て**:

    - 要求は、Microsoft Entra ID でコンプライアンス管理者ロールが割り当てられているユーザーにルーティングされます。
4. **コンプライアンス レビュー**: 割り当てられた承認者は、ユーザーのリスクの詳細を確認し、要求承認ルーティングのこの段階を承認するか拒否するかを決定します。

    - 承認された場合、要求は通常のアクセス パッケージ承認手順の残りの部分を通じて続行されます。
    - 拒否された場合、要求は閉じられ、監査ログに記録され、それ以上の承認ルーティングは行われません。
5. **監査ログ**: レポートとコンプライアンスの可視性のために、すべてのアクション (承認と拒否) と結果が [エンタイトルメント管理ログ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logs-and-reporting) にキャプチャされます。

### Microsoft Entra 管理センターを使用して、アクセス パッケージの Insider Risk Management ベースの承認を構成する

Microsoft Entra 管理センターでアクセス パッケージの Insider Risk Management ベースの承認を構成するには、次の手順を実行します。

1. 少なくとも [ID ガバナンス管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**制御構成**を参照します。
3. コントロールの構成画面で、オプションを確認できます。[Image: エンタイトルメント管理のコントロール構成カードのスクリーンショット。]
4. カードの **リスクベースの承認 (プレビュー)** で、[ **設定の表示**] を選択します。
5. [リスクベースの承認] ページで、[ **インサイダー リスク レベル (プレビュー) を持つユーザーに承認を要求**する] の横にある [ **カスタマイズ**] を選択します。 ( [ID 保護ベースの承認](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-configure-id-protection-approvals)を構成するには、別の記事を参照してください)。 [Image: リスクベースの承認の概要画面のスクリーンショット。]
6. インサイダー リスク レベルを設定し、[保存] を選択 **できます**。[Image: エンタイトルメント管理のインサイダー リスク レベル設定のスクリーンショット。]

### 危険なユーザーの要求を確認する

危険なユーザーからの保留中の要求を確認するには、承認者に [コンプライアンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#compliance-administrator) ロールが必要です。

危険なユーザーがアクセス パッケージの要求を送信すると、管理者はアクセス パッケージ内の要求ページを介して保留中の状態を確認できます。

[Image: 危険なユーザーによるアクセス パッケージに対する保留中の要求のスクリーンショット。]

危険なユーザーの承認者またはフォールバック承認者として設定されたユーザーは、アクセス ポータルを介して承認または拒否の要求を表示できます。 [Image: インサイダー リスク管理から危険なユーザーを承認するスクリーンショット。]

注

承認者は、アクションを実行するために最大 14 日間です。 その期間内にアクションを実行しない場合、要求は自動的に拒否されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-custom-teams-extension"} -->
## Microsoft Entra エンタイトルメント管理を Microsoft Teams と統合するために、カスタム拡張性と Logic Apps を使用する方法 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-custom-teams-extension
- Service: entra-id-governance / entitlement-management
- Article date: 2024-07-15
- Summary: このチュートリアルでは、カスタム拡張機能と Logic Apps を使用した、エンタイトルメント管理と Microsoft Teams の統合について説明します。

シナリオ: カスタム拡張機能と Azure Logic App を使用して、アクセス パッケージへのアクセスが受信または拒否された際に Microsoft Teams のエンド ユーザーに通知を自動的に送信します。

このチュートリアルでは、以下の内容を説明します。

- ロジック アプリ ワークフローを既存のカタログに追加する。
- 既存のアクセス パッケージ内のポリシーにカスタム拡張機能を追加する。
- エンタイトルメント管理ワークフローを再開するために Microsoft Entra にアプリケーションを登録する
- Automation 認証用に ServiceNow を構成する。
- アクセス パッケージへのアクセスをエンド ユーザーとして要求する。
- 要求されたアクセス パッケージへのアクセスをエンド ユーザーとして受け取る。

### 前提条件

- アクティブな Azure サブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 少なくとも次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者、サービス プリンシパルの所有者。

### Logic App とカスタム拡張機能をカタログに作成する

Logic App とカスタム拡張機能をカタログに作成するには、次の手順に従います。

1. 少なくとも [ID ガバナンス管理者](https://entra.microsoft.com/#view/Microsoft_AAD_ERM/DashboardBlade/%7E/elmEntitlement)として、Microsoft Entra 管理センターの [\[ID ガバナンス - Microsoft Entra 管理センター\]](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) に移動します。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者とリソース グループ所有者があります。
2. 左側のメニューで、 **[カタログ]** を選択します。
3. カスタム拡張機能を追加するカタログを選択し、左側のメニューで **[Custom Extensions] (カスタム拡張機能)** を選択します。
4. ヘッダーのナビゲーション バーで、 **[カスタム拡張機能の追加]** を選択します。
5. **[基本]** タブで、カスタム拡張機能の名前とワークフローの説明を入力します。 これらのフィールドは、カタログの **[カスタム拡張機能]** タブに表示されます。
6. **[拡張機能の種類]** で **[要求ワークフロー]** を選択し、アクセス パッケージの作成が要求されたとき、要求が承認されたとき、割り当てが許可および削除されたときのポリシー ステージに対応させます。

    注記

    **有効期限前のワークフロー**用に別のカスタム拡張機能を作成できます。

    [Image: エンタイトルメント管理のためのカスタム拡張機能を作成するスクリーンショット。]
7. [拡張機能の構成] で **[起動して続行]** を選択します。これにより、このワークフローがトリガーされた後もエンタイトルメント管理が続行されます。 [Image: エンタイトルメント管理のカスタム拡張機能の動作アクション タブのスクリーンショット。]
8. **[詳細]** タブの [Create new logic App] (新しい Logic App の作成) フィールドで [はい] を選択し、Azure サブスクリプションとリソース グループの詳細、Logic App の名前を指定します。*[ロジック アプリの作成]* を選択します。 [Image: 拡張されたカスタム拡張機能の詳細を選択するスクリーンショット。]
9. "*デプロイ中*" と表示され、完了すると次のような成功メッセージが表示されます: [Image: 新しい Logic App のデプロイに成功したことを示すスクリーンショット。]
10. **[確認と作成]** では、カスタム拡張機能の概要をレビューし、ロジック アプリのコールアウトの詳細が正しいことを確認してください。 **[作成]** を選択します。

リンクされたロジック アプリに対するこのカスタム拡張機能が、[カタログ] の下の [カスタム拡張機能] タブに表示されるようになります。 これは、アクセス パッケージ ポリシーで呼び出すことができます。

### Logic App を構成する

1. 作成されたカスタム拡張機能は、**[カスタム拡張機能]** タブの下に表示されます。カスタム拡張機能で “*ロジック アプリ*” を選択すると、ロジック アプリを構成するページへリダイレクトされます。 [Image: ロジック アプリの構成画面のスクリーンショット。]
2. 左側のメニューで **[ロジック アプリ デザイナー]** を選択します。 [Image: ロジック アプリ デザイナーの画面のスクリーンショット。]
3. 右側の 3 つのドットを選択して **[Condition] (条件)** を削除し、[削除] を選択して [OK] を選択します。 削除すると、ページに新しいステップを追加するオプションが表示されます。 [Image: ロジック アプリ デザイナーの条件設定のスクリーンショット。]
4. *[新しいステップ]* を選択するとダイアログ ボックスが開くので、**[すべて]** を選択してコネクタの一覧を展開します。 [Image: Logic App のコネクタ一覧のスクリーンショット。]
5. 表示される一覧で、Microsoft Teams を検索して選択します。 [Image: Logic App のコネクタ一覧の Microsoft Teams アプリのスクリーンショット。]
6. アクションの一覧から [チャットまたはチャネルでメッセージを投稿する] を選択します。[Image: ロジック アプリ デザイナーの Teams アクションのスクリーンショット。]
7. **[Post as] (投稿者)** で [Flow Bot] (フロー ボット) を選択し、*[Post In] (投稿先)* で [Chat with Flow bot] (フロー ボットとチャット) を選択します。[Image: Teams のメッセージ投稿パラメーターを設定するスクリーンショット。]
8. **[受信者]** を選択すると、動的コンテンツを選択するためのポップアップが表示されます。 「*ObjectID -Requestor-Objectid*」を選択します。 [Image: Teams のメッセージ投稿の受信者 ID を設定するスクリーンショット。]
9. メッセージにメール本文を追加します。 プレーンテキストの書式を設定したり、動的コンテンツを追加したりすることもできます。 [Image: Teams のメッセージ投稿設定の動的コンテンツ設定のスクリーンショット。]
10. [Add new Parameter] (新しいパラメーターの追加) 内を選択し、[IsAlert] ボックスにチェックを入れると、Microsoft Teams のアクティビティ フィードにメッセージが表示されます。[Image: Teams のメッセージ投稿設定の isAlert 設定のスクリーンショット。]
11. **[保存]** を選択して、変更内容が保存されるようにします。 これで、Logic App にリンクされたアクセス パッケージに対して更新が行われると、電子メールが送信されるようになりました。

### 既存のアクセス パッケージ内のポリシーにカスタム拡張機能を追加する

カタログでカスタム拡張機能を設定した後、管理者はポリシーを使用してアクセス パッケージを作成し、要求が承認されたときにカスタム拡張機能をトリガーできます。 これにより、特定のアクセス要件を定義し、組織のニーズに合わせてアクセス レビュー プロセスを調整できます。

1. ID ガバナンス ポータルで、少なくとも [ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)として、**[アクセス パッケージ]** を選択します。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者とアクセス パッケージ マネージャーがあります。
2. 既に作成されているアクセス パッケージの一覧から、カスタム拡張機能 (Logic App) を追加するアクセス パッケージを選択します。
3. **[編集]** を選択し、**[プロパティ]** で、「[Logic App とカスタム拡張機能をカタログに作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-custom-teams-extension#create-a-logic-app-and-custom-extension-in-a-catalog)」セクションですでに使用したカタログに変更して、**[保存]** を選択します。
4. [ポリシー] タブに移動し、ポリシーを選択して **[編集]** を選択します。
5. ポリシー設定で、**[Custom Extensions] (カスタム拡張機能)** タブに移動します。
6. [ステージ] の下のメニューで、このカスタム拡張機能 (ロジック アプリ) のトリガーとして使用するアクセス パッケージ イベントを選択します。 このシナリオでは、アクセス パッケージが要求、承認、付与、または削除されたときにカスタム拡張機能 Logic App ワークフローをトリガーするため、**[要求が作成されました]**、**[要求が承認されました]**、**[割り当てが付与されました]**、**[割り当てが削除されました]** を選択します。 [Image: アクセス パッケージのためのカスタム拡張機能ポリシーのスクリーンショット。]
7. 既存のアクセス パッケージのポリシーに追加するには、**[更新]** を選択します。

### 新しいアクセス パッケージにカスタム拡張機能を追加する

1. Identity Governance ポータルで、**[アクセス パッケージ]** を選択し、新しいアクセス パッケージを作成します。
2. [基本] タブで、ポリシーの名前、説明、「[Logic App とカスタム拡張機能をカタログに作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-custom-teams-extension#create-a-logic-app-and-custom-extension-in-a-catalog)」セクションで使用したカタログを追加します。 [Image: アクセス パッケージの作成画面のスクリーンショット。]
3. 必要な **リソース ロール**を追加します。
4. 必要な**要求**を追加します。
5. 必要に応じて、**要求元情報**を指定します。
6. **ライフサイクル**の詳細を追加します。
7. [カスタム拡張機能] タブの [ステージ] の下のメニューで、このカスタム拡張機能 (ロジック アプリ) のトリガーとして使用したいアクセス パッケージ イベントを選択します。 このシナリオでは、アクセス パッケージが要求、承認、付与、または削除されたときにカスタム拡張機能 Logic App ワークフローをトリガーするため、**[要求が作成されました]**、**[要求が承認されました]**、**[割り当てが付与されました]**、**[割り当てが削除されました]** を選択します。 [Image: アクセス パッケージ ポリシーの選択画面のスクリーンショット。]
8. **[確認と作成]** で、アクセス パッケージの概要を確認し、詳細が正しいことを確認してから **[作成]** を選択します。

注記

新しいアクセス パッケージを作成する場合は、**[新しいアクセス パッケージ]** を選択します。 アクセス パッケージを作成する方法の詳細については、「[エンタイトルメント管理で新しいアクセス パッケージを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)」をご覧ください。 既存のアクセス パッケージを編集する方法の詳細については、「[Microsoft Entra エンタイトルメント管理でアクセス パッケージの要求設定を変更する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#open-and-edit-an-existing-policys-request-settings)」を参照してください。

### 検証

Microsoft Teams との統合が成功したことを確認するため、「[新しいアクセス パッケージにカスタム拡張機能を追加する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-custom-teams-extension#add-custom-extension-to-a-new-access-package)」セクションで作成したアクセス パッケージにユーザーを追加または削除します。 ユーザーは、**Power Automate** から Microsoft Teams で通知を受け取ります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-delegate"} -->
## エンタイトルメント管理の委任と役割 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate
- Service: entra-id-governance / entitlement-management
- Article date: 2024-02-20
- Summary: 部門マネージャーとプロジェクト マネージャーが自分でアクセスを管理できるよう、IT 管理者からアクセス ガバナンスを委任する方法について説明します。

Microsoft Entra ID では、ロール モデルをいくつかの方法で使用して、ID ガバナンスを通じて大規模にアクセスを管理できます。

- アクセス パッケージを使用して、"営業担当者" など、組織内の組織の [役割](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-organizational-roles) を表すことができます。 組織のロールを表すアクセス パッケージには、複数のリソースにわたって営業担当者が一般的に必要とする可能性があるすべてのアクセス権が含まれます。
- アプリケーション [は、独自のロールを定義できます](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)。 たとえば、販売アプリケーションがあり、そのアプリケーションがそのマニフェストにアプリ ロール "salesperson" を含めた場合、 [アプリ マニフェストのそのロールをアクセス パッケージに含めることができます](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources)。 ユーザーがアプリケーション固有の複数のロールを同時に持っている可能性があるシナリオでも、アプリケーションにはセキュリティ グループを使用できます。
- ロールを使用して管理アクセスを委任できます。 営業に必要なすべてのアクセス パッケージのカタログがある場合、カタログ固有のロールを割り当てることによって、任意のユーザーをそのカタログの責任者に任命することができます。

この記事では、エンタイトルメント管理リソースへのアクセスを制御するために、ロールを使用して Microsoft Entra エンタイトルメント管理内の側面を管理する方法について説明します。

既定では、グローバル管理者ロールと ID ガバナンス管理者ロールのユーザーは、 エンタイトルメント管理のすべての側面を作成および管理できます。 ただし、これらのロールのユーザーは、アクセス パッケージが必要なあらゆる状況を把握しているとは限りません。 通常、コラボレーションの相手、使用するリソース、期間はそれぞれの部門、チーム、プロジェクト内のユーザーが把握しています。 管理者以外のユーザーに無制限のアクセス許可を付与する代わりに、それぞれの業務に必要な最小限のアクセス許可を付与することで、競合の発生や不適切なアクセス許可を回避することができます。

この動画では、IT 管理者から管理者ではないユーザーにアクセス ガバナンスを委任する方法について概説します。

### 委任の例

エンタイトルメント管理でアクセス ガバナンスを委任する方法を理解するには、例を考えてみるとよいでしょう。 組織に次の管理者やマネージャーがいるとします。

[Image: IT 管理者からマネージャーに委任する]

IT 管理者の Hana には、各部署に連絡先担当者がいます。マーケティングの Mamta、財務の Mark、法務の Joe がそれぞれの部署のリソースとビジネス クリティカル コンテンツを担当しています。

アクセスを必要とするユーザー、アクセスの期間、アクセスされるリソースを把握しているのが管理者以外の人であれば、エンタイトルメント管理を利用することで、そうした管理者以外の人にアクセス ガバナンスを委任できます。 管理者以外に委任することにより、適任者がその部署のアクセスを管理することになります。

たとえば次の方法で Hana はマーケティング部、財務部、法務部にアクセス ガバナンスを委任できます。

1. Hana は新しい Microsoft Entra セキュリティ グループを作成し、グループのメンバーとして Mamta、Mark、Joe を追加します。
2. Hana はそのグループをカタログ作成者ロールに追加します。

    Mamta、Mark、Joe はこれで、自分の部署にカタログを作成したり、自分の部署に必要なリソースを追加したり、カタログ内で追加の委任を行ったりできます。 お互いのカタログを見ることはできません。
3. Mamta は、リソースのコンテナーである **マーケティング** カタログを作成します。
4. Mamta は、マーケティング部が所有するリソースをこのカタログに追加します。
5. Mamta は、このカタログのカタログ所有者として、この部署の他のユーザーを追加できます。これにより、カタログ管理の責任を共有できます。
6. Mamta はさらに、マーケティング カタログのアクセス パッケージの作成と管理をマーケティング部のプロジェクト マネージャーに委任できます。 これを行うには、カタログ上でユーザーをアクセス パッケージ マネージャーのロールに割り当てることで実行できます。 アクセス パッケージ管理者は、そのカタログ内のポリシー、要求、割り当てと共に、アクセス パッケージを作成および管理できます。 カタログで許可されている場合、アクセス パッケージ管理者は、接続されている組織からユーザーを取り込むためのポリシーを構成できます。

次の図は、マーケティング部、財務部、法務部のリソースを含むカタログを示しています。 プロジェクト マネージャーは、これらのカタログを使用する際に、自分のチームまたはプロジェクトのアクセス パッケージを作成できます。

[Image: エンタイトルメント管理デリゲートの例]

委任後、マーケティング部に含まれるロールは次の表のようになります。

| ユーザー | 組織の役割 | Microsoft Entra ロール | 権限管理の役割 |
| --- | --- | --- | --- |
| Hana | IT 管理者 | グローバル管理者または ID ガバナンス管理者 |  |
| Mamta | マーケティング マネージャー | ユーザー | カタログ作成者とカタログ所有者 |
| ボブ | マーケティング リーダー | ユーザー | カタログ所有者 |
| ジェシカ | マーケティング プロジェクト マネージャー | ユーザー | アクセス パッケージ マネージャー |

### エンタイトルメント管理の役割

エンタイトルメント管理には、エンタイトルメント管理自体を管理するためのアクセス許可と共に、すべてのカタログにまたがって適用される次のロールがあります。

| 権限管理の役割 | ロール定義 ID | 説明 |
| --- | --- | --- |
| カタログ作成者 | `ba92d953-d8e0-4e39-a797-0cbedb0a89e8` | カタログを作成および管理します。 通常は、グローバル管理者ではない IT 管理者、またはリソース コレクションのリソース所有者です。 カタログを作成した人物が、自動的にカタログの最初のカタログ所有者になります。カタログ所有者はさらに追加することができます。 カタログ作成者は、自分が所有していないカタログを管理したり表示したりすることはできず、所有していないリソースをカタログに追加することはできません。 カタログ作成者が別のカタログを管理したり、所有していないリソースを追加したりする必要がある場合は、そのカタログまたはリソースの共同所有者になることを要求できます。 |
| 接続された組織管理者 | `e65cf63f-9cc2-4b48-8871-cb667e9d90f` | 接続された組織を作成および管理します。 |

エンタイトルメント管理には、カタログ内のアクセス パッケージやその他の構成を管理するための、特定のカタログごとに定義される次のロールがあります。 管理者またはカタログ所有者は、これらのロールに ID、ID のグループ、またはサービス プリンシパルを追加できます。

| 権限管理の役割 | ロール定義 ID | 説明 |
| --- | --- | --- |
| カタログ所有者 | `ae79f266-94d4-4dab-b730-feca7e132178` | カタログ内のアクセス パッケージやその他のリソースを編集および管理します。 通常、IT 管理者またはリソース所有者、またはカタログ所有者が選択した ID。 |
| カタログ リーダー | `44272f93-9762-48e8-af59-1b5351b1d6b3` | カタログ内の既存のアクセス パッケージを表示します。 |
| アクセス パッケージ マネージャー | `7f480852-ebdc-47d4-87de-0d8498384a83` | カタログ内の既存のリソースから新しいアクセス パッケージを作成し、カタログ内のすべての既存のアクセス パッケージを編集および管理します。 |
| アクセス パッケージ割り当てマネージャー | `e2182095-804a-4656-ae11-64734e9b7ae5` | カタログ内の既存のアクセス パッケージの場合は、ユーザーを割り当ててユーザーを削除できます。 アクセス パッケージ自体を作成または編集することはできません。 |

また、アクセス パッケージの指定された承認者と申請者も、ロールではありませんが権限を持ちます。

| はい | 説明 |
| --- | --- |
| 承認者 | アクセス パッケージへの要求を承認または拒否することがポリシーによって許可されています。ただし、アクセス パッケージの定義を変更することはできません。 |
| 要求者 | アクセス パッケージのポリシーによって、そのアクセス パッケージへの要求が許可されています。 |

次の表は、エンタイトルメント管理内で、エンタイトルメント管理ロールで実行できるタスクを一覧にしたものです。

| タスク | ID管理ガバナンス管理者 | 接続された組織管理者 | カタログ作成者 | カタログ所有者 | アクセス パッケージ マネージャー | アクセス パッケージ割り当てマネージャー |
| --- | --- | --- | --- | --- | --- | --- |
| [カタログ作成者に委任する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate-catalog) | ✔️ |  |  |  |  |  |
| [接続されている組織を追加する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-organization) | ✔️ | ✔️ |  |  |  |  |
| [新しいカタログを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create) | ✔️ |  | ✔️ |  |  |  |
| [カタログにリソースを追加する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#add-resources-to-a-catalog) | ✔️ |  |  | ✔️ |  |  |
| [カタログ所有者を追加する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#add-more-catalog-owners) | ✔️ |  |  | ✔️ |  |  |
| [カタログを編集する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#edit-a-catalog) | ✔️ |  |  | ✔️ |  |  |
| [カタログを削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#delete-a-catalog) | ✔️ |  |  | ✔️ |  |  |
| [アクセス パッケージ マネージャーに委任する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate-managers) | ✔️ |  |  | ✔️ |  |  |
| [アクセス パッケージ マネージャーを削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate-managers#remove-an-access-package-manager) | ✔️ |  |  | ✔️ |  |  |
| [カタログに新しいアクセス パッケージを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create) | ✔️ |  |  | ✔️ | ✔️ |  |
| [アクセス パッケージのリソース ロールを変更する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources) | ✔️ |  |  | ✔️ | ✔️ |  |
| [外部コラボレーションのポリシーを含むポリシーを作成および編集する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy) | ✔️ |  |  | ✔️ | ✔️ |  |
| [アクセス パッケージに ID を直接割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-an-identity) | ✔️ |  |  | ✔️ | ✔️ | ✔️ |
| [アクセス パッケージから ID を直接削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#remove-an-assignment) | ✔️ |  |  | ✔️ | ✔️ | ✔️ |
| [アクセス パッケージに割り当てられているユーザーを表示する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#view-who-has-an-assignment) | ✔️ |  |  | ✔️ | ✔️ | ✔️ |
| [アクセス パッケージの要求を表示する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-requests#view-requests) | ✔️ |  |  | ✔️ | ✔️ | ✔️ |
| [要求の配信エラーを表示する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-troubleshoot#view-a-requests-delivery-errors) | ✔️ |  |  | ✔️ | ✔️ | ✔️ |
| [要求を再処理する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-troubleshoot#reprocess-a-request) | ✔️ |  |  | ✔️ | ✔️ | ✔️ |
| [保留中の要求を取り消す](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-troubleshoot#cancel-a-pending-request) | ✔️ |  |  | ✔️ | ✔️ | ✔️ |
| [アクセス パッケージを非表示にする](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-edit#change-the-hidden-setting) | ✔️ |  |  | ✔️ | ✔️ |  |
| [アクセス パッケージを削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-edit#delete-an-access-package) | ✔️ |  |  | ✔️ | ✔️ |  |

タスクの最小特権ロールを決定するには、 [Microsoft Entra ID でタスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task#entitlement-management-least-privileged-roles)を参照することもできます。

注

エンタイトルメント管理ロールはエンタイトルメント管理内のアクションを承認しますが、それ自体では、Microsoft Entra 管理センターのテナント全体のアクセス設定は変更されません。 **[Microsoft Entra管理ポータルへのアクセスを制限する**] ユーザー設定が有効になっている場合、委任されたユーザーは、アクセス パッケージの割り当ての表示、追加、削除、再処理などの管理センタータスクを完了する前に、Microsoft Entra 管理センターへのアクセスを許可する必要があります。 この設定の詳細については、「 [既定のユーザーアクセス許可](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions)」を参照してください。

### カタログにリソースを追加するために必要なロール

グローバル管理者は、カタログ内の任意のグループ (クラウドが作成したセキュリティ グループまたはクラウドが作成した Microsoft 365 グループ)、アプリケーション、または SharePoint Online サイトを追加または削除することができます。

注

ユーザー管理者ロールが割り当てられている ID は、カタログを作成したり、所有していないカタログ内のアクセス パッケージを管理したりできなくなります。 カタログ所有者であるユーザー管理者は、ディレクトリ ロールに割り当て可能であるように構成されたグループを除き、所有しているカタログ内の任意のグループまたはアプリケーションを追加または削除することができます。 ロール割り当て可能なグループの詳細については、「 [Microsoft Entra ID でロール割り当て可能なグループを作成](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-create-eligible)する」を参照してください。 組織内のユーザーに、エンタイトルメント管理でカタログ、アクセス パッケージ、またはポリシーを構成するためのユーザー管理者ロールが割り当てられている場合は、代わりにこれらの ID **に IDENTITY Governance Administrator** ロールを割り当てる必要があります。

グローバル管理者ではない ID の場合、グループ、アプリケーション、または SharePoint Online サイトをカタログに追加するには、その ID には、そのリソースに対してアクションを実行する機能と、カタログのエンタイトルメント管理におけるカタログ所有者ロールの *両方* が必要です。 ID がリソースに対してアクションを実行できる最も一般的な方法は、リソースの管理を許可する Microsoft Entra ディレクトリ ロールを使用することです。 または、所有者が存在するリソースの場合、ID はリソースの所有者として割り当てられているため、アクションを実行できます。

ID がカタログにリソースを追加するときにエンタイトルメント管理によってチェックされるアクションは次のとおりです。

- セキュリティ グループまたは Microsoft 365 グループを追加するには、ID が `microsoft.directory/groups/members/update` および `microsoft.directory/groups/owners/update` アクションの実行を許可されている必要があります
- アプリケーションを追加するには、ID が `microsoft.directory/servicePrincipals/appRoleAssignedTo/update` アクションの実行を許可されている必要があります
- SharePoint Online サイトを追加するには、ID が SharePoint 管理者であるか、サイトの SharePoint サイト管理者である必要があります。

次の表に、これらのロールの組み合わせで ID がカタログにリソースを追加できるようにするアクションを含むロールの組み合わせの一部を示します。 カタログからリソースを削除するには、それと同じアクションを持つロールまたは所有権も必要です。

| Microsoft Entra ディレクトリ ロール | 権限管理の役割 | セキュリティグループを追加できる | Microsoft 365 グループを追加できます | アプリを追加できる | SharePoint Online サイトを追加できます |
| --- | --- | --- | --- | --- | --- |
| [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) | 該当なし | ✔️ | ✔️ | ✔️ | ✔️ |
| [ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) | 該当なし |  |  | ✔️ |  |
| [グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) | カタログ所有者 | ✔️ | ✔️ |  |  |
| [Intune 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#intune-administrator) | カタログ所有者 | ✔️ | ✔️ |  |  |
| [Exchange 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#exchange-administrator) | カタログ所有者 |  | ✔️ |  |  |
| [SharePoint 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#sharepoint-administrator) | カタログ所有者 |  | ✔️ |  | ✔️ |
| [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) | カタログ所有者 |  |  | ✔️ |  |
| [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) | カタログ所有者 |  |  | ✔️ |  |
| ユーザー | カタログ所有者 | グループ所有者の場合のみ | グループ所有者の場合のみ | アプリ所有者の場合のみ |  |

### ゲスト ユーザー ライフサイクルの委任された管理

通常、ゲスト招待者特権を持つロールのユーザーは、個々の外部ユーザーを組織に招待できます。この設定は [、外部コラボレーション設定](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)を使用して変更できます。

外部コラボレーションの管理では、コラボレーション プロジェクトの個々の外部ユーザーが事前に知られていない可能性があるため、外部組織で作業しているユーザーをエンタイトルメント管理ロールに割り当てることで、それらのユーザーが外部コラボレーション用のカタログ、アクセス パッケージ、ポリシーを構成できるようになります。 これらの構成により、共同作業相手の外部ユーザーは、組織のディレクトリとアクセス パッケージを要求して追加されるようにできます。

- 接続された組織の外部ディレクトリのユーザーがカタログ内のアクセス パッケージを要求できるようにするには、外部ユーザーに対して有効  のカタログ設定を [はい] 設定する必要があります。 この設定の変更は、カタログの管理者またはカタログ所有者が行うことができます。
- アクセス パッケージには、 [ディレクトリに含まれていないユーザーの](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#for-users-not-in-your-directory)ポリシー も設定されている必要があります。 このポリシーは、カタログの管理者、カタログ所有者、またはアクセス パッケージ管理者が作成できます。
- そのポリシーを持つアクセス パッケージを使用すると、スコープ内のユーザーは、ディレクトリにまだ存在しないユーザーを含め、アクセスを要求できるようになります。 要求が承認された場合、または承認を必要としない場合、ユーザーはディレクトリに自動的に追加されます。
- ポリシー設定が **[すべてのユーザー**] 用で、ユーザーが既存の接続済み組織に含まれていない場合は、新しい提案された接続済み組織が自動的に作成されます。 [接続されている組織の一覧を表示](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-organization#view-the-list-of-connected-organizations)し、不要になった組織を削除できます。

エンタイトルメント管理によって持ち込まれた外部ユーザーがアクセス パッケージへの最後の割り当てを失った場合の動作を構成することもできます。 [外部ユーザーのライフサイクルを管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users#manage-the-lifecycle-of-external-users)するための設定で、このディレクトリへのサインインをブロックしたり、ゲスト アカウントを削除したりできます。

#### 委任された管理者がディレクトリにないユーザーのポリシーを構成できないように制限する

[[ゲスト招待](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)] 設定を特定の管理者ロールに変更し、[**ゲストのセルフサービス サインアップを有効にする]** を **[いいえ**] に設定することで、管理役割を持たないユーザーが**外部コラボレーション**設定で個々のゲストを招待できないようにすることができます。

委任されたユーザーが、外部ユーザーから外部コラボレーションを要求できるようにエンタイトルメント管理を構成できないようにするには、すべてのグローバル管理者、Identity Governance 管理者、カタログ作成者、カタログ所有者にこの制約を伝えてください。それらの管理者はカタログを変更できるので、新規または更新されたカタログで新しいコラボレーションを誤って許可することがないようにするためです。 外部ユーザーに対してカタログが **[有効]** で **[いいえ**] に設定されていること、およびディレクトリにないユーザーに要求を許可するためのポリシーを含むアクセス パッケージがないことを確認する必要があります。

外部ユーザーに対して現在有効になっているカタログの一覧は、Microsoft Entra 管理センターで表示できます。

1. 少なくとも [ID ガバナンス管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。
2. **ID ガバナンス**&gt;**Catalogs** に移動します。
3. 外部ユーザーの  [有効] のフィルター設定を [はい] に変更します。
4. これらのカタログにアクセス パッケージの数がゼロ以外のものがある場合、それらのアクセス パッケージには、ディレクトリにないユーザーに対するポリシーが含まれることがあります。

### プログラムによるエンタイトルメント管理ロールへのロールの割り当ての管理

Microsoft Graph を使用して、カタログの作成者と、エンタイトルメント管理のカタログ固有のロール割り当てを表示して、更新することもできます。 委任された `EntitlementManagement.ReadWrite.All` アクセス許可を持つアプリケーションを持つ適切なロールのユーザーは、Graph API を呼び出してエンタイトルメント管理の [ロール定義を一覧表示](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-list-roledefinitions) し、それらのロール定義への [ロールの割り当てを一覧表示](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-list-roleassignments) できます。

たとえば、特定のユーザーまたはグループが割り当てられているエンタイトルメント管理固有のロールを表示するには、Graph クエリを使用してロールの割り当てを一覧表示し、ユーザーまたはグループの ID を `principalId` クエリ フィルターの値として指定します。次に例を示します。

```http
GET https://graph.microsoft.com/v1.0/roleManagement/entitlementManagement/roleAssignments?$filter=principalId eq 'aaaaaaaa-bbbb-cccc-1111-222222222222'&$expand=roleDefinition&$select=id,appScopeId,roleDefinition
```

カタログに固有のロールの場合、応答内の `appScopeId` は、ユーザーにロールが割り当てられているカタログを示します。 この応答では、エンタイトルメント管理でのロールに対するプリンシパルの明示的な割り当てのみが取得されます。ディレクトリ ロールを介してアクセス権を持つユーザー、およびロールに割り当てられたグループのメンバーシップを介してアクセス権を持つユーザーの結果は返されません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-delegate-catalog"} -->
## エンタイトルメント管理でカタログ作成者にアクセス ガバナンスを委任する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate-catalog
- Service: entra-id-governance / entitlement-management
- Article date: 2025-03-10
- Summary: カタログ作成者とプロジェクト マネージャーが自分でアクセスを管理できるよう、IT 管理者からアクセス ガバナンスを委任する方法について説明します。

カタログは、リソースとアクセス パッケージのコンテナーです。 関連するリソースとアクセス パッケージをグループ化するときは、カタログを作成します。 既定では、ロール Identity Governance Administrator は、 [カタログを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create)できる最小特権ロールであり、さらに最小限の特権オプションとして他のユーザーをカタログ所有者として追加できます。

注

最小特権アクセスに従い、可能であれば、エンタイトルメント管理では ID ガバナンス管理者ロールを使用することをお勧めします。

組織がカタログを委任できる方法は 3 つあります。

- パイロット プロジェクトを開始する際、ID ガバナンス管理者はカタログを[作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create)して管理できます。 その後、パイロットから運用環境に移行するときに、[カタログに所有者として非管理者を割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#add-more-catalog-owners)ことでカタログを委任できます。こうすることで、それらのユーザーが今後もポリシーを保守できるようになります。
- 所有者のいないリソースがある場合、管理者はカタログを作成し、それらのリソースを各カタログに追加してから、[カタログに所有者として非管理者を割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#add-more-catalog-owners)ことができます。 こうすることで、管理者ではなく、リソース所有者でもないユーザーが、それらのリソースに対して自分のアクセス ポリシーを管理できるようになります。
- リソースに所有者がいる場合、管理者は `All Employees` 動的グループなどのユーザーのコレクションをカタログ作成者ロールに割り当てることができます。これにより、そのグループに属し、リソースを所有するユーザーは、自分のリソースのカタログを作成できます。

この記事では、管理者ではないユーザーに委任して、自分のカタログを作成できるようにする方法について説明します。 このようなユーザーを Microsoft Entra エンタイトルメント管理で定義されたカタログ作成者ロールに追加できます。 個々のユーザーを追加することも、グループを追加することもできます。グループのメンバーはカタログを作成できるようになります。 カタログを作成したら、カタログに所有するリソースを追加できます。 彼らは、アクセス パッケージやポリシー (既存の[接続されている組織](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-organization)を参照するポリシーを含む) を作成できます。

委任する既存のカタログがある場合は、[リソースのカタログの作成と管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#add-more-catalog-owners)に関する記事に進んでください。

### IT 管理者としてカタログ作成者に委任する

カタログ作成者ロールにユーザーを割り当てるには、これらの手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に [Identity Governance 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)以上としてサインインします。
2. **ID ガバナンス**&gt;**Entitlement management**&gt;**settings** に移動します。
3. **[編集]** を選択します。

    [Image: カタログ作成者を追加するための設定]
4. **[エンタイトルメント管理の委任]** セクションで、**[カタログ作成者の追加]** を選択し、このエンタイトルメント管理ロールを委任するユーザーまたはグループを選択します。
5. **[選択]** を選択します。
6. **[保存]** を選択します。

### 委任されたロールに Microsoft Entra 管理センターへのアクセスを許可する

委任されたロール (カタログ作成者、アクセス パッケージ マネージャーなど) がアクセス パッケージを管理するために Microsoft Entra 管理センターにアクセスできるようにするには、管理ポータルの設定を確認する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に [Identity Governance 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)以上としてサインインします。
2. **Entra ID**&gt;**ユーザー**&gt;**ユーザー設定**に移動します。
3. **[Microsoft Entra 管理ポータルへのアクセスを制限する]** が **[いいえ]** に設定されていることをご確認ください。

    [Image: Microsoft Entra ユーザー設定 - 管理 portal]

### プログラムでロールの割り当てを管理する

Microsoft Graph を使用して、カタログの作成者と、エンタイトルメント管理のカタログ固有のロール割り当てを表示して、更新することもできます。 委任された `EntitlementManagement.ReadWrite.All` 権限を持つアプリケーションを使用する適切なロールのユーザーは、Graph API を呼び出して、エンタイトルメント管理の[ロール定義を一覧表示し](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-list-roledefinitions)、それらのロール定義に対する[ロール割り当てを一覧表示](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-list-roleassignments)することができます。

カタログ作成者ロールに割り当てられたユーザーとグループの一覧を取得するには、定義 ID `ba92d953-d8e0-4e39-a797-0cbedb0a89e8` を持つロールを使用して Graph クエリを使用します。

```http
GET https://graph.microsoft.com/v1.0/roleManagement/entitlementManagement/roleAssignments?$filter=roleDefinitionId eq 'ba92d953-d8e0-4e39-a797-0cbedb0a89e8'&$expand=principal
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-delegate-managers"} -->
## エンタイトルメント管理でアクセス パッケージ管理者にアクセス ガバナンスを委任する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate-managers
- Service: entra-id-governance / entitlement-management
- Article date: 2024-07-15
- Summary: アクセス パッケージ管理者とプロジェクト マネージャーが自分でアクセスを管理できるよう、IT 管理者からアクセス ガバナンスを委任する方法について説明します。

カタログ内のアクセス パッケージの作成と管理を委任するには、アクセス パッケージ管理者ロールにユーザーを追加します。 アクセス パッケージ管理者は、カタログ内のリソースに対するアクセスをユーザーが要求するニーズを理解しておく必要があります。 たとえば、あるプロジェクトにあるカタログが使用される場合、プロジェクト リーダーはそのカタログのアクセス パッケージ管理者になることがあります。 アクセス パッケージ管理者はカタログにリソースを追加できませんが、カタログ内のアクセス パッケージやポリシーを管理できます。 アクセス パッケージ管理者に委任すると、その管理者は次のことを担当します。

- カタログ内のリソースに対してユーザーに与えられるロール
- アクセスを必要とするユーザー
- アクセス要求の承認が必要なユーザー
- プロジェクトの継続期間

彼らは、アクセス パッケージやポリシー (既存の[接続されている組織](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-organization)を参照するポリシーを含む) を作成できます。 アクセス パッケージが作成されたら、他のユーザーがこれらのアクセス パッケージを要求したり、それに割り当てられたりするようにできます。

この動画では、カタログの所有者からアクセス パッケージ管理者にアクセス ガバナンスを委任する方法を概説します。

カタログ所有者ロールとアクセス パッケージ マネージャー ロールに加えて、カタログ閲覧者ロールにユーザーを追加することもできます。これにより、カタログへの表示専用アクセスが付与されます。また、アクセス パッケージ割り当てマネージャー ロールにユーザーを追加することもできます。これにより、ユーザーは割り当てを変更できますが、パッケージやポリシーにはアクセスすることはできません。

### カタログ所有者としてアクセス パッケージ管理者に委任する

アクセス パッケージ管理者のロールにユーザーを割り当てるには、以下の手順のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に [Identity Governance 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)以上としてサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者があります。
2. **ID ガバナンス**&gt;**Catalogs** に移動します。
3. [カタログ] ページで、管理者を追加するカタログを開きます。
4. 左側のメニューで、**[ロールと管理者]** を選択します。

    [Image: カタログのロールと管理者]
5. **[アクセス パッケージ管理者の追加]** を選択し、それらのロールのメンバーを選択します。
6. **[選択]** を選択すると、これらのメンバーが追加されます。

### アクセス パッケージ管理者を削除する

アクセス パッケージ管理者のロールからユーザーを削除するには、以下の手順のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に [Identity Governance 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)以上としてサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者があります。
2. **ID ガバナンス**&gt;**Catalogs** に移動します。
3. [カタログ] ページで、管理者を追加するカタログを開きます。
4. 左側のメニューで、**[ロールと管理者]** を選択します。
5. 削除するアクセス パッケージ管理者の横にチェックマークを追加します。
6. **[削除]** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-dynamic-approval"} -->
## カスタム拡張機能を使用してアクセス パッケージの承認要件を外部で決定する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-dynamic-approval
- Service: entra-id-governance / entitlement-management
- Article date: 2025-11-04
- Summary: カスタム拡張機能を使用して外部からアクセス パッケージの承認要件を動的に決定する方法に関するガイド。

エンタイトルメント管理では、アクセス パッケージ要求の承認者を直接割り当てるか、動的に決定することができます。 エンタイトルメント管理は、要求元マネージャー、第 2 レベルのマネージャー、接続された組織のスポンサーなどの承認者を動的に決定することをネイティブにサポートします。

[Image: エンタイトルメント管理での承認者のネイティブ サポートのスクリーンショット。]

[Azure Logic Apps](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logic-apps-integration) を呼び出す[カスタム拡張機能](https://learn.microsoft.com/ja-jp/azure/logic-apps/logic-apps-overview)の導入により、組織固有のビジネス ロジックに基づいて、各アクセス パッケージ割り当て要求の承認要件を動的に決定できるようになりました。 アクセス パッケージの割り当て要求プロセスは、Azure Logic Apps でホストされているビジネス ロジックが [承認ステージ](https://learn.microsoft.com/ja-jp/graph/api/resources/accesspackageapprovalstage) を返すまで一時停止します。承認ステージは、 [マイ アクセス ポータル](https://myaccess.microsoft.com)を介してサブクエリ承認プロセスで利用されます。 たとえば、アクセス要求をアクセス パッケージを要求する担当者の部門長がアクセス要求を承認する必要がある場合、この機能を使用すると、人事 (HR) システムなどの外部システムに対してクエリを実行して、現在の部門長をすぐに検索し、特定のアクセス要求の承認者として割り当てることができます。

[Image: カスタム拡張機能を使用して承認者を決定する例のスクリーンショット。]

この記事では、カスタム拡張機能、その基になる Azure Logic App の作成、カタログでのシステム割り当て ID とロールの設定、ビジネス ロジックを実行するためのロジック アプリ アクションの編集、正常に実行されたかどうかを確認するためのテストについて説明します。

この機能で SAP 組織のビジネス コンテキストを使用して承認を受ける方法を知りたいですか? Sap on Azure ポッドキャストのこのエピソードを確認してください。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID ガバナンスまたは Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。

### Prerequisites

- 少なくとも、カスタム拡張機能が作成または存在するカタログの [エンタイトルメント管理カタログ所有者](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate#entitlement-management-roles) ロール。
- ロジック アプリ自体、リソース グループ、サブスクリプション、またはロジック アプリが存在する管理グループに対する、少なくともロジック アプリ[共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles)の [Azure 組み込みロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles/integration#logic-app-contributor)。

### カスタム拡張機能と Azure Logic App を作成する

カスタム拡張機能とその基になる Azure Logic App を作成するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、カスタム拡張機能が配置されるカタログの少なくとも[カタログ所有者](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate#entitlement-management-roles)としてサインインします。
2. **ID ガバナンス**&gt;**Catalogs** に移動します。
3. [カタログの概要] ページで、カスタム拡張機能が配置される既存のカタログを選択するか、新しいカタログを作成します。
4. カスタム拡張機能を作成する特定のカタログ ページで、[カスタム **拡張機能**] を選択します。 [Image: カスタム拡張機能が追加されているカタログ ページのスクリーンショット。]
5. [ **カスタム拡張機能の追加]** を選択して、カスタム拡張機能の名前と説明を追加します。 完了したら **[次へ]** を選択します。 [Image: カスタム拡張機能の基本のスクリーンショット。]
6. [ **拡張機能の種類** ] ページで、[ **要求ワークフロー] (アクセス パッケージが要求、承認、許可、または削除されたときにトリガーされます)** を選択し、[ **次へ**] を選択します。 [Image: カスタム拡張機能の拡張機能の種類を選択するスクリーンショット。]
7. [ **拡張機能の構成]** ページで、[動作] で **[起動して待機**] を選択し、[応答データ] で [ **承認ステージ**] を選択し、[ **次へ**] を選択します。 [Image: カスタム拡張機能承認ステージ オプションのスクリーンショット。]
8. [ **詳細** ] ページで、作成するロジック アプリのサブスクリプション、リソース グループ、および名前を選択します。 この情報を入力したら、[ **ロジック アプリの作成**] を選択します。 ロジック アプリが作成されたら、[ **次へ**] を選択します。
9. [ **確認と作成** ] ページで、すべての詳細が正しいことを確認し、[ **作成**] を選択します。

### アクセス パッケージの割り当てポリシーでカスタム拡張機能を参照する

カスタム拡張機能とロジック アプリを作成したら、次の手順を実行して、アクセス パッケージの割り当てポリシーでカスタム拡張機能を参照できます。

1. カスタム拡張機能が作成されたカタログを選択します。
2. カタログ ページで、[ **アクセス パッケージ**] を選択し、更新するポリシーのアクセス パッケージを選択します。
3. アクセス パッケージの概要ページで、[ **ポリシー**] を選択し、編集するポリシーを選択します。 [Image: アクセス パッケージのポリシー一覧のスクリーンショット。]
4. [**要求**] の [**ポリシーの編集]** ページで、[**承認が必要**] ボックスを [はい] に設定すると、カスタム拡張機能を承認者として追加できます。 次の例は、最初の承認者として使用されているカスタム拡張機能を示しています。 [Image: アクセス パッケージ ポリシーの最初の承認者としてのカスタム拡張機能のスクリーンショット。]
5. **[更新]** を選択します。

更新が完了したら、編集したポリシーに移動し、[ **承認ステージの詳細**] を選択して変更を確認できます。

[Image: 編集された承認ステージの詳細のスクリーンショット。]

### ロジック アプリに割り当てられた ID を設定し、そのロールを割り当てる

Azure ロジック アプリを作成したら、システム割り当て ID を有効にし、次の手順を実行して適切なロールを付与する必要があります。

1. Azure portal にサインインし、少なくともロジック アプリ共同作成者の [Azure 組み込みロールを](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles) 使用して [ロジック アプリに移動します](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles/integration#logic-app-contributor)。
2. ロジック アプリの概要ページで、 **設定**&gt;**Identity** に移動します。
3. [ID] ページで、システム割り当てマネージド ID を [Image: 有効にする ロジック アプリ のシステム割り当てマネージド ID を有効にするスクリーンショット。]
4. **保存** を選択します。
5. Microsoft Entra 管理センターに少なくとも [カタログ所有者](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate#entitlement-management-roles)のロールとして戻り、カスタム拡張機能を作成したカタログに移動し、[ **ロールと管理者**] を選択します。
6. [ロールと管理者] ページで、[ **アクセス パッケージ割り当てマネージャーの追加**] を選択し、作成したロジック アプリを選択します。 [Image: カタログのアクセス パッケージ割り当てマネージャーとしてロジック アプリを追加するスクリーンショット。]

### ロジック アプリと対応するビジネス ロジックを構成する

カタログのアクセス パッケージ割り当てマネージャー ロールが与えられた Azure Logic App では、ロジック アプリに移動して編集し、Microsoft Entra と通信する必要があります。 これを行うには、次の手順を実行します。

1. 作成されたロジック アプリで、 **開発ツール**&gt;**Logic アプリ デザイナー**に移動します。
2. デザイナー ページで、 **手動** トリガーの下にあるすべてのものを削除し、[アクションの **追加** ] ボタンを選択します。 [Image: ロジック アプリ デザイナーでのアクションの追加のスクリーンショット。]
3. [アクションの追加] ウィンドウで、[ **HTTP**] を選択します。
4. **[HTTP**] ウィンドウの [パラメーター] で、次のパラメーターを入力します。

    - URI： `https://graph.microsoft.com/beta@{triggerBody()?['CallbackUriPath']}`
    - 方法: 投稿
    - 認証の種類: マネージド ID
    - マネージド ID: システム割り当てマネージド ID
    - 聴衆：`https://graph.microsoft.com`
5. [HTTP 設定] で、 **非同期パターン**を無効にします。 [Image: ロジック アプリの http 呼び出しで非同期パターンを無効にするスクリーンショット。]
6. HTTP トリガーに変更を加えた後、[ **保存]** を選択します。

### ロジック アプリにビジネス ロジックを追加する

Microsoft Entra との通信用に構成されたロジック アプリを使用して、アプリで実行する操作を追加できるようになりました。 ロジック アプリのアクションは、ロジック アプリ用に構成した **HTTP** セクションの本文に追加されます。 これを編集するには、次の操作を行います。

1. 作成されたロジック アプリで、 **開発ツール**&gt;**Logic アプリ デザイナー**に移動します。
2. ロジック アプリ デザイナー ページで、[ **HTTP**] を選択します。
3. [HTTP] ウィンドウの [ **パラメーター]** の下の [ **本文** ] まで下にスクロールし、クエリを実行するパラメーターに基づいてロジック データを入力します。 詳細については、「 [Azure Logic Apps のワークフローから外部 HTTP または HTTPS エンドポイントを呼び出す](https://learn.microsoft.com/ja-jp/azure/connectors/connectors-native-http?tabs=standard)」を参照してください。 [Image: ロジック アプリにビジネス ロジックを追加するスクリーンショット。]

    Note

    本文アクションの例については、 [HTTP アクションの例](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-dynamic-approval#http-action-example)を参照してください。
4. ビジネス ロジックの追加が完了したら、[保存] を選択 **します**。

### 拡張機能が機能したことを確認する

カスタム拡張機能が機能することを確認するには、次の手順に従って、アクセス パッケージへのアクセスを要求し、アクセス パッケージ ページで **要求** の詳細を表示します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、カスタム拡張機能が配置されているカタログの少なくとも[カタログ所有者](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate#entitlement-management-roles)としてサインインします。

    Tip

    このタスクを完了できるその他の最小限の特権ロールには、Access パッケージ マネージャー、Access パッケージ割り当てマネージャー、ID ガバナンス管理者が含まれます。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**アクセスパッケージ**に移動します。
3. [アクセス パッケージ] ページで、要求を表示するアクセス パッケージを開きます。
4. **[要求]** を選択します。
5. [要求] ページで、詳細を表示する要求を選択し、アクセス パッケージが正常に配信されたことを確認します。 [Image: アクセス パッケージの要求の詳細を表示します。]

### HTTP アクションの例

HTTP 本文に配置できるアクションの次の例は、プライマリ承認者を識別するロジック アプリです。 次のプロンプトが表示されたら、このコードに[独自の変数を渡す必要](https://learn.microsoft.com/ja-jp/azure/logic-apps/logic-apps-create-variables-store-values?tabs=consumption)があります。

```
{
  "data": {
    "@@odata.type": "microsoft.graph.assignmentRequestApprovalStageCallbackData",
    "approvalStage": {
      "durationBeforeAutomaticDenial": "P2D",
      "escalationApprovers": [],
      "fallbackEscalationApprovers": [],
      "fallbackPrimaryApprovers": [],
      "isApproverJustificationRequired": false,
      "isEscalationEnabled": false,
      "primaryApprovers": [
        {
          "@@odata.type": "#microsoft.graph.singleUser",
          "description": "This is the primary approver for the access package requested by the user.",
          "id": "<Dynamically assigned variable>",
          "isBackup": false
        }
      ]
    },
    "customExtensionStageInstanceDetail": "A approval stage from Logic Apps",
    "customExtensionStageInstanceId": "@{triggerBody()?['CustomExtensionStageInstanceId']}",
    "stage": "assignmentRequestDeterminingApprovalRequirements"
  },
  "source": "LogicApps",
  "type": "microsoft.graph.accessPackageCustomExtensionStage.assignmentRequestCreated"
}
```

Note

この例ではユーザー ID を使用していますが、primaryApprovers セクションと escalationApprovers セクションには、エンタイトルメント管理でサポートされている有効な [subjectSet を](https://learn.microsoft.com/ja-jp/graph/api/resources/subjectset) 含めることができます。 パブリック プレビューでは、Microsoft Graph のベータ エンドポイントに対して再開呼び出しを実行する必要があります。 ただし、再開呼び出し本文で提供される[承認ステージ](https://learn.microsoft.com/ja-jp/graph/api/resources/accesspackageapprovalstage)は、[ベータ](https://learn.microsoft.com/ja-jp/graph/api/resources/accesspackageapprovalstage)[規則ではなく、v1.0 規則](https://learn.microsoft.com/ja-jp/graph/api/resources/approvalstage?view=graph-rest-beta&preserve-view=true)に従う必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-external-users"} -->
## エンタイトルメント管理で外部ユーザーのアクセスを管理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users
- Service: entra-id-governance / entitlement-management
- Article date: 2025-09-09
- Summary: エンタイトルメント管理で外部ユーザーのアクセスを管理するために指定できる設定について説明します。

エンタイトルメント管理では、[Microsoft Entra 企業間 (B2B)](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) を利用してアクセスを共有することで、組織外のユーザーと共同で作業できるようにします。 Microsoft Entra B2B では、外部ユーザーは自分のホーム ディレクトリで認証を行いますが、こちらのディレクトリに表示されます。 こちらのディレクトリ内のその表現により、ユーザーにこちらのリソースへのアクセスを割り当てることができます。

この記事では、外部ユーザーのアクセスを管理するために指定できる設定について説明します。

### エンタイトルメント管理の効果

[Microsoft Entra B2B](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) 招待状を使用する場合、こちらのリソース ディレクトリに取り込んで共同作業する外部ゲスト ユーザーのメール アドレスをあらかじめ知っている必要があります。 各ユーザーを直接招待すると、小規模または短期のプロジェクトに取り組み、すべての参加者を既に把握している場合に最適です。 このプロセスは、多くのユーザーを操作する場合や、参加者が時間の経過と共に変化する場合は、管理が困難です。 たとえば、別の組織と仕事をしていて、その組織との連絡窓口が一本化されていたとしても、時間が経てば、その組織からさらに多くのユーザーもアクセスを必要とするようになるでしょう。

エンタイトルメント管理を使用すれば、指定した組織のユーザーがアクセス パッケージを自分で要求できるようにするポリシーを定義できます。 そのポリシーには、承認が必要かどうか、アクセス レビューが必要かどうか、およびそのアクセスの有効期限が含まれます。 ほとんどの場合、どのユーザーがディレクトリに取り込まれるかを適切に監視するために、承認を要求する必要があります。 承認が必要な場合は、主な外部組織パートナーに対して、その外部組織から 1 人以上のユーザーをディレクトリに招待し、スポンサーとして指定して、そのスポンサーが承認者となるように構成することを検討する必要があります。これらのユーザーは、自分の組織のどの外部ユーザーがアクセスを必要とするか、知っている可能性が高いからです。 アクセス パッケージを構成したら、外部組織の連絡先担当者 (スポンサー) に送信できるように、アクセス パッケージの要求リンクを取得します。 その連絡先は外部組織の他のユーザーと共有することができるほか、それらのユーザーがこのリンクを使用してアクセス パッケージを要求できます。 その組織に属する、ディレクトリに招待済みのユーザーもそのリンクを使用できます。

エンタイトルメント管理を使用して、独自の Microsoft Entra ディレクトリを持たない組織からユーザーを取り込むこともできます。 そのドメイン用のフェデレーション ID プロバイダーを構成するか、メールベースの認証を使用することができます。 Microsoft アカウントを持つユーザーを含め、ソーシャル ID プロバイダーからユーザーを取り込むこともできます。

一般的に、要求が承認されると、エンタイトルメント管理によって必要なアクセス権がユーザーにプロビジョニングされます。 ユーザーがまだディレクトリに参加していない場合は、エンタイトルメント管理によって先にそのユーザーが招待されます。 ユーザーが招待されると、Microsoft Entra ID によってそれらのユーザーの B2B ゲスト アカウントが自動的に作成されますが、ユーザーにメールは送信されません。 管理者が、他組織のドメインへの招待を許可またはブロックする [B2B 許可/ブロックリスト](https://learn.microsoft.com/ja-jp/entra/external-id/allow-deny-list)を設定して、共同作業を許可する組織を制限していた可能性があります。 ユーザーのドメインがそれらのリストによって許可されていない場合、それらのユーザーは招待されず、リストが更新されるまでアクセス権を割り当てることはできません。

外部ユーザーのアクセスを恒久的にしたくない場合、180 日などの有効期限をポリシーに指定します。 180 日経ってもアクセス権が延長されない場合、エンタイトルメント管理ではそのアクセス パッケージに関連付けられているすべてのアクセス権が削除されます。 既定では、エンタイトルメント管理によって招待されたユーザーに他のアクセス パッケージが割り当てられていない場合、最後の割り当てを失った時点で、そのゲスト アカウントは 30 日間サインインからブロックされ、その後削除されます。 これにより、不要なアカウントの拡散を防ぎます。 以下のセクションで説明するように、これらの設定は構成可能です。

### 外部ユーザーのアクセスのしくみ

次の図と手順では、アクセス パッケージへのアクセスを外部ユーザーに許可する方法の概要を説明します。

[Image: 外部ユーザーのライフサイクルを示す図]

1. 共同作業する Microsoft Entra ディレクトリまたはドメインに対して、[接続された組織を追加](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-organization)します。 ソーシャル ID プロバイダーに接続された組織を構成することもできます。
2. カタログ内でアクセス パッケージを含めるためのカタログ設定 **[外部ユーザーに対して有効]** が **[はい]** であることをチェックします。
3. ディレクトリ内にない ID のポリシーを含むアクセス パッケージ [をディレクトリに](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#allow-users-service-principals-and-agent-identities-in-your-directory-to-request-the-access-package) 作成し、要求できる接続された組織、承認者、ライフサイクルの設定を指定します。 ポリシーで "特定の接続された組織" オプションまたは "すべての接続された組織" オプションを選択した場合は、以前に構成されたことのある組織のユーザーのみが要求を行えます。 ポリシーで "すべてのユーザー" オプションを選択した場合、まだディレクトリの一員でもなく、接続された組織のどれかの一員でもないユーザーも含め、すべてのユーザーが要求を行えます。
4. [アクセス パッケージの非表示設定](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-edit#change-the-hidden-setting)をチェックして、アクセス パッケージが非表示になっていることを確認します。 非表示になっていない場合、そのアクセス パッケージのポリシー設定によって許可されているユーザーはすべて、テナントのマイ アクセス ポータルでこのアクセス パッケージを参照できます。
5. 外部組織の連絡先に[マイ アクセス ポータルのリンク](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-settings)を送信し、アクセス パッケージを要求するためにユーザーと共有できるようにします。
6. 外部ユーザー (この例では**要求者 A**) は、マイ アクセス ポータルのリンクを使用して、アクセス パッケージへの[アクセスを要求](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-access)します。 マイ アクセス ポータルでは、ユーザーが接続された組織の一員としてサインインする必要があります。 ユーザーのサインイン方法は、接続された組織と外部ユーザーの設定で定義されているディレクトリまたはドメインの認証の種類によって異なります。
7. 承認者が[要求を承認します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-approve) (ポリシーが承認を要求すると仮定)。
8. 要求は[配信中状態](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-process)になります。
9. B2B 招待プロセスを使用して、ゲスト ユーザー アカウントがディレクトリに作成されます (この例では**要求者 A (ゲスト)** )。 [許可リストまたはブロックリスト](https://learn.microsoft.com/ja-jp/entra/external-id/allow-deny-list)が定義されている場合、リストの設定が適用されます。
10. ゲスト ユーザーに、アクセス パッケージ内のすべてのリソースへのアクセスが割り当てられます。 Microsoft Entra ID および他の Microsoft Online Services や接続された SaaS アプリケーションに対して変更が行われるまで、しばらく時間がかかることがあります。 詳細については、「[変更が適用されるタイミング](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources#when-changes-are-applied)」を参照してください。
11. 外部ユーザーは、アクセスが[配信された](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-process)ことを示すメールを受信します。
12. 外部ユーザーは、メールのリンクを選択するか、ディレクトリ リソースのいずれかに直接アクセスを試みて招待プロセスを完了することにより、リソースにアクセスできます。
13. ポリシーの設定に有効期限が設定されている場合は、後に外部ユーザーに対するアクセス パッケージの割り当てが期限切れになると、そのアクセス パッケージからの外部ユーザーのアクセス権限が削除されます。
14. 外部ユーザーの設定のライフサイクルによっては、外部ユーザーにアクセス パッケージの割り当てがなくなった場合、外部ユーザーはサインインをブロックされ、外部ユーザー アカウントはディレクトリから削除されます。

### 外部ユーザーの設定

組織外のユーザーがアクセス パッケージを要求し、それらのアクセス パッケージ内のリソースに確実にアクセスできるようにするには、適切に構成されていることを確認しなければならない設定がいくつかあります。

#### 外部ユーザーに対してカタログを有効にする

- 既定では、[新しいカタログ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create)を作成すると、外部ユーザーがカタログ内のアクセス パッケージを要求できるようになります。 **[外部ユーザーに有効]** が **[はい]** に設定されていることを確認してください。

    [Image: カタログの設定を編集する]

    自分が管理者またはカタログ所有者である場合は、Microsoft Entra 管理センターのカタログ一覧内で、**[外部ユーザーに有効]** のフィルター設定を **[はい]** に変更することで、外部ユーザーに対して現在有効になっているカタログの一覧を表示できます。 そのフィルター処理されたビューに表示されるカタログのどれかが 0 以外のアクセス パッケージ数を持つ場合、それらのアクセス パッケージには、外部ユーザーが要求を行うことを許可する[ディレクトリに含まれないユーザー用](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#for-users-not-in-your-directory)のポリシーが含まれている可能性があります。

#### Microsoft Entra B2B の外部コラボレーション設定を構成する

- ゲストが他のゲストをディレクトリに招待できるようにすることは、エンタイトルメント管理の外部でゲストの招待が発生する可能性があることを意味します。 **[ゲストは招待ができる]** を **[いいえ]** に設定して、適切に管理された招待のみを許可することをお勧めします。
- 以前に B2B 許可リストを使用していた場合は、そのリストを削除するか、エンタイトルメント管理を使用してパートナーにする必要があるすべての組織のすべてのドメインがリストに追加されていることを確認する必要があります。 または、B2B ブロックリストを使用している場合は、パートナーにする必要があるすべての組織のすべてのドメインがリストに追加されていることを確認する必要があります。
- **すべてのユーザー** (すべての接続されている組織と新しい外部ユーザー) にエンタイトルメント管理ポリシーを作成し、ユーザーがディレクトリ内の接続されている組織に属していない場合は、パッケージを要求するときに、接続されている組織が自動的に作成されます。 ただし、お使いの B2B の[許可またはブロックリスト](https://learn.microsoft.com/ja-jp/entra/external-id/allow-deny-list)の設定が優先されます。 したがって、許可リストを使用していた場合は、それを削除して、**すべてのユーザー**がアクセスを要求できるようにし、ブロックリストを使用している場合は、ブロックリストからすべての認可済みドメインを除外することをお勧めします。
- **すべてのユーザー** (すべての接続されている組織とすべての新しい外部ユーザーを含む) のエンタイトルメント管理ポリシーを作成する場合は、まず、ディレクトリに対してメールのワンタイム パスコード認証を有効にする必要があります。 詳細については、「[電子メール ワンタイム パスコード認証](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode)」を参照してください。
- Microsoft Entra B2B の外部コラボレーション設定の詳細については、「[外部コラボレーションの設定を構成する](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)」を参照してください。

    [Image: Microsoft Entra の外部コラボレーション設定]

#### テナント間のアクセス設定を確認する

- 受信 B2B コラボレーションのクロステナント アクセス設定で、アクセスの要求と割り当てを許可していることを確認します。 現在または将来の接続されている組織の一部であるテナントが設定で許可されていること、およびそれらのテナントのユーザーが招待をブロックされていないことを確認する必要があります。 さらに、これらのユーザーが、コラボレーション シナリオを有効にするアプリケーションに対して認証できるように、テナント間アクセス設定によって許可されていることを確認します。 詳しくは、「[テナント間アクセス設定を構成する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)」をご覧ください。
- Microsoft Entra テナント用に接続された組織を別の Microsoft クラウドから作成する場合は、テナント間アクセス設定も適切に構成する必要があります。 詳細については、「 [Microsoft クラウド設定の構成](https://learn.microsoft.com/ja-jp/entra/external-id/cross-cloud-settings)」を参照してください。

#### 条件付きアクセス ポリシーを確認する

- ゲスト ユーザーに影響を与える条件付きアクセス ポリシーからエンタイトルメント管理アプリを除外してください。 そうしないと、条件付きアクセス ポリシーによって、MyAccess へのアクセスやディレクトリへのサインインがブロックされる可能性があります。 たとえば、ゲストに登録済みのデバイスがなく、既知の場所にも登録されておらず、多要素認証 (MFA) に再登録したくない場合、条件付きアクセス ポリシーにこれらの要件を追加すると、ゲストのエンタイトルメント管理の使用がブロックされます。 詳細については、「[Microsoft Entra 条件付きアクセスの条件とは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions)」を参照してください。
- 条件付きアクセスがエンタイトルメント管理アプリを除外するだけでなく、すべてのクラウド アプリケーションをブロックしている場合は、 *要求承認読み取りプラットフォーム* も条件付きアクセス ポリシーで除外されていることを確認します。 まず、自分に必要なロール (条件付きアクセス管理者、アプリケーション管理者、属性割り当て管理者、および属性定義管理者) が割り当てられていることを確認します。 次に、適切な名前と値を使用してカスタム セキュリティ属性を作成します。 エンタープライズ アプリケーションで [要求承認読み取りプラットフォーム] のサービス プリンシパルを見つけ、選択した値が設定されたカスタム属性をこのアプリケーションに割り当てます。 条件付きアクセス ポリシーで、 *要求承認読み取りプラットフォーム*に割り当てられたカスタム属性の名前と値に基づいて、選択したアプリケーションを除外するフィルターを適用します。 条件付きアクセス ポリシーでのアプリケーションのフィルター処理の詳細については、「[条件付きアクセス: アプリケーションのフィルター処理](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-filter-for-applications)」を参照してください。

    [Image: アプリの除外オプションのスクリーンショット。]

    [Image: クラウド アプリを除外する選択のスクリーンショット。]

    [Image: ゲスト アプリの除外の選択のスクリーンショット。]

注

エンタイトルメント管理アプリには、MyAccess のエンタイトルメント管理側、Microsoft Entra 管理センターのエンタイトルメント管理側、MS グラフのエンタイトルメント管理部分が含まれます。 後者の 2 つではアクセスに追加のアクセス許可が必要なため、明示的なアクセス許可が指定されていない限り、ゲストはアクセスできません。

#### SharePoint Online の外部共有設定を確認する

- 外部ユーザーのアクセス パッケージに SharePoint Online サイトを含めるには、組織レベルの外部共有設定が **[すべてのユーザー]** (ユーザーがサインインを必要としない)、または **[新規および既存のゲスト]** (ゲストがサインインするか、確認コードを入力する必要がある) に設定されていることを確認してください。 詳細については、「[外部共有を有効または無効にする](https://learn.microsoft.com/ja-jp/sharepoint/turn-external-sharing-on-or-off#change-the-organization-level-external-sharing-setting)」を参照してください。
- エンタイトルメント管理の外部で外部共有を制限するには、外部共有設定を **[既存のゲスト]** に設定します。 その後は、エンタイトルメント管理を通じて招待された新しいユーザーのみが、これらのサイトへのアクセス権を獲得することができます。 詳細については、「[外部共有を有効または無効にする](https://learn.microsoft.com/ja-jp/sharepoint/turn-external-sharing-on-or-off#change-the-organization-level-external-sharing-setting)」を参照してください。
- サイトレベルの設定で、ゲスト アクセスが有効になっていることを確認します (前述と同じオプションを選択します)。 詳細については、「[サイトの外部共有を有効または無効にする](https://learn.microsoft.com/ja-jp/sharepoint/change-external-sharing-site)」を参照してください。

#### Microsoft 365 グループの共有設定を確認する

- 外部ユーザーのアクセス パッケージに Microsoft 365 グループを含めるには、 **[ユーザーが組織に新しいゲストを追加できるようにします]** が **[オン]** に設定されていることを確認して、ゲスト アクセスを許可します。 詳細については、「[Microsoft 365 グループへのゲスト アクセスの管理](https://learn.microsoft.com/ja-jp/microsoft-365/admin/create-groups/manage-guest-access-in-groups#manage-groups-guest-access)」を参照してください。
- Microsoft 365 グループに関連付けられている SharePoint Online サイトとリソースに外部ユーザーがアクセスできるようにするには、SharePoint Online の外部共有がオンになっていることを確認してください。 詳細については、「[外部共有を有効または無効にする](https://learn.microsoft.com/ja-jp/sharepoint/turn-external-sharing-on-or-off#change-the-organization-level-external-sharing-setting)」を参照してください。
- PowerShell のディレクトリ レベルで Microsoft 365 グループのゲスト ポリシーを設定する方法については、「[例:ディレクトリ レベルでグループのゲスト ポリシーを構成する](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-settings-cmdlets#example-configure-guest-policy-for-groups-at-the-directory-level)」を参照してください。

#### Teams の共有設定を確認する

- 外部ユーザーのアクセス パッケージに Teams を含めるには、 **[Microsoft Teams へのゲスト アクセスを許可する]** が **[オン]** に設定されていることを確認してください。 詳細については、[Microsoft Teams 管理センターでのゲスト アクセスの構成](https://learn.microsoft.com/ja-jp/microsoftteams/set-up-guests#configure-guest-access-in-the-teams-admin-center)に関する記事を参照してください。

### 外部ユーザーのライフサイクルを管理する

アクセス パッケージ要求が作成されることでディレクトリに招待された外部ユーザーが、アクセス パッケージの割り当てを失ったときに行われる処理を選択できます。 これは、ユーザーがアクセス パッケージの割り当てをすべて放棄した場合、または最後のアクセス パッケージの割り当てが期限切れになった場合に、行われる可能性があります。 既定では、アクセス パッケージの割り当てをすべて失った外部ユーザーは、ディレクトリへのサインインをブロックされます。 30 日後に、ゲスト ユーザー アカウントがディレクトリから削除されます。 外部ユーザーのサインインがブロックまたは削除されたりしないように構成したり、外部ユーザーのサインインがブロックされずに削除されるように構成したりすることもできます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に [Identity Governance 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)以上としてサインインします。
2. **ID ガバナンス**&gt;**詳細管理**&gt;**Control 構成**を参照します。
3. [外部ユーザーのライフサイクル **] 設定** カードで [設定の表示] を選択します。 [Image: ID ガバナンスの外部設定のスクリーンショット。]
4. [設定] ページには、最後のアクセス パッケージの割り当てが期限切れになると、アクセス パッケージを介してオンボードされた外部ユーザーに対して実行するアクションの一覧が表示されます。 これには、 **外部ユーザーの削除**、 **外部ユーザーのディレクトリへのサインインをブロックする**オプション、ディレクトリ **から外部ユーザーを削除するまでの日数**などのオプションが含まれます。 [Image: 外部設定オプションのスクリーンショット。]
5. 外部ユーザーがアクセス パッケージへの最後の割り当てを失ったら、このディレクトリ内のゲスト ユーザー アカウントを削除する場合は、[ **外部ユーザーの削除** ] ボックスをオンにします。

    注

    エンタイトルメント管理では、エンタイトルメント管理を通じて招待された、またはライフサイクル管理のためにエンタイトルメント管理に追加された外部ゲスト ユーザー アカウントのみが、そのゲスト ユーザー アカウントを[管理対象に変換する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-manage-lifecycle)ことで削除されます。 パッケージの割り当てにアクセスできなかったこのディレクトリ内のリソースにユーザーが追加された場合でも、ユーザーはこのディレクトリから削除されます。 アクセス パッケージの割り当てを受け取る前にゲストがこのディレクトリに存在していた場合、ゲストは残ります。 ただし、ゲストがアクセス パッケージの割り当てを通じて招待され、招待された後も OneDrive または SharePoint Online サイトに割り当てられた場合、それらは引き続き削除されます。 [ **外部ユーザーの削除** ] 設定を **[いいえ** ] に変更すると、後で最後のアクセス パッケージの割り当てが失われたユーザーにのみ影響します。削除がスケジュールされ、サインインがブロックされているユーザーは、元のスケジュールに従って削除されます。
6. 外部ユーザーがアクセス パッケージへの最後の割り当てを失ったら、外部ユーザーがこのディレクトリへのサインインをブロックする場合は、[ **外部ユーザーによるこのディレクトリへのサインインをブロックする** ] チェック ボックスをオンにします。

    注

    エンタイトルメント管理では、エンタイトルメント管理を通じて招待された、またはライフサイクル管理のためにエンタイトルメント管理に追加された外部ゲスト ユーザー アカウントのサインインのみが、そのゲスト ユーザー アカウントを[管理対象に変換する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-manage-lifecycle)ことでブロックされます。 パッケージの割り当てにアクセスできなかったこのディレクトリ内のリソースにユーザーが追加された場合でも、ユーザーのサインインはブロックされます。 ユーザーがこのディレクトリへのサインインをブロックされている場合、ユーザーはアクセス パッケージを再要求したり、このディレクトリに追加のアクセスを要求したりすることはできません。 後でこのアクセス パッケージまたはその他のアクセス パッケージへのアクセスを要求する必要がある場合は、サインインをブロックするように構成しないでください。
7. このディレクトリ内のゲスト ユーザー アカウントを削除する場合は、削除するまでの日数を設定できます。 アクセス パッケージの有効期限が切れると、外部ユーザーに通知されますが、アカウントが削除されても通知されません。 アクセス パッケージへの最後の割り当てが失われた直後にゲスト ユーザー アカウントを削除する場合は、 **[Number of days before removing external user from this directory](https://learn.microsoft.com/ja-jp/entra/id-governance/このディレクトリから外部ユーザーを削除するまでの日数)** を **0** に設定します。 この値を変更したときに影響を受けるのは、その後に最後のアクセス パッケージの割り当てを使用するユーザーのみです。削除がスケジュールされているユーザーは、引き続き元のスケジュールどおりに削除されます。
8. **[保存]** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-global-secure-access-restrict-employee-access"} -->
## エンタイトルメント管理とグローバル セキュア アクセスを使用して、従業員のクラウド アプリへのアクセスを制限する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-global-secure-access-restrict-employee-access
- Service: entra-id-governance / entitlement-management
- Article date: 2025-05-13
- Summary: エンタイトルメント管理とグローバル セキュア アクセスを使用して、従業員のクラウド アプリへのアクセスを制限する方法について説明します。

Microsoft Entra Suite には、制限付き Web サイトにアクセスできるユーザーを管理する機能が用意されています。 Microsoft Entra Internet Access は SaaS アプリへのアクセスを保護し、エンタイトルメント管理により、組織はアクセス要求ワークフロー、アクセス割り当て、レビュー、有効期限を自動化することで、ID とアクセスライフサイクルを大規模に管理できます。

このシナリオでは、承認されていない AI アプリなどの特定の承認されていない Web サイトへのアクセスをブロックするようにグローバル セキュリティで保護されたアクセスと条件付きアクセスを設定します。一方、エンタイトルメント管理を使用して、ポリシーから除外する必要があるユーザーに管理アクセスを提供します。 このシナリオは、生成型 AI アプリケーションや、Microsoft Entra とのプロビジョニングやフェデレーションをサポートしていない他の Web アプリケーションに役立ちます。

[Image: 条件付きアクセスとグローバル セキュリティで保護されたアクセスを使用したサービス としてのソフトウェア アプリの制限を示す図のスクリーンショット。]

[Image: 条件付きアクセスとグローバルセキュリティで保護されたアクセスを使用して、管理されたソフトウェアをサービス アプリとして付与することを示す図のスクリーンショット。]

### [前提条件]

このシナリオを完了するには、Microsoft Entra テナントに次の前提条件が必要です。

- Microsoft Entra Suite、または Microsoft Entra ID ガバナンスと Microsoft Global Secure Access
- 必要なロール: 条件付きアクセス管理者、ID ガバナンス管理者、グローバル セキュリティで保護されたアクセス管理者
- グローバル セキュア アクセス クライアントをインストールできる Microsoft Entra ID 参加済みデバイス。

### 手順 1: グローバル セキュリティで保護されたアクセスを設定する

グローバル セキュリティで保護されたアクセスがまだ構成されていない場合は、まずこれを設定する必要があります。 手順通りのガイドについては、「[グローバル セキュア アクセスのスタート ガイド](https://learn.microsoft.com/ja-jp/entra/global-secure-access/quickstart-access-admin-center)」を参照してください。 次の 4 つの手順を実行します。

1. [インターネット アクセス プロファイル](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-internet-access-profile)と [Microsoft トラフィック転送プロファイルを](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-manage-microsoft-profile)有効にします。
2. エンドユーザー デバイスに Global Secure Access クライアントをインストールして構成します。

### 手順 2: グローバルなセキュリティで保護されたアクセス Web コンテンツ フィルター ポリシーを作成する

この手順では、特定の Web サイトへのアクセスをブロックするグローバル セキュリティで保護されたアクセス Web コンテンツ フィルタリング ポリシーを作成します。 [グローバル セキュリティで保護されたアクセス Web コンテンツのフィルター処理を構成する方法](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-web-content-filtering)を参照してください。

1. アクセスを制限するインターネット ドメインを特定し、ブロックされているユーザーがアクセスを取得する方法のプロセスを定義します。 次のガイドでは、セキュリティ グループを使用してユーザーにアクセスを提供します。
2. グローバルセキュリティで保護されたアクセス &gt; セキュリティで保護された &gt; Web コンテンツ フィルタリング ポリシーに移動し、[ポリシーの **作成**] を選択します。
3. ポリシーの名前を選択し、アクションとして **[ブロック** ] を選択します。
4. [ポリシー ルール] タブで、[ルールの追加] を選択します。 名前を選択し、[宛先の種類] に [fqdn] を選択し、ブロックする宛先を入力します。 [追加] を選択します。
5. ポリシーを確認して作成します。

### 手順 3: グローバルセキュリティアクセスセキュリティプロファイルを作成し、フィルタリングポリシーをリンクする

1. セキュリティで保護された&gt;セキュリティ プロファイル&gt;グローバル セキュリティ アクセスに移動し、[プロファイルの**作成**] を選択します。
2. プロファイル名を選択し、[状態] を [*有効]* のままにして、[優先度] を選択します。
3. [ポリシーのリンク] タブで、[*ポリシーのリンク*] を選択し、Web コンテンツ フィルター ポリシーを選択します。

### 手順 4: 除外されたユーザーのセキュリティ グループを作成する

1. [グループ] に移動し、[ **新しいグループ**] を選択します。
2. [グループの種類 **セキュリティ**] を選択し、グループ名を入力して、メンバーシップを **割り当て済み** のままにします。
3. グループを作成します。

### 手順 5: 条件付きアクセス ポリシーを構成する

グローバル セキュリティで保護されたアクセスを設定したら、 [条件付きアクセス ポリシーを作成](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policies) して、特定の Web サイトへのアクセスを制限する必要があります。

1. [保護] &gt; [条件付きアクセス &gt; ポリシー] を参照し、[ **新しいポリシー**] を選択します。
2. ポリシーの名前を選択します。
3. [ユーザー] で [含める] タブを選択し、[ **すべてのユーザー**] を選択します。 [除外] タブを選択し、[ **ユーザーとグループ**] を選択し、ポリシーの例外グループとして使用する手順 4 で作成したグループを選択します。
4. [ターゲット リソース] で、[ **グローバル セキュリティで保護されたアクセスを使用するすべてのインターネット リソース**] を選択します。
5. [セッション] で [ **グローバル なセキュリティで保護されたアクセスのセキュリティ プロファイルを使用**する] を選択し、手順 3 で作成したセキュリティ プロファイルの名前を選択します。
6. [ポリシーの有効化] **で [オン]** を選択するか、テスト用にレポート専用モードのままにします。
7. ポリシーを保存します。

### 手順 6: エンタイトルメント管理アクセス パッケージを作成して、制限されたリソースへの管理アクセスを提供する

シナリオの最後の手順は、手順 4 で指定したセキュリティ グループを含むアクセス パッケージを作成することです。 このアクセス パッケージに割り当てられたユーザーはこのグループに割り当てられ、確立した Web コンテンツ フィルタリング ポリシーから除外されます。

他のアクセス パッケージと同様に、パッケージを要求できるユーザー、承認する必要があるユーザー、およびそのライフサイクルを指定するルールを使用してポリシーを作成します。 詳細については、「 [エンタイトルメント管理でのアクセス パッケージの作成」を参照してください](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)。

### 手順 7: シナリオをテストする

前の手順を完了すると、シナリオをテストする準備が整います。

1. Microsoft Entra ID 参加済みデバイスで、手順 2 で制限したサイトへのアクセスを試みます。 HTTP トラフィックに対するプレーンテキスト ブラウザー エラーと HTTPS トラフィックに対する "接続リセット" ブラウザー エラーを含むすべてのブラウザーに対してブロック エクスペリエンスを受け取る必要があります。
2. エンタイトルメント管理で、手順 5 で作成したアクセス パッケージを、Microsoft Entra ID 参加済みデバイスにサインインしているユーザーに割り当てます。 これにより、手順 4 で作成したセキュリティ グループへのアクセスを提供するアクセス パッケージにユーザーが割り当てられます。 必要な承認は、割り当てが完了する前に完了する必要があります。
3. Microsoft Entra ID 参加済みデバイスで、手順 2 で制限したサイトへのアクセスを試みます。 これで、サイトにアクセスできるようになります。

注

グローバル セキュリティで保護されたアクセス ポリシーが有効になるまでに最大 60 分かかる場合があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-group-licenses"} -->
## Microsoft Entra ID でグループベースのライセンスのライフサイクルを管理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-group-licenses
- Service: entra-id-governance / entitlement-management
- Article date: 2024-07-15
- Summary: このステップバイステップのチュートリアルでは、エンタイトルメント管理でグループベースのライセンスを管理するためのアクセス パッケージを作成する方法を説明します。

Microsoft Entra ID では、グループを使用して[アプリケーションのライセンス](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true)を管理できます。 エンタイトルメント管理を使用すると、これらのグループの管理をさらに簡単に行うことができます。

- ライセンスを必要とする許可されているユーザーのみがグループに含まれていることを確認するために、定期的なアクセス レビューを構成する。
- 他のユーザーがグループに対してメンバーシップを要求できるようにする。

このチュートリアルでは、あなたは Woodgrove Bank の IT 管理者の役割を果たします。 あなたは、組織の従業員が Office ライセンスに簡単にアクセスできるように、アクセス パッケージを作成するよう求められています。 ([Office ライセンス](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true)を管理するグループが既に存在しているはずです。) これらのグループ メンバーを毎年確認できるようにする必要があるとします。 また、新しい従業員が Office ライセンスを要求して、マネージャーの承認待ちをできるようにします。

エンタイトルメント管理を使用するには、次のいずれかのライセンスが必要です。

- Microsoft Entra ID P2 または Microsoft Entra ID ガバナンス
- Enterprise Mobility + Security(EMS)E5

詳細については、「[License requirements ライセンスの要件](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview#license-requirements)」を参照してください。

### 手順 1: アクセス パッケージの基本を構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に [Identity Governance 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)以上としてサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者、ユーザー管理者、アクセス パッケージ マネージャーがあります。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**アクセスパッケージ**に移動します。
3. **[アクセス パッケージ]** ページで、**[新しいアクセス パッケージ]** を選択します。
4. **[基本]** タブの **[名前]** ボックスに、「**Office ライセンス**」と入力します。 **[説明]** ボックスに、「**Office アプリケーションのライセンスへのアクセス**」と入力します。
5. **[カタログ]** の一覧で、 **[全般]** をそのままにします。

### 手順 2: アクセス パッケージのリソースを構成する

1. **[次へ: リソース ロール]** を選択して **[リソースロール]** タブに進みます。
2. このタブでは、アクセス パッケージに含めるリソースとリソース ロールを選択します。 このシナリオでは、 **[グループとチーム]** を選択し、[Office ライセンス](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true)が割り当てられているグループを検索します。
3. **[ロール]** 一覧で **[メンバー]** を選択します。

### 手順 3: アクセス パッケージの要求を構成する

1. **次へ: 要求**を選択して **[要求]** タブに進みます。

    このタブでは、要求ポリシーを作成します。 *ポリシー*により、アクセス パッケージにアクセスするための規則を定義します。 リソース ディレクトリ内のゲスト以外のユーザーがアクセス パッケージを要求することを許可するポリシーを作成します。
2. **[アクセス権を要求できるユーザー]** セクションで、 **[ディレクトリ内のユーザーの場合]** を選択し、 **[すべてのメンバー (ゲストを除く)]** を選択します。 これらの設定によって、ディレクトリのメンバーだけが Office ライセンスを要求できるようになります。
3. **[承認を要求する]** が **[はい]** に設定されていることを確認します。
4. **[要求者の理由を要求する]** は **[はい]** のままにします。
5. **[ステージの数]** は **[1]** のままにします。
6. **[承認者]** で、 **[承認者としてのマネージャー]** を選択します。 このオプションを使用すると、要求元のマネージャーが要求を承認できます。 システムでマネージャーが見つからない場合のフォールバック承認者として、別のユーザーを選択できます。
7. **[決定は何日以内に行わなければなりませんか?]** は **[14]** のままにします。
8. **[承認者からの理由が必要]** は **[はい]** のままにします。
9. **[新しい要求と割り当ての有効化]** で **[はい]** を選択して、アクセス パッケージが作成されたらすぐに新規ユーザーが要求できるようにします。

### 手順 4: アクセス パッケージの要求元情報を構成する

1. **[次へ]** を選択して、 **[Requestor information](https://learn.microsoft.com/ja-jp/entra/id-governance/要求元情報)** タブを開きます。
2. このタブでは、要求元から詳細情報を収集するために質問をすることができます。 質問は要求フォームに表示され、必須または省略可能のいずれかにできます。 このシナリオでは、アクセス パッケージの要求元情報を含めることは要求されていないため、これらのボックスを空のままにすることができます。

### 手順 5: アクセス パッケージのライフサイクルを構成する

1. **[次へ: ライフサイクル]** を選択して、 **[ライフサイクル]** タブに進みます。
2. **[有効期限]** セクションの **[アクセス パッケージ割り当ての有効期限が切れる]** で **[日数]** を選択します。
3. **[割り当てが期限切れになるまでの日数]** に「**365**」と入力します。 このボックスで、アクセス パッケージへのアクセス権を持つメンバーがいつアクセスを更新する必要があるかを指定します。
4. アクセス レビューを構成することもできます。これにより、ユーザーに引き続きアクセス パッケージへのアクセス権が必要かどうかを確認できます。 レビューは、ユーザー自身によって実行される自己レビューにすることができます。 あるいは、ユーザーのマネージャーまたは他のユーザーをレビュー担当者として設定することもできます。 詳細については、[アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-reviews-create)に関するページを参照してください。

    このシナリオでは、すべての従業員に Office のライセンスがまだ必要かどうかを毎年確認してもらう必要があります。

    1. **[アクセス レビューが必要]** で **[はい]** を選択します。
    2. **[開始日]** は、現在の日付のままにできます。 この日付は、アクセス レビューが開始される日付です。 アクセス レビューを作成した後で、その開始日を更新することはできません。
    3. レビューは 1 年に 1 回実行されるため、**[レビュー頻度]** には **[毎年]** を選択します。 **[レビュー頻度]** ボックスでは、アクセス レビューを実行する頻度を決定します。
    4. **[期間 (日数)]** を指定します。 期間のボックスには、各アクセス レビュー系列が実行される日数を指定します。
    5. **[レビュー担当者]** で **[マネージャー]** を選択します。

### 手順 6: アクセス パッケージを確認して作成する

1. **[次へ: 確認と作成]** を選択して、 **[確認と作成]** タブに進みます。

    このタブでは、アクセス パッケージを作成する前にその構成を確認できます。 問題が発生した場合は、タブを使用してプロセス内の特定のポイントに移動し、編集することができます。
2. 構成に問題がなければ、 **[作成]** を選択します。 しばらくすると、アクセス パッケージが作成されたことを示す通知が表示されます。
3. アクセス パッケージが作成されると、パッケージの **[概要]** ページが表示されます。 ここで**マイ アクセス ポータルのリンク**を確認できます。 リンクをコピーしてチームと共有すると、チーム メンバーはアクセス パッケージに Office のライセンスが割り当てられるように要求できます。

### 手順 7: リソースをクリーンアップする

この手順では、Office ライセンス アクセス パッケージを削除します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に [Identity Governance 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)以上としてサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、アクセス パッケージ マネージャーがあります。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**アクセスパッケージ**に移動します。
3. **Office ライセンス** アクセス パッケージを開きます。
4. **[リソース ロール]** を選択します。
5. アクセス パッケージに追加したグループを選択します。 詳細ウィンドウで、 **[Remove resource role](リソース ロールの削除)** を選択します。 表示されるメッセージ ボックスで、 **[はい]** を選択します。
6. アクセス パッケージの一覧を開きます。
7. **Office ライセンス**について、省略記号ボタン (...) を選択して、 **[削除]** を選択します。 表示されるメッセージ ボックスで、 **[はい]** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-group-writeback"} -->
## エンタイトルメント管理内でのグループ書き戻しを設定する - Microsoft Entra ID - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-group-writeback
- Service: entra-id-governance / entitlement-management
- Article date: 2024-07-15
- Summary: エンタイトルメント管理でグループ書き戻しを設定する方法について説明します。

この記事では、エンタイトルメント管理でのグループ書き戻しを設定する方法について説明します。 グループ ライトバックは、Microsoft Entra Cloud Sync を使用して、クラウド グループをオンプレミスの Active Directory インスタンスに書き戻す機能です。

### エンタイトルメント管理でグループの書き戻しを設定する

アクセス パッケージで Microsoft 365 グループのグループの書き戻しを設定するには、次の前提条件を完了する必要があります。

- Microsoft Entra グループの書き戻しを設定します。
- Microsoft Entra Cloud Sync 構成でグループ ライトバックをセットアップするために使用される組織単位 (OU)。
- Microsoft Entra Cloud Sync の [グループ ライトバック有効化手順](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory) を完了します。

グループ ライトバックを使用して、アクセス パッケージの一部であるセキュリティ グループをオンプレミスの Active Directory に同期できるようになりました。 グループを同期するには、次の手順に従います。

1. Microsoft Entra セキュリティ グループを作成します。
2. そのグループをオンプレミスの Active Directory に書き戻されるように設定します。 手順については、[Microsoft Entra 管理センターのグループの書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)を参照してください。
3. そのグループをリソース ロールとしてアクセス パッケージに追加します。 ガイダンスについては、[新しいアクセス パッケージの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#select-resource-roles)に関するページを参照してください。
4. Active Directory ユーザーとコンピューターを起動し、作成された新しい AD グループが AD ドメインに作成されるまで待ちます。 存在する場合は、新しい AD グループの識別名、ドメイン、アカウント名、SID を記録します。
5. [「Microsoft Entra ID ガバナンスを使用したオンプレミス Active Directory ベースのアプリ (Kerberos) の管理](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/govern-on-premises-groups)」の説明に従って、アプリケーションを更新するか、既存のグループのメンバーとしてグループを追加して、新しいグループを使用するようにアプリケーションを構成します。
6. アクセス パッケージに ID を割り当てます。 ユーザーを直接割り当てる手順については、[アクセス パッケージの割り当ての表示、追加、削除](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-an-identity)に関するページを参照してください。
7. アクセス パッケージに ID を割り当てた後、Microsoft Entra Cloud Sync サイクルが完了したら、ユーザーがオンプレミス グループのメンバーになっていることを確認します。

    1. オンプレミス OU 内のグループのメンバー プロパティを表示するか、または
    2. ユーザー オブジェクトの [メンバーの所属先] を確認します。

    注

    Microsoft Entra Cloud Sync の既定の同期サイクル スケジュールは 30 分ごとです。 次のサイクルが実行されるまで待って結果をオンプレミスで確認するか、または結果をより早く確認するために同期サイクルの手動での実行を選択することが必要になる場合があります。
8. AD ドメインの監視では、プロビジョニング エージェントを実行する gMSA アカウントのみに、新しい AD グループのメンバーシップを変更する承認を付与します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-logic-apps-integration"} -->
## エンタイトルメント管理でカスタム拡張機能を使用して Logic Apps をトリガーする - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logic-apps-integration
- Service: entra-id-governance / entitlement-management
- Article date: 2024-07-15
- Summary: エンタイトルメント管理でカスタム ロジック アプリ ワークフローを構成して使用する方法について説明します。

[Azure Logic Apps](https://learn.microsoft.com/ja-jp/azure/logic-apps/logic-apps-overview)を使用して、カスタム ワークフローを自動化し、アプリとサービスを 1 か所で接続できます。 ユーザーは、Logic Apps をエンタイトルメント管理と統合し、コアのエンタイトルメント管理のユース ケースを超えてガバナンス ワークフローを広げることができます。

これらの Logic Apps は、エンタイトルメント管理のユース ケース (アクセス パッケージが許可または要求されたときなど) に従って実行されるようにトリガーできます。 たとえば、管理者は、ユーザーがアクセス パッケージを要求したときに、サード パーティの SAAS アプリ (Salesforce など) の特定の特性を割り当てるか、カスタム電子メールを送信するロジック アプリがトリガーされるように、カスタム ロジック アプリを作成してエンタイトルメント管理にリンクすることができます。

Logic Apps と統合できるエンタイトルメント管理のユース ケースには次のステージがあります。 これらのステージは、カスタム拡張機能ロジック アプリを起動できるアクセス パッケージに関連付けられているトリガーです。

- アクセス パッケージの要求が作成されたとき
- アクセス パッケージの要求が承認されたとき
- アクセス パッケージの割り当てが許可されたとき
- アクセス パッケージの割り当てが削除されたとき
- アクセス パッケージの割り当ての有効期限が自動的に切れる 14 日前
- アクセス パッケージの割り当ての有効期限が自動的に切れる 1 日前

Logic Apps へのこれらのトリガーは、**[ルール]** というアクセス パッケージ ポリシー内のタブで制御します。 また、[カタログ] ページの **[カスタム拡張機能]** タブには、特定のカタログに対して追加されたすべての Logic Apps の拡張機能が表示されます。 この記事では、エンタイトルメント管理でロジック アプリを作成してカタログに追加し、パッケージにアクセスする方法について説明します。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID ガバナンスまたはMicrosoft Entra スイートライセンスが必要です。 要件に適したライセンスを見つけるには、[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を参照してください。

### エンタイトルメント管理で使用するロジック アプリ ワークフローを作成して、カタログに追加する

1. 少なくとも [Identity Governance Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) として [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者とリソース グループ所有者があります。
2. **ID ガバナンス**&gt;**Catalogs** に移動します。
3. カスタム拡張機能を追加するカタログを選択し、左側のメニューで **[Custom Extensions] (カスタム拡張機能)** を選択します。
4. ヘッダーのナビゲーション バーで、 **[カスタム拡張機能の追加]** を選択します。
5. **[基本]** タブで、カスタム拡張機能の名前 (リンクするロジック アプリの名前) とワークフローの説明を入力します。 これらのフィールドは、カタログの **[カスタム拡張機能]** タブに表示されます。

    [Image: カスタム拡張機能を作成するためのペイン]
6. **[拡張機能の種類]** タブでは、カスタム拡張機能を使用できるアクセス パッケージ ポリシーの種類を定義します。 "**要求ワークフロー**" の種類では、要求されたアクセス パッケージが作成されたとき、要求が承認されたとき、割り当てが許可されたとき、割り当てが削除されたときというポリシー ステージがサポートされます。 この種類では、[\[起動して待機\]](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logic-apps-integration#configuring-custom-extensions-that-pause-entitlement-management-processes) 機能もサポートされます。
7. 有効期限前のワークフローでは、アクセス パッケージの割り当ての有効期限が切れるまで 14 日、アクセス パッケージの割り当ての有効期限が切れまで 1 日というポリシー ステージがサポートされます。 この拡張機能の種類では、[起動して待機] はサポートされていません。

    [Image: [起動して待機] の構成オプションのスクリーンショット。]
8. **[拡張機能の構成]** タブでは、拡張機能に [起動して続行] または [起動して待機] の動作があるかどうかを判断できます。 [起動して続行] では、アクセス パッケージのリンクされたポリシー アクション (要求など) によって、カスタム拡張機能にアタッチされているロジック アプリがトリガーされます。 ロジック アプリがトリガーされると、アクセス パッケージに関連付けられているエンタイトルメント管理プロセスが続行されます。 "起動して待機" の場合は、拡張機能にリンクされたロジック アプリがタスクを完了し、管理者が再開アクションを送信してプロセスを続行するまで、関連付けられているアクセス パッケージアクションを一時停止します。 定義された待機期間内に応答が返されない場合、このプロセスは失敗と見なされます。 このプロセスについては、「[エンタイトルメント管理プロセスを一時停止するカスタム拡張機能の構成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logic-apps-integration#configuring-custom-extensions-that-pause-entitlement-management-processes)」の個別のセクションで詳しく説明します。
9. **[詳細]** タブで、既存の従量課金プランのロジック アプリを使用するかどうかを選択します。 [新しいロジック アプリの作成] フィールドで [はい] を選択すると (既定値)、このカスタム拡張機能に既にリンクされている新しい空の従量課金プランのロジック アプリが作成されます。 いずれの場合も、次の情報を指定する必要があります。

    1. Azure サブスクリプション。
    2. 新しいロジック アプリを作成する場合は、ロジック アプリのリソース作成アクセス許可を持つリソース グループ。
    3. その設定を使用している場合は、[ロジック アプリの作成] を選択します。

        [Image: ロジック アプリ詳細選択の作成のスクリーンショット。]

    注

    このモーダルウィンドウで新しいロジック アプリを作成すると、"/subscriptions/{SubscriptionId}/resourceGroups/{RG Name}/providers/Microsoft.Logic/workflows/{Logicapp Name}" の文字数は150文字を超えてはなりません。
10. **[確認と作成]** では、カスタム拡張機能の概要をレビューし、ロジック アプリのコールアウトの詳細が正しいことを確認してください。 **[作成]** を選択します。
11. リンクされたロジック アプリに対するこのカスタム拡張機能が、[カタログ] の下の [カスタム拡張機能] タブに表示されるようになります。 アクセス パッケージ ポリシーでは、このカスタム拡張機能を呼び出すことが可能です。

### カタログの既存のカスタム拡張機能を表示および編集する

1. 少なくとも [ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)として、前述したように、カタログ内の [カスタム拡張機能] タブに移動します。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者があります。
2. ここでは、作成したすべてのカスタム拡張機能のほか、関連付けられているロジック アプリと、カスタム拡張機能の種類に関する情報を表示できます。 [Image: カスタム拡張機能の一覧のスクリーンショット。]
3. ロジック アプリ名と共に、[種類] 列には、カスタム拡張機能が新しい V2 認証モデル (2023 年 3 月 17 日以降) に作成されたか、元のモデルに作成されたかが指定されます。 新しいモデルでカスタム拡張機能が作成された場合、[種類] 列は、"*割り当て要求*" または "*有効期限前*" のいずれかの構成モーダルから選択した型と一致します。 以前のカスタム拡張機能の場合、種類は "*カスタム アクセス パッケージ*" と表示されます。
4. [トークン セキュリティ] 列には、カスタム拡張機能の作成時に使用された関連付けられた認証セキュリティ フレームワークが表示されます。 新しい V2 カスタム拡張機能では、トークン セキュリティの種類として "*所有証明*" (PoP) が表示されます。 以前のカスタム拡張機能では、"標準" と表示されます。
5. 古いスタイルのカスタム拡張機能は、UI から作成できなくなりました。 既存のものを UI から新しいスタイルのカスタム拡張機能に変換できます。 [Image: 古いセキュリティ トークンを新しいセキュリティ トークンに変換するスクリーンショット。]
6. 古いカスタム拡張機能の行の末尾にある 3 つのドットを選択すると、カスタム拡張機能を新しい種類にすばやく更新できます。

    注

    カスタム拡張機能は、使用されていない場合、または 1 つの特定の拡張機能の種類 (割り当て要求ステージまたは有効期限前ステージ) のポリシー ステージ専用に使用されている場合にのみ、新しい型に変換できます。
7. カスタム拡張機能を編集することもできます。 編集すると、名前、説明、およびその他のフィールド値を更新できます。 これを実現するには、任意のカスタム拡張機能の 3 ドット ウィンドウ内で **[編集]** を選択します。
8. 古いスタイルのカスタム拡張機能は、変換されていなくても、もはや作成できなくなっている場合でも引き続き使用および編集できます。
9. 割り当て要求と有効期限前の**両方**の種類のポリシー ステージで使われているために、古いスタイルのカスタム拡張機能を新しい種類に更新できない場合、更新するには、リンクされているすべてのポリシーから削除するか、**1 つ**の種類 (割り当て要求、または有効期限前) に関連付けられたポリシー ステージにのみ使うようにする必要があります。

### アクセス パッケージのポリシーにカスタム拡張機能を追加する

1. 少なくとも [Identity Governance Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) として [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。

    ヒント

    このタスクを完了できる他の最小限の特権ロールには、カタログ所有者とアクセス パッケージ マネージャーがあります。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**アクセスパッケージ**に移動します。
3. 既に作成されているアクセス パッケージのリストから、カスタム拡張機能 (ロジック アプリ) を追加するアクセス パッケージを選択します。

    注

    新しいアクセス パッケージを作成する場合は、**[新しいアクセス パッケージ]** を選択します。 アクセス パッケージを作成する方法の詳細については、「[エンタイトルメント管理で新しいアクセス パッケージを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)」をご覧ください。 既存のアクセス パッケージを編集する方法の詳細については、Microsoft Entra のエンタイトルメント管理でのアクセス パッケージの要求設定の変更を参照してください。
4. [ポリシー] タブに移動し、ポリシーを選択して **[編集]** を選択します。
5. ポリシー設定で、**[Custom Extensions] (カスタム拡張機能)** タブに移動します。
6. **[ステージ]** の下のメニューで、このカスタム拡張機能 (ロジック アプリ) のトリガーとして使用するアクセス パッケージ イベントを選択します。 たとえば、ユーザーがアクセス パッケージを要求したときにカスタム拡張機能ロジック アプリのワークフローをトリガーするだけの場合は、**[要求が作成されました]** を選択します。
7. **[カスタム拡張機能]** の下のメニューで、アクセス パッケージに追加するカスタム拡張機能 (ロジック アプリ) を選択します。 選択したアクションは、*[タイミング]* フィールドで選択したイベントが発生したときに実行されます。
8. 既存のアクセス パッケージのポリシーに追加するには、**[更新]** を選択します。

    [Image: アクセス パッケージにロジック アプリを追加する]

### リンクされたロジック アプリのワークフロー定義を編集する

カスタム拡張機能にリンクされた新しく作成された Logic Apps の場合、これらの Logic Apps は空白で始まります。 リンクされたアクセス パッケージ ポリシー条件がトリガーされたときに拡張機能によってトリガーされるロジック アプリにワークフローを作成するには、ロジック アプリ デザイナーでロジック アプリ ワークフローの定義を編集する必要があります。 これを実現するには、以下の手順に従います。

1. 少なくとも [ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)として、前述したように、カタログ内の [カスタム拡張機能] タブに移動します。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者があります。
2. 編集したいロジック アプリのカスタム拡張機能を選択します。
3. 関連付けられているカスタム拡張機能行の [ロジック アプリ] 列の下にある [ロジック アプリ] を選択します。 これにより、ロジック アプリ デザイナーでワークフローを編集または作成できます。

ロジック アプリ ワークフローの作成の詳細については、「[クイックスタート: マルチテナント Azure Logic Apps で消費型ワークフローの例を作成する](https://learn.microsoft.com/ja-jp/azure/logic-apps/quickstart-create-example-consumption-workflow)」を参照してください。

### エンタイトルメント管理プロセスを一時停止するカスタム拡張機能の構成

カスタム拡張機能の新しい更新は、ロジック アプリが完了し、再開要求ペイロードがエンタイトルメント管理に返送されるまで、カスタム拡張機能に関連付けられているアクセス パッケージ ポリシー プロセスを一時停止する機能です。 たとえば、ロジック アプリのカスタム拡張機能がアクセス パッケージ付与ポリシーからトリガーされ、"起動と待機" が有効になっている場合、ロジック アプリがトリガーされると、ロジック アプリが完了するまで許可プロセスは再開せず、再開要求はエンタイトルメント管理に送り返されます。

この一時停止プロセスにより、管理者はエンタイトルメント管理でアクセス ライフサイクル タスクを続行する前に、実行するワークフローを制御できます。 これに対する唯一の例外は、タイムアウトが発生した場合です。 [起動して待機] プロセスには、分数、時間数、または日数で示された最大 14 日間のタイムアウトが必要です。 "タイムアウト" 期間が経過するまでにレジュメ応答がエンタイトルメント管理に返送されない場合、エンタイトルメント管理要求のワークフロー プロセスは一時停止します。

管理者は、ロジック アプリ ワークフローが完了したら、API **再開要求** ペイロードをエンタイトルメント管理に送り返すことができる自動化されたプロセスを構成する役割を担います。 再開要求ペイロードを返送するには、グラフ API ドキュメントの指示に従います。 こちらで[再開要求](https://learn.microsoft.com/ja-jp/graph/api/accesspackageassignmentrequest-resume)に関する情報をご覧ください。

具体的には、カスタム拡張機能を呼び出すためにアクセス パッケージ ポリシーが有効になっており、要求処理で顧客からのコールバックが待機されている場合、顧客は再開アクションを開始できます。 これは、[requestStatus](https://learn.microsoft.com/ja-jp/graph/api/resources/accesspackageassignmentrequest) が **WaitingForCallback** 状態の **accessPackageAssignmentRequest** オブジェクトに対して実行されます。

再開要求は、次のステージに対して返送できます。

```
microsoft.graph.accessPackageCustomExtensionStage.assignmentRequestCreated
microsoft.graph.accessPackageCustomExtensionStage.assignmentRequestApproved
microsoft.graph.accessPackageCustomExtensionStage.assignmentRequestGranted
microsoft.graph.accessPackageCustomExtensionStage.assignmentRequestRemoved
```

次のフロー図は、Logic Apps ワークフローへのエンタイトルメント管理呼び出しを示しています。[Image: ロジック アプリ ワークフローへのエンタイトルメント管理呼び出しを示す図。]

このフロー図は、次のことを示しています。

1. ユーザーは、ID サービスから呼び出しを受信できるカスタム エンドポイントを作成します
2. ID サービスはテスト呼び出しを行って、エンドポイントがID サービスによって呼び出し可能であることを確認します
3. ユーザーはGraph APIを呼び出して、アクセス パッケージにユーザーを追加するように要求します
4. ID サービスがキューに追加され、バックエンド ワークフローがトリガーされます
5. エンタイトルメント管理サービス要求処理は、要求ペイロードを使用してロジック アプリを呼び出します
6. ワークフローでは、受け入れられたコードを想定しています
7. エンタイトルメント管理サービスは、ブロックしているカスタム アクションが再開されるまで待機します
8. 顧客システムは、ID サービスに対して要求再開 API を呼び出して、要求の処理を再開します
9. ID サービスは、バックエンド ワークフローを再開するエンタイトルメント管理サービス キューに再開要求メッセージを追加します
10. エンタイトルメント管理サービスがブロックされた状態から再開されます

履歴書要求ペイロードの例としては、次のようなものがあります。

```http
POST https://graph.microsoft.com/beta/identityGovernance/entitlementManagement/accessPackageAssignmentRequests/00aa00aa-bb11-cc22-dd33-44ee44ee44ee/resume
```

```http
Content-Type: application/json

{
  "source": "Contoso.SodCheckProcess",
  "type": "microsoft.graph.accessPackageCustomExtensionStage.assignmentRequestCreated",
  "data": {
    "@odata.type": "microsoft.graph.accessPackageAssignmentRequestCallbackData",
    "stage": "assignmentRequestCreated",
    "customExtensionStageInstanceId": "957d0c50-466b-4840-bb5b-c92cea7141ff",
    "customExtensionStageInstanceDetail": "This user is all verified"
  }
}
```

起動と待機を使用すると、拡張機能がアクセス パッケージのステージ "要求が*作成*されました" または "*要求が承認されました*" にリンクされている場合、管理者は要求を拒否することもできます。 このような場合、ロジック アプリはエンタイトルメント管理に *"拒否"* メッセージを送信して、エンド ユーザーがアクセス パッケージを受け取る前にプロセスを終了できます。

前述のように、4 つの関連付けられたポリシー ステージを含む要求ワークフローの種類で作成されたカスタム拡張機能は、必要に応じて *[起動して待機]* で有効にすることができます。

次の呼び出しは、コールバックを待機している要求を拒否して、アクセス パッケージの割り当て要求の処理を再開する例です。 要求は、コールアウトの**assignmentRequestCreated**ステージまたは**assignmentRequestApproved**ステージでのみ拒否できます。

ヒント

Azure Logic Apps経由でアクセス パッケージの割り当て要求を再開する場合は、[asynchronous パターン](https://learn.microsoft.com/ja-jp/azure/connectors/connectors-native-http?tabs=standard#asynchronous-request-response-behavior)を無効にします。

```http
POST https://graph.microsoft.com/beta/identityGovernance/entitlementManagement/accessPackageAssignmentRequests/9e60f18c-b2a0-4887-9da8-da2e30a39d99/resume
```

```http
Content-Type: application/json

{
  "source": "Contoso.SodCheckProcess",
  "type": "microsoft.graph.accessPackageCustomExtensionStage.assignmentRequestCreated",
  "data": {
    "@odata.type": "microsoft.graph.accessPackageAssignmentRequestCallbackData",
    "stage": "AssignmentRequestCreated",
    "customExtensionStageInstanceId": "857d0c50-466b-4840-bb5b-c92cea7141ff",
    "state": "denied",
    "customExtensionStageInstanceDetail": "Potential risk user based on the SOD check"
  }
}
```

### 拡張機能エンドユーザーのエクスペリエンス

#### 承認者のエクスペリエンス

承認者は、`customExtensionStageInstanceDetail` にあるペイロードに指定された文字列を確認します。このペイロードは、[資格管理プロセスを停止するカスタム拡張機能の構成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logic-apps-integration#configuring-custom-extensions-that-pause-entitlement-management-processes)に示されています。 [Image: 承認者画面のスクリーンショット。]

#### 要求元のエクスペリエンス

アクセス パッケージに起動と待機の機能を持つカスタム拡張機能があり、アクセス パッケージ要求が作成されたときにロジック アプリがトリガーされると、要求元は MyAccess の要求履歴内で要求の状態を確認できます。

カスタム拡張機能ステージに応じて、次の状態更新がユーザーに表示されます。

| カスタム拡張機能ステージ | MyAccess 要求履歴で要求元に表示されるメッセージ |
| --- | --- |
| 拡張機能がプロセス中の場合 | Waiting for information before proceeding (先に進む前に情報を待機しています) |
| 拡張機能が失敗した場合 | Process expired (プロセスの有効期限が切れています) |
| 拡張機能が再開された場合 | Process continues (プロセスは続行されます) |

拡張機能が再開した後の要求元からの MyAccess 要求履歴の例を次に示します。

[Image: 要求元画面のスクリーンショット。]

### トラブルシューティングと検証

要求に関連付けられているカスタム拡張機能の場合は、関連付けられたアクセス パッケージの要求の詳細ページ内の [要求履歴の詳細] リンクから、カスタム拡張機能 (有効になっている場合は [起動して待機]) プロセスに関する詳細を確認できます。

[Image: カスタム タスク拡張機能の要求履歴のスクリーンショット。][Image: カスタム タスク拡張機能の選択詳細のスクリーンショット。]

たとえば、ここでは、要求が送信された時刻と、[起動して待機] プロセス (コールバックの待機) が開始された時刻を確認できます。 要求が承認され、ロジック アプリが実行され、再開要求が午後 12 時 15 分に返されると、エンタイトルメント管理ステージが "再開" されました。

さらに、要求の詳細内の新しい **[カスタム拡張機能インスタンス]** リンクには、要求のアクセス パッケージに関連付けられているカスタム拡張機能に関する情報が表示されます。[Image: 選択詳細リスト アイテムのスクリーンショット。]

カスタム拡張機能 ID と状態が表示されます。 この情報は、[起動して待機] コールバックが関連付けられているかどうかに基づいて変更されます。

関連付けられているロジック アプリがカスタム拡張機能により正しくトリガーされたことを確認するには、ロジック アプリのログを表示して、ロジック アプリが最後に実行された日時のタイムスタンプを確認することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-logs-and-reporting"} -->
## Azure Monitor を使用したアーカイブとレポート - エンタイトルメント管理 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logs-and-reporting
- Service: entra-id-governance / entitlement-management
- Article date: 2024-07-15
- Summary: エンタイトルメント管理で、Azure Monitor を使用してログをアーカイブし、レポートを作成する方法について説明します。

Microsoft Entra ID は、エンタイトルメント管理やその他の Microsoft Entra ID ガバナンス機能の監査イベントを監査ログに 30 日間保存します。 ただし、「 [Microsoft Entra ID でレポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-reports-data-retention) データを格納する期間」で説明されている既定の保持期間よりも長い期間、監査データを Azure Storage アカウントにルーティングするか、Azure Monitor を使用して保持できます。 これで、このデータに対してブックとカスタム クエリ、レポートを使用できるようになります。

この記事では、監査ログの保持に Azure Monitor を使用する方法について説明します。 ユーザーやアプリケーション ロールの割り当てなど、Microsoft Entra オブジェクトを保持またはレポートするには、Microsoft Entra IDのデータを使用して、Azure Data Explorer (ADX) でカスタマイズされたレポートを を参照してください。

### Azure Monitor を使用するように Microsoft Entra ID を構成する

Azure Monitor ワークブックを使用する前に、Microsoft Entra ID を構成して、その監査ログのコピーを Azure Monitor に送信する必要があります。

Microsoft Entra ID 監査ログをアーカイブするには、Azure サブスクリプションに Azure Monitor が必要です。 Azure [Monitor の Microsoft Entra アクティビティ ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-log-monitoring-integration-options-considerations)で Azure Monitor を使用する場合の前提条件と推定コストの詳細を確認できます。

1. 少なくとも[セキュリティ管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)にサインインします。 Azure Monitor ワークスペースが含まれているリソース グループにアクセスできることを確認します。
2. **Entra ID**&gt;**モニタリングと健康**&gt;**Diagnostic 設定**を参照してください。
3. 監査ログをそのワークスペースに送信する設定が既にあるかどうかを確認します。
4. まだ設定がない場合は、[ **診断設定の追加]** を選択します。 [「Microsoft Entra ログを Azure Monitor ログと統合](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)する」の手順を使用して、Microsoft Entra 監査ログを Azure Monitor ワークスペースに送信します。

    [Image: [診断設定] ウィンドウ。]
5. ログが Azure Monitor に送信されたら、 **Log Analytics ワークスペース**を選択し、Microsoft Entra 監査ログを含むワークスペースを選択します。
6. [ **使用量と推定コスト]** を選択し、[ **データ保有期間**] を選択します。 監査要件に合わせて、データを保持する日数にスライダーを変更します。

    [Image: Log Analytics ワークスペース ウィンドウ。]
7. 後ほど、ワークスペースに保持されている日付の範囲を表示するには、*アーカイブされたログの日付範囲*ブックを使用できます。

    1. **Entra ID**&gt;**Monitoring & health**&gt;**Workbooks** に移動します。
    2. **[Microsoft Entra Troubleshooting**] セクションを展開し、[**アーカイブされたログの日付範囲**] を選択します。

### アクセス パッケージのイベントを表示する

アクセス パッケージのイベントを表示するには、基になる Azure Monitor ワークスペース (詳細については、 [Azure Monitor のログ データとワークスペースへのアクセスの管理](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/manage-access#azure-rbac) に関するページを参照) と、次のいずれかのロールにアクセスできる必要があります。

- グローバル管理者
- セキュリティ管理者
- セキュリティリーダー
- レポートリーダー
- アプリケーション管理者

以下の手順でイベントを表示します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。 Azure Monitor ワークスペースが含まれているリソース グループにアクセスできることを確認します。
2. **Entra ID**&gt;**Monitoring & health**&gt;**Workbooks** に移動します。
3. 複数のサブスクリプションがある場合は、ワークスペースが含まれているサブスクリプションを選択します。
4. サブスクリプションを選択した後、またはサブスクリプションが 1 つしかない場合は、 *Access Package Activity* という名前のブックを選択します。
5. そのブックで、時間範囲 (不明な場合 **は [すべて** ] に変更) を選択し、その期間中にアクティビティを持っていたすべてのアクセス パッケージのドロップダウン リストからアクセス パッケージ ID を選択します。 選択した時間範囲内で発生したアクセス パッケージに関連のあるイベントが表示されます。

    [Image: アクセス パッケージ イベントを表示します。]

    各行には、時刻、アクセス パッケージ ID、操作の名前、オブジェクト ID、UPN、操作を開始したユーザーの表示名が含まれます。 この他の詳細は JSON に含まれています。
6. グローバル管理者がアプリケーション ロールに直接ユーザーを割り当てた場合など、アクセス パッケージの割り当てによってなかったアプリケーションのアプリケーション ロールの割り当てに変更があったかどうかを確認する場合は、 *アプリケーション ロールの割り当てアクティビティ*という名前のブックを選択できます。

    [Image: アプリ ロールの割り当てを表示します。]

### Microsoft Entra 管理センターを使用してカスタム Azure Monitor クエリを作成する

エンタイトルメント管理イベントを含め、Microsoft Entra 監査イベントに対する独自のクエリを作成できます。

1. Microsoft Entra 管理センターの ID で、左側のナビゲーション メニューの [監視] セクションで **[ログ** ] を選択して、新しいクエリ ページを作成します。
2. ワークスペースは、クエリ ページの左上に表示されます。 複数の Azure Monitor ワークスペースがあり、Microsoft Entra 監査イベントの格納に使用しているワークスペースが表示されない場合は、[スコープの選択] を **選択**します。 次に、適切なサブスクリプションとワークスペースを選択します。
3. 次に、クエリ テキスト領域で、文字列 "search \*" を削除し、次のクエリに置き換えます。

    ```
    AuditLogs | where Category == "EntitlementManagement"
    ```
4. 次に、[実行] を選択 **します**。

    [Image: [実行] を選択してクエリを開始します。]

このテーブルには、既定で過去 1 時間のエンタイトルメント管理の監査ログ イベントが表示されます。 [時間の範囲] 設定を変更して、古いイベントを表示することができます。 ただし、この設定を変更すると、Azure Monitor にイベントを送信するように Microsoft Entra ID が構成された後に発生したイベントのみが表示されます。

Azure Monitor で保持されている最も古い監査イベントと最新の監査イベントを知りたい場合は、次のクエリを使用します。

```
AuditLogs | where TimeGenerated > ago(3653d) | summarize OldestAuditEvent=min(TimeGenerated), NewestAuditEvent=max(TimeGenerated) by Type
```

Azure Monitor の監査イベント用に格納される列の詳細については、「Azure Monitor での [Microsoft Entra 監査ログ スキーマの解釈](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health)」を参照してください。

### Azure PowerShell を使用してカスタム Azure Monitor クエリを作成する

ログを Azure Monitor に送信するように Microsoft Entra ID を構成すると、PowerShell を使用してログにアクセスできます。 次に、スクリプトまたは PowerShell コマンド ラインからクエリを送信します。テナントのグローバル管理者である必要はありません。

#### ユーザーまたはサービス プリンシパルに正しいロールが割り当てられていることを確認する

Microsoft Entra ID に対して認証するユーザーまたはサービス プリンシパルが、Log Analytics ワークスペースの適切な Azure ロールに属していることを確認します。 ロール オプションは、Log Analytics 閲覧者または Log Analytics 共同作成者のいずれかです。 これらのロールの 1 つを既に使用している場合は、「 1 つの Azure サブスクリプションで Log Analytics ID を取得する」に進みます。

ロールの割り当てを設定し、クエリを作成するには、次の手順を実行します。

1. Microsoft Entra 管理センターで、 [Log Analytics ワークスペース](https://entra.microsoft.com/#blade/HubsExtension/BrowseResourceBlade/resourceType/Microsoft.OperationalInsights%2Fworkspaces)を見つけます。
2. **[アクセス制御 (IAM)]**を選択します。
3. 次に、[ **追加]** を選択してロールの割り当てを追加します。

    [Image: ロールの割り当てを追加します。]

#### Azure PowerShell モジュールをインストールする

適切なロールの割り当てを完了したら、PowerShell を起動し、次のように入力して [Azure PowerShell モジュールをインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell) します (まだインストールしていない場合)。

```azurepowershell
install-module -Name az -allowClobber -Scope CurrentUser
```

これで、Microsoft Entra ID に対して認証し、クエリを実行している Log Analytics ワークスペースの ID を取得する準備ができました。

#### 1 つの Azure サブスクリプションで Log Analytics ID を取得する

1 つの Azure サブスクリプションと 1 つの Log Analytics ワークスペースしかない場合は、次のように入力して Microsoft Entra ID に対して認証し、そのサブスクリプションに接続して、そのワークスペースを取得します。

```azurepowershell
Connect-AzAccount
$wks = Get-AzOperationalInsightsWorkspace
```

#### 複数の Azure サブスクリプションで Log Analytics ID を取得する

[Get-AzOperationalInsightsWorkspace](https://learn.microsoft.com/ja-jp/powershell/module/Az.OperationalInsights/Get-AzOperationalInsightsWorkspace) は、一度に 1 つのサブスクリプションで動作します。 そのため、複数の Azure サブスクリプションがある場合は、Microsoft Entra ログを含む Log Analytics ワークスペースがあるものに接続する必要があります。

次のコマンドレットを実行すると、サブスクリプションの一覧が表示されます。Log Analytics ワークスペースがあるサブスクリプションの ID を探します。

```azurepowershell
Connect-AzAccount
$subs = Get-AzSubscription
$subs | ft
```

`Connect-AzAccount –Subscription $subs[0].id` などのコマンドを使用して、そのサブスクリプションに PowerShell セッションを再認証し、関連付けることができます。 非対話型を含む PowerShell から Azure に対して認証する方法の詳細については、「 [Azure PowerShell を使用したサインイン](https://learn.microsoft.com/ja-jp/powershell/azure/authenticate-azureps)」を参照してください。

そのサブスクリプションに複数の Log Analytics ワークスペースがある場合、コマンドレット [Get-AzOperationalInsightsWorkspace](https://learn.microsoft.com/ja-jp/powershell/module/az.operationalinsights/get-azoperationalinsightsworkspace) はワークスペースの一覧を返します。 これで、Microsoft Entra ログがあるものを見つけることができます。 このコマンドレットから返される `CustomerId` フィールドは、Microsoft Entra 管理センターの Log Analytics ワークスペースの概要に表示される "ワークスペース ID" の値と同じです。

```powershell
$wks = Get-AzOperationalInsightsWorkspace
$wks | ft CustomerId, Name
```

#### Log Analytics ワークスペースにクエリを送信する

最後に、ワークスペースを特定したら、 [Invoke-AzOperationalInsightsQuery](https://learn.microsoft.com/ja-jp/powershell/module/az.operationalinsights/invoke-azoperationalinsightsquery) を使用して Kusto クエリをそのワークスペースに送信できます。 これらのクエリは [、Kusto クエリ言語](https://learn.microsoft.com/ja-jp/azure/data-explorer/kusto/query/)で記述されます。

たとえば、次のようなクエリを送信する PowerShell コマンドレットを使用して、Log Analytics ワークスペースから監査イベント レコードの日付範囲を取得できます。

```powershell
$aQuery = "AuditLogs | where TimeGenerated > ago(3653d) | summarize OldestAuditEvent=min(TimeGenerated), NewestAuditEvent=max(TimeGenerated) by Type"
$aResponse = Invoke-AzOperationalInsightsQuery -WorkspaceId $wks[0].CustomerId -Query $aQuery
$aResponse.Results |ft
```

次のようなクエリを使用して、エンタイトルメント管理イベントを取得することもできます。

```azurepowershell
$bQuery = 'AuditLogs | where Category == "EntitlementManagement"'
$bResponse = Invoke-AzOperationalInsightsQuery -WorkspaceId $wks[0].CustomerId -Query $Query
$bResponse.Results |ft 
```

#### クエリ フィルターの使用

`TimeGenerated` フィールドを含めて、クエリのスコープを特定の時間範囲に設定できます。 たとえば、過去 90 日間に作成または更新されているエンタイトルメント管理アクセス パッケージ割り当てポリシーの監査ログ イベントを取得するには、このフィールドの他にカテゴリと操作の種類が含まれているクエリを指定できます。

```
AuditLogs | 
where TimeGenerated > ago(90d) and Category == "EntitlementManagement" and Result == "success" and (AADOperationType == "CreateEntitlementGrantPolicy" or AADOperationType == "UpdateEntitlementGrantPolicy") | 
project ActivityDateTime,OperationName, InitiatedBy, AdditionalDetails, TargetResources
```

エンタイトルメント管理などの一部のサービスの監査イベントの場合は、変更されているリソースの影響を受けるプロパティを展開し、フィルター処理することもできます。 たとえば、ユーザーに割り当てが追加されるときに承認を必要としない、作成または更新されているアクセス パッケージ割り当てポリシーの監査ログ レコードのみを表示できます。

```
AuditLogs | 
where TimeGenerated > ago(90d) and Category == "EntitlementManagement" and Result == "success" and (AADOperationType == "CreateEntitlementGrantPolicy" or AADOperationType == "UpdateEntitlementGrantPolicy") | 
mv-expand TargetResources | 
where TargetResources.type == "AccessPackageAssignmentPolicy" | 
project ActivityDateTime,OperationName,InitiatedBy,PolicyId=TargetResources.id,PolicyDisplayName=TargetResources.displayName,MP1=TargetResources.modifiedProperties | 
mv-expand MP1 | 
where (MP1.displayName == "IsApprovalRequiredForAdd" and MP1.newValue == "\"False\"") |
order by ActivityDateTime desc 
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-onboard"} -->
## エンタイトルメント管理を使用し、外部ユーザーをオンボードする - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-onboard
- Service: entra-id-governance / entitlement-management
- Article date: 2025-10-07
- Summary: 外部ユーザーを組織にオンボードするためのアプリケーションやリソースへのアクセス許可の承認を簡素化する方法について説明します。

外部ユーザーをオンボードするプロセスでは、多くの場合、ユーザーに関する情報を収集して、アクセス権を付与するかどうか、または、使用するアプリやリソースのアカウントに対して適切に設定できうる方法に関する決定をガイドすることが含まれます たとえば、外部ユーザーに特定のチームへのアクセス権を付与する前に、承認者がそのチームが自分に適しているかどうかを認識できるように、組織内でのロールを共有してもらうことができます。 従業員が特定のアプリケーションを使用しているため、従業員に対するものと同じように、外部ユーザーの場所属性を設定する必要が生じる場合があります。

以前は、企業では、外部ユーザーを設定してアクセスを付与する前に、この情報を収集するためのカスタム フォームが作成されていました。 多くの場合、これらのカスタム フォームの作成には費用がかかり、保守も困難でした。 エンタイトルメント管理機能により、承認者とアプリに必要な情報が自動入力されるようになりました。 この記事では、これらの機能を使用して、外部ユーザーを組織にオンボードするのに使用可能な方法を説明します。

### オンボーディング機能

次の機能を利用することで、エンタイトルメント管理機能を使用して、組織の外部ユーザーのオンボードを簡略化できます。

- [カスタム質問を設定する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-onboard#configure-custom-questions)
- [検証済み ID](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-onboard#verified-ids)
- [属性の指定](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-onboard#specify-attributes)

次のセクションでは、これらの機能がこの目標の達成にどのように役立つかについて説明します。

### カスタム質問を設定する

エンタイトルメント管理のアクセス パッケージのカスタム質問機能を使用すると、アクセス パッケージの作成者は、レビュー担当者が要求プロセスの一環として回答する質問を設定することができます。 これらの質問は、外部ユーザーが特定のアクセス パッケージへのアクセスを要求するビジネス上の正当な理由などの、各要求で回答が異なる可能性がある場合に役立ちます。 これらは要求オブジェクトに保持されますが、MS Graph を使用して要求オブジェクトに対してクエリを実行するアプリケーションでのみ使用できます。 この機能により、レビュー担当者はリソースへの要求を迅速に承認または拒否することができます。

[Image: アクセス パッケージに要求者情報の質問を設定する様子を示すスクリーンショット。]

この機能では、自由形式のテキストや多肢選択式など、さまざまな種類の質問をサポートしており、さまざまなロケールの外部ユーザー向けにローカライズできます。

このプロセスのステップ バイ ステップ ガイドについては、「[要求者情報をアクセス パッケージに追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#create-the-initial-policy)。

### 検証済み ID

カスタム質問機能を使用すると、要求者はアクセス パッケージの要求プロセス中に質問に回答できますが、外部ユーザーがアクセス パッケージ内のアプリにアクセスできるようにする前に、追加のセキュリティ レイヤが必要になる場合があります。 確認済 ID 機能を使用すると、信頼できる発行者からの資格情報を含む検証済み ID の提示を要求できます。 この機能を使用すると、承認者は、ユーザーがアクセス パッケージ要求内で資格情報を提示したときに、外部ユーザーの検証可能な資格情報が検証済みであるどうかを迅速に確認できます。

[Image: Microsoft Entra 確認済み ID の発行者の選択を示すスクリーンショット。]

このプロセスのステップ バイ ステップ ガイドについては、「[エンタイトルメント管理でアクセス パッケージの検証済み ID 設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-verified-id-settings)」を参照してください。

### 属性の指定

アプリでは、外部ユーザーが持っていない属性が必要になる場合があります。 属性の指定機能を使用すると、後で要求が完了したときに使用するために、情報を保存することができます。 たとえば、外部ユーザーの国コードを必要とするパートナー ポータル アプリケーションがあるとします。 カスタム質問に対する回答とは異なり、これらの回答は永続的であり、要求ごとに変更されることはありません。

属性の構成は、カスタム質問の構成と似ていますが、個々のアクセス パッケージではなく、カタログ内のアプリに表示されます。

[Image: ユーザー オブジェクトに保持される属性を指定する様子を示すスクリーンショット。]

アクセス パッケージに属性収集用に構成されたリソースが含まれている場合、外部ユーザーは、アクセス パッケージ自体に指定されたカスタム質問に加えて、それらの値を自動的に求められます。 これらの属性に指定された情報も承認者に提示され、要求が承認されると、要求者のユーザー オブジェクトに書き込まれます。 外部ユーザーが Teams または SharePoint Online とのみ共同作業している場合は、属性はおそらく必要ありません。

注

属性の指定機能はクラウドのみであり、オンプレミスの同期されたユーザーに属性を書き込むことができないためです。

このプロセスのステップ バイ ステップ ガイドについては、「[リソース属性をカタログ内に追加する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#add-resource-attributes-in-the-catalog)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-onboard-external-user"} -->
## チュートリアル - 承認プロセスを通じて Microsoft Entra ID に外部ユーザーをオンボードする - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-onboard-external-user
- Service: entra-id-governance / entitlement-management
- Article date: 2024-07-15
- Summary: エンタイトルメント管理で承認を必要とする外部ユーザー向けのアクセス パッケージの作成方法に関するステップ バイ ステップのチュートリアル。

エンタイトルメント管理は、外部ユーザーをオンボードする手段として使用できます。 この機能を使用すると、外部ユーザーが一連のリソースへのアクセスを要求でき、その場合、ディレクトリにアクセスできるようにする前に承認を設定できます。 エンタイトルメントを通じてオンボードされた外部ユーザーについては、アクセス パッケージを使用してライフサイクルを管理できます。 最後のアクセス パッケージの有効期限が切れると、ディレクトリから削除されます。

このチュートリアルでは、あなたは WoodGrove Bank の IT 管理者として勤務しているとします。 あなたは、ご自身のビジネス グループが連携している外部組織のパートナーをオンボードするアクセス パッケージの作成を求められました。 **外部コラボレーション**と呼ばれる Teams グループにアクセスする必要があります。 組織のコラボレーションには、内部スポンサーによる承認が必要です。 また、パートナーのアクセス権を 60 日後に期限切れにする必要があるということも知らされています。 エンタイトルメント管理を使用するには、次のいずれかのライセンスが必要です。

- Microsoft Entra ID P2 または Microsoft Entra ID Governance
- Enterprise Mobility + Security (EMS) E5 ライセンス

詳細については、「 [ライセンス要件](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview#license-requirements)」を参照してください。

### 手順 1: 基本情報を構成する

1. 少なくとも [ID ガバナンス管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者、ユーザー管理者、アクセス パッケージ マネージャーがあります。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**アクセスパッケージ**に移動します。
3. アクセス パッケージを選ぶときに、"アクセスが拒否されました" と表示される場合は、Microsoft Entra ID P2 または Microsoft Entra ID Governance ライセンスがディレクトリに存在することを確認します。
4. [ **新しいアクセス パッケージ**] を選択します。
5. [ **基本** ] タブで、 **外部ユーザー パッケージ** の名前と、 **承認待ちの外部ユーザーの Access** の説明を入力します。
6. **[カタログ**] ドロップダウン リストは [**全般**] のままにしておくことができます。

### 手順 2: リソースを構成する

1. [ **次へ** ] を選択して、[ **リソース ロール** ] タブを開きます。

    このタブでは、アクセス パッケージに含めるリソースとリソース ロールを選択します。
2. **[グループとチーム**] を選択し、グループ**の外部コラボレーション**を検索します。

### 手順 3: 要求を構成する

1. [ **次へ** ] を選択して [要求] タブ **を** 開きます。

    このタブでは、要求ポリシーを作成します。 *ポリシー*は、アクセス パッケージにアクセスするための規則またはガードレールを定義します。 リソース ディレクトリ内の特定のユーザーがこのアクセス パッケージを要求することを許可するポリシーを作成します。
2. [ **アクセスを要求できるユーザー** ] セクションで、[ **ディレクトリにないユーザー** の場合] を選択し、[ **すべてのユーザー (すべての接続された組織と新しい外部ユーザー)]** を選択します。
3. ディレクトリにまだいないユーザーは、このアクセス パッケージの要求を表示して送信できるため、[**承認を要求**する] 設定には **[はい**] が必須です。
4. 次の設定を使用すると、外部ユーザーに対する承認の動作を構成できます。
5. **要求者の理由を要求する**場合は、[**はい**] のままにします。
6. [ **ステージの数** ] では、これは **1 のままにしておきます**。
7. 承認者シナリオでは、[ **内部スポンサー**] を選択します。 このオプションは、あなたが関わっている特定の組織のためにスポンサーを提供できるように構成された、接続された[組織](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-organization)から提供されています。 これにより、ご自身の組織内から接続された組織で指定されたユーザーを承認者として設定できます。
8. **決定は何日以内に行う必要がありますか?** これを **14** のままにします。
9. [ **承認者の正当な理由を要求する** ] の場合は、[ **はい**] のままにします。
10. [ **新しい要求と割り当てを有効にする** ] を **[はい** ] に設定すると、このアクセス パッケージが作成されたらすぐに要求できるようになります。

### 手順 4: 要求者情報を構成する

1. [ **次へ** ] を選択して [ **リクエスタ情報** ] タブを開く
2. この画面では、追加の情報を要求元から収集するために、さらに質問をすることができます。 これらの質問は要求フォームに表示され、必須または省略可能に設定できます。 ここでは、これらは空のままでかまいません。

### 手順 5: ライフサイクルを構成する

1. [ **次へ** ] を選択して [ **ライフサイクル** ] タブを開く
2. [ **有効期限** ] セクションで、[ **アクセス パッケージの割り当ての有効期限]** を [日数] に設定 **します**。
3. **割り当ての有効期限**を **60** 日に設定します。 このフィールドでは、ゲスト ユーザーがいつアクセス権を更新する必要があるかを決定します。
4. ゲストがまだアクセス パッケージ **に** アクセスする必要があるかどうかを定期的にチェックできるアクセス レビューを構成することもできます。 レビューを自己レビューにすることも、このタスクに特定のレビューを設定することもできます。 詳細については、「 [アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-reviews-create)」を参照してください。

### 手順 6: アクセス パッケージを確認して作成する

1. [ **次へ** ] を選択して、[ **確認と作成** ] タブを開きます。
2. この画面では、アクセス パッケージを作成する前にその構成を確認できます。 問題がある場合は、これらのタブを使用して作成エクスペリエンスの特定のポイントに移動し、編集を行います。
3. 選択内容に問題がなければ、[ **作成**] を選択します。 しばらくすると、アクセス パッケージが正常に作成されたという通知が表示されます。
4. 作成すると、アクセス パッケージの **[概要** ] ページに移動します。 **マイ アクセス ポータルのリンク**を見つけて、ここで値をコピーできます。 このリンクを共有された外部ユーザーはこのパッケージを要求できるようになり、コラボレーションを開始できます。

### 手順 7: リソースをクリーンアップする

この手順では、 **外部ユーザー パッケージ** アクセス パッケージを削除できます。

1. 少なくとも [ID ガバナンス管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、アクセス パッケージ マネージャーがあります。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**アクセスパッケージ**に移動します。
3. **外部ユーザー パッケージ** アクセス パッケージを開きます。
4. **[リソース ロール] を選択します**。
5. このアクセス パッケージに追加した **外部コラボレーション** グループを選択し、[ **詳細** ] ウィンドウで [ **リソース ロールの削除**] を選択します。 表示されるメッセージで、[ **はい**] を選択します。
6. アクセス パッケージの一覧を開きます。
7. **[外部ユーザー パッケージ**] で、省略記号 (...) を選択し、[**削除**] を選択します。 表示されるメッセージで、[ **はい**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-organization"} -->
## 接続されている組織をエンタイトルメント管理で管理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-organization
- Service: entra-id-governance / entitlement-management
- Article date: 2024-07-15
- Summary: 自分の組織外のユーザーがアクセス パッケージを要求して、プロジェクトの共同作業を行うことができるようにする方法について説明します。

エンタイトルメント管理を使用すると、組織外のユーザーと共同作業できます。 特定の外部組織の多くのユーザーと頻繁に共同作業をする場合は、それらの組織の ID ソースを接続されている組織として追加できます。 接続されている組織を使用すると、アクセスのリクエスト方法が簡略化され、それらの組織のより多くのユーザーがアクセスをリクエストできます。 この記事では、接続された組織を追加して、自分の組織外のユーザーが自分のディレクトリ内のリソースを要求できるようにする方法について説明します。

### 接続されている組織とは

接続された組織とは、自分と関係のある別の組織のことです。 その組織内のユーザーが、SharePoint Online サイトやアプリなどのリソースにアクセスできるようにするには、そのディレクトリ内にその組織のユーザーを表したものが必要です。 ほとんどの場合、その組織のユーザーは現在の Microsoft Entra ディレクトリにはまだ存在しないため、必要に応じてエンタイトルメント管理を使って、Microsoft Entra ディレクトリに参加させることができます。

アクセスを要求するすべてのユーザーに対してパスを提供し、それらの新しいユーザーが属する組織がわからない場合は、[自分のディレクトリ以外のユーザーに対するアクセス パッケージ割り当てポリシー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#for-users-not-in-your-directory)を構成できます。 そのポリシー内で、**[すべてのユーザー (接続しているすべての組織とすべての新しい外部ユーザー)]** のオプションを選びます。 リクエストしたユーザーが承認され、かつそれらのユーザーが自分のディレクトリ内の接続されている組織に属していない場合は、接続されている組織がそれらのユーザー用に自動的に作成されます。

指定した組織の個人のみがアクセスをリクエストできるようにする場合は、まずそれらの接続されている組織を作成します。 次に、[自分のディレクトリ内以外のユーザーに対するアクセス パッケージ割り当てポリシー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#for-users-not-in-your-directory)を構成し、**[特定の接続されている組織]** オプションを選び、作成した組織を選びます。

エンタイトルメント管理では、接続されている組織を構成するユーザーを指定する方法が 4 つあります。 次のようなものです。

- (任意の Microsoft クラウドの) 別の Microsoft Entra ディレクトリのユーザー、
- [SAML/WS-Fed ID プロバイダー (IdP) フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation)用に構成されている Microsoft 以外の別のディレクトリのユーザー
- Microsoft 以外の別のディレクトリ内のユーザーで、メール アドレスがすべて共通でその組織に固有の同じドメイン名を持っている、または
- *live.com* ドメインなどの Microsoft アカウントを持つユーザー (共通の組織を持たないユーザーと共同作業をするビジネス ニーズがある場合)。

たとえば、あなたは Woodgrove Bank で働いていて、2 つの外部組織と共同作業を行う必要があるとします。 両方の外部組織のユーザーに同じリソースへのアクセスを付与する必要がありますが、これら 2 つの組織の構成は異なっています。

- Contoso は Microsoft Entra ID をまだ使っていません。 Contoso ユーザーは末尾が *contoso.com* のメール アドレスを持っています。
- Graphic Design Institute は Microsoft Entra ID を使っており、少なくとも一部のユーザーは末尾が *graphicdesigninstitute.com* のユーザー プリンシパル名を持っています。

この場合は、2 つの接続されている組織を構成し、それから 1 つのポリシーを含む 1 つのアクセス パッケージを構成できます。

1. [メールのワンタイム パスコード (OTP) 認証](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode)が有効なことを確認します。有効であれば、Microsoft Entra ディレクトリにまだ属していないドメインのユーザーは、アクセスを要求するときや、後でリソースにアクセスするときに、メールのワンタイム パスコードを使って認証を行うことができます。 さらに、外部ユーザーのアクセスを許可するため、[Microsoft Entra B2B の外部コラボレーション設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users#configure-your-azure-ad-b2b-external-collaboration-settings)ことが必要になる場合があります。
2. Contoso 用に、接続されている組織を作成します。 *contoso.com* ドメインを指定すると、エンタイトルメント管理は、そのドメインに関連付けられた既存の Microsoft Entra テナントが存在しないことを認識し、その接続された組織のユーザーは *contoso.com* メール アドレス ドメインを使ってメールのワンタイム パスコードで認証を行うと認識されます。
3. Graphic Design Institute 用に、別の接続されている組織を作成します。 *graphicdesigninstitute.com* ドメインを指定すると、エンタイトルメント管理は、そのドメインに関連付けられたテナントが存在することを認識します。
4. 外部ユーザーのリクエストを許可するカタログ内で、アクセス パッケージを作成します。
5. そのアクセス パッケージ内で、**自分のディレクトリ内以外のユーザー**に対するアクセス パッケージ割り当てポリシーを作成します。 そのポリシー内で、**[特定の接続されている組織]** オプションを選び、2 つの接続されている組織を指定します。 これにより、接続された組織のいずれかと一致する ID ソースを持つ各組織のユーザーは、アクセス パッケージを要求できます。
6. *contoso.com* ドメインが含まれるユーザー プリンシパル名を持つ外部ユーザーがアクセス パッケージを要求するときは、メールを使って認証を行います。 このメール ドメインは Contoso に接続されている組織と一致し、ユーザーはそのパッケージを要求できます。 「[外部ユーザーのアクセスのしくみ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users#how-access-works-for-external-users)」では、リクエスト後にその B2B ユーザーが招待され、その外部ユーザーにアクセスが割り当てられる方法が説明されています。
7. 加えて、Graphic Design Institute テナントの組織アカウントを使用している外部ユーザーは、Graphic Design Institute に接続されている組織と一致し、そのアクセス パッケージをリクエストできます。 そして、Graphic Design Institute では Microsoft Entra ID を使っているため、[graphicdesigninstitute.example](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain#verify-your-custom-domain-name) など、Graphic Design Institute テナントに追加された別の*検証済みドメイン*に一致するプリンシパル名を持つユーザーも、同じポリシーを使ってアクセス パッケージを要求できます。

[Image: 例で接続された組織と、割り当てポリシーおよびテナントとの関係を示す図。]

Microsoft Entra ディレクトリまたはドメインのユーザーの認証方法は、認証の種類によって異なります。 接続された組織の認証の種類は次のとおりです。

- Microsoft Entra ID (同じクラウド内)
- Microsoft Entra ID (別のクラウド内)
- [SAML/WS-Fed ID プロバイダー (IdP) フェデレーション](https://learn.microsoft.com/ja-jp/entra/external-id/direct-federation)
- [ワンタイム パスコード](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode) (ドメイン)
- Microsoft アカウント

接続された組織を追加する方法のデモについては、次のビデオをご覧ください。

### 接続されている組織の一覧を表示する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に [Identity Governance 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)以上としてサインインします。
2. 次の項目を参照します: **ID ガバナンス**&gt;**権限管理**&gt;**接続された組織**。
3. 検索ボックスでは、接続されている組織の名前で、接続されている組織を検索できます。 ただし、ドメイン名を検索することはできません。

### 接続されている組織の追加

外部 Microsoft Entra ディレクトリまたはドメインを接続された組織として追加するには、このセクションの手順のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に [Identity Governance 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)以上としてサインインします。
2. 次の項目を参照します: **ID ガバナンス**&gt;**権限管理**&gt;**接続された組織**。
3. [接続されている組織] ページで、**[接続されている組織の追加]** を選びます。

    [Image: [接続されている組織の追加] ボタン]
4. **[基本]** タブを選択し、組織の表示名と説明を入力します。

    [Image: [接続されている組織の追加] の [基本] ペイン]
5. 状態は、新しい接続されている組織の作成時に自動的に **[構成済み]** に設定されます。 接続されたorganizationの state プロパティの詳細については、「接続された組織の State プロパティ」 を参照してください
6. **[ディレクトリとドメイン]** タブを選択し、 **[ディレクトリとドメインの追加]** を選択します。

    その後、**[ディレクトリとドメインの選択]** ペインが開きます。
7. 検索ボックスにドメイン名を入力して、Microsoft Entra ディレクトリまたはドメインを検索します。 どの Microsoft Entra ディレクトリにも関連付けられていないドメインを追加することもできます。 必ずドメイン名全体を入力してください。
8. 組織名と認証の種類が正しいことを確認します。 ユーザー サインインで MyAccess ポータルにアクセスできるかどうかは、組織の認証タイプに依存します。 接続されている組織の認証の種類が Microsoft Entra ID である場合、その組織のディレクトリ内にアカウントがあり、その Microsoft Entra ディレクトリの検証済みドメインを使用するユーザーはすべて、そのディレクトリにサインインします。その後、接続されているその組織を許可するアクセス パッケージへのアクセスを要求できます。 認証タイプがワンタイム パスコードの場合、そのドメインからのメール アドレスのみを持つユーザーが MyAccess ポータルにアクセスできます。 パスコードを使用して認証した後、ユーザーは要求を行うことができます。

    [Image: [ディレクトリとドメインの選択] ペイン]

    注

    一部のドメインからのアクセスは、Microsoft Entra B2B の許可または拒否リストによってブロックされることがあります。 さらに、ユーザーのドメインが、Microsoft Entra 認証用に構成済みの接続されている組織と同じあっても、その Microsoft Entra ディレクトリに対して認証を行わないメール アドレスを持っていると、そのユーザーは、接続されているその組織の一部として認識されません。 詳細については、「[B2B ユーザーに対する特定組織からの招待を許可またはブロックする](https://learn.microsoft.com/ja-jp/entra/external-id/allow-deny-list)」を参照してください。
9. **[追加]** を選んで Microsoft Entra ディレクトリまたはドメインを追加します。 **複数の Microsoft Entra ディレクトリとドメインを追加できます**。
10. Microsoft Entra ディレクトリまたはドメインを追加した後、**[選択]** を選びます。

    組織が一覧に表示されます。

    [Image: [ディレクトリとドメイン] ペイン]
11. **[スポンサー]** タブを選択し、この接続された組織のオプションのスポンサーを追加します。

    スポンサーとは、この接続された組織との関係の連絡先となる、自分のディレクトリ内に既に存在する内部または外部のユーザーです。 内部スポンサーは、自分のディレクトリ内のメンバー ユーザーです。 外部スポンサーは、以前に招待され、自分のディレクトリ内に既に存在する、接続されている組織のゲスト ユーザーです。 スポンサーは、この接続されている組織内のユーザーがこのアクセス パッケージへのアクセスを要求したときに、承認者として利用できます。 ゲスト ユーザーを自分のディレクトリに招待する方法については、[Microsoft Entra B2B コラボレーション ユーザーの追加](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)に関する記事を参照してください。

    **[追加/削除]** を選択すると開くペインで、組織内または組織外のスポンサーを選択できます。 このウィンドウには、自分のディレクトリ内のユーザーとグループのフィルター処理されていない一覧が表示されます。

    [Image: [スポンサー] ペイン]
12. **[確認および作成]** タブを選択し、自分の組織の設定を確認して、 **[作成]** を選択します。

    [Image: [確認および作成] ペイン]

### 接続されている組織の更新

接続されている組織を別のドメインに変更する場合、組織の名前を変更する場合、またはスポンサーを変更する場合は、このセクションの手順のようにして、接続されている組織を更新できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に [Identity Governance 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)以上としてサインインします。
2. 次の項目を参照します: **ID ガバナンス**&gt;**権限管理**&gt;**接続された組織**。
3. [接続されている組織] ページで、更新する接続されている組織を選びます。
4. 接続されている組織の概要ペインで、 **[編集]** を選択して、組織の名前、説明、または状態を変更します。
5. **[ディレクトリとドメイン]** ページで、 **[ディレクトリとドメインを更新する]** を選択して、別のディレクトリまたはドメインに変更します。
6. **[スポンサー]** ページで、 **[内部スポンサー追加]** または **[外部スポンサーの追加]** を選択して、ユーザーをスポンサーとして追加します。 スポンサーを削除するには、スポンサーを選択し、右側のペインで **[削除]** を選択します。

### 接続されている組織の削除

外部の Microsoft Entra ディレクトリまたはドメインとの関係がなくなった場合、または提案された接続されている組織が不要になった場合は、接続されている組織を削除できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に [Identity Governance 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)以上としてサインインします。
2. 次の項目を参照します: **ID ガバナンス**&gt;**権限管理**&gt;**接続された組織**。
3. [接続されている組織] ページで、削除する接続されている組織を選んで開きます。
4. 接続されている組織の概要ウィンドウで、 **[削除]** を選択して削除します。

    [Image: 接続されている組織の [削除] ボタン]

### プログラムによる接続された組織の管理

Microsoft Graph を使用して、接続されている組織を作成、一覧表示、更新、および削除することもできます。 委任された `EntitlementManagement.ReadWrite.All` アクセス許可を持つアプリケーションを持つ適切なロールのユーザーは、API を呼び出して、[connectedOrganization](https://learn.microsoft.com/ja-jp/graph/api/resources/connectedorganization) オブジェクトを管理し、それに対してスポンサーを設定できます。

#### Microsoft PowerShell を使用して接続されている組織を管理する

接続されている組織は、PowerShell で [Identity Governance 用の Microsoft Graph PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph.Identity.Governance/) モジュール バージョン 1.16.0 以降のコマンドレットを使用して管理することもできます。

次のスクリプトは、Graph の `v1.0` プロファイルを使用して、接続されているすべての組織を取得する方法を示しています。 接続されている組織が返されるときには、それぞれの組織に、接続されているその組織のディレクトリとドメインのリスト [idSource](https://learn.microsoft.com/ja-jp/graph/api/resources/identitysource) が格納されています。

```powershell
Connect-MgGraph -Scopes "EntitlementManagement.ReadWrite.All"

$co = Get-MgEntitlementManagementConnectedOrganization -all

foreach ($c in $co) {
  foreach ($i in $c.identitySources) {
    write-output $c.Id $c.DisplayName $i.AdditionalProperties["@odata.type"]
  }
}
```

### 接続された組織の状態プロパティ

現在、エンタイトルメント管理において接続されている組織には、次の 2 つの異なる種類が構成および提案されています:

- **構成済み**の接続されている組織は、完全に機能する接続されている組織であり、その組織内のユーザーは、アクセス パッケージにアクセスできます。 管理者が Microsoft Entra 管理センターで新しい接続されている組織を作成すると、管理者はこの接続されている組織を作成して使おうとしているので、既定で**構成済み**状態になります。 さらに、接続された組織が API を使用してプログラムによって作成されるときに、既定の状態は、別の状態に明示的に設定されていない限り、**構成済み**である必要があります。

    構成済みの接続されている組織は、接続されている組織の選択肢として表示され、"すべての構成済みの接続されている組織" を対象とするすべてのポリシーの範囲に含まれようになります。
- **提案済み**の接続されている組織は、自動的に作成された接続されている組織であり、管理者はその組織を作成または承認していません。 構成済みの接続されている組織の外部にあるアクセス パッケージにユーザーがサインアップすると、自動的に作成された接続されている組織は、そのパートナーシップを設定した管理者がテナント内にないため、**提案済み**状態になります。

    提案済みの接続されている組織は、ポリシーの "すべての構成済みの接続されている組織" 設定の範囲に含まれませんが、特定の組織を対象とするポリシーに対してのみポリシーで使用できます。

構成済みのすべての組織のユーザーが利用できるアクセス パッケージを要求できるのは、構成済みの接続された組織のユーザーのみです。 提案済みの接続された組織のユーザーは、そのドメインの接続された組織が存在しない場合と同じように動作し、そのユーザーの組織が範囲に含まれている、または任意のユーザーが範囲に含まれているアクセス パッケージのみ表示および要求できます。 テナントに "すべての構成済みの接続されている組織" を許可するポリシーがある場合は、ソーシャル ID プロバイダーに対する提案済みの接続されている組織を構成済みに変換しないでください。

注

この新機能の一部として、2020 年 9 月 9 日より前に作成されたすべての接続された組織は、**構成済み**と見なされました。 任意の組織のユーザーにサインアップを許可したアクセス パッケージがあった場合は、その日付より前に作成された接続された組織の一覧を確認して、**構成済み**として誤って分類されている組織がないことを確認する必要があります。 特に、構成済みの接続されている組織すべてのユーザーについて承認を要求しない割り当てポリシーがある場合は、ソーシャル ID プロバイダーを**構成済み**として示さないようにする必要があります。 管理者は、必要に応じて **[状態]** プロパティを更新できます。 ガイダンスについては、「接続されている組織の更新」を参照してください。

注

場合によっては、ユーザーがソーシャル ID プロバイダーの個人用アカウントを使ってアクセス パッケージを要求することがあります。この場合、そのアカウントのメール アドレスには、Microsoft Entra テナントに対応する、既存の接続されている組織と同じドメインが含まれます。 そのユーザーが承認されると、そのドメインを表す新しい接続されている組織が提案される結果になります。 この場合ユーザーは、アクセスを再度要求する代わりに、組織のアカウントを使うようにしてください。それによってポータルでは、このユーザーが構成済みの接続されている組織の Microsoft Entra テナントから来ていることが識別されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-overview"} -->
## エンタイトルメント管理とは - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview
- Service: entra-id-governance / entitlement-management
- Article date: 2024-11-25
- Summary: エンタイトルメント管理の概要と、それを使用して、内部 ID と外部 ID のグループ、アプリケーション、SharePoint Online サイトへのアクセスを管理する方法について説明します。

エンタイトルメント管理は [identity governance](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview) 機能であり、組織は、要求ワークフロー、access割り当て、レビュー、有効期限access自動化することで、ID とaccessのライフサイクルを大規模に管理できます。

組織内のユーザーは、さまざまなグループ、アプリケーション、SharePoint Online サイトにアクセスして仕事を行う必要があります。 要件の変化に応じて、このaccessの管理は困難です。 新しいアプリケーションが追加されるか、ID により多くのaccess権限が必要です。 このシナリオは、外部の組織と共同作業する場合はさらに複雑になります。 組織のリソースにaccessする必要がある他の組織のユーザーがわからない場合や、組織で使用しているアプリケーション、グループ、またはサイトが不明な場合があります。

エンタイトルメント管理を使用すると、グループ、アプリケーション、SharePoint Online サイトへのアクセスを、内部 ID や、それらのリソースにアクセスする必要がある組織外の ID に対して、より効率的に管理できます。 また、プレビュー版のエンタイトルメント管理を使って、エージェントIDにグループ、API権限、役割を割り当てることもできます。

### エンタイトルメント管理を使用する理由

エンタープライズ組織は、多くの場合、次のようなリソースに対する従業員accessを管理する際に課題に直面します。

- ID は、自分のaccess、直属の部下、またはスポンサーが持つべきエージェントを知らない可能性があり、そうする場合でも、accessを承認する適切な個人を見つけるのが困難になる可能性がありますaccess
- リソースへのaccessが検出されて割り当てられると、ID はビジネス ニーズに必要以上に長くaccessに保持される可能性があります

これらの問題は、サプライ チェーン組織や他のビジネス パートナーからの外部 ID など、別の組織からのaccessを必要とする ID に対して複合化されます。 次に例を示します。

- 他社のディレクトリ内のすべての個人を把握する人物がおらず、それらのユーザーを招待できない場合がある
- これらの ID を招待できたとしても、その組織内の誰も一貫してすべての ID を管理することを覚えていないaccess

エンタイトルメント管理は、これらの課題への対処に役立ちます。 お客様がエンタイトルメント管理をどのように使用してきたかについて詳しくは、[Mississippi Division of Medicaid](https://customers.microsoft.com/story/1509263251891323344-mississippi-medicaid-microsoft-security-solutions)、[Storebrand](https://customers.microsoft.com/story/1540760473505561700-storebrand-banking-microsoft-security-solutions)、および [Digital Security and Resilience Team (Microsoft](https://customers.microsoft.com/story/1805346232767723893-microsoft-microsoft-entra-id-governance-other-en-united-states)のケース スタディを参照してください。 下記のビデオでは、エンタイトルメント管理とそのメリットについて概説しています。

### エンタイトルメント管理では何ができますか?

エンタイトルメント管理の機能の一部を次に示します。

- アプリケーション、グループ、Teams、SharePoint サイト、SAP IAG アクセス権、その他のリソースにマルチステージ承認でアクセスできるユーザーを制御し、時間制限付きの割り当てと定期的なアクセス レビューを通じて ID が無期限にアクセスを保持しないようにします。
- 部門やコスト センターなどの ID プロパティに基づいて、それらのリソースに ID accessを自動的に付与し、それらのプロパティが変更されたときに ID のaccessを削除します。
- エージェント ID を必要なリソースにaccessし、エージェント ID のスポンサーが、必要な場合にのみaccessが維持されるようにします。
- access パッケージを作成する機能を管理者以外に委任します。 これらのaccess パッケージには、ID が要求できるリソースが含まれており、委任されたaccess パッケージ マネージャーは、ID が要求できるルール、accessを承認する必要があるユーザー、およびaccess有効期限が切れるタイミングを持つポリシーを定義できます。
- ID がaccessを要求できる接続されている組織を選択します。 ディレクトリにまだいない ID がaccessを要求し、承認されると、その ID は自動的にディレクトリに招待され、access割り当てられます。 accessの有効期限が切れると、他のaccess パッケージの割り当てがない場合は、ディレクトリ内の B2B アカウントを自動的に削除できます。

注意

エンタイトルメント管理を試す準備ができたら、[tutorial でget startedして、最初のaccess パッケージ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-first)を作成できます。

また、[一般的なシナリオ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-scenarios)や下記のビデオも参考にできます

- [組織でエンタイトルメント管理をデプロイする方法](https://www.youtube.com/watch?v=zaaKvaaYwI4)
- [エンタイトルメント管理の使用を監視し、スケーリングする方法](https://www.youtube.com/watch?v=omtNJ7ySjS0)
- [エンタイトルメント管理で委任を行う方法](https://www.youtube.com/watch?v=Fmp1eBxzrqw)

### access パッケージとは何ですか。また、それらのパッケージで管理できるリソースは何ですか?

エンタイトルメント管理では、*access パッケージ*の概念が導入されています。 access パッケージは、id がprojectで動作するか、タスクを実行する必要があるaccessを持つすべてのリソースのバンドルです。 Access パッケージを使用して、内部 ID のaccessを管理したり、組織外の ID を管理したりできます。

エンタイトルメント管理を使用して、ID のaccessを管理できるリソースの種類を次に示します。

- Microsoft Entra セキュリティ グループのメンバーシップ
- Microsoft 365 グループと Teams のメンバーシップ
- フェデレーション/シングル サインオンやプロビジョニングをサポートする SaaS アプリケーションやカスタム統合アプリケーションなど、Microsoft Entraエンタープライズ アプリケーションへの割り当て
- SharePoint Online サイトのメンバーシップ
- エージェント ID またはサービス プリンシパルを持つエージェントの API アクセス許可(Microsoft Entra エージェント IDの一部としてプレビュー段階)
- プレビュー段階の SAP IAG ビジネス ロールとその他のaccess権限

Microsoft Entraセキュリティ グループまたはMicrosoft 365 グループに依存する他のリソースへのアクセスを制御することもできます。 次に例を示します。

- アクセス パッケージ内のMicrosoft Entra セキュリティ グループを使用し、そのグループの [group ベースのライセンス](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true)を構成することで、Microsoft 365の ID ライセンスを付与できます。
- アクセス パッケージ内のMicrosoft Entra セキュリティ グループを使用し、そのグループの[Azureロールの割り当て](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)を作成することで、ID にAzure リソースを管理するためのアクセス権を付与できます。
- アクセス パッケージ内のMicrosoft Entra ロールに割り当て可能なグループを使用し、Microsoft Entra ロールをそのグループに割り当てる>を使用して、id にMicrosoft Entraロールを管理するためのアクセス権を付与できます。

### accessを取得するユーザーを制御How do I?

アクセス パッケージを使用すると、管理者または委任されたアクセス パッケージ マネージャーがリソース (グループ、アプリ、サイト、Microsoft Entraロール、および API のアクセス許可) と、それらのリソースに必要な ID ロールを一覧表示します。

Access パッケージには、1 つ以上の *policies* も含まれます。 ポリシーは、access パッケージに割り当てるルールまたはガードレールを定義します。 各ポリシーを使用して、適切な ID のみがaccess割り当てを持ち、更新されない場合にaccessの有効期限が切れるようにすることができます。

access パッケージと policies.

accessを要求する ID のポリシーを設定できます。 これらの種類のポリシーでは、管理者またはaccess package managerが定義します。

- 既存の ID (通常は従業員または既に招待されたゲスト)、またはaccessを要求する資格がある外部 ID のパートナー組織
- 承認プロセスと、accessを承認または拒否できる ID
- ID のaccess割り当ての期間 (承認後、割り当てが期限切れになるまで)

また、[管理者](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-an-identity)、[規則](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)に基づいて、またはライフサイクル ワークフローを通じてaccessに割り当てられる ID のポリシーを設定することもできます。

次の図は、エンタイトルメント管理のさまざまな要素の例を示します。 2 つのサンプル access パッケージを含む 1 つのカタログが表示されます。

- **Access パッケージ 1** には、リソースとして 1 つのグループが含まれています。 Accessは、ディレクトリ内の一連の ID がaccessを要求できるようにするポリシーで定義されます。
- **Access パッケージ 2** には、グループ、アプリケーション、SharePoint Online サイトがリソースとして含まれています。 Accessは、2 つの異なるポリシーで定義されます。 最初のポリシーでは、ディレクトリ内の一連の ID がaccessを要求できるようにします。 2 番目のポリシーでは、外部ディレクトリ内の ID がaccessを要求できるようにします。

[Image: エンタイトルメント管理の概要の図]

### access パッケージを使用する必要がある場合

Access パッケージでは、access割り当ての他のメカニズムは置き換えられません。 アクセス パッケージは、次のような状況で使用するのに適しています。

- アクセス ポリシー定義をサード パーティの [enterprise ロール管理](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-organizational-roles)からMicrosoft Entra IDに移行する。
- ID には、特定のタスクに対して時間制限付きのaccessが必要です。 たとえば、グループベースのライセンスと動的グループを使用して、すべての従業員が Exchange Online メールボックスを持っていることを確認し、従業員がより多くのアクセス権を必要とする状況にアクセス パッケージを使用できます。 たとえば、ある部署のリソースを別の部署から読み取る権限があります。
- 管理者またはその他の指定された個人の承認を必要とするAccess。
- Access、その職務の期間中に組織の特定の部分のユーザーに自動的に割り当てる必要がありますが、組織内の他の場所やビジネス パートナー組織のユーザーが要求することもできます。
- 部門は、IT 部門が関与することなく、リソースの独自のaccess ポリシーを管理したいと考えています。
- 2 つ以上の組織が 1 つのプロジェクトで共同作業を行っており、その結果、別の組織のリソースにアクセスするには、Microsoft Entra B2B を介して 1 つの組織の複数の ID を取り込む必要があります。

### デリゲート access How do I?

Access パッケージは、*catalogs* と呼ばれるコンテナーで定義されます。 すべてのaccess パッケージに対して 1 つのカタログを作成することも、独自のカタログを作成して所有するように個人を指定することもできます。 管理者は任意のカタログにリソースを追加できますが、管理者以外のユーザーは、自分が所有しているリソースしかカタログに追加できません。 カタログ所有者は、他の ID をカタログ共同所有者として、またはパッケージ マネージャー access追加できます。 これらのシナリオについては、[エンタイトルメント管理での委任とロール](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate)に関する記事で詳しく説明しています。

### 用語の概要

エンタイトルメント管理とそのドキュメントについてより深く理解するために、次の用語一覧を確認してください。

| 期間 | 説明 |
| --- | --- |
| アクセス パッケージ | チームまたはprojectが必要とし、ポリシーで管理されるリソースのバンドル。 access パッケージは常にカタログに含まれます。 ID が自身のためにaccessを要求する必要があるシナリオ用の新しいaccess パッケージを作成します。 |
| access要求 | access パッケージ内のリソースをaccessする要求。 要求は通常、承認ワークフローを通じて処理されます。 承認された場合、要求側 ID はaccess パッケージの割り当てを受け取ります。 |
| 代入 | ID にaccess パッケージを割り当て、ID にそのaccess パッケージのすべてのリソース ロールが割り当てられていることを確認します。 Accessパッケージの割り当ては、通常、有効期限が切れる前に制限があります。 |
| カタログ | 関連するリソースとaccess パッケージのコンテナー。 カタログは委任に使用されるため、管理者以外のユーザーは独自のaccess パッケージを作成できます。 カタログ所有者は、自分が所有するリソースをカタログに追加できます。 |
| カタログ作成者 | 新しいカタログを作成する権限を持つアイデンティティの集合体です。 非管理者の身分がカタログ作成者として認可されている場合、新しいカタログを作成すると、そのカタログの所有者を自動的に取得します。 |
| 接続されている組織 | 関係がある外部Microsoft Entraディレクトリまたはドメイン。 接続された組織の ID は、accessの要求が許可されているポリシーで指定できます。 |
| ポリシー | ID のaccess方法、承認できるユーザー、割り当てを通じてaccessする期間など、accessライフサイクルを定義する一連のルール。 ポリシーは、access パッケージにリンクされます。 たとえば、access パッケージには、従業員がaccessを要求するためのポリシーと、外部 ID がaccessを要求するためのポリシーの 2 つがあります。 |
| リソース | ID にアクセス許可を付与できるロールを持つ、Office グループ、セキュリティ グループ、アプリケーション、SharePoint Online サイトなどの資産。 |
| リソース ディレクトリ | 共有する 1 つ以上のリソースがあるディレクトリ。 |
| リソース ロール | リソースに関連付けられ、リソースによって定義されている一連のアクセス許可のことです。 グループには 2 つのロールがあります (メンバーと所有者)。 SharePoint サイトには通常、3 つのロールがありますが、他のカスタム ロールを持つことができます。 アプリケーションにはカスタム ロールを設定できます。 |

### ライセンス要件

この機能には、組織のユーザーのMicrosoft Entra ID ガバナンスまたはMicrosoft Entra スイートサブスクリプションが必要です。 この機能内の一部の機能は、Microsoft Entra ID P2 サブスクリプションで動作する場合があります。 詳細については、各機能の記事を参照してください。 要件に適したライセンスを見つけるには、[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を参照してください。

#### access パッケージにエージェントを割り当てるためのライセンス要件 (プレビュー)

エージェント ID [にMicrosoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を使用するには、次のいずれかのライセンス プランが必要です。

- **Microsoft 365 E7** (エージェント 365 とMicrosoft Entra スイートを含む) は、ユーザー ID とエージェント ID のガバナンスを提供します。
- **Microsoftエージェント 365** ライセンスは、少なくとも Microsoft Entra P1 または Microsoft 365 E3 とペアリングされています。

詳細については、「[Microsoft Agent 365 のプランと価格](https://www.microsoft.com/microsoft-agent-365#plans-and-pricing)」を参照してください。 エージェント固有の機能の完全な一覧については、**Microsoft Entra ID ガバナンス ライセンス表**の [Microsoft Agent 365](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals) 列を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-process"} -->
## 処理と通知を要求する - Microsoft Entra エンタイトルメント管理 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-process
- Service: entra-id-governance / entitlement-management
- Article date: 2024-07-15
- Summary: エンタイトルメント管理でのアクセス パッケージの要求プロセスと電子メール通知が送信されるタイミングについて説明します。

ID がアクセス パッケージに要求を送信すると、そのアクセス要求の配信プロセスが開始されます。 エンタイトルメント管理は、処理中に重要なイベントが発生したときに、電子メール通知を承認者と要求元に送信します。 この記事では、要求プロセス、および送信される電子メール通知について説明します。

### 誰がアクセス パッケージを要求できるか

カタログ、アクセス パッケージ、およびポリシー設定は、ID がアクセス パッケージを要求できるかどうかを制御します。

- **[有効]** のカタログ設定は、ID がカタログ内のアクセス パッケージを要求できるかどうかを決定します。
- カタログ設定の**外部ユーザーに対して有効**は、外部ディレクトリのユーザーがカタログ内のアクセス パッケージを要求できるかどうかを決定します。
- アクセス パッケージ設定の**非表示**は、ユーザーがマイ アクセスでアクセス パッケージを表示できるかどうかを決定します。 これによりアクセス パッケージへのリンクを持つユーザーがそれを要求できるかどうかは制限されません。
- 互換性のないアクセス パッケージとグループのアクセス パッケージの一覧は、既に他の割り当てを持っているユーザーが要求できるかどうかを決定します。
- アクセス パッケージ内のポリシー設定は誰が要求できるかを決定します。

### 要求プロセス

アクセス パッケージ内のリソースへのアクセスを必要とする ID は、アクセス要求を送信できます。 ポリシーの構成によって、要求には承認が必要な場合があります。 要求が承認されると、アクセス パッケージ内の各リソースにユーザー アクセスを割り当てるプロセスが開始されます。 次の図は、プロセスとさまざまな状態の概要を示します。

[Image: 承認プロセスの図]

| 状態 | 説明 |
| --- | --- |
| 提出済み | ユーザーが要求を送信します。 |
| 承認待ち | アクセスパッケージのポリシーが承認を必要とする場合、要求は承認の保留状態になります。 |
| 有効期限切れ | 承認要求タイムアウト期間内に要求を承認する承認者がいなかった場合、要求は期限切れになります。 再試行するには、ユーザーはその要求を再送信する必要があります。 |
| 拒否 | 承認者は、要求を拒否します。 |
| 承認済み | 承認者は、要求を承認します。 |
| 配信 | ユーザーには、アクセス パッケージ内のすべてのリソースへのアクセスが割り当てられて**いません**。 これが外部ユーザーの場合、ユーザーはまだリソース ディレクトリにアクセスしていない可能性があります。 また、同意プロンプトを受け入れていない可能性があります。 |
| 配信済み | ユーザーには、アクセス パッケージ内のすべてのリソースへのアクセスが割り当てられています。 |
| 部分的に配信完了 | ユーザーには、アクセス パッケージ内のすべてのリソースへのアクセスがまだ割り当てられて**いません**。 |
| アクセスの拡張 | ポリシーで拡張が許可されている場合、ユーザーは割り当てを拡張しています。 |
| アクセス有効期限切れ | ユーザーのアクセス パッケージへのアクセスの有効期限が切れています。 アクセスを再度取得するには、ユーザーは要求を送信する必要があります。 |

### 電子メールによる通知

承認者の場合は、アクセス要求を承認する必要があるときにメール通知が送られてきます。 また、アクセス要求が完了したときにも通知を受け取ります。 要求者の場合も、要求の状態を示すメール通知が送られてきます。

次の図は、これらの電子メール通知が承認者または要求者に送信されるタイミングを示しています。 「[電子メール通知の一覧表](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-process#email-notifications-table)」を参照して、図に示されている電子メール通知に対応する番号を見つけてください。

注意

管理者がアクセス パッケージにユーザーを直接割り当てると、アクセス要求が完了または拒否された場合、電子メール通知を受信することはありません。 電子メール通知は、ユーザーがアクセスを要求した場合にのみ送信されます。

#### 最初の承認者および代理承認者

次の図では、最初の承認者および代理承認者のエクスペリエンスと、要求プロセスの中で両者が受け取るメール通知が示されています。

[Image: 最初の承認者と代理承認者の処理フロー]

#### 要求者

次の図は、要求元のエクスペリエンスと、要求プロセスの中で彼らが受信する電子メール通知を示しています。

[Image: 要求元の処理フロー]

#### 複数段階の承認

次の図では、段階 1 と段階 2 の承認者のエクスペリエンスと、要求プロセスの間に承認者が受け取るメール通知を示します。

[Image: 2 段階の承認のプロセス フロー]

#### 電子メール通知の一覧表

次の表では、これらの各電子メール通知についての詳細を提供します。 これらの電子メールを管理するために、規則を使用できます。 たとえば、Outlook では、件名にこの表の語句が含まれている場合には、電子メールをあるフォルダーに移動する規則を作成できます。 この語句は、ユーザーがアクセスを要求しているテナントの既定の言語設定に基づいています。

| # | 電子メールの件名 | 送信日時 | 送信先 |
| --- | --- | --- | --- |
| 1 | 必要なアクション: *[date]*までに転送された要求を承認または拒否してください | このメールは、(要求がエスカレーションされた後に) アクションを実行するように、段階 1 の代理承認者に送信されます。 | 段階 1 の代理承認者 |
| 2 | 必要なアクション: *[date]*までに要求を承認または拒否してください | このメールは、エスカレーションが無効になっている場合に、アクションを実行するように、最初の承認者に送信されます。 | 最初の承認者 |
| 3 | リマインダー: *[date]*までに*[requestor]*の要求を承認または拒否してください。 | このリマインダー メールは、エスカレーションが無効になっている場合に、最初の承認者に送信されます。 そのメールでは、まだ対応していない場合は速やかに対応するように促されます。 | 最初の承認者 |
| 4 | *[time]* までに *[date]* に要求を承認または拒否します | このメールは、(エスカレーションが有効になっている場合に) アクションを実行するように、最初の承認者に送信されます。 | 最初の承認者 |
| 5 | 必要なアクションのリマインダー: *[requestor]* のリクエストを*[date]*までに承認または拒否してください | このリマインダー メールは、エスカレーションが有効になっている場合に、最初の承認者に送信されます。 そのメールは、まだ対応していない場合に行動を起こすよう求めています。 | 最初の承認者 |
| 6 | *[access\_package]* に対する要求の有効期限が切れました | このメールは、要求の有効期限が切れた後で、最初の承認者と段階 1 の代理承認者に送信されます。 | 最初の承認者、段階 1 代理承認者 |
| 7 | *[requestor]* の *[access\_package]* への要求が承認されました | このメールは、要求が完了した時点で、最初の承認者と段階 1 の代理承認者に送信されます。 | 最初の承認者、段階 1 代理承認者 |
| 8 | *[requestor]* の *[access\_package]* への要求が承認されました | このメールは、段階 1 の要求が承認された時点で、複数段階の要求の最初の承認者と段階 1 の代理承認者に送信されます。 | 最初の承認者、段階 1 代理承認者 |
| 9 | *[access\_package]* への要求が拒否されました | このメールは、要求が拒否されたときに、要求元に送信されます | 要求者 |
| 10 | *[access\_package]* に対する要求の有効期限が切れました | このメールは、1 段階または複数段階の要求の終了時に要求元に送信されます。 そのメールでは、要求の有効期限が切れたことが要求者に通知されます。 | 要求者 |
| 11 | 必要なアクション: *[date]*までに要求を承認または拒否してください | このメールは、エスカレーションが無効になっている場合に、アクションを実行するように、2 番目の承認者に送信されます。 | 2 番目の承認者 |
| 12 | *[date]* までに要求を承認または却下するアクションが必要です。 | このリマインダー メールは、エスカレーションが無効になっている場合に、2 番目の承認者に送信されます。 その通知では、アクションをまだ実行していない場合にアクションを実行するように求められます。 | 2 番目の承認者 |
| 13 | 必要なアクション: *[requestor]* の要求を *[date]* までに承認または拒否してください | このメールは、エスカレーションが有効になっている場合に、アクションを実行するように、2 番目の承認者に送信されます。 | 2 番目の承認者 |
| 14 | 必要なアクションのリマインダー: *[requestor]* のリクエストを*[date]*までに承認または拒否してください | このリマインダー メールは、エスカレーションが有効になっている場合に、2 番目の承認者に送信されます。 その通知では、アクションをまだ実行していない場合にアクションを実行するように求められます。 | 2 番目の承認者 |
| 15 | 必要なアクション: *[date]*までに転送された要求を承認または拒否してください | このメールは、エスカレーションが有効になっている場合に、アクションを実行するように、段階 2 の代理承認者に送信されます。 | 段階 2 の代理承認者 |
| 16 | *[requestor]* の *[access\_package]* への要求が承認されました | このメールは、要求が承認された時点で、2 番目の承認者と段階 2 の代理承認者に送信されます。 | 2 番目の承認者、段階 2 の代理承認者 |
| 17 | *[access\_package]* に対する要求の有効期限が切れました | このメールは、要求の有効期限が切れた後で、2 番目の承認者または代理承認者に送信されます。 | 2 番目の承認者、段階 2 の代理承認者 |
| 18 | *[access\_package]* にアクセスできるようになりました | このメールは、アクセス権の使用を始めるために、エンド ユーザーに送信されます。 デフォルトでは、エンタイトルメント管理のメール無効化機能を使用して無効にされています。 | 要求者 |
| 19 | *[access\_package]* へのアクセスを *[date]* まで延長します | このメールは、アクセスの有効期限が切れる前に、エンド ユーザーに送信されます。 デフォルトでは、エンタイトルメント管理のメール無効化機能を使用して無効にされています。 | 要求者 |
| 20 | *[access\_package]* に対するアクセスが終了しました | このメールは、アクセスの有効期限が切れた後で、エンド ユーザーに送信されます。 デフォルトでは、エンタイトルメント管理のメール無効化機能を使用して無効にされています。 | 要求者 |

#### アクセス要求メール

承認を必要とする構成になっているアクセス パッケージのアクセス要求を要求元が送信するときに、ポリシーに追加されているすべての承認者がその承認ステージで、要求の詳細を含む電子メール通知を受け取ります。 メールの詳細には、要求者の名前、組織、業務上の正当な理由、要求されたアクセスの開始日と終了日 (提供されている場合) が含まれます。 詳細には、要求が送信された日時と、要求の有効期限が切れる日時も含まれます。

この電子メールには、承認者が選択すると、アクセス要求を承認または拒否するためにマイ アクセスに移動できるリンクが含まれています。 アクセス要求を完了するために承認者に送信されるサンプル電子メール通知を次に示します。

[Image: アクセス パッケージに対する要求を承認するよう求めるメール]

承認者は、リマインダー メールを受け取ることもできます。 そのメールでは、承認者は要求についての決定を下すよう求められます。 アクションの実行を思い出させるために承認者が受け取るメール通知のサンプルを次に示します。

[Image: アクセス要求のリマインダー電子メール]

#### 代理承認者への承認依頼の電子メール

代替承認者の設定が有効になっていて、要求がまだ保留中の場合、要求は転送されます。 代理承認者は、要求を承認または拒否するためのメールを受け取ります。 段階 1 と段階 2 で代理承認者を有効にすることができます。 代理承認者が受け取る電子メール通知のサンプルを次に示します。

[Image: 代理承認者への要求の電子メール]

承認者と代理承認者はどちらも、要求を承認または拒否することができます。

#### 承認または拒否の電子メール

要求元によって送信されたアクセス要求を受け取った承認者は、アクセス要求を承認または拒否できます。 承認者は、その意思決定に対するビジネスの正当性を追加する必要があります。 要求が承認された後で承認者または代理承認者に送信されるメールのサンプルを次に示します。

[Image: アクセス パッケージに対する承認済み要求のメール]

アクセス要求が承認され、それらのアクセスがプロビジョニングされると、アクセス パッケージへのアクセスが許可されたことを示す電子メール通知が、要求元に送信されます。 アクセス パッケージへのアクセスが許可されたときに、要求者に送信されるメール通知のサンプルを次に示します。

[Image: 要求者の承認されたアクセス要求のメール]

アクセス要求が拒否されると、要求元に電子メール通知が送信されます。 アクセス要求が拒否されたときに、要求元に送信される電子メール通知のサンプルを次に示します。

[Image: 要求元の要求が拒否された電子メール]

#### 複数段階承認のアクセス要求メール

複数段階の承認が有効になっている場合、要求元がアクセス権を受け取るためには、各段階で少なくとも 1 人の承認者が要求を承認する必要があります。

段階 1 では、最初の承認者がアクセス要求のメールを受け取り、決定を行います。

段階 1 の最初の承認者または代理承認者が要求を承認した後、段階 2 が開始されます。 段階 2 では、2 番目の承認者がアクセス要求通知メールを受け取ります。 段階 2 の 2 番目の承認者または代理承認者 (エスカレーションが有効になっている場合) が要求の承認または拒否を決定した後、最初の承認者と 2 番目の承認者、段階 1 と段階 2 のすべての代理承認者、および要求者に、通知メールが送信されます。

#### アクセス要求の有効期限切れの電子メール

要求を承認または拒否した承認者がいない場合、アクセス要求は期限切れになる可能性があります。

要求が設定されている有効期限に達すると、期限切れになり、承認者は承認または拒否することができなくなります。

アクセス要求の有効期限が切れたために、アクセス要求の再送信が必要であることを示す電子メール通知が要求元に送信されます。 次の図では、アクセス延長を要求するときの、要求者のエクスペリエンスと、要求者が受け取るメール通知を示します。

[Image: 要求者のアクセス延長のプロセス フロー]

アクセス要求の有効期限が切れたときに、要求元に送信される電子メール通知のサンプルを次に示します。

[Image: アクセス要求が期限切れとなった場合の要求元への電子メール]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-reports"} -->
## エンタイトルメント管理でレポートとログを表示する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-reports
- Service: entra-id-governance / entitlement-management
- Article date: 2026-09-03
- Summary: エンタイトルメント管理で ID 割り当てレポートと監査ログを表示する方法について説明します。

エンタイトルメント管理レポートとMicrosoft Entra監査ログには、ID がアクセスできるリソースの詳細が表示されます。 管理者は、ID のアクセス パッケージとリソースの割り当てを表示したり、監査目的で要求ログを表示したり、ID 要求の状態を判断したりできます。 この記事では、エンタイトルメント管理レポートとMicrosoft Entra監査ログを使用する方法について説明します。

この記事では、エンタイトルメント管理で現在のオブジェクトに関するレポートを表示する方法について説明します。 ID やアプリケーション ロールの割り当てなど、Microsoft Entraオブジェクトの履歴を保持して報告するには、Microsoft Entra ID のデータを使用して Azure Data Explorer (ADX) でカスタマイズされたレポートを参照してください。

エンタイトルメント管理でアクセスできるリソース ID を表示する方法については、次のビデオをご覧ください。

### アクセス パッケージに割り当てられている ID を表示する

このレポートを使用すると、アクセス パッケージに割り当てられているすべての ID を一覧表示できます。

1. あなたは少なくとも[Identity Governance Administrator](https://entra.microsoft.com)として[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。
2. **ID ガバナンス**&gt;**権限管理**&gt;**アクセスパッケージ** を参照します。
3. [アクセス パッケージ] ページで、目的のアクセス パッケージを選択します。
4. 左側のメニューで、[ **割り当て]** を選択し、[ **ダウンロード**] を選択します。
5. ファイル名を確認し、[ **ダウンロード**] を選択します。

### ユーザーのアクセス パッケージを表示する

このレポートを使用すると、ユーザーが要求できるすべてのアクセス パッケージと、現在そのユーザーに割り当てられているアクセス パッケージを一覧表示できます。

1. あなたは少なくとも[Identity Governance Administrator](https://entra.microsoft.com)として[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。
2. **ID ガバナンス**&gt;**特権管理**&gt;**レポート** を参照します。
3. **ユーザーのアクセス パッケージを選択します**。
4. [ **ユーザーの選択** ] を選択して、[ユーザーの選択] ウィンドウを開きます。
5. 一覧からユーザーを検索し、[選択] を **選択**します。

    [ **要求可能]** タブには、ユーザーが要求できるアクセス パッケージの一覧が表示されます。 この一覧は、アクセス パッケージに定義 [されている要求ポリシー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#for-users-service-principals-and-agent-identities-in-your-directory) によって決まります。

    [Image: ユーザーのアクセス パッケージ]
6. アクセス パッケージに 1 つ以上のリソースのロールやポリシーがある場合、そのリソースのロールまたはポリシーのエントリーを選択して選択内容の詳細を確認してください。
7. [ **割り当て済み** ] タブを選択すると、ユーザーに現在割り当てられているアクセス パッケージの一覧が表示されます。 アクセス パッケージがユーザーに割り当てられていると、そのユーザーがそのアクセス パッケージ内のすべてのリソースのロールにアクセスできることを意味します。

### ユーザーのリソースの割り当てを表示する

このレポートでは、エンタイトルメント管理でユーザーに現在割り当てられているリソースを一覧表示できます。 このレポートは、エンタイトルメント管理で管理されているリソースを対象としています。 ユーザーは、エンタイトルメント管理の外部にあるディレクトリ内の他のリソースにアクセスできる可能性があります。

1. あなたは少なくとも[Identity Governance Administrator](https://entra.microsoft.com)として[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。
2. **ID ガバナンス**&gt;**特権管理**&gt;**レポート** を参照します。
3. **ユーザーのリソース割り当てを選択します**。
4. [ **ユーザーの選択** ] を選択して、[ユーザーの選択] ウィンドウを開きます。
5. 一覧からユーザーを検索し、[選択] を **選択**します。

    ユーザーに現在割り当てられているリソースの一覧が表示されます。 また、この一覧には、アクセスの開始日と終了日と共に、リソースのロールの取得元のアクセス パッケージとポリシーも表示されます。

    ユーザーが 2 つ以上のパッケージ内の同じリソースへのアクセスを得ている場合は、矢印を選択して各パッケージやポリシーを確認できます。

    [Image: ユーザーのリソース割り当て]

### ユーザーの要求のステータスを確認します。

ユーザーがアクセス パッケージへのアクセスを要求および受信した方法の詳細を取得するには、Microsoft Entra監査ログを使用できます。 具体的には、`EntitlementManagement` と `UserManagement` のカテゴリ内のログ記録を使用すると、各要求の処理手順における、より詳細な情報を取得できます。

1. あなたは少なくとも[Identity Governance Administrator](https://entra.microsoft.com)として[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**監査ログ**を参照します。
3. 上部で、探している監査レコードに応じて、 **カテゴリ** を `EntitlementManagement` または `UserManagement` に変更します。
4. [ **適用]** を選択します。
5. ログをダウンロードするには、[ **ダウンロード**] を選択します。

Microsoft Entra IDが新しい要求を受信すると、監査レコードが書き込まれます。監査レコードでは、**Category** は `EntitlementManagement` であり、**Activity** は通常、`User requests access package assignment`です。 Microsoft Entra 管理センターで直接割り当てが作成された場合、監査レコードの **Activity** フィールドは `Administrator directly assigns user to access package` であり、割り当てを実行しているユーザーは **ActorUserPrincipalName** によって識別されます。

Microsoft Entra IDは、要求の進行中に、次のような追加の監査レコードを書き込みます。

| カテゴリ | アクティビティ | 要求の状態 |
| --- | --- | --- |
| `EntitlementManagement` | `Auto approve access package assignment request` | 要求に承認は必要ありません |
| `UserManagement` | `Create request approval` | 要求には承認が必要 |
| `UserManagement` | `Add approver to request approval` | 要求には承認が必要 |
| `EntitlementManagement` | `Approve access package assignment request` | 要求が承認されました |
| `EntitlementManagement` | `Ready to fulfill access package assignment request` | 要求が承認されました、または承認は必要ありません |

ユーザーにアクセス権が割り当てられると、Microsoft Entra IDは `EntitlementManagement` カテゴリの監査レコードを **Activity**`Fulfill access package assignment` で書き込みます。 アクセス権を受け取ったユーザーは、 **ActorUserPrincipalName** フィールドによって識別されます。

アクセスが割り当てられなかった場合、Microsoft Entra IDは、`EntitlementManagement` カテゴリの監査レコードを **Activity**`Deny access package assignment request`、承認者によって要求が拒否された場合は `Access package assignment request timed out (no approver action taken)` (承認者が承認できる前にタイムアウトした場合) を書き込みます。

ユーザーのアクセス パッケージの割り当てが期限切れになったり、ユーザーによって取り消されたり、管理者によって削除されたりすると、Microsoft Entra ID`EntitlementManagement` の **Activity** を持つ `Remove access package assignment` カテゴリの監査レコードを書き込みます。

### 接続されている組織の一覧をダウンロードする

1. あなたは少なくとも[Identity Governance Administrator](https://entra.microsoft.com)として[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。
2. 次の項目を参照します: **ID ガバナンス**&gt;**権限管理**&gt;**接続された組織**。
3. [接続されている組織] ページで、[ **ダウンロード**] を選択します。

### 職務分離に反するアクセスを持つ、または持つことになるユーザーを特定する

アクセス パッケージの職務設定を分離することで、セキュリティ グループのメンバーであるユーザー、または既に 1 つのアクセス パッケージに割り当てられているユーザーが、互換性のないパッケージとしてマークすることで、別のアクセス パッケージを要求できないことを構成できます。 その後、互換性のないとして構成されているアクセス パッケージを表示 し、別のアクセス パッケージに互換性のないアクセス権を持つユーザーを一覧表示 。 また、Microsoft Entra 管理センターで、Microsoft Graph を使用して、または PowerShell を使用して、別のアクセスパッケージに互換性のないアクセス権をすでに持っているユーザーをリストすることもできます。

### アクセス パッケージのイベントを表示する

[Azure Monitor](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logs-and-reporting) に監査ログ イベントを送信するように構成している場合は、組み込みのブックとカスタム ブックを使用して、Azure Monitorに保持されている監査ログを表示できます。

アクセス パッケージのイベントを表示するには、基になるAzure Monitor ワークスペース (詳細については、[Azure Monitorのログ データとワークスペースへのアクセスの管理](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/manage-access#azure-rbac)に関するページを参照) および次のいずれかのロールにアクセスできる必要があります。

- グローバル管理者
- セキュリティ管理者
- セキュリティ閲覧者
- レポート閲覧者
- アプリケーション管理者

1. [Microsoft Entra 管理センター](https://entra.microsoft.com) に少なくとも [Reports Reader](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) としてサインインします。 Azure Monitor ワークスペースを含むリソース グループにアクセスできることを確認します。
2. **[Entra ID]**&gt;**[監視とヘルス]**&gt;**[ブック]** の順に移動します。
3. 複数のサブスクリプションがある場合は、ワークスペースが含まれているサブスクリプションを選択します。
4. サブスクリプションを選択した後、またはサブスクリプションが 1 つしかない場合は、 *Access Package Activity* という名前のブックを選択します。
5. そのブックで、時間範囲 (不明な場合 **は [すべて** ] に変更) を選択し、その期間中にアクティビティを持っていたすべてのアクセス パッケージのドロップダウン リストからアクセス パッケージ ID を選択します。 選択した時間範囲内に発生したアクセスパッケージに関連のあるイベントが表示されます。

    [Image: アクセス パッケージ イベントを表示する]

    各行には、時刻、アクセス パッケージ ID、操作の名前、オブジェクト ID、UPN、操作を開始したユーザーの表示名が含まれます。 この他の詳細は JSON に含まれています。

### エンタイトルメント管理によって行われなかったアプリケーション ロールの割り当ての履歴を表示する

[Azure Monitor](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logs-and-reporting) に監査ログ イベントを送信するように構成している場合は、組み込みのブックとカスタム ブックを使用して、Azure Monitorに保持されている監査ログを表示できます。

アプリケーションのロール割り当てアクティビティ に関する ブックには、グローバル管理者がユーザーを直接アプリケーション ロールに割り当てた場合など、アクセス パッケージの割り当てによるものでないアプリケーション ロールの割り当て変更があるかどうかを示します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com) に少なくとも [Reports Reader](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) としてサインインします。 Azure Monitor ワークスペースを含むリソース グループにアクセスできることを確認します。
2. **[Entra ID]**&gt;**[監視とヘルス]**&gt;**[ブック]** の順に移動します。
3. 複数のサブスクリプションがある場合は、ワークスペースが含まれているサブスクリプションを選択します。
4. サブスクリプションを選択した後、またはサブスクリプションが 1 つしかない場合は、 *Access Package Activity* という名前のブックを選択します。

    [Image: アプリ ロールの割り当てを表示する]
5. エンタイトルメント アクティビティを省略することを選択した場合は、エンタイトルメント管理によって行われなかったアプリケーション ロールに対する変更のみが表示されます。 たとえば、グローバル管理者がアプリケーション ロールにユーザーを直接割り当てた場合、行が表示されます。

### アクセス パッケージのドリフトを表示する

アクセス パッケージの誤差レポートには、管理グループとエンタープライズ アプリケーションへのアクセスがエンタイトルメント管理ポリシーと一致しなくなった場所が表示されます。 このレポートを使用すると、アクセス パッケージの割り当てなしで直接リソース にアクセスできるユーザーと、期待されるグループ メンバーシップまたはアプリケーションの割り当てなしでアクティブなアクセス パッケージの割り当てを持つユーザーを検索できます。

アクセス パッケージのドリフト レポートはプレビュー段階にあります。

アクセス パッケージの誤差レポートを表示する前に、テナントにMicrosoft Entra ID ガバナンスまたはMicrosoft Entra スイートアドオン ライセンスがあることを確認します。

Microsoft Entra 管理センターでアクセス パッケージの誤差レポートを表示するには、次のいずれかのロールを使用します。

- グローバル管理者。
- ID ガバナンス管理者。
- ディレクトリ リーダー。
- レポート閲覧者。
- カタログ所有者。
- カタログ リーダー。

アクセス パッケージの誤差レポートをダウンロードするには、次のいずれかのロールを使用します。

- グローバル管理者。
- ID ガバナンス管理者。
- ディレクトリ リーダー。
- レポートの閲覧者。

アクセス パッケージのドリフト レポートには、次の項目が含まれます。

- 対応するアクセス パッケージの割り当てがないメンバーを含む Microsoft Entra グループ
- 対応するアクセス パッケージの割り当てを持たないアプリケーション ロールの割り当てを持つエンタープライズ アプリケーション。
- 想定されるグループ メンバーシップまたはアプリケーションの割り当てが見つからないアクティブなアクセス パッケージの割り当て。

アクセス パッケージのドリフトを確認するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **ID ガバナンス**&gt;**Access Guardian**&gt;**Reports**&gt;**Access Drift** に移動します。

    [Access Drift]\(アクセスドリフト\) ページには、タイムスタンプ **の時点のアクセス ドリフト データ** が表示され、ドリフトが検出されたリソースのみが一覧表示されます。 同じリソースが複数のカタログに含まれている場合、リソースはカタログごとに 1 回表示されます。
3. **検出されたドリフト**の概要とリソース テーブルを確認します。

    概要には、ドリフトが検出されたリソースの数が表示されます。 リソース テーブルは、[ **グループ]** タブと [ **アプリケーション]** タブに分かれています。 リソースまたはカタログ名で検索し、カタログでフィルター処理し、[ **リソース**]、[ **合計ドリフト]**、[ **未承認のアクセス]**、[ **アクセスがありません**]、および **[カタログ** ] 列を確認できます。

    基になるリソースを表示するには、[ **グループの表示** ] または **[アプリケーションの表示**] を選択します。
4. 特定のリソースを検索するか、テーブルからリソース名を選択してドリフトの詳細を開きます。
5. ドリフトの詳細ページで、選択したリソースに対してドリフトがあるユーザーを確認します。

    ドリフトの詳細はカテゴリ別にグループ化されます。

    - **不正アクセス**: ガバナンス ポリシーの外部で付与されたアクセス。
    - **不足している割り当て**: アクセス パッケージを通じてアクセスが付与されていますが、リソースには存在しません。
    - **合計ドリフト**: 選択したリソースのすべてのアクセスの不一致。

    詳細テーブルには、ユーザー、アクセス パッケージ、予想されるアクセス パッケージ ロール、現在のMicrosoft Entra ロール、およびドリフト カテゴリが表示されます。 表示名またはロールで検索できます。
6. ドリフトを是正してください。

    承認されていないアクセスを修復するには、 [アクセス パッケージにユーザーを割り当てます](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-an-identity)。 アクセス パッケージ ピッカーは、選択したリソースのカタログにスコープが設定され、アクセス パッケージにリソースが含まれているかどうかを示します。 不足しているアクセスを修復するには、選択したユーザー [のアクセス パッケージの割り当てを再処理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-reprocess-access-package-assignments) します。

    修復アクションを送信すると、変更がリソースに直接適用され、数分かかる場合があります。 ドリフト レポートはスケジュールに従って更新されるため、修復された行は次のレポートが更新されるまで変更されません。
7. アクセス ドリフトの詳細をエクスポートするには、[ **ダウンロード**] を選択します。

ダウンロードできるレポートには、テナント内のすべてのカタログにわたるドリフトが含まれます。 Microsoft Entra 管理センター内のダウンロードのスコープを 1 つのカタログにすることはできません。

現在、レポートは 1 日に 1 回程度更新されるため、データは最大 24 時間経過している可能性があります。 アクセス パッケージの誤差レポートはプレビュー段階であるため、この更新スケジュールは一般公開前に変更される可能性があります。 ページのタイムスタンプの時点のアクセス ドリフト データを参照して、結果の現在の状態を確認します。

アクセス パッケージの誤差レポートには、次の制限があります。

- エンタイトルメント管理とサード パーティ製アプリケーション、Azureロール、またはSharePoint サイトの間の誤差は含まれません。
- ネストされたグループのドリフトは含まれません。 このレポートには、アクセス パッケージの割り当てと、グループに直接割り当てられたユーザーの間の誤差のみが表示されます。

### アプリケーションで孤立したアカウントまたはローカル アカウントを表示する

接続されているアプリケーション (Salesforce、SAP Cloud Identity Services など) の管理者は、アプリケーションにアカウントを手動で作成し、ガバナンス コントロールを回避できます。 アカウント検出機能を使用すると、アプリケーション内のすべてのユーザーのレポートを生成し、一致するMicrosoft Entra アカウントを持つユーザーと、アプリケーションに対してローカルなユーザーを 1 回のクリックで識別できます。 このレポートでは、アカウントがローカル アカウント、未割り当てユーザー、または割り当てられたユーザーとして分類されます。これにより、アンマネージド アクセスとアクセスの誤差を特定するのに役立ちます。 これにより、Microsoft Entraへのオンボードを簡略化しながら、承認されていないアクセスを定期的に監視することもできます。 詳細な手順については、「 [アカウント検出を使用してターゲット アプリケーションの ID を検出する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-reprocess-access-package-assignments"} -->
## エンタイトルメント管理でアクセス パッケージの割り当てを再処理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-reprocess-access-package-assignments
- Service: entra-id-governance / entitlement-management
- Article date: 2024-07-15
- Summary: エンタイトルメント管理でアクセス パッケージの割り当てを再処理する方法について説明します。

アクセス パッケージ マネージャーは、再処理機能を使用して、アクセス パッケージ内のユーザーの元の割り当てを自動的に再評価して適用できます。 再処理によって、ユーザーは、リソースへのアクセスがエンタイトルメント管理外部での変更の影響を受けた場合にアクセス パッケージ要求プロセスを繰り返す必要がなくなります。

たとえば、ユーザーが手動でグループから削除されたために、そのユーザーが必要なリソースにアクセスできなくなる場合があります。

アクセス パッケージのリソースに対する外部の更新はエンタイトルメント管理によってブロックされないため、エンタイトルメント管理 UI ではこの変更は正確に表示されません。 そのため、ユーザーがもうリソースへのアクセス権を持っていなくても、ユーザーの割り当て状態は "配信済み" と表示されます。 ただし、ユーザーの割り当てが再処理された場合は、アクセス パッケージのリソースに再度追加されます。 再処理により、アクセス パッケージの割り当てが最新であること、ユーザーが必要なリソースのアクセス権を持っていること、および割り当てが UI に正確に反映されていることが確認されます。

この記事では、既存のアクセス パッケージ内の割り当てを再処理する方法について説明します。

Note

Microsoft Entra 管理センターでアクセス パッケージの割り当てを再処理するには、サインインしているユーザーが管理センターにアクセスできる必要があります。 Access パッケージの割り当てマネージャーなどのエンタイトルメント管理ロールは、エンタイトルメント管理内で割り当て管理アクションを承認しますが、それ自体では管理センターのテナント全体のアクセス設定を変更しません。 [**Microsoft Entra管理ポータルへのアクセスを制限する**] ユーザー設定が有効になっている場合は、委任されたユーザーが管理センターにアクセスできることを確認するか、承認されたプログラムによる方法を使用します。 この設定の詳細については、「 [既定のユーザーアクセス許可](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions)」を参照してください。

### 前提条件

エンタイトルメント管理を使用し、ユーザーをアクセス パッケージに割り当てるには、次のいずれかのライセンスが必要です。

- Microsoft Entra ID P2 または Microsoft Entra ID ガバナンス
- Enterprise Mobility + Security (EMS) E5 ライセンス

### 既存のアクセス パッケージを開いてユーザー割り当てを再処理する

"配信済み" 状態なのに、アクセス パッケージの一部であるリソースにアクセスできないユーザーがいる場合は、割り当てを再処理して、それらのユーザーをアクセス パッケージのリソースに再割り当てする必要がある可能性があります。 既存のアクセス パッケージの割り当てを再処理するには、次の手順に従ってください。

1. 少なくとも [ID ガバナンス管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者、アクセス パッケージ マネージャー、アクセス パッケージ割り当てマネージャーがあります。
2. **ID ガバナンス**&gt;**権限管理**&gt;**アクセス パッケージ**を参照します。
3. 左側の [アクセス パッケージ] ページで、再処理したいユーザー割り当てが含まれているアクセス パッケージを開きます。
4. 左側の [ **管理** ] の下にある [ **割り当て]** を選択します。

    [Image: Microsoft Entra 管理センターでのエンタイトルメント管理]
5. 再処理したい割り当てを持つユーザーをすべて選択します。
6. [ **再処理**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-reprocess-access-package-requests"} -->
## エンタイトルメント管理でアクセス パッケージの要求を再処理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-reprocess-access-package-requests
- Service: entra-id-governance / entitlement-management
- Article date: 2024-07-15
- Summary: エンタイトルメント管理でアクセス パッケージの要求を再処理する方法について説明します。

アクセス パッケージ マネージャーは、再処理機能を使用して、いつでもユーザーのアクセス パッケージへのアクセスの要求を自動的に再試行できます。 再処理によって、リソースへのアクセスが正常にプロビジョニングされていない場合に、ユーザーがアクセス パッケージ要求プロセスを繰り返す必要がなくなります。

注記

元の要求が完了した時点から最大 14 日間、要求を再処理できます。 14 日より前に完了した要求の場合、ユーザーは、MyAccess でキャンセルして新しい要求を行う必要があります。

この記事では、既存のアクセス パッケージへの要求を再処理する方法について説明します。

### 前提条件

エンタイトルメント管理を使用し、ユーザーをアクセス パッケージに割り当てるには、次のいずれかのライセンスが必要です。

- Microsoft Entra ID P2 または Microsoft Entra ID Governance
- Enterprise Mobility + Security (EMS) E5 ライセンス

### 既存のアクセス パッケージを開いてユーザー要求を再処理する

要求が "一部配信済み" または "失敗" の状態にある一連のユーザーがいる場合は、それらの要求の一部を再処理する必要がある場合があります。 既存のアクセス パッケージへの要求を再処理するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に [Identity Governance 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)以上としてサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者、アクセス パッケージ マネージャー、アクセス パッケージ割り当てマネージャーがあります。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**アクセスパッケージ**に移動します。
3. [アクセス パッケージ] で、\*\* アクセス パッケージを開きます。
4. 左側の **[管理]** の下にある **[要求]** を選択します。
5. 再処理する要求を持つすべてのユーザーを選択します。
6. **[再処理]** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-request-access"} -->
## アクセス パッケージを要求する - エンタイトルメント管理 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-access
- Service: entra-id-governance / entitlement-management
- Article date: 2026-06-17
- Summary: マイ アクセス ポータルを使用して、Microsoft Entra エンタイトルメント管理のアクセス パッケージへのアクセスを要求する方法を学習します。

エンタイトルメント管理では、アクセス パッケージにより、そのアクセス パッケージの有効期間中のアクセスを自動的に管理するリソースとポリシーの 1 回限りのセットアップが可能になります。

アクセス パッケージ マネージャーは、アクセス パッケージにアクセスするにはユーザーの承認を必要とするポリシーを構成できます。 アクセス パッケージへのアクセスが必要なユーザーは、アクセスを取得するための要求を送信できます。 この記事では、アクセス要求を送信する方法について説明します。

### マイ アクセス ポータルにサインインする

最初の手順は、アクセス パッケージへのアクセスを要求できる、マイ アクセス ポータルにサインインすることです。

**事前に必要なロール:** 要求元

1. 連携しているプロジェクト マネージャーまたはビジネス マネージャーからのメールまたはメッセージを探します。 このメールに、使用したいアクセス パッケージへのリンクが記載されています。 リンクは `myaccess` で始まり、ディレクトリ ヒントを含み、アクセス パッケージ ID で終了します。 米国政府の場合、代わりにドメインが `https://myaccess.microsoft.us` になることがあります。

    `https://myaccess.microsoft.com/@<directory_hint>#/access-packages/<access_package_id>`

    注

    ディレクトリ ヒント リンクを使用してマイ アクセスにサインインする場合は、サインイン資格情報を使用して再認証する必要があります。
2. リンクを開きます。
3. マイ アクセス ポータルにサインインします。

    必ず、組織 (職場または学校) のアカウントを使用してください。 わからない場合は、プロジェクトまたはビジネス マネージャーに確認してください。

### アクセス パッケージを要求する

マイ アクセス ポータルでアクセス パッケージを見つけたら、自分または直属の従業員の要求を送信できます。

**事前に必要なロール:** 要求元

1. リストでアクセス パッケージを見つけます。 必要に応じて、検索文字列を入力して検索できます。 名前、説明、またはリソースで検索できます。
2. アクセスを要求するには、行を選ぶか、**[要求]** を選びます。
3. [要求の詳細] ウィンドウで、自分のアクセス パッケージを要求するか、直接の従業員を要求するかを選択します。 [Image: パッケージを要求しているマネージャーのスクリーンショット。]
4. アクセス パッケージの詳細を確認し、**[続行]** を選びます。
5. 質問に回答し、要求を正当化するビジネス上の理由を提供することが必要な場合があります。 回答する必要がある質問がある場合は、各フィールドに回答を入力します。
6. **[業務上の正当な理由]** ボックスが表示された場合は、アクセスを必要とする正当な理由を入力します。
7. **[一定期間の要求ですか?]** トグルを設定して、一定期間のアクセス パッケージへのアクセスを要求します。

    1. 特定の期間にアクセスする必要がない場合は、**[一定期間の要求ですか?]** トグルを **[いいえ]** に設定します。
    2. 特定の期間にアクセスする必要がある場合は、**[一定期間の要求ですか?]** トグルを **[はい]** に設定します。 次に、アクセスの開始日と終了日を指定します。

        [Image: マイ アクセス ポータル - [アクセスの要求]]
8. 完了したら、**[要求の送信]** を選んで要求を送信します。
9. **[要求の履歴]** を選択して、要求と状態の一覧を表示します。

    アクセス パッケージに承認が必要な場合、要求はこの時点で承認待ち状態になります。

#### ポリシーの選択

適用されるポリシーが複数あるアクセス パッケージへのアクセスを要求する場合は、ポリシーの選択を求められることがあります。 たとえば、アクセス パッケージ マネージャーは、従業員の 2 つのグループ用に 2 つのポリシーを使ってアクセス パッケージを構成する場合があります。 最初のポリシーでは、60 日間アクセスを許可し、承認を要求する場合があります。 2 番目のポリシーでは、2 日間アクセスを許可し、承認を要求しない場合があります。 このシナリオが発生した場合は、使用するポリシーを選択する必要があります。

[Image: マイ アクセス ポータル - [アクセスの要求] - 複数のポリシー]

#### 要求元情報を入力する

アクセス パッケージへのアクセスを要求できます。その場合、アクセス パッケージへのアクセスが許可されるためには、その前に、業務上の正当な理由および追加の要求元情報が必要になります。 アクセス パッケージにアクセスするために必要なすべての要求元情報を記入します。

[Image: マイ アクセス ポータル - [アクセスの要求]]

管理者が従業員に代わってアクセスを要求している場合は、従業員に代わって要求元情報セクションにも入力することに注意してください。

[Image: 質問を要求しているマネージャーのスクリーンショット。]

このプロセスの詳細については、「[他のユーザーに代わってアクセス パッケージを要求する (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-behalf)」を参照してください。

注

追加の要求元情報の一部に、値が事前に設定されている場合があります。 これは通常、前の要求または他のプロセスから、アカウントに属性情報が既に設定されている場合に発生します。 これらの値は、選択したポリシーの設定に応じて、編集可能である場合とそうでない場合があります。

### 要求を再送信する

アクセス パッケージへのアクセスを要求すると、要求が拒否されたり、承認者が時間内に応答しない場合は、要求が期限切れになったりすることがあります。 アクセスが必要な場合は、再試行して、要求を再送信することができます。 次の手順では、アクセス要求を再送信する方法について説明します。

**前提となるロール:** 依頼者

1. **マイ アクセス** ポータルにサインインします。
2. 左側のナビゲーション メニューから **[要求の履歴]** を選択します。
3. 再送信している要求の対象のアクセス パッケージを検索します。
4. チェック マークを選択して、アクセス パッケージを選択します。
5. 選んだアクセス パッケージの青い **[表示]** リンクを選びます。

    [Image: アクセス パッケージと [表示] リンクを選択します]

    ペインが開き、アクセス パッケージの要求の履歴が表示されます。

    [Image: [再送信] ボタンを選択します]
6. ペインの下部にある **[再送信]** ボタンを選択します。

### 要求を取り消す

アクセス要求を送信し、要求がまだ**承認待ち**状態の場合、要求を取り消すことができます。

**前提ロール:** 依頼者

1. マイ アクセス ポータルの **[要求の履歴]** を選んで、要求と状態の一覧を表示します。
2. 取り消したい要求の **[表示]** リンクを選択します。
3. 要求が**承認待ち**状態のままになっている場合は、**[要求の取り消し]** を選択して要求を取り消すことができます。

    [Image: マイ アクセス ポータル - [要求の取り消し]]
4. **[要求の履歴]** を選択して、要求が取り消されたことを確認します。

### 保留中の要求の承認者情報を表示する

承認者の詳細を表示するようにアクセス パッケージが構成されている場合は、保留中の要求に対して承認者が誰であるかを表示できます。

**事前に必要なロール:** 要求元

1. マイ アクセス ポータルの **[要求の履歴]** を選んで、要求と状態の一覧を表示します。
2. 承認待ちの要求の **[表示]** リンクを選択します。
3. 要求の詳細パネルで、承認待ちの状態から**詳細**を選択します。 アクセス パッケージ ポリシーで許可されている場合は、承認者情報が表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-request-approve"} -->
## アクセス要求を承認または拒否する - エンタイトルメント管理 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-approve
- Service: entra-id-governance / entitlement-management
- Article date: 2025-06-18
- Summary: マイ アクセス ポータルを使用して、Microsoft Entraエンタイトルメント管理でアクセス パッケージへの要求を承認または拒否する方法について説明します。

エンタイトルメント管理では、アクセス パッケージの承認を要求し、1 人または複数の承認者を選択するようにポリシーを構成することができます。 この記事では、指定された承認者がアクセス パッケージの要求をどのように承認または拒否できるかについて説明します。

### 要求を開く

アクセス要求を承認または拒否する最初の手順は、承認待ちのアクセス要求を見つけて開くことです。 アクセス要求を開く方法は 2 つあります。

**事前に必要なロール:** 承認者

1. 要求の承認または拒否を求める電子メールをMicrosoft Azureから探します。 電子メールの例を次に示します。

    [Image: アクセス パッケージに対する要求を承認するよう求めるメール]
2. **[Approve or deny request](https://learn.microsoft.com/ja-jp/entra/id-governance/要求を承認または拒否する)** リンクを選択して、アクセス要求を開きます。
3. マイ アクセス ポータルにサインインします。

メールが届いていない場合は、これらの手順に従って、承認待ちのアクセス要求を見つけることができます。

1. https://myaccess.microsoft.com でマイ アクセス ポータルにサインインします。 米国政府の場合、マイ アクセス ポータルのリンクのドメインは `myaccess.microsoft.us` になります。
2. 左側のニューで、**[承認]** を選択して、承認待ちのアクセス要求のリストを表示します。
3. **[保留中]** タブで、要求を見つけます。

手記

要求を承認するメールが表示されない場合は、アクセス パッケージに通知が無効になっていないことを確認してください。

### 質問に対する要求元の回答を表示する

1. マイ アクセスの **[承認]** タブに移動します。
2. 承認する要求に移動し、**[詳細]** を選択します。 決定する準備ができている場合は、**[承認]** または **[拒否]** を選択することもできます。
3. **[要求の詳細]** を選択します。

    [Image: マイ アクセス ポータル - アクセス要求 - [要求の詳細] のクリック]
4. 要求の **詳細** ページには、要求を行ったユーザーや、自分用か他のユーザー用かなど、要求に関する基本情報が表示されます。 [他の ID に対するアクセスの要求の詳細については、他の ID に代わってアクセス パッケージを要求する (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-behalf) を参照してください。
5. 要求元が指定した情報は、パネルの下部に表示されます。

    [Image: 要求の詳細を示すスクリーンショット]
6. 要求元が指定した情報に基づいて、要求を承認または拒否することができます。 ガイダンスについては、「要求を承認または拒否する」の手順を参照してください。

### 要求を承認または拒否する

承認待ちのアクセス要求を開いた後、承認または拒否の決定に役立つ詳細を確認することができます。

**事前に必要なロール:** 承認者

1. **[表示]** リンクを選択して、[アクセス要求] ウィンドウを開きます。
2. **[詳細]** を選択して、アクセス要求の詳細を確認します。

    詳細には、ID の名前、組織、アクセスの開始日と終了日 (指定されている場合)、業務上の正当な理由、要求が送信された日時、および要求の有効期限が含まれます。
3. **[承認]** または **[拒否]** を選びます。
4. 必要に応じて、理由を入力します。

    [Image: 要求を承認または拒否するページを示すスクリーンショット。]
5. **[送信]** を選択して、決定を送信します。

    ポリシーがステージ内の複数の承認者によって構成されている場合は、1 人の承認者のみが承認待ちに関する決定を行う必要があります。 承認者がアクセス要求に対する決定を送信した後、要求は完了し、他の承認者は要求を承認することも拒否することもできなくなります。 他の承認者は、マイ アクセス ポータルで要求に関する決定と決定者を確認することができます。

    ステージで構成されたステージ内の承認者がアクセス要求を承認または拒否できない場合、要求は、構成された要求期間の後に期限切れになります。 ユーザーには、アクセス要求の有効期限が切れたことと、アクセス要求を再送信する必要があることが通知されます。

### 要求を取り消す

Microsoft Entra ID ガバナンスを持つユーザーは、以前に承認したアクセス要求の承認を元に戻すことができます。 これにより承認が取り消され、要求者はアクセス パッケージにアクセスできなくなります。

**必要なロール:** Microsoft Entra ID ガバナンス ライセンスを持つ承認者

1. [マイ アクセス] で、[**承認]**&gt;**[履歴]**を選択します。
2. 決定を取り消す承認済みの要求を選択します。
3. [ **削除]** を選択して、アクセス パッケージへの ID のアクセス権を削除します。 決定を取り消す理由を含めます。
4. 「**を選択」し、「** を削除」して決定を送信します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-request-behalf"} -->
## 他のIDに代わってアクセスパッケージを要求する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-behalf
- Service: entra-id-governance / entitlement-management
- Article date: 2025-06-18
- Summary: この記事では、マネージャーが自分が所有する ID、スポンサー、または報告している ID の要求を承認または拒否できるように、access パッケージを設定する方法について説明します。

エンタイトルメント管理を使用すると、管理者はaccess パッケージを作成して組織のリソースを管理できます。 管理者は、access パッケージに ID を直接割り当てるか、ユーザーとグループ メンバーがaccessを要求できるようにするaccess パッケージ ポリシーを構成できます。 セルフサービス プロセスを作成するオプションは、特に組織がより多くの従業員をスケーリングして雇用する場合に便利です。 ただし、組織に参加する新しい従業員は、自分が何にアクセスする必要があるのか、またそのアクセスをどのように要求できるのかを常に把握しているとは限りません。 この場合、新しい従業員はマネージャーに頼って、アクセスリクエストのプロセスを案内してもらうことになります。 マネージャーは、新しい従業員が要求プロセスを通過することなく、従業員のためにアクセスパッケージを要求できるため、オンボーディングをより迅速かつシームレスにすることができます。 管理者がこの機能を有効にするには、管理者が従業員に代わってaccessを要求できるaccess パッケージ ポリシーを設定するときにオプションを選択できます。

セルフサービス要求フローを拡張して、アイデンティティに代わって要求を許可することで、ユーザーが必要なリソースにタイムリーにアクセスできるようになり、生産性が向上します。

### 従業員に代わって要求するマネージャー向けのシナリオ

組織が毎年何百人もの新入社員を雇用しており、My Access のリソースのaccessを要求する方法など、IT プロセスに関する新入社員のトレーニングを任されているとします。 トレーニング セッションは毎月初めだけなので、月の後半に入社する新入社員のマネージャーは、しばしば臨時のトレーニングを利用します。 これはますます一般的になっています。

新入社員が組織の最初の週または数週間にaccessを要求する方法を確実に把握できるように、多数のアドホック トレーニング セッションを実施する代わりに、マネージャーが従業員に代わってaccessを要求できるようにするaccessパッケージ ポリシーを設定できます。

マネージャーは、IT トレーニングを受けていない新入社員に代わってaccessを要求できるようになりました。 これにより、従業員は 1 日目に開始するために必要なツールとリソースを確保でき、accessを待ったり、自分で要求プロセスを移動したりする必要がないため、新入社員の満足度が向上します。

### 他のユーザーに代わって要求するユーザーのシナリオ

従業員、請負業者、または外部コラボレーターを含むプロジェクトをリードしているとします。 各ユーザーが必要とするリソースは既にわかっていますが、一部の参加者はマイ アクセスや組織のアクセス要求プロセスに慣れていない可能性があります。 すべてのユーザーに自分の要求を送信するように依頼すると、特にプロジェクトを迅速に開始する必要がある場合に、混乱や遅延が発生する可能性があります。 代わりに、組織は、プロジェクト リーダー、チーム コーディネーター、ヘルプ デスク担当者などの指定されたユーザーが他のユーザーに代わってアクセスを要求できるようにするアクセス パッケージ ポリシーを設定できます。 これにより、アクセスが必要なユーザーを選択し、適切なアクセス パッケージを選択し、必要な要求の詳細を指定できます。 要求は引き続きポリシーで定義されている承認およびアクセス ライフサイクル プロセスに従い、組織が監視を維持しながらユーザーのエクスペリエンスを容易にするのに役立ちます

### エージェント ID に代わって要求するシナリオ

管理者が自分またはスポンサーのエージェント ID に代わって要求できることも、他の ID に代わって access パッケージを要求するためのもう 1 つの重要なシナリオです。 エージェント ID 用のアクセス パッケージを要求できる機能により、環境内であなたに代わって作業するエージェントが、業務に必要なアクセス権を持っていることを確認できますが、それ以上のアクセスは制限されます。 エージェントの管理の詳細については、[Microsoft Entra のエージェント管理](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agent) を参照してください。

### 前提条件

組織のユーザーがこの機能を使うには、Microsoft Entra ID ガバナンス または Microsoft Entra スイート のサブスクリプションが必要です。 この機能に含まれる一部の機能は、Microsoft Entra ID P2 サブスクリプションで動作する場合があります。 詳細については、各機能の記事を参照してください。 要件に適したライセンスを見つけるには、[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を参照してください。

Note

代理要求機能を使用するには、マネージャー/ユーザー (リクエスタ) と従業員/ユーザー (ターゲット) の両方にMicrosoft Entra ID ガバナンスまたはMicrosoft Entra スイートのライセンスが必要です。

#### エージェント ID に代わって要求するためのライセンス要件 (プレビュー)

エージェント ID [にMicrosoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を使用するには、次のいずれかのライセンス プランが必要です。

- **Microsoft 365 E7** (エージェント 365 とMicrosoft Entra スイートを含む) は、ユーザー ID とエージェント ID のガバナンスを提供します。
- **Microsoftエージェント 365** ライセンスは、少なくとも Microsoft Entra P1 または Microsoft 365 E3 とペアリングされています。

詳細については、「[Microsoft Agent 365 のプランと価格](https://www.microsoft.com/microsoft-agent-365#plans-and-pricing)」を参照してください。 エージェント固有の機能の完全な一覧については、**Microsoft Entra ID ガバナンス ライセンス表**の [Microsoft Agent 365](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals) 列を参照してください。

### 要求に代わって許可するアクセス パッケージ ポリシーを構成する

既存のaccess パッケージに対して要求に代わって許可するポリシーを編集するには、次の手順に従います。

1. 少なくとも [Identity Governance Administrator](https://entra.microsoft.com) として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) にサインインします。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**アクセス パッケージ** に移動します。
3. 要求に対してセットアップするアクセスパッケージを選択します。
4. 編集するポリシーを選択するか、新しいポリシーを作成します。
5. [ **要求** ] タブの [ **アクセスを要求できる** ユーザー] セクションで、このアクセス パッケージを要求する資格のあるユーザーを選択します。 **マネージャー**を選択すると、マネージャーは従業員に代わって要求を行うことができます。 **ディレクトリで [ユーザー**] を選択すると、そのユーザーがディレクトリ内にいる限り、組織内の他のユーザーに代わって要求を行うことができます。[Image: ポリシーに代わってアクセス パッケージの要求を編集するスクリーンショット。]
6. ポリシーを保存します。

### 従業員に代わってaccess パッケージを要求する

マネージャーは、次の手順を実行して、直接レポートのaccess パッケージを要求できます。

1. https://myaccess.microsoft.com でマイ Access ポータルにサインインします。 米国政府機関の場合、My Access ポータルリンクのドメインは `myaccess.microsoft.us` です。
2. [マイ Access ポータル] ページで、**Access パッケージ**を選択します。
3. [Access パッケージ] ページで、直接レポートを要求するaccess パッケージを見つけて、**Request** を選択します。
4. [要求] ウィンドウの [ **要求の詳細**] で、[ **他のユーザー**への要求] を選択します。 [Image: 直属の従業員用のアクセスパッケージを要求するマネージャーのスクリーンショット]
5. 直接レポートのaccess パッケージを要求するために必要な追加情報を入力します。 [Image: 直属の部下のためのアクセスパッケージを要求する際の正当化質問のスクリーンショット]
6. **[要求の送信]** を選択します。

### マネージャーが従業員に代わって要求したアクセスを承認する

マネージャーとして従業員に代わってaccess パッケージを承認するには、次の手順に従ってaccessを承認します。

1. https://myaccess.microsoft.com でマイ Access ポータルにサインインします。 米国政府機関の場合、My Access ポータルリンクのドメインは `myaccess.microsoft.us` です。
2. 左側のメニューで **Approvals** を選択して、承認待ちのaccess要求の一覧を表示します。
3. **[保留中]** タブで、要求を見つけます。 [Image: 私のアクセスで保留中の承認リクエストのスクリーンショット]
4. 従業員に代わっての要求を承認または拒否します。

### マイ Access ポータルを使用してチームの割り当てを管理する

管理者が機能を有効にした場合、要求を代行するポリシーをサポートするアクセス パッケージの割り当てを、個人用のマイ アクセス ポータルを使用して直属の部下のアクセス パッケージの割り当てを管理することもできます。 管理機能は次のとおりです。

- すべての直属の部下のアクティブなアクセスパッケージの割り当てを表示する機能。
- ポリシーが要求に代わってサポートしている場合に、レポートの割り当てを削除する機能。

Note

このマイ アクセス エクスペリエンスは、アクセス パッケージ ポリシーとマイ アクセス設定で代理要求がサポートされている場合に、直属のレポートのアクセス パッケージの割り当てを管理するマネージャー向けです。 アクセス パッケージ割り当てマネージャーなどの、委任されたエンタイトルメント管理ロールに関する管理者による割り当て管理タスクは、Microsoft Entra 管理センターまたは承認されたプログラムによる方法を使用して実行されます。 マイ Access ポータルでチームを管理する前に、次の手順を実行してチームの管理設定が構成されていることを確認します。

1. 少なくとも [Identity Governance Administrator](https://entra.microsoft.com) として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) にサインインします。
    ヒント

    このタスクを完了できるその他の最小限の特権ロールには、カタログ所有者とAccess package managerが含まれます。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**制御構成**を参照します。
3. [コントロールの構成] ページで、[エンドユーザー向けのマイアクセス設定] カードで **view settings** を選択します。 エンドユーザーカードのアクセス設定のスクリーンショット
4. エンドユーザー設定ページで、**[直属部下のアクセス パッケージ割り当てを表示 (プレビュー)]** にチェックマークが入っていることを確認してください。 [Image: 私のアクセスを使用するエンドユーザー向けの設定のスクリーンショット。]
5. **保存** を選択します。 この設定を有効にした状態で、次の手順を実行して、マイ Access ポータルを使用してチームの割り当てを管理します。
6. access パッケージの割り当てを管理するチームの直接マネージャーとして、https://myaccess.microsoft.com のマイ Access ポータルにサインインします。 米国政府機関の場合、My Access ポータルリンクのドメインは `myaccess.microsoft.us` です。
7. 左側のメニューで [ **チームの管理** ] を選択すると、直属の部下の一覧が表示されます。 [Image: [チームの管理] ページのチーム メンバーの一覧のスクリーンショット。]
8. 従業員を選択して、割り当ての一覧を表示します。
9. [割り当て] ページには、現在のアクセスパッケージ割り当ての一覧が表示されます。 **Remove access** を選択して、ユーザーに対する特定のaccess パッケージの割り当てを終了することもできます。 [Image: 私のアクセスポータルでのチーム管理のスクリーンショット。]

### エージェント ID に代わってアクセス パッケージを要求する

エージェント ID の所有者またはスポンサーは、次の手順を実行して、そのエージェント ID のaccess パッケージを要求できます。

1. https://myaccess.microsoft.com でマイ Access ポータルにサインインします。
2. [マイ Access ポータル] ページで、**Access パッケージ**を選択します。
3. [Access パッケージ] ページで、エージェント ID を要求するaccess パッケージを見つけて、**Request** を選択します。
4. [Request]\(要求\) ウィンドウの [ **Request details**]\(要求の詳細\) で、[ **Requesting for Sponsored agent]\(スポンサー付きエージェントの要求** \) または **[所有しているエージェントの要求**] を選択します。
5. エージェント ID を選択し、[ **続行**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-roles"} -->
## Microsoft Entra ロールを割り当てる - エンタイトルメント管理 (プレビュー) - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-roles
- Service: entra-id-governance / entitlement-management
- Article date: 2025-06-27
- Summary: アクセス パッケージを使用してMicrosoft Entraロールを割り当てる方法について説明します。

エンタイトルメント管理では、アプリケーション、SharePoint サイト、グループ、Teams など、さまざまなリソースの種類のアクセス ライフサイクルがサポートされます。 ID には、特定の方法でこれらのリソースを利用するための追加のアクセス許可が必要な場合があります。 たとえば、ID は組織のPower BI ダッシュボードにアクセスできる必要がありますが、組織全体のメトリックを表示するにはPower BI管理者ロールが必要な場合があります。 ロール割り当て可能なグループなど、他のMicrosoft Entra ID機能では、これらのMicrosoft Entraロールの割り当てがサポートされる場合がありますが、これらのメソッドを介して付与されるアクセスはあまり明示的ではありません。 たとえば、ID のロールの割り当てを直接管理するのではなく、グループのメンバーシップを管理します。

従業員とゲストにMicrosoft Entraロールを割り当てることで、エンタイトルメント管理を使用して ID の権利を調べて、その ID に割り当てられているロールをすばやく特定できます。 アクセス パッケージにリソースとしてMicrosoft Entra ロールを含める場合は、そのロールの割り当てが "*割り当て可能*" か "*active*" かを指定することもできます。

アクセス パッケージとカタログを使用してMicrosoft Entraロールを割り当てると、ロールの割り当てを大規模に効率的に管理し、ロールの割り当てのライフサイクルを向上させることができます。

注

セキュリティ強化に向けた継続的な取り組みの一環として、エンタイトルメント管理アクセス パッケージのMicrosoft Entraロールのプレビュー機能が進化しています。 今後、エンタイトルメント管理では、アクセス パッケージに特権アクセス許可のないMicrosoft Entraロールのみを含めることができます。 特権アクセス許可を持つロールは、**Privileged Identity Management** を使用して管理する必要があります。 特権組み込みロールの詳細については、 [ロールリファレンスを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#all-roles)。 特権ロールは、参照内で **特権** としてラベル付けされます。

注

Microsoft Entra ロールをカタログに割り当てると、そのアクセス制御とガバナンス制御が変更される可能性があります。

### アクセス パッケージを使用した Microsoft Entra のロール割り当てシナリオ

組織が最近、サポート チームに 50 人の新入社員を雇用し、あなたは、これらの新入社員に必要なリソースへのアクセス権を付与する必要があるとします。 これらの従業員は、サポート グループと特定のサポート関連アプリケーションにアクセスする必要があります。 彼らが仕事をするためには、*Helpdesk Administrator* ロールを含む 3 つの Microsoft Entra ロールが必要です。 50 人の各従業員をすべてのリソースとロールに個別に割り当てる代わりに、SharePoint サイト、グループ、および特定のMicrosoft Entraロールを含むアクセス パッケージを設定できます。 その後、マネージャーを承認者にするようにアクセス パッケージを構成し、リンクをサポート チームと共有できます。

[Image: 新しいアクセス パッケージにリソース ロールを追加するスクリーンショット。]

これで、サポート チームに参加する新しいメンバーは、[マイ アクセス] でこのアクセス パッケージへのアクセスを要求し、マネージャーが要求を承認するとすぐに必要なすべてにアクセスできるようになります。 サポート チームはグローバルに拡大し、最大 1,000 人の新入社員を雇用することを計画していますが、それぞれをアクセス パッケージに手動で割り当てる必要がなくなったため、これにより時間とエネルギーを節約できます。

#### PIM アクセスに関する注意事項:

注

Privileged Identity Managementを使用して、昇格されたアクセス許可を必要とするタスクを実行するユーザーに Just-In-Time アクセスを提供することをお勧めします。 これらのアクセス許可は、[Microsoft Entra組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)のドキュメントで"特権" としてタグ付けされたMicrosoft Entraロールを通じて提供されます。 エンタイトルメント管理は、ユーザーにリソースのバンドルを割り当てるのに適しています。リソースのバンドルには、Microsoft Entraロールを含めることができます。このロールは、ジョブを実行するために必要です。 アクセス パッケージに割り当てられているユーザーは、より長期間リソースにアクセスできる傾向があります。 Privileged Identity Managementを使用して高い特権を持つロールを管理することをお勧めしますが、エンタイトルメント管理のアクセス パッケージを使用して、これらのロールの資格を設定できます。

### 前提条件

この機能を使用するには、Microsoft Entra ID ガバナンスまたはMicrosoft Entra スイートライセンスが必要です。 要件に適したライセンスを見つけるには、[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を参照してください。

注

Microsoft Entraロールをカタログに追加するには、グローバル管理者またはカタログ所有者アクセス許可を持つ特権ロール管理者である必要があります。 Microsoft Entra ロールがカタログに追加されると、Identity Governance Administrators および Access Package Manager は、そのMicrosoft Entra ロールを含むアクセス パッケージを作成できます。アクセス パッケージを管理するアクセス許可を持つ他のユーザーは、そのMicrosoft Entraロールにユーザーを割り当てることができます。 同様に、エンタイトルメント管理に必要なアクセス許可を備えるグローバル管理者または特権ロール管理者のロールを持たない限り、EntitlementManagement.RW.All のアクセス許可を持つアプリケーションは、カタログに Microsoft Entra ロールを追加できません。

### Microsoft Entra ロールをリソースとしてアクセス パッケージに追加する

以下の手順に従って、既存のアクセス パッケージとは互換性のないグループまたはその他のアクセス パッケージの一覧を変更します。

1. カタログ所有者のアクセス許可を持つ [Microsoft Entra 管理センター](https://entra.microsoft.com)[Global Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) または [Privileged Role Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) としてサインインします。
2. **ID ガバナンス**&gt;**権利管理**&gt;**アクセスパッケージ**を参照します。
3. [アクセス パッケージ] ページで、リソース ロールを追加するアクセス パッケージを開き、[ **リソース ロール**] を選択します。
4. **アクセス パッケージへのリソース ロールの追加ページ**で、**Microsoft Entra ロール (プレビュー)** を選択して、[Microsoft Entra ロールの選択] ウィンドウを開きます。
5. アクセス パッケージに含めるMicrosoft Entraロールを選択します。 [Image: アクセス パッケージのロールの選択のスクリーンショット。]
6. [ **ロール** ] ボックスの一覧で、[ **資格のあるメンバー** ] または [ **アクティブ なメンバー**] を選択します。 [Image: アクセス パッケージでリソースの役割を選択するスクリーンショット。]
7. **追加** を選択します。

注

**Eligible** を選択すると、ユーザーはそのロールの対象となり、Microsoft Entra 管理センターのPrivileged Identity Managementを使用して割り当てをアクティブ化できます。 **[アクティブ]** を選択した場合、ユーザーはアクセス パッケージにアクセスできなくなるまでアクティブなロールの割り当てを受け取ります。 *"privileged"* としてタグ付けされたMicrosoft Entraロールの場合は、**Eligible** のみを選択できます。 特権ロールの一覧については、[Microsoft Entra組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)を参照してください。

### Microsoft Entra ロールをリソースとしてアクセス パッケージにプログラムで追加する

Microsoft Graphを使用して、Microsoft Entra ロールをリソース ロールとしてアクセス パッケージに追加できます。 委任されたアクセス許可を使用する場合、カタログにロールを追加するユーザーは、カタログ所有者アクセス許可を持つグローバル管理者または特権ロール管理者である必要があります。 `Entitlement Management.ReadWrite.All` アクセス許可を持つアプリケーションは、グローバル管理者もしくは特権ロール管理者のアクセス許可を持っていない限り、カタログにMicrosoft Entraのロールを追加できません。

注

これらの操作を実行するには、委任された `EntitlementManagement.ReadWrite.All` アクセス許可では不十分です。

#### Graph を使用してアクセス パッケージにリソースとしてMicrosoft Entra ロールを追加する

まず、[Create accessPackageResourceRequest](https://learn.microsoft.com/ja-jp/graph/api/entitlementmanagement-post-resourcerequests?tabs=http) を呼び出して、Microsoft Entra ロールをリソースとしてカタログに追加します。

次に、そのMicrosoft Entraロールをリソース ロールとしてアクセス パッケージに追加するには、[Create resourceRoleScope](https://learn.microsoft.com/ja-jp/graph/api/accesspackage-post-resourcerolescopes?tabs=http) に次のペイロードを使用します。

```json
{
    "role": {
        "originId": "Eligible",
        "displayName": "Eligible Member",
        "originSystem": "DirectoryRole",
        "resource": {
            "id": "ea036095-57a6-4c90-a640-013edf151eb1"
        }
    },
    "scope": {
        "description": "Root Scope",
        "displayName": "Root",
        "isRootScope": true,
        "originSystem": "DirectoryRole",
        "originId": "c4e39bd9-1100-46d3-8c65-fb160da0071f"
    }
}
```

#### PowerShell を使用してアクセス パッケージにリソースとしてMicrosoft Entra ロールを追加する

また、Microsoft Entra ロールを、[Microsoft Graph Identity Governance 用 PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph.Identity.Governance/2.15.0) モジュール バージョン 1.16.0 以降のコマンドレットを使用して、PowerShell のアクセス パッケージのリソース ロールとして追加することもできます。

次のスクリプトは、Microsoft Entra ロールをリソース ロールとしてアクセス パッケージに追加する方法を示しています。 これは、カタログ内にリソースとしてMicrosoft Entraロールがあることを前提としています。

まず、アクセス パッケージに含めるカタログの ID、そのカタログ内のリソース、およびそのスコープとロールの ID を取得します。 次の例のようなスクリプトを使用します。

```powershell
Connect-MgGraph -Scopes "EntitlementManagement.ReadWrite.All"

$catalog = Get-MgEntitlementManagementCatalog -Filter "displayName eq 'Entra Admins'" -All
if ($catalog -eq $null) { throw "catalog not found" }
$rsc = Get-MgEntitlementManagementCatalogResource -AccessPackageCatalogId $catalog.id -Filter "originSystem eq 'DirectoryRole'" -ExpandProperty scopes
if ($rsc -eq $null) { throw "resource not found" }
$filt = "(id eq '" + $rsc.Id + "')"
$rrs = Get-MgEntitlementManagementCatalogResource -AccessPackageCatalogId $catalog.id -Filter $filt -ExpandProperty roles,scopes
```

次に、そのリソースからアクセス パッケージにMicrosoft Entra ロールを割り当てます。 たとえば、先ほど返されたリソースの 1 番目のリソース ロールをアクセス パッケージのリソース ロールとして含める場合は、次のようなスクリプトを使います。

```powershell
$apid = "00001111-aaaa-2222-bbbb-3333cccc4444"

$rparams = @{
    role = @{
        id =  $rrs.Roles[0].Id
        displayName =  $rrs.Roles[0].DisplayName
        description =  $rrs.Roles[0].Description
        originSystem =  $rrs.Roles[0].OriginSystem
        originId =  $rrs.Roles[0].OriginId
        resource = @{
            id = $rrs.Id
            originId = $rrs.OriginId
            originSystem = $rrs.OriginSystem
        }
    }
    scope = @{
        id = $rsc.Scopes[0].Id
        originId = $rsc.Scopes[0].OriginId
        originSystem = $rsc.Scopes[0].OriginSystem
    }
}

New-MgEntitlementManagementAccessPackageResourceRoleScope -AccessPackageId $apid -BodyParameter $rparams
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-sap-integration"} -->
## Microsoft Entra SAP IAG 統合 (プレビュー) - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-sap-integration
- Service: entra-id-governance / entitlement-management
- Article date: 2026-03-25
- Summary: SAP Identity Access Governance (IAG) とMicrosoft Entraを統合してアクセス管理を効率化する方法について説明します。

Microsoft Entra ID ガバナンス SAP Identity Access Governance (IAG) と統合され、両方のプラットフォームでユーザー アクセスを管理するのに役立ちます。 この統合により、Microsoft Entraアクセス パッケージに SAP ビジネス ロールを含め、プロビジョニング プロセスを合理化し、統合されたアクセス管理エクスペリエンスを提供できます。

この統合により、次のことが可能になります。

- SAP IAG ビジネス ロールをエンタイトルメント管理カタログのリソースとして追加する
- Microsoft Entra アクセス パッケージを使用して SAP アプリケーションへのアクセス権をユーザーに付与する
- Microsoft Entraの承認に基づいて SAP IAG でのロールの割り当てを自動化する
- Microsoft環境と SAP 環境の両方で一貫したアクセス ガバナンス ポリシーを維持する

この記事では、SAP IAG インスタンスをMicrosoft Entraに接続し、両方のプラットフォームで必要な前提条件を構成し、SAP ビジネス ロールを含むアクセス パッケージを作成する方法について説明します。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID ガバナンスまたはMicrosoft Entra スイートライセンスが必要です。 要件に適したライセンスを見つけるには、[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を参照してください。

### [前提条件]

Microsoft Entraエンタイトルメント管理と SAP IAG の統合を利用するには、組織の SAP デプロイが次の前提条件を満たしている必要があります。

Microsoft Entraに既に統合されている SAP Cloud Identity Services インスタンス:

- ユーザー プロビジョニング: Microsoft Entra ID SAP Cloud Identity Services を構成する」を参照してください>
- 属性マッピングで、MICROSOFT ENTRA ObjectId を同期して SAP グローバル ユーザー ID としてマップする新しいマッピングを追加します。 これは、2 つのシステム間で正しいオブジェクト参照を許可するために必要です。
- [ *新しいマッピングの追加]* をクリックし、属性マッピングを次のように設定します。

    - ソース属性: objectId
    - ターゲット属性: urn:ietf:params:scim:schemas:extension:sap:2.0:User:userUuid

    [Image: SAP 統合のマネージャー属性の設定のスクリーンショット。]
- マネージャー属性のマッピングを追加します。 これにより、マネージャー情報をMicrosoft Entraから SAP Cloud Identity Services に同期できます。

    - [ *新しいマッピングの追加]*をクリックし、属性マッピングを次のように設定します。
        - ソース属性: マネージャー
        - ターゲット属性: urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager
- 前提条件が完了すると、属性マッピングは次のようになります。

[Image: SAP 統合の属性マッピングのスクリーンショット。]

- ユーザー シングル サインオン (省略可能): [Microsoft Entra ID でのシングル サインオン用の SAP Cloud Identity Services の構成を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-identity-authentication-tutorial)
- 既存の SAP IAG ビジネス ロール。

SAP Cloud Identity and Access Governance (IAG) テナント ライセンス:

- SAP BTP 管理者は、Microsoft Entraユーザー アカウントを `IAG_SUPER_ADMIN` グループに追加し、SAP BTP で `CIAG_Super_Admin` ロールを持ち、エンタイトルメント管理で SAP IAG アクセス権をリソースとして追加する必要があります。
- コネクタとアクセス パッケージを構成するMicrosoft Entra ユーザー アカウントは、SAP Cloud Identity Services (IAS) と SAP IAG に同期する必要があります。
    - Microsoft Entra ユーザーを SAP Cloud Identity Services にプロビジョニングした後、必ず SAP IAG で "*Repository Sync*" と "*SCI ユーザー グループ同期ジョブ*" を実行してください。

また、SAP IAG と対話するためのMicrosoft Entraの資格情報を格納するために、Azure Key Vaultを含むAzure サブスクリプションも必要です。

### Microsoft Entraに接続するために SAP Identity Access Governance インスタンスを準備する

Microsoft Entraエンタイトルメント管理を SAP Cloud Identity Access Governance (IAG) と統合する前に、Microsoft Entraと SAP IAG に同じユーザー ID のリストがあることを確認します。 エンタイトルメント管理が SAP IAG に送信するアクセス要求のユーザーを参照すると、SAP IAG はそのユーザーを認識できます。 ユーザーリストを同期するには、Microsoft Entraを接続して SAP Cloud Identity Services にユーザーをプロビジョニングしてから、SAP Cloud Identity Services を SAP IAG に接続します。

[Image: この記事で説明する統合におけるMicrosoft Entra、Azure Key Vault、SAP Cloud Identity Services と SAP IAG の関係のダイアグラム。]

ユーザーをMicrosoft Entraから SAP Cloud Identity Services にプロビジョニングした後、SAP Cloud Identity Services のユーザー グループと属性データを [SAP Cloud Identity Access Governance (IAG)](https://help.sap.com/docs/SAP_CLOUD_IDENTITY_ACCESS_GOVERNANCE?state=DRAFT) に同期するには、次の手順を実行します。

#### 1. IAG 同期システム管理者を登録する

1. 試用版を使用している場合は、SAP Cloud Identity Services 管理コンソール、 `https://<tenantID>.accounts.ondemand.com/admin,` 、または `https://<tenantID>.trial-accounts.ondemand.com/admin` にサインインします。 **[Users & Authorizations](https://learn.microsoft.com/ja-jp/entra/id-governance/ユーザーと承認) &gt; [Administrators](https://learn.microsoft.com/ja-jp/entra/id-governance/管理者)** に移動します。
2. 新しい管理者を一覧に追加するには、左側パネルの **[+ 追加]** ボタンを押します。 **[システムの追加]** を選択し、システムの名前を入力します。
3. **IAG 同期**などの名前を付け、プロビジョニング ロールを割り当てます (ユーザーの管理、グループの管理、プロキシ システム API、Real-Time プロビジョニング API、Identity Provisioning Tenant Admin API)
4. [ **システム認証 &gt; 証明書の構成**] で、証明書を生成して保存します。 ダウンロードした証明書 p12 ファイルと証明書のパスワードを保持することも、.p12 証明書をアップロードすることもできます。 [Image: SAP での p12 証明書のアップロードのスクリーンショット。]

#### 2. BTP HTTP 宛先を作成する

1. SAP BTP サブアカウントで、[接続 &gt; 宛先証明書] に移動 &gt; 生成方法 'インポート' を作成して使用します。 IAG 同期システム管理者の登録手順で生成された p12 証明書ファイルをアップロードします。
2. SAP BTP サブアカウントで、[接続] &gt; [接続先] &gt; [新しい接続先] &gt; [最初から] に移動します。
3. [名前] を SAP\_Identity\_Services\_Identity\_Directory に設定し、[種類] を HTTP に、[URL] を https://&lt;SCI\_TENANT\_ID&gt;.accounts.ondemand.com に、[プロキシの種類] を Internet に、[認証] を ClientCertificate に設定します。
4. IAG 同期システム管理者の登録手順で作成およびアップロードされた証明書を使用して認証を構成します。 [ストア ソース] を [DestinationService] に設定し、キー ストアの場所でドロップダウン リストから証明書を選択し、[宛先証明書] にアップロードした証明書と同じにする必要があります
5. 以下のプロパティを追加します。
    - Accept = application/scim+json
    - GROUPSURL = /Groups
    - USERSURL = /Users
    - serviceURL = /scim

#### 3. 目的地に IAG を設定する

IAG [の構成アプリ](https://help.sap.com/http.svc/login?time=1763658868990&amp;url=%2Fdocs%2FSAP_CLOUD_IDENTITY_ACCESS_GOVERNANCE%c%2F8c45d577632044e9b31f65faf4a7be7c.html%3Fversion%3DCLOUDFOUNDRY)で、[アプリケーション パラメーター] を選択し、 **UserSource &gt; SourceSystem &gt; 編集します**。 編集ページで、「SAP\_Identity\_Services\_Identity\_Directory」 **と入力します**。

#### 4. SCI ユーザー グループ同期ジョブを実行する

1. IAG でジョブ スケジューラを開き、[ジョブのスケジュール]、[SCI ユーザー グループ同期] の順に選択し、[ *直ちに開始]* (または繰り返しを設定) を選択して確定します。
2. **ジョブ履歴**で実行とログを追跡します。

#### 5. BTP サブアカウントIPS\_PROXY宛先を作成する

**前提条件**: 管理者ユーザーは IAS で作成されます。

ポータル内の BTP サブアカウントで IPS\_Proxy 宛先を作成するには、**Destination &gt; Create &gt; From Scratch** に移動し、次の詳細を追加します。

| 名前 | プロパティ |
| --- | --- |
| 名前 | IPS\_PROXY |
| Authentication | BasicAuthentication |
| タイプ | HTTP |
| ユーザー | IAS の管理者ユーザーのクライアント ID (IAG 同期システム管理者の登録手順で作成された ユーザー IAG 同期 ) |
| パスワード | IAS 管理者ユーザーのパスワード |
| Description | IPS デスティネーション |
| プロキシの種類 | インターネット |
| URL | IPS URL を使用する - https://{YOUR\_IPS\_TENANT}&gt;。{DOMAIN}&gt;.hana.ondemand.com |
| その他のプロパティ | Accept = application/scim+jsonServiceURL = /ipsproxy/service/api/v1/scim/USERSURL = /UsersGROUPSURL = /Groups |

#### 6. IAG でアプリケーションを作成する

**前提条件**: IPS プロキシ システムは、Cloud Identity Services に作成されます。

IAG でアプリケーションを作成するには、ポータル内で **Application &gt; + に移動し、 &gt; 説明の追加を作成します**。

詳細なロールの割り当て、宛先の構成、スケジュール オプションなど、より包括的な手順については、SAP ドキュメント「 SAP [ドキュメント: SAP Identity Services から IAG へのユーザー グループの同期」を](https://help.sap.com/docs/SAP_CLOUD_IDENTITY_ACCESS_GOVERNANCE/e12d8683adfa4471ac4edd40809b9038/de385218e7f94ce9ad62b1c3488413dd.html?version=CLOUDFOUNDRY)参照してください。

#### 7. Azure Key Vaultを作成する

Microsoft Entra において SAP IAG インスタンスを Azure サブスクリプションに接続するには、Microsoft Entra の資格情報を格納するために Key Vault を使用する必要があります。 Azure Key Vaultを作成するには、次の手順を実行します。

1. Azure ポータル メニュー、または **Home** ページで、**リソースの作成** を選択します。
2. [検索] ボックスに「Key Vault。
3. 結果の一覧から **Key Vault** を選択します。
4. [Key Vault] セクションで、[**Create** を選択>。
5. [キーボールトの作成] セクションで、次の情報を入力してください。
    - **Name**:一意の名前が必要です。
    - **サブスクリプション**:サブスクリプションを選択します。
    - **[リソース グループ]** で **[新規作成]** を選択し、リソース グループ名を入力します。
    - **[場所]** プルダウン メニューで場所を選択します。
    - 他のオプションは既定値のままにしておきます。
6. **を選択して**を作成します。

#### 8. Azure Key Vault内でシークレットを設定する

Register IAG Sync システム管理者で作成された SAP IAG インスタンス シークレットをAzure Key Vaultに追加する必要があります。 SAP IAG サービスの資格情報から `clientsecret` パラメーターをコピーし、新しいシークレットとしてKey Vaultに追加します。 シークレットをAzure Key Vaultに追加するには、次の手順を実行します。

1. Azure ポータルで Create an Azure Key Vault で作成したキー コンテナーに移動します。
2. 左側のサイドバー Key Vault**Objects**を選択し**Secrets**を選択します。
3. [ **+ 生成/インポート]** を選択します。
4. [シークレットの作成] 画面で、次の値を選択します。
    - **[アップロード オプション]** :手動。
    - **名前**: SAP IAG シークレットの一意の名前を作成する
    - **値**: SAP BTP 資格情報のクライアント識別子を入力します。
        - この値を取得するには、SAP BTP Cockpit にサインインし、[ **インスタンスとサブスクリプション]** に移動し、SAP IAG サービス インスタンス (サービス技術名: `grc-iag-api`) を見つけて、[ **資格情報の表示**] を選択し、 `clientID` 値をコピーします。
    - 他の値は既定値のままにしておきます。 **を選択して**を作成します。

詳細については、「[Azure ポータルを使用してAzure Key Vaultからシークレットを設定および取得する](https://learn.microsoft.com/ja-jp/azure/key-vault/secrets/quick-create-portal)を参照してください。

### Microsoft Entraで SAP IAG インスタンスを接続する

Azureサブスクリプションをセットアップし、その中にSAP IAG への認証に使用するMicrosoft Entraの資格情報を持つAzure Key Vaultを含めたら、エンタイトルメント管理をSAP IAGに接続し、Microsoft EntraはそのKey Vault内の資格情報を利用することができます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com) に、少なくとも [Identity Governance Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) としてサインインします。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**制御構成**を参照します。
3. [制御構成] ページには、[外部コネクタの管理] カードがあります。 **[コネクタの表示]** を選択します。 [Image: コントロール構成コネクタ画面のスクリーンショット。]
4. [コネクタ] ページで、[ **新しいコネクタ**] を選択します。
5. [新しいコネクタ] コンテキストで、ドロップダウン リストから **SAP IAG** を選択します。
6. SAP IAG を選択すると、次のフィールドに入力するオプションが表示されます。

    1. **型**: このフィールドは既定で **IAG** に設定されています。
    2. **[名前**]: コネクタのカスタム名を入力します。
    3. **説明**: コネクタの説明を入力します。
    4. **サブスクリプション ID**: Azure Key Vault リソースが配置されているAzureサブスクリプション ID を選択します。
    5. **Key Vault Name**: ドロップダウンから、IAG シークレットが格納されているAzure Key Vault リソースを選択します。
    6. **シークレット名**: SAP IAG クライアント シークレットを含むシークレットを選択します。
    7. **クライアント ID**: SAP BTP 資格情報からクライアント識別子を入力します。 これは、手順 Azure Key Vault 内でシークレットを設定 でキーボールトに追加されました。
    8. **SAP IAG アクセス トークン URL: SAP IAG** サービスを呼び出す認証トークンを生成するためのベース URL を入力します。

        - この値を取得するには、SAP BTP Cockpit で **インスタンスとサブスクリプション**に移動し、SAP IAG サービス インスタンス (サービス技術名: `grc-iag-api`) を見つけ、[ **資格情報の表示**] を選択し、 `url` パラメーターをコピーして、このフィールドに入力する前にサフィックス `/oauth/token` を追加します。
    9. **IAG URL**: SAP IAG によって公開されるすべてのサービスのベース URL を入力します。

        - この値を取得するには、SAP BTP Cockpit で **、インスタンスとサブスクリプション**に移動し、SAP IAG サービス インスタンス (サービス技術名: `grc-iag-api`) を見つけて、[資格情報の **表示]** を選択し、 `ARQAPI` 値をコピーします。
7. [ **作成] を** 選択してコネクタを作成します。

Microsoft Entra内のカタログ管理者は、SAP IAG インスタンスからエンタイトルメント管理カタログおよびアクセス パッケージに SAP ビジネス ロールを追加できるようになりました。

### SAP ビジネス ロールを使用してカタログとアクセス パッケージを設定する

SAP IAG への外部コネクタが構成されたので、その SAP IAG のビジネス ロールをアクセス パッケージのリソース ロールとして追加できます。

[Image: 外部コネクタ、カタログ、およびアクセス パッケージ リソース ロールの関係の図。]

1. 少なくとも [Identity Governance Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator) として [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **ID ガバナンス**&gt;**権限管理**&gt;**カタログ** を参照します。
3. [新しいカタログを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create)するか、SAP ビジネス ロールを追加する既存のカタログを選択します。
4. カタログが作成または選択されたら、その **リソース** セクションに移動し、[ **リソースの追加]** を選択します。
5. [SAP IAG] ボタンを選択してコンテキスト ウィンドウを開き、このカタログにリソースとして含める SAP IAG インスタンスを選択できます。 ドロップダウンで、接続した SAP IAG インスタンスを選択します。 [Image: SAP IAG をリソースとしてカタログに追加するスクリーンショット。]
6. SAP IAG インスタンスをカタログに追加したら、カタログ内の [アクセス パッケージ] タブに移動し、[新しいアクセス パッケージ] ボタンを選択します。 [基本情報](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#configure-basics)を入力し、[**次へ**] を選択します。アクセス パッケージに追加できるリソース ロールを参照してください。
7. [リソース ロール] タブで、SAP IAG を選択します。 ここでは、SAP IAG アクセス権を選択できます。
8. リソース テーブルで、アクセス パッケージに含める特定のビジネス ロールを選択し、[ **次へ**] を選択できます。 [Image: SAP IAG リソースのロールの設定のスクリーンショット。]
9. [要求] タブで、アクセス パッケージを要求できるユーザーを指定する最初のポリシーを作成します。 そのポリシーの承認設定も構成します。

    1. [**ディレクトリ内のユーザー、サービス プリンシパル、およびエージェント ID の場合] を**選択します
    2. このアクセス パッケージの使用を特定のユーザーのみに制限するには、[特定のユーザー**とグループ**] を選択します。 新しいMicrosoft Entra グループを作成することをお勧めします。
    3. 必要に応じて承認設定を定義する
    4. [ **アクセスを要求できるユーザー**] で [自己] が選択されていることを確認します。
    5. **[次へ: 要求者情報**] を選択する
10. [要求者情報] タブで、[**次へ: ライフサイクル**] を選択します
11. [ライフサイクル] タブで、アクセス権を付与する日数を入力し、[ **アクセス レビューが必要** ] が選択されていないことを確認して、[ **次へ: ルール**] を選択します。
12. [ **作成]** を選択して、設定を使用してアクセス パッケージの設定を完了します。 アクセス パッケージの作成の詳細については、「 [エンタイトルメント管理でのアクセス パッケージの作成」を](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)参照してください。

### 統合のテスト

新しい SAP IAG コネクタを構成したら、エンド ツー エンドのテスト シナリオで次の手順に従うことができます。

- ID ガバナンス管理者は、アクセス パッケージ [に ID を直接割り当てることができます](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-an-identity) 。
- ユーザーとして、「 SAP ビジネス ロールを使用したカタログとアクセス パッケージのセットアップ」ステップで作成されたアクセス パッケージを 要求します。 アクセス パッケージを要求する方法については、「 [エンタイトルメント管理でアクセス パッケージへのアクセスを要求する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-access)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-scenarios"} -->
## エンタイトルメント管理の一般的なシナリオ - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-scenarios
- Service: entra-id-governance / entitlement-management
- Article date: 2024-07-15
- Summary: Microsoft Entra エンタイトルメント管理での一般的なシナリオの場合に従う必要がある、大まかな手順について説明します。

組織のエンタイトルメント管理を構成するには、いくつかの方法があります。 ただし、始めたばかりの場合は、管理者、カタログ所有者、アクセスパッケージ管理者、承認者、リクエスト者の一般的なシナリオを理解することが役立ちます。

### 代理人

#### 管理者: リソースの管理を委任する

1. [ビデオ: IT 部門から部門マネージャーへの委任](https://learn-video.azurefd.net/vod/player?id=0915072b-63ec-4c78-b2ca-aa5f54a54219)
2. [カタログ作成者ロールにユーザーを委任する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate-catalog)

#### カタログ作成者: リソースの管理を委任する

- [新しいカタログを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#create-a-catalog)

#### カタログ所有者: リソースの管理を委任する

1. [カタログに共同所有者を追加する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#add-more-catalog-owners)
2. [カタログにリソースを追加する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#add-resources-to-a-catalog)

#### カタログ所有者: access パッケージの管理を委任する

1. 動画を見る：カタログ所有者からパッケージマネージャーへのアクセス権委任
2. [パッケージ管理者のロールへのアクセスをユーザーに委任します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate-managers)

### 組織内のユーザーのaccessを管理する

#### 管理者: 従業員のアクセスを自動的に割り当てる

1. [新しいaccess パッケージを作成します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#start-the-creation-process)
2. [グループ、Teams、アプリケーション、または SharePoint サイトをアクセス パッケージに追加](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#select-resource-roles)
3. [自動割り当てポリシーを追加します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)

#### 管理者: ライフサイクル ワークフローから従業員にアクセスを割り当てる

1. [新しいaccess パッケージを作成します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#start-the-creation-process)
2. [グループ、Teams、アプリケーション、または SharePoint サイトをアクセス パッケージに追加](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#select-resource-roles)
3. [直接割り当てポリシーを追加する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#none-administrator-direct-assignments-only)
4. ユーザーが参加するときに、[ユーザーアクセスパッケージの割り当て要求](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#request-user-access-package-assignment)にタスクをワークフローに追加する
5. ユーザーが離れたときにワークフローに[ユーザーのアクセスパッケージ割り当てを削除する](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#remove-access-package-assignment-for-user)タスクを追加する

#### Access package manager: 組織内の従業員がリソースへのaccessを要求できるようにする

1. [新しいaccess パッケージを作成します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#start-the-creation-process)
2. [グループ、Teams、アプリケーション、または SharePoint サイトをアクセス パッケージに追加](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#select-resource-roles)
3. ディレクトリ内のユーザー、サービス プリンシパル、およびエージェント ID が access
4. [有効期限の設定](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#specify-a-lifecycle)

#### リクエスタ: リソースへのaccessを要求する

1. [マイ Access ポータルにサインインします](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-access#sign-in-to-the-my-access-portal)
2. アクセスパッケージを検索
3. [アクセスの要求](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-access#request-an-access-package)

#### 承認者:リソースへの要求を承認する

1. [マイ Access ポータルで要求を開く](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-approve#open-request)
2. [アクセス要求を承認または拒否](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-approve#approve-or-deny-request)

#### リクエスタ: 既にアクセスしているリソースを表示する

1. [マイ Access ポータルにサインインします](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-access#sign-in-to-the-my-access-portal)
2. アクティブなアクセスパッケージを表示する

### 組織外のユーザーのaccessを管理する

#### 管理者: 外部パートナー組織とコラボレーションする

1. [外部ユーザーのaccessのしくみを読む](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users#how-access-works-for-external-users)
2. [外部ユーザーの設定を確認する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users#settings-for-external-users)
3. [外部組織に接続を追加する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-organization)

#### アクセスパッケージマネージャー: 外部パートナー組織と共同作業する

1. [新しいaccess パッケージを作成します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#start-the-creation-process)
2. [グループ、Teams、アプリケーション、または SharePoint サイトをアクセス パッケージに追加](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources#add-resource-roles)
3. [ディレクトリに存在しないユーザーがアクセスを要求できるようにするために、要求ポリシーを追加します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#for-users-not-in-your-directory)
4. [有効期限の設定](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#specify-a-lifecycle)
5. [access パッケージを要求するリンクをコピーします](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-settings)
6. 外部パートナーの連絡先パートナーにそのユーザーと共有するためのリンクを送信する

#### リクエスタ: 外部ユーザーとしてリソースへのaccessを要求する

1. 連絡先から受け取った「アクセス パッケージ」のリンクを見つける
2. [マイ Access ポータルにサインインします](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-access#sign-in-to-the-my-access-portal)
3. [アクセスの要求](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-access#request-an-access-package)

#### 承認者:リソースへの要求を承認する

1. [マイ Access ポータルで要求を開く](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-approve#open-request)
2. [アクセス要求を承認または拒否](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-approve#approve-or-deny-request)

#### リクエスタ: 既にアクセスしているリソースを表示する

1. [マイ Access ポータルにサインインします](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-access#sign-in-to-the-my-access-portal)
2. アクティブなアクセスパッケージを表示する

### エージェントのaccessを管理する (プレビュー)

エージェント ID [にMicrosoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を使用するには、次のいずれかのライセンス プランが必要です。

- **Microsoft 365 E7** (エージェント 365 とMicrosoft Entra スイートを含む) は、ユーザー ID とエージェント ID のガバナンスを提供します。
- **Microsoftエージェント 365** ライセンスは、少なくとも Microsoft Entra P1 または Microsoft 365 E3 とペアリングされています。

詳細については、「[Microsoft Agent 365 のプランと価格](https://www.microsoft.com/microsoft-agent-365#plans-and-pricing)」を参照してください。 エージェント固有の機能の完全な一覧については、**Microsoft Entra ID ガバナンス ライセンス表**の [Microsoft Agent 365](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals) 列を参照してください。

1. [新しいaccess パッケージを作成します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#start-the-creation-process)
2. [パッケージにアクセスするためにグループまたは API の権限を追加します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#select-resource-roles)
3. [ディレクトリにおけるサービス プリンシパルとエージェント識別子がアクセス権を要求できるようにする要求ポリシーを追加します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#allow-users-service-principals-and-agent-identities-in-your-directory-to-request-the-access-package)

### 日々の管理

#### 管理者: 提案および構成されている接続されている組織を表示する

1. [接続されている組織の一覧を表示する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-organization)

#### Access package manager: projectのリソースを更新する

1. [ビデオ: 日常の管理: 変更されています](https://learn-video.azurefd.net/vod/player?id=cebe87cf-64db-4242-9527-f726b6d227f9)
2. access パッケージを開く
3. [グループ、Teams、アプリケーション、または SharePoint サイトを追加または削除します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources#add-resource-roles)

#### パッケージマネージャにアクセスして、プロジェクトの期間を更新する

1. [ビデオ: 日常の管理: 変更されています](https://learn-video.azurefd.net/vod/player?id=cebe87cf-64db-4242-9527-f726b6d227f9)
2. access パッケージを開く
3. [ライフサイクル設定を開きます](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-lifecycle-policy#open-lifecycle-settings)
4. [有効期限の設定を更新します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-lifecycle-policy#specify-a-lifecycle)

#### Access package manager: projectに対するaccessの承認方法を更新する

1. [ビデオ: 日常の管理: 変更されています](https://learn-video.azurefd.net/vod/player?id=cebe87cf-64db-4242-9527-f726b6d227f9)
2. [既存のポリシーの要求設定を開きます](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#open-an-existing-access-package-and-add-a-new-policy-with-different-request-settings)
3. [承認設定を更新します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy#change-approval-settings-of-an-existing-access-package-assignment-policy)

#### Access package manager: projectのユーザーを更新する

1. [ビデオ: 日常の管理: 変更されています](https://learn-video.azurefd.net/vod/player?id=cebe87cf-64db-4242-9527-f726b6d227f9)
2. [アクセスが不要になったユーザーを削除します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments)
3. [既存のポリシーの要求設定を開きます](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#open-an-existing-access-package-and-add-a-new-policy-with-different-request-settings)
4. [アクセスが必要な ID を追加します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#for-users-service-principals-and-agent-identities-in-your-directory)

#### Access package manager: access パッケージに特定のユーザーを直接割り当てる

1. [ユーザーが異なるライフサイクル設定を必要とする場合は、access パッケージに新しいポリシーを追加します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#open-an-existing-access-package-and-add-a-new-policy-with-different-request-settings)
2. [access パッケージに特定の ID を間接的に割り当てます](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-an-identity)

### 割り当てとレポート

#### 管理者: access パッケージに割り当てられているユーザーを表示する

1. アクセスパッケージを開く
2. [View の割り当て](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#view-who-has-an-assignment)
3. [レポートとログをアーカイブする](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logs-and-reporting)

#### 管理者: ユーザーに割り当てられているリソースを表示する

1. [ユーザーのためのアクセスパッケージを表示します](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-reports#view-access-packages-for-a-user)
2. [ユーザーのリソースの割り当てを表示する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-reports#view-resource-assignments-for-a-user)

### プログラムによる管理

Microsoft Graphを使用して、access パッケージ、カタログ、ポリシー、要求、割り当てを管理することもできます。 `EntitlementManagement.Read.All` または `EntitlementManagement.ReadWrite.All` アクセス許可を委任されたアプリケーションの適切なロールのユーザーは、[エンタイトルメント管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/entitlementmanagement-overview) を呼び出すことができます。 詳細については、「[Tutorial: リソースへのaccessの管理 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/tutorial-access-package-api)」を参照してください。 `EntitlementManagement.Read.All` または `EntitlementManagement.ReadWrite.All` アプリケーションのアクセス許可を持つアプリケーションでは、カタログおよびaccess パッケージ内のリソースの管理を除き、これらの API 関数の多くを使用することもできます。 また、特定のカタログ内でしか動作する必要がないアプリケーションは、カタログの**カタログ所有者**または**カタログ閲覧者**ロールに追加して、そのカタログ内での更新または読み取りが認可されるようにできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-servicenow-integration"} -->
## Microsoft Entra ID Entitlement Management と ServiceNow の統合 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-servicenow-integration
- Service: entra-id-governance / entitlement-management
- Article date: 2026-09-02
- Summary: Microsoft Entra IDエンタイトルメント管理を ServiceNow と統合して、アクセス パッケージの要求と承認を行う方法について説明します。

### Overview

Microsoft Entra IDエンタイトルメント管理は ServiceNow と統合され、ユーザーがアクセス パッケージを要求したり、要求履歴を表示したり、承認者として指定されたときに要求を承認または拒否したりできる代替ポータルが提供されます。

Microsoft Entra ID エンタイトルメント管理は、引き続きレコードのシステムです。 管理者は、Microsoft Entra IDエンタイトルメント管理でカタログ、アクセス パッケージ、割り当てポリシー、要求者の適格性、承認者、割り当てを作成および管理します。 ServiceNow は、エンド ユーザーの要求と承認エクスペリエンスを提供します。

| **アクティビティ** | **実行される場所** |
| --- | --- |
| アクセス パッケージ、ポリシー、承認者の作成と管理 | Microsoft Entra ID エンタイトルメント管理 |
| アクセス パッケージを要求する | ServiceNow |
| 要求の状態と履歴を表示する | ServiceNow |
| 要求を承認または拒否する | ServiceNow |

### ライセンス要件

この統合を使用するには、Microsoft Entra ID P2 または Microsoft Entra スイート ライセンスが必要です。

Note

アクセス パッケージとポリシーは、ServiceNow ではなく、Microsoft Entra IDエンタイトルメント管理で作成および管理されます。

### インストールと構成

[Microsoft Entra ID ガバナンス - ServiceNow ストア](https://store.servicenow.com/store/app/9c8ca6a087c80fd4a6c6fc48cebb3560)からアプリケーションをインストールします。

インストールと構成の手順については、「ServiceNow ストアの**リンクとドキュメント**」ページの[「Microsoft Entra ID ガバナンス ServiceNow インストール ガイド」](https://store.servicenow.com/api/sn_store/v1/store/attachment/9837597d97428f503fa8b84bf253af41)を参照してください。

### ServiceNow で使用可能な機能

ServiceNow で、**サービス カタログ**&gt;**Microsoft Entra ID ガバナンスを**開きます。 **[アクセス パッケージ**] オプションと [**要求履歴**] オプションは要求者が使用でき、**承認**は指定された承認者が使用できます。

[Image: アクセス パッケージ、要求履歴、承認を示す ServiceNow のMicrosoft Entra ID ガバナンス アプリケーションのスクリーンショット。]

*図 1. ServiceNow の Microsoft Entra ID ガバナンス アプリケーション*

### アクセス パッケージを要求する

[ **アクセス パッケージ**] を選択し、[ **利用可能]** タブを開き、アクセス パッケージを選択し、割り当てポリシーに必要な情報を指定して、要求を送信します。 現在および過去のアクセスを表示するには、[ **アクティブ]** タブと [ **有効期限切れ** ] タブを使用します。

[Image: ユーザーが要求できる利用可能なパッケージを示す ServiceNow の [アクセス パッケージ] ページのスクリーンショット。]

*図 2. ServiceNow の [パッケージへのアクセス] ページ。*

### アクセス パッケージの要求履歴を表示する

[ **要求履歴** ] を選択して、送信された要求とその現在の状態を表示します。 [ **表示]** を選択して、追加の要求の詳細を開きます。

[Image: 送信されたアクセス パッケージ要求とその状態を示す ServiceNow の [要求履歴] ページのスクリーンショット。]

*図 3。 ServiceNow の [要求履歴] ページ。*

### アクセス パッケージ要求を承認または拒否する

[ **承認]** を選択し、[ **確認** ] を選択して、割り当てられた要求を開きます。 詳細を確認し、[ **承認** ] または **[拒否**] を選択します。

[Image: 承認者に割り当てられたアクセス パッケージ要求を示す ServiceNow の [承認] ページのスクリーンショット。]

*図 4. ServiceNow の [承認] ページ。*
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-suggested-access-packages"} -->
## エンタイトルメント管理でマイ アクセスの推奨アクセス パッケージを表示する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-suggested-access-packages
- Service: entra-id-governance / entitlement-management
- Article date: 2025-01-09
- Summary: 最も関連性の高いアクセス パッケージをすばやく見つけられるように、マイ アクセスで推奨されるアクセス パッケージをユーザーに表示する方法について説明します。

マイ アクセスでは、Microsoft Entra ID ガバナンス ユーザーはマイ アクセスで推奨されるアクセス パッケージのキュレーションされた一覧を表示できます。 この機能を使用すると、ユーザーは、使用可能なすべてのアクセス パッケージをスクロールすることなく、ピアのアクセス パッケージと以前の割り当てに基づいて、最も関連性の高いアクセス パッケージをすばやく表示できます。

推奨されるアクセス パッケージの一覧は、ユーザー (マネージャー、直属の部下、組織、チーム メンバー) に関連するユーザーを検索し、ユーザーのピアの内容に基づいてアクセス パッケージを推奨することによって作成されます。 ユーザーには、以前に割り当てられていたアクセス パッケージも推奨されます。

### エンド ユーザーがマイ アクセスで推奨されるアクセス パッケージを表示するための設定

マイ アクセスで推奨されるアクセス パッケージを有効にするには、次の手順に従います。

1. 少なくとも [ID ガバナンス管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**コントロール設定**&gt;**エンドユーザー向けマイアクセス設定**を参照します。

    [Image: オプトイン機能選択オプションのスクリーンショット。]
3. [マイ アクセス] の [提案されたアクセス パッケージの分析情報を表示する] で、[ *過去の割り当て]、[過去の割り当てとピア (名前なし*)]、または [ *過去の割り当てと名前を持つピア*] を選択します。
4. **[保存] を選択します**。
5. https://myaccess.microsoft.comのマイ アクセス ポータルにサインインします。 **[アクセス パッケージ**] を選択して、推奨されるアクセス パッケージを表示します。[Image: 推奨されるアクセス パッケージのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-ticketed-provisioning"} -->
## Microsoft Entra エンタイトルメント管理統合を使用した ServiceNow チケットの自動作成 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-ticketed-provisioning
- Service: entra-id-governance / entitlement-management
- Article date: 2026-02-02
- Summary: このチュートリアルでは、カスタム拡張機能と Logic Apps を使用した、エンタイトルメント管理との ServiceNow の統合によるチケット プロビジョニングについて説明します。

シナリオ: このシナリオでは、割り当てを受け取ってアプリへのアクセスが必要なユーザーの手動プロビジョニングのために、カスタム拡張機能と Logic Apps を使用して ServiceNow チケットを自動的に生成する方法について説明します。

このチュートリアルでは、次の情報を学習します。

- ロジック アプリ ワークフローを既存のカタログに追加する。
- 既存のアクセス パッケージ内のポリシーにカスタム拡張機能を追加する。
- エンタイトルメント管理ワークフローを再開するために Microsoft Entra ID にアプリケーションを登録する
- Automation 認証用に ServiceNow を構成する。
- アクセス パッケージへのアクセスをエンド ユーザーとして要求する。
- 要求されたアクセス パッケージへのアクセスをエンド ユーザーとして受け取る。

### 前提条件

- アクティブな Azure サブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかの最小特権ロール: クラウド アプリケーション管理者、アプリケーション管理者、またはサービス プリンシパルの所有者。
- ローマ以上の [ServiceNow インスタンス](https://www.servicenow.com/)
- SSO と ServiceNow との統合。 これがまだ構成されていない場合は、続行する前に、「[チュートリアル: Microsoft Entra シングル サインオン (SSO) と ServiceNow の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servicenow-tutorial) 」を参照してください。

注

これらの手順を完了するときは、最小限の特権ロールを使用することをお勧めします。

### エンタイトルメント管理のためにロジック アプリ ワークフローを既存のカタログに追加する

ロジック アプリ ワークフローを既存のカタログに追加するには、ここでロジック アプリを作成するための ARM テンプレートを使用します。

。

[Image: ロジック アプリ ARM テンプレートのスクリーンショット。]

リソース グループの詳細と、ロジック アプリを関連付けるカタログ ID を指定し、購入を選択します。 新しいカタログを作成する方法の詳細については、「 [エンタイトルメント管理でリソースのカタログを作成および管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create)」を参照してください。

カタログが作成されたら、次の手順を実行してロジック アプリ ワークフローを追加します。

1. 少なくとも [ID ガバナンス管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者とリソース グループ所有者があります。
2. 左側のメニューで、[カタログ] を選択 **します**。
3. カスタム拡張機能を追加するカタログを選択し、左側のメニューで [ **カスタム拡張機能**] を選択します。
4. ヘッダー ナビゲーション バーで、[ **カスタム拡張機能の追加]** を選択します。
5. [ **基本** ] タブで、カスタム拡張機能の名前とワークフローの説明を入力します。 これらのフィールドは、カタログの **[カスタム拡張機能** ] タブに表示されます。 [Image: エンタイトルメント管理用のカスタム拡張機能を作成するスクリーンショット。]
6. [要求**ワークフロー**] として**拡張機能の種類**を選択し、作成中に要求されたアクセス パッケージのポリシー ステージに対応します。 [Image: エンタイトルメント管理のカスタム拡張機能の動作アクション タブのスクリーンショット。]
7. 拡張機能にリンクされたロジック アプリがタスクを完了し、管理者が再開アクションを送信してプロセスを続行するまで、関連付けられているアクセス パッケージ アクションを一時停止する拡張機能**構成**で **[起動**] を選択して待機します。 このプロセスの詳細については、「 [エンタイトルメント管理プロセスを一時停止するカスタム拡張機能の構成」を](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logic-apps-integration#configuring-custom-extensions-that-pause-entitlement-management-processes)参照してください。
8. 前の手順でロジック アプリが作成されたため、[ **詳細** ] タブの [*新しいロジック アプリの作成*] フィールドで [いいえ] を選択します。 ただし、ロジック アプリ名と共に、Azure サブスクリプションとリソース グループの詳細を指定する必要があります。 [Image: エンタイトルメント管理のカスタム拡張機能の詳細タブのスクリーンショット。]
9. [ **確認と作成]** で、カスタム拡張機能の概要を確認し、ロジック アプリの呼び出しの詳細が正しいことを確認します。 次に、[ **作成**] を選択します。
10. 作成されると、ロジック アプリは、カスタム拡張機能ページのカスタム拡張機能の横にある **ロジック アプリ** でアクセスできるようになります。 これは、アクセス パッケージ ポリシーで呼び出すことができます。 [Image: カスタム拡張機能リストのスクリーンショット。]

ヒント

エンタイトルメント管理プロセスを一時停止するカスタム拡張機能機能の詳細については、「エンタイトルメント管理プロセスを [一時停止するカスタム拡張機能の構成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logic-apps-integration#configuring-custom-extensions-that-pause-entitlement-management-processes)」を参照してください。

### 既存のアクセス パッケージ内のポリシーにカスタム拡張機能を追加する

カタログでカスタム拡張機能を設定した後、管理者はポリシーを使用してアクセス パッケージを作成し、要求が承認されたときにカスタム拡張機能をトリガーできます。 これにより、特定のアクセス要件を定義し、組織のニーズに合わせてアクセス レビュー プロセスを調整できます。

1. 少なくとも [ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)として Microsoft Entra ポータルで、[ **アクセス パッケージ**] を選択します。

    ヒント

    このタスクを完了できる他の最小限の特権ロールには、カタログ所有者とアクセス パッケージ マネージャーがあります。
2. 既に作成されているアクセス パッケージの一覧から、カスタム拡張機能 (ロジック アプリ) を追加するアクセス パッケージを選択します。
3. [ポリシー] タブに移動し、ポリシーを選択して、[ **編集]** を選択します。
4. ポリシー設定で、[ **カスタム拡張機能** ] タブに移動します。
5. **ステージ**の下のメニューで、このカスタム拡張機能 (ロジック アプリ) のトリガーとして使用するアクセス パッケージ イベントを選択します。 このシナリオでは、アクセス パッケージが承認されたときにカスタム拡張機能ロジック アプリ ワークフローをトリガーするには、[ **要求が承認されました**] を選択します。

    注

    アクセス許可が以前付与されていた期限切れの割り当てについて ServiceNow チケットを作成するには、"割り当てが削除されました" の新しいステージを追加し、LogicApp を選択します。
6. [カスタム拡張機能] の下のメニューで、このアクセス パッケージに追加するために上記のステップで作成したカスタム拡張機能 (ロジック アプリ) を選択します。 選択したアクションは *、when フィールド* で選択されたイベントが発生したときに実行されます。
7. [ **更新]** を選択して、既存のアクセス パッケージのポリシーに追加します。 [Image: アクセス パッケージのカスタム拡張機能の詳細のスクリーンショット。]

注

**新しいアクセス パッケージ**を作成する場合は、[新しいアクセス パッケージ] を選択します。 アクセス パッケージを作成する方法の詳細については、「 [エンタイトルメント管理で新しいアクセス パッケージを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)」を参照してください。 既存のアクセス パッケージを編集する方法の詳細については、「 [Microsoft Entra エンタイトルメント管理でアクセス パッケージの要求設定を変更](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#open-and-edit-an-existing-policys-request-settings)する」を参照してください。

### Microsoft Entra 管理センターでシークレットにアプリケーションを登録する

Azure では、 [Azure Key Vault](https://learn.microsoft.com/ja-jp/azure/key-vault/secrets/about-secrets) を使用して、パスワードなどのアプリケーション シークレットを格納できます。 Microsoft Entra 管理センター内のシークレットにアプリケーションを登録するには、次の手順に従います。

1. 少なくとも [ID ガバナンス管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。
2. **Entra ID**&gt;**App 登録に移動します**。
3. [管理] で、[アプリの登録] &gt; [新規登録] を選択します。
4. アプリケーションの表示名を入力します。
5. サポートされているアカウントの種類に、[この組織のディレクトリ内のアカウントのみ] を選択します。
6. [登録] を選択します。

アプリケーションを登録したら、次の手順に従ってクライアント シークレットを追加する必要があります。

1. **Entra ID**&gt;**App 登録に移動します**。
2. アプリケーションを選択します。
3. &gt;[証明書とシークレット]&gt;[クライアント シークレット][新しいクライアント シークレット] を選択します。
4. クライアント シークレットの説明を追加します。
5. シークレットの有効期限を選択するか、カスタムの有効期間を指定します。
6. [追加] を選択します。

注

アプリケーションの登録の詳細については、「 [クイック スタート: Microsoft ID プラットフォームにアプリを登録する」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)参照してください。

作成されたアプリケーションが [MS Graph 再開 API](https://learn.microsoft.com/ja-jp/graph/api/accesspackageassignmentrequest-resume) を呼び出すのを承認するには、次の手順を実行します。

1. Microsoft Entra 管理センター [Identity Governance - Microsoft Entra 管理センター](https://entra.microsoft.com/#view/Microsoft_AAD_ERM/DashboardBlade/%7E/elmEntitlement) に移動します
2. 左側のメニューで、[カタログ] を選択 **します**。
3. カスタム拡張機能を追加したカタログを選択します。
4. [ロールと管理者] メニューを選択し、[+ アクセス パッケージ割り当てマネージャーの追加] を選択します。
5. [メンバーの選択] ダイアログ ボックスで、作成されたアプリケーションを名前またはアプリケーション ID で検索します。 アプリケーションを選択し、[選択] ボタンを選択 します。

ヒント

委任とロールの詳細については、Microsoft の公式ドキュメント「 [権利管理における委任とロール](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate)」を参照してください。

### Automation 認証用に ServiceNow を構成する

この時点で、ServiceNow チケットのクローズ後にエンタイトルメント管理ワークフローを再開するように ServiceNow を構成します。

1. 次の手順に従って、ServiceNow Application Registry に Microsoft Entra アプリケーションを登録します。
    1. ServiceNow にサインインし、[Application Registry] に移動します。
    2. [*新規*] を選択し、[**サード パーティの OAuth プロバイダーに接続する**] を選択します。
    3. アプリケーションの名前を指定し、[Default Grant type] で [Client Credentials] を選択します。
    4. Microsoft Entra 管理センターに Microsoft Entra アプリケーションを登録したときに生成されたクライアント名、ID、クライアント シークレット、認可 URL、トークン URL を入力します。
    5. アプリケーションを送信します。 [Image: ServiceNow 内のアプリケーション レジストリのスクリーンショット。]
2. 次の手順に従って、System Web Service の REST API メッセージを作成します。
    1. [System Web Services] の [REST API Messages] セクションに移動します。
    2. [New] ボタンを選択して、新しい REST API メッセージを作成します。
    3. すべての必須フィールドに入力します。これにはエンドポイント URL の指定を含みます: `https://learn.microsoft.com/en-us/graph/api/accesspackageassignmentrequest-resume?view=graph-rest-1.0&tabs=http`
    4. 必要なヘッダーの場合: コンテンツ タイプ: `application/json`
    5. [Authentication] で [OAuth2.0] を選択し、アプリ登録プロセス中に作成された OAuth プロファイルを選択します。
    6. [*送信]* ボタンを選択して変更を保存します。
    7. [System Web Services] の [REST API Messages] セクションに戻ります。
    8. [Http 要求] を選択し、[*新規*] を選択します。 名前を入力し、Http メソッドとして [POST] を選択します。
    9. Http 要求で、次の API スキーマを使用して Http クエリ パラメーターのコンテンツを追加します。

        ```http
        {
        "data": {
            "@odata.type": "#microsoft.graph.accessPackageAssignmentRequestCallbackData",
            "customExtensionStageInstanceDetail": "Resuming-Assignment for user",
            "customExtensionStageInstanceId": "${StageInstanceId}",
            "stage": "${Stage}"
                  },
                  "source": "ServiceNow",
                    "type": "microsoft.graph.accessPackageCustomExtensionStage.${Stage}"
                    }
        ```
    10. [*送信]* を選択して変更を保存します。 [Image: ServiceNow 内での再開呼び出しの選択のスクリーンショット。]

        [Image: ServiceNow 内の http 要求のスクリーンショット。]
3. 要求テーブル スキーマの変更: 要求テーブル スキーマを変更するには、次の図に示す 3 つのテーブルに変更を加えます。 [Image: ServiceNow 内の要求テーブル スキーマのスクリーンショット。]4 列のラベルと型を文字列として追加します。
    - アクセスパッケージ割り当てリクエストID (AccessPackageAssignmentRequestId)
    - アクセスパッケージ割り当てステージ
    - ステージインスタンスID
    - EntraユーザーオブジェクトID
4. フロー デザイナーを使用してワークフローを自動化するには、次の手順を実行します。
    1. ServiceNow にサインインし、[Flow Designer] に移動します。
    2. [*新規*] ボタンを選択し、新しいアクションを作成します。
    3. 前の手順で作成した System Web Service の REST API メッセージを呼び出すアクションを追加します。 [Image: ServiceNow 内でエンタイトルメント管理プロセスを再開するフロー デザイナー スクリプトのスクリーンショット。] アクションのスクリプト: (前の手順で作成した列ラベルを使用してスクリプトを更新します)。

        ```
        (function execute(inputs, outputs) {
            gs.info("AccessPackageAssignmentRequestId: " + inputs['accesspkgassignmentrequestid']);
            gs.info("StageInstanceId: " + inputs['customextensionstageinstanceid'] );
            gs.info("Stage: " + inputs['assignmentstage']);
            var r = new sn_ws.RESTMessageV2('Resume ELM WorkFlow', 'RESUME');
            r.setStringParameterNoEscape('AccessPackageAssignmentRequestId', inputs['accesspkgassignmentrequestid']);
            r.setStringParameterNoEscape('StageInstanceId', inputs['customextensionstageinstanceid'] );
            r.setStringParameterNoEscape('Stage', inputs['assignmentstage']);
            var response = r.execute();
            var responseBody = response.getBody();
            var httpStatus = response.getStatusCode();
            var requestBody =  r.getRequestBody();
            gs.info("requestBody: " + requestBody);
            gs.info("responseBody: " + responseBody);
            gs.info("httpStatus: " + httpStatus);
            })(inputs, outputs); 
        ```
    4. アクションを保存します。
    5. [*新規*] ボタンを選択して、新しいフローを作成します。
    6. フロー名を入力し、[Run as – System User] を選択し、[submit] を選択します。
5. ServiceNow 内にトリガーを作成するには、次の手順に従います。
    1. *トリガーを追加* を選択し、*更新* トリガーを選択して、更新ごとにトリガーを実行します。
    2. 次の図に示すように条件を更新してフィルター条件を追加します。 [Image: ServiceNow 呼び出しエンタイトルメント管理再開 API のスクリーンショット]
    3. [完了] を選択します。
    4. [アクションの追加] を選択 [Image: します。フロー ダイアグラム トリガーのスクリーンショット。]
    5. アクションを選択し、前の手順で作成したアクションを選択します。 [Image: フロー デザイナーのアクションの選択のスクリーンショット。]
    6. 新しく作成した列を要求レコードから適切なアクション パラメーターにドラッグ アンド ドロップします。
    7. [Done]、[Save]、[Activate] の順に選択します。 [Image: フロー デザイナー内での保存とアクティブ化のスクリーンショット。]

### アクセス パッケージへのアクセスをエンド ユーザーとして要求する

エンド ユーザーがアクセス パッケージへのアクセスを要求すると、要求は適切な承認者に送信されます。 承認者が承認を許可すると、エンタイトルメント管理によってロジック アプリが呼び出されます。 その後、ロジック アプリでは ServiceNow を呼び出して新しい要求/チケットが作成され、エンタイトルメント管理は ServiceNow からのコールバックを待機します。

[Image: アクセス パッケージの要求のスクリーンショット。]

### 要求されたアクセス パッケージへのアクセスをエンド ユーザーとして受け取る

IT サポート チームは、前に作成されたチケットによる必要なプロビジョニングを実行し、ServiceNow チケットを閉じます。 チケットが閉じられると、ServiceNow ではエンタイトルメント管理ワークフローを再開するための呼び出しがトリガーされます。 要求が完了すると、要求元は要求が処理されたことを示す通知をエンタイトルメント管理から受け取ります。 この合理化されたワークフローにより、アクセス要求が効率的に処理され、ユーザーに迅速に通知されるようになります。

[Image: マイ アクセス要求履歴のスクリーンショット。]

注

チケットが 14 日以内に閉じられていない場合、エンド ユーザーは MyAccess ポータルに "割り当てに失敗しました" と表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-troubleshoot"} -->
## エンタイトルメント管理のトラブルシューティング - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-troubleshoot
- Service: entra-id-governance / entitlement-management
- Article date: 2025-06-02
- Summary: Microsoft Entra エンタイトルメント管理をトラブルシューティングする際に確認しておくべき事項について説明します。

この記事では、エンタイトルメント管理のトラブルシューティングに役立てるために確認する必要がある事項について説明します。

### 管理

- エンタイトルメント管理を構成するときにアクセス拒否メッセージが表示され、全体管理者である場合は、ディレクトリに [Microsoft Entra ID P2 または Microsoft Entra ID Governance (または EMS E5) ライセンス](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview#license-requirements)があることを確認します。 期限切れの Microsoft Entra ID P2 または Microsoft Entra ID ガバナンスのサブスクリプションをごく最近更新した場合、ライセンス更新が反映されるまでに 8 時間かかることがあります。
- テナントの Microsoft Entra ID P2 または Microsoft Entra ID Governance ライセンスの有効期限が切れている場合は、新しいアクセス要求を処理したり、アクセス レビューを実行したりできません。
- アクセス パッケージの作成時または表示時にアクセス拒否メッセージが表示され、カタログ作成者グループのメンバーである場合は、最初のアクセス パッケージを作成する前に [カタログを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create) する必要があります。

### リソース

- アプリケーションのロールはアプリケーション自体によって定義され、Microsoft Entra ID で管理されます。 アプリケーションにリソース ロールがない場合は、エンタイトルメント管理によってユーザーが **既定のアクセス** ロールに割り当てられます。

    Microsoft Entra 管理センターには、アプリケーションとして選択できないサービスのサービス プリンシパルが表示される場合もあります。 特に、 **Exchange Online** と **SharePoint Online** はサービスであり、ディレクトリにリソース ロールを持つアプリケーションではないため、アクセス パッケージに含めることはできません。 代わりに、グループ ベースのライセンスを使用して、それらのサービスへのアクセスを必要とするユーザーに対して適切なライセンスを確立します。
- 認証に関して個人用の Microsoft アカウント ユーザーのみをサポートし、ディレクトリ内の組織アカウントをサポートしないアプリケーションは、アプリケーション ロールを持たず、アクセス パッケージ カタログに追加することはできません。
- グループがアクセス パッケージ内のリソースになるためには、そのグループが Microsoft Entra ID で変更可能である必要があります。 オンプレミスの Active Directory に由来するグループは、その所有者またはメンバー属性を Microsoft Entra ID で変更できないため、リソースとして割り当てることができません。 配布グループとして Exchange Online に由来するグループも Microsoft Entra ID で変更できません。
- SharePoint Online のドキュメント ライブラリおよび個別のドキュメントはリソースとして追加できません。 代わりに、 [Microsoft Entra セキュリティ グループ](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)を作成し、そのグループとサイトロールをアクセス パッケージに含めます。SharePoint Online では、そのグループを使用してドキュメント ライブラリまたはドキュメントへのアクセスを制御します。
- アクセス パッケージで管理するリソースにユーザーが既に割り当てられている場合は、それらのユーザーが適切なポリシーに基づいてアクセス パッケージに割り当てられているかどうかを確認してください。 たとえば、既にユーザーがいるグループをアクセス パッケージに含めるとよいでしょう。 グループ内のこれらのユーザーが引き続きアクセスを必要とする場合、これらのユーザーは、アクセス パッケージに対する適切なポリシーを持ち、グループへのアクセスが不可能にならないようにする必要があります。 アクセス パッケージを割り当てるには、そのリソースを含むアクセス パッケージを要求するようにユーザーに依頼するか、またはアクセス パッケージにユーザーを直接割り当てます。 詳細については、「 [アクセス パッケージの要求と承認の設定を変更する」を](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy)参照してください。
- チームのメンバーを削除すると、Microsoft 365 グループからも削除されます。 チームのチャット機能から削除されるタイミングは遅れる場合があります。 詳細については、「 [グループ メンバーシップ](https://learn.microsoft.com/ja-jp/microsoftteams/office-365-groups#group-membership)」を参照してください。

### アクセス パッケージ

- アクセス パッケージまたはポリシーを削除しようとしたときに、アクティブな割り当てがあることを示すエラー メッセージが表示されるのに、割り当てがあるユーザーが表示されない場合は、最近削除されたユーザーに、割り当てがあるかどうかを確認してください。 ユーザーが削除されてから 30 日間は、ユーザー アカウントを復元できます。

### 外部ユーザー

- 外部ユーザーがアクセス パッケージへのアクセスを要求する場合は、アクセス パッケージの **[マイ アクセス ポータル] リンク** を使用していることを確認します。 詳細については、「 [アクセス パッケージを要求するための共有リンク」を](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-settings)参照してください。 外部ユーザーが **myaccess.microsoft.com** にアクセスするだけで、個人用アクセス ポータルの完全なリンクを使用していない場合は、組織内ではなく、自分の組織で利用できるアクセス パッケージが表示されます。
- 外部ユーザーがアクセス パッケージへのアクセスを要求できない場合、またはリソースにアクセスできない場合は、 [外部ユーザーの設定](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users#settings-for-external-users)を確認してください。
- ディレクトリにまだサインインしていない新規の外部ユーザーが、SharePoint Online サイトを含むアクセス パッケージを受信した場合、このアクセス パッケージは、ユーザーのアカウントが SharePoint Online でプロビジョニングされるまで、配信未完了として表示されます。 共有設定の詳細については、「 [SharePoint Online の外部共有設定を確認](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users#review-your-sharepoint-online-external-sharing-settings)する」を参照してください。

### リクエスト

- ユーザーがアクセス パッケージへのアクセスを要求する場合は、アクセス パッケージの **[マイ アクセス ポータル] リンク** を使用していることを確認します。 詳細については、「 [アクセス パッケージを要求するための共有リンク」を](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-settings)参照してください。
- プライベート モードやシークレット モードに設定されたブラウザーを使用してマイ アクセス ポータルを開くと、サインインの動作に支障が生じる場合があります。 マイ アクセス ポータルにアクセスする際は、ブラウザーのプライベート モードやシークレット モードは使用しないようお勧めします。
- ディレクトリにまだ存在しないユーザーが、アクセス パッケージを要求するためにマイ アクセス ポータルにサインインする場合は、ユーザーが組織の自分のアカウントを使用して認証するようにします。 組織のアカウントとして使用できるのは、リソース ディレクトリ内のアカウント、またはアクセス パッケージのいずれかのポリシーに含まれるディレクトリ内のアカウントです。 ユーザーのアカウントが組織のアカウントでない場合、または認証するディレクトリがポリシーに含まれていない場合は、ユーザーにアクセス パッケージが表示されません。 詳細については、「 [アクセス パッケージへのアクセスを要求する」を](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-access)参照してください。
- リソース ディレクトリへのサインインがブロックされた場合、ユーザーはマイ アクセス ポータルでアクセスを要求できなくなります。 ユーザーがアクセスを要求できるようにするには、ユーザーのプロファイルからサインインのブロックを削除する必要があります。 サインイン ブロックを削除するには、Microsoft Entra 管理センターで **Entra ID**&gt;**Users** を参照し、ユーザーを選択し、[ **プロパティの編集**] を選択して **、[設定]** セクションを選択し、[ **アカウントが有効]** チェック ボックスをオンにします。 詳細については、「 [Microsoft Entra ID を使用してユーザーのプロファイル情報を追加または更新](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-user-profile-info)する」を参照してください。 [条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-users-groups#exclude-users)が原因でユーザーがブロックされたかどうかを確認することもできます。
- マイ アクセス ポータルで、ユーザーが要求者と承認者の両方である場合、[ **承認]** ページにアクセス パッケージの要求は表示されません。 これは、ユーザーが自分の要求を承認できないようにするための動作です。 ユーザーが要求しているアクセス パッケージで、別の承認者がポリシーに基づいて構成されていることを確めてください。 詳細については、「 [アクセス パッケージの要求と承認の設定を変更する」を](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy)参照してください。

#### 要求の配信エラーを表示する

1. 少なくとも [ID ガバナンス管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者、アクセス パッケージ マネージャー、アクセス パッケージ割り当てマネージャーがあります。
2. **ID ガバナンス**&gt;**権利管理**&gt;**アクセス パッケージ**を参照します。
3. [ **要求] を選択します**。
4. 表示する要求を選択します。

    要求に配信エラーがある場合、要求の状態は **配信不能** または **部分的に配信されます**。

    配信エラーが発生した場合、配信エラーの数が要求の詳細ウィンドウに表示されます。
5. 数を選択して要求のすべての配信エラーを表示します。

#### 要求を再処理する

アクセス パッケージの再処理要求をトリガーした後にエラーが発生した場合は、システムで要求が再処理されるまで待つ必要があります。 システムでは数時間にわたって再処理が複数回試行されるため、この間に再処理を強制することはできません。

再処理できるのは、状態が [ **配信に失敗しました** ] または [ **一部配信** 済み] で、完了日が 1 週間未満の要求のみです。 それ以外の場合、 **再処理** ボタンは淡色表示されます。

[Image: [再処理] ボタンが淡色表示]

- 試用期間中にエラーが修正されると、要求の状態が **[配信中]** に変わります。 要求の再処理は、ユーザーが追加の操作を行わなくても実行されます。
- 試用期間中にエラーが修正されなかった場合、要求の状態は **[配信に失敗しました** ] または [ **一部配信済み**] になります。 その後、 **再処理** ボタンを使用できます。 要求の再処理には 7 日間かかります。

1. 少なくとも [ID ガバナンス管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者、アクセス パッケージ マネージャー、アクセス パッケージ割り当てマネージャーがあります。
2. アクセス パッケージを開くには、**ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**アクセス パッケージ**を参照します。
3. [ **要求] を選択します**。
4. 再処理する要求を選択します。
5. 要求の詳細ウィンドウで、[ **要求の再処理**] を選択します。

    [Image: 失敗した要求を再処理する]

#### 保留中の要求をキャンセルする

キャンセルできるのは、まだ配信されていないか、配信に失敗した保留中の要求のみです。 それ以外の場合、 **キャンセル** ボタンは淡色表示されます。

1. 少なくとも [ID ガバナンス管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。

    ヒント

    このタスクを完了できる他の最小特権ロールには、カタログ所有者、アクセス パッケージ マネージャー、アクセス パッケージ割り当てマネージャーがあります。
2. アクセス パッケージを開くには、**ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**アクセス パッケージ**を参照します。
3. [ **要求] を選択します**。
4. キャンセルする要求を選択します。
5. 要求の詳細ウィンドウで、[ **要求の取り消し**] を選択します。

### 自動割り当てポリシー

- 各自動割り当てポリシーでは、ルールのスコープに最大 15,000 人のユーザーを含めることができます。 それ以外のユーザーには、たとえルールの対象者であってもアクセス権が割り当てられない可能性があります。

### 複数のポリシー

- エンタイトルメント管理は、最小限の特権のベスト プラクティスを踏襲しています。 該当するポリシーが複数あるアクセス パッケージへのアクセスをユーザーが要求すると、より厳しい (つまり具体的な) ポリシーが汎用的なポリシーよりも確実に優先されるように働き掛けるロジックがエンタイトルメント管理によって含められます。 ポリシーが汎用的である場合、要求元にポリシーが表示されないか、またはより厳しいポリシーが自動的に選択されます。
- たとえば、ディレクトリのユーザー向けに 2 つのポリシーを含んだアクセス パッケージを考えてみてください。要求元には、その両方のポリシーが当てはまるとします。 1 つ目は、要求元を含む特定のユーザーを対象としたポリシーです。 2 番目のポリシーは、ディレクトリ内のすべてのユーザーに対して行われます。 このシナリオでは、1 つ目のポリシーの方が厳しいため、要求元に対してそれが自動的に選択されます。 要求元には、2 つ目のポリシーを選択するオプションは提供されません。
- 複数のポリシーが該当する場合、自動的に選択されるポリシー (要求元に表示されるポリシー) は、次の優先順位ロジックに基づいて決定されます。

    | ポリシーの優先度 | 範囲 |
    | --- | --- |
    | P1 | ディレクトリ内の特定のユーザーとグループ、または接続されている特定の組織 |
    | P2 | ディレクトリ内のすべてのメンバー (ゲストを除く) |
    | P3の | ディレクトリ内のすべてのユーザー (ゲストを含む) または接続されている特定の組織 |
    | P4 | 構成済みで接続されているすべての組織またはすべてのユーザー (接続されているすべての組織 + 新しい外部ユーザー) |

    より優先度の高いカテゴリにポリシーが属している場合、それよりも優先度が低いカテゴリは無視されます。 要求側に同じ優先度の複数のポリシーを表示する方法の例については、「ポリシーの [選択](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-access#select-a-policy)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/entitlement-management-verified-id-settings"} -->
## エンタイトルメント管理でアクセス パッケージの確認済み ID 設定を構成する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-verified-id-settings
- Service: entra-id-governance / entitlement-management
- Article date: 2024-10-17
- Summary: エンタイトルメント管理でアクセス パッケージの確認済み ID 設定を構成する方法について説明します。

アクセス パッケージ ポリシーを設定するときに、管理者は、それがディレクトリ内のユーザー、接続された組織、任意の外部ユーザーのいずれに対するものなのかを指定できます。 エンタイトルメント管理は、アクセス パッケージを要求するユーザーがポリシーのスコープ内であるかどうかを判断します。

要求プロセス中に、トレーニングの認定資格、仕事の承認、市民権の状態などの追加の ID 証明の提示をユーザーに求めることがあります。 アクセス パッケージ マネージャーは、要求元に、信頼された発行者からの資格情報を含む確認済み ID を提示するように要求できます。 これで、ユーザーが資格情報を提示し、アクセス パッケージ要求を送信した時点で、ユーザーの検証可能な資格情報が検証されたかどうかを承認者がすぐに確認できます。

アクセス パッケージ マネージャーは、アクセス権を要求する既存のポリシーを編集するか、新しいポリシーを追加することで、アクセス パッケージに対する確認済み ID 要件をいつでも含めることができます。

この記事では、アクセス パッケージの確認済み ID 要件設定を構成する方法について説明します。

### Prerequisites

開始する前に、 [Microsoft Entra Verified ID サービス](https://learn.microsoft.com/ja-jp/entra/verified-id/decentralized-identifier-overview)を使用するようにテナントを設定する必要があります。 その方法の詳細な手順については、「 [Microsoft Entra Verified ID 用にテナントを構成](https://learn.microsoft.com/ja-jp/entra/verified-id/verifiable-credentials-configure-tenant-quick)する」を参照してください。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID Governance または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、 [Microsoft Entra ID ガバナンス のライセンスの基礎を](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)参照してください。

### 確認済み ID 要件があるアクセス パッケージを作成する

アクセス パッケージに確認済み ID 要件を追加するには、アクセス パッケージの [要求] タブから開始する必要があります。確認済み ID 要件を新しいアクセス パッケージに追加するには、次の手順に従います。

**前提条件ロール**: グローバル管理者

Note

ID ガバナンス管理者、ユーザー管理者、カタログ所有者、または アクセス パッケージ マネージャーは、まもなくアクセス パッケージに確認済み ID 要件を追加できるようになります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**権限管理**&gt;**アクセス パッケージ**を参照します。
3. [アクセス パッケージ] ページで、[ **+ 新しいアクセス パッケージ**] を選択します。
4. [要求] タブ **で** 、[ **必須の検証済み ID** ] セクションまでスクロールします。
5. [ **+ 発行者の追加]** を選択し、Microsoft Entra Verified ID ネットワークから発行者を選択します。 ユーザーに独自の資格情報を発行する場合は、「 [アプリケーションから Microsoft Entra Verified ID 資格情報を発行する](https://learn.microsoft.com/ja-jp/entra/verified-id/verifiable-credentials-configure-issuer)」を参照してください。 [Image: Microsoft Entra の検証済み ID の発行者を選択します。]
6. 要求プロセス中にユーザーに提示する **資格情報の種類** を選択します。 [Image: Microsoft Entra 検証済み ID の資格情報の種類のスクリーンショット。]

    Note

    1 つの発行者から複数の資格情報の種類を選択した場合、ユーザーは選択したすべての種類の資格情報を提示する必要があります。 同様に、複数の発行者を含める場合、ユーザーはポリシーに含める各発行者の資格情報を提示する必要があります。 さまざまな発行者から異なる資格情報を提示するオプションをユーザーに提供するには、受け入れる発行者/資格情報の種類ごとに個別のポリシーを構成します。
7. [ **追加]** を選択して、検証済みの ID 要件をアクセス パッケージ ポリシーに追加します。
8. ユーザーに顔チェックを完了させる場合は、[ **顔チェックを必須にする**] を選択します。 これにより、アクセス パッケージを要求するユーザーに、検証済み ID に保存されている写真に対して、プライバシーに準拠したリアルタイムのセルフ チェックを実行するよう求められます。 チェック ボックスをオンにすると、ID の写真にマップされる要求名を選択するように求められます。 Face Check の詳細については、「 [Use Face Check with Microsoft Entra Verified ID](https://learn.microsoft.com/ja-jp/entra/verified-id/using-facecheck)」を参照してください。

    [Image: [顔チェックが必要] オプションのスクリーンショット。]
9. 残りの設定の構成が完了したら、[確認 **と作成** ] タブで選択内容を確認できます。このアクセス パッケージ ポリシーのすべての検証済み ID 要件は、[ **検証済み ID** ] セクションで確認できます。 [Image: 検証済み ID の一覧のスクリーンショット。]

### 確認済み ID 要件があるアクセス パッケージを要求する

確認済み ID 要件があるアクセス パッケージを構成すると、ポリシーのスコープ内にあるエンド ユーザーは、マイ アクセス ポータルを使用してアクセスを要求できます。 同様に、承認者は、承認要求をレビューするときに要求者によって提示された VC の要求を確認できます。

要求者の手順は次のとおりです。

1. [`myaccess.microsoft.com`](https://myaccess.microsoft.com) に移動してサインインします。
2. アクセスを要求するアクセス パッケージを検索し (一覧のパッケージを参照するか、ページの上部にある検索バーを使用できます)、[ **要求**] を選択します。
3. アクセス パッケージで検証済み ID を提示する必要がある場合は、次のように灰色の情報バナーが表示されます。 [Image: アクセス パッケージの現在の検証済み ID オプションのスクリーンショット。]
4. [ **アクセスの要求] を**選択します。 ここで、QRコードが表示されます。 スマートフォンを使用して QR コードをスキャンします。 これにより Microsoft Authenticator が起動し、資格情報の共有が求められます。 [Image: 検証済み ID に QR コードを使用するスクリーンショット。]
5. アクセス パッケージに Face Check が必要な場合、要求するユーザーは、検証済み ID に保存されている写真に対してリアルタイムのセルフ チェックを実行する必要があります。 Face Check は、機密 ID データではなく、一致する結果のみを共有することで、ユーザーのプライバシーを保護します。
6. 資格情報を共有すると、マイ アクセスによって自動的に要求プロセスの次の手順に進みます。

### エンタイトルメント管理と検証済み ID セキュリティ パートナーの統合

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**権限管理**&gt;**アクセス パッケージ**を参照します。
3. [アクセス パッケージ] ページで、[ **+ 新しいアクセス パッケージ**] を選択します。
4. [要求] タブ **で** 、[ **必須の検証済み ID** ] セクションまでスクロールします。
5. [ **+ 発行者の追加]** を選択し、Microsoft Entra Verified ID ネットワークから発行者を選択します。 ユーザーに独自の資格情報を発行する場合は、「 [アプリケーションから Microsoft Entra Verified ID 資格情報を発行する](https://learn.microsoft.com/ja-jp/entra/verified-id/verifiable-credentials-configure-issuer)」を参照してください。 [Image: アクセス パッケージ内の発行者の検証済み ID のスクリーンショット。]
6. 手順 1 では、"発行者の種類を選択する" という 2 種類の発行者を選択できます。

    1. Microsoft Entra Verified ID Network - この Microsoft Entra Verified ID は、ドロップダウン リストから任意の検証済み ID 発行者を選択したものである可能性があります [Image: 検証済み ID ネットワーク発行者オプションのスクリーンショットです。]
    2. セキュリティ パートナー (サード パーティ プロバイダー) - [Microsoft Security Store](https://learn.microsoft.com/ja-jp/security/store/what-is-security-store) 統合を介して利用できる政府機関 ID 検証パートナー。 これは、選択した IDV パートナーの 1 つから発行された検証済み ID のプレゼンテーションを追加できるオプションを構成するための簡単な選択です。 次のドロップダウン選択では、管理者は[Image: 、発行者のセキュリティ パートナーリストのパートナースクリーンショット]からそれぞれの ID 検証オファーを購入する必要があります。
7. 「手順 1: 発行者を検索して、要求プロセス中にユーザーに提示する **資格情報の種類** を選択する」の 「Microsoft Entra Verified ID Network」の選択。
8. 手順 1 で [セキュリティ パートナー (サード パーティ プロバイダー)]を選択した場合、管理者が初めて発行者を選択する場合は、オファーを購入するための Security Store へのリンクが表示されます。 [Image: 検証パートナー オプションのスクリーンショット。]

    Note

    1 つの発行者から複数の資格情報の種類を選択した場合、ユーザーは選択したすべての種類の資格情報を提示する必要があります。 同様に、複数の発行者を含める場合、ユーザーはポリシーに含める各発行者の資格情報を提示する必要があります。 さまざまな発行者から異なる資格情報を提示するオプションをユーザーに提供するには、受け入れる発行者/資格情報の種類ごとに個別のポリシーを構成します。
9. [ **追加]** を選択して、検証済みの ID 要件をアクセス パッケージ ポリシーに追加します。
10. ユーザーに顔チェックを完了させる場合は、[ **顔チェックを必須にする**] を選択します。 これにより、アクセス パッケージを要求するユーザーに、検証済み ID に保存されている写真に対して、プライバシーに準拠したリアルタイムのセルフ チェックを実行するよう求められます。 チェック ボックスをオンにすると、ID の写真にマップされる要求名を選択するように求められます。 Face Check の詳細については、「 [Microsoft Entra Verified ID で Face Check を使用し、大規模な高保証検証のロックを解除する](https://learn.microsoft.com/ja-jp/entra/verified-id/using-facecheck)」を参照してください。 [Image: 確認済み ID を持つ顔チェック オプションのスクリーンショット。]
11. このアクセス パッケージ ポリシーの残りの設定を完了します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/governance-custom-alerts"} -->
## Identity Governance のカスタム アラート - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/governance-custom-alerts
- Service: entra-id-governance
- Article date: 2025-12-03
- Summary: この記事では、Microsoft Entra ID ガバナンスでカスタム アラートを作成する方法について説明します。

Azure Active Directory Identity Governance を使うと、組織内のユーザーがアクションを実行する必要があるとき (たとえば、リソースへのアクセス要求を承認する)、またはビジネス プロセスが正常に機能していないとき (たとえば、新規採用者がプロビジョニングされていない場合) に、ユーザーに簡単にアラートを送ることができます。

次の表は、Microsoft Entra ID Governance が提供する標準的な通知の一部を示しています。 これには、組織内のターゲット ペルソナ、アラートの方法、およびアラートを受け取るタイミングが含まれます。

**既存の標準通知のサンプル**

| ペルソナ | アラート方法 | 適時性 | アラートの例 |
| --- | --- | --- | --- |
| 最終利用者 | Email | 議事録 | このアクセス要求を承認または拒否する必要があります。要求したアクセスが承認されたら、新しいアプリを使用します。[詳細情報](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-process#email-notifications-table) |
| 最終利用者 | Email | 日 | 要求したアクセスは来週期限切れになります。更新してください。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-process#email-notifications-table) |
| 最終利用者 | Email | 日 | Woodgroveへようこそ、こちらがあなたの一時的なアクセス パスです。 [詳細情報。](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#generate-temporary-access-pass-and-send-via-email-to-users-manager) |
| ヘルプ デスク | ServiceNow | 議事録 | ユーザーをレガシ アプリケーションに手動でプロビジョニングする必要があります。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-ticketed-provisioning) |
| IT 運用 | Email | 時間 | 新しく採用された従業員が Workday からインポートされません。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) |

### カスタム アラート通知

Azure AD Identity Governance によって提供される標準通知に加えて、組織はニーズに合わせてカスタム アラートを作成できます。

Azure AD Identity Governance サービスによって実行されるすべてのアクティビティは、Microsoft Entra の[監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)に記録されます。 Azure Monitor Log Analytics ワークスペースにログをプッシュすることで、組織はカスタム アラートを作成できます。

次のセクションでは、ユーザーが Azure AD Identity Governance と Azure Monitor を統合して作成できるカスタム アラートの例を示します。 Azure Monitor を使用すると、組織は生成されるアラート、アラートの受信者、アラートの受信方法 (電子メール、SMS、[ヘルプ デスク チケット](https://learn.microsoft.com/ja-jp/azure/azure-monitor/alerts/itsm-connector-secure-webhook-connections-azure-configuration) など) をカスタマイズできます。

| 機能 | アラートの例 |
| --- | --- |
| アクセス レビュー | アクセス レビューが削除されたときに、IT 管理者に警告します。 |
| 権利管理 | ユーザーがアクセス パッケージを使わずにグループに直接追加されたときに、IT 管理者に警告します。 |
| 権利管理 | 新しい接続されている組織が追加されたときに、IT 管理者に警告します。 |
| 権利管理 | カスタム拡張機能が失敗したときに、IT 管理者に警告します。 |
| 権利管理 | 承認を必要とせずにエンタイトルメント管理アクセス パッケージの割り当てポリシーが作成または更新されたときに、IT 管理者に警告します。 |
| ライフサイクル ワークフロー | 特定のワークフローが失敗したときに、IT 管理者に警告します。 |
| マルチテナント コラボレーション | テナント間同期が有効にされたときに、IT 管理者に警告します |
| マルチテナント コラボレーション | テナント間アクセス ポリシーが有効にされたときに、IT 管理者に警告します |
| Privileged Identity Management | PIM アラートが無効にされたときに、IT 管理者に警告します。 |
| Privileged Identity Management | PIM の外部でロールが付与されたときに IT 管理者に警告します。 |
| プロビジョニング | 過去 1 日にプロビジョニングの失敗が急増した場合は、IT 管理者に警告します。 |
| プロビジョニング | 誰かがプロビジョニング構成を開始、停止、無効化、再起動、または削除したときに、IT 管理者に警告します。 |
| プロビジョニング | プロビジョニング ジョブが検疫状態になったときに IT 管理者に通知します。 |

### アクセスレビュー

#### [アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)が削除されたときに IT 管理者に警告します。

*クエリ*

```
AuditLogs
| where ActivityDisplayName == "Delete access review"
```

### 権利管理

#### [アクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)を使用せずにユーザーがグループに直接追加されたときに、IT 管理者に警告します。

*クエリ*

```
AuditLogs
| where parse_json(tostring(TargetResources[1].id)) in ("InputGroupID", "InputGroupID")
| where ActivityDisplayName == "Add member to group"
| extend ActorName = tostring(InitiatedBy.app.displayName)
| where ActorName != "Azure AD Identity Governance - User Management"
```

#### 新しい[接続された組織](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-organization)が作成されたときに IT 管理者に警告します。 この組織のユーザーは、すべての接続されている組織で利用できるリソースへのアクセスを要求できるようになります。

*クエリ*

```
AuditLogs
| where ActivityDisplayName == "Create connected organization"
| mv-expand AdditionalDetails
| extend key = AdditionalDetails.key, value = AdditionalDetails.value
| extend tostring(key) == "Description"
| where key == "Description"
| parse value with * "\n" TenantID 
| distinct TenantID
```

#### エンタイトルメント管理の [カスタム拡張機能](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logic-apps-integration) が失敗した場合に IT 管理者に警告します。

*クエリ*

```
AuditLogs
| where ActivityDisplayName == "Execute custom extension"
| where Result == "success"
| mvexpand TargetResources 
| extend  CustomExtensionName=TargetResources.displayName
| where CustomExtensionName in ('<input custom extension name>', '<input custom extension name>')
```

#### エンタイトルメント管理アクセス パッケージ割り当てポリシーが [承認](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy)を必要とせずに作成または更新された場合に、IT 管理者に警告します。

*クエリ*

```
AuditLogs
| where ActivityDisplayName in ("Create access package assignment policy", "Update access package assignment policy")
| extend AdditionalDetailsParsed = parse_json(AdditionalDetails)
| mv-expand AdditionalDetailsParsed
| extend Key = tostring(AdditionalDetailsParsed.key), Value = tostring(AdditionalDetailsParsed.value)
| summarize make_set(Key), make_set(Value) by ActivityDisplayName, CorrelationId
| where set_has_element(set_Key, "IsApprovalRequiredForAdd") and set_has_element(set_Value, "False")
| where set_has_element(set_Key, "SpecificAllowedTargets") and not(set_has_element(set_Value, "None"))
```

### ライフサイクル ワークフロー

#### 特定の [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows) が失敗した場合に IT 管理者に警告します。

*クエリ*

```
AuditLogs
| where Category == "WorkflowManagement"
| where ActivityDisplayName in ('On-demand workflow execution completed', 'Scheduled workflow execution completed')
| where Result != "success"
| mvexpand TargetResources 
| extend  WorkflowName=TargetResources.displayName
| where WorkflowName in ('input workflow name', 'input workflow name')
| extend WorkflowType = AdditionalDetails[0].value 
| extend DisplayName = AdditionalDetails[1].value 
| extend ObjectId = AdditionalDetails[2].value 
| extend UserCount = AdditionalDetails[3].value 
| extend Users = AdditionalDetails[4].value 
| extend RequestId = AdditionalDetails[5].value 
| extend InitiatedBy = InitiatedBy.app.displayName 
| extend Result = Result 
| project WorkflowType, DisplayName, ObjectId, UserCount, Users, RequestId, Id, Result,ActivityDisplayName
```

アラート ロジック

- 基準: 結果の数
- 演算子: 等しい
- しきい値: 0

### マルチテナント コラボレーション

#### 新しい[テナント間アクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)が作成されたときに IT 管理者に警告します。 これにより、組織は、新しい組織との関係が形成されたことを検出できます。

*クエリ*

```
AuditLogs
| where OperationName == "Add a partner to cross-tenant access setting"
| where parse_json(tostring(TargetResources[0].modifiedProperties))[0].displayName == "tenantId"
| extend initiating_user=parse_json(tostring(InitiatedBy.user)).userPrincipalName
| extend source_ip=parse_json(tostring(InitiatedBy.user)).ipAddress
| extend target_tenant=parse_json(tostring(TargetResources[0].modifiedProperties))[0].newValue
| project TimeGenerated, OperationName,initiating_user,source_ip, AADTenantId,target_tenant
| project-rename source_tenant= AADTenantId
```

#### 管理者は、[インバウンド テナント間同期ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)が "true" に設定されている場合にアラートを受け取ることができます。 これにより、貴社は他の組織がテナントにIDを同期する承認を受けた時を検出することができます。

*クエリ*

```
AuditLogs
| where OperationName == "Update a partner cross-tenant identity sync setting"
| extend a = tostring(TargetResources)
| where a contains "true"
| where parse_json(tostring(TargetResources[0].modifiedProperties))[0].newValue contains "true"
```

アラート ロジック

### 特権ID管理

#### 特定の [PIM セキュリティ アラート](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts) が無効になった場合は、IT 管理者に警告します。

*クエリ*

```
AuditLogs
| where ActivityDisplayName == "Disable PIM alert"
```

#### PIM の外部のロールにユーザーが追加されたときに IT 管理者に警告する

次のクエリは templateId に基づいています。 テンプレート ID の一覧は[こちら](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)で確認できます。

*クエリ*

```
AuditLogs
| where ActivityDisplayName == "Add member to role"
| where parse_json(tostring(TargetResources[0].modifiedProperties))[2].newValue in ("\"INPUT GUID\"")
```

### プロビジョニング

**過去 1 日にプロビジョニングの失敗が急増した場合は、IT 管理者に警告します。** ログ分析でアラートを構成する場合は、集計の粒度を 1 日に設定します。

*クエリ*

```
AADProvisioningLogs
| where JobId == "<input JobId>"
| where resultType == "Failure"
```

アラート ロジック

- 基準: 結果の数
- 演算子: より大きい
- しきい値: 10

#### 誰かがプロビジョニング構成を開始、停止、無効化、再起動、または削除したときに、IT 管理者に警告します。

*クエリ*

```
AuditLogs
| where ActivityDisplayName in ('Add provisioning configuration','Delete provisioning configuration','Disable/pause provisioning configuration', 'Enable/restart provisioning configuration', 'Enable/start provisioning configuration')
```

#### プロビジョニング ジョブが[検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)になったときに IT 管理者に通知します

*クエリ*

```
AuditLogs
| where ActivityDisplayName == "Quarantine"
```

**次のステップ**

- [Azure Monitor ログ分析を使用して Microsoft Entra アクティビティ ログを分析](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-analyze-activity-logs-log-analytics)
- [Azure Monitor ログでクエリの使用を開始する](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/get-started-queries)
- [Azure ポータルでアラートグループを作成および管理する](https://learn.microsoft.com/ja-jp/azure/azure-monitor/alerts/action-groups)
- [Microsoft Entra ID 用のログ分析ビューをインストールして使用する](https://learn.microsoft.com/ja-jp/azure/azure-monitor/visualize/workbooks-view-designer-conversion-overview)
- [Azure Monitor におけるエンタイトルメント管理でのログとレポートのアーカイブ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logs-and-reporting)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/governance-dashboard"} -->
## ID ガバナンス ダッシュボード - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/governance-dashboard
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: この記事では、新しい Identity Governance ダッシュボードを使用する方法について説明します

この記事では、Microsoft Identity Governance ダッシュボードの使用方法に関するガイダンスを提供します。

### ダッシュボードについて

Microsoft Identity Governance ダッシュボードは、テナントで構成されている ID ガバナンスと管理 (IGA) のさまざまな機能に関する使用状況の情報を検出します。 そして、Identity Governance の現在の状態が一目で分かるビューと、アクションを実行できるボタンおよび機能のドキュメントにすばやくアクセスできるリンクを提供します。

### ダッシュボードの利用

Microsoft は Identity Governance の実装が時間のかかる作業であることを理解しており、お客様はこの過程のさまざまな段階にいる可能性があります。

- 作業を始めたばかりの場合は、このダッシュボードを使って IT ランドスケープの複雑さを評価します。 テナント内のユーザーとゲストの数を明らかにします。 テナント内のビジネス アプリと特権ロールを検出し、Microsoft Identity Governance によって提供される機能を確認して、セキュリティとコンプライアンスのニーズに対応する実装計画をまとめます。
- 特定のガバナンス機能を既にデプロイしている場合は、ダッシュボードを使ってガバナンスの自動化の対象範囲を把握し、実装のギャップを見つけます。 たとえば、[エンタイトルメント管理](https://go.microsoft.com/fwlink/?linkid=2210375)を使って既定でのアクセス権の付与は自動化していても、定期的な[アクセス レビュー](https://go.microsoft.com/fwlink/?linkid=2211313)を設定していない可能性があります。 ダッシュボードのアクション呼び出しリンクを使って、ID ガバナンス態勢をさらに改善します。

### ダッシュボードに表示されるデータ

ダッシュボードにアクセスするには、Microsoft Entra 管理センターにログインし、"Identity Governance" の下にある [ダッシュボード] ブレードを選びます。 ダッシュボードのエクスペリエンスは、次のメイン コンポーネントで構成されています。

- **一目でわかるカード**: これらのカードでは、従業員ユーザー、ゲスト ユーザー、特権 ID、アプリケーション アクセス ガバナンスの観点から、テナントで起きていることについての大まかな分析情報が提供されます。 一目でわかるカードのナビゲーション リンクは、Identity Governance のクイック スタート ガイドとチュートリアルを指しています。
- **ID ガバナンスの状態**: このビジュアルでは、従業員、ゲスト、ビジネス アプリ、グループ、特権ロールの数に関する ID ランドスケープが示されます。 そこでは、これらのエンティティのガバナンスを向上させるために構成されている Microsoft Identity Governance のさまざまな機能セットが明確に示されています。 機能が構成されていない場合は、[今すぐ構成] オプションを使って、その機能の構成ランディング ブレードを開くことができます。
- **チュートリアル**: このセクションには、簡単にアクセスできるように ID ガバナンスの一般的なユース ケースのチュートリアルが含まれています。
- **ハイライト**: このセクションの内容を使って、Identity Governance の最新の機能に関する情報を入手し、お客様が Identity Governance を使ってセキュリティとコンプライアンスの態勢を改善する方法を学習します。

注

ダッシュボードの Graph API は、委任されたアクセス許可モデルを使って、ログインしているユーザーのコンテキストで動作します。 完全に正確な情報がダッシュボードに表示されるよう、少なくともグローバル閲覧者ロールを使用することをお勧めします。

### ダッシュボードのエラーのトラブルシューティング

ダッシュボードには、次の 2 種類のエラーが表示される場合があります。

- **サービス エラー**: このエラーは、バックエンド サービス エラーのためにダッシュボードがデータを取得できなかったことを示します。 サービス エラーが間欠的である可能性があります。 ダッシュボードを最新の情報に更新して、問題が自動的に解決されるかどうかを確認してください。 引き続き問題が発生する場合は、Microsoft サポートにお問い合わせください。
- **アクセス許可エラー**: このエラーは、不十分なアクセス許可、またはデータ アクセスやライセンスの問題のために、ダッシュボードがデータを取得できなかったことを示します。 ログインしているユーザーに割り当てられているロールを調べて、テナントに適切なライセンスがあることを確認します。 完全に正確な情報がダッシュボードに表示されるよう、少なくともグローバル閲覧者ロールを割り当てることをお勧めします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/governance-service-limits"} -->
## Microsoft Entra ID ガバナンス サービスの制限事項 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/governance-service-limits
- Service: entra-id-governance
- Article date: 2024-12-10
- Summary: この記事では、Microsoft Entra ID ガバナンス の各オファリングにおけるサービスの制限について詳しく説明します

この記事では、Microsoft Entra の一部である Microsoft Entra ID ガバナンス サービスの使用上の既定の制約について説明します。 Microsoft Entra のガバナンス固有ではないサービス制限の一覧については、「[Microsoft Entra のサービス制限事項と制約](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions)」を参照してください。

注

使用が、一覧表示されている既定の制約を超えた場合は、制限を増やすことができます。 既定のクォータを超えるためには、Microsoft サポートに連絡する必要があります。

### エンタイトルメント管理

ヒント

アクセス パッケージには複数のリソース ロールが含まれており、部門、職務、場所、プロジェクト、またはこれらの組み合わせに基づいてモデル化することをお勧めします。

| 機能 | 制限 |
| --- | --- |
| アクセス パッケージ | テナントあたり 20,000 |
| アクセス パッケージの割り当て - アクセス パッケージの割り当ては、特定のユーザーへのアクセス パッケージの割り当てです | テナントあたり 300,000 |
| 特定の自動割り当てポリシーからパッケージの割り当てにアクセスする | 自動割り当てポリシーあたり 15,000 |
| カタログ | テナントあたり 7,500 |
| 接続済み組織 | テナントあたり 2,500 |
| カスタム拡張機能 | テナントあたり 500 |
| ポリシー - アクセス パッケージの割り当てポリシーは、ユーザーがアクセス パッケージを要求または割り当てることができるポリシーを指定します | テナントあたり 25,000 |
| "アクセスを要求できるユーザー" 定義の一部として単一のポリシーで参照されている接続済み組織 | ポリシーごとに1,000 |
| "アクセスを要求できるユーザー" 定義の一部として、1 つのポリシーで明示的に参照されているユーザーとグループの数を組み合わせたもの。 | 1 ポリシーあたり500 |
| 要求 (3 か月以内) - アクセス パッケージの割り当て要求は、アクセス パッケージの割り当てを取得、更新、または削除するユーザーによって、またはユーザーに代わって作成されます。 これには、自動割り当てポリシーに対してシステムによって作成された要求が含まれます。 | テナントあたり 200,000 |

### ライフサイクル ワークフロー

| カテゴリ | 制限 |
| --- | --- |
| ワークフローの数 | テナントあたり 100 |
| タスクの数 | ワークフローあたり 25 |
| カスタム タスク拡張機能の数 | テナントあたり 100 |
| triggerAndScopeBasedConditions executionConditions の offsetInDays の範囲 | 180 日 |
| ワークフロー スケジュールのサイクル間隔 (時間) | 1 から 24 時間 |
| オンデマンド選択あたりのユーザー数 | 10 |
| カスタム タスク拡張機能の durationBeforeTimeout の範囲 | 30 分から 3 時間 |
| ワークフローごとの管理スコープ | 5 |

注

API を使用してワークフローを作成または更新する場合、offsetInDays の範囲は -180 から 180 日の間になります。 負の値は timeBasedAttribute の前に発生し、正の値は後で発生します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/guest-lifecycle-policies"} -->
## ライフサイクル ワークフローのゲスト ライフサイクル ポリシー - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/guest-lifecycle-policies
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-13
- Summary: ライフサイクル ワークフローでゲスト ライフサイクル ポリシーを構成して、外部コラボレーションを管理する方法について説明します。

Important

ゲスト ライフサイクル ポリシーは現在プレビュー段階です。 プレビュー機能は、サービス レベル アグリーメントなしで提供され、運用環境のワークロードには推奨されません。 一部の機能はサポートされていないか、機能が限られている可能性があります。 このプレビューは、パブリック クラウドで利用できます。

ライフサイクル ワークフローのゲスト ライフサイクル ポリシーは、大規模な外部コラボレーションの管理に役立ちます。 対象となる Microsoft Entra ゲスト ユーザーを定義し、ゲスト ライフサイクル シグナルを評価するルールを構成し、スポンサーまたはゲストに通知し、ポリシー要件を満たさなくなったアカウントを自動的に無効化または削除することができます。

ゲスト ライフサイクル ポリシーを使用すると、個々のワークフローを構築および管理する代わりに、大規模に適用されるビジネス ルールに集中できます。

### 主な利点

- **古いゲスト アクセスを減らす:** 通常の構成証明と非アクティブ制御は、アクセスが不要になった外部アカウントを削除するのに役立ちます。
- **一貫性のあるガバナンスを適用します。** テナント全体の既定値、属性とグループのスコープ、除外、ポリシーの優先順位により、予測可能な適用が提供されます。
- **ゲストの強制を自動化する:** 削除、無効化、または無効化を選択し、猶予期間後の削除を選択します。
- **スポンサーのアカウンタビリティの向上:** 実施アクションの前にスポンサーに通知します。
- **段階的な導入をサポートする:** 複数のポリシーと対象範囲を使用して、選択した母集団に制御を導入します。
- **運用の可視性を高めます。** 処理状態、スコープ内のユーザー、コンプライアンス状態、およびゲストに適用されるポリシーを確認します。

### 始める前の準備

#### ライセンス要件

ゲスト ライフサイクル ポリシーを構成するには、テナントに次のものが必要です。

- Microsoft Entra ID ガバナンス ライセンス。
- ゲスト アドオン メーターの接続Azureサブスクリプション。

Important

ゲスト ライフサイクル ポリシーでは、ゲスト ユーザーの月次アクティブ ユーザー (MAU) 課金モデルMicrosoft Entra ID ガバナンス使用されます。 課金対象のアクションとライセンス要件については、[ゲスト ユーザーのMicrosoft Entra ID ガバナンスライセンスに関する説明を](https://learn.microsoft.com/ja-jp/entra/id-governance/microsoft-entra-id-governance-licensing-for-guest-users)参照してください。

#### 役割と権限

ゲスト ライフサイクル ポリシーを管理するには、次のいずれかのロールが必要です。

- ライフサイクル ワークフロー管理者
- グローバル管理者
- グローバル リーダー (読み取り専用)

#### サポートされているゲスト ユーザーとデータ

- ポリシーは、ゲスト アカウントの作成方法や招待方法に関係なく、Microsoft Entra `userType` プロパティが `Guest` に設定されているユーザーに適用されます。
- 作成日スコープでは、ゲスト オブジェクトの `createdDateTime` プロパティが使用され、ファースト パーティ アプリケーションまたはその他の招待ソースを使用して作成されたゲストを含めることができます。
- 属性ベースのルールでは、プレビュー エクスペリエンスで公開されている属性に従って、サポートされている標準ユーザー プロパティ、オンプレミス拡張機能の属性 1 から 15、ディレクトリ拡張機能、およびカスタム セキュリティ属性を使用できます。
- グループの包含と除外では、直接メンバーシップが使用されます。 入れ子になったグループは評価されません。
- アクセス ベースの条件は、アクセス パッケージの割り当てデータによって異なります。

#### ポリシーの概念

| 任期 | Description |
| --- | --- |
| **Policy** | ゲスト スコープ条件、ポリシー ルール、ポリシー アクション、および通知のセット。 |
| **ルール** | スポンサー構成証明、最近のアクティビティ、アクセス パッケージの割り当て、最小スポンサー数、引き換え状態など、ゲストが満たす必要があることを示すライフサイクルシグナル。 |
| **Scope** | テナント全体のカバレッジ、作成日、属性、グループ、除外など、ポリシーが適用されるゲスト。 |
| **Precedence** | 複数のポリシーがゲストと一致する場合の競合の解決に使用される優先順位。 |
| **操作** | ゲストがポリシー要件を満たしていない場合に実行される自動適用アクション。 |

次の手順では、ゲスト ライフサイクル ポリシーを作成し、そのスコープとルールを定義し、強制アクションを選択し、その優先順位を設定する方法について説明します。 広範な適用を有効にする前に、ゲストの数が限られた構成を検証します。

### ゲスト ライフサイクル ポリシーを作成する

1. ゲスト ライフサイクル ポリシーを管理できるロールを使用して、[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **ID ガバナンス**&gt;**ライフ サイクル ワークフロー**&gt;**ライフ サイクル ポリシー**&gt;**Guest ライフサイクル ポリシー**&gt;**作成ポリシー**を参照します。
3. 一意のポリシー名と、目的のゲストの作成と目的を識別する説明を入力します。
4. ポリシーのスコープとルールを構成します。
5. ポリシー アクションを選択し、必要に応じて猶予期間を選択します。
6. 通知の動作を確認します。
7. 他のゲストライフサイクルポリシーがある場合は、ポリシーの優先順位を設定します。
8. 構成を確認し、ポリシーを作成し、評価と適用の準備ができたら有効化します。

ポリシーを保存すると、ライフサイクル ワークフローによってポリシーが定期的に評価され、ポリシー ルールに違反したゲストが識別され、ポリシー構成に従って適用サイクルが開始されます。

### ポリシー スコープを定義する

**[すべてのゲスト**] から幅広く開始するか、選択した人口にポリシーを絞り込むことができます。 **[すべてのゲスト**] を選択した場合、ポリシーには、招待元に関係なく、テナント内の既存のゲスト ユーザーが含まれます。

スコープ オプションは次のとおりです。

- 既存のゲストを含むすべてのゲスト。
- サポートされている属性に基づいて特定の条件に一致するゲスト。
- 指定したグループの直接メンバーであるゲスト。
- 指定したグループの直接メンバーを除くすべてのゲスト。 複数の除外グループを選択すると、選択したいずれかのグループの直接メンバーが除外されます。

Note

除外は慎重に使用してください。 1 つのポリシーから除外されたゲストは、他のポリシーのスコープと優先順位によっては、引き続き別のポリシーと一致する可能性があります。

### ポリシールールの設定

ポリシーには複数のルールを含めることができます。 ルールは、ゲストが準拠したままであるか、適用対象になるかを決定します。 運用環境でポリシーを有効にする前に、テスト環境で最終的なルールの組み合わせ動作を確認します。

| ポリシー規則 | デフォルト | 構成範囲 | Purpose |
| --- | --- | --- | --- |
| **スポンサー構成証明** | 90 日間 | 30～365日 | スポンサーは、ゲストがまだアクセス権を必要としていることを定期的に確認する必要があります。 スポンサーは、スポンサー付きゲストの [マイ アクセス] で構成証明 **を完了します**。 |
| **アクティビティの要件** | 90 日間 | 30 ~ 730 日 | 構成された期間内にゲストをアクティブにする必要があります。 テナントへのサインインなど、サービスが認識するアクティビティは、この要件を満たします。 |
| **アクセス パッケージの割り当ての有効期限** | N/A | 有効または無効にする | ゲストが最後のアクセス パッケージの割り当てを失った後、ゲストの無効化または削除を開始します。 この規則は、エンタイトルメント管理で管理対象としてマークされたユーザーだけでなく、すべてのゲスト ユーザーに適用されます。 詳細については、「 [エンタイトルメント管理で外部ユーザーのライフサイクルを構成する」を](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-manage-lifecycle)参照してください。 |
| **スポンサーの最小数** | 1 | 1~5人のスポンサー | ゲストには、少なくとも構成されたスポンサーの数が必要です。 グループ値のスポンサーは、1 つのスポンサーとしてカウントされます。 |
| **ゲストの引き換え** | 30 日間 | 1 ~ 30 日 | `externalUserState` プロパティが `PendingAcceptance` に設定されている招待されたゲストの削除を開始します。 |

### 適用を設定する

#### ポリシー アクション

適用期限までにゲストがポリシーを満たさない場合に実行するアクションを選択します。

- **削除:** ゲスト アカウントを削除します。
- **無効にする:** ゲスト アカウントを無効にする。
- **無効にしてから削除します。** すぐにアカウントを無効にし、猶予期間が 7 日から 90 日後に自動的に削除されます。 既定値は 30 日です。
- **猶予期間:** ゲストがポリシー規則に準拠していない場合は、少なくとも 1 日の猶予期間を構成してポリシー アクションを遅らせます。 既定は 7 日です。

#### Notifications

ライフサイクル ワークフローでは、強制適用中に最大 3 件の通知を送信できます。 通知イベントは、選択したポリシー アクションと猶予期間が構成されているかどうかによって異なります。

- **アクション前の通知:** ゲストが非準拠になり、猶予期間が構成されると、ライフサイクル ワークフローは適用前にゲストとスポンサーに通知します。
- **通知の無効化:** ライフサイクル ワークフローでは、ゲスト アカウントが無効になり、アクセスが取り消されると、スポンサーに直ちに通知します。
- **削除通知:** ライフサイクル ワークフローは、ゲスト アカウントが削除され、アクセスが取り消されると、スポンサーに直ちに通知します。

#### 複数のポリシーと優先順位

スコープと設定が異なる複数のゲスト ライフサイクル ポリシーを構成できます。 ポリシーの表示、有効化、無効化、削除、並べ替えを行うことができます。 1つのゲストに複数のポリシーが一致する場合、設定された優先度によってどのポリシーが適用されるかが決まります。

Important

ゲストは、一度に1つのポリシーにしか割り当てられません。 ゲストが複数のポリシーと一致する場合は、優先順位が最も高いポリシーが適用されます。 優先順位の低いポリシーは評価されません。

複数のポリシーを有効にする前に、最も具体的なポリシーまたは最も重要なポリシーを優先順位の上位に配置してください。

テナントには、最大 20 個のゲスト ライフサイクル ポリシーを設定できます。

### ポリシーを監視して管理する

#### ポリシーの処理とスコープを表示する

各保険契約ごとに、以下の内容を確認できます:

- スコープ内のゲストの数。
- スコープ内のゲストの一覧。
- 各ゲストのコンプライアンス状態 ( **準拠**、 **非準拠**、 **評価なし**など)。
- 選択したゲストに適用されるポリシー。

#### ポリシーの削除と復元

削除されたポリシーは論理的に削除され、復元できます。 復元されたポリシーは、以前の状態に関係なく無効になり、優先度が最も低くなります。 再度有効にする前に、スコープ、ルール、通知、アクション、および優先順位を確認してください。

### ゲスト ライフサイクル ポリシーの適用のしくみ

1. **スコープの評価:** ライフサイクル ワークフローは、ポリシーの包含ルールに一致し、その除外に一致しないゲスト ユーザーを識別します。
2. **条件の評価:** ライフサイクル ワークフローは、各ゲストのポリシー 規則を評価して、ゲストが準拠しているか非準拠であるかを判断します。
3. **適用:** ゲストが非準拠の場合、ライフサイクル ワークフローは構成されたアクションを実行します。 猶予期間が有効になっている場合は、猶予期間が終了したときに適用されます。
4. **通知:** ライフサイクル ワークフローは、該当する通知をスポンサーとゲストに送信します。
5. **状態の更新:** ライフサイクル ワークフローは、管理エクスペリエンスにおけるポリシー処理とゲスト コンプライアンスに関する情報を更新します。

ライフサイクル ワークフローは、有効なゲスト ライフサイクル ポリシーを 1 日に 1 回評価します。

### ゲスト ライフサイクル ポリシーの推奨事項

ゲスト ライフサイクル ポリシーをデプロイするときは、次のプラクティスを使用します。

- 限られたゲストの数から始めて、より広範な実装の前にポリシーのスコープと動作を確認します。
- 組織で完全な削除の前に復旧期間が必要な場合は、無効化機能の後に削除を使用します。
- リマインダーが目的の受信者に届くよう、ゲストにスポンサーが設定されていることを確認します。
- より限定的なポリシーを、より広範なポリシーより上に配置し、優先順位を変更するたびに重複するスコープを確認します。
- 各ポリシーの変更後に、処理状態、強制エラー、およびゲスト コンプライアンスを監視します。
- 除外を文書化し、例外が永続的なアンマネージド アクセスにならないように定期的に確認します。

### Troubleshooting

| 問題点 | 確認すべきこと |
| --- | --- |
| ゲストがスコープ内にない | オブジェクトが`userType``Guest`に設定されていることを確認します。 作成日ルール、属性ルール、および直接グループのルールを確認します。 明示的なユーザーとグループの除外を確認し、より優先度の高いポリシーを調べます。 |
| ポリシーはまだ開始されていません | ポリシーが有効であること、テナントがライセンスとゲスト メーターの要件を満たしていること、Lifecycle Workflows の管理対象スケジュールが実行されるだけの時間があったことを確認します。 |
| 想定外のポリシーが適用されています | 条件に一致するすべてのポリシーとその優先順位を確認します。 重複する対象への影響を評価してからのみ、対象のポリシーを上位に移動してください。 |
| スポンサーにメールが届きませんでした | スポンサーが割り当てられ、使用可能なメール属性があることを確認します。 スポンサーがいない場合は、ゲストが郵便物を受け取れることを確認します。 配信エラーと追加受信者の設定を確認します。 |
| 復元されたポリシーが動作していない | 復元されたポリシーは無効になり、最低優先順位に設定されます。 ポリシーを確認し、明示的に有効にします。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/how-to-lifecycle-workflow-sync-attributes"} -->
## ライフサイクル ワークフローの属性を同期する方法 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-sync-attributes
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: ライフサイクル ワークフローの属性の概要について説明します。

ワークフローには特定のタスクが含まれており、指定した実行条件に基づいてユーザーに対して自動的に実行できます。 自動ワークフローのスケジュールは、Microsoft Entra ID の employeeHireDate および employeeLeaveDateTime ユーザー属性に基づいてサポートされます。

ライフサイクル ワークフローを最大限に活用するには、ユーザー プロビジョニングを自動化し、関連するスケジュール属性を同期する必要があります。

### スケジュール関連の属性

次の表は、関連するスケジューリング (トリガー) 属性と、サポートされている同期の方法を示しています。

| 属性 | タイプ | HR インバウンド プロビジョニングでのサポート | Microsoft Entra Connect クラウド同期でのサポート | Microsoft Entra Connect 同期でのサポート |
| --- | --- | --- | --- | --- |
| 従業員採用日 | DateTimeOffset (日付と時刻のオフセット) | はい | はい | はい |
| 従業員退勤日時 | DateTimeOffset (日付と時刻のオフセット) | はい | はい | はい |

メモ

クラウド専用ユーザーに対して employeeLeaveDateTime を手動で設定するには、特別なアクセス許可が必要です。 詳細については、「[ユーザーの employeeLeaveDateTime プロパティを構成する」](https://learn.microsoft.com/ja-jp/graph/tutorial-lifecycle-workflows-set-employeeleavedatetime)を参照してください。

このドキュメントでは、オンプレミスの Microsoft Entra Connect クラウド同期または Microsoft Entra Connect からの必要な属性の同期を設定する方法について説明します。

メモ

Active Directory には、EmployeeHireDate または EmployeeLeaveDateTime に対応する属性はありません。 オンプレミスの AD から同期する場合は、AD で使用できる属性を確認する必要があります。 この属性は、文字列である必要があります。

### EmployeeHireDate と EmployeeLeaveDateTime の書式設定について

EmployeeHireDate と EmployeeLeaveDateTime には、特定の方法で書式設定する必要がある日付と時刻が含まれます。 つまり、式を使って、ソース属性の値を EmployeeHireDate または EmployeeLeaveDateTime が受け入れる書式に変換する必要がある場合があります。 次の表では、想定される書式の概要を示し、値の変換方法に関する式の例を示します。

| シナリオ | 式/書式 | ターゲット | 詳細情報 |
| --- | --- | --- | --- |
| Workday から Active Directory へのユーザー プロビジョニング | FormatDateTime([StatusHireDate], ,"yyyy-MM-ddzzz", "yyyyMMddHHmmss.fZ") | オンプレミスの AD の文字列属性 | [Workday の属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#below-are-some-example-attribute-mappings-between-workday-and-active-directory-with-some-common-expressions) |
| Active Directory ユーザー プロビジョニングに対する SuccessFactors | FormatDateTime([終了日付], ,"M/d/yyyy hh:mm:ss tt","yyyyMMddHHmmss.fZ") | オンプレミスの AD の文字列属性 | [SAP 成功要因の属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial) |
| Active Directory へのカスタム インポート | "yyyyMMddHHmmss.fZ" の形式である必要があります | オンプレミスの AD の文字列属性 | [他のレコード システムの属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app) |
| Microsoft Graph ユーザー API | "YYYY-MM-DDThh:mm:ssZ" の形式である必要があります | 従業員採用日と従業員退職日時 |  |
| Workday からの Microsoft Entra へのユーザー プロビジョニング | 直接マッピングを使用できます。 式は必要ありませんが、EmployeeHireDate と EmployeeLeaveDateTime の時刻部分を調整するために使用できます | 従業員採用日と従業員退職日時 |  |
| SuccessFactors から Microsoft Entra へのユーザー プロビジョニング | 直接マッピングを使用できます。 式は必要ありませんが、EmployeeHireDate と EmployeeLeaveDateTime の時刻部分を調整するために使用できます | 従業員採用日と従業員退職日時 |  |

式の詳細については、 [Microsoft Entra ID での属性マッピングの式の記述に関するリファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)を参照してください。

表の式の例では、SAP には endDate を、Workday には StatusHireDate を使用しています。 ただし、異なる属性を使うこともできます。

たとえば、StatusHireDate の代わりに StatusContinuousFirstDayOfWork を Workday に使用できます。 その場合、式は次のようになります。

`FormatDateTime([StatusContinuousFirstDayOfWork], , "yyyy-MM-ddzzz", "yyyyMMddHHmmss.fZ")`

次の表では、推奨される属性とそのシナリオの推奨事項の一覧を示します。

| HR 属性 | HR システム | シナリオ | Microsoft Entra 属性 |
| --- | --- | --- | --- |
| ステータス採用日 | Workday | 就職者 | 従業員採用日 |
| 業務開始継続初日状態 | Workday | 就職者 | 従業員採用日 |
| 労働力参入日付 | Workday | 就職者 | 従業員採用日 |
| ステータス元の採用日 | Workday | 就職者 | 従業員採用日 |
| 雇用終了日ステータス | Workday | 退職者 | 従業員休暇日時 |
| 状態-退職日 | Workday | 退職者 | 従業員休暇日時 |
| ステータス退職日 | Workday | 退職者 | 従業員休暇日時 |
| ステータス終了日 | Workday | 退職者 | 従業員休暇日時 |
| 開始日 | SAP SF | 就職者 | 従業員採用日 |
| 最初に働いた日 | SAP SF | 就職者 | 従業員採用日 |
| 最終勤務日 | SAP SF | 退職者 | 従業員休暇日時 |
| 終了日 | SAP SF | 退職者 | 従業員休暇日時 |

その他の属性については、 [Workday 属性リファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-attribute-reference) と [SAP SuccessFactors 属性リファレンスを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-attribute-reference)。

### 時刻の重要性

スケジュールされたワークフローのタイミング精度を確保するには、次の点を考慮することが重要です。

- 属性の時間部分は、それに応じて設定する必要があります。 たとえば、 `employeeHireDate` は午前 1 時や午前 5 時などの時刻を、 `employeeLeaveDateTime` は午後 9 時や午後 11 時などの 1 日の終わりに時刻を持つ必要があります。
- ワークフローは、属性で指定された時間より前に実行されません。ただし、 [テナント スケジュール (既定では 3h)](https://learn.microsoft.com/ja-jp/entra/id-governance/customize-workflow-schedule) はワークフローの実行を遅らせる可能性があります。 たとえば、 `employeeHireDate` を午前 8 時に設定しても、テナント スケジュールが午前 9 時まで実行されない場合、ワークフローはそれまで処理されません。 新入社員が午前 8 時に開始する場合は、時間を (開始時刻 - テナント スケジュール) に設定して、従業員が到着する前に実行されるようにします。
- 一時アクセス パス (TAP) を使用している場合は、最大有効期間を 24 時間に設定することをお勧めします。 これを行うと、別のタイムゾーンにいる可能性のある従業員に送信された後に TAP の有効期限が切れていないことを確認できます。 詳細については、「 [パスワードレス認証方法を登録するように Microsoft Entra ID で一時アクセス パスを構成する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass#enable-the-temporary-access-pass-policy)」を参照してください。
- データをインポートするときは、正確なタイミングにするためにユーザーが調整できるよう、ソースからタイム ゾーン情報が提供されるかどうか、およびその方法を、理解しておく必要があります。

### Microsoft Entra Connect クラウド同期で EmployeeHireDate 用のカスタム同期ルールを作成する

次の手順では、クラウド同期を使用して同期規則を作成する手順について説明します。

1. Microsoft Entra 管理センターで、 **ハイブリッド管理**&gt;**Microsoft Entra Connect** に移動します。
2. [ **Microsoft Entra Connect クラウド同期の管理**] を選択します。
3. [ **構成]** で、構成を選択します。
4. **「マッピングを編集する」をクリック**を選択します。 このリンクをクリックすると、[ **属性マッピング** ] 画面が開きます。
5. [ **属性の追加] を選択します**。
6. 次の情報を入力します。
    - マッピングの種類: 直接
    - ソース属性: msDS-cloudExtensionAttribute1
    - 既定値: 空白のまま
    - ターゲット属性: employeeHireDate
    - このマッピングを適用する: [Image: クラウド属性マッピングの常にスクリーンショット。]
7. [ **適用]** を選択します。
8. **[属性マッピング**] 画面に戻ると、新しい属性マッピングが表示されます。
9. [ **スキーマの保存] を選択します**。

属性の詳細については、「 [Microsoft Entra Connect クラウド同期の属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-attribute-mapping)」を参照してください。

### Microsoft Entra Connect で EmployeeHireDate 用のカスタム同期ルールを作成する方法

次の例では、Active Directory の属性を Microsoft Entra ID の employeeHireDate 属性に同期するカスタム同期ルールを設定する手順について説明します。

1. 管理者として PowerShell ウィンドウを開き、`Set-ADSyncScheduler -SyncCycleEnabled $false` を実行してスケジューラを無効にします。
2. Start\Microsoft Entra Connect\ に移動し、同期ルール エディターを開きます
3. 上部の方向が **[受信]** に設定されていることを確認します。
4. [ **ルールの追加] を選択します。**
5. [ **受信同期規則の作成** ] 画面で、次の情報を入力し、[ **次へ**] を選択します。
    - 名前: In from AD - EmployeeHireDate
    - 接続先システム: contoso.com
    - 接続先システム オブジェクトの種類: user
    - メタバース オブジェクトの種類: person
    - 優先順位: 20 [Image: 受信同期規則の作成の基本のスクリーンショット。]
6. **スコープ フィルター**画面で、[**次へ**] を選択します。
7. [ **参加ルール** ] 画面で、[ **次へ**] を選択します。
8. **変換**画面の**変換を追加**で、次の情報を入力します。
    - FlowType: 直接
    - ターゲット属性: employeeHireDate
    - ソース: msDS-cloudExtensionAttribute1 [Image: 受信同期規則変換の作成のスクリーンショット。]
9. **追加**を選択します。
10. 同期規則エディターで、上部の方向が **[送信**] に設定されていることを確認します。
11. [ **ルールの追加] を選択します。**
12. [ **送信同期規則の作成** ] 画面で、次の情報を入力し、[ **次へ**] を選択します。
    - 名前: Out to Microsoft Entra ID - EmployeeHireDate
    - 接続先システム: &lt;&gt;
    - 接続先システム オブジェクトの種類: user
    - メタバース オブジェクトの種類: person
    - 優先順位: 21
13. **スコープ フィルター**画面で、[**次へ**] を選択します。
14. [ **参加ルール** ] 画面で、[ **次へ**] を選択します。
15. **変換**画面の**変換を追加**で、次の情報を入力します。
    - FlowType: 直接
    - ターゲット属性: employeeHireDate
    - ソース: employeeHireDate [Image: 送信同期規則変換作成のスクリーンショット。]
16. **追加**を選択します。
17. 同期規則エディターを閉じます。
18. `Set-ADSyncScheduler -SyncCycleEnabled $true` を実行して、再度スケジューラを有効にします。

メモ

- **msDS-cloudExtensionAttribute1** はソースの例です。
- **[Microsoft Entra Connect 2.0.3.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#functional-changes-10) 以降では、`employeeHireDate`は既定の "Out to Microsoft Entra ID" ルールに追加されるため、手順 10 から 16 は必要ありません。**
- **[Microsoft Entra Connect 2.1.19.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#functional-changes-1) 以降では、`employeeLeaveDateTime`は既定の "Out to Microsoft Entra ID" ルールに追加されるため、手順 10 から 16 は必要ありません。**

詳細については、「 [同期規則をカスタマイズする方法](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-create-custom-sync-rule) 」および [「既定の構成に変更を加える」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-change-the-configuration)参照してください。

### プロビジョニング アプリケーションで属性マッピングを編集する

プロビジョニング アプリケーションを設定したら、その属性マッピングを編集できます。 アプリが作成されたら、HRM と Active Directory の間の既定のマッピングの一覧が表示されます。 そこから、既存のマッピングを編集するか、新しいマッピングを追加できます。

このマッピングを更新するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**を開きます。
3. プロビジョニングされたアプリケーションを開きます。
4. [ **プロビジョニング**] を選択し、[ **属性マッピングの編集]** を選択します。
5. [ **詳細オプションの表示**] を選択し、[ **オンプレミス Active Directory の属性リストの編集**] を選択します。 [Image: オンプレミス属性の編集のスクリーンショット。]
6. ソース属性または型文字列として作成された属性を追加し、必須のチェック ボックスをオンにします。 [Image: ソース API リストのスクリーンショット。]

    メモ

    追加されるソース属性の数と名前は、Active Directory から同期する属性によって異なります。
7. [保存] を選択します。
8. そこから、追加された Active Directory 属性に HRM 属性をマップする必要があります。 これを行うには、式を使用して新しいマッピングを追加します。
9. 式は、 [Understanding EmployeeHireDate と EmployeeLeaveDateTime 書式設定セクションにある書式設定と](https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-sync-attributes#understanding-employeehiredate-and-employeeleavedatetime-formatting) 一致する必要があります。 [Image: 属性形式の設定のスクリーンショット。]
10. **[OK] を選択**.

### Microsoft Entra ID でこれらの属性値を確認する方法

Microsoft Entra ID のユーザー オブジェクトでこれらのプロパティに設定されている値を確認するには、 [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation?view=graph-powershell-1.0&preserve-view=true) を使用できます。 次に例を示します。

```PowerShell
# Import Module
Import-Module Microsoft.Graph.Users

# Define the necessary scopes
$Scopes =@("User.Read.All", "User-LifeCycleInfo.Read.All")

# Connect using the scopes defined and select the Beta API Version
Connect-MgGraph -Scopes $Scopes

# Query a user, using its user ID, and return the desired properties
$user = Get-MgUser -UserID "00aa00aa-bb11-cc22-dd33-44ee44ee44ee" -Property EmployeeLeaveDateTime
$User.EmployeeLeaveDateTime

```

[Image: 結果のスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/how-to-lifecycle-workflow-unsponsored-guest-removal"} -->
## ライフサイクル ワークフローを使用して応答していないゲストを管理する (プレビュー) - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-unsponsored-guest-removal
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-07-31
- Summary: ライフサイクル ワークフローを使用して、組織内の応答されていないゲストの削除を管理する方法について説明します。

スポンサー未割り当てのゲスト (有効なスポンサーが割り当てられていないゲスト ユーザー) は、組織におけるセキュリティとコンプライアンスのリスクとなります。 ライフサイクル ワークフローは、応答していないゲストの管理と削除を自動化するのに役立ちます。 Microsoft Entra ID ガバナンス には、スポンサーのないゲストの検出と管理を自動化する、組み込みの **スポンサーのないゲストのクリーンアップ (プレビュー)** ワークフロー テンプレートが含まれています。

この記事では、応答しない **ゲスト クリーンアップ (プレビュー)** ワークフロー テンプレートを使用して、応答していないゲストを管理する手順について説明します。

### 前提条件

この機能を使用するには、Microsoft Entra ID ガバナンス または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を参照してください。

Important

この機能は、ゲスト課金モデルの対象となります。 詳細については、[ゲスト ユーザーのMicrosoft Entra ID ガバナンスライセンスに関するページを](https://learn.microsoft.com/ja-jp/entra/id-governance/microsoft-entra-id-governance-licensing-for-guest-users)参照してください。

### Microsoft Entra 管理センターを使用して、スポンサー未設定のゲストを管理する

応答していないゲストを管理するためのワークフローを作成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**Workflows** に移動します。
3. ワークフロー画面で、[ **新しいワークフローの作成**] を選択します。
4. テンプレートの選択画面で、**スポンサーのないゲストのクリーンアップ (プレビュー)** ワークフロー テンプレートを見つけて選択します。

    Note

    このテンプレートは、特に離職者ワークフロー用に設計されています。
5. ワークフローの基本的な詳細を入力します。

    - **表示名**: ワークフローのわかりやすい名前
    - **説明**: ワークフローの目的に関する情報
6. ワークフローの実行条件を構成します。 トリガーの種類はテンプレートによって **ゲスト スポンサーの状態 (プレビュー)** に設定され、変更することはできません。

    [Image: ゲスト スポンサー ステータス トリガーの種類とスポンサーの数が 0 に設定されているトリガーの詳細セクションを示すスクリーンショット。]

    Note

    **[スポンサーの数]** 条件は **0 に**設定されており、現在構成できません。 このワークフローは、スポンサーが割り当てられていないユーザーを対象とします。
7. 組織の要件に基づいてワークフロー タスクを構成します。
8. [ **確認と作成** ] を選択してワークフローを最終処理し、有効にします。

### 応答しないゲストの削除に関する電子メール通知を追加する (省略可能)

**スポンサー未設定のゲストのクリーンアップ (プレビュー)** テンプレートには、既定で **ユーザー アカウントの削除** タスクが含まれています。 必要に応じて、 **応答していないゲストの削除 (プレビュー) に関する電子メールの送信** タスクを追加して、応答していないゲストが組織から削除されたときに、指定された受信者に通知することができます。

Note

受信者のオプション、電子メールのカスタマイズ、動的属性など、電子メール タスクの追加と構成の詳細については、「 [ライフサイクル ワークフローのタスクと定義 - 応答されていないゲストの削除 (プレビュー) に関する電子メールの送信」](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#send-email-about-unsponsored-guest-removal-preview)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/how-to-lifecycle-workflow-update-user-attributes"} -->
## ライフサイクル ワークフローを使用してユーザー属性を更新する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-update-user-attributes
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-05-01
- Summary: ライフサイクル ワークフローの [ユーザー属性の更新] タスクを使用してユーザー属性を更新する方法について説明します。

ライフサイクル ワークフローを使用すると、joiner、mover、leaver のシナリオの一部として、ユーザー属性の更新を自動化できます。 [ **ユーザー属性の更新** ] タスクを使用すると、部門の変更や退職などのライフサイクル イベントが発生したときに、組織内のユーザーの属性値を設定またはクリアできます。

この記事では、Microsoft Entra 管理センターとMicrosoft Graphを使用して、ユーザー属性の更新タスクを使用してワークフローを構成する手順について説明します。

### 前提条件

この機能を使用するには、Microsoft Entra ID ガバナンス または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を参照してください。

### サポートされている属性

ユーザー属性の更新タスクでは、次の属性の種類がサポートされています。

- クラウド管理ユーザーの組み込みユーザー属性 ( `department`、 `jobTitle`、 `employeeLeaveDateTime`など)
- クラウド管理ユーザーのオンプレミス拡張属性 (たとえば、`extensionAttribute1` から `extensionAttribute15` まで)
- クラウド管理ユーザーとオンプレミス AD から同期されたユーザーのディレクトリ拡張属性

Note

このタスクでは、カスタム セキュリティ属性はサポートされていません。

Note

datetime 属性の場合は、特定の日付を指定することも、 `system.now`を使用することもできます。 `system.now`に設定すると、属性はタスクが処理される日付に設定されます。

### Limitations

このタスクを構成する前に、次の制限事項に注意してください。

- タスク インスタンスごとに**最大 10 個の属性**を更新できます。
- **オンプレミス AD から同期されたユーザー**の場合、このタスクは**ディレクトリ拡張機能の属性のみをサポートします**。

### Microsoft Entra 管理センターを使用してユーザー属性の更新タスクを構成する

Microsoft Entra 管理センターを使用してワークフローにユーザー属性の更新タスクを追加するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**Workflows** に移動します。
3. 既存のワークフローを選択するか、タスクを追加する新しいワークフローを作成します。
4. ワークフローの画面で、**[タスク]** を選びます。
5. [ **タスクの追加]** を選択し、使用可能なタスクの一覧から [ **ユーザー属性の更新** ] を選択します。

    [Image: [ユーザー属性の更新 (プレビュー)] が選択されている [タスクの選択] パネルを示すスクリーンショット。]
6. 属性の更新を構成します。

    - 更新またはクリアする属性を選択します。
    - 各属性に新しい値を指定するか、値を空のままにして属性をクリアします。

    [Image: [ユーザー属性の更新] タスクの属性構成パネルを示すスクリーンショット。]
7. **[保存] を**選択して、タスクをワークフローに追加します。

Note

1 つのタスク インスタンス内で最大 10 個の属性更新を構成できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/identity-governance-applications-define"} -->
## 環境内のアプリケーションへのアクセスを管理するための組織のポリシーを定義する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-define
- Service: entra-id-governance
- Article date: 2024-12-10
- Summary: Microsoft Entra ID Governance を使うと、セキュリティや従業員の生産性に対する組織のニーズと、適切なプロセスや可視性とのバランスを取ることができます。 ユーザーが Microsoft Entra ID Governance と統合されたビジネス クリティカルなアプリケーションへのアクセスを取得する方法に関するポリシーを定義できます。

Microsoft Entra ID を使用して[アクセスを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-prepare) 1 つ以上のアプリケーションを特定したら、アクセス権を持つ必要があるユーザーを決定するための組織のポリシーと、システムが提供する必要があるその他の制約を書き留めます。

### スコープ内のアプリケーションとそのロールを選択する

コンプライアンス要件またはリスク管理計画がある組織には、機密性の高いアプリケーションまたはビジネス クリティカルなアプリケーションがあります。 このアプリケーションが環境内の既存のアプリケーションである場合は、このアプリケーションにアクセスする必要があるユーザーのアクセス ポリシーを既に文書化している可能性があります。 そうでない場合は、コンプライアンス チームやリスク管理チームなど、さまざまな利害関係者と相談して、アクセスの決定を自動化するために使用されているポリシーがシナリオに適していることを確認する必要があります。

1. **各アプリケーションが提供するロールとアクセス許可を収集します。** ロール "User" のみを使用するアプリケーションなど、一部のアプリケーションでは、1 つのロールしかない場合があります。 より複雑なアプリケーションでは、Microsoft Entra ID を通じて管理される複数のロールが表示される場合があります。 通常、これらのアプリケーションのロールは、そのロールを持つユーザーがアプリ内で持つアクセス権に広範な制約を適用します。 たとえば、管理者ペルソナを使用するアプリケーションには、"User" と "Administrator" という 2 つのロールがある場合があります。 他のアプリケーションもまた、きめ細かなロールの確認のためにグループ メンバーシップまたは要求に依存する可能性があります。これは、フェデレーション SSO プロトコルを使用して発行されたか、またはセキュリティ グループ メンバーシップとして AD に書き込まれたプロビジョニングまたは要求で Microsoft Entra ID からアプリケーションに提供できます。 最後に、Microsoft Entra ID に表示されないアプリケーション固有のロールが存在する可能性があります。もしかすると、Microsoft Entra ID での管理者の定義を許可せず、代わりに独自の承認規則を利用して管理者を識別するアプリケーションかもしれません。 SAP Cloud Identity Services で割り当てに使用できるロールは、1 つ (**ユーザー**) のみです。

    注

    プロビジョニングをサポートする Microsoft Entra ID アプリケーション ギャラリーのアプリケーションを使用している場合、プロビジョニングが構成された後に、Microsoft Entra ID によって定義されたロールがアプリケーションにインポートされ、アプリケーションのロールを使用してアプリケーション マニフェストが自動的に更新される場合があります。
2. **Microsoft Entra ID で管理されるメンバーシップを持つロールとグループを選びます。** 多くの場合、コンプライアンスとリスク管理の要件に基づいて、組織は、機密情報への特権アクセスまたはアクセスを許可するアプリケーション ロールまたはグループに優先順位を付けます。

### アプリケーションへのアクセスに関する前提条件とその他の制約を使用して組織のポリシーを定義する

このセクションでは、アプリケーションへのアクセスを決定するために使用する予定の組織ポリシーを記述します。 たとえば次のように、これを表としてスプレッドシートに記録できます

| アプリ ロール | アクセスの前提条件 | 承認者 | アクセスの既定の期間 | 職務の分離の制約 | 条件付きアクセス ポリシー |
| --- | --- | --- | --- | --- | --- |
| *Western Sales* | 営業チームのメンバー | ユーザーのマネージャー | 年単位のレビュー | *Eastern Sales* アクセス権を持つことはできない | アクセスに必要な多要素認証 (MFA) と登録済みデバイス |
| *Western Sales* | 営業以外の従業員 | 営業部門長 | 90 日間 | 該当なし | アクセスに必要な MFA と登録済みデバイス |
| *Western Sales* | 従業員以外の営業担当者 | 営業部門長 | 30 日 | 該当なし | アクセスに必要な MFA |
| *東部売上* | 営業チームのメンバー | ユーザーのマネージャー | 年単位のレビュー | *Western Sales* アクセス権を持つことはできない | アクセスに必要な MFA と登録済みデバイス |
| *東部売上* | 営業以外の従業員 | 営業部門長 | 90 日間 | 該当なし | アクセスに必要な MFA と登録済みデバイス |
| *東部売上* | 従業員以外の営業担当者 | 営業部門長 | 30 日 | 該当なし | アクセスに必要な MFA |

既に組織のロールの定義がある場合は、[組織のロールを移行する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-organizational-roles)に関するページを参照してください。

1. **アプリケーションへのアクセス権を付与される前に、ユーザーが満たす必要がある前提条件、標準があるかどうかを特定します。** たとえば、通常の状況では、フルタイムの従業員、または特定の部門またはコスト センターの従業員のみが、特定の部門のアプリケーションへのアクセスを許可される必要があります。 また、1 人以上の追加承認者が必要なアクセス権を要求する他の部署のユーザー用にエンタイトルメント管理ポリシーが必要になる場合もあります。 承認の段階が複数あると、ユーザーがアクセス権を取得するプロセス全体が遅くなる可能性がある一方で、これらの追加の段階により、アクセス要求の適切さが確認され、決定に責任を負うことができるようになります。 たとえば、従業員によるアクセス要求には、2 段階の承認が必要な場合があります。1 番目は要求元のユーザーのマネージャー、2 番目はアプリケーションに保持されているデータを管理しているいずれかのリソース所有者によるものです。
2. **アクセスが承認されたユーザーが、アクセス権を持つ期間と、そのアクセス権が取り消されるタイミングを決定します。** 多くのアプリケーションでは、組織に所属しなくなるまで、ユーザーは無期限にアクセスを保持します。 状況によっては、アクセスが特定のプロジェクトまたはマイルストーンに関連付けられていて、プロジェクトが終了すると、アクセス権が自動的に削除されることがあります。 または、ポリシーを介してアプリケーションを使用しているユーザーが少ない場合は、そのポリシーを介したすべてのユーザーのアクセスを四半期ごとまたは年ごとにレビューするように構成し、定期的に監視されるようにすることができます。
3. **組織が既に組織のロール モデルを使ってアクセスを管理している場合は、その組織のロール モデルを Microsoft Entra ID に導入することを計画してください。** ユーザーのプロパティ (役職や部署など) に基づいてアクセス権を割り当てる [組織ロール](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-organizational-roles) が定義されている場合があります。 これらのプロセスにより、事前に決定されたプロジェクトの終了日がない場合でも、アクセスが不要になったときに最終的にユーザーがアクセス権を失うようにすることができます。
4. **職務の分離の制約があるかどうかを調べてください。** たとえば、*Western Sales* と *Eastern Sales* という 2 つのアプリ ロールを持つアプリケーションがあるとして、ユーザーが一度に 1 つの販売区域しか担当できないようにしたい場合があります。 アプリケーションに互換性のないアプリ ロールのペアの一覧を含めて、ユーザーが 1 つのロールを持っている場合、2 つ目のロールを要求できないようにします。
5. **アプリケーションにアクセスするための適切な条件付きアクセス ポリシーを選択します。** アプリケーションを分析し、同じユーザーに対して同じリソース要件があるアプリケーションにそれらをグループ化することをお勧めします。 これが ID ガバナンスのために Microsoft Entra ID ガバナンスと統合する最初のフェデレーション SSO アプリケーションである場合は、多要素認証 (MFA) や場所ベースのアクセスの要件など、制約を示すための新しい条件付きアクセス ポリシーを作成することが必要になることがあります。 [使用条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-require-terms-of-use)に同意する必要があるユーザーを構成できます。 条件付きアクセス ポリシーを定義する方法に関する考慮事項については、[条件付きアクセスのデプロイの計画](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/plan-conditional-access)に関するページを参照してください。
6. **条件の例外を処理する方法を決定します。** たとえば、通常、アプリケーションは指定された従業員のみが利用できますが、監査人またはベンダーが特定のプロジェクトに一時的にアクセスする必要がある場合があります。 または、出張中の従業員が、組織がその場所に存在しないために通常はブロックされている場所からのアクセスを必要とする場合があります。 このような状況では、異なる段階、異なる制限期間、または異なる承認者がいる承認のためのエンタイトルメント管理ポリシーを選択することもできます。 Microsoft Entra テナントにゲスト ユーザーとしてサインインしているベンダーにはマネージャーがいない場合があるため、代わりに、組織のスポンサー、リソース所有者、またはセキュリティ責任者によってアクセス要求が承認される可能性があります。

アクセス権を持つ必要があるユーザーの組織ポリシーが利害関係者によってレビューされているため、Microsoft Entra ID の[アプリケーションと統合](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-integrate)を開始できます。 このようにして、後の手順で、Microsoft Entra ID ガバナンスにおいてアクセスのために[組織が承認したポリシーをデプロイする](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-deploy)準備が整います。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/identity-governance-applications-deploy"} -->
## Microsoft Entra ID と統合されたアプリケーションへのアクセスを管理するためのポリシーのデプロイ - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-deploy
- Service: entra-id-governance
- Article date: 2025-03-10
- Summary: Microsoft Entra ID Governance を使用すると、セキュリティや従業員の生産性に対する組織のニーズと、適切なプロセスや可視性とのバランスを取ることができます。 エンタイトルメント管理およびその他の ID ガバナンス機能を使用して、アクセスのポリシーを適用できます。

前のセクションでは、[アプリケーションのガバナンス ポリシーを定義](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-define)し、[そのアプリケーションを Microsoft Entra ID と統合](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-integrate)しました。 このセクションでは、Microsoft Entra の条件付きアクセスとエンタイトルメント管理機能を構成して、アプリケーションへの継続的なアクセスを制御します。 次を確立します

- シングル サインオンのために Microsoft Entra ID と統合されたアプリケーションの Microsoft Entra ID に対してユーザーが認証する方法に関する条件付きアクセス ポリシー
- ユーザーがアプリケーション ロールとグループのメンバーシップに対する割り当てを取得して保持する方法に関するエンタイトルメント管理ポリシー
- グループ メンバーシップのレビュー頻度に関するアクセス レビュー ポリシー

これらのポリシーがデプロイされると、ユーザーがアプリケーションへのアクセスを要求し、アクセスが割り当てられるときに、Microsoft Entra ID の継続的な動作を監視できます。

### SSO の適用のための条件付きアクセス ポリシーをデプロイする

このセクションでは、ユーザーの認証強度やデバイスの状態などの要因に基づいて、許可されているユーザーがアプリにサインインできるかどうかを決定するためのスコープ内にある、条件付きアクセス ポリシーを確立します。

条件付きアクセスは、シングル サインオン (SSO) に Microsoft Entra ID を利用するアプリケーションでのみ可能です。 アプリケーションを SSO 用に統合できない場合は、次のセクションに進んでください。

1. **必要に応じて、使用条件 (TOU) ドキュメントをアップロードしてください。** アプリケーションにアクセスする前に利用規約 (TOU) に同意することをユーザーに要求する場合は、条件付きアクセス ポリシーに含めることができるように、[TOU ドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use)を作成してアップロードします。
2. **ユーザーが Microsoft Entra 多要素認証に対応していることを確認します。** フェデレーションを介して統合されたビジネス クリティカルなアプリケーションでは、Microsoft Entra 多要素認証を要求することをお勧めします。 これらのアプリケーションには、Microsoft Entra ID がアプリケーションへのサインインを許可する前に、ユーザーが多要素認証要件を満たしていることを要求するポリシーが必要です。 組織によっては、場所によるアクセスをブロックしたり、[登録済みのデバイスからアクセスするようにユーザーに要求](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-alt-all-users-compliant-hybrid-or-mfa)したりする場合もあります。 認証、場所、デバイス、TOU に必要な条件を含む適切なポリシーがまだない場合は、[条件付きアクセスのデプロイにポリシーを追加](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/plan-conditional-access)します。
3. **アプリケーション Web エンドポイントを適切な条件付きアクセス ポリシーのスコープに含めます**。 同じガバナンス要件の対象となる別のアプリケーション用に作成された既存の条件付きアクセス ポリシーがある場合は、そのポリシーを更新して、このアプリケーションにも適用できるようにすることで、多数のポリシーを使用するのを避けることができます。 更新が完了したら、期待どおりのポリシーが適用されていることを確認します。 ユーザーに適用されるポリシーは、[条件付きアクセス What if ツール](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-conditional-access-what-if)で確認できます。
4. **ポリシーの一時的な除外を必要とするユーザーがいる場合は、定期的なアクセス レビューを作成します**。 場合によっては、すべての認可済みユーザーに対して条件付きアクセス ポリシーをすぐには適用できないことがあります。 たとえば、一部のユーザーは、適切な登録済みデバイスを持っていない場合があります。 条件付きアクセス ポリシーから 1 人以上のユーザーを除外し、そのユーザーにアクセスを許可する必要がある場合は、[条件付きアクセス ポリシーから除外されるユーザー](https://learn.microsoft.com/ja-jp/entra/id-governance/conditional-access-exclusion)のグループに対してアクセス レビューを構成します。
5. **トークンの有効期間とアプリケーションのセッション設定を文書化します。** 継続的なアクセスを拒否されたユーザーが、フェデレーション アプリケーションを引き続き使用できる期間は、アプリケーション独自のセッションの有効期間と、アクセス トークンの有効期間によって決まります。 アプリケーション セッションの有効期間は、アプリケーション自体によって異なります。 アクセス トークンの有効期間の制御について詳しくは、[構成可能なトークンの有効期間](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes)に関する記事をご覧ください。

### アクセス割り当て自動化におけるエンタイトルメント管理ポリシーのデプロイ

このセクションでは、ユーザーがアプリケーションのロールまたはアプリケーションによって使用されるグループへのアクセスを要求できるように、Microsoft Entra エンタイトルメント管理を構成します。 これらのタスクを実行するために、既定の最小特権ロールは *Identity Governance Administrator* ロールです。または、別のユーザーを [カタログ作成者として委任](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate-catalog)し、アプリケーションの所有者にすることもできます。

注

ここでは、最小限の特権アクセスに従って、ID ガバナンス管理者ロールを使用することをお勧めします。

1. **管理対象アプリケーションのアクセス パッケージは、指定されたカタログに含まれている必要があります。** アプリケーション ガバナンス シナリオのカタログがまだない場合は、Microsoft Entra エンタイトルメント管理で [\[カタログの作成\]](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create) を行ってください。 作成するカタログが複数ある場合は、「PowerShell を使用した [カタログの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#create-a-catalog-with-powershell)」に示すように、 [PowerShell スクリプトを使用して各カタログを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create-app#create-a-catalog-in-microsoft-entra-entitlement-management)できます。
2. **カタログに必要なリソースを設定します。** アプリケーションと、アプリケーションが使用するすべての Microsoft Entra グループを[そのカタログ内のリソースとして](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#add-resources-to-a-catalog)追加します。 リソースが多数ある場合は、「アプリケーションをリソースとして [カタログに追加](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#add-a-resource-to-a-catalog-with-powershell)する」に示すように、PowerShell スクリプトを使用 [して各リソースをカタログに追加](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create-app#add-the-application-as-a-resource-to-the-catalog)できます。
3. **ユーザーが要求できるロールまたはグループごとにアクセス パッケージを作成します。** アプリケーションごとに、またそれらのアプリケーションのロールまたはグループごとに、そのロールまたはグループをリソースとして含む[アクセス パッケージを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)します。 これらのアクセス パッケージを構成するこの段階で、各アクセス パッケージの最初のアクセス パッケージ割り当てポリシーを[直接割り当てのポリシー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#none-administrator-direct-assignments-only)として構成し、管理者だけが割り当てを作成できるようにします。 そのポリシーでは、既存のユーザーがアクセス権を無期限に保持しないように、既存のユーザーのアクセス レビュー要件 (存在する場合) を設定します。 多数のアクセス パッケージがある場合は、[1 つのロールを持つアプリケーションのアクセス パッケージの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#create-an-access-package-by-using-microsoft-powershell)に関するページに示すように、PowerShell スクリプトを使用して、[カタログ内に各アクセス パッケージを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create-app#create-an-access-package-in-entitlement-management-for-an-application-with-a-single-role-using-powershell)できます。
4. **アクセス パッケージを構成して、職務の分離要件を適用します。**[職務の分離](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-incompatible)要件がある場合は、互換性のないアクセス パッケージまたは既存のグループをアクセス パッケージ用に構成します。 シナリオで職務の分離チェックをオーバーライドする機能が必要な場合は、[それらのオーバーライド シナリオ用に追加のアクセス パッケージを設定](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-incompatible#configuring-multiple-access-packages-for-override-scenarios)することもできます。
5. **既にアプリケーションへのアクセス権を持っている既存のユーザーの割り当てをアクセス パッケージに追加します。** アクセス パッケージごとに、その対応するロールのアプリケーションの既存のユーザーまたはそのグループのメンバーを、アクセス パッケージと直接割り当てポリシーに割り当てます。 [既存のユーザーの割り当ての追加](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments)に関するページに示すように、Microsoft Entra 管理センターを使用して、アクセス パッケージに[ユーザーを直接割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#assign-a-user-to-an-access-package-with-powershell)ことも、Graph または [PowerShell](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create-app#add-assignments-of-existing-users-who-already-have-access-to-the-application) を使用して一括で割り当てることもできます。
6. **ユーザーがアクセスを要求できるようにする追加のポリシーを作成します。** 各アクセス パッケージで、ユーザーがアクセスを要求できるように、[追加のアクセス パッケージ割り当てポリシーを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#open-an-existing-access-package-and-add-a-new-policy-with-different-request-settings)します。 そのポリシーで承認と定期的なアクセス レビューの要件を構成します。
7. **アプリケーションで使用される他のグループの定期的なアクセス レビューを作成します。** アプリケーションによって使用されるが、アクセス パッケージのリソース ロールではないグループがある場合は、それらのグループのメンバーシップについて [アクセス レビューを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)します。

### アクセスに関するレポートの表示

Microsoft Entra ID と Microsoft Entra ID Governance with Azure Monitor には、特定のアプリケーションにアクセスできるユーザーと、ユーザーがそのアクセス権を行使しているかどうかを把握するのに役立ついくつかのレポートが用意されています。 次に示します。

- 管理者またはカタログ所有者は、Microsoft Entra 管理センター、Graph、または PowerShell を使用して、アクセス パッケージの割り当てを持つユーザーの一覧を 取得できます。
- 監査ログを Azure Monitor に送信し、Microsoft Entra 管理センターまたは PowerShell で、[アクセス パッケージに対する変更](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logs-and-reporting#view-events-for-an-access-package)の履歴を表示することもできます。
- アプリケーションへの過去 30 日間のサインインは、Microsoft Entra 管理センターの[サインイン レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-in-log-activity-details)、または [Graph](https://learn.microsoft.com/ja-jp/graph/api/signin-list?view=graph-rest-1.0&tabs=http&preserve-view=true) で確認できます。 また、[サインイン ログを Azure Monitor に送信して](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-log-monitoring-integration-options-considerations)サインイン アクティビティを最大 2 年間アーカイブすることもできます。

Microsoft Entra には追加のレポートがあります。 エンタイトルメント管理のレポートの詳細については、「[エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-reports)でのレポートとログの表示」を参照してください。

また、Azure Data Explorer を使用して、Microsoft Entra、Microsoft Entra ID Governance、およびその他のソースからの現在または過去のデータを保持して報告することもできます。 詳細については、「Microsoft Entra IDからのデータを使用して Azure Data Explorer でカスタマイズされたレポートを する」を参照してください。

### 必要に応じて、エンタイトルメント管理ポリシーとアクセスを調整するために監視する

アプリケーションのアプリケーション アクセス割り当ての変更量に基づいて、毎週、毎月、四半期ごとなどの定期的な間隔で、Microsoft Entra 管理センターを使用して、ポリシーに従ってアクセスが許可されていることを確認します。 また、承認とレビューのために識別されたユーザーが、これらのタスクの正しい個人であることを確認できます。

- **アプリケーションのロール割り当てとグループ メンバーシップの変更を監視します。** 監査ログを Azure Monitor に送信するように Microsoft Entra ID が構成されている場合は、Azure Monitor の `Application role assignment activity` を使用して、[エンタイトルメント管理を通じて行われたわけではないアプリケーション ロールの割り当てを監視し、レポートを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-incompatible#monitor-and-report-on-access-assignments)します。 アプリケーション所有者によって直接作成されたロール割り当てがある場合は、そのアプリケーション所有者に連絡して、その割り当てが承認されたかどうかを判断する必要があります。 さらに、アプリケーションが Microsoft Entra セキュリティ グループを使用している場合は、それらのグループに対する変更も監視します。
- **また、アプリケーション内で直接アクセス権が付与されたユーザーも監視します。** 次の条件が満たされている場合、ユーザーは Microsoft Entra ID に参加せずに、または Microsoft Entra ID によってアプリケーションのユーザー アカウント ストアに追加されることなく、アプリケーションへのアクセス権を取得できます。

    - アプリケーションには、アプリ内にローカル ユーザー アカウント ストアがある
    - ユーザー アカウント ストアはデータベースまたは LDAP ディレクトリーにある
    - アプリケーションがシングル サインオンに Microsoft Entra ID のみを利用しているわけではない

    上記の一覧のプロパティを持つアプリケーションの場合、ユーザーが Microsoft Entra プロビジョニングによってのみアプリケーションのローカル ユーザー ストアに追加されたことを定期的に確認する必要があります。 アプリケーションで直接作成されたユーザーの場合は、アプリケーション所有者に連絡して、その割り当てが承認されたかどうかを判別してください。
- **承認者とレビュー担当者が最新の状態に保たれていることを確認します。** 前のセクションで構成したアクセス パッケージごとに、アクセス パッケージの割り当てポリシーに引き続き正しい承認者とレビュー担当者がいることを確認します。 以前に構成された承認者とレビュー担当者が組織内に存在しなくなった場合、または別のロールにいる場合は、これらのポリシーを更新します。
- **レビュー担当者がレビュー中に決定を下していることを検証します。**[これらのアクセス パッケージの定期的なアクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-lifecycle-policy) が正常に完了していることを監視して、レビュー担当者が参加し、ユーザーの継続的なアクセスの必要性を承認または拒否する決定を下していることを確認します。
- **プロビジョニングとプロビジョニング解除が期待どおりに機能していることを確認します。** アプリケーションへのユーザーのプロビジョニングを以前に構成していた場合は、レビューの結果が適用されたとき、またはアクセス パッケージへのユーザーの割り当ての有効期限が切れたときに、Microsoft Entra ID はアプリケーションから拒否されたユーザーのプロビジョニング解除を開始します。 [ユーザーのプロビジョニング解除のプロセスを監視](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)できます。 プロビジョニングでアプリケーションに関するエラーが示される場合は、[プロビジョニングのログをダウンロード](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)して、アプリケーションに問題があったかどうかを調査できます。
- **アプリケーションでのロールまたはグループの変更で Microsoft Entra 構成を更新します。** アプリケーション管理者が [マニフェスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)に新しいアプリ ロールを追加したり、既存のロールを更新したり、追加のグループに依存したりする場合は、アクセス パッケージとアクセス レビューを更新して、それらの新しいロールまたはグループを考慮する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/identity-governance-applications-existing-users"} -->
## Microsoft PowerShell を使用して Microsoft Entra ID 内のアプリケーションの既存のユーザーを管理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-existing-users
- Service: entra-id-governance
- Article date: 2026-04-21
- Summary: 特定のアプリケーションのアクセス レビュー キャンペーンを成功させるための計画には、そのアプリケーションのいずれかのユーザーに Microsoft Entra ID から派生していないアクセス権があるかどうかを識別することが含まれます。

アクセス レビューなどの Microsoft Entra ID ガバナンス機能でアプリケーションを使用する前に、Microsoft Entra ID に既存のアクセス権とアプリケーションのユーザーを設定する必要がある 4 つの一般的なシナリオ [があります](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-application-preparation)。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID Governance または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。

#### アプリケーションが独自の ID プロバイダーを使用した後に Microsoft Entra ID に移行された場合

最初のシナリオでは、アプリケーションが既に環境内に存在します。 そのアプリケーションは以前、どのユーザーにアクセス権があるかを追跡するために独自の ID プロバイダーまたはデータ ストアを使用していました。

Microsoft Entra ID に依存するようにアプリケーションを変更すると、Microsoft Entra ID 内に存在し、そのアプリケーションへのアクセスが許可されているユーザーだけがアクセスできます。 その構成変更の一部として、そのアプリケーションのデータ ストアから既存のユーザーを Microsoft Entra ID に取り込むことを選択できます。 それにより、それらのユーザーは引き続き Microsoft Entra ID を通してアクセスできます。

アプリケーションに関連付けられているユーザーを Microsoft Entra ID で表されるようにすると、ユーザーとアプリケーションの関係が別の場所で開始された場合でも、Microsoft Entra ID でアプリケーションにアクセスできるユーザーを追跡できます。 たとえば、その関係はアプリケーションのデータベースまたはディレクトリで開始されている可能性があります。

Microsoft Entra ID では、ユーザーの割り当てを認識した後、アプリケーションのデータ ストアに更新を送信できます。 この更新には、そのユーザーの属性が変更されたときや、そのユーザーがアプリケーションのスコープから外れたときが含まれます。

#### Microsoft Entra ID を唯一の ID プロバイダーとして使用しないアプリケーション

2 つ目のシナリオでは、アプリケーションが、その ID プロバイダーとして Microsoft Entra ID のみには依存していません。

場合によっては、アプリケーションが AD グループに依存している場合があります。 このシナリオは、「[アプリケーションへのユーザーのアクセスのアクセス レビューを準備する](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-application-preparation)」のパターン B で説明されています。 この記事で説明されているように、このアプリケーションのプロビジョニングを構成する必要はありません。AD グループのメンバーシップを確認する方法については、代わりにこの記事のパターン B の手順に従ってください。

場合によっては、アプリケーションが複数の ID プロバイダーをサポートしていたり、独自の組み込みの資格情報ストレージを持っていたりすることがあります。 このシナリオは、「[アプリケーションへのユーザーのアクセスのアクセス レビューを準備する](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-application-preparation)」ではパターン C として説明されています。

アプリケーションから他の ID プロバイダーまたはローカル資格情報認証を削除することが不可能な場合があります。 その場合、Microsoft Entra ID を使用してそのアプリケーションにアクセスできるユーザーをレビューしたり、そのアプリケーションからだれかのアクセス権を削除したりするには、認証を Microsoft Entra ID に依存しないアプリケーション ユーザーを表す Microsoft Entra ID 内の割り当てを作成する必要があります。

アクセス レビューの一環として、アプリケーションにアクセスするすべてのユーザーをレビューする予定の場合は、これらの割り当てが必要です。

たとえば、あるユーザーがアプリケーションのデータ ストアに存在するとします。 Microsoft Entra ID は、アプリケーションへのロールの割り当てを必要とするように構成されています。 ただし、そのユーザーは Microsoft Entra ID にアプリケーション ロールの割り当てを持っていません。

そのユーザーが Microsoft Entra ID で更新されても、アプリケーションに変更は送信されません。 また、アプリケーションのロール割り当てがレビューされた場合も、そのユーザーはレビューに含まれません。 すべてのユーザーがレビューに含まれるようにするには、アプリケーションのすべてのユーザーに対してアプリケーション ロールの割り当てを設定する必要があります。

#### アプリケーションが ID プロバイダーとして Microsoft Entra ID を使用しておらず、プロビジョニングもサポートしていない

一部のレガシ アプリケーションでは、他の ID プロバイダーまたはローカル資格情報認証をアプリケーションから削除したり、それらのアプリケーションのプロビジョニング プロトコルのサポートを有効にしたりできない場合があります。

このようなプロビジョニング プロトコルをサポートしないアプリケーションのシナリオについては、[プロビジョニングをサポートしないアプリケーションの既存ユーザーの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-not-provisioned-users)に関する別の記事を参照してください。

#### アプリケーションは、Id プロバイダーとして Microsoft Entra ID を使用し、ユーザーに対する追加のアクセス権を持っています

カスタム データ提供のリソースを使用して、Microsoft Entra ID アクセス レビューにアプリケーションからのアクセス権を含めるには、アクセス データをカタログに直接アップロードします。

その後、Microsoft Entra に接続されたリソースとそれらのアクセス権の両方で、ユーザー アクセス レビュー (UUAR) を実行できます。 レビュー担当者は、マイ アクセス ポータルでユーザーのアクセスを簡単に確認および認定できるため、Microsoft Entra に接続されているかどうかに関係なく、すべてのリソースで一貫したガバナンス、可視性の向上、コンプライアンスを確保できます。

このシナリオについては、別の記事で説明します。 [カタログ ユーザー アクセス レビュー (プレビュー) のカタログにカスタム データ提供リソースを含めます](https://learn.microsoft.com/ja-jp/entra/id-governance/custom-data-resource-access-reviews)。

### 用語

この記事では、[Microsoft Graph PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph)を使用して、アプリケーション ロールの割り当てを管理するためのプロセスを示しています。 ここでは、次の Microsoft Graph の用語を使用しています。

[Image: Microsoft Graph の用語を示す図。]

Microsoft Entra ID では、サービス プリンシパル (`ServicePrincipal`) は、特定の組織のディレクトリ内のアプリケーションを表します。 `ServicePrincipal` には、アプリケーションがサポートするロール (`AppRoles` など) を一覧表示する `Marketing specialist` という名前のプロパティがあります。 `AppRoleAssignment` はユーザーをサービス プリンシパルにリンクし、そのユーザーのそのアプリケーションでのロールを指定します。 アプリケーションへのシングル サインオンとアプリケーションへのプロビジョニングが個別に処理される場合、アプリケーションは複数のサービス プリンシパルを持つことができます。

ユーザーにアプリケーションへの期間限定のアクセス権を付与するために、[Microsoft Entra エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)アクセス パッケージを使用することもできます。 エンタイトルメント管理では、`AccessPackage` に 1 つ以上のリソース ロール (複数のサービス プリンシパルの可能性もあります) が含まれています。 `AccessPackage` には、アクセス パッケージへのユーザーの割り当て (`Assignment`) も含まれています。

アクセス パッケージへのユーザーの割り当てを作成すると、Microsoft Entra エンタイトルメント管理によって、各アプリケーションのアクセス パッケージ内のサービス プリンシパルに対してユーザーに必要な `AppRoleAssignment` インスタンスが自動的に作成されます。 詳細については、PowerShell を使用してアクセス パッケージを作成する方法を示す、[Microsoft Entra エンタイトルメント管理でリソースへのアクセスを管理する](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/tutorial-entitlement-management)方法に関するページ参照してください。

### 開始する前に

- テナントには次のいずれかのライセンスが必要です。

    - Microsoft Entra ID P2 または Microsoft Entra ID Governance
    - Enterprise Mobility + Security E5 ライセンス
- 適切な管理者ロールを持っている必要があります。 これらの手順を初めて実行している場合は、テナントでの Microsoft Graph PowerShell の使用を認可するグローバル管理者ロールが必要です。
- アプリケーションには、テナント内に少なくとも 1 つのサービス プリンシパルが必要です。

    - アプリケーションで LDAP ディレクトリを使用する場合は、[ユーザーを LDAP ディレクトリにプロビジョニングするように Microsoft Entra ID を構成するためのガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure)の「Microsoft Entra Connect プロビジョニング エージェント パッケージのダウンロード、インストール、構成」セクションに従います。
    - アプリケーションで SQL データベースを使用する場合は、[ユーザーを SQL ベースのアプリケーションにプロビジョニングするように Microsoft Entra ID を構成するためのガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sql-connector-configure)の「Microsoft Entra Connect プロビジョニング エージェント パッケージのダウンロード、インストール、構成」セクションに従います。
    - アプリケーションが SAP Cloud Identity Services を使っているか、SCIM プロトコルをサポートするクラウド アプリケーションであり、アプリケーションがテナントにまだ構成されていない場合は、このガイドの後半で[アプリケーション ギャラリー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/overview-application-gallery)からアプリケーションを登録します。
    - アプリケーションがオンプレミスであり、SCIM プロトコルをサポートしている場合は、[オンプレミスの SCIM ベース アプリケーションにユーザーをプロビジョニングするように Microsoft Entra ID を構成する際のガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-scim-provisioning)に従ってください。

### アプリケーションを登録する

アプリケーションが既に Microsoft Entra ID に登録されている場合は、次の手順に進みます。

- アプリケーションが LDAP ディレクトリを使っている場合は、[ユーザーを LDAP ディレクトリにプロビジョニングするように Microsoft Entra ID を構成する際のガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure#configure-the-on-premises-ecma-app)のセクションに従って、オンプレミスの ECMA アプリ用の新しい登録を Microsoft Entra ID に作成します。
- アプリケーションが SQL データベースを使っている場合は、[ユーザーを SQL ベースのアプリケーションにプロビジョニングするように Microsoft Entra ID を構成する際のガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sql-connector-configure#4-configure-the-on-premises-ecma-app)のセクションに従って、オンプレミスの ECMA アプリ用の新しい登録を Microsoft Entra ID に作成します。
- SAP Cloud Identity Services を使っている場合は、[SAP Cloud Identity Services にユーザーをプロビジョニングするための Microsoft Entra ID の構成ガイド](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial)に従います。
- SCIM プロトコルをサポートするクラウド アプリケーションである場合は、[アプリケーション ギャラリー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/overview-application-gallery)からアプリケーションを追加することができます。
- アプリケーションがオンプレミスであり、SCIM プロトコルをサポートしている場合は、[オンプレミスの SCIM ベース アプリケーションにユーザーをプロビジョニングするように Microsoft Entra ID を構成する際のガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-scim-provisioning)に従ってください。

### アプリケーションのプロビジョニングを構成する

アプリケーションが LDAP ディレクトリ、SQL データベース、または SAP Cloud Identity Services を使っている場合、または SCIM をサポートしている場合は、新しい割り当てを作成する前に、アプリケーションに対する [Microsoft Entra ユーザーのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を構成します。 割り当てを作成する前にプロビジョニングを構成すると、Microsoft Entra ID のユーザーを、アプリケーションのデータ ストアにすでに存在するユーザーに割り当てられたアプリケーションのロールと照合することができます。 プロビジョニングするオンプレミスのディレクトリまたはデータベースがアプリケーションにあり、さらにフェデレーション SSO をサポートしている場合は、ディレクトリ内のアプリケーションを表す 2 つのサービス プリンシパル (プロビジョニング用に 1 つと SSO 用に 1 つ) が必要になることがあります。 アプリケーションがプロビジョニングをサポートしていない場合は、次のセクションに進んでください。

1. 選択したユーザーのみがアプリケーションにプロビジョニングされるよう、ユーザーにアプリケーション ロールの割り当てを要求するようにアプリケーションが構成されていることを確認します。
2. アプリケーションへのプロビジョニングが構成されていない場合は、それをここで構成します (ただし、またプロビジョニングは開始しません)。

    - アプリケーションで LDAP ディレクトリを使用する場合は、[ユーザーを LDAP ディレクトリにプロビジョニングするように Microsoft Entra ID を構成するためのガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure)に従います。
    - アプリケーションで SQL データベースを使用する場合は、[ユーザーを SQL ベースのアプリケーションにプロビジョニングするように Microsoft Entra ID を構成するためのガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sql-connector-configure)に従います。
    - アプリケーションが SAP Cloud Identity Services を使う場合は、[SAP Cloud Identity Services にユーザーをプロビジョニングするための Microsoft Entra ID の構成ガイド](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial)に従います。
    - 他のアプリケーションの場合は、次の手順 1 から 3 に従って、[Graph API を介してプロビジョニングを構成します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api)。
3. アプリケーションの **[プロパティ]** タブを選択します。 **[ユーザーの割り当てが必要ですか?]** オプションが **[はい]** に設定されていることを確認します。 **[いいえ]** に設定されている場合は、外部 ID を含むディレクトリ内のすべてのユーザーがアプリケーションにアクセスでき、アプリケーションへのアクセスをレビューすることはできません。
4. そのアプリケーションへのプロビジョニングの[属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を確認します。 照合のために前のセクションで使用した Microsoft Entra の属性と列に対して **[この属性を使用してオブジェクトを照合する]** が設定されていることを確認します。
5. アプリケーションの属性に `isSoftDeleted` の属性マッピングがあることを確認します。

    ユーザーがアプリケーションから割り当て解除されるか、Microsoft Entra ID で論理的に削除されるか、またはサインインからブロックされると、Microsoft Entra プロビジョニングでは `isSoftDeleted` にマップされた属性が更新されます。 マップされた属性がない場合、後でアプリケーション ロールから割り当て解除されたユーザーは、引き続きアプリケーションのデータ ストアに存在します。
6. アプリケーションのプロビジョニングが既に有効になっている場合は、アプリケーションのプロビジョニングが[検疫](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)状態になっていないことを確認します。 先に進む前に、検疫の原因となっている問題をすべて解決します。

### アプリケーションから既存のユーザーを収集し、Microsoft Entra ID ユーザーと一致するものを確認する

プロビジョニング構成の一部として接続の詳細と一致する属性を指定したので、Microsoft Entra はアプリケーション内の既存のユーザーを検出できます。 プロビジョニングの概要ページの [ [ID の検出](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery) ] ボタンをクリックします。 レポートが生成されると、アプリケーション内のすべてのユーザーのビューが表示されます。アプリケーション内のユーザーは Microsoft Entra ID ユーザーと一致し、どのユーザーは Microsoft Entra ID でエンタープライズ アプリケーションに既に割り当て済みで、アプリケーション内のどのユーザーが Microsoft Entra ID ユーザーと一致しないかなどです。

### Microsoft Entra ID でアプリ ロールの割り当てを作成する

Microsoft Entra ID でアプリケーション内のユーザーを Microsoft Entra ID 内のユーザーと照合するには、Microsoft Entra ID でアプリケーション ロールの割り当てを作成する必要があります。 各アプリケーション ロールの割り当てにより、1 つのサービス プリンシパルの 1 つのアプリケーション ロールに 1 人のユーザーが関連付けられます。

ユーザーのアプリケーション ロールの割り当てが Microsoft Entra ID に作成されており、アプリケーションがプロビジョニングをサポートしている場合は、次のようになります。

- Microsoft Entra ID は、SCIM、またはそのディレクトリまたはデータベースを介してアプリケーションにクエリを実行し、ユーザーが既に存在するかどうかを判断します。
- Microsoft Entra ID でユーザーの属性に対して後続の更新が行われると、Microsoft Entra ID によってそれらの更新がアプリケーションに送信されます。
- ユーザーは、Microsoft Entra ID の外部で更新されない限り、または Microsoft Entra ID 内の割り当てが削除されるまで無期限にアプリケーション内に残ります。
- そのアプリケーションのロールの割り当ての次回のアクセス レビューでは、そのユーザーがアクセス レビューに含まれます。
- アクセス レビューでユーザーが拒否された場合、そのアプリケーション ロールの割り当ては削除されます。 Microsoft Entra ID では、ユーザーがサインインからブロックされことをアプリケーションに通知します。

アプリケーションがプロビジョニングをサポートしていない場合は、次のようになります

- ユーザーは、Microsoft Entra ID の外部で更新されない限り、または Microsoft Entra ID 内の割り当てが削除されるまで無期限にアプリケーション内に残ります。
- そのアプリケーションのロール割り当ての次回のレビューでは、そのユーザーがレビューに含まれます。
- アクセス レビューでユーザーが拒否された場合、そのアプリケーション ロールの割り当ては削除されます。 ユーザーは Microsoft Entra ID からアプリケーションにサインインできなくなります。

アカウント検出レポートの生成が完了したアプリケーションの場合は、次の手順を使用して、これらのユーザーをエンタープライズ アプリケーションに割り当てることを自動化できます。

1. CorrelatedUsers.ps1 ファイルを[ダウンロード](https://aka.ms/AssignCorrelatedUsersPowerShell)します。
2. ドライラン モードで現在ロールの割り当てを持っていないユーザーのアプリケーション ロールの割り当てを作成します。 これにより、割り当てられるユーザーをアプリケーションに割り当てずに確認できます。

    ```powershell
    .\Assign-CorrelatedUsers.ps1 -ServicePrincipalId "InputServicePrincipalIdHere" -DryRun
    ```
3. 現在ロールの割り当てがないユーザーに対してアプリケーション ロールの割り当てを作成します。

    ```powershell
    .\Assign-CorrelatedUsers.ps1 -ServicePrincipalId "InputServicePrincipalIdHere"
    ```
4. 変更が Microsoft Entra ID 内で伝達されるまで 1 分待ちます。

### Microsoft Entra プロビジョニングが既存のユーザーと一致していることを確認する

1. すべてのユーザーがエンタープライズ アプリケーションに正常に割り当てられていることを確認するには、[ID の検出] を選択して別のレポートを作成し、一致するすべてのユーザーがアプリケーションに割り当てられていることを確認します。
2. アプリケーション サービス プリンシパルがプロビジョニング用に構成されており、サービス プリンシパルの **[プロビジョニング状態]** が **[オフ]** の場合は、それを **[オン]** にします。 [Graph API を使って](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api#step-4-start-the-provisioning-job)プロビジョニングを開始することもできます。
3. 「[ユーザーをプロビジョニングするにはどのくらいの時間がかかりますか](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user#how-long-will-it-take-to-provision-users)」というガイダンスに基づき、Microsoft Entra プロビジョニングによって、アプリケーションの既存のユーザーと割り当てられたばかりのユーザーが照合されるのを待ちます。
4. ポータルまたは [Graph API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning) を使って[プロビジョニングの状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api#monitor-the-provisioning-job-status)を監視し、すべてのユーザーが正常に一致したことを確認します。

    プロビジョニングされているユーザーが表示されない場合は、[ユーザーがプロビジョニングされていない問題に関するトラブルシューティング ガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-no-users-provisioned)を確認してください。 プロビジョニング状態にエラーが表示され、オンプレミス アプリケーションにプロビジョニングしている場合は、[オンプレミス アプリケーションのプロビジョニングに関するトラブルシューティング ガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ecma-troubleshoot)を確認してください。
5. [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)または [Graph API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api#monitor-provisioning-events-using-the-provisioning-logs) 経由でプロビジョニング ログを確認します。 ログを状態 **[失敗]** でフィルター処理します。 **DuplicateTargetEntries** のエラー コードで失敗が発生している場合、これはプロビジョニング照合規則でのあいまいさを示しており、各 Microsoft Entra ユーザーが確実に 1 人のアプリケーション ユーザーに一致するように Microsoft Entra ユーザーまたは照合に使用されているマッピングを更新する必要があります。 次に、ログをアクション **[作成]** と状態 **[スキップ済み]** でフィルター処理します。 ユーザーが **NotEffectivelyEntitled** の SkipReason コードでスキップされた場合、これは、ユーザー アカウントの状態が **[無効]** であったために Microsoft Entra ID 内のユーザー アカウントが一致しなかったことを示している可能性があります。

作成したアプリケーション ロールの割り当てに基づいて Microsoft Entra プロビジョニング サービスがユーザーの照合を完了すると、それらのユーザーに対する以降の変更はアプリケーションに送信されます。

### 適切なレビュー担当者を選択する

各アクセス レビューを作成するとき、管理者は 1 人以上のレビュー担当者を選ぶことができます。 レビュー担当者は、レビューを実行し、リソースに継続的にアクセスするユーザーを選択または削除することができます。

通常は、リソースの所有者がレビューの実行を担当します。 パターン B で統合されたアプリケーションのアクセス レビューの一環として、グループのレビューを作成する場合は、グループ所有者をレビュー担当者として選択できます。 Microsoft Entra ID のアプリケーションには所有者がいるとは限らないため、アプリケーションの所有者をレビュー担当者として選択することはできません。 代わりに、レビューを作成するときに、アプリケーション所有者の名前をレビュー担当者として指定できます。

グループまたはアプリケーションのレビューを作成するときに、[複数ステージのレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review#create-a-multi-stage-access-review)の作成を選ぶこともできます。 たとえば、割り当てられた各ユーザーのマネージャーがレビューの最初のステージを実行し、リソース所有者が 2 番目のステージを実行するようにできます。 こうすることで、リソース所有者は、マネージャーによって既に承認されているユーザーに集中できます。

レビューを作成する前に、テナントに十分な Microsoft Entra ID P2 または Microsoft Entra ID ガバナンス SKU シートがあることを確認します。 また、すべてのレビュー担当者がメール アドレスを持つアクティブなユーザーであることを確認します。 アクセス レビューが始まったら、各自が Microsoft Entra ID からのメールをレビューします。 レビュー担当者がメールボックスを持っていない場合、レビュー開始時のメールまたはメール リマインダーを受け取りません。 また、Microsoft Entra ID へのサインインをブロックされている場合は、レビューを実行できません。

### アクセス レビューまたはエンタイトルメント管理を構成する

ユーザーにアプリケーション ロールを割り当て、レビュー担当者を指定したら、アクセス レビューまたはエンタイトルメント管理を使用して、それらのユーザーと、アクセスを必要とするその他のユーザーを管理できるようになります。

- アプリケーションが持っているアプリケーション ロールが 1 つのみであり、アプリケーションはディレクトリ内の 1 つのサービス プリンシパルによって表され、アプリケーションにアクセスする必要があるユーザーが他にいない場合は、次のセクションに進み、アクセス レビューを使用して既存のアクセスをレビューし削除します。
- それ以外の場合は、この記事の「エンタイトルメント管理を使用してアクセスを管理する」のセクションに進みます。

#### アプリ ロールの割り当てのアクセス レビューを実施し、既存のアクセスを確認および削除する。

アプリケーションに複数のアプリケーション ロールがあり、複数のサービス プリンシパルで表されている場合、またはユーザーがアプリケーションへのアクセスを要求または割り当てるプロセスを用意する場合は、この記事の次のセクションに進み、エンタイトルメント管理を使用してアクセスを管理します。

既存のユーザーがアプリケーション ロールに割り当てられているので、これらの割り当ての[レビューを開始](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-application-preparation#create-the-reviews)するように Microsoft Entra ID を構成できます。

1. この手順では、全体管理者または ID ガバナンス管理者ロールに属している必要があります。
2. [グループまたはアプリケーションのアクセス レビューの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)に関するガイドの指示に従って、アプリケーションのロール割り当てのレビューを作成します。 完了時に結果を適用するようにレビューを構成します。 PowerShell で [Identity Governance の Microsoft Graph PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph.Identity.Governance/) モジュールの `New-MgIdentityGovernanceAccessReviewDefinition` コマンドレットを使ってアクセス レビューを作成することができます。 詳細については、 [例](https://learn.microsoft.com/ja-jp/graph/api/accessreviewset-post-definitions?view=graph-rest-1.0&tabs=powershell#examples&preserve-view=true) を参照してください。

    注

    アクセス レビューの作成時にレビュー判断ヘルパーを有効にした場合、ユーザーが Microsoft Entra ID を使用してアプリケーションに最後にサインインした日時に応じて、判断ヘルパーの推奨事項は 30 日間の間隔に基づいて決まります。
3. アクセス レビューの開始時には、レビュー担当者に入力するよう依頼してください。 既定では、それぞれが、アクセス パネルへのリンクが記載されたメールを Microsoft Entra ID から受け取り、そこで[アプリケーションへのアクセスをレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/perform-access-review)します。
4. レビューが開始したら、[アクセス レビューが完了する](https://learn.microsoft.com/ja-jp/entra/id-governance/complete-access-review)まで、その進行状況を監視し、必要に応じて承認者を更新できます。 その後、レビュー担当者によってアクセスを拒否されたユーザーのアクセス権が、アプリケーションから削除されていることを確認できます。
5. レビューの作成時に自動適用を選ばなかった場合は、完了時にレビュー結果を適用する必要があります。
6. レビューの状態が **[結果を適用済み]** に変わるまで待ちます。 拒否されたユーザーが存在する場合は、そのアプリケーション ロールの割り当てが数分以内に削除されることが予想されます。
7. 結果が適用されると、Microsoft Entra ID は、アプリケーションから拒否されたユーザーのプロビジョニング解除を開始します。 [ユーザーのプロビジョニングにかかる時間のガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user#how-long-will-it-take-to-provision-users)に基づいて、Microsoft Entra プロビジョニングが拒否されたユーザーのプロビジョニング解除を開始するまで待ちます。 ポータルまたは [Graph API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning) を使って[プロビジョニングの状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api#monitor-the-provisioning-job-status)を監視し、拒否されたすべてのユーザーが正常に削除されたことを確認します。

    プロビジョニングを解除されたユーザーが表示されない場合は、[ユーザーがプロビジョニングされていない問題に関するトラブルシューティング ガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-no-users-provisioned)を確認してください。 プロビジョニング状態にエラーが表示され、オンプレミス アプリケーションにプロビジョニングしている場合は、[オンプレミス アプリケーションのプロビジョニングに関するトラブルシューティング ガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ecma-troubleshoot)を確認してください。

既存のアクセスが確認されたことを確認するベースラインが作成されたので、次のセクションでエンタイトルメント管理を構成し、新しいアクセス要求を有効にすることができます。

#### エンタイトルメント管理を使用してアクセスを管理する

アプリケーション ロールごとに異なるレビュー担当者を指定する場合、アプリケーションが複数のサービス プリンシパルによって表される場合、またはユーザーがアプリケーションへのアクセスを要求する、または割り当てを受けるプロセスを用意する場合など、その他の状況では、アプリケーション ロールごとに[アクセス パッケージ](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/tutorial-entitlement-management)で Microsoft Entra ID を構成することができます。 各アクセス パッケージには、そのアクセス パッケージに対して行われる割り当ての定期的なレビューのポリシーを設定することができます。 アクセス パッケージとポリシーを作成したら、既存のアプリケーション ロールの割り当てを持つユーザーをアクセス パッケージに割り当てて、アクセス パッケージを介して割り当てを確認できるようにします。

このセクションでは、アプリ ロールの割り当てを含むアクセス パッケージの割り当てのレビュー用に Microsoft Entra エンタイトルメント管理を構成し、ユーザーがアプリケーションのロールへのアクセスを要求できるように追加のポリシーを構成します。

1. このステップを行うには、グローバル管理者または ID ガバナンス管理者ロールに属しているか、または[カタログ作成者として委任](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate-catalog)されていてアプリケーションの所有者である必要があります。
2. アプリケーション ガバナンス シナリオのカタログがまだない場合は、Microsoft Entra エンタイトルメント管理で [\[カタログの作成\]](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create) を行ってください。 PowerShell スクリプトを使用して、PowerShell を使ったカタログの作成に示すように、各カタログを 作成できます。
3. カタログに必要なリソースを設定するには、アプリケーションと、アプリケーションが依存するすべての Microsoft Entra グループを[そのカタログ内のリソースとして](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#add-resources-to-a-catalog)追加します。 PowerShell スクリプトを使用して、カタログに各リソース 追加できます。次に示すように、アプリケーションをリソースとしてカタログに追加 。
4. アプリケーションごとに、またそれらのアプリケーションのロールまたはグループごとに、そのロールまたはグループをリソースとして含む[アクセス パッケージを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)します。 これらのアクセス パッケージを設定するこの段階では、各アクセス パッケージの最初のアクセス パッケージ割り当てポリシーを、[直接割り当て](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#none-administrator-direct-assignments-only)のポリシーとして構成します。これによって管理者のみがそのポリシーに割り当てを作成できるようにし、既存ユーザーのアクセス レビュー要件（ある場合）を設定して、アクセスが無期限に続かないようにします。 アクセス パッケージが多数ある場合は、[1 つのロールを持つアプリケーションのアクセス パッケージを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#create-an-access-package-by-using-microsoft-powershell)方法に関するページに示すように、PowerShell スクリプトを使用して[カタログ内に各アクセス パッケージを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create-app#create-an-access-package-in-entitlement-management-for-an-application-with-a-single-role-using-powershell)ことができます。
5. アクセスパッケージごとに、そのロールまたはグループのメンバーであるアプリケーションの既存ユーザーを、アクセスパッケージとその直接割り当てポリシーに割り当てます。 [既存のユーザーの割り当てを追加する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments)方法に関するページに示すように、Microsoft Entra 管理センターを使用してアクセス パッケージに[直接ユーザーを割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#assign-a-user-to-an-access-package-with-powershell)か、Graph または [PowerShell](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create-app#add-assignments-of-existing-users-who-already-have-access-to-the-application) を使用して一括で割り当てることができます。
6. アクセス パッケージの割り当てポリシーでアクセス レビューを構成した場合は、アクセス レビューが開始されたら、レビュー担当者に入力を依頼します。 既定では、それぞれが、アクセス パネルへのリンクが記載されたメールを Microsoft Entra ID から受け取り、そこでアクセス パッケージの割り当てをレビューします。 レビューを完了したときに、拒否されたユーザーが存在する場合は、そのアプリケーション ロールの割り当てが数分以内に削除されることが予想されます。 その後、Microsoft Entra ID は、アプリケーションから拒否されたユーザーのプロビジョニング解除を開始します。 [ユーザーのプロビジョニングにかかる時間のガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user#how-long-will-it-take-to-provision-users)に基づいて、Microsoft Entra プロビジョニングが拒否されたユーザーのプロビジョニング解除を開始するまで待ちます。 ポータルまたは [Graph API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning) を使って[プロビジョニングの状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api#monitor-the-provisioning-job-status)を監視し、拒否されたすべてのユーザーが正常に削除されたことを確認します。
7. [職務の分離](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-incompatible)要件がある場合は、互換性のないアクセス パッケージまたは既存のグループをアクセス パッケージ用に構成します。 シナリオで職務の分離チェックをオーバーライドする機能が必要な場合は、[それらのオーバーライド シナリオ用に追加のアクセス パッケージを設定](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-incompatible#configuring-multiple-access-packages-for-override-scenarios)することもできます。
8. アクセス権をまだ持っていないユーザーがアクセスを要求できるようにする場合は、各アクセス パッケージでユーザーがアクセスを要求するための追加のアクセス パッケージ割り当てポリシー[を作成してください](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#open-an-existing-access-package-and-add-a-new-policy-with-different-request-settings)。 そのポリシーで承認と定期的なアクセス レビューの要件を構成します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/identity-governance-applications-integrate"} -->
## ID ガバナンスのためにアプリケーションを統合し、レビューされたアクセスのベースラインを確立する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-integrate
- Service: entra-id-governance
- Article date: 2024-11-25
- Summary: Microsoft Entra ID Governance を使うと、セキュリティや従業員の生産性に対する組織のニーズと、適切なプロセスや可視性とのバランスを取ることができます。  ID ガバナンス シナリオでは、既存のビジネス クリティカルなサード パーティのオンプレミスおよびクラウドベースのアプリケーションを Microsoft Entra ID と統合できます。

アプリケーションへのアクセス権 [を](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-define) 持つ必要があるユーザーのポリシーを確立したら、 [アプリケーションを Microsoft Entra ID に接続](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-application-management) し、それらのアプリケーションへのアクセスを管理するための [ポリシーを展開](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-deploy) できます。

Microsoft Entra ID ガバナンスは、SAP R/3、SAP S/4HANA、OpenID Connect、SAML、SCIM、SQL、LDAP、SOAP、REST などの [標準](https://learn.microsoft.com/ja-jp/entra/architecture/auth-sync-overview) を使用する多くのアプリケーションと統合できます。 これらの標準を使用すると、組織が開発したアプリケーションなど、多くの一般的な SaaS アプリケーションとオンプレミス アプリケーションで Microsoft Entra ID を使用できます。 この展開計画では、アプリケーションを Microsoft Entra ID に接続し、そのアプリケーションで ID ガバナンス機能を使用できるようにする方法について説明します。

アプリケーションに Microsoft Entra ID ガバナンスを使用するには、まずアプリケーションを Microsoft Entra ID と統合し、ディレクトリで表す必要があります。 アプリケーションを Microsoft Entra ID と統合するには、次の 2 つの要件のいずれかを満たす必要があります。

- アプリケーションが、フェデレーション SSO を Microsoft Entra ID に依存し、Microsoft Entra ID が認証トークンの発行を制御します。 Microsoft Entra ID がアプリケーションの唯一の ID プロバイダーである場合、Microsoft Entra ID でアプリケーションのいずれかのロールに割り当てられているユーザーのみがアプリケーションにサインインできます。 アプリケーション ロールの割り当てを失ったユーザーは、アプリケーションにサインインするための新しいトークンを取得できなくなります。
- アプリケーションが、Microsoft Entra ID によってアプリケーションに提供されるユーザーまたはグループの一覧に依存します。 このフルフィルメントは、SCIM などのプロビジョニング プロトコル、Microsoft Graph を介して Microsoft Entra ID を照会するアプリケーション、または AD Kerberos を使用してユーザーのグループ メンバーシップを取得するアプリケーションによって実行できます。

アプリケーションが Microsoft Entra ID に依存していない場合など、アプリケーションに対してこれらの条件の両方が満たされていない場合でも、ID ガバナンスを使用できます。 ただし、条件を満たさずに ID ガバナンスを使用する場合は、いくつかの制限が存在する可能性があります。 たとえば、Microsoft Entra ID に含まれていないユーザーや、Microsoft Entra ID のアプリケーション ロールに割り当てられていないユーザーは、アプリケーション ロールに割り当てるまで、アプリケーションのアクセス レビューに含まれません。 詳細については、 [アプリケーションへのユーザーのアクセスのアクセス レビューの準備を](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-application-preparation)参照してください。

### 承認されたユーザーのみがアプリケーションにアクセスできるように、アプリケーションを Microsoft Entra ID と統合する

アプリケーション プロセスの統合は、ユーザー認証に Microsoft Entra ID に依存するようにアプリケーションを構成し、フェデレーション シングル サインオン (SSO) プロトコル接続を使用して、プロビジョニングを追加すると開始されます。 SSO に最もよく使用されるプロトコルは、 [SAML と OpenID Connect です](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols)。 [アプリケーション認証を検出して Microsoft Entra ID に移行](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-apps-phases-overview)するためのツールとプロセスの詳細を確認できます。

次に、アプリケーションがプロビジョニング プロトコルを実装する場合は、ユーザーがアクセス権を付与されたとき、またはユーザーのアクセス権が削除されたときに Microsoft Entra ID がアプリケーションに通知できるように、ユーザーをアプリケーションにプロビジョニングするように Microsoft Entra ID を構成する必要があります。 これらのプロビジョニング シグナルにより、管理者に任せていた従業員が作成したコンテンツを再割り当てするなど、アプリケーションで自動修正を行うことができます。

1. [アプリケーションがエンタープライズ アプリケーションの一覧またはアプリ登録の](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/view-applications-portal)[一覧](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)に含まれているかどうかを確認します。 アプリケーションがテナントに既に存在する場合は、このセクションの手順 5 に進みます。
2. アプリケーションがテナントにまだ登録されていない SaaS アプリケーションの場合は、フェデレーション SSO 用に統合できる [アプリケーション ギャラリー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/overview-application-gallery) で使用可能なアプリケーションを確認します。 ギャラリー内にある場合は、チュートリアルを使用して、アプリケーションを Microsoft Entra ID と統合します。

    1. [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)に従って、Microsoft Entra ID を使用してフェデレーション SSO 用にアプリケーションを構成します。
    2. アプリケーションがプロビジョニングをサポートしている場合は、プロビジョニング [用にアプリケーションを構成します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-automatic-user-provisioning-portal)。
    3. 完了したら、この記事の次のセクションに進んでください。 SaaS アプリケーションがギャラリーにない場合は、 [SaaS ベンダーにオンボードを依頼](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)します。
3. これがプライベート またはカスタム アプリケーションの場合は、アプリケーションの場所と機能に基づいて、最も適切なシングル サインオン統合を選択することもできます。

    - このアプリケーションが SAP Business Technology Platform (BTP) 上にある場合は、Microsoft Entra と SAP Cloud Identity Services の統合を構成します。 詳細については、 [Microsoft Entra SSO と SAP BTP の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-tutorial) と [SAP BTP へのアクセスの管理に関するページを](https://community.sap.com/t5/technology-blogs-by-members/identity-and-access-management-with-microsoft-entra-part-i-managing-access/ba-p/13873276)参照してください。
    - このアプリケーションがパブリック クラウドにあり、シングル サインオンをサポートしている場合は、Microsoft Entra ID からアプリケーションに直接シングル サインオンを構成します。

        | アプリケーションのサポート | 次のステップ |
        | --- | --- |
        | OpenID Connect | [OpenID Connect OAuth アプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/openidoauth-tutorial) |
        | SAML 2.0 | アプリケーションを登録し、[Microsoft Entra ID の SAML エンドポイントと証明書](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-protocol-reference)を使用してアプリケーションを構成する |
        | SAML 1.1 | [SAML ベースのアプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/saml-tutorial) |
    - これが SAP GUI を使用する SAP アプリケーションの場合は、SAP [Secure Login Service との統合](https://community.sap.com/t5/technology-blogs-by-members/sap-gui-mfa-with-microsoft-entra-part-i-integration-with-sap-secure-login/ba-p/13605383) または Microsoft Entra Private Access との統合を使用して、シングル サインオン用 [に Microsoft Entra を統合](https://community.sap.com/t5/technology-blogs-by-members/sap-gui-mfa-with-microsoft-entra-part-ii-integration-with-microsoft-entra/ba-p/13691141)します。
    - それ以外の場合、これがシングル サインオンをサポートするオンプレミスまたは IaaS でホストされているアプリケーションの場合は、アプリケーション プロキシを介して Microsoft Entra ID からアプリケーションへのシングル サインオンを構成します。

        | アプリケーションのサポート | 次のステップ |
        | --- | --- |
        | SAML 2.0 | [アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)をデプロイし、[SAML SSO](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/conceptual-sso-apps) 用にアプリケーションを構成する |
        | 統合 Windows 認証 (IWA) | [アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)をデプロイし、[統合 Windows 認証 SSO 用に](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso-with-kcd)アプリケーションを構成し、プロキシ経由を除くアプリケーションのエンドポイントへのアクセスを禁止するファイアウォール規則を設定します。 |
        | ヘッダーベースの認証 | [アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)をデプロイし、[ヘッダーベースの SSO](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-single-sign-on-with-headers) 用にアプリケーションを構成する |
4. アプリケーションが SAP BTP 上にある場合は、Microsoft Entra グループを使用して各ロールのメンバーシップを維持できます。 グループを BTP ロール コレクションに割り当てる方法の詳細については、 [SAP BTP へのアクセスの管理を](https://community.sap.com/t5/technology-blogs-by-members/identity-and-access-management-with-microsoft-entra-part-i-managing-access/ba-p/13873276)参照してください。
5. アプリケーションに複数のロールがある場合、各ユーザーはアプリケーションに 1 つのロールしか持っていなくても、アプリケーションは Microsoft Entra ID に依存して、アプリケーションにサインインするユーザーの要求としてユーザーの単一のアプリケーション固有のロールを送信し、アプリケーションの Microsoft Entra ID でそれらのアプリ ロールを構成してから、各ユーザーをアプリケーション ロールに割り当てます。 [アプリ ロール UI](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) を使用して、これらのロールをアプリケーション マニフェストに追加できます。 Microsoft 認証ライブラリを使用している場合は、アプリケーション内のアプリ ロールを使用してアクセス制御を行う方法の [コード サンプル](https://learn.microsoft.com/ja-jp/entra/identity-platform/sample-v2-code) があります。 ユーザーが複数のロールを同時に持つことができる場合は、アクセス制御にアプリ マニフェストのアプリ ロールを使用する代わりに、トークン要求または Microsoft Graph を介して使用できるセキュリティ グループを確認するアプリケーションを実装できます。
6. アプリケーションがプロビジョニングをサポートしている場合は、割り当てられたユーザーとグループの Microsoft Entra ID からそのアプリケーションへの [プロビジョニングを構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-automatic-user-provisioning-portal) します。 これがプライベート またはカスタム アプリケーションの場合は、アプリケーションの場所と機能に基づいて、最も適切な統合を選択することもできます。

    - このアプリケーションが SAP Cloud Identity Services に依存している場合は、SCIM 経由で SAP Cloud Identity Services へのユーザーのプロビジョニングを構成します。

        | アプリケーションのサポート | 次のステップ |
        | --- | --- |
        | SAP Cloud Identity Services | [SAP Cloud Identity Services にユーザーをプロビジョニングするように Microsoft Entra ID を構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial) |
    - このアプリケーションがパブリック クラウドにあり、SCIM をサポートしている場合は、SCIM 経由でユーザーのプロビジョニングを構成します。

        | アプリケーションのサポート | 次のステップ |
        | --- | --- |
        | SCIM | [ユーザー プロビジョニング用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)に SCIM を使用してアプリケーションを構成する |
    - このアプリケーションで AD を使用する場合は、グループの書き戻しを構成し、Microsoft Entra ID で作成されたグループを使用するようにアプリケーションを更新するか、Microsoft Entra ID で作成されたグループをアプリケーションの既存の AD セキュリティ グループに入れ子にします。

        | アプリケーションのサポート | 次のステップ |
        | --- | --- |
        | Kerberos | AD への Microsoft Entra Cloud Sync [グループ ライトバックを構成し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)、Microsoft Entra ID でグループを作成し、 [それらのグループを AD に書き込む](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-group-writeback) |
    - それ以外の場合、これがオンプレミスまたは IaaS でホストされているアプリケーションであり、AD と統合されていない場合は、SCIM を介して、またはアプリケーションの基になるデータベースまたはディレクトリに対して、そのアプリケーションへのプロビジョニングを構成します。

        | アプリケーションのサポート | 次のステップ |
        | --- | --- |
        | SCIM | [オンプレミスの SCIM ベースのアプリのプロビジョニング エージェントを使用してアプリケーションを構成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-scim-provisioning) |
        | SQL データベースに格納されているローカル ユーザー アカウント | [オンプレミスの SQL ベースのアプリケーションのプロビジョニング エージェント](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sql-connector-configure)を使用してアプリケーションを構成する |
        | LDAP ディレクトリに格納されているローカル ユーザー アカウント | [オンプレミス LDAP ベースのアプリケーションのプロビジョニング エージェント](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure)を使用してアプリケーションを構成する |
        | SOAP または REST API を使用して管理されるローカル ユーザー アカウント | [Web サービス コネクタを使用してプロビジョニング エージェントを使用してアプリケーションを構成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-web-services-connector) |
        | MIM コネクタを使用して管理されるローカル ユーザー アカウント | カスタム コネクタを使用して [プロビジョニング エージェントを使用してアプリケーションを構成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-custom-connector) |
        | NetWeaver AS ABAP 7.0 以降を使用した SAP ECC | [SAP ECC で構成された Web サービス コネクタを使用してプロビジョニング エージェントを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sap-connector-configure)してアプリケーションを構成する |
7. アプリケーションで Microsoft Graph を使用して Microsoft Entra ID のグループにクエリを実行する場合は、テナントから読み取るための適切なアクセス許可をアプリケーションに [付与することを同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/application-consent-experience) します。
8. アプリケーションへのアクセス **が許可されるのは、アプリケーションに割り当てられているユーザーに対してのみ設定します**。 この設定により、条件付きアクセス ポリシーが有効になる前に、ユーザーが誤って MyApps にアプリケーションを表示したり、アプリケーションにサインインしたりできなくなります。

### 最初のアクセス レビューを実行する

これが組織が以前に使用したことがない新しいアプリケーションであるため、既存のアクセス権を持っていない場合、またはこのアプリケーションのアクセス レビューを既に実行している場合は、 [次のセクション](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-deploy)に進みます。

ただし、アプリケーションが既に環境内にある場合、ユーザーは過去に手動プロセスまたは帯域外プロセスを通じてアクセス権を取得している可能性があります。 これらのユーザーを確認して、アクセスがまだ必要であり、適切であることを確認する必要があります。 より多くのユーザーがアクセスを要求できるようにするポリシーを有効にする前に、アプリケーションへのアクセス権を既に持っているユーザーのアクセス レビューを実行することをお勧めします。 このレビューでは、すべてのユーザーが少なくとも 1 回レビューされ、継続的なアクセスが承認されていることを確認するベースラインが設定されます。

1. [「アプリケーションへのユーザーのアクセスのアクセス レビューの準備」の手順に](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-application-preparation)従います。
2. アプリケーションが Microsoft Entra ID または AD を使用していなかったが、プロビジョニング プロトコルをサポートしている場合、または基になる SQL または LDAP データベースがある場合は、既存のユーザーを取り込み、アプリケーション [ロールの割り当てを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-existing-users) します。
3. アプリケーションで Microsoft Entra ID または AD が使用されておらず、プロビジョニング プロトコルがサポートされていない場合は、 [アプリケーションからユーザーの一覧を取得し、それぞれのアプリケーション ロールの割り当てを作成します](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-not-provisioned-users)。
4. アプリケーションで AD セキュリティ グループを使用していた場合は、それらのセキュリティ グループのメンバーシップを確認する必要があります。
5. アプリケーションに独自のディレクトリまたはデータベースがあり、プロビジョニング用に統合されていない場合は、レビューが完了したら、アプリケーションの内部データベースまたはディレクトリを手動で更新して、拒否されたユーザーを削除する必要があります。
6. アプリケーションが AD セキュリティ グループを使用していて、それらのグループが AD で作成された場合、レビューが完了したら、AD グループを手動で更新して、拒否されたユーザーのメンバーシップを削除する必要があります。 その後、自動的に削除されたアクセス権を拒否するには、Microsoft Entra ID で作成され、Microsoft [Entra ID に書き戻](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-configure-entra-to-active-directory)された AD グループを使用するようにアプリケーションを更新するか、AD グループから Microsoft Entra グループにメンバーシップを移動し、 [書き戻されたグループを AD グループの唯一のメンバーとして入れ子にすることができます](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/govern-on-premises-groups)。
7. レビューが完了し、アプリケーションアクセスが更新されたら、またはアクセス権を持つユーザーがいない場合は、次の手順に進み、アプリケーションの条件付きアクセスとエンタイトルメント管理ポリシーを展開します。

カスタム データ提供リソース (プレビュー) を使用して、アクセス レビューの直前にアクセス データをアップロードすることで、Microsoft Entra ID アクセス レビューにアプリケーションからのアクセス権を含めることができます。 詳細については、「 [カタログ ユーザーのアクセス レビュー (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/id-governance/custom-data-resource-access-reviews)を参照してください。

既存のアクセスが確認されたことを確認するベースラインが作成されたので、継続的なアクセスと新しいアクセス要求に対する [組織のポリシーを展開](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-deploy) できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/identity-governance-applications-not-provisioned-users"} -->
## Microsoft Entra ID のプロビジョニングをサポートしないアプリケーションの既存のユーザーを Microsoft PowerShell を使用して管理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-not-provisioned-users
- Service: entra-id-governance
- Article date: 2026-04-13
- Summary: 特定のアプリケーションのアクセス レビュー キャンペーンを成功させるための計画には、そのアプリケーションのいずれかのユーザーに Microsoft Entra ID から派生していないアクセス権があるかどうかを識別することが含まれます。  アプリケーションがプロビジョニングをサポートしていない場合、そのアプリケーション用にアプリケーション ロールの割り当てを作成し、レビューが完了したら変更一覧を作成する必要があります。

アクセス レビューなどの Microsoft Entra ID ガバナンス機能でアプリケーションを使用する前に、既存のユーザーとアプリケーションのアクセス権を Microsoft Entra ID に設定する必要がある 4 つの一般的なシナリオ [があります](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-application-preparation)。

- アプリケーションが独自の ID プロバイダーを使用した後に Microsoft Entra ID に移行された場合
- Microsoft Entra ID を唯一の ID プロバイダーとして使用しないアプリケーション
- アプリケーションが ID プロバイダーとして Microsoft Entra ID を使用しておらず、プロビジョニングもサポートしていない
- アプリケーションは、Id プロバイダーとして Microsoft Entra ID を使用し、ユーザーに対する追加のアクセス権を持っています

最初の 2 つのシナリオ (アプリケーションがプロビジョニングをサポートしている、または LDAP ディレクトリ、SQL データベースを使っている、SOAP または REST API がある、または ID プロバイダーとして Microsoft Entra ID を利用している) の詳細については、記事「[アプリケーションの既存のユーザーを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-existing-users)」を参照してください。 この記事では、これらのカテゴリのアプリケーションの既存ユーザーに対して ID ガバナンス機能を使う方法について説明します。

この記事では、3 つ目のシナリオについて説明します。 一部のレガシ アプリケーションでは、他の ID プロバイダーまたはローカル資格情報認証をアプリケーションから削除したり、それらのアプリケーションのプロビジョニング プロトコルのサポートを有効にしたりできない場合があります。 このようなアプリケーションの場合、Microsoft Entra ID を使用してそのアプリケーションにアクセスできるユーザーをレビューしたり、そのアプリケーションからだれかのアクセス権を削除したりするには、アプリケーション ユーザーを表す割り当てを Microsoft Entra ID 内に作成する必要があります。 この記事では、ID プロバイダーとして Microsoft Entra ID を使用せず、プロビジョニングをサポートしていないアプリケーションのシナリオについて説明します。

4 番目のシナリオの詳細については、 [カタログ ユーザー アクセス レビュー (プレビュー) のカタログにカスタム データ提供リソースを含める](https://learn.microsoft.com/ja-jp/entra/id-governance/custom-data-resource-access-reviews)を参照してください。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID Governance または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。

### 用語

この記事では、[Microsoft Graph PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph)を使用して、アプリケーション ロールの割り当てを管理するためのプロセスを示しています。 ここでは、次の Microsoft Graph の用語を使用しています。

[Image: Microsoft Graph の用語を示す図。]

Microsoft Entra ID では、サービス プリンシパル (`ServicePrincipal`) は、特定の組織のディレクトリ内のアプリケーションを表します。 `ServicePrincipal` には、アプリケーションがサポートするロール (`AppRoles` など) を一覧表示する `Marketing specialist` という名前のプロパティがあります。 `AppRoleAssignment` はユーザーをサービス プリンシパルにリンクし、そのユーザーのそのアプリケーションでのロールを指定します。

ユーザーにアプリケーションへの期間限定のアクセス権を付与するために、[Microsoft Entra エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)アクセス パッケージを使用することもできます。 エンタイトルメント管理では、`AccessPackage` に 1 つ以上のリソース ロール (複数のサービス プリンシパルの可能性もあります) が含まれています。 `AccessPackage` には、アクセス パッケージへのユーザーの割り当て (`Assignment`) も含まれています。

アクセス パッケージへのユーザーの割り当てを作成すると、Microsoft Entra エンタイトルメント管理によって、各アプリケーションへのユーザーの必要な `AppRoleAssignment` インスタンスが自動的に作成されます。 詳細については、PowerShell を使用してアクセス パッケージを作成する方法を示す、[Microsoft Entra エンタイトルメント管理でリソースへのアクセスを管理する](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/tutorial-entitlement-management)方法に関するページ参照してください。

### 開始する前に

- テナントには次のいずれかのライセンスが必要です。

    - Microsoft Entra ID P2 または Microsoft Entra ID Governance
    - Enterprise Mobility + Security E5 ライセンス
- 適切な管理者ロールを持っている必要があります。 これらの手順を初めて実行している場合は、テナントでの Microsoft Graph PowerShell の使用を認可するグローバル管理者ロールが必要です。
- アプリケーションには、テナント内のサービス プリンシパルが必要です。 サービス プリンシパルがまだ存在しない場合は、それを表すアプリケーションを Microsoft Entra ID に登録します。

### アプリケーションから既存のユーザーを収集する

すべてのユーザーが確実に Microsoft Entra ID に記録されるようにするための最初の手順は、アプリケーションにアクセスできる既存のユーザーのリストを収集することです。

アプリケーションによっては、現在のユーザーの一覧をデータ ストアからエクスポートするための組み込みのコマンドを備えている場合があります。 その他、アプリケーションが外部のディレクトリまたはデータベースに依存する場合もあります。

一部の環境では、アプリケーションが、Microsoft Entra ID へのアクセスを管理するために適さないネットワーク セグメントまたはシステムに配置されていることがあります。 このシステムに Microsoft Graph PowerShell コマンドレットがインストールされていないか、または Microsoft Entra ID への接続がない場合は、ユーザーのリストを含む CSV ファイルを [Microsoft Graph PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph)がインストールされているシステムに転送します。

このセクションでは、コンマ区切り値 (CSV) ファイル内のユーザーの一覧を取得する 4 つの方法について説明します。

- LDAP ディレクトリから
- SQL Server データベースから
- 別の SQL ベースのデータベースから
- SAP Cloud Identity Services から

#### LDAP ディレクトリを使用するアプリケーションから既存のユーザーを収集する

このセクションは、Microsoft Entra ID に対して認証されないユーザーの基になるデータ ストアとして LDAP ディレクトリを使用するアプリケーションに適用されます。 Active Directory などの多くの LDAP ディレクトリには、ユーザーの一覧を出力するコマンドが含まれています。

1. そのディレクトリ内のユーザーのうちのどれだけが、アプリケーションのユーザーとしてのスコープ内に存在するかを識別します。 この選択は、アプリケーションの構成によって異なります。 一部のアプリケーションでは、LDAP ディレクトリに存在するすべてのユーザーが有効なユーザーとなります。 他のアプリケーションでは、ユーザーが特定の属性を持っているか、またはそのディレクトリ内のグループのメンバーであることが必要な場合もあります。
2. ディレクトリからそのユーザーのサブセットを取得するコマンドを実行します。 その出力には、Microsoft Entra ID との照合に使用されるユーザーの属性が必ず含まれるようにしてください。 これらの属性の例には、従業員 ID、アカウント名、電子メール アドレスなどがあります。

    たとえば、次のコマンドでは、LDAP ディレクトリ内のすべてのユーザーの `userPrincipalName` 属性を含む CSV ファイルを現在のファイル システム ディレクトリ内に生成します。

    ```powershell
    $out_filename = ".\users.csv"
    csvde -f $out_filename -l userPrincipalName,cn -r "(objectclass=person)"
    ```
3. 必要に応じて、ユーザーの一覧を含む CSV ファイルを [Microsoft Graph PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph)がインストールされているシステムに転送します。
4. 引き続き、この記事の後の方にある「Microsoft Entra ID にアプリケーションのユーザーに一致するユーザーが存在することを確認する」セクションを参照してください。

#### SQL Server ウィザードを使用してアプリケーションのデータベース テーブルから既存のユーザーを収集する

このセクションは、基になるデータ ストアとして SQL Server を使用するアプリケーションに適用されます。

まず、テーブルからユーザーの一覧を取得します。 ほとんどのデータベースは、テーブルの内容を CSV ファイルなどの標準ファイル形式にエクスポートする方法を提供しています。 アプリケーションで SQL Server データベースが使用されている場合は、SQL Server インポートおよびエクスポート ウィザードを使用して、データベースの一部をエクスポートすることができます。 データベース用のユーティリティがない場合は、次のセクションで説明されているように、PowerShell で ODBC ドライバーを使用できます。

1. SQL Server がインストールされているシステムにログインします。
2. **SQL Server 2019 のインポートとエクスポー (64 ビット)** またはデータベース用の同等のツールを開きます。
3. ソースとして既存のデータベースを選択します。
4. 変換先として、**フラットファイル変換先** を選択します。 ファイル名を指定し、**[コード ページ]** 値を **[65001 (UTF-8)]** に変更します。
5. ウィザードを完了し、すぐに実行するオプションを選択します。
6. 実行が完了するまで待ちます。
7. 必要に応じて、ユーザーの一覧を含む CSV ファイルを [Microsoft Graph PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph)がインストールされているシステムに転送します。
8. 引き続き、この記事の後の方にある「Microsoft Entra ID にアプリケーションのユーザーに一致するユーザーが存在することを確認する」セクションを参照してください。

#### PowerShell を使用してアプリケーションのデータベース テーブルから既存のユーザーを収集する

このセクションは、基になるデータ ストアとして別の SQL データベースを使用するアプリケーションに適用されます。そこでは、[ECMA Connector Host](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sql-connector-configure) を使用して、そのアプリケーションにユーザーをプロビジョニングしています。 プロビジョニング エージェントがまだ構成されていない場合は、そのガイドを使用して、このセクションで使用する DSN 接続ファイルを作成します。

1. プロビジョニング エージェントがインストールされている、またはインストールする予定のシステムにログインします。
2. PowerShell を開きます。
3. データベース システムに接続するための接続文字列を構築します。

    接続文字列のコンポーネントは、データベースの要件によって異なります。 SQL Server を使用している場合は、[DSN と接続文字列のキーワードと属性の一覧](https://learn.microsoft.com/ja-jp/sql/connect/odbc/dsn-connection-string-attribute)を参照してください。

    別のデータベースを使用している場合は、そのデータベースに接続するための必須キーワードを含める必要があります。 たとえば、データベースで DSN ファイルの完全修飾パス名、ユーザー ID、パスワードを使用している場合は、次のコマンドを使用して接続文字列を作成します。

    ```powershell
    $filedsn = "c:\users\administrator\documents\db.dsn"
    $db_cs = "filedsn=" + $filedsn + ";uid=p;pwd=secret"
    ```
4. 次のコマンドを使用して、データベースへの接続を開き、接続文字列を指定します。

    ```powershell
    $db_conn = New-Object data.odbc.OdbcConnection
    $db_conn.ConnectionString = $db_cs
    $db_conn.Open()
    ```
5. データベース テーブルからユーザーを取得する SQL クエリを作成します。 アプリケーションのデータベース内のユーザーを Microsoft Entra ID 内のユーザーと照合するために使用される列を必ず含めてください。 これらの列には、従業員 ID、アカウント名、電子メール アドレスなどが含まれることがあります。

    たとえば、ユーザーが列 `USERS` と `name` を含む `email` という名前のデータベース テーブル内に保持されている場合は、次のコマンドを入力します。

    ```powershell
    $db_query = "SELECT name,email from USERS"
    
    ```
6. このクエリを接続経由でデータベースに送信します。

    ```powershell
    $result = (new-object data.odbc.OdbcCommand($db_query,$db_conn)).ExecuteReader()
    $table = new-object System.Data.DataTable
    $table.Load($result)
    ```

    その結果は、クエリから取得されたユーザーを表す行の一覧です。
7. 結果を CSV ファイルに書き込みます。

    ```powershell
    $out_filename = ".\users.csv"
    $table.Rows | Export-Csv -Path $out_filename -NoTypeInformation -Encoding UTF8
    ```
8. このシステムに Microsoft Graph PowerShell コマンドレットがインストールされていないか、または Microsoft Entra ID への接続がない場合は、ユーザーのリストを含む CSV ファイルを [Microsoft Graph PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph)がインストールされているシステムに転送します。

#### SAP Cloud Identity Services から既存のユーザーを収集する

このセクションは、ユーザー プロビジョニングの基となるサービスとして SAP Cloud Identity Services を使う SAP アプリケーションに適用されます。

1. 試用の場合は、SAP Cloud Identity Services 管理コンソール、`https://<tenantID>.accounts.ondemand.com/admin`、または `https://<tenantID>.trial-accounts.ondemand.com/admin` にサインインします。
2. **[ユーザーと認可] &gt; [ユーザーのエクスポート]** に移動します。
3. Microsoft Entraユーザーを SAP のユーザーと照合するために必要なすべての属性を選択します。 これには、SAP システムで使っている可能性のある `SCIM ID`、`userName`、`emails`、その他の属性が含まれます。
4. **[エクスポート]** を選択し、ブラウザーが CSV ファイルをダウンロードするまで待ちます。
5. このシステムに Microsoft Graph PowerShell コマンドレットがインストールされていないか、または Microsoft Entra ID への接続がない場合は、ユーザーのリストを含む CSV ファイルを [Microsoft Graph PowerShell コマンドレット](https://www.powershellgallery.com/packages/Microsoft.Graph)がインストールされているシステムに転送します。

### Microsoft Entra ID にアプリケーションのユーザーに一致するユーザーが存在することを確認する

これで、アプリケーションから取得されたすべてのユーザーのリストが用意できたので、アプリケーションのデータ ストアのユーザーを Microsoft Entra ID 内のユーザーと照合します。

#### Microsoft Entra ID でユーザーの ID を取得する

このセクションでは、[Microsoft Graph PowerShell](https://www.powershellgallery.com/packages/Microsoft.Graph) コマンドレットを使用して Microsoft Entra ID を操作する方法を示します。

このシナリオのために組織でこれらのコマンドレットを初めて使用する場合は、テナントで Microsoft Graph PowerShell を使用できるように、グローバル管理者ロールである必要があります。 以降の操作では、次のような低い特権のロールを使用できます。

- ユーザー管理者、新しいユーザーの作成が予測される場合。
- アプリケーション管理者または [ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)、アプリケーション ロールの割り当ての管理だけを行う場合。

1. PowerShell を開きます。
2. [Microsoft Graph PowerShell モジュール](https://www.powershellgallery.com/packages/Microsoft.Graph)がまだインストールされていない場合は、次のコマンドを使用して `Microsoft.Graph.Users` モジュールなどをインストールします。

    ```powershell
    Install-Module Microsoft.Graph
    ```

    これらのモジュールが既にインストールされている場合は、最新バージョンを使用していることを確認します。

    ```powershell
    Update-Module microsoft.graph.users,microsoft.graph.identity.governance,microsoft.graph.applications
    ```
3. Microsoft Entra ID に接続します。

    ```powershell
    $msg = Connect-MgGraph -ContextScope Process -Scopes "User.ReadWrite.All,Application.ReadWrite.All,AppRoleAssignment.ReadWrite.All,EntitlementManagement.ReadWrite.All"
    ```
4. このコマンドを初めて使用する場合は、Microsoft Graph コマンド ライン ツールにこれらのアクセス許可を付与することを許可する必要があります。
5. アプリケーションのデータ ストアから取得したユーザーの一覧を、PowerShell セッションに読み込みます。 ユーザーの一覧の形式が CSV ファイルであった場合は、PowerShell コマンドレット `Import-Csv` を使用し、引数として前のセクションのファイルの名前を指定できます。

    たとえば、SAP Cloud Identity Services から取得したファイル名が *Users-exported-from-sap.csv* で、現在のディレクトリにある場合は、次のコマンドを入力します。

    ```powershell
    $filename = ".\Users-exported-from-sap.csv"
    $dbusers = Import-Csv -Path $filename -Encoding UTF8
    ```

    別の例として、データベースまたはディレクトリを使用している場合、ファイル名が *users.csv* で、現在のディレクトリにある場合は、次のコマンドを入力します:

    ```powershell
    $filename = ".\users.csv"
    $dbusers = Import-Csv -Path $filename -Encoding UTF8
    ```
6. Microsoft Entra ID 内のユーザーの属性に一致する *users.csv* ファイルの列を選択します。

    SAP Cloud Identity Services を使用している場合、既定のマッピングは SAP SCIM 属性 `userName` と Microsoft Entra ID 属性 `userPrincipalName` です。

    ```powershell
    $db_match_column_name = "userName"
    $azuread_match_attr_name = "userPrincipalName"
    ```

    別の例として、データベースやディレクトリを使用している場合、`EMail` という列の値が Microsoft Entra の属性 `userPrincipalName` と同じ値であるデータベース内のユーザーがいる場合があります:

    ```powershell
    $db_match_column_name = "EMail"
    $azuread_match_attr_name = "userPrincipalName"
    ```
7. Microsoft Entra ID でこれらのユーザーの ID を取得します。

    次の PowerShell スクリプトでは、前に指定された `$dbusers`、`$db_match_column_name`、`$azuread_match_attr_name` の各値を使用します。 これは、Microsoft Entra ID にクエリを実行して、ソース ファイル内の各レコードに一致する値の属性を持つユーザーを見つけます。 ソース SAP Cloud Identity Services、データベース、またはディレクトリから取得したファイルに多数のユーザーが存在する場合、このスクリプトが完了するまでに数分かかる場合があります。 この値を持つ属性が Microsoft Entra ID に存在せず、`contains` などのフィルター式を使用する必要がある場合は、このスクリプトと後の手順 11 のスクリプトを、別のフィルター式を使用するようにカスタマイズする必要があります。

    ```powershell
    $dbu_not_queried_list = @()
    $dbu_not_matched_list = @()
    $dbu_match_ambiguous_list = @()
    $dbu_query_failed_list = @()
    $azuread_match_id_list = @()
    $azuread_not_enabled_list = @()
    $dbu_values = @()
    $dbu_duplicate_list = @()
    
    foreach ($dbu in $dbusers) { 
       if ($null -ne $dbu.$db_match_column_name -and $dbu.$db_match_column_name.Length -gt 0) { 
          $val = $dbu.$db_match_column_name
          $escval = $val -replace "'","''"
          if ($dbu_values -contains $escval) { $dbu_duplicate_list += $dbu; continue } else { $dbu_values += $escval }
          $filter = $azuread_match_attr_name + " eq '" + $escval + "'"
          try {
             $ul = @(Get-MgUser -Filter $filter -All -Property Id,accountEnabled -ErrorAction Stop)
             if ($ul.length -eq 0) { $dbu_not_matched_list += $dbu; } elseif ($ul.length -gt 1) {$dbu_match_ambiguous_list += $dbu } else {
                $id = $ul[0].id; 
                $azuread_match_id_list += $id;
                if ($ul[0].accountEnabled -eq $false) {$azuread_not_enabled_list += $id }
             } 
          } catch { $dbu_query_failed_list += $dbu } 
        } else { $dbu_not_queried_list += $dbu }
    }
    
    ```
8. 前のクエリの結果を表示します。 エラーまたは一致が見つからないために、SAP Cloud Identity Services、データベース、またはディレクトリのいずれかのユーザーが Microsoft Entra ID に配置できなかったかどうかを確認します。

    次の PowerShell スクリプトでは、見つからなかったレコードの数を表示します。

    ```powershell
    $dbu_not_queried_count = $dbu_not_queried_list.Count
    if ($dbu_not_queried_count -ne 0) {
      Write-Error "Unable to query for $dbu_not_queried_count records as rows lacked values for $db_match_column_name."
    }
    $dbu_duplicate_count = $dbu_duplicate_list.Count
    if ($dbu_duplicate_count -ne 0) {
      Write-Error "Unable to locate Microsoft Entra ID users for $dbu_duplicate_count rows as multiple rows have the same value"
    }
    $dbu_not_matched_count = $dbu_not_matched_list.Count
    if ($dbu_not_matched_count -ne 0) {
      Write-Error "Unable to locate $dbu_not_matched_count records in Microsoft Entra ID by querying for $db_match_column_name values in $azuread_match_attr_name."
    }
    $dbu_match_ambiguous_count = $dbu_match_ambiguous_list.Count
    if ($dbu_match_ambiguous_count -ne 0) {
      Write-Error "Unable to locate $dbu_match_ambiguous_count records in Microsoft Entra ID as attribute match ambiguous."
    }
    $dbu_query_failed_count = $dbu_query_failed_list.Count
    if ($dbu_query_failed_count -ne 0) {
      Write-Error "Unable to locate $dbu_query_failed_count records in Microsoft Entra ID as queries returned errors."
    }
    $azuread_not_enabled_count = $azuread_not_enabled_list.Count
    if ($azuread_not_enabled_count -ne 0) {
     Write-Error "$azuread_not_enabled_count users in Microsoft Entra ID are blocked from sign-in."
    }
    if ($dbu_not_queried_count -ne 0 -or $dbu_duplicate_count -ne 0 -or $dbu_not_matched_count -ne 0 -or $dbu_match_ambiguous_count -ne 0 -or $dbu_query_failed_count -ne 0 -or $azuread_not_enabled_count) {
     Write-Output "You will need to resolve those issues before access of all existing users can be reviewed."
    }
    $azuread_match_count = $azuread_match_id_list.Count
    Write-Output "Users corresponding to $azuread_match_count records were located in Microsoft Entra ID." 
    ```
9. このスクリプトは、完了時に、データ ソースのいずれかのレコードが Microsoft Entra ID に見つからなかった場合はエラーを示します。 アプリケーションのデータ ストアのユーザーのレコードのうち Microsoft Entra ID 内のユーザーとして見つからなかったものがある場合は、どのレコードが一致しなかったのかとその理由を調査する必要があります。

    たとえば、ユーザーのメールアドレスと userPrincipalName が Microsoft Entra ID で変更されたのに、アプリケーションのデータ ソースでそれに対応する `mail` プロパティが更新されていない可能性があります。 または、ユーザーは既に組織を離れているが、まだアプリケーションのデータ ソースに存在する可能性があります。 あるいは、アプリケーションのデータ ソースに、Microsoft Entra ID 内のどの特定のユーザーにも対応していないベンダーまたはスーパー管理者アカウントが存在する可能性もあります。
10. Microsoft Entra ID で見つけられなかったか、またはアクティブかつサインインできる状態でなかったユーザーが存在したが、そのアクセスをレビューしたり、その属性を SAP Cloud Identity Services、データベース、またはディレクトリで更新したい場合は、アプリケーションまたは照合ルールを更新するか、そのユーザー用の Microsoft Entra ユーザーを更新する必要があります。 どの変更を行うかの詳細については、「[Microsoft Entra ID のユーザーと一致しなかったアプリケーションのマッピングとユーザー アカウントを管理する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-application-unmatched-users)」を参照してください。

    Microsoft Entra ID でユーザーを作成するオプションを選択した場合、次のいずれかを使用してユーザーを一括で作成できます。

    - CSV ファイル (「[Microsoft Entra 管理センターでのユーザーの一括作成](https://learn.microsoft.com/ja-jp/entra/identity/users/users-bulk-add)」で説明されています)
    - [New-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/new-mguser?view=graph-powershell-1.0#examples&preserve-view=true) コマンドレット

    これらの新しいユーザーに、Microsoft Entra ID が後でアプリケーション内の既存のユーザーと一致するために必要な属性と、Microsoft Entra ID で必要な属性 (`userPrincipalName`、`mailNickname`、`displayName` を含む) が設定されていることを確認します。 `userPrincipalName` は、ディレクトリ内のすべてのユーザー間で一意である必要があります。

    たとえば、`EMail` という名前の列の値が Microsoft Entra のユーザー プリンシパル名として使用したい値であり、列 `Alias` の値に Microsoft Entra ID のメール ニックネームが含まれ、列 `Full name` の値にユーザーの表示名が含まれているようなユーザーが、データベースに存在する場合があります。

    ```powershell
    $db_display_name_column_name = "Full name"
    $db_user_principal_name_column_name = "Email"
    $db_mail_nickname_column_name = "Alias"
    ```

    そのような場合、このスクリプトを使用して、SAP Cloud Identity Services、データベース、またはディレクトリにある Microsoft Entra ID のユーザと一致しなかったユーザに対して Microsoft Entra ユーザを作成できます。 組織で必要な Microsoft Entra 属性をさらに追加するため、または `$azuread_match_attr_name` が `mailNickname` でも `userPrincipalName` でもない場合にその Microsoft Entra 属性を指定するために、このスクリプトの変更が必要になる場合があることに注意してください。

    ```powershell
    $dbu_missing_columns_list = @()
    $dbu_creation_failed_list = @()
    foreach ($dbu in $dbu_not_matched_list) {
       if (($null -ne $dbu.$db_display_name_column_name -and $dbu.$db_display_name_column_name.Length -gt 0) -and
           ($null -ne $dbu.$db_user_principal_name_column_name -and $dbu.$db_user_principal_name_column_name.Length -gt 0) -and
           ($null -ne $dbu.$db_mail_nickname_column_name -and $dbu.$db_mail_nickname_column_name.Length -gt 0)) {
          $params = @{
             accountEnabled = $false
             displayName = $dbu.$db_display_name_column_name
             mailNickname = $dbu.$db_mail_nickname_column_name
             userPrincipalName = $dbu.$db_user_principal_name_column_name
             passwordProfile = @{
               Password = -join (((48..90) + (96..122)) * 16 | Get-Random -Count 16 | % {[char]$_})
             }
          }
          try {
            New-MgUser -BodyParameter $params
          } catch { $dbu_creation_failed_list += $dbu; throw }
       } else {
          $dbu_missing_columns_list += $dbu
       }
    }
    ```
11. 不足しているすべてのユーザーを Microsoft Entra ID に追加したら、手順 7 のスクリプトをもう一度実行します。 次に、手順 8 のスクリプトを実行します。 エラーが報告されていないことを確認します。

    ```powershell
    $dbu_not_queried_list = @()
    $dbu_not_matched_list = @()
    $dbu_match_ambiguous_list = @()
    $dbu_query_failed_list = @()
    $azuread_match_id_list = @()
    $azuread_not_enabled_list = @()
    $dbu_values = @()
    $dbu_duplicate_list = @()
    
    foreach ($dbu in $dbusers) { 
       if ($null -ne $dbu.$db_match_column_name -and $dbu.$db_match_column_name.Length -gt 0) { 
          $val = $dbu.$db_match_column_name
          $escval = $val -replace "'","''"
          if ($dbu_values -contains $escval) { $dbu_duplicate_list += $dbu; continue } else { $dbu_values += $escval }
          $filter = $azuread_match_attr_name + " eq '" + $escval + "'"
          try {
             $ul = @(Get-MgUser -Filter $filter -All -Property Id,accountEnabled -ErrorAction Stop)
             if ($ul.length -eq 0) { $dbu_not_matched_list += $dbu; } elseif ($ul.length -gt 1) {$dbu_match_ambiguous_list += $dbu } else {
                $id = $ul[0].id; 
                $azuread_match_id_list += $id;
                if ($ul[0].accountEnabled -eq $false) {$azuread_not_enabled_list += $id }
             } 
          } catch { $dbu_query_failed_list += $dbu } 
        } else { $dbu_not_queried_list += $dbu }
    }
    
    $dbu_not_queried_count = $dbu_not_queried_list.Count
    if ($dbu_not_queried_count -ne 0) {
      Write-Error "Unable to query for $dbu_not_queried_count records as rows lacked values for $db_match_column_name."
    }
    $dbu_duplicate_count = $dbu_duplicate_list.Count
    if ($dbu_duplicate_count -ne 0) {
      Write-Error "Unable to locate Microsoft Entra ID users for $dbu_duplicate_count rows as multiple rows have the same value"
    }
    $dbu_not_matched_count = $dbu_not_matched_list.Count
    if ($dbu_not_matched_count -ne 0) {
      Write-Error "Unable to locate $dbu_not_matched_count records in Microsoft Entra ID by querying for $db_match_column_name values in $azuread_match_attr_name."
    }
    $dbu_match_ambiguous_count = $dbu_match_ambiguous_list.Count
    if ($dbu_match_ambiguous_count -ne 0) {
      Write-Error "Unable to locate $dbu_match_ambiguous_count records in Microsoft Entra ID as attribute match ambiguous."
    }
    $dbu_query_failed_count = $dbu_query_failed_list.Count
    if ($dbu_query_failed_count -ne 0) {
      Write-Error "Unable to locate $dbu_query_failed_count records in Microsoft Entra ID as queries returned errors."
    }
    $azuread_not_enabled_count = $azuread_not_enabled_list.Count
    if ($azuread_not_enabled_count -ne 0) {
     Write-Warning "$azuread_not_enabled_count users in Microsoft Entra ID are blocked from sign-in."
    }
    if ($dbu_not_queried_count -ne 0 -or $dbu_duplicate_count -ne 0 -or $dbu_not_matched_count -ne 0 -or $dbu_match_ambiguous_count -ne 0 -or $dbu_query_failed_count -ne 0 -or $azuread_not_enabled_count -ne 0) {
     Write-Output "You will need to resolve those issues before access of all existing users can be reviewed."
    }
    $azuread_match_count = $azuread_match_id_list.Count
    Write-Output "Users corresponding to $azuread_match_count records were located in Microsoft Entra ID." 
    ```

### アプリケーションを登録する

アプリケーションが既に Microsoft Entra ID に登録されている場合は、次の手順に進みます。

お使いのアカウントには、Microsoft Entra ID でアプリケーションを管理するためのアクセス許可が必要です。 次の Microsoft Entra ロールには、いずれも必要なアクセス許可が含まれています。

- [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
- [アプリケーション開発者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)
- [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)

1. アプリケーションとサービス プリンシパルを作成します。

    たとえば、エンタープライズ アプリケーションの名前が `CORPDB1` である場合は、次のコマンドを入力します。

    ```powershell
    $azuread_app_name = "CORPDB1"
    $azuread_app = New-MgApplication -DisplayName $azuread_app_name
    $azuread_sp = New-MgServicePrincipal -DisplayName $azuread_app_name -AppId $azuread_app.AppId
    ```
2. アプリケーションにロールを追加し、割り当てを確認できるように、Microsoft Entra ID と統合済みであることを示すタグをアプリケーションに付けます。 たとえば、ロール名が `General` である場合は、次の PowerShell コマンドでその値を指定します。

    ```powershell
    $ar0 = New-Object Microsoft.Graph.PowerShell.Models.MicrosoftGraphAppRole
    $ar0.AllowedMemberTypes += "User"
    $ar0.Description = "General role"
    $ar0.DisplayName = "General"
    $ar0.id = New-Guid
    $ar0.IsEnabled = $true
    $ar0.Value = "General"
    $ara = @()
    $ara += $ar0
    
    $azuread_app_tags = @()
    $azuread_app_tags += "WindowsAzureActiveDirectoryIntegratedApp"
    
    $azuread_app_update = Update-MgApplication -ApplicationId $azuread_app.Id -AppRoles $ara -Tags $azuread_app_tags
    ```

### アプリケーションにまだ割り当てられていないユーザーを確認する

前の手順では、アプリケーションのデータ ストア内のすべてのユーザーが Microsoft Entra ID のユーザーとして存在することを確認しました。 ただし、そのすべてが現在、Microsoft Entra ID でアプリケーションのロールに割り当てられているとは限りません。 そこで、次のステップでは、アプリケーション ロールに割り当てられていないユーザーがいないかを確認します。

1. アプリケーションのサービス プリンシパルのサービス プリンシパル ID を検索します。

    たとえば、エンタープライズ アプリケーションの名前が `CORPDB1` である場合は、次のコマンドを入力します。

    ```powershell
    $azuread_app_name = "CORPDB1"
    $azuread_sp_filter = "displayName eq '" + ($azuread_app_name -replace "'","''") + "'"
    $azuread_sp = Get-MgServicePrincipal -Filter $azuread_sp_filter -All
    ```
2. Microsoft Entra ID で現在アプリケーションに割り当てられているユーザーを取得します。

    これは前のコマンドで設定した `$azuread_sp` 変数に基づいています。

    ```powershell
    $azuread_existing_assignments = @(Get-MgServicePrincipalAppRoleAssignedTo -ServicePrincipalId $azuread_sp.Id -All)
    ```
3. 前のセクションのユーザー ID の一覧を、現在アプリケーションに割り当てられているユーザーと比較します。

    ```powershell
    $azuread_not_in_role_list = @()
    foreach ($id in $azuread_match_id_list) {
       $found = $false
       foreach ($existing in $azuread_existing_assignments) {
          if ($existing.principalId -eq $id) {
             $found = $true; break;
          }
       }
       if ($found -eq $false) { $azuread_not_in_role_list += $id }
    }
    $azuread_not_in_role_count = $azuread_not_in_role_list.Count
    Write-Output "$azuread_not_in_role_count users in the application's data store are not assigned to the application roles."
    ```

    アプリケーション ロールに割り当てられていないユーザーの数が 0 で、すべてのユーザーがアプリケーション ロールに割り当てられていることを示している場合は、アクセス レビューを実行する前にそれ以上の変更を行う必要はありません。

    ただし、1 人以上のユーザーが現在アプリケーション ロールに割り当てられていない場合は、手順を続行して、それらをいずれかのアプリケーションのロールに追加する必要があります。
4. 残りのユーザーを割り当てるアプリケーション ロールを選択します。

    1 つのアプリケーションに複数のロールがある可能性があります。 次のコマンドを使用して、使用可能なロールを一覧表示します。

    ```powershell
    $azuread_sp.AppRoles | where-object {$_.AllowedMemberTypes -contains "User"} | ft DisplayName,Id
    ```

    一覧から適切なロールを選択し、そのロール ID を取得します。 たとえば、ロール名が `General` である場合は、次の PowerShell コマンドでその値を指定します。

    ```powershell
    $azuread_app_role_name = "General"
    $azuread_app_role_id = ($azuread_sp.AppRoles | where-object {$_.AllowedMemberTypes -contains "User" -and $_.DisplayName -eq $azuread_app_role_name}).Id
    if ($null -eq $azuread_app_role_id) { write-error "role $azuread_app_role_name not located in application manifest"}
    ```

### Microsoft Entra ID でアプリ ロールの割り当てを作成する

Microsoft Entra ID でアプリケーション内のユーザーを Microsoft Entra ID 内のユーザーと照合するには、Microsoft Entra ID でアプリケーション ロールの割り当てを作成する必要があります。

ユーザーのアプリケーション ロールの割り当てが Microsoft Entra ID に作成されており、アプリケーションがプロビジョニングをサポートしていない場合は、次のようになります

- ユーザーは、Microsoft Entra ID の外部で更新されない限り、または Microsoft Entra ID 内の割り当てが削除されるまで無期限にアプリケーション内に残ります。
- そのアプリケーションのロール割り当ての次回のレビューでは、そのユーザーがレビューに含まれます。
- アクセス レビューでユーザーが拒否された場合、そのアプリケーション ロールの割り当ては削除されます。

1. 現在ロールの割り当てがないユーザーに対してアプリケーション ロールの割り当てを作成します。

    ```powershell
    foreach ($u in $azuread_not_in_role_list) {
       $res = New-MgServicePrincipalAppRoleAssignedTo -ServicePrincipalId $azuread_sp.Id -AppRoleId $azuread_app_role_id -PrincipalId $u -ResourceId $azuread_sp.Id 
    }
    ```
2. 変更が Microsoft Entra ID 内で伝達されるまで 1 分待ちます。
3. Microsoft Entra ID にクエリを実行して、ロールの割り当ての更新されたリストを取得します。

    ```powershell
    $azuread_existing_assignments = @(Get-MgServicePrincipalAppRoleAssignedTo -ServicePrincipalId $azuread_sp.Id -All)
    ```
4. 前のセクションのユーザー ID の一覧を、現在アプリケーションに割り当てられているユーザーと比較します。

    ```powershell
    $azuread_still_not_in_role_list = @()
    foreach ($id in $azuread_match_id_list) {
       $found = $false
       foreach ($existing in $azuread_existing_assignments) {
          if ($existing.principalId -eq $id) {
             $found = $true; break;
          }
       }
       if ($found -eq $false) { $azuread_still_not_in_role_list += $id }
    }
    $azuread_still_not_in_role_count = $azuread_still_not_in_role_list.Count
    if ($azuread_still_not_in_role_count -gt 0) {
       Write-Output "$azuread_still_not_in_role_count users in the application's data store are not assigned to the application roles."
    }
    ```

    アプリケーション ロールに割り当てられているユーザーがいない場合は、Microsoft Entra 監査ログで前のステップのエラーを確認します。

### 適切なレビュー担当者を選択する

各アクセス レビューを作成するとき、管理者は 1 人以上のレビュー担当者を選ぶことができます。 レビュー担当者は、レビューを実行し、リソースに継続的にアクセスするユーザーを選択または削除することができます。

通常は、リソースの所有者がレビューの実行を担当します。 パターン B で統合されたアプリケーションのアクセス レビューの一環として、グループのレビューを作成する場合は、グループ所有者をレビュー担当者として選択できます。 Microsoft Entra ID のアプリケーションには所有者がいるとは限らないため、アプリケーションの所有者をレビュー担当者として選択することはできません。 代わりに、レビューを作成するときに、アプリケーション所有者の名前をレビュー担当者として指定できます。

グループまたはアプリケーションのレビューを作成するときに、[複数ステージのレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review#create-a-multi-stage-access-review)の作成を選ぶこともできます。 たとえば、割り当てられた各ユーザーのマネージャーがレビューの最初のステージを実行し、リソース所有者が 2 番目のステージを実行するようにできます。 こうすることで、リソース所有者は、マネージャーによって既に承認されているユーザーに集中できます。

レビューを作成する前に、テナントに十分な Microsoft Entra ID P2 または Microsoft Entra ID ガバナンス SKU シートがあることを確認します。 また、すべてのレビュー担当者がメール アドレスを持つアクティブなユーザーであることを確認します。 アクセス レビューが始まったら、各自が Microsoft Entra ID からのメールをレビューします。 レビュー担当者がメールボックスを持っていない場合、レビュー開始時のメールまたはメール リマインダーを受け取りません。 また、Microsoft Entra ID へのサインインができないようにブロックされている場合は、レビューを実行できません。

### アプリケーション ロールの割り当てのレビューを作成する

ユーザーにアプリケーション ロールを割り当て、レビュー担当者を指定したら、Microsoft Entra ID を構成して[レビューを開始](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-application-preparation#create-the-reviews)できます。

[グループまたはアプリケーションのアクセス レビューの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)に関するガイドの指示に従って、アプリケーションのロール割り当てのレビューを作成します。 完了時に結果を適用するようにレビューを構成します。

### レビューの完了時に更新される割り当てを取得する

1. レビューが完了すると、アプリケーション ロールが割り当てられたユーザーの更新された一覧を取得できます。

    ```powershell
    $res = (Get-MgServicePrincipalAppRoleAssignedTo -ServicePrincipalId $azuread_sp.Id -All)
    ```
2. 列 `PrincipalDisplayName` と `PrincipalId` には、アプリケーション ロールの割り当てを保持している各ユーザーの表示名と Microsoft Entra ユーザー ID が含まれています。

### チケット発行のためにエンタイトルメント管理と ServiceNow の統合を構成する (省略可能)

ServiceNow をお持ちの場合は、必要に応じて、Logic Apps による [エンタイトルメント管理統合](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-ticketed-provisioning) を使用して、ServiceNow チケットの自動作成を構成できます。 このシナリオでは、エンタイトルメント管理は、アクセス パッケージ割り当てを受けたユーザーの手動プロビジョニング用に ServiceNow チケットを自動的に作成できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/identity-governance-applications-prepare"} -->
## 環境内のアプリケーションのアクセスを制御する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-prepare
- Service: entra-id-governance
- Article date: 2024-11-21
- Summary: Microsoft Entra ID Governance を使うと、セキュリティや従業員の生産性に対する組織のニーズと、適切なプロセスや可視性とのバランスを取ることができます。 これらの機能は、既存のビジネス クリティカルなサード パーティのオンプレミスおよびクラウドベースのアプリケーションに使用できます。

Microsoft Entra ID Governance を使うと、セキュリティや従業員の生産性に対する組織のニーズと、適切なプロセスや可視性とのバランスを取ることができます。 その機能により、ユーザーとエージェント ID は、適切なタイミングで組織内の適切なリソースに適切なアクセス権を持つことができます。

コンプライアンス要件またはリスク管理計画がある組織には、機密性の高いアプリケーションまたはビジネス クリティカルなアプリケーションがあります。 アプリケーションの機密性は、その目的やそれに含まれるデータ (組織の顧客の財務情報や個人情報など) に基づいている場合があります。 これらのアプリケーションでは、通常、組織内のすべてのユーザー ID とエージェント ID のサブセットのみがアクセスを許可され、アクセスは文書化されたビジネス要件に基づいてのみ許可される必要があります。

アクセスを管理するための組織のコントロールの一環として、Microsoft Entra の機能を使って、次のことを実行できます。

- 適切なアクセス権を設定する
- アプリケーションにユーザーをプロビジョニングする
- アクセス チェックを適用する
- コンプライアンスとリスク管理の目標を満たすためにこれらのコントロールがどのように使用されているかを示すレポートを生成する

アプリケーション アクセス ガバナンス シナリオに加えて、[他の組織のユーザーの確認と削除](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-external-users)や、[条件付きアクセス ポリシーから除外されたユーザーの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/conditional-access-exclusion)などのその他のシナリオでも、Microsoft Entra ID ガバナンス機能とその他の Microsoft Entra 機能を使用できます。 組織に Microsoft Entra ID または Azure の複数の管理者がいて、B2B またはセルフサービス グループ管理を使用している場合は、これらのシナリオ用の[アクセス レビューのデプロイを計画](https://learn.microsoft.com/ja-jp/entra/id-governance/deploy-access-reviews)する必要があります。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID Governance または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)」をご覧ください。

### アプリケーションへのアクセス ガバナンスの概要

Microsoft Entra ID ガバナンスは、OpenID Connect、SAML、SCIM、SQL、LDAP などの[標準](https://learn.microsoft.com/ja-jp/entra/architecture/auth-sync-overview)を使用して、多くのアプリケーションと統合できます。 これらの標準を通して、Microsoft Entra ID を多くの一般的な SaaS アプリケーション、オンプレミス アプリケーション、および組織が開発したアプリケーションと共に使用できます。

以下のセクションで説明するように、Microsoft Entra 環境の準備が完了したら、3 ステップの計画でアプリケーションを Microsoft Entra ID に接続する方法をカバーし、そのアプリケーションで ID ガバナンス機能を使用できるようにします。

1. [アプリケーションへのアクセスを管理するための組織のポリシーを定義する](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-define)
2. [アプリケーションを Microsoft Entra ID と統合して](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-integrate)、許可されているユーザーのみがアプリケーションにアクセスできるようにし、アプリケーションへのユーザーの既存のアクセスを確認して、確認されたすべてのユーザーのベースラインを設定します。 これにより、認証とユーザー プロビジョニングが可能になります
3. [シングル](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-deploy) サインオン (SSO) を制御し、そのアプリケーションのアクセス割り当てを自動化するためのポリシーをデプロイする

### ID ガバナンスのために Microsoft Entra ID と Microsoft Entra ID ガバナンスを構成する前の前提条件

Microsoft Entra ID ガバナンスからのアプリケーション アクセスを管理するプロセスを開始する前に、Microsoft Entra 環境が適切に構成されていることを確認する必要があります。

- **適切なテナント展開アーキテクチャを選択します。** ビジネス パートナーと従業員ユーザーにアプリケーションへのアクセスを提供する場合は、テナントを選択してアプリケーションを統合し、ID ガバナンス機能を展開します。テナントは、ビジネス パートナー シナリオのコラボレーションまたは分離の要件に合わせて構成されます。 詳細については、Microsoft Entraを使用した Microsoft Entra 外部 ID 展開アーキテクチャの に関するページを参照してください。
- **Microsoft Entra ID と Microsoft Online Services 環境が、アプリケーションを統合して適切にライセンス設定するための[コンプライアンス要件](https://learn.microsoft.com/ja-jp/entra/standards/standards-overview)に対する準備ができていることを確認します**。 コンプライアンスは、Microsoft、クラウド サービス プロバイダー (CSP)、および組織の間での共同責任です。 アプリケーションへのアクセスの管理に Microsoft Entra ID を使用するには、テナントに次のいずれかの[ライセンスの組み合わせ](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)が必要です。

    - **Microsoft Entra ID ガバナンス**とその前提条件、Microsoft Entra ID P1
    - **Microsoft Entra ID Governance Step Up for Microsoft Entra ID P2** とその前提条件、Microsoft Entra ID P2 または Enterprise Mobility + Security (EMS) E5 のいずれか
    - **Microsoft Entra スイート**

    テナントには、アプリケーションへのアクセスを要求したり、アプリケーションへのアクセスを承認またはレビューしたりできるユーザーを含め、管理されているメンバー (ゲスト以外) ユーザーの数と同数以上のライセンスが必要です。 これらのユーザーに適切なライセンスを使用すると、ユーザーごとに最大 1500 のアプリケーションへのアクセスを管理できます。 詳細については、ライセンス シナリオの例を参照してください。
- **アプリケーションへのゲストのアクセスを管理する場合は、Microsoft Entra テナントを MAU 課金のためのサブスクリプションにリンクします**。 このステップは、ゲストにアクセスを要求またはレビューさせる前に必要です。 詳細については、「[Microsoft Entra 外部 IDの課金モデル](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing)」を参照してください。
- **Microsoft Entra ID が、監査ログおよび必要に応じて他のログを Azure Monitor に既に送信していることを確認します。** Microsoft Entra では監査イベントが監査ログに保存されるのは最大 30 日間だけであるため、Azure Monitor は省略可能ですがアプリへのアクセスの管理に有用です。 監査データは、「[Microsoft Entra ID にレポート データが保存される期間は?](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-reports-data-retention)」で説明されている既定の保持期間よりも長く保持でき、Azure Monitor ブック、カスタム クエリ、監査データ履歴に関するレポートを使用できます。 Microsoft Entra 管理センターの **[Microsoft Entra ID]** で **[Workbooks]** をクリックすることで、Microsoft Entra 構成をチェックしてこれが Azure Monitor を使用しているかどうかを確認できます。 この統合が構成されておらず、Azure サブスクリプションが手元にあり、自分が `Global Administrator` または `Security Administrator` ロールに含まれている場合は、[Azure Monitor を使用するように Microsoft Entra ID を構成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logs-and-reporting)できます。
- **オブジェクトの保持方法を選択します。** 過去 1 年間にアプリケーションにアクセスしたユーザーやエージェント ID (その後 Microsoft Entra から削除されたユーザーを含む) を一覧表示するレポートなど、過去の Microsoft Entra オブジェクトを報告できるようにする必要がある場合は、保持とレポートのために、Microsoft Entra から別のリポジトリにオブジェクトをアーカイブすることを計画する必要があります。 詳細については、「[Microsoft Entra ID のデータを使用して Azure Data Explorer (ADX) でレポートをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/id-governance/custom-entitlement-report-with-adx-and-entra-id)」を参照してください。
- **許可されているユーザーのみが Microsoft Entra テナントの高い特権を持つ管理者ロールに含まれるようにします。***グローバル管理者*、*ID ガバナンス管理者*、*ユーザー管理者*、*アプリケーション管理者*、*クラウド アプリケーション管理者*、*特権ロール管理者*に含まれる管理者は、ユーザーとそのアプリケーション ロールの割り当てを変更できます。 これらのロールのメンバーシップが最近レビューされていない場合は、*これらのディレクトリ ロールのアクセス レビュー*が確実に開始されるようにするために、"グローバル管理者" または "特権ロール管理者" のユーザーが必要です。 また、Azure Monitor、Logic Apps、Microsoft Entra 構成の操作に必要なその他のリソースを保持するサブスクリプションの Azure ロールのユーザーが確認済みであることを確かめる必要もあります。
- **テナントが適切に分離されていることを確認します。** 組織がオンプレミスで Active Directory を使用していて、それらの AD ドメインが Microsoft Entra ID に接続されている場合は、クラウドでホストされるサービスに対する高い特権を使用する管理操作が、オンプレミス アカウントから分離されていることを確認する必要があります。 [オンプレミスの侵害から Microsoft 365 のクラウド環境を保護するようにシステムを構成している](https://learn.microsoft.com/ja-jp/entra/architecture/protect-m365-from-on-premises-attacks)ことを確認します。

Microsoft Entra 環境の準備ができていることを確認したら、アプリケーションの[ガバナンス ポリシーの定義](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-define)に進みます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/identity-governance-automation"} -->
## Azure Automation を使用して Microsoft Entra ID ガバナンスのタスクを自動化する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-automation
- Service: entra-id-governance
- Article date: 2024-11-25
- Summary: Microsoft Entra のエンタイトルメント管理などの機能を操作するために、Azure Automation で PowerShell スクリプトを記述する方法について説明します。

[Azure Automation](https://learn.microsoft.com/ja-jp/azure/automation/overview) は、一般的または反復的なシステム管理とプロセスを自動化できる Azure のクラウド サービスです。 Microsoft Graph は、ディレクトリ内のユーザー、グループ、アクセス パッケージ、アクセス レビューなどのリソースを管理する Microsoft Entra 機能のための Microsoft 統合 API エンドポイントです。

[Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/get-started) を使用して、PowerShell のコマンド ラインで Microsoft Entra ID を大規模に管理できます。 [Azure Automation で PowerShell ベースの Runbook](https://learn.microsoft.com/ja-jp/azure/automation/automation-intro) から Microsoft Graph PowerShell のコマンドレットを取り込み、簡単なスクリプトで Microsoft Entra のタスクを自動化することもできます。

Azure Automation と PowerShell Graph SDK では証明書ベースの認証とアプリケーションのアクセス許可がサポートされているため、ユーザー コンテキストを必要とせずに Azure Automation の Runbook を Microsoft Entra ID に対して認証できます。

この記事では、Microsoft Graph PowerShell でエンタイトルメント管理のクエリを実行する簡単な Runbook を作成することで、Microsoft Entra ID に対して Azure Automation の使用を開始する方法を示します。

### Azure Automation アカウントを作成する

Azure Automation は、[Runbook を実行する](https://learn.microsoft.com/ja-jp/azure/automation/automation-runbook-execution)クラウドでホストされる環境を提供します。 これらの Runbook は、スケジュールに基づいて、 Webhook によってトリガーされて、または Logic Apps によって自動的に開始できます。

この Azure Automation を使用するには、Azure サブスクリプションが必要です。

**前提条件ロール**: Azure サブスクリプションまたはリソース グループ所有者

1. [Azure portal](https://portal.azure.com) にサインインします。 サブスクリプション、または Azure Automation アカウントが配置されるリソース グループにアクセスができることを確認します。
2. サブスクリプションまたはリソース グループを選択し、**[作成]** を選択します。 **「Automation」**と入力し、Microsoft から Azure サービスの **[自動化]** を選択し、**[作成]** を選択します。
3. Azure Automation アカウントを作成後、**[アクセス制御 (IAM)]** を選択します。 次に、**[このリソースへのアクセス]** で**[表示]** を選択します。 これらのユーザーとサービス プリンシパルは、後から Azure Automation アカウント内で作成されるスクリプトを介して Microsoft サービスと対話できます。
4. そこに表示されているユーザーとサービス プリンシパルを確認し、これらが承認されていることを確認します。 未承認のユーザーを削除します。

### コンピューターで自己署名のキーの組および証明書を作成する

個人の資格情報がなくても動作が可能となるように、作成した Azure Automation アカウントの Microsoft Entra ID への認証を証明書を使って行う必要があります。

Microsoft Entra ID へのサービスを認証するキーの組と、認証局から発行を受けた証明書が既にある場合は、ここを飛ばして次のセクションにスキップしてください。

自己署名証明書を生成する

1. [自己署名証明書を作成する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-create-self-signed-certificate)のオプション 2 の手順に従って、そのプライベート キーを使用して証明書を作成およびエクスポートします。
2. 証明書のサムプリントを表示します。

    ```powershell
     $cert | ft Thumbprint
    ```
3. ファイルをエクスポートした後、ローカル ユーザー証明書ストアから証明書とキーの組を削除できます。 証明書と秘密キーが Azure Automation および Microsoft Entra サービスにアップロードされたら、以降の手順で `.pfx` および `.crt` のファイルも削除します。

### Azure Automation へキーの組をアップロードする

Azure Automation の Runbook は、`.pfx` ファイルから秘密キーを取得し、Microsoft Graph に対する認証のために使用します。

1. Azure Automation アカウントの Azure portal で、**[証明書]**、**[証明書の追加]** の順に選択します。
2. 先ほど作成した `.pfx` ファイルをアップロードし、ファイルの作成時に指定したパスワードを入力します。
3. 秘密キーがアップロードされた後、証明書の有効期限を記録します。
4. これで、ローカル コンピューターからファイル `.pfx` を削除できます。 ただし、後の手順でこのファイルが必要なので、まだ `.crt` ファイルを削除しないでください。

### Microsoft Graph のモジュールを自分の Azure Automation に追加する

既定では、Azure Automation には事前に読み込まれた Microsoft Graph 用の PowerShell モジュールはありません。 まずは **Microsoft.Graph.Authentication** を、その後に追加のモジュールをギャラリーから Automation アカウントへ追加する必要があります。

1. Azure Automation アカウントの Azure portal で、**[モジュール]**、**[ギャラリーの参照]** の順に選択します。
2. [検索] バーで、 **「Microsoft.Graph.Authentication」**とタイプします。 対象のモジュールを選択して、**[インポート]**、**[OK]** の順に選択し、Microsoft Entra ID でモジュールのインポートを開始します。 [OK] を選択した後、モジュールのインポートに数分かかる場合があります。 Microsoft.Graph.Authentication モジュールのインポートが完了するまで、Microsoft Graph をこれ以上追加しないようにしてください。これらの他のモジュールには Microsoft.Graph.Authentication が前提条件としての含まれています。
3. **[モジュール]** の一覧に戻り、**[更新]** を選択します。 **Microsoft.Graph.Authentication** の状態が **[使用可能]** に変更されると、次のモジュールをインポートできます。
4. エンタイトルメント管理などの Microsoft Entra ID ガバナンス機能にコマンドレットを使用している場合は、さらにモジュール **Microsoft.Graph.Identity.Governance** に対するインポート プロセスを繰り返します。
5. スクリプトで必要となる可能性がある他のモジュール (**Microsoft.Graph.Users** など) をインポートします。 たとえば、Microsoft Entra ID 保護を使用している場合は、**Microsoft.Graph.Identity.SignIns** モジュールをインポートするとよいでしょう。

### アプリの登録を作成してアクセス許可を割り当てる

次に、Microsoft Entra ID が認証用の Azure Automation Runbook の証明書を認識できるように、Microsoft Entra ID にアプリの登録を作成します。

1. [アプリケーション管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)にサインインします。
2. **Entra ID**&gt;**アプリ登録**に移動します。
3. **[新規登録]** を選択します。
4. アプリケーションの名前を入力し、**[登録]**を選択します。
    1. アプリケーションの登録が作成されたら、**アプリケーション (クライアント) ID** と**ディレクトリ (テナント) ID** をメモします。後でこれらの項目が必要になります。
5. **[証明書とシークレット]**&gt;**[証明書]**&gt;**[証明書のアップロード]**の順に選択します。
    1. 先に作成したファイル `.crt` をアップロードします。
6. **[API のアクセス許可]**&gt;**[アクセス許可の追加]** の順に選択します。
7. **[Microsoft Graph]**&gt;**[アプリケーションのアクセス許可]**を選択します。
    1. Azure Automation アカウントに必要なアクセス許可をそれぞれ選択してから、**[アクセス許可の追加]** を選択します。

        - Runbook がクエリまたは更新を 1 つのカタログ内でだけ実行している場合は、テナント全体のアプリケーション アクセス許可を割り当てる必要はありません。代わりに、サービス プリンシパルをカタログの**カタログ所有者**または**カタログ閲覧者**ロールに割り当てることができます。
        - Runbook がエンタイトルメント管理のクエリのみを実行している場合は、 **EntitlementManagement.Read.All** のアクセス許可を使用できます。
        - Runbook が (たとえば、複数のカタログにまたがる割り当てを作成するために) エンタイトルメント管理を変更している場合は、**EntitlementManagement.ReadWrite.All** のアクセス許可を使用します。
        - その他の API については、必要なアクセス許可が追加されていることを確認します。 たとえば、Microsoft Entra ID 保護の場合、**IdentityRiskyUser.Read.All** アクセス許可が必要な場合があります。

#### 管理者の同意を与える

前のセクションで作成したアプリケーションには、意図したとおりに動作する前に、少なくとも特権ロール管理者ロールを持つユーザーが承認する必要があるアクセス許可があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)以上としてサインインします。
2. **Entra ID**&gt;**アプリケーションの登録**&gt;**すべてのアプリケーション**に移動します。
3. 前のセクションで作成したアプリを選択します。
4. **[API のアクセス許可]** を選択し、必要なアクセス許可を確認します。
5. 必要に応じて、**[”テナント名" に管理者の同意を与えます]** を選択して、アプリケーションにこれらのアクセス許可を付与します。

### Azure Automation 変数を作成する

この手順では、Runbook が Microsoft Entra ID への認証方法を決定するために使用する 3 つの変数を Azure Automation アカウントに作成します。

1. Azure portal で、Azure Automation アカウントに戻ります。
2. **[変数]**、**[変数の追加]** の順に選択します。
3. **Thumbprint** という名前の変数を作成します。 変数の値として、前に生成された証明書のサムプリントを入力します。
4. **ClientId** という名前の変数を作成します。 変数の値として、 Microsoft Entra ID に登録されているアプリケーションのクライアント ID を入力します。
5. **TenantId** という名前の変数を作成します。 変数の値として、アプリケーションが登録されたディレクトリのテナント ID を入力します。

### Graph を使用できる Azure Automation PowerShell Runbook を作成する

この手順では、初期状態の Runbook を作成します。 この Runbook をトリガーして、以前に作成した証明書を使用して認証が成功した場合に確認できます。

1. **[Runbook]** と **[Runbook の作成]** を選択します。
2. Runbook の名前を入力し、作成する Runbook の種類として **[PowerShell]** を選択し、**[作成]** を選択します。
3. Runbook が作成されると、Runbook の PowerShell ソース コードを入力するためのテキスト編集ペインが表示されます。
4. テキスト エディターに次の PowerShell を入力します。

```powershell
Import-Module Microsoft.Graph.Authentication
$ClientId = Get-AutomationVariable -Name 'ClientId'
$TenantId = Get-AutomationVariable -Name 'TenantId'
$Thumbprint = Get-AutomationVariable -Name 'Thumbprint'
Connect-MgGraph -clientId $ClientId -tenantId $TenantId -certificatethumbprint $Thumbprint
```

1. **[テスト] ウィンドウ**、**[開始]** の順に選択します。 Runbook スクリプトの処理を Azure Automation が完了するまで数秒間待ちます。
2. Runbook の実行が成功した場合は、メッセージ **「Welcome to Microsoft Graph!」** が表示されます。

これで、Runbook が Microsoft Graph に対して認証可能なことを確認できました。次に、Microsoft Entra の機能を操作するためのコマンドレットを追加して、Runbook を拡張します。

### エンタイトルメント管理を使用するために Runbook を拡張する

Runbook のアプリ登録に **EntitlementManagement.Read.All** または **EntitlementManagement.ReadWrite.All** のアクセス許可がある場合は、エンタイトルメント管理 API を使用できます。

1. たとえば、Microsoft Entra エンタイトルメント管理のアクセス パッケージの一覧を取得するには、上記で作成した Runbook を更新して、テキストを次の PowerShell に置き換えます。

```powershell
Import-Module Microsoft.Graph.Authentication
$ClientId = Get-AutomationVariable -Name 'ClientId'
$TenantId = Get-AutomationVariable -Name 'TenantId'
$Thumbprint = Get-AutomationVariable -Name 'Thumbprint'
$auth = Connect-MgGraph -clientId $ClientId -tenantid $TenantId -certificatethumbprint $Thumbprint
Import-Module Microsoft.Graph.Identity.Governance
$ap = @(Get-MgEntitlementManagementAccessPackage -All -ErrorAction Stop)
if ($null -eq $ap -or $ap.Count -eq 0) {
   ConvertTo-Json @()
} else {
   $ap | Select-Object -Property Id,DisplayName | ConvertTo-Json -AsArray
}
```

1. **[テスト] ウィンドウ**、**[開始]** の順に選択します。 Runbook スクリプトの処理を Azure Automation が完了するまで数秒間待ちます。
2. 実行が成功した場合、ウェルカム メッセージではなく出力が JSON 配列になります。 JSON 配列には、クエリから返される各アクセス パッケージの ID と表示名が含まれます。

### Runbook にパラメーターを指定する (省略可能)

PowerShell スクリプトの上部に `Param` セクションを追加することで、Runbook に入力パラメーターを追加することもできます。 たとえば、

```powershell
Param
(
    [String] $AccessPackageAssignmentId
)
```

許可されるパラメーターの形式は、呼び出し元のサービスによって異なります。 Runbook が呼び出し元からパラメーターを受け取る場合は、Runbook に検証ロジックを追加して、指定されたパラメーター値が Runbook の開始方法に適していることを確認する必要があります。 たとえば、Runbook が [Webhook](https://learn.microsoft.com/ja-jp/azure/automation/automation-webhooks) によって開始される場合、Webhook 要求が正しい URL に対して行われている限り、Azure Automation は Webhook 要求の認証を何も実行しないため、要求を検証するための代替手段が必要となります。

[Runbook 入力パラメーターを構成](https://learn.microsoft.com/ja-jp/azure/automation/runbook-input-parameters)すると、Runbook をテストするときに、[テスト] ページで値を指定できます。 後で Runbook が発行されると、PowerShell、REST API、またはロジック アプリから Runbook を開始するときにパラメーターを指定できます。

### Logic Apps の Azure Automation アカウント内の出力を解析する (オプション)

Runbook が発行されると、Azure Automation でスケジュールを作成し、Runbook をそのスケジュールにリンクして自動的に実行できます。 Azure Automation からの Runbook のスケジュール設定は、PowerShell インターフェイスを持たない他の Azure サービスや Office 365 サービスとやり取りする必要がない Runbook に適しています。

Runbook の出力を別のサービスに送信する場合は、Logic Apps でも結果を解析できるため、[Azure Logic Apps](https://learn.microsoft.com/ja-jp/azure/logic-apps/logic-apps-overview) を使用して Azure Automation Runbook を開始することができます。

1. [Azure Logic Apps] で、**[繰り返し]** から始まる Logic Apps Designer で Logic App を作成します。
2. **[Azure Automation]** から **[ジョブの作成]** 操作を追加します。 Microsoft Entra ID への認証を行って、前に作成したサブスクリプション、リソース グループ、Automation アカウントを選択します。 **[ジョブの待機]** を選択します。
3. パラメーター **Runbook 名**を追加し、開始する Runbook の名前を入力します。 Runbook に入力パラメーターがある場合は、その値を指定できます。
4. **[新しいステップ]** を選択し、**[ジョブ出力の取得]** 操作を追加します。 前の手順と同様に、サブスクリプション、リソース グループ、Automation アカウントを選択し、前の手順の **[ジョブ ID]** の [動的] 値を選択します。
5. Runbook の完了時に返される[コンテンツ**を使用する **](https://learn.microsoft.com/ja-jp/azure/logic-apps/logic-apps-perform-data-operations#parse-json-action)JSON の解析**アクション**などのロジック アプリに、さらに操作を追加できます。 (サンプル ペイロードから **JSON の解析**スキーマを自動生成する場合は、null 値を返す可能性がある PowerShell スクリプトを必ず考慮してください。スキーマ内で、`"type": ​"string"` の一部に `"type": [​"string",​ "null"​]` への変更が必要となることがあります。)

Azure Automation では、一度に大量のデータを出力ストリームに書き込もうとすると、PowerShell Runbook の完了に失敗する可能性があります。 この問題を回避するには、通常、 不要なプロパティを除外する `Select-Object -Property` コマンドレットの使用など、ロジック アプリで必要な情報のみを Runbook に出力します。

### 証明書を最新の状態に保つためのプラン

認証のために上記の手順に従って自己署名証明書を作成した場合、証明書には期限が切れるまでの限られた有効期間しかないことに注意してください。 有効期限日より前に証明書を再生成し、その新しい証明書をアップロードする必要があります。

Azure portal で有効期限は、2 つの場所で確認できます。

- [Azure Automation] で、**[証明書]** の画面に証明書の有効期限が表示されます。
- Microsoft Entra ID のアプリの登録で、**[証明書とシークレット]** 画面に、Azure Automation アカウントに使用された証明書の有効期限が表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/identity-governance-organizational-roles"} -->
## 組織のロール モデルを使用してアクセスを管理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-organizational-roles
- Service: entra-id-governance
- Article date: 2024-12-10
- Summary: Microsoft Entra ID Governance を使用すると、アクセス パッケージを使用して組織のロールをモデル化できるため、既存のロールの定義をエンタイトルメント管理に移行できます。

ロールベースのアクセス制御 (RBAC) により、ユーザーと IT リソースを分類するためのフレームワークが提供されます。 このフレームワークにより、それらの間の関係と、その分類に応じた適切なアクセス権を明示できます。 たとえば、ユーザーの役職とプロジェクトの割り当てを指定する属性をユーザーに割り当てることで、ユーザーのジョブに必要なツールと、特定のプロジェクトの作業に必要なデータへのアクセスを、ユーザーに許可することができます。 ユーザーが別のジョブとプロジェクト割り当てを担当する場合は、ユーザーの役職とプロジェクトを指定する属性を変更すると、前の役職だけに必要だったリソースへのアクセスが自動的にブロックされます。

Microsoft Entra ID では、ロール モデルをいくつかの方法で使用して、ID ガバナンスを通じて大規模にアクセスを管理できます。

- アクセス パッケージを使用して、"営業担当者" のような組織内のロールを表すことができます。 組織のロールを表すアクセス パッケージには、複数のリソースにわたって営業担当者が一般的に必要とする可能性があるすべてのアクセス権が含まれます。
- アプリケーションでは[独自のロールを定義できます](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)。 たとえば、営業アプリケーションがあり、そのアプリケーションではマニフェストにアプリ ロール "salesperson" が含まれていた場合、[アプリ マニフェストのそのロールをアクセス パッケージに含める](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources)ことができます。 アプリケーションでは、ID に複数のアプリケーション固有のロールが同時に含まれる可能性があるシナリオでも、セキュリティ グループを使用できます。
- ロールを使用して[管理アクセスを委任](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate)できます。 営業に必要なすべてのアクセス パッケージのカタログがある場合、カタログ固有のロールを割り当てることによって、任意のユーザーをそのカタログの責任者に任命することができます。

この記事では、エンタイトルメント管理アクセス パッケージを使用して組織のロールをモデル化し、ロールの定義を Microsoft Entra ID に移行してアクセスを強制する方法について説明します。

### 組織のロール モデルの移行

次の表は、他の製品でよく知られている組織のロール定義の概念が、エンタイトルメント管理の機能にどのように対応しているかを示しています。

| 組織のロール モデリングの概念 | エンタイトルメント管理での表現 |
| --- | --- |
| 委任されたロールの管理 | [カタログ作成者への委任](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate-catalog) |
| 1 つ以上のアプリケーションにわたるアクセス許可のコレクション | [リソース ロールを使用してアクセス パッケージを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create) |
| ロールが提供するアクセス期間を制限する | [アクセス パッケージのポリシー ライフサイクル設定に有効期限を設定する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-lifecycle-policy) |
| ロールへの個別の割り当て | [アクセス パッケージへの直接割り当てを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-an-identity) |
| プロパティ (部署など) に基づくユーザーへのロールの割り当て | [アクセス パッケージへの自動割り当てを確立する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy) |
| ユーザーはロールを要求して承認を受けることができる | [アクセス パッケージを要求できるユーザーのポリシー設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy) |
| ロール メンバーのアクセスの再認定 | [アクセス パッケージ ポリシーで定期的なアクセス レビュー設定を設定する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-reviews-create) |
| ロール間の職務の分離 | [2 つ以上のアクセス パッケージを互換性なしとして定義する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-incompatible) |

たとえば、組織に次の表のような既存の組織のロール モデルがあるとします。

| ロール名 | ロールが与えるアクセス許可 | ロールへの自動割り当て | 要求に基づくロールの割り当て | 職務の分離チェック |
| --- | --- | --- | --- | --- |
| *店員* | **Sales** チームのメンバー | はい | いいえ | なし |
| *Sales Solution Manager* | *Salesperson* のアクセス許可、および Sales アプリケーションでの **Solution manager** アプリのロール | なし | salesperson は要求でき、その際にはマネージャーの承認が必要で、四半期ごとにレビューを受ける必要があります。 | 要求者を *Sales Account Manager* にすることはできない |
| *営業アカウントマネージャー* | *Salesperson* のアクセス許可、および Sales アプリケーションでの **Account manager** アプリのロール | なし | salesperson は要求でき、その際にはマネージャーの承認が必要で、四半期ごとにレビューを受ける必要があります。 | 要求を *Sales Solution Manager* にすることはできない |
| *販売サポート* | *Salesperson* と同じアクセス許可 | なし | 営業担当者以外の者が要求でき、マネージャーの承認と四半期ごとのレビューが必要です。 | 要求者は *Salesperson* になれない |

これは、Microsoft Entra ID Governance では、4 つのアクセス パッケージを含むアクセス パッケージ カタログとして表すことができます。

| アクセスパッケージ | リソース ロール | ポリシー | 互換性のないアクセス パッケージ |
| --- | --- | --- | --- |
| *店員* | **Sales** チームのメンバー | 自動割り当て |  |
| *Sales Solution Manager* | Sales アプリケーションでの**Solution manager** アプリのロール | 要請に基づく | *営業アカウントマネージャー* |
| *営業アカウントマネージャー* | Sales アプリケーションでの**Account manager** アプリのロール | リクエストベース | *Sales Solution Manager* |
| *販売サポート* | **Sales** チームのメンバー | リクエストベース | *店員* |

以降のセクションでは、Microsoft Entra ID および Microsoft Entra ID Governance 成果物を作成して、組織のロール モデルと同等のアクセスを実装する移行プロセスの概要を説明します。

#### アクセス許可が組織のロールで参照されているアプリを Microsoft Entra ID に接続する

組織のロールを使用して、Microsoft 以外の SaaS アプリ、オンプレミス アプリ、または独自のクラウド アプリへのアクセスを制御するアクセス許可を割り当てる場合は、アプリケーションを Microsoft Entra ID に接続する必要があります。

複数のロールを持ち、SCIM などの最新の標準をサポートするアプリケーションの場合、組織のロールを表すアクセス パッケージがアプリケーションのロールをロールに含めるアクセス許可として参照できるようにするには、[そのアプリケーションを Microsoft Entra ID と統合](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-integrate)し、アプリケーションのロールがアプリケーション マニフェストに一覧表示されるようにする必要があります。

アプリケーションに 1 つのロールしかない場合でも、[アプリケーションを Microsoft Entra ID と統合する](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-integrate)必要があります。 SCIM をサポートしていないアプリケーションの場合、Microsoft Entra ID では、アプリケーションの既存のディレクトリまたは SQL データベースにユーザーを書き込むか、AD ユーザーを AD グループに追加することができます。

#### アプリや組織のロールでのユーザースコープルールに使用する Microsoft Entra スキーマを入力する

ロールの定義に "これらの属性値を持つすべてのユーザーがロールに自動的に割り当てられる" または "これらの属性値を持つユーザーが要求を許可される" という形式のステートメントが含まれている場合は、それらの属性が Microsoft Entra ID に存在することを確認する必要があります。

[Microsoft Entra スキーマを拡張](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)し、オンプレミス AD、Microsoft Entra ID Connect 経由、または Workday や SuccessFactors などの人事システムからこれらの属性を設定できます。

#### 委任用のカタログを作成する

ロールの継続的なメンテナンスが委任されている場合は、委任先の組織の各部分の[カタログを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create)して、アクセス パッケージの管理を委任できます。

作成するカタログが複数ある場合は、PowerShell スクリプトを使用して[各カタログを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#create-a-catalog-with-powershell)できます。

アクセス パッケージの管理を委任する予定がない場合は、アクセス パッケージを 1 つのカタログに保持できます。

#### カタログにリソースを追加する

カタログを特定したら、組織のロールを表すアクセス パッケージに含まれる[アプリケーション、グループ、またはサイト](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#add-resources-to-a-catalog)をカタログに追加します。

リソースが多い場合は、PowerShell スクリプトを使用して[各リソースをカタログに追加](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#add-a-resource-to-a-catalog-with-powershell)できます。 詳細については、「[PowerShell を使用して 1 つのロールを持つアプリケーションのエンタイトルメント管理でアクセス パッケージを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create-app)」を参照してください。

#### 組織のロールの定義に対応するアクセス パッケージを作成する

各組織のロールの定義は、そのカタログ内の[アクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)で表すことができます。

PowerShell スクリプトを使用して、[カタログにアクセス パッケージを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#create-an-access-package-by-using-microsoft-powershell)できます。

アクセス パッケージを作成したら、カタログ内のリソースの 1 つ以上のロールをアクセス パッケージにリンクします。 これは、組織の役割の権限を表します。

さらに、 [直接割り当てのポリシーを、](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#none-administrator-direct-assignments-only)そのアクセス パッケージの一部として作成します。このポリシーを使用して、個々の組織のロールが既に割り当てられている ID を追跡できます。

#### 既存の個々の組織ロールの割り当てに対するアクセス パッケージの割り当てを作成する

一部の ID に既に組織のロール メンバーシップがあり、自動割り当てでは受け取らない場合は、対応するアクセス パッケージに対してそれらの ID の [直接割り当てを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-an-identity) する必要があります。

割り当てが必要な ID が多数ある場合は、PowerShell スクリプトを使用して [各ユーザーをアクセス パッケージに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#assign-a-user-to-an-access-package-with-powershell)。 これにより、ユーザーが直接割り当てポリシーにリンクされます。

#### 自動割り当て用にこれらのアクセス パッケージにポリシーを追加する

組織のロール定義に、それらの属性に基づいて自動的にアクセスを割り当てたり削除したりするための ID 属性に基づくルールが含まれている場合は、 [自動割り当てポリシー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)を使用してこれを表すことができます。 アクセス パッケージには、最大 1 つの自動割り当てポリシーを含めることができます。

それぞれがロールの定義を持つロールの定義が多数ある場合は、PowerShell スクリプトを使用して、各アクセス パッケージに [それぞれの自動割り当てポリシーを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy#create-an-access-package-assignment-policy-through-powershell)できます。

#### 職務の分離用にアクセス パッケージを互換性なしとして設定する

ID が既に別の組織の役割を担うのを妨げる職務の制約を分離している場合は、 [それらのアクセス パッケージの組み合わせを互換性のないものとしてマーク](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-incompatible)することで、ID がエンタイトルメント管理でアクセスを要求できないようにすることができます。

別のアクセス パッケージと互換性なしとしてマークされるアクセス パッケージごとに、PowerShell スクリプトを使用して[アクセス パッケージを互換性なしとして構成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-incompatible#configure-incompatible-access-packages-through-microsoft-powershell)できます。

#### リクエストを許可するための ID がアクセスするパッケージにポリシーを追加する

まだ組織のロールを持っていない ID がロールを要求して承認される場合は、ID がアクセス パッケージを要求できるようにエンタイトルメント管理を構成することもできます。 [アクセス パッケージに追加のポリシーを追加](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-request-policy#choose-between-one-or-multiple-policies)し、各ポリシーで、要求できる ID と承認する必要があるユーザーを指定できます。

#### アクセス パッケージの割り当てポリシーでアクセス レビューを構成する

組織のロールでメンバーシップの定期的なレビューを必要な場合は、要求ベースの直接割り当てポリシーで[定期的なアクセス レビューを構成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-reviews-create)できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/identity-governance-overview"} -->
## Microsoft Entra ID ガバナンス - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview
- Service: entra-id-governance
- Article date: 2026-05-08
- Summary: Microsoft Entra ID ガバナンスを使用すると、セキュリティとエンド ユーザーの生産性に対する組織のニーズと、適切なプロセスと可視性のバランスを取ります。

[Microsoft Entra ID ガバナンス](https://www.microsoft.com/security/business/identity-access/microsoft-entra-id-governance) は、組織が生産性を向上させ、セキュリティを強化し、コンプライアンスと規制の要件をより簡単に満たすことを可能にする ID ガバナンス ソリューションです。 Microsoft Entra ID ガバナンスは、AI 主導の分析情報を活用して、組織が適切なユーザーが適切なリソースに適切なアクセス権を持っていることを自動的に確保するのに役立ちます。 これは、ID とアクセス プロセスの自動化、ビジネス グループへの委任、可視性の向上によって実現されます。 Microsoft Entra ID ガバナンスおよび関連するMicrosoft製品の機能を使用すると、重要な資産へのアクセスを保護、監視、監査することで、ID とアクセスのリスクを軽減できます。

具体的には、Microsoft Entra ID ガバナンスは、オンプレミスとクラウドの両方のサービスとアプリケーション間のアクセスに関して、次の 4 つの重要な質問に対処するのに役立ちます。

- どの ID がどのリソースにアクセスできる必要がありますか?
- これらの ID は、そのアクセスで何を行っていますか?
- アクセスを管理するための適切な組織的管理は存在しているか。
- 監査者は管理が効果的に機能していることを確認できるか。

Microsoft Entra ID ガバナンスを使用すると、従業員、ビジネス パートナー、ベンダーに対して次のシナリオを実装できます。

- ID ライフサイクルの管理
- アクセスのライフサイクルの管理
- 管理に使用される特権アクセスのセキュリティ保護

### ID ライフサイクル

Identity Governance により、組織は、*生産性* (従業員が組織に加わったときなどに、必要なリソースへのアクセスできるようになるまでの時間) と *セキュリティ* (従業員の雇用形態の変更などによって、時間の経過に伴いアクセス権をどのように変更すべきか) とのバランスを取ることができます。 ID ライフサイクル管理は ID 管理の基盤であり、大規模な効果的な管理には、アプリケーションの ID ライフサイクル管理インフラストラクチャの近代化が必要です。

[Image: ID ライフサイクル]

多くの組織では、従業員および他の作業者の ID ライフサイクルは、HCM (ヒューマン キャピタル マネジメント) または HR システムでのその人の表示に関連付けられています。 組織は、1 日目に従業員の生産性を高めるために、そのシステムからのシグナルに基づいて新しい従業員の ID を作成するプロセスを自動化する必要があります。 また、組織は、従業員が組織を退職するときにそれらの ID およびアクセス管理が削除されるようにする必要があります。

Microsoft Entra ID ガバナンスでは、以下を使用して、アイデンティティ ライフサイクルを自動化できます。

- [組織の人事ソースからの送信プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)(Workday と SuccessFactors からの取得を含む)、Active DirectoryとMicrosoft Entra IDの両方でユーザー ID を自動的に維持します。
- [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows): 特定の重要なイベント (たとえば、新しい従業員が組織で作業を開始するようにスケジュールされる前、従業員のステータスが組織内にいる間に変更したとき、従業員が組織を退職するときなど) で実行されるワークフロー タスクを自動化します。 たとえば、新しいユーザーのマネージャーに一時アクセス パスを記載した Email を送信したり、そのユーザーの初日にウェルカム メールを送信するようなワークフローを構成することができます。
- [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)ユーザーの属性の変更に基づいて、ユーザーのグループ メンバーシップ、アプリケーション ロール、SharePoint サイトロールを追加および削除する自動割り当てポリシー。
- SCIM、LDAP、SQL を介して[数百のクラウドおよびオンプレミス アプリケーション](https://learn.microsoft.com/ja-jp/entra/id-governance/what-is-provisioning) へのコネクタを使用して、他のアプリケーションのユーザー アカウントを作成、更新、および削除するための[ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/id-governance/apps)。

また、組織は、パートナー、サプライヤー、その他のゲストが共同作業を行ったり、リソースにアクセスしたりできるようにするために、追加の ID を必要とします。

Microsoft Entra ID ガバナンスでは、ビジネスグループがどのゲストをどのくらいの期間アクセスさせるかを決定するために使用できます。

- [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview): あなたの組織のリソースに対するアクセスを要求できる ID を持つ、他の組織を指定できます。 いずれかの ID 要求が承認されると、エンタイトルメント管理によって、組織のディレクトリに [B2B](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) ゲストとして自動的に追加されます。 その後、適切なアクセス権が割り当てられます。 エンタイトルメント管理では、アクセス権の有効期限が切れたり取り消されたりすると、B2B ゲスト ユーザーが組織のディレクトリから自動的に削除されます。
- 組織のディレクトリに既に存在する既存のゲストの定期的なレビューを自動化し、アクセスが不要になったときに組織のディレクトリからそれらの ID を削除する[アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)。 AI を活用した提案は、レビュー担当者がより適切な情報に基づいて意思決定を行うのに役立ちます。

詳しくは、[従業員とゲストのライフサイクルの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/govern-the-employee-lifecycle)に関する記事をご覧ください。

### アクセスのライフサイクル

組織では、ユーザーの ID の作成時にそのユーザー用に最初にプロビジョニングしたアクセス権を上回るアクセス権を管理するプロセスが必要です。 さらに、企業組織では、アクセス ポリシーと管理を継続的に開発し適用できるように、効率的なスケールが可能でなければなりません。

[Image: アクセスのライフサイクル]

Microsoft Entra ID ガバナンスを使用すると、IT 部門は、さまざまなリソースで必要なアクセス権 ID を確立できます。 また、職務の分離やジョブの変更に対するアクセスの削除が必要な場合など、必要な強制チェックを決定することもできます。 Microsoft Entra IDには、クラウドおよびオンプレミスのアプリケーション数百に対するコネクタがあります。 AD グループに依存する組織の他のアプリ、他のオンプレミス ディレクトリ または データベース、SOAP または REST API (SAPを含む )、または SCIM、SAML、OpenID Connect などの標準 実装するアプリを統合できます。 ユーザーがこれらのアプリケーションのいずれかにサインインしようとすると、Microsoft Entra IDは [Conditional Access](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/) ポリシーを適用します。 条件付きアクセス ポリシーには、たとえば、[使用条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use)を表示することと、[ユーザーがそれらの条件に確実に同意した](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-require-terms-of-use)後でアプリケーションにアクセスできるようになることを含めることができます。 詳細については、環境内のアプリケーションへのアクセス 管理、アプリケーションへのアクセスを管理するための組織ポリシーの定義、アプリケーションの統合 、ポリシーの展開 方法を参照してください。

アプリとグループ間のアクセス変更は、属性の変更に基づいて自動化できます。 [Microsoft Entraライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/create-lifecycle-workflow)と[Microsoft Entraエンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)は、アプリケーションとリソースへのアクセスが更新されるように、グループまたはアクセス パッケージに ID を自動的に追加および削除します。 ID は、組織内の条件が異なるグループに変更されたときに移動することもでき、すべてのグループまたはアクセス パッケージから完全に削除することもできます。

以前にオンプレミスの ID ガバナンス製品を使用していた組織は、組織のロールモデルを Microsoft Entra ID ガバナンスに移行できます。

さらに、IT 部門は、ビジネスの意思決定者にアクセス管理の判断を委任できます。 たとえば、ヨーロッパの会社のマーケティング アプリケーションで機密の顧客データにアクセスする従業員は、マネージャー、部門リーダーまたはリソース所有者、およびセキュリティ リスク責任者からの承認が必要になる場合があります。 [Entitlement management](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview) を使用すると、ID がグループおよびチーム メンバーシップ、アプリ ロール、および SharePoint Online ロールのパッケージ間でアクセスを要求する方法を定義し、アクセス要求に対する職務チェックの分離を強制できます。 アクセス パッケージでは、定期的なアクセス レビューが必要になる場合があり、グループ メンバーシップなどのその他のアクセス権は、定期的な [Microsoft Entra アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview) を使用して定期的に確認できます。これには、より詳細な調査が必要になる可能性がある AI で識別されたピア外れ値など、アクセスの再認定が必要です。

また、組織は、 [オンプレミス のアプリケーション](https://learn.microsoft.com/ja-jp/entra/external-id/hybrid-cloud-to-on-premises)を含め、アクセス権を持つゲスト ID を制御することもできます。

### 特権アクセスのライフサイクル

特に、管理者権限に関連した誤用が組織にもらたす可能性のある潜在的な問題を考慮した場合、特権アクセスの管理は最新の ID ガバナンスの重要な部分であると考えています。 管理者権限を引き受ける従業員、ベンダー、および契約社員は、自分のアカウントと特権アクセスを管理する必要があります。

[Image: 特権アクセスのライフサイクル]

[Microsoft Entra Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) では、Microsoft Entra、Azure、その他の Microsoft Online Services などのアプリケーションにわたって、リソースのアクセス権をセキュリティで保護するために調整された追加の制御が提供されます。 多要素認証と条件付きアクセスに加えて、Microsoft Entra PIM によって提供される Just-In-Time アクセスとロール変更アラート機能には、組織のリソース (ディレクトリ ロール、Microsoft 365 ロール、Azure リソース ロール、グループ メンバーシップ) をセキュリティで保護するための包括的なガバナンス制御セットが用意されています。 他の形式のアクセスと同様に、組織はアクセス レビューを使用して、特権管理者ロールのすべての ID に対して定期的なアクセスの再認定を構成できます。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID ガバナンスまたはMicrosoft Entra スイートライセンスが必要です。 要件に適したライセンスを見つけるには、[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を参照してください。

### 作業の開始

Microsoft Entra ID の ID ガバナンスを構成する前に、[前提条件](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-prepare) を確認してください。 次に、Microsoft Entra 管理センターの [Governance ダッシュボード](https://entra.microsoft.com/#view/Microsoft_Azure_IdentityGovernance/Dashboard.ReactView) にアクセスして、エンタイトルメント管理、アクセス レビュー、ライフサイクル ワークフロー、Privileged Identity Managementの使用を開始します。

[エンタイトルメント管理のリソースへのアクセスを管理するためのチュートリアルもあります](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-first)、[承認プロセスを経て外部ユーザーをMicrosoft Entra IDにオンボーディングする方法](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-onboard-external-user)、[アプリケーションとその既存のユーザーへのアクセスを管理するためのガイド](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-prepare)があります。

各組織には独自の要件がある場合もありますが、次の構成ガイドでは、より安全で生産性の高い従業員を確保するために従うことをお勧めMicrosoftベースライン ポリシーも提供しています。

- [アクセス レビューをデプロイしてリソース アクセス ライフサイクルを管理する計画](https://learn.microsoft.com/ja-jp/entra/id-governance/deploy-access-reviews)
- [ゼロ トラスト ID とデバイス アクセスの構成](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/microsoft-365-policies-configurations)
- [特権アクセスのセキュリティ保護](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-planning)

また、Microsoftの [サービスおよび統合パートナー](https://learn.microsoft.com/ja-jp/entra/id-governance/services-and-integration-partners)と連携して、デプロイを計画したり、環境内のアプリケーションやその他のシステムと統合したりすることもできます。

Identity Governance 機能に関するフィードバックがある場合は、Microsoft Entra 管理センターで **Got feedback?** を選択してフィードバックを送信します。 お客様からのフィードバックは、チームにて定期的に検討しております。

### 自動化による ID ガバナンス・タスクの簡素化

これらの ID ガバナンス機能の使用を開始すると、一般的な ID ガバナンス シナリオを簡単に自動化できます。 次の表に、各シナリオの自動化を始める方法を示します。

| 自動化するためのシナリオ | オートメーションガイド |
| --- | --- |
| 従業員の AD および Microsoft Entra ユーザー アカウントの自動作成、更新、削除 | [Microsoft Entraへのユーザープロビジョニングを計画するクラウドHR](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision) |
| メンバー ユーザーの属性の変更に基づくグループのメンバーシップの更新 | [動的グループの作成](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule) |
| ライセンスの割り当て | [グループベースのライセンス](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true) |
| ユーザーの属性の変更に基づいて、ユーザーのグループ メンバーシップ、アプリケーション ロール、SharePoint サイト ロールを追加および削除する | [エンタイトルメント管理でアクセス パッケージの自動割り当てポリシーを構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy) |
| 特定の日付にユーザーのグループ メンバーシップ、アプリケーション ロール、SharePoint サイトの役割を追加および削除する | [エンタイトルメント管理でアクセス パッケージのライフサイクル設定を構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-lifecycle-policy) |
| ユーザーがアクセスを要求または受信したとき、またはアクセスが削除されたときにカスタム ワークフローを実行する | [エンタイトルメント管理で Logic Apps をトリガーする](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logic-apps-integration) |
| Microsoft グループと Teams のゲストのメンバーシップを定期的に確認し、拒否されたゲスト メンバーシップを削除する | [アクセス レビューの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review) |
| レビュー担当者によって拒否されたゲストアカウントを削除する | [リソース アクセス権を持たない外部ユーザーを確認して削除](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-external-users) |
| アクセス パッケージが割り当てられていないゲスト アカウントを削除する | [外部ユーザーのライフサイクルを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users#manage-the-lifecycle-of-external-users) |
| 独自のディレクトリまたはデータベースを持つオンプレミスおよびクラウドアプリケーションへのユーザーのプロビジョニング | [ユーザー割り当て](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)または[スコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用して自動ユーザー プロビジョニングを構成する |
| その他のスケジュール済みタスク | Azure Automation、Microsoft を介したMicrosoft Graph。Graph.Identity.Governance PowerShell モジュール |
| アプリケーションで孤立したアカウントまたはローカル アカウントを検出する | アプリケーション内の既存のユーザーを[検出](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery)します。 |

### エージェントの ID ガバナンス (プレビュー)

Microsoft エージェント ID プラットフォームを追加することで、エージェントの ID とアクセスをユーザーと同じ方法で管理することは、組織のガバナンス ライフサイクルでも同様に重要になります。 Microsoft Entra エージェント IDでは、AI エージェント用の専用の ID コンストラクトである [agent ID](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id) を導入し、企業規模で運用される自律型および半自律的なエージェントの固有の課題に対処するガバナンス コントロールを使用します。

すべてのエージェント ID には、エージェントの目的、ライフサイクルの決定、アクセス レビューに対して責任を負う人間の [スポンサー](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-owners-sponsors-managers) が必要です。 スポンサーが組織を離れる場合、スポンサープランは自動的にマネージャーに転送され、継続的な人間の監視が保証されます。 [エージェント ID ブループリントは](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/agent-blueprint) 、組織が条件付きアクセス規則、アクセス許可、ガバナンス制御を 1 回適用し、現在および将来のすべてのエージェント インスタンスが自動的に継承できるようにする一元化されたテンプレートとして機能します。 このブループリント モデルを使用すると、管理者は、1 回の操作でエージェントのクラス全体のアクセス許可を管理、無効化、または取り消すことができます。

エージェント ID は、人間の ID で使用できるのと同じ [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview) アクセス パッケージを通じて管理され、承認ワークフローを使用して時間制限付きの監査可能なアクセスを提供します。 スポンサーは有効期限の通知を受け取り、拡張機能を要求したり、アクセスを期限切れにしたりすることができます。 [ライフサイクル ワークフローは、](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-sponsor-tasks) スポンサープランの変更が発生したときにスポンサー切り替え通知を自動化します。 すべてのエージェント ID は、Microsoft Entra 管理センターとMicrosoft Graphを介して検出可能、検索可能、およびクエリ可能であり、エージェントのスプロールとシャドウ AI を防ぐための一元的な可視性を組織に提供します。

詳細については、「 [エージェント ID の管理 (プレビュー)」を](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-id-governance-overview)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/licensing-fundamentals"} -->
## Microsoft Entra ID ガバナンス ライセンスの基礎 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals
- Service: entra-id-governance
- Article date: 2026-07-29
- Summary: Microsoft Entra ID ガバナンスライセンスの種類、前提条件、機能要件、試用版、および従業員、ゲスト、エージェントのライセンスについて説明します。

この記事では、従業員のMicrosoft Entra ID ガバナンスライセンスについて説明します。 IT の意思決定者、IT 管理者、および組織のMicrosoft Entra ID ガバナンス サービスを検討している IT プロフェッショナルを対象としています。

ゲスト ユーザーのガバナンス ライセンスについては、「ゲスト ユーザーの[Microsoft Entra ID ガバナンスライセンス](https://learn.microsoft.com/ja-jp/entra/id-governance/microsoft-entra-id-governance-licensing-for-guest-users)」を参照してください。 プレビューのエージェント ID ライセンス要件については、「 [エージェント ID の管理 (プレビュー)」を](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-id-governance-overview#license-requirements)参照してください。 テナント ガバナンス管理者と機能に適用されるライセンスについては、「[Microsoft Entra テナント ガバナンスのライセンス](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/licensing)」を参照してください。

### ライセンスの種類

次のライセンスは、商用クラウドおよび政府機関向けクラウドのMicrosoft Entra ID ガバナンスで使用できます。 テナントで必要なライセンスのラインアップは、テナントで使用している機能によって異なります。

- **Free** - Microsoft Azure、Microsoft 365などのMicrosoftクラウド サブスクリプションに含まれます。
- **Microsoft Entra ID P1** - Microsoft Entra ID P1 はスタンドアロン製品として使用できます。または、エンタープライズのお客様向けのMicrosoft 365 E3と中小企業向けのMicrosoft 365 Business Premiumに含まれています。
- **Microsoft Entra ID P2** - Microsoft Entra ID P2 はスタンドアロン製品として使用できます。または、企業のお客様向けのMicrosoft 365 E5に含まれています。
- **Microsoft Entra ID ガバナンス** - Microsoft Entra ID ガバナンスは、Microsoft Entra ID P1 および P2 のお客様が利用できる高度な ID ガバナンス機能のセットです。 Microsoft Entra ID ガバナンス は、6 つの製品として利用可能です: **Microsoft Entra ID ガバナンス**、**Microsoft Entra ID ガバナンス Step Up for Microsoft Entra ID P2**、**Entra ID ガバナンス フロントライン ワーカー**、**Microsoft Entra ID ガバナンス Step Up for Microsoft Entra ID F2**、**Microsoft Entra ID ガバナンス for Government**、および**Microsoft Entra ID ガバナンス Add-on for Microsoft Entra ID P2 for Government**。 これら 6 つの製品は、前提条件のみが異なります。エンタイトルメント管理、特権 ID 管理とアクセス レビューの両方の機能 (Microsoft Entra ID P2) と、追加の高度な ID ガバナンス機能が含まれています。 次のセクションでは、これらの製品のさまざまな前提条件について詳しく説明します。
- **Microsoft Entra スイート** - Microsoft Entra スイートは、Microsoft Entra ID P1 および P2 のお客様が利用できる、従業員アクセスのための完全なクラウドベースのソリューションです。 Microsoft Entra スイートは、Microsoft Entra Private Access、Microsoft Entra Internet Access、Microsoft Entra ID ガバナンス、Microsoft Entra ID 保護、および Microsoft Entra Verified ID。 Microsoft Entra ID ガバナンス部分は、**Microsoft Entra ID ガバナンス** 製品と同じ ID ガバナンス機能を提供します。 違いは、前提条件が異なっていることです。
- **Microsoft Agent 365** - Microsoft Agent 365 は、エージェント ID の ID ガバナンス機能を提供し、Entra のエンタイトルメント管理によるエージェントとサービス プリンシパルのアクセス ガバナンス、ライフサイクル ワークフローによる追加の自動化、およびエージェント ID とアクセス ライフサイクルを管理するためのスポンサー向けのエンド ユーザー エクスペリエンスを提供します。 次のセクションでは、前提条件と機能について詳しく説明します。 Microsoft エージェント 365 の詳細については、「[Microsoft Agent 365](https://www.microsoft.com/licensing/faqs/122)を参照してください。

注

一部のMicrosoft Entra ID ガバナンスシナリオは、Microsoft Entra ID ガバナンスでカバーされていない他の機能に依存するように構成できます。 これらの機能には、追加のライセンス要件がある場合があります。 他の機能を使用したガバナンス シナリオの詳細については、 [ID ガバナンスの概要](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview) ページを参照してください。

Government 向けのMicrosoft Entra ID ガバナンスと、Microsoft Entra ID P2 for Government 製品のMicrosoft Entra ID ガバナンス アドオンは、米国政府機関向けコミュニティ クラウド (GCC)、GCC-High、および国防総省のクラウド環境で利用できます。

#### ガバナンス製品と前提条件

Microsoft Entra ID ガバナンス機能は現在、6 つのスタンドアロン製品で利用できます。 これら 6 つの製品は、同じ ID ガバナンス機能を提供します。 6 つの製品の違いは、前提条件が異なっていることです。

- Microsoft Entra ID ガバナンス または Microsoft Entra ID ガバナンス for Government のサブスクリプションは、製品条項で Microsoft Entra ID ガバナンス(ユーザー SL) 製品として記載されていますが、そのテナントには  または  サービスプランを含む別の製品へのアクティブなサブスクリプションも必要です。 この前提条件を満たす製品の例としては、**Microsoft Entra ID P1**、**Microsoft 365 E3/E5/A3/G3/G5**、または **Enterprise Mobility + Security E3/E5** があります。
- **Microsoft Entra ID ガバナンス Step Up for Microsoft Entra ID P2** または **Microsoft Entra ID ガバナンス Add-on for Microsoft Entra ID P2 for Government** は、製品条項に **Microsoft Entra ID ガバナンス P2** 製品として記載されていますが、テナントに `AAD_PREMIUM_P2` サービス プランを含む別の製品のアクティブなサブスクリプションも必要です。 この前提条件を満たす製品の例としては、**Microsoft Entra ID P2**、**Microsoft 365 E5/A5/G5**、**Enterprise Mobility + Security E5**、**Microsoft 365 E5/F5 Security** または **Microsoft 365 F5 Security + Compliance** などがあります。
- **Entra ID Governance Frontline Worker (User SL)** 製品のサブスクリプションでは、テナントにも、`AAD_PREMIUM` または `AAD_PREMIUM_P2` サービス プランを含む別の製品へのアクティブなサブスクリプションが必要です。 この前提条件を満たす製品の例としては、Microsoft Entra ID P1、Microsoft 365 E3/E5/A3/G3/G5、Enterprise Mobility + Security E3/E5、Microsoft 365 F1/F3。
- **Microsoft Entra ID ガバナンス Step-Up for Microsoft Entra ID F2** のサブスクリプションは、製品の用語において **Microsoft Entra ID ガバナンス F2** または **Microsoft Entra ID ガバナンス Step-Up for Microsoft Entra ID F2 for Frontline Worker (User SL)** として記載されています。テナントは、`AAD_PREMIUM_P2` サービス プランを含む別の製品へのアクティブなサブスクリプションも保持している必要があります。 この前提条件を満たす製品の例としては、**Microsoft Entra ID F2**があります。
- **Microsoft Agent 365** のサブスクリプションでは、テナントにも、`AAD_PREMIUM` サービス プランを含む別の製品へのアクティブなサブスクリプションが必要です。 この前提条件を満たす製品の例としては、**Microsoft Entra ID P1** または **Microsoft 365 E3** があります。

Microsoft Entra ID ガバナンス機能もMicrosoft Entra スイートに含まれています。 使用可能なMicrosoft Entra スイート製品には、**Microsoft Entra スイート (User SL)**、**Microsoft Entra スイート アドオン for Microsoft Entra ID F2 for FLW (User SL)**、**Microsoft Entra スイート アドオン for Microsoft Entra ID P2 (User SL)**、**Microsoft Entra スイート アドオン for Microsoft Entra ID P2 EDU (User SL)**、**Microsoft Entra スイート FLW (User SL)**、および**Microsoft Entra スイート for EDU (User SL)**が含まれます。

[ライセンスの製品名とサービス プラン識別子には、前提条件となるサービス プラン](https://learn.microsoft.com/ja-jp/entra/identity/users/licensing-service-plan-reference)を含む追加の製品が一覧表示されます。

注

Microsoft Entra ID ガバナンス製品の前提条件のサブスクリプションがテナントでアクティブになっている必要があります。 前提条件がない場合、またはサブスクリプションの有効期限が切れた場合、Microsoft Entra ID ガバナンスシナリオが想定どおりに機能しない可能性があります。

Microsoft Entra ID ガバナンス製品の前提条件製品がテナントに存在するかどうかを確認するには、Microsoft Entra 管理センターまたはMicrosoft 365 管理センターを使用して製品の一覧を表示できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com) に [License Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#license-administrator) としてサインインします。
2. **[請求]**&gt;**[ライセンス]** に移動します。
3. [ **管理** ] メニューで、[ **ライセンスされた機能**] を選択します。 情報バーは、現在のMicrosoft Entra IDライセンス プランを示します。
4. テナント内の既存の製品を表示するには、[ **管理** ] メニューで [ **すべての製品**] を選択します。

ゲスト ユーザーのガバナンスでは、ゲスト課金用に Azure サブスクリプションを選択する必要があります。 詳細については、「ゲスト ユーザーの[Microsoft Entra ID ガバナンスライセンス](https://learn.microsoft.com/ja-jp/entra/id-governance/microsoft-entra-id-governance-licensing-for-guest-users)を参照してください。

### 試用版の開始

Microsoft Entra ID P1 など、適切な前提条件製品を既に購入しており、Microsoft Entra ID ガバナンスをまだ使用していない、または以前に試用していない商用テナントのグローバル管理者は、テナント内でMicrosoft Entra ID ガバナンスの試用版を要求できます。

1. [Microsoft 365 管理センター](https://admin.microsoft.com/AdminPortal/Home)に[グローバル管理者としてサインインします](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)
2. [ **課金** ] メニューで、[ **サービスの購入**] を選択します。
3. [**すべての製品カテゴリを検索** ボックスに「`"Microsoft Entra ID ガバナンス"`」と入力します。
4. **Details** **Microsoft Entra ID ガバナンス** を選択して、製品の試用版と購入情報を表示します。 テナントが Microsoft Entra ID P2 を利用している場合は、以下の **Microsoft Entra ID P2 用 Microsoft Entra ID ガバナンスの強化**の下にある **詳細** を選択してください。
5. 製品の詳細ページで、[ **無料試用版の開始**] を選択します。

### Microsoft Entra ID ガバナンス 機能

次の表に、メンバー ユーザーのMicrosoft Entra ID ガバナンス機能のライセンス要件を示します。 Microsoft Entra スイートには、Microsoft Entra ID ガバナンスのすべての機能が含まれています。 Microsoft 365 E7 には、Entra スイートおよび Agent 365 を通じてのすべての ID ガバナンス機能も含まれています。 ライセンス情報と、エンタイトルメント管理、アクセス レビュー、ライフサイクル ワークフローのライセンス シナリオの例を表の後に示します。

#### ライセンス別の機能

次の表に、各ライセンスで使用できる ID ガバナンスに関連付けられている機能を示します。 その他の機能の詳細については、「[Microsoft Entra プランと価格](https://www.microsoft.com/security/business/microsoft-entra-pricing)を参照してください。 すべての機能がすべてのクラウドで利用できるわけではありません。Azure Governmentについては、[Microsoft Entra機能の可用性](https://learn.microsoft.com/ja-jp/entra/identity/authentication/feature-availability)に関する記事を参照してください。

| 特徴 | Free | Microsoft Entra ID P1 | Microsoft Entra ID P2 | Microsoft Entra ID ガバナンス | Microsoft Entra スイート | Microsoft エージェント 365 |
| --- | --- | --- | --- | --- | --- | --- |
| **プロビジョニング** |  |  |  |  |  |  |
| [API 駆動型のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts) |  | ✅ | ✅ | ✅ | ✅ |  |
| [人事主導のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/what-is-hr-driven-provisioning) |  | ✅ | ✅ | ✅ | ✅ |  |
| [アカウントの検出](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery) |  |  |  | ✅ | ✅ |  |
| [SaaS アプリへの自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list) | ✅ | ✅ | ✅ | ✅ | ✅ |  |
| [SaaS アプリへの自動グループ プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list) |  | ✅ | ✅ | ✅ | ✅ |  |
| [オンプレミス アプリへの自動プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-application-provisioning-architecture) |  | ✅ | ✅ | ✅ | ✅ |  |
| [ユーザーのテナント間同期 (同じクラウド)](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure?pivots=cross-cloud-synchronization) |  | ✅ | ✅ | ✅ | ✅ |  |
| [グループのテナント間同期 (同じクラウド)](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure?pivots=cross-cloud-synchronization) |  |  |  | ✅ | ✅ |  |
| [クラウド間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure?pivots=cross-cloud-synchronization) |  |  |  | ✅ | ✅ |  |
| **ライフサイクル ワークフロー (LCW)** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows) |  |  |  | ✅ | ✅ |  |
| [LCW + カスタム拡張機能 (Logic Apps)](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-extensibility) |  |  |  | ✅ | ✅ |  |
| [LCW + エージェント スポンサーシップ タスク](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-sponsor-tasks) |  |  |  |  |  | ✅ |
| **アクセス レビュー (AR)** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| AR - 以前は Microsoft Entra ID P2 |  |  | ✅ | ✅ | ✅ |  |
| [AR - グループ向け PIM (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review-pim-for-groups) |  |  |  | ✅ | ✅ |  |
| [AR - レビューでアクティブなユーザーがいない非アクティブなユーザーを対象にしたレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review#scope) |  |  |  | ✅ | ✅ |  |
| [AR - アクティブおよび非アクティブユーザーを対象にし、レビュアーが非アクティブユーザー向けのレビュー意思決定ヘルパーを使用するレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/review-recommendations-access-reviews#inactive-user-recommendations) |  |  | ✅ | ✅ | ✅ |  |
| [AR - 機械学習支援アクセスの認定とレビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/review-recommendations-access-reviews#user-to-group-affiliation) |  |  |  | ✅ | ✅ |  |
| [AR - カタログ アクセス レビュー (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/catalog-access-reviews) |  |  |  | ✅ | ✅ |  |
| [AR - カスタム データ提供リソース (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/custom-data-resource-access-reviews) |  |  |  | ✅ | ✅ |  |
| **エンタイトルメント管理 (EM)** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| EM - 以前は Microsoft Entra ID P2 |  |  | ✅ | ✅ | ✅ |  |
| [EM - アクセス パッケージに割り当てられたユーザー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#allow-users-service-principals-and-agent-identities-in-your-directory-to-request-the-access-package) |  |  | ✅ | ✅ | ✅ |  |
| [EM - アクセス パッケージに割り当てられたエージェントとサービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#allow-users-service-principals-and-agent-identities-in-your-directory-to-request-the-access-package) |  |  |  |  |  | ✅ |
| [EM - ユーザーが自分のアクセス権を要求する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview) |  |  | ✅ | ✅ | ✅ |  |
| [EM - 管理者がユーザーの割り当てを直接割り当てる (ゲストを含む)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-an-identity) |  |  | ✅ | ✅ | ✅ |  |
| [EM - 管理者がエージェントとサービス プリンシパルを直接割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-any-identity) |  |  |  |  |  | ✅ |
| [EM - 管理者は、ディレクトリにまだ存在しないユーザーの電子メール アドレスを使用して、ユーザーを直接割り当てます](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-any-identity) |  |  |  | ✅ | ✅ |  |
| [EM - 従業員に代わって要求するマネージャー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-behalf) |  |  |  | ✅ | ✅ |  |
| [EM - 所有者とスポンサーがエージェントまたはサービス プリンシパルに代わってアクセスを要求する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-behalf#scenarios-for-requesting-on-behalf-of-agent-identities) |  |  |  |  |  | ✅ |
| **EM - サポートされているリソース** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| [EM - アクセス パッケージ内のグループとチーム](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources#add-a-group-or-team-resource-role) |  |  | ✅ | ✅ | ✅ |  |
| [EM - アクセス パッケージの対象となるグループの所有権とメンバーシップ (グループの場合は PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-eligible) |  |  |  | ✅ | ✅ |  |
| [EM - アクセス パッケージ内のアプリケーション](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources#add-an-application-resource-role) |  |  | ✅ | ✅ | ✅ |  |
| [EM - アクセス パッケージ内のSharePointサイト](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources#add-a-sharepoint-site-resource-role) |  |  | ✅ | ✅ | ✅ |  |
| [EM - Microsoft Entra 役割 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-roles) |  |  |  | ✅ | ✅ |  |
| [EM - SAP Identity Access Governance (IAG) ビジネス ロール (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-sap-integration) |  |  |  | ✅ | ✅ |  |
| [EM - アクセス パッケージ内の API アクセス許可](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources#add-an-api-permission) |  |  |  |  |  | ✅ |
| **EM - 承認オプション** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| [EM - アクションが実行されない場合は、代替承認者による複数ステージの承認](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy) |  |  | ✅ | ✅ | ✅ | ✅ |
| [EM - 指定の承認者](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy) |  |  | ✅ | ✅ | ✅ | ✅ |
| [EM - 承認者としてのマネージャー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy) |  |  | ✅ | ✅ | ✅ |  |
| [EM - 担当者の所属組織から接続された内部スポンサーが承認者となる](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy) |  |  | ✅ | ✅ | ✅ |  |
| [EM - 承認者としての外部のスポンサー (担当者の関連組織から)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy) |  |  | ✅ | ✅ | ✅ |  |
| [EM - 承認者としてのスポンサー (アサインされたユーザーのプロファイルから)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy) |  |  |  | ✅ | ✅ |  |
| [EM - 承認者としてのエージェント スポンサー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy) |  |  |  |  |  | ✅ |
| [EM - カスタム拡張機能を使用して承認要件を外部で決定する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-dynamic-approval) |  |  |  | ✅ | ✅ |  |
| [EM - 承認のために追加の要求者情報を収集する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-approval-policy#collect-additional-requestor-information-for-approval) |  |  | ✅ | ✅ | ✅ |  |
| **EM - ライフサイクル** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| [EM - アクセス パッケージの割り当ての有効期限](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-lifecycle-policy) |  |  | ✅ | ✅ | ✅ | ✅ |
| [EM - 外部ユーザーのライフサイクルを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users#manage-the-lifecycle-of-external-users) |  |  | ✅ | ✅ | ✅ |  |
| [EM - ゲストを管理対象としてマークする](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-manage-lifecycle) |  |  |  | ✅ | ✅ |  |
| **EM - その他の機能** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| [EM - 職務の分離](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-incompatible) |  |  | ✅ | ✅ | ✅ |  |
| [EM - カスタム拡張機能 (Logic Apps)](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-logic-apps-integration) |  |  |  | ✅ | ✅ |  |
| [EM - 自動割り当てポリシー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy) |  |  |  | ✅ | ✅ |  |
| [EM - 検証済み ID の統合](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-verified-id-settings) |  |  |  | ✅ | ✅ |  |
| [EM - Microsoft Entra ID 保護 の統合](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-configure-id-protection-approvals) |  |  |  | ✅ | ✅ |  |
| [EM - Microsoft Purview インサイダー リスク管理統合](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-configure-insider-risk-management-approvals) |  |  |  | ✅ | ✅ |  |
| [EM - 条件付きアクセススコープ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users#review-your-conditional-access-policies) |  |  | ✅ | ✅ | ✅ |  |
| **マイ アクセス** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| [マイ アクセス ポータル](https://learn.microsoft.com/ja-jp/entra/id-governance/my-access-portal-overview) |  |  | ✅ | ✅ | ✅ | ✅ |
| [EM - マイ アクセス検索](https://learn.microsoft.com/ja-jp/entra/id-governance/my-access-portal-overview) |  |  | ✅ | ✅ | ✅ | ✅ |
| [EM - マイ アクセスで推奨されるアクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-suggested-access-packages) |  |  |  | ✅ | ✅ |  |
| [EM - 要求者がマイ アクセス (プレビュー) で承認者の詳細を表示できるかどうかを構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/my-access-approver-details) |  |  |  | ✅ | ✅ |  |
| [EM - マイ アクセスで承認を委任する (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/my-access-portal-overview) |  |  |  | ✅ | ✅ |  |
| **Privileged Identity Management (PIM)** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| [Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) |  |  | ✅ | ✅ | ✅ |  |
| [グループの PIM](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/concept-pim-for-groups) |  |  | ✅ | ✅ | ✅ |  |
| [PIM 条件付きアクセス制御](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings#on-activation-require-microsoft-entra-conditional-access-authentication-context) |  |  | ✅ | ✅ | ✅ |  |
| [PIM - ロールアクティブ化のカスタム拡張機能 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/privileged-identity-management-custom-extensions) |  |  |  | ✅ | ✅ |  |
| **その他** | **無料** | **Microsoft Entra ID P1** | **Microsoft Entra ID P2** | **Microsoft Entra ID ガバナンス** | **Microsoft Entra スイート** | **Microsoft エージェント 365** |
| [ID ガバナンス ダッシュボード](https://learn.microsoft.com/ja-jp/entra/id-governance/governance-dashboard) |  | ✅ | ✅ | ✅ | ✅ |  |
| [分析情報とレポート - 非アクティブなゲスト アカウント](https://learn.microsoft.com/ja-jp/entra/identity/users/clean-up-stale-guest-accounts) |  |  |  | ✅ | ✅ |  |
| [条件付きアクセス - 使用条件の承認](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use) |  | ✅ | ✅ | ✅ | ✅ |  |

#### エンタイトルメント管理

この機能を使用するには、組織のメンバー ユーザーのMicrosoft Entra ID ガバナンス サブスクリプションが必要です。 この機能内の一部の機能は、Microsoft Entra ID P2 サブスクリプションで動作できます。 この機能内の一部の機能には、ゲスト課金が必要です。

##### ライセンスのシナリオ例

必要なライセンス数の決定に役立つライセンスのシナリオ例をいくつか以下に示します。

| シナリオ | 計算 | ライセンス数 |
| --- | --- | --- |
| Woodgrove Bank の ID ガバナンス管理者が初期カタログを作成します。 ポリシーの 1 つは、 **すべての従業員** (2,000 人の従業員) が特定のアクセス パッケージのセットを要求できることを指定します。 150 人の従業員がアクセス パッケージを要求します。 | アクセス パッケージを要求可能な従業員は 2,000 人いる | 二千 |
| Woodgrove Bank の ID ガバナンス管理者が初期カタログを作成します。 **営業部門のすべてのメンバー** (350 人の従業員) に特定のアクセス パッケージへのアクセス権を付与する自動割り当てポリシーを作成します。 350 人の従業員がアクセス パッケージに自動的に割り当てられます。 | 350 人の従業員にライセンスが必要です。 | 3:51 |

#### アクセス レビュー

この機能を使用するには、組織のメンバー ユーザーのMicrosoft Entra ID ガバナンス サブスクリプションが必要です。これには、アクセス権を確認している従業員やアクセス権をレビューしているすべての従業員が含まれます。 この機能内の一部の機能は、Microsoft Entra ID P2 サブスクリプションで動作する場合があります。 この機能内の一部の機能には、ゲスト課金が必要です。

##### ライセンスのシナリオ例

必要なライセンス数の決定に役立つライセンスのシナリオ例をいくつか以下に示します。

| シナリオ | 計算 | ライセンス数 |
| --- | --- | --- |
| 管理者は、75 人のメンバー ユーザーと 1 人のグループ所有者を持つグループ A のアクセス レビューを作成し、グループ所有者をレビュー担当者として割り当てます。 | レビュー担当者としてのグループ所有者のライセンスが 1 つ、75 人のユーザーに対して 75 ライセンス。 | 76 |
| 管理者は、500 人のメンバー ユーザーと 3 人のグループ所有者を含むグループ B のアクセス レビューを作成し、3 人のグループ所有者をレビュー担当者として割り当てます。 | ユーザーには 500 ライセンス、レビュー担当者としてグループ所有者ごとに 3 つのライセンス。 | 503 |
| 管理者は、500 人のメンバー ユーザーを含むグループ B のアクセス レビューを作成します。 自己レビューにします。 | 自己レビュー担当者としての各ユーザー用に 500 ライセンス | 5:00 |
| 管理者は、メンバー ユーザーが 50 人のグループ C のアクセス レビューを作成します。 自己レビューにします。 | 自己レビュー担当者としての各ユーザー用に 50 ライセンス。 | 50 |
| 管理者は、メンバー ユーザーが 6 人のグループ D のアクセス レビューを作成します。 自己レビューにします。 | 自己レビュー担当者としての各ユーザー用に 6 ライセンス。 その他のライセンスは不要です。 | 6 |

#### ライフサイクル ワークフロー

ライフサイクル ワークフローのMicrosoft Entra ID ガバナンス ライセンスを使用すると、次のことができます。

- 合計 50 ワークフローまで作成、管理、削除できます。
- オンデマンドおよびスケジュールされたワークフロー実行をトリガーします。
- 既存のタスクを管理および構成して、ニーズに固有のワークフローを作成します。
- ワークフローで使用するカスタム タスク拡張機能を最大 100 個作成します。

この機能を使用するには、組織のメンバー ユーザーのMicrosoft Entra ID ガバナンス サブスクリプションが必要です。 この機能内の一部の機能には、ゲスト課金が必要です。

##### ライセンスのシナリオ例

| シナリオ | 計算 | ライセンス数 |
| --- | --- | --- |
| ライフサイクル ワークフロー管理者はワークフローを作成し、マーケティング部門の新入社員をマーケティング チーム グループに追加します。 このワークフローを使用して、250 人の新規採用メンバー ユーザーがマーケティング チーム グループに 1 回割り当てられます。 他の 150 人の新規採用メンバー ユーザーは、同じ年の後半にこのワークフローを介してマーケティング チーム グループに割り当てられます。 | ライフサイクル ワークフロー管理者に 1 ライセンス、ユーザーに 400 ライセンス。 | 401 |
| ライフサイクル ワークフロー管理者は、雇用最終日の前に従業員のグループに事前オフボードするためのワークフローを作成します。 事前オフボードされるユーザーの範囲は、一度に 40 人です。 40 人のライセンスユーザーをオフボードします。 これらの 40 ライセンスを再割り当てし、今年の後半にさらに 10 ライセンスを割り当てることにより、さらに 50 人のユーザーを事前オフボードできます。 | ユーザーに 50 ライセンス、ライフサイクル ワークフロー管理者に 1 ライセンス。 | 51 |

### Privileged Identity Management

Microsoft Entra Privileged Identity Managementを使用するには、テナントに有効なライセンスが必要です。 この記事では、Privileged Identity Managementを使用するためのライセンス要件について説明します。 Privileged Identity Managementを使用するには、次のいずれかのライセンスが必要です。

#### PIM の有効なライセンス

PIM とそのすべての設定を使用するには、Microsoft Entra ID ガバナンス ライセンスまたは Microsoft Entra ID P2 ライセンスが必要です。 現在、アクセス レビューは、Microsoft Entra ID を持つサービス プリンシパル、Microsoft Entra ID P2 に関連付けられたリソース ロール、またはテナント内で Microsoft Entra ID ガバナンス エディションがアクティブなユーザーに対して範囲を設定することができます。

#### PIM に必要なライセンス

ディレクトリに、次のカテゴリのユーザーに対して Microsoft Entra ID P2 または Microsoft Entra ID ガバナンス ライセンスがあることを確認します。

- PIM を使用して管理されるMicrosoft Entra IDロールまたはAzure ロールに対する有資格割り当てまたは期限付き割り当てがあるユーザー
- グループの PIM のメンバーまたは所有者として、資格のある割り当てまたは期限付きの割り当てを持つユーザー
- PIM でアクティブ化要求を承認または却下できるユーザー
- アクセス レビューに割り当てられたユーザー
- アクセス レビューを実行するユーザー

#### PIM のライセンスのシナリオ例

必要なライセンス数の決定に役立つライセンスのシナリオ例をいくつか以下に示します。

| シナリオ | 計算 | ライセンス数 |
| --- | --- | --- |
| Woodgrove Bank には、部門ごとに 10 人の管理者と、PIM を構成および管理する 2 人の [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) がいます。 5 人の管理者を対象とします。 | 資格のある管理者用の 5 ライセンス | 5 |
| Graphic Design Institute には 25 人の管理者がいて、そのうちの 14 人は PIM で管理されています。 ロールのアクティブ化には承認が必要であり、組織にはアクティブ化を承認できるユーザーが 3 人います。 | 資格のある役割用のライセンス 14 件と承認者 3 名 | 十七 |
| Contoso には 50 人の管理者がいて、そのうちの 42 人は PIM で管理されています。 ロールのアクティブ化には承認が必要であり、組織にはアクティブ化を承認できるユーザーが 5 人います。 Contoso は、管理者ロールに割り当てられたユーザーの月単位のレビューも行い、レビュー担当者はユーザーのマネージャーであり、そのうち 6 人は PIM によって管理される管理者ロールに含まれていません。 | 資格のある役割に対する42ライセンス+5人の承認者+6人のレビュー担当者 | 53 |

#### PIM のライセンスの有効期限が切れた場合

Microsoft Entra ID P2、Microsoft Entra ID ガバナンス、または試用版ライセンスの有効期限が切れると、Privileged Identity Management機能はディレクトリで使用できなくなります。

- Microsoft Entra ロールへの永続的なロールの割り当ては影響を受けません。
- Microsoft Entra 管理センターのPrivileged Identity Management サービス、および Privileged Identity Management のGraph API コマンドレットと PowerShell インターフェイスは、ユーザーが特権ロールをアクティブ化したり、特権アクセスを管理したり、特権ロールのアクセス レビューを実行したりできなくなります。
- ユーザーは特権ロールをアクティブ化できなくなるので、Microsoft Entra ロールの有資格ロールの割り当ては削除されます。
- Microsoft Entraロールに対する進行中のアクセス レビューはすべて終了し、Privileged Identity Managementの構成設定が削除されます。
- Privileged Identity Managementは、ロールの割り当ての変更に関する電子メールを送信しなくなりました。

### API 駆動型のプロビジョニング

この機能は、Microsoft Entra ID P1、P2、および Microsoft Entra ID ガバナンス サブスクリプションで使用できます。 サブスクリプション ライセンスには、[/bulkUpload](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-post-bulkupload) API を使用して取得され、オンプレミスの Active DirectoryまたはMicrosoft Entra IDにプロビジョニングされるすべてのIDに対して十分なライセンス数が必要です。

#### ライセンス シナリオ

| カスタマー ライセンス | API 駆動型のプロビジョニングのテナント レベルで適用される使用制限 |
| --- | --- |
| Microsoft Entra ID P1 または P2 | 1 日の使用量クォータ (24 時間にわたってアップロードできるユーザー レコードの数): **100,000 個のユーザー レコード (最大 50 レコードを含む各要求で 2000 /bulkUpload API 呼び出し**)。各フローの API 駆動型プロビジョニング ジョブの最大数: 2o オンプレミスの Active Directoryへの API 駆動型プロビジョニング用の最大 2 つのアプリ。o Microsoft Entra IDへの API 駆動型プロビジョニング用の最大 2 つのアプリ。 |
| Microsoft Entra ID ガバナンス と Microsoft Entra ID P1 または P2 | 1 日の使用量クォータ (24 時間にわたってアップロードできるユーザー レコードの数): **300,000 ユーザー レコード (最大 50 レコードを含む各要求で 6000 /bulkUpload API 呼び出し**)。各フローの API 駆動型プロビジョニング ジョブの最大数: 20o オンプレミスの Active Directoryへの API 駆動型プロビジョニング用の最大 20 個のアプリ。o Microsoft Entra IDへの API 駆動型プロビジョニング用の最大 20 個のアプリ。 |

### アカウントの検出

アカウントの検出には、Microsoft Entra ID ガバナンス アドオンまたはMicrosoft Entra スイートが必要です。 この機能を使用すると、管理者はターゲット アプリケーション内の既存のユーザー アカウントを検出し、一致する Entra アカウントを持っているユーザーまたは孤立したアカウントを特定できます。 詳細については、「 [アカウント検出を使用してターゲット アプリケーションの ID を検出する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery)参照してください。

### ライセンスに関する FAQ

#### ID ガバナンス機能を使用するには、ライセンスをユーザーに割り当てる必要がありますか?

ユーザーに Microsoft Entra ID ガバナンス ライセンスを割り当てる必要はありませんが、ID ガバナンス機能のスコープまたは構成者にすべてのメンバー ユーザーを含めるには、ライセンスの数が必要です。 さらに、次の回答で説明するように、管理するゲスト ユーザーがある場合は、ゲスト課金モデルを有効にする必要があります。

#### ビジネスゲスト向けのMicrosoft Entra ID ガバナンス機能の使用ライセンスを取得するにはどうすればよいですか?

Microsoft Entra ID ガバナンスでは、従業員のライセンスとは異なり、Azure サブスクリプションが必要なゲスト ユーザーの月間アクティブ ユーザー (MAU) ライセンスを利用します。

ゲスト課金モデルでは、ユーザーの認証場所に関係なく、ゲストは *userType* of Guest によって識別されます。 *userType* of Guest は、すべての B2B 招待メソッドの既定の userType であり、ID 管理者が設定することもできます。 毎月の請求には、その月に 1 つ以上のガバナンス アクションを持つ各ゲスト ユーザーのレコードが含まれます。 価格の詳細については、Azure価格のページを参照してください。

詳細については、「ゲスト ユーザーの[Microsoft Entra ID ガバナンスライセンス](https://learn.microsoft.com/ja-jp/entra/id-governance/microsoft-entra-id-governance-licensing-for-guest-users)を参照してください。

#### ライセンスの有効期限が切れたら PIM はどうなりますか?

Microsoft Entra ID P2 または Microsoft Entra ID ガバナンス ライセンスの有効期限が切れたり、試用が終了したりすると、Privileged Identity Management機能はディレクトリで使用できなくなります。 次に示す変更は、Microsoft Entra ロールの PIM、Azure リソースの PIM、グループの PIM に適用されます。

- 有効な永続割り当てには影響がありません。
- 期限付きのアクティブな割り当ては、永続的なアクティブ状態へと変更されます。つまり、指定された時間に有効期限が切れることがなくなります。
- ユーザーが今後特権ロールをアクティブ化できなくなるため、候補ロールの割り当ては削除されます。
- Microsoft Entra 管理センターや Azure ポータルの Privileged Identity Management ブレード、API、PowerShell インターフェイスでは、ユーザーはロールのアクティベーションや割り当て管理、特権ロールのアクセスレビューを行うことができなくなります。
- 進行中のMicrosoft Entraロールのアクセス レビューは終了し、Privileged Identity Management（PIM）の構成設定は削除されます。
- Privileged Identity Managementは、ロールの割り当ての変更と PIM アラートに関する電子メールを送信しなくなります。

#### Microsoft Entra ID P2 ライセンスに IGA の機能は追加されますか?

Microsoft Entra ID P2 で現在一般公開されているすべての機能は残りますが、新しい ID ガバナンスと管理 (IGA) の機能は Microsoft Entra ID P2 SKU に追加されません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/lifecycle-workflow-audits"} -->
## ライフサイクル ワークフローの監査 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-audits
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: ライフサイクル ワークフローを含む監査ログに関する情報

ライフサイクル ワークフローを使用して作成されたワークフローを使用すると、組織内の ID ライフサイクルの Joiner-Mover-Leaver (JML) モデルに分類される場所に関係なく、ユーザーのライフサイクル タスクを自動化できます。 ワークフローが正しく処理されるようにすることは、組織のライフサイクル管理プロセスの重要な部分です。 ワークフローが正しく処理されないと、セキュリティとコンプライアンスに関する多くの問題が発生するおそれがあります。 監査ログを使用すると、ライフサイクル ワークフローが最大 30 日以内に完了するすべてのアクションが記録されます。

### 監査ログ

ワークフローが処理されるたびに、イベントがログに記録されます。 これらのイベントは **監査ログ** セクションに格納され、履歴および監査の目的でワークフローに関する情報を取得するために使用できます。 監査ログ サービス、カテゴリ、およびアクティビティは頻繁に変更される可能性があります。

[Image: ワークフロー監査ログのスクリーンショット。]

[ **監査ログ** ] ページには、ライフサイクル ワークフローが実行したすべてのアクションの順次一覧が日付別に表示されます。 この情報から、次のパラメーターに基づいてフィルター処理できます。

| フィルター | 説明 |
| --- | --- |
| 日付 | 監査ログの特定の範囲を 24 時間から 30 日間までフィルター処理できます。 |
| 日付オプション | テナントの現地時刻または UTC でフィルター処理できます。 |
| Service | ライフサイクル ワークフロー サービス。 |
| カテゴリ | ログに記録されるイベントのカテゴリ。 次の内容に分かれています。 **その他**- カスタム タスクに関連するイベント。**TaskManagement** - ライフサイクル ワークフローによってログに記録されるタスク関連のイベント。 **WorkflowManagement** - ワークフロー自体を処理するイベント。 |
| 活動 | カテゴリに基づく特定のアクティビティに基づいてフィルター処理できます。 |

この情報をフィルター処理すると、次のような他の情報をログに表示することもできます。

- **状態**: ログに記録されたイベントが成功したかどうか。
- **状態の理由**: イベントが失敗した場合は、理由が示されます。
- **ターゲット**: ログに記録されたイベントが実行された相手。 Microsoft Entra オブジェクト ID として指定された情報。
- **開始者 (アクター):** ログに記録されるイベントの実行者。 ユーザー名として指定された情報。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/lifecycle-workflow-execution-conditions"} -->
## ライフサイクル ワークフローの実行条件とスケジュール設定 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-execution-conditions
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-08-21
- Summary: ライフサイクル ワークフローの実行条件に関する概念記事。

ライフサイクル ワークフローを使用して作成されたワークフローを使用すると、組織内のユーザーのライフサイクルの結合者、ムーバー、および脱退者モデルのどこに分類されているかに基づいて、ユーザーの一般的なタスクを自動化できます。 これらのワークフローは、特定のユーザーに対して手動で (オンデマンドで) 実行することも、ユーザーがワークフローの定義された実行条件を満たしている場合はスケジュールに基づいて実行することもできます。 これらの実行条件は、トリガーとスコープの 2 つの部分で定義されます。 この記事では、実行条件、ワークフロー トリガーとスコープの違い、およびスケジュールされたワークフローがユーザーに対して実行される条件について説明します。

### ワークフローの実行条件

ユーザーがスケジュールに基づいてワークフローを実行するには、まず実行条件を満たす必要があります。 実行条件は次で構成されます。

- トリガー: ワークフローがユーザーに対して実行される条件を定義します。
- スコープ: ワークフローを実行するユーザーを定義します。

選択するトリガーは、ユーザーに対して実行するワークフローの種類によって異なり、選択したスコープは選択したトリガーに基づいています。 現在、次の 4 種類のトリガーがサポートされています。

[Image: ワークフローの実行条件の [トリガーの詳細] セクションのスクリーンショット。]

- **時間ベースの属性**: ワークフローは、時間値が満たされたときにスケジュールに従ってトリガーされます。
- **属性の変更**: ワークフローは、属性の変更が発生したときにスケジュールに従ってトリガーされます。
- **グループ メンバーシップの変更: ワークフロー**は、グループ メンバーシップの変更が満たされたときにスケジュールに従ってトリガーされます。
- **サインイン アクティビティ**: ユーザーが最後にサインインしてから最小日数が経過すると、ワークフローがスケジュールに従ってトリガーされます。
- **オンデマンドのみ**: ワークフローは手動でのみトリガーされます。

注

**オンデマンドのみの**トリガーは、オンデマンドのみのワークフロー テンプレートの既定のトリガーです。 ワークフロー テンプレートとその互換性のあるトリガーの完全な一覧については、「 [ライフサイクル ワークフローのテンプレートとカテゴリ](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates)」を参照してください。

### 時間ベースの属性トリガー

**時間ベースの属性**トリガーを使用すると、時間値が満たされたタイミングに基づいてトリガーを設定できます。

[Image: 時間ベースのワークフロー トリガーのスクリーンショット。]

トリガーの種類が **時間ベースの属性**であるワークフローを設定する場合は、次の詳細が定義されます。

| トリガーの詳細 | 説明 |
| --- | --- |
| イベントからの日数 | ワークフローがトリガーされたときのイベント ユーザー属性からの日数。 値は 0 ~ 180 です。 |
| イベントのタイミング | ワークフローの *[イベントからの日数* ] の詳細がトリガーされるタイミングを定義します。 たとえば、作業を開始する前にユーザーに対して実行するようにスケジュールされているワークフローでは、イベント タイミング値が **Before** になります。一方、組織を離れた後にユーザーに対して実行するようにスケジュールされたワークフローは、イベント タイミング値として **After** になります。 イベント ユーザー属性と同じ日に実行されるワークフローのテンプレートを選択する場合、値は **[オン] になります**。 |
| イベント ユーザー属性 | ワークフローをトリガーする変更を定義する属性。 使用するワークフローの種類によって、使用可能な属性が決まります。 結合者ワークフローの属性値は "*employeeHireDate*" または "*createdDateTime*" ですが、離職者ワークフローの属性値は "*employeeLeaveDate*" です。 テンプレートとそのイベント ユーザー属性の一覧については、「 [ライフサイクル ワークフローのテンプレートとカテゴリ](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates)」を参照してください。 カスタム属性トリガーを設定することもできます。 詳細については、「 [ライフサイクル ワークフローでカスタム属性トリガーを使用する (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/id-governance/workflow-custom-triggers)を参照してください。 |

注

ユーザーの Microsoft Entra ID 内でイベント ユーザー属性を設定する必要があります。 このプロセスの詳細については、「[ライフサイクル ワークフローの属性を同期する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-sync-attributes)」を参照してください。

#### 相対時間ベースの比較 (プレビュー)

Important

相対時間ベースの比較はパブリック プレビュー段階です。 パブリック プレビュー中、Microsoft Entra 管理センターでは、**時間ベースの属性**と**時間ベースの属性 V2 (プレビュー)** が個別の選択肢として一時的に表示されます。 どちらの選択肢も、時間ベースの属性トリガーを表します。 この機能が一般公開されると、相対的な比較を含む時間ベースのトリガー選択が 1 つだけ表示されます。 プレビューの詳細については、「 [オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)」を参照してください。

プレビュー エクスペリエンスでは、時間ベースの属性トリガーが相対的な比較で拡張されます。 これには、標準の時間ベースのオプションの機能が含まれており、相対期間にわたってユーザーを照合することもできます。 新規または既存のワークフローでこれらの比較を構成するには、[Microsoft Entra 管理センターでライフサイクル ワークフローを](https://aka.ms/LCWRelativeTimeBasedTrigger)開き、[**トリガーの詳細**] で **[時間ベースの属性 V2 (プレビュー)]** を選択します。

次のトリガータイミングの詳細を設定します。

| トリガーの詳細 | 説明 |
| --- | --- |
| Operator | **[完全一致]**、**[次の値の間]**、または **[次の値以下]** を選択します。 |
| イベントからの日数 | 0 ~ 365 日のオフセットを入力します。 [ **間隔**] を選択した場合は、[ **日からイベントまでの**日数] に 0 ~ 365 日のオフセットも入力します。 |
| イベントのタイミング | イベント属性の日付の **前** または **後** を選択します。 属性の日付に対して標準の時間ベースの動作を使用するには、[ **正確]** を選択し、「0 日」と入力して **、[オン]** を選択します。 |
| イベント属性 | `employeeHireDate`、`employeeLeaveDateTime`、`createdDateTime`など、時間ベースのトリガーでサポートされている任意のユーザー属性を選択します。 |

トリガーを構成したら、[スコープの **構成]**、[ **タスクの確認**]、[ **確認と作成**] の順に進みます。 トリガーを評価するには、ワークフローとそのスケジュールの両方を有効にする必要があります。

ワークフローの評価、スケジュール設定、および処理は、 **時間ベースの属性 V2 (プレビュー)** の選択に 3 日間のキャッチアップ 期間がない点を除き、両方のプレビューの選択肢で同じように動作します。 プレビュー中に作成されたワークフローは、再構成なしで統合された一般公開エクスペリエンスに移行します。 Microsoftは、スキーマの変更を事前に通知します。

#### 時間ベースの属性スコープ

時間ベースの属性スコープを使用すると、タイム トリガーが満たされたときにワークフローを実行するユーザーを定義できます。

[Image: 時間ベースの属性トリガーのスコープ画面のスクリーンショット。]

時間ベースの属性トリガーのスコープを設定する場合、次の詳細が定義されます。

| スコープの詳細 | 説明 |
| --- | --- |
| スコープの種類 | ルールに基づく |
| ルール | 時間ベースの属性トリガーの範囲を満たす条件を定義します。 |

注

ルールの評価では、大文字と小文字を区別します。

### 属性変更トリガー

**属性変更**トリガーを使用すると、ユーザーの属性が変更されたタイミングに基づいてトリガーを設定できます。

[Image: ワークフローの属性変更トリガーのスクリーンショット。]

トリガーの種類が **属性の変更**であるワークフローを設定すると、次の詳細が定義されます。

| トリガーの詳細 | 説明 |
| --- | --- |
| トリガー属性 | トリガー属性は、ワークフローの実行をトリガーするために変更される属性を定義します。 カスタム属性トリガーを設定することもできます。 詳細については、「 [ライフサイクル ワークフローでカスタム属性トリガーを使用する (プレビュー)」](https://learn.microsoft.com/ja-jp/entra/id-governance/workflow-custom-triggers)を参照してください。 |
| アクション/演算子 | ワークフローの実行をトリガーする属性の変更を定義します。 |
| 価値 | トリガー属性の値。 |

#### 属性の変更が作用範囲を起動する

属性変更トリガー スコープを使用すると、属性変更トリガーが満たされたときにワークフローを実行するユーザーを定義できます。

属性変更トリガーのスコープを設定すると、次の詳細が定義されます。

| スコープの詳細 | 説明 |
| --- | --- |
| スコープの種類 | ルールに基づく |
| ルール | 属性変更トリガーの範囲を満たす条件を定義します。 |

注

ルールの評価では、大文字と小文字を区別します。

### グループ メンバーシップ変更トリガー

グループ メンバーシップの変更に基づいてトリガーされるワークフローの場合、ユーザーがグループに追加またはグループから削除されると、ワークフローはスケジュールに従って実行されます。

[Image: グループ メンバーシップ変更トリガーのスクリーンショット。]

トリガーの種類が **グループ メンバーシップの変更**であるワークフローを設定する場合は、次の詳細が定義されます。

| トリガーの詳細 | 説明 |
| --- | --- |
| アクション | 実行条件をトリガーするグループ メンバーシップの変更について説明します。 **グループに追加することも、グループ** **から削除することもできます**。 |

#### グループ メンバーシップの変更スコープ

グループ メンバーシップ変更スコープを使用すると、グループ メンバーシップ変更トリガーが満たされたときにワークフローを実行するユーザーを定義できます。

[Image: グループ メンバーシップの変更のスコープの設定のスクリーンショット。]

グループ メンバーシップ変更トリガーのスコープを設定する場合、次の詳細が定義されます。

| トリガーの詳細 | 説明 |
| --- | --- |
| スコープの種類 | グループ ベース。 |
| 選択したグループ | トリガー アクションの基になっているグループを定義します。 |

### サインイン非アクティブ トリガー

**サインイン非アクティブ** トリガーを使用すると、ユーザーが最後にサインインしてから一定の日数が経過した日時に基づいてトリガーを設定できます。

[Image: サインイン非アクティブ トリガーのスクリーンショット。]

サインイン非アクティブ トリガーのスコープを設定する場合、次の詳細が定義されます。

| スコープの詳細 | 説明 |
| --- | --- |
| 非アクティブな日数 | ユーザーが最後にサインインしてからの日数。 |

#### サインインの非アクティブ状態の範囲

サインイン非アクティブ スコープを使用すると、サインイン非アクティブ トリガーが満たされたときにワークフローを実行するユーザーを定義できます。

| スコープの詳細 | 説明 |
| --- | --- |
| スコープの種類 | ルールに基づく |
| ルール | 属性変更トリガーの範囲を満たす条件を定義します。 |

### オンデマンドのみのトリガー

**オンデマンドのみの**トリガーは、手動で選択したユーザーに対してワークフローを実行するように設定されます。 これらのトリガーを含むワークフローは、スケジュールに従って実行されません。 ユーザーは、ワークフローのスコープの詳細セクション内で選択されます。

[Image: 手動でワークフローを実行するためのユーザーの選択のスクリーンショット。]

トリガーの種類が **オンデマンドのみの**ワークフローを設定する場合は、次の詳細が定義されます。

| トリガーの詳細 | 説明 |
| --- | --- |
| スコープの種類 | スコープの種類によって、ワークフローのスコープを実行するように定義する方法が決まります。 この既定値は *ユーザー選択です*。 |
| 選択の種類 | ワークフローの選択の種類は、ワークフローの作成時に、ワークフローが作成されたらすぐに実行するユーザーを選択するか、後でワークフローを実行するユーザーを選択できるように設定できます。 |

ユーザーに対してワークフローをオンデマンドで実行する方法の詳細なガイドについては、「ワークフロー [をオンデマンドで実行する](https://learn.microsoft.com/ja-jp/entra/id-governance/on-demand-workflow)」を参照してください。

### 実行ユーザーのスコープ

有効なワークフローの実行条件が設定されると、現在その実行条件を満たしているユーザーの一覧を表示できます。 このユーザー リストは、ワークフローが次回実行されるときに実行されるユーザーで構成され、ワークフロー エンジンがテナント内のユーザーを最後に評価した時刻に基づいています。

[Image: ワークフローの実行条件のスコープ内のユーザーの一覧のスクリーンショット。]

ワークフローの実行条件が最近変更された場合、実行ユーザー スコープ リストが最新ではない可能性があります。 実行条件が最近変更されると、ワークフロー エンジンによってユーザーが再評価された後、最新の実行条件を満たすユーザーがリストに更新されます。 ユーザーに対してワークフローを実行する前に、ユーザーの一覧が現在の実行条件を満たしていることを確認します。

特定のワークフローの実行ユーザー スコープの表示に関する詳細なガイドについては、「ワークフローの [実行ユーザー スコープを確認](https://learn.microsoft.com/ja-jp/entra/id-governance/check-workflow-execution-scope)する」を参照してください。

### ライフサイクル ワークフローのキャッチアップ ウィンドウ

設計上、ライフサイクル ワークフローには、人事ユーザー データの更新の遅延が原因で見逃された可能性があるユーザーを顧客が処理するのに役立つ 3 日間のキャッチアップ ウィンドウが用意されています。 つまり、ワークフロー エンジンは、スケジュールされたワークフローの現在の実行条件を満たすユーザーを評価するときに、予想されるトリガー日が既に経過しているが、元のトリガー日から 3 日を超えていなかったユーザーを含めます。 ユーザーが処理されると、実行条件を再び満たすことを許可したユーザーまたはワークフローに変更があった場合にのみ、再び考慮されます。

ライフサイクル ワークフローのキャッチアップ ウィンドウの例を次の表に示します。

| ワークフロー シナリオ | ユーザー データ | ライフサイクル ワークフローの動作 |
| --- | --- | --- |
| [EmployeeHireDate](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#onboard-pre-hire-employee) の 7 日前に、事前**採用テンプレート** ワークフローがユーザーに対して実行されるようにスケジュールされています。 | ユーザーは、10 日以内に **EmployeeHireDate** を使用して Microsoft Entra ID で HR によってプロビジョニングされます。 | ワークフローは、オフセットより前の日付として、新しいユーザーに対して実行されます。 |
| [EmployeeHireDate](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#onboard-pre-hire-employee) の 7 日前に、事前**採用テンプレート** ワークフローがユーザーに対して実行されるようにスケジュールされています。 | 新しいユーザーは、5 日間で **EmployeeHireDate** を使用して Microsoft Entra ID で HR によってプロビジョニングされます。 | ワークフローは、日付がオフセットから 3 日以内であるため、新しいユーザーに対して実行されます。 |
| [EmployeeHireDate](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates#onboard-pre-hire-employee) の 7 日前に、事前**採用テンプレート** ワークフローがユーザーに対して実行されるようにスケジュールされています。 | 新しいユーザーは、3 日間で **EmployeeHireDate** を使用して Microsoft Entra ID で HR によってプロビジョニングされます。 | 日付がオフセットから 3 日を超えるため、ワークフローは新しいユーザーに対して実行 **されません** 。 |
| 2月23日に、**EmployeeHireDate**が2月23日となっているスコープユーザーに対して、新規採用のワークフローが実行されます。 | 新しいユーザーは、2 月 24 日に Microsoft Entra ID で HR によってプロビジョニングされ、EmployeeHireDate は 2 月 23 日に設定されます。 | ワークフローは、新しいユーザーに対して 2 月 24 日に再び実行されます。日付はオフセットから 3 日以内であるためです。 |

### ワークフローのスケジュール設定

新規作成されたワークフローは既定で有効になりますが、スケジュールは手動で有効にする必要があるオプションです。 ワークフローがスケジュールされているかどうかを確認するには、ワークフローの概要ページで **[スケジュール済み]** 列を表示できます。

スケジュールが有効になると、ワークフローは 3 時間ごと (既定) または **ワークフロー設定**で選択した間隔で評価され、実行する必要があるかどうかを判断します。

注

ユーザーが実行条件を満たし、ワークフローのスコープ内に入ると、ライフサイクル ワークフロー エンジンは、ワークフローがユーザーの処理を開始する前に、もう一度ユーザーを評価します。 ユーザーがワークフローの実行条件を満たさなくなった場合、ユーザーは処理されません。

ワークフローの実行条件の設定に関する詳細なガイドについては、「 [ライフサイクル ワークフローの作成」を](https://learn.microsoft.com/ja-jp/entra/id-governance/create-lifecycle-workflow)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/lifecycle-workflow-execution-limits"} -->
## ライフサイクル ワークフローの実行制限を構成する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-execution-limits
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-06-23
- Summary: テナント全体およびワークフロー固有の実行制限を設定し、ライフサイクル ワークフローで検疫されたワークフローを管理して大規模な影響を防ぐ方法について説明します。

ライフサイクル ワークフローを使用すると、組み込みのガードレールを使用して、自信を持ってワークフローを実行できます。 テナント全体またはワークフローごとの実行制限を設定でき、制限に達した後に実行を再開するには管理者の承認が必要です。 これらの制限により、構成ミスによる大規模な影響から組織が保護されます。

スケジュールされた実行が構成された制限を超えると、ライフサイクル ワークフローはワークフローを検疫に配置し、管理者に通知します。 検疫されたワークフローは、管理者が実行を承認するまで再実行されません。

この記事では、テナント全体の実行制限の設定、ワークフロー固有の実行制限の設定、Microsoft Entra 管理センターの検疫済みワークフローの確認とクリアを行う方法について説明します。

実行制限は、スケジュールされたワークフロー実行にのみ適用されます。 制限は、スケジュールされた時刻にワークフローが実行される前に自動的にチェックされます。 オンデマンド実行は、実行制限の対象になりません。

### 前提条件

この機能を使用するには、Microsoft Entra ID ガバナンス または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、[Microsoft Entra ID ガバナンス ライセンスの基礎](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)を参照してください。

### テナント全体の実行制限を設定する

テナント全体の制限は、独自のワークフロー固有の制限がないすべてのワークフローに適用されます。 テナント全体の制限を、ユーザー人口の割合、固定数のユーザー、またはその両方として設定します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフ サイクル ワークフロー**&gt;**Workflow 設定**に移動します。
3. [ **実行しきい値を有効にする] を選択します**。

    [Image: このページには、「テナント実行しきい値」セクションと、「実行しきい値を有効にする」トグルボタンが表示された、ライフサイクル ワークフローのワークフロー設定ページのスクリーンショット。]
4. 次の制限オプションのいずれかまたは両方を選択します。

    - **ユーザー全体に対する割合に制限**: 許可するユーザーの割合を入力します。
    - **特定のユーザー数に制限**する: 許可するユーザーの固定数を入力します。
5. **保存**を選びます。

両方の制限オプションを設定すると、ライフサイクル ワークフローは OR ロジックを使用してそれらを評価します。 いずれかの制限を超えた場合、ワークフローは検疫されます。

### ワークフロー固有の実行制限を設定する

ワークフロー固有の制限は、1 つのワークフローに適用され、そのワークフローのテナント全体の制限をオーバーライドします。 そのワークフローには、ワークフロー固有の制限のみが適用されます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフ サイクル ワークフロー**&gt;**Workflows** に移動し、更新するワークフローを選択します。
3. ワークフロー ページで、[ **設定]** を選択します。
4. [ **実行しきい値を有効にする] を選択します**。

    [Image: [ワークフロー実行のしきい値] セクションと、ユーザーの割合と数の制限オプションを含むワークフロー設定ページのスクリーンショット。]
5. 次の制限オプションのいずれかまたは両方を選択します。

    - **ユーザー全体に占める割合を制限**: 許可するユーザーの割合を入力します。
    - **特定のユーザー数に制限**する: 許可するユーザーの固定数を入力します。
6. **保存**を選びます。

Note

ワークフローの実行制限を更新すると、新しいワークフロー バージョンが作成されます。 新しいバージョンはMicrosoft Entra 管理センターに表示されますが、現在 API には反映されていません。

### 検疫済みワークフローの表示とクリア

ワークフローが実行制限を超えると、ライフサイクル ワークフローはワークフローを検疫し、ライフサイクル ワークフロー管理者に電子メール通知を自動的に送信します。 これらの通知を受信するように電子メール タスクを構成する必要はありません。 検疫されたワークフローを確認し、もう一度実行する準備ができたら、その実行を承認します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. 検疫されたワークフローを表示するには、次のいずれかの操作を行います。

    - **ID Governance**&gt;**ライフサイクル ワークフロー**&gt;**隔離されたワークフロー** に移動します。
    - [ **ライフサイクル ワークフロー** **の概要** ] ページの [ **アラート** ] セクションで、[ **検疫されたワークフローの表示**] を選択します。

    [Image: [ライフサイクル ワークフローの概要] ページの [アラート] セクションのスクリーンショット。[検疫中の数] と [検疫されたワークフローの表示] リンクが表示されています。]
3. 検疫されたワークフローの一覧から 1 つ以上のワークフローを選択します。

    [Image: 検疫済みワークフローの一覧の [検疫済みワークフロー] ページのスクリーンショット。しきい値、理由、検疫日を超えています。]
4. [ **実行の承認] を選択します**。

Note

ワークフローが不要になった場合は、実行を承認する代わりに、テナントからワークフローを削除できます。

実行を承認しても、即時実行はトリガーされません。 ワークフローは、既存のスケジュールに従います。

- 次回のスケジュールされた実行時間の前に実行を承認しても、ワークフローがその実行条件を満たしている場合は、スケジュールされた時刻に実行されます。
- スケジュールされた実行時間の後に実行を承認すると、次にスケジュールされた実行でワークフローが評価されます。

また、実行制限をバイパスする検疫済みワークフローをオンデマンドで実行することもできます。 ワークフロー履歴から、ワークフローが隔離されたままの状態で、特定の実行分のすべてのユーザーを再処理できます。 個々のユーザーの再処理はサポートされていません。

### よく寄せられる質問

**実行制限はオンデマンド ワークフローの実行に適用されますか?**

No. 実行制限は、スケジュールされたワークフロー実行にのみ適用されます。 制限は、スケジュールされた時刻にワークフローが実行される前に自動的にチェックされます。

**パーセンテージ制限とユーザー数制限の両方を設定するとどうなりますか?**

ライフサイクル ワークフローは、OR ロジックを使用して制限を評価します。 いずれかの制限を超えた場合、ワークフローは検疫されます。

**テナント全体の制限とワークフロー固有の制限の両方を設定するとどうなりますか?**

ワークフロー固有の制限は、そのワークフローのテナント全体の制限をオーバーライドします。 ワークフロー固有の制限のみが適用されます。

**検疫は承認なしで自動的にクリアできますか?**

No. 検疫をクリアするには承認が必要です。 検疫されたワークフローは、クリアされるまで実行されません。

**ワークフローを検疫に手動で配置できますか?**

No. 検疫は自動であり、実行制限を超えた場合にのみトリガーされます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/lifecycle-workflow-extensibility"} -->
## ワークフローの拡張性 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-extensibility
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: ライフサイクル ワークフローを使用したワークフローの拡張性について説明する概念記事

ライフサイクル ワークフローを使うと、就職者、異動者、または退職者のシナリオに基づいてトリガーできるワークフローを作成できます。 ライフサイクル ワークフローには、ユーザーのライフサイクル全体の一般的なシナリオを自動化する組み込みタスクがいくつか用意されていますが、最終的にはこれらの組み込みタスクの制限に達する可能性があります。 機能拡張機能を使用すると、カスタム タスク拡張機能の概念を利用して、ワークフローの一部として外部システムを呼び出すことができるようになります。 たとえば、ユーザーが組織に参加するときに、Teams 番号を割り当てるカスタム タスク拡張機能を含むワークフローを作成したり、ユーザーが退職したときにマネージャーの電子メール アカウントへのアクセスを許可する別のワークフローを作成したりできます。 機能拡張機能により、ライフサイクル ワークフローでは現在、 [Azure Logic Apps](https://learn.microsoft.com/ja-jp/azure/logic-apps/logic-apps-overview) を呼び出すカスタム タスク拡張機能の作成がサポートされています。

### Logic Apps の前提条件

Azure Logic App とカスタム タスク拡張機能をリンクするには、次の前提条件を満たす必要があります。

- Azure サブスクリプション
- リソース グループ
- 新しい従量課金ベースのロジック アプリを作成するためのアクセス許可、または既存の従量課金ベースのロジック アプリへのアクセス許可

ロジック アプリ自体またはリソース グループ、サブスクリプション、管理グループなどの上位のスコープで、次のいずれかの Azure ロールの割り当てが必要です。

- **ロジック アプリの共同作成者**
- **投稿者**
- **所有者**

注

**ロジック アプリ オペレーター** ロールでは不十分です。

### カスタム タスク拡張機能の展開シナリオ

カスタム タスク拡張機能を作成する場合、ライフサイクル ワークフローと対話する方法のシナリオは、次の 2 つの方法のいずれかになります。

[Image: カスタム タスク展開シナリオのスクリーンショット。]

- **起動して続行** する - Azure ロジック アプリが開始され、次のタスクの実行はすぐに続行され、Azure Logic App からの応答は期待されません。 このシナリオは、ライフサイクル ワークフローが Azure Logic App からのフィードバック (状態を含む) を必要としない場合に最適です。 ロジック アプリが正常に開始された場合、ライフサイクル ワークフロー タスクは成功と見なされます。
- **起動と待機** - Azure ロジック アプリが開始され、次のタスクの実行はロジック アプリからの応答を待機します。 カスタム タスク拡張機能が Azure Logic App からの応答を待機する期間を入力します。 定義された期間期間内に応答が受信されない場合、タスクは失敗したと見なされます。 [Image: カスタム タスクの起動と待機タスクの選択のスクリーンショット。]

注

応答は必ずしもロジック アプリによって提供される必要はありません。ロジック アプリが仲介者としてのみ機能する場合は、サード パーティシステムが応答できます。 詳細については、「 [taskProcessingResult: resume」を](https://learn.microsoft.com/ja-jp/graph/api/identitygovernance-taskprocessingresult-resume)参照してください。

### 応答の承認

ロジック アプリからの応答を待機するカスタム タスク拡張機能を作成すると、応答を送信できるアプリケーションを定義できます。

[Image: カスタム タスク拡張機能の起動と待機オプションのスクリーンショット。]

応答は、次のいずれかの方法で承認できます。

- **システム割り当てマネージド ID (既定)** - この選択により、Logic Apps のシステム割り当てマネージド ID を有効にして利用できます。 詳細については、「Azure [Logic Apps でマネージド ID を使用して Azure リソースへのアクセスを認証する」を](https://learn.microsoft.com/ja-jp/azure/logic-apps/create-managed-service-identity)参照してください。
- **承認なし** - この選択では承認は付与されません。アプリケーションのアクセス許可 (LifecycleWorkflows.ReadWrite.All) またはロールの割り当て (ライフサイクル ワークフロー管理者) を個別に割り当てる必要があります。 アプリケーションが応答している場合、最小特権の原則に従っていないため、このオプションは推奨されません。 このオプションは、応答がユーザーに代わってのみ提供される場合にも使用できます (LifecycleWorkflows.ReadWrite.All の委任されたアクセス許可とライフサイクル ワークフロー管理者ロールの割り当て)。
- **既存のアプリケーション** - この選択により、応答する既存のアプリケーションを選択できます。 通常のアプリケーションと、システムまたはユーザー割り当てマネージド ID を指定できます。 マネージド ID の種類の詳細については、「 [マネージド ID の種類」を](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)参照してください。

### カスタム タスク拡張機能と Azure Logic Apps の高度な手順の統合

Azure Logic Apps 統合の手順の概要は次のとおりです。

注

Microsoft Entra 管理センターを使用してカスタム タスク拡張機能とロジック アプリを作成すると、これらの手順の大部分が自動化されます。 この方法でカスタム タスク拡張機能を作成する方法のガイドについては、「 [カスタム タスク拡張機能に基づいて Logic Apps をトリガー](https://learn.microsoft.com/ja-jp/entra/id-governance/trigger-custom-task)する」を参照してください。

- **従量課金ベースの Azure ロジック アプリを作成**する: カスタム タスク拡張機能から呼び出される従量課金ベースの Azure ロジック アプリ。
- **ライフサイクル ワークフローと互換性のあるように Azure Logic App を構成**する: カスタム タスク拡張機能で使用できるように従量課金ベースの Azure ロジック アプリを構成します。 詳細については、「[ライフサイクル ワークフローを使用するためのロジック アプリの構成](https://learn.microsoft.com/ja-jp/entra/id-governance/configure-logic-app-lifecycle-workflows)」を参照してください。
- **Azure ロジック アプリ内でカスタム ビジネス ロジックを構築する: ロジック アプリ** デザイナーを使用して、Azure Logic App 内でビジネス ロジックを設定します。
- **Azure Logic App に関する必要な情報を保持するライフサイクル ワークフロー customTaskExtension を作成**します。構成された Azure Logic App を参照するカスタム タスク拡張機能を作成します。
- **"カスタム タスク拡張機能の実行" タスクを使用してライフサイクル ワークフローを更新または作成し、作成した customTaskExtension を参照**します。新しく作成したカスタム タスク拡張機能を新しいワークフローに追加するか、既存のワークフローに情報を更新します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/lifecycle-workflow-history"} -->
## ライフサイクル ワークフローの履歴 - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-history
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: ライフサイクル ワークフローのレポートおよび履歴の機能の概念に関する記事

ライフサイクル ワークフローを使用して作成されたワークフローを使用すると、組織内の ID ライフサイクルの Joiner-Mover-Leaver (JML) モデルに分類される場所に関係なく、ユーザーのライフサイクル タスクを自動化できます。 ワークフローが正しく処理されるようにすることは、組織のライフサイクル管理プロセスの重要な部分です。 ワークフローが正しく処理されないと、セキュリティとコンプライアンスに関する多くの問題が発生するおそれがあります。 ライフサイクル ワークフローの履歴機能を使用すると、ユーザー、実行、またはタスクの概要に基づいて、履歴を表示するワークフロー イベントを指定できます。 このレポート機能を使用すると、どのユーザーに対して何が実行されたか、そしてそれが正常に成功したかどうかをすばやく確認できます。 これらの特定の領域の概要と共に、記録された各特定イベントに関する詳細情報をそれぞれのセクションで表示することもできます。 また、[これらのレポートを CSV ファイルとしてダウンロードする](https://learn.microsoft.com/ja-jp/entra/id-governance/download-workflow-history)こともできます。 この記事では、組織内のユーザーにワークフローがどのように利用されたかについての詳細を取得する際、どのようなときにこれらの各機能を使うかについて説明します。 テナント全体の集約ワークフローについては、[ライフサイクル ワークフロー分析情報](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-insights)に関する記事をご覧ください。 ライフサイクル ワークフローで実行するすべてのアクションの詳細については、「[ライフサイクル ワークフローの監査](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-audits)」を参照してください。

### ライフサイクル ワークフローの履歴の概要

ライフサイクル ワークフローでは、概要と詳細に基づく履歴機能が導入されています。 これらの履歴の概要を使用すると、ワークフローが実行されたユーザーと、この実行が成功したかどうかに関する情報をすばやく取得できます。 これが重要なのは、監査ログによって提供される大量の情報セットが多くなりすぎて、効率的に使用できなくなる可能性があるためです。 この大量に処理される情報セットを読みやすくするため、ライフサイクル ワークフローには手軽に利用できる概要が用意されています。 これらの履歴の概要は、3 つの方法で表示できます。

- **ユーザーの概要**: ワークフローによって処理されたユーザーの概要を表示します。 特定のユーザーごとの成功、失敗、および合計実行情報が表示されます。
- **実行の概要**: ワークフローの実行の概要をワークフローの観点から表示します。 ワークフローの実行時に、成功した、失敗した、完全に実行されたタスクの情報が記録されます。
- **タスクの概要**: 成功、失敗、実行されたタスクの合計数など、ワークフローによって処理されたタスクの概要が表示されます。

概要を利用すると、ログの詳細に進まずに、ワークフローがそれ自体またはユーザーに関してどのように実行されたかに関する詳細をすばやく取得できます。 この情報を取得するための詳細なガイドについては、「 [ワークフローの状態を確認する」を](https://learn.microsoft.com/ja-jp/entra/id-governance/check-status-workflow)参照してください。

### ユーザーの概要情報

ユーザーの概要を利用すると、処理されたユーザーの観点でワークフローの情報を表示できます。

[Image: ワークフローのユーザーの概要のスクリーンショット。]

ユーザーの概要内では、次の情報を見つけることができます。

| パラメーター | 説明 |
| --- | --- |
| Total Processed (処理された合計) | 選択した期間中にワークフローによって処理されたユーザーの合計数。 |
| 成功 | 選択した期間中にワークフローによって処理された、成功したユーザーの合計数。 |
| 失敗 | 選択した期間中にワークフローによって処理された、失敗したユーザーの合計数。 |
| 合計タスク数 | 選択した期間中にワークフローでユーザーに対して処理されたタスクの合計数。 |
| 失敗したタスク | 選択した期間中にワークフローでユーザーに対して処理された、失敗したタスクの合計数。 |

#### ユーザー履歴の詳細

ユーザーの詳細な履歴情報を使用すると、以下に基づいて特定の情報をフィルター処理できます。

- **日付**: ワークフローが実行されたときの特定の範囲 (最短で 24 時間から最長で 30 日間まで) をフィルター処理できます。
- **状態**: 処理されたユーザーの特定の状態をフィルター処理できます。 サポートされている状態は、**[完了]**、**[進行中]**、**[キューに挿入済み]**、**[キャンセル済み]**、**[完了済み (エラーあり)]**、**[失敗]** です。
- **ワークフローの実行タイプ**: **[スケジュール設定]** や **[要求時]** など、ワークフローの実行の種類でフィルター処理できます
- **完了日**: ユーザーがワークフローで処理されたときの特定の範囲 (最短 24 時間から最長で 30 日まで)をフィルター処理できます。

#### ユーザー履歴の状態の詳細

ユーザー処理履歴の状態を表示する場合、状態の値は次の情報に対応します。

| ステータス | 詳細 |
| --- | --- |
| 完了 | この状態は、ワークフローのすべてのタスクがユーザーに対して正常に処理された場合に報告されます。 |
| 進行中 | この状態は、ワークフローがユーザーのタスクの実行を開始したときに報告されます。 ワークフローのすべてのタスクがユーザー用に処理されるか、失敗するまで、ステータスはこの状態を維持します。 |
| キュー登録済み | この状態は、ワークフローの実行条件を満たすライフサイクル ワークフロー エンジンによってユーザーが識別されたときに報告されます。 ここから、ユーザーは、ワークフローがユーザーのために実行を開始した場合、*進行中*の状態に入ります。管理者がワークフローを手動でキャンセルした場合は、キャンセルの状態になります。 |
| 取り消し済み | この状態は、次の理由で報告されます。**1.**ワークフローが削除された場合、実行するように設定されているすべてのスケジュールされたユーザーが取り消されます。**2.**ワークフローが無効になっている場合、実行するように設定されているすべてのスケジュールされたユーザーが取り消されます。**3**.ワークフローのスケジュールが無効になっている場合、実行するように設定されているすべてのスケジュールされたユーザーが取り消されます。**4.**ワークフローに新しいバージョンが作成され、すべてのタスクが無効になっている場合、実行するように設定されているすべてのスケジュールされたユーザーが取り消されます。**5.**ユーザーがワークフローの新しいバージョンの現在の実行条件を満たしていない場合、スケジュールされた実行が取り消されます。**6.**ユーザーがワークフローを実行するためにキューに登録されていたが、実行直前にプロファイルが変更され、ワークフローの現在の実行条件を満たさなくなった場合、処理は取り消されます。 |
| 完了 (エラーあり) | この状態は、ワークフローは完了したが、**ContinueOnError** が *true* に設定されている 1 つ以上のタスクが失敗した場合に報告されます。 |
| 失敗 | この状態は、**continueOnError** が *false* に設定されているタスクが失敗した場合に報告されます。 |

処理されたユーザーの概要情報の取得に関する詳細なガイドについては、「[Microsoft Entra 管理センターを使用したユーザー ワークフローの履歴](https://learn.microsoft.com/ja-jp/entra/id-governance/check-status-workflow#user-workflow-history-using-the-microsoft-entra-admin-center)」を参照してください。

### 実行の概要

実行の概要を利用すると、その実行履歴の観点でワークフローの情報を表示できます。

[Image: ワークフローの実行の概要のスクリーンショット。]

実行の概要内では、次の情報を見つけることができます。

| パラメーター | 説明 |
| --- | --- |
| Total Processed (処理された合計) | 実行されたワークフローの合計数。 |
| 成功 | 正常に実行されたワークフロー。 |
| 失敗 | 実行に失敗したワークフロー。 |
| 失敗したタスク | タスクが失敗した状態で実行されたワークフロー。 |

#### 実行履歴の詳細

実行履歴の詳細情報を使用すると、特定の情報を以下の基準でフィルターできます。

- **日付**: ワークフローが実行されたときの特定の範囲 (最短で 24 時間から最長で 30 日間まで) をフィルター処理できます。
- **状態**: 実行されたワークフローの特定の状態をフィルター処理できます。 サポートされている状態は、**[完了]**、**[進行中]**、**[キューに挿入済み]**、**[キャンセル済み]**、**[完了済み (エラーあり)]**、**[失敗]** です。
- **ワークフローの実行タイプ**: **[スケジュール設定]** や **[要求時]** など、ワークフローの実行の種類でフィルター処理できます。
- **完了日**: ワークフローが実行されたときの特定の範囲 (最短で 24 時間から最長で 30 日間まで) をフィルター処理できます。

#### 実行履歴の状態の詳細

実行履歴の状態を表示する場合、状態の値は次の情報に対応します。

| ステータス | 詳細 |
| --- | --- |
| キュー登録済み | この状態は、ワークフローが初めて実行するように設定されたときに報告されます。 |
| 進行中 | この状態は、ワークフローが最初のタスクの処理を開始するとすぐに報告されます。 |
| 取り消し済み | この状態は、ある時点で "進行中" だったが、現在はその状態で凍結されている場合に報告されます。 |
| 完了 (エラーあり) | この状態は、ワークフローが一部に対して正常に実行されたが、その他に対しては正常に実行されなかった場合に報告されます。 ワークフローがキューに入った状態に入っても、そのインスタンスがすべて実行前に取り消された場合は、 *進行中の状態*に入る前にこの状態も表示されます。 |
| 完了 | この状態は、ワークフローがすべてのユーザーに対して正常に実行された場合に報告されます。 |
| 失敗 | この状態は、ワークフローの実行対象であるすべてのユーザーに対してすべてのタスクが失敗した場合に報告されます。 レポートでは、取り消されたユーザーはエラーとしてカウントされません。 |

実行に関する情報を取得する方法の詳細なガイドについては、「[Microsoft Entra 管理センターを使用したワークフロー実行履歴](https://learn.microsoft.com/ja-jp/entra/id-governance/check-status-workflow#run-workflow-history-using-the-microsoft-entra-admin-center)」を参照してください。

### タスクの概要

タスクの概要を利用すると、そのタスクの観点でワークフローの情報を表示できます。

[Image: ワークフロー タスクの概要のスクリーンショット。]

タスクの概要内では、次の情報を見つけることができます。

| パラメーター | 説明 |
| --- | --- |
| Total Processed (処理された合計) | ワークフローによって処理されたタスクの合計数。 |
| 成功 | ワークフローによって正常に処理されたタスクの数。 |
| 失敗 | ワークフローによって処理され、失敗したタスクの数。 |
| 未処理 | ワークフローによって処理されなかったタスクの数。 |

#### タスク履歴の詳細

タスクの詳細な履歴情報を使用すると、以下に基づいて特定の情報をフィルター処理できます。

- **日付**: ワークフローが実行されたときの特定の範囲 (最短で 24 時間から最長で 30 日間まで) をフィルター処理できます。
- **状態**: 実行されたワークフローの特定の状態をフィルター処理できます。 サポートされている状態は、**[完了]**、**[進行中]**、**[キューに挿入済み]**、**[キャンセル済み]**、**[完了済み (エラーあり)]**、**[失敗]** です。
- **完了日**: ワークフローが実行されたときの特定の範囲 (最短で 24 時間から最長で 30 日間まで) をフィルター処理できます。
- **タスク**: 特定のタスク名に基づいてフィルター処理できます。

#### タスク履歴の状態の詳細

タスク履歴の状態を表示する場合、状態の値は次の情報に対応します。

| ステータス | 詳細 |
| --- | --- |
| キュー登録済み | この状態は、ワークフロー インスタンスの実行がスケジュールされると報告されます。ワークフロー内のすべてのタスクのタスク レポートも、この状態で実行レコードと共に作成されます。 各タスク レポートにはすべてのユーザーが含まれますが、特定のタスクを表します。 |
| 進行中 | この状態は、最初のタスクの処理が開始されるとすぐに報告されます。 |
| 取り消し済み | この状態は、ワークフローが取り消される前に処理されたタスクがない場合に報告されます。 タスクを含むワークフローが削除されると、状態も取り消し済みと表示されます。 |
| 完了 (エラーあり) | この状態は、ユーザーに対してタスクが処理されたが、必ずしもすべてのタスクが成功していない場合に報告されます。 |
| 完了 | この状態は、すべてのユーザーに対してすべてのタスクが正常に実行された場合に報告されます。 |
| 失敗 | この状態は、すべてのタスクが失敗した場合に報告されます。 |

ワークフローの処理とタスクを分離することが重要です。1 つのワークフロー内で、ユーザーの処理時に特定のタスクが成功し、他のものが失敗する場合があるためです。 ワークフローで失敗したタスクの後にタスクを実行するかどうかは、[エラー時に続行する] を有効にするといったパラメーターと、ワークフロー内でのそれらの配置によって決まります。 詳細については、[タスクの共通パラメーター](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#common-task-parameters)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/id-governance/lifecycle-workflow-inactive-users"} -->
## ライフサイクル ワークフローを使用して非アクティブなユーザーを管理する - Microsoft Entra ID Governance

- Source: https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-inactive-users
- Service: entra-id-governance / lifecycle-workflows
- Article date: 2026-03-12
- Summary: この記事では、ライフサイクル ワークフローを使用して非アクティブなユーザーを管理する手順について説明します。

ライフサイクル ワークフローでは、組織内のライフサイクルの Joiner-Mover-Leaver (JML) モデルのどこにあってもユーザーをサポートする一環として、ユーザーが一定の期間非アクティブになると、ユーザーの無効化と削除の自動化がサポートされます。 この [サインイン非アクティブ](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-execution-conditions#sign-in-inactivity-trigger) では、ユーザーが一定の日数非アクティブな場合に実行するワークフローを設定できます。 この機能を使用すると、組織に設定した条件に基づいて非アクティブなユーザーの削除を自動化することで、セキュリティで保護された環境をシームレスに維持できます。

### [前提条件]

この機能を使用するには、Microsoft Entra ID ガバナンスまたは Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、 [Microsoft Entra ID ガバナンス のライセンスの基礎を](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)参照してください。

### Microsoft Entra 管理センターを使用して非アクティブなユーザーを管理する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)としてサインインします。
2. **ID ガバナンス**&gt;**ライフサイクル ワークフロー**&gt;**のワークフローを参照してください**。
3. ワークフロー画面で、非アクティブなユーザー タスクを追加する特定のワークフローを選択するか、テンプレートに基づいて新しいワークフローを作成します。

    注

    いずれかの leaver タスクを使用するには、leaver ワークフロー テンプレートを選択する必要があります。
4. [ **基本** ] タブで、ワークフローの一意の表示名と説明を入力した後、 **サインイン非アクティブ** トリガーを選択します。
5. 目的のワークフロー テンプレートを選択したら、基本的な詳細を入力し、[ **サインイン非アクティブ]** トリガーを選択します。
6. [ **非アクティブな日数] で**、超過した場合にトリガーを実行する日数を入力し、[ **次へ**] を選択します。 [Image: 非アクティブな日数のスクリーンショット。]

    注

    サインイン非アクティブは、 `lastSuccessfulSignInDateTime` 属性によって決まります。
7. [ **スコープ** ] ページで、トリガーに必要なスコープを入力し、[ **次へ**] を選択します。
8. [ **タスクの確認]** ページで、非アクティブと見なすユーザーに対して実行するタスクを選択し、[ **確認と作成**] を選択します。

    注

    ライフサイクル ワークフローには、非アクティブなユーザーの管理に直接関連する、 [ユーザーの非アクティブに関するメールの送信](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-tasks#send-email-about-user-inactivity)という組み込みのタスクが付属しています。
<!-- /MSL-PAGE -->
