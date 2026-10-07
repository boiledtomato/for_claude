# Microsoft Learn — Microsoft Entra / ユーザー・デバイス・ロール・マネージド ID (part 5)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 46

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/quickstart-app-registration-limits"} -->
## 無制限のアプリ登録を作成するアクセス許可を持つカスタム ロールを作成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/quickstart-app-registration-limits
- Service: entra-id / role-based-access-control
- Article date: 2025-01-03
- Summary: カスタム ロールを割り当てて、Microsoft Entra ID で無制限のアプリ登録を許可します。

このクイック スタート ガイドでは、無制限の数のアプリの登録を作成するアクセス許可があるカスタム ロールを作成した後、そのロールをユーザーに割り当てます。 ロールを割り当てられたユーザーは、Microsoft Entra 管理センター、Microsoft Graph PowerShell、または Microsoft Graph API を使用して、アプリケーションの登録を作成できるようになります。 組み込みのアプリケーション開発者ロールとは異なり、このカスタム ロールには、無制限の数のアプリの登録を作成する権限が付与されます。 アプリケーション開発者ロールは権限を付与しますが、 [ディレクトリ全体](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions)のオブジェクト クォータに達しないように、作成されたオブジェクトの合計数は 250 に制限されます。 Microsoft Entra カスタム ロールの作成と割り当てに必要な最小限の特権を持つロールは、特権ロール管理者です。

Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/free/) してください。

### 前提条件

- Microsoft Entra ID P1 または P2 ライセンス
- 特権ロール管理者
- [PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) を使用する場合の Microsoft Graph PowerShell モジュール
- Microsoft Graph API の Graph エクスプローラーを使用する場合の管理者の同意

詳細については、「powerShell または Graph Explorerを使用するための 前提条件」を参照してください。

## [管理センター](#tab/admin-center)
#### カスタム ロールの作成

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[ロールと管理者]** を参照します。
3. [ **新しいカスタム ロール**] を選択します。

    [Image: Microsoft Entra 管理センターの [ロールと管理者] ページのスクリーンショット。]
4. [ **基本** ] タブで、ロールの名前として「アプリケーション登録作成者」、ロールの説明に「無制限の数のアプリケーション登録を作成できる」と入力し、[ **次へ**] を選択します。

    [Image: カスタム ロールの名前と説明を指定する [基本] タブのスクリーンショット。]
5. [ **アクセス許可** ] タブで、検索ボックスに「microsoft.directory/applications/create」と入力し、目的のアクセス許可の横にあるチェック ボックスをオンにして、[ **次へ**] を選択します。

    [Image: カスタム ロールのアクセス許可を選択する [アクセス許可] タブのスクリーンショット。]
6. [ **確認と作成** ] タブで、アクセス許可を確認し、[ **作成**] を選択します。

#### ロールを割り当てる

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[ロールと管理者]** を参照します。
3. アプリケーション登録作成者ロールを選択し、[ **割り当ての追加]** を選択します。
4. 目的のユーザーを選択し、[ **選択** ] をクリックしてユーザーをロールに追加します。

これで完了です。 このクイックスタートでは、無制限の数のアプリの登録を作成するアクセス許可を持つカスタム ロールを作成した後、そのロールをユーザーに割り当てることに成功しました。

ヒント

Microsoft Entra 管理センターを使用してアプリケーションにロールを割り当てるには、[割り当て] ページの [検索] ボックスにアプリケーションの名前を入力します。 アプリケーションは既定では一覧に表示されませんが、検索結果に返されます。

#### アプリの登録とアクセス許可

アプリケーションの登録を作成する権限を付与するために使用できるアクセス許可は 2 つあり、それぞれ動作が異なります。

- microsoft.directory/applications/createAsOwner: このアクセス許可を割り当てると、作成者は、作成されたアプリの登録の最初の所有者として追加され、作成されたアプリの登録は、その作成者の 250 という作成オブジェクト クォータのカウント対象になります。
- microsoft.directory/applications/create: このアクセス許可を割り当てると、作成者は、作成されたアプリの登録の最初の所有者として追加されず、作成されたアプリの登録は、その作成者の 250 という作成オブジェクト クォータのカウント対象になりません。 担当者がディレクトリ レベルのクォータに達するまでアプリの登録を作成できないようにするものはないため、このアクセス許可は慎重に使用してください。 両方のアクセス許可が割り当てられている場合は、このアクセス許可が優先されます。

## [PowerShell](#tab/ms-powershell)
#### カスタム ロールの作成

次の PowerShell スクリプトを実行して、新しいロールを作成します。

```powershell
# Basic role information
$displayName = "Application Registration Creator"
$description = "Can create an unlimited number of application registrations."
$templateId = (New-Guid).Guid

# Set of permissions to grant
$allowedResourceAction =
@(
    "microsoft.directory/applications/create"
    "microsoft.directory/applications/createAsOwner"
)
$rolePermissions = @{'allowedResourceActions'= $allowedResourceAction}

# Create new custom admin role
$customRole = New-MgRoleManagementDirectoryRoleDefinition -DisplayName $displayName -Description $description -RolePermissions $rolePermissions -TemplateId $templateId -IsEnabled:$true
```

#### ロールを割り当てる

次の PowerShell スクリプトを使用してロールを割り当てます。

```powershell
# Get the user and role definition you want to link
$user = Get-MgUser -Filter "UserPrincipalName eq 'Adam@contoso.com'"
$roleDefinition = Get-MgRoleManagementDirectoryRoleDefinition -Filter "DisplayName eq 'Application Registration Creator'"

# Get resource scope for assignment
$resourceScope = '/'

# Create a scoped role assignment
$roleAssignment = New-MgRoleManagementDirectoryRoleAssignment -DirectoryScopeId $resourceScope -RoleDefinitionId $roleDefinition.Id -PrincipalId $user.Id
```

## [Graph API](#tab/ms-graph)
#### カスタム ロールの作成

[Create unifiedRoleDefinition](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-post-roledefinitions) API を使用してカスタム ロールを作成します。

```http
POST https://graph.microsoft.com/v1.0/roleManagement/directory/roleDefinitions
```

本文

```http
{
    "description": "Can create an unlimited number of application registrations.",
    "displayName": "Application Registration Creator",
    "isEnabled": true,
    "rolePermissions":
    [
        {
            "allowedResourceActions":
            [
                "microsoft.directory/applications/create"
                "microsoft.directory/applications/createAsOwner"
            ]
        }
    ],
    "templateId": "<PROVIDE NEW GUID HERE>",
    "version": "1"
}
```

#### ロールを割り当てる

[Create unifiedRoleAssignment](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-post-roleassignments) API を使用して、カスタム ロールを割り当てます。 ロールの割り当てでは、セキュリティ プリンシパル ID (ユーザーでもサービス プリンシパルでも可)、ロール定義 (ロール) ID、および Microsoft Entra リソース スコープを組み合わせます。

```http
POST https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments
```

本文

```http
{
    "@odata.type": "#microsoft.graph.unifiedRoleAssignment",
    "principalId": "<PROVIDE OBJECTID OF USER TO ASSIGN HERE>",
    "roleDefinitionId": "<PROVIDE OBJECTID OF ROLE DEFINITION HERE>",
    "directoryScopeId": "/"
}
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/role-definitions-list"} -->
## Microsoft Entra ロールの定義を一覧表示する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/role-definitions-list
- Service: entra-id / role-based-access-control
- Article date: 2025-01-03
- Summary: Microsoft Entra 管理センター、Microsoft Graph PowerShell、または Microsoft Graph API を使用して、Microsoft Entra の組み込みロール定義とカスタム ロール定義とそのアクセス許可を一覧表示する方法について説明します。

この記事では、Microsoft Entra 管理センター、Microsoft Graph PowerShell、または Microsoft Graph API を使用して、Microsoft Entra の組み込みロール定義とカスタム ロール定義とそのアクセス許可を一覧表示する方法について説明します。

ロールの定義は、読み取り、書き込み、削除などの実行できるアクセス許可のコレクションです。 これは通常、ロールと呼ばれます。 Microsoft Entra ID には 100 を超える組み込みロールがあります。また、独自のカスタム ロールを作成することもできます。 "これらのロールで何ができるか" については、ロールごとのアクセス許可の詳細な一覧にアクセスして参照してください。

### 前提条件

- [PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) を使用する場合の Microsoft Graph PowerShell モジュール
- Microsoft Graph API の Graph エクスプローラーを使用する場合の管理者の同意

詳細については、「powerShell または Graph Explorerを使用するための 前提条件」を参照してください。

### Microsoft Entra ロールの定義を一覧表示する

## [管理センター](#tab/admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[Entra ID]**&gt;**[ロールと管理者]** を参照します。

    [Image: Microsoft Entra 管理センターの [ロールと管理者] ページのスクリーンショット。]
3. ロール名を選択してロールを開きます。 ロールの横にチェック マークを追加しないでください。

    [Image: ロール名の上にマウス ポインターを合わせて表示された [ロールと管理者] ページのスクリーンショット。]
4. [ **説明]** を選択すると、ロールの概要とアクセス許可の一覧が表示されます。

    このページには、ロールの管理について説明している関連ドキュメントへのリンクが含まれています。

    [Image: ロールの説明を示す [ロールと管理者] ページのスクリーンショット。]

## [PowerShell](#tab/ms-powershell)
PowerShell を使用して Microsoft Entra ロールを一覧表示するには、次の手順を実行します。

1. PowerShell ウィンドウを開きます。 必要に応じて、 [Install-Module](https://learn.microsoft.com/ja-jp/powershell/module/powershellget/install-module) を使用して Microsoft Graph PowerShell をインストールします。 詳細については、「powerShell または Graph Explorerを使用するための 前提条件」を参照してください。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    ```
2. PowerShell ウィンドウで、 [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) を使用してテナントにサインインします。

    ```powershell
    Connect-MgGraph -Scopes "RoleManagement.Read.All"
    ```
3. [Get-MgRoleManagementDirectoryRoleDefinition](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.governance/get-mgrolemanagementdirectoryroledefinition) を使用してロールを取得します。

    ```powershell
    # Get all role definitions
    Get-MgRoleManagementDirectoryRoleDefinition
    
    # Get single role definition by ID
    Get-MgRoleManagementDirectoryRoleDefinition -UnifiedRoleDefinitionId 00000000-0000-0000-0000-000000000000
    
    # Get single role definition by templateId
    Get-MgRoleManagementDirectoryRoleDefinition -Filter "TemplateId eq 'c4e39bd9-1100-46d3-8c65-fb160da0071f'"
    
    # Get role definition by displayName
    Get-MgRoleManagementDirectoryRoleDefinition -Filter "displayName eq 'Helpdesk Administrator'"
    ```
4. ロールのアクセス許可の一覧を表示するには、次のコマンドレットを使用します。

    ```powershell
    # Do this avoid truncation of the list of permissions
    $FormatEnumerationLimit = -1
    
    (Get-MgRoleManagementDirectoryRoleDefinition -Filter "displayName eq 'Conditional Access Administrator'").RolePermissions | Format-list
    ```

## [Graph API](#tab/ms-graph)
Graph [エクスプローラー](https://aka.ms/ge)で Microsoft Graph API を使用して Microsoft Entra ロールを一覧表示するには、次の手順に従います。

1. [Graph エクスプローラー](https://aka.ms/ge)にサインインします。
2. ドロップダウンから HTTP メソッドとして **GET** を選択します。
3. **v1.0** の API バージョンを選択します。
4. [List unifiedRoleDefinitions](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-list-roledefinitions) API を使用して、すべてのロール定義を一覧表示します。

    ```http
    GET https://graph.microsoft.com/v1.0/roleManagement/directory/roleDefinitions
    ```

    displayName で特定のロールを一覧表示するには、この形式を使用します。

    ```http
    GET https://graph.microsoft.com/v1.0/roleManagement/directory/roleDefinitions?$filter = displayName eq 'Helpdesk Administrator'
    ```
5. [ **クエリの実行** ] を選択してロールを一覧表示します。

    応答の例を次に示します。

    ```http
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#roleManagement/directory/roleDefinitions",
        "value": [
            {
                "id": "729827e3-9c14-49f7-bb1b-9608f156bbb8",
                "description": "Can reset passwords for non-administrators and Helpdesk Administrators.",
                "displayName": "Helpdesk Administrator",
                "isBuiltIn": true,
                "isEnabled": true,
                "resourceScopes": [
                    "/"
                ],
    
        ...
    
    ```
6. ロールのアクセス許可を表示するには、次の API を使用します。

    ```http
    GET https://graph.microsoft.com/v1.0/roleManagement/directory/roleDefinitions?$filter=DisplayName eq 'Conditional Access Administrator'&$select=rolePermissions
    ```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/security-emergency-access"} -->
## 緊急アクセス用管理者アカウントを管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access
- Service: entra-id / role-based-access-control
- Article date: 2026-06-04
- Summary: この記事では、緊急アクセス用アカウントを使用して、Microsoft Entra 組織から誤ってロックアウトされないようにする方法について説明します。

サインインまたはロールのアクティブ化ができないため、誤ってMicrosoft Entra組織からロックアウトされないようにすることが重要です。 組織内に 2 つ以上の *緊急アクセス アカウント* を作成することで、管理アクセスの不注意による影響を軽減できます。

グローバル管理者ロールを持つユーザー アカウントはシステムで高い特権を持ち、このロールにはグローバル管理者ロールを持つ緊急アクセス アカウントが含まれます。 緊急アクセス アカウントは、通常の管理アカウントを使用できない緊急時、または「ブレークグラス」シナリオでのみ使用してください。 緊急アカウントの使用を、絶対に必要な時間のみに制限します。

この記事では、Microsoft Entra ID で緊急アクセス用アカウントを管理するためのガイドラインを提供します。

### 緊急アクセス用アカウントを使用する理由

次のような場合に緊急アクセス用アカウントの使用が必要になることがあります。

- ユーザー アカウントがフェデレーションされており、携帯ネットワークの途絶または ID プロバイダーの停止のためにフェデレーションを現在使用できない場合。 たとえば、環境内の ID プロバイダー ホストがダウンした場合、Microsoft Entra IDが ID プロバイダーにリダイレクトされるときに、ユーザーはサインインできない可能性があります。
- 管理者は、Microsoft Entra多要素認証を使用して登録し、個々のデバイスがすべて使用できないか、サービスが使用できません。 ユーザーは、ロールをアクティブにするための多要素認証を完了できない可能性があります。 たとえば、携帯ネットワークが停止すると、ユーザーがデバイスに対して登録したただ 2 つの認証メカニズムである、電話呼び出しへの応答も、テキスト メッセージの受信も、できなくなります。
- 最新のグローバル管理者アクセス権を持つユーザーが組織を離れる。 Microsoft Entra ID では最後の全体管理者アカウントを削除できないようになっていますが、オンプレミスでアカウントが削除または無効化されるのを防ぐことはできません。 いずれの場合も、アカウントを復旧できなくなる可能性があります。
- 自然災害などの予期しない状況が発生した場合。携帯電話や他のネットワークが利用できなくなる可能性があります。
- すべてのグローバル管理者ロールと特権ロール管理者ロールの割り当ては有効です (アクティブではありません)。アクティブ化には承認が必要です。承認者は選択されていません (または、選択したすべての承認者がディレクトリから削除されました)。 アクティブなグローバル管理者と特権ロール管理者は、何も選択されていない場合は既定の承認者ですが、アクティブなものがないため、アクティブ化を承認できるユーザーはいません。テナント管理は効果的にロックされます。

### 緊急アクセス用アカウントを作成する

複数の緊急アクセス用アカウントを作成します。 これらのアカウントは、 \*.onmicrosoft.com ドメインを使用し、オンプレミス環境からフェデレーションまたは同期されていない、クラウド専用のアカウントである必要があります。 大まかに言うと、次の手順に従います。

1. 既存の緊急アクセス アカウントを見つけるか、 [新しいクラウド専用ユーザーを作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users#create-a-new-user) し、グローバル管理者ロールを割り当てます。
2. 緊急アクセスアカウントに対して、次のいずれかのパスワードレス認証方法を選択します。 これらの方法は、必須の多要素認証要件を満たします。

    - [パスキー (FIDO2)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2) (推奨)
    - 既に組織に公開キー基盤 (PKI) がセットアップされている場合、[証明書ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)を行う
3. 前の手順で選択した認証方法の資格情報を登録します。

    - **Passkey (FIDO2):**[組織のパスキー (FIDO2) を有効に](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2)してから、[パスキー (FIDO2) を登録](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-register-passkey-with-security-key)します
    - **証明書ベースの認証**: [証明書ベースの認証を構成する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication)
4. サインインをブロックまたは制限する条件付きアクセス ポリシーから緊急アクセス アカウントが除外されていることを確認します。 前の手順で登録したフィッシング対策認証方法は、アカウントを保護します。適用された条件付きアクセス ポリシーは、アカウントが設計されている緊急の間にサインインを防ぐことができます。 レポート専用ポリシーはアクセスをブロックせず、除外を必要としません。 詳細については、「 条件付きアクセスに関する考慮事項」を参照してください。
5. アカウントの資格情報を安全に保管する。
6. サインイン ログと監査ログを監視する。
7. アカウントを定期的に検証する。

### 構成要件

これらのアカウントを構成するときは、次の要件が満たされていることを確認します。

- 緊急アクセスアカウントを組織内の個々のユーザーに関連付けないでください。 管理チームの複数のメンバーが利用できる既知の安全な場所に資格情報を格納します。 これらのアカウントを、電話などの従業員が提供するデバイスに接続しないでください。 このアプローチは、緊急アクセスアカウント管理を統合します。 ほとんどの組織では、Microsoft Cloudインフラストラクチャだけでなく、オンプレミス環境、フェデレーション SaaS アプリケーション、およびその他の重要なシステムについても緊急アクセス アカウントが必要です。

    または、管理者用に個別の緊急アクセス アカウントを作成することもできます。 このソリューションは、アカウンタビリティを高め、管理者がリモートの場所から緊急アクセスアカウントを使用できるようにします。
- 緊急アクセスアカウントに強力な認証を使用し、他の管理アカウントと同じ認証方法を使用していないことを確認します。 たとえば、通常の管理者アカウントで、強力な認証に Microsoft Authenticator アプリを使用している場合、緊急用アカウントには FIDO2 セキュリティ キーを使用します。 認証プロセスに外部要件を追加しないようにするには、 [さまざまな認証方法の依存関係を考慮してください](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-in-credentials)。
- デバイスまたは資格情報は、有効期限が切れておらず、使用不足による自動クリーンアップの対象になっていないことが必要です。
- Microsoft Entra Privileged Identity Managementで、緊急アクセス アカウントの対象ではなく、グローバル管理者ロールの割り当てを永続的にアクティブにします。
- これらの緊急アクセス アカウントを使用する権限を持つ個人は、指定されたセキュリティで保護されたワークステーション、または特権アクセス ワークステーションなどの同様のクライアント コンピューティング環境を利用する必要があります。 緊急アクセス アカウントを操作するときは、これらのワークステーションを使用します。 指定されたワークステーションがある Microsoft Entra テナントの構成の詳細については、特権アクセス ソリューション [の展開](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-deployment)に関するページを参照してください。

### フェデレーション ガイド

一部の組織では、Active Directory Domain Services と Active Directory フェデレーション サービス (AD FS) または類似の ID プロバイダーを使用して、Microsoft Entra ID にフェデレーションします。 オンプレミス システムの緊急アクセスとクラウド サービスの緊急アクセスを区別し、相互に依存関係を持たないようにします。 他のシステムからの緊急アクセス特権を持つアカウントの認証をマスターまたはソーシングすると、それらのシステムで障害が発生した場合に不要なリスクが発生します。

### アカウントの資格情報を安全に保管する

緊急アクセス アカウントの資格情報がセキュリティで保護され、使用を許可されている個人にのみ認識されていることを確認します。 たとえば、Microsoft Entra ID [FIDO2 セキュリティ キー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-passkeys-fido2)、Windows Server Active Directory のスマートカードを使用できます。 資格情報は、安全で防火性の高い安全な場所に保管します。

### 条件付きアクセスに関する考慮事項

サインインをブロックまたは制限する条件付きアクセス ポリシーから緊急アクセス アカウントを除外します。 レポート専用ポリシーはアクセスをブロックせず、緊急アカウントを除外する必要はありません。 緊急アクセス アカウントが、MFA、準拠デバイス、または別の制御を必要とする条件付きアクセス ポリシーの対象である場合、そのアカウントが設計されている緊急シナリオの間は使用できない可能性があります。

条件付きアクセスの展開を計画するときは、次の点を考慮してください。

- **EmergencyAccess** などの緊急アクセス アカウント専用のセキュリティ グループを作成し、サインインをブロックまたは制限する条件付きアクセス ポリシーからこのグループを除外します。
- 緊急アクセス アカウントが現在の条件付きアクセス構成で正常にサインインできることを定期的にテストします (たとえば、四半期ごと)。
- 障害発生時に有効にして重要なユーザーのアクセスを復元できるコンティンジェンシー条件付きアクセス ポリシーを作成します。 詳細については、「 [回復性のあるアクセス制御管理戦略を作成する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-resilient-controls)」を参照してください。

条件付きアクセスの除外の計画の詳細については、「 [条件付きアクセスの展開を計画する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/plan-conditional-access)。

### セキュリティ ガードレールの概要

次のチェックリストは、緊急アクセス アカウントのセキュリティ要件をまとめたものです。

- 冗長性を確保するために、少なくとも 2 つの緊急アクセス アカウントを維持します。
- フェデレーション ID プロバイダーに依存しないクラウド専用アカウント (`.onmicrosoft.com` ドメイン) を使用します。
- 通常の管理者アカウントとは異なるフィッシングに強い認証方法 (FIDO2 セキュリティ キーまたは証明書ベースの認証) を使用します。
- 資格情報とデバイスの有効期限が切れていないか、自動クリーンアップの対象になっていないことを確認します。
- Privileged Identity Management で、緊急アカウントにグローバル管理者ロールを永続的にアクティブ（対象外）として割り当てます。
- 緊急アクセス アカウントを使用する場合は、指定されたセキュリティで保護されたワークステーションまたは [特権アクセス ワークステーション](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-deployment) を使用する必要があります。
- 承認された個人がアクセスできる、安全で防火性の高い別の場所に資格情報を保存します。
- サインインをブロックまたは制限する条件付きアクセス ポリシーから緊急アクセス アカウントを除外します。 レポートのみのポリシーでは、除外は必要ありません。
- 不要な使用または未承認の使用を検出するために、アラートを含む緊急アクセス アカウントのすべてのサインインと監査ログ アクティビティを監視します。
- 少なくとも 90 日ごとにアカウント機能を検証します。

### 監査可能性とコンプライアンス

規制対象の業界の組織では、緊急アクセス アカウントの使用が適切に管理されていることを示す必要がある場合があります。 この記事で説明する監視と検証のプラクティスでは、監査可能性がサポートされています。

- **サインインと監査ログの監視**: 緊急アクセス アカウントを使用するたびにアラートを構成します。 レビューのためにサインイン ログと監査ログをキャプチャします。 詳細については、この記事 の「サインイン ログと監査ログの監視」 を参照してください。
- **事後レビュー**: 緊急アクセス アカウントを使用した後、レビューを実施して、使用が承認されたかどうか、および実行されたアクションが適切かどうかを判断します。 詳細については、この記事の事後検証チームを編成するを参照してください。
- **定期的な検証**: 少なくとも 90 日ごとにアカウント検証訓練を実行します。これには、承認されたユーザーリストの確認、サインインと管理タスクの機能のテストが含まれます。 詳細については、この記事の 「アカウントを定期的に検証する 」を参照してください。
- **コンプライアンス マッピング**: 組織が HIPAA 規制に準拠する必要がある場合、Microsoftは、緊急アクセス アカウントが HIPAA 緊急アクセス手順要件にどのようにマップされるかに関するガイダンスを提供します。 詳細については、 [HIPAA アクセス制御に関するページを参照](https://learn.microsoft.com/ja-jp/entra/standards/hipaa-access-controls)してください。

### サインイン ログと監査ログを監視する

緊急アカウントからのサインインと監査ログアクティビティを監視し、他の管理者に通知をトリガーします。 緊急アクセスアカウントのアクティビティを監視する場合、これらのアカウントがテストまたは実際の緊急時にのみ使用されていることを確認できます。 Azure Monitor、Microsoft Sentinel、またはその他のツールを使用してサインイン ログを監視し、緊急アクセスアカウントがサインインするたびに管理者に電子メールと SMS アラートをトリガーできます。 このセクションでは、Azure Monitor の使用方法について説明します。

#### 前提条件

- [Microsoft Entra サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)を Azure Monitor に送信します。

#### 緊急アクセス アカウントのオブジェクト ID を取得する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. 緊急アクセス用アカウントを検索し、ユーザーの名前を選択します。
4. 後で使用できるように、オブジェクト ID 属性をコピーして保存します。
5. 2 番目の緊急アクセス アカウントに対して前の手順を繰り返します。

#### アラート ルールを作成する

1. [Azure portal](https://portal.azure.com) に[監視共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#monitoring-contributor)以上でサインインします。
2. **Monitor** を検索して開きます。
3. 左側のメニューで、[アラート] を選択 **します**。
4. **[+ 作成]**&gt;**[アラート ルール]** を選択します。 **[アラート ルールの作成]** ページが開きます。
5. **スコープ** タブで:

    1. **リソースの選択** ウィンドウで、Log Analytics ワークスペースを見つけて選択します。
    2. サブスクリプションが前提条件で構成したワークスペースと一致することを確認します。
    3. **を選択して**を適用します。
6. [**条件**] タブで:

    1. [ **シグナル名** ] ドロップダウンから、[ **カスタム ログ検索**] を選択します。
    2. **[クエリの種類]** を **[集計ログ]** に設定します。
    3. [ **検索クエリ**] で、次のいずれかのクエリを入力し、2 つの緊急アクセス アカウントのオブジェクト ID を挿入します。

        注

        追加する緊急アクセス アカウントごとに、クエリに別の `or UserId == "ObjectGuid"` を追加します。

        サンプル クエリ:

        ```kusto
        // Search for a single Object ID (UserID)
        SigninLogs
        | where UserId == "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"
        | project TimeGenerated, UserPrincipalName, UserId, IPAddress, ResultType, ResultDescription
        ```

        ```kusto
        // Search for multiple Object IDs (UserIds)
        SigninLogs
        | where UserId == "00aa00aa-bb11-cc22-dd33-44ee44ee44ee" or UserId == "11bb11bb-cc22-dd33-ee44-55ff55ff55ff"
        | project TimeGenerated, UserPrincipalName, UserId, IPAddress, ResultType, ResultDescription
        ```

        ```kusto
        // Search for a single UserPrincipalName
        SigninLogs
        | where UserPrincipalName == "user@yourdomain.onmicrosoft.com"
        | project TimeGenerated, UserPrincipalName, UserId, IPAddress, ResultType, ResultDescription
        ```
    4. [ **測定**] で、クエリの結果を集計する方法を設定します。

        1. **測定**を選択します。
        2. 集計の **種類**を選択します。
        3. **[集計の粒度]** を選択します。
    5. **ディメンション別に分割** で、**リソース ID 列**を選択します。
    6. **[アラート ロジック]** で、以下の手順を実行します:

        1. **しきい値の種類**を **[静的]** に設定します。
        2. **演算子**を **[より大きい]** に設定します。
        3. **しきい値**を **0** に設定します。
        4. **評価の頻度**を、クエリを実行する頻度に設定します。

        [Image: しきい値の種類、演算子、しきい値、評価の頻度の値の例を含むアラート ロジック設定のスクリーンショット。]
    7. **[次へ]** をクリックして続行します。
7. [ **アクション** ] タブで、アラートによって通知されるアクション グループを選択します。 作成する場合は、「アクション グループを作成する」を参照してください。
8. **[詳細]** タブでは:

    1. イベントの **重大度** を選択します。 **0 - Critical** を使用します。
    2. **アラート ルール名**を入力し、オプションの説明を追加します。
    3. **[リージョン]** を選択します。
    4. ログ クエリの実行時に使用する **ID を** 選択します。
    5. [ **詳細オプション**] で、[ **作成時に有効にする**] を選択します。
    6. **[次へ]** をクリックして続行します。
9. [ **タグ** ] タブで、アラート ルールに関連付けるタグを追加します。
10. [ **確認と作成**] を選択し、[ **作成**] を選択します。

#### アクション グループを作成する

1. **[アクション グループの作成]** を選択します。

    [Image: [基本] タブが開いている [アクション グループの作成] 画面のスクリーンショット。]
2. **[基本]** タブで、次の情報を入力します。

    - **サブスクリプション** と **リソース グループ**: アクション グループを格納する場所を選択します。
    - **リージョン**: アクション グループのリージョンを選択します。
    - **アクション グループ名**: わかりやすい名前を入力します。
    - **表示名**: 通知に表示される短い名前 (最大 12 文字) を入力します。
3. **[次へ: 通知]** を選択します。
4. [ **通知の種類**] で、[ **電子メール/SMS メッセージ/プッシュ/音声**] を選択します。
5. [ **全体管理者に通知**] などの通知名を入力します。
6. [ **詳細の編集] を**選択し、通知方法と連絡先情報を構成して、[ **OK]** を選択します。
7. トリガーするその他の通知を追加します。
8. [ **次へ: アクション]** を選択して追加の自動化されたアクションを構成するか、[ **確認と作成** ] を選択して完了します。

#### 各緊急アクセスアカウントの資格情報利用を評価するため、事後評価チームを準備する

アラートがトリガーされた場合は、Microsoft Entra やその他のワークロードのログを保持します。 状況と緊急アクセス アカウントの使用状況の結果のレビューを行います。 このレビューでは、アカウントが使用されたかどうかを判断します。

- 計画されたドリルで適合性を検証する
- 管理者が通常のアカウントを使用できない実際の緊急時に対応する
- アカウントの誤用または無断使用の結果として

次に、ログを調べて、緊急アクセス アカウントを持つ個人が実行したアクションを特定し、それらのアクションがアカウントの承認された使用と一致していることを確認します。

### アカウントを定期的に検証する

緊急アクセスアカウントを使用するようにスタッフメンバーをトレーニングするだけでなく、承認されたスタッフが緊急アクセスアカウントにアクセスできることを検証する継続的なプロセスを用意します。 アカウントの機能を検証し、アカウントが誤用された場合に監視ルールとアラート ルールがトリガーされることを確認するための訓練を定期的に実施します。 少なくとも、一定の間隔で次の手順を実行します。

- アカウント チェック アクティビティが進行中であることをセキュリティ監視スタッフが認識していることを確認します。
- 緊急アクセスアカウントの資格情報を使用する権限を持つ個人の一覧を確認して更新します。
- これらのアカウントを使用する緊急時対処プロセスが文書化され、最新のものになっていることを確認します。
- 緊急時にこれらの手順を行う必要がある可能性のある管理者およびセキュリティ担当者が、プロセスについてトレーニングされていることを確認します。
- 緊急アクセスアカウントがサインインして管理タスクを実行できることを検証します。
- ユーザーが、個々のユーザーのデバイスまたは個人の詳細に多要素認証またはセルフサービス パスワード リセット (SSPR) を登録していないことを確認します。
- アカウントが、サインインまたはロールのアクティブ化の際に使用するために、デバイスに対する多要素認証に登録されている場合は、緊急時に使用することが必要になる可能性のあるすべての管理者がデバイスにアクセスできることを確認します。 また、デバイスが、一般的な障害モードを共有していない 2 つ以上のネットワーク パスを介して通信できることを確認します。 たとえば、デバイスが施設のワイヤレス ネットワークと携帯電話会社ネットワークの両方を介してインターネットに通信できるようにします。
- すべての金庫の組み合わせを定期的に変更し、アクセス権を持つユーザーが組織を離れた後に行います。

次の手順を定期的に実行し、主な変更を行います。

- 少なくとも 90 日ごと
- 退職後や役職変更など、IT スタッフの最近の変更がある場合
- 組織内のMicrosoft Entra サブスクリプションが変更されたとき
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/security-planning"} -->
## Microsoft Entra ID の管理者向けのセキュリティで保護されたアクセス プラクティス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-planning
- Service: entra-id / role-based-access-control
- Article date: 2024-11-21
- Summary: 組織の管理アクセスと管理者アカウントがセキュリティで保護されるようにします。 Microsoft Entra ID、Azure、Microsoft Online Services を構成するシステム設計者および IT 担当者向けです。

ビジネス資産のセキュリティは、IT システムを管理する特権アカウントの信頼性に依存します。 サイバー攻撃者は、資格情報の盗難攻撃を使用して、管理者アカウントやその他の特権アクセスをターゲットにし、機密データへのアクセスを試みます。

クラウド サービスの場合、防止と対応は、クラウド サービス プロバイダーと顧客の共同責任です。 エンドポイントとクラウドに対する最新の脅威の詳細については、 [Microsoft セキュリティ インテリジェンス レポート](https://www.microsoft.com/security/operations/security-intelligence-report)を参照してください。 この記事は、現在のプランとここで説明しているガイダンス間のギャップを埋めるためのロードマップの作成に役立ちます。

Note

Microsoft は、最高レベルの信頼、透過性、標準への準拠、規制コンプライアンスに努めています。 Microsoft グローバル インシデント対応チームがクラウド サービスに対する攻撃の影響を軽減する方法、および Microsoft [セキュリティ センター - Microsoft セキュリティ センター -](https://www.microsoft.com/trustcenter/security) セキュリティと Microsoft コンプライアンスターゲットの Microsoft ビジネス製品とクラウド サービスにセキュリティを組み込む方法について説明[します。](https://www.microsoft.com/trust-center/compliance/compliance-overview)

従来、組織のセキュリティは、セキュリティ境界としてネットワークの入口と出口のポイントに重点を置いていました。 しかし、インターネット上の SaaS アプリと個人用デバイスでは、この方法があまり効果的ではありません。

Microsoft Entra ID では、ネットワーク セキュリティ境界を組織の ID 層の認証に置き換え、管理下にある特権管理者ロールにユーザーを割り当てます。 環境がオンプレミス、クラウド、ハイブリッドのいずれであるかにかかわらず、ユーザーのアクセスを保護する必要があります。

特権アクセスをセキュリティで保護するには、以下に対する変更が必要です。

- プロセス、管理作業、ナレッジ管理
- ホスト防御、アカウントの保護、ID 管理などの技術的なコンポーネント

重要な Microsoft サービスで管理および報告されている方法で、特権アクセスを保護します。 オンプレミスの管理者アカウントがある場合は、「特権 [アクセスのセキュリティ](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/overview)保護」の Active Directory でのオンプレミスおよびハイブリッド特権アクセスのガイダンスを参照してください。

Note

この記事のガイダンスでは、主に、Microsoft Entra ID P1 と P2 に含まれている Microsoft Entra ID の機能について説明します。 Microsoft Entra ID P2 は、EMS E5 スイートおよび Microsoft 365 E5 スイートに含まれています。 このガイダンスでは、組織がユーザー用に Microsoft Entra ID P2 ライセンスを既に購入していることを想定しています。 これらのライセンスがない場合、ガイダンスの一部は組織に適用されないことがあります。

### ロードマップの作成

マイクロソフトでは、サイバー攻撃者から特権アクセスを保護するためのロードマップを作成して従うことをお勧めします。 既存の機能と組織内の特定の要件に合わせてロードマップをいつでも調整できます。

ロードマップの各ステージで、敵対者がオンプレミス、クラウド、ハイブリッドの資産の特権アクセスを攻撃するコストと難易度を上げます。 Microsoft では、次の 4 つのロードマップ ステージをお勧めします。 最初に、最も効果的で、最も迅速な実装をスケジュールします。

この記事は、サイバー攻撃のインシデントと応答の実装に関する Microsoft の経験に基づくガイドとすることができます。 このロードマップのタイムラインは、おおよそのものです。

[Image: タイム ラインを含むロードマップのステージ]

- ステージ 1 (24-48 時間): すぐに実施することをお勧めする重要な項目
- ステージ 2 (2-4 週間): 最もよく使用される攻撃手法の緩和
- ステージ 3 (1 - 3 か月): 可視性の構築と管理者アクティビティのフル コントロールの構築
- ステージ 4 (6 か月以降): セキュリティ プラットフォームをさらに強化するための防御の継続的な構築

このロードマップ フレームワークは、既にデプロイしている Microsoft テクノロジの使用を最大化するように設計されています。 既に展開されているか、展開を検討している他のベンダーのセキュリティ ツールを組み込むことも検討してください。

### ステージ 1:すぐに実施する重要な項目

[Image: ステージ 1 最初に実行する重要な項目]

ロードマップのステージ 1 では、迅速かつ簡単に実装できる重要なタスクに重点を置きます。 最初の 24 ～ 48 時間以内にこれらの少数の項目をすぐに実施して、セキュリティで保護された特権アクセスの基本的なレベルを確保することをお勧めします。 セキュリティで保護された特権アクセスのロードマップのこのステージには、次のアクションが含まれます。

#### 一般的な準備

##### Microsoft Entra Privileged Identity Management を使用する

Microsoft Entra Privileged Identity Management (PIM) の使用は、Microsoft Entra 運用環境で開始することをお勧めします。 PIM の使用を開始すると、特権アクセス ロールの変更についての電子メール通知を受け取るようになります。 通知により、高度な特権ロールに他のユーザーが追加された場合に早期警告を受け取れます。

Microsoft Entra Privileged Identity Management は、Microsoft Entra ID P2 または EMS E5 に含まれています。 オンプレミスおよびクラウド内のアプリケーションとリソースへのアクセスを保護するために、 [Enterprise Mobility + Security 無料の 90 日間試用版](https://www.microsoft.com/cloud-platform/enterprise-mobility-security-trial)にサインアップします。 Microsoft Entra Privileged Identity Management と Microsoft Entra ID 保護は、Microsoft Entra ID のレポート、監査、およびアラートを使用して、セキュリティ アクティビティを監視します。

Microsoft Entra Privileged Identity Management の使用開始後:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. Privileged Identity Management を使用するディレクトリを切り替えるには、Microsoft Entra 管理センターの右上隅にあるユーザー名を選択します。
3. **ID ガバナンス**&gt;**特権 ID 管理**を参照します。

組織内で PIM を使用する最初のユーザーが [、セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロールと [特権ロール管理者ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) に割り当てられていることを確認します。 ユーザーの Microsoft Entra ディレクトリ ロールの割り当てを管理できるのは、特権ロール管理者だけです。 PIM セキュリティ ウィザードの指示に従って初回の検出と割り当てを実行できます。 この時点でさらに変更を加えずに、ウィザードを終了することができます。

##### 高度な特権ロールに属するアカウントを識別および分類する

Microsoft Entra Privileged Identity Management の使用を開始した後、次の Microsoft Entra のロールに属しているユーザーを表示します。

- グローバル管理者
- 特権ロール管理者
- Exchange 管理者
- SharePoint 管理者

組織内に Microsoft Entra Privileged Identity Management がない場合は、 [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdirectoryrolemember) を使用できます。 全体管理者は、組織がサブスクライブしているすべてのクラウド サービスで同じアクセス許可を持つため、全体管理者ロールから始めます。 これらのアクセス許可は、Microsoft 365 管理センターや Microsoft Entra 管理センターに割り当てられていても、または Microsoft Graph PowerShell を使用して割り当てられていても、付与されます。

このようなロールの不要になったアカウントがあれば削除します。 次に、管理者ロールに割り当てられている残りのアカウントを分類します。

- 管理ユーザーに割り当てられているが、管理以外の目的 (たとえば、個人用電子メール) にも使用されている
- 管理ユーザーに割り当てられ、管理目的にのみ使用されている
- 複数のユーザー間で共有される
- 非常時の緊急アクセス シナリオ用
- 自動スクリプト用
- 外部ユーザー用

##### 少なくとも 2 つの緊急アクセス用アカウントを定義する

Microsoft では、グローバル [管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールが永続的に割り当てられている 2 つのクラウド専用緊急アクセス アカウントを組織に付与することをお勧めします。 このようなアカウントは高い特権を持っており、特定のユーザーには割り当てられません。 アカウントは、通常のアカウントを使用できない、または他のすべての管理者が誤ってロックアウトされる、緊急または「非常時」のシナリオに限定されます。そのようなアカウントは、[緊急アクセスアカウントの推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)に従って作成する必要があります。

##### 多要素認証を有効にし、その他のすべての高度な特権を持つシングル ユーザー非フェデレーション管理者アカウントを登録する

Microsoft Entra 管理者のロール (全体管理者、特権ロール管理者、Exchange 管理者、SharePoint 管理者) を 1 つ以上永続的に割り当てられているすべての個人ユーザーには、サインイン時に Microsoft Entra 多要素認証を要求します。 [「管理者に多要素認証を適用する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-find-coverage-gaps#enforce-multifactor-authentication-on-your-administrators)」のガイダンスを使用し、それらのすべてのユーザーがhttps://aka.ms/mfasetupに登録されていることを確認します。 詳細については、「 [Microsoft 365 でユーザーとデバイスのアクセスを保護](https://learn.microsoft.com/ja-jp/purview/protect-access-to-data-and-services)する」ガイドの手順 2 と手順 3 を参照してください。

### ステージ 2:よく使用される攻撃の緩和

[Image: ステージ 2 頻繁に使用される攻撃を軽減する]

ロードマップのステージ 2 は、資格情報の盗用や誤用に最もよく使われる攻撃手法の緩和に重点を置き、約 2 ～ 4 週間で実装することができます。 セキュリティで保護された特権アクセスのロードマップのこのステージには、次のアクションが含まれます。

#### 一般的な準備

##### サービス、所有者、管理者のインベントリを実施する

"個人所有機器の持ち込み" と自宅からの作業のポリシーの増加や、ワイヤレス接続の拡大に伴い、ネットワークに接続するユーザーを監視することが非常に重要です。 セキュリティ監査によって、組織がサポートしていない、高いリスクを表すネットワーク上のデバイス、アプリケーション、およびプログラムを明らかにすることができます。 詳細については、 [Azure のセキュリティ管理と監視の概要に関するページを](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/management-monitoring-overview)参照してください。 インベントリ プロセスには、次のすべてのタスクを含めてください。

- 管理者ロールを持つユーザーと、それらのユーザーが管理できるサービスを識別します。
- Microsoft Entra PIM を使用して、Microsoft Entra ID への管理者アクセス権を持つ組織内のユーザーを特定します。
- Microsoft Entra ID で定義されているロール以外に、Microsoft 365 には、組織内のユーザーに割り当てることができる管理者ロールのセットが用意されています。 各管理者ロールは、一般的なビジネス機能にマップされ、組織内のユーザーに [Microsoft 365 管理センター](https://admin.microsoft.com)で特定のタスクを実行するアクセス許可を付与します。 Microsoft 365 管理センターを使用して、Microsoft Entra ID で管理されていないロール経由を含め、組織内で Microsoft 365 への管理者アクセス権を持つユーザーを特定します。 詳細については、「 [Office 365 の Microsoft 365 管理者ロール](https://support.office.com/article/About-Office-365-admin-roles-da585eea-f576-4f55-a1e0-87090b6aaa9d) と [セキュリティプラクティス](https://learn.microsoft.com/ja-jp/office365/servicedescriptions/microsoft-365-service-descriptions/microsoft-365-tenantlevel-services-licensing-guidance/microsoft-365-security-compliance-licensing-guidance)について」を参照してください。
- Azure、Intune、Dynamics 365 など、組織が利用しているサービスでインベントリを実行します。
- 管理目的で使用されているアカウントが次のようになっていることを確認します。

    - 使用可能な電子メール アドレスが設定されている
    - Microsoft Entra 多要素認証に登録しているか、オンプレミスで MFA を使用している
- ユーザーに管理アクセス権を使用するビジネス上の正当な理由を尋ねます。
- 管理者アクセス権を必要としない個人ユーザーとサービスからそれを取り消します。

##### 職場または学校アカウントに切り替える必要がある管理者ロールの Microsoft アカウントを特定する

最初のグローバル管理者が Microsoft Entra ID の使用を開始したときに既存の Microsoft アカウント資格情報を再利用する場合は、Microsoft アカウントを個々のクラウドベースのアカウントに置き換えます。

##### 全体管理者アカウントのユーザー アカウントとメール転送を分離する

個人用電子メール アカウントはサイバー攻撃者によって定常的にフィッシングされるリスクがあるため、全体管理者アカウントで個人用電子メール アドレスを使用することは許容されません。 インターネット上のリスクを管理者特権から分離するために、管理者特権を持つユーザーごとに専用のアカウントを作成します。

- 全体管理者タスクを実行するユーザーには、必ず別のアカウントを作成します。
- 全体管理者が誤って電子メールを開いたり、管理者アカウントでプログラムを実行したりしないようにします。
- これらのアカウントが電子メールを作業用のメールボックスに転送することを確認します。
- 全体管理者 (およびその他の特権グループ) アカウントは、オンプレミスの Active Directory に結び付けられていないクラウド専用アカウントである必要があります。

##### 管理者アカウントのパスワードが最近変更されたことを確認する

すべてのユーザーが過去 90 日間に少なくとも 1 回管理者アカウントにサインインし、パスワードを変更したことを確認します。 また、共有アカウントのパスワードが最近変更されたことを確認します。

##### パスワード ハッシュ同期をオンにする

Microsoft Entra Connect では、オンプレミスの Active Directory からクラウドベースの Microsoft Entra 組織に、ユーザーのパスワードのハッシュを同期します。 Active Directory フェデレーション サービス (AD FS) とのフェデレーションを使用する場合、パスワード ハッシュ同期をバックアップとして使用することができます。 このバックアップは、オンプレミスの Active Directory または AD FS サーバーが一時的に使用できなくなった場合に役立ちます。

パスワード ハッシュの同期により、ユーザーは、オンプレミスの Active Directory インスタンスにサインインするときに使うものと同じパスワードを使用してサービスにサインインできます。 パスワード ハッシュの同期を使用すると、改ざんされていることが判明しているパスワードとパスワード ハッシュを比較することで、Microsoft Entra ID Protection は侵害された資格情報を検出することができます。 詳細については、「 [Microsoft Entra Connect Sync を使用してパスワード ハッシュ同期を実装](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization)する」を参照してください。

##### 特権ロールに属するユーザーおよび露出しているユーザーに多要素認証を要求する

Microsoft Entra ID では、すべてのユーザーに多要素認証を要求することをお勧めします。 アカウントが侵害された場合に大きな影響を与えるユーザー (財務責任者など) を考慮してください。 MFA により、パスワードの漏洩による攻撃のリスクが軽減されます。

以下を有効にします。

- 組織内のすべてのユーザー[に対して条件付きアクセス ポリシーを使用する MFA](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted)。

Windows Hello for Business を使用する場合は、Windows Hello サインイン エクスペリエンスを使用して MFA 要件を満たすことができます。 詳細については、「 [Windows Hello](https://learn.microsoft.com/ja-jp/windows/uwp/security/microsoft-passport)」を参照してください。

##### Microsoft Entra ID Protection（マイクロソフト エントラ ID 保護）

Microsoft Entra ID Protection は、アルゴリズムベースの監視およびレポート ツールで、組織の ID に影響する潜在的な脆弱性を検出します。 検出された不審なアクティビティへの自動対応を構成し、それらを解決するのに適切なアクションを実行できます。 詳細については、「 [Microsoft Entra ID Protection」を](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)参照してください。

##### Microsoft 365 のセキュリティ スコアを取得する (Microsoft 365 を使用している場合)

セキュリティ スコアは、使用している Microsoft 365 サービスの設定とアクティビティを参照して、Microsoft によって確立されたベースラインと比較します。 セキュリティ プラクティスにどの程度従っているかに基づいてスコアが算出されます。 Microsoft 365 Business Standard または Enterprise サブスクリプションの管理者アクセス許可を持つユーザーは、`https://security.microsoft.com/securescore` でセキュリティ スコアにアクセスできます。

##### Microsoft 365 のセキュリティおよびコンプライアンス ガイダンスを確認する (Microsoft 365 を使用している場合)

[セキュリティとコンプライアンスの計画では、](https://support.office.com/article/Plan-for-security-and-compliance-in-Office-365-dc4f704c-6fcc-4cab-9a02-95a824e4fb57)Office 365 を構成し、他の EMS 機能を有効にする Office 365 のお客様のアプローチの概要を説明します。 次に、 [Microsoft 365 のデータとサービスへのアクセスを保護する](https://support.office.com/article/Protect-access-to-data-and-services-in-Office-365-a6ef28a4-2447-4b43-aae2-f5af6d53c68e) 方法の手順 3 から 6、および [Microsoft 365 のセキュリティとコンプライアンスを監視](https://support.office.com/article/Monitor-security-and-compliance-in-Office-365-b62f1722-fd39-44eb-8361-da61d21509b6)する方法のガイドを確認します。

##### Microsoft 365 のアクティビティ監視を構成する (Microsoft 365 を使用している場合)

Microsoft 365 を使用しているユーザーについて組織を監視し、管理者アカウントを持っているが、これらのポータルにサインインしないために Microsoft 365 へのアクセスを必要としない可能性があるユーザーを識別します。 詳細については、 [Microsoft 365 管理センターのアクティビティ レポートを](https://support.office.com/article/Activity-Reports-in-the-Office-365-admin-center-0d6dfb17-8582-4172-a9a9-aed798150263)参照してください。

##### インシデント/緊急時対応計画の所有者を設定する

適切なインシデント対応機能を確立するには、多大な計画とリソースが必要です。 サイバー攻撃を継続的に監視し、インシデント処理の優先順位を設定する必要があります。 インシデント データを収集、分析、およびレポートしてリレーションシップを構築し、他の内部グループおよび計画責任者とのコミュニケーションを確立します。 詳細については、「 [Microsoft Security Response Center](https://technet.microsoft.com/security/dn440717)」を参照してください。

##### まだ行っていない場合は、オンプレミスの特権管理者アカウントをセキュリティで保護する

Microsoft Entra 組織がオンプレミスの Active Directory と同期されている場合は、「セキュリティ特権アクセス ロードマップ」に従ってください。このステージには次のものが含まれます。

- オンプレミスの管理タスクを実行する必要があるユーザー用に個別の管理者アカウントを作成する
- Active Directory 管理者向けの特権アクセス ワークステーションを配置する
- ワークステーションとサーバーに対して一意のローカル管理者パスワードを作成する

#### Azure へのアクセスを管理する組織における追加の手順

##### サブスクリプションのインベントリを完了する

エンタープライズ ポータルと Azure Portal を使用して、運用アプリケーションをホストする組織内のサブスクリプションを識別します。

##### Microsoft アカウントを管理者ロールから削除する

Xbox、Live、Outlook などの他のプログラムの Microsoft アカウントは、組織のサブスクリプションの管理者アカウントとして使用しないでください。 すべての Microsoft アカウントから管理者状態を除去し、Microsoft Entra ID (たとえば、chris@contoso.com) の職場または学校アカウントで置き換えます。 管理者目的の場合では、他のサービスではなく Microsoft Entra ID で認証されるアカウントを利用します。

##### Azure のアクティビティを監視する

Azure アクティビティ ログは、Azure でのサブスクリプション レベルのイベント履歴を提供します。 このログは、だれがどのリソースをいつ作成、更新、または削除したかについての情報を提供します。 詳細については、「 [Azure サブスクリプションの重要なアクションに関する通知の監査と受信」を参照してください](https://learn.microsoft.com/ja-jp/azure/azure-monitor/alerts/alerts-create-new-alert-rule)。

#### Microsoft Entra ID を介して他のクラウド アプリへのアクセスを管理する組織における追加の手順

##### 条件付きアクセス ポリシーを構成する

オンプレミスのアプリケーションとクラウドでホストされるアプリケーションの条件付きアクセス ポリシーを準備します。 ユーザーが職場に参加しているデバイスがある場合は、 [Microsoft Entra デバイス登録を使用してオンプレミスの条件付きアクセスを設定する](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/overview)方法の詳細を確認してください。

### ステージ 3: 管理者アクティビティの制御

[Image: ステージ 3: 管理者アクティビティを制御する]

ステージ 3 は、ステージ 2 のリスク軽減の上に構築され、約 1 ～ 3 か月以内に実装する必要があります。 セキュリティで保護された特権アクセスのロードマップのこのステージには、次のコンポーネントが含まれます。

#### 一般的な準備

##### 管理者ロールに属するユーザーのアクセス レビューを実行する

クラウド サービス経由で特権アクセス権を得る企業ユーザーの増加は、管理されないアクセスにつながる可能性があります。 今日のユーザーは、Microsoft 365 の全体管理者や Azure サブスクリプション管理者になったり、VM や SaaS アプリの管理者アクセス権を持ったりすることができます。

組織では、すべての従業員が通常のビジネス トランザクションを特権のないユーザーとして処理し、必要な場合にのみ管理者権限を付与する必要があります。 アクセス レビューを完了して、管理者特権をアクティブ化する資格のあるユーザーを特定し、確認します。

推奨事項は次のとおりです。

1. Microsoft Entra 管理者であるユーザーを特定し、オンデマンドのジャスト イン タイム管理者アクセス権とロール ベースのセキュリティ制御を有効にします。
2. 管理者特権でアクセスする明確な正当性を持たないユーザーを別のロールに変換します (資格のあるロールがない場合は、削除します)。

##### すべてのユーザーについて、より強力な認証のロールアウトを継続する

高度に露出されたユーザーには、Microsoft Entra 多要素認証や Windows Hello などの最新の強力な認証を使用するように要求します。 高度に露出されたユーザーの例を次に示します。

- 経営幹部レベルの役員
- 高レベルのマネージャー
- IT およびセキュリティの重要な担当者

##### Microsoft Entra ID の管理に専用のワークステーションを使用する

攻撃者は、データの整合性と信頼性を低下させることができるように、特権アカウントをターゲットにしようとすることがあります。 多くの場合、プログラム ロジックを変更するか、または管理者が資格情報を入力するのを盗み取る悪意のあるコードが使用されます。 Privileged Access Workstation (PAW) には、機密性の高いタスクに専用のオペレーティング システムが用意されており、インターネット上の攻撃や脅威ベクトルから保護されます。 日常的に使用するワークステーションとデバイスからこのような機密性の高いタスクとアカウントを分離することで、以下に対する保護が強化されます。

- フィッシング攻撃
- アプリケーションとオペレーティング システムの脆弱性
- 偽装攻撃
- キーボード操作のログ記録、Pass-the-Hash、Pass-The-Ticket などの資格情報の盗用攻撃

特権アクセス ワークステーションを配置することで、強化されていないデスクトップ環境で管理者が資格情報を入力するリスクを軽減できます。 詳細については、「 [特権アクセス ワークステーション](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/overview)」を参照してください。

##### インシデントの処理に関する米国国立標準技術研究所の推奨事項を確認する

米国国立標準技術研究所 (NIST) は、特にインシデント関連のデータの分析と各インシデントへの適切な対応の決定について、インシデント処理のガイドラインを提供しています。 詳細については、「 [(NIST) コンピューター セキュリティ インシデント処理ガイド (SP 800-61、リビジョン 2)](https://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-61r2.pdf)」を参照してください。

##### JIT に Privileged Identity Management (PIM) を実装して管理者ロールを追加する

Microsoft Entra ID には、 [Microsoft Entra Privileged Identity Management 機能を](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) 使用します。 特権ロールの期間限定アクティブ化は以下を有効にすることで動作します。

- 特定のタスクを実行する管理者特権をアクティブにする
- アクティブ化プロセス中に MFA を適用する
- アラートを使用して帯域外の変更について管理者に通知する
- ユーザーが自分の特権アクセスをあらかじめ構成された時間保持できるようにする
- セキュリティ管理者に次のことを許可します。

    - すべての特権 ID を検出する
    - 監査レポートを表示する
    - 管理者特権をアクティブ化する資格があるすべてのユーザーを識別するアクセス レビューを作成する

既に Microsoft Entra Privileged Identity Management を使用している場合は、必要に応じて期限付きの特権の期間 (たとえば、メンテナンス期間) を調整します。

##### パスワードベースのサインイン プロトコルへの露出を確認する (Exchange Online を使用している場合)

資格情報が侵害された場合に、組織にとって致命的になる可能性のあるすべてのユーザーを識別することをお勧めします。 これらのユーザーには、強力な認証要件を設定し、Microsoft Entra 条件付きアクセスを使用して、ユーザー名とパスワードを使用して電子メールにサインインしないようにします。 [条件付きアクセスを使用してレガシ認証を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-legacy-authentication)ブロックしたり、Exchange Online を介して[基本認証をブロック](https://learn.microsoft.com/ja-jp/exchange/clients-and-mobile-in-exchange-online/disable-basic-authentication-in-exchange-online)したりできます。

##### Microsoft 365 ロールのロール レビュー アセスメントを実行する (Microsoft 365 を使用している場合)

すべての管理者ユーザーが適切なロールに属しているどうかを評価します (このアセスメントに基づいて削除または再割り当てします)。

##### Microsoft 365 で使用されているセキュリティ インシデント管理アプローチを確認し、自分の組織と比較する

このレポートは、 [Microsoft 365 のセキュリティ インシデント管理](https://www.microsoft.com/download/details.aspx?id=54302)からダウンロードできます。

##### オンプレミスの特権管理者アカウントのセキュリティ保護に進む

Microsoft Entra ID がオンプレミスの Active Directory に接続されている場合は、「 [セキュリティ特権アクセス ロードマップ](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/overview): ステージ 2」のガイダンスに従ってください。 このステージでは、次のことを行います。

- すべての管理者向けに特権アクセス ワークステーションを配置する
- Require MFA (MFA が必須)
- ドメイン コントローラーのメンテナンスに十分な管理者だけを使用して、ドメインの攻撃対象領域を減らす
- 攻撃検出のための [Advanced Threat Analytics](https://learn.microsoft.com/ja-jp/advanced-threat-analytics/) のデプロイ

#### Azure へのアクセスを管理する組織における追加の手順

##### 統合型の監視を確立する

[Microsoft Defender for Cloud](https://learn.microsoft.com/ja-jp/azure/defender-for-cloud/defender-for-cloud-introduction):

- Azure サブスクリプション全体のセキュリティ監視とポリシー管理を統合します
- 他の方法では見過ごされてしまう可能性のある脅威を検出します
- 幅広いセキュリティ ソリューションと連携します

##### ホストされる仮想マシン内で、特権アカウントのインベントリを行う

通常は、すべての Azure サブスクリプションまたはリソースへの無制限のアクセス許可をユーザーに付与する必要はありません。 Microsoft Entra 管理者ロールを使用して、ユーザーが自分のジョブを実行するために必要なアクセス許可のみを付与します。 Microsoft Entra 管理者ロールを使用して、ある管理者がサブスクリプションで VM のみを管理できるようにし、別の管理者が同じサブスクリプション内で SQL データベースを管理できるようにすることができます。 詳細については、「 [Azure ロールベースのアクセス制御とは」](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/overview)を参照してください。

##### Microsoft Entra 管理者ロールに PIM を実装する

Microsoft Entra 管理者ロールと共に Privileged Identity Management を使用して、Azure リソースへのアクセスを管理、制御、監視します。 PIM を使用した保護では、特権の露出時間を短縮し、レポートとアラートを通じて使用状況の可視性を高めます。 詳細については、「 [Microsoft Entra Privileged Identity Management とは」を](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)参照してください。

##### Azure ログ統合を使用して、関連する Azure ログを SIEM システムに送信する

Azure ログ統合を使用すると、未加工のログを、Azure リソースから組織の既存のセキュリティ情報イベント管理 (SIEM) システムに統合できます。 [Azure ログ統合](https://learn.microsoft.com/ja-jp/previous-versions/azure/security/fundamentals/azure-log-integration-overview) では、Windows イベント ビューアー ログから Windows イベントが収集され、Azure リソースは次の場所から収集されます。

- Azure アクティビティ ログ
- Microsoft Defender for Cloud アラート
- Azure リソース ログ

#### Microsoft Entra ID を介して他のクラウド アプリへのアクセスを管理する組織における追加の手順

##### 接続されているアプリのユーザー プロビジョニングを実装する

Microsoft Entra ID を使用すると、Dropbox、Salesforce、ServiceNow などのクラウド アプリでのユーザー ID の作成と管理を自動化できます。 詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

##### Information Protection を統合する

Microsoft Defender for Cloud Apps を使用すると、ファイルを調査し、Azure Information Protection 分類ラベルに基づいてポリシーを設定して、ご自分のクラウド データの可視性と制御を向上させることができます。 クラウド内のファイルをスキャンして分類し、Azure Information Protection ラベルを適用します。 詳細については、 [Azure Information Protection の統合に関するページを](https://learn.microsoft.com/ja-jp/defender-cloud-apps/azip-integration)参照してください。

##### 条件付きアクセスを構成する

[SaaS アプリ](https://azure.microsoft.com/overview/what-is-saas/)と Microsoft Entra 接続アプリのグループ、場所、アプリケーションの秘密度に基づいて条件付きアクセスを構成します。

##### 接続されているクラウド アプリのアクティビティを監視する

[Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/what-is-defender-for-cloud-apps) を使用して、接続されているアプリケーションでもユーザー アクセスが確実に保護されるようにすることをお勧めします。 この機能により、クラウド アプリへのエンタープライズ アクセスと管理者アカウントが保護され、以下が可能になります。

- 可視性と制御をクラウド アプリに拡張する
- アクセス、アクティビティ、データ共有のポリシーを作成する
- 危険なアクティビティ、異常な動作、脅威を自動的に識別する
- データの漏えいを防ぐ
- リスクを最小限に抑え、脅威の防止とポリシーの適用を自動化する

Defender for Cloud Apps SIEM エージェントは、Defender for Cloud Apps を SIEM サーバーと統合して、Microsoft 365 のアラートとアクティビティの一元的な監視を可能にします。 お使いのサーバー上で稼働し、Defender for Cloud App からのアラートとアクティビティをプルして、SIEM サーバーにストリーム送信します。 詳細については、 [SIEM 統合](https://learn.microsoft.com/ja-jp/defender-cloud-apps/siem)を参照してください。

### ステージ 4: 防御の構築を継続する

[Image: ステージ 4: アクティブなセキュリティ体制を採用する]

ロードマップのステージ 4 は、6 か月以上で実装する必要があります。 ロードマップを完成させて、現在知られている潜在的な攻撃からの特権アクセスの保護を強化します。 将来のセキュリティ上の脅威については、セキュリティを継続的なプロセスとしてとらえ、お使いの環境をターゲットとする敵対者のコストを上げ、成功率を下げるようにすることをお勧めします。

ビジネス資産のセキュリティ保証を確立するには、特権アクセスを保護することが重要です。 ただし、これは継続的なセキュリティ保証を提供する完全なセキュリティ プログラムの一部である必要があります。 このプログラムには、次のような要素が含まれている必要があります。

- Policy
- 操作
- 情報セキュリティ
- サーバー
- アプリケーション
- コンピューター
- デバイス
- クラウド ファブリック

特権アクセス アカウントを管理する際に、次の方法をお勧めします。

- 管理者が日常業務を特権のないユーザーとして行っていることを確認します
- 必要な場合にのみ特権アクセスを付与し、その後削除します (Just-In-Time)
- 特権アカウントに関連する監査アクティビティ ログを保持します

完全なセキュリティ ロードマップの構築の詳細については、 [Microsoft クラウド IT アーキテクチャ リソース](https://almbok.com/office365/microsoft_cloud_it_architecture_resources)に関するページを参照してください。 ロードマップの一部を実装するのに役立つ Microsoft サービスと連携するには、Microsoft の担当者に問い合わせるか、 [企業を保護するための重要なサイバー防御の構築に関するページを参照してください](https://www.microsoft.com/en-us/microsoftservices/campaigns/cybersecurity-protection.aspx)。

セキュリティで保護された特権アクセスのロードマップのこの最終的な継続ステージには、次のコンポーネントが含まれます。

#### 一般的な準備

##### Microsoft Entra ID の管理者ロールを確認する

現在の組み込み Microsoft Entra 管理者ロールが最新の状態であるかどうかを判断し、ユーザーが必要なロールにのみ属していることを確認します。 Microsoft Entra ID では、各種役割ごとに別々の管理者を割り当てることができます。 詳細については、「 [Microsoft Entra の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)」を参照してください。

##### Microsoft Entra 参加済みデバイスの管理権限を持つユーザーを確認する

詳細については、「 [Microsoft Entra ハイブリッド参加済みデバイスを構成する方法](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)」を参照してください。

##### [組み込みの Microsoft 365 管理者ロールの](https://support.office.com/article/About-Office-365-admin-roles-da585eea-f576-4f55-a1e0-87090b6aaa9d)メンバーを確認する

Microsoft 365 を使用していない場合は、この手順をスキップします。

##### インシデント対応計画を検証する

計画を強化するために、計画が想定どおりに動作していることを定期的に検証することをお勧めします。

- 既存のロードマップを実行して何が欠落していたかを確認する
- 事後の分析に基づいて、既存のプラクティスを改訂するか新しいプラクティスを定義する
- 更新したインシデント対応計画とプラクティスが、組織全体に配布されていることを確認する

#### Azure へのアクセスを管理する組織における追加の手順

[Azure サブスクリプションの所有権を別のアカウントに譲渡する](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/billing-subscription-transfer)必要があるかどうかを判断します。

### "非常時": 緊急時の対処方法

[Image: 緊急時対処アクセス用のアカウント]

1. 主要な管理者とセキュリティ責任者に、インシデントに関する情報を通知します。
2. 攻撃プレイブックをレビューします。
3. "非常時" アカウントのユーザー名とパスワードの組み合わせにアクセスして Microsoft Entra ID にサインインします。
4. [Azure サポート リクエストを開](https://learn.microsoft.com/ja-jp/azure/azure-portal/supportability/how-to-create-azure-support-request)いて、Microsoft からサポートを受けます。
5. [Microsoft Entra サインイン レポートを確認します](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health)。 イベントの発生からそのイベントがレポートに含まれるまでに時間がかかる場合があります。
6. ハイブリッド環境で、オンプレミスのインフラストラクチャがフェデレーションされており、AD FS サーバーを利用できない場合、フェデレーション認証からパスワード ハッシュ同期の使用に一時的に切り替えることができます。この切り替えにより、AD FS サーバーが使用可能になるまで、ドメイン フェデレーションは管理された認証に戻ります。
7. 特権アカウントの電子メールを監視します。
8. 潜在的なフォレンジック調査と法的調査のために関連ログのバックアップを保存してください。

Microsoft Office 365 によるセキュリティ インシデントの処理方法の詳細については、「 [Microsoft Office 365 のセキュリティ インシデント管理](https://learn.microsoft.com/ja-jp/compliance/assurance/assurance-security-incident-management)」を参照してください。

### よくあるご質問: 特権アクセスのセキュリティ保護に関する回答

**Q:** セキュリティで保護されたアクセス コンポーネントをまだ実装していない場合はどうすればよいですか?

**回答:** 少なくとも 2 つの非常用アカウントを定義し、特権管理者アカウントに MFA を割り当て、ユーザー アカウントをローバル管理者アカウントから分離します。

**Q:** 侵害後、最初に対処する必要がある最も大きな問題は何ですか?

**答える：** 露出度の高い個人に対して、最も強力な認証が必要であることを確認してください。

**Q:** 特権管理者が非アクティブ化された場合はどうなりますか?

**答える：** 常に最新の状態に保たれるグローバル管理者アカウントを作成します。

**Q:** グローバル管理者が 1 人しか残っていなくても、アクセスできない場合はどうなりますか?

**答える：** すぐに特権アクセスを得るために、お使いのブレイクグラス アカウントのいずれかを使用します。

**Q:** 組織内の管理者を保護するにはどうすればよいですか?

**答える：** 管理者は常に、標準の "特権のない" ユーザーとして日常業務を行います。

**Q:** Microsoft Entra ID 内に管理者アカウントを作成するためのベスト プラクティスは何ですか?

**答える：** 特定の管理者タスクの特権アクセスを予約します。

**Q:** 永続的な管理者アクセスを減らすためのツールは何ですか?

**回答:** Privileged Identity Management (PIM) と Microsoft Entra 管理者ロールです。

**Q:** 管理者アカウントと Microsoft Entra ID の同期に関する Microsoft の立場は何ですか?

**答える：** 階層 0 の管理者アカウントは、オンプレミスの AD アカウントにのみ使用されます。 通常、このようなアカウントは、クラウドの Microsoft Entra ID と同期されません。 階層 0 の管理者アカウントには、オンプレミスの Active Directory フォレスト、ドメイン、ドメイン コントローラー、および資産を直接的または間接的に管理するアカウント、グループ、その他の資産が含まれます。

**Q:** 管理者がポータルでランダムな管理者アクセスを割り当てないようにするにはどうすればよいですか?

**答える：** すべてのユーザーとほとんどの管理者に対して特権のないアカウントを使用します。 まず、組織のフットプリントを作成して、どの少数の管理者に特権を与えるかを決定します。 そして、新しく作成した管理ユーザーを監視します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/view-assignments"} -->
## Microsoft Entra 役割の割り当てのリスト - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/view-assignments
- Service: entra-id / role-based-access-control
- Article date: 2025-01-03
- Summary: Microsoft Entra 管理センター、Microsoft Graph PowerShell、または Microsoft Graph API を使用して Microsoft Entra ロールの割り当てを一覧表示する方法について説明します。

この記事では、Microsoft Entra 管理センター、Microsoft Graph PowerShell、または Microsoft Graph API を使用して、Microsoft Entra ID で割り当てたロールを一覧表示する方法について説明します。

ロールの割り当てには、特定のセキュリティ プリンシパル (ユーザー、グループ、またはアプリケーション サービス プリンシパル) をロール定義にリンクする情報が含まれます。 ユーザー、グループ、割り当て済みロールは既定のユーザー アクセス許可で一覧表示できます。

### スコープ

Microsoft Entra ID では、ロールは異なるスコープで割り当てることができます。

- テナント スコープでのロールの割り当てが単一のアプリケーション ロールの割り当ての一覧に追加され、表示されます。
- 単一アプリケーション スコープでのロールの割り当ては、テナント スコープの割り当ての一覧には追加されず、表示されません。

### 前提条件

- PowerShell を使用する場合の [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) モジュール
- Microsoft Graph API の Graph エクスプローラーを使用する場合の管理者の同意

詳細については、「[PowerShell または Graph エクスプローラーを使用するための前提条件](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/prerequisites)」をご覧ください。

### Microsoft Entra 役割の割り当てのリスト

## [管理センター](#tab/admin-center)
#### 自分のロールの割り当てを一覧表示する

自分のアクセス許可も簡単に一覧表示することができます。 **[ロールと管理者]** ページの **[自分のロール]** を選択すると、現在自分に割り当てられているロールが表示されます。

[Image: Microsoft Entra 管理センターの [ロールと管理者] ページのスクリーンショット。]

#### ユーザーのロールの割り当ての一覧表示

Microsoft Entra 管理センターを使用してユーザーの Microsoft Entra ロールを一覧表示するには、次の手順に従います。 [Microsoft Entra Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) が有効になっているかどうかによって、エクスペリエンスは異なります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. ユーザー名 *を選択し、割り当てられたロール*&gt;を確認します。

    ユーザーに割り当てられたロールの一覧は、さまざまなスコープで表示できます。 さらに、ロールが直接割り当てられているか、グループ経由で割り当てられているかを確認できます。

    [Image: ユーザーに割り当てられているロールのスクリーンショット。]

    Microsoft Entra ID P2 ライセンスをお持ちの場合、有効、アクティブ、期限切れのロールの割り当ての詳細が含まれる、PIM エクスペリエンスが表示されます。

    [Image: PIM でユーザーに割り当てられたロールのスクリーンショット。]

#### グループのロールの割り当ての一覧表示

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[Entra ID]**&gt;**[グループ]**&gt;**[すべてのグループ]** に移動します。
3. ロール割り当て可能なグループを選択します。

    グループがロール割り当て可能かどうかを判断するには、グループの**プロパティ**を表示できます。
4. **[割り当てられたロール]** を選択します。

    これで、このグループに割り当てられているすべての Microsoft Entra ロールが表示されます。 **[割り当てられたロール]** オプションが表示されない場合、そのグループはロール割り当て可能なグループではありません。

    [Image: グループに割り当てられているロールのスクリーンショット。]

#### ロールの割り当てのダウンロード

組み込みロールやカスタム ロールを含むすべてのロールでアクティブなロールの割り当てをダウンロードするには、次の手順に従います。

一括操作は最大 1 時間しか実行できません。また、大規模なテナントでは制限があります。 詳細については、「一括操作」と「Microsoft Entra IDでのユーザー一括作成 」を参照してください。

1. **[ロールと管理者]** ページで、**[すべてのロール]** を選択します。
2. **[Download assignments] (割り当てのダウンロード)** を選択します。

    [Image: すべてのロールの割り当てをダウンロードするためのウィンドウのスクリーンショット。]
3. ファイル名を指定し、[ **一括操作の開始]** を選択します。

    すべてのロールのすべてのスコープでの割り当ての一覧を含む CSV ファイルがダウンロードされます。

特定のロールの割り当てをダウンロードするには、次の手順に従います。

1. **[ロールと管理者]** ページでロールを選択します。
2. **[Download assignments] (割り当てのダウンロード)** を選択します。

    Microsoft Entra ID P2 ライセンスをお持ちの場合、PIM エクスペリエンスが表示されます。 **エクスポート** を選択して役割の割り当てをダウンロードします。

    そのロールのすべてのスコープでの割り当てを一覧表示する CSV ファイルがダウンロードされます。

#### テナント スコープでロールの割り当てを一覧表示する

この手順では、テナント スコープでロールの割り当てを一覧表示する方法について説明します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**役割と管理者**に移動します。
3. ロール名を選択してロールを開きます。 ロールの横にチェック マークを付けないでください。

    [Image: [ロールと管理者] ページのスクリーンショット。ロール名をマウスでポイントしています。]
4. ロールの割り当てを一覧表示するには、 **[割り当て]** を選択します。

    [Image: テナント スコープでロールの割り当てを一覧表示するスクリーンショット。]
5. [**スコープ**] 列で、**ディレクトリ** スコープに関するロールの割り当てを確認します。

#### アプリ登録のスコープでロールの割り当てを一覧表示する

このセクションでは、単一アプリケーションのスコープでロールの割り当てを一覧表示する方法について説明します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**アプリ登録**に移動します。
3. 表示するロールの割り当ての一覧に対してアプリの登録を選択します。

    Microsoft Entra 組織内のアプリ登録の完全な一覧を表示するには、**[すべてのアプリケーション]** を選択する必要がある場合があります。
4. **[ロールと管理者]** を選択します。
5. ロール名を選択してロールを開きます。
6. ロールの割り当てを一覧表示するには、 **[割り当て]** を選択します。

    アプリの登録内から [割り当て] ページを開くと、この Microsoft Entra リソースをスコープとしたロールの割り当てが表示されます。

    [Image: アプリケーション登録スコープでロールの割り当てを一覧表示するスクリーンショット。]
7. **[スコープ]** 列で、**[このリソース]** スコープを使ってロールの割り当てを確認します。

#### 管理単位スコープでロールの割り当てを一覧表示する

管理単位スコープで作成されたすべてのロールの割り当ては、Microsoft Entra 管理センターの**管理ユニット** セクションで確認できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**役割と管理者**&gt;**管理単位**を閲覧する。
3. 表示するロールの割り当ての一覧で管理単位を選択します。
4. **[ロールと管理者]** を選択します。
5. ロール名を選択してロールを開きます。
6. ロールの割り当てを一覧表示するには、 **[割り当て]** を選択します。

    [Image: 管理単位スコープでロールの割り当てを一覧表示するスクリーンショット。]
7. **[スコープ]** 列で、**[このリソース]** スコープを使ってロールの割り当てを確認します。

## [PowerShell](#tab/ms-powershell)
このセクションでは、テナント スコープでロールの割り当てを表示する方法について説明します。 このセクションでは、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) モジュールを使用します。

#### セットアップ

1. [Install-Module](https://learn.microsoft.com/ja-jp/powershell/module/powershellget/install-module) を使用して Microsoft Graph モジュールをインストールします。

    ```powershell
    Install-Module -name Microsoft.Graph
    ```
2. [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) コマンドを使用し、Microsoft Graph PowerShell コマンドレットにサインインして使用します。

    ```powershell
    Connect-MgGraph
    ```

#### テナント スコープでロールの割り当てを一覧表示する

[Get-MgRoleManagementDirectoryRoleDefinition](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.governance/get-mgrolemanagementdirectoryroledefinition) および [Get-MgRoleManagementDirectoryRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.governance/get-mgrolemanagementdirectoryroleassignment) コマンドを使用して、ロールの割り当てを一覧表示します。

次の例は、[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)ロールに対するロールの割り当てを一覧表示する方法を示しています。

```powershell
# Get a specific directory role by ID
$role = Get-MgRoleManagementDirectoryRoleDefinition -UnifiedRoleDefinitionId fdd7a751-b60b-444a-984c-02652fe8fa1c

# Get role assignments for a given role definition
Get-MgRoleManagementDirectoryRoleAssignment -Filter "roleDefinitionId eq '$($role.Id)'"
```

```Example
Id                                            PrincipalId                          RoleDefinitionId                     DirectoryScopeId AppScop
                                                                                                                                         eId
--                                            -----------                          ----------------                     ---------------- -------
lAPpYvVpN0KRkAEhdxReEH2Fs3EjKm1BvSKkcYVN2to-1 aaaaaaaa-bbbb-cccc-1111-222222222222 62e90394-69f5-4237-9190-012177145e10 /
lAPpYvVpN0KRkAEhdxReEMdXLf2tIs1ClhpzQPsutrQ-1 bbbbbbbb-cccc-dddd-2222-333333333333 62e90394-69f5-4237-9190-012177145e10 /
```

次の例では、組み込みロールやカスタム ロールを含むすべてのロールでアクティブなすべてのロールの割り当てを一覧表示する方法を示します。

```powershell
$roles = Get-MgRoleManagementDirectoryRoleDefinition
foreach ($role in $roles)
{
  Get-MgRoleManagementDirectoryRoleAssignment -Filter "roleDefinitionId eq '$($role.Id)'"
}
```

```Example
Id                                            PrincipalId                          RoleDefinitionId                     DirectoryScopeId AppScop
                                                                                                                                         eId
--                                            -----------                          ----------------                     ---------------- -------
lAPpYvVpN0KRkAEhdxReEH2Fs3EjKm1BvSKkcYVN2to-1 aaaaaaaa-bbbb-cccc-1111-222222222222 62e90394-69f5-4237-9190-012177145e10 /
lAPpYvVpN0KRkAEhdxReEMdXLf2tIs1ClhpzQPsutrQ-1 bbbbbbbb-cccc-dddd-2222-333333333333 62e90394-69f5-4237-9190-012177145e10 /
4-PYiFWPHkqVOpuYmLiHa3ibEcXLJYtFq5x3Kkj2TkA-1 cccccccc-dddd-eeee-3333-444444444444 88d8e3e3-8f55-4a1e-953a-9b9898b8876b /
4-PYiFWPHkqVOpuYmLiHa2hXf3b8iY5KsVFjHNXFN4c-1 dddddddd-eeee-ffff-4444-555555555555 88d8e3e3-8f55-4a1e-953a-9b9898b8876b /
BSub0kaAukSHWB4mGC_PModww03rMgNOkpK77ePhDnI-1 eeeeeeee-ffff-aaaa-5555-666666666666 d29b2b05-8046-44ba-8758-1e26182fcf32 /
BSub0kaAukSHWB4mGC_PMgzOWSgXj8FHusA4iaaTyaI-1 ffffffff-aaaa-bbbb-6666-777777777777 d29b2b05-8046-44ba-8758-1e26182fcf32 /
```

#### プリンシパルのロールの割り当てを一覧表示する

[Get-MgRoleManagementDirectoryRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.governance/get-mgrolemanagementdirectoryroleassignment) コマンドを使用し、プリンシパルのロールの割り当てを一覧表示します。

```powershell
# Get role assignments for a given principal
Get-MgRoleManagementDirectoryRoleAssignment -Filter "PrincipalId eq 'aaaaaaaa-bbbb-cccc-1111-222222222222'"
```

#### プリンシパルの直接ロールと推移的なロールの割り当てを一覧表示する

[List transitiveRoleAssignments](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-list-transitiveroleassignments) API を使用して、ユーザーに直接および推移的に割り当てられているロールを取得します。

```powershell
$response = $null
$uri = "https://graph.microsoft.com/beta/roleManagement/directory/transitiveRoleAssignments?`$count=true&`$filter=principalId eq 'aaaaaaaa-bbbb-cccc-1111-222222222222'"
$method = 'GET'
$headers = @{'ConsistencyLevel' = 'eventual'}

$response = (Invoke-MgGraphRequest -Uri $uri -Headers $headers -Method $method -Body $null).value
```

#### グループのロールの割り当ての一覧表示

グループを取得するには、[Get-MgGroup](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/get-mggroup) コマンドを使用します。

```powershell
Get-MgGroup -Filter "DisplayName eq 'Contoso_Helpdesk_Administrators'"
```

[Get-MgRoleManagementDirectoryRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.governance/get-mgrolemanagementdirectoryroleassignment) コマンドを使用し、グループのロールの割り当てを一覧表示します。

```powershell
Get-MgRoleManagementDirectoryRoleAssignment -Filter "PrincipalId eq '<object id of group>'" 
```

#### 管理単位スコープでロールの割り当てを一覧表示する

[Get-MgDirectoryAdministrativeUnitScopedRoleMember](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdirectoryadministrativeunitscopedrolemember) コマンドを使用して、管理ユニット範囲とともにロールの割り当てを一覧表示します。

```powershell
$adminUnit = Get-MgDirectoryAdministrativeUnit -Filter "displayname eq 'Example_admin_unit_name'"
Get-MgDirectoryAdministrativeUnitScopedRoleMember -AdministrativeUnitId $adminUnit.Id | FL *
```

## [Graph API](#tab/ms-graph)
このセクションでは、テナント スコープでロールの割り当てを一覧表示する方法について説明します。 [List unifiedRoleAssignments](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-list-roleassignments) API を使用してロールの割り当てを取得します。

#### プリンシパルのロールの割り当てを一覧表示する

```http
GET https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments?$filter=principalId+eq+'<object-id-of-principal>'
```

[応答]

```http
HTTP/1.1 200 OK
{
"value":[
            { 
                "id": "A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0uIiSDKQoTVJrLE9etXyrY0-1"
                "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
                "roleDefinitionId": "10dae51f-b6af-4016-8d66-8c2a99b929b3",
                "directoryScopeId": "/"  
            } ,
            {
                "id": "C2dE3fH4iJ5kL6mN7oP8qR9sT0uV1wIiSDKQoTVJrLE9etXyrY0-1"
                "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
                "roleDefinitionId": "fe930be7-5e62-47db-91af-98c3a49a38b1",
                "directoryScopeId": "/"
            }
        ]
}
```

#### プリンシパルの直接ロールと推移的なロールの割り当てを一覧表示する

次の手順に従って、[Graph Explorer](https://aka.ms/ge)で Microsoft Graph API を使用してユーザーに割り当てられている Microsoft Entra ロールを一覧表示します。

1. [Graph エクスプローラー](https://aka.ms/ge)にサインインします。
2. [List transitiveRoleAssignments](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-list-transitiveroleassignments) API を使用して、ユーザーに直接および推移的に割り当てられているロールを取得します。 URL への次のクエリを追加します。

    ```http
    GET https://graph.microsoft.com/beta/rolemanagement/directory/transitiveRoleAssignments?$count=true&$filter=principalId eq 'aaaaaaaa-bbbb-cccc-1111-222222222222'
    ```
3. **要求ヘッダー** タブに移動します。`ConsistencyLevel` をキーとして追加し、`Eventual` をその値として設定します。
4. [**を選択してクエリ**を実行] します。

#### グループのロールの割り当ての一覧表示

[Get group](https://learn.microsoft.com/ja-jp/graph/api/group-get) API を使用してグループを取得します。

```http
GET https://graph.microsoft.com/v1.0/groups?$filter=displayName+eq+'Contoso_Helpdesk_Administrator'
```

[List unifiedRoleAssignments](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-list-roleassignments) API を使用して、ロールの割り当てを取得します。

```http
GET https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments?$filter=principalId eq
```

#### ロール定義のロールの割り当てを一覧表示する

次の例は、特定のロール定義のロールの割り当てを一覧表示する方法を示しています。

```http
GET https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments?$filter=roleDefinitionId eq '<template-id-of-role-definition>'
```

[応答]

```http
HTTP/1.1 200 OK
{
    "id": "C2dE3fH4iJ5kL6mN7oP8qR9sT0uV1wIiSDKQoTVJrLE9etXyrY0-1",
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
    "roleDefinitionId": "00000000-0000-0000-0000-000000000000",
    "directoryScopeId": "/"
}
```

#### ID でロールの割り当てを一覧表示する

```http
GET https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments/lAPpYvVpN0KRkAEhdxReEJC2sEqbR_9Hr48lds9SGHI-1
```

[応答]

```http
HTTP/1.1 200 OK
{ 
    "id": "A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0uIiSDKQoTVJrLE9etXyrY0-1",
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
    "roleDefinitionId": "10dae51f-b6af-4016-8d66-8c2a99b929b3",
    "directoryScopeId": "/"
}
```

#### アプリ登録のスコープでロールの割り当てを一覧表示する

```http
GET https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments?$filter=directoryScopeId+eq+'/d23998b1-8853-4c87-b95f-be97d6c6b610'
```

[応答]

```http
HTTP/1.1 200 OK
{
"value":[
            { 
                "id": "A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0uIiSDKQoTVJrLE9etXyrY0-1"
                "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
                "roleDefinitionId": "10dae51f-b6af-4016-8d66-8c2a99b929b3",
                "directoryScopeId": "/d23998b1-8853-4c87-b95f-be97d6c6b610"
            } ,
            {
                "id": "C2dE3fH4iJ5kL6mN7oP8qR9sT0uV1wIiSDKQoTVJrLE9etXyrY0-1"
                "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
                "roleDefinitionId": "00000000-0000-0000-0000-000000000000",
                "directoryScopeId": "/d23998b1-8853-4c87-b95f-be97d6c6b610"
            }
        ]
}
```

#### 管理単位スコープでロールの割り当てを一覧表示する

[List scopedRoleMember](https://learn.microsoft.com/ja-jp/graph/api/administrativeunit-list-scopedrolemembers) API を使用し、管理単位スコープのあるロール割り当てを一覧表示します。

要求

```http
GET /directory/administrativeUnits/{admin-unit-id}/scopedRoleMembers
```

本文

```http
{}
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/whats-new"} -->
## Microsoft Entra RBAC ドキュメントの新機能 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/whats-new
- Service: entra-id / role-based-access-control
- Article date: 2026-07-17
- Summary: Microsoft Entra ロールベースのアクセス制御 (RBAC) の新機能とドキュメントの機能強化について説明します。

この記事では、Microsoft Entra のロールベースのアクセス制御 (RBAC) の新機能とドキュメントの機能強化に関する情報を提供します。

### 2026

| 日付 | 面積 | 説明 |
| --- | --- | --- |
| 2026 年 6 月 | 役割 | [Entra SOC ID レスポンダー ロールを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#entra-soc-identity-responder)追加しました。 |
| 2026 年 6 月 | 役割 | [セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator) ロールが更新されました。 |
| 2026 年 6 月 | 役割 | [AI 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#ai-administrator)ロールを更新しました。 |
| 2026 年 7 月 | 役割 | [テナント ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#tenant-governance-administrator)ロールを特権ロールに更新しました。 |
| 2026 年 6 月 | 役割 | [AI 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#ai-administrator)ロールと [AI 閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#ai-reader)ロールが更新されました。 |
| 2026 年 6 月 | 役割 | [エージェント ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-administrator)ロールと[エージェント ID 開発者ロールが](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#agent-id-developer)更新されました。 |
| 2026 年 5 月 | 役割 | [ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)ロールを特権ロールに更新しました。 |
| 2026 年 4 月 | 役割 | [AI 閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#ai-reader)ロールと[顧客委任管理関係管理者ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#customer-delegated-admin-relationship-administrator)を追加しました。 |
| 2026 年 3 月 | 役割 | [Teams 外部コラボレーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#teams-external-collaboration-administrator)の役割を追加しました。 |
| 2026 年 3 月 | 役割 | [Entra Backup Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#entra-backup-administrator) ロールと [Entra Backup Reader](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#entra-backup-reader) ロールが追加されました。 |
| 2026 年 3 月 | 役割 | [テナント ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#tenant-governance-administrator)、[テナント ガバナンス閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#tenant-governance-reader)、[テナント ガバナンス関係管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#tenant-governance-relationship-administrator)、[テナント ガバナンス関係閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#tenant-governance-relationship-reader)のロールが追加されました。 |
| 2026 年 3 月 | 役割 | [認証機能拡張パスワード管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-extensibility-password-administrator)ロールを追加しました。 |

### 2025

| 日付 | 面積 | 説明 |
| --- | --- | --- |
| 2025 年 12 月 | 役割 | [Microsoft 365 サポート エンジニア](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#microsoft-365-support-engineer)の役割を追加しました。 |
| 2025 年 11 月 | 役割 | [SharePoint 高度な管理 Administrator ロールが](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#sharepoint-advanced-management-administrator)追加されました。 |
| 2025 年 11 月 | 役割 | [Exchange バックアップ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#exchange-backup-administrator)ロールを更新しました。 |
| 2025 年 11 月 | 役割 | [SharePoint バックアップ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#sharepoint-backup-administrator)ロールを更新しました。 |
| 2025 年 10 月 | 役割 | [Dragon Administrator ロールを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#dragon-administrator)追加しました。 |
| 2025 年 8 月 | 役割 | [Teams 閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#teams-reader)ロールを追加しました。 |
| 2025 年 6 月 | 役割 | [組織データ ソース管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#organizational-data-source-administrator)ロールを追加しました。 |
| 2025 年 6 月 | 管理単位 | 制限付き管理の管理単位の一般提供。 [Microsoft Entra ID の制限付き管理管理単位を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-restricted-management)参照してください。 |
| 2025 年 5 月 | 役割 | 複数のロールのアクセス許可を更新しました。 [Microsoft Entra の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関する記事をご覧ください。 |
| 2025 年 5 月 | 役割 | [Microsoft Graph Data Connect 管理者ロールを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#microsoft-graph-data-connect-administrator)追加しました。 |
| 2025 年 3 月 | 役割 | [Viva Glint テナント管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#viva-glint-tenant-administrator)ロールを追加しました。 |
| 2025 年 3 月 | 役割 | [IoT デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#iot-device-administrator)ロールを追加しました。 |
| 2025 年 3 月 | 役割 | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#people-administrator)ロールを追加しました。 |
| 2025 年 2 月 | 役割 | [グローバルなセキュリティで保護されたアクセス ログ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-secure-access-log-reader)ロールを追加しました。 |
| 2025 年 2 月 | セキュリティ | 緊急アクセスアカウントのガイダンスを更新しました。 「[Microsoft Entra ID で緊急アクセス用アカウントを管理する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)」を参照してください。 |
| 2025 年 2 月 | 役割 | [Microsoft 365 バックアップ管理者ロールを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#microsoft-365-backup-administrator)追加しました。 |
| 2025 年 1 月 | 保護されたアクション | 論理的に削除されたディレクトリ オブジェクトのハード削除から保護します。 [ディレクトリー・オブジェクトの削除を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-overview#deletion-of-directory-objects)参照してください。 |
| 2025 年 1 月 | 役割 | 複数のロールのアクセス許可を更新しました。 [Microsoft Entra の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関する記事をご覧ください。 |
| 2025 年 1 月 | ロールの割り当て | ロールの割り当てを一覧表示、追加、削除する方法の手順を更新しました。 「 [Microsoft Entra ロールの割り当ての一覧表示](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/view-assignments)」、「 [Microsoft Entra ロールの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)」、「 [Microsoft Entra ロールの割り当ての削除](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-remove-assignment)」を参照してください。 |
| 2025 年 1 月 | カスタム ロール | カスタム ロールを作成する方法の手順を更新しました。 [「Microsoft Entra ID でカスタム ロールを作成](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)する」を参照してください。 |
| 2025 年 1 月 | 役割 | 比較表のユーザーごとの多要素認証 (MFA) が更新されました。 [認証ロールの比較](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#compare-authentication-roles)を参照してください。 |
| 2025 年 1 月 | マイ スタッフ | マイ スタッフと管理単位の制限を更新しました。 「 [マイ スタッフを使用してユーザーを管理](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/my-staff-configure#limitations)する」を参照してください。 |

### 2024

| 日付 | 面積 | 説明 |
| --- | --- | --- |
| 2024 年 11 月 | 役割 | [AI 管理者ロールを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#ai-administrator)追加しました。 |
| 2024 年 10 月 | 管理単位 | 動的管理単位の一般提供。 [動的メンバーシップ グループの規則を使用した管理単位のユーザーまたはデバイスの管理を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-members-dynamic)参照してください。 |
| 2024 年 10 月 | 役割 | 最小限の特権ロールに対するいくつかの更新。 「[Microsoft Entra ID のタスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task)」を参照してください。 |
| 2024 年 10 月 | Microsoft Graph API | [ディレクトリ ロール](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-post-roleassignments) API の代わりに[統合 RBAC API を](https://learn.microsoft.com/ja-jp/graph/api/directoryrole-post-members)使用することをお勧めします。 [「Microsoft Entra ロールの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal?tabs=ms-graph)」を参照してください。 |
| 2024 年 9 月 | 役割 | 複数のロールのアクセス許可を更新しました。 [Microsoft Entra の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関する記事をご覧ください。 |
| 2024 年 8 月 | 役割 | [ディレクトリ同期アカウント](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#directory-synchronization-accounts) ロールのガイダンスとアクセス許可を更新しました。 |
| 2024 年 7 月 | Microsoft Graph API | `Directory.Write.Restricted`アクセス許可を非推奨にしました。 |
| 2024 年 7 月 | 管理単位 | 管理単位に新しいグループを作成するためのアクセス許可を更新しました。 [新しいグループを作成するためのアクセス許可を](https://learn.microsoft.com/ja-jp/graph/api/administrativeunit-post-members#permissions-to-create-a-new-group)参照してください。 |
| 2024 年 6 月 | 役割 | [User Experience Success Manager](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-experience-success-manager) ロールを追加しました。 |
| 2024 年 5 月 | 役割 | [SharePoint Embedded Administrator ロールを追加しました](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#sharepoint-administrator)。 |
| 2024 年 5 月 | 役割 | [Teams テレフォニー管理者ロールを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#teams-telephony-administrator)追加しました。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users"} -->
## Microsoft Entra ユーザー、グループ、ライセンス、およびドメインのドキュメント - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users
- Service: entra-id / users
- Article date: 2025-01-15
- Summary: グループ、ドメイン名、ライセンスを使用して、Microsoft Entra 組織のユーザー認証を管理する方法について説明します。

Microsoft Entra ID には、ライセンスの割り当て、グループとユーザーの管理、ドメイン名の追加または管理を行うことができるユーザー管理サービスが用意されています。

### エンタープライズ ユーザー管理について

#### 概要

- [ユーザー、グループ、ドメイン、ライセンス](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-overview-user-model)
- [カスタム ロールの概要](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview?context=/azure/active-directory/enterprise-users/context/ugr-context)

#### 概念

- [Microsoft Entra 組織の独立性](https://learn.microsoft.com/ja-jp/entra/identity/users/licensing-directory-independence)
- [グループを使用してアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/concept-learn-about-groups?context=/azure/active-directory/enterprise-users/context/ugr-context)

### 作業の開始

#### クイックスタート

- [ユーザーを追加する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users?context=/azure/active-directory/enterprise-users/context/ugr-context)
- [グループの有効期限ポリシーを設定する](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-quickstart-expiration)
- [ユーザーにライセンスを割り当てる](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing?context=/azure/active-directory/enterprise-users/context/ugr-context)

### Microsoft Entra ドメイン名の管理

#### 攻略ガイド

- [カスタム ドメイン名を追加する](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain?context=/azure/active-directory/enterprise-users/context/ugr-context)
- [カスタム ドメイン名の管理](https://learn.microsoft.com/ja-jp/entra/identity/users/domains-manage)

### グループの管理

#### 攻略ガイド

- [動的グループを作成する](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule)
- [PowerShell のグループ設定](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-settings-v2-cmdlets)
- [グループの名前付けポリシーを設定する](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-naming-policy)

### ドメインの追加

#### 攻略ガイド

- [カスタム ドメイン名を追加する](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain?context=/azure/active-directory/enterprise-users/context/ugr-context)
- [カスタム ドメイン名の管理](https://learn.microsoft.com/ja-jp/entra/identity/users/domains-manage)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/clean-up-stale-guest-accounts"} -->
## 古いゲスト アカウントを監視およびクリーンアップする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/clean-up-stale-guest-accounts
- Service: entra-id / users
- Article date: 2024-12-30
- Summary: アクセス レビューを使用して古いゲスト アカウントを監視およびクリーンアップする

### 概要

ユーザーが外部パートナーとコラボレーションを行うと、時間の経過と共に多くのゲスト アカウントが Microsoft Entra テナントに作成される可能性があります。 コラボレーションが終了し、ユーザーがテナントにアクセスしなくなると、ゲスト アカウントが古くなる可能性があります。 管理者は、非アクティブなゲスト分析情報を使用して、ゲスト アカウントを大規模に監視できます。 管理者は、アクセス レビューを使用して、非アクティブなゲスト ユーザーを自動的に確認したり、サインインをブロックしたり、ディレクトリから削除したりすることもできます。

[Microsoft Entra ID で非アクティブなユーザー アカウントを管理する方法](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-manage-inactive-user-accounts)について説明します。

古いゲスト アカウントの監視およびクリーンアップに有効な推奨されるパターンがいくつかあります:

1. 非アクティブなゲスト レポートを使用して、組織内の非アクティブなゲストに対するインテリジェントな分析情報を使用して、ゲスト アカウントを大規模に監視します。 組織のニーズに応じて非アクティブなしきい値をカスタマイズし、監視するゲスト ユーザーの範囲を絞り込み、非アクティブなゲスト ユーザーを特定します。
2. ゲストがまだアクセスが必要かどうかを自己証明するマルチステージ レビューを作成します。 第 2 ステージのレビュー担当者が結果を評価し、最終的な決定を行います。 アクセスが拒否されたゲストは無効になり、後で削除されます。
3. レビューを作成して、非アクティブな外部ゲストを削除します。 管理者は、非アクティブな時間を日数として定義します。 その時間内にテナントにサインインしなかったゲストを無効にし、後で削除します。 既定では、これは最近作成されたユーザーには影響しません。 [非アクティブなアカウントを識別する方法の詳細について説明します](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-manage-inactive-user-accounts#how-to-detect-inactive-user-accounts)。

次の手順を使用して、非アクティブなゲスト アカウントの監視を大規模に強化し、これらのパターンに従うアクセス レビューを作成する方法について説明します。 構成に関する推奨事項を検討し、環境に合わせて必要な変更を行います。

#### ライセンス要件

この機能を使用するには、Microsoft Entra ID Governance または Microsoft Entra スイートのライセンスが必要です。 要件に適したライセンスを見つけるには、 [Microsoft Entra ID ガバナンス のライセンスの基礎を](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)参照してください。

### 非アクティブなゲスト分析情報を使用してゲスト アカウントを大規模に監視する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **ID ガバナンス**&gt;**Dashboard** に移動します。
3. [ゲスト **アクセス ガバナンス** ] カードに移動し、[非アクティブなゲストの表示] を選択して **、非アクティブなゲスト** アカウント レポートにアクセスします。
4. 非アクティブゲストレポートは、90日間活動していないゲストユーザーに関するインサイトを提供します。 しきい値は既定で 90 日に設定されていますが、組織のニーズに基づいて **非アクティブしきい値の編集** を使用して構成できます。
5. このレポートの一部として、次の分析情報が提供されます:

    - ゲスト アカウントの概要(全ゲスト数と、サインインしたことが一度もないゲスト及び少なくとも一度はサインインしたゲストの更なる分類を含む非アクティブ ゲスト数)
    - ゲスト非アクティブ分散(前回のサインインからの日数に基づくゲスト ユーザーの割合分布)
    - ゲスト非アクティブの概要(非アクティブしきい値を構成するためのゲスト非アクティブ ガイダンス)
    - ゲスト アカウントの概要 (エクスポート可能な表形式のビュー。アクティビティの状態に関する分析情報を含む、すべてのゲスト アカウントの詳細が表示されます。アクティビティの状態は、構成されている非アクティビティしきい値に基づいてアクティブまたは非アクティブになります)
6. 非アクティブ日数は、ユーザーが少なくとも 1 回サインインした場合、最後のサインイン日に基づいて計算されます。 サインインしたことがないユーザーについては、非アクティブ日数は作成日に基づいて計算されます。

注

ゲスト分析情報を含むレポートは、 **すべてのデータ**のダウンロードを使用してダウンロードできます。 ダウンロードする各アクションは、ゲスト ユーザーの数に応じて時間がかかる場合があり、最大 100 万人のゲスト ユーザーのダウンロードを有効にします。

### ゲストが継続的なアクセスを自己証明するためのマルチステージ レビューを作成する

1. 確認するゲスト ユーザーの [動的グループ](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule) を作成します。 たとえば、

    `(user.userType -eq "Guest") and (user.mail -contains "@contoso.com") and (user.accountEnabled -eq true)`
2. 動的グループ [のアクセス レビューを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review) するには、 **Microsoft Entra ID &gt; Identity Governance &gt; Access Reviews** に移動します。
3. **新しいアクセス レビュー**を選択します。
4. レビューの種類を構成します。

    | プロパティ | 値 |
    | --- | --- |
    | レビュー対象を選択する | **チーム + グループ** |
    | レビューの範囲 | **Teams とグループを選択する** |
    | グループ | 動的グループを選択する |
    | Scope | **ゲスト ユーザーのみ** |
    | (省略可能) 非アクティブなゲストをレビューする | **非アクティブなユーザー (テナント レベル) のみの**チェック ボックスをオンにします。 非アクティブな時間を構成する日数を入力します。 |

    [Image: スクリーンショットは、ゲストが継続的なアクセスを自己証明するためのマルチステージ レビューのレビューの種類ダイアログを示しています。]
5. [ **次へ: レビュー]** を選択します。
6. レビューの構成:

    | プロパティ | 値 |
    | --- | --- |
    | **第 1 段階のレビュー** |  |
    | 複数ステージのレビュー | チェックボックスをオンにします。 |
    | レビュー担当者の選択 | **ユーザーが自分のアクセス権を確認する** |
    | ステージ期間 (日数) | 日数を入力します。 |
    | **第 2 段階のレビュー** |  |
    | レビュー担当者の選択 | **グループ所有者** または **選択したユーザーまたはグループ** |
    | ステージ期間 (日数) | 日数を入力します。(省略可能) フォールバック レビュー担当者を指定します。 |
    | **レビューの繰り返しを指定する** |  |
    | レビューの繰り返し | ドロップダウンから好みを選択してください。 |
    | 開始日 | 日付を選択する |
    | End | 自分の設定を選択する |
    | **レビュー者を指定して次のステージに進む** |  |
    | 次のステージに進むレビュー対象者 | レビュー対象者を選択します。 たとえば、自己承認または返信したユーザー **[不明**] を選択します。 |

    [Image: スクリーンショットは、ゲストが継続的なアクセスを自己証明するためのマルチステージ レビューの最初のステージ レビューを示しています。]
7. [ **次へ: 設定] を選択します**。
8. 設定の構成:

    | プロパティ | 値 |
    | --- | --- |
    | **完了時の設定** |  |
    | リソースへの結果の自動適用 | チェックボックスをオンにします。 |
    | レビュー担当者が応答しない場合 | **アクセス権の削除** |
    | 拒否されたゲスト ユーザーに適用するアクション | **ユーザーが 30 日間サインインできないようにしてから、テナントからユーザーを削除する** |
    | (省略可能) レビュー終了時の通知の送信先 | 通知する他のユーザーまたはグループを指定します。 |
    | **レビュー担当者の意思決定ヘルパーを有効にする** |  |
    | レビュー担当者のメールの追加コンテンツ | レビュー担当者向けのカスタム メッセージを追加する |
    | その他のすべてのフィールド | 残りのオプションについては既定値のままにします。 |

    [Image: ゲストが継続的なアクセスを自己証明するためのマルチステージ レビューの設定ダイアログを示すスクリーンショット。]
9. [ **次へ: 確認と作成**] を選択します。
10. アクセス レビュー名を入力します。 (省略可能)説明を入力します。
11. **作成**を選択します。

### レビューを作成して、非アクティブな外部ゲストを削除します。

1. 確認するゲスト ユーザーの [動的グループ](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule) を作成します。 たとえば、

    `(user.userType -eq "Guest") and (user.mail -contains "@contoso.com") and (user.accountEnabled -eq true)`
2. 動的グループ [のアクセス レビューを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review) するには、 **Microsoft Entra ID &gt; Identity Governance &gt; Access Reviews** に移動します。
3. **新しいアクセス レビュー**を選択します。
4. レビューの種類を構成します。

    | プロパティ | 値 |
    | --- | --- |
    | レビュー対象を選択する | **チーム + グループ** |
    | レビューの範囲 | **Teams とグループを選択する** |
    | グループ | 動的グループを選択する |
    | Scope | **ゲスト ユーザーのみ** |
    | 非アクティブなユーザー (テナント レベル) のみ | チェックボックスをオンにします。 |
    | 非アクティブな日数 | 非アクティブな時間を構成する日数を入力します。 |

    注

    構成する非アクティブ時間は、最近作成されたユーザーには影響しません。 アクセス レビューでは、構成した期間にユーザーが作成されているかどうかを確認し、少なくともその期間存在していないユーザーを無視します。 たとえば、非アクティブ時間を 90 日に設定し、ゲスト ユーザーが作成または招待されてから 90 日未満の場合、ゲスト ユーザーはアクセス レビューの対象になりません。 これにより、ゲストは削除される前に必ず 1 回はサインインできるようになります。

    [Image: 非アクティブな外部ゲストを削除するレビューの種類ダイアログを示すスクリーンショット。]
5. [ **次へ: レビュー]** を選択します。
6. レビューの構成:

    | プロパティ | 値 |
    | --- | --- |
    | **校閲者を指定** |  |
    | レビュー担当者の選択 | **[グループの所有者]** または [ユーザーまたはグループ] を選択します。(省略可能) プロセスを自動化したままにするには、アクションを実行しないレビュー担当者を選択します。 |
    | **レビューの繰り返しを指定する** |  |
    | 期間 (日数) | 設定に基づいて値を入力または選択する |
    | レビューの繰り返し | ドロップダウンから好みを選択してください。 |
    | 開始日 | 日付を選択する |
    | End | オプションを選択する。 |
7. [ **次へ: 設定] を選択します**。

    [Image: 非アクティブな外部ゲストを削除するための [レビュー] ダイアログを示すスクリーンショット。]
8. 設定の構成:

    | プロパティ | 値 |
    | --- | --- |
    | **完了時の設定** |  |
    | リソースへの結果の自動適用 | チェックボックスをオンにします。 |
    | レビューが応答しない場合 | **アクセス権の削除** |
    | 拒否されたゲスト ユーザーに適用するアクション | **ユーザーが 30 日間サインインできないようにしてから、テナントからユーザーを削除する** |
    | **レビュー担当者の意思決定ヘルパーを有効にする** |  |
    | 30 日間サインインなし | チェックボックスをオンにします。 |
    | その他のすべてのフィールド | 設定に基づいてチェックボックスをオンまたはオフにします。 |

    [Image: 非アクティブな外部ゲストを削除するための [設定] ダイアログを示すスクリーンショット。]
9. [ **次へ: 確認と作成**] を選択します。
10. アクセス レビュー名を入力します。 (省略可能)説明を入力します。
11. **作成**を選択します。

構成した日数の間テナントにサインインしないゲスト ユーザーは、30 日間無効にされてから削除されます。 削除後、最大 30 日間はゲストを復元できます。その後は、新しい招待が必要になります。

注

アクセス レビューの決定がまだ適用されていない場合は、API [accessReviewInstance: stopApplyDecisions](https://learn.microsoft.com/ja-jp/graph/api/accessreviewinstance-stopapplydecisions) を使用してアクティブな適用の決定を停止できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/clean-up-unmanaged-accounts"} -->
## 管理されていない Microsoft Entra アカウントをクリーンアップする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/clean-up-unmanaged-accounts
- Service: entra-id / users
- Article date: 2023-05-02
- Summary: Microsoft Entra ID の電子メール ワンタイム パスワードと PowerShell モジュールを使用して、アンマネージド アカウントをクリーンアップする

### 概要

2022 年 8 月より前、Microsoft Entra B2B では、電子メールで確認されたユーザーのセルフサービス サインアップがサポートされました。 この機能を使用すると、ユーザーは電子メールの所有権を確認するときに Microsoft Entra アカウントを作成します。 これらのアカウントはアンマネージド (またはバイラル) テナントで作成されました。ユーザーは、IT チームの管理下ではなく、組織のドメインを持つアカウントを作成しました。 ユーザーが組織を離れた後も、アクセスは保持されます。

詳細については、「 [Microsoft Entra ID のセルフサービス サインアップとは」を](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-self-service-signup)参照してください。

注

Microsoft Entra B2B 経由のアンマネージド Microsoft Entra アカウントは非推奨となりました。 2022 年 8 月の時点で、新しい B2B の招待を引き換えることはできません。 ただし、2022 年 8 月より前の招待は、管理されていない Microsoft Entra アカウントで引き換え可能でした。

### アンマネージド Microsoft Entra アカウントを削除する

Microsoft Entra テナントからアンマネージド Microsoft Entra アカウントを削除するには、次のガイダンスを使用します。 ツール機能は、Microsoft Entra テナント内のバイラル ユーザーを識別するのに役立ちます。 ユーザーの引き換えの状態をリセットできます。

- [Azure-samples/Remove-unmanaged-guests のサンプル アプリケーションを使用します](https://github.com/Azure-Samples/Remove-Unmanaged-Guests)。
- [`MSIdentityTools`](https://github.com/AzureAD/MSIdentityTools/wiki/)で PowerShell コマンドレットを使用します。

#### 招待を承認する

ツールを実行すると、管理されていない Microsoft Entra アカウントを持つユーザーがテナントにアクセスし、招待を再使用します。 ただし、Microsoft Entra ID により、ユーザーは管理されていない Microsoft Entra アカウントでの特典交換を行うことができません。 別の種類のアカウントで引き換えることができます。 Google フェデレーションと SAML/WS-Federation は、既定では有効になっていません。 そのため、ユーザーは Microsoft アカウント (MSA) または電子メール ワンタイム パスワード (OTP) を使用して引き換えます。 MSA をお勧めします。

詳細については、「 [招待の引き換えフロー」](https://learn.microsoft.com/ja-jp/entra/external-id/redemption-experience#invitation-redemption-flow)を参照してください。

### 支配されたテナントとドメイン

一部のアンマネージド テナントをマネージド テナントに変換できます。

詳細については、「[Microsoft Entra ID で管理者として非管理対象ディレクトリを引き継ぐ](https://learn.microsoft.com/ja-jp/entra/identity/users/domains-admin-takeover)」を参照してください。

一部の超過ドメインは更新されない場合があります。 たとえば、不足している DNS TXT レコードは、アンマネージド状態を示します。 影響は次のとおりです。

- 管理されていないテナントのゲスト ユーザーの場合、引き換えの状態はリセットされます。 同意プロンプトが表示されます。
    - 引き換えは同じアカウントで行われます。
- このツールは、アンマネージド ユーザーの引き換え状態をリセットした後に、アンマネージド ユーザーを誤検知として識別する場合があります。

### サンプルアプリケーションを使って引き換えをリセットする

[Azure-Samples/Remove-Unmanaged-Guests でサンプル アプリケーションを使用します](https://github.com/Azure-Samples/Remove-Unmanaged-Guests)。

### `MSIdentityTools` PowerShell モジュールを使用して引き換えのリセットを行う

`MSIdentityTools` PowerShell モジュールは、Microsoft ID プラットフォームと Microsoft Entra ID で使用するコマンドレットとスクリプトのコレクションです。 コマンドレットとスクリプトを使用して、PowerShell SDK の機能を強化します。 [microsoftgraph/msgraph-sdk-powershell](https://github.com/microsoftgraph/msgraph-sdk-powershell) を参照してください。

次のコマンドレットを実行します。

- `Install-Module Microsoft.Graph -Scope CurrentUser`
- `Install-Module MSIdentityTools`
- `Import-Module msidentitytools,microsoft.graph`

アンマネージド Microsoft Entra アカウントを識別するには、次のコマンドを実行します。

- `Connect-MgGraph -Scope User.Read.All`
- `Get-MsIdUnmanagedExternalUser`

アンマネージド Microsoft Entra アカウントの引き換え状態をリセットするには、次のコマンドを実行します。

- `Connect-MgGraph -Scopes User.ReadWriteAll`
- `Get-MsIdUnmanagedExternalUser | Reset-MsIdExternalUser`

アンマネージド Microsoft Entra アカウントを削除するには、次のコマンドを実行します。

- `Connect-MgGraph -Scopes User.ReadWriteAll`
- `Get-MsIdUnmanagedExternalUser | Remove-MgUser`

### 資源

次のツールは、テナント内の外部アンマネージド ユーザー (バイラル ユーザー) の一覧を返します。 [Get-MSIdUnmanagedExternalUser](https://github.com/AzureAD/MSIdentityTools/wiki/Get-MsIdUnmanagedExternalUser) を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/convert-external-users-internal"} -->
## 外部ユーザーを内部ユーザーに変換する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/convert-external-users-internal
- Service: entra-id / users
- Article date: 2025-01-06
- Summary: ユーザーを再作成しなくても、ユーザーを外部から内部に変換できます。

### 概要

組織再編、合併、買収などの企業では、既存のユーザーの一部またはすべてを操作する方法を変更する必要がある場合があります。 場合によっては、管理者は既存の外部ユーザーを内部ユーザーに変更する必要があります。

外部ユーザー変換では、既存のユーザー オブジェクトを削除して新しいユーザー オブジェクトを作成する必要なく、外部ユーザーから内部ユーザーへの変換が処理されます。 ユーザー オブジェクトを保持することで、ユーザーは元のアカウントを保持でき、アクセスが中断されることはなくなります。 変換されたユーザーのアカウントは、ホスト組織との関係の変化に応じて、アクティビティの履歴をそのまま保持します。

- **内部ユーザー** は、ローカル テナントで認証を行うユーザーです。
- **外部ユーザー** とは、別の組織の Microsoft Entra ID、Google フェデレーション、Microsoft アカウントなど、ホスト組織が管理していない方法で認証を行うユーザーです。 多くの外部ユーザーは *userType* が `guest` ですが、*userType* とユーザーのサインイン方法の間に正式な関係はありません。 *userType* が `member` である外部ユーザーも、変換の対象となる可能性があります。

外部ユーザーは、 [Microsoft Graph API または Microsoft](https://graph.microsoft.com) Entra 管理センターを使用して変換できます。

### 外部ユーザーの変換

userType と  と  では、ユーザーがどこで認証されるかではなく、ユーザーがこのテナントにおいて持っている権限のレベルを定義します。 ユーザーの *userType* を更新できますが、それだけではユーザーの外部と内部の状態は変更されません。 外部ユーザーを内部ユーザーに変更するには、「同期されたユーザー変換」を参照してください。

内部への変換が可能な外部ユーザーには、次の 2 種類があります。

- クラウド専用ユーザー
- 同期されたユーザー

#### クラウド ユーザーの変換

クラウド ユーザーを外部から内部に変換する場合、管理者はユーザーの *UPN* と "パスワード" を指定する必要があります。 クラウド ユーザーを同期されたユーザーに変換すると、ユーザーが現在のテナントで認証できるようになります。

#### 同期されたユーザー変換

同期されたユーザー変換を使用すると、Microsoft Entra ID でユーザーを外部から内部に変換できます。 [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect) を使ってオンプレミスの ID を同期することができます。 ユーザーを外部ユーザーから内部ユーザーに変換する場合、ユーザーの権限ソースは引き続きオンプレミスですが、ユーザーは内部ユーザーとして認証されます。

*同期されたユーザー* は、オンプレミスから同期されたユーザーです。 これらのアカウントはソースで管理されるため、管理者はこれらのユーザーの UPN を指定できません。

- テナントでパスワード ハッシュ同期 (PHS) が有効になっている同期されたユーザーは、管理者が変換中に新しいパスワードを設定できないようにブロックされます。
- テナントがフェデレーション認証を使用している場合、管理者は変換中に同期されたユーザーの新しいパスワードの設定をブロックされます。
- テナントが管理されていて、クラウド認証が使用されていて、テナントで PHS が有効になっていない場合、管理者は変換時にパスワードを指定する必要があります。

### 外部ユーザー変換をテストする

外部ユーザーのコンバージョンをテストする場合は、使用できなくなった場合に中断が発生しないテスト アカウントまたはアカウントを使用します。

#### 要件

- 外部ユーザーを内部ユーザーに変換するには、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)ロールが割り当てられているアカウントが必要です。
- 変換の対象となるのは、ホスト組織の外部の認証方法で構成されたユーザーだけです。

#### 外部ユーザーの変換

Microsoft Entra 管理センターを使用して、クラウドのみのユーザーや同期されたユーザーなどの外部ユーザーを内部ユーザーに変換できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. 外部ユーザーを選択します。
4. **[内部ユーザーに変換]** を選択します。

    [Image: ユーザー プロパティを示すスクリーンショット。[内部ユーザーに変換] オプションが赤いボックスで囲まれています。]
5. **[内部ユーザーに変換]** セクションで、いくつかの手順を完了する必要があります:

    1. **ユーザー プリンシパル名 (UPN)** を入力します。 この値は、ユーザーの新しい UPN 値です。 クラウドのみのユーザーの場合、UPN ドメインは非フェデレーションのドメインである必要があります。 オンプレミスの同期されたユーザーの場合、UPN を指定する必要はありません。 ユーザーは引き続きオンプレミスの資格情報を使用します。
    2. 自動生成されたパスワードが必要な場合は、チェック ボックスをオンにします。
    3. **[メール アドレスの変更]** のチェック ボックスをオンにすると、クラウド ユーザーに対してオプションの新しいメール アドレスを指定できます。

    [Image: 外部ユーザーを内部ユーザーに変換する前に選択する必要がある最後のオプション セットを示すスクリーンショット。]
6. オプションを確認し、必要な選択を行った後、**[変換]** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/directory-delegated-administration-primer"} -->
## Microsoft Entra ID での代理管理 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/directory-delegated-administration-primer
- Service: entra-id / users
- Article date: 2024-12-13
- Summary: 以前の代理管理アクセス許可と、Microsoft Entra ID の新しい詳細な代理管理アクセス許可の関係

### 概要

外部パートナーのアクセス許可を管理することは、セキュリティ態勢にとって重要です。 Microsoft Entra の一部である Microsoft Entra ID の管理者ポータル エクスペリエンスに、テナントを管理できる Microsoft クラウド サービス プロバイダー (CSP) との Microsoft Entra テナントとの関係を管理者が確認できるように機能が含まれるようになりました。 このアクセス許可モデルは、代理管理と呼ばれます。 この記事では、Microsoft Entra 管理者を対象に、以前の代理管理アクセス許可 (DAP) アクセス許可モデルと新しい[詳細な代理管理アクセス許可 (GDAP)](https://learn.microsoft.com/ja-jp/partner-center/gdap-introduction)アクセス許可モデルの関係について説明します。

### 委任された管理関係

代理管理の関係により、Microsoft CSP の技術者は、組織に代わって Microsoft 365、Dynamics 365、Azure などの Microsoft サービスを管理できます。 これらの技術者は、組織の管理者と同じ役割とアクセス許可を使用して、これらのサービスを管理します。 これらのロールは、CSP の Microsoft Entra テナントのセキュリティ グループに割り当てられます。そのため、CSP 技術者はサービスを管理するためにテナントにユーザー アカウントを必要としません。

Azure portal エクスペリエンスに表示される代理管理の関係には、2 種類あります。 新しい種類の代理管理者関係は、詳細な代理管理アクセス許可と呼ばれます。 以前の種類の関係は、代理管理アクセス許可と呼ばれます。 Azure portal にサインインし、**[代理管理]** を選択すると、両方の種類の関係を確認できます。

### 詳細な委任された管理者のアクセス許可

Microsoft CSP がテナントの GDAP リレーションシップ要求を作成する場合、グローバル管理者は要求を承認する必要があります。 GDAP 関係要求では、以下が指定されます。

- CSP パートナー テナント
- パートナーが技術者に委任する必要があるロール
- 有効期限日

テナントに GDAP リレーションシップがある場合は、Microsoft Entra 管理センターの **代理管理** ページに通知バナーが表示されます。 通知バナーを選択して、Microsoft Admin Center の **[パートナー]** ページで GDAP 関係を表示および管理します。

### 代理管理アクセス許可

すべての DAP 関係により、CSP はグローバル管理者およびヘルプデスク管理者の役割を技術者に委任できます。 GDAP リレーションシップとは異なり、DAP リレーションシップは、ユーザーまたは CSP によって取り消されるまで保持されます。

テナントに DAP リレーションシップがある場合は、Azure portal の **[委任された管理** ] ページの一覧に表示されます。 CSP の DAP 関係を削除するには、Microsoft 管理センターの **[パートナー** ] ページへのリンクに従います。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/directory-delete-howto"} -->
## Microsoft Entra テナントを削除する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/directory-delete-howto
- Service: entra-id / users
- Article date: 2024-12-19
- Summary: セルフサービス テナントを含むMicrosoft Entra テナントを削除用に準備する方法について説明します。

### 概要

Microsoft Entra IDで組織 (テナント) が削除されると、組織内のすべてのリソースも削除されます。 組織でその関連リソースを最小限にして準備してから、削除してください。 グローバル管理者のみが、Microsoft Entra管理センターからMicrosoft Entra組織を削除できます。

### 組織を準備する

複数のチェックに合格するまで、Microsoft Entra ID内の組織を削除することはできません。 これらのチェックにより、Microsoft Entra組織を削除すると、Microsoft 365にサインインしたり、Azure内のリソースにアクセスしたりする機能など、ユーザー アクセスに悪影響を与えるリスクが軽減されます。 たとえば、サブスクリプションに関連付けられている組織が誤って削除された場合、ユーザーはそのサブスクリプションのAzure リソースにアクセスできません。

次の状況を確認します。

- 未払いの請求書と支払期日または期限切れの金額をすべて支払いました。
- 組織の削除を担当する 1 人のグローバル管理者を除き、Microsoft Entra テナントにユーザーはいません。 組織を削除するには、他のユーザーを削除する必要があります。

    ユーザーがオンプレミスから同期されている場合は、最初に同期をオフにします。 Microsoft Entra管理センターまたはAzure PowerShellコマンドレットを使用して、クラウド組織内のユーザーを削除する必要があります。
- 組織内にアプリケーションが存在しない。 組織を削除する前に、アプリケーションを削除する必要があります。
- その組織にリンクされる多要素認証プロバイダーが存在しない。
- Microsoft Online Services オファリング (Azure、Microsoft 365、P1 または P2 Microsoft Entra IDなど) のサブスクリプションは組織に関連付けされません。

    たとえば、既定のMicrosoft Entra テナントが自動的に作成された場合、サブスクリプションが認証に依存している場合、この組織を削除することはできません。 また、別のユーザーのサブスクリプションとの関連付けが残っている場合は、テナントを削除できません。

注

Microsoft は、特定のテナント構成をお持ちのお客様が、Microsoft Entra組織を正常に削除できない可能性があることを認識しています。 Microsoft は、この問題に対処するために取り組んでいます。 詳細については、Microsoft サポートにお問い合わせください。

### 組織を削除する

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **Microsoft Entra ID** を選択します。
3. テナントの **[概要]** ページで **[テナント管理]** を選択します。

    [Image: テナントを管理するためのボタンを示すスクリーンショット。]
4. 削除したいテナントのチェック ボックスをオンにし、**[削除]** を選択します。

    [Image: 組織を削除するためのボタンを示すスクリーンショット。]
5. 組織が 1 つ以上のチェックに合格しない場合は、合格方法に関する詳細情報へのリンクが表示されます。 すべてのチェックに合格したら、 **[削除]** を選択してプロセスを完了します。

### 組織の削除を許可するサブスクリプションのプロビジョニング解除

Microsoft Entra ID P2、Microsoft 365 Business Standard、Enterprise Mobility + Security E5 など、組織のライセンスベースのサブスクリプションもアクティブ化した場合、サブスクリプションが完全に削除されるまで、組織を削除することはできません。 組織の削除を許可するには、サブスクリプションが**プロビジョニング解除済み**状態である必要があります。 **有効期限切れ**または**キャンセル済み**のサブスクリプションは、**無効** 状態に移行します。そして、最終段階が**プロビジョニング解除済み**状態です。

試用版Microsoft 365サブスクリプションの有効期限が切れたとき (有料のパートナー/CSP、エンタープライズ契約、ボリューム ライセンスを含まない) については、次の表を参照してください。 Microsoft 365データの保持とサブスクリプションのライフサイクルの詳細については、「[ビジネス サブスクリプションのMicrosoft 365が終了したときにデータとアクセスはどうなりますか?](https://support.office.com/article/what-happens-to-my-data-and-access-when-my-office-365-for-business-subscription-ends-4436582f-211a-45ec-b72e-33647f97d8a3)を参照してください。

| サブスクリプションの状態 | データ | データへのアクセス |
| --- | --- | --- |
| **アクティブ** (試用版の 30 日間) | すべてのユーザーがデータにアクセスできます。 | ユーザーは、Microsoft 365ファイルまたはアプリに通常アクセスできます。管理者は、Microsoft 365管理センターとリソースに通常アクセスできます。 |
| **有効期限切れ** (30 日間) | すべてのユーザーがデータにアクセスできます。 | ユーザーは、Microsoft 365ファイルまたはアプリに通常アクセスできます。管理者は、Microsoft 365管理センターとリソースに通常アクセスできます。 |
| **無効** (30 日間) | 管理者のみがデータにアクセスできます。 | ユーザーはMicrosoft 365ファイルやアプリにアクセスできません。管理者はMicrosoft 365管理センターにアクセスできますが、ユーザーにライセンスを割り当てたり、ユーザーを更新したりすることはできません。 |
| **プロビジョニング解除** (**無効**後 30 日間) | データは削除されます (使用中の他のサービスがない場合は自動的に削除)。 | ユーザーはMicrosoft 365ファイルやアプリにアクセスできません。管理者は、Microsoft 365管理センターにアクセスして、他のサブスクリプションを購入および管理できます。 |

### Office 365またはMicrosoft 365サブスクリプションを削除する

Microsoft 管理センターを使用して、サブスクリプションを 3 日以内に削除できるように**プロビジョニング解除済み**状態にすることができます。

1. 組織内のグローバル管理者であるアカウントを使用して、[Microsoft 365管理センター](https://admin.microsoft.com)にサインインします。 既定の初期ドメイン `contoso.onmicrosoft.com` を持つ Contoso 組織を削除する場合は、`admin@contoso.onmicrosoft.com` などのユーザー プリンシパル名 (UPN) を使用してサインインします。
2. サブスクリプションを削除する前に、取り消す必要があります。 **[課金]**&gt;**お使いの製品**を選択してから、キャンセルするサブスクリプションの **[サブスクリプションのキャンセル]** を選択します。

    [Image: キャンセルするサブスクリプションの選択を示すスクリーンショット。]
3. フィードバック フォームに入力し、**[サブスクリプションのキャンセル]** を選択します。

    [Image: フィードバック オプションとサブスクリプションを取り消すボタンを示すスクリーンショット。]
4. 削除するサブスクリプションの **[削除]** を選択します。 **お使いの製品**ページでサブスクリプションが見つからない場合は、**[サブスクリプションの状態]** を **[すべて]** に設定してあることを確認してください。

    [Image: サブスクリプションの状態と削除リンクを示すスクリーンショット。]
5. 使用条件に同意するチェック ボックスをオンにし、**[サブスクリプションの削除]** を選択します。 サブスクリプションのすべてのデータは、3 日後に完全に削除されます。 気が変わった場合は、3 日の間に[サブスクリプションを再アクティブ化する](https://learn.microsoft.com/ja-jp/microsoft-365/commerce/subscriptions/reactivate-your-subscription)ことができます。

    [Image: 使用条件のリンクと、サブスクリプションを削除するためのボタンを示すスクリーンショット。]

    サブスクリプションの状態が **無効**になり、サブスクリプションが削除対象としてマークされます。 このサブスクリプションは、72 時間後に**プロビジョニング解除済み**状態になります。
6. サブスクリプションを削除してから 72 時間後に、もう一度Microsoft Entra管理センターにサインインします。 必須のアクションやサブスクリプションによって、組織の削除がブロックされていないことを確認します。 Microsoft Entra組織を正常に削除できる必要があります。

    [Image: サブスクリプション チェックに合格したリソースを示すスクリーンショット。]

### Azure サブスクリプションを削除する

Microsoft Entra テナントに関連付けられているアクティブなサブスクリプションまたは取り消されたAzureサブスクリプションがある場合、テナントを削除することはできません。 サブスクリプションのキャンセル直後に請求は停止します。 サブスクリプションを取り消してから 7 日後に、削除済みサブスクリプション オプションが使用可能になったときに、Azure ポータルを使用してサブスクリプションを直接削除できます。 サブスクリプションが削除されると、データにアクセスするかサブスクリプションを再アクティブ化する必要がある場合に備えて、Microsoft はデータを完全に削除するまで 30 ~ 90 日待機します。 このデータの保持に対しては課金されません。 詳しくは、[Microsoft Trust Center の Microsoft によるデータの管理方法](https://go.microsoft.com/fwLink/p/?LinkID=822930&amp;clcid=0x409)に関するページをご覧ください。

無料試用版または従量課金制サブスクリプションをお持ちの場合は、[サブスクリプションの削除] オプションが利用可能になったときに、サブスクリプションを取り消してから 3 日後に **サブスクリプションを削除** できます。 詳細については、 「[無料試用版または従量課金制サブスクリプションを削除する](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/cancel-azure-subscription#delete-subscriptions)」を参照してください。

他の種類のサブスクリプションはすべて、[サブスクリプションの取り消し](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/cancel-azure-subscription#cancel-a-subscription-in-the-azure-portal)手続きにより削除されます。 つまり、無料試用版と従量課金制サブスクリプション以外のサブスクリプションは、直接削除することができません。

または、Azure サブスクリプションを別のテナントに移動することもできます。 サブスクリプションの課金所有権を別のテナント内のアカウントに移動すると、サブスクリプションをその新しいアカウントのテナントに移動することができます。 サブスクリプションに対して **Switch Directory** アクションを実行しても問題ありません。これは、サブスクリプションへのサインアップに使用されたMicrosoft Entra テナントと引き続き課金が調整されるためです。 詳細については、「[サブスクリプションを別のMicrosoft Entraテナント アカウントに転送する](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/billing-subscription-transfer#transfer-a-subscription-to-another-azure-ad-tenant-account)」を参照>。

すべてのAzure、Office 365、およびMicrosoft 365サブスクリプションを取り消して削除したら、Microsoft Entra テナント内の残りの部分を削除する前にクリーンアップできます。

### 削除できないエンタープライズ アプリを削除する

一部のエンタープライズ アプリケーションは、Microsoft Entra管理センターで削除できないため、テナントの削除がブロックされる可能性があります。

警告

このコードは、デモンストレーション用のサンプルとして提供されています。 ご利用の環境で使用する場合は、まず小規模にテストするか別のテスト組織でテストすることを検討してください。 環境の特定のニーズに合わせてコードを調整する必要がある場合があります。

これらのアプリケーションを削除するには、次の PowerShell コードを使用します。

1. [Install](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)次のコマンドを実行して、Microsoft Graph PowerShell モジュールを実行します。

    ```powershell
    Install-Module Microsoft.Graph
    ```
2. 次のコマンドを実行して、Az PowerShell モジュールをインストールします。

    ```powershell
    Install-Module -Name Az
    ```
3. 削除したいテナントからマネージド管理者アカウントを作成または使用します。 たとえば、 `newAdmin@tenanttodelete.onmicrosoft.com`と指定します。
4. PowerShell を開き、次のコマンドを使用して管理者資格情報を使用してMicrosoft Entra IDに接続します: `Connect-MgGraph -Scopes "Application.ReadWrite.All"`

    警告

    削除しようとしているテナントの管理者資格情報を使用して PowerShell を実行する必要があります。 PowerShell を使用してディレクトリを管理するアクセス権を持つのは、自宅の管理者だけです。 ゲスト ユーザー管理者、Microsoft アカウント、または複数のディレクトリを使用することはできません。

    先に進む前に、Microsoft Graph PowerShell モジュールを使用して削除するテナントに接続されていることを確認します。 `Get-MgDomain` コマンドを実行して、正しいテナント ID と `onmicrosoft.com` ドメインに接続していることを確認することをお勧めします。
5. 次のコマンドを実行して、Az PowerShell モジュールを使用してテナント コンテキストを確認します。 この手順は、正しいテナントに接続されていることを確認するための安全性チェックです。 **これらの手順をスキップしないでください。または、間違ったテナントからエンタープライズ アプリを削除するリスクがあります。**

    ```powershell
    Clear-AzContext -Scope CurrentUser
    Connect-AzAccount -Tenant <object id of the tenant you are attempting to delete>
    Get-AzContext
    ```

    警告

    続行する前に、Az PowerShell モジュールを使用して削除するテナントに接続していることを確認します。 `Get-AzContext` コマンドを実行して、接続されているテナント ID と `onmicrosoft.com` ドメインを確認することをお勧めします。 上記の手順をスキップしたり、誤ったテナントからエンタープライズ アプリを削除したりするリスクを実行しないでください。
6. 次のコマンドを実行して、サービス プリンシパルを削除します。 依存関係が原因で最初の試行で失敗する可能性があるため、すべてのサービス プリンシパルが削除されるまで、コマンドを複数回実行します。

    ```powershell
    Get-MgServicePrincipal -All | ForEach-Object { Remove-MgServicePrincipal -ServicePrincipalId $_.Id }
    ```
7. 一部のサービス プリンシパルを削除できない場合は、テナントの削除をブロックしないように無効にしてから、削除を再試行します。

    ```powershell
    $ServicePrincipalUpdate = @{ "accountEnabled" = "false" }
    
    Get-MgServicePrincipal -All | ForEach-Object { Update-MgServicePrincipal -ServicePrincipalId $_.Id -BodyParameter $ServicePrincipalUpdate }
    Get-MgServicePrincipal -All | ForEach-Object { Remove-MgServicePrincipal -ServicePrincipalId $_.Id }
    ```
8. [Microsoft Entra管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインし、手順 3 で作成した新しい管理者アカウントをすべて削除します。
9. Microsoft Entra管理センターからテナントの削除を再試行します。

### 削除をブロックする試用版サブスクリプションを処理する

Microsoft Power BI、Azure Rights Management、Microsoft Power Apps、Dynamics 365 など、[自身のサービスサインアップ製品](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/self-service-sign-up)があります。 個々のユーザーは、Microsoft 365経由でサインアップできます。これによって、Microsoft Entra組織で認証用のゲスト ユーザーも作成されます。

これらのセルフサービスの製品では、データ損失を回避するために、組織から製品が完全に削除されるまでディレクトリの削除がブロックされます。 ユーザーが個別にサインアップしたか、製品が割り当てられているかに関係なく、Microsoft Entra管理者のみが削除できます。

セルフサービス サインアップ製品の割り当て方法は 2 とおりあります。

- 組織レベルの割り当て: Microsoft Entra管理者が製品を組織全体に割り当てます。 ユーザーは、ユーザーが個別にライセンスされていない場合でも、組織レベルの割り当てでサービスをアクティブに使用できます。
- ユーザー レベルの割り当て: セルフサービス サインアップ中は、基本的に個々のユーザーが管理者なしで自分自身に製品を割り当てています。管理者が組織の管理を開始すると ([管理者による非管理対象組織の引き継ぎ](https://learn.microsoft.com/ja-jp/entra/identity/users/domains-admin-takeover)に関する記事を参照)、管理者はセルフサービス サインアップなしで直接ユーザーに製品を割り当てることができます。

セルフサービス サインアップ製品の削除を開始すると、データは完全に削除され、そのサービスへのユーザー アクセスもすべて削除されます。 その後、個別または組織レベルでオファーが割り当てられたユーザーは、サインインしたり既存のデータにアクセスしたりできないようにブロックされます。 [Microsoft Power BI ダッシュボード](https://learn.microsoft.com/ja-jp/power-bi/create-reports/service-export-to-pbix)や[Azure RMS ポリシー構成](https://learn.microsoft.com/ja-jp/previous-versions/azure/information-protection/configure-policy#how-to-configure-the-azure-information-protection-policy)などのセルフサービス サインアップ製品でデータが失われないようにするには、データがバックアップされて他の場所に保存されていることを確認します。

現在使用可能なセルフサービス サインアップ製品およびサービスの詳細については、「[利用可能なセルフサービス プログラム](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/self-service-sign-up#available-self-service-programs)」を参照してください。

試用版Microsoft 365サブスクリプションの有効期限が切れたとき (有料のパートナー/CSP、エンタープライズ契約、ボリューム ライセンスを含まない) については、次の表を参照してください。 Microsoft 365データの保持とサブスクリプションのライフサイクルの詳細については、「[ビジネス向けMicrosoft 365サブスクリプションが終了したときにデータとアクセスはどうなりますか?](https://learn.microsoft.com/ja-jp/microsoft-365/commerce/subscriptions/what-if-my-subscription-expires)」を参照してください。

| 製品の状態 | データ | データへのアクセス |
| --- | --- | --- |
| **アクティブ** (試用版の 30 日間) | すべてのユーザーがデータにアクセスできます。 | ユーザーはセルフサービス サインアップの製品、ファイル、アプリへの通常のアクセス権を持ちます。管理者は、Microsoft 365管理センターとリソースに通常アクセスできます。 |
| **削除済み** | データが削除されます。 | ユーザーはセルフサービス サインアップの製品、ファイル、アプリにアクセスできません。管理者は、Microsoft 365管理センターにアクセスして、他のサブスクリプションを購入および管理できます。 |

### セルフサービス サインアップ製品の削除

Microsoft Power BI や Azure RMS などのセルフサービス サインアップ製品を **Delete** 状態にして、Microsoft Entra管理センターですぐに削除することができます。

注

既定の初期ドメイン `contoso.onmicrosoft.com` を持つ Contoso 組織を削除する場合は、`admin@contoso.onmicrosoft.com` などの UPN を使用してサインインします。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **Microsoft Entra ID** を選択します。
3. **[ライセンス]** を選択し、 **[Self-service sign-up products](セルフサービス サインアップ製品)** を選択します。 シートベースのサブスクリプションとは別に、すべてのセルフサービス サインアップ製品を確認できます。 完全に削除する製品を選択します。 Microsoft Power BIの例を次に示します。

    [Image: セルフサービス サインアップ製品の一覧を示すスクリーンショット。]
4. **[削除]** を選択して、製品を削除します。 このアクションにより、すべてのユーザーが削除され、製品への組織のアクセスが削除されます。 製品の削除が直ちに元に戻せないという警告がダイアログに表示されます。 **[はい]** を選択して確定します。

    [Image: データの削除に関する警告を表示する確認ダイアログのスクリーンショット。]

    削除が進行中であることを通知します。

    [Image: 削除が進行中であることを示す通知のスクリーンショット。]
5. セルフサービス サインアップ製品の状態は **[削除済み] です**。 ページを最新の情報に更新すると、製品が **[セルフサービス サインアップ製品]** ページから削除されていることを確認します。

    [Image: セルフサービス サインアップ製品の一覧と、セルフサービス サインアップ製品の削除を確認するウィンドウを示すスクリーンショット。]
6. すべての製品を削除したら、もう一度Microsoft Entra管理センターにサインインします。 必須のアクションや製品によって、組織の削除がブロックされていないことを確認します。 Microsoft Entra組織を正常に削除できる必要があります。

    [Image: リソースの状態情報を示すスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/directory-overview-user-model"} -->
## Microsoft Entra ID のユーザー、グループ、ライセンス、ロール - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/directory-overview-user-model
- Service: entra-id / users
- Article date: 2025-01-31
- Summary: Microsoft Entra ID でのユーザー、割り当てられたライセンス、管理者ロール、動的メンバーシップ グループの間の関係

### 概要

この記事では、Microsoft Entra の一部である Microsoft Entra ID 管理者向けに、グループ、ライセンス、デプロイされているエンタープライズ アプリ、管理者の役割の観点から、ユーザーを対象とする主要な [ID 管理](https://learn.microsoft.com/ja-jp/entra/fundamentals/what-is-entra?context=azure/active-directory/users-groups-roles/context/ugr-context)タスク間の関係について説明します。 Microsoft Entra のグループと管理者ロールを使うことにより、組織の規模の拡大に伴って次のことを行えます。

- ライセンスを個人のユーザーに割り当てるのではなくライセンスをグループに割り当てる。
- 低い特権ロールを持つ担当者に Microsoft Entra 管理作業を委任するアクセス許可を付与する。
- エンタープライズ アプリのアクセス権をグループに割り当てる。

### ユーザーをグループに割り当てる

Microsoft Entra ID のグループを使うと、多数のユーザーにライセンスやデプロイされているエンタープライズ アプリを割り当てることができます。 グループを使用して、Microsoft Entra 全体管理者を除くすべての管理者ロールを割り当てたり、外部のリソース (SaaS アプリケーション、SharePoint サイトなど) へのアクセス権を付与したりすることができます。

Microsoft Entra ID の[動的メンバーシップ グループ](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule)を使用して、動的メンバーシップ グループを自動的に拡大および縮小できます。 動的グループを使用すると、柔軟性が向上し、動的メンバーシップ グループ管理作業が削減されます。

注

1 つ以上の動的メンバーシップ グループのメンバーになっている一意のユーザーには、それぞれに Microsoft Entra ID P1 ライセンスが必要です。

### ライセンスをグループに割り当てる

ユーザー ライセンスの割り当てを個別に管理すると、時間がかかり、エラーが発生しやすくなります。 代わりに[ライセンスをグループに割り当てる](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing?context=azure/active-directory/users-groups-roles/context/ugr-context)と、大規模なライセンス管理が簡単になります。

ライセンスを付与されているグループに参加している Microsoft Entra ユーザーは、適切なライセンスが自動的に割り当てられます。 ユーザーがグループから離脱すると、そのライセンスの割り当てが Microsoft Entra ID によって解除されます。 Microsoft Entra グループがない場合には、組織に参加するユーザーや組織から離脱するユーザーのライセンスを一括追加または一括削除するために、PowerShell スクリプトを作成するか、Graph API を使用する必要があります。 グループの一括操作の詳細については、「 [CSV ファイルをアップロードしてグループ メンバーを一括追加する」を](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-bulk-import-members)参照してください。

使用できるライセンスが不足している場合や、何らかの問題 (同時には割り当てることができないサービス プランなど) が生じた場合、グループのライセンスに関する問題の状態を Azure portal で確認できます。

### 管理者ロールを委任する

多くの大規模組織は、そのユーザー (アプリケーションを登録する必要のあるユーザーなど) に強力なグローバル管理者ロールを割り当てることなく、ユーザーそれぞれが仕事上のタスクを遂行するうえで十分なアクセス許可を入手する手段を求めています。 以下に示すのは、アプリケーション管理作業をより詳細に分散させるうえで役立つ、新しい Microsoft Entra 管理者の役割の例です。

| ロール名 | アクセス許可の概要 |
| --- | --- |
| **アプリケーション管理者** | エンタープライズ アプリケーションとアプリケーションの登録を追加して管理したり、プロキシ アプリケーションの設定を構成したりすることができます。 アプリケーション管理者は、条件付きアクセス ポリシーとデバイスを表示することはできますが、それらを管理することはできません。 |
| **クラウド アプリケーション管理者** | エンタープライズ アプリケーションとエンタープライズ アプリの登録を追加して管理することができます。 このロールには、アプリケーション管理者のすべてのアクセス許可が含まれます。ただし、アプリケーション プロキシの設定を管理することはできません。 |
| **アプリケーション開発者** | アプリケーションの登録を追加、更新できます。ただし、エンタープライズ アプリケーションを管理したり、アプリケーション プロキシを構成したりすることはできません。 |

新しい Microsoft Entra 管理者ロールは引き続き追加されます。 Azure portal または [管理者ロールのアクセス許可リファレンス](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) で、現在使用可能なロールを確認します。

### アプリへのアクセス権を割り当てる

Microsoft Entra ID を使用して、[Microsoft Entra 組織にデプロイされたエンタープライズ アプリ](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal?context=azure/active-directory/users-groups-roles/context/ugr-context)にグループ アクセスを割り当てることができます。 アプリへのグループ割り当てと動的メンバーシップ グループを組み合わせれば、組織が成長するときに、ユーザーに対するアプリ アクセス権の割り当てを自動化することができます。 エンタープライズ アプリへのアクセスを割り当てるには、Microsoft Entra ID P1 または Premium P2 ライセンスが必要です。

また、Microsoft Entra ID を使用すると、アクセス権の割り当て先となるグループとアプリの間を行き来するデータを具体的に制御できます。 [\[エンタープライズ アプリケーション\]](https://portal.azure.com/#blade/Microsoft_AAD_IAM/StartboardApplicationsMenuBlade/AllApps) でアプリを開き、 **[プロビジョニング]** を選択して次の作業を実施できます。

- 自動プロビジョニングをサポートするアプリに対してその設定を行う
- アプリのユーザー管理 API に接続するための資格情報を指定する
- ユーザー アカウントのプロビジョニング時または更新時に Microsoft Entra ID とアプリの間でやり取りされるユーザー属性を制御するマッピングを設定する
- アプリの Microsoft Entra プロビジョニング サービスを開始、停止したり、プロビジョニング キャッシュをクリアしたり、サービスを再開したりする
- **プロビジョニング アクティビティ レポート** (Microsoft Entra ID とアプリの間で作成、更新、削除されたすべてのユーザーとグループのログを確認できる) および**プロビジョニング エラー レポート** (より詳細なエラー メッセージを確認できる) を確認する
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/directory-self-service-signup"} -->
## 電子メール検証済みユーザーのセルフサービス サインアップ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/directory-self-service-signup
- Service: entra-id / users
- Article date: 2024-12-13
- Summary: Microsoft Entra 組織でセルフサービス サインアップを使用する

### 概要

この記事では、セルフサービス サインアップを使用して Microsoft Entra に含まれる Microsoft Entra ID に組織を設定する方法について説明します。 アンマネージド Microsoft Entra 組織からドメイン名を引き継ぐ場合は、[アンマネージド テナントを管理者として引き継ぐ方法](https://learn.microsoft.com/ja-jp/entra/identity/users/domains-admin-takeover)に関する記事をご覧ください。

Note

この記事では、Microsoft Entra ID組織の電子メール検証済みユーザーのセルフサービス サインアップについて説明します。 B2B コラボレーション ユーザーや顧客などの外部ユーザーのセルフサービス サインアップ エクスペリエンスについては、[Microsoft Entra 外部 IDでのセルフサービス サインアップに](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-overview)関するページを参照してください。

### セルフサービス サインアップを使用する理由

- 顧客が求めるサービスを迅速に提供できる。
- サービス提供の電子メール ベース プランを構築できる。
- ユーザーが覚えやすい職場の電子メールの別名を使用して ID をすばやく作成することを可能にする、電子メール ベースのサインアップ フローを作成できる。
- セルフサービスで作成された Microsoft Entra テナントを、他のサービスで使用できるマネージド テナントに変えることができる。

### 用語と定義

- **セルフサービス サインアップ** は、ユーザーがクラウド サービスにサインアップし、電子メール ドメインに基づいて Microsoft Entra ID で自動的に作成される ID を持つ方法です。
- **アンマネージド Microsoft Entra テナント**は、その ID が作成されるテナントです。 アンマネージド テナントとは、全体管理者がいないテナントのことです。
- **電子メールで確認されたユーザー**は、Microsoft Entra ID のユーザー アカウントの一種です。 セルフサービス プランへのサインアップ後に自動作成された ID を持つユーザーは、電子メール検証済みユーザーです。 電子メール検証済みユーザーは、creationmethod=EmailVerified でタグ付けされたテナントの通常メンバーです。

### セルフサービス設定を制御する

管理者は、セルフサービスの 2 種類の管理を実行できます。 次の点を管理できます。

- ユーザーが電子メール経由でテナントに参加できるかどうか
- ユーザー自身がアプリケーションやサービスのライセンスを取得できるかどうか

#### これらの機能を制御する

管理者は、次の Microsoft Entra コマンドレット `Update-MgPolicyAuthorizationPolicy` パラメーターを使用して、これらの機能を構成できます。

- `allowEmailVerifiedUsersToJoinOrganization` は、ユーザーが電子メールの検証によってテナントに参加できるかどうかを制御します。 参加するには、ユーザーは、テナント内の検証済みドメインのいずれかに一致するメール アドレスをドメイン内に持っている必要があります。 この設定は、テナント内のすべてのドメインに対して、会社全体に適用されます。 このパラメーターを $false に設定すると、電子メール検証済みのユーザーはテナントに参加できません。
- `allowedToSignUpEmailBasedSubscriptions` は、ユーザーがセルフサービス サインアップを実行する機能を制御します。 このパラメーターを $false に設定すると、ユーザーはセルフサービス サインアップを実行できません。

`allowEmailVerifiedUsersToJoinOrganization` および `allowedToSignUpEmailBasedSubscriptions` は、マネージド テナントまたはアンマネージド テナントに適用できるテナント全体の設定です。 次のような例を示します。

- contoso.com などの検証済みドメインを持つテナントを管理します。
- 別のテナントからの B2B コラボレーションを使用して、contoso.com のホーム テナントにまだ存在しない (userdoesnotexist@contoso.com) ユーザーを招待します。
- ホーム テナントでは `allowedToSignUpEmailBasedSubscriptions` はオンです。

上記の条件が当てはまる場合、メンバー ユーザーがホーム テナントに作成され、B2B ゲスト ユーザーが招待側のテナントに作成されます。

Note

Office 365 for Education ユーザーは、現在、この切り替えが有効になっている場合でも、既存のマネージド テナントに追加される唯一のユーザーです

Flow および Power Apps の試用版サインアップの詳細については、次の記事を参照してください。

- [既存のユーザーが Power BI の使用を開始できないようにするにはどうすればよいですか。](https://support.office.com/article/Power-BI-in-your-Organization-d7941332-8aec-4e5e-87e8-92073ce73dc5#bkmk_preventjoining)
- [組織におけるフローの Q&A](https://learn.microsoft.com/ja-jp/power-automate/organization-q-and-a)

#### これらの管理機能の連携について

これら 2 つのパラメーターを組み合わせて使用すると、セルフサービス サインアップをさらに細かく管理できるようになります。 たとえば、次のコマンドによりユーザーはセルフサービス サインアップを実行できますが、Microsoft Entra ID のアカウントを既に持っている場合に限定されます (つまり、まずメール検証済みのアカウントを作成する必要があるユーザーの場合、最初はセルフサービス サインアップを実行できません)。

```powershell
Import-Module Microsoft.Graph.Identity.SignIns
connect-MgGraph -Scopes "Policy.ReadWrite.Authorization"
$param = @{
 allowedToSignUpEmailBasedSubscriptions=$true
 allowEmailVerifiedUsersToJoinOrganization=$false
 }
Update-MgPolicyAuthorizationPolicy -BodyParameter $param
```

次のフローチャートは、これらのパラメーターのさまざまな組み合わせと、結果として得られるテナントとセルフサービス サインアップの状態を示しています。

[Image: セルフサービス サインアップ コントロールのフローチャート。]

この設定の詳細は、PowerShell コマンドレット `Get-MgPolicyAuthorizationPolicy`を使用して取得できます。 詳細については、「[Get-MgPolicyAuthorizationPolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mgpolicyauthorizationpolicy)」を参照してください。

```powershell
Get-MgPolicyAuthorizationPolicy | Select-Object AllowedToSignUpEmailBasedSubscriptions, AllowEmailVerifiedUsersToJoinOrganization
```

これらのパラメーターの使用方法についての詳細は、「 [Update-MgPolicyAuthorizationPolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/update-mgpolicyauthorizationpolicy?view=graph-powershell-1.0&preserve-view=true)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/directory-service-limits-restrictions"} -->
## サービスの制限と制約 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions
- Service: entra-id / users
- Article date: 2026-10-01
- Summary: Microsoft Entra サービスの使用上の制約およびその他のサービスの制限

### 概要

この記事では、Microsoft Entra の一部である Microsoft Entra ID の使用上の制約とその他のサービス制限について説明します。 Microsoft Azure サービスの制限すべてをご覧になりたい場合は、「 [Azure サブスクリプションとサービスの制限、クォータ、および制約](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/azure-subscription-service-limits)」を参照してください。

Microsoft Entra サービスの使用上の制約とその他のサービスの制限を次に示します。

| Category | Limit |
| --- | --- |
| Tenants | - 1 人のユーザーは、最大 500 の Microsoft Entra テナントにメンバーまたはゲストとして属することができます。<br>- 最大 200 個のテナントを作成します。 この制限は、従来のアドオン テナント作成エクスペリエンスと、Microsoft Entra 管理センターの **Governed Workforce** のセキュリティで保護されたアドオン テナント作成エクスペリエンスの両方に適用されます。<br>- テナントあたり 300 [ライセンス ベースのサブスクリプション](https://learn.microsoft.com/ja-jp/microsoft-365/commerce/licenses/subscriptions-and-licenses) (Microsoft 365 サブスクリプションなど) の制限。 |
| Domains | - 追加できるマネージド ドメイン名は 5,000 個以下です。<br>- オンプレミスの Active Directory とのフェデレーション用にすべてのドメインを設定する場合は、最適なパフォーマンスを得るために、各テナントで 300 個のドメイン名に制限することを強くお勧めします。 サポートされる最大ドメイン名は 2,500 個です。 |
| Resources | - 新しく作成されたテナントには、テナント作成後の最初の 2 日間に 600 個のディレクトリ オブジェクトの一時的なクォータ制限があります。 この制限は自動的に適用され、テナント管理者からのアクションは必要ありません。 2 日間が経過すると、クォータは自動的に標準の既定値 (検証済みドメインのないテナントの場合は 50,000 オブジェクト、検証済みドメインを持つテナントの場合は 300,000 オブジェクト) に自動的に復元されます。 最初の 2 日間に検証済みドメインを追加しても、すぐにクォータが増えるわけではありません。期間が終了した後に適用されるクォータ レベルが決定されます。 既存のテナントは、この一時的な制限の影響を受けられません。<br>- 既定で、Microsoft Entra ID Free エディションのユーザーは、1 つのテナントに最大 50,000 個の Microsoft Entra リソースを作成できます。 検証済みドメインが少なくとも 1 つある場合は、組織の既定の Microsoft Entra サービス クォータは 300,000 個の Microsoft Entra リソースに拡張されます。内部管理者の引き継ぎを実行し、少なくとも 1 つの確認済みドメインを持つマネージド テナントに組織が変換された後も、セルフサービス サインアップによって作成された組織の Microsoft Entra サービス クォータは、50,000 個の Microsoft Entra リソースのままになります。 このサービス制限は、Microsoft Entra の価格ページに記載されている 500,000 個のリソースの価格レベル制限とは関係ありません。既定のクォータを超えるためには、Microsoft サポートに連絡する必要があります。<br>- 管理者以外のユーザーは、最大 250 個の Microsoft Entra リソースを作成できます。 アクティブ リソースと復元可能な削除済みリソースの両方が、このクォータに加算されます。 論理的に削除されたディレクトリ オブジェクトは、復元に使用できる間も、ディレクトリ オブジェクトクォータの使用に引き続き貢献できます。 サポートされているオブジェクト型の論理的に削除されたオブジェクトの数を取得するには、「[Microsoft Graphを使用して deletedItems を一覧表示する](https://learn.microsoft.com/ja-jp/graph/api/directory-deleteditems-list?view=graph-rest-1.0)」を参照してください。 30 日未満に削除された削除済み Microsoft Entra リソースのみが復元可能です。 復元できなくなった削除済み Microsoft Entra リソースは、30 日間、4 分の 1 の値でこのクォータに加算されます。通常の作業で、このクォータを繰り返し超過する可能性のある開発者がいる場合は、無制限の数のアプリ登録を作成できる権限を持った[カスタムロールを作成して割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/quickstart-app-registration-limits)こともできます。<br>- リソースの制限は、ユーザー、グループ、アプリケーション、サービス プリンシパルなど、特定の Microsoft Entra テナント内のすべてのディレクトリ オブジェクトに適用されます。 |
| スキーマ拡張機能 | - 文字列型の拡張の最大文字数は 256 文字です。<br>- バイナリ型の拡張は 256 バイトに制限されます。<br>- 1 つの Microsoft Entra リソースに対して書き込める拡張値は、("すべて" の型と "すべて" のアプリケーションで合計) 100 個のみです。<br>- 文字列型またはバイナリ型の単一値の属性を使用して拡張できるのは、User、Group、TenantDetail、Device、Application、および ServicePrincipal エンティティのみです。<br>- DateTime 型の拡張機能では、"equals" 演算子のみがサポートされています。 "より大きい" や "より小さい" などの範囲演算子はサポートされていません。 |
| アプリ ロールと公開されているアクセス許可スコープ | アプリケーションまたはサービス プリンシパルごとに最大 700 個の既定のアクセス許可定義。アプリ ロール (`appRoles`) と公開された委任されたアクセス許可スコープ間で共有されます。 有効な定義と無効な定義の両方がカウントされます。 この制限は、1,200 エントリのアプリケーション マニフェストの制限と、アプリ ロールの割り当ての制限とは別です。 ルールのカウント、制限を超える既存のオブジェクトの動作、および設計ガイダンスについては、「 [アプリ ロールの制限](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-role-limits)」を参照してください。 |
| Applications | - 最大 100 人のユーザーおよびサービス プリンシパルが 1 つのアプリケーションの所有者になれます。<br>- ユーザー、グループ、またはサービス プリンシパルに割り当てることのできるアプリ ロールは 1,500 までです。 この制限は、すべてのアプリ ロールにわたる割り当て済みサービス プリンシパル、ユーザー、グループが対象です。1 つのアプリ ロールの割り当ての数に対するものではありません。 この制限には、リソース サービス プリンシパルが論理的に削除されたアプリ ロールの割り当てが含まれます。<br>- ユーザーは、パスワードベースのシングル サインオンを使用して、最大 48 個のアプリに対して資格情報を構成できます。 この制限は、ユーザーが割り当てられているグループのメンバーである場合ではなく、ユーザーがアプリに直接割り当てられているときに構成された資格情報にのみ適用されます。<br>- 1 つのグループでは、パスワードベースのシングル サインオンを使用して、最大 48 個のアプリに対して資格情報を構成できます。<br>- 追加の制限については、[サポートされているアカウントの種類別の検証の相違点](https://learn.microsoft.com/ja-jp/entra/identity-platform/supported-accounts-validation)に関するページをご覧ください。 |
| アプリケーション マニフェスト | アプリケーション マニフェストには、最大で 1200 のエントリを追加できます。追加の制限については、[サポートされているアカウントの種類別の検証の相違点](https://learn.microsoft.com/ja-jp/entra/identity-platform/supported-accounts-validation)に関するページをご覧ください。 |
| Groups | - 管理者以外のユーザーは、Microsoft Entra 組織に最大 250 個のグループを作成できます。 このクォータは、他の Entra リソースと共有されます。 組織内のグループを管理できる Microsoft Entra 管理者であれば、グループを無制限に作成することもできます (Microsoft Entra オブジェクトの上限まで)。 ユーザーにロールを割り当ててそのユーザーの制限を削除するには、ユーザー管理者やグループ管理者など、特権の低い組み込みロールを割り当ててください。<br>- Microsoft Entra 組織は、最大 15,000 個の動的グループ (Microsoft Entra エンタイトルメント管理の自動割り当てポリシーからのグループを含む) と動的管理単位を組み合わせることができます。<br>- 1 つの Microsoft Entra 組織 (テナント) には、最大 500 個の[ロール割り当て可能なグループ](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)を作成できます。<br>- 最大 100 人のユーザーが 1 つのグループの所有者になれます。<br>- [Entra Kerberos](https://learn.microsoft.com/ja-jp/troubleshoot/windows-server/windows-security/logging-on-user-account-fails)では、トークンあたり 1010 グループの制限があります。<br>- 任意の数の Microsoft Entra リソースが 1 つのグループのメンバーになることができます。<br>- ユーザー (またはグループ) が 2,048 を超えるグループ (直接および入れ子) のメンバーである場合、アクセスがブロックされる可能性があります。 この制限は、直接グループ メンバーシップと入れ子になったグループ メンバーシップの両方に適用されます。 [条件付きアクセスのユーザー、グループ、およびワークロード ID の割り当てを](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-users-groups)参照してください。<br>- ユーザーは任意の数のグループのメンバーになることができます。 認証または承認でグループ メンバーシップを使用する場合 (直接メンバーシップと間接メンバーシップを含む) には、次のサービス固有の制限が適用されます。<br>    - **SharePoint Online:** SharePoint Online でセキュリティ グループを使用する場合、ユーザーは、直接メンバーシップと間接メンバーシップを含め、合計で最大 2,047 のセキュリティ グループの一部にすることができます。 この制限を超えると、認証と検索結果が予測不能になる可能性があります。 [SharePoint の制限を](https://learn.microsoft.com/ja-jp/office365/servicedescriptions/sharepoint-online-service-description/sharepoint-online-limits)参照してください。<br>    - **SAML トークン:** オプションのグループ要求が有効になっている場合、最大 150 個のグループ メンバーシップ (入れ子になったグループを含む) が SAML アサーションに含まれます。 ユーザーが 150 を超えるグループに含まれている場合、グループ要求は省略され、代わりに超過分要求が送信されます。 Microsoft Entra ID では、ユーザーが 1,000 以下のグループ (ダイレクト メンバーシップや推移メンバーシップを含む) に属している場合にのみ、グループ のフィルター処理がサポートされます。 この制限を超えた場合、フィルター処理は適用されません。代わりに超過分の要求が送信されます。 実装ガイダンスについては、 [オプションの要求の構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims) と [SAML トークン要求のリファレンスを参照](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-saml-tokens#claims-in-saml-tokens) してください。<br>    - **JWT/OIDC トークン:** オプションのグループ要求が有効になっている場合、JWT トークンには最大 200 個のグループ メンバーシップ (入れ子になったグループを含む) が含まれます。 ユーザーが 200 を超えるグループに含まれている場合、グループ要求は省略され、代わりに超過分要求が送信されます。 実装ガイダンスについては、 [オプションの要求の構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims) と [アクセス トークン要求のリファレンスを参照](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-token-claims-reference#payload-claims) してください。<br>    - **条件付きアクセス ポリシー:** ポリシー評価では、ユーザーあたり最大 4,096 個のグループ メンバーシップ (直接および間接) がサポートされます。 この制限を超えると、ポリシーの適用が失敗する可能性があります。 [条件付きアクセスのユーザー、グループ、およびワークロード ID の割り当てを](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-users-groups)参照してください。<br>- Microsoft Entra Connect v2.0 以降、V2 エンドポイントが既定の API になりました。 Microsoft Entra Connect を使ってオンプレミスの Active Directory から Microsoft Entra ID に同期できるグループ内のメンバーの数は、250,000 ユーザーに制限されています。 詳細については、[Microsoft Entra Connect 同期 V2](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-endpoint-api-v2) に関するページを参照してください。<br>- グループのリストを選択すると、グループの有効期限ポリシーを最大 500 の Microsoft 365 グループに割り当てることができます。 ポリシーがすべての Microsoft 365 グループに適用される場合、制限はありません。<br><br>現時点では、入れ子になったグループでは次のシナリオがサポートされています。<br>- 1 つのグループを別のグループのメンバーとして追加することができるため、グループの入れ子を実現できます。<br>- グループ メンバーシップ クレーム アプリがトークンでグループ メンバーシップ クレームを受信するように構成されている場合は、サインインしているユーザーがメンバーになっている入れ子になったグループが含まれます。<br>- 条件付きアクセス (条件付きアクセス ポリシーにグループのスコープが設定されている場合)<br>- セルフサービスのパスワード リセットへのアクセスの制限。<br>- Microsoft Entra 参加とデバイス登録を実行できるユーザーの制限。<br><br>次のシナリオは、入れ子になったグループではサポート "されません"。<br>- アクセスとプロビジョニングの両方を対象としたアプリ ロールの割り当て。 アプリへのグループの割り当てはサポートされますが、直接割り当てられたグループ内に入れ子になったグループにはアクセスできません。<br>- グループベースのライセンス (グループのすべてのメンバーにライセンスを自動的に割り当てます)。<br>- Microsoft 365 グループ。 |
| アプリケーション プロキシ | - アプリケーション プロキシ アプリケーションごとに 1 秒あたり最大 500 件のトランザクション。<br>- Microsoft Entra 組織に対する 1 秒あたり最大 750 件のトランザクション。<br><br>\*トランザクションは、単一の HTTP 要求と一意のリソースの応答として定義されます。 クライアントは調整された場合、429 応答を受け取ることになります (要求が多すぎます)。 トランザクション メトリックは各コネクタで収集され、オブジェクト名 `Microsoft Entra private network connector` の下のパフォーマンス カウンターを使用して監視できます。 |
| アクセス パネル | 割り当てられたライセンス数に関係なく、アクセス パネルに表示できる、各ユーザーのアプリケーション数に制限はありません。 |
| Reports | いずれのレポートでも、最大 1,000 行を表示またはダウンロードできます。 それを超えるデータは切り捨てられます。 |
| 管理単位 | - Microsoft Entra リソースは最大 30 個の管理単位のメンバーにすることができます。<br>- テナント内の管理単位の数には特定の制限はありませんが、テナント内の制限付き管理単位は最大 100 個までです。<br>- Microsoft Entra 組織は、最大 15,000 個の動的グループ (Microsoft Entra エンタイトルメント管理の自動割り当てポリシーからのグループを含む) と動的管理単位を組み合わせることができます。 |
| Microsoft Entra のロールとアクセス許可 | - Microsoft Entra 組織には、最大 100 個の [Microsoft Entra カスタム ロール](https://learn.microsoft.com/ja-jp/azure/active-directory//users-groups-roles/roles-custom-overview?context=azure%2factive-directory%2fusers-groups-roles%2fcontext%2fugr-context)を作成できます。<br>- 任意のスコープで 1 つのプリンシパルに最大 150 個の Microsoft Entra カスタム ロール割り当て。<br>- テナント以外のスコープ (管理単位、Microsoft Entra オブジェクトなど) で 1 つのプリンシパルに最大 100 個の Microsoft Entra 組み込みロール割り当て。 テナント スコープでの Microsoft Entra 組み込みロール割り当てに制限はありません。 詳細については、「[Microsoft Entra ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)割り当てる」を参照してください。<br>- グループを[グループ所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions?context=azure/active-directory/users-groups-roles/context/ugr-context#object-ownership)として追加することはできません。<br>- ユーザーが他のユーザーのテナント情報を読み取る機能を制限するには、Microsoft Entra 組織全体のスイッチを使い、管理者以外の全ユーザーによるすべてのテナント情報へのアクセスを無効にする必要があります (これは推奨されません)。 詳細については、「[メンバー ユーザーの既定のアクセス許可を制限するには](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions?context=azure/active-directory/users-groups-roles/context/ugr-context#restrict-member-users-default-permissions)」を参照してください。<br>- 管理者ロールのメンバーシップの追加と失効が有効になるまでには、最大 15 分かかる場合もあれば、サインアウトしてから再度サインインすることが必要になる場合もあります。 |
| 条件付きアクセス ポリシー | 1 つのMicrosoft Entra組織 (テナント) に最大 240 個のポリシーを作成できます。 |
| 使用条件 | 1 つの Microsoft Entra 組織 (テナント) には 40 件以下の条件を追加できます。 |
| マルチテナント組織 | - 所有者テナントを含め、最大 100 のアクティブなテナント。 所有者テナントは 100 を超える保留中のテナントを追加できますが、制限を超えると、マルチテナント組織に参加できなくなります。 この制限は、保留中のテナントがマルチテナント組織に参加する時点で適用されます。<br>この制限は、マルチテナント組織内のテナント数に固有です。 テナント間同期自体には適用されません。 |
| Microsoft Entra テナントガバナンス | - **構成管理:**<br>    - 監視: 1 日に 800 個のリソース インスタンス。<br>    - 監視スケジュール: 6 時間ごと。<br>    - テナントあたりのモニターの最大数: 30。<br>    - スナップショット: 毎月 20,000 個のリソース インスタンス。<br>    - スナップショットの保持期間: 7 日間。<br>    - テナントあたりのスナップショットの最大数: 13。<br>    - テナントMicrosoft 365 E7、Microsoft Entra ID ガバナンス、またはMicrosoft Entra スイート ライセンスごとに、監視制限は毎日 10 リソース インスタンスずつ増加し、スナップショットの制限は毎月 35 リソース インスタンスずつ増加します。<br>- **関連するテナント:**<br>    - 関連するテナント検出は、テナントごとに 6 時間ごとに最大 1 回更新できます。<br>    - 検出シグナルは 1 日に 1 回集計されます。 変更が表示されるまでに最大 36 時間かかることがあります。<br>    - 関連するテナント API は、既定でページあたり最大 1,000 件の結果を返します。 `$top`を使用して、ページ サイズを小さくし、結果をページングする`@odata.nextLink`を要求します。<br>- **ガバナンス関係:** ガバナンス ポリシー テンプレートでは、テンプレートあたり最大 10 個のマルチテナント アプリケーション、マルチテナント アプリケーションあたり 100 のアクセス許可、およびテンプレートごとに 10 個のロールの割り当てがサポートされます。 詳細については、「 [ガバナンス ポリシー テンプレートの制限事項](https://learn.microsoft.com/ja-jp/entra/id-governance/tenant-governance/governance-policy-templates#limitations)」を参照してください。 |
| エージェント ID ブループリント | - 管理者以外のユーザーは、最大 250 個のエージェント ID ブループリントの作成に制限されます。 アクティブなブループリントと論理的に削除されたブループリントの両方がこのクォータに影響します。 このクォータは、他のMicrosoft Entra リソースと共有されます。<br>- ブループリントは、テナントの全体的なリソース クォータの 95% 以下を占めることができます ( **リソース**を参照)。 |
| エージェントアイデンティティ (エージェントID) | - テナントでは、Microsoftが所有していないプラットフォームによって管理される各エージェント ブループリントは、最大 250 個のエージェント ID に制限されます。 この制限は、Foundry や Copilot Studio などの Microsoft が所有するプラットフォームによって管理されるブループリントには適用されません。<br>- 管理者以外のユーザーの場合、エージェント ID の作成もエージェント ブループリントごとに 250 に制限されます。 アクティブなエージェント ID と論理的に削除されたエージェント ID の両方がこの制限にカウントされます。 このクォータは、他の Entra リソースと共有されます。<br>- エージェント ID は、テナントの全体的なリソース クォータの 95% 以下を占めることができます ( **リソース**を参照)。 |
| B2B の招待 | - **有料ライセンスを持つ従業員と外部テナント**<br>    - 30 日未満のテナント: 1 日あたり 200 件の招待<br>    - 30 日を超えるテナント: Microsoft Entra サービス クォータによって制限されます<br>- **有料ライセンスのない従業員と外部テナント**<br>    - 30 日未満のテナント: 1 日あたり 10 件の招待<br>    - 30 日を超えるテナント: 1 日あたり 100 件の招待 |
| テナント間アクセス ポリシー | - 各クロステナント アクセス ポリシー パートナー テナント関係に対応する [crossTenantAccessPolicyConfigurationPartner](https://learn.microsoft.com/ja-jp/graph/api/resources/crosstenantaccesspolicyconfigurationpartner) オブジェクトは、4 KB に制限されています。オーバーヘッドと JSON BLOB データの内容の両方を含む各 [crossTenantAccessPolicyConfigurationPartner](https://learn.microsoft.com/ja-jp/graph/api/resources/crosstenantaccesspolicyconfigurationpartner) オブジェクトは、この制限を超えてはなりません。 パートナー ポリシーを最適化するには、スコープに明示的なユーザー ID ではなくグループを使用することを検討してください。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/domains-admin-takeover"} -->
## 管理者による非管理対象ディレクトリの引き継ぎ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/domains-admin-takeover
- Service: entra-id / users
- Article date: 2026-06-18
- Summary: 管理されていない Microsoft Entra 組織 (シャドウ テナント) で DNS ドメイン名を引き継ぐ方法。

### 概要

この記事では、Microsoft Entra ID のアンマネージド ディレクトリにある DNS ドメイン名を引き継ぐための 2 つの方法について説明します。 セルフサービス ユーザーは、Microsoft Entra ID を使用するクラウド サービスにサインアップする際、ユーザーのメールのドメインに基づいてアンマネージド Microsoft Entra ディレクトリに追加されます。 サービスのセルフサービスまたは "バイラル" サインアップについては、「[Microsoft Entra ID のセルフサービス サインアップとは?](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-self-service-signup)」を参照してください

### 非管理対象ディレクトリを引き継ぐ方法を決定する

管理者引き継ぎのプロセスでは、「[Microsoft Entra ID へのカスタム ドメイン名の追加](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)」で説明されているように、所有権を証明できます。 次のセクションでは管理者エクスペリエンスを詳細に説明しますが、概要を以下に示します。

- アンマネージド ディレクトリの "内部" 管理者の引き継ぎ を実行すると、アンマネージド ディレクトリの [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールが割り当てられます。 管理しているその他のディレクトリには、ユーザー、ドメイン、またはサービス プランは移行されません。
- アンマネージド ディレクトリの "外部" 管理者の引き継ぎ を実行する場合は、管理されていないディレクトリの DNS ドメイン名をマネージド Azure ディレクトリに追加します。 ドメイン名を追加すると、ユーザーとリソースのマッピングがマネージド ディレクトリに作成されるため、ユーザーは中断することなくサービスに引き続きアクセスできます。

注

"内部" 管理者の引き継ぎには、アンマネージド ディレクトリに対する何らかのレベルのアクセス権が必要です。 引き継ぎしようとしているディレクトリにアクセスできない場合は、 "外部" 管理者の引き継ぎを実行する必要があります。

### 内部管理者の引き継ぎ

Microsoft 365 などの SharePoint と OneDrive を含む一部の製品では、外部引き継ぎがサポートされていません。 これが自分のシナリオである場合、または管理者であり、セルフサービス サインアップを使用したユーザーによって作成された管理されていない、または "シャドウ" な Microsoft Entra 組織を引き継ぐ場合は、内部管理者の引き継ぎでこれを行うことができます。

1. Power BI のサインアップを使って、管理されていない組織にユーザー コンテキストを作成します。 説明上の便宜のために、これらの手順ではそのパスを仮定します。
2. [Power BI サイト](https://powerbi.microsoft.com)を開き、 **[無料体験を開始する]** を選びます。 たとえば、`admin@fourthcoffee.xyz` のように、組織のドメイン名を使用しているユーザー アカウントを入力します。 認証コードを入力したら、電子メールで確認コードをチェックします。
3. Power BI からの確認の電子メールで、 **[Yes, that's me](https://learn.microsoft.com/ja-jp/entra/identity/users/はい、私です)** を選びます。
4. Power BI ユーザー アカウントで、[Microsoft 365 管理センター](https://portal.office.com/admintakeover)にサインインします。

    [Image: Microsoft 365 ウェルカム ページのスクリーンショット。]
5. 管理されていない組織で既に確認済みのドメイン名の **[管理者になる]** に誘導するメッセージを受信します。 **[Yes, I want to be the admin](https://learn.microsoft.com/ja-jp/entra/identity/users/はい、管理者になります)** を選択します。

    [Image: [管理者になる] のスクリーン ショット。]
6. TXT レコードを追加して、ドメイン名のレジストラーでドメイン名 **fourthcoffee.xyz** を所有していることを証明します。 この例では、それは GoDaddy.com です。

    [Image: ドメイン名の TXT レコードの追加を示すスクリーンショット。]

DNS TXT レコードがドメイン名 レジストラーで検証されると、Microsoft Entra 組織を管理できるようになります。

前述の手順を完了すると、Microsoft 365 の Fourth Coffee 組織のグローバル管理者になります。 お使いの他の Azure サービスとドメイン名を統合するには、ドメイン名を Microsoft 365 から削除して、Azure の別の管理対象組織に追加します。

#### Microsoft Entra ID でマネージド組織にドメイン名を追加する

1. [Microsoft 365 管理センター](https://admin.microsoft.com)を開きます。
2. [ **ユーザー** ] タブを選択し、カスタム ドメイン名を使用しない *user@fourthcoffeexyz.onmicrosoft.com* などの名前を持つ新しいユーザー アカウントを作成します。
3. 新しいユーザー アカウントに Microsoft Entra 組織のグローバル管理者特権が付与されていることを確認します。
4. Microsoft 365 管理センターで **[ドメイン]** タブを開き、ドメイン名を選択して **[削除]** を選択します。

    [Image: Microsoft 365 からドメイン名を削除するオプションを示すスクリーンショット。]
5. 削除されたドメイン名を参照しているユーザーまたはグループが Microsoft 365 にある場合、.onmicrosoft.com ドメインに名前を変更する必要があります。 ドメイン名を強制的に削除した場合、すべてのユーザーの名前が自動的に変更されます。この例では、*user@fourthcoffeexyz.onmicrosoft.com* になります。
6. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
7. ページの上部にある検索ボックスで、**ドメイン名**を検索します。
8. **[+ カスタム ドメイン名の追加]** を選択してから、ドメイン名を追加します。 ドメイン名の所有権を確認するには、DNS TXT レコードを入力する必要があります。

    [Image: Microsoft Entra ID に追加済みと確認されたドメインを示すスクリーンショット。]

注

Microsoft 365 組織に割り当てられているライセンスを所有する、Power BI または Azure Rights Management サービスのユーザーは、ドメイン名が削除された場合、ダッシュ ボードを保存しておく必要があります。 *user@fourthcoffeexyz.onmicrosoft.com* ではなく、*user@fourthcoffee.xyz* のようなユーザー名でサインインする必要があります。

### 外部管理者の管理権限の移譲

Azure サービスまたは Microsoft 365 を使用して既に組織を管理している場合で、カスタム ドメイン名が別の Microsoft Entra 組織で既に検証済みである場合は、それを追加することはできません。 しかし、Microsoft Entra ID のマネージド組織から、外部管理者引き継ぎとしてアンマネージド組織を引き継ぐことは可能です。 一般的な手順は、「[Microsoft Entra ID へのカスタム ドメイン名の追加](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)」の記事の通りです。

ユーザーがドメイン名の所有権を検証すると、Microsoft Entra ID は、アンマネージド組織からそのドメイン名を削除し、それを既存の組織に移動します。 非管理対象ディレクトリの外部管理者の引き継ぎには、内部管理者の引き継ぎと同じ DNS TXT の確認プロセスを行う必要があります。 相違点は、ドメイン名と共に次の内容も移行されることです。

- ユーザー
- サブスクリプション
- ライセンスの割り当て

#### 外部管理者の引き継ぎのサポート

外部管理者の引き継ぎは、次のオンライン サービスによってサポートされています。

- Azure Rights Management
- エクスチェンジ・オンライン

サポートされているサービス プランは次のとおりです。

- Power Apps 無料
- Power Automate 無料
- 個人向け RMS
- Microsoft Stream
- Dynamics 365 無料試用版

外部管理者の引き継ぎは、たとえば、Office の無償のサブスクリプションを通してなど、SharePoint、OneDrive、または Skype For Business を含むサービス プランを持つサービスに対してはサポートされません。

注

外部管理者の引き継ぎは、クラウド境界を越えてサポートされていません (Azure Commercial から Azure Government など)。 これらのシナリオでは、外部管理者による別の Azure Commercial テナントへの引き継ぎを実行し、移行先の Azure Government テナントに正常に検証できるように、このテナントからドメインを削除することをお勧めします。

##### 個人向け RMS の詳細

[個人向け RMS](https://learn.microsoft.com/ja-jp/azure/information-protection/rms-for-individuals) の場合、所有している組織と同じリージョンに管理されていない組織があるとき、自動的に作成された [Azure Information Protection 組織キー](https://learn.microsoft.com/ja-jp/azure/information-protection/plan-implement-tenant-key)と[既定の保護テンプレート](https://learn.microsoft.com/ja-jp/azure/information-protection/configure-usage-rights#rights-included-in-the-default-templates)もドメイン名と共に移動します。

アンマネージド組織が異なるリージョンにあるときは、キーとテンプレートは移行されません。 たとえば、管理されていない組織がヨーロッパにあり、所有している組織が北米にあるとします。

個人向け RMS は保護コンテンツを開くために Microsoft Entra 認証をサポートするように設計されていますが、ユーザーがコンテンツ保護も行うことを妨げません。 ユーザーが個人用 RMS サブスクリプションでコンテンツを保護し、キーとテンプレートが移行されていない場合は、ドメイン引き継ぎ後にそのコンテンツはアクセスできなくなります。

#### 外部管理者の引き継ぎのための PowerShell と Microsoft Graph API の手順

PowerShell の例で使用されているこれらのコマンドと API 呼び出しを確認できます。

| コマンドまたは API 呼び出し | 使用法 |
| --- | --- |
| `Connect-MgGraph` | メッセージが表示されたら、 `Domain.ReadWrite.All` スコープを使用してマネージド組織にサインインします。 委任されたアクセスの場合、サインインしているユーザーはサポートされているMicrosoft Entraロールを持っている必要があります。 [ドメイン名管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#domain-name-administrator) は、ドメイン検証でサポートされる最小特権ロールです。 |
| `Get-MgDomain` | 現在の組織に関連付けられているドメイン名を表示します。 |
| `New-MgDomain -BodyParameter @{Id="<your domain name>"; IsDefault="False"}` | "未確認" (DNS の確認がまだ実行されていない) として組織にドメイン名を追加します。 |
| `Get-MgDomain` | ドメイン名は、管理対象組織に関連付けられているドメイン名の一覧に含まれるようになりましたが、"**未確認**" として表示されます。 |
| `Get-MgDomainVerificationDnsRecord` | ドメインの新しい DNS TXT レコード (MS=xxxxx) に格納する情報を提供します。 TXT レコードが反映されるまでに時間がかかるため、すぐには検証が行われません。 |
| `Confirm-MgDomain -DomainId <domain name>` | 標準ドメイン検証シナリオの所有権を検証します。 現在の `Confirm-MgDomain` コマンドレットでは、 `-ForceTakeover` パラメーターは公開されません。 |
| `Invoke-MgGraphRequest` | Microsoft Graph ドメインを呼び出[します。](https://learn.microsoft.com/ja-jp/graph/api/domain-verify?view=graph-rest-1.0&preserve-view=true)API を直接検証します。 アンマネージド ドメインの外部管理者の引き継ぎの場合は、必要な TXT レコードを追加した後、API の `forceTakeover` 要求本文パラメーターを `true` に設定します。 |
| `Get-MgDomain` | ドメインの一覧に、ドメイン名が "**確認済み**" と表示されるようになりました。 |

注

管理されていないMicrosoft Entra組織は、`forceTakeover` オプションを使用して外部管理者の引き継ぎが成功してから 10 日後に削除されます。

#### PowerShell の例

1. 次のようにセルフサービス オファリングに対応するために使用された資格情報を使用して Microsoft Graph に接続します。

    ```powershell
    Install-Module -Name Microsoft.Graph
    
    Connect-MgGraph -Scopes "Domain.ReadWrite.All"
    ```
2. ドメインの一覧を取得してください。

    ```powershell
    Get-MgDomain
    ```
3. 次のように New-MgDomain コマンドレットを実行して、新しいドメインを追加します。

    ```powershell
    New-MgDomain -BodyParameter @{Id="<your domain name>"; IsDefault="False"}
    ```
4. 次のように Get-MgDomainVerificationDnsRecord コマンドレットを実行して、DNS チャレンジを表示します。

    ```powershell
    (Get-MgDomainVerificationDnsRecord -DomainId "<your domain name>" | ?{$_.recordtype -eq "Txt"}).AdditionalProperties.text
    ```

    次に例を示します。

    ```powershell
    (Get-MgDomainVerificationDnsRecord -DomainId "contoso.com" | ?{$_.recordtype -eq "Txt"}).AdditionalProperties.text
    ```
5. このコマンドから返される値 (チャレンジ) をコピーします。 次に例を示します。

    ```powershell
    MS=ms18939161
    ```
6. パブリック DNS 名前空間で、前の手順でコピーした値を含む DNS txt レコードを作成します。 このレコードの名前は親ドメインの名前です。このリソース レコードを Windows Server の DNS ロールを使用して作成する場合は、[レコード名] を空のままにして、テキスト ボックスに値を貼り付けます。
7. チャレンジを検証する方法を選択します。

    標準ドメイン検証の場合は、 [Confirm-MgDomain](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/confirm-mgdomain?view=graph-powershell-1.0&preserve-view=true) コマンドレットを実行します。

    ```powershell
    Confirm-MgDomain -DomainId "<your domain name>"
    ```

    次に例を示します。

    ```powershell
    Confirm-MgDomain -DomainId "contoso.com"
    ```

    アンマネージド ドメインの外部管理者の引き継ぎの場合は、[Invoke-MgGraphRequest](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/invoke-mggraphrequest?view=graph-powershell-1.0&preserve-view=true) を使用して Microsoft Graph [ドメイン](https://learn.microsoft.com/ja-jp/graph/api/domain-verify?view=graph-rest-1.0&preserve-view=true)を呼び出します。`forceTakeover`が `true` に設定されている API を確認します。

    ```powershell
    $body = @{
        forceTakeover = $true
    } | ConvertTo-Json
    
    Invoke-MgGraphRequest -Method POST `
        -Uri "https://graph.microsoft.com/v1.0/domains/<your domain name>/verify" `
        -Body $body `
        -ContentType "application/json"
    ```

    次に例を示します。

    ```powershell
    $body = @{
        forceTakeover = $true
    } | ConvertTo-Json
    
    Invoke-MgGraphRequest -Method POST `
        -Uri "https://graph.microsoft.com/v1.0/domains/contoso.com/verify" `
        -Body $body `
        -ContentType "application/json"
    ```

注

現在の `Confirm-MgDomain` コマンドレットでは、 `forceTakeover` 要求本文パラメーターは公開されません。 `Invoke-MgGraphRequest`を必要とする外部管理者の引き継ぎシナリオには、`forceTakeover`を使用します。

検証が成功すると、エラーなしで返されます。 `domain: verify` API は、応答本文でドメイン オブジェクトを返します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/domains-manage"} -->
## カスタム ドメイン名を追加して確認する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/domains-manage
- Service: entra-id / users
- Article date: 2026-04-07
- Summary: Microsoft Entra IDでドメイン名を管理するための管理の概念と方法

### 概要

ドメイン名は、多くのMicrosoft Entraデプロイのリソースの識別子の重要な部分です。 これは、ユーザーのユーザー名または電子メール アドレスの一部であり、グループのアドレスの一部であり、アプリケーションのアプリ ID URI の一部になることもあります。 Microsoft Entra IDのリソースには、リソースを含むMicrosoft Entra組織 (テナントとも呼ばれます) が所有するドメイン名を含めることができます。 [ドメイン名管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#domain-name-administrator)ロールは、Microsoft Entra IDのドメインを管理するために必要な最小限の特権ロールです。

### Microsoft Entra組織のプライマリ ドメイン名を設定する

組織が作成されると、"contoso.onmicrosoft.com" などの初期ドメイン名もプライマリ ドメイン名になります。 プライマリ ドメインは、新しいユーザーを作成したときにそのユーザーの既定のドメイン名になります。 プライマリ ドメイン名の設定によって、管理者がポータルでユーザーを新規作成するプロセスが効率化されます。 プライマリ ドメイン名を変更するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも[ドメイン名管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#domain-name-administrator)としてサインインします。
2. Entra IDドメイン名 に移動します
3. **[カスタム ドメイン名] を選択します**。

    [Image: ユーザー管理ページを開くスクリーンショット。]
4. プライマリ ドメインにするドメインの名前を選びます。
5. [プライマリにする] コマンド **を** 選択します。 メッセージが表示されたら、選択を確定します。

    [Image: ドメイン名をプライマリにするスクリーンショット。]

組織のプライマリ ドメイン名を変更して、フェデレーションされていない検証済みカスタム ドメインを指定することができます。 組織のプライマリ ドメインを変更しても、既存のユーザーのユーザー名は変更されません。

### Microsoft Entra組織にカスタム ドメイン名を追加する

マネージド ドメインの名前は最大 5,000 件追加できます。 すべてのドメインを オンプレミスの Active Directory とのフェデレーション用に構成している場合は、各組織で最大 2,500 個のドメイン名を追加できます。

### カスタム ドメインのサブドメインの追加

組織に europe.contoso.com などのサブドメイン名を追加する場合は、最初に、contoso.com などのルート ドメインを追加して、確認する必要があります。 Microsoft Entra IDは、サブドメインを自動的に検証します。 追加したサブドメインが検証されたことを確認するには、ブラウザーでドメインの一覧を更新します。

contoso.com ドメインを 1 つのMicrosoft Entra組織に既に追加している場合は、別のMicrosoft Entra組織でサブドメイン europe.contoso.com を確認することもできます。 サブドメインを追加するときに、ドメイン ネーム サーバー (DNS) ホスティング プロバイダーに TXT レコードを追加するように求められます。

### カスタム ドメイン名の DNS レジストラーを変更する場合にすべきこと

DNS レジストラーを変更した場合、Microsoft Entra IDには他の構成タスクはありません。 ドメイン名は、中断することなくMicrosoft Entra IDで引き続き使用できます。 Microsoft Entra IDのカスタム ドメイン名に依存するMicrosoft 365、Intune、またはその他のサービスでカスタム ドメイン名を使用する場合は、それらのサービスのドキュメントを参照してください。

### カスタム ドメイン名を削除する

組織がそのドメイン名を使用しなくなった場合、または別のMicrosoft Entra組織でそのドメイン名を使用する必要がある場合は、Microsoft Entra IDからカスタム ドメイン名を削除できます。

カスタム ドメイン名を削除する場合は、そのドメイン名を使用しているリソースが組織内にないことを事前に確認する必要があります。 次の状況に当てはまる場合、組織からドメイン名を削除することはできません。

- ユーザーのユーザー名、電子メール アドレス、またはプロキシ アドレスにドメイン名が含まれている。
- グループに付与された電子メール アドレスまたはプロキシ アドレスにドメイン名が含まれている。
- Microsoft Entra ID内のすべてのアプリケーションには、ドメイン名を含むアプリ ID URI があります。

カスタム ドメイン名を削除する前に、Microsoft Entra組織内のそのようなリソースを変更または削除する必要があります。

注

カスタム ドメインを削除するには、少なくとも既定のドメイン (onmicrosoft.com) または別のカスタム ドメイン (mydomainname.com) に基づくドメイン [名管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#domain-name-administrator) ロールを持つアカウントを使用します。

### 「ForceDelete」オプション

`ForceDelete`ドメイン名は、[Azure portal](https://portal.azure.com) または [Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/domain-forcedelete) で使用できます。 これらのオプションでは、非同期操作を使用し、"user@contoso.com" などのカスタム ドメイン名から最初の既定のドメイン名 ("user@contoso.onmicrosoft.com." など) へのすべての参照を更新します。

Azure ポータルで **ForceDelete** を呼び出すには、ドメイン名への参照が 1,000 未満であることを確認し、Exchange がプロビジョニング サービスとして使用されている場合、[Exchange Admin Center (EAC)](https://learn.microsoft.com/ja-jp/exchange/exchange-admin-center) で該当する参照を更新または削除する必要があります。 これには、Exchange Mail-Enabledセキュリティ グループと分散リストが含まれます。 詳細については、「[メールが有効なセキュリティ グループの削除](https://learn.microsoft.com/ja-jp/Exchange/recipients/mail-enabled-security-groups#Remove%20mail-enabled%20security%20groups)」を参照してください。 また、次のいずれかが当てはまる場合、 **ForceDelete** 操作は成功しません。

- ドメイン サブスクリプション サービスを使用してドメインMicrosoft 365購入した
- あなたは別のお客様組織の代理で管理を行うパートナーです

**ForceDelete** 操作の一部として、次のアクションが実行されます。

- カスタム ドメイン名を参照しているユーザーの UPN、EmailAddress、ProxyAddress の名前が既定の初期ドメイン名に変更されます。
- カスタム ドメイン名を参照しているグループの EmailAddress の名前が既定の初期ドメイン名に変更されます。
- カスタム ドメイン名を参照しているアプリケーションの identifierUris の名前が既定の初期ドメイン名に変更されます。
- Microsoft Entra 管理センターの ForceDelete オプションの影響を受けたユーザー アカウントを無効にします。また、Graph APIを使用する場合は必要に応じて無効にします。

次の場合はエラーが返されます。

- 名前を変更されるオブジェクトの数が 1,000 を超える
- 名前が変更されるアプリケーションの 1 つが、マルチテナント アプリである

### ドメインの検疫に関するベスト プラクティス

ドメイン名の変更、登録の有効期限、期限切れドメインの猶予期間に関する適切な通知を提供し、またドメイン名の構成と TXT レコードにアクセスできるユーザーを制御するために高いセキュリティ基準を維持する、信頼できるレジストラーを使用します。 ドメイン名をレジストラーで最新の状態に保ち、TXT レコードの精度を確認します。

- 意図的にドメイン名の有効期限が切れている場合、または (Microsoft Entra テナントとは別に) 他のユーザーに所有権を引き継ぐ場合は、期限切れまたは譲渡する前に、Microsoft Entra テナントから削除する必要があります。
- ドメイン名の有効期限が切れる場合、ドメイン名の再アクティブ化/制御の回復が可能な場合は、レジストラーですべての TXT レコードを慎重に確認して、ドメイン名の改ざんが行われないようにします。
- ドメイン名をすぐに再アクティブ化または制御を回復できない場合は、Microsoft Entra テナントからドメイン名を削除する必要があります。 ドメイン名の所有権を解決し、TXT レコード全体の正確性を確認できるようになるまで、読み取り/再検証は行いません。

注

Microsoftでは、複数のMicrosoft Entra テナントでドメイン名を検証することはできません。 テナントからドメイン名を削除すると、後で別のMicrosoft Entra テナントで追加および検証された場合、Microsoft Entra テナントでドメイン名を再追加または再検証することはできません。

### よく寄せられる質問

**Q: ドメイン名にExchangeで管理されているグループが含まれているというエラーで、ドメインの削除が失敗するのはなぜですか?** **A:** 現在、Mail-Enabled セキュリティ グループや分散リストなどの特定のグループはExchangeによってプロビジョニングされ、[Exchange 管理センター](https://learn.microsoft.com/ja-jp/exchange/exchange-admin-center)で手動でクリーンアップする必要があります。 カスタム ドメイン名に依存し、別のドメイン名に手動で更新する必要がある ProxyAddresses が残っている可能性があります。

**Q: admin@contoso.com としてログインしていますが、ドメイン名 "contoso.com" を削除できませんか?** **A：** ユーザー アカウント名で削除しようとしているカスタム ドメイン名を参照することはできません。 少なくとも [ドメイン名管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#domain-name-administrator) ロールを持つアカウントで、 admin@contoso.onmicrosoft.comなどの初期の既定のドメイン名 (.onmicrosoft.com) が使用されていることを確認します。 異なるアカウントでサインインします。このアカウントは[ドメイン名管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#domain-name-administrator)のロールを持っている必要があります。admin@contoso.onmicrosoft.com や、例えばアカウントが admin@fabrikam.com にある "fabrikam.com" などのカスタムドメイン名が考えられます。

**Q: [ドメインの削除] ボタンをクリックすると、`In Progress` という削除操作の状態が表示されます。 どのくらいの時間がかかりますか? 失敗するとどうなりますか。** **回答：** ドメインの削除操作は、ドメイン名に関連するすべての参照を変更する非同期バックグラウンド タスクです。 完了までに最大 24 時間かかる場合があります。 ドメインの削除が失敗する場合は、次のものがないことを確認してください。

- appIdentifierURI のドメイン名で構成されているアプリ
- カスタム ドメイン名を参照している、メール対応の任意のグループ
- ドメイン名に対する 1,000 個を超える参照
- 削除するドメインは、組織のプライマリ ドメインとして設定されます

また、ドメインでフェデレーション認証タイプが使用されている場合、ForceDelete オプションは機能しないので注意してください。 その場合、ドメインの削除を再試行する前に、オンプレミスの Active Directoryを使用してドメインのユーザー/グループの名前を変更または削除する必要があります。 どの条件も当てはまらないことがわかった場合は、手動で参照をクリーンアップし、もう一度ドメインの削除を試みてください。

### PowerShell または Microsoft Graph APIを使用してドメイン名を管理する

Microsoft Entra IDのドメイン名のほとんどの管理タスクは、Microsoft PowerShell を使用するか、Microsoft Graph APIを使用してプログラムで実行することもできます。

- Microsoft Entra ID
- [`Domain` リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/domain)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/domains-verify-custom-subdomain"} -->
## PowerShell と Graph を使用したサブドメイン認証の種類の変更 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/domains-verify-custom-subdomain
- Service: entra-id / users
- Article date: 2025-01-15
- Summary: Microsoft Entra ID のルート ドメイン設定から継承された既定のサブドメイン認証設定を変更します。

### 概要

ルート ドメインが Microsoft Entra ID の一部である Azure Active Directory (Azure AD) に追加されると、Microsoft Entra ID 組織内のそのルートに追加された後続のすべてのサブドメインは、ルート ドメインから認証設定を自動的に継承します。 ただし、ルート ドメイン設定とは別にドメイン認証設定を管理する場合は、Microsoft Graph API を使用してこれを行うことができます。 たとえば、フェデレーション ルート ドメイン (contoso.com など) がある場合、この記事を参照すると、child.contoso.com などのサブドメインをフェデレーションではなくマネージドとして検証するために役立てることができます。

Azure portal で、親ドメインがフェデレーションされていて、管理者が **[カスタム ドメイン名** ] ページでマネージド サブドメインの検証を試みると、"1 つ以上のプロパティに無効な値が含まれています" という理由で "ドメインの追加に失敗しました" というエラーが表示されます。Microsoft 365 管理センターからこのサブドメインを追加しようとすると、同様のエラーが表示されます。 エラーの詳細については、「 [子ドメインが Office 365、Azure、または Intune で親ドメインの変更を継承しない](https://learn.microsoft.com/ja-jp/microsoft-365/troubleshoot/administration/child-domain-fails-inherit-parent-domain-changes)」を参照してください。

サブドメインは既定でルート ドメインの認証の種類を継承するため、Microsoft Graph を使用してサブドメインを Microsoft Entra ID のルート ドメインに昇格させる必要があります。これにより、認証の種類を目的の種類に設定できます。

警告

この小さなスクリプトは、デモンストレーション用の例です。 環境内で使用する場合は、最初にテストします。 要件を満たすようにコードを調整する必要があります。

### サブドメインを追加する

注

次の例では、プレースホルダーとして `<your-root-domain>` を使用します。 これを、確認済みの独自のルート ドメイン名 (たとえば、 `contoso.com`) に置き換えます。

1. PowerShell を使用して、ルート ドメインの既定の認証の種類を持つ新しいサブドメインを追加します。 Microsoft Entra ID および Microsoft 365 管理センターでは、この操作はまだサポートされていません。

    ```powershell
    
    # Connect to Microsoft Graph with the required scopes
    Connect-MgGraph -Scopes "Domain.ReadWrite.All"
    
    # Define the parameters for the new domain
    $domainParams = @{
        Id = "child6.<your-root-domain>"
        AuthenticationType = "Federated"
    }
    
    # Create a new domain with the specified parameters
    New-MgDomain @domainParams
    
    ```
2. ドメインを取得するには、次の例を使用します。 ドメインはルート ドメインではないため、ルート ドメインの認証の種類を継承します。 コマンドと結果は次のようになります。独自のテナント ID を使用しています。

    注

    この要求の発行は [、Graph エクスプローラー](https://aka.ms/ge)で直接実行できます。

    ```http
    GET https://graph.microsoft.com/v1.0/domains/foo.contoso.com/
    
    Return:
      {
          "authenticationType": "Federated",
          "availabilityStatus": null,
          "isAdminManaged": true,
          "isDefault": false,
          "isDefaultForCloudRedirections": false,
          "isInitial": false,
          "isRoot": false,          <---------------- Not a root domain, so it inherits parent domain's authentication type (federated)
          "isVerified": true,
          "name": "child.<your-root-domain>",
          "supportedServices": [],
          "forceDeleteState": null,
          "state": null,
          "passwordValidityPeriodInDays": null,
          "passwordNotificationWindowInDays": null
      },
    ```

### サブドメインをルート ドメインに変更する

次のコマンドを使用して、サブドメインを昇格します。

```http
POST https://graph.microsoft.com/v1.0/{tenant-id}/domains/foo.contoso.com/promote
```

#### コマンド エラー条件の昇格

| シナリオ | メソッド | Code | メッセージ |
| --- | --- | --- | --- |
| 親ドメインが検証されていないサブドメインを使用して API を呼び出す | POST | 400 | 未検証のドメインは昇格できません。 昇格する前にドメインを確認します。 |
| フェデレーション検証済みサブドメインとユーザー参照を使用して API を呼び出す | POST | 400 | ユーザー参照を使用してサブドメインを昇格することはできません。 サブドメインを昇格する前に、ユーザーを現在のルート ドメインに移行します。 |

#### サブドメイン認証の種類をマネージドに変更する

重要

フェデレーション サブドメインの認証の種類を変更する場合は、次の手順を完了する前に、既存のフェデレーション構成値をメモしておく必要があります。 ドメインを昇格する前にフェデレーションを再実装する場合は、この情報が必要です。

1. 次のコマンドを使用して、サブドメインの認証の種類を変更します。

    ```powershell
    Connect-MGGraph -Scopes "Domain.ReadWrite.All", "Directory.AccessAsUser.All"
    Update-MgDomain -DomainId "test.contoso.com" -BodyParameter @{AuthenticationType="Managed"}
    ```
2. Microsoft Graph API で GET を使って、サブドメイン認証の種類がマネージドになったことを確認します。

    ```http
    GET https://graph.microsoft.com/v1.0/domains/foo.contoso.com/
    
    Return:
      {
          "authenticationType": "Managed",   <---------- Now this domain is successfully added as Managed and not inheriting Federated status
          "availabilityStatus": null,
          "isAdminManaged": true,
          "isDefault": false,
          "isDefaultForCloudRedirections": false,
          "isInitial": false,
          "isRoot": true,   <------------------------------ Also a root domain, so not inheriting from parent domain any longer
          "isVerified": true,
          "name": "child.<your-root-domain>",
          "supportedServices": [
              "Email",
              "OfficeCommunicationsOnline",
              "Intune"
          ],
          "forceDeleteState": null,
          "state": null,
          "passwordValidityPeriodInDays": null,
          "passwordNotificationWindowInDays": null }
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-assign-sensitivity-labels"} -->
## グループに秘密度ラベルを割り当てる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-assign-sensitivity-labels
- Service: entra-id / users
- Article date: 2026-04-03
- Summary: 秘密度ラベルをグループに割り当てる方法について説明します。 トラブルシューティング情報を参照し、その他のリソースを表示します。

### 概要

Microsoft Entra ID では、[秘密度](https://learn.microsoft.com/ja-jp/purview/sensitivity-labels)ラベルを Microsoft 365 グループに適用できます。これらのラベルが[Microsoft Purview ポータル](https://learn.microsoft.com/ja-jp/purview/purview-portal)で発行され、グループとサイト用に構成されている場合に限ります。

秘密度ラベルは、Outlook、Microsoft Teams、SharePoint などのアプリやサービス全体のグループに適用できます。 詳細については、Purview ドキュメントの[秘密度ラベルのサポート](https://learn.microsoft.com/ja-jp/purview/sensitivity-labels-teams-groups-sites#support-for-the-sensitivity-labels)に関するページを参照してください。

重要

この機能を構成するには、Microsoft Entra 組織に少なくとも 1 つのアクティブな Microsoft Entra ID P1 ライセンスが必要です。

### PowerShell でセンシティビティ・ラベルのサポートを有効にする

発行されたラベルをグループに適用するには、まずこの機能を有効にする必要があります。 次の手順では、Microsoft Entra ID の機能を有効にします。 Microsoft Graph PowerShell SDK には、`Microsoft.Graph` と `Microsoft.Graph.Beta`の 2 つのモジュールが含まれています。

Microsoft が運営するすべてのリージョンでは、Microsoft を選択する必要があります。 他のすべてのリージョンでは、演算子が一覧表示されている場合は、その演算子を選択する必要があります。

## [Microsoft](#tab/microsoft)
1. コンピューターで PowerShell プロンプトを開き、コマンドレットを実行するために必要な Graph モジュールをインストールします。

    ```powershell
    Install-Module Microsoft.Graph.Authentication -Scope CurrentUser
    Install-Module Microsoft.Graph.Beta.Identity.DirectoryManagement -Scope CurrentUser
    ```
2. テナントに接続します。

    ```powershell
    Connect-MgGraph -Scopes "Directory.ReadWrite.All"
    ```
3. Microsoft Entra 組織の現在のグループ設定をフェッチし、現在のグループ設定を表示します。

    ```powershell
    $grpUnifiedSetting = Get-MgBetaDirectorySetting | Where-Object { $_.Values.Name -eq "EnableMIPLabels" }
    $grpUnifiedSetting.Values
    ```

    この Microsoft Entra 組織のグループ設定が作成されていない場合は、空の画面が表示されます。 この場合は、最初に設定を作成する必要があります。 この Microsoft Entra 組織のグループ設定を作成 グループ設定を構成するには、Microsoft Entra コマンドレット の手順に従います。

    手記

    秘密度ラベルが以前に有効になっている場合は、`EnableMIPLabels = True`が表示されます。 この場合、何もする必要はありません。 また、管理者以外のユーザーがグループを作成できないようにする場合は、必ず `EnableGroupCreation = False` してください。 詳細については、「[テンプレート設定の](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-settings-cmdlets#template-settings)」を参照してください。
4. 新しい設定を適用します。

    ```powershell
    $params = @{
         Values = @(
     	    @{
     		    Name = "EnableMIPLabels"
     		    Value = "True"
     	    }
         )
    }
    
    Update-MgBetaDirectorySetting -DirectorySettingId $grpUnifiedSetting.Id -BodyParameter $params
    ```
5. 新しい値が存在することを確認します。

    ```powershell
    $Setting = Get-MgBetaDirectorySetting -DirectorySettingId $grpUnifiedSetting.Id
    $Setting.Values
    ```

`Request_BadRequest` エラーが発生した場合は、設定がテナントに既に存在するためです。 新しい `property:value` ペアを作成しようとすると、結果はエラーになります。 この場合は、次の手順に従います。

1. `Get-MgBetaDirectorySetting | FL` コマンドレットを発行し、ID を確認します。 複数の ID 値が存在する場合は、`EnableMIPLabels` 設定に  プロパティが表示される値を使用します。
2. 取得した ID を使用して、`Update-MgBetaDirectorySetting` コマンドレットを発行します。

また、秘密度ラベルを Microsoft Entra ID に同期する必要もあります。 手順については、「[コンテナーの秘密度ラベルを有効にして、ラベルを](https://learn.microsoft.com/ja-jp/purview/sensitivity-labels-teams-groups-sites#how-to-enable-sensitivity-labels-for-containers-and-synchronize-labels)同期する」を参照してください。

## [21Vianet](#tab/21Vianet)
21Vianet から次の Microsoft 365 操作を実行している場合:

1. Microsoft Entra ID アプリケーションを Microsoft Entra ID に登録します。
2. `Directory.ReadWriteAll`や`Group.ReadWriteAll`など、Microsoft Graph にアクセスするためのアプリケーション API のアクセス許可を付与します。アプリケーションに Microsoft Graph へのアクセス権を付与するには、テナント管理者の明示的な同意が必要になる場合があります。
3. クライアント シークレットを生成してコピーします。 Microsoft Graph に接続するには、クライアント シークレットが必要です。
4. 管理者として PowerShell を実行します。

    ```PowerShell
    $ClientSecretCredential = Get-Credential -Credential
    ```

    コマンドを実行すると、パスワードの入力を求められます。 パスワードは、前の手順でコピーした新しいクライアント シークレットです。
5. 次のコマンドを実行して、Microsoft Graph にアクセスします。

    ```PowerShell
    Connect-MgGraph -TenantId "Current tenant id" -ClientSecretCredential $ClientSecretCredential -Environment China
    ```
6. Microsoft Entra 組織の現在のグループ設定をフェッチし、現在のグループ設定を表示します。

    ```powershell
    $grpUnifiedSetting = Get-MgBetaDirectorySetting -Search DisplayName:"Group.Unified"
    ```

    この Microsoft Entra 組織のグループ設定が作成されていない場合は、空の画面が表示されます。 この場合は、最初に設定を作成する必要があります。 この Microsoft Entra 組織のグループ設定を作成 グループ設定を構成するには、Microsoft Entra コマンドレット の手順に従います。

    手記

    秘密度ラベルが以前に有効になっている場合は、`EnableMIPLabels = True`が表示されます。 この場合、何もする必要はありません。 また、管理者以外のユーザーがグループを作成できないようにする場合は、必ず `EnableGroupCreation = False` してください。 詳細については、「[テンプレート設定の](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-settings-cmdlets#template-settings)」を参照してください。
7. 新しい設定を適用します。

    ```powershell
    $params = @{
         Values = @(
     	    @{
     		    Name = "EnableMIPLabels"
     		    Value = "True"
     	    }
         )
    }
    
    Update-MgBetaDirectorySetting -DirectorySettingId $grpUnifiedSetting.Id -BodyParameter $params
    ```
8. 新しい値が存在することを確認します。

    ```powershell
    $Setting = Get-MgBetaDirectorySetting -DirectorySettingId $grpUnifiedSetting.Id
    $Setting.Values
    ```

`Request_BadRequest` エラーが発生した場合は、設定がテナントに既に存在するためです。 新しい `property:value` ペアを作成しようとすると、結果はエラーになります。 この場合は、次の手順に従います。

1. `Get-MgBetaDirectorySetting | FL` コマンドレットを発行し、ID を確認します。 複数の ID 値が存在する場合は、`EnableMIPLabels` 設定に  プロパティが表示される値を使用します。
2. 取得した ID を使用して、`Update-MgBetaDirectorySetting` コマンドレットを発行します。

また、秘密度ラベルを Microsoft Entra ID に同期する必要もあります。 手順については、「[コンテナーの秘密度ラベルを有効にして、ラベルを](https://learn.microsoft.com/ja-jp/purview/sensitivity-labels-teams-groups-sites#how-to-enable-sensitivity-labels-for-containers-and-synchronize-labels)同期する」を参照してください。

---

### Microsoft Entra 管理センターで新しいグループにラベルを割り当てる

1. 少なくとも [グループ管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) にサインインします。
2. **Microsoft Entra ID**を選択します。
3. [**グループ]**&gt;**[すべてのグループ]**&gt;**[新しいグループ**] を選択します。
4. **[新しいグループ]** ページで、**[Microsoft 365]** を選択します。 次に、新しいグループに必要な情報を入力し、一覧から秘密度ラベルを選択します。

    [Image: [新しいグループ] ページで秘密度ラベルを割り当てる方法を示すスクリーンショット。]
5. **[作成]** を選択して変更を保存します。

グループが作成され、選択したラベルに関連付けられているサイトとグループの設定が自動的に適用されます。

### Microsoft Entra 管理センターで既存のグループにラベルを割り当てる

1. 少なくとも [グループ管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) にサインインします。
2. **Microsoft Entra ID**を選択します。
3. **グループ**を選択します。
4. [**すべてのグループ**] ページから、ラベルを付けたいグループを選択します。
5. 選択したグループのページで、**[プロパティ]** を選択し、一覧から秘密度ラベルを選択します。

    [Image: グループの概要ページに秘密度ラベルを割り当てる方法を示すスクリーンショット。]
6. **[保存]** を選択して変更を保存します。

### Microsoft Entra 管理センターの既存のグループからラベルを削除する

1. 少なくとも [グループ管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) にサインインします。
2. **Microsoft Entra ID**を選択します。
3. **グループ**&gt;**すべてのグループ**を選択します。
4. **すべてのグループ** ページで、ラベルを削除するグループを選択します。
5. [**グループ**] ページで、[プロパティ **]**を選択します。
6. [**を選択し、**を削除します。
7. **[保存]** を選択して変更を適用します。

### 従来の Microsoft Entra 分類を使用する

この機能を有効にすると、グループの "クラシック" 分類は既存のグループとサイトにのみ表示されます。 秘密度ラベルをサポートしていないアプリでグループを作成する場合にのみ、新しいグループに使用する必要があります。 管理者は、必要に応じて後でそれらを機密ラベルに変換できます。 クラシック分類は、以前に設定した古い分類です。 この機能を有効にすると、それらの分類はグループに適用されません。

### 問題の対処法

このセクションでは、一般的な問題のトラブルシューティングのヒントを提供します。

#### 秘密度ラベルをグループでの割り当てに使用できない

秘密度ラベル オプションは、次のすべての条件が満たされている場合にのみグループに対して表示されます。

1. 組織には、アクティブな Microsoft Entra ID P1 ライセンスがあります。
2. この機能は有効になっており、 は Microsoft Graph PowerShell モジュールで true を に設定されています。
3. 秘密度ラベルは、Microsoft Purview ポータルまたはこの Microsoft Entra 組織用の Microsoft Purview ポータルで発行されます。
4. ラベルは、Security & Compliance PowerShell モジュールの `Execute-AzureAdLabelSync` コマンドレットを使用して Microsoft Entra ID に同期されます。 ラベルが Microsoft Entra ID で使用できるようになるには、同期後最大 24 時間かかる場合があります。
5. [秘密度ラベルのスコープ](https://learn.microsoft.com/ja-jp/purview/sensitivity-labels?preserve-view=true&view=o365-worldwide#label-scopes) は、グループとサイトに対して構成する必要があります。
6. グループは Microsoft 365 グループです。
7. 現在サインインしているユーザー:
    1. 秘密度ラベルを割り当てるための十分な特権があります。 ユーザーは、グループ所有者であるか、少なくともグループ管理者である必要があります。
    2. [のセンシティビティラベル発行ポリシー](https://learn.microsoft.com/ja-jp/purview/sensitivity-labels?preserve-view=true&view=o365-worldwide#what-label-policies-can-do)の範囲内にある必要があります。

グループにラベルを割り当てるために、上記のすべての条件が満たされていることを確認します。

#### 割り当てるラベルが一覧にない

探しているラベルが一覧にない場合:

- ラベルは Microsoft Purview ポータルで発行されない場合があります。 また、ラベルが発行されなくなる可能性もあります。 詳細については、管理者に問い合わせてください。
- ラベルは公開されている可能性がありますが、サインインしているユーザーは使用できません。 ラベルにアクセスする方法の詳細については、管理者に問い合わせてください。

#### グループのラベルを変更する

ラベルは、既存のグループにラベルを割り当てるのと同じ手順を使用して、いつでもスワップできます。

1. 少なくとも [グループ管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) にサインインします。
2. **Microsoft Entra ID**を選択します。
3. [**グループ]**&gt;**[すべてのグループ**] を選択し、ラベルを付けるグループを選択します。
4. 選択したグループのページで、**[プロパティ]** を選択し、リストから新しい感度ラベルを選択します。
5. **[保存]** を選択します。

#### 発行済みラベルに対するグループ設定の変更がグループで更新されない

[Microsoft Purview ポータル](https://purview.microsoft.com/)で発行済みラベルのグループ設定を変更しても、それらのポリシーの変更はラベル付きグループに自動的に適用されません。 秘密度ラベルを発行してグループに適用した後、ポータルでラベルのグループ設定を変更しないことをお勧めします。

変更する必要がある場合は、[PowerShell スクリプト](https://github.com/microsoftgraph/powershell-aad-samples/blob/master/ReassignSensitivityLabelToO365Groups.ps1) を使用して、影響を受けるグループに更新プログラムを手動で適用します。 このメソッドを使用すると、既存のすべてのグループで新しい設定が適用されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-bulk-download"} -->
## Azure portal でグループの一覧をダウンロードする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-bulk-download
- Service: entra-id / users
- Article date: 2025-12-05
- Summary: Microsoft Entra ID の Azure 管理センターでグループ プロパティを一括ダウンロードします。

### 概要

Microsoft Entra ID のポータルで、組織内のすべてのグループのリストをコンマ区切り値 (CSV) ファイルにダウンロードできます。 すべての管理者と非管理者ユーザーはグループ リストをダウンロードできます。

### グループの一括ダウンロード

組織内のすべてのグループをダウンロードするには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/#view/Microsoft_AAD_IAM/GroupsManagementMenuBlade)にサインインし、左側のナビゲーション ウィンドウで [**グループ**] タブを選択し、[**すべてのグループ**] を選択します。

    [Image: 列ヘッダーとアクションを含む [すべてのグループ] リストを示す Microsoft Entra 管理センターの [グループ] ブレードのスクリーンショット。]
2. [ **グループのダウンロード**] を選択します。

    [Image: ツールバーの [グループのダウンロード] ボタンが強調表示されている [グループ] ページのスクリーンショット。]
3. ファイル名を入力し、[ **一括操作の開始]** を選択します。

    [Image: 一括操作を開始する前にファイル名の入力を求める [グループのダウンロード] ダイアログのスクリーンショット。]
4. ジョブが送信されると**Success!**通知が表示されます。 通知には、「一括操作でのダウンロード グループの送信が成功しました」と表示されています。 詳細については、タイトルをクリックしてください。
5. **Success!** 通知タイトルを選択してジョブの詳細を開き、CSV ファイルをダウンロードするファイル名を選択します。 **ダウンロード成功**通知は、ファイルがダウンロードされたことを確認します。

ヒント

[ここをクリック] を選択すると、ダウンロード ダイアログ **の各操作の状態が表示** され、[ **一括操作の結果** ] ページに直接移動し、保留中および完了したすべての一括操作を監視できます。

#### ダウンロードした CSV ファイル形式

ダウンロードした CSV ファイルには、次のような各グループに関する情報が含まれています。

| コラム | Description |
| --- | --- |
| オブジェクト ID | グループの一意識別子 (GUID) |
| 表示名称 | グループの表示名 |
| 郵便 | グループに関連付けられている電子メール アドレス (該当する場合) |
| グループの種類 | セキュリティまたは Microsoft 365 |
| メンバーシップの種類 | 割り当て済みまたは動的 |

ヒント

ダウンロードした CSV ファイルを使用して、グループへのメンバーの追加など、他の一括操作に必要なオブジェクト ID を取得できます。

### フィルター処理されたグループをダウンロードする

フィルター処理されたグループのサブセットをダウンロードするには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/#view/Microsoft_AAD_IAM/GroupsManagementMenuBlade)にサインインし、左側のナビゲーション ウィンドウで [**グループ**] タブを選択します。
2. [ **フィルターの管理** ] を選択して列フィルターを編集します。
3. [ **グループのダウンロード**] を選択します。
4. 一括ダウンロード グループの手順 3 から 5 に従います。

注記

グループをフィルター処理する場合、フィルターの編集後に選択した列のみが CSV ファイルに表示されます。

エラーが発生する場合は、**[一括操作の結果]** ページで結果ファイルをダウンロードして確認できます。 このファイルには、各エラーの理由が含まれています。 ファイルの送信は、指定されたテンプレートと一致し、正確な列名が含まれている必要があります。 一括操作の制限の詳細については、「一括ダウンロード サービスの制限」を参照してください。

### ダウンロードの状態を確認する

保留中のすべての一括要求の状態は、[ **一括操作の結果** ] ページで確認できます。

[Image: [一括操作の結果] ページの [チェックの状態] オプションを示すスクリーンショット。]

### 一括ダウンロード サービスの制限

注記

インポートや作成などの一括操作を実行するときに、一括操作が 1 時間以内に完了しない場合、問題が発生する可能性があります。 この問題を回避するには、バッチごとに処理されるレコードの数を分割することをお勧めします。 たとえば、エクスポートの開始前に、グループの種類またはユーザー名でフィルター処理して結果セットを制限し、結果のサイズを小さくすることができます。 フィルターで絞り込むことは、実質的には一括操作によって返されるデータを制限することになります。 詳しくは、[一括操作サービスの制限](https://learn.microsoft.com/ja-jp/entra/fundamentals/bulk-operations-service-limitations)に関する記事をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-bulk-download-members"} -->
## グループ メンバーシップの一覧を一括でダウンロードする - Azure portal - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-bulk-download-members
- Service: entra-id / users
- Article date: 2026-06-18
- Summary: Microsoft Entra 管理センターでグループ メンバーを一括ダウンロードします。

### 概要

Microsoft Entra 管理センターから、組織のグループ メンバーをコンマ区切り値 (CSV) ファイルに一括ダウンロードできます。 グループ メンバーシップ リストは、管理者とそれ以外のユーザー全員がダウンロードできます。

### グループ メンバーを一括ダウンロードする

特定のグループのすべてのメンバーをダウンロードするには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/#view/Microsoft_AAD_IAM/GroupsManagementMenuBlade)にサインインし、左側のナビゲーション ウィンドウで [**グループ**] タブを選択します。
2. リストからグループを選択し、[ **メンバー** ] タブに移動します。

    [Image: 選択したグループの [メンバー] タブのスクリーンショット。ユーザーとサービス プリンシパルが一覧表示されています。]
3. [ **メンバー** ] ページのコマンド バーで、[ **メンバーのダウンロード**] を選択します。

    代わりに **一括操作** メニューが表示される場合は、 **一括操作**&gt;**メンバーのダウンロード**を選択します。
4. ファイル名を入力し、[ **一括操作の開始]** を選択します。
5. ジョブが送信されると、**Success!** の通知が表示されます。 通知には、「グループメンバーの一括ダウンロードの送信が成功しました」と表示されています。 詳細については、タイトルをクリックしてください。
6. **Success!** 通知タイトルを選択して、ジョブの詳細を開きます。 ファイル名を選択して、CSV ファイルのダウンロードを開始します。
7. **ダウンロード成功**通知は、ファイルがダウンロードされたことを確認します。 [通知] パネルの上部にある **監査ログで [その他のアクティビティ** ] を選択して、すべての一括操作アクティビティを表示することもできます。

ヒント

[ここをクリック] を選択すると、ダウンロード ダイアログ **の各操作の状態が表示** され、[ **一括操作の結果** ] ページに直接移動し、保留中および完了したすべての一括操作を監視できます。

#### ダウンロードした CSV ファイル形式

ダウンロードした CSV ファイルには、グループ メンバーごとに次の情報が含まれています。

| コラム | Description |
| --- | --- |
| オブジェクト ID | メンバーの一意の識別子 (GUID) |
| ユーザー プリンシパル名 | ユーザー メンバーの UPN (たとえば、 `user@contoso.com`) |
| 表示名称 | メンバーの表示名 |
| メンバーの種類 | メンバーがユーザー、グループ、またはサービス プリンシパルのいずれであるか |

ヒント

グループからメンバーを削除する必要がある場合は、ダウンロードした CSV ファイルを開始点として使用できます。 削除するメンバーのみを含むようにファイルを編集し、[ **メンバーの削除** ] 一括操作を使用してアップロードするだけです。

エラーが発生する場合は、**[一括操作の結果]** ページで結果ファイルをダウンロードして表示できます。 このファイルには、各エラーの理由が含まれています。

### ダウンロードの状態を確認する

保留中のすべての一括要求の状態は、[ **一括操作の結果** ] ページで確認できます。

1. **Identity**&gt;**ユーザー**&gt;**一括操作の結果**に移動します。
2. 一覧からダウンロード操作を見つけます。
3. **[状態]** **に [完了] と**表示されたら、ファイル名を選択して CSV ファイルをダウンロードします。

    [Image: [一括操作の結果] ページの [チェックの状態] オプションを示すスクリーンショット。]

### 一括ダウンロード サービスの制限

注

インポートや作成などの一括操作を実行するときに、一括操作が 1 時間以内に完了しない場合に問題が発生する可能性があります。 この問題を回避するには、バッチごとに処理されるレコードの数を分割することをお勧めします。 たとえば、エクスポートの開始前に、グループの種類またはユーザー名でフィルター処理して結果セットを制限し、結果のサイズを小さくすることができます。 フィルターを絞り込むことで、基本的に一括操作によって返されるデータを制限します。 詳しくは、[一括操作サービスの制限](https://learn.microsoft.com/ja-jp/entra/fundamentals/bulk-operations-service-limitations)に関する記事をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-bulk-import-members"} -->
## CSV ファイルをアップロードしてグループ メンバーを一括追加する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-bulk-import-members
- Service: entra-id / users
- Article date: 2026-06-18
- Summary: コンマ区切り値 (CSV) ファイルを使用して、グループ メンバーを一括で追加します。

### 概要

コンマ区切り値 (CSV) ファイルを使用して、Microsoft Entra ID のポータルにグループ メンバーを一括インポートすることで、複数のメンバーをグループに追加できます。

### CSV テンプレートについて

一括アップロード CSV テンプレートをダウンロードして入力し、Microsoft Entra グループ メンバーを正常に一括追加します。 グループ メンバーのインポート操作にダウンロードしたテンプレートを使用します。 現在のグループ メンバー テンプレートは、 `version:v1.0` 行ではなく、列ヘッダーで始まります。

#### CSV テンプレートの構造

ダウンロードされた CSV テンプレートの行は次のとおりです。

- **列見出し**: ダウンロードした列見出し全体を完全にそのまま保持してください。 角かっこ内のプロパティ名はヘッダーの 1 つの部分のみであるため、完全なヘッダーを `memberObjectIdOrUpn`のみに置き換えないでください。 現在のグループ メンバーのインポート ヘッダーが `Member object ID or user principal name [memberObjectIdOrUpn] Required`。 グループ メンバーシップの変更については、このヘッダーの下の行でメンバー オブジェクト ID またはユーザー プリンシパル名 (UPN) を使用できます。
- **[例] 行**: テンプレートに `Example: 9832aad8-e4fe-496b-a604-95c6eF01ae75`などの値の例の行が含まれている場合は、例の行を削除し、独自のエントリに置き換えます。

メモ

CSV テンプレートの形式は操作によって異なり、変更される可能性があります。 Microsoft Entra 管理センターから操作の最新のテンプレートをダウンロードします。 ダウンロードしたとおりに列ヘッダーを保持します。 テンプレートにバージョン行が含まれている場合は、保持します。 テンプレートにバージョン行が含まれていない場合は、追加しないでください。 操作固有の手順に従って、例の行を処理します。

#### その他のガイダンス

- ダウンロードしたとおりに列ヘッダーを保持します。 テンプレートにバージョン行が含まれている場合は、保持します。
- 必須の列が最初に示されています。
- テンプレートに新しい列を追加することはお勧めしません。 列を追加しても無視され、処理されません。
- できるだけ頻繁に最新バーションの CSV テンプレートをダウンロードすることをお勧めします。

- ファイルを正常にアップロードするには、少なくとも 2 人のユーザーの UPN またはオブジェクト ID を追加します。
- 行ごとに 1 つのメンバーを入力します。 セミコロンやその他の区切り記号を使用して、1 つの行に複数のメンバーを区切らないでください。

#### CSV ファイルの例

現在のグループ メンバー インポート テンプレート形式と一致する、アップロードの準備ができている完成した CSV ファイルの例を次に示します。 この例では、ユーザー プリンシパル名 (UPN) を使用します。

```csv
Member object ID or user principal name [memberObjectIdOrUpn] Required
alain@contoso.com
isabella@contoso.com
joseph@contoso.com
chaya@contoso.com
```

または、UPN の代わりにオブジェクト ID を使用することもできます。

```csv
Member object ID or user principal name [memberObjectIdOrUpn] Required
00aa00aa-bb11-cc22-dd33-44ee44ee44ee
11bb11bb-cc22-dd33-ee44-55ff55ff55ff
22cc22cc-dd33-ee44-ff55-66aa66aa66aa
```

ヒント

ユーザーのオブジェクト ID を検索するには、 **Identity**&gt;**Users**&gt;**All users** に移動し、ユーザーを選択し、ユーザーのプロファイル ページから **オブジェクト ID を** コピーします。

### グループ メンバーを一括インポートする

1. [グループ管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)にサインインします。
2. **Identity** に移動します。

    メモ

    グループの所有者も、所有しているグループのメンバーを一括インポートできます。
3. **[グループ]**&gt;**[すべてのグループ]** の順に選択します。
4. メンバーを追加するグループを開き、 **[メンバー]** を選択します。
5. [ **メンバー** ] ページで、[ **一括操作** ] を選択し、[ **メンバーのインポート**] を選択します。
6. **[グループ メンバーの一括インポート]** ページで、 **[ダウンロード]** を選択して、必要なグループ メンバーのプロパティを含む CSV ファイル テンプレートを取得します。

    [Image: [メンバーのインポート] コマンドがグループの [プロファイル] ページにあることを示すスクリーンショット。]
7. CSV ファイルを開き、グループにインポートするグループ メンバーごとに行を追加します。 メンバーごとに、 **ユーザー プリンシパル名** ( `user@contoso.com` などの UPN) または **オブジェクト ID** ( `aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb` などの GUID) を入力します。 行ごとに 1 つのメンバーを入力します。 そのうえでファイルを保存します。
8. **[グループ メンバーの一括インポート]** ページの **[CSV ファイルをアップロード]** で、そのファイルを参照します。 ファイルを選択すると、CSV ファイルの検証が開始されます。
9. ファイルの内容が検証されると、一括インポート ページに "**ファイルが正常にアップロードされました**" と表示されます。 エラーが存在する場合は、ジョブを送信する前にそれらを修正する必要があります。
10. ファイルが検証に合格したら、**[送信]** を選択して、グループ メンバーをグループにインポートする一括操作を開始してください。
11. インポート操作が完了すると、一括操作が成功したことを示す通知が表示されます。

エラーが発生する場合は、**[一括操作の結果]** ページで結果ファイルをダウンロードして表示できます。 このファイルには、各エラーの理由が含まれています。 ファイルの送信は、指定されたテンプレートと一致し、正確な列名が含まれている必要があります。

一括操作の制限の詳細については、「一括インポート サービスの制限」を参照してください。

### インポートの状態を確認する

保留中のすべての一括要求の状態は、[ **一括操作の結果** ] ページで確認できます。

1. **Identity**&gt;**ユーザー**&gt;**一括操作の結果**に移動します。
2. 一覧の中から一括操作を見つけてください。 **[状態]** 列には、操作が**進行中**か、**成功**したか、失敗したか**が表示されます**。

    [Image: [一括操作の結果] ページの [チェックの状態] オプションを示すスクリーンショット。]

一括操作に含まれる各行の項目の詳細については、 **[成功数]** 、 **[失敗数]** 、 **[要求数合計]** の各列の値を選択してください。 障害が発生した場合は、障害の理由がリストされます。

#### 結果ファイルをダウンロードする

詳細な結果をダウンロードするには:

1. [ **一括操作の結果** ] ページで、確認する操作を選択します。
2. [ **ダウンロード** ] を選択して、元のアップロードから各行の状態を含む CSV ファイルを取得します。
3. CSV ファイルを開き、正常に追加されたメンバーと失敗したメンバーと、特定のエラー メッセージを確認します。

一般的なエラーの理由は、次のとおりです。

- **メンバーが見つかりません**: UPN またはオブジェクト ID がディレクトリに存在しません。
- **メンバーは既に存在します**。ユーザーは既にグループのメンバーです。
- **無効な形式**: UPN またはオブジェクト ID の形式が正しくありません。

### 一括インポート サービスの制限

メモ

インポートや作成などの一括操作を実行するときに、一括操作が 1 時間以内に完了しない場合に問題が発生する可能性があります。 この問題を回避するには、バッチごとに処理されるレコードの数を分割することをお勧めします。 たとえば、エクスポートの開始前に、グループの種類またはユーザー名でフィルター処理して結果セットを制限し、結果のサイズを小さくすることができます。 フィルターを絞り込むことで、基本的に一括操作によって返されるデータを制限します。 詳しくは、[一括操作サービスの制限](https://learn.microsoft.com/ja-jp/entra/fundamentals/bulk-operations-service-limitations)に関する記事をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-bulk-remove-members"} -->
## CSV ファイルをアップロードしてグループ メンバーを一括削除する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-bulk-remove-members
- Service: entra-id / users
- Article date: 2026-06-18
- Summary: コンマ区切り値 (CSV) ファイルを使用して、一括操作でグループ メンバーを削除します。

### 概要

Microsoft Entra ID のポータルでコンマ区切り値 (CSV) ファイルを使用して、グループから多数のメンバーを削除できます。

### CSV テンプレートについて

一括アップロード CSV テンプレートをダウンロードして入力すると、Microsoft Entra グループメンバーが一括で正常に削除されます。 グループ メンバーの削除操作にダウンロードしたテンプレートを使用します。 現在のグループ メンバー テンプレートは、 `version:v1.0` 行ではなく、列ヘッダーで始まります。

#### CSV テンプレートの構造

ダウンロードされた CSV テンプレートの行は次のとおりです。

- **列見出し**: ダウンロードした列見出し全体を完全にそのまま保持してください。 角かっこ内のプロパティ名はヘッダーの 1 つの部分のみであるため、完全なヘッダーを `memberObjectIdOrUpn`のみに置き換えないでください。 現在のグループ メンバーの削除ヘッダーが `Member object ID or user principal name [memberObjectIdOrUpn] Required`。 グループ メンバーシップの変更については、このヘッダーの下の行でメンバー オブジェクト ID またはユーザー プリンシパル名 (UPN) を使用できます。
- **[例] 行**: テンプレートに `Example: 9832aad8-e4fe-496b-a604-95c6eF01ae75`などの値の例の行が含まれている場合は、例の行を削除し、独自のエントリに置き換えます。

注記

CSV テンプレートの形式は操作によって異なり、変更される可能性があります。 Microsoft Entra 管理センターから操作の最新のテンプレートをダウンロードします。 ダウンロードしたとおりに列ヘッダーを保持します。 テンプレートにバージョン行が含まれている場合は、保持します。 テンプレートにバージョン行が含まれていない場合は、追加しないでください。 操作固有の手順に従って、例の行を処理します。

#### その他のガイダンス

- ダウンロードしたとおりに列ヘッダーを保持します。 テンプレートにバージョン行が含まれている場合は、保持します。
- 必須の列が最初に示されています。
- テンプレートに新しい列を追加することはお勧めしません。 列を追加しても無視され、処理されません。
- できるだけ頻繁に最新バーションの CSV テンプレートをダウンロードすることをお勧めします。

- 行ごとに 1 つのメンバーを入力します。 セミコロンやその他の区切り記号を使用して、1 つの行に複数のメンバーを区切らないでください。

#### CSV ファイルの例

アップロードの準備ができている完成した CSV ファイルの例を次に示します。

```csv
Member object ID or user principal name [memberObjectIdOrUpn] Required
alain@contoso.com
isabella@contoso.com
joseph@contoso.com
```

ヒント

編集できる現在のグループ メンバーの一覧を取得するには、最初に **メンバーの一括ダウンロード** 操作を使用します。 これにより、削除するメンバーのみを含むように変更できる、現在のすべてのメンバーを含む CSV ファイルが提供されます。

### グループ メンバーを一括削除する

1. [グループ管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)にサインインします。
2. **[Entra ID]**&gt;**[グループ]**&gt;**[すべてのグループ]** に移動します。
3. メンバーを削除するグループを開き、 **[メンバー]** を選択します。
4. **[メンバー]** ページで、 **[メンバーの削除]** を選択します。
5. **[グループ メンバーの一括削除]** ページで、 **[ダウンロード]** を選択して、必要なグループ メンバー プロパティを含む CSV ファイル テンプレートを取得します。

    [Image: [メンバーの削除] コマンドがグループの [プロファイル] ページにあることを示すスクリーンショット。]
6. CSV ファイルを開き、グループから削除するグループ メンバーごとに行を追加します。 メンバーごとに、 **ユーザー プリンシパル名** ( `user@contoso.com` などの UPN) または **オブジェクト ID** ( `aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb` などの GUID) を入力します。 行ごとに 1 つのメンバーを入力します。 そのうえでファイルを保存します。
7. **[グループ メンバーの一括削除]** ページの **[csv ファイルをアップロードします]** で、そのファイルを参照します。 ファイルを選択すると、CSV ファイルの検証が開始されます。
8. ファイルの内容が検証されると、一括削除ページに **正常にアップロードされたファイルが**表示されます。 エラーが存在する場合は、ジョブを送信する前にそれらを修正する必要があります。
9. ファイルが検証に合格したら、**[送信]** を選択して、グループ メンバーをグループから削除する一括操作を開始してください。
10. 削除操作が完了すると、一括操作が成功したことを示す通知が表示されます。

エラーが発生する場合は、**[一括操作の結果]** ページで結果ファイルをダウンロードして確認できます。 このファイルには、各エラーの理由が含まれています。 ファイルの送信は、指定されたテンプレートと一致し、正確な列名が含まれている必要があります。

一括操作の制限の詳細については、「一括削除サービスの制限」を参照してください。

### 削除の状態を確認する

保留中のすべての一括要求の状態は、[ **一括操作の結果** ] ページで確認できます。

[Image: [一括操作の結果] ページの [チェックの状態] オプションを示すスクリーンショット。]

一括操作に含まれる各行の項目の詳細については、 **[成功数]** 、 **[失敗数]** 、 **[要求数合計]** の各列の値を選択してください。 障害が発生した場合は、障害の理由がリストされます。

### 一括削除サービスの制限

注記

インポートや作成などの一括操作を実行するときに、一括操作が 1 時間以内に完了しない場合、問題が発生する可能性があります。 この問題を回避するには、バッチごとに処理されるレコードの数を分割することをお勧めします。 たとえば、エクスポートの開始前に、グループの種類またはユーザー名でフィルター処理して結果セットを制限し、結果のサイズを小さくすることができます。 フィルターで絞り込むことは、実質的には一括操作によって返されるデータを制限することになります。 詳しくは、[一括操作サービスの制限](https://learn.microsoft.com/ja-jp/entra/fundamentals/bulk-operations-service-limitations)に関する記事をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-change-type"} -->
## 静的グループを動的メンバーシップ グループに変更する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-change-type
- Service: entra-id / users
- Article date: 2025-07-10
- Summary: Azure ポータルまたは PowerShell コマンドレットを使用して、既存のメンバーシップ グループを静的から動的に変換する方法について説明します。

### 概要

Microsoft Entra IDでは、グループのメンバーシップを静的から動的 (またはその逆) に変更できます。 Microsoft Entra IDは同じグループ名と ID をシステムに保持するため、グループへの既存のすべての参照は引き続き有効です。 代わりに新しいグループを作成する場合は、それらの参照を更新する必要があります。

動的メンバーシップ グループを作成すると、ユーザーの追加と削除の管理オーバーヘッドがなくなります。 この記事では、Azure ポータルまたは PowerShell コマンドレットを使用して、既存のメンバーシップ グループを静的から動的に変換する方法について説明します。 Microsoft Entraでは、1 つのテナントに最大 15,000 個の動的メンバーシップ グループを含めることができます。

注

静的グループを動的メンバーシップ グループに変換すると、メンバーシップ ルールを満たす既存のメンバーが残ります。 条件を満たしていないメンバーは削除されます。 メンバーシップ ルールを満たす他のユーザーは自動的に追加されます。 グループを使用してアプリまたはリソースへのアクセスを制御すると、メンバーシップ ルールが完全に処理されるまで、元のメンバーがアクセスできなくなる可能性があります。

新しいメンバーシップ ルールを事前にテストして、グループ内の新しいメンバーシップが想定どおりになっていることを確認します。 テスト中にエラーが発生した場合は、「 [グループベースのライセンス エラーの管理」を](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true#manage-group-based-licensing-errors)参照してください。

### [前提条件]

- ポータルを使用してメンバーシップの種類を変更するには、少なくとも [グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) ロールを持つアカウントが必要です。
- PowerShell を使用して動的グループのプロパティを変更するには、Microsoft Graph PowerShell モジュールのコマンドレットを使用する必要があります。 詳細については、「[install the Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)」を参照してください。

### グループのメンバーシップの種類を変更する (ポータル)

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくともグループ管理者としてサインインします。
2. **Microsoft Entra ID** を選択します。
3. **[グループ]** を選びます。
4. [ **すべてのグループ** ] 一覧で、変更するグループを開きます。
5. **プロパティ**を選択します。
6. グループの **[プロパティ** ] ページで、目的の **メンバーシップの種類** に応じて、 **割り当て済み (静的)**、 **動的ユーザー**、または **動的デバイス**のメンバーシップの種類の値を選択します。 動的メンバーシップ グループの場合は、ルール ビルダーを使用して簡単なルールのオプションを選択したり、メンバーシップ ルールを自分で作成したりすることができます。

    次の手順は、ユーザーのグループを静的メンバーシップ グループから動的メンバーシップ グループに変更する例です。

    1. [ **メンバーシップの種類**] で、[ **動的ユーザー**] を選択します。 動的メンバーシップ グループの変更を説明するダイアログで、[ **はい** ] を選択して続行します。

        [Image: 動的ユーザーのメンバーシップの種類を選択するスクリーンショット。]
    2. **[動的クエリの追加]** を選び、ルールを指定します。

        [Image: 動的グループのルールを入力するスクリーンショット。]
7. ルールを作成したら、[クエリの **追加]** を選択します。
8. グループの **[プロパティ** ] ページで、[ **保存]** を選択して変更を保存します。 グループの一覧でグループの **[メンバーシップの種類]** がすぐに更新されます。

ヒント

入力したメンバーシップ ルールが正しくない場合、グループ変換が失敗する可能性があります。 ポータルの右上隅に、ルールを受け入れられない理由が通知で示されます。 それをよく読み、有効にするためにできる調整の方法を理解してください。 ルール構文の例と、メンバーシップ ルールでサポートされているプロパティ、演算子、値の完全な一覧については、「Microsoft Entra ID の動的メンバーシップ グループの管理ルール」を参照してください。

### グループのメンバーシップの種類を変更する (PowerShell)

既存のグループのメンバーシップ管理を切り替える関数の例を次に示します。 この例では、動的メンバーシップ グループに関係のない値を保持するために、 `GroupTypes` プロパティを正しく操作します。

```powershell
#The moniker for dynamic membership groups, as used in the GroupTypes property of a group object
$dynamicGroupTypeString = "DynamicMembership"

function ConvertDynamicGroupToStatic
{
    Param([string]$groupId)

    #Existing group types
    [System.Collections.ArrayList]$groupTypes = (Get-MgGroup -GroupId $groupId).GroupTypes

    if($groupTypes -eq $null -or !$groupTypes.Contains($dynamicGroupTypeString))
    {
        throw "This group is already a static group. Aborting conversion.";
    }

    #Remove the type for dynamic membership groups, but keep the other type values
    $groupTypes.Remove($dynamicGroupTypeString)

    #Modify the group properties to make it a static group: change GroupTypes to remove the dynamic type, and then pause execution of the current rule
    Update-MgGroup -GroupId $groupId -GroupTypes $groupTypes.ToArray() -MembershipRuleProcessingState "Paused"
}

function ConvertStaticGroupToDynamic
{
    Param([string]$groupId, [string]$dynamicMembershipRule)

    #Existing group types
    [System.Collections.ArrayList]$groupTypes = (Get-MgGroup -GroupId $groupId).GroupTypes

    if($groupTypes -ne $null -and $groupTypes.Contains($dynamicGroupTypeString))
    {
        throw "This group is already a dynamic group. Aborting conversion.";
    }
    #Add the dynamic group type to existing types
    $groupTypes.Add($dynamicGroupTypeString)

    #Modify the group properties to make it a static group: change GroupTypes to add the dynamic type, start execution of the rule, and then set the rule
    Update-MgGroup -GroupId $groupId -GroupTypes $groupTypes.ToArray() -MembershipRuleProcessingState "On" -MembershipRule $dynamicMembershipRule
}
```

グループを静的にするには、次のコマンドを使用します。

```powershell
ConvertDynamicGroupToStatic "a58913b2-eee4-44f9-beb2-e381c375058f"
```

グループを動的にするには、次のコマンドを使用します。

```powershell
ConvertStaticGroupToDynamic "a58913b2-eee4-44f9-beb2-e381c375058f" "user.displayName -startsWith ""Peter"""
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-create-rule"} -->
## 動的メンバーシップ グループを作成または編集し、その処理状態を取得する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule
- Service: entra-id / users
- Article date: 2024-12-19
- Summary: Azure portal で動的メンバーシップ グループのルールを作成または更新し、その処理状態を確認する方法について説明します。

### 概要

ルールを使用して、Microsoft Entra ID のユーザーまたはデバイスのプロパティに基づいて動的メンバーシップ グループを決定できます。 この記事では、Azure portal で動的メンバーシップ グループのルールを設定する方法について説明します。

ユーザーまたはデバイスのプロパティに基づくグループ メンバーシップは、セキュリティ グループと Microsoft 365 グループでサポートされています。 動的メンバーシップ グループのルールが適用されるときに、ユーザーとデバイスの属性はメンバーシップ ルールとの一致について評価されます。 ユーザーまたはデバイスの属性が変更されると、組織内のすべての動的メンバーシップ グループのルールが、変更のために処理されます。 ユーザーとデバイスは、動的メンバーシップ グループの条件を満たす場合、追加または削除されます。 Microsoft Entra ID では、1 つのテナントに最大 15,000 個の動的メンバーシップ グループを含めることができます。

注

セキュリティ グループにはデバイスまたはユーザーを含めることができますが、Microsoft 365 グループにはユーザーのみを含めることができます。

動的メンバーシップ グループを使用するには、Microsoft Entra ID P1 ライセンスまたは Intune for Education ライセンスが必要です。 詳細については、「 [Microsoft Entra ID での動的メンバーシップ グループのルールの管理」を](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership)参照してください。

注

動的メンバーシップ グループルールは、テナント内のすべてのユーザーとデバイス (非アクティブまたは使用されなくなったオブジェクトを含む) を評価します。 古いデバイスと古いユーザーは、グループ メンバーシップが拡張され、動的メンバーシップ グループの処理が遅くなる可能性があります。 ルールを作成する前に、 [古いデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-stale-devices) と [非アクティブなユーザー](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-manage-inactive-user-accounts) をクリーンアップして、管理するオブジェクトのみがグループに反映されるようにします。

### Azure portal のルール ビルダー

Microsoft Entra ID には、重要なルールをすばやく作成したり更新したりできるルール ビルダーが用意されています。 ルール ビルダーでは、最大で 5 つの式の作成がサポートされます。

[Image: ルール ビルダーを示すスクリーンショット。式を追加するためのアクションが強調表示されています。]

ルール ビルダーを使用すると、いくつかの単純な式を使用してルールを簡単に作成できます。 ただし、すべてのルールを再現するために使用することはできません。 ルール ビルダーが作成するルールをサポートしていない場合は、テキスト ボックスを使用できます。

テキスト ボックスを必要とする高度な規則または構文の例を次に示します。

- 5 つを超える式を持つルール
- 直属の部下に関する規則
- [演算子の優先順位](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership#operator-precedence)の設定
- [複雑な式を含むルール](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership#rules-with-complex-expressions)。例えば `(user.proxyAddresses -any (_ -contains "contoso"))`

注

ルール ビルダーは、テキスト ボックスで作成された一部のルールを表示できない場合があります。 ルール ビルダーでルールを表示できない場合に、メッセージが表示されることがあります。 ルール ビルダーは、動的メンバーシップ グループのルールのサポートされている構文、検証、または処理をどのような方法でも変更しません。

メンバーシップ ルールの構文とサポートされているプロパティ、演算子、値の例については、「 [Microsoft Entra ID での動的メンバーシップ グループのルールの管理」を](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership)参照してください。

### 動的メンバーシップ グループのルールを作成する

1. [グループ管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)にサインインします。
2. **Microsoft Entra ID**&gt;**Groups** を選択します。
3. [ **すべてのグループ**] を選択し、[ **新しいグループ**] を選択します。

    [Image: 新しいグループを追加するための選択を示すスクリーンショット。]
4. [ **グループ** ] ウィンドウで、新しいグループの名前と説明を入力します。 ユーザーまたはデバイスの **メンバーシップの種類** の値を選択し、[ **動的クエリの追加]** を選択します。
5. ルール ビルダーで、最大 5 つの式を追加します。 5 つを超える式を追加するには、テキスト ボックスを使用する必要があります。

    [Image: ルールを構成するためのペインを示すスクリーンショット。式を追加するためのボタンが強調表示されています。]
6. メンバーシップ クエリで使用できるカスタム拡張機能プロパティを表示するには:

    1. [ **カスタム拡張機能のプロパティを取得する**] を選択します。
    2. アプリケーション ID を入力し、 **[プロパティの更新]** を選択します。
7. ルールの作成が完了したら、[ **保存]** を選択します。
8. [ **新しいグループ** ] ページで、[ **作成** ] を選択してグループを作成します。

入力したルールが有効でない場合は、ルールを処理できなかった理由の説明がポータルに表示されます。 ルールを修正する方法を理解するには、こちらを注意深くお読みください。

### 既存のルールを更新する

1. [グループ管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)にサインインします。
2. **[Microsoft Entra ID]** を選びます。
3. **[グループ]**&gt;**[すべてのグループ]** の順に選択します。
4. グループを選択して、そのプロファイルを開きます。
5. グループのプロファイル ページで、 **[動的メンバーシップ ルール]** を選択します。 ルール ビルダーでは、最大で 5 つの式がサポートされます。 5 つを超える式を追加するには、テキスト ボックスを使用する必要があります。

    [Image: 動的メンバーシップ グループのルール ビルダーを示すスクリーンショット。]
6. メンバーシップ ルールで使用できるカスタム拡張機能プロパティを表示するには:

    1. [ **カスタム拡張機能のプロパティを取得する**] を選択します。
    2. アプリケーション ID を入力し、 **[プロパティの更新]** を選択します。
7. ルールの更新が完了したら、[ **保存]** を選択します。

### ウェルカム メールのオンとオフを切り替える

管理者が新しい Microsoft 365 グループを作成すると、グループに追加されたユーザーはウェルカム メール通知を受け取ります。 後で、ユーザーまたはデバイスの属性 (セキュリティ グループのみ) が変更された場合、組織内の動的メンバーシップ グループのすべてのルールが変更のために処理されます。 追加されたユーザーは、ウェルカム通知も受け取ります。

[Exchange PowerShell](https://learn.microsoft.com/ja-jp/powershell/module/exchange/set-unifiedgroup) でこの動作を有効または無効にすることができます。

### ルールの処理状態を確認する

動的メンバーシップ グループの概要ページで、ルール処理の状態と最後のメンバーシップ変更の日付を確認できます。

[Image: 動的メンバーシップ グループの状態を示すスクリーンショット。]

**動的ルール処理**ステータスには、次のステータス メッセージが表示されます。

- **評価**中: グループの変更が受信され、更新プログラムが評価されています。
- **処理**: 更新プログラムが処理されています。
- **更新完了**: 処理が完了し、適用可能なすべての更新が行われました。
- **処理エラー**: メンバーシップ ルールの評価中にエラーが発生したため、処理を完了できませんでした。
- **更新が一時停止されました**:管理者は、動的メンバーシップ グループを更新するルールを一時停止しました。 `MembershipRuleProcessingState` は `Paused` に設定されます。
- **未開始**: 処理が開始されていません。

注

このページには、 **一時停止処理** オプションが含まれます。 以前は、このオプションは、 `membershipRuleProcessingState` プロパティの変更によってのみ使用されていました。 少なくとも [グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) ロールを持つユーザーは、この設定を管理でき、動的メンバーシップ グループの処理を一時停止および再開できます。 正しいロールを持たないグループ所有者には、この設定を編集するために必要な権限がありません。

**[最後のメンバーシップの変更**] には、次のステータス メッセージが表示されます。

- ** &lt;日付と時刻&gt;**: メンバーシップはこの日時に最後に更新されました。
- **進行中**: 更新は現在進行中です。
- **不明**:最終更新時刻を取得することができません。 新しいグループである可能性があります。

重要

動的メンバーシップ グループの処理を一時停止および一時停止解除すると、 **最後のメンバーシップ変更** 日にプレースホルダー値が表示されます。 この値は、処理が完了した後に更新されます。

特定のグループのメンバーシップ ルールの処理中にエラーが発生した場合は、グループの概要ページの上部にアラートが表示されます。 組織内のすべてのグループに対して 24 時間以上、動的メンバーシップ グループの保留中の更新を処理できない場合は、 **アラートが [すべてのグループ**] の上に表示されます。

[Image: システムの遅延が原因で動的グループ メンバーシップが更新されていないことを示すアラートのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-dynamic-membership"} -->
## Microsoft Entra IDで動的メンバーシップ グループのルールを管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership
- Service: entra-id / users
- Article date: 2026-08-13
- Summary: 動的メンバーシップ グループのルールを管理して、グループ メンバーとルール参照を自動的に設定する方法について説明します。

### 概要

ユーザー ベースまたはデバイス属性ベースのルールを作成して、Microsoft Entra IDの動的メンバーシップ グループのメンバーシップを有効にすることができます。 メンバー属性に基づくメンバーシップ ルールを使用して、動的メンバーシップ グループを自動的に追加および削除できます。 Microsoft Entraでは、1 つのテナントに最大 15,000 個の動的メンバーシップ グループを含めることができます。

この記事では、ユーザーまたはデバイスに基づいて動的メンバーシップ グループの規則を作成するためのプロパティと構文について説明します。

エージェントのユーザー アカウントは、Microsoft Entra内のユーザー ID のサブタイプです。 エージェントのユーザー アカウントは、ユーザー ベースのメンバーシップ ルールによって評価され、ルールを満たす場合に動的ユーザー グループに含めることができます。

既定では、エージェントのユーザー アカウントは他のユーザー ID と区別されません。 これらのアカウントを除外するには、メンバーシップ ルールに条件を含めます。 また、特定のエージェント ID ブループリントに関連付けられているエージェントのユーザー アカウントまたはターゲット アカウントのみを含むルールを作成することもできます。 エージェント ID (サービス プリンシパル) は、動的メンバーシップ グループのメンバーとしてサポートされていません。

注

セキュリティ グループにはデバイスまたはユーザーを含めることができますが、Microsoft 365 グループにはユーザーのみを含めることができます。

### 動的メンバーシップ グループに関する考慮事項

ユーザーまたはデバイスの属性が変更されると、システムはディレクトリ内の動的メンバーシップ グループのすべてのルールを評価して、変更によってグループの追加または削除がトリガーされるかどうかを確認します。 ユーザーまたはデバイスがグループのルールを満たしている場合は、そのグループのメンバーとして追加されます。 ルールに適合しなくなった場合は、削除されます。 動的メンバーシップ グループのメンバーを手動で追加または削除することはできません。

また、次の制限事項にも注意してください。

- ユーザーまたはデバイスの動的メンバーシップ グループを作成することはできますが、ユーザーとデバイスの両方を含むルールを作成することはできません。
- デバイス所有者のユーザー属性に基づいてデバイス メンバーシップ グループを作成することはできません。 デバイス メンバーシップ ルールでは、デバイスの属性のみを参照できます。

#### セキュリティに関する考慮事項: 動的グループ ルールで属性の書き込みアクセス許可を使用する前に評価する

動的メンバーシップ ルールを作成する場合、そのグループのメンバーシップのセキュリティは、ルールで参照される属性を変更できるユーザーによって異なります。 属性を選択する前に、Microsoft Entra IDと接続されているソース ディレクトリの両方で、その属性の書き込みアクセス許可を確認します。

これは、次の場合に特に重要です。

- **オンプレミスの Active Directoryから同期される属性。** 一部のオンプレミス属性は、ユーザーが自分の値を変更できるアクセス許可 (SELF 書き込み) で構成される場合があります。
- **アクセス制御に使用されるグループ。** 動的グループが機密性の高いリソース、アプリケーション、または条件付きアクセス ポリシーへのアクセスを制御する場合、そのアクセスのセキュリティは、ルール内の属性に対する書き込みコントロールと同じくらい強力です。

ベスト プラクティスとして、Microsoft Entra IDとソース (オンプレミスの Active Directory など) の両方で、動的メンバーシップ ルールで使用する予定のすべてのエンティティ型とその属性に対する書き込みアクセス許可を監査します。 セキュリティに依存するグループで使用される属性に対するセルフサービス書き込みアクセスを制限します。

注

ロール割り当て可能なグループは、割り当てられた (動的ではない) メンバーシップを要求することで、このリスクを既に防ぎます。

#### ライセンス要件

動的メンバーシップ グループの機能には、1 つ以上の動的メンバーシップ グループのメンバーである一意のユーザーごとに、Microsoft Entra ID P1 ライセンスまたは Intune for Education ライセンスが必要です。 ユーザーが動的メンバーシップ グループのメンバーになるには、ユーザーにライセンスを割り当てる必要はありません。 ただし、このようなすべてのユーザーを対象にするには、Microsoft Entra組織内のライセンスの最小数が必要です。

たとえば、組織内のすべての動的メンバーシップ グループに合計 1,000 人の一意のユーザーがいる場合は、ライセンス要件を満たすために、Microsoft Entra ID P1 に少なくとも 1,000 個のライセンスが必要です。

デバイスに基づいた動的メンバーシップ グループのメンバーであるデバイスには、ライセンスは必要ありません。

### Azure ポータルのルール ビルダー

Microsoft Entra IDでは、重要なルールをより迅速に作成および更新するためのルール ビルダーが提供されます。 ルール ビルダーでは、最大で 5 つの式の作成がサポートされます。 ルール ビルダーを使用して、いくつかの単純な式でルールを形成できますが、それを使用してすべてのルールを再現することはできません。 ルール ビルダーが作成するルールをサポートしていない場合は、テキスト ボックスを使用できます。

[Image: ルール ビルダーを示すスクリーンショット。式を追加するためのアクションが強調表示されています。]

詳細な手順については、「 [動的メンバーシップ グループを作成または更新する」を](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule)参照してください。

重要

ルール ビルダーは、ユーザー ベースの動的メンバーシップ グループでのみ使用できます。 デバイス ベースの動的メンバーシップ グループは、テキスト ボックスを使用してのみ作成できます。

テキスト ボックスの使用を必要とする高度なルールまたは構文の例を次に示します。

- 5 つを超える式を持つルール
- 直属の部下に関する規則
- `-contains`演算子または`-notContains`演算子を含む規則
- 演算子の優先順位の設定
- 複雑な式を含むルール。例えば `(user.proxyAddresses -any (_ -startsWith "contoso"))`

注

ルール ビルダーは、テキスト ボックスで作成された一部のルールを表示できない場合があります。 ルール ビルダーでルールを表示できない場合に、メッセージが表示されることがあります。 ルール ビルダーは、動的メンバーシップ グループの規則のサポートされている構文、検証、または処理をどのような方法でも変更しません。

### 単一式のルール構文

1 つの式は、メンバーシップ ルールの最も簡単な形式です。 単一の式を持つルールは、プロパティの構文が`<Property> <Operator> <Value>`の名前である`<object>.<property>`の形式になります。

次は、単一式で正しく構成されたメンバーシップ ルールの例です。

```
user.department -eq "Sales"
```

単一の式の場合、丸括弧は省略可能です。 メンバーシップ ルールの本文の合計長は 3,072 文字を超えることはできません。

#### メンバーシップ ルールの本文を構築する

グループにユーザーまたはデバイスを自動的に入力するメンバーシップ ルールは、true または false に帰結するバイナリ式です。 シンプルなルールの要素は次の 3 つです。

- プロパティ
- オペレーター
- 値

構文エラーを避けるためには、式内の部分の順序が重要です。

#### サポートされているプロパティ

メンバーシップ ルールを作成するには、次の 3 種類のプロパティを使用できます。

- ブール値
- 日付/時刻
- 糸
- 文字列コレクション

次のユーザー プロパティを使用して、1 つの式を作成できます。

##### ブール型のプロパティ

| プロパティ | 使用できる値 | 使用法 |
| --- | --- | --- |
| `accountEnabled` | `true`、`false` | `user.accountEnabled -eq true` |
| `dirSyncEnabled` | `true`、`false` | `user.dirSyncEnabled -eq true` |

##### 日付/時刻型のプロパティ

| プロパティ | 使用できる値 | 使用法 |
| --- | --- | --- |
| `employeeHireDate` (プレビュー) | 任意の `DateTimeOffset` 値またはキーワード `system.now` | `user.employeeHireDate -eq "value"` |

##### 文字列型のプロパティ

| プロパティ | 使用できる値 | 使用法 |
| --- | --- | --- |
| `city` | 任意の文字列値または `null` | `user.city -eq "value"` |
| `country` | 任意の文字列値または `null` | `user.country -eq "value"` |
| `companyName` | 任意の文字列値または `null` | `user.companyName -eq "value"` |
| `department` | 任意の文字列値または `null` | `user.department -eq "value"` |
| `displayName` | 任意の文字列値 | `user.displayName -eq "value"` |
| `employeeId` | 任意の文字列値 | `user.employeeId -eq "value"``user.employeeId -ne "null"` |
| `facsimileTelephoneNumber` | 任意の文字列値または `null` | `user.facsimileTelephoneNumber -eq "value"` |
| `givenName` | 任意の文字列値または `null` | `user.givenName -eq "value"` |
| `jobTitle` | 任意の文字列値または `null` | `user.jobTitle -eq "value"` |
| `mail` | 任意の文字列値または `null` (ユーザーの SMTP アドレス) | `user.mail -eq "value"``user.mail -notEndsWith "@Contoso.com"` |
| `mailNickName` | 任意の文字列値 (ユーザーのメール エイリアス) | `user.mailNickName -eq "value"``user.mailNickname -endsWith "-vendor"` |
| `mobile` | 任意の文字列値または `null` | `user.mobile -eq "value"` |
| `objectId` | ユーザー オブジェクトの GUID | `user.objectId -eq "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb"` |
| `onPremisesDistinguishedName` | 任意の文字列値または `null` | `user.onPremisesDistinguishedName -eq "value"` |
| `onPremisesSecurityIdentifier` | オンプレミスからクラウドに同期されたユーザーのオンプレミス セキュリティ識別子 (SID) | `user.onPremisesSecurityIdentifier -eq "S-1-1-11-1111111111-1111111111-1111111111-1111111"` |
| `passwordPolicies` | `None`、`DisableStrongPassword`、`DisablePasswordExpiration`、`DisablePasswordExpiration`、`DisableStrongPassword` | `user.passwordPolicies -eq "DisableStrongPassword"` |
| `physicalDeliveryOfficeName` | 任意の文字列値または `null` | `user.physicalDeliveryOfficeName -eq "value"` |
| `postalCode` | 任意の文字列値または `null` | `user.postalCode -eq "value"` |
| `preferredLanguage` | ISO 639-1 コード | `user.preferredLanguage -eq "en-US"` |
| `sipProxyAddress` | 任意の文字列値または `null` | `user.sipProxyAddress -eq "value"` |
| `state` | 任意の文字列値または `null` | `user.state -eq "value"` |
| `streetAddress` | 任意の文字列値または `null` | `user.streetAddress -eq "value"` |
| `surname` | 任意の文字列値または `null` | `user.surname -eq "value"` |
| `telephoneNumber` | 任意の文字列値または `null` | `user.telephoneNumber -eq "value"` |
| `usageLocation` | 2 文字の国または地域コード | `user.usageLocation -eq "US"` |
| `userPrincipalName` | 任意の文字列値 | `user.userPrincipalName -eq "alias@domain"` |
| `userType` | `member`、`guest`、`null` | `user.userType -eq "Member"` |

##### 文字列コレクション型のプロパティ

| プロパティ | 使用できる値 | 例示 |
| --- | --- | --- |
| `memberOf` (プレビュー) | 文字列のコレクション (グループ オブジェクト ID) | `user.memberOf -any (group.objectId -in ['value'])` |
| `otherMails` | 任意の文字列値 | `(user.otherMails -any (_ -startsWith "alias@domain"))``(user.otherMails -any (_ -endsWith "@contoso.com"))` |
| `proxyAddresses` | `SMTP: alias@domain`、`smtp: alias@domain` | `(user.proxyAddresses -any (_ -startsWith "SMTP: alias@domain"))``(user.proxyAddresses -any (_ -notEndsWith "@outlook.com"))` |

デバイス ルールに使用されるプロパティについては、「デバイスのルール」を参照してください。

#### サポートされている式の演算子

次の表は、サポートされているすべての演算子とその単一式用の構文をまとめたものです。 演算子は、ハイフン (`-`) プレフィックスの有無にかかわらず使用できます。 `Contains`演算子は、文字列の部分的な一致を行いますが、コレクション内の項目には一致しません。

注意

最良の結果を得るには、できるだけ `Match` または `Contains` の使用を最小限に抑えます。 動的 [メンバーシップ グループのよりシンプルで効率的なルールの作成](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-rule-more-efficient) に関する記事では、動的グループの処理時間を短縮するルールを作成する方法に関するガイダンスを提供します。 [`memberOf`](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-rule-member-of)オペレーターはプレビュー段階であり、いくつかの制限があるため、注意して使用してください。

| オペレーター | 構文 |
| --- | --- |
| `Ends With` | `-endsWith` |
| `Not Ends With` | `-notEndsWith` |
| `Not Equals` | `-ne` |
| `Equals` | `-eq` |
| `Not Starts With` | `-notStartsWith` |
| `Starts With` | `-startsWith` |
| `Not Contains` | `-notContains` |
| `Contains` | `-contains` |
| `Not Match` | `-notMatch` |
| `Match` | `-match` |
| `In` | `-in` |
| `Not In` | `-notIn` |

##### -in 演算子と -notIn 演算子を使用する

ユーザー属性の値を複数の値と比較する場合は、 `-in` 演算子または `-notIn` 演算子を使用できます。 角かっこ記号 (`[` と `]`) を使用して、値のリストを開始および終了します。

次の例では、`true`の値がリスト内のいずれかの値と等しい場合、式は`user.department`に評価されます。

```
   user.department -in ["50001","50002","50003","50005","50006","50007","50008","50016","50020","50024","50038","50039","51100"]
```

##### -le 演算子と -ge 演算子を使用する

動的メンバーシップ グループのルールで `-le` 属性を使用する場合は、より小さい (`-ge`) 演算子またはより大きい (`employeeHireDate`) 演算子を使用できます。

以下に例を示します。

```
user.employeehiredate -ge system.now -plus p1d 

user.employeehiredate -le 2020-06-10T18:13:20Z 

```

##### -match 演算子を使用する

`-match`演算子を使用して、任意の正規表現を照合できます。

次の例では、 `Da`、 `Dav`、および `David` は `true`と評価されます。 `aDa` は `false` に評価されます。

```
user.displayName -match "^Da.*"   
```

次の例では、 `David` は `true`に評価されます。 `Da` は `false` に評価されます。

```
user.displayName -match ".*vid"
```

#### サポートされている値

式で使用する値は、いくつかの型で構成できます。

- 文字列
- Boolean (`true`、 `false`)
- 数値
- 配列 (数値配列、文字列配列)

式内で値を指定する場合は、エラーを回避するために正しい構文を使用することが重要です。 構文のヒントを次に示します。

- 値が文字列でない限り、二重引用符は省略可能です。
- 正規表現および文字列操作では、大文字と小文字は区別されません。
- プロパティ名は大文字と小文字が区別されるため、表示されているとおりに正しく書式設定されていることを確認します。
- 文字列値に二重引用符が含まれている場合は、バッククォート (') 文字を使用して両方の引用符をエスケープする必要があります。 たとえば、 *user.department -eq "Sales"* は、 `Sales` が値である場合に適切な構文です。 単一引用符をエスケープするには、毎回 1 つではなく 2 つの単一引用符を使用します。
- `null`を値として使用して null チェックを実行することもできます (たとえば、`user.department -eq null`)。

##### null 値の使用

ルールで `null` 値を指定するには:

- 式の`-eq`値を比較するときは、`-ne`または`null`を使用します。
- 単語 `null` をリテラル文字列値として解釈する場合にのみ、引用符を使用します。
- null 値の比較演算子として `-not` 演算子を使用しないでください。 これを使用すると、 `null` または `$null`のどちらを使用してもエラーが発生します。

`null`値を参照する正しい方法は次のとおりです。

```
   user.mail –ne null
```

### 複数の式を持つルール

動的メンバーシップ グループの規則は、 `-and`、 `-or`、および `-not` 論理演算子によって接続された複数の式で構成できます。 論理演算子を組み合わせて使用することもできます。

複数の式で正しく構築されたメンバーシップ ルールの例を次に示します。

```
(user.department -eq "Sales") -or (user.department -eq "Marketing")
(user.department -eq "Sales") -and -not (user.jobTitle -startsWith "SDE")
```

#### 演算子の優先順位

次の一覧は、優先順位が最高から最も低い順にすべての演算子を示しています。 同じ行の演算子の優先順位は同じです。

```
-eq -ne -startsWith -notStartsWith -contains -notContains -match –notMatch -in -notIn
-not
-and
-or
-any -all
```

次の例は、演算子の優先順位を示しています。この例では、ユーザーに対して 2 つの式が評価されます。

```
   user.department –eq "Marketing" –and user.country –eq "US"
```

かっこは、優先順位が要件を満たしていない場合にのみ必要です。 たとえば、部門を最初に評価する場合、次のコードは、かっこを使用して順序を決定する方法を示しています。

```
   user.country –eq "US" –and (user.department –eq "Marketing" –or user.department –eq "Sales")
```

### 複雑な式を持つルール

メンバーシップ ルールは、プロパティ、演算子、値がより複雑な形式をとる複雑な式で構成できます。 次のいずれかの点に該当する場合、式は複雑と見なされます。

- プロパティは、値のコレクションで構成されます。具体的には、複数値のプロパティです。
- 式では、 `-any` 演算子と `-all` 演算子が使用されます。
- 式の値は、それ自体に 1 つ以上の式を指定できます。

#### 複数値プロパティ

複数値プロパティは、同じ型のオブジェクトのコレクションです。 これらを使用して、 `-any` と `-all` 論理演算子を使用してメンバーシップ ルールを作成できます。

| プロパティ | 数値 | 使用法 |
| --- | --- | --- |
| `assignedPlans` | コレクション内の各オブジェクトは、次の文字列プロパティを公開します: `capabilityStatus`、 `service`、 `servicePlanId` | `user.assignedPlans -any (assignedPlan.servicePlanId -eq "aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e" -and assignedPlan.capabilityStatus -eq "Enabled")` |
| `proxyAddresses` | `SMTP: alias@domain`、`smtp: alias@domain` | `(user.proxyAddresses -any (\_ -startsWith "contoso"))` |

##### -any 演算子と -all 演算子を使用する

次の演算子を使用して、コレクション内の 1 つまたはすべての項目に条件を適用できます。

- `-any`: コレクション内の少なくとも 1 つの項目が条件と一致する場合に満たされます。
- `-all`: コレクション内のすべての項目が条件と一致する場合に満たされます。

###### 例 1

`assignedPlans` は、ユーザーに割り当てられているすべてのサービス プランを一覧表示する複数値プロパティです。 サービス プランは、ライセンスや製品と同じではありません。 詳細については、「ライセンスの [製品名とサービス プラン識別子」を](https://learn.microsoft.com/ja-jp/entra/identity/users/licensing-service-plan-reference)参照してください。

ユーザーに割り当てられているサービス プラン ID を確認するには、`User.Read.All` アクセス許可で Microsoft Graph PowerShell を使用します。

```powershell
Connect-MgGraph -Scopes 'User.Read.All'

Get-MgUser -UserId user@contoso.com -Property assignedPlans |
  Select-Object -ExpandProperty assignedPlans |
  Select-Object service, servicePlanId, capabilityStatus | Format-List
```

次の式では、Exchange Online (プラン 2) サービス プランを持つユーザーを GUID 値として選択します。これは、`Enabled`状態でもあります。

```
user.assignedPlans -any (assignedPlan.servicePlanId -eq "efb87545-963c-4e0d-99df-69c6916d9eb0" -and assignedPlan.capabilityStatus -eq "Enabled")
```

サービス プランが有効になっているユーザーのみにルールを一致させる場合は、 `assignedPlan.capabilityStatus -eq "Enabled"` を含めます。

このようなルールを使用して、Microsoft 365またはその他の Microsoft Online Services 機能が有効になっているすべてのユーザーをグループ化できます。 その後、一連のポリシーを含むルールをグループに適用できます。

###### 例 2

次の式では、Intune サービスに関連付けられているサービス プランを持つすべてのユーザーを選択します (サービス名 `SCO`で識別されます)。

```
user.assignedPlans -any (assignedPlan.service -eq "SCO" -and assignedPlan.capabilityStatus -eq "Enabled")
```

###### 例 3

次の式では、Intune サービスに関連付けられている有効なサービス プランがないすべてのユーザーを選択します (サービス名 `SCO`で識別されます)。

```
user.assignedPlans -all (assignedPlan.service -ne "SCO" -or assignedPlan.capabilityStatus -ne "Enabled")
```

このルールには、サービス プランが割り当てられていないユーザーも含まれます。 サービス プランのないユーザーを除外する場合は、このルールを別の条件と組み合わせます。

###### 例 4

次の式では、サービス プランが割り当てられていないすべてのユーザーを選択します:

```
user.assignedPlans -all (assignedPlan.servicePlanId -eq null)
```

##### アンダースコア (\_) 構文を使用する

アンダースコア (`_`) 構文は、動的メンバーシップ グループにユーザーまたはデバイスを追加するために、複数値の文字列コレクション プロパティの 1 つで特定の値が出現した場合と一致します。 `-any`または `-all` 演算子と共に使用します。

ルールでアンダースコアを使用して、 `user.proxyAddress`に基づいてメンバーを追加する例を次に示します。 ( `user.otherMails`でも同じように動作します)。このルールは、 `contoso` で始まるプロキシ アドレスを持つユーザーをグループに追加します。

```
(user.proxyAddresses -any (_ -startsWith "contoso"))
```

#### その他のプロパティと一般的なルール

次のセクションでは、動的メンバーシップ グループの他の一般的な規則パターンについて説明します。

##### 直属の部下に対するルールを作成する

マネージャーのすべての直属の部下を含むグループを作成できます。 将来、マネージャーの直属の部下が変更されたとき、グループのメンバーシップが自動的に調整されます。

注

マネージャーは直属の部下の動的グループにも追加されます。

直属の部下ルールは、次の構文を使用して作成します。

```
Direct Reports for "{objectID_of_manager}"
```

有効なルールの例を次に示します。ここで、 `aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb` はマネージャーのオブジェクト ID です。

```
Direct Reports for "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb"
```

次のヒントは、ルールを正しく使用するのに役立ちます。

- *マネージャー ID* は、マネージャーのオブジェクト ID です。 マネージャーのプロファイルで見つけることができます。
- ルールを機能させるには、組織内のユーザーに対して `Manager` プロパティが正しく設定されていることを確認します。 ユーザーのプロファイルで現在の値を確認できます。
- このルールでは、マネージャーの直属の部下のみがサポートされます。 マネージャーの直属の部下 *とその* レポートを含むグループを作成することはできません。
- このルールを他のメンバーシップ ルールと組み合わせることはできません。

##### すべてのユーザーのルールを作成する

メンバーシップ ルールを使用して、組織内のすべてのユーザーを含むグループを作成できます。 将来、ユーザーが組織に追加されたり、組織から削除されたりしたとき、メンバーシップは自動的に調整されます。

`-ne`演算子と`null`値を含む単一の式を使用して、すべてのユーザーのルールを作成します。 このルールは、企業間ゲスト ユーザーとメンバー ユーザーをグループに追加します。

```
user.objectId -ne null
```

グループでゲスト ユーザーを除外し、ご自身の組織のメンバーのみを含めるようにする場合は、次の構文を使用できます。

```
(user.objectId -ne null) -and (user.userType -eq "Member")
```

##### すべてのデバイスのルールを作成する

メンバーシップ ルールを使用して、組織内のすべてのデバイスを含むグループを作成できます。 今後、デバイスが組織に追加されたり、組織から削除されたりしたとき、メンバーシップは自動的に調整されます。

`-ne`演算子と`null`値を含む単一の式を使用して、すべてのデバイスのルールを構築します。

```
device.objectId -ne null
```

#### 拡張属性とカスタム拡張プロパティ

拡張機能属性とカスタム拡張機能プロパティは、動的メンバーシップ グループの規則で文字列プロパティとしてサポートされています。

オンプレミスのWindows Server Active Directoryから拡張属性を[同期](https://learn.microsoft.com/ja-jp/graph/api/resources/onpremisesextensionattributes)できます。 または、Microsoft Graphを使用して拡張属性を更新することもできます。

拡張属性は `ExtensionAttribute<X>`の形式になります。ここで、 `<X>` は `1`-`15`と等しくなります。 複数値の拡張機能プロパティは、動的メンバーシップ グループの規則ではサポートされていません。

プロパティとして拡張機能属性を使用するルールの例を示します。

```
(user.extensionAttribute15 -eq "Marketing")
```

オンプレミスのWindows Server Active Directoryまたは接続されたソフトウェアとしてのサービス (SaaS) アプリケーションから、[カスタム拡張機能プロパティ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions)を同期できます。 Microsoft Graphを使用して、カスタム拡張機能のプロパティを作成できます。

カスタム拡張機能のプロパティは、 `user.extension_[GUID]_[Attribute]`の形式を受け取ります。ここで、

- `[GUID]` は、プロパティを作成したアプリケーションの Microsoft Entra ID の一意の識別子の一意識別子を簡略化したものです。 0 ~ 9 文字と A ~ Z 文字のみが含まれます。
- `[Attribute]` は、作成されたプロパティの名前です。

カスタム拡張機能プロパティを使用するルールの例を次に示します。

```
user.extension_c272a57b722d4eb29bfe327874ae79cb_OfficeNumber -eq "123"
```

カスタム拡張プロパティは、ディレクトリまたはMicrosoft Entra拡張機能プロパティとも呼ばれます。

Graph Explorer でユーザーのプロパティに対してクエリを実行し、プロパティ名を検索することで、ディレクトリ内でカスタム プロパティ名を見つけることができます。 また、動的ルール ビルダーで [ **カスタム拡張機能プロパティの取得** ] リンクを選択して、一意のアプリ ID を入力し、動的メンバーシップ グループのルールを作成するときに使用するカスタム拡張機能プロパティの完全な一覧を受け取ることができます。 この一覧を更新すると、そのアプリの新しいカスタム拡張機能プロパティを取得できます。 拡張属性とカスタム拡張プロパティは、テナント内のアプリケーションからのものである必要があります。

詳細については、「 [動的メンバーシップ グループでの属性の使用](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions#use-the-attributes-in-dynamic-membership-groups)」を参照してください。

### デバイスのルール

グループのメンバーシップのデバイス オブジェクトを選択するルールを作成できます。 グループ メンバーとしてユーザーとデバイスの両方を含めることはできません。 Intune アプリとポリシーの割り当ての場合は、ターゲット設定のニーズを満たす場合に割り当てフィルターを使用します。 フィルターには、割り当てられたグループ内の管理対象デバイスまたはアプリが含まれるか除外されます。 Intune 以外またはワークロード間のシナリオ (条件付きアクセス、ライセンス、Autopilot プロファイルの割り当てなど) に動的グループを使用します。 詳細については、「[Microsoft Intuneでの割り当てフィルターの作成](https://learn.microsoft.com/ja-jp/intune/fundamentals/filters/overview)」を参照してください。

注

`organizationalUnit`属性は一覧に表示されなくなり、使用しないでください。 Intune では特定のケースでこの文字列が設定されますが、Microsoft Entra IDでは認識されません。 この属性に基づいてグループにデバイスは追加されません。

`systemlabels`属性は読み取り専用です。 Intune では設定できません。

Windows 10の場合、`deviceOSVersion` 属性の正しい形式は `device.deviceOSVersion -startsWith "10.0.1"` です。 `Get-MgDevice` PowerShell コマンドレットを使用して、書式設定を検証できます。

```
Get-MgDevice -Search "displayName:YourMachineNameHere" -ConsistencyLevel eventual | Select-Object -ExpandProperty 'OperatingSystemVersion'
```

次のデバイス属性を使用できます。

| デバイス属性 | 数値 | 例示 |
| --- | --- | --- |
| `accountEnabled` | `true`、`false` | `device.accountEnabled -eq true` |
| `deviceCategory` | 有効なデバイス カテゴリ名 | `device.deviceCategory -eq "BYOD"` |
| `deviceId` | 有効なMicrosoft Entraデバイス ID | `device.deviceId -eq "d4fe7726-5966-431c-b3b8-cddc8fdb717d"` |
| `deviceManagementAppId` | Microsoft Entra IDでのモバイル デバイス管理の有効なアプリケーション ID | `device.deviceManagementAppId -eq "0000000a-0000-0000-c000-000000000000"`Microsoft Intune 管理対象デバイス用 System Center Configuration Manager の共同管理デバイス向け `"54b943f8-d761-4f8d-951e-9cea1846db5a"` |
| `deviceManufacturer` | 任意の文字列値 | `device.deviceManufacturer -eq "Samsung"` |
| `deviceModel` | 任意の文字列値 | `device.deviceModel -eq "iPad Air"` |
| `displayName` | 任意の文字列値 | `device.displayName -eq "Rob iPhone"` |
| `deviceOSType` | 任意の文字列値 | `(device.deviceOSType -eq "iPad") -or (device.deviceOSType -eq "iPhone")``device.deviceOSType -startsWith "AndroidEnterprise"``device.deviceOSType -eq "AndroidForWork"``device.deviceOSType -eq "Windows"` |
| `deviceOSVersion` | 任意の文字列値 | `device.deviceOSVersion -eq "9.1"``device.deviceOSVersion -startsWith "10.0.1"` |
| `deviceOwnership`^1^ | `Personal`、`Company`、`Unknown` | `device.deviceOwnership -eq "Company"` |
| `devicePhysicalIds` | すべてのWindows Autopilot デバイス、`OrderID`、`PurchaseOrderID` など、Windows Autopilotが使用する任意の文字列値 | `device.devicePhysicalIDs -any _ -startsWith "[ZTDId]"``device.devicePhysicalIds -any _ -eq "[OrderID]:179887111881"``device.devicePhysicalIds -any _ -eq "[PurchaseOrderId]:76222342342"` |
| `deviceTrustType`^2^ | `AzureAD`、`ServerAD`、`Workplace` | `device.deviceTrustType -eq "AzureAD"` |
| `enrollmentProfileName` | Apple Automated Device Enrollment、Android Enterprise 企業所有の専用デバイス登録、またはWindows Autopilotのプロファイル名 | `device.enrollmentProfileName -eq "DEP iPhones"` |
| `extensionAttribute1`^3^ | 任意の文字列値 | `device.extensionAttribute1 -eq "some string value"` |
| `extensionAttribute2` | 任意の文字列値 | `device.extensionAttribute2 -eq "some string value"` |
| `extensionAttribute3` | 任意の文字列値 | `device.extensionAttribute3 -eq "some string value"` |
| `extensionAttribute4` | 任意の文字列値 | `device.extensionAttribute4 -eq "some string value"` |
| `extensionAttribute5` | 任意の文字列値 | `device.extensionAttribute5 -eq "some string value"` |
| `extensionAttribute6` | 任意の文字列値 | `device.extensionAttribute6 -eq "some string value"` |
| `extensionAttribute7` | 任意の文字列値 | `device.extensionAttribute7 -eq "some string value"` |
| `extensionAttribute8` | 任意の文字列値 | `device.extensionAttribute8 -eq "some string value"` |
| `extensionAttribute9` | 任意の文字列値 | `device.extensionAttribute9 -eq "some string value"` |
| `extensionAttribute10` | 任意の文字列値 | `device.extensionAttribute10 -eq "some string value"` |
| `extensionAttribute11` | 任意の文字列値 | `device.extensionAttribute11 -eq "some string value"` |
| `extensionAttribute12` | 任意の文字列値 | `device.extensionAttribute12 -eq "some string value"` |
| `extensionAttribute13` | 任意の文字列値 | `device.extensionAttribute13 -eq "some string value"` |
| `extensionAttribute14` | 任意の文字列値 | `device.extensionAttribute14 -eq "some string value"` |
| `extensionAttribute15` | 任意の文字列値 | `device.extensionAttribute15 -eq "some string value"` |
| `isRooted` | `true`、`false` | `device.isRooted -eq true` |
| `managementType` | モバイル デバイス管理 (モバイル デバイス用) | `device.managementType -eq "MDM"` |
| `memberOf` (プレビュー) | 文字列のコレクション (グループ オブジェクト ID) | `device.memberOf -any (group.objectId -in ['value'])` |
| `objectId` | 有効なMicrosoft Entra オブジェクト ID | `device.objectId -eq "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb"` |
| `profileType` | Microsoft Entra IDの有効な [profile タイプ](https://learn.microsoft.com/ja-jp/graph/api/resources/device?view=graph-rest-1.0&preserve-view=true#properties) | `device.profileType -eq "RegisteredDevice"` |
| `systemLabels`^4^ | Intune デバイス プロパティと一致する、Modern Workplace デバイスにタグを付けるための読み取り専用の文字列 | `device.systemLabels -startsWith "M365Managed" SystemLabels` |

^1^`deviceOwnership` を使用してデバイスの動的メンバーシップ グループを作成する場合は、値を `Company` に設定する必要があります。 Intune では、デバイスの所有権は代わりに `Corporate`として表されます。 詳細については、[`ownerTypes`](https://learn.microsoft.com/ja-jp/mem/intune/developer/reports-ref-devices#ownertypes)を参照してください。

^2^`deviceTrustType` を使用してデバイスの動的メンバーシップ グループを作成する場合は、Microsoft Entra 参加済みデバイスを表すために `AzureAD` を設定し、Microsoft Entra ハイブリッド参加済みデバイスを表すために `ServerAD` を、または Microsoft Entra 登録済みデバイスを表すために `Workplace` を設定する必要があります。

^3^`extensionAttribute1-15` を使用してデバイスの動的メンバーシップ グループを作成する場合は、デバイスの `extensionAttribute1-15` の値を設定する必要があります。 [Microsoft Entra デバイス オブジェクトに`extensionAttributes`を書き込む方法の詳細](https://learn.microsoft.com/ja-jp/graph/api/device-update?view=graph-rest-1.0&tabs=http&preserve-view=true#example-2--write-extensionattributes-on-a-device)。

^4^`systemLabels`を使用する場合、さまざまなコンテキストで使用される読み取り専用属性 (デバイス管理や機密ラベル付けなど) は、Intune で編集できません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-dynamic-membership-powershell-samples"} -->
## 動的メンバーシップ処理用の PowerShell サンプル - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership-powershell-samples
- Service: entra-id / users
- Article date: 2026-06-11
- Summary: これらの PowerShell サンプルを使用して、インシデント対応中にMicrosoft Entra グループと管理単位の動的メンバーシップ ルール処理を一時停止および再開します。

動的メンバーシップ ルールによって、Microsoft Entra テナントで意図しない変更や処理の遅延が発生した場合は、ルール処理を一時停止して問題を含め、復旧中に制御された順序で再開できます。 これらのサンプルでは、動的グループと動的管理単位の両方について説明します。

### Overview

これらの PowerShell サンプルは、メンバーシップの更新に関する継続的な問題や意図しないルールの変更を軽減する必要がある場合に、Microsoft Entra テナントのグループと管理単位の動的メンバーシップ ルール処理を一時停止および再開するのに役立ちます。 一時停止するとルールの評価が停止し、再開すると元に戻ります。 各スクリプトは 2 つのフェーズで実行されます。グループは最初に、次に管理単位です。 各フェーズの開始時に、スクリプトは確認を求めます。 `yes` を入力して、そのフェーズを実行するか、他の何かを入力してスキップします。 これにより、1 回の実行でグループのみ、管理単位のみ、またはその両方を対象にできます。

これらのサンプルでは、[Microsoft Graph PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)を使用します。

### 前提条件

- PowerShell 5.1 (x64) 以降。
- Microsoft Graph PowerShell モジュール:

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    ```
- 対象となるコレクションを管理できるサインイン アカウント。 グループ フェーズには、[Groups Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) Microsoft Entra ロールと `Group.ReadWrite.All` Microsoft Graph スコープが必要です。 管理単位フェーズには、[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) Microsoft Entra ロールと`AdministrativeUnit.ReadWrite.All` スコープが必要です。 各スクリプトは、実行するフェーズのスコープのみを要求します。

Important

運用環境でこれらのスクリプトを実行する前に、テスト環境のすべての手順を確認します。

Note

大規模なテナントでは、これらのスクリプトによって Microsoft Graph のスロットリングが発生する場合があります。 組み込みの再試行処理があるため、失敗するのではなく、実行時間が長くなることが想定されます。 明示的なエラーが表示されない限り、実行中のスクリプトを取り消さないでください。

### 動的メンバーシップ処理を一時停止する

| Sample | Description |
| --- | --- |
| [動的メンバーシップを持つすべてのグループと管理単位を一時停止する](https://learn.microsoft.com/ja-jp/entra/identity/users/scripts/powershell-pause-all-dynamic-membership) | テナントに動的メンバーシップ ルールがあるすべてのグループと管理単位を一時停止します。 このサンプルは、テナント全体の意図しない変更や広範囲にわたるメンバーシップ処理の遅延が疑われる場合に使用します。 |
| [動的メンバーシップを使用して特定のグループと管理単位を一時停止する](https://learn.microsoft.com/ja-jp/entra/identity/users/scripts/powershell-pause-specific-dynamic-membership) | ID を指定したグループと管理単位のみを一時停止します。 このサンプルは、コレクションの既知のサブセットの処理を停止する必要がある場合に使用します。 |
| [指定を除き、動的メンバーシップを持つすべてのグループと管理単位を一時停止する](https://learn.microsoft.com/ja-jp/entra/identity/users/scripts/powershell-pause-all-except-dynamic-membership) | 除外する ID を除き、動的メンバーシップを持つすべてのグループと管理単位を一時停止します。 このサンプルを使用して、重要なコレクションを実行したまま、他のすべてを停止します。 |

### 動的メンバーシップ処理を再開する

| Sample | Description |
| --- | --- |
| [動的メンバーシップを使用して特定の重要なグループと管理単位を再開する](https://learn.microsoft.com/ja-jp/entra/identity/users/scripts/powershell-resume-specific-critical-dynamic-membership) | 指定した重要なグループと管理単位の処理を再開します。 一時停止から回復する場合は、最初にこのサンプルを実行します。 |
| [動的メンバーシップを使用して重要でないグループと管理単位をバッチで再開する](https://learn.microsoft.com/ja-jp/entra/identity/users/scripts/powershell-resume-noncritical-dynamic-membership) | 一時停止された重要でないグループと管理単位の処理を再開します。各実行ごとに最大 100 個。 重要なコレクションが復元され、一時停止から少なくとも 12 時間が経過したら、このサンプルを実行します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-dynamic-rule-member-of"} -->
## Azure portal で memberOf 属性を使用して動的メンバーシップ グループを構成する (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-rule-member-of
- Service: entra-id / users
- Article date: 2026-01-27
- Summary: Microsoft Entra ID で他のグループのメンバーを含められる動的メンバーシップ グループを作成する方法について説明します。

### 概要

Microsoft Entra ID のこの機能プレビューを使用すると、管理者は、`memberOf` 属性を使用して他のグループのメンバーを追加することで設定される動的メンバーシップ グループと管理単位を作成できます。 これまで Microsoft Entra ID でグループベースのメンバーシップを読み取ることができなかったアプリは、これらの新しい `memberOf` グループのメンバーシップ全体を読み取れるようになりました。 これらのグループは、アプリに使用できるだけでなく、ライセンスの割り当てにも使用できます。

Warnung

これはプレビュー機能であり、運用環境での使用を目的としたものではありません。 この機能の使用には、テナントでの動的なグループ処理に影響する可能性がある制限があります。 この機能を使用する前に 、「プレビューの制限事項」 セクションを確認してください。

次の図は、Security-Group-X と Security-Group-Y のメンバーからなる Dynamic-Group-A を作成する方法を示しています。 Security-Group-X および Security-Group-Y 内のグループのメンバーは、Dynamic-Group-A のメンバーにはなりません。

[Image: memberOf 属性のしくみを示す図。]

このプレビューを使用すると、管理者は Azure portal、Microsoft Graph、PowerShell で `memberOf` 属性を使用して動的メンバーシップ グループを構成できます。 セキュリティ グループ、Microsoft 365 グループ、オンプレミスの Active Directory から同期されるグループはすべて、これらの動的メンバーシップ グループのメンバーとして追加できます。 これらはすべて 1 つのグループに追加することもできます。 たとえば、その動的グループがセキュリティ グループであっても、Microsoft 365 グループ、セキュリティ グループ、オンプレミスから同期されるグループを使用してメンバーシップを定義できます。

### 前提条件

 属性を使って Microsoft Entra 動的グループを作成するには、少なくとも`memberOf`である必要があります。 Microsoft Entra テナントに Microsoft Entra ID P1 または P2 ライセンスが必要です。

### プレビューの制限事項

- このプレビューは、テナントでの動的なグループ処理に影響を与える可能性があるため、テスト環境でのみ使用する必要があります。 これらの制限に対処中であり、利用可能になると更新プログラムが提供されます。
- 各 Microsoft Entra テナントは、 `memberOf` 属性を使用して 500 個の動的グループに制限されます。 `memberOf` グループは、15,000 の動的グループ クォータの合計にカウントされます。
- 各動的グループには、最大 50 個のメンバー グループを含めることができます。
- セキュリティ グループのメンバーを `memberOf` 動的メンバーシップ グループに追加する場合、セキュリティ グループの直接メンバーのみが動的グループのメンバーになります。
- ある `memberOf` 動的グループを使用して、別の `memberOf` 動的グループのメンバーシップを定義することはできません。 たとえば、グループ B と C のメンバーが含まれる動的グループ A は、動的グループ D のメンバーにすることはできません。
- `memberOf` 属性を他のルールと共に使用することはできません。 たとえば、動的グループ A にグループ B のメンバーを含める、かつレドモンドにいるユーザーのみを含めるというルールは失敗します。
- 動的グループ ルール ビルダーと検証機能は、現時点では `memberOf` には使用できません。
- この `memberOf` 属性は、他の演算子では使用できません。 たとえば、"グループ A のメンバーを動的グループ B に含めることはできない" というルールは作成できません。
- `memberOf`動的メンバーシップ グループに含まれるユーザーは、テナントに多数のグループまたは頻繁な動的メンバーシップ グループの更新がある場合、テナントの処理時間が遅くなる可能性があります。
- memberOf 動的グループのメンバーシップは、子グループが削除されたとき、またはメンバーが子グループから削除されたときに自動的に更新されることはありません。 影響を受けるユーザーまたはデバイスは、ルールが変更されるまで memberOf 動的グループのメンバーのままです。
- パブリック クラウドでのみ使用できます。

### 概要

この機能は、Azure portal、Microsoft Graph、PowerShell で使用できます。 ただし、 `memberOf` 属性は、ルール ビルダー UI では現在サポートされていません。 Azure portal で `memberOf` を使用するには、ルール エディター (高度な構文) を使用してルールを定義する必要があります。

#### memberOf 属性による動的グループを作成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[グループ]**&gt;**[すべてのグループ]** に移動します。
3. [ **新しいグループ]** を選択します。
4. グループの詳細を入力します。 グループの種類は **Security** または **Microsoft 365** で、メンバーシップの種類は **動的ユーザー** または **動的デバイス**に設定できます。
5. [ **動的クエリの追加]** を選択します。
6. MemberOf は、ルール ビルダー UI ではまだサポートされていません。 [ルール**構文**] ボックスでルールを書き込むには、[**編集] を**選択します。

    1. ユーザー ルールの例: `user.memberof -any (group.objectId -in ['groupId'])`
    2. デバイス ルールの例: `device.memberof -any (group.objectId -in ['groupId'])`

    注

    `'groupId'`を、動的**グループにメンバーを含めるソース グループのオブジェクト ID** に置き換えます。

    次の 2 つの例が代替手段です。

    - 動的 **ユーザー** グループを作成するときは、 **ユーザー** ルールを使用します。
    - 動的 **デバイス** グループを作成するときは、 **デバイス** ルールを使用します。

    複数のソース グループを含めるには、複数のグループ オブジェクト ID を指定します。 例えば次が挙げられます。

    ```text
    user.memberof -any (group.objectId -in ['<groupObjectId1>', '<groupObjectId2>'])
    ```
7. [ **OK] を選択します**。
8. [ **グループの作成] を選択します**。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-dynamic-rule-more-efficient"} -->
## 動的メンバーシップ グループのよりシンプルで高速なルールを作成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-rule-more-efficient
- Service: entra-id / users
- Article date: 2025-01-15
- Summary: メンバーシップ ルールを最適化してグループを自動的に設定する方法について説明します。

### 概要

この記事では、動的メンバーシップ グループのルールを簡略化するために使用できる最も一般的な方法について説明します。 より単純で効率的なルールにより、動的グループの処理時間が短縮されます。

動的メンバーシップ グループのメンバーシップ ルールを作成するときは、この記事のヒントに従って、これらのルールをできるだけ効率的に作成してください。

### -match 演算子の使用を最小限に抑える

ルールでの `-match` 演算子の使用を可能な限り最小限に抑えます。 代わりに、 `-startsWith`、 `-endsWith` 、または `-eq` 演算子を使用できるかどうかを調べられます。 `-eq` は、完全な属性値がわかっている場合に推奨されます。プレフィックスまたはサフィックスのみがわかっている場合、 `-startsWith` と `-endsWith` は次に効率的です。 `-match`演算子を使用せずにグループのユーザーを選択するルールを記述できる他のプロパティを使用することを検討してください。

たとえば、都市が Lagos であるすべてのユーザーを含むグループのルールが必要な場合は、次のようなルールを使用しないでください。

- `user.city -match "ago"`
- `user.city -match ".*?ago.*"`

次の例のようなルールを使用することをお勧めします。

- `user.city -startsWith "Lag"`

または、最も良い方法は次のとおりです:

- `user.city -eq "Lagos"`

同様に、電子メール ドメインルールの場合は、次を使用しないでください。

- user.mail -match ".\*@Contoso.com$"
- user.mail -notMatch ".\*@Contoso.com$"

次を使用することを推奨します:

- user.userPrincipalName -endsWith "@Contoso.com"
- user.mail -notEndsWith "@Contoso.com"

### -contains 演算子の使用を最小限に抑える

`-match`と同様に、ルールでの`-contains`演算子の使用を可能な限り最小限に抑えます。 代わりに、 `-startsWith`、 `-endsWith` 、または `-eq` 演算子を使用できるかどうかを調べられます。 `-contains`を使用すると、特に多数の動的メンバーシップ グループを持つテナントの処理時間が長くなる可能性があります。

### 使用する -or 演算子の数を減らします

ルールが同じプロパティに対してさまざまな値を使用するタイミングを特定し、 `-or` 演算子と共にリンクします。 代わりに、 `-in` 演算子を使用して、それらを 1 つの条件にグループ化します。 1 つの条件により、ルールの評価が容易になります。

たとえば、次のようなルールを使用しないでください。

```
(user.department -eq "Accounts" -and user.city -eq "Lagos") -or 
(user.department -eq "Accounts" -and user.city -eq "Ibadan") -or 
(user.department -eq "Accounts" -and user.city -eq "Kaduna") -or 
(user.department -eq "Accounts" -and user.city -eq "Abuja") -or 
(user.department -eq "Accounts" -and user.city -eq "Port Harcourt")
```

次の例のようなルールを使用することをお勧めします。

- `user.department -eq "Accounts" -and user.city -in ["Lagos", "Ibadan", "Kaduna", "Abuja", "Port Harcourt"]`

逆に、同じプロパティが、 `-and` 演算子とリンクされているさまざまな値と等しくない類似のサブクリテリアを識別します。 次に、`-notin` 演算子を使用して 1 つの条件にグループ化し、ルールの理解と評価を簡単にします。

たとえば、次のようなルールを使用しないでください。

- `(user.city -ne "Lagos") -and (user.city -ne "Ibadan") -and (user.city -ne "Kaduna") -and (user.city -ne "Abuja") -and (user.city -ne "Port Harcourt")`

次の例のようなルールを使用することをお勧めします。

- `user.city -notin ["Lagos", "Ibadan", "Kaduna", "Abuja", "Port Harcourt"]`

### 冗長な条件を回避する

ルールで冗長な条件を使用していないことを確認します。 たとえば、次のようなルールを使用しないでください。

- `user.city -eq "Lagos" or user.city -startswith "Lag"`

次の例のようなルールを使用することをお勧めします。

- `user.city -startswith "Lag"`
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-dynamic-rule-validation"} -->
## 動的メンバーシップ グループの規則を検証する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-rule-validation
- Service: entra-id / users
- Article date: 2024-12-19
- Summary: Microsoft Entra ID で動的メンバーシップ グループのルールに対してメンバーをテストする方法について説明します。

### 概要

Microsoft Entra ID は、動的メンバーシップ グループのルールを検証する手段を提供します。 [ **ルールの検証** ] タブでは、サンプル グループ メンバーに対してルールを検証して、ルールが期待どおりに動作していることを確認できます。

動的メンバーシップ グループの規則を作成または更新する場合は、ユーザーまたはデバイスがグループのメンバーであるかどうかを確認する必要があります。 この知識は、ユーザーまたはデバイスがルールの条件を満たしているかどうかを評価するのに役立ちます。 また、メンバーシップが予期されない場合のトラブルシューティングにも役立ちます。

### 前提条件

動的メンバーシップ グループのルールを評価するには、管理者が少なくとも [グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)である必要があります。

Warnung

間接ロールの割り当てを使用して必要なロールの 1 つを割り当てることはサポートされていません。

### 動的メンバーシップ グループのルールを検証する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくともグループ管理者としてサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All groups** に移動します。
3. 既存の動的グループを選択するか、新しい動的グループを作成してから、[ **動的メンバーシップ ルール**] を選択します。

    [Image: 動的メンバーシップ ルールの詳細を表示するための選択のスクリーンショット。]
4. [ **ルールの検証** ] タブで、メンバーシップを検証するユーザーを選択します。 一度に 20 人のユーザーまたはデバイスを選択できます。

    [Image: ルールを検証するプロセスでユーザーを追加するためのボタンのスクリーンショット。]
5. ユーザーまたはデバイスの選択が完了したら、[選択] を **選択します**。 検証が自動的に開始されます。 検証結果は、ユーザーがグループのメンバーであるかどうかを示します。

    [Image: ルール検証の結果を示すスクリーンショット。]
6. ルールが有効でない場合、またはネットワークに問題がある場合、結果は **[不明] と**表示されます。 値が **[不明]** の場合は、[ **詳細の表示**] を選択します。 詳細なエラー メッセージには、問題と必要なアクションが記載されています。

    [Image: ルール検証の詳細な結果を示すスクリーンショット。]
7. 規則を変更すると、メンバーシップの新たな検証をトリガーできます。 ユーザーがグループのメンバーではない理由を確認するには、[ **詳細の表示**] を選択します。 検証の詳細には、ルールを構成する各式の結果が表示されます。 [ **OK] を** 選択して詳細を閉じます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-dynamic-tutorial"} -->
## 動的グループへのユーザーの追加 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-tutorial
- Service: entra-id / users
- Article date: 2025-01-31
- Summary: ユーザー メンバーシップの規則を含んだグループを使って、ユーザーを自動的に追加したり削除したりします。

### 概要

Microsoft Entra の一部である Microsoft Entra ID では、セキュリティ グループまたは Microsoft 365 グループのユーザーを自動的に追加したり削除したりできるので、この作業を必ずしも手動で行う必要はありません。 ユーザーまたはデバイスのいずれかのプロパティが変更されるたびに、Microsoft Entra ID は Microsoft Entra 組織内のすべての動的メンバーシップ グループの規則を評価して、その変更によってメンバーの追加または削除を行う必要があるかを確認します。

このチュートリアルでは、次の作業を行う方法について説明します。

- パートナー企業のゲスト ユーザーが自動的に追加されるグループを作成します。
- パートナー固有の機能にゲスト ユーザーがアクセスするためのライセンスをグループに割り当てます。
- 補足: ゲスト ユーザーを削除して **[すべてのユーザー]** グループのセキュリティを確保する (社内専用サイトへのアクセス権をメンバー ユーザーに与える場合など)。

Azure サブスクリプションをお持ちでない場合は、開始する前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### 前提条件

この機能を使うには、組織の管理者用に Microsoft Entra ID P1 または P2 ライセンスが 1 つ必要です。 ない場合は、Microsoft Entra ID で **[ライセンス]**&gt;**[製品]**&gt;**[試用/購入]** を選択します。

ユーザーを動的メンバーシップ グループのメンバーにするために、そのユーザーにライセンスを割り当てる必要はありません。 そのようなユーザーの全員をカバーできるだけの最小限の Microsoft Entra ID P1 ライセンス数が組織にあれば済みます。

### ゲスト ユーザーのグループを作成する

まず、1 つのパートナー企業のゲスト ユーザーのグループを作成します。 これらのユーザーには特別なライセンスが必要であるため、通常は、そのためのグループを作成した方が効率的です。

1. [グループ管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)にサインインします。
2. **Microsoft Entra ID** を選択します。
3. **[グループ]**&gt;**[すべてのグループ]**&gt;**[新しいグループ]** の順に選択します。

    [Image: 新しいグループを開始するための [選択] コマンドの使用のスクリーンショット。]
4. **[新しいグループ]** ペインで、次の手順を実行します。

    - *ゲスト ユーザー名*、*メール アドレス*、およびグループ *の説明* を入力します。
    - **[メンバーシップの種類]** を **[動的ユーザー]** に変更します。

    [Image: ユーザーが動的メンバーシップ グループの詳細を入力する [グループ] ページのスクリーンショット。]
5. **[所有者が選択されていません]** を選択し、**[所有者の追加]** ペインでスクロールして目的の所有者を見つけます。 グループに所有者を追加する名前を選択します。
6. [**選択**] を選択して、所有者を保存し、[**所有者の追加**] ウィンドウを閉じます。
7. **[動的なユーザー メンバー]** ボックスで **[動的クエリの追加]** を選択します。
8. **[動的メンバーシップ ルール]** ペインで次の手順を実行します。

    - **プロパティ** フィールドで、既存の値を選択し、**userType**を選択します。
    - **[Operator](https://learn.microsoft.com/ja-jp/entra/identity/users/演算子)** フィールドで **[Equals](https://learn.microsoft.com/ja-jp/entra/identity/users/等しい)** が選択されていることを確認します。
    - **[値]** フィールドを選択し、「**Guest**」と入力します。
    - **[式の追加]** ハイパーリンクを選択して新しい行を追加します。
    - **[And/Or]** フィールドで **[AND]** を選択します。
    - **[プロパティ]** フィールドで **[companyName]** を選択します。
    - **[Operator](https://learn.microsoft.com/ja-jp/entra/identity/users/演算子)** フィールドで **[Equals](https://learn.microsoft.com/ja-jp/entra/identity/users/等しい)** が選択されていることを確認します。
    - **[値]** フィールドに「**Contoso**」と入力します。
    - **[カスタム拡張機能プロパティの取得]** を選択してアプリケーション ID を入力し、ルールを作成するために使用可能なすべてのカスタム拡張機能プロパティを取得します。
    - 完了後は、**[保存]** を選択して **[動的メンバーシップ ルール]** を閉じます。
9. 完了してグループを作成するには、**[グループ]** ペインで **[作成]** を選択します。

### ライセンスを割り当てる

新しいグループが作成されたら、パートナー ユーザーに必要なライセンスを適用することができます。

1. [Microsoft 365 管理センター](https://admin.microsoft.com/)で、[**課金**&gt;**ライセンス]** に移動します。
2. [サブスクリプション] タブ **で** 、割り当てる製品を選択します。
3. [ライセンス] ページ **で** 、[ **グループ** ] タブを選択し、[ **ライセンスの割り当て]** を選択します。
4. 追加するグループ名を検索し、推奨されるグループの一覧からグループを選択します。
5. 特定のアイテムへのアクセス権を割り当てるか削除するには、[ **アプリとサービスのオンとオフを切り替える**] を選択します。
6. 完了したら、**割り当て** を選択してから **閉じる** を選択します。

### "すべてのユーザー" グループからゲストを削除する

最終的な管理の計画は、すべてのゲスト ユーザーを各自の会社ごとのグループに割り当てることでしょう。 **すべてのユーザー** グループを変更して、組織内のユーザーを含むように制限することもできます。 そのうえで、ホーム組織に固有のアプリとライセンスを割り当てることができます。

[Image: すべてのユーザー グループをメンバー専用に変更の使用のスクリーンショット。]

### リソースをクリーンアップする

チュートリアルが完了したら、作成したリソースをクリーンアップします。

#### ゲスト ユーザー グループを削除する

1. [グループ管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)にサインインします。
2. **[グループ]**&gt;**[すべてのグループ]** に移動します。
3. **[ゲスト ユーザー]** グループを選択し、省略記号 (...) を選択して、**[削除]** を選択します。 グループを削除すると、割り当てられているライセンスはすべて削除されます。

#### [すべてのユーザー] グループを復元する

1. [ **Entra ID**&gt;**Groups**&gt;**すべてのグループ**を選択します。 **すべてのユーザー** グループの名前を選択して、そのグループを開きます。
2. **[動的メンバーシップ ルール]** を選択し、ルール内のテキストをすべて消去して、 **[保存]** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-lifecycle"} -->
## Microsoft 365 グループの有効期限を設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-lifecycle
- Service: entra-id / users
- Article date: 2025-01-15
- Summary: Microsoft Entra ID で Microsoft 365 グループの有効期限を設定する方法について説明します。

### 概要

この記事では、Microsoft 365 グループの有効期限ポリシーを設定して、そのライフサイクルを管理する方法について説明します。 有効期限ポリシーを設定できるのは、Microsoft Entra ID 内の Microsoft 365 グループのみです。

グループに有効期限を設定した後:

- ユーザー アクティビティがあるグループは、有効期限が近づくと自動的に更新されます。
- グループが自動更新されない場合、グループの所有者には、グループを更新するように通知されます。
- 更新されないグループはすべて削除されます。
- 削除された Microsoft 365 グループは、30 日以内であればグループの所有者または管理者が復元できます。

現在、Microsoft Entra 組織内のすべての Microsoft 365 グループに対して構成できる有効期限ポリシーは 1 つのみです。

注

Microsoft 365 グループの有効期限ポリシーを構成および使用するには、有効期限ポリシーを適用するすべてのグループのメンバーの Microsoft Entra ID P1 or P2 ライセンスを所有している必要がありますが、必ずしも割り当てる必要はありません。

Microsoft Graph PowerShell コマンドレットをダウンロードしてインストールする方法については、「[Microsoft Graph PowerShell SDK のインストール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)」を参照してください。

注

Azure AD および MSOnline PowerShell モジュールは、2024 年 3 月 30 日の時点で非推奨となります。 詳細については、[非推奨の最新情報](https://techcommunity.microsoft.com/t5/microsoft-entra-blog/important-azure-ad-graph-retirement-and-powershell-module/ba-p/3848270)を参照してください。 この日以降、これらのモジュールのサポートは、Microsoft Graph PowerShell SDK への移行支援とセキュリティ修正プログラムに限定されます。 非推奨になるモジュールは、2025 年 3 月 30 日まで引き続き機能します。

Microsoft Entra ID (旧称 Azure AD) を使用するには、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) に移行することをお勧めします。 移行に関する一般的な質問については、「[移行に関する FAQ](https://learn.microsoft.com/ja-jp/powershell/azure/active-directory/migration-faq)」を参照してください。 *注:* バージョン 1.0.x の MSOnline では、2024 年 6 月 30 日以降に中断が発生する可能性があります。

### アクティビティベースの自動更新

Microsoft Entra インテリジェンスを使うと、最近使用されたかどうかに基づいてグループが自動的に更新されるようになります。 この機能により、グループ所有者による手動アクションが不要になります。 これは、Outlook、SharePoint、Teams、Viva Engage などの Microsoft 365 サービス全体のグループでのユーザー アクティビティに基づいています。

たとえば、所有者またはグループ メンバーは、次のような操作を行います。

- Outlook でグループにメールを送信します。
- ドキュメントを SharePoint にアップロードします。
- チーム チャネルにアクセスする。
- Viva Engage で投稿を表示します。

上記のシナリオでは、グループの有効期限が切れる約 35 日前にグループが自動的に更新され、所有者は更新通知を受け取りません。

30 日間非アクティブになった後にグループの有効期限が切れる有効期限ポリシーを設定したとします。 グループの有効期限が有効化された日に有効期限のメールが送信されないようにするには (レコード アクティビティがまだ存在しないため)、まず Microsoft Entra は、5 日間待機します。 その後、以下を実行します。

- この 5 日間にアクティビティがある場合、有効期限ポリシーは期待した通り動作します。
- 5 日以内にアクティビティがない場合、Microsoft Entra ID により有効期限または更新メールが送信されます。
- グループが 5 日間非アクティブで、電子メールが送信された後、グループがアクティブだった場合は、Microsoft Entra によってグループが自動的に更新され、有効期限が再び開始されます。

#### グループの有効期限を自動的に更新するアクティビティ

次のユーザー操作により、グループの自動更新が行われます。

- **SharePoint**: ファイルの表示、編集、ダウンロード、移動、共有、またはアップロード。
- **Outlook**: グループへの参加、グループ領域からのグループ メッセージの読み取り/書き込み、メッセージへの "いいね!" の追加 (Outlook Web Access)。
- **Teams**: Teams チャネルにアクセスする。
- **Viva Engage**: Viva Engage コミュニティ内の投稿または Outlook の対話型メールを表示します。

#### 監査とレポート

管理者は、Microsoft Entra ID のアクティビティ監査ログから自動的に更新されたグループの一覧を取得できます。

[Image: アクティビティに基づくグループの自動更新を示すスクリーンショット。]

### 役割とアクセス許可

次のロールは、Microsoft Entra ID で Microsoft 365 グループの有効期限を構成して使用できます。

| ロール | 権限 |
| --- | --- |
| グループ管理者またはユーザー管理者 | Microsoft 365 グループの有効期限ポリシー設定の作成、読み取り、更新、または削除が可能です任意の Microsoft 365 グループを更新できます |
| ユーザー | 自分が所有する Microsoft 365 グループを更新できます自分が所有する Microsoft 365 グループを復元できます有効期限ポリシーの設定を読み取ることができます |

削除したグループを復元するためのアクセス許可の詳細については、[Microsoft Entra ID で削除した Microsoft 365 グループの復元](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-restore-deleted)を参照してください。

### グループの有効期限の設定

1. [グループ管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)にサインインします。
2. **アイデンティティ** を選択します。
3. **[グループ]**&gt;**[すべてのグループ]** を選択してから**[有効期限]** を選択して有効期限の設定を開きます。

    [Image: グループの有効期限の設定を示すスクリーンショット。]
4. **[有効期限]** ページで、次のことができます。

    - グループの有効期間を日数で設定する。 プリセット値またはカスタム値のいずれかを選択できます。 30 日以上である必要があります。
    - グループに所有者がいない場合に、更新と有効期限の通知を送信する電子メール アドレスを指定する。
    - 有効期限が切れる Microsoft 365 グループを選択します。 有効期限は次のように設定できます。
        - **[すべて]** の Microsoft 365 グループ。
        - **[選択済み]** の Microsoft 365 グループ。
        - **[なし]**: すべてのグループの有効期限を制限するために設定します。
    - **[保存]** を選択して完了し、設定を保存する。

    注

    - 有効期限を初めて設定すると、有効期限の間隔よりも古いグループは、グループが自動的に更新されるか所有者が更新しない限り、有効期限まで 35 日間に設定されます。
    - 動的なグループを削除し復元する場合、そのグループは新しいグループとみなされ、ルールに従って再度追加されます。 このプロセスには最大で 24 時間かかります。
    - Teams で使用されるグループの有効期限の通知は、Teams の所有者フィードに表示されます。
    - 選択したグループの有効期限を有効にすると、最大 500 のグループを一覧に追加できます。 500 を超えるグループを追加する必要がある場合は、すべてのグループの有効期限を有効にすることができます。 このシナリオでは、500 グループの制限は適用されません。
    - グループは、自動更新アクティビティが発生してもすぐには更新されません。 アクティビティが発生した場合、有効期限が近づいているときに更新の準備ができていることを示すフラグがグループに配置されます。 グループの有効期限が近づくと、更新は 24 時間以内に行われます。

### メール通知

グループが自動的に更新されない場合は、次の例のような電子メール通知が、グループの有効期限の 30 日前、15 日前、1 日前に Microsoft 365 グループ所有者に送信されます。

グループ所有者の優先言語または Microsoft Entra の言語設定によって、メールの言語が決まります。 グループの所有者が優先言語を定義している場合、または複数の所有者が同じ優先言語を持っている場合は、その言語が使用されます。 それ以外のすべての場合は、Microsoft Entra の言語設定が使用されます。

[Image: 有効期限の電子メール通知を示すスクリーンショット。]

グループの所有者は、**グループの更新**通知電子メールから、[アクセス パネル](https://account.activedirectory.windowsazure.com/r#/applications)のグループ詳細ページに直接アクセスできます。 ユーザーは、グループに関する詳細情報 (説明、最後に更新された日時、有効期限が切れた場合など) や、グループを更新する機能を取得できます。 グループの詳細ページには、現在、Microsoft 365 グループ リソースへのリンクも含まれているので、グループの所有者がそのグループの内容とアクティビティを簡単に確認できます。

重要

通知メールに問題が発生し、送信されない場合、または遅延している場合は、Microsoft は、最後の電子メールが送信される前にグループを削除することはありませんので、ご安心ください。

グループが有効期限切れになると、有効期限日の 1 日後にグループが削除されます。 次のような電子メール通知が Microsoft 365 グループの所有者に送信され、その Microsoft 365 グループの有効期限とその後の削除について通知されます。

[Image: グループ削除の電子メール通知を示すスクリーンショット。]

**グループの復元**を選択するか、PowerShell コマンドレットを 使用して、削除から 30 日以内にグループを復元できます。 詳細については、「[Microsoft Entra ID で削除された Microsoft 365 グループを復元する](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-restore-deleted)」を参照してください。 30 日間のグループの復元期間はカスタマイズできません。

復元するグループにドキュメント、SharePoint サイト、または他の永続的なオブジェクトが含まれている場合は、グループとその内容を完全に復元するまでに最大 24 時間かかることがあります。

### Microsoft 365 グループの有効期限日を取得する

アクセス パネルを使用して有効期限や最終更新日などのグループの詳細を表示するだけでなく、Graph REST API ベータ版からMicrosoft 365 グループの有効期限を取得できます。 グループ プロパティ `expirationDateTime` は、Microsoft Graph Beta で有効になっています。 これは GET 要求で取得できます。 詳細については、[この例](https://learn.microsoft.com/ja-jp/graph/api/group-get?view=graph-rest-beta&preserve-view=true#example)を参照してください。

注

アクセス パネルでグループのメンバーシップを管理するには、Microsoft Entra グループの **[全般]** 設定で、**[アクセス パネルのグループへのアクセスを制限する]** を **[いいえ]** に設定する必要があります。

### 法的ホールドがかけられたメールボックスを持つ Microsoft 365 グループの有効期限

グループの有効期限が切れてグループが削除されると、削除の 30 日後に、グループのアプリ (Planner、サイト、チームなど) のデータが完全に削除されます。 法的ホールド中のグループ メールボックスは保持され、完全には削除されません。 管理者は、Exchange コマンドレットを使用してメールボックスを復元し、データをフェッチできます。

### Microsoft 365 グループの有効期限と保持ポリシー

セキュリティ コンプライアンス ポータル内で、アイテム保持ポリシーを構成できます。 そこで、Microsoft 365 グループの保持ポリシーを設定できます。 グループの有効期限が切れてグループが削除されると、グループ メールボックス内のグループのメッセージ交換とグループ サイト内のファイルは、アイテム保持ポリシーで定義されている特定の日数の間、保持コンテナーに保持されます。 有効期限が切れると、グループまたはそのコンテンツはユーザーに表示されません。 電子情報開示を使用してサイトとメールボックスのデータを回復できます。

### PowerShell の例

PowerShell コマンドレットを使用して、Microsoft Entra 組織の Microsoft 365 グループの有効期限の設定を構成する方法の例を次に示します:

1. Microsoft Graph PowerShell モジュールをインストールし、PowerShell プロンプトでサインインします。

    ```PowerShell
    Install-Module Microsoft.Graph -Scope CurrentUser
    Connect-MgGraph -Scopes "Directory.ReadWrite.All"
    ```
2. 有効期限の設定を構成します。 [New-MgGroupLifecyclePolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/new-mggrouplifecyclepolicy) コマンドレットを使用して、Microsoft Entra 組織内のすべての Microsoft 365 グループの有効期間を 365 日に設定します。 所有者がいない Microsoft 365 グループの更新通知は、`emailaddress@contoso.com` に送信されます。

    ```PowerShell
    New-MgGroupLifecyclePolicy -AlternateNotificationEmails emailaddress@contoso.com `
       -GroupLifetimeInDays 365 -ManagedGroupTypes All
    ```
3. [Get-MgGroupLifecyclePolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/get-mggrouplifecyclepolicy) を使用して、既存のポリシーを取得します。 このコマンドレットにより、現在の構成済み Microsoft 365 グループの有効期限の設定が取得されます。

    ```powershell
    Get-MgGroupLifecyclePolicy
    ```

    この例では、次のものを確認できます。

    - ポリシー ID。
    - 所有者がいない Microsoft 365 グループの更新通知は、`emailaddress@contoso.com` に送信されます。
    - Microsoft Entra 組織内のすべての Microsoft 365 グループの有効期間が 365 日に設定されていること。

    ```output
    Id                                   AlternateNotificationEmails GroupLifetimeInDays ManagedGroupTypes
    --                                   --------------------------- ------------------- -----------------
    1aaaaaa1-2bb2-3cc3-4dd4-5eeeeeeeeee5 emailaddress@contoso.com    365                 All
    ```
4. [Update-MgGroupLifecyclePolicy を使用して](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/update-mggrouplifecyclepolicy)既存のポリシーを更新します。 このコマンドレットは、既存のポリシーの更新に使用されます。 次の例では、グループの既存ポリシーの有効期間が 365 日から 180 日に変更されます。

    ```powershell
    Update-MgGroupLifecyclePolicy -GroupLifecyclePolicyId "1aaaaaa1-2bb2-3cc3-4dd4-5eeeeeeeeee5" -GroupLifetimeInDays 180 -AlternateNotificationEmails "emailaddress@contoso.com"
    ```
5. Add-MgGroupToLifecyclePolicy[を使用](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/add-mggrouptolifecyclepolicy)して、ポリシーに特定のグループを追加します。 このコマンドレットにより、グループがライフサイクル ポリシーに追加されます。 一例として

    ```powershell
    Add-MgGroupToLifecyclePolicy -GroupLifecyclePolicyId "1aaaaaa1-2bb2-3cc3-4dd4-5eeeeeeeeee5" -GroupId "cffd97bd-6b91-4c4e-b553-6918a320211c"
    ```
6. [Remove-MgGroupLifecyclePolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/remove-mggrouplifecyclepolicy) を使用して、既存のポリシーを削除します。 このコマンドレットにより、Microsoft 365 グループの有効期限の設定が削除されます。ただし、ポリシー ID が必要です。 このコマンドレットを実行すると、Microsoft 365 グループの有効期限が無効になります。

    ```powershell
    Remove-MgGroupLifecyclePolicy -GroupLifecyclePolicyId "1aaaaaa1-2bb2-3cc3-4dd4-5eeeeeeeeee5"
    ```

次のコマンドレット使用して、ポリシーをさらに細かく構成できます。 詳細については、[Microsoft Graph PowerShell のドキュメント](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/)を参照してください。

- [Get-MgGroupLifecyclePolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/get-mggrouplifecyclepolicy)
- [New-MgGroupLifecyclePolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/new-mggrouplifecyclepolicy)（新しい Mg グループ ライフサイクル ポリシー）
- [Remove-MgGroupLifecyclePolicy グループライフサイクルポリシーを削除](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/remove-mggrouplifecyclepolicy)
- [Update-MgGroupLifecyclePolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/update-mggrouplifecyclepolicy)
- [Add-MgGroupToLifecyclePolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/add-mggrouptolifecyclepolicy)
- [Remove-MgGroupFromLifecyclePolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/remove-mggroupfromlifecyclepolicy)[（](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/remove-mggroupfromlifecyclepolicy)ライフサイクルポリシーからグループを削除する）
- [Invoke-MgRenewGroup](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/invoke-mgrenewgroup)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-members-owners-search"} -->
## グループ、メンバー、所有者の検索とフィルター (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-members-owners-search
- Service: entra-id / users
- Article date: 2025-09-04
- Summary: Microsoft Entra でグループのメンバーと所有者を検索し、フィルター処理します。

### 概要

この記事では、グループのメンバーと所有者を検索する方法と、Microsoft Entra の一部である Microsoft Entra ID で検索フィルターを使う方法について説明します。 グループの検索機能には、次のようなものがあります。

- グループ名の部分文字列検索などのグループ検索機能
- メンバーと所有者のリストに対するフィルター処理および並べ替えオプション
- メンバーと所有者のリストの検索機能

### グループ検索とソート

[**すべてのグループ**] ページで、検索文字列を入力すると、[**すべてのグループ**] ページでのみ、**包含**と**検索の開始**を切り替えることができます。

Important

Contains 検索では、リテラル部分文字列の一致ではなく、トークン化された一致が使用されます。 つまり、システムはクエリと格納された値の両方を小さなチャンク (トークン) (通常は単語または英数字セグメント) に分割し、格納されているトークンにクエリ トークンが表示されるかどうかを確認します。

部分文字列検索は単語全体でのみ行われ、特殊文字の検索は AND 検索でも行われます。 たとえば、-Name を検索すると、部分文字列 "Name" の検索と "-" の検索が開始されます。 Substring 検索では大文字と小文字が区別されます。 オブジェクト ID または mailNickname プロパティも検索されます。

[Image: 「すべてのグループ」ページでの新しい部分文字列検索のスクリーンショット。]

たとえば、"policy" を検索すると、"MDM policy – West" と "Policy group" の両方が返されます。"New\_policy" という名前のグループは返されません。 **[すべてのグループ]** リストは、昇順または降順で名前別に並べ替えることができます。

### グループ メンバーの検索とフィルター処理

#### グループ メンバーと所有者のリストを検索する

グループのメンバーまたは所有者を検索すると、トークン化された包含検索が自動的に使用されます。 たとえば、"Scott" を検索すると、Scott Wilkinson と Maya Scott の両方が返されます。

[Image: グループ メンバーと所有者のリストでの新しい部分文字列の検索のスクリーンショット。]

#### メンバーと所有者のリストをフィルター処理する

ユーザーの種類別にグループ メンバーと所有者のリストをフィルター処理することもできます。 この情報は、メンバーまたは所有者リストの **[ユーザーの種類]** 列に示されます。 リストをフィルター処理して、メンバーまたはゲストだけを表示することができます。

**[メンバー]** ページには、グループ メンバーシップを別のグループから継承したユーザーを含む、グループのすべての一意のメンバーが含まれます。

また、リストの検索とフィルター処理を個別に行うこともできます。 すべてのメンバー リストをフィルター処理しても、直接メンバー リストに適用されているフィルターには影響しません。

### グループ メンバーシップ

また、**[Group memberships]** ページでグループ メンバーシップを表示することもできます。 **[グループ メンバーシップ]** ページでは、他のグループ ページに似た検索、並べ替え、およびフィルター操作がサポートされます。

### グループ メンバー数

グループの **[概要]** ページには、グループのメンバー数が示されます。 **[概要]** ページでは、グループの直接メンバーの総数と、メンバーシップの合計数 (継承されたメンバーシップを含むグループのすべての一意のメンバー) を確認できます。

[Image: グループ メンバーシップ数の精度がより高いスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-naming-policy"} -->
## Microsoft Entra ID でグループの名前付けポリシーを適用する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-naming-policy
- Service: entra-id / users
- Article date: 2025-01-14
- Summary: Microsoft Entra ID で Microsoft 365 グループの名前付けポリシーを設定する方法について説明します。

### 概要

ユーザーが作成または編集した Microsoft 365 グループに一貫した名前付け規則を適用するには、Microsoft Entra ID で組織のグループ名前付けポリシーを設定します。 たとえば、名前付けポリシーを使用して、グループ、メンバーシップ、地理的地域の機能やグループ作成者の役割を伝えることができます。 また、名前付けポリシーを使用して、アドレス帳でグループを分類することもできます。 このポリシーを使用して、グループ名やグループ エイリアスで特定の単語の使用を禁止できます。

重要

Microsoft 365 グループに対してMicrosoft Entra ID 名前付けポリシーを使用するには、1 つ以上の Microsoft 365 グループのメンバーである一意のユーザーごとに Microsoft Entra ID P1 ライセンスまたは Microsoft Entra Basic EDU ライセンスを持っている必要がありますが、必ずしも割り当てる必要はありません。

名前付けポリシーは、編集変更が行われていない場合でも、Outlook、Microsoft Teams、SharePoint、Exchange、Planner などのワークロード全体で作成されたグループの作成または編集に適用されます。 ポリシーは、グループ名とグループ エイリアスの両方に適用されます。 Microsoft Entra ID で名前付けポリシーを設定し、既存の Exchange グループ名前付けポリシーもある場合は、組織では Microsoft Entra ID の名前付けポリシーが強制されます。

グループの名前付けポリシーが構成されると、ユーザーが作成した新しい Microsoft 365 グループにこのポリシーが適用されます。 名前付けポリシーは、グローバル 管理者 や User 管理者 など、特定のディレクトリ ロールには適用されません。 (グループの名前付けポリシーから除外されるロールの完全な一覧については、「ロールとアクセス許可」セクションを参照してください)。既存の Microsoft 365 グループの場合、構成時にポリシーはすぐには適用されません。 グループ所有者がこれらのグループのグループ名を編集すると、変更が行われていなくても、名前付けポリシーが適用されます。

### 名前付けポリシーの機能

グループの名前付けポリシーは、次の 2 つの異なる方法で適用できます。

- **プレフィックス サフィックスの名前付けポリシー**: グループに名前付け規則を適用するために自動的に追加されるプレフィックスまたはサフィックスを定義できます。 たとえば、グループ名 `GRP_JAPAN_My Group_Engineering`では、プレフィックスは `GRP_JAPAN_` 〗、サフィックスは `_Engineering`。
- **カスタムの禁止単語**: 組織に固有の禁止単語のセットをアップロードして、ユーザーが作成したグループ内でブロックすることができます。 たとえば、`Payroll,CEO,HR`を使用できます。

#### プレフィックス/サフィックス名前付けポリシー

名前付け規則の一般的な構造は、`Prefix[GroupName]Suffix` です。 複数のプレフィックスとサフィックスを定義できますが、設定で使用できる `[GroupName]` のインスタンスは 1 つに限られます。 プレフィックスまたはサフィックスは、グループを作成しているユーザーに基づいて置換される固定文字列またはユーザー属性 (`[Department]` など) のいずれかになります。 プレフィックスとサフィックスの文字列に許可される文字の総数は、グループ名を含めて 63 文字です。

プレフィックスとサフィックスには、グループ名とグループ エイリアスでサポートされている特殊文字を含めることができます。 グループ エイリアスでサポートされていないプレフィックスまたはサフィックスの文字はグループ名には適用されますが、グループ エイリアスからは削除されます。 この制限により、グループ名に適用されるプレフィックスとサフィックスは、グループ エイリアスに適用されるものとは異なる場合があります。

##### 固定文字列

文字列を使用すると、グローバル アドレス一覧やグループ ワークロードの左側のナビゲーション リンクでグループのスキャンと区別が容易になります。 一般的なプレフィックスには、`Grp_Name`、`#Name`、`_Name`などのキーワードがあります。

##### ユーザー属性

グループの作成対象となった部門、オフィス、または地理的地域の特定に役立つ属性をご利用いただけます。 たとえば、名前付けポリシーを`PrefixSuffixNamingRequirement = "GRP [GroupName] [Department]"`と`User's department = Engineering`として定義した場合、適用されるグループ名は`"GRP My Group Engineering."`になる可能性があります。サポートされる Microsoft Entra 属性は `\[Department\]`、`\[Company\]`、`\[Office\]`、`\[StateOrProvince\]`、`\[CountryOrRegion\]`、および`\[Title\]`です。 サポートされていないユーザー属性は固定文字列として扱われます。 たとえば `"\[postalCode\]"` です。 拡張属性とカスタム属性はサポートされていません。

組織内のすべてのユーザーに対して値が入力されている属性を使用し、長い値を持つ属性は使用しないようにしてください。

#### カスタム禁止単語

禁止単語リストは、グループ名とグループ エイリアスで禁止されているフレーズのコンマ区切りリストです。 サブ文字列の検索は実行されません。 エラーをトリガーするには、グループ名と 1 つ以上のカスタム禁止単語が完全に一致する必要があります。 ユーザーが「Class」などの一般的な単語を使用できるように、「lass」が禁止されている単語であっても、部分文字列検索が行われないようにします。

禁止単語リストのルールは次のとおりです。

- ブロックされた単語では大文字と小文字は区別されない。
- ユーザーがグループ名の一部として禁止単語を入力すると、その禁止単語と共にエラー メッセージが表示されます。
- 禁止単語に文字の制限はありません。
- 禁止単語リストに設定できるフレーズの上限は 5,000 フレーズです。

#### 役割とアクセス許可

名前付けポリシーを構成するには、次のいずれかのロールが必要です。

- グローバル管理者
- グループ管理者
- ディレクトリ ライター

一部の管理者ロールは、すべてのグループ ワークロードとエンドポイントでこれらのポリシーの適用を免除されるので、禁止単語を使用し、自分たちの命名規則に従ってグループを作成できます。 次の管理者ロールは、グループ命名ポリシーの適用を免除されます。

- グローバル管理者
- ユーザー管理者

### 名前付けポリシーを構成する

1. [グループ管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)にサインインします。
2. **[Microsoft Entra ID]** を選びます。
3. **すべてのグループ**&gt;**グループ** を選択し、**名前付けポリシー** を選択して **名前付けポリシー** ページを開きます。

    [Image: 管理センターで [名前付けポリシー] ページを開いている様子を示すスクリーンショット。]

#### プレフィックス/サフィックス名前付けポリシーを表示または編集する

1. **[名前付けポリシー]** ページで、 **[グループの名前付けポリシー]** を選択します。
2. 名前付けポリシーの一部として強制する属性または文字列を選択することで、現在のプレフィックスまたはサフィックス名前付けポリシーを個別に表示または編集できます。
3. プレフィックスまたはサフィックスを一覧から削除するには、プレフィックスまたはサフィックスを選択して **[削除]**を選択します。 複数の項目を同時に削除できます。
4. **[保存]** を選択して変更を保存し、新しいポリシーを有効にします。

#### カスタムのブロックされている単語を編集する

1. **[名前付けポリシー]** ページで、 **[ブロックされている単語]** を選択します。

    [Image: 名前付けポリシーのブロックされた単語リストの編集とアップロードを示すスクリーンショット。]
2. **[ダウンロード]** を選択して、現在のカスタムのブロックされている単語の一覧を表示または編集します。 新しいエントリは既存のエントリに追加する必要があります。
3. **ファイル** アイコンを選択して、カスタムのブロックされている単語の新しい一覧をアップロードします。
4. **[保存]** を選択して変更を保存し、新しいポリシーを有効にします。

### PowerShell コマンドレットのインストール

[Microsoft Graph PowerShell SDK のインストール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)に関する記事の説明に従って、Microsoft Graph コマンドレットをインストールします。

注意

Azure AD および MSOnline PowerShell モジュールは、2024 年 3 月 30 日の時点で非推奨となります。 詳細については、[非推奨の最新情報](https://techcommunity.microsoft.com/t5/microsoft-entra-blog/important-azure-ad-graph-retirement-and-powershell-module/ba-p/3848270)を参照してください。 この日以降、これらのモジュールのサポートは、Microsoft Graph PowerShell SDK への移行支援とセキュリティ修正プログラムに限定されます。 非推奨になるモジュールは、2025 年 3 月 30 日まで引き続き機能します。

Microsoft Entra ID (旧称 Azure AD) を使用するには、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) に移行することをお勧めします。 移行に関する一般的な質問については、「[移行に関する FAQ](https://learn.microsoft.com/ja-jp/powershell/azure/active-directory/migration-faq)」を参照してください。 *注:* バージョン 1.0.x の MSOnline では、2024 年 6 月 30 日以降に中断が発生する可能性があります。

1. 管理者として Windows PowerShell アプリを開きます。
2. Microsoft Graph コマンドレットをインストールします。

    ```powershell
    Install-Module Microsoft.Graph -Scope AllUsers
    ```
3. Microsoft Graph ベータ版のコマンドレットをインストールします。

    ```powershell
    Install-Module Microsoft.Graph.Beta -Scope AllUsers
    ```

### PowerShell で名前付けポリシーを構成する

1. コンピューターで Windows PowerShell ウィンドウを開きます。 管理者権限なしで開くことができます。
2. 次のコマンドを実行してコマンドレットの実行を準備します。

    ```powershell
    Connect-MgGraph -Scopes "Directory.ReadWrite.All"
    ```

    開いた **[アカウントにサインイン]** 画面で、管理者アカウントとパスワードを入力してサービスに接続します。
3. 「[グループの設定を構成するための Microsoft Entra コマンドレット](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-settings-cmdlets)」の手順に従ってこの組織のグループ設定を作成します。

#### 現在の設定を表示する

1. 現在の設定を確認するための現在の名前付けポリシーを取得します。

    ```powershell
    $Setting = Get-MgBetaDirectorySetting -DirectorySettingId (Get-MgBetaDirectorySetting | where -Property DisplayName -Value "Group.Unified" -EQ).id
    ```
2. 現在のグループ設定を表示します。

    ```powershell
    $Setting.Values
    ```

#### 名前付けポリシーとカスタム禁止単語を設定する

1. 設定を取得します。

    ```powershell
    $Setting = Get-MgBetaDirectorySetting -DirectorySettingId (Get-MgBetaDirectorySetting | where -Property DisplayName -Value "Group.Unified" -EQ).id
    ```
2. グループ名のプレフィックスとサフィックスを設定します。 機能を適切に動作させるには、設定に [`[GroupName]`] を含める必要があります。 また、制限したいカスタム単語を設定してください。

    ```powershell
    $params = @{
       values = @(
          @{
             name = "PrefixSuffixNamingRequirement"
             value = "GRP_[GroupName]_[Department]"
          }
          @{
             name = "CustomBlockedWordsList"
             value = "Payroll,CEO,HR"
          }
       )
    }
    ```
3. 次の例に示すように、設定を更新して、新しいポリシーを有効にします。

    ```powershell
    Update-MgBetaDirectorySetting -DirectorySettingId $Setting.Id -BodyParameter $params
    ```

これで終了です。 名前付けポリシーを設定し、禁止単語を追加しました。

### カスタムのブロックされている単語をエクスポートまたはインポートする

詳細については、「[グループの設定を構成するための Microsoft Entra コマンドレット](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-settings-cmdlets)」をご覧ください。

複数の禁止単語をエクスポートする PowerShell スクリプトの例を次に示します。

```powershell
$Words = (Get-MgBetaDirectorySetting).Values | where -Property Name -Value CustomBlockedWordsList -EQ 
Add-Content "c:\work\currentblockedwordslist.txt" -Value $Words.value.Split(",").Replace("`"","")
```

複数の禁止単語をインポートする PowerShell スクリプトの例を次に示します。

```powershell
$BadWords = Get-Content "C:\work\currentblockedwordslist.txt"
$BadWords = [string]::join(",", $BadWords)
$Setting = Get-MgBetaDirectorySetting | where {$_.DisplayName -eq "Group.Unified"}
if ($Setting.Count -eq 0) {
   $Template = Get-MgBetaDirectorySettingTemplate | where {$_.DisplayName -eq "Group.Unified"}
   $Params = @{ templateId = $Template.Id }
   $Setting = New-MgBetaDirectorySetting -BodyParameter $Params 
   }
$params = @{
   values = @(
      @{
         name = "PrefixSuffixNamingRequirement"
         value = "GRP_[GroupName]_[Department]"
      }
      @{
         name = "CustomBlockedWordsList"
         value = "$BadWords"
      }
   )
}
Update-MgBetaDirectorySetting -DirectorySettingId $Setting.Id -BodyParameter $params
```

### 名前付けポリシーを削除する

Azure portal または Microsoft Graph PowerShell を使用して、名前付けポリシーを削除できます。

#### Azure portal を使用して名前付けポリシーを削除する

1. **[名前付けポリシー]** ページで、**[ポリシーの削除]** を選択します。
2. 削除を確定すると、すべてのプレフィックス/サフィックス名前付けポリシーとカスタムのブロックされている単語を含め、名前付けポリシーが削除されます。

#### Microsoft Graph PowerShell を使用して名前付けポリシーを削除する

1. 設定を取得します。

    ```powershell
    $Setting = Get-MgBetaDirectorySetting -DirectorySettingId (Get-MgBetaDirectorySetting | where -Property DisplayName -Value "Group.Unified" -EQ).id
    ```
2. グループ名のプレフィックスとサフィックスを空にします。 カスタム禁止単語を空にします。

    ```powershell
    $params = @{
       values = @(
          @{
             name = "PrefixSuffixNamingRequirement"
             value = ""
          }
          @{
             name = "CustomBlockedWordsList"
             value = ""
          }
       )
    }
    ```
3. 設定を更新します。

    ```powershell
    Update-MgBetaDirectorySetting -DirectorySettingId $Setting.Id -BodyParameter $params
    ```

### Microsoft 365 アプリ全体のエクスペリエンス

Microsoft Entra ID でグループ名前付けポリシーを設定した後、ユーザーが Microsoft 365 アプリでグループを作成すると、次のようになります。

- ユーザーがグループ名を入力するとすぐに、名前付けポリシー (プレフィックスとサフィックスを含む) に従った名前のプレビューが表示されます。
- ユーザーが禁止単語を入力すると、禁止単語を削除できるようにエラー メッセージが表示されます。

| ワークロード | コンプライアンス |
| --- | --- |
| Azure portal | グループの作成または編集時にユーザーがグループ名を入力すると、Azure portal とアクセス パネル ポータルに、名前付けポリシーが適用された名前が表示されます。 ユーザーがカスタム禁止単語を入力すると、その禁止単語を含むエラー メッセージが表示されるので、ユーザーはそれを削除できます。 |
| Outlook Web Access (OWA) | ユーザーがグループ名またはグループ エイリアスを入力すると、Outlook Web Access に、名前付けポリシーが適用された名前が表示されます。 ユーザーがカスタムの禁止単語を入力すると、ユーザーが削除できるよう、UI にエラー メッセージと禁止単語が表示されます。 |
| Outlook デスクトップ | Outlook デスクトップで作成されたグループは、名前付けポリシー設定に準拠しています。 Outlook デスクトップ アプリでは、ポリシーが適用されたグループ名のプレビューはまだ表示されず、ユーザーがグループ名を入力したときにカスタム禁止単語エラーは返されません。 ただし、名前付けポリシーは、ユーザーがグループを作成または編集するときに自動的に適用されます。 グループ名またはエイリアスにカスタム禁止単語がある場合、ユーザーにエラー メッセージが表示されます。 |
| Microsoft Teams | ユーザーがチーム名を入力すると、Microsoft Teams に、グループ名前付けポリシーが適用された名前が表示されます。 ユーザーがカスタム禁止単語を入力すると、その禁止単語と共にエラー メッセージが表示されるので、ユーザーはそれを削除できます。 |
| SharePoint | ユーザーがサイト名またはグループの電子メール アドレスを入力すると、SharePoint に、名前付けポリシーが適用された名前が表示されます。 ユーザーがカスタムの禁止単語を入力すると、ユーザーが削除できるよう、禁止単語と共にエラー メッセージが表示されます。 |
| Microsoft Stream | ユーザーがグループ名またはグループの電子メール エイリアスを入力すると、Microsoft Stream に、グループ名前付けポリシーが適用された名前が表示されます。 ユーザーがカスタム禁止単語を入力すると、その禁止単語と共にエラー メッセージが表示されるので、ユーザーはそれを削除できます。 |
| Outlook iOS および Android アプリ | Outlook アプリで作成されたグループは、構成済みの名前付けポリシー設定に準拠しています。 Outlook モバイル アプリには、名前付けポリシーによって適用された名前のプレビューはまだ表示されません。 ユーザーがグループ名を入力しても、アプリはカスタムブロックワードエラーを返しません。 ただし、ユーザーが** [作成] **または **[編集]** を選択すると、名前付けポリシーが自動的に適用されます。 グループ名またはエイリアスにカスタム禁止単語がある場合、ユーザーにエラー メッセージが表示されます。 |
| Groups モバイル アプリ | Groups モバイル アプリで作成されたグループは、名前付けポリシーに準拠しています。 Groups モバイル アプリでは、名前付けポリシーのプレビューは表示されず、ユーザーがグループ名を入力したときにカスタム禁止単語エラーは返されません。 ただし、名前付けポリシーは、ユーザーがグループを作成または編集するときに自動的に適用されます。 グループ名またはエイリアスにカスタムの禁止単語がある場合、ユーザーに適切なエラーが表示されます。 |
| 計画ツール | Planner は名前付けポリシーに準拠しています。 ユーザーがプラン名を入力すると、Planner に名前付けポリシーのプレビューが表示されます。 ユーザーがカスタム禁止単語を入力すると、プランの作成時にエラー メッセージが表示されます。 |
| Web 用プロジェクト | Project for the web は、名前付けポリシーに準拠しています。 |
| Dynamics 365 for Customer Engagement | Dynamics 365 for Customer Engagemen は名前付けポリシーに準拠しています。 ユーザーがグループ名またはグループの電子メール エイリアスを入力すると、Dynamics 365 に、名前付けポリシーが適用された名前が表示されます。 ユーザーがカスタム禁止単語を入力すると、その禁止単語と共にエラー メッセージが表示されるので、ユーザーはそれを削除できます。 |
| School Data Sync (SDS) | SDS を使用して作成されたグループは名前付けポリシーに準拠していますが、名前付けポリシーが自動的に適用されるわけではありません。 SDS 管理者は、グループを作成して SDS にアップロードする必要があるクラス名にプレフィックスとサフィックスを追加する必要があります。 そうしないと、グループの作成または編集は失敗します。 |
| Classroom アプリ | Classroom アプリで作成されたグループは名前付けポリシーに準拠していますが、名前付けポリシーが自動的に適用されるわけではありません。 クラスルーム グループ名を入力しても、名前付けポリシープレビューはユーザーに表示されません。 ユーザーは、プレフィックスとサフィックスを含めた強制された教室グループ名を入力する必要があります。 そうしないと、教室グループの作成または編集操作はエラーで失敗します。 |
| Power BI | Power BI ワークスペースは、名前付けポリシーに準拠しています。 |
| Yammer | ユーザーがMicrosoft Entra アカウントで Yammer にサインインしたユーザーがグループを作成する、またはグループ名を編集する場合、グループ名は名前付けポリシーに準拠します。 これは、Microsoft 365 接続グループおよび他のすべての Yammer グループのどちらにも適用されます。名前付けポリシーが実施される前に Microsoft 365 接続グループが作成された場合、グループ名は名前付けポリシーに自動的には従いません。 ユーザーは、グループ名を編集するときに、プレフィックスとサフィックスを追加するよう求められます。 |
| StaffHub | StaffHub チームは名前付けポリシーに従いませんが、基になる Microsoft 365 グループは従います。 StaffHub チーム名にはプレフィックスとサフィックスは適用されず、カスタム禁止単語もチェックされません。 しかし、StaffHub ではプレフィックスとサフィックスが適用され、基になる Microsoft 365 グループから禁止された単語が削除されます。 |
| Exchange PowerShell | Exchange PowerShell コマンドレットは名前付けポリシーに準拠しています。 ユーザーがグループ名やグループ エイリアス (mailNickname) で名前付けポリシーに従っていない場合、推奨されるプレフィックスとサフィックスやカスタム禁止単語を示す適切なエラー メッセージが表示されます。 |
| Microsoft Graph PowerShell のコマンドレット | Microsoft PowerShell コマンドレットは名前付けポリシーに準拠しています。 ユーザーがグループ名やグループ エイリアスで名前付け規則に従っていない場合、推奨されるプレフィックスとサフィックスやカスタム禁止単語を示す適切なエラー メッセージが表示されます。 |
| Exchange 管理センター | Exchange 管理センターは名前付けポリシーに準拠しています。 ユーザーがグループ名やグループ エイリアスで名前付け規則に従っていない場合、推奨されるプレフィックスとサフィックスやカスタム禁止単語を示す適切なエラー メッセージが表示されます。 |
| Microsoft 365 管理センター | Microsoft 365 管理センターは名前付けポリシーに準拠しています。 ユーザーがグループ名を作成または編集すると、名前付けポリシーが自動的に適用されます。 ユーザーは、カスタムの禁止単語を入力すると、適切なエラーを受け取ります。 Microsoft 365 管理センターでは、名前付けポリシーのプレビューはまだ表示されず、ユーザーがグループ名を入力したときにカスタム禁止単語エラーは返されません。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-quickstart-expiration"} -->
## グループ有効期限ポリシーのクイックスタート - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-quickstart-expiration
- Service: entra-id / users
- Article date: 2025-01-15
- Summary: Microsoft 365 グループの有効期限

### 概要

このクイックスタートでは、Microsoft 365 グループの有効期限ポリシーを設定します。 ユーザーが独自のグループを設定できるようになっていると、未使用のグループが増えてしまうことがあります。 未使用のグループを管理する 1 つの方法は、それらのグループに有効期限を設定することです。グループを手動で削除するというメンテナンスの負担が軽減されます。

有効期限ポリシーは次のように単純なものです。

- ユーザー アクティビティがあるグループは、有効期限が近づくと自動的に更新されます。
- グループ所有者には、期限切れのグループを更新するように通知されます。
- 更新されていないグループは削除されます。
- 削除された Microsoft 365 グループは、グループ所有者または Microsoft Entra 管理者が 30 日以内に復元できます。

注

Microsoft Entra の一部である Microsoft Entra ID では、インテリジェンスを使用して、最近使用されたかどうかに基づいてグループを自動的に更新します。 この更新の決定は、Outlook、SharePoint、Teams、Yammer などの Microsoft 365 サービスにまたがるグループのユーザー アクティビティに基づいています。

Azure サブスクリプションをお持ちでない場合は、開始する前に[無料のアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### 前提条件

グループの有効期限を設定するために必要な最小限の特権ロールは、組織のユーザー管理者です。

### ユーザーによるグループの作成を有効にする

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にグループ管理者以上の権限でサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All groups** に移動し、[**全般**] を選択します。

    [Image: [セルフサービス グループ設定] ページのスクリーンショット。]
3. **[ユーザーは Azure portal、API、または PowerShell で Microsoft 365 グループを作成できる]** を **[はい]** に設定します。
4. 最後に **[保存]** を選択してグループの設定を保存します。

### グループの有効期限の設定

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にグループ管理者以上の権限でサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All groups**&gt;**Expiration** に移動して、有効期限の設定を開きます。

    [Image: グループの [有効期限の設定] ページのスクリーンショット。]
3. 期限切れの間隔を設定します。 プリセット値を選択するか、31 日を超えるカスタム値を入力します。
4. グループに所有者がいない場合に、有効期限の通知を送信するメール アドレスを指定します。
5. このクイックスタートでは、**[これらの Microsoft 365 グループの有効期限を有効にする]** を **[すべて]** に設定します。
6. 最後に **[保存]** を選択して有効期限の設定を保存します。

これで完了です。 このクイックスタートを通じて、選択した Microsoft 365 グループの有効期限ポリシーを正しく設定することができました。

### リソースをクリーンアップする

有効期限ポリシーを削除し、グループのユーザー作成を無効にするには、次の手順を使用します。

#### 有効期限ポリシーを削除する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にグループ管理者以上の権限でサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All groups**&gt;**Expiration** に移動します。
3. **[これらの Microsoft 365 グループの有効期限を有効にする]** を **[なし]** に設定します。

#### グループのユーザー作成を無効にする

1. **Entra ID**&gt;**グループ**&gt;**グループ設定**&gt;**一般** に移動します。
2. **を設定することで、ユーザーは Azure ポータルで Microsoft 365 グループを作成できます。** と **がありますが、**はありません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-quickstart-naming-policy"} -->
## グループの名前付けポリシーのクイックスタート - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-quickstart-naming-policy
- Service: entra-id / users
- Article date: 2024-12-16
- Summary: Microsoft Entra ID で新しいユーザーを追加する方法、既存のユーザーを削除する方法について説明します

### 概要

このクイック スタートでは、Microsoft Entra の一部である Microsoft Entra ID で、ユーザーが作成した Microsoft 365 グループの名前付けポリシーを Microsoft Entra 組織に設定して、グループの並べ替えと検索を支援します。 名前付けポリシーの使用例を次に示します。

- グループ、メンバーシップ、地理的地域の機能やグループ作成者の職務を伝える。
- アドレス帳でグループを分類しやすいようにする。
- グループ名やグループ エイリアスで特定の単語の使用を禁止する。

Azure サブスクリプションをお持ちでない場合は、開始する前に[無料](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)アカウントを作成してください。

### グループの名前付けポリシーを構成する

1. [グループ管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)にサインインします。
2. **Microsoft Entra ID** を選択します。
3. [ **グループ**&gt;**すべてのグループ**] を選択し、[ **名前付けポリシー** ] を選択して [ **名前付けポリシー** ] ページを開きます。

    [Image: 管理センターでの [名前付けポリシー] ページのスクリーンショット。]

#### プレフィックス/サフィックス名前付けポリシーを表示または編集する

1. **[名前付けポリシー]** ページで、 **[グループの名前付けポリシー]** を選択します。
2. 名前付けポリシーの一部として強制する属性または文字列を選択することで、現在のプレフィックスまたはサフィックス名前付けポリシーを個別に表示または編集できます。
3. プレフィックスまたはサフィックスを一覧から削除するには、プレフィックスまたはサフィックスを選択して **[削除]**を選択します。 複数の項目を同時に削除できます。
4. **[保存]** を選択して、ポリシーへの変更を有効にします。

#### カスタム禁止単語を表示または編集する

1. **[名前付けポリシー]** ページで、 **[ブロックされている単語]** を選択します。

    [Image: 名前付けポリシーのブロックされている単語の一覧の編集とアップロードのスクリーンショット。]
2. **[ダウンロード]** を選択して、現在のカスタムのブロックされている単語の一覧を表示または編集します。
3. ファイル アイコンを選択して、カスタムのブロックされている単語の新しい一覧をアップロードします。
4. **[保存]** を選択して、ポリシーへの変更を有効にします。

これで終了です。 名前付けポリシーを設定し、カスタムのブロックされた単語を追加しました。

### リソースをクリーンアップする

名前付けポリシーを削除するには、次の手順を使用します。

#### 名前付けポリシーを削除する

1. **[名前付けポリシー]** ページで、**[ポリシーの削除]** を選択します。
2. 削除を確定すると、すべてのプレフィックス/サフィックス名前付けポリシーとカスタムのブロックされている単語を含め、名前付けポリシーが削除されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-restore-deleted"} -->
## 削除された Microsoft 365 グループまたはクラウド セキュリティ グループを復元する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-restore-deleted
- Service: entra-id / users
- Article date: 2026-03-11
- Summary: Microsoft Entra ID で、削除されたグループを復元する方法、復元可能なグループを表示する方法、およびグループを完全に削除する方法を参照してください。

### 概要

Microsoft Entra ID で Microsoft 365 グループまたはクラウド セキュリティ グループを削除すると、削除されたグループは保持されますが、削除日から 30 日間は表示されません。 この動作は、必要に応じて、グループとその内容を復元できるようにするためです。 この機能は、Microsoft Entra ID の Microsoft 365 グループとクラウド セキュリティ グループで使用できます。 配布グループでは使用できません。 30 日間のグループの復元期間はカスタマイズできません。

グループを復元するために必要となるアクセス許可を、次の表に示します。

| 役割 | 権限 |
| --- | --- |
| グローバル管理者、グループ管理者、Partner Tier 2 サポート、Intune 管理者 | 削除された Microsoft 365 グループまたはクラウド セキュリティ グループを復元できます |
| ユーザー管理者および Partner Tier 1 サポート | グローバル管理者ロールに割り当てられているグループを除き、削除された Microsoft 365 グループまたはクラウド セキュリティ グループを復元できます |
| ユーザー | 削除された Microsoft 365 または自分が所有するクラウド セキュリティ グループを復元できます |

注

論理的な削除は、メンバーシップが割り当てられている Microsoft 365 グループ、動的メンバーシップを持つ Microsoft 365 グループ、クラウド セキュリティ グループで使用できます。 クラウド セキュリティ グループの論理的な削除と復元はプレビュー段階であり、パブリック クラウドでのみ使用できます。

Important

セキュリティ グループの論理的な削除は、次のシナリオではサポートされていません。

- OneDrive for Business (OBD) ストレージを使用する EDU テナント
- 従来の Web パーツ (すべてのテナント) を対象とする対象ユーザー

### 復元に使用できる削除された Microsoft 365 グループとクラウド セキュリティ グループを表示および管理する

1. [グループ管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)にサインインします。
2. **[Microsoft Entra ID]** を選びます。
3. **グループ**&gt;** すべてのグループ** を選択した後、**削除したグループ** を選択して、復元可能な削除されたグループを表示します。

    [Image: 復元可能なグループを表示しているスクリーンショット。]
4. **[削除されたグループ]** ウィンドウ内で、以下を実行できます。

    - **[グループの復元]** を選択して、削除されたグループとその内容を復元します。
    - **[完全に削除]** を選択して、削除されたグループを完全に削除します。 グループを完全に削除するには、自分が管理者である必要があります。

### PowerShell を使用して復元できる削除された Microsoft 365 グループとクラウド セキュリティ グループを表示する

次のコマンドレットを使用して、削除済みのグループを表示します。 関心のあるグループが完全に消去されていないことを確認する必要があります。 これらのコマンドレットは、[Microsoft Graph PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation?view=graph-powershell-1.0&preserve-view=true)に含まれています。 このモジュールの詳細については、[「Microsoft Graph PowerShell の概要」](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview?view=graph-powershell-1.0&preserve-view=true)を参照してください。

次のコマンドレットを実行して、復元可能な Microsoft Entra 組織内のすべての削除済み Microsoft 365 グループとクラウド セキュリティ グループを表示します。 マシンにまだインストールされていない場合は、[Graph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation?view=graph-powershell-1.0&preserve-view=true) ベータ版をインストールしてください。

```powershell
Install-Module Microsoft.Graph.Beta
Connect-MgGraph -Scopes "Group.ReadWrite.All"
Get-MgBetaDirectoryDeletedGroup
```

または、特定のグループのオブジェクト ID がわかっている場合 (および手順 1 のコマンドレットから取得できる場合) は、次のコマンドレットを実行します。 ユーザーは、削除された特定のグループが完全に消去されていないことを確認する必要があります。

```powershell
Get-MgBetaDirectoryDeletedGroup -DirectoryObjectId <objectId>
```

### 削除した Microsoft 365 グループまたはクラウド セキュリティ グループを復元する

グループがまだ復元可能であることを確認したら、次のいずれかの手順を実行して削除されたグループを復元します。 グループにドキュメント、SharePoint、または他の永続的なオブジェクトが含まれている場合、グループとその内容を完全に復元するまでに最大 24 時間かかることがあります。

次のコマンドレットを実行して、グループとその内容を復元します。

```powershell
Restore-MgBetaDirectoryDeletedItem -DirectoryObjectId <objectId>
```

また、次のコマンドレットを実行して、削除されたグループを完全に削除することもできます。

```powershell
Remove-MgBetaDirectoryDeletedItem -DirectoryObjectId <objectId>
```

### 復元が機能したことを、どのようにしてわかりますか？

Microsoft 365 グループまたはクラウド セキュリティ グループが正常に復元されたことを確認するには、 `Get-MgBetaGroup –GroupId <objectId>` コマンドレットを実行してグループに関する情報を表示します。 復元要求が完了すると、次のようになります。

- Exchange の左側のナビゲーション ウィンドウにグループが表示されます。
- Planner にグループのプランが表示されます。
- SharePoint サイトとそのすべてのコンテンツを利用できるようになります。
- Exchange エンドポイントと、Microsoft 365 グループをサポートする他の Microsoft 365 ワークロードからグループにアクセスできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-saasapps"} -->
## グループを使用して SaaS アプリへのアクセスを管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-saasapps
- Service: entra-id / users
- Article date: 2024-12-13
- Summary: Microsoft Entra ID のグループを使って、Microsoft Entra ID と統合された SaaS アプリケーションへのアクセスを割り当てる方法について説明します。

### 概要

Microsoft Entra ID P1 または P2 ライセンス プランで Microsoft Entra ID を使用する場合は、グループを使用して、Microsoft Entra ID と統合されたサービスとしてのソフトウェア (SaaS) アプリケーションへのアクセスを割り当てることができます。

たとえば、マーケティング部門に 5 つの異なる SaaS アプリケーションを使用するためのアクセス権を割り当てる場合は、マーケティング部門のユーザーを含む Office 365 またはセキュリティ グループを作成できます。 その後、マーケティング部門が必要とする 5 つの SaaS アプリケーションにそのグループを割り当てることができます。

Microsoft Entra ID を使用すると、マーケティング部門のメンバーシップを一元的に管理することで時間を節約することが可能です。 ユーザーは、マーケティング グループのメンバーとして追加されると、アプリケーションに割り当てられます。 マーケティンググループから削除されるとき、彼らの割り当てはアプリケーションからも削除されます。 この機能は、Microsoft Entra アプリケーション ギャラリー内から追加できる何百ものアプリケーションで利用することができます。

重要

この機能は、Microsoft Entra ID P1 または P2 試用版を開始するか、Microsoft Entra ID P1 または P2 ライセンス プランを購入した後にのみ使用できます。 グループ ベースの割り当てがサポートされるのはセキュリティ グループのみです。 現在、アプリケーションに対するグループベースの割り当てでは、入れ子になったグループ のメンバーシップはサポートされてされていません。

### ユーザーまたはグループに対して SaaS アプリケーションへのアクセス権を割り当てる

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Applications**&gt;**Enterprise アプリケーション**に移動して、アプリケーション ギャラリー内の**すべてのアプリケーション**を開きます。

    [Image: アプリケーション ギャラリーを示すスクリーンショット。]
3. アプリケーション ギャラリーから追加したアプリケーションをクリックして開きます。
4. 左側のペインで、 **[ユーザーとグループ]** を選択し、 **[ユーザー/グループの追加]** を選択します。
5. **[割り当ての追加]** で **[ユーザーとグループ]** を選択し、**[ユーザーとグループ]** 選択リストを開きます。
6. 必要な数のグループまたはユーザーを選択し、[ **選択** ] を選択して **割り当ての追加** リストに追加します。 ユーザーに対するロールの割り当ても、この段階で行います。
7. **[割り当て]** を選択し、選択したエンタープライズ アプリケーションにユーザーまたはグループを割り当てます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-self-service-management"} -->
## セルフサービス グループ管理を設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management
- Service: entra-id / users
- Article date: 2025-02-12
- Summary: Microsoft Entra ID にセキュリティ グループまたは Microsoft 365 グループを作成して管理したり、セキュリティ グループまたは Microsoft 365 グループのメンバーシップを要求したりすることができます。

### 概要

Microsoft Entra ID では、セルフサービス グループ管理機能が提供され、ユーザーが独自のセキュリティ グループまたは Microsoft 365 グループを作成して管理できます。 グループの所有者は、メンバーシップ要求を承認または拒否できます。また、グループ メンバーシップの制御を委任できます。 セルフサービスによるグループ管理機能は、[メールを有効にしたセキュリティ グループまたは配布リスト](https://learn.microsoft.com/ja-jp/entra/fundamentals/concept-learn-about-groups)では使用できません。

### セルフサービス グループ メンバーシップ

ユーザーがセキュリティ グループを作成して共有リソースへのアクセスを管理できるようにします。 ユーザーは、[Microsoft Entra 管理センター](https://entra.microsoft.com)で、PowerShell を使用して、または [マイ グループ ポータル](https://myaccount.microsoft.com/groups)からセキュリティ グループを作成できます。

[Image: [マイ グループ] ポータルを示すスクリーンショット。]

メンバーシップを更新できるのは、グループの所有者だけです。 グループ所有者に、マイ グループ ポータルからメンバーシップ要求を承認または拒否する権限を付与できます。 [マイ グループ] ポータル経由でセルフサービスによって作成されたセキュリティ グループには、所有者による承認か自動承認かにかかわらず、すべてのユーザーが参加できます。 [マイ グループ] ポータルでは、グループを作成するときにメンバーシップ オプションを変更できます。

Microsoft 365 グループは、ユーザーに共同作業の機会を提供します。 SharePoint や Microsoft Teams など、Microsoft 365 アプリケーションのいずれかでグループを作成できます。 また、Microsoft Graph PowerShell を使用して、または [マイ グループ] ポータルから、Azure portal に Microsoft 365 グループを作成することもできます。 セキュリティ グループと Microsoft 365 グループの違いの詳細については、[グループの詳細](https://learn.microsoft.com/ja-jp/entra/fundamentals/concept-learn-about-groups)に関するページを参照してください。

| グループの作成場所 | セキュリティ グループの既定の動作 | Microsoft 365 グループの既定の動作 |
| --- | --- | --- |
| [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-settings-v2-cmdlets) | メンバーを追加できるのは所有者のみです。[MyApp グループのアクセス] パネルに表示されますが、参加はできません。 | すべてのユーザーが参加できます。 |
| [Azure Portal](https://portal.azure.com) | メンバーを追加できるのは所有者のみです。[マイ グループ] ポータルに表示されますが、参加はできません。グループ 作成時に所有者は自動的に割り当てられません | すべてのユーザーが参加できます。 |
| [マイ グループ ポータル](https://myaccount.microsoft.com/groups) | ユーザーはグループを管理し、ここでグループに参加するためのアクセスを要求できます。グループの作成時に、メンバーシップ オプションを変更できます。 | すべてのユーザーが参加できます。グループの作成時に、メンバーシップ オプションを変更できます。 |

### セルフサービス グループ管理のシナリオ

セルフサービス グループ管理の説明には、2 つのシナリオが役立ちます。

#### グループ管理の委任

このシナリオ例では、管理者が会社が使用しているサービスとしてのソフトウェア (SaaS) アプリケーションへのアクセスを管理します。 そのアクセス権を管理する負担が大きいことから、この管理者はビジネス オーナーに依頼して新しいグループを作成してもらいます。 管理者はアプリケーションへのアクセス権を新しいグループに割り当て、既にアプリケーションにアクセスしているすべてのユーザーをそのグループに追加します。 これ以降はビジネス オーナーがユーザーをさらに追加できます。追加されたユーザーはアプリケーションへと自動的にプロビジョニングされます。

ビジネス オーナーは、管理者によるユーザーのアクセス管理が行われるまで待つ必要がありません。 この管理者が別のビジネス グループのマネージャーに同じアクセス許可を付与した場合、その人物も自分のグループ メンバーのアクセスを管理できるようになります。 ビジネス オーナーとマネージャーは、互いのグループ メンバーシップを表示または管理することはできません。 管理者は依然として、そのアプリケーションへのアクセス権を持ったすべてのユーザーを表示でき、必要に応じてアクセス権をブロックすることができます。

注

委任されたシナリオの場合、管理者は少なくとも[特権ロール管理者 Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) ロールを持っている必要があります。

#### セルフサービスのグループ管理

この例のシナリオでは、2 人のユーザーが別々に SharePoint Online サイトを設定しています。 この 2 人のユーザーは、自分のサイトへのアクセス権を互いのチームに付与しようとしています。 このタスクを実行するには、Microsoft Entra ID で 1 つのグループを作成します。 SharePoint Online では、それぞれのグループが選択され、サイトへのアクセスが提供されます。

だれかがアクセスを希望する場合は、[\[マイ グループ\] ポータル](https://myaccount.microsoft.com/groups)からアクセスを要求します。 承認後、両方の SharePoint Online サイトに自動的にアクセスできます。 後日、2 人のうち一方が、サイトにアクセスしているすべてのユーザーに、特定の SaaS アプリケーションへのアクセス権も付与することに決めたとします。 SaaS アプリケーションの管理者は、アプリケーションによる SharePoint Online サイトへのアクセス権を追加することができます。 それ以降は、承認されたすべての要求により、この 2 つの SharePoint Online サイトのほか、SaaS アプリケーションへのアクセス権も付与されます。

### グループをユーザーのセルフ サービスに使用できるようにする

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) としてサインインします。
2. **[Microsoft Entra ID]** を選びます。
3. **[すべてのグループ]**&gt;**[グループ]** を選択したら、**[全般]** 設定を選択します。

    注

    この設定によって制限されるのは、**[自分のグループ]** でのグループ情報のアクセスのみです。 Microsoft Graph API の呼び出しや Microsoft Entra 管理センターなど、他の方法を使ったグループ情報へのアクセスは制限されません。

    [Image: Microsoft Entra グループの [全般] 設定を示すスクリーンショット。]
4. **[所有者はアクセス パネルでのグループ メンバーシップの要求を管理できる]** を **[はい]** に設定します。
5. **[アクセス パネルのグループ機能にアクセスするユーザー機能を制限する]** を **[いいえ]** に設定します。
6. **[Users can create security groups in Azure portals, API or PowerShell](ユーザーは Azure portal、API、または PowerShell でセキュリティ グループを作成できる)** を **[はい]** または **[いいえ]** に設定します。

    この変更の詳細については、「グループ設定」を参照してください。
7. **[Users can create Microsoft 365 groups in Azure portals, API or PowerShell](ユーザーは Azure portal、API、または PowerShell で Microsoft 365 グループを作成できる)** を **[はい]** または **[いいえ]** に設定します。

    この変更の詳細については、「グループ設定」を参照してください。

また、**Azure portal でメンバーをグループの所有者として割り当てることができる所有者**を使用して、ユーザーのセルフサービスのグループ管理よりも詳細なアクセス制御を実現することもできます。

ユーザーがグループを作成できる場合、自分の組織内のすべてのユーザーが、新しいグループを作成できるようになります。 デフォルトの所有者は、これらのグループにメンバーを追加できます。 独自のグループを作成できる個人を指定することはできません。 個人を指定できるのは、別のグループ メンバーをグループの所有者にする場合のみです。

注

ユーザーがセキュリティ グループまたは Microsoft 365 グループへの参加を要求する場合、または所有者がメンバーシップ要求を承認または拒否する場合は、Microsoft Entra ID P1 または P2 ライセンスが必要です。 Microsoft Entra ID P1 または P2 ライセンスがない場合でも、ユーザーは [MyApp Groups のアクセス] パネルでグループを管理できます。 ただし、所有者の承認を必要とするグループを作成することも、グループへの参加を要求することもできません。

### グループ設定

グループ設定を使用すると、だれがセキュリティ グループや Microsoft 365 グループを作成できるかを制御できます。

[Image: Microsoft Entra セキュリティ グループの設定の変更を示すスクリーンショット。]

次の表は、選択する値を決定するのに役立ちます。

| 設定 | 値 | テナントへの影響 |
| --- | --- | --- |
| ユーザーは Azure portal、API、または PowerShell でセキュリティ グループを作成できる。 | はい | Microsoft Entra 組織内のすべてのユーザーが、Azure portal、API、または PowerShell を使って、新しいセキュリティ グループを作成し、それらのグループにメンバーを追加できます。 これらの新しいグループは、他のすべてのユーザーのアクセス パネルにも表示されます。 グループのポリシー設定で許可されている場合、他のユーザーはこれらのグループへの参加要求を作成できます。 |
|  | いいえ | ユーザーはセキュリティ グループを作成できません。 所有者であるグループのメンバーシップを引き続き管理し、他のユーザーからのグループへの参加要求を承認できます。 |
| ユーザーは Azure portal、API、または PowerShell で Microsoft 365 グループを作成できる。 | はい | Microsoft Entra 組織内のすべてのユーザーが、Azure portal、API、または PowerShell を使って、新しい Microsoft 365 グループを作成し、それらのグループにメンバーを追加できます。 これらの新しいグループは、他のすべてのユーザーのアクセス パネルにも表示されます。 グループのポリシー設定で許可されている場合、他のユーザーはこれらのグループへの参加要求を作成できます。 |
|  | いいえ | ユーザーは Microsoft 365 グループを作成できません。 所有者であるグループのメンバーシップを引き続き管理し、他のユーザーからのグループへの参加要求を承認できます。 |

これらのグループ設定に関する詳細を示します。

- これらの設定は、有効になるまでに最大 15 分かかる場合があります。
- ユーザー全員ではなく一部のユーザーがグループを作成できるようにする場合は、グループを作成できるロール ([グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)など) をそれらのユーザーに割り当てることができます。
- これらの設定はユーザー向けであり、サービス プリンシパルには影響しません。 たとえば、グループを作成するアクセス許可を備えたサービス プリンシパルがあると、これらの設定を **[いいえ]** に設定したとしても、サービス プリンシパルは引き続きグループを作成できます。

### Microsoft Graph を使用してグループ設定を構成する

Microsoft Graph を使用して **[Users can create Microsoft 365 groups in Azure portals, API or PowerShell] (ユーザーは Azure portal、API、または PowerShell で Microsoft 365 グループを作成できる)** の設定を構成するには、`EnableGroupCreation` オブジェクトの `groupSettings` オブジェクトを構成します。 詳細については、[グループ設定の概要](https://learn.microsoft.com/ja-jp/graph/group-directory-settings)に関するページを参照してください。

**「ユーザーは Azure portal、API、または PowerShell でセキュリティ グループを作成できる」** の設定を構成するには、Microsoft Graph を使用して `allowedToCreateSecurityGroups` オブジェクト内の `defaultUserRolePermissions` の  プロパティを更新します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-sensitivity-labels"} -->
## 秘密度ラベルをMicrosoft Entraセキュリティ グループに割り当てる (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-sensitivity-labels
- Service: entra-id / users
- Article date: 2026-05-01
- Summary: 一貫性のある分類とガバナンスのために、Microsoft Entra IDのクラウド セキュリティ グループに秘密度ラベルを適用する方法について説明します。

Microsoft Entra のクラウド セキュリティ グループに秘密度ラベルを適用して、Microsoft 365 グループに既に使用している分類とガバナンスを拡張します。 Microsoft Purview ポータルで発行し、グループとサイト用に構成するのと同じラベルが、個別のラベル構成を必要とせず、クラウド セキュリティ グループに自動的に適用されます。

Note

この機能は、オンプレミスの Active DirectoryまたはExchangeマネージド セキュリティ グループから同期されたセキュリティ グループには適用されません。 ラベルは、動的メンバーシップを持つセキュリティ グループでもサポートされていません。 詳細については、「 既知の制限事項」 を参照してください。

Important

この機能はプレビュー中です。 一度設定したラベルを変更できる機能や、高特権ロールに対する適用など、一部の動作は一般提供前に変更される可能性があります。 この機能を構成するには、Microsoft Entra 組織に少なくとも 1 つのアクティブな Microsoft Entra ID P1 ライセンスが必要です。

### Microsoft 365 グループのラベル付けとの主な違い

クラウド セキュリティ グループの秘密度ラベルは、Microsoft 365 グループと同じ基になるラベル インフラストラクチャを共有しますが、動作上の重要な違いがあります。

| Behavior | Microsoft 365 グループ | クラウド セキュリティ グループ |
| --- | --- | --- |
| **ラベルの変更可能性** | グループ所有者と管理者は、いつでもラベルを変更または削除できます。 | **ラベルは、** 一度適用すると変更できません。 ラベルを変更または削除することはできません。 |
| **ラベルの割り当て** | グループを作成するとき、または既存のグループにラベルを割り当てます。 | グループを作成するとき、または既存のグループにラベルを割り当てます。 子グループでグループにラベルを付ける場合は、まずすべての子グループを削除し、ラベルを適用してから、子グループを再度追加します。 |
| **メンバーシップの検証** | ラベル ポリシーに対するメンバーシップの追加を検証します。 | ラベル ポリシーに対するメンバーシップの追加を検証します。 ラベルの最初の割り当て時に、ラベル ゲスト ポリシーに対して既存のメンバーシップを検証します。 |
| **ネスト対応** | Microsoft 365 グループは入れ子をサポートしていません。 | サポートされていますが、子グループには、親グループのラベルと同じか、より制限の厳しいラベルが必要です。 詳細については、「ラベル付きグループでのネスト動作」を参照してください。 |
| **管理者/高い特権のバイパス** | 管理者はラベル ポリシーを尊重します。 | プレビュー期間中、特定のアクセス許可を持つ特定の組み込み管理者ロールとアプリ **は、ラベルの適用をバイパスできます**。 完全な一覧については、「 既知の制限事項」を参照してください。 この動作は、一般公開前に変更される可能性があります。 |

Note

メールが有効なセキュリティ グループと配布リストの秘密度ラベルはサポートされていません。

### プレビューでラベルが変更できない理由

Microsoft 365 グループは、SharePoint、Teams、Exchangeなどの共有コンテンツとワークロードにメンバーシップを適用するコラボレーション コンストラクトです。 一方、Microsoft Entra IDセキュリティ グループは承認プリミティブであり、Microsoft Entra IDおよびダウンストリームアクセス制御システムがメンバーシップを評価するセキュリティ プリンシパルです。

クラウド セキュリティ グループに適用された秘密度ラベルがゲスト アクセスを禁止する場合、**Microsoft Entra ID は評価時に有効なメンバーシップを検証する必要があります**。 有効なメンバーシップには、直接グループ メンバーと、入れ子になったグループを通じて推移的に継承されたメンバーの両方が含まれます。 検証により、グループ階層のどのレベルにもゲストが存在しないようにします。

セキュリティ グループは、条件付きアクセスのスコープ、アプリケーション アクセス、リソースのアクセス許可などの承認の決定に直接使用されます。 ラベル付けされたメンバーシップは、権利のバンドルを表します。 適用はMicrosoft Entra IDのメンバーシップの解決と検証ロジックに依存し、管理ガバナンスのプラクティスだけに依存しません。 **現在の検証プロセスは、グループの作成時またはラベルが最初に割り当てられた場合にのみ適用されます。** 他のシナリオでこれらの検証プロセスの拡張が進行中です。

Warning

Microsoft Purviewの秘密度ラベル ポリシーの変更は、新しいポリシー チェック既存のグループ メンバーシップは変更されません。 たとえば、ラベルを変更してゲスト アクセスをブロックできないようにした場合、新しいポリシーは新しくラベル付けされたグループ内のゲストをブロックし、既存のラベル付きグループへの新しいゲストの追加をブロックしますが、それらのグループに既に存在するゲストは、所有者または管理者が削除するまで残ります。

**セキュリティ グループでラベルが使用された後は、ラベルのポリシーを変更または削除しないでください。** 所有者は、割り当て時に提供される保護に基づいてラベルを選択します。 これらの保護を弱める、締め付ける、または削除すると、ラベルの現在のポリシーとグループの既存の状態が一致しない可能性があります。 Purview に関するより広範なガイダンスについては、[Microsoft Purview の秘密度ラベル](https://learn.microsoft.com/ja-jp/purview/sensitivity-labels)および [コンテナーの秘密度ラベルを有効にし、ラベルを同期する](https://learn.microsoft.com/ja-jp/purview/sensitivity-labels-teams-groups-sites) を参照してください。

### 既知の制限事項 (プレビュー)

プレビュー期間中は、次の制限事項が適用されます。

- **ラベルの不変性:** 適用後にラベルを変更または削除することはできません。 ラベルを適用する前に、慎重にラベルを選択してください。
- **高い特権のバイパス:** 次の管理者ロールとアプリケーションのアクセス許可は、メンバーを追加するときにラベル ポリシーの適用をバイパスできます。 この動作は、一般公開前に変更される可能性があります。

    - **Admin roles:** グローバル管理者、ユーザー管理者、グループ管理者、ディレクトリ ライター、Exchange管理者、SharePoint管理者、SharePoint 高度な管理管理者、Teams 管理者、Yammer 管理者、ヘルプデスク管理者、サービス サポート管理者
    - **アプリケーションのアクセス許可:**`Group.ReadWrite.All`、 `Directory.ReadWrite.All`、 `Directory.ReadWriteAdvanced.All`、 `GroupMember.ReadWrite.All`
- **入れ子になったグループのラベル付けなし:** 入れ子になったグループを含むセキュリティ グループにラベルを適用することはできません。 入れ子になったグループをすべて最初に削除し、ラベルを適用し、子グループに個別にラベルを付けてから、再度追加します。 子グループ ラベルは、親と互換性がある必要があります。
- **動的メンバーシップ グループ:** このリリースでは、動的メンバーシップを持つセキュリティ グループに秘密度ラベルを適用することはできません。 動的グループにラベルを適用できる特定のエッジ ケースがありますが、関連付けられているラベル ポリシーは適用されません。
- **オンプレミスおよびExchangeマネージド グループ:** オンプレミスの Active Directory および Exchange マネージド セキュリティ グループから同期されたセキュリティ グループはサポートされていません。
- **メールが有効なセキュリティ グループと配布リスト:** サポートされていません。
- **Microsoft 365 管理センター と My Groups:** セキュリティ グループへの秘密度ラベルの割り当ては、Microsoft 365 管理センターまたは [My Groups ポータル](https://myaccount.microsoft.com/groups)ではサポートされていません。 代わりに、Microsoft Entra 管理センター、Azure ポータル、PowerShell、またはMicrosoft Graphを使用します。

### Prerequisites

クラウド セキュリティ グループに秘密度ラベルを割り当てる前に、次の条件が満たされていることを確認します。

1. 組織には、少なくとも 1 つのアクティブな **Microsoft Entra ID P1** または P2 ライセンス (または Microsoft 365 E3/E5) があります。
2. `EnableMIPLabels`設定は、テナント ディレクトリ設定のクラウド セキュリティ グループの`True`に設定されます。
3. 秘密度ラベルは、Microsoft Purview ポータルで **Groups & Sites** スコープを有効にして公開されます。
4. ラベルは、`Execute-AzureADLabelSync` コマンドレットを使用してMicrosoft Entra IDに同期されます。 ラベルが使用可能になるまでに、同期後最大 24 時間かかる場合があります。
5. [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)は、この記事のディレクトリ設定コマンドレットとグループ管理コマンドレット用にインストールされています。
6. [セキュリティとコンプライアンスの PowerShell](https://learn.microsoft.com/ja-jp/powershell/exchange/connect-to-scc-powershell) がインストールされ、接続されています。  コマンドレットは、Microsoft Graph PowerShell SDK ではなく、Security & Compliance PowerShell でのみ使用できます。

### PowerShell でセンシティビティ・ラベルのサポートを有効にする

クラウド セキュリティ グループに秘密度ラベルを適用するには、テナント レベルのディレクトリ設定を作成または更新して、この機能を有効にする必要があります。 クラウド セキュリティ グループでは、`Group.Security` 設定テンプレートが使用されます。これは、Microsoft 365 グループに使用される `Group.Unified` テンプレートとは別です。

Note

Microsoft 365 グループの秘密度ラベルを既に有効にしている場合でも、クラウド セキュリティ グループに対して有効にするには、これらの手順を完了する必要があります。 2 つのグループの種類では、個別のディレクトリ設定テンプレートを使用します。

1. PowerShell プロンプトを開き、コマンドレットを実行するために必要な Graph モジュールをインストールします。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    Install-Module Microsoft.Graph.Beta -Scope CurrentUser
    ```
2. テナントに接続します。

    ```powershell
    Connect-MgGraph -Scopes "Directory.ReadWrite.All"
    ```
3. クラウド セキュリティ グループのテナント レベルのディレクトリ設定を作成します。 `Group.Security` テンプレート ID を使用します。

    ```powershell
    $params = @{
        templateId = "d209f6fa-3839-4d70-b83f-60b1c64d0e8f"
        values = @(
            @{
                name = "AllowToAddGuests"
                value = "True"
            }
            @{
                name = "EnableMIPLabels"
                value = "True"
            }
        )
    }
    New-MgBetaDirectorySetting -BodyParameter $params
    ```
4. 設定が作成されたことを確認します。

    ```powershell
    (Get-MgBetaDirectorySetting | Where-Object {
        $_.DisplayName -eq "Group.Security"
    }).Values
    ```

    出力には、`EnableMIPLabels` が `True` に設定されていることが表示されます。

**設定が既に存在し、それらを更新する必要がある場合:**

```powershell
$Setting = Get-MgBetaDirectorySetting -Search DisplayName:"Group.Security"
$params = @{
    Values = @(
        @{
            Name = "EnableMIPLabels"
            Value = "True"
        }
    )
}
Update-MgBetaDirectorySetting -DirectorySettingId $Setting.Id `
    -BodyParameter $params
```

`Request_BadRequest` エラーが発生した場合、設定は既に存在します。 `Get-MgBetaDirectorySetting | Format-List`を使用して正しい設定 ID を見つけ、その ID で `Update-MgBetaDirectorySetting` コマンドレットを発行します。

また、秘密度ラベルを Microsoft Entra ID に同期する必要もあります。 手順については、Purview ドキュメントの [コンテナーの秘密度ラベルを有効にし、ラベルを同期](https://learn.microsoft.com/ja-jp/purview/sensitivity-labels-teams-groups-sites#how-to-enable-sensitivity-labels-for-containers-and-synchronize-labels) するを参照してください。

### Microsoft Entra 管理センターの新しいセキュリティ グループにラベルを割り当てる

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくともグループ管理者としてサインインします。
2. **Microsoft Entra ID** を選択します。
3. **[グループ]**&gt;**[すべてのグループ]**&gt;**[新しいグループ]** の順に選択します。
4. [新しいグループ] ページで、グループの種類として **[セキュリティ** ] を選択します。
5. 必要な情報を入力し、[秘密度ラベル] ドロップダウンから **秘密度ラベル** を選択します。

    Warning

    秘密度ラベルを適用してグループを作成すると、ラベルを変更または削除することはできません。 先に進む前に、ラベルが目的のアクセスポリシーと使用ポリシーと一致することを確認します。 この動作は、一般公開前に変更される可能性があります。
6. 必要に応じて所有者とメンバーを追加します。 選択したラベルにゲスト アクセスをブロックするポリシーが含まれている場合、ゲスト メンバーを追加することはできません。
7. [ **作成]** を選択して変更を保存します。

グループが作成され、選択したラベルに関連付けられているメンバーシップ制限が適用されます。

### Microsoft Entra 管理センター内の既存のセキュリティ グループにラベルを割り当てる

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくともグループ管理者としてサインインします。
2. **Microsoft Entra ID** を選択します。
3. [ **グループ**&gt;**すべてのグループ**] を選択し、ラベルを付けるグループを選択します。
4. グループのページで、[プロパティ] を選択 **します**。
5. [秘密度ラベル] ドロップダウンから **秘密度ラベルを** 選択します。

    Warning

    ラベルを適用すると、ラベルを変更または削除することはできません。 グループの現在のメンバーシップが選択したラベルのポリシーと競合する場合 (たとえば、グループにゲストが含まれており、ラベルによってゲスト アクセスがブロックされる)、保存操作は失敗します。 ラベルを適用する前に競合を解決します。
6. **[保存]** を選択して変更を保存します。

### PowerShell または Microsoft Graph を使用してラベルを割り当てる

ラベルとラベル付きグループ メンバーシップをプログラムで管理するには、Microsoft Graphベータ エンドポイントを呼び出す次の PowerShell スニペットを使用します。

#### 新しいセキュリティ グループにラベルを適用する

```powershell
$param = @{
    description = "Your Group Description"
    displayName = "Your Group Name"
    mailEnabled = $false
    securityEnabled = $true
    mailNickname = "YourGroupNickName"
    assignedLabels = @(
        @{ "LabelId" = "<labelID>" }
    )
}
New-MgBetaGroup @param
```

使用可能なラベル ID を取得するには、[List sensitivityLabels](https://learn.microsoft.com/ja-jp/graph/api/security-informationprotection-list-sensitivitylabels) Microsoft Graph APIを使用します。

#### 既存のセキュリティ グループにラベルを適用する

```powershell
$assignedLabels = @(
    @{ "LabelId" = "<labelID>" }
)
Update-MgBetaGroup -GroupId <groupId> -AssignedLabels $assignedLabels
```

#### ラベル付きセキュリティ グループにメンバーを追加する

```powershell
$userUPN = "user1@domain.com"
$user = Get-MgBetaUser -UserId $userUPN
$odataID = "https://graph.microsoft.com/v1.0/directoryObjects/" + $user.Id
New-MgBetaGroupMemberByRef -GroupId <groupId> -OdataId $odataID
```

ラベル付きグループにメンバーを追加すると、Microsoft Entra ID新しいメンバーがラベルの制限を満たしていることを確認します。 メンバーが制限を満たしていない場合 (ゲストなしポリシーを使用してグループにゲストを追加しようとした場合など)、操作はブロックされ、エラーが返されます。

### ラベル付きグループにおけるネスト動作

入れ子になったグループと秘密度ラベルを使用する場合は、次の規則が適用されます。

- **現在入れ子になったグループを含むグループにラベルを付けることはできません。** 親グループにラベルを付ける場合は、入れ子になった (子) グループをすべて最初に削除し、そのラベルを親に適用してから、子グループを再度追加します。
- **子グループには互換性のあるラベルが必要です。** ラベル付き親に子グループを追加し直す場合、子グループのラベルは、少なくとも親のラベルと同じ制限を持つ必要があります。 制限の緩いラベル (優先順位が低い) の子グループを、より制限の厳しい親グループのメンバーとして追加することはできません。
- **ラベルのないグループを、ラベル付きの親グループの配下にネストすることはできません。** 親グループにラベルがある場合は、その子グループを追加する前に、互換性のあるラベルですべての子グループにラベルを付ける必要があります。

#### 入れ子グループを含む親グループにラベルを付ける手順

1. 親グループから入れ子になったグループをすべて削除します。
2. 目的のラベルを親グループに適用します。
3. 各子グループにラベルを適用します (ラベルは、親のラベル以上の制限が必要です)。
4. ラベル付けされた子グループを親グループに追加し直します。

### Troubleshooting

#### 秘密度ラベルをセキュリティ グループに割り当てることはできません

クラウド セキュリティ グループの秘密度ラベル オプションは、次のすべての条件が満たされている場合にのみ表示されます。

1. 組織には、アクティブな Microsoft Entra ID P1 ライセンスがあります。
2. `EnableMIPLabels`は、`True` ディレクトリ設定で`Group.Security`に設定されます。
3. 秘密度ラベルは、この Microsoft Entra 組織の Microsoft Purview ポータルで発行されています。
4. ラベルは、 コマンドレットを使用して Microsoft Entra ID と同期されます（Security & Compliance PowerShell から実行）。 ラベルが使用可能になるまでに、同期後最大 24 時間かかる場合があります。
5. 秘密度ラベルのスコープは、**グループとサイト**用に構成されています。
6. グループは、メンバーシップの種類が **割り当てられた** クラウド セキュリティ グループです (動的ではありません)。
7. 現在サインインしているユーザーには、秘密度ラベル (グループ所有者または少なくともグループ管理者) を割り当てるための十分な特権があり、秘密度ラベル発行ポリシーのスコープ内にあります。

#### メンバーシップの競合が原因でラベルの割り当てが失敗する

ポリシーがグループの現在のメンバーシップと競合するラベルを適用しようとすると、操作はエラーで失敗します。 一般的なシナリオは次のとおりです。

- **ゲスト メンバーを含むグループへのゲスト アクセスをブロックするラベルを適用する。** 解決策: ゲスト メンバーを削除し、ラベルを適用します。
- **入れ子になったグループを含むグループにラベルを適用する。** 解決策: 入れ子になったグループをすべて削除し、ラベルを適用し、子グループにラベルを付けてから、それらを再度追加します。

#### ラベルの競合が原因でメンバーの追加が失敗する

ラベル ポリシーがグループの現在のメンバーシップと競合するクラウド セキュリティ グループにメンバー (ゲストまたは入れ子になったグループ) を追加しようとすると、操作はエラーで失敗します。 一般的なシナリオは次のとおりです。

- **ゲスト アクセスをブロックするラベルを持つグループにゲスト メンバーを追加する。** 解決策: ゲスト アクセスが許可されていないラベルを持つグループにゲストを追加することはできません。
- **ラベル付けされていない入れ子になったグループをラベル付けされた親グループに追加する。** 解決策: 親グループのラベルと同じか、より制限の厳しい入れ子になったグループにラベルを適用し、入れ子になったグループを追加します。
- **制限の緩いラベルを持つ入れ子になったグループを親グループに追加する。** 解決策: 入れ子になったグループには、親グループのラベルと同じか、より制限の厳しいラベルが必要です。 入れ子になったグループに制限の緩いラベルがある場合は、追加できません。

#### ラベルを変更または削除することはできません

プレビュー期間中は、クラウド セキュリティ グループの秘密度ラベルを変更することはできません。 ラベルを適用した後、ラベルを変更または削除することはできません。 ラベルを変更するには、適切なラベルを持つ新しいセキュリティ グループを作成し、新しいグループにメンバーを再追加します。

#### 既知の問題: PowerShell で assignedLabels が返されない

PowerShell を使用してグループにクエリを実行するときに、 `assignedLabels` プロパティが設定されないという既知の問題があります。 回避策として、Microsoft Graph エクスプローラーまたはGraph APIを直接使用します。

```http
GET https://graph.microsoft.com/v1.0/groups/{groupId}?$select=assignedLabels
```

または

```http
GET https://graph.microsoft.com/v1.0/groups/{groupId}/assignedlabels
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-settings-cmdlets"} -->
## PowerShell を使用してグループ設定を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-settings-cmdlets
- Service: entra-id / users
- Article date: 2025-06-05
- Summary: Microsoft Entra コマンドレットを使用してグループの設定を管理する方法

### 概要

この記事では、PowerShell コマンドレットを使用して、Microsoft Entra ID (Microsoft Entra の一部) でグループを作成および更新する手順について説明します。 このコンテンツは、Microsoft 365 グループにのみ適用されます。

重要

一部の設定では、Microsoft Entra ID P1 ライセンスが必要です。 詳細については、テンプレートの設定 表を参照してください。

管理者以外のユーザーがセキュリティ グループを作成できないようにする方法の詳細については、「`AllowedToCreateSecurityGroups`で説明されているように、 プロパティを False に設定します。

Microsoft 365 グループの設定は、Settings オブジェクトと SettingsTemplate オブジェクトを使用して構成されます。 ディレクトリは既定の設定で構成されているため、最初はディレクトリに Settings オブジェクトが表示されません。 既定の設定を変更するには、設定テンプレートを使用して新しい設定オブジェクトを作成する必要があります。 Microsoft には、いくつかの設定テンプレートが用意されています。 ディレクトリの Microsoft 365 グループ設定を構成するには、"Group.Unified" という名前のテンプレートを使用します。 1 つのグループで Microsoft 365 グループ設定を構成するには、"Group.Unified.Guest" という名前のテンプレートを使用します。 このテンプレートは、Microsoft 365 グループへのゲスト アクセスを管理するために使用されます。

コマンドレットは、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/) モジュールの一部です。 モジュールをダウンロードしてコンピューターにインストールする方法については、「[Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)をインストールする」を参照してください。

手記

Microsoft 365 グループにゲストを追加できないように制限が有効になっている場合でも、管理者はゲスト ユーザーを追加できます。 この制限は、管理者以外のユーザーにのみ適用されます。

### PowerShell コマンドレットをインストールする

「[Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)のインストール」の説明に従って、Microsoft Graph コマンドレットをインストールします。

1. 管理者として Windows PowerShell アプリを開きます。
2. Microsoft Graph コマンドレットをインストールします。

    ```powershell
    Install-Module Microsoft.Graph -Scope AllUsers
    ```
3. Microsoft Graph ベータ版コマンドレットをインストールします。

    ```powershell
    Install-Module Microsoft.Graph.Beta -Scope AllUsers
    ```

### Microsoft Graph への接続

この記事の `Get-Mg*`、 `New-Mg*`、または `Update-Mg*` コマンドレットを実行する前に、まず Microsoft Graph に対して認証を行う必要があります。

#### サインイン

```PowerShell
Connect-MgGraph -Scopes "Directory.ReadWrite.All"
```

サインインし、必要なアクセス許可に同意するように求められます。

1. 接続を確認する

    ```powershell
    Get-MgContext
    ```

    このコマンドは、サインインしていることを確認し、現在選択されている Microsoft Graph プロファイルを表示します。
2. この記事のいくつかの例では、Microsoft Graph ベータ API を使用します。 プロファイルを切り替えるには:

    ```powershell
    Select-MgProfile -Name "beta"
    ```

手記

`Connect-MgGraph`を実行しない場合、このドキュメントのすべての`Get-Mg*`、`New-Mg*`、および`Update-Mg*`コマンドは失敗します。

### ディレクトリ レベルで設定を作成する

これらの手順では、ディレクトリ レベルで設定を作成します。これは、ディレクトリ内のすべての Microsoft 365 グループに適用されます。

1. DirectorySettings コマンドレットでは、使用する SettingsTemplate の ID を指定する必要があります。 この ID がわからない場合、このコマンドレットはすべての設定テンプレートの一覧を返します。

    ```powershell
    Get-MgBetaDirectorySettingTemplate
    ```

    このコマンドレット呼び出しは、使用可能なすべてのテンプレートを返します。

    ```output
    Id                                   DisplayName         Description
    --                                   -----------         -----------
    62375ab9-6b52-47ed-826b-58e47e0e304b Group.Unified       ...
    08d542b9-071f-4e16-94b0-74abb372e3d9 Group.Unified.Guest Settings for a specific Microsoft 365 group
    16933506-8a8d-4f0d-ad58-e1db05a5b929 Company.BuiltIn     Setting templates define the different settings that can be used for the associ...
    4bc7f740-180e-4586-adb6-38b2e9024e6b Application...
    898f1161-d651-43d1-805c-3b0b388a9fc2 Custom Policy       Settings ...
    5cf42378-d67d-4f36-ba46-e8b86229381d Password Rule       Settings ...
    ```
2. 使用ガイドライン URL を追加するには、まず、使用ガイドラインの URL 値を定義する SettingsTemplate オブジェクトを取得する必要があります。つまり、Group.Unified テンプレート:

    ```powershell
    $TemplateId = (Get-MgBetaDirectorySettingTemplate | where { $_.DisplayName -eq "Group.Unified" }).Id
    $Template = Get-MgBetaDirectorySettingTemplate | where -Property Id -Value $TemplateId -EQ
    ```
3. ディレクトリ設定に使用する値を含むオブジェクトを作成します。 これらの値により、使用ガイドラインの値が変更され、秘密度ラベルが有効になります。 必要に応じて、テンプレートで次またはその他の設定を設定します。

    ```powershell
    $params = @{
       templateId = "$TemplateId"
       values = @(
          @{
             name = "UsageGuidelinesUrl"
             value = "https://guideline.example.com"
          }
          @{
             name = "EnableMIPLabels"
             value = "True"
          }
       )
    }
    ```
4. [New-MgBetaDirectorySetting](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.identity.directorymanagement/new-mgbetadirectorysetting)を使用して、ディレクトリ設定を作成します。

    ```powershell
    New-MgBetaDirectorySetting -BodyParameter $params
    ```
5. 次のコマンドを使用して値を読み取ることができます。

    ```powershell
    $Setting = Get-MgBetaDirectorySetting | where { $_.DisplayName -eq "Group.Unified"}
    $Setting.Values
    ```

### ディレクトリ レベルで設定を更新する

設定テンプレートの UsageGuideLinesUrl の値を更新するには、Microsoft Entra ID から現在の設定を読み取ります。そうしないと、UsageGuideLinesUrl 以外の既存の設定が上書きされる可能性があります。

1. Group.Unified SettingsTemplate から現在の設定を取得します。

    ```powershell
    $Setting = Get-MgBetaDirectorySetting | where { $_.DisplayName -eq "Group.Unified"}
    ```
2. 現在の設定を確認します。

    ```powershell
    $Setting.Values
    ```

    このコマンドは、次の値を返します。

    ```output
    Name                            Value
    ----                            -----
    EnableMIPLabels                 True
    CustomBlockedWordsList
    EnableMSStandardBlockedWords    False
    ClassificationDescriptions
    DefaultClassification
    PrefixSuffixNamingRequirement
    AllowGuestsToBeGroupOwner       False
    AllowGuestsToAccessGroups       True
    GuestUsageGuidelinesUrl
    GroupCreationAllowedGroupId
    AllowToAddGuests                True
    UsageGuidelinesUrl              https://guideline.example.com
    ClassificationList
    EnableGroupCreation             True
    NewUnifiedGroupWritebackDefault True
    ```
3. UsageGuideLinesUrl の値を削除するには、URL を空の文字列に編集します。

    ```powershell
    $params = @{
       Values = @(
          @{
             Name = "UsageGuidelinesUrl"
             Value = ""
          }
       )
    }
    ```
4. [Update-MgBetaDirectorySetting](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.identity.directorymanagement/update-mgbetadirectorysetting) コマンドレットを使用して値を更新します。

    ```powershell
    Update-MgBetaDirectorySetting -DirectorySettingId $Setting.Id -BodyParameter $params
    ```

### テンプレートの設定

`Group.Unified SettingsTemplate`で定義されている設定を次に示します。 特に明記されていない限り、これらの機能には Microsoft Entra ID P1 ライセンスが必要です。

| **設定** | **説明** |
| --- | --- |
| **EnableGroupCreation**次のコマンドを入力します: `Boolean`既定値: `True` | このフラグは、管理者以外のユーザーがディレクトリに Microsoft 365 グループを作成できるかどうかを示します。 この設定では、Microsoft Entra ID P1 ライセンスは必要ありません。 |
| **グループ作成許可グループID**次のコマンドを入力します: `String`既定値: `""` | `EnableGroupCreation == false`場合でも、メンバーが Microsoft 365 グループの作成を許可されているセキュリティ グループの GUID。 |
| **使用ガイドラインURL**次のコマンドを入力します: `String`既定値: `""` | グループ使用ガイドラインへのリンク。 |
| **分類の説明**次のコマンドを入力します: `String`既定値: `""` | 分類に関する説明のコンマ区切りリスト。 `ClassificationDescriptions` の値は、次の形式でのみ有効です。`$setting["ClassificationDescriptions"] ="Classification:Description,Classification:Description"`ここで、分類は ClassificationList のエントリと一致します。この設定は、`EnableMIPLabels == True`場合には適用されません。プロパティ `ClassificationDescriptions` の文字制限は 300 で、コンマはエスケープできません。 |
| **デフォルト分類**次のコマンドを入力します: `String`既定値: `""` | 何も指定されていない場合にグループの既定の分類として使用される分類。この設定は、`EnableMIPLabels == True`場合には適用されません。 この値は、同じ呼び出しで `ClassificationList` が設定されている場合にのみ設定できます。 |
| **プレフィックスおよびサフィックスのネーミング要件**次のコマンドを入力します: `String`既定値: `""` | Microsoft 365 グループ用に構成された名前付け規則を定義する最大長 64 文字の文字列。 詳細については、「Microsoft 365 グループの名前付けポリシーを適用する」を参照してください。 |
| **CustomBlockedWordsList (英語)**次のコマンドを入力します: `String`既定値: `""` | ユーザーがグループ名またはエイリアスで使用できない語句のコンマ区切りの文字列。 詳細については、「Microsoft 365 グループの名前付けポリシーを適用する」を参照してください。 |
| **MS標準ブロックワードを有効にする**次のコマンドを入力します: `Boolean`既定値: `False` | 廃止。 使用しないでください。 |
| **ゲストがグループオーナーになれるようにする**次のコマンドを入力します: `Boolean`既定値: `False` | ゲスト ユーザーをグループの所有者にできるかどうかを示すブール値。 |
| **AllowGuestsToAccessGroups (ゲストによるグループへのアクセスを許可)**次のコマンドを入力します: `Boolean`既定値: `True` | ゲスト ユーザーが Microsoft 365 グループのコンテンツにアクセスできるかどうかを示すブール値。 この設定では、Microsoft Entra ID P1 ライセンスは必要ありません。 |
| **GuestUsageGuidelinesUrl**次のコマンドを入力します: `String`既定値: `""` | ゲストの使用ガイドラインへのリンクの URL。 |
| **ゲストの追加を許可**次のコマンドを入力します: `Boolean`既定値: `True` | ゲストをこのディレクトリに追加できるかどうかを示すブール値。 `EnableMIPLabels`が *True* に設定され、ゲスト ポリシーがグループに割り当てられている秘密度ラベルに関連付けられている場合、この設定はオーバーライドされ、読み取り専用になる可能性があります。`AllowToAddGuests` 設定が組織レベルで False に設定されている場合、グループ レベルの `AllowToAddGuests` 設定は無視されます。 少数のグループに対してのみゲスト アクセスを有効にする場合は、`AllowToAddGuests` を組織レベルで true に設定し、特定のグループに対して選択的に無効にする必要があります。 |
| **分類リスト**次のコマンドを入力します: `String`既定値: `""` | Microsoft 365 グループに適用できる有効な分類値のコンマ区切りの一覧。 EnableMIPLabels == True の場合、この設定は適用されません。 |
| **EnableMIPLabels**次のコマンドを入力します: `Boolean`既定値: `False` | Microsoft Purview ポータルで発行された秘密度ラベルを Microsoft 365 グループに適用できるかどうかを示すフラグ。 詳細については、「[Microsoft 365 グループ](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-assign-sensitivity-labels)の秘密度ラベルを割り当てる」を参照してください。 |
| **NewUnifiedGroupWritebackDefault**次のコマンドを入力します: `Boolean`既定値: `True` | 管理者が要求ペイロードで groupWritebackConfiguration リソースの種類を設定せずに新しい Microsoft 365 グループを作成できるようにするフラグ。 この設定は、Microsoft Entra Connect でグループ ライトバックが構成されている場合に適用されます。 `NewUnifiedGroupWritebackDefault` は、グローバルな Microsoft 365 グループ設定です。 既定値は true です。 設定値を false に更新すると、新しく作成された Microsoft 365 グループの既定の書き戻し動作が変更され、既存の Microsoft 365 グループの **isEnabled** プロパティ値は変更されません。 グループ管理者は、既存の Microsoft 365 グループの書き戻し状態を変更するために、グループ isEnabled プロパティ値を明示的に更新する必要があります。 |

### 例: ディレクトリ レベルでグループのゲスト ポリシーを構成する

1. すべての設定テンプレートを取得します。

    ```powershell
    Get-MgBetaDirectorySettingTemplate
    ```
2. ディレクトリ レベルでグループのゲスト ポリシーを設定するには、Group.Unified テンプレートが必要です。

    ```powershell
    $Template = Get-MgBetaDirectorySettingTemplate | where -Property Id -Value "62375ab9-6b52-47ed-826b-58e47e0e304b" -EQ
    ```
3. 指定したテンプレートの AllowToAddGuests の値を設定します。

    ```powershell
    $params = @{
       templateId = "62375ab9-6b52-47ed-826b-58e47e0e304b"
       values = @(
          @{
             name = "AllowToAddGuests"
             value = "False"
          }
       )
    }
    ```
4. 次に、[New-MgBetaDirectorySetting](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.identity.directorymanagement/new-mgbetadirectorysetting) コマンドレットを使用して、新しい設定オブジェクトを作成します。

    ```powershell
    $Setting = New-MgBetaDirectorySetting -BodyParameter $params
    ```
5. 値は次の方法で読み取ることができます。

    ```powershell
    $Setting.Values
    ```

### ディレクトリ レベルでの設定の読み取り

取得する設定の名前がわかっている場合は、次のコマンドレットを使用して現在の設定値を取得できます。 この例では、 `UsageGuidelinesUrl` という名前の設定の値が取得されます。

```powershell
(Get-MgBetaDirectorySetting).Values | where -Property Name -Value UsageGuidelinesUrl -EQ
```

これらの手順では、ディレクトリ レベルで設定を読み取ります。これは、ディレクトリ内のすべての Microsoft 365 グループに適用されます。

1. 既存のすべてのディレクトリ設定を読み取ります。

    ```powershell
    Get-MgBetaDirectorySetting -All
    ```

    このコマンドレットは、すべてのディレクトリ設定の一覧を返します。

    ```output
    Id                                   DisplayName   TemplateId                           Values
    --                                   -----------   ----------                           ------
    c391b57d-5783-4c53-9236-cefb5c6ef323 Group.Unified 62375ab9-6b52-47ed-826b-58e47e0e304b {class SettingValue {...
    ```
2. 特定のグループのすべての設定を読み取ります。

    ```powershell
    Get-MgBetaGroupSetting -GroupId "ab6a3887-776a-4db7-9da4-ea2b0d63c504"
    ```
3. 設定 ID GUID を使用して、特定のディレクトリ設定オブジェクトのすべてのディレクトリ設定値を読み取ります。

    ```powershell
    (Get-MgBetaDirectorySetting -DirectorySettingId "c391b57d-5783-4c53-9236-cefb5c6ef323").values
    ```

    このコマンドレットは、この特定のグループのこの設定オブジェクトの名前と値を返します。

    ```output
    Name                          Value
    ----                          -----
    ClassificationDescriptions
    DefaultClassification
    PrefixSuffixNamingRequirement
    CustomBlockedWordsList        
    AllowGuestsToBeGroupOwner     False 
    AllowGuestsToAccessGroups     True
    GuestUsageGuidelinesUrl
    GroupCreationAllowedGroupId
    AllowToAddGuests              True
    UsageGuidelinesUrl            https://guideline.example.com
    ClassificationList
    EnableGroupCreation           True
    ```

### ディレクトリ レベルで設定を削除する

この手順では、ディレクトリ レベルの設定を削除します。これは、ディレクトリ内のすべての Microsoft 365 グループに適用されます。

```powershell
Remove-MgBetaDirectorySetting –DirectorySettingId "c391b57d-5783-4c53-9236-cefb5c6ef323c"
```

### 特定のグループの設定を作成する

1. 設定テンプレートを取得します。

    ```powershell
    Get-MgBetaDirectorySettingTemplate
    ```
2. 結果で、"Groups.Unified.Guest" という名前の設定テンプレートを探します。

    ```output
    Id                                   DisplayName            Description
    --                                   -----------            -----------
    62375ab9-6b52-47ed-826b-58e47e0e304b Group.Unified          ...
    08d542b9-071f-4e16-94b0-74abb372e3d9 Group.Unified.Guest    Settings for a specific Microsoft 365 group
    4bc7f740-180e-4586-adb6-38b2e9024e6b Application            ...
    898f1161-d651-43d1-805c-3b0b388a9fc2 Custom Policy Settings ...
    5cf42378-d67d-4f36-ba46-e8b86229381d Password Rule Settings ...
    ```
3. Groups.Unified.Guest テンプレートのテンプレート オブジェクトを取得します。

    ```powershell
    $Template1 = Get-MgBetaDirectorySettingTemplate | where -Property Id -Value "08d542b9-071f-4e16-94b0-74abb372e3d9" -EQ
    ```
4. この設定を適用するグループの ID を取得します。

    ```powershell
    $GroupId = (Get-MgGroup -Filter "DisplayName eq '<YourGroupName>'").Id
    ```
5. 新しい設定を作成します。

    ```powershell
    $params = @{
       templateId = "08d542b9-071f-4e16-94b0-74abb372e3d9"
       values = @(
          @{
             name = "AllowToAddGuests"
             value = "False"
          }
       )
    }
    ```
6. グループ設定を作成します。

    ```powershell
    New-MgBetaGroupSetting -GroupId $GroupId -BodyParameter $params
    ```
7. 設定を確認するには、次のコマンドを実行します。

    ```powershell
    Get-MgBetaGroupSetting -GroupId $GroupId | FL Values
    ```

### 特定のグループの設定を更新する

1. 設定を更新するグループの ID を取得します。

    ```powershell
    $groupId = (Get-MgGroup -Filter "DisplayName eq '<YourGroupName>'").Id
    ```
2. グループの設定を取得します。

    ```powershell
    $Setting = Get-MgBetaGroupSetting -GroupId $GroupId
    ```
3. 必要に応じて、グループの設定を更新します。

    ```powershell
    $params = @{
       values = @(
          @{
             name = "AllowToAddGuests"
             value = "True"
          }
       )
    }
    ```
4. その後、この設定の新しい値を設定できます。

    ```powershell
    Update-MgBetaGroupSetting -DirectorySettingId $Setting.Id -GroupId $GroupId -BodyParameter $params
    ```
5. 設定の値を読み取って、正しく更新されていることを確認できます。

    ```powershell
    Get-MgBetaGroupSetting -GroupId $GroupId  | FL Values
    ```

### コマンドレット構文リファレンス

その他の Microsoft Graph PowerShell ドキュメントについては、[Microsoft Entra コマンドレット](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/)を参照してください。

### Microsoft Graph を使用してグループ設定を管理する

Microsoft Graph を使用してグループ設定を構成および管理するには、[`groupSetting` リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/groupsetting?view=graph-rest-1.0&preserve-view=true) とそれに関連付けられているメソッドを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-settings-v2-cmdlets"} -->
## グループ管理における PowerShell V2 の例 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-settings-v2-cmdlets
- Service: entra-id / users
- Article date: 2026-04-02
- Summary: このページでは、Microsoft Entra ID でグループを管理する際に役立つ PowerShell の例を示します。

### 概要

- [Azure Portal](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups?context=azure/active-directory/users-groups-roles/context/ugr-context)
- [PowerShell](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-settings-v2-cmdlets)

この記事では、Microsoft Entra ID (Microsoft Entra の一部) で PowerShell を使用してグループを管理する方法の例を示します。 また、Microsoft Graph PowerShell モジュールをセットアップする方法も説明します。 まず、[Microsoft Graph PowerShell モジュールをダウンロード](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation?view=graph-powershell-1.0&preserve-view=true)する必要があります。

### 前提条件

- [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation?view=graph-powershell-1.0&preserve-view=true) がインストールされています。
- 少なくとも [グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) ロールを持つアカウントでサインインします。
- `Group.ReadWrite.All`アクセス許可スコープは、テナント内の Microsoft Graph PowerShell アプリケーションに対して同意する必要があります。 `Connect-MgGraph`を実行すると、以前に許可されていない場合は同意を求められます。 最初の同意には管理者アカウントが必要です。 詳細については、「[Microsoft Graph PowerShell SDK の使用を開始する](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/get-started?view=graph-powershell-1.0&preserve-view=true)」 を参照してください。

### Microsoft Graph PowerShell モジュールをインストールする

MgGroup PowerShell モジュールをインストールするには、次のコマンドを使用します。

```powershell
    PS C:\Windows\system32> Install-module Microsoft.Graph
```

モジュールを使用する準備ができているかどうかを確認するには、次のコマンドを使用します。

```powershell
PS C:\Windows\system32> Get-Module -Name "*graph*"

ModuleType Version    PreRelease Name                                ExportedCommands
---------- -------    ---------- ----                                ----------------
Script     1.27.0                Microsoft.Graph.Authentication      {Add-MgEnvironment, Connect-MgGraph, Disconnect-MgGraph, Get-MgContext…}
Script     1.27.0                Microsoft.Graph.Groups              {Add-MgGroupDriveListContentTypeCopy, Add-MgGroupDriveListContentTypeCopyF…
```

これで、モジュールのコマンドレットの使用を開始できます。 Microsoft Graph モジュールのコマンドレットの詳しい説明については、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation?view=graph-powershell-1.0&preserve-view=true) のオンライン リファレンス ドキュメントを参照してください。

### ディレクトリに接続する

Microsoft Graph PowerShell のコマンドレットを使用したグループの管理を開始するには、管理するディレクトリに PowerShell セッションを接続する必要があります。 次のコマンドを使用します。

```powershell
    PS C:\Windows\system32> Connect-MgGraph -Scopes "Group.ReadWrite.All"
```

このコマンドレットでは、ディレクトリへのアクセスに使用する資格情報の入力を求められます。 この例では、 karen@drumkit.onmicrosoft.com を使用してデモ ディレクトリにアクセスします。 コマンドレットは、セッションがディレクトリに正常に接続されたことを表示する確認メッセージを返します。

```powershell
    Welcome To Microsoft Graph!
```

これで、MgGraph コマンドレットを使用してディレクトリ内のグループを管理できます。

### グループを取得する

ディレクトリから既存のグループを取得するには、 `Get-MgGroup` コマンドレットを使用します。

ディレクトリ内のすべてのグループを取得するには、パラメーターなしでコマンドレットを使用します。

```powershell
    PS C:\Windows\system32> Get-MgGroup -All
```

このコマンドレットは、接続されたディレクトリ内のすべてのグループを返します。

`-GroupId` パラメーターを使用して、グループの objectID を指定する特定のグループを取得できます。

```powershell
    PS C:\Windows\system32> Get-MgGroup -GroupId 5e3eba05-6c2b-4555-9909-c08e997aab18 | fl
```

コマンドレットは次のように、入力したパラメーターの値に一致する objectID のグループを返します。

```powershell
AcceptedSenders               :
AllowExternalSenders          :
AppRoleAssignments            :
AssignedLabels                :
AssignedLicenses              :
AutoSubscribeNewMembers       :
Calendar                      : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCalendar
CalendarView                  :
Classification                :
Conversations                 :
CreatedDateTime               : 14-07-2023 14:25:49
CreatedOnBehalfOf             : Microsoft.Graph.PowerShell.Models.MicrosoftGraphDirectoryObject
DeletedDateTime               :
Description                   : Sales and Marketing
DisplayName                   : Sales and Marketing
Id                            : f76cbbb8-0581-4e01-a0d4-133d3ce9197f
IsArchived                    :
IsAssignableToRole            :
IsSubscribedByMail            :
LicenseProcessingState        : Microsoft.Graph.PowerShell.Models.MicrosoftGraphLicenseProcessingState
Mail                          : SalesAndMarketing@M365x64647001.onmicrosoft.com
MailEnabled                   : True
MailNickname                  : SalesAndMarketing
RejectedSenders               :
RenewedDateTime               : 14-07-2023 14:25:49
SecurityEnabled               : True
```

`-Filter` パラメーターを使用して、特定のグループを検索できます。 次の例に示すように、このパラメーターは ODATA フィルター句を使用し、フィルターに一致するすべてのグループを返します。

```powershell
    PS C:\Windows\system32> Get-MgGroup -Filter "DisplayName eq 'Intune Administrators'"

    DeletionTimeStamp            :
    ObjectId                     : aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb
    ObjectType                   : Group
    Description                  : Intune Administrators
    DirSyncEnabled               :
    DisplayName                  : Intune Administrators
    LastDirSyncTime              :
    Mail                         :
    MailEnabled                  : False
    MailNickName                 : 4dd067a0-6515-4f23-968a-cc2ffc2eff5c
    OnPremisesSecurityIdentifier :
    ProvisioningErrors           : {}
    ProxyAddresses               : {}
    SecurityEnabled              : True
```

Note

MgGroup PowerShell コマンドレットは OData クエリ標準を実装しています。 詳しくは、「**OData エンドポイントを使用する OData システム クエリ オプション**」の「 [$filter](https://learn.microsoft.com/ja-jp/previous-versions/dynamicscrm-2015/developers-guide/gg309461%28v=crm.7%29#BKMK_filter)」を参照してください。

ここでは、有効期限ポリシーが適用されていないすべてのグループをプルする方法を示す例を示します。

```powershell
Connect-MgGraph -Scopes 'Group.Read.All'
Get-MgGroup -ConsistencyLevel eventual -Count groupCount -Filter "NOT (expirationDateTime+ge+1900-01-01T00:00:00Z)" | Format-List Id
```

次の例では、前のスクリプトの結果を CSV にエクスポートしています。

```powershell
Connect-MgGraph -Scopes 'Group.Read.All'
Get-MgGroup -ConsistencyLevel eventual -Count groupCount -Filter "NOT (expirationDateTime+ge+1900-01-01T00:00:00Z)" | Format-List Id |Export-Csv -Path {path} -NoTypeInformation
```

この最後の例では、Teams に属するグループのみを取得する方法を示します。

```powershell
Get-MgGroup -ConsistencyLevel eventual -Count groupCount -Filter "NOT (expirationDateTime+ge+1900-01-01T00:00:00Z) and resourceProvisioningOptions/any(p:p eq 'Team')" | Format-List Id, expirationDateTime, resourceProvisioningOptions
```

### グループの作成

ディレクトリに新しいグループを作成するには、 `New-MgGroup` コマンドレットを使用します。 このコマンドレットは、"DemoGroup" という名前の新しいセキュリティ グループを作成します。

```powershell
$param = @{
 description="My Demo Group"
 displayName="DemoGroup"
 mailEnabled=$false
 securityEnabled=$true
 mailNickname="Demo"
}

New-MgGroup @param
```

### グループを更新する

既存のグループを更新するには、 `Update-MgGroup` コマンドレットを使用します。 この例では、グループ "Intune Administrators" の DisplayName プロパティが変更されています。 まず、 `Get-MgGroup` コマンドレットを使用してグループを検索し、DisplayName 属性を使用してフィルター処理します。

```powershell
    PS C:\Windows\system32> Get-MgGroup -Filter "DisplayName eq 'Intune Administrators'"

    DeletionTimeStamp            :
    ObjectId                     : aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb
    ObjectType                   : Group
    Description                  : Intune Administrators
    DirSyncEnabled               :
    DisplayName                  : Intune Administrators
    LastDirSyncTime              :
    Mail                         :
    MailEnabled                  : False
    MailNickName                 : 4dd067a0-6515-4f23-968a-cc2ffc2eff5c
    OnPremisesSecurityIdentifier :
    ProvisioningErrors           : {}
    ProxyAddresses               : {}
    SecurityEnabled              : True
```

次に、Description プロパティを新しい値 "Intune デバイス管理者" に変更します。

```powershell
    PS C:\Windows\system32> Update-MgGroup -GroupId 958d212c-14b0-43d0-a052-d0c2bb555b8b -Description "Demo Group Updated"
```

ここで、グループが再び見つかると、Description プロパティが更新され、新しい値が反映されます。

```powershell
    PS C:\Windows\system32> Get-MgGroup -GroupId 958d212c-14b0-43d0-a052-d0c2bb555b8b | select displayname, description

    DisplayName Description
    ----------- -----------
    DemoGroup   Demo Group Updated
```

### グループを削除する

ディレクトリからグループを削除するには、次のように `Remove-MgGroup` コマンドレットを使用します。

```powershell
    PS C:\Windows\system32> Remove-MgGroup -GroupId 958d212c-14b0-43d0-a052-d0c2bb555b8b
```

### グループ メンバーシップを管理する

グループ メンバーの追加、取得、削除、メンバーシップの確認を行うことができます。

#### メンバーの追加

新しいメンバーをグループに追加するには、 `New-MgGroupMember` コマンドレットを使用します。 このコマンドは、前の例で使用した Intune Administrators グループにメンバーを追加します。

```powershell
    PS C:\Windows\system32> New-MgGroupMember -GroupId f76cbbb8-0581-4e01-a0d4-133d3ce9197f -DirectoryObjectId a88762b7-ce17-40e9-b417-0add1848eb68
```

`-GroupId` パラメーターは、グループの ObjectID です。 `-DirectoryObjectId`は、グループ メンバーとして追加するユーザーの ObjectID です。

#### メンバーを取得する

グループの既存のメンバーを取得するには、次の例のように、 `Get-MgGroupMember` コマンドレットを使用します。

```powershell
    PS C:\Windows\system32> Get-MgGroupMember -GroupId 2c52c779-8587-48c5-9d4a-c474f2a66cf4

Id                                   DeletedDateTime
--                                   ---------------
aaaaaaaa-bbbb-cccc-1111-222222222222
bbbbbbbb-cccc-dddd-2222-333333333333
```

#### メンバーの削除

グループに追加したメンバーを削除するには、次に示すように、 `Remove-MgGroupMemberByRef` コマンドレットを使用します。

```powershell
    PS C:\Windows\system32> Remove-MgGroupMemberByRef -DirectoryObjectId 00aa00aa-bb11-cc22-dd33-44ee44ee44ee -GroupId 2c52c779-8587-48c5-9d4a-c474f2a66cf4
```

#### メンバーを確認する

ユーザーのグループ メンバーシップを確認するには、 `Select-MgGroupIdsUserIsMemberOf` コマンドレットを使用します。 このコマンドレットは、グループ メンバーシップを確認する対象のユーザーの ObjectId、およびメンバーシップを確認する対象のグループの一覧を、パラメーターとして使用します。 グループの一覧は、"Microsoft.Open.AzureAD.Model.GroupIdsForMembershipCheck" 型の複合変数の形式で指定する必要があるため、最初にその型の変数を作成する必要があります。

```powershell
Get-MgUserMemberOf -UserId 00aa00aa-bb11-cc22-dd33-44ee44ee44ee

Id                                   DisplayName Description GroupTypes AccessType
--                                   ----------- ----------- ---------- ----------
5dc16449-3420-4ad5-9634-49cd04eceba0 demogroup   demogroup    {Unified}
```

返される値は、このユーザーがメンバーであるグループの一覧です。 また、このメソッドを適用して、 `Select-MgGroupIdsContactIsMemberOf`、 `Select-MgGroupIdsGroupIsMemberOf`、または `Select-MgGroupIdsServicePrincipalIsMemberOf`を使用して、グループの特定のリストの連絡先、グループ、またはサービス プリンシパルのメンバーシップを確認することもできます。

### ユーザーによるグループの作成を無効にする

標準ユーザーがセキュリティ グループを作成できないようにすることができます。 既定の動作では、セルフサービス グループ管理 (SSGM) も有効になっているかどうかにかかわらず、標準ユーザーがグループを作成できるようになります。 SSGM の設定は、マイ グループ ポータルでの動作のみを制御します。

標準ユーザーのグループ作成を無効にするには:

1. 標準ユーザーがグループの作成を許可されていることを確認します。

    ```powershell
    PS C:\> Get-MgBetaDirectorySetting | select -ExpandProperty values
    
     Name                            Value
     ----                            -----
     NewUnifiedGroupWritebackDefault true
     EnableMIPLabels                 false
     CustomBlockedWordsList
     EnableMSStandardBlockedWords    false
     ClassificationDescriptions
     DefaultClassification
     PrefixSuffixNamingRequirement
     AllowGuestsToBeGroupOwner       false
     AllowGuestsToAccessGroups       true
     GuestUsageGuidelinesUrl
     GroupCreationAllowedGroupId
     AllowToAddGuests                true
     UsageGuidelinesUrl
     ClassificationList
     EnableGroupCreation             true
    ```
2. `EnableGroupCreation : True`が返された場合、標準ユーザーはグループを作成できます。 この機能を無効にするには、次のコマンドを実行します。

    ```powershell
     Install-Module Microsoft.Graph.Beta.Identity.DirectoryManagement
     Import-Module Microsoft.Graph.Beta.Identity.DirectoryManagement
     $params = @{
     TemplateId = "62375ab9-6b52-47ed-826b-58e47e0e304b"
     Values = @(		
     	@{
     		Name = "EnableGroupCreation"
     		Value = "false"
     	}		
     )
     }
     Connect-MgGraph -Scopes "Directory.ReadWrite.All"
     New-MgBetaDirectorySetting -BodyParameter $params
    
    ```

### グループの所有者を管理する

グループに所有者を追加するには、 `New-MgGroupOwner` コマンドレットを使用します。

```powershell
    PS C:\Windows\system32> New-MgGroupOwner -GroupId 0e48dc96-3bff-4fe1-8939-4cd680163497 -DirectoryObjectId 92a0dad0-7c9e-472f-b2a3-0fe2c9a02867
```

`-GroupId` パラメーターは、所有者を追加するグループの ObjectID です。 `-DirectoryObjectId`は、所有者として追加するユーザーまたはサービス プリンシパルの ObjectID です。

グループの所有者を取得するには、 `Get-MgGroupOwner` コマンドレットを使用します。

```powershell
    PS C:\Windows\system32> Get-MgGroupOwner -GroupId 0e48dc96-3bff-4fe1-8939-4cd680163497
```

コマンドレットは、指定したグループの所有者 (ユーザーおよびサービス プリンシパル) の一覧を返します。

```powershell
    Id                                       DeletedDateTime
    --                                       ---------------
    8ee754e0-743e-4231-ace4-c28d20cf2841
    85b1df54-e5c0-4cfd-a20b-8bc1a2ca7865
    4451b332-2294-4dcf-a214-6cc805016c50
```

グループから所有者を削除する場合は、 `Remove-MgGroupOwnerByRef` コマンドレットを使用します。

```powershell
    PS C:\Windows\system32> Remove-MgGroupOwnerByRef -GroupId 0e48dc96-3bff-4fe1-8939-4cd680163497 -DirectoryObjectId 92a0dad0-7c9e-472f-b2a3-0fe2c9a02867
```

### 予約済みのエイリアス

グループを作成するときに、ユーザーは、グループのメール アドレスの一部としてシステムが使用する mailNickname またはエイリアスを指定します。 高い特権を持つ電子メール エイリアスが一覧表示されているグループの作成は、Microsoft Entra グローバル管理者に限定されます。

- 濫用
- 管理者
- 管理者
- ホストマスター
- majordomo
- ポストマスタ
- ルート
- secure
- セキュリティ
- ssl-admin
- ウェブサイト管理者

### オンプレミスへのグループの書き戻し

現在でも、多くのグループがオンプレミスの Active Directory で管理されています。 クラウド グループをオンプレミスに同期させたいというご要望にお応えするため、Microsoft Entra ID に、Microsoft Entra クラウド同期を使用したグループ書き戻し機能が実装されました。

重要

Microsoft Entra Connect Sync でのグループ ライトバック v2 のプレビューは非推奨となり、サポートされなくなりました。

Microsoft Entra Cloud Sync を使用して、クラウド セキュリティ グループをオンプレミスの Active Directory Domain Services (AD DS) にプロビジョニングできます。

Microsoft Entra Connect Sync でグループ ライトバック v2 を使用する場合は、同期クライアントを Microsoft Entra Cloud Sync に移動する必要があります。Microsoft Entra Cloud Sync に移行する資格があるかどうかを確認するには、ユーザー同期ウィザードを使用します。

ウィザードで推奨されているように Microsoft Cloud Sync を使用できない場合は、Microsoft Entra Connect Sync と並行して Microsoft Entra Cloud Sync を実行できます。その場合は、Microsoft Entra Cloud Sync を実行して、クラウド セキュリティ グループをオンプレミスの AD DS にプロビジョニングするだけです。

MICROSOFT 365 グループを AD DS にプロビジョニングする場合は、グループ ライトバック v1 を引き続き使用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/groups-troubleshooting"} -->
## 動的メンバーシップ グループで問題を修正する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/groups-troubleshooting
- Service: entra-id / users
- Article date: 2025-01-15
- Summary: Microsoft Entra ID における動的メンバーシップ グループのトラブルシューティングのヒント

### 概要

この記事では、Microsoft Entra の一部である Microsoft Entra ID のグループのトラブルシューティング情報について説明します。

### グループの作成に関する問題のトラブルシューティング

**Azure portal でセキュリティ グループの作成を無効にしましたが、PowerShell でグループを作成できます** Azure portal の **[User can create security groups in Azure portals](ユーザーは Azure portal でセキュリティ グループを作成できる)** 設定は、管理者以外のユーザーがアクセス パネルまたは Azure portal でセキュリティ グループを作成できるかどうかを制御します。 PowerShell を使用したセキュリティ グループの作成は制御されません。

Powershell で管理者以外のユーザーによるグループの作成を無効にするには、次のようにします。

1. 管理者以外のユーザーにグループの作成が許可されていることを確認します。

    ```powershell
    Get-MgBetaDirectorySetting | select -ExpandProperty values
    ```
2. `EnableGroupCreation : True` が返された場合は、管理者以外のユーザーはグループを作成できます。 この機能を無効にするには、次のコマンドを実行します。

    ```powershell
    Install-Module Microsoft.Graph.Beta.Identity.DirectoryManagement
    Import-Module Microsoft.Graph.Beta.Identity.DirectoryManagement
    $params = @{
    TemplateId = "62375ab9-6b52-47ed-826b-58e47e0e304b"
    Values = @(    
     @{
       Name = "EnableGroupCreation"
       Value = "false"
     }    
    )
     }
    Connect-MgGraph -Scopes "Directory.ReadWrite.All"
    New-MgBetaDirectorySetting -BodyParameter $params
    ```

**PowerShell で動的グループを作成しようとしたときに、最大許容グループ数のエラーが表示されました**

組織あたりの動的グループの最大数は 5,000 です。 組織内の動的グループの最大数に達すると、動的 *グループ ポリシーの最大許可グループ数に達したことを*示すメッセージが PowerShell に表示されます。

この制限に達した場合、新しい動的グループを作成するには、まず既存の動的グループをいくつか削除する必要があります。 上限を増やす方法はありません。

### 動的メンバーシップ グループのトラブルシューティング

**グループに対するルールを構成しましたが、グループのメンバーシップが更新されません**

1. ルール内のユーザー属性またはデバイス属性の値を確認します。 ルールを満たすユーザーが存在することを確認してください。 デバイスの場合は、デバイスのプロパティを調べて、同期された属性に予期される値が含まれていることを確認してください。
2. メンバーシップの処理の状態を調べて、処理が完了しているかどうかを確認してください。 グループの [\[概要\]](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule#check-the-processing-status-for-a-rule) ページで、**メンバーシップの処理状態**と最終更新日を確認できます。

すべてが正常に見える場合は、グループのメンバーが揃うまで少し待ってください。 Microsoft Entra 組織のサイズによっては、グループが初めてまたはルールの変更後に設定されるまでに最大 24 時間かかる場合があります。

**ルールの設定を変更したのですが、そのルールの既存のメンバーが削除されてしまいました** これは通常の動作です。 ルールを有効にしたり変更を加えたりするとグループの既存のメンバーは削除されます。 すべての既存のメンバーが削除されるわけではありません。新しいルールを満たさなくなったユーザーのみが削除されます。 新しいルールの評価から返されたユーザーは、グループにメンバーとして追加されます。 既存のルールと新しいルールの両方を満たすユーザーは、動的グループに残ります。 ライセンスの割り当ては一時的に削除されず、ロールの割り当ては削除されません。

**ルールを追加または変更してもすぐにはメンバーシップの変更を確認できません。なぜでしょうか。**

メンバーシップの評価に特化した機能が、非同期のバックグラウンド プロセスで定期的に実行されます。 ディレクトリ内のユーザー数と結果グループのサイズの両方が処理時間に影響します。

通常、ユーザー数が少ないディレクトリでは、数分以内に動的メンバーシップ グループの変更が表示されます。 ディレクトリのユーザー数が多いと、変更が反映されるまでに 30 分以上かかる場合があります。

**グループが今すぐ処理されるように強制するにはどうすればよいですか。** 現時点では、グループの処理をオンデマンドで自動的にトリガーする方法はありません。 ただし、メンバーシップ ルールを更新して末尾に空白文字を追加すると、手動で再処理をトリガーできます。

**ルール処理エラーが発生しました** 次の表は、動的メンバーシップ グループの一般的な規則エラーとその修正方法を一覧表示しています。

| ルール パーサー エラー | 間違った使用法 | 正しい使用法 |
| --- | --- | --- |
| エラー: 属性がサポートされていません。 | (user.invalidProperty -eq "値") | (user.department -eq "値")該当する属性が、[サポートされているプロパティ一覧](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership#supported-properties)に記載されていることを確認してください。 |
| エラー: 属性で演算子がサポートされていません。 | (ユーザーアカウントが有効 - 含まれる true) | (ユーザーのアカウントが有効化されている場合)プロパティの型に対してサポートされていない演算子が使用されています (この例では、-contains をブール型で使用することはできません)。 プロパティの型に合った適切な演算子を使用してください。 |
| エラー: クエリ コンパイル エラー。 | 1. (user.department -eq "営業") (user.department -eq "マーケティング")2. (user.userPrincipalName -match "\*@domain.ext") | 1. 演算子が不足しています。 述語を結合するには -and か -or を使用します。(user.department -eq "セールス") -or (user.department -eq "マーケティング")2. -match に使用されている正規表現に誤りがあります(user.userPrincipalName -match ".\*@domain.ext")または: (user.userPrincipalName -match "@domain.ext$") |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/how-to-get-signed-in-identity"} -->
## サインイン ID を取得する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/how-to-get-signed-in-identity
- Service: entra-id / users
- Article date: 2025-04-11
- Summary: Azureのロールベースのアクセス制御でこの ID を使用してさまざまなAzure サービスに接続できるように、Azure CLIの現在サインインしているアカウントの一意の識別子を取得します。

### 概要

この記事では、現在サインインしているアカウントの ID を取得する簡単な手順について説明します。 この ID 情報を後で使用して、サインインしたアカウントにロールベースのアクセス制御アクセス権を付与し、Azure内のデータまたはリソースを管理できます。

現在のAzure CLI セッションは、人間の ID (アカウント)、マネージド ID、ワークロード ID、またはサービス プリンシパルを使用してサインインできます。 Azure CLIで使用する ID の種類に関係なく、ID の詳細を取得する手順は似ています。 詳細については、「[Microsoft Entra ID の基礎](https://learn.microsoft.com/ja-jp/entra/fundamentals/identity-fundamental-concepts#identity)を参照してください。

### [前提条件]

- アクティブなサブスクリプションを持つAzure アカウント。 [無料でアカウントを作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。

::: zone pivot="interface-cli"

- [Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview) で Bash 環境を使用します。 詳細については、「[Get started with Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/quickstart)」を参照してください。

- CLI 参照コマンドをローカルで実行する場合は、Azure CLI[install](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli)します。 Windowsまたは macOS で実行している場合は、Docker コンテナーでAzure CLIを実行することを検討してください。 詳細については、「[Docker コンテナーでAzure CLIを実行する方法](https://learn.microsoft.com/ja-jp/cli/azure/run-azure-cli-docker)を参照してください。

    - ローカル インストールを使用している場合は、[az login](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-login) コマンドを使用してAzure CLIにサインインします。 認証プロセスを完了するには、ターミナルに表示される手順に従います。 その他のサインイン オプションについては、「[Azure CLI を使用して Azure に認証する](https://learn.microsoft.com/ja-jp/cli/azure/authenticate-azure-cli)」を参照してください。
    - メッセージが表示されたら、最初に使用するときにAzure CLI拡張機能をインストールします。 拡張機能の詳細については、「Azure CLIを参照してください。
    - [az version](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-version) を実行し、インストールされているバージョンおよび依存ライブラリを検索します。 最新バージョンにアップグレードするには、[az upgrade](https://learn.microsoft.com/ja-jp/cli/azure/reference-index#az-upgrade) を実行します。

::: zone-end

::: zone pivot="interface-shell"

- Azure PowerShellをローカルで使用する場合:
    - [Az PowerShell モジュールの最新バージョンをインストールします](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)。
    - [Connect-AzAccount](https://learn.microsoft.com/ja-jp/powershell/module/az.accounts/connect-azaccount) コマンドレットを使用してAzure アカウントに接続します。
- Azure Cloud Shellを使用する場合:
    - 詳細については、 Azure Cloud Shellを参照してください。

::: zone-end

### サインイン中のアカウントのIDを取得する

コマンド ラインを使用して、グラフにアカウントの一意識別子に関する情報のクエリを実行します。

::: zone pivot="interface-cli"

1. [`az ad signed-in-user`](https://learn.microsoft.com/ja-jp/cli/azure/ad/signed-in-user#az-ad-signed-in-user-show)を使用して、現在ログインしているアカウントの詳細を取得します。

    ```azurecli
    az ad signed-in-user show
    ```
2. このコマンドは、さまざまなフィールドを含む JSON 応答を出力します。

    ```json
    {
      "@odata.context": "<https://graph.microsoft.com/v1.0/$metadata#users/$entity>",
      "businessPhones": [],
      "displayName": "Kai Carter",
      "givenName": "Kai",
      "id": "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
      "jobTitle": "Senior Sales Representative",
      "mail": "<kai@adventure-works.com>",
      "mobilePhone": null,
      "officeLocation": "Redmond",
      "preferredLanguage": null,
      "surname": "Carter",
      "userPrincipalName": "<kai@adventure-works.com>"
    }
    ```

    ヒント

    `id` フィールドの値を記録します。 この例では、その値は `aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb`。 その後、この値をさまざまなスクリプトで使用して、現在のアカウントのロールベースのアクセス制御アクセス許可をAzureリソースに付与できます。

::: zone-end

::: zone pivot="interface-portal"

Microsoft Entra IDのポータル内ウィンドウを使用して、現在サインインしているユーザー アカウントの詳細を取得します。

1. Azure ポータル (https://portal.azure.com) にサインインします。
2. **Home** ペインで、**Microsoft Entra ID** オプションを見つけて選択します。

    [Image: Azure portal の「ホーム」ページにある Microsoft Entra ID オプションのスクリーンショット。]

    ヒント

    このオプションが一覧にない場合は、[その他のサービス] を選択し、検索語句 "Entra" を使用して>Microsoft Entra ID
3. Microsoft Entra ID テナントの Overview ペインで、サービス メニューの Manage セクション内の Users を選択します。

    [Image: Microsoft Entra ID テナントのサービス メニューにある [ユーザー] オプションのスクリーンショット]
4. ユーザーの一覧で、詳細を取得する ID (ユーザー) を選択します。

    [Image: ユーザーの例が強調表示されているMicrosoft Entra ID テナントのユーザーの一覧のスクリーンショット。]

    注

    このスクリーンショットは、のプリンシパルを持つ `kai@adventure-works.com` という名前のユーザーの例を示しています。
5. 特定のユーザーの詳細ウィンドウで、 **オブジェクト ID** プロパティの値を確認します。

    [Image: 一意の 'オブジェクト ID' が強調表示されているMicrosoft Entra ID テナント内の特定のユーザーの詳細ウィンドウのスクリーンショット。]

    ヒント

    **オブジェクト ID** プロパティの値を記録します。 この例では、その値は `aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb`。 その後、この値をさまざまなスクリプトで使用して、現在のアカウントのロールベースのアクセス制御アクセス許可をAzureリソースに付与できます。

::: zone-end

::: zone pivot="interface-shell"

1. [`Get-AzADUser`](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/get-azaduser)を使用して、現在ログインしているアカウントの詳細を取得します。

    ```azurepowershell
    Get-AzADUser -SignedIn | Format-List `
        -Property Id, DisplayName, Mail, UserPrincipalName
    ```
2. このコマンドは、さまざまなフィールドを含むリスト応答を出力します。

    ```output
    Id                : aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb
    DisplayName       : Kai Carter
    Mail              : kai@adventure-works.com
    UserPrincipalName : kai@adventure-works.com
    ```

    ヒント

    `id` フィールドの値を記録します。 この例では、その値は `aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb`。 その後、この値をさまざまなスクリプトで使用して、現在のアカウントのロールベースのアクセス制御アクセス許可をAzureリソースに付与できます。

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/licensing-directory-independence"} -->
## マルチテナントの相互作用の特性 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/licensing-directory-independence
- Service: entra-id / users
- Article date: 2024-12-16
- Summary: Microsoft Entra 組織のデータの独立性を理解する

### 概要

Microsoft Entra の一部である Microsoft Entra ID では、各 Microsoft Entra 組織は完全に独立しています。つまり、管理されている他の Microsoft Entra 組織から論理的に独立したピアです。 この組織間の独立性には、リソースの独立性、管理の独立性、同期の独立性が含まれます。 組織間に親子の関係はありません。

### リソースの独立

- ある組織で Microsoft Entra リソースを作成または削除しても、外部ユーザーの一部の例外を除き、別の組織内のどのリソースにも影響を与えません。
- ある組織にドメイン名のいずれかを登録しても、それを他のどの組織にも使用できません。

### 管理上の独立

組織 'Contoso' の管理者以外のユーザーがテスト組織 'Test' を作成した場合は、次のようになります。

- 既定では、組織を作成したユーザーはその新しい組織に外部ユーザーとして追加され、グローバル管理者ロールが割り当てられます。
- 'Test' の管理者が管理者特権を明示的に付与しない限り、組織 'Contoso' の管理者には組織 'Test' に対する直接の管理者特権が与えられません。
- ある組織のユーザーに対して Microsoft Entra ロールを追加または削除した場合、変更は他のロールには影響しません。 たとえば、ユーザーが他の Microsoft Entra 組織で割り当てるロールなどです。

### 同期の独立

Microsoft Entra Connect ツールを使用して、各 Microsoft Entra 組織を、さまざまな AD フォレストからデータが同期されるように独立に構成できます。 複数の Microsoft Entra テナントがある場合にサポートされるトポロジの詳細については、 [Microsoft Entra Connect のトポロジ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-topologies)を参照してください。

### Microsoft Entra 組織を追加する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com) に少なくとも [テナント作成者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#tenant-creator) としてサインインします。
2. **Entra ID**&gt;**Overview** に移動します。
3. **テナントの管理** を選択します。
4. **を選択して**を作成します。
5. **Workforce** を選択し、要求された情報を指定します。 Microsoft Entra ID によって新しい組織が作成され、組織の一覧に表示されます。

注

他の Azure リソースとは異なり、Microsoft Entra 組織は Azure サブスクリプションの子リソースではありません。 Azure サブスクリプションが取り消されたり、期限切れになったりした場合でも、Azure PowerShell、Microsoft Graph API、または Microsoft 365 管理センターを使用して Microsoft Entra 組織のデータに引き続きアクセスできます。 また、[組織に別のサブスクリプションを関連付ける](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-subscriptions-associated-directory)こともできます。

注

Azure AD および MSOnline PowerShell モジュールは、2024 年 3 月 30 日の時点で非推奨となります。 詳細については、[非推奨の最新情報](https://techcommunity.microsoft.com/t5/microsoft-entra-blog/important-azure-ad-graph-retirement-and-powershell-module/ba-p/3848270)を参照してください。 この日以降、これらのモジュールのサポートは、Microsoft Graph PowerShell SDK への移行支援とセキュリティ修正プログラムに限定されます。 非推奨になるモジュールは、2025 年 3 月 30 日まで引き続き機能します。

Microsoft Entra ID (旧称 Azure AD) を使用するには、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) に移行することをお勧めします。 移行に関する一般的な質問については、「[移行に関する FAQ](https://learn.microsoft.com/ja-jp/powershell/azure/active-directory/migration-faq)」を参照してください。 *注:* MSOnline のバージョン 1.0.x では、2024 年 6 月 30 日以降に中断が発生する可能性があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/licensing-powershell-graph-examples"} -->
## グループベースのライセンスの PowerShell の例 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/licensing-powershell-graph-examples
- Service: entra-id / users
- Article date: 2026-07-01
- Summary: Microsoft PowerShell を使用して Microsoft Entra ID でグループベースのライセンスを管理する方法について説明します。 ライセンスの割り当てとエラーのトラブルシューティングの例が含まれています。

### 概要

Microsoft Entraの一部であるMicrosoft Entra IDのグループベースのライセンスは、Microsoft 365 管理センターで管理[されます](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true)。 この記事では、[Microsoft Graph PowerShell コマンドレット](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/get-started)を使用して実行できる便利なタスクについて説明します。

この記事では、Microsoft Graph PowerShell を使用していくつかの例について説明します。

警告

これらのサンプルは、デモンストレーションのみを目的としています。 運用環境で使用する前に、小規模または別のテスト環境でテストします。 特定の環境の要件を満たすようにサンプルを変更できます。

コマンドレットの実行を開始する前に、 `Connect-MgGraph` コマンドレットを実行して、まず組織に接続してください。

### グループへのライセンスの割り当て

[グループベースのライセンス](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true) は、ライセンスの割り当てを管理するための便利な方法を提供します。 1 つ以上の製品ライセンスをグループに割り当てることができ、それらのライセンスはグループのすべてのメンバーに割り当てられます。

```powershell
# Import the Microsoft.Graph.Groups module
Import-Module Microsoft.Graph.Groups
# Define the group ID - replace with your actual group ID
$groupId = "11111111-1111-1111-1111-111111111111"

# Create a hashtable to store the parameters for the Set-MgGroupLicense cmdlet
$params = @{
    AddLicenses = @(
        @{
            # Remove the DisabledPlans key as we don't need to disable any service plans
            # Specify the SkuId of the license you want to assign
                        SkuId = "11111111-1111-1111-1111-111111111111"       }
    )
    # Keep the RemoveLicenses key empty as we don't need to remove any licenses
    RemoveLicenses = @(
    )
}

# Call the Set-MgGroupLicense cmdlet to update the licenses for the specified group
# Replace $groupId with the actual group ID
Set-MgGroupLicense -GroupId $groupId -BodyParameter $params

```

### グループに割り当てられた製品ライセンスの表示

[Get-MgGroup](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/get-mggroup) コマンドレットを使用すると、グループ オブジェクトを取得し*、AssignedLicenses* プロパティを確認できます。現在グループに割り当てられているすべての製品ライセンスが一覧表示されます。

```powershell
# Define the group ID
$groupId = "99c4216a-56de-42c4-a4ac-e411cd8c7c41"

# Get the group with the specified ID and its assigned licenses
$group = Get-MgGroup -GroupId $groupId -Property "AssignedLicenses"

# Extract the assigned licenses
$assignedLicenses = $group | Select-Object -ExpandProperty AssignedLicenses

# Extract the SKU IDs from the assigned licenses
$skuIds = $assignedLicenses | Select-Object -ExpandProperty SkuId

# For each SKU ID, get the corresponding SKU part number
$skuPartNumbers = $skuIds | ForEach-Object {
    $skuId = $_
    $subscribedSku = Get-MgSubscribedSku | Where-Object { $_.SkuId -eq $skuId }
    $skuPartNumber = $subscribedSku | Select-Object -ExpandProperty SkuPartNumber
    $skuPartNumber
}

# Output the SKU part numbers
$skuPartNumbers
```

この出力は、結果の外観です。

```output
SkuPartNumber
-------------
ENTERPRISEPREMIUM
EMSPREMIUM
```

### 割り当てられたライセンスを持つすべてのグループを取得する

次のコマンドを実行して、割り当てられているすべてのライセンスを持つグループをすべて検索できます。

```powershell
Get-MgGroup -All -Property Id, MailNickname, DisplayName, GroupTypes, Description, AssignedLicenses | Where-Object {$_.AssignedLicenses -ne $null }
```

割り当てられている製品に関する詳細情報を表示することもできます。

```powershell
# Get all groups with assigned licenses
$groups = Get-MgGroup -All -Property Id, MailNickname, DisplayName, GroupTypes, Description, AssignedLicenses | Where-Object {$_.AssignedLicenses -ne $null }

# Process each group
$groupInfo = foreach ($group in $groups) {
    # For each group, get the SKU part numbers of the assigned licenses
    $skuPartNumbers = foreach ($skuId in $group.AssignedLicenses.SkuId) {
        $subscribedSku = Get-MgSubscribedSku | Where-Object { $_.SkuId -eq $skuId }
        $subscribedSku.SkuPartNumber
    }

    # Create a custom object with the group's object ID, display name, and license SKU part numbers
    [PSCustomObject]@{
        ObjectId = $group.Id
        DisplayName = $group.DisplayName
        Licenses = $skuPartNumbers -join ', '
    }
}

$groupInfo
```

この出力は、結果の外観です。

```output
Id                                   DisplayName              AssignedLicenses
--                                   -----------              ----------------
7023a314-6148-4d7b-b33f-6c775572879a EMS E5 – Licensed users  EMSPREMIUM
cf41f428-3b45-490b-b69f-a349c8a4c38e PowerBi - Licensed users POWER_BI_STANDARD
962f7189-59d9-4a29-983f-556ae56f19a5 O365 E3 - Licensed users ENTERPRISEPACK
c2652d63-9161-439b-b74e-fcd8228a7074 EMSandOffice             {ENTERPRISEPREMIUM,EMSPREMIUM}
```

### グループに割り当てられている無効になっているすべてのサービス プラン ライセンスを表示する

```powershell
$groups = Get-MgGroup -All
$groupsWithLicenses = @()

foreach ($group in $groups) {
$licenses = Get-MgGroup -GroupId $group.Id -Property "AssignedLicenses, Id, DisplayName" |
Select-Object AssignedLicenses, DisplayName, Id

if ($licenses.AssignedLicenses) {
    foreach ($license in $licenses.AssignedLicenses) {
        $skuId = $license.SkuId
        $disabledPlans = $license.DisabledPlans

        $skuDetails = Get-MgSubscribedSku | Where-Object { $_.SkuId -eq $skuId }
        $skuPartNumber = $skuDetails.SkuPartNumber

        $disabledPlanDetails = @()
        if ($disabledPlans.Count -gt 0) {
            foreach ($planId in $disabledPlans) {
                $planDetails = $skuDetails.ServicePlans | Where-Object { $_.ServicePlanId -eq $planId }
                
                if ($planDetails) {
                    $disabledPlanDetails += "$($planDetails.ServicePlanName) ($planId)"
                }
            }
        } else {
            $disabledPlanDetails = "None"
        }

        $groupsWithLicenses += [PSCustomObject]@{
            GroupObjectId  = $group.Id
            GroupName      = $group.DisplayName
            SkuId          = $skuId
            SkuPartNumber  = $skuPartNumber
            DisabledPlans  = ($disabledPlanDetails -join ", ")
        }
    }
}
}

# Export to CSV
$csvPath = "$env:USERPROFILE\Documents\GroupLicenses.csv"
$groupsWithLicenses | Export-Csv -Path $csvPath -NoTypeInformation -Encoding UTF8

Write-Host "Export completed: $csvPath"
```

### ライセンスを持つグループの統計を取得する

```powershell
# Import User Graph Module
Import-Module Microsoft.Graph.Users
# Authenticate to MS Graph
Connect-MgGraph -Scopes "User.Read.All", "Directory.Read.All", "Group.ReadWrite.All"
#get all groups with licenses
$groups = Get-MgGroup -All -Property LicenseProcessingState, DisplayName, Id, AssignedLicenses | Select-Object  displayname, Id, LicenseProcessingState, AssignedLicenses | Select-Object DisplayName, Id, AssignedLicenses -ExpandProperty LicenseProcessingState | Select-Object DisplayName, State, Id, AssignedLicenses | Where-Object {$_.State -eq "ProcessingComplete"}
$groupInfoArray = @()
# Filter the groups to only include those that have licenses assigned
$groups = $groups | Where-Object {$_.AssignedLicenses -ne $null}
# For each group, get the group name, license types, total user count, licensed user count, and license error count
foreach ($group in $groups) {
    $groupInfo = New-Object PSObject
    $groupInfo | Add-Member -MemberType NoteProperty -Name "Group Name" -Value $group.DisplayName
    $groupInfo | Add-Member -MemberType NoteProperty -Name "Group ID" -Value $group.Id
    $groupInfo | Add-Member -MemberType NoteProperty -Name "License Types" -Value ($group.AssignedLicenses | Select-Object -ExpandProperty SkuId)
    $groupInfo | Add-Member -MemberType NoteProperty -Name "Total User Count" -Value (Get-MgGroupMember -GroupId $group.Id -All | Measure-Object).Count
    $groupInfo | Add-Member -MemberType NoteProperty -Name "License Error Count" -Value (Get-MgGroupMemberWithLicenseError -GroupId $group.Id -All | Measure-Object).Count
    $groupInfo | Add-Member -MemberType NoteProperty -Name "Licensed User Count" -Value ((Get-MgGroupMember -GroupId $group.Id -All | Measure-Object).Count - (Get-MgGroupMemberWithLicenseError -GroupId $group.Id -All | Measure-Object).Count)
    $groupInfoArray += $groupInfo
}

# Format the output and print it to the console
$groupInfoArray | Format-Table -AutoSize

```

### ライセンス エラーがあるすべてのグループを取得する

```powershell
# Get all groups that have assigned licenses
$groups = Get-MgGroup -All -Property DisplayName, Id, AssignedLicenses | 
    Where-Object { $_.AssignedLicenses -ne $null } | 
    Select-Object DisplayName, Id, AssignedLicenses

# Initialize an array to store group information
$groupInfo = @()

# Iterate over each group
foreach ($group in $groups) {
    $groupId = $group.Id
    $groupName = $group.DisplayName

    # Get count of the group's members and members with license errors
    $totalCount = (Get-MgGroupMember -GroupId $GroupId -All).count
    $licenseErrorCount = (Get-MgGroupMemberWithLicenseError -GroupId $groupId).count

    # Create a custom object with the group's information and counts
    $groupInfo += [PSCustomObject]@{
        GroupName         = $groupName
        GroupId           = $groupId
        TotalUserCount    = $totalCount
        LicenseErrorCount = $licenseErrorCount
    }
}

# Display the groups with licensing errors
$groupInfo | Where-Object { $_.LicenseErrorCount -gt 0 } | Format-Table -Property GroupName, GroupId, TotalUserCount, LicenseErrorCount
```

### グループ内のライセンス エラーがあるすべてのユーザーを取得する

ライセンス関連のエラーがあるグループについて、これらのエラーの影響を受けるすべてのユーザーを表示できるようになりました。 ユーザーが他のグループからのエラーを持つ場合もあります。 ただし、この例では、対象のグループに関連するエラーのみに結果を制限しています。このためには、ユーザーの各 **IndirectLicenseError** エントリの **ReferencedObjectId** プロパティを確認します。

```powershell
# Import necessary modules
Import-Module Microsoft.Graph.Users
Import-Module Microsoft.Graph.Groups

# Specify the group ID you want to check
$groupId = "ENTER-YOUR-GROUP-ID-HERE"

# Authenticate to Microsoft Graph
Connect-MgGraph -Scopes "Group.Read.All", "User.Read.All"

# Get the specified group
$group = Get-MgGroup -GroupId $groupId -Property DisplayName, Id, AssignedLicenses
Write-Host "Checking license errors for group: $($group.DisplayName)" -ForegroundColor Cyan

# Initialize output array
$groupInfoArray = @()

# Get all members from the group and check their license status
$groupMembers = Get-MgGroupMember -GroupId $group.Id -All
$errorCount = 0

# Process each member
foreach ($memberId in $groupMembers.Id) {
    # Get user details
    $user = Get-MgUser -UserId $memberId -Property DisplayName, Id, LicenseAssignmentStates
    
    # Check for license errors
    $licenseErrors = $user.LicenseAssignmentStates | Where-Object { 
        $_.AssignedByGroup -eq $groupId -and $_.Error -ne "None" 
    }
    
    if ($licenseErrors) {
        $errorCount++
        $userInfo = [PSCustomObject]@{
            GroupName = $group.DisplayName
            GroupId = $group.Id
            UserName = $user.DisplayName
            UserId = $user.Id
            Error = ($licenseErrors.Error -join ", ")
            ErrorSubcode = ($licenseErrors.ErrorSubcode -join ", ")
        }
        $groupInfoArray += $userInfo
    }
}

# Summary
Write-Host "Found $errorCount users with license errors in group $($group.DisplayName)" -ForegroundColor Yellow

# Format the output and print it to the console

if ($groupInfoArray.Length -gt 0) {
    $groupInfoArray | Format-Table -AutoSize
}
else {
    Write-Host "No License Errors"
}

```

### 組織全体のライセンス エラーがあるすべてのユーザーを取得する

次のスクリプトを使用すると、1 つ以上のグループからのライセンス エラーを持つすべてのユーザーを一覧表示できます。 このスクリプトでは、ユーザーごと、またライセンス エラーごとに 1 行を出力するので、各エラーのソースを明確に識別することができます。

```powershell
# Connect to Microsoft Graph
Connect-MgGraph -Scopes "User.Read.All", "Directory.Read.All", "Organization.Read.All"

# Retrieve all SKUs in the tenant
$skus = Get-MgSubscribedSku -All | Select-Object SkuId, SkuPartNumber

# Retrieve all users in the tenant with required properties
$users = Get-MgUser -All -Property AssignedLicenses, LicenseAssignmentStates, DisplayName, Id, UserPrincipalName

# Initialize an empty array to store the user license information
$allUserLicenses = @()

foreach ($user in $users) {
    # Initialize a hash table to track all assignment methods for each license
    $licenseAssignments = @{}
    $licenseErrors = @()

    # Loop through license assignment states
    foreach ($assignment in $user.LicenseAssignmentStates) {
        $skuId = $assignment.SkuId
        $assignedByGroup = $assignment.AssignedByGroup
        $assignmentMethod = if ($assignedByGroup -ne $null) {
            # If the license was assigned by a group, get the group name
            $group = Get-MgGroup -GroupId $assignedByGroup
            if ($group) { $group.DisplayName } else { "Unknown Group" }
        } else {
            # If the license was assigned directly by the user
            "User"
        }

        # Check for errors in the assignment state and capture them
        if ($assignment.Error -ne $null -or $assignment.ErrorSubcode -ne $null) {
            $errorDetails = @{
                Error         = $assignment.Error
                ErrorSubcode  = $assignment.ErrorSubcode
                SkuId         = $skuId
                AssignedBy    = $assignmentMethod
            }
            $licenseErrors += $errorDetails
        }

        # Ensure all assignment methods are captured
        if (-not $licenseAssignments.ContainsKey($skuId)) {
            $licenseAssignments[$skuId] = @($assignmentMethod)
        } else {
            $licenseAssignments[$skuId] += $assignmentMethod
        }
    }

    # Process assigned licenses
    foreach ($skuId in $licenseAssignments.Keys) {
        # Get SKU details from the pre-fetched list
        $sku = $skus | Where-Object { $_.SkuId -eq $skuId } | Select-Object -First 1
        $skuPartNumber = if ($sku) { $sku.SkuPartNumber } else { "Unknown SKU" }

        # Sort and join the assignment methods
        $assignmentMethods = ($licenseAssignments[$skuId] | Sort-Object -Unique) -join ", "

        # Clean up license errors to make them more legible
        $errorDetails = if ($licenseErrors.Count -gt 0) {
            $errorMessages = $licenseErrors | Where-Object { $_.SkuId -eq $skuId } | ForEach-Object {
                # Check if error or subcode are empty, and filter them out
                if ($_Error -ne "None" -and $_.ErrorSubcode) {
                    "$($_.AssignedBy): Error: $($_.Error) Subcode: $($_.ErrorSubcode)"
                } elseif ($_Error -ne "None") {
                    "$($_.AssignedBy): Error: $($_.Error)"
                } elseif ($_.ErrorSubcode) {
                    "$($_.AssignedBy): Subcode: $($_.ErrorSubcode)"
                }
            }

            # Join filtered error messages into a clean output
            $errorMessages -join "; "
        } else {
            "No Errors"
        }

        # Construct a custom object to store the user's license information
        $userLicenseInfo = [PSCustomObject]@{
            UserId             = $user.Id
            UserDisplayName    = $user.DisplayName
            UserPrincipalName  = $user.UserPrincipalName
            SkuId              = $skuId
            SkuPartNumber      = $skuPartNumber
            AssignedBy         = $assignmentMethods
            LicenseErrors      = $errorDetails
        }

        # Add the user's license information to the array
        $allUserLicenses += $userLicenseInfo
    }
}

# Export the results to a CSV file
$path = Join-path $env:LOCALAPPDATA ("UserLicenseAssignments_" + [string](Get-Date -UFormat %Y%m%d) + ".csv")
$allUserLicenses | Export-Csv $path -Force -NoTypeInformation

# Display the location of the CSV file
Write-Host "CSV file generated at: $((Get-Item $path).FullName)"
```

メモ

このスクリプトは、環境内のすべてのライセンスユーザーの一覧を取得し、割り当てられているライセンスと割り当ての方法を示します。 結果では、"AssignedBy" に "User" と表示され、直接ライセンス割り当てが示されます。 "SkuPartNumber" に "Unknown SKU" と表示されている場合は、テナントで特定のライセンス SKU が無効になっていることを示します。 このスクリプトは、詳細な分析のために、完全な結果をローカル AppData フォルダー内の CSV ファイルにエクスポートします。

### ユーザー ライセンスが直接割り当てられたものか、グループから継承されたものかを確認する

```powershell
# Retrieve all SKUs in the tenant
$skus = Get-MgSubscribedSku -All | Select-Object SkuId, SkuPartNumber
 
# Retrieve all users in the tenant with required properties
$users = Get-MgUser -All -Property AssignedLicenses, LicenseAssignmentStates, DisplayName, Id, UserPrincipalName
 
# Initialize an empty array to store the user license information
$allUserLicenses = @()
 
foreach ($user in $users) {
    # Initialize a hash table to track all assignment methods for each license
    $licenseAssignments = @{}
 
    # Loop through license assignment states
    foreach ($assignment in $user.LicenseAssignmentStates) {
        $skuId = $assignment.SkuId
        $assignedByGroup = $assignment.AssignedByGroup
        $assignmentMethod = if ($assignedByGroup -ne $null) {
            # If the license was assigned by a group, get the group name
            $group = Get-MgGroup -GroupId $assignedByGroup
            if ($group) { $group.DisplayName } else { "Unknown Group" }
        } else {
            # If the license was assigned directly by the user
            "User"
        }
 
        # Ensure all assignment methods are captured
        if (-not $licenseAssignments.ContainsKey($skuId)) {
            $licenseAssignments[$skuId] = @($assignmentMethod)
        } else {
            $licenseAssignments[$skuId] += $assignmentMethod
        }
    }
 
    # Process assigned licenses
    foreach ($skuId in $licenseAssignments.Keys) {
        # Get SKU details from the pre-fetched list
        $sku = $skus | Where-Object { $_.SkuId -eq $skuId } | Select-Object -First 1
        $skuPartNumber = if ($sku) { $sku.SkuPartNumber } else { "Unknown SKU" }
 
        # Sort and join the assignment methods
        $assignmentMethods = ($licenseAssignments[$skuId] | Sort-Object -Unique) -join ", "
 
        # Construct a custom object to store the user's license information
        $userLicenseInfo = [PSCustomObject]@{
            UserId = $user.Id
            UserDisplayName = $user.DisplayName
            UserPrincipalName = $user.UserPrincipalName
            SkuId = $skuId
            SkuPartNumber = $skuPartNumber
            AssignedBy = $assignmentMethods
        }
 
        # Add the user's license information to the array
        $allUserLicenses += $userLicenseInfo
    }
}
 
# Export the results to a CSV file
$path = Join-path $env:LOCALAPPDATA ("UserLicenseAssignments_" + [string](Get-Date -UFormat %Y%m%d) + ".csv")
$allUserLicenses | Export-Csv $path -Force -NoTypeInformation
 
# Display the location of the CSV file
Write-Host "CSV file generated at: $((Get-Item $path).FullName)"
```

### グループ ライセンスを持つユーザーの直接付与されたライセンスを削除する

このスクリプトの目的は、グループから既に同じライセンスを継承している (例: [グループベースのライセンスへの移行](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/manage-group-licenses?view=o365-worldwide&preserve-view=true)の一環として) ユーザーの不要な直接ライセンスを削除することです。

メモ

ユーザーがサービスやデータへのアクセスを失わないようにするには、直接割り当てられたライセンスで、継承されたライセンスよりも多くのサービス機能が提供されていないことを確認することが重要です。 現在、PowerShell を使用して、継承されたライセンスと直接ライセンスを使用して有効になっているサービスを特定することはできません。 そのため、このスクリプトでは、グループから継承されることが知られている最小限のレベルのサービスを使用して、ユーザーが予期しないサービス損失を発生させないようにチェックします。

#### 変数

- *$GroupLicenses:* グループに割り当てられているライセンスを表します。
- *$GroupMembers:* グループのメンバーを格納します。
- *$UserLicenses:* ユーザーに直接割り当てられたライセンスを保持します。
- *$DirectLicensesToRemove:* ユーザーから削除する必要があるライセンスを格納します。

```powershell
# Define the group ID containing the assigned license
$GroupId = "objectID of Group"

# Force all errors to be terminating errors
$ErrorActionPreference = "Stop"

# Get the group's assigned licenses
$Group = Get-MgGroup -GroupId $GroupId -Property AssignedLicenses
$GroupLicenses = $Group.AssignedLicenses.SkuId

if (-not $GroupLicenses) {
    Write-Host "No licenses assigned to the specified group. Exiting script."
    return
}

# Get all members of the group
$GroupMembers = Get-MgGroupMember -GroupId $GroupId -All

foreach ($User in $GroupMembers) {
    $UserId = $User.Id

    # Get user's assigned licenses
    $UserData = Get-MgUser -UserId $UserId -Property DisplayName,Mail,UserPrincipalName,AssignedLicenses
    $UserLicenses = $UserData.AssignedLicenses.SkuId

    # Identify direct licenses that match the group's assigned licenses
    $DirectLicensesToRemove = @()
    foreach ($License in $UserLicenses) {
        if ($GroupLicenses -contains $License) {
            $DirectLicensesToRemove += $License
        }
    }

    # Print user info before taking action
    Write-Host ("{0,-40} {1,-25} {2,-40} {3}" -f $UserData.Id, $UserData.DisplayName, $UserData.Mail, $UserData.UserPrincipalName)

    # Skip users who have no direct licenses matching the group
    if ($DirectLicensesToRemove.Count -eq 0) {
        Write-Host "No direct licenses to remove. (Only inherited licenses detected)"
        Write-Host "------------------------------------------------------"
        continue
    }

    # Attempt to remove direct licenses
    try {
        Write-Host "Removing direct license(s)..."
        Set-MgUserLicense -UserId $UserId -RemoveLicenses $DirectLicensesToRemove -AddLicenses @() -ErrorAction Stop
        Write-Host "✅ License(s) removed successfully."
    }
    catch {
        $ErrorMessage = $_.Exception.Message

        if ($ErrorMessage -match "User license is inherited from a group membership") {
            Write-Host "⚠️ Skipping removal - License is inherited from a group."
        } else {
            Write-Host "❌ Unexpected error: $ErrorMessage"
        }
    }
    Write-Host "------------------------------------------------------"
}

Write-Host "Script execution complete."

```
<!-- /MSL-PAGE -->
