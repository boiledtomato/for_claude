# Microsoft Learn — Microsoft Entra / 基礎・アーキテクチャ・標準・その他 (part 3)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 53

---

<!-- MSL-PAGE {"url":"entra/architecture/secure-resource-management"} -->
## Microsoft Entra ID でのリソース管理の基礎 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/secure-resource-management
- Service: entra / architecture
- Article date: 2024-10-15
- Summary: Microsoft Entra ID でのリソース管理の概要。

Azure リソースに固有の構造と用語を理解することが重要です。 次の図は、Azure によって提供されるスコープの 4 つのレベルの例を示しています。

[Image: Azure リソース管理モデルを示す図。]

### 用語

理解しておくべきいくつかの用語を次に示します。

**リソース** - Azure を通じて使用できる管理可能な項目です。 リソースの例として、仮想マシン、ストレージ アカウント、Web アプリ、データベース、および仮想ネットワークがあります。

**リソース グループ** - 特定のチームによる管理を必要とする仮想マシン、関連付けられた VNet、ロード バランサーのコレクションなど、Azure ソリューションの関連リソースを保持するコンテナー。 [リソース グループ](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview)には、グループとして管理するリソースが含まれます。 組織にとって最も有用になるように、どのリソースをリソース グループに含めるかを決定します。 またリソース グループは、有効期間が同じすべてのリソースを一度に削除することで、ライフサイクル管理に役立てるように使用することもできます。 このアプローチには、悪用される可能性のあるフラグメントを残さないことによるセキュリティ上の利点もあります。

**サブスクリプション** - 組織階層の観点から見ると、サブスクリプションはリソースとリソース グループの課金と管理のコンテナーです。 Azure サブスクリプションには、Microsoft Entra ID との信頼関係があります。 サブスクリプションでは、Microsoft Entra ID を信頼して、ユーザー、サービス、デバイスの認証が行われます。

注

サブスクリプションでは、1 つの Microsoft Entra テナントのみを信頼できます。 ただし、各テナントは複数のサブスクリプションを信頼する場合があり、サブスクリプションをテナント間で移動することができます。

**管理グループ** - [Azure 管理グループ](https://learn.microsoft.com/ja-jp/azure/governance/management-groups/overview)では、サブスクリプションの上のさまざまなスコープで、ポリシーとコンプライアンスを適用する階層的な方法が提供されます。 テナント ルート管理グループ（最上位スコープ）または階層内の下位レベルに配置できます。 "管理グループ" と呼ばれるコンテナーにサブスクリプションを整理して、管理グループに管理条件を適用できます。 管理グループ内のすべてのサブスクリプションは、管理グループに適用された条件を自動的に継承します。 ポリシー定義は、管理グループまたはサブスクリプションに適用できます。

**リソース プロバイダー** - Azure リソースを提供するサービス。 たとえば、一般的な[リソース プロバイダー](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/resource-providers-and-types)は Microsoft です。 Compute は、仮想マシン リソースを提供します。 Microsoft。 Storage は、もう 1 つの一般的なリソースプロバイダーです。

**Resource Manager テンプレート** - リソース グループ、サブスクリプション、テナント、または管理グループにデプロイする 1 つまたは複数のリソースを定義する JavaScript Object Notation (JSON) ファイル。 このテンプレートを使えば、リソースを一貫して繰り返しデプロイできます。 [テンプレートのデプロイの概要](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/templates/overview)に関するページを参照してください。 さらに、JSON の代わりに [Bicep 言語](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/bicep/overview)を使用できます。

### Azure リソース管理モデル

それぞれの Azure サブスクリプションは、[Azure Resource Manager](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview) によって使用される制御に関連付けられています。 Resource Manager は Azure のデプロイおよび管理サービスであり、組織の ID 管理については Microsoft Entra ID と、個人については Microsoft アカウント (MSA) と、信頼関係を持っています。 Resource Manager には、Azure サブスクリプションのリソースを作成、更新、削除できる管理レイヤーが用意されています。 アクセス制御、ロック、タグなどの管理機能を使用して、デプロイ後にリソースをセキュリティ保護および整理します。

注

ARM 以前に、Azure Service Manager (ASM) または "クラシック" という名前の別のデプロイ モデルが存在していました。 詳細については、「[Azure Resource Manager とクラシック デプロイ](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/deployment-models)」を参照してください。 ASM モデルを使用した環境の管理は、このコンテンツの範囲外です。

Azure Resource Manager は、PowerShell、Azure portal、またはリソースを管理するためのその他のクライアントによって使用される REST API をホストする、フロントエンド サービスです。 クライアントが特定のリソースを管理するように要求すると、Resource Manager は要求を完了するために、要求をリソース プロバイダーにプロキシ処理します。 たとえば、クライアントが仮想マシン リソースを管理するように要求した場合、Resource Manager は要求を Microsoft にプロキシします。 計算資源プロバイダー Resource Manager は仮想マシン リソースを管理するために、サブスクリプションとリソース グループの両方の識別子を指定するようクライアントに要求します。

Resource Manager によってリソースの管理要求が実行される前に、一連の制御がチェックされます。

- **有効なユーザー チェック** - リソースの管理を要求するユーザーは、マネージド リソースのサブスクリプションに関連付けられている Microsoft Entra ID テナント内のアカウントを持っている必要があります。
- **ユーザー アクセス許可チェック** - [ロールベースのアクセス制御 (RBAC)](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/overview) を使用して、アクセス許可がユーザーに割り当てられます。 RBAC ロールでは、特定のリソースに対してユーザーが適用できる一連のアクセス許可が指定されます。 RBAC は、Azure のリソースにアクセスできるユーザー、そのユーザーがそれらのリソースに対して実行できること、そのユーザーがアクセスできる領域を管理するのに役立ちます。
- **Azure ポリシー チェック** - [Azure ポリシー](https://learn.microsoft.com/ja-jp/azure/governance/policy/overview)では、特定のリソースに対して許可または明示的に拒否される操作が指定されます。 たとえば、ポリシーを使用して、ユーザーが特定の種類の仮想マシンのみデプロイできる (またはできない) ように指定できます。

次の図は、先ほど説明したリソース モデルをまとめたものです。

[Image: ARM と Microsoft Entra ID を使用した Azure リソース管理を示す図。]

**Azure Lighthouse** - [Azure Lighthouse](https://learn.microsoft.com/ja-jp/azure/lighthouse/overview) を使用すると、テナント間でのリソース管理が可能になります。 組織は、サブスクリプションまたはリソース グループ レベルのロールを別のテナントの ID に委任できます。

Azure Lighthouse で[委任されたリソース管理](https://learn.microsoft.com/ja-jp/azure/lighthouse/concepts/architecture)を有効にするサブスクリプションには、サブスクリプションまたはリソース グループを管理できるテナント ID を示す属性と、リソース テナントの組み込み RBAC ロールとサービス プロバイダー テナント内の ID とのマッピングを示す属性があります。 実行時に、Azure Resource Manager ではこれらの属性を使用して、サービス プロバイダー テナントからのトークンを承認します。

Azure Lighthouse 自体が Azure リソース プロバイダーとしてモデル化されていることに注意してください。つまり、Azure ポリシーによって、テナントをまたぐ委任の側面を対象にすることができます。

**Microsoft 365 Lighthouse** - [Microsoft 365 Lighthouse](https://learn.microsoft.com/ja-jp/microsoft-365/lighthouse/m365-lighthouse-overview?view=o365-worldwide&preserve-view=true) は、Microsoft 365 Business Premium、Microsoft 365 E3、または Windows 365 Business を使っている中小規模企業 (SMB) の顧客向けに、デバイス、データ、ユーザーを大規模にセキュリティで保護して管理する、マネージド サービス プロバイダー (MSP) を支援するための管理ポータルです。

### Microsoft Entra ID を使用した Azure リソース管理

Azure のリソース管理モデルについて深く理解できたので、次に Azure リソースの ID とアクセス管理を提供できる Microsoft Entra ID の機能をいくつか簡単に見てみましょう。

#### 請求管理

一部の課金ロールでは、リソースを操作したりリソースを管理したりできるため、課金はリソース管理にとって重要です。 課金は、Microsoft との契約の種類によって動作が異なります。

##### Azure エンタープライズ アグリーメント

Azure Enterprise Agreement (Azure EA) のお客様は、Microsoft との商用契約の実行時に Azure EA Portal にオンボードされます。 オンボーディング時に、ID は「ルート」エンタープライズ管理者の請求ロールに関連付けられます。 ポータルには、管理機能の階層が用意されています。

- 部門は、コストを論理グループに分割し、部門レベルで予算またはクォータを設定するのに役立ちます。
- アカウントは、部門をさらにセグメント化するために使用されます。 アカウントを使用して、サブスクリプションを管理し、レポートにアクセスできます。 EA ポータルでは、Microsoft アカウント (MSA) または Microsoft Entra アカウント (ポータルで "職場または学校アカウント" として識別される) を承認できます。 EA ポータルで "アカウント所有者" のロールを持つ ID によって、Azure サブスクリプションを作成できます。

##### エンタープライズ課金と Microsoft Entra テナント

アカウント所有者がエンタープライズ契約内で Azure サブスクリプションを作成すると、サブスクリプションの ID とアクセス管理は次のように構成されます。

- Azure サブスクリプションは、アカウント所有者と同じ Microsoft Entra テナントに関連付けられます。
- サブスクリプションを作成したアカウント所有者には、サービス管理者ロールとアカウント管理者ロールが割り当てられます。 (Azure EA Portal では、サブスクリプションを管理するために Azure Service Manager (ASM) ロールまたは "クラシック" ロールが割り当てられます。 詳細については、「[Azure Resource Manager とクラシック デプロイ](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/deployment-models)」を参照してください。

Azure EA Portal で認証の種類として "テナント間の職場または学校アカウント" を設定することで、複数のテナントをサポートするようにエンタープライズ契約を構成できます。 上記の場合、組織は次の図に示すように、テナントごとに複数のアカウントを設定し、アカウントごとに複数のサブスクリプションを設定できます。

[Image: Enterprise Agreement の課金構造を示す図。]

上で説明した既定の構成では、作成したすべてのサブスクリプションのリソースを管理するための Azure EA アカウント所有者特権が付与されることに注意してください。 運用ワークロードを保持しているサブスクリプションの場合は、作成直後にサブスクリプションのサービス管理者を変更して、課金とリソース管理を分離することを検討してください。

アカウント所有者をさらに分離し、サブスクリプションへのサービス管理者アクセス権を回復できないようにするために、作成後にサブスクリプションのテナントを[変更](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-subscriptions-associated-directory)できます。 サブスクリプションの移動先の Microsoft Entra テナント内に、アカウント所有者がユーザー オブジェクトを持っていない場合、サービス所有者ロールを回復できません。

詳細については、[従来のサブスクリプション管理者ロール、Azure ロール、および Microsoft Entra ロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/rbac-and-directory-admin-roles)に関する記事を参照してください。

#### Microsoft 顧客契約

[Microsoft 顧客契約](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/understand/mca-overview) (MCA) に登録された顧客には、独自のロールを持つ異なる課金管理システムがあります。

Microsoft 顧客契約の[請求先アカウント](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/understand-mca-roles)には、請求書および支払方法を管理できるようにする 1 つまたは複数の[課金プロファイル](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/understand-mca-roles)が含まれています。 各課金プロファイルには、課金プロファイルの請求書上でコストを整理するための、1 つまたは複数の[請求書セクション](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/understand-mca-roles)が含まれています。

Microsoft 顧客契約では、課金ロールは 1 つの Microsoft Entra テナントから取得されます。 複数のテナントのサブスクリプションをプロビジョニングするには、最初に MCA と同じ Microsoft Entra テナントにサブスクリプションを作成し、その後に変更する必要があります。 次の図では、企業の IT 部門の運用前環境のサブスクリプションは、作成後に ContosoSandbox テナントに移動されました。

[Image: MCA の課金構造を示す図。]

### Azure での RBAC とロールの割り当て

「Microsoft Entra の基礎」セクションでは、Azure RBAC が Azure リソースへのきめ細かなアクセス管理を提供し、多くの[組み込みロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles)を含む認可システムであることを学習しました。 [カスタム ロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/custom-roles)を作成し、さまざまなスコープでロールを割り当てることができます。 アクセス許可は、Azure リソースへのアクセスを要求するオブジェクトに RBAC ロールを割り当てることによって適用されます。

Microsoft Entra ロールは、[Azure のロールベースのアクセス制御](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/overview)に似た概念で動作します。 [これら 2 つのロールベースのアクセス制御システムの違い](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/rbac-and-directory-admin-roles)は、Azure RBAC では、Azure Resource Management を使用して仮想マシンやストレージなどの Azure リソースへのアクセスが制御されるのに対し、Microsoft Entra ロールでは、Microsoft Entra ID、アプリケーション、Office 365 などの Microsoft サービスへのアクセスが制御されることです。

Microsoft Entra ロールと Azure RBAC ロールの両方が Microsoft Entra Privileged Identity Management と統合され、承認ワークフローや MFA などの Just-In-Time アクティブ化ポリシーが有効になります。

### Azure での ABAC とロールの割り当て

[属性ベースのアクセス制御 (ABAC)](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/conditions-overview) は、セキュリティ プリンシパル、リソース、環境に関連付けられている属性に基づいてアクセスを定義する認可システムです。 ABAC を使用すると、属性に基づいてセキュリティ プリンシパルにリソースへのアクセス権を付与できます。 Azure ABAC は、Azure 向けに実装された ABAC を指します。

Azure ABAC は、Azure RBAC を基盤としたものであり、特定のアクションのコンテキストにおける属性に基づいたロールの割り当て条件を追加することにより構築されます。 ロールの割り当て条件は、ロールの割り当てに追加することによってさらにきめ細かなアクセス制御を可能にする確認のしくみの 1 つです。 条件は、ロールの定義とロールの割り当ての一環として付与されたアクセス許可を絞り込むものです。 たとえば、オブジェクトを読み取るためには、特定のタグが付いている必要があるという条件を追加できます。 条件を使用して特定のリソースに対するアクセスを明示的に拒否することはできません。

### 条件付きアクセス

Microsoft Entra の [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps)を使用して、Azure 管理エンドポイントへのアクセスを管理できます。 条件付きアクセス ポリシーを Windows Azure Service Management API クラウド アプリに適用して、次のような Azure リソース管理エンドポイントを保護できます。

- Azure Resource Manager プロバイダー (サービス)
- Azure Resource Manager API
- Azure PowerShell
- Azure CLI
- Azure portal

[Image: 条件付きアクセス ポリシーを示すスクリーンショット。]

たとえば、管理者は条件付きアクセス ポリシーを構成できます。これにより、ユーザーは承認済みの場所からのみ Azure portal にサインインでき、多要素認証 (MFA) またはハイブリッド Microsoft Entra ドメイン参加済みデバイスも必要になります。

### Azure マネージド ID

クラウド アプリケーションの構築時における一般的な課題は、クラウド サービスへの認証用のコードで資格情報をどのように管理するかです。 資格情報を安全に保つことは重要な課題です。 資格情報は開発者のワークステーションに表示されないこと、またソース管理にチェックインされないことが理想です。 [Azure リソースのマネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) は、Microsoft Entra ID で自動的に管理される ID を Azure サービスに提供します。 その ID を使用して、コードで資格情報を渡すことなく Microsoft Entra 認証をサポートするすべてのサービスに対して認証します。

マネージド ID には、次の 2 種類があります。

- システム割り当てマネージド ID は、Azure リソース上で直接有効にされます。 リソースが有効になると、Azure によって、関連付けられているサブスクリプションの信頼された Microsoft Entra テナントに、リソースの ID が作成されます。 ID が作成されると、その資格情報がリソースにプロビジョニングされます。 システム割り当て ID のライフサイクルは、Azure リソースに直接関連付けられます。 リソースが削除された場合、Azure は Microsoft Entra ID の資格情報および ID を自動的にクリーンアップします。
- ユーザー割り当てマネージド ID は、スタンドアロン Azure リソースとして作成されます。 Azure では、リソースが関連付けられているサブスクリプションによって信頼される Microsoft Entra テナントに ID が作成されます。 作成された ID は、1 つまたは複数の Azure リソースに割り当てることができます。 ユーザー割り当て ID のライフサイクルは、その ID が割り当てられている Azure リソースのライフサイクルとは別々に管理されます。

内部的には、マネージド ID は特別な種類のサービス プリンシパルであり、特定の Azure リソースによってのみ使用されます。 マネージド ID が削除されると、対応するサービス プリンシパルが自動的に削除されます。 Graph API アクセス許可の承認は PowerShell でのみ実行できるため、ポータル UI を介してマネージド ID のすべての機能にアクセスできるわけではありません。

### Microsoft Entra Domain Services

Microsoft Entra Domain Services には、レガシ プロトコルを使用した Azure ワークロードの認証を容易にするマネージド ドメインが用意されています。 サポートされているサーバーは、オンプレミスの AD DS フォレストから移動され、Microsoft Entra Domain Services マネージド ドメインに参加し、認証 (Kerberos 認証など) にレガシ プロトコルを引き続き使用します。

### Azure AD B2C ディレクトリと Azure

重要

2025 年 5 月 1 日より、Azure Active Directory B2C (Azure AD B2C) は新規のお客様が購入できなくなります。 詳細については、FAQ の [「Azure AD B2C を引き続き購入できますか?」を](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) 参照してください。

Azure AD B2C テナントは、課金と通信の目的で Azure サブスクリプションにリンクされています。 Azure AD B2C テナントは、Azure サブスクリプションの Azure RBAC 特権ロールとは独立した、ディレクトリ内の自己完結型ロール構造を持ちます。

Azure AD B2C テナントが最初にプロビジョニングされるとき、B2C テナントを作成するユーザーには、サブスクリプションの共同作成者または所有者のアクセス許可が必要です。 後で他のアカウントを作成し、ディレクトリ ロールに割り当てができます。 詳細については、「[Microsoft Entra ID でのロールベースのアクセス制御の概要](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview)」を参照してください。

リンクされた Microsoft Entra サブスクリプションの所有者と共同作成者は、サブスクリプションとディレクトリ間のリンクを削除できることに注意してください。これは、Azure AD B2C の使用状況の継続的な課金に影響します。

### Azure での IaaS ソリューションの ID に関する考慮事項

このシナリオでは、サービスとしてのインフラストラクチャ (IaaS) ワークロードに対して組織が持っている ID 分離要件について説明します。

IaaS ワークロードの分離管理には、次の 3 つの主要なオプションがあります。

- スタンドアロンの Active Directory Domain Services (AD DS) に参加している仮想マシン
- Microsoft Entra Domain Services に参加した仮想マシン
- Microsoft Entra 認証を使用して Azure の仮想マシンにサインインする

最初の 2 つのオプションで対処する重要な概念は、これらのシナリオには 2 つの ID 領域が関連しているということです。

- リモート デスクトップ プロトコル (RDP) を使用して Azure Windows Server VM にサインインする場合、通常はドメイン資格情報を使用してサーバーにログオンし、これによってオンプレミスの AD DS ドメイン コントローラーまたは Microsoft Entra Domain Services に対して Kerberos 認証が実行されます。 あるいは、サーバーがドメイン参加済みでない場合、ローカル アカウントを使用して仮想マシンにサインインできます。
- VM を作成または管理するために Azure portal にサインインすると、Microsoft Entra ID に対して認証が行われます (正しいアカウントを同期している場合は、同じ資格情報を使用する可能性があります)。Active Directory フェデレーション サービス (AD FS) 認証またはパススルー認証を使用している場合、これは結果として、ドメイン コントローラーに対する認証となる可能性があります。

#### スタンドアロンの Active Directory Domain Services に参加している仮想マシン

AD DS は、組織がオンプレミスの ID サービスに広く採用している Windows Server ベースのディレクトリ サービスです。 AD DS は、IaaS ワークロードを Azure にデプロイする要件が存在し、それによって別のフォレストの AD DS 管理者とユーザーからの ID の分離が必要な場合にデプロイできます。

[Image: AD DS 仮想マシンの管理を示す図。]

このシナリオでは、次の事項を考慮する必要があります。

AD DS ドメイン コントローラー: 認証サービスの高可用性と高パフォーマンスを保証するには、少なくとも 2 つの AD DS ドメイン コントローラーをデプロイする必要があります。 詳細については、「[AD DS の設計と計画](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/ad-ds-design-and-planning)」を参照してください。

**AD DS の設計と計画** - 次のサービスが正しく構成された新しい AD DS フォレストを作成する必要があります。

- **AD DS ドメイン ネーム サービス (DNS)** - サーバーとアプリケーションで名前解決が正しく動作するように、AD DS 内の関連ゾーンに対して AD DS DNS を構成する必要があります。
- **AD DS のサイトとサービス** - これらのサービスは、アプリケーションの低待機時間と、ドメイン コントローラーに対する高パフォーマンスのアクセスを確保するように構成する必要があります。 関連する仮想ネットワーク、サブネット、サーバーが配置されているデータ センターの場所は、サイトとサービスで構成する必要があります。
- **AD DS FSMO** - 必要なフレキシブル シングル マスター操作 (FSMO) ロールを確認し、適切な AD DS ドメイン コントローラーに割り当てる必要があります。
- **AD DS ドメイン参加** - 認証、構成、管理に AD DS を必要とするすべてのサーバー ("ジャンプボックス" を除く) を分離フォレストに参加させる必要があります。
- **AD DS グループ ポリシー (GPO)** - 構成がセキュリティ要件を満たしていること、および構成がフォレストとドメイン参加済みマシン全体で標準化されるように、AD DS GPO を構成する必要があります。
- **AD DS 組織単位 (OU)** - 構成の管理と適用を目的として、AD DS リソースを論理的な管理および構成のサイロにグループ化するには、AD DS OU を定義する必要があります。
- **ロールベースのアクセス制御** - このフォレストに参加しているリソースの管理とアクセスのために RBAC を定義する必要があります。 これには次のものが含まれます

    - **AD DS グループ** - AD DS リソースに対するユーザーの適切なアクセス許可を適用するには、グループを作成する必要があります。
    - **管理アカウント** - このセクションの冒頭で説明したように、このソリューションを管理するには 2 つの管理アカウントが必要です。

        - AD DS およびドメイン参加済みサーバーで必要な管理を実行するために必要な最小限の特権アクセスを持つ AD DS 管理アカウント。
        - 仮想マシン、VNet、ネットワーク セキュリティ グループ、その他の必要な Azure リソースに接続し、管理し、構成するための Azure portal アクセス用の Microsoft Entra 管理アカウント。
    - **AD DS ユーザー アカウント** - このソリューションでホストされているアプリケーションへのユーザー アクセスを許可するには、関連するユーザー アカウントをプロビジョニングし、正しいグループに追加する必要があります。

**仮想ネットワーク (VNet)** - 構成ガイダンス

- **AD DS ドメイン コントローラーの IP アドレス** - ドメイン コントローラーは、オペレーティング システム内の静的 IP アドレスで構成しないでください。 IP アドレスは、常に同じに保たれ、DHCP を使用するように DC を構成する必要があるため、Azure VNet 上で予約する必要があります。
- **VNet DNS サーバー** - この分離ソリューションの一部である VNet 上に DNS サーバーを構成して、ドメイン コントローラーを指す必要があります。 これはアプリケーションとサーバーが、必要な AD DS サービスまたは AD DS フォレストに参加しているその他のサービスを解決できるようにするために必要です。
- **ネットワーク セキュリティ グループ (NSG)** - ドメイン コントローラーは、必要なサーバー (ドメイン参加済みのマシンやジャンプボックスなど) からのドメイン コントローラーへのアクセスのみを許可するように定義された NSG を使用して、独自の VNet またはサブネットに配置する必要があります。 NSG の作成と管理を簡略化するには、アプリケーション セキュリティ グループ (ASG) にジャンプボックスを追加する必要があります。

**課題**: 以下の一覧では、ID の分離にこのオプションを使用する際の主な課題を示します。

- 運用、管理、監視する AD DS フォレストが追加されることで、IT チームの作業が増えます。
- 修正プログラムの適用とソフトウェアのデプロイを管理するために、追加のインフラストラクチャが必要になる場合があります。 組織では、これらのサーバーを管理するために、Azure Update Management、グループ ポリシー (GPO) または System Center Configuration Manager (SCCM) のデプロイを検討する必要があります。
- ユーザーがリソースにアクセスするために覚えて使用する追加の資格情報。

重要

この分離モデルでは、顧客の企業ネットワークからのドメイン コントローラーとの間に接続がなく、他のフォレストとの信頼が構成されていないことが前提になります。 AD DS ドメイン コントローラーを管理および運用できるポイントを許可するには、ジャンプボックスまたは管理サーバーを作成する必要があります。

#### Microsoft Entra Domain Services に参加した仮想マシン

IaaS ワークロードを Azure にデプロイする要件が存在し、それによって別のフォレストの AD DS 管理者とユーザーからの ID 分離が必要な場合、Microsoft Entra Domain Services マネージド ドメインをデプロイできます。 Microsoft Entra Domain Services は、レガシ プロトコルを使用した Azure ワークロードの認証を容易にするマネージド ドメインが用意されているサービスです。 これにより、独自の AD DS を構築して管理する技術的な複雑さに頼ることなく、分離されたドメインが提供されます。 次の事項を考慮する必要があります。

[Image: Microsoft Entra Domain Services 仮想マシンの管理を示す図。]

**Microsoft Entra Domain Services マネージド ドメイン** - Microsoft Entra テナントごとにデプロイできる Microsoft Entra Domain Services マネージド ドメインは 1 つだけで、これは 1 つの VNet にバインドされます。 この VNet が Microsoft Entra Domain Services 認証の "ハブ" を形成するようにすることをお勧めします。 このハブから、"スポーク" を作成してリンクし、サーバーとアプリケーションのレガシ認証を許可できます。 スポークは、Microsoft Entra Domain Services 参加済みサーバーが配置され、Azure ネットワーク ゲートウェイまたは VNet ピアリングを使用してハブにリンクされる追加の VNet です。

**マネージド ドメインの場所** - Microsoft Entra Domain Services マネージド ドメインをデプロイするときは、場所を設定する必要があります。 場所は、マネージド ドメインがデプロイされる物理リージョン (データ センター) です。 次のようにすることをお勧めします。

- Microsoft Entra Domain Services サービスを必要とするサーバーとアプリケーションに対して地理的に閉じられている場所を検討してください。
- 高可用性要件のための Availability Zones 機能を提供するリージョンを検討してください。 詳細については、「[Azure のリージョンと Availability Zones](https://learn.microsoft.com/ja-jp/azure/reliability/availability-zones-service-support)」をご覧ください。

**オブジェクトのプロビジョニング** - Microsoft Entra Domain Services は、Microsoft Entra Domain Services がデプロイされているサブスクリプションに関連付けられている Microsoft Entra ID から ID を同期します。 また、関連付けられている Microsoft Entra ID に Microsoft Entra Connect との同期が設定されている場合 (ユーザー フォレスト シナリオ)、これらの ID のライフサイクルも Microsoft Entra Domain Services に反映される可能性があることにも注目してください。 このサービスには、Microsoft Entra ID からユーザー オブジェクトとグループ オブジェクトをプロビジョニングするために使用できる 2 つのモードがあります。

- **すべて**: すべてのユーザーとグループは、Microsoft Entra ID から Microsoft Entra Domain Services に同期されます。
- **スコープ付き:** グループのスコープ内のユーザーのみが Microsoft Entra ID から Microsoft Entra Domain Services に同期されます。

Microsoft Entra Domain Services を初めてデプロイするとき、Microsoft Entra ID からオブジェクトをレプリケートするための一方向の自動同期が構成されます。 この一方向の同期は引き続きバックグラウンドで実行され、Microsoft Entra ID からの変更を反映して Microsoft Entra Domain Services マネージド ドメインを最新の状態に保ちます。 Microsoft Entra Domain Services から Microsoft Entra ID への同期は行われません。 詳細については、「[Microsoft Entra Domain Services のマネージド ドメイン内でのオブジェクトと資格情報の同期のしくみ](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/synchronization)」を参照してください。

同期の種類を [すべて] から [スコープ付き] (またはその逆) に変更する必要がある場合は、Microsoft Entra Domain Services マネージド ドメインを削除、再作成、構成する必要があることに注意してください。 さらに組織では適切な慣行として、Microsoft Entra Domain Services リソースへのアクセスが必要なもののみに ID を減らすために、"スコープ付き" プロビジョニングの使用を検討する必要があります。

**グループ ポリシー オブジェクト (GPO)** - Microsoft Entra Domain Services マネージド ドメインで GPO を構成するには、Microsoft Entra Domain Services マネージド ドメインにドメイン参加しているサーバー上のグループ ポリシー管理ツールを使用する必要があります。 詳細については、「[Microsoft Entra Domain Services のマネージド ドメインでグループ ポリシーを管理する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/manage-group-policy)」を参照してください。

**Secure LDAP** - Microsoft Entra Domain Services は、それを必要とするアプリケーションで使用できるセキュリティで保護された LDAP サービスを提供します。 この設定は既定で無効になっており、セキュリティで保護された LDAP を有効にするには、証明書をアップロードする必要があります。さらに、Microsoft Entra Domain Services がデプロイされている VNet をセキュリティで保護する NSG では、Microsoft Entra Domain Services マネージド ドメインへのポート 636 接続が許可される必要があります。 詳細については、[Microsoft Entra Domain Services のマネージド ドメインに対するセキュリティで保護された LDAP の構成](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-ldaps)に関する記事を参照してください。

**管理** - Microsoft Entra Domain Services で管理作業を実行するには (ドメイン参加マシンや GPO の編集など)、このタスクに使用されるアカウントが Microsoft Entra DC 管理者グループの一部である必要があります。 このグループのメンバーであるアカウントは、管理タスクを実行するためにドメイン コントローラーに直接サインインすることはできません。 代わりに、Microsoft Entra Domain Services マネージド ドメインに参加している管理 VM を作成してから、通常の AD DS 管理ツールをインストールします。 詳細については、[Microsoft Entra Domain Services でのユーザー アカウント、パスワード、および管理の管理の概念](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/administration-concepts)に関する記事を参照してください。

**パスワード ハッシュ** - Microsoft Entra Domain Services での認証を機能させるには、すべてのユーザーのパスワード ハッシュが NT LAN Manager (NTLM) および Kerberos 認証に適した形式である必要があります。 Microsoft Entra Domain Services での認証が期待どおりに動作するようにするには、次の前提条件を実行する必要があります。

- **Microsoft Entra Connect と同期されたユーザー (AD DS から)** - 従来のパスワード ハッシュを、オンプレミスの AD DS から Microsoft Entra ID に同期する必要があります。
- **Microsoft Entra ID で作成されたユーザー** - Microsoft Entra Domain Services で使用するために正しいハッシュが生成されるようにパスワードをリセットする必要があります。 詳細については、「[パスワード ハッシュの同期を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-configure-password-hash-sync)」を参照してください。

**ネットワーク** - Microsoft Entra Domain Services は Azure VNet にデプロイされるため、サーバーとアプリケーションがセキュリティで保護され、マネージド ドメインに正しくアクセスできるように考慮する必要があります。 詳細については、[Microsoft Entra Domain Services の仮想ネットワーク設計の考慮事項と構成オプション](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/network-considerations)に関する記事を参照してください。

- Microsoft Entra Domain Services を独自のサブネットにデプロイする必要がある: 既存のサブネットやゲートウェイ サブネットは使用しないでください。
- **ネットワーク セキュリティ グループ (NSG)** - Microsoft Entra Domain Services マネージド ドメインのデプロイ時に作成されます。 このネットワーク セキュリティ グループには、サービス通信を正しく行うために必要な規則が含まれています。 独自のカスタム規則を持つ既存のネットワーク セキュリティ グループを作成または使用しないでください。
- **Microsoft Entra Domain Services には 3 から 5 個の IP アドレスが必要** - サブネットの IP アドレス範囲でこの数のアドレスを提供できることを確認してください。 使用可能な IP アドレスを制限すると、Microsoft Entra Domain Services で 2 つのドメイン コントローラーを維持できなくなる可能性があります。
- **VNet DNS サーバー** - "ハブとスポーク" モデルについて既に説明したように、Microsoft Entra Domain Services マネージド ドメインに参加しているサーバーが Microsoft Entra Domain Services マネージド ドメインを解決するための正しい DNS 設定を持っているようにするために、VNet で DNS を正しく構成することが重要です。 各 VNet には、IP アドレスを取得する際にサーバーに渡される DNS サーバー エントリがあり、これらの DNS エントリは Microsoft Entra Domain Services マネージド ドメインの IP アドレスである必要があります。 詳しくは、「[Azure 仮想ネットワークの DNS 設定を更新する](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-instance)」をご覧ください。

**課題**: 以下の一覧では、ID の分離にこのオプションを使用する際の主な課題を示します。

- 一部の Microsoft Entra Domain Services 構成は、Microsoft Entra Domain Services 参加済みサーバーからのみ管理できます。
- Microsoft Entra テナントごとに展開できる Microsoft Entra Domain Services マネージド ドメインは 1 つだけです。 このセクションで説明するように、他の VNet 上のサービスに Microsoft Entra Domain Services 認証を提供するために、ハブ アンド スポーク モデルをお勧めします。
- 修正プログラムの適用とソフトウェアのデプロイを管理するために、追加のインフラストラクチャが必要になる場合があります。 組織では、これらのサーバーを管理するために、Azure Update Management、グループ ポリシー (GPO) または System Center Configuration Manager (SCCM) のデプロイを検討する必要があります。

この分離モデルでは、顧客の企業ネットワークから Microsoft Entra Domain Services マネージド ドメインをホストする VNet への接続がなく、他のフォレストとの信頼が構成されていないことが前提になります。 Microsoft Entra Domain Services を管理および運用できるポイントを許可するには、ジャンプボックスまたは管理サーバーを作成する必要があります。

#### Microsoft Entra 認証を使用して Azure の仮想マシンにサインインする

IaaS ワークロードを Azure にデプロイするための要件が存在し、それによって ID の分離が必要な場合、最後のオプションは、このシナリオのサーバーへのログオンに Microsoft Entra ID を使用することです。 これにより、Microsoft Entra ID を認証用の ID 領域にすることができ、必要な Microsoft Entra テナントにリンクされている、関連するサブスクリプションにサーバーをプロビジョニングすることで、ID の分離を実現できます。 次の事項を考慮する必要があります。

[Image: Azure VM に対する Microsoft Entra 認証を示す図。]

**サポートされているオペレーティング システム**: Microsoft Entra 認証を使用して Azure の仮想マシンにサインインすることは、Windows と Linux で現在サポートされています。 サポートされているオペレーティング システムの詳細については、[Windows](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-windows) と [Linux](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-linux) のドキュメントを参照してください。

**資格情報**: Microsoft Entra 認証を使用して Azure 内の仮想マシンにサインインする主な利点の 1 つは、仮想マシンへのサインインのために Microsoft Entra サービスへのアクセスに通常使用するのと同じフェデレーションまたはマネージド Microsoft Entra 資格情報を使用できることです。

注

このシナリオでのサインインに使用される Microsoft Entra テナントは、仮想マシンがプロビジョニングされたサブスクリプションに関連付けられている Microsoft Entra テナントです。 この Microsoft Entra テナントは、オンプレミスの AD DS から同期された ID を持つものとすることができます。 組織では、これらのサーバーへのサインインに使用するサブスクリプションと Microsoft Entra テナントを選択する際に、自らの分離原則と整合の取れた、十分な情報を得たうえでの選択を行う必要があります。

**ネットワーク要件**: これらの仮想マシンは認証のために Microsoft Entra ID にアクセスする必要があるため、仮想マシンのネットワーク構成では Microsoft Entra エンドポイントへの送信アクセスが 443 で許可されるようにする必要があります。 詳細については、[Windows](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-windows) と [Linux](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-linux) のドキュメントを参照してください。

**ロールベースのアクセス制御 (RBAC)**: これらの仮想マシンへの適切なレベルのアクセスを提供するために、2 つの RBAC ロールを使用できます。 これらの RBAC ロールは、Azure portal または Azure Cloud Shell エクスペリエンスを使用して構成できます。 詳細については、「[仮想マシン ロールの割り当てを構成する](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-windows)」を参照してください。

- **仮想マシンの管理者ログオン**: このロールを割り当てられたユーザーは、管理者特権を使用して Azure 仮想マシンにログインできます。
- **仮想マシンのユーザー ログオン**: このロールを割り当てられたユーザーは、通常のユーザー特権を使用して Azure 仮想マシンにログインできます。

条件付きアクセス: Azure 仮想マシンへのサインインに Microsoft Entra ID を使用する主な利点は、サインイン プロセスの一環として条件付きアクセスを適用できることです。 これにより組織は、仮想マシンへのアクセスを許可する前に条件を満たすことを要求し、多要素認証を使用して強力な認証を提供することができます。 詳細については、「[条件付きアクセスの使用](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-windows)」を参照してください。

注

Microsoft Entra ID に参加している仮想マシンへのリモート接続は、Windows 10、Windows 11、および Cloud PC のいずれかからのみ許可されます。ただし、これらの PC においても、仮想マシンと同じディレクトリに Microsoft Entra 参加済みまたは Microsoft Entra ハイブリッド参加済みである必要があります。

**課題**: 以下の一覧では、ID の分離にこのオプションを使用する際の主な課題を示します。

- サーバーの中央からの管理または構成はありません。 たとえば、サーバーのグループに適用できるグループ ポリシーはありません。 組織では、これらのサーバーの修正プログラムと更新プログラムを管理するために、[Azure の Update Management](https://learn.microsoft.com/ja-jp/azure/automation/update-management/overview) をデプロイすることを検討する必要があります。
- これらのサーバーまたはサービス全体で、Windows 統合認証などのオンプレミスのメカニズムで認証する要件がある多層アプリケーションには適していません。 これが組織の要件である場合は、スタンドアロンの Active Directory Domain Services、またはこのセクションで説明する Microsoft Entra Domain Services シナリオを確認することをお勧めします。

この分離モデルでは、顧客の企業ネットワークから、仮想マシンをホストする VNet への接続がないことを前提としています。 これらのサーバーを管理および運用できるポイントを許可するには、ジャンプボックスまたは管理サーバーを作成する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/secure-service-accounts"} -->
## Microsoft Entra サービス アカウントのセキュリティ保護に関する概要 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/secure-service-accounts
- Service: entra / architecture
- Article date: 2022-08-26
- Summary: Microsoft Entra ID で使用できるサービス アカウントの種類について説明します。

Microsoft Entra ID のネイティブなサービス アカウントには、マネージド ID、サービス プリンシパル、ユーザーベースのサービス アカウントの 3 種類があります。 サービス アカウントは、アプリケーション、API、その他のサービスなどの人間以外のエンティティを表すことを目的とした特別な種類のアカウントです。 これらのエンティティは、サービス アカウントによって提供されるセキュリティ コンテキスト内で動作します。

### Microsoft Entra サービス アカウントの種類

Azure でホストされるサービスでは、可能であればマネージド ID を使用し、そうでない場合はサービス プリンシパルを使用することをお勧めします。 マネージド ID は、Azure の外部でホストされているサービスには使用できません。 その場合は、サービス プリンシパルをお勧めします。 マネージド ID またはサービス プリンシパルを使用できる場合は、それらを使用してください。 Microsoft Entra ユーザー アカウントをサービス アカウントとして使用することはお勧めしません。 概要については、次の表を参照してください。

| サービス ホスティング | マネージド ID | サービス プリンシパル | Azure ユーザー アカウント |
| --- | --- | --- | --- |
| サービスは Azure でホストされている。 | はい。 サービスでマネージド ID がサポートされる場合に推奨。 | はい。 | お勧めしません。 |
| サービスは Azure でホストされていない。 | いいえ | はい。 推奨。 | お勧めしません。 |
| サービスはマルチテナントである | いいえ | はい。 推奨。 | いいえ。 |

### 管理されたアイデンティティー

マネージド ID は、Azure リソースに ID を提供するために作成されるセキュアな Microsoft Entra ID です。 [マネージド ID には次の 2 種類](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)があります。

- システムによって割り当てられたマネージド ID は、サービスのインスタンスに直接割り当てることができます。
- ユーザーが割り当てたマネージド ID は、スタンドアロン リソースとして作成できます。

詳細については、[マネージド ID のセキュリティ保護](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-managed-identities)に関するページを参照してください。 マネージド ID の一般的な情報については、「[Azure リソースのマネージド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)」を参照してください。

### サービス プリンシパル

アプリケーションを表すためにマネージド ID を使用できない場合は、サービス プリンシパルを使用します。 サービス プリンシパルは、シングル テナント アプリケーションとマルチテナント アプリケーションの両方で使用できます。

サービス プリンシパルは、単一の Microsoft Entra テナント内のアプリケーション オブジェクトのローカル表現です。 アプリケーション インスタンスの ID として機能し、アプリケーションにアクセスできるユーザーと、アプリケーションでアクセスできるリソースが定義されます。 サービス プリンシパルは、アプリケーションが使用される各テナントで (ローカルに) 作成され、グローバルに一意なアプリケーション オブジェクトが参照されます。 テナントによって、サービス プリンシパルのサインインとリソースへのアクセスがセキュリティで保護されます。

サービス プリンシパルを使用した認証には、2 つのメカニズム (クライアント証明書とクライアント シークレット) があります。 証明書のほうがより安全です。可能な場合は、クライアント証明書を使用してください。 クライアント シークレットとは異なり、クライアント証明書が誤ってコードに埋め込まれることはありません。

サービス プリンシパルのセキュリティ保護の詳細については、[サービス プリンシパルのセキュリティ保護](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-principal)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/secure-single-tenant"} -->
## Microsoft Entra ID のシングル テナントでの安全なリソースの分離 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/secure-single-tenant
- Service: entra / architecture
- Article date: 2024-10-08
- Summary: Microsoft Entra ID のシングル テナントでのリソース分離の概要。

多くの分離シナリオがシングル テナントで実現できます。 可能であれば、最高の生産性とコラボレーション エクスペリエンスを得るために、管理をシングル テナント内の個別の環境に委任することをお勧めします。

### 結果

**リソースの分離** - ユーザー、グループ、およびサービス プリンシパルに対しリソース アクセスを制限するには、Microsoft Entra ディレクトリ ロール、セキュリティ グループ、条件付きアクセス ポリシー、Azure リソース グループ、Azure 管理グループ、管理単位 (AU)、その他の制御を使用します。 個別の管理者がリソースを管理できるようにします。 個別のユーザー、アクセス許可、およびアクセス要件を使用します。

次の場合は、複数のテナントで分離を行います。

- テナント全体の設定を必要とするリソース セット
- テナント メンバーによる未承認のアクセスに対するリスク許容度が最小限
- 構成の変更によって望ましくない影響が発生する

**構成の分離** - アプリケーションなどのリソースが、認証方法やネームド ロケーションなどのテナント全体の構成への依存関係をもっている場合があります。 リソースを分離するときには、依存関係を考慮してください。 全体管理者は、リソースに影響を与えるリソース設定およびテナント全体の設定を構成できます。

一連のリソースで固有のテナント全体の設定が必要な場合、または別のエンティティがテナントの設定を管理している場合は、マルチ テナントで分離を行ってください。

**管理の分離** - Microsoft Entra ID の委任された管理を使用して、アプリケーションおよび API、ユーザーおよびグループ、リソース グループ、条件付きアクセス ポリシーなどのリソースの管理を分離します。

全体管理者は、信頼できるリソースを検出して、そのリソースへのアクセス権を取得できます。 リソースに対する認証された管理者の変更の監査とアラートを設定してください。

Microsoft Entra ID で管理単位 (AU) を使用して管理の分離を行ってください。 組織の管理単位 (AUs) では、ロールのアクセス許可を組織内の特定の部分に制限します。 [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)ロールを地域のサポート スペシャリストに委任するには、AU を使用します。 すると、サポート スペシャリストはサポートしているリージョン内のユーザーを管理できます。

[Image: 管理単位の図。]

[ユーザー、グループ、およびデバイス オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units)を分離するには、AU を使用します。 [動的メンバーシップ グループのルール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-members-dynamic)を使用して、ユニットを割り当ててください。

Privileged Identity Management (PIM) を使用して、より高い特権ロールの要求を承認するユーザーを選択してください。 たとえば、ユーザーの認証方法を変更するために認証管理者アクセスを必要とする管理者を選択します。

注

PIM を使用するには、ユーザーごとに Microsoft Entra ID P2 ライセンスが必要です。

認証管理者がリソースを管理できないようにするには、別の認証管理者により、別のテナントにリソースを分離します。 バックアップにはこの方法を使用してください。 例については、[マルチユーザー承認ガイダンス](https://learn.microsoft.com/ja-jp/azure/backup/multi-user-authorization)をご覧ください。

### 一般的な使用法

シングル テナントでの複数の環境の一般的な用法は、実稼働リソースを非実稼働リソースから分離することです。 テナントで、開発チームとアプリケーション所有者は、テスト アプリ、テスト ユーザーおよびグループ、それらのオブジェクトのテスト ポリシーを備えた別の環境を作成して管理します。 同様に、チームは Azure リソースと信頼されたアプリの非実稼働インスタンスを作成します。

非実稼働 Azure リソース、および、同等の非実稼働ディレクトリ オブジェクトを持つ Microsoft Entra 統合アプリケーションの非実稼働インスタンスを使用してください。 ディレクトリ内の非実稼働リソースはテスト用です。

注

Microsoft Entra テナントに複数の Microsoft 365 環境を作成しないでください。 ただし、Microsoft Entra テナントに複数の Dynamics 365 環境は作成できます。

シングル テナントで分離するもう 1 つのシナリオは、場所、子会社、または階層化された管理での分離です。 「[エンタープライズ アクセス モデル](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-access-model)」をご覧ください。

Azure リソースのスコープ管理には、Azure ロールベースのアクセス制御 (Azure RBAC) の割り当てを使用します。 同様に、複数の機能により、Microsoft Entra ID を信頼するアプリケーションの Microsoft Entra ID 管理を有効にします。 例としては、条件付きアクセス、ユーザーとグループのフィルター処理、管理単位の割り当て、アプリケーションの割り当てなどがあります。

Microsoft 365 サービスの分離 (組織レベルの構成のステージングを含む) を行うには、[マルチ テナントの分離](https://learn.microsoft.com/ja-jp/azure/backup/multi-user-authorization)を選択します。

#### Azure リソースのスコープを設定した管理

Azure RBAC を使用して、細分化されたスコープと表面領域を持つ管理モデルを設計します。 次の例の管理階層について考えてみます。

注

組織の要件、制約、目標に基づいて管理階層を定義できます。 詳細については、[Azure リソースの整理](https://learn.microsoft.com/ja-jp/azure/cloud-adoption-framework/ready/azure-setup-guide/organize-resources)に関する「クラウド導入フレームワーク ガイダンス」をご覧ください。

[Image: テナントでのリソースの分離を示すダイアグラム。]

- **管理グループ** - 他の管理グループに影響を与えないように、特定の管理グループにロールを割り当てます。 上記のシナリオでは、HR チームは、Azure Policy を定義して、リソースがすべての HR サブスクリプションにデプロイされている領域を監査できます。
- **サブスクリプション** - 特定のサブスクリプションにロールを割り当てて、他のリソース グループに影響を与えないようにします。 上記の例では、HR チームは、他の HR サブスクリプションや他のチームのサブスクリプションを読み取ることなく、福利厚生サブスクリプションの閲覧者ロールを割り当てることができます。
- **リソース グループ** - ロールを特定のリソース グループに割り当てて、他のリソース グループに影響を与えないようにすることができます。 福利厚生エンジニアリング チームは共同作成者ロールを誰かに割り当てて、テスト データベースやテスト Web アプリを管理したり、リソースを追加したりできるようにします。
- **個々のリソース** - ロールを特定のリソースに割り当てて、他のリソースに影響を与えないようにすることができます。 福利厚生エンジニアリング チームは、データ アナリストに、Azure Cosmos DB データベースのテスト インスタンスの Cosmos DB アカウント閲覧者ロールを割り当てます。 この作業は、テスト Web アプリや運用リソースに干渉しません。

詳細については、「[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles)」と「[Azure RBAC とは何か](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/overview)」をご覧ください。

構造は階層型です。 したがって、階層が高いほど、スコープ、可視性、および下位レベルへの影響が広くなります。 最上位レベルのスコープは、Microsoft Entra テナント境界内の Azure リソースに影響します。 複数のレベルでアクセス許可を適用できます。 このアクションにより、リスクが生じます。 階層の上位のロールを割り当てると、階層の下位では意図したよりも多くのアクセスが提供される可能性があります。 [Microsoft Entra](https://www.microsoft.com/security/business/identity-access/microsoft-entra-permissions-management) では、リスクの軽減に役立つ可視性と修復が提供されます。

- ルート管理グループでは、サブスクリプションとリソースに適用される Azure ポリシーと RBAC ロールの割り当てを定義します。
- グローバル管理者は、サブスクリプションと管理グループについて[アクセス権を昇格させる](https://aka.ms/AzureADSecuredAzure/12a)ことができます。

最上位レベルのスコープを監視します。 ネットワークなど、リソース分離の他のディメンションを計画することが重要です。 Azure ネットワークに関するガイダンスについては、「[ネットワーク セキュリティに関する Azure のベスト プラクティス](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/network-best-practices)」をご覧ください。 サービスとしてのインフラストラクチャ (IaaS) ワークロードには、ID とリソースの分離を設計と戦略の一部とする必要があるシナリオがあります。

「[Azure ランディング ゾーンの概念アーキテクチャ](https://learn.microsoft.com/ja-jp/azure/cloud-adoption-framework/ready/landing-zone/)」に従って、機密性の高いリソースやテスト リソースを分離することを検討してください。 たとえば、分離された管理グループに ID サブスクリプションを割り当てます。 サンドボックス管理グループでの開発用の個別のサブスクリプション。 詳細については、「[エンタープライズ スケールのドキュメント](https://learn.microsoft.com/ja-jp/azure/cloud-adoption-framework/ready/enterprise-scale/faq)」をご覧ください。 テナント内でのテスト目的の分離については、「[参照アーキテクチャの管理グループ階層](https://learn.microsoft.com/ja-jp/azure/cloud-adoption-framework/ready/enterprise-scale/testing-approach)」で考慮されています。

#### Microsoft Entra ID 信頼アプリケーションのスコープ管理

次のセクションでは、Microsoft Entra ID 信頼アプリケーションのスコープ管理のパターンについて概説します。

Microsoft Entra ID では、カスタム アプリと SaaS アプリの複数のインスタンスの構成がサポートされていますが、[独立したユーザー割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)がある同じディレクトリに対して、ほとんどの Microsoft サービスはサポートされていません。 上記の例には、旅行アプリの実稼働バージョンとテスト バージョンの両方が含まれています。 アプリ固有の構成とポリシーの分離を実現するには、企業テナントに対して実稼働前バージョンをデプロイします。 このアクションにより、ワークロード所有者は会社の資格情報を使用してテストを実行できます。 テスト ユーザーやテスト グループなどの非実稼働ディレクトリ オブジェクトは、それらのオブジェクトの個別の[所有権](https://aka.ms/AzureADSecuredAzure/14a)を持つ非実稼働アプリケーションに関連付けられます。

Microsoft Entra テナント境界内の信頼アプリケーションに影響を与えるテナント全体の側面は次のとおりです。

- グローバル管理者は、テナント全体の設定をすべて管理します
- ユーザー管理者、アプリケーション管理者、条件付きアクセス管理者など、他の[ディレクトリ ロール](https://aka.ms/AzureADSecuredAzure/14b)では、そのロールのスコープ内でテナント全体の構成を管理します。

認証方法、ハイブリッド構成、B2B Collaboration、ドメインの許可リスト、ネームド ロケーションなどの構成設定はテナント全体にわたるものです。

注

Microsoft Graph API のアクセス許可と同意のアクセス許可を 1 グループまたは AU メンバーにスコープすることはできません。 これらのアクセス許可は、ディレクトリ レベルで割り当てられます。 リソース レベルのスコープを許可できるのはリソース固有の同意のみで、現在は、[Microsoft Teams チャットのアクセス許可](https://learn.microsoft.com/ja-jp/microsoftteams/platform/graph-api/rsc/resource-specific-consent)に制限されています。

重要

Office 365、Microsoft Dynamics、Microsoft Exchange などの Microsoft SaaS サービスのライフサイクルは、Microsoft Entra テナントにバインドされます。 その結果、これらのサービスの複数のインスタンスでは、複数の Microsoft Entra テナントが必要になります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/security-copilot-entra-proof-of-concept"} -->
## Microsoft Entra の Microsoft Security Copilot 概念実証ガイド - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/security-copilot-entra-proof-of-concept
- Service: entra
- Article date: 2026-03-19
- Summary: AI を利用した分析情報と自動化を活用し、環境内の Entra で Security Copilot の価値を実証する方法について説明します。

Microsoft Entra の Microsoft Security Copilot (Entra のセキュリティ コピロット) 概念実証 (PoC) ガイドを使用して、人工知能 (AI) を利用した分析情報と自動化を活用します。 定義済みのシナリオを使用して、環境内の Entra の Security Copilot の価値を示します。 このガイドでは、テストと価値の実現を加速するための構造化されたアプローチを示します。

Important

このガイドでは、組織が必要なユーザー データを含む環境で PoC を実行していることを前提としています。

注

Entra のセキュリティ コピロットは現在、米国政府のクラウド **ではなく** 、商用クラウド テナントをサポートしています。

### 製品を理解する

Entra での Security Copilot の主要な概念を理解することは、PoC を成功させる最初のステップです。 このセクションの製品機能のラーニング パスから始めます。

- [Security Copilot の使用を開始する](https://learn.microsoft.com/ja-jp/copilot/security/get-started-security-copilot)
- [セキュリティ コピロット用に環境を準備する](https://learn.microsoft.com/ja-jp/security/zero-trust/copilots/zero-trust-microsoft-copilot-for-security)
- [セキュリティコパイロットの価格](https://aka.ms/CopilotforSecurity_Pricing)
- [Security Copilotに関するよくある質問](https://learn.microsoft.com/ja-jp/copilot/security/faq-security-copilot)

### PoC の前提条件

Entra PoC でこのセキュリティ コピロットを実施するには、次の前提条件が満たされていることを確認します。

- Microsoft Entra ID P1、P2、または試用版ライセンスを持つ有効な Microsoft Entra テナント
    - [無料でアカウントを作成する](https://signup.azure.com/)
- Microsoft Entra Suite のシナリオには、その他のライセンスが必要です
- PoC を有効にするには、クラウド ユーザーに次のいずれかのロールを割り当てます。
    - グローバル管理者
    - セキュリティ管理者
    - 請求管理者

エージェント固有の前提条件の詳細については、 [条件付きアクセスの最適化エージェントのドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization)。

### シナリオの概要

Entra の Security Copilot は、セキュリティ チームが Microsoft Entra のさまざまな ID およびアクセス管理の課題に対処できるようにします。 一般的なシナリオについては、次の一覧を参照してください。

- 危険なユーザーとサインイン動作を検出して分析する
- 疑わしいアクティビティの監査ログとサインイン ログを調査する
- アクセス レビューとライセンスの使用状況を監視する
- セキュリティに関する推奨事項とコンプライアンス体制を確認する
- ライフサイクル ワークフローとグループ管理を効率化する
- 環境全体でユーザー情報とアプリのリスクを評価する

次の表に、主要なシナリオと、Entra の Security Copilot が各シナリオにもたらす機能を示します。これにより、より迅速な調査が可能になり、可視性が向上します。

| シナリオ | 能力 |
| --- | --- |
| Microsoft Entra ID | [テナント](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios)[ユーザー](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios)[グループ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios)[ドメイン](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios)[ライセンス](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios)[サインイン ログ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios)[監査ログ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios)[プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios)[推奨事項](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios)[正常性監視アラート](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios)[サービス レベル合意](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios)[ロールと管理者](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios)[デバイス](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios)[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios)[認証](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios) |
| Microsoft Entra ID Protection（マイクロソフト エントラ ID 保護） | [危険なユーザー](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-protection-scenarios)[アプリケーション リスク](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-protection-scenarios) |
| Microsoft Entra ID ガバナンス | [アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-governance-scenarios)[エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-governance-scenarios)[Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-governance-scenarios)[PIM 書き込みアクセス](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-governance-scenarios)[ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-governance-scenarios) |
| Microsoft Entra Internet AccessMicrosoft Entra Private Access | [グローバルなセキュリティで保護されたアクセス](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-internet-access-private-access-scenarios) |

注

エントラの Security Copilot では、OAuth 2.0 によって提供されるオン・ビハーフ・オブ (OBO) 認証が使用されており、これは OAuth の委任に基づく認証フローです。 Security Operations ユーザーがプロンプトを発行すると、Entra の Security Copilot は要求チェーンを介してユーザー ID とアクセス許可を渡します。 このアクションにより、ユーザーがアクセス権を持つべきではないリソースに対するアクセス許可をユーザーが取得できなくなります。 OBO 認証の詳細については、 [Microsoft ID プラットフォームと OAuth2.0 の代理フローを](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)参照してください。

### Entra でセキュリティ コピロットを設定する

Entra の Security Copilot は、Microsoft 365 E5 サブスクリプションに含まれています。 E5 のお客様は、ゼロクリックによるアクティブ化を受け取ります。つまり、Entra の Security Copilot はプロビジョニングされ、有効にすると使用できる状態になります。 これ以外の操作は必要ありません。

サブスクリプションに Entra に Security Copilot が含まれていない場合は、 [Security Copilot をオンボード](https://learn.microsoft.com/ja-jp/copilot/security/get-started-security-copilot)する手順に進みます。 E5 のお客様でない場合は、少なくとも 1 つのセキュリティ コンピューティング ユニット (SCU) が必要です。 15 から 20 個の SKU をお勧めします。

#### ユース ケースを特定する

Entra PoC のセキュリティ コピロットは、組織固有のセキュリティの課題と目標から始まります。 [Microsoft Entra 管理センター](https://entra.microsoft.com/)で、**Copilot** を選択します。 開始するためのプロンプトが表示されます。 これらのプロンプトを調べるか、チャット ボックスにプロンプトを入力できます。 結果の派生に使用されるグラフ クエリを表示できます。

[Image: Microsoft Entra 管理センターのスクリーンショット。]

[Image: Copilot トピック履歴のスクリーンショット。]

#### ペルソナ機能

次の表に、ペルソナ関数に基づくプロンプトを示します。

| ペルソナ | シナリオ | 推奨プロンプト |
| --- | --- | --- |
| ヘルプデスク管理者 | リスク イベントが原因でサインインがブロックされたユーザーを調査します。 この問題を解決するために、ヘルプ デスクはユーザーと共に通話中です。 | リスクが高いためにブロックされたユーザーの電子メール **julie-b@contoso** を調査しています。 このユーザーがブロックされている理由を理解するのに役立ちます。 また、過去 24 時間以内に、同じ状況が原因で他のユーザーがブロックされているかどうかをお知らせください。 |
| セキュリティ オペレーション センター (SOC) チーム メンバー | 国/地域または部門からのパスワード リセットの急増を調査する: 傾向やパターンを決定します。 | 複数のユーザーにとってリスクが高い IP アドレスからの傾向が見受けられます。 米国内のすべてのユーザー アプリケーションを確認して、アプリケーション、サービス、またはグループのパターンがあるかどうかを判断します。 |
| アイデンティティ管理者 | 新しい条件付きアクセス ポリシーの潜在的な影響を調査する | サインインと適用される条件付きアクセス ポリシーを表示します。 登録済みの多要素認証 (MFA) なしでユーザーを一覧表示します。 過去 14 日間のアンマネージド デバイスからのサインイン ログを表示する |
| テナント管理者 | ゲスト管理 | テナントのゲスト ユーザーを表示する |

### PoC シナリオ: Microsoft Entra ID

次のシナリオは、ユーザー認証、多要素認証 (MFA)、監査ログ調査に関連しています。

#### ユーザー認証と多要素認証

**目標**: トラブルシューティングを高速化し、手動ログ分析を減らす。 Microsoft Entra の Security Copilot チャット エクスペリエンスを使用して、ユーザーのサインインエラーと MFA カバレッジを調査します。

**実行**: Entra で Security Copilot を使用して、認証の動作を調査し、一般的なサインインエラーの理由を特定し、MFA 登録なしでユーザーを検出します。

1. Entra で Security Copilot にアクセスするには、 [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **サインイン ログ**に移動し、**Copilot プロンプト バー**を開きます。
3. サインイン失敗パターンを特定します。
4. テナントのエラーを理解するには、次のプロンプトを実行します。
    - *過去 24 時間のサインイン エラーの上位 5 つの理由は何ですか?*
5. Entra の Security Copilot は、エラーの理由、影響を受けるユーザー、および条件付きアクセスなどの関連条件を返します。
6. RequestID を使用してイベントを調査します。
7. 結果からイベントを選択します。
    - *要求 ID &lt;RequestID の詳細を教えてください&gt;*
8. 応答を使用して、障害の原因、認証方法、デバイスの状態、適用されたポリシーを理解します。
9. デバイス コンプライアンスの効果を評価する (オプションのブランチ)。 デバイスの状態が関連する場合は、次のプロンプトを使用します。
    - *過去 24 時間以内に非準拠デバイスでサインインしたユーザーはどれですか?*
10. 返された RequestID を確認して、特定のサインインを検証します。
11. RequestID を使用して、手動のログ フィルター処理を行わずにイベント コンテキストを取得します。
    - *リクエスト ID &lt;RequestID のサインイン詳細を表示&gt;*
12. テナント内の MFA ギャップを特定します。
13. MFA 保護なしでユーザーを識別します。
    - *テナント内のどのユーザーが MFA に登録されていないのですか?*
14. 特定のユーザーの認証方法を検査して、対象を絞った調査を行います。

**成功条件**

| ディメンション | 成功基準 |
| --- | --- |
| Efficiency | サインイン エラーの調査時間を、手動によるポータルナビゲーションやフィルター処理に比べて50%以上短縮します。 |
| 対象範囲 | Entra の対話におけるセキュリティ コパイロットで、サインイン失敗の主な理由、影響を受けたユーザー、および関連する条件付きアクセス ポリシーを特定します。 |
| アクション可能性 | アナリストは、ログのエクスポートや Kusto クエリ言語 (KQL) の記述を行わずに、概要結果から要求レベルの詳細、要求 ID に移動できます。 |
| 分析情報の品質 | Entra の Security Copilot は、自然言語でのエラーの理由を提供し、検証のために基になる Microsoft Graph クエリを公開します。 |
| セキュリティ態勢 | MFA 登録のないユーザーを特定して、修復またはポリシーの適用に役立ちます。 |

#### 監査ログの調査

**目標**: Microsoft Entra の Security Copilot チャット エクスペリエンスを使用して監査ログを調査し、トラブルシューティングの高速化、変更の検出、手動ログ分析の削減を行います。

**実行**: このシナリオでは、Entra の Security Copilot を使用して、監査調査で変更を検討します。

1. Entra で Security Copilot にアクセスするには、 [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **監査ログ**に移動します。
3. **Copilot プロンプト バーを開きます**。
4. テナントの条件付きアクセス ポリシーに対して、次のプロンプトを実行します。
    - *過去 24 時間以内に新しい条件付きアクセス ポリシーが作成されましたか?*
5. 条件付きアクセス ポリシーの変更を調査します。
    - *テナントで最近変更された条件付きアクセス ポリシーを表示します。*
6. Entra の Security Copilot は、変更された条件付きアクセス ポリシーの一覧を返します。
7. 変更されたプロパティと、変更者を確認します。
8. 誰がそれらをエクスポートしたかを確認するために監査ログを参照します。
    - *過去 24 時間のエクスポート アクティビティの監査ログを表示します。*
9. Entra の Security Copilot は、一覧表示された期間のログ エクスポートの一覧を返します。
10. サービス プリンシパルに対する管理者の可視性を決定します。
    - *テナント内のすべてのサービス プリンシパルを一覧表示します。*

**成功条件**

| ディメンション | 成功基準 |
| --- | --- |
| Efficiency | 手動でポータルを操作したり、ログをエクスポートしたり、イベントを個別にフィルターしたりする場合に比べて、監査変更の調査時間を50%以上短縮します。 |
| 対象範囲 | Entra のセキュリティ コピロットとの対話内で、新しい条件付きアクセス ポリシー、変更、エクスポート、サービス プリンシパルの変更など、関連する監査アクティビティを特定します。 |
| アクション可能性 | アナリストは、ポリシーの変更やエクスポートなどの概要から、KQL を記述したり画面を切り替えたりすることなく、イベント レベルの詳細に移動できます。 |
| 分析情報の品質 | Entra の Security Copilot では、変更に関する自然言語の説明が提供され、さらに、誰がいつ変更を開始したかというコンテキストも含まれます。 |
| セキュリティ態勢 | 未承認のエクスポートや未所有のサービス プリンシパルなど、潜在的にリスクの高い、または予期しない管理上の変更を検出して、修復やエスカレーションに役立てます。 |

[Microsoft Entra ID のシナリオ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-scenarios)の詳細について説明します。

### PoC シナリオ: Microsoft Entra ID Protection

次のセクションでは、危険な動作、サインイン試行など、ユーザーのリスク プロファイルについて説明します。

#### 危険なユーザーの概要

**目標**: Microsoft Entra の Security Copilot 要約機能を使用して、危険なユーザーを調査します。 ユーザー インターフェイスを手動で移動することなく、効率的なトリアージと修復パスを有効にします。

**実行**: このシナリオでは、Entra の Security Copilot を使用して、ユーザーのリスク プロファイルを要約し、リスクのフラグが設定されている理由を理解し、潜在的な脅威を軽減するためのアクションを特定します。

1. Entra で Security Copilot にアクセスするには、 [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. [ **保護**] に移動します。
3. **危険なユーザーを選択します**。
4. 一覧からユーザーを選択します。
5. Copilot Summary エクスペリエンスを使用して、リスクの概要を生成します。
6. リスクの概要を生成するには、 **Copilot の概要**で次のプロンプトを実行します。
    - *検出の種類、リスク レベル、投稿イベントなど、アカウントの危険なユーザー アクティビティを要約します。*
7. 必要に応じて、より具体的なプロンプトを使用します。
    - *この &lt;User Name または UPN&gt; リスク状態に関連する検出について説明します。*
8. ユーザーの最近の危険なサインイン試行を特定します。
    - *この &lt;User Name または UPN に関連付けられている最近の危険なサインイン試行は何ですか&gt;。*
9. ユーザー認証方法を調査します。
    - *このユーザーに対して構成されている認証方法は何ですか?*
10. サインイン アクティビティを確認します。
    - 過去 14 日間のこのユーザーのサインイン アクティビティを表示します。 失敗した試行の場所と IP アドレスを含めます。
11. ユーザーの最近のアクティビティを調べます。
    - *過去 14 日間のこのユーザーの監査ログを表示します。*

**成功条件**

| ディメンション | 成功基準 |
| --- | --- |
| Efficiency | 手動ナビゲーションと比較して、≥50% によって、危険なユーザー コンテキストを解釈する時間を短縮します。 |
| 対象範囲 | Entra の Security Copilot は、1 回の対話の検出、サインイン パターン、認証体制をまとめたものです。 |
| アクション可能性 | アナリストは、ユーザーが危険である理由を理解し、推奨される次の手順を特定します。 |
| 分析情報の品質 | Entra の Security Copilot では、自然言語、基になるシグナル、検出の種類に関する説明が提供されます。 |
| セキュリティ態勢 | アナリストは、リスクの修復、安全な確認、または無視を決定します。 |

[Microsoft Entra ID Protection のシナリオ](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-id-protection-scenarios)の詳細について説明します。

### PoC シナリオ: Microsoft Entra エージェント

Microsoft Entra エージェントは、ID 環境を分析し、ベスト プラクティスを適用し、ID とアクセスのセキュリティ体制を向上させるアクションを実行します。また、運用効率も向上します。 これらは Microsoft Entra サービスと統合され、組織の ID データと構成を使用して、コンテキストに応じた実用的な分析情報を提供します。

#### 条件付きアクセスの最適化エージェント

条件付きアクセスの最適化エージェントは、次のようなポリシーを評価します。

- 多要素認証を要求する (MFA)
- デバイス ベースのコントロールを適用する:
    - デバイスのコンプライアンス
    - アプリ保護ポリシー
    - ドメイン参加済みデバイス
- レガシ認証とデバイス コード フローをブロックする

**目標**: エージェントは、有効なポリシーを評価して、同様のポリシーの統合の可能性を提案します。 エージェントは、提案を識別すると、関連付けられているポリシーを 1 回のクリックで更新します。

[条件付きアクセスの最適化エージェントの前提条件](https://learn.microsoft.com/ja-jp/entra/security-copilot/conditional-access-agent-optimization)の詳細を確認します。

**実行**: 条件付きアクセス最適化エージェントを使用して、アクセス体制の分析、最適化の機会の検出、修復アクションの実行を行います。

1. セキュリティ管理者として [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. ホーム ページで、エージェント通知カードから [エージェント **に移動**] を選択するか、左側のナビゲーション メニューから **[エージェント** ] を選択します。

    [Image: 管理センターのエージェント オプションのスクリーンショット。]
3. [条件付きアクセスの最適化エージェント] で、[ **詳細の表示**] を選択します。

    [Image: [詳細の表示] オプションのスクリーンショット。]
4. **[エージェントの開始]** を選択します。

    注

    Privileged Identity Management (PIM) によってアクティブ化されたロールを持つアカウントは使用しないでください。

    [Image: [エージェントの開始] オプションのスクリーンショット。]
5. エージェント アクティビティの概要の横に **、エージェント アクティビティ マップ**があります。 ワークフロー グラフでエージェントの結果を確認します。
6. 提案を確認し、シナリオがエージェント ロジックにどのように合っているかに注意してください。
7. 推奨事項を適用または無視します。

    [Image: [提案の確認] オプションのスクリーンショット。]
8. ポリシーを更新または統合するには、ワンクリック修復を適用するか、提案を **レビュー済み**としてマークします。
9. **[修復]** を選択して、ポリシーを更新または統合します。

**成功条件**

| ディメンション | 成功基準 |
| --- | --- |
| Efficiency | 手動による評価とポリシーごとのレビューと比較して、≥50%による条件付きアクセス体制の評価にかかる時間を短縮します。 |
| 対象範囲 | エージェントの実行で、ポリシーのギャップ、冗長なルール、MFA またはデバイス制御の不足、危険な認証フローを特定します。 |
| アクション可能性 | 管理者は、ワンクリックの推奨事項を適用してポリシーを強化したり、それらを統合したりできます。 |
| 分析情報の品質 | エージェントは、ロジック、シグナル、評価されたポリシーなど、推奨事項に関するコンテキストの説明を提供します。 |
| セキュリティ態勢 | 一貫性のある MFA を適用し、レガシ認証を減らし、冗長ポリシーを統合し、デバイス アクセス要件を強化します。 |

### Entra でセキュリティ コピロットを紹介する

このセクションを使用して、PoC の結果を、役員、セキュリティ リーダー、および部門間のチームと共鳴する測定可能な結果に変換します。 PoC からの ID リスクを要約します。

- MFA ギャップまたは認証リスク
- 危険なユーザーと関連する検出パターン
- 古いアプリケーションのリスクが高い
- 条件付きアクセス ポリシーのギャップ、冗長ポリシー、または従来の認証パターン
- 正しく構成されていないリソースまたは未所有のリソース

#### 改善点を測定する

| カテゴリ | ベースライン | Copilot を使用する | 機能強化 |
| --- | --- | --- | --- |
| サインインエラーを調査する時間 |  |  |  |
| 監査の変更を調査する時間 |  |  |  |
| 危険なユーザーをトリアージする時間 |  |  |  |
| 条件付きアクセス ポリシーのレビュー作業 |  |  |  |
| 特定または修復されたリスク |  |  |  |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/security-operations-applications"} -->
## アプリケーションのための Microsoft Entra セキュリティ運用 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-applications
- Service: entra / architecture
- Article date: 2022-09-06
- Summary: セキュリティ上の脅威を特定するために、アプリケーションを監視してアラートを生成する方法について説明します。

アプリケーションは、セキュリティ侵害の攻撃対象となるため、監視する必要があります。 ユーザー アカウントほど頻繁に狙われるわけではありませんが、侵害される可能性があります。 アプリケーションは人間の介入なしで実行されることが多いため、攻撃を検出することがより難しい場合があります。

この記事では、アプリケーション イベントの監視とアラートに関するガイダンスを提供します。 次のことを確実に行えるように定期的に更新されます。

- 悪意のあるアプリケーションがデータに不正にアクセスできないようにする
- アプリケーションが不正なアクターによって侵害されないようにする
- 新しいアプリケーションのビルドと構成をより安全に行うことができるように分析情報を収集する

Microsoft Entra ID でアプリケーションがどのように動作するのかよく理解していない場合は、「[Microsoft Entra ID のアプリとサービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)」を参照してください。

注

「[Microsoft Entra のセキュリティ運用の概要](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-introduction)」をまだお読みでない場合は、今すぐお読みください。

### 注意点

アプリケーション ログでセキュリティ インシデントを監視する場合、以下の一覧を確認すると、通常のアクティビティと悪意のあるアクティビティを区別できます。 次のイベントは、セキュリティ上の問題を示している可能性があります。 それぞれをこの記事で説明します。

- 通常ビジネスのプロセスとスケジュールの外で発生した変更すべて
- アプリケーション資格情報の変更
- アプリケーションのアクセス許可

    - Microsoft Entra ID または Azure のロールベースのアクセス制御 (RBAC) のロールに割り当てられたサービス プリンシパル
    - 高い特権を持つアクセス許可が付与されたアプリケーション
    - Azure Key Vault の変更
    - アプリケーションに同意を付与するエンド ユーザー
    - リスクのレベルに基づいてエンド ユーザーの同意を停止した
- アプリケーション構成の変更

    - ユニバーサル リソース識別子 (URI) の変更または非標準
    - アプリケーション所有者に対する変更
    - ログアウト URL が変更された

### 確認先

調査と監視に使用するログ ファイルは次のとおりです。

- [Microsoft Entra 監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)
- [サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)
- [Microsoft 365 監査ログ](https://learn.microsoft.com/ja-jp/purview/audit-solutions-overview)
- [Azure Key Vault ログ](https://learn.microsoft.com/ja-jp/azure/key-vault/general/logging)

Azure portal から、Microsoft Entra 監査ログを表示し、コンマ区切り値 (CSV) または JavaScript Object Notation (JSON) ファイルとしてダウンロードできます。 Azure portal には、Microsoft Entra ログを以下のツールと統合する方法がいくつか用意されています。この統合により、監視とアラートの自動化を強化できます。

- **[Microsoft Sentinel](https://learn.microsoft.com/ja-jp/azure/sentinel/overview)** – セキュリティ情報イベント管理 (SIEM) 機能によって、エンタープライズ レベルでインテリジェントにセキュリティを分析できます。
- **[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure)** - Sigma は、ルールやテンプレートを記述するための進化し続けるオープン標準です。自動化された管理ツールでこれらのルールを使用して、ログ ファイルを解析できます。 推奨される検索条件に Sigma テンプレートがある場合は、Sigma リポジトリへのリンクを追加しました。 Sigma テンプレートは、Microsoft によって記述、テスト、管理されません。 リポジトリとテンプレートは、世界中の IT セキュリティ コミュニティによって作成および収集されています。
- **[Azure Monitor](https://learn.microsoft.com/ja-jp/azure/azure-monitor/overview)** – さまざまな条件に基づいて監視とアラートを自動化します。 ブックを作成または使用して、異なるソースのデータを結合できます。
- **[Azure Event Hubs](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-about) と SIEM の統合 **- Azure Event Hubs 統合を介して、Splunk、ArcSight、QRadar、Sumo Logic などの[他の SIEM と Microsoft Entra ログを統合できます](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub)。
- **[Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/what-is-defender-for-cloud-apps)** – アプリの検出と管理、アプリとリソース全体のガバナンス、クラウド アプリのコンプライアンスの確認を行います。
- **[Microsoft Entra ID Protection を使用してワークロード ID を保護する](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk)** - サインイン動作とオフラインでの侵害の兆候からワークロード ID のリスクを検出します。

監視およびアラートの対象となるのは、条件付きアクセス ポリシーの影響がほとんどです。 [条件付きアクセスに関する分析情報とレポート ブック](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-insights-reporting)を使用して、1 つまたは複数の条件付きアクセス ポリシーがサインインに及ぼしている影響と、デバイスの状態などのポリシーの結果を確認できます。 ブックを使用して概要を表示し、一定期間の影響を特定します。 このブックを使用して、特定のユーザーのサインインを調査できます。

この記事の後半に、監視とアラートが推奨される対象を示します。 脅威の種類ごとに分類されています。 事前構築済みソリューションがある場合は、リンクを示すか、表の後にサンプルを提供しています。 それ以外の場合は、前述のツールを使用してアラートを作成できます。

### アプリケーション資格情報

多くのアプリケーションは、Microsoft Entra ID での認証に資格情報を使用します。 予想されるプロセスの外部で追加された他の資格情報がある場合は、それらの資格情報を使用する悪意のあるアクターの可能性があります。 クライアント シークレットを使用するのではなく、信頼できる機関によって発行された X509 証明書またはマネージド ID を使用することをお勧めします。 しかしながら、クライアント シークレットを使用する必要がある場合は、適切な検疫プラクティスに従ってアプリケーションを安全に保ってください。 アプリケーションとサービス プリンシパルの更新は、監査ログに 2 つのエントリとして記録されます。

- アプリケーションを監視して、資格情報の有効期限が長いものを特定します。
- 長い期間の資格情報は、短い期間に置き換えます。 資格情報がコード リポジトリにコミットされず、安全に格納されるようにします。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 既存のアプリケーションに資格情報を追加した | 高 | Microsoft Entra 監査ログ | Service-Core ディレクトリ、Category-ApplicationManagementアクティビティ: アプリケーションの更新 - 証明書およびシークレット管理およびアクティビティ: サービス プリンシパルの更新/アプリケーションの更新 | 資格情報が次の場合にアラートを生成します。通常の営業時間またはワークフロー外に追加された場合、環境内で使用されていない種類の場合、またはサービス プリンシパルをサポートする非 SAML フローに追加された場合。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/NewAppOrServicePrincipalCredential.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 資格情報の有効期間がポリシーで許可されているよりも長い。 | ミディアム | Microsoft Graph | アプリケーション キー資格情報の状態と終了日およびアプリケーション パスワードの資格情報 | MS Graph API を使用して、資格情報の開始および終了日を検索し、許可された有効期間よりも長いものを判断できます。 この表の後の PowerShell スクリプトを参照してください。 |

次の事前構築済みの監視とアラートを利用できます。

- Microsoft Sentinel – [新しいアプリまたはサービス プリンシパルの資格情報が追加されたときにアラートを生成する](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/NewAppOrServicePrincipalCredential.yaml)
- Azure Monitor – [Solorigate リスクの評価に役立つ Microsoft Entra ブック - Microsoft Tech Community](https://techcommunity.microsoft.com/t5/azure-active-directory-identity/azure-ad-workbook-to-help-you-assess-solorigate-risk/ba-p/2010718)
- Defender for Cloud Apps – [Defender for Cloud Apps 異常検出アラートの調査ガイド](https://learn.microsoft.com/ja-jp/defender-cloud-apps/investigate-anomaly-alerts)
- PowerShell - [資格情報の有効期間を検索するサンプルの PowerShell スクリプト](https://github.com/madansr7/appCredAge)。

### アプリケーションのアクセス許可

管理者アカウントと同様に、アプリケーションには特権ロールを割り当てることができます。 アプリは、[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)などの Microsoft Entra ロール、または[請求閲覧者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles/management-and-governance#billing-reader)などの Azure RBAC ロールに割り当てることができます。 アプリはユーザーなしでバックグラウンド サービスとして実行できるため、アプリに特権ロールまたは特権アクセス許可が付与されたときは注意深く監視します。

#### ロールに割り当てられたサービス プリンシパル

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| Azure RBAC ロールまたは Microsoft Entra ロールに割り当てられたアプリ | 高から中 | Microsoft Entra 監査ログ | 種類: サービス プリンシパルアクティビティ: "メンバーをロールに追加する" または "適格なメンバーをロールに追加する"\- または -"スコープを持つメンバーをロールに追加する"。 | 高い特権ロールの場合、リスクは高くなります。 低い特権ロールの場合、リスクは中です。 通常の変更管理または構成手順の外部でアプリケーションが Azure ロールまたは Microsoft Entra ロールに割り当てられたときは、常にアラートが生成されます。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/ServicePrincipalAssignedPrivilegedRole.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

#### 高い特権を持つアクセス許可が付与されたアプリケーション

アプリケーションは、最小特権の原則に従う必要があります。 アプリケーションのアクセス許可を調査して、それらが必要であることを確認します。 アプリケーションを識別し、特権アクセス許可を強調表示するのに役立つ[アプリ同意付与レポート](https://aka.ms/getazureadpermissions)を作成できます。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| アプリに " *.All" のアクセス許可 (Directory.ReadWrite.All) や広範囲のアクセス許可 (Mail.* ) など、高い特権を持つアクセス許可が付与される | 高 | Microsoft Entra 監査ログ | "サービス プリンシパルへのアプリ ロールの割り当てを追加する"、このとき ターゲットで、機密データを含む API を識別する (Microsoft Graph など)およびAppRole.Value で、高い特権を持つアプリケーションのアクセス許可 (アプリ ロール) を識別する。 | " *.All" (Directory.ReadWrite.All) や広範囲のアクセス許可 (Mail.* ) など、幅広いアクセス許可が付与されたアプリ[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/ServicePrincipalAssignedAppRoleWithSensitiveAccess.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 管理者が、アプリケーションのアクセス許可 (アプリ ロール) または高い特権を持つ委任されたアクセス許可を付与する | 高 | Microsoft 365 ポータル | "サービス プリンシパルへのアプリ ロールの割り当てを追加する"、このときターゲットで、機密データを含む API を識別する (Microsoft Graph など)"委任されたアクセス許可の付与を追加する"、このときターゲットで、機密データを含む API を識別する (Microsoft Graph など)およびDelegatedPermissionGrant.Scope に、高い特権のアクセス許可が含まれる。 | 管理者がアプリケーションに同意したときにアラートを生成します。 特に、通常のアクティビティや変更手順以外の同意を探します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/ServicePrincipalAssignedAppRoleWithSensitiveAccess.yaml)[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/AzureADRoleManagementPermissionGrant.yaml)[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/MailPermissionsAddedToApplication.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| アプリケーションに、Microsoft Graph、Exchange、SharePoint、または Microsoft Entra ID に対するアクセス許可が付与される。 | 高 | Microsoft Entra 監査ログ | "委任されたアクセス許可の付与を追加する"、\- または -"サービス プリンシパルへのアプリ ロールの割り当てを追加する"、このときターゲットで、機密データを含む API を識別する (Microsoft Graph、Exchange Online など) | 前の行と同様にアラートを生成します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/ServicePrincipalAssignedAppRoleWithSensitiveAccess.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 他の API に対するアプリケーションのアクセス許可 (アプリ ロール) が付与される | ミディアム | Microsoft Entra 監査ログ | "サービス プリンシパルへのアプリ ロールの割り当てを追加する"、このときターゲットで、他の API を識別する。 | 前の行と同様にアラートを生成します。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| すべてのユーザーの代理として、高い特権を持つ委任されたアクセス許可が付与される | 高 | Microsoft Entra 監査ログ | "委任されたアクセス許可付与を追加する"。このとき、ターゲットで、機密データを含む API を識別する (Microsoft Graph など)、 DelegatedPermissionGrant.Scope に、高い特権のアクセス許可が含まれる、およびDelegatedPermissionGrant.ConsentType が "AllPrincipals" である。 | 前の行と同様にアラートを生成します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/ServicePrincipalAssignedAppRoleWithSensitiveAccess.yaml)[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/AzureADRoleManagementPermissionGrant.yaml)[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/SuspiciousOAuthApp_OfflineAccess.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

アプリのアクセス許可の監視の詳細については、「[チュートリアル: 危険な OAuth アプリを調査して修復する](https://learn.microsoft.com/ja-jp/defender-cloud-apps/investigate-risky-oauth)」を参照してください。

#### Azure Key Vault

Azure Key Vault を使用して、テナントのシークレットを格納します。 Key Vault の構成とアクティビティの変更には注意することをお勧めします。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| Key Vault にいつ、だれが、どのようにアクセスするか | ミディアム | [Azure Key Vault ログ](https://learn.microsoft.com/ja-jp/azure/key-vault/general/logging?tabs=Vault) | リソースの種類: Key Vault | 調査項目: 通常のプロセスと時間外のKey Vault へのアクセス、Key Vault ACL への変更。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/AzureDiagnostics/AzureKeyVaultAccessManipulation.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

Azure Key Vault を設定したら、[ログ記録を有効にします](https://learn.microsoft.com/ja-jp/azure/key-vault/general/howto-logging?tabs=azure-cli)。 [Key Vaults にいつ、どのようにアクセスされたか](https://learn.microsoft.com/ja-jp/azure/key-vault/general/logging?tabs=Vault)を表示し、Key Vault で[アラートを構成](https://learn.microsoft.com/ja-jp/azure/key-vault/general/alert)して、正常性に影響があった場合に、割り当てられたユーザーまたは配布リストにメール、通話、テキスト、または[イベント グリッド](https://learn.microsoft.com/ja-jp/azure/key-vault/general/event-grid-overview)通知を介して通知します。 さらに、Key Vault 分析情報を使用して[監視](https://learn.microsoft.com/ja-jp/azure/key-vault/general/alert)を設定すると、Key Vault 要求、パフォーマンス、エラー、待機時間のスナップショットが提供されます。 [Log Analytics](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/log-analytics-overview) には、Azure Key Vault 用の[サンプル クエリ](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/queries)もいくつか用意されています。これらのクエリにアクセスするには、Key Vault を選択した後、[監視] の [ログ] を選択します。

#### エンドユーザーの同意

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| アプリケーションに対するエンドユーザーの同意 | 低 | Microsoft Entra 監査ログ | アクティビティ: アプリケーションへの同意/ConsentContext.IsAdminConsent = false | ハイ プロファイル アカウントまたは高い特権を持つアカウント、リスクの高いアクセス許可を要求するアプリ、疑わしい名前 (汎用的、スペルミスがあるものなど) を持つアプリなどを探します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/AuditLogs/ConsentToApplicationDiscovery.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

アプリケーションに同意する行為は、悪意のあるものではありません。 ではありますが、疑わしいアプリケーションを探して、新しいエンドユーザーの同意付与を調査してください。 [ユーザーの同意操作を制限する](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/steps-secure-identity)ことができます。

同意操作の詳細については、以下のリソースを参照してください。

- [Microsoft Entra ID でのアプリケーションへの同意の管理と同意要求の評価](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-consent-requests)
- [不正な同意付与を検出して修復する - Office 365](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/detect-and-remediate-illicit-consent-grants)
- [インシデント対応プレイブック - アプリ同意付与の調査](https://learn.microsoft.com/ja-jp/security/operations/incident-response-playbook-app-consent)

#### リスクベースの同意のためにエンド ユーザーが停止した

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| リスクベースの同意のためにエンドユーザーの同意が停止した | ミディアム | Microsoft Entra 監査ログ | Core ディレクトリ/ApplicationManagement/アプリケーションへの同意 エラー状態の理由 = Microsoft.online.Security.userConsentBlockedForRiskyAppsExceptions | リスクが原因で同意が停止されたときは常に監視および分析します。 ハイ プロファイル アカウントまたは高い特権を持つアカウント、リスクの高いアクセス許可を要求するアプリ、疑わしい名前 (汎用的、スペルミスがあるものなど) を持つアプリなどを探します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/End-userconsentstoppedduetorisk-basedconsent.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

### アプリケーションの認証フロー

OAuth 2.0 プロトコルには、いくつかのフローがあります。 アプリケーションに推奨されるフローは、作成されているアプリケーションの種類によって異なります。 場合によっては、アプリケーションでフローを選択できます。 この場合、一部の認証フローが他に優先して推奨されます。 具体的には、リソース所有者パスワード資格情報 (ROPC) の使用は避けてください。これによってユーザーが現在のパスワード資格情報をアプリケーションに公開する必要があるためです。 その後、アプリケーションはこの資格情報を使用して、ID プロバイダーに対してユーザーを認証します。 ほとんどのアプリケーションでは、認証コード フローが推奨されているため、このフロー、または Proof Key for Code Exchange (PKCE) と共にこのフローを使用する必要があります。

ROPC が推奨される唯一のシナリオは、アプリケーションの自動テストです。 詳細については、「[自動統合テストの実行](https://learn.microsoft.com/ja-jp/entra/identity-platform/test-automate-integration-testing)」を参照してください。

デバイス コード フローは、入力制約付きデバイス用の別の OAuth 2.0 プロトコル フローであり、すべての環境で使用されるわけではありません。 環境にデバイス コード フローが存在していて、入力制約付きデバイスのシナリオで使用されていない場合。 アプリケーションが正しく構成されていないか、何らかの悪意が存在する可能性がある場合は、追加の調査が必要です。 デバイス コード フローは、条件付きアクセスでブロックまたは許可することもできます。 詳細については、「[条件付きアクセスの認証フロー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-authentication-flows#device-code-flow)」を参照してください。

次の情報を使用してアプリケーション認証を監視します。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| ROPC 認証フローを使用しているアプリケーション | ミディアム | Microsoft Entra サインイン ログ | 状態 = 成功認証プロトコル - ROPC | 資格情報をキャッシュまたは保存できるため、このアプリケーションには高いレベルの信頼が置かれています。 可能であれば、より安全な認証フローに移行します。 仮に使うなら、これは、アプリケーションの自動テストでのみ使う必要があります。 詳細については、「[Microsoft ID プラットフォームと OAuth 2.0 リソース所有者のパスワード資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc)」を参照してください[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| デバイス コード フローを使用しているアプリケーション | 低から中 | Microsoft Entra サインイン ログ | 状態 = 成功認証プロトコル - デバイス コード | デバイス コード フローは、すべての環境にあるとは限らない、入力制約付きデバイスに使用されます。 成功したデバイス コード フローが、その必要性がなく存在している場合は、正当性があるか調査します。 詳細については、「[Microsoft ID プラットフォームと OAuth 2.0 デバイス許可付与フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code)」を参照してください[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

### アプリケーション構成の変更

アプリケーション構成の変更を監視します。 具体的には、構成の Uniform Resource Identifier (URI)、所有権、ログアウト URL の変更です。

#### 宙ぶらりんの URI とリダイレクト URI の変更

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| ダングリング URI | 高 | Microsoft Entra ログとアプリケーションの登録 | Service-Core ディレクトリ、Category-ApplicationManagementアクティビティ: アプリケーションの更新成功 – プロパティ名 AppAddress | たとえば、存在しなくなったドメイン名や明示的に所有していないドメイン名を指す、宙ぶらりんの URI を探します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/URLAddedtoApplicationfromUnknownDomain.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| リダイレクト URI 構成の変更 | 高 | Microsoft Entra ログ | Service-Core ディレクトリ、Category-ApplicationManagementアクティビティ: アプリケーションの更新成功 – プロパティ名 AppAddress | HTTPS\* を使用していない URI、URL の末尾またはドメインにワイルドカードを含む URI、アプリケーションに固有ではない URI、ご自分が制御していないドメインを指す URI を探します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/ApplicationRedirectURLUpdate.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

これらの変更が検出されたときはアラートを生成します。

#### AppID URI が追加、変更、または削除された

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| AppID URI の変更 | 高 | Microsoft Entra ログ | Service-Core ディレクトリ、Category-ApplicationManagementアクティビティ: 更新アプリケーションアクティビティ: サービス プリンシパルの更新 | URI の追加、変更、削除など、AppID URI の変更を探します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/ApplicationIDURIChanged.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

これらの変更が、承認済みの変更管理手順の外部で検出されたときはアラートを生成します。

#### 新しい所有者

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| アプリケーション所有者に対する変更 | ミディアム | Microsoft Entra ログ | Service-Core ディレクトリ、Category-ApplicationManagementアクティビティ: アプリケーションへの所有者の追加 | 通常の変更管理アクティビティの外部で、アプリケーション所有者として追加されているユーザーのインスタンスを探します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/ChangestoApplicationOwnership.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

#### ログアウト URL が変更または削除された

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| ログアウト URL の変更 | 低 | Microsoft Entra ログ | Service-Core ディレクトリ、Category-ApplicationManagementアクティビティ: アプリケーションの更新およびアクティビティ: サービス プリンシパルの更新 | サインアウト URL に対する変更を探します。 空のエントリまたは存在しない場所へのエントリがあると、ユーザーによるセッションの終了が停止されます。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/ChangestoApplicationLogoutURL.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

### リソース

- GitHub Microsoft Entra ツールキット - https://github.com/microsoft/AzureADToolkit
- Azure Key Vault の概要とセキュリティ ガイダンス - [Azure Key Vault セキュリティの概要](https://learn.microsoft.com/ja-jp/azure/key-vault/general/security-features)
- Solorigate リスクの情報とツール - [Solorigate リスクの評価に役立つ Microsoft Entra ブック](https://techcommunity.microsoft.com/t5/azure-active-directory-identity/azure-ad-workbook-to-help-you-assess-solorigate-risk/ba-p/2010718)
- OAuth 攻撃検出ガイダンス - [OAuth アプリへの資格情報の異常な追加](https://learn.microsoft.com/ja-jp/defender-cloud-apps/investigate-anomaly-alerts)
- SIEM に対する Microsoft Entra 監視構成情報 - [Azure Monitor とパートナー ツールの統合](https://learn.microsoft.com/ja-jp/azure/azure-monitor/essentials/stream-monitoring-data-event-hubs)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/security-operations-consumer-accounts"} -->
## コンシューマー アカウントのための Microsoft Entra セキュリティ運用 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-consumer-accounts
- Service: entra / architecture
- Article date: 2025-05-20
- Summary: ベースラインを確立するためのガイダンスと、ユーザー アカウントの潜在的なセキュリティ問題を監視し、警告する方法。

Von Bedeutung

2025 年 5 月 1 日より、Azure Active Directory B2C (Azure AD B2C) は新規のお客様が購入できなくなります。 詳細については、FAQ の [「Azure AD B2C を引き続き購入できますか?」を](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) 参照してください。

コンシューマー ID アクティビティは、組織が保護および監視するための重要な領域です。 この記事では、Azure Active Directory B2C (Azure AD B2C) テナントについて扱い、コンシューマー アカウント アクティビティの監視に関するガイダンスを提供します。 アクティビティは次のとおりです。

- コンシューマー アカウント
- 特権アカウント
- アプリケーション
- インフラストラクチャ

### はじめに

この記事のガイダンスに従って作業する前に、[Microsoft Entra セキュリティ運用ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-introduction)を読むことをお勧めします。

### ベースラインを定義する

異常な動作を発見するには、通常の想定される動作を定義します。 組織にとって想定される動作を定義することで、想定しない動作を検出できます。 その定義を使用することで、監視とアラート中の擬陽性を減らすことができます。

想定される動作を定義した状態で、ベースライン監視を実行し、期待値を検証します。 次に、許容範囲から外れたものについてログを監視します。

通常のプロセス以外で作成されたアカウントの場合、Microsoft Entra の監査ログ、サインイン ログ、ディレクトリ属性をデータ ソースとして使用します。 次の提案は、正常な動作を定義するのに役立ちます。

#### コンシューマー アカウントの作成

次のリストを評価します。

- コンシューマー アカウントを作成および管理するためのツールとプロセスの戦略と原則
    - たとえば、コンシューマー アカウントの属性に適用される標準の属性および形式
- アカウントの作成に承認されているソース。
    - たとえば、カスタム ポリシーのオンボーディング、顧客プロビジョニング、移行ツールなど
- 承認されたソース以外でアカウントが作成された場合のアラート戦略。
    - 自分の組織が共同作業している組織の管理リストを作成します
- 承認されていないコンシューマー アカウント管理者によって作成、変更、または無効化されたアカウントに対する戦略とアラートのパラメーター。
- 顧客番号などの標準属性がない、または組織の名前付け規則に従っていないコンシューマー アカウントの監視とアラート戦略。
- アカウントの削除と保持のための戦略、原則、プロセス

### 確認先

ログ ファイルを使用して調査と監視を行います。 手順については、次の記事を参照してください。

- [Microsoft Entra ID の監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)
- [Microsoft Entra ID のサインイン ログ (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)
- [方法: リスクを調査する](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)

#### 監査ログと自動化ツール

Azure portal から、Microsoft Entra 監査ログを表示し、コンマ区切り値 (CSV) または JSON (JavaScript Object Notation) ファイルとしてダウンロードできます。 監視とアラートを自動化するために、Azure portal を使用して Microsoft Entra ログを他のツールと統合します。

- **Microsoft Sentinel**- セキュリティ情報イベント管理 (SIEM) 機能を備えたセキュリティ分析プラットフォーム
    - [Microsoft Sentinel とは](https://learn.microsoft.com/ja-jp/azure/sentinel/overview)
- **Sigma ルール**- 自動化された管理ツールがログ ファイルを解析するために使用できるルールとテンプレートを記述するためのオープン標準です。 推奨される検索条件に Sigma テンプレートがある場合は、Sigma リポジトリへのリンクを追加しました。 Microsoft では、Sigma テンプレートの作成、テスト、管理は行っていません。 リポジトリとテンプレートは、IT セキュリティ コミュニティによって作成および収集されます。
    - [SigmaHR/sigma](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure)
- **Azure Monitor**– さまざまな条件に基づいて監視とアラートを自動化します。 ブックを作成または使用して、異なるソースのデータを結合できます。
    - [Azure Monitor の概要](https://learn.microsoft.com/ja-jp/azure/azure-monitor/overview)
- **SIEM と統合された Azure Event Hubs**- Azure Event Hubs を使用して、Splunk、ArcSight、QRadar、Sumo Logic など、他の SIEM と Microsoft Entra ログを統合します。
    - [Azure Event Hubs - ビッグ データのストリーミング プラットフォームとなるイベント インジェスト サービス](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-about)
    - [チュートリアル: Microsoft Entra ログを Azure イベント ハブにストリーミングする](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub)
- **Microsoft Defender for Cloud Apps**- Microsoft Defender for Cloud Apps - アプリの検出と管理、アプリとリソース全体のガバナンス、クラウド アプリのコンプライアンスの準拠を行います。
    - [Microsoft Defender for Cloud Apps の概要](https://learn.microsoft.com/ja-jp/defender-cloud-apps/what-is-defender-for-cloud-apps)
- **Microsoft Entra ID Protection**- サインイン動作とオフラインでの侵害の兆候ワークロード ID のリスクを検出します。
    - [Identity Protection でワークロード ID のセキュリティ保護](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk)

監視およびアラートする内容に関する推奨事項については、記事の残りの部分を参照してください。 脅威の種類別に整理された表を参照してください。 次の表に従って、事前構築済みのソリューションまたはサンプルへのリンクを参照してください。 前述のツールを使用してアラートを作成します。

### コンシューマー アカウント

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 多数のアカウントの作成または削除 | 高 | Microsoft Entra 監査ログ | アクティビティ: ユーザーの追加状態 = 成功開始者(アクター) = CPIM サービスおよびアクティビティ: ユーザーの削除状態 = 成功開始者(アクター) = CPIM サービス | ベースラインしきい値を定義してから、組織の行動に合わせて監視および調整します。 誤ったアラートを制限します。 |
| 承認されていないユーザーまたはプロセスによって作成・削除されたアカウント。 | ミディアム | Microsoft Entra 監査ログ | 開始者 (アクター) - ユーザー プリンシパル名およびアクティビティ: ユーザーの追加状態 = 成功開始者(アクター) != CPIM サービスおよび - またはアクティビティ: ユーザーの削除状態 = 成功開始者(アクター) != CPIM サービス | アクターが承認されていないユーザーの場合、アラートを送信するように構成します。 |
| 特権ロールに割り当てられたアカウント | 高 | Microsoft Entra 監査ログ | アクティビティ: ユーザーの追加状態 = 成功(アクター) によって開始 == CPIM サービスおよびアクティビティ: ロールへのメンバーの追加状態 = 成功 | アカウントが Microsoft Entra ロール、Azure ロール、または特権グループ メンバーシップに割り当てられている場合、調査についてアラートを生成し、優先します。 |
| 失敗したサインインの試行 | 中 - 孤立したインシデントの場合高 - 多数のアカウントで同じパターンが発生している場合 | Microsoft Entra サインイン ログ | 状態 = 失敗およびサインイン エラー コード 50126 - 無効なユーザー名またはパスワードにより、資格情報の検証でエラーが発生しました。およびアプリケーション == "CPIM PowerShell Client"- または -アプリケーション == "ProxyIdentityExperienceFramework" | ベースラインしきい値を定義してから、組織の行動に合わせて監視および調整し、誤ったアラートが生成されないようにします。 |
| スマート ロックアウト イベント | 中 - 孤立したインシデントの場合高 - 多数のアカウントで同じパターンまたは VIP が発生している場合 | Microsoft Entra サインイン ログ | 状態 = 失敗およびサインイン エラー コード = 50053 - IdsLockedおよびアプリケーション == "CPIM PowerShell Client"- または -アプリケーション =="ProxyIdentityExperienceFramework" | ベースラインしきい値を定義してから、組織の行動に合わせて監視および調整し、誤ったアラートを制限します。 |
| 事業を行っていない国または地域からの認証の失敗 | ミディアム | Microsoft Entra サインイン ログ | 状態 = 失敗および場所 = &lt;未承認の場所&gt;およびApplication == "CPIM PowerShell Client"- または -アプリケーション == "ProxyIdentityExperienceFramework" | 指定された市区町村名と等しくないエントリを監視します。 |
| 任意の種類の認証失敗の増加 | ミディアム | Microsoft Entra サインイン ログ | 状態 = 失敗およびApplication == "CPIM PowerShell Client"- または -アプリケーション == "ProxyIdentityExperienceFramework" | しきい値を設定していない場合は、失敗が 10% 以上増えた場合に監視してアラートを生成します。 |
| アカウントがサインインで無効またはブロックされる | 低 | Microsoft Entra サインイン ログ | 状態 = 失敗およびエラー コード = 50057、ユーザー アカウントは無効になっています。 | このシナリオは、誰かが組織を退職した後にアカウントにアクセスしようとしていることを示している可能性があります。 このアカウントはブロックされていますが、このアクティビティについて記録し、アラートを生成することが重要です。 |
| 成功したサインインの測定可能な増加 | 低 | Microsoft Entra サインイン ログ | 状態 = 成功およびApplication == "CPIM PowerShell Client"- または -アプリケーション == "ProxyIdentityExperienceFramework" | しきい値を設定していない場合は、認証の成功が 10% 以上増えた場合に監視し、アラートを生成します。 |

### 特権アカウント

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| サインインの失敗、不正なパスワードしきい値 | 高 | Microsoft Entra サインイン ログ | 状態 = 失敗およびエラー コード = 50126 | ベースラインしきい値を定義してから、組織の行動に合わせて監視および調整します。 誤ったアラートを制限します。 |
| 条件付きアクセス要件が原因による失敗 | 高 | Microsoft Entra サインイン ログ | 状態 = 失敗およびエラー コード = 53003および失敗の理由 = 条件付きアクセスによってブロック | このイベントは、攻撃者がアカウントに侵入しようとしていることを示している可能性があります。 |
| 割り込み | 高、中 | Microsoft Entra サインイン ログ | 状態 = 失敗およびエラー コード = 53003および失敗の理由 = 条件付きアクセスによってブロック | このイベントは、攻撃者がアカウントのパスワードを持っていても、MFA チャレンジに合格できないことを示している可能性があります。 |
| アカウントのロックアウト | 高 | Microsoft Entra サインイン ログ | 状態 = 失敗およびエラー コード = 50053 | ベースラインしきい値を定義してから、組織の行動に合わせて監視および調整します。 誤ったアラートを制限します。 |
| アカウントがサインインで無効またはブロックされる | 低 | Microsoft Entra サインイン ログ | 状態 = 失敗およびターゲット = ユーザー UPNおよびエラー コード = 50057 | このイベントは、誰かが組織を退職した後にアカウントを取得しようとしていることを示している可能性があります。 このアカウントはブロックされていますが、このアクティビティについて記録し、アラートを生成します。 |
| MFA 不正アクセスのアラートまたはブロック | 高 | Microsoft Entra サインイン ログ/Azure Log Analytics | サインイン &gt; 認証の詳細 結果の詳細 = MFA 拒否、不正コードが入力された | 特権ユーザーは、自分が MFA プロンプトを引き起こしていないと示しており、これは攻撃者がアカウント パスワードを持っていることを示している可能性があります。 |
| MFA 不正アクセスのアラートまたはブロック | 高 | Microsoft Entra サインイン ログ/Azure Log Analytics | アクティビティの種類 = 不正報告 - MFA または不正報告によりユーザーをブロックしています - 不正報告のテナント レベル設定に基づいて、対応を行いませんでした | 特権ユーザーは、MFA プロンプトの割り当てがないことを示しました。 このシナリオは、攻撃者がアカウント パスワードを持っている可能性を示します。 |
| 想定された制御の範囲外の特権アカウント サインイン | 高 | Microsoft Entra サインイン ログ | 状態 = 失敗UserPrincipalName = &lt;管理者アカウント&gt;  場所 = &lt;未承認の場所&gt;  IP アドレス = &lt;未承認の IP&gt;デバイス情報 = &lt;承認されていないブラウザー、オペレーティング システム&gt; | 未承認として定義したエントリを監視し、アラートを生成します。 |
| 通常のサインイン時間外 | 高 | Microsoft Entra サインイン ログ | 状態 = 成功および場所 =および時間 = 勤務時間外 | 想定される時間外にサインインが発生した場合について監視し、アラートを生成します。 各特権アカウントの通常の勤務パターンを見つけて、通常の勤務時間外に予定外の変更が発生した場合にアラートを生成します。 通常の勤務時間外のサインインが、侵害や内部関係者の脅威の可能性を示している場合があります。 |
| パスワードの変更 | 高 | Microsoft Entra 監査ログ | アクティビティ アクター = 管理者/セルフサービスおよびターゲット = ユーザーおよび状態 = 成功または失敗 | いずれかの管理者アカウントのパスワードが変更された場合にアラートを送信します。 特権アカウントのクエリを記述します。 |
| 認証方法に対する変更 | 高 | Microsoft Entra 監査ログ | アクティビティ: ID プロバイダーの作成カテゴリ: ResourceManagementターゲット: ユーザー プリンシパル名 | 変更は、攻撃者が認証方法をアカウントに追加して、継続的にアクセスできるようにしていることを示している可能性があります。 |
| 承認されていないアクターによって更新された ID プロバイダー | 高 | Microsoft Entra 監査ログ | アクティビティ: ID プロバイダーの更新カテゴリ: ResourceManagementターゲット: ユーザー プリンシパル名 | 変更は、攻撃者が認証方法をアカウントに追加して、継続的にアクセスできるようにしていることを示している可能性があります。 |
| 承認されていないアクターによって削除された ID プロバイダー | 高 | Microsoft Entra アクセス レビュー | アクティビティ: ID プロバイダーの削除カテゴリ: ResourceManagementターゲット: ユーザー プリンシパル名 | 変更は、攻撃者が認証方法をアカウントに追加して、継続的にアクセスできるようにしていることを示している可能性があります。 |

### アプリケーション

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| アプリケーションに資格情報を追加した | 高 | Microsoft Entra 監査ログ | Service-Core ディレクトリ、Category-ApplicationManagementアクティビティ: アプリケーションの更新 - 証明書およびシークレット管理およびアクティビティ: サービス プリンシパルの更新/アプリケーションの更新 | 資格情報が次の場合にアラートを生成します。通常の営業時間またはワークフロー外に追加された場合、環境内で使用されていない種類の場合、またはサービス プリンシパルをサポートする非 SAML フローに追加された場合。 |
| Azure ロールベースのアクセス制御 (RBAC) ロールまたは Microsoft Entra ロールに割り当てられたアプリ | 高から中 | Microsoft Entra 監査ログ | 種類: サービス プリンシパルアクティビティ: ”ロールへのメンバーの追加”または”ロールへの有資格メンバーの追加”- または -"スコープを持つメンバーをロールに追加する" | 対象外 |
| ".All" を使ったアクセス許可 (Directory.ReadWrite.All) や広範囲のアクセス許可 (Mail.) など、高い権限が付与されたアプリ。 | 高 | Microsoft Entra 監査ログ | 対象外 | ".All" (Directory.ReadWrite.All) や広範囲のアクセス許可 (Mail.) など、幅広いアクセス許可が付与されたアプリ |
| 管理者が、アプリケーションのアクセス許可 (アプリ ロール) または高い特権を持つ委任されたアクセス許可を付与する | 高 | Microsoft 365 ポータル | "サービス プリンシパルへのアプリ ロールの割り当てを追加する"このときターゲットは、機密データを含む API を識別する (Microsoft Graph など) “委任されたアクセス許可の付与を追加する”このときターゲットで、機密データを含む API を識別する (Microsoft Graph など)およびDelegatedPermissionGrant.Scope に、高権限のアクセス許可が含まれる。 | グローバル管理者、アプリケーション管理者、またはクラウド アプリケーション管理者がアプリケーションに同意したときにアラートを生成します。 特に、通常のアクティビティや変更手順以外の同意を探します。 |
| アプリケーションに、Microsoft Graph、Exchange、SharePoint、または Microsoft Entra ID に対するアクセス許可が付与される。 | 高 | Microsoft Entra 監査ログ | "委任されたアクセス許可の付与を追加する"- または -"サービス プリンシパルへのアプリ ロールの割り当てを追加する"このときターゲットで、機密データを含む API を識別する (Microsoft Graph、Exchange Online など) | 前の行のアラートを使用します。 |
| すべてのユーザーの代理として、高い特権を持つ委任されたアクセス許可が付与される | 高 | Microsoft Entra 監査ログ | "委任されたアクセス許可の付与を追加する"whereターゲットで、機密データを含む API を識別する (Microsoft Graph など)DelegatedPermissionGrant.Scope に、高権限のアクセス許可が含まれるおよびDelegatedPermissionGrant.ConsentType が "AllPrincipals" である。 | 前の行のアラートを使用します。 |
| ROPC 認証フローを使用しているアプリケーション | ミディアム | Microsoft Entra サインイン ログ | 状態 = 成功認証プロトコル - ROPC | 資格情報をキャッシュまたは保存できるため、このアプリケーションには高いレベルの信頼が置かれています。 可能であれば、より安全な認証フローに移行します。 このプロセスは自動化されたアプリケーション テストでのみ使用しますが、ほとんどありません。 |
| ダングリング URI | 高 | Microsoft Entra ログとアプリケーションの登録 | サービス - コア ディレクトリカテゴリ - ApplicationManagementアクティビティ: アプリケーションの更新成功 – プロパティ名 AppAddress | たとえば、存在しなくなったドメイン名や所有していないドメイン名を指す未解決の URI を探します。 |
| リダイレクト URI 構成の変更 | 高 | Microsoft Entra ログ | サービス - コア ディレクトリカテゴリ - ApplicationManagementアクティビティ: アプリケーションの更新成功 – プロパティ名 AppAddress | HTTPS\* を使用していない URI、URL の末尾またはドメインにワイルドカードを含む URI、アプリケーションに固有**ではない** URI、ご自分が制御していないドメインを指す URI を探します。 |
| AppID URI の変更 | 高 | Microsoft Entra ログ | サービス - コア ディレクトリカテゴリ - ApplicationManagementアクティビティ: アプリケーションの更新アクティビティ: サービス プリンシパルの更新 | URI の追加、変更、削除など、AppID URI の変更を探します。 |
| アプリケーション所有者に対する変更 | ミディアム | Microsoft Entra ログ | サービス - コア ディレクトリカテゴリ - ApplicationManagementアクティビティ: アプリケーションへの所有者の追加 | 通常の変更管理アクティビティ以外でアプリケーション所有者として追加されたユーザーのインスタンスを探します。 |
| サインアウト URL への変更 | 低 | Microsoft Entra ログ | サービス - コア ディレクトリカテゴリ - ApplicationManagementアクティビティ: アプリケーションの更新およびアクティビティ: サービス プリンシパルの更新 | サインアウト URL に対する変更を探します。 空のエントリまたは存在しない場所へのエントリがあると、ユーザーによるセッションの終了が停止されます。 |

### インフラストラクチャ

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 承認されていないアクターによって作成された新しい条件付きアクセス ポリシー | 高 | Microsoft Entra 監査ログ | アクティビティ: 条件付きアクセス ポリシーを追加するカテゴリ: Policy開始者 (アクター): ユーザー プリンシパル名 | 条件付きアクセスの変更を監視し、アラートを生成します。 開始者 (アクター): 条件付きアクセスに変更を加えるために承認されていますか? |
| 承認されていないアクターによって削除された条件付きアクセス ポリシー | ミディアム | Microsoft Entra 監査ログ | アクティビティ: 条件付きアクセス ポリシーを削除するカテゴリ: Policy開始者 (アクター): ユーザー プリンシパル名 | 条件付きアクセスの変更を監視し、アラートを生成します。 開始者 (アクター): 条件付きアクセスに変更を加えるために承認されていますか? |
| 承認されていないアクターによって更新された条件付きアクセス ポリシー | 高 | Microsoft Entra 監査ログ | アクティビティ: 条件付きアクセス ポリシーを更新するカテゴリ: Policy開始者 (アクター): ユーザー プリンシパル名 | 条件付きアクセスの変更を監視し、アラートを生成します。 開始者 (アクター): 条件付きアクセスに変更を加えるために承認されていますか?変更されたプロパティを確認し、古い値と新しい値を比較します |
| 承認されていないアクターによって作成された B2C カスタム ポリシー | 高 | Microsoft Entra 監査ログ | アクティビティ: カスタム ポリシーを作成するカテゴリ: ResourceManagementターゲット: ユーザー プリンシパル名 | カスタム ポリシーの変更を監視し、アラートを生成します。 開始者 (アクター): カスタム ポリシーの変更を承認されていますか? |
| 承認されていないアクターによって更新された B2C カスタム ポリシー | 高 | Microsoft Entra 監査ログ | アクティビティ: カスタム ポリシーを取得するカテゴリ: ResourceManagementターゲット: ユーザー プリンシパル名 | カスタム ポリシーの変更を監視し、アラートを生成します。 開始者 (アクター): カスタム ポリシーの変更を承認されていますか? |
| 承認されていないアクターによって削除された B2C カスタム ポリシー | ミディアム | Microsoft Entra 監査ログ | アクティビティ: カスタム ポリシーを削除するカテゴリ: ResourceManagementターゲット: ユーザー プリンシパル名 | カスタム ポリシーの変更を監視し、アラートを生成します。 開始者 (アクター): カスタム ポリシーの変更を承認されていますか? |
| 承認されていないアクターによって作成されたユーザー フロー | 高 | Microsoft Entra 監査ログ | アクティビティ: ユーザー フローを作成するカテゴリ: ResourceManagementターゲット: ユーザー プリンシパル名 | ユーザー フローの変更を監視し、アラートを生成します。 開始者 (アクター): ユーザー フローの変更を承認されていますか? |
| 承認されていないアクターによって更新されたユーザー フロー | 高 | Microsoft Entra 監査ログ | アクティビティ: ユーザー フローの更新カテゴリ: ResourceManagementターゲット: ユーザー プリンシパル名 | ユーザー フローの変更を監視し、アラートを生成します。 開始者 (アクター): ユーザー フローの変更を承認されていますか? |
| 承認されていないアクターによって削除されたユーザー フロー | ミディアム | Microsoft Entra 監査ログ | アクティビティ: ユーザー フローの削除カテゴリ: ResourceManagementターゲット: ユーザー プリンシパル名 | ユーザー フローの変更を監視し、アラートを生成します。 開始者 (アクター): ユーザー フローの変更を承認されていますか? |
| 承認されていないアクターによって作成された API コネクタ | ミディアム | Microsoft Entra 監査ログ | アクティビティ: API コネクタを作成するカテゴリ: ResourceManagementターゲット: ユーザー プリンシパル名 | API コネクタの変更を監視し、アラートを生成します。 開始者 (アクター): API コネクタの変更を承認されていますか? |
| 承認されていないアクターによって更新された API コネクタ | ミディアム | Microsoft Entra 監査ログ | アクティビティ: API コネクタの更新カテゴリ: ResourceManagementターゲット: ユーザー プリンシパル名: ResourceManagement | API コネクタの変更を監視し、アラートを生成します。 開始者 (アクター): API コネクタの変更を承認されていますか? |
| 承認されていないアクターによって削除された API コネクタ | ミディアム | Microsoft Entra 監査ログ | アクティビティ: API コネクタの更新カテゴリ: ResourceManagementターゲット: ユーザー プリンシパル名: ResourceManagement | API コネクタの変更を監視し、アラートを生成します。 開始者 (アクター): API コネクタの変更を承認されていますか? |
| 承認されていないアクターによって作成された ID プロバイダー (IdP) | 高 | Microsoft Entra 監査ログ | アクティビティ: ID プロバイダーの作成カテゴリ: ResourceManagementターゲット: ユーザー プリンシパル名 | IdP の変更を監視し、アラートを生成します。 開始者 (アクター): IdP 構成の変更を承認されていますか? |
| 承認されていないアクターによって更新された IdP | 高 | Microsoft Entra 監査ログ | アクティビティ: ID プロバイダーの更新カテゴリ: ResourceManagementターゲット: ユーザー プリンシパル名 | IdP の変更を監視し、アラートを生成します。 開始者 (アクター): IdP 構成の変更を承認されていますか? |
| 承認されていないアクターによって削除された IdP | ミディアム | Microsoft Entra 監査ログ | アクティビティ: ID プロバイダーの削除カテゴリ: ResourceManagementターゲット: ユーザー プリンシパル名 | IdP の変更を監視し、アラートを生成します。 開始者 (アクター): IdP 構成の変更を承認されていますか? |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/security-operations-devices"} -->
## デバイスに対する Microsoft Entra セキュリティ オペレーション - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-devices
- Service: entra / architecture
- Article date: 2022-09-06
- Summary: ベースラインを定めてデバイスの監視とレポートを行い、デバイスの潜在的なセキュリティ リスクを特定する方法について説明します。

デバイスが ID ベースの攻撃の対象にされることはあまりありません。しかし、セキュリティ制御への適合を偽装したり、ユーザーを偽装したりするために、デバイスが利用される "*可能性はあります*"。 デバイスは、Microsoft Entra ID と次の 4 つのいずれかの関係を持つことができます。

- 登録解除済み
- [Microsoft Entra 登録済み](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-device-registration)
- [Microsoft Entra 参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)
- [Microsoft Entra ハイブリッド参加済み](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)

登録済みデバイスおよび参加済みデバイスには、[プライマリ更新トークン (PRT)](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token) が発行されます。PRT はプライマリ認証アーティファクトとして使用できるほか、多要素認証アーティファクトとして使用されることもあります。 攻撃者の行動としては、自身のデバイスを登録しようとする、正当なデバイスの PRT を使用してビジネス データにアクセスしようとする、正当なユーザー デバイスから PRT ベースのトークンを盗もうとする、Microsoft Entra ID のデバイスベースの制御に含まれる構成ミスを見つけようとする、などが考えられます。 Microsoft Entra ハイブリッド参加済みのデバイスについては、参加プロセスの開始と制御に管理者が対応するので、発生し得る攻撃方法を抑えることができます。

デバイスの統合方法の詳細については、「[Microsoft Entra デバイスのデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/devices/plan-device-deployment)」の「[統合方法を選択する](https://learn.microsoft.com/ja-jp/entra/identity/devices/plan-device-deployment)」を参照してください。

悪意のあるアクターからデバイス経由でインフラストラクチャに攻撃を受けるリスクを減らすには、次のものを監視してください

- デバイスの登録と Microsoft Entra への参加
- アプリケーションにアクセスする非準拠デバイス
- BitLocker キーの取得
- デバイス管理者ロール
- 仮想マシンへのサインイン

### 確認先

調査と監視に使用するログ ファイルは次のとおりです。

- [Microsoft Entra 監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)
- [サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)
- [Microsoft 365 監査ログ](https://learn.microsoft.com/ja-jp/purview/audit-solutions-overview)
- [Azure Key Vault ログ](https://learn.microsoft.com/ja-jp/azure/key-vault/general/logging?tabs=Vault)

Azure portal から、Microsoft Entra 監査ログを表示し、コンマ区切り値 (CSV) または JavaScript Object Notation (JSON) ファイルとしてダウンロードできます。 Azure portal には、Microsoft Entra ログを他のツールと統合する方法がいくつか用意されており、監視とアラートの自動化を強化することができます。

- **[Microsoft Sentinel](https://learn.microsoft.com/ja-jp/azure/sentinel/overview)** – セキュリティ情報イベント管理 (SIEM) 機能を備え、エンタープライズ レベルでインテリジェントにセキュリティを分析します。
- **[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure)** - Sigma は、ルールやテンプレートを記述するための進化し続けるオープン標準です。自動化された管理ツールでこれらのルールを使用して、ログ ファイルを解析できます。 推奨される検索条件に Sigma テンプレートが存在する場合は、Sigma リポジトリへのリンクを追加しました。 Sigma テンプレートは、Microsoft によって記述、テスト、管理されません。 リポジトリとテンプレートは、世界中の IT セキュリティ コミュニティによって作成および収集されています。
- **[Azure Monitor](https://learn.microsoft.com/ja-jp/azure/azure-monitor/overview)** - さまざまな条件に基づいて監視とアラートを自動化します。 ブックを作成または使用して、異なるソースのデータを結合できます。
- **[Azure Event Hubs](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-about) と SIEM の統合 **- Azure Event Hubs 統合を介して、Splunk、ArcSight、QRadar、Sumo Logic などの[他の SIEM と Microsoft Entra ログを統合できます](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub)。
- **[Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/what-is-defender-for-cloud-apps)** – アプリを検出し、アプリを管理し、すべてのアプリとリソースを制御し、クラウド アプリのコンプライアンス状況を確認できます。
- **[Microsoft Entra ID Protection を使用してワークロード ID を保護する](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk)** - サインイン動作とオフラインでの侵害の兆候からワークロード ID のリスクを検出するために使用します。

監視およびアラートの対象となるのは、条件付きアクセス ポリシーの影響がほとんどです。 [条件付きアクセスに関する分析情報とレポート ブック](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-insights-reporting)を使用して、1 つまたは複数の条件付きアクセス ポリシーがサインインに及ぼしている影響と、デバイスの状態などのポリシーの結果を確認できます。 このブックでは、概要を確認し、指定期間における影響を特定できます。 ブックを使用して、特定のユーザーのサインインを調査することもできます。

以降では、監視とアラートが推奨される対象について説明し、脅威の種類ごとの分類も示します。 特定の事前構築済みソリューションがある場合は、表の後にリンクを示すか、サンプルを提供しています。 それ以外の場合は、前述のツールを使用してアラートを作成できます。

### ポリシー対象外のデバイスの登録と参加

Microsoft Entra 登録済みデバイスおよび Microsoft Entra 参加済みデバイスは、単一の認証要素に相当するプライマリ更新トークン (PRT) を保有しています。 これらのデバイスには、強力な認証要求が含まれることもあります。 PRT に強力な認証要求が含まれる状況の詳細については、「[PRT が MFA 要求を受けるのはいつですか?](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token)」を参照してください。 悪意のあるアクターがデバイスの登録および参加を行えないようにするために、デバイスの登録または参加には多要素認証 (MFA) を必須としてください。 さらに、MFA なしで登録または参加したデバイスがないか監視してください。 また、MFA の設定とポリシーや、デバイス コンプライアンス ポリシーが変更されていないかどうかを監視する必要もあります。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| MFA なしで行われたデバイスの登録または参加 | ミディアム | サインイン ログ | アクティビティ: デバイス登録サービスへの認証成功。 AndMFA が必須とされていない | アラートのタイミング: MFA なしでのデバイスの登録または参加[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/SigninLogs/SuspiciousSignintoPrivilegedAccount.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| Microsoft Entra ID におけるデバイス登録の MFA のトグルに対する変更 | 高 | 監査ログ | アクティビティ: デバイス登録ポリシーの設定 | 調査項目: トグルがオフに設定されていないかどうか。 監査ログのエントリがない。 定期的なチェックをスケジュールしてください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| ドメイン参加済みデバイスまたは準拠デバイスを必須とする条件付きアクセス ポリシーに対する変更。 | 高 | 監査ログ | 条件付きアクセス ポリシーの変更 | アラートのタイミング: ドメインに参加または準拠している必要があるすべてのポリシーの変更、信頼できる場所の変更、または MFA ポリシー例外へのアカウントまたはデバイスの追加。 |

Microsoft Sentinel を使用し、MFA なしでデバイスを登録したとき、デバイスに参加したときに適切な管理者に通知するアラートを作成できます。

```
SigninLogs
| where ResourceDisplayName == "Device Registration Service"
| where ConditionalAccessStatus == "success"
| where AuthenticationRequirement <> "multiFactorAuthentication"
```

また、[Microsoft Intune を使用してデバイス コンプライアンス ポリシーの設定および監視を行うこともできます](https://learn.microsoft.com/ja-jp/mem/intune/protect/device-compliance-get-started)。

### 非準拠デバイスのサインイン

条件付きアクセス ポリシーで準拠デバイスを必須とするだけでは、クラウド アプリケーションおよびソフトウェアとしてのサービス アプリケーションへのアクセスをブロックしきれないことがあります。

 Windows 10 デバイスのポリシー準拠を維持するには、[モバイル デバイス管理](https://learn.microsoft.com/ja-jp/windows/client-management/mdm/) (MDM) が役立ちます。 Windows バージョン 1809 より、ポリシーの[セキュリティ ベースライン](https://learn.microsoft.com/ja-jp/windows/client-management/mdm/)がリリースされています。 Microsoft Entra ID を [MDM と統合](https://learn.microsoft.com/ja-jp/windows/client-management/azure-active-directory-integration-with-mdm)することで、企業ポリシーへの準拠をデバイスに義務付けると共に、デバイスの準拠状況をレポートできます。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 非準拠デバイスによるサインイン | 高 | サインイン ログ | DeviceDetail.isCompliant == false（デバイスが適合していません） | 準拠しているデバイスからのサインインが必要な場合のアラートのタイミング: 非準拠デバイスによるすべてのサインイン、または MFA や信頼できる場所を持たないすべてのアクセス。<br>デバイスの必須化に取り組む場合、不審なサインインがないか監視してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 不明なデバイスによるサインイン | 低 | サインイン ログ | DeviceDetail が空、単一要素認証、または信頼されていない場所からの場合 | 調査項目: 非準拠デバイスからのすべてのアクセス、MFA または信頼できる場所を持たないすべてのアクセス[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/SigninLogs/AnomalousSingleFactorSignin.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

#### LogAnalytics を使用してクエリを実行する

**非準拠デバイスによるサインイン**

```
SigninLogs
| where DeviceDetail.isCompliant == false
| where ConditionalAccessStatus == "success"
```

**不明なデバイスによるサインイン**

```

SigninLogs
| where isempty(DeviceDetail.deviceId)
| where AuthenticationRequirement == "singleFactorAuthentication"
| where ResultType == "0"
| where NetworkLocationDetails == "[]"
```

### 古くなったデバイス

古くなったデバイスには、一定期間にわたりサインインしなかったデバイスが含まれます。 ユーザーが新しいデバイスを入手するかデバイスを紛失した場合や、Microsoft Entra 参加済みデバイスがワイプまたは再プロビジョニングされた場合、デバイスは古くなったと見なされます。 また、ユーザーがテナントと関連付けられなくなった場合でも、デバイスは登録済みまたは参加済みの状態のままになる可能性があります。 古くなったデバイスは、そのプライマリ更新トークン (PRT) を使用できないように削除する必要があります。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 前回のサインイン日時 | 低 | Graph API | おおよその最後のサインイン日時 | Graph API または PowerShell を使用して、古くなったデバイスを特定し削除してください。 |

### BitLocker キーの取得

ユーザーのデバイスを侵害した攻撃者は、Microsoft Entra ID で [BitLocker](https://learn.microsoft.com/ja-jp/windows/security/operating-system-security/data-protection/bitlocker/bitlocker-device-encryption-overview-windows-10) キーを取得する可能性があります。 ユーザーがキーを取得することはめったにないため、このような事態について監視および調査する必要があります。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| キーの取得 | ミディアム | 監査ログ | OperationName == "BitLocker キーの読み取り" | 調査項目: キーを取得したユーザーによる他の異常な行動。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/AuditLogs/BitLockerKeyRetrieval.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

LogAnalytics で次のようなクエリを作成します。

```
AuditLogs
| where OperationName == "Read BitLocker key" 
```

### デバイス管理者ロール

[Microsoft Entra 参加済みデバイスのローカル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#microsoft-entra-joined-device-local-administrator)および[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)には、すべての Microsoft Entra 参加済みデバイスのローカル管理者権限が自動的に付与されます。 環境の安全を保つには、これらの権限を持つユーザーを監視することが重要です。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| グローバル管理者または Microsoft Entra 参加済みデバイスローカル管理者ロールに追加されたユーザー | 高 | 監査ログ | アクティビティの種類 = ロールへのメンバーの追加。 | 調査項目: これらの Microsoft Entra ロールに追加された新しいユーザー、マシンまたはユーザーによる後続の異常な動作。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/4ad195f4fe6fdbc66fb8469120381e8277ebed81/Detections/AuditLogs/UserAddedtoAdminRole.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

### Azure AD 以外からの仮想マシンへのサインイン

Windows または Linux 仮想マシン (VM) へのサインインについて、Microsoft Entra アカウント以外のアカウントによるサインインがないか監視してください。

#### LINUX への Microsoft Entra サインイン

組織は Linux への Microsoft Entra サインインを使用することで、Microsoft Entra アカウントを使用し Secure Shell プロトコル (SSH) 経由で Azure Linux VM にサインインできます。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| Azure AD アカウント以外からのサインイン (特に SSH 経由) | 高 | ローカル認証ログ | Ubuntu: /var/log/auth.log で SSH の使用状況を監視するRedHat: ‎/var/log/sssd/ で SSH の使用状況を監視する | 調査項目: [Azure AD 以外のアカウントが VM への接続に成功したことを示す](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-linux)エントリ。 次の例を参照してください。 |

Ubuntu の例:

May 9 23:49:39 ubuntu1804 aad\_certhandler[3915]: Version: 1.0.015570001; user: localusertest01 (5 月 9 日 23:49:39 ubuntu1804 aad\_certhandler[3915]: バージョン: 1.0.015570001; ユーザー: localusertest01)

May 9 23:49:39 ubuntu1804 aad\_certhandler[3915]: User 'localusertest01' is not an Microsoft Entra user; returning empty result. (5 月 9 日 23:49:39 ubuntu1804 aad\_certhandler[3915]: ユーザー 'localusertest01' は Microsoft Entra ユーザーではありません; 空の結果が返されました。)

May 9 23:49:43 ubuntu1804 aad\_certhandler[3916]: Version: 1.0.015570001; user: localusertest01 (5 月 9 日 23:49:43 ubuntu1804 aad\_certhandler[3916]: バージョン: 1.0.015570001; ユーザー: localusertest01)

May 9 23:49:39 ubuntu1804 aad\_certhandler[3915]: User 'localusertest01' is not an Microsoft Entra user; returning empty result. (5 月 9 日 23:49:39 ubuntu1804 aad\_certhandler[3915]: ユーザー 'localusertest01' は Microsoft Entra ユーザーではありません。空の結果が返されました。)

May 9 23:49:43 ubuntu1804 sshd[3909]: Accepted publicly for localusertest01 from 192.168.0.15 port 53582 ssh2: RSA SHA256:MiROf6f9u1w8J+46AXR1WmPjDhNWJEoXp4HMm9lvJAQ (5 月 9 日 23:49:43 ubuntu1804 sshd[3909]: 192.168.0.15 ポート 53582 ssh2 からの localusertest01 について公的に受け入れられました: RSA SHA256:MiROf6f9u1w8J+46AXR1WmPjDhNWJEoXp4HMm9lvJAQ)

May 9 23:49:43 ubuntu1804 sshd[3909]: pam\_unix(sshd:session): session opened for user localusertest01 by (uid=0). (5 月 9 日 23:49:43 ubuntu1804 sshd[3909]: pam\_unix(sshd:session): ユーザー localusertest01 に対してセッションが (uid=0) で開かれました。)

Linux VM へのサインインに対するポリシーを設定し、未承認のローカル アカウントが追加された Linux VM を検出してフラグを設定できます。 詳細については、「[Azure Policy を使用して、標準および評価コンプライアンスを確保する](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-vm-sign-in-azure-ad-linux)」を参照してください。

#### Windows Server への Microsoft Entra サインイン

組織は、Windows への Microsoft Entra サインインを使用することで、Microsoft Entra アカウントを使用しリモート デスクトップ プロトコル (RDP) 経由で Windows 2019 以降の Azure VM にサインインできます。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| Azure AD アカウント以外からのサインイン (特に RDP 経由) | 高 | Windows Server イベント ログ | Windows VM への対話型ログイン | イベント 528、ログオンの種類 10 (RemoteInteractive)。ターミナル サービスまたはリモート デスクトップ経由でユーザーがサインインしたときに表示されます。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/security-operations-infrastructure"} -->
## インフラストラクチャのための Microsoft Entra セキュリティ運用 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-infrastructure
- Service: entra / architecture
- Article date: 2022-09-06
- Summary: セキュリティの脅威を特定するために、インフラストラクチャ コンポーネントの監視とアラートを行う方法について説明します。

インフラストラクチャには、適切に構成されていないと脆弱性が発生するおそれがある多くのコンポーネントがあります。 インフラストラクチャの監視とアラートの戦略の一環として、次の領域においてイベントの監視とアラートを行ないます。

- 認証と承認
- ハイブリッド認証コンポーネント (例: フェデレーション サーバー)
- ポリシー
- Subscriptions

認証インフラストラクチャのコンポーネントを監視し、アラートを生成することが重要です。 セキュリティが侵害されると、環境全体が完全に侵害される可能性があります。 Microsoft Entra ID を使用する多くの企業は、ハイブリッド認証環境で運用しています。 これは、クラウドとオンプレミスの両方のコンポーネントを監視とアラートの戦略に含める必要があります。 ハイブリッド認証環境を使用すると、環境に別の攻撃ベクトルももたらされます。

すべてのコンポーネントおよびそれを管理するために用いられるアカウントは、コントロール プレーンあるいは階層 0 の資産と見なすことをお勧めします。 環境の設計と実装についてのガイダンスは、[特権資産のセキュリティ保護 (SPA)](https://learn.microsoft.com/ja-jp/security/compass/overview) に関する記事を参照してください。 このガイダンスには、Microsoft Entra テナントで使用される可能性があるハイブリッド認証コンポーネントのそれぞれに関する推奨事項が記載されています。

予期しないイベントや潜在的な攻撃を検出できるようにするための最初の手順は、ベースラインを確立することです。 この記事に記載されているすべてのオンプレミス コンポーネントについては、「[特権アクセスの展開](https://learn.microsoft.com/ja-jp/security/compass/privileged-access-deployment)」を参照してください。これは、特権資産のセキュリティ保護 (SPA) ガイダンスの一部です。

### 確認先

調査と監視に使用するログ ファイルは次のとおりです。

- [Microsoft Entra 監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)
- [サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)
- [Microsoft 365 監査ログ](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/auditing-solutions-overview)
- [Azure Key Vault ログ](https://learn.microsoft.com/ja-jp/azure/key-vault/general/logging?tabs=Vault)

Azure portal から、Microsoft Entra 監査ログを表示し、コンマ区切り値 (CSV) または JavaScript Object Notation (JSON) ファイルとしてダウンロードできます。 Azure portal には、Microsoft Entra ログを他のツールと統合する方法がいくつか用意されており、監視とアラートの自動化を強化することができます。

- **[Microsoft Sentinel](https://learn.microsoft.com/ja-jp/azure/sentinel/overview)** – セキュリティ情報イベント管理 (SIEM) 機能を備え、エンタープライズ レベルでインテリジェントにセキュリティを分析します。
- **[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure)** - Sigma は、ルールやテンプレートを記述するための進化し続けるオープン標準です。自動化された管理ツールでこれらのルールを使用して、ログ ファイルを解析できます。 推奨される検索条件に Sigma テンプレートが存在する場合は、Sigma リポジトリへのリンクを追加しました。 Sigma テンプレートは、Microsoft によって記述、テスト、管理されません。 リポジトリとテンプレートは、世界中の IT セキュリティ コミュニティによって作成および収集されています。
- **[Azure Monitor](https://learn.microsoft.com/ja-jp/azure/azure-monitor/overview)** - さまざまな条件に基づいて監視とアラートを自動化します。 ブックを作成または使用して、異なるソースのデータを結合できます。
- **[Azure Event Hubs](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-about)** と SIEM の統合 - Azure Event Hubs 統合を介して、Splunk、ArcSight、QRadar、Sumo Logic などの[他の SIEM と Microsoft Entra ログを統合できます](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub)。
- **[Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/cloud-app-security/what-is-cloud-app-security)** – アプリを検出し、アプリを管理し、すべてのアプリとリソースを制御し、クラウド アプリのコンプライアンス状況を確認できます。
- **[Microsoft Entra ID Protection を使用してワークロード ID を保護する](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk)** - サインイン動作とオフラインでの侵害の兆候からワークロード ID のリスクを検出するために使用します。

この記事の残りの部分では、何を監視とアラートの対象にすべきかについて説明します。 脅威の種類ごとに分類されています。 事前構築済みソリューションがある場合は、表の後にリンクを示しています。 それ以外の場合は、前述のツールを使用してアラートを作成できます。

### 認証インフラストラクチャ

オンプレミスとクラウドベースの両方のリソースとアカウントを含むハイブリッド環境では、Active Directory インフラストラクチャは認証スタックの重要な部分です。 スタックは攻撃のターゲットでもあるため、セキュリティで保護された環境を維持するように構成し、適切に監視する必要があります。 例に挙げた認証インフラストラクチャに使用される最新の攻撃の種類では、パスワード スプレーと Solorigate 手法が使用されます。 推奨される記事へのリンクを次に示します。

- [特権アクセスのセキュリティ保護の概要](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/overview) – この記事では、セキュリティで保護された特権アクセスを作成および維持するために、ゼロ トラスト手法を使用する最新手法の概要を説明します。
- [Microsoft Defender for Identity が監視するドメイン アクティビティ](https://learn.microsoft.com/ja-jp/defender-for-identity/monitored-activities) - この記事では、監視しアラートを設定するアクティビティの包括的な一覧を示します。
- [Microsoft Defender for Identity のセキュリティ アラート チュートリアル](https://learn.microsoft.com/ja-jp/defender-for-identity/understanding-security-alerts) - この記事では、セキュリティ アラート戦略の作成と実装に関するガイダンスを提供します。

認証インフラストラクチャの監視とアラートに焦点を当てた具体的な記事へのリンクを次に示します。

- [Microsoft Defender for Identity での横移動パスの理解と使用](https://learn.microsoft.com/ja-jp/defender-for-identity/use-case-lateral-movement-path) - 機密性の低いアカウントを使用して機密性の高いネットワーク アカウントにアクセスされたときの識別に役立つ検出方法。
- [Microsoft Defender for Identity でのセキュリティ アラートの処理](https://learn.microsoft.com/ja-jp/defender-for-identity/manage-security-alerts) - この記事では、アラートがログに記録された後に、アラートを確認して管理する方法について説明します。

具体的な項目について以下で説明します。

| [What to monitor] (監視対象) | リスク レベル | どこ | メモ |
| --- | --- | --- | --- |
| エクストラネットのロックアウトの傾向 | 高 | Microsoft Entra Connect ヘルス | エクストラネットのロックアウトの傾向を検出するためのツールと手法については、「[Microsoft Entra Connect Health を使用した AD FS の監視](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-adfs)」を参照してください。 |
| 失敗したサインイン | 高 | Connect Health ポータル | 危険な IP のレポートをエクスポートもしくはダウンロードし、「[危険な IP のレポート (パブリック レビュー)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-adfs-risky-ip)」にあるガイダンスに従って次のステップに進んでください。 |
| プライバシー準拠 | 低 | Microsoft Entra Connect ヘルス | 「[ユーザー プライバシーと Microsoft Entra Connect Health](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-health-user-privacy)」の記事を使用して Microsoft Entra Connect Health を構成し、データ コレクションと監視を無効化します。 |
| LDAP に対するブルート フォース攻撃の可能性 | ミディアム | Microsoft Defender for Identity | センサーを使用して、LDAP に対する潜在的なブルート フォース攻撃を検出できます。 |
| アカウント列挙の偵察 | ミディアム | Microsoft Defender for Identity | センサーを使用して、アカウント列挙攻撃による偵察を実行できます。 |
| Microsoft Entra ID と Azure AD FS の間の一般的な相関関係 | ミディアム | Microsoft Defender for Identity | Microsoft Entra ID と Azure AD FS 環境の間でアクティビティを関連付ける機能を使用します。 |

#### パススルー認証に対する監視

Microsoft Entra パススルー認証では、オンプレミスの Active Directory と照合してパスワードを直接検証することで、ユーザーをサインインさせます。

具体的な項目について以下で説明します。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| Microsoft Entra パススルー認証エラー | ミディアム | アプリケーションとサービス ログ\Microsoft\AzureAdConnect\AuthenticationAgent\Admin | AADSTS80001 - Unable to connect to Active Directory (Active Directory に接続できません) | エージェント サーバーが、パスワードを検証する必要のあるユーザーと同じ AD フォレストのメンバーであり、Active Directory に接続できることを確認します。 |
| Microsoft Entra パススルー認証エラー | ミディアム | アプリケーションとサービス ログ\Microsoft\AzureAdConnect\AuthenticationAgent\Admin | AADSTS8002 - A timeout occurred connecting to Active Directory (Active Directory への接続中にタイムアウトが発生しました) | Active Directory が使用可能で、エージェントからの要求に応答していることを確認します。 |
| Microsoft Entra パススルー認証エラー | ミディアム | アプリケーションとサービス ログ\Microsoft\AzureAdConnect\AuthenticationAgent\Admin | AADSTS80004 - The username passed to the agent was not valid (エージェントに渡されたユーザー名が無効です) | サインインしようとしているユーザーのユーザー名が正しいことを確認してください。 |
| Microsoft Entra パススルー認証エラー | ミディアム | アプリケーションとサービス ログ\Microsoft\AzureAdConnect\AuthenticationAgent\Admin | AADSTS80005 - Validation encountered unpredictable WebException (検証で予測外の WebException が発生しました) | 一時的なエラーです。 要求をやり直してください。 引き続きエラーが発生する場合は、Microsoft サポートに連絡してください。 |
| Microsoft Entra パススルー認証エラー | ミディアム | アプリケーションとサービス ログ\Microsoft\AzureAdConnect\AuthenticationAgent\Admin | AADSTS80007 - An error occurred communicating with Active Directory (Active Directory との通信中にエラーが発生しました) | Check the agent logs for more information and verify that Active Directory is operating as expected. (エージェント ログで詳細を確認し、Active Directory が期待通りに動作していることを確認してください。) |
| Microsoft Entra パススルー認証エラー | 高 | Win32 LogonUserA 関数 API | Logon events 4624(s): アカウントが正常にログオンしました- 次と関連 -4625(F): アカウントがログオンに失敗しました | 要求を認証しているドメイン コントローラーで、疑わしいユーザー名に使用します。 [LogonUserA 関数 (winbase.h)](https://learn.microsoft.com/ja-jp/windows/win32/api/winbase/nf-winbase-logonusera) におけるガイダンス |
| Microsoft Entra パススルー認証エラー | ミディアム | ドメイン コントローラーの PowerShell スクリプト | 表の後のクエリを参照してください。 | ガイダンスが必要な場合は、「[Microsoft Entra Connect: パススルー認証のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-pass-through-authentication)」にある情報を使用します。 |

```Kusto

<QueryList>

<Query Id="0" Path="Security">

<Select Path="Security">*[EventData[Data[@Name='ProcessName'] and (Data='C:\Program Files\Microsoft Azure AD Connect Authentication Agent\AzureADConnectAuthenticationAgentService.exe')]]</Select>

</Query>

</QueryList>
```

### 新しい Microsoft Entra テナントの作成の監視

組織では、組織テナントの ID によってアクションが開始されたときに、新しい Microsoft Entra テナントの作成を監視してアラートを生成することが必要になる場合があります。 このシナリオの監視では、エンド ユーザーによって作成され、アクセスできるテナントの数を確認できます。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| テナントからの ID を使用して、新しい Microsoft Entra テナントを作成します。 | ミディアム | Microsoft Entra 監査ログ | カテゴリ: ディレクトリ管理アクティビティ: 会社の作成 | ターゲットに、作成された TenantID が表示されます |

#### プライベート ネットワーク コネクタ

Microsoft Entra ID と Microsoft Entra アプリケーション プロキシを使用すると、シングル サインオン (SSO) エクスペリエンスがリモート ユーザーに提供されます。 仮想プライベート ネットワーク (VPN) またはデュアル ホーム サーバーとファイアウォール規則を使用しなくても、ユーザーはオンプレミス アプリケーションに安全に接続します。 Microsoft Entra プライベート ネットワーク コネクタ サーバーが侵害された場合、攻撃者によって SSO エクスペリエンスが変更されたり、公開されたアプリケーションへのアクセス権が変更されたりするおそれがあります。

アプリケーション プロキシの監視を構成するには、「[アプリケーション プロキシの問題とエラー メッセージのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-troubleshoot)」を参照してください。 情報を記録するデータ ファイルは、Applications and Services Logs\Microsoft\Microsoft Entra private network\Connector\Admin にあります。監査アクティビティの完全なリファレンス ガイドについては、「[Microsoft Entra 監査アクティビティのリファレンス](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-audit-activities)」を参照してください。 具体的な監視項目は次の通りです。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| Kerberos のエラー | ミディアム | 各種のツール | ミディアム | 「[アプリケーション プロキシの問題とエラー メッセージのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-troubleshoot)」の「Kerberos エラー」に Kerberos 認証エラーのガイダンスがあります。 |
| DC セキュリティに関する問題 | 高 | DC セキュリティ監査ログ | Event ID 4742(S): コンピューター アカウントが変更されましたおよびフラグ - 委任に対して信頼されている\- または -フラグ – 委任の認証に対して信頼されている | フラグの変更を調査します。 |
| Pass-the-Ticket と同様の攻撃 | 高 |  |  | 次のガイダンスに従ってください。[セキュリティ プリンシパルによる偵察 (LDAP) (外部 ID 2038)](https://learn.microsoft.com/ja-jp/defender-for-identity/reconnaissance-discovery-alerts)[チュートリアル:資格証明の侵害のアラート](https://learn.microsoft.com/ja-jp/defender-for-identity/credential-access-alerts)[Microsoft Defender for Identity で横移動パスを理解して使用する](https://learn.microsoft.com/ja-jp/defender-for-identity/understand-lateral-movement-paths)[エンティティのプロファイルを理解する](https://learn.microsoft.com/ja-jp/defender-for-identity/investigate-assets) |

#### レガシの認証設定

多要素認証 (MFA) を有効にするには、レガシ認証をブロックする必要もあります。 次に、環境を監視し、レガシ認証の使用に対してアラートを生成する必要があります。 POP、SMTP、IMAP、MAPI などのレガシ認証プロトコルでは MFA を強制できません。 そのため、これらのプロトコルは攻撃者が好むエントリ ポイントとなります。 レガシ認証をブロックするために使用できるツールの詳細については、「[組織内のレガシ認証をブロックする新しいツール](https://techcommunity.microsoft.com/t5/azure-active-directory-identity/new-tools-to-block-legacy-authentication-in-your-organization/ba-p/1225302)」を参照してください。

レガシ認証は、イベントの詳細の一部として Microsoft Entra サインイン ログに取得されます。 Azure Monitor ブックを使用して、レガシ認証の使用状況を識別できます。 詳細情報については、「[Microsoft Entra レポートに Azure Monitor ブックを使用する方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-workbooks)」の「[レガシ認証を使用したサインイン](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-workbooks)」を参照してください。 Microsoft Sentinel の安全でないプロトコル ブックを使用することもできます。 詳細については、[Microsoft Sentinel の安全でないプロトコル ブックの実装ガイド](https://techcommunity.microsoft.com/t5/azure-sentinel/azure-sentinel-insecure-protocols-workbook-implementation-guide/ba-p/1197564)を参照してください。 監視する具体的なアクティビティは次の通りです。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| レガシ認証 | 高 | Microsoft Entra サインイン ログ | ClientApp: POPClientApp: IMAPClientApp: MAPIClientApp: SMTPClientApp: ActiveSync go to EXOその他のクライアント = SharePoint と EWS | フェデレーション ドメイン環境では、失敗した認証は記録されないため、ログに表示されません。 |

### Microsoft Entra Connect

Microsoft Entra Connect は、オンプレミスとクラウドベースの Microsoft Entra 環境の間でアカウントと属性の同期を可能にする一元的な場所を提供します。 Microsoft Entra Connect は、ユーザーのハイブリッド ID の目標を達成するために設計されている Microsoft ツールです。 また、以下のような特徴があります。

- [パスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs) - ユーザーのオンプレミス AD パスワードのハッシュを Microsoft Entra ID と同期させるサインイン方法。
- [同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis) - ユーザー、グループ、およびその他のオブジェクトを作成する役割を果たします。 そして、オンプレミスのユーザーとグループの ID 情報をクラウド側と一致させます。 この同期にはパスワード ハッシュも含まれます。
- [正常性の監視](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect) - Microsoft Entra Connect Health は、堅牢な監視を提供したり、このアクティビティを表示するための Azure Portal 内の中央の場所を提供したりできます。

オンプレミス環境とクラウド環境の間で ID を同期させることで、オンプレミスとクラウドベースの環境に新しい攻撃面がもたらされます。 以下のことが推奨されます。

- Microsoft Entra Connect のプライマリ サーバーとステージング サーバーを、コントロール プレーンの階層 0 システムとして扱います。
- 環境内で各種のアカウントとその使用状況を管理する標準のポリシー セットに従います。
- Microsoft Entra Connect と Connect Health をインストールします。 これらは主に、環境の運用データを提供します。

Microsoft Entra Connect 操作のログ記録は、次のようにさまざまな方法で行われます。

- Microsoft Entra Connect ウィザードは、データを `\ProgramData\AADConnect` に記録します。 ウィザードが起動されるたびに、タイムスタンプ付きのトレース ログ ファイルが作成されます。 トレース ログは、解析用に Sentinel または他のサードパーティのセキュリティ情報とイベント管理 (SIEM) ツールにインポートできます。
- 一部の操作では、ログ情報を取り込むために PowerShell スクリプトを開始します。 このデータを収集するには、スクリプト ブロックのログが有効になっていることを確認する必要があります。

#### 構成の変更の監視

Microsoft Entra ID は Microsoft SQL Server データ エンジンまたは SQL を使用して Microsoft Entra Connect 構成情報を格納します。 そのため、構成に関連付けられているログ ファイルの監視と監査は、監視と監査の戦略に含める必要があります。 具体的には、監視とアラートの戦略に次の表を含めます。

| [What to monitor] (監視対象) | どこ | メモ |
| --- | --- | --- |
| MMS管理エージェント | SQL サービス監査レコード | 「[SQL Server 監査レコード](https://learn.microsoft.com/ja-jp/sql/relational-databases/security/auditing/sql-server-audit-records)」を参照してください |
| mms\_partition | SQL サービス監査レコード | 「[SQL Server 監査レコード](https://learn.microsoft.com/ja-jp/sql/relational-databases/security/auditing/sql-server-audit-records)」を参照してください |
| mms\_run\_profile | SQL サービス監査レコード | 「[SQL Server 監査レコード](https://learn.microsoft.com/ja-jp/sql/relational-databases/security/auditing/sql-server-audit-records)」を参照してください |
| MMSサーバー設定 (mms\_server\_configuration) | SQL サービス監査レコード | 「[SQL Server 監査レコード](https://learn.microsoft.com/ja-jp/sql/relational-databases/security/auditing/sql-server-audit-records)」を参照してください |
| mms\_synchronization\_rule | SQL サービス監査レコード | 「[SQL Server 監査レコード](https://learn.microsoft.com/ja-jp/sql/relational-databases/security/auditing/sql-server-audit-records)」を参照してください |

監視すべき構成情報とその方法の詳細については、次を参照してください。

- SQL Server の場合は、「[SQL Server 監査レコード](https://learn.microsoft.com/ja-jp/sql/relational-databases/security/auditing/sql-server-audit-records)」を参照してください。
- Microsoft Sentinel の場合、[Windows サーバーに接続してセキュリティ イベントを収集する](https://learn.microsoft.com/ja-jp/sql/relational-databases/security/auditing/sql-server-audit-records)方法に関するページを参照してください。
- Microsoft Entra Connect の構成と使用に関する詳細については、「[Microsoft Entra Connect とは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect)」を参照してください。

#### 同期の監視とトラブルシューティング

Microsoft Entra Connect の機能の 1 つに、ユーザーのオンプレミスのパスワードと Microsoft Entra ID の間のハッシュ同期があります。 パスワードが想定どおりに同期されない場合、その同期によって、一部またはすべてのユーザーに影響を及ぼすおそれがあります。 適切な操作を確認したり、問題のトラブルシューティングを行ったりするには、次の情報を使用します。

- ハッシュ同期の確認とトラブルシューティングについては、「[Microsoft Entra Connect Sync を使用したパスワード ハッシュ同期のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-password-hash-synchronization)」を参照してください。
- コネクタ スペースへの変更については、「[Microsoft Entra Connect オブジェクトと属性のトラブルシューティング](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/troubleshoot-aad-connect-objects-attributes)」を参照してください。

**監視に関する重要なリソース**

| [What to monitor] (監視対象) | リソース |
| --- | --- |
| ハッシュ同期の検証 | 「[Microsoft Entra Connect Sync を使用したパスワード ハッシュ同期のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-password-hash-synchronization)」を参照してください |
| コネクタ スペースの変更 | 「[Microsoft Entra Connect のオブジェクトと属性のトラブルシューティング](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/troubleshoot-aad-connect-objects-attributes)」を参照してください |
| 構成した規則に対する変更 | 変更の監視: フィルター処理、ドメインと OU、属性、グループベースの変更 |
| SQL と MSDE の変更 | ログ パラメーターの変更とカスタム関数の追加 |

**以下を監視します**。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| スケジューラの変更 | 高 | PowerShell | Set-ADSyncScheduler | スケジュールへの変更を検索する |
| スケジュールされたタスクの変更 | 高 | Microsoft Entra 監査ログ | Activity = 4699(S): スケジュールされたタスクが削除されました\- または -Activity = 4701(s): スケジュールされたタスクが無効化されました\- または -Activity = 4702(s): スケジュールされたタスクが更新されました | すべてを監視する |

- PowerShell スクリプト操作のログの詳細については、PowerShell リファレンス ドキュメントの一部である「[スクリプト ブロックのログ記録を有効にする](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_logging_windows)」を参照してください。
- Splunk による解析のために PowerShell のログを構成する方法の詳細については、「[Get Data into Splunk User Behavior Analytics](https://docs.splunk.com/Documentation/UBA/5.0.4.1/GetDataIn/AddPowerShell)」を参照してください。

#### シームレス シングル サインオンの監視

Microsoft Entra シームレス シングル サインオン (シームレス SSO) により、ユーザーは企業ネットワークにつながっている会社のデスクトップを使用するときに自動でサインインできます。 シームレス SSO により、ユーザーは、他のオンプレミス コンポーネントを使用せずに、クラウド ベースのアプリケーションに簡単にアクセスできるようになります。 SSO では、Microsoft Entra Connect によって提供されるパススルー認証とパスワード ハッシュ同期機能を使用します。

シングル サインオンと Kerberos アクティビティの監視は、資格情報の盗用に関する一般的な攻撃パターンを検出するのに役立ちます。 次の情報を使用して監視してください。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| SSO および Kerberos 検証の失敗に関連するエラー | ミディアム | Microsoft Entra サインイン ログ |  | シングル サインオンに関するエラー コードの一覧は、[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-sso)に関する記事を参照してください。 |
| エラーのトラブルシューティングに関するクエリ | ミディアム | PowerShell | 表の下にあるクエリを参照してください。 SSO を有効化してフォレストにチェックインします。 | SSO を有効化してフォレストにチェックインします。 |
| Kerberos 関連のイベント | 高 | Microsoft Defender for Identity の監視 |  | 「[Microsoft Defender for Identity の横移動パス (LMP)](https://learn.microsoft.com/ja-jp/defender-for-identity/understand-lateral-movement-paths)」で利用可能なガイダンスを確認します |

```kusto
<QueryList>

<Query Id="0" Path="Security">

<Select Path="Security">*[EventData[Data[@Name='ServiceName'] and (Data='AZUREADSSOACC$')]]</Select>

</Query>

</QueryList>
```

### パスワード保護ポリシー

Microsoft Entra パスワード保護をデプロイする場合、監視とレポートは重要なタスクです。 以下のリンクでは、各サービスが情報をログに記録する場所や、Microsoft Entra パスワード保護の使用についてレポートを作成する方法など、さまざまな監視手法を理解できるように詳しく説明します。

ドメイン コントローラー (DC) エージェントとプロキシ サービスは両方とも、イベント ログ メッセージを記録します。 以下で説明するすべての PowerShell コマンドレットは、プロキシ サーバーでのみ使用できます (AzureADPasswordProtection PowerShell モジュールを参照)。 DC エージェント ソフトウェアでは、PowerShell モジュールはインストールされません。

オンプレミスのパスワード保護の計画と実装の詳細については、「[オンプレミスの Microsoft Entra パスワード保護を計画してデプロイする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-deploy)」を参照してください。 監視の詳細については、「[オンプレミスの Microsoft Entra パスワード保護を監視する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-ban-bad-on-premises-monitor)」を参照してください。 各ドメイン コントローラーでは、DC エージェント サービス ソフトウェアによって、各個人のパスワード検証操作の結果 (およびその他の状態) が次のローカルのイベント ログに書き込まれます。

- \Applications and Services Logs\Microsoft\AzureADPasswordProtection\DCAgent\Admin
- \アプリケーションとサービスログ\Microsoft\AzureADパスワード保護\DCAgent\運用
- \Applications and Services Logs\Microsoft\AzureADPasswordProtection\DCAgent\Trace

DC エージェント管理ログは、ソフトウェアの動作に関する情報の主要なソースです。 既定では、トレース ログはオフであり、データをログに記録する前に有効にする必要があります。 アプリケーション プロキシの問題とエラー メッセージのトラブルシューティングを行う場合、詳しい情報は、「[Microsoft Entra アプリケーション プロキシをトラブルシューティングする](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-troubleshoot)」で確認できます。 これらのイベントの情報は次に記録されます。

- Applications and Services Logs\Microsoft\Microsoft Entra private network\Connector\Admin
- Microsoft Entra 監査ログ、カテゴリ アプリケーション プロキシ

Microsoft Entra 監査アクティビティの完全なリファレンスについては、[Microsoft Entra 監査アクティビティ リファレンス](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-audit-activities)に関する記事で確認できます。

### 条件付きアクセス

Microsoft Entra ID では、条件付きアクセス ポリシーを構成することでリソースへのアクセスを保護できます。 IT 管理者は、リソースが保護されているようにするため、条件付きアクセス ポリシーが予期したとおりに機能することを確認する必要があります。 条件付きアクセス サービスに対する変更の監視とアラートにより、データへのアクセスのために組織で定義したポリシーが適用されるようになります。 Microsoft Entra は、条件付きアクセスに変更が加えられたときにログ記録し、ポリシーが想定される範囲を網羅していることを確認するためのワークブックも提供します。

**ワークブックのリンク**

- [条件付きアクセスに関する分析情報とレポート](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-insights-reporting)
- [条件付きアクセス ギャップ分析ブック](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-conditional-access-gap-analyzer)

次の情報を使用して、条件付きアクセス ポリシーに対する変更を監視します。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 承認されていないアクターによって作成された新しい条件付きアクセス ポリシー | ミディアム | Microsoft Entra 監査ログ | アクティビティ: 条件付きアクセス ポリシーを追加するカテゴリ: Policy開始者 (アクター): ユーザー プリンシパル名 | 条件付きアクセスの変更を監視し、アラートを生成します。 開始者 (アクター): は条件付きアクセスに変更を加えるために承認されていますか?[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/ConditionalAccessPolicyModifiedbyNewUser.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 承認されていないアクターによって削除された条件付きアクセス ポリシー | ミディアム | Microsoft Entra 監査ログ | アクティビティ: 条件付きアクセス ポリシーを削除するカテゴリ: Policy開始者 (アクター): ユーザー プリンシパル名 | 条件付きアクセスの変更を監視し、アラートを生成します。 開始者 (アクター): は条件付きアクセスに変更を加えるために承認されていますか?[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/ConditionalAccessPolicyModifiedbyNewUser.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 承認されていないアクターによって更新された条件付きアクセス ポリシー | ミディアム | Microsoft Entra 監査ログ | アクティビティ: 条件付きアクセス ポリシーを更新するカテゴリ: Policy開始者 (アクター): ユーザー プリンシパル名 | 条件付きアクセスの変更を監視し、アラートを生成します。 開始者 (アクター): は条件付きアクセスに変更を加えるために承認されていますか?変更されたプロパティを確認し、"古い" 値と "新しい" ものを比較します[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/ConditionalAccessPolicyModifiedbyNewUser.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 重要な条件付きアクセス ポリシーのスコープを設定するために使用するグループからのユーザーの削除 | ミディアム | Microsoft Entra 監査ログ | アクティビティ: グループからメンバーを削除するカテゴリ: GroupManagementターゲット: ユーザー プリンシパル名 | 重要な条件付きアクセス ポリシーのスコープを設定するために使用されるグループの監視とアラート。"ターゲット" は削除されたユーザーです。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 重要な条件付きアクセス ポリシーのスコープを設定するために使用するグループへのユーザーの追加 | 低 | Microsoft Entra 監査ログ | アクティビティ: グループにメンバーを追加するカテゴリ: GroupManagementターゲット: ユーザー プリンシパル名 | 重要な条件付きアクセス ポリシーのスコープを設定するために使用されるグループの監視とアラート。"ターゲット" は追加されたユーザーです。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/security-operations-introduction"} -->
## Microsoft Entra セキュリティ運用ガイド - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-introduction
- Service: entra / architecture
- Article date: 2022-09-06
- Summary: Microsoft Entra ID でのアカウント、アプリケーション、デバイス、インフラストラクチャに関するセキュリティの問題を監視、特定、警告する方法について説明します。

Microsoft では、コントロール プレーンとして ID を利用した[多層防御](https://aka.ms/Zero-Trust)原則により、実績のある[ゼロ トラスト セキュリティ](https://www.cisa.gov/sites/default/files/recommended_practices/NCCIC_ICS-CERT_Defense_in_Depth_2016_S508C.pdf)へのアプローチを成功させてきました。 組織はスケール、コスト削減、セキュリティを追求し、ハイブリッド ワークロード環境を受け入れ続けています。 Microsoft Entra ID は、ID 管理の戦略において非常に重要な役割を果たします。 最近では、ID とセキュリティの侵害に関するニュースにより、企業の IT 部門は、ID セキュリティ態勢を、防御的セキュリティ成功の指標として捉えるようになりました。

さらに組織はオンプレミスとクラウドのアプリケーションを組み合わせて使用する必要があり、ユーザーはそれらのアプリケーションに、オンプレミスとクラウド専用の両方のアカウントでアクセスします。 オンプレミスとクラウドの両方でユーザー、アプリケーション、デバイスを管理するのは困難なシナリオです。

### ハイブリッド ID

Microsoft Entra ID では、場所に関係なく、すべてのリソースに対する認証と承認を行うための、共通のユーザー ID を作成します。 これを*ハイブリッド ID* と呼んでいます。

Microsoft Entra でハイブリッド ID を実現するために、お使いのシナリオに応じて、3 つの認証方法のうち 1 つを使用できます。 次に 3 つの方法を示します。

- [パスワード ハッシュの同期 (PHS)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs)
- [パススルー認証 (PTA)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)
- [フェデレーション (AD FS)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed)

現在のセキュリティ操作を監査したり、または Azure 環境のセキュリティ運用を確立したりする際には、次のことをお勧めします。

- Microsoft セキュリティ ガイダンスの特定の部分を読み、クラウドベースまたはハイブリッド Azure 環境のセキュリティ保護に関する知識のベースラインを確立します。
- アカウントとパスワードの戦略と認証方法を監査し、最も一般的な攻撃ベクトルを抑止します。
- セキュリティ上の脅威を示す可能性があるアクティビティについて、継続的な監視とアラートを行うための戦略を作成します。

#### 対象者

Microsoft Entra SecOps ガイドは、より高度な ID セキュリティ構成と監視プロファイルを通じて脅威に対抗する必要がある、企業の IT ID セキュリティ運用チームおよび管理サービス プロバイダーを対象としています。 このガイドは、セキュリティ オペレーション センター (SOC) の防御と侵入テストのチームに対し、ID のセキュリティ態勢の改善および維持についてアドバイスする、IT 管理者や ID アーキテクトにとって特に有益です。

#### Scope

この概要では、事前にお読みいただきたい推奨文書やパスワードの監査と戦略に関する推奨事項について説明します。 またこの記事では、ハイブリッド Azure 環境および完全なクラウドベースの Azure 環境で使用できるツールの概要についても説明します。 最後に、監視、アラート、およびセキュリティ情報イベント管理 (SIEM) の戦略と環境を構成するために使用できるデータ ソースの一覧を提供します。 このガイドの残りの部分では、次の領域における監視およびアラートの戦略について説明します。

- [ユーザー アカウント](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-user-accounts)。 管理者特権を持たない、非特権ユーザー アカウント特有のガイダンス。異常なアカウントの作成と使用、異常なサインインなどが含まれます。
- [特権アカウント](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-privileged-accounts)。 管理タスクを実行するための昇格されたアクセス許可を持つ特権ユーザー アカウント特有のガイダンス。 タスクには、Microsoft Entra ロールの割り当て、Azure リソース ロールの割り当て、Azure リソースとサブスクリプションのアクセス管理が含まれます。
- [Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-privileged-identity-management)。 PIM を使用してリソースへのアクセスを管理、制御、監視する方法に特有のガイダンス。
- [アプリケーション](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-applications)。 アプリケーションの認証を提供するために使用されるアカウント特有のガイダンス。
- [デバイス](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-devices)。 ポリシーの外部で登録または参加したデバイスの監視とアラート、非準拠の使用、デバイス管理ロールの管理、仮想マシンへのサインイン特有のガイダンス。
- [[インフラストラクチャ](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-infrastructure)]。 ハイブリッド環境および純粋なクラウドベースの環境に対する脅威の監視とアラート特有のガイダンス。

### 重要なリファレンス コンテンツ

Microsoft には、お客様のニーズに合わせて IT 環境をカスタマイズできる製品とサービスが多数用意されています。 ご自身の運用環境に関する次のガイダンスを確認することをお勧めします。

- Windows オペレーティング システム

    - [Windows 10 v1909 と Windows Server v1909 のセキュリティ ベースライン (最終)](https://techcommunity.microsoft.com/t5/microsoft-security-baselines/security-baseline-final-for-windows-10-v1909-and-windows-server/ba-p/1023093)
    - [Windows 11 用のセキュリティ ベースライン](https://techcommunity.microsoft.com/t5/microsoft-security-baselines/windows-11-security-baseline/ba-p/2810772)
    - [Windows Server 2022 用のセキュリティ ベースライン](https://techcommunity.microsoft.com/t5/microsoft-security-baselines/windows-server-2022-security-baseline/ba-p/2724685)
- オンプレミス環境

    - [Microsoft Defender for Identity のアーキテクチャ](https://learn.microsoft.com/ja-jp/defender-for-identity/architecture)
    - [クイックスタート: Microsoft Defender for Identity を Active Directory に接続する](https://learn.microsoft.com/ja-jp/defender-for-identity/install-step2)
    - [Microsoft Defender for Identity の Azure セキュリティ ベースライン](https://learn.microsoft.com/ja-jp/security/benchmark/azure/baselines/defender-for-identity-security-baseline)
    - [Active Directory の侵害の兆候を監視する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/security-best-practices/monitoring-active-directory-for-signs-of-compromise)
- クラウドベースの Azure 環境

    - [Microsoft Entra のサインイン ログを使用してサインインを監視する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)
    - [Azure portal でアクティビティ レポートを監査する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)
    - [Microsoft Entra ID 保護を使用してリスクを調査する](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)
    - [Microsoft Entra ID 保護 データを Microsoft Sentinel に接続する](https://learn.microsoft.com/ja-jp/azure/sentinel/data-connectors/azure-active-directory-identity-protection)
- Active Directory Domain Services (AD DS)

    - [監査ポリシーの推奨事項](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/security-best-practices/audit-policy-recommendations)
- Active Directory フェデレーション サービス (AD FS)

    - [AD FS のトラブルシューティング - イベントとログの監査](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/troubleshooting/ad-fs-tshoot-logging)

### データ ソース

調査と監視に使用するログ ファイルは次のとおりです。

- [Microsoft Entra 監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)
- [サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)
- [Microsoft 365 監査ログ](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/auditing-solutions-overview)
- [Azure Key Vault ログ](https://learn.microsoft.com/ja-jp/azure/key-vault/general/logging?tabs=Vault)

Azure portal から Microsoft Entra 監査ログを表示できます。 コンマ区切り値 (CSV) または JavaScript Object Notation (JSON) ファイルとしてログをダウンロードします。 Azure portal には、監視とアラートの自動化を強化できる他のツールと Microsoft Entra ログを統合する方法がいくつかあります:

- **[Microsoft Sentinel](https://learn.microsoft.com/ja-jp/azure/sentinel/overview)** - セキュリティ情報イベント管理 (SIEM) 機能を備え、エンタープライズ レベルでインテリジェントにセキュリティを分析できます。
- **[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure)** - Sigma は、自動化された管理ツールがログ ファイルの解析に使用できるルールとテンプレートを記述するための、進化するオープン標準です。 推奨される検索条件に Sigma テンプレートが存在する場合は、Sigma リポジトリへのリンクを追加しました。 Sigma テンプレートは、Microsoft によって記述、テスト、管理されません。 その代わり、リポジトリとテンプレートは、世界中の IT セキュリティ コミュニティによって作成および収集されます。
- **[Azure Monitor](https://learn.microsoft.com/ja-jp/azure/azure-monitor/overview)** – さまざまな条件に基づいて監視とアラートを自動化します。 ワークブックを作成または使用して、異なるソースからデータを結合できます。
- **[Azure Event Hubs](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-about)** と SIEM の統合。 Azure Event Hubs 統合を介して、Splunk、ArcSight、QRadar、Sumo Logic などの他の SIEM に Microsoft Entra ID ログを統合できます。 詳細については、「[Azure イベント ハブへのMicrosoft Entra ログのストリーム配信](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub)」を参照してください。
- **[Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/cloud-app-security/what-is-cloud-app-security)** - アプリを検出して管理、すべてのアプリとリソースを制御、クラウド アプリのコンプライアンス状況を確認できるようにします。
- **[Microsoft Entra ID 保護 でワークロード ID をセキュリティで保護](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk)** - ログイン動作間とオフラインの侵害インジケーターにおけるワークロード ID のリスクを検出するために使用されます。

監視とアラートの対象の多くは、条件付きアクセス ポリシーの影響を受けます。 条件付きアクセスに関する分析情報とレポート ブックを使用して、1 つまたは複数の条件付きアクセス ポリシーがサインインに及ぼしている影響と、デバイスの状態などのポリシーの結果を確認できます。 このブックでは、影響の概要を確認し、指定期間における影響を特定できます。 また、このブックを使用して、特定のユーザーのサインインを調査することもできます。 詳細については、「[条件付きアクセスに関する分析情報とレポート](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-insights-reporting)」をご覧ください。

この記事の残りの部分では、何を監視とアラートの対象にすべきかについて説明します。 特定の事前構築済みソリューションがある場合は、リンクを示すか、表の後にサンプルを提供しています。 それ以外の場合は、前述のツールを使用してアラートを作成できます。

- **[ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)**は、調査に役立つ 3 つの主要なレポートが生成されます。
- **危険なユーザー**には、リスクのあるユーザーに関する情報、検出の詳細、すべての危険なログインの履歴、リスク履歴が含まれています。
- **[危険なサインイン]** には、疑わしい状況を示す可能性のあるサインインの状況に関する情報が含まれています。 このレポートの情報を調査するための追加情報については、「[方法: リスクの調査](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)」をご覧ください。
- **リスク検出**には、サインインとユーザーのリスクを通知する Microsoft Entra ID 保護 によって検出されたリスク シグナルに関する情報が含まれます。 詳細については、「[ユーザー アカウントの Microsoft Entra セキュリティ オペレーション ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-user-accounts)」を参照してください。

詳細については、[[Microsoft Entra ID 保護の概要](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)]を参照してください。

#### ドメイン コントローラー監視のデータ ソース

最適な結果を得るために、Microsoft Defender for Identity を使用してドメイン コントローラーを監視することをお勧めします。 このアプローチにより、最適な検出と自動化の機能を使用できるようになります。 これらのリソースのガイダンスに従います。

- [Microsoft Defender for Identity のアーキテクチャ](https://learn.microsoft.com/ja-jp/defender-for-identity/architecture)
- [クイックスタート: Microsoft Defender for Identity を Active Directory に接続する](https://learn.microsoft.com/ja-jp/defender-for-identity/directory-service-accounts)

Microsoft Defender for Identity を使用する予定がない場合は、次のいずれかの方法でドメイン コントローラーを監視します。

- イベント ログ メッセージ。 「[Active Directory の侵害の兆候を監視する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/security-best-practices/monitoring-active-directory-for-signs-of-compromise)」をご覧ください。
- PowerShell コマンドレット。 「[ドメイン コントローラーの展開のトラブルシューティング](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/deploy/troubleshooting-domain-controller-deployment)」をご覧ください。

### ハイブリッド認証のコンポーネント

Azure ハイブリッド環境の一部として、次の項目をベースラインとして使用し、監視とアラートの戦略に含める必要があります。

- **PTA エージェント** – パススルー認証エージェントは、パススルー認証を有効にするために使用され、オンプレミスにインストールされます。 エージェントのバージョンの確認と次の手順の詳細については、「[Microsoft Entra パススルー認証エージェント: バージョン リリース履歴](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-pta-version-history)」を参照してください。
- **AD FS/WAP** - Active Directory フェデレーション サービス (AD FS) (AD FS) と Web アプリケーション プロキシ (WAP) を使用すると、セキュリティとエンタープライズの境界を越えてデジタル ID と権利権を安全に共有できます。 セキュリティのベストプラクティスについては、[Active Directoryフェデレーションサービスを保護するためのベストプラクティス](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/deployment/best-practices-securing-ad-fs)を参照してください。
- **Microsoft Entra Connect Health エージェント** – Microsoft Entra Connect Health の通信リンクを提供するために使用されるエージェントです。 エージェントのインストールについては、「[Microsoft Entra Connect Health エージェントのインストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-agent-install)」をご覧ください。
- **Microsoft Entra Connect Sync Engine** - オンプレミスのコンポーネント。同期エンジンとも呼ばれます。 この機能の詳細については、「[Microsoft Entra Connect 同期サービスの機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-syncservice-features)」をご覧ください。
- **パスワード保護 DC エージェント** – Azure パスワード保護 DC エージェントは、イベント ログ メッセージの監視とレポート作成に使用されます。 情報については、｢[Active Directory Domain Services にオンプレミスの Microsoft Entra パスワード保護を適用する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-on-premises)」を参照してください。
- **パスワード フィルター DLL** – DC エージェントのパスワード フィルター DLL は、オペレーティング システムからユーザーのパスワード検証要求を受け取ります。 このフィルターは、DC でローカルで実行されている DC エージェント サービスにそれらを転送します。 DLL の使用の詳細については、「[Active Directory Domain Services にオンプレミスの Microsoft Entra パスワード保護を適用する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-on-premises)」をご覧ください。
- **パスワード ライトバック エージェント** - パスワード ライトバックは、[Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity) により有効になる機能で、クラウド内でのパスワード変更を既存のオンプレミスのディレクトリにリアルタイムで書き戻せるようにします。 この機能の詳細については、「[Microsoft Entra ID でのセルフサービス パスワード リセットによる書き戻しのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-writeback)」を参照してください。
- **Microsoft Entra プライベート ネットワーク コネクタ** - オンプレミスに配置され、アプリケーション プロキシ サービスへの送信接続を容易にする軽量のエージェント。 詳細については、「 [Microsoft Entra プライベート ネットワーク コネクタ](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors)」を参照してください。

### クラウドベース認証のコンポーネント

Azure クラウドベース環境の一部として、次の項目をベースラインとして使用し、監視とアラートの戦略に含める必要があります。

- **Microsoft Entra アプリケーション プロキシ** – このクラウド サービスは、オンプレミスの Web アプリケーションへのセキュリティで保護されたリモート アクセスを提供します。 詳細については、「[Microsoft Entra アプリケーション プロキシからのオンプレミス アプリケーションへのリモート アクセス](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors)」を参照してください。
- **Microsoft Entra Connect** - Microsoft Entra Connect ソリューションに使用されるサービス。 詳細については、「[Microsoft Sentinel Connect とは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect)」を参照してください。
- **Microsoft Entra Connect Health** – Service Health は、ユーザーが使用しているリージョン内の Azure サービスの正常性を追跡するカスタマイズ可能なダッシュボードを提供します。 詳細については、「[Microsoft Entra Connect Health](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect)」を参照してください。
- **Microsoft Entra 多要素認証 (MFA)** – MFA では、ユーザーは認証のために複数の形式の証明を提供する必要があります。 このアプローチにより、環境をセキュリティで保護するためのプロアクティブな最初の手順が提供されます。 詳細については、「[Microsoft Entra MFA を取得する方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)」を参照してください。
- **動的グループ** - Microsoft Entra 管理者向けセキュリティ グループ メンバーシップの動的構成では、ユーザー属性に基づいて Microsoft Entra で作成されるグループを設定する規則を設定できます。 詳しくは、「[動的グループと Microsoft Entra B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/use-dynamic-groups)」を参照してください。
- **条件付きアクセス** - 条件付きアクセスは、Microsoft Entra ID で使用されるツールです。このツールによって、シグナルをまとめ、決定を行い、組織のポリシーを適用することができます。 条件付きアクセスは、新しい ID ドリブン コントロール プレーンの中心になるものです。 詳細については、「[条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)」を参照してください。
- **Microsoft Entra ID 保護** – 組織が ID ベースのリスクの検出と修復の自動化、ポータルでデータを使用するリスクの調査、SIEM にリスク検出データのエクスポートを実行できるようにするツールです。 詳細については、[[Microsoft Entra ID 保護の概要](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)]を参照してください。
- **グループベースのライセンス** – ライセンスは、ユーザーに直接割り当てるのではなく、グループに割り当てることができます。 ユーザーのライセンスの割り当て状態に関する情報は Microsoft Entra ID に格納されます。
- **プロビジョニング サービス** - プロビジョニングとは、ユーザーがアクセスする必要のあるクラウド アプリケーションのユーザー ID とロールを作成することです。 自動プロビジョニングには、ユーザー ID の作成に加えて、状態または役割が変化したときのユーザー ID のメンテナンスおよび削除が含まれます。 詳細については、「[Microsoft Entra ID でのアプリケーションのプロビジョニングのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works)」をご覧ください。
- **Graph API** – RESTful Web API である Microsoft Graph API を使用すると、Microsoft Cloud サービスのリソースにアクセスできます。 アプリを登録し、ユーザーまたはサービスのための認証トークンを取得した後、Microsoft Graph API に要求を行うことができます。 詳しくは、「[Microsoft Graph の概要](https://learn.microsoft.com/ja-jp/graph/overview)」をご覧ください。
- **Domain Service** – Microsoft Entra Domain Services (AD DS) では、ドメイン参加やグループ ポリシーなどのマネージド ドメイン サービスが提供されます。 詳細については、「[Microsoft Entra Domain Services とは](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/overview)」を参照してください。
- **Azure Resource Manager** – Azure Resource Manager は、Azure のデプロイおよび管理サービスです。 お使いの Azure アカウント内のリソースを作成、更新、および削除できる管理レイヤーを提供します。 詳細については、「[Azure Resource Manager とは](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview)」をご覧ください。
- **マネージド ID** – マネージド ID により、開発者は資格情報を管理する必要がなくなります。 マネージド ID は、Microsoft Entra 認証をサポートするリソースに接続するときに使う ID をアプリケーションに提供します。 詳細については、「[Azure リソース用マネージド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)」を参照してください。
- **Privileged Identity Management** - PIM は Microsoft Entra ID のサービスで、これにより、お客様の組織内の重要なリソースへのアクセスを管理、制御、監視できるようになります。 詳しくは、「[Microsoft Entra Privileged Identity Management とは](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)」を参照してください。
- **アクセス ビュー** - Microsoft Entra アクセス レビューを組織で使用することにより、グループ メンバーシップ、エンタープライズ アプリケーションへのアクセス、およびロールの割り当てを効率的に管理できます。 ユーザーのアクセスを定期的にレビューし、適切なユーザーのみが継続的なアクセス権を持っていることを確認できます。 詳細については、「[Microsoft Entra アクセス レビューとは](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)」を参照してください。
- **エンタイトルメント管理** - Microsoft Entra エンタイトルメント管理は、[ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview)機能です。 組織は、アクセス要求ワークフロー、アクセス割り当て、レビュー、期限切れ処理を自動化することで、ID とアクセスのライフサイクルを大規模に管理できます。 詳細については、「[Microsoft Entra エンタイトルメント管理とは](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)」を参照してください。
- **アクティビティ ログ** - アクティビティ ログは Azure [プラットフォームのログ](https://learn.microsoft.com/ja-jp/azure/azure-monitor/essentials/platform-logs-overview)であり、サブスクリプションレベルのイベントの分析情報が提供されます。 このログには、リソースが変更されたときや仮想マシンが起動されたときなどの情報が含まれます。 詳細については、「[Azure アクティビティ ログ](https://learn.microsoft.com/ja-jp/azure/azure-monitor/essentials/activity-log)」をご覧ください。
- **セルフサービス パスワード リセット サービス** - Microsoft Entra セルフサービス パスワード リセット (SSPR) により、ユーザーは自分のパスワードを変更またはリセットできます。 管理者またはヘルプ デスクは必要ありません。 詳細については、「[動作のしくみ: Microsoft Entra のセルフサービス パスワード リセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks)」を参照してください。
- **デバイス サービス** – デバイス ID 管理は、[デバイス ベースの条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant)の基盤です。 デバイス ベースの条件付きアクセス ポリシーにより、環境内のリソースへのアクセスを確実にマネージド デバイスでのみ可能にすることができます。 詳細については、「[デバイス ID とは](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)」をご覧ください。
- **セルフサービス グループ管理** – Microsoft Entra ID では、ユーザーが独自のセキュリティ グループまたは Microsoft 365 グループを作成して管理できます。 グループの所有者は、メンバーシップ要求を承認または拒否できます。また、グループ メンバーシップの制御を委任できます。 セルフサービスによるグループ管理機能は、メールを有効にしたセキュリティ グループまたは配布リストでは使用できません。 詳細については、「[Microsoft Entra ID でのセルフサービス グループ管理の設定](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management)」をご覧ください。
- **リスク検出** - リスクが検出されたときトリガーされるその他のリスクに関する情報や、サインインの場所などの他の関連情報、および Microsoft Defender for Cloud Apps からの詳細情報が含まれます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/security-operations-privileged-accounts"} -->
## Microsoft Entra ID での特権アカウントのためのセキュリティ運用 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-privileged-accounts
- Service: entra / architecture
- Article date: 2022-09-06
- Summary: ベースラインと、Microsoft Entra ID の特権アカウントに関する潜在的なセキュリティの問題を監視およびアラートする方法について説明します。

ビジネス資産のセキュリティは、IT システムを管理する特権アカウントの整合性に依存します。 サイバー攻撃者は、資格情報窃盗攻撃や他の手法を使用して特権アカウントを標的にし、機密データにアクセスします。

従来、組織のセキュリティは、セキュリティ境界としてネットワークの入口と出口のポイントに重点を置いていました。 しかし、インターネット上のサービスとしてのソフトウェア (SaaS) アプリケーションと個人用デバイスでは、この方法があまり効果的ではありません。

Microsoft Entra ID は、ID およびアクセス管理 (IAM) をコントロール プレーンとして使用します。 組織の ID 層では、特権管理者ロールに割り当てられているユーザーが管理を行います。 アクセスに使用されるアカウントは、環境がオンプレミス、クラウド、またはハイブリッド環境のいずれであっても、保護しなければなりません。

お客様は、オンプレミスの IT 環境におけるすべての層のセキュリティに全責任を負います。 Azure サービスを使用する場合、防止と対応は、Microsoft (クラウド サービス プロバイダー) とお客様 (あなた) の共同責任となります。

- 共有責任モデルの詳細については、「[クラウドにおける共有責任](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/shared-responsibility)」を参照してください。
- 特権を持つユーザーのアクセスをセキュリティで保護する方法の詳細については、「[Microsoft Entra ID でのハイブリッドおよびクラウド デプロイのための特権アクセスのセキュリティ保護](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-planning)」を参照してください。
- 特権 ID の主要概念に関するさまざまな動画、ハウツーガイド、およびコンテンツについては、[Privileged Identity Management のドキュメント](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/)を参照してください。

### 監視するログ ファイル

調査と監視に使用するログ ファイルは次のとおりです。

- [Microsoft Entra 監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)
- [Microsoft 365 監査ログ](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/auditing-solutions-overview)
- [Azure Key Vault 分析情報](https://learn.microsoft.com/ja-jp/azure/key-vault/key-vault-insights-overview)

Azure portal から、Microsoft Entra 監査ログを表示し、コンマ区切り値 (CSV) または JavaScript Object Notation (JSON) ファイルとしてダウンロードできます。 Azure portal には、Microsoft Entra ログを他のツールと統合する方法がいくつか用意されており、監視とアラートの自動化を強化することができます。

- **[Microsoft Sentinel](https://learn.microsoft.com/ja-jp/azure/sentinel/overview)**. セキュリティ情報イベント管理 (SIEM) 機能を備え、エンタープライズ レベルでのインテリジェントなセキュリティ分析を実現します。
- **[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure)** - Sigma は、ルールやテンプレートを記述するための進化し続けるオープン標準です。自動化された管理ツールでこれらのルールを使用して、ログ ファイルを解析できます。 推奨される検索条件に Sigma テンプレートが存在する場合は、Sigma リポジトリへのリンクを追加しました。 Sigma テンプレートは、Microsoft によって記述、テスト、管理されません。 リポジトリとテンプレートは、世界中の IT セキュリティ コミュニティによって作成および収集されています。
- **[Azure Monitor](https://learn.microsoft.com/ja-jp/azure/azure-monitor/overview)**. さまざまな条件に基づいて監視とアラートを自動化します。 ブックを作成または使用して、異なるソースのデータを結合できます。
- **[Azure Event Hubs](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-about)** と SIEM の統合。 Azure Event Hubs 統合を介して、Splunk、ArcSight、QRadar、Sumo Logic など、他の SIEM に Microsoft Entra ID ログをプッシュできるようにします。 詳しくは、[Azure イベント ハブへの Microsoft Entra ログのストリーム配信](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub)に関する記事をご覧ください。
- **[Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/cloud-app-security/what-is-cloud-app-security)**. アプリの検出と管理、アプリとリソース全体のガバナンス、クラウド アプリのコンプライアンスの確認を行うことができます。
- **Microsoft Graph**。 データをエクスポートし、Microsoft Graph を使用してより詳細な分析を行うことができます。 詳細については、「[Microsoft Graph PowerShell SDK と Microsoft Entra ID 保護について](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-graph-api)」を参照してください。
- **[Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)**. 調査に役立つ 3 つの主要なレポートを生成します。

    - **危険なユーザー**。 リスクのあるユーザーに関する情報、検出の詳細、すべての危険なサインインの履歴、およびリスク履歴が含まれます。
    - **危険なサインイン**。疑わしい状況を示す可能性のあるサインインの状況に関する情報が含まれています。 このレポートの情報を調査するための追加情報については、「[リスクを調査する](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)」をご覧ください。
    - **リスク検出**。 リスクが検出されたときトリガーされるその他のリスクに関する情報や、サインインの場所などの他の関連情報、Microsoft Defender for Cloud Apps からの詳細情報が含まれます。
- **[Microsoft Entra ID 保護によるワークロード ID のセキュリティ保護](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk)**。 サインイン時の動作やオフラインでの侵害の兆候からワークロード ID のリスクを検出するために使用します。

この方法はお勧めしませんが、特権アカウントが永続的な管理者権限を持つことができます。 永続的な特権を使用することを選択した場合、そのアカウントが侵害されると、強い悪影響が及ぶ可能性があります。 特権アカウントの監視を優先し、そのアカウントを Privileged Identity Management (PIM) 構成に含めることをお勧めします。 PIM の詳細については、「[Privileged Identity Management の使用開始](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-getting-started)」を参照してください。 さらに、その管理者アカウントについて、以下を検証することをお勧めします。

- 必須であること。
- 必要なアクティビティを実行するための最小限の特権を持っていること。
- 少なくとも多要素認証で保護されていること。
- 特権アクセス ワークステーション (PAW) またはセキュリティ保護された管理ワークステーション (SAW) デバイスから実行されていること。

以降では、監視とアラートが推奨される対象について説明します。 この記事は、脅威の種類ごとの分類を示しています。 特定の事前構築済みソリューションがある場合は、表の後にリンクを示しています。 それ以外の場合は、前述のツールを使用してアラートを作成できます。

この記事では、ベースラインの設定、特権アカウントのサインインと使用の監査について詳しく説明します。 また、特権アカウントの整合性を維持するために使用できるツールとリソースについても説明します。 内容は、次の項目で構成されています。

- 緊急時の "非常用" アカウント
- 特権アカウントのサインイン
- 特権アカウントの変更
- 特権グループ
- 特権の割り当てと昇格

### 緊急アクセス アカウント

Microsoft Entra テナントから誤ってロック アウトされないようにすることが重要です。

Microsoft は、組織が [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールが永続的に割り当てられる 2 つのクラウド専用の緊急アクセス アカウントを作成することを推奨しています。 このようなアカウントは高い特権を持っており、特定のユーザーには割り当てられません。 これらのアカウントは、通常のアカウントを使用できない、または他のすべての管理者が誤ってロックアウトされたという緊急または "ブレーク グラス" のシナリオに限定されます。これらのアカウントは、[緊急アクセス アカウントに関するレコメンデーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)に従って作成する必要があります。

緊急アクセス アカウントが使用されるたびに、優先度の高いアラートを送信します。

#### 探索

非常用アカウントは緊急時にのみ使用されるため、監視によってアカウント アクティビティが検出されないようにする必要があります。 緊急アクセス アカウントが使用または変更されるたびに、優先度の高いアラートを送信します。 次のいずれかのイベントは、悪意のあるアクターがあなたの環境を侵害しようとしていることを示している可能性があります。

- サインイン。
- アカウント パスワードの変更。
- アカウントのアクセス許可またはロールの変更。
- 資格情報または認証方法の追加または変更。

緊急アクセス アカウントの管理の詳細については、[Microsoft Entra ID での緊急アクセス用管理者アカウントの管理](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)に関するページを参照してください。 緊急アカウントのアラートの作成の詳細については、[アラート ルールの作成](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)に関するページを参照してください。

### 特権アカウントのサインイン

Microsoft Entra サインイン ログをデータ ソースとして使用して、特権アカウントのすべてのサインイン アクティビティを監視します。 このログには、サインインの成功と失敗に関する情報に加えて、次の詳細が含まれています。

- 割り込み
- デバイス
- 保管場所
- リスク
- アプリケーション
- 日付と時刻
- アカウントが無効になっているか
- ロックアウト
- MFA の不正
- 条件付きアクセスの失敗

#### 監視する内容

特権アカウントのサインイン イベントは、Microsoft Entra サインイン ログで監視できます。 特権アカウントの次のイベントについてアラートを出して調査します。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| サインインの失敗、不正なパスワードしきい値 | 高 | Microsoft Entra サインイン ログ | 状態 = 失敗およびエラー コード = 50126 | ベースラインしきい値を定義してから、組織の行動に合わせて監視および調整し、誤ったアラートが生成されないようにします。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/MultipleDataSources/PrivilegedAccountsSigninFailureSpikes.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 条件付きアクセス要件が原因による失敗 | 高 | Microsoft Entra サインイン ログ | 状態 = 失敗およびエラー コード = 53003および失敗の理由 = 条件付きアクセスによってブロック | このイベントは、攻撃者がアカウントに侵入しようとしていることを示している可能性があります。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/SigninLogs/UserAccounts-CABlockedSigninSpikes.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 名前付けポリシーに従わない特権アカウント |  | Azure サブスクリプション | [Azure ポータルを使用して Azure ロールの割り当てを一覧表示する](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-list-portal) | サブスクリプションのロール割り当てを一覧表示し、サインイン名が組織の形式と一致していない場合にアラートを出します。 たとえば、プレフィックスとしての ADM\_ の使用です。 |
| 割り込み | [高]、[中] | Microsoft Entra サインイン | 状態 = 中断およびエラー コード = 50074および失敗の理由 = 強力な認証が必要状態 = 中断およびエラー コード = 500121失敗の理由 = 強力な認証の要求時に、認証に失敗しました | このイベントは、攻撃者がアカウントのパスワードを持っていても、多要素認証チャレンジに合格できないことを示している可能性があります。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/MultipleDataSources/AADPrivilegedAccountsFailedMFA.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 名前付けポリシーに従わない特権アカウント | 高 | Microsoft Entra Directory | [Microsoft Entra ロールの割り当ての一覧表示](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/view-assignments) | UPN が組織の形式と一致しない Microsoft Entra ロールとアラートのロール割り当てを一覧表示します。 たとえば、プレフィックスとしての ADM\_ の使用です。 |
| 多要素認証に登録されていない特権アカウントを検出します | 高 | Microsoft Graph API | 管理者アカウントの IsMFARegistered eq false のクエリ。 [credentialUserRegistrationDetails の一覧表示 - Microsoft Graph ベータ版](https://learn.microsoft.com/ja-jp/graph/api/reportroot-list-credentialuserregistrationdetails?view=graph-rest-beta&preserve-view=true&tabs=http) | 監査と調査を行って、このイベントが意図的なのか、見落としなのかを判断します。 |
| アカウントのロックアウト | 高 | Microsoft Entra サインイン ログ | 状態 = 失敗およびエラー コード = 50053 | ベースラインしきい値を定義してから、組織の行動に合わせて監視および調整し、誤ったアラートが生成されないようにします。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/MultipleDataSources/PrivilegedAccountsLockedOut.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| アカウントがサインインで無効またはブロックされる | 低 | Microsoft Entra サインイン ログ | 状態 = 失敗およびターゲット = ユーザー UPNおよびエラー コード = 50057 | このイベントは、誰かが組織を退職した後にアカウントにアクセスしようとしていることを示している可能性があります。 このアカウントはブロックされていますが、このアクティビティについて記録し、アラートを出すことが重要です。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/SigninLogs/UserAccounts-BlockedAccounts.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| MFA 不正アクセスのアラートまたはブロック | 高 | Microsoft Entra サインイン ログ/Azure Log Analytics | サインイン&gt;認証詳細 結果詳細 = MFA 拒否、不正なコードの入力) | 特権ユーザーは、自分が多要素認証プロンプトを引き起こしていないと示しており、これは、攻撃者がアカウントのパスワードを持っていることを示している可能性があります。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/SigninLogs/MFARejectedbyUser.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| MFA 不正アクセスのアラートまたはブロック | 高 | Microsoft Entra 監査ログ/Azure Log Analytics | アクティビティの種類 = 不正報告 - MFA または 不正報告によりユーザーをブロックしています - 対応を行いませんでした (不正報告のテナント レベル設定による) | 特権ユーザーは、自分が多要素認証プロンプトを引き起こしていないと示しており、これは、攻撃者がアカウントのパスワードを持っていることを示している可能性があります。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/SigninLogs/MFARejectedbyUser.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 想定された制御の範囲外の特権アカウント サインイン |  | Microsoft Entra サインイン ログ | 状態 = 失敗UserPrincipalName = &lt;管理者アカウント&gt;場所 = &lt;未承認の場所&gt;IP アドレス = &lt;未承認の IP&gt;デバイス情報 = &lt;承認されていないブラウザー、オペレーティング システム&gt; | 未承認として定義したエントリを監視し、アラートを出します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/SigninLogs/SuspiciousSignintoPrivilegedAccount.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 通常のサインイン時間外 | 高 | Microsoft Entra サインイン ログ | 状態 = 成功および場所 =および時間 = 勤務時間外 | 想定される時間外にサインインが発生した場合について監視し、アラートを出します。 各特権アカウントの通常の勤務パターンを見つけて、通常の勤務時間外に予定外の変更が発生した場合にアラートを出すことが重要です。 通常の勤務時間外のサインインが、侵害や内部関係者の脅威の可能性を示している場合があります。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/MultipleDataSources/AnomolousSignInsBasedonTime.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| Microsoft Entra ID Protection リスク | 高 | ID 保護ログ | リスク状態 = リスクありおよびリスク レベル = 低、中、高およびアクティビティ = 通常とは異なるサインイン/TOR など | このイベントは、そのアカウントへのサインインに何らかの異常が検出されたことを示しており、アラートを出す必要があります。 |
| パスワードの変更 | 高 | Microsoft Entra 監査ログ | アクティビティ アクター = 管理者/セルフサービスおよびターゲット = ユーザーおよび状態 = 成功または失敗 | いずれかの管理者アカウントのパスワードが変更された場合にアラートを送信します。 特権アカウントのクエリを記述します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/MultipleDataSources/PrivilegedAccountPasswordChanges.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| レガシ認証プロトコルの変更 | 高 | Microsoft Entra サインイン ログ | クライアント アプリ = その他のクライアント、IMAP、POP3、MAPI、SMTP などおよびユーザー名 = UPNおよびアプリケーション = Exchange (例) | 多くの攻撃ではレガシ認証が使用されます。そのため、ユーザーの認証プロトコルに変更があった場合は、攻撃を示している可能性があります。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/17ead56ae30b1a8e46bb0f95a458bdeb2d30ba9b/Hunting%20Queries/SigninLogs/LegacyAuthAttempt.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 新しいデバイスまたは場所 | 高 | Microsoft Entra サインイン ログ | デバイス情報 = デバイス IDおよびブラウザーおよびオペレーティングシステム (OS)および準拠している/マネージドおよびターゲット = ユーザーおよび保管場所 | ほとんどの管理者アクティビティは、限られた数の場所からの[特権アクセス デバイス](https://learn.microsoft.com/ja-jp/security/compass/privileged-access-devices)からのものである必要があります。 このため、新しいデバイスや場所についてアラートを出します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/SigninLogs/SuspiciousSignintoPrivilegedAccount.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 監査アラートの設定が変更された | 高 | Microsoft Entra 監査ログ | サービス = PIMおよびカテゴリ = ロール管理およびアクティビティ = PIM アラートの無効化および状態 = 成功 | コア アラートに対する変更は、想定外の場合にアラートを出す必要があります。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/SecurityAlert/DetectPIMAlertDisablingActivity.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 他の Microsoft Entra テナントに対して認証を行う管理者 | 中間 | Microsoft Entra サインイン ログ | 状態 = 成功リソースの tenantID != ホーム テナント ID | 特権ユーザーにスコープを設定したとき、このモニターは管理者が組織のテナント内の ID を使用して別の Microsoft Entra テナントに対して正常に認証されたことを検出します。 リソース TenantID がホーム テナント ID と等しくない場合にアラートを生成します[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/SigninLogs/AdministratorsAuthenticatingtoAnotherAzureADTenant.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 管理者ユーザーの状態が [ゲスト] から [メンバー] に変更された | 中間 | Microsoft Entra 監査ログ | アクティビティ: ユーザーの更新カテゴリ: UserManagementUserType が [ゲスト] から [メンバー] に変更された | ユーザーの種類の [ゲスト] から [メンバー] への変更を監視し、アラートを生成します。 この変更は予期されていたものですか?[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/UserStatechangedfromGuesttoMember.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 承認されていない招待元によってテナントに招待されたゲスト ユーザー | 中間 | Microsoft Entra 監査ログ | アクティビティ: 外部ユーザーを招待するカテゴリ: UserManagement開始者 (アクター): ユーザー プリンシパル名 | 外部ユーザーを招待する承認されていないアクターを監視し、アラートを生成します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/GuestUsersInvitedtoTenantbyNewInviters.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

### 特権アカウントによる変更

特権アカウントによって完了および試行された変更をすべて監視します。 このデータにより、各特権アカウントの通常のアクティビティが何であるかを確定し、想定から逸脱したアクティビティについてアラートを出すことができます。 Microsoft Entra 監査ログは、この種類のイベントを記録するために使用されます。 Microsoft Entra 監査ログの詳細については、「[Microsoft Entra ID の監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)」を参照してください。

#### Microsoft Entra Domain Services

Microsoft Entra Domain Services でアクセス許可が割り当てられている特権アカウントは、Microsoft Entra Domain Services を使用する Azure ホステッド仮想マシンのセキュリティ体制に影響を与える Microsoft Entra Domain Services のタスクを実行できます。 仮想マシンでセキュリティ監査を有効にして、ログを監視します。 Microsoft Entra Domain Services 監査の有効化に関する詳細と、機密性の高い特権アクセスの一覧については、次のリソースを参照してください。

- [Microsoft Entra DS でセキュリティ監査を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/security-audit-events)
- [機密性が高い特権の使用の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-sensitive-privilege-use)

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 試行および完了された変更 | 高 | Microsoft Entra 監査ログ | 日付と時刻およびServiceおよびアクティビティの名前とカテゴリ ("何")および状態 = 成功または失敗およびTargetおよびイニシエーターまたはアクター (誰か) | 予定外の変更について、直ちにアラートを出す必要があります。 これらのログは、あらゆる調査に役立てるために保持する必要があります。 テナントのセキュリティ体制を低下させるようなすべてのテナント レベルの変更は、直ちに調査する必要があります (インフラストラクチャ ドキュメントにリンク)。 たとえば、アカウントを多要素認証または条件付きアクセスから除外する。 アプリケーションに対する追加または変更があった場合にアラートを出します。 「[アプリケーションのための Microsoft Entra セキュリティ運用ガイド](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-applications)」をご覧ください。 |
| **例**価値の高いアプリまたはサービスに対する変更の試行または完了 | 高 | 監査ログ | Serviceおよびアクティビティのカテゴリと名前 | 日付と時刻、サービス、アクティビティのカテゴリと名前、状態 = 成功または失敗、ターゲット、イニシエーターまたはアクター (誰か) |
| Microsoft Entra Domain Services での特権的な変更 | 高 | Microsoft Entra Domain Services | イベント [4673](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/event-4673) を探す | [Microsoft Entra DS でセキュリティ監査を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/security-audit-events)すべての特権イベントの一覧については、「[機密性が高い特権の使用の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-sensitive-privilege-use)」を参照してください。 |

### 特権アカウントに対する変更

特権アカウントの認証規則と特権に対する変更を調査します。これが特に該当するのは、この変更によって、Microsoft Entra 環境でより大きな特権またはタスクを実行する能力が付与される場合です。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 特権アカウントの作成 | 中間 | Microsoft Entra 監査ログ | サービス = コア ディレクトリおよびカテゴリ = ユーザー管理およびアクティビティの種類 = ユーザーの追加-次と関連-カテゴリの種類 = ロール管理およびアクティビティの種類 = ロールへのメンバーの追加および変更されたプロパティ = Role.DisplayName | 特権アカウントの作成を監視します。 アカウントの作成から削除までの時間間隔が短いという相関関係を探します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/UserAssignedPrivilegedRole.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 認証方法に対する変更 | 高 | Microsoft Entra 監査ログ | サービス = 認証方法およびアクティビティの種類 = ユーザーが登録したセキュリティ情報およびカテゴリ = ユーザー管理 | この変更は、攻撃者が認証方法をアカウントに追加して、継続的にアクセスできるようにしていることを示している可能性があります。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/MultipleDataSources/AuthenticationMethodsChangedforPrivilegedAccount.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 特権アカウントのアクセス許可に対する変更についてアラートを出します | 高 | Microsoft Entra 監査ログ | カテゴリ = ロール管理およびアクティビティの種類 = 対象メンバーの追加 (永続的)\- または -アクティビティの種類 = 対象メンバーの追加 (対象)および状態 = 成功または失敗および変更されたプロパティ = Role.DisplayName | このアラートは特に、アカウントに割り当てられているロールが不明なものであったり、通常の責任の範囲外のものであったりする場合に該当します。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 使用されていない特権アカウント | 中間 | Microsoft Entra アクセス レビュー |  | 非アクティブな特権ユーザー アカウントに対して毎月のレビューを行います。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 条件付きアクセスの適用が除外されたアカウント | 高 | Azure Monitor ログ\- または -アクセス レビュー | 条件付きアクセス = 分析情報とレポート | 条件付きアクセスの適用が除外されたアカウントは、セキュリティ制御を回避している可能性が高く、侵害に対してより脆弱です。 非常用アカウントは除外されます。 非常用アカウントを監視する方法については、この記事で後述する説明をご覧ください。 |
| 特権アカウントへの一時アクセス パスの追加 | 高 | Microsoft Entra 監査ログ | アクティビティ: 管理者が登録したセキュリティ情報状態の理由: 管理者がユーザーに対して登録した一時アクセス パス方法カテゴリ: UserManagement開始者 (アクター): ユーザー プリンシパル名ターゲット: ユーザー プリンシパル名 | 特権ユーザーに対して作成されている一時アクセス パスを監視し、アラートを生成します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/tree/master/Detections/AuditLogs/AdditionofaTemporaryAccessPasstoaPrivilegedAccount.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

条件付きアクセス ポリシーの例外を監視する方法の詳細については、「[条件付きアクセスに関する分析情報とレポート](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-insights-reporting)」を参照してください。

使用されていない特権アカウントの検出の詳細については、「[Privileged Identity Management で Microsoft Entra ロールのアクセス レビューを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)」を参照してください。

### 割り当てと昇格

昇格した能力を持つ特権アカウントを永続的にプロビジョニングすると、攻撃対象が増え、セキュリティ境界に対するリスクが高まる可能性があります。 代わりに、昇格手順を使用して Just-In-Time アクセスを採用します。 この種類のシステムでは、特権ロールの資格を割り当てることができます。 管理者は、これらの特権を必要とするタスクを実行する場合にのみ、特権をそれらのロールに昇格させます。 昇格プロセスを使用することで、特権アカウントの昇格と不使用を監視できます。

#### ベースラインを確立する

例外を監視するには、最初にベースラインを作成する必要があります。 これらの要素について、以下の情報を確認してください

- **管理者アカウント**

    - 特権アカウント戦略
    - オンプレミスのリソースを管理するためのオンプレミス アカウントの使用
    - クラウドベースのリソースを管理するためのクラウドベース アカウントの使用
    - オンプレミスおよびクラウドベースのリソースの管理アクセス許可を分離および監視する方法
- **特権ロール保護**

    - 管理者特権を持つロールの保護戦略
    - 特権アカウントの使用に関する組織ポリシー
    - 永続的な特権を維持するための戦略および原則と、時間の制約がある承認されたアクセスを提供するための戦略と原則

ポリシーを決定する際には、次の概念と情報が役立ちます。

- **Just-In-Time 管理の原則**。 Microsoft Entra ログを使用して、環境内で共通する管理タスクを実行するための情報を取得します。 このタスクを完了するために必要な通常の時間を決定します。
- **必要十分な管理者の原則**。 管理タスクに必要な最小限の特権を持つロールを決定します (これはカスタム ロールである場合もあります)。 詳細については、「[Microsoft Entra ID のタスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task)」を参照してください。
- **昇格ポリシーを確立する**。 昇格された特権の必要性の種類と、各タスクで必要とされる時間を把握したら、環境に対して昇格された特権使用を反映するポリシーを作成します。 たとえば、ロールの昇格を 1 時間に制限するポリシーを定義します。

ベースラインを確立してポリシーを設定したら、ポリシーの範囲を超える使用状況を検出してアラートを出すように監視を構成できます。

#### 探索

特権の割り当てと昇格における変更に特別な注意を払い、調査します。

#### 監視する内容

特権アカウントの変更を、Microsoft Entra 監査ログと Azure Monitor ログを使用して監視できます。 監視プロセスには次の変更を含めてください。

| [What to monitor] (監視対象) | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 対象特権ロールへの追加 | 高 | Microsoft Entra 監査ログ | サービス = PIMおよびカテゴリ = ロール管理およびアクティビティの種類 = ロールへのメンバーの追加が完了 (対象)および状態 = 成功または失敗および変更されたプロパティ = Role.DisplayName | ロールの対象となるすべてのアカウントに特権アクセスが与えられるようになりました。 割り当てが予期しないものであるか、アカウント所有者の責任でないロールに割り当てられている場合は、調査してください。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/UserAssignedPrivilegedRole.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| PIM の範囲外で割り当てられたロール | 高 | Microsoft Entra 監査ログ | サービス = PIMおよびカテゴリ = ロール管理およびアクティビティの種類 = ロールへのメンバーの追加 (永続的)および状態 = 成功または失敗および変更されたプロパティ = Role.DisplayName | これらのロールは注意深く監視し、アラートを出す必要があります。 ユーザーには、可能な限り PIM の範囲外のロールを割り当てないでください。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/PrivlegedRoleAssignedOutsidePIM.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 標高 | 中間 | Microsoft Entra 監査ログ | サービス = PIMおよびカテゴリ = ロール管理およびアクティビティの種類 = ロールへのメンバーの追加が完了しました (PIM アクティブ化)および状態 = 成功または失敗 および変更されたプロパティ = Role.DisplayName | 特権アカウントは、昇格後、テナントのセキュリティに影響を与える可能性のある変更を行えるようになります。 すべての昇格がログに記録される必要があります。また、そのユーザーの標準パターンの範囲外で起きている場合はアラートを出し、計画されていない場合は調査する必要があります。 |
| 昇格の承認と拒否 | 低 | Microsoft Entra 監査ログ | サービス = アクセス レビューおよびカテゴリ = ユーザー管理およびアクティビティの種類 = 要求が承認または拒否されたおよび開始したアクター = UPN | 昇格が攻撃のタイムラインを明示している可能性があるので、すべての昇格を監視してください。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/PIMElevationRequestRejected.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| PIM 設定の変更 | 高 | Microsoft Entra 監査ログ | サービス = PIMおよびカテゴリ = ロール管理およびアクティビティの種類 = PIM のロール設定の更新およびステータスの理由 = アクティブ化の MFA が無効になっている (例) | これらのアクションの 1 つにより PIM 昇格のセキュリティが低下し、攻撃者が特権アカウントを取得しやすくなります。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/4ad195f4fe6fdbc66fb8469120381e8277ebed81/Detections/AuditLogs/ChangestoPIMSettings.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 昇格が SAW/PAW で行われていない | 高 | Microsoft Entra サインイン ログ | デバイス ID およびブラウザーおよびオペレーティングシステム (OS)および準拠している/マネージド次と関連:サービス = PIMおよびカテゴリ = ロール管理およびアクティビティの種類 = ロールへのメンバーの追加が完了しました (PIM アクティブ化)および状態 = 成功または失敗および変更されたプロパティ = Role.DisplayName | この変更が構成されている場合は、PAW/SAW 以外のデバイスでの昇格の試行は、攻撃者がアカウントを使用しようとしていることを示す可能性があるため、すぐに調査する必要があります。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| すべての Azure サブスクリプションを管理するための昇格 | 高 | Azure Monitor | [アクティビティ ログ] タブ [ディレクトリ アクティビティ] タブ  操作名 = 呼び出し元をユーザー アクセス管理者に割り当てます  - および - イベント カテゴリ = 管理  および状態 = 成功、開始、失敗およびイベントの開始者 (アクター) | この変更は、計画されたものでない場合は直ちに調査する必要があります。 この設定により、攻撃者がユーザーの環境内の Azure サブスクリプションにアクセスできる可能性があります。 |

昇格の管理の詳細については、「[Azure のすべてのサブスクリプションと管理グループを管理する目的でアクセス権限を昇格させる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/elevate-access-global-admin)」を参照してください。 Microsoft Entra ログで利用可能な情報を使用した昇格の監視の詳細については、Azure Monitor のドキュメントの一部である「[Azure アクティビティ ログ](https://learn.microsoft.com/ja-jp/azure/azure-monitor/essentials/activity-log)」を参照してください。

Azure ロールに対するアラートの構成の詳細については、「[Privileged Identity Management で Azure リソース ロールに対するセキュリティ アラートを構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-configure-alerts)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/security-operations-privileged-identity-management"} -->
## Privileged Identity Management のための Microsoft Entra セキュリティ運用 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-privileged-identity-management
- Service: entra / architecture
- Article date: 2022-09-06
- Summary: ベースラインを確立し、Microsoft Entra Privileged Identity Management (PIM) を使用して、PIM で管理されているアカウントの問題を監視して警告します。

ビジネス資産のセキュリティは、IT システムを管理する特権アカウントの整合性に依存します。 サイバー攻撃者は、資格情報の盗難攻撃を使用して、管理者アカウントやその他の特権アクセス アカウントをターゲットにし、機密データへのアクセスを試みます。

クラウド サービスの場合、防止と対応は、クラウド サービス プロバイダーと顧客の共同責任です。

従来、組織のセキュリティは、セキュリティ境界としてネットワークの入口と出口のポイントに重点を置いていました。 しかし、SaaS アプリと個人用デバイスでは、この方法があまり効果的ではありません。 Microsoft Entra ID では、組織の ID レイヤーでネットワーク セキュリティ境界を認証に置き換えます。 ユーザーが特権管理者ロールに割り当てられている場合、そのユーザーのアクセスはオンプレミス、クラウド、およびハイブリッド環境で保護されている必要があります。

お客様は、オンプレミスの IT 環境におけるすべての層のセキュリティに全責任を負います。 Azure クラウド サービスを使用する場合、防止と対応は、Microsoft (クラウド サービス プロバイダー) とお客様 (あなた) の共同責任となります。

- 共有責任モデルの詳細については、「[クラウドにおける共有責任](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/shared-responsibility)」を参照してください。
- 特権を持つユーザーのアクセスをセキュリティで保護する方法の詳細については、「[Microsoft Entra ID でのハイブリッドおよびクラウド デプロイのための特権アクセスのセキュリティ保護](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-planning)」を参照してください。
- 特権 ID の主要概念に関するさまざまな動画、ハウツーガイド、およびコンテンツについては、[Privileged Identity Management のドキュメント](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/)を参照してください。

Privileged Identity Management (PIM) は Microsoft Entra のサービスで、これにより、お客様の組織内の重要なリソースへのアクセスを管理、制御、監視できるようになります。 これらのリソースには、Microsoft Entra ID、Azure、および Microsoft 365 や Microsoft Intune などのその他の Microsoft Online Services 内のリソースが含まれます。 PIM を使用すると、次のリスクを軽減できます。

- セキュリティで保護された情報やリソースへのアクセス権を持つユーザーの数を特定し、最小限に抑えます。
- 機密リソースに対するアクセス許可の過剰、不要、または誤用を検出します。
- 悪意のあるアクターがセキュリティで保護された情報やリソースにアクセスする危険性を低減します。
- 未承認のユーザーが誤って機密性の高いリソースに影響を与える危険性を低減します。

この記事では、ベースラインの設定、サインインの監査、特権アカウントの使用に関するガイダンスを提供します。 ソースの監査ログ ソースを使用して、特権アカウントの整合性を維持します。

### 見る場所

調査と監視に使用するログ ファイルは次のとおりです。

- [Microsoft Entra 監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)
- [サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)
- [Microsoft 365 監査ログ](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/auditing-solutions-overview)
- [Azure Key Vault ログ](https://learn.microsoft.com/ja-jp/azure/key-vault/general/logging?tabs=Vault)

Azure portal で、Microsoft Entra 監査ログを表示したり、コンマ区切り値 (CSV) または JavaScript Object Notation (JSON) ファイルとしてダウンロードしたりします。 Azure portal には、監視とアラートを自動化するために、Microsoft Entra ログを他のツールと統合するいくつかの方法があります:

- [**Microsoft Sentinel**](https://learn.microsoft.com/ja-jp/azure/sentinel/overview) – セキュリティ情報イベント管理 (SIEM) 機能を備え、エンタープライズ レベルでインテリジェントにセキュリティを分析します。
- **[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure)** - Sigma は、自動化された管理ツールがログ ファイルの解析に使用できるルールとテンプレートを記述するための、進化するオープン標準です。 推奨される検索条件に Sigma テンプレートが存在する場合は、Sigma リポジトリへのリンクを追加しました。 Sigma テンプレートは、Microsoft によって記述、テスト、管理されません。 その代わり、リポジトリとテンプレートは、世界中の IT セキュリティ コミュニティによって作成および収集されます。
- [**Azure Monitor**](https://learn.microsoft.com/ja-jp/azure/azure-monitor/overview) - さまざまな条件に基づいて監視とアラートを自動化します。 ワークブックを作成または使用して、異なるソースのデータを結合できます。
- **SIEM と統合した** **[Azure Event Hubs](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-about)**- [Microsoft Event Hubs ログは他の SIEM と統合できます](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub) (Azure Event Hubs 統合経由で Splunk、ArcSight、QRadar、Sumo Logic などと)。
- [**Microsoft Defender for Cloud Apps**](https://learn.microsoft.com/ja-jp/cloud-app-security/what-is-cloud-app-security) – アプリの検出と管理、アプリとリソース全体のガバナンス、クラウド アプリのコンプライアンスの確認を行うことができます。
- **[Microsoft Entra ID 保護 でワークロード ID をセキュリティで保護](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk)** - ログイン動作間とオフラインの侵害インジケーターにおけるワークロード ID のリスクを検出するために使用されます。

この記事の残りの部分には、階層モデルを使用して監視とアラートを出すベースラインを設定するための推奨事項が記載されています。 事前構築済みソリューションへのリンクは、表の後にあります。 前述のツールを使用してアラートを作成できます。 内容は、次の区分で構成されています。

- 基準
- Microsoft Entra のロール割り当て
- Microsoft Entra のロールアラート設定
- Azure リソース ロールの割り当て
- Azure リソースのアクセス管理
- Azure サブスクリプションを管理するために昇格したアクセス権

### 基準

推奨されるベースライン設定は、次のとおりです。

| 監視項目 | リスク レベル | 推奨 | 役割 | メモ |
| --- | --- | --- | --- | --- |
| Microsoft Entra のロール割り当て | 高 | アクティベーションの理由が必須です。 アクティブ化の承認が必須です。 2 レベル承認プロセスを設定する。 アクティブ化時に、Microsoft Entra 多要素認証を要求します。 最大昇格期間を 8 時間に設定します。 | セキュリティ管理者、特権ロール管理者、グローバル管理者 | 特権ロール管理者は、対象となるロールの割り当てをアクティブ化するユーザーのエクスペリエンスの変更など、Microsoft Entra 組織の PIM をカスタマイズできます。 |
| Azure リソース ロールの構成 | 高 | アクティベーションの理由が必須です。 アクティブ化の承認が必須です。 承認者を2段階に分けるプロセスを設定します。 アクティブ化時に、Microsoft Entra 多要素認証を要求します。 最大昇格期間を 8 時間に設定します。 | 所有者、ユーザー アクセス管理者 | 計画された変更でない場合は、すぐに調査します。 この設定により、攻撃者がユーザーの環境内の Azure サブスクリプションにアクセスできる可能性があります。 |

### Privileged Identity Management アラート

Microsoft Entra の組織内で疑わしいアクティビティや危険なアクティビティが行われると、Privileged Identity Management (PIM) によりアラートが生成されます。 アラートが生成されると、Privileged Identity Management ダッシュボードに表示されます。 電子メール通知を構成したり、GraphAPI 経由で SIEM に送信したりすることもできます。 これらのアラートは特に管理ロールに重点を置いているため、アラートを注意深く監視する必要があります。

| 監視項目 | リスク レベル | どこ | フィルター/サブフィルター UX | メモ |
| --- | --- | --- | --- | --- |
| [ロールが Privileged Identity Management の外部に割り当てられている](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts) | 高 | Privileged Identity Management、アラート | [ロールが Privileged Identity Management の外部に割り当てられている](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts) | [セキュリティ アラートを構成する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| [特権役割にある古くなっている可能性のあるアカウント](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts) | 中 | Privileged Identity Management、アラート | [特権ロールにある古くなる可能性のあるアカウント](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts) | [セキュリティ アラートを構成する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| [管理者が特権ロールを使用してません](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts) | 低 | Privileged Identity Management、アラート | [管理者が特権ロールを使用してません](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts) | [セキュリティ アラートを構成する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| [ロールのアクティブ化に多要素認証は必要ありません](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts) | 低 | Privileged Identity Management、アラート | [ロールのアクティブ化に多要素認証は必要ありません](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts) | [セキュリティ アラートを構成する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| [組織に Microsoft Entra ID P2 または Microsoft Entra ID ガバナンスがない](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts) | 低 | Privileged Identity Management、アラート | [組織に Microsoft Entra ID P2 または Microsoft Entra ID ガバナンスがない](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts) | [セキュリティ アラートを構成する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| [グローバル管理者が多すぎます](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts) | 低 | Privileged Identity Management、アラート | [グローバル管理者が多すぎます](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts) | [セキュリティ アラートを構成する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| [ロールをアクティブ化する頻度が高すぎます](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts) | 低 | Privileged Identity Management、アラート | [ロールをアクティブ化する頻度が高すぎます](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts) | [セキュリティ アラートを構成する方法](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-configure-security-alerts#security-alerts)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

### Microsoft Entra のロール割り当て

特権ロール管理者は、Microsoft Entra 組織の PIM をカスタマイズできます。これには、資格のあるロールの割り当てをアクティブ化するユーザー エクスペリエンスの変更が含まれます。

- 悪意のある行為者が Microsoft Entra の多要素認証要件を削除し、特権アクセスを有効にするのを防ぎます。
- 悪意のあるユーザーが、特権アクセスをアクティブ化する理由と承認を回避するのを防ぎます。

| 監視項目 | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 特権アカウントのアクセス許可変更に関するアラートを出します | 高 | Microsoft Entra 監査ログ | カテゴリ = ロール管理およびアクティビティの種類 – 対象メンバーの追加 (永続的) およびアクティビティの種類 – 対象メンバーの追加 (対象) および状態 = 成功/失敗および変更されたプロパティ = Role.DisplayName | 特権ロール管理者とグローバル管理者に対する変更を監視し、常に警告します。 これは、攻撃者がロールの割り当て設定を変更する特権を取得しようとしていることを示している可能性があります。 しきい値を定義していない場合は、ユーザーの場合については 60 分で 4 回、特権アカウントについては 60 分で 2 回でアラートを送信します。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 特権アカウントのアクセス許可に対する一括削除の変更についてアラートを出します | 高 | Microsoft Entra 監査ログ | カテゴリ = ロール管理およびアクティビティの種類 – 対象メンバーの削除 (永続的) およびアクティビティの種類 – 対象メンバーの削除 (対象) および状態 = 成功/失敗および変更されたプロパティ = Role.DisplayName | 計画された変更でない場合は、すぐに調査します。 この設定により、攻撃者がユーザーの環境内の Azure サブスクリプションにアクセスできる可能性があります。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/BulkChangestoPrivilegedAccountPermissions.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| PIM 設定の変更 | 高 | Microsoft Entra 監査ログ | サービス = PIMおよびカテゴリ = ロール管理およびアクティビティの種類 = PIM のロール設定の更新およびステータスの理由 = 有効化における MFA が無効になっている (例) | 特権ロール管理者とグローバル管理者に対する変更を監視し、常に警告します。 これは、攻撃者がロールの割り当て設定を変更するアクセス権を取得したことを示している可能性があります これらのアクションの 1 つにより PIM 昇格のセキュリティが低下し、攻撃者が特権アカウントを取得しやすくなります。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/ChangestoPIMSettings.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 昇格の承認と拒否 | 高 | Microsoft Entra 監査ログ | サービス = アクセス レビューおよびカテゴリ = ユーザー管理およびアクティビティの種類 = 要求が承認/拒否されたおよび開始したアクター = UPN | すべての高低差を監視する必要があります。 攻撃のタイムラインを明示するために、すべての昇格をログに記録してください。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/PIMElevationRequestRejected.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| アラート設定が無効に変更されました。 | 高 | Microsoft Entra 監査ログ | サービス: PIMおよびカテゴリ = ロール管理およびアクティビティの種類 = PIM アラートを無効にするおよび状態 = 成功/失敗 | 常にアラート。 不正なアクターが、特権アクセスをアクティブ化しようとして Microsoft Entra の多要素認証要件に関連付けられているアラートを削除するのを検出できます。 疑わしいアクティビティまたは安全でないアクティビティの検出に役立ちます。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/SecurityAlert/DetectPIMAlertDisablingActivity.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

Microsoft Entra 監査ログでロール設定の変更を識別する方法の詳細については、「[Privileged Identity Management で Microsoft Entra ロールの監査履歴を表示する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-use-audit-log)」を参照してください。

### Azure リソース ロールの割り当て

Azure リソース ロールの割り当てを監視すると、リソースロールのアクティビティやアクティブ化を可視化できます。 これらの割り当ては、リソースに対する攻撃面を作成するために悪用される可能性があります。 この種類のアクティビティを監視する際には、次のものを検出しようとしています。

- 特定のリソースでのクエリ ロールの割り当て
- すべての子リソースに対するロールの割り当て
- アクティブなロールと資格のあるロールの割り当ての変更

| 監視項目 | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 特権アカウント活動に関する監査アラートリソースの監査ログ | 高 | PIM の「Azure リソース」にある「リソース監査」 | アクション: PIM での資格のあるメンバーのロールへの追加が完了しました (期限付き) およびプライマリ ターゲット およびタイプ: ユーザーおよび状態: 成功 | 常にアラート。 不正なアクターが、Azure のすべてのリソースを管理する資格のあるロールを追加しようとするのを検出できます。 |
| アラートの無効化に関する監査アラート リソース監査 | 中 | PIM の [Azure リソース] にある [リソースの監査] | アクション: アラートの無効化およびプライマリ ターゲット: リソースに対して所有者が多すぎるおよび状態: 成功 | 不正なアクターが、[アラート] ウィンドウでアラートを無効にし、悪意のあるアクティビティの調査をバイパスしようとするのを検出できます |
| アラートの無効化に関する監査アラート リソース監査 | 中 | PIM の [Azure リソース] にある [リソース監査] | アクション: アラートの無効化および主な対象: あるリソースにあまりにも多くの永続的な所有者が割り当てられているおよび状態: 成功 | 不正なアクターが、[アラート] ウィンドウでアラートを無効にし、悪意のあるアクティビティの調査をバイパスしようとするのを防ぎます |
| アラートの無効化に関する監査アラート リソース監査 | 中 | PIM の [Azure リソース] にある [リソース監査] | アクション: アラートの無効化およびプライマリ ターゲット: 重複して作成されたロールおよび状態: 成功 | 不正なアクターが、[アラート] ウィンドウでアラートを無効にし、悪意のあるアクティビティの調査をバイパスしようとするのを防ぎます |

アラートの構成と、Azure リソース ロールの監査の詳細については、次を参照してください。

- [Privileged Identity Management で Azure リソース ロールに対するセキュリティ アラートを構成する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-configure-alerts)
- [Privileged Identity Management (PIM) で Azure リソース ロールの監査レポートを表示する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/azure-pim-resource-rbac)

### Azure リソースとサブスクリプションに対するアクセス管理

所有者またはユーザー アクセス管理者サブスクリプション ロールに割り当てられたユーザーまたはグループ メンバー、および Microsoft Entra ID でサブスクリプション管理を有効した Microsoft Entra 全体管理者には、リソース管理者のアクセス許可が既定で与えられます。 この管理者は、ロールを割り当て、ロール設定を構成し、Azure リソース用 Privileged Identity Management (PIM) を使用してアクセスを確認します。

リソース管理者のアクセス許可を持つユーザーは、リソースの PIM を管理できます。 この発生するリスクを監視および軽減する: この機能を使用して、仮想マシン (VM) やストレージ アカウントなどの Azure サブスクリプション リソースに対する特権アクセスが、不正なアクターに許可される可能性があります。

| 監視項目 | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 標高 | 高 | Microsoft Entra ID の [管理]、[プロパティ] | 設定を定期的に確認します。Azure リソースのアクセス管理 | グローバル管理者は、Azure リソースのアクセス管理を有効にすることで昇格が可能です。Active Directory に関連付けられているすべての Azure サブスクリプションと管理グループにロールを割り当てるアクセス許可が不正なアクターに付与されていないか確認します。 |

詳細については、「[Privileged Identity Management で Azure リソース ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-resource-roles-assign-roles)」を参照してください
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/security-operations-user-accounts"} -->
## ユーザー アカウントのための Microsoft Entra セキュリティ運用 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-user-accounts
- Service: entra / architecture
- Article date: 2022-09-06
- Summary: ベースラインを確立するためのガイダンスと、ユーザー アカウントの潜在的なセキュリティ問題を監視し、アラートを生成する方法。

ユーザー ID は、組織とデータを保護する上で最も重要な側面の 1 つです。 この記事では、アカウントの作成、削除と、アカウントの使用状況を監視するためのガイダンスを提供します。 最初に、通常とは異なるアカウントの作成と削除を監視する方法について説明します。 次に、アカウントの異常な使用を監視する方法について説明します。

[Microsoft Entra のセキュリティ運用の概要](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-introduction)に関するページをまだ読んでいない場合は、読んでから先に進むことをお勧めします。

この記事では、一般的なユーザー アカウントについて説明します。 特権アカウントについては、セキュリティ運用の特権アカウントに関するページを参照してください。

### ベースラインを定義する

異常な動作を発見するには、通常の想定される動作をまず定義する必要があります。 組織にとって想定される動作を定義することで、想定しない動作が発生したときに判断することができます。 また、定義することで、監視とアラートを行う際の擬陽性のノイズ レベルを減らすことができます。

想定するものを定義したら、ベースライン監視を実行し、想定内容を検証します。 その情報を使用して、定義した許容範囲から外れたものがないか、ログを監視することができます。

通常のプロセス以外で作成されたアカウントについては、データ ソースとして、Microsoft Entra 監査ログ、Microsoft Entra サインイン ログ、ディレクトリ属性を使用します。 組織にとって通常とは何かについて考え、定義する際に役立つ提案を次に示します。

- **ユーザーのアカウント作成** - 以下を評価します。

    - ユーザー アカウントの作成と管理に使用するツールとプロセスの戦略と原則。 たとえば、ユーザー アカウントの属性に適用される標準の属性、形式はあるでしょうか。
    - アカウントの作成に承認されているソース。 たとえば、Active Directory (AD)、Microsoft Entra ID、または Workday のような人事システムによるものです。
    - 承認されたソース以外でアカウントが作成された場合のアラート戦略。 ご自分の組織が共同作業している組織の管理された一覧はありますか。
    - ゲスト アカウントのプロビジョニングと、エンタイトルメント管理やその他の通常のプロセス以外で作成されたアカウントに対するアラート パラメーター。
    - 承認されたユーザー管理者ではないアカウントによって作成、変更、または無効化されたアカウントに対する戦略とアラートのパラメーター。
    - 従業員 ID などの標準の属性がない、または組織の名前付け規則に従っていないアカウントの監視とアラート戦略。
    - アカウントの削除と保持のための戦略、原則、プロセス。
- **オンプレミスのユーザー アカウント** - Microsoft Entra Connect と同期されているアカウントについて、以下を評価します。

    - 同期のスコープに対象となるフォレスト、ドメイン、組織単位 (OU)。 これらの設定を変更できる承認された管理者は誰ですか。また、スコープはどのくらいの頻度で確認されますか。
    - 同期されるアカウントの種類。 たとえば、ユーザー アカウントやサービス アカウントなどです。
    - オンプレミスの特権アカウントを作成するプロセスと、この種類のアカウントの同期を制御する方法。
    - オンプレミスのユーザー アカウントを作成するプロセスと、この種類のアカウントの同期を管理する方法。

オンプレミスのアカウントのセキュリティと監視の詳細については、「[オンプレミスの攻撃から Microsoft 365 を保護する](https://learn.microsoft.com/ja-jp/entra/architecture/protect-m365-from-on-premises-attacks)」を参照してください。

- **クラウド ユーザー アカウント** - 以下を評価します。

    - Microsoft Entra ID でクラウド アカウントを直接プロビジョニングし、管理するプロセス。
    - Microsoft Entra のクラウド アカウントとしてプロビジョニングされるユーザーの種類を決定するプロセス。 たとえば、特権アカウントのみを許可するか、ユーザー アカウントも許可するか、などです。
    - クラウド ユーザー アカウントの作成と管理を行うことを想定する、信頼できる個人とプロセスの一覧を作成し、保守するプロセス。
    - 承認されていないクラウドベースのアカウントに対するアラート戦略を作成し、保守するプロセス。

### 見る場所

調査と監視に使用するログ ファイルは次のとおりです。

- [Microsoft Entra 監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)
- [サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)
- [Microsoft 365 監査ログ](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/auditing-solutions-overview)
- [Azure Key Vault ログ](https://learn.microsoft.com/ja-jp/azure/key-vault/general/logging?tabs=Vault)
- [危険なユーザー ログ](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)
- [UserRiskEvents ログ](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-investigate-risk)

Azure portal から、Microsoft Entra 監査ログを表示したり、コンマ区切り値 (CSV) または JavaScript Object Notation (JSON) ファイルとしてダウンロードしたりできます。 Azure portal には、監視とアラートの自動化を強化できる他のツールと Microsoft Entra ログを統合する方法がいくつかあります:

- **[Microsoft Sentinel](https://learn.microsoft.com/ja-jp/azure/sentinel/overview)** – セキュリティ情報イベント管理 (SIEM) 機能を備え、エンタープライズ レベルでインテリジェントにセキュリティを分析します。
- **[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure)** - Sigma は、自動化された管理ツールがログ ファイルの解析に使用できるルールとテンプレートを記述するための、進化するオープン標準です。 推奨される検索条件に Sigma テンプレートが存在する場合は、Sigma リポジトリへのリンクを追加しました。 Sigma テンプレートは、Microsoft によって記述、テスト、管理されません。 その代わり、リポジトリとテンプレートは、世界中の IT セキュリティ コミュニティによって作成および収集されます。
- **[Azure Monitor](https://learn.microsoft.com/ja-jp/azure/azure-monitor/overview)** - さまざまな条件に基づいて監視とアラートを自動化します。 ワークブックを作成または使用して、異なるソースのデータを結合できます。
- **[Azure Event Hubs](https://learn.microsoft.com/ja-jp/azure/event-hubs/event-hubs-about)** と SIEM の統合 - Azure Event Hub 統合を介して、Splunk、ArcSight、QRadar、Sumo Logic など、[他の SIEM に Microsoft Entra ログを統合できます](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-stream-logs-to-event-hub)。
- **[Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/cloud-app-security/what-is-cloud-app-security)** – アプリの検出と管理、すべてのアプリとリソースの制御、クラウド アプリのコンプライアンスの確認を行得るようにします。
- **[Microsoft Entra ID 保護 でワークロード ID をセキュリティで保護](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-workload-identity-risk)** - ログイン動作間とオフラインの侵害インジケーターにおけるワークロード ID のリスクを検出するために使用されます。

監視とアラートの対象の多くは、条件付きアクセス ポリシーの影響を受けます。 [条件付きアクセスに関する分析情報とレポート ブック](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-insights-reporting)を使用して、1 つまたは複数の条件付きアクセス ポリシーがサインインに及ぼしている影響と、デバイスの状態などのポリシーの結果を確認できます。 このブックでは、概要を確認し、指定期間における影響を特定できます。 ワークブックを使用して、特定のユーザーのサインインを調べることもできます。

この記事の残りの部分で、監視とアラートをお勧めする対象について説明し、脅威の種類別にまとめています。 特定の事前構築済みソリューションがある場合は、表の後にリンクを示すか、サンプルを提供しています。 それ以外の場合は、前述のツールを使用してアラートを作成できます。

### アカウントの作成

異常なアカウント作成は、セキュリティ上の問題を示している可能性があります。 短命なアカウント、名前付け標準に従っていないアカウント、通常のプロセス以外で作成されたアカウントなどを調査する必要があります。

#### 短命なアカウント

通常の ID 管理プロセス以外で行われたアカウントの作成と削除は、Microsoft Entra ID で監視する必要があります。 短命なアカウントとは、短期間に作成され、削除されたアカウントです。 このようなアカウントの作成と短期の削除は、不正なアクターがアカウントを作成し、使用後にアカウントを削除することで、検出を回避しようとしていることを示す可能性があります。

短命なアカウントのパターンは、承認されていない個人またはプロセスが、確立されたプロセスとポリシーから外れたアカウントの作成や削除の権限を持っていることを示す可能性があります。 このような動作により、目に見えるマーカーがディレクトリから削除されます。

アカウントの作成と削除に関するデータの軌跡を迅速に発見できなければ、インシデントの調査に必要な情報が存在しなくなる可能性があります。 たとえば、アカウントが削除された後、ごみ箱から消去される場合があります。 監査ログは 30 日間保持されます。 ただし、ログを Azure Monitor またはセキュリティ情報イベント管理 (SIEM) ソリューションにエクスポートすることで、より長期的に保持することができます。

| 監視項目 | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 近い期間内のアカウントの作成と削除のイベント。 | 高 | Microsoft Entra 監査ログ | アクティビティ: ユーザーの追加状態 = 成功およびアクティビティ: ユーザーの削除状態 = 成功 | ユーザー プリンシパル名 (UPN) イベントを検索します。 24 時間以内に作成され、削除されたアカウントを探します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/AccountCreatedandDeletedinShortTimeframe.yaml) |
| 承認されていないユーザーまたはプロセスによって作成され、削除されたアカウント。 | 中 | Microsoft Entra 監査ログ | 開始者（アクター）- ユーザー主体名およびアクティビティ: ユーザーの追加状態 = 成功および/またはアクティビティ: ユーザーの削除状態 = 成功 | アクターが承認されていないユーザーの場合、アラートを送信するように構成します。 [Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/AccountCreatedDeletedByNonApprovedUser.yaml) |
| 承認されていないソースからのアカウント。 | 中 | Microsoft Entra 監査ログ | アクティビティ: ユーザーの追加状態 = 成功対象 = ユーザー プリンシパル名 | 承認されたドメインからのエントリではない場合、または既知のブロックされたドメインのものである場合、アラートを送信するように構成します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/MultipleDataSources/Accountcreatedfromnon-approvedsources.yaml) |
| 特権ロールに割り当てられたアカウント。 | 高 | Microsoft Entra 監査ログ | アクティビティ: ユーザーの追加状態 = 成功およびアクティビティ: ユーザーの削除状態 = 成功およびアクティビティ: ロールへのメンバーの追加状態 = 成功 | アカウントが Microsoft Entra ロール、Azure ロール、または特権グループメンバーシップに割り当てられている場合、アラートを作成して調査を優先してください。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/UserAssignedPrivilegedRole.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

特権アカウントと非特権アカウントの両方を監視し、アラートを生成する必要があります。 ただし、特権アカウントは管理者アクセス許可を持っているため、監視、アラート、対応のプロセスにおいて優先順位を高くする必要があります。

#### 名前付けポリシーに従っていないアカウント

名前付けポリシーに従っていないユーザー アカウントは、組織のポリシーに基づかずに作成された可能性があります。

ベスト プラクティスは、ユーザー オブジェクトに名前付けポリシーを設定することです。 名前付けポリシーを設定することで、管理が容易になり、一貫性を保つことができます。 このポリシーがあると、承認されたプロセス以外でユーザーが作成された場合に検出するのにも役立ちます。 不正なアクターは、名前付け標準を認識していない可能性があり、組織のプロセス以外でプロビジョニングされたアカウントを検出しやすくする可能性があります。

組織には、ユーザー アカウントや特権アカウントの作成に使用される特定の形式や属性が存在する傾向があります。 次に例を示します。

- 管理者アカウントの UPN = ADM\_firstname.lastname@tenant.onmicrosoft.com
- ユーザー アカウントの UPN = Firstname.Lastname@contoso.com

ユーザー アカウントには、実際のユーザーを識別するための属性が設定されていることがよくあります。 たとえば、EMPID = XXXNNN などです。 組織の標準を定義するために、およびアカウントが名前付け規則に従わない場合にログ エントリのベースラインを定義するときに、次の提案を使用します。

- 名前付け規則に従っていないアカウント。 たとえば、`nnnnnnn@contoso.com` と `firstname.lastname@contoso.com` などです。
- 標準の属性が設定されていない、または正しい形式ではないアカウント。 たとえば、有効な従業員 ID がない場合などです。

| 監視項目 | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 想定される属性が定義されていないユーザー アカウント。 | 低 | Microsoft Entra 監査ログ | アクティビティ: ユーザーの追加状態 = 成功 | 標準の属性が null 値または誤った形式であるアカウントを探します。 たとえば、EmployeeID [Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/Useraccountcreatedwithoutexpectedattributesdefined.yaml) |
| 誤った名前付け形式で作成されたユーザー アカウント。 | 低 | Microsoft Entra 監査ログ | アクティビティ: ユーザーの追加状態 = 成功 | 名前付けポリシーに従わない UPN のアカウントを探します。 [Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/UserAccountCreatedUsingIncorrectNamingFormat.yaml) |
| 名前付けポリシーに従わない特権アカウント。 | 高 | Azure サブスクリプション | [Azure portal を使用して Azure でのロールの割り当てを一覧表示する - Azure RBAC](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-list-portal) | サブスクリプションのロール割り当てを一覧表示し、サインイン名が組織の形式と一致していない場合にアラートを生成します。 たとえば、プレフィックスとしての ADM\_ です。 |
| 名前付けポリシーに従わない特権アカウント。 | 高 | Microsoft Entra ディレクトリ | [Microsoft Entra ロールの割り当てを一覧表示する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/view-assignments) | UPN が組織の形式と一致しない Microsoft Entra ロール アラートのロール割り当てを一覧表示します。 たとえば、プレフィックスとしての ADM\_ です。 |

解析の詳細については、以下を参照してください。

- Microsoft Entra 監査ログ - [Azure Monitor ログのテキスト データの解析](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/parse-text)
- Azure サブスクリプション - [Azure PowerShell を使用して Azure でのロールの割り当てを一覧表示する](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-list-powershell)
- Microsoft Entra ID - [Microsoft Entra ロールの割り当てを一覧表示する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/view-assignments)

#### 通常のプロセス以外で作成されたアカウント

ユーザーと特権アカウントを作成する標準のプロセスを用意することは、ID のライフサイクルを安全に制御するために重要です。 確立されたプロセス以外でユーザーのプロビジョニングとプロビジョニング解除が行われると、それがセキュリティ リスクになる可能性があります。 また、確立されたプロセス以外での運用によって、ID 管理上の問題が発生する可能性があります。 次のようなリスクが考えられます。

- ユーザー アカウントと特権アカウントが、組織のポリシーに従って管理されない可能性があります。 その結果、正しく管理されていないアカウントに対する攻撃対象領域が広がる可能性があります。
- 不正なアクターが悪意のある目的でアカウントを作成しても、検出することが難しくなります。 確立された手順以外で有効なアカウントが作成されることで、悪意のある目的でアカウントが作成されたり、アクセス許可が変更されたりしても、検出することが難しくなります。

ユーザー アカウントと特権アカウントは、必ず組織のポリシーに従って作成することをお勧めします。 たとえば、正しい名前付け標準、組織の情報、適切な ID ガバナンスのスコープ内でアカウントを作成する必要があります。 ID を作成、管理、および削除する権限を誰が持っているかについて、組織は厳格に管理する必要があります。 このようなアカウントを作成するロールは厳重に管理し、これらのアクセス許可を承認および取得する確立されたワークフローに従った後にのみ、その権限を使用できるようにする必要があります。

| 監視項目 | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 承認されていないユーザーまたはプロセスによって作成または削除されたユーザー アカウント。 | 中 | Microsoft Entra 監査ログ | アクティビティ: ユーザーの追加状態 = 成功および - またはアクティビティ: ユーザーの削除状態 = 成功および開始者 (アクター) = ユーザー プリンシパル名 | 承認されていないユーザーまたはプロセスによって作成されたアカウントについてアラートを生成します。 高い特権を使用して作成されたアカウントを優先します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/AccountCreatedDeletedByNonApprovedUser.yaml) |
| 承認されていないソースから作成または削除されたユーザー アカウント。 | 中 | Microsoft Entra 監査ログ | アクティビティ: ユーザーの追加状態 = 成功またはアクティビティ: ユーザーの削除状態 = 成功および対象 = ユーザー プリンシパル名 | 承認されていないドメインまたは既知のブロックされたドメインである場合にアラートを生成します。 |

### 通常とは異なるサインイン

ユーザー認証に失敗するのは普通のことです。 ただし、失敗のパターンやブロックが見られる場合、ユーザーの ID に何かが起こっている兆候である可能性があります。 たとえば、パスワード スプレーやブルート フォース攻撃の場合、またはユーザー アカウントが侵害された場合などです。 パターンが出現したときに監視し、アラートを生成することが重要です。 これにより、ユーザーと組織のデータを確実に保護することができます。

成功すると、すべてがうまくいっているように見えます。 ただし、それは不正なアクターがサービスへのアクセスに成功したことを意味する可能性があります。 ログインの成功を監視することで、アクセス権を取得しているユーザー アカウントであっても、アクセス権を持つべきユーザー アカウントではない場合を検出することができます。 ユーザー認証の成功は、Microsoft Entra サインイン ログの通常のエントリです。 パターンが出現したときに検出するために、監視とアラートを行うことをお勧めします。 これにより、ユーザー アカウントと組織のデータを確実に保護することができます。

ログの監視とアラートの戦略を策定して運用するとき、Azure Portal で利用できるツールを検討してください。 Microsoft Entra ID 保護は、ID ベースのリスクの検出、保護、修復を自動化できるようにします。 ID 保護は、リスクの検出とユーザーとログインにリスク スコアを割り当てるため、インテリジェンス主導型機械学習とヒューリスティック システムを使用しています。お客様は、アクセスを許可または拒否するタイミングや、ユーザーがリスクから安全に自己修復できるようにするため、リスク レベルに基づいてポリシーを構成することができます。 次の ID 保護のリスク検出は、現在のリスク レベルを通知します。

| 監視項目 | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 漏えいした資格情報のユーザー リスク検出 | 高 | Microsoft Entra のリスク検出ログ | UX: 漏えいした資格情報 API: 「[riskDetection リソースの種類 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection)」を参照してください | 「[リスクとは? Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| Microsoft Entra 脅威インテリジェンスのユーザー リスク検出 | 高 | Microsoft Entra のリスク検出ログ | UX: Microsoft Entra のセキュリティインテリジェンス API: 「[riskDetection リソースの種類 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection)」を参照してください | 「[リスクとは? Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 匿名 IP アドレスのサインイン リスク検出 | 場合により異なる | Microsoft Entra のリスク検出ログ | UX: 匿名 IP アドレス API: 「[riskDetection リソースの種類 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection)」を参照してください | 「[リスクとは? Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 通常とは異なる旅行のサインイン リスク検知 | 場合により異なる | Microsoft Entra のリスク検出ログ | UX: 通常とは異なる移動 API: 「[riskDetection リソースの種類 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection)」を参照してください | 「[リスクとは? Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 異常なトークン | 場合により異なる | Microsoft Entra のリスク検出ログ | UX: 異常なトークン API: 「[riskDetection リソースの種類 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection)」を参照してください | 「[リスクとは? Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| マルウェアにリンクした IP アドレスのサインイン リスク検出 | 場合により異なる | Microsoft Entra のリスク検出ログ | UX: マルウェアにリンクした IP アドレス API: 「[riskDetection リソースの種類 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection)」を参照してください | 「[リスクとは? Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 疑わしいブラウザーのサインイン リスク検出 | 場合により異なる | Microsoft Entra のリスク検出ログ | UX: 疑わしいブラウザー API: 「[riskDetection リソースの種類 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection)」を参照してください | 「[リスクとは? Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 見慣れないサインイン プロパティによるリスクの検出 | 場合により異なる | Microsoft Entra のリスク検出ログ | UX: 見慣れないサインイン属性 API: 「[riskDetection リソースの種類 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection)」を参照してください | 「[リスクとは? Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 悪意のある IP アドレスのサインイン リスク検出 | 場合により異なる | Microsoft Entra のリスク検出ログ | UX: 悪意のある IP アドレスAPI: 「[riskDetection リソースの種類 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection)」を参照してください | 「[リスクとは? Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 受信トレイ操作ルールの不審なサインインリスク検出 | 場合により異なる | Microsoft Entra のリスク検出ログ | UX: 受信トレイ操作に関する疑わしいルールAPI: 「[riskDetection リソースの種類 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection)」を参照してください | 「[リスクとは? Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| パスワードスプレー攻撃のサインインリスク検知 | 高 | Microsoft Entra のリスク検出ログ | UX: パスワード スプレーAPI: 「[riskDetection リソースの種類 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection)」を参照してください | 「[リスクとは? Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| あり得ない移動のサインイン リスク検出 | 場合により異なる | Microsoft Entra のリスク検出ログ | UX: あり得ない移動API: 「[riskDetection リソースの種類 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection)」を参照してください | 「[リスクとは? Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 新しい国またはリージョンのサインインリスク検出 | 場合により異なる | Microsoft Entra のリスク検出ログ | UX: 新しい国または地域API: 「[riskDetection リソースの種類 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection)」を参照してください | 「[リスクとは? Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 匿名IPアドレスからのサインイン活動リスク検出 | 場合により異なる | Microsoft Entra のリスク検出ログ | UX: 匿名 IP アドレスからのアクティビティAPI: 「[riskDetection リソースの種類 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection)」を参照してください | 「[リスクとは? Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 疑わしい受信トレイ転送のサインインリスク検出 | 場合により異なる | Microsoft Entra のリスク検出ログ | UX: 受信トレイからの疑わしい転送API: 「[riskDetection リソースの種類 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection)」を参照してください | 「[リスクとは? Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| Microsoft Entra 脅威インテリジェンスのサインイン リスク検出 | 高 | Microsoft Entra のリスク検出ログ | UX: Microsoft Entra の脅威インテリジェンスAPI: 「[riskDetection リソースの種類 - Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/resources/riskdetection)」を参照してください | 「[リスクとは? Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)」を参照してください。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

詳細については、「[ID 保護とは](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)」を参照してください。

#### 注意点

Microsoft Entra サインイン ログ内のデータに対して監視を構成し、アラートが生成され、組織のセキュリティ ポリシーに準拠していることを確認します。 この例を次にいくつか示します。

- **認証の失敗**: 人は誰でも、パスワードをときどき間違えるものです。 ただし、認証に何度も失敗するということは、不正なアクターがアクセスを取得しようとしていることを示す可能性があります。 攻撃の強さはさまざまですが、1 時間に数回の試行から、はるかに高い頻度のものまであります。 たとえば、パスワード スプレーの場合、通常は多数のアカウントに対して簡単なパスワードが試行されるのに対し、ブルート フォースの場合はターゲットのアカウントに対して多数のパスワードが試行されます。
- **認証の割り込み**: Microsoft Entra ID の割り込みは、認証を充足するためのプロセスが挿入されたことを表します（条件付きアクセス ポリシーの制御を適用する場合など）。 これは通常のイベントであり、アプリケーションが正しく構成されていない場合に発生する可能性があります。 ただし、あるユーザー アカウントに対して多数の割り込みが発生した場合、そのアカウントに何かが発生していることを示す可能性があります。

    - たとえば、サインイン ログでユーザーをフィルターし、サインインのステータスが "割り込み"、条件付きアクセスが "失敗" であるケースが多く見られるとします。 さらに掘り下げると、認証の詳細に、パスワードは正しいが、強力な認証が必要であると示されることがあります。 これは、ユーザーが多要素認証 (MFA) を完了していないことを意味しており、ユーザーのパスワードが漏えいしていて、不正なアクターが MFA を完了できないことを示している可能性があります。
- **スマート ロックアウト**: Microsoft Entra ID には、認証プロセスになじみのある場所となじみのない場所の概念を導入するスマート ロックアウト サービスが用意されています。 なじみのある場所にアクセスしたユーザー アカウントは認証に成功しても、同じ場所になじみのない不正なアクターは数回の試行後にブロックされます。 ロックアウトされたアカウントを探し、さらに調査します。
- **IP の変更**: 異なる IP アドレスから送信されるユーザーが確認されるのは通常のことです。 ただし、ゼロ トラスト状態では、決して信用せず、常に検証します。 大量の IP アドレスとサインインの失敗が確認される場合、侵入の兆候である可能性があります。 複数の IP アドレスから発生する多数の認証失敗のパターンを探します。 仮想プライベート ネットワーク (VPN) 接続は擬陽性を引き起こす可能性があることに注意してください。 課題に関係なく、IP アドレスの変更を監視し、可能であれば Microsoft Entra ID Protection を使用して、これらのリスクを自動的に検出して軽減することをお勧めします。
- **場所**: 一般的に、ユーザー アカウントは地理的に同じ場所にあることを想定します。 また、従業員やビジネス関係者がいる場所からのサインインも想定します。 ユーザー アカウントが海外の異なる場所から、そこまで移動するのにかかる時間よりも短い時間で送信された場合、そのユーザー アカウントは悪用されていることを示す可能性があります。 VPN は擬陽性を引き起こす可能性があるため、地理的に離れた場所からサインインするユーザー アカウントを監視し、可能であれば Microsoft Entra ID Protection を使用して、これらのリスクを自動的に検出して軽減することをお勧めします。

このリスク領域については、標準のユーザー アカウントと特権アカウントを監視しますが、特権アカウントの調査を優先することをお勧めします。 特権アカウントは、どの Microsoft Entra テナントでも最も重要なアカウントです。 特権アカウントに固有のガイダンスについては、セキュリティ運用の特権アカウントに関するページを参照してください。

#### 検出する方法

Microsoft Entra ID 保護と Microsoft Entra のログイン ログを使用し、異常なログインの特徴によって示される脅威を検出に活用します。 詳細については、「[Identity 保護とは](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)」を参照してください。 監視とアラートの目的で、Azure Monitor または SIEM にデータをレプリケートすることもできます。 環境の通常を定義し、ベースラインを設定するには、次のことを判断します。

- ユーザー ベースで通常と見なされるパラメーター。
- ユーザーがサービス デスクに電話するか、セルフサービス パスワード リセットを実行するまでの、ある期間におけるパスワードの平均試行回数。
- アラートを生成するまでに許容する失敗試行の回数と、それがユーザー アカウントと特権アカウントで異なるかどうか。
- アラートを生成するまでに許容する MFA 試行の回数と、それがユーザー アカウントと特権アカウントで異なるかどうか。
- レガシ認証が有効であり、使用を中止するためのロードマップがあるかどうか。
- 既知のエグレス IP アドレスは組織のものである。
- 操作を行っているユーザーが所在している国またはリージョン。
- ネットワーク上の場所または国やリージョン内に固定されているユーザーのグループがあるかどうか。
- 組織に固有の、異常なサインインに関するその他のメトリックを特定します。 たとえば、組織が操業していない曜日、時間帯、年などです。

環境のアカウントにとって正常な範囲を指定したら、次のリストを考慮して監視してアラートを出すシナリオを決定し、アラートを微調整します。

- Microsoft Entra ID 保護が構成されている場合、監視とアラートを行う必要がありますか?
- 監視してアラートを出すために使用できる特権アカウントに適用されるより厳しい条件はありますか? たとえば、信頼できる IP アドレスからのみ特権アカウントを使用することを必須にするなどです。
- 設定したベースラインは積極的すぎませんか。 アラート数が多すぎると、無視または見逃されたりする可能性があります。

ID 保護を構成してセキュリティ ベースライン ポリシーをサポートする保護が確実に実行されるようにします。 たとえば、リスクが「高」の場合にユーザーをブロックするなと。 このリスク レベルは、ユーザー アカウントが侵害されていることを高い確度で示します。 サインイン リスク ポリシーとユーザー リスク ポリシーの設定の詳細については、「[ID 保護ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-policies)」を参照してください。

次の内容では、エントリの影響と重大度に基づいて重要度の高い順にリストされています。

#### 外部ユーザーのサインインの監視

| 監視項目 | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 他の Microsoft Entra テナントに対して認証を行うユーザー。 | 低 | Microsoft Entra サインイン ログ | 状態 = 成功リソースのテナントID != ホームテナントID | ユーザーが自分の組織のテナント内の ID を使用して別の Microsoft Entra テナントに対して正常に認証されたときに検出します。リソース TenantID がホーム テナント ID と等しくない場合にアラートを生成します [Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/AuditLogs/UsersAuthenticatingtoOtherAzureADTenants.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| ユーザーの状態が [ゲスト] から [メンバー] に変更された | 中 | Microsoft Entra 監査ログ | アクティビティ: ユーザーの更新カテゴリ: UserManagementUserType が [ゲスト] から [メンバー] に変更された | ユーザーの種類の [ゲスト] から [メンバー] への変更を監視し、アラートを生成します。 これは想定されていましたか?[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/UserStatechangedfromGuesttoMember.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 承認されていない招待元によってテナントに招待されたゲスト ユーザー | 中 | Microsoft Entra 監査ログ | アクティビティ: 外部ユーザーを招待するカテゴリ: UserManagement開始者 (アクター): ユーザー プリンシパル名 | 外部ユーザーを招待する承認されていないアクターを監視し、アラートを生成します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/AuditLogs/GuestUsersInvitedtoTenantbyNewInviters.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

#### 通常とは異なる失敗したサインインの監視

| 監視項目 | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 失敗したサインインの試行。 | 中 - 孤立したインシデントの場合高 - 多数のアカウントで同じパターンまたは VIP が発生している場合。 | Microsoft Entra サインイン ログ | 状態 = 失敗およびサインイン エラー コード 50126 - 無効なユーザー名またはパスワードにより、資格情報の検証でエラーが発生しました。 | ベースラインしきい値を定義してから、組織の行動に合わせて監視および調整し、誤ったアラートが生成されないようにします。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/SigninLogs/SpikeInFailedSignInAttempts.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| スマート ロックアウト イベント。 | 中 - 孤立したインシデントの場合高 - 多数のアカウントで同じパターンまたは VIP が発生している場合。 | Microsoft Entra サインイン ログ | 状態 = 失敗およびサインイン エラー コード = 50053 - IdsLocked | ベースラインしきい値を定義してから、組織の行動に合わせて監視および調整し、誤ったアラートが生成されないようにします。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/SigninLogs/SmartLockouts.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 割り込み | 中 - 孤立したインシデントの場合高 - 多数のアカウントで同じパターンまたは VIP が発生している場合。 | Microsoft Entra サインイン ログ | 500121、強力な認証の要求時に、認証に失敗しました。 または50097、デバイス認証が必要です、または 50074、強力な認証が必要です。 または50155、デバイス認証失敗または50158、ExternalSecurityChallenge - 外部セキュリティ チャレンジが達成されませんでしたまたは53003 と失敗の理由 = 条件付きアクセスによるブロック | 割り込みについて監視し、アラートを生成します。ベースラインしきい値を定義してから、組織の行動に合わせて監視および調整し、誤ったアラートが生成されないようにします。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/MultipleDataSources/AADPrivilegedAccountsFailedMFA.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

次の内容では、エントリの影響と重大度に基づいて重要度の高い順にリストされています。

| 監視項目 | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 多要素認証 (MFA) の不正アラート。 | 高 | Microsoft Entra サインイン ログ | 状態 = 失敗および詳細 は MFA 拒否 です | エントリについて監視し、アラートを生成します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/SigninLogs/MFARejectedbyUser.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 事業を行っていない国またはリージョンからの認証の失敗。 | 中くらい | Microsoft Entra サインイン ログ | 場所 = &lt;未承認の場所&gt; | すべてのエントリについて監視し、アラートを生成します。 [Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/SigninLogs/AuthenticationAttemptfromNewCountry.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| レガシ プロトコルまたは使用されていないプロトコルの失敗した認証。 | 中 | Microsoft Entra サインイン ログ | 状態 = 失敗およびクライアント アプリ = その他のクライアント、POP、IMAP、MAPI、SMTP、ActiveSync | すべてのエントリについて監視し、アラートを生成します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/9bd30c2d4f6a2de17956cd11536a83adcbfc1757/Hunting%20Queries/SigninLogs/LegacyAuthAttempt.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 条件付きアクセスによってブロックされた失敗。 | 中間 | Microsoft Entra サインイン ログ | エラー コード = 53003 および失敗の理由 = 条件付きアクセスによるブロック | すべてのエントリについて監視し、アラートを生成します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/SigninLogs/UserAccounts-CABlockedSigninSpikes.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 任意の種類の認証失敗の増加。 | ミディアム | Microsoft Entra サインイン ログ | 全体として失敗の増加を記録します。 つまり、今日の失敗率は、前週の同じ曜日の 10% であることを意味します。 | しきい値を設定していない場合は、失敗が 10% 以上増えた場合に監視してアラートを生成します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/SigninLogs/SpikeInFailedSignInAttempts.yaml) |
| 国またはリージョンの通常の業務を行っていない時間帯と曜日に発生した認証。 | 低 | Microsoft Entra サインイン ログ | 通常の業務が行われていない曜日または時間帯に発生した対話型の認証をキャプチャします。 状態 = 成功および場所 = &lt;場所&gt;およびDay\Time = &lt;通常の作業時間ではありません&gt; | すべてのエントリについて監視し、アラートを生成します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/MultipleDataSources/AnomolousSignInsBasedonTime.yaml) |
| アカウントがサインインで無効またはブロックされる | 低 | Microsoft Entra サインイン ログ | 状態 = 失敗およびエラー コード = 50057、ユーザー アカウントは無効になっています。 | これは、誰かが組織を退職した後にアカウントにアクセスしようとしていることを示している可能性があります。 このアカウントはブロックされていますが、このアクティビティについて記録し、アラートを出すことが重要です。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/SigninLogs/UserAccounts-BlockedAccounts.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

#### 通常とは異なるサインインの監視

| 監視項目 | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 想定されるコントロールの範囲外の特権アカウントの認証。 | 高 | Microsoft Entra サインイン ログ | 状態 = 成功およびUserPrincipalName = &lt;Admin アカウント&gt;および場所 = &lt;未承認の場所&gt;およびIP アドレス = &lt;未承認の IP&gt;デバイス情報= &lt;承認されていないブラウザー、オペレーティング システム&gt; | 想定されるコントロールの範囲外で特権アカウントの認証が成功した場合に監視し、アラートを生成します。 3 つの一般的なコントロールがあります。 [Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/SigninLogs/AuthenticationsofPrivilegedAccountsOutsideofExpectedControls.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 単一要素認証のみが必要な場合。 | 低 | Microsoft Entra サインイン ログ | 状態 = 成功認証要件 = 単一要素認証 | 定期的に監視し、想定される動作であることを確認します。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| MFA に登録されていない特権アカウントを検出します。 | 高 | Azure Graph API | 管理者アカウントの IsMFARegistered eq false のクエリ。 [credentialUserRegistrationDetails の一覧表示 - Microsoft Graph ベータ版](https://learn.microsoft.com/ja-jp/graph/api/reportroot-list-credentialuserregistrationdetails?view=graph-rest-beta&preserve-view=true&tabs=http) | 監査と調査を行って、意図的なのか、見落としなのかを判断します。 |
| 組織が事業を行っていない国またはリージョンからの認証の成功。 | 中 | Microsoft Entra サインイン ログ | 状態 = 成功場所 = &lt;未承認の国やリージョン&gt; | 指定された市区町村名と等しくないエントリを監視し、アラートを生成します。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 認証の成功、条件付きアクセスによってブロックされたセッション。 | 中 | Microsoft Entra サインイン ログ | 状態 = 成功およびエラー コード = 53003 - 失敗の理由、条件付きアクセスによるブロック | 認証に成功しても、条件付きアクセスによってセッションがブロックされた場合を監視し、調査します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Detections/SigninLogs/UserAccounts-CABlockedSigninSpikes.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| レガシ認証を無効にした後の認証の成功。 | 中 | Microsoft Entra サインイン ログ | 状態 = 成功 およびクライアント アプリ = その他のクライアント、POP、IMAP、MAPI、SMTP、ActiveSync | 組織でレガシ認証を無効にした場合は、レガシ認証が成功したときを監視し、アラートを生成します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/9bd30c2d4f6a2de17956cd11536a83adcbfc1757/Hunting%20Queries/SigninLogs/LegacyAuthAttempt.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |

単一要素認証のみが必要な中事業影響度 (MBI) と高事業影響度 (HBI) のアプリケーションに対する認証を定期的に確認することをお勧めします。 それぞれについて、単一要素認証が想定されていたかどうかを判断する必要があります。 さらに、認証が成功したケースの増加をレビューすることや、場所に基づき、予期しない時間帯において確認します。

| 監視項目 | リスク レベル | どこ | フィルターまたはサブフィルター | メモ |
| --- | --- | --- | --- | --- |
| 単一要素認証を使用した MBI および HBI アプリケーションに対する認証。 | 低 | Microsoft Entra サインイン ログ | 状態 = 成功およびアプリケーション ID = &lt;HBI アプリ&gt; および認証要件 = 単一要素認証 | この構成が意図的なものであることを確認し、検証します。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 通常の業務が行われていない国や地域の特定の曜日や時間帯、または年間を通した特定の期間における認証。 | 低 | Microsoft Entra サインイン ログ | 通常の業務が行われていない曜日または時間帯に発生した対話型の認証をキャプチャします。 状態 = 成功場所 = &lt;場所&gt;日付\時間 = &lt;通常の勤務時間ではありません&gt; | 国またはリージョンにおいて、通常の業務が行われていない日付や時間帯に発生した認証について監視し、アラートを発します。[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
| 成功したログインの測定可能な増加 | 低 | Microsoft Entra サインイン ログ | 認証成功件数の増加を全体的に捉えます。 つまり、今日の成功の合計は、先週の同じ曜日と比べて&gt;で10%増加しています。 | しきい値を設定していない場合は、認証の成功が 10% 以上増えた場合に監視し、アラートを生成します。[Microsoft Sentinel テンプレート](https://github.com/Azure/Azure-Sentinel/blob/master/Hunting%20Queries/SigninLogs/UserAccountsMeasurableincreaseofsuccessfulsignins.yaml)[Sigma ルール](https://github.com/SigmaHQ/sigma/tree/master/rules/cloud/azure) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/service-accounts-computer"} -->
## Active Directory を使用してオンプレミスのコンピューター アカウントをセキュリティで保護する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-computer
- Service: entra / architecture
- Article date: 2023-02-03
- Summary: Active Directory を使用してオンプレミスのコンピューター アカウントまたは LocalSystem アカウントをセキュリティで保護するためのガイド

コンピューター アカウント (LocalSystem アカウント) は、ローカル コンピューター上のほぼすべてのリソースにアクセスできる高い特権を持つアカウントです。 このアカウントは、サインオンしたユーザー アカウントに関連付けられません。 サービスは、コンピューターの資格情報を `<domain_name>\\<computer_name>$`形式でリモート サーバーに提示することで、LocalSystem アクセス ネットワーク リソースとして実行されます。 コンピューター アカウントの定義済みの名前が `NT AUTHORITY\SYSTEM`。 サービスを開始し、そのサービスのセキュリティ コンテキストを提供できます。

[Image: コンピューター アカウント上のローカル サービスの一覧のスクリーンショット。]

### コンピューター アカウントを使用する利点

コンピューター アカウントには、次の利点があります。

- **無制限のローカル アクセス** - コンピューター アカウントは、コンピューターのローカル リソースへの完全なアクセスを提供します
- **パスワードの自動管理** - 手動でパスワードを変更する必要がなくなります。 アカウントは Active Directory のメンバーであり、そのパスワードは自動的に変更されます。 コンピューター アカウントでは、サービス プリンシパル名を登録する必要はありません。
- **コンピューター外の制限付きアクセス権** - Active Directory Domain Services (AD DS) の既定のアクセス制御リストでは、コンピューター アカウントへの最小限のアクセスが許可されます。 承認されていないユーザーによるアクセス中、サービスはネットワーク リソースへのアクセスが制限されます。

### コンピューター アカウントのセキュリティ体制の評価

次の表を使用して、コンピューター アカウントの潜在的な問題と軽減策を確認します。

| コンピューター アカウントの問題 | 緩和策 |
| --- | --- |
| コンピューター アカウントは、コンピューターがドメインを離れ、再び参加するときに、削除と再作成の対象となります。 | Active Directory グループにコンピューターを追加する要件を確認します。 グループに追加されたコンピューター アカウントを確認するには、次のセクションのスクリプトを使用します。 |
| コンピューター アカウントをグループに追加すると、そのコンピューターで LocalSystem として実行されるサービスはグループ アクセス権を取得します。 | コンピューターアカウント グループのメンバーシップは慎重に選択する。 コンピューター アカウントをドメイン管理者グループのメンバーにしないでください。 関連付けられているサービスは、AD DS に完全にアクセスできます。 |
| LocalSystem のネットワークの既定値が不正確です。 | コンピューター アカウントがネットワーク リソースへの既定の制限付きアクセス権を持っていると想定しないでください。 代わりに、アカウントのグループ メンバーシップを確認します。 |
| LocalSystem として実行される不明なサービス。 | LocalSystem アカウントで実行されるサービスが Microsoft サービスまたは信頼されたサービスであることを確認します。 |

### サービスとコンピューター アカウントを検索する

コンピューター アカウントで実行されるサービスを検索するには、次の PowerShell コマンドレットを使用します。

```powershell
Get-WmiObject win32_service | select Name, StartName | Where-Object {($_.StartName -eq "LocalSystem")}
```

特定のグループのメンバーであるコンピューター アカウントを検索するには、次の PowerShell コマンドレットを実行します。

```powershell
Get-ADComputer -Filter {Name -Like "*"} -Properties MemberOf | Where-Object {[STRING]$_.MemberOf -like "Your_Group_Name_here*"} | Select Name, MemberOf
```

ID 管理者グループ (ドメイン管理者、エンタープライズ管理者、管理者) のメンバーであるコンピューター アカウントを検索するには、次の PowerShell コマンドレットを実行します。

```powershell
Get-ADGroupMember -Identity Administrators -Recursive | Where objectClass -eq "computer"
```

### コンピューター アカウントの推奨事項

重要

コンピューター アカウントは高い特権を持っているため、サービスがコンピューター上のローカル リソースへの無制限のアクセスを必要とし、マネージド サービス アカウント (MSA) を使用できない場合に使用します。

- サービス所有者のサービスが MSA で実行されたことを確認する
- サービスでサポートされている場合は、グループ管理サービス アカウント (gMSA) またはスタンドアロンマネージド サービス アカウント (sMSA) を使用する
- サービスの実行に必要なアクセス許可を持つドメイン ユーザー アカウントを使用する
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/service-accounts-govern-on-premises"} -->
## オンプレミスのサービス アカウントを管理する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-govern-on-premises
- Service: entra / architecture
- Article date: 2023-02-10
- Summary: オンプレミスのサービス アカウントのアカウント ライフサイクル プロセスを作成して実行する方法について説明します

Active Directory には、次の 4 種類のオンプレミス サービス アカウントが用意されています。

- グループ管理サービス アカウント (gMSA)
    - [グループ管理サービス アカウントをセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-group-managed)
- スタンドアロンマネージド サービス アカウント (sMSA)
    - [スタンドアロン管理サービス アカウントをセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-standalone-managed)
- オンプレミスのコンピューター アカウント
    - [Active Directory を使用してオンプレミスのコンピューター アカウントをセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-computer)
- サービス アカウントとして機能するユーザー アカウント
    - [Active Directory でユーザーベースのサービス アカウントをセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-user-on-premises)

サービス アカウント ガバナンスの一部には、次のものが含まれます。

- 要件と目的に基づいて保護する
- アカウントのライフサイクルとその資格情報の管理
- リスクとアクセス許可に基づいてサービス アカウントを評価する
- Active Directory (AD) と Microsoft Entra ID に、アクセス許可を持つ未使用のサービス アカウントがないことを確認する

### 新しいサービスアカウントのガイドライン

サービス アカウントを作成するときは、次の表の情報を考慮してください。

| 原則 | 考慮事項 |
| --- | --- |
| サービス アカウントのマッピング | サービス アカウントをサービス、アプリケーション、またはスクリプトに接続する |
| 所有権 | 要求して責任を負うアカウント所有者が存在することを確認する |
| Scope | スコープを定義し、使用期間を予測する |
| 目的 | 1 つの目的でサービス アカウントを作成する |
| 権限 | 最小限のアクセス許可の原則を適用する: - 管理者などの組み込みグループにアクセス許可を割り当てない - ローカル コンピューターのアクセス許可を削除する (可能な場合) - アクセス権を調整し、ディレクトリ アクセスに AD 委任を使用する - 詳細なアクセス許可を使用する - ユーザーベースのサービス アカウントに対するアカウントの有効期限と場所の制限を設定する |
| 使用の監視と監査 | - サインイン データを監視し、目的の使用状況と一致していることを確認します - 異常な使用に関するアラートを設定する |

#### ユーザー アカウントの制限

サービス アカウントとして使用されるユーザー アカウントの場合は、次の設定を適用します。

- **アカウントの有効期限** - アカウントを続行できない限り、レビュー期間後にサービス アカウントの有効期限が自動的に切れるよう設定します
- **LogonWorkstations**- サービス アカウントのサインインアクセス許可を制限する
    - ローカルで実行され、コンピューター上のリソースにアクセスする場合は、他の場所へのサインインを制限します
- **パスワードを変更できない** - サービス アカウントが独自のパスワードを変更できないようにするには、パラメーターを **true** に設定します

### ライフサイクル管理プロセス

サービス アカウントのセキュリティを維持するために、サービス アカウントを初期から使用停止まで管理します。 次の手順を使用します。

1. アカウントの使用状況情報を収集します。
2. サービス アカウントとアプリを構成管理データベース (CMDB) に移動します。
3. リスク評価または正式なレビューを実行します。
4. サービス アカウントを作成し、制限を適用します。
5. 定期的なレビューをスケジュールして実行します。
6. 必要に応じてアクセス許可とスコープを調整します。
7. アカウントのプロビジョニングを解除します。

#### サービス アカウントの使用状況情報を収集する

各サービス アカウントの関連情報を収集します。 次の表に、収集する最小限の情報を示します。 各アカウントを検証するために必要なものを取得します。

| データ | 説明 |
| --- | --- |
| オーナー | サービス アカウントに対して責任を負うユーザーまたはグループ |
| 目的 | サービス アカウントの目的 |
| アクセス許可 (スコープ) | 必要なアクセス許可 |
| CMDB のリンク | ターゲット スクリプトまたはアプリケーションと所有者とのクロスリンク サービス アカウント |
| リスク | セキュリティ リスク評価の結果 |
| 有効期間 | アカウントの有効期限または再認定をスケジュールするために予想される最大有効期間 |

アカウント要求をセルフサービスで行い、関連情報を要求します。 所有者は、アプリケーションまたはビジネス所有者、IT チーム メンバー、またはインフラストラクチャ所有者です。 要求と関連情報には、Microsoft Forms を使用できます。 アカウントが承認されている場合は、Microsoft Forms を使用して構成管理データベース (CMDB) インベントリ ツールに移植します。

#### サービス アカウントと CMDB

収集した情報を CMDB アプリケーションに格納します。 インフラストラクチャ、アプリ、プロセスへの依存関係を含めます。 この中央リポジトリを使用して、次の操作を行います。

- リスクを評価する
- 制限を使用してサービス アカウントを構成する
- 機能とセキュリティの依存関係を確認する
- セキュリティと継続的なニーズに関する定期的なレビューを実施する
- サービス アカウントを確認、廃止、変更するには、所有者に問い合わせてください

##### HR シナリオの例

たとえば、人事 SQL データベースに接続するアクセス許可を持つ Web サイトを実行するサービス アカウントがあります。 例を含む、サービス アカウント CMDB の情報を次の表に示します。

| データ | 例 |
| --- | --- |
| 所有者、Deputy | 名前、名前 |
| 目的 | HR Web ページを実行し、HR データベースに接続します。 データベースにアクセスするときは、エンド ユーザーの権限を借用します。 |
| アクセス許可、範囲 | HR-WEBServer: ローカルでサインインします。Web ページの実行HR-SQL1: ローカルでサインイン; HR データベースの読み取り権限HR-SQL2: ローカルでサインインします。Salary データベースに対する読み取りアクセス許可のみ |
| コスト センター | 123456 |
| 評価されたリスク | 中程度;ビジネスへの影響: 中;個人情報中程度 |
| アカウントの制限 | サインイン先: 前述のサーバーのみ。パスワードを変更できません。MBI-Password ポリシー; |
| 有効期間 | 無制限 |
| レビュー サイクル | 年に2回: 所有者、セキュリティチーム、またはプライバシーチームによって |

#### サービス アカウントのリスク評価または正式なレビュー

承認されていないソースによってアカウントが侵害された場合は、関連するアプリケーション、サービス、インフラストラクチャに対するリスクを評価します。 直接的および間接的なリスクを考慮します。

- 承認されていないユーザーがアクセスできるリソース
    - サービス アカウントがアクセスできるその他の情報またはシステム
- アカウントが付与できるアクセス許可
    - アクセス許可が変更されたときの表示または通知

リスク評価の後、書類はリスクがアカウントに影響を与えることを示す可能性があります。

- 制約
- 有効期間
- 要件を確認する
    - ケイデンスと校閲者

#### サービス アカウントを作成し、アカウント制限を適用する

注

リスク評価後にサービス アカウントを作成し、CMDB で結果を文書化します。 アカウントの制限をリスク評価の結果に合わせます。

評価に関連しないものもありますが、次の制限事項を考慮してください。

- サービス アカウントとして使用されるユーザー アカウントの場合は、現実的な終了日を定義します
    - アカウントの **有効期限フラグを** 使用して日付を設定する
    - 詳細情報: [Set-ADAccountExpiration](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/set-adaccountexpiration)
- 参照、 [Set-ADUser (Active Directory)](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/set-aduser)
- パスワード ポリシーの要件
    - [Microsoft Entra Domain Services マネージド ドメインのパスワードとアカウントロックアウトポリシー](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/password-policy)を参照してください
- 一部のユーザーのみがアカウントを管理できるようにする組織単位の場所にアカウントを作成する
    - [アカウント OU とリソース OU の管理の委任](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/delegating-administration-of-account-ous-and-resource-ous)に関する説明を参照してください
- サービス アカウントの変更を検出する監査を設定し収集します。
    - 「[ディレクトリ サービスの変更の監査](https://learn.microsoft.com/ja-jp/windows/security/threat-protection/auditing/audit-directory-service-changes)」を参照し、
    - [AD で Kerberos 認証イベントを監査する方法](https://www.manageengine.com/products/active-directory-audit/how-to/audit-kerberos-authentication-events.html)の manageengine.com に移動する
- 運用環境に移行する前に、アカウントアクセスをより安全に付与する

#### サービス アカウントのレビュー

定期的なサービス アカウント レビュー 、特に中リスクと高リスクに分類されたレビューをスケジュールします。 レビューには次のものが含まれます。

- アカウントの必要性について所有者が証明し、アクセス許可とスコープの正当な理由を説明します。
- アップストリームとダウンストリームの依存関係を含むプライバシーとセキュリティのチーム レビュー
- 監査データのレビュー
- アカウントが明記された目的で使用されていることを確認する

#### サービス アカウントをプロビジョニング解除する

次の手順でサービス アカウントのプロビジョニングを解除します。

- サービス アカウントが作成されたスクリプトまたはアプリケーションの提供終了
- サービス アカウントが使用されたスクリプトまたはアプリケーション関数の廃止
- サービス アカウントを別のアカウントに置き換える

プロビジョニングを解除するには:

1. アクセス許可と監視を削除します。
2. 関連するサービス アカウントのサインインとリソース アクセスを調べて、それらに潜在的な影響がないことを確認します。
3. アカウントのサインインを禁止します。
4. アカウントが不要になっていることを確認します (苦情はありません)。
5. アカウントを無効にする時間を決定するビジネス ポリシーを作成します。
6. サービス アカウントを削除します。

- **MSA** - [Uninstall-ADServiceAccount](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/uninstall-adserviceaccount?view=winserver2012-ps&preserve-view=true)を参照
    - PowerShell を使用するか、マネージド サービス アカウント コンテナーから手動で削除する
- **コンピューターまたはユーザー アカウント** - Active Directory からアカウントを手動で削除する
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/service-accounts-group-managed"} -->
## グループ管理サービス アカウントをセキュリティで保護する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-group-managed
- Service: entra / architecture
- Article date: 2023-02-09
- Summary: グループ管理サービス アカウント (gMSA) のセキュリティ保護に関するガイド

グループ管理サービス アカウント (gMSA) は、サービスのセキュリティ保護のためのドメイン アカウントです。 gMSA は、1 台のサーバー上、またはサーバー ファーム (ネットワーク負荷分散やインターネット インフォメーション サービス (IIS) サーバーの背後にあるシステムなど) で実行できます。 gMSA プリンシパルを使用するようにサービスを構成すると、アカウントのパスワード管理は Windows オペレーティング システム (OS) によって処理されます。

### gMSA の利点

gMSA は、管理オーバーヘッドを削減するのに役立つ、セキュリティが強化された ID ソリューションです。

- **強力なパスワードの設定** - ランダムに生成される 240 バイトのパスワード: 長くて複雑な gMSA パスワードより、ブルート フォース攻撃や辞書攻撃による侵害の可能性を最小限に抑えます。
- **パスワードの定期的な循環** - パスワード管理は Windows OS に移行し、30 日ごとにパスワードが変更されます。 サービス管理者とドメイン管理者が、パスワードの変更をスケジュールしたり、サービス停止を管理したりする必要はありません。
- **サーバー ファームへのデプロイのサポート** - gMSA を複数のサーバーにデプロイして、複数のホストで同じサービスが実行される負荷分散ソリューションをサポートします。
- **簡略化されたサービス プリンシパル名 (SPN) 管理のサポート**- アカウントの作成時に PowerShell を使用して SPN を設定します。
    - また、gMSA のアクセス許可が正しく設定されていれば、自動 SPN 登録がサポートされているサービスでは、gMSA に対してそれを行うことができます。

### gMSA の使用

フェールオーバー クラスタリングなどのサービスでサポートされていない場合を除き、gMSA はオンプレミス サービスのアカウントの種類として使用します。

重要

運用環境に移行する前に、gMSA を使用してサービスをテストします。 アプリケーションが gMSA を使用し、リソースにアクセスするように、テスト環境を設定します。 詳細については、「[グループ管理サービス アカウントのサポート](https://learn.microsoft.com/ja-jp/system-center/scom/support-group-managed-service-accounts?view=sc-om-2022&preserve-view=true)」を参照してください。

サービスで gMSA の使用がサポートされていない場合は、スタンドアロン管理サービス アカウント (sMSA) を使用できます。 sMSA にも同じ機能がありますが、単一サーバーでのデプロイを対象としています。

サービスによってサポートされている gMSA または sMSA を使用できない場合、標準ユーザー アカウントとして実行するようにサービスを構成します。 アカウントのセキュリティを維持するために、サービスとドメインの管理者は強力なパスワード管理プロセスを監視する必要があります。

### gMSA セキュリティ体制を評価する

gMSA は、継続的なパスワード管理を必要とする標準ユーザー アカウントよりも安全性が高くなります。 ただし、セキュリティ体制に関連して、gMSA のアクセス スコープを検討してください。 gMSA を使用する場合の潜在的なセキュリティ問題とその軽減策を次の表に示します。

| セキュリティ上の問題 | 対応策 |
| --- | --- |
| gMSA が特権グループのメンバーである | - グループ メンバーシップを確認します。 グループ メンバーシップを列挙する PowerShell スクリプトを作成します。 gMSA ファイルの名前で結果の CSV ファイルをフィルター処理します - 特権グループから gMSA を削除します - サービスの実行に必要な gMSA 権限とアクセス許可を付与します。 サービス ベンダーに問い合わせてください。 |
| gMSA に、機密リソースへの読み取りおよび書き込みアクセス権がある | - 機密性の高いリソースへのアクセスを監査します - 監査ログを Azure Log Analytics や Microsoft Sentinel などの SIEM にアーカイブして分析します - 不要なアクセス レベルがある場合は、不要なリソース許可を削除します |

### gMSA を検出する

#### Managed Service Accounts コンテナー

効率的な動作のためには、gMSA が Active Directory ユーザーとコンピューターの管理サービス アカウント コンテナーに含まれている必要があります。

この一覧にないサービス MSA を見つけるには、次のコマンドを実行します。

```powershell

Get-ADServiceAccount -Filter *

# This PowerShell cmdlet returns managed service accounts (gMSAs and sMSAs). Differentiate by examining the ObjectClass attribute on returned accounts.

# For gMSA accounts, ObjectClass = msDS-GroupManagedServiceAccount

# For sMSA accounts, ObjectClass = msDS-ManagedServiceAccount

# To filter results to only gMSAs:

Get-ADServiceAccount –Filter * | where-object {$_.ObjectClass -eq "msDS-GroupManagedServiceAccount"}
```

### gMSA を管理する

gMSA を管理するには、次の Active Directory PowerShell コマンドレットを使用します。

`Get-ADServiceAccount`

`Install-ADServiceAccount`

`New-ADServiceAccount`

`Remove-ADServiceAccount`

`Set-ADServiceAccount`

`Test-ADServiceAccount`

`Uninstall-ADServiceAccount`

注意

Windows Server 2012 以降のバージョンでは、\*-ADServiceAccount コマンドレットは gMSA と連携します。 詳細情報: [グループ管理サービス アカウントの概要](https://learn.microsoft.com/ja-jp/windows-server/security/group-managed-service-accounts/getting-started-with-group-managed-service-accounts)。

### gMSA に移動する

gMSA は、オンプレミス用のセキュリティで保護されたサービス アカウントの種類です。 可能であれば、gMSA を使用することをお勧めします。 さらに、お使いのサービスを Azure に移動し、お使いのサービス アカウントを Microsoft Entra ID に移動することもご検討ください。

注意

gMSA を使用するようにサービスを構成する前に、「[グループ管理サービス アカウントの概要](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-R2-and-2012/jj128431%28v=ws.11%29)」を参照してください。

gMSA に移動するには、次の手順に従います。

1. キー配布サービス (KDS) のルート キーがフォレストにデプロイされていることを確認します。 これは 1 回限りの操作です。 「[キー配布サービス KDS ルート キーの作成](https://learn.microsoft.com/ja-jp/windows-server/security/group-managed-service-accounts/create-the-key-distribution-services-kds-root-key)」を参照してください。
2. 新しい gMSA を作成します。 「[グループの管理されたサービス アカウントの使用開始](https://learn.microsoft.com/ja-jp/windows-server/security/group-managed-service-accounts/getting-started-with-group-managed-service-accounts)」を参照してください。
3. サービスを実行するホストに新しい gMSA をインストールします。
4. サービス ID を gMSA に変更します。
5. 空のパスワードを指定します。
6. お使いのサービスが新しい gMSA ID で動作していることを確認します。
7. 古いサービス アカウント ID を削除します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/service-accounts-managed-identities"} -->
## Microsoft Entra ID でのマネージド ID のセキュリティ保護 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-managed-identities
- Service: entra / architecture
- Article date: 2023-02-07
- Summary: Microsoft Entra ID でマネージド ID のセキュリティを確認、評価、強化する方法について学習します

この記事では、サービス間の通信をセキュリティで保護するためのシークレットと資格情報の管理について説明します。 マネージド ID では、Microsoft Entra ID で自動的に管理される ID が提供されます。 アプリケーションでは、マネージド ID を使用して、Microsoft Entra 認証をサポートするリソースに接続し、資格情報の管理なしで Microsoft Entra トークンを取得します。

### マネージド ID の利点

マネージド ID を使用する利点:

- マネージド ID を使用すると、資格情報は Azure によって完全に管理、ローテーション、保護されます。 ID は Azure リソースとともに提供され、削除されます。 マネージド ID により、Azure リソースと、Microsoft Entra 認証をサポートするサービスとの通信が可能になります。
- 割り当てられた特権ロールを含め、誰も資格情報にアクセスできません。これは、コードに含まれることによって誤って漏洩することはありません。

### マネージド ID の使用

マネージド ID は、Microsoft Entra 認証をサポートするサービス間の通信に最適です。 ソース システムからターゲット サービスにアクセスを要求します。 任意の Azure リソースをソース システムにすることができます。 たとえば、Azure 仮想マシン (VM)、Azure Function インスタンス、Azure App Services インスタンスでマネージド ID がサポートされています。

詳細については、「[マネージド ID を何に使用できるか](https://www.youtube.com/embed/5lqayO_oeEo)」のビデオを参照してください。

#### 認証と権限承認

マネージド ID を使用すると、ソース システムによって、所有者の資格情報の管理なしで、Microsoft Entra ID からトークンが取得されます。 資格情報は Azure で管理されます。 ソース システムによって取得されたトークンは、認証のためにターゲット システムに提示されます。

ターゲット システムは、アクセスを許可するために、ソース システムの認証および認可を行います。 ターゲット サービスで Microsoft Entra 認証がサポートされている場合は、Microsoft Entra ID によって発行されたアクセス トークンが受け入れられます。

Azure には、コントロール プレーンとデータ プレーンがあります。 コントロール プレーンでリソースを作成し、データ プレーンでアクセスします。 たとえば、コントロール プレーンで Azure Cosmos DB データベースを作成しますが、そのクエリはデータ プレーンで実行します。

ターゲット システムで認証のためにトークンが受け入れられると、コントロール プレーンとデータ プレーン用に認可のメカニズムがサポートされます。

Azure コントロール プレーン操作は Azure Resource Manager によって管理され、Azure ロールベースのアクセス制御 (Azure RBAC) を使用します。 データ プレーンでは、ターゲット システムに認可メカニズムがあります。 Azure Storage によってデータ プレーンで Azure RBAC がサポートされています。 たとえば、Azure App Services を使用するアプリケーションで Azure Storage からデータを読み取ることができ、Azure Kubernetes Service を使用するアプリケーションで Azure Key Vault に格納されているシークレットを読み取ることができます。

詳細情報:

- [Azure Resource Manager とは](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview)
- [Azure のロールベースの Azure RBAC とは](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/overview)
- [Azure コントロール プレーンとデータ プレーン](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/control-plane-and-data-plane)
- [マネージド ID を使用して他のサービスにアクセスできる Azure サービス](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-status)

### システム割り当てとユーザー割り当てのマネージド ID

マネージド ID には、システム割り当てとユーザー割り当ての 2 種類があります。

システム割り当てマネージド ID:

- Azure リソースとの一対一のリレーションシップ
    - たとえば、各 VM に関連付けられた一意のマネージド ID があります
- Azure リソースのライフサイクルに関連付けられています。 リソースが削除されると、それに関連付けられているマネージド ID が自動的に削除されます。
- このアクションにより、孤立したアカウントからのリスクが排除されます

ユーザー割り当てマネージド ID

- ライフサイクルが、Azure リソースから独立しています。 ユーザーがライフサイクルを管理します。
    - 割り当て済みのユーザー割り当てマネージド ID は、Azure リソースが削除されても自動的に削除されません
- ユーザー割り当てマネージド ID は 0 個以上の Azure リソースに割り当てます
- 事前に ID を作成し、後でリソースに割り当てます

### Microsoft Entra ID でマネージド ID サービス プリンシパルを検索する

マネージド ID を見つけるには、以下を使用できます。

- Azure portal の [エンタープライズ アプリケーション] ページ
- Microsoft Graph

#### Azure ポータル

1. Azure portal の左側のナビゲーションで、**[Microsoft Entra ID]** を選択します。
2. 左側のナビゲーションで、**[エンタープライズ アプリケーション]** を選択します。
3. **[アプリケーションの種類]** 列の **[値]** で、下矢印を選択して **[マネージド ID]** を選択します。

    [Image: [アプリケーションの種類] 列の [値] の [マネージド ID] オプションのスクリーンショット。]

#### Microsoft Graph

Microsoft Graph に対する次の GET 要求を使用して、テナント内のマネージド ID の一覧を取得します。

`https://graph.microsoft.com/v1.0/servicePrincipals?$filter=(servicePrincipalType eq 'ManagedIdentity')`

これらの要求をフィルター処理できます。 詳細については、「[servicePrincipal を取得する](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-get?view=graph-rest-1.0&tabs=http&preserve-view=true)」を参照してください。

### マネージド ID のセキュリティの評価

マネージド ID のセキュリティを評価するには、以下を行います。

- 特権を調べて、最小特権モデルが選択されていることを確認します

    - 次の Microsoft Graph コマンドレットを使用して、マネージド ID に割り当てられているアクセス許可を取得します。

    `Get-MgServicePrincipalAppRoleAssignment -ServicePrincipalId <String>`
- マネージド ID が特権グループ (管理者グループなど) に含まれていないことを確認します。

    - 高い特権を持つグループのメンバーを Microsoft Graph で列挙するには、以下を行います。

    `Get-MgGroupMember -GroupId <String> [-All <Boolean>] [-Top <Int32>] [<CommonParameters>]`

### マネージド ID に移行する

サービス プリンシパルまたは Microsoft Entra ユーザー アカウントを使用している場合は、マネージド ID の使用を評価します。 資格情報の保護、ローテーション、および管理を行う必要がなくなります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/service-accounts-on-premises"} -->
## Active Directory サービス アカウントの概要 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-on-premises
- Service: entra / architecture
- Article date: 2022-08-26
- Summary: Active Directory でのサービス アカウントの種類の概要と、それらをセキュリティで保護する方法について説明します。

サービスには、ローカル リソースとネットワーク リソースのアクセス権を決定する、プライマリ セキュリティ ID があります。 Microsoft Win32 サービスのセキュリティ コンテキストは、サービスを開始するために使用されるサービス アカウントによって決定されます。 次の目的でサービス アカウントを使用します。

- サービスを識別し、認証する。
- サービスを正常に開始する。
- コードまたはアプリケーションへのアクセスや実行を行う。
- プロセスを開始する。

### オンプレミス サービス アカウントの種類

ユース ケースに応じて、管理されたサービス アカウント (MSA)、コンピューター アカウント、またはユーザー アカウントを使用してサービスを実行できます。 まず、サービスをテストして、管理されたサービス アカウントを使用できるかどうかを確認する必要があります。 サービスで MSA を使用できる場合は、1 つを使用する必要があります。

#### グループ管理サービス アカウント

オンプレミス環境で実行されるサービスの場合は、可能な限り[グループの管理されたサービス アカウント (gMSA)](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-group-managed) を使用します。 gMSA では、サーバー ファームで、またはネットワーク ロード バランサーの背後で実行されるサービスに対して、単一の ID ソリューションが提供されます。 gMSA は、1 台のサーバーで実行されるサービスにも使用できます。 gMSA の要件については、「[グループの管理されたサービス アカウントの概要](https://learn.microsoft.com/ja-jp/windows-server/security/group-managed-service-accounts/getting-started-with-group-managed-service-accounts)」を参照してください。

#### スタンドアロンの管理されたサービス アカウント

gMSA を使用できない場合は、[スタンドアロンの管理されたサービス アカウント (sMSA)](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-standalone-managed) を使用します。 sMSA を使用するには、Windows Server 2008 R2 以降が必要です。 gMSA とは異なり、sMSA は 1 台のサーバーでのみ実行されます。 そのサーバー上の複数のサービスに使用できます。

#### コンピューター アカウント

MSA を使用できない場合は、[コンピューター アカウント](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-computer)の使用を検討してください。 LocalSystem アカウントは、ローカル コンピューターに対する広範な権限を持ち、ネットワーク上のコンピューター ID として機能する定義済みのローカル アカウントです。

LocalSystem アカウントとして実行されるサービスでは、&lt;domain\_name&gt;\&lt;computer\_name&gt; の形式でコンピューター アカウントの資格情報を使用してネットワーク リソースにアクセスします。 その定義済みの名前は NT AUTHORITY\SYSTEM です。 それを使用して、サービスを開始し、そのサービスのセキュリティ コンテキストを提供することができます。

注意

コンピューター アカウントを使う場合、そのアカウントを使用しているコンピューター上のサービスを特定することはできません。 そのため、変更が行われているサービスを監査することはできません。

#### ユーザー アカウント

MSA を使用できない場合は、[ユーザー アカウント](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-user-on-premises)の使用を検討してください。 ユーザー アカウントは、''*ドメイン*'' ユーザー アカウントまたは ''*ローカル*'' ユーザー アカウントにすることができます。

ドメイン ユーザー アカウントを使用すると、Windows と Microsoft Active Directory Domain Services のサービス セキュリティ機能をサービスで最大限に活用できます。 サービスでは、そのアカウントにローカルおよびネットワークのアクセス許可が付与されます。 また、そのアカウントがメンバーとなっているグループのアクセス許可も付与されます。 ドメイン サービス アカウントは、Kerberos 相互認証をサポートします。

ローカル ユーザー アカウント (名前の形式: *.\UserName*) は、ホスト コンピューターのセキュリティ アカウント マネージャー データベースにのみ存在します。 Active Directory Domain Services にはユーザー オブジェクトはありません。 ローカル アカウントをドメインで認証することはできません。 そのため、ローカル ユーザー アカウントのセキュリティ コンテキストで実行されるサービスには、ネットワーク リソースへのアクセス権はありません (匿名ユーザーの場合を除く)。 ローカル ユーザー コンテキストで実行されるサービスでは、サービスがそのクライアントによって認証される Kerberos 相互認証をサポートできません。 これらの理由から、ローカル ユーザー アカウントは通常、ディレクトリ対応サービスには適していません。

重要

特権グループのメンバーシップによって、セキュリティ上のリスクとなるおそれがあるアクセス許可が付与されるため、サービス アカウントを特権グループのメンバーにすることはできません。 監査とセキュリティのために、各サービスに独自のサービス アカウントが必要です。

### 適切な種類のサービス アカウントの選択

| 条件 | gMSAの | sMSAの | コンピューター アカウント | ユーザー アカウント |
| --- | --- | --- | --- | --- |
| 1 台のサーバー上でアプリを実行 | はい | はい。 可能な場合は gMSA を使用します。 | はい。 可能な場合は MSA を使用します。 | はい。 可能な場合は MSA を使用します。 |
| 複数のサーバーでアプリケーションを実行 | はい | いいえ | いいえ。 アカウントはサーバーに関連付けられています。 | はい。 可能な場合は MSA を使用します。 |
| ロード バランサーの背後でアプを実行 | はい | いいえ | いいえ | はい。 gMSA を使用できない場合にのみ使用します。 |
| Windows Server 2008 R2 でアプリケーションを実行 | いいえ | はい | はい。 可能な場合は MSA を使用します。 | はい。 可能な場合は MSA を使用します。 |
| Windows Server 2012 上でアプリを実行 | はい | はい。 可能な場合は gMSA を使用します。 | はい。 可能な場合は MSA を使用します。 | はい。 可能な場合は MSA を使用します。 |
| サービス アカウントを 1 台のサーバーに制限する必要がある | いいえ | はい | はい。 可能な場合は sMSA を使用します。 | いいえ |

#### サーバー ログと PowerShell を使用した調査

サーバー ログを使用して、アプリケーションが実行されているサーバーとサーバーの台数を確認できます。

ネットワーク上のすべてのサーバーについて、Windows Server のバージョンの一覧を取得するために、次の PowerShell コマンドを実行できます。

```PowerShell

Get-ADComputer -Filter 'operatingsystem -like "*server*" -and enabled -eq "true"' `

-Properties Name,Operatingsystem,OperatingSystemVersion,IPv4Address |

sort-Object -Property Operatingsystem |

Select-Object -Property Name,Operatingsystem,OperatingSystemVersion,IPv4Address |

Out-GridView

```

### オンプレミス サービス アカウントの検索

サービス アカウントとして使用するすべてのアカウントに "svc-" などのプレフィックスを追加することをお勧めします。 この名前付け規則を使用すると、アカウントの検索と管理がより簡単になります。 サービス アカウントとサービス アカウントの所有者について、説明属性を使用することも検討してください。 説明は、チーム エイリアスまたはセキュリティ チーム所有者にすることができます。

オンプレミス サービス アカウントを見つけることは、セキュリティを確保するために重要です。 この作業は、MSA 以外のアカウントでは困難な場合があります。 オンプレミスの重要なリソースにアクセスできるすべてのアカウントを確認し、サービス アカウントとして機能する可能性のあるコンピューターまたはユーザー アカウントを判断することをお勧めします。

サービス アカウントを見つける方法については、「次のステップ」セクションのそのアカウントの種類に関する記事を参照してください。

### サービス アカウントの文書化

オンプレミス環境でサービス アカウントを見つけた後、次の情報を文書化します。

- **所有者**: アカウントを管理する責任者。
- **目的**: アカウントが表すアプリケーション、またはその他の目的。
- **アクセス許可スコープ**: 所有している、または所有する必要があるアクセス許可、およびそのメンバーであるすべてのグループ。
- **リスク プロファイル**: このアカウントが侵害された場合のビジネスのリスク。 リスクが高い場合は、MSA を使用します。
- **予想される有効期間と定期的な構成証明**: このアカウントが有効であると予想される期間、および所有者が継続的なニーズを確認し、構成証明する頻度。
- **パスワードのセキュリティ**: ユーザーおよびローカル コンピューター アカウントの場合、パスワードがどこに格納されるか。 パスワードの安全性を確保し、アクセス権を持つユーザーを文書化します。 [Windows LAPS](https://learn.microsoft.com/ja-jp/windows-server/identity/laps/laps-scenarios-azure-active-directory) を使用して、ローカル コンピューター アカウントでのアカウントをセキュリティで保護することを検討してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/service-accounts-principal"} -->
## Microsoft Entra ID でのサービス プリンシパルのセキュリティ保護 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-principal
- Service: entra / architecture
- Article date: 2023-02-08
- Summary: サービス プリンシパルを検索し、評価して、セキュリティで保護します。

Microsoft Entra サービス プリンシパルは、テナントまたはディレクトリ内のアプリケーション オブジェクトをローカルに表現したものです。 これは、アプリケーション インスタンスの ID です。 サービス プリンシパルは、アプリケーションへのアクセス権と、アプリケーションがアクセスするリソースを定義します。 サービス プリンシパルは、アプリケーションが使用される各テナント内に作成されて、グローバルに一意なアプリケーション オブジェクトを参照します。 テナントによって、サービス プリンシパルのサインインとリソースへのアクセスがセキュリティで保護されます。

詳細情報: [Microsoft Entra ID のアプリケーション オブジェクトとサービス プリンシパル オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)

### テナントとサービス プリンシパルの関係

シングルテナント アプリケーションには、そのホーム テナント内にサービス プリンシパルが 1 つあります。 マルチテナント Web アプリケーションまたは API では、各テナント内にサービス プリンシパルが 1 つ必要です。 サービス プリンシパルは、そのテナントのユーザーがアプリケーションまたは API を使用することに同意すると作成されます。 この同意によって、マルチテナント アプリケーションとそれに関連付けられたサービス プリンシパルの間に、一対多の関係が作成されます。

マルチテナント アプリケーションは、1 つのテナントに所属し、他のテナント内にインスタンスを持ちます。 サービスとしてのソフトウェア (SaaS) アプリケーションのほとんどが、マルチテナントを許容します。 サービス プリンシパルを使用して、シングルまたはマルチテナントの両方のシナリオで、アプリケーションとそのユーザーに必要なセキュリティ態勢を確保します。

### ApplicationID と ObjectID

アプリケーション インスタンスには、ApplicationID (つまり、ClientID) と ObjectID という 2 つのプロパティがあります。

注意

**アプリケーション**と**サービス プリンシパル**という用語は、認証タスクでアプリケーションについて言及するとき、同じ意味で使用されます。 ただし、Microsoft Entra ID には、2 つの表現のアプリケーションがあります。

ApplicationID は、グローバルなアプリケーションを表し、テナント全体のアプリケーション インスタンスで同一です。 ObjectID は、アプリケーション オブジェクトの一意の値です。 ユーザー、グループ、その他のリソースと同様に、ObjectID は、Microsoft Entra ID 内のアプリケーション インスタンスを識別するのに役立ちます。

詳細については、「[Microsoft Entra ID のアプリケーションとサービス プリンシパルのリレーションシップ](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)」をご覧ください

#### アプリケーションとそのサービス プリンシパル オブジェクトを作成する

以下を使用して、テナント内にアプリケーションとそのサービス プリンシパル オブジェクト (ObjectID) を作成できます。

- Azure PowerShell
- Microsoft Graph PowerShell
- Azure コマンドライン インターフェイス (Azure CLI)
- Microsoft Graph API
- Azure ポータル
- その他のツール

[Image: [新しいアプリ] ページの [アプリケーション (クライアント) ID] と [オブジェクト ID] のスクリーンショット。]

### サービス プリンシパルの認証

サービス プリンシパルを使用する場合、クライアント証明書とクライアント シークレットという 2 つの認証のメカニズムがあります。

[Image: [新しいアプリ]、[証明書とシークレット] の下の [証明書] と [クライアント シークレット] のスクリーンショット。]

証明書の方が安全性が高いため、可能な場合は証明書を使用することをお勧めします。 クライアント シークレットとは異なり、クライアント証明書が誤ってコードに埋め込まれることはありません。 可能な場合は、Azure Key Vault を証明書とシークレットの管理に使用して、以下の資産を、ハードウェア セキュリティ モジュールによって保護されているキーで暗号化します。

- 認証キー
- ストレージ アカウント キー
- データ暗号化キー
- .pfx ファイル
- パスワード

Azure Key Vault の詳細と、それを証明書とシークレットの管理に使用する方法については、次を参照してください。

- [Azure Key Vault について](https://learn.microsoft.com/ja-jp/azure/key-vault/general/overview)
- [Key Vault アクセス ポリシーを割り当てる](https://learn.microsoft.com/ja-jp/azure/key-vault/general/assign-access-policy)

#### 課題と軽減策

サービス プリンシパルを使用する場合は、次の表で課題と軽減策を確認してください。

| 課題 | 対応策 |
| --- | --- |
| 特権ロールに割り当てられたサービス プリンシパルのアクセス レビュー | この機能はプレビュー状態です |
| サービス プリンシパルのアクセス レビュー | Azure portal を使用したリソース アクセス制御リストの手動チェック |
| 過剰なアクセス許可が付与されたサービス プリンシパル | オートメーション サービス アカウントまたはサービス プリンシパルを作成するときに、タスクのアクセス許可を付与します。 サービス プリンシパルを評価して特権を減らします。 |
| サービス プリンシパルの資格情報または認証方法に対する変更の特定 | - 「[機密性の高い操作のレポート ブック](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/workbook-sensitive-operations-report)」を参照してください - Tech Community のブログ記事である [Solorigate リスクの評価に役立つ Microsoft Entra ブック](https://techcommunity.microsoft.com/t5/azure-active-directory-identity/azure-ad-workbook-to-help-you-assess-solorigate-risk/ba-p/2010718)に関するページを参照してください |

### サービス プリンシパルを使用してアカウントを見つける

アカウントを見つけるには、Azure CLI または PowerShell を使用して次のコマンドを実行します。

- Azure CLI - `az ad sp list`
- PowerShell - `Get-MgServicePrincipal -All:$true`

詳細については、「[MgServicePrincipal を取得する](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal)」をご覧ください。

### サービス プリンシパルのセキュリティを評価する

セキュリティを評価するには、特権と資格情報の保存について評価します。 次の表を使用して、課題を軽減してください。

| 課題 | 対応策 |
| --- | --- |
| マルチテナント アプリに同意したユーザーを検出し、マルチテナント アプリに対する不正な同意の付与を検出する | - 次の PowerShell を実行して、マルチテナント アプリを見つけます `Get-MgServicePrincipal -All:$true | ? {$_.Tags -eq "WindowsAzureActiveDirectoryIntegratedApp"}` - ユーザーの同意を無効にします - 選択されたアクセス許可について、確認済みの発行元からのユーザーの同意を許可します (推奨) - それらをユーザー コンテキストで構成します - それらのトークンを使用してサービス プリンシパルをトリガーします |
| サービス プリンシパルを使用した、スクリプト内でのハードコーディングされた共有シークレットの使用 | 証明書を使用します |
| 誰が証明書またはシークレットを使用しているかの追跡 | Microsoft Entra サインイン ログを使用して、サービス プリンシパルのサインインを監視します |
| 条件付きアクセスではサービス プリンシパルのサインインを管理できない | Microsoft Entra サインイン ログを使用して、サインインを監視します |
| Azure ロールベースのアクセス制御 (Azure RBAC) の既定のロールが共同作成者である | ニーズを評価し、可能な限り最小限のアクセス許可を適用します |

詳細情報: [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)

### ユーザー アカウントからサービス プリンシパルに移行する

サービス プリンシパルとして Azure ユーザー アカウントを使用している場合は、マネージド ID またはサービス プリンシパルに移行できるかどうかを評価します。 マネージド ID を使用できない場合は、必要なタスクを実行するのに十分なアクセス許可とスコープをサービス プリンシパルに付与します。 サービス プリンシパルは、アプリケーションを登録するか、PowerShell を使用して作成できます。

Microsoft Graph を使用するときは、API のドキュメントを確認してください。 アプリケーションのアクセス許可の種類がサポートされていることを確認します。 「[servicePrincipal を作成する](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-post-serviceprincipals?view=graph-rest-1.0&tabs=http&preserve-view=true)」を参照してください

詳細情報:

- [App Service と Azure Functions でマネージド ID を使用する方法](https://learn.microsoft.com/ja-jp/azure/app-service/overview-managed-identity?tabs=dotnet)
- [リソースにアクセスできる Microsoft Entra アプリケーションとサービス プリンシパルを作成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-create-service-principal-portal)
- [Azure PowerShell を使用して資格情報でのサービス プリンシパルを作成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-authenticate-service-principal-powershell)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/service-accounts-standalone-managed"} -->
## スタンドアロン管理サービス アカウントをセキュリティで保護する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-standalone-managed
- Service: entra / architecture
- Article date: 2023-02-08
- Summary: スタンドアロン管理サービス アカウント (sMSA) を使用するタイミング、評価方法、セキュリティで保護する方法について説明します

スタンドアロン管理サービス アカウント (sMSA) は、サーバーで実行されているサービスをセキュリティで保護するための、マネージド ドメイン アカウントです。 複数のサーバーにわたって再利用することはできません。 sMSA には、パスワードの自動管理、簡略化されたサービス プリンシパル名 (SPN) の管理、および管理者に管理を委任する機能があります。

Active Directory (AD) では、sMSA は、サービスを実行するサーバーに関連付けられています。 アカウントは、Microsoft 管理コンソールの [Active Directory ユーザーとコンピューター] スナップインにあります。

注

管理サービス アカウントは、Windows Server 2008 R2 Active Directory スキーマで導入されたものであり、Windows Server 2008 R2 以降のバージョンが必要です。

### sMSA の利点

sMSA は、サービス アカウントとして使用されるユーザー アカウントよりも高度なセキュリティを提供します。 管理オーバーヘッドの削減に役立ちます。

- 強力なパスワードの設定 - sMSA では、240 バイトのランダムに生成された複雑なパスワードが使用されます
    - この複雑さにより、ブルート フォースや辞書攻撃による侵害の可能性が最小限に抑えられます
- パスワードの定期的な循環 - Windows によって、sMSA パスワードが 30 日ごとに変更されます。
    - サービス管理者とドメイン管理者が、パスワードの変更をスケジュールしたり、関連するダウンタイムを管理したりする必要はありません
- SPN 管理の簡素化 - ドメインの機能レベル (DFL) が Windows Server 2008 R2 の場合、SPN は更新されます。 SPN は、次の場合に更新されます。
    - ホスト コンピューター アカウントを変更する
    - ホスト コンピューターのドメイン ネーム サーバー (DNS) 名を変更する
    - PowerShell を使用して、他の sam-accountname パラメーターまたは dns-hostname パラメーターを追加または削除する
    - 「[Set-ADServiceAccount](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/set-adserviceaccount)」を参照してください

### sMSA の使用

sMSA を使用すると、管理タスクとセキュリティ タスクを簡単に実行できます。 sMSA は、サービスがサーバーにデプロイされていて、グループ管理サービス アカウント (gMSA) を使用できない場合に役立ちます。

注

複数のサービスに対して sMSA を使用できますが、監査のために、各サービスには監査用の ID を設定することをお勧めします。

アプリケーションが MSA を使用しているかどうかをソフトウェア作成者が確認できない場合は、アプリケーションをテストします。 テスト環境を作成し、必要なリソースにアクセスできることを確認します。

詳細情報: [管理されたサービス アカウント: 理解、実装、ベスト プラクティス、およびトラブルシューティング](https://learn.microsoft.com/ja-jp/archive/blogs/askds/managed-service-accounts-understanding-implementing-best-practices-and-troubleshooting)

#### sMSA セキュリティ体制を評価する

セキュリティ体制の一部として、アクセスの sMSA スコープを検討してください。 潜在的なセキュリティの問題を軽減するには、次の表を参照してください。

| セキュリティ上の問題 | 軽減策 |
| --- | --- |
| sMSA は特権グループのメンバーです。 | - 昇格された特権グループ (Domain Admins など) から sMSA を削除します - 最小特権モデルを使用します  - サービスを実行するための sMSA の権利とアクセス許可を付与します - アクセス許可がわからない場合は、サービス作成者に問い合わせてください |
| sMSA には、機密リソースへの読み取りおよび書き込みアクセス権があります | - 機密リソースへのアクセスを監査します - 監査ログを Azure Log Analytics や Microsoft Sentinel などのセキュリティ情報およびイベント管理 (SIEM) プログラムにアーカイブします - 望ましくないアクセスが検出された場合は、リソースのアクセス許可を修正します |
| 既定では、sMSA パスワード ロールオーバーの頻度は 30 日です | グループ ポリシーを使用して、企業のセキュリティ要件に応じて期限を調整します。 パスワードの有効期限を設定するには、次の手順に進みます。コンピューターの構成&gt;ポリシー&gt;Windows の設定&gt;セキュリティ設定&gt;セキュリティ オプション。 ドメイン メンバーの場合は、 **[マシン アカウントのパスワード変更の最大有効期間]** を使用します。 |

#### sMSA の課題

次の表を使用して、課題を軽減策に関連付けます。

| 課題 | 緩和 |
| --- | --- |
| sMSA は単一サーバー上にあります | 複数のサーバーにわたってアカウントを使用する場合は、gMSA を使用します |
| sMSA は複数のドメイン間では使用できません | 複数のドメインにわたってアカウントを使用する場合は、gMSA を使用します |
| すべてのアプリケーションで sMSA がサポートされるわけではありません | 可能な場合は gMSA を使用します。 可能でない場合は、作成者が勧める標準のユーザー アカウントまたはコンピューター アカウントを使用します |

### sMSA の検索

ドメイン コントローラーで、DSA.msc を実行してから、管理サービス アカウントのコンテナーを展開して、すべての sMSA を表示します。

Active Directory ドメイン内のすべての sMSA と gMSA を返すには、次の PowerShell コマンドを実行します。

`Get-ADServiceAccount -Filter *`

Active Directory ドメイン内の sMSA を返すには、次のコマンドを実行します。

`Get-ADServiceAccount -Filter * | where { $_.objectClass -eq "msDS-ManagedServiceAccount" }`

### sMSA の管理

sMSA を管理するには、次の AD PowerShell コマンドレットを使用できます。

`Get-ADServiceAccount``Install-ADServiceAccount``New-ADServiceAccount``Remove-ADServiceAccount``Set-ADServiceAccount``Test-ADServiceAccount``Uninstall-ADServiceAccount`

### sMSA への移行

アプリケーション サービスで sMSA はサポートされているが gMSA がサポートされておらず、現在、セキュリティ コンテキストにユーザー アカウントまたはコンピューター アカウントを使用している場合は、「[管理されたサービス アカウント: 理解、実装、ベスト プラクティス、およびトラブルシューティング](https://learn.microsoft.com/ja-jp/archive/blogs/askds/managed-service-accounts-understanding-implementing-best-practices-and-troubleshooting)」を参照してください。

可能な場合は、Azure にリソースを移動し、Azure のマネージド ID またはサービス プリンシパルを使用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/service-accounts-user-on-premises"} -->
## Active Directory でユーザーベースのサービス アカウントをセキュリティで保護する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-user-on-premises
- Service: entra / architecture
- Article date: 2023-02-09
- Summary: ユーザー ベースのサービス アカウントのセキュリティの問題を特定、評価、軽減する方法について説明します

オンプレミス ユーザー アカウントは、Windows で実行されているサービスをセキュリティで保護するための従来のアプローチです。 現在、グループ管理サービス アカウント (gMSA) やスタンドアロンの管理サービス アカウント (sMSA) がサービスでサポートされていない場合は、これらのアカウントを使用します。 使用するアカウントの種類については、[オンプレミス サービス アカウントの保護](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-on-premises)に関するページを参照してください。

マネージド ID やサービス プリンシパルなどの Azure サービス アカウントへのサービスの移行を調査できます。

詳細情報：

- [Azure リソースのマネージド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)
- [Microsoft Entra ID でのサービス プリンシパルのセキュリティ保護](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-principal)

オンプレミスのユーザー アカウントを作成して、アカウントがローカル リソースとネットワーク リソースにアクセスするために使用するサービスとアクセス許可のセキュリティを用意することができます。 他の Active Directory (AD) ユーザー アカウントと同様に、オンプレミスのユーザー アカウントには手動のパスワード管理が必要です。 アカウントのセキュリティを維持するために、サービスとドメインの管理者は、強力なパスワード管理プロセスを監視する必要があります。

ユーザー アカウントをサービス アカウントとして作成する場合は、単一のサービスに対して使用します。 名前付け規則を使用して、サービス アカウントと、それに関連するサービスを明確にします。

### メリットと課題

オンプレミスのユーザー アカウントは、汎用性が高い種類のアカウントです。 サービス アカウントとして使用するユーザー アカウントは、ユーザー アカウントを管理するポリシーによって制御されます。 MSA を使用できない場合に使用してください。 コンピューター アカウントがより適切なオプションであるかどうかも評価します。

次の表は、オンプレミスのユーザー アカウントの課題をまとめたものです。

| 課題 | 緩和策 |
| --- | --- |
| パスワード管理は手動であり、セキュリティとサービスのダウンタイムが低下します | - 定期的なパスワードの複雑さを確保し、強力なパスワードを維持するプロセスによって変更が管理されるようにします - パスワードの変更をサービス パスワードと調整することで、サービスのダウンタイムを削減します |
| サービス アカウントであるオンプレミス ユーザー アカウントを識別するのが困難な場合があります | - 環境内にデプロイされたサービス アカウントを文書化します - アカウント名とアクセスできるリソースを追跡する - サービス アカウントとして使用されるユーザー アカウントにプレフィックス svc を追加することを検討します |

### サービス アカウントとして使用されるユーザー アカウントの検索

オンプレミス ユーザー アカウントは、他の AD ユーザー アカウントと似ています。 サービス アカウントであると識別できるユーザー アカウントの属性がないため、そのようなアカウントを見つけるのは困難な場合があります。 サービス アカウントとして使用するユーザー アカウントの名前付け規則を作成することをお勧めします。 たとえば、サービス名 svc-HRDataConnector にプレフィックス svc を追加します。

次の条件のいくつかを使用して、サービス アカウントを見つけます。 ただし、この方法では、次のようなアカウントを見つけられない可能性があります。

- 委任のために信頼されている
- サービス プリンシパル名が付いている
- パスワードが期限切れにならないように設定されている

サービス用に使用されるオンプレミス ユーザー アカウントを見つけるには、次の PowerShell コマンドを実行します。

委任に対して信頼されているアカウントを見つけるには:

```PowerShell

Get-ADObject -Filter {(msDS-AllowedToDelegateTo -like '*') -or (UserAccountControl -band 0x0080000) -or (UserAccountControl -band 0x1000000)} -prop samAccountName,msDS-AllowedToDelegateTo,servicePrincipalName,userAccountControl | select DistinguishedName,ObjectClass,samAccountName,servicePrincipalName, @{name='DelegationStatus';expression={if($_.UserAccountControl -band 0x80000){'AllServices'}else{'SpecificServices'}}}, @{name='AllowedProtocols';expression={if($_.UserAccountControl -band 0x1000000){'Any'}else{'Kerberos'}}}, @{name='DestinationServices';expression={$_.'msDS-AllowedToDelegateTo'}}

```

サービス プリンシパル名を持つアカウントを見つけるには:

```PowerShell

Get-ADUser -Filter * -Properties servicePrincipalName | where {$_.servicePrincipalName -ne $null}

```

パスワードが無期限に設定されているアカウントを見つけるには:

```PowerShell

Get-ADUser -Filter * -Properties PasswordNeverExpires | where {$_.PasswordNeverExpires -eq $true}

```

機密性の高いリソースへのアクセスを監査し、監査ログをセキュリティ情報イベント管理 (SIEM) システムにアーカイブすることができます。 Azure Log Analytics や Microsoft Sentinel を使用して、サービス アカウントを検索し、分析できます。

### オンプレミスのユーザー アカウントのセキュリティを評価する

サービス アカウントとして使用されるオンプレミス ユーザー アカウントのセキュリティを評価するには、次の条件を使用します。

- パスワード管理ポリシー
- 特権グループのメンバーシップを持つアカウント
- 重要なリソースの読み取り/書き込みアクセス許可

#### 潜在的なセキュリティの問題の軽減

オンプレミスのユーザー アカウントに関する潜在的なセキュリティの問題とその軽減策については、次の表を参照してください。

| セキュリティ上の問題 | 緩和策 |
| --- | --- |
| パスワード管理 | - パスワードの複雑さとパスワードの変更が、定期的な更新と強力なパスワード要件によって管理されていることを確認します - サービスのダウンタイムを最小化するために、パスワードの更新とパスワードの変更を調整する |
| アカウントが特権グループのメンバーである | - グループ メンバーシップを確認します - 特権グループからアカウントを削除します - サービスを実行するためのアカウント権限とアクセス許可を付与します (サービス ベンダーに問い合わせてください) - たとえば、ローカルでのサインインまたはインタラクティブなサインインを拒否します |
| このアカウントには、機密性の高いリソースに対する読み取りおよび書き込みのアクセス許可がある | - 機密性の高いリソースへのアクセスを監査します - 監査ログを SIEM (Azure Log Analytics または Microsoft Sentinel) にアーカイブします - 望ましくないアクセス レベルが検出された場合はリソースのアクセス許可を修正します |

### セキュリティで保護されたアカウントの種類

Microsoft では、オンプレミス ユーザー アカウントをサービス アカウントとして使用することはお勧めしていません。 このアカウントの種類を使用しているサービスについては、gMSA または sMSA を使用するように構成できるかどうかを評価してください。 さらに、サービスを Azure に移行して、より安全なアカウントの種類を使用できるかどうかを評価します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/sync-directory"} -->
## Microsoft Entra ID を使用したディレクトリ同期 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/sync-directory
- Service: entra / architecture
- Article date: 2023-03-01
- Summary: Microsoft Entra ID を使用したディレクトリ同期の実現に関するアーキテクチャ ガイダンス。

多くの組織には、オンプレミスとクラウドの両方のコンポーネントを含むハイブリッド インフラストラクチャがあります。 ローカル ディレクトリとクラウド ディレクトリ間でユーザーの ID を同期すると、ユーザーは 1 つの資格情報セットを使用してリソースにアクセスできます。

同期は次のプロセスです。

- 特定の条件に基づいてオブジェクトを作成する
- オブジェクトの更新を維持し、
- 条件が満たされなくなったときにオブジェクトを削除する。

オンプレミスのプロビジョニングには、オンプレミスのソース (Active Directory など) から Microsoft Entra ID へのプロビジョニングが含まれます。

### ディレクトリ同期を使用するタイミング

次の図に示すように、オンプレミスの Active Directory 環境から Microsoft Entra ID に ID データを同期する必要がある場合は、ディレクトリ同期を使用します。

[Image: アーキテクチャの図]

### システム コンポーネント

- **Microsoft Entra ID: Microsoft Entra** Connect を使用して、組織のオンプレミス ディレクトリから ID 情報を同期します。
- **Microsoft Entra Connect**: オンプレミスの ID インフラストラクチャを Microsoft Entra ID に接続するためのツール。 ウィザードとガイド付きエクスペリエンスは、接続に必要な前提条件とコンポーネント (Active Directory から Microsoft Entra ID への同期とサインオンを含む) の展開と構成に役立ちます。
- **Active Directory**: Active Directory は、ほとんどの Windows Server オペレーティング システムに含まれるディレクトリ サービスです。 Active Directory Domain Services (AD DS) を実行するサーバーは、ドメイン コントローラーと呼ばれます。 ドメイン内のすべてのユーザーとコンピューターを認証および承認します。

Microsoft は、ユーザー、グループ、および連絡先を Microsoft Entra ID に同期するためのハイブリッド ID 目標を満たし、達成するように Microsoft [Entra Connect クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync) を設計しました。 Microsoft Entra Connect クラウド同期では、Microsoft Entra Connect アプリケーションの代わりに Microsoft Entra クラウド プロビジョニング エージェントが使用されます。

### Microsoft Entra ID を使用してディレクトリ同期を実装する

Microsoft Entra ID とのディレクトリ同期の詳細については、次のリソースを参照してください。

- [Microsoft Entra ID を使用した ID プロビジョニングとは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/what-is-provisioning)プロビジョニングとは、特定の条件に基づいてオブジェクトを作成し、オブジェクトを -date up-to保持し、条件が満たされなくなった場合にオブジェクトを削除するプロセスです。 オンプレミスのプロビジョニングには、オンプレミスのソース (Active Directory など) から Microsoft Entra ID へのプロビジョニングが含まれます。
- [ハイブリッド ID: ディレクトリ統合ツールの比較](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/) では、Microsoft Entra Connect Sync と Microsoft Entra Connect クラウド プロビジョニングの違いについて説明します。
- [Microsoft Entra Connect と Microsoft Entra Connect Health のインストール ロードマップ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-roadmap) では、詳細なインストールと構成手順が提供されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/sync-ldap"} -->
## Microsoft Entra ID を使用した LDAP 同期 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/sync-ldap
- Service: entra / architecture
- Article date: 2023-03-01
- Summary: Microsoft Entra ID を使用した LDAP 同期の実現に関するアーキテクチャ ガイダンス。

ライトウェイト ディレクトリ アクセス プロトコル (LDAP) は、TCP/IP スタックで実行されるディレクトリ サービス プロトコルです。 インターネット ディレクトリへの接続、検索、変更に使用できるメカニズムを提供します。 クライアント/サーバー モデルに基づいて、LDAP ディレクトリ サービスは既存のディレクトリへのアクセスを有効にします。

多くの企業は、重要なビジネス アプリのユーザーとグループを格納するためにオンプレミスの LDAP サーバーに依存しています。

Microsoft Entra ID は、LDAP 同期を Microsoft Entra Connect に置き換えることができます。 Microsoft Entra Connect 同期サービスは、オンプレミス環境と Microsoft Entra ID の間での ID データの同期に関連するすべての操作を実行します。

### LDAP 同期を使用するタイミング

次の図に示すように、オンプレミスの LDAP v3 ディレクトリと Microsoft Entra ID の間で ID データを同期する必要がある場合は、LDAP 同期を使用します。

[Image: アーキテクチャの図]

### システム コンポーネント

- **Microsoft Entra ID**: Microsoft Entra ID は、Microsoft Entra Connect を介して組織のオンプレミス LDAP ディレクトリから ID 情報 (ユーザー、グループ) を同期します。
- **Microsoft Entra Connect**: オンプレミスの ID インフラストラクチャを Microsoft Entra ID に接続するためのツールです。 ウィザードとガイド付きエクスペリエンスは、接続に必要な前提条件とコンポーネントの展開と構成に役立ちます。
- **カスタム コネクタ**: 汎用 LDAP コネクタを使用すると、Microsoft Entra Connect 同期サービスを LDAP v3 サーバーと統合できます。 Microsoft Entra Connect に配置されます。
- **Active Directory**: Active Directory は、ほとんどの Windows Server オペレーティング システムに含まれるディレクトリ サービスです。 ドメイン コントローラーと呼ばれる Active Directory サービスを実行するサーバーは、Windows ドメイン内のすべてのユーザーとコンピューターを認証および承認します。
- **LDAP v3 サーバー**: ディレクトリ サービス認証に使用される企業ユーザーとパスワードを格納する LDAP プロトコルに準拠したディレクトリ。

### Microsoft Entra ID を使用して LDAP 同期を実装する

Microsoft Entra ID を使用した LDAP 同期の詳細については、次のリソースを参照してください。

- [ハイブリッド ID: ディレクトリ統合ツールの比較](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/) では、Microsoft Entra Connect Sync と Microsoft Entra Connect クラウド プロビジョニングの違いについて説明します。
- [Microsoft Entra Connect と Microsoft Entra Connect Health のインストール ロードマップ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-roadmap) では、詳細なインストールと構成手順が提供されます。
- [Generic LDAP コネクタ](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-connector-genericldap)を使用すると、同期サービスを LDAP v3 サーバーと統合できます。

    注

    LDAP コネクタをデプロイするには、高度な構成が必要です。 Microsoft では、このコネクタに限りのあるサポートを提供しています。 このコネクタを構成するには、Microsoft Identity Manager と特定の LDAP ディレクトリに関する知識が必要です。

    運用環境でこの構成を展開する場合は、Microsoft コンサルティング サービスなどのパートナーと協力して、ヘルプ、ガイダンス、サポートを受けます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/sync-scim"} -->
## Microsoft Entra ID を使用した SCIM 同期 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/sync-scim
- Service: entra / architecture
- Article date: 2023-01-10
- Summary: Microsoft Entra ID を使用した SCIM 同期の取得に関するアーキテクチャ ガイダンス。

クロスドメイン ID 管理システム (SCIM) とは、ID ドメインと IT システムの間で行うユーザー ID 情報の交換を自動化するためのオープンな標準プロトコルです。 SCIM を使用すれば、人材管理 (HCM) システムに追加された従業員のアカウントを、確実に Microsoft Entra ID または Windows Server Active Directory によって自動的に作成することができます。 ユーザーの属性およびプロファイルは 2 つのシステム間で同期されているので、ユーザーの状態または役割の変更に基づいてユーザーの更新および削除が行われます。

SCIM とは、2 つのエンドポイント (/Users エンドポイントおよび /Groups エンドポイント) の標準化された定義です。 オブジェクトの作成、更新、および削除を行う共通の REST Verb を使用します。 また、グループ名、ユーザー名、名、姓、電子メールなどの一般的な属性に対しては定義済みのスキーマも使用します。 SCIM 2.0 REST API を提供するアプリケーションであれば、独自のユーザー管理 API または製品を使用する煩わしさを軽減するか、なくすことができます。 たとえば、SCIM に準拠しているクライアントがあれば、JSON オブジェクトの HTTP POST を /Users エンドポイントに送信して新しいユーザー エントリを作成することができます。 同じ基本的なアクションに対して若干異なる API を必要とするのではなく、SCIM 標準に準拠しているアプリでは、既存のクライアント、ツール、およびコードをすぐに利用できます。

### たとえば、次のような場合です。

ユーザー情報を HCM システムから Microsoft Entra および Windows Server Active Directory に、さらに必要があればターゲット システムに自動的にプロビジョニングする必要があります。

[Image: アーキテクチャの図]

### システムのコンポーネント

- **HCM システム**: 従業員のライフサイクル全体にわたって人事プロセスをサポートおよび自動化する人材管理プロセスおよびプラクティスを実現するアプリケーションとテクノロジ。
- **Microsoft Entra プロビジョニング サービス**: 自動プロビジョニングに SCIM 2.0 プロトコルが使用されます。 このサービスが、アプリケーションの SCIM エンドポイントに接続されると、SCIM ユーザー オブジェクト スキーマおよび REST API を使用してユーザーとグループのプロビジョニングとプロビジョニング解除が自動化されます。
- **Microsoft Entra ID**: アイデンティティとその権利のライフサイクルを管理するために使用されるユーザーリポジトリです。
- **ターゲット システム**: SCIM エンドポイントを備えていて、ユーザーとグループの自動プロビジョニングを有効にするために Microsoft Entra プロビジョニングと連携するアプリケーションまたはシステムです。

### Microsoft Entra ID を使用して SCIM を実装する

- [Microsoft Entra ID でのプロビジョニングのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works)
- [Azure Portal でエンタープライズ アプリのユーザー アカウント プロビジョニングを管理する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/configure-automatic-user-provisioning-portal)
- [SCIM エンドポイントの構築と Microsoft Entra を使用したユーザー プロビジョニングの構成](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)
- [Microsoft Entra プロビジョニング サービスの SCIM 2.0 プロトコルへのコンプライアンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-scim-compatibility)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/tenant-estate-business-partner"} -->
## ビジネス パートナー アクセス向け Microsoft Entra テナント ガイダンス - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-business-partner
- Service: entra / architecture
- Article date: 2026-06-22
- Summary: ニーズを特定し、アーキテクチャ オプションを比較できるように、ビジネス パートナー アクセスのテナント アーキテクチャMicrosoft Entraについて説明します。

Microsoft Entra**テナント資産** (組織が運用するテナントのセット) を設計することは、セキュリティ、コンプライアンス、管理の複雑さ、ユーザー エクスペリエンスのバランスを取るということです。 単一の運用テナントはシンプルでユーザー エクスペリエンスに最適ですが、特定のビジネス要件と技術要件により、複数の運用テナントが必要になる場合があります。

この記事シリーズでは、Microsoftが実際のデプロイ全体で観察した、次の一般的なテナント アーキテクチャ パターンについて説明します。

- [Microsoft Entra テナント資産ガイダンスの概要](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide)
- [プライマリ運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-primary)
- [共同作業先の運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-collaborating)
- [非本番環境](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-nonproduction)
- [重要な運用システム用の分離されたテナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-critical-production)
- [ハイブリッド ID と分離](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-hybrid-identity)

この記事では、ビジネス パートナー アクセス用の個別のテナントについて説明します。 エクストラネット テナントまたはパートナー テナントとも呼ばれる個別のテナントは、内部アクセスと外部アクセスの間の明確に定義された境界を持つ分離されたコラボレーション スペースとして機能します。

このパターンは、7 つのアーキテクチャ評価領域について詳しく説明する [プライマリ運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-primary) ベースラインに基づいています。 この記事では、同じ評価領域のコンテキストで、ビジネス パートナー アクセスに関する増分的な考慮事項のみを説明します。 全シリーズについては、[Microsoft Entra テナント資産ガイダンスの概要](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide)をご覧ください。

### サンプル シナリオ

Contoso は、主にメイン テナントに内部従業員を保持しながら、外部ユーザー専用の別のテナントを作成します。 たとえば、Contoso は、ジョイント ベンチャー プロジェクトまたはすべてのサプライヤーの相互作用のためにテナントを設定できます。 Contoso ユーザーとパートナー ユーザーの両方を、その別々の境界内で共同作業するように招待します。

次のシナリオ例の図は、ビジネス パートナー アクセス用の Contoso の個別のテナント アーキテクチャを示しています。

[Image: シナリオ図の例は、ビジネス パートナー アクセス用の個別のテナント アーキテクチャを示しています。]

### Administration

ビジネス パートナー アクセス用の個別のテナント アーキテクチャを使用すると、主要な従業員テナントとは別の管理者のセットを使用できます。 このソリューションは、コラボレーションのユース ケース (ジョイント ベンチャーやサプライ チェーンなど) と一致する可能性があります。 リソースに対応できるテナント全体の設定を柔軟に構成でき、主要な従業員テナントとは異なる構成の要件を持つアプリケーションを信頼できます。 ビジネス パートナー アクセス用の個別のテナント アーキテクチャを使用すると、メインワークフォース テナントからMicrosoft サービスのインスタンス (Exchange、SharePoint Online、Teams、Intune など) を分離できます。

### 変更管理

ビジネス パートナー アクセス用のテナント アーキテクチャを分離すると、コラボレーション ワークロードがメインワークフォース テナントの変更から切り離されます。 専用テナントの管理者は、変更を個別に計画できます。 前の変更制御セクションで説明したように、テナント内の **変更制御** に関して同じ考慮事項を適用します。

### アカウントのライフサイクル

パートナー テナントでは、アカウント管理には、アクセス権を必要とする従業員と外部パートナー ユーザーが含まれます。 従業員の場合は、テナント間同期を使用して、パートナー テナントへのオンボードを自動化できます。 このアプローチは、統合されたマルチテナント アーキテクチャに似ていますが、外部ユーザーと共同作業する必要があるユーザーのサブセットを対象としています。 このシナリオの例では、Contoso は販売チームのみをパートナー コラボレーション テナントに同期して、手動招待なしでメンバーとして表示できます。 これらの同期された Contoso ユーザーは、パートナー テナントの外部 (B2B) メンバーである可能性があります。

外部ユーザーのオンボード方法には、次のオプションがあります。

- パートナーが独自のMicrosoft Entra IDを持っている場合は、[エンタイトルメント管理アクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users)を使用して、適切なアクセス権を持つ専用テナントにパートナー ユーザーをオンボードできます。 エンタイトルメント管理は、ワークフローの承認、有効期限、追跡を提供するため、大規模または継続的なパートナーのオンボードに適しています。
- パートナーが独自のMicrosoft Entra IDを持っていない場合、または個人 (コンサルタントなど) である場合は、[セルフサービス サインアップ](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-overview)を使用できます。 ワンタイム パスコードまたはソーシャル ID (有効な場合) を含む電子メールの招待を受け取ることができます。 いずれの場合も、パートナー テナント アカウントのライフサイクルを定義します。 たとえば、プロジェクトの後にアクセスが期限切れになったり、アクセス レビューの適用が必要になったり、時間制限付きのアクセス パッケージが含まれている場合があります。 パートナー テナント管理者が定期的にユーザー リストを監査し、不要になったらアカウント (特に外部アカウント) を削除または無効にすることを確認します。 アクセス レビューなどのMicrosoft Entra ID ガバナンス機能を構成して、定期的なアクセスの再認定のためにゲスト ユーザーを含めます。
- ビジネス パートナー アクセス用の個別のテナント アーキテクチャでは、すべての従業員による大規模なアドホック セルフサービス招待は最適化されません。 通常、例外的なケースでパートナーをパートナー テナントに招待できるのは、特定の内部ユーザーまたは管理者だけです。 アカウントのライフサイクルは、より構造化されています。 内部ユーザーを一括プロビジョニングすることも、手順で招待することもできます。 管理されたプロセスを通じて外部ユーザーをオンボードできます。

### 資格情報の管理

このシナリオ例では、ビジネス パートナー アクセスのシナリオ用の個別のテナント アーキテクチャが、Contoso の従業員がホーム テナントを持っていることを考慮しています。 パートナー テナントで、Contoso の従業員は Contoso の資格情報を使用して B2B にサインインします。

通常は、パートナー テナントのクロステナント アクセス設定を構成して、それらのユーザー アカウントの組織の MFA とデバイスの状態を信頼します。 そうすることで、内部ユーザーがエクストラネット テナントにアクセスし、企業のサインインが必要な制御を満たすと、認証プロンプトが表示されません。 パートナー ユーザーが自分のMicrosoft Entra テナント (独自のMicrosoft Entra テナントを持つサプライヤーなど) から来た場合は、テナントに MFA を登録するように要求できます。 次に、条件付きアクセスを使用して、パートナー テナントのすべてのゲスト ユーザーに適用します。

パートナー アクセス テナントは、外部ユーザーに対して複数の ID プロバイダーの種類 (他のMicrosoft Entra ID、SAML/WS-Federation、Microsoft アカウントなど) を受け入れることもできます。 Google または Facebook を許可できますが、多くの企業ではビジネス ID プロバイダーに制限されています。 パートナー ユーザーに既存の ID がない場合は、 [ワンタイム パスコード (OTP) 認証を](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode)許可できます。 サインインのたびにパスコードが記載された電子メールを受け取ります。 その場合、パートナー テナントは、パスワードを持たないユーザー アカウントをバックグラウンドで作成します (メール ワンタイム パスコード ユーザーとして)。 ユーザー名として電子メール アドレスを使用し、資格情報として電子メールで送信された OTP を使用します。 外部ユーザーには管理するアカウントがないため、この方法は簡単です。 MFA を要求することも、メールがセキュリティで保護されていることを確認することもできます。

### Collaboration

ビジネス パートナー アクセス用の個別のテナント アーキテクチャでの外部コラボレーションは、内部ユーザーに摩擦のないエクスペリエンスを提供するように最適化されません。 従業員ユーザーは共同作業を行うことができますが、その作業のためにパートナー アクセス テナントで意識的に運用する必要があります。 このアプローチでは、外部コラボレーション専用の個別の Teams チャネルとSharePoint サイトが必要です。

その場しのぎの共有や招待は好ましくありません。 代わりに、構造化されたアプローチを推奨できます。 たとえば、ファイルをサプライヤーと共有する必要がある場合は、内部OneDriveではなく、サプライヤー SharePoint サイト (エクストラネット SharePoint サイト) を使用することをユーザーに伝えます。 そうすることで、分離されたテナントでパートナーのコラボレーションが行われます。 コラボレーションは、このモデルでは意図的に行われます。 内部ユーザーは、パートナーをパートナー テナントに招待し、メイン テナントのアドホックベースではなく、そこで共同作業を行います。 この管理されたアプローチでは、パートナー テナントのガバナンス下にある Teams（共有チャネルまたは個別のチームを含む）と SharePoint サイトを使用できます。

このシナリオ例では、パートナー テナントにアクセスする Contoso の従業員と外部ユーザーが、Microsoft Entraやその他の Microsoft Services (Stream や Planner など) で [B2B の制限](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide#b2b-limitations-across-microsoft-services)に遭遇する可能性があります。

### ロールベースのリソースの割り当て

エクストラネット テナントには、従業員とパートナー ユーザーがアクセスする必要があるリソース (アプリケーション、SharePoint サイト、Teams など) が含まれています。 ロールとグループを使用してアクセスを管理し、順序と一貫性を維持します。 パートナー アクセス テナントで [エンタイトルメント管理アクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users) を使用します。

このシナリオの例では、Contoso のパートナー アクセス テナントに *、サプライヤー ポータル アクセス*という名前の承認済みアクセス パッケージを含めることができます。 このアクセス パッケージは、サプライヤー ポータルの SharePoint および Teams で必要なロールを持つ内部または外部のユーザー アクセス権を自動的に付与できます。

アクセス パッケージでは、セキュリティ グループにユーザーを追加して、アプリケーションへのアクセスを許可できます。 パッケージでは、時間制限と承認を適用できます。 内部的には、パートナーがそのようなパッケージを通過することを要求するルールを作成できます。

### リスク管理

#### ブラスト半径

ビジネス パートナー アクセス用の個別のテナント アーキテクチャにより、ビジネス パートナーが企業テナント内のリソースに (意図的または悪意を持って) 不正アクセスを行うリスクが軽減されます。 この軽減策は、他のテナントが提供する個別のセキュリティ境界が原因で可能です。 主要な企業テナントのユーザーが、ジョイント ベンチャーやサプライ チェーン アプリケーションに対する可視性や偶発的なアクセス権を持たないようにすることが重要な場合があります。

- クロステナント アクセス設定やドメイン許可リストなどの機能を使用して、許可された組織に外部コラボレーションのスコープを設定する許可リスト アプローチを実装します。 [Microsoft Entra B2B コラボレーションによる管理コラボレーションへの移行](https://learn.microsoft.com/ja-jp/entra/architecture/5-secure-access-b2b)に関する記事では、リソースへの外部アクセスをセキュリティで保護する方法について説明しています。
- 列挙や同様の偵察手法の悪意のある、または偶発的な試行を防ぐには、 [ゲスト アクセス](https://learn.microsoft.com/ja-jp/entra/identity/users/users-restrict-guest-permissions) を独自のディレクトリ オブジェクトのプロパティとメンバーシップに制限します。
- ビジネス パートナーは、オンボード後、広範なアクセス許可セットを持つ環境アプリケーションとリソースにアクセスできます。 過剰共有のリスクに対する意図しない露出を軽減するには、予防と検出のコントロールを実装します。 すべての環境リソースとアプリケーションに適切なアクセス許可を常に適用します。

#### 規制要件

ビジネス パートナー アクセス用の個別のテナント アーキテクチャは、企業テナントに適用可能な規制スコープを含めるのに役立ちます。

### その他の考慮事項

エクストラネット テナントを維持する場合、運用上のオーバーヘッドが要因となります。 個別の条件付きアクセス、個別のコンプライアンス構成、およびコンテンツ用の個別の DLP ポリシーのオーバーヘッドを考慮してください。 外部テナント アカウント プロセス (要求、承認、定期的なクリーンアップ) に対するユーザー管理のオーバーヘッドを考慮します。 これらのテナントはテナント間の B2B アクセスによって定義されるため、Microsoft Entra テナント ガバナンスの[関連テナント](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/related-tenants)を使用して、それらの B2B リレーションシップを検出して測定し、承認されていない、または "シャドウ IT" パートナー テナントを表示できます。

[ゲスト ユーザーのMicrosoft Entra ID ガバナンスライセンスを考慮します](https://learn.microsoft.com/ja-jp/entra/id-governance/microsoft-entra-id-governance-licensing-for-guest-users)。 [外部 ID の月間アクティブ ユーザー (MAU) 課金モデルでは、](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing)ゲストとの基本的なコラボレーションについて説明します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/tenant-estate-collaborating"} -->
## Microsoft Entra の連携する運用テナントに関するガイダンス - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-collaborating
- Service: entra / architecture
- Article date: 2026-06-22
- Summary: ニーズを特定し、アーキテクチャ オプションを比較できるように、運用テナントを共同作業するためのテナント アーキテクチャMicrosoft Entraについて説明します。

Microsoft Entra**テナント資産** (組織が運用するテナントのセット) を設計することは、セキュリティ、コンプライアンス、管理の複雑さ、ユーザー エクスペリエンスのバランスを取るということです。 単一の運用テナントはシンプルでユーザー エクスペリエンスに最適ですが、特定のビジネス要件と技術要件により、複数の運用テナントが必要になる場合があります。

この記事シリーズでは、Microsoftが実際のデプロイ全体で観察した、次の一般的なテナント アーキテクチャ パターンについて説明します。

- [Microsoft Entra テナント資産ガイダンスの概要](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide)
- [プライマリ運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-primary)
- [非本番環境](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-nonproduction)
- [重要な運用システム用の分離されたテナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-critical-production)
- [ビジネス パートナー アクセス用の分離されたテナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-business-partner)
- [ハイブリッド ID と分離](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-hybrid-identity)

共同運用テナントは、組織が運営するが、統合された企業として連携する必要がある 2 つ以上の運用テナントです。 このパターンは、一般的に、単一のテナントに統合することは実用的ではない企業構造 (合併と買収、子会社、規制された事業単位、または地域の運用) から生じます。

複数のMicrosoft Entra テナントの運用は一般的です。Microsoftこれは、[マルチテナント組織のシナリオ](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/overview)として広く参照されます。 組織は通常、ワークロード、環境、または外部アクセスを分離するために追加のテナントを運用します。 たとえば、外部ユーザーが内部リソースから分離された環境にアクセスする必要がある場合は、別のテナントが必要になる場合があります。 このシリーズのその他の記事では、これらの分離駆動型パターン (非運用、クリティカル運用、ビジネス パートナー テナント) について説明します。この記事では、個別の運用テナントが統合された企業として引き続き共同作業を行う必要がある場合について説明します。

このパターンは、7 つのアーキテクチャ評価領域について詳しく説明する [プライマリ運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-primary) ベースラインに基づいています。 この記事では、運用テナントの共同作業に関する増分的な考慮事項のみを、同じ評価領域のコンテキストで説明します。 全シリーズについては、[Microsoft Entra テナント資産ガイダンスの概要](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide)をご覧ください。

### サンプル シナリオ

Contoso には、企業構造 (合併、子会社、規制対象事業単位、地域単位) により、統合された企業として連携する必要がある複数の運用テナントがあります。 内部ユーザー向けのテナント間のよりシームレスなコラボレーションとアクセスに重点を置いて取り組みます。 Microsoft Entra IDとMicrosoft 365の[テナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)と[マルチテナント組織 (MTO)](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-overview) 機能は、このコラボレーションとアクセスを有効にするのに役立ちます。

Microsoft Entraでは、[テナント間の同期のための](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-topology)複数のトポロジがサポートされています。ハブ アンド スポーク トポロジ (1 つのテナントは、アプリケーションまたはユーザーの中央ハブとして機能します)、メッシュ トポロジ (相互に直接同期するピア テナント)、Just-In-Time コラボレーション (ジョイント ベンチャーなどのシナリオの場合は、接続された組織とエンタイトルメント管理) です。 たとえば、取得後、Contoso は、取得した会社のユーザーをアプリケーション ハブとして Contoso のテナントにプロビジョニングし、アカウントが元のテナントで管理されている間、1 日目から共有アプリケーションとリソースにアクセスできるようにします。

一般的に最適なトポロジは 1 つもなく、これらは推奨事項のランク付けされた一覧ではありません。ID とアプリケーションの場所とテナントの関係に基づいて選択します。 ハブアンドスポークは、共有 ID (ユーザー ハブ) または共有アプリケーション (アプリ ハブ) を所有する中央テナントに適しています。メッシュは、中央ハブなしで直接同期するピア テナントに適しています。 フル メッシュでは、すべてのテナントが他のすべてのテナントと同期されるため、リレーションシップの数と運用オーバーヘッドが急速に増加するため、 [限られた数のテナント](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions)に対してのみ実用的になります。 多くの組織は、これらのパターンを組み合わせています。 動作するシナリオと図を含むトポロジの完全なセットについては、 [テナント間同期のトポロジ](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-topology)に関するページを参照してください。

次の図は、取得例 (アプリケーション ハブを含むハブアンドスポーク トポロジ) を示しています。このトポロジでは、最近取得したテナントのユーザーが Contoso のテナントにプロビジョニングされ、共有アプリケーションとリソースにアクセスできます。

[Image: シナリオ図の例は、アプリケーション ハブのハブアンドスポーク マルチテナント アーキテクチャを示しています。]

### Administration

マルチテナント トポロジでは、ユーザーはホーム テナントと 1 つ以上のリソース テナントを持ちます。 ホーム テナント管理者は、ユーザー アカウントのライフサイクル、資格情報、認証方法、デバイスの要件を管理します。 リソース テナント管理者は、アプリケーションとリソースへのアクセスを管理します。 MTO 内の管理者は、ID 同期を有効にして、通信とコラボレーションを向上させます。

各テナントは分離された境界であり、共通のセキュリティ要件について調整し、一致を図る独自の管理者グループを持っています。 これらの一般的な要件は、テナントごとに個別に実装および管理します。 構成ドリフトを検出して修正するコントロールは、マルチテナント アーキテクチャの中核となる要件となります。

Microsoft Entraテナント ガバナンスでの[セキュリティで保護されたテナントの作成](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/how-to-create-tenant)により、テナントを作成できるユーザーを制御したり、新しいテナントとのガバナンス関係を自動的に確立したり、必要に応じて管理アクセスを回復したりできます。 [関連するテナントは、](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/related-tenants) B2B アクセス、マルチテナント アプリケーションのアクセス許可、共有課金アカウントなどのシグナルを通じて、組織全体のテナントを検出するのに役立ちます。

[マルチテナント管理パターンを](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide#multitenant-administration-patterns)評価して、管理者がテナント全体で管理アクセス権を取得して実行する方法を決定します。 ガバナンス関係を介したテナント間の委任された管理により、組織内の複数のテナント間の調整された管理アクセスを簡略化できます。 特権 ID と管理ワークステーションを専用の分離された管理テナントに一元化する組織もあれば、ユーザーとワークロードをホストするテナント内から管理アクセスを管理する組織もあります。 どちらの方法も、分離、規制、運用の要件に応じて有効です。

管理者は、従業員のライフサイクルの一部として、テナント間でユーザーを移行するための新しい IT プロセスを設計します。 たとえば、米国のユーザーがヨーロッパに再配置された場合、そのユーザー アカウントは 米国 テナントからヨーロッパ テナントに移行されます。

ライセンス管理は、テナント間で分割および分散されるため、より複雑になります。 一部のサービスでは、ライセンスの重複が必要になる場合があります。 たとえば、Microsoft 365と統合するMicrosoft以外のツールでは、テナントごとに個別のインスタンスが必要になる場合があります。

### 変更管理

マルチテナント組織内の複数のテナントに影響する構成変更を慎重に計画、検証、ロールアウトします。 すべてのテナントで、新しいセキュリティ ポリシー、データ損失防止 (DLP) ルール、構成などの変更を実装して追跡します。 さまざまな時間または少し異なる方法で適用される変更による不整合やポリシーの誤差のリスクを軽減します。 テナントは、一方のテナントの機能を他のテナントとは独立してパイロットまたは有効化できます。 たとえば、米国はプレビュー機能の早期導入者である可能性があり、他のリージョンでは待機を選択できます。

### アカウントのライフサイクル

統合されたマルチテナント組織は、テナント間でのユーザー プロビジョニングを自動化します。 この基礎となるのは、[テナント間の同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)です。テナント間の同期規則を構成できるMicrosoft Entra IDの機能です。 テナントの 1 つでプロビジョニングした従業員ユーザーを取得し、他のテナントのそれらのユーザーのコピーを B2B コラボレーション ユーザーとして継続的に維持できます。 ソース テナントでユーザー属性が変更されると、ターゲット テナントは属性データの同期を維持するように更新されます。同様に、ユーザーがホーム組織から離れ、プロビジョニング解除するときに、B2B アカウントを自動的に削除できます。 [テナント間アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)で自動承諾が有効になっているマルチテナント組織のユーザーについては、B2B 招待の承諾をスキップできます。 この方法では、ユーザー アクセスが合理化され、各テナントに電子メールやユーザー アクションを参加させる必要はありません。

テナント間同期の結果は、マルチテナント組織内のテナント全体のユーザー表現であり、テナント内のリソースにアクセスできます。 同様に、ユーザーは、異なるテナントに属している場合でも、相互に検出して共同作業を行うことができます。

マルチテナント組織は、リソースへのアクセス権の付与やグループへの追加など、他のタスクを自動化するように [ライフサイクル ワークフローを構成](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-governance#manage-employee-lifecycles-across-tenants) できます。

### 資格情報の管理

ユーザーは、ホーム テナントに 1 つの資格情報セットを持ち、他のテナントのリソースにアクセスし、他のテナントのユーザーと共同作業を行います。 ユーザーのホーム テナントから MFA とデバイスの状態を受け入れるように、 [テナント間アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration) を構成できます。 このアプローチでは、従業員はプライマリ テナントで MFA またはデバイスの状態の要件を 1 回満たします。 この状態は、マルチテナント組織の他のテナントの条件付きアクセス MFA とデバイス コンプライアンスの要件を満たすことができます。

マルチテナント組織内のテナントの管理者は、ホーム テナント アカウントが組織全体の一般的な認証強度とデバイス体制の要件を満たすようにポリシーに同意します。 この合意により、他のテナントは、多要素認証、準拠デバイス、および Microsoft Entra ハイブリッド参加済みデバイスのクレームを信頼できるようになります。 各テナントに独自の条件付きアクセス ポリシーがある場合は、必要に応じてより多くの制御を適用できます。 B2B コラボレーション ユーザーには、B2B ユーザーのMicrosoft Entra ID 保護など、特定の条件付きアクセス機能に制限[があります](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-b2b)。

B2B ユーザー アカウントの種類も重要な考慮事項です。 デフォルトでは、テナント間同期により、ソース テナントのメンバーに対して、ターゲット テナントの*外部メンバー*`UserType`としてアカウントが作成されます。 *外部メンバー*`UserType`を使用すると、マルチテナント組織内から発信された B2B ユーザーを、外部テナントから取得したゲスト ユーザーと比較して区別できます。 たとえば、*メンバー*に昇格した B2B ユーザーは`UserType`ほとんどのMicrosoft 365アプリケーションで[マルチテナントユーザー検索](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/overview)で使用でき、[Teams マルチテナント エクスペリエンス](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/plan-multi-tenant-org-overview)の恩恵を受けることができます。 外部メンバーには、SharePoint Online の特定のMicrosoft 365 リソースとアクセス許可スコープ (*Fabrikam のユーザー*など) に対する追加のディレクトリ アクセス許可とメンバー レベルの権限があります。 *外部メンバー*に固有のMicrosoft Entra IDとMicrosoft 365にわたる[マルチテナント組織の機能制限](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-known-issues)と共に、これらのコラボレーションの利点とセキュリティに関する考慮事項を評価します`UserType`。

B2B コラボレーション ユーザー (ゲストとメンバーの両方) は、 [内部認証](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)を行うユーザーと同等ではありません。 テナント間リソース アクセスのユース ケースに基づいて[、Microsoft サービス全体の B2B 制限を](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide#b2b-limitations-across-microsoft-services)評価します。

### Collaboration

シングルテナント アーキテクチャほどシームレスではありませんが、マルチテナント組織アーキテクチャにより、特定のMicrosoft 365 ワークロードに対する従業員ユーザーへのテナント間コラボレーション エクスペリエンスが容易になります。

統合された [テナント間のユーザー検索](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/multi-tenant-people-search) では、グローバル アドレス帳を使用できます。 ユーザーは、テナントを切り替えたり、外部のメール アドレスを使用したりすることなく、マルチテナント組織全体のユーザーとの通話、チャット、または会議のスケジュールを設定できます。 ユーザーが他のテナントの同僚のリッチ プレゼンスとプロファイル情報を表示するように設定を構成できます。 Outlookと Teams では、エクスペリエンスは単一テナント環境のエクスペリエンスに近づきますが、ユーザーはコンテキストの切り替え (別のテナントのチームやチャネルへのアクセスなど) や関連する制限 (空き時間情報の予定表の参照など) に気付く場合があります。

マルチテナント組織では、ブランド化の制限が導入されています。 たとえば、テナント間でメール ドメインを共有することはできません。 ブランド化を一貫させるために、us.contoso.com や emea.contoso.com などのサブドメインを作成できます。

Microsoft Entraおよびその他のMicrosoft サービス (Stream や Planner など) の [B2B の制限は](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide#b2b-limitations-across-microsoft-services)、マルチテナント組織内の他のテナントのリソースにアクセスするユーザーに影響します。

### ロールベースのリソースの割り当て

マルチテナント組織では、複数のディレクトリに従業員が表示される可能性があり、割り当てにはテナントごとに個別の構成が必要になるため、ロールの割り当て管理が複雑になる可能性があります。 [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-governance#govern-synchronized-user-access-with-access-packages)を使用して、テナント間でリソースへのアクセスを割り当てます。

### リスク管理

#### ブラスト半径

[プライマリ運用テナントのリスク管理](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-primary#risk-management)の爆発半径の軽減策を確認します。 B2B コラボレーション関係は、ユーザーを個別に招待する場合も、テナント間の同期を通じて大規模にプロビジョニングする場合でも、横移動のパスを作成するため、1 つのテナント内の侵害されたアカウントが他のテナントの足掛かりになる可能性があります。 広範で自動化されたプロビジョニングにより、この露出が広がります。

- 最小特権のゼロ トラスト原則に従います。
- アクセス権を定期的に [確認します](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-governance#review-synchronized-user-access)。
- [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-governance#govern-synchronized-user-access-with-access-packages)アクセス ライフサイクル制御を使用して、マルチテナント組織内の他のテナントのユーザーが不要になった場合に、アプリケーション、データ、およびリソースへのアクセスを削除します。

#### 規制要件

[Microsoft 365 Multi-Geo](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/microsoft-365-multi-geo)のデータ所在地機能を確認します。 さらに、テナントごとに個別に設定することで、テナント作成時に地理的な場所を選択する際、[Microsoft Entra ディレクトリ データのレジデンシー](https://learn.microsoft.com/ja-jp/entra/fundamentals/data-residency)に関する柔軟性を高めることができます。 一部の規制では、テナント内のユーザーのプレゼンスに基づいて、テナントを監査スコープに取り込む場合があります。

規制上の理由から何かを厳密に分離する必要がある場合は、テナント間のユーザー フローを制限できます。 たとえば、規制の厳しい子会社が意図的にマルチテナント組織に参加していない場合があります。 代わりに、コンプライアンス監査で別個のものとして確認できるように、片方向の招待を使用できます。 統合モデルを構成できます。 フル メッシュ (すべてのテナントが互いのユーザーを自由に認識) またはより制御 (ユーザーのサブセットのみを同期) できます。 各同期関係では、同期するユーザーをフィルター処理できます (たとえば、同期する部門は特定の部門のみであり、全員が同期するわけではありません)。

#### セキュリティ操作

インシデントを調査し、複数のテナント間で検出できるように、[マルチテナント管理Microsoft Defender](https://learn.microsoft.com/ja-jp/unified-secops/mto-overview)、すべてのテナントで統一されたビューを提供します。

### その他の考慮事項

MTO ユーザーは、SAML ベースの認証または Kerberos 制約付き委任 (KCD) を使用する統合Windows 認証 (IWA) を使用する[オンプレミス アプリにアクセス](https://learn.microsoft.com/ja-jp/entra/external-id/hybrid-cloud-to-on-premises)できます。 アプリケーションの後者のカテゴリには、Active Directoryのユーザー オブジェクトが必要です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/tenant-estate-critical-production"} -->
## 重要なビジネス システムのMicrosoft Entra テナントに関するガイダンス - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-critical-production
- Service: entra / architecture
- Article date: 2026-06-22
- Summary: ニーズを特定し、アーキテクチャ オプションを比較できるように、重要なビジネス システムのテナント アーキテクチャMicrosoft Entraについて説明します。

Microsoft Entra**テナント資産** (組織が運用するテナントのセット) を設計することは、セキュリティ、コンプライアンス、管理の複雑さ、ユーザー エクスペリエンスのバランスを取るということです。 単一の運用テナントはシンプルでユーザー エクスペリエンスに最適ですが、特定のビジネス要件と技術要件により、複数の運用テナントが必要になる場合があります。

この記事シリーズでは、Microsoftが実際のデプロイ全体で観察した次の一般的なテナント アーキテクチャ パターンについて説明します。

- [Microsoft Entra テナント資産ガイダンスの概要](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide)
- [プライマリ運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-primary)
- [運用テナントの共同作業](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-collaborating)
- [非運用環境](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-nonproduction)
- [ビジネス パートナー アクセス用の分離されたテナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-business-partner)
- [ハイブリッド ID と分離](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-hybrid-identity)

重要な運用システム用のテナントを分離すると、主要な従業員テナントとは別に、組織の最も機密性の高い、価値の高い、またはミッション クリティカルなアプリケーションとリソースが専用テナントに分離されます。 このパターンは、一般的に、メイン テナントのセキュリティ インシデントまたは運用エラーの残留リスクが、強力なセキュリティ制御が適用されていても、それらのワークロードに対して許容できない場合に発生します。このパターンには、爆発半径が含まれ、より厳密なコンプライアンス境界が適用され、横移動が減少します。

このパターンは、7 つのアーキテクチャ評価領域について詳しく説明する [プライマリ運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-primary) ベースラインに基づいています。 この記事では、重要な運用システムを分離するための増分的な考慮事項のみを、同じ評価領域のコンテキストで説明します。 完全なシリーズについては、[テナント資産ガイダンスの概要Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide)参照してください。

### サンプル シナリオ

Contoso は、価値の高いミッション クリティカルなアプリケーションとリソースをホストするための専用テナントを維持しています。 Contoso は、より広範なエンタープライズ環境から専用テナントを分離します。

リスク評価によって、Contoso のアーキテクチャの選択が促進されます。 これらは、1 つのテナント内で使用可能なすべてのセキュリティ制御とベスト プラクティスを適用します。 ただし、主要な従業員テナントにおけるセキュリティ インシデントまたは運用エラーの潜在的な影響は、特定の重要なワークロードでは許容されません。 その結果、Contoso は、より広範な環境でインシデントが発生した場合に最も機密性の高いシステムを保護するために、これらの重要なワークロードを意図的に専用テナントに分割します。

専用テナントでは、より厳密なセキュリティ制御とガバナンスが適用されます。 このソリューションは、最適なセキュリティ体制が整っていても、重要な資産をプライマリ テナントの爆発半径に公開する残りのリスクを想定しないという Contoso の決定を反映しています。

次の図は、Contoso が顧客に提供する重要な SaaS 製品の分離された `FabrikamSaas.onmicrosoft.com` テナントを示しています。

[Image: シナリオ図の例は、顧客にとって重要なサービスとしてのソフトウェア製品の分離されたテナントを示しています。]

### Administration

重要な運用システム用の個別のテナント アーキテクチャでは、異なる管理者セットを構成できるように、新しい境界 (Microsoft Entra ディレクトリ ロールの個別のセット) が作成されます。 これは、リソースに対応し、主要な従業員テナントとは異なる構成の要件を持つアプリケーションを信頼するために、テナント全体の設定の別のセットを提供します。 重要な運用システム用の個別のテナント アーキテクチャを使用すると、Microsoft サービスのインスタンス (Exchange、SharePoint Online、Teams、Intune など) をメインワークフォース テナントから分離できます。

ミッション クリティカルなアプリケーションとリソースをホストするテナントは、主要な従業員テナントよりも制限の厳しいセキュリティ制御を実装します。 これらの制限により、ユーザー エクスペリエンスの摩擦が高まり、承認ワークフローへの管理上の関与が増えます。 たとえば、セルフサービス機能をロックダウンし、コラボレーション最適化の既定値を最も制限の厳しい値に置き換え、ユーザーがサインインできる IP アドレス範囲と場所に対する制限を設定します。

[マルチテナント管理パターンを](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide#multitenant-administration-patterns)評価して、管理者が専用テナントにアクセスする方法を決定します。 最大分離が優先される場合、個別の資格情報を持つローカル アカウントは、このパターンで説明されているセキュリティ体制と一致します。

### 変更管理

重要な運用システム用にテナント アーキテクチャを分離すると、主要な従業員テナントの変更から重要なワークロードが切り離されます。 専用テナントの管理者は、変更を個別に計画できます。 前の変更制御セクションで説明したように、テナント内の **変更制御** に関して同じ考慮事項を適用します。

### アカウントのライフサイクル

通常、管理者は、重要なワークロード テナントへの従業員のユーザー アクセスを事前に選択し、厳密に制御します。 これらのアカウントは、ワークフローによって実装された完全なコントロールに従ってプロビジョニングされる場合があります。 ビジネス ニーズを持つユーザーのみにアクセスを制限し、ライフサイクル ガバナンスを厳格に適用します。 たとえば、従業員が別のロールに移行すると、アクセスが自動的に期限切れになる場合があります。 この例のシナリオでは、従業員が Fabrikam でエンジニアリングロールを離れると、自動化によってアカウントが削除され、SaaS テナントにアクセスできるようになります。 管理者は、アクセス レビューを使用して定期的な再認定を要求できます。 同じ組織内の他のテナントとの外部コラボレーションを無効にすることができます。

### 資格情報の管理

企業環境からの分離を最大限に高めるために、ミッション クリティカルな運用テナントにアクセスする必要があるユーザーのために、個別のアカウントとデバイスをプロビジョニングできます。 条件付きアクセス ポリシーは、すべてのセッションに対してフィッシングに強い認証強度と準拠デバイスを強制します。

### Collaboration

重要な運用システム用の個別のテナント アーキテクチャにより、外部コラボレーションが明示的に制限されます。 この分離により、他のテナントのユーザーへの露出と侵害が軽減されます。

### ロールベースのリソースの割り当て

重要な運用システム用の個別のテナント アーキテクチャでは、ロールの割り当てを厳密に範囲指定します。 [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)を使用してアプリケーション アクセスを割り当てることができます。 リソース所有者はアクセス要求を正当化します。 すべての割り当ては定期的なレビューを受けることができます。 テナントは [、管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units) と [制限付き管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-restricted-management) を使用して、重要なワークロード環境内のアクセスをセグメント化できます。

### リスク管理

#### ブラスト半径

重要な運用システム用に個別のテナント アーキテクチャを使用すると、メインワークフォース テナントのセキュリティ インシデントまたは運用エラーの残留リスクが許容できない場合に、追加の軽減のための分離が提供されます。 重要なアプリケーションを別のテナントでホストする場合は、侵害を含め、より厳密なコンプライアンス境界を適用し、横移動リスクを軽減できます。 監査ログ、条件付きアクセス、およびデバイス管理ポリシーは、メインワークフォース テナントとは別に構成できます。

重要な運用システムの個別のテナント アーキテクチャの利点を実現するには、分離を損なう環境の依存関係を回避します。 たとえば、メインワークフォーステナントと専用テナントが同じActive Directoryフォレストのセットから同期する場合、それらのフォレスト内のインシデントは両方のテナントに影響します。 [ハイブリッド ID と分離](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-hybrid-identity)における潜在的なインシデントについて説明します。

#### 規制要件

別のテナントには、データ主権規制の要件に準拠するために、異なるクラウドと位置情報に専用テナントを作成できる別の境界が用意されています。 専用テナントは、監査スコープを減らすのに役立ちます。

### その他の考慮事項

重要なワークロード テナントを運用する場合、個別のセキュリティ ベースラインや監視などのオーバーヘッドが増えます。 時間の経過と共に、複数のテナントにわたるセキュリティまたは運用に関する組織全体のポリシー変更を実装して監査し、 [構成管理](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/configuration-management)などの機能を使用して誤差を監視します。これは、これらのテナントが必要とするより厳密なベースラインと監査証拠にとって特に重要です。

テナント間でライセンスが重複すると、運用オーバーヘッドが高くなります。 主要なビジネス機能、規制対象データ、または国家安全保障上の利益をサポートするワークロードは、トレードオフを正当化します。 分離の根拠を文書化し、ライフサイクル、アクセス、監査のプロセスが企業のリスク管理と一致していることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/tenant-estate-guide"} -->
## Microsoft Entra テナント資産ガイダンスの概要 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide
- Service: entra / architecture
- Article date: 2026-06-22
- Summary: できるだけ少ないテナントで要件を満たすことができるように、一般的なテナント アーキテクチャ パターンからMicrosoft Entraテナント資産を作成する方法について説明します。

Microsoft Entra**テナント資産** (組織が運用するテナントのセット) を設計することは、セキュリティ、コンプライアンス、管理の複雑さ、ユーザー エクスペリエンスのバランスを取るということです。 単一の運用テナントはシンプルでユーザー エクスペリエンスに最適ですが、特定のビジネス要件と技術要件により、複数の運用テナントが必要になる場合があります。

このガイダンスは、組織が運用するMicrosoft Entra テナントの数と、ワークロード、ID、および外部コラボレーションを分散する方法を決定するのに役立ちます。 ここでは、Microsoftが実際のデプロイ全体で観察し、それぞれを同じ 7 つのアーキテクチャ評価領域に対して評価する一般的なテナント アーキテクチャ パターンについて説明します。そのため、オプションを比較し、セキュリティ、コンプライアンス、リスク、運用要件を可能な限り少ないテナントで満たすことができます。 これは、ID およびセキュリティ アーキテクト、IT 管理者、および彼らが助言するビジネスおよびセキュリティ スポンサーを対象にしており、テナント アーキテクチャの計画またはレビューを行います。

Note

このガイダンスでは、**従業員テナント**Microsoft Entraについて説明します。従業員、内部アプリ、組織のリソースを保持するテナント (外部のビジネス パートナーや招待したゲストを含む)。 **外部テナント**、顧客 ID とアクセス管理 (CIAM) のMicrosoft Entra 外部 IDで使用される個別の構成については説明しません。 2 つの違いについては、「テナントの [構成](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations)」を参照してください。

### テナント アーキテクチャ パターン

これらのパターンは **、論理テナント ロール** を表します。テナントがアーキテクチャで果たす役割と、それが存在する理由です。 ロールは、資産内の各テナントを設計および管理する方法を推論する方法です。

モデルの中心にあるのは **、プライマリ 運用テナント**です。つまり、従業員 ID と運用ワークロードをホストする運用テナント、および必要に応じて外部コラボレーションです。 ほとんどの組織は、このようなテナントを 1 つから始めます。 要件はそこから増大する可能性があります。大規模な組織では、複数の運用テナントと、ここで説明する他のロールに合わせた多数のテナントが運用される場合があります。 [プライマリ運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-primary)に関する記事では、このロール全体について説明します。

組織のテナント資産は、プライマリ運用テナントと追加のテナントで構成されます。これは、特定の要件を満たすためにテナントを作成するか、合併、買収、またはその他の組織の変更を通じて継承するかに関係なく、追加のテナントで構成されます。 追加のテナントごとに管理オーバーヘッド、コスト、調整が追加されるため、セキュリティ、コンプライアンス、運用の要件で許容される数のテナントとして動作します。

複数の人事システム、Active Directory フォレスト、または Microsoft Entra テナントを継承する場合 (最も一般的には M&A — 並列および結合された ID インフラストラクチャ オプションは、少数のインスタンスに統合したり、並列で実行したりするためのデシジョン ツリーと技術的なオプションを提供します。 このシリーズは、ターゲット テナント アーキテクチャを決定するのに役立ちます。その記事は、それに到達するための統合テクノロジを選択するのに役立ちます。 統合するのではなく、取得したテナントを保持するオプションは B2B コラボレーションに依存するため、ここで説明する B2B の制限が 適用されます。

| 論理テナント ロール | パターン |
| --- | --- |
| プライマリ運用テナント - 従業員 ID、運用ワークロード、および必要に応じて外部コラボレーションをホストします | [プライマリ運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-primary) |
| M&A アクティビティ中に取得したテナントや独立したビジネス ユニットによって運用されているテナントなど、共同作業を行う追加の運用テナント | [運用テナントの共同作業](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-collaborating) |
| 重要な運用システム用の分離されたテナント | [重要な運用システム用の分離されたテナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-critical-production) |
| ビジネス パートナー アクセス用の分離テナント | [ビジネス パートナー アクセス用の分離されたテナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-business-partner) |
| テナント全体の変更を開発、テスト、検証するための非運用テナント | [非運用環境](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-nonproduction) |

[プライマリ運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-primary)に関する記事は、シリーズのベースラインです。7 つのアーキテクチャ評価領域について詳しく説明します。 その他のパターンに関する記事では、その論理テナント ロールに関する増分の考慮事項についてのみ説明します。

[ハイブリッド ID と分離](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-hybrid-identity) は、テナント ロールではなく横断的な考慮事項であり、資産内の任意のテナントに適用できます。

**例。** 1 つのプライマリ運用テナントを持ち、その重要なシステム用に別のテナントを持つ組織は、ワークフォース [テナントのプライマリ運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-primary) ベースラインと、 [分離テナントの重要な運用システムの分離テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-critical-production) という 2 つのパターンを組み合わせています。

### テナント アーキテクチャの評価領域

次のアーキテクチャ評価領域は、要件を特定し、テナント構成オプションを比較するための一貫したフレームワークを提供します。

- **管理。** 管理者の境界を越えて、特権ロール、テナント全体の設定、およびサービス固有の制御を委任および管理します。 追加のテナントにより、調整のオーバーヘッドと構成の誤差のリスクが高まる一方で、必要なワークロードに対して独立したテナント全体の構成が可能になります。
- **コントロールを変更します。** 構成の変更を計画、検証、ロールアウトして、安定性、コンプライアンス、セキュリティを維持します。 段階的なロールアウト メカニズムがないテナント スコープ設定の検証環境として追加のテナントを使用できますが、ポリシーのずれを防ぐために調整が必要です。
- **アカウントのライフサイクル。** ユーザー アカウントをライフサイクル全体にわたってプロビジョニング、移動、プロビジョニング解除するためのビジネス ルールを定義します。 テナント間のシナリオでは、孤立したアクセスを防ぐために、明示的なライフサイクル自動化が必要です。
- **資格情報の管理。** 従業員と外部ユーザーの認証方法、資格情報ポリシー、多要素認証の適用を管理します。 追加のテナントでは、他のテナントから MFA とデバイスの要求を信頼するか、別の資格情報を必要とするかを決定する必要があります。 分離が増えると、ユーザーの摩擦が増加します。
- **コラボレーション。** 従業員と外部ユーザーが、Microsoft 365 サービス間で通信、コンテンツ共有、共同作業を行えるようにします。 テナントの分離が増えるとコラボレーション エクスペリエンスが低下するため、分離要件と生産性への影響のトレードオフを考慮してください。
- **ロールベースのリソースの割り当て。** アプリケーション、グループ、サイトなどのリソースへの従業員アクセスと外部ユーザー アクセスを割り当てて管理します。 追加のテナントでは、1 つのディレクトリ全体で統合管理するのではなく、各テナントで独立した割り当ての構成とガバナンスが必要です。
- **リスク管理。** 爆発半径、規制コンプライアンススコープ、テナント境界を越えた横移動リスクを評価して軽減します。 追加のテナントには、セキュリティ インシデントの影響を含め、全体的な攻撃面と運用の複雑さを拡大しながら、コンプライアンス スコープを減らすことができます。

### アプリまたはワークロードを既存のテナントと統合するタイミング

新しいアプリまたはワークロードを導入する場合、または既存のアプリまたはワークロードを再評価する場合は、シングル サインオンとユーザー プロビジョニングのために既存のテナント (通常はプライマリ運用テナント) と統合するか、別のテナントに配置するかを決定します。 アプリまたはワークロードを 1 つのテナントで操作するユーザーとリソースと併置することで、最もシームレスなユーザー エクスペリエンスと機能の忠実性が実現します。 代わりにテナントを追加するには、2 種類のトレードオフが必要です。 **追加のテナントごとに** オーバーヘッドが追加されます。

- **管理とガバナンス。** ロール、ポリシー、およびガバナンスは、各テナントで個別に構成および監査されるため、調整のオーバーヘッドと構成ドリフトのリスクが増加します。
- **アカウントのライフサイクル。** 各テナントには、独自のプロビジョニングとプロビジョニング解除が必要です。
- **攻撃対象領域。** 追加の各テナントは、セキュリティで保護および監視するための別のディレクトリであり、全体的な攻撃対象領域を拡大します。

**追加** のトレードオフは、引き続き連携する必要があるワークロードまたは ID がテナント間で分割される場合に適用されます。これは、B2B コラボレーションによるテナント間アクセスに依存するためです。 ( [重要な運用システムの分離テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-critical-production) などのパターンでは、この接続が意図的に回避されます。そこでは、相互運用性の低下が目標であり、アーキテクチャ上の欠点ではありません)。

- **コラボレーション。** 異なるテナントに所属するユーザーは、より限定的なMicrosoft 365エクスペリエンスを得ることができます。1 つのディレクトリ内でシームレスな Teams、SharePoint、共同編集、検索、プレゼンスのシナリオの一部が減ったり、テナントの切り替えが必要になったりします。 複数の運用テナントを運用する必要がある場合は、 [共同作業の運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-collaborating) でこのエクスペリエンスの一部を回復する方法について説明します。
- **機能のサポート。** B2B コラボレーション ユーザーは、[内部認証を行うユーザー](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)と同等ではありません。特定の機能は、外部 (B2B) ユーザーに対して制限付きまたはサポートされていません (Microsoft サービス全体の B2B 制限を参照)。
- **ユーザーの摩擦。** テナント間の信頼を構成しない限り、ユーザーは MFA を登録し、テナントごとに個別に認証する必要があります。また、一部の管理者エクスペリエンスでは B2B または委任されたテナント間管理がサポートされていません。

特定の要件がそれらを上回る場合を除き、これらのトレードオフを分離し、既存のテナントとアプリまたはワークロードを統合する理由と比較します。 このシリーズの各パターンは、このような要件を表します。たとえば、重要なワークロードの管理とコンプライアンスのスコープをメイン テナント ([重要な運用システム用の分離されたテナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-critical-production)) とは別に維持したり、ワークフォース テナントからビジネス パートナー アクセスを分離したり ([ビジネス パートナー アクセス用に分離されたテナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-business-partner))、引き続き共同作業する必要がある個別の運用テナントを運用する ([運用テナントの共同作業](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-collaborating)) などです。 シングル サインオンまたはユーザー プロビジョニングのためにワークロードを既存のテナントに接続すると、テナントの管理者と ID の変更によって影響を受けることもできます。これは、管理の分離が要件である場合の考慮事項です。

### テナント ライフサイクル ガバナンス

テナントの数が増えるにつれて、攻撃対象領域も増えます。 分類、作成コントロール、使用停止プロセスなど、テナント ライフサイクル ガバナンスを最初から計画します。 [Microsoft Entraテナント ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/overview)を使用して、関連する "シャドウ IT" テナントの検出、テナントの作成の制御、構成の誤差の監視、管理されたテナントの 1 か所からの管理など、資産全体でこれを運用化します。 セキュリティを[リスクとするレガシ システムの削除](https://learn.microsoft.com/ja-jp/security/zero-trust/sfi/remove-legacy-systems-that-risk-security)に関する記事では、大規模なマルチテナント資産の運用に関するMicrosoftの学習に基づいて、テナントの広がりを減らし、レガシ環境を排除するためのガイダンスを提供します。

### マルチテナント管理パターン

複数のテナントを運用する場合は、管理者が管理アクセス権を取得して実行する方法を決定します。 次の表では、3 つの方法を比較します。

| パターン | Description | 考慮事項 |
| --- | --- | --- |
| テナント間の委任された管理 (GDAP) | 管理テナントと管理テナントの間に [ガバナンス関係](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-relationships) を確立します。 管理者は、管理テナントの資格情報を使用して管理テナントにサインインし、最小特権ロールの割り当てに基づいて [テナント間の委任管理](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/cross-tenant-delegated-administration) を実行します。 | セキュリティ グループとガバナンス ポリシー テンプレートを使用して、一貫したポリシー適用を使用して、多くのテナント間でスケーラブルです。 管理されたテナントでは、B2B またはローカル アカウントは必要ありません。 一部の管理者エクスペリエンスでは GDAP がサポートされていません。 テナント ガバナンスリレーションシップは、パートナー センターを通じて同じ 2 つのテナント間で構成する GDAP リレーションシップと共存できません。 [テナント ガバナンスに関する FAQ](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/faq) では、詳細なガイダンスが提供されます。 |
| B2B 管理者アカウント | B2B コラボレーション ユーザーとして管理者を招待し、各テナントにディレクトリ ロールを割り当てます。 管理者は、ホーム Microsoft Entra テナントを使用してサインインします。 | 管理者の資格情報を 1 つ使用すると、サインインの手間が軽減されます。 一部の管理者エクスペリエンスでは、B2B ユーザーがサポートされていません。 ロールの割り当てには、テナントごとに個別の管理が必要です。 アカウントのライフサイクルは、B2B の招待と引き換えのプロセスによって異なります。 1 人のユーザーは、メンバーまたはゲストとして[限られた数のMicrosoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions)に属できます。 |
| ローカル アカウント | 各テナントで個別の管理者アカウントを作成および管理します。 | テナントあたりの最大分離と独立。 資格情報管理のオーバーヘッドが最も高い。 単一 ID の追跡可能性はありません。 孤立したアカウントのリスクの増加。 |

選択したパターンに関係なく、ターゲット テナントのセキュリティとコンプライアンスの制御は、そのテナントの管理者セッションに適用されます。 たとえば、条件付きアクセス ポリシーは、アクセスログと監査ログキャプチャ管理アクティビティを管理します。

テナント間の委任された管理では、パートナー センターがパートナーが顧客テナントを管理できるようにするために使用するのと同じ詳細な委任された管理者特権 (GDAP) テクノロジを使用します。 構成された委任された管理とのガバナンス関係を確立すると、管理テナントから指定されたセキュリティ グループが管理タスクを実行できるようにする GDAP ロールの割り当てが管理テナントに作成されます。 管理テナントの利害関係者は、委任された管理者が独自のサインイン ログと監査ログを通じて実行するすべてのアクションを個別に監視、確認、監査できます。

分離要件、運用スケール、ガバナンスの成熟度に基づいて、テナント間管理パターンを選択します。 同じ組織内のテナントリレーションシップごとに異なるパターンを使用できます。 たとえば、非運用テナントと子会社テナントに対してテナント間の委任された管理を使用しながら、他のすべての環境からの最大限の分離を必要とするテナントのローカル アカウントを選択できます。

### シェイプ テナント アーキテクチャが必要なユーザー

この記事シリーズ全体を通して、アクセスとコラボレーションでテナント アーキテクチャに対応する必要がある次のユーザーについて説明します。

- **労働 力。** 組織内で安全なアクセスとコラボレーションを必要とするフルタイムの従業員、パートタイム 従業員、請負業者。
- **外部ユーザー。** 企業と協力して相互目標を達成する組織または組織外の個人。 たとえば、サプライヤー、ベンダー、コンサルタント、戦略的提携では、特定のリソースやアプリケーションへのアクセスが必要になる場合があります。

### Microsoft サービス全体の B2B の制限事項

Microsoft Entra B2B コラボレーションを使用すると、ユーザーはホーム テナントから 1 セットの資格情報を使用して他のテナントのリソースにアクセスし、他のテナントのユーザーと共同作業を行うことができます。 ただし、B2B コラボレーション ユーザーは [、内部認証を行うユーザー](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)と同等ではありません。 テナント間リソース アクセスのユース ケースに基づいて、次の外部 (B2B) ユーザーの考慮事項と制限事項を評価します。

- [B2B コラボレーションの制限事項 - Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/current-limitations)
- [マルチテナント組織の制限事項 - Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-known-issues)
- [B2B ユーザーのMicrosoft Entra ID 保護 - Microsoft Entra ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-b2b)
- [トークン保護が条件付きアクセス ポリシーを強化する方法 - Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-token-protection)
- [B2B ユーザーの認証と条件付きアクセス - Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/tenant-estate-hybrid-identity"} -->
## ハイブリッド ID と分離マルチテナント ガイドのMicrosoft Entra - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-hybrid-identity
- Service: entra / architecture
- Article date: 2026-06-22
- Summary: ニーズMicrosoft Entra識別し、アーキテクチャ オプションを比較できるように、ハイブリッド ID と分離のためのテナント アーキテクチャについて説明します。

Microsoft Entra**テナント資産** (組織が運用するテナントのセット) を設計することは、セキュリティ、コンプライアンス、管理の複雑さ、ユーザー エクスペリエンスのバランスを取るということです。 単一の運用テナントはシンプルでユーザー エクスペリエンスに最適ですが、特定のビジネス要件と技術要件により、複数の運用テナントが必要になる場合があります。

この記事シリーズでは、Microsoftが実際のデプロイ全体で観察した次の一般的なテナント アーキテクチャ パターンについて説明します。

- [Microsoft Entra テナント資産ガイダンスの概要](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide)
- [プライマリ運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-primary)
- [運用テナントの共同作業](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-collaborating)
- [非運用環境](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-nonproduction)
- [重要な運用システム用の分離されたテナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-critical-production)
- [ビジネス パートナー アクセス用の分離されたテナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-business-partner)

この記事では、ハイブリッド ID と分離について説明します。 マルチテナント アーキテクチャは、クラウド レイヤーで強力な分離を提供できます。 ただし、多くの組織はハイブリッド ID モデルを運用しています。 ハイブリッド ID は、オンプレミス環境とクラウド環境全体のリソース アクセスとデバイス管理をサポートします。 ただし、攻撃面と運用の複雑さが増します。 デプロイ、監視、保守には、より大きな投資が必要です。

Microsoft Entra IDテナントは、オンプレミスの Active Directoryからユーザー、グループ、デバイスを Microsoft Entra Connect、Cloud Sync、Microsoft Identity Manager などのツールと同期します。

分離要件を満たすようにマルチテナント アーキテクチャを設計する場合は、各Microsoft Entra テナントと基になるインフラストラクチャでセグメント化コントロールを使用します。 たとえば、複数のテナントが同じActive Directory フォレストから (または信頼を使用する相互接続されたフォレストから) ソース ID を取得した場合、共有 ID ソースによってテナント レベルの分離が損なわれる可能性があります。 次の潜在的なインシデントを計画します。

- 侵害されたオンプレミス アカウントは、そのアカウントが各テナントに同期する場合に、複数のテナントに伝達される可能性があります。
- オンプレミスで管理されているグループ メンバーシップは、スコープが不適切な場合に、テナント間でアクセス権を誤って付与する可能性があります。
- デバイス コンプライアンスポリシーと条件付きアクセス ポリシーは、テナント間で共有される可能性があるハイブリッド参加済みデバイスからのシグナルに依存できます。

ハイブリッド シナリオで意味のある分離を実現するには、次の推奨事項を検討してください。

- テナントActive Directory境界に合わせて**フォレストまたはドメインをセグメント**化します。 たとえば、テナントごとに個別のドメインを使用し、明示的に必要でない限り、クロスドメインの信頼を回避します。
- **同期コネクタのスコープ** を設定して、各テナントに同期する ID を制限します。 複数のテナントに同じ ID が表示される可能性がある同期スコープが重複しないようにします。
- 特にグループがオンプレミスから同期する場合は、グループ**管理のプラクティスを確認**します。 グループ メンバーシップが誤って分離境界をまたがらないようにします。
- 特に Intune またはハイブリッド モードでConfiguration Managerを使用する場合は、**デバイス管理の境界を評価**します。 サポートされているテナントに合わせてデバイスを登録および管理します。
- オンプレミスの管理者に**最小限の特権原則を適用**します。 Active Directoryの権限を持つ管理者は、不適切なスコープの同期を持つ複数のテナントに間接的に影響を与える可能性があります。
- **クラウドの分離をオンプレミスのインフラストラクチャの分離に合わせます**。 慎重に設計しないと、ハイブリッド ID がテナントの境界をバイパスするブリッジになる可能性があります。 セキュリティ、コンプライアンス、運用上の理由からマルチテナント アーキテクチャを実装する場合は、計画にActive Directoryとデバイス管理アーキテクチャを含めます。

上記の考慮事項では、共有オンプレミス インフラストラクチャが意図せずにテナントの境界を弱める可能性があるハイブリッド ID モデルで動作するマルチテナント アーキテクチャに固有の分離リスクを強調しています。 これらの推奨事項は追加的であり、確立された ID セキュリティのベスト プラクティスに代わるものではありません。 階層化されたActive Directory管理モデル、特権アクセス ワークステーション (PAW)、階層 0 資産の厳密な分離など、十分に理解された制御を引き続き適用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/tenant-estate-nonproduction"} -->
## 非運用環境でのマルチテナント ガイダンスのMicrosoft Entra - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-nonproduction
- Service: entra / architecture
- Article date: 2026-06-22
- Summary: ニーズを特定し、アーキテクチャ オプションを比較できるように、非運用環境のテナント アーキテクチャMicrosoft Entraについて説明します。

Microsoft Entra**テナント資産** (組織が運用するテナントのセット) を設計することは、セキュリティ、コンプライアンス、管理の複雑さ、ユーザー エクスペリエンスのバランスを取るということです。 単一の運用テナントはシンプルでユーザー エクスペリエンスに最適ですが、特定のビジネス要件と技術要件により、複数の運用テナントが必要になる場合があります。

この記事シリーズでは、Microsoftが実際のデプロイ全体で観察した、次の一般的なテナント アーキテクチャ パターンについて説明します。

- [Microsoft Entra テナント資産ガイダンスの概要](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide)
- [プライマリ運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-primary)
- [運用テナントの共同作業](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-collaborating)
- [重要な運用システム用の分離されたテナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-critical-production)
- [ビジネス パートナー アクセス用の分離されたテナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-business-partner)
- [ハイブリッド ID と分離](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-hybrid-identity)

この記事では、非運用環境について説明します。 非運用テナントのユース ケースには、次のようなものがあります。

- 次のシナリオの運用環境の変更制御手順の一環として、テナント全体の構成変更を計画、設計、文書化、テストします。

    - セキュリティ グループや同様のパイロット手法を使用して、運用テナントで段階的なロールアウトを実行することはできません。
    - 段階的なロールアウトでは、誤りが発生した場合に操作が中断される過度のリスクが伴います。
- ビジネス継続性とディザスター リカバリー (BCDR) の演習を計画、文書化、検証します。
- テナント全体の広範なアクセス許可を持つアプリケーションまたはツールの開発環境 (たとえば、 `Directory.Read.All` アクセス許可で大規模にディレクトリ オブジェクトを更新するスクリプト)。

非運用テナントは、概念実証やハッカソンなどのユース ケースで短いライフサイクルを持つことができます。 変更制御の運用前テナントは永続的であり、依存関係を含め、対応する運用環境を厳密に反映している可能性があります。 たとえば、Microsoft Entra Connect for preproduction は、実稼働前Active Directoryから ID を同期し、運用前 HR システムからプロビジョニングします。

このパターンは、7 つのアーキテクチャ評価領域について詳しく説明する [プライマリ運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-primary) ベースラインに基づいています。 非運用テナントでは、主にそのベースライン (特に運用テナントの変更制御検証環境) がサポートされているため、この記事では、同じ評価領域のコンテキストで 1 つを運用するための考慮事項について説明します。 完全なシリーズについては、[テナント資産ガイダンスの概要Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide)参照してください。

### サンプル シナリオ

Contoso には、変更制御、開発、テスト用の `ContosoSandbox.onmicrosoft.com` セカンダリ テナントがあります。 セカンダリ テナントを運用テナントから分離します。

次のシナリオ例の図は、運用環境と非運用環境の両方を含む Contoso のテナント アーキテクチャを示しています。

[Image: シナリオ図の例は、運用環境と非運用環境の両方を含むテナント アーキテクチャを示しています。]

### Administration

変更制御用の運用前テナントの管理者が、アカウント所有権の追跡可能性が明確な対応する運用環境との整合性を維持していることを確認します。 可能な場合は常に、特権アカウントに対して一貫したセキュリティ制御を維持します。

非運用テナントを概念実証、実験、および同様のスタンドアロンユース ケースに使用する場合は、一元管理プロセスを定義します。 テナントの作成と使用停止を一貫して行い、追跡可能性のある管理者を所有者に割り当て、通信用のテナントのインベントリを作成します。 セキュリティ チームがテナントへの特権アクセスを維持し、可視性、リスク評価の監査、および必要に応じて軽減策の監視と実行を行います。

[マルチテナント管理パターンを](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide#multitenant-administration-patterns)評価して、管理者が非運用テナントにアクセスする方法を決定します。 ガバナンス関係を通じたテナント間の委任された管理により、運用テナントからの管理監視を維持しながら、不安定な非運用テナントでのアカウントの拡散を減らすことができます。

非運用テナントは揮発性であり、運用環境の依存関係としては適さないと考えてください。 たとえば、運用テナントの非運用テナントで定義されているマルチテナント アプリケーションを使用しないでください。

### 変更管理

非運用テナント アーキテクチャは、「 プライマリ運用テナントの変更制御」で説明されている課題に管理者が対処するのに役立ちます。 組織は、最初に非運用テナントのテナント全体の変更を計画、設計、実装できます。 ドキュメントとコンティンジェンシーの目的でロールバックします。 成功条件を満たしたら、ドキュメントを作成して運用環境にロールアウトします。 Microsoft Entraテナント ガバナンスの[構成管理](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/configuration-management)を使用して、既知の適切な構成のスナップショットを作成し、それを使用してベースラインを作成し、運用ロールアウト前にテナント全体の変更を検証するときに誤差を監視できます。

すべての変更制御ケースに個別のテナントが必要なわけではありません。 たとえば、Azureは、個別の管理グループ、リソース グループ、サブスクリプションを使用して、1 つの[テナント内で](https://learn.microsoft.com/ja-jp/azure/cloud-adoption-framework/ready/considerations/environments)堅牢な開発テスト ステージング運用環境を提供できます。

### アカウントのライフサイクル

サンドボックス テナントで、アクセスが必要なユーザー (開発者、テスト担当者、管理者など) のアカウントをプロビジョニングします。 これらのアカウントは、サンドボックスに対してローカルとして管理するか (メイン テナントとは別の資格情報でアクセスするユーザー)、または B2B コラボレーション アカウントMicrosoft Entra (ユーザーはメイン テナントの資格情報を使用してアクセスします) として管理します。 どの方法を選択するかは、ユース ケースによって異なります。 たとえば、一部のMicrosoftクラウド サービスでは[、B2B ユーザー アカウント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide#b2b-limitations-across-microsoft-services)のエクスペリエンスや制限が異なる場合があります。

サンドボックス テナントでローカル アカウントを作成するには、カスタム オーケストレーション ロジックが必要な場合があります。 これらのアカウントを所有する従業員に対して、適切な追跡メカニズムを確保します。

B2B を選択した場合は、テナント間同期を使用して、サンドボックス内のアカウントをシームレスにプロビジョニングおよびプロビジョニング解除し、属性の同期を維持できます。または、アクセスと共に統合されたアカウント ライフサイクルを提供する [エンタイトルメント管理アクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users) を使用して、企業ユーザーをサンドボックスにオンボードすることもできます。

アクセス権を持つアカウントが、非運用テナント内のすべてのアカウントと必ずしも等しいとは限りません。 たとえば、開発テスト テナントでは、運用環境を表すディレクトリ データのボリュームを維持するために、テナント内のユーザー オブジェクトとグループ オブジェクトが増える場合があります。

### 資格情報の管理

B2B を使用すると、ユーザーは運用テナントのプライマリ テナント資格情報で認証されます。 サンドボックス テナントは、認証のためにホーム テナントを信頼します。 [クロステナント アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)を有効にして、ホーム テナントからの多要素認証とデバイスの状態を信頼する場合、ユーザーは複数のプロンプトを登録したり、応答したりする必要はありません。 サンドボックス テナントでローカル アカウントの資格情報を個別に管理します。 パスワード ポリシー、MFA 登録、資格情報のライフサイクル制御を個別に適用します。 サンドボックスでデバイス ベースのアクセス制御が必要な場合は、サンドボックス テナント内のデバイスのプロビジョニング、登録、管理も行います。 運用環境のデバイスの状態に依存しないでください。

### Collaboration

B2B アカウントは便宜上非運用テナントにアクセスできますが、このアーキテクチャは外部コラボレーションには最適ではありません。 サンドボックス テナントのリソース (アプリ、リソース、データ) は、明示的に招待され、アクセス権が付与されない限り、プライマリ テナントの通常のユーザーには表示されません。 厳密な制御により、運用環境からサンドボックスへの共有が制限されます。 サンドボックスと運用環境の間でコンテンツをアドホックに共有しないようにします。 Contoso の場合、サンドボックス内にアカウントを持っているのは、特定の開発者と管理者だけです。 Teams チャットやSharePoint共有などの日常的なコラボレーションは、このモデルでは広く行われません。

### ロールベースのリソースの割り当て

サンドボックス リソース アクセスは、同じMicrosoft Entra IDコンストラクト (グループ、アプリケーション ロール、エンタイトルメント管理アクセス パッケージの割り当て) を使用して管理しますが、そのテナントのアプリケーション、リソース、データを対象とします。

### リスク管理

#### ブラスト半径

このテナントコンポジション パターンでは、ブラスト半径が制限されます。 非運用テナントで問題が発生した場合 (構成ミスなど)、運用環境のリソースに直接影響を与えるべきではありません。 サンドボックス テナントは、個別の監査ログと管理者制御を使用して、個別のセキュリティ境界を形成します。 実際のユーザーを中断することなく、危険な変更 (新しい条件付きアクセス ポリシーや一括インポートなど) をテストできます。 運用環境からサンドボックスへのゲスト招待を使用する場合、ID は分離されません。 プライマリ テナントの侵害されたユーザーも、サンドボックスで大混乱を引き起こす可能性があります。 同様に、プライマリ テナントでの操作上の間違い (ユーザーの削除や資格情報の取り消しなど) によって、そのユーザーのサンドボックス アクセスが予期せず切断される可能性があります。 このシナリオを軽減するには、完全な ID 分離を選択します (サンドボックス用に個別の資格情報を持つローカル アカウントが必要な場合など)。

サンドボックステナントと運用前テナントは非運用システムですが、これらのテナントにアクセスすると、脅威アクターは運用環境に関する貴重な洞察を得る可能性があります。 非運用テナントを適切に構成して監視します。

#### 規制要件

非運用テナントは、規制機関が必要とする変更管理プロセスの証拠を顧客が提供するのに役立ちます。

### その他の考慮事項

追加のテナントを運用する場合は、管理者が別のディレクトリを監視してセキュリティで保護するため、より多くのオーバーヘッドが必要になります。 サンドボックスが組織のセキュリティ ポリシーを満たしていることを確認し、必要な条件付きアクセスまたは ID 保護ポリシーを適用することが必要な場合があります。 サンドボックス テナントには、他のライセンスが必要な場合があります。 たとえば、Microsoft Intuneまたは Purview の機能をテストする場合や、ゲスト ユーザーが*メンバー*に変換する場合`UserType`アクセスが広い場合などです。 サンドボックスでリスクが発生しないようにガバナンスを確立します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/architecture/tenant-estate-primary"} -->
## Microsoft Entra プライマリ運用テナントのガイダンス - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-primary
- Service: entra / architecture
- Article date: 2026-06-22
- Summary: ニーズを特定し、アーキテクチャ オプションを比較できるように、プライマリ運用テナントのテナント アーキテクチャMicrosoft Entraについて説明します。

Microsoft Entra**テナント資産** (組織が運用するテナントのセット) を設計することは、セキュリティ、コンプライアンス、管理の複雑さ、ユーザー エクスペリエンスのバランスを取るということです。 単一の運用テナントはシンプルでユーザー エクスペリエンスに最適ですが、特定のビジネス要件と技術要件により、複数の運用テナントが必要になる場合があります。

この記事シリーズでは、Microsoftが実際のデプロイ全体で観察した、次の一般的なテナント アーキテクチャ パターンについて説明します。

- [Microsoft Entra テナント資産ガイダンスの概要](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide)
- [運用テナントの共同作業](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-collaborating)
- [非本番環境](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-nonproduction)
- [重要な運用システム用の分離されたテナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-critical-production)
- [ビジネス パートナー アクセス用の分離されたテナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-business-partner)
- [ハイブリッド ID と分離](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-hybrid-identity)

プライマリ運用テナントは、従業員 ID と運用ワークロード、および必要に応じて外部コラボレーションをホストします。 ほとんどの組織は、このようなテナントを 1 つから始めます。 要件はそこから増大する可能性があります。大規模な組織では、複数の運用テナントと、上記の 1 つ以上のパターンに合わせた多数のテナントが運用される場合があります。 1 つのテナント内で作業すると、Microsoft サービス全体で最もシームレスなコラボレーション エクスペリエンスと最高レベルの機能が提供されます。

この記事は、[Microsoft Entra テナント資産ガイダンス](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide) シリーズのベースラインです。7 つの[アーキテクチャ評価領域](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide#tenant-architecture-evaluation-areas)について詳しく説明します。 互いのパターンに関する記事では、同じ評価領域のコンテキストで、そのロールの増分の違いのみを説明します。 ワークロード、ID、または外部コラボレーションを別のテナントでホストする予定の場合は、対応するパターンに関する記事を参照してください。

ここでワークロードを保持するか、別のテナントに分割するかを決定するトレードオフについては、「 [アプリまたはワークロードを既存のテナントと統合するタイミング](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-guide#when-to-integrate-an-app-or-workload-with-an-existing-tenant)」を参照してください。

### サンプル シナリオ

Contoso は、従業員向けに 1 つのプライマリ運用テナントを運用しています。 このテナントは、従業員 ID、運用アプリケーションとリソース、および B2B コラボレーションを通じて招待する外部ゲストとビジネス パートナーをホストします。 Contoso は、この 1 つのテナント内でセキュリティ、コンプライアンス、運用の要件を満たし、管理をシンプルにし、コラボレーション エクスペリエンスをシームレスに保ちます。 次のシナリオ例の図は、Contoso のプライマリ運用テナント アーキテクチャを示しています。

[Image: シナリオ図の例は、単一の運用テナント アーキテクチャを示しています。]

### Administration

テナント全体の特権ロールには、 *グローバル管理者* と *特権ロール管理者が含まれます*。 管理委任を実装するには、次の機能を使用できます。 これらの機能は、特定のワークロードの特定のシナリオに適用されます。

- **[Microsoft Entra の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)**は、ユーザーが割り当てられたタスクを実行するために必要な、より限定された一連のアクセス許可を付与します。 たとえば、Exchange管理者ロールは、Exchange Onlineのすべての側面のみを管理できます。
- **[Microsoft Entraカスタム ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview)**を使用すると、特定のアクセス許可を作成し、1 つのアプリケーション スコープで制限付き所有者として、またはディレクトリ スコープ (すべてのアプリケーション) で制限付き管理者として割り当てることができます。
- **[Azureロールベースのアクセス制御](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/overview)** (RBAC) は、管理グループ、サブスクリプション、リソース グループ、およびリソース レベルの管理委任を使用してAzureリソースを構成するための階層スコープを提供します。
- **[サービス固有のロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/m365-workload-docs)**は、SharePoint、Intune、Purview などのMicrosoft サービスに固有のロールを提供します。
- **[管理単位は、](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units)**特定のMicrosoft Entraロールに対するアクセス許可を、定義する組織の一部に制限します。 たとえば、管理単位を使用して [、ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator) の役割を地域のサポート スペシャリストに委任し、サポートされているリージョンでのみユーザーを管理できるようにします。
- **[制限付き管理管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-restricted-management)** を使用すると、指定した特定のユーザー セット以外のユーザーによる変更から、エグゼクティブ ユーザー アカウントやテナント内の機密性の高いグループなどの特定のオブジェクトを保護できます。 この方法を使用すると、管理者からテナント レベルのロールの割り当てを削除しなくても、セキュリティまたはコンプライアンスの要件を満たすことができます。
- アプリケーションやグループなどのMicrosoft Entra オブジェクトの所有権と、SharePoint サイト コレクションやMicrosoft TeamsなどのMicrosoft 365 データの**所有権を委任**します。

管理制御を委任するだけでなく、これらのロールの誤用や侵害が組織全体に影響を与える可能性があるため、特権ロールを慎重に管理します。 [Microsoft Entra Privileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) (PIM) は、継続的な管理アクセスを制限し、小規模なスコープに分離できない影響の大きい運用に対するガバナンスを導入することで、このリスクを軽減するのに役立ちます。 特権ロールの明示的な期限付きアクティブ化が必要な場合、PIM では、テナント全体に管理アクションが本質的に適用される環境で、最小特権の原則がサポートされます。

Microsoft Entra テナント ガバナンスの[構成管理](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/configuration-management)を使用すると、構成基準の定義、テナントの誤差の監視、現在の設定のスナップショットの生成を行うことができます。 これらの定義は、Microsoft Entra、Exchange Online、Intune、Defender、Purview、Teams などのワークロード全体に適用できます。 テナント リソースの目的の状態を表現し、そのベースラインに対するセキュリティ構成を継続的に監視し、現在の状態を文書化できます。 このアプローチは、監査と回復性の要件を満たすのに役立ちます。

### 変更管理

運用テナントでの変更制御は、特にテナント全体の設定であり、セキュリティ グループなどの段階的なロールアウト メカニズムではパイロットできないため、高い懸念があります。 プライマリ運用テナントに直接適用すると、これらの変更には慎重に計画された "ビッグ バン" ロールアウトが必要な場合があります。 この増幅されたリスクは、徹底的なテスト、ロールバック計画、明確なコミュニケーションを必要とします。 たとえば、テナント間のアクセス設定、テナントの制限、ネットワークの場所、ブランド化の変更などがあります。 Exchange Onlineでは、グローバル設定はすべてのメールボックスに即座に適用されます。 設定には、承認済みドメイン、リモート ドメイン、POP/IMAP または基本認証の無効化、外部メールのタグ付け、組織全体のトランスポート ルールが含まれます。 Intune の場合、コンプライアンス ポリシーの既定値、デバイス クリーンアップ規則、登録制限などのテナント全体の構成は、登録されているすべてのデバイスに一度に影響します。 これらのコントロールには、段階的なロールアウトのためのスコープ メカニズムがありません。 そのため、依存関係を検証し、構成の変更前の状態を文書化し、実装前にロールバック戦略を準備します。 組織全体で利害関係者を調整します。 運用環境に適用する前にテナント全体の変更を検証するために、組織は別の [非運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-nonproduction)を使用できます。 データとスケールの違いは、慎重な運用ロールアウトの必要性を減らすことを意味しますが、完全には削除されません。

一部の組織では、プライマリ運用テナント内で非運用環境またはプレリリース アプリケーションをホストしてテストすることを選択します。 これにより、ビジネス ユーザーは、実際の運用データとワークフローに対するアプリケーションの動作やユーザー エクスペリエンスの変更を検証できます。 このアプローチでは、テストを簡略化し、環境の重複を減らすことができますが、その他の変更制御とリスクに関する考慮事項が導入されています。 最小限のMicrosoft Graphアクセス許可を要求するアプリケーションに適しています。 影響の大きいアクセス許可 (ディレクトリ全体の読み取りまたは書き込みアクセスなど) を要求するアプリケーションの場合は、可能な限り、分離された [非運用テナント](https://learn.microsoft.com/ja-jp/entra/architecture/tenant-estate-nonproduction) でのテストを検討してください。

### アカウントのライフサイクル

Microsoft Entraは、[人事システムから従業員のユーザー ID を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/what-is-hr-driven-provisioning)プロビジョニングし、アクセスの割り当てに基づいてサービスとしてのソフトウェア (SaaS) アプリケーションなどの[アプリケーションにプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/toc.json)します。 [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows) は、ユーザーのライフサイクルの結合者、ムーバー、および脱退フェーズ全体の ID 管理を自動化します。

テナント ユーザーから Business-to-Business (B2B) コラボレーションへの招待を受け入れるときに、外部ユーザーをテナントにオンボードできます。 外部ユーザーをオンボードするには、承認ワークフローなどの組み込みコントロールで [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview) アクセス パッケージを使用できます。 パッケージへのアクセスが失われると、エンタイトルメント管理でオンボードした外部ユーザー アカウントをディレクトリから自動的に削除できます。 外部アクセスをセキュリティで保護して管理するには、「[Microsoft Entra B2B コラボレーション展開の計画](https://docs.azure.cn/entra/architecture/secure-external-access-resources)」と「[エンタイトルメント管理における外部ユーザーのアクセスの管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users)」を参照してください。

### 資格情報の管理

Microsoft Entraのテナント全体のポリシーを使用して、従業員ユーザーが使用できる認証方法を管理します。 Windows Hello for Business、Authenticator アプリのパスキー、FIDO2 セキュリティ キー、証明書ベースの認証など、[フィッシングに強いパスワードレス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-plan-prerequisites-phishing-resistant-passwordless-authentication)認証を採用します。

外部ユーザーに多要素認証を適用する。 [B2B ユーザーの認証と条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/external-id/authentication-conditional-access) では、ゲストを対象とする条件付きアクセス ポリシーを作成する方法について説明します。 [テナント間アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration) を使用すると、特定のビジネス パートナー組織からの多要素認証 (MFA) メソッド要求を信頼できます。 それ以外の場合は、これらのユーザー アカウントを適用して、Microsoft Entra IDの他の MFA メソッドに登録します。

### Collaboration

従業員ユーザーは、同じテナントに所属している場合に、互いに最も合理化されたMicrosoft 365コラボレーション エクスペリエンスを得ることができます。 そのテナントで外部ユーザーもホストする場合は、同じディレクトリ内のゲストとして参加します。 管理者が外部ユーザーのMicrosoft TeamsやPower BIなどのMicrosoftクラウド サービスを有効にすると、従業員と外部ユーザーを同じチーム、共有チャネル、SharePoint サイト、アプリケーションに追加でき、テナントを切り替えることなく互いにアドホックに共同作業できます。

### ロールベースのリソースの割り当て

組み込みの制御を持つエンタイトルメント管理アクセス パッケージを持つユーザーにアクセス権を付与します。 制御には、時間制限付きのアプリケーション ロールの割り当て、職務の分離、外部コラボレーションのために特定の組織にスコープを設定する機能が含まれます。

### リスク管理

#### ブラスト半径

高い特権を持つユーザーまたはアプリケーション、または正しく構成されていないテナント レベルのポリシーの侵害は、そのテナントに関連付けられているすべてのユーザー、リソース、またはアプリケーションに影響を与える可能性があります。 特権のないユーザーやアプリケーションであっても、侵害された場合、広範なアクセス許可が付与されていると、広範囲に影響を及ぼす可能性があります（たとえば、*Everyone access* 設定の SharePoint サイト）。

Microsoft Cloudセキュリティ ソリューションは、エンドポイント、データ、アプリケーション、インフラストラクチャ、ネットワーク、セキュリティ運用を保護するための[ゼロ トラスト](https://learn.microsoft.com/ja-jp/security/zero-trust/)原則に従うことで、単一テナント内でこのリスクを軽減するために役立つ幅広い技術的制御セットを提供します。 具体的な推奨事項は次のとおりです。

- セキュリティ強化に関する記事「[Microsoft Entraの構成」](https://learn.microsoft.com/ja-jp/entra/fundamentals/configure-security)のすべてのコントロールを実装します。
- Microsoft Entra ID記事の[単一テナントでのセキュリティで保護されたリソース分離のコントロールを](https://learn.microsoft.com/ja-jp/entra/architecture/secure-single-tenant)使用します。
- [Microsoft Defender](https://learn.microsoft.com/ja-jp/defender/)で保護、検出、応答するためのポリシーを実装します。
- [Microsoft Purview Information Protection](https://learn.microsoft.com/ja-jp/purview/information-protection)による過剰な共有に対処します。
- [Microsoft Purview インサイダー リスク管理](https://learn.microsoft.com/ja-jp/purview/insider-risk-management)やデータ分類などのデータ セキュリティとガバナンスコントロールを実装します。 この軽減策は、外部ユーザーがテナントにアクセスできる場合に特に重要です。

#### 規制要件

Microsoft Entraでは、お客様が 1 つのテナント内の規制要件を満たすのに役立つ技術的な制御が提供されます。 各種規制に共通する考慮事項の 1 つは、保存時のデータ所在地です。 Microsoft Entraは、テナント作成時の[ディレクトリ データの保存場所を決定します](https://learn.microsoft.com/ja-jp/entra/fundamentals/data-residency)。 [Microsoft 365 Multi-Geo](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/microsoft-365-multi-geo)を使用すると、Exchange Online、SharePoint/OneDrive、Microsoft Teams、OneDriveなどのコア サービスのユーザー レベルでのスコープ内データの管理と保存が可能になります。Microsoft Copilot。

多くの場合、規制では、認証方法、アクセス ライフサイクル管理、レポートなどの ID およびアクセス管理 (IAM) コントロールが呼び出されます。 [Microsoft Entra ID で ID 標準を実装する](https://learn.microsoft.com/ja-jp/entra/standards/)では、特定の標準に関する詳細なガイダンスを提供します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/backup"} -->
## Microsoft Entra のバックアップと回復に関するドキュメント - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/backup
- Service: entra-id
- Article date: 2026-03-02
- Summary: Microsoft Entra のバックアップと回復を使用すると、ユーザー、グループ、アプリ、ポリシーなどの重要なディレクトリ オブジェクトを以前の既知の正常な状態に回復できます。

Microsoft Entra Backup and Recovery を使用して、ディレクトリ オブジェクトを以前の状態に戻すことで、偶発的な変更やセキュリティ侵害からテナントを保護する方法について説明します。

### バックアップと回復について

#### 概要

- [Microsoft Entra のバックアップと回復とは](https://learn.microsoft.com/ja-jp/entra/backup/overview)

#### 概念

- [バックアップ、差分レポート、復旧モデル](https://learn.microsoft.com/ja-jp/entra/backup/backup-difference-report-recovery-model)
- [サポートされているオブジェクトと回復可能なプロパティ](https://learn.microsoft.com/ja-jp/entra/backup/scope-supported-objects-limitations)
- [ソフト削除](https://learn.microsoft.com/ja-jp/entra/backup/soft-deletion)

### 操作方法ガイド

#### 攻略ガイド

- [使用可能なバックアップを表示する](https://learn.microsoft.com/ja-jp/entra/backup/view-available-backups)
- [差分レポートの作成と確認](https://learn.microsoft.com/ja-jp/entra/backup/create-review-difference-reports)
- [オブジェクトを回復する](https://learn.microsoft.com/ja-jp/entra/backup/recover-objects)
- [回復履歴を確認する](https://learn.microsoft.com/ja-jp/entra/backup/review-recovery-history)

### Troubleshooting

#### 攻略ガイド

- [バックアップと回復のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/backup/troubleshooting)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/backup/backup-difference-report-recovery-model"} -->
## Microsoft Entra Backup and Recovery のバックアップ、相違レポート、および復旧モデル - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/backup/backup-difference-report-recovery-model
- Service: entra-id
- Article date: 2026-08-25
- Summary: Microsoft Entra Backup and Recovery がバックアップを作成し、差分レポートを生成し、テナント オブジェクトを以前の状態に回復する方法を理解する

Microsoft Entra のバックアップと回復では、サポートされているテナント オブジェクトが自動的にバックアップされるため、変更を比較して以前の状態に回復できます。 サポートされるオブジェクトは次のとおりです。

- エージェントのユーザー アカウントを含むユーザー
- グループ
- エージェント ID ブループリントを含むアプリケーション
- サービスプリンシパル (エージェント ID およびエージェント ID ブループリントのプリンシパルを含む)
- 条件付きアクセス ポリシー
- 名前付き場所ポリシー
- 認証方法ポリシー
- 認可ポリシー
- 組織

サポートされている属性の完全な一覧については、「 [サポートされているオブジェクトと属性](https://learn.microsoft.com/ja-jp/entra/backup/scope-supported-objects-limitations)」を参照してください。

バックアップは 1 日に 1 回自動的に作成されます。 差分レポートを作成するか、復旧を開始するときに、保持されているバックアップから選択します。

### 相違レポート

差分レポートを作成して、テナントの現在の状態と選択したバックアップを比較します。 変更されたオブジェクトのみがレポートに表示され、変更された属性とリンクがレビュー用に表示されます。 フィルターを適用して、特定のオブジェクトの種類または特定のオブジェクトの変更を表示します。 フィルターを適用しない場合、変更されたすべてのオブジェクトが差分レポートに含まれます。

オンプレミスの Active Directory から同期されたユーザーとグループの変更は、変更されたオブジェクトの追跡に役立つ差分レポートに表示されます。 ただし、これらのオブジェクトの権限のソースはオンプレミスの Active Directory であるため、Backup と Recovery を使用してオンプレミス同期オブジェクトを復旧することはできません。

#### 初回差分レポートの生成

差分レポートを初めて作成するときに、差分計算が開始される前にバックアップ データの読み込み時に遅延が発生する可能性があります。 [差分レポート] セクションで **、** レポート生成の進行状況を確認します。

| テナント サイズ | 初回レポート生成の推定データ読み込み時間 |
| --- | --- |
| 1 ~ 50,000 個のオブジェクト | 最大 1 時間 |
| 50,000 から 300,000 オブジェクト | 最大 1 時間 30 分 |
| 300,000 から 1,000,000 オブジェクト | 最大 2 時間 |
| 1,000,000 を超えるオブジェクト | 最大 2 時間 30 分 |

同じバックアップに対して 2 回目に差分レポートを作成する場合、レポートにはデータ読み込み手順は必要ないため、完了時間が短縮されます。

違いの計算は、バックアップ状態と現在の状態の間で発生した変更によって異なります。 100,000 個のオブジェクトやリンクの変更の場合、完全なレポート生成が完了するまでに約 45 分かかることがあります。

注

時間の見積もりは概算であり、一般的な計画目的でのみ提供されます。 実際のパフォーマンスは、同時ネットワーク アクティビティ、リソースの可用性、テナント サイズによって大きく異なる場合があります。

### 復元

テナントを回復する場合は、フィルターを適用して、回復するオブジェクトを制御します。

- **オブジェクトの種類別**: ユーザー、グループ、アプリケーション、サービス プリンシパル、条件付きアクセス ポリシーなど、特定の種類のオブジェクトのみを回復します。
- **オブジェクト ID:** 特定のオブジェクトを回復するためのオブジェクトの種類とオブジェクト ID を指定します。
- **すべての変更**: 変更されたすべてのオブジェクトを、選択したバックアップでキャプチャされた状態に回復します。

回復のパフォーマンスは、復旧する変更の数によって異なります。 500,000 件の変更を回復するには、最大で 30 時間かかることがあります。

注

時間の見積もりは概算であり、一般的な計画目的でのみ提供されます。 実際のパフォーマンスは、同時ネットワーク アクティビティ、リソースの可用性、テナント サイズによって大きく異なる場合があります。

Important

差分レポートや回復ジョブなど、一度に実行できるジョブは 1 つだけです。 たとえば、テナントで差分レポートが実行されている場合、復旧ジョブを開始することはできません。 現在のジョブが完了するまで待ってから、新しいジョブを開始します。

### 復旧モデル

バックアップ状態からの変更の種類によって、復旧アクションが決まります。

| バックアップ以降の変更 | 回復アクション |
| --- | --- |
| オブジェクトが追加されました | バックアップと回復 オブジェクトの論理削除 |
| オブジェクトが更新されました | バックアップと回復により、オブジェクトがバックアップ値に更新されます |
| オブジェクトがソフト削除されました | バックアップと回復によってオブジェクトが復元される |
| オブジェクトが復元されました | バックアップと回復 オブジェクトの論理削除 |

バックアップと回復では、新しいオブジェクトが作成されたり、テナントからオブジェクトがハード削除されたりすることはありません。

Warnung

ハード削除されたオブジェクトは回復できません。 不要なハード削除を防ぐために [、保護されたアクション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-overview) を構成します。

論理的に削除されたユーザー、Microsoft 365 グループ、クラウド セキュリティ グループ、アプリケーション登録、サービス プリンシパルの場合は、30 日間のリテンション期間内に[論理的な削除](https://learn.microsoft.com/ja-jp/entra/backup/soft-deletion)を使用することもできます。

権限のソースはオンプレミスの Active Directory であるため、オンプレミス同期オブジェクトはバックアップと回復を通じて復旧できません。 代わりに、オンプレミスの Active Directory でこれらのオブジェクトを回復します。 同期されたオブジェクトに対する変更は、引き続き相違レポートに表示されます。

Microsoft Entra のバックアップと回復は、従業員テナントでのみ使用できます。 Microsoft Entra 外部 ID テナントと Azure AD B2C テナントはサポートされていません。

Microsoft Entraバックアップと回復は、Microsoft Entraディレクトリ オブジェクトをバックアップおよび回復します。 メールボックス、OneDrive、SharePoint サイトなどのMicrosoft 365リソース、Azure リソースは、Microsoft Entraバックアップと回復ではバックアップされません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/backup/create-review-difference-reports"} -->
## Microsoft Entra Backup and Recovery で差分レポートを作成および確認する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/backup/create-review-difference-reports
- Service: entra-id
- Article date: 2026-03-02
- Summary: Microsoft Entra Backup and Recovery でテナントとバックアップを比較する差分レポートを作成および確認する方法について説明します

差分レポートでは、テナントの現在の状態と選択したバックアップが比較され、変更内容が強調表示されます。

差分レポートには、選択したバックアップが作成された後 **に作成、変更、論理的に削除、または復元** されたオブジェクトと、次に関する詳細が表示されます。

- 属性の変更: バックアップと現在のテナントの状態が異なる値を持つオブジェクトのプロパティ。
- リンクの変更: グループ メンバーシップなど、他のオブジェクトとのオブジェクトのリレーションシップに対する変更。

重要な詳細:

- 差分レポート ID は、比較ジョブを識別します。
- 同じバックアップから複数の差分レポートを作成しますが、一度に実行できるレポートは 1 つだけです。
- 差分レポートは、完了後最大 7 日間保持されます。

ヒント

テナントの変更を確認して理解できるように、復旧を開始する前に差分レポートを作成します。

### 前提条件

違いレポートを確認するには、少なくとも **Microsoft Entra Backup 閲覧者** ロールが必要です。 差分レポートを確認して作成するには、 **Microsoft Entra Backup 管理者** ロールが必要です。 **グローバル管理者**ロールには、これらのアクセス許可も含まれます。

テナントは、[P1 または P2 ライセンスMicrosoft Entra ID](https://learn.microsoft.com/ja-jp/entra/backup/overview#prerequisites)含め、**バックアップと回復の前提条件**を満たしている必要があります。

### 相違レポートの範囲を指定する

差分レポートを作成する場合は、比較に含めるオブジェクトを制御する範囲を指定します。

- **サポートされているすべてのオブジェクト**: テナントでサポートされているすべてのオブジェクトの種類が含まれます。
- **オブジェクトの種類別**: 条件付きアクセス ポリシー、サービス プリンシパル、グループなど、選択したオブジェクトの種類のみが含まれます。
- **オブジェクト ID**: 指定されたオブジェクトの種類を持つオブジェクト ID ごとの具体的なオブジェクトのみが含まれます。 サポートされているオブジェクトの種類に対して、最大 100 個のオブジェクト ID を指定します。

レポートの作成時にスコープを設定します。 後で変更することはできません。

### 差分レポートを作成する

1. [Microsoft Entra 管理センターに](https://entra.microsoft.com)**少なくとも Microsoft Entra Backup 管理者として** サインインします。
2. **バックアップと回復**&gt;バックアップに移動**します**。 一覧からバックアップを選択し、[ **差分レポートの作成**] を選択します。

    [Image: ツール バーの [差分レポートの作成] ボタンを使用して使用可能なバックアップを示す [バックアップ] ページのスクリーンショット。]

    バックアップは 1 日に 1 回自動的に作成されます。 選択対象として表示されるのは、保持されているバックアップのみです。 レポートでは、選択したバックアップが現在のテナントの状態と比較されます。
3. (省略可能)フィルターを適用して、レポートに含まれるオブジェクトのスコープを制限します。 次のオプションのいずれかを選択します。

    - **以前の状態のすべてのオブジェクトを含める**: テナントでサポートされているすべてのオブジェクトを比較します。

        [Image: [以前の状態のすべてのオブジェクトを含める] オプションが選択されている [差分レポートの作成] ダイアログのスクリーンショット。]
    - **特定の種類のオブジェクトのみを含める**: ユーザーやグループなど、選択したオブジェクトの種類にレポートを制限します。

        [Image: [特定の種類のオブジェクトのみを含める] オプションが選択されている [差分レポートの作成] ダイアログのスクリーンショット。]
    - **特定のオブジェクトのみを ID で含める**: レポートをオブジェクト ID によって特定のオブジェクトに制限します。 さまざまな種類のオブジェクトに対して最大 100 個のオブジェクト ID を入力します。

        [Image: [ID で特定のオブジェクトのみを含める] が選択されている [差分レポートの作成] ダイアログのスクリーンショット。]
4. [ **差分レポートの作成** ] を選択して、レポートを開始します。

    [Image: [差分レポートの作成] ボタンの上にカーソルを置いた [差分レポートの作成] ダイアログのスクリーンショット。レポートを送信する準備が整いました。]

### 差分レポートを取り消す

処理中の差分レポートをキャンセルします。 取り消されたレポートには、部分的な結果は表示されません。 取り消しは、誤ってレポートを開始した場合に便利です。

1. **バックアップと回復**&gt;**差異レポート**に移動します。
2. 進行中のレポートを選択し、ツール バーの **[キャンセル** ] を選択します。

    [Image: [差分レポート] ページのスクリーンショット。1 つのレポートが進行中で、完了した 2 つのレポートが表示され、ツールバーに [キャンセル] ボタンが表示されています。]

### 差異レポートの状態を確認する

差分レポートは、作成および処理されると、次の状態に移動します。

| 地位 | 説明 |
| --- | --- |
| **データの読み込み** | 現在のテナントの状態と比較するために、選択したバックアップからデータが読み込まれます。 以前に差分レポートまたは復旧にバックアップを使用していた場合、この手順はすぐに完了する可能性があります。 |
| **処理中** | システムは、バックアップと現在のテナントの状態の違いを計算します。 期間は、オブジェクトの数とレポートのスコープによって異なります。 |
| **完了** | 差分レポートの処理が完了し、確認の準備が整いました。 |
| **失敗しました** | エラーのため、差分レポートを生成できませんでした。 |
| **取り消されました** | 差分レポートは完了前に取り消されました。 |

### 差分レポートを確認する

差分レポートには、レポートが作成された時点の現在のテナントの状態が反映され、自動的には更新されません。 復旧前にさらに変更が発生する場合は、新しい差分レポートを作成します。

1. **バックアップと回復**&gt;**差異レポート**に移動します。 一覧には、各レポートの状態、バックアップの詳細 (ID、タイムスタンプ、可用性)、スコープ条件が表示されます。 また、作成と完了の時間、およびレポート内のオブジェクトとリンクの数も表示されます。

    [Image: 3 つの差分レポートのレポートの状態、バックアップ タイムスタンプ、およびフィルター処理の詳細を示す [差分レポート] リスト ページのスクリーンショット。]
2. 完了した差分レポートを選択して、その詳細を表示します。

    [Image: バックアップとオブジェクトの詳細を含む 3 つの完了したレポートを示す [差分レポート] リスト ページのスクリーンショット。]
3. 差分レポートの内容を確認します。 レポートには、変更された各オブジェクトと、復旧中に適用される復旧アクションが一覧表示されます。

    [Image: 回復アクションと変更された属性を持つユーザー オブジェクトを示す差分レポートの詳細ページのスクリーンショット。]

    レポートには、各オブジェクトの次の情報が含まれます。

    - **変更された属性**: 属性の違いを表示するには、[ **変更された属性** ] 列でカウントを選択します。 [ **レポート値]** 列には、差分レポートの作成時にキャプチャされた値が表示されます。 **[バックアップ値**] 列には、選択したバックアップでキャプチャされた値が表示されます。

        [Image: 現在の状態とバックアップの値の違いを示す [変更された属性の表示] パネルのスクリーンショット。]
    - **変更されたリンク**: [ **変更されたリンク** ] 列の数を選択して、リレーションシップの違いを表示します。 [ **レポートの状態** ] 列には、差分レポートの作成時にキャプチャされたリレーションシップが表示されます。 **[バックアップ状態**] 列には、選択したバックアップでキャプチャされたリレーションシップが表示されます。 [ **回復アクション** ] 列は、バックアップ状態を復元するために実行されたアクションを示します。

        [Image: グループ オブジェクトの [変更されたリンクの表示] パネルのスクリーンショット。回復によって元に戻されるグループ メンバーシップの変更が示されています。]
    - **回復アクション**: オブジェクトの復旧時に適用されるアクション。 使用可能な値:

        - **更新**: 既存のオブジェクトの変更された属性、リンク、またはその両方を元に戻します。
        - **復元**: 論理的に削除されたオブジェクトを復元します。
        - **論理的な削除**: バックアップ後に作成されたオブジェクトを論理的に削除します。

        [Image: [回復アクション] 列が強調表示されている差分レポートの詳細ページのスクリーンショット。各オブジェクトの更新アクションと復元アクションが表示されています。]

注

ハード削除されたオブジェクトと読み取り専用プロパティは、差分レポートには表示されません。 オンプレミスのディレクトリから同期されたオブジェクトは、異なるレポートに表示される可能性がありますが、回復できません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/backup/overview"} -->
## Microsoft Entra のバックアップと回復の概要 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/backup/overview
- Service: entra-id
- Article date: 2026-03-02
- Summary: Microsoft Entra Backup and Recovery を使用して、悪意のある攻撃やテナント オブジェクトへの偶発的な変更から回復する方法について説明します

Microsoft Entra のバックアップと回復は、重要な Microsoft Entra ディレクトリ オブジェクトを、偶発的な変更やセキュリティ侵害の後、以前の既知の正常な状態に回復できる組み込みのバックアップと回復ソリューションです。 サポートされるオブジェクトには、ユーザー、グループ、アプリ、サービス プリンシパル、条件付きアクセス ポリシー、名前付き場所、認証方法ポリシー、承認ポリシー (選択したプロパティ) が含まれます。 また、このソリューションは、異なる型と特性を持つユーザー オブジェクトとサービス プリンシパル オブジェクトで構成されるため、エージェント ID もサポートします。

### バックアップのしくみ

Microsoft Entraバックアップと復旧では、サポートされているオブジェクトのバックアップが 1 日に 1 回自動的に実行され、最大 7 日間のバックアップ履歴が保持されます。 このソリューションは、テナントを生産的で安全な状態に復元するのに役立ちます。 Microsoft では、より多くのディレクトリ オブジェクトとより多くの属性をサポートするために、ソリューションを定期的に改善および拡張しています。

Microsoft はバックアップを自動的に作成し、十分なアクセス許可を持つ管理者がバックアップを使用できるようにします。 管理者特権が最も高い場合でも、サインインしているユーザーまたはアプリケーションは、テナントのバックアップを無効にしたり、削除したり、変更したりすることはできません。 バックアップ データは、テナントの作成時に決定される [Microsoft Entra テナントと同じ地理的な場所](https://learn.microsoft.com/ja-jp/entra/fundamentals/data-residency)に安全に存在します。

### 主な機能

Microsoft Entra のバックアップと回復では、次のことができます。

- **使用可能なバックアップを表示**する: Microsoft Entra テナントで使用可能なバックアップの一覧を表示します。
- **差分レポートを作成**する: オブジェクトを以前の状態に復旧する前に、テナントの現在の状態をバックアップと比較し、変更された属性とリンクを確認します。
- **オブジェクトの回復**: サポートされているすべてのオブジェクト、選択したオブジェクトの種類、または特定のオブジェクト ID を回復します。
- **復旧履歴を確認**する: テナントの完了した復旧操作と進行中の復旧操作を表示します。

ヒント

適切なバックアップに確実に復旧するには、常に差分レポートを実行し、変更を確認してから、回復する内容を決定します。 回復する時間は、主に復旧ジョブの変更の数によって異なります。

### 概要

開始するには、 [Microsoft Entra 管理センター](https://entra.microsoft.com) を参照し、左側のナビゲーション ウィンドウで **[バックアップと回復** ] を選択します。 次のページを使用できます。

- **概要**: バックアップと回復機能の概要を表示します。
- **バックアップ**: 過去 7 日間の使用可能なバックアップを参照します。
- **相違レポート**: バックアップと現在のテナントの状態を比較するレポートを作成して確認します。
- **復旧履歴**: テナントの完了した復旧操作と進行中の復旧操作を表示します。

### 前提条件

Microsoft Entra Backup and Recovery を使用するには、テナントが次の要件を満たしている必要があります。

- テナントは **従業員テナント**です。 外部 ID と Azure AD B2C テナントはサポートされていません。
- テナントには **、Microsoft Entra ID P1 または P2** ライセンスがあります。
- 次のいずれかの役割でサインインしています。
    - **Microsoft Entra バックアップ リーダー**: バックアップを表示したり、バックアップ状態と現在の状態の間で変更されたオブジェクトの比較を表示したり、復旧履歴を確認したりできます。
    - **Microsoft Entra Backup Administrator: Microsoft Entra Backup** Reader のすべてのアクセス許可を持ちます。また、変更されたオブジェクトの差分レポートを開始し、回復をトリガーすることもできます。 Microsoft Entra Backup Administrator のすべてのアクセス許可は、グローバル管理者ロールに含まれています。

### ハイブリッド ID とより広範な回復可能性

Microsoft Entra ID でハイブリッド ID を使用する組織では、Active Directory Domain Services (AD DS) から同期されたオブジェクトに対する変更を識別するための差分レポートを作成できます。 グループなどの特定のオブジェクトの種類では、AD DS からクラウドに権限のソースを移動できます。 これにより、変換されたオブジェクトに対して Microsoft Entra のバックアップと回復のすべての機能を使用できるようになります。 代替ソリューションを使用して、AD DS で管理されているオブジェクトをバックアップおよび回復します。

論理的に削除されたユーザー、Microsoft 365 グループ、クラウド セキュリティ グループ、アプリケーションの登録、サービス プリンシパルは、30 日間復元できます。 論理的な削除とバックアップと回復の関係の詳細については、「[Microsoft Entraバックアップと回復」の「論理的な削除](https://learn.microsoft.com/ja-jp/entra/backup/soft-deletion)」を参照してください。 バックアップと回復は、保持されているバックアップからサポートされているプロパティとリンクを復元し、論理的な削除の回復を補完します。

Microsoft Entra のバックアップと回復では、ハード削除されたオブジェクトの回復または再作成はサポートされていません。

Microsoft Entra のバックアップと回復は、組織の回復力を高めるのに役立つ回復性に対するより広範なアプローチの一部として使用します。 制限事項、ハイブリッド シナリオ、回復可能性のベスト プラクティスの詳細については、「 [サポートされているオブジェクトと回復可能なプロパティ](https://learn.microsoft.com/ja-jp/entra/backup/scope-supported-objects-limitations)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/backup/recover-applications"} -->
## Microsoft Entra のバックアップと回復を使用してアプリケーション シークレットを回復する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/backup/recover-applications
- Service: entra-id
- Article date: 2026-03-09
- Summary: Microsoft Entra Backup and Recovery を使用して、偶発的または悪意のある変更の後にアプリケーション シークレットと資格情報を回復する方法について説明します

この記事では、Microsoft Entra Backup and Recovery を使用して、偶発的または悪意のある変更の後にアプリケーション シークレットを復元する方法について説明します。

バックアップは 1 日に 1 回自動的に作成されます。 アプリケーションとサービス プリンシパルの復元ポイントは、保持されるバックアップに制限されます。

### 前提条件

テナントは、[P1 または P2 ライセンスMicrosoft Entra ID](https://learn.microsoft.com/ja-jp/entra/backup/overview#prerequisites)含め、**バックアップと回復の前提条件**を満たしている必要があります。 アプリケーション オブジェクトとサービス プリンシパルを回復するには、**Microsoft Entraバックアップ管理者**ロールが必要です。

### 回復の準備

アプリケーションのディザスター リカバリー計画の一環として、アプリケーション シークレットとシークレットローテーションを管理するための現在のプロセスを確認します。 アプリケーション シークレットを管理するためのベスト プラクティスを使用すると、偶発的または悪意のある編集からの回復が容易になります。 この記事では、アプリケーション シークレットを管理するために Azure Key Vault または別のセキュリティで保護されたソリューションを使用していることを前提としています。 詳細については、「シークレットを [保護するためのベスト プラクティス」を](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/secrets-best-practices)参照してください。

アプリケーション シークレットを超えるアプリケーションの一部のプロパティは、バックアップと回復に含まれません。 偶発的な編集からアプリケーションを完全に回復するために、保存して手動で再適用する必要がある可能性があるプロパティについては 、「付録」 を参照してください。

アプリケーションがハード削除されている場合は、バックアップと回復を使用してアプリケーションを回復できないため、再作成する必要があります。 アプリケーションはソフト削除された状態にある30日後、またはハード削除APIが直接呼び出されると、完全に削除されます。

アプリケーションの早期削除を防ぐために、 [保護されたアクション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-overview#deletion-of-directory-objects)を使用してオブジェクトをハード削除する機能を高い特権を持つ管理者のみに制限します。 アプリケーションを再作成する必要がある場合は、登録されているすべてのアプリケーションとカスタマイズされた設定の記録を保持します。 アプリケーションの削除の詳細については、「アプリケーション [の削除と回復に関する FAQ](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-recover-faq)」を参照してください。

アプリケーション、サービス プリンシパル、アプリケーション シークレットに必要な復旧手順を文書化して検証します。 非運用環境またはテスト アプリケーションを使用して、編集または論理的な削除後にアプリケーションとそのシークレットを復元するために必要なプロセスを確実に理解します。

この記事では、誤ってまたは悪意を持って変更されたアプリケーションからの回復について説明します。 変更が悪意のある変更であったかどうかを判断できない場合は、その変更が悪意のある変更であったと見なします。 このアプローチは、ゼロ トラストの原則に沿っています。 悪意のある変更が発生した場合は、復旧計画の一部として脅威を含め、悪意のあるアクターを排除することが重要です。 これらの手順については、この記事では説明しません。

### 変更の原因と影響を特定する

最初の手順は、アプリケーションに加えられた変更が偶発的または悪意のあるものかどうかを判断することです。 偶発的な変更は、多くの場合、自動化、スクリプト、および誤って実行された直接の管理アクションに起因します。 悪意のある変更は、不適切なアクターによって引き起こされる変更です。 変更の原因を特定できない場合は、変更が悪意があると想定してください。

変更の原因を特定したら、アプリケーションのシークレットが影響を受けたかどうかを検証します。 監査ログでアプリケーション シークレットに対する変更を見つけます。 アプリケーション シークレットが変更または更新されたことを示すイベントを探します。

差分レポートを使用して、選択したバックアップを、影響を受けるアプリケーションとサービス プリンシパルの現在のテナント状態と復旧前に比較します。 差分レポートは、復元する対象を選択する前に、変更された属性とリンクを特定するのに役立ちます。

変更の性質とシークレットが影響を受けたかどうかによって、アプリケーションの復旧に最適なパスが決まります。 アプリケーション、サービス プリンシパル、またはユーザーが論理的な削除から回復されるたびに、シークレットは削除アクションが発生した時点の状態に回復されます。

### シークレットに影響がなかった場合に、誤って行った変更を元に戻す

これには、アプリケーションが編集または論理的に削除されたが、アプリケーション上のシークレットが編集されなかったシナリオが含まれます。

バックアップと回復を使用して、影響を受けるアプリケーションとサービス プリンシパルに復旧のスコープを設定し、変更が発生する前の特定の時点に復旧します。 この時点で、アプリケーションは既存のシークレットを使用して機能できる必要があります。 アプリケーションを削除した場合、復旧では、削除時にシークレットが状態に復元されます。

チームが Azure Key Vault を使用している場合は、次の手順を使用してシークレットが正しく機能していることを検証します。

1. [Azure portal](https://portal.azure.com) にサインインする
2. Azure Key Vault サービスに移動します。
3. このアプリケーションで構成した Key Vault を見つけます。
4. **[シークレット**] を参照し、シークレットを選択して現在のバージョンを表示します。

    [Image: [有効] 状態のシークレットを示す [Azure Key Vault シークレット] ページのスクリーンショット。]

    [Image: 現在のバージョンが有効で、以前のバージョンが無効になっている Key Vault シークレットのバージョン ページのスクリーンショット。]
5. [ **シークレット値の表示]** を選択し、シークレットの最初の 3 文字と、Microsoft Entra 管理センターのアプリケーション登録で構成されたシークレット値を比較します。 それらが一致する場合、シークレットは変更されておらず、引き続き期待どおりに機能します。

    [Image: Key Vault シークレット バージョンの詳細ページのスクリーンショット。比較のためにシークレット値が表示されています。]

    [Image: Key Vault と比較するためのシークレット値を示す [証明書とシークレット] ページのスクリーンショット。]

注

また、アプリケーションを以前の状態に完全に復元するために、バックアップと回復でサポートされていないアプリケーションのプロパティを確認する必要がある場合もあります。 サポートされていないプロパティのアドレス指定については、 付録 を参照してください。

### シークレットが変更または削除されたときの偶発的な変更を回復する

バックアップと回復を使用して、影響を受けるアプリケーションとサービス プリンシパルに復旧のスコープを設定し、変更が発生する前の特定の時点に復旧します。

チームが Azure Key Vault を使用している場合は、アプリケーションのシークレットをロールする必要があります。 悪意のある変更が原因でアプリケーションを回復 するための手順に従って、シークレットを再設定します。

チームがシークレットのバックアップに別の製品を使用している場合は、独自のプロセスを使用してシークレットを回復できます。 アプリケーション オブジェクト ID は変更されないため、標準プロセスを使用してシークレットを検証またはローリングし、アプリケーションを更新できる必要があります。

アプリケーションを以前の状態に完全に復元するには、バックアップと回復でサポートされていないアプリケーションのプロパティを確認する必要がある場合があります。 サポートされていないプロパティについては、 付録を参照してください。

### 悪意のある変更が原因でアプリケーションを回復する

まず、脅威を封じ込め、悪いアクターを排除するために積極的に取り組んでいることを確認します。 既存のシークレットが変更されていない場合は使用できますが、シークレットをローテーションする方が安全な方法です。 シークレットが影響を受けず、シークレットをすぐにロールしたくない場合は、シークレットを変更しないで偶発的な変更と同じプロセスに従うことができます。 しかし、これはお勧めしません。

チームで Azure Key Vault を使用している場合は、次の手順を使用してシークレットをロールできます。

**アプリケーションの新しいシークレットを作成します。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. アプリケーション登録内のアプリケーションに移動します。
3. **[証明書とシークレット**] に移動して、新しいシークレットを作成します。

    [Image: [クライアント シークレットの追加] パネルのスクリーンショット。説明フィールドと有効期限フィールドがあります。]
4. シークレットの値をコピーして、Azure Key Vault に新しいバージョンのシークレットを作成します。

    [Image: 新しく作成されたシークレットを含む 2 つのクライアント シークレットを示す [証明書とシークレット] ページのスクリーンショット。]

**Key Vault に新しいバージョンのシークレットを追加して、新しいシークレットを管理します。**

1. [Azure portal](https://portal.azure.com) にサインインする
2. Azure Key Vault サービスに移動します。
3. このアプリケーションで構成した Key Vault を見つけます。
4. **[シークレット**] を参照し、シークレットを選択して現在のバージョンを表示します。
5. [ **新しいバージョン] を** 選択し、アプリケーションから新しいシークレット値を指定して新しいシークレットを作成します。 有効になっていることを確認します。

    [Image: Key Vault の [シークレットの作成] フォームのスクリーンショット。シークレット値フィールドが設定され、[有効] トグルが [はい] に設定されています。]
6. このシークレットの以前のバージョンに戻り、無効にします。

    [Image: 古いシークレット バージョンの [無効] オプションを示す [Key Vault シークレットのバージョン] ページのスクリーンショット。]
7. 新しい **シークレット識別子** の値をコピーし、必要に応じてアプリケーションのコードで更新します。

    [Image: コピー用にシークレット識別子 URL が強調表示されている Key Vault シークレット バージョンの詳細ページのスクリーンショット。]

チームがシークレットのバックアップに別の製品を使用している場合は、独自のプロセスを使用してシークレットをロールします。 アプリケーション オブジェクト ID は変更されないため、標準プロセスを使用してシークレットをローリングし、影響を受けるアプリケーションを更新できる必要があります。

この時点で、アプリケーションはロールされたシークレットを使用して機能できる必要があります。

アプリケーションを以前の状態に完全に復元するには、バックアップと回復でサポートされていないアプリケーションのプロパティを確認する必要がある場合があります。 サポートされていないプロパティについては、 付録を参照してください。

### ハード削除されたアプリケーションから回復する

アプリケーションがハード削除された場合、バックアップと回復では復旧できません。 アプリケーションを再作成する必要があるため、格納されているシークレットを再利用することはできません。

Azure Key Vault を使用する場合は、新しいアプリケーションと新しいシークレットを指定する必要があります。

1. [Azure portal](https://portal.azure.com) にサインインする
2. Azure Key Vault サービスに移動します。
3. このアプリケーションで構成した Key Vault を見つけます。
4. **[シークレット**] を参照し、シークレットを選択して現在のバージョンを表示します。
5. [ **新しいバージョン] を** 選択し、アプリケーションから新しいシークレット値を指定して新しいシークレットを作成します。 有効になっていることを確認します。
6. このシークレットの以前のバージョンに戻り、無効にします。
7. 新しい **シークレット識別子** の値をコピーし、必要に応じてアプリケーションで更新します。

他のソリューションでは、新しいシークレットを生成してアプリケーションに適用するか、シークレット管理システムのオブジェクト ID を更新してから、新しく作成したアプリケーションにシークレットを再追加する必要があります。

### 付録

付録には、バックアップと回復が自動的に復元しないアプリケーションとサービス プリンシパルのプロパティが一覧表示されます。

#### バックアップと回復でサポートされていないアプリケーションとサービス プリンシパルのプロパティ

すべてのアプリケーションとサービス プリンシパルのプロパティがバックアップと回復でサポートされているわけではありません。 サポートされているオブジェクトと回復可能なプロパティで、サポートされている [アプリケーションのプロパティ](https://learn.microsoft.com/ja-jp/entra/backup/scope-supported-objects-limitations#application) と [サービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/backup/scope-supported-objects-limitations#service-principal) のプロパティを確認します。 サポート対象として一覧表示されていないアプリケーション設定は、保存して手動で再適用する必要がある場合があります。

リダイレクト URI、サポートされているアカウントの種類、割り当てられたアクセス許可またはロール、公開されている API プロパティなどの主要なアプリケーション設定を確認して、復旧後にアプリケーションが期待どおりに機能することを確認します。

- アプリケーションにアタッチされている [クレーム ポリシー](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-claims-customization) と [ホーム領域検出ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-authentication-for-federated-users-portal?pivots=ms-powershell#create-an-hrd-policy-using-microsoft-graph-powershell) を復元できません。 ポリシーをもう一度構成する必要がある場合があります。
- マネージド ID がアプリケーションにアタッチされている場合、それらは復元されません。
- アプリケーション プロキシ用にアプリケーションを構成した場合、アプリケーション プロキシの構成を復元できません。 アプリケーション プロキシ設定を再作成するには、 **onPremisesPublishing** のエンドポイントを使用する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/backup/recover-objects"} -->
## Microsoft Entra のバックアップと回復を使用してオブジェクトを回復する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/backup/recover-objects
- Service: entra-id
- Article date: 2026-03-02
- Summary: Microsoft Entra Backup and Recovery を使用して、異なるレポートまたはバックアップからオブジェクトを以前の状態に回復する方法について説明します

Microsoft Entra Backup and Recovery を使用して、オブジェクトを以前の既知の正常な状態に回復する方法について説明します。 回復には、サポートされているオブジェクトと属性の復元、論理的な削除、更新が含まれます。

重要な詳細:

- 復旧 ID は、復旧ジョブを識別します。
- バックアップは 1 日に 1 回自動的に作成されます。 復旧と差分レポートの生成には、保持されたバックアップのみを使用できます。
- 一度に実行される復旧は 1 つだけです。 別のジョブ (回復ジョブまたは差分レポート) が既に実行されている場合は、完了するまで待機するか、取り消してから新しいジョブを開始する必要があります。
- **復旧履歴** は、復旧の完了日から 7 日間、復旧の詳細を保持します。
- 監査ログには、すべての復旧アクションが記録されます。

### 前提条件

テナントは、[P1 または P2 ライセンスMicrosoft Entra ID](https://learn.microsoft.com/ja-jp/entra/backup/overview#prerequisites)含め、**バックアップと回復の前提条件**を満たしている必要があります。 オブジェクトを回復するには、 **Microsoft Entra Backup 管理者** ロールが必要です。

### 差分レポートから復旧する

この方法は、差分レポートを既に作成し、変更を確認した場合に使用します。

1. 少なくとも [Microsoft Entra Backup 管理者](https://entra.microsoft.com)として**Microsoft Entra 管理センター**にサインインします。
2. **バックアップと回復**&gt;**差異レポート**に移動します。 完了した差分レポートを選択します。

    [Image: 3 つの完了したレポートと使用可能なバックアップを示す [差分レポート] ページのスクリーンショット。]

    差分レポートでは、選択したバックアップと現在のテナントの状態が比較され、変更された属性とリンクが表示されます。
3. 差分レポートに一覧表示されているオブジェクトを調べた後、[ **回復** ] を選択して回復を開始します。

    [Image: 回復するオブジェクトの一覧を示す [差分からの回復] レポート ダイアログのスクリーンショット。下部に [回復] ボタンが表示されています。]

    スコープ フィルターを使用して作成された差分レポートから回復する場合、回復では同じスコープが自動的に使用され、追加のフィルター処理は許可されません。 別のオブジェクト セットを回復するには、バックアップ ページから開始し、差分レポートを実行して変更を確認します。
4. (省略可能)完全復旧ジョブを開始せずに 1 つの優先度の高いオブジェクトを回復するには、オブジェクトの変更された属性パネルを開き、[ **このオブジェクトの回復**] を選択します。

    [Image: ユーザー オブジェクトの [変更された属性の表示] パネルのスクリーンショット。特定のオブジェクトの回復を求める確認ダイアログが表示されています。]

    差分レポートは、ポイントインタイム比較です。 レポートの作成後にテナントでオブジェクトが変更された場合、それらの変更はレポートに反映されません。 差分レポートから復旧する場合、復旧はテナントの最新の状態に適用されます。 これにより、差分レポートに表示される変更とは異なる変更セットが発生する可能性があります。

### バックアップから直接回復する

差分レポートを作成すると、復旧前に変更をプレビューできます。 この手順をスキップするには、バックアップから直接回復します。 [バックアップ] ページには、保持されている **バックアップ** のみが表示されます。

1. 少なくとも [Microsoft Entra Backup 管理者](https://entra.microsoft.com)として**Microsoft Entra 管理センター**にサインインします。
2. **バックアップと回復**&gt;バックアップに移動**します**。 バックアップを選択し、[ **バックアップの回復**] を選択します。

    [Image: バックアップが選択され、ツール バーに [バックアップの回復] ボタンが表示されている [バックアップ] ページのスクリーンショット。]
3. (省略可能)スコープ フィルターを適用して、回復に含まれるオブジェクトを制限します。 次のオプションのいずれかを選択します。

    - **以前の状態のすべてのオブジェクトを回復**する: テナントでサポートされているすべてのオブジェクトを回復します。

        [Image: [以前の状態のすべてのオブジェクトを回復する] オプションが選択され、[回復] ボタンのカーソルが表示された [バックアップの回復] ダイアログのスクリーンショット。]
    - **特定の種類のオブジェクトのみを回復**する: ユーザーや条件付きアクセス ポリシーなど、選択したオブジェクトの種類に回復を制限します。

        [Image: [バックアップの回復] ダイアログのスクリーンショット。[特定の種類のオブジェクトのみを回復する] が選択され、種類のオプションが表示されています。]
    - **特定のオブジェクトのみを ID で回復**する: オブジェクト ID によって特定のオブジェクトへの回復を制限します。 さまざまな種類のオブジェクトに対して最大 100 個のオブジェクト ID を入力します。

        [Image: [バックアップの回復] ダイアログのスクリーンショット。[ID で特定のオブジェクトのみを回復する] が選択され、オブジェクト ID エントリが表示されています。]
4. **[回復**] を選択して回復ジョブを開始します。

Warnung

復旧アクションはテナントに直接適用され、自動的に元に戻すことはできません。 復旧を開始する前に、差分レポートの変更を確認します。 回復ジョブは、監査ログのすべての変更を記録します。

### 回復を取り消す

実行中に回復ジョブを取り消します。 取り消しの前に完了した復旧アクションは引き続き有効です。

1. **バックアップと回復**&gt;**回復履歴に**移動します。
2. 進行中の復旧ジョブを選択し、[キャンセル] を選択 **します**。

    [Image: 完了した復旧ジョブと進行中の復旧ジョブを示す [回復履歴] ページのスクリーンショット。ツールバーに [キャンセル] ボタンが表示されています。]

注

- 論理的に削除されたユーザー、Microsoft 365 グループ、クラウド セキュリティ グループ、アプリケーションの登録、サービス プリンシパルは、30 日間復元できます。 詳細については、「[Microsoft Entra バックアップと回復の論理的な削除](https://learn.microsoft.com/ja-jp/entra/backup/soft-deletion)」を参照してください。 バックアップと回復では、保持されているバックアップからサポートされているプロパティとリンクが復元されます。
- ハード削除されたオブジェクトは回復できません。 保護されたアクションを使用して、テナント内の不要なハード削除を防ぎます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/backup/recover-user-secrets"} -->
## Microsoft Entra Backup and Recovery を使用してユーザー認証方法を回復する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/backup/recover-user-secrets
- Service: entra-id
- Article date: 2026-03-09
- Summary: Microsoft Entra Backup and Recovery を使用して、偶発的または悪意のある変更の後にユーザー認証方法を回復する方法について説明します

誤って、または悪意を持って編集または削除された場合に、Microsoft Entra ID のユーザー認証方法 ("ユーザー シークレット") を回復する方法について説明します。

### 認証方法の回復

認証方法は、ユーザー ID セキュリティの重要なコンポーネントです。 中断や侵害により、アカウントのロックアウトや未承認のアクセスが発生する可能性があります。

管理者は、Microsoft Entra のバックアップと回復を使用して、ユーザー オブジェクトと関連付けられている認証データを以前の状態に復元できます。 認証方法を復元できない場合、または復元できない場合、管理者は信頼されていないエントリを削除し、ユーザーに新しい信頼された認証方法を再登録させる必要があります。

これらのセクションでは、2 つの一般的な復旧シナリオの詳細なガイダンスを提供します。

- **誤った変更または削除:** 管理者エラーや自動化など、ユーザーの認証方法またはアカウントが意図せずに変更または削除された場合。
- **悪意のある変更または削除:** 認証方法またはユーザー オブジェクト自体が、無効なアクターによって意図的に変更または削除された可能性があり、ゼロ トラスト復旧アプローチが必要な場合。

すべてのシナリオで、セキュリティで保護されたユーザー アクセスを復元し、認証方法を信頼できることを確認し、適切な管理制御を通じて長期的な防止を強化することが目標です。

### 準備と予防

回復が必要な前に、組織はユーザー認証方法を管理するための明確な制御と冗長性を確立する必要があります。 これにより、中断を最小限に抑え、問題が発生した場合の復旧が簡略化されます。

**推奨されるプラクティス:**

- **すべてのユーザーに多要素認証 (MFA) が必要です。**[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)、[認証強度](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strengths)、または [Identity Protection ポリシー](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)を使用して、ユーザーがパスワードのみに依存するのではなく、強力な方法で認証されるようにします。
- **ユーザーごとに複数の認証方法が必要です。** Microsoft Entra ID では厳密な "2 メソッドの最小値" は適用されませんが、組織は、1 つのメソッドが失われたり削除されたりした場合にロックアウトを防ぐために、少なくとも 2 つの強力な方法を登録するようユーザーに勧める必要があります。 これは、認証強度、 [複数の方法を必要とするセルフサービス パスワード リセット (SSPR) ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr#select-authentication-methods-and-registration-options)、運用チェック ( [Microsoft Graph レポート API](https://learn.microsoft.com/ja-jp/graph/api/resources/userregistrationdetails?view=graph-rest-1.0&preserve-view=true) を使用した定期的なレポートなど) によって強化できます。
- **フィッシングに強い方法を優先します。** アカウントの侵害に対する回復性を強化するために、FIDO2 セキュリティ キー、パスキー、または Windows Hello for Business を登録するようユーザーに勧めます。 ほとんどのエンド ユーザーには同期されたパスキーを使用し、より高い保証ロールにはデバイスバインドパスキーを使用します。 詳細については、 [パスキーデプロイガイド](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-deploy-phishing-resistant-passwordless-authentication)を参照してください。
- **Microsoft Entra ロールベースのアクセス制御 (RBAC) を使用して認証方法の管理を制限します。** 認証方法の管理を [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) または [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator)に制限します。これは、信頼された最小特権のユーザーにのみ割り当てられ、 [Privileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) (PIM) によって昇格されます。 ヘルプデスクのシナリオでは、認証管理者を使用します。これにより、サポート スタッフは、特権アカウントの変更を防ぎながら、標準ユーザーの認証方法をリセットまたは削除できます。
- **条件付きアクセスを使用してユーザー認証方法の登録を保護します。**ユーザーがセキュリティ情報登録ポータルにアクセスするときに、強力なサインイン (準拠しているデバイスや信頼されたネットワークなど) が[必要](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-security-info-registration)です。
- **破壊的操作には保護されたアクションを使用します。**[保護されたアクション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-overview)を使用して、高い特権を持つ管理者にユーザーを完全に削除する機能を制限します。

### 変更の性質を判断する

復元する前に、アクティビティが偶発的か悪意があるかを判断します。

| シナリオ | 一般的な原因 | 推奨される姿勢 |
| --- | --- | --- |
| **偶然** | 管理者エラー、自動化、ディレクトリ同期のエラー | 検証と選択的復元 |
| **悪意** | Insider の脅威、侵害されたアカウント、外部侵害 | 妥協として扱い、信頼できる状態から再構築する |

原因が不明な場合は、ゼロ トラスト復旧のプラクティスに従って **、既定でインシデントを悪意のあるものとして扱** います。

### 復旧ガイダンス

ユーザー認証方法に対する偶発的および悪意のある変更から回復するには、次のガイダンスを使用します。

#### 誤った変更または削除

ユーザーの認証方法またはアカウントが誤って変更または削除された場合は、バックアップと回復を使用して以前の状態に復元します。

**手順 1: 監査ログを確認する**

変更された内容とタイミングを確認することから始めます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**監視**&gt;**Audit ログ**に移動し、影響を受けるユーザーをフィルター処理します。
2. *ユーザー認証方法の更新、ユーザー認証方法の* *削除、ユーザーの削除*などのアクティビティを探*します*。
3. この情報を使用して、問題がユーザーの認証方法のみに影響したかどうか、またはユーザー オブジェクト自体が削除されたかどうかを判断します。

**手順 2: ユーザーを以前の状態に回復する**

バックアップと回復を使用して、誤って変更が発生する前の特定の時点にユーザーを復元します。

ユーザーが**論理削除**された場合に、ユーザーを復元すると、ユーザーの属性、グループ メンバーシップ、ライセンスおよび割り当てが復元されます。 これにより、MFA、SSPR、および認証方法のポリシーが以前と同様に適用され続けるのに役立ちます。

ユーザーが **ハード削除** され、バックアップと回復で使用できなくなった場合は、ユーザーを手動で再作成し、すべての認証方法を最初から登録する必要があります。

注

ユーザーが削除 *されなかった* 場合でも、以前の認証方法を回復するには、ユーザー オブジェクトを復元する必要があります。 個々の認証方法を個別に復元することはできません。

**手順 3: 回復された認証方法を検証する**

復元後、 **Microsoft Entra 管理センター**&gt;**Users**&gt;**{Select user}**&gt;**Authentication methods** page or Microsoft Graph (`GET /users/{id}/authentication/methods`) を使用して、手順 1 で変更または削除された特定の方法を確認します。

Microsoft Entra ID のほとんどの認証方法は、復元後も引き続き正常に機能します。 次の表は、復旧後に使用できるメソッドをまとめたものです。

| 認証方法 | 復元後に使用できますか? |
| --- | --- |
| 電話 (SMS または音声) | ✅ はい |
| Email | ✅ はい |
| パスワード | ❌ いいえ (セキュリティのために回復されません) |
| Microsoft Authenticator Push (通知プッシュの機能) | ✅ はい |
| Microsoft Authenticator パスキー | ✅ はい |
| 同期されたパスキー | ✅ はい |
| FIDO2 セキュリティ キー | ✅ はい |
| Windows Hello for Business | ✅ はい |
| macOS 用のプラットフォーム SSO | ✅ はい |
| 証明書ベースの認証 | ✅ はい |
| 外部認証方法 | ✅ はい |
| 一時アクセス パス | ❌ いいえ (期限あり、通常は期限切れ) |
| OATH ハードウェア トークン | ✅ はい |
| OATH ソフトウェア トークン | ✅ はい |

**手順 4: 新しいパスワードを設定するか、TAP を発行する (必要に応じて)**

ほとんどの認証方法は復元後に完全に使用できるため、通常はアクションが必要な資格情報は 2 つだけです。

1. **ユーザーの新しいパスワードを設定します。** バックアップと回復ではパスワードが復元されないため、回復後にユーザーが通常どおりサインインできるように [、新しいパスワードの設定](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-reset-password-azure-portal) が必要になる場合があります。

    注

    組織で認証強度を使用してパスワードレス認証を適用する場合、この手順は必要ない場合があります。
2. **必要に応じて、新しい一時アクセス パス (TAP) を発行します。** TAP は期限付きであり、通常は復元後に期限切れになります。 ユーザーがサインインを完了したり、メソッドを再構成したりする必要がある場合は、[新しい TAP を生成](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass#create-a-temporary-access-pass)します。

ほとんどの場合、復旧中に特定の方法が意図的に削除されない限り、追加の再登録は必要ありません。

#### 悪意のある変更または削除

ユーザーの認証方法が悪意を持って変更されたか、ユーザー アカウント自体が削除されたと思われる場合は、そのイベントをアカウント侵害の可能性として扱います。 ゼロ トラスト復旧モデルでは、ユーザーに関連付けられているすべての既存のメソッドが信頼できない可能性があると仮定します。

このシナリオの目標は、必要に応じてユーザー オブジェクトを復元し、侵害された可能性のあるすべてのメソッドを削除し、ユーザーに新しい信頼されたメソッドを再登録することです。

**手順 1: 悪意のあるアクティビティを確認する**

予期しない認証方法の変更またはユーザー削除イベントの **監査ログ** を確認します。 変更の発生元が不明または疑わしい場合は、アクティビティが悪意のあるものとして続行します。

次のようなアクティビティを探します。

- *ユーザー認証方法を追加*します / *管理者がユーザー認証方法を追加する*
- *ユーザー認証方法を更新する*
- *ユーザー認証方法を削除する*
- *ユーザーの削除*

**手順 2: 既存のすべての認証方法を削除する**

認証方法を削除する前に、ユーザー オブジェクトが存在することを確認します。

- **ユーザー アカウントが悪意を持って削除されても回復可能な場合は**、バックアップと回復を使用して、削除が発生する前の特定の時点にユーザーを復元します。
- **ユーザーがハード削除され、バックアップと回復で使用できなくなった場合は**、ユーザーを手動で再作成します。 この場合、削除する認証方法は存在せず、すべての方法を復旧の一部として新しく登録する必要があります。

ユーザーを復元したら、すべての認証方法の削除に進みます。 **Microsoft Entra 管理センター**&gt;**Users**&gt;**{Select user}**&gt;**Authentication methods** page or Microsoft Graph (`/users/{id}/authentication/methods`) を使用して、ユーザーのすべての既存の認証方法を削除します。 バックアップと回復によって元に戻すことができる場合でも、悪意のあるアクティビティに関係している可能性のある認証方法を再利用しないでください。

**手順 3: ユーザーの新しいパスワードを設定する**

新しい認証方法を登録する前に、 [ユーザーに新しいパスワードを設定](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-reset-password-azure-portal) して、回復されたアカウントのクリーンで信頼できる資格情報を確立します。 バックアップと回復ではパスワードが復元されないため、新しいパスワードを発行すると、ユーザーは管理者が発行した既知のベースラインから開始されます。

**Microsoft Entra 管理センターまたは Microsoft** Graph (`POST /users/{id}/changePassword`) を使用してパスワードをリセットします。 テナントが認証強度を使用してパスワードレスのみの認証を適用しない限り、ユーザーは次回のサインイン時に新しい強力なパスワードを設定する必要があります。

**手順 4: 新しい認証方法を再登録する**

1. **組織の標準登録フローを使用して新しい方法を登録するようユーザーに要求** します。
2. 次のような**フィッシングに強い認証**オプションを可能な限り推奨します。
    - FIDO2 セキュリティキー
    - パスキー (デバイスバウンドまたはクロスデバイス)
    - Windows Hello for Business (マネージド デバイス用)
3. **ID 検証を使用します (推奨)。** 使用可能な場合、組織は [アカウントの回復](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-account-recovery-overview)の一環としてリリースされた ID 検証機能の使用を検討する必要があります。 ID 検証は、新しい認証方法を登録する前にユーザー信頼を再確立するための一時アクセス パスのより高い保証とセキュリティの代替手段を提供します。
4. **ID 検証が利用できない場合は、一時アクセス パス (TAP) を指定** して、安全な初期サインインと方法のセットアップを許可します。
5. ユーザーの認証方法の一覧の下に**新しい方法が正しく表示されていることを確認**します。

### 復旧後の検証

任意のシナリオで復旧を完了した後:

- ユーザーにテスト サインインを実行して、復元されたメソッドまたは再登録されたメソッドが正しく機能することを確認させます。
- MFA、SSPR、および条件付きアクセス ポリシーが想定どおりに適用されることを確認します。
- Microsoft Entra RBAC による管理者アクセスの制限や条件付きアクセスによる登録の保護など、この記事で前述した防止と準備に関する推奨事項を確認し、可能な限りこれらの制御が実装されていることを確認します。
- 回復の結果と、将来の対応を改善するために学んだ教訓を文書化します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/backup/review-recovery-history"} -->
## Microsoft Entra のバックアップと回復の回復履歴を確認する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/backup/review-recovery-history
- Service: entra-id
- Article date: 2026-03-02
- Summary: Microsoft Entra Backup and Recovery の [回復履歴] ページを使用して、テナントで実行された復旧操作を確認する方法について説明します

Microsoft Entra Backup and Recovery の [回復履歴] ページを使用して、テナントの過去の復旧操作を確認する方法について説明します。

回復履歴には次のものが含まれます。

- 復旧の最終的な状態。
- 各復旧に使用されるバックアップ ポイント。
- 復旧の開始時刻と完了時刻。
- 変更されたオブジェクトとリンクの数。

最近の運用レビューとトラブルシューティングには、復旧履歴を使用します。 復旧履歴データは、復旧の完了後最大 7 日間保持されます。

### 前提条件

テナントで使用可能な回復履歴を表示するには、少なくとも **Microsoft Entra Backup 閲覧者** ロールでサインインする必要があります。

### 回復履歴を確認する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも **Microsoft Entra Backup Reader** としてサインインします。
2. 左側のナビゲーション ウィンドウで、[**バックアップと回復**] の下にある [**回復履歴**] を選択します。

    [Image: [状態]、[バックアップ タイムスタンプ]、[回復が開始されました]、[変更されたオブジェクト] 列を含む回復操作を示す [回復履歴] ページのスクリーンショット。]

    [回復履歴] ページには、テナント内の最近の復旧操作がすべて表示されます。 このページから:

    - 各操作の **復旧 ID を** 表示します。
    - 状態を確認 **します**。
    - 使用されている **バックアップ** タイムスタンプと **バックアップ ID を** 参照してください。
    - 復旧の開始と完了を確認します。
    - 回復が変更されたオブジェクトとリンクの数を確認します。
    - 回復レコードをフィルター処理または検索して、結果を絞り込みます。

    [Image: 状態とタイムスタンプの詳細を含む複数の回復操作を示す [回復履歴] ページのスクリーンショット。]

注

システムは、復旧の完了後 7 日後に自動的に回復履歴を削除します。

#### 回復の状態

復旧操作は、システムがテナントに変更を適用すると、これらの状態を通過します。 これらの状態は、回復ジョブの進行状況と結果を示します。

| 地位 | 説明 |
| --- | --- |
| **データの読み込み** | システムは、選択したバックアップからデータを読み込み、復旧の準備をしています。 バックアップを既に使用して差分レポートまたは以前の復旧を作成している場合、この手順はすぐに完了する可能性があります。 |
| **処理中** | システムは、オブジェクトをバックアップ状態に復元するための回復アクションを適用しています。 この手順の期間は、適用される変更の数と種類によって異なります。 |
| **完了** | 復旧が正常に完了し、サポートされているすべての変更がシステムによって適用されました。 |
| **警告が表示された状態で完了しました** | 復旧は完了しましたが、一部の変更を適用できませんでした。 失敗した変更を確認して、復元されなかったオブジェクトとその理由を理解します。 |
| **失敗しました** | エラーが発生したため、復旧を完了できませんでした。 システムで一部の変更が適用されていない可能性があります。 |
| **取り消されました** | 復旧は完了前に取り消されました。 |

### 失敗した変更を確認する

回復操作が部分的に成功した場合、[ **状態]** 列に **警告が**表示され、[完了] と表示され、回復されなかったオブジェクトを識別できます。 [ **完了と警告]** を選択すると、回復されなかった変更の詳細が表示されます。

[Image: [状態] 列で [完了] エントリが強調表示されている [回復履歴] ページのスクリーンショット。]

オブジェクト **の変更された属性** または **変更されたリンク** を選択して、エラーの詳細を表示します。

[Image: エラー コード 400 の回復ジョブの詳細と失敗したオブジェクトを示す [失敗した回復の変更] ページのスクリーンショット。]

**復旧試行時の値** は、復旧が試行された時点の属性値を示します。 **バックアップ値** は、復旧サービスが復元しようとした値を示します。

[Image: エラーの詳細と属性値の比較を示す [変更された属性の表示に失敗しました] ポップアップのスクリーンショット。]

失敗した復旧エントリを使用して次の操作を行います。

- 正常に完了しなかった回復操作とオブジェクトを特定します。
- 使用されたバックアップ ポイントを確認します。
- 復旧が成功しなかった理由を説明するエラーの詳細を表示します。

注

失敗した復旧レコードは、復旧の完了後 7 日間使用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/backup/scope-supported-objects-limitations"} -->
## Microsoft Entra のバックアップと回復でサポートされているオブジェクトと回復可能なプロパティ - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/backup/scope-supported-objects-limitations
- Service: entra-id
- Article date: 2026-08-25
- Summary: サポートされている Microsoft Entra Backup および Recovery オブジェクトの種類とプロパティについて説明し、現在の制限事項を理解します

Microsoft Entra Backup and Recovery では、テナント オブジェクトの種類の定義済みセットと、それらのオブジェクトで選択されたプロパティの復旧がサポートされています。

注

サポートされているオブジェクトとプロパティのセットは、時間の経過と同時に拡張されます。 回復は、この記事に記載されているサポートされているプロパティにのみ適用され、オブジェクトの完全なロールバックを意味するものではありません。

バックアップは 1 日に 1 回自動的に作成されます。 差分レポートでは、選択したバックアップが現在のテナントの状態と比較され、サポートされているプロパティとリンクに対する変更が表示されます。

### 回復スコープ レベル

差分レポートを作成するか、復旧を開始するときに、操作のスコープを、サポートされているすべてのオブジェクト、選択したオブジェクトの種類、または特定のオブジェクト ID に設定できます。 一部の関連オブジェクトは、スコープ用にグループ化されます。 たとえば、サービス プリンシパル、OAuth2 アクセス許可付与、アプリ ロールの割り当ては、Microsoft Entra 管理センターの 1 つのフィルターの下にグループ化されます。

注

Microsoft Entra Backup と Recovery では、基になるディレクトリ オブジェクトの種類を通じてMicrosoft Entra エージェント IDオブジェクトがカバーされます。 エージェントのユーザー アカウントはユーザー オブジェクトです。 エージェント ID ブループリントはアプリケーション オブジェクトです。 エージェント ID およびエージェント ID ブループリントのプリンシパルは、サービス プリンシパル オブジェクトです。 バックアップ、差分レポート、および回復には、オブジェクトの種類ごとに一覧表示されているサポートされているプロパティのみが含まれます。

### User

ユーザー オブジェクトの回復では、次のプロパティがサポートされます。

- `AccountEnabled`
- `AgeGroup`
- `City`
- `CompanyName`
- `ConsentProvidedForMinor`
- `Country`
- `Department`
- `DisplayName`
- `EmployeeHireDate`
- `EmployeeId`
- `EmployeeLeaveDate`
- `EmployeeOrgData`
- `EmployeeType`
- `FaxNumber`
- `GivenName`
- `JobTitle`
- `Mail`
- `MailNickname`
- `Mobile`
- `OtherMail`
- `PasswordPolicies`
- `PerUserMfaState`
- `PhysicalDeliveryOfficeName`
- `PostalCode`
- `PreferredDataLocation`
- `PreferredLanguage`
- `State`
- `StreetAddress`
- `Surname`
- `TelephoneNumber`
- `UsageLocation`
- `UserPrincipalName`
- `UserType`

注

マネージャーとスポンサーの変更はスコープ内にありません。

参照については、 [Microsoft Graph ユーザー リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/user#properties)のユーザー プロパティの完全なセットを表示します。

### グループ

グループ オブジェクトの回復では、次のプロパティがサポートされます。

- `Classification`
- `Description`
- `DisplayName`
- `GroupType`
- `IsPublic`
- `Mail`
- `MailEnabled`
- `MailNickname`
- `PreferredDataLocation`
- `PreferredLanguage`
- `SecurityEnabled`
- `Theme`

注

グループの所有権の変更はスコープ内にありません。 動的グループは復旧中に復元または論理的に削除できますが、動的グループ ルールの変更はスコープ内にありません。

参照については、 [Microsoft Graph グループ リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/group#properties)のグループ プロパティの完全なセットを表示します。

### 条件付きアクセス ポリシー

条件付きアクセス ポリシーのすべてのプロパティがスコープ内にあります。 [Microsoft Graph conditionalAccessPolicy リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/conditionalaccesspolicy#properties)ですべての条件付きアクセス ポリシー プロパティを表示します。

### 名前付き場所ポリシー

名前付き場所ポリシーのすべてのプロパティが対象範囲内にあります。 [Microsoft Graph の namedLocation リソース タイプですべての名前付きロケーション ポリシー プロパティを表示します。](https://learn.microsoft.com/ja-jp/graph/api/resources/namedlocation#properties)

### 認可ポリシー

承認ポリシー オブジェクトの回復では、次のプロパティがサポートされます。

- `blockMsolPowerShell`
- `guestUserRoleId`

ゲスト ユーザー ロール ID とゲスト ユーザーのアクセス許可レベルのマッピングを次に示します。

| 権限レベル | 説明 | 役割ID |
| --- | --- | --- |
| メンバーユーザー | ゲスト ユーザーはメンバーと同じアクセス権を持ちます | `a0b1b346-4d3e-4e8b-98f8-753987be4970` |
| ゲスト ユーザー | ゲスト ユーザーは、ディレクトリ オブジェクトのプロパティとメンバーシップへのアクセスが制限されています | `10dae51f-b6af-4016-8d66-8c2a99b929b3` |
| 制限されたゲストユーザー | ゲスト ユーザー アクセスは、独自のディレクトリ オブジェクトのプロパティとメンバーシップに制限されます | `2af84b1e-32c8-42b7-82bc-daa82404023b` |

リファレンスについては、 [Microsoft Graph authorizationPolicy リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/authorizationpolicy#properties)の承認ポリシー プロパティの完全なセットを表示します。

### 認証方法ポリシー

回復では、次の認証方法ポリシーがサポートされています。

- 電子メール ワンタイム パスワード (OTP)
- FIDO2 パスキー
- Authenticator アプリ
- 音声通話
- SMS
- サード パーティ製ソフトウェア OATH
- 一時アクセス パス
- 証明書ベースの認証

リファレンスについては、 [Microsoft Graph authenticationMethodConfiguration リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/authenticationmethodconfiguration)の認証方法ポリシー プロパティの完全なセットを表示します。

### アプリケーション

**アプリケーション** オブジェクトの回復では、次のプロパティがサポートされます。

- `DisplayName`
- `Description`
- `Notes`
- `ApplicationTag`
- `AppIdentifierUri`
- `AppCreatedDateTime`
- `PublicClient`
- `PublisherDomain`
- `IsDeviceOnlyAuthSupported`
- `ServiceManagementReference`
- `RequiredResourceAccess`
- `NativeAuthenticationApisEnabled`
- `SignInAudience`
- `GroupMembershipClaims`
- `OptionalClaims`
- `IsDisabled`
- `AddIns`
- `ServicePrincipalLockConfiguration`
- `AppInformationalUrl`

参考までに、 [Microsoft Graph アプリケーション リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/application#properties)のアプリケーション プロパティの完全なセットを表示します。

### サービス プリンシパル

**サービス プリンシパル** オブジェクトの復旧では、次のプロパティがサポートされます。

- `AccountEnabled`
- `AlternativeNames`
- `ExplicitAccessGrantRequired`
- `Description`
- `LoginUrl`
- `Notes`
- `NotificationEmailAddresses`
- `PreferredTokenSigningKeyThumbprint`
- `ServicePrincipalTag`
- `ServicePrincipalType`
- `PreferredSingleSignOnMode`
- `PublisherName`
- `SamlSingleSignOnSettings`
- `ServicePrincipalName`

参考までに、 [Microsoft Graph servicePrincipal リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal#properties)のサービス プリンシパル プロパティの完全なセットを表示します。

サービス プリンシパルの回復は、関連するアクセス許可の基盤です。 サービス プリンシパルが復旧されると、Microsoft Entra のバックアップと回復も復元されます。

- OAuth2 アクセス許可は、回復されたサービス プリンシパルがターゲット オブジェクトである場所を許可します
- 復旧されたサービス プリンシパルがターゲット オブジェクトであるアプリ ロールの割り当て

#### OAuth2 (委任された) アクセス許可の付与

OAuth2 アクセス許可付与は、アプリケーションのサービス プリンシパルに付与された委任されたアクセス許可を表します。 管理者は、ユーザーがアプリケーションの API へのアクセス要求に同意した場合に委任されたアクセス許可付与を作成できます。または、管理者はすべてのユーザーに代わってアクセス許可を付与できます。 管理者がすべてのユーザーに代わって作成するアクセス許可が対象範囲に含まれます。 これらのアクセス許可付与は、 `consentType` = `AllPrincipals` と `principalId` = `null`で識別できます。

ユーザーの同意の結果として作成されたアクセス許可許可はサポートされていません。 [Microsoft Graph oauth2PermissionGrant リソースの種類で OAuth2](https://learn.microsoft.com/ja-jp/graph/api/resources/oauth2permissiongrant#properties) (委任された) アクセス許可付与プロパティを表示します。

OAuth2 アクセス許可付与は個別に復旧されません。 レポートと回復のスコープの違いについては、サービス プリンシパル、OAuth2 アクセス許可付与、アプリ ロールの割り当ては、Microsoft Entra 管理センターの 1 つのフィルターの下にグループ化されます。

#### アプリ ロールの割り当て

アプリ ロールの割り当ては、ユーザー、グループ、またはサービス プリンシパルにアプリのアプリ ロールが割り当てられたときに記録されます。 アプリ ロールの割り当てのすべてのプロパティがスコープ内にあります。 [Microsoft Graph appRoleAssignment リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/approleassignment)ですべてのアプリロールの割り当ての詳細とプロパティを表示します。

アプリ ロールの割り当ては個別に回復されません。 レポートと回復のスコープの違いについては、サービス プリンシパル、OAuth2 アクセス許可付与、アプリ ロールの割り当ては、Microsoft Entra 管理センターの 1 つのフィルターの下にグループ化されます。

### 組織

組織オブジェクトの回復では、次のプロパティがサポートされます。

**テナント レベルのユーザーごとの多要素認証 (MFA) 設定:**

- `StrongAuthenticationDetails`

    - `availableMFAMethods`

        [Image: StrongAuthenticationDetails の下の使用可能なMFAMethods プロパティを示すスクリーンショット。]
    - `IsApplicationPasswordBlocked`

        [Image: StrongAuthenticationDetails の IsApplicationPasswordBlocked プロパティを示すスクリーンショット。]
    - `IsRememberDevicesEnabled`

        [Image: StrongAuthenticationDetails の IsRememberDevicesEnabled プロパティを示すスクリーンショット。]
    - `rememberDevicesDurationInDays`

        [Image: StrongAuthenticationDetails の rememberDevicesDurationInDays プロパティを示すスクリーンショット。]
- `StrongAuthenticationPolicy`

    - `enabled`

        [Image: StrongAuthenticationPolicy の下の有効なプロパティを示すスクリーンショット。]
    - `ipAllowList`

        [Image: StrongAuthenticationPolicy の ipAllowList プロパティを示すスクリーンショット。]

### 制限事項

Microsoft Entra のバックアップと回復を使用する場合は、次の制限事項を考慮してください。

#### ジョブの完了時間

差分レポートと回復の完了時間は、**データの読み込みと** **処理**によって異なります。

差分レポートまたは復旧を使用してバックアップに初めてアクセスすると、復旧サービスによってバックアップ データが読み込まれます。 この読み込みには、小規模なテナントでも一定の時間がかかります。 このサービスでは、同じバックアップを参照する操作間で読み込まれたデータが再利用されるため、後続の操作は高速に完了します。 復旧前に差分レポートを作成すると、データを事前に読み込むことで復旧時間を短縮できます。

データの読み込みが完了すると、操作は処理に移ります。 差分レポートでは、処理によってバックアップと現在のテナントの間の変更が識別されます。 復旧の場合、バックアップ状態を復元するために必要な変更が処理によって適用されます。 処理時間は、オブジェクトの数、操作のスコープ、および関係する変更の数によって異なります。

#### ハード削除されたオブジェクト

Microsoft Entra のバックアップと回復では、ハード削除されたオブジェクトの回復または再作成はサポートされていません。 復元できるのは、ソフト削除されたオブジェクトまたは変更済みのオブジェクトのみです。

論理的に削除されたユーザー、Microsoft 365 グループ、クラウド セキュリティ グループ、アプリケーションの登録、サービス プリンシパルは、30 日間復元できます。 論理的な削除とバックアップと回復の関係の詳細については、「[Microsoft Entraバックアップと回復」の「論理的な削除](https://learn.microsoft.com/ja-jp/entra/backup/soft-deletion)」を参照してください。 バックアップと回復は、保持されているバックアップからサポートされているプロパティとリンクを復元することに重点を置いており、ソフト削除の回復に代わるものではありません。

#### オンプレミスの Active Directory Domain Services で管理されるオブジェクト

オンプレミス同期オブジェクト (グループ メンバーシップを除く) に加えられた変更は、差分レポートに表示されますが、回復からは自動的に除外されます。 Microsoft Entra ID でハイブリッド ID を使用する組織では、差分レポートを使用して、オンプレミスから同期されたオブジェクトに対する変更を識別できます。 ユーザーやグループなど、特定の種類のオブジェクトについては、権限のソースをオンプレミスからクラウドに移動できます。 変換後、これらのオブジェクトに対してすべてのバックアップと回復機能を使用できます。 別のソリューションを使用して、オンプレミスで管理されているオブジェクトをバックアップおよび回復します。

バックアップの作成後にユーザーまたはグループがクラウド管理に変換された場合、そのバックアップから復旧しても、権限のソースはオンプレミスの Active Directory に戻りません。 サポートされているその他の変更された属性が復旧されます。

#### より広範な回復可能性

Microsoft Entra のバックアップと回復は、組織の回復力を高めるのに役立つ回復性に対するより広範なアプローチの一部として使用する必要があります。 悪意のある偶発的なディレクトリ データ損失のリスクを軽減するには、 [Microsoft Entra ID の回復可能性のベスト プラクティスに](https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-deletions)従ってください。 これらのプラクティスは次のとおりです。

- 予防的な運用セキュリティ対策の確立
- Microsoft Graph API を使用して既知の良好な状態を定期的に文書化する
- 削除と構成の誤りから回復するためのプロセスの準備
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/backup/soft-deletion"} -->
## Microsoft Entra のバックアップと回復におけるソフト削除 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/backup/soft-deletion
- Service: entra-id
- Article date: 2026-03-02
- Summary: 論理的な削除とは何か、それが Microsoft Entra のバックアップと回復にどのように関連しているか、および回復でできることとできないことについて説明します

論理的な削除は、Microsoft Entra の基本的なデータ保護機能であり、組織が偶発的または悪意のある削除から回復するのに役立ちます。 論理的な削除では、オブジェクトを直ちに完全に削除する代わりに、限られた保有期間にわたってオブジェクトが回復可能な状態になります。 この間、オブジェクトはプロパティとリレーションシップをそのまま使用して復元できます。

論理的な削除は、Microsoft Entra のバックアップと回復の中核となる構成要素であり、オブジェクトを再作成したり、アクセス モデルを再構成したりすることなく、信頼性の高い回復を可能にします。 削除と回復の概念の概要については、「 [削除からの回復](https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-deletions)」を参照してください。

この記事では、論理的な削除とは何か、バックアップと回復との関係、および復旧で実行できる操作と実行できない操作について説明します。

### ソフト削除とは

オブジェクトが論理削除をサポートしている場合、そのオブジェクトが削除されても、Microsoft Entra はそれをすぐにディレクトリから削除しません。 代わりに、論理削除された状態に遷移します。

- オブジェクトはアクティブではなくなり、認証や承認には使用できません。
- Microsoft Entra は、オブジェクトのデータを 30 日間保持します。
- 保持期間中にオブジェクトを復元し、以前のアクティブな状態に戻すことができます。

### ソフト削除とバックアップおよびリカバリー

Microsoft Entra のバックアップと回復は、包括的な回復エクスペリエンスを提供するために論理的な削除に基づいています。

#### バックアップのしくみ

Microsoft Entra は、サポートされているディレクトリ オブジェクトに対する変更を継続的に記録します。 オブジェクトが論理的に削除された場合、バックアップは変更をキャプチャし、そのバックアップを回復に使用するときにオブジェクトを復元します。 論理的な削除をサポートするオブジェクトについては、「 [Microsoft Entra ID での削除からの回復」を](https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-deletions#properties-maintained-with-soft-delete)参照してください。

これらのバックアップは Microsoft が管理するため、独自のコピーをエクスポートまたは管理する必要はありません。 バックアップでは、時間の経過と同時にオブジェクトの状態がキャプチャされ、既知の適切な時点への復旧が可能になります。

#### 回復のしくみ

復旧操作中:

- Microsoft Entra は **、バックアップ** を使用して正しいオブジェクトの状態を判断します。
- バックアップとリカバリーでは、ソフト削除されたオブジェクトを再作成するのではなく、復元します。
- バックアップと回復 **ソフトは、バックアップの** 作成後に追加されたオブジェクトを削除します。
- オブジェクト識別子、プロパティ、サポートされているリレーションシップは保持されます。

Important

Microsoft は、復旧プロセスの一環として顧客オブジェクトをハード削除することはありません。 回復操作は常に、論理的に削除されたオブジェクトを復元するか、オブジェクトを以前の状態にロールバックすることに依存します。 回復中、バックアップと回復ソフトは、選択したバックアップの後に追加された新しいオブジェクトを削除します。 このアプローチは、回復後の偶発的および悪意のある構成ミスのリスクを軽減するのに役立ちます。 1 つ以上のオブジェクトを論理的に削除しないシナリオでは、フィルターを適用して、回復の対象となるオブジェクトを制御します。

このアプローチにより、次のようなオブジェクトの再作成のリスクと運用上の負担が回避されます。

- オブジェクト ID の損失
- 破損した依存関係
- 管理者によるアクセスまたはポリシーの手動再構成

#### ソフト削除とハード削除

復旧計画では、論理的な削除とハード削除の違いを理解することが重要です。

| 削除の種類 | 何が起きるか | 回復できますか? |
| --- | --- | --- |
| **ソフトデリート** | オブジェクトは削除された状態で一定期間保持されます | はい(リテンション期間内) |
| **物理的な削除** | オブジェクトがディレクトリから完全に削除される | いいえ |

オブジェクトが **ハード削除された**場合、オブジェクトは完全に削除され、 **回復できません**。 唯一のオプションは、新しいオブジェクトを作成することです。その結果、新しいオブジェクト ID が作成され、以前の構成とリレーションシップが失われます。

Microsoft Entra のバックアップと回復 **では、ハード削除されたオブジェクトの回復はサポートされていません**。 組織では、Microsoft Entra 条件付きアクセスなどの機能を使用して、ディレクトリ オブジェクトのハード削除など、機密性の高いアクセス許可の保護レイヤーを追加できます。 詳細については、「[Microsoft Entra ID 内の保護されたアクションとは](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-overview)」を参照してください。

#### 論理的な削除が重要な理由

ソフト削除は、レジリエントな ID システムを構築するために不可欠です。

- ミスや攻撃からの迅速な回復を可能にする
- オブジェクトの整合性とリレーションシップを保持します。
- ダウンタイムと運用上のリスクを軽減
- 信頼性の高いバックアップと回復の基盤を形成します

論理的な削除と組み合わせると、Microsoft Entra Backup and Recovery を使用すると、組織は意図しない、または悪意のある属性の変更や削除から回復できます。 復旧によって顧客データが完全に削除されることはありません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/backup/troubleshooting"} -->
## Microsoft Entra のバックアップと回復のトラブルシューティング - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/backup/troubleshooting
- Service: entra-id
- Article date: 2026-03-02
- Summary: Microsoft Entra のバックアップと回復に関する一般的な問題 (バックアップ アクセス、差分レポート、回復ジョブなど) を診断して解決する

この記事は、Microsoft Entra のバックアップと回復 (バックアップ アクセス、差分レポート ジョブ、回復ジョブなど) を使用するときの一般的な問題を診断して解決するのに役立ちます。

### 開始する前に

トラブルシューティングを行う前に、次の前提条件を確認してください。

- テナントは **従業員テナント** です (外部 ID と B2C テナントはサポートされていません)。
- テナントに **Microsoft Entra ID P1 または P2** ライセンスがあります。
- 適切な **Microsoft Entra Backup ロール**を持つアカウントでサインインしています。
    - **Microsoft Entra バックアップ リーダー**
    - **Microsoft Entra バックアップ管理者**

**グローバル管理者**ロールには、必要なアクセス許可もあります。

これらの前提条件が満たされていない場合、操作が承認エラーまたは空の結果で失敗する可能性があります。

### 問題: バックアップが一覧に表示されない

この問題は、バックアップ リストに表示されるバックアップ数が予想よりも少ない場合、またはタイムスタンプが重複している場合に発生します。

#### Symptoms

- 予想される 7 日間ではなく、7 日未満のバックアップが表示されます。
- 2 つ以上のバックアップのバックアップ一覧に同じタイムスタンプが表示されます。

#### 考えられる原因

最も古いバックアップは、サービスの初期化中、テナントのオンボーディング中、またはバックエンドの一時的な状態変化により、保持期間から通常より早く外れることがあり、その結果、一時的に表示されるバックアップが 7 日分未満になる場合があります。 この条件は、データの損失やバックアップの失敗を示すわけではありません。

#### Resolution

アクションは必要ありません。 サービスは引き続き新しいバックアップを自動的に作成します。

### 問題: 差分レポートまたはリカバリージョブを開始できない

この問題は、競合、承認エラー、またはサポートされていないオブジェクトの種類が原因で、差分レポートまたは回復ジョブの開始に失敗した場合に発生します。

#### Symptoms

- 差分レポートまたは復旧ジョブが起動しません。
- エラーは、競合または承認エラーを示します。
- 使用するオブジェクトの種類は、スコープ フィルターのドロップダウンには表示されません。

#### 考えられる原因

- 別の差分レポートまたは回復ジョブが既に実行されています。
- サインインしているユーザーには、必要な Microsoft Entra Backup ロールがありません。
- 選択したオブジェクトの種類は、現在のリリースのバックアップと回復ではサポートされていません。

#### Resolution

1. 別の差分レポートまたは回復ジョブが実行されているかどうかを確認します。 一度に実行できるジョブは 1 つだけです。
2. アカウントに適切なロールがあることを確認しましょう。差分レポート用の**Microsoft Entra Backup Reader** または復旧用の**Microsoft Entra Backup Administrator**。
3. スコープを設定しようとしているオブジェクトの種類とオブジェクト属性がサポートされていることを確認します。 スコープ フィルターには、サポートされているオブジェクトの種類と属性のみが表示されます。

### 問題: 相違レポートの問題

このセクションでは、変更の欠落、実行時間の長いジョブ、失敗したジョブ、取り消されたジョブ、不足しているレポートなど、相違レポートに関連する一般的な問題のトラブルシューティングに役立ちます。

#### Symptoms

- 差分レポートには、探していたオブジェクトの変更は含まれていませんでした。
- 差分レポートは数時間実行されており、いつ終了したかはわかりません。
- 差分レポートの状態は "Failed" です。
- 前に生成した差分レポートが見つかりません。
- **[キャンセル]** を選択しましたが、差分レポートは引き続き実行されているようです。

#### 考えられる原因

- オブジェクトまたはプロパティは、現在のリリースのプレビューではサポートされていません。
- バックアップ状態と現在の状態の間でオブジェクトが変更されませんでした。
- 差分レポートでは、多数のオブジェクトまたは変更が処理されています。
- 一度に実行できる相違レポートまたは回復ジョブは 1 つだけです。
- レポートが取り消されたか、完了前に失敗しました。
- 差分レポートは無期限に保持されません。

#### Resolution

**予想される変更がない場合:**

1. オブジェクトの種類とプロパティが現在のリリースでサポートされていることを確認します。
2. バックアップの作成後にオブジェクトが変更されたことを確認します。
3. ハード削除されたオブジェクトとサポートされていないプロパティは含まれません。

**差分レポートが長時間にわたって実行されている場合:**

[推定差分レポートの生成時間](https://learn.microsoft.com/ja-jp/entra/backup/backup-difference-report-recovery-model)を参照してください。 大規模なテナントまたは大規模な変更セットの処理に時間がかかる場合があります。

- 取り消しが必要でない限り、レポートの実行を続行できるようにします。

**差分レポートが失敗した場合:**

1. レポートの詳細に表示されるジョブの状態とエラー メッセージを確認します。
2. 特定のオブジェクトの種類やオブジェクト ID など、より狭いスコープを使用して、差分レポートを再試行してください。
3. 他の差分レポートまたは復旧ジョブが同時に実行されていないことを確認します。

**以前の相違レポートが見つからない場合:**

差分レポートは、作成されたバックアップに関連付けられます。

1. バックアップを参照し、それに関連付けられている相違レポート ジョブの一覧を確認します。
2. レポートが一覧に表示されない場合、差異レポートは終了日から 7 日間保持されるため、使用できなくなる可能性があります。

**取り消し後も差分レポートの実行が続く場合:**

1. キャンセルはベスト エフォートです。 **[キャンセル**] を選択した後も、一部の処理が短時間続く場合があります。
2. ジョブが実行中の状態のままである場合は、状態が更新されるまで待ってから、新しいレポートを開始します。

### 問題: リカバリー ジョブの問題

この問題は、回復ジョブが予期されたオブジェクトを回復しない場合、予想よりも長く実行される場合、または警告が表示された状態で完了した場合に発生します。

#### Symptoms

- 回復ジョブは、差分レポートに表示されたオブジェクトを回復しませんでした。
- 復旧ジョブは数時間実行されており、いつ完了したかはわかりません。
- 回復ジョブの状態が "警告付きで完了しました" です。
- 復旧ジョブを取り消しましたが、ジョブはまだ実行中と表示されています。

#### 考えられる原因

- 差分レポートに表示されるオブジェクトまたはプロパティ (オンプレミス同期プロパティなど) は、復旧ではサポートされていません。
- 回復ジョブには、多数のオブジェクトまたは変更が含まれています。
- 一度に実行できる相違レポートまたは回復ジョブは 1 つだけです。
- 変更の処理中に回復ジョブが取り消されたか中断されました。
- 一部の復旧アクションは、障害または取り消しが発生する前に部分的に成功する可能性があります。

#### Resolution

1. 回復ジョブの詳細から、失敗した変更の一覧を確認します。
2. サポートされているクラウド専用オブジェクトに焦点を当てて、より狭いスコープで復旧を再試行します。

### 問題: リカバリー ジョブが見つからないか、すべてのリンクが復旧されていない

この問題は、以前に完了した回復ジョブが表示されなくなった場合、または一部のリンクのみが復旧された場合に発生します。

#### Symptoms

- 昨日実行したリカバリー ジョブが見つかりません。
- 回復ジョブでは、一部のリンクまたはプロパティのみが復旧され (たとえば、5 つ中 1 つ)、回復されなかったリンクまたはプロパティが表示されます。

#### 考えられる原因

- 回復ジョブの詳細は、ジョブが完了してから 7 日後に自動的に削除されます。
- 一部のリンクは、現在のリリースでは復旧できません。
- 特定のリンクは、存在しなくなった他のオブジェクトまたは状態に依存します。
- 回復ジョブが完了し、部分的な成功を示す警告が表示されます。

#### Resolution

**前に実行した復旧ジョブが見つからない場合:**

1. 復旧に使用された **バックアップ タイムスタンプ** を見つけます。
2. **復旧履歴**を確認して、**そのバックアップ タイムスタンプに関連付けられている復旧ジョブを見つけます**。
3. ジョブが一覧に表示されていない場合、復旧ジョブは完了日から 7 日間保持されるため、そのジョブはすでに利用できなくなっている可能性があります。

**すべてのリンクが回復されなかった場合:**

1. **警告やリンク変更の失敗**があるか、復旧ジョブの詳細を確認します。
2. 回復する予定のリンクが現在のリリースでサポートされていることを確認します。
3. 次の場合、一部のリンクは復旧されない可能性があります。
    - 関連オブジェクトが存在しなくなりました。
    - リンクの種類はサポートされていません。
4. 必要に応じて、サポートされていないリンクまたは失敗したリンクを手動で再作成します。

### エラー条件とメッセージ

| 状態 | エラー コードとメッセージ |
| --- | --- |
| 無効なバックアップ ID でクエリされた差分レポート | **404 Not Found**: これは、復旧に有効なタイムスタンプではありません。 指定されたタイムスタンプは、使用可能なバックアップの一覧に含まれている必要があります。 |
| 無効なジョブ ID でジョブがクエリされる | **404 見つかりません** |
| 別の差分レポートまたは回復ジョブがまだ実行中のときに、新しいジョブが開始される。 | **409 競合**: 復旧ジョブが現在進行中です。 完了するのを待つまでは、新しいジョブを開始しないでください。 |
| 差分レポートが完了していない間に変更を取得する | **400 無効な要求**: 識別子 `{key}` を持つジョブは、変更を列挙する前に正常に完了している必要があります。 |
| 管理者権限が不足 | **403 禁止**: この要求に対する承認が拒否されました。 資格情報を確認してください。 |

### 既知の制限

このリリースの Microsoft Entra Backup and Recovery には、次の既知の制限事項が適用されます。

#### 部分プロパティカバレッジ

バックアップと回復 **では、** サポートされているオブジェクトのすべてのプロパティがカバーされるわけではありません。 この制限には、読み取り専用プロパティ、システム生成プロパティ、特殊なビジネス ロジックに依存するプロパティが含まれます。 詳細については、 [サポートされているプロパティの一覧](https://learn.microsoft.com/ja-jp/entra/backup/scope-supported-objects-limitations) を参照してください。 Microsoft では、時間の経過と同時に、より多くのプロパティのサポートを拡大しています。

#### テナントサポートスコープ

Microsoft Entra のバックアップと回復は、従業員テナントでのみサポートされています。 外部 ID と B2C テナントはサポートされていません。

#### ハード削除されたオブジェクト

ハード削除されたオブジェクト **は回復できません** 。 これらのオブジェクトは差分レポートに含まれていないので、復旧ジョブを通じて再作成または復元することはできません。 ハード削除のリスクを軽減するには、 [保護されたアクション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-overview)を構成することを検討してください。

#### オンプレミス同期オブジェクト

オンプレミスの Active Directory から同期されたユーザーとグループは、Microsoft Entra のバックアップと回復では回復できません。 これらのオブジェクトは、オンプレミスの Active Directory 環境で直接復旧する必要があります。

#### リンク回復の制限事項

回復では、静的グループ メンバーシップ リンクのみがサポートされます。 グループ所有者のリンク、ユーザー マネージャーの関係、およびスポンサー リンクはサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/backup/view-available-backups"} -->
## Microsoft Entra のバックアップと回復で使用可能なバックアップを表示する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/backup/view-available-backups
- Service: entra-id
- Article date: 2026-03-02
- Summary: Microsoft Entra Backup and Recovery でテナントの使用可能なバックアップを表示する方法 (バックアップの頻度、リテンション期間、次の手順など) について説明します

この記事では、Microsoft Entra Backup and Recovery でテナントの使用可能なバックアップを表示する方法について説明します。

Microsoft Entra バックアップは、サポートされているテナント オブジェクトとその属性のポイントインタイム ビューを提供します。 バックアップは、管理者が変更を確認し、偶発的または望ましくない変更から回復するのに役立ちます。

バックアップと回復の主な特性:

- **1 日に 1 回のバックアップ**: Microsoft Entra は、テナントに対して毎日 1 つのバックアップを自動的に作成します。
- **7 日間保持**: 各バックアップはタイムスタンプから最大 7 日間使用できます。
- **編集不可**: バックアップを変更または削除することはできません。

### 前提条件

テナントで使用可能なバックアップを表示するには、 **Microsoft Entra Backup Reader** ロールまたは高い特権ロールが必要です。

### バックアップの表示

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも **Microsoft Entra Backup Reader** としてサインインします。
2. **[バックアップと回復**] を参照します。 [ **概要]** ページには、機能の強調表示、アラート、最近のアクティビティが表示されます。

    [Image: Microsoft Entra 管理センターの [バックアップと回復の概要] ページのスクリーンショット。機能の強調表示とアラートが表示されています。]
3. [ **バックアップ]** を選択して、テナントで使用可能なバックアップの一覧を表示します。 各バックアップには、タイムスタンプとバックアップ ID が表示されます。

    [Image: タイムスタンプとバックアップ ID を含む 5 つの使用可能なバックアップの一覧を示す [バックアップ] ページのスクリーンショット。]

[ **バックアップ** ] ページで、バックアップを選択して [差分レポートを作成](https://learn.microsoft.com/ja-jp/entra/backup/create-review-difference-reports) するか [、復旧を開始します](https://learn.microsoft.com/ja-jp/entra/backup/recover-objects)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals"} -->
## Microsoft Entra の基礎に関するドキュメント - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals
- Service: entra / fundamentals
- Article date: 2025-08-25
- Summary: ユーザー、グループ、ライセンス、会社のブランド化、サブスクリプション、テナントその他、Microsoft Entra の基礎。

基本的な環境の作成、ユーザーの追加、ライセンスの適用、グループの管理など、Microsoft Entra の概念とプロセスについてご確認ください。

### Microsoft Entra について

#### 概要

- [Microsoft Entra とは](https://learn.microsoft.com/ja-jp/entra/fundamentals/what-is-entra)
- [Microsoft Entra 管理センターとは](https://learn.microsoft.com/ja-jp/entra/fundamentals/entra-admin-center)
- [Microsoft Entra の新機能](https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new)

#### 概念

- [ID およびアクセス管理](https://learn.microsoft.com/ja-jp/entra/fundamentals/identity-fundamental-concepts)
- [Microsoft Entra アーキテクチャ](https://learn.microsoft.com/ja-jp/entra/architecture/architecture)

### 概要

#### クイックスタート

- [ポータルにアクセスしてテナントを作成する](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant)
- [会社のブランドを追加する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding)
- [カスタム ドメイン名を追加する](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)

### ユーザーとグループ

#### 攻略ガイド

- [ユーザーを作成または削除する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)
- [ユーザーにロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-assign-roles-to-users)
- [グループとグループ メンバーシップを管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)

### ライセンス

#### 概念

- [Microsoft Entra ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing)

#### 攻略ガイド

- [Microsoft Entra ID P1 または P2 エディションにサインアップする](https://learn.microsoft.com/ja-jp/entra/fundamentals/get-started-premium)
- [ユーザーへのライセンスの割り当て](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing)

### Microsoft Entra のセキュリティに関するベスト プラクティス

#### リファレンス

- [セキュリティに関する推奨事項を構成する](https://learn.microsoft.com/ja-jp/entra/fundamentals/configure-security)

#### 概念

- [セキュリティ態勢を改善する](https://learn.microsoft.com/ja-jp/entra/fundamentals/configure-security)
- [ゼロ トラストによる ID のセキュリティ保護](https://learn.microsoft.com/ja-jp/security/zero-trust/deploy/identity)
- [セキュリティの既定値群](https://learn.microsoft.com/ja-jp/entra/fundamentals/security-defaults)

### Microsoft Security Copilot + Microsoft Entra

#### 概要

- [Microsoft Entra のセキュリティ コピロット](https://learn.microsoft.com/ja-jp/entra/security-copilot/security-copilot-in-entra)
- [Microsoft Entra のエージェント](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-agents)

#### 攻略ガイド

- [ID の脅威に対応する](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-risky-user-summarization)
- [インシデントの調査](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-incident)
- [アプリのリスクを調査する](https://learn.microsoft.com/ja-jp/entra/security-copilot/entra-investigate-risky-apps)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/add-custom-domain"} -->
## カスタム ドメインを追加する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain
- Service: entra / fundamentals
- Article date: 2026-06-18
- Summary: カスタム ドメイン名をテナントに追加する方法について説明します。

### 概要

Microsoft Entra テナントには、 `domainname.onmicrosoft.com`などの初期ドメイン名が付属しています。 初期ドメイン名を変更または削除することはできませんが、組織の DNS 名をカスタム ドメイン名として追加し、プライマリとして設定することはできます。 ドメイン名を追加することで、ユーザーになじみのあるユーザー名 ( `alain@contoso.com`など) を追加できます。

### [前提条件]

カスタム ドメイン名を追加する前に、ドメイン レジストラーを使用してドメイン名を作成します。 認定ドメイン レジストラーについては、「 [ICANN-Accredited レジストラー」](https://www.icann.org/registrar-reports/accredited-list.html)を参照してください。

### ディレクトリを作成する

ドメイン名を取得したら、最初のディレクトリを作成できます。 サブスクリプションの[所有者](https://portal.azure.com)ロールを持つアカウントを使用して、ディレクトリの [Azure portal](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner) にサインインします。

1. 「組織の新しいテナントを作成する」の手順に従って [、新しいディレクトリを作成します](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant#create-a-new-tenant-for-your-organization)。
2. 既定では、Microsoft Entra テナントを作成するユーザーには、 [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールが自動的に割り当てられます。

ヒント

オンプレミスの Windows Server Active Directory と Microsoft Entra ID をフェデレーションする場合は、Microsoft Entra Connect ツールを実行してディレクトリを同期するときに、 **ローカル Active Directory でシングル サインオンするようにこのドメインを構成** する予定です。

また、ウィザードの Microsoft Entra ドメイン ステップで、オンプレミス ディレクトリとのフェデレーション用に選択したのと同じ**ドメイン**名を追加して確認する必要があります。 そのセットアップの外観を確認するには、「 [フェデレーション用に選択されたドメインを確認する」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#verify-the-azure-ad-domain-selected-for-federation)を参照してください。 Microsoft Entra Connect ツールをお持ちでない場合は、Microsoft Entra Connect の [[管理](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted)] タブにある **Microsoft Entra 管理センター**から最新バージョンをダウンロードできます **。[作業の開始]** ページ。

### カスタム ドメイン名を追加する

ディレクトリを作成したら、カスタム ドメイン名を追加できます。

Important

ドメイン情報を更新するときに、プロセスを完了できず、HTTP 500 内部サーバー エラー メッセージが表示されることがあります。 状況によっては、このエラーが発生する可能性があります。 保護された DNS サフィックスを使用しようとすると、このメッセージが表示されることがあります。 保護された DNS サフィックスは、Microsoft のみが使用できます。 この操作が正常に完了している必要があると思われる場合は、Microsoft の担当者にお問い合わせください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ドメイン名管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#domain-name-administrator)としてサインインします。
2. **Entra ID**&gt;**ドメイン名**&gt;**カスタム ドメインの追加**に移動します。

    [Image: [カスタム ドメイン名] ページのスクリーンショット。[カスタム ドメインの追加] が表示されています。]
3. **[カスタム ドメイン名**] に、組織のドメインを入力します。この例では*、contoso.com*。 [ **ドメインの追加] を選択します**。

    [Image: [カスタム ドメイン名] ページのスクリーンショット。[カスタム ドメインの追加] ページがあります。]

Important

これを機能させるには、 *.com*、 *.net*、またはその他の最上位レベルの拡張機能を含める必要があります。 カスタム ドメインを追加すると、パスワード ポリシーの値が初期ドメインから継承されます。

1. 未確認のドメインが表示されます。 **contoso.com** ページに、ドメインの所有権を検証するために必要な DNS 情報が表示されます。 この情報を保存します。

    [Image: DNS エントリ情報を含む Contoso ページのスクリーンショット。]

### ドメイン レジストラーに DNS 情報を追加する

次の手順に従います。

1. カスタム ドメイン名を追加したら、ドメイン レジストラーに戻り、前の手順でコピーした DNS 情報を追加する必要があります。 ドメインに対してこの TXT または MX レコードを作成すると、ドメイン名の所有権が検証されます。
2. ドメイン レジストラーに戻り、コピーした DNS 情報に基づいて、ドメインの新しい TXT または MX レコードを作成します。 Time to Live (TTL) を 3600 秒 (60 分) に設定し、レコードを保存します。

Important

必要な数のカスタム ドメイン名を追加できます。 ただし、各ドメインは独自の TXT または MX レコードを取得します。 ドメイン レジストラーで情報を入力するときは注意してください。 誤って間違った情報または重複する情報を入力した場合は、TTL がタイムアウト (60 分) するまで待ってからやり直す必要があります。

### カスタム ドメイン名を確認する

ドメイン レジストラーで DNS レコードを追加した後、Microsoft Entraでカスタム ドメイン名を確認します。 伝達時間は、ドメイン レジストラーによっては瞬時に行われる場合や、数日かかる場合があります。

カスタム ドメイン名を確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ドメイン名管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#domain-name-administrator)としてサインインします。
2. **Entra ID**&gt;**ドメイン名**を参照します。
3. [ **カスタム ドメイン名**] で、カスタム ドメイン名を選択します。 この例では、contoso.com を選択 **します**。

    [Image: [Fabrikam - カスタム ドメイン名] ページのスクリーンショット。Contoso が強調表示されています。]
4. **contoso.com** ページで、[**確認**] を選択して、カスタム ドメインが正しく追加され、有効であることを確認します。

    [Image: DNS エントリ情報と [確認] ボタンを含む Contoso ページのスクリーンショット。]

### 一般的な検証の問題

カスタム ドメイン名を確認できない場合は、次の推奨事項をお試しください。

- **少なくとも 1 時間待ってから、もう一度やり直してください。** DNS レコードは、ドメインを確認する前に伝達する必要があります。 このプロセスには 1 時間以上かかることがあります。
- **DNS レコードが正しいことを確認します。** ドメイン名レジストラー サイトに戻ります。 エントリが存在し、Microsoft Entra 管理センターで提供されている DNS エントリ情報と一致していることを確認します。

    - レジストラー サイトでレコードを更新できない場合は、エントリを追加するアクセス許可を持つユーザーとエントリを共有し、正しいことを確認します。
- **ドメイン名がまだ別のディレクトリで使用されていないことを確認します。** ドメイン名は、1 つのディレクトリでのみ確認できます。 ドメイン名が現在別のディレクトリで検証されている場合は、新しいディレクトリでも確認できません。 この重複の問題を解決するには、古いディレクトリからドメイン名を削除する必要があります。 ドメイン名の削除の詳細については、「 [カスタム ドメイン名の管理](https://learn.microsoft.com/ja-jp/entra/identity/users/domains-manage)」を参照してください。
- **管理されていない Power BI テナントがないことを確認します。** ユーザーがセルフサービス サインアップを通じて Power BI をアクティブ化した場合、組織の管理されていないテナントが作成されている可能性があります。 PowerShell を使用して、内部管理者または外部管理者として管理を引き継ぐ必要があります。 詳細については、「 [アンマネージド ディレクトリを引き継ぐ」を](https://learn.microsoft.com/ja-jp/entra/identity/users/domains-admin-takeover)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/bulk-operations"} -->
## Microsoft Entra ID での一括操作 (プレビュー) - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/bulk-operations
- Service: entra / fundamentals
- Article date: 2026-06-18
- Summary: ユーザー、グループ、デバイスを管理するための新しい Microsoft Entra 一括操作エクスペリエンスについて説明します。

### 概要

Microsoft Entra ID の新しい一括操作エクスペリエンスでは、 **グループ**、 **デバイス、管理単位、ロールの割り当てを** 管理するための強化された機能が提供されます。このサービスでは、作成、更新、削除操作などの一括アクションが有効になります。 改善されたサービスにより、パフォーマンスが向上し、タイムアウトが削減され、大規模なテナントのスケーリング制限が削除されます。

注

現在、新しい一括操作サービスでは、 **グループ**、 **デバイス**、 **ユーザー** のエクスポート、 **管理単位、ロールの割り当て**のみがサポートされています。 **エンタープライズ アプリケーション**などの追加エンティティのサポートは、今後の更新プログラムで追加される予定です。 テンプレートのローカライズは部分的にサポートされています (エクスポートされた CSV にはローカライズ テンプレートはありませんが、インポートと削除がサポートされています)。 さらに、ゲスト ユーザーは一括操作を開始できません。 新しい一括操作サービスでは、非表示のメンバーシップのエクスポートはサポートされていません。

制限事項の詳細と、以前の一括操作エクスペリエンスの詳細については、「 [一括操作サービスの制限事項](https://learn.microsoft.com/ja-jp/entra/fundamentals/bulk-operations-service-limitations)」を参照してください。

### 一括ダウンロードグループ

組織内のすべてのグループをダウンロードするには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/#view/Microsoft_AAD_IAM/GroupsManagementMenuBlade)にサインインし、左側のナビゲーション ウィンドウで [**グループ**] タブを選択し、[**すべてのグループ**] を選択します。

    [Image: 列ヘッダーとアクションを含む [すべてのグループ] リストを示す Microsoft Entra 管理センターの [グループ] ブレードのスクリーンショット。]
2. [ **グループのダウンロード**] を選択します。

    [Image: ツールバーの [グループのダウンロード] ボタンが強調表示されている [グループ] ページのスクリーンショット。]
3. ファイル名を入力し、[ **一括操作の開始]** を選択します。

    [Image: 一括操作を開始する前にファイル名の入力を求める [グループのダウンロード] ダイアログのスクリーンショット。]
4. [ **ここをクリックして、各操作の状態を表示** する] リンクを選択して、[ **一括操作** ] ブレードに移動します。

    [Image: 一括グループのダウンロードが送信されたことを確認する成功通知のスクリーンショット。状態を表示するためのリンクが表示されています。]
5. ファイル名を選択して、指定した列を持つすべてのグループを含む CSV ファイルをダウンロードします。

### フィルター処理されたグループをダウンロードする

フィルター処理されたグループのサブセットをダウンロードするには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/#view/Microsoft_AAD_IAM/GroupsManagementMenuBlade)にサインインし、左側のナビゲーション ウィンドウで [**グループ**] タブを選択します。
2. [ **フィルターの追加]** を選択して、[ **フィルターの管理** ] パネルを開きます。 必要なフィルターを適用して、グループ リストを絞り込みます。 選択した列のみが CSV ファイルに表示されます。

    [Image: [グループ] ページの [フィルターの管理] パネルのスクリーンショット。フィルターが適用され、[グループのダウンロード] アクションが使用可能です。]
3. [ **グループのダウンロード**] を選択します。
4. 一括ダウンロード グループの手順 3 から 5 に従います。

### グループ メンバーを一括ダウンロードする

特定のグループのすべてのメンバーをダウンロードするには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/#view/Microsoft_AAD_IAM/GroupsManagementMenuBlade)にサインインし、左側のナビゲーション ウィンドウで [**グループ**] タブを選択します。
2. リストからグループを選択し、[ **メンバー** ] タブに移動します。

    [Image: 選択したグループの [メンバー] タブのスクリーンショット。ユーザーとサービス プリンシパルが一覧表示されています。]
3. [ **メンバー** ] ページのコマンド バーで、[ **メンバーのダウンロード**] を選択します。

    代わりに **一括操作** メニューが表示される場合は、 **一括操作**&gt;**メンバーのダウンロード**を選択します。
4. ファイル名を入力し、[ **一括操作の開始]** を選択します。
5. 「 一括ダウンロード グループ」の説明に従って、ダウンロード プロセスに従います。

### Microsoft Entra ID でグループ メンバーを一括インポートする

グループに複数のメンバーを追加するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/#view/Microsoft_AAD_IAM/GroupsManagementMenuBlade)にサインインし、左側のナビゲーション ウィンドウで [**グループ**] タブを選択します。
2. リストからグループを選択し、[ **メンバー** ] タブに移動します。
3. **一括操作**&gt;**メンバーをインポート**を選択します。

    [Image: [メンバー] タブの [一括操作] メニューのスクリーンショット。[メンバーのインポート] が選択されています。]
4. [ **CSV テンプレートのダウンロード** ] を選択して、正しい列ヘッダーを含むテンプレート ファイルを取得します。 テンプレートには、 `Member object ID or user principal name [memberObjectIdOrUpn] Required`という 1 つの列が含まれています。 例の行を削除し、インポートするメンバーのオブジェクト ID または UPN を行ごとに 1 つずつ追加します。 以下のいずれかを使用できます。

    - **オブジェクト ID**: ユーザーの GUID (たとえば、 `aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb`)
    - **ユーザー プリンシパル名: ユーザー**の UPN (たとえば、 `user@contoso.com`)

    [Image: メンバー ID を貼り付ける ObjectId 列を示す、メンバーをインポートするための CSV テンプレートのスクリーンショット。]
5. 完成した CSV ファイルをアップロードし、[ **送信]** を選択します。
6. ジョブ完了の通知を監視します。 **[成功]** リンクを選択して、操作の状態を表示します。

Important

アップロードした CSV ファイルに無効なオブジェクト ID を追加すると、一括操作の状態に **NotAllRowsSuccessfullyProcessed** という理由で**失敗と**表示されます。 ファイル名を選択すると、各オブジェクト ID の状態を示す詳細なレポートをダウンロードできます。

### グループ メンバーを一括削除する

グループから複数のメンバーを削除するには:

1. グループ メンバーを一括ダウンロードする手順 1 から 2 に従います。
2. **一括操作**&gt;**メンバーの削除**を選択します。

    [Image: [メンバー] タブの [一括操作] メニューのスクリーンショット。[メンバーの削除] が選択されています。]
3. [ **CSV テンプレートのダウンロード** ] を選択して、正しい列ヘッダーを含むテンプレート ファイルを取得します。 テンプレートには、 `Member object ID or user principal name [memberObjectIdOrUpn] Required`という 1 つの列が含まれています。 例の行を削除し、削除するメンバーのオブジェクト ID または UPN を行ごとに 1 つずつ追加します。 以下のいずれかを使用できます。

    - **オブジェクト ID**: ユーザーの GUID (たとえば、 `aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb`)
    - **ユーザー プリンシパル名: ユーザー**の UPN (たとえば、 `user@contoso.com`)
4. 完成した CSV ファイルをアップロードし、[ **送信]** を選択します。

    [Image: 処理する ObjectId 値を含むメンバーを削除するためのサンプル CSV のスクリーンショット。]
5. ジョブ完了の通知を監視します。 **[成功]** リンクを選択して、操作の状態を表示します。
6. 操作に **NotAllRowsSuccessfullyProcessed** という理由で**失敗した**状態が表示される場合は、ファイル名を選択して、各オブジェクト ID の状態を示す詳細なレポートをダウンロードします。
7. 指定したメンバーがグループから削除されたことを確認します。

### 一括ジョブの削除

完了または失敗した一括操作を削除するには:

1. [一括操作 (プレビュー)](https://entra.microsoft.com/?feature.tokencaching=true&amp;feature.internalgraphapiversion=true&amp;enableNewBulkJobsExport=true&amp;enableNewBulkJobsList=true#view/Microsoft_AAD_IAM/BulkJobsList.ReactView) ページに移動します。

    [Image: 状態、種類、タイムスタンプの列を含む最近の一括ジョブを一覧表示する [一括操作] ページのスクリーンショット。]
2. 削除する一括ジョブを選択します。
3. **を選択して、**を削除します。

    [Image: [一括操作] ページで選択した一括ジョブのスクリーンショット。[削除] ボタンが表示されています。]
4. 削除を確認します。 削除されたジョブが一覧から削除されます。

    [Image: 一括ジョブが正常に削除されたことを示す確認通知のスクリーンショット。]

### 端末シナリオの手順

1. [ **すべてのデバイス** ] ブレードに移動します。

    [Image: デバイスの一覧が表示されている Microsoft Entra 管理センターの [すべてのデバイス] ブレードのスクリーンショット。]
2. [ **デバイスのダウンロード**] を選択します。

    [Image: [一括操作] が開き、[デバイスのダウンロード] オプションが選択されている [すべてのデバイス] ページのスクリーンショット。]
3. 名前付け規則に一致するファイル名を入力し、[ **一括操作の開始]** を選択します。 [Image: デバイスのダウンロード ジョブを開始した後の成功通知のスクリーンショット。]
4. 通知メッセージを確認し、ジョブが正常に送信された場合は、[ **成功!** または **ファイルの準備完了] を選択します。ここをクリックしてリンクをダウンロード** してください。

    [Image: [一括操作] ページのスクリーンショット。完了した [デバイスのダウンロード] ジョブと [ファイルが準備完了] リンクが表示され、CSV をダウンロードできます。]
5. CSV ファイルをダウンロードするために作成した一括ジョブのファイル名を選択します。 一括ジョブの作成時に選択した列を含むすべてのデバイスが CSV に含まれていることを確認します。

[「Microsoft Entra 管理センターでユーザーの一覧をダウンロードする」の手順に従って、ユーザーを](https://learn.microsoft.com/ja-jp/entra/identity/users/users-bulk-download)一括エクスポートできます。

### 一括操作で管理単位にユーザーを追加する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **Entra ID**&gt;**Roles & admins**&gt;**Admin ユニット**に移動します。
3. ユーザーを追加する管理単位を選択します。
4. **ユーザー**&gt;**一括操作**&gt;**メンバーを一括追加** を選択します。

    [Image: ユーザーを一括操作として管理単位に割り当てるための [ユーザー] ページのスクリーンショット。]
5. [ **メンバーの一括追加** ] ウィンドウで、コンマ区切り値 (CSV) テンプレートをダウンロードします。 CSV を次のように更新して書式設定します。

    エントリ: 管理ユニットに追加するメンバーのオブジェクト ID または UPN。 必要に応じてファイルの名前を変更し、編集したファイルを選択してアップロードします。
6. アップロードが成功した後 **、[送信] を選択します** 。

    [Image: メンバーの一括追加送信画面のスクリーンショット。]
7. 通知メッセージを確認し、ジョブが正常に送信されたことを確認します。

    [Image: メンバーの一括追加操作の成功通知のスクリーンショット。]
8. [ **成功]** を選択して、一括ジョブの一覧に移動します。 作成時間で並べ替えた後、ジョブを検索してそれを選択し、ダウンロードできます。

    [Image: 完了した操作を示す一括ジョブの一覧のスクリーンショット。]

    [Image: 一括操作の結果をダウンロードするスクリーンショット。]

注

正しいオブジェクト ID が管理ユニットに正常に追加されたことを確認します。 必要に応じて UX を更新して、更新された状態を確認します。管理ユニットにグループを追加する場合は、特に反映に時間がかかります。

入力に無効なオブジェクト ID またはこの管理ユニットに既に割り当てられているオブジェクト ID が含まれている場合、すべての行が正常に処理されたわけではないため、一括操作の状態には "成功" ではなく "Failed" と表示されます。 結果 csv には、"要求の形式が正しくないか、無効なパラメーターが含まれています" というエラー理由が表示されます。 これは、オブジェクトがこの管理ユニットの事前操作に既に割り当てられているために発生する可能性が最も高いです。 他の有効な行は引き続き正常に処理されます。それに応じて確認してください。

### 一括操作で管理単位からユーザーを削除する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **Entra ID**&gt;**Roles & admins**&gt;**Admin ユニット**に移動します。
3. ユーザーを削除する管理単位を選択します。
4. **ユーザー**&gt;**一括操作**&gt;**一括削除メンバー**を選択します。

    [Image: [メンバーの一括削除] リンクを示す [ユーザー] ページのスクリーンショット。]
5. [ **メンバーの一括削除** ] ウィンドウで、コンマ区切り値 (CSV) テンプレートをダウンロードします。
6. テンプレートの最初の行は変更しないでください。削除するユーザー/デバイス/グループの objectID または UPN を各行に入力します。
7. 変更を保存し、CSV ファイルをアップロードします。
8. **送信**を選択します。

### ロールの割り当てのダウンロード

組み込みロールやカスタム ロールを含むすべてのロールでアクティブなロールの割り当てをダウンロードするには、次の手順に従います。

1. [ **ロールと管理者** ] ページで、[ **すべてのロール**] を選択します。
2. **[Download assignments] (割り当てのダウンロード)** を選択します。
3. [ **成功]** を選択して、一括ジョブの一覧に移動します。 作成時間で並べ替えた後、ジョブを検索してそれを選択し、ダウンロードできます。

    [Image: ロール割り当てのダウンロード用一括ジョブ一覧のスクリーンショット。]

    [Image: ロールの割り当ての結果のダウンロードのスクリーンショット。]
4. サンプル出力:

    [Image: ロールの割り当て CSV 出力のサンプルのスクリーンショット。]

注

フィルターと並べ替えは、このバルクタスクタイプ **ではサポートされていません**。このため、すべての役割の割り当てがダウンロードされます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/bulk-operations-service-limitations"} -->
## 一括操作サービスの制限事項 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/bulk-operations-service-limitations
- Service: entra / fundamentals
- Article date: 2025-12-05
- Summary: Microsoft Entra 管理ポータルでのユーザー、グループ、デバイスに関連する一括操作は、大規模なテナントではタイムアウトして失敗する可能性があることを学びます。

### 概要

Microsoft Entra ID での一括操作を使用すると、ユーザー、グループ、デバイスなどの複数のエンティティに対して一度にアクションを実行できます。 これらのアクションには、1 回の操作で複数のレコードを作成、削除、または更新することが含まれます。 一括操作により、管理タスクを大幅に効率化し、効率を向上させることができます。

Microsoft Entra 管理ポータルでの一括操作は、大規模なテナントでタイムアウトし、失敗する可能性があります。 この制限は、スケーリングの制限が原因である既知の問題です。

ヒント

新しい一括操作エクスペリエンスがプレビューで利用できるようになりました。これにより、パフォーマンスが向上し、大規模なテナントのスケーリングの制限が解除されます。 詳細については、「 [Microsoft Entra ID (プレビュー)の一括操作」を](https://learn.microsoft.com/ja-jp/entra/fundamentals/bulk-operations)参照してください。

注記

インポートや作成などの一括操作を実行するときに、一括操作が 1 時間以内に完了しない場合に問題が発生する可能性があります。 この問題を回避するには、バッチごとに処理されるレコードの数を分割します。 たとえば、エクスポートの開始前に、グループの種類またはユーザー名でフィルター処理して結果セットを制限し、結果のサイズを小さくすることができます。 フィルターを絞り込むことで、基本的に一括操作によって返されるデータが制限されます。

### 一括操作の対策

この問題の 1 つの回避策は、PowerShell を使用して Microsoft Graph API を直接呼び出すことです。 ユーザーとグループの一括ダウンロードエラーの場合は、PowerShell コマンドレットの `GET-MgGroup -All` と `GET-MgUser -All`を使用することをお勧めします。

以下の PowerShell コード例は、次のエンティティに関連する一括操作のためのものです。

- ユーザー
- グループ
- デバイス

### ユーザー

次のセクションでは、ユーザーの一括操作の制限について説明します。

#### すべてのユーザーをダウンロードする

```azurepowershell
# Import the Microsoft Graph module 
Import-Module Microsoft.Graph 

# Authenticate to Microsoft Graph (you may need to provide your credentials) 
Connect-MgGraph -Scopes "User.Read.All" 

# Get all users using Get-MgUser 
$users = Get-MgUser -All -ConsistencyLevel eventual -Property Id, DisplayName, UserPrincipalName,UserType,OnPremisesSyncEnabled,CompanyName,CreationType 

# Specify the output CSV file path 
$outputCsvPath = "C:\\Users\\YourUsername\\Documents\\Users.csv"  

# Create a custom object to store user data 
$userData = @() 

# Loop through each user and collect relevant data 
foreach ($user in $users) { 
    $userObject = [PSCustomObject]@{ 
        Id = $user.Id 
        DisplayName = $user.DisplayName 
        UserPrincipalName = $user.UserPrincipalName 
        UserType = $user.UserType 
        OnPremisesSyncEnabled = $user.OnPremisesSyncEnabled 
        CompanyName = $user.CompanyName 
        CreationType = $user.CreationType 
    } 
    $userData += $userObject 
} 

# Export user data to a CSV file 
$userData | Export-Csv -Path $outputCsvPath -NoTypeInformation 

# Disconnect from Microsoft Graph 
Disconnect-MgGraph 

Write-Host "User data exported to $outputCsvPath" 
```

#### ユーザーの作成

```azurepowershell
# Import the Microsoft Graph module 
Import-Module Microsoft.Graph 

# Authenticate to Microsoft Graph (you may need to provide your credentials) 
Connect-MgGraph -Scopes "User.ReadWrite.All" 

# Specify the path to the CSV file containing user data 
$csvFilePath = "C:\\Path\\To\\Your\\Users.csv" 

# Read the CSV file (adjust the column names as needed) 
$usersData = Import-Csv -Path $csvFilePath 

# Loop through each row in the CSV and create users \
foreach ($userRow in $usersData) { 
    $userParams = @{ 
        DisplayName = $userRow.'Name [displayName] Required' 
        UserPrincipalName = $userRow.'User name [userPrincipalName] Required' 
        PasswordProfile = @{ 
            Password = $userRow.'Initial password [passwordProfile] Required' 
        } 
        AccountEnabled = $true 
        MailNickName = $userRow.mailNickName 
    } 
    try { 
        New-MgUser @userParams 
        Write-Host "User $($userRow.UserPrincipalName) created successfully." 
    } catch { 
        Write-Host "Error creating user $($userRow.UserPrincipalName): $($_.Exception.Message)" 
    } 
} 

# Disconnect from Microsoft Graph 
Disconnect-MgGraph 

Write-Host "Bulk user creation completed." 
```

注記

CSV ファイルに必要な列 ( `DisplayName`、 `UserPrincipalName`など) が含まれていることを確認します。 また、CSV ファイル内の実際の列名と一致するようにスクリプトを調整します。

#### ユーザーの削除

```azurepowershell
# Import the Microsoft Graph module 
Import-Module Microsoft.Graph 

# Authenticate to Microsoft Graph (you may need to provide your credentials) 
Connect-MgGraph -Scopes "User.ReadWrite.All" 

# Specify the path to the CSV file containing user data 
$csvFilePath = "C:\\Path\\To\\Your\\Users.csv" 

# Read the CSV file (adjust the column names as needed) 
$usersData = Import-Csv -Path $csvFilePath 

# Loop through each row in the CSV and delete users 
foreach ($userRow in $usersData) { 
    try { 
        Remove-MgUser -UserId $userRow.UserPrincipalName -Confirm:$false 
        Write-Host "User $($userRow.UserPrincipalName) deleted successfully." 
    } catch { 
        Write-Host "Error deleting user $($userRow.UserPrincipalName): $($_.Exception.Message)" 
    } 
} 

# Disconnect from Microsoft Graph 
Disconnect-MgGraph 

Write-Host "Bulk user deletion completed." 
```

注記

必要な列 (たとえば、`UserPrincipalName`) が CSV ファイルに含まれていることを確認します。 また、CSV ファイル内の実際の列名と一致するようにスクリプトを調整します。

### グループ

次のセクションでは、グループの一括操作の制限について説明します。

#### すべてのグループを一括ダウンロードする

```azurepowershell
Import-Module Microsoft.Graph.Groups 

 # Authenticate to Microsoft Graph (you may need to provide your credentials) 
 Connect-MgGraph -Scopes "Group.Read.All" 

 # Get the group members 
 $groups = Get-MgGroup -All | Select displayName, Id, groupTypes,mail 

 # Create a custom object to store group data 
$groupData = @() 

# Loop through each group and collect relevant data 
foreach ($group in $groups) { 
    if ($group.groupTypes -contains "Unified"){$groupType = "Microsoft 365"} 
    else {$groupType = "Security"} 
    if ($group.groupTypes -contains "DynamicMembership"){$membershipType = "Dynamic"} 
    else {$membershipType = "Assigned"} 
    $groupObject = [PSCustomObject]@{ 
        Id = $group.Id 
        DisplayName = $group.displayName 
        Mail = $group.mail 
        GroupType = $groupType 
        MembershipType = $membershipType 
    }   
    $groupData += $groupObject 
} 

 # Specify the output CSV file path 
 $outputCsvPath = "C:\\Users\\<YourUsername>\\Documents\\Groups.csv" 

 $groupData| Export-Csv -Path $outputCsvPath -NoTypeInformation 
 
 Write-Host "Group members exported to $outputCsvPath" 
```

#### グループのメンバーを一括ダウンロードする

```azurepowershell
Import-Module Microsoft.Graph.Groups 

 # Authenticate to Microsoft Graph (you may need to provide your credentials) 
 Connect-MgGraph -Scopes "Group.Read.All,GroupMember.Read.All" 

 # Set the group ID of the group whose members you want to download 
 $groupId = "your_group_id" 

 # Get the group members 
 $members = Get-MgGroupMember -GroupId $groupId -All | select * -ExpandProperty additionalProperties | Select-Object @( 
                'id'     
                @{  Name       = 'userPrincipalName' 
                    Expression = { $_.AdditionalProperties["userPrincipalName"] } 
                } 
                @{  Name = 'displayName' 
                Expression = { $_.AdditionalProperties["displayName"] } 
                } 
            ) 

 # Specify the output CSV file path 
 $outputCsvPath = "C:\\Users\\YourUserName\\Documents\\GroupMembers.csv" 

 $members| Export-Csv -Path $outputCsvPath -NoTypeInformation 

# Disconnect from Microsoft Graph 
Disconnect-MgGraph 

 Write-Host "Group members exported to $outputCsvPath"  
```

#### メンバーを一括で追加する

```azurepowershell
Import-Module Microsoft.Graph.Groups 

 # Authenticate to Microsoft Graph (you may need to provide your credentials) 
 Connect-MgGraph -Scopes "GroupMember.ReadWrite.All" 

# Import the CSV file 
$members = Import-Csv -Path "C:\path\to\your\file.csv" 

# Define the Group ID 
$groupId = "your-group-id" 

# Iterate over each member and add them to the group 
foreach ($member in $members) { 
    try{ 
        New-MgGroupMember -GroupId $groupId -DirectoryObjectId $member.memberObjectId 
  	 Write-Host "Added $($member.memberObjectId) to the group."  
    } 
    Catch{ 
        Write-Host "Error adding member $($member.memberObjectId):$($_.Exception.Message)" 
    } 
} 

# Disconnect from Microsoft Graph 
Disconnect-MgGraph 
```

#### メンバーを一括で削除する

```azurepowershell
Import-Module Microsoft.Graph.Groups 

 # Authenticate to Microsoft Graph (you may need to provide your credentials) 
 Connect-MgGraph -Scopes "GroupMember.ReadWrite.All" 

# Import the CSV file 
$members = Import-Csv -Path "C:\path\to\your\file.csv" 

# Define the Group ID 
$groupId = "your-group-id" 

# Iterate over each member and remove them from the group
foreach ($member in $members) { 
    try{ 
        Remove-MgGroupMemberByRef -GroupId $groupId -DirectoryObjectId $member.memberObjectId \
        Write-Host "Removed $($member.memberObjectId) from the group." 
    } 
    Catch{ 
        Write-Host "Error removing member $($member.memberObjectId):$($_.Exception.Message)" 
    } 
} 

# Disconnect from Microsoft Graph 
Disconnect-MgGraph 
```

### デバイス

次のセクションでは、デバイスの一括操作の制限について説明します。

#### すべてのデバイスを一括ダウンロードする

```azurepowershell
Import-Module Microsoft.Graph 

 # Authenticate to Microsoft Graph (you may need to provide your credentials) 
 Connect-MgGraph -Scopes "Device.Read.All" 

 # Get all devices  
 $devices = Get-MgDevice -All |select displayName,deviceId,operatingSystem,operatingSystemVersion,isManaged,isCompliant,mdmAppId,registeredOwners,TrustType 

 # Specify the output CSV file path 
 $outputCsvPath = "C:\\Users\\YourUserName\\Documents\\Devices.csv" 

 $devices| Export-Csv -Path $outputCsvPath -NoTypeInformation 

 Write-Host "Devices exported to $outputCsvPath"  
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/compare"} -->
## Active Directory と Microsoft Entra ID との比較 - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/compare
- Service: entra / fundamentals
- Article date: 2022-08-17
- Summary: このドキュメントでは、Active Directory Domain Services (AD DS) と Microsoft Entra ID を比較します。 両方の ID ソリューションでの主な概念を示し、相違点や類似点について説明します。

Microsoft Entra ID は、クラウドに対する次世代の ID およびアクセス管理のソリューションです。 Microsoft では、ユーザーごとに 1 つの ID を使用して複数のオンプレミス インフラストラクチャ コンポーネントとシステムを管理する機能を組織に提供するために、Windows 2000 に Active Directory Domain Services を導入しました。

Microsoft Entra ID ではこのアプローチを、クラウドとオンプレミス全体のすべてのアプリに対するサービスとしての ID (IDaaS) ソリューションを組織に提供することで、次のレベルに引き上げます。

ほとんどの IT 管理者は、Active Directory Domain Services の概念を理解しています。 次の表に、Active Directory の概念と Microsoft Entra ID 間の相違点と類似点を示します。

| 概念 | Windows Server Active Directory | Microsoft Entra ID |
| --- | --- | --- |
| **ユーザー** |  |  |
| プロビジョニング: ユーザー | 組織では、手動で内部ユーザーを作成するか、Microsoft Identity Manager などの社内または自動のプロビジョニング システムを使用して、HR システムと統合します。 | 既存の Microsoft Windows Server Active Directory 組織は [、Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis) を使用して ID をクラウドに同期します。  Microsoft Entra ID では、[クラウド人事システム](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/what-is-hr-driven-provisioning)からユーザーを自動的に作成するためのサポートが追加されています。 Microsoft Entra ID は、クロスドメイン アイデンティティ管理 (SCIM) に対応したサービスとしてのソフトウェア [(SaaS) アプリ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups) に ID をプロビジョニングし、ユーザーのアクセス許可に必要な詳細をアプリに自動的に提供できます。 |
| プロビジョニング: 外部 ID | 組織は、外部ユーザーを専用の外部 Microsoft Windows Server Active Directory フォレストに通常のユーザーとして手動で作成します。その結果、外部 ID (ゲスト ユーザー) のライフサイクルを管理するための管理オーバーヘッドが発生します。 | Microsoft Entra ID では、外部 ID をサポートするための特殊な ID クラスが提供されています。 [Microsoft Entra B2B](https://learn.microsoft.com/ja-jp/entra/external-id/) では、外部ユーザーの ID へのリンクを管理して、それらの有効性を確保します。 |
| エンタイトルメントの管理とグループ | 管理者は、ユーザーをグループのメンバーにすることができます。 アプリとリソースの所有者は、アプリまたはリソースへのアクセス権をグループに付与します。 | また、[グループ](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)は Microsoft Entra ID でも使用でき、管理者はグループを使用してリソースへのアクセス許可を付与することもできますす。 Microsoft Entra ID では、管理者はグループにメンバーシップを手動で割り当てるか、またはクエリを使用してユーザーをグループに動的に含めることができます。  管理者は、Microsoft Entra ID にある[エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)を使用し、ワークフローと (必要な場合は) 時間ベースの条件を使って、アプリとリソースのコレクションへのアクセス権をユーザーに付与できます。 |
| 管理者の管理 | 組織は、Microsoft Windows Server Active Directory のドメイン、組織単位、グループの組み合わせを使用して、管理するディレクトリとリソースを管理するための管理者権限を委任します。 | Microsoft Entra ID は、Microsoft Entra のロールベースのアクセス制御 (RBAC) システムが提供する[組み込みロール](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-subscriptions-associated-directory)が用意されており、ID システム、アプリ、および制御するリソースへの特権アクセスを委任するための[カスタム ロール作成](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview)のサポートは制限されています。[Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) を使用することで、ロールの管理を強化し、必要に応じたオンデマンド、時間制限付き、またはワークフローに基づくアクセスを特権ロールに提供することができます。 |
| 資格情報の管理 | Active Directory の資格情報は、パスワード、証明書認証、スマート カード認証に基づいています。 パスワードは、パスワードの長さ、有効期限、および複雑さに基づくパスワード ポリシーを使用して、管理されます。 | Microsoft Entra ID では、クラウドとオンプレミスに対してインテリジェントな[パスワード保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad)を使用します。 保護には、スマート ロックアウトに加えて、共通およびカスタムのパスワード フレーズと代替のブロック機能が含まれます。 Microsoft Entra ID は、FIDO2 などの [多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) と [パスワードレス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2) テクノロジによってセキュリティを大幅に強化します。 Microsoft Entra ID では、ユーザーに[セルフサービス パスワード リセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks)のシステムを提供することで、サポートのコストを削減しています。 |
| **アプリ** |  |  |
| インフラストラクチャ アプリ | Active Directory は、DNS、動的ホスト構成プロトコル (DHCP)、インターネット プロトコル セキュリティ (IPSec)、WiFi、NPS、VPN アクセスなど、オンプレミスの多くのインフラストラクチャ コンポーネントの基礎となります | 新しいクラウド環境では、Microsoft Entra ID は、アプリにアクセスするためと、ネットワーク コントロールに依存するための新しいコントロール プレーンです。 ユーザーが認証を行うときに、[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) では、必要な条件下でどのユーザーがどのアプリへのアクセス権を持つかを制御します。 |
| 従来のアプリとレガシ アプリ | ほとんどのオンプレミス アプリでは、LDAP、Windows 統合認証 (NTLM と Kerberos)、またはヘッダーベースの認証を使用して、ユーザーへのアクセスを制御します。 | Microsoft Entra ID では、オンプレミスで実行されている [Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy) エージェントを使用して、これらの種類のオンプレミス アプリへのアクセスを提供できます。 この方法を利用して、Microsoft Entra ID では、移行しているとき、またはレガシ アプリと共存する必要があるときに、Kerberos を使ってオンプレミスで Active Directory ユーザーを認証できます。 |
| SaaS アプリ | Active Directory では、SaaS アプリがネイティブでサポートされず、AD FS などのフェデレーション システムを必要とします。 | OAuth2、Security Assertion Markup Language (SAML)、および WS-\* 認証をサポートする SaaS アプリを統合して、認証に Microsoft Entra ID を使用できます。 |
| 先進認証を使用した基幹業務 (LOB) アプリ | 組織では Active Directory と共に AD FS を使用して、先進認証を必要とする LOB アプリをサポートできます。 | 先進認証を必要とする LOB アプリは、認証に Microsoft Entra ID を使用するように構成できます。 |
| 中間層/デーモン サービス | オンプレミス環境で実行されているサービスは、通常、Microsoft Windows Server Active Directory サービス アカウントまたはグループ管理サービス アカウント (gMSA) を使用して実行します。 これらのアプリでは、サービス アカウントのアクセス許可を継承します。 | Microsoft Entra ID には、クラウド内の他のワークロードを実行するための[マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/) が用意されています。 これらの ID のライフサイクルは Microsoft Entra ID によって管理され、リソース プロバイダーに関連付けられているため、他の目的でバックドア アクセスを取得するために使用することはできません。 |
| **デバイス** |  |  |
| モバイル | Active Directory では、サードパーティのソリューションを使用しないモバイル デバイスはネイティブにサポートされていません。 | Microsoft のモバイル デバイス管理ソリューションである Microsoft Intune は、Microsoft Entra ID と統合されています。 Microsoft Intune では、認証中に評価するために、ID システムにデバイスの状態情報を提供しています。 |
| Windows デスクトップ | Active Directory では、グループ ポリシー、System Center Configuration Manager、またはその他のサードパーティのソリューションを使用して Windows デバイスを管理するために、デバイスをドメインに参加させる機能を提供しています。 | Windows デバイスを、[Microsoft Entra ID に参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/)させることができます。 条件付きアクセスでは、認証プロセスの一部としてデバイスが Microsoft Entra に参加しているかどうかの確認を行うことができます。 また、Windows デバイスは、[Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune) を使って管理することもできます。 この場合、条件付きアクセスでは、アプリへのアクセスを許可する前に、デバイスが準拠しているかどうか (最新のセキュリティ更新プログラムやウイルス署名など) の確認を行います。 |
| Windows サーバー | Active Directory では、グループ ポリシーまたはその他の管理ソリューションを使用して、オンプレミスの Windows サーバーに強固な管理機能を提供します。 | Azure にある Windows サーバー仮想マシンは、[Microsoft Entra Domain Services](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/)を使って管理できます。 [マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/) は、VM が ID システム ディレクトリまたはリソースにアクセスする必要がある場合に使用できます。 |
| Linux/Unix ワークロード | Active Directory では、サードパーティのソリューションを使用しない Windows 以外はネイティブでサポートされません。ただし、Active Directory を使って Kerberos 領域として認証するように Linux コンピューターを構成することはできます。 | Linux/Unix VM では、ID システムまたはリソースにアクセスするために、[マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/) を使用できます。 組織によっては、これらのワークロードをマネージド ID も使用できるクラウド コンテナー テクノロジに移行します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/concept-learn-about-groups"} -->
## グループ、グループ メンバーシップ、およびアクセスについて説明します - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/concept-learn-about-groups
- Service: entra / fundamentals
- Article date: 2026-05-27
- Summary: Microsoft Entra グループの動作、アクセスできる内容、メンバーシップとアクセスの割り当て方法などについて説明します。

Microsoft Entra ID には、リソース、アプリケーション、タスクへのアクセスを管理する方法がいくつか用意されています。 Microsoft Entra グループを使用すると、個々のユーザーではなく、ユーザーのグループにアクセスとアクセス許可を付与できます。 アクセスを必要とするユーザーのみに Microsoft Entra リソースへのアクセスを制限することは、[ゼロ トラスト](https://learn.microsoft.com/ja-jp/security/zero-trust/zero-trust-overview)の中核的なセキュリティ原則の 1 つです。

この記事では、セキュリティのベスト プラクティスを適用しながら、Microsoft Entra ユーザーの管理を容易にするためにグループとアクセス権を組み合わせて使用する方法の概要について説明します。

注

Azure ポータルまたは Microsoft Entra 管理センター.

- オンプレミスの Active Directory から同期されたグループは、オンプレミスでのみ管理できます。
- 配布リストとメールが有効なセキュリティ グループは、[Exchange 管理センター](https://admin.cloud.microsoft/exchange#/groups) または [Microsoft 365 管理センター](https://admin.microsoft.com/Adminportal/Home?#/groups)でのみ管理できます。 これらのグループを管理するには、サインインし、その管理センターに適切なアクセス許可を持っている必要があります。

### Microsoft Entra グループの概要

グループを効果的に使用すると、ロールとアクセス許可を個々のユーザーに割り当てるなどの手動タスクを減らすことができます。 役割をグループに割り当て、その職務または部署に基づいてメンバーをグループに割り当てることができます。 グループに適用される条件付きアクセス ポリシーを作成し、そのポリシーをグループに割り当てることができます。 グループの使用の可能性があるため、そのしくみと管理方法を理解することが重要です。

#### グループの種類

Microsoft Entra 管理センターでは、次の 2 種類のグループを管理できます。

- **セキュリティ グループ**: 共有リソースへのアクセスを管理するために使用されます。

    - セキュリティ グループのメンバーには、ユーザー、デバイス、[サービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-principal)含めることができます。
    - グループは、他のグループのメンバー (入れ子になったグループとも呼ばれます) にすることができます。 *注を参照してください。*
    - ユーザーとサービス プリンシパルは、セキュリティ グループの所有者にすることができます。
- **Microsoft 365 グループ**: コラボレーションの機会を提供します。

    - Microsoft 365 グループのメンバーには、ユーザーのみを含めることができます。
    - ユーザーとサービス プリンシパルは、Microsoft 365 グループを所有できます。
    - 組織外のユーザーは、グループのメンバーにすることができます。
    - 詳細については、「[Microsoft 365 グループの詳細](https://support.office.com/article/learn-about-office-365-groups-b565caa1-5c40-40ef-9915-60fdb2d97fa2)」を参照してください。

注

既存のセキュリティ グループを別のセキュリティ グループに入れ子にすると、親グループ内のメンバーのみが共有リソースとアプリケーションにアクセスできます。 入れ子グループを管理する方法の詳細については、「[グループの管理方法](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups#add-a-group-to-another-group)」を参照してください。

#### メンバーシップの種類

- **割り当てられたグループ**: 特定のユーザーをグループのメンバーとして追加し、一意のアクセス許可を持つことができます。
- **ユーザーの動的メンバーシップ グループ**: ルールを使用して、ユーザーをメンバーとして自動的に追加および削除できます。 メンバーの属性が変更された場合、システムはディレクトリに対する動的メンバーシップ グループのルールを調べます。 メンバーがルール要件を満たす (追加される) か、またはルール要件を満たしていない (削除される) かどうかを確認します。
- **デバイスの動的メンバーシップ グループ**: ルールを使用して、デバイスをメンバーとして自動的に追加および削除できます。 デバイスの属性が変更されると、システムは、ディレクトリに対する動的メンバーシップ グループのルールを調べて、そのデバイスがルール要件を満たしているか (追加される)、またはルール要件を満たさなくなったか (削除される) を確認します。

重要

デバイスまたはユーザーのどちらかに対して動的グループを作成することは可能ですが、両方に対して作成することはできません。 デバイス所有者の属性に基づいてデバイス グループを作成することはできません。 デバイス メンバーシップ ルールで参照できるのは、デバイスの属性のみです。 詳細については、「[動的グループ](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule)の作成」を参照してください。

### アクセス管理

Microsoft Entra ID は、1 人のユーザーまたはグループにアクセス権を付与することで、組織のリソースへのアクセス権を付与するのに役立ちます。 グループを使用すると、リソース所有者または Microsoft Entra ディレクトリ所有者は、グループのすべてのメンバーにアクセス許可のセットを割り当てることができます。 リソースまたはディレクトリの所有者は、部門マネージャーやヘルプ デスク管理者などのユーザーにグループ管理権限を付与することもできます。これにより、そのユーザーはメンバーを追加および削除できます。 グループ所有者を管理する方法の詳細については、[グループの管理](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)に関する記事を参照してください。

Microsoft Entra グループがアクセスを管理できるリソースは次のとおりです。

- ユーザー、アプリケーション、課金、およびその他のオブジェクトを管理するためのアクセス許可など、Microsoft Entra 組織の一部。
- Microsoft 以外の SaaS アプリなど、組織の外部のもの。
- Azure サービス。
- SharePoint サイト。
- オンプレミス のリソース。

アクセス許可を必要とするアプリケーション、リソース、サービスは個別に管理する必要があります。これは、それぞれのアクセス許可が同じでない場合があるからです。 [最小限の特権の原則](https://learn.microsoft.com/ja-jp/entra/identity-platform/secure-least-privileged-access)を使ってアクセスを付与することで、攻撃やセキュリティ侵害のリスクを軽減することができます。

#### 割り当ての種類

グループを作成したら、そのアクセスを管理する方法を決定する必要があります。

- **直接割り当て**: リソース所有者は、ユーザーをリソースに直接割り当てます。
- **グループの割り当て。** リソース所有者は、Microsoft Entra グループをリソースに割り当てます。これにより、グループ メンバー全員に、リソースへのアクセスが自動的に与えられます。 グループ所有者とリソース所有者の両方がグループのメンバーシップを管理し、どちらの所有者も、グループのメンバーを追加したり、削除したりできます。 グループ メンバーシップの管理の詳細については、「[グループの管理](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)」の記事をご覧ください。
- **ルールベースの割り当て**: リソース所有者はグループを作成し、ルールを使用して特定のリソースに割り当てるユーザーを定義します。 ルールは、個々のユーザーに割り当てられている属性に基づきます。 リソース所有者は、リソースへのアクセスを許可するためにはどの属性と値が必要であるかを判断し、ルールを管理します。 詳細については、「[動的グループ](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule)の作成」を参照してください。
- **外部機関の割り当て**: アクセスは、オンプレミスのディレクトリや SaaS アプリなどの外部ソースから取得されます。 この状況においては、リソース所有者がリソースへのアクセス権を提供するためのグループを割り当ててから、外部ソースがグループのメンバーを管理します。

### クラウドでグループを管理するためのベスト プラクティス

クラウドでグループを管理するためのベスト プラクティスを次に示します。

- **セルフサービス グループ管理を有効にする**: ユーザーがグループを検索して参加したり、独自のMicrosoft 365 グループを作成および管理したりできます。
    - IT に対する管理上の負担を軽減しながら、チームが自分自身を整理できるようにします。
    - *グループの名前付けポリシー* を適用して、制限付き単語の使用をブロックし、一貫性を確保します。
    - グループ所有者によって更新されない限り、指定した期間を過ぎると未使用のグループが自動的に削除されるグループの有効期限ポリシーを有効にすることで、非アクティブなグループが残らないようにします。
    - 参加または承認が必要なすべてのユーザーを自動的に受け入れるようにグループを構成します。
    - 詳細については、「[Microsoft Entra ID でのセルフサービス グループ管理の設定](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management)」をご覧ください。
- **秘密度ラベル**: 秘密度ラベルを使用して、セキュリティとコンプライアンスのニーズに基づいてMicrosoft 365 グループを分類および管理します。
    - きめ細かいアクセス制御を提供し、機密性の高いリソースが確実に保護されるようにします。
    - 詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-assign-sensitivity-labels) で Microsoft 365 グループに秘密度ラベルを割り当てる」を参照してください。
- **動的グループを使用してメンバーシップを自動化**する: 動的メンバーシップ ルールを実装して、部門、場所、役職などの属性に基づいてグループに対してユーザーとデバイスを自動的に追加または削除します。
    - 手動更新を最小限に抑え、アクセスが残留するリスクを軽減します。
    - この機能は、Microsoft 365 グループとセキュリティ グループに適用されます。
- **定期的なアクセス レビューの実施**: Microsoft Entra ID ガバナンス機能を使用して、定期的なアクセス レビューをスケジュールします。
    - 割り当てられたグループのメンバーシップが、時間の経過と同時に正確で関連性の高いままであることを確認します。
    - 詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule) で動的メンバーシップ グループを作成または更新する」を参照してください。
- **アクセス パッケージを含む管理メンバーシップ**: 複数のグループ メンバーシップの管理を効率化するために、Microsoft Entra ID ガバナンスを使用してアクセス パッケージを作成します。 アクセス パッケージは次のことができます。
    - メンバーシップの承認ワークフローを含める
    - アクセスの有効期限の条件を定義する
    - グループとアプリケーション間でアクセスを許可、確認、取り消す一元化された方法を提供する
    - 詳細については、「[エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create) でのアクセス パッケージの作成」を参照してください。
- **複数のグループ所有者を割り当てる**: グループに少なくとも 2 人の所有者を割り当てて、継続性を確保し、1 人の個人に対する依存関係を減らします。
    - 詳細については、「[Microsoft Entra グループとグループ メンバーシップの管理](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups) を参照してください。
- **グループベースのライセンスを使用する**: グループベースのライセンスにより、ユーザー プロビジョニングが簡素化され、一貫性のあるライセンス割り当てが保証されます。
    - 動的メンバーシップ グループを使用して、特定の条件を満たすユーザーのライセンスを自動的に管理します。
    - 詳細については、[Microsoft 365 管理センターのグループへのライセンスの割り当てまたは割り当て解除を](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true)参照してください。
- **ロールベースのアクセス制御 (RBAC) の適用**: グループを管理できるユーザーを制御するロールを割り当てます。
    - RBAC を使用すると、特権の悪用のリスクが軽減され、グループ管理が簡素化されます。
    - 詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview) でのロールベースのアクセス制御の概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/concept-license-usage-insights"} -->
## Microsoft Entraライセンスの使用状況インサイト - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/concept-license-usage-insights
- Service: entra / fundamentals
- Article date: 2026-04-15
- Summary: Microsoft Entra 管理センターのライセンス使用状況分析情報ページを使用して、ライセンスの使用状況と権利を監視する方法について説明します。

### 概要

Microsoft Entra 管理センターの [ライセンスの使用状況] ページは、テナント全体の機能の使用状況を可視化することで、Microsoft Entra ライセンスを最適化するのに役立ちます。 このページには、所有している Microsoft Entra ID P1、P2、Suite ライセンスの数と、各ライセンスの種類にマップされた主要な機能の使用状況が表示されます。

このビューでは、ライセンス数、Microsoft Entra ライセンスから取得している値、テナント内での過剰使用の可能性をより明確に把握できます。

### 前提条件

- テナントには、有料のMicrosoft Entra ライセンスが必要です (P1、P2、Microsoft Entra スイート Microsoft Entra IDなど)。
- この機能は、パブリック クラウドでのみ使用できます。
- 最小特権ロールは [レポート閲覧者です](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)。
    - このページには、 [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、 [グローバル閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)、 [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)、 [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)、 [セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator)のロールもアクセスできます。

### ライセンス使用状況の分析情報ページにアクセスする

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[請求]**&gt;**[ライセンス]** に移動します。

[ [ライセンスの使用状況](https://entra.microsoft.com/#view/Microsoft_AAD_IAM/LicensesMenuBlade/%7E/LicenseUtilization) ] ページに直接移動することもできます。

[Image: ライセンスの権利と製品の使用状況の分析情報を示すMicrosoft Entra 管理センターの [ライセンスの使用状況] ページのスクリーンショット。]

### ライセンス権利

[ライセンスの権利] セクションには、現在の月に購入したライセンスの数が表示されます。 その他の月の金額は、使用許諾契約書の更新、更新以外、または追加購入に基づいて変更される可能性があります。 エンタイトルメントは、購入したすべてのMicrosoft Entra製品を調べることによって計算されます。これには次のものが含まれます。

- Microsoft Entra ID P1
- Microsoft Entra ID P2
- Microsoft Entra スイート
- Microsoft Entra ID ガバナンス
- Microsoft Entra 確認済み ID
- マイクロソフト エントラ プライベート アクセス (Microsoft Entra Private Access)
- マイクロソフト エントラ インターネット アクセス

エンタイトルメント数は、Microsoft Entra ID機能の各レベルを含むすべての製品のライセンスの合計数を反映します。

### 製品の使用状況に関する分析情報

**Product usage insights** セクションは、ライセンスの使用状況を監視してMicrosoft Entraプランを最大化するのに役立ちます。 データには、権利を持つライセンスに基づいて先月の機能の使用状況が表示され、更新には最大 3 日かかる場合があります。

ライセンス使用状況分析情報ページでは、ライセンスレベルごとに 1 つの代表的なメトリック (ヒーロー メトリック) を使用して使用状況を測定します。 このアプローチでは、有料機能導入の最も意味のあるインジケーターに焦点を当てることで、ビューを簡略化します。

このセクションは、次の 2 つのタブに分かれています。

- **Entra ID** — Microsoft Entra ID P1 機能の使用状況メトリックを表示します。
- **ID Protection** — Microsoft Entra ID P2 機能の使用状況メトリックを表示します。

#### Microsoft Entra ID P1 の使用

**Entra ID** タブでは、主要メトリックは **Conditional Access users** — 測定期間中に少なくとも 1 つの条件付きアクセス ポリシーが評価された一意のユーザーの数です。

#### Microsoft Entra ID P2 の使用

[ **ID 保護** ] タブの主要なメトリックは **、リスクベースの条件付きアクセス ユーザー** です。これは、測定期間中に評価されたリスクベースの条件付きアクセス ポリシーが 1 つ以上ある一意のユーザーの数です。

#### 機能の使用状況レポート

各タブには、前月のテナントの機能使用状況を示す機能使用状況レポートが含まれています。 レポートには、使用状況と各メトリックの権利を比較する棒グラフが表示されます。これは、ライセンスの合計に対する割合で表されます。

このグラフでは、次のインジケーターが使用されます。

- **使用されたライセンス** — アクティブな使用によって消費される権利の部分。
- **ライセンスが使用されていません** — 残りの権利は使用されません。
- **使用量の急増** - ライセンスの数を超える使用量。

#### 毎月の使用パターン

[ **月単位の使用パターン** ] パネルには、過去 6 か月間のテナント機能の使用状況が表示されます。 **アクティブ ユーザー** ビューと**ゲスト ユーザー** ビューを切り替えて、ユーザーの種類ごとの傾向を確認できます。 このグラフでは、時間の経過に伴う資格のあるライセンス数と機能の使用状況が比較され、使用状況の傾向を特定し、将来のライセンス ニーズを計画するのに役立ちます。

#### アクティブユーザーとゲストユーザーの区別

使用状況メトリックは、アクティブなユーザーとゲスト ユーザーを区別します。 この違いは、ライセンス消費のどの部分が内部ユーザーと外部コラボレーターから得られたかを理解するのに役立ちます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/configure-security"} -->
## セキュリティを強化するためにMicrosoft Entraを構成する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/configure-security
- Service: entra / fundamentals
- Article date: 2026-04-30
- Summary: Microsoft Entraを使用してセキュリティ体制を改善する方法について説明します。

Microsoft Entraでは、セキュリティに関する推奨事項を、Secure Future Initiative (SFI) に基づいて複数のテーマにグループ化します。 この構造により、組織は論理的にプロジェクトを関連する消費型チャンクに分割できます。

Tip

一部の組織では、これらの推奨事項が書かれたとおりに行われる場合もあれば、独自のビジネス ニーズに基づいて変更を行う場合もあります。 このガイダンスの最初のリリースでは、従来の [従業員テナント](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#workforce-tenants)に焦点を当てています。 これらの従業員テナントは、従業員、内部ビジネス アプリ、およびその他の組織リソース用です。

ライセンスが利用可能な場合は、次のすべてのコントロールを実装することをお勧めします。 これらのパターンとプラクティスは、このソリューションに基づいて構築された他のリソースの基盤を提供するのに役立ちます。 このドキュメントには、時間の経過に伴ってさらに多くのコントロールが追加されます。

### 自動評価

テナントの構成に対してこのガイダンスを手動で確認すると、時間がかかり、エラーが発生しやすくなります。 ゼロ トラスト評価では、自動化によってこのプロセスが変換され、これらのセキュリティ構成項目などをテストします。 詳細については、「ゼロ トラスト Assessment とは」を参照してください>

### ID とシークレットを保護する

最新の ID 標準を実装することで、資格情報関連のリスクを軽減します。

| 確認 | 最低限必要なライセンス |
| --- | --- |
| [アプリケーションにクライアント シークレットが構成されていない](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#applications-dont-have-client-secrets-configured) | なし (Microsoft Entra IDに含まれます) |
| [サービス プリンシパルに証明書または資格情報が関連付けられていない](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#service-principals-dont-have-certificates-or-credentials-associated-with-them) | なし (Microsoft Entra IDに含まれます) |
| [アプリケーションの有効期限が 180 日を超える証明書がない](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#applications-dont-have-certificates-with-expiration-longer-than-180-days) | なし (Microsoft Entra IDに含まれます) |
| [アプリケーション証明書は定期的にローテーションする必要があります](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#application-certificates-must-be-rotated-on-a-regular-basis) | なし (Microsoft Entra IDに含まれます) |
| [アプリ シークレットと証明書の標準を適用する](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#enforce-standards-for-app-secrets-and-certificates) | なし (Microsoft Entra IDに含まれます) |
| [Microsoft サービス アプリケーションに資格情報が構成されていない](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#microsoft-services-applications-dont-have-credentials-configured) | なし (Microsoft Entra IDに含まれます) |
| [ユーザーの同意設定が制限される](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#user-consent-settings-are-restricted) | なし (Microsoft Entra IDに含まれます) |
| [管理者の同意ワークフローが有効になっている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#admin-consent-workflow-is-enabled) | なし (Microsoft Entra IDに含まれます) |
| [特権ユーザー比率に対する高Global Administrator](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#high-global-administrator-to-privileged-user-ratio) | なし (Microsoft Entra IDに含まれます) |
| [セキュリティ侵害を防ぐために、管理者特権が厳密に制限されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#administrative-privileges-are-tightly-limited-to-prevent-compromise) | Microsoft Entra ID P1 |
| [アプリケーション管理者権限は、特定のプライベート Access アプリに制限されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#application-admin-rights-are-constrained-to-specific-private-access-apps) | Microsoft Entra Internet Access または Microsoft Entra Private Access |
| [特権アカウントはクラウド ネイティブ ID です](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#privileged-accounts-are-cloud-native-identities) | なし (Microsoft Entra IDに含まれます) |
| [すべての特権ロールの割り当ては、期限内にアクティブになり、永続的にはアクティブになりません](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#all-privileged-role-assignments-are-activated-just-in-time-and-not-permanently-active) | Microsoft Entra ID P2 |
| [すべてのMicrosoft Entra特権ロールの割り当ては PIM で管理されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#all-microsoft-entra-privileged-role-assignments-are-managed-with-pim) | Microsoft Entra ID P2 |
| [Passkey 認証方法が有効になっている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#passkey-authentication-method-enabled) | なし (Microsoft Entra IDに含まれます) |
| [セキュリティ キーの構成証明が適用される](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#security-key-attestation-is-enforced) | なし (Microsoft Entra IDに含まれます) |
| [特権アカウントには、フィッシングに対する耐性のある方法が登録されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#privileged-accounts-have-phishing-resistant-methods-registered) | Microsoft Entra ID P1 |
| [Privileged Microsoft Entra 組み込みロールは、フィッシング詐欺に強い方法を適用するために条件付きAccess ポリシーを対象とします](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#privileged-microsoft-entra-built-in-roles-are-targeted-with-conditional-access-policies-to-enforce-phishing-resistant-methods) | Microsoft Entra ID P1 |
| [Conditional Access ポリシーでは、プライベート アプリに対して強力な認証が適用されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#conditional-access-policies-enforce-strong-authentication-for-private-apps) | Microsoft Entra プライベートアクセス |
| アプリケーション プロキシ アプリケーションでは、匿名access | Microsoft Entra ID P1 |
| [管理者ロールにパスワード リセット通知を要求する](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#require-password-reset-notifications-for-administrator-roles) | Microsoft Entra ID P1 |
| [レガシ認証ポリシーの構成をブロックする](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#block-legacy-authentication-policy-is-configured) | Microsoft Entra ID P1 |
| [Temporary access パスが有効になっています](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#temporary-access-pass-is-enabled) | Microsoft Entra ID P1 |
| [一時Accessを 1 回の使用に渡す](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#restrict-temporary-access-pass-to-single-use) | Microsoft Entra ID P1 |
| [従来の MFA ポリシーと SSPR ポリシーから移行する](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#migrate-from-legacy-mfa-and-sspr-policies) | Microsoft Entra ID P1 |
| [管理者による SSPR の使用をブロックする](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#block-administrators-from-using-sspr) | Microsoft Entra ID P1 |
| [セルフサービス パスワード リセットではセキュリティに関する質問は使用されません](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#self-service-password-reset-doesnt-use-security-questions) | Microsoft Entra ID P1 |
| [SMS と音声通話の認証方法が無効になっている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#sms-and-voice-call-authentication-methods-are-disabled) | Microsoft Entra ID P1 |
| [MFA 登録のセキュリティ保護 (マイ セキュリティ情報) ページ](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#secure-the-mfa-registration-my-security-info-page) | Microsoft Entra ID P1 |
| [クラウド認証を使用する](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#use-cloud-authentication) | Microsoft Entra ID P1 |
| [すべてのユーザーが MFA に登録する必要がある](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#all-users-are-required-to-register-for-mfa) | Microsoft Entra ID P2 |
| [ユーザーに強力な認証方法が構成されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#users-have-strong-authentication-methods-configured) | Microsoft Entra ID P1 |
| [ユーザーに表示されるパスワードの領域を減らす](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#reduce-the-user-visible-password-surface-area) | Microsoft Entra ID P1 |
| [ユーザー サインイン アクティビティでトークン保護を使用する](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#user-sign-in-activity-uses-token-protection) | Microsoft Entra ID P1 |
| [トークン保護ポリシーが構成されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#token-protection-policies-are-configured) | Microsoft Entra ID P1 |
| [すべてのユーザー サインイン アクティビティでフィッシングに強い認証方法を使用する](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#all-user-sign-in-activity-uses-phishing-resistant-authentication-methods) | Microsoft Entra ID P1 |
| [すべてのサインイン アクティビティはマネージド デバイスから取得されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#all-sign-in-activity-comes-from-managed-devices) | Microsoft Entra ID P1 |
| [セキュリティ キーの認証方法が有効になっている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#security-key-authentication-method-enabled) | なし (Microsoft Entra IDに含まれます) |
| [特権ロールが古い ID に割り当てられていません](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#privileged-roles-arent-assigned-to-stale-identities) | Microsoft Entra ID P2 |
| [Microsoft Authenticator アプリにサインイン コンテキストが表示されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#microsoft-authenticator-app-shows-sign-in-context) | Microsoft Entra ID P1 |
| [Microsoft Authenticatorアプリ レポートの不審なアクティビティの設定が有効になっています](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#microsoft-authenticator-app-report-suspicious-activity-setting-is-enabled) | Microsoft Entra ID P1 |
| [パスワードの有効期限が無効になっている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#password-expiration-is-disabled) | Microsoft Entra ID P1 |
| [スマート ロックアウトしきい値を 10 以下に設定](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#smart-lockout-threshold-set-to-10-or-less) | Microsoft Entra ID P1 |
| [スマート ロックアウト期間が 60 以上に設定されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#smart-lockout-duration-is-set-to-a-minimum-of-60) | Microsoft Entra ID P1 |
| [禁止パスワード リストに組織の用語を追加する](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#add-organizational-terms-to-the-banned-password-list) | Microsoft Entra ID P1 |
| [ユーザー アクションを使用してデバイスの参加とデバイスの登録に多要素認証を要求する](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#require-multifactor-authentication-for-device-join-and-device-registration-using-user-action) | Microsoft Entra ID P1 |
| [ローカル管理者パスワード ソリューションがデプロイされている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#local-admin-password-solution-is-deployed) | Microsoft Entra ID P1 |
| [Entra Connect Sync がサービス プリンシパルの資格情報で構成されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#entra-connect-sync-is-configured-with-service-principal-credentials) | なし (Microsoft Entra IDに含まれます) |
| [ディレクトリ同期アカウントが特定の名前付き場所にロックダウンされている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#directory-sync-account-is-locked-down-to-specific-named-location) | Microsoft Entra ID P1 |
| [テナントで ADAL を使用しない](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#no-usage-of-adal-in-the-tenant) | なし (Microsoft Entra IDに含まれます) |
| [Block レガシ Azure AD PowerShell モジュール](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#block-legacy-azure-ad-powershell-module) | なし (Microsoft Entra IDに含まれます) |
| [無料テナントのMicrosoft Entra IDセキュリティの既定値を有効にします](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-identities#enable-microsoft-entra-id-security-defaults-for-free-tenants) | なし (Microsoft Entra IDに含まれます) |

### テナントを保護し、運用システムを分離する

| 確認 | 最低限必要なライセンス |
| --- | --- |
| [新しいテナントを作成するためのアクセス許可は、テナント作成者ロールに制限されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#permissions-to-create-new-tenants-are-limited-to-the-tenant-creator-role) | なし (Microsoft Entra IDに含まれます) |
| [条件付きAccess ポリシーの作成と変更をセキュリティで保護するための保護されたアクションを有効にする](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#enable-protected-actions-to-secure-conditional-access-policy-creation-and-changes) | Microsoft Entra ID P1 |
| [Guest accessは承認されたテナントに制限されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#guest-access-is-limited-to-approved-tenants) | Microsoft Entra ID無料 |
| [ゲストに高い特権ディレクトリ ロールが割り当てられない](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#guests-are-not-assigned-high-privileged-directory-roles) | Microsoft Entra ID無料PIM の P2 または Microsoft ID ガバナンスのMicrosoft Entra ID |
| [ゲストは他のゲストを招待できません](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#guests-cant-invite-other-guests) | Microsoft Entra ID無料 |
| [Guests では、ディレクトリ オブジェクトへのaccessが制限されています](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#guests-have-restricted-access-to-directory-objects) | Microsoft Entra ID無料 |
| [アプリ インスタンスのプロパティ ロックがすべてのアプリケーションに対して構成されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#app-instance-property-lock-is-configured-for-all-applications) | Microsoft Entra ID無料 |
| [ゲストは有効期間の長いサインイン セッションを持っていません](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#guests-dont-have-long-lived-sign-in-sessions) | Microsoft Entra ID P1 |
| [Guest accessは強力な認証方法で保護されています](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#guest-access-is-protected-by-strong-authentication-methods) | Microsoft Entra ID無料条件付きAccessに推奨される P1 Microsoft Entra ID |
| [ユーザー フローを使用したゲスト セルフサービス サインアップが無効になっている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#guest-self-service-sign-up-via-user-flow-is-disabled) | Microsoft Entra ID無料 |
| [テナント間の送信access設定が構成されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#outbound-cross-tenant-access-settings-are-configured) | Microsoft Entra ID無料条件付きAccessに推奨される P1 Microsoft Entra ID |
| [ゲストがテナント内のアプリを所有していない](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#guests-dont-own-apps-in-the-tenant) | なし (Microsoft Entra IDに含まれます) |
| [すべてのゲストはスポンサーを持っています](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#all-guests-have-a-sponsor) | Microsoft Entra ID無料 |
| [非アクティブなゲスト ID が無効になっているか、テナントから削除される](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#inactive-guest-identities-are-disabled-or-removed-from-the-tenant) | Microsoft Entra ID無料 |
| [すべてのエンタイトルメント管理ポリシーの有効期限が設定されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#all-entitlement-management-policies-have-an-expiration-date) | Microsoft Entra ID P2 または Microsoft ID Governance for entitlement managed and access reviews |
| [外部ユーザーに適用されるすべてのエンタイトルメント管理割り当てポリシーには、接続された組織が必要です](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#all-entitlement-management-assignment-policies-that-apply-to-external-users-require-connected-organizations) | Microsoft Entra ID P2 または Microsoft ID Governance for entitlement managed and access reviews |
| [外部ユーザーに適用されるすべてのエンタイトルメント管理割り当てポリシーには、承認が必要です](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#all-entitlement-management-assignment-policies-that-apply-to-external-users-require-approval) | Microsoft Entra ID P2 または Microsoft ID Governance for entitlement managed and access reviews |
| [ゲストに適用されるすべてのエンタイトルメント管理パッケージには、割り当てポリシーで有効期限またはaccessレビューが構成されています](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#all-entitlement-management-packages-that-apply-to-guests-have-expirations-or-access-reviews-configured-in-their-assignment-policies) | Microsoft Entra ID P2 または Microsoft ID Governance for entitlement managed and access reviews |
| [Microsoft Entra 参加済みデバイスでローカル管理者を管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#manage-the-local-administrators-on-microsoft-entra-joined-devices) | なし (Microsoft Entra IDに含まれます) |
| [管理者以外のユーザーが所有デバイスの BitLocker キーを回復できないように制限する](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-tenants#restrict-nonadministrator-users-from-recovering-the-bitlocker-keys-for-their-owned-devices) | なし (Microsoft Entra IDに含まれます) |

### ネットワークを保護する

ネットワーク境界を保護します。

| 確認 | 最低限必要なライセンス |
| --- | --- |
| [名前付きの場所が構成されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#named-locations-are-configured) | Microsoft Entra ID P1 |
| [テナント制限 v2 ポリシーが構成されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#tenant-restrictions-v2-policy-is-configured) | Microsoft Entra ID P1 |
| [Internet Access転送プロファイルが有効になっています](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#internet-access-forwarding-profile-is-enabled) | マイクロソフト エントラ インターネット アクセス |
| [Web コンテンツ フィルター ポリシーが構成されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#web-content-filtering-policies-are-configured) | マイクロソフト エントラ インターネット アクセス |
| [Web コンテンツ のフィルター処理でカテゴリベースのルールが使用される](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#web-content-filtering-uses-category-based-rules) | マイクロソフト エントラ インターネット アクセス |
| [Web コンテンツ フィルター ポリシーがセキュリティ プロファイルにリンクされている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#web-content-filtering-policies-are-linked-to-security-profiles) | マイクロソフト エントラ インターネット アクセス |
| Web コンテンツ のフィルター処理は、条件付きAccess | マイクロソフト エントラ インターネット アクセス |
| [Web コンテンツのフィルター処理により、リスクの高いカテゴリがブロックされる](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#web-content-filtering-blocks-high-risk-categories) | マイクロソフト エントラ インターネット アクセス |
| [TLS 検査が有効で、送信トラフィック用に正しく構成されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#tls-inspection-is-enabled-and-correctly-configured-for-outbound-traffic) | マイクロソフト エントラ インターネット アクセス |
| [TLS 検査バイパス規則が定期的にレビューされる](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#tls-inspection-bypass-rules-are-regularly-reviewed) | マイクロソフト エントラ インターネット アクセス |
| [TLS 検査証明書には十分な有効期間があります](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#tls-inspection-certificates-have-a-sufficient-validity-period) | マイクロソフト エントラ インターネット アクセス |
| [TLS 検査の失敗率が 1%を下回る](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#tls-inspection-failure-rate-is-below-1) | マイクロソフト エントラ インターネット アクセス |
| [TLS 検査のカスタム バイパス規則でシステム バイパスの宛先が重複しない](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#tls-inspection-custom-bypass-rules-dont-duplicate-system-bypass-destinations) | マイクロソフト エントラ インターネット アクセス |
| [脅威インテリジェンス のフィルター処理によってインターネット トラフィックが保護される](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#threat-intelligence-filtering-protects-internet-traffic) | マイクロソフト エントラ インターネット アクセス |
| [ファイル転送ポリシーは、データ流出を防ぐために構成されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#file-transfer-policies-are-configured-to-prevent-data-exfiltration) | マイクロソフト エントラ インターネット アクセス |
| [AI Gateway は、企業の生成型 AI アプリケーションを迅速なインジェクション攻撃から保護します](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#ai-gateway-protects-enterprise-generative-ai-applications-from-prompt-injection-attacks) | マイクロソフト エントラ インターネット アクセス |
| [グローバル セキュア Access クラウド ファイアウォールは、ブランチ オフィスのインターネット トラフィックを保護します](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#global-secure-access-cloud-firewall-protects-branch-office-internet-traffic) | マイクロソフト エントラ インターネット アクセス |
| [すべての Secure Web Gateway 防御レイヤーでインターネット トラフィックが検査される](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#internet-traffic-is-inspected-across-all-secure-web-gateway-defense-layers) | マイクロソフト エントラ インターネット アクセス |
| Network 検証は、ユニバーサル継続的Access評価 | Microsoft Entra Internet Access または Microsoft Entra Private Access |
| [Global Secure Access クライアントはすべてのマネージド エンドポイントにデプロイされます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#global-secure-access-client-is-deployed-on-all-managed-endpoints) | Microsoft Entra Internet Access または Microsoft Entra Private Access |
| [Global Secure Access ライセンスはテナントで使用でき、ユーザーに割り当てられます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#global-secure-access-licenses-are-available-in-the-tenant-and-assigned-to-users) | Microsoft Entra Internet Access または Microsoft Entra Private Access |
| Microsoft 365トラフィックはグローバル セキュア Access | Microsoft Entra スイート |
| Universal テナントの制限により、承認されていない外部テナント access | マイクロソフト エントラ インターネット アクセス |
| [Conditional Access ポリシーでは、準拠したネットワーク制御が使用されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#conditional-access-policies-use-compliant-network-controls) | Microsoft Entra ID P1 |
| [条件付きAccessのGlobal Secure Accessシグナリングが有効になっています](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#global-secure-access-signaling-for-conditional-access-is-enabled) | マイクロソフト エントラ インターネット アクセス |
| [ネットワーク トラフィックは、セキュリティ ポリシーの適用のためにグローバル セキュア Access経由でルーティングされます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#network-traffic-is-routed-through-global-secure-access-for-security-policy-enforcement) | Microsoft Entra Internet Access または Microsoft Entra Private Access |
| [トラフィック転送プロファイルのスコープは、制御された展開の適切なユーザーとグループに設定されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#traffic-forwarding-profiles-are-scoped-to-appropriate-users-and-groups-for-controlled-deployment) | Microsoft Entra Internet Access または Microsoft Entra Private Access |
| [Private ネットワーク コネクタは、内部リソースへのゼロ トラスト accessを維持するためにアクティブで正常です](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#private-network-connectors-are-active-and-healthy-to-maintain-zero-trust-access-to-internal-resources) | Microsoft Entra プライベートアクセス |
| [プライベート ネットワーク コネクタで最新バージョンが実行されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#private-network-connectors-are-running-the-latest-version) | Microsoft Entra プライベートアクセス |
| [少なくとも 2 つのプライベート Access コネクタは、コネクタ グループごとにアクティブで正常です](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#at-least-two-private-access-connectors-are-active-and-healthy-per-connector-group) | Microsoft Entra プライベートアクセス |
| [プライベート DNSは内部名前解決用に構成されています](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#private-dns-is-configured-for-internal-name-resolution) | Microsoft Entra プライベートアクセス |
| 内部ドメインのDNS トラフィックはプライベート Access | Microsoft Entra プライベートアクセス |
| [Intelligent Local Accessが有効になり、構成されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#intelligent-local-access-is-enabled-and-configured) | Microsoft Entra プライベートアクセス |
| [Quick Accessが有効になり、コネクタにバインドされます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#quick-access-is-enabled-and-bound-to-a-connector) | Microsoft Entra プライベートアクセス |
| [Quick Accessは条件付きAccess ポリシーにバインドされます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#quick-access-is-bound-to-a-conditional-access-policy) | Microsoft Entra プライベートアクセス |
| Entra プライベート Access アプリケーション セグメントは、最小特権access | Microsoft Entra プライベートアクセス |
| ドメイン コントローラーの RDP accessは、グローバル セキュア Access | Microsoft Entra プライベートアクセス |
| [Private Access センサーは、ドメイン コントローラーに強力な認証ポリシーを適用しています](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#private-access-sensors-are-enforcing-strong-authentication-policies-on-domain-controllers) | Microsoft Entra プライベートアクセス |
| [Quick Accessには、ユーザーまたはグループの割り当てがあります](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#quick-access-has-user-or-group-assignments) | Microsoft Entra プライベートアクセス |
| [すべてのプライベート Access アプリには、ユーザーまたはグループの割り当てがあります](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-networks#all-private-access-apps-have-user-or-group-assignments) | Microsoft Entra プライベートアクセス |

### エンジニアリング システムの保護

ソフトウェア資産を保護し、コードのセキュリティを強化します。

| 確認 | 最低限必要なライセンス |
| --- | --- |
| [Emergency access アカウントは適切に構成されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems#emergency-access-accounts-are-configured-appropriately) | Microsoft Entra ID P1 |
| [Global Administrator ロールのアクティブ化によって承認ワークフローがトリガーされます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems#global-administrator-role-activation-triggers-an-approval-workflow) | Microsoft Entra ID P2 |
| [グローバル管理者は、サブスクリプションをAzureするaccessを持っていません](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems#global-administrators-dont-have-standing-access-to-azure-subscriptions) | なし (Microsoft Entra IDに含まれます) |
| [新しいアプリケーションとサービス プリンシパルの作成は特権ユーザーに制限されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems#creating-new-applications-and-service-principals-is-restricted-to-privileged-users) | Microsoft Entra ID P1 |
| [非アクティブなアプリケーションには、高い特権を持つ Microsoft Graph API アクセス許可がありません](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems#inactive-applications-dont-have-highly-privileged-microsoft-graph-api-permissions) | Microsoft Entra ID P1 |
| [非アクティブなアプリケーションには、高い特権を持つ組み込みロールがありません](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems#inactive-applications-dont-have-highly-privileged-built-in-roles) | Microsoft Entra ID P1 |
| [アプリの登録安全なリダイレクト URI を使用します](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems#app-registrations-use-safe-redirect-uris) | Microsoft Entra ID P1 |
| [サービス プリンシパルが安全なリダイレクト URI を使用する](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems#service-principals-use-safe-redirect-uris) | Microsoft Entra ID P1 |
| [アプリの登録には、未解決または破棄されたドメイン リダイレクト URI を含めてはなりません](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems#app-registrations-must-not-have-dangling-or-abandoned-domain-redirect-uris) | Microsoft Entra ID P1 |
| [リソース固有の同意が制限されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems#resource-specific-consent-is-restricted) | Microsoft Entra ID P1 |
| [ワークロード ID に特権ロールが割り当てられない](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems#workload-identities-are-not-assigned-privileged-roles) | Microsoft Entra ID P1 |
| [エンタープライズ アプリケーションでは、明示的な割り当てまたはスコープ付きプロビジョニングが必要です](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems#enterprise-applications-must-require-explicit-assignment-or-scoped-provisioning) | Microsoft Entra ID P1 |
| [エンタープライズ アプリケーションには所有者がいます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems#enterprise-applications-have-owners) | なし (Microsoft Entra IDに含まれます) |
| [ユーザーあたりのデバイスの最大数を 10 に制限する](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems#limit-the-maximum-number-of-devices-per-user-to-10) | なし (Microsoft Entra IDに含まれます) |
| [Privileged Access Workstations のConditional Access ポリシーが構成されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-protect-engineering-systems#conditional-access-policies-for-privileged-access-workstations-are-configured) | Microsoft Entra ID P1 |

### サイバー脅威の監視と検出

セキュリティ ログとトリアージ アラートを収集して分析します。

| 確認 | 最低限必要なライセンス |
| --- | --- |
| [Diagnostic 設定は、すべてのMicrosoft Entra ログに対して構成されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#diagnostic-settings-are-configured-for-all-microsoft-entra-logs) | Microsoft Entra ID P1 |
| [特権ロールのアクティブ化で監視とアラートが構成されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#privileged-role-activations-have-monitoring-and-alerting-configured) | Microsoft Entra ID P2 |
| [Global Administrator ロールの割り当てのアクティブ化アラート](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#activation-alert-for-global-administrator-role-assignments) | Microsoft Entra ID P2 |
| [すべての特権ロールの割り当てに対するアクティブ化アラート](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#activation-alert-for-all-privileged-role-assignments) | Microsoft Entra ID P2 |
| [フィッシング詐欺に強い方法で特権ユーザーがサインインする](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#privileged-users-sign-in-with-phishing-resistant-methods) | Microsoft Entra ID P1 |
| [リスクの高いユーザーはすべてトリアージされます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#all-high-risk-users-are-triaged) | Microsoft Entra ID P2 |
| [リスクの高いサインインはすべてトリアージされます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#all-high-risk-sign-ins-are-triaged) | Microsoft Entra ID P2 |
| [リスクの高いワークロード ID はすべてトリアージされます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#all-risky-workload-identities-are-triaged) | Microsoft Entra ID P2 |
| [テナント作成イベントがトリアージされる](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#tenant-creation-events-are-triaged) | Microsoft Entra ID P1 |
| [すべてのユーザー サインイン アクティビティで強力な認証方法が使用される](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#all-user-sign-in-activity-uses-strong-authentication-methods) | Microsoft Entra ID P1 |
| [優先度の高いMicrosoft Entraの推奨事項に対処します](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#high-priority-microsoft-entra-recommendations-are-addressed) | Microsoft Entra ID P1 |
| [ID 保護通知が有効になっている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#id-protection-notifications-are-enabled) | Microsoft Entra ID P2 |
| [レガシ認証サインイン アクティビティなし](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#no-legacy-authentication-sign-in-activity) | Microsoft Entra ID P1 |
| [すべてのMicrosoft Entra推奨事項に対処します](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#all-microsoft-entra-recommendations-are-addressed) | Microsoft Entra ID P1 |
| [Network access アクティビティは、脅威の検出と対応のためのセキュリティ操作に表示されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#network-access-activity-is-visible-to-security-operations-for-threat-detection-and-response) | Microsoft Entra ID P1 |
| [Network access ログは、セキュリティ分析とコンプライアンス要件のために保持されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#network-access-logs-are-retained-for-security-analysis-and-compliance-requirements) | Microsoft Entra ID P1 |
| [Global Secure Accessデプロイ ログが設定され、確認されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-monitor-detect#global-secure-access-deployment-logs-are-populated-and-reviewed) | Microsoft Entra Internet Access または Microsoft Entra Private Access |

### 応答と修復を高速化する

セキュリティ インシデント対応とインシデント通信を改善します。

| 確認 | 最低限必要なライセンス |
| --- | --- |
| [ワークロード ID はリスクベースのポリシーで構成されます](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-response-remediation#workload-identities-are-configured-with-risk-based-policies) | Microsoft Entra ワークロード ID |
| [リスクの高いサインインを制限する](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-response-remediation#restrict-high-risk-sign-ins) | Microsoft Entra ID P2 |
| [リスクの高いユーザーにaccess](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-response-remediation#restrict-access-to-high-risk-users) | Microsoft Entra ID P2 |

### AI

ID コントロールを使用して AI エージェントとエージェントベースのワークロードをセキュリティで保護します。

| 確認 | 最低限必要なライセンス |
| --- | --- |
| [エージェントと対話するための認証Microsoft Entra ID必要があります](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-ai#require-microsoft-entra-id-authentication-to-interact-with-agents) | Microsoft Entra ID P1 |
| [条件付きアクセス ポリシーは、エージェント ID とエージェントのユーザー アカウントの両方を対象とします](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-ai#conditional-access-policies-cover-both-agent-identities-and-agents-user-accounts) | Microsoft Entra ID P1 |
| [リスクベースの条件付きアクセスによって、危険なエージェント ID がブロックされる](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-ai#risk-based-conditional-access-blocks-risky-agent-identities) | Microsoft Entra ID P2 |
| [エージェント ID のカスタム セキュリティ属性が存在する](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-ai#custom-security-attributes-for-agent-identities-are-present) | なし (Microsoft Entra IDに含まれます) |
| [エージェント ID スポンサーの ID ガバナンスが構成されている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-ai#identity-governance-for-agent-identity-sponsors-is-configured) | Microsoft Entra ID P1 |
| [エージェント ID とブループリント プリンシパルには技術所有者が割り当てられ、無効なエージェントはディレクトリに残っていません](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-ai#agent-identities-and-blueprint-principals-have-assigned-technical-owners-and-no-disabled-agents-remain-in-the-directory) | なし (Microsoft Entra IDに含まれます) |
| [AI 管理者ロールにプリンシパルが割り当てられている](https://learn.microsoft.com/ja-jp/entra/fundamentals/zero-trust-ai#ai-administrative-roles-have-assigned-principals) | なし (Microsoft Entra IDに含まれます) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/create-new-tenant"} -->
## クイックスタート - アクセスして新しいテナントを作成する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant
- Service: entra / fundamentals
- Article date: 2026-07-29
- Summary: Microsoft Entra ID の検索方法と、組織の新しいテナントの作成方法に関する手順。

### 概要

組織の新しいテナントの作成を含め、Microsoft Entra 管理センターを使用して、すべての管理タスクを実行できます。

このクイック スタート記事では、組織の基本的なテナントを作成する方法について説明します。

注

有料のお客様のみが、Microsoft Entra ID で新しい従業員テナントを作成できます。 無料のテナントまたは試用版サブスクリプションを使用しているお客様は、Microsoft Entra 管理センターから追加のテナントを作成することはできません。 このシナリオに直面している、新しいテナントが必要なお客様は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)にサインアップできます。

### 組織の新しいテナントを作成する

[Azure portal](https://portal.azure.com) にサインインすると、組織の新しいテナントを作成できます。 新しいテナントは組織を表し、社内外のユーザー向けに Microsoft Cloud サービスの特定のインスタンスを管理するのに役立ちます。

注

- Microsoft Entra ID または Azure AD B2C テナントを作成できない場合は、ユーザー設定ページを確認して、テナントの作成がオフになっていないことを確認します。 有効になっていない場合は、少なくとも [テナント作成者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#tenant-creator) ロールを割り当てる必要があります。
- この記事では、コンシューマー向けアプリの *外部* テナント構成の作成については説明しません。顧客 ID とアクセス管理 (CIAM) のシナリオでの [Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/customers/overview-customers-ciam) の使用について詳しく説明します。
- 管理されたワークフォース テナントを作成できない場合は、 [Enterprise Agreement (EA)](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/understand-ea-roles) または [従量課金制](https://azure.microsoft.com/pricing/offers/ms-azr-0003p?cid=msft_learn) サブスクリプションがあることを確認します。 [マイクロソフト オンライン サブスクリプション契約 (MOSA)](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/view-all-accounts#microsoft-online-services-program) と [Microsoft 顧客契約 (MCA)](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/view-all-accounts#microsoft-customer-agreement) の両方の課金アカウントがサポートされています。 また、テナント共同作成者ロールまたはサブスクリプション所有者/作成者ロールを使用して、選択したサブスクリプションに必要なAzure Resource Manager (ARM) アクセス許可も必要です。 課金アカウントの種類を特定するには、[Azure ポータルで課金アカウントを表示するを](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/view-all-accounts)参照してください。

#### 新しいテナントを作成するには

## [ワークフォース/B2C](#tab/workforce)
1. [Azure portal](https://portal.azure.com) にサインインします。
2. Azure portal メニューから **[Microsoft Entra ID]** を選択します。
3. **Entra ID**&gt;**概要**&gt;**テナントを管理**に移動します。
4. **［作成］** を選択します

    [Image: Microsoft Entra ID - [概要] ページ - [テナントの作成] のスクリーンショット。]
5. [基本] タブで作成するテナントの種類として **[Microsoft Entra ID]** か **[Microsoft Entra ID (B2C)]** を選択します。

    **Microsoft Entra ID**を選択して、組織のユーザーとリソースの従業員テナントを作成します。 **Azure AD B2C** テナントが必要な場合にのみ、Microsoft Entra ID (B2C) を選択します。 **Microsoft Entra ID**使用できない場合は、有料の顧客要件、テナント作成設定、テナント作成者ロールなど、前のメモの前提条件を確認してください。
6. **[次へ: 構成]** を選択して [構成] タブに移動します。
7. [構成] タブで、次の情報を入力します。

    [Image: Microsoft Entra ID - [テナントの作成] ページ - [構成] タブのスクリーンショット。]

    - 目的の組織名 (*Contoso 組織*など) を **[組織名]** ボックスに入力します。
    - 目的の初期ドメイン名 (*Contosoorg* など) を **[初期ドメイン名]** ボックスに入力します。
    - 目的の国/地域を選択するか、*[国/地域]* ボックスを **[米国]** オプションのままにします。
8. **次へ: 確認と作成** を選択します。 入力した情報を確認し、誤りがなければ左下隅の **[作成]** を選択します。

新しいテナントは、ドメイン contoso.onmicrosoft.com で作成されます。

## [セキュリティで保護されたアドオン テナントの作成](#tab/governed-workforce)
セキュリティで保護されたアドオン テナント作成フローを使用して、新しい管理された Workforce テナントを作成します。 このプロセスにより、テナントが作成され、ホーム テナントとの [ガバナンス関係](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-relationships) が自動的に確立されます。

#### 既定のガバナンス ポリシー テンプレートを定義する

アドオン テナントとのガバナンス関係を自動的に確立するには、まず既定のガバナンス ポリシー テンプレートを定義する必要があります。

1. 管理テナントに管理者としてサインインします。
2. [テンプレート] に移動します。
3. 既定のポリシー テンプレートを選択し、必要に応じて次のオプションを構成します。

    - **代理管理**: 1 つ以上の Microsoft Entra 組み込みロールを選択し、それらを管理テナントのロール割り当て可能なセキュリティ グループに割り当てます。 このグループのメンバーは、管理テナントの資格情報を使用して、管理テナントのアカウントを必要とせずに、管理テナントにサインインできます。 各グループには複数のロールの割り当てを割り当てることができ、各ポリシー テンプレートには複数のグループを定義できます。
    - **マルチテナント アプリケーション管理**: カスタムのマルチテナント アプリケーションを選択します。 リレーションシップを確立すると、管理されているテナントは同じアクセス許可を持つサービス プリンシパルを作成します。

#### テナントを作成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[Entra ID]**&gt;**[概要]**&gt;**[テナントを管理する]** を参照します。
3. **［作成］** を選択します
4. [基本] タブで、[ **Governed Workforce** ] を選択して、セキュリティで保護されたアドオン テナント作成機能にアクセスします。
5. **[次へ: 構成]** を選択して [構成] タブに移動します。
6. [構成] タブで、次の情報を入力します。

    - 目的の組織名 (*Contoso 組織*など) を **[組織名]** ボックスに入力します。
    - 目的の初期ドメイン名 (*Contosoorg* など) を **[初期ドメイン名]** ボックスに入力します。
    - 目的の国/地域を選択するか、*[国/地域]* ボックスを **[米国]** オプションのままにします。
    - 新しいテナントの Microsoft Entra ID 無料課金資産を格納するために、目的のクラウド サブスクリプションとリソース グループを選択します。

    注

    [Enterprise Agreement (EA)](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/understand-ea-roles) または[従量課金制](https://azure.microsoft.com/pricing/offers/ms-azr-0003p?cid=msft_learn)サブスクリプションを使用します。 [マイクロソフト オンライン サブスクリプション契約 (MOSA)](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/view-all-accounts#microsoft-online-services-program) と [Microsoft 顧客契約 (MCA)](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/view-all-accounts#microsoft-customer-agreement) の両方の課金アカウントがサポートされています。 また、テナント共同作成者ロールまたはサブスクリプション所有者/作成者ロールを使用して、選択したサブスクリプションに必要なAzure Resource Manager (ARM) アクセス許可も必要です。 課金アカウントの種類を特定するには、[Azure ポータルで課金アカウントを表示するを](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/view-all-accounts)参照してください。
7. **次へ: 確認と作成** を選択します。 入力した情報を確認し、誤りがなければ左下隅の **[作成]** を選択します。

新しいテナントは、ドメイン contoso.onmicrosoft.com で作成されます。 ガバナンス ポリシー テンプレートを定義した場合、ホーム テナントと新しく作成されたテナントの間にテナント ガバナンス関係が自動的に形成されます。 課金アカウントに、選択したサブスクリプションとリソース グループの下に、新しく作成されたテナントにリンクされた Microsoft Entra ID Free 課金資産が表示されるようになりました。

ガバナンス関係とポリシー テンプレートの詳細については、ガバナンス [関係](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-relationships) と [ガバナンス ポリシー テンプレート](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-policy-templates)に関するページを参照してください。

---

### 新しいテナントのユーザー アカウント

既定では、Microsoft Entra テナントを作成するユーザーには、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールが自動的に割り当てられます。

既定では、テナントの[技術部連絡先](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/change-address-contact-and-more#what-do-these-fields-mean)としても表示されます。 技術部連絡先の情報は、[**\[プロパティ\]**](https://portal.azure.com/#blade/Microsoft_AAD_IAM/ActiveDirectoryMenuBlade/Properties) で変更できます。

Microsoft は、組織が[全体管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)ロールが永続的に割り当てられる 2 つのクラウド専用緊急アクセス アカウントを作成することを推奨しています。 このようなアカウントは高い特権を持っており、特定のユーザーには割り当てられません。 これらのアカウントは、通常のアカウントを使用できない、またはすべての管理者が誤ってロックアウトされた場合の緊急時や最終手段の緊急対応としてのシナリオにおいてのみ使用されます。これらのアカウントは、[緊急アクセス アカウントに関する推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)に従って作成する必要があります。

### リソースをクリーンアップする

このテナントを引き続き使用しない場合は、次の手順を使用してテナントを削除できます。

- Azure portal で **[ディレクトリ + サブスクリプション]** フィルターを使用して、削除するディレクトリにサインインしていることを確認します。 必要に応じてターゲット ディレクトリに切り替えます。
- **[Microsoft Entra ID]** を選択し、**[Contoso - 概要]** ページで **[ディレクトリの削除]** を選択します。

    テナントとその関連情報は削除されます。

    [Image: [ディレクトリの削除] ボタンが強調表示されている [概要] ページのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/custom-security-attributes-add"} -->
## Microsoft Entra ID でカスタム セキュリティ属性の定義を追加または非アクティブ化する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add
- Service: entra / fundamentals
- Article date: 2025-05-30
- Summary: Microsoft Entra ID で新しいカスタム セキュリティ属性の定義を追加したり、カスタム セキュリティ属性の定義を非アクティブ化したりする方法を説明します。

Microsoft Entra ID の[カスタム セキュリティ属性](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview)は、ビジネス固有の属性 (キーと値のペア) であり、Microsoft Entra のオブジェクトに対して定義し割り当てることができます。 この記事では、カスタム セキュリティ属性の定義を追加、編集、または非アクティブ化する方法について説明します。

### 前提条件

カスタム セキュリティ属性の定義を追加または非アクティブ化するには、次が必要です。

- [属性定義管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-administrator)
- [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) を使用する場合、Microsoft.Graph モジュール

重要

既定では、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、割り当てを行う権限がありません。

### 属性セットを追加する

属性セットは、関連する属性のコレクションです。 すべてのカスタム セキュリティ属性は、属性セットの一部である必要があります。 属性セットの名前を変更または削除することはできません。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[属性定義管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-administrator)としてサインインします。
2. **Entra ID**&gt;**カスタム セキュリティ属性**を参照します。
3. **[Add attribute set](https://learn.microsoft.com/ja-jp/entra/fundamentals/属性セットの追加)** を選択して、新しい属性セットを追加します。

    [Add attribute set] (属性セットの追加) が無効になっている場合は、属性定義管理者のロールが割り当てられていることを確認してください。 詳細については、[カスタム セキュリティ属性のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-troubleshoot)に関する記事を参照してください。
4. 属性の名前、説明、最大数を入力します。

    属性セット名には、スペースや特殊文字を含めない 32 文字を指定できます。 一度指定した名前は、変更できません。 詳細については、「[制限および制約](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview#limits-and-constraints)」を参照してください。

    [Image: Microsoft Entra 管理センターの [新しい属性セット] ペインのスクリーンショット。]
5. 完了したら、**を選択して**を追加します。

    新しい属性セットが属性セットの一覧に表示されます。

### カスタム セキュリティ属性の定義を追加する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[属性定義管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-administrator)としてサインインします。
2. **Entra ID**&gt;**カスタム セキュリティ属性**を参照します。
3. [カスタム セキュリティ属性] ページで、既存の属性セットを見つけるか、**[属性セットの追加]** を選択して新しい属性セットを追加します。

    すべてのカスタム セキュリティ属性の定義は、属性セットの一部である必要があります。
4. 属性セットを選択すると開きます。
5. **[属性の追加]** を選択して、新しいカスタム セキュリティ属性をその属性セットに追加します。

    [Image: Microsoft Entra 管理センターの [新しい属性] ペインのスクリーンショット。]
6. **[属性名]** ボックスに、カスタム セキュリティ属性名を入力します。

    カスタム セキュリティ属性名には、スペースや特殊文字を含めない 32 文字を指定できます。 一度指定した名前は、変更できません。 詳細については、「[制限および制約](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview#limits-and-constraints)」を参照してください。
7. **説明** ボックスに、任意の説明を入力します。

    説明は 128 文字まで入力できます。 必要に応じて、後で説明を変更できます。
8. **[データ型]** の一覧から、カスタム セキュリティ属性のデータ型を選択します。

    | データ型 | 説明 |
    | --- | --- |
    | ボーリアン | true、True、false、False のいずれかの値をとるブール値。 |
    | 整数 | 32 ビットの整数。 |
    | 糸 | X 文字までの長さの文字列。 |
9. **[Allow multiple values to be assigned](https://learn.microsoft.com/ja-jp/entra/fundamentals/複数の値の割り当てを許可する)** で、**[はい]** または **[いいえ]** を選択します。

    **[はい]** を選択すると、このカスタム セキュリティ属性に複数の値を割り当てることができます。 **[いいえ]** を選択すると、このカスタム セキュリティ属性に単一の値のみを割り当てることができます。
10. **で定義済みの値のみを**割り当てられるようにするには、**[はい]** または **[いいえ]**を選択します。

    定義済みの値の一覧からこのカスタム セキュリティ属性に値を割り当てる場合は、**[はい]** を選択します。 **[いいえ]** を選択すると、ユーザー定義の値または潜在的に定義済みの値をこのカスタム セキュリティ属性に割り当てることができます。
11. **[Only allow predefined values to be assigned](https://learn.microsoft.com/ja-jp/entra/fundamentals/定義済みの値のみの割り当てを許可する)** が **[はい]** の場合は、**[値の追加]** を選択して定義済みの値を追加します。

    アクティブな値は、オブジェクトへの割り当てに使用できます。 アクティブでない値は定義されますが、割り当てにはまだ使用できません。

    [Image: Microsoft Entra 管理センターの [定義済みの値の追加] ペインを含む [新しい属性] ペインのスクリーンショット。]
12. 終わったら、 **[保存]** を選択します。

    新しいカスタム セキュリティ属性は、カスタム セキュリティ属性の一覧に表示されます。
13. 定義済みの値を含める場合は、次のセクションの手順に従います。

### カスタム セキュリティ属性の定義を編集する

新しいカスタム セキュリティ属性の定義を追加したら、後でいくつかのプロパティを編集できます。 一部のプロパティは不変であり、変更することはできません。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[属性定義管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-administrator)としてサインインします。
2. **Entra ID**&gt;**カスタム セキュリティ属性**を参照します。
3. 編集するカスタム セキュリティ属性を含む属性セットを選択します。
4. カスタム セキュリティ属性の一覧で、編集したいカスタム セキュリティ属性の省略記号を選択し、**[属性の編集]** を選択します。
5. 有効になっているプロパティを編集します。
6. **[Only allow predefined values to be assigned](https://learn.microsoft.com/ja-jp/entra/fundamentals/定義済みの値のみの割り当てを許可する)** が **[はい]** の場合は、**[値の追加]** を選択して定義済みの値を追加します。 定義済みの値を選択して、**「アクティブですか？」**設定を変更します。

    [Image: Microsoft Entra 管理センターの [定義済みの値の追加] ウィンドウのスクリーンショット。]

### カスタム セキュリティ属性の定義を非アクティブ化する

カスタム セキュリティ属性の定義を追加したら、それを削除できます。 ただし、カスタム セキュリティ属性の定義を非アクティブ化できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[属性定義管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-administrator)としてサインインします。
2. **Entra ID**&gt;**カスタム セキュリティ属性**を参照します。
3. 非アクティブ化するカスタム セキュリティ属性を含む属性セットを選択します。
4. カスタム セキュリティ属性の一覧で、非アクティブ化するカスタム セキュリティ属性の横にチェック マークを追加します。
5. **で属性**を非アクティブ化するを選択します。
6. 表示される [属性の非アクティブ化] ダイアログで、**[はい]** を選択します。

    カスタム セキュリティ属性が非アクティブ化され、非アクティブ化された属性の一覧に移動します。

### PowerShell または Microsoft Graph API

Microsoft Entra 組織内のカスタム セキュリティ属性の定義を管理するには、PowerShell または Microsoft Graph API を使用することもできます。 次の例では、属性セットとカスタム セキュリティ属性の定義を管理します。

##### すべての属性セットを取得する

次の例では、すべての属性セットを取得します。

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[Get-MgDirectoryAttributeSet](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdirectoryattributeset)

```powershell
Get-MgDirectoryAttributeSet | Format-List
```

```output
Description          : Attributes for engineering team
Id                   : Engineering
MaxAttributesPerSet  : 25
AdditionalProperties : {}

Description          : Attributes for marketing team
Id                   : Marketing
MaxAttributesPerSet  : 25
AdditionalProperties : {}
```

## [Microsoft Graph](#tab/ms-graph)
[属性セットを一覧表示する](https://learn.microsoft.com/ja-jp/graph/api/directory-list-attributesets)

```http
GET https://graph.microsoft.com/v1.0/directory/attributeSets
```

---

##### 上位の属性セットを取得する

次の例では、上位の属性セットを取得します。

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[Get-MgDirectoryAttributeSet](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdirectoryattributeset)

```powershell
Get-MgDirectoryAttributeSet -Top 10
```

## [Microsoft Graph](#tab/ms-graph)
[属性セットを一覧表示する](https://learn.microsoft.com/ja-jp/graph/api/directory-list-attributesets)

```http
GET https://graph.microsoft.com/v1.0/directory/attributeSets?$top=10
```

---

##### 属性セットを順番に取得する

次の例では、属性セットを順番に取得します。

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[Get-MgDirectoryAttributeSet](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdirectoryattributeset)

```powershell
Get-MgDirectoryAttributeSet -Sort "Id"
```

## [Microsoft Graph](#tab/ms-graph)
[属性セットを一覧表示する](https://learn.microsoft.com/ja-jp/graph/api/directory-list-attributesets)

```http
GET https://graph.microsoft.com/v1.0/directory/attributeSets?$orderBy=id
```

---

##### 属性セットを取得する

次の例では、属性セットを取得します。

- 属性セット: `Engineering`

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[Get-MgDirectoryAttributeSet](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdirectoryattributeset)

```powershell
Get-MgDirectoryAttributeSet -AttributeSetId "Engineering" | Format-List
```

```output
Description          : Attributes for engineering team
Id                   : Engineering
MaxAttributesPerSet  : 25
AdditionalProperties : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#directory/attributeSets/$entity]}
```

## [Microsoft Graph](#tab/ms-graph)
[attributeSet の取得](https://learn.microsoft.com/ja-jp/graph/api/attributeset-get)

```http
GET https://graph.microsoft.com/v1.0/directory/attributeSets/Engineering
```

---

##### 属性セットを追加する

次の例では、新しい属性セットを追加します。

- 属性セット: `Engineering`

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[New-MgDirectoryAttributeSet](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/new-mgdirectoryattributeset)

```powershell
$params = @{
    Id = "Engineering"
    Description = "Attributes for engineering team"
    MaxAttributesPerSet = 25
}
New-MgDirectoryAttributeSet -BodyParameter $params
```

```output
Id          Description                     MaxAttributesPerSet
--          -----------                     -------------------
Engineering Attributes for engineering team 25
```

## [Microsoft Graph](#tab/ms-graph)
[attributeSet の作成](https://learn.microsoft.com/ja-jp/graph/api/directory-post-attributesets)

```http
POST https://graph.microsoft.com/v1.0/directory/attributeSets 
{
    "id":"Engineering",
    "description":"Attributes for engineering team",
    "maxAttributesPerSet":25
}
```

---

##### 属性セットを更新する

次の例では、属性セットを更新します。

- 属性セット: `Engineering`

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[Update-MgDirectoryAttributeSet](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/update-mgdirectoryattributeset)

```powershell
$params = @{
    description = "Attributes for engineering team"
    maxAttributesPerSet = 20
}
Update-MgDirectoryAttributeSet -AttributeSetId "Engineering" -BodyParameter $params
```

## [Microsoft Graph](#tab/ms-graph)
[属性セットの更新](https://learn.microsoft.com/ja-jp/graph/api/attributeset-update)

```http
PATCH https://graph.microsoft.com/v1.0/directory/attributeSets/Engineering
{
    "description":"Attributes for engineering team",
    "maxAttributesPerSet":20
}
```

---

##### すべてのカスタム セキュリティ属性の定義を取得する

次の例では、すべてのカスタム セキュリティ属性の定義を取得します。

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[Get-MgDirectoryCustomSecurityAttributeDefinition](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdirectorycustomsecurityattributedefinition)

```powershell
Get-MgDirectoryCustomSecurityAttributeDefinition | Format-List
```

```output
AllowedValues           :
AttributeSet            : Engineering
Description             : Target completion date
Id                      : Engineering_ProjectDate
IsCollection            : False
IsSearchable            : True
Name                    : ProjectDate
Status                  : Available
Type                    : String
UsePreDefinedValuesOnly : False
AdditionalProperties    : {}

AllowedValues           :
AttributeSet            : Engineering
Description             : Active projects for user
Id                      : Engineering_Project
IsCollection            : True
IsSearchable            : True
Name                    : Project
Status                  : Available
Type                    : String
UsePreDefinedValuesOnly : True
AdditionalProperties    : {}

AllowedValues           :
AttributeSet            : Marketing
Description             : Country where is application is used
Id                      : Marketing_AppCountry
IsCollection            : True
IsSearchable            : True
Name                    : AppCountry
Status                  : Available
Type                    : String
UsePreDefinedValuesOnly : True
AdditionalProperties    : {}
```

## [Microsoft Graph](#tab/ms-graph)
[customSecurityAttributeDefinitions を一覧表示する](https://learn.microsoft.com/ja-jp/graph/api/directory-list-customsecurityattributedefinitions)

```http
GET https://graph.microsoft.com/v1.0/directory/customSecurityAttributeDefinitions
```

---

##### カスタム セキュリティ属性の定義をフィルター処理する

次の例では、カスタム セキュリティ属性の定義をフィルター処理します。

- フィルター: 属性名が「Project」で、状態が「利用可能」

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[Get-MgDirectoryCustomSecurityAttributeDefinition](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdirectorycustomsecurityattributedefinition)

```powershell
Get-MgDirectoryCustomSecurityAttributeDefinition -Filter "name eq 'Project' and status eq 'Available'" | Format-List
```

```output
AllowedValues           :
AttributeSet            : Engineering
Description             : Active projects for user
Id                      : Engineering_Project
IsCollection            : True
IsSearchable            : True
Name                    : Project
Status                  : Available
Type                    : String
UsePreDefinedValuesOnly : True
AdditionalProperties    : {}
```

## [Microsoft Graph](#tab/ms-graph)
[customSecurityAttributeDefinitions を一覧表示する](https://learn.microsoft.com/ja-jp/graph/api/directory-list-customsecurityattributedefinitions)

```http
GET https://graph.microsoft.com/v1.0/directory/customSecurityAttributeDefinitions?$filter=name+eq+'Project'%20and%20status+eq+'Available'
```

---

- フィルター: 属性セット が 'Engineering' で状態が 'Available'、データ型が 'String'

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[Get-MgDirectoryCustomSecurityAttributeDefinition](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdirectorycustomsecurityattributedefinition)

```powershell
Get-MgDirectoryCustomSecurityAttributeDefinition -Filter "attributeSet eq 'Engineering' and status eq 'Available' and type eq 'String'" | Format-List
```

```output
AllowedValues           :
AttributeSet            : Engineering
Description             : Target completion date
Id                      : Engineering_ProjectDate
IsCollection            : False
IsSearchable            : True
Name                    : ProjectDate
Status                  : Available
Type                    : String
UsePreDefinedValuesOnly : False
AdditionalProperties    : {}

AllowedValues           :
AttributeSet            : Engineering
Description             : Active projects for user
Id                      : Engineering_Project
IsCollection            : True
IsSearchable            : True
Name                    : Project
Status                  : Available
Type                    : String
UsePreDefinedValuesOnly : True
AdditionalProperties    : {}
```

## [Microsoft Graph](#tab/ms-graph)
[customSecurityAttributeDefinitions を一覧表示する](https://learn.microsoft.com/ja-jp/graph/api/directory-list-customsecurityattributedefinitions)

```http
GET https://graph.microsoft.com/v1.0/directory/customSecurityAttributeDefinitions?$filter=attributeSet+eq+'Engineering'%20and%20status+eq+'Available'%20and%20type+eq+'String'
```

---

##### カスタム セキュリティ属性の定義を取得する

次の例では、カスタム セキュリティ属性の定義を取得します。

- 属性セット: `Engineering`
- 属性: `ProjectDate`

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[Get-MgDirectoryCustomSecurityAttributeDefinition](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdirectorycustomsecurityattributedefinition)

```powershell
Get-MgDirectoryCustomSecurityAttributeDefinition -CustomSecurityAttributeDefinitionId "Engineering_ProjectDate" | Format-List
```

```output
AllowedValues           :
AttributeSet            : Engineering
Description             : Target completion date
Id                      : Engineering_ProjectDate
IsCollection            : False
IsSearchable            : True
Name                    : ProjectDate
Status                  : Available
Type                    : String
UsePreDefinedValuesOnly : False
AdditionalProperties    : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#directory/customSecurityAttributeDefinitions/$entity]}
```

## [Microsoft Graph](#tab/ms-graph)
[customSecurityAttributeDefinition を取得する](https://learn.microsoft.com/ja-jp/graph/api/customsecurityattributedefinition-get)

```http
GET https://graph.microsoft.com/v1.0/directory/customSecurityAttributeDefinitions/Engineering_ProjectDate
```

---

##### カスタム セキュリティ属性の定義を追加する

次の例では、新しいカスタム セキュリティ属性の定義を追加します。

- 属性セット: `Engineering`
- 属性: `ProjectDate`
- 属性のデータ型: 文字列

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[New-MgDirectoryCustomSecurityAttributeDefinition](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/new-mgdirectorycustomsecurityattributedefinition)

```powershell
$params = @{
    attributeSet = "Engineering"
    description = "Target completion date"
    isCollection = $false
    isSearchable = $true
    name = "ProjectDate"
    status = "Available"
    type = "String"
    usePreDefinedValuesOnly = $false
}
New-MgDirectoryCustomSecurityAttributeDefinition -BodyParameter $params | Format-List
```

```output
AllowedValues           :
AttributeSet            : Engineering
Description             : Target completion date
Id                      : Engineering_ProjectDate
IsCollection            : False
IsSearchable            : True
Name                    : ProjectDate
Status                  : Available
Type                    : String
UsePreDefinedValuesOnly : False
AdditionalProperties    : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#directory/customSecurityAttributeDefinitions/$entity]}
```

## [Microsoft Graph](#tab/ms-graph)
[customSecurityAttributeDefinition を作成する](https://learn.microsoft.com/ja-jp/graph/api/directory-post-customsecurityattributedefinitions)

```http
POST https://graph.microsoft.com/v1.0/directory/customSecurityAttributeDefinitions
{
    "attributeSet":"Engineering",
    "description":"Target completion date",
    "isCollection":false,
    "isSearchable":true,
    "name":"ProjectDate",
    "status":"Available",
    "type":"String",
    "usePreDefinedValuesOnly": false
}
```

---

##### 複数の定義済みの値をサポートするカスタム セキュリティ属性の定義を追加する

次の例では、複数の定義済みの値をサポートする新しいカスタム セキュリティ属性の定義を追加します。

- 属性セット: `Engineering`
- 属性: `Project`
- 属性のデータ型: 文字列のコレクション

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[New-MgDirectoryCustomSecurityAttributeDefinition](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/new-mgdirectorycustomsecurityattributedefinition)

```powershell
$params = @{
    attributeSet = "Engineering"
    description = "Active projects for user"
    isCollection = $true
    isSearchable = $true
    name = "Project"
    status = "Available"
    type = "String"
    usePreDefinedValuesOnly = $true
}
New-MgDirectoryCustomSecurityAttributeDefinition -BodyParameter $params | Format-List
```

```output
AllowedValues           :
AttributeSet            : Engineering
Description             : Active projects for user
Id                      : Engineering_Project
IsCollection            : True
IsSearchable            : True
Name                    : Project
Status                  : Available
Type                    : String
UsePreDefinedValuesOnly : True
AdditionalProperties    : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#directory/customSecurityAttributeDefinitions/$entity]}
```

## [Microsoft Graph](#tab/ms-graph)
[customSecurityAttributeDefinition を作成する](https://learn.microsoft.com/ja-jp/graph/api/directory-post-customsecurityattributedefinitions)

```http
POST https://graph.microsoft.com/v1.0/directory/customSecurityAttributeDefinitions
{
    "attributeSet":"Engineering",
    "description":"Active projects for user",
    "isCollection":true,
    "isSearchable":true,
    "name":"Project",
    "status":"Available",
    "type":"String",
    "usePreDefinedValuesOnly": true
}
```

---

##### 定義済みの値の一覧を含むカスタム セキュリティ属性の定義を追加する

次の例では、定義済みの値の一覧を含む新しいカスタム セキュリティ属性の定義を追加します。

- 属性セット: `Engineering`
- 属性: `Project`
- 属性のデータ型: 文字列のコレクション
- 定義済みの値: `Alpine`、`Baker`、`Cascade`

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[New-MgDirectoryCustomSecurityAttributeDefinition](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/new-mgdirectorycustomsecurityattributedefinition)

```powershell
$params = @{
    attributeSet = "Engineering"
    description = "Active projects for user"
    isCollection = $true
    isSearchable = $true
    name = "Project"
    status = "Available"
    type = "String"
    usePreDefinedValuesOnly = $true
    allowedValues = @(
        @{
            id = "Alpine"
            isActive = $true
        }
        @{
            id = "Baker"
            isActive = $true
        }
        @{
            id = "Cascade"
            isActive = $true
        }
    )
}
New-MgDirectoryCustomSecurityAttributeDefinition -BodyParameter $params | Format-List
```

```output
AllowedValues           :
AttributeSet            : Engineering
Description             : Active projects for user
Id                      : Engineering_Project
IsCollection            : True
IsSearchable            : True
Name                    : Project
Status                  : Available
Type                    : String
UsePreDefinedValuesOnly : True
AdditionalProperties    : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#directory/customSecurityAttributeDefinitions/$entity]}
```

## [Microsoft Graph](#tab/ms-graph)
[customSecurityAttributeDefinition を作成する](https://learn.microsoft.com/ja-jp/graph/api/directory-post-customsecurityattributedefinitions)

```http
POST https://graph.microsoft.com/v1.0/directory/customSecurityAttributeDefinitions
{
    "attributeSet": "Engineering",
    "description": "Active projects for user",
    "isCollection": true,
    "isSearchable": true,
    "name": "Project",
    "status": "Available",
    "type": "String",
    "usePreDefinedValuesOnly": true,
    "allowedValues": [
        {
            "id": "Alpine",
            "isActive": true
        },
        {
            "id": "Baker",
            "isActive": true
        },
        {
            "id": "Cascade",
            "isActive": true
        }
    ]
}
```

---

##### カスタム セキュリティ属性の定義を更新する

次の例では、カスタム セキュリティ属性の定義を更新します。

- 属性セット: `Engineering`
- 属性: `ProjectDate`

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[Update-MgDirectoryCustomSecurityAttributeDefinition](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/update-mgdirectorycustomsecurityattributedefinition)

```powershell
$params = @{
    description = "Target completion date (YYYY/MM/DD)"
}
Update-MgDirectoryCustomSecurityAttributeDefinition -CustomSecurityAttributeDefinitionId "Engineering_ProjectDate" -BodyParameter $params
```

## [Microsoft Graph](#tab/ms-graph)
[カスタムセキュリティ属性の定義を更新](https://learn.microsoft.com/ja-jp/graph/api/customsecurityattributedefinition-update)

```http
PATCH https://graph.microsoft.com/v1.0/directory/customSecurityAttributeDefinitions/Engineering_ProjectDate
{
  "description": "Target completion date (YYYY/MM/DD)",
}
```

---

##### カスタム セキュリティ属性の定義の定義済みの値を更新する

次の例では、カスタム セキュリティ属性の定義の定義済みの値を更新します。

- 属性セット: `Engineering`
- 属性: `Project`
- 属性のデータ型: 文字列のコレクション
- 定義済みの値の更新: `Baker`
- 新しい定義済みの値: `Skagit`

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[Invoke-MgGraphRequest](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-invoke-mggraphrequest)

注

この要求では、**OData-Version** ヘッダーを追加し、値 `4.01` を割り当てる必要があります。

```powershell
$params = @{
    "allowedValues@delta" = @(
        @{
            id = "Baker"
            isActive = $false
        }
        @{
            id = "Skagit"
            isActive = $true
        }
    )
}
$header = @{
    "OData-Version" = 4.01
}
Invoke-MgGraphRequest -Method PATCH -Uri "https://graph.microsoft.com/v1.0/directory/customSecurityAttributeDefinitions/Engineering_Project5" -Headers $header -Body $params
```

## [Microsoft Graph](#tab/ms-graph)
[カスタムセキュリティ属性の定義を更新](https://learn.microsoft.com/ja-jp/graph/api/customsecurityattributedefinition-update)

注

この要求では、**OData-Version** ヘッダーを追加し、値 `4.01` を割り当てる必要があります。

```http
PATCH https://graph.microsoft.com/v1.0/directory/customSecurityAttributeDefinitions/Engineering_Project
{
    "allowedValues@delta": [
        {
            "id": "Baker",
            "isActive": false
        },
        {
            "id": "Skagit",
            "isActive": true
        }
    ]
}
```

---

##### カスタム セキュリティ属性の定義を非アクティブ化する

次の例では、カスタム セキュリティ属性の定義を非アクティブ化します。

- 属性セット: `Engineering`
- 属性: `Project`

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[Update-MgDirectoryCustomSecurityAttributeDefinition](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/update-mgdirectorycustomsecurityattributedefinition)

```powershell
$params = @{
    status = "Deprecated"
}
Update-MgDirectoryCustomSecurityAttributeDefinition -CustomSecurityAttributeDefinitionId "Engineering_ProjectDate" -BodyParameter $params
```

## [Microsoft Graph](#tab/ms-graph)
[カスタムセキュリティ属性の定義を更新](https://learn.microsoft.com/ja-jp/graph/api/customsecurityattributedefinition-update)

```http
PATCH https://graph.microsoft.com/v1.0/directory/customSecurityAttributeDefinitions/Engineering_Project
{
  "status": "Deprecated"
}
```

---

##### すべての定義済みの値を取得する

次の例では、カスタム セキュリティ属性の定義のすべての定義済みの値を取得します。

- 属性セット: `Engineering`
- 属性: `Project`

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[Get-MgDirectoryCustomSecurityAttributeDefinitionAllowedValue](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdirectorycustomsecurityattributedefinitionallowedvalue)

```powershell
Get-MgDirectoryCustomSecurityAttributeDefinitionAllowedValue -CustomSecurityAttributeDefinitionId "Engineering_Project" | Format-List
```

```output
Id                   : Skagit
IsActive             : True
AdditionalProperties : {}

Id                   : Baker
IsActive             : False
AdditionalProperties : {}

Id                   : Cascade
IsActive             : True
AdditionalProperties : {}

Id                   : Alpine
IsActive             : True
AdditionalProperties : {}
```

## [Microsoft Graph](#tab/ms-graph)
[allowedValues](https://learn.microsoft.com/ja-jp/graph/api/customsecurityattributedefinition-list-allowedvalues) の許可された値を一覧表示する

```http
GET https://graph.microsoft.com/v1.0/directory/customSecurityAttributeDefinitions/Engineering_Project/allowedValues
```

---

##### 定義済みの値を取得する

次の例では、カスタム セキュリティ属性の定義の定義済みの値を取得します。

- 属性セット: `Engineering`
- 属性: `Project`
- 定義済みの値: `Alpine`

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[Get-MgDirectoryCustomSecurityAttributeDefinitionAllowedValue](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdirectorycustomsecurityattributedefinitionallowedvalue)

```powershell
Get-MgDirectoryCustomSecurityAttributeDefinitionAllowedValue -CustomSecurityAttributeDefinitionId "Engineering_Project" -AllowedValueId "Alpine" | Format-List
```

```output
Id                   : Alpine
IsActive             : True
AdditionalProperties : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#directory/customSecurityAttributeDefinitions('Engineering_Project')/al
                       lowedValues/$entity]}
```

## [Microsoft Graph](#tab/ms-graph)
[許可された値](https://learn.microsoft.com/ja-jp/graph/api/allowedvalue-get) を取得する

```http
GET https://graph.microsoft.com/v1.0/directory/customSecurityAttributeDefinitions/Engineering_Project/allowedValues/Alpine
```

---

##### 定義済みの値を追加する

次の例では、カスタム セキュリティ属性の定義の定義済みの値を追加します。

`usePreDefinedValuesOnly` が `true` に設定されているカスタム セキュリティ属性には、定義済みの値を追加することができます。

- 属性セット: `Engineering`
- 属性: `Project`
- 定義済みの値: `Alpine`

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[New-MgDirectoryCustomSecurityAttributeDefinitionAllowedValue](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/new-mgdirectorycustomsecurityattributedefinitionallowedvalue)

```powershell
$params = @{
    id = "Alpine"
    isActive = $true
}
New-MgDirectoryCustomSecurityAttributeDefinitionAllowedValue -CustomSecurityAttributeDefinitionId "Engineering_Project" -BodyParameter $params | Format-List
```

```output
Id                   : Alpine
IsActive             : True
AdditionalProperties : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#directory/customSecurityAttributeDefinitions('Engineering_Project')/al
                       lowedValues/$entity]}
```

## [Microsoft Graph](#tab/ms-graph)
[allowedValue の作成](https://learn.microsoft.com/ja-jp/graph/api/customsecurityattributedefinition-post-allowedvalues)

```http
POST https://graph.microsoft.com/v1.0/directory/customSecurityAttributeDefinitions/Engineering_Project/allowedValues
{
    "id":"Alpine",
    "isActive":"true"
}
```

---

##### 定義済みの値を非アクティブ化する

次の例では、カスタム セキュリティ属性の定義の定義済みの値を非アクティブ化します。

- 属性セット: `Engineering`
- 属性: `Project`
- 定義済みの値: `Alpine`

## [Microsoft Graph PowerShell](#tab/ms-powershell)
[Update-MgDirectoryCustomSecurityAttributeDefinitionAllowedValue](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/update-mgdirectorycustomsecurityattributedefinitionallowedvalue)（カスタムセキュリティ属性の許可される値を更新するためのディレクトリ）

```powershell
$params = @{
    isActive = $false
}
Update-MgDirectoryCustomSecurityAttributeDefinitionAllowedValue -CustomSecurityAttributeDefinitionId "Engineering_Project" -AllowedValueId "Alpine" -BodyParameter $params
```

## [Microsoft Graph](#tab/ms-graph)
[allowedValue を更新する](https://learn.microsoft.com/ja-jp/graph/api/allowedvalue-update)

```http
PATCH https://graph.microsoft.com/v1.0/directory/customSecurityAttributeDefinitions/Engineering_Project/allowedValues/Alpine
{
    "isActive":"false"
}
```

---

### よく寄せられる質問

**カスタム セキュリティ属性の定義を削除できますか?**

いいえ、カスタム セキュリティ属性の定義を削除することはできません。 カスタム セキュリティ属性の定義を非アクティブ化することのみが可能です。 カスタム セキュリティ属性を非アクティブ化すると、Microsoft Entra オブジェクトに適用できなくなります。 非アクティブ化されたカスタム セキュリティ属性の定義に対するカスタム セキュリティ属性の割り当ては、自動的には削除されません。 非アクティブ化されるカスタム セキュリティ属性の数に制限はありません。 テナントごとに 500 のアクティブなカスタム セキュリティ属性の定義を設定でき、カスタム セキュリティ属性の定義あたり 100 の定義済みの値を許可できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/fundamentals/custom-security-attributes-manage"} -->
## Microsoft Entra ID でカスタム セキュリティ属性へのアクセスを管理する - Microsoft Entra

- Source: https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-manage
- Service: entra / fundamentals
- Article date: 2025-03-30
- Summary: Microsoft Entra ID でカスタム セキュリティ属性へのアクセスを管理する方法について説明します。

組織内のユーザーが [カスタム セキュリティ属性](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview)を効果的に操作するには、適切なアクセス権を付与する必要があります。 カスタム セキュリティ属性に含める情報によって、カスタム セキュリティ属性を制限する場合もあれば、組織内で広く利用できるようにする場合もあります。 この記事では、カスタム セキュリティ属性へのアクセスを管理する方法について説明します。

### 前提条件

カスタム セキュリティ属性へのアクセスを管理するには、以下が必要です。

- [属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)
- Microsoft Graph PowerShell を使用する場合[の Microsoft.Graph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) モジュール

重要

既定では、 [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、または割り当てに対するアクセス許可がありません。

### 手順 1: 属性の整理方法を決定する

すべてのカスタム セキュリティ属性の定義は、属性セットの一部である必要があります。 属性セットは、関連するカスタム セキュリティ属性をまとめて管理するための手段です。 ご自身の組織の属性セットをどのように追加するかを決定する必要があります。 たとえば、部署やチーム、プロジェクトに基づいて属性セットを追加することが考えられます。 カスタム セキュリティ属性へのアクセス権を付与する権限は、属性セットをどのように整理するかによって決まります。

[Image: 部門別に設定された属性を示す図。]

### 手順 2: 必要なスコープを特定する

"スコープ" は、アクセスが適用されるリソースのセットです。 カスタム セキュリティ属性には、テナント スコープまたは属性セット スコープでロールを割り当てることができます。 広範囲にアクセス権を割り当てる場合は、テナント スコープでロールを割り当てます。 一方、アクセス権を特定の属性セットに制限する場合は、属性セット スコープでロールを割り当てます。

[Image: テナント スコープと属性セット スコープを示す図。]

Microsoft Entra ロールの割り当ては加算方式のモデルであるため、自分で行ったロール割り当ての合計が自分の実際のアクセス許可になります。 たとえば、あるユーザーにテナント スコープでロールを割り当てたうえで、同じユーザーに対して同じロールを属性セット スコープで割り当てた場合、そのユーザーに割り当てられるアクセス許可は依然としてテナント スコープとなります。

### 手順 3: 使用可能なロールを確認する

カスタム セキュリティ属性を扱うためのアクセス権が組織内の誰に必要かを判断する必要があります。 Microsoft Entra には、カスタム セキュリティ属性へのアクセス管理に役立つ組み込みロールが 4 つあります。 必要に応じて、少なくとも [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) ロールを持つユーザーがこれらのロールを割り当てることができます。

- [属性定義管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-administrator)
- [属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)
- [属性定義閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-reader)
- [属性の割り当てリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-reader)

次の表に、カスタム セキュリティ属性の各ロールの大まかな比較を示します。

| アクセス許可 | 属性定義管理者 | 属性割り当て管理者 | 属性定義閲覧者 | 属性割り当て閲覧者 |
| --- | --- | --- | --- | --- |
| 属性セットを読み取る | ✅ | ✅ | ✅ | ✅ |
| 属性の定義を読み取る | ✅ | ✅ | ✅ | ✅ |
| ユーザーとアプリケーション (サービス プリンシパル) に対する属性の割り当てを読み取る |  | ✅ |  | ✅ |
| 属性セットを追加または編集する | ✅ |  |  |  |
| 属性の定義を追加、編集、または非アクティブ化する | ✅ |  |  |  |
| ユーザーとアプリケーション (サービス プリンシパル) に属性を割り当てる |  | ✅ |  |  |

### 手順 4: 委任戦略を決定する

この手順では、カスタム セキュリティ属性へのアクセスを管理する 2 つの方法を説明します。 1 つ目の方法は、それらを一元的に管理すること、2 つ目の方法は、他のユーザーに管理を委任することです。

##### 属性を一元的に管理する

属性定義管理者および属性割り当て管理者の各ロールをテナント スコープで割り当てられた管理者は、カスタム セキュリティ属性のすべての側面を管理できます。 次の図は、カスタム セキュリティ属性の定義と割り当てを 1 人の管理者が行う状態を表しています。

[Image: 一元的に管理されるカスタム セキュリティ属性の図。]

1. 管理者 (Xia) には、属性定義管理者と属性割り当て管理者の両方のロールがテナント スコープで割り当てられています。 その管理者が属性セットを追加し、属性を定義します。
2. その管理者が Microsoft Entra オブジェクトに属性を割り当てます。

属性を一元的に管理する利点は、1 人または 2 人の管理者で属性を管理できることです。 欠点は、カスタム セキュリティ属性の定義または割り当てを行うリクエストがその管理者に集中する可能性があることです。 そのような場合は、管理を委任することをお勧めします。

##### 委任を使用して属性を管理する

カスタム セキュリティ属性をどう定義し、どう割り当てるかについて、1 人の管理者がすべての事情を把握しているとは限りません。 通常、各分野に最も精通しているのは、それぞれの部署やチーム、プロジェクト内のユーザーです。 すべてのカスタム セキュリティ属性を管理する管理者を 1 人または 2 人割り当てる代わりに、属性セット スコープで管理を委任することができます。 これは、他の管理者に各自の仕事に必要なアクセス許可だけを与え、不要なアクセスを防止するという最小特権のベスト プラクティスにも則っています。 次の図は、カスタム セキュリティ属性の管理が複数の管理者に委任された状態を表しています。

[Image: 委任で管理されるカスタム セキュリティ属性の図。]

1. 属性定義管理者ロールをテナント スコープで割り当てられている管理者 (Xia) が属性セットを追加します。 この管理者は、他のユーザーにロールを割り当てるアクセス許可も持っており (特権ロール管理者)、カスタム セキュリティ属性の読み取り、定義、割り当てができる担当者を属性セットごとに委任します。
2. 委任された属性定義管理者 (Alice と Bob) は、各自がアクセスすることを許された属性セット内の属性を定義します。
3. 委任された属性割り当て管理者 (Chandra と Bob) が、その属性セット内の属性を Microsoft Entra オブジェクトに割り当てます。

### 手順 5: 適切なロールとスコープを選択する

どのように属性を整理し、誰がアクセス権を必要としているかをよく理解したら、カスタム セキュリティ属性の適切なロールとスコープを選びます。 次の表は、その選択のうえで役立ちます。

| 付与するアクセス | 割り当てるロール | 範囲 |
| --- | --- | --- |
| - テナント内のすべての属性セットを読み取る<br>- テナント内のすべての属性の定義を読み取る<br>- [テナント内のすべての属性セットを追加または編集する](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add)<br>- [テナント内のすべての属性定義を追加、編集、または非アクティブ化する](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add) | [属性定義管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-administrator) | [Image: テナント スコープのアイコン。]テナント |
| - スコープ指定された属性セット内の属性の定義を読み取る<br>- [スコープ属性セット内の属性定義を追加、編集、または非アクティブ化する](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add)<br>- スコープ指定された属性セットを更新**できません**<br>- 他の属性セットの読み取り、追加、または更新**ができない** | [属性定義管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-administrator) | [Image: 属性セットスコープのアイコン。]属性セット |
| - テナント内のすべての属性セットを読み取る<br>- テナント内のすべての属性の定義を読み取る<br>- ユーザーのテナント内のすべての属性の割り当てを読み取る<br>- アプリケーション (サービス プリンシパル) のテナント内のすべての属性の割り当てを読み取る<br>- [テナント内のすべての属性をユーザーに割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/users/users-custom-security-attributes)<br>- [テナント内のすべての属性をアプリケーション (サービス プリンシパル) に割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/custom-security-attributes-apps)<br>- [テナント内のすべての属性にプリンシパル属性を使用する Azure ロールの割り当て条件を作成する](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/conditions-format#attributes) | [属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator) | [Image: テナント スコープのアイコン。]テナント |
| - スコープ指定された属性セット内の属性の定義を読み取る<br>- スコープ指定された属性セット内の、ユーザーを対象とした属性を使用する属性割り当てを読み取る<br>- スコープ指定された属性セット内の、アプリケーション (サービス プリンシパル) を対象とした属性を使用する属性割り当てを読み取る<br>- [スコープ属性セット内の属性をユーザーに割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/users/users-custom-security-attributes)<br>- [スコープ属性セット内の属性をアプリケーション (サービス プリンシパル) に割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/custom-security-attributes-apps)<br>- [スコープ属性セット内のすべての属性にプリンシパル属性を使用する Azure ロールの割り当て条件を作成する](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/conditions-format#attributes)<br>- 他の属性セット内の属性を読み取**ることができません**<br>- 他の属性セット内の属性を使用した属性の割り当てを読み取ることは**できない** | [属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator) | [Image: 属性セットスコープのアイコン。]属性セット |
| - テナント内のすべての属性セットを読み取る<br>- テナント内のすべての属性の定義を読み取る | [属性定義閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-reader) | [Image: テナント スコープのアイコン。]テナント |
| - スコープ指定された属性セット内の属性の定義を読み取る<br>- 他の属性セットを読み取**ることができません** | [属性定義閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-reader) | [Image: 属性セットスコープのアイコン。]属性セット |
| - テナント内のすべての属性セットを読み取る<br>- テナント内のすべての属性の定義を読み取る<br>- ユーザーのテナント内のすべての属性の割り当てを読み取る<br>- アプリケーション (サービス プリンシパル) のテナント内のすべての属性の割り当てを読み取る | [属性の割り当てリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-reader) | [Image: テナント スコープのアイコン。]テナント |
| - スコープ指定された属性セット内の属性の定義を読み取る<br>- スコープ指定された属性セット内の、ユーザーを対象とした属性を使用する属性割り当てを読み取る<br>- スコープ指定された属性セット内の、アプリケーション (サービス プリンシパル) を対象とした属性を使用する属性割り当てを読み取る<br>- 他の属性セット内の属性を読み取**ることができません**<br>- 他の属性セット内の属性を使用した属性の割り当てを読み取ることは**できない** | [属性の割り当てリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-reader) | [Image: 属性セットスコープのアイコン。]属性セット |

### 手順 6: ロールを割り当てる

適切なユーザーにアクセス権を与えるには、こちらの手順に従って、いずれかのカスタム セキュリティ属性ロールを割り当てます。

#### 属性セット スコープでロールを割り当てる

次の例では、Engineering という名前の属性セット スコープで、カスタム セキュリティ属性ロールをプリンシパルに割り当てる方法を示します。

## [管理センター](#tab/admin-center)
1. [属性割り当て管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)にサインインします。
2. **[Entra ID]**&gt;**[カスタム セキュリティ属性]** に移動します。
3. アクセス権を付与する属性セットを選択します。
4. **[ロールと管理者] を選択します**。

    [Image: 属性セットスコープでの属性ロールの割り当てのスクリーンショット。]
5. カスタム セキュリティ属性のロールに対する割り当てを追加します。

    注

    Microsoft Entra Privileged Identity Management (PIM) を使用している場合、属性セット スコープでの資格のあるロールの割り当ては現在サポートされません。 属性セット スコープでの永続的なロールの割り当てがサポートされています。

## [PowerShell](#tab/ms-powershell)
[New-MgRoleManagementDirectoryRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.governance/new-mgrolemanagementdirectoryroleassignment)

```powershell
$roleDefinitionId = "58a13ea3-c632-46ae-9ee0-9c0d43cd7f3d"
$principalId = "aaaaaaaa-bbbb-cccc-1111-222222222222"
$directoryScopeId = "/attributeSets/Engineering"
$roleAssignment = New-MgRoleManagementDirectoryRoleAssignment -RoleDefinitionId $roleDefinitionId -PrincipalId $principalId -DirectoryScopeId $directoryScopeId
```

## [Microsoft Graph](#tab/ms-graph)
[unifiedRoleAssignment の作成](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-post-roleassignments)

```http
POST https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments
Content-type: application/json

{
    "@odata.type": "#microsoft.graph.unifiedRoleAssignment",
    "roleDefinitionId": "58a13ea3-c632-46ae-9ee0-9c0d43cd7f3d",
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
    "directoryScopeId": "/attributeSets/Engineering"
}
```

---

#### テナント スコープでロールを割り当てる

次の例では、テナント スコープでプリンシパルにカスタム セキュリティ属性ロールを割り当てる方法を示します。

## [管理センター](#tab/admin-center)
1. [属性割り当て管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)にサインインします。
2. Entra ID役割 & 管理者に移動します。

    [Image: テナント スコープでの属性ロールの割り当てのスクリーンショット。]
3. カスタム セキュリティ属性のロールに対する割り当てを追加します。

## [PowerShell](#tab/ms-powershell)
[New-MgRoleManagementDirectoryRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.governance/new-mgrolemanagementdirectoryroleassignment)

```powershell
$roleDefinitionId = "58a13ea3-c632-46ae-9ee0-9c0d43cd7f3d"
$principalId = "aaaaaaaa-bbbb-cccc-1111-222222222222"
$directoryScopeId = "/"
$roleAssignment = New-MgRoleManagementDirectoryRoleAssignment -RoleDefinitionId $roleDefinitionId -PrincipalId $principalId -DirectoryScopeId $directoryScopeId
```

## [Microsoft Graph](#tab/ms-graph)
[unifiedRoleAssignment の作成](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-post-roleassignments)

```http
POST https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments
Content-type: application/json

{
    "@odata.type": "#microsoft.graph.unifiedRoleAssignment",
    "roleDefinitionId": "58a13ea3-c632-46ae-9ee0-9c0d43cd7f3d",
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
    "directoryScopeId": "/"
}
```

---

### カスタム セキュリティ属性の監査ログ

監査やトラブルシューティングなどの目的で、カスタム セキュリティ属性の変更に関する情報が必要になることがあります。 定義や割り当てが変更されるたびに、そのアクティビティがログに記録されます。

カスタム セキュリティ属性の監査ログでは、新しい定義の追加や、ユーザーへの属性値の割り当てなど、カスタム セキュリティ属性に関連するアクティビティの履歴を確認することができます。 カスタム セキュリティ属性に関連してログされるアクティビティは次のとおりです。

- 属性セットを追加する
- 属性セットにカスタム セキュリティ属性定義を追加する
- 属性セットを更新する
- servicePrincipal に割り当てられた属性値を更新する
- ユーザーに割り当てられた属性値を更新する
- 属性セットのカスタム セキュリティ属性定義を更新する

#### 属性の変更の監査ログを表示する

カスタム セキュリティ属性の監査ログを表示するには、Microsoft Entra 管理センターにサインインし、[ **監査ログ]** を参照して、[ **カスタム セキュリティ**] を選択します。 カスタム セキュリティ属性の監査ログを表示するには、次のいずれかのロールが割り当てられている必要があります。 必要に応じて、少なくとも [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) ロールを持つユーザーがこれらのロールを割り当てることができます。

- [属性ログ リーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-log-reader)
- [属性ログ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-log-administrator)

[Image: [カスタム セキュリティ] タブが選択されている監査ログのスクリーンショット。]

Microsoft Graph API を使用してカスタム セキュリティ属性監査ログを取得する方法については、 [`customSecurityAttributeAudit` リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/customsecurityattributeaudit)を参照してください。 詳細については、 [Microsoft Entra 監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)を参照してください。

#### 診断設定

追加の処理のためにカスタム セキュリティ属性の監査ログを別の宛先にエクスポートするには、診断設定を使用します。 カスタム セキュリティ属性の診断設定を作成して構成するには、 [属性ログ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-log-administrator) ロールが割り当てられている必要があります。

ヒント

属性の割り当てが誤って公開されてしまわないように、カスタム セキュリティ属性監査ログとディレクトリ監査ログを別にしておくことをお勧めします。

次のスクリーンショットは、カスタム セキュリティ属性の診断設定を示したものです。 詳細については、「 [診断設定を構成する方法」を](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-diagnostic-settings)参照してください。

[Image: [カスタム セキュリティ属性] タブが選択されている診断設定のスクリーンショット。]

### 監査ログの動作に対する変更

一般提供の開始に伴って、カスタム セキュリティ属性監査ログに変更が加えられましたが、これは日常業務に影響する可能性があります。 プレビュー期間中にカスタム セキュリティ属性監査ログを使用していた場合に、監査ログ操作が中断されないよう実行する必要があるアクションを以下に示しています。

- 新しい監査ログの場所を使用する
- 監査ログを表示するために属性ログのロールを割り当てる
- 監査ログをエクスポートするための新しい診断設定を作成する

#### 新しい監査ログの場所を使用する

プレビュー期間中は、カスタム セキュリティ属性監査ログはディレクトリ監査ログのエンドポイントに書き込まれていました。 2023 年 10 月に、カスタム セキュリティ属性監査ログ専用の新しいエンドポイントが追加されました。 次のスクリーンショットには、ディレクトリ監査ログと新しいカスタム セキュリティ属性監査ログの場所が示されています。 Microsoft Graph API を使用してカスタム セキュリティ属性監査ログを取得するには、 [`customSecurityAttributeAudit` リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/customsecurityattributeaudit)を参照してください。

[Image: [ディレクトリ] タブと [カスタム セキュリティ] タブを示す監査ログのスクリーンショット。]

カスタム セキュリティ監査ログがディレクトリとカスタム セキュリティ属性両方の監査ログ エンドポイントに書き込まれる移行期間があります。 その期間の後は、カスタム セキュリティ属性監査ログを検索するには、カスタム セキュリティ属性監査ログのエンドポイントを使う必要があります。

次の表に、移行期間中にカスタム セキュリティ属性監査ログを見つけることができるエンドポイントを示しています。

| イベントの日付 | ディレクトリ エンドポイント | カスタム セキュリティ属性エンドポイント |
| --- | --- | --- |
| 2023 年 10 月 | ✅ | ✅ |
| 2024 年 2 月 |  | ✅ |

#### 監査ログを表示するために属性ログのロールを割り当てる

プレビュー期間中、カスタム セキュリティ属性監査ログは、ディレクトリ監査ログに少なくとも [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロールを持つユーザーが表示できます。 新しいエンドポイントを使用すると、これらのロールを使ってカスタム セキュリティ属性監査ログを見ることはできなくなります。 カスタム セキュリティ属性監査ログを表示するには、 [属性ログ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-log-reader) ロールまたは [属性ログ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-log-administrator) ロールが割り当てられている必要があります。

#### 監査ログをエクスポートするための新しい診断設定を作成する

プレビュー期間中に監査ログをエクスポートするよう構成した場合は、カスタム セキュリティ属性監査ログは現在の診断設定に送信されました。 カスタム セキュリティ監査属性の監査ログを引き続き受信するには、前の診断設定セクションで説明したように、新しい 診断設定 を作成する必要があります。
<!-- /MSL-PAGE -->
