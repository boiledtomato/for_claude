# Microsoft Learn — Microsoft Entra / 開発者向け (Identity Platform・MSAL・Microsoft.Identity.Web) (part 1)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 66

---

<!-- MSL-PAGE {"url":"entra/agent-id"} -->
## Microsoft Entra エージェント ID のドキュメント

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id
- Service: entra-id / agent-id
- Article date: 2026-06-19
- Summary: Microsoft Entra エージェント ID を使用してエージェント ID を構築、セキュリティ保護、管理します。 AI エージェントをエンタープライズ ワークフローと統合し、ゼロ トラスト原則を適用し、大規模なエージェント アクセスを管理します。

エンタープライズ レベルのアクセス管理、保護、ガバナンスを使用して AI エージェントのアクセスをセキュリティで保護します。 AI エージェントをエンタープライズ ワークフローと統合し、ゼロ トラスト原則を適用し、Microsoft Entraの ID とネットワーク アクセス機能を使用して大規模なエージェント アクセスを管理します。

概要
[Microsoft Entra エージェント IDとは](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id)

Architecture
[エージェント ID アーキテクチャを計画する](https://learn.microsoft.com/ja-jp/entra/agent-id/how-to-plan-agent-identity-architecture)

概念
[AI のセキュリティの概要](https://learn.microsoft.com/ja-jp/entra/agent-id/security-for-ai-overview)

新機能
[エージェント ID の新機能](https://learn.microsoft.com/ja-jp/entra/agent-id/whats-new-agent-id)

### 管理、統制、保護

Microsoft Entra エージェント IDは、組織全体の AI エージェント ID を管理、管理、保護するのに役立ちます。

#### エージェント ID の管理

- [エージェント ブループリントを作成する](https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint)
- [エージェント ID の作成](https://learn.microsoft.com/ja-jp/entra/agent-id/create-delete-agent-identities)
- [所有者とスポンサーを管理する](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-owners-sponsors-agents)

#### エージェントのライフサイクルを管理する

- [エージェントの ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-id-governance-overview)
- [エージェントのアクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-access-packages)
- [エージェントのスポンサーとライフサイクルを維持する](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-sponsor-tasks)

#### リソースへのエージェント アクセスを保護する

- [エージェントの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/agent-id)
- [エージェントの ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-agents)
- [エージェントのネットワーク制御](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-secure-web-ai-gateway-agents)

#### 主な概念

- [エージェントのアイデンティティ](https://learn.microsoft.com/ja-jp/entra/agent-id/what-are-agent-identities)
- [エージェント ID ブループリント](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-blueprint)
- [エージェントのユーザー アカウント](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-users)
- [エージェント サービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-service-principals)

#### 認証と承認

- [エージェント ID での承認](https://learn.microsoft.com/ja-jp/entra/agent-id/authorization-agent-id)
- [継承可能なアクセス許可](https://learn.microsoft.com/ja-jp/entra/agent-id/concept-inheritable-permissions)
- [エージェントの認証プロトコル](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-oauth-protocols)

#### 計画と意思決定

- [エージェント ID アーキテクチャを計画する](https://learn.microsoft.com/ja-jp/entra/agent-id/how-to-plan-agent-identity-architecture)
- [エージェント作成チャネル](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-id-creation-channels)
- [エージェント ID のベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/agent-id/best-practices-agent-id)

### Microsoft Entra エージェント ID プラットフォーム上に構築する

エージェントの認証と承認を SDK、OAuth フロー、および開発者ツールと統合します。

[エージェント用 Microsoft Entra SDK](https://learn.microsoft.com/ja-jp/entra/agent-id/microsoft-entra-sdk-for-agent-identities)
エージェント認証を使い慣れた SDK や開発者ツールと統合します。

[AIガイド付きセットアップ](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-id-ai-guided-setup)
AI コーディング エージェントを使用して、エージェント ID のオンボード ワークフロー全体を自動化します。

[エージェントの OAuth プロトコル](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-oauth-protocols)
AI ワークロード用に最適化された OAuth 2.0 フローを使用してエージェントが認証する方法について説明します。

[サード パーティのエージェントを構成する](https://learn.microsoft.com/ja-jp/entra/agent-id/configure-third-party-agents)
AWS Bedrock や n8n などのプラットフォームのエージェントをエージェント ID と統合します。

[Microsoft Graph API を呼び出す](https://learn.microsoft.com/ja-jp/entra/agent-id/call-api-microsoft-graph)
エージェントからMicrosoft 365データとサービスにアクセスします。

### Microsoft エージェント 365

大規模な AI エージェントを管理および管理するためのエンタープライズ コントロール プレーン。 Microsoft Entraの ID 基盤上に構築されています。

[エージェント 365 for enterprise の理由](https://learn.microsoft.com/ja-jp/microsoft-agent-365/leadership/why-agent-365-for-enterprise)
Microsoft Agent 365 が企業の大規模な AI エージェントの管理と管理にどのように役立つかについて説明します。

[Microsoft Entraおよびエージェント 365](https://learn.microsoft.com/ja-jp/microsoft-agent-365/leadership/entra-agent-365)
Microsoft Entra エージェント IDがエージェント 365 の ID 基盤を提供する方法について説明します。

[エージェント 365 を使用して AI エージェントをセキュリティで保護する](https://learn.microsoft.com/ja-jp/security/security-for-ai/agent-365-security?context=%2Fmicrosoft-agent-365%2Fcontext&view=o365-worldwide)
エージェント 365 とエージェント ID が連携して AI エージェントをセキュリティで保護する方法について説明します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/agent-access-packages"} -->
## Microsoft Entraのエージェント ID のパッケージにアクセスする - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/agent-access-packages
- Service: entra-id / agent-id
- Article date: 2026-05-01
- Summary: この記事では、アクセス パッケージがリソースへのエージェント ID アクセスのガバナンスを提供する方法について説明します。

Microsoft Entra特権管理は、ガバナンスの手段としてアクセスパッケージを提供します。 アクセス パッケージでは、エージェントのアクセス割り当てが意図的、監査可能、および期限付きであることを確認します。 アクセス パッケージは、エージェント ID のアクセス許可を管理するための構造化されたアプローチを表します。適切なガバナンス制御がない可能性があるアドホックアクセス許可の割り当てとは対照的です。 アクセス パッケージを使用すると、同じアクセス ニーズを持つ多くの AI エージェント (たとえば、顧客サポート AI エージェントの群) に対して標準化されたアクセスが可能になります。 アクセス パッケージを通じて、組織は、エージェント ID、エージェントのユーザー アカウント、リソースへのサービス プリンシパル アクセスに関する一貫したガバナンス プラクティスを確立できます。 詳細については、「 [エージェント ID の管理」を](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-id-governance-overview)参照してください。

### 前提条件

アクセス パッケージを作成する前に、組織で次の前提条件が満たされていることを確認します。

1. エージェントは、Microsoft Entraエージェント ID エージェント ID またはサービス プリンシパルを使用して、リソースにアクセスするための承認を行っています。
2. 承認は次のいずれかです。

    - エージェントは、ターゲット リソースの API にアクセスできるようにするために、ターゲット リソース (Microsoft Graph やアプリケーションなど) に対する OAuth *アプリケーションのアクセス許可* を自分の ID に割り当てる必要があります。
    - エージェントは、グループのメンバーとして ID を割り当てる必要があります。
    - エージェントは、その ID をディレクトリ ロールに割り当てる必要があります。 許可されている役割は、エージェントのために許可されている[Microsoft Entra のロール](https://learn.microsoft.com/ja-jp/entra/agent-id/authorization-agent-id#microsoft-entra-roles-allowed-for-agents)に一覧表示されています。
3. これらのリソースを保持するのに適したエンタイトルメント管理カタログがある、または作成できます。 作成するアクセス パッケージと、それに含まれるすべてのリソースがカタログに追加されます。 詳細については、 [カタログの作成を](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create)参照してください。

    注

    OAuth API のアクセス許可またはディレクトリ ロールをリソース ロールとしてアクセス パッケージに追加する場合、カタログはアクセス パッケージに追加されるときに [特権としてマーク](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-catalog-create#What-changes-for-privileged-catalogs) されます。

### エージェント ID のアクセス パッケージを作成する

エージェントにアクセス パッケージを使用するために、IT 管理者はまず、アプリケーション API に対する Entra ロール、グループ メンバーシップ、OAuth アクセス許可付与など、関連するリソースを使用して新しいアクセス パッケージを構成します。 その後、管理者はアクセス パッケージで必要なポリシー設定を構成します。 これらの設定では、アクセス権を取得できるユーザー、アクセスを要求できるユーザー、承認、アクセスの有効期限、拡張機能を定義します。

エージェント ID とサービス プリンシパルは、アプリケーション ロール、SAP ロール、または SharePoint Online サイト ロールへのアクセス パッケージを通じて追加できないため、これらのリソース ロールを含む既存のアクセス パッケージを再利用することはできません。 代わりに、新しいアクセス パッケージを作成します。

1. 少なくとも [ID ガバナンス管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)にサインインします。

    ヒント

    OAuth API のアクセス許可またはディレクトリ ロールをリソース ロールとしてアクセス パッケージに追加する場合は、全体管理者である必要があります。
2. **ID ガバナンス**&gt;**エンタイトルメント管理**&gt;**アクセス パッケージ**を参照してください。
3. **[新しいアクセス パッケージ]** を選択します。
4. [ **基本** ] タブでは、アクセス パッケージに名前と説明を付け、アクセス パッケージを作成するカタログを指定します。 **[カタログ]** ドロップダウン リストで、アクセス パッケージを配置するカタログを選択します。
5. **[次へ: リソース ロール]** を選択します。 [ **リソース ロール** ] タブで、アクセス パッケージに含めるリソース ロールを選択します。 エージェント ID のアクセス パッケージには、リソース ロールとしてセキュリティ グループ メンバーシップ、ディレクトリ ロール、または API アクセス許可を持つことができます。 詳細については、「[グループの追加](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources#add-a-group-or-team-resource-role)、[Microsoft Entra ロールの追加](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources#add-a-microsoft-entra-role-assignment)、[API アクセス許可の追加](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources#add-an-api-permission-preview)を参照してください。 エージェント ID のアクセス パッケージには、アプリケーション ロール、SAP ロール、または SharePoint Online サイトの役割を追加しないでください。

    ヒント

    含めるリソース ロールが不確定の場合は、アクセス パッケージの作成時にはそれらの追加をスキップし、後で[追加](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-resources)できます。 API アクセス許可の場合、アクセス パッケージにアクセス許可をオンボードするときと、アクセス パッケージにアクセス許可を追加する場合の両方で、リソースの所有権の検証が行われます。 この検証は、承認されたリソース所有者のみがアクセス パッケージを通じて API アクセス許可へのアクセスを導入または拡張できるようにするために役立ちます。
6. **[Next: Requests](次へ: 要求)** を選択します。 **[Requests](https://learn.microsoft.com/ja-jp/entra/agent-id/要求)** タブで、最初のポリシーを作成して、アクセス パッケージを要求できるユーザーを指定します。 [ **アクセス権を取得できるユーザー** ] セクション **で、ディレクトリ内のユーザー、サービス プリンシパル、およびエージェント ID を選択します**。 [ **特定のスコープの選択**] で、[ **すべてのエージェント**] オプションを選択します。

    注

    エージェントがMicrosoft EntraのエージェントIDを使用する代わりにサービスプリンシパルを使用している場合は、ディレクトリ内のサービスプリンシパルがこのアクセスパッケージを要求できるように、**All Service principals**オプションを使用してアクセスパッケージ割り当てポリシーも作成してください。
7. 必要な承認ステージの数を決定します。 **[ステージの数]** トグルを、1 段階の承認の場合は **[1]** に設定し、2 段階の承認の場合は **[2]** に設定し、3 段階承認の場合は **[3]** に設定します。 次に、承認ステージと承認者を構成します。 詳細については、 [単一ステージの承認](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#single-stage-approval)を参照してください。
8. 各承認ステージを指定したら、[ **次へ: 要求者情報**] を選択します。
9. **[次へ: ライフサイクル]** を選択します。 アクセス パッケージの割り当てが期限切れになるまでの期間を指定します。
10. **[Next : Rules] (次へ: 規則)** を選択します。
11. **次のステップ: 確認 + 作成**を選択します。 **[Review + create](https://learn.microsoft.com/ja-jp/entra/agent-id/確認と作成)** タブでは、設定の確認と検証エラーのチェックを行うことができます。
12. **[作成]** を選択して、アクセス パッケージとその初期ポリシーを作成します。

Microsoft Entra管理センターを使用するだけでなく、プログラム、Microsoft Graph、および Microsoft Graph 用 PowerShell コマンドレットを使用してアクセス パッケージを作成することもできます。 詳細については、「 [プログラムによるアクセス パッケージの作成」を](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create#create-an-access-package-programmatically)参照してください。

### アクセス要求と承認プロセス

エージェントは、3 つの異なる要求経路を介してアクセス パッケージを割り当てることができます。

- エージェント ID 自体は、 [accessPackageAssignmentRequest](https://learn.microsoft.com/ja-jp/graph/api/entitlementmanagement-post-assignmentrequests?tabs=http) を作成することで、操作に必要なときにプログラムでアクセス パッケージを要求できます。
- エージェントのスポンサーは、エージェント ID に代わってアクセスを要求し、アクセス要求プロセスで人間による監視を提供できます。 詳細については、「 [エージェント ID に代わってアクセス パッケージを要求する」を](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-behalf#request-an-access-package-on-behalf-of-an-agent-identity)参照してください。
- 管理者は、 [エージェント ID またはエージェントのユーザー アカウントをアクセス パッケージに直接割り当てることができます](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-an-identity)。

送信後、アクセス 要求は、アクセス パッケージ ポリシーの構成に基づいて、指定された承認者にルーティングされます。

### 別のユーザーに代わってアクセス パッケージを要求する

適切なアクセス許可を持つユーザーは、組織内の別のユーザーに代わってアクセス パッケージを要求できます。

1. [https://myaccess.microsoft.com](https://myaccess.microsoft.com/) でマイ アクセス ポータルにサインインします。 米国政府の場合は、マイ アクセス ポータル リンクのドメインは `myaccess.microsoft.us` です。
2. [マイ アクセス ポータル] ページで、**[アクセス パッケージ]** を選択します。
3. [アクセス パッケージ] ページで、直属の部下のために要求するアクセス パッケージを見つけて、**[要求]** を選択します。
4. [要求] ウィンドウの [ **要求の詳細**] で、[ **その他**の要求] を選択します。
5. **組織内のすべてのユーザーを**選択し、要求を送信するユーザーを検索します。

    [Image: 別のユーザーに代わってアクセス パッケージを要求するときに[組織内のすべてのユーザー]を選択するスクリーンショット。]
6. 残りの要求手順に進み、ユーザーに代わってアクセス パッケージ要求を完了します。

### アクセス割り当てのライフサイクル

承認者がアクセス パッケージの割り当て要求を受け入れると、エージェント ID は指定されたリソースへの期限付きアクセスを受け取ります。 アクセスは、アクセス パッケージで定義されているリソース ロールに従って付与されます。 これにより、エージェントが必要とする可能性があるアクセスの明確な開始日と終了日が確立されます。

エージェント ID に割り当てられていて、スポンサーがそのエージェント ID に設定されている場合、有効期限が近づくにつれて、スポンサーは保留中の有効期限に関する通知を受け取ります。 スポンサーには次の 2 つのオプションがあります。アクセス パッケージの延長を要求するか (ポリシーで許可されている場合)、アクセス パッケージの割り当ての期限切れを許可できます。

スポンサーが延長を要求した場合、この要求は新しい承認サイクルをトリガーできます。承認者は、継続的なアクセスが適切かどうかを再び確認します。 スポンサーがアクションを実行しない場合、アクセス パッケージの割り当ては終了日に自動的に期限切れになり、エージェント ID はターゲット リソースへのアクセスを失います。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/agent-autonomous-app-oauth-flow"} -->
## エージェント自律アプリの OAuth フロー - アプリ専用プロトコル - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/agent-autonomous-app-oauth-flow
- Service: entra-id / agent-id
- Article date: 2025-11-04
- Summary: OAuth 2.0 クライアント資格情報フローでアプリ専用プロトコルを使用して、ユーザー コンテキストなしでエージェント ID が自律的に動作する方法について説明します。

アプリのみの操作により、エージェント ID は、クライアント資格情報フローを使用して、ユーザー コンテキストなしで自律的に動作できます。 エージェント ID (アクター) は、それ自体 (サブジェクト) のトークンを取得するために使用されます。 このトークンを取得するために、エージェント ID ブループリントはエージェント ID を偽装します。 サブジェクトはアプリ専用アクセスを使用しますが、必要なアクセス許可のみが割り当てられるはずです。 テナント管理者は、すべてのアクセス許可を付与します。

エージェント ID ブループリントは、子エージェント ID のみを偽装できます。 エージェント ID を偽装できるのは、1 つのエージェント ID ブループリントだけです。 エージェント ID ブループリントは多数のエージェント ID を偽装できますが、複数のブループリントで所有できるエージェント ID はありません。 エージェント ID は、親エージェント ID ブループリントのテナント モデルに関係なく、常にシングルテナントです。 各エージェント ID は、1 つのテナントのセキュリティとポリシーの境界内で動作します。

Warnung

Microsoft は、これらのプロトコルを実装するには、Microsoft.Identity.Web や Microsoft Entra ID Auth SDK（サイドカー）ライブラリなどの承認済み SDK を使用することを推奨しています。 これらのプロトコルの手動実装は複雑でエラーが発生しやすく、SDK を使用すると、セキュリティとベスト プラクティスへの準拠が保証されます。

### マネージド ID の統合

マネージド ID は、推奨される資格情報の種類です。 この構成では、マネージド ID トークンは親エージェント ID ブループリントの資格情報として機能し、標準の MSI プロトコルは資格情報の取得に適用されます。 この統合により、エージェント ID は、資格情報の自動ローテーションやセキュリティで保護されたストレージなど、MSI のセキュリティと管理のすべての利点を受け取ることができます。

### プロトコルの手順

プロトコルの手順を次に示します。

[Image: エージェントの自律アプリ トークン取得フローの図を示す図。]

1. エージェント ID ブループリントは、交換トークン T1 を要求します。 エージェント ID ブループリントは、シークレット、証明書、またはマネージド ID トークンの可能性がある資格情報を提示します。 Microsoft Entra IDは、エージェント ID ブループリントに T1 を返します。 この例では、マネージド ID をフェデレーション ID 資格情報 (FIC) として使用します。

    Warnung

    セキュリティ リスクのために、エージェント ID ブループリントの運用環境では、クライアント シークレットをクライアント資格情報として使用しないでください。 代わりに、マネージド ID またはクライアント証明書を使用 [するフェデレーション ID 資格情報 (FIC)](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-config-app-trust-managed-identity) などのより安全な認証方法を使用してください。 これらの方法により、アプリケーション構成内に機密性の高いシークレットを直接格納する必要がなくなり、セキュリティが強化されます。

    ```
    POST /oauth2/v2.0/token
    Content-Type: application/x-www-form-urlencoded
    
    client_id=AgentBlueprint
    &scope=api://AzureADTokenExchange/.default
    &fmi_path=AgentIdentity
    &client_assertion_type=urn:ietf:params:oauth:client-assertion-type:jwt-bearer
    &client_assertion=TUAMI
    &grant_type=client_credentials
    ```

    - `fmi_path`: エージェント ID のクライアント ID (アプリ ID)。 このパラメーターは、トークン交換中にブループリントが偽装しているどの子エージェント ID であるかを Microsoft Entra ID に示します。

    TUAMI は、ユーザー割り当てマネージド ID (UAMI) のマネージド ID トークンです。 この手順では T1 が返されます。 ここで、T1 は FIC のトークン交換トークンです。
2. エージェント ID は、トークン交換要求をMicrosoft Entra IDに送信します。 要求にはトークン T1 が含まれます。

    ```
    POST /oauth2/v2.0/token
    Content-Type: application/x-www-form-urlencoded
    
    client_id=AgentIdentity
    &scope=https://resource.example.com/.default
    &client_assertion_type=urn:ietf:params:oauth:client-assertion-type:jwt-bearer
    &client_assertion={T1}
    &grant_type=client_credentials
    ```
3. Microsoft Entra ID T1 を検証した後、エージェント ID にアプリ専用リソース アクセス トークン (TR) を発行します。 Microsoft Entra IDは、T1 (aud) がエージェントIDの親アプリであり、エージェントIDのブループリントであることを検証します。

### シーケンス図

アプリ専用フローのシーケンス図を次に示します。

[Image: エージェントの自律アプリ トークン取得フローのトークン シーケンスを示す図。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/agent-blueprint"} -->
## Microsoft Entra エージェント IDのエージェント ID ブループリント - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/agent-blueprint
- Service: entra-id / agent-id
- Article date: 2026-04-28
- Summary: エージェント ID ブループリント、エージェントの定義方法、およびエージェント ID プラットフォーム内での認証のしくみについて説明します。

エージェント ID ブループリントは、Microsoft Entra ID内のオブジェクトであり、エージェント ID を作成するためのテンプレートとして機能します。 これは、組織内でエージェントを作成、認証、管理する方法の基礎を確立します。 エージェント ID ブループリントは、大規模な AI エージェントの安全な開発と管理を可能にする、Microsoft エージェント ID プラットフォームの重要なコンポーネントです。

[Image: エージェント ID とエージェント ID ブループリントの関係を示す図。]

エージェント ID ブループリントは、Microsoft Entra エージェント ID プラットフォームの強力なコンポーネントです。 エージェント ID の作成と認証方法に関する重要な情報が保持され、エージェント ID を大規模に管理するためのコンテナーとして機能します。

### エージェント ID ブループリント

エージェント ID ブループリントは、単なるエージェント ID のテンプレートではなく、建物のブループリントが単なる図面ではなく、単なるブループリントであるのと同じです。 アーキテクチャブループリントに配管、電気、構造の詳細が含まれる場合、エージェント ID ブループリントには認証、アクセス許可、アクティビティ ログに関する重要な情報が含まれます。

エージェント ID ブループリントを使用して、同じ種類の複数のエージェントをデプロイできます。 1 つのエージェント ID ブループリントから複数のエージェントが作成された場合、各エージェントは独自の ID、資格情報、アクセス許可を持ちますが、ブループリントで定義されている共通の特性を共有します。 これにより、組織は、必要に応じて個々のエージェントをカスタマイズする柔軟性を維持しながら、特定の種類のすべてのエージェントに対して一貫した構成を作成できます。

エージェント ID ブループリントには、すべてのエージェント ID で共有される次のプロパティがあります。

- **説明**: エージェントの目的と機能の簡単な概要。
- **アプリ ロール**: エージェントの使用時にユーザーやその他のプリンシパルに付与できるロールを定義します。
- **検証済み発行元**: エージェントを構築した組織。
- **認証プロトコルの設定**: エージェントに発行されたアクセス トークンに含まれる情報 ( **OptionalClaims** など) を構成します。

完全なスキーマは、[Microsoft Graph API リファレンス ドキュメント](https://learn.microsoft.com/ja-jp/graph/api/resources/agentidentityblueprint)で入手できます。

### エージェント ID ブループリントの主な特性

エージェント ID ブループリントには、標準プロパティに加えて、エージェント ID ブループリントからエージェント ID を作成する前に理解しておくことが重要な重要な特性が含まれています。

#### 資格情報

エージェント ID の認証に使用される資格情報は、エージェント ID ブループリントで構成されます。 AI エージェントが操作を実行する場合、エージェント ID ブループリントで構成された資格情報を使用して、Microsoft Entra IDからアクセス トークンを要求します。 エージェント ID ブループリントに付与された OAuth アクセス許可は、そのブループリントから作成されたすべてのエージェント ID に付与されます。 エージェント ID に使用できる資格情報の種類はいくつかあります。 これらの詳細については、 [エージェント ID の資格情報を](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-identities#authorizing-agent-identities)参照してください。 認証プロトコルについては、「[エージェント ID 認証プロトコル」を](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-oauth-protocols)参照してください。

#### セキュリティ

このブループリントは、さまざまな ID 管理操作を実行できるエージェント ID の論理コンテナーを提供します。 この機能は、管理者がセキュリティ作業を多数の AI エージェントにスケーリングするのに役立ちます。

ID 管理者は、ブループリントから作成されたすべてのエージェント ID に対して有効なエージェント ID ブループリントにポリシーと設定を適用できます。 条件付きアクセス ポリシーは、そのブループリントから作成されたすべてのエージェント ID を対象とするエージェント ID ブループリントを適用できます。 エージェント ID ブループリントを無効にすると、そのすべてのエージェント ID が認証されなくなります。

#### エージェント ID の作成に使用されます

ブループリントは情報を保持するだけではありません。 また、Microsoft Entra ID テナントの特殊な ID の種類でもあります。 ブループリントは、テナント内でエージェント ID のプロビジョニングまたはプロビジョニング解除の操作を 1 つだけ実行できます。 Microsoft Entra ID テナント内のすべてのエージェント ID は、エージェント ID ブループリントから作成されます。 エージェント ID を作成するために、ブループリントには次の内容があります。

- OAuth クライアント ID: Microsoft Entra IDからアクセス トークンを要求するために使用される一意の ID。
- 資格情報: Microsoft Entra IDからアクセス トークンを要求するために使用されます。
- `AgentIdentity.CreateAsManager`: ブループリントがテナントにエージェント ID を作成できるようにする特別な Microsoft Graph 権限。

サービスでは、ブループリントのクライアント ID、資格情報、およびアクセス許可を使用して、Microsoft Graph API 経由でエージェント ID 作成要求を送信します。 ブループリントによって作成されたエージェント ID は、共通の特性を共有します。

詳細については、「 [エージェント ID の作成」を](https://learn.microsoft.com/ja-jp/entra/agent-id/create-delete-agent-identities)参照してください。

### 継承可能なアクセス許可と必要なリソース アクセス

エージェント ID ブループリントには、エージェント ID がリソースにアクセスする方法を制御する 2 つの重要なアクセス許可関連の構成が含まれます。

- **必要なリソース アクセス** は、エージェントが機能するために必要な API とアクセス許可を宣言します。 この一覧は、同意レビュー中に管理者に表示され、エージェントを承認するかどうかを評価するのに役立ちます。
- **継承可能なアクセス許可** は、ブループリントから作成されたエージェント ID によってアクセス許可を自動的に継承できるリソース アプリを定義します。 管理者が継承可能なリソース アプリからブループリント プリンシパルに対するアクセス許可を付与すると、その組織内のすべてのエージェント ID がそれらのアクセス許可を自動的に受け取ります。

これらの構成は、単独で承認を付与しない宣言です。 管理者は、ブループリント プリンシパルまたは個々のエージェント ID に対するアクセス許可に同意する必要があります。

詳細については、「 [継承可能なアクセス許可](https://learn.microsoft.com/ja-jp/entra/agent-id/concept-inheritable-permissions)」を参照してください。

### エージェント ID ブループリント プリンシパル

エージェント ID ブループリント プリンシパルは、特定のテナント内でのエージェント ID ブループリントの存在を表すMicrosoft Entra エージェント ID内のオブジェクトです。 エージェント ID ブループリント アプリケーションがテナントに追加されると、Microsoft Entraは対応するプリンシパル オブジェクト (エージェント ID ブループリント プリンシパル) を作成します。

[Image: エージェントのブループリント プリンシパルを示す図。]

このプリンシパルは、いくつかの重要な役割を果たします。

- **トークンの発行**: エージェント ID ブループリントを使用してテナント内のトークンを取得する場合、結果のトークンの `oid` (オブジェクト ID) 要求はエージェント ID ブループリント プリンシパルを参照します。 これにより、エージェント ID ブループリントによって実行されるすべての認証または承認が、テナント内のプリンシパル オブジェクトに対してトレース可能になります。
- **監査ログ**: エージェント ID ブループリントによって実行されるアクション (エージェント ID の作成など) は、エージェント ID ブループリント プリンシパルによって実行されている監査ログに記録されます。 エージェント ID ブループリントによって開始される操作について、明確な説明責任と追跡可能性を提供します。

エージェント ID ブループリントは、常にMicrosoft Entra テナントに作成されます。 エージェント ID ブループリントは、多くの場合、その同じテナントにエージェント ID を作成するために使用されます。 これらのエージェント ID ブループリントは"シングルテナント" と呼ばれます。エージェント ID ブループリントは、"マルチテナント" として構成し、Microsoft カタログを介して潜在的な顧客に公開することもできます。 お客様は、これらのブループリントをテナントに追加して、エージェント ID の作成に使用できます。

どちらの場合も、ブループリントがテナントに追加されると、エージェント ID ブループリント プリンシパルが常に作成されます。 このプリンシパルの存在は、ブループリントがテナントに存在し、エージェント ID の作成に使用できることを示します。 顧客は、エージェント ID ブループリント プリンシパルを削除することで、テナントからそのブループリントを消去できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/agent-id-ai-guided-setup"} -->
## Microsoft Entra エージェント ID用の AI ガイド付きセットアップ

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/agent-id-ai-guided-setup
- Service: entra-id / agent-id
- Article date: 2026-05-12
- Summary: AI コーディング エージェントを使用して、ブループリントの作成、資格情報の構成、エージェント ID のプロビジョニングなど、Microsoft Entra エージェント IDのオンボード プロセスを自動化する方法について説明します。

Microsoft Entra エージェント IDへのオンボードには、エージェント ID ブループリントの作成、資格情報の構成、識別子 URI とスコープの設定、ブループリント プリンシパルの作成、エージェント ID のプロビジョニングという複数の手順が含まれます。 各ステップには独自の前提条件、検証チェック、意思決定ポイントがあります。

この AI ガイド付きセットアップでは、AI コーディング エージェント (VS Code のGitHub Copilotなど) を使用して、ユーザーに代わって手順を実行することで、このワークフロー全体が自動化されます。 複数のドキュメントページを移動したり手動でコマンドを実行する代わりに、AIエージェントに単一の命令ファイルを提供し、インタラクティブにプロセスを案内してくれます。 この命令ファイルはスキルとして使用でき、複数の方法でアクセスできます。

### 特典

AIガイド付きセットアップは、手動ワークフローに比べていくつかの利点があります。

- **1 つのエントリ ポイント**: 1 つの命令ファイルで、複数のドキュメント ページ間を移動する必要性が置き換えられます。 AIエージェントは手順を順番に追い、トランジションを自動的に処理します。
- **自動前提条件検証**: AI エージェントは、適切なMicrosoft Entraロールがあること、必要なツールと Graph モジュールがインストールされていること、および API 呼び出しを行う前に管理者の同意を得て必要なアクセス許可が構成されていることを検証します。
- **スマートな既定値と自動検出**: AI エージェントは、既存のユーザー情報とリソースの詳細についてテナントにクエリを実行し、構成入力を収集するときにそれらの値を提案として使用します。
- **派生名前付け規則**: エージェントの表示名を 1 つ指定すると、AI エージェントは、一貫したパターンを使用して関連するすべてのリソース名 (ブループリント、ブループリント プリンシパル、エージェント ID、識別子 URI) を派生させます。
- **インライン エラー処理**: コマンドが失敗すると、AI エージェントはエラーを分析し、修正プログラムを提案し、再試行します。トラブルシューティングドキュメントを検索する必要はなくなります。 このエラー処理は、アクセス許可の伝達の遅延や OData ヘッダーの要件などのエージェント ID 固有の落とし穴に特に役立ちます。
- **べき等操作**: AI エージェントは、リソースを作成する前にリソースが既に存在するかどうかをチェックし、以前の試行が中断された場合にセットアップを安全に再実行できるようにします。

### 前提条件

開始する前に、次の前提条件を満たしていることを確認してください。

#### 必要なツール

AI ガイド付きセットアップには、ターミナル アクセスを備えた AI コーディング エージェントが必要です。

- [Visual Studio Code](https://code.visualstudio.com/)[GitHub Copilot](https://marketplace.visualstudio.com/items?itemName=GitHub.copilot) および [GitHub Copilot Chat](https://marketplace.visualstudio.com/items?itemName=GitHub.copilot-chat) 拡張機能がインストールされています。
- VS Code で直接 Microsoft Entra エージェント ID スキルを提供する [GitHub Copilot for Azure](https://marketplace.visualstudio.com/items?itemName=ms-azuretools.vscode-azure-github-copilot) 拡張機能。

このスキルでは、2 つのプロビジョニング パスがサポートされています。 設定に応じて、いずれかまたは両方をインストールします。

- **PowerShell を使用する方法**: [PowerShell 7](https://learn.microsoft.com/ja-jp/powershell/scripting/install/installing-powershell) 以降と [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) が必要です。 `Install-Module Microsoft.Graph.Applications -Scope CurrentUser -Force` を使用してインストールします。
- **Python パス**: `azure-identity` および `requests` を含む [Python](https://www.python.org/downloads/) 3.8 以降。 `pip install azure-identity requests` を使用してインストールします。

#### 必要なアカウントとアクセス許可

- 次のいずれかのロールを持つ **Microsoft Entra テナント**へのアクセス。
    - [エージェント ID 開発者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-developer)はエージェント ID の設計図とエージェント ID を作成。 エージェント ID ブループリントの所有者は、エージェント ID ロールなしで、そのブループリントのエージェント ID を作成できます。
    - [エージェント ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-administrator)は、エージェント ID リソースへの完全な管理アクセスを提供します。

注

エージェント ID ブループリントまたはエージェント ID ブループリント プリンシパルの所有者は、Microsoft Entra エージェント IDロールなしで、そのブループリントのエージェント ID を作成できます。 エージェント ID ブループリント作成者は、ブループリントと関連するエージェント ID ブループリント プリンシパルの両方の所有者として自動的に設定されます。

- **アクセス許可付与の追加ロール:**
    - [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)Microsoft Graphアプリケーションのアクセス許可を付与します。
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)または[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)は、委任された Microsoft Graph のアクセス許可を付与します。

#### 必要なMicrosoft Graphアクセス許可

使用するクライアント (PowerShell またはカスタム アプリの登録) は、次の委任されたアクセス許可で承認されている必要があります。

| 許可 | Purpose |
| --- | --- |
| `AgentIdentityBlueprint.Create` | 新しいエージェント ID ブループリントを作成する |
| `AgentIdentityBlueprint.ReadWrite.All` | ブループリントのプロパティ (識別子 URI、スコープ、資格情報) の読み取りと更新 |
| `AgentIdentityBlueprintPrincipal.Create` | ブループリントのサービス プリンシパルを生成する |
| `AgentIdentity.Create.All` | ブループリントの下にエージェント ID を作成する |
| `AgentIdentity.ReadWrite.All` | エージェント ID の読み取りと更新 |
| `Application.ReadWrite.All` | アプリケーション オブジェクトのブループリント CRUD |
| `AppRoleAssignment.ReadWrite.All` | エージェント ID にアプリケーションのアクセス許可を付与する |
| `DelegatedPermissionGrant.ReadWrite.All` | エージェント ID に委任されたアクセス許可を付与する |
| `User.Read` | サインインしているユーザーのプロファイルを読み取る (スポンサー割り当て用) |

Important

**`DefaultAzureCredential` トークンとAzure CLI トークンは、エージェント ID API では機能しません。** Azure CLIトークンには、`Directory.AccessAsUser.All`が含まれます。エージェント ID API は 403 エラーで拒否します。 `Connect-MgGraph` を、明示的な委任スコープ (PowerShell) または `client_credentials` を使用する専用のアプリ登録 (Python) とともに使用します。 エージェント ID のプロビジョニングには `az login` トークンを使用しないでください。

#### 必要なエージェント コード (省略可能)

エージェント ID が必要な作業エージェント プロジェクト (Python、Node.js、または.NET) が既にある場合は、プロジェクト ディレクトリを使用できます。 まだお持ちでない場合でも、ブループリントのセットアップを個別に完了できます。

### 概要

AI ガイド付きセットアップでは、スキルが使用されます。これは、すべての手順と検証チェックを含む 1 つの命令ファイルです。 スキルには、次の 2 つの方法のいずれかでアクセスできます。

- **GitHub Copilot for Azure 拡張機能を使用する**（推奨）: [GitHub Copilot for Azure VS Code 拡張機能](https://learn.microsoft.com/ja-jp/azure/developer/github-copilot-azure/introduction)をインストールします。 Microsoft Entra エージェント ID スキルは、Copilot Chatでのエージェント ID の設定について質問すると自動的にアクティブになります。
- ** GitHub** から間接的に: Copilot Chat プロンプトで参照することで、スタンドアロンの [Microsoft Entra エージェント ID スキル](https://github.com/microsoft/GitHub-Copilot-for-Azure/blob/main/plugin/skills/entra-agent-id/SKILL.md) を使用します。

#### 手順 1: VS Code でプロジェクトを開く

Visual Studio Codeでエージェント プロジェクト ディレクトリ (または任意の作業ディレクトリ) を開きます。

#### 手順 2: エージェント モードで GitHub Copilot Chatを開く

GitHub Copilot Chat パネルを開き、**Agent モード**に切り替えます。 エージェント モードを使用すると、GitHub Copilotターミナル コマンドの実行、ファイルの読み取り、環境との対話を行うことができます。これは、AI ガイド付きセットアップで必要になります。

Important

**エージェント モード**を使用する必要があります ([要求] モードまたは [編集] モードではありません)。 AIガイドのセットアップは、端末コマンドを実行し、環境とやり取りする能力を必要とします。

#### 手順 3: ガイド付きセットアップを開始する

Azure拡張機能のGitHub Copilotがインストールされている場合は、エージェント ID を設定するようにCopilotに依頼してください。 例えば次が挙げられます。

```text
@azure Use the Agent ID Skill to set up an agent identity blueprint and create agent identities for my project using Microsoft Entra Agent ID.
```

拡張機能がない場合は、GitHubからスキルを直接参照します。

```text
Follow the steps in https://github.com/microsoft/GitHub-Copilot-for-Azure/blob/main/plugin/skills/entra-agent-id/SKILL.md
```

AI エージェントがスキルを読み取り、ガイド付きセットアップを開始します。 これは、次の手順を順番に実行します。

1. **Validate の前提条件**: Microsoft Entraの役割を確認し、必要なツール (Microsoft Graph モジュールを使用する PowerShell、または `azure-identity` を使用したPython) がインストールされていることを検証します。
2. **Authenticate**: 必要なスコープでMicrosoft Graphに接続します。 PowerShell の場合、スキルは明示的に委任されたスコープを持つ `Connect-MgGraph` を使用します。 Pythonでは、クライアント資格情報を使用した専用アプリの登録が使用されます。
3. **エージェント ID ブループリントの作成**: 表示名を収集し、スポンサー (ユーザー) を識別し、型指定されたエンドポイント (`/applications/microsoft.graph.agentIdentityBlueprint`) を使用してブループリントを作成し、 `appId`を記録します。
4. **資格情報の構成**: マネージド ID を持つフェデレーション ID 資格情報 (運用環境の場合) またはクライアント シークレット (ローカル開発/テスト用) をブループリントに追加します。
5. **識別子 URI とスコープの構成**: `identifierUris` を `api://{appId}` に設定し、エージェント間およびユーザー間通信用の OAuth2 アクセス許可スコープを作成します。
6. **ブループリント プリンシパルを作成**する: 型指定されたエンドポイントを使用してブループリントのサービス プリンシパルを作成します (プリンシパルは自動作成 **されないため** 、明示的に行う必要があります)。
7. **エージェント ID の作成**: ブループリントの下に 1 つ以上のエージェント ID サービス プリンシパルを作成します。

エージェントがスキルを読み取ると、拡張機能やその他のツールをインストールするように求められる場合があります。 プロンプトに従って、環境の準備ができていることを確認します。

#### ステップ4:プロンプトに応答する

AIエージェントは特定のポイントで一時停止し、あなたから入力を収集します:

- **表示名**: エージェント ID ブループリントの表示名 ("Contoso Budget Agent" など)。
- **スポンサー**: エージェントの責任を負うユーザーまたはグループ。 既定値は、現在サインインしているユーザーです。
- **所有者**: ブループリントに技術的な変更を加えることができるユーザーまたはサービス プリンシパル。 省略可能ですが推奨されます。
- **資格情報の種類**: マネージド ID (運用環境に推奨) を使用するか、証明書またはクライアント シークレット (ローカル開発用) を使用するか。
- **エージェント ID の数**: このブループリントの下に作成するエージェント ID の数。
- **派生値の確認**: リソースが作成される前に、自動生成された名前と URI を確認します。

ヒント

AI エージェントは、構成入力を要求するときに、Microsoft Entra テナントからの実際の値を例として示します。 提案を受け入れるか、自分の価値観を提示するかのどちらかです。

#### 手順 5: Microsoft Entra 管理センターで確認する

セットアップが完了すると、AI エージェントはリソースを確認する方法について説明します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)に少なくとも [Agent ID Developer](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-developer) としてサインインします。
2. **Entra ID**&gt;**Agents**&gt;**Agent ID** に移動して、新しいエージェント ID ブループリントとその下に作成されたすべてのエージェント ID を確認します。
3. ブループリントに正しい資格情報、識別子 URI、およびスコープが構成されていることを確認します。

### AIガイド付きセットアップがカバーしていること

AI ガイド付きセットアップでは、エージェント ID 統合の次のステージが自動化されます。

| Stage | 何が起きるか | 関連ドキュメント |
| --- | --- | --- |
| 前提条件 | Microsoft Entraロール、PowerShell モジュール、および Graph のアクセス許可を検証します | [ブループリントの作成: 前提条件](https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint#prerequisites) |
| 環境のセットアップ | 正しいスコープでMicrosoft Graphに接続します | [ブループリントを作成する: 環境を準備する](https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint#prepare-your-environment) |
| ブループリントの作成 | スポンサーと所有者を使用してエージェント ID ブループリントを作成します | [ブループリントを作成する](https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint#create-an-agent-identity-blueprint-1) |
| 資格情報の構成 | マネージド ID FIC またはクライアント シークレットをブループリントに追加します | [資格情報を構成する](https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint#configure-credentials-for-the-agent-identity-blueprint) |
| スコープの構成 | 識別子 URI と OAuth2 アクセス許可スコープを設定します | [識別子 URI とスコープを構成する](https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint#configure-identifier-uri-and-scope) |
| プリンシパルの作成 | エージェント ID の設計原則（サービス プリンシパル）を作成します | [エージェントの設計図主要要素を作成する](https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint#create-an-agent-blueprint-principal) |
| エージェントのアイデンティティ | ブループリントに基づいてエージェント ID サービス プリンシパルを作成します | [エージェント ID の作成](https://learn.microsoft.com/ja-jp/entra/agent-id/create-delete-agent-identities) |

注

AI ガイド付きセットアップでは、エージェント ID をエージェントのコードに統合する必要性は置き換わりません。 エージェントが [トークンを取得](https://learn.microsoft.com/ja-jp/entra/agent-id/create-delete-agent-identities#get-an-access-token-using-agent-identity-blueprint) し、そのエージェント ID を使用して操作を実行する方法を理解する必要があります。 ガイド付きセットアップでは、エージェント コードで使用される ID インフラストラクチャが作成されます。

### AI ガイド付きセットアップで対応する一般的な落とし穴

エージェント ID API には、AI ガイド付きセットアップで自動的に検出および解決されるいくつかの要件がありますが、明らかな要件ではありません。 これらの落とし穴を理解することは、問題をデバッグしたり、セットアップを拡張したりする必要がある場合に役立ちます。

#### OData-Version ヘッダーが必要です

すべてのエージェント ID API 呼び出しには、 `OData-Version: 4.0` ヘッダーが必要です。 このヘッダーを省略すると、API によってエージェント ID ブループリントではなく標準アプリケーションが自動的に作成される可能性があります。 AI ガイド付きセットアップには、常にこのヘッダーが含まれます。 スキルでは、`/applications/microsoft.graph.agentIdentityBlueprint` プロパティを持つ生の`/applications`ではなく、型指定されたエンドポイント (`@odata.type` など) も使用して、この問題のリスクを軽減します。

#### ブループリントのプリンシパルは自動的に作成されません。

エージェント ID ブループリント (`POST /applications`) を作成しても、そのブループリント プリンシパル (サービス プリンシパル) は自動的には作成 **されません** 。 ブループリント プリンシパルがない場合、後続のすべてのエージェント ID の作成は次の場合に失敗します。

```
400: The Agent Blueprint Principal for the Agent Blueprint does not exist.
```

AI ガイド付きセットアップでは、ブループリントの直後に常にブループリント プリンシパルが作成されます。 また、べき等ケースも処理します。 以前の実行でブループリントが作成されたが、プリンシパルを作成する前にクラッシュした場合、セットアップはこのイベントを検出し、不足しているプリンシパルを作成します。

#### スポンサーが必要です

スポンサーは必須であり、ユーザー、動的メンバーシップを持つグループ、または統合グループにすることができます。 ブループリントとエージェント ID の両方の作成には、 `sponsors@odata.bind` フィールドが必要です。 これを行わないと、次の情報が表示されます。

```
400: No sponsor specified. Please provide at least one sponsor.
```

AI ガイド付きセットアップでは、スポンサー割り当ての **ユーザー** オブジェクトのみを受け入れ、 `/users/{objectId}` URL 形式 ( `/directoryObjects/` や `/servicePrincipals/`ではなく) を使用します。 セットアップでは、現在のユーザーのオブジェクト ID が解決され、既定のスポンサーとして使用されます。 ブループリントのスポンサーとして [supported グループ](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-owners-sponsors-managers#sponsors)を割り当てるには、Microsoft Graph APIを直接使用します。

#### アクセス許可の伝達には 30 ~ 120 秒以上かかります

エージェント ID のアクセス許可に対して管理者の同意を付与すると、新しく付与されたアクセス許可はすぐにトークンに表示されません。 トークン エンドポイントはキャッシュされた要求を処理し、伝達には 30 ~ 120 秒以上かかることがあります。

AI ガイド付きセットアップでは、403 を受信したときに指数バックオフで操作を再試行することで、最近のアクセス許可の変更を処理します。 これを手動でスクリプト化する場合は、再試行ロジックを実装します。

```powershell
# Example: Retry with backoff after admin consent
$maxRetries = 5
for ($i = 0; $i -lt $maxRetries; $i++) {
    try {
        # Attempt the operation
        $result = Invoke-MgGraphRequest -Method POST -Uri $uri -Body $body
        break
    } catch {
        if ($_.Exception.Response.StatusCode -eq 403 -and $i -lt $maxRetries - 1) {
            $wait = 20 * ($i + 1)
            Write-Host "Permission not yet propagated. Retrying in $wait seconds..."
            Start-Sleep -Seconds $wait
            # Disconnect and reconnect to force a fresh token
            Disconnect-MgGraph
            Connect-MgGraph -Scopes $scopes
        } else {
            throw
        }
    }
}
```

#### エージェント ID にパスワード資格情報を設定できない

エージェント ID は、アプリケーション オブジェクトをバッキングしないサービス プリンシパルです。 エージェント ID に `passwordCredential` を直接追加しようとすると、次の結果が得られます。

```
PropertyNotCompatibleWithAgentIdentity
```

資格情報は、個々のエージェント ID ではなく、 **ブループリント**で構成する必要があります。 マネージド ID フェデレーション (推奨) を使用するか、ブループリントにシークレット/証明書を追加し、エージェント ID は偽装によって資格情報を継承します。

#### 識別子 URI は明示的に設定する必要があります

ブループリントの `identifierUris` フィールドは、既定では設定されていません。 そうしないと、OAuth2 スコープ `api://{appId}/.default` は解決されず、エージェントのトークンの取得は失敗します。 AI ガイド付きセットアップでは、スコープのセットアップ手順の一部として常にこの値が構成されます。

#### ブループリントのフェデレーション ID 資格情報のパス

マネージド ID フェデレーション用のフェデレーション ID 資格情報 (FIC) を追加する場合は、エージェント固有の API パスを使用する必要があります。

```
POST /applications/{blueprint-obj-id}/microsoft.graph.agentIdentityBlueprint/federatedIdentityCredentials
```

`/applications/{id}/federatedIdentityCredentials` パスを使用すると、エージェント ID ブループリントで機能する場合がありますが、サポートされていないため、推奨されません。

#### トークン発行者はエンドポイントのバージョンによって異なります

エージェント バックエンドでトークンを検証する場合は、次のバリエーションに注意してください。

- v1.0 トークンは発行者`https://sts.windows.net/{tenant-id}/`を使用します
- v2.0 トークンは発行者 `https://login.microsoftonline.com/{tenant-id}/v2.0` を使用する

トークン検証ロジックで両方の形式を受け入れます。

### Troubleshooting

#### AIエージェントはターミナルコマンドを実行しません

AI エージェントがコマンドを記述しても実行しない場合は、GitHub Copilot Chat で **Agent モード**を使用していることを確認します。 AskモードとEditモードには端末アクセスがありません。

#### AIエージェントは検証ステップをスキップします

命令ファイルは厳密なステップ順序を強制します。 AIエージェントがステップを飛ばしているように見えたら、最初から指示に従うようリマインドしてください。 例えば次が挙げられます。

```text
Please start from Step 1 in the setup instructions and work through each step in order.
```

#### Graph コマンドが 403-Forbidden で失敗する

403 エラーの最も一般的な原因:

- **Azure CLIまたは`DefaultAzureCredential` トークンの使用**: Azure CLIトークンには、`Directory.AccessAsUser.All`が含まれます。エージェント ID API は完全に拒否します。 明示的に委任されたスコープで `Connect-MgGraph` を使用するか、 `client_credentials`での専用アプリの登録を使用します。 前提条件の 認証の警告 を参照してください。
- **アクセス許可の伝達の遅延**: 管理者の同意を得てから 1 ~ 2 分待ってから再試行します。 AI ガイド付きセットアップでは、再試行ロジックを使用してこれを自動的に処理します。
- **管理者の同意がない**: [Microsoft Entra 管理センター](https://entra.microsoft.com/)で**アプリの登録**&gt;クライアント アプリ&gt;**API 権限**の必要なアクセス許可が管理者によって許可されているかを確認してください。

#### ブループリントの作成は成功しますが、標準アプリケーションが返されます

この結果は、 `OData-Version: 4.0` ヘッダーが見つからない場合に発生します。 この問題を回避するには、`/applications/microsoft.graph.agentIdentityBlueprint`で生の`/applications`ではなく、型指定されたエンドポイント (`@odata.type`) を使用します。

#### エージェント ID の作成が "ブループリント プリンシパルが存在しない" で失敗する

ブループリントの後に、ブループリント プリンシパルは別の手順として作成する必要があります。 走れ

```http
POST https://graph.microsoft.com/v1.0/servicePrincipals/microsoft.graph.agentIdentityBlueprintPrincipal
OData-Version: 4.0
Content-Type: application/json

{
  "appId": "<your-blueprint-app-id>"
}
```

#### 資格情報の有効期間ポリシー エラー

テナントには、クライアント シークレットの最大有効期間を制限する資格情報ライフサイクル ポリシーがある場合があります。 パスワードを追加するときに資格情報の有効期間に関するエラーが発生した場合は、組織のポリシーに合わせて `endDateTime` の値を減らします。

#### 構成値を変更する必要がある

セットアップ後に構成値を変更する必要がある場合は、次のことができます。

- 更新された値を使用して AI ガイド付きセットアップを再実行します。 べき等チェックでは、既に正しく存在するリソースがスキップされます。
- Microsoft Graph PowerShell を使用して、`PATCH` 要求で特定のプロパティを更新します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/agent-id-creation-channels"} -->
## エージェント ID はどのように作成されますか?

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/agent-id-creation-channels
- Service: entra-id / agent-id
- Article date: 2026-04-16
- Summary: Microsoft Entra エージェント ID ブループリント、エージェント ID、エージェントのユーザー アカウントを作成する方法と、テナントへの導入を監視および制御する方法について説明します。

ID およびアクセス管理とセキュリティで作業する管理者は、AI エージェント ID をテナントに追加する方法、追加方法、およびそこにいるときに行う操作を明確に把握する必要があります。 この記事では、Microsoft Entra テナントでエージェント ID を作成できるさまざまなチャネル、各チャネルに必要なロールとアクセス許可、およびエージェント ID の作成を監視および制御するための戦略について説明します。

### 作成チャネルの概要

**エージェントのアイデンティティ設計図は、複数のチャネルを通じてテナントに入ることができます。** 各チャネルは、異なる監視ポイントと制御ポイントを意味します。

| チャネル | 一般的なアクター | 制御は次の方法で行うことができます。 |
| --- | --- | --- |
| \*Microsoft Entra 管理センター/Azureポータル | 開発者、管理者 | ロールの割り当て |
| Microsoft Graph API | 自動化、DevOps パイプライン、統合サービス | Microsoft Graph のアクセス許可の付与 |
| CLI、PowerShell、コードとしてのインフラストラクチャ | DevOps、管理者 | ロールの割り当て |
| \*Microsoft製品の統合 | Microsoft エージェント プラットフォームのユーザー | 各製品の管理コントロール |
| \*Microsoft Entra ID同意エクスペリエンス | 従業員、組織のメンバー | アプリの同意ポリシー |

- マネージド エクスペリエンス チャネルを示します

マネージド エクスペリエンスのいずれかを通じてエージェント ID ブループリントがテナントに追加されると、 **エージェント ID ブループリント プリンシパル** オブジェクトもテナントに作成されます。 このプリンシパルには、テナントにエージェント ID とエージェントのユーザー アカウントを作成するための特権が割り当てられます。 Microsoft Graph API、CLI、PowerShell、または Infrastructure as Code Tools チャネルを使用してエージェント ID ブループリントを追加する場合は、関連付けられているプリンシパルを手動で作成する必要があります。

前の表に示したチャネルに加えて、テナント内にエージェント ID ブループリント プリンシパルを持つシステムは、エージェント ID とエージェントのユーザー アカウント作成のチャネルになります。

| チャネル | 一般的なアクター | 制御は次の方法で行うことができます。 |
| --- | --- | --- |
| エージェント ID ブループリント プリンシパル | テナントに追加されたMicrosoft構築済みエージェントと非Microsoft エージェント | Microsoft Entra アプリケーションのアクセス許可 |

以下のセクションでは、これらの各チャネルについて詳しく説明します。

### エージェント ID 作成ワークフロー

次の表では、前のセクションで説明したチャネルのいずれかを使用して、エージェント ID をテナントに追加する方法の 3 つの例を示します。

| - | コピロットスタジオに組み込まれています | 開発者によるビルド | Microsoft 以外で作成された |
| --- | --- | --- | --- |
| **エージェント ブループリント ソース** | Copilot Studio が有効になっているときに自動的に追加される | Microsoft Entra 管理センターを使用して開発者が作成する | 従業員がエージェントに同意したときに追加されました |
| **エージェントを作成するのは誰か** | Copilot Studio の従業員 | コードを使ってエージェント ブループリントを利用する開発者 | Microsoft 以外のエージェント (購入/同意後) |
| **ID の作成** | Copilot Studio がテナントに ID を作成する | Microsoft Graph を使用して ID を作成するコード | エージェントが Microsoft Graph を使用して ID を作成する |

### Microsoft Entra 管理センター、Microsoft Graph、CLI ツール

開発者や組織の他のメンバーには、Microsoft Entra 管理センター、Azure portal、Microsoft Graph PowerShell/CLI、Bicep/ARM テンプレート、およびその他のツールを使用して、エージェント ID ブループリントを作成する権限を付与できます。 これらのツールはすべて、Microsoft Graph API を呼び出すことによってエージェント ID ブループリントを作成します。

これらのツールを使用してエージェント ID ブループリントを作成するには、次のいずれかのアクセス許可が必要です。

| シナリオ | 権限が必要です |
| --- | --- |
| ユーザーが Microsoft Entra 管理センター (CLI) を使用してブループリントを作成する | ユーザーには、 **エージェント ID の開発者** ロールまたは **エージェント ID 管理者ロールが** 割り当てられている必要があります。 |
| クライアントは、委任されたアクセス許可を使用して Microsoft Graph を使用してブループリントを作成します | ユーザーには、前の行で説明したロールが必要です。 クライアントには *、少なくとも***AgentIdentityBlueprint.Create** の委任されたアクセス許可が付与されている必要があります。 |
| クライアントは、アプリケーションのアクセス許可を使用して Microsoft Graph を使用してブループリントを作成します | クライアントには **AgentIdentityBlueprint.Create** アプリケーションのアクセス許可が付与されている必要があります。 |

これらのツールを使用してエージェント ID を作成するには、次のいずれかのアクセス許可が必要です。

| シナリオ | 権限が必要です |
| --- | --- |
| ユーザーは、Microsoft Entra 管理センター、Microsoft Graph、または PowerShell/CLI を使用してエージェント ID を作成します | ユーザーには、 **エージェント ID 開発者**、 **エージェント ID 管理者**、または **AI 管理者** ロールが割り当てられている必要があります。 |
| クライアントは、委任されたアクセス許可を使用して、Microsoft Graphを使用してエージェント ID を作成します | ユーザーには **エージェント ID 管理者** ロールが割り当てられている必要があります。 クライアントには **AgentIdentity.Create.All** または委任されたアクセス許可 **AgentIdentity.ReadWrite.Al** 付与する必要があります。 |
| クライアントは、委任されたアクセス許可を使用して、Microsoft Graphを使用してエージェント ID を作成します | ユーザーは、エージェント ID ブループリントまたはエージェント ID ブループリント プリンシパルの **所有者** である必要があります (いつでも追加された所有者には、このアクセス許可があります)。 クライアントには **、AgentIdentity.Create.All**、 **AgentIdentity.ReadWrite.All**、または **AgentIdentity.ReadWrite.ManagedBy** の委任されたアクセス許可が付与されている必要があります。 エージェント ID ロールは必要ありません。 |
| クライアントは、アプリケーションのアクセス許可を使用して、Microsoft Graphを使用してエージェント ID を作成します | クライアントには **AgentIdentity.Create.All** アプリケーションのアクセス許可が付与されている必要があります。 |

これらのツールを使用してエージェントのユーザー アカウントを作成するには、次のいずれかのアクセス許可が必要です。

| シナリオ | 権限が必要です |
| --- | --- |
| ユーザーは、Microsoft Entra 管理センター、Microsoft Graph、または PowerShell/CLI を使用してエージェントのユーザー アカウントを作成します | ユーザーには、 **エージェント ID 管理者** ロールまたは **ユーザー管理者ロールが** 割り当てられている必要があります。 |
| クライアントは、委任されたアクセス許可を使用して、Microsoft Graphを使用してエージェントのユーザー アカウントを作成します | ユーザーには、前の行で指定されたロールが必要です。 クライアントには **AgentIdUser.ReadWrite.All** の委任されたアクセス許可が付与されている必要があります。 |
| クライアントは、アプリケーションのアクセス許可を使用して、Microsoft Graphを使用してエージェントのユーザー アカウントを作成します | クライアントには **AgentIdUser.ReadWrite.All** アプリケーションのアクセス許可が付与されている必要があります。 |

管理者は、前の表のアクセス許可が割り当てられているユーザーとクライアントを制限することで、これらのチャネルを介してエージェント ID の作成を制御できます。 詳細については、「 [エージェント ID のアクセス許可のリファレンス」を参照してください](https://learn.microsoft.com/ja-jp/entra/agent-id/authorization-agent-id#microsoft-graph-permissions-for-agent-ids)。

### Microsoft 製品の統合

従業員は、多くの Microsoft 製品を介してエージェントを作成できます。 これらの製品は Microsoft Entra Agent ID プラットフォームと統合されており、テナントにエージェント ID ブループリント、エージェント ID、エージェントのユーザー アカウントを作成できます。 Microsoft Entra エージェント ID と統合された Microsoft 製品は次のとおりです。

| 製品 | ドキュメント |
| --- | --- |
| Microsoft Copilot Studio | [Copilot Studio のドキュメント](https://learn.microsoft.com/ja-jp/microsoft-copilot-studio/fundamentals-what-is-copilot-studio) |
| Microsoft Security Copilot | [Security Copilot のドキュメント](https://learn.microsoft.com/ja-jp/copilot/security/microsoft-security-copilot) |
| Azure AI Foundry | [Azure AI Foundry のドキュメント](https://learn.microsoft.com/ja-jp/azure/ai-foundry/what-is-azure-ai-foundry) |

管理者は、それぞれの製品の管理ツールを使用して、これらのチャネルを介してエージェント ID の作成を制御できます。

### Microsoft Entra ID 同意エクスペリエンス

ユーザーがサード パーティのエージェントに初めてサインインすると、Microsoft Entra ID サインイン エクスペリエンスによってユーザーに "同意" ページが表示されます。 同意ページでは、従業員がテナントにエージェントを追加でき、その結果、エージェント ID テンプレートのプリンシパルが作成されます。

### エージェント ID ブループリント プリンシパル

テナントにエージェント ID ブループリント プリンシパルが作成されると、そのプリンシパルには、エージェント ID とエージェントのユーザー アカウントを作成するための特権が割り当てられます。 プリンシパルは、作成した任意の ID を更新および削除することもできます。 次の表では、これらのプリンシパルに付与できるアクセス許可について説明します。

| エンティティ | 許可 | コメント |
| --- | --- | --- |
| エージェント識別子 | `AgentIdentity.CreateAsManager` (アプリケーションのアクセス許可) | このアクセス許可は、テナント内のすべてのエージェント ID ブループリント プリンシパルに自動的に付与されます。 これにより、プリンシパルはエージェント ID を作成し、作成したエージェント ID を更新および削除できます。 このアクセス許可を取り消すことはできません。 プリンシパルがエージェント ID を作成できないようにするには、無効にするか、テナントからプリンシパルを削除する必要があります。 プリンシパルは、最大 250 個のエージェント ID の作成に制限されます。 |
| エージェントのユーザー アカウント | `AgentIdUser.ReadWrite.IdentityParentedBy` (アプリケーションのアクセス許可) | Microsoft Entra ID 管理者は、エージェント ID ブループリント プリンシパルにこのアクセス許可を付与する必要があります。 付与されると、プリンシパルはエージェントのユーザー アカウントを作成し、作成したすべてのユーザーを更新および削除できます。 権限は取り消すことができます。 |

管理者は、エージェント ID ブループリント プリンシパルとしてテナントに追加されるエージェント ID ブループリントを制限することで、このチャネルを介してエージェント ID の作成を制御できます。 これらのアクセス許可の詳細については、「 [エージェント ID のアクセス許可のリファレンス」を参照してください](https://learn.microsoft.com/ja-jp/entra/agent-id/authorization-agent-id)。

### エージェント ID の作成の監査と監視

すべてのエージェント ID の作成は、Microsoft Entra 監査ログに記録されます。 監査ログを使用して、エージェント ID ブループリント、エージェント ID ブループリント プリンシパル、エージェント ID、またはエージェントのユーザー アカウントの作成に使用されたチャネルを特定できます。

| 目標 | 実装方法 | ツール/ソース |
| --- | --- | --- |
| 新しいエージェント ID の作成を検出する | エージェント ID オブジェクトの種類/作成操作に対する監査イベントのフィルター処理をサブスクライブする | [Microsoft Entra 監査ログ](https://learn.microsoft.com/ja-jp/entra/agent-id/sign-in-audit-logs-agents) |
| すべてのエージェント ID のインベントリ | Microsoft Graph API を使用して、オブジェクトの種類ごとにすべてのエージェント ID オブジェクトに対してクエリを実行します。 | Microsoft Graph のドキュメント |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/agent-identities"} -->
## Microsoft Entraでのエージェント ID の概要

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/agent-identities
- Service: entra-id / agent-id
- Article date: 2026-03-30
- Summary: エンタープライズ環境での AI エージェントのセキュリティで保護された認証と承認を可能にする、Microsoft Entra IDの特殊な ID コンストラクトのエージェント ID について説明します。

エージェント アイデンティティは、Microsoft Entra IDの特殊なサービスプリンシパルです。 これは、エージェント ID ブループリントによって作成され、偽装が承認されている ID を表します。 独自の資格情報はありません。 エージェント ID ブループリントは、ユーザーまたはテナント管理者がエージェント ID に対して対応するスコープに同意していれば、エージェント ID に代わってトークンを取得できます。 自律エージェントは、エージェント ID に代わってアプリ トークンを取得します。 ユーザー トークンを使用して呼び出された対話型エージェントは、エージェント ID に代わってユーザー トークンを取得します。

エージェント ID は、次の用途に使用できます。

- Microsoft Entra IDからエージェント トークンを要求します。 アクセス トークンのサブジェクトはエージェント ID です。
- Microsoft Entra IDによって発行された受信アクセス トークンを受信します。 アクセス トークンの対象ユーザーはエージェント ID です。
- 認証されたユーザーのMicrosoft Entra IDからユーザー トークンを要求します。 トークンのサブジェクトはユーザーですが、アクターはエージェント ID です。

### エージェント ID の構造

AI エージェントによって使用されるアカウントは、 **エージェント ID** と呼ばれます。 一般的なユーザー アカウントと同様に、エージェント ID にはいくつかの重要なコンポーネントがあります。

[Image: エージェント ID の図を示す図。]

- **識別子**。 各エージェント ID には、`id`などの`aaaaaaaa-1111-2222-3333-bbbbbbbbbb` (オブジェクト ID とも呼ばれます) があります。 Microsoft Entraは、`id`を生成し、Microsoft Entra テナント内のアカウントを一意に識別します。
- **認証情報**。 エージェント ID には、独自の資格情報がありません。 エージェント ID ブループリントを使用して、代わりにトークンを取得します。
- **表示名**. エージェント ID の表示名は、Microsoft Entra 管理センター、Azure ポータル、Teams、Outlookなど、多くのエクスペリエンスで表示されます。 これはエージェントのわかりやすい名前であり、変更できます。
- **スポンサー**。 エージェント ID にはスポンサーを設定できます。このスポンサーは、エージェントの責任を負う人間のユーザーまたはグループを記録します。 このスポンサーは、セキュリティ インシデントが発生した場合に人間に連絡するなど、さまざまな目的で使用されます。
- **ブループリント**。 すべてのエージェント ID は、エージェント ID ブループリントと呼ばれる再利用可能なテンプレートから作成されます。 エージェント ID ブループリントは、エージェントの種類を確立し、共通の種類のすべてのエージェント ID で共有されるメタデータを記録します。
- **エージェントのユーザー アカウント (省略可能)。** 一部のエージェントは、認証にMicrosoft Entraユーザー アカウントを厳密に使用する必要があるシステムへのアクセスを必要とします。 このような場合、エージェントには、エージェントのユーザー アカウントと呼ばれる 2 つ目 **のアカウント**を指定できます。 この 2 つ目のアカウントは、AI エージェントとして装飾されたMicrosoft Entra テナント内のユーザー アカウントです。 エージェント ID とは異なる `id` がありますが、エージェント ID とそのエージェントのユーザー アカウントの間には常に 1 対 1 のリレーションシップが確立されます。

これらは、セキュリティで保護された認証と承認を有効にするエージェント ID の基本的なコンポーネントです。 エージェント ID の完全なオブジェクト スキーマについては、Microsoft Graphリファレンス ドキュメントを参照してください。

### エージェント ID の承認

エージェント ID は、AI エージェントがさまざまなシステムに対して認証するために使用するプライマリ アカウントです。 オブジェクト ID やアプリ ID などの一意の識別子があり、常に同じ値を持ち、認証と承認の決定に確実に使用できます。

人間のユーザーとは異なり、AI エージェントは認証にパスワード、ショート メッセージ サービス (SMS)、パスキー、または認証アプリを使用しません。 エージェント ID には、独自の資格情報がありません。 これらは、エージェント ID ブループリントによって発行*されたフェデレーション ID 資格情報 (FIC)* を使用してのみ[認証](https://learn.microsoft.com/ja-jp/graph/api/resources/federatedidentitycredentials-overview)されます。 *ブループリントは、* エージェント ID に代わってトークンを取得するために使用する資格情報を保持します。 資格情報はエージェント ID に存在 *しません* 。 ブループリントの資格情報の種類は次のとおりです。

- フェデレーションアイデンティティ資格情報
- 証明書/暗号化キー
- クライアント シークレット

エージェントのアイデンティティは、作成されたMicrosoft Entra テナントでのみトークンを発行されます。 他のテナントのリソースや API にアクセスすることはできません。

Note

エージェント ID はシングルテナントですが、エージェント ID ブループリントはマルチテナントとして構成できます。 マルチテナント ブループリントを発行して他のテナントに追加し、そこでテナントローカル エージェント ID を作成できます。 エージェントのID自体は常にシングルテナントのままです。

### ブループリント: エージェント ID の一貫性のあるセキュリティ

エージェント ID の主な特徴は、すべてのエージェント ID が、エージェント ID ブループリントと呼ばれる再利用可能なテンプレートから作成されるということです。 ブループリントは、エージェントの "種類" を確立し、共通の種類のすべてのエージェント ID で共有されるメタデータを記録します。

[Image: エージェント ID とエージェント ID ブループリントの関係を示す図。]

ある組織が "Sales Assistant Agent" という AI エージェントを使用しているとします。エージェントが購入されているか、社内に組み込まれているかにかかわらず、エージェント ID ブループリントが組織の Microsoft Entra テナントに追加されます。 ブループリントは、次の情報をキャプチャします。

- ブループリントの名前 ("Sales Assistant Agent" など)
- ブループリントを発行した組織 ("Contoso" など)
- エージェントが提供する可能性があるロール ("営業マネージャー" や "営業担当者" など)
- エージェントに付与される Microsoft Graph アクセス許可 ("サインイン中のユーザーの予定表を読むこと" など)

組織内の多くの営業チームが AI エージェントをデプロイします。 北米販売用にエージェントがデプロイされます。 もう 1 つは南アメリカの販売に展開されています。 1 つはエンタープライズ販売用、1 つは中小企業用、もう 1 つはスタートアップ用です。 作成すると、これらの各エージェントにエージェント ID が付与されます。 各エージェントは、認証にエージェント ID を使用してタスクの実行と実行を開始します。

各エージェント ID は同じエージェント ID ブループリントを使用して作成されるため、すべてのエージェントがMicrosoft Entra 管理センターに "Sales Assistant Agents" として表示されます。 この機能を使用すると、Microsoft Entra管理者は次のようなアクションを実行できます。

- すべてのセールス アシスタント エージェントに条件付きアクセス ポリシーを適用します。
- すべての Sales Assistant エージェントを無効にします。
- すべての Sales Assistant エージェントのアクセス許可付与を取り消します。

エージェント ID ブループリントを使用すると、Microsoft Entra管理者は、ルールを設定し、エージェントの種類に基づいて操作を実行することで、エージェント ID を大規模にセキュリティで保護できます。 この機能により、組織内にデプロイされる各 AI エージェントの一貫したセキュリティが保証されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/agent-lists"} -->
## テナント内のエージェント ID の表示とフィルター処理 - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/agent-lists
- Service: entra-id / agent-id
- Article date: 2026-05-01
- Summary: Microsoft Entra 管理センターにアクセスして、エージェント ID を表示およびフィルター処理します。 検索、フィルター、列のカスタマイズにより、テナントの監視を合理化します。

Microsoft Entra 管理センターでは、エージェント ID を表示およびフィルター処理するための一元化されたインターフェイスが提供されます。 これには、テナント内の特定のエージェントの識別情報を検索、フィルタリング、並べ替え、カスタマイズする機能が備わっています。

- エージェント ID ブループリント プリンシパルを表示および管理するには、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agent-blueprint) を使用してエージェント ID ブループリントを表示および管理する方法に関するページを参照してください。
- ID なしでエージェント レジストリに登録されているエージェントを表示および管理するには、ID [なしでエージェント ID ブループリントを管理する](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agents-without-identity)方法に関するページを参照してください。

### 前提条件

Microsoft Entra テナント内のエージェント ID を表示するには、次のものが必要です。

- Microsoft Entraユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。 表示には管理者ロールは必要ありません。

Microsoft Entra テナントでエージェント ID を管理するには、次のものが必要です。

- エージェント ID 管理者またはクラウド アプリケーション管理者ロール。
- 上記のロールの有無にかかわらず、そのエージェント ID の所有者である場合は、エージェント ID を管理することもできます。

### エージェント ID の一覧を表示する

テナント内のエージェント ID を表示するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **Entra ID**&gt;**Agents**&gt;**Agent ids** に移動します。
3. 管理するエージェント ID を選択します。

このページには、組織内のすべてのエージェント ID の一覧が含まれています。 これには、 [サービス プリンシパルを使用するエージェント ID オブジェクト](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-identities) と [エージェントの両方が含まれます](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-service-principals)。

### エージェント ID を検索する

- エージェント ID を検索するには、検索ボックスに検索するエージェント ID の **名前** または **オブジェクト ID を** 入力します。
- **ブループリント アプリ ID** でエージェント ID を検索するには、**ブループリント アプリ ID** フィルターを追加します。 さまざまな条件に基づいてフィルターを使用して、一覧をさらに絞り込むことができます。

この一覧からエージェント ID を選択すると、次のような情報を表示できます。

- エージェント ID の概要を次に示します。
    - エージェント ID の名前、説明、ロゴ
    - そのエージェント ID の状態、および特定のエージェント ID を有効または無効にする機能
    - 親エージェント ID ブループリントへのリンク
- そのエージェント ID の所有者とスポンサーの一覧
- このエージェントの ID を通じて、エージェントがアクセスできるアクセス許可と Microsoft Entra のロール
- そのエージェント ID の監査ログとサインイン ログ

### 表示オプションを選択する

エージェント ID のビューをカスタマイズするには、フィルターを変更するか、エージェント ID ごとに表示する列を選択します。 既定では、すべての列が表示されるわけではありません。 使用可能なすべての列を表示し、表示されている列を編集するには、[ **列の選択** ] ボタンを選択します。 テーブル列とそのフィルター オプションは次のとおりです。

| 列名 | 説明 | 並べ替え可能 | フィルター可能 | 特記事項 |
| --- | --- | --- | --- | --- |
| **氏名** | エージェント ID の表示名 | ✓ | ✓ | プライマリ検索フィールド。クリックしてエージェント ID の詳細を表示する |
| **作成日** | エージェントが作成された日付 | ✓ | ✓ | "過去 N 日間" でフィルター処理する |
| **ステータス** | 現在の操作状態 (アクティブまたは無効) | ✓ | ✓ |  |
| **オブジェクト ID** | エージェント ID のユニーク識別子 | ✗ | ✓ |  |
| **アクセスの表示** | エージェント ID のアクセス許可への直接リンク | ✗ | ✗ | [アクセス許可] タブの [エージェントのアクセス] ウィンドウに移動します。 |
| **ブループリント アプリ ID** | このエージェント ID の「エージェント ID のブループリント」に対する一意の識別子 | ✗ | ✓ | [サービス プリンシパルを使用するエージェント](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-service-principals)の場合は空白になります |
| **所有者とスポンサー** | 特定のエージェント ID の所有者とスポンサーへの直接リンク | ✗ | ✗ |  |
| **エージェント ID を使用する** | このエージェントがエージェント ID オブジェクトを持っているか、サービス プリンシパルを利用しているかを表します。 | ✗ | ✗ | 答えが "はい" の場合は、エージェント ID オブジェクトが使用されます。 "いいえ" の場合、このエージェントはサービスプリンシパルを使用します |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/agent-oauth-protocols"} -->
## エージェントの認証プロトコル - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/agent-oauth-protocols
- Service: entra-id / agent-id
- Article date: 2025-11-04
- Summary: Microsoft Entra IDのエージェントの OAuth 2.0 プロトコルとトークン交換パターンについて説明します。

エージェントは、フェデレーション ID 資格情報 (FIC) によって有効にされた特殊なトークン交換パターンを持つ OAuth 2.0 プロトコルを使用します。 すべてのエージェント認証フローには、エージェント ID ブループリントが操作を実行するためにエージェント ID を偽装するマルチステージ トークン交換が含まれます。 この記事では、エージェントによって使用される認証プロトコルとトークン フローについて説明します。 委任シナリオ、自律操作、およびフェデレーション ID 資格情報パターンについて説明します。 Microsoftでは、Microsoft Entra ID[認証 SDK (サイドカー)](https://aka.ms/entra/sdk/agentid) などの SDK を使用することをお勧めします。これらのプロトコル手順の実装は簡単ではありません。

すべてのエージェント エンティティは機密クライアントであり、オンBehalf-Of シナリオの API としても機能します。 対話型フローは、どのエージェント エンティティの種類でもサポートされていないため、すべての認証は、ユーザー操作フローではなく、プログラムによるトークン交換を通じて行われます。

Warnung

Microsoft は、これらのプロトコルを実装するには、Microsoft.Identity.Web や Microsoft Entra ID Auth SDK（サイドカー）ライブラリなどの承認済み SDK を使用することを推奨しています。 これらのプロトコルの手動実装は複雑でエラーが発生しやすく、SDK を使用すると、セキュリティとベスト プラクティスへの準拠が保証されます。

### 前提条件

まだ慣れていない場合は、次のプロトコル に関するドキュメントを参照してください。

- [Microsoft ID プラットフォームと OAuth 2.0 On-Behalf-Of フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)
- [Microsoft ID プラットフォームおよび OAuth 2.0 クライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)

### サポートされている認可の種類

エージェント アプリケーションでサポートされている許可の種類を次に示します。

#### エージェント ID ブループリント

エージェント ID ブループリントは、偽装シナリオでセキュリティで保護されたトークンの取得を有効にする `client_credentials` をサポートします。 `jwt-bearer`グラントタイプは、委任パターンを実現する[On-Behalf Of シナリオ](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)におけるトークン交換を容易にします。 `refresh_token` 許可を使用すると、ユーザー の承認を維持する実行時間の長いプロセスをサポートする、ユーザー コンテキストを使用したバックグラウンド操作が可能になります。

#### エージェント識別子

エージェント ID は、アプリ専用の自律操作に `client_credentials` を使用し、ユーザー コンテキストなしで独立した機能を有効にし、ユーザー エージェント ID の偽装を行います。 `jwt-bearer`グラントタイプでは、[クライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)と[On-Behalf Of (OBO) フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)の両方をサポートしており、委任パターンに柔軟性を持たせます。 `refresh_token` 許可は、バックグラウンドユーザー委任操作を容易にし、エージェント ID が拡張操作全体にわたってユーザー コンテキストを維持できるようにします。

#### サポートされないフロー

- エージェント アプリケーション モデルでは、セキュリティ境界を維持するために、特定の認証パターンが明示的に除外されます。 エージェントは対話型 (`/authorize`) フローではサポートされていないため、すべての認証がプログラムによって実行されます。
- パブリック クライアント機能は使用できないため、すべてのエージェントが機密クライアントとして動作する必要があります。
- Web リダイレクト URI は、同意フロー専用 (`response_type=none`) のブループリントで構成できますが、対話型トークンの取得には使用できません。 完全リダイレクト URI 機能は、クライアント アプリケーションで構成されます。

### コア プロトコル パターン

エージェントは、次の 3 つの主要モードで動作できます。

- Microsoft Entra IDの通常のユーザーに代わって動作するエージェント (対話型エージェント)。 これは、通常の On-Behalf-Of フローです。
- エージェント (自律型) 用に作成されたサービス プリンシパルを使用して、自身の代理で動作するエージェント。
- そのエージェント専用に作成されたユーザー プリンシパルを使用して、自身の代理で動作するエージェント (独自のメールボックスを持つエージェントなど)。

### マネージド ID の統合

マネージド ID は、推奨される資格情報の種類です。 この構成では、マネージド ID トークンは親エージェント ID ブループリントの資格情報として機能し、標準の MSI プロトコルは資格情報の取得に適用されます。 この統合により、エージェント ID は、資格情報の自動ローテーションやセキュリティで保護されたストレージなど、MSI のセキュリティと管理のすべての利点を受け取ることができます。

Warnung

セキュリティ リスクのために、エージェント ID ブループリントの運用環境では、クライアント シークレットをクライアント資格情報として使用しないでください。 代わりに、マネージド ID またはクライアント証明書を使用 [するフェデレーション ID 資格情報 (FIC)](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-config-app-trust-managed-identity) などのより安全な認証方法を使用してください。 これらの方法により、アプリケーション構成内に機密性の高いシークレットを直接格納する必要がなくなり、セキュリティが強化されます。

### Oauth プロトコル

次の 3 つのエージェント OAuth フローがあります。

[Image: エージェントの oauth フローの図を示す図。]

- [エージェントの On-Behalf-Of フロー](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-on-behalf-of-oauth-flow): 一般ユーザーの代理として活動するエージェント (インタラクティブ エージェント)。
- [自律的なアプリ フロー](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-autonomous-app-oauth-flow): アプリのみの操作により、エージェント ID はユーザー コンテキストなしで自律的に動作できます。
- [エージェントのユーザー アカウント フロー](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-user-oauth-flow): エージェント専用に作成されたユーザー プリンシパルを使用して、独自の代理で動作するエージェント。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/agent-on-behalf-of-oauth-flow"} -->
## エージェント OAuth フロー - On-Behalf-Of フロー - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/agent-on-behalf-of-oauth-flow
- Service: entra-id / agent-id
- Article date: 2025-11-04
- Summary: エージェント ID ブループリントとエージェント ID を含む OAuth 2.0 On-Behalf-Of フローを使用して、サインインしているユーザーに代わってエージェント アプリケーションが動作する方法について説明します。

通常のサインインしているユーザーに代わって動作するエージェント (エージェント ID ブループリント) は、標準の OAuth 2.0 プロトコルとそのすべての機能を使用します。 ユーザー委任を使用すると、エージェント固有の偽装を含む標準の OAuth 2.0 On-Behalf-Of フローを使用して、サインインしているユーザーに代わってエージェント ID を操作できます。 エージェント ID には、OBO アクセスに必要な委任されたアクセス許可が割り当てられます。 データにアクセスするには、ユーザーからの同意が必要です。

エージェントには、Microsoft Entra ID リソース (API) アプリケーションの機能があり、(OAuth2Permissions、AppURI) に必要な API 属性がサポートされています。 エージェント ID ブループリントでは、対話型承認 (`/authorize`) フローを直接開始することはできません。 クライアント アプリケーションからユーザー トークンを受け取り、OBO トークン交換を実行する必要があります。 Web リダイレクト URI は、同意フロー専用 (`response_type=none`) のブループリントで構成できますが、アプリ登録のリダイレクト URI と比較して機能が制限されています。

Important

親ブループリントと同様に、子エージェント ID は対話型の `/authorize` フローを開始できません。 その結果、ユーザーは対話形式で同意を付与できません (子 ID に対してこれを試みると、エラー `AADSTS82014`が返されます)。 代わりに、親エージェント ID ブループリントで継承可能なアクセス許可を構成することで、必要な委任されたアクセス許可を事前に認証する必要があります。 管理者がブループリントでこれらのアクセス許可に対する同意を実際に付与していることを確認します。 その後、子エージェント ID は、対話型の同意プロンプトをトリガーすることなく、これらのスコープを継承します。 詳細なガイダンスについては、「 [エージェント ID ブループリントの継承可能なアクセス許可を構成する」を](https://learn.microsoft.com/ja-jp/entra/agent-id/configure-inheritable-permissions-blueprints)参照してください。

Warnung

Microsoft は、これらのプロトコルを実装するには、Microsoft.Identity.Web や Microsoft Entra ID Auth SDK（サイドカー）ライブラリなどの承認済み SDK を使用することを推奨しています。 これらのプロトコルの手動実装は複雑でエラーが発生しやすく、SDK を使用すると、セキュリティとベスト プラクティスへの準拠が保証されます。

### マネージド ID の統合

マネージド ID は、推奨される資格情報の種類です。 この構成では、マネージド ID トークンは親エージェント ID ブループリントの資格情報として機能し、標準の MSI プロトコルは資格情報の取得に適用されます。 この統合により、エージェント ID は、資格情報の自動ローテーションやセキュリティで保護されたストレージなど、MSI のセキュリティと管理のすべての利点を受け取ることができます。

### プロトコルの手順

エージェントは、対話型 (`/authorize`) フローではサポートされていません。 サポートされている許可の種類は、 `client_credential`、 `jwt-bearer`、および `refresh_token`です。 このフローには、エージェント ID ブループリント、エージェント ID、およびクライアント資格情報が含まれます。 クライアント資格情報には、クライアント シークレット、クライアント証明書、またはフェデレーション ID 資格情報 (FIC) を指定できます。 可能であれば、マネージド ID を使用して FIC を取得します。

[Image: エージェントの代理トークン取得フローの図を示す図。]

1. ユーザーはクライアントで認証を行い、ユーザー アクセス トークン (クライアント トークン Tc) を取得します。
2. クライアントは、ユーザーの代わりに動作するように、ユーザー アクセス トークン (Tc) をエージェント ID ブループリントに送信します。 これは、エージェント ID ブループリントの OBO 交換に使用されるトークンです。
3. エージェント ID ブループリントは、クライアント資格情報 (シークレット、証明書、またはマネージド ID トークン (TUAMI)) を提示して、交換トークンを要求します。 この例では、マネージド ID を FIC として使用します。 Microsoft Entra IDはトークン T1 をエージェントに返します。

    Warnung

    セキュリティ リスクのために、エージェント ID ブループリントの運用環境では、クライアント シークレットをクライアント資格情報として使用しないでください。 代わりに、マネージド ID またはクライアント証明書を使用 [するフェデレーション ID 資格情報 (FIC)](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-config-app-trust-managed-identity) などのより安全な認証方法を使用してください。 これらの方法により、アプリケーション構成内に機密性の高いシークレットを直接格納する必要がなくなり、セキュリティが強化されます。

    ```
    POST /oauth2/v2.0/token
    Content-Type: application/x-www-form-urlencoded
    
    client_id=AgentBlueprint
    &scope=api://AzureADTokenExchange/.default
    &fmi_path=AgentIdentity
    &client_assertion=TUAMI
    &grant_type=client_credentials
    ```

    - `fmi_path`: エージェント ID のクライアント ID (アプリ ID)。 このパラメーターは、トークン交換中にブループリントが偽装しているどの子エージェント ID であるかを Microsoft Entra ID に示します。

    TUAMI は、ユーザー割り当てマネージド ID (UAMI) のマネージド ID トークンです。 この手順では T1 が返されます。
4. エージェント ID (エージェント ID ブループリントの子) は、OBO トークン交換要求を送信します。 この要求には、T1 とユーザー アクセス トークン Tc の両方が含まれます。 `client_id`ブループリント (前の手順) からここで**エージェント ID** に切り替わるのは、エージェント ID が OBO 交換を実行するエンティティであるためです。

    ```
    POST /oauth2/v2.0/token
    Content-Type: application/x-www-form-urlencoded
    
    client_id=AgentIdentity
    &scope=https://resource.example.com/scope1
    &client_assertion_type=urn:ietf:params:oauth:client-assertion-type:jwt-bearer
    &client_assertion={T1}
    &grant_type=urn:ietf:params:oauth:grant-type:jwt-bearer
    &assertion={Tc(aud=AgentIdentity Blueprint, oid=User)}
    &requested_token_use=on_behalf_of
    ```
5. Microsoft Entra IDは、T1 と Tc の両方を検証した後、リソース トークンを返します。 次の対象ユーザーとリンケージの要件が適用されます。

    - Tc (aud) == エージェント ID ブループリント クライアント ID。 ユーザー アサーションはブループリントの対象にする必要があります。別のリソース (たとえば、Microsoft Graph) の対象トークンは、`AADSTS50013`で拒否されます。
    - T1 は `scope=api://AzureADTokenExchange/.default`で取得されるため、その `aud` はブループリントではなくトークン交換リソースです。 Microsoft Entra IDは、T1 がブループリントにバインドされていること (その`azp`がブループリント)、T1 の`sub` (FMI パス) が交換を実行する子エージェント ID に解決されることを検証します。

#### シーケンス図

次のシーケンス図は、OBO フローを示しています

[Image: エージェントの代理トークン取得フローのトークン シーケンスを示す図。]

#### 更新トークンのサポート

更新トークンは、非同期シナリオとバックグラウンド プロセスに使用できます。

```
POST /oauth2/v2.0/token
Content-Type: application/x-www-form-urlencoded

client_id=AgentIdentity
&scope=https://resource.example.com/scope1
&client_assertion_type=urn:ietf:params:oauth:client-assertion-type:jwt-bearer
&client_assertion={T1}
&grant_type=refresh_token
&refresh_token={AgentIdentityRefreshToken}
```

#### アクセス許可の継承

`InheritDelegatedPermissions` プロパティが有効になっている場合、エージェント ID は親エージェント ID ブループリントから委任されたアクセス許可を継承できます。 この継承メカニズムは、エージェント ID が親アプリケーションに既に付与されているアクセス許可を使用できるようにすることで、マルチインスタンス シナリオの同意の複雑さを軽減します。 継承機能は、FIC 偽装が使用されている場合に特に適用され、複数のインスタンス間で効率的なアクセス許可管理が可能になります。 ただし、継承はテナントの境界内でのみ機能し、権限の範囲が適切な組織の範囲内に制限されることを保証します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/agent-owners-sponsors-managers"} -->
## Microsoft Entra エージェント IDの管理関係 (所有者、スポンサー、マネージャー) - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/agent-owners-sponsors-managers
- Service: entra-id / agent-id
- Article date: 2026-04-16
- Summary: Microsoft Entraのエージェントの管理モデルについて説明します。これには、セキュリティで保護された運用、ビジネスアカウンタビリティ、コンプライアンスの監視を維持するための所有者、スポンサー、マネージャーの役割が含まれます。

Microsoft Entra エージェント IDでは、技術的な管理とビジネスアカウンタビリティを分離し、過剰なアクセス許可なしで運用管理と監視を確保する管理モデルが導入されています。 このドキュメントでは、Microsoft Entra エージェント ID ID の種類の管理関係について説明します。 このガイダンスは、 [エージェント ID](https://learn.microsoft.com/ja-jp/graph/api/resources/agentidentity?view=graph-rest-beta&preserve-view=true)、 [エージェント ID ブループリント](https://learn.microsoft.com/ja-jp/graph/api/resources/agentidentityblueprint?view=graph-rest-beta&preserve-view=true)、 [エージェント ID ブループリント プリンシパル](https://learn.microsoft.com/ja-jp/graph/api/resources/agentidentityblueprintprincipal?view=graph-rest-beta&preserve-view=true)、エージェント [のユーザー アカウント](https://learn.microsoft.com/ja-jp/graph/api/resources/agentuser?view=graph-rest-beta&preserve-view=true)に適用されます。 この記事では、所有者、スポンサー、マネージャー、およびセキュリティで保護された運用を維持する上でのその重要性について説明します。

エージェント ID で使用できる管理リレーションシップは次のとおりです。

- **所有者**: エージェント ID ブループリントとエージェント ID の運用管理を担当する技術管理者 (セットアップ、構成、資格情報の管理など)。
- **スポンサー**: ビジネス担当者は、技術的な管理アクセスなしで、アクセス レビューやエージェントの保持など、エージェントの目的とライフサイクルの決定に責任を負います。 エージェント ID とエージェント ID ブループリントごとに少なくとも 1 つのスポンサーが必要です。
- **マネージャー**: 組織の階層内のエージェントを担当し、レポート エージェントのアクセス パッケージを要求できるユーザー。

これらの管理リレーションシップは、エージェント ID オブジェクトごとに構成する必要があり、Microsoft Entraのロール ベースのアクセス コントロール (RBAC) の役割、例えばエージェント ID 管理者によって付与される管理者権限とは別個です。

### 所有者

所有者は通常、エージェントの技術管理者として機能し、運用面と構成面を処理します。 個々のユーザー (ゲスト ユーザーを含む) とサービス プリンシパルを所有者として割り当てることができます。 グループは所有者としてサポートされていません。 所有者としてのサービス プリンシパルにより、エージェント ID の自動管理が可能になります。 所有者は、すべてのエージェント ID オブジェクトに対して省略可能です。

#### 所有者の責任

所有者は、認証プロパティのように、スポンサーができないプロパティを変更できます。 所有者は、エージェント ID の他の所有者とスポンサーを追加または更新することもできます。 スポンサーと同様に、不要になったエージェント ID を無効にしたり削除したりすることもできます。 スポンサーとは異なり、所有者は無効になっているエージェント ID を再度有効にしたり、論理的に削除された ID を復元したり、ID をハード削除したりすることができます。

#### 所有者のアクセスとアクセス許可

所有者は、割り当てられたエージェントIDの設計図またはエージェントIDに基づく管理特権を持っています。 設定の編集、資格情報の管理、構成の変更、さらに所有者の割り当てを行うことができます。

エージェント ID ブループリントまたはエージェント ID ブループリント プリンシパルの所有者は、エージェント ID 管理者またはエージェント ID 開発者ロールを必要とせずに、委任されたアクセス許可を使用してそのブループリントからエージェント ID を作成することもできます。 呼び出し元のアプリケーションには、 `AgentIdentity.Create.All`、 `AgentIdentity.ReadWrite.All`、または `AgentIdentity.ReadWrite.ManagedBy`のいずれかの委任されたアクセス許可が付与されている必要があります。

#### 所有者の一般的なペルソナ

所有者は、通常、アプリケーション ID を管理するための技術的な知識を持つ開発者または IT プロフェッショナルです。 エージェント作成者、技術アプリケーション所有者、または重要なエージェントの IT 管理者である可能性があります。 バックアップ 対象範囲には複数の所有者を割り当てることができます。

他の管理サービスで、ユーザーの介入なしに特定のエージェント ID を変更または削除する機能が必要な場合は、サービス プリンシパルを所有者として設定することもできます。

### スポンサー

スポンサーは、エージェントにビジネスアカウンタビリティを提供し、技術的な管理アクセスなしでライフサイクルの決定を行います。 エージェントのビジネス目的を理解し、エージェントがまだ必要かアクセスが必要かを判断できます。 スポンサーは、エージェント ID のブループリントとエージェント ID に必要であり、すべてのエージェントに指定されたビジネス所有者がいることを確認します。

スポンサーの従業員が移動または退職したときに、後継者を確保するために、スポンサープランを維持する必要があります。 ユーザー (ゲスト ユーザーを含む) とグループをスポンサーとして割り当てることができます。 グループが割り当てられると、グループのすべてのメンバーは、エージェント ID オブジェクトに対するスポンサー権限を持ちます。 すべてのグループの種類がスポンサーとしてサポートされているわけではありません。 次の種類のグループを使用できます。

- 動的メンバーシップ グループ (セキュリティまたはMicrosoft 365)
- 割り当てられたメンバーシップ グループ (Microsoft 365)

次のグループの種類はスポンサーとして許可されていません。

- ロール割り当て可能なグループ (セキュリティまたはMicrosoft 365)
- 割り当てられたメンバーシップ グループ (セキュリティ)

#### スポンサーの責任

スポンサーは、ビジネス ニーズに基づいて、更新、延長、削除など、エージェントのライフサイクルに関する決定を行います。 エージェントに代わってアクセス パッケージを要求し、アクセス要求の業務上の正当な理由を提供します。 セキュリティ インシデント中に、スポンサーはエージェントの動作が期待されるかどうかを判断し、中断やアクセス許可の調整を含む適切な対応を承認する場合があります。

#### スポンサーのアクセスとアクセス許可

スポンサーは、管理アクセス許可が制限された最小特権で動作します。 エージェント ブループリントまたはエージェント ID のアプリケーション設定を変更することはできません。 アクセスは、非破壊的ライフサイクル操作 (エージェント ID の無効化、ID のスポンサーの変更、論理的な削除) に制限されます。

スポンサーは、エージェントのブループリントまたは ID を再度有効にしたり、復元したりすることはできません。 スポンサーが誤ってリソースを無効または削除した場合は、リソース所有者または管理者に連絡して回復する必要があります。

#### 一般的なペルソナを支援する

スポンサーは、通常、ビジネスオーナー、製品マネージャー、チームリーダー、またはエージェントの目的を理解している利害関係者です。 非公開のエージェントの場合、作成者はスポンサーとして機能することが多いです。 公開されたエージェントの場合、スポンサーは通常、エージェントを使用するチームから来ます。

#### エージェント ID スポンサーとエージェントのユーザー アカウント スポンサー

Microsoft エージェント ID では、エージェントの ID、ブループリント、ブループリント プリンシパルのすべてにスポンサーが関連付けられている場合があります。 さらに、エージェントは、ユーザー指向サービスにアクセスするために [エージェントのユーザー アカウント](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-users) を作成できます。 Entra ユーザーにはスポンサー関係がありますが、ユーザー アカウントのスポンサーと、エージェント ID、ブループリント、またはブループリント プリンシパルのスポンサーとの間には違いがあります。

ユーザーのスポンサー関係は、主に [B2B ゲストのスポンサー](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-sponsors)を対象としています。 スポンサー付きユーザーに変更を加える権限はありませんが、ユーザーに代わってアクセスを要求することができ、承認フローに関与する可能性があります。 これに対し、エージェント ID、ブループリント、ブループリント プリンシパルのスポンサーは、これらの ID を直接管理するためのアクセスが制限されており、ライフサイクル ワークフローでアクセスを要求したり、承認を与えたりすることもできます。

エージェント ID オブジェクトとエージェント ユーザー アカウントの両方によってエージェントが表される場合は、エージェント ID スポンサーをエージェントを担当するプライマリ ユーザーまたはグループとして維持することをお勧めします。

関連付けられているユーザー アカウントに対して、エージェント ID とは異なるアクセスまたは承認が必要な場合、各オブジェクトのスポンサーは、スポンサーの ID に代わって [アクセス パッケージを要求](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-request-access) できます。 エージェントのユーザー アカウントにスポンサーを設定する必要がある場合は、必要に応じてエージェント ID とエージェントのユーザー アカウントの両方に適切なアクセスを要求できるように、両方のオブジェクトで同じユーザーまたはグループをスポンサーとして設定する必要があります。

| - | エージェント ユーザー アカウントのスポンサー | エージェント ID、ブループリント、ブループリント プリンシパル スポンサー |
| --- | --- | --- |
| **許可される型** | ユーザー (ゲストを含む)、グループ (任意) | ユーザー (ゲストを含む)、グループ (動的メンバーシップ、Microsoft 365) を選択します。 ロール割り当て可能なグループはサポートされていません。 |
| **Limits** | 最大 5 人のスポンサー | 最大 100 人のスポンサー(5 グループ以下) |
| **認可** | スポンサー付きユーザーを変更するための直接承認なし | エージェント ID を削除または無効化し、そのスポンサーを変更する |
| **必須** | 必須ではない | エージェント ID とエージェント ブループリントの作成時に必要 |

### Managers

マネージャーは、組織階層内のエージェント ID を担当する個々のユーザーです。 ユーザー シナリオでアクティブなエージェントの場合は、エージェントのユーザー アカウントにマネージャーを設定することを検討してください。 マネージャーは、エージェントのユーザー アカウントに対するアクセス パッケージを要求でき、Microsoft Entra 管理センターでは、彼らに報告するエージェントが表示されます。 マネージャーは、エージェントを変更または削除する権限を持っていません。所有者、スポンサー、または管理者は、これらのアクションを実行する必要があります。

### 要件と制約

管理モデルでは、特定の要件と制約が適用され、効果的な監視とアカウンタビリティが確保されます。

#### 作成のための条件

エージェント ID またはエージェント ブループリントを作成する場合は、スポンサーが必要です。 作成時に、エージェント ID ブループリントの主要者はスポンサー要件が免除されます。 所有者とマネージャーは常に省略可能です。

#### 割り当てポリシー

アプリケーションとユーザー コンテキストの両方が存在する委任された作成要求の場合、スポンサーが明示的に指定されていない場合、呼び出し元ユーザーは自動的にスポンサーになります。 ただし、作成時に 1 つ以上の他のスポンサーが指定された場合、呼び出し元ユーザーは自動的に追加されません。 エージェント ID 管理者ロールを持つユーザーは、作成時に自動的にスポンサーになりません。 これにより、管理者が個々のエージェントに直接責任を負うという意図せずに過剰な負荷が発生することを回避できます。

アプリ専用の作成要求の場合、作成サービスでは、1 人以上のユーザーまたは サポートされているグループ をスポンサーとして設定する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/agent-registry-convergence"} -->
## Microsoft Agent 365 とのエージェント レジストリの統合

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/agent-registry-convergence
- Service: entra-id / agent-id
- Article date: 2026-04-05
- Summary: Microsoft Agent 365 でのエージェント レジストリ エクスペリエンスの収束方法、Microsoft Entra エージェント IDの変更の意味、および組織内のすべてのエージェントを表示する方法について説明します。

組織は、Microsoft プラットフォーム、パートナー エコシステム、カスタム アプリケーション全体で AI エージェントを急速に導入しています。 エージェントの数と機能が増えるにつれて、組織はそれらを観察、管理、セキュリティで保護するための一元的な方法が必要です。

[Microsoft Agent 365](https://learn.microsoft.com/ja-jp/microsoft-agent-365/overview) は、AI エージェントMicrosoftのコントロール プレーンです。 次のように組織を支援します:

- 企業全体のエージェントを観察します。
- エージェントがシステム、データ、ツールにアクセスする方法を管理します。
- Microsoft ID とセキュリティ機能を使用してエージェントをセキュリティで保護します。

Microsoft Agent 365 内の主要な機能は、[agent レジストリ](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/agent-registry)であり、Microsoftエージェントと非Microsoft エージェントの両方を含む、組織内で運用されているすべてのエージェントの統合インベントリを提供します。

### レジストリの収束

以前は、エージェントの可視性は、Microsoft EntraやMicrosoft 365 管理センターなど、複数のポータルに表示されました。 お客様からのフィードバックに基づいて、Microsoftはエージェント 365 の下でこれらのレジストリ エクスペリエンスを集約し、よりシンプルで一貫性のある管理エクスペリエンスを提供しています。

この変更により、次の操作が行われます。

- **エージェント 365** は、エージェントの統合レジストリおよびコントロール プレーンになります。
- **Microsoft Entra** は引き続きエージェント ID を介して ID 基盤を提供します。

このアプローチにより、エージェントを検出して管理しながら、エージェント ID とアクセス制御にMicrosoft Entraを引き続き使用できます。

### よく寄せられる質問

#### 現在どのレジストリを使用する必要がありますか?

Agent 365 を使用して、組織内のすべてのエージェントを検出して管理し、運用アクティビティを監視します。 Microsoft Entraを使用して、エージェント ID (エージェント ID) を管理し、ID ガバナンスと条件付きアクセス ポリシーを適用し、ID 関連のセキュリティシグナルを監視します。

#### Microsoft Entra エージェント レジストリの一部として発表された機能はどうなりますか?

Microsoft Entra エージェント レジストリで導入された機能は引き続き存在します。 具体的な内容は次のとおりです。

- エージェント ID 機能は、引き続きMicrosoft Entra エージェント IDの一部です。
- エージェント登録 API は引き続きサポートされます。
- エージェントの ID ガバナンスとセキュリティ制御は変更されません。

この変更により、お客様が組織内のすべてのエージェントを見て管理する場所が簡素化されますが、Microsoft Entraは ID とアクセスの管理を引き続き提供します。

#### Microsoft Entra 管理センターに同じエージェント インベントリが表示されますか?

Microsoft Entra 管理センターでは、エージェントの ID とアクセスの管理に重点を置いています。 ID 管理者は、エージェントを表示および管理するためのMicrosoft Entra エージェント IDを持つエージェントを表示できます。 エージェント ID がMicrosoft Entraされていないエージェントを含む包括的なエージェント インベントリは、エージェント 365 で使用できます。

#### Microsoft Entra 管理センターで何を管理できますか?

Microsoft Entra 管理センターでは、管理者は次のことができます。

- Microsoft Entra エージェント ID を持つエージェントを表示します。
- エージェント ID、ブループリント、およびアクセス許可を管理します。
- 条件付きアクセス、ID ガバナンス、およびネットワーク セキュリティ制御を適用します。
- ID 関連のセキュリティ シグナルを監視します。

#### ID 管理者の場合、Microsoft Agent 365 にアクセスする必要がある理由

ID 管理者は、必要に応じて、エージェント 365 をMicrosoft 365 管理センター経由して組織内のすべてのエージェントを表示しながら、エージェント ID、アクセス ポリシー、ID ガバナンス制御の管理に引き続きMicrosoft Entra 管理センターを使用できます。

#### エージェント 365 でエージェントを表示するには、別のロールが必要ですか?

エージェント 365 とMicrosoft Entra エージェント IDのすべてのエージェントを表示するには、ユーザーには [AI 閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#ai-reader) ロールが必要です。 このロールは、アクセスの監視またはレポートを表示する必要があるユーザーを対象としています。

[エージェント ID を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-administrator)管理または変更するには、エージェント ID 管理者ロールが必要です。

#### ライセンスは必要ですか?

Microsoft 365 管理センター内のすべてのエージェントを表示するには、特定のライセンスは必要ありません。 管理者は、インベントリ ビューにアクセスするために、AI 閲覧者 (推奨される最小特権ロール) や AI 管理者などの適切なロールのみが必要です。

条件付きアクセスや ID ガバナンス ポリシーなどのエージェントにセキュリティとガバナンスの制御を適用するには、[Microsoft Entra エージェント ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing#microsoft-entra-agent-id) の適切なライセンスが必要です。

#### エージェント ID ブループリントをエージェント 365 レジストリに表示するにはどうすればよいですか?

エージェント ID ブループリントを作成したら、[Agent 365 レジストリ](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/agent-registry)に登録して、管理者がMicrosoft 365 管理センターからエージェントを検出、管理、管理できるようにします。 一部のシナリオでは、以前に作成したエージェント ID ブループリントがレジストリに表示されない場合があります。 すべてのエージェント ID ブループリントが確実に登録されるようにするには、「 [エージェント 365 レジストリにエージェントを登録](https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint#register-agents-in-the-agent-365-registry)する」を参照してください。

### エージェント インベントリ全体を表示する方法

組織内のエージェントの完全なインベントリを表示するには:

1. [Microsoft 365 管理センター](https://admin.microsoft.com)に少なくとも[AI リーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#ai-reader)として移動します。
2. ナビゲーション メニューから **[エージェント]** を選択します。
3. テナント内のエージェントの包括的な一覧を表示するには、[ **すべての** エージェント] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/agent-service-principals"} -->
## エージェント ID、サービス プリンシパル、およびアプリケーション - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/agent-service-principals
- Service: entra-id / agent-id
- Article date: 2026-04-30
- Summary: Microsoft Entra エージェント IDのエージェント サービス プリンシパルと、認証、アクセス許可、ライフサイクル管理における従来のサービス プリンシパルとの違いについて説明します。

エージェント ID は、アプリケーションがMicrosoft Entra全体で使用するのと同じサービス プリンシパル インフラストラクチャ上に構築されますが、オブジェクトの種類は異なります。 Standard サービス プリンシパルは、静的で確定的なワークロード用に設計されており、独自の資格情報を直接使用して動作します。 エージェント ID は、ブループリントが各エージェント ID に代わってトークンを取得する委任モデルを追加し、一対多のブループリントリレーションシップをサポートし、割り当てられたスポンサーを必要とし、エージェント固有の監査エントリを生成します。

この記事では、次の概念について説明します。

- エージェント アプリ モデルのサービス プリンシパルの種類
- エージェント ID ブループリントとそれらがどのように関連しているか
- 標準アプリケーション サービス プリンシパルとそれらの違い

### エージェント ID 設計図の主例

エージェント ID ブループリント プリンシパルは、エージェント ID ブループリントがテナントでインスタンス化されるときに自動的に作成されます。 これらのサービス プリンシパルは、テナントのディレクトリ内のエージェント ID ブループリントのランタイム *表現* を提供し、インスタンスの作成やライフサイクル操作の管理などの操作をエージェント ID ブループリントで実行できるようにします。

作成プロセスには、 `AgentIdentity.Create` や `ServicePrincipal.Manage.OwnedBy`などのアクセス許可を必要とする同意操作が含まれます。 エージェント ID ブループリント プリンシパルを使用すると、エージェント ID ブループリントは、エージェント ID の作成と管理に必要なMicrosoft Graph呼び出しのアプリ専用トークンを取得できます。 エージェント ブループリント自体は、Microsoft Graph操作のトークンを直接取得できないため、このプロセスは不可欠です。

### サービス プリンシパルとしてのエージェント ID

エージェント ID は、新しい "エージェント" サブタイプ分類を使用してシングルテナント サービス プリンシパルとしてモデル化されます。 この設計では、エージェント固有の動作と制約を追加しながら、既存のMicrosoft Entra ID サービス プリンシパル インフラストラクチャを使用します。

エージェント ID は、ParentID リレーションシップを通じて、親エージェント ID ブループリントからプロトコル プロパティを継承します。 エージェント ID は、独立して動作する標準のサービス プリンシパルとは異なり、偽装操作とトークン交換操作のために親エージェント ID ブループリントを必要とします。

エージェントの操作に対してトークンが発行されたときに、エージェント ID に直接アクセス許可を付与し、サインイン ログに表示できます。 これらは、エージェントのアクセス許可とアクセスを管理するときに顧客が理由とする主要な ID として機能します。

エージェント ID ブループリント プリンシパルは、アプリ専用トークンと適切なロールを持つMicrosoft Graph呼び出しを使用して、エージェント ID サービス プリンシパルを作成します。 作成プロセスによって親子関係が確立され、偽装に必要なフェデレーション ID 資格情報 (FIC) リレーションシップが構成されます。

### アプリケーション サービス プリンシパルを使用して構築されたエージェント

Microsoft エージェント ID プラットフォームが導入される前は、以前のバージョンのMicrosoft Copilot StudioやAzure AI Foundryを含む一部のMicrosoft アプリケーションが、エージェントをセキュリティで保護するために標準アプリケーション サービス プリンシパルを使用していました。 これらのサービス プリンシパルは、Microsoft Entra 管理センター内のエージェント ID オブジェクトと共に表示されます。

すべての **エージェント ID** の一覧をフィルター処理することで、それらを区別できます。 標準サービス プリンシパルを使用するエージェントは、他のアプリケーション サービス プリンシパルと同じように動作し、この記事で説明するエージェント固有の動作はありません。 フィルタリング オプションについては、「 [表示オプションの選択](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-lists#select-viewing-options)」を参照してください。

### 主な違い

次のセクションでは、エージェント アプリ モデルと標準アプリケーション サービス プリンシパルの違いについて説明します。

#### 偽装モデル

Standard サービス プリンシパルは、独自の資格情報と ID を使用して動作します。 エージェント サービス プリンシパルは偽装モデルを使用します。エージェント ID ブループリントは各エージェント ID に代わってトークンを取得するため、ブループリントが実際のトークン交換を実行した場合でも、エージェント ID は結果のトークンと監査ログにクライアントとして表示されます。

この分離は、エージェント ID がアクセス許可と監査 ID を保持している間、ブループリントが認証資格情報を保持します。 ブループリントの資格情報の侵害は、その下にあるすべてのエージェント ID に影響します。そのため、ブループリントの数はセキュリティ境界の決定です。

#### 複数インスタンスのリレーションシップ

標準アプリケーションには、アプリケーションとサービス プリンシパルの間に 1 対 1 のリレーションシップがあります。 エージェント アプリ モデルは 1 対多です。1 つのエージェント ID ブループリントで、テナント内またはテナント間で複数のエージェント ID サービス プリンシパルを生成できます。 各エージェント ID は、ブループリントからプロトコル プロパティを提供しますが、ダウンストリーム リソースに対する独自のアクセス許可を保持できます。

#### 資格情報の管理

Standard サービス プリンシパルは、独自の資格情報 (証明書、シークレット、またはマネージド ID) を管理し、それらを使用してトークンを直接取得します。 エージェント ID は資格情報を管理しません。 ブループリントは、すべての資格情報を保持し、それらを使用して各エージェント ID を偽装します。

マネージド ID はブループリントでサポートされている資格情報の種類 (Azure で実行されているエージェントの最も安全なオプション) ですが、これらは資格情報*ブループリント*であり、エージェント ID 自体の代わりではありません。

#### アクセス許可とロールの割り当て

エージェント サービス プリンシパルは、アプリケーションのアクセス許可 (自律操作用) と委任されたアクセス許可 (対話型の代理操作の場合) の両方をサポートします。 アクセス許可は、エージェント ID に直接割り当てたり、ブループリントから継承したりできます。

`InheritDelegatedPermissions`が有効になっている場合、エージェント ID はブループリントから委任されたアクセス許可を継承します。これにより、多くのエージェント ID の同意管理が簡略化されます。 共有ベースラインには継承されたアクセス許可を使用し、ロール固有のアクセスには直接割り当てを使用します。

エージェント ID は、標準のサービス プリンシパルと同じように、Azure の RBAC ロールと Microsoft Entra の組み込みロールを割り当てることができます。 エージェント識別子ブループリントには、Azure の RBAC ロールを割り当てることはできません。

#### 監査とログ記録

エージェント サービス プリンシパルは、監査ログとサインイン レポートで個別の ID を保持します。 エージェント ID が操作を実行すると、ログにその操作が動作するクライアントとして表示され、ブループリントとの関係が示されます。 サインイン ログは、エージェント ID ブループリント、エージェント ID、エージェントのユーザー アカウントを区別し、資格情報ソース、動作 ID、各操作のサブジェクトを明確に識別できるようにします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/agent-token-claims"} -->
## エージェントIDのトークン請求参照 - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/agent-token-claims
- Service: entra-id / agent-id
- Article date: 2025-11-04
- Summary: 認証および承認フロー中にエンティティの種類、リレーションシップ、ロールを識別するために、Microsoft Entraのエージェント アプリケーションによって使用される特殊なトークン要求について説明します。

エージェントは認証および認可フロー中に異なるエンティティタイプとその関係を特定するために、専門的なトークンクレームを使用します。 これらの請求により、代理店の運営における適切な帰属、保険証券評価、監査記録が可能になります。 本記事では、エージェントアプリケーションにおけるトークン請求について概説し、トークンがどのようにエージェントエンティティとその認証フローにおける役割を識別するかを詳述します。

エージェント ID を使用するクライアントは、リソース サーバーで使用するために発行されたアクセス トークンを不透明として扱い、解析を試みないことを期待されます。 ただし、エージェントに発行されたアクセス トークンを受け取るリソース サーバーは、トークンを解析して検証し、承認のために要求を抽出する必要があります。

### コアトークンクレームタイプ

リソース アクセスに使用される ID に対して発行されるトークンには、通常、問題をMicrosoft Entraするアクセス トークンに表示される要求が含まれます。 詳細については、 [アクセストークン請求の参考文献](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-token-claims-reference)をご覧ください。 以下の例は、エージェントに発行されたサンプルアクセストークンが自律的に動作している様子を示しています。

```json
{
  "aud": "00001111-aaaa-2222-bbbb-3333cccc4444",
  "iss": "https://sts.windows.net/00000001-0000-0ff1-ce00-000000000000/",
  "iat": 1753392285,
  "nbf": 1753392285,
  "exp": 1753421385,
  "aio": "Y2JgYGhn1nzmErKqi0vc4Fr6H22/C5/4FP+xZbZYpik8nRkp+gEA",
  "appid": "11112222-bbbb-3333-cccc-4444dddd5555",
  "appidacr": "2",
  "idp": "https://sts.windows.net/00000001-0000-0ff1-ce00-000000000000/",
  "idtyp": "app",
  "oid": "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
  "rh": "1.AAAAAQAAAAAA8Q_OAAAAAAAAADQNUfLKjbhKoLyq7E06PjYAAAAAAA.",
  "sub": "bbbbbbbb-1111-2222-3333-cccccccccccc",
  "tid": "aaaabbbb-0000-cccc-1111-dddd2222eeee",
  "uti": "m5RaaRnoFUyp2TbSCAAAAA",
  "ver": "1.0",
  "xms_act_fct": "3 9 11",
  "xms_ftd": "Z5DrW4HFOkR_Lz0M5qETa260d2-fO6seMZJ_tOwRNuc",
  "xms_idrel": "7 10",
  "xms_sub_fct": "9 3 11",
  "xms_tnt_fct": "3 9",
  "xms_par_app_azp": "30cf4c22-9985-4ef7-8756-91cc888176bd"
}
```

v2トークンでは`azp`ではなく`appid`が見えます。 どちらもエージェントIDのアプリケーションIDを指しています。

トークンには、これまでアクセストークンに発行されていないいくつかのクレームが含まれていることに気づくでしょう。 以下のオプション請求も、トークンがエージェントのアイデンティティ用であることを識別するために支持されています。 また、エージェントのアイデンティティがどのような状況で行動しているかをより明確に示します。

- `xms_tnt_fct`
- `xms_sub_fct`
- `xms_act_fct`
- `xms_par_app_azp`

| 要求名 | 説明 |
| --- | --- |
| `tid` | エージェントIDが登録されている顧客テナントのテナントID。 トークンが有効なのはテナントです。 |
| `sub` | 主体(認証対象のユーザー、サービスプリンシパル、またはエージェントの身元) |
| `oid` | 対象のオブジェクトID。 ユーザー委任シナリオのためのユーザーオブジェクトID。 アプリのみのシナリオ向けのエージェントIDサービスプリンシパルOID。 ユーザー偽装シナリオ用のエージェントのユーザー アカウント OID。 |
| `idtyp` | 対象となる法人の種類。 値は`user`,`app`です。 |
| `xms_idrel` | 主体とリソーステナントの関係。 詳細はこちら |
| `aud` | オーディエンス(エージェントがアクセスしようとしているAPI) |
| `azp` または `appid` | 認可された当事者/アクター。 エージェントIDのアプリケーションID。 監査ログにおける適切なクライアント帰属を可能にします。 |
| `scp` | 範囲。 ユーザーコンテキストトークンの権限を委譲します。 ユーザー委任とエージェントのユーザー アカウント シナリオにのみ存在します。 アプリのみのシナリオでは空または`/` |
| `xms_act_fct` | 俳優の側面が主張します。 詳細はこちら |
| `xms_sub_fct` | 主体の側面は主張しています。 詳しくはこちらをご覧ください。 |
| `xms_tnt_fct` | テナントの側面請求。 詳しくはこちらをご覧ください。 |
| `xms_par_app_azp` | 認可当事者の親申請。 詳しくはこちらをご覧ください。 |

### シナリオ別の idtyp 要求値

`idtyp`要求は、トークンサブジェクトが表すエンティティの種類を識別します。 値は、トークンを発行したエージェント フローによって異なります。

| エージェントのシナリオ | `idtyp`値 | 件名 (`sub`/`oid`) |
| --- | --- | --- |
| On-Behalf-of フロー (対話型エージェント) | `user` | エージェントが代理して行動する人間のユーザー |
| 自律アプリ フロー (アプリのみ) | `app` | エージェント ID サービス プリンシパル |
| エージェントのユーザー アカウント フロー | `user` | エージェントの独自のユーザー アカウント |

Note

`idtyp`値だけでは、人間のユーザーとエージェントのユーザー アカウントは区別されません。 これらのシナリオを区別するには、 `xms_sub_fct` 要求 (値 `13` = エージェントのユーザー アカウント) を使用します。

### xms\_idrel

`xms_idrel`請求は、トークンが発行されるエンティティとリソーステナントとのアイデンティティ関係を示します。

以下は `xms_idrel` 請求の可能な値を示します。 これは多値請求であり、複数の値を持つことができ、空間で区切られています。 値は整数で表されます。 有効な値は常に1から始まる奇数です。

| 請求額 | 説明 |
| --- | --- |
| `1` | 会員ユーザー |
| `3` | MSA会員ユーザー |
| `5` | ゲスト ユーザー |
| `7` | サービス プリンシパル |
| `9` | 装置の原理 |
| `11` | GDAPユーザー |
| `13` | SPLess アプリケーション |
| `15` | パススルー |
| `17` | プロファイルを持つネイティブアイデンティティユーザー |
| `19` | ネイティブアイデンティティユーザー |
| `21` | ネイティブアイデンティティチームの会議参加者 |
| `23` | パススルー認証済みTeams会議参加者 |
| `25` | ネイティブアイデンティティのコンテンツ共有ユーザー |
| `27` | 完全に同期したMTOメンバー |
| `29` | 弱いMTOユーザー |
| `31` | DAPユーザー |
| `33` | フェデレーテッド・マネージド・アイデンティティ |

### xms\_tnt\_fct、xms\_sub\_fct、xms\_act\_fct請求

`xms_tnt_fct`請求は、`tid`請求で識別される借主を記述します。 `xms_sub_fct`請求項と`xms_act_fct`請求項は、それぞれトークンの対象(`sub`)と行為者(`azp`または`appid`)に関する事実を記述するために用いられます。 これらの主張は、エージェントの身元やその行動についてより深い文脈を提供します。

以下はこれらの主張に関する関連する価値です。 これらの請求は多重価値を持ち、複数の値を持つことができ、間隔で区切られています。 有効な値は常に1から始まる奇数です。

| 請求額 | 説明 |
| --- | --- |
| `11` | エージェントアイデンティティ |
| `13` | AgentIDUser |

シナリオや検証ロジックに関係のない値は無視すべきです。 応募に関係のない値は無視しましょう。 これらの主張の価値の順序を決めないでください。

### xms\_par\_app\_azp

`xms_par_app_azp`請求は、認可当事者の親アプリケーション(`azp`または`appid`)を特定するために用いられます。 含まれている場合はGUIDです。 請求を使って親を特定することができます

監査のために親アプリケーションIDを記録してください。 Microsoft Entra IDサインイン ログには常に親 ID (使用可能な場合) が含まれるため、リソース サーバーでも同じ操作を行う必要があります。 承認決定に親アプリケーションIDを使うことは推奨されません。多くのエージェントが広範囲にアクセスしてしまうためです。

### シナリオごとの例

以下のセクションでは、いくつかの認証シナリオとそれぞれの関連する主張について説明します。

#### 人間のユーザーを代表して行動するエージェントのアイデンティティ

この場合、エージェントのアイデンティティは人間のユーザーを代表して行動しています。 アクセストークンには以下の主張が含まれています。

| 要求名 | 説明 |
| --- | --- |
| `tid ` | 顧客テナントのテナントID |
| `idtyp ` | `user` (主語がユーザーであることを示す) |
| `xms_idrel` | `1` (メンバーユーザーを示す場合、他にも可能性があります) |
| `azp` / `appid` | エージェント識別のアプリケーションID |
| `scp` | エージェントのアイデンティティに付与された委任権限 |
| `oid` | ユーザーのオブジェクトID |
| `aud` | トークンのリソースオーディエンス |
| `xms_act_fct` | `11` (エージェントアイデンティティ) |

#### エージェントのアイデンティティが自律的に行動する

この場合、エージェントの同一性は自身の同一性を用いて行動します。 アクセストークンには以下の主張が含まれています。

| 要求名 | 説明 |
| --- | --- |
| `tid` | 顧客テナントのテナントID |
| `idtyp` | `app` (主題が申請であることを示す) |
| `xms_idrel` | `7` (サービスプリンシパルを示す) |
| `azp` / `appid` | エージェント識別のアプリケーションID |
| `roles` | エージェント識別に付与された権限 |
| `oid` | エージェント識別のオブジェクトID |
| `xms_act_fct` | `11` (エージェントアイデンティティ) |
| `xms_sub_fct` | `11` (エージェントアイデンティティ) |
| `aud` | トークンのリソースオーディエンス |
| `scp` | 空か `/` (スコープなし)。 |

#### エージェント ID は、エージェントのユーザー アカウントを介して自律的に動作します

このシナリオでは、エージェントは、エージェント ID に関連付けられているエージェントのユーザー アカウントを使用してトークンを取得します。 アクセストークンには以下の主張が含まれています。

| 要求名 | 説明 |
| --- | --- |
| `tid` | 顧客テナントのテナントID |
| `idtyp` | `user` (主語がユーザーであることを示す) |
| `xms_idrel` | `1` (メンバーユーザーを示す場合、他にも可能性があります) |
| `azp` / `appid` | エージェント識別のアプリケーションID |
| `scp` | エージェントのアイデンティティに付与された委任権限 |
| `oid` | エージェントのユーザー アカウントのオブジェクト ID |
| `xms_act_fct` | `11` (エージェントの身分) |
| `xms_sub_fct` | `13` (エージェントのユーザー アカウント) |
| `aud` | トークンのリソースオーディエンス |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/agent-tokens"} -->
## Microsoft エージェント ID プラットフォームのトークン - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/agent-tokens
- Service: entra-id / agent-id
- Article date: 2025-11-10
- Summary: トークンの種類、要求構造、認証フローなど、Microsoft エージェント ID プラットフォームのトークンについて説明し、トークンを使用してエージェント アプリケーションとリソース間のセキュリティで保護された通信を可能にする方法について説明します。

トークンは、Microsoft エージェント ID プラットフォームでセキュリティで保護された通信と承認を可能にする基本的なセキュリティ メカニズムです。 この記事では、エージェント アプリのシナリオでトークンがどのように機能するかについて説明します。

エージェント アプリのシナリオでは、トークンは一意の要件をサポートするために拡張要求を実行します。 エージェントによって使用されるトークンには、非エージェント アプリケーション トークンとは異なり、エンティティの種類、委任リレーションシップ、およびエージェント操作に固有の承認コンテキストを識別する特殊な要求が含まれます。

これらのトークンにより、次の間のセキュリティで保護された通信が可能になります。

- エージェント アイデンティティ設計図とそのエージェント アイデンティティ
- エージェント ID とリソース API
- エージェントのユーザー アカウントと連携するサービス
- 複数のエージェント エンティティを含む複雑な委任チェーン

### トークンクレームエンティティ識別子

エージェント トークンには、認証フローに参加しているエンティティの種類とロールを識別する特殊な要求が含まれます。

- アクター ファセット要求 (`xms_act_fct`): トークン フロー内でアクションを実行するエンティティを特定します。 これらの要求により、システムは、アクセスを実際に要求しているユーザーや操作を実行しているユーザーを把握できます。
- サブジェクト ファセット要求 (`xms_sub_fct`): 操作が実行されている最終的なサブジェクトを特定します。 複雑な委任シナリオでも適切な帰属を可能にします。
- ID の種類の要求 (`idtyp`): ユーザーとアプリケーションのコンテキストを区別し、適切なポリシー アプリケーションとセキュリティの適用を有効にします。
- ID 関係要求 (`xms_idrel`): マルチテナントとゲスト アクセスのシナリオをサポートする、トークンのサブジェクトとリソース テナントの間の関係について説明します。

### トークン フロー パターン

エージェントは、それぞれ特定の運用シナリオ向けに設計された、いくつかの異なるトークン フロー パターンに参加します。 詳細については、Microsoft エージェント ID プラットフォームの [auth プロトコル](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-oauth-protocols)を参照してください。

#### ユーザー委任シナリオでのトークン

ユーザー委任シナリオでは、エージェント アプリケーションがユーザーに代わって動作できます。 このシナリオでは、トークンによってユーザー ID が保持され、エージェント ID ブループリントが動作エンティティとして識別されます。 On-Behalf-of (OBO) プロトコルでは、トークンの対象ユーザーがクライアント ID と一致する必要があります。 ただし、エージェント ID (エージェント ID) の場合、受信トークンにはエージェント ID ブループリントの対象ユーザーが含まれます。 このシナリオでは、トークンには次の重要な特性があります。

- 操作全体でユーザー ID コンテキストを維持する
- エージェント ID ブループリントに付与された委任されたアクセス許可を含める
- ユーザー レベルとアプリケーション レベルの両方でポリシー評価を有効にする
- 対話型操作とバックグラウンド操作の両方をサポートする

エージェント ID は委任されたアクセス許可を受け取ります。委任されたアクセス許可は、権限借用の使用時に親エージェント ID ブループリントから直接割り当てまたは継承できます。

#### アプリケーションのみのシナリオでのトークン

アプリケーションのみのシナリオは、エージェント ID ブループリントがユーザー コンテキストなしで自身の代わりに動作する自律的な操作を表します。

このシナリオでは、トークンには次の重要な特性があります。

- エージェントIDブループリント自体のIDを表す
- テナント管理者によって直接割り当てられたアプリケーション レベルのアクセス許可を含める
- 許可されたアクセス許可の境界内でスコープ外のアクセスを有効にします。 委任されたアクセス許可は適用されません。
- 完全に自律的なバックグラウンド操作をサポートする

#### ユーザーアカウント偽装シナリオにおけるエージェントのトークン

エージェントのユーザー アカウント偽装シナリオを使用すると、エージェントのユーザー アカウントを人間のユーザーのように動作できます。 これらのトークンは、エージェントがユーザーに似たコンテキストを必要とするが、制御された定義済みの ID を持つシナリオをサポートします。

このシナリオでは、トークンには次の重要な特性があります。

- 特殊化されたエージェントのユーザー アカウント ID を使用する
- ユーザー コンテキストの動作パターンを維持する
- スコープ付き委任されたアクセス許可を含める
- エージェント ID への明示的な割り当てを要求する

このシナリオでは、エージェント ID ブループリントによってエージェント ID が偽装され、割り当てられたエージェントのユーザー アカウントが偽装されます。 アクセスのスコープは、エージェント ID に割り当てられた委任されたアクセス許可に設定されるため、ユーザー コンテキストを使用して操作する場合でも、エージェントが付与されたアクセス許可を超えないようにします。 エージェントのユーザー アカウントは、エージェント ID に割り当てられている場合にのみ使用でき、個別に認証することはできません。

### 非エージェント API の統合

エージェントと非エージェントのOBOクライアントの両方がサブジェクトの側面を引き継ぎます。 これにより、リソース サーバーはエージェントのサブジェクトに対して適切なポリシーとログを適用できます。 また、トークンが非エージェント中継局を通過する場合でも、適切な属性を有効にします。

### Builder アプリケーションクレーム

エージェントを作成し、Microsoft Entra エージェント IDと統合するプラットフォームは、特別なトークン要求を受け取りません。 これらのプラットフォームでは、認証方法に適した標準トークン要求が使用され、特殊なエージェント要求構造には参加しません。

### テナント モデルとトークンの動作

すべてのトークンには、組織のテナントを表すテナント ID (`tid`) が含まれています。 トークンは、エージェント ID のテナント内でバインドされます。 各エージェント ID は、運用テナントをスコープとするトークンを受け取ります。 エージェント ID は、割り当てられた顧客テナントの外部のリソースにアクセスできません。 権限の継承は、親エンティティから子エンティティに直接伝達されます。

### トークンの検証

エージェント ID を使用するクライアントは、リソース サーバーで使用するために発行されたアクセス トークンを不透明として扱い、解析を試みないことを期待されます。 ただし、エージェントに発行されたアクセス トークンを受け取るリソース サーバーは、トークンを解析して検証し、承認のために要求を抽出する必要があります。

リソース サーバーは、次の方法でエージェント トークンを検証する必要があります。

- 標準の OAuth 要求 (aud、exp、iss) の検証
- エージェントの facet claim でエンティティが正しく識別されているか確認する
- トークンの種類に基づくアクセス許可の検証 (委任されたアクセス許可とアプリのみ)
- テナント境界のコンプライアンスの確保

トークン要求を使用すると、ポリシー エンジンは次の処理を行うことができます。

- エージェントと非エージェント クライアントの識別
- 異なるエージェント シナリオを区別する
- 適切な条件付きアクセス ポリシーを適用する
- 正確な監査ログを生成する

検証プロセスの例として、次のアクションを実行します。

- エージェント ID とエージェント ブループリントに対してトークンが発行されたかどうかを確認します。

    ```csharp
    HttpContext.User.GetParentAgentBlueprint()
    ```
- エージェントのユーザー アカウント ID に対してトークンが発行されたかどうかを確認します。

    ```csharp
    HttpContext.User.IsAgentUserIdentity()
    ```

これら 2 つの拡張メソッドは、 `ClaimsIdentity` と `ClaimsPrincipal`の両方に適用されます。

### 監査とログの統合

トークン要求は、包括的な監査証跡の基盤を提供します。

- `azp` は、クライアント帰属のために要求されたエージェントのIDを識別します
- `oid` は、リソース アクセス属性のサブジェクトを識別します
- `xms_act_fct`と`xms_sub_fct`は詳細なフロー分析を可能にする
- `tid` ログ内の適切なテナント コンテキストが保証されます
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/agent-user-oauth-flow"} -->
## エージェントのユーザー アカウント偽装プロトコル - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/agent-user-oauth-flow
- Service: entra-id / agent-id
- Article date: 2025-10-30
- Summary: OAuth 2.0 トークン交換でエージェントのユーザー アカウント偽装プロトコルを使用して、エージェントのユーザー アカウントを介してエージェント ID がユーザー コンテキストで動作する方法について説明します。

エージェントのユーザー アカウントの偽装により、エージェント ID はエージェントのユーザー アカウントを介してユーザー コンテキストで動作し、ユーザーのアクセス許可と自律的な操作を組み合わせて使用できます。 このシナリオでは、エージェント ID ブループリント (アクター 1) が、FIC を使用してエージェントのユーザー アカウント (サブジェクト) を偽装するエージェント ID (アクター 2) を偽装します。 アクセスの範囲はエージェントIDに割り当てられた委任された権限です。 エージェントのユーザー アカウントは、1 つのエージェント ID でのみ偽装できます。

Warnung

Microsoft は、これらのプロトコルを実装するには、Microsoft.Identity.Web や Microsoft Entra ID Auth SDK（サイドカー）ライブラリなどの承認済み SDK を使用することを推奨しています。 これらのプロトコルの手動実装は複雑でエラーが発生しやすく、SDK を使用すると、セキュリティとベスト プラクティスへの準拠が保証されます。

### マネージド ID の統合

マネージド ID は、推奨される資格情報の種類です。 この構成では、マネージド ID トークンは親エージェント ID ブループリントの資格情報として機能し、標準の MSI プロトコルは資格情報の取得に適用されます。 この統合により、エージェント ID は、資格情報の自動ローテーションやセキュリティで保護されたストレージなど、MSI のセキュリティと管理のすべての利点を受け取ることができます。

### プロトコルの手順

プロトコルの手順を次に示します。

[Image: エージェントのユーザーアカウントトークン取得フローを示す図。]

1. エージェント ID ブループリントは、エージェント ID の偽装に使用する交換トークン (T1) を要求します。 エージェント ID ブループリントは、FIC として使用されるシークレット、証明書、またはマネージド ID トークンである可能性があるクライアント資格情報を提示します。

    Warnung

    セキュリティ リスクのために、エージェント ID ブループリントの運用環境では、クライアント シークレットをクライアント資格情報として使用しないでください。 代わりに、マネージド ID またはクライアント証明書を使用 [するフェデレーション ID 資格情報 (FIC)](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-config-app-trust-managed-identity) などのより安全な認証方法を使用してください。 これらの方法により、アプリケーション構成内に機密性の高いシークレットを直接格納する必要がなくなり、セキュリティが強化されます。

    ```
    POST /oauth2/v2.0/token
    Content-Type: application/x-www-form-urlencoded
    
    client_id=AgentBlueprint
    &scope=api://AzureADTokenExchange/.default
    &fmi_path=AgentIdentity
    &client_assertion_type=urn:ietf:params:oauth:client-assertion-type:jwt-bearer
    &client_assertion=TUAMI
    &grant_type=client_credentials
    ```

    TUAMI は、ユーザー割り当てマネージド ID (UAMI) の MSI トークンです。 これにより、トークン T1 が返されます。
2. エージェント ID は、エージェントのユーザー アカウントの偽装に使用するトークン (T2) を要求します。 エージェント ID は、クライアント アサーションとして T1 を提示します。 Microsoft Entra IDは、T1 (aud) == エージェント ID の親アプリ == エージェント ID ブループリントを検証した後、エージェント ID に T2 を返します。

    ```
    POST /oauth2/v2.0/token
    Content-Type: application/x-www-form-urlencoded
    
    client_id=AgentIdentity
    &scope=api://AzureADTokenExchange/.default
    &client_assertion_type=urn:ietf:params:oauth:client-assertion-type:jwt-bearer
    &client_assertion={T1}
    &grant_type=client_credentials
    ```

    これにより、トークン T2 が返されます。
3. エージェント ID は、T1 と T2 の両方を含む OBO トークン交換要求をMicrosoft Entra IDに送信します。 Microsoft Entra IDは、T2 (aud) == エージェント ID を検証します。

    ```
    POST /oauth2/v2.0/token
    Content-Type: application/x-www-form-urlencoded
    
    client_id=AgentIdentity
    &scope=https://resource.example.com/scope1
    &client_assertion_type=urn:ietf:params:oauth:client-assertion-type:jwt-bearer
    &client_assertion={T1}
    &user_federated_identity_credential={T2}
    &username=agentuser@contoso.com
    &grant_type=user_fic
    &requested_token_use=on_behalf_of
    ```
4. Microsoft Entra IDリソース トークンを発行します。

#### シーケンス図

次のシーケンス図は、エージェントのユーザー アカウントの偽装フローを示しています

[Image: エージェントのユーザーアカウントトークン取得フローのトークンシーケンスを示す図。]

エージェントのユーザー アカウントの偽装には、エージェント ID ブループリント→エージェント ID→エージェントのユーザー アカウントというパターンに従う資格情報の連鎖が必要です。 このチェーンの各手順では、前の手順のトークンを資格情報として使用し、セキュリティで保護された委任経路を作成します。 特権エスカレーション攻撃を防ぐために、両方のフェーズで同じクライアント ID を使用する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/agent-users"} -->
## Microsoft Entra エージェント IDでのエージェントのユーザー アカウントについて説明します - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/agent-users
- Service: entra-id / agent-id
- Article date: 2025-11-04
- Summary: この記事では、エージェントのユーザー アカウントの概念、Microsoft Entra ID内での機能、およびエージェント ID との関係について説明します。

エージェントのユーザー アカウントは、エージェントと人間のユーザー機能の間のギャップを埋めるために設計された特殊な ID の種類です。 エージェントのユーザー アカウントを使用すると、AI を利用したアプリケーションは、適切なセキュリティ境界と管理制御を維持しながら、ユーザー ID を必要とするシステムやサービスと対話できます。 これにより、組織は、人間のユーザーと同様の機能を使用してエージェントのアクセスを管理できます。

### エージェントのユーザー アカウント シナリオの例

エージェントがユーザーに代わってタスクを実行したり、自律アプリケーションとして動作したりするには不十分な場合があります。 特定のシナリオでは、エージェントはユーザーとして機能し、基本的にデジタル ワーカーとして機能する必要があります。 エージェントのユーザー アカウントが適用されるシナリオの例を次に示します。

- 組織には、メールボックス、チャット アクセス、人事システムへの組み込みを持つチーム メンバーとして機能する長期的なデジタル従業員が必要です。
- エージェントは、ユーザー ID のみが使用できる API またはリソースにアクセスする必要があります
- エージェントは、チーム メンバーとして共同作業ワークフローに参加する必要があります

このような理由から、エージェントのユーザー アカウントが作成されます。 エージェントのユーザー アカウントは省略可能であり、エージェントがユーザーとして機能するか、ユーザー アカウントに制限されたリソースにアクセスする必要がある対話のためにのみ作成する必要があります。

### エージェントのユーザー アカウント

エージェントのユーザー アカウントは、Microsoft Entra内のユーザー ID のサブタイプを表します。 これらの ID は、エージェント アプリケーションがユーザー ID が必要なコンテキストでアクションを実行できるように設計されています。 エージェントのユーザー アカウントは、非エージェント のサービス プリンシパルまたはアプリケーション ID とは異なり、要求 `idtyp=user`を持つトークンを受け取り、ユーザー ID を特に必要とする API とサービスにアクセスできるようにします。 また、非人間 ID に必要なセキュリティ制約も維持されます。

エージェントのユーザー アカウントは自動的に作成されません。 親エージェント ID に接続する明示的な作成プロセスが必要です。 この親子関係は、エージェントのユーザー アカウントがどのように機能し、Microsoft Entraで保護されているかを理解するための基礎となります。 確立されると、この関係は不変であり、エージェントのユーザー アカウントのセキュリティ モデルの基礎として機能します。 リレーションシップは 1 対 1 (1:1) のマッピングです。 各エージェント ID には、最大で 1 つのエージェントのユーザー アカウントを関連付けることができます。各エージェントのユーザー アカウントは、1 つの親エージェント ID にリンクされ、それ自体は 1 つのエージェント ID ブループリント アプリケーションにリンクされます。

エージェントのユーザー アカウント:

- また、エージェント ID ブループリントを使用して作成されます。
- 常に、作成時に指定された特定のエージェント ID に関連付けられます。
- エージェントの IDと は別に、固有の識別子を持ちます。
- 関連付けられているエージェント ID に発行されたトークンを提示することによってのみ認証できます。

[Image: エージェントのユーザー アカウントとエージェント ID の関係を示す図。]

### エージェントのユーザー アカウントとエージェント ID の関係

エージェント ID ブループリントには、エージェントのユーザー アカウントを作成するための既定のアクセス許可がありません。これは、この機能は省略可能であり、必ずしも必要とは限らないためです。 これは、エージェント ID ブループリントに明示的に付与する必要があるアクセス許可です。

エージェントのユーザー アカウントは、エージェント ID ブループリントを使用して作成されます。 適切なアクセス許可が付与されると、エージェント ID ブループリントはエージェントのユーザー アカウントを作成し、特定のエージェント ID との親関係を確立できます。 エージェント ID は、エージェントのユーザー アカウントの親と見なされます。

管理者は、エージェントのユーザー アカウントのライフサイクルを管理します。 管理者ユーザーは、その機能が不要になったら、エージェントのユーザー アカウントを削除できます。

### 認証とセキュリティ モデル

エージェントのユーザー アカウントの認証モデルは、人間のユーザー アカウントと大きく異なります。

- **フェデレーション ID 資格情報**: 認証は、エージェントのユーザー アカウントに割り当てられた資格情報によって行われます。 運用システムでは、フェデレーション ID 資格情報 (FIC) を使用します。 これらの資格情報は、エージェント ID ブループリントとエージェント ID の両方を認証するために使用されます。 ユーザーに割り当てられた資格情報は、エージェント エコシステム全体の認証に使用されます。
- **制限付き資格情報モデル**: エージェントのユーザー アカウントには、パスワードなどの通常の資格情報がありません。 その代わり、親リレーションシップを通じて提供された資格情報の使用に限定されます。 資格情報に対するこの制限と対話型サインインの制限により、エージェントのユーザー アカウントを標準のユーザー アカウントのように使用できなくなります。
- **偽装メカニズム**: 関連付けられているエージェント ID は、その子エージェントのユーザー アカウントを偽装できます。 これにより、親のビジネス ロジックはトークンを取得し、必要に応じてエージェントのユーザー アカウントとして機能できます。

### エージェントのユーザー アカウントの機能

エージェントのユーザー アカウントには、Microsoft 365やその他の環境内で効果的に機能できる機能があります。

- エージェントのユーザー アカウントは、動的グループを含むMicrosoft Entra グループに追加でき、それらのグループに付与されたアクセス許可を継承できます。 ただし、ロール割り当て可能なグループに追加することはできません。
- エージェントのユーザー アカウントは、リソースにアクセスし、通常は人間のユーザー用に予約されている他の共同作業機能を利用できます。
- エージェントのユーザー アカウントは、人間のユーザーと同様に、管理単位に追加できます。
- エージェントのユーザー アカウントにはライセンスを割り当てることができます。多くの場合、Microsoft 365リソースをプロビジョニングするために必要です。

### セキュリティの制約

エージェントのユーザー アカウントは、適切な使用を確保するために、特定のセキュリティ制約の下で動作します。

- 資格情報の制限: エージェントのユーザー アカウントは、パスワードやパスキーなどの資格情報を持つことができません。 サポートされている資格情報の種類は、親へのエージェント ID 参照のみです。 そのため、エージェントのユーザー アカウントがユーザーとして動作する場合でも、その資格情報は機密クライアント資格情報です。
- 管理ロールの制限: エージェントのユーザー アカウントに特権管理者ロールを割り当てることはできません。 この制限により、重要なセキュリティ境界が提供され、特権の昇格を防ぐことができます。 エージェントのユーザー アカウントは、カスタム ロールで割り当てることができます。
- アクセス許可モデル: 通常、エージェントのユーザー アカウントにはゲスト ユーザーに似たアクセス許可があり、ユーザーとグループを列挙するための機能が追加されています。

### Microsoft 365のエージェント ユーザー アカウントのプロビジョニング

メールボックス、Teams プレゼンス、人事システム統合などのデジタル ワーカー機能を使用してエージェントのユーザー アカウントを完全にプロビジョニングするには、Microsoft Teamsを使用してエージェントを作成します。 エージェント 365 とエージェント 365 SDK は、エージェント ユーザー アカウントがMicrosoft 365に完全に参加するための基盤を提供します。

Note

Microsoft Graph API を介してエージェントのユーザー アカウントを直接作成すると、Microsoft Entraで ID が確立されますが、Microsoft 365機能はプロビジョニングされません。 Graph APIアプローチは、Microsoft 365参加を必要としないシナリオにのみ使用します。

詳細については、[Microsoft 365 エージェント SDKドキュメント](https://learn.microsoft.com/ja-jp/microsoft-365/agents-sdk/)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/authentication-with-auth-sdk-sidecar"} -->
## Microsoft Entra ID認証 SDK (サイドカー) を使用した認証 - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/authentication-with-auth-sdk-sidecar
- Service: entra-id / agent-id
- Article date: 2026-04-28
- Summary: シークレットがエージェント コードを入力しないように、Auth SDK (サイドカー) Microsoft Entra IDが AI エージェントの資格情報とトークンを管理する方法について説明します。

[Microsoft Entra ID認証 SDK (サイドカー)](https://mcr.microsoft.com/en-us/product/entra-sdk/auth-sidecar/about) は、AI エージェントの認証とトークン操作を処理します。 これは、クライアント資格情報の交換、代理フロー、トークン ライフサイクル管理を処理する、エージェントの隣の 2 番目のコンテナーとして実行されます。 この記事では、Microsoft Entra ID認証 SDK (サイドカー) の設計パターン、動作方法、および関係する ID オブジェクトについて説明します。

### Microsoft Entra ID認証 SDK (サイドカー) を使用する理由

AI エージェントはダウンストリーム API を呼び出すために認証情報を必要としますが、エージェント認証に対する一般的なアプローチは十分ではありません。

- **エージェント コードでハードコーディングされたシークレット:** すべてのエージェント イメージには、アプリの `client_secret`のコピーが保持されます。 脆弱性の悪用、ログの漏洩、または忘れたファイル `.env` が Git にコミットされると、完全なテナントが露呈します。
- **すべての委任されたユーザー トークン:** エージェントは、人間が存在する場合にのみ動作できます。 すべての呼び出しが同じサービス プリンシパルとして扱われるため、個別の監査ができなくなります。

Microsoft Entra エージェント IDは、各エージェントに独自の ID を付与します。 サイドカー パターンでは、すべての資格情報処理をエージェント コードの外部に保持することで、その ID を簡単に使用できます。

### Microsoft Entra ID認証 SDK (サイドカー) のしくみ

[Microsoft Entra ID Auth SDK (サイドカー)](https://mcr.microsoft.com/en-us/product/entra-sdk/auth-sidecar/about) は、ポッドローカル ネットワーク上の HTTP エンドポイントを公開するコンテナーとして実行されます。 次の責任を担います。

- クライアント資格情報を `login.microsoftonline.com`と交換します。
- 自律フロー内のエージェント ID のクライアント資格情報またはフェデレーション ID 資格情報 (FIC) を使用してトークンを取得します。
- ユーザー コンテキスト呼び出しの代理 (OBO) フローを処理します。
- トークンをキャッシュし、更新と有効期限を管理します。
- 資格情報ソースを抽象化します。開発には `ClientSecret` を使用し、同じ API を使用したAzureデプロイには `SignedAssertionFromManagedIdentity` を使用します。

次の表は、エージェントとサイドカーの間の認証アクションのフローをまとめたものです。

| エージェント (あなたのコード) | Microsoft Entra ID 認証 SDK (サイドカー) |
| --- | --- |
| API を呼び出すタイミングを決定する | 適切なトークンを取得してキャッシュする |
| HTTP 要求をビルドする | クライアント資格情報と OBO 交換を実行する |
| OBO のユーザートークンを通過させる | ユーザー アサーションの検証と転送 |
| ビジネス ロジックの処理 | `login.microsoftonline.com`と通信を行う |

セキュリティ境界は明示的です。サイドカーにはホスト ポートがありません。 同じネットワーク内のサービス (エージェント コンテナーなど) のみがトークンを要求できます。

### サイドカー パターンの ID オブジェクト

次の表では、サイドカー パターンのMicrosoft Entra オブジェクトについて説明します。

| オブジェクト | 役割 | Location |
| --- | --- | --- |
| **ブループリント アプリケーション** | エージェント ID を作成して発行するテンプレート。 クライアント資格情報 (シークレットまたはフェデレーション) を保持します。 | あなたの Microsoft Entra テナント |
| **エージェント ID** | 個々の AI エージェント。 一意のアプリ ID、アクセス許可の付与、監査証跡があります。 | あなたの Microsoft Entra テナント |
| **クライアント シングルページ アプリケーション (SPA)** (OBO のみ) | ユーザーをサインインさせ、ユーザーのトークンを彼らに代わってエージェントトークンと交換するWeb UI。 | あなたの Microsoft Entra テナント |
| **サイドカー コンテナー** | クライアント資格情報と OBO フローを実行します。 ブループリント資格を保持します。 | エージェントの横 |
| **エージェント コンテナー** | アプリケーション コード。 サイドカーから承認ヘッダーを要求します。 | ポッド、Compose サービス、または App Service |

ブループリントとエージェント ID の詳細については、「 [エージェント ID ブループリントとエージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-blueprint) 」 [を](https://learn.microsoft.com/ja-jp/entra/agent-id/what-are-agent-identities)参照してください。

### 資格情報ソースの抽象化

サイドカーは、エージェント コードから資格情報ソースを抽象化します。 開発中は、利便性のために `ClientSecret` を使用できます。 運用環境のAzure展開では、エージェント コードを変更することなく、マネージド ID によってサポートされるフェデレーション ID 資格情報である `SignedAssertionFromManagedIdentity` に切り替えることができます。

サイドカーの構成によって、使用する資格情報ソースが決まります。 認証メカニズムに関係なく、エージェントは引き続き同じ `/AuthorizationHeader` エンドポイントを呼び出します。

### サイドカー サンプル シナリオ

[Microsoft Entra エージェント IDサイドカーのサンプル](https://github.com/microsoft/entra-agentid-samples/tree/dev/sidecar)は、次の概念を示しています。

- ブループリントとエージェント ID の違い、およびエージェントが独自の ID を必要とする理由。
- サイドカーが `/AuthorizationHeader` (トークンの取得) エンドポイントと `/DownstreamApi` (トークン + プロキシ呼び出し) エンドポイントを公開する方法。
- サイドカーがサインインしているユーザーのトークンを転送し、Microsoft Entra ID Auth SDK (サイドカー) が OBO 経由でエージェントの代理ユーザー トークンを作成する方法。
- ダウンストリーム API が、署名、発行者、 `xms_par_app_azp`、対象ユーザーを含むエージェント トークンを検証する方法。
- エージェント コードを変更せずに、`ClientSecret` (開発) から `SignedAssertionFromManagedIdentity` (Azure デプロイ) にスワップする方法。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/authorization-agent-id"} -->
## Microsoft Entra エージェント ID での承認

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/authorization-agent-id
- Service: entra-id / agent-id
- Article date: 2026-06-12
- Summary: Microsoft Entra エージェント ID での承認が AI エージェントに対してどのように機能するかについて説明します。

エージェント ID の導入は、組織における AI を利用したエージェントの増加によって推進されます。 従来の ID の種類 (標準アプリの登録やユーザー アカウントなど) は、自律型エージェントには適していません。 AI エージェントには、自律的な意思決定、動的学習機能、および機密データへのアクセスが予測不可能な動作を引き起こす可能性があるため、固有のセキュリティ上の懸念があります。

このギャップを埋めるために Microsoft Entra エージェント ID が作成されました。 Microsoft Entra ID プラットフォーム上に構築され、AI エージェント向けの専用の認証および承認フレームワークを提供します。これにより、ユーザーはサービスと API に安全にアクセスでき、管理者は自分のアクションを監視および制御する一元的な方法が提供されます。 つまり、エージェント ID を使用すると、エージェントを完全なユーザーまたは汎用アプリとして扱うのではなく、適切なポリシー適用を使用して、テナントで動作する AI エージェントを検出、管理、セキュリティで保護できます。

この記事では、ロール、アクセス許可制御、およびエージェント アクセスを管理するためのベスト プラクティスに関する情報を提供することで、AI エージェントに対する Microsoft Entra エージェント ID の承認のしくみについて説明します。

### エージェント ID 承認が重要な理由

AI エージェントは、タスクを迅速かつ大規模に実行できます。 Microsoft Entra ID の多くの高い特権機能 (ユーザーやロールを管理する機能など) は、慎重な意図を持つ人間の管理者を想定しています。 高い特権を持つ制約のないエージェントでは、予期しない管理タスクが実行され、影響が大きくなります (ユーザーの削除やセキュリティ設定の変更など)。

このため、Microsoft Entra ID では、エージェント ID で実行できる操作が制限されます。 たとえば、Microsoft Entra は、エージェントに多くの高い特権ロールまたはアクセス許可が付与されないようにブロックします。 ユーザーと管理者は、エージェントに対するこれらの強力なアクセス許可に同意することはできません。 この設計では、エージェントは最小限の特権で動作する必要があることを認識しています。 エージェントが機密性の高い特権を受け取らないようにすることで、システムは AI エージェントがアクセスをエスカレートするリスクを最小限に抑えます。 許可されているロールとアクセス許可の一覧は、時間の経過と同時に進化します。

### Microsoft Entra のエージェント識別子に対するロール割り当て

承認の観点から見ると、エージェント ID は、アプリケーションや追加のセーフガードを持つユーザーのように多少動作します。 各エージェント ID には、Microsoft Entra ID のサービス プリンシパルまたはユーザーが含まれており、特定の Microsoft Entra ロールを割り当てることができます。

たとえば、エージェントの ID に Microsoft Entra ロールを割り当てて管理者特権を付与できますが、エージェントに対して多くの高い特権を持つディレクトリ ロールがブロックされます。 グローバル管理者、特権ロール管理者、ユーザー管理者などのロールをエージェント ID に割り当てることはできません。 エージェントには、下位の特権ロール (閲覧者ロールなど) のみを割り当てることができます。 エージェント ID をロール割り当て可能なグループのメンバーにすることはできません。

Microsoft は、エージェント自体を管理および作成するためのエージェント ID 管理者ロールとエージェント ID 開発者ロールを作成しました。

### エージェントに許可されている Microsoft Entra のロール

エージェント ID に割り当てることができる[Microsoft Entraロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)の一覧を次に示します。

- AI 管理者
- 攻撃のペイロードの作成者
- 攻撃のシミュレーションの管理者
- 属性アサインメントリーダー
- 属性定義閲覧者
- 属性ログ管理者
- 属性ログリーダー
- Azure DevOps 管理者
- Azure Information Protection 管理者
- B2C IEF ポリシー管理者
- 請求管理者
- Cloud App Security 管理者
- コンプライアンス管理者
- コンプライアンス データ管理者
- カスタマー ロックボックスのアクセス承認者
- デスクトップアナリティクス管理者
- ディレクトリの読み手
- ディレクトリ同期アカウント
- Dynamics 365管理者
- Dynamics 365 Business Central 管理者
- Edge 管理者
- Exchange 管理者
- Exchange 受信者管理者
- 拡張ディレクトリ ユーザー管理者
- 外部IDユーザーフロー管理者
- 外部 ID ユーザー フロー属性管理者
- ファブリック管理者
- グローバルリーダー
- グローバル セキュリティで保護されたアクセス ログ リーダー
- Insights 管理者
- 洞察アナリスト
- インサイトビジネスリーダー
- IoT デバイス管理者
- Kaizala 管理者
- ナレッジ管理者
- ナレッジ マネージャー
- ライセンス管理者
- メッセージ センターのプライバシーリーダー
- メッセージ センター閲覧者
- Microsoft 365 バックアップ管理者
- Microsoft 365 移行管理者
- Microsoft Entra 参加済みデバイスのローカル管理者
- Microsoft Graph データ接続管理者
- Microsoft ハードウェア保証管理者
- Microsoft ハードウェア保証スペシャリスト
- ネットワーク管理者
- Office アプリ管理者
- 組織ブランド化管理者
- 組織データ ソース管理者
- 組織メッセージ承認者
- 組織メッセージ ライター
- 人事管理者
- プレース管理者
- Power Platform 管理者
- プリンター管理者
- プリンター技術者
- Purview ワークロード コンテンツ管理者
- Purview ワークロード コンテンツ リーダー
- Purview ワークロード コンテンツ ライター
- レポートビューワー
- 検索管理者
- 検索エディター
- セキュリティリーダー
- サービス サポート管理者
- SharePoint管理者
- SharePoint 組み込み管理者
- Skype for Business管理者
- Teams 管理者
- Teams 通信管理者
- Teams 通信サポート エンジニア
- Teams 通信サポート スペシャリスト
- Teams デバイス管理者
- Teams 閲覧者
- Teams テレフォニー管理者
- テナント作成
- 使用状況の概要レポート閲覧者
- ユーザー エクスペリエンス成功マネージャー
- 仮想訪問管理者
- Viva Glintテナント管理者
- Viva Goals 管理者
- Viva Pulse 管理者
- Windows 365 管理者
- ウィンドウズ アップデート デプロイ管理者
- Yammer 管理者

カスタム ロールはエージェントに割り当てることができます。

### エージェント ID に対する Microsoft Graph のアクセス許可

OAuth2 アクセス許可の場合、エージェント ID (具体的には、エージェント ID ブループリントとエージェント ID ブループリント プリンシパル) は、他のアプリと同じ Microsoft Graph アクセス許可モデルを使用できます。 エージェントは、委任されたアクセス許可 (同意を介してユーザーに代わって動作) またはアプリケーションのアクセス許可 (管理者によって付与されたアプリ専用特権) を要求できます。

ただし、リスクの高い Microsoft Graph API アクセス許可のセットは、エージェントに対して明示的にブロックされます。 たとえば、エージェントに次のアクセス許可を付与することはできません。

| ブロックされたアクセス許可 | メモ |
| --- | --- |
| `Application.ReadWrite.All` | すべてのアプリケーションの管理を許可します。 |
| `RoleManagement.ReadWrite.All` | ユーザー、グループ、ロール、ディレクトリ設定、およびその他の重要な操作を完全に制御できます。 |
| `User.ReadWrite.All` | すべてのユーザー アカウントを完全に制御できます。 |
| `Directory.AccessAsUser.All` | サインインしているユーザーとしてディレクトリ内の情報へのアクセスを許可します。 エージェントが Microsoft Graph へのアクセス権を一掃するように要求することで、エージェントがセキュリティを回避できないようにします。管理者であっても、エージェントにこれらのアクセス許可を付与することに同意することはできません。 |

エージェント ID には、必要に応じて低い特権のアクセス許可を引き続き付与できます。 たとえば、エージェントがそのユーザーの代わりにユーザーのメールボックスまたは OneDrive ファイルを読み取る必要がある場合は、 `Mail.Read` や `Files.Read` などの委任されたアクセス許可を要求でき、ユーザー (または管理者) は同意できます。 これらはテナント全体において高い特権とは見なされず、そのユーザーのデータに限定されます。

ブロックされるのは、単一のユーザーを越える、または管理制御を伴うテナント単位の特権です。 エージェントは、制限されたスコープの原則の下で動作します。 エージェントは、通常のユーザーが同意できること、または管理者が制御されたスコープの方法で明示的に許可する操作のみを実行できます。

### Azure ロール、Microsoft Entra ロール、または Microsoft Graph のアクセス許可を使用するタイミング

エージェントが実行する必要がある内容に応じて、管理者は、スコープを適切に保つためにさまざまな方法でアクセス権を付与できます。 これには、Azure ロールの割り当て、Microsoft Entra ロール、Graph のアクセス許可、アプリケーション ロールの割り当て、 [アクセス パッケージの割り当て](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-access-packages)、グループ メンバーシップなどの OAuth アクセス許可の付与が含まれます。

#### Azure ロール

**エージェントが Azure リソースにアクセスする必要がある場合**: これらの特定のリソースに対して Azure ロールを割り当てます。 たとえば、エージェントに Azure Key Vault の読み取りを許可するには、その ID にそのコンテナーの Key Vault 閲覧者ロールを付与します。 これにより、スコープが狭く (そのリソースまたはリソース グループだけが) 保持され、最小限の特権が使用されます。 詳細については、[Azure ポータルを使用して Azure ロールを割り当てる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal) を参照してください。

#### Microsoft Entra ロール

**エージェントがディレクトリ レベルのアクションを実行する必要がある場合**: 適切な低い特権ロールが存在する場合にのみ、Microsoft Entra ロールを使用します。 たとえば、エージェントが基本的なディレクトリ情報を読み取る必要があるだけの場合は、ディレクトリ閲覧者の種類のロールを使用できます。 エージェントに書き込みアクセス権を付与する必要がある場合は、影響を確認し、最小限の特権ロールを選択します。 ブロックされていない適切な組み込みロールがない可能性があります。 このような場合は、(制限を理解して) 代わりに Microsoft Graph のアクセス許可に依存することを選択できます。 詳細については、「[Microsoft Entra ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)割り当てる」を参照してください。

#### 委任された Microsoft Graph のアクセス許可

**エージェントがユーザーに代わって動作する場合 (ユーザー中心のシナリオ)**:委任された Microsoft Graph アクセス許可を使用します。 このオプションでは、対話型ユーザーの同意が必要ですが、エージェントがそのユーザーのアクセス権を超えないようにします。 たとえば、Alice のエージェント スケジュール会議では、委任された予定表 API アクセス許可が使用されます。Alice は同意し、エージェントは Alice の予定表 (Alice が自分で管理できるのと同じように) のみを管理できます。 詳細については、 [Microsoft ID プラットフォームでのアクセス許可と同意の概要に関するページを](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)参照してください。

#### Microsoft Graph アプリケーションのアクセス許可

**エージェントがテナント全体で自律的に実行される場合 (サービス シナリオ)**:Microsoft Graph アプリケーションのアクセス許可を控えめに使用します。 必要な特定のアプリのアクセス許可のみを付与し、高い特権ではない場合にのみ付与します。 たとえば、組織の組織図を生成するエージェントでは、すべてのプロファイルを読み取るための User.Read.All アプリのアクセス許可が必要になる場合があります。これは許容できる (ブロックリストに含まれていない) のに対し、User.ReadWrite.All は拒否されます。

アクセス許可のスコープを常に確認します。テナント全体の読み取りアクセスは特定のデータに対して問題ない場合がありますが、テナント全体の書き込みまたは制御はエージェントに対して許可されません。 管理者は、エージェントが取得するアプリのアクセス許可に明示的に同意する必要があるため、これらの要求を慎重に確認する機会があります。 詳細については、 [Microsoft ID プラットフォームでのアクセス許可と同意の概要に関するページを](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)参照してください。

### エージェント ID の継承可能なアクセス許可

エージェント ID ブループリントでは、継承可能なアクセス許可がサポートされています。管理者はブループリント レベルでアクセス許可を 1 回付与し、それらの許可がブループリントから作成されたすべてのエージェント ID に自動的に適用されます。 この機能により、複数のデプロイと環境で同意プロンプトが繰り返し表示されるのを減らすことができます。

継承可能なアクセス許可、必要なリソース アクセス、および直接付与がどのように連携するかの詳細については、「 [継承可能なアクセス許可](https://learn.microsoft.com/ja-jp/entra/agent-id/concept-inheritable-permissions)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/autonomous-agent-authentication-authorization-flow"} -->
## 自律エージェントのトークンを認証して取得する - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/autonomous-agent-authentication-authorization-flow
- Service: entra-id / agent-id
- Article date: 2026-06-15
- Summary: Microsoft Entra IDを使用して自律エージェントを認証し、アプリケーションのアクセス許可を付与し、必要に応じてエージェントのユーザー アカウントとして作成および認証する方法について説明します。

自律エージェントは、ユーザーの代理人として機能するのではなく、独自の ID を使用して操作を実行します。 安全に動作するには、自律エージェントがMicrosoft Entra IDで認証し、アクセス トークンを取得し、適切なアクセス許可を付与する必要があります。 この記事では、エンド ツー エンドのフローについて説明します。

1. クライアント資格情報を構成します。
2. エージェント ID ブループリントのトークンを要求します。
3. エージェント ID トークンを要求します。
4. アプリケーションのアクセス許可を付与する (管理者の同意)。
5. (省略可能)ユーザー ID を必要とするリソースに対して、エージェントのユーザー アカウントとして作成および認証します。

注

この記事では、独自の ID で動作する自律エージェントについて説明します。 エージェントがサインインしているユーザーの代わりに動作する必要がある場合は、「 [ユーザーの認証と対話型エージェントのトークンの取得](https://learn.microsoft.com/ja-jp/entra/agent-id/interactive-agent-authentication-authorization-flow)」を参照してください。

### 前提条件

エージェント トークン認証を実装する前に、次のことを確認してください。

- [エージェント ID ブループリント](https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint)。
- [エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/create-delete-agent-identities)。 エージェントアイデンティティクライアントIDが必要です。
- [Microsoft Entra エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-oauth-protocols)[における](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-oauth-protocols)OAuth プロトコル の理解。

管理者の承認には、次のものが必要です。

- Microsoft Entra ID テナントにおけるユーザーの管理者権限。
- エージェントに必要な特定のアクセス許可を理解する。

エージェントのユーザー アカウント認証には、次のものが必要です。

- Microsoft Entra エージェント IDにおけるエージェントのユーザーアカウントに関する理解。

### クライアント資格情報を構成する

クライアント資格情報の詳細を取得します。 これは、クライアントシークレット、証明書、またはフェデレート ID 資格情報として使用している管理された ID かもしれません。

Warnung

セキュリティ リスクのために、エージェント ID ブループリントの運用環境では、クライアント シークレットをクライアント資格情報として使用しないでください。 代わりに、マネージド ID またはクライアント証明書を使用 [するフェデレーション ID 資格情報 (FIC)](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-config-app-trust-managed-identity) などのより安全な認証方法を使用してください。 これらの方法により、アプリケーション構成内に機密性の高いシークレットを直接格納する必要がなくなり、セキュリティが強化されます。

## [マイクロソフト グラフ API](#tab/Microsoft-graph-api)
エージェントアイデンティティブループリントで構成したクレデンシャルを集めます。 次のいずれかが必要です。

- **管理 ID** (推奨): マネージド ID クライアント ID と、Azure インスタンス メタデータ サービス (IMDS) からのトークン。
- **証明書**: 証明書の拇印または PFX ファイル。
- **クライアント シークレット** (開発のみ): シークレット値。

## [Microsoft。Identity.Web](#tab/microsoft-identity-web)
フェデレーション ID 資格情報を使用してエージェント ID ブループリントを認証するために、*Microsoft.Identity.Web* を構成します。 運用環境では、フェデレーション ID 資格情報としてマネージド ID を使用します。

```json
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "TenantId": "<your-tenant-id>",
    "ClientId": "<agent-blueprint-client-id>",
    "ClientCredentials": [
      {
        "SourceType": "SignedAssertionFromManagedIdentity",
        "ManagedIdentityClientId": "<managed-identity-client-id>"
      }
    ]
  }
}
```

ローカル開発とテストの場合のみ、代わりにクライアント シークレットを使用できます。

```json
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "TenantId": "<your-tenant-id>",
    "ClientId": "<agent-blueprint-client-id>",
    "ClientCredentials": [
      {
        "SourceType": "ClientSecret",
        "ClientSecret": "your-client-secret"
      }
    ]
  }
}
```

---

### エージェント ID ブループリントのトークンを要求する

エージェント ID ブループリントのトークンを要求する場合は、 `fmi_path` (フェデレーション マネージド ID パス) パラメーターにエージェント ID のクライアント ID を指定します。 このパラメーターはMicrosoft Entra IDブループリントがどのエージェント ID に代わって動作するかを示します。

ローカル開発中にクライアント シークレットを使用する場合は、 `client_secret` パラメーターを指定します。 証明書とマネージド ID の場合は、代わりに `client_assertion` と `client_assertion_type` を使用します。

## [マイクロソフト グラフ API](#tab/Microsoft-graph-api)
エージェント ID ブループリントのトークンを取得するには、次のトークン要求を使用します。

```http
POST https://login.microsoftonline.com/<your-tenant-id>/oauth2/v2.0/token
Content-Type: application/x-www-form-urlencoded

client_id=<agent-blueprint-client-id>
&scope=api://AzureADTokenExchange/.default
&grant_type=client_credentials
&client_secret=<client-secret>
&fmi_path=<agent-identity-client-id>
```

## [Microsoft。Identity.Web](#tab/microsoft-identity-web)
Microsoft。Identity.Web、エージェント ID ブループリントのトークンを明示的に要求する必要はありません。 *Microsoft。Identity.Web* が自動的に実行します。

```bash
dotnet add package Microsoft.Identity.Web
dotnet add package Microsoft.Identity.Web.AgentIdentities
```

---

### エージェント ID トークンを要求する

## [マイクロソフト グラフ API](#tab/Microsoft-graph-api)
エージェント ID ブループリント トークン (T1) を取得したら、それを使用してエージェント ID トークンを要求します。

```http
POST https://login.microsoftonline.com/<your-tenant-id>/oauth2/v2.0/token
Content-Type: application/x-www-form-urlencoded

client_id=<agent-identity-client-id>
&scope=https://graph.microsoft.com/.default
&grant_type=client_credentials
&client_assertion_type=urn:ietf:params:oauth:client-assertion-type:jwt-bearer
&client_assertion=<agent-blueprint-token-T1>
```

## [Microsoft。Identity.Web](#tab/microsoft-identity-web)
*Microsoft。Identity.Web* は、アプリケーションの初期化時にサービス コレクションで新しい `services.AddAgentIdentities()`を使用する場合に、トークン取得プロトコルの詳細を処理します。

アプリケーションを初期化します。

```csharp
// Program.cs
using Microsoft.AspNetCore.Authorization;
using Microsoft.Identity.Abstractions;
using Microsoft.Identity.Web;
using Microsoft.Identity.Web.Resource;
using Microsoft.Identity.Web.TokenCacheProviders.InMemory;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddMicrosoftIdentityWebApiAuthentication(builder.Configuration);
builder.Services.AddAgentIdentities();
builder.Services.AddInMemoryTokenCaches();
```

アクセス トークンを要求するには、 `.WithAgentIdentity()` パターンを使用します。

```csharp
// In an API endpoint or service method where HttpContext is available
app.MapGet("/call-api", async (HttpContext httpContext) =>
{
    IAuthorizationHeaderProvider authorizationHeaderProvider =
        httpContext.RequestServices.GetRequiredService<IAuthorizationHeaderProvider>();

    AuthorizationHeaderProviderOptions options =
        new AuthorizationHeaderProviderOptions().WithAgentIdentity("<agent-identity-client-id>");

    // Request agent identity tokens
    string authorizationHeader = await authorizationHeaderProvider
        .CreateAuthorizationHeaderForAppAsync("https://graph.microsoft.com/.default", options);

    // The authHeader contains "Bearer " + the access token
    return Results.Ok(authorizationHeader);
});
```

---

### アプリケーションのアクセス許可を付与する

エージェントは、多くの場合、Microsoft Entra ID アプリケーションのアクセス許可 (アプリ ロールとして表されます) を必要とするMicrosoft Graphやその他の Web サービスでアクションを実行する必要があります。 自律エージェントは、Microsoft Entra ID管理者にこれらのアクセス許可を要求する必要があります。

自律エージェントにアプリケーションのアクセス許可を付与するには、次の 2 つの方法があります。

- 管理者は、Microsoft Graph API または PowerShell を使用して、*appRoleAssignment* を作成できます。
- エージェントは、管理者の同意 URL を使用して、管理者を同意ページに誘導できます。

#### API を使用してアプリ ロールの割り当てを作成する

アプリ ロールの割り当てを取得するには、次の手順を使用します。

1. アクセスしようとしているリソース サービス プリンシパルのオブジェクト ID を取得します。 たとえば、Microsoft Graph サービス プリンシパル オブジェクト ID を検索するには、次のようにします。

    1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)に移動します。
    2. **Entra ID** --&gt;**Enterprise Applications** に移動します
    3. アプリケーションの種類が Microsoft アプリケーションのときにフィルターをかける
    4. **Microsoft Graph** を検索します。
2. [Microsoft Graphアクセス許可参照](https://learn.microsoft.com/ja-jp/graph/permissions-reference)から、割り当てるアプリ ロールの一意の ID を取得します。
3. アプリ ロールの割り当てを作成します。

## [マイクロソフト グラフ API](#tab/microsoft-graph-api)
```http
    POST https://graph.microsoft.com/v1.0/servicePrincipals/<agent-identity-id>/appRoleAssignments
    Authorization: Bearer <token>
    Content-Type: application/json
    
    {
      "principalId": "<agent-identity-id>",
      "resourceId": "<microsoft-graph-sp-object-id>",
      "appRoleId": "<app-role-id>"
    }
    ```

## [Microsoft Graph PowerShell](#tab/microsoft-graph-powershell)
```powershell
    Connect-MgGraph -Scopes "Application.Read.All AppRoleAssignment.ReadWrite.All" -TenantId <your-tenant-id>
    
    # Get the service principal for Microsoft Graph (well-known app ID)
    $graphSp = Get-MgServicePrincipal -Filter "appId eq '00000003-0000-0000-c000-000000000000'"
    
    # Get your application's service principal (replace with your app's client ID)
    $agentId = "<agent-identity-id>"
    
    # Find the App Role ID for "User.ReadBasic.All"
    $userReadBasicRole = $graphSp.AppRoles | Where-Object {
        $_.Value -eq "User.ReadBasic.All" -and $_.AllowedMemberTypes -contains "Application"
    }
    
    # Assign the app role
    New-MgServicePrincipalAppRoleAssignment -ServicePrincipalId $agentId `
        -PrincipalId $agentId `
        -ResourceId $graphSp.Id `
        -AppRoleId $userReadBasicRole.Id
    ```

---

#### テナント管理者に承認を要求する

アプリケーションのアクセス許可を付与するには、管理者にメッセージを表示するために使用する承認 URL を作成します。 ロール パラメーターは、要求されたアプリケーションのアクセス許可を指定するために使用されます。

次の要求では、必ずエージェント ID クライアント ID を使用してください。

```bash
https://login.microsoftonline.com/contoso.onmicrosoft.com/v2.0/adminconsent
?client_id=<agent-identity-client-id>
&role=https://graph.microsoft.com/User.Read.All
&redirect_uri=https://entra.microsoft.com/TokenAuthorize
&state=xyz123
```

エージェントの実装では、チャット ウィンドウで管理者に送信されたメッセージに含めるなど、さまざまな方法で管理者をこの URL にリダイレクトする場合があります。 管理者がこの URL にリダイレクトされると、サインインし、スコープ パラメーターで指定されたアクセス許可に同意するように求められます。 現時点では、一覧表示されているリダイレクト URI を使用する必要があります。この URI を使用すると、管理者は同意を与えてから空白のページに移動します。

注

ブループリントでリダイレクト URI を構成し、同意要求に `state` パラメーターを含めます。 同意が付与されると、確認を表示できるリダイレクト URI にユーザーが送信されます。 エンドポイントでは、 `state` パラメーターを使用して、そのアクセス許可が付与されたことを追跡できます。 シングルテナント エージェントの場合は、テナント ID が既にわかっているため、同意が付与されるまでトークン要求を再試行することもできます。

エージェント ID ブループリントに必要なアクセス許可を付与したら、アクセス許可を有効にするために新しいエージェント アクセス トークンを要求します。

### エージェントのユーザー アカウントとして認証する

自律エージェントは、アプリ専用トークンを使用して操作するだけでなく、エージェントのユーザー アカウントとして認証できます。 エージェントのユーザー アカウントは、エージェントで使用するために専用に構築Microsoft Entra特殊な種類のユーザー アカウントです。 最も一般的に使用されるのは、メールボックス、Teams チャネル、その他のユーザー固有のリソースなど、ユーザー アカウントの存在を必要とするシステムにエージェントが接続する必要がある場合です。

各エージェント ID に関連付けられるエージェントのユーザー アカウントは 1 つだけであり、各エージェントのユーザー アカウントは 1 つのエージェント ID にのみ関連付けることができます。

#### エージェントのユーザー アカウントを作成するための承認を取得する

エージェントのユーザー アカウントを作成するには、エージェント ID ブループリントに、テナント内の `AgentIdUser.ReadWrite.IdentityParentedBy` アプリケーションのアクセス許可を付与する必要があります。 承認は、次の 2 つの方法のいずれかで取得できます。

- テナント管理者に承認を要求します。 必ず、エージェント ID ではなく、エージェント ID ブループリントを `client_id` として使用してください。
- テナントの API を使用してアプリ ロールの割り当てを作成します。 エージェント ID ブループリントのサービス プリンシパル オブジェクト ID を、アプリケーション (クライアント) ID ではなく、 `principalId` 値として使用します。

エージェント ID ブループリントではなく別のクライアントを使用してエージェントのユーザー アカウントを作成する場合、そのクライアントは代わりに委任された `AgentIdUser.ReadWrite.All` またはアプリケーションのアクセス許可を取得する必要があります。

#### エージェントのユーザー アカウントを作成する

エージェント ID ブループリントまたはその他の承認済みクライアントを使用して、エージェントのユーザー アカウントを作成します。 エージェントのユーザー アカウントを作成する推奨される方法は、エージェント ID ブループリントを使用することです。 エージェントのユーザー アカウントを作成するには [、エージェント ID ブループリントを使用してアクセス トークンを取得](https://learn.microsoft.com/ja-jp/entra/agent-id/create-delete-agent-identities#get-an-access-token-using-agent-identity-blueprint) する必要があります。

## [レスト](#tab/rest)
必要なアクセス許可を持つアクセス トークンを取得したら、次の要求を行います。

```http
POST https://graph.microsoft.com/beta/users
OData-Version: 4.0
Content-Type: application/json
Authorization: Bearer <token>

{
  "@odata.type": "microsoft.graph.agentUser",
  "displayName": "New Agent User",
  "userPrincipalName": "agentuserupn@tenant.onmicrosoft.com",
  "identityParentId": "{agent-identity-id}",
  "mailNickname": "agentuserupn",
  "accountEnabled": true
}
```

## [Microsoft。Identity.Web](#tab/msidweb)
`Microsoft.Identity.Web` を使用してエージェントのユーザー アカウントを作成するには、次の手順に従います。

1. 構成ファイルに次のコードを追加します。

    ```json
    {
      "AzureAd": {
        "Instance": "https://login.microsoftonline.com/",
        "TenantId": "<your-tenant-id>",
        "ClientId": "<my-agent-blueprint-id>",
        "Scopes": "access_agent",
        "ClientCredentials": [
          {
            "SourceType": "SignedAssertionFromManagedIdentity",
            "ManagedIdentityClientId": "managed-identity-client-id"  // Omit for system-assigned
          }
        ]
      },
    
      "DownstreamApis": {
        "agent-identity": {
          "BaseUrl": "https://graph.microsoft.com",
          "RelativePath": "/v1.0/users",
          "Scopes": ["00000003-0000-0000-c000-000000000000/.default"],
          "RequestAppToken": true
        }
      }
    }
    ```
2. 必要なパッケージを追加する

    ```bash
    dotnet add package Microsoft.Identity.Web
    dotnet add package Microsoft.Identity.Web.AgentIdentities
    ```
3. サービスを構成します。

    ```csharp
    using Microsoft.Identity.Abstractions;
    using Microsoft.Identity.Web;
    using Microsoft.Identity.Web.Resource;
    using Microsoft.IdentityModel.S2S.Extensions.AspNetCore;
    
    var builder = WebApplication.CreateBuilder(args);
    
    // Add services to the container.
    builder.Services.AddMicrosoftIdentityWebApiAuthentication(builder.Configuration)
        .EnableTokenAcquisitionToCallDownstreamApi();
    builder.Services.AddAgentIdentities();
    builder.Services.AddInMemoryTokenCaches();
    
    var app = builder.Build();
    app.UseHttpsRedirection();
    app.UseAuthentication();
    app.UseAuthorization();
    app.Run();
    ```
4. エージェントのユーザー アカウント作成エンドポイントを作成します。

    ```csharp
    app.MapPost("/create-agent-id-user", async (HttpContext httpContext) =>
    {
        try
        {
            // Get the service to call the downstream API (preconfigured in the appsettings.json file)
            IDownstreamApi downstreamApi = httpContext.RequestServices.GetRequiredService<IDownstreamApi>();
    
            var requestBody = new AgentIdUser
            {
                displayName = "my-agent-name",
                mailNickname = "my-agent-alias",
                userPrincipalName = "my-agent-email-address",
                accountEnabled = true,
                identityParentId = "<associated-agent-identity-id>"
            };
    
            // Call the downstream API (Graph) with a POST request to create an agent's user account
            var jsonResult = await downstreamApi.PostForAppAsync<AgentIdUser, AgentIdUser>(
                "agent-identity",
                requestBody
            );
    
            return Results.Json(jsonResult);
        }
        catch (Exception ex)
        {
            return ex.Message;
        }
    })
    ```

---

エージェントのユーザー アカウントを作成したら、他に何も構成する必要はありません。 これらのアカウントには資格情報がないため、次のセクションで説明するプロトコルを使用してのみ認証できます。

#### エージェント ID に同意を付与する

エージェントのユーザー アカウントは、他のユーザー アカウントと同様に動作します。 エージェントのユーザー アカウントを使用してトークンを要求するには、エージェント ID がエージェントの代わりに動作することを承認する必要があります。 テナント管理者に承認を要求するか、Microsoft Graphまたは PowerShell を使用して手動で`oAuth2PermissionGrant`を作成することで、エージェント ID Microsoft Graph承認できます。

Microsoft Graphの場合、要求は次のスニペットに示されています。

```http
POST https://graph.microsoft.com/v1.0/oauth2PermissionGrants
Authorization: Bearer {token}
Content-Type: application/json

{
  "clientId": "{agent-identity-id}",
  "consentType": "Principal",
  "principalId": "{agent-id-user-object-id}",
  "resourceId": "{ms-graph-service-principal-object-id}",
  "scope": "Mail.Read"
}
```

Microsoft Graph PowerShell の場合は、次のスクリプトを使用します。

```powershell
Connect-MgGraph -Scopes "DelegatedPermissionGrant.ReadWrite.All" -TenantId <your-tenant-id>

# Get the service principal for Microsoft Graph
$graphSp = Get-MgServicePrincipal -Filter "appId eq '00000003-0000-0000-c000-000000000000'"

# Get the service principal for your client app
$clientSp = Get-MgServicePrincipal -Filter "appId eq '{agent-identity-id}'"

# Create the delegated permission grant
New-MgOauth2PermissionGrant -BodyParameter @{
    clientId    = $clientSp.Id
    consentType = "Principal"
    principalId = "{agent-id-user-object-id}"
    resourceId  = $graphSp.Id
    scope       = "Mail.Read"
}
```

#### エージェントのユーザー アカウント トークンを要求する

エージェントのユーザー アカウントを認証するには、次の 3 つの手順に従う必要があります。

1. エージェント ID ブループリントとしてトークンを取得します。
2. そのトークンを使用して、エージェント ID として別のトークンを取得します。
3. エージェントのユーザー アカウントとして別のトークンを取得するには、前の両方のトークンを使用します。

## [レスト](#tab/rest)
最初に、「エージェント ID ブループリントのトークンを要求する」の説明に従って、 エージェント ID ブループリントとしてトークンを要求します。 エージェント ID ブループリント トークンを取得したら、エージェント ID ブループリント トークンを使用して、エージェント ID のフェデレーション ID 資格情報 (FIC) を要求します。

```http
POST https://login.microsoftonline.com/<your-tenant-id>/oauth2/v2.0/token
Content-Type: application/x-www-form-urlencoded

client_id=<agent-identity-id>
&scope=api://AzureADTokenExchange/.default
&grant_type=client_credentials
&client_assertion_type=urn:ietf:params:oauth:client-assertion-type:jwt-bearer
&client_assertion=<agent-blueprint-token>
```

これにより、エージェント ID の交換トークン (T2) が返されます。 次の要求でそれを使用して、エージェントのユーザー アカウントの委任されたトークンを取得します。

```http
POST https://login.microsoftonline.com/<your-tenant-id>/oauth2/v2.0/token
Content-Type: application/x-www-form-urlencoded

client_id=<agent-identity-id>
&scope=https://graph.microsoft.com/.default
&grant_type=user_fic
&client_assertion_type=urn:ietf:params:oauth:client-assertion-type:jwt-bearer
&client_assertion=<agent-blueprint-token>
&user_id=<agent-user-object-id>
&user_federated_identity_credential=<agent-identity-token>
```

これにより、エージェントのユーザー アカウントとしてMicrosoft Graphを呼び出すために使用できる委任されたアクセス トークンが提供されます。 ユーザー識別子に`user_id=<user-object-id>`する代わりに、`username=<UPN>`を使用できます。

## [Microsoft。Identity.Web](#tab/msidweb)
*Microsoft。Identity.Web* はトークンの取得を自動的に処理します。 アクセス トークンを要求するには、 `.WithAgentUserIdentity()` パターンを使用します。

```csharp
// In an API endpoint or service method where HttpContext is available
app.MapGet("/agent-user-token", async (HttpContext httpContext) =>
{
    IAuthorizationHeaderProvider authorizationHeaderProvider =
        httpContext.RequestServices.GetRequiredService<IAuthorizationHeaderProvider>();

    // Configure options for the agent's user account identity
    string agentIdentity = "agent-identity-id";
    string userId = "<user-object-id>";
    var options = new AuthorizationHeaderProviderOptions()
        .WithAgentUserIdentity(agentIdentity, userId);

    // Create a ClaimsPrincipal to enable token caching
    ClaimsPrincipal user = new ClaimsPrincipal();

    // Acquire a user token
    string authHeader = await authorizationHeaderProvider
        .CreateAuthorizationHeaderForUserAsync(
            scopes: ["https://graph.microsoft.com/.default"],
            options: options,
            user: user);

    return Results.Ok(authHeader);
});
```

オブジェクト ID の代わりに、エージェントのユーザー アカウント プリンシパル名を使用することもできます。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/best-practices-agent-id"} -->
## Microsoft Entra エージェント IDのベスト プラクティス - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/best-practices-agent-id
- Service: entra-id / agent-id
- Article date: 2026-03-27
- Summary: ブループリントの設計、資格情報管理、アクセス制御、監視戦略など、Microsoft Entra エージェント IDを使用した AI エージェント ID の設計、セキュリティ保護、管理に関する運用上のベスト プラクティスについて説明します。

この記事では、Microsoft Entra エージェント IDを使用して AI エージェント ID を設計、セキュリティ保護、管理するための運用上のベスト プラクティスについて説明します。 これらの推奨事項は、エージェントのデプロイ、資格情報の管理、アクセス ポリシーの適用、およびエージェント アクティビティの監視を計画する際に、情報に基づいた意思決定を行うのに役立ちます。

基本的な概念については、「[Microsoft Entra エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id) と [Key の概念](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/key-concepts)を参照してください。

### エージェント ID ブループリントを設計する

[エージェント ID ブループリント](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/agent-blueprint) は、共通の種類のすべてのエージェント インスタンスのセキュリティ体制を定義するテンプレートです。 思慮深いブループリント設計は、適切に管理されたエージェントデプロイの基盤です。

- **エージェントをデプロイする前にブループリントを計画します。** アドホック サービス プリンシパルを作成するのではなく、ブループリントで必要な設定、アクセス許可、およびメタデータを事前に定義します。 これにより、すべてのインスタンスで一貫したガバナンスされたロールアウトが保証されます。 ID モデルの構築に関するガイダンスについては、 [エージェント ID アーキテクチャの計画に関するページを参照してください](https://learn.microsoft.com/ja-jp/entra/agent-id/how-to-plan-agent-identity-architecture)。
- **エージェント インスタンスごとに一意の ID をプロビジョニングします。** 異なるエージェント間で ID を共有しないようにします。 個別の ID を使用すると、追跡可能性が向上し、他のエージェントに影響を与えずに 1 つのエージェントを無効または更新できます。 ブループリント モデルでは、資格情報が各インスタンスではなくブループリント上に配置されるため、一意の ID を使用したスケーリングを管理できます。 手順については、「 [エージェント ID の作成と削除」を](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/create-delete-agent-identities)参照してください。
- **作成時にスポンサーと所有者を割り当てます。** すべてのブループリントとエージェント ID には [スポンサー](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/agent-owners-sponsors-managers)が必要です。エージェントの目的に対して責任を負う人物またはグループ。 所有者 (技術管理者) も割り当てます。 これらの割り当てが最新であることを定期的に確認します(特に人事異動が発生した場合)。
- **説明的なメタデータを指定します。** 各ブループリントの説明、タグ、および検証済みの発行元フィールドを入力して、エージェントの目的、スコープ、所有チームを明確に文書化します。 適切なメタデータにより、検出が向上し、同僚がエージェントの役割を一目で理解するのに役立ちます。 ブループリントのセットアップの詳細については、 [エージェント ID ブループリントの作成に関するページを](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/create-blueprint)参照してください。
- **ブループリント レベルでポリシーを適用します。** 条件付きアクセス規則、API アクセス許可、およびガバナンス制御をブループリントに関連付けて、現在および将来のすべてのエージェント インスタンスがそれらを自動的に継承できるようにします。 ブループリントを無効にすると、そのすべてのエージェント ID が即座にブロックされます。 このオプションは、必要に応じ迅速なキルスイッチを提供します。 詳細については、「 [エージェントの条件付きアクセス」を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/agent-id)参照してください。
- **すべてのエージェントにエージェント ID フレームワークを使用します。** エージェント ID フレームワークの外部で、プレーンなアプリ登録またはサービス プリンシパルとして AI エージェントを作成しないでください。 サポートされている [作成チャネル](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-id-creation-channels) を常に使用して、組み込みのスポンサーアカウンタビリティとライフサイクル制御を使用してエージェント ID としてエージェントを追跡します。
- **必要な場合にのみ、エージェントのユーザー アカウントを作成します。**[エージェントのユーザー アカウント](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/agent-users) は、メールボックスや Teams プレゼンスを必要とするエージェントなど、ユーザー オブジェクトを本当に必要とするシナリオでのみ作成する必要があります。 エージェントがアプリの資格情報だけで動作できる場合は、エージェントのユーザー アカウントは、ライセンス、グループ メンバーシップ、およびユーザー レベルのポリシーで複雑になるため、避けてください。

### AI コーディング アシスタント用のガードレール

AI コーディング アシスタントは、多くの場合、従来のワークロード ID パターンであるため、エージェントの構築を求められた場合に、既定で標準アプリ登録を作成します。 AI コーディング アシスタントがエージェント ID を正しく使用するように、リポジトリの `.github/copilot-instructions.md` または `AGENTS.md` ファイルに次のコードを追加します。

```markdown
## Microsoft Entra Agent ID
- To give an AI agent its own identity in Microsoft Entra, create an agent identity blueprint, then create agent identities from it.
- Don't create or reuse a standard app registration or service principal to represent an agent. Never use `az ad app create`, `New-MgApplication`, `New-AzADApplication`, or `POST /applications` to create an agent identity.
- In .NET, use the Microsoft.Identity.Web.AgentIdentities package: call builder.Services.AddAgentIdentities(), then acquire tokens with WithAgentIdentity(agentIdentity) (app-only) or WithAgentUserIdentity(agentIdentity, user) (on behalf of a user).
- Requires the Agent ID Developer or Agent ID Administrator role and the AgentIdentityBlueprint.Create permission.
- Reference: /entra/agent-id/how-to-plan-agent-identity-architecture
```

### 資格情報を安全に管理する

資格情報の管理は、エージェント ID を介した未承認のアクセスを防ぐために重要です。 詳細な手順については、「 [エージェント ID ブループリントの作成」を](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/create-blueprint)参照してください。

- **運用環境でマネージド ID または証明書を使用します。** 運用エージェントの場合は、クライアント シークレットよりも [フェデレーション ID 資格情報](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) (マネージド ID) または証明書を優先します。 マネージド ID は、格納されているシークレットを完全に排除します。 クライアント シークレットは、最初の開発またはテストにのみ使用し、公開前にローテーションします。
- **ブループリントごとに資格情報を分離します。** 関連のないブループリント間で同じ資格情報を再利用しないでください。 個別の環境 (開発、テスト、運用) がある場合は、1 つの環境での侵害が他の環境に影響しないように、個別のブループリントまたは環境固有のフェデレーション資格情報を使用します。 セットアップ手順については、「 [エージェント ID ブループリントの作成」を](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/create-blueprint)参照してください。
- **資格情報を安全に保存します。** 証明書の秘密キーを [Azure Key Vault](https://learn.microsoft.com/ja-jp/azure/key-vault/general/overview) または HSM に格納します。 マネージド ID に関連付けられているフェデレーション資格情報を使用する場合は、マネージド ID のスコープを制限します。 ブループリントで有効期間の長い資格情報が許可されている場合でも、証明書を少なくとも年 1 回ローテーションするローテーション スケジュールを確立します。
- **OAuth フローをエージェント のシナリオに合わせます。** エージェントの運用モデルに適した [OAuth フロー](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/agent-oauth-protocols) を使用します。

    - ユーザー コンテキストのない自律エージェントの場合は、必要なアプリのアクセス許可のみを持つクライアント資格情報フローを使用します。
    - ユーザーの代わりに動作する対話型エージェントの場合は、ユーザー アクセス ポリシーと同意が適用されるように、代理 (OBO) フローを使用します。
    - 委任されたアクセス許可で十分な場合は、アプリのアクセス許可を付与しないでください。
- **デプロイ後にトークンの使用状況を監視します。**[サインイン ログ](https://learn.microsoft.com/ja-jp/entra/agent-id/sign-in-audit-logs-agents)を確認して、エージェントが目的の認証方法と資格情報の種類を使用していることを確認します。 各ブループリントで同意された API アクセス許可を定期的に監査して、特権の逸脱を防ぎます。

### アクセス制御を適用する

エージェント ID には、ユーザー ID と同じゼロ トラスト原則を適用します。 詳細な構成については、 [エージェントの条件付きアクセスとエージェントの](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/agent-id)[Identity Protection に関する](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-agents)記事を参照してください。

- **カスタム セキュリティ属性を持つエージェントをセグメント化します。**`Environment`、`Department`、`DataSensitivity`などの組織全体の属性を定義し、エージェント ID に割り当てます。 これらの属性を条件付きアクセス ポリシー条件で使用して、運用リソースへの非運用エージェントのアクセスをブロックするなど、きめ細かい制御を大規模に適用します。 詳細については、「 [カスタム セキュリティ属性の割り当て](https://learn.microsoft.com/ja-jp/entra/identity/users/users-custom-security-attributes)」を参照してください。
- **リスクの高いエージェントを自動的にブロックします。**[Identity Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-agents) によって高リスク レベルのフラグが設定されたエージェント ID をブロックする条件付きアクセス ポリシーをデプロイします。 これは危険なユーザーをブロックするのと似ていて、侵害されたエージェントが直ちに遮断されるようにします。
- **エージェント固有の条件付きアクセス ポリシーを作成します。** エージェントのユーザーを対象とするポリシーに依存しないでください。 エージェントは MFA などの対話型コントロールを満たすことはできません。そのため、ID フィルター、リスクシグナル、および名前付きの場所を制御ポイントとして使用する個別のポリシーを作成します。 ポリシーを適用する前に、レポート専用モードを使用してポリシーをテストします。 詳細なガイダンスについては、「 [エージェントの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/agent-id)」を参照してください。
- **エージェントへの影響について既存のポリシーを確認します。** 広範なポリシー ("すべてのユーザーが MFA を使用する必要がある" など) を監査して、エージェント フローを意図せずにブロックしないようにします。 エージェント ID を除外し、適切な制御を使用して専用エージェント ポリシーを作成するようにリファクタリングします。
- **最小特権のアクセス許可を実装します。**[承認ガイダンス](https://learn.microsoft.com/ja-jp/entra/agent-id/authorization-agent-id)を使用して、各エージェントに必要なアクセス許可のみを付与します。 便宜上、広範なアクセス許可を付与しないでください。 特定のスコープ、API リソース、またはサイトにアクセス許可を制限します。 アクセス許可を定期的に確認し、適切なサイズに設定します。

### エージェントのライフサイクルを管理する

効果的なガバナンスにより、エージェントが拡散するのを防ぎ、エージェントがライフサイクル全体にわたって責任を持ち続けられるようにします。 ガバナンス ツールについては、 [エージェントの ID ガバナンスと、エージェント ID の](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-id-governance-overview)[Access パッケージに関するページを](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-access-packages)参照してください。

- **すべてのエージェントをMicrosoft Entraに登録します。** Copilot Studio、Azure、外部プラットフォームのいずれに組み込まれているかにかかわらず、すべてのエージェントをエージェント ID フレームワークを使用してMicrosoft Entraに登録します。 一元化された登録により、シャドウ AI が排除され、IT に完全な可視性が提供されます。 サポートされているメソッドについては、 [エージェント作成チャネル](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-id-creation-channels)を参照してください。
- **名前付け規則を標準化します。** エージェント ID の名前付け規則を定義して適用します 。たとえば、表示名に部門や関数のプレフィックスを付ける ( `Agent-HROnboardingBot`など)。 一貫性のある名前付けにより、エージェントはログと管理センターで認識できるようになります。
- **アクセス レビューにエージェントを含めます。** エージェント ID を含む定期的 [なアクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview) を構成します。 スポンサーは、各エージェントが引き続き必要であり、適切に構成されていることを 6 ~ 12 か月ごとに証明します。 スポンサーから確認が取れない場合は、そのエージェントの廃止を検討してください。
- **孤立したエージェントを監視します。** スポンサーの不足、古いメタデータ、または最近のアクティビティがないエージェントを特定するために、四半期ごとのレビュー プロセスを開発します。 スポンサー プランを再割り当てするか、未使用のエージェントの使用を停止します。 一元化されたビューについては、「 [エージェント ID の表示とフィルター処理」を](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/agent-lists)参照してください。
- **標準化されたアクセスにはアクセス パッケージを使用します。** 一般的なアクセス パターンを持つエージェント (たとえば、カスタマー サポート エージェントのフリート) の場合は、 [アクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-access-packages) を使用して、直接のアクセス許可の割り当てではなく、承認ワークフローを通じて時間制限付きの監査可能なアクセス権を付与します。

### エージェント アクティビティの監視と監査

継続的な監視により、エージェントは予想される境界内で動作します。 ログの詳細については、 [エージェントのサインインと監査ログに関するページを](https://learn.microsoft.com/ja-jp/entra/agent-id/sign-in-audit-logs-agents)参照してください。

- **サインイン ログで異常を監視します。** トークン要求の急激な急増、予期しない API へのアクセス、未知の IP 範囲からのサインインなど、異常なパターンのアラートを設定します。 エージェントのサインイン ログには、各トークンの取得と、リソース、資格情報の種類、結果に関する詳細が表示されます。
- **監査ログの構成変更を追跡します。** エージェントのブループリント、資格情報の追加、アクセス許可の付与、ロールの割り当ての変更について [監査ログ](https://learn.microsoft.com/ja-jp/entra/agent-id/sign-in-audit-logs-agents) を監視します。 通常のデプロイ パイプラインの外部で発生した変更に関するアラート。
- **インシデント対応にエージェントを含めます。** セキュリティ インシデントを分析する場合は、影響を受けるリソースにエージェントがアクセスできるかどうかを確認し、インシデントウィンドウでアクティビティを確認します。 エージェント ID チェックを既存の事後分析プロセスに統合します。
- **次のプロアクティブ アラートを設定します。**

    - 資格情報の有効期限 (証明書またはシークレットが終了日に近づいている)
    - 条件付きアクセスまたは Identity Protection によってブロックされたエージェント
    - トークン取得試行の過剰な失敗
    - 予期しないアクセス許可またはロールの変更
- **コンプライアンスのためにログを保持します。** 組織のコンプライアンス フレームワークに必要な期間、ログ保持ポリシーでエージェントのアクティビティがカバーされていることを確認します。 必要に応じて、大量のエージェント ログをセキュリティで保護されたアーカイブにエクスポートします。 構成オプションについては、「 [診断設定の構成」を](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-diagnostic-settings)参照してください。

### 開発と IT ワークフローの調整

エージェントをスムーズにデプロイするには、エージェントを構築する開発者と、エージェントを管理する IT 管理者の間で調整が必要です。

- **サポートされている作成チャネルを使用します。** 必要なプロパティを見逃す可能性がある手動の Graph 呼び出しではなく、[Copilot Studio、Graph API、またはエージェント 365 CLI](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-id-creation-channels) を使用してエージェントを構築します。 これらのツールは、ブループリントの作成、資格情報のバインド、インスタンスの設定を自動的に処理します。
- **生産ハンドシェイクプロセスを確立します。** 新しいエージェントが運用環境に移行したら、ID 管理者にMicrosoft Entra エージェント ID設定を確認させます。ブループリントとスポンサーが正しいことを確認し、必要なアクセス許可が同意され、条件付きアクセス ポリシーが適用され、エージェントが適切なグループまたは管理単位に配置されていることを確認します。
- **非運用環境でテストします。** 運用環境にデプロイする前に、別の開発テナントまたはサンドボックスを使用して、エージェント認証フロー、条件付きアクセス ポリシー、およびアクセス許可の構成を検証します。
- **エージェント構成をコードとして扱います。** ブループリント定義、アクセス許可の構成、およびソース管理へのスクリプトのセットアップを確認します。 これにより、構成のずれを防ぎ、ピア レビューを有効にし、エージェントをMicrosoft Entra IDに統合する方法に関する制度的なメモリを提供します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/call-api-azure-services"} -->
## .NET Azure SDKを使用してエージェントからAzure サービスを呼び出す - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/call-api-azure-services
- Service: entra-id / agent-id
- Article date: 2026-05-01
- Summary: エージェント ID を使用してエージェントから.NET Azure SDKを使用してAzure サービスを呼び出す方法について説明します。

この記事では、エージェントから Azure サービスを呼び出す方法について説明します。 エージェント ID を使用してAzure StorageやAzure Key VaultなどのAzure サービスに対して認証を行うには、*Microsoft の `MicrosoftIdentityTokenCredential` クラスを使用します。Identity.Web。Azure*。 `MicrosoftIdentityTokenCredential` クラスは、Azure SDK の `TokenCredential` インターフェイスを実装することで、*Microsoft.Identity.Web* と Azure SDK クライアント間のシームレスな統合を可能にします。

エージェントから API を呼び出すには、エージェントが API に対して自身を認証するために使用できるアクセス トークンを取得する必要があります。 .NET用 SDKである*Microsoft.Identity.Web*を使用してWeb API を呼び出すことをお勧めします。 この SDK は、トークンの取得と検証のプロセスを簡略化します。 他の言語の場合は、[Microsoft Entra ID認証 SDK (サイドカー)](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/overview) を使用します。

### 前提条件

- ターゲット API を呼び出す適切なアクセス許可を持つエージェント ID。 On-Behalf-Of フローにはユーザーが必要です。
- ターゲット API を呼び出す適切なアクセス許可を持つエージェントのユーザー アカウント。

### 実装の手順

1. Azure統合パッケージと*Microsoft.Identity.Web.AgentIdentities*パッケージをインストールして、エージェント ID のサポートを追加します。

    ```bash
    dotnet add package Microsoft.Identity.Web.Azure
    dotnet add package Microsoft.Identity.Web.AgentIdentities
    ```
2. Azure Storageなど、使用するAzure SDK パッケージをインストールします。

    ```bash
    dotnet add package Azure.Storage.Blobs
    ```
3. Azureトークン資格情報のサポートを追加するようにサービスを構成します。

    ```csharp
    using Microsoft.AspNetCore.Authentication.OpenIdConnect;
    using Microsoft.Identity.Web;
    
    var builder = WebApplication.CreateBuilder(args);
    
    // Add authentication
    builder.Services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
        .AddMicrosoftIdentityWebApp(builder.Configuration.GetSection("AzureAd"))
        .EnableTokenAcquisitionToCallDownstreamApi()
        .AddInMemoryTokenCaches();
    
    // Add Azure token credential support
    builder.Services.AddMicrosoftIdentityAzureTokenCredential();
    
    builder.Services.AddControllersWithViews();
    var app = builder.Build();
    app.UseAuthentication();
    app.UseAuthorization();
    app.MapControllers();
    app.Run();
    ```
4. *appsettings.json*でAzureのトークン資格情報オプションを構成します。

    Warnung

    セキュリティ リスクのために、エージェント ID ブループリントの運用環境では、クライアント シークレットをクライアント資格情報として使用しないでください。 代わりに、マネージド ID またはクライアント証明書を使用 [するフェデレーション ID 資格情報 (FIC)](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-config-app-trust-managed-identity) などのより安全な認証方法を使用してください。 これらの方法により、アプリケーション構成内に機密性の高いシークレットを直接格納する必要がなくなり、セキュリティが強化されます。

    ```json
    {
      "AzureAd": {
        "Instance": "https://login.microsoftonline.com/",
        "TenantId": "<your-tenant-id>",
        "ClientId": "<agent-blueprint-id>",
    
       // Other client creedentials available. See <https://aka.ms/ms-id-web/client-credentials>
        "ClientCredentials": [
          {
            "SourceType": "ClientSecret",
            "ClientSecret": "your-client-secret"
          }
        ]   
      }
    }
    ```
5. サービス プロバイダーからトークン資格情報を取得し、Azure SDK クライアントで使用します。

    1. エージェント ID の場合は、 `WithAgentIdentity` メソッドを使用して、アプリ専用トークン (自律エージェント) またはユーザー トークンの代理 (対話型エージェント) を取得できます。 アプリのみのトークンの場合は、 `RequestAppToken` プロパティを `true` に設定します。 ユーザー トークンの代理委任の場合は、 `RequestAppToken` プロパティを設定したり、明示的に `false` に設定したりしないでください。

        ```csharp
        using Microsoft.Identity.Web;
        
        public class AgentService
        {
            private readonly MicrosoftIdentityTokenCredential _credential;
        
            public AgentService(MicrosoftIdentityTokenCredential credential)
            {
                _credential = credential;
            }
        
            // Call Azure service with the agent identity for app only scenario
            public async Task<List<string>> ListBlobsForAgentAppOnlyAsync(string agentIdentity)
            {
                // Configure for agent identity
                _credential.Options.WithAgentIdentity(agentIdentity);
                _credential.Options.RequestAppToken = true;
        
                var blobClient = new BlobServiceClient(
                    new Uri("https://myaccount.blob.core.windows.net"),
                    _credential);
        
                var container = blobClient.GetBlobContainerClient("agent-data");
                var blobs = new List<string>();
        
                await foreach (var blob in container.GetBlobsAsync())
                {
                    blobs.Add(blob.Name);
                }
        
                return blobs;
            }
        
            // Call Azure service with the agent identity for on-behalf of user scenario
            public async Task<List<string>> ListBlobsForAgentOnBehalfOfUserAsync(string agentIdentity)
            {
                // Configure for agent identity
                _credential.Options.WithAgentIdentity(agentIdentity);
                _credential.Options.RequestAppToken = false;
        
                var blobClient = new BlobServiceClient(
                    new Uri("https://myaccount.blob.core.windows.net"),
                    _credential);
        
                var container = blobClient.GetBlobContainerClient("agent-data");
                var blobs = new List<string>();
        
                await foreach (var blob in container.GetBlobsAsync())
                {
                    blobs.Add(blob.Name);
                }
        
                return blobs;
            }
        }
        ```
    2. エージェントのユーザー アカウントのトークンを取得することもできます。 これを行うには、ユーザー プリンシパル名 (UPN) またはオブジェクト ID (OID) を使用して、エージェントのユーザー アカウントを識別できます。

        オブジェクト ID の場合:

        ```csharp
        using Microsoft.Identity.Web;
        
        public class AgentService
        {
            private readonly MicrosoftIdentityTokenCredential _credential;
        
            public AgentService(MicrosoftIdentityTokenCredential credential)
            {
                _credential = credential;
            }
        
            // Use object ID to identify the agent's user account
            public async Task<List<string>> ListBlobsForAgentUserByOidAsync(string agentIdentity)
            {
                // Configure for agent identity
                string userOid = "user-object-id";
                _credential.Options.WithAgentUserIdentity(agentIdentity, userOid);
        
                var blobClient = new BlobServiceClient(
                    new Uri("https://myaccount.blob.core.windows.net"),
                    _credential);
        
                var container = blobClient.GetBlobContainerClient("agent-data");
                var blobs = new List<string>();
        
                await foreach (var blob in container.GetBlobsAsync())
                {
                    blobs.Add(blob.Name);
                }
        
                return blobs;
            }
        
            // Use UPN to identify the agent's user account
            public async Task<List<string>> ListBlobsForAgentUserByUpnAsync(string agentIdentity)
            {
                // Configure for agent identity
                string userUpn = "user@contoso.com";
        
                _credential.Options.WithAgentUserIdentity(agentIdentity, userUpn);
        
                var blobClient = new BlobServiceClient(
                    new Uri("https://myaccount.blob.core.windows.net"),
                    _credential);
        
                var container = blobClient.GetBlobContainerClient("agent-data");
                var blobs = new List<string>();
        
                await foreach (var blob in container.GetBlobsAsync())
                {
                    blobs.Add(blob.Name);
                }
        
                return blobs;
            }
        }
        ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/call-api-custom"} -->
## .NETを使用してエージェントからカスタム API を呼び出す - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/call-api-custom
- Service: entra-id / agent-id
- Article date: 2025-11-04
- Summary: IDownstreamApi、MicrosoftIdentityMessageHandler、IAuthorizationHeaderProvider などのさまざまな方法を使用して、エージェントからカスタム保護された API を呼び出す方法について説明します。

エージェントからカスタム API を呼び出すには、複数の方法があります。 シナリオに応じて、 `IDownstreamApi`、 `MicrosoftIdentityMessageHandler`、または `IAuthorizationHeaderProvider`のいずれかを使用できます。 このガイドでは、独自の保護された API を呼び出すためのさまざまな方法について、3 つの方法すべてについて説明します。

エージェントから API を呼び出すには、エージェントが API に対して自身を認証するために使用できるアクセス トークンを取得する必要があります。 *Microsoft.Identity.Web* SDK for .NET を使用して Web API を呼び出すことをお勧めします。 この SDK は、トークンの取得と検証のプロセスを簡略化します。 他の言語の場合は、[Microsoft Entra ID認証 SDK (サイドカー)](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/overview) を使用します。

### 前提条件

- ターゲット API を呼び出す適切なアクセス許可を持つエージェント ID。 On-Behalf-Of フローにはユーザーが必要です。
- ターゲット API を呼び出す適切なアクセス許可を持つエージェントのユーザー アカウント。

### シナリオに基づいて使用する方法を決定する

次の表は、使用する方法を決定するのに役立ちます。 ほとんどのシナリオでは、 `IDownstreamApi`を使用することをお勧めします。

| 方法 | 複雑さ | 柔軟性 | ユースケース(事例) |
| --- | --- | --- | --- |
| `IDownstreamApi` | 低 | 中程度 | 構成による標準 REST API |
| `MicrosoftIdentityMessageHandler` | 中程度 | High | 直接挿入 (DI) と構成可能なパイプラインを使用した HttpClient |
| `IAuthorizationHeaderProvider` | High | 非常に高 | HTTP 要求を完全に制御する |

## [IDownstreamApi を使用する](#tab/idownstream)
`IDownstreamApi` は、3 つのオプションの中から保護された API を呼び出す推奨される方法です。 これは高度に構成可能であり、最小限のコード変更が必要です。 また、トークンの自動取得も提供します。

次の項目が必要な場合は、 `IDownstreamApi` を使用します。

- 標準 REST API を呼び出している
- 構成主導型のアプローチが必要です
- 自動シリアル化/逆シリアル化が必要です
- 最小限のコードを記述する

## [MicrosoftIdentityMessageHandler を使用する](#tab/messagehandler)
`MicrosoftIdentityMessageHandler` は、*Microsoft.Identity.Web.TokenAcquisition* パッケージ内の委任ハンドラーで、HttpClient 要求に認証を追加します。 これは、トークンの自動取得で完全な HttpClient 機能が必要な場合に使用します。

*Microsoft.Identity.Web.TokenAcquisition* パッケージは既に *Microsoft.Identity.Web.AgentIdentities* によって参照されています。

次の項目が必要な場合は、 `MicrosoftIdentityMessageHandler` を使用します。

- HTTP 要求をきめ細かく制御する必要がある
- 複数のメッセージ ハンドラーを作成する場合
- 既存の HttpClient ベースのコードと統合している
- 生の HttpResponseMessage にアクセスする必要がある

## [IAuthorizationHeaderProvider を使用する](#tab/authheaderprovider)
`IAuthorizationHeaderProvider`*Microsoft.Identity.Web* から、HTTP 要求を完全に制御するために、承認ヘッダーに直接アクセスできます。 つまり、必要に応じてヘッダーを追加または変更するなど、ニーズに合わせて認証プロセスをカスタマイズできます。 このメソッドでは、カスタム HTTP ライブラリを使用して API 呼び出しを行うこともできます。

次の項目が必要な場合は、 `IAuthorizationHeaderProvider` を使用します。

- HTTP 要求の構築を完全に制御する必要がある
- 非標準の HTTP API と統合している
- DI なしで HttpClient を使用する必要がある
- カスタム HTTP 抽象化を構築している

---

### API を呼び出す

何が適切かを判断したら、カスタム Web API の呼び出しに進みます。

Warnung

セキュリティ リスクのために、エージェント ID ブループリントの運用環境では、クライアント シークレットをクライアント資格情報として使用しないでください。 代わりに、マネージド ID またはクライアント証明書を使用 [するフェデレーション ID 資格情報 (FIC)](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-config-app-trust-managed-identity) などのより安全な認証方法を使用してください。 これらの方法により、アプリケーション構成内に機密性の高いシークレットを直接格納する必要がなくなり、セキュリティが強化されます。

## [IDownstreamApi を使用する](#tab/idownstream)
1. 必要な NuGet パッケージをインストールします。

    ```bash
    dotnet add package Microsoft.Identity.Web.DownstreamApi
    dotnet add package Microsoft.Identity.Web.AgentIdentities
    ```
2. *appsettings.json*でトークン資格情報オプションと API を構成します。

    ```json
    {
      "AzureAd": {
        "Instance": "https://login.microsoftonline.com/",
        "TenantId": "your-tenant-id",
        "ClientId": "your-blueprint-id",
        "ClientCredentials": [
          {
            "SourceType": "ClientSecret",
            "ClientSecret": "your-client-secret"
          }
        ]
      },
      "DownstreamApis": {
        "MyApi": {
          "BaseUrl": "https://api.example.com",
          "Scopes": ["api://my-api-client-id/read", "api://my-api-client-id/write"],
          "RelativePath": "/api/v1",
          "RequestAppToken": false
        }
      }
    }
    ```
3. ダウンストリーム API サポートを追加するようにサービスを構成します。

    ```csharp
    using Microsoft.AspNetCore.Authentication.OpenIdConnect;
    using Microsoft.Identity.Web;
    
    var builder = WebApplication.CreateBuilder(args);
    
    // Add authentication
    builder.Services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
        .AddMicrosoftIdentityWebApp(builder.Configuration.GetSection("AzureAd"))
        .EnableTokenAcquisitionToCallDownstreamApi()
        .AddInMemoryTokenCaches();
    
    // Register downstream APIs
    builder.Services.AddDownstreamApis(
        builder.Configuration.GetSection("DownstreamApis"));
    
    // Add Agent Identities support
    builder.Services.AddAgentIdentities();
    
    builder.Services.AddControllersWithViews();
    
    var app = builder.Build();
    app.UseAuthentication();
    app.UseAuthorization();
    app.MapControllers();
    app.Run();
    ```
4. `IDownstreamApi`を使用して保護された API を呼び出します。 API を呼び出すときは、 `WithAgentIdentity` または `WithAgentUserIdentity` メソッドを使用して、エージェント ID またはエージェントのユーザー アカウント ID を指定できます。 `IDownstreamApi` は、トークンの取得を自動的に処理し、アクセス トークンを要求にアタッチします。

    - `WithAgentIdentity`の場合は、アプリ専用トークン (自律エージェント) を使用して API を呼び出すか、ユーザーの代わりに (対話型エージェント) API を呼び出します。

        ```csharp
        using Microsoft.Identity.Abstractions;
        using Microsoft.AspNetCore.Authorization;
        using Microsoft.AspNetCore.Mvc;
        
        [Authorize]
        public class ProductsController : Controller
        {
            private readonly IDownstreamApi _api;
        
            public ProductsController(IDownstreamApi api)
            {
                _api = api;
            }
        
            // GET request for app only token scenario for agent identity
            public async Task<IActionResult> Index()
            {
        
                string agentIdentity = "<your-agent-identity>";
                var products = await _api.GetForAppAsync<List<Product>>(
                    "MyApi",
                    "products",
                    options => options.WithAgentIdentity(agentIdentity));
        
                return View(products);
            }
        
            // GET request for on-behalf of user token scenario for agent identity
            public async Task<IActionResult> UserProducts()
            {
        
                string agentIdentity = "<your-agent-identity>";
                var products = await _api.GetForUserAsync<List<Product>>(
                    "MyApi",
                    "products",
                    options => options.WithAgentIdentity(agentIdentity));
        
                return View(products);
            }
        }
        ```
    - `WithAgentUserIdentity`では、ユーザー プリンシパル名 (UPN) またはオブジェクト ID (OID) のいずれかを指定して、エージェントのユーザー アカウントを識別できます。

        ```csharp
        using Microsoft.Identity.Abstractions;
        using Microsoft.AspNetCore.Authorization;
        using Microsoft.AspNetCore.Mvc;
        
        [Authorize]
        public class ProductsController : Controller
        {
            private readonly IDownstreamApi _api;
        
            public ProductsController(IDownstreamApi api)
            {
                _api = api;
            }
        
            // GET request for agent's user account identity using UPN
            public async Task<IActionResult> Index()
            {
        
                string agentIdentity = "<your-agent-identity>";
                string userUpn = "user@contoso.com";
        
                var products = await _api.GetForUserAsync<List<Product>>(
                    "MyApi",
                    "products",
                    options => options.WithAgentUserIdentity(agentIdentity, userUpn));
                return View(products);
            }
        
            // GET request for agent's user account identity using OID
            public async Task<IActionResult> UserProducts()
            {
        
                string agentIdentity = "<your-agent-identity>";
                string userOid = "user-object-id";
        
                var products = await _api.GetForUserAsync<List<Product>>(
                    "MyApi",
                    "products",
                    options => options.WithAgentUserIdentity(agentIdentity, userOid));
        
                return View(products);
            }
        
        }
        ```

## [MicrosoftIdentityMessageHandler を使用する](#tab/messagehandler)
1. 必要な NuGet パッケージをインストールします。

    ```bash
    dotnet add package Microsoft.Identity.Web.AgentIdentities
    ```
2. エージェント ID を使用して認証を追加し、httpClient を `MicrosoftIdentityMessageHandler` に登録するようにサービスを構成します。

    ```csharp
    using Microsoft.AspNetCore.Authentication.OpenIdConnect;
    using Microsoft.Identity.Web;
    using Microsoft.Identity.Abstractions;
    
    var builder = WebApplication.CreateBuilder(args);
    
    builder.Services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
        .AddMicrosoftIdentityWebApp(builder.Configuration.GetSection("AzureAd"))
        .EnableTokenAcquisitionToCallDownstreamApi()
        .AddInMemoryTokenCaches();
    
    // Configure named HttpClient with authentication
    builder.Services.AddHttpClient("MyApiClient", client =>
    {
        client.BaseAddress = new Uri("https://api.example.com");
        client.DefaultRequestHeaders.Add("Accept", "application/json");
        client.Timeout = TimeSpan.FromSeconds(30);
    })
    .AddHttpMessageHandler(sp =>
    {
        var authProvider = sp.GetRequiredService<IAuthorizationHeaderProvider>();
        return new MicrosoftIdentityMessageHandler(
            authProvider,
            new MicrosoftIdentityMessageHandlerOptions
            {
                Scopes = new[] { "api://my-api-client-id/read" },
            });
    });
    
    builder.Services.AddControllersWithViews();
    
    // Add Agent Identities support
    builder.Services.AddAgentIdentities();

    var app = builder.Build();
    app.UseAuthentication();
    app.UseAuthorization();
    app.MapControllers();
    app.Run();
    ```
3. 構成された HttpClient を使用してトークンを取得し、保護された API を呼び出します。 `WithAgentIdentity`または`WithAgentUserIdentity`メソッドを使用して、エージェント ID またはエージェントのユーザー アカウントを指定できます。

    - `WithAgentIdentity`の場合は、アプリ専用トークン (自律エージェント) を使用して API を呼び出すか、ユーザーの代わりに (対話型エージェント) API を呼び出します。

        エージェント ID のアプリ専用トークンを使用して API を呼び出すには、 `RequestAppToken` を `true` に設定します。

        ```csharp
        public class MyService
        {
            private readonly HttpClient _httpClient;
        
            public MyService(IHttpClientFactory httpClientFactory)
            {
                _httpClient = httpClientFactory.CreateClient("MyApiClient");
            }
        
            public async Task<string> CallApiWithAgentIdentity(string agentIdentity)
            {
                // Create request with agent identity authentication
                var request = new HttpRequestMessage(HttpMethod.Get, "/api/data")
                    .WithAuthenticationOptions(options => 
                    {
                        options.WithAgentIdentity(agentIdentity);
                        options.RequestAppToken = true;
                    });
        
                var response = await _httpClient.SendAsync(request);
                response.EnsureSuccessStatusCode();
                return await response.Content.ReadAsStringAsync();
            }
        }
        ```

        エージェント ID に対してユーザーの代わりに API を呼び出すには、 `RequestAppToken` を設定したり、明示的に `false` に設定したりしないでください。

        ```csharp
        public class MyService
        {
            private readonly HttpClient _httpClient;
        
            public MyService(IHttpClientFactory httpClientFactory)
            {
                _httpClient = httpClientFactory.CreateClient("MyApiClient");
            }
        
            public async Task<string> CallApiWithAgentIdentity(string agentIdentity)
            {
                // Create request with agent identity authentication
                var request = new HttpRequestMessage(HttpMethod.Get, "/api/data")
                    .WithAuthenticationOptions(options =>
                    {
                        options.WithAgentIdentity(agentIdentity);
                        options.RequestAppToken = false;
                    });
        
                var response = await _httpClient.SendAsync(request);
                response.EnsureSuccessStatusCode();
                return await response.Content.ReadAsStringAsync();
            }
        }
        ```
    - `WithAgentUserIdentity`を使用するには、UPN または OID のいずれかを指定して、エージェントのユーザー アカウントを識別できます。

        ```csharp
        // Create request with agent's user account identity authentication with UPN
        public async Task<string> CallApiWithAgentUserIdentityByUpn(string agentIdentity, string userUpn)
        {
        
            var request = new HttpRequestMessage(HttpMethod.Get, "/api/userdata")
                .WithAuthenticationOptions(options => 
                {
                    options.WithAgentUserIdentity(agentIdentity, userUpn);
                    options.Scopes.Add("https://myapi.domain.com/user.read");
                });
        
            var response = await _httpClient.SendAsync(request);
            response.EnsureSuccessStatusCode();
            return await response.Content.ReadAsStringAsync();
        }
        
        // Create request with agent's user account identity authentication with OID
        public async Task<string> CallApiWithAgentUserIdentityByOid(string agentIdentity, string userOid)
        {
        
            var request = new HttpRequestMessage(HttpMethod.Get, "/api/userdata")
                .WithAuthenticationOptions(options => 
                {
                    options.WithAgentUserIdentity(agentIdentity, userOid);
                    options.Scopes.Add("https://myapi.domain.com/user.read");
                });
        
            var response = await _httpClient.SendAsync(request);
            response.EnsureSuccessStatusCode();
            return await response.Content.ReadAsStringAsync();
        }
        ```

## [IAuthorizationHeaderProvider を使用する](#tab/authheaderprovider)
1. 必要な NuGet パッケージをインストールします。

    ```bash
    dotnet add package Microsoft.Identity.Web.AgentIdentities
    ```
2. エージェント ID を使用して認証を追加するようにサービスを構成します。

    ```csharp
    using Microsoft.AspNetCore.Authorization;
    using Microsoft.Identity.Abstractions;
    using Microsoft.Identity.Web;
    
    var builder = WebApplication.CreateBuilder(args);
    
    // With Microsoft.Identity.Web
    builder.Services.AddMicrosoftIdentityWebApiAuthentication(builder.Configuration)
        .EnableTokenAcquisitionToCallDownstreamApi();
    
    builder.Services.AddAgentIdentities();
    
    var app = builder.Build();
    
    app.UseAuthentication();
    app.UseAuthorization();
    
    app.Run();
    ```

    - *appsettings.json* で認証資格情報を構成する

    ```json
    {
      "AzureAd": {
        "Instance": "https://login.microsoftonline.com/",
        "TenantId": "your-tenant-id",
        "ClientId": "your-blueprint-id",
        "ClientCredentials": [
          {
            "SourceType": "ClientSecret",
            "ClientSecret": "your-client-secret"
          }
        ]
      }
    }
    ```
3. アクセス トークンを取得して抽出し、Web API を呼び出します。

    - `WithAgentIdentity`の場合は、アプリ専用トークン (自律エージェント) を使用して API を呼び出すか、ユーザーの代わりに (対話型エージェント) API を呼び出します。

        - アプリのみのトークン シナリオでは、 `CreateAuthorizationHeaderForAppAsync` メソッドを使用します。
        - 代理 (OBO) トークン シナリオでは、`CreateAuthorizationHeaderForUserAsync` メソッドを使用します

        ```csharp
        using Microsoft.Identity.Abstractions;
        
        [Authorize]
        public class CustomApiController : Controller
        {
            private readonly IAuthorizationHeaderProvider _headerProvider;
        
            public CustomApiController(IAuthorizationHeaderProvider headerProvider)
            {
                _headerProvider = headerProvider;
            }
        
            // App only token scenario for agent identity
            public async Task<IActionResult> GetBackgroundData()
            {
                // Configure options for the agent identity
                string agentIdentity = "agent-identity-guid";
                var options = new AuthorizationHeaderProviderOptions()
                    .WithAgentIdentity(agentIdentity);
        
                // Acquire an access token for the agent identity
                var authHeader = await _headerProvider.CreateAuthorizationHeaderForAppAsync(
                    scopes: new[] { "api://my-api/.default" }, options: options);
        
                // Call the protected API
                using var client = new HttpClient();
                client.DefaultRequestHeaders.Add("Authorization", authHeader);
        
                var response = await client.GetAsync("https://api.example.com/background");
                var data = await response.Content.ReadFromJsonAsync<BackgroundData>();
        
                return Ok(data);
            }
        
            // On-behalf of user token scenario for agent identity
            public async Task<IActionResult> GetUserData()
            {
                // Configure options for the agent identity
                string agentIdentity = "agent-identity-guid";
                var options = new AuthorizationHeaderProviderOptions()
                    .WithAgentIdentity(agentIdentity);
        
                // Acquire an access token for the agent identity
                var authHeader = await _headerProvider.CreateAuthorizationHeaderForUserAsync(
                    scopes: new[] { "api://my-api/.default" }, options: options);
        
                // Call the protected API
                using var client = new HttpClient();
                client.DefaultRequestHeaders.Add("Authorization", authHeader);
        
                var response = await client.GetAsync("https://api.example.com/background");
                var data = await response.Content.ReadFromJsonAsync<BackgroundData>();
        
                return Ok(data);
            }
        }
        ```
    - `WithAgentUserIdentity`の場合は、UPN または OID を使用してユーザーに代わって API を呼び出します。

        ```csharp
        using Microsoft.Identity.Abstractions;
        
        [Authorize]
        public class CustomApiController : Controller
        {
            private readonly IAuthorizationHeaderProvider _headerProvider;
        
            public CustomApiController(IAuthorizationHeaderProvider headerProvider)
            {
                _headerProvider = headerProvider;
            }
        
            // App only token scenario for agent identity
            public async Task<IActionResult> GetBackgroundData()
            {
                // Configure options for the agent identity
                string agentIdentity = "agent-identity-guid";
                string userUpn = "user@contoso.com";
        
                var options = new AuthorizationHeaderProviderOptions()
                    .WithAgentUserIdentity(agentIdentity, userUpn);
        
                // Create a ClaimsPrincipal to enable token caching
                ClaimsPrincipal user = new ClaimsPrincipal();
        
                // Acquire an access token for the agent identity
                var authHeader = await _headerProvider.CreateAuthorizationHeaderForAppAsync(
                    scopes: new[] { "api://my-api/.default" }, options: options, user: user);
        
                // Call the protected API
                using var client = new HttpClient();
                client.DefaultRequestHeaders.Add("Authorization", authHeader);
        
                var response = await client.GetAsync("https://api.example.com/background");
                var data = await response.Content.ReadFromJsonAsync<BackgroundData>();
        
                return Ok(data);
            }
        
            // On-behalf of user token scenario for agent identity
            public async Task<IActionResult> GetUserData()
            {
                // Configure options for the agent identity
                string agentIdentity = "agent-identity-guid";
                string userUpn = "user@contoso.com";
        
                var options = new AuthorizationHeaderProviderOptions()
                    .WithAgentUserIdentity(agentIdentity, userUpn);
        
                // Create a ClaimsPrincipal to enable token caching
                ClaimsPrincipal user = new ClaimsPrincipal();
        
                // Acquire an access token for the agent identity
                var authHeader = await _headerProvider.CreateAuthorizationHeaderForAppAsync(
                    scopes: new[] { "api://my-api/.default" }, options: options, user: user);
        
                // Call the protected API
                using var client = new HttpClient();
                client.DefaultRequestHeaders.Add("Authorization", authHeader);
        
                var response = await client.GetAsync("https://api.example.com/background");
                var data = await response.Content.ReadFromJsonAsync<BackgroundData>();
        
                return Ok(data);
            }
        }       
        ```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/call-api-microsoft-graph"} -->
## .NET を使用してエージェントから Microsoft Graph APIを呼び出す - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/call-api-microsoft-graph
- Service: entra-id / agent-id
- Article date: 2025-11-04
- Summary: エージェント ID またはエージェントのユーザー アカウントを使用して、エージェントから Microsoft Graph APIを呼び出す方法について説明します。これには、認証の構成と実装の手順が含まれます。

この記事では、エージェント ID またはエージェントのユーザー アカウントを使用して、エージェントから Microsoft Graph APIを呼び出す方法について説明します。

エージェントから API を呼び出すには、エージェントが API に対して自身を認証するために使用できるアクセス トークンを取得する必要があります。 *Microsoft.Identity.Web* SDK for .NET を使用して Web API を呼び出すことをお勧めします。 この SDK は、トークンの取得と検証のプロセスを簡略化します。 他の言語の場合は、[Microsoft Entra ID認証 SDK (サイドカー)](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/overview) を使用します。

### 前提条件

- ターゲット API を呼び出す適切なアクセス許可を持つエージェント ID。 On-Behalf-Of フローにはユーザーが必要です。
- ターゲット API を呼び出す適切なアクセス許可を持つエージェントのユーザー アカウント。

### Microsoft Graph APIを呼び出す

1. *Microsoft.Identity.Web.GraphServiceClient* をインストールして、Graph SDK の認証を処理し、*Microsoft.Identity.Web.AgentIdentities* パッケージをインストールしてエージェント ID のサポートを追加します。

    ```bash
    dotnet add package Microsoft.Identity.Web.GraphServiceClient
    dotnet add package Microsoft.Identity.Web.AgentIdentities
    ```
2. サービス コレクションにMicrosoft Graph ID とエージェント ID のサポートを追加します。

    ```csharp
    using Microsoft.AspNetCore.Authentication.OpenIdConnect;
    using Microsoft.Identity.Web;
    
    var builder = WebApplication.CreateBuilder(args);
    
    // Add authentication (web app or web API)
    builder.Services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
        .AddMicrosoftIdentityWebApp(builder.Configuration.GetSection("AzureAd"))
        .EnableTokenAcquisitionToCallDownstreamApi()
        .AddInMemoryTokenCaches();
    
    // Add Microsoft Graph support
    builder.Services.AddMicrosoftGraph();
    
    // Add Agent Identities support
    builder.Services.AddAgentIdentities();
    
    var app = builder.Build();
    app.UseAuthentication();
    app.UseAuthorization();
    app.Run();
    ```
3. *appsettings.json*で Graph とエージェント ID のオプションを構成します。

    Warnung

    セキュリティ リスクのために、エージェント ID ブループリントの運用環境では、クライアント シークレットをクライアント資格情報として使用しないでください。 代わりに、マネージド ID またはクライアント証明書を使用 [するフェデレーション ID 資格情報 (FIC)](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-config-app-trust-managed-identity) などのより安全な認証方法を使用してください。 これらの方法により、アプリケーション構成内に機密性の高いシークレットを直接格納する必要がなくなり、セキュリティが強化されます。

    ```json
    {
      "AzureAd": {
        "Instance": "https://login.microsoftonline.com/",
        "TenantId": "<your-tenant-id>",
        "ClientId": "<agent-blueprint-client-id>",
        "ClientCredentials": [
          {
            "SourceType": "ClientSecret",
            "ClientSecret": "your-client-secret"
          }
        ]
      },
      "DownstreamApis": {
        "MicrosoftGraph": {
          "BaseUrl": "https://graph.microsoft.com/v1.0",
          "Scopes": ["User.Read", "User.ReadBasic.All"]
        }
      }
    }
    ```

    Note

    エージェントに必要なMicrosoft Graphアクセス許可のみを構成し、設定した`Scopes`がコードで呼び出す Graph リソースと一致していることを確認します。 これらの例では、 `User.Read` と `User.ReadBasic.All`を使用します。他のリソースを呼び出す場合は、対応するアクセス許可が必要です。
4. 現在、`GraphServiceClient` をサービスやサービス プロバイダーから取得し、それを注入して Microsoft Graph を呼び出すことができます。

- エージェント ID の場合は、 `WithAgentIdentity` メソッドを使用して、アプリ専用トークン (自律エージェント) またはユーザー トークンの代理 (対話型エージェント) を取得できます。 アプリのみのトークンの場合は、 `RequestAppToken` プロパティを `true` に設定します。 ユーザー トークンの代理委任の場合は、 `RequestAppToken` プロパティを設定したり、明示的に `false` に設定したりしないでください。

    ```csharp
    using Microsoft.Graph;
    using Microsoft.Identity.Web;
    
    // Get the GraphServiceClient
    GraphServiceClient graphServiceClient = serviceProvider.GetRequiredService<GraphServiceClient>();
    
    string agentIdentity = "agent-identity-guid";
    
    // Call Microsoft Graph APIs with the agent identity for app only scenario
    var usersAppOnly = await graphServiceClient.Users
        .GetAsync(r => r.Options.WithAuthenticationOptions(options =>
        {
            options.WithAgentIdentity(agentIdentity);
            options.RequestAppToken = true; // Set to true for app only
        }));
    
    // Call Microsoft Graph APIs with the agent identity for on-behalf of user scenario
    var usersOnBehalfOfUser = await graphServiceClient.Users
        .GetAsync(r => r.Options.WithAuthenticationOptions(options =>
        {
            options.WithAgentIdentity(agentIdentity);
            options.RequestAppToken = false; // False to show it's on-behalf of user
        }));
    ```

    - エージェントのユーザー アカウント ID の場合は、ユーザー プリンシパル名 (UPN) またはオブジェクト ID (OID) のいずれかを指定して、 `WithAgentUserIdentity` メソッドを使用してエージェントのユーザー アカウントを識別できます。

        ```csharp
        using Microsoft.Graph;
        using Microsoft.Identity.Web;
        
        // Get the GraphServiceClient
        GraphServiceClient graphServiceClient = serviceProvider.GetRequiredService<GraphServiceClient>();
        
        string agentIdentity = "agent-identity-guid";
        
        // Call Microsoft Graph APIs with the agent's user account identity using UPN
        string userUpn = "user-upn";
        var me = await graphServiceClient.Me
            .GetAsync(r => r.Options.WithAuthenticationOptions(options =>
                options.WithAgentUserIdentity(agentIdentity, userUpn)));
        
        // Or using OID
        string userOid = "user-object-id";
        var meByOid = await graphServiceClient.Me
            .GetAsync(r => r.Options.WithAuthenticationOptions(options =>
                options.WithAgentUserIdentity(agentIdentity, userOid)));
        ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/concept-agent-id-design-patterns"} -->
## Microsoft Entra エージェント ID の設計パターン - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/concept-agent-id-design-patterns
- Service: entra-id / agent-id
- Article date: 2026-04-03
- Summary: ブループリント、エージェント ID、エージェントのユーザー アカウントなど、AI エージェント アーキテクチャを Microsoft Entra エージェント ID にマップする方法について説明します。

Microsoft Entra エージェント ID には、新しい ID コンストラクトと、既存の認証と承認のパターンに関する新しい考え方が導入されています。 これらのコンストラクトがどのように一緒に適合するかを理解するには、一般的な AI エージェントのデプロイ パターンと、それらが Microsoft Entra エージェント ID にどのようにマップされるかを確認すると便利です。

この記事では、一般的な AI エージェントのデプロイ パターンと、それらが Microsoft Entra エージェント ID にどのようにマップされるかについて説明します。 この記事では、まず、主要な ID の概念を確認し、アクセス許可と信頼の境界について説明した後、一般的なデプロイ パターンについて説明します。

作成するブループリントとエージェント ID の数に関する詳細な決定ガイダンスについては、「 [エージェント ID アーキテクチャの計画](https://learn.microsoft.com/ja-jp/entra/agent-id/how-to-plan-agent-identity-architecture)」を参照してください。

### 主な概念

Microsoft Entra エージェント ID の基盤となるコンポーネントを次に示します。 初めて使用する場合は、この記事に進む前に [、Microsoft Entra エージェント ID の主要な概念](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/key-concepts) から始めてください。

#### アイデンティティ コンストラクト

この記事で説明するパターン全体で、次の ID コンストラクトが使用されます。

- **[エージェント ID ブループリント](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/agent-blueprint)**: 1 つ以上のエージェント ID のテンプレートと認証基盤。 資格情報とポリシーが保持され、そこから作成されたすべてのエージェント ID に適用されます。
- **[エージェント ID ブループリント プリンシパル: ブループリント](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/agent-blueprint#agent-identity-blueprint-principals)**がテナントに追加されたときに作成された Microsoft Entra オブジェクト。 実際にトークンを取得し、エージェント ID を作成し、ブループリントに代わって監査ログに表示されます。
- **[エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/agent-identities)**: ダウンストリーム リソースに対する独自のアクセス許可を持つ、特定の AI エージェントのランタイム ID。
- **[エージェントのユーザー アカウント](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/agent-users)**: エージェント ID とペアになったオプションの 1:1 アカウント。エージェントがユーザー オブジェクトを必要とするシステムにアクセスする必要がある場合にのみ必要です。

#### アクセス許可モデル

ブループリント レベルのアクセス許可とエージェント ID レベルのアクセス許可は、さまざまな目的に役立ちます。

- **ブループリントのアクセス許可** は、ブループリントから作成されたすべてのエージェント ID で共有される最小限のアクセス許可を表します。 すべてのエージェント ID を共通のベースラインで開始する場合は、ブループリントで継承可能なアクセス許可を使用します。
- **エージェント ID のアクセス許可は、** 特定のエージェントに対する差別化されたアクセス許可を表します。 これらのアクセス許可は、同じシステム内の異なるエージェントがダウンストリーム リソースへの異なるアクセスを必要とする場合に使用します。

詳細については、「[Microsoft Entra エージェント ID で](https://learn.microsoft.com/ja-jp/entra/agent-id/configure-inheritable-permissions-blueprints)ブループリントと承認[の継承可能なアクセス許可を構成する」](https://learn.microsoft.com/ja-jp/entra/agent-id/authorization-agent-id)を参照してください。

#### 信頼境界

**信頼境界**とは、1 つの侵害が境界全体に影響すると見なされる共有リスク サーフェスを指します。 個別のサービス アカウント、シークレット、およびネットワーク セグメントを持つ個別のプラットフォームで実行されているエージェントは、信頼境界を共有しません。

信頼境界はアプリケーションの脅威モデリングの決定であり、Microsoft Entra エージェント ID によって定義されるものではありません。

### デプロイ パターン

次のパターンは、実際のエージェントのデプロイに基づいています。 各エージェント アーキテクチャ、その ID 構造、および関連するアクセス許可とガバナンスに関する考慮事項について説明します。

#### ローコード シングルトン エージェント

特定のタスクを支援する 1 つのエージェント。通常は、Microsoft Copilot Studio のような低コードまたはコードなしのプラットフォーム上に構築されます。 エージェントは、常にサインインしているユーザーの代わりに動作するか (対話型)、または常にそれ自体 (自律的) として機能します。

**構造：** 1 つのブループリント→ 1 つのエージェント ID

単一のエージェント ID を持つブループリントは冗長に見えるかもしれませんが、このブループリントはエージェントに一貫した条件付きアクセス ポリシー、監視、ガバナンス、監査エントリを提供します。これは、マルチエージェント システムに必要なインフラストラクチャと同じで、最小限のセットアップで実現します。

**アクセス 許可：** エージェント ID に直接アクセス許可を付与します。 ブループリントの継承可能なアクセス許可は、通常、シングルトン ケースでは必要ありません。

**エージェントのユーザー アカウント:** エージェントが Exchange、Teams、またはユーザー オブジェクトを必要とする別のシステムにアクセスする必要がある場合を除き、必須ではありません。

#### ドメイン ワーカー (シーケンシャル マルチエージェント)

複数のエージェントが緊密に結合されたシーケンシャル ワークフローで連携し、共通のドメイン目標に対応します。 通常、エージェントはコードベースを共有し、同じランタイム環境 (たとえば、同じ Kubernetes 名前空間またはコンテナー) で実行され、同じセキュリティ体制を持ちます。 各エージェントには、個別の責任があり、ダウンストリーム リソースへのアクセスが異なります。 このパターンは、マルチエージェント設計の [シーケンシャル オーケストレーション](https://learn.microsoft.com/ja-jp/azure/architecture/ai-ml/guide/ai-agent-design-patterns#sequential-orchestration-example) にマップされます。

**構造：** 1 つのブループリント→複数のエージェント ID (エージェント ロールごとに 1 つ)

ここでは、すべてのエージェントが同じ信頼境界を共有するため、1 つのブループリントを使用することが適切です。 各エージェントは、監査ログとサインイン ログでアクションを特定のエージェントに属性付けできるように、独自のエージェント ID を取得し、各エージェントがダウンストリーム リソースに対して異なるアクセス許可を保持できるようにします。

*例: 小売製品管理システムには、店舗在庫、製品比較、仕入先在庫の 3 つのエージェントがあります。 3 つすべてが同じ Kubernetes 名前空間で実行され、同じチームによってビルドされます。 3 つのエージェント ID を持つ 1 つのブループリントを使用し、それぞれが独自のリソースをスコープとするアクセス許可を持ちます。*

**アクセス 許可：** ブループリントで共有ベースラインのアクセス許可を継承可能として設定します。 ロール固有のアクセス許可を各エージェント ID に直接割り当てます。

**エージェントのユーザー アカウント:** 通常、ドメイン ワーカー エージェントには必要ありません。

#### ドメイン ワーカーとの同時オーケストレーター

オーケストレーター エージェントは、受信タスクに基づいて異なるドメイン ワーカーを動的にアクティブ化します。 ドメイン ワーカーは、異なるプラットフォームで実行され、異なるチームによって運用され、信頼境界を越える場合があります。 このパターンは、マルチエージェント設計の [同時実行オーケストレーション](https://learn.microsoft.com/ja-jp/azure/architecture/ai-ml/guide/ai-agent-design-patterns#concurrent-orchestration) にマップされます。

**構造：**

- ブループリント A → オーケストレーター エージェント アイデンティティ
- ブループリント B → ドメイン ワーカー エージェント 用の ID (ロールごとに 1 つ、信頼境界を越えるグループ)
- ブループリント C →別のドメイン ワーカー グループ (別のチームまたはプラットフォームによって運用されている場合)

ドメイン ワーカーは信頼の境界を越えるため (個別のランタイム、シークレット、またはチーム)、個別のブループリントが必要です。 ブループリントの資格情報は、その信頼ドメインに特化しているため、あるドメインにおける侵害がピアエージェントに影響を与えることはありません。

**エフェメラル エージェント ID:** このパターンのバリアントでは、エフェメラル エージェント ID が使用されます。 オーケストレーターは、特定の対話 (メンテナンス サブシステムとの調整など) を容易にするために実行時に一時エージェント ID を作成し、ブループリントから継承されたアクセス許可を付与し、セッションの終了時に ID を削除します。 これにより、ブラスト半径がタスクの期間に制限されます。

注

エフェメラル エージェント ID の作成は実行時に行われます。これにより、非決定的な待機時間が発生します。 待機時間が影響を受けやすいシナリオで、このトレードオフを評価します。

**アクセス 許可：** オーケストレーターと各ドメイン ワーカー グループには、それぞれのブループリントとエージェント ID をスコープとする独自のアクセス許可セットがあります。

**エージェントのユーザー アカウント:** 特定のドメイン ワーカーがユーザー オブジェクト依存リソースにアクセスする必要がある場合を除き、通常はオーケストレーターまたはドメイン ワーカー レベルでは必要ありません。

#### ユーザーごとのエージェント (small-n)

ユーザーまたは組織単位ごとに個別のエージェント ID が作成されます。 たとえば、SOC アナリスト エージェントには、クラウド環境ごとに 1 つのインスタンスがある場合や、監査エージェントに部門ごとに 1 つのインスタンスがある場合があります。 エージェント ID の数は、ディレクトリ ユーザーごとに 1 つではなく、中程度 (数十から数百) です。

**構造：** ユーザー、部門、または環境ごとに 1 つのエージェント ID を→する 1 つのブループリント

このパターンは、各エージェント インスタンスに異なるアクセス許可、異なる監査境界、または独立したライフサイクルが必要な場合に適しています (たとえば、部門のエージェントを他のユーザーに影響を与えずに無効にすることができます)。

**アクセス 許可：** 各エージェント ID には、そのユーザーまたは組織単位をスコープとするアクセス許可が保持されます。 ブループリントから継承可能なアクセス許可は最小ベースラインを設定し、各エージェント ID には必要に応じて追加のアクセス許可が付与されます。

**エージェントのユーザー アカウント:** 各エージェントがユーザーまたは部門の名前付き担当者 (たとえば、担当地域に代わって電子メールを受信する専用のセールス エージェント) として機能する場合は、各エージェント ID をエージェントのユーザー アカウントとペアリングすることを検討してください。

#### デジタルワーカー(完全自律エージェント)

完全に自律的なエージェントは、デジタル従業員として機能し、通常は人の従業員のために予約されたリソース (Exchange メールボックス、OneDrive 共有、Teams プレゼンス) でプロビジョニングされます。 これはエージェントの自律性の最高レベルです。

**構造：** 1 つのブループリント→ 1 つのエージェント ID→ 1 つのエージェントのユーザー アカウント

各デジタル ワーカーには、独自のエージェントのユーザー アカウントが必要です。 エージェント ID とエージェントのユーザー アカウントの間の 1 対 1 の関係は固定されています。複数のエージェント ID 間でエージェントのユーザー アカウントを共有することはできません。

*例: 電子メールに応答し、組織図に人間のマネージャーが割り当てられる、実際のメールボックスを持つ AI 営業担当者 (グローバル アドレス一覧にリストされています)。*

**アクセス 許可：** エージェントのユーザー アカウントに、必要な特定の Exchange、Teams、および OneDrive のアクセス許可を付与します。 ユーザー オブジェクトを必要としないシステムに対して、エージェント ID アプリケーション レベルのアクセス許可を付与します。 詳細については、「 [エージェントに Microsoft 365 へのアクセスを許可する](https://learn.microsoft.com/ja-jp/entra/agent-id/grant-agent-access-microsoft-365)」を参照してください。

**エージェントのユーザー アカウント:** 必須。 デジタル ワーカー エージェント ID ごとに 1 つのエージェントのユーザー アカウントを作成します。

### 回避するパターン

#### スケールアウト レプリカに個別のエージェント ID は必要ありません

同じエージェント コード (スケールアウト) の複数のインスタンスを実行する場合、個別のエージェント ID は必要ありません。 スケールアウトはランタイムの問題です。ブループリントはエージェント ID としてトークンを取得し、エージェントの複数のインスタンスはすべて同じ ID で同時に実行できます。 レプリカごとに個別のエージェント ID を作成すると、監査、アクセス制御、またはアカウンタビリティの利点なしに、ディレクトリ オブジェクトと管理オーバーヘッドが追加されます。

#### メモリとコンテキストの管理では、個別のエージェント ID は必要ありません

エージェント メモリは通常、共有データ ストア (Azure Cache for Redis や Azure AI 検索 など) であり、取得時にセッション ID でデータがフィルター処理されます。 そのデータ ストアへのアクセスは、セッションごとに個別の ID ではなく、エージェント ID のアクセス許可によって制御されます。 メモリ分離用にエージェント ID を分離すると、セキュリティ上の利点なしに複雑さが増します。

#### ディレクトリでスケールされたオブジェクトごとのエージェント ID を使用しない

現在、大量の会議、ドキュメント、または一時オブジェクトごとに 1 つのエージェント ID を作成することは、ディレクトリ レベルの ID では実用的ではありません。 高フラックスのシナリオでは、共有エージェント ID を使用し、アプリケーション 層のセッションまたはコンテキスト識別子に依存して相互作用を区別します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/concept-agent-identity-deletion"} -->
## エージェント ID の削除のしくみについて説明します - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/concept-agent-identity-deletion
- Service: entra-id / agent-id
- Article date: 2026-05-15
- Summary: エージェント ID ブループリントを削除すると、Microsoft Entraで子エージェント ID の自動クリーンアップがトリガーされる方法と、削除されたオブジェクトを復元する方法について説明します。

エージェント ID ブループリントまたはそのプリンシパルを削除すると、Microsoft Entra*cascade cleanup* プロセスを使用して、すべての子エージェント ID とエージェントのユーザー アカウントが自動的にクリーンアップされます。 各クエリを手動で実行して削除する必要はありません。 この連鎖クリーンアップがいつどのように行われるかを理解することは、必要に応じて復元を計画するのに役立ちます。

エージェント ID ブループリントとそれに関連付けられているオブジェクトは、Microsoft Entraの他のアプリ登録およびサービス プリンシパルと同じ論理的な削除とハード削除の動作に従います。 そのプロセスの完全な概要については、「 [アプリケーションの削除と回復に関する FAQ」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-recover-faq)を参照してください。 この記事では、エージェント ID の削除に固有の内容について説明します。

### オブジェクトの関係性

エージェント ID の削除ライフサイクルには、次のオブジェクトが関係します。 連鎖クリーンアップ プロセスは、これらのリレーションシップに基づいています。

| オブジェクト | ディレクトリ オブジェクトの種類 | リレーションシップ |
| --- | --- | --- |
| エージェント ID ブループリント | アプリケーション | ブループリント プリンシパルの親 |
| エージェント ID 設計図の主例 | サービス プリンシパル | ブループリントのプリンシパル |
| エージェント識別子 | サービス プリンシパル | ブループリント プリンシパルの子 |
| エージェントのユーザー アカウント | ユーザー | エージェントの識別子と1:1でペアリングされています。 |

### 無効化と削除

ブループリントを削除する前に、無効にすることが適切なアクションかどうかを検討してください。

- **無効**: ブループリント プリンシパルまたはそのエージェント ID が認証されないようにしますが、すべてのオブジェクトは所定の位置に残ります。 エージェントのアクティビティを一時的に停止したり、問題を調査したり、エージェントを徐々に使用停止したりする場合に使用します。 オブジェクトはディレクトリに残り、クォータにカウントされます。
- **削除**: エージェント ID ブループリントまたはそのエージェント ID ブループリント プリンシパルをディレクトリから削除し、子エージェント ID の連鎖クリーンアップをトリガーします。 これは、ブループリントとそのブループリントから作成されたすべてのエージェントを完全に廃止する場合に使用します。 30 日間の論理的な削除期間の有効期限が切れた後は、削除を元に戻すことはできません。

エージェント ID の無効化の詳細については、「 [エージェント ID の無効化」を](https://learn.microsoft.com/ja-jp/entra/agent-id/disable-agent-identities)参照してください。

### カスケード式クリーンアップ

エージェント ID ブループリントまたはそのエージェント ID ブループリント プリンシパルを削除すると、Microsoft Entra は関連するすべてのエージェント ID とエージェントのユーザー アカウントを自動的に論理的削除します。 このクリーンアップは非同期です。

カスケード プロセスは次のように機能します。

1. **エージェント ID ブループリントまたはエージェント ID ブループリント プリンシパルを削除**します。オブジェクトは論理的に削除され、ごみ箱に移動します。
2. **Microsoft Entra は自動クリーンアップをトリガーします**: バックグラウンド タスクは、削除されたブループリントに関連付けられているすべての子エージェント ID とエージェントのユーザー アカウントを論理的に削除します。
3. **オブジェクトは 30 日間復元可能**です。論理的に削除されたオブジェクトは、30 日以内に復元できます。 その後、完全に削除されます。

Important

バックグラウンド クリーンアップの実行前にエージェント ID ブループリント プリンシパルを復元しても、子エージェント ID は影響を受けなくなります。 クリーンアップの実行後、各子 ID を個別に復元する必要があります。 エージェント ID ブループリント プリンシパルを復元しても、既に発生した連鎖削除は元に戻りません。

#### 監査ログでの連鎖クリーンアップ

バックグラウンド クリーンアップ タスクがエージェント ID を削除すると、削除はテナントの [Microsoft Entra 監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)に表示されます。 これらのエントリには、次の特性があります。

| 監査ログ フィールド | 価値 |
| --- | --- |
| **アクティビティ** | サービス プリンシパルを削除する |
| **開始者 (アクター)** | アプリケーション: *エージェント ID の削除タスク* |
| **アクター アプリ ID** | (空白) |

アクターは、アプリ ID のない **エージェント ID の削除タスク** という名前のアプリケーションとして表示されます。 これは、クリーンアップは、外部アプリケーションやユーザーではなく、内部Microsoft Entraバックグラウンド プロセスによって実行されるためです。

Note

カスケード クリーンアップは、ブループリントが削除された後に非同期的に実行されます。 すぐには実行されない可能性があります。ブループリントの削除と子エージェント ID のクリーンアップの間に、数時間または数日の遅延が発生する可能性があります。 各子 ID の削除は、個別の監査ログ エントリとして表示されます。

### 孤立したオブジェクトとクォータに関する考慮事項

エージェントIDテンプレートのプリンシパルが完全に削除されると、削除されなかった関連エージェントIDおよびエージェントのユーザー アカウントが**孤立オブジェクト**になり、ソフト削除されます。 孤立したオブジェクトは認証できませんが、30 日間のリテンション期間が経過した後に完全に削除されるまで、ディレクトリ クォータにカウントし続けます。

エージェント ID の削除は、他のMicrosoft Entra オブジェクトと同じクォータ 規則に従います。 ソフト削除されたオブジェクトは、完全に削除されるまでクォータ制限にカウントされ続けます。 一般的なクォータ情報については、「[Microsoft Entra サービスの制限と制限](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions)を参照してください。

エージェント ID に固有の考慮事項の 1 つ:アプリ専用のアクセス許可を使用していて、ブループリントのエージェント ID の上限が 250 である場合、エージェント ID を削除しても、完全に削除されるまで領域は解放されません。 既定では、完全な削除は、30 日間のリテンション期間が経過した後に自動的に行われます。 すぐにクォータを解放する必要がある場合は、論理的に削除されたエージェント ID を強制的に完全に削除できます。 手順については、「 [エージェント ID オブジェクトを完全に削除する」を](https://learn.microsoft.com/ja-jp/entra/agent-id/howto-delete-agent-identity#permanently-delete-agent-identity-objects)参照してください。 エージェント ID ブループリントは、アプリ専用のアクセス許可を使用する場合も、この 250 の制限に従います。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/concept-inheritable-permissions"} -->
## Microsoft Entra エージェント IDの継承可能なアクセス許可

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/concept-inheritable-permissions
- Service: entra-id / agent-id
- Article date: 2026-04-30
- Summary: Microsoft Entra エージェント IDのエージェント ID ブループリントに必要なリソース アクセス宣言と継承可能なアクセス許可の違いについて説明します。

エージェント ID ブループリントを構築するときに、必要 *なリソース アクセス* と *継承可能な*アクセス許可という 2 つの主要なアクセス許可関連の構成があります。 これらの構成は連携して、エージェントに必要なもの、同意時に管理者が確認する内容、およびエージェント ID へのアクセス許可のフローを定義します。

これらの構成と、それらの構成が承認に与える影響の関係を理解することは、エージェントブループリントを設計する開発者と、エージェントを組織にオンボードする管理者の両方にとって不可欠です。

### 必要なリソース アクセス

必要なリソース アクセスは、エージェント ID ブループリントの API の初期宣言と、ブループリントの子エージェント ID が動作するために必要なアクセス許可です。 これは、一連のターゲット リソース アプリケーションと、エージェントが要求する特定の委任されたスコープとアプリケーション ロールとして表されます。

必要なリソース アクセスは、エージェントの静的同意アクセス許可の一覧として機能します。 テナント管理者がエージェントの承認を確認すると、この一覧によって同意の決定が明示的かつレビュー可能になります。 *「このエージェントは何を機能させる必要があるか」という質問に*答えます。

動的同意では、アクセス許可が明示的に要求され、リソース アプリが継承可能として構成されている場合でも、継承されるアクセス許可を付与できます。 ただし、動的に要求されたアクセス許可は、必要なリソース アクセスでも宣言されていない限り、前もって表示されません。

必要なリソース アクセスの主な特性:

- エージェントが初期エクスペリエンスに必要とするアクセス許可のベースライン セットを宣言します。
- 同意とオンボード プロセス中にテナント管理者に表示されます。
- 権限付与ではなく宣言です。 承認には管理者の同意が必要です。

### 継承可能なアクセス許可

継承可能なアクセス許可は、エージェント ID ブループリントで構成されたリソース アプリの一覧であり、そのブループリントから作成されたエージェント ID によって自動的に継承できるアクセス許可を定義します。 管理者がエージェント ID ブループリント プリンシパルにアクセス許可を付与し、それらのアクセス許可が継承可能としてリストされているリソース アプリからのアクセス許可である場合、組織内のそのブループリントから作成されたすべての現在および将来のエージェント ID は、トークンを使用してそれらのアクセス許可を自動的に受け取ります。

継承可能なアクセス許可は、一般的なデプロイの課題に対処します。複数の環境または部署間で同じエージェントの複数のインスタンスがある場合、管理者がすべてのエージェント ID に対して同じアクセス許可に対して再同意することを望まない。 継承可能なアクセス許可を使用すると、管理者はブループリント レベルで 1 回承認し、その承認が自動的に適用されます。

#### 継承の 2 つの条件

エージェント ID によって権限を継承するには、次の *両方* の条件を満たす必要があります。

- **リソース スコープ、ロール、またはその両方は、**エージェント ID ブループリントの継承可能なアクセス許可の構成に一覧表示されている必要があります。
- **アクセス許可は、次の方法で付与する必要があります**。
    - *必要なリソース アクセス*を使用した静的同意、または
    - 同意要求で明示的に宣言されたアクセス許可を持つ動的同意。

いずれかの条件がない場合、継承は行われません。

#### 継承可能なアクセス許可に含まれるもの

継承可能なアクセス許可では、次の両方がサポートされます。

- **委任されたスコープ**: エージェントの委任されたアプリケーションアクセス許可アクセス トークン `scp` 要求に表示されます。
- **アプリケーション ロール**: エージェントのアプリケーションアクセス許可トークン `roles` 要求に表示されます。

#### 継承パターン

リソース アプリごとに次のパターンがサポートされています。

| パターン | 説明 |
| --- | --- |
| **すべて許可** | 指定したリソース アプリで使用可能なすべての委任されたスコープまたはアプリケーション ロールを継承します。 ブループリント プリンシパルに対して新しく付与されたスコープまたはロールが自動的に含まれます。 |
| **なし** | 指定したリソース アプリのスコープまたはロールを継承しません。 このパターンを使用して、スコープやロールの継承を個別に明示的に無効にします。 |

スコープとロールは、同じリソース アプリで個別に構成できます。 たとえば、ロールを継承しないときにすべてのスコープを継承したり、その逆を行ったりすることができます。

### 宣言、許可、継承

必要なリソース アクセスと継承可能なアクセス許可は *構成*です。単独で承認を付与することはありません。 宣言された内容、付与された内容、継承された内容の違いを理解することが重要です。

| レイヤー | それは何か | 誰が制御する | 影響 |
| --- | --- | --- | --- |
| **必要なリソース アクセス** | エージェントが機能するために必要な API とアクセス許可の一覧 | 開発者 (ブループリント上) | 同意レビュー中に管理者に表示されます。 アクセス権は付与されません。 |
| **継承可能なアクセス許可** | 継承の対象となるリソース アプリの一覧 | 開発者 (ブループリント上) | エージェント ID へのアクセス許可フローを持つリソース アプリを定義します。 アクセス権は付与されません。 |
| **ブループリントの重要要件に対する合意** | テナントの管理者によってブループリントプリンシパルに与えられる権限 | テナント管理者 | 承認を付与します。 リソース アプリも継承可能として一覧表示されている場合、アクセス許可はすべてのエージェント ID に送信されます。 |
| **エージェント ID に対するユーザーまたはエージェントの同意** | 特定のエージェント ID に直接付与されるアクセス許可 | テナント管理者 | その特定のエージェント ID に対してのみ承認を付与します。 |
| **トークンの有効なアクセス許可** | 継承された＋直接付与された権限が統合されたセット | プラットフォーム (トークン発行時) | エージェント ID が実行時に実際に実行できること。 |

Note

継承されたアクセス許可は、Microsoft Entra 管理センターまたは Microsoft Graph を介したエージェント ID に対するアクセス許可として表示されません。 これらは、実行時にトークンの内容でのみ監視できます。 プラットフォームは、トークンの発行中に継承されたアクセス許可と直接付与されたアクセス許可を統合します。

### アクセス許可構成の早見表

次のクイック ルールを使用します。

- ブループリント レベルでの静的承認は、必要なリソース アクセスにアクセス許可が含まれているかどうかに依存します。
- ブループリント レベルでの動的同意は、アクセス許可が必要なリソース アクセスに含まれていないが、アクセス許可を明示的に要求する必要がある場合でも機能します。
- エージェント ID への継承は、リソース アプリが継承可能として構成されているかどうかによって異なります。
- 事前の可視性は、アクセス許可が必要なリソースへのアクセスにあるかどうかによって異なります。

#### 静的同意 (ブループリント プリンシパル)

| 必要なリソースアクセスの許可はありますか？ | リソース アプリは継承可能ですか? | エージェント ID によって継承されますか? | 管理者にすぐに見える形で表示されますか？ |
| --- | --- | --- | --- |
| Yes | Yes | Yes | Yes |
| Yes | No | No | Yes |
| No | Yes | No | No |
| No | No | No | No |

#### 動的同意 (ブループリントの基本方針、明示的に求められた許可)

| リクエストされたリソースアクセスの許可 | リソース アプリは継承可能ですか? | エージェント ID によって継承されますか? | 管理者にすぐ表示されますか? |
| --- | --- | --- | --- |
| Yes | Yes | Yes | Yes |
| Yes | No | No | Yes |
| No | Yes | Yes | No |
| No | No | No | No |

エージェント ID に対する直接の同意は、すべてのケースで引き続き使用できますが、これらの許可は、その特定のエージェント ID にのみ適用されます。

### ベスト プラクティス

エージェント ブループリントに必要なリソース アクセスと継承可能なアクセス許可を構成する場合は、セキュリティ、使いやすさ、および将来のスケーラビリティのバランスを取ります。

- **事前アクセス許可を最小限に抑えます。** 必要なリソース アクセスにエージェントのコア機能に不可欠なリソース アクセスのみを含めます。 インストール時に不要なアクセス許可を要求すると、摩擦が増し、テナント管理者との信頼が低下します。
- **将来のアクセス許可を事前に宣言します。** 継承可能なアクセス許可の一覧で、今後のエージェント機能に必要になる可能性があるリソース アプリを指定します。 この透明性により、管理者は将来の同意要求を予測でき、環境間での展開がよりスムーズになります。
- **再利用可能な場合は、継承可能なアクセス許可を使用します。** 継承可能なアクセス許可を使用して、管理者がブループリント レベルで 1 回同意を付与し、その承認が、複数のデプロイや環境全体を含むすべてのエージェント ID に自動的に適用されるようにします。 アクセス許可が必要な場合は、リソース アプリを継承可能にして、管理者が各エージェント ID に個別に付与する必要がないようにすることをお勧めします。
- **ガバナンスをシンプルで予測可能な状態に保ちます。** 必要なアクセス許可と後で要求される可能性のあるアクセス許可を明示的に定義することで、組織は明確なアクセス制御を維持し、予期しないアクセス許可のエスカレーションを回避するのに役立ちます。
- **セキュリティへの影響を確認します。** 継承可能なアクセス許可が過剰なアクセスを許可したり、必要以上に機密性の高いリソースを公開したりしないようにします。 コンプライアンスを維持し、リスクを最小限に抑えるために、アクセス許可リストを定期的に監査します。

### シナリオの例

次のシナリオは、さまざまなアクセス許可構成がさまざまな展開ニーズにどのように対応するかを示しています。

#### シナリオ 1: エージェントには、後でアクセス許可を必要とするオプションの機能があります

Priya は、ナレッジ ベースからの質問に回答する IT ヘルプ デスク エージェントを構築しています。 Priya は、顧客が後でインシデントの作成や Teams への投稿などのオプションのアクションを有効にすることを期待しています。 Priya は、必要なリソース アクセスを空または最小限のままにします。 彼女は、継承可能なアクセス許可の一覧でエージェントが使用するリソース アプリを定義します。 会社がアクション機能を有効にすると、管理者はブループリント プリンシパルに対して必要なアクセス許可を 1 回付与し、その承認はすべてのデプロイで再利用されます。

#### シナリオ 2: エージェントには、継承可能なアクセス許可が必要です

Mateo は、ユーザー プロファイルの読み取りとタスクの作成にMicrosoft Graphアクセスする必要がある新しい雇用オンボーディング エージェントを構築しています。 Mateo は、必要なリソース アクセスのベースライン Graph アクセス許可を一覧表示し、Graph リソース アプリを継承可能なアクセス許可の一覧に追加します。 彼の会社がエージェントを複数の部署にロールアウトすると、管理者レビューは一貫しています。毎回同じアクセス許可が要求され、継承可能な指定によって、繰り返しの承認作業が減ります。

#### シナリオ 3: エージェントには継承できないアクセス許可が必要

Lin は、機密性の高いタスクを実行するために小規模な管理者チームによって使用される特権運用エージェントを構築しています。 エージェントには、すぐに高い特権のアクセス許可が必要です。 Lin は、必要なリソース アクセスにこれらを含めますが、継承可能なアクセス許可の一覧には意図的に追加しません。 彼女の会社では、各インストールに対して新たな明示的な管理者の決定が必要です。これにより、高特権アクセスの権限の蔓延が減少します。

#### シナリオ 4: エージェントには、組織ごとに異なるアクセス許可が必要です

Aisha は、コンプライアンス証拠コレクター エージェントを構築しています。 一部のテナントでは、Microsoft 365監査ソースからプルする必要があります。また、SharePoint サイトからプルする必要があるテナントもあります。 Aisha は、必要なリソース アクセスで小さなコア セットを定義し、継承可能なアクセス許可の一覧に使用可能なリソースの完全なメニューを一覧表示します。 各組織は、アーキテクチャに一致するアクセス許可のみを付与し、継承可能なアプローチにより、ロールアウト中に繰り返される承認が減ります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/configure-inheritable-permissions-blueprints"} -->
## エージェント ID ブループリントの継承可能なアクセス許可を構成する

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/configure-inheritable-permissions-blueprints
- Service: entra-id / agent-id
- Article date: 2026-05-21
- Summary: OAuth 2.0 の委任されたアクセス許可スコープとアプリケーション ロールをエージェント ID に自動的に付与するように、エージェント ID ブループリントの継承可能なアクセス許可を構成する方法について説明します。

委任されたスコープとアプリケーション ロールの基本セットを事前に認証するように、エージェント ID ブループリントに継承可能なアクセス許可を構成します。 ブループリントから作成されたエージェント ID は、対話型の同意プロンプトなしでこれらのアクセス許可を自動的に継承します。

継承可能なアクセス許可と必要なリソース アクセスと直接アクセス許可の付与との関係の概念の背景については、「 [継承可能なアクセス許可と必要なリソース アクセス](https://learn.microsoft.com/ja-jp/entra/agent-id/concept-inheritable-permissions)」を参照してください。

### 前提条件

- 既存のエージェント ID ブループリントが既に作成および構成されている
- 次のいずれかのアクセス許可。
    - ユーザーが所有するエージェント ID ブループリントを管理するためのエージェント ID 開発者ロール
    - エージェント ID ブループリントを管理するためのエージェント ID 管理者ロール

### 継承可能なアクセス許可のしくみ

エージェント ID のトークン発行中に、プラットフォームは、対象となる継承されたスコープをエージェントの要求された委任されたスコープとマージします。 継承されたスコープはアクセス トークンの **scp** 要求に表示され、継承されたロールは **ロール** 要求に表示されます。 継承条件と宣言、許可、および有効なアクセス許可の関係の詳細については、「 [継承可能なアクセス許可と必要なリソース アクセス](https://learn.microsoft.com/ja-jp/entra/agent-id/concept-inheritable-permissions)」を参照してください。

### 継承パターン

この概念の記事では、継承パターンについて大まかに説明します。 次の表は、リソース アプリごとの継承を構成するときに API で使用される特定の `kind` 値を示しています。

| 継承 | サブタイプ | 説明 |
| --- | --- | --- |
| すべて許可 | `allAllowed` | 指定したリソース アプリで使用可能なすべての委任されたスコープまたはアプリケーション ロールを継承します。 エージェントアイデンティティブループリントのプリンシパルに新しく付与されたスコープまたはロールは、自動的に含まれます。 |
| None | `none` | 指定したリソース アプリのスコープまたはロールを継承しません。 スコープ (`noScopes`) またはロール (`noRoles`) の継承を明示的に無効にするには、これを使用します。 |

スコープとロールは、同じリソースで個別に構成できます。 たとえば、ロールを継承しないときにすべてのスコープを継承したり、その逆を行ったりすることができます。

### 継承可能なアクセス許可の制限事項

- エージェント ID ブループリントあたり最大 50 個のリソース アプリ (たとえば、 *inheritablePermissions* コレクション内の最大 50 エントリ)。 この制限を超えた場合は、サポートされている境界内に留まるリソース アプリの数を減らします。

継承可能なアクセス許可の構成を定期的に確認して監視します。 継承されたスコープとロールを再評価して、ユース ケースに適した状態を維持します。 エージェントによって使用されている継承されたスコープとロールを監査し、セキュリティの健全性を維持するために、エージェント ID ブループリントプリンシパルと継承可能なアクセス許可リストの両方から未使用のアクセス許可を削除します。

### 継承可能なアクセス許可を構成する (Microsoft Graphを使用)

継承可能なアクセス許可を構成するには、 `agentIdentityBlueprint` アプリケーション リソースで inheritablePermissions ナビゲーション プロパティを使用します。 各エントリは、1 つのリソース アプリのスコープとロールの継承構成を指定します。 各スコープやロールがなぜ継承可能であるかについての理由と、監査目的でそれを承認した人物を追跡し、その構成に関する決定を文書化します。

要求で `resourceAppId` を指定するときは、有効な GUID 形式を指定してください。 無効な GUID を指定すると、400 個の無効な要求エラーが発生します。

#### Microsoft Graphのすべてのスコープとロールの継承を追加する

**申請**

```http
POST https://graph.microsoft.com/v1.0/applications/microsoft.graph.agentIdentityBlueprint/bc057821-f236-49d6-9f2c-1ebf43e9437a/inheritablePermissions
Content-Type: application/json
OData-Version: 4.0

{
  "resourceAppId": "00000003-0000-0000-c000-000000000000",
  "inheritableScopes": {
    "@odata.type": "#microsoft.graph.allAllowedScopes",
    "kind": "allAllowed"
  },
  "inheritableRoles": {
    "@odata.type": "#microsoft.graph.allAllowedRoles",
    "kind": "allAllowed"
  }
}
```

**応答**

```http
HTTP/1.1 201 Created
Content-Type: application/json

{
  "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#applications('bc057821-f236-49d6-9f2c-1ebf43e9437a')/inheritablePermissions/$entity",
  "resourceAppId": "00000003-0000-0000-c000-000000000000",
  "inheritableScopes": {
    "@odata.type": "microsoft.graph.allAllowedScopes",
    "kind": "allAllowed"
  },
  "inheritableRoles": {
    "@odata.type": "microsoft.graph.allAllowedRoles",
    "kind": "allAllowed"
  }
}
```

#### 複数のリソースのすべてのスコープとロールの継承を追加する

同じブループリント上の複数のリソース アプリに対して継承可能なアクセス許可を構成できます。 各リソースには個別の POST 要求が必要です。 次の例では、Microsoft Graph と SharePoint Online の両方に継承を追加します。

**Request (Microsoft Graph)**

```http
POST https://graph.microsoft.com/v1.0/applications/microsoft.graph.agentIdentityBlueprint/bc057821-f236-49d6-9f2c-1ebf43e9437a/inheritablePermissions
Content-Type: application/json
OData-Version: 4.0

{
  "resourceAppId": "00000003-0000-0000-c000-000000000000",
  "inheritableScopes": {
    "@odata.type": "#microsoft.graph.allAllowedScopes",
    "kind": "allAllowed"
  },
  "inheritableRoles": {
    "@odata.type": "#microsoft.graph.allAllowedRoles",
    "kind": "allAllowed"
  }
}
```

**応答**

```http
HTTP/1.1 201 Created
Content-Type: application/json

{
  "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#applications('bc057821-f236-49d6-9f2c-1ebf43e9437a')/inheritablePermissions/$entity",
  "resourceAppId": "00000003-0000-0000-c000-000000000000",
  "inheritableScopes": {
    "@odata.type": "microsoft.graph.allAllowedScopes",
    "kind": "allAllowed"
  },
  "inheritableRoles": {
    "@odata.type": "microsoft.graph.allAllowedRoles",
    "kind": "allAllowed"
  }
}
```

**Request (SharePoint Online)**

```http
POST https://graph.microsoft.com/v1.0/applications/microsoft.graph.agentIdentityBlueprint/bc057821-f236-49d6-9f2c-1ebf43e9437a/inheritablePermissions
Content-Type: application/json
OData-Version: 4.0

{
  "resourceAppId": "00000003-0000-0ff1-ce00-000000000000",
  "inheritableScopes": {
    "@odata.type": "#microsoft.graph.allAllowedScopes",
    "kind": "allAllowed"
  },
  "inheritableRoles": {
    "@odata.type": "#microsoft.graph.allAllowedRoles",
    "kind": "allAllowed"
  }
}
```

**応答**

```http
HTTP/1.1 201 Created
Content-Type: application/json

{
  "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#applications('bc057821-f236-49d6-9f2c-1ebf43e9437a')/inheritablePermissions/$entity",
  "resourceAppId": "00000003-0000-0ff1-ce00-000000000000",
  "inheritableScopes": {
    "@odata.type": "microsoft.graph.allAllowedScopes",
    "kind": "allAllowed"
  },
  "inheritableRoles": {
    "@odata.type": "microsoft.graph.allAllowedRoles",
    "kind": "allAllowed"
  }
}
```

#### スコープの継承のみを追加する (ロールなし)

委任されたスコープを継承し、アプリケーション ロールを継承しない場合は、 `inheritableRoles` を `noRoles` に設定します。

**申請**

```http
POST https://graph.microsoft.com/v1.0/applications/microsoft.graph.agentIdentityBlueprint/bc057821-f236-49d6-9f2c-1ebf43e9437a/inheritablePermissions
Content-Type: application/json
OData-Version: 4.0

{
  "resourceAppId": "00000003-0000-0000-c000-000000000000",
  "inheritableScopes": {
    "@odata.type": "#microsoft.graph.allAllowedScopes",
    "kind": "allAllowed"
  },
  "inheritableRoles": {
    "@odata.type": "#microsoft.graph.noRoles",
    "kind": "none"
  }
}
```

**応答**

```http
HTTP/1.1 201 Created
Content-Type: application/json

{
  "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#applications('bc057821-f236-49d6-9f2c-1ebf43e9437a')/inheritablePermissions/$entity",
  "resourceAppId": "00000003-0000-0000-c000-000000000000",
  "inheritableScopes": {
    "@odata.type": "microsoft.graph.allAllowedScopes",
    "kind": "allAllowed"
  },
  "inheritableRoles": {
    "@odata.type": "microsoft.graph.noRoles",
    "kind": "none"
  }
}
```

#### ロールの継承のみを追加する (スコープなし)

委任されたスコープではなくアプリケーション ロールを継承するには、 `inheritableScopes` を `noScopes` に設定します。

**申請**

```http
POST https://graph.microsoft.com/v1.0/applications/microsoft.graph.agentIdentityBlueprint/bc057821-f236-49d6-9f2c-1ebf43e9437a/inheritablePermissions
Content-Type: application/json
OData-Version: 4.0

{
  "resourceAppId": "00000003-0000-0000-c000-000000000000",
  "inheritableScopes": {
    "@odata.type": "#microsoft.graph.noScopes",
    "kind": "none"
  },
  "inheritableRoles": {
    "@odata.type": "#microsoft.graph.allAllowedRoles",
    "kind": "allAllowed"
  }
}
```

**応答**

```http
HTTP/1.1 201 Created
Content-Type: application/json

{
  "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#applications('bc057821-f236-49d6-9f2c-1ebf43e9437a')/inheritablePermissions/$entity",
  "resourceAppId": "00000003-0000-0000-c000-000000000000",
  "inheritableScopes": {
    "@odata.type": "microsoft.graph.noScopes",
    "kind": "none"
  },
  "inheritableRoles": {
    "@odata.type": "microsoft.graph.allAllowedRoles",
    "kind": "allAllowed"
  }
}
```

#### ロールの継承を無効にするための更新

resourceAppId のエントリが既に存在する場合は、重複するエントリを作成するのではなく PATCH を使用して更新すると、409 競合エラーが発生します。 次の例では、スコープの継承を有効にしたままロールの継承を無効にします。

**申請**

```http
PATCH https://graph.microsoft.com/v1.0/applications/microsoft.graph.agentIdentityBlueprint/bc057821-f236-49d6-9f2c-1ebf43e9437a/inheritablePermissions/00000003-0000-0000-c000-000000000000
Content-Type: application/json
OData-Version: 4.0

{
  "inheritableRoles": {
    "@odata.type": "#microsoft.graph.noRoles",
    "kind": "none"
  }
}
```

**応答**

```http
HTTP/1.1 200 OK
Content-Type: application/json

{
  "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#applications('bc057821-f236-49d6-9f2c-1ebf43e9437a')/inheritablePermissions/$entity",
  "resourceAppId": "00000003-0000-0000-c000-000000000000",
  "inheritableScopes": {
    "@odata.type": "microsoft.graph.allAllowedScopes",
    "kind": "allAllowed"
  },
  "inheritableRoles": {
    "@odata.type": "microsoft.graph.noRoles",
    "kind": "none"
  }
}
```

#### スコープ継承を無効にする更新

次の例では、ロールの継承を有効にしたまま、スコープの継承を無効にします。

**申請**

```http
PATCH https://graph.microsoft.com/v1.0/applications/microsoft.graph.agentIdentityBlueprint/bc057821-f236-49d6-9f2c-1ebf43e9437a/inheritablePermissions/00000003-0000-0000-c000-000000000000
Content-Type: application/json
OData-Version: 4.0

{
  "inheritableScopes": {
    "@odata.type": "#microsoft.graph.noScopes",
    "kind": "none"
  }
}
```

**応答**

```http
HTTP/1.1 200 OK
Content-Type: application/json

{
  "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#applications('bc057821-f236-49d6-9f2c-1ebf43e9437a')/inheritablePermissions/$entity",
  "resourceAppId": "00000003-0000-0000-c000-000000000000",
  "inheritableScopes": {
    "@odata.type": "microsoft.graph.noScopes",
    "kind": "none"
  },
  "inheritableRoles": {
    "@odata.type": "microsoft.graph.allAllowedRoles",
    "kind": "allAllowed"
  }
}
```

#### 既存の継承可能なアクセス許可を削除する

**申請**

```http
DELETE https://graph.microsoft.com/v1.0/applications/microsoft.graph.agentIdentityBlueprint/bc057821-f236-49d6-9f2c-1ebf43e9437a/inheritablePermissions/00000003-0000-0000-c000-000000000000
OData-Version: 4.0
```

**応答**

```http
HTTP/1.1 204 No Content
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/configure-third-party-agents"} -->
## サード パーティのエージェントをMicrosoft Entra エージェント IDと統合する

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/configure-third-party-agents
- Service: entra-id / agent-id
- Article date: 2026-04-29
- Summary: サイドカーとフェデレーション パターンを使用してセキュリティで保護された認証を行うために、サードパーティの AI エージェントをMicrosoft Entra エージェント IDと統合する方法について説明します。

Microsoft Entra エージェント IDを使用すると、サード パーティのプラットフォームの AI エージェントは、資格情報を直接処理することなく、API を安全に認証してアクセスできます。 この記事では、Amazon Web Service (AWS) Bedrock や n8n などのプラットフォームの 2 つの統合パターン (Microsoft Entra ID認証 SDK (サイドカー) とフェデレーション) について説明します。

### 前提条件

開始する前に、以下を用意してください。

- エージェント ID 機能が有効になっている**Microsoft Entra テナント**。
- **Azure サブスクリプション**。一部のデプロイ オプションに必要です。
- サイドカー パターンの **Docker** と **Docker Compose**。
- 選択したパターンに応じて、**資格情報の設定またはフェデレーションのセットアップ**。
- **PowerShell 7.5 以上**と Microsoft Graph PowerShell モジュールを使用します。
- **グローバル管理者** ロール。初期セットアップにのみ必要です。 [Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) を使用して、このロールをジャスト イン タイムでアクティブ化します。
- **Cloud アプリケーション管理者**または**アプリケーション管理者**のロールを使用して、エージェント管理操作のためにMicrosoft Graphの委任されたアクセス許可を付与します。

環境の準備ができているかどうかを確認するには:

1. Microsoft Entra テナントにアプリケーションとサービス プリンシパルを作成するアクセス許可があることを確認します。
2. Azureにデプロイする場合は、サブスクリプションとリソース グループを確認します。
3. AWS Bedrock や n8n などのエージェント プラットフォームのドキュメントを確認します。

### サードパーティのエージェント統合が必要な理由

組織は、AWS Bedrock、n8n などの複数のプラットフォームの AI エージェントを使用します。 これらのエージェントは、多くの場合、次のことが必要です。

- Microsoft GraphやAzure サービスなどのMicrosoft API を呼び出します。
- 内部 API とリソースにアクセスします。
- コードまたは構成にシークレットを格納せずに安全に認証します。

Microsoft Entra エージェント IDは、サードパーティのエージェントがシークレットや証明書を直接管理することなく、必要に応じてトークンを取得するために使用できる、一元化されたセキュリティで保護された ID サービスを提供します。 Microsoft Entra エージェント IDを使用すると、次のことができます。

- エージェントが資格情報を直接処理する必要を取り除く。
- Azureの外部で実行されているエージェントには、ワークロード ID フェデレーションを使用します。
- クライアント資格情報認証、フェデレーション ID 認証、代行認証など、複数の認証パターンをサポートします。
- Microsoft Entra ID認証 SDK (サイドカー) を使用して、サード パーティのエージェント プラットフォームと統合します。

### サード パーティのエージェントの統合パターン

サード パーティのエージェントをMicrosoft Entra エージェント IDと統合するには、次のパターンから選択します。

#### Microsoft Entra ID認証 SDK (サイドカー) を使用する

**サイドカー パターン**は、Microsoft Entra ID認証 SDK (サイドカー) をコンパニオン コンテナーとしてエージェントと共に実行します。 エージェントはサイドカーを呼び出して、API 呼び出しのトークンを要求します。 エージェントは資格情報を直接処理しません。代わりに、トークンの取得をサイドカーに委任します。

**次の場合に最適です。**

- Docker または Kubernetes 上のコンテナー化されたエージェント。
- 独自のオーケストレーションで実行中の AWS Bedrock エージェント。
- Docker Compose を使用したローカル開発。
- コンテナー インフラストラクチャを既に使用している組織。

**サポートされているプラットフォーム:**

- クロードやその他の基盤モデルを含む AWS Bedrock。
- LangChain を使用した Ollama などのローカルの大規模言語モデル (LLM)。
- コンテナー化されたエージェント。

**長所:**

- 資格情報不要のエージェントコード。
- コンテナー化されたエージェントで動作します。
- Docker Compose を使用した簡単なローカル開発。
- Azure Container Apps、Kubernetes、またはオンプレミスにデプロイできます。

**考察：**

- 2 つ目のコンテナーを管理する必要があります。

次の図は、サイドカーのアーキテクチャを示しています。 エージェント コンテナーとサイドカー コンテナーは、同じオーケストレーション環境で一緒に実行されます。 エージェントはサイドカーからトークンを要求します。このトークンは、アクセス トークンを取得するためにMicrosoft Entra エージェント IDと通信します。

[Image: エージェントとサイドカー コンテナーが同じオーケストレーション環境で実行されているサイドカー パターン アーキテクチャを示す図。サイドカーは Microsoft Entra エージェント ID からトークンを要求します。]

#### ワークロード ID フェデレーションの使用 (直接 ID 交換)

**federation パターン**は、ワークロード ID フェデレーションを使用して、AWS セキュリティ トークン サービス (STS) などの外部 ID プロバイダーからの資格情報をMicrosoft Entraトークンと直接交換します。 このパターンにはサイドカーは必要ありません。

**次の場合に最適です。**

- STS と OIDC を使用する AWS エージェント。
- フェデレーション インフラストラクチャが既に存在する組織。
- コンテナーを実行できないエージェント。

**サポートされているプラットフォーム:**

- GCP ワークロード アイデンティティ → Microsoft Entra エージェント ID。
- AWS STS → Microsoft Entra エージェント ID。

**長所:**

- サイドカーは必要ありません。
- AWS やその他のプラットフォームの既存のインフラストラクチャを使用します。
- ID 層での直接トークン交換。

**要件:**

- Microsoft Entraで構成済みのフェデレーション ID 資格情報。
- OIDC または STS をサポートするエージェント プラットフォーム。

次の図は、フェデレーション フローを示しています。 サードパーティ プラットフォーム上のエージェントは、ネイティブ ワークロード ID プロバイダーを介して認証を行い、結果として得られる OIDC トークンをMicrosoft Entra トークンと交換した後、API を呼び出します。

[Image: サードパーティプラットフォームエージェントがMicrosoft Entra エージェント IDを介してOIDCトークンを交換し、MicrosoftのAPIまたはお客様のAPIにアクセスするフェデレーションパターンのフローを示す図。]

### トークン フローを理解する

どちらのパターンも、同じコア トークン フローに従います。

1. **エージェントがトークンを要求します。** エージェントまたはエージェントの代わりにサイドカーが、認証資格情報を使用して Microsoft Entra エージェント ID にアクセスします。
2. **Microsoft Entraは ID を検証します。** Microsoft Entraは、クライアント資格情報、フェデレーション資格情報、またはサポートされている別の方法を使用して、エージェントの ID を検証します。
3. **Microsoft Entraはトークンを返します。** エージェントは、Microsoft Entraアクセス トークンを受け取ります。
4. **エージェントは API を呼び出します。** エージェントはトークンを使用して、Microsoftまたはカスタム API に対する認証を行います。
5. **API はトークンを検証します。** API はトークンの署名と要求を確認し、アクセス権を付与します。

### 一般的な統合シナリオ

#### AWS Bedrock エージェントがMicrosoft Graphを呼び出す

Claude などの AWS Bedrock エージェントは、Microsoft 365データに対してクエリを実行するか、Microsoft Graphを使用してリソースを管理する必要があります。 サイドカー パターンは、このシナリオに最適です。 AWS Bedrock エージェントをサイドカーパターンで統合するには:

1. エージェントとサイドカーを AWS または独自のインフラストラクチャにデプロイします。
2. Microsoft Graphするアクセス許可を使用して、Microsoft Entraでエージェント ID を構成します。
3. エージェントは、トークンを取得するためにサイドカー コンポーネントを呼び出します。
4. サイドカーは、Microsoft Entra エージェント IDからトークンを取得します。
5. エージェントはトークンを使用してMicrosoft Graphを呼び出します。

詳細な手順については、「Microsoft Entra エージェント IDを参照してください。

#### n8n エージェントがエンタープライズ向けに Microsoft Graph および MCP サーバーを呼び出し

n8n エージェントは、Microsoft Graphまたは Microsoft Graph MCP Server for Enterprise を介してMicrosoft 365 データにアクセスする必要があります。 このシナリオでは、n8n-nodes-entraagentid コミュニティ ノードを使用して、n8n ワークフロー内でトークンの取得を直接管理します。 n8n エージェントを統合するには:

1. Azure Developer CLI (`azd`) を使用して n8n をAzure Container Appsにデプロイします。
2. Microsoft Graphするアクセス許可を使用して、Microsoft Entraでエージェント ID を構成します。
3. n8n ワークフローでは、コミュニティ ノードを使用して、Microsoft Entra エージェント IDからトークンを取得します。
4. エージェントはトークンを使用して、Microsoft Graphまたは MCP Server for Enterprise を呼び出します。

詳しい手順については、「Microsoft Entra エージェント IDを参照してください。

#### オラマでの現地開発

Ollama などのローカル LLM を使用して開発しており、デプロイする前に認証をテストしたいと考えています。 Docker Compose でサイドカー パターンを使用します。 ローカルでテストするには:

1. Docker Compose でエージェントとサイドカーを実行します。
2. エージェントは `localhost:7000/token` を呼び出して、サイドカーからトークンを要求します。
3. サイドカーは、Microsoft Entra エージェント IDからトークンを取得します。
4. デプロイする前に、エージェントの動作をローカルでテストします。

詳しい手順については、「 [ローカル開発用にサイドカーを実行する」を](https://learn.microsoft.com/ja-jp/entra/agent-id/sidecar-local-development)参照してください。

### ロードマップの概要

次の表を使用して、選択したパターンの手順を特定します。

| Step | パターン | タスク |
| --- | --- | --- |
| 1 | 両方 | Microsoft Entraでエージェント ID とアクセス許可を設定します。 |
| 2 | 両方 | 統合パターン (サイドカーまたはフェデレーション) を選択します。 |
| 3 | Sidecar | エージェントとサイドカー コンテナーをデプロイし、ローカルでテストします。 |
| 4 | Sidecar | Azure Container Apps、Kubernetes、または別のプラットフォームで運用環境にデプロイします。 |
| 5 | フェデレーション | Microsoft Entraでフェデレーション ID 資格情報を構成します。 |
| 6 | フェデレーション | ターゲット プラットフォームにエージェントをデプロイします。 |

### セキュリティのベスト プラクティス

サード パーティのエージェントを統合する場合は、次のセキュリティ原則に従います。

- **エージェント コードに資格情報を埋め込むことはありません。** Microsoft Entra エージェント IDを使用して、トークンを動的に取得します。
- **最小特権を使います。** エージェント ID には、ロールまたはスコープを通じて必要なアクセス許可のみを付与します。
- **トークンの対象ユーザーと発行者を検証します。** トークンがMicrosoft Entra テナントから取得されていることを常に確認します。
- **資格情報を定期的にローテーションします。** クライアント シークレットを使用する場合は、スケジュールに従ってローテーションします。 代わりにフェデレーション資格情報を検討してください。
- **トークンの使用状況を監視します。** Microsoft Entraログを使用して、どのエージェントがどの API にアクセスするかを追跡します。
- **Microsoft Entra ID認証 SDK (サイドカー) を更新したままにします。** セキュリティと互換性の更新プログラムは定期的にリリースされます。

### 一般的な問題のトラブルシューティング

統合中に問題が発生した場合は、次のガイダンスを使用して原因と解決策を特定します。

| 問題点 | 原因 | 解決策 |
| --- | --- | --- |
| エージェントがサイドカーに到達できない | ネットワーク構成の問題、またはサイドカーが起動していない可能性 | サイドカーが実行されていることを確認し、DNS とネットワークを確認し、ポート バインドを確認します。 既定のポートは 7000 です。 |
| サイドカーがトークンの取得に失敗する | Microsoft Entra認証に失敗しました | エージェント ID の資格情報を確認し、Microsoft Entraアクセス許可を確認し、テナント ID とクライアント ID を確認します。 |
| トークン要求から 401 が返される | Microsoft Entra の資格情報が無効であるか、フェデレーションの資格情報が構成されていません。 | 資格情報が正しいことを確認し、フェデレーション パターンを使用している場合は、フェデレーション ID 資格情報が設定されていることを確認します。 |
| API がトークンを拒否する | トークンに必要なスコープまたはアクセス許可がない | エージェント ID に必要な API アクセス許可を追加し、適切なスコープのトークンを要求します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/control-user-access-agents"} -->
## エージェントへのユーザー アクセスを制御する

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/control-user-access-agents
- Service: entra-id / agent-id
- Article date: 2025-11-04
- Summary: セキュリティで保護されたアクセス管理のためにアプリ ロールと assignmentRequired プロパティを使用して、アプリケーションへのユーザーとエージェントのアクセスを制御する方法について説明します。

この記事では、アプリ ロールと `assignmentRequired` プロパティを使用して、アプリケーションへのユーザーとエージェントのアクセスを制御する方法について説明します。 アプリ ロールの定義、明示的なロールの割り当ての適用、Microsoft GraphまたはMicrosoft Entra 管理センターを使用したロールの割り当て、セキュリティで保護されたアクセス管理のベスト プラクティスについて説明します。

### 前提条件

- 既存のエージェント ID ブループリントが既に作成および構成されている
- エージェント ID と割り当てを管理するためのエージェント ID 管理者ロール
- アプリケーションの役割と assignmentRequired プロパティを管理するためのアプリケーション管理者またはクラウドアプリケーション管理者の役割
- *AppRoleAssignment.ReadWrite.All* プログラムによるアプローチを使用する場合の Microsoft Graph API操作に対するアクセス許可。

### アプリ ロールとは

アプリ ロールは、アプリケーションの論理アクセス許可またはアクセス レベルを定義します。 これらはエージェント ID ブループリントで宣言され、ユーザー、グループ、サービス プリンシパル、またはエージェント ID に割り当てることができます。 次のセクションでは、アプリ ロール定義の例を示します。

```json
{
  "id": "b1a2c3d4-e5f6-7890-abcd-ef1234567890",
  "allowedMemberTypes": ["User", "Application"],
  "description": "Grants ability to invoke agent actions",
  "displayName": "AgentInvoker",
  "isEnabled": true,
  "value": "AgentInvoker"
}
```

- `allowedMemberTypes` 割り当て可能なユーザー (ユーザー、アプリ、またはその両方) を制御します。
- `value` は、割り当てられたときにトークンのロール要求に表示される文字列です。

エージェント ID ブループリントでアプリ ロールが公開されていない場合は、既定のアプリ ロール `<guid of all zeros>` を使用して、エージェント ID にプリンシパルを割り当てることができます。

エージェント アプリケーションのアプリ ロールを設計する場合は、職務と責任を分離する詳細なロールを定義します。 たとえば、エージェント アクションを呼び出すことができるユーザーには "AgentInvoker" などの特定のロールを作成し、エージェント構成を管理できるユーザーには "AgentAdmin" を作成します。 この方法により、セキュリティ制御が向上し、アクセス パターンの監査が容易になります。

### assignmentRequired とは

アプリケーションまたはエージェント ID の assignmentRequired プロパティによって、ロールの割り当てがアクセスに必須かどうかが決まります。

- `assignmentRequired = true` → 明示的に割り当てられたアプリ役割を持つプリンシパルのみが、エージェントのアイデンティティでサインインまたは呼び出しを行うことができます。
- `assignmentRequired = false` →認証されたプリンシパルは、エージェント ID にアクセスできます (他の条件に従います)。

### assignmentRequired を使用する理由

assignmentRequired プロパティは次のような理由で使用することができます。

- 明示的な割り当てを要求することで、最小限の特権を適用します。
- エージェントの使用を意図していないユーザーまたはエージェントによる誤ったアクセスを防ぎます。

機密性の高いエージェント ID ブループリントの `assignmentRequired` を有効にして、明示的なロールの割り当てを強制し、承認されたプリンシパルのみが重要なエージェント機能にアクセスできるようにします。 ユーザーが明示的な割り当てなしでアプリケーションにアクセスできる場合は、 `assignmentRequired` が `false` に設定されていることを示します。 この設定を true に変更し、セキュリティ制御を維持するためにロールを明示的に割り当てます。

### assignmentRequired を構成する

次の例では、Update アプリケーション API を使用して、 `assignmentRequired` プロパティを設定します。

```http
PATCH https://graph.microsoft.com/v1.0/applications/<agent-app-id>
Content-Type: application/json
Authorization: Bearer <token>

{
  "appRoleAssignmentRequired": true
}
```

`assignmentRequired` プロパティを使用すると、アプリ ロールが明示的に割り当てられたプリンシパルのみがエージェント ID ブループリントにアクセスでき、最小限の特権が適用され、偶発的なアクセスが防止されます。

### アプリ ロールを割り当ててアクセスを制御する

アプリ ロールは、アプリ ロールの割り当てを通じて付与されます。 プリンシパル (ユーザー、グループ、またはエージェント ID) をターゲット エージェント ID の特定のアプリ ロールにリンクします。 構成後にエージェント ID がアプリケーションにアクセスできない場合は、次の例に示す API を使用して、エージェント ID の `appRoleAssignment` が作成されていることを確認します。

#### アプリ ロールをユーザーに割り当てる

次の例では、ユーザーに `appRoleAssignment` を付与する API を使用して、アプリ ロールをユーザーに割り当てます。

```http
POST https://graph.microsoft.com/v1.0/users/<user-id>/appRoleAssignments
Authorization: Bearer <token with AppRoleAssignment.ReadWrite.All>
Content-Type: application/json

{
  "principalId": "<user-id>",
  "resourceId": "<agent-identity-blueprint-principal-id>",
  "appRoleId": "<app-role-id>"
}
```

#### エージェント ID にアプリ ロールを割り当てる

次の例では、サービス プリンシパル API への `appRoleAssignment` の付与を使用して、エージェント ID にアプリ ロールを割り当てます。

```http
POST https://graph.microsoft.com/v1.0/servicePrincipals/<agent-identity-id>/appRoleAssignments
Authorization: Bearer <token with AppRoleAssignment.ReadWrite.All>
Content-Type: application/json

{
  "principalId": "<agent-identity-id>",
  "resourceId": "<agent-identity-blueprint-principal-id>",
  "appRoleId": "<app-role-id>"
}
```

### ロールの割り当てを確認する

ロールの割り当てを確認するには、次のアクションを実行してください。

- ユーザーまたはエージェント ID の `appRoleAssignments` を一覧表示します。
- サインイン後にトークン内のロールクレームを確認します。

ロール要求がトークンから欠落している場合は、適切な appRoleId を使用して割り当てが存在することを確認し、新しいトークンを要求します。 Microsoft GraphまたはMicrosoft Entra 管理センターを使用してこれらの割り当てを定期的に監査し、それらが適切であることを確認し、不要なアクセスを削除します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/create-blueprint"} -->
## エージェント ID ブループリントを作成する - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint
- Service: entra-id / agent-id
- Article date: 2026-04-27
- Summary: Microsoft Graph API と PowerShell を使用して、複数のエージェント ID のテンプレートとして機能するエージェント ID ブループリントを作成する方法について説明します。

[エージェント ID ブループリントは、エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-blueprint) を作成し、それらのエージェント ID を使用してトークンを要求するために使用されます。 エージェント ID ブループリントを作成するプロセス中に、そのブループリントの [所有者とスポンサー](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-owners-sponsors-managers) を設定して、アカウンタビリティと管理関係を確立します。 また、識別子 URI を構成し、エージェントが他のエージェントとユーザーからの受信要求を受信するように設計されている場合は、このブループリントから作成されたエージェントのスコープを定義します。

エージェント ID ブループリントは、次の 2 つの方法で作成できます。

- **Microsoft Entra 管理センター** — このウィザードを使用して、ブループリントとそのプリンシパルを作成する簡単なセットアップを行います。
- **Microsoft Graph APIまたは PowerShell** — 単一のワークフローで資格情報、識別子 URI、スコープ、ブループリント プリンシパルを含むブループリントをプログラムで作成し、完全に構成します。

### 前提条件

エージェント ID ブループリントを作成するには、次のものが必要です。

- [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)ロールは、Microsoft Graphアプリケーションのアクセス許可を付与するために必要な最小限の特権ロールです。
- [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)または[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)は、Microsoft Graph に委任されたアクセス許可を付与する必要があります。
- [エージェント ID 開発者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-developer)ロールと[エージェント ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-administrator)ロールの両方で、エージェント ID ブループリントとエージェント ID ブループリント プリンシパルを作成できます。
    - [エージェント ID 開発者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-developer) は、エージェント ID ブループリントでフェデレーション ID 資格情報を構成できます。
    - [エージェント ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-administrator) は、エージェント ID ブループリントでフェデレーション ID 資格情報を構成でき、シークレットまたは証明書の資格情報を追加する必要があります。
- PowerShell を使用する場合は、バージョン 7 が必要です。

注

エージェント ID ブループリントまたはエージェント ID ブループリント プリンシパルの所有者は、Microsoft Entra エージェント IDロールなしで、そのブループリントのエージェント ID を作成できます。 エージェント ID ブループリント作成者は、ブループリントと関連するエージェント ID ブループリント プリンシパルの両方の所有者として自動的に設定されます。

### 環境を準備する

プロセスを効率化するには、少し時間を取って、適切なアクセス許可に合わせて環境を設定します。

#### クライアントがエージェント ID ブループリントを作成することを承認する

この記事では、Microsoft Graph PowerShell または別のクライアントを使用して、エージェント ID ブループリントを作成します。 エージェント ID ブループリントを作成して構成し、エージェント ID ブループリント プリンシパルを作成するには、このクライアントを承認する必要があります。 クライアントには、次のMicrosoft Graphアクセス許可が必要です。

- [AgentIdentityBlueprint.Create](https://learn.microsoft.com/ja-jp/graph/api/agentidentityblueprint-post?view=graph-rest-v1.0&preserve-view=true) に対する委任されたアクセス許可
- [AgentIdentityBlueprint.AddRemoveCreds.All](https://learn.microsoft.com/ja-jp/graph/api/agentidentityblueprint-addpassword?view=graph-rest-v1.0&preserve-view=true) 委任された権限
- [AgentIdentityBlueprint.UpdateAuthProperties.All](https://learn.microsoft.com/ja-jp/graph/api/agentidentityblueprint-update?view=graph-rest-v1.0&preserve-view=true&tabs=http) 委任された権限
- [AgentIdentityBlueprintPrincipal.Create](https://learn.microsoft.com/ja-jp/graph/api/agentidentityblueprintprincipal-post?view=graph-rest-v1.0&preserve-view=true) の委任された許可

このガイドの手順では、委任されたすべてのアクセス許可を使用しますが、必要なシナリオではアプリケーションのアクセス許可を使用できます。

Microsoft Graph PowerShell に必要なすべてのスコープに接続するには、次のコマンドを実行します。

```powershell
Connect-MgGraph -Scopes "AgentIdentityBlueprint.Create", "AgentIdentityBlueprint.AddRemoveCreds.All", "AgentIdentityBlueprint.UpdateAuthProperties.All", "AgentIdentityBlueprintPrincipal.Create", "User.Read" -TenantId <your-tenant-id>
```

### エージェント ID ブループリントを作成する

エージェント ID ブループリントには、エージェントの責任を負うユーザーまたは [サポートされているグループ](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-owners-sponsors-managers#sponsors) であるスポンサーが必要です。 所有者が推奨されます。これは、エージェント ID ブループリントに変更を加えることができるユーザーまたはサービス プリンシパルです。 詳細については、「Microsoft Entra エージェント ID の管理関係」を参照してください。

#### Microsoft Entra 管理センターを使用する

エージェント ID ブループリントは、Microsoft Entra 管理センターで直接作成できます。 管理センター ウィザードでは、エージェント ID ブループリントとそのブループリント プリンシパルの両方が自動的に作成されます。

注

管理センター ウィザードによってブループリント名が設定され、所有者とスポンサーが割り当てられます。 資格情報、識別子 URI、スコープ、またはアクセス許可を構成するには、Microsoft Graph APIまたは PowerShell を使用するか、管理センターのブループリントの詳細ページを使用して作成後に構成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**エージェント**&gt;**エージェント設計図**に移動してください。
3. **新しいエージェント ブループリント (プレビュー)**を選択します。
4. [ **基本** ] タブの [ **エージェント ブループリント名** ] フィールドに名前を入力し、[ **次へ**] を選択します。

    [Image: [基本] タブと [エージェント ブループリント名] フィールドが表示されているエージェント ブループリントの作成ウィザードのスクリーンショット。]
5. [ **所有者とスポンサー** ] タブで、必要に応じてブループリントの所有者とスポンサーを変更または追加します。

    - [ **所有者** ] フィールドの横にある鉛筆アイコンを選択して、ブループリントを管理できるユーザーを変更または追加します。
    - **ブループリントをスポンサー**できるユーザーを変更または追加するには、[スポンサー] フィールドの横にある鉛筆アイコンを選択します。

    注

    スポンサーには、ユーザー、動的メンバーシップ グループ、またはMicrosoft 365 グループを指定できます。 セキュリティ グループとロール割り当て可能なグループは、スポンサーとしてサポートされていません。
6. **次へ**を選択します。
7. 設定を確認し、[ **作成**] を選択します。
8. [ **完了]** を選択してウィザードを終了するか **、エージェント ブループリントに移動してブループリント** の詳細ページを表示するか、その他の設定を構成します。

エージェント ID ブループリントの管理の詳細については、「 [エージェント ID ブループリントの管理」を](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agent-blueprint)参照してください。

#### プログラムで作成する

コードを使用してエージェント ID ブループリントを作成するには、Microsoft Graph APIまたは PowerShell を使用します。

## [マイクロソフト グラフ API](#tab/microsoft-graph-api)
この手順では、エージェント ID ブループリントを作成し、所有者とスポンサーを割り当て、次の詳細が必要です。

- `AgentIdentityBlueprint.Create` アクセス許可。
- OData-Version ヘッダーは 4.0 に設定する必要があります。
- 要求本文の例の所有者フィールドとスポンサー フィールドのユーザー ID。 スポンサーは必須ですが、所有者は省略可能です。

```http
POST https://graph.microsoft.com/v1.0/applications/
OData-Version: 4.0
Content-Type: application/json
Authorization: Bearer <token>

{
  "@odata.type": "Microsoft.Graph.AgentIdentityBlueprint",
  "displayName": "My Agent Identity Blueprint",
  "sponsors@odata.bind": [
    "https://graph.microsoft.com/v1.0/users/<id>"
  ],
  "owners@odata.bind": [
    "https://graph.microsoft.com/v1.0/users/<id>"
  ]
}

```

エージェント ID ブループリントを作成した後、次の手順で `appId` の値を記録します。

## [Microsoft Graph PowerShell](#tab/powershell)
この手順では、現在のユーザーを所有者およびスポンサーとして使用してエージェント ID ブループリント アプリケーションを作成し、次の個別のタスクを含めます。

- `AgentIdentityBlueprint.Create`スコープと`User.Read`スコープを使用してテナントに接続します。
- エージェント ID ブループリントのスポンサーおよび所有者として現在のユーザーを追加します。
- エージェント ID ブループリント アプリケーションを作成します。

```powershell
Connect-MgGraph -Scopes "AgentIdentityBlueprint.Create","User.Read" -TenantId <your-tenant-id>

$currentUser = Get-MgContext | Select-Object -ExpandProperty Account
$user = Get-MgUser -UserId $currentUser

Write-Host "Current user: $($user.DisplayName) ($($user.Id))"
Write-Host "Sponsor user: $($user.DisplayName) ($($user.Id))"

$body = @{
    "@odata.type" = "Microsoft.Graph.AgentIdentityBlueprint"
    "displayName" = "My Agent Identity Blueprint"
    "sponsors@odata.bind" = @("https://graph.microsoft.com/v1.0/users/$($user.Id)")
    "owners@odata.bind" = @("https://graph.microsoft.com/v1.0/users/$($user.Id)")
} | ConvertTo-Json -Depth 5

$response = Invoke-MgGraphRequest `
    -Method POST `
    -Uri "https://graph.microsoft.com/v1.0/applications/microsoft.graph.agentIdentityBlueprint" `
    -Headers @{ "OData-Version" = "4.0" } `
    -Body $body `
    -ContentType "application/json"

$response

```

エージェント ID ブループリントを作成した後、出力から `appId` の値を記録します。

---

### エージェント ID ブループリントの資格情報を構成する

エージェントアイデンティティブループリントを使用してアクセス トークンを要求するには、[クライアント資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)を追加する必要があります。 運用環境へのデプロイメントでは、フェデレーテッド ID 資格情報 (FIC) として[マネージド アイデンティティ](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) を使用することをお勧めします。 マネージド ID を使用すると、資格情報を管理することなく、Microsoft Entraトークンを取得できます。 詳細については、「[Azure リソースの管理 ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)」を参照してください。

`keyCredentials`や`passwordCredentials`など、他の種類のアプリ資格情報はサポートされていますが、運用環境ではお勧めしません。 これらは、ローカルでの開発とテスト、またはマネージド ID が機能しない場合に便利ですが、これらのオプションはセキュリティのベスト プラクティスと一致しません。 詳細については、「 [アプリケーションプロパティのセキュリティのベスト プラクティス」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-best-practices-for-app-registration#credentials-including-certificates-and-secrets)参照してください。

マネージド ID を使用するには、仮想マシンやAzure App ServiceなどのAzure サービスでコードを実行する必要があることに注意してください。 ローカルでの開発とテストには、 クライアント シークレットまたは証明書を使用します。

## [マイクロソフト グラフ API](#tab/microsoft-graph-api)
この要求を送信するには:

- `AgentIdentityBlueprint.AddRemoveCreds.All` アクセス許可が必要です。
- `<agent-blueprint-id>` プレースホルダーをエージェント ID ブループリントの`appId`に置き換えます。
- `<managed-identity-principal-id>` プレースホルダーをマネージド ID の ID に置き換えます。

次の要求を使用して、資格情報としてマネージド ID を追加します。

```http
POST https://graph.microsoft.com/v1.0/applications/<agent-blueprint-id>/federatedIdentityCredentials
OData-Version: 4.0
Content-Type: application/json
Authorization: Bearer <token>

{
    "name": "my-managed-identity",
    "issuer": "https://login.microsoftonline.com/<your-tenant-id>/v2.0",
    "subject": "<managed-identity-principal-id>",
    "audiences": [
        "api://AzureADTokenExchange"
    ]
}
```

## [Microsoft Graph PowerShell](#tab/powershell)
この手順には、次の個別のタスクが含まれています。

- `AgentIdentityBlueprint.AddRemoveCreds.All` スコープを使用してテナントに接続します。
- 以前に作成したエージェント ID ブループリント プリンシパルを使用して、マネージド ID をエージェント ID ブループリントの資格情報として追加します。

```powershell
Install-Module Microsoft.Graph.Applications -Scope CurrentUser -Force

Connect-MgGraph -Scopes "AgentIdentityBlueprint.AddRemoveCreds.All" -TenantId <your-tenant-id>

$applicationId = "<agent-blueprint-id>"

$federatedCredential = @{
  Name             = "my-managed-identity"
  Issuer           = "https://login.microsoftonline.com/<your-tenant-id>/v2.0"
  Subject          = "<managed-identity-principal-id>"
  Audiences         = @("api://AzureADTokenExchange")
}

New-MgApplicationFederatedIdentityCredential `
  -ApplicationId $applicationId `
  -BodyParameter $federatedCredential
```

---

#### その他のアプリ資格情報

マネージド ID が機能しないシナリオや、テスト用にブループリントをローカルで作成する場合は、次の手順を使用して資格情報を追加します。

## [マイクロソフト グラフ API](#tab/microsoft-graph-api)
この要求を送信するには、まず、委任されたアクセス許可を持つアクセス トークンを取得する必要があります `AgentIdentityBlueprint.AddRemoveCreds.All`

```http
POST https://graph.microsoft.com/v1.0/applications/<agent-blueprint-id>/addPassword
Content-Type: application/json
Authorization: Bearer <token>

{
  "passwordCredential": {
    "displayName": "My Secret",
    "endDateTime": "2026-08-05T23:59:59Z"
  }
}
```

## [Microsoft Graph PowerShell](#tab/powershell)
```powershell
Connect-MgGraph -Scopes "AgentIdentityBlueprint.AddRemoveCreds.All" -TenantId <your-tenant-id>

$applicationId = "<agent-blueprint-application-id>"

# Define the secret properties
$displayName = "My Secret"
$endDate = (Get-Date).AddYears(1).ToString("o")  # 1 year from now, in ISO 8601 format

# Construct the password credential
$passwordCredential = @{
    displayName = $displayName
    endDateTime = $endDate
}

# Add the password (client secret)
$response = Add-MgApplicationPassword -ApplicationId $applicationId -PasswordCredential $passwordCredential

# Output the generated secret (only returned once!)
Write-Host "Secret Text: $($response.secretText)"
```

---

注

テナントには、クライアント シークレットの最大有効期間を制限する資格情報ライフサイクル ポリシーがある場合があります。 資格情報の有効期間に関するエラーが発生した場合は、組織のポリシーに合わせて `endDateTime` 値を減らします。

必ず、生成された `passwordCredential` 値を安全に格納してください。 最初の作成後は表示できません。 資格情報としてクライアント証明書を使用することもできます。 [証明書資格情報の追加を](https://learn.microsoft.com/ja-jp/graph/api/application-addkey?tabs=http#example-3-add-a-certificate-credential-to-an-application)参照してください。

ブループリントで作成されたエージェントが対話型エージェントをサポートする場合は、エージェントがユーザーの代わりに動作する場合、エージェント フロントエンドがアクセス トークンをエージェント バックエンドに渡すことができるように、ブループリントでスコープを公開する必要があります。 その後、このトークンをエージェント バックエンドが使用して、ユーザーに代わって動作するアクセス トークンを取得できます。

### 識別子 URI とスコープを構成する

任意の Web API などのユーザーや他のエージェントから受信要求を受信するには、エージェント ID ブループリントの識別子 URI と OAuth スコープを定義する必要があります。

## [マイクロソフト グラフ API](#tab/microsoft-graph-api)
この要求を送信するには:

- アクセス許可 `AgentIdentityBlueprint.UpdateAuthProperties.All`が必要です。
- `<agent-blueprint-id>` プレースホルダーをエージェント ID ブループリントの`appId`に置き換えます。
- グローバルユニーク識別子 (GUID) が必要です。 PowerShell で、 `[guid]::NewGuid()` を実行するか、オンライン GUID ジェネレーターを使用します。 生成された GUID をコピーし、それを使用して `<generate-a-guid>` プレースホルダーを置き換えます。

```http
PATCH https://graph.microsoft.com/v1.0/applications/<agent-blueprint-id>
OData-Version: 4.0
Content-Type: application/json
Authorization: Bearer <token>

{
    "identifierUris": ["api://<agent-blueprint-id>"],
    "api": {
      "oauth2PermissionScopes": [
        {
          "adminConsentDescription": "Allow the application to access the agent on behalf of the signed-in user.",
          "adminConsentDisplayName": "Access agent",
          "id": "<generate-a-guid>",
          "isEnabled": true,
          "type": "User",
          "value": "access_agent"
        }
      ]
  }
}
```

呼び出しが成功すると、204 応答が生成されます。

## [Microsoft Graph PowerShell](#tab/powershell)
この手順には、次の個別のタスクが含まれています。

- まだインストールしていない場合は、必要なモジュールをインストールします。
- `AgentIdentityBlueprint.UpdateAuthProperties.All` スコープを使用してテナントに接続します。
- 新しいエージェント ID ブループリントの URI とスコープを構成します。

```powershell
Connect-MgGraph -Scopes "AgentIdentityBlueprint.UpdateAuthProperties.All" -TenantId <your-tenant-id>

$AppId = "<agent-blueprint-id>"
$IdentifierUri = "api://<agent-blueprint-id>"
$ScopeId = [guid]::NewGuid()

# Construct the OAuth2 permission scope
$scope = @{
    adminConsentDescription = "Allow the application to access the agent on behalf of the signed-in user."
    adminConsentDisplayName = "Access agent"
    id = $ScopeId
    isEnabled = $true
    type = "User"
    value = "access_agent"
}

Update-MgApplication -ApplicationId $AppId `
    -IdentifierUris @($IdentifierUri) `
    -Api @{ oauth2PermissionScopes = @($scope) }
```

---

### エージェント ブループリントの主要部分を作成する

この手順では、エージェント ID ブループリントのプリンシパルを作成します。 詳細については、「 [エージェント ID、サービス プリンシパル、およびアプリケーション」を](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-service-principals)参照してください。

## [マイクロソフト グラフ API](#tab/microsoft-graph-api)
`<agent-blueprint-app-id>` プレースホルダーを、前の手順の結果からコピーした`appId`に置き換えます。

```http
POST https://graph.microsoft.com/v1.0/serviceprincipals/microsoft.graph.agentIdentityBlueprintPrincipal
OData-Version: 4.0
Content-Type: application/json
Authorization: Bearer <token>

{
  "appId": "<agent-blueprint-app-id>"
}
```

## [Microsoft Graph PowerShell](#tab/powershell)
エージェント ID ブループリントを作成した後、新しく作成されたエージェント ID ブループリント `appId`を使用して、エージェント ID ブループリント プリンシパルを作成します。

Connect-MgGraph -Scopes "AgentIdentityBlueprintPrincipal.Create" -TenantId `<your-tenant-id>`

```powershell
Connect-MgGraph -Scopes "AgentIdentityBlueprintPrincipal.Create" -TenantId
$body = @{
    appId   = "<agent-blueprint-client-id>"
}
Invoke-MgGraphRequest -Method POST `
        -Uri "https://graph.microsoft.com/v1.0/serviceprincipals/microsoft.graph.agentIdentityBlueprintPrincipal" `
        -Headers @{ "OData-Version" = "4.0" } `
        -Body ($body | ConvertTo-Json)
```

---

これで、エージェントブループリントの準備が整い、[Microsoft Entra 管理センター](https://entra.microsoft.com)に表示されるようになりました。 次の手順では、このブループリントを使用して [エージェント ID を作成します](https://learn.microsoft.com/ja-jp/entra/agent-id/create-delete-agent-identities)。

### エージェント 365 レジストリにエージェントを登録する

エージェント ID ブループリントを作成したら、[Agent 365 レジストリ](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/agent-registry)に登録して、管理者がMicrosoft 365 管理センターからエージェントを検出、管理、管理できるようにします。 このセクションでは、エージェント 365 レジストリに現在表示されていない可能性がある既存のエージェント ID ブループリントを追加する手順についても説明します。

#### Microsoft 365 エージェント SDKを使用する (推奨)

[Microsoft 365 エージェント SDK](https://learn.microsoft.com/ja-jp/microsoft-365/agents-sdk/) が一般公開され、エージェントをビルドしてプロビジョニングするための推奨される方法です。 SDK SDK はエージェント 365 レジストリでのエージェント ID の作成と登録を自動的に処理するため、エージェント ID は追加のコードなしで自動的に表示されます。 新しいエージェント プロジェクトを開始する場合、または既存のコードを柔軟に移行できる場合は、SDK を使用します。 これは最も単純で最も永続的なパスであり、複数の API 呼び出しを自分で調整する必要がなくなります。

#### エージェント 365 CLI を使用する

[エージェント 365 CLI](https://learn.microsoft.com/ja-jp/microsoft-agent-365/developer/reference/cli/setup) は、エージェントの登録など、セットアップを自動的に処理するもう 1 つのオプションです。 [推奨される実行順序](https://learn.microsoft.com/ja-jp/microsoft-agent-365/developer/reference/cli/setup#recommended-execution-order)を使用して、セットアップ手順に従います。 次のコマンドを使用します。

```http
a365 setup all
```

登録に失敗した場合は、プロセス全体を実行しなくても、登録手順だけを再実行できます。 次のコマンドを使用します。

```http
a365 setup all --agent-registration-only
```

#### エージェント レジストリ API を直接呼び出す

Microsoft Graph APIを使用してエージェント ID ブループリントをプログラムで作成する必要がある場合 (たとえば、すぐに変更できない既存の ID 発行ワークフローがあるため)、Agent Registry API エージェント ID ブループリントを作成して、対応するエージェント カードを投稿する必要があります。 この手順では、エージェント カードがエージェント 365 レジストリに登録され、管理者が表示されます。

1. 前のセクションに示すように、Microsoft Graph APIを使用してエージェント ID ブループリントを作成します。
2. すぐにエージェント レジストリ API の呼び出しに従って、管理者が管理する必要があるメタデータなど、対応するエージェント カードを投稿します。
3. いずれかの呼び出しで一時的な障害が発生しても環境が回復可能な状態になるように、再試行セーフな方法で 2 つの呼び出しパターンを処理します。

要求スキーマと応答スキーマ、必要なアクセス許可、コード サンプルについては、 [Agent Registry API リファレンスを参照してください](https://learn.microsoft.com/ja-jp/microsoft-365/copilot/extensibility/api/admin-settings/agent-registration/overview)。

Tip

エージェント 365 レジストリに表示されない既存のエージェント ID ブループリントがある場合は、エージェント レジストリ API を使用してそれらを登録します。 エージェント ID ブループリントを一括で使用する場合は、バッチ エンドポイントを使用します。 詳細については、「 [エージェント レジストリと Microsoft Agent 365 の統合](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-registry-convergence)」を参照してください。

#### エージェント 365 レジストリにない既存のエージェント ID ブループリント

以前にMicrosoft Entra エージェント ID Graph APIを使用して作成されたが、エージェント 365 レジストリには現在表示されていないエージェント ID ブループリントの場合は、エージェント レジストリ API を使用してそれらを登録できます。 この手順により、エージェント 365 レジストリに確実に表示されます。

### エージェント ID ブループリントを削除する

エージェントが使用停止になったら、関連付けられているエージェント ID ブループリントを削除します。 ブループリントを削除すると、すべての子エージェント ID とエージェントのユーザー アカウントの自動クリーンアップがトリガーされます。 詳しい削除と復元の手順については、「 [エージェント ID オブジェクトの削除と復元」を](https://learn.microsoft.com/ja-jp/entra/agent-id/howto-delete-agent-identity)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/create-delete-agent-identities"} -->
## Microsoft エージェント ID プラットフォームでエージェント ID を作成する - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/create-delete-agent-identities
- Service: entra-id / agent-id
- Article date: 2026-04-28
- Summary: Microsoft Graph API とさまざまな認証ライブラリを使用して、テナント内の AI エージェントを表すエージェント ID を作成する方法について説明します。

エージェント ID ブループリントを作成した後、次の手順は、テナント内の AI エージェントを表す 1 つ以上の [エージェント ID を](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-identities) 作成することです。 エージェント ID の作成は、通常、新しい AI エージェントをプロビジョニングするときに実行されます。

エージェント ID は、次の 2 つの方法で作成できます。

- **Microsoft Entra 管理センター** - 管理センター ウィザードを使用して、ID をすばやく個別に作成します。
- **Microsoft Graph API** — エージェント ID をプログラムで作成する Web サービスを構築します。これは、大規模な自動プロビジョニングに役立ちます。

テスト目的でエージェント ID をすばやく作成する場合は、[このMicrosoft Entra PowerShell モジュールを使用してエージェント ID を作成および使用することを検討してください](https://aka.ms/agentidpowershell)。

### 前提条件

エージェント ID を作成するには、次のものが必要です。

- [エージェント ID ブループリント](https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint)。 作成プロセスからエージェント ID ブループリント アプリ ID を記録します。
- エージェント ID 作成ロジックをホストする Web サービスまたはアプリケーション (ローカルで実行されているか、Azureにデプロイされている)。 この前提条件は、エージェント ID をプログラムで作成する場合にのみ適用されます。

### Microsoft Entra 管理センターを使用する

既存のブループリントを選択し、所有者とスポンサーを割り当てることで、Microsoft Entra 管理センターでエージェント ID を直接作成できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Agents**&gt;**Agent ids** に移動します。
3. [ **新しいエージェント ID (プレビュー)]を**選択します。
4. [基本] タブで、次の **操作** を行います。

    - [ **エージェント ブループリント**] で、エージェント ID を作成するブループリントを選択します。
    - **[エージェント ID 名**] フィールドに名前を入力し、[**次へ**] を選択します。

        [Image: ブループリントの選択フィールドと名前フィールドを含む [基本] タブを示すエージェント ID の作成ウィザードのスクリーンショット。]
5. [ **所有者とスポンサー** ] タブで、必要に応じて ID の所有者とスポンサーを追加します。

    - [ **所有者** ] フィールドの横にある鉛筆アイコンを選択して、このエージェント ID を管理できるユーザーを変更または追加します。
    - **[スポンサー**] フィールドの横にある鉛筆アイコンを選択して、このエージェント ID をスポンサーできるユーザーを変更または追加します。

    Note

    スポンサーには、ユーザー、動的メンバーシップ グループ、またはMicrosoft 365 グループを指定できます。 セキュリティ グループとロール割り当て可能なグループは、スポンサーとしてサポートされていません。
6. **次へ**を選択します。
7. 設定を確認し、[ **作成**] を選択します。
8. [ **完了]** を選択してウィザードを終了するか、 **エージェント ID に移動して** ID の詳細ページを表示するか、その他の設定を構成します。

次の手順では、Microsoft Graph APIとMicrosoft.Identity.Webを使用してプログラム的にエージェント ID を作成する方法について説明します。 最初にアクセス トークンを取得してから、作成 API を呼び出します。

## [Microsoft Graph API](#tab/microsoft-graph-api)
#### エージェント ID ブループリントを使用してアクセス トークンを取得する

エージェント ID ブループリントを使用して、各エージェント ID を作成します。 エージェント ID ブループリントを使用して、Microsoft Entraからアクセス トークンを要求します。

マネージド ID を資格情報として使用する場合は、まずマネージド ID を使用してアクセス トークンを取得する必要があります。 マネージド ID トークンは、コンピューティング環境でローカルに公開されている IP アドレスから要求できます。 詳細については、 [マネージド ID のドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/)。

```
GET http://169.254.169.254/metadata/identity/oauth2/token?api-version=2019-08-01&resource=api://AzureADTokenExchange/.default
Metadata: True
```

マネージド ID のトークンを取得したら、エージェント ID ブループリントのトークンを要求します。

```
POST https://login.microsoftonline.com/<your-tenant-id>/oauth2/v2.0/token
Content-Type: application/x-www-form-urlencoded

client_id=<agent-blueprint-id>
scope=https://graph.microsoft.com/.default
client_assertion_type=urn:ietf:params:oauth:client-assertion-type:jwt-bearer
client_assertion=<msi-token>
grant_type=client_credentials
```

`client_secret` パラメーターは、クライアント シークレットがローカル開発で使用されている場合に、`client_assertion`と`client_assertion_type`の代わりに使用することもできます。

## [Microsoft。Identity.Web](#tab/microsoft-identity-web)
Microsoft.Identity.Web をインストールするには:

```ps
dotnet add package Microsoft.Identity.Web
```

*Microsoft。Identity.Web* には、アクセス トークンを自動的に要求し、それを送信 HTTP 要求にアタッチするインターフェイスが含まれています。 *Microsoft.Identity.Web* を使用する場合、次の手順に進むことができます。

---

### エージェント ID を作成する

前の手順で取得したアクセス トークンを使用して、テナントにエージェント ID を作成できるようになりました。 エージェント ID の作成は、ユーザーが新しいエージェントを作成するためのボタンを選択するなど、さまざまなイベントやトリガーに応答して発生する可能性があります。 エージェントごとに 1 つのエージェント ID を作成することをお勧めしますが、ニーズに応じて異なるアプローチを選択することもできます。

## [Microsoft Graph API](#tab/microsoft-graph-api)
@odata.typeを使用するときは、常に OData-Version ヘッダーを含めます。

```
POST https://graph.microsoft.com/beta/serviceprincipals/Microsoft.Graph.AgentIdentity
OData-Version: 4.0
Content-Type: application/json
Authorization: Bearer <token>
{"displayName": "My Agent Identity","agentIdentityBlueprintId": "<my-agent-blueprint-id>","sponsors@odata.bind": [	"https://graph.microsoft.com/v1.0/users/<id>",	"https://graph.microsoft.com/v1.0/groups/<group-id>"]
}
```

Note

スポンサーとしてグループを割り当てる場合、 [サポートされているグループの種類](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-owners-sponsors-managers#sponsors) のみが受け入れられます。 グループは所有者としてサポートされていません。

## [Microsoft。Identity.Web](#tab/microsoft-identity-web)
*Microsoft.Identity.Web*を使用して、エージェント ID を作成する Microsoft Graph API 要求を実行するには、次の MISE 構成ファイルを追加します。

Warnung

セキュリティ リスクのために、エージェント ID ブループリントの運用環境では、クライアント シークレットをクライアント資格情報として使用しないでください。 代わりに、マネージド ID またはクライアント証明書を使用 [するフェデレーション ID 資格情報 (FIC)](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-config-app-trust-managed-identity) などのより安全な認証方法を使用してください。 これらの方法により、アプリケーション構成内に機密性の高いシークレットを直接格納する必要がなくなり、セキュリティが強化されます。

```json
{
  "AzureAd": {"Instance": "https://login.microsoftonline.com/","TenantId": "<your-tenant-id>","ClientId": "<my-agent-blueprint-id>","Scopes": "access_agent","ClientCredentials": [	{		"SourceType": "ClientSecret",		"ClientSecret": "your-client-secret"	}]
  },

  "DownstreamApis": {"agent-identity": {  "BaseUrl": "https://graph.microsoft.com",  "RelativePath": "/beta/serviceprincipals/Microsoft.Graph.AgentIdentity",  "Scopes": ["00000003-0000-0000-c000-000000000000/.default"],  "RequestAppToken": true}
  }
}
```

ASP.NET Core アプリ (*Program.cs*) のコードは次の例です。

```csharp
using System.Text.Json.Serialization;
using Microsoft.Identity.Abstractions;
using Microsoft.Identity.Web;
using Microsoft.Identity.Web.Resource;
using Microsoft.IdentityModel.S2S.Extensions.AspNetCore;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddMicrosoftIdentityWebApiAuthentication(builder.Configuration)
    .EnableTokenAcquisitionToCallDownstreamApi();
builder.Services.AddInMemoryTokenCaches();
var app = builder.Build();

app.UseHttpsRedirection();
app.UseAuthentication();
app.UseAuthorization();

// Create an Agent identity
app.MapGet("/create-agent-identity", async (HttpContext httpContext) =>
{
    try
    {
        // Get the service to call the downstream API (preconfigured in the appsettings.json file)
        IDownstreamApi downstreamApi = httpContext.RequestServices.GetRequiredService<IDownstreamApi>();

        // Call the downstream API with a POST request to create an Agent Identity
        var jsonResult = await downstreamApi.PostForAppAsync<AgentIdentity, AgentIdentity>(
            "agent-identity",
            new AgentIdentity
            {
                displayName = "My agent identity",
                agentIdentityBlueprintId = "<my-agent-blueprint-id>",
                sponsorsOdataBind = new[] { "https://graph.microsoft.com/v1.0/users/<id>" }
            });
        return jsonResult?.id;
    }
    catch (Exception ex)
    {
        return ex.Message;
    }
});

app.Run();

// Type declarations must follow the top-level statements.
public class AgentIdentity
{
    [JsonPropertyName("@odata.type")]
    public string @odata_type { get; set; } = "#Microsoft.Graph.AgentIdentity";

    [JsonPropertyName("displayName")]
    public string? displayName { get; set; }

    [JsonPropertyName("agentIdentityBlueprintId")]
    public string? agentIdentityBlueprintId { get; set; }

    [JsonPropertyName("id")]
    public string? id { get; set; }

    [JsonPropertyName("sponsors@odata.bind")]
    public string[]? sponsorsOdataBind { get; set; }

    [JsonPropertyName("owners@odata.bind")]
    public string[]? ownersOdataBind { get; set; }
}
```

---

### エージェント ID を削除する

エージェントの割り当てが解除または破棄されると、サービスは関連するエージェント ID も削除する必要があります。

## [Microsoft Graph API](#tab/microsoft-graph-api)
```http
DELETE https://graph.microsoft.com/beta/serviceprincipals/<agent-identity-id>
OData-Version: 4.0
Content-Type: application/json
Authorization: Bearer <token>
```

## [Microsoft。Identity.Web](#tab/microsoft-identity-web)
```csharp
// Delete an Agent identity
app.MapGet("/delete-agent-identity", async (HttpContext httpContext, string id) =>
{// Get the service to call the downstream API (preconfigured in the appsettings.json file)IDownstreamApi downstreamApi = httpContext.RequestServices.GetRequiredService<IDownstreamApi>();
// Call the downstream API with a DELETE request to remove an Agent Identityvar jsonResult = await downstreamApi.DeleteForAppAsync<string, string>(	"agent-identity",	null!,	options =>	{		options.RelativePath += $"/{id}"; // Specify the ID of the agent identity to delete	});return jsonResult;
});
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/disable-agent-identities"} -->
## テナントでエージェント ID を無効にする - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/disable-agent-identities
- Service: entra-id / agent-id
- Article date: 2026-04-29
- Summary: 条件付きアクセス ポリシーと作成制限を使用して、Microsoft Entra ID テナントのエージェント ID を無効にする方法について説明します。

テナント内のエージェント ID を監視する IT 管理者は、問題を調査したりエージェントの使用状況を確認したりするために、エージェントアクティビティを一時的に停止することが必要になる場合があります。 必要に応じて、テナントのエージェント ID とエージェント ID ブループリントを無効にすることができます。 どちらのアクションにも、先に進む前に理解しておく必要がある影響があります。

また、組織がテナントでエージェント ID の作成または使用を制限する必要がある場合は、条件付きアクセス ポリシーを構成し、エージェントの作成アクセス許可を削除することもできます。 これらのプロセスは、エージェント ID を無効にする別のアプローチとして機能します。 この記事では、エージェント ID の無効化に関連するシナリオについて説明します。

### AI エージェントによって使用される ID の種類

Microsoft Entra テナントには、Microsoft Entra エージェント ID の有無に関係なく AI エージェントが含まれている場合があります。

- **エージェント ID を持つエージェント**: Microsoft Entra エージェント IDで作成されたエージェント、またはMicrosoft Copilot Studio、Azure AI Foundry、Security Copilotなどのシステムの最新のイテレーションを使用して作成されたエージェントは、エージェント ID で作成されます。 エージェント ID には、明確な分類、豊富なメタデータ、AI エージェントの固有のセキュリティ課題に対処するように設計された機能があります。
- **エージェント ID を持たないエージェント**: 以前のバージョンのCopilot StudioおよびAzure AI Foundryで作成されたエージェントは、テナントでクラシック アプリケーション/サービス プリンシパルとして作成されている可能性があります。 これらのアプリケーション/サービス プリンシパルには、AI エージェントとして示すタグ値が含まれる場合がありますが、Microsoft Entra エージェント ID はありません。 これらは、テナント内の他のすべてのアプリケーション/サービス プリンシパルと同じポリシー、ガバナンス、およびプロセスの対象となります。

### エージェント ID の無効化に関する考慮事項

テナントでエージェント ID を無効にすると、新しい AI エージェントの作成や使用を単に停止するよりも、より広範な結果が生じることがあります。 続行する前に、次の考慮事項を評価します。

- 貴社内で動作中の既存のエージェントが失敗する恐れがあります。
- エージェント ID を利用できることを前提とする Microsoft 製品エクスペリエンス (たとえば、Copilot Studio エージェント、Security Copilot シナリオ エージェント、Microsoft Entra 条件付きアクセス 最適化エージェント) は、失敗するか、エージェント固有の追跡機能と制御機能を備えていない標準のサービス プリンシパルにフォールバックする可能性があります。

### エージェント ID の作成とアクティビティの監視

エージェント ID またはエージェント ID ブループリントを無効にする前に、それらの ID に関連付けられているアクティビティの種類に注意する必要があります。 エージェント ID アクティビティは、元のベース ID の種類でログに記録されます。 たとえば、エージェント ID の作成は *サービス プリンシパルの追加* として表示され、エージェント ID ブループリントの追加は監査ログに *アプリケーションの追加* として表示されます。

監査イベントにエージェント ID が関係するかどうかを識別するには、`agentType`フィールドと`initiatedBy` フィールドの`targetResources` プロパティを確認します。 `notAgentic`以外の値は、エージェントの関与を示します。

`agentSignIn` リソースの種類は、サインイン イベントをエージェントとして識別および分類する説明情報を提供します。 この値を使用すると、エージェント ID が認証イベントに関係する ID のサブタイプだった時期を判断できます。

詳細については、[エージェントのサインイン ログを](https://learn.microsoft.com/ja-jp/entra/agent-id/sign-in-audit-logs-agents)参照してください

### エージェント ID を無効にする方法

エージェント アクティビティを確認したら、シナリオに合ったアプローチを選択します。 Microsoft Entra 管理センター内のエージェントを無効にすると、オブジェクト スコープで、他のユーザーに影響を与えることなく、特定のブループリントまたはエージェント ID を対象とします。 条件付きアクセス ポリシーは、エージェント ID やブループリントを変更することなく、幅広いカテゴリの ID のトークン発行をブロックするテナント全体の適用です。 2 つのアプローチを組み合わせることができます。 テナントに条件付きアクセス ポリシーを適用するには、Microsoft Entra ID P1 ライセンスが必要です。

条件付きアクセス ポリシーでは、エージェント ID の認証とトークンの発行をブロックできます。 ポリシーを適用すると、既存のエージェント ID と新しいエージェント ID が認証されなくなりますが、テナントでのエージェント ID の作成は妨げられることはありません。

ポリシーを適用する前に [、これらのポリシーをレポート専用モード](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-report-only) で実行して、その影響を理解することをお勧めします。

**次の場合、Microsoft Entra 管理センターでエージェントを無効にします:**

- エージェント ID がトークンを受信して認証されないようにするが、エージェント ID とそのメタデータをテナントに保持する必要がある。

**条件付きアクセス ポリシーは、次の場合に使用します。**

- 個々のオブジェクトを変更せずに、作成しなかったものも含め、テナント全体のすべてのエージェント ID が認証されないようにする必要があります。
    - ポリシー 1: エージェント ID 認証をブロックします。
- エージェント ID オブジェクト自体を無効にせずに、ユーザーに代わって動作するエージェントがトークンを受け取らないようにする (委任されたアクションを実行しているエージェントなど)
    - ポリシー 2: エージェントのユーザー アカウント認証をブロックします。
- エージェント間および自律フローは影響を受けずに、人間のユーザーがエージェントにサインインしたり、エージェントのアクションをトリガーしたりできないようにする必要がある
    - ポリシー 3: エージェントへのユーザーのサインインをブロックします。
- コンプライアンスまたはインシデント対応のために、すべてのエージェント認証に対して一時的で元に戻せるテナント全体の保留を適用し、最初にレポート専用モードで 3 つのポリシーをすべて適用してから、それらを適用する必要があります。

### Microsoft Entra 管理センターでエージェント ID とエージェント ID ブループリントを無効にする

エージェント ID を無効にするには:

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[Agent ID Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-administrator) としてサインインします。
2. **Entra ID**&gt;**Agents**&gt;**Agent ids** に移動します。
3. 無効にするエージェント ID を選択し、[ **無効]** を選択します。

エージェント ID ブループリントを無効にするには:

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[Agent ID Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-administrator) としてサインインします。
2. **Entra ID**&gt;**エージェント**&gt;**エージェント設計図**に移動してください。
3. 無効にするエージェント ID ブループリントを選択し、[ **無効]** を選択します。

### エージェント アクティビティを無効にする条件付きアクセス ポリシーを作成する

トークンの発行をブロックしたり、ユーザーがエージェントにサインインできないようにしたりするために使用できるポリシー テンプレートは 3 つあります。 これらのポリシーは、Microsoft Entra 管理センターまたは Microsoft Graph APIを使用して作成できます。 これらのポリシーを適用する前に、まずレポート専用モードでこれらのポリシーを適用して、その影響を理解することをお勧めします。

- 条件付きアクセスを使用してエージェント ID へのトークン発行をブロックする &gt; ポリシー 1: エージェント ID 認証をブロックする
- 条件付きアクセス &gt; ポリシー 2 を使用してエージェントのユーザー アカウントへのトークン発行をブロックする: エージェントのユーザー アカウント認証をブロックする
- 条件付きアクセスを使用してエージェントへのユーザーのサインインをブロックする &gt; ポリシー 3: エージェントへのユーザーのサインインをブロックする

#### ポリシー 1: エージェント ID 認証をブロックする

次の手順は、エージェント ID を使用して要求されたアクセス トークンの発行をブロックする条件付きアクセス ポリシーを作成するのに役立ちます。

## [Microsoft Entra 管理センター](#tab/microsoft-entra-admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Policies** に移動します。
3. **[新しいポリシー]** を選択します。
4. ポリシーの名前を設定してください。 ポリシーの名前に対する意味のある標準を組織で作成することをお勧めします。
5. [ **割り当て]** で、[ **ユーザー、エージェント、またはワークロード ID] を選択します**。
    1. 「**含める**」で、「**すべてのエージェント ID**」を選択します。
    2. [ **除外**] で [なし] を選択 **します。**
6. **ターゲット リソース**&gt;**リソース（以前のクラウド アプリ）**&gt;にある**Include**で、**すべてのリソース（以前の「すべてのクラウド アプリ」）**を選択します。
7. **アクセス制御**&gt;**付与**の配下。
    1. **[ブロック]** を選択します。
    2. **[選択]**
8. 設定を確認し、 **[ポリシーの有効化]** を **[レポート専用]** に設定します。
9. **[作成]** を選択して、ポリシーを作成および有効化します。

## [マイクロソフト グラフ API](#tab/microsoft-graph-api)
Microsoft Graph API を使用して **Block エージェント ID 認証** ポリシーを作成するための JSON の例:

```http
POST https://graph.microsoft.com/beta/identity/conditionalAccess/policies
Content-type: application/json

{
    "displayName": "Block all agent identities from accessing resources",
    "conditions": {
        "clientApplications": {
            "includeAgentIdServicePrincipals": [
                "All"
            ],
            "excludeAgentIdServicePrincipals": [],
            "agentIdServicePrincipalFilter": null
        },
        "applications": {
            "includeApplications": [
                "All"
            ],
            "excludeApplications": []
        }
    },
    "grantControls": {
        "operator": "AND",
        "builtInControls": [
            "block"
        ]
    },
    "state": "enabledForReportingButNotEnforced"
}
```

---

#### ポリシー 2: エージェントのユーザー アカウント認証をブロックする

次の手順は、エージェントのユーザー アカウントを使用して要求されたアクセス トークンの発行をブロックする条件付きアクセス ポリシーを作成するのに役立ちます。

## [Microsoft Entra 管理センター](#tab/microsoft-entra-admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Policies** に移動します。
3. **[新しいポリシー]** を選択します。
4. ポリシーの名前を設定してください。 ポリシーの名前に対する意味のある標準を組織で作成することをお勧めします。
5. [ **割り当て]** で、[ **ユーザー、エージェント、またはワークロード ID] を選択します**。
    1. [**含める**&gt;**エージェントを選択**&gt;**ユーザーとして機能するエージェント**&gt;すべてのユーザーとして機能するエージェントを**選択します**]
6. **ターゲット リソース**&gt;**リソース（以前のクラウド アプリ）**&gt;にある**Include**で、**すべてのリソース（以前の「すべてのクラウド アプリ」）**を選択します。
7. **アクセス制御**&gt;**付与**の配下。
    1. **アクセスをブロック**を選択します。
    2. **[選択]**
8. 設定を確認し、 **[ポリシーの有効化]** を **[レポート専用]** に設定します。
9. **[作成]** を選択して、ポリシーを作成および有効化します。

## [マイクロソフト グラフ API](#tab/microsoft-graph-api)
Microsoft Graph API を使用して **Block エージェントのユーザー アカウント認証** ポリシーを作成するための JSON の例:

```http
POST https://graph.microsoft.com/beta/identity/conditionalAccess/policies
Content-type: application/json

{
    "displayName": "Block all agent users from accessing resources",
    "conditions": {
        "users": {
            "includeUsers": [
                "AllAgentIdUsers"
            ]
        },
        "applications": {
            "includeApplications": [
                "All"
            ]
        }
    },
    "grantControls": {
        "operator": "AND",
        "builtInControls": [
            "block"
        ]
    },
    "state": "enabledForReportingButNotEnforced"
}
```

---

#### ポリシー 3: エージェントへのユーザーのサインインをブロックする

次の手順は、条件付きアクセス ポリシーを作成して、人間のユーザーから要求されたときにエージェント リソースへのアクセス トークンの発行をブロックするのに役立ちます。 これにより、人間のユーザーはエージェントにサインインすることも、エージェントがその代わりにアクションを実行することもできなくなります。

## [Microsoft Entra 管理センター](#tab/microsoft-entra-admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Policies** に移動します。
3. **[新しいポリシー]** を選択します。
4. ポリシーの名前を設定してください。 ポリシーの名前に対する意味のある標準を組織で作成することをお勧めします。
5. [ **割り当て]** で、[ **ユーザー、エージェント、またはワークロード ID] を選択します**。
    1. [**含める**] で、[**すべてのユーザー**] を選択します
    2. **[除外]**で、次のようにします。
        1. **[なし]** を選択します
6. **ターゲット リソース**&gt;**リソース (以前のクラウド アプリ)**&gt;**で** **すべてのエージェント リソース**を選択します。
7. **アクセス制御**&gt;**付与**の配下。
    1. **[ブロック]** を選択します。
    2. **[選択]**
8. 設定を確認し、 **[ポリシーの有効化]** を **[レポート専用]** に設定します。
9. **[作成]** を選択して、ポリシーを作成および有効化します。

## [マイクロソフト グラフ API](#tab/microsoft-graph-api)
Microsoft Graph API を使用した **エージェントへのサインインをブロックする** ポリシー作成用の JSON の例:

```http
POST https://graph.microsoft.com/beta/identity/conditionalAccess/policies
Content-type: application/json

{
    "displayName": "Block all users from accessing agent resources",
    "conditions": {
        "users": {
            "includeUsers": [
                "All"
            ]
        },
        "applications": {
            "includeApplications": [
                "AllAgentIdResources"
            ],
            "excludeApplications": []
        }
    },
    "grantControls": {
        "operator": "AND",
        "builtInControls": [
            "block"
        ]
    },
    "state": "enabledForReportingButNotEnforced"
}
```

---

### (省略可能)エージェント ID の作成をブロックする

条件付きアクセス ポリシーは、新しく作成されたエージェント ID を含め、テナント内のすべてのエージェント ID の使用を防ぐのに十分です。 テナントでエージェント ID が作成されないようにするには、このセクションの手順に従います。

エージェント ID は、さまざまなチャネルを介してテナントに入ることができます。 詳細については、 [エージェント ID 作成チャネルを](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-id-creation-channels)参照してください。 エージェント ID の作成は、次の方法でブロックできます。

- Microsoft Entra 管理センターおよびその他のMicrosoft Entra エクスペリエンスでのエージェント ID の作成をブロックします。
- 独立系ソフトウェア ベンダー (ISV) からのエージェント ID の取得をブロックします。
- Microsoft製品とサービスによるエージェント ID の作成をブロックします。

#### Microsoft Entra IDでのエージェント ID の作成をブロックする

ユーザーが Microsoft Entra 管理センター やその他のMicrosoft Entra エクスペリエンスでエージェント ID を作成できないようにするには、

1. **エージェント ID 管理者またはエージェント ID** **開発者**の組み込みロールに対する適格またはアクティブな割り当てを削除します。
2. エージェント ID の作成を許可するサービス プリンシパルに付与されている *oauth2PermissionGrants* または *appRoleAssignment* を削除します。 特定のアクセス許可については、次の表を参照してください。

| 許可 | タイプ |
| --- | --- |
| `AgentIdentity.Create.All` | アプリケーションのアクセス許可 |
| `AgentIdentityBlueprint.Create` | 委任されたアクセス許可とアプリケーションアクセス許可 |
| `AgentIdentityBlueprint.ReadWrite.All` | 委任されたアクセス許可とアプリケーションアクセス許可 |
| `AgentIdentityBlueprintPrincipal.Create` | 委任されたアクセス許可とアプリケーションアクセス許可 |
| `AgentIdentityBlueprintPrincipal.ReadWrite.All` | 委任されたアクセス許可とアプリケーションアクセス許可 |
| `AgentIdUser.ReadWrite.IdentityParentedBy` | 委任されたアクセス許可とアプリケーションアクセス許可 |
| `AgentIdUser.ReadWrite.All` | 委任されたアクセス許可とアプリケーションアクセス許可 |
| `User.ReadWrite.All` | 委任された権限とアプリケーション権限。 これらのアクセス許可は、人間のユーザー アカウントを管理するためにも使用できます。 このアクセス許可を削除すると、システムは人間のユーザーを管理するためのアクセス権を失います。 |

これらのアクセス許可の詳細については、 [Microsoft Graph のアクセス許可リファレンスを参照](https://learn.microsoft.com/ja-jp/graph/permissions-reference) してください。

#### ISV からのエージェント ID の取得をブロックする

ユーザーが ISV エージェント ID ブループリントに同意してエージェント ID を作成できないようにするには、Microsoft Entra [settings を使用して、アプリケーションに同意するユーザー機能を無効にします](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent)。 アプリケーションに同意を付与する機能にも影響を与えずに、ユーザーがエージェント ID に同意を許可できないようにする方法はありません。 ユーザーの同意を無効にすることは広範であり、ユーザーの同意フローに依存する正当な非エージェント SaaS アプリのオンボードや、既存の非エージェント アプリへのアクセス許可の付与もブロックされます。

この影響が大きすぎる場合は、ユーザーの同意を有効のままにし、代わりに 条件付きアクセス ブロック ポリシー に依存して、承認されていない ISV エージェント ID のトークンを防ぎます。

#### Microsoft製品とサービスによるエージェント ID の作成をブロックする

Microsoft製品とサービスがテナントにエージェント ID を作成できないようにするには、各Microsoft製品で使用可能な設定を使用する必要があります。

##### Security Copilot

Security Copilotによってエージェント ID の作成を無効にするには、すべてのセキュリティ コンピューティング ユニット (SCU) 容量を削除してSecurity Copilotをシャットダウンします。 これにより、エージェントと Security Copilot 自体の両方がブロックされます。 詳細については、 [Security Copilot のドキュメントを参照してください](https://learn.microsoft.com/ja-jp/copilot/security/)。 次のロールを持つユーザーは、SCU 容量を作成することで、Security Copilot を有効に戻すことができます。

- 請求管理者
- Microsoft Entra コンプライアンス管理者
- グローバル管理者
- Intune 管理者
- セキュリティ管理者
- Purview コンプライアンス管理者
- Purview データ ガバナンス管理者
- Purview Organization Management。

Security Copilot の使用を有効にし、エージェントの作成をブロックするには、ブロックするエージェントの種類に応じて次の方法を使用できます。

- Microsoft エージェント (Microsoft Entra 条件付きアクセス エージェントなど) をブロックするには、関連するロールを持つユーザーに、各エージェントを有効にしないように要求します。 現在エージェントを有効にできるロールは次のとおりです。
    - セキュリティ管理者
    - アイデンティティガバナンス管理者
    - ライフサイクル ワークフロー管理者
    - セキュリティ コパイロット 貢献者
- サード パーティのエージェント (Microsoft が所有していないエージェント) をブロックするには、Security Copilot ワークスペースの所有者/共同作成者ロールからすべてのユーザーを削除します。

##### Copilot Studio

Copilot Studioの使用とエージェント ID の作成を無効にするには、ライセンス、RBAC、またはデータ ポリシーを使用してエージェントの作成を制限できます。

- ライセンス：
    - ユーザーが Copilot Studio の無料試用版にサインアップできないようにします。
    - Copilot Studio ライセンスをユーザーに割り当てないでください。
- RBAC:
    - ユーザーが無料試用版にサインアップできないようにします。これにより、試用版環境が作成されなくなります。
    - Copilot Studio 環境を作成するには、Power Platform 管理者ロールが必要です。 環境へのアクセスと、新しい環境を作成する機能を削除します。
- データ ポリシー:
    - エージェントが公開されないようにポリシーを適用して、誰もエージェントとチャットできないようにします。 これにより、エージェントの作成はブロック **されません** 。

[データ ポリシーの使用を推奨](https://learn.microsoft.com/ja-jp/microsoft-copilot-studio/security-faq#can-i-disable-microsoft-copilot-studio-agent-creation-in-my-organization)する詳細については、Copilot Studio のドキュメントを参照してください。

##### Azure AI Foundry

エージェント ID の作成を無効にし、ユーザーがAzure AI Foundryでプロジェクトとエージェントを作成できないようにするには、無料試用版または従量課金制を使用してAzureサブスクリプションを作成するユーザー機能を無効にします。 これにより、次の設定が適用されます。

- サブスクリプションを作成できるのは、課金管理者ロールまたはアカウント管理者ロールだけです。
- サブスクリプション内で Foundry プロジェクトを作成できるのは、Azure AI アカウント所有者ロールだけです。
- プロジェクト内で、ユーザーはエージェントを作成するための Azure AI ユーザー ロールを持っている必要があります。

これらのロールをユーザーに割り当てない場合、ユーザーはエージェントまたはエージェント ID を作成できません。

詳細については、 [Azure AI Foundry のドキュメントを参照してください](https://learn.microsoft.com/ja-jp/azure/ai-foundry/concepts/rbac-azure-ai-foundry)。

##### Microsoft Teams

Microsoft Teamsを使用してエージェント ID の作成を無効にするには、Teams 管理センターの設定を使用します。

- ユーザーが Teams にアプリやエージェントを追加できないようにします。
- Microsoft アプリ、サード パーティ製アプリ、またはカスタム アプリを使用してピボットします。
- 必要に応じて、特定のユーザー/グループに対して特定のアプリ/エージェントを有効にします。

詳細については、 [Teams 管理センターのドキュメントを参照してください](https://learn.microsoft.com/ja-jp/microsoftteams/manage-apps)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/error-codes"} -->
## Microsoft エージェント ID プラットフォーム エラー コード

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/error-codes
- Service: entra-id / agent-id
- Article date: 2025-12-02
- Summary: エージェント ID、エージェント ブループリント、およびエージェント ID のエラー コードについて説明します。

この記事では、Microsoft エージェント ID プラットフォームを使用するときに発生する可能性があるエラー コードに関する包括的なリファレンスを提供します。

### アプリケーションでのエラー コードの処理

[OAuth2.0 仕様](https://tools.ietf.org/html/rfc6749#section-5.2)では、エラー応答の`error`部分を使用して認証中にエラーを処理する方法に関するガイダンスが提供されます。 エラー コードの処理と可能な`error` フィールド値の詳細については、[Microsoft ID プラットフォーム および OAuth 2.0 エラー コードのドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes#handling-error-codes-in-your-application)を参照してください。

### クォータと制限エラー

これらのエラーは、テナント クォータまたはエージェント ID ブループリントまたはエージェント ID の最大数を超えた場合に発生します。

| エラー コード | 説明 |
| --- | --- |
| `Agent_Directory_QuotaExceeded` | エージェント ID ブループリントやエージェント ID が、テナント リソース クォータの 95% を超えています。 さらに作成するには、不要なブループリントまたは ID を完全に削除する必要があります。 |
| `AgentBlueprint_LimitExceeded` | アクティブなアイテムや論理的に削除されたアイテムを含め、許可されているエージェント ID ブループリントの最大数に達しました。 さらに作成するには、不要なブループリントを完全に削除する必要があります。 |
| `AgentIdentity_LimitExceeded` | アクティブなエントリや論理的に削除されたエントリを含め、許可されているエージェント ID の最大数に達しました。 さらに追加するには、不要なエージェント ID を完全に削除する必要があります。 |

### エージェント ID ブループリント エラー

これらのエラーは、エージェント ID ブループリントでサポートされていないプロパティまたは API バージョンが要求に含まれている場合に発生します。

| エラー コード | 説明 |
| --- | --- |
| `AgentBlueprint_IncompatibleProperty` | 要求で指定されたプロパティは、エージェント ID ブループリントと互換性がありません。設定できません。 |
| `AgentBlueprint_IncompatibleProperty_NullPropertyName` | 要求内のプロパティはエージェント ID ブループリントと互換性がありません。設定できません。 |
| `AgentBlueprint_NotSupportedOnApiVersion` | エージェント ID ブループリントは、この要求で使用される API バージョンではサポートされていません。 |

### エージェント ID ブループリントの主要なエラー

これらのエラーは、エージェント ID ブループリント プリンシパルがサポートしていないプロパティ、API バージョン、または親リソースが要求に含まれている場合に発生します。

| エラー コード | 説明 |
| --- | --- |
| `AgentBlueprintPrincipal_AgentIdentity_IncompatibleProperty` | 要求で指定されたプロパティはエージェント ID と互換性がありません。設定できません。 |
| `AgentBlueprintPrincipal_IncompatibleProperty` | 要求で指定されたプロパティは、エージェント ID ブループリント プリンシパルと互換性がありません。設定できません。 |
| `AgentBlueprintPrincipal_NotSupportedOnApiVersion` | エージェント ID ブループリント プリンシパルは、この要求で使用される API バージョンではサポートされていません。 |
| `AgentBlueprintPrincipal_RequireAgentBlueprint` | エージェント ID ブループリント プリンシパルは、エージェント ブループリントに対してのみ作成できます。 |

### エージェント ID エラー

これらのエラーは、要求が不足しているブループリント プリンシパル、互換性のない親の種類、またはエージェント ID のサポートされていない資格情報または API バージョンを参照している場合に発生します。

| エラー コード | 説明 |
| --- | --- |
| `AgentIdentity_AgentBlueprintPrincipalDoesNotExist` | 指定したエージェント ID ブループリント ID に必要なエージェント ID ブループリント プリンシパルが存在しません。 |
| `AgentIdentity_CredentialsNotSupported` | エージェント ID では資格情報はサポートされていません。 すべての資格情報をエージェント ID ブループリントに追加する必要があります。 |
| `AgentIdentity_IncompatibleParentType` | 指定されたアプリケーション (AppId) がエージェント ブループリントではありません。 *AgentIdentityBlueprintId* は、有効なエージェント ID ブループリントの *AppId* に設定する必要があります。 |
| `AgentIdentity_NotSupportedOnApiVersion` | エージェント ID は、この要求で使用される API バージョンではサポートされていません。 |

### エージェント ID の作成エラー

これらのエラーは、呼び出し元プリンシパルが要求されたエージェント ID の作成を許可されていない場合に発生します。

| エラー コード | 説明 |
| --- | --- |
| `Error_AgentBlueprintCannotCreateAssociatedIdentity` | エージェント ID ブループリントでは、別のエージェント ID ブループリントに関連付けられているエージェント ID を作成できません。 このエージェント ID を作成するには、エージェント ID に関連付けられているエージェント ID ブループリントを使用するか、エージェント ID を作成するために必要なロール/アクセス許可を持つ別のプリンシパルで操作を実行します。 |
| `Error_AgentIdentitiesCreatingAgentIdentitiesNotAllowed` | エージェント ID は、他のエージェント ID を作成できません。 エージェント ID を作成するには、関連付けられているエージェント ID ブループリント プリンシパルまたは非エージェント ブループリント サービス プリンシパルを、必要なアクセス許可と共に使用します。 |
| `Error_AgentIdentitySelfCreateRequired` | アプリケーションは、エージェント ID を自身の下にのみ作成できます。 指定された *AgentIdentityBlueprintId* が、呼び出し元アプリケーションの *AppId と*一致しません。 |

### ヘルプを取得する

ご質問がある場合、またはお探しの内容が見つからない場合は、「 [開発者向けのサポートとヘルプ オプション](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-support-help-options) 」を参照して、ヘルプを受けるその他の方法について学習してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/faq"} -->
## Microsoft Entra エージェント IDについてよく寄せられる質問 (FAQ)

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/faq
- Service: entra-id / agent-id
- Article date: 2026-04-13
- Summary: Microsoft Entra エージェント IDとMicrosoft Entra エージェント ID プラットフォームに関する一般的な質問と回答。

Microsoft Entra エージェント ID は、Microsoft Entra 機能を AI エージェントに拡張する ID とセキュリティ フレームワークです。 組織が支援型、自律型、ユーザー型のエージェントをデプロイする場合、これらの非人間的な ID を認証、承認、管理、保護するための専用の ID コンストラクトが必要です。 Microsoft Entra エージェント ID は、エンタープライズ規模でエージェント ID を管理するための統合プラットフォームを提供することで、これらのニーズに対応します。

### エージェント ID とブループリント

#### エージェント ID のみを返すように Microsoft Graph API クエリをフィルター処理するにはどうすればよいですか?

Microsoft Graph `/ownedObjects`、`/deletedItems`、`/owners` などのエージェント ID を含むリレーションシップをサポートする API では、エンティティの種類によるフィルター処理はサポートされていません。 既存の API を使用し、 `odata.type` プロパティを使用してクライアント側で結果をフィルター処理して、応答内のエージェント ID オブジェクトを識別します。

#### エージェント ID またはブループリントが削除されると、エージェントのユーザー アカウントはどうなりますか?

エージェント ID ブループリントまたはエージェント ID が削除されると、関連付けられているエージェントのユーザー アカウントはテナントに残ります。 無効化や削除は表示されませんが、認証はできません。 Microsoft Graph API または Microsoft Entra PowerShell を使用して、孤立したエージェントのユーザー アカウントを手動で削除します。

#### エージェント ID オブジェクトを作成するときに、Microsoft の順次Graph API要求が失敗する理由

Microsoft Graph API を使用してエージェント ID オブジェクトを連続して作成すると、`400 Bad Request: Object with id {id} not found` などのエラーで要求が失敗する可能性があります。 この動作をトリガーする一般的なシーケンスは次のとおりです。

- エージェント ID ブループリントを作成した後、すぐにブループリント プリンシパルを作成します。
- ブループリント プリンシパルを作成した後、すぐにブループリントを使用してエージェント ID を作成します。
- エージェント ID を作成した後、すぐにエージェントのユーザー アカウントを作成します。

これらのエラーは、アプリ専用のアクセス許可を使用する場合に一般的です。 委任されたアクセス許可を可能な限り使用し、指数バックオフを含む再試行ロジックを要求に追加します。

#### テナントあたりのエージェント ID ブループリントの数に制限はありますか?

エージェント ID ブループリントには、次の制限が適用されます。

- アプリ専用のアクセス許可を使用するMicrosoft以外の管理プラットフォームは、ブループリントあたり 250 個のエージェント ID に制限されます。 委任された呼び出しとMicrosoft所有プラットフォーム (Foundry、Copilot Studio) は、この上限の対象になりません。
- 管理者以外のユーザーは、Microsoft Entra IDで既存の 250 個の所有オブジェクトの制限を受けます。これは、すべてのMicrosoft Entraリソースの種類に適用されます。
- ブループリントは、テナントの全体的なリソース クォータの 95% 以下を占めることができます。 詳細については、**Microsoft Entra サービスの制限事項**の[リソース](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions)行を参照してください。

#### 管理者がテナントでエージェント ID ブループリントを承認するタイミングを確認するにはどうすればよいですか?

エージェント ID ブループリントの承認のための組み込みの通知メカニズムはありません。 テナント管理者がエージェントのエージェント ID ブループリントを作成または承認した場合、Microsoft EntraまたはMicrosoft Graphを通じて通知されません。

ブループリントが特定のテナントで承認されているかどうかを確認するには、アプリケーションに関連付けられているブループリント プリンシパル オブジェクトについて Microsoft Graph APIにクエリを実行します。 管理者がまだブループリントを承認していない場合、クエリはそのテナントの結果を返しません。

#### Microsoft Entra 管理センター内のエージェント ID オブジェクトを削除または復元できますか?

No. エージェント ID オブジェクトの削除と復元は、Microsoft Entra 管理センターではサポートされていません。 代わりに、Microsoft Graph API または Microsoft Entra PowerShell を使用してください。

#### 完全に削除されたエージェント ID を回復できますか?

No. 完全に削除されたオブジェクトは復元できません。 論理的に削除されたオブジェクトは、30 日以内に復元できます。そのウィンドウの後、完全な削除は自動的に行われ、元に戻すことはできません。

#### エージェント ID ブループリント プリンシパルを完全に削除して、すぐにクォータを解放することはできますか?

No. エージェント ID ブループリント プリンシパルの完全な削除はブロックされます。 使用するクォータを解放するには、30 日間の保有期間が期限切れになるまで待ちます。

### ロール、アクセス許可、およびグループ

#### 管理単位にエージェント ID を追加できますか?

エージェント ID、エージェント ID ブループリント、およびエージェント ID ブループリント プリンシパルを管理単位に追加することはできません。 エージェント ID の `owners` プロパティを使用して、特定のオブジェクトを管理できるユーザーを制限します。

#### エージェントのユーザー アカウントの写真を更新できますか?

*エージェント ID 管理者*ロールには、エージェントのユーザー アカウントの写真を更新するアクセス許可がありません。 このタスクには *ユーザー管理者* ロールを使用します。

#### 動的グループを使用してエージェントのユーザー アカウントを管理できますか?

エージェントのユーザー アカウントは、動的グループを含むMicrosoft Entra グループに追加でき、それらのグループに付与されたアクセス権を継承できます。

#### エージェント ID に割り当てることができないMicrosoft Entraロールとグループはどれですか?

高い特権を持つディレクトリ ロールは、グローバル管理者、特権ロール管理者、ユーザー管理者などのエージェント ID に対してブロックされます。 割り当てることができるのは、閲覧者ロールなどの低い特権のロールのみです。 エージェント ID をロール割り当て可能なグループのメンバーにすることはできません。

### 認証と同意

#### エージェント ID はシングル サインオン (SSO) を使用して Web アプリにサインインできますか?

エージェント ID は、Microsoft Entra IDサインイン ページにサインインできません。つまり、OpenID Connect または SAML プロトコルでシングル サインオンを使用することはできません。 利用可能なウェブAPIを利用して、エージェントを職場のアプリやサービスと統合しましょう。

#### 管理者の同意ワークフローは、Microsoft Entra エージェント IDアクセス許可要求に対して機能しますか?

Microsoft Entra ID [admin 同意ワークフロー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-admin-consent-workflow)は、エージェント ID によって要求されたアクセス許可に対して正しく機能しません。 ユーザーは、Microsoft Entra テナント管理者に連絡して、エージェント ID に直接アクセス許可を付与するよう要求する必要があります。

#### ユーザーの同意がリスクベースのステップアップによってブロックされた場合はどうすればよいですか?

エージェント ID の同意フローには、リスクベースのステップアップが適用されます。 ユーザーの同意がブロックされている場合、回避策はありません。 ユーザーは、同意を続行する前にフラグ付きリスクを解決する必要があります。

#### 管理者は、エージェントの高い特権Microsoft Graphアクセス許可に同意できますか?

No. `Application.ReadWrite.All`、`RoleManagement.ReadWrite.All`、`User.ReadWrite.All`、`Directory.AccessAsUser.All`など、一連のリスクの高いMicrosoft Graphアクセス許可はエージェントに対してブロックされ、Microsoft GraphまたはMicrosoft Entra 管理センターを通じて付与することはできません。 これらのアクセス許可の要求は拒否されます。 エージェントは引き続き、ユーザーまたは管理者が同意する、より低い特権のスコープ付きアクセス許可を受け取ることができます。

### 監視とログ

#### 監査ログでエージェント ID アクティビティを識別するにはどうすればよいですか?

監査ログでは、既定では、エージェント ID と他のMicrosoft Entra ID の種類は区別されません。

- エージェント ID、ブループリント、ブループリント プリンシパルに対する操作は、 *ApplicationManagement* カテゴリに記録されます。
- エージェントのユーザー アカウントに対する操作は、 *ユーザー管理* カテゴリに記録されます。
- エージェント ID によって開始される操作は、サービス プリンシパルとして表示されます。
- エージェントのユーザー アカウントによって開始された操作は、ユーザーとして表示されます。

エージェント ID 関連のアクティビティを識別するには、監査ログのオブジェクト ID を使用してMicrosoft Graphクエリを実行し、エンティティの種類を決定します。 サインイン ログの関連付け ID を使用して、アクティビティに関係するアクターまたはサブジェクトの ID を特定することもできます。

#### Microsoft Graph アクティビティ ログでエージェント ID を識別するにはどうすればよいですか?

Microsoft Graphアクティビティ ログでは、現在、エージェント ID が他の ID の種類と分離されていません。

- エージェントのアイデンティティからのリクエストはアプリケーションとしてログされ、エージェントのアイデンティティは *appID* の列に含まれています。
- エージェントのユーザー アカウントからの要求は、 *UserID* 列のエージェント ユーザー ID を持つユーザーとしてログに記録されます。

Microsoft Entraサインイン ログと結合して、エンティティの種類を決定します。

### 開発リソース

#### Microsoft Entra エージェント IDシナリオで使用できる SDK またはライブラリはありますか?

使用する SDK は、シナリオによって異なります。

Microsoft Agent 365 CLI と SDK は、ほとんどの開発者に推奨される開始点です。 CLI は、エージェント ID のプロビジョニング、ブループリントの作成、アクセス許可の配線を 1 つのコマンドで処理します。 SDK は、実行時にトークンの取得を処理します。 詳細については、[Microsoft Entra Agent 365 SDK のドキュメント](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/overview)を参照してください。

Microsoft。Identity.Web には、.NET アプリケーションでエージェント ID のトークンを取得するための上位レベルの API が用意されています。 [Microsoft.Identity.Web.AgentIdentities](https://github.com/AzureAD/microsoft-identity-web/blob/master/src/Microsoft.Identity.Web.AgentIdentities/README.AgentIdentities.md) パッケージを使用して、エージェントIDの管理を簡素化します。

Microsoft Entra ID認証 SDK (サイドカー) は、アプリケーションと共に実行されるコンテナー化された Web サービスとして、Microsoft Entra認証とトークン管理機能を提供します。 エージェントがコンテナー化された環境 (Kubernetes、Docker、Azure コンテナー サービスなど) で実行されている場合、またはエージェントが.NETに組み込まれていないときに、言語に依存しない HTTP インターフェイスが必要な場合に使用します。 .NET アプリケーションの場合は、Microsoft。Identity.Web では、同等のインプロセス統合が提供されます。 詳細については、「[Microsoft Entra ID認証 SDK (サイドカー)](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/overview)」を参照してください。

Microsoft Graph API は、他のオプションがシナリオに合わない場合にエージェント ID 管理を提供します。 詳細については、「エージェント ID ブループリントの[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/agentidentityblueprint)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/grant-agent-access-microsoft-365"} -->
## エージェントに Microsoft 365 リソースへのアクセスを許可する

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/grant-agent-access-microsoft-365
- Service: entra-id / agent-id
- Article date: 2026-05-01
- Summary: Microsoft 365 リソースの同意、手動承認、およびその他の承認システムを通じてエージェントへのアクセス権を付与する方法について説明します。

この記事では、同意、手動承認、およびその他の承認システムを通じてエージェントへのアクセスを許可する方法に関するガイダンスを提供します。 エージェントが Microsoft 365 リソースにアクセスすることを承認するために使用できるさまざまな方法と、各アプローチを使用するタイミングについて説明します。

### 前提条件

- エージェント ID ブループリントと、そこから作成された少なくとも 1 つのエージェント ID。
- 有効なリダイレクト URI を持つエージェント ID ブループリント。

### 同意を要求する方法 (委任されたアクセス許可またはアプリケーションのアクセス許可)

ユーザーまたは管理者は、OAuth フロー中に API のアクセス許可に同意することで、エージェントにデータへのアクセス権を付与できます。 このセクションでは、エージェントの委任されたアクセス許可とアプリケーションアクセス許可の同意を要求する方法について説明します。

#### 委任されたアクセス許可またはアプリケーションのアクセス許可を使用する場合

要求するアクセス許可の種類は、エージェントの動作方法とアクセスする必要があるリソースによって異なります。

対話型エージェントがサインインしているユーザーの代わりに動作する必要がある場合は、委任されたアクセス許可を使用します。 たとえば、そのユーザーのメール、予定表、またはファイルを読み取ります。 委任されたアクセスは、トークンの scp 要求で実行されます。

自律エージェントがユーザーなしで実行され、アプリ専用アクセスが必要な場合は、アプリケーションのアクセス許可を使用します。 たとえば、すべてのユーザーのプロファイルを読み取ります。 アプリのアクセス許可は、トークンのロールクレームに表示されます。

詳細については、「[アクセス許可と同意の概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)」を参照してください

#### 同意のしくみ

委任されたアクセス許可については、ユーザーを Microsoft ID プラットフォーム `/authorize` エンドポイントにリダイレクトするときに、 `User.Read` や `Mail.Read`などのスコープを確認します。 同意が付与されると、Microsoft Entra ID は、エージェント (クライアント) から Microsoft Graph などのリソースに OAuth2PermissionGrant を記録します。 そのリソースに対する将来の委任トークンには、同意が変更されない限り、`scp` で承認されたスコープが含まれます。 アプリが管理者の制限付きアクセス許可に対する同意を要求すると、ユーザーにエラーが表示されます。 管理者は、これらのアクセス許可を直接要求します。 詳細については、「 [管理者が制限するアクセス許可」](https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc#admin-restricted-permissions)を参照してください。

#### 同意 URL の構築

同意 URL を作成するときは、クライアント ID がエージェント ID である必要があります。

詳細については、「 同意によるアクセス許可の要求」を参照してください。 一部のアクセス許可は、テナント内で付与する前に管理者の同意が必要です。 詳細については、[Microsoft ID プラットフォームでの管理者の同意に関するページを](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-admin-consent)参照してください

### 承認を手動で付与する方法 (委任またはアプリケーション)

基になる権限オブジェクトを直接作成できます。 これは、承認を自動化する必要がある場合や、対話型のプロンプトを回避したい場合に便利です。

- [委任されたアクセス許可を手動で付与します](https://learn.microsoft.com/ja-jp/graph/api/oauth2permissiongrant-post)。 クライアント ID をエージェント ID として設定します。
- [アプリケーションのアクセス許可を手動で付与する (アプリ ロールの割り当て)。](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-post-approleassignments) プリンシパル ID をエージェント ID として設定します。

### アクセス パッケージを使用してアクセスを管理する方法

アクセス パッケージを使用すると、同じアクセス ニーズを持つ多くの AI エージェントに対して標準化されたアクセスを有効にすることができます。 アクセス パッケージには、Entra ロール、OAuth2 の委任されたアクセス許可、アプリケーションアクセス許可の付与、およびセキュリティ グループ メンバーシップを含めることができます。 エージェントはアクセス パッケージを要求するか、スポンサーまたは管理者がアクセス パッケージを要求できます。承認されると、エージェント ID またはエージェントのユーザー アカウントは、割り当てが取り消されるか期限切れになるまでアクセス権を受け取ります。 詳細については、 [エージェント ID のアクセス パッケージを](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-access-packages)参照してください。

### その他の承認システム

エージェントは、アプリ ロールの割り当て、グループ メンバーシップ、OAuth2 アクセス許可の付与を超えて、いくつかの方法で承認できます。 これらの代替システムは、さまざまなプラットフォーム、サービス、およびセキュリティ要件に合わせて調整されたアクセスを割り当てるための柔軟性を提供します。 次のセクションでは、エージェントの承認に関する多くの方法をいくつかまとめます。

#### Azure ロールベースのアクセス制御 (Azure RBAC)

最も狭いスコープ (リソース、リソース グループ、サブスクリプション) でエージェント ID に Azure ロールを割り当てます。 たとえば、エージェントが広範なディレクトリまたはテナントのアクセス許可を必要とせずにシークレットを読み取ることができるように、1 つのコンテナーに Key Vault 閲覧者ロールを付与できます。 詳細と詳細なガイダンスについては、 [Azure RBAC のドキュメントを参照してください](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles)。

#### Microsoft Entra ロール

一部の低権限ディレクトリ ロールは、メタデータ操作または読み取りの場面でエージェントに割り当てられることがあります。 高い特権ロールは、プラットフォーム ポリシーによってエージェントに対してブロックされます。 Microsoft Entra ロールと PIM の詳細については、 [Microsoft Entra ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

#### Exchange ロールベースアクセス制御 (RBAC)

Exchange RBAC (Role-Based アクセス制御) を使用すると、管理者は Exchange リソースにアクセス許可を委任できます。 これにより、エージェントは、1 つのメールボックスまたはいくつかのメールボックスへの自律アクセスなど、きめ細かい承認を得ることができます。 詳細については、「[Exchange Online でのアプリケーションのロールベースのアクセス制御](https://learn.microsoft.com/ja-jp/exchange/permissions-exo/application-rbac)」を参照してください。

#### Teams リソース特定の同意 (RSC)

Teams Resource-Specific Consent (RSC) を使用すると、Microsoft Teams内のエージェントの詳細なアクセス許可の割り当てが可能になります。 RSC を使用すると、テナント レベルではなく、チームごとにアプリまたはエージェントにアクセス許可を付与できます。 この方法は、特定のチーム内のリソースとデータのみにアクセスを制限する場合に特に便利です。 このようなデータには、より広範な組織のアクセス許可を付与することなく、チャネル、メッセージ、または名簿情報が含まれます。 詳細については、「 [Teams アプリのリソース固有の同意」を参照してください](https://learn.microsoft.com/ja-jp/microsoftteams/platform/graph-api/rsc/resource-specific-consent)。

#### Microsoft 365 チャネル間のエージェント通信のアクセス許可

独自の ID (エージェント ID) を持つエージェントは、Outlook電子メールの送受信、OneDriveファイルとSharePoint ファイル内のコメントの送受信、Teams チャットやチャネルからのメッセージの送受信など、Microsoft 365サーフェス間で通信できます。 エージェントが通信する各サーフェスには、特定のアクセス許可が必要です。このアクセス許可は、エージェント ID ブループリントの必要なリソース アクセスで宣言し、エージェントがそこで何かを送受信する前にテナント管理者が同意する必要があります。

| Channel | 次のように表示されます | 受信 (チャネル → エージェント)イベントの受信 | 送信 (エージェント → チャネル)応答の送信 |
| --- | --- | --- | --- |
| `email` | 前途 | `Mail.Read` または `Mail.ReadWrite` | `Mail.Send` または `Mail.ReadWrite` |
| `spo.files.comments` | OneDrive と SharePoint | `Files.Read.All` または `Files.ReadWrite.All` | `Files.ReadWrite.All` |
| `teams-chat` | Teams チャット | `Chat.Read` または `Chat.ReadWrite` | `Chat.ReadWrite` または `ChatMessage.Send` |
| `teams-channel` | Teams チャネル | `ChannelMessage.Read.All` | `ChannelMessage.Send` |

#### カスタム (サード パーティ製) API

エージェントは、他の OAuth で保護された API を呼び出すことができます。 リソース アプリケーションとそのサービス プリンシパルがテナントに存在し、必要なスコープ/アプリ ロールを定義し、同じ委任されたフローまたはアプリアクセス許可フローに従っていることを確認します。 詳細については、Microsoft ID プラットフォームでのアクセス許可と同意に関する Microsoft のガイドを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/how-to-plan-agent-identity-architecture"} -->
## エージェント ID アーキテクチャを計画する - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/how-to-plan-agent-identity-architecture
- Service: entra-id / agent-id
- Article date: 2026-04-03
- Summary: この決定ガイドを使用して、Microsoft Entra エージェント ID で AI エージェントに適した ID の種類、操作パターン、ブループリントとエージェントの ID 構造を選択します。

AI エージェントを Microsoft Entra エージェント ID と統合する前に、一連の設計上の決定を行う必要があります。 このガイドでは、次の順序で各決定について説明します。

1. エージェント**に必要な ID の種類**。
2. トークンの取得方法や動作するコンテキストなど、エージェントが使用する**操作パターン**。
3. システム**に必要なブループリントの数**。
4. ブループリントごとに作成する**エージェント ID の数**。

以前の選択肢は後で形成されるため、これらの決定を順番に行います。 一部の単一エージェントデプロイでは、最初の 2 つの手順のみが必要な場合があります。 これらの決定が実際のエージェント アーキテクチャにどのようにマップされるかの例については、「 [エージェント ID の設計パターン」を](https://learn.microsoft.com/ja-jp/entra/agent-id/concept-agent-id-design-patterns)参照してください。

### 手順 1: ID の種類を選択する

Microsoft Entra ID には、AI エージェントで使用できる ID の種類がいくつか用意されています。 ほとんどの AI エージェントでは、 **エージェント ID** が適切な選択肢です。 エージェントが **ユーザー** オブジェクトを必要とするシステムにアクセスする必要がある場合は、エージェント ID に加えてエージェントのユーザー アカウントを構成します。 サービス プリンシパルと通常のユーザー アカウントは、AI エージェントには推奨されません。

| IDの種類 | 次の場合に使用... | 主な特性 |
| --- | --- | --- |
| **エージェント ID** | ...エージェントは、自身に代わって、またはユーザーの代理として機能します | 強制されたスポンサーシップ、個別の監査ログエントリー、ブループリントによって管理される資格情報 |
| **エージェントのユーザー アカウント** | ...エージェントは、Exchange メールボックスや Teams チャネルなどのユーザー オブジェクトを必要とするリソースにアクセスする必要があります | ユーザー アカウントは、エージェントの識別情報と1対1でペアになっており、ユーザー プリンシパル名 (UPN)、管理者、その他のユーザー プロパティを持つ |
| **サービス プリンシパル** (エージェント ワークロードには推奨されません) | ...ワークロードは、自律的な意思決定を行うことなく、スクリプト化された予測可能な操作を実行します | エージェント固有のガバナンス、透明性制御、またはライフサイクル管理を使用しないクラシック アプリケーション ID |

#### サービス プリンシパルではないのはなぜですか?

サービス プリンシパルは、確定的な静的ワークロード用に設計されています。 Microsoft Entra エージェント ID には、次のようなサービス プリンシパルでは使用できないエージェント固有の機能が用意されています。

- サインイン ログと監査ログに明示的なエントリを含む専用 ID の種類。汎用サービス プリンシパルでは実現が困難な追跡可能性と透明性を提供します。
- 特定の高い特権の承認に対するプラットフォーム レベルの制限。これにより、侵害されたエージェントの爆発半径が減少します。
- エージェント ID の作成時に、必ず責任あるビジネス所有者が割り当てられるように、スポンサーが強制されました。
- ブループリントで管理される資格情報とライフサイクル: エージェント ID の作成、ローテーション、削除は、個別ではなく、親ブループリントを通じて行います。
- 実行時に作成され、ブループリントを通じて既に継承可能なアクセス許可が付与され、タスク完了時に削除されるエフェメラルなエージェントIDのサポート。

詳細な比較については、 [エージェント ID、サービス プリンシパル、アプリケーションに関する記事を](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/agent-service-principals)参照してください。

#### 通常のユーザー アカウントではないのはなぜですか?

AI エージェントには通常の Microsoft Entra ユーザー アカウントを使用しないでください。 ユーザー アカウントは人間のサインイン パターン用に設計されており、エージェントに割り当てると、すべてのゼロ トラスト適用レイヤーで問題が発生します。

- 準拠しているデバイス要件、多要素認証、使用条件などの**条件付きアクセス** ポリシーは、エージェントに対して失敗します。これらのコントロールは人間の操作用に設計されているためです。
- **Microsoft Entra ID 保護** は、人間のサインイン動作に合わせて調整された機械学習を使用します。 ユーザー アカウントを介して AI エージェント トラフィックをルーティングすると、人間とエージェントの両方の検出が低下します。
- joiner-mover-leaver ワークフロー、アクセス パッケージ、アクセス レビューなどの **ID ガバナンス** プロセスは、エージェントのライフサイクル パターン用に設計されていないため、エージェント アクセスが誤って削除される可能性があります。
- エージェントは、人間の従業員と共にグローバル アドレス一覧、Teams、SharePoint に表示され、AI エージェントと人の区別が困難になります。

#### エージェントにアプリを登録しない

ワークロード ID の作成に慣れている場合は、たとえば、`az ad app create`、`New-MgApplication`、`New-AzADApplication`、`POST /applications` Microsoft Graph要求を実行するなどして、エージェントのアプリ登録またはサービス プリンシパルを作成することが本能になります。 AI エージェントに対しては、この操作を行わないでください。 この方法で作成された ID は標準アプリケーションです。スポンサーがなく、エージェント固有の監査エントリも、ブループリントで管理されたライフサイクルもありません。 Microsoft Entra はそれをエージェントとして管理しているわけではなく、「エージェント ID」とラベル付けしても、それがエージェント ID になるわけではありません。

代わりに、エージェント ID ブループリントを作成し、そこからエージェント ID を作成します。 エージェント ID は、アプリケーション登録 API ではなく、ブループリントを使用して作成された個別のMicrosoft Entra オブジェクトの種類 (`#Microsoft.Graph.AgentIdentity`) です。

| この代わりに (標準アプリの登録) | これを行う (エージェント ID) |
| --- | --- |
| `az ad app create` | [エージェント ID ブループリント](https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint)を作成し、そこからエージェント ID を作成する |
| `New-MgApplication` または `New-AzADApplication` | **エージェント ID 開発者ロールまたはエージェント ID** 管理者ロールでサポートされている[作成チャネル](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-id-creation-channels)**を**使用する |
| `POST https://graph.microsoft.com/v1.0/applications` | `AgentIdentityBlueprint.Create`付与してから、[ブループリントとエージェント ID を作成します](https://learn.microsoft.com/ja-jp/entra/agent-id/create-delete-agent-identities) |

.NETでは、`builder.Services.AddAgentIdentities()`を呼び出し、`WithAgentIdentity(...)`でトークンを取得する`Microsoft.Identity.Web.AgentIdentities` パッケージを使用します。 [エージェントからのカスタム API の呼び出しを](https://learn.microsoft.com/ja-jp/entra/agent-id/call-api-custom)参照してください。

完全な比較については、「 [エージェント ID、サービス プリンシパル、アプリケーション」を](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-service-principals)参照してください。

### 手順 2: 操作パターンを選択する

エージェントの操作パターンによって、トークンを取得する方法と、トークンが動作するコンテキストが決まります。 Microsoft Entra エージェント ID では、自律型と対話型の 2 つの主要なパターンがサポートされています。

| 特徴 | 自主的な | インタラクティブ |
| --- | --- | --- |
| **ユーザー コンテキスト** | ユーザーが存在しない | ユーザーがサインインしている |
| **権限の種類** | アプリケーションのアクセス許可 | 委任権限 |
| **同意モデル** | 管理者の同意が必要 | ユーザーまたは管理者の同意 |
| **トークンのサブジェクト** | エージェント識別子 | ユーザー (エージェントをアクターとして使用) |
| **一般的なシナリオ** | バックグラウンド処理、スケジュールされたタスク、システム間 | チャット アシスタント、ユーザー向けの副操縦士、ユーザー データに作用するエージェント |
| **アクセス スコープ** | テナント全体の広範なアクセス | サインインしているユーザーのデータにスコープが設定されている |

エージェントが次の場合、**自律的**を選択します。

- バックグラウンド タスクまたはスケジュールされたタスクを実行します。
- 複数のユーザー間でデータを処理します。
- ユーザーが存在せずにシステム間操作を実行します。

エージェントが次の場合、**対話型** を選択します。

- サインインしているユーザーの代わりに動作します。
- メール、予定表、ファイルなど、そのユーザーのデータにアクセスする必要があります。
- ユーザー自身のアクセス許可の境界を尊重する必要があります。

一部のエージェントには両方が必要です。 たとえば、エージェントは自律パターンを使用して夜間のバックグラウンド同期を実行し、対話型パターンを使用してユーザー チャット メッセージにも応答する場合があります。 この場合は、両方の OAuth フローを実装し、操作に基づいて適切なトークンを選択します。

- 自律エージェントについては、「 [自律エージェントのエージェント トークンを要求する」を](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/autonomous-agent-request-tokens)参照してください。
- 対話型エージェントについては、「 [対話型エージェントでのユーザーの認証](https://learn.microsoft.com/ja-jp/entra/agent-id/interactive-agent-authentication-authorization-flow)」を参照してください。

### 手順 3: エージェント ID ブループリントの数を決定する

エージェント ID ブループリントは、そこから作成されたすべてのエージェント ID の認証を管理します。 ブループリントは、これらのエージェント ID に代わってトークンを取得するために使用される資格情報を保持します。 この機能により、ブループリントの侵害は、その下のすべてのエージェント ID に影響を与える可能性があります。 セキュリティ境界に基づいて、エージェント ID ブループリントの数を選択します。

**既定値: 信頼境界ごとに 1 つのブループリントを使用します。**

信頼境界は、1 つの侵害が境界全体に影響すると見なされる共有リスク サーフェスです。 ランタイム、シークレット、ファイル システム、およびネットワークを共有するエージェントは、信頼境界を共有し、ブループリントを共有できます。

| 決定要因 | 同じ信頼境界→1つのブループリント | 複数のブループリント→異なる信頼境界 |
| --- | --- | --- |
| **認証資料** | すべてのエージェントで同じ資格情報を共有できます。資格情報のローテーションまたは侵害がすべてのエージェントに一緒に影響を与える | 資格情報は暗号化で分離する必要があります。資格情報の侵害をピア エージェントに分散してはならない |
| **セキュリティ境界** | エージェントは、同じ信頼境界 (同じランタイム、シークレット、ファイル システム、ネットワーク) で実行されます | エージェントが信頼ドメインの境界を越える: 別々の環境、ランタイム、または分離ドメイン |

次の要因は、ブループリントを追加する理由 *ではありません* 。

- **認証のブロック**: ブループリントを追加せずに、1 つのエージェント ID を無効にしたり、条件付きアクセス ポリシーでターゲットにしたりできます。
- **監査の分離**: 各エージェント ID は、親ブループリントの下に独自のサインインと監査ログ エントリを生成します。
- **スケールアウトまたはレプリカ**: 同じエージェントの複数のインスタンスを実行する場合、複数のブループリントは必要ありません。
- **メモリまたはコンテキストの分離**: エージェント メモリは通常、取得時にセッション ID でフィルター処理された共有データ ストアであり、個別のブループリントは必要ありません。

ブループリントの詳細については、「 [エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/agent-blueprint) ブループリント」を参照してください。

### 手順 4: ブループリントごとにエージェント ID の数を決定する

**既定値: 論理エージェントごとに 1 つのエージェント ID を使用します。** エージェントごとに個別の ID を使用すると、監査証跡、トレース、およびアクセス制御の最も忠実度が高くなります。

| 決定要因 | 複数のエージェント ID | 単一エージェントアイデンティティ |
| --- | --- | --- |
| **監査と帰属** | 調査、コンプライアンス、またはアカウンタビリティのために、アクションは特定のエージェントに起因する必要があります | "the system" として行動することで十分です。どのエージェントが行動したかを区別する必要はありません。 |
| **ライフサイクルの独立性** | エージェントは個別に作成、削除、または無効化される | エージェントが 1 つのユニットとして作成、削除、および管理される |
| **役割の分離** | エージェントには個別の責任があります。 たとえば、在庫検索、製品比較、仕入先データなどです。 | エージェントは交換可能 |

次の要因は、エージェント ID を追加する理由 *ではありません* 。

- **水平方向のスケールアウト**: 同じエージェント コードのレプリカまたはインスタンスを実行する場合、個別の ID は必要ありません。 スケールアウトは実行時の問題であり、ID の問題ではありません。
- **メモリ プールとコンテキスト管理**: エージェント メモリは、通常、取得時にセッション ID でフィルター処理された共有データ ストアです。 メモリまたはコンテキストの分離には、個別の ID は必要ありません。

複数のエージェント ID が適切なマルチエージェント アーキテクチャには、個別のエージェント ロールを持つシーケンシャル パイプラインと、特殊なドメイン ワーカーとの同時オーケストレーションを含めることができます。 詳細については、「 [AI エージェントのオーケストレーション パターン](https://learn.microsoft.com/ja-jp/azure/architecture/ai-ml/guide/ai-agent-design-patterns)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/how-to-validate-agent-tokens-downstream-api"} -->
## ダウンストリーム API でエージェント ID トークンを検証する - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/how-to-validate-agent-tokens-downstream-api
- Service: entra-id / agent-id
- Article date: 2026-04-28
- Summary: 署名、発行者、対象ユーザー、およびエージェント ID マーカー要求を確認して、ダウンストリーム API のMicrosoft Entra エージェント ID トークンを検証する方法について説明します。

AI エージェントが Microsoft Entra ID 認証 SDK (サイドカー) を介して API を呼び出すと、要求には `Bearer` トークンが含まれます。 API は、このトークンを検証して、要求が正しいアクセス許可を持つ認証済みエージェントから送信されたことを確認します。 検証に失敗した場合、API は理由で HTTP 401 を返します。

この記事では、検証チェックについて説明し、エージェント ID トークンをエンドツーエンドで検証するサンプル Weather API を構成して実行する方法について説明します。

### 前提条件

- **Docker Desktop** (macOS/Windows) または **Docker Engine** (Linux)。
- エージェント ID ブループリントとエージェント ID を含む **Microsoft Entra テナント**。 セットアップ手順については、「 [エージェント ブループリントの作成](https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint) 」および「 [エージェント ID の作成と削除」を](https://learn.microsoft.com/ja-jp/entra/agent-id/create-delete-agent-identities)参照してください。
- テスト用のエージェント ID トークン。 [サイドカーローカル開発サンプル](https://learn.microsoft.com/ja-jp/entra/agent-id/sidecar-local-development)または[Microsoft Entra エージェント IDサンプルリポジトリ](https://github.com/microsoft/entra-agentid-samples)のスクリプトから取得できます。

### トークンの検証チェック

ダウンストリーム API は、すべての受信エージェント ID トークンに対して 4 つのチェックを実行する必要があります。

| チェック | 検証内容 | 詳細情報 |
| --- | --- | --- |
| **署名** | トークンは改ざんされません。 | にある JSON Web キー セット (JWKS) に対して RS256 署名を確認します。 |
| **発行者** | Microsoft Entra IDトークンを発行しました。 | `iss`要求は、`https://sts.windows.net/<tenant>/`または`https://login.microsoftonline.com/<tenant>/v2.0`と一致します。 |
| **オーディエンス** | トークンは API を対象としています。 | `aud`要求は、API の予想される対象ユーザーの値と一致します。 |
| **エージェント ID マーカー** | トークンは、通常のアプリではなくエージェントに発行されました。 | `xms_par_app_azp`要求はエージェント ID トークンに存在し、標準のアプリ専用トークンには存在しません。 この要求は、エージェントを作成したブループリントを識別します。 |

4 つのチェックがすべて成功すると、API は要求を信頼して処理できます。

### サンプル天気 API のしくみ

[Microsoft Entra エージェント ID サンプル リポジトリ](https://github.com/microsoft/entra-agentid-samples)には、これらの検証チェックを示すサンプル天気 API が含まれています。 サンプル API は、エージェントが呼び出すダウンストリーム API として機能する最小限の Flask アプリです。 これは、受信エージェント ID トークンを検証し、 [Open-Meteo](https://open-meteo.com) から実際の気象データを返します。

[ローカル開発 (Ollama)](https://learn.microsoft.com/ja-jp/entra/agent-id/sidecar-local-development) と AWS (Bedrock) の両方のサイドカー サンプルは、同じ Weather API コンテナーを呼び出します。 このサンプルは、次の 3 つのファイルで構成されています。

- **`app.py`:** ルート ハンドラー、トークン検証ロジック、Open-Meteo クライアントを含む Flask アプリ。
- **`Dockerfile`:** 基本イメージとして `python:3.13-slim` を使用します。 ポート 8080 で `gunicorn app:app` を実行します。
- **`requirements.txt`: 依存関係:**`flask`、 `pyjwt[crypto]`、 `cryptography`、 `requests`、 `gunicorn`。

API は、次の 2 つのエンドポイントを公開します。

- **`GET /weather?city=<name>`:**`Authorization: Bearer <token>` ヘッダーを検証し、指定した都市の気象データを返します。
- **`GET /healthz`:** トークンの検証を必要とせずに正常性状態を返します。

次の図は、トークンがエージェントからサイドカーを経由して weather API に流れる方法を示しています。 エージェントが直接Microsoft Entra IDに接触することはありません。 代わりに、サイドカーはエージェント ID に代わってトークン (TR) を取得し、エージェントはそのトークンを `Authorization: Bearer` ヘッダーの weather API に渡します。

[Image: エージェントの呼び出し元がベアラー トークンを天気APIに送信し、APIがトークンを検証してOpen-Meteoを呼び出す過程を示す図。]

エージェント ID トークン TR は、サイドカーを介してMicrosoft Entra IDによって発行されます。 これには、 `xms_par_app_azp` エージェント ID マーカーを含め、API が検証する要求が含まれます。 フロー内のすべてのトークンの詳細な内訳については、「 [ローカル開発用にサイドカーを実行する」](https://learn.microsoft.com/ja-jp/entra/agent-id/sidecar-local-development#understand-the-token-flow)を参照してください。

トークン検証ライブラリは、エコシステム (Python、Node.js、.NET) によって異なります。 このサンプルでは、RS256 署名検証のために、 `cryptography` バックエンドで PyJWT を使用します。 独自のダウンストリーム API を構築するときは、テクノロジ スタックに対応する JWT 検証ライブラリを選択します。

### サンプル天気 API を構成する

サンプルの weather API は、次の環境変数を受け入れます。

| Variable | 必須 | Default | Purpose |
| --- | --- | --- | --- |
| `TENANT_ID` | Yes | — | Microsoft Entra テナント ID は、JWKS URL の構築および発行者クレームの検証に使用されます。 |
| `EXPECTED_AUDIENCE` | No | `https://graph.microsoft.com` | 予期される `aud` クレーム値。 同じエージェント トークンがローカル テストで機能するように、既定値は Microsoft Graph です。 |
| `PORT` | No | `8080` | API がリッスンする HTTP ポート。 |

### サンプル天気 API を実行する

Weather API をスタンドアロン コンテナーとして実行し、トークンの検証をテストするには:

1. リポジトリを複製し、weather API ディレクトリに移動します。

    ```bash
    git clone https://github.com/microsoft/entra-agentid-samples.git
    cd entra-agentid-samples/sidecar/weather-api
    ```
2. コンテナーをビルドして実行します。

    ```bash
    docker build -t weather-api:local .
    docker run --rm -p 8080:8080 \
      -e TENANT_ID=<your-tenant-id> \
      -e EXPECTED_AUDIENCE=https://graph.microsoft.com \
      weather-api:local
    ```
3. エージェント ID トークンを使用して要求を送信します。

    ```bash
    curl -H "Authorization: Bearer $TOKEN" \
         "http://localhost:8080/weather?city=Dallas"
    ```

API は、気象データとトークン検証結果の両方を含む JSON 応答を返します。

```json
{
  "city": "Dallas",
  "temperature": 61,
  "temperature_unit": "F",
  "condition": "Overcast",
  "humidity": 93,
  "wind_speed": 8,
  "is_agent_identity": true,
  "agent_app_id": "<agent-app-id from xms_par_app_azp>",
  "validated_by": "Agent Identity Token",
  "data_source": "Open-Meteo API (Real-time)"
}
```

`is_agent_identity`、`agent_app_id`、および`validated_by`フィールドは、トークンの検証を確認します。

- **`is_agent_identity`:**`true`要求が存在する場合に`xms_par_app_azp`に設定します。これは、トークンが標準のアプリ登録ではなくエージェント ID に発行されたことを確認します。
- **`agent_app_id`:** エージェント ID を作成したブループリント アプリケーションを識別する `xms_par_app_azp` 要求の値。
- **`validated_by`:** トークンに適用される検証メソッド。 エージェント マーカー宣言が存在する場合、`Agent Identity Token` を表示します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/howto-delete-agent-identity"} -->
## エージェント ID オブジェクトの削除と復元 - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/howto-delete-agent-identity
- Service: entra-id / agent-id
- Article date: 2026-04-29
- Summary: Microsoft Entraでエージェント ID ブループリントを削除し、論理的に削除されたエージェント ID オブジェクトを復元する方法について説明します。

エージェント ID ブループリントを削除すると、Microsoft Entra関連付けられているすべての子エージェント ID とエージェントのユーザー アカウントが自動的にクリーンアップされます。 すべての削除は論理的な削除です。削除されたオブジェクトはごみ箱に移動し、30 日以内に復元できます。

カスケード クリーンアップのしくみとクォータに関する考慮事項の概念の概要については、 [エージェント ID の削除のしくみに関するページを](https://learn.microsoft.com/ja-jp/entra/agent-id/concept-agent-identity-deletion)参照してください。

### 前提条件

エージェント ID オブジェクトを削除および復元するには、次のものが必要です。

- [エージェント ID オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-administrator) を表示および管理するためのエージェント ID 管理者。
- ブループリント アプリケーションとそのサービス プリンシパルを削除する[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)。
- Microsoft Graph API または Microsoft Entra PowerShell 操作の `Application.ReadWrite.All` アクセス許可。
- 論理的に削除されたエージェント ID を完全に削除するための `AgentIdentity.ReadWrite.All` アクセス許可。
- エージェント ID ブループリントの所有者は、これらのロールなしで、そのブループリントに関連付けられているエージェント ID オブジェクトを削除できます。

### ブループリントを削除する

エージェント ID オブジェクトの削除は、Microsoft Entra 管理センターではサポートされていません。 Microsoft Graph API または powerShell Microsoft Entraを使用して、ブループリントとエージェント ID を削除します。

エージェントアイデンティティブループリントとブループリントプリンシパルは別々に削除できます。 アプリの登録を削除すると、プリンシパルも削除されます。 プリンシパルのみを削除すると、アプリの登録はそのまま残ります。

Note

標準削除は常に論理的な削除です。 オブジェクトはごみ箱に移動されますが、すぐには削除されません。 完全削除は 30 日後に自動的に行われます。または、「アプリケーションの削除 [と回復](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-recover-faq)に関する FAQ」で説明されている標準的なハード削除プロセスを使用して強制できます。

## [Microsoft Graph API](#tab/microsoft-graph-api)
ブループリント アプリケーションを削除するには、次の手順を実行してください（これによりプリンシパルも削除されます）。

```http
DELETE https://graph.microsoft.com/v1.0/applications/{blueprint-app-object-id}
```

ブループリントのプリンシパルのみを削除するには、次の手順に従ってください。

```http
DELETE https://graph.microsoft.com/v1.0/servicePrincipals/{blueprint-principal-object-id}
```

Note

ブループリント プリンシパル (`permanentDelete`) の明示的なハード削除がブロックされます。 上記の標準の削除エンドポイントを使用します。

## [Microsoft Entra PowerShell](#tab/microsoft-entra-powershell)
ブループリント アプリケーション (これによりプリンシパルも削除されます) を削除するには、以下の手順に従ってください。

```powershell
Connect-Entra -Scopes 'Application.ReadWrite.All'
Remove-EntraApplication -ObjectId <blueprint-app-object-id>
```

ブループリントのプリンシパルのみを削除するには、次の手順に従ってください。

```powershell
Connect-Entra -Scopes 'Application.ReadWrite.All'
Remove-EntraServicePrincipal -ObjectId <blueprint-principal-object-id>
```

---

ブループリントまたはそのプリンシパルを削除すると、Microsoft Entra は関連付けられているすべての子エージェントの ID とエージェントのユーザーアカウントを自動的にソフト削除します。 このプロセスの詳細については、「 [エージェント ID の削除」を](https://learn.microsoft.com/ja-jp/entra/agent-id/concept-agent-identity-deletion)参照してください。

重要

カスケード クリーンアップを実行する前にブループリント プリンシパルを復元しても、子エージェント ID は影響を受けません。 クリーンアップの実行後、各子 ID を個別に復元する必要があります。 ブループリント プリンシパルを復元しても、既に発生した連鎖削除は元に戻りません。

### ブループリント プリンシパルを復元する

論理的に削除されたブループリント プリンシパルは、30 日以内に復元できます。 エージェント ID オブジェクトの復元は、Microsoft Entra 管理センターではサポートされていません。 Microsoft Graph API または Microsoft Entra PowerShell を使用します。

## [Microsoft Graph API](#tab/microsoft-graph-api)
```http
POST https://graph.microsoft.com/v1.0/directory/deletedItems/{blueprint-principal-object-id}/restore
```

## [Microsoft Entra PowerShell](#tab/microsoft-entra-powershell)
```powershell
Connect-Entra -Scopes 'Application.ReadWrite.All'
Restore-EntraDeletedDirectoryObject -Id <blueprint-principal-object-id>
```

---

### 子エージェント ID を復元する

連鎖クリーンアップが既に実行されていて、子エージェント ID が論理的に削除されている場合は、それぞれを個別に復元します。

Note

Microsoft Graph `/directory/deletedItems` エンドポイントは、エージェント ID の種類によるフィルター処理をサポートしていません。 既知のオブジェクト ID、アプリ ID、または表示名を使用して、削除されたサービス プリンシパルのクエリを実行し、クライアント側で結果をフィルター処理して、正しいエージェント ID を識別します。

## [Microsoft Graph API](#tab/microsoft-graph-api)
1. 論理的に削除されたサービス プリンシパルを一覧表示して、影響を受けるエージェント ID を見つけます。

    ```http
    GET https://graph.microsoft.com/v1.0/directory/deletedItems/microsoft.graph.servicePrincipal
    ```
2. クライアント側の結果をフィルター処理して、ブループリントにリンクされているエージェント ID を特定し、それぞれを復元します。

    ```http
    POST https://graph.microsoft.com/v1.0/directory/deletedItems/{agent-identity-object-id}/restore
    ```

## [Microsoft Entra PowerShell](#tab/microsoft-entra-powershell)
```powershell
Connect-Entra -Scopes 'Application.ReadWrite.All'

# List deleted service principals and identify agent identities
Get-EntraDeletedServicePrincipal

# Restore each agent identity individually
Restore-EntraDeletedDirectoryObject -Id <agent-identity-object-id>
```

---

### エージェント ID オブジェクトを完全に削除する

論理的に削除されたオブジェクトは、完全に削除されるまで [ディレクトリ クォータ](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions) にカウントされ続けます。 アプリ専用のアクセス許可を使用してブループリントのエージェント ID の上限が 250 に達している場合は、30 日間の保持期間の有効期限が切れるのを待つのではなく、すぐにクォータを解放するために完全な削除を強制する必要がある場合があります。 エージェント ID ブループリント プリンシパルの完全な削除はブロックされます。 ブループリント プリンシパルによって使用されるクォータを完全に解放するには、30 日間の保有期間が期限切れになるまで待ちます。

Caution

完全に削除されたオブジェクトは復元できません。 オブジェクトが不要になったと確信している場合にのみ、オブジェクトを完全に削除します。

## [Microsoft Graph API](#tab/microsoft-graph-api)
論理的に削除されたエージェント ID を完全に削除します。

```http
DELETE https://graph.microsoft.com/v1.0/directory/deletedItems/{agent-identity-object-id}
```

論理的に削除されたブループリント アプリケーションを完全に削除します。

```http
DELETE https://graph.microsoft.com/v1.0/directory/deletedItems/{blueprint-app-object-id}
```

## [Microsoft Entra PowerShell](#tab/microsoft-entra-powershell)
一時的に削除されたエージェントID を完全に削除します。

```powershell
Connect-Entra -Scopes 'AgentIdentity.ReadWrite.All'
Remove-EntraDeletedDirectoryObject -DirectoryObjectId <agent-identity-object-id>
```

論理的に削除されたブループリント アプリケーションを完全に削除します。

```powershell
Connect-Entra -Scopes 'Application.ReadWrite.All'
Remove-EntraDeletedDirectoryObject -DirectoryObjectId <blueprint-app-object-id>
```

---

### エージェントのユーザー アカウント

エージェントのユーザー アカウントは、エージェント ID と 1 対 1 でペアリングされます。 エージェントのユーザー アカウントが連鎖削除の一部として自動的にクリーンアップされない場合は、手動で削除します。

## [Microsoft Graph API](#tab/microsoft-graph-api)
```http
DELETE https://graph.microsoft.com/v1.0/users/{agent-user-object-id}
```

## [Microsoft Entra PowerShell](#tab/microsoft-entra-powershell)
```powershell
Connect-Entra -Scopes 'User.ReadWrite.All'
Remove-EntraUser -UserId <agent-user-object-id>
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/integrate-aws-bedrock-agent"} -->
## Microsoft Entra エージェント IDを使用して Amazon Bedrock エージェントをセキュリティで保護する - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/integrate-aws-bedrock-agent
- Service: entra-id / agent-id
- Article date: 2026-04-30
- Summary: Microsoft Entra ID認証 SDK (サイドカー) を使用して、ダウンストリーム API を呼び出すための独自の ID で Amazon Bedrock AI エージェントをセキュリティで保護する方法について説明します。

このガイドでは、Microsoft Entra ID認証 SDK (サイドカー) を使用してダウンストリーム API に対する認証を行って[、Amazon Bedrock](https://aws.amazon.com/bedrock/) エージェントをセキュリティで保護する方法について説明します。 サイドカーは別のコンテナーとして実行され、Microsoft Entra IDを使用してすべての資格情報の管理とトークン交換を処理します。 エージェントがサイドカーに承認ヘッダーを要求し、サイドカーが OAuth 2.0 の交換を Microsoft Entra ID で処理します。

### 前提条件

開始する前に、以下の項目があることを確認します:

- Microsoft Entra のテナント。
- Azure サブスクリプション。
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (macOS/Windows) または Docker Engine with Compose v2 (Linux)。
- [PowerShell 7 以降](https://learn.microsoft.com/ja-jp/powershell/scripting/install/installing-powershell)。
- [Azure CLI](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli)。
- [AWS CLI v2](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)。
- Anthropic Claude 3 Haiku (またはお好みのモデル) に対して Bedrock モデル アクセスが有効になっている AWS アカウント。 AWS Bedrock コンソールの**モデル アクセス**&gt;内の**管理モデルアクセス**で、アクセスを有効にします。
- Microsoft Entra の初回セットアップにおける**Global Administrator** ロール。 [Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) を使用して、このロールをジャスト イン タイムでアクティブ化します。

### サンプル リポジトリを複製する

1. リポジトリを複製し、AWS サンプルディレクトリに移動します。

    ```bash
    git clone https://github.com/microsoft/entra-agentid-samples.git
    cd entra-agentid-samples/sidecar/aws
    ```

### アーキテクチャ

Microsoft Entra ID認証 SDK (サイドカー) は、エージェントとMicrosoft Entra IDの間に配置されます。 エージェントが直接Microsoft Entra IDと通信したり、資格情報を管理したりすることはありません。 サイドカーに、ダウンストリーム API を呼び出す `Authorization` ヘッダーを要求します。 Amazon Bedrock は、ID について心配することなく、LLM 推論を個別に処理します。

[Image: Bedrock エージェント、サイドカー、Microsoft Entra ID、Weather API の間のトークン フローを示す図。]

このサンプルでは、Docker ブリッジ ネットワーク上で次の 3 つのコンテナーを実行します。

- **`llm-agent-aws`:** チャット UI と、推論のために Amazon Bedrock (Claude) を呼び出す LangGraph ReAct エージェントを備えた Flask アプリ。 ポート 3001 で公開されます。
- **`agent-id-sidecar-aws`:** 公式の Microsoft Entra ID 認証 SDK (サイドカー) コンテナー。 トークンを取得してキャッシュします。 ホスト ポートなし。Docker ネットワーク内からのみ到達可能です。
- **`weather-api-aws`:** すべての要求でエージェントの JWT (署名、発行者、有効期限、対象ユーザー) を検証し、気象データを返すダウンストリーム API。

要求は次のステップを通過します。

1. `http://localhost:3001`のチャット UI にクエリを入力します。
2. Flask アプリは、LangGraph ReAct エージェントを介して AWS Bedrock (Claude) にクエリを送信します。
3. Claude は気象データが必要だと判断すると、 `get_weather` ツールを呼び出します。
4. このツールは、 `GET /AuthorizationHeader?AgentIdentity={agentId}`を呼び出してサイドカーに承認ヘッダーを要求します。
5. サイドカーは、OAuth 2.0 (クライアント資格情報または on-behalf-of (OBO) 交換) を使用して Microsoft Entra ID に認証します。
6. Microsoft Entra IDは、要求されたトークン (TR) をサイドカーに返します。
7. エージェントは、 `Authorization: Bearer TR`を使用して weather API を呼び出します。
8. weather API は TR を検証し、天気 JSON 応答を返します。

#### トークン フローを理解する

ID 交換には、次の 3 つのトークンが関係します。

| トークン | 発行先 | の場合 | どのように |
| --- | --- | --- | --- |
| **Tc** | サインインしているユーザー | OBO フローのみ | ブラウザーで MSAL.js する |
| **T1** | ブループリント アプリ | 両方のフロー | サイドカー (クライアント資格情報) |
| **TR** | エージェント (ダウンストリーム API) | 両方のフロー | サイドカーアプリのみ（自律型）または OBO 交換 |

自律フローでは、サイドカーはクライアント資格情報を使用して T1 を取得し、ダウンストリーム API にスコープされた TR と交換します。 OBO フローでは、サイドカーも Tc (ユーザーのトークン) を受け取り、OBO 交換を実行して、サインインしているユーザーの代わりに動作する TR を取得します。

このセットアップでは、チャット UI (ポート 3001) のみがホストに公開されます。 サイドカーと気象 API は Docker ネットワーク内でのみ到達可能であり、明確なセキュリティ境界が確立されます。

### 実行モードと ID フローを選択する

このサンプルでは、2 つの実行モードと、組み合わせることができる 2 つの ID フローがサポートされています。

| - | **自律型**(アプリのみ) | **OBO**(ユーザーの代理) |
| --- | --- | --- |
| **Direct** (LLM なし) | 高速デモ手順。 トークンがフェッチされ、気象 API が直接呼び出されます。 | 同じですが、認証されたサイドカー エンドポイントをユーザー トークンと共に使用します。 |
| **Bedrock + LangChain** | LangGraph ReAct エージェントは、 `get_weather`を呼び出すタイミングを決定します。 | 同じですが、エージェントはツールの実行時にユーザー トークンを渡します。 |

**直接**モードを使用して、AWS Bedrock アクセスを必要とせずに、トークン フローをエンドツーエンドで確認します。 エージェントエクスペリエンス全体を実現するために **Bedrock** モードに切り替えます。

### AWS 認証レベルを選択する

このサンプルでは、Amazon Bedrock に対して認証する 3 つの方法がサポートされています。 環境に一致するレベルを選択します。

- **一時的な STS 資格情報:** AWS SSO を使用したローカル開発に最適です。 `AWS_ACCESS_KEY_ID` ファイルで`AWS_SECRET_ACCESS_KEY`、`AWS_SESSION_TOKEN`、および`.env`を設定します。 これらの資格情報は、約 1 時間後に有効期限が切れます。
- **Bedrock API キー:** デモやワークショップに最適です。 `AWS_BEARER_TOKEN_BEDROCK` ファイルに`.env`を設定します。 Bedrock のみにスコープされ、構成可能な有効期間があります。
- **OIDC federation:** Azure App Service での運用環境のデプロイに最適です。 プラットフォームによって設定された `AWS_ROLE_ARN` と `AWS_WEB_IDENTITY_TOKEN_FILE` を使用します。 シークレットはどこにも保存されません。

ヒント

運用環境のデプロイについては、[Azure App Service デプロイ ガイド](https://github.com/microsoft/entra-agentid-samples/blob/dev/sidecar/aws/DEPLOY-AZURE-APP-SERVICE.md)を参照して、Azureと AWS の間に格納されているシークレットがゼロの OIDC フェデレーションを設定する手順について詳しく説明します。

### Bedrock モデルを選択する

このサンプルは、Bedrock で最も安価なAnthropic モデルであり、ツール呼び出しをサポートしているため、既定では `us.anthropic.claude-3-haiku-20240307-v1:0` になります。 `us.` プレフィックスは、米国リージョン間をルーティングして可用性を高めるリージョン間推論プロファイルを示します。

サポートされているその他のモデル:

| モデル ID | 1K 入力トークンあたりのコスト | Notes |
| --- | --- | --- |
| `us.anthropic.claude-3-haiku-20240307-v1:0` | $0.00025 | 既定値。 高速、最も安い、ツールの呼び出しをサポートしています。 |
| `us.anthropic.claude-3-5-haiku-20241022-v1:0` | $0.0008 | \*\* より新しく、よりスマートで、手頃な価格のまま。 |
| `us.anthropic.claude-3-5-sonnet-20241022-v2:0` | $0.003 | 最高の品質/コスト比。 |

`BEDROCK_MODEL_ID` ファイルで`.env`を設定して、既定値をオーバーライドします。 呼び出す前に、 **AWS Bedrock コンソール**&gt;**Model アクセス** で各モデルを有効にする必要があります。

### Microsoft Entra オブジェクトを作成する (初回セットアップ)

前の実行の `.env` ファイルに既に `BLUEPRINT_APP_ID` が設定されている場合は、「 環境変数の構成」に進みます。

テナントごとに次のコマンドを 1 回実行して、OBO サインインに使用されるブループリント アプリ、エージェント ID、SPA アプリを作成します。

1. 「 [エージェント ID ブループリント](https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint) の作成」および「エージェント ID の作成」の PowerShell ワークフローに従って、自律フローのブループリント アプリと [エージェント ID を作成します](https://learn.microsoft.com/ja-jp/entra/agent-id/create-delete-agent-identities)。 最後に、次の情報が表示されます。

    - **`TENANT_ID`:** お使いの Microsoft Entra テナント。
    - **`BLUEPRINT_APP_ID`:** ブループリント アプリの登録。
    - **`BLUEPRINT_CLIENT_SECRET`:** ブループリントのクライアント シークレット。
    - **`AGENT_CLIENT_ID`:** ブループリントから作成されたエージェント ID。
2. (省略可能)SPA アプリを作成し、OBO を構成します。 この手順は、OBO ID フローを使用する場合にのみ必要です。

    次のスクリプトを実行して SPA アプリの登録を作成し、ブループリントで OBO アクセス許可を構成します。 スクリプトは SPA リダイレクト URI を登録し、必要な委任されたアクセス許可を付与します。

    **Bash：**

    ```bash
    bash ../../scripts/setup-obo-client-app.sh
    bash ../../scripts/setup-obo-blueprint.sh
    ```

    **PowerShell**:

    ```powershell
    pwsh ../../scripts/setup-obo-client-app.ps1
    pwsh ../../scripts/setup-obo-blueprint.ps1 `
        -TenantId        '<TENANT_ID>' `
        -BlueprintAppId  '<BLUEPRINT_APP_ID>' `
        -AgentAppId      '<AGENT_CLIENT_ID>' `
        -ClientSpaAppId  '<CLIENT_SPA_APP_ID>'
    ```

このサンプルの SPA リダイレクト URI は `http://localhost:3001` です (ポート 3001、3003 ではありません)。 この URI が登録されていることを確認します。

### 環境変数を構成する

サイドカーは、`AzureAd__ClientCredentials__0__SourceType`の`docker-compose.yml`設定を使用して、複数の資格情報の種類をサポートします。

- **`ClientSecret`:** ローカル開発のみ。 サンプルには、この型が含まれています。
- **`SignedAssertionFromManagedIdentity`:** Azure にデプロイされます。 ゼロ シークレット。運用環境に推奨されます。
- **`KeyVault`:** Azure Key Vaultからの証明書。
- **`StoreWithThumbprint`:** ローカル コンピューター ストアからの証明書。

1. 含まれているテンプレートからローカル `.env` 構成ファイルを作成します。 このファイルには、テナント、アプリ、AWS の資格情報が格納されます。

**Bash：**

```bash
cp .env.example .env
```

**PowerShell**:

```powershell
Copy-Item .env.example .env
```

1. `.env` ファイルに次の変数を設定します。

    - **`TENANT_ID`:** Microsoft Entra テナント ID。
    - **`BLUEPRINT_APP_ID`:** ブループリント アプリの登録。 サイドカーはこのアプリとして認証を行います。
    - **`BLUEPRINT_CLIENT_SECRET`:** ブループリント クライアント シークレット (ローカル開発のみ)。
    - **`AGENT_CLIENT_ID`:** エージェント ID。 `AgentIdentity` クエリ パラメーターとして表示されます。
    - **`CLIENT_SPA_APP_ID`:** MSAL.js がブラウザーのサインインに使用する SPA アプリ ID (OBO のみ)。
    - **`AWS_REGION`:**`us-east-2` などの Bedrock の AWS リージョン。
    - **`BEDROCK_MODEL_ID`:** モデル ID。 既定値: `us.anthropic.claude-3-haiku-20240307-v1:0`。
    - **`VALIDATE_TOKEN_SIGNATURE`:** 既定の `true`。 weather API で JWKS 署名の検証をスキップするには、 `false` に設定します (デバッグのみ)。
2. 選択したレベルに基づいて AWS 資格情報を追加します。

    - **階層 A (STS):**`AWS_ACCESS_KEY_ID`、`AWS_SECRET_ACCESS_KEY`、および`AWS_SESSION_TOKEN`を設定します。
    - **レベル B (API キー):**`AWS_BEARER_TOKEN_BEDROCK`を設定します。
    - **レベル C (OIDC):**`.env`ではなく、プラットフォームのアプリ設定を通じて構成されます。

ヒント

以前のセッションの `.env` が既にある場合は、AWS の資格情報を更新するだけで済みます (STS トークンは約 1 時間後に期限切れになります)。 直接スキップして スタックを開始します。

### スタックを開始する

1. Docker Desktop (または Docker エンジン) がコンピューター上で実行されていることを確認します。
2. コンテナー イメージをビルドし、デタッチ モードで 3 つのサービス (エージェント、サイドカー、天気 API) をすべて開始します。

    ```bash
    docker compose up --build -d
    ```
3. 状態エンドポイントのクエリを実行して、すべてのコンテナーが正常に開始されたことを確認します。 応答は、エージェントが AWS Bedrock に到達できるかどうかを報告します。

    **Bash：**

    ```bash
    curl http://localhost:3001/api/status
    ```

    **PowerShell**:

    ```powershell
    Invoke-RestMethod http://localhost:3001/api/status
    ```

    `bedrock_available: true`を示す応答が表示されます (AWS 資格情報なしで Direct モードを使用している場合は`false`)。

Important

`.env`を更新する場合 (たとえば、期限切れの STS 資格情報を更新する場合)、`docker compose restart`環境変数は再読み込みされません。 `docker compose up -d --force-recreate llm-agent-aws` を代わりに使用します。

### チャット UI を使用してクエリを送信する

1. ブラウザーで `http://localhost:3001` を開きます。
2. ヘッダー バーを使用してデモを構成します。

    - **実行モード:**`Direct` (LLM をスキップ) または `Bedrock` (Claude の LangChain ReAct エージェント)。
    - **ID フロー:**`Autonomous` (アプリ専用トークン) または `OBO` (サインインしているユーザーに対して機能します)。
3. **OBO** を選択した場合は、MSAL.js ポップアップで **[サインイン**] を選択して認証します。
4. *"Weather in Dallas?"* のようなクエリを入力し、[**送信**] を選択します。
5. 右側の **[ID トレース** ] パネルで、すべてのトークン交換と API 呼び出しの詳細な内訳を確認します。 パネルには、デコードされた要求を含む各トークン (Tc、T1、TR) の色分けされた JWT カードが表示されます。

### 一般的な問題のトラブルシューティング

想定どおりに動作しない場合は、次の表で一般的な問題と修正プログラムを確認してください。

| 現象 | 考えられる原因 | 固定 |
| --- | --- | --- |
| `/api/status` を表示 `bedrock_available: false` | AWS の資格情報が見つからないか期限切れであるか、モデルアクセスが許可されていません。 | `docker logs llm-agent-aws` を確認します。 `aws sso login`を使用して STS 資格情報を更新します。 Bedrock コンソールでモデルを有効にします。 |
| `ExpiredTokenException` Bedrock から | STS セッション トークン (階層 A) の有効期限が切れています。 | 新しい資格情報を `.env`に貼り付け、 `docker compose up -d --force-recreate llm-agent-aws`実行します。 |
| `AccessDeniedException` の `InvokeModel` | IAM プリンシパルに `bedrock:InvokeModel` アクセス許可がない、またはモデルアクセスが有効になっていません。 | モデルと推論プロファイルの ARN に `bedrock:InvokeModel` を付与します。 |
| `ValidationException: invalid model identifier` | リージョンでモデルがホストされていないか、 `us.` 推論プロファイルの代わりにベア モデル ID を使用しました。 | `us.`プレフィックス付きの推論プロファイル ID (たとえば、`us.anthropic.claude-3-haiku-20240307-v1:0`) を使用します。 |
| Weather API が返す `401 Unauthorized` | トークン テナントの不一致、期限切れのシークレット、または署名の確認に失敗しました。 | `TENANT_ID`がブループリントのテナントと一致するかどうかを確認します。 サイドカー ログを確認します。 |
| ツールを呼び出さずに LLM が応答する | クエリがツールの形を明確にしていなかったか、モデルがツール呼び出しをサポートしていません。 | Claude 3 Haiku 以降を使用します。 要求を *" &lt;市外の天気&gt;?"* と表現します。 |
| OBO サインイン ポップアップがブロックされました | ブラウザー ポップアップ ブロック。 | `localhost:3001`のポップアップを許可します。 |
| OBO 中にサイドカーからの `4xx` | `CLIENT_SPA_APP_ID` が見つからないか、SPA リダイレクト URI が一致しません。 | `setup-obo-client-app`を再実行します。 `http://localhost:3001` が SPA のリダイレクト URI にあることを確認します。 |

トラブルシューティングの手順に従っても問題が解決しない場合は、コンテナー ログを直接調べます。 各サービスは、独自のコンテナーにログを記録します。

```bash
docker logs llm-agent-aws          # Agent app: Bedrock calls, tool invocations
docker logs agent-id-sidecar-aws   # Sidecar: token acquisition, credential errors
docker logs weather-api-aws        # Weather API: JWT validation, request handling
```

### リソースをクリーンアップする

テストが完了したら、ローカル コンテナーを停止してシステム リソースを解放します。 再起動を高速化するためにイメージを保持するかどうかに基づいて、次のいずれかのクリーンアップ オプションを選択します。

Important

`docker compose down` では、ローカル Docker コンテナーのみが削除されます。 Microsoft Entra オブジェクト (エージェント ブループリント、エージェント ID、SPA アプリの登録) はテナント側の状態であり、保持されます。 不要になった場合は、Microsoft Entra 管理センターで手動で削除します。

```bash
# Stop containers but keep volumes and images for faster restarts
docker compose down

# Remove everything including volumes and images
docker compose down -v --rmi all
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/integrate-n8n-agent"} -->
## Microsoft Entra エージェント IDを使用して n8n エージェントをセキュリティで保護する - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/integrate-n8n-agent
- Service: entra-id / agent-id
- Article date: 2026-06-15
- Summary: Microsoft Entra エージェント IDと Microsoft Graph MCP Server for Enterprise を使用して、Azure Container Appsおよびセキュリティで保護された AI エージェント ワークフローに n8n をデプロイします。

このガイドでは、Microsoft Entra エージェント IDの統合を行った[n8n](https://n8n.io/)をAzure Container Appsにデプロイする方法について説明します。 デプロイでは、Azure Developer CLI (`azd`) を使用してインフラストラクチャをプロビジョニングし、Microsoft Entra ID オブジェクトを作成し、n8n ワークフローを自動的に構成します。

カスタム エージェント[に使用される Microsoft Entra ID Auth SDK (サイドカー) による認証](https://learn.microsoft.com/ja-jp/entra/agent-id/authentication-with-auth-sdk-sidecar)パターンとは異なり、n8n 統合では [n8n-nodes-entraagentid](https://www.npmjs.com/package/@astaykov/n8n-nodes-entraagentid) コミュニティ ノードを使用して、n8n ワークフロー内でトークンの取得を直接管理します。 デプロイされたワークフローは、自律型 (アプリ専用) と代理 (OBO) トークン フローの両方を示しており、Microsoft Graphと Microsoft Graph MCP Server for Enterprise、`https://mcp.svc.cloud.microsoft/enterprise` にアクセスできます。

メモ

このサンプルでは、n8n 内の `n8n-nodes-entraagentid` コミュニティ ノードの使用を示します。 運用環境のAzureに n8n をデプロイするためのガイダンスではありません。

### 前提条件

開始する前に、以下の項目があることを確認します:

- Azure OpenAI (GPT-4o など)、PostgreSQL フレキシブル サーバー、およびAzure Container Appsのクォータを持つAzure サブスクリプション。
- Microsoft Entra テナントの **グローバル管理者** ロール。 自動化によって複数のMicrosoft Entra オブジェクトが作成され、アクセス許可に対する管理者の同意が付与されるため、このロールが必要です。 [Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) を使用して、このロールをジャスト イン タイムでアクティブ化します。

**Azure Cloud Shell** (推奨) には、Azure CLI、Azure Developer CLI (`azd`)、PowerShell 7、Git がすべてプレインストールされています。

Cloud Shellではなくローカルで実行している場合は、続行する前に次のツールをインストールしてください。

- [Azure Developer CLI (`azd`)](https://learn.microsoft.com/ja-jp/azure/developer/azure-developer-cli/install-azd) v1.9 以降。
- [Azure CLI (`az`)](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli) v2.60 以降。
- [PowerShell 7.4 以降](https://learn.microsoft.com/ja-jp/powershell/scripting/install/installing-powershell)。
- [Microsoft。Entra PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/entra-powershell/) v1.2 以降。
- Git。

デプロイ コマンドでサブスクリプション内のリソースを作成および管理できるように、Azure CLIと Azure Developer CLI の両方を認証します。

```bash
az login
azd auth login
```

### 複製とデプロイ

デプロイ全体は、Azureインフラストラクチャをプロビジョニングし、n8n を自動的に構成する 1 つの `azd up` コマンドを使用して実行されます。 n8n をデプロイするには、次の手順に従います。

1. [Azure Cloud Shell](https://shell.azure.com) を開き、**PowerShell** を選択します。
2. リポジトリを複製し、デプロイを開始します。

    ```bash
    git clone https://github.com/astaykov/n8n-aca.git && cd n8n-aca && azd auth login && azd up
    ```

    Azure Cloud Shellでは、`azd auth login` はデバイス コードを表示します。 表示された URL を開き、認証するコードを入力し、 `azd up` 自動的に続行します。
3. メッセージが表示されたら、次の値を指定します。

    - **環境名:** 任意の名前 (たとえば、 `my-n8n`)。 このデプロイを分離するために使用されます。
    - **Azure subscription:** デプロイするサブスクリプションを選択します。
    - **Azure location:** リージョンを選択します (例: `northeurope`)。
    - **n8n 管理者の電子メール:** n8n 所有者アカウントの電子メール。
    - **n8n 管理者パスワード:** n8n 所有者アカウントのパスワード (最小 8 文字、大文字と小文字の混在、数字)。
4. プロビジョニング後のフェーズでは、自動化によって 2 回目のサインインが実行されます。 デバイス コードが表示されます。 URL を開き、コードを入力します。 この手順には、全体管理者またはアプリケーション管理者ロールが必要です。 次に、`azd up` ポストプロビジョニング フック:

    - Microsoft Entra エージェント ID オブジェクト (ブループリント、エージェント ID、エージェント ユーザー) を作成します。
    - Microsoft Graph MCP Server for Enterprise を有効にします。
    - n8n の準備が整うのを待ちます。
    - 所有者アカウントを作成します。
    - `@astaykov/n8n-nodes-entraagentid` コミュニティ ノードをインストールします。
    - n8n オートメーション用の API キーを生成します。
    - 5 つの資格情報を実際の値で作成します。
    - 3 つのデモ ワークフローをインポートしてアクティブ化します。

デプロイが完了すると、スクリプトによって n8n URL と構成内容の概要が出力されます。 テナント ID はAzureサインインから自動検出されるため、手動で構成する必要はありません。

### デプロイされたリソースを調べる

デプロイでは、Azureリソース、Microsoft Entra ID オブジェクト、およびサンプル ワークフローをサポートするために連携する n8n 構成資産が作成されます。

#### Azure インフラストラクチャ リソースを確認する

デプロイでは、次の Azure リソースが作成されます。

- **Container Apps 環境:** n8n とテスト用 SPA をホストします。
- **n8n Container App:** HTTPS イングレスを使用して公式の `n8nio/n8n` イメージを実行します。
- **静的 Web アプリ:** OBO Webhook フローの SPA をテストします。
- **PostgreSQL フレキシブル サーバー:** ワークフロー、資格情報、実行履歴の永続ストア (バースト可能 B1ms)。
- **ストレージ アカウントとファイル共有:** 永続的な `/home/node/.n8n` ディレクトリ。 コミュニティ ノードと構成は再起動後も存続します。
- **Azure AI エージェント ワークフローによって使用される OpenAI:** GPT モデルのデプロイ。
- **Log Analytics Workspace:** 診断と監視。

#### Microsoft Entra ID オブジェクトを確認する

自動化では、これらのオブジェクトが 1 回作成され、後続の実行時に再利用されます。

- **エージェント ID ブループリント:** フェデレーション ID 資格情報を介してエージェント ID に代わってトークンを発行するアプリの登録。
- **エージェント ID サービス プリンシパル:** AI エージェントのサービス プリンシパル。 Microsoft Graphトークンと MCP トークンを自律的に取得します。
- **エージェント ユーザー アカウント:** 委任された (OBO) トークン フローを有効にするクラウド専用ユーザー ID。
- **シングル ページ アプリ (SPA) アプリの登録:** リダイレクト URI と Blueprint API のアクセス許可で事前構成された Webhook デモ用のクライアント アプリ。

#### n8n 資格情報とワークフローを確認する

プロビジョニング後フックは、n8n を自動的に構成します。

**作成された資格情報:**

- **EntraAgentID - Autonomous:** アプリ専用の Microsoft Graph API トークン (ユーザー コンテキストなし)。
- **EntraAgentID - エージェント ユーザー OBO:** エージェント ユーザーに代わって委任されたトークン。
- **Azure OpenAI:** AI エージェント ワークフロー用にデプロイされた GPT モデルへの接続。
- **AgentID Auth Manager - アクセス トークン:** 認証マネージャーからダウンストリーム ノードへのトークン転送。
- **AuthManager のベアラー:** MCP 呼び出しのトークン転送用ベアラー。

**インポートされたワークフロー:**

- **エージェント ID Auth Manager - MCP Enterprise のエージェント ユーザー:** エージェント ユーザーの委任された MCP トークンを取得し、サブワークフローに転送します。
- **HTTP 要求と自律エージェント トークン:** アプリ専用トークンを使用してMicrosoft Graphを直接呼び出す自律エージェントを示します。
- **Webhook - 対話型エージェント (代理):** SPA からベアラー トークンを受信し、認証マネージャーを呼び出し、サインインしているユーザーの代わりに Graph MCP サーバー経由で応答する Webhook エントリ ポイント。

#### トークン フローについて

n8n デプロイでは、次の 2 つのトークン フロー パターンがサポートされています。

- **自律 (アプリのみ):** n8n ワークフローでは、エージェント ID ブループリント資格情報（Federated Identity Credentials を使用）を利用して、エージェント ID サービス プリンシパルのアプリ専用トークンを取得します。 その後、ワークフローはこのトークンを使用してMicrosoft Graphを直接呼び出します。 ユーザー コンテキストは関係しません。
- **MCP を使用した On-Behalf-of (OBO):** ブラウザー ベースの SPA は、ベアラー トークンを n8n webhook に送信します。 Webhook は Auth Manager ワークフローを呼び出します。このワークフローでは、Blueprint 資格情報を使用して、エージェント ユーザーに代わって委任されたトークンを取得します。 Auth Manager は、Microsoft Graph MCP Server for Enterprise を呼び出すサブワークフローにトークンを転送します。このサブワークフローでは、MCP ツールの呼び出しが委任されたトークンを使用して Microsoft Graph API 要求に変換されます。

どちらのパターンでも、エージェント ID ブループリントはトークン ファクトリとして機能します。 エージェント ID ブループリントは、エージェント自体に資格情報を格納せずに、エージェント ID のトークンを発行します。 Auth Manager コミュニティ ノードは、各ワークフロー実行内でトークンの取得と AES-256-GCM キャッシュを処理します。

### テスト SPA をデプロイする (省略可能)

テスト SPA は、ブラウザーからの OBO Webhook フローを示す静的 JavaScript アプリです。

1. 最初のプロビジョニングが完了した後にデプロイします。

    ```bash
    azd deploy spa
    ```

### MCP サーバーのスコープについて

このセットアップでは、次の委任された `MCP.*` スコープがエージェント ID サービス プリンシパルに付与されます。 これらのスコープは、対応するMicrosoft Graphを反映します (たとえば、`MCP.User.Read.All` は `User.Read.All` に対応します)。

- `MCP.User.Read.All`: すべてのユーザーを読み取ります。
- `MCP.Organization.Read.All`: テナント組織の情報を読み取る。
- `MCP.Group.Read.All`: すべてのグループを読み取る。
- `MCP.GroupMember.Read.All`: グループ メンバーシップを読み取ります。
- `MCP.Application.Read.All`: アプリの登録とサービス プリンシパルを読み取ります。
- `MCP.AuditLog.Read.All`: サインイン ログと監査ログを読み取ります。
- `MCP.Reports.Read.All`: Microsoft 365 の使用状況レポートを読み取る。
- `MCP.Policy.Read.All`: 条件付きアクセス ポリシーを読み取ります。
- `MCP.Domain.Read.All`: 検証済みドメインを読み取ります。
- `MCP.Device.Read.All`: Microsoft Entra登録済みデバイスを読み取る。

さらにスコープを追加するには、`$MCP_SCOPES`で`scripts/Setup-EntraAgentId.ps1`配列を編集し、`azd provision`再実行します。

メモ

MCP サーバーは、委任されたアクセス許可フローのみをサポートします。 アプリ専用のMicrosoft Graph呼び出しには自律資格情報を使用します。

### 展開を再実行して更新する

デプロイは完全に同一化された操作です。

- Bicepは、既に存在Azureリソースをスキップします。
- `azd` 環境では、最初の実行後Microsoft Entraオブジェクト ID (ブループリント、エージェント ID、エージェント ユーザー、ブループリント シークレット) が保存され、後続の実行時に再利用されます。
- n8n 構成 (資格情報、ワークフロー) は、実行ごとに新しく適用され、破損した状態を修復できます。

インフラストラクチャを変更せずにプロビジョニング後のスクリプトだけを再実行するには、プロビジョニングをもう一度実行します。 Bicep テンプレートは、インフラストラクチャの変更を検出せず、デプロイ フックのみを実行します。

```bash
azd provision   # Bicep detects no changes, runs hooks only
```

### スクリプトを手動で実行する (省略可能)

必要に応じて、構成スクリプトを個別に実行できます。

- **完全なエンド ツー エンド (Microsoft Entra と n8n):**

    ```powershell
    .\scripts\Run-All.ps1 `
        -TenantId  "<your-tenant-id>" `
        -N8nUrl    "https://ca-n8n-<token>.<region>.azurecontainerapps.io"
    ```
- **n8n 構成のみ (Entra のセットアップをスキップ):**

    ```powershell
    .\scripts\Configure-N8n.ps1 `
        -N8nUrl          "https://ca-n8n-<token>.<region>.azurecontainerapps.io" `
        -OwnerEmail      "admin@contoso.com" `
        -OwnerPassword   "MyStr0ngPassword!"
    ```
- **Entra のセットアップのみ:**

    ```powershell
    .\scripts\Setup-EntraAgentId.ps1 `
        -TenantId  "<your-tenant-id>" `
        -N8nUrl    "https://ca-n8n-<token>.<region>.azurecontainerapps.io"
    ```

### リソースをクリーンアップする

デプロイによって作成されたすべてのAzure リソースを削除し、保持されているデプロイ状態を消去します。

```bash
azd down --purge
```

メモ

`azd down` コマンドはAzureリソースを削除しますが、ブループリント、エージェント ID、エージェント ユーザー アカウントなどのMicrosoft Entraオブジェクトは削除しません。 不要になった場合は、Microsoft Entra 管理センターでこれらのオブジェクトを手動で削除します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/interactive-agent-authentication-authorization-flow"} -->
## ユーザーを認証し、対話型エージェントのトークンを取得する - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/interactive-agent-authentication-authorization-flow
- Service: entra-id / agent-id
- Article date: 2026-06-15
- Summary: ユーザーを認証し、承認を構成し、対話型エージェントがユーザーに代わってリソースにアクセスするための On-Behalf-Of フローを実装する方法について説明します。

対話型エージェントは、ユーザーに代わってアクションを実行します。 エージェントは、ユーザーに代わって安全に操作するために、ユーザーを認証し、必要なアクセス許可に対する同意を取得し、ダウンストリーム API のアクセス トークンを取得します。 この記事では、対話型エージェントのエンド ツー エンド認証とトークン取得フローについて説明します。

1. 継承可能なアクセス許可または同意を通じてアクセス許可を付与します。
2. ユーザーを認証し、アクセス トークンを取得します。
3. トークンを検証し、ユーザー要求を抽出します。
4. On-Behalf-Of (OBO) フローを使用してダウンストリーム API のトークンを取得します。

注

この記事では、OBO フローを使用してサインインしているユーザー **に代わって** 動作する対話型エージェントについて説明します。 エージェントに独自のユーザーのような ID (デジタル ワーカー シナリオ) が必要な場合は、 [エージェントのユーザー アカウント](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-users) と [エージェントのユーザー アカウントの OAuth フロー](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-user-oauth-flow)を参照してください。

### 前提条件

始める前に、以下のことを確認してください:

- [エージェント ID ブループリント](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-blueprint)。 エージェント ID ブループリント アプリ ID (クライアント ID) を記録します。
- [エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/create-delete-agent-identities)。
- ユーザー認証を処理するためにMicrosoft Entraに登録されているクライアント アプリケーション。
- [OAuth 2.0 承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)に関する知識。
- この記事のトークン検証と OBO サンプルを使用する予定の場合は、ASP.NET Core Web API を実行する機能。

管理者の承認には、次のものが必要です。

- [管理者アクセス](https://learn.microsoft.com/ja-jp/entra/agent-id/grant-agent-access-microsoft-365)を使用してアプリケーションのアクセス許可に同意します。

### アクセス許可と同意

エージェントがユーザーの代わりに動作できるようにするには、ユーザーまたは管理者が必要なアクセス許可に同意する必要があります。 アクセス許可を付与するには、次の 2 つの方法があります。

- **継承可能なアクセス許可**: エージェント ID が自動的に継承されるように、ブループリントのアクセス許可を事前認証します。
- **同意を要求**する: リダイレクト URI を登録し、OAuth 要求を通じて同意を付与するか、管理者の同意エンドポイントを使用するようにユーザーまたは管理者に求めます。

#### 継承可能なアクセス許可を使用する

委任されたスコープとアプリケーション ロールの基本セットを事前に認証するように、エージェント ID ブループリントに継承可能なアクセス許可を構成します。 ブループリントから作成されたエージェント ID は、対話型の同意プロンプトなしでこれらのアクセス許可を自動的に継承します。 詳細については、「 [エージェント ID ブループリントの継承可能なアクセス許可を構成する」を](https://learn.microsoft.com/ja-jp/entra/agent-id/configure-inheritable-permissions-blueprints)参照してください。

#### 同意を要求

OAuth フローを通じて同意を要求するには、まず、エージェント ID ブループリントをリダイレクト URI で構成する必要があります。 ブループリントの場合、リダイレクト URI は **Web アプリケーション** の種類である必要があります。 アプリ登録のリダイレクト URI とは異なり、ブループリントのリダイレクト URI を使用して委任されたアクセス許可トークンを取得することはできません。 OAuth2 要求では `response_type=none` のみがサポートされています。つまり、要求レコードは同意のみであり、トークンは返されません。

##### リダイレクト URI を登録する

## [マイクロソフト グラフ API](#tab/microsoft-graph-api)
エージェント ID ブループリントのリダイレクト URI を更新するには、まず、委任されたアクセス許可 `AgentIdentityBlueprint.ReadWrite.All`を持つアクセス トークンを取得する必要があります。 次に、エージェント ID ブループリントのアプリケーション オブジェクトに PATCH 要求を送信します。

```http
PATCH https://graph.microsoft.com/beta/applications/<agent-blueprint-id>
OData-Version: 4.0
Content-Type: application/json
Authorization: Bearer <token>

{
  "web": {
    "redirectUris": [
      "https://myagentapp.com/authorize"
    ]
  }
}
```

## [Microsoft Graph PowerShell](#tab/microsoft-graph-powershell)
Microsoft Graph PowerShell を使用して、エージェント ID ブループリントをリダイレクト URI で更新します。

```powershell
Connect-MgGraph -Scopes "AgentIdentityBlueprint.ReadWrite.All" -TenantId <your-tenant-id>

$applicationId = "<agent-blueprint-id>"
$web = @{
    redirectUris= @(
        "https://myagentapp.com/authorize"
    )
}

$body = @{
    web   =  $web
}

Invoke-MgGraphRequest -Method PATCH `
        -Uri "https://graph.microsoft.com/beta/applications/$applicationId" `
        -Headers @{ "OData-Version" = "4.0" } `
        -Body ($body | ConvertTo-Json)
```

---

##### ユーザーの同意を要求する

エージェントがユーザーの代わりに動作する前に、ユーザーは必要なアクセス許可に同意する必要があります。 ユーザーの同意要求はトークンを返しません。 代わりに、ユーザーがエージェントに代わって動作するアクセス許可を付与したことを記録します。 トークンの取得は、 ユーザーの認証とトークンの要求で行われます。

Important

**エージェント ID** ブループリント ID ではなく、`client_id` パラメーターでエージェント ID クライアント ID を使用します。

ユーザーに同意を求めるメッセージを表示するには、承認 URL を作成し、その URL にユーザーをリダイレクトします。 エージェントは、チャット メッセージ内のリンクなど、さまざまな方法でこの URL を表示できます。

```text
https://login.microsoftonline.com/contoso.onmicrosoft.com/oauth2/v2.0/authorize?
  client_id=<agent-identity-id>
  &response_type=none
  &redirect_uri=https%3A%2F%2Fmyagentapp.com%2Fauthorize
  &response_mode=query
  &scope=User.Read
  &state=xyz123
```

ユーザーがこの URL を開くと、Microsoft Entra IDはサインインして同意を求めるメッセージを表示します。 同意すると、ユーザーはリダイレクト URI に送り返されます。

ユーザーの同意承認 URL の主要なパラメーターは次のとおりです。

- `client_id`: エージェント ID クライアント ID (エージェント ID ブループリント クライアント ID ではありません)。
- `response_type`: この要求では同意のみが記録されるため、 `none` に設定されます。 トークンの取得では、「ユーザーの`response_type=code`のを使用します。
- `redirect_uri`: エージェント ID ブループリントで構成されたリダイレクト URI と正確に一致する必要があります。
- `scope`: 必要な委任されたアクセス許可 (たとえば、 `User.Read`) を指定します。
- `state`: 要求とコールバックの間の状態を維持するための省略可能なパラメーター。

OAuth 承認の概念の詳細については、Microsoft ID プラットフォーム の Permissions と同意に関するページを参照してください。

##### すべてのユーザーの管理者の同意を要求する

エージェントは、テナント内のすべてのユーザーに対してエージェントに同意を付与できる、Microsoft Entra ID管理者に承認を要求することもできます。 テナントで構成されている同意設定によっては、管理者の同意が必要になる場合があります。

テナント全体の管理者の同意を付与するには、管理者に次の URL を指示します。 `client_id` パラメーターでエージェント ID を使用します。

```text
https://login.microsoftonline.com/contoso.onmicrosoft.com/v2.0/adminconsent
?client_id=<agent-identity-id>
&scope=User.Read
&redirect_uri=<redirect-uri>
&state=xyz123
```

管理者が同意を付与すると、アクセス許可はテナント全体に適用されます。 ユーザーはもう一度同意する必要はありません。

注

ブループリントでリダイレクト URI を構成し、同意要求に `state` パラメーターを含めます。 同意が付与されると、確認を表示できるリダイレクト URI にユーザーが送信されます。 エンドポイントでは、 `state` パラメーターを使用して、そのアクセス許可が付与されたことを追跡できます。 シングルテナント エージェントの場合は、テナント ID が既にわかっているため、同意が付与されるまでトークン要求を再試行することもできます。

### ユーザーを認証し、トークンを要求する

同意が付与されると、クライアント アプリ (フロントエンドやモバイル アプリなど) が OAuth 2.0 承認コード要求を開始して、対象ユーザーがエージェント ID ブループリントであるトークンを取得します。 この手順では、 `client_id` は、エージェント ID またはエージェント ID ブループリントではなく、クライアント アプリ独自の登録済みアプリケーション ID を参照します。

注

この要求の `redirect_uri` は、前の同意手順で構成されたブループリントのリダイレクト URI ではなく、 **クライアント アプリ** の登録に属しています。

1. 次のパラメーターを使用して、ユーザーを Microsoft Entra ID 承認エンドポイントにリダイレクトします。

    ```http
    GET https://login.microsoftonline.com/<your-tenant-id>/oauth2/v2.0/authorize?client_id=<client-app-id>
    &response_type=code
    &redirect_uri=<redirect_uri>
    &response_mode=query
    &scope=api://<agent-blueprint-id>/access_agent
    &state=abc123
    ```
2. ユーザーがサインインすると、アプリはリダイレクト URI で承認コードを受け取ります。 アクセス トークンの承認コードをExchangeします。

    ```http
    POST https://login.microsoftonline.com/<your-tenant-id>/oauth2/v2.0/token
    Content-Type: application/x-www-form-urlencoded
    
    client_id=<client-app-id>
    &grant_type=authorization_code
    &code=<authorization_code>
    &redirect_uri=<redirect_uri>
    &scope=api://<agent-blueprint-id>/access_agent
    &client_secret=<client-secret>
    ```

    機密クライアントを使用している場合にのみ、 `client_secret` パラメーターを含めます。

    JSON 応答には、エージェントの API へのアクセスに使用できるアクセス トークンが含まれています。

### アクセス トークンを検証する

Web API は、エージェントが動作する前に受信アクセス トークンを検証する必要があります。承認されたライブラリを常に使用してトークンを検証します。 独自のトークン検証コードを記述しないでください。

1. `Microsoft.Identity.Web` NuGet パッケージをインストールします。

    ```bash
    dotnet add package Microsoft.Identity.Web
    ```
2. ASP.NET Core Web API プロジェクトで、Microsoft Entra ID認証を実装します。

    ```csharp
    // Program.cs
    using Microsoft.AspNetCore.Authentication.JwtBearer;
    using Microsoft.Identity.Web;
    
    var builder = WebApplication.CreateBuilder(args);
    
    builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
        .AddMicrosoftIdentityWebApi(builder.Configuration.GetSection("AzureAd"));
    
    var app = builder.Build();
    
    app.UseAuthentication();
    app.UseAuthorization();
    ```
3. `appsettings.json` ファイルで認証資格情報を構成します。

    Warnung

    セキュリティ リスクのために、エージェント ID ブループリントの運用環境では、クライアント シークレットをクライアント資格情報として使用しないでください。 代わりに、マネージド ID またはクライアント証明書を使用 [するフェデレーション ID 資格情報 (FIC)](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-config-app-trust-managed-identity) などのより安全な認証方法を使用してください。 これらの方法により、アプリケーション構成内に機密性の高いシークレットを直接格納する必要がなくなり、セキュリティが強化されます。

    ```json
    "AzureAd": {
        "Instance": "https://login.microsoftonline.com/",
        "TenantId": "<your-tenant-id>",
        "ClientId": "<agent-blueprint-id>",
        "Audience": "<agent-blueprint-id>",
        "ClientCredentials": [
            {
                "SourceType": "ClientSecret",
                "ClientSecret": "your-client-secret"
            }
        ]
    }
    ```

Microsoft.Identity.Web の詳細については、[Microsoft.Identity.Web ドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/microsoft-identity-web/)をご参照ください。

### ユーザー要求を検証する

アクセス トークンの検証後、エージェントはユーザーを識別し、承認チェックを実行できます。 次の API ルートの例では、アクセス トークンからユーザー要求を抽出し、API 応答で返します。

```csharp
app.MapGet("/hello-agent", (HttpContext httpContext) =>
{   
    var claims = httpContext.User.Claims.Select(c => new
    {
        Type = c.Type,
        Value = c.Value
    });

    return Results.Ok(claims);
})
.RequireAuthorization();
```

### ダウンストリーム API のトークンを取得する

対話型エージェントは、ユーザーのトークンを検証した後、アクセス トークンを要求して、ユーザーに代わってダウンストリーム API を呼び出すことができます。 On-Behalf-Of (OBO) フローを使用すると、エージェントは以下のことが可能です。

- クライアントからアクセス トークンを受け取ります。
- Microsoft Graph などのダウンストリーム API の新しいアクセス トークンに交換します。
- その新しいトークンを使用して、元のユーザーの代わりに保護されたリソースにアクセスします。

- Microsoft Identity Web
- MICROSOFT GRAPH SDK

`Microsoft.Identity.Web` ライブラリは、トークン交換を自動的に処理することで OBO の実装を簡略化するため、プロトコルに従ってフローを手動で実装する必要はありません。

1. 必要な NuGet パッケージをインストールします。

    ```bash
    dotnet add package Microsoft.Identity.Web
    dotnet add package Microsoft.Identity.Web.AgentIdentities
    ```
2. ASP.NET Core Web API プロジェクトで、Microsoft Entra ID認証の実装を更新します。

    ```csharp
    // Program.cs
    using Microsoft.AspNetCore.Authorization;
    using Microsoft.Identity.Abstractions;
    using Microsoft.Identity.Web;
    using Microsoft.Identity.Web.Resource;
    using Microsoft.Identity.Web.TokenCacheProviders.InMemory;
    
    var builder = WebApplication.CreateBuilder(args);
    
    builder.Services.AddMicrosoftIdentityWebApiAuthentication(builder.Configuration)
        .EnableTokenAcquisitionToCallDownstreamApi();
    builder.Services.AddAgentIdentities();
    builder.Services.AddInMemoryTokenCaches();
    
    var app = builder.Build();
    
    app.UseAuthentication();
    app.UseAuthorization();
    
    app.Run();
    ```
3. エージェント API で、受信ユーザー アクセス トークンをエージェント ID の新しいアクセス トークンと交換します。 `Microsoft.Identity.Web` は、受信アクセス トークンを検証し、代理トークン交換を処理します。

    ```csharp
    app.MapGet("/agent-obo-user", async (HttpContext httpContext) =>
    {
        string agentIdentity = "<your-agent-identity>";
        IAuthorizationHeaderProvider authorizationHeaderProvider = httpContext.RequestServices.GetService<IAuthorizationHeaderProvider>()!;
        AuthorizationHeaderProviderOptions options = new AuthorizationHeaderProviderOptions().WithAgentIdentity(agentIdentity);
    
        string authorizationHeaderWithUserToken = await authorizationHeaderProvider.CreateAuthorizationHeaderForUserAsync(["https://graph.microsoft.com/.default"], options);
    
        var response = new { header = authorizationHeaderWithUserToken };
        return Results.Json(response);
    })
    .RequireAuthorization();
    ```

Microsoft Graph SDK を使用している場合は、`GraphServiceClient` を使用してMicrosoft Graphを認証できます。

1. 必要な NuGet パッケージをインストールします。

    ```bash
    dotnet add package Microsoft.Identity.Web
    dotnet add package Microsoft.Identity.Web.AgentIdentities
    dotnet add package Microsoft.Identity.Web.GraphServiceClient
    ```
2. ASP.NET Core Web API プロジェクトで、認証を構成し、Microsoft Graphのサポートを追加します。

    ```csharp
    // Program.cs
    using Microsoft.AspNetCore.Authorization;
    using Microsoft.Identity.Web;
    using Microsoft.Identity.Web.TokenCacheProviders.InMemory;
    
    var builder = WebApplication.CreateBuilder(args);
    
    builder.Services.AddMicrosoftIdentityWebApiAuthentication(builder.Configuration)
        .EnableTokenAcquisitionToCallDownstreamApi();
    builder.Services.AddAgentIdentities();
    builder.Services.AddInMemoryTokenCaches();
    builder.Services.AddMicrosoftGraph();
    
    var app = builder.Build();
    
    app.UseAuthentication();
    app.UseAuthorization();
    
    app.Run();
    ```
3. サービス プロバイダーから `GraphServiceClient` を取得し、エージェント ID を使用Microsoft Graph API を呼び出します。

    ```csharp
    app.MapGet("/agent-obo-user", async (HttpContext httpContext) =>
    {
        string agentIdentity = "<your-agent-identity>";
    
        GraphServiceClient graphServiceClient = httpContext.RequestServices.GetService<GraphServiceClient>()!;
        var me = await graphServiceClient.Me.GetAsync(r => r.Options.WithAuthenticationOptions(
            o =>
            {
                o.WithAgentIdentity(agentIdentity);
            }));
        return me.UserPrincipalName;
    })
    .RequireAuthorization();
    ```

内部的には、OBO フローには 2 つのトークン交換が含まれます。最初に、エージェント ID ブループリントはクライアント資格情報を使用して交換トークンを取得し、次にエージェント ID はそのトークンをダウンストリーム API トークンのユーザーのアクセス トークンと共に交換します。 HTTP 要求形式やトークン検証の詳細など、プロトコルの完全なチュートリアルについては、 [エージェントでの On-behalf-of フローを](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-on-behalf-of-oauth-flow)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/key-concepts"} -->
## Microsoft Entra エージェント IDの基本的な概念 - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/key-concepts
- Service: entra-id / agent-id
- Article date: 2026-04-03
- Summary: AI 認証におけるエージェント ID の役割を確認します。 一意の識別子、トークンの使用状況、およびシステムへのセキュリティで保護されたアクセスを可能にする方法について説明します。

Microsoft エージェント ID プラットフォームは、エンタープライズ環境で動作する AI エージェント専用に設計された特殊な ID コンストラクトを提供します。 これらの ID コンストラクトにより、従来のユーザー ID やアプリケーション ID とは異なるセキュリティで保護された認証と承認のパターンが可能になり、自律 AI システムの固有の要件に対処できます。

この記事では、エージェント ID 管理の基礎となる主要な概念 (エージェント ID、エージェント ID ブループリント、およびそれらのサポート コンポーネント) について説明します。 これらの概念を理解することは、AI エージェントのセキュリティで保護されたスケーラブルな認証パターンを実装する必要がある開発者にとって不可欠です。

エージェント ID アーキテクチャは階層モデルに従います。エージェント ID ブループリントは、それぞれが個別の ID と機能を持つ複数のエージェント インスタンスを作成するためのテンプレートとして機能します。 このアプローチにより、一元的な管理が可能になり、多様な AI エージェントのデプロイ シナリオに必要な柔軟性が提供されます。

### コア ID の概念

次の概念は、Microsoft Entra エージェント IDとMicrosoft エージェント ID プラットフォームの基礎を形成します。

#### エージェント識別子

エージェント ID は、AI エージェントがシステムに対する認証とリソースへのアクセスに使用するプライマリ ID です。 ユーザー アカウントとは異なり、エージェント ID には独自の資格情報がありません。 エージェント ID ブループリントによって発行されたトークンを使用して認証します。 詳細については、「 [エージェント ID」を](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-identities)参照してください。

#### エージェント ID ブループリント

エージェント ID ブループリントは、Microsoft Entra ID内のオブジェクトであり、1 つ以上のエージェント ID のテンプレートと認証基盤として機能します。 ブループリントは資格情報を保持し、資格情報を使用して、そこから作成されたすべてのエージェント ID に代わってトークンを取得します。 ブループリントに適用されるポリシーと設定 (条件付きアクセスなど) は、そのすべてのエージェント ID に対して有効になります。 詳細については、「 [エージェント ID ブループリント」を](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-blueprint)参照してください。

#### エージェント ID 設計図の主例

ブループリントをテナントに追加すると、Microsoft Entra が対応するプリンシパル オブジェクトを作成します。 エージェント ID ブループリント プリンシパルは、テナント内のブループリントのプレゼンスを記録し、トークンを取得して監査ログに表示できるようにするMicrosoft Entra オブジェクトです。 詳細については、「 [エージェント ID ブループリント プリンシパル」を](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-blueprint#agent-identity-blueprint-principals)参照してください。

#### 従来のサービス プリンシパル (AI エージェントには推奨されません)

従来のサービス プリンシパルは、静的で確定的なワークロード用に設計されていました。 サービス プリンシパルには AI エージェントが必要とするガバナンス インフラストラクチャがないため、Microsoft Entra エージェント ID が存在します。 強制されたスポンサーシップ、エージェント対応の監査エントリ、ブループリントで管理されたライフサイクルはありません。 詳細については、「 [エージェント ID、サービス プリンシパル、およびアプリケーション」を](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-service-principals)参照してください。

#### 通常のユーザー アカウント (AI エージェントには推奨されません)

通常のMicrosoft Entraユーザー アカウントは、人間のサインイン パターン用に設計されています。 AI エージェントに割り当てると、すべてのゼロ トラスト適用レイヤーでエラーが発生します。人間向けに構築された条件付きアクセス ポリシーがエージェントに正しく適用されず、ID 保護の検出が低下し、ID ガバナンス プロセスによってエージェント のアクセスが誤って削除される可能性があります。 詳細については、「 [エージェント ID アーキテクチャの計画」を参照してください](https://learn.microsoft.com/ja-jp/entra/agent-id/how-to-plan-agent-identity-architecture)。

### エージェント操作パターン

エージェント ID プラットフォームでは、エージェントの動作と認証方法に関する次のパターンがサポートされており、それぞれが異なるユース ケースとセキュリティ要件に対応しています。

#### 対話型エージェント

対話型エージェントは、サインインしているユーザーに代わって、多くの場合、チャット インターフェイスを介して、特定のタスクをオンデマンドで実行します。 タスクには、顧客データを分析して販売に関する推奨事項を確認したり、サポートに関する質問に回答したりして、担当者へのエスカレーションを行ったりします。 これらのエージェントには、On-Behalf-Of (OBO) 認証フローを使用してユーザーに代わって動作できるようにする Microsoft Entra の委任されたアクセス許可が付与されます。 一般的なシナリオとしては、カスタマー サポート アシスタント、リサーチ ヘルパー、リアルタイム コラボレーション エージェントなどがあります。

#### 自律エージェント

自律エージェントは、人間のユーザーの ID ではなく、独自の ID を使用して独立して動作します。 これらのエージェントはバックグラウンドで実行され、セキュリティ操作のネットワーク ログの監視、自動スケーリングによるインフラストラクチャデプロイの管理、スケジュールされたメンテナンス タスクの処理など、人間の介入なしに意思決定を行い、アクションを実行します。 自律エージェントは、エージェント ID とクライアント資格情報フローを使用して、Microsoft Entra ID プラットフォームで直接認証します。

#### エージェントのユーザー アカウント

エージェントのユーザーアカウントは、エージェントIDと1対1に対応するオプションのアカウントです。 永続的な ID や、メールボックス、予定表、Teams チャネル、ドキュメントなどの組織リソースへのアクセスなど、人間のユーザー特性を持つ機能です。 エージェントのユーザー アカウントは、エージェントがユーザー オブジェクトを必要とするシステムにアクセスする必要がある場合にのみ使用します。 エージェントのユーザー アカウントはエージェント ID を置き換えません。両方が存在している必要があります。 詳細については、「 [エージェントのユーザー アカウント」](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-users)を参照してください。

### エージェントの所有者、スポンサー、およびマネージャー

エージェント ID プラットフォームでは、技術的な管理とビジネスアカウンタビリティを分離する管理モデルが導入され、過剰なアクセス許可なしで運用管理とコンプライアンスの監視が保証されます。 エージェントの管理ロールには、所有者、マネージャー、およびスポンサーが含まれます。

- **所有者は** 、エージェントの技術管理者として機能し、運用面と構成面を処理します。
- **スポンサーは、エージェントに** ビジネスアカウンタビリティを提供し、技術的な管理アクセスなしでライフサイクルの決定を行います。
- **マネージャーは、** エージェントのユーザー アカウントの雇用マネージャーまたは運用所有者として指定されている人間のユーザーです。

詳細については、「[エージェント ID の管理関係 (所有者、スポンサー、およびマネージャー)](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-owners-sponsors-managers)」を参照してください。

### Microsoft Entra ID 認証 SDK (サイドカー)

Microsoft Entra ID認証 SDK (サイドカー) は、Microsoft ID プラットフォームに登録されているエージェントのトークンの取得、検証、およびセキュリティで保護されたダウンストリーム API 呼び出しを処理するコンテナー化された Web サービスです。 アプリケーションと共にコンパニオン コンテナーとして実行されるため、ID ロジックを専用サービスにオフロードできます。 詳細については、「[Microsoft Entra ID認証 SDK (サイドカー)](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/overview)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/manage-agent-blueprint"} -->
## Microsoft Entra 管理センターでエージェント ID ブループリントを管理する - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agent-blueprint
- Service: entra-id / agent-id
- Article date: 2026-04-27
- Summary: アクセス許可の表示、資格情報の管理、所有者とスポンサーの構成など、Microsoft Entra 管理センターでエージェント ID ブループリントを管理する方法について説明します。

Microsoft Entra 管理センターを使用すると、テナント内のすべてのエージェント ID ブループリント プリンシパルを表示できます。 資格情報、アクセス許可、所有者など、ブループリント プリンシパルの検索、フィルター処理、並べ替え、管理を行うことができます。

### エージェント ID ブループリントの一覧に移動します

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**エージェント**&gt;**エージェント設計図**に移動してください。
3. ブループリント プリンシパルを選択し、その管理ページを開いてください。

### ブループリントの検索とフィルター処理

1. 検索ボックスにブループリント プリンシパルの **名前** または **オブジェクト ID を** 入力します。
2. **ブループリント アプリケーション ID** でブループリントを検索するには、[**フィルターの追加]** を選択し、**ブループリント アプリ ID** フィルターを追加します。
3. さまざまな条件に基づいてフィルターを使用して、一覧をさらに絞り込むことができます。

### 表示オプションを選択する

ビューをカスタマイズするには、[ **列の選択** ] を選択して、表示する列を構成します。 使用できる列は次のとおりです。

| 列名 | 説明 | 並べ替え可能 | フィルター可能 | 特記事項 |
| --- | --- | --- | --- | --- |
| **氏名** | エージェント ID ブループリント プリンシパルの表示名 | ✓ | ✓ | プライマリ検索フィールド。クリックすると、エージェント ID ブループリント プリンシパルの詳細が表示されます |
| **エージェント ID** | エージェント ブループリント プリンシパルによって作成された子エージェント ID の数 | ✗ | ✗ | これを選択すると、そのエージェント ID ブループリント プリンシパルのリンクされた子エージェント ID の一覧が表示されます |
| **ステータス** | 現在の操作状態 (アクティブまたは無効) | ✓ | ✓ |  |
| **ブループリント アプリケーション ID** | このエージェント ID ブループリント プリンシパルの一部であるエージェント ID ブループリントの一意の識別子 | ✗ | ✓ |  |
| **オブジェクト ID** | エージェント ブループリント プリンシパルの一意識別子 | ✗ | ✓ |  |

### リンク されたエージェント ID を表示する

ブループリントから作成されたエージェント ID を表示します。

1. ブループリントの管理ページで、左側のメニューから **[リンク されたエージェント ID] を** 選択します。
2. この一覧には、リンクされた各 ID とその **名前**、 **状態**、 **アクセスの表示** リンク、 **所有者とスポンサーが表示されます**。
3. ID 名を選択して、その詳細ページに移動します。
4. **[フィルターの追加]** を使用して、一覧を絞り込みます。

### 付与されたアクセス許可を表示する

同意のタイプごとに編成されたブループリント主体に割り当てられたアクセス許可を確認します。

1. ブループリントの管理ページで、[**アクセス**] で [**付与されたアクセス許可**] を選択します。
2. 管理者の **同意** タブを選択して管理者の同意によって付与されたアクセス許可を表示するか、[ **ユーザーの同意** ] タブを選択して、ユーザーの同意によって付与されたアクセス許可を表示します。
3. この一覧には、各アクセス許可エントリの **API 名**、 **要求値**、 **アクセス許可**、 **種類**、 **付与方法**、および **付与者が** 表示されます。

### マニフェストを表示する

エージェント ID ブループリントの生の JSON マニフェストを表示または編集するには、ブループリントの管理ページの **[開発者設定**] で **[マニフェスト**] を選択します。

Note

マニフェスト エディターは現在プレビュー段階です。

### 資格情報を管理する

エージェント ID が認証に使用する資格情報を構成します。 資格情報ページには、資格情報の種類ごとに 3 つのタブがあります。

[Image: 証明書、クライアント シークレット、フェデレーション資格情報の 3 つのタブを示すブループリント資格情報ページのスクリーンショット。]

Important

ゼロ トラスト原則に最も適合するには、クライアント シークレットの代わりにフェデレーション資格情報または証明書を使用します。

#### 証明書のアップロード

1. ブループリントの管理ページで、[**開発者設定**] の [**資格情報]** を選択します。
2. **証明書** タブを選択します。
3. **[証明書のアップロード]** を選択します。
4. 証明書ファイルを参照して選択し、必要に応じて説明を追加します。
5. **追加**を選択します。

#### クライアント シークレットの作成

1. ブループリントの管理ページで、[**開発者設定**] の [**資格情報]** を選択します。
2. [ **クライアント シークレット** ] タブを選択します。
3. **新しいクライアント シークレット** を選択します。
4. シークレットの **説明** を入力します。
5. **[有効期限]**の期間を選択してください。 カスタム有効期限の場合は、 **開始日** と **終了日** を設定します。
6. **追加**を選択します。
7. **シークレット値**をすぐにコピーします。 ページを離れると、値は再び表示されません。

Note

テナント ポリシーでは、クライアント シークレットの最大有効期間が制限される場合があります。

#### フェデレーション資格情報を追加する

フェデレーション資格情報では、ワークロード ID フェデレーションを使用して、シークレットを格納せずにMicrosoft Entraと外部 ID プロバイダー間の信頼を確立します。 これらのシナリオとワークロード ID フェデレーションのしくみの詳細については、「 [ワークロード ID](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation) フェデレーション」を参照してください。

エージェント ID ブループリント プリンシパルのフェデレーション資格情報を追加するには、次の手順を実行します。

1. ブループリントの管理ページで、[**開発者設定**] の [**資格情報]** を選択します。
2. [ **フェデレーション資格情報** ] タブを選択します。
3. **資格情報の追加**を選択します。
4. **フェデレーション資格情報シナリオ**で、シナリオを選んでください。
    - **マネージド ID** — テナント間でトークンを取得し、リソースにアクセスするようにマネージド ID を構成します。
    - **GitHub Actions Azure リソースのデプロイ** — トークンを取得してAzureにデプロイするようにGitHub ワークフローを構成します。
    - **Kbernetes Azure リソースにアクセスする** — トークンを取得してリソースAzureアクセスするように Kubernetes サービス アカウントを構成します。
    - **その他の発行者** - 外部 OpenID Connect プロバイダーによって管理される ID を構成します。
5. 選択したシナリオに対して、必須フィールドを入力してください。
6. **追加**を選択します。

### 所有者とスポンサーを管理する

所有者とスポンサーは、ブループリントのガバナンスを確立するのに役立ちます。 左側のメニューの **[所有者とスポンサー** ] ページから、エージェント ブループリントとエージェント ブループリント プリンシパルの両方の所有権を管理できます。

所有者とスポンサーの追加、削除、管理の詳細な手順については、 [エージェント ID とブループリントの所有者とスポンサーの追加と管理](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-owners-sponsors-agents)に関するページを参照してください。

### 監査ログとサインイン ログを表示する

ブループリントの管理ページから、エージェント ブループリント プリンシパルとブループリント自体の両方のログを表示できます。

#### エージェント ブループリント プリンシパルの活動

エージェント ブループリント プリンシパルは、監査ログとサインイン ログの両方をサポートします。

- ブループリント プリンシパルに対して実行された管理アクションを表示するには、左側のメニューの **[エージェント ブループリント プリンシパル アクティビティ**] で [**監査ログ**] を選択します。
- ブループリント プリンシパルのサインイン アクティビティを表示するには、左側のメニューの **[エージェント ブループリント プリンシパル アクティビティ**] の下にある **[サインイン ログ**] を選択します。 詳細については、エージェントの [サインイン ログの表示を](https://learn.microsoft.com/ja-jp/entra/agent-id/sign-in-audit-logs-agents)参照してください。

#### エージェント ブループリント活動

エージェント ブループリントでは、監査ログのみがサポートされます。

- ブループリント構成に関連する管理アクションを表示するには、左側のメニューの **[エージェント ブループリント アクティビティ**] で [**監査ログ**] を選択します。

### エージェント ID ブループリント プリンシパルを無効にする

エージェント ID ブループリント プリンシパルを無効にするには、ブループリントの概要ページの上部にあるコマンド バーの **[無効]** ボタンを選択します。 このブループリントから作成された既存のエージェント ID が **認証できなくなる**ことを示す確認ダイアログが表示されます。 アクションを続行するには確認が必要です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/manage-agent-identities-admin"} -->
## 組織内のエージェント ID を管理する - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agent-identities-admin
- Service: entra-id / agent-id
- Article date: 2026-05-01
- Summary: 組織全体でエージェント ID を管理する方法について説明します。 Microsoft Entra 管理センターと条件付きアクセスを使用して、エージェントの表示、無効化、管理、監視を行います。

Microsoft Entra エージェント IDでは、組織全体でエージェント ID を管理するための一元化されたツール セットが提供されます。 エージェント ID は、Microsoft Entra IDの個別の ID の種類であり、エージェントのワークロードに合わせて調整された分類、メタデータ、およびセキュリティ制御を備えた AI エージェント向けに設計されています。

この記事では、エージェントの表示と無効化から、アクセスの管理、アクティビティの監視、セキュリティ リスクへの対応まで、主要なエージェント管理タスクについて説明します。 テナント全体のエージェント監視を担当する管理者であるか、特定のエージェントを管理するスポンサーであるかに関係なく、このガイドでは、組織内のエージェント ID を効果的に管理するために必要な情報を提供します。

### 前提条件

管理タスクが異なると、異なるロールとライセンスが必要になります。 次の表は、エージェント ID 管理の各領域に必要なロールをまとめたものです。

| Task | 必要なロール | メモ |
| --- | --- | --- |
| エージェント ID の表示 | Microsoft Entra ユーザー アカウント | 表示に管理者ロールは必要ありません。 |
| エージェント ID の管理 | エージェント ID 管理者またはクラウド アプリケーション管理者 | エージェント ID の所有者は、これらのロールを持たない独自のエージェントを管理できます。 |
| エージェントブループリントを作成する | エージェント ID 開発者 | ユーザーは、ブループリントとそのサービス プリンシパルの所有者として追加されます。 |
| 条件付きアクセス ポリシーを構成する | 条件付きアクセス管理者 | Microsoft Entra ID の P1 ライセンスが必要です。 |
| ID 保護リスク レポートを表示する | セキュリティ管理者、セキュリティ オペレーター、またはセキュリティ閲覧者 | プレビュー期間中Microsoft Entra ID P2 ライセンスが必要です。 |
| ライフサイクル ワークフローの構成 | ライフサイクル ワークフロー管理者 |  |

### エージェント ID の表示

Microsoft Entra 管理センターでは、テナント内のすべてのエージェント ID を表示するための一元化されたインターフェイスが提供されます。 列の検索、フィルター処理、並べ替え、カスタマイズを行って、特定のエージェントを見つけることができます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Agents**&gt;**Agent ids** に移動します。
3. 名前、説明、状態、所有者、スポンサー、付与されたアクセス許可、サインイン ログなど、エージェント ID を選択して詳細を表示します。

特定のエージェントを検索するには、検索ボックスに **名前** または **オブジェクト ID を** 入力するか、 **ブループリント アプリ ID** フィルターを追加します。 [列の選択] ボタンを選択すると、表示される **列** をカスタマイズできます。 使用可能な列には、 **名前**、 **作成日**、 **状態**、 **オブジェクト ID**、 **アクセスの表示**、 **ブループリント アプリ ID**、 **所有者とスポンサー**、 **およびエージェント ID の使用**が含まれます。

このビューからのエージェントのフィルター処理、列のカスタマイズ、および表示の詳細な手順については、「 [テナント内のエージェント ID の表示とフィルター処理」を参照してください](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-lists)。

### エージェント ID ブループリントの管理

エージェント ID ブループリントは、個々のエージェント ID の作成元となる親定義です。 管理センターでは、すべてのブループリント プリンシパルの表示、アクセス許可の管理、アクティビティの監視を行うことができます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**エージェント**&gt;**エージェント設計図**に移動してください。
3. 任意のエージェントのアイデンティティ設計プリンシパルを選択して管理します。

ブループリントの管理ページでは、次のことができます。

- **リンク されたエージェント ID の表示**: このブループリントから作成されたすべての子エージェント ID を表示します。
- **ブループリント アクセスの管理: ブループリント**に割り当てられたアクセス許可を表示、管理、取り消します。
- **所有者とスポンサーの管理**: 所有者は技術的な管理を処理しますが、スポンサーはエージェントの目的とライフサイクルの決定に対して責任を負います。
- **監査ログの表示**: セキュリティとコンプライアンスの管理上の変更を追跡します。
- **サインイン ログの表示**: 認証イベントを監視します。
- **ブループリントを無効にする**: コマンド バーで **[無効]** を選択します。

詳細な手順については、 [テナント内のエージェント ID ブループリントの表示と管理に関するページを参照してください](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agent-blueprint)。

### ブループリントの継承可能なアクセス許可を構成する

継承可能なアクセス許可により、エージェント ID は、OAuth 2.0 の委任されたアクセス許可スコープを親ブループリントから自動的に継承できます。 ブループリントで継承可能なアクセス許可を構成すると、新しく作成されたエージェント ID は、対話型のユーザーまたは管理者の同意プロンプトを必要とせずに、スコープの基本セットを受け取ります。

リソース アプリごとに 2 つの継承パターンがサポートされています。

| パターン | 説明 |
| --- | --- |
| **列挙されたスコープ** | 明示的に一覧表示されているスコープのみを継承します。 きめ細かい制御に使用します。 |
| **許可されているすべてのスコープ** | リソース アプリで使用可能なすべての委任されたスコープを継承します。 ブループリントに新しく付与されたスコープは自動的に含まれます。 |

基本的なアクセス許可のみを使用して列挙されたスコープから始めて、必要に応じて展開します。 この方法は、最小限の特権の原則に従い、エージェントが実際に使用するアクセス許可を簡単に監査できるようにします。

キーの制限:

- ブループリントあたり最大 10 個のリソース アプリ。
- 列挙されたスコープの場合、リソース アプリあたり最大 40 個のスコープ。
- 一部の高い特権スコープはプラットフォーム ポリシーによってブロックされ、継承できません。

継承可能なアクセス許可は、Microsoft Graphを使用して、`inheritablePermissions` アプリケーション リソースの `agentIdentityBlueprint` ナビゲーション プロパティを使用して構成されます。 詳細な API の例 (追加、更新、削除) については、「 [エージェント ID ブループリントの継承可能なアクセス許可を構成する」を](https://learn.microsoft.com/ja-jp/entra/agent-id/configure-inheritable-permissions-blueprints)参照してください。

### リソースへのエージェント アクセスを制御する

条件付きアクセス ポリシーは、エージェント ID 認証のテナント全体の制御を提供します。 これらのポリシーを使用して、すべてのエージェント ID をブロックしたり、特定のエージェントのみを許可したり、ID 保護シグナルに基づいて危険なエージェントをブロックしたりすることができます。

エージェント ID の条件付きアクセスに関する重要なポイント:

- ポリシーは、**すべてのエージェント ID** または**ブロック**許可コントロールを持つ**すべてのエージェント ユーザー**にスコープを設定できます。
- ポリシーは、組織全体のエージェント アクセスを防ぐために **、すべてのリソース** を対象にすることができます。
- **エージェントのリスク状態** (高、中、低) を使用すると、ID Protection からのリスクシグナルに基づいてエージェントをブロックできます。
- ポリシーでは、適用前に安全な評価を行う **レポート専用モード** がサポートされています。

Important

条件付きアクセスの適用は、エージェント ID またはエージェントのユーザー アカウントが任意のリソースのトークンを要求するときに適用されます。 エージェント ID ブループリントが、エージェント ID またはエージェントのユーザー アカウントを作成するためのトークンを取得する場合は適用 **されません** 。

詳細なポリシー構成、詳細なチュートリアル、ビジネス シナリオの例については、「 [エージェント ID の条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/agent-id)」を参照してください。

### エージェントのアクティビティを監視する

#### サインインログと監査ログ

エージェントのIDアクティビティは、Microsoft Entraの監査ログとサインインログにキャプチャされます。

- **監査ログは** 、エージェント関連のイベントを、発生元のベース ID の種類で記録します。 たとえば、エージェント ID ユーザーの作成は "ユーザーの作成" 監査アクティビティとして表示され、エージェント ID の作成は "サービス プリンシパルの作成" として表示されます。
- **サインイン ログ** には、エージェントとそのサインイン動作に関するプロパティを提供する `agentSignIn` リソースの種類が含まれます。

エージェントのサインイン ログを表示するには:

1. 少なくとも [Reports Reader](https://entra.microsoft.com) として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) にサインインします。
2. Entra IDモニタリング & ヘルスサインインログ に移動します。
3. 次のフィルターを使用します。
    - **エージェントの種類**: **エージェント ID ユーザー**、**エージェントアイデンティティ**、**エージェントアイデンティティブループリント**、または**エージェントではない**から選択します。
    - **エージェント:** **「いいえ」** または **「はい」** から選択します。

Microsoft Graphを使用して、エージェントのサインイン イベントを取得することもできます。

```http
GET https://graph.microsoft.com/beta/auditLogs/signIns?$filter=signInEventTypes/any(t: t eq 'servicePrincipal') and agent/agentType eq 'AgentIdentity'
```

ログの種類とアクセス方法の詳細については、「[Microsoft Entra エージェント ID logs](https://learn.microsoft.com/ja-jp/entra/agent-id/sign-in-audit-logs-agents)」を参照してください。

### エージェントのリスクを検出して軽減する

#### エージェントのアイデンティティ保護

Microsoft Entra ID 保護は、エージェント ID の異常な動作を監視します。 未知のリソース アクセス、サインインの急増、アクセス試行の失敗など、6 種類のオフライン リスクが検出されます。 管理者は、 **危険なエージェント レポート** を確認し、侵害の確認、安全な確認、リスクの無視、エージェントの無効化などの対応アクションを実行できます。

注

エージェントの ID 保護には、プレビュー中に Microsoft Entra ID P2 ライセンスが必要です。

エージェントが侵害されたことを確認すると、リスク レベルが **[高**] に設定されます。 高いエージェント リスクでブロックするように構成された条件付きアクセス ポリシーがある場合、エージェントはリソースへのアクセスを自動的にブロックされます。

完全なリスク検出テーブル、応答アクション、Graph APIの詳細、およびレポートのチュートリアルについては、「[Identity Protection for agents](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-agents)」を参照してください。

#### エージェントのセキュリティ インシデントへの対応

エージェント アクティビティがリスク検出またはセキュリティの問題をトリガーする場合は、次の順序に従います。

1. **Detect**: Microsoft Entra 管理センターの **Risky Agents レポート**を確認します。 リスク検出は、最大 90 日間表示できます。 詳細には、エージェントの表示名、リスクの状態、リスク レベル、エージェントの種類、スポンサーが含まれます。
2. **応答**: すぐにアクションを実行します。
    - **侵害を確認**する: リスク レベルを高に設定し、エージェント リスクが高い場合にブロックするように構成されたリスクベースの条件付きアクセス ポリシーをトリガーします。
    - **Disable**: Microsoft Entra IDおよび接続されているアプリ間のすべてのサインインを禁止します。
3. **調査**: リスク検出の詳細、サインイン ログ、監査ログを確認して、スコープと影響を把握します。
4. **復旧**：調査結果に基づく
    - 誤検知の場合: リスクを無視し、エージェントを再度有効にします。
    - 真の侵害の場合: 再び有効にする前に資格情報をローテーションするか、エージェント ID を廃止します。

リスクの種類、検出メカニズム、および応答アクションの詳細については、「 [エージェントの Identity Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-agents)」を参照してください。

### エージェント ID とスポンサーの監視を管理する

#### スポンサーの責任

各エージェント ID には、ライフサイクルとアクセスの決定を担当する責任のある人間のスポンサーが必要です。 主要なガバナンス動作は次のとおりです。

- **自動スポンサー転送**: スポンサーが組織を離れる場合、Microsoft Entra IDはスポンサーのマネージャーにスポンサーシップを自動的に再割り当てします。
- **有効期限の通知**: スポンサーは、アクセス パッケージの割り当てアプローチの有効期限が切れると通知を受け取ります。 スポンサーは、延長を要求するか (新しい承認サイクルをトリガーします)、割り当ての有効期限を切らせることができます。
- **アクセス要求経路**: アクセス パッケージは、エージェント独自のプログラムによる要求、エージェントの代理のスポンサー、管理者の直接割り当ての 3 つの経路を通じて要求できます。

アクセス パッケージは、セキュリティ グループ メンバーシップ、アプリケーション OAuth API のアクセス許可 (Microsoft Graph アプリケーションのアクセス許可を含む)、およびMicrosoft Entraロールを付与できます。

アクセス パッケージの構成やスポンサー ポリシーを含む完全なガバナンスの概要については、 [エージェント ID の管理に関するページを](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-id-governance-overview)参照してください。

#### ライフサイクル ワークフローを使用してスポンサー通知を自動化する

ライフサイクル ワークフローは、エージェント ID スポンサーシップに対して 2 つの自動化されたタスクを提供します。

- スポンサー プランの変更に関するメールをマネージャーに送信する
- スポンサーの変更に関するメールを共同主催者に送信する

どちらのタスクも **mover および leaver** カテゴリ のタスクです。これらは、結合者テンプレートではなく、mover または leaver ワークフロー テンプレートの下でのみトリガーされます。 これにより、エージェントのスポンサーがロールを変更したり、組織を離れたりしたときのスポンサーシップの継続性が保証されます。

詳細なワークフロー構成については、「ライフサイクル ワークフロー」の [エージェント ID スポンサー タスクを](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-sponsor-tasks)参照してください。

### 大規模なエージェント管理を自動化する

多数のエージェント ID を管理している組織では、次のオプションを使用できます。

- **複数選択の無効化**: 管理センターでは、一度に複数のエージェント ID を選択し、[ **すべてのエージェント ID** ] ページからそれらを一括で無効にすることができます。
- **Microsoft Graph API**: エージェント ID エンドポイントは、プログラムによる管理をサポートします。 たとえば、ID Protection では、プログラムによるリスク監視のために `riskyAgents` コレクションと `agentRiskDetections` コレクションが公開されます。

### エージェント ID を無効または制限する

組織は、必要なスコープに応じて、3 つのレベルでエージェント ID の使用を制御できます。

| Scope | 動作内容 | 詳細情報 |
| --- | --- | --- |
| **個々のエージェント** | 特定のエージェント ID を無効にして、アクセスとトークンの発行をブロックします。 管理者は管理センターを使用します。所有者とスポンサーは、マイ アカウント ポータルを使用します。 | [テナント内のエージェント ID を表示およびフィルター処理](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-lists)する ·[エンド ユーザー エクスペリエンスでエージェントを管理](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agent-identities-end-user)する |
| **ブループリント レベル** | 管理ページからエージェント ID ブループリントを無効にします。 これにより、そのブループリントから新しいエージェント ID が作成されなくなり、既存の ID がブロックされます。 | [テナント内のエージェント ID ブループリントを表示および管理する](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agent-blueprint) |
| **テナント全体** | 条件付きアクセス ポリシーを使用してすべてのエージェント ID 認証をブロックし、オプションで製品固有の制御 (Microsoft Entra ID、Security Copilot、Copilot Studio、Azure AI Foundry、Microsoft Teams) を通じて新しいエージェント ID の作成をブロックします。 | [テナントでエージェント ID を無効にする](https://learn.microsoft.com/ja-jp/entra/agent-id/disable-agent-identities) |

任意のスコープで無効なエージェント ID を再度有効にすると、アクセスとトークンの発行が復元されます。

注意事項

エージェント ID をグローバルに無効にすると、既存のエージェントが失敗し、Microsoft製品エクスペリエンスが低下し、チームが透明性の低いアプリケーションまたはサービス プリンシパル ID を使用するようにプッシュされる可能性があります。 適用する前に影響を評価します。 部分的なアプローチの場合は、条件付きアクセス ポリシーを使用して、すべてのエージェント ID ではなく特定のエージェントをブロックします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/manage-agent-identities-end-user"} -->
## エンド ユーザー エクスペリエンスでエージェントを管理する

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agent-identities-end-user
- Service: entra-id / agent-id
- Article date: 2026-05-01
- Summary: Microsoft Entra内のエンド ユーザー エクスペリエンスでエージェント ID を管理する方法について説明します。 所有またはスポンサーのエージェントを簡単に表示、制御、およびアクションを実行できます。

Microsoft Entra の [エージェントの管理] 機能を使用すると、所有またはスポンサーしているエージェント ID を表示および管理できます。 [エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/what-are-agent-identities) は、ユーザーまたはチームに代わって動作する、ボットや自動化されたプロセスなどの特別な ID です。 エージェントの管理機能を使用すると、担当するエージェントを簡単に確認し、その詳細を確認し、それらに対するアクセスを有効、無効、または要求するためのアクションを実行できます。

注

この記事は、エージェント ID の所有者とスポンサーを対象としています。 [ **エージェントの管理** ] メニューは、少なくとも 1 つのエージェント ID を所有またはスポンサーしているユーザーにのみ表示されます。 管理者によるテナント全体の管理については、 [組織内のエージェント ID の管理に関するページを参照してください](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agent-identities-admin)。

### エージェント ID の所有者またはスポンサーとしてエージェントを管理する

1. My [Account エンド ユーザー ポータル](https://myaccount.microsoft.com/) に、少なくとも 1 つのエージェント ID の所有者またはスポンサーとしてサインインします。
2. 新しいホームページにまだオプトインしていない場合は、バナーで [ **新しいバージョンを使用** ] を選択します。

    注

    新しいホーム ページを既に使用している場合は、バナーに "新しいバージョンのアカウント ホームページを使用しています" というメッセージと [ **以前のバージョンを使用** する] ボタンが表示されます。
3. 左側のメニューで、[ **エージェントの管理**] を選択します。

    注

    このメニュー項目は、少なくとも 1 つのエージェント ID の所有者またはスポンサーである場合にのみ表示されます。
4. **[スポンサーするエージェント]** タブまたは [**所有しているエージェント]** タブを選択して、エージェントを表示します。 [Image: マイ アカウント ポータルの [マネージド エージェント] ページのスクリーンショット。]
5. エージェントを選択すると、その詳細が表示されます。 [Image: エージェントの管理ビューのスクリーンショット。]

### エージェントを有効または無効にする

1. エージェントを無効にするには、一覧からエージェントを選択し、[ **エージェントの無効化]** を選択します。 これにより、ユーザーはアクセスできなくなり、トークンが発行されなくなります。 これは、管理センターからエージェントを無効にした場合と同じ効果があります。
2. 再度有効にするには、無効になっているエージェントを選択し、[エージェントの **有効化]** を選択します。 これにより、ユーザーはアクセスでき、トークンを発行できます。 スポンサーはエージェントを再度有効にできないことに注意してください。 エージェントの 1 つを再度有効にする必要がある場合は、所有者または管理者のヘルプが必要です。

### エージェント ID に代わってアクセス パッケージを要求する

エージェント ID の所有者またはスポンサーは、次の手順を実行して、そのエージェント ID のアクセス パッケージを要求できます。

1. https://myaccess.microsoft.com でマイ アクセス ポータルにサインインします。
2. [マイ アクセス ポータル] ページで、**[アクセス パッケージ]** を選択します。
3. [アクセス パッケージ] ページで、エージェント ID を要求するアクセス パッケージを見つけて、[ **要求**] を選択します。
4. [Request]\(要求\) ウィンドウの [ **Request details**]\(要求の詳細\) で、[ **Requesting for Sponsored agent]\(スポンサー付きエージェントの要求** \) または **[所有しているエージェントの要求**] を選択します。
5. エージェント ID を選択し、[ **続行**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/manage-agents-without-identity"} -->
## エージェント ID を持たないエージェントを管理する

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agents-without-identity
- Service: entra-id / agent-id
- Article date: 2026-05-01
- Summary: この記事では、Microsoft Entra ID にエージェント ID が関連付けられていないレジストリ専用エージェントを管理する方法について説明します。

管理者は、セキュリティと運用効率の両方について、エージェントを 360 度表示する必要があります。 一部のエージェントは、エージェント ID ブループリント プリンシパルとエージェント ID、またはサービス プリンシパルとして Microsoft Entra で表されます。 エージェント レジストリに登録されているが、Microsoft Entra エージェント ID が関連付けられていないエージェントも存在することも珍しくありません。 これらのエージェントは、レジストリのみのエージェントと呼ばれます。 これらは、オンボード中であるか、エージェントの ID プロバイダーとして Microsoft Entra エージェント ID を使用せずにレジストリに登録されている可能性があります。

### 前提条件

エージェントは、エージェントをエージェント レジストリに発行した後にのみ [レジストリに](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/publish-agents-to-registry)表示されます。

### エージェント ID なしでエージェントに移動する

エージェント ID のページなしでエージェントにアクセスするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 左側のナビゲーション ウィンドウで、**Entra ID**&gt;**Agents**&gt;**Agent レジストリ (プレビュー)** を選択します。
3. 特定のエージェントのエージェント カードの詳細を表示するには、[ **詳細を表示** ] リンクを選択します。 次のようなエージェントのカードの詳細が表示されます。

    - **名前**: エージェントの名前。
    - **レジストリ ID**: レジストリ内のエージェントを識別する一意の識別子です。
    - **プラットフォーム**: エージェントを作成したプラットフォーム。
    - **スキル**: エージェントの機能。
    - **ロゴ**: エージェントのロゴ。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/manage-owners-sponsors-agents"} -->
## エージェント ID とブループリントの所有者とスポンサーを追加および管理する - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/manage-owners-sponsors-agents
- Service: entra-id / agent-id
- Article date: 2026-04-28
- Summary: Microsoft Entra 管理センターでエージェント ID ブループリントとエージェント ID の所有者とスポンサーを追加および管理する方法について説明します。

所有者とスポンサーは、Microsoft Entra IDのエージェント ID ブループリントとエージェント ID に対して個別のガバナンス ロールを果たします。 所有者は、エージェントの構成と操作を管理できる技術管理者です。 スポンサーは、エージェントの目的、ライフサイクルの決定、アクセス レビューに対して責任を負うビジネス所有者です。

この記事では、Microsoft Entra 管理センターを使用して所有者とスポンサーを追加および削除する手順について説明します。 所有者とスポンサーの役割と責任の詳細については、 [所有者、スポンサー、およびマネージャーを](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-owners-sponsors-managers)参照してください。

### 前提条件

所有者とスポンサーを管理するには、次の手順を実行する必要があります。

- [エージェント ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-administrator)ロールを持っている。
- 管理するエージェント ID ブループリントまたはエージェント ID の既存の所有者であること。

### エージェント ブループリントに所有者またはスポンサーを追加する

エージェント ID ブループリントの所有者とスポンサーを管理する場合は、それぞれのタブを使用して、エージェント ID ブループリントまたはエージェント ブループリント プリンシパルに割り当てることができます。

Note

スポンサーとして動的メンバーシップ グループを使用する場合、スポンサープランの承認チェックが成功するまでに、メンバーシップ ルールの変更またはユーザー プロパティの変更後最大 24 時間かかる場合があります。 エージェント ID またはブループリントのスポンサーとして動的グループを割り当てるときは、それに応じて計画します。

[Image: 所有者とスポンサーの役割の一覧を示すブループリントの [所有者とスポンサー] ページのスクリーンショット。]

1. エージェント ID ブループリントの[Agent ID 管理者](https://entra.microsoft.com)または所有者として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-administrator) にサインインします。
2. **Entra ID**&gt;**エージェント**&gt;**エージェント設計図**に移動してください。
3. 管理するブループリントを選択します。
4. [ **アクセス** ] で [ **所有者とスポンサー**] を選択します。
5. 管理する対象に応じて、[ **エージェント ブループリント** ] タブまたは [ **エージェント ブループリント プリンシパル** ] タブを選択します。
6. 追加する方法に応じて、[ **追加**&gt;**所有者の追加** ] または [ **スポンサー**の追加] を選択します。
7. 追加するユーザーとグループ (スポンサーのみ) を検索して選択します。
8. **追加**を選択します。

### エージェント ID に所有者またはスポンサーを追加する

個々のエージェント ID に所有者とスポンサーを追加するプロセスは、ブループリントに似ています。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[Agent ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-administrator)またはエージェント ID の所有者としてサインインします。
2. **Entra ID**&gt;**Agents**&gt;**Agent ids** に移動します。
3. 管理するエージェント ID を選択します。
4. [ **アクセス** ] で [ **所有者とスポンサー**] を選択します。
5. **追加**&gt;**所有者を追加**または**スポンサーを追加**を選択します。
6. 追加するユーザーとグループ (スポンサーのみ) を検索して選択します。
7. **追加**を選択します。

### 所有者またはスポンサーを削除する

#### ブループリントから削除する

1. エージェント ID ブループリントの[Agent ID 管理者](https://entra.microsoft.com)または所有者として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-administrator) にサインインします。
2. **Entra ID**&gt;**エージェント**&gt;**エージェント設計図**に移動してください。
3. 管理するブループリントを選択します。
4. 左側のメニューから **[所有者とスポンサー** ] を選択します。
5. 管理する対象に応じて、[ **エージェント ブループリント** ] タブまたは [ **エージェント ブループリント プリンシパル** ] タブを選択します。
6. 削除する所有者またはスポンサーの横にあるチェック ボックスをオンにします。
7. **削除**を選択します。

#### エージェント ID から削除する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[Agent ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-administrator)またはエージェント ID の所有者としてサインインします。
2. **Entra ID**&gt;**Agents**&gt;**Agent ids** に移動します。
3. 管理するエージェント ID を選択します。
4. [ **アクセス** ] で [ **所有者とスポンサー**] を選択します。
5. 削除する所有者またはスポンサーの横にあるチェック ボックスをオンにします。
6. **削除**を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/microsoft-entra-sdk-for-agent-identities"} -->
## Microsoft Entra ID Auth SDK (サイドカー) を使用してトークンを取得し、ダウンストリーム API を呼び出す - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/microsoft-entra-sdk-for-agent-identities
- Service: entra-id / agent-id
- Article date: 2025-11-05
- Summary: 自律エージェントがMicrosoft Entra ID認証 SDK (サイドカー) を使用してトークンを取得し、ダウンストリーム API を個別に呼び出す方法について説明します。

Microsoft Entra ID認証 SDK (サイドカー) は、エージェントのトークンの取得、検証、およびダウンストリーム API 呼び出しを処理するコンテナー化された Web サービスです。 この SDK は HTTP API を介してアプリケーションと通信し、テクノロジ スタックに関係なく一貫した統合パターンを提供します。 Microsoft Entra ID Auth SDK (サイドカー) は、アプリケーション コードに ID ロジックを直接埋め込む代わりに、標準の HTTP 要求を介してトークンの取得、検証、および API 呼び出しを管理します。

### 前提条件

始める前に、以下のことを確認してください:

- [Microsoft Entra ID認証 SDK (サイドカー) を設定する](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/installation)
- [エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/create-delete-agent-identities)。 エージェント ID クライアント ID を記録します。
- [エージェント ID ブループリント](https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint)。 エージェント ID ブループリント クライアント ID を記録します。
- [Microsoft Entra ID で構成されている](https://learn.microsoft.com/ja-jp/entra/agent-id/grant-agent-access-microsoft-365)必要なアクセス許可

### コンテナー化されたサービスをデプロイする

Microsoft Entra ID認証 SDK (サイドカー) をコンテナー化されたサービスとして環境内にデプロイします。 [Microsoft Entra ID認証 SDK (サイドカー) ガイドの](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/installation)手順に従って、エージェント ID の詳細を使用してサービスを構成します。

### Microsoft Entra ID認証 SDK (サイドカー) 設定を構成する

Microsoft Entra ID認証 SDK (サイドカー) 設定を構成するには、次の手順に従います。

Warnung

セキュリティ リスクのために、エージェント ID ブループリントの運用環境では、クライアント シークレットをクライアント資格情報として使用しないでください。 代わりに、マネージド ID またはクライアント証明書を使用 [するフェデレーション ID 資格情報 (FIC)](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-config-app-trust-managed-identity) などのより安全な認証方法を使用してください。 これらの方法により、アプリケーション構成内に機密性の高いシークレットを直接格納する必要がなくなり、セキュリティが強化されます。

1. Microsoft Entra IDで必要なコンポーネントを設定します。 Microsoft Entra ID テナントにアプリケーションを登録していることを確認します。
2. クライアント資格情報を構成します。 この資格情報には、クライアント シークレット、証明書、またはフェデレーション ID 資格情報として使用しているマネージド ID を指定できます。
3. ダウンストリーム API を呼び出す場合は、必要なアクセス許可が付与されていることを確認します。 カスタム Web API を呼び出すには、SDK 構成で API 登録の詳細を指定する必要があります。

詳細については、「[Microsoft Entra ID認証 SDK (サイドカー) 設定を構成する」](https://learn.microsoft.com/ja-jp/entra/msidweb/agent-id-sdk/configuration)を参照してください。

### Microsoft Entra ID認証 SDK (サイドカー) を使用してトークンを取得する

Microsoft Entra ID認証 SDK (サイドカー) を使用してトークンを取得する手順を次に示します。

1. Microsoft Entra ID認証 SDK (サイドカー) を使用してトークンを取得します。 これは、エージェントが自律的に動作しているか、ユーザーに代わって動作しているかによって異なります。 考慮すべきシナリオは 3 つあります。

    - 自律エージェント: エージェント (自律) 用に作成されたサービス プリンシパルを使用して、自身の代理で動作するエージェント。
    - 自律エージェントのユーザー アカウント: エージェント専用に作成されたユーザー プリンシパル (独自のメールボックスを持つエージェントなど) を使用して、自分の代理で動作するエージェント。
    - 対話型エージェント: 人間のユーザーに代わって動作するエージェント。

    Microsoft Entra ID認証 SDK (サイドカー) 構成に基づいて、要求 URL にその名前を含めることで、ダウンストリーム API を指定します。 承認ヘッダー エンドポイントは `/AuthorizationHeader/{serviceName}` 形式になります。ここで、 `serviceName` は SDK 設定で構成されたダウンストリーム API の名前です。
2. 自律エージェントのアプリ専用 (クライアント資格情報) トークンを取得するには、要求でエージェント ID クライアント ID を指定します。

    ```bash
    GET /AuthorizationHeader/Graph?AgentIdentity=<agent-identity-client-id>
    Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGc...
    ```
3. 自律エージェントのユーザー アカウントのトークンを取得するには、ユーザー オブジェクト ID またはユーザー プリンシパル名を指定しますが、両方を指定しないでください。 これは、 `AgentUsername` または `AgentUserId`を提供することを意味します。 両方を指定すると、検証エラーが発生します。 トークンの取得に使用するエージェント ID を指定する `AgentIdentity` も指定する必要があります。 エージェント ID パラメーターがない場合、要求は検証エラーで失敗します。

    ```bash
    GET /AuthorizationHeader/Graph?AgentIdentity=<agent-identity-client-id>&AgentUserId=<agent-user-object-id>
    Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGc...
    ```

    ```bash
    GET /AuthorizationHeader/Graph?AgentIdentity=<agent-identity-client-id>&AgentUsername=<agent-user-principal-name>
    Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGc...
    ```
4. 対話型エージェントの場合は、代理 (OBO) フローを使用します。 エージェントは、ダウンストリーム API を呼び出すリソース トークンを取得する前に、付与されたユーザー トークンを最初に検証します。

    エージェント Web API は、呼び出し元のアプリケーションからユーザー トークンを受け取り、Microsoft Entra ID認証 SDK (サイドカー) `/Validate` エンドポイント経由でトークンを検証します。`AgentIdentity`と受信承認ヘッダーのみを使用して`/AuthorizationHeader`を呼び出してダウンストリーム API のトークンを取得します

    ```bash
    # Step 1: Validate incoming user token
    GET /Validate
    Authorization: Bearer <user-token>
    
    # Step 2: Get authorization header on behalf of the user
    GET /AuthorizationHeader/Graph?AgentIdentity=<agent-identity-client-id>
    Authorization: Bearer <user-token>
    ```

### API を呼び出す

ダウンストリーム API を呼び出すための承認ヘッダーを取得すると、Microsoft Entra ID Auth SDK (サイドカー) は、API 呼び出しで直接使用できる`Authorization` ヘッダー値を返します。

このヘッダーを使用して、ダウンストリーム API を呼び出すことができます。 Web API は、Microsoft Entra ID Auth SDK (サイドカー) の`/Validate` エンドポイントを呼び出してトークンを検証する必要があります。 このエンドポイントは、承認をさらに決定するためにトークン要求を返します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/migrate-copilot-studio-agents-to-agent-id"} -->
## Microsoft Entra エージェント ID を使用して Copilot Studio エージェントを再作成する - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/migrate-copilot-studio-agents-to-agent-id
- Service: entra-id / agent-id
- Article date: 2026-06-15
- Summary: ガバナンスとセキュリティを強化するために、Microsoft Entra エージェント IDを使用してMicrosoft Copilot Studioエージェントを再作成する方法について説明します。 現時点では、インプレース移行の方法はありません。

Microsoft Copilot Studioで作成された一部のエージェントは、プラットフォームで管理されるサービス プリンシパルを使用して認証されます。 これらのエージェントは、Copilot Studio が新しいすべてのエージェントに対してエージェント ID を自動的に作成するようになる前に作成された可能性があります。この変更は 2026 年 3 月 18 日に行われました。 これらのエージェントは、組織が [Microsoft Entra エージェント ID Copilot Studio](https://learn.microsoft.com/ja-jp/microsoft-copilot-studio/admin-use-entra-agent-identities) との統合をオプトインする前、または組織がエージェント ID の作成をオプトアウトした場合にも作成されている可能性があります。

これらのサービス プリンシパルを使用すると、エージェントはAzure Bot Service、Microsoft Teams、Bot Framework のスキルと通信できますが、Microsoft Entraこれらのサービス プリンシパルは、AI エージェントとしてではなく、標準アプリケーションとして扱われます。 Microsoft Entra エージェント IDを採用すると、条件付きアクセス ポリシー、一元化された監査ログ、ライフサイクル管理など、エージェント固有のガバナンスが提供されます。

この記事では、Microsoft Entra エージェント ID を使用して Copilot Studio エージェントを再作成し、従来のサービス プリンシパルを廃止する手動のプロセスについて説明します。 Copilot Studioはエージェント コード、資格情報、デプロイ ライフサイクルを管理するため、このプロセスはカスタム構築エージェントとは異なります。 コードと ID の構成を所有しているエージェントについては、「 [カスタム アプリの登録をエージェント ID に移行する」を](https://learn.microsoft.com/ja-jp/entra/agent-id/migrate-custom-app-registrations-to-agent-id)参照してください。

Important

現時点では、既存のCopilot Studio エージェントのサービス プリンシパルをエージェント ID に変換するための自動またはインプレース移行パスはありません。 Copilot StudioでMicrosoft Entra エージェント IDを使用するには、**エージェント ID 統合を有効にして新しいエージェント**を作成し、エージェントを手動で再構成してから、レガシ エージェントを使用停止にする必要があります。 この記事では、その再作成して非推奨にするプロセスについて説明します。

### 前提条件

開始する前に、次の要件を満たしていることを確認してください。

- 2026 年 3 月 18 日より前、またはテナントが Microsoft Entra エージェント ID にオプトインする前に作成され、従来のサービス プリンシパルを使用する 1 つ以上の Copilot Studio エージェント。
- **Copilot Studio管理センター**および**Microsoft Entra 管理センター**にアクセスします。
- エージェント ID を管理するためのMicrosoft Entraの **Agent ID Developer** ロールまたは **Agent ID Administrator** ロール。
- Microsoft Entra エージェント ID統合は、Copilot Studioのテナントに対して有効になっています。
- [Microsoft Entra エージェント IDの主要な概念に関する](https://learn.microsoft.com/ja-jp/entra/agent-id/key-concepts)知識。

#### ライセンス要件

Microsoft Entra エージェント IDは、エージェント ID とエージェント ID ブループリントを作成および管理するためのプラットフォームを提供する、Microsoft Entra内の製品です。 エージェント ID は、すべてのMicrosoft Entraユーザーが使用できます。

[Microsoft Agent 365](https://learn.microsoft.com/ja-jp/microsoft-agent-365/overview) を使用すると、エージェントはMicrosoft 365サービスとエンタープライズ ワークフロー全体で動作できます。これには、ユーザーごとに **Microsoft Agent 365** ライセンスが必要です。 価格の詳細については、「[Microsoft Agent 365 のプランと価格](https://www.microsoft.com/microsoft-agent-365#plans-and-pricing)を参照してください。

Microsoft Entraセキュリティ機能をエージェントに拡張するには、Microsoft Agent 365 が必要です。 エージェント 365 は Microsoft 365 E7 に含まれており、Microsoft E5/A5/Business Premium (または Microsoft Defender スイート + Microsoft Purview スイート) のアドオンとして使用できます。 詳細については、 [最新の Agent 365 製品条項を参照してください](https://www.microsoft.com/licensing/terms/productoffering/Agent365/EAEAS#clause-2755-h3-1)。

### Microsoft Entra エージェント IDを採用する理由

レガシ サービス プリンシパルを使用するエージェントCopilot Studioは、Microsoft Entra エージェント IDが提供するガバナンス機能を利用しません。 Microsoft Entra エージェント IDを採用することで、組織は次の情報を取得します。

エージェント ID は、ユーザーとワークロードと同じガバナンス モデルの下にエージェントを取り込みます。 エージェント ID に移動すると、次の情報が提供されます。

**アクセスの制御**

- **条件付きアクセスの適用。** ユーザーの場合と同様に、条件付きアクセス ポリシーとコントロールをエージェントとアクセスするリソースに直接適用します。

**エージェント ガバナンス**

- **ライフサイクル管理。** Microsoft Entra ID ガバナンスはエージェント アクセス ライフサイクル ポリシーを管理できるため、エージェントは古いアクセス許可を蓄積しません。
- **マルチクラウドの可視性。** Azure、オンプレミス、およびその他のクラウド環境全体で動作するエージェントは、1 つの ID プレーンを介して管理されます。

**可視性と監査**

- **エージェント固有の監査証跡。** 監査とサインインのログでは、エージェントの行動とそれによってアクセスされるリソースを、一般的なアプリ登録ではなく、特定のエージェントの身元に関連付けます。 このアプローチにより、調査と属性が簡単になります。
- **エージェント レジストリの可視性。** エージェントは組織のエージェント レジストリに表示され、盲点ではなく完全なインベントリが提供されます。

**リスクと保護**

- **ID 保護の対象範囲。** ID Protection は、異常なエージェントの動作を監視し、リスクベースの検出と自動修復を提供します。

### Copilot Studio エージェントとカスタム エージェントの違い

Copilot Studio エージェントには、カスタム エージェントとは異なるアプローチが必要です。

| Aspect | カスタム アプリの登録 | Copilot Studio |
| --- | --- | --- |
| **コードを制御するユーザー** | 自分 (開発者) | Copilot Studio プラットフォーム |
| **SP を作成したユーザー** | あなたは、Azure ポータルまたはGraph APIを通じて | Copilot Studio、エージェントを発行する際に自動的に |
| **資格情報の管理** | FIC、マネージド ID、またはクライアント シークレットを構成する | Copilot Studioは、ユーザーに代わって資格情報を管理します |
| **Microsoft Entra エージェント ID アクション** | Graph APIを使用してブループリントとエージェント ID を作成し、コードを更新する | Microsoft Entra エージェント ID統合を有効にして新しいエージェントを作成し、手動で再構成する |
| **ロールバック戦略** | 古いアプリの登録を使用するようにコードを元に戻す | 新しいエージェントが完全に検証されるまで、レガシ エージェントをアクティブのままにします |

Copilot Studioは ID ライフサイクルを管理するため、カスタム エージェントの場合と同様に、ブループリントとエージェント ID を直接作成することはできません。 既存のサービス プリンシパルをエージェント ID に変換するインプレース変換はなく、既存のエージェントを再発行してもエージェント ID は作成されません。 代わりに、Microsoft Entra エージェント ID統合を有効にしてCopilot Studioに新しいエージェントを作成し、すべてのチャネルと接続を手動で再構成してから、レガシ エージェントを使用停止にする必要があります。

### プロセス フェーズを確認する

再作成および非推奨化のプロセスは4つのフェーズで構成されており、各フェーズは前のフェーズを踏まえて進行します。

| 段階 | ゴール | 主要出力 |
| --- | --- | --- |
| **発見** | テナント内のCopilot Studioエージェント関連のすべてのサービス プリンシパルをインベントリします。 | ID メタデータ、エージェント マッピング、サインイン アクティビティ、チャネルデプロイを含むレポートまたはダッシュボード。 |
| **分類** | 各エージェントを使用レベル別に分類します。 | 優先順位付きのアクション プラン: どのエージェントをクリーンアップするか、どのエージェントを Microsoft Entra エージェント ID を使用して再作成するか、どのエージェントをそのまま残すか。 |
| **再作成** | Microsoft Entra エージェント ID統合を有効にして新しいエージェントを作成し、手動で再構成します。 | Microsoft Entra エージェント IDネイティブなエージェント ID を使用して動作する新しいエージェント。 |
| **検証と使用停止** | 新しいエージェントがエンドツーエンドで動作することを確認してから、レガシ エージェントとサービス プリンシパルをセーフガードで廃止します。 | 従来のサービス プリンシパルを削除し、エージェントは Microsoft Entra エージェント ID 上で完全に動作するようになりました。 |

### フェーズ 1: Copilot Studio エージェントを検出する

Copilot Studioエージェントは、サービス プリンシパルに特定のフィンガープリントを残します。 次のシグナルを使用して識別します。

- **Tag patterns:** Copilot Studio は、プロビジョニング中に特定のタグ パターンをサービス プリンシパルに適用します。 最も信頼性の高い検出信号は、 `AgentCreatedBy:CopilotStudio` タグです。 その他の一貫性のあるタグには、`AgenticApp`、`AIAgentBuilder`、`AgenticInstance`、サービス プリンシパルをCopilot Studioの特定のエージェントにリンクする `power-virtual-agents-{agent-id}` タグがあります。
- **Copilot Studio管理センター:** **Copilot Studio 管理センター** で検出されたサービス プリンシパルを相互参照して、関連するエージェント名と ID、最終更新日、およびエージェントの状態を検索します。

Copilot Studioは、エージェントの代わりに認証されるように、エージェントのアプリ登録でプラットフォームで管理される資格情報 (フェデレーション ID 資格情報) を構成します。 Copilot Studioはこれらの資格情報を管理します。 それらを直接検査または管理する必要はありません。

Copilot Studioサービス プリンシパルごとに、次の情報をキャプチャします。

- **ID メタデータ:** 表示名、アプリケーション ID、オブジェクト ID、作成日。
- **Agent mapping:** Copilot Studio内の対応するエージェント名と ID。
- **最終更新日:** エージェントが最後に発行または更新された日時。
- **エージェントの状態:** アクティブ、非アクティブ、または下書き。
- **API のアクセス許可:** 委任されたアクセス許可とアプリケーションのアクセス許可が付与されます。
- **接続参照:** エージェントが使用するコネクタとデータ ソース。
- **チャネルのデプロイ:** エージェントが公開されている Teams、ウェブチャット、またはその他のチャネル。
- **Downstream dependencies:**エージェントのサービス プリンシパルに依存するすべてのワークフロー、Power Automate フロー、またはその他のリソース。

テレメトリ (エージェント セッション数、最後の会話日)、Power Platform 管理センター (環境レベルの展開状態)、Microsoft 365使用状況分析など、より多くのデータ Copilot Studio ソースを使用して検出を強化できます。

### フェーズ 2: Copilot Studio エージェントを分類する

各Copilot Studio エージェントを使用レベルで分類し、移行アプローチを決定します。

| 使用状況 | シグナル | 推奨されるアクション |
| --- | --- | --- |
| **Low** | 30日以上ログインを行っていない、アクティブな会話がない、下書きまたは未公開状態のエージェント。 | クリーンアップ対象の候補。 エージェントとそのサービス プリンシパルを直接使用停止する。移行は必要ありません。 |
| **Medium** | 最近の会話アクティビティ、重要でないビジネス機能、識別可能な所有者。 | Microsoft Entra エージェント ID を使用した再作成の対象。 標準検証を使用してフェーズ 3 を進めます。 |
| **高い** | アクティブな日常会話、エージェントに依存する運用ビジネス ワークフロー、Teams または顧客向けチャネルと統合されます。 | **現時点では再作成しないでください。**運用上重要なエージェントを参照してください。 |

30 日間のサインインしきい値は、Microsoft Entraの既定の保持期間に基づいています。 組織が (Microsoft Sentinel または別の SIEM を通じて) 監査ログの保持期間を延長している場合は、それに応じてしきい値を調整します。

#### 運用に重要なエージェント

使用率が高いCopilot Studioエージェントの場合は、**Microsoftが公式の自動移行パスを提供するまで、エージェントを再作成しないでください**。 自動移行パスはなく、手動の再作成プロセスは運用エージェントにとって重大なリスクを伴います。 公式の自動移行パスが使用可能になるまで、これらのサービス プリンシパル as-is を維持します。

使用率の高いCopilot Studioエージェントに固有のリスクは次のとおりです。

- **チャネルの中断:** Teams チャネルの構成、Web チャットの埋め込み、その他の展開では、既存のサービス プリンシパルのアプリケーション ID が参照されます。 新しいエージェントを作成すると、新しい ID が生成されます。これには、すべてのチャネルデプロイを再構成する必要があります。
- **コネクタの再認可:** 古いサービス プリンシパルに関連付けられた接続参照は、新しい Microsoft Entra エージェント ID で再認可する必要があります。
- **フローの依存関係:** エージェントのサービス プリンシパルを参照する Power Automate フローやその他の統合は、移行中に機能しなくなります。
- **構成の移植性なし:** エージェントのトピック、ナレッジ ソース、および設定は、新しいエージェントで手動で再作成する必要があります。

### フェーズ 3: Microsoft Entra エージェント IDを使用してエージェントを再作成する

Warnung

既存の Copilot Studio サービス プリンシパルから Microsoft Entra エージェント ID ネイティブ エージェント ID へのインプレース移行はサポートされていません。 Microsoft Entra エージェント ID統合が有効になっているCopilot Studioで新しいエージェントを作成する必要があります。 構成、チャネル、および内部 ID は自動的に引き継がされません。

**中程度の使用量**の Copilot Studio エージェントについては、次の 7 段階の再作成手順を実行してください。

#### 手順 1: エージェントを特定する

置き換えるサービス プリンシパル (SP) のCopilot Studioで、対応するエージェントを見つけます。 SP の表示名とアプリケーション ID を、**Copilot Studio 管理センター**のエージェントの一覧と一致させます。

#### 手順 2: 現在の構成を文書化する

変更を加える前に、次のドキュメントを参照してください。

- 既存の SP に対するすべての API アクセス許可。
- 接続参照とその承認状態。
- チャネル展開 (Teams、Web チャットなど)。
- Power Automate プロセスまたはその他のダウンストリーム統合。
- カスタム コネクタの構成。

このドキュメントをチェックリストとして使用して、新しいエージェントのすべての項目を再確立します。

#### 手順 3: Microsoft Entra エージェント ID統合を有効にする

お使いのテナントが Copilot Studio 向けの Microsoft Entra エージェント ID 統合にオプトインしていることを確認してください。 **Copilot Studio管理センター**またはテナントの機能設定を使用して、この設定を確認します。

#### 手順 4: Microsoft Entra エージェント IDを使用して新しいエージェントを作成する

Copilot Studioで、Microsoft Entra エージェント ID統合が有効になっている新しいエージェントを作成します。 この新しいエージェントは、プロビジョニング中にネイティブ エージェント ID を受け取ります。 エージェントのトピック、ナレッジ ソース、および構成を、レガシ エージェントと一致するように再作成します。

Note

既存のエージェントを再発行または再登録すると、Microsoft Entra エージェント ID は作成されません。 Microsoft Entra エージェント IDネイティブ エージェント ID を取得するには、新しいエージェントを作成する必要があります。

#### 手順 5: 新しいエージェント ID を検証する

Microsoft Entra 管理センター の Agents 領域に新しいエージェント ID が正しく表示されていることを確認します。 確認：

- エージェント ID は、正しいブループリントにリンクされています。
- 表示名とメタデータは、エージェントと一致します。
- スポンサー (所有者) が正しく割り当てられている。

#### 手順 6: アクセス許可、接続、チャネルを再構成する

レガシ エージェントと一致するように新しいエージェントを手動で構成します。

- API のアクセス許可を割り当てます。
- 接続参照を承認します。
- コネクタ アクセスを構成します。
- チャネル展開 (Teams、Web チャットなど) を設定します。
- **適切な所有者とスポンサーを割り当てます。** 新しいエージェントを作成すると、作成者が既定で所有者になります。 所有権を確認して更新し、適切な個人またはグループがガバナンス目的でエージェントのスポンサーとして指定されていることを確認します。

Important

チャネル構成 (Teams アプリ マニフェスト、Web チャット埋め込みコード) は、エージェントのアプリケーション ID を参照します。 すべてのチャネル統合を更新して、新しいエージェントのIDを参照するようにする必要があります。

#### 手順 7: 運用環境で再作成されたエージェントを監視する

レガシ エージェントの使用停止に進む前に、新しいエージェントを **10 ~ 14 日間** 監視します。 この期間中は、次のことを確認します。

- エージェントの会話が正しく機能します。
- 接続されているすべてのデータ ソースが期待どおりに応答します。
- Microsoft Entra のサインイン ログに認証エラーは表示されません。
- チャネル統合 (Teams、Web チャット) は期待どおりに動作します。

### フェーズ 4: 検証と使用停止

エージェントを再作成した後、新しい ID をエンド ツー エンドで検証し、このフェーズのセーフガードを使用してレガシ サービス プリンシパルを使用停止します。

#### 使用停止前に再作成されたエージェントを検証する

次のチェックリストを使用して、レガシ ID を使用停止する前に、新しいエージェントが正しく機能していることを確認します。

| チェック | 確認方法 | 期待される結果 |
| --- | --- | --- |
| **エージェントの会話** | 構成されているすべてのチャネルを介してテスト メッセージを送信します。 | エージェントは認証エラーなしで正しく応答します。 |
| **接続参照** | 接続されている各データ ソースを使用するアクションをトリガーします。 | すべてのデータ ソースから期待される結果が返されます。 |
| **サインイン ログ** | **Microsoft Entra 管理センター**&gt;**サインイン ログ**、新しいエージェント ID でフィルター処理します。 | 正常な状態の新しいエージェント ID のサインイン イベントが表示されます。 |
| **監査ログ** | **Microsoft Entra 管理センター**&gt;**監査ログ**。 | エージェント ID 作成イベントがログに記録されます。 |
| **チャネルのデプロイ** | 各チャネル (Teams、Web チャットなど) をテストします。 | エージェントは到達可能であり、すべてのチャネルで機能します。 |
| **Power Automate フロー** | エージェントを参照するすべてのフローをトリガーします。 | フローは、新しい ID で正常に実行されます。 |

#### 並列実行（使用量が中程度のエージェントに推奨）

可能であれば、完全に切り替える前に、古いエージェントと新しいエージェントを並行して稼働させてください。 並列検証を実行するには、次の手順に従います。

1. エージェント ID を持つ新しいエージェントの検証中は、古いエージェントを公開し、機能したままにしておきます。
2. ユーザーまたはテスト チャネルのサブセットを新しいエージェントに転送します。
3. 10 日から 14 日間のエラー、会話の失敗、アクセス許可の問題の両方を監視します。
4. 新しいエージェントが完全に検証されたら、使用停止に進みます。

#### セーフガードの廃止

レガシ SP を使用停止する前に、次の手順を実行します。

- **削除前スナップショット:** SP のメタデータ、アクセス許可、監査履歴をエクスポートします。 このエクスポートは、何かが見逃された場合にロールバック参照を提供します。
- **段階的なバッチ:** 複数のCopilot Studio SPを停止する際は、小さなバッチ（段階ごとに20から50 SP）で削除します。
- **Soft-delete first:** Microsoft Entra のソフト削除機能 (30 日間のごみ箱) を使用してから、ハード削除を行います。

#### レガシー エージェントとサービス プリンシパルを廃止する

Warnung

Copilot Studioでエージェントを最初に発行または削除せずにアプリの登録を削除すると、エージェントの認証が中断されます。 Microsoft Entra ID に触れる前に、必ずCopilot Studioでエージェントを使用停止にしてください。

レガシ サービス プリンシパルの利用を停止するには、次の手順に従います。

1. **Copilot Studioでエージェントの発行を取り消すか、削除します。** この手順により、プラットフォームがエージェントに代わって認証を停止します。
2. 古いアプリの登録から API アクセス許可を削除します。
3. 古いアプリの登録時にクライアント シークレットと証明書を削除またはローテーションします。
4. アプリの登録を削除すると、30日間のソフト削除状態に入ります。
5. 30 日後、必要に応じて、完全な削除またはハード削除を確認します。

### 一般的な問題のトラブルシューティング

次の表は、再作成および非推奨化のプロセス中に発生する可能性がある一般的な問題と、その解決方法を示しています。

| 現象 | 考えられる原因 | Resolution |
| --- | --- | --- |
| エージェントの作成後、新しいエージェント ID が **Microsoft Entra 管理センター** に表示されない | テナントに対してエージェント ID 統合が有効になっていない可能性があります。 | エージェント ID 統合のテナント オプトインを確認します。 エージェントをもう一度作成してみてください。 問題が解決しない場合は、Copilot Studioサポートにお問い合わせください。 |
| 切り替え後にエージェントが応答を停止する | チャネル構成では、引き続き古い SP のアプリケーション ID が参照されます。 | 新しいエージェントの ID を参照するようにチャネル構成を更新します。 Teams アプリ マニフェストと Web チャット埋め込みコードを確認します。 |
| 接続参照エラー | コネクタは古い SP に対して承認されており、新しい ID で再認証する必要があります。 | 新しいエージェントの ID を使用して、Copilot Studio内の各接続参照を再認証します。 |
| Power Automate のフローが失敗しました | フローは、古い SP のアプリケーション ID またはサービス プリンシパルを参照します。 | 新しいエージェントの ID を使用するようにフロー接続とトリガーを更新します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/migrate-custom-app-registrations-to-agent-id"} -->
## カスタム アプリ登録をエージェント ID に移行する - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/migrate-custom-app-registrations-to-agent-id
- Service: entra-id / agent-id
- Article date: 2026-06-15
- Summary: エージェント固有のガバナンスとセキュリティのために、AI エージェントを標準のMicrosoft Entra アプリ登録からエージェント ID に移行する方法について説明します。

標準のMicrosoft Entraアプリの登録またはサービス プリンシパルを使用して AI エージェントを構築した場合、エージェントは他のアプリケーションと同様に認証されます。 条件付きアクセス ポリシー、一元化された監査ログ、エージェント ID が提供するライフサイクル管理などのエージェント固有のガバナンス機能は利用されません。

この記事では、コードと ID 構成を所有するエージェントをエージェント ID に移行する方法について説明します。 エージェント ID リソース (ブループリントとエージェント ID) を作成し、アプリケーション コードを更新し、古い ID を使用停止にします。 Microsoft Copilot Studioを使用して作成されたエージェントについては、「[エージェント ID にCopilot Studioエージェントを移行する](https://learn.microsoft.com/ja-jp/entra/agent-id/migrate-copilot-studio-agents-to-agent-id)を参照してください。

### 前提条件

- 認証にアプリ登録またはサービス プリンシパルを使用する既存の AI エージェント。
- ブループリントとエージェント ID を作成するためのエージェント **ID 開発者**またはエージェント **ID 管理者**ロール。
- **特権ロール管理者**のロールはMicrosoft Graphアプリケーションのアクセス許可を付与します。
- Microsoft Graph v1.0 API へのアクセス。
- エージェント ID の主要概念に関する知識。 詳細については、「 [エージェント ID の概念」を](https://learn.microsoft.com/ja-jp/entra/agent-id/key-concepts)参照してください。

#### ライセンス要件

Microsoft Entra エージェント IDは、エージェント ID とエージェント ID ブループリントを作成および管理するためのプラットフォームを提供する、Microsoft Entra内の製品です。 エージェント ID は、すべてのMicrosoft Entraユーザーが使用できます。

[Microsoft Agent 365](https://learn.microsoft.com/ja-jp/microsoft-agent-365/overview) を使用すると、エージェントはMicrosoft 365サービスとエンタープライズ ワークフロー全体で動作できます。これには、ユーザーごとに **Microsoft Agent 365** ライセンスが必要です。 価格の詳細については、「[Microsoft Agent 365 のプランと価格](https://www.microsoft.com/microsoft-agent-365#plans-and-pricing)を参照してください。

Microsoft Entraセキュリティ機能をエージェントに拡張するには、Microsoft Agent 365 が必要です。 エージェント 365 は Microsoft 365 E7 に含まれており、Microsoft E5/A5/Business Premium (または Microsoft Defender スイート + Microsoft Purview スイート) のアドオンとして使用できます。 詳細については、 [最新の Agent 365 製品条項を参照してください](https://www.microsoft.com/licensing/terms/productoffering/Agent365/EAEAS#clause-2755-h3-1)。

### 移行の利点を確認する

Microsoft Entra エージェント IDでは、AI エージェントの認証と承認のための特殊な ID コンストラクトが提供されます。 エージェント ID ブループリントはガバナンス テンプレートとして機能し、エージェント ID は、エージェントが認証、トークンの取得、リソースへのアクセスに使用するランタイム プリンシパルです。 エージェント ID に移行すると、次の情報が得られます。

エージェント ID は、ユーザーとワークロードと同じガバナンス モデルの下にエージェントを取り込みます。 エージェント ID に移動すると、次の情報が提供されます。

**アクセスの制御**

- **条件付きアクセスの適用。** ユーザーの場合と同様に、条件付きアクセス ポリシーとコントロールをエージェントとアクセスするリソースに直接適用します。

**エージェント ガバナンス**

- **ライフサイクル管理。** Microsoft Entra ID ガバナンスはエージェント アクセス ライフサイクル ポリシーを管理できるため、エージェントは古いアクセス許可を蓄積しません。
- **マルチクラウドの可視性。** Azure、オンプレミス、およびその他のクラウド環境全体で動作するエージェントは、1 つの ID プレーンを介して管理されます。

**可視性と監査**

- **エージェント固有の監査証跡。** 監査とサインインのログでは、エージェントの行動とそれによってアクセスされるリソースを、一般的なアプリ登録ではなく、特定のエージェントの身元に関連付けます。 このアプローチにより、調査と属性が簡単になります。
- **エージェント レジストリの可視性。** エージェントは組織のエージェント レジストリに表示され、盲点ではなく完全なインベントリが提供されます。

**リスクと保護**

- **ID 保護の対象範囲。** ID Protection は、異常なエージェントの動作を監視し、リスクベースの検出と自動修復を提供します。

### 移行フェーズを確認する

エージェント ID への移行は、単一ステップの切り替えではなく、段階的なプロセスです。 このガイドでは、プロセスを 4 つのフェーズに編成し、それぞれ前のフェーズの出力を基にしています。 段階的なモデルにより、アクティブなワークロードをサポートする可能性のあるサービス プリンシパルが早期に削除されるのを防ぐことができます。 移行または使用停止アクションの前に、適切な検出と分類が行われます。

| 段階 | ゴール | 主要出力 |
| --- | --- | --- |
| **発見** | テナント内のすべてのエージェント関連の ID (サービス プリンシパル、アプリの登録、およびその使用状況シグナル) のインベントリを作成します。 | 各エージェントの ID メタデータ、サインイン アクティビティ、アクセス許可、所有権、ビルダーの配信元を含む構造化されたレポートまたはダッシュボード。 |
| **分類** | 使用レベルと配信元ごとに各 ID を分類します。 | 優先順位付けされたアクション プラン: クリーンアップするID、移行するID、そのまま残すID。 |
| **移行** | エージェント ID リソース (ブループリント + エージェント ID) を作成し、それらを使用するようにエージェントを更新します。 | エージェント ID で実行され、アクセス許可と資格情報が一致する新しいエージェント ID の ID。 |
| **検証と使用停止** | 新しい ID がエンドツーエンドで機能することを確認し、必要に応じて並列実行を行い、安全措置を講じてレガシ ID を廃止します。 | レガシIDが削除され、エージェントはエージェントIDに完全に依存しています。 |

### フェーズ 1: 既存のアプリ登録を検出する

何かを移行する前に、テナント内のエージェント関連 ID の完全なインベントリを作成します。 組織全体のスイープから始めてすべての移行候補を見つけ、次に個々のエージェントに絞り込んで、移行中に必要な構成の詳細をキャプチャします。

#### 組織レベルのインベントリを作成する

AI エージェントを表す可能性のあるすべてのサービス プリンシパルについてテナントをスキャンします。 目標は、使用状況シグナルを表示し、次のフェーズで各 ID を分類するのに役立つ構造化されたレポートを作成することです。

候補のサービス プリンシパルごとに、次の情報をキャプチャします。

- **ID メタデータ:** 表示名、アプリケーション ID、オブジェクト ID、作成日、テナント。
- **所有権:** 割り当てられた所有者、所有チームまたは部門 (使用可能な場合)。
- **サインイン アクティビティ:** 最後の対話型サインインと非対話型サインイン、サインイン回数は 30 日、90 日、180 日を超えています。
- **監査ログ通知:** サービス プリンシパルを作成したソース システム、作成イベント、最近の変更、またはアクセス許可の変更。
- **タグ分析:** サービス プリンシパルに適用されるタグ。ビルダー固有のタグ パターンが含まれていないかどうかを確認します。
- **API のアクセス許可:** 委任されたアクセス許可と付与されたアプリケーションのアクセス許可、管理者の同意状態、アクセス許可の秘密度レベル。
- **その他の ID 構成:** 使用される資格情報、OAuth フロー、カスタム属性、RBAC ロールの割り当て、リダイレクト URI、およびダウンストリームの依存関係。 エージェント ID として ID を正しく再作成するには、移行中にこれらの構成の詳細が必要です。

ヒント

構成の詳細のほとんどは、Microsoft Graphを使用してプログラムでプルできます。 `GET /applications`エンドポイントと`GET /servicePrincipals` エンドポイントを使用して、アクセス許可、資格情報、所有権などをプルします。 アプリケーションで使用される OAuth フローとダウンストリームの依存関係については、アプリケーション コードを確認します。

#### ログとアクセス許可の分析を使用してエージェントを識別する

タグを持たないサービス プリンシパルの場合は、動作シグナルと構成シグナルを使用して、可能性の高いエージェント ID を識別します。 1つの信号は確定的でない。 それらを組み合わせて重み付けして、信頼度スコアを生成します。

| シグナル カテゴリ | 注目すべきポイント | エージェントを示す理由 |
| --- | --- | --- |
| **API のアクセス許可** | Bot Framework、Azure OpenAI、Azure AI Services、または Cognitive Services API を対象とするアプリケーションのアクセス許可。 | エージェントは、これらの API を機能させる必要があります。従来のアプリケーションでは、それらを一緒に要求することはめったにありません。 |
| **リダイレクトURI** | `token.botframework.com`、`botframework.com`、またはAzure Bot Serviceエンドポイントを指す URI。 | これらの URI は、会話エージェントによって排他的に使用される Bot Framework コールバック URL です。 |
| **サインイン パターン** | 頻度が高く、ユーザー コンテキストが関連付けられていない非対話型サインインまたはサービス プリンシパル サインイン。 | エージェントは自律的に認証されます。通常、人間向けのアプリには対話型サインイン アクティビティがあります。 |
| **トークンの対象ユーザー** | `https://api.botframework.com`、Azure OpenAI エンドポイント、または AI サービス エンドポイントに対して要求されたトークン。 | トークンの対象ユーザーは、サービス プリンシパルが呼び出しているリソースを明らかにします。 |
| **リソース グループの関連付け** | Bot Service、Azure OpenAI、または AI Search リソースを含むリソース グループにリンクされているサービス プリンシパル。 | AI インフラストラクチャとの併配置は、サービス プリンシパルがエージェント ワークロードにサービスを提供するよう提案します。 |
| **名前付け規則** | `agent`、`bot`、`copilot`、`assistant`、`orchestrator`などの用語を含む表示名。 | 決定論的ではありませんが、名前付けパターンは、他のシグナルと組み合わせた場合に、エージェントワークロードと強く相関します。 |

ヒューリスティック検出を運用可能にするには、Microsoft Graphを使用してサービス プリンシパルのサインイン ログとアクセス許可付与をプログラムで照会します。 (Microsoft Sentinelまたは別の SIEM を使用して) ログリテンション期間が延長されたテナントの場合は、Microsoft Entraの既定の 30 日間のサインインリテンション期間を超えて分析ウィンドウを展開し、信号の精度を向上させます。

ヒューリスティック検出では、確認済みのエージェントではなく、候補が生成されます。 分類に進む前に、すべての一致を確認してください。 Azure OpenAI を呼び出すが自律的なエージェントではないバックエンド マイクロサービスなど、誤検知が予想されます。

#### 組織のナレッジ ソースを使用してエージェントを検出する

自動化されたメソッドでは、すべてのエージェントが見つからない場合があります。特に、汎用 API アクセス許可を持ち、名前付け規則の重複がないカスタム構築エージェントは、タグベースのヒューリスティック スキャンでは見えません。 そのため、次の点を考慮する必要があります。

**CMDB と資産インベントリの調整**

検出されたサービス プリンシパルを、組織の構成管理データベースまたはアプリケーション ポートフォリオ レジストリに対して相互参照します。 CMDB の "AI エージェント"、"チャットボット"、または "仮想アシスタント" ワークロードの種類として登録されているアプリケーションは、対応するサービス プリンシパルと照合し、移行インベントリに追加する必要があります。

**開発者の自己証明**

多数のアプリ登録があるテナントの場合は、移行インベントリをアプリケーション所有者に発行し、サービス プリンシパルが AI エージェントを表しているかどうかを証明するように依頼します。 このアプローチでは、アプリケーション所有者がワークロードの種類に関する明確な知識を持っているため、自動スキャンで実現できる領域を超えて検出をスケーリングします。 自己証明を期限とエスカレーション パスと組み合わせて、完了を促進します。

#### 推奨される検出シーケンス

次のメソッドを順番に実行します。

1. **タグベースのスキャン:** タグ付けされたすべてのエージェントを自動的に識別します。 これらのエージェントは、確認された候補です。
2. **ヒューリスティック分析:** 残りのタグ付けされていないサービス プリンシパルをスキャンして、動作シグナルを検出します。 信頼度の高い一致に候補としてフラグを設定します。
3. **CMDB 調整:** 残りのサービス プリンシパルを資産インベントリと照合して、明確でない名前で登録されたエージェントをキャッチします。
4. **開発者の宣誓:** 最終的な分類のために、未一致項目の残差リストをアプリケーションオーナーに提供します。

### フェーズ 2: 移行用のエージェント ID を分類する

検出レポートを使用して、各サービス プリンシパルを使用レベル別に分類し、移行の進め方を決定します。

| 使用状況 | シグナル | 推奨されるアクション |
| --- | --- | --- |
| **Low** | 30 日以上のサインイン アクティビティなし、所有者の割り当てなし、アクティブな使用での API アクセス許可なし。 | クリーンアップ対象の候補。 移行をスキップして、直接サービスを終了します（フェーズ 4）。 |
| **Medium** | 最近のサインイン アクティビティ、運用以外のアクセス許可、識別可能な所有者。 | 移行の候補。 標準検証を使用してフェーズ 3 ~ 4 を進めます。 |
| **高い** | 頻繁なサインイン、運用 API のアクセス許可、SP に依存するアクティブなワークフロー。 | 移行には細心の注意を払ってください。 拡張並列実行 (フェーズ 4)、ロールバック 計画、利害関係者のサインオフが必要です。 |

30 日間のサインインしきい値は、Microsoft Entraの既定の保持期間に基づいています。 組織がMicrosoft Sentinelまたは別の SIEM を通じて監査ログのリテンション期間を保持している場合は、それに応じてしきい値を調整します。

### フェーズ 3: アプリの登録をエージェント ID に移行する

このフェーズでは、ID が標準のアプリ登録またはサービス プリンシパルとして作成されたエージェントの技術的な移行手順について説明します。 新しいエージェント ID リソースを作成し、2 段階のトークン取得モデルを使用するようにアプリケーション コードを更新します。 手順 1 から 3 は、Microsoft Entra テナントを対象とします。 手順 4 では、アプリケーション コードの変更を対象とします。

Important

既存のアプリ登録またはサービス プリンシパルをエージェント ID に変換するインプレース変換はありません。 移行するには、既存の ID と共に新しいエージェント ID を作成し、それを使用するようにエージェントを移行する必要があります。

#### 手順 1: エージェント ID ブループリントを作成する

この手順を完了するには、ブループリントとエージェント ID を作成するために必要なのは、**Agent ID Developer** ロールまたは **Agent ID Administrator** ロールです。また、Microsoft Graph アプリケーションのアクセス許可を付与するには、**Privileged Role Administrator** ロールが必要です。

ブループリントは、エージェントのガバナンス テンプレートです。 その下に作成されたすべてのエージェント ID が継承する資格情報とアクセス許可ポリシーを定義します。

Microsoft Graphを使用してブループリントを作成します。

```http
POST https://graph.microsoft.com/v1.0/applications/microsoft.graph.agentIdentityBlueprint
Content-Type: application/json

{
    "@odata.type": "Microsoft.Graph.AgentIdentityBlueprint",
    "displayName": "My Agent Blueprint",
    "sponsors@odata.bind": [
        "https://graph.microsoft.com/v1.0/users/<sponsor-user-id>"
    ],
    "owners@odata.bind": [
        "https://graph.microsoft.com/v1.0/users/<owner-user-id>"
    ]
}
```

または PowerShell を使用する:

```powershell
$body = @{
    "@odata.type" = "Microsoft.Graph.AgentIdentityBlueprint"
    "displayName" = "My Agent Blueprint"
    "sponsors@odata.bind" = @("https://graph.microsoft.com/v1.0/users/<sponsor-user-id>")
    "owners@odata.bind" = @("https://graph.microsoft.com/v1.0/users/<owner-user-id>")
} | ConvertTo-Json -Depth 5

Invoke-MgGraphRequest `
    -Method POST `
    -Uri "https://graph.microsoft.com/v1.0/applications/microsoft.graph.agentIdentityBlueprint" `
    -Headers @{ "OData-Version" = "4.0" } `
    -Body $body `
    -ContentType "application/json"
```

Important

個々のエージェント ID ではなく、ブループリントで資格情報を作成します。 エージェント ID を作成する前に、ブループリントでフェデレーション ID 資格情報を構成します。

##### ブループリントで資格情報を構成する

エージェント ID は、推奨される資格情報の種類としてフェデレーション ID 資格情報 (FIC) を使用します。 マネージド ID は、シークレット管理を排除するため、運用環境のデプロイに推奨されるオプションです。 運用環境では、クライアント シークレットと証明書もサポートされています。 移行作業を減らすには、既存のアプリ登録で使用するのと同じ資格情報の種類を再利用することを検討してください。

推奨される資格情報は、ホスティング環境によって異なります。

| ホスティング環境 | 推奨される資格情報 | Notes |
| --- | --- | --- |
| Azure (App Service、AKS、Container Apps、VM) | ユーザー指定のマネージド ID | 運用環境で最も安全です。 シークレット管理は必要ありません。 |
| 非Azure クラウド (AWS、GCP) | 外部 IDP を使用したフェデレーション ID 資格情報 | クラウド プロバイダーとのワークロード ID フェデレーションを構成します。 |
| オンプレミスまたはローカル開発 | クライアント シークレットまたは証明書 | 運用環境のオンプレミスデプロイでは、クライアント シークレットよりも証明書をお勧めします。 ローカル テストにのみクライアント シークレットを使用します。 |

資格情報を構成したら、ブループリントのサービス プリンシパルを作成します。 ブループリントでエージェント ID を作成する前に、サービス プリンシパルが必要です。

```http
POST https://graph.microsoft.com/v1.0/servicePrincipals
Content-Type: application/json

{
    "appId": "<blueprint-app-id>"
}
```

#### 手順 2: エージェント ID を作成する

この手順を完了するには、 **エージェント ID の開発者** ロールまたは **エージェント ID 管理者ロールが** 必要です。

エージェント ID は、エージェントが使用するランタイム プリンシパルです。 ブループリントの下に作成し、 **スポンサー** (必須)、人間のユーザー、またはエージェントの責任を負うグループを割り当てます。 管理目的で **所有者** を割り当てることをお勧めします。

```http
POST https://graph.microsoft.com/beta/serviceprincipals/Microsoft.Graph.AgentIdentity
OData-Version: 4.0
Content-Type: application/json

{
    "displayName": "Customer Service Agent - Production",
    "agentIdentityBlueprintId": "<blueprint-app-id>",
    "sponsors@odata.bind": [
        "https://graph.microsoft.com/v1.0/users/<sponsor-user-id>"
    ]
}
```

応答からエージェント ID のクライアント ID を記録します。 この値は、アプリケーション コードを更新するときに必要です。

#### 手順 3: アクセス許可を構成する

元のアプリ登録からエージェント ID に API アクセス許可をレプリケートします。 この手順を完了するには、管理者の同意を必要とするアプリケーションのアクセス許可を付与する **特権ロール管理者** ロールが必要です。 次の 2 つのオプションがあります。

- **直接割り当て:** エージェント ID に直接アクセス許可を割り当てます。 このオプションは、各エージェント ID に異なるアクセス許可が必要な場合に使用します。
- **継承されたアクセス許可:** ブループリントに対するアクセス許可を構成し、継承を有効にします。 このオプションは、ブループリントの下にあるすべてのエージェント ID が同じアクセス許可を共有する場合に使用します。

Microsoft Graph アプリケーションのアクセス許可を複製するには、`appRoles` API を使用して、同じ  をエージェントのサービス プリンシパルに付与します。 委任されたアクセス許可の場合は、`oauth2PermissionGrants` API の作成を使用して、必要なを追加します。 必要なアプリケーションのアクセス許可に対して管理者の同意が付与されていることを確認します。

##### OBO サポートの構成 (対話型エージェントのみ)

エージェントがユーザーの代わりに動作するために On-Behalf-of (OBO) フローを使用する場合は、フロントエンド アプリケーションがトークンを要求できるように、ブループリントにカスタム スコープを追加する必要があります。 スコープを追加した後、フロントエンド アプリケーションを更新して、前のリソースではなくブループリントのリソースのトークンを要求します。 OBO 構成の完全なチュートリアルについては、 [対話型エージェントの認証と承認フロー](https://learn.microsoft.com/ja-jp/entra/agent-id/interactive-agent-authentication-authorization-flow)に関する記事を参照してください。

元のアプリの登録に RBAC ロールの割り当てがAzureされている場合は、新しいエージェント ID のサービス プリンシパルに同じロールを割り当てて、実行時に必要なAzure リソースにアクセスできるようにします。

```powershell
New-AzRoleAssignment `
    -ObjectId "<agent-identity-service-principal-id>" `
    -RoleDefinitionName "Contributor" `
    -Scope "/subscriptions/<sub-id>/resourceGroups/<rg-name>"
```

#### 手順 4: アプリケーション コードを更新する

エージェント ID は、2 段階のトークン取得モデルを使用します。 エージェントは、最初にブループリントの資格情報を使用してブートストラップ トークンを取得してから、エージェント固有のトークンと交換します。 コードの更新の詳細については、次のシナリオを参照してください。

- トークン取得の完全なチュートリアル用の[自律エージェント認証と承認フロー](https://learn.microsoft.com/ja-jp/entra/agent-id/autonomous-agent-authentication-authorization-flow)。
- [対話型エージェントのガイダンス、](https://learn.microsoft.com/ja-jp/entra/agent-id/interactive-agent-authentication-authorization-flow) コード サンプル、およびトークン構成のための対話型エージェントの認証と承認フロー。 これらのエージェントのトークン フローは、ユーザー トークンがユーザー コンテキストを持つエージェント トークンと交換される OBO 交換を追加します。

### フェーズ 4: 検証と使用停止

エージェント ID を移行した後、古い ID を使用停止にする前に、新しいエージェント ID が正しく動作することを検証します。

#### 移行されたエージェント ID を検証する

| チェック | 確認方法 | 期待される結果 |
| --- | --- | --- |
| **トークンの取得** | エージェントを実行し、トークンの応答を調べます。 | エージェントは、2 段階のフローを使用してトークンを正常に取得します。 認証エラーはありません。 |
| **API アクセス** | エージェントが行うすべての API 呼び出しを実行します。 | すべてのダウンストリーム API は、期待される結果を返します。 401/403 エラーはありません。 |
| **サインイン ログ** | **Microsoft Entra 管理センター**&gt;**サインイン ログ**、新しいエージェント ID でフィルター処理します。 | 正常な状態の新しいエージェント ID のサインイン イベントが表示されます。 |
| **監査ログ** | **Microsoft Entra 管理センター**&gt;**監査ログ**。 | エージェント ID の作成イベントとアクセス許可の割り当てイベントがログに記録されます。 |
| **条件付きアクセス** | リソースを対象とする CA ポリシーを確認します。 | CA ポリシーは、新しいエージェント ID を正しく評価します。 予期しないブロックはありません。 |

#### 古い ID と新しい ID を並列で実行する

使用率が高いエージェントの場合は、切り替える前に、古い ID と新しい ID を並べて実行します。

1. 新しいエージェント ID で構成されたエージェントの並列インスタンスをデプロイします。
2. トラフィックの割合を新しいインスタンスにルーティングします (5 から 10%で始まります)。
3. 両方のインスタンスでエラー、待機時間の違い、アクセス許可の問題を監視します。
4. 新しいインスタンスへのトラフィックを 1 週間から 2 週間かけて徐々に増やします。
5. 100% のトラフィックが新しい ID で実行されたら、使用停止に進みます。

機能フラグまたはトラフィック分割インフラストラクチャを使用してロールアウトを制御します。 この方法では、問題が発生した場合にすぐに古い ID に戻すことができます。

#### セーフガードの廃止

新しいエージェント ID を検証し、運用トラフィックを伝達したら、古い ID を使用停止します。 リスクを最小限に抑えるには、次のセーフガードに従います。

- **削除前スナップショット:** 削除する前に、サービス プリンシパルのメタデータ、アクセス許可、および監査履歴をエクスポートします。 このエクスポートは、検証中に何かが見逃された場合にロールバック参照を提供します。
- **段階的なバッチ:** 複数の ID を使用停止する場合は、一括削除するのではなく、小さなバッチ (ウェーブあたり 20 から 50 個のサービス プリンシパル) で削除します。
- **まず論理削除:** Microsoft Entra の論理削除機能（アプリケーションの場合、30日間保持されるごみ箱）を利用し、続いてハード削除を実行します。 この手順により、復旧期間が提供されます。

#### 元のアプリの登録を使用停止する

古い ID を使用停止にするには、次の手順に従います。

1. 古いアプリの登録から API アクセス許可を削除します。
2. 古いサービス プリンシパルのAzure RBACロールの割り当てを取り消します。
3. 古いアプリの登録時にクライアント シークレットと証明書を削除またはローテーションします。
4. アプリの登録を削除すると、30日間のソフト削除状態に入ります。
5. 30 日後に、アプリの登録が完全に削除されるか、必要に応じてハード削除されていることを確認します。

### 一般的な問題のトラブルシューティング

次の表に、移行中に発生する可能性がある一般的な問題とその解決方法を示します。

| 現象 | 考えられる原因 | Resolution |
| --- | --- | --- |
| トークンの交換に失敗しました`invalid_grant` | ブループリントのフェデレーション ID 資格情報が正しく構成されていないか、マネージド ID が正しくリンクされていません。 | FIC のサブジェクト、発行者、および対象ユーザーがホスティング環境と一致するかどうかを確認します。 マネージド ID に正しい割り当てがあることを確認します。 |
| 対象ユーザーの不一致エラー | トークン要求では、間違った対象ユーザーが指定されているか、ブループリントの対象ユーザーがターゲット リソースと一致しません。 | トークン要求の対象ユーザーが、呼び出しているリソースと一致していることを確認します。 Microsoft Graphの場合は、`https://graph.microsoft.com/.default` を使用します。 |
| `403 Forbidden` API コール時において | API のアクセス許可が、古いアプリの登録からエージェント ID に正しくレプリケートされませんでした。 | 両方の ID のアクセス許可を比較します。 アプリケーションのアクセス許可に対して管理者の同意が付与されていることを確認します。 |
| OBO フローが失敗する | エージェント ID が委任されたアクセス許可用に構成されていないか、ユーザー トークンに必要なスコープが含まれていません。 | 委任されたアクセス許可が割り当てられているかどうかを確認します。 ユーザー トークンに、OBO 交換に必要なエージェントのスコープが含まれていることを確認します。 |
| `400 Bad Request: Object not found` (ブループリントの作成後) | 書き込み後の読み出し一貫性の遅延。 ブループリントまたはエージェント ID は、後続の API 呼び出しではまだ使用できません。 | ブループリントを作成してから 30 ~ 60 秒待ってから、エージェント ID またはサービス プリンシパルを作成します。 指数バックオフを使用して再試行ロジックを実装します。 |
| エージェント ID が Microsoft Entra 管理センター | API を使用して作成されたエージェント ID がポータルに表示されるまでに数分かかる場合があります。 | 待機して更新します。 それでも ID が表示されない場合は、Graph APIを通じて正常に作成されたことを確認します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/secure-mcp-server-with-entra-id"} -->
## Microsoft Entra IDを使用してモデル コンテキスト プロトコル (MCP) サーバーをセキュリティで保護する - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/secure-mcp-server-with-entra-id
- Service: entra-id / agent-id
- Article date: 2026-09-01
- Summary: Microsoft Entra IDを使用してモデル コンテキスト プロトコル (MCP) サーバーを OAuth 2.0 で保護されたリソースとしてセキュリティで保護し、Microsoft Entra エージェント IDを使用して MCP クライアントを接続する方法について説明します。

[モデル コンテキスト プロトコル (MCP)](https://modelcontextprotocol.io) を使用すると、AI エージェントやその他のクライアントは、MCP サーバーから公開するツールとデータ ソースを呼び出すことができます。 MCP サーバーはユーザーまたは自律エージェントに代わって動作できるため、他の API と同様に保護する必要があります。すべての要求で OAuth 2.0 アクセス トークンを要求し、ツールを実行する前にそのトークンを検証する必要があります。

この記事では、MCP サーバーの承認サーバーとしてMicrosoft Entra IDを構成し、MCP クライアントがトークンを要求できるようにサーバーを登録し、[Microsoft Entra エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id)を使用して MCP クライアントを接続する方法について説明します。

Important

独自のトークン検証ロジックを最初から記述しないでください。 トークン検証のバグにより、MCP サーバーが承認されていない呼び出し元にサイレント モードで公開される可能性があります。 プラットフォームに対して十分にテストされた認証ライブラリまたはミドルウェアを使用し、「 MCP サーバーのアクセス トークンの検証」にリンクされている信頼のソース ガイダンスに従います。

### 前提条件

- アプリケーションを登録できる**Microsoft Entra テナント**。 アプリを登録するには、少なくとも [アプリケーション開発者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer) ロールが必要です。
- `https://mcp.contoso.com` など、HTTPS URL でホストする **MCP サーバー**。
- OAuth 2.1、OAuth [2.0 のリソース インジケーター (RFC 8707)、OAuth 2.0](https://www.rfc-editor.org/rfc/rfc8707)[保護されたリソース メタデータ (RFC 9728](https://www.rfc-editor.org/rfc/rfc9728)) に基づく [MCP 承認仕様](https://modelcontextprotocol.io/specification/basic/authorization)に関する知識。

### Microsoft Entra IDでの MCP 承認のしくみ

MCP 承認モデルでは、次の手順を実行します。

- **MCP サーバー**は OAuth 2.0 *で保護されたリソースです*。
- **Microsoft Entra ID**は、アクセス トークンを発行する*承認サーバー*です。
- **エージェントなどの MCP クライアント**は、トークンを要求してサーバーに提示する*クライアント*です。

MCP 仕様では、クライアントはトークン要求で (RFC 8707 から) `resource` パラメーターを送信することによって、トークンが必要な保護されたリソースを識別する必要があります。 `resource`の値は、MCP サーバーの正規 URL です。 Microsoft Entra IDは、その`resource`値を、MCP サーバーのアプリ登録で構成された**アプリケーション ID URI** (識別子 URI とも呼ばれます) と比較します。 一致する場合、Microsoft Entra ID は、その audience (`aud`) クレームの値がご使用の MCP サーバーであるトークンを発行します。 一致しない場合、トークン要求は失敗します。

この照合を機能させるには、MCP サーバーが **v2 アクセス トークンを**受け入れる必要があります。 アクセス トークンのバージョンを構成する正確な手順については、「Microsoft Entra IDで MCP サーバーを登録する」を参照してください。

エンド ツー エンドフローは次のようになります。

1. MCP クライアントは、サーバーの承認要件を検出します。 認証されていないリクエストが到着すると、サーバーは、`HTTP 401` を指す `WWW-Authenticate` ヘッダーを含む  を返します。これにより、認可サーバーが Microsoft Entra ID であることが識別されます。
2. MCP クライアントは、Microsoft Entra IDからアクセス トークンを要求し、`resource=<your MCP server URL>`を送信します。
3. Microsoft Entra IDは、要求を検証し、`resource`をアプリ登録のアプリケーション ID URI と照合し、サーバーにスコープが設定された v2 アクセス トークンを発行します。
4. MCP クライアントは、 `Authorization: Bearer` ヘッダー内のトークンを使用して MCP サーバーを呼び出します。
5. MCP サーバーはトークンを検証し、有効な場合は、要求されたツールを実行します。

### MCP サーバーを Microsoft Entra ID に登録する

MCP サーバーをアプリケーションとして登録して、Microsoft Entra IDがトークンを発行できるようにします。

MCP サーバーが、Entra によって既にセキュリティ保護されている既存の Web サービスまたは REST API によって提供されている場合は、既存のアプリ登録とその OAuth スコープを再利用できます。

次の手順の順序が重要です。サーバー URL に一致する HTTPS アプリケーション ID URI を設定する *前に* 、v2 アクセス トークンを有効にする必要があります。

#### 手順 1: アプリの登録を作成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;の**アプリ登録**&gt;に移動し、**新規登録**を選択します。
3. MCP サーバーの名前 ( `Contoso MCP server`など) を入力し、[ **登録**] を選択します。

#### 手順 2: アクセス トークンのバージョンを v2 に設定する

これはほとんどの人が見逃すステップです。 アプリの登録では、Microsoft Graph アプリ マニフェストで`2`に`requestedAccessTokenVersion`を設定して、v2 アクセス トークンを要求[する](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-microsoft-graph-app-manifest#api-attribute)必要があります。

1. アプリの登録で、[マニフェスト] を選択 **します**。
2. `api` オブジェクトで、`requestedAccessTokenVersion`を `2` に設定します。

    ```json
    "api": {
        "requestedAccessTokenVersion": 2
    }
    ```
3. **保存**を選びます。

Note

この手順をスキップして v1 アクセス トークンにアプリを残した場合、MCP サーバーの URL に一致する HTTPS アプリケーション ID URI を設定することはできません。また、 `resource=<your MCP server URL>` を送信する MCP クライアントはエラーを受け取ります。 FAQ とトラブルシューティングを参照してください。

#### 手順 3: アプリケーション ID URI を MCP サーバー URL に設定する

アプリが v2 アクセス トークンを発行した後、アプリケーション ID URI を MCP サーバーの正確な正規 URL に設定します。 この値は、MCP クライアントが `resource` パラメーターで送信する値です。

1. アプリの登録で、[ **API の公開**] を選択します。
2. **[アプリケーション ID URI] の**横にある **[追加]** (または **[編集])** を選択し、MCP サーバーの URL (`https://mcp.contoso.com`など) を入力します。
3. **保存**を選びます。

または、マニフェスト内で `identifierUris` を直接設定します。 `identifierUris` プロパティはリストであるため、MCP サーバーに複数の URL で到達できる場合は、クライアントが使用するすべての URL を追加します。 クライアントは通常、接続先の URL を`resource`値として送信し、Microsoft Entra IDは、その値が登録済みのアプリケーション ID URI のいずれかに正確に一致する場合にのみトークンを発行します。

```json
"identifierUris": [
    "https://mcp.contoso.com",
    "https://mcp.contoso.com/mcp",
    "https://contoso-mcp.azurewebsites.net"
]
```

#### 手順 4: スコープを定義する

委任されたスコープを使用すると、サインインしているユーザーに代わって機能する MCP クライアントは、必要なアクセス レベルのみを要求できます。 MCP サーバーが対話型クライアントにサービスを提供する場合は、少なくとも 1 つの OAuth スコープを定義して、クライアントがサーバーのトークンを要求できるようにし、ユーザーまたは管理者がそれに同意できるようにします。

1. アプリの登録で、[ **API の公開**] を選択します。
2. **スコープの追加** を選択します。
3. `tool.read`や`tool.execute`など、サーバーが公開する操作を反映するスコープ名を入力します。
4. 同意できるユーザー (**管理者のみ**、管理者 **とユーザー**) を選択し、同意の表示名と説明を入力し、状態を **[有効]** に設定して、[ **スコープの追加]** を選択します。

MCP クライアントは、アプリケーション ID URI とスコープ名 (たとえば、 `https://mcp.contoso.com/tool.execute`) を組み合わせた完全修飾スコープを要求します。

MCP サーバーへのきめ細かなアクセスを提供するために、必要な数の OAuth スコープを定義します。

#### 手順 5 (省略可能): アプリ ロールを定義する

MCP クライアントは、ユーザーに代わって動作するだけでなく、エージェント ID として認証することもできます。 アクセス トークンをエージェント ID として要求するために、MCP クライアントは OAuth クライアント資格情報付与要求を送信し、 `scope=https://mcp.contoso.com/.default`などのスコープ値を使用します。

Entra **アプリ ロール** を使用して、MCP クライアントが MCP サーバーに対して持つアクセスを制限できます。 MCP クライアントのエージェント ID にアプリ ロールを割り当てます。 割り当てられたロールはアクセス トークンの `roles` 要求に表示され、サーバーはツールを実行する前にその要求を確認します。

1. アプリの登録で、[**アプリ ロール**] &gt; [**アプリ ロールの作成]** を選択します。
2. 表示名と値 ( `Tools.Execute.All`など) を入力し、[ **許可されるメンバーの種類]** を **[アプリケーション**] に設定して、ロールを有効にします。
3. サーバーを呼び出すエージェント ID (または他のクライアント アプリケーション) にロールを割り当てます。

詳細については、「 [アプリ ロールを追加してトークンで受け取る」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)参照してください。

### 保護されたリソース メタデータを発行する

MCP クライアントは、 [OAuth 2.0 Protected Resource Metadata (PRM)](https://datatracker.ietf.org/doc/html/rfc9728) ドキュメントを読んで、サーバーに対する認証方法を検出します。 MCP 承認仕様では、すべての HTTP MCP サーバーが PRM ドキュメントを提供し、その`401`応答の `WWW-Authenticate` ヘッダーからそれを参照する必要があります。 このドキュメントでは、使用する承認サーバー (Microsoft Entra ID) とトークンを要求するリソース識別子をクライアントに通知します。

`https://mcp.contoso.com/.well-known/oauth-protected-resource`など、サーバーの URL から派生した既知のパスからドキュメントを JSON として提供します。 Microsoft Entra IDによってセキュリティ保護されたサーバーの場合、次のようになります。

```json
{
  "resource": "https://mcp.contoso.com",
  "authorization_servers": [
    "https://login.microsoftonline.com/<tenant-id>/v2.0"
  ],
  "scopes_supported": [
    "tools.read",
    "tools.execute"
  ],
  "bearer_methods_supported": ["header"]
}
```

次のようにフィールドを設定します。

- **`resource`** — MCP サーバーの正規 URL。 この値は、 手順 3 で登録したアプリケーション ID URI と、クライアントが送信する `resource` パラメーターと同じである必要があります。 サーバーが複数の URL を公開している場合、各 PRM ドキュメントには 1 つの特定の URL が記述され、クライアントは接続先の URL のメタデータを要求します。
- **`authorization_servers`**— テナントのMicrosoft Entra ID発行者、`https://login.microsoftonline.com/<tenant-id>/v2.0`。 クライアントは、この発行者 (たとえば、 `https://login.microsoftonline.com/<tenant-id>/v2.0/.well-known/openid-configuration`) に対して承認サーバー メタデータ検出を実行して、Entra の承認エンドポイントとトークン エンドポイントを検索します。 テナント ID または検証済みドメインを使用する。マルチテナント サーバーに対してのみ、テナント ID の代わりに `organizations` または `common` を使用します。
- **`scopes_supported`** — 省略可能。 手順 4 で定義した委任されたスコープを一覧表示します。 クライアントはこれらを使用して、 `WWW-Authenticate` チャレンジで `scope`が指定されていない場合に要求する内容を決定します。
- **`bearer_methods_supported`**— `Authorization: Bearer`要求ヘッダー内のトークンがサーバーで予期されるため、`["header"]`に設定されます。

Important

`authorization_servers`値は **v2.0** 発行者 (`.../v2.0`) である必要があります。これは、サーバーが受け入れる v2 アクセス トークンの`iss`要求と一致します (手順 2 を参照)。 v1 発行者 (`https://sts.windows.net/<tenant-id>/`) は、クライアントが検出する v2.0 承認サーバー メタデータと一緒に並んでいません。

認証されていない要求で、`WWW-Authenticate` ヘッダーがこのドキュメントを指す`401`応答を返します。 必要に応じて、 `scope` パラメーターを含め、現在の操作で必要なスコープをクライアントに伝えます。

```http
HTTP/1.1 401 Unauthorized
WWW-Authenticate: Bearer resource_metadata="https://mcp.contoso.com/.well-known/oauth-protected-resource", scope="tools.execute"
```

公式の MCP SDK または Azure App Service 認証を使用する場合、プラットフォームは、このドキュメントと`401`の課題を生成して提供できます。 MCP サーバーのアクセス トークンの検証を参照してください。

### MCP サーバーでアクセス トークンを検証する

すべての要求で、MCP サーバーはツールを実行する前にアクセス トークンを検証する必要があります。 少なくとも、トークンの **署名**、 **発行者** (`iss`)、 **テナント ID** (`tid`)、 **対象ユーザー** (`aud`、MCP サーバーのアプリケーション ID と等しい必要があります)、有効期限を確認します。 次に、**ロール** (`roles`) 要求または別の承認チェックを使用して、トークンの**サブジェクト** (`sub`または`oid`) が要求された操作に対して承認されていることを確認します。 委任されたアクセス トークンの場合は、 **スコープ** (`scp`) 要求を確認して、要求された操作に対して呼び出し元/アクターが承認されていることを確認してください。

セキュリティの脆弱性が発生しないようにするには、これらのチェックを手動で実装しないでください。 次の信頼のソースに関する記事のガイダンスとライブラリを使用します。

- [保護された Web API: 概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-protected-web-api-overview) と [保護された Web API: コード構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-protected-web-api-app-configuration) — Microsoft.Identity.Web などの推奨ミドルウェアを含む、Microsoft Entra ID で API を保護するための基本パターン。
- [アクセス トークンをセキュリティで保護](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens) し、 [トークンを検証する](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens#validate-tokens) — 署名、発行者、および対象ユーザーを正しく検証する方法。
- [要求を検証してアプリケーションと API をセキュリティで保護](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-validation) する — 署名と対象ユーザーのチェックを超えて、トークン内の要求を検証して要求を承認する方法。
- [ダウンストリーム API でエージェント ID トークンを検証](https://learn.microsoft.com/ja-jp/entra/agent-id/how-to-validate-agent-tokens-downstream-api)します。これは、呼び出し元がエージェント ID であることを検出する方法など、Microsoft Entra エージェント ID トークンの有効な例です。

**Azure App Service**で MCP サーバーをホストする場合は、アプリ コードでトークンを検証する代わりに、プラットフォームの組み込み認証 (Easy Auth) に認証をオフロードできます。 詳細なガイダンスについては、Azure App Serviceでの[Microsoft Entra認証を使用した MCP サーバーのセキュリティ保護に関する](https://learn.microsoft.com/ja-jp/azure/app-service/configure-authentication-mcp-server-vscode)トピックを参照してください。

### Microsoft Entra エージェント IDを使用して MCP クライアントを接続する

MCP クライアントが AI エージェントである場合は、[Microsoft Entra エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id)を使用して、エージェントが埋め込みシークレットではなく独自の ID で認証されるようにします。 エージェントは MCP サーバーのトークンを取得し、各呼び出しで提示します。

1. エージェントの ID を設定します。 [エージェントでのエージェント ID と認証プロトコルの作成と削除](https://learn.microsoft.com/ja-jp/entra/agent-id/create-delete-agent-identities)[に関する記事を](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-oauth-protocols)参照してください。
2. 手順 4 で定義した委任されたスコープに同意するか、手順 5 で定義したアプリ ロールを割り当てることで、MCP サーバーへのアクセス権をエージェントに付与します。
3. エージェントに MCP サーバーのトークンを取得させます。 クライアントは (MCP 仕様に従って) `resource=<your MCP server URL>` を送信し、適切な OAuth スコープを要求します。
4. エージェントは、 `Authorization: Bearer` ヘッダー内のトークンを使用して MCP サーバーを呼び出します。 サーバーは、 MCP サーバーのアクセス トークンの検証に関する説明に従って検証します。

エージェントが保護されたリソースを呼び出す方法のより広いビューについては、「 [エージェント ID を使用してサード パーティのエージェントを構成する](https://learn.microsoft.com/ja-jp/entra/agent-id/configure-third-party-agents) 」および「 [エージェントからカスタム API を呼び出す」を](https://learn.microsoft.com/ja-jp/entra/agent-id/call-api-custom)参照してください。

### FAQ とトラブルシューティング

#### MCP サーバーで v1 または v2 アクセス トークンを使用する必要がありますか?

**v2 アクセス トークンを使用します**。 手順 2 で説明されているように、`requestedAccessTokenVersion`を MCP サーバーのアプリ登録で`2`に設定します。 MCP 承認は、クライアントの `resource` パラメーターと HTTPS アプリケーション ID URI の照合に依存しており、その構成には v2 トークンが必要です。

#### クライアントが MCP サーバーのトークンを要求すると、エラー AADSTS9010010が発生する

`AADSTS9010010` は、 **アプリ登録で構成されたアプリケーション ID URI が、クライアントの OAuth 要求の `resource` パラメーターの値と正確に一致しないことを** 意味します。 これを修正するには:

1. **末尾のスラッシュまたはその他の完全一致の違いを確認します。** クライアントが送信する `resource` の値は、スキーム（`https://`）、ホストの大文字/小文字、パスを含め、Application ID URI と一文字一句一致している必要があります。 末尾のスラッシュは一般的な原因です。アプリケーション ID URI はスラッシュで終わることができないため、 `resource=https://mcp.contoso.com/` 送信するクライアントは登録された `https://mcp.contoso.com`と一致しません。 クライアントの `resource` 値とアプリケーション ID URI が同じになるように、末尾のスラッシュ (およびその他の相違点) を削除します。
2. **アプリで v2 アクセス トークンが使用されたことを確認します。**`requestedAccessTokenVersion`が `2` に設定されていない場合は、MCP サーバーの URL に一致する HTTPS アプリケーション ID URI を登録できないため、`resource`値は一致しません。 手順 2 を完了し、手順 3 でアプリケーション ID URI を再追加します。
3. **要求されたスコープが、 `resource`と同じアプリ登録に属していることを確認します。** このエラーは、クライアントが、`resource` パラメーターによって参照されるの*とは異なる*アプリ登録で定義されているスコープを送信する場合にも発生します。 Microsoft Entra ID は、`resource` とスコープを同じリソース アプリとして解決します。これらが異なるアプリ登録を指している場合、要求は失敗します。 クライアント要求のすべてのスコープが、 `resource`で送信するアプリケーション ID URI を持つアプリ登録によって公開されていることを確認し、別のアプリに属するすべてのスコープを削除します。

#### v2 アクセス トークンを使用できない場合はどうすればよいですか?

一部の MCP サーバーでは、既に v1 アクセス トークンを他のクライアントに発行している既存のアプリ登録が再利用され、それらのクライアントを中断せずに v2 トークンに切り替えることはできません。 その場合は、`use_guid`[追加プロパティ](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims-reference#additionalproperties-of-optional-claims)を使用して`aud`オプションの要求を構成することで、v1 トークンを保持し、代わりに`aud`[要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims-reference#v10-specific-optional-claims-set)を決定論的にすることができます。

既定では、v1 アクセス トークンの`aud`要求は非決定的です。Microsoft Entra IDは、アプリのアプリケーション ID URI (末尾のスラッシュの有無にかかわらず) またはリソースのクライアント ID を出力できます。 `use_guid` を設定すると、`aud` は*常に*リソースのクライアント ID（GUID 形式）になります。 対象ユーザーは特定のアプリケーション ID URI に関連付けられなくなったため、Microsoft Entra IDはアプリのアプリケーション ID URI の制限を緩和するため、アプリが v1 トークンを発行している場合でも、MCP サーバーの HTTPS URL をアプリケーション ID URI として追加できます (手順 3)。 その後、クライアントは `resource=<your MCP server URL>` を送信し、トークンを受け取ることができます。

オプションの要求をアプリ登録のマニフェストに追加します。

```json
"optionalClaims": {
    "accessToken": [
        {
            "name": "aud",
            "additionalProperties": ["use_guid"]
        }
    ]
}
```

`use_guid`設定すると、トークンの`aud`要求は、MCP サーバーの URL ではなく、リソースの**クライアント ID** (GUID) になります。 クライアント ID GUID を対象ユーザーとして想定するように、MCP サーバー (またはトークンを検証するミドルウェアまたは MCP SDK) を構成します。 v2 トークンに移行できる場合は常に、その方法 (手順 2) を選択します。v2 `aud` 要求は一貫してリソースのクライアント ID であり、MCP クライアントが検出する承認サーバー メタデータと一致するためです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/security-for-ai-overview"} -->
## AI 用 Microsoft Entra セキュリティの概要 - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/security-for-ai-overview
- Service: entra-id / agent-id
- Article date: 2026-05-08
- Summary: Microsoft Entra が、認証、ガバナンス、ゼロ トラスト ポリシーの適用を通じて、AI エージェント、アプリケーション、サービスに対して ID ベースのセキュリティ制御を提供する方法について説明します。

AI エージェント (環境を認識し、意思決定を行い、アクションを実行する自律的なソフトウェア システム) は、組織の機能を拡張しますが、従来のアプリケーション セキュリティとは異なるセキュリティ上の課題が生じます。 これらの機能には、AI ワークロードを認証し、アクセス ポリシーを適用し、非人間 ID に対するガバナンスを提供する ID ベースのセキュリティ制御が必要です。

Microsoft Entra は、AI システムをセキュリティで保護するための ID コントロール プレーンを提供します。 ID 機能を AI エージェント、アプリケーション、サービスに拡張して、組織が人間と非人間の ID に対して一貫した認証、承認、およびガバナンス制御を適用できるようにします。

この記事では、AI システムで ID ベースのセキュリティが必要な理由、これらの課題に対処する主要な Microsoft Entra 機能、および各領域の詳細なドキュメントへのリンクについて説明します。

### AI システムに ID ベースのセキュリティが必要な理由

組織はますます多様なタスクに対して AI エージェントをデプロイしており、各デプロイ モデルには個別のセキュリティ上の課題があります。

#### 対話型エージェント

対話型エージェントは、サインインしているユーザーに代わって、多くの場合、チャット インターフェイスを介して、特定のタスクをオンデマンドで実行します。 タスクには、顧客データを分析して販売に関する推奨事項を確認したり、サポートに関する質問に回答したりして、担当者へのエスカレーションを行ったりします。 これらのエージェントには、On-Behalf-Of (OBO) 認証フローを使用してユーザーに代わって動作できるようにする Microsoft Entra の委任されたアクセス許可が付与されます。 一般的なシナリオとしては、カスタマー サポート アシスタント、リサーチ ヘルパー、リアルタイム コラボレーション エージェントなどがあります。

#### 自律エージェント

自律エージェントは、人間のユーザーの ID ではなく、独自の ID を使用して独立して動作します。 これらのエージェントはバックグラウンドで実行され、セキュリティ操作のネットワーク ログの監視、自動スケーリングによるインフラストラクチャデプロイの管理、スケジュールされたメンテナンス タスクの処理など、人間の介入なしに意思決定を行い、アクションを実行します。 自律エージェントは、エージェント ID とクライアント資格情報フローを使用して、Microsoft Entra ID プラットフォームで直接認証します。

#### エージェントのユーザー アカウント

エージェントのユーザーアカウントは、エージェントIDと1対1に対応するオプションのアカウントです。 永続的な ID や、メールボックス、予定表、Teams チャネル、ドキュメントなどの組織リソースへのアクセスなど、人間のユーザー特性を持つ機能です。 エージェントのユーザー アカウントは、エージェントがユーザー オブジェクトを必要とするシステムにアクセスする必要がある場合にのみ使用します。 エージェントのユーザー アカウントはエージェント ID を置き換えません。両方が存在している必要があります。 詳細については、「 [エージェントのユーザー アカウント」](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-users)を参照してください。

#### セキュリティに関する課題

事前に定義されたロジックを実行するアプリケーションとは異なり、AI エージェントは動的な決定を行い、トレーニング データ、入力、環境条件に基づいて動作を適応させます。 このアダプティブ動作により、組織の攻撃対象領域が拡大します。

- **外部アクセシビリティ**: 多くの AI エージェントは、外部ユーザー、サードパーティ システム、またはパブリック インターネットと対話します。 この露出により、敵対者がエージェントを侵害し、組織のシステムにアクセスするための経路が生じる可能性があります。
- **アクセス許可のエスカレーション リスク**: エージェントは、多くの場合、機能を確保するために広範なアクセス許可でプロビジョニングされます。 財務データを分析するエージェントは、すべての財務記録、経費報告書、ベンダー契約へのアクセスを受け取る可能性があります。これは、特定のタスクに必要な範囲よりも広くなります。
- **自律的な意思決定**: 自律的な意思決定を行う侵害されたエージェントは、有害なアクションを実行する可能性があります。 購入機関を持つサプライ チェーン エージェントは、承認されていない注文を行う可能性があります。 インフラストラクチャ管理エージェントは、重要なシステムを削除する可能性があります。
- **迅速なインジェクション攻撃**: AI エージェントは、従来のアプリケーションに影響を与えない攻撃に対して脆弱です。 プロンプトインジェクション攻撃は、エージェントによって処理されたデータに悪意のある命令を挿入することによって、エージェントの動作を操作します。
- **エージェント間の伝達**: 他のエージェントと対話するエージェントは、侵害を伝達する可能性があります。 オーケストレーション エージェントが侵害された場合、他のエージェントをターゲットにして悪意のあるアクションを実行する可能性があります。

また、組織は、AI システムがガバナンス フレームワーク内で動作し、プライバシー規制に準拠し、エージェントのアクションと意思決定を文書化する監査証跡を維持することを示す必要があります。

#### エージェントの過剰展開

エージェントの急増は、適切な可視性、管理、またはライフサイクル制御なしで組織全体のエージェントを制御不能に拡張する、"エージェントスプロール" と呼ばれるガバナンスの課題を生み出します。 ビジネス ユニットが正式な IT 監視 (シャドウ AI) なしでエージェントを作成し、一時的な目的で作成されたエージェントが無期限に運用環境に残り、エージェントのアクセス許可が実際の要件を超え、レビューされない場合、エージェントのスプロールが発生します。

制御されていないエージェントのスプロールは、所有権が不明な特権を持つエージェントからのセキュリティ リスクの増加、監査者が AI システムに対するガバナンスを期待する場合のコンプライアンスの課題、および組織が侵害されたエージェントを迅速に特定できない場合のインシデント対応の困難につながります。

#### エージェントのセキュリティ シナリオ

エージェントのセキュリティの課題は、エージェントの目的とデプロイのコンテキストによって異なります。

| シナリオ | 説明 | セキュリティの課題 | リスク |
| --- | --- | --- | --- |
| **ユーザーが起動したエージェント** | エージェントは、ユーザーの代わりに、ユーザー機能とアクセス権を継承して行動します。 | エージェントが継承されたアクセス許可を誤って使用できないようにする。ユーザー制御を維持し、アクセス失効を有効にします。 | 侵害されたエージェントは、ファイルへのアクセス、通信の送信、データの操作など、承認されていないアクションをユーザーとして実行する可能性があります。 |
| **自律エージェント** | エージェントは、ユーザーとは関係なく、独自の ID とアクセス許可で動作します。 | 目的のタスクに必要なアクセス許可のみを付与します。エージェントが承認されたスコープを超えないようにします。 | 侵害されたエージェントは、制約なく動作したり、承認されていない注文を行ったり、データを変更したり、機密情報にアクセスしたりする可能性があります。 |
| **エージェントのユーザー アカウント** | エージェントのユーザー アカウントを使用すると、エージェントは永続的な ID、メールボックス、コラボレーション システムへのアクセスを持つ人間のユーザーとして機能できます。 | 適切なアクセス許可スコープを維持する。侵害されたエージェントがチーム アクセスを使用してマルウェアを拡散したり、意思決定を操作したりするのを防ぎます。 | 侵害されたエージェントは、ドキュメントにアクセスしたり、偽りの理由で会議に参加したり、信頼されているチームメンバーになりすまして通信を送信したりする可能性があります。 |
| **エージェント間** | エージェントは、タスクを特殊なエージェントに委任するオーケストレーション エージェントなど、他のエージェントと対話します。 | 認証済みエージェント通信を確立する。エージェントが正当なエージェントとのみ対話することを保証する。操作の監査証跡を維持します。 | セキュリティで保護されていない通信により、敵対者は悪意のあるエージェントを挿入したり、エージェントの対話を傍受または操作したりすることができます。 |

### AI エージェントの ID をセキュリティで保護する

AI エージェントには、従来のアプリケーション ID とは異なる専用の ID コンストラクトが必要です。 [Microsoft Entra エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id) は、AI エージェント用に設計された ID とセキュリティ フレームワークを提供します。

[Image: Microsoft Entra エージェント ID を使用した AI ランドスケープのセキュリティの図。]

Microsoft Entra エージェント ID を使用すると、組織は次のことが可能になります。

- **エージェントの登録と管理**: エージェント ID ブループリントをテンプレートとして作成し、親と子のリレーションシップを持つ個々のインスタンスとしてエージェント ID を管理し、一元化されたメタデータ管理とセキュリティ コレクションへの自動編成を可能にします。
- **セキュリティで保護されたスケーラブルな ID を割り当てる**: [Microsoft Entra Agent ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/what-is-agent-id-platform) を使用すると、エージェントに ID を割り当て、組織全体で自動検出し、機能、タスク、プロトコルを含むすべてのエージェント メタデータを 1 か所で管理できます。 モデル コンテキスト プロトコル (MCP) やエージェント間 (A2A) などの標準プロトコルに基づいて、エージェント間の検出と承認を提供します。
- **エージェント アクティビティのログ記録と監視**: エージェントによって実行されるすべての認証とアクションは Microsoft Entra ID に記録され、コンプライアンスと監査の目的で Microsoft Entra 管理センターを通じて表示できます。

アプリケーション ID とユーザー ID との違いなど、ID コンストラクトとしてのエージェント ID の詳細については、「 [エージェント ID とは」を](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/what-are-agent-identities)参照してください。

### ゼロ トラストを使用して AI アクセスを制御する

Microsoft Entra は、条件付きアクセスと Identity Protection を使用して、ゼロ トラスト ID コントロールを AI ID に適用します。

#### エージェントの条件付きアクセス

条件付きアクセスを使用すると、リソースへのアクセスを許可する前に、エージェントのコンテキストとリスクを評価するアダプティブ ポリシーを定義して適用できます。

- 支援、自律、およびエージェントのユーザー アカウントの種類にまたがって、すべてのエージェント パターンに対してアダプティブ アクセス制御ポリシーを適用します。
- エージェント ID リスクなどのリアルタイムシグナルを使用して、リソースへのエージェント アクセスを制御します。Microsoft マネージド ポリシーは、リスクの高いエージェントをブロックすることでセキュリティで保護されたベースラインを提供します。
- カスタム セキュリティ属性を使用して条件付きアクセス ポリシーを大規模に展開しながら、個々のエージェントのきめ細かい制御をサポートします。

詳細については、「 [エージェントの条件付きアクセス」を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/agent-id)参照してください。

#### エージェントのアイデンティティ保護

Microsoft Entra ID 保護 は、エージェントを含む異常なアクティビティにフラグを設定することで、脅威を検出してブロックします。

- 通常とは異なるアクティビティや未承認のアクティビティなど、エージェント自身のアクションに基づいて、ユーザー リスクから派生したエージェント ID リスクを検出します。
- 条件付きアクセスにリスクシグナルを提供して、リスクベースのポリシーとセッション管理制御を適用します。
- 事前に構成されたポリシーで侵害されたエージェントを自動的に修復し、エージェントの発見可能性とアクセスに関するリスクシグナルを提供します。

詳細については、「[エージェントの Identity Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-agents)」を参照してください。

### AI の ID とアクセス許可を管理する

組織がより多くの AI エージェントをデプロイするにつれて、エージェントの拡散を防ぎ、コンプライアンスを維持するために ID ガバナンスが重要になります。 Microsoft Entra ID ガバナンスは、ライフサイクル管理、アクセス レビュー、エンタイトルメント管理をエージェント ID に拡張します。

- デプロイから有効期限まで、エージェント ID を大規模に管理します。
- 各エージェント ID に対してスポンサーと所有者が割り当てられ、維持されていることを確認し、孤立したエージェント ID を防ぎます。
- リソースへのエージェント アクセスは、アクセス パッケージを通じて意図的、監査可能、および期限付きであることを強制します。
- [ブループリント レベル](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-platform/agent-blueprint)で条件付きアクセス規則、アクセス許可、およびガバナンス制御を適用して、現在および将来のすべてのエージェント インスタンスがそれらを自動的に継承し、1 回の操作でエージェントのクラス全体を無効にすることができます。
- Microsoft Entra 管理センターと [Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/agentidentity) での一元的な検出と管理を通じて、すべてのエージェント ID の完全なインベントリを維持し、シャドウ AI を防ぎ、登録と資格情報の管理から非アクティブ化と使用停止まで、組織全体のライフサイクルにわたってエージェントを追跡できるようにします。

詳細については、「 [エージェントの ID ガバナンス」を](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-id-governance-overview)参照してください。

Microsoft Entra ID ガバナンスの一般的な概要については、「 [Microsoft Entra ID ガバナンスとは」を](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview)参照してください。

### AI サービスと自動化をセキュリティで保護する

多くの AI ワークロードは、対話型エージェントとしてではなく、アプリケーション、サービス、または自動化パイプラインとして実行されます。 これらのワークロードでは、エンタープライズ リソースにアクセスするために、セキュリティで保護された資格情報のない認証が必要です。

Microsoft Entra ワークロード ID は、ソフトウェア ワークロードの ID とアクセス管理を提供します。 組織は、マネージド ID とフェデレーション資格情報を使用して、シークレットを管理せずに AI サービスを認証できます。

詳細については、「[ワークロード ID とは](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-overview)」を参照してください。

### セキュリティで保護された生成 AI アーキテクチャ

エンタープライズ生成 AI アーキテクチャでは、クライアント アプリケーションから AI モデル、およびアクセスするデータ ソースまで、すべてのレイヤーで ID 制御が必要です。 Microsoft Entra は、これらのアーキテクチャ コンポーネント全体に認証、承認、ガバナンスの制御を適用します。

グローバル セキュア アクセスによるネットワーク レベルの制御では、ユーザーとエージェント間で一貫したネットワーク セキュリティ ポリシーが適用されます。

- 監査と脅威の検出のためにリモート ツールにエージェント ネットワーク アクティビティをログに記録し、Web 分類を適用して API と MCP サーバーへのアクセスを制御します。
- ファイルの種類のポリシーを使用してファイルのアップロードとダウンロードを制限してリスクを最小限に抑え、脅威インテリジェンスベースのフィルター処理を使用して悪意のある宛先を自動的にブロックおよびアラートします。
- 悪意のある命令によってエージェントの動作を操作しようとするプロンプトインジェクション攻撃を検出してブロックします。

詳細については、 [エージェントの Web ゲートウェイと AI ゲートウェイのセキュリティ保護に関するページを](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-secure-web-ai-gateway-agents)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/sidecar-local-development"} -->
## ローカル開発用の Microsoft Entra ID 認証 SDK (サイドカー) を実行する - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/sidecar-local-development
- Service: entra-id / agent-id
- Article date: 2026-06-15
- Summary: Docker Compose と Ollama を使用してノート PC で Microsoft Entra ID Auth SDK (サイドカー) を実行し、自律的で代理的なエージェント認証がエンド ツー エンドで動作することを確認します。

この記事では、Docker Compose を使用してローカル環境で [Microsoft Entra ID 認証 SDK (サイドカー)](https://mcr.microsoft.com/en-us/product/entra-sdk/auth-sidecar/about) を実行する方法について説明します。 チャット エージェント、サイドカー、ダウンストリーム天気 API、ローカルの大規模言語モデル (LLM) ([Ollama](https://ollama.com)) など) の 4 つのコンテナー スタックを開始します。 次に、チャット UI を介してクエリを送信し、エージェントから API への完全なトークン フローを観察します。 開始する前に、必要なツール、テナント オブジェクトのMicrosoft Entra、ローカル環境のセットアップの前提条件を確認します。

このサンプルでは、2 つの実行モードと 2 つの ID フローを示します。

| - | **自律型**(アプリのみ) | **OBO**(ユーザーの代理) |
| --- | --- | --- |
| **Direct** (LLM なし) | エージェントはトークンをフェッチし、weather API を直接呼び出します。 | 同じですが、サイドカーはサインインしているユーザーのトークンを交換します。 |
| **Ollama + LangChain** | LangGraph ReAct エージェントは、 `get_weather` ツールを呼び出すタイミングを決定します。 | 同じですが、エージェントはユーザー トークンを渡します。 |

### 前提条件

このサンプルは、macOS、Linux、および Windows 10/11 で動作します。

| 要件 | macOS | Linux | Windows |
| --- | --- | --- | --- |
| Docker | Docker Desktop | Docker エンジン + Compose v2 | Docker Desktop (WSL 2 バックエンドを推奨) |
| PowerShell 7 以降 | `brew install --cask powershell` | [Linux に PowerShell をインストールする](https://learn.microsoft.com/ja-jp/powershell/scripting/install/installing-powershell-on-linux) | 組み込み (または PowerShell 7 以降をインストール) |
| Azure CLI | `brew install azure-cli` | [Azure CLI のインストール](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli-linux) | `winget install -e Microsoft.AzureCLI` |

また、次のオブジェクトを含む **Microsoft Entra テナント**も必要です。

- クライアント シークレットを含むエージェント ID ブループリント。 `BLUEPRINT_APP_ID`と`BLUEPRINT_CLIENT_SECRET`の値を記録します。
- そのブループリントから作成されたエージェント ID。 `AGENT_CLIENT_ID` を記録します。
- (OBO フローのみ)SPA アプリの登録。 `CLIENT_SPA_APP_ID` を記録します。

これらのオブジェクトを作成するには、[Microsoft Entra エージェント ID サンプル リポジトリ](https://github.com/microsoft/entra-agentid-samples)の PowerShell ワークフローに従います。 ワークフローは、ブループリント アプリ、エージェント ID、および必要に応じて OBO サインイン用の SPA アプリを作成します。

Ollama はホストの必須条件 **ではありません** 。コンポーズスタック内で稼働し、`qwen2.5:1.5b` 自動的に取得されます。

### サンプル リポジトリを複製する

次のコマンドを実行してサンプル プロジェクトをダウンロードし、サイドカー ディレクトリに変更します。このディレクトリには、このチュートリアルの Docker Compose 構成とエージェントのソース コードが含まれています。

```bash
git clone https://github.com/microsoft/entra-agentid-samples.git
cd entra-agentid-samples/sidecar/dev
```

### サイドカーローカル開発アーキテクチャ

このスタックは、内部 Docker ネットワーク上で 4 つのコンテナー (`llm-agent-dev` (ポート 3003 で公開されている Flask チャット UI)、`agent-id-sidecar-dev` (Microsoft Entra ID認証 SDK サイドカー)、`weather-api-dev` (エージェント トークンを検証するダウンストリーム API)、`ollama-dev` (ローカル LLM) の 4 つのコンテナーを実行します。 チャット UI のみがホストに公開されます。サイドカーと天気 API は、Docker ネットワーク内からのみ到達できます。

[Image: サイドカー アーキテクチャを示すダイアグラム: Microsoft Entra ID はサイドカーに TR トークンを発行し、エージェントはサイドカーに承認ヘッダーを要求します。その後、Bearer TR を使用して weather API を呼び出し、トークンが検証されてデータが返されます。]

4 つのコンテナーはすべて、共有 Docker ブリッジ ネットワーク (`agent-network-dev`) 上で実行されます。 チャット UI (ポート 3003) のみがホストに公開されます。 サイドカー API と Weather API にはホスト ポートがないため、トークン エンドポイントは信頼境界内に保持されます。

要求パスは次のように動作します。

1. ブラウザーで `http://localhost:3003` を開き、クエリを送信します。
2. エージェント (`llm-agent-dev`) はクエリを受け取り、 `get_weather` ツールを呼び出します。
3. このツールは、サイドカーに `GET /AuthorizationHeader...?AgentIdentity={agentId}`で承認ヘッダーを要求します。
4. サイドカー (`agent-id-sidecar-dev`) は、Microsoft Entra IDとの OAuth 2.0 交換を実行し、トークン (TR) を受け取ります。
5. サイドカーは、 `Authorization: Bearer TR` ヘッダーをエージェントに返します。
6. エージェントは、そのヘッダーを使用して weather API (`weather-api-dev`) を呼び出します。
7. Weather API は、TR (JSON Web Key Set (JWKS)、RS256 署名アルゴリズム、発行者、有効期限、対象ユーザー) を検証し、気象データを返します。

エージェントはMicrosoft Entra ID直接連絡を取ることはありません。また、資格情報は表示されません。 サイドカーに `Authorization` ヘッダーを要求し、 `Bearer` トークンを受け取り、そのトークンを weather API に渡します。 サイドカーのみが `login.microsoftonline.com`と通信します。

### トークン フローを理解する

自律フローでは、 **T1** (クライアント資格情報からのブループリント アプリ トークン) と **TR** (ダウンストリーム API のエージェント トークン) の 2 つのトークンが使用されます。 OBO フローでは、3 つ目の要素として **Tc**（MSAL.js のブラウザーサインインからのユーザー アクセス トークン）が追加されます。 サイドカーはすべてのトークンの取得とキャッシュを処理するため、エージェント コードが資格情報を直接管理することはありません。

#### 自律トークン フローを理解する

ユーザーのサインインは必要ありません。 エージェントは、ブループリントのクライアント資格情報を使用してそれ自体として認証します。

[Image: エージェントからサイドカー、Microsoft Entra ID、天気APIへの自律フローシーケンスを示す図。]

1. ユーザーはチャット UI を介してクエリを送信します。
2. エージェント (または LangGraph ReAct エージェント) は、 `get_weather` ツールの呼び出しを決定します。
3. ツールは、`GET /AuthorizationHeaderUnauthenticated/graph-app?AgentIdentity={agentAppId}`のサイドカーから認証ヘッダーを要求します。
4. サイドカーは、Microsoft Entra IDとのクライアント資格情報交換を実行し、TR (アプリ専用、`idtyp=app`) を受け取ります。
5. このツールは、 `Authorization: Bearer TR`を使用して weather API を呼び出します。
6. Weather API は、TR (署名、発行者、有効期限、対象ユーザー) を検証し、気象データを返します。

#### On-Behalf-of (OBO) トークン フローを理解する

エージェントは、サインインしているユーザーに代わって機能します。 サイドカーは、3 段階のトークン交換を実行します。

[Image: ブラウザーのサインインからサイドカー トークン交換を経て天気 API に至る On-Behalf-Of フローのシーケンスを示す図。]

1. ユーザーはブラウザーで MSAL.js を介してサインインし、Tc (ユーザー アクセス トークン、対象ユーザー = `api://{BlueprintAppId}`) を受け取ります。
2. ユーザーがクエリを送信します。 エージェントは、要求と共に Tc を受信します。
3. ツールは `GET /AuthorizationHeader/graph` のサイドカーから承認ヘッダーを要求し、`Authorization: Bearer Tc` と `?AgentIdentity={agentAppId}` を渡します。
4. サイドカーは Tc を検証し、T1 を取得するためにクライアント資格情報交換を実行した後、OBO 交換を実行して TR (委任、 `idtyp=user`) を取得します。 OBO 交換では、 `assertion=Tc`、 `client_assertion=T1`、および `grant_type=jwt-bearer`が使用されます。
5. このツールは、 `Authorization: Bearer TR`を使用して weather API を呼び出します。
6. Weather API は TR を検証し、気象データを返します。 TR は、サインインしているユーザーの代わりに機能します。

### 環境変数を構成する

ヒント

`.env`、`TENANT_ID`、`BLUEPRINT_APP_ID`、`BLUEPRINT_CLIENT_SECRET`が設定された以前の実行の`AGENT_CLIENT_ID` ファイルが既にある場合は、「スタックの開始」に進みます。 Microsoft Entra オブジェクトは、コンテナーの再起動後も存続し、`docker compose down`。

環境ファイルの例をコピーし、Microsoft Entra値を追加します。

## [Bash](#tab/bash)
テナントとアプリの登録値を入力できるように、サンプル テンプレートからローカル環境ファイルを作成します。

```bash
cp .env.example .env
```

## [PowerShell](#tab/powershell)
サンプルの環境ファイルを `.env` にコピーして、テナントとアプリの登録値を設定できるようにします。

```powershell
Copy-Item .env.example .env
```

---

エディターで `.env` を開き、次の値を設定します。

| Variable | 説明 |
| --- | --- |
| `TENANT_ID` | あなたの Microsoft Entra テナント ID。 |
| `BLUEPRINT_APP_ID` | 設計図アプリ登録クライアントID。 サイドカーはこのアプリとして認証を行います。 |
| `BLUEPRINT_CLIENT_SECRET` | ブループリント クライアント シークレット。 ローカル開発にのみ使用されます。 |
| `AGENT_CLIENT_ID` | エージェント・アイデンティティ・クライアントID。 `AgentIdentity` クエリ パラメーターとしてサイドカーに渡されます。 |
| `CLIENT_SPA_APP_ID` | SPA アプリ登録クライアント ID。 OBO フローにのみ必要です。 |
| `OLLAMA_MODEL` | 使用する Ollama モデル。 既定値は `qwen2.5:1.5b` です。 |

自律フローには、 `TENANT_ID`、 `BLUEPRINT_APP_ID`、 `BLUEPRINT_CLIENT_SECRET`、および `AGENT_CLIENT_ID`が必要です。 OBO フローには、 `CLIENT_SPA_APP_ID`も必要です。

このサイドカー サンプルでは、資格情報ソースの種類として `ClientSecret` を使用します。 サイドカーは、`AzureAd__ClientCredentials__0__SourceType`の`docker-compose.yml`設定を使用して、次の資格情報の種類をサポートします。

- **`ClientSecret`:** ローカル開発のみ。 この種類は、このサンプルの既定値です。
- **`SignedAssertionFromManagedIdentity`:** Azure にデプロイされます。 ゼロ シークレット。運用環境に推奨されます。
- **`KeyVault`:** Azure Key Vaultからの証明書。
- **`StoreWithThumbprint`:** ローカル コンピューター ストアからの証明書。

### OBO サインインを設定する (省略可能)

代理フローをテストするには、SPA アプリを作成し、OBO の同意を構成します。 リポジトリ ルートから次のいずれかのスクリプト ペアを実行します。

## [Bash](#tab/bash)
```bash
# Create the SPA app registration for MSAL.js browser sign-in
bash ../../scripts/setup-obo-client-app.sh
# → prints CLIENT_SPA_APP_ID

# Wire up the OBO scope + admin consent on the Blueprint
bash ../../scripts/setup-obo-blueprint.sh
```

## [PowerShell](#tab/powershell)
```powershell
# Create the SPA app registration for MSAL.js browser sign-in
pwsh ../../scripts/setup-obo-client-app.ps1
# → prints CLIENT_SPA_APP_ID (and writes it to .env)

# Wire up the OBO scope + admin consent on the Blueprint
pwsh ../../scripts/setup-obo-blueprint.ps1 `
    -TenantId        '<TENANT_ID>' `
    -BlueprintAppId  '<BLUEPRINT_APP_ID>' `
    -AgentAppId      '<AGENT_CLIENT_ID>' `
    -ClientSpaAppId  '<CLIENT_SPA_APP_ID>'
```

---

スクリプトを実行した後、`CLIENT_SPA_APP_ID` ファイルに`.env`値を追加します。

### スタックを開始する

次のコマンドを実行して、コンテナー イメージをビルドし、デタッチ モードで 4 つのサービスをすべて開始します。

```bash
docker compose up --build -d
```

最初の実行には約 30 秒かかりますが、Ollama は `qwen2.5:1.5b` モデルをプルします。 ローカル サンプル スタックが実行されていること、およびサイドカーや Ollama などのコンポーネントの準備ができていることを確認するには、状態エンドポイントに対してクエリを実行します。

## [Bash](#tab/bash)
```bash
curl http://localhost:3003/api/status
```

## [PowerShell](#tab/powershell)
サンプル アプリの状態エンドポイントにクエリを実行して、ローカル環境が正常で、すべてのサービスが実行されていることを確認します。

```powershell
Invoke-RestMethod http://localhost:3003/api/status
```

---

応答には、スタックの準備ができたときに `ollama_available: true` が表示されます。

### チャット UI を使用してクエリを送信する

チャット UI を介してテスト クエリを送信し、トークン フローを観察するには、次の手順を実行します。

1. ブラウザーで `http://localhost:3003` を開きます。
2. ヘッダー バーで、 **テナント ID** と **エージェント ID** が表示されることを確認します。
3. 2 つのトグルを使用して、デモ構成を選択します。

    - **実行モード**: **ダイレクト** を選択して LLM をスキップし、weather API を直接呼び出すか、 **Ollama** を選択して LangChain ReAct エージェントを使用します。
    - **ID フロー**: アプリ専用トークンの場合は **[自律]** を選択し、サインインしているユーザーの代わりに **動作する OBO** を選択します。 OBO の場合は、[ **サインイン** ] を選択して、MSAL.js ポップアップを使用して認証します。
4. 事前設定されたクエリ "Weather in Dallas?" を送信し、結果を確認します。
5. 右側のパネルで、[ **ID トレース] を** 展開して、トークン フローの各ステップを調べます。

    - `AgentIdentity` パラメーターを含むサイドカーへのトークン要求。
    - トークンごとにデコードされた JWT 要求 (**Tc**、 **T1**、 **TR** for OBO; **T1** と **TR** (自律)。
    - 署名 (JWKS、RS256)、発行者、有効期限、対象ユーザーのチェックなど、ダウンストリーム API 検証の結果。

### 一般的な問題のトラブルシューティング

| 現象 | 考えられる原因 | 固定 |
| --- | --- | --- |
| `/api/status` は `ollama_available: false` を返します。 | モデルはまだダウンロード中です。 | 約 30 秒待ちます。 `docker logs ollama-dev`を使用してログを確認します。 |
| Weather API が返す `401 Unauthorized` | トークン テナントの不一致、期限切れのシークレット、または署名の確認に失敗しました。 | `TENANT_ID`がブループリントのテナントと一致するかどうかを確認します。 `docker logs agent-id-sidecar-dev`を使用してサイドカー ログを確認します。 |
| LLM はツールを呼び出さずに天気を返します | `qwen2.5:1.5b` モデルは、信頼性の高いツール呼び出しには小さすぎます。 | `OLLAMA_MODEL`を、`qwen2.5:7b` ファイル内の`llama3.1:8b`または`.env`に変更します。 |
| OBO サインイン ポップアップがブロックされている | ブラウザー ポップアップ ブロックがアクティブです。 | `localhost:3003`のポップアップを許可します。 |
| OBO 中のサイドカーからの `4xx` エラー | `CLIENT_SPA_APP_ID` が見つからないか、SPA リダイレクト URI が一致しません。 | OBO セットアップ スクリプトを再実行します。 `http://localhost:3003` が SPA のリダイレクト URI に一覧表示されていることを確認します。 |

スタートアップ、認証、またはダウンストリーム API の問題を診断するには、次のコマンドを実行して各コンテナーのログを表示します。

```bash
docker logs llm-agent-dev
docker logs agent-id-sidecar-dev
docker logs weather-api-dev
```

### リソースをクリーンアップする

完了したら、コンテナーを停止します。 ニーズに合ったクリーンアップ レベルを選択します。 最初のコマンドは、後で再起動を高速化するために、ボリュームとイメージを保持しながらデモ コンテナーを停止します。

```bash
# Stop containers, keep volumes and images
docker compose down

# Stop containers and remove the Ollama model cache
docker compose down -v

# Remove containers, volumes, and images
docker compose down -v --rmi all
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/sign-in-audit-logs-agents"} -->
## Microsoft Entra エージェント ID ログ

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/sign-in-audit-logs-agents
- Service: entra-id / agent-id
- Article date: 2026-04-28
- Summary: エージェント ID に関連付けられている監査アクティビティとサインイン アクティビティが Microsoft Entra ID にどのように記録されるかについて説明します。

AI エージェントの使用状況、機能、スコープが拡大するにつれて、エージェント ID に関連付けられているアクティビティが Microsoft Entra ID にどのように記録されるかを理解することが重要です。 IT 管理者は、監査ログとサインイン ログで使用できる情報と、その情報を使用してエージェントアクティビティを監視する方法を知る必要があります。 監査ログとサインイン ログは、次の種類のシナリオを調査する必要がある場合に役立ちます。

- エージェントまたはエージェント関連トラフィックのログインログ
- エージェント ID またはエージェントのユーザー アカウントがテナント内の操作のイニシエーターまたは実行者である場合の監査ログ

この記事では、エージェント ID アクティビティを Microsoft Entra ID に記録する方法と、Microsoft Entra 管理センターと Microsoft Graph を使用してこれらのログにアクセスする方法について説明します。

### 監査ログ

エージェント アクティビティは、アクティビティの発生元のベース ID の種類でログに記録されます。 現在、監査ログに表示されるエージェント ID の種類は 3 つあり、それぞれが Microsoft Entra ID の既存の ID の種類に関連付けられます。

- **エージェント ID ブループリント アクティビティは** 、 *アプリケーション イベント* ("アプリケーションの追加" や "アプリケーションの削除" など) として表示されます。
- **エージェント ID アクティビティは** 、 *サービス プリンシパル イベント ("サービス プリンシパル* の追加" や "サービス プリンシパルの更新" など) として表示されます。
- **エージェントのユーザー アカウント アクティビティは** 、 *ユーザー イベント* ("ユーザーの追加" など) として表示されます。

監査イベントにエージェント ID が関係するかどうかを識別するには、`agentType`、`initiatedBy`、および`performedBy`フィールドの`targetResources` プロパティを確認します。 エージェント ID とエージェントのユーザー アカウントは、 `initiatedBy` イベントと `performedBy` イベントの両方で表すことができます。 `notAgentic`以外の値は、エージェントの関与を示します。

#### agentType

`agentType`値は、エージェント ID アクションをさらに明確にしています。 `agentType` プロパティは、監査イベントに関連する ID がエージェント ID、エージェント ID ブループリント、またはエージェントのユーザー アカウントであるかどうかを示します。

`agentType` プロパティは、次のリソースに表示されます。

- `auditAppIdentity`
- `auditUserIdentity`
- `targetResource`
- `auditActivityPerformer`

| 先頭値 | 説明 |
| --- | --- |
| `notAgentic` | アイデンティティはエージェントではありません。 これは、標準のアプリ、ユーザー、またはサービス プリンシパルです。 |
| `agenticApp` | エージェント ID ブループリント。エージェントのテンプレートまたは定義です。 アプリの登録に似ています。 |
| `agenticAppInstance` | エージェント ID。エージェントの特定の実行中のインスタンスです。 サービス プリンシパルに似ています。 |
| `agentIdentityBlueprintPrincipal` | ブループリント プリンシパル自体。これは、ブループリントを表すサービス プリンシパルです。 |
| `agentIDuser` | エージェントのユーザー アカウント。 この ID により、エージェントはユーザーが委任されたアクセス許可を持つユーザーとして機能できます。 |
| `unknownFutureValue` | 進化可能な列挙センチネル値。 使用しないでください。 |

次の表は、これらのエージェントのアクティビティと値がどのようにマップされ、監査ログに表示されるかをまとめたものです。

| エージェント アクション | 監査活動 | エージェントタイプの値 |
| --- | --- | --- |
| エージェント ID ブループリントを作成する | アプリケーションを追加する | `agenticApp` |
| エージェント ID を作成する | サービス プリンシパルの追加 | `agenticAppInstance` |
| エージェントのユーザー アカウントを作成する | ユーザーの追加 | `agentIDuser` |
| エージェント ID ブループリントを更新する | アプリケーションを更新する | `agenticApp` |
| エージェント ID を更新する | サービス プリンシパルの更新 | `agenticAppInstance` |
| エージェント ID ブループリントを削除する | アプリケーションを削除する | `agenticApp` |
| エージェント ID を削除する | サービス プリンシパルを削除する | `agenticAppInstance` |

ヒント

Microsoft Graph応答で `agentIdentityBlueprintPrincipal` 値と `agentIDuser` 値を受信するには、`Prefer: include-unknown-enum-members` 要求ヘッダーを含めます。 これらの値は、 [進化可能な列挙体](https://learn.microsoft.com/ja-jp/graph/best-practices-concept#handling-future-members-in-evolvable-enumerations)の一部です。

#### blueprintId

`blueprintId` プロパティは、エージェントに関連付けられている [agentIdentityBlueprint](https://learn.microsoft.com/ja-jp/graph/api/resources/agentidentityblueprint?view=graph-rest-beta&preserve-view=true) のオブジェクト ID です。 このプロパティを使用して、エージェント ID (インスタンス) をブループリント (テンプレート) に関連付けます。 このプロパティは、 `auditAppIdentity`、 [targetResource](https://learn.microsoft.com/ja-jp/graph/api/resources/targetresource?view=graph-rest-beta&preserve-view=true)、および `auditActivityPerformer`に表示されます。

`blueprintId`とエージェント ID の関係は、アプリの登録とそのサービス プリンシパルの関係に似ています。 複数のエージェント ID で同じブループリントを共有できます。

#### 監査ログ スキーマの変更点

監査ログ スキーマに対する次の変更により、エージェント ID の追跡が有効になります。

- `initiatedBy.app` プロパティでは、既存の`auditAppIdentity`、`agentType`、`blueprintId`、および`appId`プロパティと共に`displayName`と`servicePrincipalId`を含む、`servicePrincipalName` リソースの種類が使用されるようになりました。
- [targetResource](https://learn.microsoft.com/ja-jp/graph/api/resources/targetresource?view=graph-rest-beta&preserve-view=true) リソースの種類に、`agentType`プロパティと`blueprintId`プロパティが含まれるようになりました。
- [auditUserIdentity](https://learn.microsoft.com/ja-jp/graph/api/resources/audituseridentity?view=graph-rest-beta&preserve-view=true) リソースの種類に、`agentType` プロパティが含まれるようになりました。
- 新しい `auditActivityPerformer` リソースの種類は、監査イベントのアクターの `agentType`、 `appId`、および `blueprintId` を提供します。

### サインイン ログ

`agentSignIn`サインイン イベントの種類には、エージェントがアプリであるかアプリのインスタンスであるかなど、エージェントに関するプロパティが含まれます。 エージェントはユーザー委任アクセス許可またはアプリ専用のアクセス許可でサインインできるため、4 つのサインイン ログの種類ごとにサインインが表示される場合があります。

`agentSignIn` サインイン イベントの種類は、Microsoft Entra 管理センターと Microsoft Graph APIで使用できます。

## [Microsoft Entra 管理センター](#tab/microsoft-entra-admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;に移動し、**モニタリングとヘルス**&gt;の**サインイン ログ**を参照します。
3. エージェントのサインインを表示するには、次のフィルター オプションを使用します。
    - \*\* **エージェントの種類**: **エージェント ID ユーザー**、**エージェントの特性**、**エージェントの特性ブループリント**、または**非エージェント**から選択
    - **エージェント:** **[いいえ**] または [**はい**] から選択

## [Microsoft Graph](#tab/microsoft-graph)
Microsoft Entra エージェント ID ログは、 `/beta` エンドポイントで Microsoft Graph を使用して表示および管理できます。

作業を開始するには、次の手順に従って、Graph エクスプローラーの Microsoft Graph を使用して推奨事項を操作します。

1. [グラフ エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)にサインインします
2. ドロップダウンから HTTP メソッドとして **GET** を選択します。
3. API バージョンを **[ベータ]** に設定します。

#### エージェント ID サインイン イベントを取得する

Microsoft Entra エージェント ID が関係していたサインイン イベントのみを取得するには、エージェントの種類が `AgentIdentity`されているサービス プリンシパルによって開始されたイベントを検索する必要があります。 次の Microsoft Graph 要求を使用します。

```http
GET https://graph.microsoft.com/beta/auditLogs/signIns?$filter=signInEventTypes/any(t: t eq 'servicePrincipal') and agent/agentType eq 'AgentIdentity'
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/sign-in-process"} -->
## Microsoft Entra エージェント IDのサインイン手順

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/sign-in-process
- Service: entra-id / agent-id
- Article date: 2025-11-04
- Summary: 同意ページ、信頼条件、職場アカウントを使用して AI エージェントに安全にアクセスするためのエージェントのアクセス許可を管理する方法など、Microsoft Entra エージェント IDサインイン プロセスについて説明します。

この記事は、Microsoft Entra エージェント IDサインイン プロセスの詳細を知りたい非技術ユーザーを対象としています。 認証とデータ アクセスにMicrosoft Entra エージェント IDを使用するエージェント、製品、またはサービスにサインインすると、次のような同意ページが表示されることがあります。

[Image: AI エージェントにサインインするためのサンプル同意ページのスクリーンショット。]

### エージェントにログイン

AI エージェントは、自分またはチームのタスクを実行できるソフトウェアです。 情報を要約したり、問題を監視したり、アラートを送信したり、レビューできる下書きを準備したりする場合があります。 選択または入力を待機する通常のアプリとは異なり、エージェントは承認した制限内で独自に操作できます。

サインインするには、AI エージェントは、エージェント ID を作成して、製品またはサービスをMicrosoftに登録する必要があります。 各 AI エージェントには、一意のエージェント ID があります。

エージェント ID を使用すると、AI エージェントは次のことができます。

- アカウントにサインインして、あなたを識別します。
- 許可するデータとアクションへのアクセスを要求します。

### 同意ページ

同意ページが表示されるのは、エージェントが組織で運用する前にアクセス許可が必要であるためです。 このプロセスには、次の 2 つの手順があります。

1. このエージェントを組織に追加します。 これにより、エージェントは組織のメンバーとして参加できます。 このページは、組織内のユーザーがエージェントに初めてログインしたときにのみ表示されます。
2. エージェントにこのデータへのアクセスを許可します。 これにより、エージェントが要求している特定の種類の情報やアクション (プロファイルや電子メールの読み取りなど) が表示されます。 許可または拒否を選択します。

このページでは、アクセス権を持つアカウントとデータを AI エージェントがどのように使用するかを制御できます。

### エージェントを信頼する

同意ページには、サインインするエージェントの信頼性を評価するのに役立つ情報が含まれています。

- エージェント、製品、またはサービスの名前
- エージェントがMicrosoftパブリッシャーの検証プロセスを完了したかどうか
- エージェントを発行した組織
- エージェントがMicrosoftによってビルドされるかどうか

同意ページの詳細については、 [この記事を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/application-consent-experience)。

**このエージェントに意図的にサインインしなかった場合は、常にアクセスを拒否してください。 慣れていないアプリやエージェントにサインインしないでください。**

### 組織にエージェントを追加する

AI エージェントは、メンバーが承認しない限り、組織に参加できません。 組織にエージェントを追加すると、エージェントは、自分と他の従業員またはメンバーが使用できるようになります。 エージェントは、他のメンバーにサインインし、データと情報へのアクセスを要求できます。

組織にエージェントを追加すると、エージェント アカウントを作成することもできます。 人間のユーザーと同様に、AI エージェントは組織内にアカウントを持つことができます。 これらのアカウントを使用して、組織内のアプリ、ツール、ファイル、システムにアクセスするためのアクセス権を AI エージェントに付与できます。 また、AI エージェントが実行するアクションを追跡するのにも役立ちます。

エージェントを追加すると、エージェントは組織内で独自のアカウントを作成し、アクセスに使用できるようになります。 エージェントのアカウントは、ユーザーまたは管理者によって付与されるアクセス許可とアクセス権に常に制限されます。

**組織にエージェントを追加するかどうかが不明な場合は、一時停止して管理者に問い合わせてください。**

### エージェントによるデータへのアクセスを許可する

エージェントがデータにアクセスすることを承認するということは、次のことを意味します。

- エージェントは、毎回再度要求することなく、一覧表示されているアクセス許可を使用できます。
- エージェントはバックグラウンドで実行して、自分または組織が構成したタスクを実行できます。

エージェントに無制限のアクセス権は付与されません。 表示された特定の項目のみが取得され、ユーザーまたは管理者が後で追加のアクセス許可を付与しない限り、外部に移動することはできません。

**不明な場合は、一時停止し、エージェントに付与するアクセス許可が安全かどうかを管理者に問い合わせてください**。

### エージェントまたはそのアクセス許可を無効にする

ユーザー (または管理者) は、エージェントまたはそのアクセス許可を削除または無効にすることができます。 詳細については、 [エンド ユーザー エクスペリエンスでのエージェントの管理に](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agent-identities-end-user)関するページを参照してください。 グローバル削除の場合、管理者は Microsoft Graph API または PowerShell Microsoft Entra使用してエージェント ID を削除できます。

### 疑わしいエージェントを報告する基準

次の場合にエージェントを報告します。

- 発行元名が間違っているか疑わしいと思われる。
- 説明されているタスクには、アクセス許可が広すぎるように見えます。
- 予期せずメッセージが表示されましたが、最近の変更ではその原因が説明されていません。
- エージェントはデータを誤用しているように見えます。

同意画面の **レポートはこちら** のリンクを使用するか、セキュリティ デスクに通知してください。

### ヘルプが必要ですか?

不明な場合は、承認しないでください。 スクリーンショットをキャプチャし、エージェント名をメモし、ヘルプデスクまたはセキュリティ チームに問い合わせてください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/what-are-agent-identities"} -->
## エージェント ID とは - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/what-are-agent-identities
- Service: entra-id / agent-id
- Article date: 2025-11-06
- Summary: エージェント ID、エンタープライズ環境での AI エージェントのセキュリティで保護された認証と承認を可能にする特殊な ID コンストラクトについて説明します。

エージェント ID は、AI エージェントに固有の ID と認証機能を提供するMicrosoft Entra ID内の ID アカウントです。 組織が自律 AI システムをデプロイするにつれて、人間のユーザーとアプリケーション向けに設計された ID モデルが不十分であることが証明されます。 エージェント ID は、エンタープライズ規模で動作する AI エージェントの固有の要件に特化して構築された特殊な ID コンストラクトを提供することで、このギャップに対処します。

### エージェントとは

エージェントは、その最も基本的な形式で、その環境/コンテキストを理解し、意思決定を行い、利用可能なツールを使用して自律的にそれらに対処することによって目標を達成しようとするアプリケーションです。 エージェントは、適切な目標または目標を提供された場合、人間の介入の有無にかかわらず行動できます。

エージェントの主なコンポーネントは次のとおりです。

- **モデル**: モデル ベースのエージェントには、エージェント プロセスの一元化された意思決定者として機能する言語モデルがあります。 モデルは、特定のエージェント アーキテクチャのニーズに基づいて汎用、マルチモーダル、または微調整できます。
- **オーケストレーションレイヤー**: エージェントが情報を取り込み、内部推論を実行し、その推論を使用して次のアクションを通知する循環的なプロセス。 このループは、エージェントが目標または停止ポイントに達するまで続行されます。 複雑さは、単純な決定ルールからチェーンされたロジックまでさまざまです。
- **メモリ**: エージェント内のメモリは、より動的で最新の情報をエージェントに提供し、応答が正確で関連性のあるものになるようにします。 このメモリを使用すると、開発者は、データ変換、モデルの再トレーニング、または微調整を必要とせずに、元の形式でより多くのデータをエージェントに提供できます。 静的な一般的な大規模言語モデル (LLM) とは異なり、最初にトレーニングされた知識のみが保持されます。
- **ツール**: ツールを使用すると、エージェントは自分の環境と対話し、機能を拡張できます。 ツールには、Web 検索、データベース アクセス、API、ファイル システム、または他のソフトウェアとの統合を含めることができます。 各ツールでは、セキュリティ、アクセス許可、およびエラー処理について慎重に検討する必要があります。 ツールを使用することで、エージェントは複雑なタスクを実行したり、情報にアクセスしたり、外部システムを制御したりできるため、より効果的で適応性が高くなります。

エージェント ワークフローの全体または一部は、人間のユーザーとは無関係に、自律的に計画および駆動されます。 エージェント ワークフローには、ユーザーが直接開始するものから自律的に動作するものまで、さまざまな種類があります。 これらのワークフローで使用されるサブコンポーネント、スキル、ツール、または API は、それ自体がエージェントである場合とそうでない場合があります。

### エージェント ID が存在する理由

自律型エンタープライズ システムとしての AI エージェントの登場により、既存の ID モデルでは適切に対処できないセキュリティと運用上の課題が生まれます。 エージェント ID は、次のような AI エージェントによってもたらされる特定のセキュリティの課題に対処するのに役立ちます。

- AI エージェントによって実行される操作と、従業員、顧客、またはワークロード ID によって実行される操作を区別する必要性。
- AI エージェントが複数のシステムにわたって適切なサイズのアクセスを取得できるようにします。
- AIエージェントが最も重要なセキュリティ役割やシステムにアクセスするのを防ぐこと。
- ID 管理を、迅速に作成および破棄される可能性のある多数の AI エージェントにスケーリングします。

### エージェント ID とアプリケーション ID

通常、Microsoft Entra IDのサービス プリンシパルとして表されるアプリケーション ID は、組織によって構築および管理されるサービス用に設計されました。 これらの ID は、長期的な安定性、既知の所有権、およびマネージド ライフサイクルの期待を伝達します。

エージェントは、多くの場合、自動化、Copilot Studioなどのツールのユーザー アクション、または API オーケストレーションによって動的に作成されます。 エージェントは、特定のタスク中に数分存在する場合もあれば、自動化されたワークフローの一部として 1 日に何千回も作成および破棄される場合があります。 既存のアプリケーション ID を使用してこのレベルのダイナミズムを管理すると、運用の複雑さとセキュリティの課題が生まれます。

エージェント ID は、適切なセキュリティ制御を提供しながら、この動的な性質を受け入れます。 組織は、エージェント ID を一括で作成し、すべてのエージェントに一貫性のあるポリシーを適用し、孤立した資格情報やアクセス許可の割り当てを残さずにエージェントを廃止できます。 ID モデルは、永続性ではなく、スケールとエフェメラリティのために設計されています。

### エージェント ID と人間のユーザー ID

人間のユーザー ID は、パスワード、多要素認証、パスキーなど、人間が毎日使用する認証メカニズムに関連付けられています。 人間のユーザーには、メールボックス、チーム、組織階層などのデータが関連付けられています。

エージェント ID は、人間ではなくソフトウェア システムを表します。 ユーザー認証メカニズムは使用しません。 ただし、特定のシナリオでは、エージェントが人間のユーザーであるかのように見えるようにし、動作させる必要があります。 これらのシナリオでは、エージェント ID をエージェントのユーザー アカウント (ペアのエージェント ID と 1 対 1 の関係を維持する特殊なMicrosoft Entraユーザー アカウント) とペアにすることができます。 この区別により、組織は、システムの互換性のために必要な場合にエージェントにユーザー ID を提供できる一方で、AI 主導の運用に対する明確な分離と適切なセキュリティ ポリシーを維持することができます。

### エージェントIDが可能にすることは何か

エージェント ID は、いくつかの主要な機能を有効にすることで、セキュリティで保護されたスケーラブルな AI エージェントのデプロイの基盤を提供します。 他のアカウントと同様に、エージェント ID は、主に組織内のアプリ、Web サービス、およびその他のシステムにアクセスするための手段です。 AI エージェントは、エージェント ID を使用して次のことができます。

- **Web サービスにアクセスします**。 エージェントは、Microsoft Entraからアクセス トークンを要求し、それらのトークンを使用して Web サービスにアクセスできます。 サービスには、Microsoft Graph、組織で構築されたサービス、サード パーティベンダーから購入したサービスなどのMicrosoft サービスを含めることができます。
- **Autonomous access**。 エージェントは、エージェント ID に直接与えられたアクセス権を使用して自律的に動作できます。 アクセス権には、Microsoft Graphアクセス許可、AZURE RBAC ロール、Microsoft Entra ディレクトリ ロール、Microsoft Entra アプリ ロールなどが含まれます。
- **委任されたアクセス**。 エージェントは、ユーザーに与えられたアクセス権を使用して、人間のユーザーに代わって行動できます。 ユーザーは、エージェント ID に委任される権限を制御できます。
- **受信メッセージを認証**する: エージェントは、他のクライアント、ユーザー、およびエージェントからの要求を受け入れます。 これらの要求は、Microsoft Entra IDによって発行されたアクセス トークンを使用してセキュリティで保護できます。これにより、エージェントは呼び出し元を確実に識別し、承認の決定を行うことができます。

### エージェント ID の実際の使用

いくつかのMicrosoft製品では、AI エージェントの認証にエージェント ID が既に使用されています。 2 つの例を次に示します。

- [**Entra 条件付きアクセス最適化エージェント**](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/agent-optimization): テナントでこのエージェントが有効になっている場合、エージェント ID が付与されます。 その後、Microsoft Entra条件付きアクセス最適化エージェントは、そのエージェント ID を使用してMicrosoft Entra システムに対してクエリを実行し、テナントの構成を検査します。 エージェントによって行われたすべてのクエリは、AI エージェントによって実行されたものとして記録されます。 エージェントのアクティビティは、エージェント ID に適用されるすべてのセキュリティ ポリシーの対象となります。 エージェントの ID は、**Agent ID** タブのMicrosoft Entra 管理センターで確認できます。
- [**Copilot Studioで作成されたエージェント**](https://learn.microsoft.com/ja-jp/copilot/security/agents-overview): Copilot Studioを使用する組織では、ユーザーはCopilot Studioのローコードツールを活用してAIエージェントを迅速に作成することができます。 AI エージェントが作成されるたびに、エージェントは Microsoft Entra テナント内のエージェント ID を取得します。 エージェントを作成したユーザーは、そのスポンサーとして記録されます。 エージェントは、エージェント ID を使用してユーザーにチャット メッセージを送受信し、SharePointや Dataverse などのさまざまなシステムに接続できます。 これらのエージェントによって実行されるすべての認証は、AI エージェントとしてMicrosoft Entra IDにログインし、Microsoft Entra 管理センターを介して表示できます。

### 始め方

Microsoft Entra エージェント IDは、エージェント ID とエージェント ID ブループリントを作成および管理するためのプラットフォームを提供する、Microsoft Entra内の製品です。 エージェント ID は、すべてのMicrosoft Entraユーザーが使用できます。

[Microsoft Agent 365](https://learn.microsoft.com/ja-jp/microsoft-agent-365/overview) を使用すると、エージェントはMicrosoft 365サービスとエンタープライズ ワークフロー全体で動作できます。これには、ユーザーごとに **Microsoft Agent 365** ライセンスが必要です。 価格の詳細については、「[Microsoft Agent 365 のプランと価格](https://www.microsoft.com/microsoft-agent-365#plans-and-pricing)を参照してください。

Microsoft Entraセキュリティ機能をエージェントに拡張するには、Microsoft Agent 365 が必要です。 エージェント 365 は Microsoft 365 E7 に含まれており、Microsoft E5/A5/Business Premium (または Microsoft Defender スイート + Microsoft Purview スイート) のアドオンとして使用できます。 詳細については、 [最新の Agent 365 製品条項を参照してください](https://www.microsoft.com/licensing/terms/productoffering/Agent365/EAEAS#clause-2755-h3-1)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/what-is-agent-id-platform"} -->
## Microsoft エージェント ID プラットフォームとは - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-agent-id-platform
- Service: entra-id / agent-id
- Article date: 2025-10-24
- Summary: Microsoft エージェント ID プラットフォーム、AI エージェント専用に設計された包括的な ID、および承認フレームワークについて説明します。 主要な概念には、エージェント レジストリ、認証プロトコル、トークン、クレーム、およびエージェント検出機能が含まれます。

Microsoft エージェント ID プラットフォームは、エンタープライズ環境で動作する AI エージェントによってもたらされる一意の認証、承認、ガバナンスの課題に対処するために構築された ID および承認フレームワークです。

Web サービス用に設計された非エージェント アプリケーション ID や人間向けに設計されたユーザー ID とは異なり、Microsoft エージェント ID プラットフォームは AI エージェント用に構築されています。 このプラットフォームには、AI エージェントが安全に認証し、リソースに適切にアクセスできるようにする特殊なコンポーネントが用意されています。 また、エージェントは他のエージェントを検出し、エンタープライズ レベルのガバナンス フレームワーク内で動作することもできます。

この概要では、Microsoft エージェント ID プラットフォームのコア コンポーネントについて説明します。 ここでは、エンタープライズ環境でのエージェント ID 管理の基盤となる ID コンストラクト、認証メカニズム、トークン システム、検出機能に重点を置いています。

### 始め方

Microsoft Entra エージェント IDは、エージェント ID とエージェント ID ブループリントを作成および管理するためのプラットフォームを提供する、Microsoft Entra内の製品です。 エージェント ID は、すべてのMicrosoft Entraユーザーが使用できます。

[Microsoft Agent 365](https://learn.microsoft.com/ja-jp/microsoft-agent-365/overview) を使用すると、エージェントはMicrosoft 365サービスとエンタープライズ ワークフロー全体で動作できます。これには、ユーザーごとに **Microsoft Agent 365** ライセンスが必要です。 価格の詳細については、「[Microsoft Agent 365 のプランと価格](https://www.microsoft.com/microsoft-agent-365#plans-and-pricing)を参照してください。

Microsoft Entraセキュリティ機能をエージェントに拡張するには、Microsoft Agent 365 が必要です。 エージェント 365 は Microsoft 365 E7 に含まれており、Microsoft E5/A5/Business Premium (または Microsoft Defender スイート + Microsoft Purview スイート) のアドオンとして使用できます。 詳細については、 [最新の Agent 365 製品条項を参照してください](https://www.microsoft.com/licensing/terms/productoffering/Agent365/EAEAS#clause-2755-h3-1)。

### プラットフォーム アーキテクチャの概要

Microsoft エージェント ID プラットフォームは、AI エージェントの完全な ID と承認ソリューションを提供するために連携するいくつかの基本的な技術コンポーネントに基づいて構築されています。

- **認証サービス**: OAuth 2.0 および OpenID Connect (OIDC) 標準に準拠した認証サービス。エージェントに対してセキュリティで保護された標準ベースの認証を可能にします。 このサービスは、エージェントがリソースと API に対する認証に使用するトークンを発行し、アプリケーション専用と委任されたアクセスの両方のシナリオをサポートします。 プラットフォームのコア ID コンストラクトを形成するオブジェクトは、エージェント ID ブループリント、エージェント ID、エージェントのユーザー アカウントの 3 つです。
- **SDK**: 開発者が Microsoft エージェント ID プラットフォームと統合できるようにするソフトウェア開発キット。 SDK はトークン取得とプロトコル処理の複雑さを抽象化するため、エージェントを構築するプラットフォームでは ID 管理をアプリケーションに簡単に組み込むことができます。 Microsoft エージェント ID プラットフォームには、Microsoft Identity Web (.NET) と Microsoft Entra ID 認証 SDK (サイドカー) の 2 つの SDK が含まれています。
- **Agent management**: 管理者がエージェントを検出、表示、構成、および管理できるようにする、Microsoft Entra 管理センター内の包括的なエージェント メタデータ ストアと管理インターフェイス。 このプラットフォームは、組織全体のエージェントを登録および管理するための一元化されたリポジトリであるエージェント レジストリを提供します。

これらの技術コンポーネントは、次のセクションで説明する ID コンストラクトと連携して、完全なプラットフォーム機能を提供します。

### 認証と承認

Microsoft エージェント ID プラットフォームでは、承認に OAuth を使用し、認証に OpenID Connect (OIDC) を使用します。

- **OpenID Connect (OIDC)** を使用すると、エージェントは通信相手の他のエンティティの ID を認証および検証し、セキュリティで保護された信頼関係を確立できます。
- **OAuth 2.0** を使用すると、エージェントは自身またはユーザーの代わりにリソースにアクセスすることを承認するアクセス トークンを要求でき、アプリケーション専用と委任されたアクセスの両方のシナリオをサポートします。

詳細については、「 [OAuth プロトコル」](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-oauth-protocols)を参照してください。

トークンは、Microsoft エージェント ID プラットフォームでセキュリティで保護された通信と承認を可能にする基本的なセキュリティ メカニズムです。 プラットフォームでは、特定の運用シナリオ用に設計された複数のトークン フロー パターンがサポートされています。 詳細については、Microsoft エージェント ID プラットフォームの tokens

### 統合と相互運用性

Microsoft エージェント ID プラットフォームは、Microsoft エコシステム以降でシームレスに動作するように設計されています。 次のと統合されます。

- **Microsoft Entra ID**: プラットフォームは、既存の ID インフラストラクチャとポリシーを使用して、エージェント シナリオをサポートするためにMicrosoft Entra ID機能を拡張します。
- **エージェントを作成するプラットフォームとサービス**: エージェントを作成および管理するプラットフォームは、Microsoft エージェント ID プラットフォームと統合してエージェントをセキュリティで保護できます。 これには、Copilot StudioやMicrosoft以外のプラットフォーム (Amazon Web Services (AWS) Bedrock、n8n など、OAuth 2.0 と OpenID Connect をサポートするその他のエージェント フレームワークなど、Microsoft所有のプラットフォームが含まれます。 組織は、プラットフォーム固有の資格情報管理を必要とせず、[Microsoft Entra ID認証 SDK (サイドカー)](https://learn.microsoft.com/ja-jp/entra/agent-id/authentication-with-auth-sdk-sidecar) または[ワークロード ID フェデレーション](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation)を使用して、これらのプラットフォームからエージェントをオンボードできます。
- **Microsoft ID とセキュリティ製品**: 条件付きアクセス、ID 保護、ID ガバナンス、グローバル セキュリティ アクセス、およびその他のセキュリティ サービスとの統合により、包括的なエージェント セキュリティが可能になります。

この相互運用性により、エージェントが作成またはデプロイされる場所に関係なく、組織はエージェント ID を一貫して構築、デプロイ、および管理できます。 Microsoft以外のエージェントの統合に関する詳細なガイダンスについては、「[サードパーティエージェントとMicrosoft Entra エージェント ID](https://learn.microsoft.com/ja-jp/entra/agent-id/configure-third-party-agents)を統合する」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/what-is-microsoft-entra-agent-id"} -->
## Microsoft Entra エージェント ID とは - Microsoft Entra Agent ID

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-microsoft-entra-agent-id
- Service: entra-id / agent-id
- Article date: 2026-04-14
- Summary: 組織がエンタープライズ規模で AI エージェント ID を構築、検出、管理、保護できるようにする ID とセキュリティ フレームワークである Microsoft Entra エージェント ID について説明します。

Microsoft Entra エージェント ID は、Microsoft Entra 機能を AI エージェントに拡張する ID とセキュリティ フレームワークです。 組織が支援型、自律型、ユーザー型のエージェントをデプロイする場合、これらの非人間的な ID を認証、承認、管理、保護するための専用の ID コンストラクトが必要です。 Microsoft Entra エージェント ID は、エンタープライズ規模でエージェント ID を管理するための統合プラットフォームを提供することで、これらのニーズに対応します。

[Image: AI エージェントに提供Microsoft Entra エージェント ID ID 管理、アクセス保護、ガバナンス、コンプライアンス機能を示す図。]

Microsoft Entra エージェント ID は、AI エージェントの ID 管理、アクセス保護、ガバナンス、コンプライアンスをまとめます。

### エージェント ID プラットフォーム

[Microsoft Entra エージェント ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/agent-id/what-is-agent-id-platform)を使用すると、開発者は AI エージェント用に構築された特殊な ID コンストラクトである [agent ID](https://learn.microsoft.com/ja-jp/entra/agent-id/what-are-agent-identities) を作成および管理できます。 エージェント ID ブループリントは、親子関係を持つ個々のエージェント ID を作成するためのテンプレートとして機能し、多数のエージェント間で一貫したセキュリティ ポリシーを有効にします。 このプラットフォームでは、認証とエージェント間通信のために、OAuth 2.0、モデル コンテキスト プロトコル (MCP)、エージェント間 (A2A) などの標準プロトコルがサポートされています。

Microsoft Entra エージェント IDは、MicrosoftプラットフォームとMicrosoft以外のプラットフォーム上に構築されたエージェントで動作します。 組織は、Microsoft Entra ID認証 SDK (サイドカー) またはワークロード ID フェデレーションを使用して AWS Bedrock や n8n などのプラットフォームから[サードパーティのエージェントを統合](https://learn.microsoft.com/ja-jp/entra/agent-id/configure-third-party-agents)し、構築された場所に関係なくすべてのエージェントに管理 ID を付与できます。

### エージェントのセキュリティとガバナンス

Microsoft Entra エージェント ID は、既存の Microsoft Entra のセキュリティおよびガバナンス機能をエージェント ID に拡張します。 エージェントは、アダプティブ アクセス ポリシー、リアルタイム リスク検出、ライフサイクル管理、ネットワーク レベルの制御など、ユーザーやワークロードと同じ ID ドリブン保護を受け取ります。 コンプライアンスと監査のために、すべてのエージェント認証とアクティビティがログに記録されます。

エージェントに対してこれらの機能がどのように機能するかの詳細については、以下を参照してください。

- [AI 用 Microsoft Entra セキュリティの概要](https://learn.microsoft.com/ja-jp/entra/agent-id/security-for-ai-overview)
- [エージェントの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/agent-id)
- [エージェントのアイデンティティ保護](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-risky-agents)
- [エージェントの ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-id-governance-overview)
- [エージェントのネットワーク制御](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-secure-web-ai-gateway-agents)
- [エージェントのサインインと監査ログ](https://learn.microsoft.com/ja-jp/entra/agent-id/sign-in-audit-logs-agents)

### 始め方

Microsoft Entra エージェント IDは、エージェント ID とエージェント ID ブループリントを作成および管理するためのプラットフォームを提供する、Microsoft Entra内の製品です。 エージェント ID は、すべてのMicrosoft Entraユーザーが使用できます。

[Microsoft Agent 365](https://learn.microsoft.com/ja-jp/microsoft-agent-365/overview) を使用すると、エージェントはMicrosoft 365サービスとエンタープライズ ワークフロー全体で動作できます。これには、ユーザーごとに **Microsoft Agent 365** ライセンスが必要です。 価格の詳細については、「[Microsoft Agent 365 のプランと価格](https://www.microsoft.com/microsoft-agent-365#plans-and-pricing)を参照してください。

Microsoft Entraセキュリティ機能をエージェントに拡張するには、Microsoft Agent 365 が必要です。 エージェント 365 は Microsoft 365 E7 に含まれており、Microsoft E5/A5/Business Premium (または Microsoft Defender スイート + Microsoft Purview スイート) のアドオンとして使用できます。 詳細については、 [最新の Agent 365 製品条項を参照してください](https://www.microsoft.com/licensing/terms/productoffering/Agent365/EAEAS#clause-2755-h3-1)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/agent-id/whats-new-agent-id"} -->
## Microsoft Entra エージェント IDの新機能

- Source: https://learn.microsoft.com/ja-jp/entra/agent-id/whats-new-agent-id
- Service: entra-id / agent-id
- Article date: 2026-05-01
- Summary: Microsoft以外の統合、移行ガイド、エンタープライズ ガバナンスなど、一般提供時のMicrosoft Entra エージェント IDの新機能と更新プログラムについて説明します。

Microsoft Entra エージェント IDが一般公開されました。 このリリースでは、AI エージェントにファースト クラスの ID とアクセス管理が提供され、組織は企業規模でエージェント ID の認証、承認、管理、保護を行うことができます。 Microsoft Entra エージェント IDは、専用の ID コンストラクト、特殊な OAuth フロー、包括的なセキュリティ制御を使用して、ゼロ トラスト原則を AI ワークロードに拡張します。

この記事では、現在使用できる主な機能とドキュメントをまとめます。

### 大規模な AI エージェントの管理

Microsoft Entra エージェント IDでは、AI エージェント専用に設計された新しい ID コンストラクトと認証プロトコルが導入されています。 注目すべき更新プログラムは次のとおりです。

- [主要な概念](https://learn.microsoft.com/ja-jp/entra/agent-id/key-concepts) - コア エージェント ID の概念とその関係をさらに定義するために新しい概念が追加されました。
- [管理関係](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-owners-sponsors-managers) - より深い定義と、エージェント ID、ブループリント、エージェントのユーザー アカウントの所有者、スポンサー、およびマネージャーの違いを明確にしました。
- [設計パターン](https://learn.microsoft.com/ja-jp/entra/agent-id/concept-agent-id-design-patterns) (新規) - エージェント ID デプロイの一般的なアーキテクチャ パターン。
- [ベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/agent-id/best-practices-agent-id) (新規) - エージェント ID 管理に推奨される方法。
- [エージェント ID アーキテクチャを計画する](https://learn.microsoft.com/ja-jp/entra/agent-id/how-to-plan-agent-identity-architecture) (新規) - 大規模なエージェント ID のデプロイを計画するためのガイダンス。
- [エージェント ID ブループリント](https://learn.microsoft.com/ja-jp/entra/agent-id/create-blueprint)および[エージェント ID の作成](https://learn.microsoft.com/ja-jp/entra/agent-id/create-delete-agent-identities) (プレビュー) - 新しい "ウィザード" を使用して、Microsoft Entra 管理センターにエージェント ID ブループリントとエージェント ID を作成します。
- [AI ガイド付きセットアップ](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-id-ai-guided-setup) (新規) - ブループリントの作成、資格情報の構成、およびエージェント ID のプロビジョニングについて説明する AI コーディング エージェントによるオンボードを自動化します。
- [エージェント ID の削除](https://learn.microsoft.com/ja-jp/entra/agent-id/concept-agent-identity-deletion) (新規) - エージェント ID の自動カスケード クリーンアップ プロセスと論理的な削除機能について説明します。
- [Auth SDK (サイドカー) による認証 (](https://learn.microsoft.com/ja-jp/entra/agent-id/authentication-with-auth-sdk-sidecar) 新規) - エージェント認証のサイドカー パターンの概要。
- [エージェント ID Microsoft Entra ID Auth SDK (サイドカー) を構成する](https://learn.microsoft.com/ja-jp/entra/agent-id/microsoft-entra-sdk-for-agent-identities) - トークン取得用の SDK 構成。
- [ローカル開発用にサイドカーを実行](https://learn.microsoft.com/ja-jp/entra/agent-id/sidecar-local-development) する (新規) - Auth SDK のローカル開発セットアップ。
- [ダウンストリーム API でエージェント トークンを検証する](https://learn.microsoft.com/ja-jp/entra/agent-id/how-to-validate-agent-tokens-downstream-api) (新規) - エージェント トークンを受信する API のトークン検証ガイダンス。
- [エージェント ID を持つ非Microsoft エージェントの構成](https://learn.microsoft.com/ja-jp/entra/agent-id/configure-third-party-agents) (新規) - AWS、GCP、n8n などのプラットフォームの統合パターン (サイドカーとフェデレーション)。
- [Amazon Bedrock エージェントをセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/agent-id/integrate-aws-bedrock-agent) (新規) - エージェント ID を使用して Bedrock エージェントをセキュリティで保護するためのステップ バイ ステップ ガイド。
- [n8n エージェントをセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/agent-id/integrate-n8n-agent) (新規) - エージェント ID 統合を使用してAzureに n8n をデプロイします。
- [カスタム アプリ登録の移行](https://learn.microsoft.com/ja-jp/entra/agent-id/migrate-custom-app-registrations-to-agent-id) (新規) - 標準アプリ登録を使用してエージェント ID にエージェントを移動します。
- [Copilot Studio エージェントの移行](https://learn.microsoft.com/ja-jp/entra/agent-id/migrate-copilot-studio-agents-to-agent-id) (新規) - Microsoft Copilot Studio エージェントをエージェント ID に移動します。

企業全体のエージェント管理を簡略化するために、エージェント レジストリ エクスペリエンスは [Microsoft Agent 365](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/agent-registry) に集約されています。 この変更により、すべてのエージェントを検出して管理する 1 つの場所が顧客に与えられますが、Microsoft Entraはエージェント ID を通じて ID 基盤を提供し続けます。 詳細については、「 [エージェント レジストリと Microsoft Agent 365 の統合](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-registry-convergence)」を参照してください。

### エージェントの ID とライフサイクルを管理する

Microsoft Entra ID ガバナンスは、ライフサイクルとアクセス管理機能をエージェント ID に拡張します。

- [エージェント ID 管理の概要](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-id-governance-overview) - Microsoft Entra がエージェント ID のライフサイクルとアクセスをどのように管理するかの概要。
- [エージェント ID のアクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/agent-id/agent-access-packages) - ポリシー ベースのアクセス パッケージと、代理 (OBO) と自律 (OBO 以外) の両方のシナリオのアクセス許可の割り当てを通じてエージェント アクセスを管理します。
- [スポンサー ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/agent-sponsor-tasks) (新規) - エージェントブループリントとエージェント ID のスポンサーメンテナンスと再割り当てを自動化します。
- [エージェント ID の管理](https://learn.microsoft.com/ja-jp/entra/agent-id/manage-agent-identities-end-user) (新規) - 所有またはスポンサーのエージェント ID を表示および制御します。
- [エージェント ID スポンサー テンプレート](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-templates) (新規) - 2 つの新しいライフサイクル ワークフロー テンプレート。マネージャーと共同スポンサーに通知し、エージェント ID スポンサーがロールを変更したり、組織を離れたりしたときにスポンサーシップを自動的に転送して、孤立したエージェントを防ぎます。

### リソースへのエージェント アクセスを保護する

条件付きアクセスと ID 保護機能は、エージェント ID とそのリソースへのアクセスをセキュリティで保護するために、Microsoft Entra エージェント IDを拡張します。

- [エージェントの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/agent-id) (更新) - 条件付きアクセス ポリシーのガイダンスと詳細なシナリオ、およびエージェント固有のポリシー用の新しいテンプレートが改善されました。
- [リスクの高いエージェント ID のアクセスをブロックする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-agent-block-high-risk) (新規) - 危険なエージェント ID からのサインインをブロックするための条件付きアクセス テンプレート。
- [自律エージェント アクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-autonomous-agents) (新規) - ユーザー コンテキストのない自律エージェントの条件付きアクセス テンプレート。
- [エージェント アクセス ポリシーの代理](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-on-behalf-of-agents) (新規) - ユーザーに代わって動作するエージェントの条件付きアクセス テンプレート。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform"} -->
## Microsoft ID プラットフォームのドキュメント - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform
- Service: identity-platform
- Article date: 2025-02-11
- Summary: OAuth 2.0 および OpenID Connect (OIDC) で Microsoft Entra を使用して、ビルドするアプリと Web API を保護します。 Microsoft が提供するクイックスタート、チュートリアル、コード サンプル、API リファレンス ドキュメントを使用して、ユーザーのサインインとアクセスの管理を行う方法について説明します。 

Microsoft ID プラットフォームとオープンソースの認証ライブラリを使用して、Microsoft Entra アカウント、Microsoft 個人用アカウント、および Facebook や Google などのソーシャル アカウントでユーザーをサインインさせます。 Web API を保護し、ユーザーと組織のデータを操作するために Microsoft Graph のような保護された API にアクセスします。

概要
[Microsoft ID プラットフォームとは](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview)

概念
[認証と承認の基本](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-vs-authorization)

概念
[アプリの種類と認証フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios)

sample
[コード サンプル](https://learn.microsoft.com/ja-jp/entra/identity-platform/sample-v2-code)

新機能
[ドキュメントの最新情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/whats-new-docs)

概念
[OAuth 2.0 および OpenID Connect (OIDC)](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols)

概念
[MSAL へのアプリの移行](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-migration)

クイックスタート
[アプリケーションを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)

### アプリの認証と承認

ID およびアクセス管理 (IAM) のサポートを使用して構築または拡張しているアプリのドキュメントに焦点を当てるには、その種類を選択します。

[シングルページ アプリ (SPA)](https://learn.microsoft.com/ja-jp/entra/identity-platform/index-spa)
コードがダウンロードされ、ブラウザー自体で実行される Web アプリ。

[Web アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/index-web-app)
サーバー上でコードが実行され、ブラウザーがレンダリングするページ データを返す、従来の、つまり "クラシック" web アプリ。

[Web API](https://learn.microsoft.com/ja-jp/entra/identity-platform/index-web-api)
アプリやその他の Web API によってアクセスされる RESTful web サービス。通常は、API によって提供されるデータを操作します。

[デスクトップ アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/index-desktop)
ユーザーのデスクトップ、ラップトップ、またはノート PC でコードを実行するユーザーインターフェイス (UI) を備えたアプリ。

[モバイル アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/index-mobile)
ユーザーの電話、タブレット、またはその他のモバイル デバイスでコードを実行する UI があるアプリ。

[バックグラウンド サービス、デーモン、またはスクリプト](https://learn.microsoft.com/ja-jp/entra/identity-platform/index-service)
サーバー間の通信やスケジュールされたジョブなど、非対話型のタスクを実行する UI を使用しないアプリまたはスクリプト。

### 作業の開始

アプリにコア IAM 機能を追加するためのガイダンスと、アプリの安全性と可用性を保つためのベストプラクティスにすばやくアクセスできます。

#### ユーザーのサインイン

- [シングルページの Web アプリ (SPA)](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-sign-in)
- [Web アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-dotnet-core-sign-in)
- [デスクトップ アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-desktop-app-nodejs-electron-sign-in)
- [モバイル アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-android-sign-in)

#### Web API を保護する

- [Web API を公開するようにアプリを構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-expose-web-apis)
- [保護された Web API の構築](https://learn.microsoft.com/ja-jp/entra/identity-platform/web-api-tutorial-01-register-app)
- [非対話型アプリまたはスクリプトから API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-api-aspnet-protect-api)

#### アプリをテストおよびデプロイする

- [テスト環境を構築する](https://learn.microsoft.com/ja-jp/entra/identity-platform/test-setup-environment)
- [統合テストを実行する](https://learn.microsoft.com/ja-jp/entra/identity-platform/test-automate-integration-testing)

#### セキュリティと回復性を高めるための構築

- [ゼロ トラスト対応アプリのビルド](https://learn.microsoft.com/ja-jp/entra/identity-platform/zero-trust-for-developers)
- [特権が過剰なアプリを防止する](https://learn.microsoft.com/ja-jp/entra/identity-platform/secure-least-privileged-access)
- [認証の回復性をアプリに組み込む](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-app-development-overview)

### Microsoft 認証ライブラリ

オープンソースの Microsoft Authentication Library (MSAL) は、Microsoft によって構築およびサポートされています。 認証と認可に Microsoft ID プラットフォームを使用するすべてのアプリに対して MSAL をお勧めします。

[。網](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/)

[アンドロイド](https://github.com/AzureAD/microsoft-authentication-library-for-android)

[Angular（アンギュラー）](https://learn.microsoft.com/ja-jp/javascript/api/%40azure/msal-angular/)

[iOS と macOS](https://github.com/AzureAD/microsoft-authentication-library-for-objc)

[ジャワ](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j)

[JavaScript](https://learn.microsoft.com/ja-jp/javascript/api/overview/msal-overview)

[Node.js](https://learn.microsoft.com/ja-jp/javascript/api/%40azure/msal-node/)

[Python（プログラミング言語）](https://learn.microsoft.com/ja-jp/entra/msal/python/)

[反応する](https://learn.microsoft.com/ja-jp/javascript/api/%40azure/msal-react/)

#### パートナーと顧客を認証する

企業間 (B2B) シナリオでパートナー組織のユーザーをサインインさせるか、企業消費者間取引 (B2C) シナリオで顧客向けにカスタムのサインアップおよびサインイン エクスペリエンスを作成します。

- [External Identities のドキュメント](https://learn.microsoft.com/ja-jp/entra/external-id/)

#### Microsoft Graph に接続する

Microsoft Entra ID に格納されている組織、ユーザー、アプリのデータへのプログラムによるアクセス。 アプリから Microsoft Graph を呼び出して、Microsoft Entra のユーザーとグループの作成と管理、プロファイル、予定表、電子メールなどのユーザーのデータの取得と変更を行います。

- [Microsoft Graph API のドキュメント](https://learn.microsoft.com/ja-jp/graph/overview)

#### アプリを管理および販売する

Dropbox、Salesforce、ServiceNow などの既存の SaaS アプリを組織のユーザーが利用できるようにし、シングル サインオン (SSO) を構成し、セキュリティを管理します。 または、Microsoft Entra ID を使用する \*他の\* 組織が使用する自分独自の SaaS アプリを公開することで、独立系ソフトウェア ベンダー (ISV) になります。

- [アプリ管理のドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-application-management)

#### アプリ ユーザーとそのアクセスの管理

組織の SaaS アプリでユーザー ID とそのロールを自動的に作成します。 HR 主導のプロビジョニング、クロスドメイン ID 管理システム (SCIM) など。

- [アプリ ユーザーとロールのプロビジョニングに関するドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/access-token-claims-reference"} -->
## アクセス トークン クレームのリファレンス - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/access-token-claims-reference
- Service: identity-platform
- Article date: 2023-05-26
- Summary: Microsoft ID プラットフォームから発行されるアクセス トークンに含まれるクレームの詳細に関するクレームのリファレンス。

アクセス トークンは [JSON Web トークン (JWT)](https://wikipedia.org/wiki/JSON_Web_Token) です。 JWT には、次の要素が含まれます。

- **ヘッダー** - トークンの種類や署名方法の情報など、トークンの検証方法に関する情報を提供します。
- **ペイロード** - サービスを呼び出そうとしているユーザーまたはアプリケーションに関する重要なデータがすべて含まれています。
- **署名** - トークンの検証に使用される原材料です。

各部分はピリオド (`.`) で区切られ、Base64 で個別にエンコードされます。

クレームは、そこに入力される値が存在する場合にのみ存在します。 アプリケーションは要求の存在に依存することはできません。 例として、`pwd_exp` (すべてのテナントでパスワードを期限切れにする必要があるわけではありません) や、`family_name` (名前のないアプリケーションに代わって、[クライアント資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)フローが使用されます) などがあります。 アクセス トークンには、アクセス評価のための十分な要求が常に含まれます。

Microsoft ID プラットフォームでは、いくつかの要求を使用して、再利用のためにトークンをセキュリティで保護します。 `Opaque` の説明では、これらの要求は一般に公開されないものとして示されています。 これらのクレームはトークンに表示される場合とされない場合があり、新しいものが予告なく追加される場合もあります。

大事な

アプリケーションは、要求が存在するか、特定の順序で、ハード依存関係を取るべきではありません。 新しい機能がサポートされるため、新しい要求を追加したり、要求を省略可能から必須に変更したりできます。

### ヘッダーのクレーム

| 要求 | フォーマット | 説明 |
| --- | --- | --- |
| `typ` | 文字列 - 常に `JWT` | トークンが JWT であることを示します。 |
| `alg` | 糸 | トークンの署名に使用されたアルゴリズム (`RS256` など) を示します。 |
| `kid` | 糸 | トークンの署名を検証するために使用される公開キーの拇印を指定します。 v1.0 と v2.0 のどちらのアクセス トークンでも生成されます。 |
| `x5t` | 糸 | `kid` と同様に機能します (使用方法も値も同じ)。 `x5t` は、互換性を目的として v1.0 アクセス トークンでのみ生成されるレガシ クレームです。 |

### ペイロードのクレーム

| 要求 | フォーマット | 説明 | 認可に関する考慮事項 |
| --- | --- | --- | --- |
| `acrs` | 文字列の JSON 配列 | ベアラーで実行できる操作の認証コンテキスト ID を示します。 認証コンテキスト ID を使用して、アプリケーションとサービス内からステップアップ認証の要求をトリガーできます。 多くの場合、`xms_cc` クレームと共に使用されます。 |  |
| `aud` | 文字列、アプリケーション ID URI、または GUID | トークンの想定されている読者を識別します。 v2.0 トークンでは、この値は常に API のクライアント ID です。 v1.0 トークンでは、これは、クライアント ID、または要求で使用されるリソース URI になります。 値は、クライアントがトークンを要求した方法によって異なります。 | この値は検証の必要があり、値が対象と一致しない場合はトークンを拒否します。 |
| `iss` | 文字列、セキュリティ トークン サービス (STS) URI | トークンを作成して返す STS と、認証されたユーザーの Microsoft Entra テナントを識別します。 発行されたトークンが v2.0 トークンである場合 (`ver` 要求を参照)、URI は `/v2.0` で終了します。 ユーザーが Microsoft アカウントを持つコンシューマー ユーザーであることを示す GUID は `9188040d-6c67-4c5b-b112-36a304b66dad` です。 | アプリケーションでは、要求の GUID 部分を使用して、アプリケーションにサインインできるテナントのセットを制限できます (該当する場合)。 |
| `idp` | 文字列 (通常は STS URI) | トークンのサブジェクトを認証した ID プロバイダーを記録します。 この値は、発行者とテナントが異なるユーザー アカウント (ゲストなど) の場合を除いて、発行者クレームの値と同じです。 要求が存在しない場合は、`iss` の値を使用します。 個人用アカウントが組織のコンテキストで使用されている場合 (たとえば、個人用アカウントが Microsoft Entra テナントに招待された場合)、`idp` 要求は "live.com" または Microsoft アカウント テナント `9188040d-6c67-4c5b-b112-36a304b66dad` を含む STS URI である可能性があります。 この要求は、ゲスト ユーザーが関係するフェデレーション ドメイン シナリオで生成され、常にマネージド ドメイン のシナリオで送信する必要があります。 |  |
| `iat` | int、Unix タイムスタンプ | このトークンの認証がいつ行われたのかを示します。 |  |
| `nbf` | int、Unix タイムスタンプ | JWT を処理できるようになる時間を指定します。 |  |
| `exp` | int、Unix タイムスタンプ | JWT が期限切れになる時間を指定します。この時間より前は、JWT を受け入れて処理できます。 この日時より前でも、リソースがトークンを拒否する場合もありあす。 認証で必要な変更や、トークンが取り消されたときに、拒否が発生する場合があります。 |  |
| `aio` | あいまいな文字列 | Microsoft Entra ID がトークン再利用のためにデータの記録に使用する内部の要求。 リソースでこの要求を使用しないでください。 |  |
| `acr` | 文字列、「`0`」 または 「`1`」、v1.0 トークンにのみ存在する | "認証コンテキスト クラス" 要求の値 「`0`」 は、エンドユーザーの認証が ISO/IEC 29115 の要件を満たしていないことを示します。 |  |
| `amr` | 文字列の JSON 配列 | トークンのサブジェクトの認証方法を識別します。 |  |
| `appid` | 文字列、GUID、v1.0 トークンにのみ存在する | トークンを使用するクライアントのアプリケーションID。 アプリケーションとして識別することもできますが、アプリケーションを使用しているユーザーとして識別することもできます。 アプリケーション ID は通常、アプリケーション オブジェクトを表しますが、Microsoft Entra ID 内のサービス プリンシパル オブジェクトを表すこともできます。 | `appid` は認可の決定に使用できます。 |
| `azp` | 文字列、GUID、v2.0 トークンにのみ存在する | `appid` に代わるものです。 トークンを使用するクライアントのアプリケーションID。 アプリケーションとして識別することもできますが、アプリケーションを使用しているユーザーとして識別することもできます。 アプリケーション ID は通常、アプリケーション オブジェクトを表しますが、Microsoft Entra ID 内のサービス プリンシパル オブジェクトを表すこともできます。 | `azp` は認可の決定に使用できます。 |
| `appidacr` | 文字列、「`0`」、「`1`」または 「`2`」、v1.0 トークンにのみ存在する | クライアントの認証方法を示します。 パブリック クライアントの場合、値は "`0`" です。 クライアント ID とクライアント シークレットを使用する場合、値は `1` です。 クライアント証明書を認証に使用する場合、値は `2` です。 |  |
| `azpacr` | 文字列、「`0`」、「`1`」または 「`2`」、v2.0 トークンにのみ存在する | `appidacr` に代わるものです。 クライアントの認証方法を示します。 パブリック クライアントの場合、値は "`0`" です。 クライアント ID とクライアント シークレットを使用する場合、値は `1` です。 クライアント証明書を認証に使用する場合、値は `2` です。 |  |
| `preferred_username` | 文字列、v2.0 トークンにのみ存在する | ユーザーを表すプライマリ ユーザー名です。 この値には、電子メール アドレス、電話番号、または指定された書式のない一般的なユーザー名を指定できます。 この値をユーザー名のヒントとして使用したり、人間が判読できる UI でユーザー名として使用します。 この要求を受け取るには `profile` スコープを使用します。 | この値は変更可能なため、認可の判断には使用しないでください。 |
| `name` | 糸 | トークンのサブジェクトを識別する、人間が判読できる値を提供します。 値は変化する場合があり、変更可能であり、表示のみを目的としています。 この要求を受け取るには `profile` スコープを使用します。 | この値は認可の判断に使用しないでください。 |
| `scp` | 文字列、スコープのスペース区切りリスト | クライアント アプリケーションが同意を要求し、同意を得た、アプリケーションによって公開されているスコープのセット。 ユーザー トークンにのみ含まれます。 事前認証されたスコープを含めることができます。これは、すべてのテナントで許可できない可能性があります。 | アプリケーションでは、これらのスコープがアプリケーションによって公開されている有効なスコープであることを確認し、これらのスコープの値に基づいて認可を決定する必要があります。 |
| `roles` | 文字列の配列、アクセス許可の一覧 | 要求元のアプリケーションまたはユーザーに呼び出しのアクセス許可が付与されている、アプリケーションによって公開されているアクセス許可のセット。 アプリケーション トークンで、このアクセス許可のセットはユーザー スコープの代わりに[クライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)で使用されます。 ユーザー トークンで、この値のセットにはターゲット アプリケーションのユーザーに割り当てられたロールが含まれます。 事前認証されたアプリ ロールを含めることができます。これは、すべてのテナントで許可できない可能性があります。 | これらの値はアクセスの管理 (リソースへのアクセスに認可を適用するなど) に使用できます。 |
| `wids` | [RoleTemplateID](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#all-roles) GUID の配列 | [Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#all-roles)に存在するロールのセクションから、このユーザーに割り当てられたテナント全体のロールを示します。 この要求は、`groupMembershipClaims`の  プロパティによって、アプリケーションごとに構成されます。 要求を `All` や `DirectoryRole` に設定します。 | これらの値はアクセスの管理 (リソースへのアクセスに認可を適用するなど) に使用できます。 |
| `groups` | GUID の JSON 配列 | サブジェクトのグループ メンバーシップを表すオブジェクト ID です。 このグループ要求は、`groupMembershipClaims`の  プロパティによって、アプリケーションごとに構成されます。 値が `null` の場合はすべてのグループが除外され、値が `SecurityGroup` の場合はディレクトリ ロールと Active Directory セキュリティ グループのメンバーシップが含まれ、値が `All` の場合はセキュリティ グループと Microsoft 365 配布リストの両方が含まれます。 他のフローでは、ユーザーが属するグループの数が SAML の場合は 150、JWT の場合は 200 を超えた場合、Microsoft Entra ID によって要求ソースに超過要求が追加されます。 要求ソースは、ユーザーのグループのリストを含む Microsoft Graph エンドポイントを参照します。 | これらの値はアクセスの管理 (リソースへのアクセスに認可を適用するなど) に使用できます。 |
| `hasgroups` | ブール値 | 存在する場合、常に `true` であり、ユーザーが 1 つ以上のグループに属しているかどうかを示します。 クライアントが Microsoft Graph API を使用して、ユーザーのグループ (`https://graph.microsoft.com/v1.0/users/{userID}/getMemberObjects`) を決定する必要があることを示します。 |  |
| `groups:src1` | JSON オブジェクト | トークン要求がトークンに対して大きすぎる場合は、ユーザーの完全なグループ リストへのリンクを含めます。 SAML では `groups` 要求の代わりに新しい要求として、JWT では分散要求として使用されます。 **JWT 値の例**: `"groups":"src1"``"_claim_sources`: `"src1" : { "endpoint" : "https://graph.microsoft.com/v1.0/users/{userID}/getMemberObjects" }` |  |
| `sub` | 糸 | トークンに関連付けられているプリンシパル。 たとえば、アプリケーションのユーザーです。 この値は不変であり、再割り当てや再利用をしないでください。 サブジェクトは、特定のアプリケーション ID に一意のペアワイズ識別子です。 1 人のユーザーが 2 つの異なるクライアント ID を使用して 2 つの異なるアプリケーションにサインインすると、そのアプリケーションは、サブジェクト要求に対して 2 つの異なる値を受け取ります。 2 つの異なる値の使用は、アーキテクチャとプライバシーの要件によって異なります。 `oid` 要求 (テナント内のアプリケーション全体で同じまま) もご覧ください。 | トークンを使用してリソースにアクセスする場合など、認可チェックを実行するためにこの値を使用できます。また、データベース テーブルのキーとして使用することもできます。 |
| `oid` | 文字列、GUID | 要求元の不変識別子。これは、ユーザーやサービス プリンシパルの検証済み ID です。 この ID によって、複数のアプリケーションで要求元が一意に識別されます。 同じユーザーにサインインする 2 つの異なるアプリケーションは `oid` 要求で同じ値を受け取ります。 Microsoft Graph などの Microsoft オンライン サービスに対してクエリを実行するときに `oid` を使用できます。 Microsoft Graph は、この ID を、指定されたユーザー アカウントの `id` プロパティとして返します。 `oid` では複数のアプリケーションがプリンシパルを相互に関連付けられるため、ユーザーがこの要求を受け取るには、`profile` スコープを使用します。 1 人のユーザーが複数のテナントに存在する場合、そのユーザーのオブジェクト ID はテナントごとに異なります。 ユーザーは同じ資格情報で各アカウントにログインしますが、アカウントは異なります。 | トークンを使用してリソースにアクセスする場合など、認可チェックを実行するためにこの値を使用できます。また、データベース テーブルのキーとして使用することもできます。 |
| `tid` | 文字列、GUID | ユーザーがサインインしているテナントを表します。 職場または学校アカウントの場合、GUID はユーザーがサインインしている組織の不変のテナント ID です。 個人用 Microsoft アカウント テナント (Xbox、Teams for Life、Outlook のようなサービス) へのサインインの場合、値は `9188040d-6c67-4c5b-b112-36a304b66dad` です。 この要求を受け取るには、アプリケーションが `profile` スコープを要求する必要があります。 | 認可の決定において、この値は他の要求と組み合わせて考慮する必要があります。 |
| `sid` | 文字列、GUID | セッションの一意の識別子を表し、新しいセッションが確立されたときに生成されます。 |  |
| `unique_name` | 文字列、v1.0 トークンにのみ存在する | トークンのサブジェクトを識別する、人が判読できる値を提供します。 | この値はテナント内で異なる場合があり、表示目的でのみ使用します。 |
| `uti` | 糸 | トークン識別子要求。JWT 仕様の `jti` と同等です。 大文字と小文字を区別する一意のトークンごとの識別子。 |  |
| `rh` | あいまいな文字列 | Azure がトークンの再検証に使用する内部要求。 リソースでこの要求を使用しないでください。 |  |
| `ver` | 文字列、`1.0` または `2.0` | アクセス トークンのバージョンを示します。 |  |
| `xms_cc` | 文字列の JSON 配列 | トークンを取得したクライアント アプリケーションがクレームのチャレンジを処理できるかどうかを示します。 多くの場合、`acrs` クレームと共に使用されます。 このクレームは、条件付きアクセスと継続的アクセス評価のシナリオでよく使用されます。 トークンの発行対象であるリソース サーバーまたはサービス アプリケーションで、トークン内のこのクレームの存在を制御します。 アクセス トークン内の `cp1` の値は、クライアント アプリケーションでクレーム チャレンジを処理できることを識別するための信頼できる方法です。 詳細については、「[クレーム チャレンジ、クレーム要求、およびクライアントの機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-challenge?tabs=dotnet)」を参照してください。 |  |

注

`roles`、`groups`、`scp`、および `wids` 要求は、リソースがユーザーやアプリケーションを認可する方法を網羅したリストでもなければ、呼び出し元に付与されるアクセス許可を網羅したリストでもありません。 ターゲット リソースは、保護されたリソースへのアクセスを認可するのに別の方法を使用しているかもしれません。

#### グループ超過要求

Microsoft Entra ID では、グループ要求に含まれるオブジェクト ID の数が、HTTP ヘッダーのサイズ制限内に収まるように制限されます。 超過制限 (SAML トークンの場合は 150、JWT トークンの場合は 200) を超えるグループのメンバーにユーザーがなっている場合、Microsoft Entra ID は、グループ要求をトークンに出力しません。 代わりに、Microsoft Graph API に照会してユーザーのグループ メンバーシップを取得するようアプリケーションに指示する超過要求がトークンに追加されます。

```JSON
{
    ...
    "_claim_names": {
        "groups": "src1"
    },
    "_claim_sources": {
        "src1": {
            "endpoint": "[Url to get this user's group membership from]"
        }   
    }
    ...
}
```

超過のシナリオは、`BulkCreateGroups.ps1` フォルダーにある  を使用してテストします。

注

返される URL は、Azure AD Graph の URL (つまり、graph.windows.net) になります。 サービスでは、この URL に依存するのではなく、オプションの要求 (トークンがアプリまたはアプリ+ ユーザー トークンのどちらであるかを識別する) を使用 `idtyp` して、グループの完全な一覧に対してクエリを実行するための Microsoft Graph URL を作成する必要があります。

#### v1.0 の基本要求

v1.0 トークンには、該当する場合は次の要求が含まれますが、v2.0 トークンには既定では含まれません。 v2.0 に対してこれらの要求を使用するために、アプリケーションの要求では[省略可能な要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)が使用されます。

| 要求 | フォーマット | 説明 |
| --- | --- | --- |
| `ipaddr` | 糸 | ユーザーが認証された IP アドレス。 |
| `onprem_sid` | 文字列 ([SID 形式](https://learn.microsoft.com/ja-jp/windows/win32/secauthz/sid-components)) | ユーザーがオンプレミス認証を行った場合、この要求によって SID が提供されます。 レガシ アプリケーションでの承認にこの要求を使用できます。 |
| `pwd_exp` | int、Unix タイムスタンプ | ユーザーのパスワードの有効期限を示します。 |
| `pwd_url` | 糸 | ユーザーがパスワードをリセットできる URL。 |
| `in_corp` | ブーリアン | クライアントが企業ネットワークからサインインしている場合に通知します。 |
| `nickname` | 糸 | ユーザーの別の名前。姓または名とは別の名前です。 |
| `family_name` | 糸 | ユーザー オブジェクトで定義されたユーザーの姓を示します。 |
| `given_name` | 糸 | ユーザー オブジェクトに設定されたユーザーの名を示します。 |
| `upn` | 糸 | ユーザーのユーザー名。 電話番号、電子メール アドレス、または書式なし文字列を指定できます。 表示目的でのみ使用し、再認証のシナリオでユーザー名のヒントを提供します。 |

#### amr 要求

ID は、アプリケーションに関連している可能性のあるさまざまな方法で認証できます。 `amr` 要求は、パスワードと Authenticator アプリの両方を使用した認証用に、複数の項目を格納できる配列 (`["mfa", "rsa", "pwd"]` など) です。

| 値 | 説明 |
| --- | --- |
| `pwd` | パスワード認証。ユーザーの Microsoft パスワードまたはアプリのクライアント シークレット。 |
| `rsa` | [Microsoft Authenticator アプリ](https://aka.ms/AA2kvvu)を使用した認証など、認証が RSA キーの証明に基づいていたことを示します。 この値は、認証にサービス所有の X509 証明書を使用した自己署名 JWT の使用も示します。 |
| `otp` | 電子メールまたはテキスト メッセージを使用したワンタイム パスコード。 |
| `fed` | フェデレーション認証アサーション (JWT や SAML など) の使用を示します。 |
| `wia` | Windows 統合認証 |
| `mfa` | [多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)の使用を示します。 この要求が存在する場合は、他の認証方法が含まれます。 |
| `ngcmfa` | `mfa` と同等です。特定の高度な資格情報の種類のプロビジョニングに使用されます。 |
| `wiaormfa` | ユーザーが Windows 資格情報または MFA 資格情報を使用して認証されたことを示します。 |
| `none` | 完了した認証がないことを示します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/access-tokens"} -->
## Microsoft ID プラットフォームのアクセス トークン - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens
- Service: identity-platform
- Article date: 2025-05-14
- Summary: Microsoft ID プラットフォームで使用されるアクセス トークンについて説明します。

アクセス トークンは、認証されたユーザーに代わって特定のリソースへのアクセスを許可する、承認用に設計されたセキュリティ トークンの一種です。 アクセス トークン内の情報は、ビル内の特定のドアのロックを解除するキーと同様に、ユーザーが特定のリソースにアクセスする権限を持っているかどうかを決定します。 トークンを構成するこれらの個々の情報は、クレームと呼ばれます。 そのため、これらは機密性の高い資格情報であり、正しく処理されない場合はセキュリティ リスクを引き起こす可能性があります。 アクセス トークンは、認証の証明として機能する [ID トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)とは異なります。

アクセス トークンにより、クライアントは保護された Web API を安全に呼び出すことができます。 クライアント アプリケーションはアクセス トークンを受け取って使用できますが、それらを不透明な文字列として扱う必要があります。 クライアント アプリケーションは、アクセス トークンの検証を試みることはできません。 リソース サーバーは、アクセス トークンを認可の証明として受け入れる前に検証する必要があります。 トークンの内容は API のみを対象としているため、アクセス トークンは不透明な文字列として扱う必要があります。 検証とデバッグ "*のみ*" を目的として、開発者は https://jwt.ms などのサイトを使用して JWT をデコードできます。 Microsoft API が受信するトークンは、常にデコードできる JWT であるとは限りません。

クライアントは、アクセス トークンと共に返されるトークン応答データを使用して、その内容の詳細を確認する必要があります。 クライアントがアクセス トークンを要求すると、Microsoft ID プラットフォームからは、アプリケーションで使用されるそのアクセス トークンに関する何らかのメタデータも返されます。 この情報には、アクセス トークンの有効期限や、それが有効なスコープが含まれます。 このデータを使用すると、アプリケーションはアクセス トークン自体を解析しなくても、そのアクセス トークンのインテリジェントなキャッシュを実行できます。 この記事では、形式、所有権、有効期間、API がアクセス トークン内のクレームを検証して使用する方法など、アクセス トークンに関する重要な情報について説明します。

注

このページのすべてのドキュメントは、特に記載がある場合を除き、登録された API に対して発行されるトークンにのみ適用されます。 Microsoft が所有する API に対して発行されるトークンには適用されません。また、それらのトークンを使用して、Microsoft ID プラットフォームが、登録された API に対してトークンを発行する方法を検証することもできません。

### トークンの形式

Microsoft ID プラットフォームで使用できるアクセス トークンには、v1.0 と v2.0 の 2 つのバージョンがあります。 これらのバージョンでは、トークン内の要求を決定し、Web API がトークンの内容を制御できるようにします。

Web API では、登録時に次のいずれかのバージョンが既定として選択されます。

- Microsoft Entra 専用アプリケーションの場合は v1.0。 次の例は v1.0 トークンを示しています (キーは変更され、個人情報は削除されており、トークンの検証はできません)。

    ```text
    eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Imk2bEdrM0ZaenhSY1ViMkMzbkVRN3N5SEpsWSIsImtpZCI6Imk2bEdrM0ZaenhSY1ViMkMzbkVRN3N5SEpsWSJ9.eyJhdWQiOiJlZjFkYTlkNC1mZjc3LTRjM2UtYTAwNS04NDBjM2Y4MzA3NDUiLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC9mYTE1ZDY5Mi1lOWM3LTQ0NjAtYTc0My0yOWYyOTUyMjIyOS8iLCJpYXQiOjE1MzcyMzMxMDYsIm5iZiI6MTUzNzIzMzEwNiwiZXhwIjoxNTM3MjM3MDA2LCJhY3IiOiIxIiwiYWlvIjoiQVhRQWkvOElBQUFBRm0rRS9RVEcrZ0ZuVnhMaldkdzhLKzYxQUdyU091TU1GNmViYU1qN1hPM0libUQzZkdtck95RCtOdlp5R24yVmFUL2tES1h3NE1JaHJnR1ZxNkJuOHdMWG9UMUxrSVorRnpRVmtKUFBMUU9WNEtjWHFTbENWUERTL0RpQ0RnRTIyMlRJbU12V05hRU1hVU9Uc0lHdlRRPT0iLCJhbXIiOlsid2lhIl0sImFwcGlkIjoiNzVkYmU3N2YtMTBhMy00ZTU5LTg1ZmQtOGMxMjc1NDRmMTdjIiwiYXBwaWRhY3IiOiIwIiwiZW1haWwiOiJBYmVMaUBtaWNyb3NvZnQuY29tIiwiZmFtaWx5X25hbWUiOiJMaW5jb2xuIiwiZ2l2ZW5fbmFtZSI6IkFiZSAoTVNGVCkiLCJpZHAiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC83MmY5ODhiZi04NmYxLTQxYWYtOTFhYi0yZDdjZDAxMjIyNDcvIiwiaXBhZGRyIjoiMjIyLjIyMi4yMjIuMjIiLCJuYW1lIjoiYWJlbGkiLCJvaWQiOiIwMjIyM2I2Yi1hYTFkLTQyZDQtOWVjMC0xYjJiYjkxOTQ0MzgiLCJyaCI6IkkiLCJzY3AiOiJ1c2VyX2ltcGVyc29uYXRpb24iLCJzdWIiOiJsM19yb0lTUVUyMjJiVUxTOXlpMmswWHBxcE9pTXo1SDNaQUNvMUdlWEEiLCJ0aWQiOiJmYTE1ZDY5Mi1lOWM3LTQ0NjAtYTc0My0yOWYyOTU2ZmQ0MjkiLCJ1bmlxdWVfbmFtZSI6ImFiZWxpQG1pY3Jvc29mdC5jb20iLCJ1dGkiOiJGVnNHeFlYSTMwLVR1aWt1dVVvRkFBIiwidmVyIjoiMS4wIn0.D3H6pMUtQnoJAGq6AHd
    ```
- コンシューマー アカウントをサポートするアプリケーションの場合、v2.0。 次の例は、v2.0 トークンを示しています (キーは変更され、個人情報は削除されており、トークンの検証はできません)。

    ```text
    eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Imk2bEdrM0ZaenhSY1ViMkMzbkVRN3N5SEpsWSJ9.eyJhdWQiOiI2ZTc0MTcyYi1iZTU2LTQ4NDMtOWZmNC1lNjZhMzliYjEyZTMiLCJpc3MiOiJodHRwczovL2xvZ2luLm1pY3Jvc29mdG9ubGluZS5jb20vNzJmOTg4YmYtODZmMS00MWFmLTkxYWItMmQ3Y2QwMTFkYjQ3L3YyLjAiLCJpYXQiOjE1MzcyMzEwNDgsIm5iZiI6MTUzNzIzMTA0OCwiZXhwIjoxNTM3MjM0OTQ4LCJhaW8iOiJBWFFBaS84SUFBQUF0QWFaTG8zQ2hNaWY2S09udHRSQjdlQnE0L0RjY1F6amNKR3hQWXkvQzNqRGFOR3hYZDZ3TklJVkdSZ2hOUm53SjFsT2NBbk5aY2p2a295ckZ4Q3R0djMzMTQwUmlvT0ZKNGJDQ0dWdW9DYWcxdU9UVDIyMjIyZ0h3TFBZUS91Zjc5UVgrMEtJaWpkcm1wNjlSY3R6bVE9PSIsImF6cCI6IjZlNzQxNzJiLWJlNTYtNDg0My05ZmY0LWU2NmEzOWJiMTJlMyIsImF6cGFjciI6IjAiLCJuYW1lIjoiQWJlIExpbmNvbG4iLCJvaWQiOiI2OTAyMjJiZS1mZjFhLTRkNTYtYWJkMS03ZTRmN2QzOGU0NzQiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJhYmVsaUBtaWNyb3NvZnQuY29tIiwicmgiOiJJIiwic2NwIjoiYWNjZXNzX2FzX3VzZXIiLCJzdWIiOiJIS1pwZmFIeVdhZGVPb3VZbGl0anJJLUtmZlRtMjIyWDVyclYzeERxZktRIiwidGlkIjoiNzJmOTg4YmYtODZmMS00MWFmLTkxYWItMmQ3Y2QwMTFkYjQ3IiwidXRpIjoiZnFpQnFYTFBqMGVRYTgyUy1JWUZBQSIsInZlciI6IjIuMCJ9.pj4N-w_3Us9DrBLfpCt
    ```

`requestedAccessTokenVersion`の  設定に適切な値を指定して、アプリケーションのバージョンを設定します。 `null` および `1` の値では v1.0 トークンになり、`2` の値では v2.0 トークンになります。

### トークンの所有権

アクセス トークン要求には 2 つのパーティが関与します。トークンを要求するクライアントと、トークンを受け入れるリソース (Web API) です。 トークンの対象となるリソース (その*対象ユーザー*) は、トークンの `aud` 要求で定義されます。 クライアントはトークンを使用しますが、それを理解したり解析しようとしたりすることはできません。 リソースはトークンを受け入れます。

Microsoft ID プラットフォームでは、任意のバージョンのエンドポイントからの任意のトークン バージョンの発行がサポートされています。 たとえば、`requestedAccessTokenVersion` の値が `2` の場合、v1.0 エンドポイントを呼び出してそのリソースのトークンを取得するクライアントは、v2.0 アクセス トークンを受け取ります。

リソースは、`aud` 要求を使用するトークンを常に所有し、トークンの詳細を変更できる唯一のアプリケーションになります。

### トークンの有効期間

アクセス トークンの既定の有効期間は、可変です。 発行されると、Microsoft ID プラットフォームはアクセス トークンの既定の有効期間として、60 分から 90 分 (平均で 75 分) の範囲でランダムな値を割り当てます。 このバリエーションによって、期間内にアクセス トークンの要求が分散してサービスの回復性が向上します。これで、Microsoft Entra ID へのトラフィックが 1 時間ごとに急増することを防止します。

条件付きアクセスを使用しないテナントでは、Microsoft Teams や Microsoft 365 などのクライアントに対して、既定のアクセス トークンの有効期間は 2 時間です。

アクセス トークンの有効期間を調整して、クライアント アプリケーションがアプリケーション セッションを期限切れにする頻度と、ユーザーに再認証 (サイレントまたは対話形式) を要求する頻度を制御できます。 既定のアクセス トークンの有効期間のバリエーションをオーバーライドするには、[構成可能なトークンの有効期間 (CTL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes) を使用します。

既定のトークンの有効期間のバリエーションを、継続的アクセス評価 (CAE) が有効な組織に適用します。 組織が CTL ポリシーを使用している場合でも、既定のトークンの有効期間のバリエーションを適用します。 有効期間が長いトークンの既定のトークンの有効期間の範囲は、20 時間から 28 時間です。 アクセス トークンの有効期限が切れると、クライアントは更新トークンを使用して新しい更新トークンとアクセス トークンを取得する必要があります。

[条件付きアクセスのサインイン頻度 (SIF)](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-session-lifetime#user-sign-in-frequency) を使用してサインインの頻度を適用する組織は、既定のアクセス トークンの有効期間のバリエーションをオーバーライドすることはできません。 組織が SIF を使用する場合、クライアントの資格情報入力の間隔は、サインイン頻度の間隔から、サインイン頻度に加えて 60 ～ 90 分範囲であるトークンの存続期間までを含む範囲です。

既定のトークンの有効期間のバリエーションがサインイン頻度とどのように連動するかの例を次に示します。 たとえば、組織で 1 時間ごとにサインイン頻度が発生するように設定します。 トークンの有効期間のバリエーションにより、トークンの有効期間の範囲が 60 分から 90 分の場合、実際のサインイン間隔は 1 時間から 2.5 時間の間で発生します。

有効期間が 1 時間のトークンを持つユーザーが、59 分の時点で対話型サインインを実行する場合、そのサインインは SIF しきい値を下回るため、資格情報のプロンプトは表示されません。 新しいトークンの有効期間が 90 分の場合、次の 1 時間半の間、ユーザーに資格情報プロンプトは表示されません。 サイレント更新を実行しようとすると、セッションの長さの合計が サインイン頻度設定の 1 時間を超えたため、Microsoft Entra ID は資格情報プロンプトを要求します。 この例では、SIF 間隔とトークンの有効期間のバリエーションが原因で、資格情報プロンプトの時間差は 2.5 時間になります。

### トークンを検証する

すべてのアプリケーションでトークンを検証する必要はありません。 アプリケーションでトークンを検証する必要があるのは、特定のシナリオのみです。

- Web API は、クライアントから自身に送信されたアクセス トークンを検証する必要があります。 AppId URI の 1 つが `aud` クレームとして含まれるトークンのみを受け入れる必要があります。
- Web アプリは、ユーザーのデータへのアクセスを許可する、またはセッションを確立する前に、ハイブリッド フローでユーザーのブラウザーを使用して自身に送信された ID トークンを検証する必要があります。

前述のシナリオが該当しない場合は、トークンを検証する必要はありません。 ネイティブ、デスクトップ、シングルページ アプリケーションなどのパブリック クライアントは、アプリケーションが ID トークンを検証する利点はありません。これは、アプリケーションが IDP と直接通信し、SSL 保護によって ID トークンが有効であることを確認するためです。 アクセス トークンの検証は Web API での検証を目的としているため、クライアントでアクセス トークンを検証する必要はありません。

API と Web アプリケーションは、アプリケーションに一致する `aud` 要求を含むトークンのみを検証する必要があります。 その他のリソースには、カスタム トークン検証規則がある場合があります。 たとえば、Microsoft Graph のトークンは独自の形式であるため、これらの規則に従って検証することはできません。 別のリソースを対象とするトークンを検証して受け入れることは、[混乱した使節 (Confused Deputy)](https://cwe.mitre.org/data/definitions/441.html) の問題にたとえることができます。

アプリケーションで ID トークンまたはアクセス トークンを検証する必要がある場合は、最初に OpenID 探索ドキュメントの値と突き合わせてトークンの署名と発行者を検証する必要があります。

Microsoft Entra ミドルウェアには、アクセス トークンを検証するための組み込み機能があります。 適切な言語のサンプルを見つけるには、[samples](https://learn.microsoft.com/ja-jp/entra/identity-platform/sample-v2-code) を参照してください。 JWT の検証に使用できるサードパーティのオープン ソース ライブラリもいくつか存在します。 認証ライブラリとコード サンプルの詳細については、[認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)に関する記事を参照してください。 Web アプリまたは Web API が ASP.NET または ASP.NET Core で実行されている場合は、検証に対処する Microsoft.Identity.Web を使います。

#### v1.0 トークンと v2.0 トークン

- Web アプリ/API が v1.0 トークン (`ver` クレーム = "1.0") を検証している場合、Web API 用に構成された機関が v2.0 機関だとしても、v1.0 エンドポイント (`https://login.microsoftonline.com/{example-tenant-id}/.well-known/openid-configuration`) から OpenID Connect メタデータ ドキュメントを読み取る必要があります。
- Web アプリ/API が v2.0 トークン (`ver` クレーム = "2.0") を検証している場合、Web API 用に構成された機関が v1.0 機関だとしても、v2.0 エンドポイント (`https://login.microsoftonline.com/{example-tenant-id}/v2.0/.well-known/openid-configuration`) から OpenID Connect メタデータ ドキュメントを読み取る必要があります。

次の例では、アプリケーションが v2.0 アクセス トークンを検証していることを前提としています (したがって、v2.0 バージョンの OIDC メタデータ ドキュメントとキーを参照します)。 v1.0 トークンを検証する場合は、URL の "/v2.0" を削除してください。

#### 発行者を検証する

[OpenID Connect Core](https://openid.net/specs/openid-connect-core-1_0.html#IDTokenValidation) には "発行者識別子 [...] は iss (発行者) クレームの値と完全に一致する必要があります" と表示されます。テナント固有のメタデータ エンドポイント (`https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/v2.0/.well-known/openid-configuration` や `https://login.microsoftonline.com/contoso.onmicrosoft.com/v2.0/.well-known/openid-configuration` など) を使うアプリケーションの場合は、必要なものはこれだけです。

Microsoft Entra ID には、https://login.microsoftonline.com/common/v2.0/.well-known/openid-configuration で利用できるドキュメントのテナントに依存しないバージョンが与えられます。 このエンドポイントは、発行者の値 `https://login.microsoftonline.com/{tenantid}/v2.0` を返します。 アプリケーションでは、このテナントに依存しないエンドポイントを使って、次の変更を加えてすべてのテナントからのトークンを検証できます。

1. トークンの発行者要求がメタデータの発行者の値と正確に一致することを想定するのではなく、アプリケーションは発行者メタデータの `{tenantid}` 値を現在の要求のターゲットであるテナント ID に置き換え、完全一致を確認する必要があります。
2. アプリケーションでは、キーのスコープを制限するために、キー エンドポイントから返される `issuer` プロパティを使う必要があります。

    - `https://login.microsoftonline.com/{tenantid}/v2.0` のような発行者の値を持つキーは、一致するトークン発行者と共に使用できます。
    - `https://login.microsoftonline.com/9188040d-6c67-4c5b-b112-36a304b66dad/v2.0` のような発行者の値を持つキーは、完全一致でのみ使う必要があります。

    Microsoft Entra テナントに依存しないキー エンドポイント (https://login.microsoftonline.com/common/discovery/v2.0/keys) は、次のようなドキュメントを返します。

    ```
    {
      "keys":[
        {"kty":"RSA","use":"sig","kid":"A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u","x5t":"A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u","n":"spv...","e":"AQAB","x5c":["MIID..."],"issuer":"https://login.microsoftonline.com/{tenantid}/v2.0"},
        {"kty":"RSA","use":"sig","kid":"C2dE3fH4iJ5kL6mN7oP8qR9sT0uV1w","x5t":"C2dE3fH4iJ5kL6mN7oP8qR9sT0uV1w","n":"wEM...","e":"AQAB","x5c":["MIID..."],"issuer":"https://login.microsoftonline.com/{tenantid}/v2.0"},
        {"kty":"RSA","use":"sig","kid":"E3fH4iJ5kL6mN7oP8qR9sT0uV1wX2y","x5t":"E3fH4iJ5kL6mN7oP8qR9sT0uV1wX2y","n":"rv0...","e":"AQAB","x5c":["MIID..."],"issuer":"https://login.microsoftonline.com/9188040d-6c67-4c5b-b112-36a304b66dad/v2.0"}
      ]
    }
    ```
3. 標準の発行者要求ではなく、信頼境界として Microsoft Entra テナント ID (`tid`) 要求を使用するアプリケーションでは、テナント ID 要求が guid であり、発行者とテナント ID が一致していることを確認する必要があります。

多くのテナントからのトークンを受け入れるアプリケーションの場合、テナントに依存しないメタデータを使う方が効率的です。

注

Microsoft Entra テナントに依存しないメタデータでは、要求はテナント内で解釈される必要があります。標準の OpenID Connect の場合、要求は発行者内で解釈されます。 つまり、`{"sub":"ABC123","iss":"https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/v2.0","tid":"aaaabbbb-0000-cccc-1111-dddd2222eeee"}` と `{"sub":"ABC123","iss":"https://login.microsoftonline.com/bbbbcccc-1111-dddd-2222-eeee3333ffff/v2.0","tid":"bbbbcccc-1111-dddd-2222-eeee3333ffff"}` は、`sub` は同じですが、`sub` のようなクレームは発行者/テナントのコンテキスト内で解釈されるため、異なるユーザーを記述しています。

#### 署名を検証

JWT には 3 つのセグメントがあり、`.` 文字で区切られています。 1 番目のセグメントは**ヘッダー**、2 番目は**本文**、3 番目は**署名**です。 署名セグメントを使用して、トークンの信頼性を評価します。

Microsoft Entra ID によって発行されるトークンは、RS256 など、業界標準の非対称暗号化アルゴリズムを使用して署名されます。 JWT のヘッダーには、トークンの署名に使用されたキーと暗号方法に関する情報が含まれます。

```json
{
  "typ": "JWT",
  "alg": "RS256",
  "x5t": "H4iJ5kL6mN7oP8qR9sT0uV1wX2yZ3a",
  "kid": "H4iJ5kL6mN7oP8qR9sT0uV1wX2yZ3a"
}
```

`alg` 要求はトークンの署名に使用されたアルゴリズムを示し、`kid` 要求はトークンの検証に使用された特定の公開キーを示します。

いつでも、Microsoft Entra ID は公開/秘密キー ペアの特定セットのいずれかを使用して、ID トークンに署名できます。 Microsoft Entra ID は定期的に使用可能なキー セットをローテーションするため、このキー変更を自動的に処理するようにアプリケーションを作成します。 Microsoft Entra ID によって使用される公開キーの更新を確認する適切な頻度は、24 時間間隔です。

署名の検証に必要な署名キー データは、次の場所にある [OpenID Connect メタデータのドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc#fetch-the-openid-configuration-document)を使用して入手します。

```
https://login.microsoftonline.com/common/v2.0/.well-known/openid-configuration
```

ヒント

ブラウザーで [URL](https://login.microsoftonline.com/common/v2.0/.well-known/openid-configuration) を試します

次の情報では、メタデータ ドキュメントについて説明します。

- OpenID Connect 認証を実行するために必要なさまざまなエンドポイントの場所などの、いくつかの有効な情報を含む JSON オブジェクトです。
- `jwks_uri` が含まれています。これは、トークンの署名に使用される秘密キーに対応する公開キーのセットの場所を示すものです。 `jwks_uri` にある JSON Web キー (JWK) には、特定の時点で使用されているすべての公開キー情報が含まれます。 「[RFC 7517](https://tools.ietf.org/html/rfc7517)」では JWK 形式が記述されています。 アプリケーションには、JWT ヘッダーの `kid` 要求を使用し、このドキュメントから、特定のトークンの署名に使用された秘密キーに対応する公開キーを選択できます。 その後、正しい公開キーと指定されたアルゴリズムを使用して、署名の検証を実行できます。

注

`kid` 要求を使用してトークンを検証します。 v1.0 トークンには `x5t` と `kid` の両方の要求が含まれますが、v2.0 トークンには `kid` 要求のみ含まれます。

署名の検証を実行する方法については、このドキュメントでは説明していません。 必要に応じて、署名の検証に役立つオープン ソース ライブラリが多数存在します。 ただし、Microsoft ID プラットフォームには、標準に対する 1 つのトークン署名拡張であるカスタム署名キーがあります。

[要求のマッピング](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization) 機能を使用した結果としてアプリケーションにカスタム署名キーがある場合は、アプリケーション ID を含む `appid` クエリ パラメーターを追加します。 検証には、アプリケーションの署名キー情報を指す `jwks_uri` を使用します。 例: `https://login.microsoftonline.com/{tenant}/.well-known/openid-configuration?appid=00001111-aaaa-2222-bbbb-3333cccc4444` には、`jwks_uri` の `https://login.microsoftonline.com/{tenant}/discovery/keys?appid=00001111-aaaa-2222-bbbb-3333cccc4444` が含まれます。

#### 発行者を検証する

ID トークンを検証する Web アプリ、およびアクセス トークンを検証する Web API は、トークンの発行者 (`iss` クレーム) を次に対して検証する必要があります。

1. アプリケーション構成 (機関) に関連付けられている OpenID connect メタデータ ドキュメントから取得できる発行者。 検証対象のメタデータ ドキュメントは、次の項目に応じて異なります。
    - トークンのバージョン
    - アプリケーションでサポートされているアカウント。
2. トークンのテナント ID (`tid` クレーム)、
3. 署名キーの発行者。

##### シングルテナント アプリケーション

[OpenID Connect Core](https://openid.net/specs/openid-connect-core-1_0.html#IDTokenValidation) には "発行者識別子 [...] は `iss` (発行者) クレームの値と完全に一致する必要があります" と表示されます。`https://login.microsoftonline.com/{example-tenant-id}/v2.0/.well-known/openid-configuration` や `https://login.microsoftonline.com/contoso.onmicrosoft.com/v2.0/.well-known/openid-configuration` など、テナント固有のメタデータ エンドポイントを使うアプリケーションの場合。

シングル テナント アプリケーションは、次をサポートするアプリケーションです。

- 1 つの組織ディレクトリ内のアカウント (**example-tenant-id** のみ): `https://login.microsoftonline.com/{example-tenant-id}`
- 個人用 Microsoft アカウントのみ: `https://login.microsoftonline.com/consumers` (**consumers** はテナント 9188040d-6c67-4c5b-b112-36a304b66dad のニックネーム)

##### マルチテナント アプリケーション

Microsoft Entra ID では、マルチテナント アプリケーションもサポートされています。 これらのアプリケーションでは、次がサポートされます。

- 任意の組織ディレクトリ内のアカウント (任意の Microsoft Entra ディレクトリ): `https://login.microsoftonline.com/organizations`
- 任意の組織ディレクトリ (任意の Microsoft Entra ディレクトリ) 内のアカウント、および個人用 Microsoft アカウント (Skype、Xbox など): `https://login.microsoftonline.com/common`

これらのアプリケーションの場合、Microsoft Entra ID では、`https://login.microsoftonline.com/common/v2.0/.well-known/openid-configuration` と `https://login.microsoftonline.com/organizations/v2.0/.well-known/openid-configuration` のそれぞれで、テナントに依存しない OIDC ドキュメントのバージョンが公開されます。 これらのエンドポイントは発行者の値を返します。これは、`tenantid`: `https://login.microsoftonline.com/{tenantid}/v2.0` でパラメーター化されたテンプレートです。 アプリケーションでは、このテナントに依存しないエンドポイントを使用して、以下の要件を満たすことですべてのテナントからのトークンを検証できます。

- 署名キーの発行者を検証する
- トークン内の発行者クレームがメタデータの発行者の値と正確に一致することを想定するのではなく、アプリケーションは発行者メタデータの `{tenantid}` 値を現在の要求のターゲットであるテナント ID に置き換えて、完全一致 (トークンの `tid` クレーム) をチェックする必要があります。
- `tid` クレームが GUID であり、`iss` クレームが `https://login.microsoftonline.com/{tid}/v2.0` の形式であることを検証します。ここで、`{tid}` は `tid` クレームと完全に同一です。 この検証により、テナントが発行者に関連付けられ、さらに署名キーのスコープに関連付けられることで、信頼チェーンが作成されます。
- クレームのサブジェクトに関連付けられているデータを特定する際に、必ず `tid` クレームを使用します。 つまり、`tid` クレームは、ユーザー データへのアクセスに使用されるキーの一部である必要があります。

#### 署名キーの発行者を検証する

v2.0 テナントに依存しないメタデータを使用するアプリケーションでは、署名キーの発行者を検証する必要があります。

##### 鍵ドキュメントおよび署名キー発行者

前述のように、OpenID Connect ドキュメントから、アプリケーションはトークンの署名に使用されるキーにアクセスします。 OpenIdConnect ドキュメントの **jwks\_uri** プロパティで公開されている URL にアクセスすることで、対応するキー ドキュメントを取得します。

```json
 "jwks_uri": "https://login.microsoftonline.com/{example-tenant-id}/discovery/v2.0/keys",
```

`{example-tenant-id}` の値は、GUID、ドメイン名、または **common**、\*\*organizations、**consumers** に置き換えることができます。

Azure AD v2.0 によって公開される `keys` ドキュメントには、この署名キーを使用する発行者がキーごとに含まれています。 たとえば、テナントに依存しない "common" キー エンドポイント `https://login.microsoftonline.com/common/discovery/v2.0/keys` は次のようなドキュメントを返します。

```json
{
  "keys":[
    {"kty":"RSA","use":"sig","kid":"A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u","x5t":"A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u","n":"spv...","e":"AQAB","x5c":["MIID..."],"issuer":"https://login.microsoftonline.com/{tenantid}/v2.0"},
    {"kty":"RSA","use":"sig","kid":"C2dE3fH4iJ5kL6mN7oP8qR9sT0uV1w","x5t":"C2dE3fH4iJ5kL6mN7oP8qR9sT0uV1w","n":"wEM...","e":"AQAB","x5c":["MIID..."],"issuer":"https://login.microsoftonline.com/{tenantid}/v2.0"},
    {"kty":"RSA","use":"sig","kid":"E3fH4iJ5kL6mN7oP8qR9sT0uV1wX2y","x5t":"E3fH4iJ5kL6mN7oP8qR9sT0uV1wX2y","n":"rv0...","e":"AQAB","x5c":["MIID..."],"issuer":"https://login.microsoftonline.com/9188040d-6c67-4c5b-b112-36a304b66dad/v2.0"}
  ]
}
```

##### 署名キー発行者の検証

アプリケーションでは、キーのスコープを制限するために、トークンの署名に使用されるキーに関連付けられているキー ドキュメントの `issuer` プロパティを使用する必要があります。

- `https://login.microsoftonline.com/9188040d-6c67-4c5b-b112-36a304b66dad/v2.0` のような GUID を含む発行者の値を持つキーは、トークン内の `iss` クレームと値が完全一致する場合にのみ使用する必要があります。
- `https://login.microsoftonline.com/{tenantid}/v2.0` のようなテンプレート化された発行者値を持つキーは、`iss` プレースホルダーのトークン内の `tid` クレームを置き換えた後、トークン内の `{tenantid}` クレームがこの値と一致する場合にのみ使用してください。

多くのテナントからトークンを受け入れるアプリケーションの場合、テナントに依存しないメタデータを使用する方がより効率的です。

注

Microsoft Entra テナントに依存しないメタデータでは、要求はテナント内で解釈される必要があります。標準の OpenID Connect の場合、要求は発行者内で解釈されます。 つまり、`{"sub":"ABC123","iss":"https://login.microsoftonline.com/{example-tenant-id}/v2.0","tid":"{example-tenant-id}"}` と `{"sub":"ABC123","iss":"https://login.microsoftonline.com/{another-tenand-id}/v2.0","tid":"{another-tenant-id}"}` は、`sub` は同じですが、`sub` のようなクレームは発行者/テナントのコンテキスト内で解釈されるため、異なるユーザーを記述しています。

##### まとめ

発行者と署名キー発行者の検証方法を要約した擬似コードを次に示します。

1. 構成されたメタデータ URL からキーを取り込みます
2. 公開されたキーのいずれかで署名されている場合はトークンをチェックし、そうでない場合は処理を失敗させます
3. kid ヘッダーに基づいて、メタデータ内のキーを特定します。 メタデータ ドキュメントのキーにアタッチされている "issuer" プロパティをチェックします。

    ```c
    var issuer = metadata["kid"].issuer;
    if (issuer.contains("{tenantId}", CaseInvariant)) issuer = issuer.Replace("{tenantid}", token["tid"], CaseInvariant);
    if (issuer != token["iss"]) throw validationException;
    if (configuration.allowedIssuer != "*" && configuration.allowedIssuer != issuer) throw validationException;
    var issUri = new Uri(token["iss"]);
    if (issUri.Segments.Count < 1) throw validationException;
    if (issUri.Segments[1] != token["tid"]) throw validationException;
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/accounts-overview"} -->
## Android での Microsoft ID プラットフォームのアカウントとテナント プロファイル - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/accounts-overview
- Service: identity-platform
- Article date: 2025-05-14
- Summary: Android 用の Microsoft ID プラットフォーム アカウントの概要

この記事では、Microsoft ID プラットフォームにおける `account` の概要を示します。

Microsoft Authentication Library (MSAL) API では、*ユーザー* という用語が*アカウント* という用語に置き換えられています。 1 つの理由は、ユーザー (人間またはソフトウェア エージェント) が複数のアカウントを持っている場合がある、あるいは使用できるためです。 これらのアカウントは、ユーザー自身の組織、またはユーザーがメンバーになっている他の組織に存在する場合があります。

Microsoft ID プラットフォームのアカウントは、次のもので構成されます。

- 一意の識別子。
- アカウントの所有権/制御を示すために使用される 1 つまたは複数の資格情報。
- 次のような属性で構成される 1 つまたは複数のプロファイル:
    - 画像、名、姓、肩書、オフィス所在地
- アカウントには、権限のソースまたはレコードのシステムがあります。 これは、アカウントが作成され、そのアカウントに関連付けられている資格情報が格納される場所です。 Microsoft ID プラットフォームのようなマルチテナント システムでは、レコードのシステムは、アカウントが作成された `tenant` です。 このテナントは `home tenant` とも呼ばれます。
- Microsoft ID プラットフォームのアカウントには、次のレコードのシステムがあります。
    - Microsoft Entra ID (Azure Active Directory B2C を含む)。
    - Microsoft アカウント (Live)。
- Microsoft ID プラットフォームの外部にあるレコードのシステムからのアカウントは、次のような Microsoft ID プラットフォーム内で表されます。
    - 接続されたオンプレミス ディレクトリ (Windows Server Active Directory) からの ID
    - LinkedIn や GitHub などからの外部 ID。 このような場合、アカウントには、元のレコードのシステムと、Microsoft ID プラットフォーム内のレコードのシステムの両方があります。
- Microsoft ID プラットフォームでは、1 つのアカウントを使用して、複数の組織 (Microsoft Entra テナント) に属しているリソースにアクセスできます。
    - あるレコードのシステム (Microsoft Entra テナント A) からのアカウントで別のレコードのシステム (Microsoft Entra テナント B) のリソースにアクセスできることを記録するには、リソースが定義されているテナントでアカウントを表す必要があります。 これを行うには、システム B でシステム A からのアカウントのローカル レコードを作成します。
    - アカウントの表現である、このローカル レコードは、元のアカウントにバインドされます。
    - MSAL では、このローカル レコードを `Tenant Profile` として公開します。
    - テナント プロファイルには、役職、勤務先所在地、連絡先情報など、ローカル コンテキストに適したさまざまな属性を含めることができます。
- 1 つまたは複数のテナントにアカウントが存在する場合があるため、アカウントに複数のプロファイルが含まれる可能性があります。

注意

MSAL では、Microsoft アカウント システム (Live、MSA) を、Microsoft ID プラットフォーム内の別のテナントとして扱います。 Microsoft アカウント テナントのテナント ID は、`9188040d-6c67-4c5b-b112-36a304b66dad` です

### アカウントの概要図

[Image: アカウントの概要図]

上の図では:

- アカウント `bob@contoso.com` は、オンプレミスの Windows Server Active Directory (元のレコードのオンプレミス システム) に作成されます。
- アカウント `tom@live.com` は Microsoft アカウント テナントに作成されます。
- `bob@contoso.com`では、次の Microsoft Entra テナントの少なくとも 1 つのリソースにアクセスできます。
    - contoso.com (レコードのクラウド システム - レコードのオンプレミス システムにリンクされている)
    - fabrikam.com
    - woodgrovebank.com
    - `bob@contoso.com` 用のテナント プロファイルは、これらの各テナントに存在します。
- `tom@live.com`では、次の Microsoft テナントのリソースにアクセスできます。
    - contoso.com
    - fabrikam.com
    - `tom@live.com` 用のテナント プロファイルは、これらの各テナントに存在します。
- 他のテナントの Tom と Bob に関する情報は、レコードのシステムのものとは異なる場合があります。 役職や勤務先所在地などの属性が異なる場合があります。 各組織 (Microsoft Entra テナント) 内のグループやロールのメンバーである場合があります。 この情報を bob@contoso.com テナント プロファイルと呼びます。

図では、bob@contoso.com および tom@live.com で、異なる Microsoft Entra テナント内のリソースにアクセスすることができます。 詳細については、[Azure portal での Microsoft Entra B2B コラボレーション ユーザーの追加](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)に関するページを参照してください。

### アカウントとシングル サインオン (SSO)

MSAL トークン キャッシュには、アカウントごとに*単一の更新トークン* が格納されます。 この更新トークンを使用して、複数の Microsoft ID プラットフォーム テナントからのアクセス トークンをサイレントに要求できます。 ブローカーがデバイス上にインストールされている場合、アカウントはブローカーによって管理され、デバイス全体のシングル サインオンが可能になります。

重要

企業-消費者間 (B2C) アカウントと更新トークンの動作は、Microsoft の他の ID プラットフォームとは異なります。 詳細については、「B2C ポリシーとアカウント」を参照してください。

### アカウント ID

MSAL のアカウント ID は、アカウント オブジェクト ID ではありません。 これは、Microsoft ID プラットフォーム内の一意性以外のものを伝えるために解析したり、依存したりすることを目的としたものではありません。

MSAL は、MSAL キャッシュで使用可能なアカウントの有効な識別子を使用してアカウントを検索できます。 たとえば、以下の場合は、各 ID が有効であるため、tom@live.com に対して常に同じアカウント オブジェクトが取得されます。

```java
// The following would always retrieve the same account object for tom@live.com because each identifier is valid

IAccount account = app.getAccount("<tome@live.com msal account id>");
IAccount account = app.getAccount("<tom@live.com contoso user object id>");
IAccount account = app.getAccount("<tom@live.com woodgrovebank user object id>");
```

### アカウントに関するクレームへのアクセス

また、MSAL では、アクセス トークンを要求するだけでなく、常に各テナントから ID トークンを要求します。 これは、常に次のスコープを要求することによって行われます。

- openid（オープンID認証プロトコル）
- プロフィール

ID トークンには要求のリストが含まれます。 `Claims` は、アカウントに関する名前と値のペアであり、要求を行うために使用されます。

前述のように、アカウントが存在する各テナントには、アカウントに関するさまざまな情報が格納される場合があり、役職や勤務先所在地などの属性が含まれますが、これらに限定されるものではありません。

アカウントは複数の組織のメンバーまたはゲストである場合がありますが、MSAL では、アカウントがメンバーとなっているテナントのリストを取得するためにサービスを照会することはありません。 代わりに、MSAL では、行われたトークン要求の結果として、アカウントが存在するテナントのリストが作成されます。

アカウント オブジェクトで公開される要求は、常に、アカウントの 'home tenant'/{authority} からの要求です。 そのアカウントがホーム テナントのトークンの要求に使用されていない場合、MSAL ではアカウント オブジェクトを介して要求を提供できません。 次に例を示します。

```java
// Pseudo Code
IAccount account = getAccount("accountid");

String username = account.getClaims().get("preferred_username");
String tenantId = account.getClaims().get("tid"); // tenant id
String objectId = account.getClaims().get("oid"); // object id
String issuer = account.getClaims().get("iss"); // The tenant specific authority that issued the id_token
```

ヒント

アカウント オブジェクトから利用可能な要求のリストを表示する場合は、「[ID トークン クレーム リファレンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-token-claims-reference)」を参照してください。

ヒント

id\_token に追加の要求を含める場合は、[Microsoft Entra アプリに省略可能な要求を提供する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)に関するページを参照してください

#### テナント プロファイルの要求にアクセスする

他のテナントに示されているアカウントに関する要求にアクセスするには、まず、アカウント オブジェクトを `IMultiTenantAccount` にキャストする必要があります。 アカウントはすべてマルチテナントである場合がありますが、MSAL で利用できるテナント プロファイルの数は、現在のアカウントを使用してトークンを要求したテナントに基づいています。 次に例を示します。

```java
// Pseudo Code
IAccount account = getAccount("accountid");
IMultiTenantAccount multiTenantAccount = (IMultiTenantAccount)account;

multiTenantAccount.getTenantProfiles().get("tenantid for fabrikam").getClaims().get("family_name");
multiTenantAccount.getTenantProfiles().get("tenantid for contoso").getClaims().get("family_name");
```

### B2C ポリシーとアカウント

アカウントの更新トークンは、B2C ポリシー間では共有されません。 そのため、トークンを使用したシングル サインオンを行うことはできません。 これは、シングル サインオンできないことを意味するわけではありません。 シングル サインオンでは、シングル サインオンを有効にするために Cookie を利用できる対話型のエクスペリエンスを使用する必要があることを意味します。

また、MSAL では、異なる B2C ポリシーを使用してトークンを取得する場合、これらが個別のアカウントとして扱われ、それぞれに独自の ID があることを意味します。 アカウントを使用し、`acquireTokenSilent` を使ってトークンを要求する場合は、トークン要求で使用するポリシーに一致するアカウントのリストからアカウントを選択する必要があります。 次に例を示します。

```java
// Get Account For Policy

String policyId = "SignIn";
IAccount signInPolicyAccount = getAccountForPolicyId(app, policyId);

private IAccount getAccountForPolicy(IPublicClientApplication app, String policyId)
{
    List<IAccount> accounts = app.getAccounts();

    foreach(IAccount account : accounts)
   {
        if (account.getClaims().get("tfp").equals(policyId))
        {
            return account;
        }
    }

    return null;
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/android-qr-code-pin-authentication"} -->
## Android アプリで QR コードと PIN 認証を設定する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/android-qr-code-pin-authentication
- Service: identity-platform
- Article date: 2025-02-05
- Summary: Android 用 Microsoft Authentication Library を使用して QR コードと PIN 認証を使用するように Android アプリを構成する方法について説明します。

QR コード認証方法を使用すると、現場担当者は共有デバイス上のアプリにすばやく簡単にサインインできます。 ユーザーは、管理者が提供する一意の QR コードを使用し、PIN を入力してサインインできるため、ユーザー名とパスワードを入力する必要がなくなります。

*login.microsoft.com* で利用できる QR コード Web サインイン エクスペリエンスを使用できます。 このユーザー エントリ ポイントでは、開発者の変更は必要ありません。 ユーザーが [**サインイン オプション**] を選択&gt;**組織にサインイン**&gt;**QR コードでサインイン**します。 サインイン ページにエントリ ポイントを指定することで、QR コードのサインイン エクスペリエンスを最適化し、ユーザーが 2 回クリックする必要がなくなります。 QR コード認証方法を利用するために、アプリ開発者と [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) は連携して作業します。

- アプリ開発者は、Android 用 Microsoft Authentication Library (MSAL) を使用して、QR コード認証の最適化されたエントリ ポイントをアプリに統合します。
- 認証ポリシー管理者は、Microsoft Entra ID で [認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-qr-code) を構成します。

### QR コード認証を使用するようにアプリを構成する

QR コード認証を使用するようにアプリを構成するには、`PreferredAuthMethod` オブジェクトで`AcquireTokenParameters`を QR に設定する必要があります。 次のコード スニペットは、QR コード認証を使用するようにアプリを構成する方法を示しています。

```java
final AcquireTokenParameters acquireTokenParameters = 
    new AcquireTokenParameters.Builder()
    .startAuthorizationFromActivity(activity)
    .withLoginHint(requestOptions.getLoginHint())
     .forAccount(requestOptions.getAccount())
     .withPrompt(requestOptions.getPrompt())
     .withPreferredAuthMethod(PreferredAuthMethod.QR)
     .withCallback(getAuthenticationCallback(callback))
     .build(); 
```

`PreferredAuthMethod`は `PreferredAuthMethod.QR` に設定され、QR コード認証方法を使用することを指定します。 この方法を使用すると、ユーザーは QR コードをスキャンし、自分のピンを入力して認証できます。

`AcquireTokenParameters` オブジェクトを構成したら、`acquireToken` メソッドを呼び出して認証プロセスを開始できます。 次のコード スニペットは、 `AcquireTokenParameters` オブジェクトを使用してトークンを取得する方法を示しています。

```java
// Create the MultipleAccountPublicClientApplication instance with the given configuration
final MultipleAccountPublicClientApplication mpca = new MultipleAccountPublicClientApplication(config);

// Pass the acquireTokenParameters object to the acquireToken function
mpca.acquireToken(acquireTokenParameters);

```

これにより、推奨される QR コード認証方法を含む、指定されたパラメーターを使用してトークン取得プロセスが開始されます。

### 優先する認証方法を取得する

QR コード認証方法は、Microsoft Authenticator アプリ上の [管理対象 Android Enterprise デバイスのアプリ構成ポリシー](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-configuration-policies-use-android) を使用して認証ポリシー管理者によって構成 `preferred_auth_method` 、 `qrpin`と同じ設定になります。

[Image: QR コード認証を構成する方法を示すスクリーンショット。]

`getPreferredAuthMethod` オブジェクトで `mpca` メソッドを呼び出すことで、現在のアカウントの優先認証方法を取得できます。 次のコード スニペットは、現在のアカウントの優先認証方法を取得する方法を示しています。

```java
mpca.getPreferredAuthConfiguration()
```

`getPreferredAuthConfiguration`方法では、Microsoft Authenticator アプリをデバイスにインストールする必要があります。 Microsoft Authenticator アプリがインストールされていない場合、メソッドは `None`を返します。

### カメラの同意プロンプトを表示しない

既定では、QR コードと PIN 認証は、カメラを使用して QR コードをスキャンする必要があるたびに、ユーザーにカメラのアクセス許可を求めます。 ただし、管理者はこの動作を抑制し、カメラのアクセス許可の要求をスキップできます。

[Image: Android QR コードと PIN 認証プロンプトを示すスクリーンショット。]

これは、Microsoft Authenticator アプリの管理対象 Android Enterprise デバイス用のアプリ構成ポリシーを通じて認証ポリシー管理者によって構成され、を`sdm_suppress_camera_consent`に設定する点で、`true`の構成方法と同様です。

この設定が有効な場合:

- OS レベルでカメラのアクセス許可が既に付与されている場合、アプリにはカメラの同意プロンプトは表示されません。
- ユーザーは、アクセス許可要求を繰り返すことなく、よりスムーズな認証エクスペリエンスを実現できます。
- QR コードのスキャン フローは、マネージド デバイスに対してより合理化されます。

この構成は、デバイスが管理され、IT 管理者がカメラのアクセス許可を事前に構成できるエンタープライズ環境で役立ちます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/app-objects-and-service-principals"} -->
## Microsoft Entra ID のアプリケーションとサービス プリンシパル - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals
- Service: identity-platform
- Article date: 2024-10-01
- Summary: Microsoft Entra ID におけるアプリケーションとサービス プリンシパルの各オブジェクト間のリレーションシップについて説明します。

この記事では、Microsoft Entra ID のアプリケーション登録、アプリケーション オブジェクト、およびサービス プリンシパルの概要、使用方法、相互関係について説明します。 アプリケーションのアプリケーション オブジェクトと対応するサービス プリンシパル オブジェクトの間の関係を示すために、マルチテナントのシナリオ例も紹介します。

### アプリケーションの登録

ID およびアクセス管理の機能を Microsoft Entra ID に委任するには、アプリケーションを Microsoft Entra テナントに登録する必要があります。 アプリケーションを Microsoft Entra ID に登録するとき、アプリケーションの ID 構成を作成します。これによって Microsoft Entra ID との統合が可能になります。 アプリを登録するときに、そのアプリが[シングル テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-and-multi-tenant-apps#who-can-sign-in-to-your-app)か[マルチテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-and-multi-tenant-apps#who-can-sign-in-to-your-app)かを選択し、必要に応じて[リダイレクト URI](https://learn.microsoft.com/ja-jp/entra/identity-platform/reply-url) を設定します。 アプリを登録する手順については、[アプリの登録に関するクイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)を参照してください。

アプリの登録が完了すると、ホーム テナントまたはディレクトリ内に存在するアプリ (アプリケーション オブジェクト) のグローバルに一意なインスタンスが作成されます。 また、アプリにグローバルに一意な ID (アプリまたはクライアント ID) も割り当てられます。 シークレットまたは証明書とスコープを追加してアプリを機能させたり、サインイン ダイアログでアプリのブランド化をカスタマイズしたりすることができます。

アプリケーションを登録すると、ホーム テナントにアプリケーション オブジェクトとサービス プリンシパル オブジェクトが自動的に作成されます。 Microsoft Graph API を使用してアプリケーションを登録または作成する場合、サービス プリンシパル オブジェクトの作成は別の手順です。

### アプリケーション オブジェクト

Microsoft Entra アプリケーションは、その唯一のアプリケーション オブジェクトによって定義されます。アプリケーション オブジェクトは、アプリケーションの登録先の Microsoft Entra テナント (アプリケーションの "ホーム" テナントという) 内にあります。 アプリケーション オブジェクトは、1 つ以上のサービス プリンシパル オブジェクトを作成するためのテンプレートまたはブループリントとして使用されます。 サービス プリンシパルは、アプリケーションが使用されるすべてのテナントに作成されます。 オブジェクト指向プログラミングのクラスと同様に、アプリケーション オブジェクトには、作成されたすべてのサービス プリンシパル (またはアプリケーション インスタンス) に適用されるいくつかの静的プロパティがあります。

アプリケーション オブジェクトでは、アプリケーションの 3 つの側面について説明します。

- サービスがアプリケーションにアクセスするためにトークンを発行する方法
- アプリケーションがアクセスする必要があるリソース
- アプリケーションが実行できるアクション

**Microsoft Entra 管理センター**の [\[アプリの登録\]](https://entra.microsoft.com) ページを使用して、ホーム テナントのアプリケーション オブジェクトを一覧表示し、管理することができます。

[Image: [アプリの登録] ブレード]

アプリケーション オブジェクトのプロパティのスキーマは、Microsoft Graph [Application エンティティ](https://learn.microsoft.com/ja-jp/graph/api/resources/application)によって定義されています。

### サービス プリンシパル オブジェクト

Microsoft Entra テナントによってセキュリティ保護されているリソースにアクセスするには、アクセスを必要とするエンティティをセキュリティ プリンシパルで表す必要があります。 この要件は、ユーザー (ユーザー プリンシパル) とアプリケーション (サービス プリンシパル) の両方に当てはまります。 セキュリティ プリンシパルは、その Microsoft Entra テナント内のユーザー/アプリケーションのアクセス ポリシーとアクセス許可を定義します。 これにより、サインイン時のユーザー/アプリケーションの認証、リソースへのアクセス時の承認などのコア機能を利用できるようになります。

サービス プリンシパルには、次の 3 種類があります。

- **アプリケーション** - この種類のサービス プリンシパルは、単一のテナントまたはディレクトリ内のグローバル アプリケーション オブジェクトのローカル表現、つまりアプリケーション インスタンスです。 この場合、サービス プリンシパルは、アプリケーション オブジェクトから作成された具象インスタンスであり、そのアプリケーション オブジェクトから特定のプロパティが継承されます。 サービス プリンシパルは、アプリケーションが使用される各テナントで作成され、グローバルに一意なアプリ オブジェクトが参照されます。 サービス プリンシパル オブジェクトには、特定のテナント内でアプリが実際に実行できること、アプリにアクセスできるユーザー、アプリからアクセスできるリソースを定義します。

    アプリケーションが (登録または同意によって) テナント内のリソースへのアクセス許可を与えられると、サービス プリンシパル オブジェクトが作成されます。 アプリケーションを登録すると、サービス プリンシパルが自動的に作成されます。 Azure PowerShell、Azure CLI、Microsoft Graph、およびその他のツールを使用して、テナントにサービス プリンシパル オブジェクトを作成することもできます。
- **マネージド ID** - この種類のサービス プリンシパルは、[マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) を表す目的で使用されます。 マネージド ID により、開発者は資格情報を管理する必要がなくなります。 マネージド ID は、Microsoft Entra 認証をサポートするリソースに接続するときに使う ID をアプリケーションに提供します。 マネージド ID が有効になっている場合、そのマネージド ID を表すサービス プリンシパルがテナントに作成されます。 マネージド ID を表すサービス プリンシパルには、権利やアクセス許可を付与できますが、直接更新したり変更を加えたりすることはできません。 マネージド ID を表すサービス プリンシパルには、(上記のアプリケーションの種類とは異なり) アプリ オブジェクトが関連付けられません。
- **レガシ** - この種類のサービス プリンシパルは、レガシ アプリを表します。これは、アプリの登録前に作成されたアプリ、またはレガシ エクスペリエンスを使用して作成されたアプリです。 レガシ サービス プリンシパルには、資格情報、サービス プリンシパル名、応答 URL など、許可されているユーザーが編集できるプロパティを割り当てることができますが、アプリの登録は関連付けられません。 このサービス プリンシパルは、その作成元のテナントでのみ使用できます。

サービス プリンシパル オブジェクトのプロパティのスキーマは、Microsoft Graph [ServicePrincipal エンティティ](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal)によって定義されています。

Microsoft Entra 管理センターの **[エンタープライズ アプリケーション]** ページを使用して、テナントのサービス プリンシパルを一覧表示および管理することができます。 サービス プリンシパルのアクセス許可、ユーザーが同意したアクセス許可、その同意を行ったユーザー、サインイン情報などを確認できます。

[Image: エンタープライズ アプリ ブレード]

### アプリケーション オブジェクトとサービス プリンシパル間のリレーションシップ

アプリケーション オブジェクトは、すべてのテナントで使用するアプリケーションの "*グローバル*" 表現であり、サービス プリンシパルは、特定のテナントで使用する "*ローカル*" 表現です。 アプリケーション オブジェクトは、対応するサービス プリンシパル オブジェクトの作成に使用するために、一般的な既定のプロパティが*派生*するテンプレートとして機能します。

アプリケーション オブジェクトには以下があります。

- ソフトウェア アプリケーションとの 1 対 1 の関係
- 対応するサービス プリンシパル オブジェクトとの 1 対多のリレーションシップ。

サービス プリンシパルは、テナントによってセキュリティ保護されているリソースにサインインまたはアクセスするための ID を確立できるように、アプリケーションが使用される各テナントで作成する必要があります。 シングルテナント アプリケーションには、アプリケーション登録中に作成され、使用が同意されたサービス プリンシパルが (そのホーム テナントに) 1 つだけあります。 マルチテナント アプリケーションには、そのテナントのユーザーが使用に同意した各テナントで作成されたサービス プリンシパルもあります。

#### アプリに関連するサービス プリンシパルを一覧表示する

アプリケーション オブジェクトに関連するサービス プリンシパルを見つけることができます。

## [ブラウザー](#tab/browser)
Microsoft Entra 管理センターで、アプリケーション登録の概要に移動します。 **[ローカル ディレクトリでのマネージド アプリケーション]** を選びます。

[Image: 概要の [ローカル ディレクトリでのマネージド アプリケーション] オプションを示すスクリーン ショット。]

## [PowerShell](#tab/azure-powershell)
Microsoft Graph PowerShell を使用する:

```azurepowershell
Get-MgServicePrincipal -Filter "appId eq '{AppId}'"
```

## [Azure CLI](#tab/azure-cli)
Azure CLI の使用:

```azurecli
az ad sp list --filter "appId eq '{AppId}'"
```

---

#### アプリケーションの変更と削除の結果

アプリケーション オブジェクトに加えたすべての変更は、アプリケーションのホーム テナント (アプリケーションが登録されたテナント) にだけ存在するサービス プリンシパル オブジェクトにも反映されます。 つまり、アプリケーション オブジェクトを削除すると、そのホーム テナントのサービス プリンシパル オブジェクトも削除されます。 ただし、アプリの登録 UI からそのアプリケーション オブジェクトを復元しても、対応するサービス プリンシパルは復元されません。

完全削除ではなく一時的な中断が必要なアプリケーションの場合は、 [アプリケーションを非アクティブ化](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/deactivate-application-portal)できます。 非アクティブ化により、調査または将来の再アクティブ化のためにアプリケーション オブジェクトとサービス プリンシパルを保持しながら、新しいトークンの発行が防止されます。

アプリケーションとそのサービス プリンシパル オブジェクトの削除と復元について詳しくは、[アプリケーションとサービス プリンシパル オブジェクトの削除と復元](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-recover-faq)に関する記事を参照してください。

### 例

次の図は、**HR アプリ**という名前のサンプル マルチテナント アプリケーションを基に、アプリケーションのアプリケーション オブジェクトと、対応するサービス プリンシパル オブジェクトの間のリレーションシップを表しています。 このサンプル シナリオには、次の 3 つの Microsoft Entra テナントがあります。

- **Adatum** - **HR アプリ**を開発した会社が使用するテナント
- **Contoso** - **HR アプリ**のコンシューマーである Contoso という組織が使用するテナント
- **Fabrikam** - Contoso と同じく **HR アプリ**のコンシューマーである Fabrikam という組織が使用するテナント

[Image: アプリ オブジェクトとサービス プリンシパル オブジェクトの間のリレーションシップ]

このサンプル シナリオの内容:

| 手順 | 説明 |
| --- | --- |
| 1 | アプリケーションとサービス プリンシパル オブジェクトを、アプリケーションのホーム テナント内に作成するプロセスです。 |
| 2 | Contoso と Fabrikam の管理者が同意を終えると、それぞれの会社の Microsoft Entra テナント内にサービス プリンシパル オブジェクトが作成され、それに管理者が付与したアクセス許可が割り当てられます。 HR アプリは、個々のユーザー用として、ユーザーによる同意を許可するように構成/設計することができる点にも注目してください。 |
| 3 | HR アプリケーション (Contoso と Fabrikam) のコンシューマー テナントにそれぞれ独自のサービス プリンシパル オブジェクトが作成されます。 それぞれ実行時におけるアプリケーションのインスタンスの使用を表し、それぞれの管理者によって同意されたアクセス許可によって管理されます。 |
<!-- /MSL-PAGE -->
