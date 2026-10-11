# Microsoft Learn — Microsoft Entra / ユーザー・デバイス・ロール・マネージド ID (part 3)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 40

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/tutorial-vm-managed-identities-cosmos"} -->
## 仮想マシンのマネージド ID を使用して Azure Cosmos DB にアクセスする - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/tutorial-vm-managed-identities-cosmos
- Service: entra-id / managed-identities
- Article date: 2023-03-31
- Summary: Azure portal、CLI、PowerShell、Azure Resource Manager テンプレートを使用して、Windows VM でマネージド ID を使用する方法について説明します

この記事では、マネージド ID を使用して Azure Cosmos DB に接続するよう仮想マシンを設定します。 [Azure Cosmos DB](https://learn.microsoft.com/ja-jp/azure/cosmos-db/introduction) は、最新のアプリ開発に対応するフル マネージドの NoSQL データベースです。 [Azure リソースのマネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) を使用すると、Azure によって管理されている ID を使用して Microsoft Entra 認証をサポートするサービスにアクセスするときに、アプリケーションを認証できます。

### 前提条件

- マネージド ID の基礎知識。 続行する前に Azure リソースのマネージド ID について詳しく学習する場合は、マネージド ID の[概要](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)を確認してください。
- アクティブなサブスクリプションを含む Azure アカウントが必要です。 [アカウントは無料で作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- [PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/new-azureps-module-az) または [CLI](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli) のいずれかが必要になる場合があります。
- [Visual Studio Community エディション](https://visualstudio.microsoft.com/vs/community/)、または任意のその他の開発環境。

### リソース グループの作成

**mi-test** という名前のリソース グループを作成します。 このチュートリアルで使用されるすべてのリソースについて、このリソース グループを使用します。

- [Azure portal を使用してリソース グループを作成する](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/manage-resource-groups-portal#create-resource-groups)
- [CLI を使用してリソース グループを作成する](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/manage-resource-groups-cli#create-resource-groups)
- [PowerShell を使用してリソース グループを作成する](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/manage-resource-groups-powershell#create-resource-groups)

### マネージド ID を使用して Azure VM を作成する

このチュートリアルでは、Azure 仮想マシン (VM) が必要です。 システム割り当てマネージド ID を有効にして **mi-vm-01** という名前の仮想マシンを作成します。 また、以前作成したリソース グループ ([mi-test](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities)) に、**mi-ua-01** という名前の**ユーザー割り当てマネージド ID を作成する**こともできます。 ユーザー割り当てマネージド ID を使用する場合は、作成時に VM に割り当てることができます。

#### システム割り当てマネージド ID を使用して VM を作成する

システム割り当てマネージド ID を有効にして Azure VM を作成するには、お使いのアカウントに[仮想マシン共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#virtual-machine-contributor)ロールの割り当てが必要です。 Microsoft Entra の他のロールの割り当ては不要です。

## [Portal](#tab/azure-portal)
- **Azure portal** から、**[仮想マシン]** を検索します。
- **[作成]** を選択します。
- [基本] タブで、必要な情報を指定します。
- **[Next: Disks&gt;](次へ: ディスク>)** を選択します
- 必要に応じて情報の入力を続行し、**[管理]** タブで **[ID]** セクションを見つけ、**[システム割り当てマネージド ID]** の横のボックスをオンにします。

[Image: VM の作成時にシステム割り当てマネージド ID を有効にする方法を示す画像。]

詳細については、Azure 仮想マシンのドキュメントを参照してください。

- [リナックス](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-portal)
- [ウィンドウズ](https://learn.microsoft.com/ja-jp/azure/virtual-machines/windows/quick-create-portal)

## [PowerShell](#tab/azure-powershell)
[New-AZVM](https://learn.microsoft.com/ja-jp/powershell/module/az.compute/new-azvm) で、参照するリソースが作成されます (存在しない場合)。 システム割り当てマネージド ID を有効にして VM を作成するには、以下に示すようにパラメーター **-SystemAssignedIdentity** を渡します。

```powershell

New-AzVm `
    -ResourceGroupName "My VM" `
    -Name "My resource group" `
    -Location "East US" `
    -VirtualNetworkName "myVnet" `
    -SubnetName "mySubnet" `
    -SecurityGroupName "myNetworkSecurityGroup" `
    -SystemAssignedIdentity
    -OpenPorts 80,3389
```

- [クイック スタート: PowerShell を使用して Azure に Windows 仮想マシンを作成する](https://learn.microsoft.com/ja-jp/azure/virtual-machines/windows/quick-create-powershell)
- [クイック スタート: PowerShell を使用して Azure に Linux 仮想マシンを作成する](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-powershell)

## [Azure CLI](#tab/azure-cli)
[Azure CLI vm create](https://learn.microsoft.com/ja-jp/cli/azure/vm/#az-vm-create) コマンドを使用して VM を作成します。 次の例では、 パラメーターの要求どおりに、システム割り当てマネージド ID を持つ `--assign-identity` という名前の VM を作成します。 `--admin-username` および `--admin-password` パラメーターは、仮想マシンのサインイン用の管理ユーザー名とパスワードを指定します。 これらの値は、お使いの環境に合わせて更新してください。

```azurecli
az vm create --resource-group myResourceGroup --name myVM --image win2016datacenter --generate-ssh-keys --assign-identity --admin-username azureuser --admin-password myPassword12
```

- [システム割り当てマネージド ID を使用して Linux 仮想マシンを作成する](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/quick-create-cli)
- [システム割り当てマネージド ID を使用して Windows 仮想マシンを作成する](https://learn.microsoft.com/ja-jp/azure/virtual-machines/windows/quick-create-cli)

## [Resource Manager テンプレート](#tab/azure-resource-manager)
システム割り当てマネージド ID を有効にするには、テンプレートをエディターに読み込み、`Microsoft.Compute/virtualMachines` セクション内で対象の `resources` リソースを探し、`"identity"` プロパティと同じレベルに `"type": "Microsoft.Compute/virtualMachines"` プロパティを追加します。 次の構文を使用します。

```json
"identity": {
    "type": "SystemAssigned"
},
```

完了すると、テンプレートの `resource` セクションに次のセクションが追加されます。 テンプレートは、次に示す例のようになります。

```json
 "resources": [
     {
         //other resource provider properties...
         "apiVersion": "2018-06-01",
         "type": "Microsoft.Compute/virtualMachines",
         "name": "[variables('myVM')]",
         "location": "[myResourceGroup().location]",
         "identity": {
             "type": "SystemAssigned",
             }                        
     }
 ]
```

---

#### ユーザー割り当てマネージド ID を使用して VM を作成する

以下の手順では、ユーザー割り当てマネージド ID が構成された仮想マシンを作成する方法を示します。

## [Portal](#tab/azure-portal)
現在 Azure portal では、VM 作成中のユーザー割り当てマネージド ID の割り当てはサポートされていません。 仮想マシンを作成した後で、ユーザー割り当てマネージド ID をそれに割り当てる必要があります。

[Azure portal を使用して Azure VM で Azure リソースのマネージド ID を構成する](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-portal-windows-vm#user-assigned-managed-identity)

## [PowerShell](#tab/azure-powershell)
ユーザー割り当てマネージド ID を指定して Windows 仮想マシンを作成する。

```powershell
New-AzVm `
    -ResourceGroupName "<Your resource group>" `
    -Name "<Your VM name>" `
    -Location "East US" `
    -VirtualNetworkName "<myVnet>" `
    -SubnetName "mySubnet" `
    -SecurityGroupName "myNetworkSecurityGroup" `
    -UserAssignedIdentity "/subscriptions/<Your subscription>/resourceGroups/<Your resource group>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<Your user assigned managed identity>" `
    -OpenPorts 80,3389

```

ユーザー割り当てマネージド ID を指定して Linux 仮想マシンを作成する。

```powershell
New-AzVm `
    -Name "<Linux VM name>" `
    -image CentOS85Gen2
    -ResourceGroupName "<Your resource group>" `
    -Location "East US" `
    -VirtualNetworkName "myVnet" `
    -SubnetName "mySubnet" `
    -Linux `
    -SecurityGroupName "myNetworkSecurityGroup" `
    -UserAssignedIdentity "/subscriptions/<Your subscription>/resourceGroups/<Your resource group>/providers/Microsoft.ManagedIdentity/userAssignedIdentities/<Your user assigned managed identity>" `
    -OpenPorts 22

```

ユーザー割り当てマネージド ID は、その [resourceID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities) を使用して指定する必要があります。

## [Azure CLI](#tab/azure-cli)
```azurecli
az vm create --resource-group <MyResourceGroup> --name <myVM> --image <SKU Linux Image> --admin-username <USER NAME> --admin-password <PASSWORD> --assign-identity <USER ASSIGNED IDENTITY NAME>
```

[Azure CLI を使用して Azure VM で Azure リソースのマネージド ID を構成する](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-cli-windows-vm#user-assigned-managed-identity)

## [Resource Manager テンプレート](#tab/azure-resource-manager)
API のバージョンに応じて、[異なる手順](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-template-windows-vm#user-assigned-managed-identity)を実行する必要があります。 apiVersion が 2018-06-01 の場合、ユーザー割り当てマネージド ID は userAssignedIdentities 辞書フォームで保存されます。 `<identityName>` 値は、テンプレートの変数 セクションで定義する変数の名前です。 変数で、割り当てるユーザー割り当てマネージド ID を指定します。

```json
    "variables": {
     "identityName": "my-user-assigned"    
        
    },
```

ユーザー割り当てマネージド ID を VM に割り当てるには、resources 要素に次のエントリを追加します。 `<identityName>` は、作成したユーザー割り当てマネージド ID の名前に置き換えてください。

```json

"resources": [
     {
         //other resource provider properties...
         "apiVersion": "2018-06-01",
         "type": "Microsoft.Compute/virtualMachines",
         "name": "[variables('vmName')]",
         "location": "[resourceGroup().location]",
         "identity": {
             "type": "userAssigned",
             "userAssignedIdentities": {
                "[resourceID('Microsoft.ManagedIdentity/userAssignedIdentities/',variables('<identityName>'))]": {}
             }
         }
     }
 ]
```

---

### Azure Cosmos DB アカウントを作成する

これで、ユーザー割り当てマネージド ID またはシステム割り当てマネージド ID を使用して VM が作成されたので、管理者権限を持つ Azure Cosmos DB アカウントを使用できるようにする必要があります。 このチュートリアル用に Azure Cosmos DB アカウントを作成する必要がある場合、その方法の詳細なステップについては [Azure Cosmos DB クイック スタート](https://learn.microsoft.com/ja-jp/azure/cosmos-db/sql/create-cosmosdb-resources-portal)を参照してください。

注

マネージド ID は、Microsoft Entra 認証をサポートするあらゆる Azure リソースにアクセスするために使用できます。 このチュートリアルでは、Azure Cosmos DB アカウントが次に示すように構成されていることを前提とします。

| 設定 | [値] | 説明 |
| --- | --- | --- |
| サブスクリプション | サブスクリプション名 | この Azure Cosmos DB アカウントに使用する Azure サブスクリプションを選択します。 |
| リソース グループ | リソース グループ名 | **mi-test** を選択するか、**[新規作成]** を選択し、新しいリソース グループの一意の名前を入力します。 |
| アカウント名 | 一意の名前 | 自分の Azure Cosmos DB アカウントを識別するための名前を入力します。 指定した名前に *documents.azure.com* が付加されて URI が作成されるので、一意の名前を使用してください。名前に含めることができるのは、英小文字、数字、ハイフン (-) のみです。 長さは 3 文字から 44 文字でなければなりません。 |
| API | 作成するアカウントの種類。 | ドキュメント データベースを作成し、SQL 構文を使用してクエリを実行するには、**Azure Cosmos DB for NoSQL** を選択します。 [SQL API について詳細を確認してください](https://learn.microsoft.com/ja-jp/azure/cosmos-db/introduction)。 |
| 保管場所 | ユーザーに最も近いリージョン | Azure Cosmos DB アカウントをホストする地理的な場所を選択します。 データに最も高速にアクセスできるよう、お客様のユーザーに最も近い場所を使用します。 |

注

テストを行う場合は、Azure Cosmos DB の Free レベル割引を適用することもできます。 Azure Cosmos DB Free レベルのアカウントでは、最初の 1000 RU/s と 25 GB のストレージを無料でご利用いただけます。 [Free レベル](https://azure.microsoft.com/pricing/details/cosmos-db/)の詳細を確認してください。 ただし、このチュートリアルでは、この選択によって違いが生じることはありません。

### アクセス許可

この時点で、マネージド ID を使用して構成された仮想マシンと、Azure Cosmos DB アカウントが両方存在するはずです。 続行する前に、マネージド ID にいくつかの異なるロールを付与する必要があります。

- 最初に、[Azure RBAC](https://learn.microsoft.com/ja-jp/azure/cosmos-db/role-based-access-control) を使用して、Azure Cosmos DB 管理プレーンへのアクセス権を付与します。 マネージド ID には、データベースとコンテナーを作成するために DocumentDB アカウント共同作成者ロールが割り当てられている必要があります。
- また、[Azure Cosmos DB RBAC](https://learn.microsoft.com/ja-jp/azure/cosmos-db/how-to-setup-rbac) を使用して、マネージド ID に共同作成者ロールを付与する必要もあります。 具体的な手順は下で確認できます。

注

**Cosmos DB 組み込みデータ共同作成者**ロールを使用します。 アクセス権を付与するには、ロール定義を ID に関連付ける必要があります。 この例では、マネージド ID は仮想マシンに関連付けられます。

## [Portal](#tab/azure-portal)
**現時点では、Azure portal ではロールの割り当てオプションを利用できません**

## [PowerShell](#tab/azure-powershell)
```powershell
$resourceGroupName = "<myResourceGroup>"
$accountName = "<myCosmosAccount>" 
$contributorRoleDefinitionId = "00000000-0000-0000-0000-000000000002" # This is the ID of the Cosmos DB Built-in Data contributor role definition
$principalId = "1111111-1111-11111-1111-11111111" # This is the object ID of the managed identity.
New-AzCosmosDBSqlRoleAssignment -AccountName $accountName `
    -ResourceGroupName $resourceGroupName `
    -RoleDefinitionId $contributorRoleDefinitionId `
    -Scope "/" `
    -PrincipalId $principalId
```

ロールの割り当て手順が完了すると、次のような結果が表示されます。

[Image: ロールの割り当ての結果が表示されるスクリーンショット。]

## [Azure CLI](#tab/azure-cli)
```azurecli

resourceGroupName='<myResourceGroup>'
accountName='<myCosmosAccount>'
readOnlyRoleDefinitionId = '00000000-0000-0000-0000-000000000002' # This is the ID of the Cosmos DB Built-in Data contributor role definition
principalId = "1111111-1111-11111-1111-11111111" # This is the object ID of the managed identity.
az cosmosdb sql role assignment create --account-name $accountName --resource-group $resourceGroupName --scope "/" --principal-id $principalId --role-definition-id $readOnlyRoleDefinitionId

```

## [Resource Manager テンプレート](#tab/azure-resource-manager)
```JSON
{
  "id": "/subscriptions/mySubscriptionId/resourceGroups/myResourceGroupName/providers/Microsoft.DocumentDB/databaseAccounts/myAccountName/sqlRoleAssignments/00000000-0000-0000-0000-000000000002",
  "name": "myRoleAssignmentId",
  "type": "Microsoft.DocumentDB/databaseAccounts/sqlRoleAssignments",
  "properties": {
    "roleDefinitionId": "/subscriptions/mySubscriptionId/resourceGroups/myResourceGroupName/providers/Microsoft.DocumentDB/databaseAccounts/myAccountName/sqlRoleDefinitions/00000000-0000-0000-0000-000000000002",
    "scope": "/subscriptions/mySubscriptionId/resourceGroups/myResourceGroupName/providers/Microsoft.DocumentDB/databaseAccounts/myAccountName/dbs/purchases/colls/redmond-purchases",
    "principalId": "myPrincipalId"
  }
}

```

---

### データにアクセスする

マネージド ID を使用して Azure Cosmos DB にアクセスすることは、Azure.identity ライブラリを使用してアプリケーションで認証を有効にすることによって実現できます。 [ManagedIdentityCredential](https://learn.microsoft.com/ja-jp/dotnet/api/azure.identity.managedidentitycredential) を直接呼び出すか、[DefaultAzureCredential](https://learn.microsoft.com/ja-jp/dotnet/api/azure.identity.defaultazurecredential) を使用することができます。

ManagedIdentityCredential クラスでは、デプロイ環境に割り当てられたマネージド ID を使用して認証が試行されます。 [DefaultAzureCredential](https://learn.microsoft.com/ja-jp/dotnet/api/overview/azure/identity-readme) では、様々な認証オプションが順番に実行されます。 DefaultAzureCredential が試行する 2 番目の認証オプションが、マネージド ID です。

下の例では、データベース、コンテナー、コンテナー内の項目を作成し、仮想マシンのシステム割り当てマネージド ID を使用して、新しく作成された項目を読み戻します。 ユーザー割り当てマネージド ID を使用する場合は、マネージド ID のクライアント ID を指定して、ユーザー割り当てマネージド ID を指定する必要があります。

```csharp
string userAssignedClientId = "<your managed identity client Id>";
var tokenCredential = new DefaultAzureCredential(new DefaultAzureCredentialOptions { ManagedIdentityClientId = userAssignedClientId });
```

下のサンプルを使用するには、次の NuGet パッケージが必要です。

- Azure.Identity
- Microsoft.Azure.Cosmos
- Microsoft.Azure.Management.CosmosDB

上記の NuGet パッケージに加えて、**[プレリリースを含める]** を有効にし、**Azure.ResourceManager.CosmosDB** を追加する必要もあります。

```csharp
using Azure.Identity;
using Azure.ResourceManager.CosmosDB;
using Azure.ResourceManager.CosmosDB.Models;
using Microsoft.Azure.Cosmos;
using System;
using System.Threading.Tasks;

namespace MITest
{
    class Program
    {
        static async Task Main(string[] args)
        {
            // Replace the placeholders with your own values
            var subscriptionId = "Your subscription ID";
            var resourceGroupName = "You resource group";
            var accountName = "Cosmos DB Account name";
            var databaseName = "mi-test";
            var containerName = "container01";

            // Authenticate to Azure using Managed Identity (system-assigned or user-assigned)
            var tokenCredential = new DefaultAzureCredential();

            // Create the Cosmos DB management client using the subscription ID and token credential
            var managementClient = new CosmosDBManagementClient(tokenCredential)
            {
                SubscriptionId = subscriptionId
            };

            // Create the Cosmos DB data client using the account URL and token credential
            var dataClient = new CosmosClient($"https://{accountName}.documents.azure.com:443/", tokenCredential);

            // Create a new database using the management client
            var createDatabaseOperation = await managementClient.SqlResources.StartCreateUpdateSqlDatabaseAsync(
                resourceGroupName,
                accountName,
                databaseName,
                new SqlDatabaseCreateUpdateParameters(new SqlDatabaseResource(databaseName), new CreateUpdateOptions()));
            await createDatabaseOperation.WaitForCompletionAsync();

            // Create a new container using the management client
            var createContainerOperation = await managementClient.SqlResources.StartCreateUpdateSqlContainerAsync(
                resourceGroupName,
                accountName,
                databaseName,
                containerName,
                new SqlContainerCreateUpdateParameters(new SqlContainerResource(containerName), new CreateUpdateOptions()));
            await createContainerOperation.WaitForCompletionAsync();

            // Create a new item in the container using the data client
            var partitionKey = "pkey";
            var id = Guid.NewGuid().ToString();
            await dataClient.GetContainer(databaseName, containerName)
                .CreateItemAsync(new { id = id, _partitionKey = partitionKey }, new PartitionKey(partitionKey));

            // Read back the item from the container using the data client
            var pointReadResult = await dataClient.GetContainer(databaseName, containerName)
                .ReadItemAsync<dynamic>(id, new PartitionKey(partitionKey));

            // Run a query to get all items from the container using the data client
            await dataClient.GetContainer(databaseName, containerName)
                .GetItemQueryIterator<dynamic>("SELECT * FROM c")
                .ReadNextAsync();
        }
    }
}

```

ManagedIdentityCredential を使用した言語固有の例:

#### .NET

Azure Cosmos DB クライアントを初期化します：

```csharp
CosmosClient client = new CosmosClient("<account-endpoint>", new ManagedIdentityCredential());
```

次に、[データの読み取りと書き込み](https://learn.microsoft.com/ja-jp/azure/cosmos-db/sql/sql-api-dotnet-v3sdk-samples)を行います。

#### Java

Azure Cosmos DB クライアントを初期化します：

```java
CosmosAsyncClient Client = new CosmosClientBuilder().endpoint("<account-endpoint>") .credential(new ManagedIdentityCredential()) .build();
```

次に、[これらのサンプル](https://learn.microsoft.com/ja-jp/azure/cosmos-db/sql/sql-api-java-sdk-samples)で説明されているように、データの読み取りと書き込みを行います。

#### JavaScript

Azure Cosmos DB クライアントを初期化します：

```javascript
const client = new CosmosClient({ "<account-endpoint>", aadCredentials: new ManagedIdentityCredential() });
```

次に、[これらのサンプル](https://learn.microsoft.com/ja-jp/azure/cosmos-db/sql/sql-api-nodejs-samples)で説明されているように、データの読み取りと書き込みを行います。

### クリーンアップの手順

## [Portal](#tab/azure-portal)
1. [Azure portal](https://portal.azure.com) にサインインします。
2. 削除するリソースを選択します。
3. **[削除]** を選択します。
4. メッセージが表示されたら、削除を確定します。

## [PowerShell](#tab/azure-powershell)
```azurepowershell
Remove-AzResource `
  -ResourceGroupName ExampleResourceGroup `
  -ResourceName ExampleVM `
  -ResourceType Microsoft.Compute/virtualMachines
```

## [Azure CLI](#tab/azure-cli)
```azurecli
az resource delete \
  --resource-group ExampleResourceGroup \
  --name ExampleVM \
  --resource-type "Microsoft.Compute/virtualMachines"
```

## [Resource Manager テンプレート](#tab/azure-resource-manager)
セクションは意図的に空のままになっています

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/tutorial-windows-managed-identities-vm-access"} -->
## チュートリアル - Windows VM/VMSS を使用して Azure リソースにアクセスする - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/tutorial-windows-managed-identities-vm-access
- Service: entra-id / managed-identities
- Article date: 2024-06-06
- Summary: Windows VM/VMSS を使用して Azure リソースにアクセスする方法を示すチュートリアル。

### 前提条件

- マネージド ID の知識。 Azure リソースのマネージド ID 機能に慣れていない場合は、こちらの[概要](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)を参照してください。
- Azure アカウント。[無料アカウントにサインアップ](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- 必要なリソース作成とロール管理の手順を実行するための、適切なスコープ (サブスクリプションまたはリソース グループ) の*所有者*アクセス許可。 ロールの割り当てに関するサポートが必要な場合は、[Azure ロールの割り当てによる Azure サブスクリプション リソースへのアクセスの管理](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)に関するページをご覧ください。
- システム割り当てマネージド ID が有効になっている Windows 仮想マシン (VM)。
    - このチュートリアル用に VM を作成する必要がある場合は、[システム割り当て ID が有効な仮想マシンの作成](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-configure-managed-identities)に関する記事をご覧ください。

::: zone pivot="identity-windows-mi-vm-access-data-lake"

### Windows VM のシステム割り当てマネージド ID を使用して Azure Data Lake Store にアクセスする

このチュートリアルでは、Windows 仮想マシン (VM) のシステム割り当てマネージド ID を使用して [Azure Data Lake Store](https://learn.microsoft.com/ja-jp/azure/data-lake-store/data-lake-store-overview) にアクセスする方法について説明します。 マネージド ID は Azure によって自動的に管理されます。 これにより、コードに資格情報を埋め込まなくても、アプリケーションが Microsoft Entra 認証対応のサービスに対して認証できるようになります。

この記事では、次の方法について学習します。

- VM に Azure Data Lake Store へのアクセスを許可する
- VM ID を使用してアクセス トークンを取得し、それを使用して Azure Data Lake Store にアクセスする

### 有効にする

システム割り当てマネージド ID の有効化は、1 クリックで行うことができます。 VM の作成中に有効にするか、既存の VM のプロパティで有効にすることができます。

[Image: 仮想マシンの [システム割り当て済み] タブを示すスクリーンショット。ここで、システム割り当て済みの状態を有効にできます。]

**新しい VM 上でシステム割り当てマネージド ID を有効にするには:**

1. [Azure portal](https://portal.azure.com) にサインインします。
2. [システム割り当て ID を有効にして仮想マシンを作成します](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-portal-windows-vm#system-assigned-managed-identity)。

### アクセス権の付与

VM に Azure Data Lake Store のファイルとフォルダーへのアクセスを許可できます。 この手順では、既存の Data Lake Store を使用することも、新しいものを作成することもできます。

Azure portal を使用して新しい Data Lake Store を作成するには、[Azure Data Lake Store のクイック スタート](https://learn.microsoft.com/ja-jp/azure/data-lake-store/data-lake-store-get-started-portal)を参照してください。 [Azure Data Lake Store のドキュメント](https://learn.microsoft.com/ja-jp/azure/data-lake-store/data-lake-store-overview)に、Azure CLI と Azure PowerShell を使用するクイック スタートも用意されています。

Data Lake Store で新しいフォルダーを作成し、VM のシステム割り当て ID のアクセス許可を付与します。 この ID には、そのフォルダー内のファイルの読み取り、書き込み、実行を行う権限が必要です。

1. Azure portal で、左側のナビゲーションの **[Data Lake Store]** を選びます。
2. このチュートリアルで使用する Data Lake Store を選びます。
3. コマンド バーの **[データ エクスプローラー]** を選びます。
4. Data Lake Store のルート フォルダーが選択されます。 コマンド バーの **[アクセス]** を選びます。
5. **[追加]** を選択します。 **[選択]** フィールドにお使いの VM の名前 (例: **DevTestVM**) を入力します。 検索結果からお使いの VM を選び、**[選択]** を選びます。
6. **[アクセス許可の選択]** を選択してから、**[読み取り]** と **[実行]** を選びます。 **[このフォルダー]** に追加し、**[アクセス許可のみ]** を選択します。
7. **[OK]** を選択し、**[アクセス]** ブレードを閉じます。 アクセス許可が正常に追加されます。
8. 次に、新しいフォルダーを作成します。 コマンド バーの **[新しいフォルダー]** を選び、この新しいフォルダーに名前を付けます。 たとえば **TestFolder** とし、**[OK]** を選択します。
9. 作成したフォルダーを選択し、コマンド バーの **[アクセス]** を選択します。
10. **[追加]** を選択してから、**[選択]** フィールドに VM の名前を入力し、**[選択]** を選びます。
11. **[アクセス許可の選択]** を選択し、**[読み取り]**、**[書き込み]**、**[実行]** を選択します。 **[このフォルダー]** に追加してから、**[アクセス許可エントリと既定のアクセス許可エントリ]** として追加します。
12. **[OK]** を選択します。 アクセス許可が正常に追加されます。

この時点で VM のシステム割り当てマネージド ID は、作成したフォルダーのファイルに対してすべての操作を実行できます。 Data Lake Store のアクセス管理については、[Data Lake Store のアクセス制御](https://learn.microsoft.com/ja-jp/azure/data-lake-store/data-lake-store-access-control)に関するページをご覧ください。

### データにアクセスする

Azure Data Lake Store は Microsoft Entra 認証をネイティブにサポートするため、Azure リソース用マネージド ID を使用して取得されたアクセス トークンを直接受け入れることができます。 Data Lake Store のファイルシステムに認証するために、お使いの Data Lake Store ファイルシステムのエンドポイントに Microsoft Entra ID によって発行されたアクセス トークンを Authorization ヘッダーで送信します。 ヘッダーの形式は `Bearer <ACCESS_TOKEN_VALUE>` です。

Microsoft Entra 認証に対する Data Lake Store のサポートの詳細については、[Microsoft Entra ID を使った Data Lake Store での認証](https://learn.microsoft.com/ja-jp/azure/data-lake-store/data-lakes-store-authentication-using-azure-active-directory)に関するページを参照してください。

Note

Data Lake Store ファイルシステムのクライアント SDK では、Azure リソースのマネージド ID はまだサポートされていません。

このチュートリアルでは、PowerShell を使用して Data Lake Store ファイルシステムの REST API と認証し、REST 要求を行います。 VM のシステム割り当てマネージド ID を認証で使用するには、VM から要求を行う必要があります。

1. ポータルで **[仮想マシン]** に移動し、Windows VM に移動します。 次に、**[概要]** セクションで、**[接続]** を選びます。
2. Windows VM を作成したときに追加した**ユーザー名**と**パスワード**を入力します。
3. これで、VM との**リモート デスクトップ接続**が作成されました。リモート セッションで **PowerShell** を開きます。
4. PowerShell の `Invoke-WebRequest` コマンドレットを使用して、Azure リソース エンドポイントのローカル マネージド ID に、Azure Data Lake Store のアクセス トークンを取得するよう要求します。 Data Lake Store のリソース識別子は `https://datalake.azure.net/` です。 Data Lake はリソース識別子の完全一致を確認するため、末尾のスラッシュが重要です。

    ```powershell
    $response = Invoke-WebRequest -Uri 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fdatalake.azure.net%2F' -Method GET -Headers @{Metadata="true"}
    ```

    応答を JSON オブジェクトから PowerShell オブジェクトに変換します。

    ```powershell
    $content = $response.Content | ConvertFrom-Json
    ```

    応答からアクセス トークンを抽出します。

    ```powershell
    $AccessToken = $content.access_token
    ```
5. すべてが正しく構成されていることを確認します。 PowerShell の `Invoke-WebRequest` コマンドレットを使用して、ルート フォルダー内のフォルダーを一覧表示するよう Data Lake Store の REST エンドポイントに要求します。 Authorization ヘッダーの文字列 `Bearer` に、大文字の "B" があることが重要です。 Data Lake Store の **[概要]** セクションで、ご利用の Data Lake Store の名前を確認できます。

    ```powershell
    Invoke-WebRequest -Uri https://<YOUR_ADLS_NAME>.azuredatalakestore.net/webhdfs/v1/?op=LISTSTATUS -Headers @{Authorization="Bearer $AccessToken"}
    ```

    正常な応答は次のようになります。

    ```powershell
    StatusCode        : 200
    StatusDescription : OK
    Content           : {"FileStatuses":{"FileStatus":[{"length":0,"pathSuffix":"TestFolder","type":"DIRECTORY", "blockSize":0,"accessTime":1507934941392, "modificationTime":1507944835699,"replication":0, "permission":"770","ow..."
    RawContent        : HTTP/1.1 200 OK
                        Pragma: no-cache
                        x-ms-request-id: b4b31e16-e968-46a1-879a-3474aa7d4528
                        x-ms-webhdfs-version: 17.04.22.00
                        Status: 0x0
                        X-Content-Type-Options: nosniff
                        Strict-Transport-Security: ma...
    Forms             : {}
    Headers           : {[Pragma, no-cache], [x-ms-request-id, b4b31e16-e968-46a1-879a-3474aa7d4528],
                        [x-ms-webhdfs-version, 17.04.22.00], [Status, 0x0]...}
    Images            : {}
    InputFields       : {}
    Links             : {}
    ParsedHtml        : System.__ComObject
    RawContentLength  : 556
    ```
6. ここで、Data Lake Store へのファイルのアップロードを試します。 まず、アップロードするファイルを作成します。

    ```powershell
    echo "Test file." > Test1.txt
    ```
7. PowerShell の `Invoke-WebRequest` コマンドレットを使用して、先ほど作成したフォルダーにファイルをアップロードするよう Data Lake Store の REST エンドポイントに要求します。 この要求では 2 つの手順が実行されます。

    1. 要求を行うと、ファイルをアップロードする場所にリダイレクトされます。
    2. ファイルをアップロードします。 このチュートリアルに示されているのとは異なる値を使用する場合は、フォルダーとファイルの名前を正しく設定するのを忘れないでください。

    ```powershell
    $HdfsRedirectResponse = Invoke-WebRequest -Uri https://<YOUR_ADLS_NAME>.azuredatalakestore.net/webhdfs/v1/TestFolder/Test1.txt?op=CREATE -Method PUT -Headers @{Authorization="Bearer $AccessToken"} -Infile Test1.txt -MaximumRedirection 0
    ```

    `$HdfsRedirectResponse` の値を確認すると、次の応答のような値になります。

    ```powershell
    PS C:\> $HdfsRedirectResponse
    
    StatusCode        : 307
    StatusDescription : Temporary Redirect
    Content           : {}
    RawContent        : HTTP/1.1 307 Temporary Redirect
                        Pragma: no-cache
                        x-ms-request-id: b7ab492f-b514-4483-aada-4aa0611d12b3
                        ContentLength: 0
                        x-ms-webhdfs-version: 17.04.22.00
                        Status: 0x0
                        X-Content-Type-Options: nosn...
    Headers           : {[Pragma, no-cache], [x-ms-request-id, b7ab492f-b514-4483-aada-4aa0611d12b3], 
                        [ContentLength, 0], [x-ms-webhdfs-version, 17.04.22.00]...}
    RawContentLength  : 0
    ```

    リダイレクト エンドポイントに要求を送信してアップロードを完了します。

    ```powershell
    Invoke-WebRequest -Uri $HdfsRedirectResponse.Headers.Location -Method PUT -Headers @{Authorization="Bearer $AccessToken"} -Infile Test1.txt -MaximumRedirection 0
    ```

    正常な応答は次のようになります。

    ```powershell
    StatusCode        : 201
    StatusDescription : Created
    Content           : {}
    RawContent        : HTTP/1.1 201 Created
                        Pragma: no-cache
                        x-ms-request-id: 1e70f36f-ead1-4566-acfa-d0c3ec1e2307
                        ContentLength: 0
                        x-ms-webhdfs-version: 17.04.22.00
                        Status: 0x0
                        X-Content-Type-Options: nosniff
                        Strict...
    Headers           : {[Pragma, no-cache], [x-ms-request-id, 1e70f36f-ead1-4566-acfa-d0c3ec1e2307],
                        [ContentLength, 0], [x-ms-webhdfs-version, 17.04.22.00]...}
    RawContentLength  : 0
    ```

最後に、その他の Data Lake Store ファイルシステムの API を使用して、ファイルへの追加、ファイルのダウンロードなどを行うことができます。

### Disable

VM 上でシステム割り当て ID を無効にするには、システム割り当て ID の状態を **Off** に設定します。

[Image: 仮想マシンの [システム割り当て済み] タブを示すスクリーンショット。ここで、システム割り当て済みの状態を無効にできます。]

::: zone-end

::: zone pivot="identity-windows-mi-vm-access-storage"

### Windows VM のシステム割り当てマネージド ID を使用して Azure Storage にアクセスする

このチュートリアルでは、Windows 仮想マシン (VM) のシステム割り当てマネージド ID を使用して Azure Storage にアクセスする方法について説明します。 学習内容は次のとおりです。

- ストレージ アカウントに BLOB コンテナーを作成する
- Windows VM のシステム割り当てマネージド ID にストレージ アカウントへのアクセスを許可する
- アクセス権を取得し、それを使用して Azure Storage を呼び出す

### 有効にする

システム割り当てマネージド ID の有効化は、1 クリックで行うことができます。 VM の作成中に有効にするか、既存の VM のプロパティで有効にすることができます。

[Image: 仮想マシンの [システム割り当て済み] タブを示すスクリーンショット。ここで、システム割り当て済みの状態を有効にできます。]

**新しい VM 上でシステム割り当てマネージド ID を有効にするには:**

1. [Azure portal](https://portal.azure.com) にサインインします。
2. [システム割り当て ID を有効にして仮想マシンを作成します](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-portal-windows-vm#system-assigned-managed-identity)。

#### ストレージ アカウントの作成

このセクションでは、ストレージ アカウントを作成します。

1. Azure portal の左上隅にある **[+ リソースの作成]** ボタンを選択します。
2. **[ストレージ]**、**[ストレージ アカウント - Blob、File、Table、Queue]** の順に選択します。
3. **[名前]** フィールドにストレージ アカウントの名前を入力します。
4. **[デプロイ モデル]** と **[アカウントの種類]** がそれぞれ **[Resource manager]** と **[ストレージ (汎用 v1)]** に設定されている必要があります。
5. **[サブスクリプション]** と **[リソース グループ]** が、前の手順で VM を作成したときに指定したものと一致していることを確認します。
6. **［作成］** を選択します

    [Image: 新しいストレージ アカウントを作成する方法を示すスクリーンショット。]

#### BLOB コンテナーを作成し、ファイルをストレージ アカウントにアップロードする

ファイルには Blob Storage が必要であるため、ファイルを格納する BLOB コンテナーを作成する必要があります。 次に、新しいストレージ アカウントで、BLOB コンテナーにファイルをアップロードします。

1. 新しく作成したストレージ アカウントに移動します。
2. **[Blob service]** セクションで、**[コンテナー]** を選択します。
3. ページの上部にある **[+ コンテナー]** を選択します。
4. **[新しいコンテナー]** フィールドにコンテナーの名前を入力し、**[パブリック アクセス レベル]** オプションで既定値をそのままにします。

    [Image: ストレージ コンテナーの作成方法を示すスクリーンショット。]
5. 任意のエディターを使用して、ローカル コンピューターに *hello world.txt* という名前のファイルを作成します。 ファイルを開き、*Hello world!* というテキストを追加して、保存します。
6. 新しく作成したコンテナーにファイルをアップロードするためのコンテナー名を選択し、**[アップロード]** を選択します。
7. **[BLOB のアップロード]** ペインの **[ファイル]** セクションで、フォルダー アイコンを選択し、ローカル コンピューター上の **hello\_world.txt** ファイルを参照します。 次に、ファイルを選択して**アップロード**します。 [Image: テキスト ファイルのアップロード画面を示すスクリーンショット。]

#### アクセス権の付与

このセクションでは、Azure Storage コンテナーへのアクセスを VM に許可する方法を説明します。 VM のシステム割り当てマネージド ID を使用して、Azure Storage Blob のデータを取得できます。

1. 新しく作成したストレージ アカウントに移動します。
2. **[アクセス制御 (IAM)]** を選択します。
3. **[追加]**&gt;**[ロールの割り当ての追加]** を選択して、**[ロールの割り当ての追加]** ページを開きます。
4. 次のロールを割り当てます。 詳細な手順については、「[Azure portal を使用して Azure ロールを割り当てる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)」を参照してください。

    | 設定 | 値 |
    | --- | --- |
    | Role | ストレージ BLOB データ閲覧者 |
    | アクセスの割り当て先 | マネージド ID |
    | システム割り当て | 仮想マシン |
    | Select | &lt;お使いの仮想マシン&gt; |

    [Image: ロールの割り当てを追加するためのページを示すスクリーンショット。]

### データにアクセスする

Azure Storage は Microsoft Entra 認証をネイティブにサポートするため、マネージド ID を使用して取得したアクセス トークンを直接受け入れることができます。 この手法では Azure Storage の Microsoft Entra ID との統合が使用され、接続文字列に資格情報を提供することとは異なります。

Azure Storage への接続を開く .NET コード例を次に示します。 この例ではアクセス トークンを使用し、前に作成したファイルの内容を読み取ります。 このコードは、VM のマネージド ID のエンドポイントにアクセスできる VM 上で実行する必要があります。 アクセス トークン メソッドを使用するには、.NET Framework 4.6 以降が必要です。 適宜、`<URI to blob file>` の値を置き換えます。 この値は、以前に作成して Blob Storage にアップロードしたファイルに移動し、**[概要]** ページの **[プロパティ]** で **URL** をコピーすることで取得できます。

```csharp
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Net;
using System.Web.Script.Serialization;
using Microsoft.WindowsAzure.Storage.Auth;
using Microsoft.WindowsAzure.Storage.Blob;

namespace StorageOAuthToken
{
    class Program
    {
        static void Main(string[] args)
        {
            //get token
            string accessToken = GetMSIToken("https://storage.azure.com/");

            //create token credential
            TokenCredential tokenCredential = new TokenCredential(accessToken);

            //create storage credentials
            StorageCredentials storageCredentials = new StorageCredentials(tokenCredential);

            Uri blobAddress = new Uri("<URI to blob file>");

            //create block blob using storage credentials
            CloudBlockBlob blob = new CloudBlockBlob(blobAddress, storageCredentials);

            //retrieve blob contents
            Console.WriteLine(blob.DownloadText());
            Console.ReadLine();
        }

        static string GetMSIToken(string resourceID)
        {
            string accessToken = string.Empty;
            // Build request to acquire MSI token
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create("http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=" + resourceID);
            request.Headers["Metadata"] = "true";
            request.Method = "GET";

            try
            {
                // Call /token endpoint
                HttpWebResponse response = (HttpWebResponse)request.GetResponse();

                // Pipe response Stream to a StreamReader, and extract access token
                StreamReader streamResponse = new StreamReader(response.GetResponseStream());
                string stringResponse = streamResponse.ReadToEnd();
                JavaScriptSerializer j = new JavaScriptSerializer();
                Dictionary<string, string> list = (Dictionary<string, string>)j.Deserialize(stringResponse, typeof(Dictionary<string, string>));
                accessToken = list["access_token"];
                return accessToken;
            }
            catch (Exception e)
            {
                string errorText = String.Format("{0} \n\n{1}", e.Message, e.InnerException != null ? e.InnerException.Message : "Acquire token failed");
                return accessToken;
            }
        }
    }
}
```

応答には、次のようなファイルの内容が含まれています。

`Hello world! :)`

### Disable

VM 上でシステム割り当て ID を無効にするには、システム割り当て ID の状態を **Off** に設定します。

[Image: 仮想マシンの [システム割り当て済み] タブを示すスクリーンショット。ここで、システム割り当て済みの状態を無効にできます。]

::: zone-end

::: zone pivot="identity-windows-mi-vm-access-storage-sas"

### Windows VM のシステム割り当てマネージド ID を使用して SAS 資格情報で Azure Storage にアクセスする

このチュートリアルでは、Windows 仮想マシン (VM) のシステム割り当て ID を使用して、ストレージの [Shared Access Signature (SAS)](https://learn.microsoft.com/ja-jp/azure/storage/common/storage-sas-overview) 資格情報を取得する方法について説明します。

サービス SAS は、ストレージ アカウント内のオブジェクトへの限られたアクセス許可を限られた時間にわたって特定のサービス (この例では BLOB サービス) に対してのみ提供できます。 SAS を使用すると、これがアカウント アクセス キーを公開することなく行われます。 SAS 資格情報は、ストレージ SDK を使用するときなど、ストレージ操作に通常どおりに使用できます。 このチュートリアルでは、Azure Storage PowerShell を使用して BLOB のアップロードとダウンロードを行う手順を示します。

学習内容は次のとおりです。

- ストレージ アカウントの作成
- Resource Manager で VM にストレージ アカウント SAS へのアクセス権を付与する
- VM の ID を使用してアクセス トークンを取得し、それを使用して Resource Manager から SAS を取得する

Note

Azure を操作するには、Azure Az PowerShell モジュールを使用することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「[AzureRM から Az への Azure PowerShell の移行](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)」を参照してください。

### ストレージ アカウントの作成

まだ持っていない場合は、この時点でストレージ アカウントを作成する必要があります。 それ以外の場合は、これらの手順に従って、既存のストレージ アカウントの SAS 資格情報へのアクセスを、VM のシステム割り当てマネージド ID に許可します。

1. **[ストレージ]** を選択し、次に **[ストレージ アカウント]** を選択します。
2. **[ストレージ アカウントの作成]** パネルで、ストレージ アカウントの名前を入力します。
3. **[デプロイ モデル]** と **[アカウントの種類]** が **[Resource Manager]** と **[汎用]** にそれぞれ設定されている必要があります。
4. **[サブスクリプション]** と **[リソース グループ]** が、前の手順で VM を作成したときに指定した項目と一致していることを確認するために、チェックします。
5. **[+ 作成]** を選択してストレージ アカウントを作成します。

    [Image: 新しいストレージ アカウントを作成する方法を示すスクリーンショット。]

### ストレージ アカウントに BLOB コンテナーを作成する

チュートリアルの後半で、新しいストレージ アカウントにファイルをアップロードしてダウンロードします。 ファイルには BLOB ストレージが必要であるため、ファイルを格納する BLOB コンテナーを作成する必要があります。

1. 新しく作成したストレージ アカウントに移動します。
2. 左側のパネルで、**[Blob service]** の下の **[コンテナー]** リンクを選択します。
3. ページの上部にある **[+ コンテナー]** を選択すると、**[新しいコンテナー]** パネルが表示されます。
4. コンテナーに名前を付け、アクセス レベルを決定して、**[OK]** を選択します。 ここで指定した名前は、チュートリアルの後半で使用します。

    [Image: ストレージ コンテナーの作成方法を示すスクリーンショット。]

### VM のシステム割り当てマネージド ID にストレージ SAS を使用するためのアクセス権を付与する

Azure Storage では、ネイティブで Microsoft Entra 認証がサポートされていません。 ただし、マネージド ID を使用して Resource Manager からストレージ SAS を取得し、その SAS を使用してストレージにアクセスできます。 この手順では、ストレージ アカウントの SAS へのアクセス権を VM のシステム割り当てマネージド ID に付与します。

1. 新たに作成したストレージ アカウントに戻ります。
2. **[アクセス制御 (IAM)]** を選択します。
3. **[追加]**&gt;**[ロールの割り当ての追加]** を選択して、**[ロールの割り当ての追加]** ページを開きます。
4. 次のロールを割り当てます。 詳細な手順については、「[Azure portal を使用して Azure ロールを割り当てる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)」を参照してください。

    | 設定 | 値 |
    | --- | --- |
    | Role | ストレージ アカウント共同作業者 |
    | アクセスの割り当て先 | マネージド ID |
    | システム割り当て | 仮想マシン |
    | Select | &lt;Windows 仮想マシン&gt; |

    [Image: ロールの割り当てを追加するためのページを示すスクリーンショット。]

### VM ID を使用してアクセス トークンを取得し、そのアクセス トークンを使用して Azure Resource Manager を呼び出す

チュートリアルの残りの部分では、VM から作業を行います。 ここでは、Azure Resource Manager PowerShell コマンドレットを使用する必要があります。 PowerShell をインストールしていない場合は、先に進む前に、[最新バージョンをダウンロード](https://learn.microsoft.com/ja-jp/powershell/azure/)してください。

1. Azure Portal で **[Virtual Machines]** にナビゲートして Windows 仮想マシンに移動し、**[概要]** ページの上部にある **[接続]** を選びます。
2. Windows VM を作成したときに追加した**ユーザー名**と**パスワード**を入力します。
3. 仮想マシンとの**リモート デスクトップ接続**を確立します。
4. リモート セッションで PowerShell を開き、PowerShell の `Invoke-WebRequest` コマンドレットを使用して、Azure リソース エンドポイントのローカル マネージド ID から Azure Resource Manager トークンを取得します。

    ```powershell
       $response = Invoke-WebRequest -Uri 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fmanagement.azure.com%2F' -Method GET -Headers @{Metadata="true"}
    ```

    Note

    `resource` パラメーターの値は、Microsoft Entra ID で想定されているものと完全に一致している必要があります。 Azure Resource Manager のリソース ID を使用する場合は、URI の末尾にスラッシュを含める必要があります。

    次に、`content` オブジェクトに JavaScript Object Notation (JSON) 形式の文字列として格納されている `$response` 要素を抽出します。

    ```powershell
    $content = $response.Content | ConvertFrom-Json
    ```

    次に、アクセス トークンを応答から抽出します。

    ```powershell
    $ArmToken = $content.access_token
    ```

### ストレージ呼び出しを行うために Azure Resource Manager から SAS 資格情報を取得する

最後に、PowerShell を使用して、前のセクションで取得したアクセス トークンを使って Resource Manager を呼び出します。 このトークンを使用して、ストレージ SAS 資格情報を作成します。 SAS 資格情報を取得したら、他のストレージ操作を呼び出すことができます。

この要求のために、次の HTTP 要求のパラメーターを使用して SAS 資格情報を作成します。

```JSON
{
    "canonicalizedResource":"/blob/<STORAGE ACCOUNT NAME>/<CONTAINER NAME>",
    "signedResource":"c",              // The kind of resource accessible with the SAS, in this case a container (c).
    "signedPermission":"rcw",          // Permissions for this SAS, in this case (r)ead, (c)reate, and (w)rite. Order is important.
    "signedProtocol":"https",          // Require the SAS be used on https protocol.
    "signedExpiry":"<EXPIRATION TIME>" // UTC expiration time for SAS in ISO 8601 format, for example 2017-09-22T00:06:00Z.
}
```

ここでは、パラメーターは SAS 資格情報に対する要求の POST 本文に含まれます。 SAS 資格情報を作成するためのパラメーターの詳細については、[List Service SAS REST リファレンス](https://learn.microsoft.com/ja-jp/rest/api/storagerp/storage-accounts/list-service-sas)に関する記事を参照してください。

1. パラメーターを JSON に変換し、その後で SAS 資格情報を作成するストレージの `listServiceSas` エンドポイントを呼び出します。

    ```powershell
    $params = @{canonicalizedResource="/blob/<STORAGE-ACCOUNT-NAME>/<CONTAINER-NAME>";signedResource="c";signedPermission="rcw";signedProtocol="https";signedExpiry="2017-09-23T00:00:00Z"}
    $jsonParams = $params | ConvertTo-Json
    ```

    ```powershell
    $sasResponse = Invoke-WebRequest -Uri https://management.azure.com/subscriptions/<SUBSCRIPTION-ID>/resourceGroups/<RESOURCE-GROUP>/providers/Microsoft.Storage/storageAccounts/<STORAGE-ACCOUNT-NAME>/listServiceSas/?api-version=2017-06-01 -Method POST -Body $jsonParams -Headers @{Authorization="Bearer $ArmToken"}
    ```

    Note

    URL では大文字小文字が区別されるため、リソース グループの命名時に使用したものと同じ大文字小文字が使用されていること (`resourceGroups` の "G" が大文字であることを含む) を確認してください。
2. 次に、応答から SAS 資格情報を抽出します。

    ```powershell
    $sasContent = $sasResponse.Content | ConvertFrom-Json
    $sasCred = $sasContent.serviceSasToken
    ```
3. SAS 資格情報を検査すると、次のような内容が表示されます。

    ```powershell
    PS C:\> $sasCred
    sv=2015-04-05&sr=c&spr=https&se=2017-09-23T00%3A00%3A00Z&sp=rcw&sig=JVhIWG48nmxqhTIuN0uiFBppdzhwHdehdYan1W%2F4O0E%3D
    ```
4. *test.txt* というファイルを作成します。 その後、SAS 資格情報を使用して `New-AzStorageContent` コマンドレットで認証を行い、ファイルを BLOB コンテナーにアップロードしてから、ファイルをダウンロードします。

    ```bash
    echo "This is a test text file." > test.txt
    ```
5. 必ず、最初に `Install-Module Azure.Storage` を使用して Azure Storage コマンドレットをインストールしてください。 その後、先ほど作成した BLOB を、次のように PowerShell の `Set-AzStorageBlobContent` コマンドレットを使用してアップロードします。

    ```powershell
    $ctx = New-AzStorageContext -StorageAccountName <STORAGE-ACCOUNT-NAME> -SasToken $sasCred
    Set-AzStorageBlobContent -File test.txt -Container <CONTAINER-NAME> -Blob testblob -Context $ctx
    ```

    応答:

    ```powershell
    ICloudBlob        : Microsoft.WindowsAzure.Storage.Blob.CloudBlockBlob
    BlobType          : BlockBlob
    Length            : 56
    ContentType       : application/octet-stream
    LastModified      : 9/21/2017 6:14:25 PM +00:00
    SnapshotTime      :
    ContinuationToken :
    Context           : Microsoft.WindowsAzure.Commands.Storage.AzureStorageContext
    Name              : testblob
    ```
6. アップロードした BLOB を、次のように`Get-AzStorageBlobContent`PowerShell コマンドレットを使用してダウンロードすることもできます。

    ```powershell
    Get-AzStorageBlobContent -Blob testblob -Container <CONTAINER-NAME> -Destination test2.txt -Context $ctx
    ```

    応答:

    ```powershell
    ICloudBlob        : Microsoft.WindowsAzure.Storage.Blob.CloudBlockBlob
    BlobType          : BlockBlob
    Length            : 56
    ContentType       : application/octet-stream
    LastModified      : 9/21/2017 6:14:25 PM +00:00
    SnapshotTime      :
    ContinuationToken :
    Context           : Microsoft.WindowsAzure.Commands.Storage.AzureStorageContext
    Name              : testblob
    ```

::: zone-end

::: zone pivot="identity-windows-mi-vm-access-sql-db"

### Windows VM のシステム割り当てマネージド ID を使用して Azure SQL Database にアクセスする

このチュートリアルでは、Windows 仮想マシン (VM) のシステム割り当て ID を使用して Azure SQL Database にアクセスする方法について説明します。 管理対象サービス ID は Azure によって自動的に管理され、資格情報をコードに挿入しなくても、Microsoft Entra 認証をサポートするサービスへの認証を有効にします。

学習内容は次のとおりです。

- VM に Azure SQL Database へのアクセスを許可する
- Microsoft Entra 認証を有効にする
- VM のシステム割り当て ID を表す包含ユーザーをデータベースに作成する
- VM ID を使用してアクセス トークンを取得し、それを使用して Azure SQL Database にクエリを実行する

### 有効にする

システム割り当てマネージド ID の有効化は、1 クリックで行うことができます。 VM の作成中に有効にするか、既存の VM のプロパティで有効にすることができます。

[Image: 仮想マシンの [システム割り当て済み] タブを示すスクリーンショット。ここで、システム割り当て済みの状態を有効にできます。]

**新しい VM 上でシステム割り当てマネージド ID を有効にするには:**

1. [Azure portal](https://portal.azure.com) にサインインします。
2. [システム割り当て ID を有効にして仮想マシンを作成します](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/qs-configure-portal-windows-vm#system-assigned-managed-identity)。

### アクセス権の付与

Azure SQL Database 内のデータベースに対するアクセスを VM に許可するには、既存の[論理 SQL サーバー](https://learn.microsoft.com/ja-jp/azure/azure-sql/database/logical-servers)を使用するか、新しいものを作成します。 Azure portal を使用して新しいサーバーとデータベースを作成するには、[Azure SQL のクイック スタート](https://learn.microsoft.com/ja-jp/azure/azure-sql/database/single-database-create-quickstart)に従います。 [Azure SQL のドキュメント](https://learn.microsoft.com/ja-jp/azure/azure-sql/)に、Azure CLI と Azure PowerShell を使用するクイックスタートも用意されています。

VM にデータベースへのアクセスを許可するには、次の手順に従います。

1. サーバーに対して Microsoft Entra 認証を有効にします。
2. VM のシステム割り当て ID を表す*包含ユーザー*をデータベースに作成します。

### Microsoft Entra 認証を有効にする

[Microsoft Entra 認証を構成する](https://learn.microsoft.com/ja-jp/azure/azure-sql/database/authentication-aad-configure)には:

1. Azure portal で、左側のナビゲーションから **[SQL サーバー]** を選択します。
2. Microsoft Entra 認証を有効にする SQL サーバーを選択します。
3. ブレードの **[設定]** セクションで **[Active Directory 管理者]** を選択します。
4. コマンド バーで、 **[管理者の設定]** を選択します。
5. サーバーの管理者にする Microsoft Entra ユーザー アカウントを選択し、**[選択]** を選びます。
6. コマンド バーの **[保存]** を選択します。

### 包含ユーザーを作成する

このセクションでは、VM のシステム割り当て ID を表す包含ユーザーをデータベースに作成する方法を説明します。 このステップのためには、[Microsoft SQL Server Management Studio (SSMS)](https://learn.microsoft.com/ja-jp/sql/ssms/download-sql-server-management-studio-ssms) がインストールされている必要があります。 始める前に、Microsoft Entra 統合の背景について次の記事で確認しておくと有益です。

- [SQL Database と Azure Synapse Analytics を使用したユニバーサル認証 (SSMS での MFA のサポート)](https://learn.microsoft.com/ja-jp/azure/azure-sql/database/authentication-mfa-ssms-overview)
- [SQL Database または Azure Synapse Analytics を使用した Microsoft Entra 認証の構成と管理](https://learn.microsoft.com/ja-jp/azure/azure-sql/database/authentication-aad-configure)

SQL データベースには、一意の Microsoft Entra ID 表示名が必要です。 このため、ユーザー、グループ、サービス プリンシパル (アプリケーション) や、マネージド ID 用に有効化された VM 名などの Microsoft Entra アカウントは、対応する表示名に固有の Microsoft Entra ID で一意に定義されている必要があります。 SQL を使用すると、このようなユーザーの T-SQL 作成時に Microsoft Entra ID の表示名が確認されます。 表示名が一意でない場合、コマンドは失敗し、指定されたアカウントごとに一意の Microsoft Entra ID 表示名を指定するように求められます。

#### 包含ユーザーを作成するには

1. SQL Server Management Studio を開きます。
2. **[サーバーに接続]** ダイアログで、 **[サーバー名]** フィールドにサーバー名を入力します。
3. **[認証]** フィールドで、 **[Active Directory - MFA サポートで汎用]** を選択します。
4. **[ユーザー名]** フィールドに、サーバー管理者として設定した Microsoft Entra アカウントの名前を入力します (例: *cjensen@fabrikam.com*)。
5. **オプション**を選択します。
6. **[データベースに接続]** フィールドに、構成する非システム データベースの名前を入力します。
7. **[接続]** を選択し、サインイン プロセスを完了します。
8. **オブジェクト エクスプローラー**で、 **[データベース]** フォルダーを展開します。
9. ユーザー データベースを右クリックし、**[新しいクエリ]** を選択します。
10. クエリ ウィンドウで、次の行を入力し、ツールバーの **[実行]** を選択します。

    Note

    次のコマンドの `VMName` は、前提条件のセクションでシステム割り当て ID を有効にした VM の名前です。

    ```sql
    CREATE USER [VMName] FROM EXTERNAL PROVIDER
    ```

    VM のシステム割り当て ID の包含ユーザーが作成されて、コマンドは正常に完了します。
11. クエリ ウィンドウをクリアし、次の行を入力して、ツール バーの **[実行]** を選択します。

    Note

    次のコマンドの `VMName` は、前提条件のセクションでシステム割り当て ID を有効にした VM の名前です。

    "プリンシパル `VMName` に重複した表示名があります" というエラーが発生した場合は、CREATE USER ステートメントに WITH OBJECT\_ID='xxx' を追加します。

    ```sql
    ALTER ROLE db_datareader ADD MEMBER [VMName]
    ```

    包含ユーザーにデータベース全体を読み取る権限が与えられ、コマンドは正常に完了します。

これで、VM 上で実行されるコードは、システム割り当てマネージド ID を使用してトークンを取得し、そのトークンを使用してサーバーへの認証を行うようになりました。

### データにアクセスする

このセクションでは、VM のシステム割り当てマネージド ID を使用してアクセス トークンを取得し、それを使用して Azure SQL を呼び出す方法を説明します。 Azure SQL は Microsoft Entra 認証をネイティブにサポートするため、Azure リソースのマネージド ID を使用して取得されたアクセス トークンを直接受け入れることができます。 このメソッドでは、接続文字列に資格情報を指定する必要はありません。

Active Directory マネージド ID 認証を使用して SQL への接続を開く .NET コードの例を次に示します。 このコードは、VM のシステム割り当てマネージド ID のエンドポイントにアクセスできる VM 上で実行する必要があります。

このメソッドを使用するには、**.NET Framework 4.6.2** 以降または **.NET Core 3.1** 以降が必要です。 それに応じて AZURE-SQL-SERVERNAME と DATABASE の値を置き換え、NuGet 参照を Microsoft.Data.SqlClient ライブラリに追加します。

```csharp
using Microsoft.Data.SqlClient;

try
{
//
// Open a connection to the server using Active Directory Managed Identity authentication.
//
string connectionString = "Data Source=<AZURE-SQL-SERVERNAME>; Initial Catalog=<DATABASE>; Authentication=Active Directory Managed Identity; Encrypt=True";
SqlConnection conn = new SqlConnection(connectionString);
conn.Open();
```

Note

[SDK](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-configure-managed-identities) とともに他のプログラミング オプションを使用しながら、マネージド ID を使用できます。

または、PowerShell を使用して、アプリを記述して VM に展開することなく、エンド ツー エンドのセットアップをテストします。

1. ポータルで **[仮想マシン]** に移動し、Windows VM に移動して、**[概要]** の **[接続]** を選択します。
2. Windows VM を作成したときに追加した **VM 管理者の資格情報**を入力します。
3. これで、VM との**リモート デスクトップ接続**が作成されました。リモート セッションで **PowerShell** を開きます。
4. PowerShell の `Invoke-WebRequest` コマンドレットを使用して、ローカルのマネージド ID のエンドポイントに Azure SQL のアクセス トークンを取得するよう要求します。

    ```powershell
        $response = Invoke-WebRequest -Uri 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fdatabase.windows.net%2F' -Method GET -Headers @{Metadata="true"}
    ```

    応答を JSON オブジェクトから PowerShell オブジェクトに変換します。

    ```powershell
    $content = $response.Content | ConvertFrom-Json
    ```

    応答からアクセス トークンを抽出します。

    ```powershell
    $AccessToken = $content.access_token
    ```
5. サーバーへの接続を開きます。 AZURE-SQL-SERVERNAME と DATABASE の値を置き換えることを忘れないでください。

    ```powershell
    $SqlConnection = New-Object System.Data.SqlClient.SqlConnection
    $SqlConnection.ConnectionString = "Data Source = <AZURE-SQL-SERVERNAME>; Initial Catalog = <DATABASE>; Encrypt=True;"
    $SqlConnection.AccessToken = $AccessToken
    $SqlConnection.Open()
    ```

    次に、クエリを作成してサーバーに送信します。 TABLE の値を必ず置き換えてください。

    ```powershell
    $SqlCmd = New-Object System.Data.SqlClient.SqlCommand
    $SqlCmd.CommandText = "SELECT * from <TABLE>;"
    $SqlCmd.Connection = $SqlConnection
    $SqlAdapter = New-Object System.Data.SqlClient.SqlDataAdapter
    $SqlAdapter.SelectCommand = $SqlCmd
    $DataSet = New-Object System.Data.DataSet
    $SqlAdapter.Fill($DataSet)
    ```

最後に、`$DataSet.Tables[0]` の値を調べて、クエリの結果を確認します。

### Disable

VM 上でシステム割り当て ID を無効にするには、システム割り当て ID の状態を **Off** に設定します。

[Image: 仮想マシンの [システム割り当て済み] タブを示すスクリーンショット。ここで、システム割り当て済みの状態を無効にできます。]

::: zone-end

::: zone pivot="identity-windows-mi-vm-access-key-vault"

### Windows VM のシステム割り当てマネージド ID を使用して Azure Key Vault にアクセスする

このチュートリアルでは、Windows 仮想マシン (VM) でシステム割り当てマネージド ID を使用して [Azure Key Vault](https://learn.microsoft.com/ja-jp/azure/key-vault/general/overview) にアクセスする方法について説明します。 Key Vault により、クライアント アプリケーションは、Microsoft Entra ID で保護されていないリソースにシークレットを使ってアクセスできます。 マネージド ID は Azure によって自動的に管理されます。 これらを使うと、コードに認証情報を含めることなく、Microsoft Entra 認証をサポートするサービスに対して認証を行うことができます。

学習内容は次のとおりです。

- Key Vault に格納されているシークレットへ VM のアクセスを許可する
- VM ID を使用してアクセス トークンを取得して、Key Vault からシークレットを取得する

### Key Vault の作成

このセクションでは、Key Vault に格納されているシークレットへのアクセスを VM に許可する方法を説明します。 Azure リソース用マネージド ID を使うとき、Microsoft Entra 認証をサポートするリソースに対して認証するためのアクセス トークンをコードで取得できます。

ただし、すべての Azure サービスで Microsoft Entra 認証がサポートされているわけではありません。 Azure リソースのマネージド ID をこれらのサービスと共に使用するには、Azure Key Vault にサービス資格情報を保存し、VM のマネージド ID を使用して Key Vault にアクセスして、資格情報を取得します。

まず、Key Vault を作成し、VM のシステム割り当てマネージド ID に Key Vault へのアクセスを許可する必要があります。

1. [Azure portal](https://portal.azure.com/) にサインインします。
2. 左側のナビゲーション バーの上部で、**[リソースの作成]** を選びます。
3. **[Marketplace を検索]** ボックスに「**Key Vault**」と入力し、**Enter** キーを押します。
4. 結果から **[Key Vault]** を選択し、**[作成]** を選択します。
5. 新しいキー コンテナーの **[名前]** を入力します。

    [Image: [キー コンテナーの作成] 画面のスクリーンショット。]
6. 必須情報を全部入力します このチュートリアルで使用しているサブスクリプションとリソース グループを選択していることを確認してください。
7. **[確認および作成]** を選択します。
8. **［作成］** を選択します

### シークレットを作成します

次に、Key Vault にシークレットを追加し、VM で実行されているコードを使用して後で取得できるようにする必要があります。 このセクションでは PowerShell を使用しますが、VM で実行するどのコードにも同じ概念が適用されます。

1. 新しく作成した Key Vault に移動します。
2. **[シークレット]** を選択してから、**[追加]** を選択します。
3. **[Generate/Import](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/生成/インポート)** を選択します。
4. **[シークレットの作成]** 画面の **[アップロード オプション]** で、**[手動]** を選択したままにします。
5. シークレットの名前と値を指定します。 値は任意のものを指定できます。
6. アクティブ化した日付と有効期限の日付をクリアのままにし、**[有効]** を **[はい]** のままにします。
7. **[作成]** を選択して、シークレットを作成します。

    [Image: シークレットの作成方法を示すスクリーンショット。]

### アクセス権の付与

VM で使用されるマネージド ID には、Key Vault に格納するシークレットを読み取るためのアクセスを許可する必要があります。

1. 新しく作成した Key Vault に移動します。
2. 左側のメニューで、 **[アクセス ポリシー]** を選択します。
3. **[アクセス ポリシーの追加]** を選択します。

    [Image: Key Vault アクセス ポリシー画面が表示されたスクリーンショット。]
4. **[アクセス ポリシーの追加]** セクションで、**[テンプレートからの構成 (省略可能)]** のドロップダウン メニューから **[シークレットの管理]** を選択します。
5. **[プリンシパルの選択]** を選択し、以前に作成した VM の名前を検索フィールドに入力します。
6. 結果一覧で VM を選択し、**[選択]** を選びます。
7. **[追加]** を選択します。
8. **[保存]** を選択します。

### データにアクセスする

このセクションでは、VM ID を使用してアクセス トークンを取得し、それを使用して Key Vault からシークレットを取得する方法を説明します。 PowerShell 4.3.1 以上がインストールされていない場合、[最新バージョンをダウンロードしてインストールする](https://learn.microsoft.com/ja-jp/powershell/azure/)必要があります。

Note

PowerShell を使用してシークレットを認証および取得する方法は、マネージド ID が特に必要なシナリオや、アプリケーションのコード内にプロセスを埋め込む場合に推奨されます。

最初に、VM のシステム割り当てマネージド ID を使用して、Key Vault に対して認証するためのアクセス トークンを取得します。

1. ポータルで **[仮想マシン]** に移動し、Windows VM に移動して、**[概要]** の **[接続]** を選択します。
2. **Windows VM** を作成したときに追加した**ユーザー名**と**パスワード**を入力します。
3. これで、VM との**リモート デスクトップ接続**が作成されました。リモート セッションで PowerShell を開きます。
4. PowerShell では、テナント上で Web 要求を呼び出し、VM の特定のポートでローカル ホストのトークンを取得します。

Note

GCC-H などのソブリン クラウドを使用する場合、PowerShell コマンドレットでは、エンドポイント `vault.usgovcloudapi.net` ではなく `vault.azure.net` を使用します。

PowerShell 要求の例:

```powershell
$Response = Invoke-RestMethod -Uri 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fvault.azure.net' -Method GET -Headers @{Metadata="true"} 
```

Note

ソブリン クラウドを使用する場合は、コマンドレットの最後で指定されるエンドポイントを調整する必要があります。

たとえば、Azure Government クラウドを使用するときは `vault.usgovcloudapi.net` を使用する必要があります。最終的な結果は次のようになります。

`$Response = Invoke-RestMethod -Uri 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https%3A%2F%2Fvault.usgovcloudapi.net' -Method GET -Headers @{Metadata="true"`

サフィックスが環境と一致することを確認するには、[Azure Key Vault のセキュリティの概要](https://learn.microsoft.com/ja-jp/azure/key-vault/general/security-features#privileged-access)に関する記事を参照してください。

応答は次のようになります。

[Image: トークン応答を含む要求を示すスクリーンショット。]

次に、アクセス トークンを応答から抽出します。

```powershell
   $KeyVaultToken = $Response.access_token
```

最後に、PowerShell の `Invoke-WebRequest` コマンドを使用して、Key Vault で以前に作成したシークレットを取得し、Authorization ヘッダーにアクセス トークンを渡します。 Key Vault の [**概要**] ページの [**要点**] セクションにある Key Vault の URL が必要です。

```powershell
Invoke-RestMethod -Uri https://<your-key-vault-URL>/secrets/<secret-name>?api-version=2016-10-01 -Method GET -Headers @{Authorization="Bearer $KeyVaultToken"}
```

応答は次のようになります。

```powershell
  value       id                                                                                    attributes
  -----       --                                                                                    ----------
  'My Secret' https://mi-lab-vault.vault.azure.net/secrets/mi-test/50644e90b13249b584c44b9f712f2e51 @{enabled=True; created=16…
```

Key Vault からシークレットを取得した後は、名前とパスワードを必要とするサービスへの認証にそのシークレットを使用できます。

### リソースをクリーンアップする

最後に、リソースをクリーンアップする場合は、[Azure portal](https://portal.azure.com) にサインインし、**[リソース グループ]** を選択し、このチュートリアルのプロセスで作成されたリソース グループ (`mi-test` など) を見つけて選択します。 その後、**[リソース グループの削除]** コマンドを使用します。

あるいは、[PowerShell または CLI](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/delete-resource-group) を使用してリソースをクリーンアップすることもできます。

::: zone-end

::: zone pivot="identity-windows-mi-vm-access-arm"

### Windows VM のシステム割り当てマネージド ID を使用して Resource Manager にアクセスする

このチュートリアルでは、システム割り当て ID を作成し、それを Windows 仮想マシン (VM) に割り当ててから、その ID を使って [Azure Resource Manager](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview) API にアクセスする方法について説明します。 管理対象サービス ID は Azure によって自動的に管理されます。 管理対象サービス ID を使用すると、コード内に資格情報を埋め込む必要なく、Microsoft Entra の認証をサポートするサービスに認証することができます。

学習内容は次のとおりです。

- VM に Azure Resource Manager へのアクセスを許可します。
- VM のシステム割り当てマネージド ID を使って、Resource Manager にアクセスするためのアクセス トークンを取得します。

1. 管理者アカウントで [Azure Portal](https://portal.azure.com) にサインインします。
2. **[リソース グループ]** タブに移動します。
3. VM のマネージド ID にアクセスを許可する**リソース グループ**を選びます。
4. 左側のパネルで **[アクセス制御 (IAM)]** を選択します。
5. **[追加]** を選択し、 **[ロールの割り当ての追加]** を選択します。
6. **[ロール]** タブで、**[閲覧者]** を選択します。 このロールでは、すべてのリソースを表示できますが、変更を加えることはできません
7. **[メンバー]** タブの **[アクセスの割り当て先]** オプションで **[マネージド ID]** を選んでから、**[+ メンバーの選択]** を選びます。
8. **[サブスクリプション]** ドロップダウンに適切なサブスクリプションが表示されていることを確認します。 **[リソース グループ]** で **[すべてのリソース グループ]** を選びます。
9. **[Manage identity] (ID の管理)** ボックスの一覧で **[仮想マシン]** を選択します。
10. **[選択]** のドロップダウンで VM を選んでから、**[保存]** を選びます。

    [Image: マネージド ID への閲覧者ロールの追加を示すスクリーンショット。]

### アクセス トークンを取得する

VM のシステム割り当てマネージド ID を使って Resource Manager を呼び出し、アクセス トークンを取得します。

これらの手順を完了するには、SSH クライアントが必要です。 Windows を使用している場合は、[Linux 用 Windows サブシステム](https://learn.microsoft.com/ja-jp/windows/wsl/about)で SSH クライアントを使用することができます。 SSH クライアント キーの構成について支援が必要な場合は、「[Azure 上の Windows で SSH キーを使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/ssh-from-windows)」または「[Azure に Linux VM 用の SSH 公開キーと秘密キーのペアを作成して使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/mac-create-ssh-keys)」をご覧ください。

1. ポータルで Linux VM に移動し、**[概要]** の **[接続]** を選択します。
2. 任意の SSH クライアントを使用して、VM に**接続**します。
3. ターミナル ウィンドウで、`curl` を使用して、Azure リソース エンドポイントのローカル マネージド ID に、Azure Resource Manager のアクセス トークンを取得するよう要求します。 アクセス トークンの `curl` 要求を次に示します。

```bash
curl 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https://management.azure.com/' -H Metadata:true
```

Note

`resource` パラメーターの値は、Microsoft Entra ID で想定されているものと完全に一致している必要があります。 Resource Manager のリソース ID の場合は、URI の末尾にスラッシュを含める必要があります。

応答には、Azure Resource Manager へのアクセスに必要なアクセス トークンが含まれています。

応答:

```json
{
  "access_token":"eyJ0eXAiOi...",
  "refresh_token":"",
  "expires_in":"3599",
  "expires_on":"1504130527",
  "not_before":"1504126627",
  "resource":"https://management.azure.com",
  "token_type":"Bearer"
}
```

このアクセス トークンを使って Azure Resource Manager にアクセスし、たとえば、以前にこの VM にアクセスを許可したリソース グループの詳細を読み取ります。 `<SUBSCRIPTION-ID>`、 `<RESOURCE-GROUP>`、および `<ACCESS-TOKEN>` の値を先ほど作成したものと置き換えます。

Note

URL は大文字と小文字が区別されるため、前にリソース グループ名の指定で使ったものと同じ大文字と小文字の使い分けになっていること、および "resourceGroup" の "G" が大文字になっていることを確認します。

```bash
curl https://management.azure.com/subscriptions/<SUBSCRIPTION-ID>/resourceGroups/<RESOURCE-GROUP>?api-version=2016-09-01 -H "Authorization: Bearer <ACCESS-TOKEN>" 
```

特定のリソース グループの情報を含む応答が返されます。

```json
{
"id":"/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/resourceGroups/DevTest",
"name":"DevTest",
"location":"westus",
"properties":
{
  "provisioningState":"Succeeded"
  }
} 
```

::: zone-end

::: zone pivot="identity-windows-mi-vm-ua-arm"

### Windows VM 上でユーザー割り当てマネージド ID を使用して Azure Resource Manager にアクセスする

このチュートリアルでは、ユーザー割り当て ID を作成して Windows 仮想マシン (VM) に割り当て、その ID を使用して [Azure Resource Manager](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview) API にアクセスする方法について説明します。 管理対象サービス ID は Azure によって自動的に管理されます。 管理対象サービス ID を使用すると、コード内に資格情報を埋め込む必要なく、Microsoft Entra の認証をサポートするサービスに認証することができます。

学習内容は次のとおりです。

- ユーザー割り当てマネージド ID を作成する
- ユーザー割り当て ID を Windows VM に割り当てる
- Azure Resource Manager でユーザー割り当て ID にリソース グループへのアクセスを許可する
- ユーザー割り当て ID を使用してアクセス トークンを取得し、そのアクセス トークンを使用して Azure Resource Manager を呼び出す
- リソース グループのプロパティを読み取る

Note

Azure を操作するには、Azure Az PowerShell モジュールを使用することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「[AzureRM から Az への Azure PowerShell の移行](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)」を参照してください。

#### ローカルで Azure PowerShell を構成する

この例のスクリプトを実行するには、次の 2 つのオプションがあります。

- [Azure Cloud Shell](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview) を使用します。これは、コード ブロックの右上隅にある **[試してみる]** ボタンを使用して開くことができます。
- Azure PowerShell を使用して、スクリプトをローカルで実行します。次のセクションの説明を参照してください。

このチュートリアルで、(Cloud Shell を使用するのではなく) ローカルで Azure PowerShell を使用するには、次の手順を実行します。

1. [最新バージョンの Azure PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell) をインストールします (まだそうしていない場合)。
2. Azure にサインインします。

    ```azurepowershell
    Connect-AzAccount
    ```
3. [PowerShellGet の最新バージョン](https://learn.microsoft.com/ja-jp/powershell/gallery/powershellget/install-powershellget)をインストールします。

    ```azurepowershell
    Install-Module -Name PowerShellGet -AllowPrerelease
    ```

    次の手順に備えて、このコマンドを実行した後に現在の PowerShell セッションから `Exit` する必要がある場合があります。
4. リリースされたバージョンの `Az.ManagedServiceIdentity` モジュールをインストールします。 これは、このチュートリアルのユーザー割り当てマネージド ID 操作を実行するために必要です。

    ```azurepowershell
    Install-Module -Name Az.ManagedServiceIdentity -AllowPrerelease
    ```

### 有効にする

ユーザー割り当て ID に基づくシナリオの場合は、このセクションの次の手順を実行する必要があります。

1. ID を作成する。
2. 新しく作成した ID を割り当てる。

#### ID の作成

このセクションでは、ユーザー割り当て ID を作成する方法について説明します。これはスタンドアロンの Azure リソースとして作成されます。 [New-AzUserAssignedIdentity](https://learn.microsoft.com/ja-jp/powershell/module/az.managedserviceidentity/get-azuserassignedidentity) コマンドレットを使用すると、Azure の Microsoft Entra テナント内に、1 つ以上の Azure サービス インスタンスに割り当てることができる ID が作成されます。

重要

ユーザー割り当てマネージド ID を作成する場合、名前は文字または数字で始まる必要があり、英数字、ハイフン (-) とアンダースコア (\_) の組み合わせを含めることができます。 仮想マシンまたは仮想マシン スケール セットへの割り当てが適切に動作するように、名前は 24 文字に制限されています。 詳細については、[FAQ と既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/known-issues)に関するページを参照してください。

```azurepowershell
New-AzUserAssignedIdentity -ResourceGroupName myResourceGroupVM -Name ID1
```

応答には、次の例のように、作成されたユーザー割り当て ID の詳細が含まれています。 後続の手順で使用するため、ユーザー割り当て ID の `Id` と `ClientId` の値を定義します。

```azurepowershell
{
Id: /subscriptions/<SUBSCRIPTIONID>/resourcegroups/myResourceGroupVM/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1
ResourceGroupName : myResourceGroupVM
Name: ID1
Location: westus
TenantId: aaaabbbb-0000-cccc-1111-dddd2222eeee
PrincipalId: aaaaaaaa-bbbb-cccc-1111-222222222222
ClientId: 00001111-aaaa-2222-bbbb-3333cccc4444
ClientSecretUrl: https://control-westus.identity.azure.net/subscriptions/<SUBSCRIPTIONID>/resourcegroups/myResourceGroupVM/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1/credentials?tid=aaaabbbb-0000-cccc-1111-dddd2222eeee&oid=aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb&aid=00001111-aaaa-2222-bbbb-3333cccc4444
Type: Microsoft.ManagedIdentity/userAssignedIdentities
}
```

#### ID の割り当て

このセクションでは、ユーザー割り当て ID を Windows VM に割り当てる方法を説明します。 ユーザー割り当て ID は、複数の Azure リソース上のクライアントで使用できます。 単一の VM にユーザー割り当て ID を割り当てるには、次のコマンドを使用します。 `Id` パラメーターには、前の手順で返された `-IdentityID` プロパティを使用します。

```azurepowershell
$vm = Get-AzVM -ResourceGroupName myResourceGroup -Name myVM
Update-AzVM -ResourceGroupName TestRG -VM $vm -IdentityType "UserAssigned" -IdentityID "/subscriptions/<SUBSCRIPTIONID>/resourcegroups/myResourceGroupVM/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ID1"
```

### アクセス権の付与

このセクションでは、[Azure Resource Manager](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview) のリソース グループへのアクセスをユーザー割り当て ID に許可する方法を説明します。 Microsoft Entra 認証をサポートするリソース API に認証するアクセス トークンを要求するためにコードで使用する ID が、Azure リソースのマネージド ID により提供されます。 このチュートリアルでは、コードは Azure Resource Manager API にアクセスします。

コードで API にアクセスできるようにするには、事前に ID に Azure Resource Manager のリソースへのアクセスを許可する必要があります。 この例では、VM が含まれているリソース グループにアクセスします。 使用する環境に合わせて、`<SUBSCRIPTIONID>` の値を更新します。

```azurepowershell
$spID = (Get-AzUserAssignedIdentity -ResourceGroupName myResourceGroupVM -Name ID1).principalid
New-AzRoleAssignment -ObjectId $spID -RoleDefinitionName "Reader" -Scope "/subscriptions/<SUBSCRIPTIONID>/resourcegroups/myResourceGroupVM/"
```

応答には、次の例のように、作成されたロールの割り当ての詳細が含まれています。

```azurepowershell
RoleAssignmentId: /subscriptions/<SUBSCRIPTIONID>/resourcegroups/myResourceGroupVM/providers/Microsoft.Authorization/roleAssignments/00000000-0000-0000-0000-000000000000
Scope: /subscriptions/<SUBSCRIPTIONID>/resourcegroups/myResourceGroupVM
DisplayName: ID1
SignInName:
RoleDefinitionName: Reader
RoleDefinitionId: 00000000-0000-0000-0000-000000000000
ObjectId: aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb
ObjectType: ServicePrincipal
CanDelegate: False
```

### データにアクセスする

#### アクセス トークンを取得する

チュートリアルの残りの部分では、以前に作成した VM から作業を行います。

1. [Azure portal](https://portal.azure.com) にサインインします。
2. ポータルで **[仮想マシン]** に移動し、Windows VM に移動します。 **[概要]** で **[接続]** を選びます。
3. Windows VM を作成したときに使用した**ユーザー名**と**パスワード**を入力します。
4. これで、VM との**リモート デスクトップ接続**が作成されました。リモート セッションで **PowerShell** を開きます。
5. PowerShell の `Invoke-WebRequest` コマンドレットを使用して、Azure リソース エンドポイントのローカル マネージド ID に、Azure Resource Manager のアクセス トークンを取得するよう要求します。 `client_id` 値は、ユーザー割り当てマネージド ID を作成したときに返された値です。

    ```azurepowershell
    $response = Invoke-WebRequest -Uri 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&client_id=00001111-aaaa-2222-bbbb-3333cccc4444&resource=https://management.azure.com/' -Method GET -Headers @{Metadata="true"}
    $content = $response.Content | ConvertFrom-Json
    $ArmToken = $content.access_token
    ```

#### プロパティの読み取り

最後に、前の手順で取得したアクセス トークンを使用して Azure Resource Manager にアクセスし、ユーザー割り当て ID にアクセスを許可したリソース グループのプロパティを読み取ります。 `<SUBSCRIPTION ID>` は使用している環境内のサブスクリプション ID に置き換えます。

```azurepowershell
(Invoke-WebRequest -Uri https://management.azure.com/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/resourceGroups/myResourceGroupVM?api-version=2016-06-01 -Method GET -ContentType "application/json" -Headers @{Authorization ="Bearer $ArmToken"}).content
```

応答には、次の例のように、特定のリソース グループの情報が含まれています。

```json
{"id":"/subscriptions/<SUBSCRIPTIONID>/resourceGroups/myResourceGroupVM","name":"myResourceGroupVM","location":"eastus","properties":{"provisioningState":"Succeeded"}}
```

::: zone-end

### 詳細情報

- [Azure リソースのマネージド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)
- [クイックスタート: VM でユーザー割り当てマネージド ID を使用して Azure Resource Manager にアクセスする](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/tutorial-windows-vm-access)
- [Azure PowerShell を使用してユーザー割り当てマネージド ID を作成、一覧表示、削除する](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-manage-user-assigned-managed-identities?pivots=identity-mi-methods-powershell)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/managed-identities-azure-resources/tutorial-windows-vm-access"} -->
## チュートリアル: 仮想マシン (VM) でマネージド ID を使用して Azure Resource Manager にアクセスする - Managed identities for Azure resources

- Source: https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/tutorial-windows-vm-access
- Service: entra-id / managed-identities
- Article date: 2024-05-28
- Summary: 仮想マシン (VM) でシステム割り当てマネージド ID を使用して Azure Resource Manager にアクセスするプロセスについて説明するチュートリアル。

このクイックスタートでは、仮想マシン (VM) の ID としてシステム割り当てマネージド ID を使って Azure Resource Manager API にアクセスする方法について説明します。 Azure リソースのマネージド ID は Azure によって自動的に管理され、資格情報をコードに挿入しなくても、Microsoft Entra 認証をサポートするサービスへの認証を有効にします。

学習内容は次のとおりです。

- 仮想マシン (VM) に Azure Resource Manager 内のリソース グループへのアクセスを許可する
- 仮想マシン (VM) の ID を使ってアクセス トークンを取得し、それを使って Azure Resource Manager を呼び出す

::: zone pivot="windows-vm-access-wvm"

### Windows VM のシステム割り当てマネージド ID を使用して Resource Manager にアクセスする

このチュートリアルでは、システム割り当て ID を作成し、それを Windows 仮想マシン (VM) に割り当ててから、その ID を使って [Azure Resource Manager](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview) API にアクセスする方法について説明します。 管理対象サービス ID は Azure によって自動的に管理されます。 管理対象サービス ID を使用すると、コード内に資格情報を埋め込む必要なく、Microsoft Entra の認証をサポートするサービスに認証することができます。

学習内容は次のとおりです。

- VM に Azure Resource Manager へのアクセスを許可します。
- VM のシステム割り当てマネージド ID を使って、Resource Manager にアクセスするためのアクセス トークンを取得します。

1. 管理者アカウントで [Azure Portal](https://portal.azure.com) にサインインします。
2. **[リソース グループ]** タブに移動します。
3. VM のマネージド ID にアクセスを許可する**リソース グループ**を選びます。
4. 左側のパネルで **[アクセス制御 (IAM)]** を選択します。
5. **[追加]** を選択し、 **[ロールの割り当ての追加]** を選択します。
6. **[ロール]** タブで、**[閲覧者]** を選択します。 このロールでは、すべてのリソースを表示できますが、変更を加えることはできません
7. **[メンバー]** タブの **[アクセスの割り当て先]** オプションで **[マネージド ID]** を選んでから、**[+ メンバーの選択]** を選びます。
8. **[サブスクリプション]** ドロップダウンに適切なサブスクリプションが表示されていることを確認します。 **[リソース グループ]** で **[すべてのリソース グループ]** を選びます。
9. **[Manage identity] (ID の管理)** ボックスの一覧で **[仮想マシン]** を選択します。
10. **[選択]** のドロップダウンで VM を選んでから、**[保存]** を選びます。

    [Image: マネージド ID への閲覧者ロールの追加を示すスクリーンショット。]

### アクセス トークンを取得する

VM のシステム割り当てマネージド ID を使って Resource Manager を呼び出し、アクセス トークンを取得します。

これらの手順を完了するには、SSH クライアントが必要です。 Windows を使用している場合は、[Linux 用 Windows サブシステム](https://learn.microsoft.com/ja-jp/windows/wsl/about)で SSH クライアントを使用することができます。 SSH クライアント キーの構成について支援が必要な場合は、「[Azure 上の Windows で SSH キーを使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/ssh-from-windows)」または「[Azure に Linux VM 用の SSH 公開キーと秘密キーのペアを作成して使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/mac-create-ssh-keys)」をご覧ください。

1. ポータルで Linux VM に移動し、**[概要]** の **[接続]** を選択します。
2. 任意の SSH クライアントを使用して、VM に**接続**します。
3. ターミナル ウィンドウで、`curl` を使用して、Azure リソース エンドポイントのローカル マネージド ID に、Azure Resource Manager のアクセス トークンを取得するよう要求します。 アクセス トークンの `curl` 要求を次に示します。

```bash
curl 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https://management.azure.com/' -H Metadata:true
```

注

`resource` パラメーターの値は、Microsoft Entra ID で想定されているものと完全に一致している必要があります。 Resource Manager のリソース ID の場合は、URI の末尾にスラッシュを含める必要があります。

応答には、Azure Resource Manager へのアクセスに必要なアクセス トークンが含まれています。

応答:

```json
{
  "access_token":"eyJ0eXAiOi...",
  "refresh_token":"",
  "expires_in":"3599",
  "expires_on":"1504130527",
  "not_before":"1504126627",
  "resource":"https://management.azure.com",
  "token_type":"Bearer"
}
```

このアクセス トークンを使って Azure Resource Manager にアクセスし、たとえば、以前にこの VM にアクセスを許可したリソース グループの詳細を読み取ります。 `<SUBSCRIPTION-ID>`、 `<RESOURCE-GROUP>`、および `<ACCESS-TOKEN>` の値を先ほど作成したものと置き換えます。

注

URL は大文字と小文字が区別されるため、前にリソース グループ名の指定で使ったものと同じ大文字と小文字の使い分けになっていること、および "resourceGroup" の "G" が大文字になっていることを確認します。

```bash
curl https://management.azure.com/subscriptions/<SUBSCRIPTION-ID>/resourceGroups/<RESOURCE-GROUP>?api-version=2016-09-01 -H "Authorization: Bearer <ACCESS-TOKEN>" 
```

特定のリソース グループの情報を含む応答が返されます。

```json
{
"id":"/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/resourceGroups/DevTest",
"name":"DevTest",
"location":"westus",
"properties":
{
  "provisioningState":"Succeeded"
  }
} 
```

::: zone-end

::: zone pivot="windows-vm-access-lvm"

### Linux VM のシステム割り当てマネージド ID を使用して Resource Manager のリソース グループにアクセスする

このチュートリアルでは、システム割り当て ID を作成し、それを Linux 仮想マシン (VM) に割り当ててから、その ID を使って [Azure Resource Manager](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview) API にアクセスする方法について説明します。 管理対象サービス ID は Azure によって自動的に管理されます。 管理対象サービス ID を使用すると、コード内に資格情報を埋め込む必要なく、Microsoft Entra の認証をサポートするサービスに認証することができます。

学習内容は次のとおりです。

- VM に Azure Resource Manager へのアクセスを許可します。
- VM のシステム割り当てマネージド ID を使って、Resource Manager にアクセスするためのアクセス トークンを取得します。

1. 管理者アカウントで [Azure Portal](https://portal.azure.com) にサインインします。
2. **[リソース グループ]** タブに移動します。
3. VM のマネージド ID にアクセスを許可する**リソース グループ**を選びます。
4. 左側のパネルで **[アクセス制御 (IAM)]** を選択します。
5. **[追加]** を選択し、 **[ロールの割り当ての追加]** を選択します。
6. **[ロール]** タブで、**[閲覧者]** を選択します。 このロールでは、すべてのリソースを表示できますが、変更を加えることはできません
7. **[メンバー]** タブの **[アクセスの割り当て先]** オプションで **[マネージド ID]** を選んでから、**[+ メンバーの選択]** を選びます。
8. **[サブスクリプション]** ドロップダウンに適切なサブスクリプションが表示されていることを確認します。 **[リソース グループ]** で **[すべてのリソース グループ]** を選びます。
9. **[ID の管理]** ドロップダウンで **[仮想マシン]** を選びます。
10. **[選択]** オプションのドロップダウンで VM を選んでから、**[保存]** を選びます。

    [Image: マネージド ID への閲覧者ロールの追加を示すスクリーンショット。]

### アクセス トークンを取得する

VM のシステム割り当てマネージド ID を使ってリソース マネージャーを呼び出し、アクセス トークンを取得します。

これらの手順を完了するには、SSH クライアントが必要です。 Windows を使用している場合は、[Linux 用 Windows サブシステム](https://learn.microsoft.com/ja-jp/windows/wsl/about)で SSH クライアントを使用することができます。 SSH クライアント キーの構成について支援が必要な場合は、「[Azure 上の Windows で SSH キーを使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/ssh-from-windows)」または「[Azure に Linux VM 用の SSH 公開キーと秘密キーのペアを作成して使用する方法](https://learn.microsoft.com/ja-jp/azure/virtual-machines/linux/mac-create-ssh-keys)」をご覧ください。

1. Azure portal で Linux VM に移動します。
2. **[概要]** で **[接続]** を選びます。
3. 任意の SSH クライアントを使用して、VM に**接続**します。
4. ターミナル ウィンドウで、`curl` を使って、ローカル環境の Azure リソース用マネージド ID エンドポイントに、Azure Resource Manager 用のアクセス トークンの取得を要求します。 アクセス トークンの `curl` 要求を次に示します。

```bash
curl 'http://169.254.169.254/metadata/identity/oauth2/token?api-version=2018-02-01&resource=https://management.azure.com/' -H Metadata:true
```

注

`resource` パラメーターの値は、Microsoft Entra ID で想定されているものと完全に一致している必要があります。 Resource Manager のリソース ID の場合は、URI の末尾にスラッシュを含める必要があります。

応答に、Azure Resource Manager へのアクセスに必要なアクセス トークンが含まれています。

応答:

```json
{
  "access_token":"eyJ0eXAiOi...",
  "refresh_token":"",
  "expires_in":"3599",
  "expires_on":"1504130527",
  "not_before":"1504126627",
  "resource":"https://management.azure.com",
  "token_type":"Bearer"
}
```

このアクセス トークンを使って、Azure Resource Manager にアクセスします。 たとえば、以前にこの VM にアクセスを許可したリソース グループの詳細を読み取ります。 `<SUBSCRIPTION-ID>`、 `<RESOURCE-GROUP>`、および `<ACCESS-TOKEN>` の値を先ほど作成したものと置き換えます。

注

URL は大文字と小文字が区別されるため、前にリソース グループ名の指定で使ったものと同じ大文字と小文字の使い分けになっていること、および `resourceGroup` の "G" が大文字になっていることを確認します。

```bash
curl https://management.azure.com/subscriptions/<SUBSCRIPTION-ID>/resourceGroups/<RESOURCE-GROUP>?api-version=2016-09-01 -H "Authorization: Bearer <ACCESS-TOKEN>" 
```

特定のリソース グループの情報を含む応答が返されます。

```json
{
"id":"/subscriptions/aaaa0a0a-bb1b-cc2c-dd3d-eeeeee4e4e4e/resourceGroups/DevTest",
"name":"DevTest",
"location":"westus",
"properties":
{
  "provisioningState":"Succeeded"
  }
} 
```

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/multi-tenant-organizations"} -->
## マルチテナント組織のドキュメント - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations
- Service: entra-id / multitenant-organizations
- Article date: 2024-04-23
- Summary: マルチテナント組織について説明します。

マルチテナント組織は、Microsoft Entra ID の複数のインスタンスを持つ組織です。 ユーザーが複数のテナント間でリソースにアクセスし、共同作業を行うシームレスなエクスペリエンスを実現する方法について説明します。

### マルチテナント組織について

#### 概要

- [マルチテナント組織の機能](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/overview)
- [マルチテナント機能の比較](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/overview#compare-multitenant-capabilities)

### マルチテナント組織を構成する

#### 概要

- [マルチテナント組織とは?](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-overview)

#### 攻略ガイド

- [Microsoft 365 管理センター](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/set-up-multi-tenant-org)
- [PowerShell または Microsoft Graph API](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-configure-graph)

### テナント間同期を構成する

#### 概要

- [テナント間同期とは?](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)

#### 攻略ガイド

- [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)
- [PowerShell または Microsoft Graph API](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure-graph)

### Microsoft 365 での共同作業

#### 概念

- [Microsoft 365 の ID プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-microsoft-365)
- [Microsoft 365 マルチテナントユーザー検索](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/multi-tenant-people-search)
- [Microsoft 365 でのマルチテナント組織の計画](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/plan-multi-tenant-org-overview)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure"} -->
## テナント間同期を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure
- Service: entra-id / multitenant-organizations
- Article date: 2026-08-06
- Summary: Microsoft Entra 管理センターを使用して、テナント間の同期を構成します。 信頼設定、プロビジョニング スコープ、属性マッピング、テストに関するステップ バイ ステップ ガイド。

### 概要

::: zone pivot="same-cloud-synchronization"

この記事では、Microsoft Entra 管理センターを利用してテナント間同期を構成する手順について説明します。 構成すると、Microsoft Entra ID によって、ターゲット テナント内の B2B ユーザーとセキュリティ グループが自動的にプロビジョニングおよび解除されます。

このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

[Image: ソース テナントとターゲット テナントの間のテナント間の同期を示す図。]

::: zone-end

::: zone pivot="cross-cloud-synchronization"

この記事では、Microsoft クラウド間のテナント間同期を構成する手順について説明します。 構成すると、Microsoft Entra ID により、ターゲット テナント内の B2B ユーザーが自動的にプロビジョニングおよび解除されます。 このチュートリアルでは、商用クラウド (米国政府&gt; ) からの ID の同期に重点を置いていますが、中国 --&gt; 商用と商用 --&gt; の政府機関にも同じ手順が適用されます。

このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。 テナント間同期とクラウド間同期の違いについては、 [よく寄せられる質問のクラウド間同期に関するページ](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview#clouds)を参照してください。

[Image: ソース テナントとターゲット テナントの間のクラウド間の同期を示す図。]

::: zone-end

### サポートされているクラウド ペア

::: zone pivot="same-cloud-synchronization"

テナント間同期では、次のクラウド ペアがサポートされます。

| 情報源 | 目標 | Azure portal のリンク ドメイン |
| --- | --- | --- |
| Azure 商用 | Azure 商用 | `portal.azure.com` --&gt;`portal.azure.com` |
| Azure Government | Azure Government | `portal.azure.us` --&gt;`portal.azure.us` |
| 21Vianet (中国) | 21Vianet (中国) | `portal.azure.cn` --&gt;`portal.azure.cn` |

::: zone-end

::: zone pivot="cross-cloud-synchronization"

クラウド間同期では、次のクラウド ペアがサポートされます。

| 情報源 | 目標 | Azure portal のリンク ドメイン |
| --- | --- | --- |
| Azure 商用 | Azure Government | `portal.azure.com` --&gt;`portal.azure.us` |
| Azure Government | Azure 商用 | `portal.azure.us` --&gt;`portal.azure.com` |
| Azure 商用 | 21Vianet によって運営される Azure(中国の Azure) | `portal.azure.com` --&gt;`portal.azure.cn` |

::: zone-end

### 学習の目的

この記事を最後まで読むと、次のことができるようになります。

::: zone pivot="same-cloud-synchronization"

- ターゲット テナントに B2B ユーザーとセキュリティ グループを作成する
- ターゲット テナント内の B2B ユーザーとセキュリティ グループを削除する
- ソースとターゲットのテナント間でユーザー属性の同期を維持する

::: zone-end

::: zone pivot="cross-cloud-synchronization"

- ターゲット テナントで B2B ユーザーを作成する
- ターゲット テナントで B2B ユーザーを削除する
- ソースとターゲットのテナント間でユーザー属性の同期を維持する

::: zone-end

### 前提条件

[Image: ソース テナントのアイコン。]**ソース テナント**

::: zone pivot="same-cloud-synchronization"

- テナント間ユーザー同期用の Microsoft Entra ID P1 または P2 ライセンス。テナント間グループ同期用の Microsoft Entra ID ガバナンスまたは Microsoft Entra スイート ライセンス。詳細については、「 [ライセンス要件](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview#license-requirements)」を参照してください。
- テナント間アクセス設定を構成するための[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)ロール。
- テナント間同期を構成するための[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロール。
- 構成にユーザーを割り当て、構成を削除する[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)または[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)ロール。

::: zone-end

::: zone pivot="cross-cloud-synchronization"

- Microsoft Entra ID ガバナンスまたは Microsoft Entra スイート ライセンス。 詳細については、「 [ライセンス要件](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview#license-requirements)」を参照してください。
- テナント間アクセス設定を構成するための[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)ロール。
- テナント間同期を構成するための[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロール。
- 構成にユーザーを割り当て、構成を削除する[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)または[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)ロール。

::: zone-end

[Image: ターゲット テナントのアイコン。]**ターゲット テナント**

- テナント間アクセス設定を構成するための[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)ロール。

::: zone pivot="same-cloud-synchronization"

### 手順 1: プロビジョニングの展開を計画する

1. [組織内のテナントを構成](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-topology)する方法を定義します。
2. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-provisioning-works)します。
3. プロビジョニングの対象となるユーザー [を決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts?pivots=cross-tenant-synchronization)。
4. [テナント間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

::: zone-end

::: zone pivot="cross-cloud-synchronization"

### 手順 1: 両方のテナントでクラウド間設定を有効にする

[Image: ソース テナントのアイコン。]**ソース テナント**

1. ソース テナントの [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **[Entra ID]**&gt;**[External Identities]**&gt;**[クロステナント アクセス設定]** に移動します。
3. [ **Microsoft クラウド設定** ] タブで、共同作業を行うクラウド ( **Microsoft Azure Government** など) のチェック ボックスをオンにします。

    クラウドの一覧は、使用しているクラウドによって異なります。 詳細については、「 [Microsoft クラウド設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview#microsoft-cloud-settings)」を参照してください。

    [Image: 共同作業するさまざまな Microsoft クラウドのチェック ボックスを示す Microsoft クラウド設定のスクリーンショット。]
4. **[保存] を選択します**。

[Image: ターゲット テナントのアイコン。]**ターゲット**

1. ターゲット テナントの [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **[Entra ID]**&gt;**[External Identities]**&gt;**[クロステナント アクセス設定]** に移動します。
3. [ **Microsoft クラウド設定** ] タブで、ソース テナントのクラウド間同期チェック ボックス ( **Microsoft Azure Commercial** など) を選択します。

    [Image: クラウド間同期を有効にするチェック ボックスを示す Microsoft クラウド設定のスクリーンショット。]

    このチェック ボックスをオンにすると、次のアクセス許可を持つサービス プリンシパルが作成されます。

    - User.ReadWrite.CrossCloud
    - User.Invite.All
    - Organization.Read.All
    - Policy.Read.All
4. **[保存] を選択します**。

::: zone-end

::: zone pivot="same-cloud-synchronization"

### 手順 2: ターゲット テナントでユーザーとグループの同期を有効にする

::: zone-end

::: zone pivot="cross-cloud-synchronization"

### 手順 2: ターゲット テナントでユーザーの同期を有効にする

::: zone-end

[Image: ターゲット テナントのアイコン。]**ターゲット テナント**

1. ターゲット テナントの [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **[Entra ID]**&gt;**[External Identities]**&gt;**[クロステナント アクセス設定]** に移動します。
3. [ **組織の設定** ] タブで、[ **組織の追加]** を選択します。
4. テナント ID またはドメイン名を入力し、[追加] を選択して、ソース テナントを **追加**します。

    [Image: ソース テナントを追加する [組織の追加] ウィンドウを示すスクリーンショット。]
5. 追加した組織の **受信アクセス** で、[ **既定から継承**] を選択します。
6. [ **クロステナント同期** ] タブを選択します。

::: zone pivot="same-cloud-synchronization"

1. [ **このテナントへのユーザー同期を許可する** ] チェック ボックスをオンにします。

    必要に応じて、[ **このテナントへのグループ同期を許可する** ] チェック ボックスをオンにします。

    詳細については、「 [グループ同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview#group-synchronization)」を参照してください。

    [Image: [このテナントへのユーザー同期を許可する] チェック ボックスと [このテナントへのグループ同期を許可する] チェックボックスが表示された [クロステナント同期] タブを示すスクリーンショット。]

::: zone-end

::: zone pivot="cross-cloud-synchronization"

1. [ **このテナントへのユーザー同期を許可する** ] チェック ボックスをオンにします。

::: zone-end

1. **[保存] を選択します**。
2. **テナント間の同期と自動引き換えの有効化** ダイアログ ボックスが表示された場合、自動引き換えを有効にするか尋ねられます。 [ **はい**] を選択してください。

    [ **はい** ] を選択すると、ターゲット テナントで招待が自動的に引き換えられます。

    [Image: スクリーンショットは [クロステナント同期と自動引き換えの有効化] ダイアログ ボックスを示しており、ターゲットテナントで招待を自動的に引き換える機能があります。]

### 手順 3: ターゲット テナントで招待を自動的に引き換える

[Image: ターゲット テナントのアイコン。]**ターゲット テナント**

このステップでは、ソース テナントのユーザーが同意プロンプトに同意する必要がないように、招待を自動的に引き換えます。 この設定は、ソース テナント (アウトバウンド) とターゲット テナント (インバウンド) の両方でオンにする必要があります。 詳細については、「 [自動引き換え設定](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview#automatic-redemption-setting)」を参照してください。

1. ターゲット テナントの同じ **[受信アクセス設定** ] ページで、[ **信頼設定** ] タブを選択します。
2. **[テナントで招待を自動的に引き換える]**&lt;[テナント]&gt; チェック ボックスをオンにします。

    [**テナント間の同期と自動引き換えの有効化**] ダイアログ ボックスで以前に [**はい**] を選択した場合、このボックスは既にオンになっている可能性があります。

    [Image: [受信自動引き換え] チェックボックスを示すスクリーンショット。]
3. **[保存] を選択します**。

### 手順 4: ソース テナントで招待を自動的に引き換える

[Image: ソース テナントのアイコン。]**ソース テナント**

このステップでは、ソース テナントで招待を自動的に受け入れます。

1. ソース テナントの [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **[Entra ID]**&gt;**[External Identities]**&gt;**[クロステナント アクセス設定]** に移動します。
3. [ **組織の設定** ] タブで、[ **組織の追加]** を選択します。
4. テナント ID またはドメイン名を入力し、[追加] を選択して、ターゲット テナントを **追加**します。

    [Image: ターゲット テナントを追加する [組織の追加] ウィンドウを示すスクリーンショット。]
5. ターゲット組織の**送信アクセス**で、**既定から継承**を選択します。
6. [ **信頼** 設定] タブを選択します。
7. **[テナントで招待を自動的に引き換える]**&lt;[テナント]&gt; チェック ボックスをオンにします。

    [Image: [送信自動引き換え] チェックボックスを示すスクリーンショット。]
8. **[保存] を選択します**。

### 手順 5: ソース テナントで構成を作成する

[Image: ソース テナントのアイコン。]**ソース テナント**

1. ソース テナントで、**Entra ID**&gt;**クロス テナント同期**に移動します。

    [Image: Microsoft Entra 管理センターのテナント間同期ナビゲーションを示すスクリーンショット。]

    Azure portal を使用している場合は、**Microsoft Entra ID**&gt;の**管理**&gt;**クロステナント同期**に移動します。

    [Image: Azure portal のテナント間同期ナビゲーションを示すスクリーンショット。]
2. **[構成] を選択します**。
3. ページの上部にある [ **新しい構成**] を選択します。

::: zone pivot="same-cloud-synchronization"

1. 構成の名前を指定します。

    [Image: 名前を示す新しい構成のスクリーンショット。]
2. **を選択して**を作成します。

    作成した構成が一覧に表示されるまで、最大で 15 秒かかる場合があります。

::: zone-end

::: zone pivot="cross-cloud-synchronization"

1. 構成の名前を指定します。
2. [ **Microsoft クラウド間のテナント間同期のセットアップ** ] チェック ボックスをオンにします。

    [Image: 名前とクラウド間同期のチェック ボックスを示す新しい構成のスクリーンショット。]
3. **を選択して**を作成します。

    作成した構成が一覧に表示されるまで、最大で 15 秒かかる場合があります。

    クラウド間同期の [構成] ページで、[ **テナント名]** 列と [ **テナント ID** ] 列が空になります。

::: zone-end

### 手順 6: ターゲット テナントへの接続をテストする

[Image: ソース テナントのアイコン。]**ソース テナント**

1. ソース テナントに新しい構成が表示されるはずです。 表示されない場合、構成リストで構成を選択します。

    [Image: [クロステナント同期の構成] ページと新しい構成を示すスクリーンショット。]
2. **[新しい構成]** を選択して、新しいプロビジョニング構成を作成します。
3. [ **管理者資格情報** ] セクションの [ **テナント ID** ] ボックスに、ターゲット テナントのテナント ID を入力します。

    [Image: クロステナント同期ポリシーが選択された [プロビジョニング] ページを示すスクリーンショット。]
4. **[テスト接続]** を選択して接続をテストします。

    指定した資格情報でプロビジョニングを有効にすることが承認されたことを示すメッセージが表示されます。 テスト接続が失敗した場合は、この記事で後述する 一般的なテナント間同期シナリオのトラブルシューティング を参照してください。

    [Image: テスト接続通知を示すスクリーンショット。]
5. **を選択して**を作成します。

    新しいプロビジョニング構成の作成には数秒かかる場合があります。

### 手順 7: プロビジョニングの対象となるユーザーを定義する

[Image: ソース テナントのアイコン。]**ソース テナント**

Microsoft Entra プロビジョニング サービスを使うと、次のいずれかまたは両方の方法でプロビジョニングを行うユーザーを定義できます。

- 構成への割り当てに基づく
- ユーザーの属性に基づく

小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーでテストします。 プロビジョニングのスコープを割り当て済みのユーザーとグループに設定すると、構成に 1 人または 2 人のユーザーを割り当てることで、それを制御できます。 次の手順で説明する属性ベースのスコープ フィルターを作成することで、プロビジョニングの対象となるユーザーをさらに絞り込むことができます。

1. ソース テナントの **[概要** ] ページで、[ **プロパティ** ] タブを選択します。

    [Image: [プロパティ] タブを示す [概要] ページのスクリーンショット。]
2. [ **基本** ] 見出しの横にある鉛筆アイコンを選択して、[ **基本** ] ウィンドウを開きます。

    [Image: [スコープ] オプションと [プロビジョニング状態] オプションが表示された [設定] セクションを示す [プロビジョニング] ページのスクリーンショット。]
3. **[スコープ**] ボックスの一覧で、ソース テナント内のすべてのユーザーを同期するか、構成に割り当てられているユーザーのみを同期するかを選択します。

    [すべてのユーザーを同期する] ではなく、[**割り当てられたユーザーのみを同期** **する**] を選択することをお勧めします。 スコープ内のユーザーの数を減らすと、パフォーマンスが向上します。

::: zone pivot="same-cloud-synchronization"

グループを同期する場合は、[ **割り当てられたユーザーとグループのみを同期する**] を選択する必要があります。

::: zone-end
4. 変更を加えた場合は、[ **適用**] を選択します。
5. [構成] ページで、[ **ユーザーとグループ**] を選択します。

    テナント間同期が機能するには、少なくとも 1 人の内部ユーザーを構成に割り当てる必要があります。
6. [ **ユーザー/グループの追加]** を選択します。
7. [ **割り当ての追加** ] ページの [ **ユーザーとグループ**] で、[ **選択なし**] を選択します。
8. [ **ユーザーとグループ** ] ウィンドウで、構成に割り当てる 1 つ以上の内部ユーザーとグループを検索して選択します。

    グループを選んで構成に割り当てた場合、グループ内の直接メンバーであるユーザーのみがプロビジョニングの対象になります。 静的グループまたは動的グループを選択できます。 割り当ては、入れ子になったグループにはカスケードされません。
9. **[選択]** を選択します。
10. **割り当て**を選択します。

    [Image: ユーザーが構成に割り当てられている [ユーザーとグループ] ページを示すスクリーンショット。]

    詳細については、「 [アプリケーションにユーザーとグループを割り当てる」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)参照してください。

### ステップ 8: (省略可能) スコープ フィルターを使用してプロビジョニングの対象となるユーザーを指定します。

[Image: ソース テナントのアイコン。]**ソース テナント**

前の手順で **[スコープ** ] に選択した値に関係なく、属性ベースのスコープ フィルターを作成することで、同期するユーザーをさらに制限できます。

1. ソース テナントで [ **プロビジョニング** ] を選択し、[マッピング] セクション **を** 展開します。

    [Image: [マッピング] セクションが展開された [プロビジョニング] ページを示すスクリーンショット。]
2. [**Microsoft Entra ID ユーザーのプロビジョニング**] を選択して、[**属性マッピング**] ページを開きます。
3. [ **ソース オブジェクト スコープ] で**、[ **すべてのレコード**] を選択します。

    [Image: [ソース オブジェクト スコープ] の [属性マッピング] ページを示すスクリーンショット。]
4. [ **ソース オブジェクト スコープ] ページで** 、[ **スコープ フィルターの追加]** を選択します。
5. プロビジョニング用のスコープに含まれるユーザーを定義するフィルターを追加します。

    スコープ フィルターを構成するには、スコープ フィルターを使用して [プロビジョニングするスコープ ユーザーまたはグループ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts?pivots=cross-tenant-synchronization)に関するページに記載されている手順を参照してください。

    [Image: サンプル フィルターを含む [スコープ フィルターの追加] ページを示すスクリーンショット。]
6. [ **OK] を** 選択し **、[保存]** を選択して変更を保存します。

    フィルターを追加した場合、変更を保存すると、割り当てられたすべてのユーザーとグループが再同期されることを示すメッセージが表示されます。 ディレクトリのサイズによっては、これに長い時間かかる場合があります。
7. [ **はい** ] を選択し、[ **属性マッピング** ] ページを閉じます。

::: zone pivot="same-cloud-synchronization"

1. [ **プロビジョニング** ] ページの [ **マッピング** ] セクションで、[ **Microsoft Entra ID グループのプロビジョニング** ] を選択して **[属性マッピング** ] ページを開きます。
2. グループを同期する場合は、[ **有効]** トグルを **[はい**] に設定します。

    既定では、このトグルは **[いいえ**] に設定されています。
3. グループのフィルターをスコープ設定する場合は、ユーザーと同様の前の手順に従います。

::: zone-end

### 手順 9: 属性マッピングを確認する

[Image: ソース テナントのアイコン。]**ソース テナント**

属性マッピングを使うと、ソース テナントとターゲット テナントの間でデータが流れる方法を定義できます。 既定の属性マッピングをカスタマイズする方法については、「 [チュートリアル - Microsoft Entra ID で SaaS アプリケーションのユーザー プロビジョニング属性マッピングをカスタマイズする」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)参照してください。

1. ソース テナントで、[ **属性マッピング**] を選択します。
2. [ **属性マッピング** ] ページで、下にスクロールして、テナント間で同期されるユーザー属性を確認します。

    **AltSecIdFromNetId([netId]) (alternativeSecurityIds)** は、テナント間でユーザーを一意に識別し、ソース テナント内のユーザーをターゲット テナント内の既存のユーザーと照合し、各ユーザーがアカウントを 1 つだけ持っていることを確認するために使用される内部属性です。 一致する属性は変更できません。 一致属性の変更または一致属性の追加を試みると、`schemaInvalid` エラーが発生します。

    [Image: Microsoft Entra 属性の一覧を示す [属性マッピング] ページのスクリーンショット。]
3. **Member (userType)** 属性の場合は、鉛筆アイコンを選択して [**属性マッピングの編集]** ページを開きます。
4. **定数属性**の設定を確認します。この設定は既定で **[メンバー**] に設定されています。

    この設定では、ターゲット テナントに作成されるユーザーの種類を定義し、次の表のいずれかの値を指定できます。 既定では、ユーザーは外部メンバー (B2B コラボレーション ユーザー) として作成されます。 詳細については、「 [Microsoft Entra B2B コラボレーション ユーザーのプロパティ」](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)を参照してください。

    | 定数属性 | 説明 |
    | --- | --- |
    | **メンバー** | 既定値。 ユーザーは、外部メンバー (B2B コラボレーション ユーザー) としてターゲット テナントに作成されます。 ユーザーは、ターゲット テナントの任意の内部メンバーとして機能できます。 |
    | **ゲスト** | ユーザーは、外部ゲスト (B2B コラボレーション ユーザー) としてターゲット テナントに作成されます。 |

    注

    B2B ユーザーが既にターゲット テナントに存在する場合、[**このマッピングの適用**] 設定が **[常**に] に設定されていない限り、**Member (userType)** は**メンバー**に変更されません。

    選択したユーザーの種類には、アプリまたはサービスに対して次の制限があります (ただし、これらに限定されません)。

    | アプリまたはサービス | 制限事項 |
    | --- | --- |
    | Azure Virtual Desktop | 制限事項については、[Azure Virtual Desktop の前提条件](https://learn.microsoft.com/ja-jp/azure/virtual-desktop/prerequisites#users)を参照してください。 |
    | Microsoft Teams | 制限事項については、「[他のMicrosoft 365クラウド環境からのゲストとの連携](https://learn.microsoft.com/ja-jp/microsoft-365/solutions/collaborate-guests-cross-cloud)を参照してください。 |

    [Image: メンバー属性を示す [属性の編集] ページのスクリーンショット。]
5. 変換を定義する場合は、[ **属性マッピング** ] ページで、変換する属性の鉛筆アイコン ( **displayName** など) を選択します。
6. **[マッピング] の種類**を **[式]** に設定します。
7. [ **式** ] ボックスに、変換式を入力します。 たとえば、表示名の場合は、次のようにできます。

    - 名と姓を入れ替えて、その間にコンマを追加します。
    - 表示名の最後に、かっこで囲んだドメイン名を追加します。

    例については、 [Microsoft Entra ID での属性マッピングの式の記述に関するリファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#examples)を参照してください。

    [Image: displayName 属性と式ボックスを表示する「属性の編集」ページのスクリーンショット。]

::: zone pivot="same-cloud-synchronization"

1. [ **プロビジョニング** ] ページの [ **マッピング** ] セクションで、[ **Microsoft Entra ID グループのプロビジョニング** ] を選択して **[属性マッピング** ] ページを開きます。
2. グループの属性マッピングを変更する場合は、ユーザーと同様の前の手順に従います。

::: zone-end

ヒント

テナント間同期のスキーマを更新することで、ディレクトリ拡張機能をマップできます。 詳細については、「 [テナント間同期でのディレクトリ拡張機能のマップ](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-directory-extensions)」を参照してください。

### 手順 10: 追加のプロビジョニング設定を指定する

[Image: ソース テナントのアイコン。]**ソース テナント**

1. ソース テナントの **[概要** ] ページで、[ **プロパティ** ] タブを選択します。
2. [ **基本** ] 見出しの横にある鉛筆アイコンを選択して、[ **基本** ] ウィンドウを開きます。

    [Image: [スコープ] オプションと [プロビジョニング状態] オプションが表示された [設定] セクションを示す [プロビジョニング] ページのスクリーンショット。]
3. [ **通知メール** ] ボックスに、プロビジョニング エラー通知を受け取るユーザーまたはグループのメール アドレスを入力します。

    ジョブが検疫状態になってから 24 時間以内に、メール通知が送信されます。 カスタム アラートについては、「プロビジョニングと [Azure Monitor ログの統合方法について」](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-log-analytics)を参照してください。
4. 誤って削除されないようにするには、[ **誤って削除されないように** する] を選択し、しきい値を指定します。 既定では、しきい値は 500 に設定されます。

    詳細については、「 [Microsoft Entra プロビジョニング サービスで誤削除防止を有効にする」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/accidental-deletions?pivots=cross-tenant-synchronization)参照してください。
5. [ **適用]** を選択して変更を保存します。

### 手順 11: オンデマンドのプロビジョニングをテストする

[Image: ソース テナントのアイコン。]**ソース テナント**

構成が完了したので、いずれかのユーザーでオンデマンド プロビジョニングをテストできます。

1. ソース テナントで、**Entra ID**&gt;**クロス テナント同期**に移動します。
2. [ **構成]** を選択し、構成を選択します。
3. **オンデマンドプロビジョン** を選択します。
4. [ **ユーザーまたはグループの選択** ] ボックスで、いずれかのテスト ユーザーを検索して選択します。

    [Image: テスト ユーザーが選択されていることを示す [オンデマンドプロビジョニング] ページのスクリーンショット。]
5. **プロビジョン** を選択します。

    しばらくすると、[ **アクションの実行** ] ページが表示され、ターゲット テナントでのテスト ユーザーのプロビジョニングに関する情報が表示されます。

    [Image: テスト ユーザーと変更された属性の一覧を示す [アクションの実行] ページのスクリーンショット。]

    ユーザーがスコープ内にない場合は、テスト ユーザーがスキップされた理由に関する情報を含むページが表示されます。

    [Image: [ユーザーがスコープ内にあるかどうかを確認する] ページでテスト ユーザーがスキップされた理由に関する情報を示すスクリーンショット。]

    [ **オンデマンドプロビジョニング** ] ページでは、プロビジョニングに関する詳細を表示し、再試行するオプションを選択できます。

    [Image: プロビジョニングの詳細を表示する [オンデマンドプロビジョニング] ページのスクリーンショット。]
6. ターゲット テナントで、テスト ユーザーがプロビジョニングされたことを確認します。

    [Image: プロビジョニングされたテスト ユーザーを示すターゲット テナントの [ユーザー] ページのスクリーンショット。]
7. すべてが期待どおりに動作している場合は、構成に追加のユーザーを割り当てます。

    詳細については、「 [Microsoft Entra ID でのオンデマンド プロビジョニング」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand?pivots=cross-tenant-synchronization)参照してください。

### 手順 12: プロビジョニング ジョブを開始する

[Image: ソース テナントのアイコン。]**ソース テナント**

プロビジョニング ジョブは**、[設定]** セクションの**スコープ**で定義されているすべてのユーザーの初期同期サイクルを開始します。 初期サイクルは後続の同期よりも実行に時間がかかります。後続のサイクルは、Microsoft Entra のプロビジョニング サービスが実行されている限り約 40 分ごとに実行されます。

1. ソース テナントで、**Entra ID**&gt;**クロス テナント同期**に移動します。
2. [ **構成]** を選択し、構成を選択します。
3. [ **概要** ] ページで、プロビジョニングの詳細を確認します。

    [Image: プロビジョニングの詳細を一覧表示する [構成の概要] ページのスクリーンショット。]
4. [ **プロビジョニングの開始]** を選択して、プロビジョニング ジョブを開始します。

### 手順 13: プロビジョニングを監視する

[Image: ソース テナントのアイコン。][Image: ターゲット テナントのアイコン。]**ソースとターゲット テナント**

プロビジョニング ジョブを開始したら、状態を監視できます。

1. ソース テナントの **[概要** ] ページで、進行状況バーを確認して、プロビジョニング サイクルの状態と完了までの時間を確認します。 詳細については、「 [ユーザー プロビジョニングの状態を確認する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)参照してください。

    プロビジョニングが正常でない状態に見える場合、構成は隔離されます。 詳細については、「 [検疫状態のアプリケーション プロビジョニング」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)参照してください。

    [Image: プロビジョニング サイクルの状態を示す [構成の概要] ページのスクリーンショット。]
2. [ **プロビジョニング ログ] を** 選択して、どのユーザーが正常にプロビジョニングされたか、失敗したかを判断します。 既定では、ログは構成のサービス プリンシパル ID でフィルター処理されます。 詳細については、[Microsoft Entra ID でのプロビジョニングログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)を参照してください。

    [Image: ログ エントリとその状態を一覧表示する [プロビジョニング ログ] ページのスクリーンショット。]
3. Microsoft Entra ID でログに記録されたすべてのイベントを表示するには、[ **監査ログ** ] を選択します。 詳細については、「 [Microsoft Entra ID の監査ログ」を](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)参照してください。

    [Image: ログ エントリとその状態を一覧表示する [監査ログ] ページのスクリーンショット。]

    ターゲット テナントでの監査ログを見ることもできます。
4. ターゲット テナントで、[ **ユーザー**&gt;**監査ログ** ] を選択して、ユーザー管理のログに記録されたイベントを表示します。 ターゲット テナントでのテナント間同期は、アクターとして "Microsoft.Azure.SyncFabric" アプリケーションとしてログに記録されます。

    [Image: ユーザー管理のログ エントリを一覧表示するターゲット テナントの [監査ログ] ページのスクリーンショット。]

### 手順 14: 脱退の設定を構成する

[Image: ターゲット テナントのアイコン。]**ターゲット テナント**

ユーザーがターゲット テナントでプロビジョニングされている場合でも、ユーザーは自分自身を削除できる可能性があります。 自分自身を削除したユーザーがスコープ内にいる場合、そのユーザーは次のプロビジョニング サイクル中に再びプロビジョニングされます。 ユーザーが組織から自分自身を削除できないようにするには、 **外部ユーザーの休暇設定**を構成する必要があります。

1. ターゲット テナントで、**Entra ID**&gt;**外部ID**&gt;**外部コラボレーション設定**を参照します。
2. [ **外部ユーザーの脱退設定**] で、外部ユーザーが自分で組織から離れることを許可するかどうかを選択します。

この設定は B2B コラボレーションと B2B 直接接続にも適用されるため、 **外部ユーザーの休暇設定** を **[いいえ**] に設定した場合、B2B コラボレーション ユーザーと B2B 直接接続ユーザーは組織を離れることはできません。 詳細については、「 [組織を外部ユーザーとして脱退する」を](https://learn.microsoft.com/ja-jp/entra/external-id/leave-the-organization#more-information-for-administrators)参照してください。

### テナント間同期の一般的なシナリオのトラブルシューティング

#### 現象 - AzureActiveDirectoryCrossTenantSyncPolicyCheckFailure でテスト接続が失敗する

ソース テナントでテナント間同期を構成し、接続をテストすると、次のいずれかのエラー メッセージで失敗します。

*ソース テナントで自動引き換えが設定されていない*

```
You appear to have entered invalid credentials. Please confirm you are using the correct information for an administrative account.
Error code: AzureActiveDirectoryCrossTenantSyncPolicyCheckFailure
Details: The source tenant has not enabled automatic user consent with the target tenant. Please enable the outbound cross-tenant access policy for automatic user consent in the source tenant. aka.ms/TroubleshootingCrossTenantSyncPolicyCheck
```

*ターゲット テナントで自動引き換えが設定されていない*

```
You appear to have entered invalid credentials. Please confirm you are using the correct information for an administrative account.
Error code: AzureActiveDirectoryCrossTenantSyncPolicyCheckFailure
Details: The target tenant has not enabled inbound synchronization with this tenant. Please request the target tenant admin to enable the inbound synchronization on their cross-tenant access policy. Learn more: aka.ms/TroubleshootingCrossTenantSyncPolicyCheck
```

**原因**

このエラーは、ソース テナントまたはターゲット テナントの招待を自動的に引き換えるポリシーが設定されなかったことを示します。

**解決**

「手順 3: ターゲット テナントで招待を自動的に利用する」と「手順 4: ソース テナントの招待を自動的に引き換える」の手順に従います。

::: zone pivot="cross-cloud-synchronization"

#### 現象 - ExternalTenantNotFound でテスト接続が失敗する

ソース テナントでクラウド間同期を構成し、接続をテストすると、次のエラー メッセージで失敗します。

```
You appear to have entered invalid credentials. Please confirm you are using the correct information for an administrative account.
Error code: ExternalTenantNotFound
Details: This tenant was not found by the authentication authority of the current cloud: <targetTenantId>. The authentication authority is https://login.microsoftonline.com/<targetTenantId>.
```

**原因**

このエラーは、[ **Microsoft クラウド間のテナント間同期のセットアップ** ] チェック ボックスがオフになっていることを示します。

**解決**

1. ソース テナントで、接続に失敗した作成した構成を削除します。
2. ターゲット テナントで新しい構成を作成し、「**手順 5: ソース** テナントに構成を作成する」の説明に従って、[Microsoft クラウド間のテナント間同期のセットアップ] チェック ボックスをオンにします。

#### 現象 - AzureActiveDirectoryTokenExpired でテスト接続が失敗する

ソース テナントでクラウド間同期を構成し、接続をテストすると、次のエラー メッセージで失敗します。

```
You appear to have entered invalid credentials. Please confirm you are using the correct information for an administrative account.
Error code: AzureActiveDirectoryTokenExpired
Details: The identity of the calling application could not be established.
```

**原因**

このエラーは、同期のクラウド間設定が有効になっていない場合を示します。

**解決**

ターゲット テナントの **[Microsoft クラウド設定** ] タブで、ソース テナントのクラウド間同期チェック ボックスをオンにします。 手順 1: 両方のテナントでクラウド間設定を有効にする手順に従います。

::: zone-end

#### 現象 - [自動引き換え] チェックボックスが無効になっている

テナント間同期を構成する場合、[ **自動引き換え** ] チェックボックスは無効になります。

[Image: [自動引き換え] チェックボックスが無効であることを示すスクリーンショット。]

**原因**

お使いのテナントに Microsoft Entra ID P1 または P2 ライセンスがありません。

**解決**

信頼設定を構成するには、Microsoft Entra ID P1 または P2 が必要です。

#### 現象 - ターゲット テナントで最近削除されたユーザーが復元されない

ターゲット テナントで同期されているユーザーを論理的に削除した後、次の同期サイクルの間は、そのユーザーは復元されません。 オンデマンド プロビジョニングを使用してユーザーを論理的に削除した後、そのユーザーを復元しようとすると、ユーザーが重複する可能性があります。

**原因**

以前にターゲット テナントで論理的に削除されたユーザーの復元は、サポートされていません。

**解決**

ターゲット テナントでソフト削除されたユーザーは、手動で復元してください。 詳細については、「 [Microsoft Entra ID を使用して最近削除されたユーザーを復元または削除](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-restore)する」を参照してください。

#### 現象 - ユーザーに対して SMS サインインが有効になっているため、ユーザーはスキップされます

ユーザーは同期からスキップされます。 スコープステップには、状態が false の以下のフィルターが含まれています:「Filter external users.alternativeSecurityIds EQUALS 'None'」

**原因**

ユーザーに対して SMS サインインが有効になっている場合、プロビジョニング サービスによってスキップされます。

**解決**

ユーザーの SMS サインインを無効にします。 次のスクリプトは、PowerShell を使用して SMS サインインを無効にする方法を示しています。

```powershell
##### Disable SMS Sign-in options for the users

#### Import module
Install-Module Microsoft.Graph.Users.Actions
Install-Module Microsoft.Graph.Identity.SignIns
Import-Module Microsoft.Graph.Users.Actions

Connect-MgGraph -Scopes "User.Read.All", "Group.ReadWrite.All", "UserAuthenticationMethod.Read.All","UserAuthenticationMethod.ReadWrite","UserAuthenticationMethod.ReadWrite.All"

##### The value for phoneAuthenticationMethodId is 3179e48a-750b-4051-897c-87b9720928f7

$phoneAuthenticationMethodId = "3179e48a-750b-4051-897c-87b9720928f7"

#### Get the User Details

$userId = "objectid_of_the_user_in_Entra_ID"

#### validate the value for SmsSignInState

$smssignin = Get-MgUserAuthenticationPhoneMethod -UserId $userId

    if($smssignin.SmsSignInState -eq "ready"){   
      #### Disable Sms Sign-In for the user is set to ready

      Disable-MgUserAuthenticationPhoneMethodSmsSignIn -UserId $userId -PhoneAuthenticationMethodId $phoneAuthenticationMethodId
      Write-Host "SMS sign-in disabled for the user" -ForegroundColor Green
    }
    else{
    Write-Host "SMS sign-in status not set or found for the user " -ForegroundColor Yellow
    }

##### End the script
```

::: zone pivot="same-cloud-synchronization"

#### 現象 - EntityTypeNotSupported が原因でグループがスキップされました

EntityTypeNotSupported のため、グループは同期からスキップされます。

[Image: EntityTypeNotSupported が原因でスキップされるグループを示すスクリーンショット。]

**原因**

このメッセージは、ソース テナントでグループの同期が有効になっていないことを示している可能性があります。

**解決**

ソース テナントの [ **プロビジョニング** ] ページの [ **マッピング** ] セクションで、[ **Microsoft Entra ID グループのプロビジョニング** ] を選択して **[属性マッピング** ] ページを開きます。 **[有効]** トグルが **[はい**] に設定されていることを確認します。 「詳細については、ステップ 8: (任意) スコープ フィルターでプロビジョニングの範囲を定義する対象者を決定を参照してください。」

::: zone-end

#### 現象 - "AzureActiveDirectoryForbidden" エラーでユーザーがプロビジョニングに失敗する

スコープ内のユーザーがプロビジョニングに失敗します。 プロビジョニング ログの詳細には、次のエラー メッセージが含まれます。

`Guest invitations not allowed for your company. Contact your company administrator for more details.`

**原因**

このエラーは、ターゲット テナントのゲスト招待設定が最も制限の厳しい設定 "管理者を含む組織内のすべてのユーザーがゲスト ユーザーを招待できない (最も制限的)" で構成されていることを示します。

**解決**

ターゲット テナントのゲスト招待設定を、制限の緩い設定に変更します。 詳細については、「 [外部コラボレーション設定の構成」を](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure)参照してください。

#### 現象 - UserPrincipalName が、保留中の受け入れ状態の既存の B2B ユーザーに対して更新されない

ユーザーが手動の B2B 招待を通じて初めて招待されると、招待はソース ユーザーのメール アドレスに送信されます。 その結果、ターゲット テナント内のゲスト ユーザーは、ソース メール値プロパティを使用して UserPrincipalName (UPN) プレフィックスを使用して作成されます。 環境によっては、ソース ユーザー オブジェクトのプロパティである UPN とメールの値が異なる場合があります (たとえば Mail == user.mail@domain.com と UPN == user.upn@otherdomain.com)。 この場合、ターゲット テナント内のゲスト ユーザーは、*UPN を user.mail\_domain.com#EXT#@contoso.onmicrosoft.com* として作成されます。

この問題は、ソース オブジェクトがテナント間同期のスコープに配置され、他のプロパティ以外にも、ターゲット ゲスト ユーザーの UPN プレフィックスが **ソース ユーザーの UPN と一致するように更新されることを** 想定している場合に発生します (上記の例を使用すると *、user.upn\_otherdomain.com#EXT#@contoso.onmicrosoft.com*)。 ただし、増分同期サイクル中にはこのようなことは起こらず、変更は無視されます。

**原因**

この問題は、 **ターゲット テナントに手動で招待された B2B ユーザーが招待を承諾または引き換えなかったため**、その状態が保留中の状態である場合に発生します。 ユーザーがメールを通じて招待されると、メールから設定された一連の属性を使ってオブジェクトが作成されます。そのうちの 1 つは、ソース ユーザーのメール値を指す UPN です。 後でクロステナント同期のスコープにユーザーを追加する場合、システムは alternativeSecurityIdentifier 属性に基づいてターゲット テナント内の B2B ユーザーとソース ユーザーを結合しようとしますが、以前に作成したユーザーには、招待が引き換えられなかったため、alternativeSecurityIdentifier プロパティが設定されていません。 そのため、システムはこれを新しいユーザー オブジェクトとは見なせず、UPN 値を更新しません。 UserPrincipalName は、次のシナリオでは更新されません。

1. 手動で招待されたときのユーザーの UPN とメールが異なる。
2. ユーザーが、テナント間同期を有効にする前に招待された。
3. ユーザーが招待を受け入れなかったため、"承認待ち状態" になっている。
4. ユーザーがテナント間同期のスコープに組み込まれる。

**解決**

この問題を解決するには、影響を受けるユーザーに対してオンデマンド プロビジョニングを実行して UPN を更新します。 プロビジョニングを再起動して、すべての影響を受けるユーザーの UPN を更新することもできます。 これにより初期サイクルがトリガーされ、大規模なテナントでは長い時間がかかる場合があることに注意してください。 保留中の受け入れ状態の手動招待されたユーザーの一覧を取得するには、スクリプトを使用できます。次のサンプルを参照してください。

```powershell
Connect-MgGraph -Scopes "User.Read.All"
$users = Get-MgUser -Filter "userType eq 'Guest' and externalUserState eq 'PendingAcceptance'" 
$users | Select-Object DisplayName, UserPrincipalName | Export-Csv "C:\Temp\GuestUsersPending.csv"
```

その後、 [PowerShell で provisionOnDemand](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-provisionondemand?tabs=powershell#request) をユーザーごとに使用できます。 この API のレート制限は 10 秒あたり 5 要求です。 詳細については、「 [オンデマンド プロビジョニングの既知の制限事項](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand?pivots=cross-tenant-synchronization#known-limitations)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure-graph"} -->
## PowerShell または Microsoft Graph API を使用してテナント間同期を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure-graph
- Service: entra-id / multitenant-organizations
- Article date: 2026-03-25
- Summary: Microsoft Graph PowerShell または Microsoft Graph API を使用してテナント間の同期を構成します。 同期の有効化、自動引き換えの設定、プロビジョニング ジョブの作成、オンデマンド プロビジョニングのテストが含まれます。

### 概要

この記事では、Microsoft Graph PowerShell または Microsoft Graph API を使用してテナント間同期を構成するうえで重要な手順について説明します。 そのように構成した Microsoft Entra ID によって、ターゲット テナントの B2B ユーザーが自動的にプロビジョニングされ、また自動的にプロビジョニング解除されます。 Microsoft Entra 管理センターの詳しい使用手順については「[テナント間同期を構成する](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)」を参照してください。

[Image: ソース テナントとターゲット テナントの間のテナント間同期を示す図。]

### 前提条件

[Image: ソース テナントのアイコン。]**ソース テナント**

- Microsoft Entra ID の P1 または P2 ライセンス。 詳細については、「[License requirements ライセンスの要件](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview#license-requirements)」を参照してください。
- テナント間アクセス設定を構成するための[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)ロール。
- テナント間同期を構成するための[ハイブリッド ID の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロール。
- 構成へのユーザーの割り当ておよび構成の削除のための[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)ロールまたは[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)ロール。
- 必要なアクセス許可に同意するための[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)ロール。

[Image: ターゲット テナントのアイコン。]**ターゲット テナント**

- Microsoft Entra ID の P1 または P2 ライセンス。 詳細については、「[License requirements ライセンスの要件](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview#license-requirements)」を参照してください。
- テナント間アクセス設定を構成するための[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)ロール。
- 必要なアクセス許可に同意するための[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)ロール。

### 手順 1: ターゲット テナントにサインインする

[Image: ターゲット テナントのアイコン。]**ターゲット テナント**

## [PowerShell](#tab/ms-powershell)
1. PowerShell を開始します。
2. 必要に応じて、[Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) をインストールします。
3. ソース テナントとターゲット テナントのテナント ID を取得し、変数を初期化します。

    ```powershell
    $SourceTenantId = "<SourceTenantId>"
    $TargetTenantId = "<TargetTenantId>"
    ```
4. [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) コマンドを使用してターゲット テナントにサインインし、必要な次のアクセス許可に同意します。

    - `Policy.Read.All`
    - `Policy.ReadWrite.CrossTenantAccess`

    ```powershell
    Connect-MgGraph -TenantId $TargetTenantId -Scopes "Policy.Read.All","Policy.ReadWrite.CrossTenantAccess"
    ```

## [Microsoft Graph](#tab/ms-graph)
以下の手順で Microsoft Graph Explorer を使用する方法について説明しますが、他の REST API クライアントを使用することもできます。

1. [Microsoft Graph Explorer ツール](https://aka.ms/ge)を起動します。
2. ターゲット テナントにサインインします。
3. プロファイルを選択し、**[Consent to permissions] (アクセス許可に同意する)** を選択します。

    [Image: [Consent to permissions] (アクセス許可に同意する) リンクがある Microsoft Graph Explorer プロファイルのスクリーンショット。]
4. 次の必要なアクセス許可に同意します。

    - `Policy.Read.All`
    - `Policy.ReadWrite.CrossTenantAccess`
5. ソースとターゲットのテナントのテナント ID を取得します。 この記事で説明する構成例では、次のテナント ID を使用します。

    - ソース テナント ID: {sourceTenantId}
    - ターゲット テナント ID: {targetTenantId}

---

### 手順 2: ターゲット テナントでユーザーの同期を有効にする

[Image: ターゲット テナントのアイコン。]**ターゲット テナント**

## [PowerShell](#tab/ms-powershell)
1. ターゲット テナントで [New-MgPolicyCrossTenantAccessPolicyPartner](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/new-mgpolicycrosstenantaccesspolicypartner) コマンドを使用して、ターゲット テナントとソース テナント間のテナント間アクセス ポリシーに新しいパートナー構成を作成します。 要求でソース テナント ID を使用します。

    エラー `New-MgPolicyCrossTenantAccessPolicyPartner_Create: Another object with the same value for property tenantId already exists` が発生した場合は、既存の構成が既に存在していることが考えられます。 詳細については「症状 - New-MgPolicyCrossTenantAccessPolicyPartner_Create エラー」を参照してください。

    ```powershell
    $Params = @{
        TenantId = $SourceTenantId
    }
    New-MgPolicyCrossTenantAccessPolicyPartner -BodyParameter $Params | Format-List
    ```

    ```Output
    AutomaticUserConsentSettings : Microsoft.Graph.PowerShell.Models.MicrosoftGraphInboundOutboundPolicyConfiguration
    B2BCollaborationInbound      : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCrossTenantAccessPolicyB2BSetting
    B2BCollaborationOutbound     : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCrossTenantAccessPolicyB2BSetting
    B2BDirectConnectInbound      : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCrossTenantAccessPolicyB2BSetting
    B2BDirectConnectOutbound     : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCrossTenantAccessPolicyB2BSetting
    IdentitySynchronization      : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCrossTenantIdentitySyncPolicyPartner
    InboundTrust                 : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCrossTenantAccessPolicyInboundTrust
    IsServiceProvider            :
    TenantId                     : <SourceTenantId>
    TenantRestrictions           : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCrossTenantAccessPolicyTenantRestrictions
    AdditionalProperties         : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#policies/crossTenantAccessPolicy/partners/$entity],
                                   [crossCloudMeetingConfiguration,
                                   System.Collections.Generic.Dictionary`2[System.String,System.Object]], [protectedContentSharing,
                                   System.Collections.Generic.Dictionary`2[System.String,System.Object]]}
    ```
2. [Invoke-MgGraphRequest](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-invoke-mggraphrequest) コマンドを使用して、ターゲット テナントでユーザー同期を有効にします。

    `Request_MultipleObjectsWithSameKeyValue` エラーが発生した場合は、既存のポリシーが既にある可能性があります。 詳細については、「現象 - Request_MultipleObjectsWithSameKeyValue エラー」を参照してください。

    ```powershell
    $Params = @{
        userSyncInbound = @{
            isSyncAllowed = $true
        }
    }
    Invoke-MgGraphRequest -Method PUT -Uri "https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/partners/$SourceTenantId/identitySynchronization" -Body $Params
    ```
3. [Get-MgPolicyCrossTenantAccessPolicyPartnerIdentitySynchronization](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mgpolicycrosstenantaccesspolicypartneridentitysynchronization) コマンドを使用して、`IsSyncAllowed` が True に設定されていることを確認します。

    ```powershell
    (Get-MgPolicyCrossTenantAccessPolicyPartnerIdentitySynchronization -CrossTenantAccessPolicyConfigurationPartnerTenantId $SourceTenantId).UserSyncInbound
    ```

    ```Output
    IsSyncAllowed
    -------------
    True
    ```

## [Microsoft Graph](#tab/ms-graph)
1. ターゲット テナントで、[crossTenantAccessPolicyConfigurationPartner を作成する](https://learn.microsoft.com/ja-jp/graph/api/crosstenantaccesspolicy-post-partners) API を使用して、ターゲット テナントとソース テナントの間のテナント間アクセス ポリシーに新しいパートナー構成を作成します。 要求でソース テナント ID を使用します。

    `Request_MultipleObjectsWithSameKeyValue` エラーが発生した場合は、既に既存の構成がある可能性があります。 詳細については、「現象 - Request_MultipleObjectsWithSameKeyValue エラー」を参照してください。

    **依頼**

    ```http
    POST https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/partners
    Content-Type: application/json
    
    {
      "tenantId": "{sourceTenantId}"
    }
    ```

    **応答**

    ```http
    HTTP/1.1 201 Created
    Content-Type: application/json
    
    {
      "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#policies/crossTenantAccessPolicy/partners/$entity",
      "tenantId": "{sourceTenantId}",
      "isServiceProvider": null,
      "inboundTrust": null,
      "b2bCollaborationOutbound": null,
      "b2bCollaborationInbound": null,
      "b2bDirectConnectOutbound": null,
      "b2bDirectConnectInbound": null,
      "tenantRestrictions": null,
      "crossCloudMeetingConfiguration":
      {
        "inboundAllowed": null,
        "outboundAllowed": null
      },
      "automaticUserConsentSettings":
      {
        "inboundAllowed": null,
        "outboundAllowed": null
      }
    }
    ```
2. [Create identitySynchronization](https://learn.microsoft.com/ja-jp/graph/api/crosstenantaccesspolicyconfigurationpartner-put-identitysynchronization) API を使用して、ターゲット テナントでユーザー同期を有効にします。

    `Request_MultipleObjectsWithSameKeyValue` エラーが発生した場合は、既存のポリシーが既にある可能性があります。 詳細については、「現象 - Request_MultipleObjectsWithSameKeyValue エラー」を参照してください。

    **依頼**

    ```http
    PUT https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/partners/{sourceTenantId}/identitySynchronization
    Content-type: application/json
    
    {
       "displayName": "Fabrikam",
       "userSyncInbound": 
        {
          "isSyncAllowed": true
        }
    }
    ```

    **応答**

    ```http
    HTTP/1.1 204 No Content
    ```

---

### 手順 3: ターゲット テナントで招待を自動的に引き換える

[Image: ターゲット テナントのアイコン。]**ターゲット テナント**

## [PowerShell](#tab/ms-powershell)
1. ターゲット テナントで [Update-MgPolicyCrossTenantAccessPolicyPartner](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/update-mgpolicycrosstenantaccesspolicypartner) コマンドを使用して、招待を自動的に受け、受信アクセスへの同意プロンプトが表示されないようにします。

    ```powershell
    $AutomaticUserConsentSettings = @{
        "InboundAllowed"="True"
    }
    Update-MgPolicyCrossTenantAccessPolicyPartner -CrossTenantAccessPolicyConfigurationPartnerTenantId $SourceTenantId -AutomaticUserConsentSettings $AutomaticUserConsentSettings
    ```

## [Microsoft Graph](#tab/ms-graph)
1. ターゲット テナントで、[crossTenantAccessPolicyConfigurationPartner を更新する](https://learn.microsoft.com/ja-jp/graph/api/crosstenantaccesspolicyconfigurationpartner-update) API を使用して、招待を自動的に引き換え、受信アクセスの同意プロンプトを抑制します。

    **依頼**

    ```http
    PATCH https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/partners/{sourceTenantId}
    Content-Type: application/json
    
    {
        "inboundTrust": null,
        "automaticUserConsentSettings":
        {
            "inboundAllowed": true
        }
    }
    ```

    **応答**

    ```http
    HTTP/1.1 204 No Content
    ```

---

### 手順 4: ソース テナントにサインインする

[Image: ソース テナントのアイコン。]**ソース テナント**

## [PowerShell](#tab/ms-powershell)
1. PowerShell のインスタンスを開始します。
2. ソース テナントとターゲット テナントのテナント ID を取得し、変数を初期化します。

    ```powershell
    $SourceTenantId = "<SourceTenantId>"
    $TargetTenantId = "<TargetTenantId>"
    ```
3. [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) コマンドを使用してソース テナントにサインインし、必要な次のアクセス許可に同意します。

    - `Policy.Read.All`
    - `Policy.ReadWrite.CrossTenantAccess`
    - `Application.ReadWrite.All`
    - `Directory.ReadWrite.All`
    - `AuditLog.Read.All`

    ```powershell
    Connect-MgGraph -TenantId $SourceTenantId -Scopes "Policy.Read.All","Policy.ReadWrite.CrossTenantAccess","Application.ReadWrite.All","Directory.ReadWrite.All","AuditLog.Read.All"
    ```

## [Microsoft Graph](#tab/ms-graph)
1. [Microsoft Graph Explorer ツール](https://aka.ms/ge)のインスタンスを開始します。
2. ソース テナントにサインインします。
3. 次の必要なアクセス許可に同意します。

    - `Policy.Read.All`
    - `Policy.ReadWrite.CrossTenantAccess`
    - `Application.ReadWrite.All`
    - `Directory.ReadWrite.All`
    - `AuditLog.Read.All`

    [ **管理者の承認が必要** ] ページが表示された場合は、少なくとも特権ロール管理者ロールが割り当てられているユーザーでサインインして同意する必要があります。

---

### 手順 5: ソース テナントで招待を自動的に引き換える

[Image: ソース テナントのアイコン。]**ソース テナント**

## [PowerShell](#tab/ms-powershell)
1. ソース テナントで [New-MgPolicyCrossTenantAccessPolicyPartner](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/new-mgpolicycrosstenantaccesspolicypartner) コマンドを使用して、ソース テナントとターゲット テナント間のテナント間アクセス ポリシーに新しいパートナー構成を作成します。 要求でターゲット テナント ID を使用します。

    エラー `New-MgPolicyCrossTenantAccessPolicyPartner_Create: Another object with the same value for property tenantId already exists` が発生した場合は、既存の構成が既に存在していることが考えられます。 詳細については「症状 - New-MgPolicyCrossTenantAccessPolicyPartner_Create エラー」を参照してください。

    ```powershell
    $Params = @{
        TenantId = $TargetTenantId
    }
    New-MgPolicyCrossTenantAccessPolicyPartner -BodyParameter $Params | Format-List
    ```

    ```Output
    AutomaticUserConsentSettings : Microsoft.Graph.PowerShell.Models.MicrosoftGraphInboundOutboundPolicyConfiguration
    B2BCollaborationInbound      : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCrossTenantAccessPolicyB2BSetting
    B2BCollaborationOutbound     : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCrossTenantAccessPolicyB2BSetting
    B2BDirectConnectInbound      : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCrossTenantAccessPolicyB2BSetting
    B2BDirectConnectOutbound     : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCrossTenantAccessPolicyB2BSetting
    IdentitySynchronization      : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCrossTenantIdentitySyncPolicyPartner
    InboundTrust                 : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCrossTenantAccessPolicyInboundTrust
    IsServiceProvider            :
    TenantId                     : <TargetTenantId>
    TenantRestrictions           : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCrossTenantAccessPolicyTenantRestrictions
    AdditionalProperties         : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#policies/crossTenantAccessPolicy/partners/$entity],
                                   [crossCloudMeetingConfiguration,
                                   System.Collections.Generic.Dictionary`2[System.String,System.Object]], [protectedContentSharing,
                                   System.Collections.Generic.Dictionary`2[System.String,System.Object]]}
    
    ```
2. [Update-MgPolicyCrossTenantAccessPolicyPartner](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/update-mgpolicycrosstenantaccesspolicypartner) コマンドを使用して、招待を自動的に受け、送信アクセスの同意プロンプトが表示されないようにします。

    ```powershell
    $AutomaticUserConsentSettings = @{
        "OutboundAllowed"="True"
    }
    Update-MgPolicyCrossTenantAccessPolicyPartner -CrossTenantAccessPolicyConfigurationPartnerTenantId $TargetTenantId -AutomaticUserConsentSettings $AutomaticUserConsentSettings
    ```

## [Microsoft Graph](#tab/ms-graph)
1. ソース テナントで、[crossTenantAccessPolicyConfigurationPartner を作成する](https://learn.microsoft.com/ja-jp/graph/api/crosstenantaccesspolicy-post-partners) API を使用して、ソース テナントとターゲット テナントの間のテナント間アクセス ポリシーに新しいパートナー構成を作成します。 要求でターゲット テナント ID を使用します。

    `Request_MultipleObjectsWithSameKeyValue` エラーが発生した場合は、既に既存の構成がある可能性があります。 詳細については、「現象 - Request_MultipleObjectsWithSameKeyValue エラー」を参照してください。

    **依頼**

    ```http
    POST https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/partners
    Content-Type: application/json
    
    {
      "tenantId": "{targetTenantId}"
    }
    ```

    **応答**

    ```http
    HTTP/1.1 201 Created
    Content-Type: application/json
    
    {
      "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#policies/crossTenantAccessPolicy/partners/$entity",
      "tenantId": "{targetTenantId}",
      "isServiceProvider": null,
      "inboundTrust": null,
      "b2bCollaborationOutbound": null,
      "b2bCollaborationInbound": null,
      "b2bDirectConnectOutbound": null,
      "b2bDirectConnectInbound": null,
      "tenantRestrictions": null,
      "crossCloudMeetingConfiguration":
      {
        "inboundAllowed": null,
        "outboundAllowed": null
      },
      "automaticUserConsentSettings":
      {
        "inboundAllowed": null,
        "outboundAllowed": null
      }
    }
    ```
2. [Update crossTenantAccessPolicyConfigurationPartner](https://learn.microsoft.com/ja-jp/graph/api/crosstenantaccesspolicyconfigurationpartner-update) API を使用して、招待を自動的に引き換え、送信アクセスの同意プロンプトを抑制します。

    **依頼**

    ```http
    PATCH https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/partners/{targetTenantId}
    Content-Type: application/json
    
    {
        "automaticUserConsentSettings":
        {
            "outboundAllowed": true
        }
    }
    ```

    **応答**

    ```http
    HTTP/1.1 204 No Content
    ```

---

### 手順 6: ソース テナントで構成アプリケーションを作成する

[Image: ソース テナントのアイコン。]**ソース テナント**

## [PowerShell](#tab/ms-powershell)
1. ソース テナントで [Invoke-MgInstantiateApplicationTemplate](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/invoke-mginstantiateapplicationtemplate) コマンドを使用して、Microsoft Entra アプリケーション ギャラリーからテナントに構成アプリケーションのインスタンスを追加します。

    ```powershell
    Invoke-MgInstantiateApplicationTemplate -ApplicationTemplateId "518e5f48-1fc8-4c48-9387-9fdf28b0dfe7" -DisplayName "Fabrikam"
    ```
2. [Get-MgServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal) コマンドを使用して、サービス プリンシパル ID とアプリ ロール ID を取得します。

    ```powershell
    Get-MgServicePrincipal -Filter "DisplayName eq 'Fabrikam'" | Format-List
    ```

    ```Output
    AccountEnabled                      : True
    AddIns                              : {}
    AlternativeNames                    : {}
    AppDescription                      :
    AppDisplayName                      : Fabrikam
    AppId                               : <AppId>
    AppManagementPolicies               :
    AppOwnerOrganizationId              : <AppOwnerOrganizationId>
    AppRoleAssignedTo                   :
    AppRoleAssignmentRequired           : True
    AppRoleAssignments                  :
    AppRoles                            : {<AppRoleId>}
    ApplicationTemplateId               : 518e5f48-1fc8-4c48-9387-9fdf28b0dfe7
    ClaimsMappingPolicies               :
    CreatedObjects                      :
    CustomSecurityAttributes            : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCustomSecurityAttributeValue
    DelegatedPermissionClassifications  :
    DeletedDateTime                     :
    Description                         :
    DisabledByMicrosoftStatus           :
    DisplayName                         : Fabrikam
    Endpoints                           :
    ErrorUrl                            :
    FederatedIdentityCredentials        :
    HomeRealmDiscoveryPolicies          :
    Homepage                            : https://account.activedirectory.windowsazure.com:444/applications/default.aspx?metadata=aad2aadsync|ISV9.1|primary|z
    Id                                  : <ServicePrincipalId>
    Info                                : Microsoft.Graph.PowerShell.Models.MicrosoftGraphInformationalUrl
    KeyCredentials                      : {}
    LicenseDetails                      :
    
    ...
    ```
3. サービス プリンシパル ID の変数を初期化します。

    アプリケーション ID ではなく、必ずサービス プリンシパル ID を使用します。

    ```powershell
    $ServicePrincipalId = "<ServicePrincipalId>"
    ```
4. アプリ ロール ID の変数を初期化します。

    ```powershell
    $AppRoleId= "<AppRoleId>"
    ```

## [Microsoft Graph](#tab/ms-graph)
1. ソース テナントで [applicationTemplate: instantiate](https://learn.microsoft.com/ja-jp/graph/api/applicationtemplate-instantiate) API を使用して、Microsoft Entra アプリケーション ギャラリーからテナントに構成アプリケーションのインスタンスを追加します。

    **依頼**

    ```http
    POST https://graph.microsoft.com/v1.0/applicationTemplates/518e5f48-1fc8-4c48-9387-9fdf28b0dfe7/instantiate
    Content-type: application/json
    
    {
      "displayName": "Fabrikam"
    }
    ```

    **応答**

    ```http
    HTTP/1.1 201 Created
    Content-type: application/json
    
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#microsoft.graph.applicationServicePrincipal",
        "application": {
            "id": "{id}",
            "appId": "{appId}",
            "applicationTemplateId": "518e5f48-1fc8-4c48-9387-9fdf28b0dfe7",
            "createdDateTime": "2023-07-31T23:26:24Z",
            "deletedDateTime": null,
            "displayName": "Fabrikam",
            "description": null,
            "groupMembershipClaims": null,
            "identifierUris": [],
            "isFallbackPublicClient": false,
            "signInAudience": "AzureADMyOrg",
            "tags": [],
            "tokenEncryptionKeyId": null,
            "defaultRedirectUri": null,
            "optionalClaims": null,
            "addIns": [],
            "api": {
                "acceptMappedClaims": null,
                "knownClientApplications": [],
                "requestedAccessTokenVersion": null,
                "oauth2PermissionScopes": [
                    {
                        "adminConsentDescription": "Allow the application to access Fabrikam on behalf of the signed-in user.",
                        "adminConsentDisplayName": "Access Fabrikam",
                        "id": "{id}",
                        "isEnabled": true,
                        "type": "User",
                        "userConsentDescription": "Allow the application to access Fabrikam on your behalf.",
                        "userConsentDisplayName": "Access Fabrikam",
                        "value": "user_impersonation"
                    }
                ],
                "preAuthorizedApplications": []
            },
            "appRoles": [
                {
                    "allowedMemberTypes": [
                        "User"
                    ],
                    "displayName": "msiam_access",
                    "id": "{appRoleId}",
                    "isEnabled": true,
                    "description": "msiam_access",
                    "value": null,
                    "origin": "Application"
                }
            ],
            "info": {
                "logoUrl": null,
                "marketingUrl": null,
                "privacyStatementUrl": null,
                "supportUrl": null,
                "termsOfServiceUrl": null
            },
            "keyCredentials": [],
            "parentalControlSettings": {
                "countriesBlockedForMinors": [],
                "legalAgeGroupRule": "Allow"
            },
            "passwordCredentials": [],
            "publicClient": {
                "redirectUris": []
            },
            "requiredResourceAccess": [],
            "verifiedPublisher": {
                "displayName": null,
                "verifiedPublisherId": null,
                "addedDateTime": null
            },
            "web": {
                "homePageUrl": "https://account.activedirectory.windowsazure.com:444/applications/default.aspx?metadata=aad2aadsync|ISV9.1|primary|z",
                "redirectUris": [],
                "logoutUrl": null
            }
        },
        "servicePrincipal": {
            "id": "{servicePrincipalId}",
            "deletedDateTime": null,
            "accountEnabled": true,
            "appId": "{appId}",
            "applicationTemplateId": "518e5f48-1fc8-4c48-9387-9fdf28b0dfe7",
            "appDisplayName": "Fabrikam",
            "alternativeNames": [],
            "appOwnerOrganizationId": "{appOwnerOrganizationId}",
            "displayName": "Fabrikam",
            "appRoleAssignmentRequired": true,
            "loginUrl": null,
            "logoutUrl": null,
            "homepage": "https://account.activedirectory.windowsazure.com:444/applications/default.aspx?metadata=aad2aadsync|ISV9.1|primary|z",
            "notificationEmailAddresses": [],
            "preferredSingleSignOnMode": null,
            "preferredTokenSigningKeyThumbprint": null,
            "replyUrls": [],
            "servicePrincipalNames": [
                "{appId}"
            ],
            "servicePrincipalType": "Application",
            "tags": [
                "WindowsAzureActiveDirectoryIntegratedApp"
            ],
            "tokenEncryptionKeyId": null,
            "samlSingleSignOnSettings": null,
            "addIns": [],
            "appRoles": [
                {
                    "allowedMemberTypes": [
                        "User"
                    ],
                    "displayName": "msiam_access",
                    "id": "{appRoleId}",
                    "isEnabled": true,
                    "description": "msiam_access",
                    "value": null,
                    "origin": "Application"
                }
            ],
            "info": {
                "logoUrl": null,
                "marketingUrl": null,
                "privacyStatementUrl": null,
                "supportUrl": null,
                "termsOfServiceUrl": null
            },
            "keyCredentials": [],
            "oauth2PermissionScopes": [
                {
                    "adminConsentDescription": "Allow the application to access Fabrikam on behalf of the signed-in user.",
                    "adminConsentDisplayName": "Access Fabrikam",
                    "id": "{id}",
                    "isEnabled": true,
                    "type": "User",
                    "userConsentDescription": "Allow the application to access Fabrikam on your behalf.",
                    "userConsentDisplayName": "Access Fabrikam",
                    "value": "user_impersonation"
                }
            ],
            "passwordCredentials": [],
            "verifiedPublisher": {
                "displayName": null,
                "verifiedPublisherId": null,
                "addedDateTime": null
            }
        }
    }
    ```
2. servicePrincipalId を保存します。

    アプリケーション ID ではなく、必ずサービス プリンシパル ID を使用します。
3. appRoleId を保存します。

---

### 手順 7: ターゲット テナントへの接続をテストする

[Image: ソース テナントのアイコン。]**ソース テナント**

## [PowerShell](#tab/ms-powershell)
1. ソース テナントで [Invoke-MgGraphRequest](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-invoke-mggraphrequest) コマンドを使用して、ターゲット テナントへの接続をテストし、資格情報を検証します。

    ```powershell
    $Params = @{
        "useSavedCredentials" = $false
        "templateId" = "Azure2Azure"
        "credentials" = @(
            @{
                "key" = "CompanyId"
                "value" = $TargetTenantId
            }
            @{
                "key" = "AuthenticationType"
                "value" = "SyncPolicy"
            }
        )
    }
    Invoke-MgGraphRequest -Method POST -Uri "https://graph.microsoft.com/v1.0/servicePrincipals/$ServicePrincipalId/synchronization/jobs/validateCredentials" -Body $Params
    ```

## [Microsoft Graph](#tab/ms-graph)
1. ソース テナントで、[synchronizationJob: validateCredentials](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-validatecredentials) API を使用して、ターゲット テナントへの接続をテストし、資格情報を検証します。

    **依頼**

    ```http
    POST https://graph.microsoft.com/v1.0/servicePrincipals/{servicePrincipalId}/synchronization/jobs/validateCredentials
    Content-Type: application/json
    
    {
        "useSavedCredentials": false,
        "templateId": "Azure2Azure",
        "credentials": [
            {
                "key": "CompanyId",
                "value": "{targetTenantId}"
            },
            {
                "key": "AuthenticationType",
                "value": "SyncPolicy"
            }
        ]
    }
    ```

    **応答**

    ```http
    HTTP/1.1 204 No Content
    ```

---

### 手順 8: ソース テナントでプロビジョニング ジョブを作成する

[Image: ソース テナントのアイコン。]**ソース テナント**

ソース テナントで、プロビジョニングを有効にするには、プロビジョニング ジョブを作成します。

## [PowerShell](#tab/ms-powershell)
1. 使用する同期テンプレート (`Azure2Azure` など) を決定します。

    テンプレートには、事前に構成された同期設定が含まれています。
2. ソース テナントで [New-MgServicePrincipalSynchronizationJob](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/new-mgserviceprincipalsynchronizationjob) コマンドを使用して、テンプレートに基づくプロビジョニング ジョブを作成します。

    ```powershell
    New-MgServicePrincipalSynchronizationJob -ServicePrincipalId $ServicePrincipalId -TemplateId "Azure2Azure" | Format-List
    ```

    ```Output
    Id                         : <JobId>
    Schedule                   : Microsoft.Graph.PowerShell.Models.MicrosoftGraphSynchronizationSchedule
    Schema                     : Microsoft.Graph.PowerShell.Models.MicrosoftGraphSynchronizationSchema
    Status                     : Microsoft.Graph.PowerShell.Models.MicrosoftGraphSynchronizationStatus
    SynchronizationJobSettings : {AzureIngestionAttributeOptimization, LookaheadQueryEnabled}
    TemplateId                 : Azure2Azure
    AdditionalProperties       : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#servicePrincipals('<ServicePrincipalId>')/synchro
                                 nization/jobs/$entity]}
    ```
3. ジョブ ID の変数を初期化します。

    ```powershell
    $JobId = "<JobId>"
    ```

## [Microsoft Graph](#tab/ms-graph)
1. 使用する[同期テンプレート](https://learn.microsoft.com/ja-jp/graph/api/resources/synchronization-synchronizationtemplate) (`Azure2Azure` など) を決定します。

    テンプレートには、事前に構成された同期設定が含まれています。
2. ソース テナントで、[Create synchronizationJob](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronization-post-jobs) API を使用して、テンプレートに基づいてプロビジョニング ジョブを作成します。

    **依頼**

    ```http
    POST https://graph.microsoft.com/v1.0/servicePrincipals/{servicePrincipalId}/synchronization/jobs
    Content-type: application/json
    
    {
        "templateId": "Azure2Azure"
    }
    ```

    **応答**

    ```http
    HTTP/1.1 201 Created
    Content-type: application/json
    
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#servicePrincipals('{servicePrincipalId}')/synchronization/jobs/$entity",
        "id": "{jobId}",
        "templateId": "Azure2Azure",
        "schedule": {
            "expiration": null,
            "interval": "PT40M",
            "state": "Disabled"
        },
        "status": {
            "countSuccessiveCompleteFailures": 0,
            "escrowsPruned": false,
            "code": "Paused",
            "lastExecution": null,
            "lastSuccessfulExecution": null,
            "lastSuccessfulExecutionWithExports": null,
            "quarantine": null,
            "steadyStateFirstAchievedTime": "0001-01-01T00:00:00Z",
            "steadyStateLastAchievedTime": "0001-01-01T00:00:00Z",
            "troubleshootingUrl": null,
            "progress": [],
            "synchronizedEntryCountByType": []
        },
        "synchronizationJobSettings": [
            {
                "name": "AzureIngestionAttributeOptimization",
                "value": "False"
            },
            {
                "name": "LookaheadQueryEnabled",
                "value": "False"
            }
        ]
    }
    ```
3. jobId を保存します。

---

### 手順 9: 資格情報を保存する

[Image: ソース テナントのアイコン。]**ソース テナント**

## [PowerShell](#tab/ms-powershell)
1. ソース テナントで [Invoke-MgGraphRequest](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-invoke-mggraphrequest) コマンドを使用して、自身の資格情報を保存します。

    ```powershell
    $Params = @{
        "value" = @(
            @{
                "key" = "AuthenticationType"
                "value" = "SyncPolicy"
            }
            @{
                "key" = "CompanyId"
                "value" = $TargetTenantId
            }
        )
    }
    Invoke-MgGraphRequest -Method PUT -Uri "https://graph.microsoft.com/v1.0/servicePrincipals/$ServicePrincipalId/synchronization/secrets" -Body $Params
    ```

## [Microsoft Graph](#tab/ms-graph)
1. ソース テナントで [同期シークレットの追加](https://learn.microsoft.com/ja-jp/graph/api/synchronization-serviceprincipal-put-synchronization) API を使用して、自身の資格情報を保存します。

    **依頼**

    ```http
    PUT https://graph.microsoft.com/v1.0/servicePrincipals/{servicePrincipalId}/synchronization/secrets 
    Content-Type: application/json
    
    {
        "value": [
            {
                "key": "AuthenticationType",
                "value": "SyncPolicy"
            },
            {
                "key": "CompanyId",
                "value": "{targetTenantId}"
            },
            {
                "key": "SyncNotificationSettings",
                "value": "{\"Enabled\":false,\"DeleteThresholdEnabled\":false,\"HumanResourcesLookaheadQueryEnabled\":false}"
            },
            {
                "key": "SyncAll",
                "value": "false"
            }
        ]
    }
    ```

    **応答**

    ```http
    HTTP/1.1 204 No Content
    ```

---

### 手順 10: 構成にユーザーを割り当てる

[Image: ソース テナントのアイコン。]**ソース テナント**

テナント間同期が機能するには、少なくとも 1 人の内部ユーザーを構成に割り当てる必要があります。

## [PowerShell](#tab/ms-powershell)
1. ソース テナントで [New-MgServicePrincipalAppRoleAssignedTo](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/new-mgserviceprincipalapproleassignedto) コマンドを使用して、この構成に内部ユーザーを割り当てます。

    ```powershell
    $Params = @{
        PrincipalId = "<PrincipalId>"
        ResourceId = $ServicePrincipalId
        AppRoleId = $AppRoleId
    }
    New-MgServicePrincipalAppRoleAssignedTo -ServicePrincipalId $ServicePrincipalId -BodyParameter $Params | Format-List
    ```

    ```Output
    AppRoleId            : <AppRoleId>
    CreatedDateTime      : 7/31/2023 10:27:12 PM
    DeletedDateTime      :
    Id                   : <Id>
    PrincipalDisplayName : User1
    PrincipalId          : <PrincipalId>
    PrincipalType        : User
    ResourceDisplayName  : Fabrikam
    ResourceId           : <ServicePrincipalId>
    AdditionalProperties : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#appRoleAssignments/$entity]}
    ```

## [Microsoft Graph](#tab/ms-graph)
1. ソース テナントで、[Grant an appRoleAssignment for a service principal](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-post-approleassignedto) API を使用して構成に内部ユーザーを割り当てます。

    **依頼**

    ```http
    POST https://graph.microsoft.com/v1.0/servicePrincipals/{servicePrincipalId}/appRoleAssignedTo
    Content-type: application/json
    
    {
        "appRoleId": "{appRoleId}",
        "resourceId": "{servicePrincipalId}",
        "principalId": "{principalId}"
    }
    ```

    **応答**

    ```http
    HTTP/1.1 201 Created
    Content-Type: application/json
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#servicePrincipals('{servicePrincipalId}')/appRoleAssignedTo/$entity",
        "id": "{keyId}",
        "deletedDateTime": null,
        "appRoleId": "{appRoleId}",
        "createdDateTime": "2023-07-31T22:23:48.6541804Z",
        "principalDisplayName": "User1",
        "principalId": "{principalId}",
        "principalType": "User",
        "resourceDisplayName": "Fabrikam",
        "resourceId": "{servicePrincipalId}"
    }
    ```

---

### 手順 11: オンデマンドのプロビジョニングをテストする

[Image: ソース テナントのアイコン。]**ソース テナント**

構成が済んだので、いずれかのユーザーでオンデマンド プロビジョニングをテストできます。

## [PowerShell](#tab/ms-powershell)
1. ソース テナントで [Get-MgServicePrincipalSynchronizationJobSchema](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipalsynchronizationjobschema) コマンドを使用してスキーマ ルール ID を取得します。

    ```powershell
    $SynchronizationSchema = Get-MgServicePrincipalSynchronizationJobSchema -ServicePrincipalId $ServicePrincipalId -SynchronizationJobId $JobId
    $SynchronizationSchema.SynchronizationRules | Format-List
    ```

    ```Output
    ContainerFilter      : Microsoft.Graph.PowerShell.Models.MicrosoftGraphContainerFilter
    Editable             : True
    GroupFilter          : Microsoft.Graph.PowerShell.Models.MicrosoftGraphGroupFilter
    Id                   : <RuleId>
    Metadata             : {defaultSourceObjectMappings, supportsProvisionOnDemand}
    Name                 : USER_INBOUND_USER
    ObjectMappings       : {Provision Azure Active Directory Users, , , ...}
    Priority             : 1
    SourceDirectoryName  : Azure Active Directory
    TargetDirectoryName  : Azure Active Directory (target tenant)
    AdditionalProperties : {}
    ```
2. ルール ID の変数を初期化します。

    ```powershell
    $RuleId = "<RuleId>"
    ```
3. [New-MgServicePrincipalSynchronizationJobOnDemand](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/new-mgserviceprincipalsynchronizationjobondemand) コマンドを使用して、オンデマンドでテスト ユーザーをプロビジョニングします。

    ```powershell
    $Params = @{
        Parameters = @(
            @{
                Subjects = @(
                    @{
                        ObjectId = "<UserObjectId>"
                        ObjectTypeName = "User"
                    }
                )
                RuleId = $RuleId
            }
        )
    }
    New-MgServicePrincipalSynchronizationJobOnDemand -ServicePrincipalId $ServicePrincipalId -SynchronizationJobId $JobId -BodyParameter $Params | Format-List
    ```

    ```Output
    Key                  : Microsoft.Identity.Health.CPP.Common.DataContracts.SyncFabric.StatusInfo
    Value                : [{"provisioningSteps":[{"name":"EntryImport","type":"Import","status":"Success","description":"Retrieved User
                           'user1@fabrikam.com' from Azure Active Directory","timestamp":"2023-07-31T22:31:15.9116590Z","details":{"objectId":
                           "<UserObjectId>","accountEnabled":"True","displayName":"User1","mailNickname":"user1","userPrincipalName":"use
                           ...
    AdditionalProperties : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#microsoft.graph.stringKeyStringValuePair]}
    ```

## [Microsoft Graph](#tab/ms-graph)
1. ソース テナントで [Get synchronizationSchema](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationschema-get) API を使用してスキーマ ルール ID を取得します。

    **依頼**

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals/{servicePrincipalId}/synchronization/jobs/{jobId}/schema
    ```

    **応答**

    ```http
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#servicePrincipals('{servicePrincipalId}')/synchronization/jobs('{jobId}')/schema/$entity",
        "id": "{jobId}",
        "version": "v1.2",
        "synchronizationRules": [
            {
                "containerFilter": null,
                "editable": true,
                "groupFilter": null,
                "id": "{ruleId}",
                "name": "USER_INBOUND_USER",
                "priority": 1,
                "sourceDirectoryName": "Azure Active Directory",
                "targetDirectoryName": "Azure Active Directory (target tenant)",
                "metadata": [
    
                ...
    ```
2. ソース テナントで、[synchronizationJob: provisionOnDemand](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-provisionondemand) API を使用して、オンデマンドでテスト ユーザーをプロビジョニングします。

    **依頼**

    ```http
    POST https://graph.microsoft.com/v1.0/servicePrincipals/{servicePrincipalId}/synchronization/jobs/{jobId}/provisionOnDemand
    Content-Type: application/json
    
    {
        "parameters": [
            {
                "ruleId": "{ruleId}",
                "subjects": [
                    {
                        "objectId": "{userObjectId}",
                        "objectTypeName": "User"
                    }
                ]
            }
        ]
    }
    ```

    **応答**

    ```http
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#microsoft.graph.stringKeyStringValuePair",
        "key": "Microsoft.Identity.Health.CPP.Common.DataContracts.SyncFabric.StatusInfo",
        "value": "[{\"provisioningSteps\":[{\"name\":\"EntryImport\",\"type\":\"Import\",\"status\":\"Success\",\"description\":\"Retrieved User 'user1@fabrikam.com' from Azure Active Directory\",\"timestamp\":\"2023-07-31T00:00:16.7866324Z\",\"details\":{\"objectId\":\"{userObjectId}\",\"accountEnabled\":\"True\",\"displayName\":\"User1\",\"mailNickname\":\"user1\",\"userPrincipalName\":\"user1@fabrikam.com\",}
    
        ...
    ```

---

### 手順 12: プロビジョニング ジョブを開始する

[Image: ソース テナントのアイコン。]**ソース テナント**

## [PowerShell](#tab/ms-powershell)
1. これでプロビジョニング ジョブの構成が完了したので、ソース テナントで [Start-MgServicePrincipalSynchronizationJob](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/start-mgserviceprincipalsynchronizationjob) コマンドを使用して、このプロビジョニング ジョブを開始します。

    ```powershell
    Start-MgServicePrincipalSynchronizationJob -ServicePrincipalId $ServicePrincipalId -SynchronizationJobId $JobId
    ```

## [Microsoft Graph](#tab/ms-graph)
1. これで、プロビジョニング ジョブが構成されたので、ソース テナントで、[同期ジョブの開始](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-start) API を使用してプロビジョニング ジョブを開始します。

    **依頼**

    ```http
    POST https://graph.microsoft.com/v1.0/servicePrincipals/{servicePrincipalId}/synchronization/jobs/{jobId}/start
    ```

    **応答**

    ```http
    HTTP/1.1 204 No Content
    ```

---

### 手順 13: プロビジョニングを監視する

[Image: ソース テナントのアイコン。]**ソース テナント**

## [PowerShell](#tab/ms-powershell)
1. これでプロビジョニング ジョブが実行中になったので、ソース テナントで [Get-MgServicePrincipalSynchronizationJob](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipalsynchronizationjob) コマンドを使用して、現在のプロビジョニング サイクルの進行状況と、現在までの統計情報 (ターゲット システムで作成されたユーザーとグループの数など) を監視します。

    ```powershell
    Get-MgServicePrincipalSynchronizationJob -ServicePrincipalId $ServicePrincipalId -SynchronizationJobId $JobId | Format-List
    ```

    ```Output
    Id                         : <JobId>
    Schedule                   : Microsoft.Graph.PowerShell.Models.MicrosoftGraphSynchronizationSchedule
    Schema                     : Microsoft.Graph.PowerShell.Models.MicrosoftGraphSynchronizationSchema
    Status                     : Microsoft.Graph.PowerShell.Models.MicrosoftGraphSynchronizationStatus
    SynchronizationJobSettings : {AzureIngestionAttributeOptimization, LookaheadQueryEnabled}
    TemplateId                 : Azure2Azure
    AdditionalProperties       : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#servicePrincipals('<ServicePrincipalId>')/synchro
                                 nization/jobs/$entity]}
    ```
2. プロビジョニング ジョブの状態を監視するほか、[Get-MgAuditLogProvisioning](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.reports/get-mgauditlogprovisioning) コマンドを使用してプロビジョニング ログを取得し、発生したすべてのプロビジョニング イベントを確認します。 たとえば、特定のユーザーについてクエリを実行し、正常にプロビジョニングされたかどうかを判断します。

    ```powershell
    Get-MgAuditLogDirectoryAudit | Select -First 10 | Format-List
    ```

    ```Output
    ActivityDateTime     : 7/31/2023 12:08:17 AM
    ActivityDisplayName  : Export
    AdditionalDetails    : {Details, ErrorCode, EventName, ipaddr...}
    Category             : ProvisioningManagement
    CorrelationId        : aaaa0000-bb11-2222-33cc-444444dddddd
    Id                   : Sync_aaaa0000-bb11-2222-33cc-444444dddddd_L5BFV_161778479
    InitiatedBy          : Microsoft.Graph.PowerShell.Models.MicrosoftGraphAuditActivityInitiator1
    LoggedByService      : Account Provisioning
    OperationType        :
    Result               : success
    ResultReason         : User 'user2@fabrikam.com' was created in Azure Active Directory (target tenant)
    TargetResources      : {<ServicePrincipalId>, }
    AdditionalProperties : {}
    
    ActivityDateTime     : 7/31/2023 12:08:17 AM
    ActivityDisplayName  : Export
    AdditionalDetails    : {Details, ErrorCode, EventName, ipaddr...}
    Category             : ProvisioningManagement
    CorrelationId        : aaaa0000-bb11-2222-33cc-444444dddddd
    Id                   : Sync_aaaa0000-bb11-2222-33cc-444444dddddd_L5BFV_161778264
    InitiatedBy          : Microsoft.Graph.PowerShell.Models.MicrosoftGraphAuditActivityInitiator1
    LoggedByService      : Account Provisioning
    OperationType        :
    Result               : success
    ResultReason         : User 'user2@fabrikam.com' was updated in Azure Active Directory (target tenant)
    TargetResources      : {<ServicePrincipalId>, }
    AdditionalProperties : {}
    
    ActivityDateTime     : 7/31/2023 12:08:14 AM
    ActivityDisplayName  : Synchronization rule action
    AdditionalDetails    : {Details, ErrorCode, EventName, ipaddr...}
    Category             : ProvisioningManagement
    CorrelationId        : aaaa0000-bb11-2222-33cc-444444dddddd
    Id                   : Sync_aaaa0000-bb11-2222-33cc-444444dddddd_L5BFV_161778395
    InitiatedBy          : Microsoft.Graph.PowerShell.Models.MicrosoftGraphAuditActivityInitiator1
    LoggedByService      : Account Provisioning
    OperationType        :
    Result               : success
    ResultReason         : User 'user2@fabrikam.com' will be created in Azure Active Directory (target tenant) (User is active and assigned
                           in Azure Active Directory, but no matching User was found in Azure Active Directory (target tenant))
    TargetResources      : {<ServicePrincipalId>, }
    AdditionalProperties : {}
    ```

## [Microsoft Graph](#tab/ms-graph)
1. これで、プロビジョニング ジョブが実行中になったので、[同期ジョブの取得](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationjob-get) API を使用して、現在のプロビジョニング サイクルの進行状況と、現在までの統計情報 (ターゲット システムで作成されたユーザーとグループの数など) を監視します。

    **依頼**

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals/{servicePrincipalId}/synchronization/jobs/{jobId}
    ```

    **応答**

    ```http
    HTTP/1.1 200 OK
    Content-type: application/json
    
    {
        "id": "{jobId}",
        "templateId": "Azure2Azure",
        "schedule": {
            "expiration": null,
            "interval": "PT40M",
            "state": "Active"
        },
        "status": {
            "countSuccessiveCompleteFailures": 0,
            "escrowsPruned": false,
            "code": "NotRun",
            "lastSuccessfulExecution": null,
            "lastSuccessfulExecutionWithExports": null,
            "quarantine": null,
            "steadyStateFirstAchievedTime": "0001-01-01T00:00:00Z",
            "steadyStateLastAchievedTime": "0001-01-01T00:00:00Z",
            "troubleshootingUrl": "",
            "lastExecution": {
                "activityIdentifier": null,
                "countEntitled": 0,
                "countEntitledForProvisioning": 0,
                "countEscrowed": 0,
                "countEscrowedRaw": 0,
                "countExported": 0,
                "countExports": 0,
                "countImported": 0,
                "countImportedDeltas": 0,
                "countImportedReferenceDeltas": 0,
                "state": "Failed",
                "timeBegan": "0001-01-01T00:00:00Z",
                "timeEnded": "0001-01-01T00:00:00Z",
                "error": {
                    "code": "None",
                    "message": "",
                    "tenantActionable": false
                }
            },
            "progress": [],
            "synchronizedEntryCountByType": []
        },
        "synchronizationJobSettings": [
            {
                "name": "AzureIngestionAttributeOptimization",
                "value": "False"
            },
            {
                "name": "LookaheadQueryEnabled",
                "value": "False"
            }
        ]
    }
    ```
2. プロビジョニング ジョブの状態の監視に加えて、[List provisioningObjectSummary](https://learn.microsoft.com/ja-jp/graph/api/provisioningobjectsummary-list) API を使用してプロビジョニング ログを取得し、発生するすべてのプロビジョニング イベントを取得します。 たとえば、特定のユーザーについてクエリを実行し、正常にプロビジョニングされたかどうかを判断します。

    **依頼**

    ```http
    GET https://graph.microsoft.com/v1.0/auditLogs/provisioning?$filter=((contains(tolower(servicePrincipal/id), '{servicePrincipalId}') or contains(tolower(servicePrincipal/displayName), '{servicePrincipalId}')) and activityDateTime gt 2023-07-30 and activityDateTime lt 2023-07-31)&$top=500&$orderby=activityDateTime desc
    ```

    **応答**

    ここに示されている応答オブジェクトは、読みやすくするために短縮されています。

    ```http
    HTTP/1.1 200 OK
    Content-type: application/json
    
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#auditLogs/provisioning",
        "value": [
            {
                "id": "{id}",
                "activityDateTime": "2023-07-31T00:40:37Z",
                "tenantId": "{targetTenantId}",
                "jobId": "{jobId}",
                "cycleId": "{cycleId}",
                "changeId": "{changeId}",
                "provisioningAction": "create",
                "durationInMilliseconds": 4375,
                "servicePrincipal": {
                    "id": "{servicePrincipalId}",
                    "displayName": "Fabrikam"
                },
                "sourceSystem": {
                    "id": "{id}",
                    "displayName": "Azure Active Directory",
                    "details": {}
                },
                "targetSystem": {
                    "id": "{id}",
                    "displayName": "Azure Active Directory (target tenant)",
                    "details": {
                        "ApplicationId": "{applicationId}",
                        "ServicePrincipalId": "{servicePrincipalId}",
                        "ServicePrincipalDisplayName": "Fabrikam"
                    }
                },
                "initiatedBy": {
                    "id": "",
                    "displayName": "Azure AD Provisioning Service",
                    "initiatorType": "system"
                },
                "sourceIdentity": {
                    "id": "{sourceUserObjectId}",
                    "displayName": "User4",
                    "identityType": "User",
                    "details": {
                        "id": "{sourceUserObjectId}",
                        "odatatype": "User",
                        "DisplayName": "User4",
                        "UserPrincipalName": "user4@fabrikam.com"
                    }
                },
                "targetIdentity": {
                    "id": "{targetUserObjectId}",
                    "displayName": "",
                    "identityType": "User",
                    "details": {}
                },
                "provisioningStatusInfo": {
                    "status": "success",
                    "errorInformation": null
                },
            "provisioningSteps": [
                {
                    "name": "EntryImportAdd",
                    "provisioningStepType": "import",
                    "status": "success",
                    "description": "Received User 'user4@fabrikam.com' change of type (Add) from Azure Active Directory",
                    "details": {
                        "objectId": "{sourceUserObjectId}",
                        "accountEnabled": "True",
                        "department": "Marketing",
                        "displayName": "User4",
                        "mailNickname": "user4",
                        "userPrincipalName": "user4@fabrikam.com",
                        "netId": "{netId}",
                        "showInAddressList": "",
                        "alternativeSecurityIds": "None",
                        "IsSoftDeleted": "False",
                        "appRoleAssignments": "msiam_access"
                    }
                },
                {
                    "name": "EntrySynchronizationScoping",
                    "provisioningStepType": "scoping",
                    "status": "success",
                    "description": "Determine if User in scope by evaluating against each scoping filter",
                    "details": {
                        "Active in the source system": "True",
                        "Assigned to the application": "True",
                        "User has the required role": "True",
                        "Scoping filter evaluation passed": "True",
                        "ScopeEvaluationResult": "{\"Marketing department filter.department EQUALS 'Marketing'\":true}"
                    }
                },
    
                ...
    
            }
        ]
    }
    ```

---

### トラブルシューティングのヒント

## [PowerShell](#tab/ms-powershell)
##### 現象 - 不十分な特権エラー

アクションを実行しようとすると、次のようなメッセージが表示されます。

```
code: Authorization_RequestDenied
message: Insufficient privileges to complete the operation.
```

**原因**

サインインしているユーザーに十分な特権がないか、または必要なアクセス許可のいずれかに同意する必要があります。

**解決策**

1. 必要なロールが割り当てられていることを確認します。 この記事に出てきた「前提条件」を参照してください。
2. [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) でサインインする場合は、必要なスコープを必ず指定します。 この記事の「手順 1: ターゲット テナントにサインインする」と「手順 4: ソース テナントにサインインする」を参照してください。

##### 症状 - New-MgPolicyCrossTenantAccessPolicyPartner\_Create エラー

新しいパートナー構成を作成しようとすると、次のようなエラー メッセージが表示されます。

`New-MgPolicyCrossTenantAccessPolicyPartner_Create: Another object with the same value for property tenantId already exists.`

**原因**

以前の構成からのものと思われる、既に存在する構成またはオブジェクトを作成しようとしている可能性があります。

**解決策**

1. 使用した構文とテナント ID が正しいことを確認します。
2. [Get-MgPolicyCrossTenantAccessPolicyPartner](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mgpolicycrosstenantaccesspolicypartner) コマンドを使用して、既存のオブジェクトを一覧表示します。
3. 既存のオブジェクトがある場合は、[Update-MgPolicyCrossTenantAccessPolicyPartner](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/update-mgpolicycrosstenantaccesspolicypartner) を使用した更新が必要な場合があります

##### 現象 - Request\_MultipleObjectsWithSameKeyValue エラー

ユーザー同期を有効にしようとすると、次のようなメッセージが表示されます。

```
Invoke-MgGraphRequest: PUT https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/partners/<SourceTenantId>/identitySynchronization
HTTP/1.1 409 Conflict
...
{"error":{"code":"Request_MultipleObjectsWithSameKeyValue","message":"A conflicting object with one or more of the specified property values is present in the directory.","details":[{"code":"ConflictingObjects","message":"A conflicting object with one or more of the specified property values is present in the directory.", ... }}}
```

**原因**

以前の構成のものと考えられる、既に存在するポリシーを作成しようとしている可能性があります。

**解決策**

1. 使用した構文とテナント ID が正しいことを確認します。
2. [Get-MgPolicyCrossTenantAccessPolicyPartnerIdentitySynchronization](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mgpolicycrosstenantaccesspolicypartneridentitysynchronization) コマンドを使用して、`IsSyncAllowed` 設定を一覧表示します。

    ```powershell
    (Get-MgPolicyCrossTenantAccessPolicyPartnerIdentitySynchronization -CrossTenantAccessPolicyConfigurationPartnerTenantId $SourceTenantId).UserSyncInbound
    ```
3. 既存のポリシーがあると、[Set-MgPolicyCrossTenantAccessPolicyPartnerIdentitySynchronization](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/set-mgpolicycrosstenantaccesspolicypartneridentitysynchronization) コマンドを使用して更新し、ユーザー同期を有効にすることが必要になる場合があります。

    ```powershell
    $Params = @{
        userSyncInbound = @{
            isSyncAllowed = $true
        }
    }
    Set-MgPolicyCrossTenantAccessPolicyPartnerIdentitySynchronization -CrossTenantAccessPolicyConfigurationPartnerTenantId $SourceTenantId -BodyParameter $Params
    ```

## [Microsoft Graph](#tab/ms-graph)
##### 現象 - 不十分な特権エラー

アクションを実行しようとすると、次のようなメッセージが表示されます。

```
code: Authorization_RequestDenied
message: Insufficient privileges to complete the operation.
```

**原因**

サインインしているユーザーに十分な特権がないか、または必要なアクセス許可のいずれかに同意する必要があります。

**解決策**

1. 必要なロールが割り当てられていることを確認します。 この記事に出てきた「前提条件」を参照してください。
2. [Microsoft Graph Explorer ツール](https://aka.ms/ge)で、必要なアクセス許可に同意していることを確認します。 この記事の「手順 1: ターゲット テナントにサインインする」と「手順 4: ソース テナントにサインインする」を参照してください。

##### 現象 - Request\_MultipleObjectsWithSameKeyValue エラー

Microsoft Graph API を呼び出そうとすると、次のようなエラー メッセージが表示されます。

```
code: Request_MultipleObjectsWithSameKeyValue
message: Another object with the same value for property tenantId already exists.
message: A conflicting object with one or more of the specified property values is present in the directory.
```

**原因**

以前の構成からのものと思われる、既に存在する構成またはオブジェクトを作成しようとしている可能性があります。

**解決策**

1. 要求構文と、正しいテナント ID を使用していることを確認します。
2. 既存のオブジェクトを一覧表示する `GET` 要求を行います。
3. 既存のオブジェクトがある場合は、`POST` または `PUT` を使用して作成要求を行う代わりに、次のように `PATCH` を使用して更新要求が行うことが必要になることがあります。

    - [crossTenantAccessPolicyConfigurationPartner を更新する](https://learn.microsoft.com/ja-jp/graph/api/crosstenantaccesspolicyconfigurationpartner-update)
    - [crossTenantIdentitySyncPolicyPartner を更新する](https://learn.microsoft.com/ja-jp/graph/api/crosstenantidentitysyncpolicypartner-update)

##### 現象 - Directory\_ObjectNotFound エラー

Microsoft Graph API を呼び出そうとすると、次のようなエラー メッセージが表示されます。

```
code: Directory_ObjectNotFound
message: Unable to read the company information from the directory.
```

**原因**

`PATCH` を使用して、存在しないオブジェクトを更新しようとしている可能性があります。

**解決策**

1. 要求構文と、正しいテナント ID を使用していることを確認します。
2. オブジェクトが存在しないことを確認する `GET` 要求を行います。
3. オブジェクトが存在しない場合は、`PATCH` を使用して更新要求を行う代わりに、次のように `POST` または `PUT` を使用して作成要求を行うことが必要になることがあります。

    - [identitySynchronization を作成する](https://learn.microsoft.com/ja-jp/graph/api/crosstenantaccesspolicyconfigurationpartner-put-identitysynchronization)

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/multi-tenant-organizations/cross-tenant-synchronization-directory-extensions"} -->
## テナント間同期でのディレクトリ拡張機能をマップする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-directory-extensions
- Service: entra-id / multitenant-organizations
- Article date: 2026-03-25
- Summary: テナント間同期でカスタム ディレクトリ拡張機能属性をマップします。 拡張機能の作成、属性マッピングへの追加、および手動によるスキーマ編集について説明します。

### 概要

ディレクトリ拡張機能では、Microsoft Entra ID のスキーマを独自の属性で拡張できます。 テナント間同期でユーザーをプロビジョニングするとき、これらのディレクトリ拡張機能をマップできます。 [カスタム セキュリティ属性](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview) は異なり、テナント間同期ではサポートされていません。

この記事では、テナント間同期でのディレクトリ拡張機能をマップする方法について説明します。

### 前提条件

- テナント間同期を構成するための[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)ロール。
- 構成にユーザーを割り当て、構成を削除する[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)または[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)ロール。

### ディレクトリ拡張機能を作成する

ディレクトリ拡張機能がまだない場合、ソースまたはターゲット テナントで 1 つまたは複数のディレクトリ拡張機能を作成する必要があります。 Microsoft Entra Connect または Microsoft Graph API を使用して拡張機能を作成できます。 ディレクトリ拡張機能を作成する方法については、「 [Microsoft Entra Application Provisioning の拡張機能属性の同期](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)」を参照してください。

### ディレクトリ拡張機能をマップする

[Image: ソース テナントのアイコン。]**ソース テナント**

1 つまたは複数のディレクトリ拡張機能がある場合、テナント間同期で属性をマップするときにそれらを使用できます。

1. ソース テナントの [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **Entra ID**&gt;**External Ids**&gt;**Cross-tenant 同期**に移動します。
3. [ **構成]** を選択し、構成を選択します。
4. [ **プロビジョニング** ] を選択し、[マッピング] セクション **を** 展開します。

    [Image: [マッピング] セクションが展開された [プロビジョニング] ページを示すスクリーンショット。]
5. **[Microsoft Entra ID ユーザーをプロビジョニング]** を選択して、**[属性マッピング]** ページを開きます。
6. ページの一番下までスクロールし、[ **新しいマッピングの追加]** を選択します。

    [Image: [新しいマッピングの追加] リンクが表示された [属性マッピング] ページを示すスクリーンショット。]
7. [ **ソース属性** ] ドロップダウン リストで、ソース属性を選択します。

    ソース テナントでディレクトリ拡張機能を作成した場合、そのディレクトリ拡張機能を選択します。

    [Image: [ソース属性] にディレクトリ拡張機能が表示されている [属性の編集] ページを示すスクリーンショット。]

    そのディレクトリ拡張機能がリストにない場合、ディレクトリ拡張機能が正常に作成されたことを確認します。 次のセクションで説明するように、ディレクトリ拡張機能を属性リストに手動で追加してみることもできます。
8. [ **ターゲット属性** ] ドロップダウン リストで、ターゲット属性を選択します。

    ターゲット テナントでディレクトリ拡張機能を作成した場合、そのディレクトリ拡張機能を選択します。
9. [ **OK] を** 選択してマッピングを保存します。

### 属性リストにディレクトリ拡張機能を手動で追加する

[Image: ソース テナントのアイコン。]**ソース テナント**

ディレクトリ拡張機能が自動的に検出されなかった場合、次の手順を試し、ディレクトリ拡張機能を属性リストに手動で追加できます。

1. 次のリンクを使用し、ソース テナントの Microsoft Entra 管理センターにサインインします。

    https://entra.microsoft.com/?Microsoft_AAD_Connect_Provisioning_forceSchemaEditorEnabled=true
2. **Entra ID**&gt;**External Ids**&gt;**Cross-tenant 同期**に移動します。
3. [ **構成]** を選択し、構成を選択します。
4. [ **プロビジョニング** ] を選択し、[マッピング] セクション **を** 展開します。
5. **[Microsoft Entra ID ユーザーをプロビジョニング]** を選択して、**[属性マッピング]** ページを開きます。
6. 一番下までスクロールし、[ **詳細設定の表示** ] チェック ボックスをオンにします。

    [Image: 詳細オプションが表示された [属性マッピング] ページのスクリーンショット。]

    ヒント

    **[属性リストの編集]** リンクが表示されない場合は、手順 1 のリンクを使用して Microsoft Entra 管理センターにサインインしていることを確認してください。
7. ソース テナントでディレクトリ拡張機能を作成した場合は、 **Microsoft Entra ID の属性リストの編集リンクを** 選択します。
8. ターゲット テナントで拡張機能を作成した場合は、 **Azure Active Directory (ターゲット テナント) リンクの [属性の編集] リスト** を選択します。
9. ディレクトリ拡張機能を追加し、適切なオプションを選択します。

    [Image: ディレクトリ拡張機能が追加された [属性リストの編集] ページのスクリーンショット。]
10. **[保存] を選択します**。
11. ブラウザーを更新します。
12. **[属性マッピング**] ページを参照し、この記事で前述したようにディレクトリ拡張機能のマップを試みます。

### スキーマを編集することでディレクトリ拡張機能を手動追加する

[Image: ソース テナントのアイコン。]**ソース テナント**

次の手順に従い、スキーマ エディターを利用し、ディレクトリ拡張機能をスキーマに手動で追加します。

1. ソース テナントの [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **Entra ID**&gt;**External Ids**&gt;**Cross-tenant 同期**に移動します。
3. [ **構成]** を選択し、構成を選択します。
4. [ **プロビジョニング** ] を選択し、[マッピング] セクション **を** 展開します。
5. **[Microsoft Entra ID ユーザーをプロビジョニング]** を選択して、**[属性マッピング]** ページを開きます。
6. 一番下までスクロールし、[ **詳細設定の表示** ] チェック ボックスをオンにします。

    [Image: スキーマ エディターへのリンクを含む [属性マッピング] ページのスクリーンショット。]
7. [スキーマ **エディター**] ページを開くには、[**ここでスキーマを確認**する] リンクを選択します。

    [Image: JSON でスキーマを編集するためのオプションが [スキーマ エディター] ページのスクリーンショット。]
8. スキーマの元のコピーをバックアップとしてダウンロードします。
9. 必要な構成に従ってスキーマを変更します。
10. **[保存] を選択します**。
11. ブラウザーを更新します。
12. **[属性マッピング**] ページを参照し、この記事で前述したようにディレクトリ拡張機能のマップを試みます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/multi-tenant-organizations/cross-tenant-synchronization-governance"} -->
## ガバナンスとテナント間同期 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-governance
- Service: entra-id / multitenant-organizations
- Article date: 2026-03-18
- Summary: マルチテナント組織間で ID およびアクセス ライフサイクルを管理する方法について説飯います。

テナント間同期は、アカウントをプロビジョニングし、組織内のテナント間のシームレスなコラボレーションを促進するための柔軟ですぐに使えるソリューションです。 テナント間同期により、テナント間のユーザー ID ライフサイクルを自動的に管理します。 同期範囲内で、ソーステナントからユーザーをプロビジョニングし、同期し、解除します。

この記事では、 [Microsoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview)の顧客が、テナント間同期を使用して、マルチテナント組織の ID およびアクセスのライフサイクルを管理する方法について説明します。

### デプロイの例

この例では、Contoso は、3 つの運用環境 Microsoft Entra テナントを持つマルチテナント組織です。 Contoso は、テナント間同期と Microsoft Entra ID ガバナンス機能をデプロイし、次のシナリオに対応します。

- 複数のテナントにわたる従業員の ID およびライフサイクルを管理する
- ワークフローを使用して、他のテナントで生成された従業員のライフサイクル プロセスを自動化する
- 他のテナントで生成された従業員へのリソース アクセスを自動的に割り当てる
- 従業員が複数のテナントのリソースへのアクセスを要求できるようにします。
- 同期済みユーザーのアクセスを確認する

テナント間同期の観点から、Contoso Europe, Middle East, and Africa (Contoso EMEA) と Contoso United States (Contoso US) はソース テナントであり、Contoso はターゲット テナントです。 次の図は、トポロジを示しています。

[Image: テナント間同期のトポロジの図。]

このサポートされる[テナント間同期のトポロジ](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-topology)は、Microsoft Entra ID に多数存在するトポロジの 1 つです。 テナントは、ソース テナント、ターゲット テナント、またはその両方にすることができます。 次のセクションでは、テナント間同期と Microsoft Entra ID ガバナンス機能でいくつかのシナリオに対応する方法について説明します。

### テナント間で従業員のライフサイクルを管理する

[Microsoft Entra ID でのテナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)を使用すると、B2B コラボレーション ユーザーの作成、更新、削除が自動化されます。

組織がテナントに B2B コラボレーション ユーザーを作成またはプロビジョニングする場合、ユーザー アクセスの一部は、組織によるユーザーのプロビジョニング方法に依存します: ゲスト ユーザータイプまたはメンバー ユーザータイプ。 ユーザー タイプを選択する場合は、さまざまな [Microsoft Entra B2B コラボレーション ユーザーのプロパティ](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)を考慮してください。 メンバー ユーザー タイプは、ユーザーが大規模なマルチテナント組織の一員であり、組織のテナント内のリソースへのメンバー レベルのアクセスが必要な場合に適しています。 Microsoft Teams では、[マルチテナント組織](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/plan-multi-tenant-org-overview?view=o365-worldwide&preserve-view=true)内のメンバー ユーザー タイプが必要です。

既定では、テナント間同期には、Microsoft Entra ID のユーザー オブジェクトで一般的に使用される属性が含まれます。 次の図にこのシナリオを示します。

[Image: 一般的に使用される属性との同期の図。]

組織はこの属性を使用して、ソース テナントとターゲット テナントで動的メンバーシップ グループとアクセス パッケージを作成するサポートを行います。 Microsoft Entra ID 機能には、ライフサイクル ワークフロー ユーザーのスコープ設定など、対象となるユーザー属性があります。

テナントから B2B コラボレーション ユーザーを削除 (プロビジョニング解除) すると、そのテナント内のリソースへのアクセスが自動的に停止されます。 この構成は、従業員が組織を脱退する場合に関係します。

### ワークフローを使用してライフサイクル プロセスを自動化する

Microsoft Entra ID ライフサイクル ワークフローは、Microsoft Entra ユーザーを管理するための ID ガバナンス機能です。 組織では、新規入社者、異動者、退職者のプロセスを自動化できます。

テナント間同期により、マルチテナント組織は、管理する B2B コラボレーション ユーザーに対して自動的に実行されるライフサイクル ワークフローを構成できます。 たとえば、`createdDateTime` イベント ユーザー属性によってトリガーされるユーザーオンボード ワークフローを構成して、新しい B2B コラボレーション ユーザーにアクセス パッケージの割り当てを要求します。 `userType` や `userPrincipalName` などの属性を使用して、組織が所有する他のテナントにいるユーザーのライフサイクル ワークフローをスコープ設定します。

### アクセス パッケージで同期されたユーザー アクセスを管理する

マルチテナント組織は、B2B コラボレーション ユーザーがターゲット テナント内の共有リソースにアクセスできるようにすることができます。 ユーザーは、必要に応じてアクセスを要求できます。 次のシナリオでは、ID ガバナンス機能、[エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)アクセス パッケージでリソース アクセスを管理する方法をご覧ください。

#### ソース テナントから従業員にターゲット テナントへのアクセスを自動的に割り当てる

生得権割り当てという用語は、1 つまたは複数のユーザー プロパティに基づいてリソース アクセスを自動的に付与することを指します。 生得権割り当てを構成するには、エンタイトルメント管理で[アクセス パッケージの自動割り当てポリシー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)を作成し、共有リソース アクセスを付与するリソース ロールを構成します。

組織は、ソース テナントでテナント間同期の構成を管理します。 そのため、組織は同期された B2B コラボレーション ユーザーに対するリソース アクセス管理を他のソース テナント管理者に委任できます:

- ソース テナントでは、管理者は、テナント間リソース アクセスが必要なユーザーに対して、テナント間同期属性マッピングを構成します
- ターゲット テナントでは、管理者は自動割り当てポリシーの属性を使用して、同期された B2B コラボレーション ユーザーのアクセス パッケージ メンバーシップを決定します

ターゲット テナントで自動割り当てポリシーを促進するには、ソース テナントで部署やマップ ディレクトリ拡張子などの既定の属性マッピングを同期します。

#### ソース テナントの従業員がターゲット テナント共有リソースへのアクセスを要求できるようにします。

ID ガバナンス [アクセス パッケージ](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create) ポリシーを使用すると、マルチテナント組織は、テナント間同期で作成された B2B コラボレーション ユーザーに、ターゲット テナント内の共有リソースへのアクセス要求を許可できます。 このプロセスは、従業員が他のテナントで所有されるリソースにジャスト イン タイム (JIT) アクセスが必要な場合に役立ちます。

### 同期済みユーザーのアクセスを確認する

[Microsoft Entra ID のアクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)を組織で使用すると、グループ メンバーシップ、エンタープライズ アプリケーションへのアクセス、およびロールの割り当てを管理できます。 ユーザー アクセスを定期的に確認し、適切なユーザーがアクセスできるようにします。

リソース アクセス構成が動的メンバーシップ グループやアクセス パッケージなどによりアクセス権を自動的に割り当てない場合は、完了時に結果をリソースに適用するようにアクセス レビューを構成します。 次のセクションでは、マルチテナント組織が、ソース テナントとターゲット テナントのテナント間でユーザーのアクセス レビューを構成できるようにする方法について説明します。

#### ソーステナントのユーザーのアクセスを確認する

マルチテナント組織は、アクセス レビューに内部ユーザーを含めることができます。 このアクションは、ユーザーを同期するソース テナントでのアクセス再認証を有効にします。 テナント間同期に割り当てられたセキュリティ グループの定期的な確認には、このアプローチを使用します。 そのため、他のテナントへの継続的な B2B コラボレーション アクセスは、ユーザー ホーム テナントで承認されます。

ソース テナント内のユーザー アクセス レビューを使用して、テナント間同期と、完了時に拒否されたユーザーを削除するアクセス レビューに発生する可能性のある競合を回避します。

#### ターゲット テナント ユーザー アクセスを確認する

組織は、ターゲット テナントのテナント間同期によってプロビジョニングされたユーザーなど、B2B コラボレーション ユーザーをアクセス レビューに含めることができます。 このオプションは、ターゲット テナントでのリソースのアクセス再認証を有効にします。 組織は、アクセス レビューにおいてすべてのユーザーをターゲットにすることができますが、必要に応じてゲスト ユーザーを明示的にターゲットにすることができます。

B2B Collaboration ユーザーを同期している組織に関しては、Microsoft は通常、拒否されたゲスト ユーザーをアクセス レビューから自動的に削除することを推奨していません。 テナント間同期では、同期範囲内にユーザーがいる場合、そのユーザーを再プロビジョニングします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview"} -->
## Microsoft Entra IDでのテナント間同期とは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview
- Service: entra-id / multitenant-organizations
- Article date: 2026-05-29
- Summary: Microsoft Entra ID でのテナント間同期について説明します。

### 概要

*テナント間同期* では、組織内のテナント間での [Microsoft Entra B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) ユーザーとグループの作成、更新、削除が自動化されます。 これにより、ユーザーはアプリケーションにアクセスし、テナント間で共同作業を行いながら、組織を進化させることができます。

テナント間同期の主な目標は次のとおりです。

- マルチテナント組織のシームレスなコラボレーション
- マルチテナント組織における B2B コラボレーション ユーザーの自動ライフサイクル管理
- ユーザーが組織を離れたときの B2B アカウントの自動削除

### テナント間同期を使用する理由

テナント間同期では、B2B コラボレーション ユーザーとグループの作成、更新、削除が自動化されます。 テナント間同期を使用して作成されたユーザーは、アプリが統合されているテナントに関係なく、Microsoft アプリケーション (Teams やSharePoint など) とMicrosoft以外のアプリケーション ([ServiceNow](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servicenow-provisioning-tutorial)、[Adobe](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adobe-identity-management-provisioning-saml-tutorial) など) の両方にアクセスできます。

これらのユーザーは、[Microsoft Entra 条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) や [クロステナント アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview) など、Microsoft Entra ID のセキュリティ機能の恩恵を受け続けます。 これらは、[Microsoft Entra エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)などの機能によって管理できます。

次の図は、テナント間同期を使用して、ユーザーが組織内のテナント間でアプリケーションにアクセスできるようにする方法を示しています。

[Image: 複数のテナントのユーザーの同期を示す図。]

### 誰がテナント間同期を使用する必要があるか

複数のMicrosoft Entra テナントを所有し、組織内のテナント間アプリケーション アクセスを合理化する必要がある組織は、テナント間の同期の恩恵を受けることができます。

テナント間同期は組織間で使用できますが、これを行うと、追加のコンプライアンス責任が生じる可能性があります。 お客様は、欧州連合一般データ保護規則 (GDPR) を含む、該当するプライバシー、セキュリティ、規制の要件に従って使用することを保証する責任を負います。

Microsoftでは、テナント間の同期によるユーザーの同意の収集は容易ではありません。 お客様は、シナリオでユーザーの同意、データの最小化、またはその他のセーフガードが必要かどうかを評価する必要があります。 また、お客様は、組織全体の同期またはテナント間の同期を有効にする前に、法務チームまたはコンプライアンス チームに問い合わせてください。

### メリット

テナント間同期を使用すると、次のことができます。

- 組織内で B2B コラボレーション ユーザーを自動的に作成し、カスタム スクリプトを作成して保守することなく、必要なアプリケーションにアクセスできるようにします。
- 招待メールを受け取らず、各テナントで同意プロンプトを受け入れなくても、ユーザーがリソースにアクセスできるようにすることで、ユーザー エクスペリエンスを向上させます。
- ユーザーが組織を離れたときに、そのユーザーを自動的に更新および削除する。

### Teams と Microsoft 365

テナント間同期を使用して作成されたユーザーは、手動招待によって作成された B2B コラボレーション ユーザーと同じエクスペリエンスでMicrosoft Teamsやその他のMicrosoft 365 サービスにアクセスできます。 組織で共有チャネルを使用している場合は、[Microsoft Entra ID でのプロビジョニングに関する既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/known-issues)を参照してください。 さまざまなMicrosoft 365 サービスでは、`member` プロパティの `userType` 値を使用して、マルチテナント組織のユーザーに差別化されたエクスペリエンスを提供します。

### プロパティ

テナント間同期を構成するときは、ソース テナントとターゲット テナントの間に信頼関係を定義します。 テナント間同期には、次の特性があります。

- これは、Microsoft Entra プロビジョニング エンジンに基づいています。
- これはソース テナントからのプッシュ プロセスであり、ターゲット テナントからのプル プロセスではありません。
- ソース テナントからの内部メンバーのプッシュのみがサポートされます。 ソース テナントからの外部ユーザーの同期はサポートされていない。
- 同期のスコープ内のユーザーは、ソース テナントで構成される。
- 属性マッピングは、ソース テナントで構成される。
- 拡張属性がサポートされている。
- ターゲット テナント管理者は、いつでも同期を停止できる。

次の表は、テナント間の同期の部分と、それらがどのテナントのために構成されているかを示しています。

| テナント | クロステナントアクセスの設定 | 自動引き換え | 同期設定構成 | スコープ内のユーザー |
| --- | --- | --- | --- | --- |
| [Image: ソース テナントのアイコン。]ソース テナント |  | ✔️ | ✔️ | ✔️ |
| [Image: ターゲット テナントのアイコン。]ターゲット テナント | ✔️ | ✔️ |  |  |

### テナント間同期の設定

クロステナント同期設定は、ソース テナントの管理者がユーザーとグループをターゲット テナントに同期できるようにするための受信専用の組織設定です。 これらの設定は、この **テナントへのユーザー同期を許可** し、ターゲット テナントで指定されている **このテナントへのグループ同期を許可** するという名前のチェック ボックスです。 これらの設定は、手動の招待やMicrosoft Entraの特権管理など、他のプロセスを通じて作成されたB2B招待状には影響しません。

[Image: クロステナント同期のタブを示すスクリーンショット。ユーザーとグループをターゲット テナントに同期するためのチェック ボックスが表示されています。]

Microsoft Graphを使用してこれらの設定を構成するには、[Update crossTenantIdentitySyncPolicyPartner](https://learn.microsoft.com/ja-jp/graph/api/crosstenantidentitysyncpolicypartner-update?branch=main) API を参照してください。 詳しくは、「[テナント間同期を構成する](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)」をご覧ください。

### 自動引き換えの設定

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

Microsoft Graphを使用してこの設定を構成するには、[Update crossTenantAccessPolicyConfigurationPartner](https://learn.microsoft.com/ja-jp/graph/api/crosstenantaccesspolicyconfigurationpartner-update?branch=main) API を参照してください。 詳しくは、「[テナント間同期を構成する](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)」をご覧ください。

#### ユーザーが自分が属しているテナントを知る方法

テナント間同期の場合、ユーザーはメールを受け取らないか、同意プロンプトを受け入れる必要があります。 ユーザーが自分が属しているテナントを確認するには、自分の [\[マイ アカウント\]](https://support.microsoft.com/account-billing/my-account-portal-for-work-or-school-accounts-eab41bfe-3b9e-441e-82be-1f6e568d65fd) ページを開き、**[組織]** を選択できます。 Microsoft Entra 管理センターでは、ユーザーは [portal 設定](https://learn.microsoft.com/ja-jp/azure/azure-portal/set-preferences)を開き、**Directories + subscriptions** を表示し、ディレクトリを切り替えることができます。

プライバシー情報など、詳細については、「[外部ユーザーとして組織を脱退する](https://learn.microsoft.com/ja-jp/entra/external-id/leave-the-organization)」を参照してください。

### 作業を開始するための手順

テナント間同期の使用を開始する基本的な手順を次に示します。

#### 手順 1: 組織内のテナントを構成する方法を定義する

テナント間同期により、コラボレーションを可能にする柔軟なソリューションが提供されますが、組織はそれぞれ異なります。 たとえば、中央テナント、サテライト テナント、またはテナントのメッシュがあるとします。 テナント間同期では、これらのトポロジのいずれもサポートされています。 詳しくは、「[テナント間同期のトポロジ](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-topology)」をご覧ください。

[Image: さまざまなテナント トポロジを示す図。]

#### 手順 2: ターゲット テナントでテナント間同期を有効にする

ユーザーが作成されるターゲット テナントで、[ **クロステナント アクセス設定** ] ウィンドウに移動します。 ここでは、それぞれのチェック ボックスをオンにして、テナント間同期と B2B の自動引き換え設定を有効にします。 詳しくは、「[テナント間同期を構成する](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)」をご覧ください。

[Image: ターゲット テナントで有効になっているテナント間同期を示す図。]

#### 手順 3: ソース テナントでテナント間同期を有効にする

任意のソース テナントで、[ **クロステナント アクセス設定** ] ウィンドウに移動し、B2B 自動引き換え機能を有効にします。 次に、[ **テナント間同期** ] ウィンドウを使用してテナント間同期ジョブを設定し、次のように指定します。

- 同期したいユーザーはどれですか？
- 含めたい属性はどれですか。
- あらゆる変換。

Microsoft Entra IDを使用して [サービスとしてのソフトウェア (SaaS) アプリケーションに ID をプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)ユーザーにとって、このエクスペリエンスはおなじみです。 同期を構成したら、少数のユーザーでテストを開始し、必要なすべての属性で作成されていることを確認できます。 テストが完了したら、組織全体で同期とロールアウトを行うユーザーをすばやく追加できます。 詳しくは、「[テナント間同期を構成する](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)」をご覧ください。

[Image: ソース テナントで構成されたテナント間同期ジョブを示す図。]

### ライセンスの要件

次の表に、シナリオに応じて必要なライセンスを示します。

| シナリオ | ソース テナント | ターゲット テナント |
| --- | --- | --- |
| ユーザーのテナント間同期 (同じクラウド) | Microsoft Entra ID P1 ライセンス | 適用なし |
| グループのテナント間同期 (同じクラウド) | Microsoft Entra ID ガバナンスまたは Microsoft Entra スイート ライセンス | 適用なし |
| [クラウド間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure?pivots=cross-cloud-synchronization) | Microsoft Entra ID ガバナンスまたは Microsoft Entra スイート ライセンス | 適用なし |

**Source テナント**: テナント間同期と同期する各ユーザーは、ホーム/ソース テナントに Microsoft Entra ID P1 ライセンスを持っている必要があります。 クラウド間同期と同期する各ユーザーは、ホーム/ソース テナントにMicrosoft Entra ID ガバナンスまたはMicrosoft Entra スイートライセンスを持っている必要があります。 詳細については、[Microsoft Entra のプランと価格、](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)[および Microsoft Entra ID ガバナンス のライセンスの基礎を参照してください](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals)。

**ターゲット テナント: ターゲット テナント**でのテナント間同期またはクラウド間同期にはライセンスは必要ありません。 ただし、ターゲット テナントで使用している機能によっては、追加のライセンスが必要になる場合があります。 たとえば、外部 ID の課金を有効にし、外部ゲストをプロビジョニングしている顧客は、[Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-pricing) の課金モデルに従って課金される場合があります。

### よく寄せられる質問

#### クラウド

##### 同じクラウド内で、テナント間同期はどこで使用できますか?

テナント間同期は、商用クラウド、Azure Government、21Vianet 内でサポートされます。

| 情報源 | 目標 | Azure portal のリンク ドメイン |
| --- | --- | --- |
| Azure 商用 | Azure 商用 | `portal.azure.com` --&gt;`portal.azure.com` |
| Azure Government | Azure Government | `portal.azure.us` --&gt;`portal.azure.us` |
| 21Vianet (中国) | 21Vianet (中国) | `portal.azure.cn` --&gt;`portal.azure.cn` |

##### クラウド間同期はサポートされていますか?

はい。[クロス クラウド同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure?pivots=cross-cloud-synchronization) (パブリック クラウドから Azure Government など) がサポートされます。

Azure クラウド環境とMicrosoft 365 (GCC、GCC High) の関係については、[Microsoft 365 統合](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/feature-availability#microsoft-365-integration)を参照してください。

##### クラウド間同期でサポートされているクラウド ペアは何ですか?

クラウド間同期では、次のクラウド ペアがサポートされます。

| 情報源 | 目標 | Azure portal のリンク ドメイン |
| --- | --- | --- |
| Azure 商用 | Azure Government | `portal.azure.com` --&gt;`portal.azure.us` |
| Azure Government | Azure 商用 | `portal.azure.us` --&gt;`portal.azure.com` |
| Azure 商用 | 21Vianet によって運営される Azure(中国の Azure) | `portal.azure.com` --&gt;`portal.azure.cn` |

##### テナント間同期とクラウド間同期の違いは何ですか?

テナント間同期とクラウド間同期は、同じテクノロジを使用して構築され、基本的には同じです。 主な違いは、同期が同じクラウド内ではなくクラウド間で行われることです。

##### クラウド間同期には制限がありますか?

`manager`属性の同期は、現在、クラウド間同期ではサポートされていません。

マルチテナント組織の制限については、 [マルチテナント組織](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/multitenant-org-faq#can-an-mto-be-created-across-worldwide-geographies)に関する FAQ を参照してください。

外部メンバーに対する Microsoft 365 の制限事項については、 [他の Microsoft 365 クラウド環境のゲストとの共同作業](https://learn.microsoft.com/ja-jp/microsoft-365/solutions/collaborate-guests-cross-cloud)に関するページを参照してください。

#### 既存の B2B ユーザー

##### テナント間同期では、既存の B2B ユーザーを管理できますか?

はい。 テナント間同期では、 `alternativeSecurityIdentifier` と呼ばれる内部属性を使用して、ソース テナント内の内部ユーザーとターゲット テナント内の外部ユーザーまたは B2B ユーザーを一意に照合します。 テナント間同期では、既存の B2B ユーザーを更新して、各ユーザーがアカウントを 1 つだけ持っていることを確認できます。

テナント間同期は、ソース テナント内の内部ユーザーとターゲット テナント内の内部ユーザー ( `member` 型と型 `guest`の両方) と一致させることはありません。

#### 同期の間隔

##### テナント間同期はどのくらいの頻度で実行されますか?

同期間隔は現在、40 分間隔で開始するように固定されています。 同期期間は、スコープ内ユーザーの数によって異なります。 初期同期サイクルは、後の増分同期サイクルよりも大幅に時間がかかる可能性があります。

#### Scope

##### ターゲット テナントに同期される内容を制御するにはどうすればよいですか?

ソース テナントでは、構成ベースまたは属性ベースのフィルターを使用して、プロビジョニングするユーザーを制御できます。 また、同期するユーザー オブジェクトの属性を制御することもできます。 詳細については、「[スコープ フィルターを使用してプロビジョニングするユーザーまたはグループのスコープを設定する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts?pivots=cross-tenant-synchronization)」を参照してください。

##### ユーザーがソース テナントの同期のスコープから削除された場合、テナント間同期はターゲット テナント内でそれらを論理的に削除しますか?

はい。

#### オブジェクトの型

##### 同期できるオブジェクトの種類は何ですか?

テナント間Microsoft Entraユーザーとセキュリティ グループを同期できます。 デバイスと連絡先は現在サポートされていません。

##### 同期できるユーザーの種類

ソース テナントから内部メンバーを同期できます。 ソース テナントから内部ゲストを同期することはできません。

外部メンバー (既定) または外部ゲストとして、ユーザーをターゲット テナントに同期できます。

`userType`定義の詳細については、「[B2B ゲスト ユーザーのプロパティの理解と管理](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)」を参照してください。

##### 既存の B2B コラボレーション ユーザーがいます。 彼らはどうなるでしょうか?

テナント間同期はユーザーと一致し、表示名の更新など、ユーザーに必要な更新を行います。 既定では、 `userType` は `guest` から `member` に更新されません。 このプロパティは、属性マッピングで構成できます。

#### 属性

##### 同期することができるユーザー属性は何ですか?

テナント間同期では、Microsoft Entra IDのユーザー オブジェクトで一般的に使用される属性 (`displayName`、`userPrincipalName`、ディレクトリ拡張属性など) を同期できます。

テナント間同期では、Azure 商用クラウドでの `manager` 属性のプロビジョニングがサポートされます。 マネージャーの同期は、現在、米国政府機関向けクラウドではサポートされていません。 `manager`属性をプロビジョニングするには、ユーザーとそのマネージャーの両方がテナント間同期のスコープ内にある必要があります。

既定のスキーマ/属性マッピングを使用して 2024 年 1 月より後に作成されたテナント間同期構成の場合:

- `manager`属性は、属性マッピングに自動的に追加されます。
- マネージャーの更新プログラムは、変更を受けているユーザー (マネージャーの変更など) の増分サイクルに適用されます。 同期エンジンは、以前にプロビジョニングされたすべての既存のユーザーを自動的に更新するわけではありません。
- プロビジョニングの対象になっている既存のユーザーのマネージャーを更新するには、特定のユーザーに対してオンデマンド プロビジョニングを使用するか、再起動してすべてのユーザーに対してマネージャーをプロビジョニングします。

カスタム スキーマ/属性マッピングを使用して 2024 年 1 月より前に作成されたテナント間同期構成の場合 (たとえば、マッピングに属性を追加したり、既定のマッピングを変更したりしました)。

- 属性マッピングに `manager` 属性を追加する必要があります。 このアクションにより再起動がトリガーされ、プロビジョニングのスコープ内にあるすべてのユーザーが更新されます。 このプロセスは、ソース テナントの `manager` 属性とターゲット テナントの `manager` 属性の直接マッピングである必要があります。

ソース テナントでユーザーのマネージャーが削除され、ソース テナントに新しいマネージャーが割り当てられていない場合、 `manager` 属性はターゲット テナントで更新されません。

##### 同期できない属性は何ですか?

クロステナント同期を使用して、写真、カスタム セキュリティ属性、ディレクトリの外部のユーザー属性などの属性を同期することはできません。

##### ユーザー属性をソースまたは管理する場所を制御できますか?

テナント間同期では、権限のソースを直接制御することはできません。 ユーザーとその属性は、ソース テナントで権限があると見なされます。

ユーザーの権限ソース制御を属性レベルに進化させる、並列の権限ソース ワークストリームがあります。 ソースのユーザー オブジェクトには、最終的に複数の基になるソースが反映される場合があります。 テナント間プロセスの場合、この状況はソース テナントの値として引き続き、ターゲット テナントへの同期プロセスに対して信頼できるものとして扱われます (一部が別の場所で発生した場合でも)。 現在、同期プロセスの権限ソースを元に戻すことはサポートされていません。

テナント間同期では、オブジェクト レベルでのみ権限のソースがサポートされます。 ユーザーのすべての属性は、資格情報を含め、同じソースから取得する必要があります。 同期されたオブジェクトの権限ソースまたはフェデレーションの方向を逆にすることはできません。

##### ターゲット テナントで同期されたユーザーの属性を変更するとどうなりますか?

テナント間同期では、ターゲットの変更に対してクエリは実行されません。 ソース テナント内の同期されたユーザーに変更を加えない場合、ターゲット テナントで行ったユーザー属性の変更は保持されます。 ソース テナント内のユーザーに変更を加えた場合、次の同期サイクル中に、ターゲット テナント内のユーザーがソース テナントのユーザーと一致するように更新されます。

##### ターゲット テナントは、同期された特定のホーム/ソース テナント ユーザーのサインインを手動でブロックできますか?

ソース テナント内の同期されたユーザーに変更を加えない場合、ターゲット テナントでのサインインをブロックする設定は保持されます。 ソース テナント内のユーザーに対して変更が検出された場合、クロステナント同期では、ターゲット テナントでのサインインがブロックされているユーザーが再び有効になります。

#### グループ同期

##### グループの同期はサポートされていますか?

はい。テナント間同期では、ターゲット テナントにセキュリティ グループを作成できます。

グループが同期されると、同期のスコープ内にあるグループのすべてのメンバーが同期されます。 詳しくは、「[テナント間同期を構成する](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure?pivots=same-cloud-synchronization)」をご覧ください。

##### ターゲット テナントにグループが既に存在する場合はどうなりますか。

(テナント間同期の外部で作成された) ターゲット テナントにグループが存在する場合、テナント間同期では更新されません。

##### サポートされているグループの種類は何ですか?

| サポート | 情報源 | 目標 |
| --- | --- | --- |
| サポートされている | セキュリティ グループ (静的および動的)Microsoft 365 グループ | セキュリティ グループ (静的) |
| サポートしていません | その他のグループの種類は次のとおりです。次に例を示します。- メールが有効なセキュリティ グループ- 共有メールボックス- 動的配布グループ- 配布グループ | その他のグループの種類は次のとおりです。次に例を示します。- Microsoft 365 グループ- メールが有効なセキュリティ グループ- 共有メールボックス- 動的配布グループ- 配布グループ |

##### グループの同期にはどのような制限がありますか?

- 現在、ロールの割り当て可能なグループの作成はサポートされていません。
- ネストされたグループはサポートされていません。
- テナント間同期では、Microsoft 365 グループ、配布グループ、メールが有効なセキュリティ グループ、配布リストは作成されません。
- 同期スコープは、 **割り当てられたユーザーとグループのみを同期**するように設定する必要があります。 グループ同期が有効になっている場合、[ **すべてのユーザーの同期** ] オプションはサポートされていません。
- 中国でのAzure商用、Azure Government、Azureなどのクラウド環境間でのグループの同期はサポートされていません。
- ターゲット テナント内のグループに対する変更は、自動的にはオーバーライドされません。 これらは、ソース テナント内のグループに変更がある場合にのみオーバーライドされます。

    たとえば、グループがテナント A からテナント B に同期され、管理者がテナント B のグループに変更を加えた場合、その変更はテナント B に保持されます。同期エンジンは、ターゲット テナント内のグループに加えられた変更を検出しないため、変更をオーバーライドしません。
- クロステナント同期の外部でグループが作成された場合、グループはテナント間同期には含まれません。
- 同期のパフォーマンスは、同期ジョブによって処理されるオブジェクト (ユーザー、グループ) と参照 (グループ メンバーシップやマネージャーリレーションシップなど) の合計数によって異なります。 オブジェクトと参照の全体的な量が増えるにつれて、変更の評価と同期に必要な時間も長くなる可能性があります。 大規模な環境では、同期サイクルが長くなり、更新がターゲット テナントに反映される待機時間が長くなる可能性があります。 パフォーマンスを最適化するには、必要なユーザー、グループ、およびリレーションシップのみに同期をスコープします。

#### 構造体

##### 複数のテナント間でメッシュを同期できますか?

テナント間同期は、単一方向のピアツーピア同期として構成されます。つまり、同期は、1 つのソースと 1 つのターゲット テナントの間で構成されます。 1 つのソースから複数のターゲット、および複数のソースから 1 つのターゲットに同期するように、テナント間同期の複数のインスタンスを構成できます。 ただし、ソースとターゲットの間に存在できる同期インスタンスは 1 つだけです。

テナント間同期では、ホーム/ソース テナントの内部にいるユーザーのみが同期されます。 この制限により、ユーザーが同じテナントに書き戻されるループを作成できなくなります。

複数のトポロジがサポートされています。 詳しくは、「[テナント間同期のトポロジ](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-topology)」をご覧ください。

##### 組織間 (マルチテナント組織の外部) でテナント間同期を使用できますか?

プライバシー上の理由から、テナント間同期は組織内で使用することを目的としています。 組織間で B2B コラボレーション ユーザーを招待するために [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview) を使用することを検討してください。

##### テナント間同期を使用して、あるテナントから別のテナントにユーザーを移行できますか?

その必要はありません。 同期されたユーザーの認証にはソース テナントが必要であるため、テナント間同期は移行ツールではありません。 さらに、テナントの移行では、SharePointやOneDrive データなどのユーザー データを移行する必要があります。

#### B2B コラボレーション

##### テナント間同期では、B2B コラボレーションの現在の制限は解決されますか?

テナント間同期は既存の [B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) テクノロジに基づいて構築されているため、既存の制限が適用されます。 例を次に示します (ただし、これらに限定されません)。

| アプリまたはサービス | 制限事項 |
| --- | --- |
| Azure Virtual Desktop | 制限事項については、[Azure Virtual Desktop の前提条件](https://learn.microsoft.com/ja-jp/azure/virtual-desktop/prerequisites#users)を参照してください。 |
| Microsoft Teams | 制限事項については、「[他のMicrosoft 365クラウド環境からのゲストとの連携](https://learn.microsoft.com/ja-jp/microsoft-365/solutions/collaborate-guests-cross-cloud)を参照してください。 |

#### B2B 直接接続

##### テナント間同期は B2B 直接接続にどのように関連しますか?

[B2B 直接接続](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-direct-connect-overview) は、 [Teams Connect 共有チャネル](https://learn.microsoft.com/ja-jp/microsoftteams/platform/concepts/build-and-test/shared-channels)に必要な基になる ID テクノロジです。

B2B 直接接続とテナント間同期は、共存するように設計されています。 テナント間のシナリオを幅広くカバーするために、両方を有効にすることができます。

Microsoft アプリケーションと Microsoft 以外のアプリケーションの両方を含む、他のすべてのテナント間アプリケーション アクセス シナリオでは、B2B コラボレーションをお勧めします。

##### マルチテナント組織でテナント間同期を使用する必要がある範囲を特定しようとしています。 Teams Connect を超えて B2B 直接接続のサポートを拡張する予定はありますか?

B2B 直接接続のサポートを、Teams Connect 共有チャネルを超えて拡張する予定はありません。

#### Microsoft 365

##### テナント間同期は、アプリアクセスのためのテナント間Microsoft 365ユーザー エクスペリエンスを強化しますか?

テナント間同期では、各テナントでの初めての B2B 同意プロンプトと引き換えプロセスを抑制することで、ユーザー エクスペリエンスを向上させる機能が使用されます。

同期されたユーザーには、他の B2B コラボレーション ユーザーが使用できる同じテナント間Microsoft 365 エクスペリエンスがあります。

##### テナント間同期を使用すると、Microsoft 365でユーザー検索シナリオを有効にできますか?

はい。テナント間同期では、Microsoft 365 でユーザーの検索を有効にすることができます。 `showInAddressList`属性がターゲット テナントのユーザーに対して`True`に設定されていることを確認します。 `showInAddressList`属性は、テナント間同期の`True`で既定でに設定されます。

テナント間同期では、B2B コラボレーション ユーザーが作成され、連絡先は作成されません。

#### チーム

##### テナント間同期によって、現在の Teams エクスペリエンスが強化されますか?

同期されたユーザーには、他の B2B コラボレーション ユーザーが使用できるのと同じテナント間Microsoft 365 エクスペリエンスがあります。

#### 統合

##### ターゲット テナント内のユーザーがソース テナントに戻るためにサポートされているフェデレーション オプションは何ですか?

テナント間同期によって、ソース テナント内の内部ユーザーごとにフェデレーション外部ユーザー (B2B でよく使用される) がターゲットに作成されます。

テナント間同期では、内部ユーザーの同期がサポートされます。 このサポートには、ドメイン フェデレーション (Active Directory フェデレーション サービス (AD FS)が含まれます。 テナント間同期では、外部ユーザーの同期はサポートされていません。

##### テナント間同期では SCIM が使用されますか?

その必要はありません。 現在、Microsoft Entra IDは、SCIM サーバーではなく、クロスドメイン ID 管理 (SCIM) クライアントのシステムをサポートしています。 詳細については、「[Microsoft Entra ID との SCIM 同期](https://learn.microsoft.com/ja-jp/entra/architecture/sync-scim)」を参照してください。

#### Deprovisioning

##### テナント間同期では、ユーザーのプロビジョニング解除はサポートされていますか?

はい。 ソース テナントで次のアクションが発生すると、ユーザーはターゲット テナントで [論理的に削除されます](https://learn.microsoft.com/ja-jp/entra/architecture/recover-from-deletions#soft-deletions) 。

- ソース テナント内のユーザーを削除します。
- テナント間同期構成からユーザーの割り当てを解除します。
- テナント間同期構成に割り当てられているグループからユーザーを削除します。
- テナント間同期構成で定義されているスコープ フィルター条件を満たさないように、ユーザーの属性が変更されます。

ユーザーがソース テナント (`accountEnabled = false`) でのサインインをブロックされている場合、ユーザーはターゲットへのサインインをブロックされます。 このアクションは削除ではなく、 `accountEnabled` プロパティの更新です。

このシナリオでは、ユーザーはターゲット テナントから論理的に削除されません。

1. ユーザーをグループに追加し、ソース テナントのテナント間同期構成に割り当てます。
2. ユーザーをオンデマンドでプロビジョニングするか、増分サイクルを通じてプロビジョニングします。
3. ユーザーの`accountEnabled`の状態をソース テナントで`false`に更新します。
4. ユーザーをオンデマンドでプロビジョニングするか、増分サイクルを通じてプロビジョニングします。 `accountEnabled`の状態は、ターゲット テナントの`false`に変わります。
5. ソース テナントのグループからユーザーを削除します。

##### テナント間同期はユーザーの復元をサポートしていますか?

はい。 ソース テナント内のユーザーが復元され、アプリに再割り当てされ、論理的な削除から 30 日以内に再度スコープ条件を満たしている場合、ユーザーはターゲット テナントに復元されます。

IT 管理者は、ターゲット テナントでユーザーを手動で直接 [復元](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-restore) することもできます。

##### 現在テナント間同期のスコープ内にあるすべてのユーザーをプロビジョニング解除するにはどうすればよいですか?

テナント間同期構成からすべてのユーザーとグループの割り当てを解除します。 このアクションにより、割り当てられていないすべてのユーザーが、直接またはグループ メンバーシップを通じて、後続の同期サイクルでプロビジョニング解除されます。 ターゲット テナントは、プロビジョニング解除が完了するまで同期の受信ポリシーを有効にしておく必要があります。

スコープが [ **すべてのユーザーの同期**] に設定されている場合は、割 **り当てられたユーザーとグループのみを同期**するように変更する必要があります。 テナント間同期では、ユーザーが自動的に論理的に削除されます。 ユーザーは 30 日後に自動的にハード削除されるか、ターゲット テナントから直接ユーザーをハード削除することを選択できます。

##### 同期関係が切断された場合、以前にテナント間同期によって管理されていた外部ユーザーはターゲット テナントで削除されますか?

その必要はありません。 関係が切断された場合 (たとえば、テナント間同期ポリシーが削除された場合)、テナント間同期によって以前に管理されていた外部ユーザーは変更されません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/multi-tenant-organizations/cross-tenant-synchronization-topology"} -->
## テナント間同期のトポロジ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-topology
- Service: entra-id / multitenant-organizations
- Article date: 2026-03-18
- Summary: Microsoft Entra ID でのテナント間同期のトポロジについて確認してください。

### 概要

組織では多くの場合、合併と買収、規制要件、または管理上の境界により、複数のテナントを管理する必要があります。 どのようなシナリオであっても、Microsoft Entra は、テナント間でアカウントをプロビジョニングし、シームレスなコラボレーションを促進するための柔軟ですぐに使用可能なソリューションを提供します。 Microsoft Entra は次の 3 つのモデルに対応しており、組織の進化するニーズに適応できます。

- ハブ アンド スポーク
- メッシュ型
- Just-In-Time

### ハブ アンド スポーク

ハブ アンド スポーク トポロジには、次の 2 つの一般的なパターンがあります。

- **オプション 1 (アプリケーション ハブ):** このオプションでは、一般的に使用されるアプリケーションを、組織全体のユーザーがアクセスできる中央ハブ テナントに統合できます。
- **オプション 2 (ユーザー ハブ):** 一方、オプション 2 では、すべてのユーザーを 1 つのテナント内で一元管理し、リソースが管理されるスポーク テナントにユーザーをプロビジョニングします。

実際のシナリオをいくつか調べ、それらがこれらの各モデルとどのように一致するかを見てみましょう。

#### 合併と買収 (アプリケーション ハブ)

合併と買収では、すぐにコラボレーション可能にする機能がとても重要であり、それにより、IT で複雑な意思決定が行われている間も企業間で連携して機能することができます。 たとえば、新しく買収された会社の従業員が、社内ヘルプ デスクアプリ チケット システムなどのアプリケーションやベネフィット アプリケーションにすぐにアクセスする必要がある場合、テナント間同期が非常に重要です。 この同期プロセスにより、買収された企業のユーザーを初日からアプリケーション ハブにプロビジョニングし、SaaS アプリ、オンプレミスのアプリケーション、その他のクラウド リソースへのアクセスを許可できます。 ターゲット テナント内で、管理者はアクセス パッケージを設定して、ビジネス クリティカルなデータを含む Salesforce や Amazon Web Services などの追加アプリケーションへの時間制限付きアクセスを許可できます。 次の図は、最近買収したテナント (左側) とそのユーザーを示しています。ユーザーは、親会社のテナントにプロビジョニングされ、必要なリソースへのアクセスが許可されます。

[Image: 1 つのターゲット テナントと同期している複数のソース テナントを示す図。]

### コラボレーションとリソースのテナントを分離する (ユーザー ハブ)

組織が Azure の使用を拡大するにつれて、多くの場合、重要な Azure リソースを管理するための専用テナントが作成されます。 一方、ユーザー プロビジョニングは中央のハブ テナントに依存します。 このモデルにより、ハブ テナントの管理者は中央のセキュリティとガバナンスのポリシーを確立できるようになり、開発チームでは必要な Azure リソースをデプロイするための自律性と機敏性が向上します。 テナント間同期では、管理者がユーザーのサブセットをスポーク テナントにプロビジョニングし、それらのユーザーのライフサイクルを管理できるようにすることで、このトポロジがサポートされます。

[Image: 複数のターゲット テナントと同期しているソース テナントを示す図。]

### メッシュ型

単一のテナント内でユーザーを一元管理している企業もあれば、アプリケーション、HR システム、Active Directory ドメインが各テナントに統合された分散構造を採用している企業もあります。 テナント間同期では、各テナントにプロビジョニングするユーザーを柔軟に選択できます。

#### ポートフォリオ企業内でのコラボレーション (部分メッシュ)

このシナリオでは、各テナントは、同一の親組織内の異なる企業を表しています。 各テナントの管理者は、ターゲット テナントにプロビジョニングするユーザーのサブセットを選択します。 このソリューションは、ユーザーが重要なリソースにアクセスする必要がある場合にコラボレーションを促進しながら、各テナントが個別に動作するための柔軟性を提供します。

[Image: 複数のテナントと同期する部分メッシュ トポロジを示す図。]

テナント間同期は一方向です。 内部メンバー ユーザーは、外部ユーザーとして複数のテナントに同期できます。 トポロジが双方向で同期が行われていることを示している場合、それは各方向の個別のユーザー セットであり、各矢印は個別の構成です。

#### 事業単位間でのコラボレーション (フルメッシュ)

このシナリオでは、組織は事業単位ごとに異なるテナントを指定します。 事業単位は、特に Microsoft Teams を使用して密接に連携します。 その結果、各テナントは、組織内の 4 つのテナント全体ですべてのユーザーをプロビジョニングすることを選択しました。 新しいユーザーが入社したり退職したりすると、プロビジョニング サービスによってユーザーの作成と削除が行われます。 組織では、4 つのテナントすべてを含むマルチテナント組織も構成されています。 ユーザーが Teams でコラボレーションを行う必要があるときは、会社全体でユーザーを簡単に見つけて、それらのユーザーとのチャットや会議を開始できます。

[Image: 複数のテナントと同期するフルメッシュ トポロジを示す図。]

### Just-In-Time

これまで説明したシナリオは組織内のコラボレーションを対象としていますが、組織間のコラボレーションが不可欠な場合もあります。 これは、合弁事業や独立した法人の組織の場合に該当する可能性があります。 接続された組織とエンタイトルメント管理を採用すると、接続された組織間でリソースにアクセスするためのポリシーを定義し、ユーザーが必要なリソースへのアクセスを要求可能にできます。

#### 合弁事業

複数年にわたる合弁事業に従事する別個の組織である Contoso と Litware について考えてみましょう。 両社は、緊密にコラボレーションを行う必要があります。 Contoso の管理者は、Litware ユーザーが必要とするリソースを含むアクセス パッケージを定義しています。 Litware の新入社員が Contoso のリソースにアクセスする必要がある場合、このアクセス パッケージへのアクセスを要求できます。 承認されると、その社員が必要なリソースを使用してプロビジョニングされます。 アクセスは、時間制限が設けられたり、Contoso のガバナンス要件に準拠するために定期的に見直されたりする場合があります。

次の図は、2 つの組織が、接続された組織とエンタイトルメント管理を使用して Just-In-Time でコラボレーションを行う方法を示しています。

[Image: 接続された組織とエンタイトルメント管理を使用して Just-In-Time によるコラボレーションを示す図。]

### サポートされるシナリオ

テナント間同期では、ソース テナントに[内部ユーザー](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)をインポートし、ターゲット テナントで[外部ユーザー](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)をプロビジョニングできます。

| ソース テナントの資格情報 | ソース テナントの userType | ターゲット テナントの資格情報 | ターゲット テナントの userType | サポートされるシナリオ |
| --- | --- | --- | --- | --- |
| 内部 | メンバー | 外部 | メンバー | あり |
| 内部 | メンバー | 外部 | ゲスト | あり |
| 内部 | ゲスト | 外部 | メンバー | あり |
| 内部 | ゲスト | 外部 | ゲスト | あり |
| 内部 | メンバー | 内部 | メンバー | いいえ |
| 内部 | メンバー | 内部 | ゲスト | いいえ |
| 内部 | ゲスト | 内部 | メンバー | いいえ |
| 内部 | ゲスト | 内部 | ゲスト | いいえ |
| 外部 | メンバー | 外部 | メンバー | いいえ |
| 外部 | メンバー | 外部 | ゲスト | いいえ |
| 外部 | ゲスト | 外部 | メンバー | いいえ |
| 外部 | ゲスト | 外部 | ゲスト | いいえ |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/multi-tenant-organizations/defender-xdr-microsoft-entra-mto"} -->
## Microsoft Defender for Cloud XDR と Microsoft Entra ID Governance を使用して、マルチテナント組織でのセキュリティ オペレーション センター (SOC) によるアクセスをセキュリティで保護し、管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/defender-xdr-microsoft-entra-mto
- Service: entra-id / multitenant-organizations
- Article date: 2026-03-18
- Summary: セキュリティ オペレーション アナリストがテナント間でリソースにアクセスできるようにする方法について説明します。

### 概要

企業が直面するセキュリティの脅威は絶え間なく進化しており、それらに対応するとなると、マルチテナント環境の管理はさらに複雑になる可能性があります。 複数のテナント間を移動すると時間がかかり、セキュリティ オペレーション センター (SOC) チームの全体的な効率が低下するおそれがあります。 [Microsoft Defender XDR](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender/mto-overview) のマルチテナント管理により、セキュリティ オペレーション チームは、管理するすべてのテナントを単一の統合ビューで確認できます。 このビューを使用すると、チームはインシデントをすばやく調査し、複数のテナントからのデータに対して高度なハンティングを実行して、セキュリティ オペレーションを向上できます。

[Microsoft Entra ID Governance](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview) を使用すると、SOC チームおよび脅威ハンター チームのメンバーであるユーザーのアクセスとライフサイクルを管理できます。 このドキュメントでは、次の内容について説明します。

- SOC チームがテナント間でリソースに安全にアクセスできるようにするために導入できる制御機能。
- ライフサイクルとアクセスの制御を実装する方法を示すトポロジ例。
- デプロイに関する考慮事項 (ロール、監視、API)。

### SOC ユーザーのライフサイクルとアクセスを管理する

Microsoft Entra には、SOC ユーザーのライフサイクルを管理するため、および必要とするリソースへのアクセスを安全に提供するために必要な制御が用意されています。 このドキュメントでは、ソース テナントという用語は、SOC ユーザーが発生し、認証される場所を指します。 ターゲット テナントとは、インシデント時に調査対象となるテナントを指します。 組織には、合併や買収、事業単位に合わせたテナントの調整、地域に合わせたテナントの調整が行われることにより、複数のターゲット テナントが存在します。

#### ライフサイクル制御

**エンタイトルメント管理では、アクセス パッケージおよび接続されている組織を作成することにより**、ターゲット テナント管理者は、ソース テナントのユーザーがアクセスを要求できるリソース (例: アプリ ロール、ディレクトリ ロール、グループ) のコレクションを定義できます。 ユーザーが、必要なリソースの承認を得ても、B2B アカウントをまだ持っていない場合、エンタイトルメント管理によって、そのユーザーの B2B アカウントがターゲット テナント内に自動的に作成されます。 ユーザーのエンタイトルメントがターゲット テナント内に残っていない場合、そのユーザーの B2B アカウントは自動的に削除されます。 エンタイトルメント管理は、組織内と組織全体の両方で使用できます。

詳細については、「 [エンタイトルメント管理と接続された組織](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-organization)」を参照してください。

**テナント間同期**を使用すると、ソース テナントで、組織内のテナント全体での B2B ユーザーの作成、更新、削除を自動化できます。

詳細については、 [テナント間の同期に関するページを](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)参照してください。

**エンタイトルメント管理とテナント間同期の比較**

| 能力 | エンタイトルメント管理 | テナント間同期 |
| --- | --- | --- |
| ターゲット テナントでユーザーを作成する | - | - |
| ソース テナントでユーザーの属性が変更されたときに、ターゲット テナントでそのユーザーを更新する |  | - |
| ユーザーを削除する | - | - |
| グループ、ディレクトリ ロール、アプリ ロールにユーザーを割り当てる | - |  |
| ターゲット テナント内のユーザーの属性 | 最小限、要求時にユーザー自身によって提供される | ソース テナントから同期される |

#### アクセス制御

エンタイトルメント管理とテナント間アクセス ポリシーを使用して、テナント間のリソースへのアクセスを制御できます。 エンタイトルメント管理では適切なユーザーが適切なリソースに割り当てられますが、テナント間アクセス ポリシーと条件付きアクセスは、適切なユーザーが適切なリソースにアクセスしていることを確認するために必要な実行時チェックを一緒に実行します。

**エンタイトルメント管理**

エンタイトルメント管理のアクセス パッケージを使用して Microsoft Entra ロールを割り当てると、大規模なロールの割り当てを効率的に管理し、ロール割り当てライフサイクルを改善するのに役立ちます。 ディレクトリ ロール、アプリ ロール、グループへのアクセスを取得するための柔軟な要求および承認プロセスが提供されると同時に、ユーザー属性に基づいたリソースへの自動割り当ても可能になります。

詳細については、 [エンタイトルメント管理の概要](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)を参照してください。

**テナント間アクセス ポリシー**

外部 ID のテナント間アクセス設定を使用して、B2B コラボレーションを介して他の Microsoft Entra 組織とのコラボレーションを行う方法を管理します。 これらの設定によって、お客様のリソースに対して外部 Microsoft Entra 組織の受信アクセス ユーザーが持つレベルと、お客様のユーザーが外部組織に対して持つ送信アクセスのレベルの両方が決まります。

詳細については、 [テナント間アクセスの概要に関する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)ページを参照してください。

### 展開トポロジ

このセクションでは、テナント間同期、エンタイトルメント管理、テナント間アクセス ポリシー、条件付きアクセスなどのツールを一緒に使用する方法について説明します。 どちらのトポロジでも、ターゲット テナント管理者は、ターゲット テナント内のリソースへのアクセスを完全に制御できます。 プロビジョニングとプロビジョニング解除の開始者が異なります。

#### トポロジ 1

トポロジ 1 では、ソース テナントでエンタイトルメント管理とテナント間同期を構成して、ユーザーをターゲット テナントにプロビジョニングします。 その後、ターゲット テナントの管理者は、ターゲット テナント内の必要なディレクトリ ロール、グループ、アプリ ロールへのアクセスを提供するアクセス パッケージを構成します。

[Image: トポロジ 1 を示す図。ユーザーは、テナント間同期によってテナント間でプッシュされ、エンタイトルメント管理によってロールへのアクセス権が付与されます。]

**トポロジ 1 を構成する手順**

1. ソース テナントで、ソース テナントの内部アカウントをターゲット テナントの外部アカウントとしてプロビジョニングするように[テナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)を構成します。

    ユーザーは、テナント間同期のサービス プリンシパルに割り当てられると、ターゲット テナントに自動的にプロビジョニングされます。 ユーザーは、構成から削除されると、自動的にプロビジョニング解除されます。 属性マッピングの一環として、定数型の新しいマッピングを追加し、ユーザーに[ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-directory-extensions)をプロビジョニングして、ユーザーが SOC 管理者であることを示すことができます。 または、この手順で使用できる属性 (部署など) がある場合は、拡張機能の作成をスキップできます。 この属性は、必要なロールへのアクセスをユーザーに提供するためにターゲット テナントで使用されます。
2. ソース テナントで、テナント間同期のサービス プリンシパルをリソースとして含むアクセス パッケージを作成します。

    ユーザーは、パッケージへのアクセス権が付与されると、テナント間同期のサービス プリンシパルに割り当てられます。 必ずアクセス パッケージの定期的なアクセス レビューを設定するか、または割り当て時間を制限して、ターゲット テナントにアクセスする必要があるユーザーのみが引き続きアクセスできるようにします。
3. ターゲット テナントで、インシデントの調査に必要なロールを提供する[アクセス パッケージを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create)します。

    セキュリティ閲覧者ロールを提供するには、1 つの [自動割り当て](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy) アクセス パッケージと、セキュリティ オペレーターロールとセキュリティ管理者ロールに対して 1 つの要求ベースのパッケージを提供することをお勧めします。

セットアップが完了したら、SOC ユーザーは myaccess.microsoft.com に移動して、ソース テナント内の必要なアクセス パッケージへの時間制限付きアクセスを要求できます。 承認後、セキュリティ閲覧者ロールを持つターゲット テナントに自動的にプロビジョニングされます。 その後、ユーザーは、セキュリティ オペレーターまたはセキュリティ管理者のロールを必要とする任意のテナントで追加のアクセスを要求できます。 アクセス期間が過ぎているか、アクセス レビューの一部として削除されると、アクセスが不要なすべてのターゲット テナントからプロビジョニングが解除されます。

#### トポロジ 2

トポロジ 2 では、ターゲット テナント管理者が、ソース ユーザーがアクセスを要求できるアクセス パッケージとリソースを定義します。 ソース テナント管理者がターゲット テナントにアクセスできるユーザーを制限したい場合、テナント間アクセス ポリシーとアクセス パッケージを組み合わせて使用し、ホーム テナントのアクセス パッケージに含まれるグループのメンバーであるユーザーを除いて、ターゲット テナントへのすべてのアクセスをブロックできます。

トポロジ 2 を示す図。特権が接続された組織を利用することで、ユーザーはターゲットテナントへのアクセスを要求でき、ターゲットテナント内の必要なロールにプロビジョニングされることが可能になります。

**トポロジ 2 を構成する手順**

1. ターゲット テナントで、ソース テナントを、[接続されている組織](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-organization)として追加します。

    この設定により、ターゲット テナント管理者は、ソース テナントがアクセス パッケージを使用できるようにすることができます。
2. ターゲット テナントで、セキュリティ閲覧者、セキュリティ管理者、セキュリティ オペレーターのロールを提供するアクセス パッケージを作成します。
3. これで、ソース テナントのユーザーは、ターゲット テナントのアクセス パッケージを要求できるようになりました。

セットアップが完了したら、SOC ユーザーは myaccess.microsoft.com に移動して、各テナントの必要なロールへの時間制限付きアクセスを要求できます。

**トポロジの比較**

どちらのトポロジでも、ユーザーがアクセスするリソースをターゲット テナントで制御できます。 これは、テナント間アクセス ポリシー、条件付きアクセス、アプリとロールのユーザーへの割り当てを組み合わせて使用して実現できます。 プロビジョニングを構成して開始する人が異なります。 トポロジ 1 では、ソース テナントでプロビジョニングを構成し、ユーザーをターゲット テナントにプッシュします。 トポロジ 2 では、ターゲット テナントで、そのテナントにアクセスする資格があるユーザーを定義します。

ユーザーが一度に複数のテナントにアクセスする必要がある場合、トポロジ 1 を使用すると、ユーザーは、1 つのテナントのアクセス パッケージへのアクセスを簡単に要求でき、複数のテナントに自動的にプロビジョニングされます。 ターゲット テナントで、そのテナントにプロビジョニングされるユーザーを完全に制御し、そのテナントで必要な承認を実行する必要がある場合、トポロジ 2 は、これらのニーズを満たすのに最適です。

### デプロイに関する考慮事項

**監視**

Microsoft Entra で SOC アナリストによって実行されたアクションは、アナリストが作業している Microsoft Entra テナントで監査されます。 組織は、実行されたアクションの監査証跡を保持することができ、特定のアクションが実行されたときにアラートを生成できます。また、監査ログを Azure Monitor にプッシュして、実行されたアクションを分析することもできます。

詳細については、 [アクティビティ ログと Azure Monitor ログの統合](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)に関するページを参照してください。

Microsoft Defender for Cloud で SOC アナリストによって実行されたアクションも監査されます。

詳細については、 [監査ログアクティビティ](https://learn.microsoft.com/ja-jp/purview/audit-log-activities)を参照してください。

**PowerShell または API を使用したデプロイのスケーリング**

Microsoft Entra のユーザー インターフェイスを使用して構成されるすべての手順には、Microsoft Graph API と PowerShell コマンドレットが付属しているため、組織内のテナント全体に目的のポリシーと構成を展開できます。

| 能力 | Microsoft Graph API | PowerShell |
| --- | --- | --- |
| テナント間同期 | [リンク](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure-graph?tabs=ms-graph) | [リンク](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure-graph?tabs=ms-powershell) |
| エンタイトルメント管理 | [リンク](https://learn.microsoft.com/ja-jp/graph/tutorial-access-package-api) | [リンク](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/tutorial-entitlement-management) |
| テナント間アクセス ポリシー | [リンク](https://learn.microsoft.com/ja-jp/graph/api/resources/crosstenantaccesspolicy-overview) | [リンク](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/new-mgpolicycrosstenantaccesspolicypartner) |

**ロールベースのアクセス制御**

トポロジ 1 とトポロジ 2 で説明した機能を構成するには、次のロールが必要です。

- テナント間アクセス設定の構成 - セキュリティ管理者
- テナント間同期の構成 - ハイブリッド ID 管理者
- 特権管理の構成 - ID ガバナンス アドミニストレーター
- [Microsoft Defender](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender/m365d-permissions) では、セキュリティ閲覧者、セキュリティ管理者、セキュリティ オペレーターなどの組み込みロールとカスタム ロールの両方がサポートされています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/multi-tenant-organizations/multi-tenant-organization-configure-graph"} -->
## PowerShell または Microsoft Graph API を使用してマルチテナント組織を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-configure-graph
- Service: entra-id / multitenant-organizations
- Article date: 2026-03-25
- Summary: Microsoft Graph PowerShell または Microsoft Graph API を使用してマルチテナント組織を作成および管理します。 組織の作成、テナントの追加、参加、ロールの管理について説明します。

### 概要

この記事では、Microsoft Graph PowerShell または Microsoft Graph API を使用してマルチテナント組織を構成するうえで重要な手順について説明します。 この記事では、*Cairo* という名前の所有者テナントと *Berlin* と *Athens* という名前の 2 つのメンバー テナントの例を使用します。

代わりに、Microsoft 365 管理センターを使用してマルチテナント組織を構成する場合は、「[Microsoft 365 でマルチテナント組織を設定する](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/set-up-multi-tenant-org)」および「[Microsoft 365 でマルチテナント組織に参加または脱退する](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/join-leave-multi-tenant-org)」を参照してください。 マルチテナント組織用に Microsoft Teams を構成する方法については、[Microsoft Teams デスクトップ クライアント](https://learn.microsoft.com/ja-jp/microsoftteams/new-teams-desktop-admin)に関する記事を参照してください。

[Image: 1 つの所有者テナントと 2 つのメンバー テナントを持つマルチテナント組織を示す図。]

### 前提条件

[Image: 所有者テナントのアイコン。]**所有者テナント**

- ライセンス情報については、「[ライセンスの要件](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-overview#license-requirements)」を参照してください。
- マルチテナント組織のテナント間アクセス設定とテンプレートを構成するための[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)ロール。
- 必要なアクセス許可に同意するための[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)ロール。

[Image: メンバー テナントのアイコン。]**メンバー テナント**

- ライセンス情報については、「[ライセンスの要件](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-overview#license-requirements)」を参照してください。
- マルチテナント組織のテナント間アクセス設定とテンプレートを構成するための[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)ロール。
- 必要なアクセス許可に同意するための[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)ロール。

### 手順 1: 所有者テナントにサインインする

[Image: 所有者テナントのアイコン。]**所有者テナント**

## [PowerShell](#tab/ms-powershell)
1. PowerShell を開始します。
2. 必要に応じて、[Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) をインストールします。
3. 所有者とメンバーのテナントのテナント ID を取得し、変数を初期化します。

    ```powershell
    $OwnerTenantId = "<OwnerTenantId>"
    $MemberTenantIdB = "<MemberTenantIdB>"
    $MemberTenantIdA = "<MemberTenantIdA>"
    ```
4. [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) コマンドを使用して所有者テナントにサインインし、必要な次のアクセス許可に同意します。

    - `MultiTenantOrganization.ReadWrite.All`
    - `Policy.Read.All`
    - `Policy.ReadWrite.CrossTenantAccess`
    - `Application.ReadWrite.All`
    - `Directory.ReadWrite.All`

    ```powershell
    Connect-MgGraph -TenantId $OwnerTenantId -Scopes "MultiTenantOrganization.ReadWrite.All","Policy.Read.All","Policy.ReadWrite.CrossTenantAccess","Application.ReadWrite.All","Directory.ReadWrite.All"
    ```

## [Microsoft Graph](#tab/ms-graph)
以下の手順で Microsoft Graph Explorer を使用する方法について説明しますが、他の REST API クライアントを使用することもできます。

1. [Microsoft Graph Explorer ツール](https://aka.ms/ge)を起動します。
2. 所有者テナントにサインインします。
3. プロファイルを選択し、**[Consent to permissions] (アクセス許可に同意する)** を選択します。
4. 次の必要なアクセス許可に同意します。

    - `MultiTenantOrganization.ReadWrite.All`
    - `Policy.Read.All`
    - `Policy.ReadWrite.CrossTenantAccess`
    - `Application.ReadWrite.All`
    - `Directory.ReadWrite.All`

---

### 手順 2: マルチテナント組織を作成する

[Image: 所有者テナントのアイコン。]**所有者テナント**

## [PowerShell](#tab/ms-powershell)
1. 所有者テナントで、 [Update-MgTenantRelationshipMultiTenantOrganization](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/update-mgtenantrelationshipmultitenantorganization) コマンドを使用してマルチテナント組織を作成します。 この操作には数分かかります。

    ```powershell
    Update-MgTenantRelationshipMultiTenantOrganization -DisplayName "Cairo"
    ```
2. 続行する前に操作が完了したことを確認するには、 [Get-MgTenantRelationshipMultiTenantOrganization](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mgtenantrelationshipmultitenantorganization) コマンドを使用します。

    ```powershell
    Get-MgTenantRelationshipMultiTenantOrganization | Format-List
    ```

    ```Output
    CreatedDateTime      : 1/8/2024 7:47:45 PM
    Description          :
    DisplayName          : Cairo
    Id                   : <MtoIdC>
    JoinRequest          : Microsoft.Graph.PowerShell.Models.MicrosoftGraphMultiTenantOrganizationJoinRequestRecord
    State                : active
    Tenants              :
    AdditionalProperties : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#tenantRelationships/multiTenantOrganization/$entity]}
    ```

## [Microsoft Graph](#tab/ms-graph)
1. 所有者テナントで、[Create multiTenantOrganization](https://learn.microsoft.com/ja-jp/graph/api/tenantrelationship-put-multitenantorganization) API を使用してマルチテナント組織を作成します。 この操作には数分かかります。

    **依頼**

    ```http
    PUT https://graph.microsoft.com/v1.0/tenantRelationships/multiTenantOrganization
    {
        "displayName": "Cairo"
    }
    ```
2. 続行する前に操作が完了したことをチェックするには、[Get multiTenantOrganization](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganization-get) API を使用します。

    **依頼**

    ```http
    GET https://graph.microsoft.com/v1.0/tenantRelationships/multiTenantOrganization
    ```

    **応答**

    ```http
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#tenantRelationships/multiTenantOrganization/$entity",
        "id": "{mtoId}",
        "createdDateTime": "2023-11-20T20:38:20Z",
        "state": "active",
        "displayName": "Cairo",
        "description": null
    }
    ```

---

### 手順 3: テナントを追加する

[Image: 所有者テナントのアイコン。]**所有者テナント**

## [PowerShell](#tab/ms-powershell)
1. 所有者テナントで、 [New-MgTenantRelationshipMultiTenantOrganizationTenant](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/new-mgtenantrelationshipmultitenantorganizationtenant) コマンドを使用して、マルチテナント組織にテナントを追加します。

    ```powershell
    New-MgTenantRelationshipMultiTenantOrganizationTenant -TenantID $MemberTenantIdB -DisplayName "Berlin" | Format-List
    ```

    ```powershell
    New-MgTenantRelationshipMultiTenantOrganizationTenant -TenantID $MemberTenantIdA -DisplayName "Athens" | Format-List
    ```
2. 続行する前に操作が完了したことを確認するには、[Get-MgTenantRelationshipMultiTenantOrganizationTenant](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mgtenantrelationshipmultitenantorganizationtenant) コマンドを使用します。

    ```powershell
    Get-MgTenantRelationshipMultiTenantOrganizationTenant | Format-List
    ```

    ```Output
    AddedByTenantId      : <OwnerTenantId>
    AddedDateTime        : 1/8/2024 7:47:45 PM
    DeletedDateTime      :
    DisplayName          : Cairo
    Id                   : <MtoIdC>
    JoinedDateTime       :
    Role                 : owner
    State                : active
    TenantId             : <OwnerTenantId>
    TransitionDetails    : Microsoft.Graph.PowerShell.Models.MicrosoftGraphMultiTenantOrganizationMemberTransitionDetails
    AdditionalProperties : {[multiTenantOrgLabelType, none]}
    
    AddedByTenantId      : <OwnerTenantId>
    AddedDateTime        : 1/8/2024 8:05:25 PM
    DeletedDateTime      :
    DisplayName          : Berlin
    Id                   : <MtoIdB>
    JoinedDateTime       :
    Role                 : member
    State                : pending
    TenantId             : <MemberTenantIdB>
    TransitionDetails    : Microsoft.Graph.PowerShell.Models.MicrosoftGraphMultiTenantOrganizationMemberTransitionDetails
    AdditionalProperties : {[multiTenantOrgLabelType, none]}
    
    AddedByTenantId      : <OwnerTenantId>
    AddedDateTime        : 1/8/2024 8:08:47 PM
    DeletedDateTime      :
    DisplayName          : Athens
    Id                   : <MtoIdA>
    JoinedDateTime       :
    Role                 : member
    State                : pending
    TenantId             : <MemberTenantIdA>
    TransitionDetails    : Microsoft.Graph.PowerShell.Models.MicrosoftGraphMultiTenantOrganizationMemberTransitionDetails
    AdditionalProperties : {[multiTenantOrgLabelType, none]}
    ```

## [Microsoft Graph](#tab/ms-graph)
1. 所有者テナントで、[Add multiTenantOrganizationMember](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganization-post-tenants) API を使用して、マルチテナント組織にテナントを追加します。

    **依頼**

    ```http
    POST https://graph.microsoft.com/v1.0/tenantRelationships/multiTenantOrganization/tenants
    {
        "tenantId": "{memberTenantIdB}",
        "displayName": "Berlin"
    }
    ```

    **依頼**

    ```http
    POST https://graph.microsoft.com/v1.0/tenantRelationships/multiTenantOrganization/tenants
    {
        "tenantId": "{memberTenantIdA}",
        "displayName": "Athens"
    }
    ```
2. 続行する前に操作が完了したことを確認するには、[List multiTenantOrganizationMembers](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganization-list-tenants) API を使用します。

    **依頼**

    ```http
    GET https://graph.microsoft.com/v1.0/tenantRelationships/multiTenantOrganization/tenants
    ```

    **応答**

    ```http
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#tenantRelationships/multiTenantOrganization/tenants"
        "value": [
            {
                "tenantId": "{ownerTenantId}",
                "displayName": "Cairo",
                "addedDateTime": "2023-11-20T20:38:20Z",
                "joinedDateTime": null,
                "addedByTenantId": "{ownerTenantId}",
                "role": "owner",
                "state": "active",
                "transitionDetails": null
            },
            {
                "tenantId": "{memberTenantIdB}",
                "displayName": "Berlin",
                "addedDateTime": "2023-11-20T21:22:35Z",
                "joinedDateTime": null,
                "addedByTenantId": "{ownerTenantId}",
                "role": "member",
                "state": "pending",
                "transitionDetails": {
                    "desiredState": "active",
                    "desiredRole": "member",
                    "status": "notStarted",
                    "details": null
                }
            },
            {
                "tenantId": "{memberTenantIdA}",
                "displayName": "Athens",
                "addedDateTime": "2023-11-20T21:24:59Z",
                "joinedDateTime": null,
                "addedByTenantId": "{ownerTenantId}",
                "role": "member",
                "state": "pending",
                "transitionDetails": {
                    "desiredState": "active",
                    "desiredRole": "member",
                    "status": "notStarted",
                    "details": null
                }
            }
        ]
    }
    ```

---

### 手順 4: (省略可能) テナントのロールを変更する

[Image: 所有者テナントのアイコン。]**所有者テナント**

既定では、マルチテナント組織に追加されたテナントはメンバー テナントです。 必要に応じて、所有者テナントに変更できます。これにより、マルチテナント組織に他のテナントを追加できます。 所有者テナントをメンバー テナントに変更することもできます。

## [PowerShell](#tab/ms-powershell)
1. 所有者テナントで、 [Update-MgTenantRelationshipMultiTenantOrganizationTenant](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/update-mgtenantrelationshipmultitenantorganizationtenant) コマンドを使用して、メンバー テナントを所有者テナントに変更します。

    ```powershell
    Update-MgTenantRelationshipMultiTenantOrganizationTenant -MultiTenantOrganizationMemberId $MemberTenantIdB -Role "Owner" | Format-List
    ```
2. [Get-MgTenantRelationshipMultiTenantOrganizationTenant](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mgtenantrelationshipmultitenantorganizationtenant) コマンドを使用して、変更を確認します。

    ```powershell
    Get-MgTenantRelationshipMultiTenantOrganizationTenant -MultiTenantOrganizationMemberId $MemberTenantIdB | Format-List
    ```

    ```Output
    AddedByTenantId      : <OwnerTenantId>
    AddedDateTime        : 1/8/2024 8:05:25 PM
    DeletedDateTime      :
    DisplayName          : Berlin
    Id                   : <MtoIdB>
    JoinedDateTime       :
    Role                 : owner
    State                : pending
    TenantId             : <MemberTenantIdB>
    TransitionDetails    : Microsoft.Graph.PowerShell.Models.MicrosoftGraphMultiTenantOrganizationMemberTransitionDetails
    AdditionalProperties : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#tenantRelationships/multiTenantOrganization/tenants/$entity],
                           [multiTenantOrgLabelType, none]}
    ```

## [Microsoft Graph](#tab/ms-graph)
1. 所有者テナントで、[Update multiTenantOrganizationMember](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganizationmember-update) API を使用して、メンバー テナントを所有者テナントに変更します。

    **依頼**

    ```http
    PATCH https://graph.microsoft.com/v1.0/tenantRelationships/multiTenantOrganization/tenants/{memberTenantIdB}
    {
        "role": "owner"
    }
    ```
2. [Get multiTenantOrganizationMember](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganizationmember-get) API を使用して、変更を確認します。

    **依頼**

    ```http
    GET https://graph.microsoft.com/v1.0/tenantRelationships/multiTenantOrganization/tenants/{memberTenantIdB}
    ```

    **応答**

    ```http
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#tenantRelationships/multiTenantOrganization/tenants/$entity",
        "tenantId": "{memberTenantIdB}",
        "displayName": "Berlin",
        "addedDateTime": "2023-11-20T21:22:35Z",
        "joinedDateTime": null,
        "addedByTenantId": "{ownerTenantId}",
        "role": "member",
        "state": "pending",
        "transitionDetails": {
            "desiredState": "active",
            "desiredRole": "owner",
            "status": "notStarted",
            "details": null
        } 
    }
    ```
3. [Update multiTenantOrganizationMember](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganizationmember-update) API を使用して、所有者テナントを所有者テナントに変更します。

    **依頼**

    ```http
    PATCH https://graph.microsoft.com/v1.0/tenantRelationships/multiTenantOrganization/tenants/{memberTenantIdB}
    {
        "role": "member"
    }
    ```

---

### 手順 5: (省略可能) メンバー テナントを削除する

[Image: 所有者テナントのアイコン。]**所有者テナント**

自身のメンバー テナントを含め、任意のメンバー テナントを削除できます。 所有者テナントを削除することはできません。 また、所有者からメンバーに変更された場合でも、元の作成者テナントを削除することはできません。

## [PowerShell](#tab/ms-powershell)
1. 所有者テナントで [Remove-MgTenantRelationshipMultiTenantOrganizationTenant](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/remove-mgtenantrelationshipmultitenantorganizationtenant) コマンドを使用して、任意のメンバー テナントを削除します。 この操作は、数分かかります。

    ```powershell
    Remove-MgTenantRelationshipMultiTenantOrganizationTenant -MultiTenantOrganizationMemberId <MemberTenantIdD>
    ```
2. [Get-MgTenantRelationshipMultiTenantOrganizationTenant](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mgtenantrelationshipmultitenantorganizationtenant) コマンドを使用して、変更を確認します。

    ```powershell
    Get-MgTenantRelationshipMultiTenantOrganizationTenant -MultiTenantOrganizationMemberId <MemberTenantIdD>
    ```

    削除コマンドが完了すると、出力は次のようになります。 これは予想されるエラー メッセージです。 これは、テナントがマルチテナント組織から削除されたことを示します。

    ```Output
    Get-MgTenantRelationshipMultiTenantOrganizationTenant_Get: Unable to read the company information from the directory.
    
    Status: 404 (NotFound)
    ErrorCode: Directory_ObjectNotFound
    Date: 2024-01-08T20:35:11
    
    ...
    ```

## [Microsoft Graph](#tab/ms-graph)
1. 所有者テナントで、[Remove multiTenantOrganizationMember](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganization-delete-tenants) API を使用して、メンバー テナントを削除します。 この操作は、数分かかります。

    **依頼**

    ```http
    DELETE https://graph.microsoft.com/v1.0/tenantRelationships/multiTenantOrganization/tenants/{memberTenantIdD}
    ```
2. [Get multiTenantOrganizationMember](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganizationmember-get) API を使用して、変更を確認します。

    **依頼**

    ```http
    GET https://graph.microsoft.com/v1.0/tenantRelationships/multiTenantOrganization/tenants/{memberTenantIdD}
    ```

    remove API を呼び出した直後にチェックすると、次と同様の応答が表示されます。

    **応答**

    ```http
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#tenantRelationships/multiTenantOrganization/tenants/$entity",
        "tenantId": "{memberTenantIdD}",
        "displayName": "Denver",
        "addedDateTime": "2023-11-20T20:56:05Z",
        "joinedDateTime": null,
        "addedByTenantId": "{ownerTenantId}",
        "role": "member",
        "state": "pending",
        "transitionDetails": {
            "desiredState": "removed",
            "desiredRole": "member",
            "status": "notStarted",
            "details": null
        }
    }
    ```

    削除操作が完了すると、応答は次のようになります。 これは予想されるエラー メッセージです。 これは、テナントがマルチテナント組織から削除されたことを示します。

    **応答**

    ```http
    {
        "error": {
            "code": "Directory_ObjectNotFound",
            "message": "Unable to read the company information from the directory.",
            "innerError": {
                "date": "2023-11-20T21:09:53",
                "request-id": "75216961-c21d-49ed-8c1f-2cfe51f920f1",
                "client-request-id": "0000aaaa-11bb-cccc-dd22-eeeeee333333"
            }
        }
    }
    ```

---

### 手順 6: メンバー テナントにサインインする

[Image: メンバー テナントのアイコン。]**メンバー テナント**

Cairo テナントはマルチテナント組織を作成し、Berlin テナントと Athens テナントを追加しました。 次の手順では、Berlin テナントにサインインし、Cairo によって作成されたマルチテナント組織に参加します。

## [PowerShell](#tab/ms-powershell)
1. PowerShell を開始します。
2. [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) コマンドを使用してメンバー テナントにサインインし、必要な次のアクセス許可に同意します。

    - `MultiTenantOrganization.ReadWrite.All`
    - `Policy.Read.All`
    - `Policy.ReadWrite.CrossTenantAccess`
    - `Application.ReadWrite.All`
    - `Directory.ReadWrite.All`

    ```powershell
    Connect-MgGraph -TenantId $MemberTenantIdB -Scopes "MultiTenantOrganization.ReadWrite.All","Policy.Read.All","Policy.ReadWrite.CrossTenantAccess","Application.ReadWrite.All","Directory.ReadWrite.All"
    ```

## [Microsoft Graph](#tab/ms-graph)
1. [Microsoft Graph Explorer ツール](https://aka.ms/ge)を起動します。
2. メンバー テナントにサインインします。
3. プロファイルを選択し、**[Consent to permissions] (アクセス許可に同意する)** を選択します。
4. 次の必要なアクセス許可に同意します。

    - `MultiTenantOrganization.ReadWrite.All`
    - `Policy.Read.All`
    - `Policy.ReadWrite.CrossTenantAccess`
    - `Application.ReadWrite.All`
    - `Directory.ReadWrite.All`

---

### 手順 7: マルチテナント組織に参加する

[Image: メンバー テナントのアイコン。]**メンバー テナント**

## [PowerShell](#tab/ms-powershell)
1. メンバー テナントで、 [Update-MgTenantRelationshipMultiTenantOrganizationJoinRequest](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/update-mgtenantrelationshipmultitenantorganizationjoinrequest) コマンドを使用してマルチテナント組織に参加します。

    ```powershell
    Update-MgTenantRelationshipMultiTenantOrganizationJoinRequest -AddedByTenantId $OwnerTenantId | Format-List
    ```
2. [Get-MgTenantRelationshipMultiTenantOrganizationJoinRequest](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mgtenantrelationshipmultitenantorganizationjoinrequest) コマンドを使用して、結合を確認します。

    ```powershell
    Get-MgTenantRelationshipMultiTenantOrganizationJoinRequest | Format-List
    ```

    ```Output
    AddedByTenantId      : <OwnerTenantId>
    Id                   : <MtoJoinRequestIdB>
    MemberState          : active
    Role                 : member
    TransitionDetails    : Microsoft.Graph.PowerShell.Models.MicrosoftGraphMultiTenantOrganizationJoinRequestTransitionDetails
    AdditionalProperties : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#tenantRelationships/multiTenantOrganization/joinRequest/$entity]}
    ```
3. [Get-MgTenantRelationshipMultiTenantOrganizationTenant](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mgtenantrelationshipmultitenantorganizationtenant) コマンドを使用して、マルチテナント組織自体を確認します。 参加操作が反映されている必要があります。

    ```powershell
    Get-MgTenantRelationshipMultiTenantOrganizationTenant | Format-List
    ```

    ```Output
    AddedByTenantId      : <OwnerTenantId>
    AddedDateTime        : 1/8/2024 8:05:25 PM
    DeletedDateTime      :
    DisplayName          : Berlin
    Id                   : <MtoJoinRequestIdB>
    JoinedDateTime       : 1/8/2024 9:53:55 PM
    Role                 : member
    State                : active
    TenantId             : <MemberTenantIdB>
    TransitionDetails    : Microsoft.Graph.PowerShell.Models.MicrosoftGraphMultiTenantOrganizationMemberTransitionDetails
    AdditionalProperties : {[multiTenantOrgLabelType, none]}
    
    AddedByTenantId      : <OwnerTenantId>
    AddedDateTime        : 1/8/2024 7:47:45 PM
    DeletedDateTime      :
    DisplayName          : Cairo
    Id                   : <Id>
    JoinedDateTime       :
    Role                 : owner
    State                : active
    TenantId             : <OwnerTenantId>
    TransitionDetails    : Microsoft.Graph.PowerShell.Models.MicrosoftGraphMultiTenantOrganizationMemberTransitionDetails
    AdditionalProperties : {[multiTenantOrgLabelType, none]}
    ```
4. 非同期処理を許可するには、マルチテナント組織への参加が完了するまで**最大 2 時間**待機します。

## [Microsoft Graph](#tab/ms-graph)
1. メンバー テナントで、[Update multiTenantOrganizationJoinRequestRecord](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganizationjoinrequestrecord-update) API を使用してマルチテナント組織に参加します。

    **依頼**

    ```http
    PATCH https://graph.microsoft.com/v1.0/tenantRelationships/multiTenantOrganization/joinRequest
    {
        "addedByTenantId": "{ownerTenantId}"
    }
    ```
2. [Get multiTenantOrganizationJoinRequestRecord](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganizationjoinrequestrecord-get) API を使用して、参加を確認します。

    **依頼**

    ```http
    GET https://graph.microsoft.com/v1.0/tenantRelationships/multiTenantOrganization/joinRequest
    ```

    この操作は、数分かかります。 参加する API を呼び出した直後にチェックした場合、応答は次と同様のものになります。

    **応答**

    ```http
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#tenantRelationships/multiTenantOrganization/joinRequest/$entity",
        "id": "aa87e8a4-9c88-4e67-971d-79c9e43319a3",
        "addedByTenantId": "{ownerTenantId}",
        "memberState": "pending",
        "role": null,
        "transitionDetails": {
            "desiredMemberState": "active",
            "status": "notStarted",
            "details": ""
        }
    }
    ```

    結合操作が完了すると、応答は次のようになります。

    **応答**

    ```http
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#tenantRelationships/multiTenantOrganization/joinRequest/$entity",
        "id": "aa87e8a4-9c88-4e67-971d-79c9e43319a3",
        "addedByTenantId": "{ownerTenantId}",
        "memberState": "active",
        "role": "member",
        "transitionDetails": null
    }
    ```
3. [List multiTenantOrganizationMembers](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganization-list-tenants) API を使用して、マルチテナント組織自体をチェックします。 参加操作が反映されている必要があります。

    **依頼**

    ```http
    GET https://graph.microsoft.com/v1.0/tenantRelationships/multiTenantOrganization/tenants
    ```

    **応答**

    ```http
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#tenantRelationships/multiTenantOrganization/tenants",
        "value": [
            {
                "tenantId": "{memberTenantIdA}",
                "displayName": "Athens",
                "addedDateTime": "2023-11-20T21:24:59Z",
                "joinedDateTime": "2023-11-21T22:09:18Z",
                "addedByTenantId": "{ownerTenantId}",
                "role": "member",
                "state": "active",
                "transitionDetails": null
            },
            {
                "tenantId": "{memberTenantIdB}",
                "displayName": "Berlin",
                "addedDateTime": "2023-11-20T21:22:35Z",
                "joinedDateTime": "2023-11-21T21:55:34Z",
                "addedByTenantId": "{ownerTenantId}",
                "role": "member",
                "state": "active",
                "transitionDetails": null
            },
            {
                "tenantId": "{ownerTenantId}",
                "displayName": "Cairo",
                "addedDateTime": "2023-11-20T20:38:20Z",
                "joinedDateTime": null,
                "addedByTenantId": "{ownerTenantId}",
                "role": "owner",
                "state": "active",
                "transitionDetails": null
            }
        ]
    }
    ```
4. 非同期処理を許可するには、マルチテナント組織への参加が完了するまで**最大 2 時間**待機します。

---

### 手順 8: (省略可能) マルチテナント組織を離脱する

[Image: メンバー テナントのアイコン。]**メンバー テナント**

参加したマルチテナント組織を離脱することができます。 マルチテナント組織から自分のテナントを削除するプロセスは、マルチテナント組織から別のテナントを削除するプロセスと同じです。

テナントが唯一のマルチテナント組織所有者である場合は、新しいテナントをマルチテナント組織所有者に指定する必要があります。 手順については、「手順 4: (省略可能) テナントのロールを変更する」を参照してください。

## [PowerShell](#tab/ms-powershell)
- テナントで、 [Remove-MgTenantRelationshipMultiTenantOrganizationTenant](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/remove-mgtenantrelationshipmultitenantorganizationtenant) コマンドを使用してテナントを削除します。 この操作は、数分かかります。

    ```powershell
    Remove-MgTenantRelationshipMultiTenantOrganizationTenant -MultiTenantOrganizationMemberId <MemberTenantId>
    ```

## [Microsoft Graph](#tab/ms-graph)
- テナントで、[Remove multiTenantOrganizationMember](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganization-delete-tenants) API を使用して、メンバー テナントを削除します。 この操作は、数分かかります。

    **依頼**

    ```http
    DELETE https://graph.microsoft.com/v1.0/tenantRelationships/multiTenantOrganization/tenants/{memberTenantId}
    ```

---

### 手順 9: (省略可能) マルチテナント組織を削除する

[Image: 所有者テナントのアイコン。]**所有者テナント**

すべてのテナントを削除することで、マルチテナント組織を削除します。 最終的な所有者テナントを削除するプロセスは、他のすべてのメンバー テナントを削除するプロセスと同じです。

## [PowerShell](#tab/ms-powershell)
- 最後の所有者テナントで、[Remove-MgTenantRelationshipMultiTenantOrganizationTenant](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/remove-mgtenantrelationshipmultitenantorganizationtenant) コマンドを使ってテナントを削除します。 この操作は、数分かかります。

    ```powershell
    Remove-MgTenantRelationshipMultiTenantOrganizationTenant -MultiTenantOrganizationMemberId $OwnerTenantId
    ```

## [Microsoft Graph](#tab/ms-graph)
- 最終所有者テナントで、[Remove multiTenantOrganizationMember](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganization-delete-tenants) API を使用して、メンバー テナントを削除します。 この操作は、数分かかります。

    **依頼**

    ```http
    DELETE https://graph.microsoft.com/v1.0/tenantRelationships/multiTenantOrganization/tenants/{ownerTenantId}
    ```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/multi-tenant-organizations/multi-tenant-organization-configure-templates"} -->
## Microsoft Graph API を使用してマルチテナント組織ポリシー テンプレートを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-configure-templates
- Service: entra-id / multitenant-organizations
- Article date: 2026-03-25
- Summary: Microsoft Graph API を使用してマルチテナント組織のテナント間アクセスと ID 同期ポリシー テンプレートを構成します。 自動引き換え、受信同期、テンプレート管理について説明します。

### 概要

この記事では、マルチテナント組織のポリシー テンプレートを構成する方法について説明します。

### 前提 条件

- ライセンス情報については、ライセンス要件を参照してください。
- [マルチテナント組織のテナント間アクセス設定とテンプレートを構成するためのセキュリティ管理者の](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロールです。
- 必要なアクセス許可に同意するための[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)ロール。

### テナント間アクセス ポリシー パートナー テンプレート

[クロステナント アクセス パートナー構成](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration) は、パートナー テナント間の信頼設定と自動ユーザー同意設定を処理します。 たとえば、これらの設定を使用して、ターゲット パートナー テナントからの受信ユーザーの多要素認証要求を信頼できます。 テンプレートが未構成の状態の場合、マルチテナント組織のパートナー テナントのパートナー構成は修正されません。すべての信頼設定が既定の設定から渡されます。 ただし、テンプレートを構成すると、ポリシー テンプレートに対応するパートナー構成が修正されます。

#### 受信と送信の自動引き換えを構成する

ポリシー テンプレートに適用する信頼設定と自動ユーザー同意設定を指定するには、[Update multiTenantOrganizationPartnerConfigurationTemplate](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganizationpartnerconfigurationtemplate-update) API を使用します。 Microsoft 365 管理センターを使用してマルチテナント組織を作成または参加すると、この構成は自動的に処理されます。

**要求**

```http
PATCH https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/templates/multiTenantOrganizationPartnerConfiguration

{
    "inboundTrust": {
        "isMfaAccepted": true,
        "isCompliantDeviceAccepted": true,
        "isHybridAzureADJoinedDeviceAccepted": true
    },
    "automaticUserConsentSettings": {
        "inboundAllowed": true,
        "outboundAllowed": true
    },
    "templateApplicationLevel": "newPartners,existingPartners"
}
```

#### 既存のパートナーのパートナー構成テンプレートを無効にする

このテンプレートを新しいマルチテナント組織のメンバーにのみ適用し、既存のパートナーを除外するには、`templateApplicationLevel` パラメーターを新しいパートナーのみに設定します。

**要求**

```http
PATCH https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/templates/multiTenantOrganizationPartnerConfiguration

{
    "inboundTrust": {
        "isMfaAccepted": true,
        "isCompliantDeviceAccepted": true,
        "isHybridAzureADJoinedDeviceAccepted": true
    },
    "automaticUserConsentSettings": {
        "inboundAllowed": true,
        "outboundAllowed": true
    },
    "templateApplicationLevel": "newPartners"
}
```

#### パートナー構成テンプレートを完全に無効にする

テンプレートを完全に無効にするには、`templateApplicationLevel` パラメーターを null に設定します。

**要求**

```http
PATCH https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/templates/multiTenantOrganizationPartnerConfiguration

{
    "inboundTrust": {
        "isMfaAccepted": true,
        "isCompliantDeviceAccepted": true,
        "isHybridAzureADJoinedDeviceAccepted": true
    },
    "automaticUserConsentSettings": {
        "inboundAllowed": true,
        "outboundAllowed": true
    },
    "templateApplicationLevel": ""
}
```

#### パートナー構成テンプレートをリセットする

テンプレートを既定の状態にリセットする (すべての信頼と自動ユーザーの同意を拒否する) には、[multiTenantOrganizationPartnerConfigurationTemplate: resetToDefaultSettings](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganizationpartnerconfigurationtemplate-resettodefaultsettings) API を使用します。

```http
POST https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/templates/multiTenantOrganizationPartnerConfiguration/resetToDefaultSettings
```

### テナント間同期テンプレート

ID 同期ポリシーは、テナント間同期制御します。これにより、組織内のテナント間でユーザーとグループを共有できます。 これらの設定を使用して、受信ユーザーの同期を許可できます。 テンプレートが未構成の状態の場合、マルチテナント組織のパートナー テナントの ID 同期ポリシーは修正されません。 ただし、テンプレートを構成すると、ポリシー テンプレートに対応する ID 同期ポリシーが修正されます。

#### 受信ユーザー同期を構成する

ポリシー テンプレートで受信ユーザー同期を許可するには、[Update multiTenantOrganizationIdentitySyncPolicyTemplate](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganizationidentitysyncpolicytemplate-update) API を使用します。 Microsoft 365 管理センターを使用してマルチテナント組織を作成または参加すると、この構成は自動的に処理されます。

**要求**

```http
PATCH https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/templates/multiTenantOrganizationIdentitySynchronization

{
    "userSyncInbound": {
        "isSyncAllowed": true
    },
    "templateApplicationLevel": "newPartners,existingPartners"
}
```

#### 既存のパートナーの同期テンプレートを無効にする

このテンプレートを新しいマルチテナント組織のメンバーにのみ適用し、既存のパートナーを除外するには、`templateApplicationLevel` パラメーターを新しいパートナーのみに設定します。

**要求**

```http
PATCH https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/templates/multiTenantOrganizationIdentitySynchronization

{
    "userSyncInbound": {
        "isSyncAllowed": true
    },
    "templateApplicationLevel": "newPartners"
}
```

#### 同期テンプレートを完全に無効にする

テンプレートを完全に無効にするには、`templateApplicationLevel` パラメーターを null に設定します。

**要求**

```http
PATCH https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/templates/multiTenantOrganizationIdentitySynchronization

{
    "userSyncInbound": {
        "isSyncAllowed": true
    },
    "templateApplicationLevel": ""
}
```

#### 同期テンプレートをリセットする

テンプレートを既定の状態 (受信同期の拒否) にリセットするには、[multiTenantOrganizationIdentitySyncPolicyTemplate: resetToDefaultSettings](https://learn.microsoft.com/ja-jp/graph/api/multitenantorganizationidentitysyncpolicytemplate-resettodefaultsettings) API を使用します。

**要求**

```http
POST https://graph.microsoft.com/v1.0/policies/crossTenantAccessPolicy/templates/multiTenantOrganizationIdentitySynchronization/resetToDefaultSettings
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/multi-tenant-organizations/multi-tenant-organization-known-issues"} -->
## マルチテナント組織の制限事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-known-issues
- Service: entra-id / multitenant-organizations
- Article date: 2026-03-18
- Summary: Microsoft Entra ID でマルチテナント組織を使用する際の制限事項について説明します。

### 概要

この記事では、Microsoft Entra ID と Microsoft 365 全体でマルチテナント組織機能を使用する場合に注意すべき制限事項について説明します。 UserVoice のマルチテナント組織機能に関するフィードバックを提供するには、[Microsoft Entra UserVoice](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789?category_id=360892) を参照してください。 UserVoice は、サービスを改善するために注意深く監視されています。

### Scope

この記事で説明する制限事項には、次を対象としています。

| Scope | 説明 |
| --- | --- |
| 対象範囲 | - 相互プロビジョニングされた B2B メンバーとの新しい Microsoft Teams におけるシームレスなコラボレーション エクスペリエンスをサポートするための、マルチテナント組織に関する Microsoft Entra 管理者の制限事項- 一元的にプロビジョニングされた B2B メンバーとの Microsoft Viva Engage におけるシームレスなコラボレーション エクスペリエンスをサポートするための、マルチテナント組織に関する Microsoft Entra 管理者の制限事項 |
| 関連するスコープ | - マルチテナント組織に関連する Microsoft 365 管理センターの制限事項- Microsoft 365 マルチテナント組織ユーザーの検索エクスペリエンス- Microsoft 365 に関連するテナント間同期の制限事項 |
| 対象範囲外 | - Microsoft 365 とは無関係のテナント間同期- Viva Engage のエンド ユーザー エクスペリエンス- テナントの移行または統合 |
| サポートされていないシナリオ | - 学生のシナリオを含む教育テナントでのマルチテナント組織Microsoft 365 Government のマルチテナント組織- 従来の Teams のマルチテナント組織間のシームレスなコラボレーション エクスペリエンス- 100 を超えるテナントを持つマルチテナント組織向けのセルフサービス- 21Vianet が運営する Azure Government または Microsoft Azure のマルチテナント組織- マルチテナント組織は、GCC、GCC-H、および DOD クラウド内で利用できます。 ただし、マルチテナント組織のテナントは、同じクラウド内にあるテナントのみを持つことができます。 クラウド間マルチテナント組織はサポートされていません。 |

### Microsoft 365 管理センターを使用してマルチテナント組織の作成または参加を行う

- Microsoft 365 管理センターでマルチテナント組織を作成すると、Microsoft 管理センターで作成されたテナント間同期構成が `MTO_Sync_<TenantID>` という名前で表示されます。 Microsoft 365 管理センターに、Microsoft 365 管理センターが作成し管理する構成として認識させたい場合は、名前の編集や変更は控えてください。
- Microsoft Entra ID で作成された同期ジョブは、Microsoft 365 管理センターには表示されません。 Microsoft 365 管理センターには、**[Outbound sync status] (送信同期状態)** が **[構成されていない]** と示されます。 これは正しい動作です。 Microsoft 365 管理センターには、Microsoft Entra 管理センターで作成されたテナント間同期ジョブを制御するためにサポートされているパターンはありません。

### クロステナント アクセス設定

- Microsoft Entra ID のテナント間同期では、対象のテナントが ID 同期用のテナント間アクセス設定で受信同期を許可する前に、テナント間同期構成を確立することはサポートされていません。
- そのため、マルチテナント組織の作成前に、`userSyncInbound` を true に設定して、ID 同期用のテナント間アクセス設定テンプレートを使用することをお勧めします。
- 同様に、マルチテナント組織の作成前に、`automaticUserConsentSettings.inboundAllowed` と `automaticUserConsentSettings.outboundAllowed` を true に設定して、パートナー構成用のテナント間アクセス設定テンプレートを使用することをお勧めします。

### 参加リクエスト

- 結合要求が失敗する理由は複数あります。 Microsoft 365 管理センターで参加要求が成功しない理由が示されない場合は、Microsoft Graph API または Microsoft Graph エクスプローラーを使用して、参加要求への応答を調べてみてください。
- マルチテナント組織を作成する正しい手順に従い、マルチテナント組織にテナントを追加しても、追加されたテナントの参加要求が失敗し続ける場合は、Microsoft Entra または Microsoft 365 管理センターにサポート リクエストを送信してください。

### 外部メンバー ユーザーをプロビジョニングするためのオプション

- 既に Microsoft Entra テナント間同期を使用している場合は、さまざまな[マルチハブ マルチスポーク トポロジ](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-topology)に対して、Microsoft 365 管理センターの共有ユーザー機能を使用する必要はありません。 代わりに、既存の Microsoft Entra テナント間同期ジョブを引き続き使用できます。
- Microsoft Entra テナント間同期を使用したことがなく、同じユーザー セットがすべてのマルチテナント組織テナントに共有される[共同作業ユーザー セット](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-microsoft-365#collaborating-user-set) トポロジを確立する場合は、Microsoft 365 管理センターのユーザー共有機能を使用できます。
- 独自の大規模なユーザー プロビジョニング エンジンを既にお持ちの場合は、引き続きその独自のエンジンを使用して従業員のライフサイクルを管理しながら、新しいマルチテナント組織のベネフィットを利用できます。
- 個別の外部メンバー ユーザーを、ソース テナントからプロビジョニング エンジンを使用して作成するのではなく、ホスト テナントで作成する必要がある場合は、「[ユーザーを作成、招待、削除する方法](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users#users-in-workforce-tenants)」を参照してください。

### Microsoft Entra 管理センターでのテナント間同期

- 複雑な ID 構成を持つエンタープライズ組織の場合は、Microsoft Entra 管理センターでテナント間同期を使用します。
- 既定では、新しい B2B ユーザーは B2B メンバーとしてプロビジョニングされますが、既存の B2B ゲストは B2B ゲストのままです。 [**\[このマッピング** を "**常に**" に適用する\]](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure#step-9-review-attribute-mappings) を設定して、B2B ゲストを B2B メンバーに変換することを選択できます。
- 既定では、`showInAddressList` は true としてターゲット テナントに同期されます。 この属性マッピングは、組織のニーズに合わせて調整できます。
- B2Bユーザーの大規模プロビジョニングは、連絡先オブジェクトと競合する可能性があります。 連絡先オブジェクトの処理または変換は現在サポートされていません。
- テナント間同期を使用して、B2B ユーザーに変換されたハイブリッド ID をターゲットにすることは、現在サポートされていません。

### Microsoft 365 管理センターでユーザーを同期する

- 小規模なマルチテナント組織の場合は、Microsoft 365 管理センターを使用して、マルチテナント組織の [複数のテナントにユーザーを同期](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/sync-users-multi-tenant-orgs) することを検討してください。
- Microsoft 365 管理センターでは、ユーザーを共有するために、ターゲット テナントごとに 1 つずつ、複数のテナント間同期ジョブを作成し、すべてのジョブで同じユーザー スコープを維持します。
- Microsoft 365 管理センターでテナント間同期ジョブが作成されたら、属性マッピングを組織のニーズに合わせて Microsoft Entra 管理センターで調整できます。

### B2B ゲストまたは B2B メンバーはホストテナントで管理されている

- B2B ゲストの B2B メンバーへの昇格は、マルチテナント組織が B2B メンバーを組織の信頼できるユーザーと見なす戦略的決定を表します。 B2B メンバーの[既定のアクセス許可](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions)を確認します。
- 組織がマルチテナント組織テナント間での B2B ユーザーのプロビジョニングを含むマルチテナント組織機能をロールアウトする際に、一部のユーザーを B2B ゲストとしてプロビジョニングし、他のユーザーを B2B メンバーとしてプロビジョニングできます。
- ホスト テナント管理者は、B2B ゲストを B2B メンバーに昇格するために、[userType](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-user-profile-info#add-or-change-profile-information) を (このプロパティが繰り返し同期されないと想定して) 変更できます。

### テナント間同期を使用して管理される B2B ゲストまたは B2B メンバー

- テナント間同期を使用して [userType](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-user-profile-info#add-or-change-profile-information) プロパティを繰り返し同期する場合、ソース テナント管理者は[属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure#step-9-review-attribute-mappings)を修正できます。
- ソース テナントで2つのMicrosoft Entraクロステナント同期設定を確立し、1つはuserType属性マッピングをB2Bゲストに構成し、もう1つはuserType属性マッピングをB2Bメンバーに構成します。それぞれの設定において[**\[このマッピングを適用する\]**を**\[常に\]**](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure#step-9-review-attribute-mappings)に設定します。
- ある構成のスコープから別の構成にユーザーを移動すると、ターゲット テナントの B2B ゲストまたは B2B メンバーになるユーザーを簡単に制御できます。 この方法を使用する場合は、[削除のターゲット オブジェクト アクション](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure#step-8-optional-define-who-is-in-scope-for-provisioning-with-scoping-filters)を無効にすることもできます。

### ホスト テナントで管理されているグローバル アドレス一覧

- B2B ユーザーの [showInAddressList](https://learn.microsoft.com/ja-jp/graph/api/resources/user#properties) プロパティは、Microsoft Graph エクスプローラーまたは Microsoft Graph PowerShell で[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)特権を使用して更新できます。
- ユーザー オブジェクトの [showInAddressList](https://learn.microsoft.com/ja-jp/graph/api/resources/user#properties) プロパティを更新すると、Microsoft Exchange Online でアドレス一覧の受信者を非表示にする設定も更新されます。
- [アドレス一覧の受信者を非表示にする](https://learn.microsoft.com/ja-jp/exchange/address-books/address-lists/manage-address-lists#hide-recipients-from-address-lists)設定は、[showInAddressList](https://learn.microsoft.com/ja-jp/graph/api/resources/user#properties) プロパティと異なる構成になっている場合、アドレス一覧の表示内容を決定する際に優先されます。
- [受信者を非表示にする](https://learn.microsoft.com/ja-jp/exchange/address-books/address-lists/manage-address-lists#hide-recipients-from-address-lists)設定を、ユーザーの種類が Guest であるために [Exchange 管理センター](https://learn.microsoft.com/ja-jp/exchange/address-books/address-lists/manage-address-lists#use-the-new-eac-to-hide-recipients-from-address-lists)で構成できない場合は、PowerShell で [HiddenFromAddressListsEnabled](https://learn.microsoft.com/ja-jp/powershell/module/exchange/set-mailuser#-hiddenfromaddresslistsenabled) プロパティを使用して構成できます。
- 詳細については、「[グローバル アドレス一覧にゲストを追加する](https://learn.microsoft.com/ja-jp/microsoft-365/solutions/per-group-guest-access#add-guests-to-the-global-address-list)」を参照してください。

### テナント間同期を使用して管理されるグローバル アドレス一覧

- テナント間同期を使用してプロパティを同期する場合、ソース テナントの [showInAddressList](https://learn.microsoft.com/ja-jp/graph/api/resources/user#properties) を使用して、ターゲット テナントでのアドレス一覧の表示を制御**できます**。
- 一方、ソース テナントの [アドレス一覧から受信者を非表示](https://learn.microsoft.com/ja-jp/exchange/address-books/address-lists/manage-address-lists#hide-recipients-from-address-lists) にして、ターゲット テナントのアドレス一覧の表示に影響を与 **えることはできません** 。

### Microsoft アプリ

- [SharePoint OneDrive](https://learn.microsoft.com/ja-jp/sharepoint/) ユーザー インターフェイスでは、*Fabrikam でファイルを People* と共有すると、Contoso の Fabrikam の B2B メンバーが *Fabrikam の People* にカウントされるため、現在のユーザー インターフェイスは直感に反する可能性があります。
- Microsoft 365 管理センター、[Microsoft Forms](https://learn.microsoft.com/ja-jp/office365/servicedescriptions/microsoft-forms-service-description)、Microsoft OneNote、Microsoft Planner では、B2B メンバー ユーザーがサポートされない場合があります。
- [Microsoft Power BI](https://learn.microsoft.com/ja-jp/fabric/enterprise/powerbi/service-admin-entra-b2b#who-can-you-invite) では、B2B メンバーのサポートは現在プレビュー段階です。 B2B ゲスト ユーザーは、引き続き Power BI ダッシュボードにアクセスできます。
- [Microsoft Power Apps](https://learn.microsoft.com/ja-jp/power-platform/)、[Microsoft Dynamics 365](https://learn.microsoft.com/ja-jp/dynamics365/)、および関連するワークロードでは、B2B メンバー ユーザーの機能が制限されている可能性があります。 詳細については、「[Microsoft Entra Directory B2B コラボレーションでユーザーを招待する](https://learn.microsoft.com/ja-jp/power-platform/admin/invite-users-azure-active-directory-b2b-collaboration)」を参照してください。
- Microsoft Purview では、マルチテナント組織の機能はまだサポートされていません。 詳細については、外部ユーザー向けの既存の機能と、[秘密度ラベルを使用](https://learn.microsoft.com/ja-jp/purview/sensitivity-labels-office-apps#support-for-external-users-and-labeled-content)[したラベル付きコンテンツ](https://techcommunity.microsoft.com/t5/security-compliance-and-identity/secure-external-collaboration-using-sensitivity-labels/ba-p/1680498)と外部コラボレーションを参照してください。
- Microsoft Intune では、マルチテナント組織の機能はまだサポートされていません。 詳細については、 [外部組織からの準拠デバイス要求を信頼](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#to-change-inbound-trust-settings-for-mfa-and-device-claims)するための既存の機能を参照してください。

### B2B ユーザーまたは B2B メンバー

- マルチテナント組織の一部として、[既に利用を開始している B2B ユーザーの利用履歴のリセット](https://learn.microsoft.com/ja-jp/entra/external-id/reset-redemption-status)は現在無効になっています。
- B2Bユーザーの大規模プロビジョニングは、連絡先オブジェクトと競合する可能性があります。 連絡先オブジェクトの処理または変換は現在サポートされていません。
- テナント間同期を使用して、B2B ユーザーに変換されたハイブリッド ID をターゲットにすることは、権限の競合の原因の点でテストされておらず、サポートされていません。
- サインインされたユーザーは、[セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)や[グローバル閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)などのロールの割り当てなしでも、マルチテナント組織およびマルチテナント組織メンバー テナントの基本的な属性を読み取ることができます。

### テナント間同期のプロビジョニング解除

- 既定では、同期ジョブの実行中にプロビジョニング スコープが縮小されると、[削除のターゲット オブジェクト アクション](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure#step-8-optional-define-who-is-in-scope-for-provisioning-with-scoping-filters)が無効になっていない限り、ユーザーはスコープから外れ、論理的に削除されます。 詳細については、「[プロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview#deprovisioning)」および「[プロビジョニングの対象となるユーザーを定義する](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure#step-8-optional-define-who-is-in-scope-for-provisioning-with-scoping-filters)」を参照してください。
- 現時点では、[SkipOutOfScopeDeletions](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions?pivots=cross-tenant-synchronization) はアプリケーション プロビジョニング ジョブに対しては機能しますが、テナント間同期には機能しません。 テナント間同期のスコープから除外されたユーザーの論理的な削除を回避するには、[\[削除のターゲット オブジェクト アクション\]](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure#step-8-optional-define-who-is-in-scope-for-provisioning-with-scoping-filters) を無効に設定します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/multi-tenant-organizations/multi-tenant-organization-microsoft-365"} -->
## Microsoft 365 向けのマルチテナント組織 ID プロビジョニング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-microsoft-365
- Service: entra-id / multitenant-organizations
- Article date: 2026-03-18
- Summary: マルチテナント組織 ID プロビジョニングと Microsoft 365 の連携方法について説明します。

### 概要

マルチテナント組織機能は、複数の Microsoft Entra テナントを所有しており、Microsoft 365 での組織内のテナント間コラボレーションの効率化を求めている組織向けに設計されています。 これは、マルチテナント組織テナント間での B2B メンバー ユーザーの相互プロビジョニングを前提として構築されています。

### Microsoft 365 人物検索

[Teams 外部アクセス](https://learn.microsoft.com/ja-jp/microsoftteams/communicate-with-users-from-other-organizations)と [Teams 共有チャネル](https://learn.microsoft.com/ja-jp/microsoftteams/shared-channels#getting-started-with-shared-channels) は除外され、[Microsoft 365 People 検索](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/multi-tenant-people-search)のスコープは、通常、ローカル テナント境界内に設定されます。 テナント間の仕事仲間によるコラボレーションの必要性が高まっているマルチテナント組織では、ユーザーをそのホーム テナントから、共同作業を行う仕事仲間のリソース テナントに相互プロビジョニングすることが推奨されます。

### 新しい Microsoft Teams

[新しい Microsoft Teams](https://learn.microsoft.com/ja-jp/microsoftteams/new-teams-desktop-admin) エクスペリエンスでは、Microsoft 365 People 検索と Teams 外部アクセスが改善されており、統合されたシームレスなコラボレーション エクスペリエンスが提供されます。 この改善されたエクスペリエンスを活用するには、Microsoft Entra ID でのマルチテナント組織の表現が必要であり、共同作業ユーザーを B2B メンバーとしてプロビジョニングする必要があります。 詳細については、「[マルチテナント組織向けの Microsoft Teams でのよりシームレスなコラボレーションの発表](https://techcommunity.microsoft.com/t5/microsoft-teams-blog/announcing-more-seamless-collaboration-in-microsoft-teams-for/ba-p/3901092)」を参照してください。

### 共同作業ユーザーグループ

Microsoft 365 のコラボレーションは、マルチテナント組織テナント間での B2B ID の相互プロビジョニングを前提として構築されています。

たとえば、テナント A の Annie、テナント B の Bob と Barbara、テナント C の Charlie が共同作業を行うとしましょう。 概念上、これら 4 人のユーザーは、3 つのテナントにまたがる 4 つの内部 ID で構成される共同作業ユーザー セットを表します。

[Image: 複数のテナント内のユーザーを示す図。]

ユーザーの検索が問題なく実行されるためには、スコープをローカル テナント境界に設定したまま、各マルチテナント組織テナント A、B、C のスコープ内で、共同作業ユーザー セット全体を内部 ID または B2B ID のいずれかの形式で表す必要があります。

[Image: 複数のテナント間で表されたユーザーを示す図。]

組織のニーズに応じて、共同作業を行うユーザー セットに、共同作業を行う従業員のサブセット、または最終的にすべての従業員が含まれる場合があります。

### ユーザーの共有

各マルチテナント組織テナントで共同作業ユーザー セットを実現するためのより簡単な方法の 1 つは、各テナント管理者がユーザー コントリビューションを定義し、アウトバウンドで同期することです。 受信側のテナント管理者は、共有ユーザーをインバウンドで受け入れる必要があります。

- 管理者 A は、Annie を参加させるか、Annie を共有する
- 管理者 B は、Bob と Barbara を参加させるか、2 人を共有する
- 管理者CはCharlesを寄与または共有する

[Image: 複数のテナント間で同期されたユーザーを示す図。]

Microsoft 365 管理センターは、マルチテナント組織テナント間でのこのような共同作業ユーザー セットのオーケストレーションを支援します。 詳細については、「[Microsoft 365 のマルチテナント組織のユーザーを同期する](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/sync-users-multi-tenant-orgs)」を参照してください。

または、インバウンドおよびアウトバウンドのテナント間同期に関するペアごとの構成を使用して、マルチテナント組織テナント間のこのような照合ユーザー セットを調整することもできます。 詳細については、「[テナント間同期とは?](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)」を参照してください。

### B2B 会員ユーザー

新しい Microsoft Teams で、マルチテナント組織全体でシームレスなコラボレーション エクスペリエンスを確保するために、B2B ID は [Member userType](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties#user-type) の B2B ユーザーとしてプロビジョニングされます。

| ユーザー同期方法 | 既定の userType プロパティ |
| --- | --- |
| [Microsoft 365 のマルチテナント組織のユーザーを同期する](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/sync-users-multi-tenant-orgs) | **メンバー** B2B ID が既に Guest として存在している場合は、Guest のまま |
| [Microsoft Entra ID でのテナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview) | **メンバー** B2B ID が既に Guest として存在している場合は、Guest のまま |

セキュリティの観点から、B2B メンバー ユーザーに付与されている既定のアクセス許可を確認する必要があります。 詳細については、「[メンバーとゲストの既定のアクセス許可を比較する](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#compare-member-and-guest-default-permissions)」を参照してください。

userType を **ゲスト** から **メンバー** (またはその逆) に変更するには、ソース テナント管理者が [属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure#step-9-review-attribute-mappings)を修正できます。また、プロパティが定期的に同期されていない場合は、ターゲット テナント管理者が [userType を変更](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-user-profile-info#add-or-change-profile-information) できます。

### ユーザーの共有の解除

ユーザーの共有を解除するには、Microsoft Entra テナント間同期で使用できるユーザー プロビジョニング解除機能を使用してユーザーのプロビジョニングを解除します。 既定では、同期ジョブの実行中にプロビジョニング スコープが縮小されると、削除のターゲット オブジェクト アクションが無効になっていない限り、ユーザーはスコープから外れ、論理的に削除されます。 詳細については、「[プロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview#deprovisioning)」および「[プロビジョニングの対象となるユーザーを定義する](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure#step-8-optional-define-who-is-in-scope-for-provisioning-with-scoping-filters)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/multi-tenant-organizations/multi-tenant-organization-overview"} -->
## Microsoft Entra ID でのマルチテナント組織とは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-overview
- Service: entra-id / multitenant-organizations
- Article date: 2026-03-18
- Summary: Microsoft Entra ID と Microsoft 365 のマルチテナント組織について説明します。

### 概要

マルチテナント組織は、Microsoft Entra ID と Microsoft 365 の機能で、これを使用すると、組織が所有する Microsoft Entra テナントの境界を定義できます。 ディレクトリ内では、組織を表すテナント グループという形になります。 グループ内にあるテナントの各ペアは、B2B Collaboration を構成するために使用できるテナント間アクセス設定によって管理されます。

### マルチテナント組織を使用する理由

マルチテナント組織の主な目的を次に示します。

- 組織に属するテナントの境界を定義する
- 新しい Microsoft Teams でテナント間のコラボレーションを行う
- Microsoft Viva Engage でテナント間のコラボレーションを行う

### 対象ユーザーについて

複数の Microsoft Entra テナントを所有し、Microsoft 365 における組織内のテナント間のコラボレーションを効率化する必要がある組織。

Microsoft Teams のマルチテナント組織の機能は、マルチテナント組織のテナント間で [B2B Collaboration メンバー ユーザー](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)が相互プロビジョニングされていることを前提として構築されています。

Viva Engage のマルチテナント組織の機能は、B2B Collaboration メンバー ユーザーがハブ テナントに一元的にプロビジョニングされていることを前提として構築されています。

このため、マルチテナント組織機能は、[テナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)を使用するなど、B2B Collaboration ユーザー用の一括プロビジョニング エンジンの使用によって最も的確にデプロイできます。

### メリット

マルチテナント組織の主な利点を次に示しています。

- 組織内と組織外のユーザーを区別する

    Microsoft Entra ID では、マルチテナント組織内の外部ユーザーと、マルチテナント組織外の外部ユーザーを区別できます。 この区別により、組織内および組織外の外部ユーザーに対して異なるポリシーの適用が容易になります。
- Microsoft Teams でのコラボレーション エクスペリエンスの向上

    新しい Microsoft Teams では、マルチテナント組織のユーザーは、マルチテナント組織全体で接続されているすべてのテナントからのチャット、通話、会議開始の通知により、テナント間のコラボレーション エクスペリエンスの向上が期待できます。 よりシームレスかつ迅速なテナント切り替えが可能になります。 詳細については次を参照してください:

    - [マルチテナント組織向けの Microsoft Teams でのよりシームレスなコラボレーション](https://techcommunity.microsoft.com/t5/microsoft-teams-blog/announcing-more-seamless-collaboration-in-microsoft-teams-for/ba-p/3901092)
    - [Microsoft Teams: 新しいアーキテクチャの利点](https://techcommunity.microsoft.com/t5/microsoft-teams-blog/microsoft-teams-advantages-of-the-new-architecture/ba-p/3775704)
    - [現在使用できるマルチテナント組織の機能](https://techcommunity.microsoft.com/t5/microsoft-365-blog/multi-tenant-organization-capabilities-now-available-in/ba-p/4122812)
- Viva Engage でのコラボレーション エクスペリエンスの向上

    マルチテナント組織向けの Viva Engage を使用すると、複雑に分散している組織どうしが統合ネットワークとしてコミュニケーションをとることができます。 マルチテナント組織のコミュニティ、キャンペーン、イベントから分析にいたるまで、Viva Engage を使用すると、従業員とリーダーが自分たちのマルチテナント組織全体の参加の結合、共有、測定を行うための新たな方法が実現します。 詳細については次を参照してください:

    - [Viva Engage の新機能](https://techcommunity.microsoft.com/t5/viva-engage-blog/what-s-new-for-viva-engage-ignite-edition/ba-p/3981897)
    - [マルチテナント組織向けに Viva Engage を設定する](https://learn.microsoft.com/ja-jp/Viva/engage/mto-setup)
    - [現在使用できるマルチテナント組織の機能](https://techcommunity.microsoft.com/t5/microsoft-365-blog/multi-tenant-organization-capabilities-now-available-in/ba-p/4122812)

### マルチテナント組織のメンバー ユーザーとは

マルチテナント組織を定義する際に、外部ユーザー (B2B Collaboration ユーザー) は userType プロパティに基づいて次の方法でセグメント化されます。

- マルチテナント組織内の (別テナントに所属する) 外部メンバー
- マルチテナント組織内の (別テナントに所属する) 外部ゲスト
- 組織外の (別テナントに所属する) 外部メンバー
- 組織外の (別テナントに所属する) 外部ゲスト

こうして外部ユーザーをセグメント化すると、マルチテナント組織の組織内と組織外の外部ユーザーをより適切に区別できます。

マルチテナント組織内の外部メンバーは、マルチテナント組織メンバー ユーザーと呼ばれる場合があります。

Microsoft 365 のマルチテナント コラボレーション機能を利用すると、マルチテナント組織のメンバー ユーザーとのコラボレーションを行う際に、テナント境界を越えてシームレスなコラボレーション エクスペリエンスが得られます。

### マルチテナント組織のしくみ

マルチテナント組織の機能を使用すると、テナント管理者間の招待/承認フローを通じて、組織が所有する Microsoft Entra テナントの境界を定義できます。 次のリストは、マルチテナント組織の基本的なライフサイクルを説明しています。

- マルチテナント組織の定義

    1 人のテナント管理者が、マルチテナント組織を複数テナントのグループとして定義します。 このテナントのグループは、リストされている各テナントがマルチテナント組織への参加手続きを行うまでは相互に作用しません。 目的は、リストされているすべてのテナント間で相互合意を形成することです。
- マルチテナント組織への参加

    招待対象としてリストされているテナントのテナント管理者は、マルチテナント組織に参加するためのアクションを実行します。 参加後は、マルチテナント組織に参加したすべてのテナント間で、マルチテナント組織間の関係が相互に確立されます。
- マルチテナント組織からの離脱

    リストされているテナントのテナント管理者は、いつでもマルチテナント組織から離脱できます。 マルチテナント組織を定義したテナント管理者は、リストされているテナントを追加および削除できますが、他のテナントを制御することはできません。

マルチテナント組織は、対等な立場のメンバーによるコラボレーションとして構築されます。 各テナント管理者は、自身が所属するテナントとマルチテナント組織におけるメンバーシップを管理します。

### マルチテナント組織の例

次の図は、マルチテナント組織を形成する 3 つのテナント A、B、C を示しています。

[Image: マルチテナント組織のトポロジとテナント間アクセス設定を示す図。]

| Tenant | 説明 |
| --- | --- |
| A | 管理者は、A、B、C で構成されるマルチテナント組織を表示できます。また、B と C のテナント間アクセス設定も表示できます。 |
| B | 管理者は、A、B、C で構成されるマルチテナント組織を表示できます。また、A と C のテナント間アクセス設定も表示できます。 |
| C | 管理者は、A、B、C で構成されるマルチテナント組織を表示できます。また、A と B のテナント間アクセス設定も表示できます。 |

### テナントのロールと状態

マルチテナント組織の管理を容易にするために、どのマルチテナント組織のテナントにもロールと状態が関連付けられています。

| テナントのロール | 説明 |
| --- | --- |
| 担当者 | 1 つのテナントがマルチテナント組織を作成します。 マルチテナント組織を作成したテナントには所有者のロールが付与されます。 所有者テナントの権限は、テナントを保留中状態に追加したり、マルチテナント組織からテナントを削除したりすることです。 また、所有者テナントは、マルチテナント組織の他のテナントのロールを変更できます。 |
| メンバー | 保留中のテナントをマルチテナント組織に追加した後、保留中のテナントの状態を "保留中" から "アクティブ" に変更するには、マルチテナント組織に参加する必要があります。 参加したテナントは、通常、メンバー ロールで開始されます。 メンバー テナントには、マルチテナント組織から離脱する権限があります。 |

| テナントの状態 | 説明 |
| --- | --- |
| 保留中 | 保留中のテナントは、まだマルチテナント組織に参加していません。 保留中のテナントは、マルチテナント組織の管理者のビューには表示されますが、まだマルチテナント組織の一部ではないため、マルチテナント組織のエンド ユーザーのビューには表示されません。 |
| アクティブです | 保留中のテナントをマルチテナント組織に追加した後、保留中のテナントの状態を "保留中" から "アクティブ" に変更するには、マルチテナント組織に参加する必要があります。 参加したテナントは、通常、メンバー ロールで開始されます。 メンバー テナントには、マルチテナント組織から離脱する権限があります。 |

### クロステナント アクセス設定

管理者がリソースを適切に管理できることは、マルチテナント組織におけるコラボレーションの基本原則です。 テナント間アクセス設定は、テナント間の関係ごとに必要です。 テナント管理者は、必要に応じて、以下のポリシーを明示的に構成します。

- テナント間アクセス パートナーの構成

    詳細については、「[B2B コラボレーションのためにテナント間アクセス設定を構成する](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)」および「[crossTenantAccessPolicyConfigurationPartner リソース型](https://learn.microsoft.com/ja-jp/graph/api/resources/crosstenantaccesspolicyconfigurationpartner)」を参照してください。
- テナント間アクセスの ID 同期

    詳細については、「[テナント間同期を構成する](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)」および「[crossTenantIdentitySyncPolicyPartner リソース型](https://learn.microsoft.com/ja-jp/graph/api/resources/crosstenantidentitysyncpolicypartner)」を参照してください。

### テナント間アクセス設定のテンプレート

マルチテナント組織のパートナー テナントに適用される同種のテナント間アクセス設定のセットアップを容易にするために、各マルチテナント組織テナントの管理者は、オプションでマルチテナント組織専用のテナント間アクセス設定テンプレートを構成できます。 これらのテンプレートを使用することで、マルチテナント組織に新たに参加するパートナー テナントに適用されるテナント間アクセス設定を事前に構成できます。

### 制約

マルチテナント組織機能は、以下の制約を考慮して設計されています。

- 個々のテナントが作成または参加できるマルチテナント組織は、1 つだけです。
- クラウド ソリューション プロバイダー (CSP) と顧客テナントの間でマルチテナント組織を使用することはできません。
- マルチテナント組織には、少なくとも 1 つのアクティブな所有者テナントが必要です。
- アクティブな各テナントには、すべてのアクティブなテナントに対するテナント間アクセス設定が必要です。
- アクティブなテナントは、自分自身を削除することで、マルチテナント組織から離れることができます。
- マルチテナント組織は、唯一残っているアクティブな (所有者) テナントが離脱すると、削除されます。

### 制限

| リソース | 制限 | メモ |
| --- | --- | --- |
| 所有者テナントを含め、最大数のアクティブなテナント | 100 | 所有者テナントは 100 を超える保留中のテナントを追加できますが、制限を超えると、マルチテナント組織に参加できなくなります。 この制限は、保留中のテナントがマルチテナント組織に参加する時点で適用されます。 この制限は、マルチテナント組織内のテナント数に固有のものです。 テナント間同期自体には適用されません。 この制限を引き上げるには、Microsoft Entra または Microsoft 365 管理センターでサポート要求を送信します。 |

### 概要

マルチテナント組織の使用を開始するための基本的な手順を次に示します。

#### ステップ 1: デプロイを計画する

詳細については、「[Microsoft 365 でのマルチテナント組織の計画](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/plan-multi-tenant-org-overview)」および「[マルチテナント組織の制限事項](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-known-issues)」を参照してください。

#### ステップ 2: マルチテナント組織を作成する

[Microsoft 365 管理センター](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/set-up-multi-tenant-org)、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-configure-graph?tabs=ms-powershell)、または [Microsoft Graph API](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-configure-graph?tabs=ms-graph) を使用してマルチテナント組織を作成します。

- 最初のテナント (間もなく所有者となるテナント) がマルチテナント組織を作成します。
- 所有者テナントが 1 つ以上の参加者テナントを追加します。

Microsoft 365 管理センターを使用したマルチテナント組織の作成の詳細については、「[Microsoft 365 管理センターを使用してマルチテナント組織の作成または参加を行う](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-known-issues#create-or-join-a-multitenant-organization-using-the-microsoft-365-admin-center)」を参照してください。

#### ステップ 3: マルチテナント組織に参加する

[Microsoft 365 管理センター](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/join-leave-multi-tenant-org)、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-configure-graph?tabs=ms-powershell)、または [Microsoft Graph API](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-configure-graph?tabs=ms-graph) を使用してマルチテナント組織に参加します。

- 参加者テナントは、所有者テナントのマルチテナント組織に参加するための参加要求を送信します。
- 非同期処理が完了されるまでに、**最大で 2 時間**かかることがあります。

これでマルチテナント組織が作成されます。 これにより、マルチテナント組織内の既存の外部メンバー ユーザーがマルチテナント組織のメンバーとして認識され、マルチテナント組織のアクティブなテナント間で、よりシームレスなコラボレーションが可能になります。

Microsoft 365 管理センターを使用したマルチテナント組織への参加の詳細については、「[Microsoft 365 管理センターを使用してマルチテナント組織の作成または参加を行う](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-known-issues#create-or-join-a-multitenant-organization-using-the-microsoft-365-admin-center)」を参照してください。

#### ステップ 4: 外部メンバー ユーザーをプロビジョニングする

Microsoft 365 におけるマルチテナント組織のコラボレーションは、B2B コラボレーション メンバー ユーザーのプロビジョニングに依存しています。 ユース ケースによっては、次の 1 つ以上の方法を使用してユーザーをプロビジョニングできます。

- [Microsoft 365 のマルチテナント組織のユーザーを同期する](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/sync-users-multi-tenant-orgs)
- [Microsoft Entra 管理センターでテナント間同期を構成する](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)
- 既存の一括プロビジョニング エンジンを使用して外部メンバー ユーザーをプロビジョニングする
- [Microsoft Entra 管理センターを使用して個々の外部メンバー ユーザーをプロビジョニングする](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users#users-in-workforce-tenants)

外部メンバー ユーザーのプロビジョニングの詳細については、「[外部メンバー ユーザーをプロビジョニングするためのオプション](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-known-issues#options-to-provision-your-external-member-users)」を参照してください。

#### ステップ 5: Microsoft 365 アプリケーションの要件を満たす

次のマルチテナント組織コラボレーション アプリケーションには、追加の要件がある場合があります。

- [マルチテナント組織に関する Microsoft Teams の要件](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/plan-multi-tenant-org-overview#the-new-microsoft-teams-desktop-client)
- [マルチテナント組織向けの Viva Engage のセットアップ](https://learn.microsoft.com/ja-jp/Viva/engage/mto-setup)

Microsoft 365 アプリケーションの要件が完了すると、従業員は複数のテナントの組織全体でシームレスに共同作業を行うことができます。

### ライセンスの要件

マルチテナント組織の機能には、Microsoft Entra ID P1 ライセンスが必要です。 マルチテナント組織では、従業員 1 人につき Microsoft Entra ID P1 ライセンスが 1 つ必要です。 また、テナントごとに少なくとも 1 つの Microsoft Entra ID P1 ライセンスが必要です。 自分の要件に適したライセンスを探すには、「[一般提供されている Microsoft Entra ID の機能の比較](https://www.microsoft.com/security/business/microsoft-entra-pricing)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/multi-tenant-organizations/multi-tenant-organization-templates"} -->
## マルチテナント組織のオプション ポリシー テンプレート - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-templates
- Service: entra-id / multitenant-organizations
- Article date: 2026-03-18
- Summary: Microsoft Entra ID でのマルチテナント組織オプション ポリシー テンプレートについてご確認ください。

### 概要

管理者がリソースを把握しておくことは、マルチテナント組織のコラボレーションの基本原則です。 各テナント間の関係ごとにテナント間アクセス設定が必要です。 テナント管理者は、マルチテナント組織内のパートナー テナントのテナント間アクセス パートナー構成と ID 同期設定を明示的に構成します。

同種のテナント間アクセス設定をマルチテナント組織内のパートナー テナントに適用するために、各テナントの管理者は、マルチテナント組織専用のオプションのテナント間アクセス設定テンプレートを構成することができます。 この記事では、テンプレートを使用して、マルチテナント組織に新たに参加するパートナー テナントに適用されるテナント間アクセス設定を事前に構成する方法を説明します。

### テナント間アクセス設定の自動生成

マルチテナント組織内では、テナントの各ペアがパートナー構成と ID 同期の両方について、双方向の[テナント間アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)を持つ必要があります。 これらの設定は、信頼を有効にし、ユーザーとアプリケーションを共有するための基になるポリシー フレームワークになります。

テナントが新しいマルチテナント組織に参加する場合、またはパートナー テナントが既存のマルチテナント組織に参加する場合は、拡大されたマルチテナント組織内の他のパートナー テナントに対するテナント間アクセス設定が、まだ存在しなければ、未構成の状態で自動的に生成されます。 未構成の状態では、これらのテナント間アクセス設定は [既定の設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration#configure-default-settings)を引き継ぎます。

既定のクロステナント アクセス設定は、組織固有のカスタマイズした設定を作成していない、すべての外部テナントに適用されます。 通常、これらの設定は信頼できない設定として構成されます。 たとえば、多要素認証と準拠デバイス要求に対するテナント間の信頼が無効になり、B2B 直接接続または B2B コラボレーションでのユーザーとグループの共有が許可されない可能性があります。

一方、マルチテナント組織では、通常、テナント間アクセス設定は信頼できるものと想定されます。 たとえば、多要素認証と準拠デバイス要求に対するテナント間の信頼が有効になり、B2B 直接接続または B2B コラボレーションでのユーザーとグループの共有が許可される可能性があります。

マルチテナント組織のパートナー テナントに対するテナント間アクセス設定の自動生成自体によって認証ポリシーまたは承認ポリシーの動作が変更されることはありませんが、自動生成により、組織はマルチテナント組織内のパートナー テナントのテナント間アクセス設定をテナントごとに簡単にカスタマイズできるようになります。

### マルチテナント組織形成時のポリシー テンプレート

前述のように、マルチテナント組織では、通常、テナント間アクセス設定は信頼できるものと想定されます。 たとえば、多要素認証と準拠デバイス要求に対するテナント間の信頼が有効になり、B2B 直接接続または B2B コラボレーションでのユーザーとグループの共有が許可される可能性があります。

前のセクションで説明したとおり、テナント間アクセス設定の自動生成では、マルチテナント組織のパートナー テナントごとにテナント間アクセス設定の存在が保証されますが、マルチテナント組織のパートナー テナントのテナント間アクセス設定のさらなるメンテナンスはテナントごとに個別に実行されます。

マルチテナント組織の形成時に管理者の作業負荷を軽減するために、必要に応じて、ポリシー テンプレートをテナント間アクセス設定の先行的な構成に使用できます。 これらのテンプレート設定は、テナントがマルチテナント組織に参加した時点で、すべての外部のマルチテナント組織に適用され、パートナー テナントが既存のマルチテナント組織に参加した時点で、その新しいパートナー テナントに適用されます。

パートナー テナントがマルチテナント組織に参加した時点で[オプションのポリシー テンプレートを有効化または構成する](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-configure-templates)ことで、パートナー構成と ID 同期の両方について、対応する[テナント間アクセス設定](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-settings-b2b-collaboration)が先行して修正されます。

例として、A、B、C の 3 つのテナントから構成されるマルチテナント組織の管理者の予想される行動を考えてみましょう。

- 3 つのテナントの管理者は、それぞれのオプションのポリシー テンプレートを有効にして構成し、多要素認証と準拠デバイス要求に対するテナント間信頼を有効にして、B2B 直接接続と B2B コラボレーションでのユーザーとグループの共有を許可します。
- 管理者 A がマルチテナント組織を作成し、テナント B とテナント C を保留中のテナントとしてマルチテナント組織に追加します。
- 管理者 B がマルチテナント組織に参加します。 テナント A のポリシー テンプレートの設定に従って、パートナー テナント B に対するテナント A のテナント間アクセス設定が修正されます。 それと逆に、テナント B のポリシー テンプレートの設定に従って、パートナー テナント A に対するテナント B のテナント間アクセス設定が修正されます。
- 管理者 C がマルチテナント組織に参加します。 テナント A (と B) のポリシー テンプレートの設定に従って、パートナー テナント C に対するテナント A (と B) のテナント間アクセス設定が修正されます。 同様に、テナント C のポリシー テンプレートの設定に従って、パートナー テナント A と B に対するテナント C のテナント間アクセス設定が修正されます。
- これら 3 つのテナントから構成される、このマルチテナント組織が形成された後、マルチテナント組織内のすべてのテナント ペアのテナント間アクセス設定が先行して構成されています。

要約すると、オプションのポリシー テンプレートを構成することで、必要に応じてテナントごとにテナント間アクセス設定をカスタマイズする最大限の柔軟性を維持しながら、マルチテナント組織全体でテナント間アクセス設定を一様に初期化することができます。

ポリシー テンプレートの使用を停止するには、ポリシー テンプレートを既定の状態にリセットします。 詳細については、「[マルチテナント組織テンプレートの構成](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-configure-templates)」を参照してください。

### ポリシー テンプレートのスコープ設定と追加のプロパティ

管理者の構成機能を強化するために、ポリシー テンプレートに従ってテナント間アクセス設定を修正するタイミングを選択できます。 たとえば、あるテナントがマルチテナント組織に参加した時点で、ポリシー テンプレートを適用するテナントを次のように選択できます。

| テナント | 説明 |
| --- | --- |
| 新しいパートナー テナントのみ | テナント間アクセス設定が自動生成されるテナント |
| 既存のパートナー テナントのみ | テナント間アクセス設定を既に持っているテナント |
| すべてのパートナー テナント | 新しいパートナー テナントと既存のパートナー テナントの両方 |
| パートナー テナントなし | ポリシー テンプレートは事実上無効になります |

このコンテキストでは、*新しい*パートナーがテナント間アクセス設定がまだ構成されていないテナントを参照するのに対して、*既存*のパートナーは、テナント間アクセス設定が既に構成されているテナントを参照します。 このスコーピングは、クロステナントアクセスパートナー構成テンプレートの`templateApplicationLevel`プロパティと、クロステナントアクセスアイデンティティ同期テンプレートのプロパティを使用して指定されます。

最後に、テンプレート プロパティ値の解釈の観点から見れば、`null` のテンプレート プロパティ値は、対象となるテナント間アクセス設定の対応するプロパティ値に影響しませんが、定義されたテンプレート プロパティ値には、対象となるテナント間アクセス設定の対応するプロパティ値をテンプレートに従って修正する作用があります。 次の表は、テンプレート プロパティ値が、対応するテナント間アクセス設定値にどのように適用されているかを示しています。

| テンプレート値 | パートナー設定の初期値(マルチテナント組織に参加する前) | 最終的なパートナー設定値(マルチテナント組織に参加した後) |
| --- | --- | --- |
| `null` | &lt;パートナー設定値&gt; | &lt;パートナー設定値&gt; |
| &lt;テンプレート値&gt; | &lt;任意の値&gt; | &lt;テンプレート値&gt; |

### Microsoft 365 管理センターによって使用されるポリシー テンプレート

マルチテナント組織が Microsoft 365 管理センターで形成されると、管理者は次のマルチテナント組織テンプレート設定に同意します。

- ID 同期は、ユーザーがこのテナントに同期できるように設定されます
- テナント間アクセスは、受信と送信の両方について、ユーザーの招待を自動的に引き換えるように設定されます

これは、対応する 3 つのテンプレート プロパティ値を `true` に設定することで実現されます。

- `automaticUserConsentSettings.inboundAllowed`
- `automaticUserConsentSettings.outboundAllowed`
- `userSyncInbound`

詳細については、「[Microsoft 365 でマルチテナント組織に参加または脱退する](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/join-leave-multi-tenant-org)」を参照してください。

### マルチテナント組織の解体時のテナント間アクセス設定

現在、マルチテナント組織の解体に対応するポリシー テンプレート機能はありません。 パートナー テナントがマルチテナント組織を離脱した場合、各テナント管理者は、マルチテナント組織を離脱したパートナー テナントのテナント間アクセス設定を再調査し、結果に応じて修正する必要があります。

マルチテナント組織を離脱したパートナー テナントは、マルチテナント組織の以前のパートナー テナントすべてのテナント間アクセス設定を再調査し、結果に応じて修正する必要があります。また、テナント間アクセス設定の 2 つのポリシー テンプレートのリセットも検討する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/multi-tenant-organizations/overview"} -->
## Microsoft Entra ID のマルチテナント組織機能 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/overview
- Service: entra-id / multitenant-organizations
- Article date: 2026-03-18
- Summary: Microsoft Entra ID でのマルチテナント組織のシナリオと機能について説明します。

### 概要

この記事では、マルチテナント組織のシナリオと、Microsoft Entra ID の関連機能の概要について説明します。

### マルチテナント組織のシナリオとは

マルチテナント組織のシナリオが生じるのは、1 つの組織に Microsoft Entra ID のテナント インスタンスが複数ある場合です。 組織で複数のテナントを使用する主な理由は以下のとおりです。

- **コングロマリット:** 独立経営の複数の子会社または事業単位を含む組織。
- **合併と買収:** 企業を合併または買収する組織。
- **売却活動:** 売却では、ある組織がその事業の一部を分離して新しい組織を立ち上げるか、既存の組織に売却します。
- **複数のクラウド:** 複数のクラウド環境で展開するために、コンプライアンスまたは規制要件を満たす必要がある組織。
- **複数の地理的境界**: 複数の地理的地域で事業を展開し、異所在地に関するさまざま規制を適用する組織。
- **テスト テナントまたはステージング テナント:** プライマリ テナントへのより広範なデプロイに先んじて、テストまたはステージングの目的で複数のテナントを必要としている組織。
- **部門または従業員が作成したテナント:** 開発、テスト、または分離した管理のために部門または従業員がテナントを作成した組織。

### Microsoft Entra テナントとは

*テナント*は Microsoft Entra ID のインスタンスであり、ユーザー、グループ、デバイスなどの組織オブジェクトや、Microsoft 365、サードパーティ アプリケーションなどのアプリケーションの登録を含む、1 つの組織に関する情報がここに置かれます。 テナントには、ディレクトリに登録されているアプリケーションなどのリソースのアクセス ポリシーとコンプライアンス ポリシーも含まれています。 テナントによって提供される主な機能には、ID 認証とリソース アクセス管理が含まれます。

Microsoft Entra の観点からは、テナントは ID およびアクセス管理の範囲を形成するものです。 たとえば、テナント管理者は、テナントの一部またはすべてのユーザーがアプリケーションを使用できるようにしたり、アプリケーションに対するアクセス ポリシーをテナントのユーザーに適用したりします。 さらに、テナントには、組織のメール ドメインや、その組織の従業員が使用する SharePoint URL など、エンド ユーザーのエクスペリエンスを決定付ける組織ブランディング データが含まれています。 Microsoft 365 の観点からは、テナントはコラボレーションとライセンスに関する既定の境界を形成します。 たとえば、Microsoft Teams または Microsoft Outlook のユーザーは、自らが属するテナントから他のユーザーを探して共同作業することが簡単にできますが、他のテナントのユーザーは探すことも見ることもできません。

テナントには特権的な組織データが含まれ、他のテナントから安全に分離されています。 さらに、テナントは、特定のリージョンまたはクラウドでデータが永続化され、処理されるように構成できます。これにより、組織では、データの所在地と処理に関するコンプライアンス要件を満たすためのメカニズムとしてテナントを使用できます。

### マルチテナントの課題

組織が最近、新しい会社を買収したり、別の会社と合併したり、新しく形成された事業単位に基づいて再構築したりしている可能性があります。 別々の ID 管理システムを使用している場合、異なるテナントのユーザーがリソースにアクセスしたり、共同作業したりするのに困難が伴う可能性があります。

次の図は、他のテナントのユーザーが、組織のテナント間でアプリケーションにアクセスできない可能性がある様子を示しています。

[Image: ユーザーがテナント間でアプリケーションにアクセスできないことを示す図。]

組織が進化するにつれて、IT チームも変化するニーズを満たせるように適応していく必要があります。 これには、多くの場合、既存のテナントとの統合や新しいテナントの立ち上げが含まれます。 ID インフラストラクチャの管理方式にかかわらず、ユーザーがシームレスにリソースにアクセスして共同作業ができることが重要です。 現在、カスタム スクリプトまたはオンプレミス ソリューションを使用してテナントをまとめ、テナント間でシームレスなエクスペリエンスを提供している可能性があります。

### マルチテナント組織のマルチテナント機能

[Microsoft Entra ID のマルチテナント組織](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-overview)は、マルチテナント機能のポートフォリオを備え、これを使用することで、複数のテナントを含む組織全体のユーザーと安全に対話し、テナント全体にそれらのユーザーを自動的にプロビジョニングして管理できます。

これらのマルチテナント機能の中には、 [ビジネス ゲスト向け Microsoft Entra External ID と Microsoft Entra External ID](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview) と [Microsoft Entra ID のアプリ プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)が共通のテクノロジ スタックを共有する機能がいくつか含まれているため、これらの他の領域への相互参照が頻繁に見つかる場合があります。 [Microsoft 365 for Enterprise](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/plan-multi-tenant-org-overview) では、マルチテナント機能を使用して、Microsoft Teams 内および Microsoft 365 アプリケーション全体でシームレスなマルチテナント コラボレーション エクスペリエンスを実現あるいは促すことができます。

マルチテナント組織のニーズに対応する、マルチテナント機能セットを次に示します。

- **テナント間アクセス設定** - テナントが、組織内の他のテナントとの間での相互アクセスをどのように許可または禁止するかを管理します。 この設定は、B2B Collaboration、B2B 直接接続、テナント間同期を管理し、また、組織の別のテナントが自分のマルチテナント組織に含まれることが認識されているかどうかを示すものです。
- **B2B 直接接続** - シームレスなコラボレーションを実現するために、別の Microsoft Entra テナントとの間で双方向の相互信頼関係を確立します。 B2B 直接接続ユーザーはディレクトリに表示されませんが、Teams 共有チャネル内でコラボレーションを行うために Teams では表示されます。
- **B2B コラボレーション** – 外部ユーザーに対するアプリケーション アクセスとコラボレーションを可能にします。 B2B Collaboration ユーザーはディレクトリに表示されます。 Microsoft Teams でのコラボレーションに使用できます (有効になっている場合)。 Microsoft 365 アプリケーション全体でも使用できます。
- **テナント間同期**は、組織のテナント間で B2B コラボレーション ユーザーの作成、更新、削除を自動化する同期サービスです。 このサービスを使用すると、ターゲット テナントで Microsoft 365 ユーザー検索の範囲を設定できます。 このサービスは、テナント間アクセス設定の下のテナント間同期設定で管理されます。
- **Microsoft 365 マルチテナント ユーザー検索** - B2B Collaboration ユーザーとのコラボレーション。 B2B Collaboration ユーザーは、アドレス一覧に表示されていれば、Outlook で連絡先として使用できます。 ユーザーの種類が「メンバー」に昇格されると、B2B Collaboration メンバー ユーザーは、ほとんどの Microsoft 365 アプリケーションでコラボレーション可能となります。
- **マルチテナント組織** - 組織が所有する Microsoft Entra テナントの境界を定義し、招待と受け入れフローによって円滑に機能させます。 B2B メンバー プロビジョニングと組み合わせて、Microsoft Teams や Microsoft 365 アプリケーション (Microsoft Viva Engage など) でシームレスなコラボレーション エクスペリエンスを実現できます。 テナント間アクセス設定では、マルチテナント組織のテナントにフラグを設定します。
- **マルチテナント コラボレーション用の Microsoft 365 管理センター** - マルチテナント組織を作成するための直感的な管理ポータル エクスペリエンスを提供します。 小規模なマルチテナント組織の場合はさらに、Microsoft Entra 管理センターを使用する代わりに、ユーザーをマルチテナント組織のテナントに同期するためのシンプル エクスペリエンスが提供されています。

次のセクションで、各機能について詳しく説明します。

#### クロステナント アクセス設定

複数のテナントを含む組織内であっても、Microsoft Entra テナント管理者が常に自分のテナントスコープのリソースを管理することは、基本原則です。 そのため、テナント間アクセス設定はテナント間の関係ごとに必要となり、テナント管理者は、必要に応じてテナント間のアクセス関係を明示的に構成します。

次の図は、基本的なクロステナント アクセスの受信と送信の設定機能を示しています。

[Image: テナント間アクセス設定の概要ダイアグラム。]

詳しくは、[テナント間アクセスの概要](https://learn.microsoft.com/ja-jp/entra/external-id/cross-tenant-access-overview)に関するページをご覧ください。

#### B2B 直接接続

複数のテナントにまたがるユーザーが [Teams Connect 共有チャネル](https://learn.microsoft.com/ja-jp/microsoftteams/platform/concepts/build-and-test/shared-channels)で共同作業できるようにするには、[Microsoft Entra B2B 直接接続](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-direct-connect-overview)を使用できます。 B2B 直接接続は、Teams でのシームレスなコラボレーションのために他の Microsoft Entra テナントとの相互の信頼関係を設定できるようにする外部 ID の機能です。 信頼が確立されると、B2B 直接接続ユーザーは、ホーム テナントからの資格情報を使用してシングル サインオン アクセスできます。

複数のテナント間で B2B 直接接続を使用する上での主な制約を次に示します。

- 現時点では、B2B 直接接続は、Teams Connect 共有チャネルとのみ連携します。

[Image: テナント間での B2B 直接接続の使用を示す図。]

詳細については、「[B2B 直接接続の概要](https://learn.microsoft.com/ja-jp/entra/external-id/b2b-direct-connect-overview)」をご覧ください。

#### B2B コラボレーション

ユーザーがテナント間で共同作業できるようにするには、[Microsoft Entra B2B Collaboration](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) を使用できます。 B2B Collaboration は、自分の組織にゲスト ユーザーを招待して共同作業を行えるようにする外部 の機能です。 招待を引き換えた、またはサインアップを完了した外部ユーザーは、テナントでユーザー オブジェクトとして表現されます。 B2B Collaboration によって、自分のテナントのデータに対するコントロールを維持したまま、自分のテナントのアプリケーションとサービスを外部ユーザーと安全に共有できます。

複数のテナント間で B2B コラボレーションを使用する上での主な制約を次に示します。

- 管理者は、B2B 招待プロセスを使用してユーザーを招待するか、[B2B コラボレーション招待マネージャー](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview#azure-ad-microsoft-graph-api-for-b2b-collaboration)を使用してオンボード エクスペリエンスを構築する必要があります。
- 管理者がカスタム スクリプトを使用してユーザーを同期することが必要な場合があります。
- 自動引き換えの設定によっては、ユーザーは各テナントで同意プロンプトに同意し、引き換えプロセスに従うことが必要な場合があります。

[Image: テナント間での B2B コラボレーションの使用を示す図。]

詳細については、「[B2B コラボレーションの概要](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)」を参照してください。

#### テナント間同期

ユーザーがよりシームレスにテナント間で共同作業できるようにしたい場合は、[Microsoft Entra ID のテナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)を使用できます。 テナント間同期は、Microsoft Entra ID の一方向同期サービスであり、組織のテナント間で B2B コラボレーション ユーザーの作成、更新、削除を自動化します。 テナント間同期は、B2B コラボレーション機能に基づいており、既存の B2B テナント間アクセス設定を利用します。 ユーザーは、ターゲット テナントで B2B コラボレーション ユーザー オブジェクトとして表されます。

テナント間同期を使用する主な利点を次に示します。

- 組織内で B2B コラボレーション ユーザーを自動的に作成し、必要なアプリケーションへのアクセスを付与します。カスタム スクリプトの作成や保守は不要です。
- ユーザー エクスペリエンスを向上させ、ユーザーが招待メールを受信したり、各テナントで同意プロンプトを受け入れることなく、リソースにアクセスできるようにする。
- ユーザーが組織を離れたときに、そのユーザーを自動的に更新および削除する。

複数のテナント間でのテナント間同期の使用に関する主な制約を次に示します。

- 同期されたユーザーは、他の B2B コラボレーション ユーザーと同じように、テナント間で Teams および Microsoft 365 を利用できます。
- デバイスまたは連絡先を同期しません。

[Image: テナント間でのテナント間同期の使用を示す図。]

詳しくは、「[テナント間同期とは](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)」をご覧ください。

#### Microsoft 365 マルチテナントのユーザー検索

B2B Collaboration ユーザーは、広く認知されている [B2B Collaboration のゲスト ユーザー](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) エクスペリエンスだけでなく、Microsoft 365 でコラボレーションを行うことができるようになりました。

マルチテナント組織のユーザー検索は、複数のテナント間でのユーザーの検索と検出を可能にするコラボレーション機能です。 B2B Collaboration ユーザーは、アドレス一覧に表示されていれば、Outlook で連絡先として使用できます。 アドレス一覧に表示されることに加え、ユーザーの種類が「メンバー」に昇格されると B2B Collaboration のメンバー ユーザーは、ほとんどの Microsoft 365 アプリケーションでコラボレーションできるようになります。

複数のテナント全体にわたって Microsoft 365 のユーザー検索を使用する主な利点を次に示しています。

- B2B Collaboration ユーザーを、Outlook でコラボレーションできるようにすることができます。 これを有効にするには、ホスト テナント内の Exchange Online メール ユーザーに対して true に設定した [showInAddressList](https://learn.microsoft.com/ja-jp/graph/api/resources/user#properties) プロパティを使用するか、ソース テナントからテナント間同期を使用します。
- 「メンバー」に設定した [userType](https://learn.microsoft.com/ja-jp/graph/api/resources/user#properties) プロパティ (ホスト テナントの [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-user-profile-info)で管理) を使用するか、ソース テナントからテナント間同期を使用することで、アドレス一覧に既に表示されている B2B Collaboration ユーザーを、ほとんどの Microsoft 365 アプリケーションでコラボレーションできるようにできます。

複数のテナント全体にわたって Microsoft 365 のユーザー検索を使用する主な制約を次に示しています。

- ほとんどの Microsoft 365 アプリケーションでのコラボレーションの場合、B2B Collaboration ユーザーは、アドレス一覧に表示され、さらにユーザーの種類が「メンバー」に設定されている必要があります。
- アドレス一覧のその他の制約については、[マルチテナント組織でのグローバル アドレス一覧の制限](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-known-issues#global-address-list-managed-in-the-host-tenant)に関する記事を参照してください。

詳細については、[Microsoft 365 マルチテナントのユーザー検索](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/multi-tenant-people-search)に関する記事を参照してください。

#### マルチテナント組織

[マルチテナント組織](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-overview)は、Microsoft Entra ID と Microsoft 365 の機能で、これを使用すると、組織が所有する Microsoft Entra テナントの境界を定義できます。 ディレクトリ内では、組織を表すテナント グループの形式をとります。 グループ内にあるテナントの各ペアは、B2B Collaboration を構成するために使用できるテナント間アクセス設定によって管理されます。

マルチテナント組織の主な利点を次に示しています。

- 組織内と組織外のユーザーを区別する
- 新しい Microsoft Teams でのコラボレーション エクスペリエンスの向上
- Viva Engage でのコラボレーション エクスペリエンスの向上

マルチテナント組織を使用する場合の主な制約を次に示しています。

- マルチテナント組織に含まれるテナントに既に B2B Collaboration メンバー ユーザーが存在する場合、それらのユーザーは、マルチテナント組織が作成されると直ぐにマルチテナント組織のメンバーになります。 そのため、マルチテナント組織のエクスペリエンスを提供するアプリケーションでは、既存の B2B Collaboration メンバー ユーザーがマルチテナント組織のユーザーとして認識されます。
- 向上した Microsoft Teams のコラボレーションは、B2B Collaboration メンバー ユーザーの相互プロビジョニングに依存します。
- 向上した Viva Engage のコラボレーションは、B2B Collaboration メンバーの一元的なプロビジョニングに依存します。
- その他の制約については、「[マルチテナント組織の制限事項](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-known-issues)」を参照してください。

[Image: マルチテナント組織のトポロジとテナント間アクセス設定を示す図。]

詳細については、「[Microsoft Entra ID でのマルチテナント組織とは](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-overview)」を参照してください。

#### マルチテナント コラボレーション用の Microsoft 365 管理センター

[マルチテナント コラボレーション用の Microsoft 365 管理センター](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/plan-multi-tenant-org-overview)は、マルチテナント組織を作成するための直感的な管理ポータル エクスペリエンスを提供します。

- Microsoft 365 管理センターで[マルチテナント組織を作成](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/set-up-multi-tenant-org)します。

Microsoft では、マルチテナント組織の作成後に、従業員をマルチテナント組織の隣接するテナントに大規模にプロビジョニングするための方法を 2 つ用意しています。

- 複雑な ID トポロジを持つエンタープライズ組織の場合は、 [Microsoft Entra ID でテナント間同期を](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview)使用することを検討してください。 テナント間同期は詳細に構成でき、これを使用すると、どのようなマルチハブ、マルチスポークの ID トポロジでもプロビジョニングできます。
- 従業員をすべてのテナントにプロビジョニングする小規模なマルチテナント組織の場合は、Microsoft 365 管理センターに留まり、マルチテナント組織の [複数のテナントにユーザーを同時に同期](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/sync-users-multi-tenant-orgs) することを検討してください。

独自の大規模なユーザー プロビジョニング エンジンを既にお持ちの場合は、引き続きその独自のエンジンを使用して従業員のライフサイクルを管理しながら、新しいマルチテナント組織の利点を享受できます。

Microsoft 365 管理センターを使用してマルチテナント組織を作成し、従業員をプロビジョニングする場合の主な利点を次に示しています。

- Microsoft 365 管理センターでは、マルチテナント組織を作成するためのグラフィカル ユーザー エクスペリエンスが提供されています。
- Microsoft 365 管理センターでは、B2B Collaboration の招待の自動引き換え用にテナントが事前に構成されます。
- Microsoft 365 管理センターでは、受信ユーザー同期用にテナントを事前に構成します。ただし、テナント間同期の使用はオプションのままです。
- Microsoft 365 管理センターを使用すると、マルチテナント組織の複数のテナントに従業員を簡単にプロビジョニングできます。
- Microsoft 365 管理センターでは、Exchange Online 組織のリレーションシップを作成して、予定表の可用性情報を表示できます。

Microsoft 365 管理センターを使用してマルチテナント組織を作成する、または従業員をプロビジョニングする場合の、主な制約を次に示しています。

- Microsoft Entra 管理センターでのテナント間同期の使用が意図されている場合でも、Microsoft 365 管理センターでは、テナント間同期ジョブが事前に構成されますが、ジョブは開始されません。
- マルチハブやマルチスポーク システムなどの複雑な ID トポロジは、Microsoft Entra 管理ポータルでテナント間同期を使用すると、より適切にプロビジョニングされます。

詳細については、[Microsoft 365 マルチテナントのコラボレーション](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/plan-multi-tenant-org-overview)に関する記事を参照してください。

### マルチテナント機能の比較

組織のニーズに応じて、B2B 直接接続、B2B コラボレーション、テナント間同期、マルチテナント組織機能を自由に組み合わせて使用できます。 B2B 直接接続と B2B コラボレーションが独立した機能である一方、テナント間同期とマルチテナント組織機能は互いに独立していますが、どちらも基になる B2B Collaboration に依存しています。

次の表では、それぞれの機能を比較しています。 さまざまな外部 ID シナリオの詳細については、「[外部 ID 機能セットの比較](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview#comparing-external-identities-feature-sets)」を参照してください。

| - | B2B 直接接続(組織間の外部または内部) | B2B コラボレーション(組織間の外部または内部) | テナント間同期(組織内部) | マルチテナント組織(組織内部) |
| --- | --- | --- | --- | --- |
| **目的** | ユーザーは、外部テナントでホストされている Teams Connect 共有チャネルにアクセスできます。 | ユーザーは、外部テナントでホストされているアプリ/リソースに、通常は制限付きのゲスト権限でアクセスできます。 自動引き換えの設定によっては、ユーザーは各テナントで同意プロンプトに同意することが必要な場合があります。 | ユーザーは、異なるテナントでホストされている場合でも、同じ組織全体のアプリ/リソースにシームレスにアクセスできます。 | ユーザーは、新しい Teams と Viva Engage で、マルチテナント組織全体にわたってよりシームレスにコラボレーションを行うことができます。 |
| **価値** | Teams Connect 共有チャネル内でのみ外部コラボレーションを有効にします。 B2B ユーザーを管理する必要がないため、管理者にとってより便利です。 | 外部コラボレーションを可能にします。 B2B コラボレーション ユーザーを管理することで、管理者の制御と監視を強化します。 管理者は、これらの外部ユーザーが持つアクセスを、ユーザーのアプリ/リソースに制限できます。 | 組織のテナント間でのコラボレーションを有効にします。 管理者は、組織内のアプリやリソースへの継続的なアクセスを確保するために、テナント間でユーザーを手動で招待および同期する必要はありません。 | 組織のテナント間でのコラボレーションを有効にします。 管理者は、テナント間アクセス設定を使用して、引き続きすべての構成を行うことができます。 オプションのテナント間アクセス テンプレートを使用すると、テナント間アクセス設定を事前に構成できます。 |
| **管理者の主なワークフロー** | テナント間アクセスを構成し、外部ユーザーが自分のホーム テナントの資格情報を使用してテナントに受信アクセスできるようにします。 | B2B 招待プロセスを使用して外部ユーザーをリソース テナントに追加するか、[B2B コラボレーション招待マネージャー](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview#azure-ad-microsoft-graph-api-for-b2b-collaboration)を使用して独自のオンボード エクスペリエンスを構築します。 | B2B コラボレーション ユーザーとして複数のテナント間でユーザーを同期するようにテナント間同期エンジンを構成します。 | マルチテナント組織を作成し、テナントを追加 (招待) して、マルチテナント組織に参加します。 既存の B2B Collaboration ユーザーを使用するか、テナント間同期を使用して B2B Collaboration ユーザーをプロビジョニングします。 |
| **信頼レベル** | 中程度の信頼。 B2B 直接接続ユーザーの追跡はそれほど容易ではなく、外部組織との間で一定レベルの信頼が必要です。 | 低から中程度の信頼。 ユーザー オブジェクトを容易に追跡でき、詳細な制御を使用して管理できます。 | 高い信頼。 すべてのテナントは同じ組織の一部であり、ユーザーは通常、すべてのアプリ/リソースへのメンバー アクセスを許可されます。 | 高い信頼。 すべてのテナントは同じ組織の一部であり、ユーザーは通常、すべてのアプリ/リソースへのメンバー アクセスを許可されます。 |
| **ユーザーに対する影響** | ユーザーは自分のホーム テナントの資格情報を使用してリソース テナントにアクセスします。 ユーザー オブジェクトはリソース テナントに作成されません。 | 外部ユーザーは B2B コラボレーション ユーザーとしてテナントに追加されます。 | 同じ組織内で、ユーザーは B2B コラボレーション ユーザーとして自分のホーム テナントからリソース テナントに同期されます。 | 同じマルチテナント組織内では、B2B Collaboration ユーザー (特にメンバー ユーザー) は、Microsoft 365 全体で強化されたシームレスなコラボレーションの恩恵を受けることができます。 |
| **ユーザーの種類** | B2B 直接接続ユーザー- 該当なし | B2B コラボレーション ユーザー- 外部メンバー- 外部ゲスト (既定) | B2B コラボレーション ユーザー- 外部メンバー (既定)- 外部ゲスト | B2B コラボレーション ユーザー- 外部メンバー (既定)- 外部ゲスト |

次の図は、B2B 直接接続、B2B コラボレーション、テナント間同期の機能を組み合わせて使用する方法を示しています。

[Image: さまざまなマルチテナント機能を示す図。]

### 用語

マルチテナント組織シナリオに関連する Microsoft Entra 機能をより深く理解するには、次の用語リストを参照してください。

| 用語 | Definition |
| --- | --- |
| テナント | Microsoft Entra ID のインスタンス。 |
| 組織 | ビジネス階層の最上位レベル。 |
| マルチテナント組織 | Microsoft Entra ID の複数のインスタンスに加えて、それらのインスタンスを Microsoft Entra ID でグループ化する機能を持つ組織。 |
| 作成者テナント | マルチテナント組織を作成したテナント。 |
| 所有者テナント | 所有者ロールを持つテナント。 最初は、作成者テナントです。 |
| 追加されたテナント | 所有者テナントによって追加されたテナント。 |
| 参加者テナント | マルチテナント組織に参加しているテナント。 |
| 参加要求 | 参加者テナントまたは追加されたテナントによって、マルチテナント組織に参加するための参加要求が送信されます。 |
| 保留中のテナント | 所有者によって追加されたが、まだ参加していないテナント。 |
| アクティブなテナント | マルチテナント組織を作成または参加したテナント。 |
| メンバー テナント | メンバー ロールを持つテナント。 ほとんどの参加者テナントはメンバーとして開始します。 |
| マルチテナント組織のテナント | マルチテナント組織の保留中ではないアクティブなテナント。 |
| テナント間同期 | Microsoft Entra ID の一方向同期サービスであり、組織のテナント間で B2B コラボレーション ユーザーの作成、更新、削除を自動化します。 |
| テナント間アクセス設定 | 特定の Microsoft Entra 組織のコラボレーションを管理するための設定。 |
| クロステナント アクセス設定テンプレート | マルチテナント組織に新しく参加するパートナー テナントに適用されるテナント間アクセス設定を、事前構成するためのオプションのテンプレート。 |
| 組織の設定 | 特定の Microsoft Entra 組織のテナント間アクセス設定。 |
| 構成 | テナント間同期に必要な設定 (ターゲット テナント、ユーザー スコープ、属性マッピングなど) を含む、Microsoft Entra ID のアプリケーションと基になるサービス プリンシパル。 |
| プロビジョニング | 境界を越えてオブジェクトを自動的に作成または同期するプロセス。 |
| 自動引き換え | 新しく作成されたユーザーが招待メールを受信せず、ターゲット テナントに追加されたときに同意プロンプトを受け入れる必要もないように、招待を自動的に引き換えるための B2B 設定。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/multi-tenant-organizations/whats-new"} -->
## マルチテナント組織のドキュメントの新機能 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/whats-new
- Service: entra-id / multitenant-organizations
- Article date: 2026-03-18
- Summary: Microsoft Entra ID のマルチテナント組織の新機能とドキュメントの機能強化について説明します。

### 概要

この記事では、Microsoft Entra ID のマルチテナント組織の新機能とドキュメントの改善に関する情報を提供します。

### 2026

| 日付 | Area | Description |
| --- | --- | --- |
| 2026 年 8 月 | テナント間同期 | 更新された [概要]、[属性マッピング]、[プロビジョニング構成]、[基本設定] ページの構成手順を変更しました。 「 [テナント間同期の構成」を](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)参照してください。 |
| 2026 年 3 月 | テナント間同期 | プレビューでセキュリティ グループ同期 (同じクラウド) のサポートを追加しました。 [グループ同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview#group-synchronization)と[テナント間同期の構成に関するページ](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure?pivots=same-cloud-synchronization)を参照してください。 |

### 2025

| 日付 | Area | Description |
| --- | --- | --- |
| 2025 年 6 月 | テナント間同期 | クラウド間同期のサポート状態の更新。 「 [テナント間同期の構成」を](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure?pivots=cross-cloud-synchronization)参照してください。 |
| 2025 年 6 月 | テナント間同期 | クラウド間同期プレビュー。 「 [テナント間同期の構成」を](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure?pivots=cross-cloud-synchronization)参照してください。 |
| 2025 年 5 月 | マルチテナント組織 | PowerShell API と Microsoft Graph API の一般提供に関するベータ版リファレンスを更新しました。 [「PowerShell または Microsoft Graph API を使用してマルチテナント組織を構成](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-configure-graph)する」を参照してください。 |

### 2024

| 日付 | Area | Description |
| --- | --- | --- |
| 2024 年 5 月 | マルチテナント組織 | 機能と概要の更新。 [Microsoft Entra ID のマルチテナント組織の機能](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/overview)と、[Microsoft Entra ID のマルチテナント組織とは何かを](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-overview)参照してください。 |
| 2024 年 4 月 | マルチテナント組織 | マルチテナント組織機能の一般提供。 [「Microsoft Entra ID のマルチテナント組織とは」を](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/multi-tenant-organization-overview)参照してください。 |
| 2024 年 1 月 | テナント間同期 | 手順とスクリーンショットを更新します。 「 [テナント間同期の構成」を](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-configure)参照してください。 |
| 2024 年 1 月 | テナント間同期 | ディレクトリ拡張機能を追加しました。 [テナント間同期でのディレクトリ拡張機能のマップに関する](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-directory-extensions)ページを参照してください。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control"} -->
## Microsoft Entra RBAC のドキュメント - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control
- Service: entra-id / role-based-access-control
- Article date: 2025-02-18
- Summary: Microsoft Entra ロールベースのアクセス制御 (RBAC) を使用して、組織内のアクセスを管理する方法について説明します。

Microsoft Entra ロールベースのアクセス制御 (RBAC) は、Microsoft Entra リソースへのアクセスを管理します。 ロールを割り当ててアクセス権を付与する、グループを使用してロールの割り当てを管理する、スコープを制限する管理単位を作成する、または特定のアクセス許可を持つカスタム ロールを作成します。

### Microsoft Entra RBAC について

#### 概要

- [Microsoft Entra RBAC とは](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview)
- [ドキュメントの最新情報](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/whats-new)

#### 概念

- [Microsoft Entra ロールについて](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/concept-understand-roles)
- [管理単位について](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units)
- [グループを使用してロールの割り当てを管理する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)

### ロールの選択

#### 攻略ガイド

- [ロール定義を一覧表示する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/role-definitions-list)

#### リファレンス

- [組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)
- [タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task)

### ロールの割り当てを一覧表示する

#### 攻略ガイド

- [ロールの割り当てを一覧表示する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/view-assignments)

### ロールの割り当て

#### 攻略ガイド

- [ユーザーとグループにロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)
- [ロール割り当て可能なグループを作成する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-create-eligible)

### カスタム ロールを作成する

#### 攻略ガイド

- [カスタム ロールを作成する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)
- [エンタープライズ アプリのカスタム ロールを作成する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-enterprise-apps)

### 管理単位を使用してスコープを管理する

#### 攻略ガイド

- [管理単位を作成する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-manage)
- [メンバーの追加](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-members-add)
- [スコープを持つロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/admin-units-faq-troubleshoot"} -->
## 管理単位のトラブルシューティングと FAQ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-faq-troubleshoot
- Service: entra-id / role-based-access-control
- Article date: 2024-10-29
- Summary: Microsoft Entra ID で範囲を制限してアクセス許可を付与する管理単位を調査します。

Microsoft Entra ID でよりきめ細かな管理制御を行う場合、1 つ以上の管理単位に制限されたスコープで Microsoft Entra ロールにユーザーを割り当てることができます。 一般的なタスク用の PowerShell スクリプトのサンプルについては、「[管理単位の操作](https://learn.microsoft.com/ja-jp/powershell/azure/active-directory/working-with-administrative-units)」を参照してください。

### 全般

#### 管理単位を作成できないのはなぜですか?

Microsoft Entra ID で管理単位を作成するには、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)ロールが割り当てられている必要があります。 管理単位を作成しようとしているユーザーに、"特権ロール管理者" ロールが割り当てられていることを確認します。

#### 管理単位にグループを追加しました。 そこにグループ メンバーがまだ表示されないのはなぜですか?

グループを管理単位に追加しても、そのグループのすべてのメンバーがそれに追加されるわけではありません。 ユーザーを管理単位に直接割り当てる必要があります。

#### 管理単位のメンバーを先ほど追加 (または削除) しました。 メンバーがユーザー インターフェイスに表示されない (または、まだ表示されている) のはなぜですか?

管理単位の 1 つまたは複数のメンバーの追加または削除が **[管理単位]** ページに反映されるまでに数分かかる場合があります。 あるいは、関連するリソースのプロパティに直接アクセスして、アクションが完了したかどうかを確認することもできます。 管理単位のメンバーの詳細については、「[管理単位のユーザー、グループ、デバイスを一覧表示する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-members-list)」を参照してください。

#### 私は管理単位の委任されたパスワード管理者です。 特定のユーザーのパスワードをリセットできないのはなぜですか?

管理単位の管理者は、自分の管理単位に割り当てられているユーザーのパスワードのみをリセットできます。 パスワードのリセットが失敗している対象のユーザーが、あなたが割り当てられている管理単位に属していることを確認してください。 ユーザーが同じ管理単位に属していても、そのユーザーのパスワードをリセットできない場合は、そのユーザーに割り当てられているロールを確認してください。

特権の昇格を防ぐため、管理単位のスコープが設定された管理者は、組織全体のスコープでロールに割り当てられているユーザーのパスワードをリセットすることはできません。

#### 管理単位が必要なのはなぜですか? スコープを定義する方法としてセキュリティ グループを使用することはできないのでしょうか?

セキュリティ グループには、既存の目的と承認モデルがあります。 たとえば "ユーザー管理者" は、Microsoft Entra 組織内のすべてのセキュリティ グループのメンバーシップを管理できます。 このロールは、Salesforce などのアプリケーションへのアクセスを管理するためにグループを使用する場合があります。 "ユーザー管理者" は委任モデル自体を管理できないようにする必要があります。これは、"リソースのグループ化" シナリオをサポートするためにセキュリティ グループが拡張された場合の結果です。

Windows Server Active Directory の組織単位などの管理単位は、さまざまなディレクトリ オブジェクトの管理スコープを設定する方法を提供するためのものです。 セキュリティ グループ自体は、リソース スコープのメンバーであっても構いません。 セキュリティ グループを使用して、管理者が管理できるセキュリティ グループのセットを定義すると、混乱を招く可能性があります。

#### グループを管理単位に追加するとはどういう意味ですか?

管理単位にグループを追加すると、グループ自体は管理単位の管理スコープに入れられますが、グループのメンバーには入れられ**ません**。 詳細については、「[Microsoft Entra ID の管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units#groups)」を参照してください。

#### リソース (ユーザー、グループ、デバイス) を複数の管理単位のメンバーにすることはできますか?

はい。リソースは複数の管理単位のメンバーにすることができます。 リソースを管理できるのは、そのリソースに対するアクセス許可を持つすべての組織全体および管理単位でスコープ設定された管理者です。

#### B2C 組織では管理単位を使用できますか?

いいえ。B2C 組織では管理単位を使用できません。

#### 入れ子になった管理単位はサポートされていますか?

いいえ。入れ子になった管理単位はサポートされていません。

#### PowerShell と Microsoft Graph API では管理単位はサポートされていますか?

はい。 管理単位のサポートについては、[PowerShell コマンドレットのドキュメント](https://learn.microsoft.com/ja-jp/powershell/module/azuread/)と[サンプル スクリプト](https://learn.microsoft.com/ja-jp/powershell/azure/active-directory/working-with-administrative-units)を参照してください。

Microsoft Graph で [administrativeUnit リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/administrativeunit)のサポートを検索してください。

### 動的管理単位

#### 管理単位の動的メンバーシップ グループのルールを保存したばかりなのですが、まだ設定されたユーザーが表示されません。

管理単位の初期更新は、テナントのサイズと現在の Microsoft Entra ID 負荷に応じて数分かかることがあります。

#### ルール ビルダーを使用して Microsoft Entra 管理センターで動的メンバーシップ グループのルールを作成し、保存しようとすると、"管理単位のプロパティを更新できませんでした" というエラーが表示されます。

これは、通常、指定されたプロパティ値に問題があることを意味しています。 指定したプロパティ値の値の型が適切 (ブール値、文字列、または文字列コレクション) であることを確認します。 詳細については、 「[ユーザー](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership#supported-properties) または [デバイス](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership#rules-for-devices)の各オペレーターに許可されている値」を参照してください。

このエラーは、Microsoft Entra ID Premium P1 ライセンスを持たないユーザーが管理単位に更新を保存しようとした場合にも発生することがあります。

#### 現在の動的メンバーシップ グループのルールに加えて、1 人のメンバーを管理単位に追加するにはどうすればいいですか?

1 人のユーザーを追加するには、`OR` クエリ演算子を含む適切な式を動的メンバーシップ グループのルールに追加します。

#### 私は特権ロール管理者ですが、管理単位のメンバーを追加または削除できません。

管理単位が動的メンバーシップ グループ用に構成されている場合、メンバーシップを変更するには、動的メンバーシップ グループのルールを編集する必要があります。

#### テナントには、動的メンバーシップ グループのルールが設定された管理単位をいくつ作成できますか?

動的メンバーシップ グループと動的管理単位の合計数は 15,000 を超えることはできません。

#### 動的メンバーシップ グループのルールに文字数の制限はありますか?

はい。 3,072 文字です。

#### Microsoft 365 管理センターで動的メンバーシップ グループのルールが設定された管理単位を作成できますか?

いいえ。

### 制限付き管理の管理単位

#### 私は、制限付き管理の管理単位のメンバーであるグループの所有者です。 アクセス許可はどのように影響しますか?

保護されたグループの所有者は、所有権のみに基づいてグループを管理することはできません。 現在、保護されたリソースを管理するには、保護されたリソースの制限付き管理の管理単位のスコープでロールを割り当てる必要があります。

#### 制限付き管理の管理単位を使用すると、Microsoft 365 リソースはどのように影響を受けますか?

現在、制限付き管理の管理単位での Microsoft Entra リソースのセキュリティ保護はサポートされています。 Microsoft Entra ID の外部で管理されるリソースはサポートされていません。

#### 制限付き管理の管理単位のメンバーを変更できません。

ユーザー、グループ、またはデバイスは、制限付き管理の管理単位のメンバーです。 管理権限は、その管理単位をスコープとする管理者に限定されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/admin-units-manage"} -->
## 管理単位の作成または削除 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-manage
- Service: entra-id / role-based-access-control
- Article date: 2025-01-03
- Summary: 管理単位を作成して、Microsoft Entra ID のロールアクセス許可のスコープを制限します。

管理単位を使用すると、組織を任意の単位に分割し、そのユニットのメンバーのみを管理できる特定の管理者を割り当てることができます。 たとえば、管理単位を使用して、大規模な大学の各学校の管理者にアクセス許可を委任し、アクセスを制御し、ユーザーを管理し、エンジニアリングの学校でのみポリシーを設定することができます。

この記事では、管理単位を作成または削除して、Microsoft Entra ID のロールアクセス許可のスコープを制限する方法について説明します。

### 前提 条件

- 各管理単位管理者の Microsoft Entra ID P1 または P2 ライセンス
- 管理単位メンバー向けの Microsoft Entra ID 無料ライセンス
- 特権ロール管理者ロール
- [PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) を使用する場合の Microsoft Graph PowerShell モジュール
- Microsoft Graph API 用 Graph エクスプローラーを使用する場合の管理者の同意

詳細については、「powerShell または Graph Explorerを使用するための 前提条件」を参照してください。

### 管理単位を作成する

Microsoft Entra 管理センター、Microsoft Entra PowerShell、または Microsoft Graph を使用して、新しい管理単位を作成できます。

## [管理センター](#tab/admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[ロールと管理者]**&gt;**[管理単位]** に移動します。

    [Image: [管理単位] ページのスクリーンショット。]
3. **追加**を選択します。
4. [ **名前** ] ボックスに、管理単位の名前を入力します。 必要に応じて、管理単位の説明を追加します。
5. テナント レベルの管理者がこの管理単位にアクセスできないようにするには、[制限付き管理単位の ] トグルを [はい]に設定します。 詳細については、「制限付き管理単位 」を参照してください。

    [Image: [管理単位の追加] ページと、管理単位の名前を入力するための [名前] ボックスを示すスクリーンショット。]
6. 必要に応じて、[ **ロールの割り当て** ] タブでロールを選択し、この管理単位スコープでロールを割り当てるユーザーを選択します。

    [Image: この管理単位スコープでロールの割り当てを追加する [割り当ての追加] ウィンドウを示すスクリーンショット。]
7. [ **確認と作成** ] タブで、管理単位とロールの割り当てを確認します。
8. [ **作成** ] ボタンを選択します。

## [PowerShell](#tab/ms-powershell)
[Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) コマンドを使用してテナントにサインインし、必要なアクセス許可に同意します。

```powershell
Connect-MgGraph -Scopes "AdministrativeUnit.ReadWrite.All"
```

[New-MgDirectoryAdministrativeUnit](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/new-mgdirectoryadministrativeunit) コマンドを使用して、新しい管理単位を作成します。

```powershell
$params = @{
    DisplayName = "Seattle District Technical Schools"
    Description = "Seattle district technical schools administration"
    Visibility = "HiddenMembership"
}
$adminUnitObj = New-MgDirectoryAdministrativeUnit -BodyParameter $params
```

[New-MgDirectoryAdministrativeUnit](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/new-mgdirectoryadministrativeunit) コマンドを使用して、新しい制限付き管理単位を作成します。 `IsMemberManagementRestricted` プロパティを `$true`に設定します。

```powershell
$params = @{
    DisplayName = "Contoso Executive Division"
    Description = "Contoso Executive Division administration"
    Visibility = "HiddenMembership"
    IsMemberManagementRestricted = $true
}
$restrictedAU = New-MgDirectoryAdministrativeUnit -BodyParameter $params
```

## [Graph API](#tab/ms-graph)
[Create administrativeUnit](https://learn.microsoft.com/ja-jp/graph/api/directory-post-administrativeunits) API を使用して、新しい管理単位を作成します。

依頼

```http
POST https://graph.microsoft.com/v1.0/directory/administrativeUnits
```

本文

```http
{
  "displayName": "North America Operations",
  "description": "North America Operations administration"
}
```

[Create administrativeUnit](https://learn.microsoft.com/ja-jp/graph/api/directory-post-administrativeunits) API を使用して、新しい制限付き管理管理単位を作成します。 `isMemberManagementRestricted` プロパティを `true`に設定します。

依頼

```http
POST https://graph.microsoft.com/v1.0/directory/administrativeUnits
```

本文

```http
{ 
  "displayName": "Contoso Executive Division",
  "description": "This administrative unit contains executive accounts of Contoso Corp.", 
  "isMemberManagementRestricted": true
}
```

---

### 管理単位を削除する

Microsoft Entra ID では、管理ロールのスコープの単位として不要になった管理単位を削除できます。 管理単位を削除する前に、その管理単位スコープを持つロールの割り当てを削除する必要があります。

## [管理センター](#tab/admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[ロールと管理者]**&gt;**[管理単位]** に移動します。
3. 削除する管理単位を選択します。
4. [ **ロールと管理者**] を選択し、ロールを開いてロールの割り当てを表示します。
5. 管理単位スコープを持つすべてのロールの割り当てを削除します。
6. **[Entra ID]**&gt;**[ロールと管理者]**&gt;**[管理単位]** に移動します。
7. 削除する管理単位の横にチェック マークを追加します。
8. [ **削除] を選択します**。

    [Image: 管理単位の [削除] ボタンと確認ウィンドウのスクリーンショット。]
9. 管理単位を削除することを確認するには、[はい]選択します。

## [PowerShell](#tab/ms-powershell)
[Remove-MgDirectoryAdministrativeUnit](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/remove-mgdirectoryadministrativeunit) コマンドを使用して、管理単位を削除します。

```powershell
$adminUnitObj = Get-MgDirectoryAdministrativeUnit -Filter "DisplayName eq 'Seattle District Technical Schools'"
Remove-MgDirectoryAdministrativeUnit -AdministrativeUnitId $adminUnitObj.Id
```

## [Graph API](#tab/ms-graph)
管理単位を削除するには、 [delete administrativeUnit](https://learn.microsoft.com/ja-jp/graph/api/administrativeunit-delete) API を使用します。

```http
DELETE https://graph.microsoft.com/v1.0/directory/administrativeUnits/{admin-unit-id}
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/admin-units-members-add"} -->
## ユーザー、グループ、またはデバイスを管理単位に追加する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-members-add
- Service: entra-id / role-based-access-control
- Article date: 2026-03-04
- Summary: Microsoft Entra ID の管理単位からユーザー、グループ、またはデバイスを追加する

Microsoft Entra ID で、管理単位にユーザー、グループ、またはデバイスを追加して、ロールのアクセス許可の範囲を制限できます。 管理単位にグループを追加すると、グループ自体は管理単位の管理スコープに入りますが、グループのメンバー **には含まれません** 。 スコープ管理者が実行できる操作の詳細については、「 [Microsoft Entra ID の管理単位」を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units)参照してください。

この記事では、ユーザー、グループ、またはデバイスを手動で管理単位に追加する方法について説明します。 ルールを使用してユーザーまたはデバイスを管理単位に動的に追加する方法については、「動的メンバーシップ グループの規則を使用して [管理単位のユーザーまたはデバイスを管理](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-members-dynamic)する」を参照してください。

### 前提条件

- 管理単位の各管理者ごとに Microsoft Entra ID P1 または P2 ライセンス
- 管理単位メンバーに対する Microsoft Entra ID Free ライセンス
- 既存のユーザー、グループ、またはデバイスを追加するには:
    - 特権ロール管理者
- 新しいグループを作成するには:
    - グループ管理者 (管理単位またはディレクトリ全体を対象とする)
- Microsoft Graph PowerShell モジュール は、PowerShell を使用する場合に使用されます。
- 管理者の同意 (Microsoft Graph API の Graph エクスプローラーを使用する場合)

詳細については、「powerShell または Graph Explorerを使用するための 前提条件」を参照してください。

## [管理センター](#tab/admin-center)
Microsoft Entra 管理センターを使用して、ユーザー、グループ、またはデバイスを管理単位に追加できます。 管理単位には、一括操作でユーザーを追加したり、新しいグループを作成したりすることもできます。

#### 管理単位に単一のユーザー、グループ、またはデバイスを追加する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **Entra ID** に移動します。
3. 次のいずれかに移動します。

    - **ユーザー**&gt;**すべてのユーザー**
    - **グループ**&gt;**すべてのグループ**
    - **デバイス**&gt;**すべてのデバイス**
4. 管理単位に追加するユーザー、グループ、またはデバイスを選択します。
5. [ **管理単位]** を選択します。
6. **管理単位に割り当てる**を選択します。
7. [ **選択** ] ウィンドウで、管理単位を選択し、[ **選択**] を選択します。

    [Image: ユーザーを管理単位に追加するための [管理単位] ページのスクリーンショット。]

#### ユーザー、グループ、またはデバイスを 1 つの管理単位に追加する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[ロールと管理者]**&gt;**[管理単位]** に移動します。
3. ユーザー、グループ、またはデバイスを追加する先の管理単位を選択します。
4. 次のいずれかを選択してください。

    - **ユーザー**
    - **グループ**
    - **デバイス**
5. [ **メンバーの追加]**、[ **追加]**、または **[デバイスの追加] を選択します**。
6. **[選択**] ウィンドウで、管理単位に追加するユーザー、グループ、またはデバイスを選択し、[選択] を**選択**します。

    [Image: 複数のデバイスを管理単位に追加するスクリーンショット。]

#### 一括操作で管理単位にユーザーを追加する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[ロールと管理者]**&gt;**[管理単位]** に移動します。
3. ユーザーを追加する先の管理単位を選択します。
4. **Users**&gt;**一括操作**&gt;**メンバーを一括追加** を選択します。

    [Image: ユーザーを一括操作として管理単位に割り当てるための [ユーザー] ページのスクリーンショット。]
5. [ **メンバーの一括追加** ] ウィンドウで、コンマ区切り値 (CSV) テンプレートをダウンロードします。
6. ダウンロードした CSV テンプレートを編集して、追加するユーザーの一覧を加えます。

    各行に 1 つのユーザー プリンシパル名 (UPN) を追加します。 テンプレートの最初の 2 行は削除しないでください。
7. 変更内容を保存し、CSV ファイルをアップロードします。

    [Image: ユーザーを管理単位に一括で追加するための編集済み CSV ファイルのスクリーンショット。]
8. [ **送信] を選択します**。

#### 管理単位に新しいグループを作成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[ロールと管理者]**&gt;**[管理単位]** に移動します。
3. 新しいグループを作成する先の管理単位を選びます。
4. **[グループ] を選択します**。
5. [ **新しいグループ** ] を選択し、新しいグループを作成する手順を完了します。

    [Image: 管理単位で新しいグループを作成するための [管理単位] ページのスクリーンショット。]

## [PowerShell](#tab/ms-powershell)
[New-MgDirectoryAdministrativeUnitMemberByRef](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/new-mgdirectoryadministrativeunitmemberbyref) コマンドを使用して、ユーザー、グループ、またはデバイスを管理単位に追加するか、管理単位に新しいグループを作成します。

#### 管理単位にユーザー追加する

```powershell
$adminUnitObj = Get-MgDirectoryAdministrativeUnit -Filter "DisplayName eq '{admin-unit-id}'"
$userObj = Get-MgUser -Filter "UserPrincipalName eq '{user-principal-name}'"
$odataId = "https://graph.microsoft.com/v1.0/users/" + $userObj.Id
New-MgDirectoryAdministrativeUnitMemberByRef -AdministrativeUnitId $adminUnitObj.Id -OdataId $odataId
```

#### 管理単位にグループを追加する

```powershell
$adminUnitObj = Get-MgDirectoryAdministrativeUnit -Filter "DisplayName eq '{admin-unit-id}'"
$groupObj = Get-MgGroup -Filter "DisplayName eq 'group-name'"
$odataId = "https://graph.microsoft.com/v1.0/groups/" + $groupObj.Id
New-MgDirectoryAdministrativeUnitMemberByRef -AdministrativeUnitId $adminUnitObj.Id -OdataId $odataId
```

#### 管理単位にデバイスを追加する

```powershell
$adminUnitObj = Get-MgDirectoryAdministrativeUnit -Filter "DisplayName eq '{admin-unit-id}'"
$odataId = "https://graph.microsoft.com/v1.0/devices/{device-id}"
New-MgDirectoryAdministrativeUnitMemberByRef -AdministrativeUnitId $adminUnitObj.Id -OdataId $odataId
```

#### 管理単位に新しいグループを作成する

```powershell
$adminUnitObj = Get-MgDirectoryAdministrativeUnit -Filter "DisplayName eq '{admin-unit-id}'"
$params = @{
    "@odata.type" = "#microsoft.graph.group"
    description = "{group-description}"
    displayName = "{group-name}"
    groupTypes = @(
        "Unified"
    )
    mailEnabled = $false
    mailNickname = "{group-name}"
    securityEnabled = $true
}
New-MgDirectoryAdministrativeUnitMember -AdministrativeUnitId $adminUnitObj.Id -BodyParameter $params
```

## [Graph API](#tab/ms-graph)
メンバーの [追加](https://learn.microsoft.com/ja-jp/graph/api/administrativeunit-post-members) API を使用して、ユーザー、グループ、またはデバイスを管理単位に追加するか、管理単位に新しいグループを作成します。

#### 管理単位にユーザー追加する

要求

```http
POST https://graph.microsoft.com/v1.0/directory/administrativeUnits/{admin-unit-id}/members/$ref
```

本文

```http
{
    "@odata.id":"https://graph.microsoft.com/v1.0/users/{user-id}"
}
```

例

```http
{
    "@odata.id":"https://graph.microsoft.com/v1.0/users/john@example.com"
}
```

#### 管理単位にグループを追加する

要求

```http
POST https://graph.microsoft.com/v1.0/directory/administrativeUnits/{admin-unit-id}/members/$ref
```

本文

```http
{
    "@odata.id":"https://graph.microsoft.com/v1.0/groups/{group-id}"
}
```

例

```http
{
    "@odata.id":"https://graph.microsoft.com/v1.0/groups/871d21ab-6b4e-4d56-b257-ba27827628f3"
}
```

#### 管理単位にデバイスを追加する

要求

```http
POST https://graph.microsoft.com/v1.0/directory/administrativeUnits/{admin-unit-id}/members/$ref
```

本文

```http
{
    "@odata.id":"https://graph.microsoft.com/v1.0/devices/{device-id}"
}
```

#### 管理単位に新しいグループを作成する

管理単位で新しいグループを直接作成するには、次の要求を使用します。 代わりに既存のグループを追加するには、この記事の「 **管理単位にグループを追加する** 」を参照してください。

要求

```http
POST https://graph.microsoft.com/v1.0/directory/administrativeUnits/{admin-unit-id}/members
```

本文

```http
{
    "@odata.type": "#Microsoft.Graph.Group",
    "description": "{Example group description}",
    "displayName": "{Example group name}",
    "groupTypes": [
        "Unified"
    ],
    "mailEnabled": true,
    "mailNickname": "{examplegroup}",
    "securityEnabled": false
}
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/admin-units-members-dynamic"} -->
## 動的なメンバーシップ グループの規則を使用して、管理単位のユーザーまたはデバイスを管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-members-dynamic
- Service: entra-id / role-based-access-control
- Article date: 2025-01-03
- Summary: Microsoft Entra ID で動的メンバーシップ グループのルールを使用して管理単位のユーザーまたはデバイスを管理する

管理単位のユーザーまたはデバイスを手動で追加または削除できます。 動的メンバーシップ グループでは、規則を使用した管理単位のユーザーまたはデバイスを動的に追加または削除できます。 この記事では、Microsoft Entra 管理センター、PowerShell、または Microsoft Graph API を使用して、動的メンバーシップ グループのルールが設定された管理単位を作成する方法について説明します。

注

管理単位の動的メンバーシップ規則を、動的メンバーシップ グループで使用できるのと同じ属性を使用して作成できます。 使用できる特定の属性とその使用方法の例の詳細については、「 [Microsoft Entra ID での動的メンバーシップ グループのルールの管理」を](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership)参照してください。

メンバーが割り当てられた管理単位では、ユーザー、グループ、デバイスなどの複数のオブジェクトの種類が手動でサポートされていますが、現在、複数のオブジェクトの種類を含む動的メンバーシップ グループの規則を持つ管理単位を作成することはできません。 たとえば、ユーザーまたはデバイスの動的メンバーシップの規則が設定された管理単位を作成できますが、両方はできません。 グループの動的メンバーシップ グループの規則が設定された管理単位は、現在サポートされていません。

### 前提条件

- 管理単位の各管理者ごとに Microsoft Entra ID P1 または P2 ライセンス
- 管理単位の各メンバーごとの Microsoft Entra ID P1 または P2 ライセンス
- 特権ロール管理者
- [PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) を使用する場合の Microsoft Graph PowerShell モジュール
- 管理者の同意 (Microsoft Graph API の Graph エクスプローラーを使用する場合)
- グローバル Azure クラウド (Azure Government や 21Vianet によって運営される Microsoft Azure などの特別なクラウドでは利用できません)

注

管理単位の動的メンバーシップ規則では、1 つ以上の動的管理単位のメンバーである一意のユーザーごとに Microsoft Entra ID P1 ライセンスが必要です。 ユーザーを動的管理単位のメンバーにするために、そのユーザーにライセンスを割り当てる必要はありません。ただし、少なくともそのすべてのユーザーを対象にできるだけのライセンス数が Microsoft Entra 組織に含まれている必要があります。 たとえば、組織のすべての動的管理単位に、合計 1,000 人の一意のユーザーがいる場合、ライセンス要件を満たすには、Microsoft Entra ID P1 に対するライセンスが 1,000 個以上必要です。 デバイスの動的メンバーシップ グループに対する管理単位のメンバーであるデバイスには、ライセンスは必要ありません。

詳細については、「powerShell または Graph Explorerを使用するための 前提条件」を参照してください。

### 動的メンバーシップ グループのルールを追加

次の手順に従って、ユーザーまたはデバイスの動的メンバーシップ グループの規則が設定された管理単位を作成します。

## [管理センター](#tab/admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. ユーザーまたはデバイスを追加する管理単位を選択します。
3. **[プロパティ] を選択します**。
4. [ **メンバーシップの種類** ] ボックスの一覧で、追加するルールの種類に応じて、[ **動的ユーザー** ] または [ **動的デバイス**] を選択します。

    [Image: [メンバーシップの種類] リストが表示された [管理単位のプロパティ] ページのスクリーンショット。]
5. [ **動的クエリの追加]** を選択します。
6. ルール ビルダーを使用して、動的メンバーシップ グループの規則を指定します。 詳細については、 [Azure portal のルール ビルダーを](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership#rule-builder-in-the-azure-portal)参照してください。

    [Image: プロパティ、演算子、値を含むルール ビルダーを示す [動的メンバーシップ ルール] ページのスクリーンショット。]
7. 完了したら、[ **保存]** を選択して、動的メンバーシップ グループのルールを保存します。
8. [ **プロパティ** ] ページで、[ **保存** ] を選択してメンバーシップの種類とクエリを保存します。

    次のメッセージが表示されます。

    管理単位の種類を変更した後、指定した動的メンバーシップ グループの規則に基づいて既存のメンバーシップが変更される可能性があります。
9. [ **はい** ] を選択して続行します。

ルールを編集する手順については、 次の「動的メンバーシップ グループのルールを編集する」 セクションを参照してください。

## [PowerShell](#tab/ms-powershell)
1. 動的メンバーシップ グループのルールを作成します。 詳細については、「 [Microsoft Entra ID での動的メンバーシップ グループのルールの管理」を](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership)参照してください。
2. [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) コマンドを使用して、特権ロール管理者ロールが割り当てられているユーザーと Microsoft Entra ID で接続します。

    ```powershell
    Connect-MgGraph -Scopes "AdministrativeUnit.ReadWrite.All"
    ```
3. [New-MgDirectoryAdministrativeUnit](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/new-mgdirectoryadministrativeunit) コマンドを使用して、次のパラメーターを使用して、動的メンバーシップ グループのルールを含む新しい管理単位を作成します。

    - `MembershipType` : `Dynamic` または `Assigned`
    - `MembershipRule`前の手順で作成した動的メンバーシップの規則
    - `MembershipRuleProcessingState` : `On` または `Paused`

    ```powershell
    # Create an administrative unit for users in the United States
    $params = @{
       displayName = "Example Admin Unit"
       description = "Example Dynamic Membership Admin Unit"
       membershipType = "Dynamic"
       membershipRule = "(user.country -eq 'United States')"
       membershipRuleProcessingState = "On"
    }
    
    New-MgDirectoryAdministrativeUnit -BodyParameter $params
    ```

## [Graph API](#tab/ms-graph)
1. 動的メンバーシップ グループのルールを作成します。 詳細については、「 [Microsoft Entra ID のグループの動的メンバーシップルール」を](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership)参照してください。
2. [Create administrativeUnit](https://learn.microsoft.com/ja-jp/graph/api/directory-post-administrativeunits) API を使用して、動的メンバーシップ グループのルールを含む新しい管理単位を作成します。

    次に、Windows デバイスに適用する動的メンバーシップ グループの規則の例を示します。

    要求

    ```http
    POST https://graph.microsoft.com/v1.0/directory/administrativeUnits
    ```

    本文

    ```http
    {
      "displayName": "Windows Devices",
      "description": "All Contoso devices running Windows",
      "membershipType": "Dynamic",
      "membershipRule": "(deviceOSType -eq 'Windows')",
      "membershipRuleProcessingState": "On"
    }
    ```

---

### 動的メンバーシップ グループのルールを編集

管理単位が動的メンバーシップ グループ用に構成されている場合、動的メンバーシップ グループ エンジンでは、メンバーの追加または削除の所有権のみが保持されるため、管理単位のメンバーを追加または削除するための通常のコマンドは無効になっています。 メンバーシップに変更を加えるには、動的メンバーシップ グループの規則を編集します。

## [管理センター](#tab/admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[ロールと管理者]**&gt;**[管理単位]** に移動します。
3. 編集する動的メンバーシップ グループのルールが設定された管理単位を選択します。
4. **ルール** ビルダーを使用して動的メンバーシップ グループのルールを編集するには、[メンバーシップ ルール] を選択します。

    [Image: ルール ビルダーを開く[メンバーシップ ルール] オプションと [動的メンバーシップ ルール] オプションを含む管理単位のスクリーンショット。]

    左側のナビゲーションで **[動的メンバーシップ** ルール] を選択して、ルール ビルダーを開くこともできます。
5. 完了したら、[ **保存]** を選択して、動的メンバーシップ グループルールの変更を保存します。

## [PowerShell](#tab/ms-powershell)
[Update-MgDirectoryAdministrativeUnit](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/update-mgdirectoryadministrativeunit) コマンドを使用して、動的メンバーシップ グループルールを編集します。

```powershell
# Set a new rules for dynamic membership groups for an administrative unit
$adminUnit = Get-MgDirectoryAdministrativeUnit -Filter "displayName eq 'Example Admin Unit'"
$params = @{
   membershipRule = "(user.country -eq 'Germany')"
}

Update-MgDirectoryAdministrativeUnit -AdministrativeUnitId $adminUnit.Id -BodyParameter $params
```

## [Graph API](#tab/ms-graph)
[Update administrativeUnit](https://learn.microsoft.com/ja-jp/graph/api/administrativeunit-update) API を使用して、動的メンバーシップ グループのルールを編集します。

要求

```http
PATCH https://graph.microsoft.com/v1.0/directory/administrativeUnits/{id}
```

本文

```http
{
  "membershipRule": "(user.country -eq "Germany")"
}
```

---

### 動的管理単位を割り当てられるように変更する

動的メンバーシップ グループの規則が設定された管理単位をメンバーが手動で割り当てられる管理単位に変更するには、次の手順に従います。

## [管理センター](#tab/admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[ロールと管理者]**&gt;**[管理単位]** に移動します。
3. 割り当てるために変更する管理単位を選択します。
4. **[プロパティ] を選択します**。
5. [ **メンバーシップの種類** ] ボックスの一覧 **で、[割り当て済み**] を選択します。

    [Image: [メンバーシップの種類] リストが表示され、[割り当て済み] が選択されている管理単位の [プロパティ] ページのスクリーンショット。]
6. [ **保存] を** 選択してメンバーシップの種類を保存します。

    次のメッセージが表示されます。

    管理単位の種類を変更した後は、動的な規則は処理されなくなります。 現在の管理単位メンバーは管理単位に残り、その管理単位にメンバーシップが割り当てられます。
7. [ **はい** ] を選択して続行します。

    メンバーシップの種類の設定を [動的] から [割り当て済み] に変更しても、現在のメンバーは管理単位内にそのまま維持されます。 さらに、管理単位にグループを追加する機能が有効になっています。

## [PowerShell](#tab/ms-powershell)
[Update-MgDirectoryAdministrativeUnit](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/update-mgdirectoryadministrativeunit) コマンドを使用して、動的メンバーシップ グループのルールを編集します。

```powershell
# Change an administrative unit to assigned
$adminUnit = Get-MgDirectoryAdministrativeUnit -Filter "displayName eq 'Example Admin Unit'"
$params = @{
   membershipRuleProcessingState = "Paused"
   membershipType = "Assigned"
}

Update-MgDirectoryAdministrativeUnit -AdministrativeUnitId $adminUnit.Id -BodyParameter $params
```

## [Graph API](#tab/ms-graph)
[Update administrativeUnit](https://learn.microsoft.com/ja-jp/graph/api/administrativeunit-update) API を使用して、メンバーシップの種類の設定を変更します。

要求

```http
PATCH https://graph.microsoft.com/v1.0/directory/administrativeUnits/{id}
```

本文

```http
{
  "membershipType": "Assigned"
}
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/admin-units-members-list"} -->
## 管理単位内のユーザー、グループ、またはデバイスを一覧表示する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-members-list
- Service: entra-id / role-based-access-control
- Article date: 2025-01-03
- Summary: 管理単位のユーザー、グループ、またはデバイスを Microsoft Entra ID で一覧表示します。

Microsoft Entra ID では、管理単位のユーザー、グループ、またはデバイスを一覧表示できます。

### 前提 条件

- 各管理単位管理者の Microsoft Entra ID P1 または P2 ライセンス
- 管理単位メンバー向けの Microsoft Entra ID 無料ライセンス
- [PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) を使用する場合の Microsoft Graph PowerShell モジュール
- Microsoft Graph API 用 Graph エクスプローラーを使用する場合の管理者の同意

詳細については、「powerShell または Graph Explorerを使用するための 前提条件」を参照してください。

## [管理センター](#tab/admin-center)
Microsoft Entra 管理センターを使用して、管理単位のユーザー、グループ、またはデバイスを一覧表示できます。

#### 1 人のユーザー、グループ、またはデバイスの管理単位を一覧表示する

1. Microsoft Entra 管理センター にサインインします。
2. **Entra ID** に移動します。
3. 次のいずれかに移動します。

    - **ユーザー**&gt;**すべてのユーザー**
    - **グループ**&gt;**すべてのグループ**
    - **デバイス**&gt;**すべてのデバイス**
4. 管理単位を一覧表示するユーザー、グループ、またはデバイスを選択します。
5. ユーザー **、** グループ、またはデバイスがメンバーであるすべての管理単位を一覧表示するには、[管理単位] を選択します。

    [Image: [管理単位] ページのスクリーンショット。グループが割り当てられている管理単位の一覧が表示されています。]

#### 1 つの管理単位のユーザー、グループ、またはデバイスを一覧表示する

1. Microsoft Entra 管理センター にサインインします。
2. **Entra ID**&gt;**ロール & 管理者**&gt;**管理単位**に移動します。
3. ユーザー、グループ、またはデバイスを一覧表示する管理単位を選択します。
4. 次のいずれかを選択します。

    - **ユーザー**
    - **グループ**
    - **デバイス**

    [Image: 管理単位のグループの一覧が表示されている [グループ] ページのスクリーンショット。]

#### [すべてのデバイス] ページを使用して管理単位のデバイスを一覧表示する

1. Microsoft Entra 管理センター にサインインします。
2. **Entra ID**&gt;**デバイス**&gt;**すべてのデバイス**を参照してください。
3. 管理単位のフィルターを選択します。
4. デバイスを一覧表示する管理単位を選択します。

    [Image: 管理単位フィルターを含む [すべてのデバイス] ページのスクリーンショット。]

#### 1 人のユーザーまたはグループの制限付き管理単位を一覧表示する

1. Microsoft Entra 管理センター にサインインします。
2. **Entra ID** に移動します。
3. 次のいずれかに移動します。

    - **ユーザー**&gt;**すべてのユーザー**
    - **グループ**&gt;**すべてのグループ**
4. 制限付き管理管理単位を一覧表示するユーザーまたはグループを選択します。
5. **ユーザーまたは**グループがメンバーであるすべての管理単位を一覧表示するには、[管理単位] を選択します。
6. [ **制限付き管理** ] 列で、[ **はい**] に設定されている管理単位を探します。

    [Image: [管理単位] ページのスクリーンショット。[制限付き管理] 列が表示されています。]

## [PowerShell](#tab/ms-powershell)
管理単位のユーザー、グループ、またはデバイスを一覧表示するには、 [Get-MgDirectoryAdministrativeUnit](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdirectoryadministrativeunit) コマンドと [Get-MgDirectoryAdministrativeUnitMember](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdirectoryadministrativeunitmember) コマンドを使用します。

手記

既定では、Get-MgDirectoryAdministrativeUnitMember  は、管理単位の上位メンバーのみを返します。 すべてのメンバーを取得するには、`-All:$true` パラメーターを追加します。

#### ユーザーの管理単位を一覧表示する

```powershell
$userObj = Get-MgUser -Filter "UserPrincipalName eq 'bill@example.com'"
Get-MgDirectoryAdministrativeUnit | `
   where { Get-MgDirectoryAdministrativeUnitMember -AdministrativeUnitId $_.Id | `
   where {$_.Id -eq $userObj.Id} }
```

#### グループの管理単位を一覧表示する

```powershell
$groupObj = Get-MgGroup -Filter "DisplayName eq 'TestGroup'"
Get-MgDirectoryAdministrativeUnit | `
   where { Get-MgDirectoryAdministrativeUnitMember -AdministrativeUnitId $_.Id | `
   where {$_.Id -eq $groupObj.Id} }
```

#### デバイスの管理単位を一覧表示する

```powershell
$deviceObj = Get-MgDevice -Filter "DisplayName eq 'Test device'"
Get-MgDirectoryAdministrativeUnit | `
   where { Get-MgDirectoryAdministrativeUnitMember -AdministrativeUnitId $_.Id | `
   where {$_.Id -eq $deviceObj.Id} }
```

#### 管理単位のユーザー、グループ、およびデバイスを一覧表示する

```powershell
$adminUnitObj = Get-MgDirectoryAdministrativeUnit -Filter "DisplayName eq 'Test administrative unit 2'"
Get-MgDirectoryAdministrativeUnitMember -AdministrativeUnitId $adminUnitObj.Id
```

#### 管理単位のグループを一覧表示する

```powershell
$adminUnitObj = Get-MgDirectoryAdministrativeUnit -Filter "DisplayName eq 'Test administrative unit 2'"
foreach ($member in (Get-MgDirectoryAdministrativeUnitMember -AdministrativeUnitId $adminUnitObj.Id)) 
{
    if($member.AdditionalProperties."@odata.type" -eq "#microsoft.graph.group")
    {
        Get-MgGroup -GroupId $member.Id
    }
}
```

#### 管理単位のデバイスを一覧表示する

```powershell
$adminUnitObj = Get-MgDirectoryAdministrativeUnit -Filter "DisplayName eq 'Test administrative unit 2'"
foreach ($member in (Get-MgDirectoryAdministrativeUnitMember -AdministrativeUnitId $adminUnitObj.Id)) 
{
    if($member.AdditionalProperties.ObjectType -eq "Device")
    {
        Get-MgDevice -DeviceId $member.Id
    }
}
```

## [Graph API](#tab/ms-graph)
#### ユーザーの管理単位を一覧表示する

ユーザーが直接メンバーである管理単位を一覧表示するには、ユーザー List [memberOf](https://learn.microsoft.com/ja-jp/graph/api/user-list-memberof) API を使用します。

```http
GET https://graph.microsoft.com/v1.0/users/{user-id}/memberOf/$/Microsoft.Graph.AdministrativeUnit
```

#### グループの管理単位を一覧表示する

Group [List memberOf](https://learn.microsoft.com/ja-jp/graph/api/group-list-memberof) API を使用して、グループが直接メンバーである管理単位を一覧表示します。

```http
GET https://graph.microsoft.com/v1.0/groups/{group-id}/memberOf/$/Microsoft.Graph.AdministrativeUnit
```

#### デバイスの管理単位を一覧表示する

[List device memberships](https://learn.microsoft.com/ja-jp/graph/api/device-list-memberof) API を使用して、デバイスが直接メンバーである管理単位を一覧表示します。

```http
GET https://graph.microsoft.com/v1.0/devices/{device-id}/memberOf/$/Microsoft.Graph.AdministrativeUnit
```

#### 管理単位のユーザー、グループ、またはデバイスを一覧表示する

[List メンバー](https://learn.microsoft.com/ja-jp/graph/api/administrativeunit-list-members) API を使用して、管理単位のユーザー、グループ、またはデバイスを一覧表示します。 メンバー型には、`microsoft.graph.user`、`microsoft.graph.group`、または `microsoft.graph.device`を指定します。

```http
GET https://graph.microsoft.com/v1.0/directory/administrativeUnits/{admin-unit-id}/members/$/microsoft.graph.group
```

#### 1 人のユーザーが制限付き管理単位に含まれるかどうかを一覧表示する

ユーザーの [取得 (ベータ)](https://learn.microsoft.com/ja-jp/graph/api/user-get?view=graph-rest-beta&preserve-view=true) API を使用して、ユーザーが制限付きの管理管理単位に含まれているかどうかを判断します。 `isManagementRestricted` プロパティの値を見てください。 プロパティが `true`されている場合は、制限付きの管理管理単位に含まれます。 プロパティが `false`、空、または null の場合は、制限付き管理単位に含まれません。

```http
GET https://graph.microsoft.com/beta/users/{user-id}
```

応答

```
{ 
  "displayName": "John",
  "isManagementRestricted": true,
  "userPrincipalName": "john@contoso.com", 
}
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/admin-units-members-remove"} -->
## 管理単位からユーザー、グループ、またはデバイスを削除する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-members-remove
- Service: entra-id / role-based-access-control
- Article date: 2025-01-03
- Summary: Microsoft Entra ID の管理単位からユーザー、グループ、またはデバイスを削除する

管理単位内のユーザー、グループ、またはデバイスにアクセスする必要がなくなったら、それらを削除できます。

### 前提条件

- 管理単位の各管理者ごとに Microsoft Entra ID P1 または P2 ライセンス
- 管理単位メンバーに対する Microsoft Entra ID Free ライセンス
- 特権ロール管理者
- [PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) を使用する場合の Microsoft Graph PowerShell モジュール
- 管理者の同意 (Microsoft Graph API の Graph エクスプローラーを使用する場合)

詳細については、「powerShell または Graph Explorerを使用するための 前提条件」を参照してください。

## [管理センター](#tab/admin-center)
Microsoft Entra 管理センターを使用して、管理単位からユーザー、グループ、またはデバイスを個別に削除できます。 一括操作でユーザーを削除することもできます。

#### 管理単位から単一のユーザー、グループ、またはデバイスを削除する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **Entra ID** に移動します。
3. 次のいずれかに移動します。

    - **ユーザー**&gt;**すべてのユーザー**
    - **グループ**&gt;**すべてのグループ**
    - **デバイス**&gt;**すべてのデバイス**
4. 管理単位から削除するユーザー、グループ、またはデバイスを選択します。
5. [ **管理単位]** を選択します。
6. ユーザー、グループ、またはデバイスを削除する管理単位の横にチェック マークを追加します。
7. [ **管理単位から削除] を選択します**。

    [Image: [管理単位から削除] オプションが表示された [デバイスと管理単位] ページのスクリーンショット。]

#### 単一の管理単位からユーザー、グループ、またはデバイスを削除する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[ロールと管理者]**&gt;**[管理単位]** に移動します。
3. ユーザー、グループ、またはデバイスを削除する対象の管理単位を選択します。
4. 次のいずれかを選択してください。

    - **ユーザー**
    - **グループ**
    - **デバイス**
5. 削除するユーザー、グループ、またはデバイスの横にチェック マークを追加します。
6. [ **メンバーの削除]**、[ **削除**]、または **[デバイスの削除] を選択します**。

    [Image: チェック マークと [メンバーの削除] オプションを含む管理単位のユーザーの一覧を示すスクリーンショット。]

#### 一括操作で管理単位からユーザーを削除する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[ロールと管理者]**&gt;**[管理単位]** に移動します。
3. ユーザーを削除する対象の管理単位を選択します。
4. **Users**&gt;**一括操作**&gt;**メンバーを一括で削除**を選択します。

    [Image: [メンバーの一括削除] リンクを示す [ユーザー] ページのスクリーンショット。]
5. [ **メンバーの一括削除** ] ウィンドウで、コンマ区切り値 (CSV) テンプレートをダウンロードします。
6. ダウンロードした CSV テンプレートを、削除するユーザーの一覧で編集します。

    各行に 1 つのユーザー プリンシパル名 (UPN) を追加します。 テンプレートの最初の 2 行は削除しないでください。
7. 変更内容を保存し、CSV ファイルをアップロードします。
8. [ **送信] を選択します**。

## [PowerShell](#tab/ms-powershell)
[Remove-MgDirectoryAdministrativeUnitMemberByRef](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/remove-mgdirectoryadministrativeunitmemberbyref) コマンドを使用して、管理単位からユーザー、グループ、またはデバイスを削除します。

#### 管理単位からユーザーを削除する

```powershell
$adminUnitObj = Get-MgDirectoryAdministrativeUnit -Filter "DisplayName eq 'Test administrative unit 2'"
$userObj = Get-MgUser -Filter "UserPrincipalName eq 'bill@example.com'"
Remove-MgDirectoryAdministrativeUnitMemberByRef -AdministrativeUnitId $adminUnitObj.Id -DirectoryObjectId $userObj.Id
```

#### 管理単位からグループを削除する

```powershell
$adminUnitObj = Get-MgDirectoryAdministrativeUnit -Filter "DisplayName eq 'Test administrative unit 2'"
$groupObj = Get-MgGroup -Filter "DisplayName eq 'TestGroup'"
Remove-MgDirectoryAdministrativeUnitMemberByRef -AdministrativeUnitId $adminUnitObj.Id -DirectoryObjectId $groupObj.Id
```

#### 管理単位からデバイスを削除する

```powershell
Remove-MgDirectoryAdministrativeUnitMemberByRef -AdministrativeUnitId $adminUnitObj.Id -DirectoryObjectId $deviceObj.Id
```

## [Graph API](#tab/ms-graph)
メンバーの [削除 API を](https://learn.microsoft.com/ja-jp/graph/api/administrativeunit-delete-members) 使用して、管理単位からユーザー、グループ、またはデバイスを削除します。 `{member-id}` について、ユーザー、グループ、またはデバイス ID を指定します。

```http
DELETE https://graph.microsoft.com/v1.0/directory/administrativeUnits/{admin-unit-id}/members/{member-id}/$ref
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/admin-units-restricted-management"} -->
## Microsoft Entra IDの制限付き管理者用管理単位 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-restricted-management
- Service: entra-id / role-based-access-control
- Article date: 2026-03-04
- Summary: Microsoft Entra IDの機密性の高いリソースには、制限付き管理管理単位を使用します。

組織には、CEO のユーザー アカウントなど、厳密なセキュリティを必要とするリソースがあります。 現時点では、ヘルプデスク管理者はパスワードをリセットすることで CEO のアカウントにアクセスできる可能性があり、テナント レベルのグループ管理者は、SharePointの財務データ アクセス権を持つセキュリティ グループにユーザーを追加できます。

制限付き管理単位を使用すると、指定した特定のユーザー セット以外のユーザーによる変更から、テナント内の特定のオブジェクトを保護できます。 これにより、管理者からテナント レベルのロールの割り当てを削除しなくても、セキュリティまたはコンプライアンスの要件を満たせます。

### 制限付き管理の管理単位をなぜ使用するのですか。

テナント内のアクセス管理に制限付き管理の管理単位を使用する理由をいくつか示します。

- **エグゼクティブ アカウントとそのデバイスを保護する**

    パスワードをリセットしたり、BitLocker 回復キーにアクセスしたりができるヘルプデスク管理者から、C レベルのエグゼクティブ アカウントとそのデバイスを保護する必要があります。 制限付き管理管理単位に C レベルのユーザー アカウントを追加し、パスワードをリセットし、必要に応じて BitLocker 回復キーにアクセスできる特定の信頼された管理者のセットを有効にすることができます。
- **ローカル管理者のみにコンプライアンス制御を実装する**

    特定のリソースを特定の国/地域の管理者のみが管理できるように、コンプライアンスコントロールを実装する必要があります。 これらのリソースを制限付き管理の管理単位に追加し、ローカル管理者を割り当ててそれらのオブジェクトを管理できます。 グローバル管理者でも、制限付き管理の管理ユニット (監査可能なイベント) をスコープとするロールに明示的に割り当てられない限り、オブジェクトを変更することはできません。
- **機密性の高いセキュリティ グループの管理を特定の管理者に制限する**

    セキュリティ グループを使用して、組織内の機密性の高いアプリケーションへのアクセスを制御していますが、グループを変更できるテナント スコープの管理者が、アプリケーションにアクセスできるユーザーを制御できるようにしたくないと考えています。 これらのセキュリティ グループを制限付き管理の管理単位に追加し、割り当てた特定の管理者のみがそれらを管理できるようにすることができます。

### サンプル シナリオ

次の図は、役員サポートによってのみ変更できるオブジェクトを含むエグゼクティブ制限付き管理単位 (紫色のボックスに表示) の例を示しています。 テナント レベルの管理者とローカル管理者は、Executive 管理単位のオブジェクトを変更できません。

[Image: 役員サポートによってのみ変更できるオブジェクトを含む、エグゼクティブ制限付き管理管理単位の例の図。]

注

制限付き管理管理単位にオブジェクトを配置すると、オブジェクトに変更を加えることができるユーザーが厳しく制限されます。 この制限により、既存のワークフローが中断する可能性があります。

### メンバーにできるオブジェクトはどれですか?

制限付き管理の管理単位のメンバーにできるオブジェクトを次に示します。

| Microsoft Entra オブジェクトの種類 | 管理単位 | 制限付き管理管理単位 |
| --- | --- | --- |
| ユーザー | あり | あり |
| デバイス | あり | あり |
| グループ (セキュリティ) | あり | あり |
| グループ (Microsoft 365) | あり | いいえ |
| グループ (メールが有効なセキュリティ) | あり | いいえ |
| グループ (配布) | あり | いいえ |

### ブロックされる操作の種類は何ですか?

制限付き管理単位スコープで明示的に割り当てられていない管理者の場合、制限付き管理単位内のオブジェクトのMicrosoft Entraプロパティを直接変更する操作はブロックされますが、Microsoft 365 サービス内の関連オブジェクトに対する操作は影響を受けません。

| 操作の種類 | ブロックされました | 許可されます。 |
| --- | --- | --- |
| ユーザー プリンシパル名、ユーザー写真などの標準プロパティを読み取る |  | ✅ |
| ユーザー、グループ、またはデバイスのMicrosoft Entraプロパティを変更する | ❌ |  |
| ユーザー、グループ、またはデバイスを削除する | ❌ |  |
| ユーザーのパスワードを更新する | ❌ |  |
| 制限付き管理の管理単位でグループの所有者またはメンバーを変更する | ❌ |  |
| 制限付き管理単位のユーザー、グループ、またはデバイスをMicrosoft Entra IDのグループに追加する |  | ✅ |
| 制限付き管理の管理単位のユーザーの Exchange のメールとメールボックス設定を変更する |  | ✅ |
| Intune を使用して、制限付き管理の管理単位のデバイスにポリシーを適用する |  | ✅ |
| SharePointでサイト所有者としてグループを追加または削除する |  | ✅ |
| 制限付き管理単位でライセンスを割り当て、ユーザーの使用場所を更新する |  | ✅ |

### オブジェクトを変更できるユーザーは誰ですか?

制限付き管理管理単位のスコープで明示的に割り当てられている管理者のみが、制限付き管理管理単位内のオブジェクトのMicrosoft Entraプロパティを変更できます。

| 役割 | Scope | ブロックされました | 許可されます。 |
| --- | --- | --- | --- |
| グローバル管理者 | テナント | ❌ |  |
| 特権ロール管理者 | テナント | ❌ |  |
| グループ管理者、ユーザー管理者、またはその他のロール | リソース | ❌ |  |
| 制限付き管理の管理単位に追加されたグループまたはデバイスの所有者 |  | ❌ |  |
| 組み込みロールまたはカスタム ロール | テナント | ❌ |  |
| [管理単位スコープで割り当てることができるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal#roles-that-can-be-assigned-with-administrative-unit-scope) | 制限付き管理管理単位 |  | ✅ |
| [管理単位スコープで割り当てることができるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal#roles-that-can-be-assigned-with-administrative-unit-scope) | オブジェクトがメンバーである別の制限付き管理管理単位 |  | ✅ |
| [管理単位スコープで割り当てることができるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal#roles-that-can-be-assigned-with-administrative-unit-scope) | オブジェクトがメンバーである別の通常の管理単位 | ❌ |  |

テナント スコープを持つ管理者が、制限付き管理管理単位内のオブジェクトを変更しようとすると、次のようなメッセージが表示されます。

`This user is a member of a restricted management administrative unit. Management rights are limited to administrators scoped on that administrative unit.`

`This group is a member of restricted management administrative unit. Management rights are limited to administrators scoped on that administrative unit.`

ユーザーとデバイスの場合、このメッセージは **[概要** ] ページに表示されます。 グループの場合、このメッセージは **[メンバー** ] ページに表示されます。

[Image: ユーザーが制限付き管理単位のメンバーであり、管理権限が制限されていることを示すメッセージのスクリーンショット。]

### 制限付き管理単位を管理できるのは誰ですか？

テナント スコープの次のロール **は、** 制限付き管理管理単位内のオブジェクトを変更できませんが、制限付き管理単位自体を管理 **できます** 。

| 役割 | Scope | 制限付き管理管理単位のオブジェクトを変更する | 制限付き管理管理単位の管理 |
| --- | --- | --- | --- |
| グローバル管理者 | テナント | いいえ | あり |
| 特権ロール管理者 | テナント | いいえ | あり |

この管理には、次のタスクが含まれます。

- 制限付き管理管理単位を作成または削除する
- 制限付き管理管理単位のメンバーを追加または削除する
- 管理単位のスコープが制限されている場合のロール割り当てやロール割り当ての解除
- 制限付き管理の管理単位のスコープを持つロールを自分自身に割り当てる

管理単位のスコープが制限されている管理者がジョブを変更したり、組織を離れたりした場合、アクセスを回復するために、グローバル管理者または特権ロール管理者は、制限付き管理単位に別の管理者または自分自身を割り当てることができます。

### 監査ログ

制限付き管理管理単位に変更が加えられたタイミングを追跡するために、これらのアクティビティはMicrosoft Entra監査ログに記録されます。

| 活動 | カテゴリ | 詳細 |
| --- | --- | --- |
| 管理単位の追加 | AdministrativeUnit | `IsMemberManagementRestricted` = 真 |
| 制限付き管理の管理単位にメンバーを追加する | AdministrativeUnit |  |
| 制限付き管理の管理単位からメンバーを削除する | AdministrativeUnit |  |
| 制限付き管理の管理単位をスコープとするロールにメンバーを追加する | 役割管理 |  |
| 制限付き管理の管理単位をスコープとするロールからメンバーを削除する | 役割管理 |  |

### 制限事項

制限付き管理の管理単位における制限と制約の一部を次に示します。

- 制限付き管理の設定は管理単位の作成時に適用する必要があり、管理単位を作成した後は変更できません。
- 制限付き管理単位のグループとユーザーは、Microsoft Entra ID Governance の機能、例えば [Privileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/groups-discover-groups)、[Entitlement management](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)、[Lifecycle workflows](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows)、および [アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview) では管理できません。
- グループがパブリック メンバーシップを持つように構成されている場合 ( [visibility](https://learn.microsoft.com/ja-jp/graph/api/resources/group#properties) プロパティを `Public` に設定することで)、ユーザーは [セルフサービス グループ メンバーシップ](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management)を使用してグループに参加できます。 この構成は既定の設定ではなく、パブリック メンバーシップを許可するように制限付き管理単位のグループを構成することはお勧めしません。 これは一時的な制限であり、削除されます。
- 制限付き管理の管理単位に追加されたロール割り当て可能なグループは、メンバーシップを変更できません。 グループ所有者は、制限付き管理の管理単位でグループを管理することはできず、グローバル管理者と特権ロール管理者 (どちらも管理単位のスコープで割り当てることはできません) のみがメンバーシップを変更できます。
- オブジェクトが制限付き管理の管理単位にあり、必要なロールが管理単位のスコープで割り当てることができるロールの 1 つではない場合、特定のアクションを実行できないことがあります。 たとえば、制限付き管理単位のグローバル管理者は、グローバル管理者のパスワードをリセットできる管理者ロールが管理単位スコープで割り当てられないため、システム内の他の管理者がパスワードをリセットすることはできません。 このようなシナリオでは、グローバル管理者は、まず制限付き管理の管理単位から削除してから、別の全体管理者または特権ロール管理者によりパスワードをリセットする必要があります。
- 制限付き管理の管理単位を削除する場合、以前のメンバーからすべての保護を削除するまでに最大 30 分かかる場合があります。
- テナント内の制限付き管理単位は最大 100 個です。

### プログラミング

既定では、アプリケーションは、制限付き管理の管理単位内のオブジェクトを変更できません。 制限付き管理管理単位内のオブジェクトを管理するためのアクセス権をアプリケーションに付与するには、制限付き管理単位のスコープでアプリケーションにMicrosoft Entraロールを割り当てる必要があります。 [Microsoft Graphアプリケーションのアクセス許可](https://learn.microsoft.com/ja-jp/graph/permissions-reference)をアプリケーションに割り当てると、それらのアクセス許可は制限されているため適用されません。

### ライセンスの要件

制限付き管理単位には、各管理単位管理者に対して Microsoft Entra ID P1 ライセンスと、管理単位メンバー用の無料ライセンスMicrosoft Entra ID必要があります。 ご自分の要件に対して適切なライセンスを探すには、[一般公開されている Free および Premium エディションの機能比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)に関するページをご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/administrative-units"} -->
## Microsoft Entra ID の管理単位 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units
- Service: entra-id / role-based-access-control
- Article date: 2024-10-29
- Summary: より細かいレベルでアクセス許可を委任できるように、Microsoft Entra ID で管理単位を使います。

この記事では、Microsoft Entra ID の管理単位について説明します。 管理単位は、他の Microsoft Entra リソースのコンテナーとして使用できる Microsoft Entra リソースです。 管理単位には、ユーザー、グループ、デバイスのみを含めることができます。

管理単位では、ロールのアクセス許可が、組織の任意の定義部分に限定されます。 たとえば、管理単位を使用して、[ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator)のロールを地域のサポート スペシャリストに委任できます。そうすれば、そのスペシャリストが自分のサポートするリージョンのユーザーのみを管理できます。 なお、管理単位のメンバーではないユーザーにロールを割り当てる場合、そのロールのスコープはテナント全体になります。

ユーザーは複数の管理単位のメンバーにすることができます。 たとえば、地域別や部門別で管理単位にユーザーを追加できます。Megan Bowen は、管理単位の "Seattle" や "Marketing" に属することがあります。

### デプロイ シナリオ

あらゆる種類の独立した部門で構成される組織では、管理単位を使用して管理スコープを制限することが役立つ可能性があります。 多数の自律的な学部 (経営学部、工学部など) で構成される大きな大学の例を考えてみましょう。 各学部には、アクセスを制御し、ユーザーを管理し、学部のポリシーを設定する IT 管理者のチームがあります。

全体管理者は、以下を行うことができます。

- 経営学部の管理単位を作成する。
- 管理単位に経営学部内の学生とスタッフのみを設定する。
- 経営学部管理単位内の Microsoft Entra ユーザーのみを対象とする管理アクセス許可を含むロールを作成する。
- 経営学部の IT チームを役割とその範囲に追加します。

[Image: 1 つの管理単位をスコープとするロールの割り当てを持つ複数の管理単位の例を示す図。]

### 制約

管理単位の制約の一部を次に示します。

- 管理単位を入れ子にすることはできません。
- 現在、管理単位は [Microsoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview)では使用できません。

### グループ

管理単位にグループを追加すると、グループ自体は管理単位の管理スコープに入れられますが、グループのメンバーには入れられ**ません**。 つまり、管理単位をスコープとする管理者は、グループ名やメンバーシップなどのグループのプロパティを管理できますが、そのグループ内のユーザーやデバイスのプロパティは管理できません (それらのユーザーとデバイスが管理単位のメンバーとして個別に追加されている場合を除きます)。

たとえば、グループを含む管理単位をスコープとする[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)が実行できる操作と実行できない操作を次に示します。

| アクセス許可 | できること |
| --- | --- |
| グループの名前を管理する | ✅ |
| グループのメンバーシップを管理する | ✅ |
| グループの個々の**メンバー**のユーザー プロパティを管理する | ❌ |
| グループの個々の**メンバー**のユーザー認証方法を管理する | ❌ |
| グループの個々の**メンバー**のパスワードをリセットする | ❌ |

[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)がグループの個々のメンバーのユーザー プロパティまたはユーザー認証方法を管理するには、グループ メンバー (ユーザー) を管理単位のメンバーとして直接追加する必要があります。

### ライセンスの要件

管理単位を使用するには、管理単位のスコープを介してロールを直接割り当てられている管理単位の各管理者に Microsoft Entra ID P1 ライセンスが必要です。また、管理単位の各メンバーには Microsoft Entra ID Free ライセンスが必要です。 管理単位の作成は、Microsoft Entra ID Free ライセンスで行うことができます。 管理単位に動的メンバーシップ グループのルールを使用している場合は、各管理単位メンバーに Microsoft Entra ID P1 ライセンスが必要です。 要件に合ったライセンスを見つけるには、「[一般公開されている無料と Premium Edition の機能の比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)」を参照してください。

### 管理単位を管理する

管理単位は、Microsoft Entra 管理センター、PowerShell コマンドレットとスクリプト、または Microsoft Graph API を使用して管理できます。 詳細については、以下を参照してください:

- [管理単位の作成または削除](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-manage)
- [ユーザー、グループ、デバイスを管理単位に追加する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-members-add)
- [動的メンバーシップ グループの規則を使用して管理単位のユーザーまたはデバイスを管理する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-members-dynamic)
- [管理単位スコープを使って Microsoft Entra ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)
- [管理単位を使用する](https://learn.microsoft.com/ja-jp/powershell/azure/active-directory/working-with-administrative-units): PowerShell を使用して管理単位を操作する方法が記載されています。
- [管理単位での Graph のサポート](https://learn.microsoft.com/ja-jp/graph/api/resources/administrativeunit): Microsoft Graph で管理単位を使用するための詳細なドキュメントが提供されています。

#### 管理単位を計画する

管理単位を使用して、Microsoft Entra リソースを論理的にグループ化できます。 IT 部門がグローバルに分散している組織では、適切な地理的境界を定義する管理単位を作成するでしょう。 グローバルな組織に、業務において半自律的なサブ組織がある別のシナリオでは、管理単位でサブ組織を表すことができます。

管理単位を作成する条件は、組織固有の要件によって決まります。 管理単位は、Microsoft 365 サービスをまたがる構造を定義するための一般的な方法です。 Microsoft 365 サービスをまたがる使用を考慮して管理単位を準備することをお勧めします。 管理単位の下にある Microsoft 365 間で共通のリソースを関連付けることができる場合は、管理単位から最大の価値を得ることができます。

次の段階に進むには、組織内の管理単位の作成が予想されます。

1. **初期導入**: 組織は初期条件に基づいて管理単位の作成を開始しますが、条件の適用範囲を絞り込むにつれて管理単位の数が増加していきます。
2. **排除**: 条件を定義すると、不要になった管理単位が削除されます。
3. **安定化**: 組織の構造が定義されており、管理単位の数が短期間で大幅に変更されることはありません。

### 現在サポートされているシナリオ

特権ロール管理者は、Microsoft Entra 管理センターを使って次のことができます。

- 管理単位を管理する
- ユーザー、グループ、デバイスを管理単位のメンバーとして追加する
- 動的メンバーシップ グループの規則を使用して管理単位のユーザーまたはデバイスを管理する
- 管理単位を対象範囲とする管理者の役割に IT スタッフを割り当てます。

管理単位スコープの管理者は、Microsoft 365 管理センターを使用して、管理単位内のユーザーの基本的な管理を行うことができます。 管理単位のスコープであるグループ管理者は、PowerShell、Microsoft Graph、および Microsoft 365 管理センターを使用してグループを管理できます。

管理単位では、管理アクセス許可にのみスコープが適用されます。 メンバーまたは管理者が[既定のユーザー アクセス許可](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions)を使用して管理単位外の他のユーザー、グループ、またはリソースを参照するのを妨げられることはありません。 Microsoft 365 管理センターでは、スコープ管理者の管理単位外のユーザーは除外されます。ただし、Microsoft Entra 管理センター、PowerShell、およびその他の Microsoft サービスで他のユーザーを参照することはできます。

注

Microsoft 365 管理センターでは、このセクションで説明されている機能のみを使用できます。 管理単位のスコープである Microsoft Entra ロールでは、組織レベルの機能は使用できません。

以下のセクションでは、さまざまな管理単位シナリオに対する現在のサポート状況について説明しています。

#### 管理単位管理

| アクセス許可 | Microsoft Graph/PowerShell | Microsoft Entra 管理センター | Microsoft 365 管理センター |
| --- | --- | --- | --- |
| 管理単位を作成または削除する | ✅ | ✅ | ✅ |
| メンバーの追加または削除 | ✅ | ✅ | ✅ |
| 管理単位を対象範囲とする管理者を割り当てる | ✅ | ✅ | ✅ |
| ルールに基づいた動的なユーザーまたはデバイスの追加または削除 | ✅ | ✅ | ❌ |
| グループをルールに基づいてダイナミックに追加または削除する | ❌ | ❌ | ❌ |

#### [ユーザー管理]

| アクセス許可 | Microsoft Graph/PowerShell | Microsoft Entra 管理センター | Microsoft 365 管理センター |
| --- | --- | --- | --- |
| 管理単位を対象範囲とするユーザー プロパティ、パスワードの管理 | ✅ | ✅ | ✅ |
| 管理単位を対象範囲とするユーザー ライセンスの管理 | ✅ | ✅ | ✅ |
| 管理単位を対象範囲とするユーザー サインインのブロックとブロック解除 | ✅ | ✅ | ✅ |
| ユーザーの多要素認証資格情報に対する、管理単位スコープでの管理 | ✅ | ✅ | ❌ |

#### グループの管理

| アクセス許可 | Microsoft Graph/PowerShell | Microsoft Entra 管理センター | Microsoft 365 管理センター |
| --- | --- | --- | --- |
| グループの管理単位スコープでの作成と削除 | ✅ | ✅ | ✅ |
| Microsoft 365 グループのグループ プロパティおよびメンバーシップの管理単位スコープでの管理 | ✅ | ✅ | ✅ |
| 他のすべてのグループのグループ プロパティおよびメンバーシップの管理単位スコープでの管理 | ✅ | ✅ | ❌ |
| 管理単位を対象範囲とするグループ ライセンスの管理 | ✅ | ✅ | ❌ |

#### デバイス管理

| アクセス許可 | Microsoft Graph/PowerShell | Microsoft Entra 管理センター | Microsoft 365 管理センター |
| --- | --- | --- | --- |
| デバイスを有効化、無効化、または削除する | ✅ | ✅ | ❌ |
| BitLocker 回復キーを読む | ✅ | ✅ | ❌ |

現時点では、Intune でのデバイスの管理はサポートされて*いません*。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/best-practices"} -->
## Microsoft Entra ロールのベスト プラクティス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/best-practices
- Service: entra-id / role-based-access-control
- Article date: 2026-06-01
- Summary: Microsoft Entra ロールの使用に関するベスト プラクティス。

この記事では、Microsoft Entra のロールベースのアクセス制御 (Microsoft Entra RBAC) を使用するためのベスト プラクティスについて説明します。 これらのベスト プラクティスは、Microsoft Entra RBAC に関して Microsoft が蓄積してきたノウハウと、ユーザーの皆様の経験に基づいています。 Microsoft [Entra ID でのハイブリッドおよびクラウドデプロイの特権アクセスのセキュリティ保護に関](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-planning)する詳細なセキュリティ ガイダンスも参照することをお勧めします。

### 1. 最小特権の原則を適用する

アクセスの制御戦略を計画するときは、最小特権を管理することをお勧めします。 最小特権とは、ジョブを実行するために必要な特権を管理者に正確に付与することを意味します。 管理者にロールを割り当てる際に考慮すべき 3 つのアスペクトがあります。特定の期間、特定のスコープに対する、特定の権限セットです。 当初、より広範なスコープでより広範なロールを割り当てる方が便利に思われたとしても、避けてください。 ロールとスコープを制限することで、セキュリティ プリンシパルが侵害された場合にリスクにさらされるリソースを制限することができます。 Microsoft Entra RBAC では、65 を超える [組み込みロールが](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)サポートされています。 Microsoft Entra には、ユーザー、グループ、アプリケーションなどのディレクトリ オブジェクトを管理したり、Exchange、SharePoint、Intune などの Microsoft 365 サービスを管理したりするためのロールがあります。 Microsoft Entra の組み込みロールについて詳しくは、「 [Microsoft Entra ID のロールについて」](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/concept-understand-roles)をご覧ください。 ニーズを満たす組み込みロールがない場合は、独自の [カスタム ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)を作成できます。

#### 適切なロールを見つける

適切なロールを見つけるには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**ロールと管理者**&gt;**すべてのロール**に移動します。
3. **サービス** フィルターを使用して、ロールの一覧を絞り込みます。

    [Image: サービス フィルターが開いている管理センターの [ロールと管理者] ページ。]
4. [Microsoft Entra の組み込みロールのドキュメントを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。 各ロールに関連付けられている権限は、読みやすくするためにまとめて表示されます。 ロールのアクセス許可の構造と意味を理解するには、「ロールのアクセス [許可を理解する方法」を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#how-to-understand-role-permissions)参照してください。
5. [タスク別の最小特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task)のドキュメントを参照してください。

### 2. Privileged Identity Management を使用して Just-In-Time アクセスを付与する

最小特権の原則の 1 つは、必要なときにのみアクセス許可を付与することです。 [Microsoft Entra Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) を使用すると、管理者に Just-In-Time アクセス権を付与できます。 Microsoft Entra ID で PIM を使用することをお勧めします。 PIM を使用すると、ユーザーは Microsoft Entra ロールの対象となり、必要に応じて限られた期間だけロールをアクティブ化できるようになります。 期間が経過すると、特権アクセスは自動的に削除されます。 また、承認を必須にしたり、誰かがロールの割り当てをアクティブ化したときに通知メールを受信したり、その他のロール設定を行うように PIM 設定を構成することもできます。 通知により、高度な特権ロールに新しいユーザーが追加された場合にアラートを受け取れます。 詳細については、「 [Privileged Identity Management で Microsoft Entra ロール設定を構成](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings)する」を参照してください。

### 3. すべての管理者アカウントに対して多要素認証を有効にする

[調査によると](https://techcommunity.microsoft.com/t5/azure-active-directory-identity/your-pa-word-doesn-t-matter/ba-p/731984)、多要素認証 (MFA) を使用する場合、アカウントは 99.9% 侵害される可能性が低くなります。

次の 2 つの方法を使用して、Microsoft Entra ロールで MFA を有効にすることができます。

- Privileged Identity Management の[ロール設定](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings)
- [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-old-require-mfa-admin)

### 4. 定期的なアクセス レビューを構成して、時間の経過に伴う不要なアクセス許可を取り消す

アクセス レビューを使用すると、組織は管理者のアクセス権を定期的に確認して、適切なユーザーのみが継続的なアクセス権を持つようにすることができます。 通常の監査は、次の理由により、管理者が非常に重要です。

- 悪意のあるアクターによってアカウントが侵害される可能性があります。
- 企業内でチームが移動します。 監査を行わないと、時間の経過と共に不要なアクセスが増える可能性があります。

アクセス レビューを使用して、不要になったロールの割り当てを見つけて削除することをお勧めします。 これにより、承認されていないアクセスや過剰なアクセスのリスクを軽減し、コンプライアンス標準を維持することができます。

ロールのアクセス レビューの詳細については、「PIM で [Azure リソースと Microsoft Entra ロールのアクセス レビューを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)する」を参照してください。 ロールが割り当てられているグループのアクセス レビューの詳細については、「 [Microsoft Entra ID でグループとアプリケーションのアクセス レビューを作成する」を](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)参照してください。

### 5. グローバル管理者の数を 5 人未満に制限する

ベスト プラクティスとして、組織内の **5 人未満** にグローバル管理者ロールを割り当てることをお勧めします。 グローバル管理者は基本的に無制限のアクセス権を持っているため、攻撃対象領域を小さく保つことが最善の利益となります。 前述のように、これらのアカウントはすべて多要素認証で保護する必要があります。

5 つ以上の特権を持つグローバル管理者ロールの割り当てがある場合は、 **グローバル管理者** ロールの割り当てを監視するのに役立つグローバル管理者アラート カードが Microsoft Entra の [概要] ページに表示されます。

[Image: 特権ロールの割り当ての数を含むカードを示す Microsoft Entra の [概要] ページのスクリーンショット。]

既定では、ユーザーが Microsoft のクラウド サービスに新規登録すると、Microsoft Entra テナントが作成され、そのユーザーはグローバル管理者ロールに割り当てられます。 グローバル管理者ロールに割り当てられているユーザーは、Microsoft Entra 組織内のほとんどすべての管理設定を読み取り、変更できます。 いくつかの例外を除き、グローバル管理者は Microsoft 365 組織内のすべての構成設定を読み取り、変更することもできます。 グローバル管理者は、アクセス権を昇格させてデータを読み取ることもできます。

Microsoft では、グローバル [管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールが永続的に割り当てられている 2 つのクラウド専用緊急アクセス アカウントを組織に付与することをお勧めします。 このようなアカウントは高い特権を持っており、特定のユーザーには割り当てられません。 アカウントは、通常のアカウントを使用できない場合や、他のすべての管理者が誤ってロックアウトされているような、緊急時または「ブレークグラス」シナリオに限定されます。これらのアカウントは、[緊急アクセスアカウントの推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)に従って作成する必要があります。

### 6. 特権ロールの割り当ての数を 10 未満に制限する

一部のロールには、資格情報を更新する機能など、特権アクセス許可が含まれます。 これらのロールは特権の昇格につながる可能性があるため、組織内でこれらの特権ロールの割り当ての使用を **10 未満** に制限する必要があります。 特権ロールの割り当てが 10 件を超えると、[ロールと管理者] ページに警告が表示されます。

[Image: 特権ロールの割り当ての警告を示す Microsoft Entra ロールと管理者ページのスクリーンショット。]

**PRIVILEGED** ラベルを探すことで、特権を持つロール、アクセス許可、およびロールの割り当てを識別できます。 詳細については、「 [Microsoft Entra ID の特権ロールとアクセス許可」を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)参照してください。

### 7. Microsoft Entra ロールの割り当てにグループを使用し、ロールの割り当てを委任する

グループを利用する外部ガバナンス システムがある場合は、個々のユーザーではなく、Microsoft Entra グループにロールを割り当てることを検討します。 また、PIM でロール割り当て可能なグループを管理して、これらの特権グループに継続的な所有者またはメンバーがいないことを確認することもできます。 詳細については、「[グループの Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/concept-pim-for-groups)」をご覧ください。

ロールを割り当て可能なグループに所有者を割り当てることができます。 その所有者は、グループに対して追加または削除されるユーザーを決定し、間接的には、ロールの割り当てを取得するユーザーを決定します。 このようにして、特権ロール管理者は、グループを使用して役割ごとにロール管理を委任できます。 詳細については、「 [Microsoft Entra グループを使用してロールの割り当てを管理する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)」を参照してください。

### 8. PIM for Groups を使用して一度に複数のロールをアクティブにする

個人が PIM を介して Microsoft Entra ロールに対して 5 つまたは 6 つの適格な割り当てを持っている場合があります。 各ロールを個別にアクティブにする必要があるため、生産性が低下する可能性があります。 さらに悪いことに、多数の Azure リソースが割り当てられている場合もあり、問題が悪化します。

この場合は、 [グループに Privileged Identity Management (PIM) を使用する](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/concept-pim-for-groups)必要があります。 PIM for Groups を作成し、複数のロール (Microsoft Entra ID と Azure のどちらかまたは両方) に対する永続的なアクセスを付与します。 このユーザーを、このグループの有資格メンバーまたは所有者にします。 アクティブ化を 1 回行うだけで、リンクされたすべてのリソースにアクセスできます。

[Image: 複数のロールを一度にアクティブ化することを示すグループの PIM 図]

### 9. Microsoft Entra ロールにクラウド ネイティブ アカウントを使用する

Microsoft Entra ロールの割り当てには、オンプレミスの同期されたアカウントを使用しないでください。 オンプレミスのアカウントが侵害された場合、Microsoft Entra リソースも危険にさらされる可能性があります。

### 10. きめ細かなアクセス ガバナンスに階層化されたコントロールを使用する

Microsoft Entra IDには、最小限の特権アクセスを細かいレベルで適用するのに役立つ補完的な機能がいくつか用意されています。 すべての承認シナリオに対応する機能は 1 つないため、組織の要件に基づいてこれらのコントロールをレイヤーに組み合わせます。

| 制御 | それが何をするか | いつ使用するか |
| --- | --- | --- |
| [管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units) | ロール割り当ての対象範囲を、特定のユーザー、グループ、またはデバイスの一部に限定します。 | テナント全体のアクセス許可を付与せずに、地域または部門の管理者に管理を委任します。 |
| [カスタム ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create) | ジョブ関数に必要なアクセス許可のみを持つロールを定義します。 | 組み込みロールは、特定の責任に対して広すぎるか狭すぎます。 |
| [Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) | Just-In-Time の期限付きの承認ベースのロールのアクティブ化を可能にします。 | 管理者や開発者を含む特権ロールのユーザーに対する永続的な特権アクセスを排除します。 |
| [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) | リアルタイム信号 (ユーザー リスク、デバイス コンプライアンス、場所、アプリケーション) を評価して、アクセスを強制またはブロックします。 | 変化するリスク条件に適応するコンテキストベースのアクセス決定を適用します。 |
| [資格管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview) | 自動要求、承認、および有効期限のワークフローを使用して、リソースをアクセス パッケージにバンドルします。 | 大規模なプロジェクト、チーム、または組織間コラボレーションのアクセス権を管理します。 |
| [継続的アクセス評価 (CAE)](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation) | アクティブ セッション中のアクセスの再評価は、重要なイベント評価 (アカウントの無効化、パスワード リセット、管理者トークンの失効など) と条件付きアクセス ポリシーの評価 (ネットワークの場所の変更など) の 2 つのシナリオで行います。 | トークンの有効期限を待たずに、ほぼリアルタイムでポリシー変更を適用します。 |
| [Azure 属性ベースのアクセス制御 (ABAC)](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/conditions-custom-security-attributes) を使用したカスタム セキュリティ属性 | ビジネス属性を使用してユーザーとサービス プリンシパルにタグを付け、ロールの割り当ての属性条件によってサポートされているAzure リソース (現在Azure Blob StorageおよびAzure Queue Storageデータ アクション) へのアクセスを制限します。 | 多数の明示的なロールの割り当てを 1 つの属性条件付き割り当てに置き換え、インベントリとレポート用に数百のアプリを分類します。 |

**階層化アプローチの例:** 地域のヘルプデスク管理者が自分の地域のユーザーのパスワードのみをリセットできるように、管理単位をスコープとしたカスタム ロールを割り当てます。 ロールが期限付きで承認ベースになるように、PIM のアクティブ化を要求します。 管理者がロールをアクティブ化するときに、準拠しているデバイスと多要素認証を必要とする条件付きアクセス ポリシーを適用します。 エンタイトルメント管理のアクセス レビューを使用して、管理者がまだ割り当てを必要としていることを定期的に検証します。

Note

前の表のコントロールの可用性は、Microsoft Entraライセンスレベルによって異なります。 たとえば、カスタム ロールと条件付きアクセスには P1 Microsoft Entra IDが必要であり、管理単位には管理単位をスコープとする管理者には Microsoft Entra ID P1 が必要です (作成と基本メンバーシップは Microsoft Entra ID Free で利用できます)。 Privileged Identity Management には Microsoft Entra ID P2 または Microsoft Entra ID ガバナンス が必要です。一方、エンタイトルメント管理とアクセス レビューには Microsoft Entra ID ガバナンス または Microsoft Entra スイート が必要です（一部の機能は Microsoft Entra ID P2 で利用できます）。 継続的アクセス評価の重要なイベント評価は、すべてのテナントで利用できます。条件付きアクセス ポリシーの評価部分は、条件付きアクセスに依存します。これには、Microsoft Entra ID P1 が必要です。 各レベルに含まれる内容を比較するには、[Microsoft Entra プランと価格](https://www.microsoft.com/en-us/security/business/microsoft-entra-pricing)を参照してください。

これらの階層化されたコントロールが付与した内容を監査するには、「だれが [何にアクセスできるかを理解する」を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview#understand-who-has-access-to-what)参照してください。

最小特権アクセス戦略の設計の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-planning) でのハイブリッドおよびクラウドデプロイのセキュリティ保護特権アクセス」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/concept-understand-roles"} -->
## Microsoft Entra ロールの概念についての理解 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/concept-understand-roles
- Service: entra-id / role-based-access-control
- Article date: 2024-07-30
- Summary: Microsoft Entra ID のリソース スコープを使用して、Microsoft Entra 組み込みロールとカスタム ロールを理解する方法について説明します。

Microsoft Entra には約 60 種の組み込みロールがあります。これらは、ロールのアクセス許可がセットとして固定されたロールです。 組み込みロールを補完するため、Microsoft Entra ID ではカスタム ロールもサポートされています。 カスタム ロールを使用して、必要なアクセス許可をロールに選択できます。 たとえば、アプリケーションやサービス プリンシパルなど、特定の Microsoft Entra リソースを管理するために作成することが可能です。

この記事では、Microsoft Entra ロールの概要と、その使用方法について説明します。

### Microsoft Entra ロールとその他の Microsoft 365 ロールとの違い

Microsoft 365 には、Microsoft Entra ID や Intune といったさまざまなサービスがあります。 これらのサービスの一部は、独自のロールベースのアクセス制御システムを備えています。具体的には次のとおりです。

- Microsoft Entra ID
- マイクロソフトエクスチェンジ
- Microsoft Intune
- クラウドアプリ向けのMicrosoft Defender
- Microsoft 365 Defender ポータル
- コンプライアンス ポータル
- Cost Management + Billing

Teams、SharePoint、マネージド デスクトップなど、他のサービスには個別のロールベースのアクセス制御システムがありません。 管理者アクセスには Microsoft Entra ロールを使用します。 Azure には、Azure リソース (仮想マシンなど) 用の独自のロールベースのアクセス制御システムがあり、このシステムは Microsoft Entra ロールとは異なります。

[Image: Azure RBAC と Microsoft Entra ロール]

別のロールベースのアクセス制御システムとは、ロールの定義とロールの割り当てが格納されている異なるデータ ストアが存在することを意味します。 同様に、アクセス確認が行われるポリシー決定ポイントも別にあります。 詳細については、「[Microsoft サービス全体のロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/m365-workload-docs)」と「[Azure ロール、Microsoft Entra ロール、および従来のサブスクリプション管理者ロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/rbac-and-directory-admin-roles)」をご覧ください。

### その他のサービスで一部の Microsoft Entra ロールが使用される理由

Microsoft 365 には、これまで個別に開発されてきたロールベースのアクセス制御システムが多数あり、それぞれに独自のサービス ポータルが用意されています。 Microsoft 365 全体での ID 管理を Microsoft Entra 管理センターから便利に行えるように、サービス固有の組み込みロールがいくつか追加されています。これらでは、それぞれの Microsoft 365 サービスへの管理アクセス権が付与されます。 この追加の一例として、Microsoft Entra ID の Exchange 管理者ロールがあります。 このロールは Exchange のロールベースのアクセス制御システムにおける[組織管理用ロール グループ](https://learn.microsoft.com/ja-jp/exchange/organization-management-exchange-2013-help)と同等であり、Exchange のあらゆる側面を管理できます。 同様に、Intune 管理者ロール、Teams 管理者、SharePoint 管理者なども追加されました。 サービス固有のロールは、次のセクションに出てくる Microsoft Entra 組み込みロールのカテゴリの 1 つです。

### Microsoft Entra ロールのカテゴリ

Microsoft Entra 組み込みロールには、使用される場所によってさまざまなものが存在し、次の 3 つの大きなカテゴリに分類されます。

- **Microsoft Entra ID 固有のロール**: これらのロールは、Microsoft Entra 専用でリソースを管理するためのアクセス許可を付与します。 たとえば、ユーザー管理者、アプリケーション管理者、グループ管理者では、そのどれにおいても Microsoft Entra ID 内にあるリソースを管理するためのアクセス許可が付与されます。
- **Microsoft Entra ID のサービス固有のロール**: サービス内のすべての機能を管理するためのサービス固有の特権に対して Microsoft Entra ID のロールを定義する Microsoft 365 などの Microsoft サービス。 たとえば、Exchange 管理者、Intune 管理者、SharePoint 管理者、および Teams 管理者の各ロールは、それぞれのサービスを使用して機能を管理できます。 Exchange 管理者はメールボックスを管理でき、Intune 管理者はデバイス ポリシーを管理でき、SharePoint 管理者はサイト コレクションを管理でき、Teams 管理者は通話の品質を管理できます。
- **Microsoft Entra ID のサービス間のロール:** 複数のサービスにわたるロールがいくつかあります。 グローバルなロールとしては、グローバル管理者とグローバル閲覧者の 2 つがあります。 これら 2 つのロールはすべての Microsoft 365 サービスに対して有効です。 また、セキュリティ管理者やセキュリティ閲覧者など、Microsoft 365 内の複数のセキュリティ サービスに対するアクセス権を付与するセキュリティ関連のロールもあります。 たとえば、Microsoft Entra ID でセキュリティ管理者ロールを使用すると、Microsoft 365 Defender ポータル、Microsoft Defender Advanced Threat Protection、Microsoft Defender for Cloud Apps を管理できます。 同様に、コンプライアンス管理者ロールでは、コンプライアンス関連の設定をコンプライアンス ポータルや Exchange などで管理できます。

[Image: Microsoft Entra 組み込みロールの 3 つのカテゴリ]

これらのロール カテゴリの理解に、次の表を役立ててください。 カテゴリの名前は任意で付けられており、[ドキュメントに記載されている Microsoft Entra ロールのアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)を超える他の機能があることを示すものではありません。

| カテゴリ | 役割 |
| --- | --- |
| Microsoft Entra ID 固有のロール | アプリケーション管理者アプリケーション開発者認証管理者B2C IEF キーセット管理者B2C IEF ポリシー管理者クラウド アプリケーション管理者クラウド デバイス管理者条件付きアクセス管理者デバイス管理者ディレクトリ リーダーディレクトリ同期アカウントディレクトリ ライター外部 ID ユーザー フロー管理者外部 ID ユーザー フロー属性管理者外部 ID プロバイダー管理者グループ管理者ゲスト招待元ヘルプデスク管理者ハイブリッド ID の管理者ライセンス管理者パートナー レベル 1 のサポートパートナー レベル 2 のサポートパスワード管理者特権認証管理者特権ロール管理者レポート閲覧者ユーザー管理者 |
| Microsoft Entra ID のサービス固有のロール | Azure DevOps 管理者Azure Information Protection 管理者課金管理者CRM サービス管理者カスタマー ロックボックスのアクセス承認者デスクトップ Analytics 管理者Exchange サービス管理者Insights 管理者Insights ビジネス リーダーIntune サービス管理者Kaizala 管理者Lync サービス管理者メッセージ センターのプライバシー閲覧者メッセージ センター閲覧者Modern Commerce 管理者ネットワーク管理者Office アプリ管理者Power BI サービス管理者Power Platform 管理者プリンター管理者プリンター技術者Search 管理者Search エディターSharePoint サービス管理者Teams 通信管理者Teams 通信サポート エンジニアTeams 通信サポート スペシャリストTeams デバイス管理者Teams 管理者 |
| Microsoft Entra ID のサービス間のロール | コンプライアンス管理者コンプライアンス データ管理者グローバル閲覧者グローバル管理者セキュリティ管理者セキュリティ オペレーターセキュリティ閲覧者サービス サポート管理者 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/custom-available-permissions"} -->
## アプリの登録のためのカスタム ロールのアクセス許可 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-available-permissions
- Service: entra-id / role-based-access-control
- Article date: 2026-03-16
- Summary: アプリの登録を管理するためのカスタム管理者ロールのアクセス許可を委任します。

この記事では、Microsoft Entra ID のカスタム ロール定義で使用できるアプリの登録のアクセス許可について概要を説明します。 これらのアクセス許可により、管理者は特定のアクセス レベルでアプリケーションの登録を管理でき、組織内のアプリケーションを安全かつ効率的に管理できるようになります。

### ライセンスの要件

この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 自分の要件に適したライセンスを探すには [一般提供されている Microsoft Entra ID の機能の比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)」を参照してください。

### シングルテナント アプリケーションを管理するためのアクセス許可

カスタム ロールのアクセス許可を選択する場合は、シングルテナント アプリケーションのみを管理するためのアクセス許可を付与できます。 シングルテナント アプリケーションは、アプリケーションが登録されている Microsoft Entra 組織内のユーザーのみが利用できます。

シングルテナント アプリケーションは、**サポートされているアカウントの種類** が "この組織のディレクトリ内のアカウントのみ" に設定されているとして定義されます。Graph API では、シングルテナント アプリケーションの signInAudience プロパティは "AzureADMyOrg" に設定されています。

シングルテナント アプリケーションのみを管理するためのアクセス許可を付与するには、以下のアクセス許可と、サブタイプ **applications.myOrganization** を使用します。 たとえば、microsoft.directory/applications.myOrganization/basic/update です。

サブタイプ、アクセス許可、プロパティ セットという用語の意味については、[カスタム ロールの概要](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview) の説明を参照してください。 次の情報は、アプリケーションの登録に固有のものです。

### 作成と削除

アプリケーションの登録を作成する機能を許可するために使用できるアクセス許可は 2 つあり、それぞれ動作が異なります。

##### microsoft.directory/applications/createAsOwner

このアクセス許可を割り当てると、作成されたアプリの登録の最初の所有者として作成者が追加されます。 作成されたアプリの登録は、作成者の 250 個の作成済みオブジェクト クォータにカウントされます。

##### microsoft.directory/applications/create

このアクセス許可を付与すると、作成者がアプリの登録の最初の所有者として追加されなくなり、作成者の 250 個のオブジェクト クォータからアプリの登録が除外されます。 担当者がディレクトリレベルのクォータに達するまでアプリの登録を作成できないようにする機能はないため、このアクセス許可は慎重に使用してください。

両方のアクセス許可が割り当てられている場合、/create アクセス許可が優先されます。 /createAsOwner アクセス許可では作成者を最初の所有者として自動的に追加しませんが、Graph API または PowerShell コマンドレットを使用する場合は、アプリの登録の作成時に所有者を指定できます。

作成のアクセス許可は、 **[New registration](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/新規登録)** コマンドへのアクセス権を付与します。

[Image: [新しい登録ポータル] コマンドへのアクセスを許可するアクセス許可のスクリーンショット。]

アプリの登録を削除する権限を付与するために使用できるアクセス許可には、次の 2 つがあります。

##### microsoft.directory/applications/delete

サブタイプに関係なく、アプリの登録を削除する権限を付与します。これにはシングルテナント アプリケーションとマルチテナント アプリケーションの両方が含まれます。

##### microsoft.directory/applications.myOrganization/delete

組織内のアカウントまたはシングルテナント アプリケーションのみがアクセスできるものに限定して、アプリの登録を削除する権限を付与します (myOrganization サブタイプ)。

[Image: アプリ登録の削除コマンドへのアクセスを許可するアクセス許可のスクリーンショット。]

Note

作成のアクセス許可を含むロールを割り当てるとき、ロールの割り当てはディレクトリ スコープで行う必要があります。 リソース スコープで割り当てられた作成のアクセス許可は、アプリの登録を作成する権限を付与しません。

### 読み取り

組織内のすべてのメンバー ユーザーは、既定でアプリ登録情報を読み取ることができます。 一方、ゲスト ユーザーおよびアプリケーション サービス プリンシパルはできません。 ゲスト ユーザーまたはアプリケーションにロールを割り当てる予定の場合は、適切な読み取りアクセス許可を含める必要があります。

##### microsoft.directory/applications/allProperties/read

資格情報など、いかなる状況でも読み取ることができないプロパティを除き、シングルテナント アプリケーションおよびマルチテナント アプリケーションのすべてのプロパティを読み取る権限を付与します。

##### microsoft.directory/applications.myOrganization/allProperties/read

microsoft.directory/applications/allProperties/read と同じアクセス許可を付与しますが、シングルテナント アプリケーションのみが対象となります。

##### microsoft.directory/applications/owners/read

シングルテナント アプリケーションおよびマルチテナント アプリケーションの所有者プロパティを読み取る権限を付与します。 アプリケーションの登録の所有者ページのすべてのフィールドへのアクセスを許可します。

[Image: アプリ登録所有者ページへのアクセスを許可するアクセス許可のスクリーンショット。]

##### microsoft.directory/applications/standard/read

標準アプリケーションの登録プロパティの読み取りアクセスを許可します。 これには、アプリケーションの登録ページ間のプロパティが含まれます。

##### microsoft.directory/applications.myOrganization/standard/read

microsoft.directory/applications/standard/read と同じアクセス許可を付与しますが、シングルテナント アプリケーションのみが対象となります。

### 更新プログラム

Microsoft Entra ID の "更新" アクセス許可により、管理者はアプリケーションの登録のさまざまなプロパティを変更できます。 これらのアクセス許可は、シングルテナント アプリケーションおよびマルチテナント アプリケーションの両方を保守、管理するために不可欠です。 付与された特定のアクセス許可に応じて、管理者はサポートされているアカウントの種類、認証設定、ブランドの詳細などのプロパティを更新できます。 利用可能な更新プログラムのアクセス許可とその特定の機能について、詳細な一覧を次に示します。

##### microsoft.directory/applications/allProperties/update

シングルテナント アプリケーションおよびマルチテナント アプリケーションのすべてのプロパティを更新する権限を許可します。

##### microsoft.directory/applications.myOrganization/allProperties/update

microsoft.directory/applications/allProperties/update と同じアクセス許可を付与しますが、シングルテナント アプリケーションのみが対象となります。

##### microsoft.directory/applications/audience/update

シングルテナント アプリケーションおよびマルチテナント アプリケーションでサポートされているアカウントの種類 (signInAudience) のプロパティを更新する権限を許可します。

[Image: 認証ページのアプリ登録でサポートされているアカウントの種類プロパティへのアクセスを許可するアクセス許可のスクリーンショット。]

##### microsoft.directory/applications.myOrganization/audience/update

microsoft.directory/applications/audience/update と同じアクセス許可を付与しますが、シングルテナント アプリケーションのみが対象となります。

##### microsoft.ディレクトリ/アプリケーション/認証/更新

シングルテナント アプリケーションおよびマルチテナント アプリケーションの応答 URL、サインアウト URL、暗黙的なフロー、発行元ドメインのプロパティを更新する権限を許可します。 サポートされているアカウントの種類を除き、アプリケーションの登録の認証ページのすべてのフィールドへのアクセスを許可します。

[Image: アプリ登録認証へのアクセスを許可するアクセス許可のスクリーンショット。サポートされているアカウントの種類ではありません。]

##### microsoft.directory/applications.myOrganization/authentication/update

microsoft.directory/applications/authentication/update と同じアクセス許可を付与しますが、シングルテナント アプリケーションのみが対象となります。

##### microsoft.directory/applications/basic/update

シングルテナント アプリケーションおよびマルチテナント アプリケーションの名前、ロゴ、ホーム ページ URL、サービス使用条件 URL、プライバシーに関する声明 URL のプロパティを更新する権限を許可します。 アプリケーションの登録のブランド化ページのすべてのフィールドへのアクセスを許可します。

[Image: アプリ登録のブランド化ページへのアクセスを許可するアクセス許可のスクリーンショット。]

##### microsoft.directory/applications.myOrganization/ベーシック/アップデート

microsoft.directory/applications/basic/update と同じアクセス許可を付与しますが、シングルテナント アプリケーションのみが対象となります。

##### microsoft.directory/applications/credentials/update

シングルテナント アプリケーションおよびマルチテナント アプリケーションの認定資格証とクライアント シークレットのプロパティを更新する権限を許可します。 アプリケーションの登録の認定資格証および & シークレット ページのすべてのフィールドへのアクセス許可を付与します。

[Image: アプリ登録証明書とシークレット ページへのアクセスを許可するアクセス許可のスクリーンショット。]

##### microsoft.directory/applications.myOrganization/credentials/update

microsoft.directory/applications/credentials/update と同じアクセス許可を付与しますが、シングルテナント アプリケーションのみが対象となります。

##### microsoft.directory/applications/disablement/update

ユーザーがサインインできるようにアプリケーションが有効になっているかどうかを更新できます。

##### microsoft.directory/applications/owners/update

シングルテナントおよびマルチテナントの所有者プロパティを更新する権限を許可します。 アプリケーションの登録の所有者ページのすべてのフィールドへのアクセスを許可します。

[Image: アプリ登録所有者ページへのアクセスを許可するアクセス許可のスクリーンショット。]

##### microsoft.directory/applications.myOrganization/owners/update

microsoft.directory/applications/owners/update と同じアクセス許可を付与しますが、シングルテナント アプリケーションのみが対象となります。

##### microsoft.directory/applications/permissions/update

このアクセス許可により、委任されたアクセス許可、アプリケーションのアクセス許可、認可済みクライアント アプリケーション、必要なアクセス許可、同意のプロパティなど、シングルテナント アプリケーションおよびマルチテナント アプリケーションのさまざまなプロパティを更新できます。 同意を実行する権限が付与されるわけではありません。 アプリケーションの登録の API アクセス許可および API 公開ページのすべてのフィールドへのアクセスを許可します。

[Image: アプリ登録 API のアクセス許可ページへのアクセスを許可するアクセス許可のスクリーンショット。]

[Image: アプリ登録の [API の公開] ページへのアクセスを許可するアクセス許可のスクリーンショット。]

##### microsoft.directory/applications.myOrganization/permissions/update

microsoft.directory/applications/permissions/update と同じアクセス許可を付与しますが、シングルテナント アプリケーションのみが対象となります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/custom-consent-permissions"} -->
## Microsoft Entra ID のカスタム ロールに対するアプリの同意権限 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-consent-permissions
- Service: entra-id / role-based-access-control
- Article date: 2025-03-30
- Summary: Microsoft Entra 管理センター、PowerShell、または Graph API でのカスタム Microsoft Entra ロールに関するアプリの同意権限。

この記事には、Microsoft Entra ID のカスタム ロール定義に対して現在使用可能なアプリの同意アクセス許可が含まれています。 この記事では、アプリの同意とアクセス許可に関連するいくつかの一般的なシナリオに必要なアクセス許可について説明します。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)の一般公開機能を比較する」を参照してください。

### アプリへの同意のアクセス許可

この記事に記載されているアクセス許可を使用して、アプリの同意ポリシーと、アプリに同意を付与するアクセス許可を管理します。

手記

Microsoft Entra 管理センターでは、この記事に記載されているアクセス許可をカスタム ロール定義に追加することはまだサポートされていません。 Microsoft Graph PowerShell [使用して、この記事に記載されているアクセス許可を持つカスタム ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create) を作成する必要があります。

##### 自己に代わって委任されたアクセス許可をアプリに付与する (ユーザーの同意)

ユーザーが自分に代わってアプリケーションに同意を付与 (ユーザーの同意) できるようにするには、アプリの同意ポリシーに従います。

- microsoft.directory/servicePrincipals/managePermissionGrantsForSelf.{id}

ここで、 は、このアクセス許可をアクティブにするために満たす必要がある条件を設定するアプリの同意ポリシーの ID に置き換えられます。

たとえば、ID `microsoft-user-default-low`を持つ組み込みのアプリ同意ポリシーに従って、ユーザーが自分の代わりに同意を付与できるようにするには、アクセス許可 `...managePermissionGrantsForSelf.microsoft-user-default-low`を使用します。

##### すべてのユーザーに代わってアプリにアクセス許可を付与する (管理者の同意)

委任されたアクセス許可とアプリケーションのアクセス許可 (アプリ ロール) の両方について、テナント全体の管理者の同意をアプリに委任するには:

- microsoft.directory/servicePrincipals/managePermissionGrantsForAll.{id}

ここで、 は、このアクセス許可を使用するために満たす必要がある条件を設定するアプリの同意ポリシーの ID に置き換えられます。

たとえば、ロールの割り当てユーザーが ID を持つカスタム `low-risk-any-app` の対象となるアプリにテナント全体の管理者の同意を付与できるようにするには、アクセス許可 `microsoft.directory/servicePrincipals/managePermissionGrantsForAll.low-risk-any-app`を使用します。

##### アプリの同意ポリシーの管理

[アプリへの同意ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-app-consent-policies)の作成、更新、削除を委任します。

- microsoft.directory/permissionGrantPolicies/create
- microsoft.directory/permissionGrantPolicies/standard/read
- microsoft.directory/permissionGrantPolicies/basic/update
- microsoft.directory/permissionGrantPolicies/delete

### アクセス許可の完全な一覧

| 許可 | 説明 |
| --- | --- |
| microsoft.directory/servicePrincipals/managePermissionGrantsForSelf.{id} | アプリの同意ポリシー `{id}`に従って、自己 (ユーザーの同意) に代わってアプリに同意する権限を付与します。 |
| microsoft.directory/servicePrincipals/managePermissionGrantsForAll.{id} | アプリの同意ポリシーの `{id}`に従って、すべてのアプリに代わってアプリに同意するアクセス許可を付与します (テナント全体の管理者の同意)。 |
| microsoft.directory/permissionGrantPolicies/standard/read | アクセス許可付与ポリシーの標準プロパティの読み取り |
| microsoft.directory/permissionGrantPolicies/basic/update | アクセス許可付与ポリシーの基本プロパティを更新する |
| microsoft.directory/permissionGrantPolicies/create | アクセス許可付与ポリシーを作成する |
| microsoft.directory/permissionGrantPolicies/delete | アクセス許可付与ポリシーを削除する |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/custom-create"} -->
## Microsoft Entra ID でカスタム ロールを作成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create
- Service: entra-id / role-based-access-control
- Article date: 2026-06-27
- Summary: Microsoft Entra 管理センター、Microsoft Graph PowerShell、または Microsoft Graph API を使用して Microsoft Entra リソースへのアクセスを管理するカスタム ロールを作成する方法について説明します

この記事では、Microsoft Entra 管理センター、Microsoft Graph PowerShell、または Microsoft Graph API を使用して、Microsoft Entra リソースへのアクセスを管理するカスタム ロールを作成する方法について説明します。 代わりに、Azure リソースへのアクセスを管理するカスタム ロールを作成する場合は、「Azure [portal を使用して Azure カスタム ロールを作成または更新](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/custom-roles-portal)する」を参照してください。

カスタム ロールの基本については、[カスタム ロールの概要](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview)を参照してください。 ロールは、ディレクトリ レベルのスコープまたはアプリ登録リソース スコープでのみ割り当てることができます。 Microsoft Entra 組織で作成できるカスタム ロールの最大数については、「[Microsoft Entra サービスの制限と制限](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions)を参照してください。

カスタム ロールには、カスタム使用が有効になっているアクセス許可のみを含めることができます。 使用可能なカテゴリは、 [アプリの登録](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-available-permissions)、 [エンタープライズ アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-enterprise-app-permissions)、 [同意](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-consent-permissions)、 [デバイス](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-device-permissions)、 [ユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-user-permissions)、 [グループ](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-group-permissions)です。

### 前提 条件

- Microsoft Entra ID P1 または P2 ライセンス
- 特権ロール管理者
- PowerShell を使用する場合の [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) モジュール
- Microsoft Graph API の Graph エクスプローラーを使用する場合の管理者の同意

詳細については、「powerShell または Graph Explorer [を使用するための](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/prerequisites)前提条件」を参照してください。

## [管理センター](#tab/admin-center)
#### カスタム ロールを作成する

これらの手順では、Microsoft Entra 管理センターでカスタム ロールを作成してアプリの登録を管理する方法について説明します。

1. [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)以上として [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**役割と管理者**に移動します。
3. **新しいカスタム ロール**を選択します。

    [Image: Microsoft Entra 管理センターの [ロールと管理者] ページのスクリーンショット。]
4. [**基本**] タブで、ロールの名前と説明を入力します。

    カスタム ロールからベースラインのアクセス許可を複製することはできますが、組み込みのロールを複製することはできません。

    [Image: カスタム ロールの名前と説明を指定する [基本] タブのスクリーンショット。]
5. [**アクセス許可**] タブで、アプリ登録の基本プロパティと資格情報プロパティを管理するために必要なアクセス許可を選択します。 各アクセス許可の詳細については、Microsoft Entra ID [の「アプリケーション登録サブタイプとアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-available-permissions)」を参照してください。

    1. まず、検索バーに「資格情報」と入力し、`microsoft.directory/applications/credentials/update` アクセス許可を選択します。

        [Image: カスタム ロールのアクセス許可を選択するための [アクセス許可] タブのスクリーンショット。]
    2. 次に、検索バーに「基本」と入力し、`microsoft.directory/applications/basic/update` 権限を選択して、[次へ ] をクリックします。
6. **[確認と作成]** タブでアクセス許可を確認し、 **[作成]** を選択します。

    カスタム ロールが、割り当て可能なロールの一覧に表示されます。

## [PowerShell](#tab/ms-powershell)
#### サインイン

[Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) コマンドを使用して、テナントにサインインします。

```PowerShell
Connect-MgGraph -Scopes "RoleManagement.ReadWrite.Directory"
```

#### カスタム ロールを作成する

次の PowerShell スクリプトを使用して新しいロールを作成します。

```PowerShell
# Basic role information
$displayName = "Application Support Administrator"
$description = "Can manage basic aspects of application registrations."
$templateId = (New-Guid).Guid
      
# Set of permissions to grant
$rolePermissions = @{
    "allowedResourceActions" = @(
        "microsoft.directory/applications/basic/update",
        "microsoft.directory/applications/credentials/update"
    )
}
      
# Create new custom admin role
$customAdmin = New-MgRoleManagementDirectoryRoleDefinition -RolePermissions $rolePermissions `
    -DisplayName $displayName -Description $description -TemplateId $templateId -IsEnabled:$true
```

#### カスタム ロールを更新する

```powershell
# Update role definition
# This works for any writable property on role definition. You can replace display name with other
# valid properties.
Update-MgRoleManagementDirectoryRoleDefinition -UnifiedRoleDefinitionId c4e39bd9-1100-46d3-8c65-fb160da0071f `
   -DisplayName "Updated DisplayName"
```

#### カスタム ロールを削除する

```powershell
# Delete role definition
Remove-MgRoleManagementDirectoryRoleDefinition -UnifiedRoleDefinitionId c4e39bd9-1100-46d3-8c65-fb160da0071f
```

## [Graph API](#tab/ms-graph)
#### カスタム ロールを作成する

次の手順に従います。

1. [Create unifiedRoleDefinition](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-post-roledefinitions) API を使用して、カスタム ロールを作成します。

    ```HTTP
    POST https://graph.microsoft.com/v1.0/roleManagement/directory/roleDefinitions
    ```

    Body

    ```HTTP
    {
        "description": "Can manage basic aspects of application registrations.",
        "displayName": "Application Support Administrator",
        "isEnabled": true,
        "templateId": "<GUID>",
        "rolePermissions": [
            {
                "allowedResourceActions": [
                    "microsoft.directory/applications/basic/update",
                    "microsoft.directory/applications/credentials/update"
                ]
            }
        ]
    }
    ```

    手記

    `"templateId": "GUID"` は、要件に応じて本文で送信される省略可能なパラメーターです。 共通パラメーターを使用して複数の異なるカスタム ロールを作成する必要がある場合は、テンプレートを作成し、`templateId` 値を定義することをお勧めします。 PowerShell コマンドレット `templateId`を使用して、`(New-Guid).Guid` 値を事前に生成できます。
2. [create unifiedRoleAssignment](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-post-roleassignments) API を使用して、カスタム ロールを割り当てます。

    ```http
    POST https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments
    ```

    Body

    ```http
    {
    "principalId":"<GUID OF USER>",
    "roleDefinitionId":"<GUID OF ROLE DEFINITION>",
    "directoryScopeId":"/<GUID OF APPLICATION REGISTRATION>"
    }
    ```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/custom-device-permissions"} -->
## Microsoft Entra カスタム ロールのデバイス管理アクセス許可 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-device-permissions
- Service: entra-id / role-based-access-control
- Article date: 2024-07-18
- Summary: Microsoft Entra 管理センター、PowerShell、または Microsoft Graph API での Microsoft Entra カスタム ロールに対するデバイス管理アクセス許可。

デバイス管理アクセス許可を Microsoft Entra ID のカスタム ロール定義で使うと、次のようなきめ細かいアクセス権を付与できます。

- デバイスを有効または無効にする
- デバイスの削除
- BitLocker 回復キーの読み取り
- BitLocker メタデータの読み取り
- デバイス登録ポリシーの読み取り
- デバイス登録ポリシーの更新

この記事では、さまざまなデバイス管理シナリオのカスタム ロールで使用できるアクセス許可の一覧を示します。 カスタム ロールを作成する方法については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)でカスタム ロールを作成する」を参照してください。

### デバイスを有効または無効にする

デバイスの状態を切り替えるには、次のアクセス許可を使用できます。

- microsoft.directory/devices/enable
- microsoft.directory/devices/disable

### BitLocker 回復キーの読み取り

次のアクセス許可を使用して、BitLocker メタデータと回復キーを読み取ることができます。 この 1 つのアクセス許可で、BitLocker メタデータと回復キーの両方の読み取りができることに注意してください。

- microsoft.directory/bitlockerKeys/key/read

BitLocker 回復キーを見るには、**[すべてのデバイス]** ページでデバイスを選択して、**[回復キーを表示する]** を選択します。 BitLocker 回復キーの読み取りの詳細については、「[BitLocker キーを表示またはコピーする](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-device-identities#view-or-copy-bitlocker-keys)」を参照してください。

[Image: Azure portal の Bitlocker キーを示すスクリーンショット。]

注

[Windows Autopilot](https://learn.microsoft.com/ja-jp/mem/autopilot/windows-autopilot) を利用するデバイスが Entra に参加するために再利用され、**新しいデバイス所有者がいる**場合、その新しいデバイス所有者は管理者に連絡して、そのデバイスの BitLocker 回復キーを取得する必要があります。 カスタム役割または管理単位スコープの管理者は、デバイス所有権の変更が行われたデバイスの BitLocker 回復キーにアクセスできなくなります。 これらのスコープの管理者は、スコープ外の管理者に連絡して回復キーを取得する必要があります。 詳細については、「[Intune デバイスのプライマリ ユーザーを検索する](https://learn.microsoft.com/ja-jp/mem/intune/remote-actions/find-primary-user#change-a-devices-primary-user)」の記事を参照してください。

### BitLocker メタデータの読み取り

次のアクセス許可を使用して、すべてのデバイスの BitLocker メタデータを読み取ることができます。

- microsoft.directory/bitlockerKeys/metadata/read

すべてのデバイスの BitLocker メタデータを読み取ることはできますが、BitLocker 回復キーを読み取ることはできません。

[Image: Azure portal の Bitlocker メタデータを示すスクリーンショット。]

### デバイス登録ポリシーの読み取り

次のアクセス許可を使用して、テナント全体のデバイス登録設定を読み取ることができます。

- microsoft.directory/deviceRegistrationPolicy/standard/read

デバイスの設定は、Microsoft Entra 管理センターで確認できます。

[Image: Azure portal の [デバイス設定] ページを示すスクリーンショット。]

### デバイス登録ポリシーの更新

次のアクセス許可を使用して、テナント全体のデバイス登録設定を更新することができます。

- microsoft.directory/deviceRegistrationPolicy/basic/update

### アクセス許可の全一覧

##### お読みください

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/devices/createdFrom/read | モノのインターネット (IoT) デバイス テンプレート リンクから作成されたものを読み取る |
| microsoft.directory/devices/registeredOwners/read | デバイスの登録済み所有者を読み取る |
| microsoft.directory/devices/registeredUsers/read | デバイスの登録済みユーザーを読み取る |
| microsoft.directory/devices/standard/read | デバイスで基本プロパティを読み取る |
| microsoft.directory/bitlockerKeys/key/read | デバイス上の bitlocker メタデータとキーを読み取る |
| microsoft.directory/bitlockerKeys/metadata/read | デバイスの BitLocker キー メタデータを読み取る |
| microsoft.directory/deviceRegistrationPolicy/standard/read | デバイス登録ポリシーの標準プロパティの読み取り |

##### 更新

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/devices/registeredOwners/update | デバイスの登録済み所有者を更新する |
| microsoft.directory/devices/registeredUsers/update | デバイスの登録済みユーザーを更新する |
| microsoft.directory/devices/enable | Microsoft Entra ID でデバイスを有効にする |
| microsoft.directory/devices/disable | Microsoft Entra ID でデバイスを無効にする |
| microsoft.directory/deviceRegistrationPolicy/basic/update | デバイス登録ポリシーの基本プロパティを更新する |

##### 削除

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/devices/delete | Microsoft Entra ID からデバイスを削除する |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/custom-enterprise-app-permissions"} -->
## Microsoft Entra ID でのカスタム ロール向けのアプリケーションアクセス許可 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-enterprise-app-permissions
- Service: entra-id / role-based-access-control
- Article date: 2023-01-31
- Summary: Microsoft Entra 管理センター、PowerShell、または Graph API でカスタム Microsoft Entra ロールのエンタープライズ アプリのアクセス許可をプレビューします。

この記事には、Microsoft Entra ID のカスタム ロール定義に対して現在使用可能なエンタープライズ アプリケーションのアクセス許可が含まれています。 この記事では、いくつかの一般的なシナリオのアクセス許可リストと、エンタープライズ アプリのアクセス許可の完全な一覧について説明します。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)の一般公開機能を比較する」を参照してください。

### エンタープライズ アプリケーションのアクセス許可

これらのアクセス許可の使用方法の詳細については、「[カスタム ロールを割り当ててエンタープライズ アプリの](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-enterprise-apps) を管理する」を参照してください。

##### アプリケーションへのユーザーまたはグループの割り当て

SAML ベースのシングル サインオン アプリケーションにアクセスできるユーザーとグループの割り当てを委任する。 必要なアクセス許可

- microsoft.directory/servicePrincipals/appRoleAssignedTo/update

##### ギャラリー アプリケーションの作成

ServiceNow、F5、Salesforce などの Microsoft Entra Gallery アプリケーションの作成を委任します。 必要なアクセス許可:

- microsoft.directory/applicationTemplates/instantiate

##### 基本的な SAML URL の構成

SAML ベースのシングル サインオン アプリケーションの基本的な SAML 構成の更新と読み取りを委任します。 必要なアクセス許可:

- Microsoft ディレクトリ/サービスプリンシパル/認証/更新
- microsoft.directory/applications.myOrganization/authentication/update

##### 署名証明書の更新または作成

SAML ベースのシングル サインオン アプリケーションの署名証明書の管理を委任する。 権限が必要です。

microsoft.directory/servicePrincipals/credentials/update

##### 期限切れのサインイン証明書通知メール アドレスを更新する

SAML ベースのシングル サインオン アプリケーションの期限切れのサインイン証明書通知電子メール アドレスの更新を委任します。 必要なアクセス許可:

- microsoft.directory/applications.myOrganization/authentication/update
- microsoft.directory/applications.myOrganization/permissions/update
- microsoft.directory/servicePrincipals/authentication/update
- microsoft.directory/servicePrincipals/basic/update

##### SAML トークン署名とサインイン アルゴリズムを管理する

SAML ベースのシングル サインオン アプリケーションの SAML トークン署名とサインイン アルゴリズムの更新を委任します。 必要なアクセス許可:

- microsoft.directory/applicationPolicies/basic/update
- microsoft.directory/applications/authentication/update
- microsoft.directory/servicePrincipals/policies/update

##### ユーザー属性と要求を管理する

SAML ベースのシングル サインオン アプリケーションのユーザー属性と要求の作成、削除、および更新を委任します。 必要なアクセス許可:

- microsoft.directory/applicationPolicies/basic/update
- microsoft.directory/applications/authentication/update
- microsoft.directory/servicePrincipals/policies/update

### アプリプロビジョニングの権限

UI を使用してジョブ、スキーマ、資格情報を管理するなどの書き込み操作を実行するには、プロビジョニング ページを表示するための読み取りアクセス許可も必要です。

スコープをすべてのユーザーとグループ、または割り当てられたユーザーとグループに設定するには、現在、synchronizationJob と synchronizationCredentials の両方のアクセス許可が必要です。

##### プロビジョニング ジョブを有効または再起動する

プロビジョニング ジョブをオン、オフ、再起動する機能を委任する。 必要なアクセス許可:

- microsoft.directory/servicePrincipals/synchronizationJobs/manage

##### プロビジョニング スキーマを構成する

属性マッピングに更新を委任します。 必要なアクセス許可:

- マイクロソフト.ディレクトリ/サービスプリンシパル/同期スキーマ/管理

##### アプリケーション オブジェクトに関連付けられているプロビジョニング設定の読み取り

オブジェクトに関連付けられているプロビジョニング設定を読み取る機能を委任する。 必要なアクセス許可:

- microsoft.directory/applications/synchronization/standard/read

##### サービス プリンシパルに関連付けられているプロビジョニング設定を読み取る

サービス プリンシパルに関連付けられているプロビジョニング設定を読み取る権限を委任します。 必要なアクセス許可:

- microsoft.directory/servicePrincipals/synchronization/standard/read

##### プロビジョニング用のアプリケーション アクセスを承認する

プロビジョニングのアプリケーション アクセスを承認する権限を委任する。 Oauth ベアラー トークンの入力例。 必要なアクセス許可:

- microsoft.directory/servicePrincipals/synchronizationCredentials/manage

### アプリケーション プロキシ アクセス許可

アプリケーションのアプリケーション プロキシ プロパティに対する書き込み操作を実行するには、アプリケーションの基本プロパティと認証を更新するためのアクセス許可も必要です。

アプリケーションのアプリケーション プロキシ プロパティに対する書き込み操作を読み取って実行するには、コネクタ グループを表示するための読み取りアクセス許可も必要です。これは、ページに表示されるプロパティの一覧の一部であるためです。

##### アプリケーション プロキシ コネクタの管理を委任する

コネクタ管理の作成、読み取り、更新、削除の各アクションを委任します。 必要なアクセス許可:

- microsoft.directory/connectorGroups/allProperties/read
- microsoft.directory/connectorGroups/allProperties/update
- microsoft.directory/connectorGroups/create
- microsoft.directory/connectorGroups/delete
- microsoft.directory/connectors/allProperties/read
- microsoft.directory/connectors/create

##### アプリケーション プロキシ設定の管理を委任する

アプリのアプリケーション プロキシ プロパティの作成、読み取り、更新、および削除アクションを委任する。 必要なアクセス許可:

- microsoft.directory/applications/applicationProxy/read
- microsoft.directory/applications/applicationProxy/update
- microsoft.directory/applications/applicationProxyAuthentication/update
- microsoft.directory/applications/applicationProxySslCertificate/update
- microsoft.directory/applications/applicationProxyUrlSettings/update
- microsoft.directory/applications/basic/update
- microsoft.directory/applications/authentication/update
- microsoft.directory/connectorGroups/allProperties/read

##### アプリのプロキシ設定の読み取り

アプリのアプリケーション プロキシ プロパティの読み取りアクセス許可を委任する。 必要なアクセス許可:

- microsoft.directory/applications/applicationProxy/read
- microsoft.directory/connectorGroups/allProperties/read

##### アプリの URL 構成アプリケーション プロキシ設定を更新する

アプリケーション プロキシの外部 URL、内部 URL、SSL 証明書のプロパティを更新するための作成、読み取り、更新、削除 (CRUD) アクセス許可を委任します。 必要なアクセス許可:

- microsoft.directory/applications/applicationProxy/read
- microsoft.directory/connectorGroups/allProperties/read
- microsoft.directory/applications/basic/update
- Microsoftのディレクトリ/アプリケーション/認証/更新
- microsoft.ディレクトリ/アプリケーション/アプリケーションプロキシ認証/更新
- microsoft.directory/applications/applicationProxySslCertificate/update
- microsoft.directory/applications/applicationProxyUrlSettings/update

### アクセス許可の完全な一覧

| 許可 | 説明 |
| --- | --- |
| microsoft.directory/applicationPolicies/allProperties/read | アプリケーション ポリシーのすべてのプロパティ (特権プロパティを含む) の読み取り |
| microsoft.directory/applicationPolicies/allProperties/update | アプリケーション ポリシーのすべてのプロパティ (特権プロパティを含む) を更新する |
| microsoft.directory/applicationPolicies/basic/update | アプリケーション ポリシーの標準プロパティを更新する |
| microsoft.directory/applicationPolicies/create | アプリケーション ポリシーを作成する |
| microsoft.directory/applicationPolicies/createAsOwner | アプリケーション ポリシーを作成し、作成者を最初の所有者として追加する |
| microsoft.directory/applicationPolicies/delete | アプリケーション ポリシーを削除する |
| microsoft.directory/applicationPolicies/owners/read | アプリケーション ポリシーの所有者を読み取る |
| microsoft.directory/applicationPolicies/owners/update | アプリケーション ポリシーの所有者プロパティを更新する |
| microsoft.directory/applicationPolicies/policyAppliedTo/read | オブジェクトの一覧に適用されるアプリケーション ポリシーの読み取り |
| microsoft.directory/applicationPolicies/standard/read | アプリケーション ポリシーの標準プロパティの読み取り |
| microsoft.directory/servicePrincipals/allProperties/allTasks | サービス プリンシパルの作成と削除、およびすべてのプロパティの読み取りと更新 |
| microsoft.directory/servicePrincipals/allProperties/read | servicePrincipals のすべてのプロパティ (特権プロパティを含む) を読み取ります |
| microsoft.directory/servicePrincipals/allProperties/update | servicePrincipals のすべてのプロパティ (特権プロパティを含む) を更新する |
| microsoft.directory/servicePrincipals/appRoleAssignedTo/read | サービス プリンシパルのロールの割り当てを読み取る |
| microsoft.directory/servicePrincipals/appRoleAssignedTo/update | サービス プリンシパル ロールの割り当てを更新する |
| microsoft.directory/servicePrincipals/appRoleAssignments/read | サービス プリンシパルに割り当てられたロールの割り当てを読み取る |
| microsoft.directory/servicePrincipals/audience/update | サービス プリンシパルで対象ユーザー プロパティを更新する |
| microsoft.directory/servicePrincipals/authentication/update | サービス プリンシパルの認証プロパティを更新する |
| microsoft.directory/servicePrincipals/basic/update | サービス プリンシパルの基本プロパティを更新する |
| microsoft.directory/servicePrincipals/create | サービス プリンシパルを作成する |
| microsoft.directory/servicePrincipals/createAsOwner | 作成者を最初の所有者として使用してサービス プリンシパルを作成する |
| microsoft.directory/servicePrincipals/credentials/update | サービス プリンシパルの資格情報を更新する |
| マイクロソフトディレクトリ/サービスプリンシパル/削除 | サービス プリンシパルを削除する |
| microsoft.directory/servicePrincipals/disable | サービス プリンシパルを無効にする |
| microsoft.directory/servicePrincipals/enable | サービス プリンシパルを有効にする |
| microsoft.directory/servicePrincipals/getPasswordSingleSignOnCredentials | サービス プリンシパルのパスワード シングル サインオン資格情報を読み取る |
| microsoft.directory/servicePrincipals/managePasswordSingleSignOnCredentials | サービス プリンシパルでパスワード シングル サインオン資格情報を管理する |
| microsoft.directory/servicePrincipals/oAuth2PermissionGrants/read | サービス プリンシパルの委任されたアクセス許可付与を読み取る |
| microsoft.directory/servicePrincipals/owners/read | サービス プリンシパルの所有者を読み取る |
| microsoft.directory/servicePrincipals/owners/update | サービス プリンシパルの所有者を更新する |
| microsoft.directory/servicePrincipals/permissions/update | サービス プリンシパルのアクセス許可を更新する |
| microsoft.directory/servicePrincipals/policies/read | サービス プリンシパルのポリシーを読み取る |
| microsoft.directory/servicePrincipals/policies/update | サービス プリンシパルのポリシーを更新する |
| microsoft.directory/servicePrincipals/standard/read | サービス プリンシパルの基本プロパティを読み取る |
| microsoft.directory/servicePrincipals/synchronization/standard/read | サービス プリンシパルに関連付けられているプロビジョニング設定を読み取る |
| microsoft.directory/servicePrincipals/tag/update | サービス プリンシパルのタグ プロパティを更新する |
| microsoft.directory/applicationTemplates/instantiate | アプリケーション テンプレートからギャラリー アプリケーションをインスタンス化する |
| microsoft.directory/auditLogs/allProperties/read | 特権プロパティを含め、監査ログのすべてのプロパティを読み取ります |
| microsoft.directory/signInReports/allProperties/read | 特権プロパティを含め、サインイン レポートのすべてのプロパティを読み取る |
| microsoft.directory/applications/applicationProxy/read | すべてのアプリケーション プロキシ プロパティの読み取り |
| microsoft.directory/applications/applicationProxy/update | すべてのアプリケーション プロキシ プロパティを更新する |
| microsoft.directory/applications/applicationProxyAuthentication/update | すべての種類のアプリケーションで認証を更新する |
| microsoft.directory/applications/applicationProxyUrlSettings/update | アプリケーション プロキシの URL 設定を更新する |
| microsoft.directory/applications/applicationProxySslCertificate/update | アプリケーション プロキシの SSL 証明書設定を更新する |
| microsoft.directory/applications/synchronization/standard/read | アプリケーション オブジェクトに関連付けられているプロビジョニング設定の読み取り |
| microsoft.directory/connectorGroups/create | プライベート ネットワーク コネクタ グループを作成する |
| microsoft.directory/connectorGroups/delete | プライベート ネットワーク コネクタ グループを削除する |
| microsoft.directory/connectorGroups/allProperties/read | プライベート ネットワーク コネクタ グループのすべてのプロパティを読み取る |
| microsoft.directory/connectorGroups/allProperties/update | プライベート ネットワーク コネクタ グループのすべてのプロパティを更新する |
| microsoft.directory/connectors/create | プライベート ネットワーク コネクタを作成する |
| microsoft.directory/connectors/allProperties/read | プライベート ネットワーク コネクタのすべてのプロパティの読み取り |
| microsoft.directory/servicePrincipals/synchronizationJobs/manage | アプリケーション プロビジョニング同期ジョブを開始、再起動、および一時停止する |
| microsoft.directory/servicePrincipals/synchronization/standard/read | サービス プリンシパルに関連付けられているプロビジョニング設定を読み取る |
| microsoft.directory/servicePrincipals/synchronizationSchema/manage | アプリケーション プロビジョニング同期ジョブとスキーマを作成および管理する |
| microsoft.directory/provisioningLogs/allProperties/read | プロビジョニング ログのすべてのプロパティの読み取り |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/custom-enterprise-apps"} -->
## Microsoft Entra ID でエンタープライズ アプリを管理するカスタム ロールを作成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-enterprise-apps
- Service: entra-id / role-based-access-control
- Article date: 2025-01-03
- Summary: Microsoft Entra ID でエンタープライズ アプリ アクセス用のカスタム Microsoft Entra ロールを作成して割り当てる

この記事では、Microsoft Entra ID でユーザーとグループのエンタープライズ アプリの割り当てを管理するためのアクセス許可を持つカスタム ロールを作成する方法について説明します。 ロールの割り当ての要素と、サブタイプ、アクセス許可、プロパティ セットなどの用語の意味については、 [カスタム ロールの概要](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview)を参照してください。

### 前提条件

- Microsoft Entra ID P1 または P2 ライセンス
- 特権ロール管理者
- [PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) を使用する場合の Microsoft Graph PowerShell モジュール
- Microsoft Graph API の Graph エクスプローラーを使用する場合の管理者の同意

詳細については、「powerShell または Graph Explorerを使用するための 前提条件」を参照してください。

### エンタープライズ アプリ ロールのアクセス許可

この記事では、2 つのエンタープライズ アプリのアクセス許可について説明します。 すべての例で更新アクセス許可が使用されます。

- スコープでユーザーとグループの割り当てを読み取るには、`microsoft.directory/servicePrincipals/appRoleAssignedTo/read` アクセス許可を付与します
- スコープでユーザーとグループの割り当てを管理するには、`microsoft.directory/servicePrincipals/appRoleAssignedTo/update` アクセス許可を付与します

更新アクセス許可を付与すると、担当者はエンタープライズ アプリへのユーザーとグループの割り当てを管理できるようになります。 ユーザーやグループの割り当てのスコープは、単一のアプリケーションに対して付与することも、すべてのアプリケーションに対して付与することもできます。 組織全体のレベルで付与された場合、担当者はすべてのアプリケーションの割り当てを管理できます。 アプリケーション レベルで行われた場合、担当者は、指定されたアプリケーションのみの割り当てを管理できます。

更新アクセス許可の付与は、次の 2 つの手順で行われます。

1. `microsoft.directory/servicePrincipals/appRoleAssignedTo/update` アクセス許可を持つカスタム ロールを作成します
2. エンタープライズ アプリへのユーザーとグループの割り当てを管理するためのアクセス許可を、ユーザーまたはグループに付与します。 これは、スコープを組織全体のレベルまたは単一のアプリケーションに設定できる場合です。

## [管理センター](#tab/admin-center)
#### 新しいカスタム ロールを作成する

Microsoft Entra 管理センターでは、エンタープライズ アプリのアクセスとアクセス許可を制御するカスタム ロールを作成および管理できます。

注

カスタム ロールは組織全体のレベルで作成および管理され、組織の [概要] ページからのみ使用できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. Entra ID役割 & 管理者に移動します。
3. [ **新しいカスタム ロール**] を選択します。

    [Image: Microsoft Entra 管理センターの [ロールと管理者] ページのスクリーンショット。]
4. [ **基本** ] タブで、ロールの名前に [ユーザーとグループの割り当ての管理] を指定し、ロールの説明に [ユーザーとグループの割り当てを管理するためのアクセス許可を付与する] を指定し、[ **次へ**] を選択します。

    [Image: カスタム ロールの名前と説明を指定する [基本] タブのスクリーンショット。]
5. [ **アクセス許可** ] タブで、検索ボックスに「microsoft.directory/servicePrincipals/appRoleAssignedTo/update」と入力し、目的のアクセス許可の横にあるチェックボックスをオンにして、[ **次へ**] を選択します。

    [Image: カスタム ロールにアクセス許可を追加する [アクセス許可] タブのスクリーンショット。]
6. [ **確認と作成** ] タブで、アクセス許可を確認し、[ **作成**] を選択します。

    [Image: カスタム ロールを作成するための [確認と作成] タブのスクリーンショット。]

#### Microsoft Entra 管理センターを使用してユーザーにロールを割り当てる

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. Entra ID役割 & 管理者に移動します。
3. [ **ユーザーとグループの割り当ての管理** ] ロールを選択します。

    [Image: カスタム ロールを検索する [ロールと管理者] ページのスクリーンショット。]
4. [ **割り当ての追加]** を選択し、目的のユーザーを選択し、[ **選択** ] をクリックしてロールの割り当てをユーザーに追加します。

    [Image: ユーザーにカスタム ロールを割り当てる [割り当ての追加] ページのスクリーンショット。]

##### 割り当てのヒント

- 組織全体のすべてのエンタープライズ アプリのユーザーとグループ アクセスを管理するためのアクセス許可を担当者に付与するには、組織の Microsoft Entra ID **の [概要**] ページにある組織全体の**ロールと管理者**の一覧から開始します。
- 特定のエンタープライズ アプリのユーザーとグループ アクセスを管理するためのアクセス許可を担当者に付与するには、Microsoft Entra ID でそのアプリに移動し、そのアプリの **ロールと管理者** の一覧で開きます。 新しいカスタム ロールを選択し、ユーザーまたはグループの割り当てを完了します。 担当者は、特定のアプリのユーザーとグループのアクセスのみを管理できます。
- カスタム ロールの割り当てをテストするには、担当者としてサインインし、アプリケーションの **[ユーザーとグループ** ] ページを開き、[ **ユーザーの追加]** オプションが有効になっていることを確認します。

    [Image: ユーザーのアクセス許可を確認するための [ユーザーとグループ] ページのスクリーンショット。]

## [PowerShell](#tab/ms-powershell)
詳細については、「 [Microsoft Entra ID でカスタム ロールを作成](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create) する」および「 [Microsoft Entra ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)」を参照してください。

#### カスタム ロールを作成する

次の PowerShell スクリプトを実行して、新しいロールを作成します。

```PowerShell
# Basic role information
$description = "Can manage user and group assignments for Applications"
$displayName = "Manage user and group assignments"
$templateId = (New-Guid).Guid

# Set of permissions to grant
$allowedResourceAction = @("microsoft.directory/servicePrincipals/appRoleAssignedTo/update")
$rolePermission = @{'allowedResourceActions'= $allowedResourceAction}
$rolePermissions = $rolePermission

# Create new custom admin role
$customRole = New-MgRoleManagementDirectoryRoleDefinition -Description $description `
   -DisplayName $displayName -RolePermissions $rolePermissions -TemplateId $templateId -IsEnabled
```

#### カスタム ロールを割り当てる

この PowerShell スクリプトを使用して、ロールを割り当てます。

```powershell
# Get the user and role definition you want to link
$user =  Get-MgUser -Filter "userPrincipalName eq 'chandra@example.com'"
$roleDefinition = Get-MgRoleManagementDirectoryRoleDefinition -Filter "displayName eq 'Manage user and group assignments'"

# Get app registration and construct scope for assignment.
$appRegistration = Get-MgApplication -Filter "displayName eq 'My Filter Photos'"
$directoryScope = '/' + $appRegistration.Id

# Create a scoped role assignment
$roleAssignment = New-MgRoleManagementDirectoryRoleAssignment -DirectoryScopeId $directoryScope `
   -PrincipalId $user.Id -RoleDefinitionId $roleDefinition.Id
```

## [Graph API](#tab/ms-graph)
[Create unifiedRoleDefinition](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-post-roledefinitions) API を使用してカスタム ロールを作成します。 詳細については、「 [Microsoft Entra ID でカスタム ロールを作成](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create) し、 [Microsoft Entra ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)」を参照してください。

```http
POST https://graph.microsoft.com/v1.0/roleManagement/directory/roleDefinitions

{
    "description": "Can manage user and group assignments for Applications.",
    "displayName": "Manage user and group assignments",
    "isEnabled": true,
    "rolePermissions":
    [
        {
            "allowedResourceActions":
            [
                "microsoft.directory/servicePrincipals/appRoleAssignedTo/update"
            ]
        }
    ],
    "templateId": "<PROVIDE NEW GUID HERE>",
    "version": "1"
}
```

#### Microsoft Graph API を使用してカスタム ロールを割り当てる

[Create unifiedRoleAssignment](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-post-roleassignments) API を使用して、カスタム ロールを割り当てます。 ロールの割り当てでは、セキュリティ プリンシパル ID (ユーザーでもサービス プリンシパルでも可)、ロール定義 ID、および Microsoft Entra リソース スコープを組み合わせます。 ロールの割り当ての要素の詳細については、[カスタム ロールの概要](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview)を参照してください。

```http
POST https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments

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

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/custom-group-permissions"} -->
## Microsoft Entra カスタム ロールのグループ管理アクセス許可 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-group-permissions
- Service: entra-id / role-based-access-control
- Article date: 2024-08-25
- Summary: Microsoft Entra 管理センター、PowerShell、または Microsoft Graph API での Microsoft Entra カスタム ロールに対するグループ管理アクセス許可。

グループ管理アクセス許可を Microsoft Entra ID のカスタム ロール定義で使用すると、次のようなきめ細かいアクセス権を付与できます:

- 名前や説明など、グループのプロパティを管理する
- メンバーと所有者を管理する
- グループを作成または削除する
- 監査ログを読み取る
- 特定の種類のグループを管理する

この記事では、さまざまなグループ管理シナリオのカスタム ロールで使用できるアクセス許可の一覧を示します。 カスタム ロールを作成する方法については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)でカスタム ロールを作成する」を参照してください。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 自分の要件に適したライセンスを探すには、「[一般提供されている Microsoft Entra ID の機能の比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)」を参照してください。

### グループ管理アクセス許可を解釈する方法

グループ管理アクセス許可を解釈するには、さまざまなアクセス許可のサブタイプの意味を理解すると役立ちます。

| アクセス許可のサブタイプ | アクセス許可のサブタイプの説明 |
| --- | --- |
| グループ | ロール割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループを管理する |
| groups.unified | ロール割り当て可能なグループを除き、メンバーシップ種類が動的と割り当て済みの両方の Microsoft 365 グループを管理する |
| groups.unified.assignedMembership | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みのみの Microsoft 365 グループを管理する |
| groups.security | ロール割り当て可能なグループを除き、メンバーシップ種類が動的と割り当て済みの両方のセキュリティ グループを管理する |
| groups.security.assignedMembership | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みのみのセキュリティ グループを管理する |

次の表に、さまざまなサブタイプのグループ メンバーを更新するためのアクセス許可の例を示します。

| アクセス許可の例 | アクセス許可の説明 |
| --- | --- |
| microsoft.directory/**groups**/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/**groups.unified**/members/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/**groups.unified.assignedMembership**/members/update | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みである Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/**groups.security**/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループのメンバーを更新する |
| microsoft.directory/**groups.security.assignedMembership**/members/update | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みであるセキュリティ グループのメンバーを更新する |

### グループ情報を読み取る

次のアクセス許可を使用すると、グループのプロパティ、メンバー、所有者を読み取ることができます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/groups/allProperties/read | ロールを割り当て可能なグループを含む、セキュリティ グループと Microsoft 365 グループのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.directory/groups/standard/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの標準プロパティを読み取る |
| microsoft.directory/groups/members/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループのメンバーを読み取る |
| microsoft.directory/groups/memberOf/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの memberOf プロパティを読み取る |
| microsoft.directory/groups/owners/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの所有者を読み取る |

### グループの作成

次のアクセス許可を使用すると、さまざまな種類のグループを作成できます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/groups/create | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループを作成する |
| microsoft.directory/groups.unified/create | ロールを割り当て可能なグループを除き、Microsoft 365 グループを作成する |
| microsoft.directory/groups.unified.assignedMembership/create | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みの Microsoft 365 グループを作成する |
| microsoft.directory/groups.security/create | ロールを割り当て可能なグループを除き、セキュリティ グループを作成する |
| microsoft.directory/groups.security.assignedMembership/create | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みのセキュリティ グループを作成する |
| microsoft.directory/groups/createAsOwner | ロール割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループを作成する。 作成者は最初の所有者として追加されます。 |
| microsoft.directory/groups.unified/createAsOwner | ロール割り当て可能なグループを除き、Microsoft 365 グループを作成する。 作成者は最初の所有者として追加されます。 |
| microsoft.directory/groups.unified.assignedMembership/createAsOwner | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みの Microsoft 365 グループを作成する。 作成者は最初の所有者として追加されます。 |
| microsoft.directory/groups.security/createAsOwner | ロールを割り当て可能なグループを除き、セキュリティ グループを作成する 作成者は最初の所有者として追加されます。 |
| microsoft.directory/groups.security.assignedMembership/createAsOwner | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みのセキュリティ グループを作成する。 作成者は最初の所有者として追加されます。 |

### グループ情報を更新する

次のアクセス許可を使用すると、グループのプロパティとメンバーを更新できます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/groups/allProperties/update | ロール割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループのすべてのプロパティ (特権プロパティを含む) を更新する |
| microsoft.directory/groups.unified/allProperties/update | ロール割り当て可能なグループを除き、Microsoft 365 グループのすべてのプロパティ (特権プロパティを含む) を更新する |
| microsoft.directory/groups.unified.assignedMembership/allProperties/update | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みである Microsoft 365 グループのすべてのプロパティ (特権プロパティを含む) を更新する |
| microsoft.directory/groups.security/allProperties/update | ロール割り当て可能なグループを除き、セキュリティ グループのすべてのプロパティ (特権プロパティを含む) を更新する |
| microsoft.directory/groups.security.assignedMembership/allProperties/update | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みであるセキュリティ グループのすべてのプロパティ (特権プロパティを含む) を更新する |
| microsoft.directory/グループ/基本/更新 | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの基本プロパティを更新する |
| microsoft.directory/groups.unified/basic/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループの基本プロパティを更新する |
| microsoft.directory/groups.unified.assignedMembership/basic/update | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みである Microsoft 365 グループの基本プロパティを更新する |
| microsoft.directory/groups.security/basic/update | ロールを割り当て可能なグループを除き、セキュリティ グループの基本プロパティを更新する |
| microsoft.directory/groups.security.assignedMembership/basic/update | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みであるセキュリティ グループの基本プロパティを更新する |
| microsoft.directory/groups/classification/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの分類プロパティを更新する |
| microsoft.directory/groups.unified/classification/update | ロール割り当て可能なグループを除き、Microsoft 365 グループの分類プロパティを更新する |
| microsoft.directory/groups.unified.assignedMembership/classification/update | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みである Microsoft 365 グループの分類プロパティを更新する |
| microsoft.directory/groups.security/classification/update | ロールを割り当て可能なグループを除き、セキュリティ グループの分類プロパティを更新する |
| microsoft.directory/groups.security.assignedMembership/classification/update | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みであるセキュリティ グループの分類プロパティを更新する |
| microsoft.directory/groups/dynamicMembershipRule/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの動的メンバーシップ グループのルールを更新する |
| microsoft.directory/groups.unified/dynamicMembershipRule/update | ロール割り当て可能なグループを除き、Microsoft 365 グループの動的メンバーシップ グループのルールを更新する |
| microsoft.directory/groups.security/dynamicMembershipRule/update | ロール割り当て可能なグループを除き、セキュリティ グループの動的メンバーシップ グループのルールを更新する |
| microsoft.directory/groups/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups.unified/members/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups.unified.assignedMembership/members/update | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みである Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups.security/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループのメンバーを更新する |
| microsoft.directory/groups.security.assignedMembership/members/update | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みであるセキュリティ グループのメンバーを更新する |

### さまざまなグループの種類のメンバーを更新する

次のアクセス許可を使用すると、さまざまなグループの種類のメンバーを更新できます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/groups/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups.unified/members/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups.unified.assignedMembership/members/update | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みである Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups.security/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループのメンバーを更新する |
| microsoft.directory/groups.security.assignedMembership/members/update | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みであるセキュリティ グループのメンバーを更新する |

### グループを削除する

次のアクセス許可を使用すると、グループを削除できます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/groups/delete | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループを削除する |
| microsoft.directory/groups.unified/members/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups.unified.assignedMembership/members/update | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みである Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups.security/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループのメンバーを更新する |
| microsoft.directory/groups.security.assignedMembership/members/update | ロール割り当て可能なグループを除き、メンバーシップの種類が割り当て済みであるセキュリティ グループのメンバーを更新する |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/custom-overview"} -->
## Microsoft Entra ロールベースのアクセス制御 (RBAC) の概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview
- Service: entra-id / role-based-access-control
- Article date: 2026-10-01
- Summary: Microsoft Entra ID でロールの割り当てと制限付きスコープの部分を理解する方法について説明します。

この記事では、Microsoft Entra のロールベースのアクセス制御を理解する方法について説明します。 Microsoft Entra ロールを使用すると、最小特権の原則に従って、管理者に詳細なアクセス許可を付与できます。 Microsoft Entra の組み込みロールとカスタム ロールは、Azure リソース (Azure ロール) のロールベースのアクセス制御システム で見つけた概念に基づいて動作します。 これらの 2 つのロールベースのアクセス制御システム違いは次のとおりです。

- Microsoft Entra ロールは、Microsoft Graph API を使用して、ユーザー、グループ、アプリケーションなどの Microsoft Entra リソースへのアクセスを制御します
- Azure ロールは、Azure Resource Management を使用して仮想マシンやストレージなどの Azure リソースへのアクセスを制御します

どちらのシステムにも、同様に使用されるロール定義とロールの割り当てが含まれています。 ただし、Microsoft Entra ロールのアクセス許可は、Azure カスタム ロールでは使用できません。その逆も同様です。

### Microsoft Entra のロールベースのアクセス制御について

Microsoft Entra ID では、次の 2 種類のロール定義がサポートされています。

- [組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)
- [カスタム ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)

組み込みロールは、一連のアクセス許可が固定された、すぐに使えるロールです。 これらのロール定義は変更できません。 Microsoft Entra ID では多数の[組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)がサポートされており、その数は増え続けています。 エッジを切り捨て、高度な要件を満たすために、Microsoft Entra ID はカスタム ロールもサポートしています。 カスタムの Microsoft Entra ロールを使用してアクセス許可を付与することは、カスタム ロール定義を作成し、ロールの割り当てを使用して割り当てる 2 段階のプロセスです。 カスタム ロール定義は、プリセット リストから追加するアクセス許可のコレクションです。 これらのアクセス許可は、組み込みロールで使用されるのと同じアクセス許可です。

カスタム ロール定義を作成 (または組み込みロールを使用) したら、ロールの割り当てを作成してユーザーに割り当てることができます。 ロールの割り当てにより、指定したスコープのロール定義のアクセス許可がユーザーに付与されます。 この 2 段階のプロセスでは、1 つのロール定義を作成し、異なるスコープで何度も割り当てることができます。 スコープは、ロール メンバーがアクセスできる Microsoft Entra リソースのセットを定義します。 最も一般的なスコープは、組織全体のスコープです。 カスタム ロールは組織全体のスコープで割り当てることができます。つまり、ロール メンバーは組織内のすべてのリソースに対するロールのアクセス許可を持ちます。 カスタム ロールは、オブジェクト スコープで割り当てることもできます。 オブジェクト スコープの例として、1 つのアプリケーションがあります。 同じロールを組織内のすべてのアプリケーションの 1 人のユーザーに割り当て、次に Contoso Expense Reports アプリのみのスコープを持つ別のユーザーに割り当てることができます。

#### ユーザーがリソースにアクセスできるかどうかを Microsoft Entra ID が判断する方法

管理リソースにアクセスできるかどうかを判断するために Microsoft Entra ID が使用する大まかな手順を次に示します。 この情報を使用して、アクセスの問題のトラブルシューティングを行います。

1. ユーザー (またはサービス プリンシパル) が Microsoft Graph エンドポイントへのトークンを取得します。
2. ユーザーは、発行されたトークンを使用して、Microsoft Graph 経由で Microsoft Entra ID への API 呼び出しを行います。
3. 状況に応じて、Microsoft Entra ID は次のいずれかのアクションを実行します。
    - ユーザーのアクセス トークン内の [wids 要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens) に基づいて、ユーザーのロール メンバーシップを評価します。
    - ユーザーに適用されるすべてのロールの割り当てを、直接またはグループ メンバーシップを使用して、アクションが実行されているリソースに対して取得します。
4. Microsoft Entra ID は、API 呼び出しのアクションがこのリソースに対してユーザーが持っているロールに含まれているかどうかを決定します。
5. ユーザーが要求されたスコープでアクションを持つロールを持っていない場合、アクセス権は付与されません。 それ以外の場合は、アクセス権が付与されます。

### ロールの割り当て

ロールの割り当ては、Microsoft Entra リソースへのアクセスを許可するために、特定の *[スコープ]* で *[セキュリティ プリンシパル]* に *[ロールの定義]* を関連付ける Microsoft Entra リソースです。 アクセス権はロールの割り当てを作成することによって付与され、アクセスはロールの割り当てを削除することで取り消されます。 その中核となるロールの割り当ては、次の 3 つの要素で構成されます。

- セキュリティ プリンシパル - アクセス許可を取得する ID。 ユーザー、グループ、またはサービス プリンシパルを指定できます。
- ロール定義 - 権限のコレクション。
- スコープ - これらのアクセス許可が適用される場所を制限する方法。

Microsoft Entra 管理センター、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)、または Microsoft Graph API を使用して、[ロールの割り当てを作成](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/view-assignments)および[ロールの割り当てを一覧表示](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview)できます。 Azure CLI は、Microsoft Entra ロールの割り当てではサポートされていません。

次の図は、ロールの割り当ての例を示しています。 この例では、Chris に Contoso Widget Builder アプリ登録のスコープでアプリ登録管理者のカスタム ロールが割り当てられています。 この割り当てにより、この特定のアプリ登録に対してのみ、アプリ登録管理者ロールのアクセス許可が Chris に付与されます。

[Image: 3 つの部分で構成されるロールの割り当ての図。]

#### セキュリティ プリンシパル

セキュリティ プリンシパルは、Microsoft Entra リソースへのアクセスが割り当てられているユーザー、グループ、またはサービス プリンシパルを表します。 ユーザーとは、Microsoft Entra ID のユーザー プロファイルを持つ個人です。 グループは、[ロール割り当て可能なグループ](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)として設定された新しい Microsoft 365 またはセキュリティ グループです。 サービス プリンシパルは、アプリケーション、ホステッド サービス、および Microsoft Entra リソースにアクセスするための自動化ツールで使用するために作成された ID です。

#### ロールの定義

ロール定義またはロールは、アクセス許可のコレクションです。 ロール定義には、作成、読み取り、更新、削除など、Microsoft Entra リソースで実行できる操作が一覧表示されます。 Microsoft Entra ID には、次の 2 種類のロールがあります。

- 変更できない Microsoft によって作成された組み込みロール。
- 組織によって作成および管理されるカスタム ロール。

#### Scope

スコープは、ロールの割り当ての一部として、許可されるアクションを特定のリソース セットに制限する方法です。 たとえば、開発者にカスタム ロールを割り当てるが、特定のアプリケーション登録のみを管理する場合は、ロールの割り当てに特定のアプリケーション登録をスコープとして含めることができます。

ロールを割り当てるときは、次のいずれかの種類のスコープを指定します。

- テナント
- [管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units)
- Microsoft Entra リソース

スコープとして Microsoft Entra リソースを指定する場合は、次のいずれかになります。

- Microsoft Entra グループ
- エンタープライズ アプリケーション
- アプリケーションの登録

テナントや管理単位などのコンテナー スコープにロールが割り当てられると、コンテナー自体には含まれていないオブジェクトに対するアクセス許可が付与されます。 それどころか、ロールがリソース スコープに割り当てられると、リソース自体に対するアクセス許可が付与されますが、それ以上のアクセス許可は付与されません (特に、Microsoft Entra グループのメンバーには拡張されません)。

詳細については、「[Microsoft Entra ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)」を参照してください。

### ロールの割り当てオプション

Microsoft Entra ID には、ロールを割り当てるための複数のオプションが用意されています。

- ロールをユーザーに直接割り当てることができます。これは、ロールを割り当てる既定の方法です。 アクセス要件に基づいて、組み込みロールとカスタム Microsoft Entra ロールの両方をユーザーに割り当てることができます。 詳細については、「[Microsoft Entra ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)」を参照してください。
- Microsoft Entra ID P1 を使用すると、ロール割り当て可能なグループを作成し、これらのグループにロールを割り当てることができます。 個人ではなくグループにロールを割り当てると、ロールからユーザーを簡単に追加または削除でき、グループのすべてのメンバーに対して一貫したアクセス許可が作成されます。 詳細については、「[Microsoft Entra ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)」を参照してください。
- Microsoft Entra ID P2 を使用すると、Microsoft Entra Privileged Identity Management (Microsoft Entra PIM) を使用して、ロールへの Just-In-Time アクセスを提供できます。 この機能を使用すると、永続的なアクセス権を付与するのではなく、ロールを必要とするユーザーに時間制限付きアクセス権を付与できます。 また、詳細なレポート機能と監査機能も提供します。 詳細については、「Microsoft Entra の役割を Privileged Identity Managementに割り当てる 」を参照してください。

### 誰が何にアクセスできるかを理解する

ロールの割り当ての一覧表示は、より広範な質問に答える部分の 1 つです。"誰が自分の組織内の何にアクセスできるか" Microsoft Entra IDには、テナント全体のアクセスを可視化できるツールがいくつか用意されています。

- **ロールの割り当て。** テナント、アプリケーション、または管理単位のスコープで Microsoft Entraロールが割り当てられている対象を一覧表示するには、[List Microsoft Entra role assignments](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/view-assignments) の手順を使用します。 オフライン分析用の CSV として[ロールの割り当てをダウンロード](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/view-assignments#download-role-assignments)したり、[List unifiedRoleAssignments](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-list-roleassignments) Microsoft Graph API を使用してプログラムでクエリを実行したりできます。
- **アプリ ロールの割り当てと同意の付与。** アプリケーション [にユーザーとグループを割り当てて](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal) 、特定のエンタープライズ アプリケーションにアクセスできるユーザーとグループを確認します。 [アプリケーションに付与されたレビューアクセス許可を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-application-permissions)使用して、ユーザーまたは管理者が同意した委任されたアクセス許可とアプリケーションのアクセス許可を検査します。
- **カスタム セキュリティ属性。**[カスタム セキュリティ属性を](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview)使用して、テナント用に定義したビジネス固有の属性でユーザーとサービス プリンシパルにタグを付けます。 その後、属性でディレクトリをフィルター処理してクエリを実行し、ロールベースのクエリを補完するアクセスのビジネス属性ビューを作成できます。
- **アクセス レビュー。**[アクセス レビューを](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview)使用して、ユーザーが現在のグループ メンバーシップとエンタープライズ アプリケーションへの割り当てを引き続き必要としていることを定期的に確認します。 [Privileged Identity Management (PIM) アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)を使用して、Microsoft EntraまたはAzureリソース ロールに割り当てられているユーザーとサービス プリンシパルを確認します。 レビュー担当者は、各ユーザーの継続的なアクセスを承認または拒否します。 アクセス レビューにはMicrosoft Entra ID ガバナンスまたはMicrosoft Entra スイートが必要です。一部の機能は Microsoft Entra ID P2 で動作します。 詳細については、「 [ライセンス要件](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview#license-requirements)」を参照してください。 PIM を使用してサービス プリンシパルを確認するには、Microsoft Entra ワークロード ID Premium も必要です。
- **権限管理。**[エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview)を使用して、アクセス パッケージを使用してアクセス権が付与されているユーザーを確認します。 アクセス パッケージは、関連するリソースのコンテナーであるカタログに編成され、アクセスの委任と管理に使用できるアクセス パッケージです。 エンタイトルメント管理には、Microsoft Entra ID ガバナンスまたはMicrosoft Entra スイートが必要です。一部の機能は、Microsoft Entra ID P2 で動作します。 詳細については、「 [ライセンス要件](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview#license-requirements)」を参照してください。
- **サインインログと監査ログ。**[サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)を使用して、リソースにアクティブにアクセスしているユーザーと、どのような条件下にあるかを確認します。 [監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)を使用して、ロールの割り当て、グループ メンバーシップ、およびその他のディレクトリ オブジェクトに対する変更を経時的に追跡します。 監査ログは構成の変更を記録し、サインイン ログはサインイン イベントを記録します。これらのイベントは、付与されたアクセスと実際のアクセスを区別するのに役立ちます。 Microsoft Graph API 呼び出しのより豊富なトレースについては、[Microsoft Graph アクティビティ ログ](https://learn.microsoft.com/ja-jp/graph/microsoft-graph-activity-logs-overview)を参照してください。

Tip

大規模なテナントの場合は、[Log Analytics ワークスペース](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)にログをストリーミングして、何千ものユーザーとロールにわたるアクセス パターンのクエリと分析を行うことができます。

### ワークロード ID のアクセスを管理する

大規模な完全な承認戦略では、ユーザーだけでなく、アプリケーション、サービス プリンシパル、マネージド ID などのワークロード ID を対象にする必要があります。 Microsoft Entraでは、マシン ID を確立するためのいくつかの方法がサポートされており、それぞれ異なるシナリオに適しています。

- **[アプリの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。**[アプリケーション オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)を登録して、クライアント シークレット、証明書、またはフェデレーション資格情報で認証するサービス プリンシパルを作成します。 従来のアプリケーションとプラットフォームの統合に使用します。
- **[マネージドアイデンティティ](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)。** Azureで実行されているワークロードには、システム割り当てマネージド ID またはユーザー割り当てマネージド ID を使用します。 Azureは資格情報を管理するため、シークレットはコードまたは構成に格納されません。
- **[ワークロード ID フェデレーション](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation)。** Microsoft Entraと外部 ID プロバイダーの間の信頼を構成して、Azure外のワークロード (またはアプリ登録として認証されるAzure内のワークロード) が、シークレットを格納せずに保護されたリソースMicrosoft Entraアクセスできるようにします。
- **[柔軟なフェデレーション ID の認証情報 (プレビュー)](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-flexible-federated-identity-credentials).** アプリ登録のシークレットレス パターンを、GitHub、GitLab、または Terraform Cloud によって発行されたトークンに対してワイルドカードまたはクレームベースの照合を必要とするシナリオに拡張します。

ユーザーに適用するのと同じ階層化されたコントロールを使用して、これらの ID を大規模に管理します。

- [ワークロード ID に条件付きアクセスを](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/workload-identity)適用して、サービス プリンシパルが認証できる場所とタイミングを制限します。 この機能にはワークロード ID Premium ライセンスが必要であり、テナントに登録されているシングルテナント サービス プリンシパルにのみ適用されます。マネージド ID とマルチテナントまたはサード パーティの SaaS アプリはスコープ内にありません。
- グループとアプリケーションの[アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/create-access-review)を実行して、それらのリソースに割り当てられたユーザーが引き続きアクセス権を必要としていることを確認し、[PIM アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-create-roles-and-resource-roles-review)を使用して、Microsoft EntraおよびAzureリソース ロールに割り当てられたサービス プリンシパルを確認します。 グループとアプリケーションのレビューには、Microsoft Entra ID ガバナンスまたはMicrosoft Entra スイートが必要です (一部の機能は Microsoft Entra ID P2 で利用できます)。詳細については、「[License の要件](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview#license-requirements)を参照してください。 サービス プリンシパルのレビューには、さらに Microsoft Entra ワークロード ID Premium が必要です。
- 登録済みアプリケーションのサービス プリンシパルに [カスタム セキュリティ属性](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview) をタグ付けして、フィルター可能なインベントリを構築し、ビジネス属性に基づいて [Azure ABAC](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/conditions-custom-security-attributes) の決定を行えるようにします。
- シングルテナント アプリとマルチテナント アプリで [アプリ インスタンス プロパティ ロック](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-app-instance-property-locks) を有効にして、サービス プリンシパルの機密プロパティが不正に変更されないようにします。

### ライセンス要件

Microsoft Entra ID での組み込みロールの使用は無料です。 カスタム ロールを使用するには、カスタム ロールの割り当てを持つすべてのユーザーに対して Microsoft Entra ID P1 ライセンスが必要です。 要件に適したライセンスを見つけるには、「[Free エディションと Premium エディションの一般提供機能の比較」](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/custom-user-permissions"} -->
## Microsoft Entra カスタム ロールに対するユーザー管理アクセス許可 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-user-permissions
- Service: entra-id / role-based-access-control
- Article date: 2023-06-09
- Summary: Microsoft Entra 管理センター、PowerShell、または Microsoft Graph API での Microsoft Entra カスタム ロールに対するユーザー管理アクセス許可。

ユーザー管理アクセス許可を Microsoft Entra ID のカスタム ロール定義で使用すると、次のようなきめ細かいアクセス権を付与できます。

- ユーザーの基本プロパティの読み取りまたは更新
- ユーザーの ID の読み取り
- ユーザーのジョブ情報の読み取りまたは更新
- ユーザーの連絡先情報の更新
- ユーザーの保護者による制限の更新
- ユーザー設定の更新
- ユーザーの直属部下の読み取り
- ユーザーの拡張機能プロパティの更新
- ユーザーのデバイス情報の読み取り
- ユーザーのライセンスの読み取りまたは管理
- ユーザーのパスワード ポリシーの更新
- ユーザーの割り当てとメンバーシップの読み取り

この記事では、さまざまなユーザー管理シナリオのカスタム ロールで使用できるアクセス許可の一覧を示します。 カスタム ロールを作成する方法については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)でカスタム ロールを作成する」を参照してください。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 自分の要件に適したライセンスを探すには、「[一般提供されている Microsoft Entra ID の機能の比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)」を参照してください。

### ユーザーの基本プロパティの読み取りまたは更新

ユーザーの基本プロパティの読み取りまたは更新には、次のアクセス許可を使用できます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/users/standard/read | ユーザーの基本プロパティを読み取る |
| microsoft.directory/users/basic/update | ユーザーの基本プロパティを更新する |

### ユーザーの ID の読み取り

ユーザーの ID を読み取るには、次のアクセス許可を使用できます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/users/identities/read | ユーザーの ID の読み取り |

### ユーザーのジョブ情報の読み取りまたは更新

ユーザーのジョブ情報を読み取りまたは更新するには、次のアクセス許可を使用できます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/users/manager/read | ユーザーのマネージャーを読み取る |
| microsoft.directory/users/manager/update | ユーザーのマネージャーを更新する |
| microsoft.directory/users/jobInfo/update | ユーザーのジョブ情報の更新 |

### ユーザーの連絡先情報の更新

ユーザーの連絡先情報を更新するには、次のアクセス許可を使用できます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/users/contactInfo/update | ユーザーの連絡先プロパティの更新 |

### ユーザーの保護者による制限の更新

ユーザーの保護者による制御を更新するには、次のアクセス許可を使用できます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/users/parentalControls/update | ユーザーの保護者による制限の更新 |

### ユーザー設定の更新

ユーザー設定を更新するには、次のアクセス許可を使用できます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/users/usageLocation/update | ユーザーの利用場所を更新する |

### ユーザーの直属部下の読み取り

ユーザーの直属部下を読み取るために、次のアクセス許可を使用できます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/users/directReports/read | ユーザーの直属の部下を読み取る |

### ユーザーの拡張機能プロパティの更新

ユーザーの拡張機能のプロパティを更新するには、次のアクセス許可を使用できます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/users/extensionProperties/update | ユーザーの拡張機能プロパティの更新 |

### ユーザーのデバイス情報の読み取り

ユーザーのデバイス情報を読み取るために、次のアクセス許可を使用できます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/users/ownedDevices/read | ユーザーの所有デバイスを読み取る |
| microsoft.directory/users/registeredDevices/read | ユーザーの登録済みデバイスを読み取る |
| microsoft.directory/users/deviceForResourceAccount/read | ユーザーの deviceForResourceAccount を読み取る |

### ユーザーのライセンスの読み取りまたは管理

ユーザーのライセンスを読み取ったり管理したりするには、次のアクセス許可を使用できます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/users/licenseDetails/read | ユーザーのライセンスの詳細を読み取る |
| microsoft.directory/users/assignLicense | ユーザー ライセンスの管理 |
| microsoft.directory/users/reprocessLicenseAssignment | ユーザーのライセンス割り当てを再処理する |

### ユーザーのパスワード ポリシーの更新

ユーザーのパスワード ポリシーを更新するには、次のアクセス許可を使用できます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/users/passwordPolicies/update | ユーザーのパスワード ポリシー プロパティの更新 |

### ユーザーの割り当てとメンバーシップの読み取り

ユーザーの割り当てとメンバーシップを読み取るために、次のアクセス許可を使用できます。

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/users/appRoleAssignments/read | ユーザーのアプリケーション ロールの割り当てを読み取る |
| microsoft.directory/users/scopedRoleMemberOf/read | 管理単位にスコープが設定されている Microsoft Entra ロールのユーザーのメンバーシップを読み取る |
| microsoft.directory/users/memberOf/read | ユーザーの動的メンバーシップ グループを読み取る |

### アクセス許可の全一覧

| 権限 | 説明 |
| --- | --- |
| microsoft.directory/users/appRoleAssignments/read | ユーザーのアプリケーション ロールの割り当てを読み取る |
| microsoft.directory/users/assignLicense | ユーザー ライセンスの管理 |
| microsoft.directory/users/basic/update | ユーザーの基本プロパティを更新する |
| microsoft.directory/users/contactInfo/update | ユーザーの連絡先プロパティの更新 |
| microsoft.directory/users/deviceForResourceAccount/read | ユーザーの deviceForResourceAccount を読み取る |
| microsoft.directory/users/directReports/read | ユーザーの直属の部下を読み取る |
| microsoft.directory/users/extensionProperties/update | ユーザーの拡張機能プロパティの更新 |
| microsoft.directory/users/identities/read | ユーザーの ID の読み取り |
| microsoft.directory/users/jobInfo/update | ユーザーのジョブ情報の更新 |
| microsoft.directory/users/licenseDetails/read | ユーザーのライセンスの詳細を読み取る |
| microsoft.directory/users/manager/read | ユーザーのマネージャーを読み取る |
| microsoft.directory/users/manager/update | ユーザーのマネージャーを更新する |
| microsoft.directory/users/memberOf/read | ユーザーの動的メンバーシップ グループを読み取る |
| microsoft.directory/users/ownedDevices/read | ユーザーの所有デバイスを読み取る |
| microsoft.directory/users/parentalControls/update | ユーザーの保護者による制限の更新 |
| microsoft.directory/users/passwordPolicies/update | ユーザーのパスワード ポリシー プロパティの更新 |
| microsoft.directory/users/registeredDevices/read | ユーザーの登録済みデバイスを読み取る |
| microsoft.directory/users/reprocessLicenseAssignment | ユーザーのライセンス割り当てを再処理する |
| microsoft.directory/users/scopedRoleMemberOf/read | 管理単位にスコープが設定されている Microsoft Entra ロールのユーザーのメンバーシップを読み取る |
| microsoft.directory/users/standard/read | ユーザーの基本プロパティを読み取る |
| microsoft.directory/users/usageLocation/update | ユーザーの利用場所を更新する |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/delegate-app-roles"} -->
## アプリケーション管理の管理者アクセス許可を委任する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-app-roles
- Service: entra-id / role-based-access-control
- Article date: 2024-11-17
- Summary: Microsoft Entra ID でアプリケーション アクセス管理のアクセス許可を付与する

この記事では、Microsoft Entra ID のカスタム ロールによって付与されたアクセス許可を使用して、アプリケーション管理のニーズに対応する方法について説明します。 Microsoft Entra ID では、アプリケーションの作成と管理のアクセス許可を次の方法で委任できます。

- アプリケーションを作成できるユーザーを制限し、作成されたアプリケーションを管理できるユーザーを制限する。 Microsoft Entra ID の既定では、すべてのユーザーがアプリケーションを登録し、作成されたアプリケーションのすべての側面を管理できます。 この権限は、選択されたユーザーのみがそのアクセス許可を持つように制限できます。
- アプリケーションに 1 人以上の所有者を割り当てる。 これは、特定のアプリケーションについての Microsoft Entra 構成のすべての側面を管理する能力を一部のユーザーに付与するための簡単な方法です。
- すべてのアプリケーションに対する Microsoft Entra ID での構成を管理するためのアクセス権を付与する、組み込みの管理ロールを割り当てる。 これは、IT エキスパートに、幅広いアプリケーション構成を管理するためのアクセス権を付与しつつ、アプリケーション構成に関連しない Microsoft Entra の他の部分を管理するためのアクセス権を付与しないようにするための推奨される方法です。
- 非常に限定されたアクセス許可を定義するカスタム ロールを作成し、それを一部のユーザーに、限定された所有者として 1 つのアプリケーションのスコープに対して割り当てるか、または制限付き管理者としてディレクトリ スコープ (すべてのアプリケーション) で割り当てます。

上記の方法のいずれかを使用してアクセス権を付与することを検討することは、2 つの理由から重要です。 まず、管理タスクを実行する機能を委任すると、高い特権を持つ管理者のオーバーヘッドが削減されます。 2 番目の理由として、制限付きアクセス許可を使用することでセキュリティ体制が改善され、未承認アクセスの可能性が減少します。 ロール セキュリティの計画のガイドラインについては、「[Microsoft Entra ID でのハイブリッドおよびクラウド デプロイ用の特権アクセスをセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-planning)」を参照してください。

### アプリケーションを作成できるユーザーを制限する

Microsoft Entra ID の既定では、すべてのユーザーがアプリケーションを登録し、作成されたアプリケーションのすべての側面を管理できます。 また、すべてのユーザーは、アプリがユーザーの代わりに会社のデータにアクセスすることに同意することができます。 グローバル スイッチを [いいえ] に設定し、選択したユーザーをアプリケーション開発者ロールに追加することで、これらのアクセス許可を選択的に付与することができます。

#### アプリケーション登録またはアプリケーションへの同意を作成する既定の機能を無効にするには

アプリケーションの登録またはアプリケーションへの同意を作成する既定の機能を無効にするには、次の手順に従って、組織にこれらの設定の一方または両方を設定してください。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**ユーザー**&gt;**ユーザー設定**に移動します。
3. **[ユーザーはアプリケーションを登録できます]** の設定を **[いいえ]** に設定してください。

    これにより、ユーザーがアプリケーション登録を作成する既定の機能が無効になります。
4. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**同意とアクセス許可**に移動します。
5. **[ユーザーの同意を許可しない]** オプションを選択してください。

    これにより、アプリケーションがユーザーの代わりに会社のデータにアクセスすることにユーザーが同意する既定の機能が無効になります。

#### 既定の機能が無効になっている場合にアプリケーションを作成して同意するための個々のアクセス許可を付与する

[\[ユーザーはアプリケーションを登録できる\]](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer) 設定が [いいえ] に設定されている場合に、アプリケーション登録を作成できる能力を付与するための**アプリケーション開発者ロール**を割り当てます。 さらにこのロールでは、 **[ユーザーはアプリが自身の代わりに会社のデータにアクセスすることを許可できます]** 設定が [いいえ] に設定されている場合に、代わりに同意する権限を付与します。

### アプリケーションの所有者を割り当てる

所有者を割り当てることは、特定のアプリケーション登録またはエンタープライズ アプリケーションについての Microsoft Entra 構成のすべての側面を管理する能力を付与するための簡単な方法です。 詳細については、「[エンタープライズ アプリケーション所有者を割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-app-owners)」をご覧ください。

### 組み込みのアプリケーション管理者ロールを割り当てる

Microsoft Entra ID には、すべてのアプリケーションに対する Microsoft Entra ID での構成を管理するためのアクセス権を付与する、一連の組み込みの管理者ロールがあります。 これらのロールは、IT エキスパートに、幅広いアプリケーション構成を管理するためのアクセス権を付与しつつ、アプリケーション構成に関連しない Microsoft Entra の他の部分を管理するためのアクセス権を付与しないようにするために推奨される方法です。

- アプリケーション管理者:このロールのユーザーは、エンタープライズ アプリケーション、アプリケーション登録、アプリケーション プロキシの設定の全側面を作成して管理できます。 さらに、このロールは、委任されたアクセス許可とアプリケーション アクセス許可 (Microsoft Graph を除く) に同意する権限を付与します。 このロールに割り当てられたユーザーは、新しいアプリケーション登録またはエンタープライズ アプリケーションを作成する際に、所有者として追加されません。
- クラウド アプリケーション管理者:このロールのユーザーは、(アプリケーション プロキシを管理する権限を除き) アプリケーション管理者ロールと同じアクセス許可を持ちます。 このロールに割り当てられたユーザーは、新しいアプリケーション登録またはエンタープライズ アプリケーションを作成する際に、所有者として追加されません。

詳細およびこれらのロールの説明については、「[Microsoft Entra の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)」を参照してください。

[Microsoft Entra ID を使用してユーザーにロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-subscriptions-associated-directory)ことに関するハウツーガイドに記載されている手順に従って、アプリケーション管理者またはクラウド アプリケーション管理者のロールを割り当てます。

重要

アプリケーション管理者およびクラウド アプリケーション管理者は、アプリケーションに資格情報を追加し、その資格情報を使用してアプリケーションの ID を偽装することができます。 アプリケーションは、管理者ロールのアクセス許可を上回る特権の昇格となるアクセス許可を持つことができます。 このロールの管理者は、アプリケーションのアクセス許可に応じて、アプリケーションの偽装中にユーザーや他のオブジェクトを作成または更新する可能性があります。 いずれのロールでも、条件付きアクセスの設定の管理権限は付与されません。

### カスタム ロールの作成と割り当て (プレビュー)

カスタム ロールの作成とカスタム ロールの割り当ては別々の手順です。

- [カスタムの*ロール定義*を作成](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)し、[あらかじめ設定されている一覧からアクセス許可をその定義に追加](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-available-permissions)します。 これらは、組み込みロールで使用されるものと同じアクセス許可です。
- [*ロールの割り当て*を作成](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)し、カスタム ロールを割り当てます。

この分離により、1 つのロール定義を作成し、それを異なる*スコープ*で何度も割り当てることができます。 カスタム ロールは、組織全体のスコープで割り当てることも、単一の Microsoft Entra オブジェクトの場合はそのスコープで割り当てることもできます。 オブジェクト スコープの例としては、単一のアプリ登録があります。 異なるスコープを使用すると、組織内のすべてのアプリ登録に対して同じロール定義を Sally に割り当て、次に Contoso Expense Reports アプリ登録に対してのみ Naveen に割り当てることができます。

アプリケーション管理の委任のためにカスタム ロールを作成して使用する場合のヒント:

- カスタム役割は、Microsoft Entra 管理センターの最新の [アプリ登録] ペインでのみアクセス権を付与します。 レガシ アプリ登録ブレードではアクセス権は付与されません。
- [\[Microsoft Entra 管理ポータルへのアクセスを制限する\]](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions) ユーザー設定が **[はい]** に設定されている場合、カスタム役割は、Microsoft Entra 管理センターへのアクセス権を付与しません。
- ロールの割り当てを使用してユーザーがアクセス権を持っているアプリ登録は、アプリの登録ページの [すべてのアプリケーション] タブにのみ表示されます。 これらは [所有しているアプリケーション] タブには表示されません。

カスタム ロールの基本の詳細については、[カスタム ロールの概要](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview)に関するページと、[カスタム ロールの作成](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)方法と[ロールの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)方法に関するページを参照してください。

### トラブルシューティング

#### 症状 - アプリケーションを登録しようとするとアクセスが拒否される

Microsoft Entra ID にアプリケーションを登録しようとすると、次のようなメッセージが表示されます。

```
Access denied
You do not have access
You don't have permission to register applications in the <directoryName> directory. To request access, contact your administrator.
```

[Image: 新しいアプリ登録を作成しようとしたときのアクセス拒否メッセージのスクリーンショット。]

**原因**

アプリケーションをディレクトリに登録できないのは、ディレクトリ管理者がアプリケーションを作成できる人を制限しているためです。

**ソリューション**

管理者に連絡して次のいずれかを行ってもらってください。

- あなたにアプリケーション開発者ロールを割り当てることで、アプリケーションを作成して同意するためのアクセス許可を付与してもらう。
- アプリケーション登録をあなたの代わりに作成してあなたをアプリケーション所有者として割り当ててもらう。
<!-- /MSL-PAGE -->
