# Microsoft Learn — Microsoft Entra / 開発者向け (Identity Platform・MSAL・Microsoft.Identity.Web) (part 1)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 19

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
