# Microsoft Learn — Microsoft Entra / 開発者向け (Identity Platform・MSAL・Microsoft.Identity.Web) (part 4)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 29

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-daemon-app-call-api"} -->
## クイック スタート - サンプル デーモン アプリで Web API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-app-call-api
- Service: identity-platform
- Article date: 2024-11-20
- Summary: Microsoft ID プラットフォームを使用して保護された Web API を呼び出すアクセス トークンを取得する方法を示すデーモン アプリ のコード サンプル クイック スタート

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このクイックスタートでは、サンプル デーモン アプリケーションを使用してアクセス トークンを取得し、 [Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) を使用して保護された Web API を呼び出します。

開始する前に、このページの上部にある **[テナントの種類** の選択] セレクターを使用して、テナントの種類を選択します。 Microsoft Entra ID には、 [従業員](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#workforce-tenants) と [外部](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#external-tenants)の 2 つのテナント構成が用意されています。 従業員テナントの構成は、従業員、内部アプリ、およびその他の組織リソースを対象としています。 外部テナントは、顧客向けのアプリ用です。

::: zone pivot="workforce"

このクイック スタートで使用するサンプル アプリは、Microsoft Graph API を呼び出すアクセス トークンを取得します。

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 次の Microsoft Entra ロールには、必要なアクセス許可が含まれます。
    - アプリケーション管理者
    - アプリケーション開発者
    - クラウド アプリケーション管理者
- 従業員テナント。 既定のディレクトリを使用するか、 [新しいテナントを設定](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)できます。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- アプリの登録にクライアント シークレットを追加します。 運用アプリではクライアント シークレットを使用**しないでください**。 代わりに、証明書またはフェデレーション資格情報を使用してください。 詳細については、「 [アプリケーションに資格情報を追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=client-secret)。

## [.NET](#tab/asp-dot-net-core-workforce)
- [.NET 6.0 SDK](https://dotnet.microsoft.com/download/dotnet) の最小要件。
- [Visual Studio 2022](https://visualstudio.microsoft.com/vs/) または [Visual Studio Code](https://code.visualstudio.com/)。

## [ノード](#tab/node-workforce)
- [Node.js](https://nodejs.org/en/download/package-manager).
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコード エディター。

## [Python](#tab/python-workforce)
- [Python 3 以降](https://www.python.org/downloads/release/python-364/)。
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコード エディター。

## [ジャワ](#tab/java-workforce)
- [Java Development Kit (JDK)](https://openjdk.java.net/) 8 以降。
- [Maven](https://maven.apache.org/)。
- 適切なコード エディター。

---

### デーモン アプリに API アクセス許可を付与する

デーモン アプリが Microsoft Graph API のデータにアクセスするには、必要なアクセス許可を付与します。 デーモン アプリには、アプリケーションの種類のアクセス許可が必要です。 ユーザーはデーモン アプリケーションと対話できないため、テナント管理者はこれらのアクセス許可に同意する必要があります。 アクセス許可を付与して同意するには、次の手順を使用します。

## [.NET](#tab/asp-dot-net-core-workforce)
.NET デーモン アプリの場合、アクセス許可を付与して同意する必要はありません。 このデーモン アプリは、独自のアプリ登録情報を読み取るので、アプリケーションのアクセス許可を与えられていない状態で読み取ることができます。

## [ノード](#tab/node-workforce)
1. [ **アプリの登録** ] ページで、作成したアプリケーション ( *ciam-client-app* など) を選択します。
2. **[管理]** の下にある **[API のアクセス許可]** を選択します。
3. [ **構成されたアクセス許可**] で、[ **アクセス許可の追加]** を選択します。
4. [ **Microsoft API** ] タブで、[ **Microsoft Graph &gt;&gt; アプリケーションのアクセス許可**] を選択します。 アプリがそれ自体としてサインインする場合は [ **アプリケーションのアクセス許可** ] オプションを選択しますが、ユーザーの代わりにはサインインしません。
5. [ **アクセス許可の選択** ] ボックスの一覧で、 **User.Read.All** を検索して選択します。 アプリが必要とするすべてのユーザーの完全なプロファイルを読み取ることができるように、このアクセス許可を付与します。
6. [ **アクセス許可の追加]** ボタンを選択します。
7. この時点で、アクセス許可が正しく割り当てられます。 ただし、デーモン アプリではユーザーが操作できないため、ユーザー自身がこれらのアクセス許可に同意することはできません。 テナント管理者は、テナント内のすべてのユーザーに代わって、次のアクセス許可に同意する必要があります。

    1. [&lt; を選択し&gt;、[**はい**] を選択します。
    2. [**最新の情報に更新**] を選択し、両方**のアクセス許可の [状態&lt;テナント名&gt;**が表示されることを確認します。

## [Python](#tab/python-workforce)
1. [ **アプリの登録** ] ページで、作成したアプリケーション ( *ciam-client-app* など) を選択します。
2. **[管理]** の下にある **[API のアクセス許可]** を選択します。
3. [ **構成されたアクセス許可**] で、[ **アクセス許可の追加]** を選択します。
4. [ **Microsoft API** ] タブで、[ **Microsoft Graph &gt;&gt; アプリケーションのアクセス許可**] を選択します。 アプリがそれ自体としてサインインする場合は [ **アプリケーションのアクセス許可** ] オプションを選択しますが、ユーザーの代わりにはサインインしません。
5. [ **アクセス許可の選択** ] ボックスの一覧で、 **User.Read.All** を検索して選択します。 アプリが必要とするすべてのユーザーの完全なプロファイルを読み取ることができるように、このアクセス許可を付与します。
6. [ **アクセス許可の追加]** ボタンを選択します。
7. この時点で、アクセス許可が正しく割り当てられます。 ただし、デーモン アプリではユーザーが操作できないため、ユーザー自身がこれらのアクセス許可に同意することはできません。 テナント管理者は、テナント内のすべてのユーザーに代わって、次のアクセス許可に同意する必要があります。

    1. [&lt; を選択し&gt;、[**はい**] を選択します。
    2. [**最新の情報に更新**] を選択し、両方**のアクセス許可の [状態&lt;テナント名&gt;**が表示されることを確認します。

## [ジャワ](#tab/java-workforce)
1. [ **アプリの登録** ] ページで、作成したアプリケーション ( *ciam-client-app* など) を選択します。
2. **[管理]** の下にある **[API のアクセス許可]** を選択します。
3. [ **構成されたアクセス許可**] で、[ **アクセス許可の追加]** を選択します。
4. [ **Microsoft API** ] タブで、[ **Microsoft Graph &gt;&gt; アプリケーションのアクセス許可**] を選択します。 アプリがそれ自体としてサインインする場合は [ **アプリケーションのアクセス許可** ] オプションを選択しますが、ユーザーの代わりにはサインインしません。
5. [ **アクセス許可の選択** ] ボックスの一覧で、 **User.Read.All** を検索して選択します。 アプリが必要とするすべてのユーザーの完全なプロファイルを読み取ることができるように、このアクセス許可を付与します。
6. [ **アクセス許可の追加]** ボタンを選択します。
7. この時点で、アクセス許可が正しく割り当てられます。 ただし、デーモン アプリではユーザーが操作できないため、ユーザー自身がこれらのアクセス許可に同意することはできません。 テナント管理者は、テナント内のすべてのユーザーに代わって、次のアクセス許可に同意する必要があります。

    1. [&lt; を選択し&gt;、[**はい**] を選択します。
    2. [**最新の情報に更新**] を選択し、両方**のアクセス許可の [状態&lt;テナント名&gt;**が表示されることを確認します。

---

### サンプル アプリケーションを複製またはダウンロードする

サンプル アプリケーションを取得するには、GitHub から複製するか、 *.zip* ファイルとしてダウンロードします。

- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

## [.NET](#tab/asp-dot-net-core-workforce)
```console
git clone https://github.com/Azure-Samples/ms-identity-docs-code-dotnet.git
```

- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-docs-code-dotnet/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

## [ノード](#tab/node-workforce)
```console
git clone https://github.com/azure-samples/ms-identity-javascript-nodejs-console.git 
```

- [.zip ファイルをダウンロードします](https://github.com/azure-samples/ms-identity-javascript-nodejs-console/archive/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

## [Python](#tab/python-workforce)
```console
git clone https://github.com/Azure-Samples/ms-identity-python-daemon.git 
```

- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-python-daemon/archive/master.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

## [ジャワ](#tab/java-workforce)
```console
git clone https://github.com/Azure-Samples/ms-identity-java-daemon.git
```

- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-java-daemon/archive/master.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

---

### プロジェクトを構成する

クライアント デーモン アプリ サンプルでアプリ登録の詳細を使用するには、次の手順に従います。

## [.NET](#tab/asp-dot-net-core-workforce)
1. コンソール ウィンドウを開き、 *ms-identity-docs-code-dotnet/console-daemon* ディレクトリに移動します。

    ```console
    cd ms-identity-docs-code-dotnet/console-daemon
    ```
2. *Program.cs*を開き、ファイルの内容を次のスニペットに置き換えます。

    ```csharp
     // Full directory URL, in the form of https://login.microsoftonline.com/<tenant_id>
     Authority = " https://login.microsoftonline.com/Enter_the_tenant_ID_obtained_from_the_Microsoft_Entra_admin_center",
     // 'Enter the client ID obtained from the Microsoft Entra admin center
     ClientId = "Enter the client ID obtained from the Microsoft Entra admin center",
     // Client secret 'Value' (not its ID) from 'Client secrets' in the Microsoft Entra admin center
     ClientSecret = "Enter the client secret value obtained from the Microsoft Entra admin center",
     // Client 'Object ID' of app registration in Microsoft Entra admin center - this value is a GUID
     ClientObjectId = "Enter the client Object ID obtained from the Microsoft Entra admin center"
    ```

    - `Authority` - 機関は、MSAL がトークンを要求できるディレクトリを示す URL です。 *Enter\_the\_tenant\_ID\_obtained\_from\_the\_Microsoft\_Entra\_admin\_center*を、先ほど記録した**ディレクトリ (テナント) ID** の値に置き換えます。
    - `ClientId` - アプリケーションの識別子 。クライアントとも呼ばれます。 引用符で囲まれたテキストを、登録済みアプリケーションの概要ページから前に記録した `Application (client) ID` 値に置き換えます。
    - `ClientSecret` - Microsoft Entra 管理センターでアプリケーション用に作成されたクライアント シークレット。 クライアント シークレットの **値** を入力します。
    - `ClientObjectId` - クライアント アプリケーションのオブジェクト ID。 引用符で囲まれたテキストを、登録済みアプリケーションの概要ページから前に記録した `Object ID` 値に置き換えます。

## [ノード](#tab/node-workforce)
エディターで *.env* ファイルを開き、プレースホルダーを置き換えます。

- `Enter_the_Application_Id_Here` は、先ほど登録したアプリケーションのアプリケーション (クライアント) ID で指定します。
- `Enter_the_Tenant_Id_Here` ワークフォース テナントのテナント ID を使用します。
- `Enter_the_Client_Secret_Here` 前に作成したクライアント シークレットを使用します。
- `Enter_the_Cloud_Instance_Id_Here` と `https://login.microsoftonline.com`。
- `Enter_the_Graph_Endpoint_Here` と `https://graph.microsoft.com/`。

## [Python](#tab/python-workforce)
1. *1-Call-MsGraph-WithSecret* ディレクトリに移動します。
2. エディターで、 **parameters.json** ファイルを開き、プレースホルダーを置き換えます。

    - `Enter_the_Application_Id_Here` は、先ほど登録したアプリケーションのアプリケーション (クライアント) ID で指定します。
    - `Enter_the_Tenant_Id_Here` ワークフォース テナントのテナント ID を使用します。
    - `Enter_the_Client_Secret_Here` 前に作成したクライアント シークレットを使用します。

## [ジャワ](#tab/java-workforce)
1. `msal-client-credential-secret` ディレクトリに移動します。
2. エディターで、 `src\main\resources\application.properties` ファイルを開き、プレースホルダーを置き換えます。

    - `Enter_the_Application_Id_Here` は、先ほど登録したアプリケーションのアプリケーション (クライアント) ID で指定します。
    - `Enter_the_Tenant_Id_Here` ワークフォース テナントのテナント ID を使用します。
    - `Enter_the_Client_Secret_Here` 前に作成したクライアント シークレットを使用します。

---

### アプリケーションを実行してテストする

サンプル アプリを構成しました。 実行とテストに進むことができます。

## [.NET](#tab/asp-dot-net-core-workforce)
コンソール ウィンドウから次のコマンドを実行して、アプリケーションをビルドして実行します。

```console
dotnet run
```

アプリケーションが正常に実行されると、次のスニペットのような応答が表示されます (簡潔にするために短縮されます)。

```console
{
"@odata.context": "https://graph.microsoft.com/v1.0/$metadata#applications/$entity",
"id": "00001111-aaaa-2222-bbbb-3333cccc4444",
"deletedDateTime": null,
"appId": "00001111-aaaa-2222-bbbb-3333cccc4444",
"applicationTemplateId": null,
"disabledByMicrosoftStatus": null,
"createdDateTime": "2021-01-17T15:30:55Z",
"displayName": "identity-dotnet-console-app",
"description": null,
"groupMembershipClaims": null,
...
}
```

#### 動作方法

デーモン アプリケーションは、(ユーザーの代わりにではなく) それ自体に代わってトークンを取得します。 ユーザーは独自の ID を必要とするため、デーモン アプリケーションと対話できません。 この種類のアプリケーションは、アプリケーション ID、資格情報 (シークレットまたは証明書)、およびアプリケーション ID URI を提示することで、アプリケーション ID を使用してアクセス トークンを要求します。 デーモン アプリケーションは、標準の [OAuth 2.0 クライアント資格情報付与フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow) を使用してアクセス トークンを取得します。

アプリは、Microsoft ID プラットフォームからアクセス トークンを取得します。 アクセス トークンのスコープは、Microsoft Graph API です。 その後、アプリはアクセス トークンを使用して、Microsoft Graph API から独自のアプリケーション登録の詳細を要求します。 アクセス トークンに適切なアクセス許可がある限り、アプリは Microsoft Graph API から任意のリソースを要求できます。

このサンプルでは、ユーザーの ID ではなく、無人ジョブまたは Windows サービスをアプリケーション ID で実行する方法を示します。

## [ノード](#tab/node-workforce)
1. 依存関係をインストールするには、次のコマンドを実行します。

    ```console
    npm install
    ```
2. アプリケーションを実行するには、次のコマンドを使用します。

    ```console
    node . --op getUsers
    ```

アプリが正常に実行されると、従業員テナントのすべてのユーザーの一覧を表す JSON 形式の出力が表示されます。 次のスニペットのようになります。

```json
{
  '@odata.context': 'https://graph.microsoft.com/v1.0/$metadata#users',
  value: [
    {
      businessPhones: [],
      displayName: 'Casey Jensen',
      givenName: 'Jense',
      jobTitle: null,
      mail: null,
      mobilePhone: null,
      officeLocation: null,
      preferredLanguage: null,
      surname: 'Casey',
      userPrincipalName: 'jensen@contoso.onmicrosoft.com',
      id: 'aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb'
    },
    ...
  ]
}

```

#### 動作方法

デーモン アプリケーションは、(ユーザーの代わりにではなく) それ自体に代わってトークンを取得します。 ユーザーは独自の ID を必要とするため、デーモン アプリケーションと対話できません。 この種類のアプリケーションは、アプリケーション ID、資格情報 (シークレットまたは証明書)、およびアプリケーション ID URI を提示することで、アプリケーション ID を使用してアクセス トークンを要求します。 デーモン アプリケーションは、標準の [OAuth 2.0 クライアント資格情報付与フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow) を使用してアクセス トークンを取得します。

アプリは、Microsoft ID プラットフォームからアクセス トークンを取得します。 アクセス トークンのスコープは、Microsoft Graph API です。 その後、アプリはアクセス トークンを使用して、Microsoft Graph API からテナント内のすべてのユーザーを読み取る。 アクセス トークンに適切なアクセス許可がある限り、アプリは Microsoft Graph API から任意のリソースを要求できます。 この場合、すべてのユーザーの完全なプロファイルを **読み取ることができるように、アプリ User.Read.All** アプリのアクセス許可を付与しました。

このサンプルでは、ユーザーの ID ではなく、無人ジョブまたは Windows サービスをアプリケーション ID で実行する方法を示します。

## [Python](#tab/python-workforce)
1. 依存関係をインストールするには、次のコマンドを実行します。

    ```console
    pip install -r requirements.txt
    ```
2. アプリケーションを実行するには、次のコマンドを使用します。

    ```console
    python confidential_client_secret_sample.py parameters.json
    ```

アプリが正常に実行されると、従業員テナントのすべてのユーザーの一覧を表す JSON 形式の出力が表示されます。 次のスニペットのようになります。

```json
{
  '@odata.context': 'https://graph.microsoft.com/v1.0/$metadata#users',
  value: [
    {
      businessPhones: [],
      displayName: 'Casey Jensen',
      givenName: 'Jense',
      jobTitle: null,
      mail: null,
      mobilePhone: null,
      officeLocation: null,
      preferredLanguage: null,
      surname: 'Casey',
      userPrincipalName: 'jensen@contoso.onmicrosoft.com',
      id: 'aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb'
    },
    ...
  ]
}

```

#### 動作方法

デーモン アプリケーションは、(ユーザーの代わりにではなく) それ自体に代わってトークンを取得します。 ユーザーは独自の ID を必要とするため、デーモン アプリケーションと対話できません。 この種類のアプリケーションは、アプリケーション ID、資格情報 (シークレットまたは証明書)、およびアプリケーション ID URI を提示することで、アプリケーション ID を使用してアクセス トークンを要求します。 デーモン アプリケーションは、標準の [OAuth 2.0 クライアント資格情報付与フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow) を使用してアクセス トークンを取得します。

アプリは、Microsoft ID プラットフォームからアクセス トークンを取得します。 アクセス トークンのスコープは、Microsoft Graph API です。 その後、アプリはアクセス トークンを使用して、Microsoft Graph API からテナント内のすべてのユーザーを読み取る。 アクセス トークンに適切なアクセス許可がある限り、アプリは Microsoft Graph API から任意のリソースを要求できます。 この場合、すべてのユーザーの完全なプロファイルを **読み取ることができるように、アプリ User.Read.All** アプリのアクセス許可を付与しました。

このサンプルでは、ユーザーの ID ではなく、無人ジョブまたは Windows サービスをアプリケーション ID で実行する方法を示します。

## [ジャワ](#tab/java-workforce)
IDE または から *ClientCredentialGrant.java* のメイン メソッドを実行して、サンプル アプリをテストできます。

1. 本体から、次のコマンドを実行します。

    ```
    $ mvn clean compile assembly:single
    ```

    このコマンドは、*/targets* ディレクトリに*msal-client-credential-secret-1.0.0.jar* ファイルを生成します。
2. */targets* ディレクトリに移動し、次のコマンドを使用して Java 実行可能ファイルを実行します。

    ```
    $ java -jar msal-client-credential-secret-1.0.0.jar
    ```

アプリが正常に実行されると、従業員テナントのすべてのユーザーの一覧を表す JSON 形式の出力が表示されます。 次のスニペットのようになります。

```json
{
  '@odata.context': 'https://graph.microsoft.com/v1.0/$metadata#users',
  value: [
    {
      businessPhones: [],
      displayName: 'Casey Jensen',
      givenName: 'Jense',
      jobTitle: null,
      mail: null,
      mobilePhone: null,
      officeLocation: null,
      preferredLanguage: null,
      surname: 'Casey',
      userPrincipalName: 'jensen@contoso.onmicrosoft.com',
      id: 'aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb'
    },
    ...
  ]
}

```

#### 動作方法

デーモン アプリケーションは、(ユーザーの代わりにではなく) それ自体に代わってトークンを取得します。 ユーザーは独自の ID を必要とするため、デーモン アプリケーションと対話できません。 この種類のアプリケーションは、アプリケーション ID、資格情報 (シークレットまたは証明書)、およびアプリケーション ID URI を提示することで、アプリケーション ID を使用してアクセス トークンを要求します。 デーモン アプリケーションは、標準の [OAuth 2.0 クライアント資格情報付与フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow) を使用してアクセス トークンを取得します。

アプリは、Microsoft ID プラットフォームからアクセス トークンを取得します。 アクセス トークンのスコープは、Microsoft Graph API です。 その後、アプリはアクセス トークンを使用して、Microsoft Graph API からテナント内のすべてのユーザーを読み取る。 アクセス トークンに適切なアクセス許可がある限り、アプリは Microsoft Graph API から任意のリソースを要求できます。 この場合、すべてのユーザーの完全なプロファイルを **読み取ることができるように、アプリ User.Read.All** アプリのアクセス許可を付与しました。

このサンプルでは、ユーザーの ID ではなく、無人ジョブまたは Windows サービスをアプリケーション ID で実行する方法を示します。

---

::: zone-end

::: zone pivot="external"

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 次の Microsoft Entra ロールには、必要なアクセス許可が含まれます。
    - アプリケーション管理者
    - アプリケーション開発者
    - クラウド アプリケーション管理者
- 外部テナント。 作成するには、次の方法から選択します。
    - (推奨) [Microsoft Entra 外部 ID 拡張機能](https://aka.ms/ciamvscode/samples/marketplace) を使用して、Visual Studio Code で外部テナントを直接設定します。
    - Microsoft Entra 管理センターで[新しい外部テナントを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)します。
- 次の構成で [、Microsoft Entra 管理センター](https://entra.microsoft.com) に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)参照してください。
    - **名前**: *ciam-daemon-app*
    - **サポートされているアカウントの種類**: *この組織のディレクトリ内のアカウントのみ (シングル テナント)*
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコード エディター。
- [.NET 7.0](https://dotnet.microsoft.com/learn/dotnet/hello-world-tutorial/install) 以降。
- [Node.js](https://nodejs.org) (ノード実装のみ)

### クライアント シークレットを作成する

登録済みアプリケーションのクライアント シークレットを作成します。 アプリケーションは、トークンの要求時にクライアント シークレットを使用して ID を証明します。

1. [ **アプリの登録** ] ページで、作成したアプリケーション ( *Web アプリ クライアント シークレット*など) を選択して **[概要** ] ページを開きます。
2. [ **管理**] で、 **証明書とシークレット**&gt;**クライアント シークレット**&gt;**新しいクライアント シークレット**の順に選択します。
3. [ **説明** ] ボックスに、クライアント シークレットの説明 ( *Web アプリ クライアント シークレット*など) を入力します。
4. [ **有効期限]** で、(組織のセキュリティ規則に従って) シークレットが有効な期間を選択し、[ **追加**] を選択します。
5. シークレットの値を記録 **します**。 この値は、後の手順で構成に使用します。 **証明書とシークレット**から移動した後、シークレット値は再び表示されず、取得できません。 必ず記録してください。

機密クライアント アプリケーションの資格情報を作成する場合:

- アプリケーションを運用環境に移行する前に、クライアント シークレットの代わりに証明書を使用することをお勧めします。 証明書の使用方法の詳細については、 [Microsoft ID プラットフォーム アプリケーション認証証明書の資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials)の手順を参照してください。
- テスト目的で、自己署名証明書を作成し、それに対して認証するようにアプリを構成できます。 ただし、 **運用環境では**、既知の証明機関によって署名された証明書を購入し、 [Azure Key Vault](https://learn.microsoft.com/ja-jp/azure/key-vault/general/overview) を使用して証明書のアクセスと有効期間を管理する必要があります。

### デーモン アプリに API アクセス許可を付与する

1. [ **アプリの登録** ] ページで、作成したアプリケーション ( *ciam-client-app* など) を選択します。
2. **[管理]** の下にある **[API のアクセス許可]** を選択します。
3. [ **構成されたアクセス許可**] で、[ **アクセス許可の追加]** を選択します。
4. 組織で **使用している API** タブを選択します。
5. API の一覧で、 *ciam-ToDoList-api などの API* を選択します。
6. [ **アプリケーションのアクセス許可]** オプションを選択します。 このオプションは、アプリ自体としてサインインする場合に選択しますが、ユーザーの代わりには選択しません。
7. アクセス許可の一覧から、 **TodoList.Read.All、ToDoList.ReadWrite.All** を選択します (必要に応じて検索ボックスを使用します)。
8. [ **アクセス許可の追加]** ボタンを選択します。
9. この時点で、アクセス許可が正しく割り当てられます。 ただし、デーモン アプリではユーザーが操作できないため、ユーザー自身がこれらのアクセス許可に同意することはできません。 この問題に対処するには、管理者がテナント内のすべてのユーザーに代わってこれらのアクセス許可に同意する必要があります。

    1. [&lt; を選択し&gt;、[**はい**] を選択します。
    2. [**最新の情報に更新**] を選択し、両方**のアクセス許可の [状態&lt;テナント名&gt;**が表示されることを確認します。

### アプリ ロールを構成する

API は、クライアント アプリがアクセス トークンをそれ自体として取得するために、アプリケーションに対して少なくとも 1 つのアプリ ロール ( [アプリケーションアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)とも呼ばれます) を発行する必要があります。 アプリケーションのアクセス許可は、クライアント アプリケーションが自身として正常に認証できるようにし、ユーザーをサインインさせる必要がない場合に API が発行する必要があるアクセス許可の種類です。 アプリケーションのアクセス許可を発行するには、次の手順に従います。

1. [ **アプリの登録** ] ページで、作成したアプリケーション ( *ciam-ToDoList-api* など) を選択して **[概要** ] ページを開きます。
2. [ **管理**] で、[ **アプリ ロール**] を選択します。
3. [ **アプリ ロールの作成**] を選択し、次の値を入力し、[ **適用** ] を選択して変更を保存します。

    | プロパティ | 価値 |
    | --- | --- |
    | [表示名] | *ToDoList.Read.All* |
    | 許可されるメンバー型 | **アプリケーション** |
    | 価値 | *ToDoList.Read.All* |
    | Description | *アプリが 'TodoListApi' を使用してすべてのユーザーの ToDo リストを読み取れるようにする* |
    | このアプリ ロールを有効にしますか? | チェックしたままにする |
4. [ **アプリ ロールの作成** ] をもう一度選択し、2 つ目のアプリ ロールに次の値を入力し、[ **適用** ] を選択して変更を保存します。

    | プロパティ | 価値 |
    | --- | --- |
    | [表示名] | *ToDoList.ReadWrite.All* |
    | 許可されるメンバー型 | **アプリケーション** |
    | 価値 | *ToDoList.ReadWrite.All* |
    | Description | *アプリで 'ToDoListApi' を使用して、すべてのユーザーの ToDo リストの読み取りと書き込みを許可する* |
    | このアプリ ロールを有効にしますか? | チェックしたままにする |

### オプションクレームの設定

**idtyp** の省略可能な要求を追加して、Web API がトークンがアプリ トークンか**アプリ** **+ ユーザー** トークンかを判断するのに役立ちます。 **scp** と**ロール**要求の組み合わせを同じ目的で使用できますが、**idtyp** 要求を使用すると、アプリ トークンとアプリ + ユーザー トークンを区別する最も簡単な方法です。 たとえば、トークンが *アプリ* 専用トークンの場合、この要求の値は app です。

### サンプル デーモン アプリケーションと Web API を複製またはダウンロードする

サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

## [ノード](#tab/node-external)
- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial.git
    ```
- または、 [ファイル .zip サンプルをダウンロード](https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial/archive/refs/heads/main.zip)し、名前の長さが 260 文字未満のファイル パスに抽出します。

#### プロジェクトの依存関係をインストールする

1. コンソール ウィンドウを開き、Node.js サンプル アプリを含むディレクトリに移動します。

    ```console
    cd 2-Authorization\3-call-api-node-daemon\App
    ```
2. 次のコマンドを実行して、アプリの依存関係をインストールします。

    ```console
    npm install && npm update
    ```

## [.NET](#tab/asp-dot-net-core-external)
- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial.git
    ```
- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

---

### サンプル デーモン アプリと API を構成する

クライアント Web アプリ サンプルでアプリ登録の詳細を使用するには、次の手順に従います。

## [ノード](#tab/node-external)
1. コード エディターで、ファイル `App\authConfig.js` 開きます。
2. プレースホルダーを見つけます。

    - `Enter_the_Application_Id_Here` を選択し、先ほど登録したデーモン アプリのアプリケーション (クライアント) ID に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com`されている場合は、 `contoso`を使用します。 テナント名がない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。
    - `Enter_the_Client_Secret_Here` を選択し、前にコピーしたデーモン アプリ シークレット値に置き換えます。
    - `Enter_the_Web_Api_Application_Id_Here` を選択し、先ほどコピーした Web API のアプリケーション (クライアント) ID に置き換えます。

Web API サンプルでアプリの登録を使用するには:

1. コード エディターで、ファイル `API\ToDoListAPI\appsettings.json` 開きます。
2. プレースホルダーを見つけます。

    - `Enter_the_Application_Id_Here` を選択し、コピーした Web API のアプリケーション (クライアント) ID に置き換えます。
    - `Enter_the_Tenant_Id_Here` を選択し、先ほどコピーしたディレクトリ (テナント) ID に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com`されている場合は、 `contoso`を使用します。 テナント名がない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。

## [.NET](#tab/asp-dot-net-core-external)
1. コード エディター *で、ms-identity-ciam-dotnet-tutorial/2-Authorization/3-call-own-api-dotnet-core-daemon/ToDoListClient/appsettings.json* ファイルを開きます。
2. プレースホルダーを見つけます。

    - `Enter_the_Application_Id_Here` を選択し、先ほど登録したデーモン アプリケーションのアプリケーション (クライアント) ID に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com`されている場合は、 `contoso`を使用します。 テナント名がない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。
    - `Enter_the_Client_Secret_Here` を選択し、前にコピーしたデーモン アプリケーション シークレット値に置き換えます。
    - `Enter_the_Web_Api_Application_Id_Here` を選択し、先ほどコピーした Web API のアプリケーション (クライアント) ID に置き換えます。

Web API サンプルでアプリの登録を使用するには:

1. コード エディター *で、ms-identity-ciam-dotnet-tutorial/2-Authorization/3-call-own-api-dotnet-core-daemon/ToDoListAPI/appsettings.json* ファイルを開きます。
2. プレースホルダーを見つけます。

    - `Enter_the_Application_Id_Here` を選択し、コピーした Web API のアプリケーション (クライアント) ID に置き換えます。
    - `Enter_the_Tenant_Id_Here` を選択し、先ほどコピーしたディレクトリ (テナント) ID に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com`されている場合は、 `contoso`を使用します。 テナント名がない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。

---

### サンプル デーモン アプリと API の実行とテスト

サンプル アプリを構成しました。 実行とテストに進むことができます。

## [ノード](#tab/node-external)
1. コンソール ウィンドウを開き、次のコマンドを使用して Web API を実行します。

    ```console
    cd 2-Authorization\3-call-api-node-daemon\API\ToDoListAPI
    dotnet run
    ```
2. 次のコマンドを使用して、Web アプリ クライアントを実行します。

    ```console
    2-Authorization\3-call-api-node-daemon\App
    node . --op getToDos
    ```

デーモン アプリと Web API が正常に実行されると、コンソール ウィンドウに次の JSON 配列のようなものが表示されます

```json
{
    "id": 1,
    "owner": "3e8....-db63-43a2-a767-5d7db...",
    "description": "Pick up grocery"
},
{
    "id": 2,
    "owner": "c3cc....-c4ec-4531-a197-cb919ed.....",
    "description": "Finish invoice report"
},
{
    "id": 3,
    "owner": "a35e....-3b8a-4632-8c4f-ffb840d.....",
    "description": "Water plants"
}
```

#### 動作方法

Node.js アプリは [、OAuth 2.0 クライアント資格情報付与フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow) を使用して、ユーザーではなく、それ自体のアクセス トークンを取得します。 アプリが要求するアクセス トークンには、ロールとして表されるアクセス許可が含まれます。 クライアント資格情報フローでは、アプリケーション トークンのユーザー スコープの代わりに、この一連のアクセス許可が使用されます。 前に Web API で これらのアプリケーションのアクセス許可を公開 し、 デーモン アプリに付与しました。

API 側のサンプル .NET Web API では、API はアクセス トークンに必要なアクセス許可 (アプリケーションのアクセス許可) があることを確認する必要があります。 Web API は、必要なアクセス許可を持たないアクセス トークンを受け入れることはできません。

#### データへのアクセス

Web API エンドポイントは、ユーザーとアプリケーションの両方からの呼び出しを受け入れるように準備する必要があります。 したがって、それに応じて各要求に応答する方法が必要です。 たとえば、委任されたアクセス許可/スコープを介したユーザーからの呼び出しは、ユーザーのデータ to-do リストを受け取ります。 一方、アプリケーションのアクセス許可/ロールを介したアプリケーションからの呼び出しは、to-do リスト全体を受け取る場合があります。 ただし、この記事ではアプリケーション呼び出しのみを行っているので、委任されたアクセス許可/スコープを構成する必要はありませんでした。

## [.NET](#tab/asp-dot-net-core-external)
1. コンソール ウィンドウを開き、次のコマンドを使用して Web API を実行します。

    ```console
    cd 2-Authorization\3-call-own-api-dotnet-core-daemon\ToDoListAPI
    dotnet run
    ```
2. 次のコマンドを使用してデーモン クライアントを実行します。

    ```console
    cd 2-Authorization\3-call-own-api-dotnet-core-daemon\ToDoListClient
    dotnet run
    ```

    デーモン アプリケーションと Web API が正常に実行されると、コンソール ウィンドウに次の JSON 配列のようなものが表示されます。

    ```bash
    Posting a to-do...
    Retrieving to-do's from server...
    To-do data:
    ID: 1
    User ID: 00aa00aa-bb11-cc22-dd33-44ee44ee44ee
    Message: Bake bread
    Posting a second to-do...
    Retrieving to-do's from server...
    To-do data:
    ID: 1
    User ID: 00aa00aa-bb11-cc22-dd33-44ee44ee44ee
    Message: Bake bread
    ID: 2
    User ID: 00aa00aa-bb11-cc22-dd33-44ee44ee44ee
    Message: Butter bread
    Deleting a to-do...
    Retrieving to-do's from server...
    To-do data:
    ID: 2
    User ID: 00aa00aa-bb11-cc22-dd33-44ee44ee44ee
    Message: Butter bread
    Editing a to-do...
    Retrieving to-do's from server...
    To-do data:
    ID: 2
    User ID: 00aa00aa-bb11-cc22-dd33-44ee44ee44ee
    Message: Eat bread
    Deleting remaining to-do...
    Retrieving to-do's from server...
    There are no to-do's in server
    ```

### 動作方法

デーモン アプリケーションは [、OAuth 2.0 クライアント資格情報付与フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow) を使用して、ユーザーではなく、それ自体のアクセス トークンを取得します。 アプリが要求するアクセス トークンには、ロールとして表されるアクセス許可が含まれます。 クライアント資格情報フローでは、アプリケーション トークンのユーザー スコープの代わりに、この一連のアクセス許可が使用されます。 前に Web API で これらのアプリケーションのアクセス許可を公開 し、 デーモン アプリに付与しました。 この記事のデーモン アプリでは [、Microsoft Authentication Library for .NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/) を使用して、トークンを取得するプロセスを簡略化します。

API 側のサンプル .NET Web API では、API はアクセス トークンに必要なアクセス許可 (アプリケーションのアクセス許可) があることを確認する必要があります。 Web API は、必要なアクセス許可を持たないアクセス トークンを拒否します。

---

### 関連コンテンツ

## [ノード](#tab/node-external)
- [アクセス トークンを取得し、独自の Node.js デーモン アプリで Web API を呼び出](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-daemon-node-call-api-prepare-tenant)します。
- [Node.js 機密アプリで認証にシークレットの代わりにクライアント証明書を使用します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-web-app-node-use-certificate)。

## [.NET](#tab/asp-dot-net-core-external)
- [この .NET デーモン アプリを最初からビルドするには、マルチパートチュートリアル シリーズを使用します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-daemon-dotnet-call-api-prepare-tenant)

---

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-desktop-app-sign-in"} -->
## クイック スタート - サンプル デスクトップ アプリでユーザーをサインインする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-desktop-app-sign-in
- Service: identity-platform
- Article date: 2024-11-19
- Summary: Microsoft ID プラットフォームを使用して従業員または顧客にサインインするようにサンプル デスクトップ アプリを構成するためのクイック スタート。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このクイック スタートでは、サンプル アプリケーションを使用して、デスクトップ アプリケーションに認証を追加する方法について説明します。 サンプル アプリケーションを使用すると、ユーザーはサインインしてサインアウトし、Microsoft Authentication Library (MSAL) を使用して認証を処理できます。

開始する前に、このページの上部にある **[テナントの種類** の選択] セレクターを使用して、テナントの種類を選択します。 Microsoft Entra ID には、 [従業員](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#workforce-tenants) と [外部](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#external-tenants)の 2 つのテナント構成が用意されています。 従業員テナントの構成は、従業員、内部アプリ、およびその他の組織リソースを対象としています。 外部テナントは、顧客向けのアプリ用です。

::: zone pivot="workforce"

### 前提条件

- アクティブなサブスクリプションを持つ Azure アカウント。 アカウントをまだお持ちでない場合は、[無料](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)アカウントを作成します。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 以下のいずれの Microsoft Entra ロールにも、必要なアクセス許可が含まれています。
    - アプリケーション管理者
    - アプリケーション開発者
- 従業員テナント。 既定のディレクトリを使用するか、 [新しいテナントを設定](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)できます。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID

## [Node.js Electron](#tab/node-js-workforce)
- **モバイル アプリケーションとデスクトップ アプリケーション**のプラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost`
- [Node.js](https://nodejs.org/en/download/package-manager)
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコード エディター

## [Windows Presentation Foundation (WPF)](#tab/wpf-workforce)
- **モバイル アプリケーションとデスクトップ アプリケーション**のプラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `https://login.microsoftonline.com/common/oauth2/nativeclient`
    - **カスタム リダイレクト URI**: {client\_id} がアプリケーションのアプリケーション (クライアント) ID である `ms-appx-web://microsoft.aad.brokerplugin/{client_id}` 。
- [ユニバーサル Windows プラットフォーム開発](https://visualstudio.microsoft.com/vs/)ワークロードがインストールされている [Visual Studio](https://learn.microsoft.com/ja-jp/windows/apps/windows-app-sdk/set-up-your-development-environment)

---

### サンプル プロジェクトのダウンロード

## [Node.js Electron](#tab/node-js-workforce)
注

このチュートリアルで提供される Electron サンプルは、MSAL ノードで動作するように特別に設計されています。 MSAL-browser は、Electron アプリケーションではサポートされていません。 プロジェクトを正しく設定するには、次の手順を実行してください。

- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-javascript-nodejs-desktop.git
    ```
- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-desktop/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

## [Windows Presentation Foundation (WPF)](#tab/wpf-workforce)
- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/active-directory-dotnet-desktop-msgraph-v2.git
    ```
- [.zip ファイルをダウンロードする](https://github.com/Azure-Samples/active-directory-dotnet-desktop-msgraph-v2/archive/refs/heads/msal3x.zip)

---

### プロジェクトを構成する

## [Node.js Electron](#tab/node-js-workforce)
コード エディター *で、ms-identity-javascript-nodejs-desktop-main/App/authConfig.js* ファイルを開きます。 値を次のように置き換えます。

| 変数 | 説明 | 例 |
| --- | --- | --- |
| `Enter_the_Cloud_Instance_Id_Here` | アプリケーションが登録されている Azure クラウド インスタンス | `https://login.microsoftonline.com/` (末尾のスラッシュを含む) |
| `Enter_the_Tenant_Info_Here` | テナント ID またはプライマリ ドメイン | `contoso.microsoft.com` または `aaaabbbb-0000-cccc-1111-dddd2222eeee` |
| `Enter_the_Application_Id_Here` | 登録したアプリケーションのクライアント ID | `00001111-aaaa-2222-bbbb-3333cccc4444` |
| `Enter_the_Graph_Endpoint_Here` | アプリが呼び出す Microsoft Graph API クラウド インスタンス | `https://graph.microsoft.com/` (末尾のスラッシュを含む) |

ファイルは次のようになります。

```javascript
const AAD_ENDPOINT_HOST = "https://login.microsoftonline.com/"; // include the trailing slash

const msalConfig = {
    auth: {
        clientId: "00001111-aaaa-2222-bbbb-3333cccc4444",
        authority: `${AAD_ENDPOINT_HOST}/aaaabbbb-0000-cccc-1111-dddd2222eeee`,
    },
    system: {
        loggerOptions: {
            loggerCallback(loglevel, message, containsPii) {
                 console.log(message);
             },
             piiLoggingEnabled: false,
             logLevel: LogLevel.Verbose,
        }
    }
}

const GRAPH_ENDPOINT_HOST = "https://graph.microsoft.com/"; // include the trailing slash

const protectedResources = {
     graphMe: {
         endpoint: `${GRAPH_ENDPOINT_HOST}v1.0/me`,
         scopes: ["User.Read"],
     }
};

module.exports = {
     msalConfig: msalConfig,
     protectedResources: protectedResources,
 };

```

## [Windows Presentation Foundation (WPF)](#tab/wpf-workforce)
1. ディスクのルートに近いローカル フォルダー (例: **C:\Azure-Samples**) に zip ファイルを展開します。
2. Visual Studio でプロジェクトを開きます。
3. **active-directory-wpf-msgraph-v2/App.xaml.cs** を編集し、`ClientId`および`Tenant`フィールドの値を次のコードに置き換えます。

    ```csharp
    private static string ClientId = "Enter_the_Application_Id_here";
    private static string Tenant = "Enter_the_Tenant_Info_Here";
    ```

どこ：

- `Enter_the_Application_Id_here` - 登録したアプリケーションの**アプリケーション (クライアント) ID**。

    **アプリケーション (クライアント) ID** の値を見つけるには、Microsoft Entra 管理センターのアプリの **[概要**] ページに移動します。
- `Enter_the_Tenant_Info_Here` - 次のいずれかのオプションに設定します。

    - アプリケーション **でこの組織ディレクトリのアカウント**がサポートされている場合は、この値 **をテナント ID** または **テナント名** (たとえば、contoso.microsoft.com) に置き換えます。
    - アプリケーションが**任意の組織ディレクトリ内のアカウントを**サポートする場合には、この値を`organizations`と置き換えてください。
    - アプリケーションで **組織のディレクトリ内のアカウントと個人用の Microsoft アカウント**がサポートされている場合は、この値を `common`に置き換えます。

        **ディレクトリ (テナント) ID** と**サポートされているアカウントの種類**の値を見つけるには、Microsoft Entra 管理センターのアプリの **[概要**] ページに移動します。

---

### アプリケーションを実行する

## [Node.js Electron](#tab/node-js-workforce)
1. このサンプルの依存関係を 1 回インストールする必要があります。

    ```console
    cd ms-identity-javascript-nodejs-desktop-main
    npm install
    ```
2. 次に、コマンド プロンプトまたはコンソールを使用して、アプリケーションを実行します。

    ```console
    npm start
    ```
3. **サインイン**を選択して、サインイン プロセスを開始します。

    初めてサインインするときには、アプリケーションがサインインしてプロファイルにアクセスすることを許可することに同意するように求められます。 正常にサインインすると、アプリケーションにリダイレクトされます。

## [Windows Presentation Foundation (WPF)](#tab/wpf-workforce)
Visual Studio でサンプル アプリケーションをビルドして実行するには、次の手順に従います。

1. [**デバッグ] メニュー**を選択&gt;**デバッグを開始**するか、F5 キーを押します。 アプリケーションの **MainWindow** が表示されます。
2. [ **Microsoft Graph API の呼び出し** ] ボタンを選択します。
3. Microsoft Entra アカウント (職場または学校アカウント) または Microsoft アカウント (live.com、outlook.com) の資格情報を使用してサインインします。
4. アプリケーションを初めて実行する場合は、アプリケーションがユーザー プロファイルにアクセスしてサインインすることを許可する同意を求められます。 要求されたアクセス許可に同意すると、正常にログインしたことがアプリケーションに表示されます。

Microsoft Graph API の呼び出しから取得した基本的なトークン情報とユーザー データが表示されます。

---

::: zone-end

::: zone pivot="external"

### 前提条件

- アクティブなサブスクリプションを持つ Azure アカウント。 アカウントをまだお持ちでない場合は、[無料](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)アカウントを作成します。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 以下のいずれの Microsoft Entra ロールにも、必要なアクセス許可が含まれています。
    - アプリケーション管理者
    - アプリケーション開発者
    - クラウド アプリケーション管理者
- 外部テナント。 作成するには、次の方法から選択します。
    - (推奨) [Microsoft Entra External ID 拡張機能](https://aka.ms/ciamvscode/samples/marketplace) を使用して、Visual Studio Code で外部テナントを直接設定する
    - Microsoft Entra 管理センターで[新しい外部テナントを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)する
- ユーザーの流れ。 詳細については、 [外部テナント内のアプリのセルフサービス サインアップ ユーザー フローを作成する方法](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)に関するページを参照してください。 このユーザー フローは、複数のアプリケーションに使用できます。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- [アプリケーションをユーザー フローに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)

## [Node.js Electron](#tab/node-js-external)
- **モバイル アプリケーションとデスクトップ アプリケーション**のプラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost`
- [Node.js](https://nodejs.org)
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコード エディター\*

## [.NET (MAUI)](#tab/wpfdotnet-maui-external)
- **モバイル アプリケーションとデスクトップ アプリケーション**のプラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: {client\_id} がアプリケーションのアプリケーション (クライアント) ID である `msal{client_id}://auth` 。
- [.NET 7.0 SDK](https://dotnet.microsoft.com/download/dotnet/7.0) 以降
- MAUI ワークロードがインストールされている [Visual Studio 2022](https://aka.ms/vsdownloads):
    - [Windows 向けのステップ](https://learn.microsoft.com/ja-jp/dotnet/maui/get-started/installation?tabs=vswin)
    - [macOS 向けの手順](https://learn.microsoft.com/ja-jp/dotnet/maui/get-started/installation?tabs=vsmac)
- [アプリケーションをユーザー フローに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)

## [.NET (MAUI) WPF](#tab/wpfdotnet-wpf-external)
- **モバイル アプリケーションとデスクトップ アプリケーション**のプラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **カスタム リダイレクト URI**: `https://login.microsoftonline.com/common/oauth2/nativeclient`。 これは手動で入力する必要があります。
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコード エディター
- [.NET 7.0](https://dotnet.microsoft.com/download/dotnet/7.0) 以降
- [アプリケーションをユーザー フローに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)

---

### サンプル プロジェクトのダウンロード

## [Node.js Electron](#tab/node-js-external)
注

このチュートリアルで提供される Electron サンプルは、MSAL ノードで動作するように特別に設計されています。 MSAL-browser は、Electron アプリケーションではサポートされていません。 プロジェクトを正しく設定するには、次の手順を実行してください。

デスクトップ アプリのサンプル コードを取得するには、[.zip ファイルをダウンロードする](https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial/archive/refs/heads/main.zip)か、次のコマンドを実行して、GitHub からサンプル Web アプリケーションを複製します。

```powershell
git clone https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial.git
```

`.zip` ファイルをダウンロードする場合は、パスの全長が 260 文字以下のフォルダーにサンプル アプリ ファイルを抽出します。

#### プロジェクトの依存関係をインストールする

1. コンソール ウィンドウを開き、Electron サンプル アプリが含まれるディレクトリに移動します。

    ```powershell
    cd 1-Authentication\3-sign-in-electron\App
    ```
2. 次のコマンドを実行してアプリの依存関係をインストールします。

    ```powershell
    npm install && npm update
    ```

## [.NET (MAUI)](#tab/wpfdotnet-maui-external)
.NET MAUI デスクトップ アプリケーションのサンプル コードを取得するには、次のコマンドを実行して、[.zip ファイルをダウンロード](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/archive/refs/heads/main.zip)するか、GitHub からサンプルの .NET MAUI デスクトップ アプリケーションを複製します。

```bash
git clone https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial.git
```

## [.NET (MAUI) WPF](#tab/wpfdotnet-wpf-external)
サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial.git
    ```
- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

---

### サンプル Web アプリを構成する

## [Node.js Electron](#tab/node-js-external)
1. コード エディターで、`App\authConfig.js` ファイルを開きます。
2. プレースホルダーを見つけてください。

    1. `Enter_the_Application_Id_Here` を、前に登録したアプリのアプリケーション (クライアント) ID に置き換えます。
    2. `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。

## [.NET (MAUI)](#tab/wpfdotnet-maui-external)
1. Visual Studio で *ms-identity-ciam-dotnet-tutorial-main/1-Authentication/2-sign-in-maui/appsettings.json* ファイルを開きます。
2. プレースホルダーを見つける。
    1. `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。
    2. `Enter_the_Application_Id_Here` を、前に登録したアプリのアプリケーション (クライアント) ID に置き換えます。

## [.NET (MAUI) WPF](#tab/wpfdotnet-wpf-external)
1. IDE (Visual Studio や Visual Studio Code など) でプロジェクトを開き、コードを構成します。
2. コード エディターで、*ms-identity-ciam-dotnet-tutorial*&gt;&gt; フォルダーの **appsettings.json** ファイルを開きます。
3. `Enter_the_Application_Id_Here` を、前に登録したアプリのアプリケーション (クライアント) ID に置き換えます。
4. `Enter_the_Tenant_Subdomain_Here` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* である場合は、`Enter_the_Tenant_Subdomain_Here` を *contoso* に置き換えます。 プライマリ ドメインがない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください。

---

### サンプル Web アプリを実行してテストする

## [Node.js Electron](#tab/node-js-external)
これで、サンプルの Electron デスクトップ アプリをテストできます。 アプリを実行すると、デスクトップ アプリ ウィンドウが自動的に表示されます。

1. ご利用のターミナルで、次のコマンドを実行します。

    ```powershell
    npm start
    ```

    [Image: Electron デスクトップ アプリへのサインインのスクリーンショット。]
2. 表示されたデスクトップ ウィンドウで、**[サインイン]** または **[サインアップ]** ボタンを選択します。 ブラウザー ウィンドウが開き、サインインが求められます。
3. ブラウザーのサインイン ページで、**[メール アドレス]** を入力して **[次へ]** を選択し、**[パスワード]** を入力してから **[サインイン]** を選択します。 アカウントをお持ちでない場合は、**[アカウントをお持ちではない場合、作成できます]** リンクを選択します。これで、サインアップ フローが開始されます。
4. サインアップ オプションを選択した場合は、メール、ワンタイム パスコード、新しいパスワード、その他のアカウントの詳細を入力すると、サインアップ フロー全体が完了します。 以下のスクリーンショットのようなページが表示されます。 サインイン オプションを選択すると、同様のページが表示されます。 このページには、トークン ID 要求が表示されます。

    [Image: Electron デスクトップ アプリでのトークン要求の表示のスクリーンショット。]

## [.NET (MAUI)](#tab/wpfdotnet-maui-external)
.NET MAUI アプリは、複数のオペレーティング システムとデバイス上で実行できるように設計されています。 どのターゲットでアプリをテストしてデバッグしたいかを選択する必要があります。

Visual Studio ツール バーの **[デバッグ ターゲット]** を、デバッグしてテストしたいデバイスに設定します。 次の手順は、**[デバッグ ターゲット]** を *Windows* に設定する方法を示しています。

1. **[デバッグ ターゲット]** ドロップダウンを選択します。
2. **フレームワーク**を選択します
3. **[net7.0-windows...]** を選択します

*F5* キーを押すか、Visual Studio の上部にある "再生ボタン" を選択してアプリを実行します。

1. これでサンプルの .NET MAUI デスクトップ アプリケーションをテストできるようになりました。 アプリケーションを実行すると、デスクトップ アプリケーション ウィンドウが自動的に表示されます。

    [Image: デスクトップ アプリケーションの [サインイン] ボタンのスクリーンショット]
2. 表示されたデスクトップ ウィンドウで、**[サインイン]** ボタンを選択します。 ブラウザー ウィンドウが開き、サインインが求められます。

    [Image: デスクトップ アプリケーションで資格情報を入力するためのユーザー プロンプトのスクリーンショット。]

    サインイン プロセス中に、さまざまなアクセス許可を付与するように求められます (アプリケーションがデータにアクセスできるようにします)。 サインインと同意が成功すると、アプリケーション画面にメイン ページが表示されます。

    [Image: サインインした後に表示されるデスクトップ アプリケーションのメイン ページのスクリーンショット。]

## [.NET (MAUI) WPF](#tab/wpfdotnet-wpf-external)
1. コンソール ウィンドウを開き、次の WPF デスクトップ サンプル アプリが含まれるディレクトリに変更します。

    ```console
    cd 1-Authentication\5-sign-in-dotnet-wpf
    ```
2. ターミナルで、次のコマンドを実行してアプリを実行します。

    ```console
    dotnet run
    ```
3. サンプルを起動すると、**サインイン** ボタンを含むウィンドウが表示されます。 **[サインイン]** ボタンを選択します。

    [Image: WPF デスクトップ アプリケーションのサインイン画面のスクリーンショット。]
4. サインイン ページで、アカウントのメール アドレスを入力します。 アカウントをお持ちでない場合は、**[アカウントをお持ちではない場合、作成できます]** を選択します。これで、サインアップ フローが開始されます。 このフローに従って、新しいアカウントを作成してサインインします。
5. サインインすると、正常なサインインと、取得したトークンに保存されているユーザー アカウントに関する基本情報を表示する画面が表示されます。 サインイン画面の *[トークン情報* ] セクションに基本情報が表示されます

---

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-desktop-app-uwp-sign-in"} -->
## クイック スタート: ユニバーサル Windows プラットフォーム アプリでユーザーのサインインと Microsoft Graph の呼び出しを行う - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-desktop-app-uwp-sign-in
- Service: identity-platform
- Article date: 2024-05-19
- Summary: このクイックスタートでは、ユニバーサル Windows プラットフォーム (UWP) アプリケーションでアクセス トークンを取得し、Microsoft ID プラットフォームで保護されている API を呼び出す方法について説明します。

このクイックスタートでは、ユニバーサル Windows プラットフォーム (UWP) アプリケーションでユーザーをサインインし、アクセス トークンを取得して Microsoft Graph API を呼び出す方法を示すコード サンプルをダウンロードして実行します。

図については、「このサンプルのしくみ」を参照してください。

### 前提条件

- アクティブなサブスクリプションが含まれる Azure アカウント。 [無料でアカウントを作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- [Visual Studio](https://visualstudio.microsoft.com/vs/)

注

MSAL.NET バージョン 4.61.0 以降では、ユニバーサル Windows プラットフォーム (UWP)、Xamarin Android、Xamarin iOS はサポートされていません。 UWP アプリケーションを WINUI などの最新のフレームワークに移行することをお勧めします。 この非推奨化については、「[Announcing the Upcoming Deprecation of MSAL.NET for Xamarin and UWP](https://devblogs.microsoft.com/identity/uwp-xamarin-msal-net-deprecation/)」で詳しく説明しています。

### クイック スタート アプリを登録してダウンロードする

クイック スタート アプリケーションを開始する方法としては、次の 2 つの選択肢があります。

- [簡易] 選択肢 1: アプリを登録して自動構成を行った後、コード サンプルをダウンロードする
- [手動] 選択肢 2: アプリケーションを登録し、コード サンプルを手動で構成する

#### オプション 1: アプリを登録して自動構成を行った後、コード サンプルをダウンロードする

1. [Microsoft Entra 管理センターの \[アプリの登録\]](https://entra.microsoft.com/#blade/Microsoft_AAD_RegisteredApps/applicationsListBlade/quickStartType/UwpQuickstartPage/sourceType/docs) クイックスタート エクスペリエンスに移動します。
2. アプリケーションの名前を入力し、 **[登録]** を選択します。
3. 指示に従って新しいアプリケーションをダウンロードし、自動構成します。

#### オプション 2:アプリケーションを登録し、アプリケーションとコード サンプルを手動で構成する

##### 手順 1:アプリケーションの登録

アプリケーションを登録し、その登録情報をソリューションに追加するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニューの **[設定]** アイコン  を使い、**[ディレクトリとサブスクリプション]** メニューからアプリケーションを登録するテナントに切り替えます。
3. **Entra ID**&gt;**App registrations** に移動し、[**新規登録**] を選択します。
4. アプリケーションの**名前**を入力します (例: `UWP-App-calling-MsGraph`)。 この名前は、アプリのユーザーに表示される場合があります。また、後で変更することができます。
5. **[サポートされているアカウントの種類]** セクションで、 **[Accounts in any organizational directory and personal Microsoft accounts (for example, Skype, Xbox, Outlook.com)](任意の組織のディレクトリ内のアカウントと個人用の Microsoft アカウント (例: Skype、Xbox、Outlook.com))** を選択します。
6. **[登録]** を選択してアプリケーションを作成し、後の手順で使用する**アプリケーション (クライアント) ID** を記録します。
7. **[管理]** で、 **[認証]** を選択します。
8. **[プラットフォームを追加]**&gt;**[モバイル アプリケーションとデスクトップ アプリケーション]** を選択します。
9. **[リダイレクト URI]** で `https://login.microsoftonline.com/common/oauth2/nativeclient` を選択します。
10. **[構成]** をクリックします。

##### 手順 2:プロジェクトのダウンロード

[UWP サンプル アプリケーションのダウンロード](https://github.com/Azure-Samples/active-directory-dotnet-native-uwp-v2/archive/msal3x.zip)

ヒント

Windows におけるパスの長さの制限に起因したエラーを防ぐため、ドライブのルートに近いディレクトリをアーカイブの展開先またはリポジトリのクローン先とすることをお勧めします。

##### 手順 3: プロジェクトを構成する

1. .zip アーカイブを、ドライブのルートに近いローカル フォルダーに抽出します。 たとえば、**C:\Azure-Samples** にします。
2. Visual Studio でプロジェクトを開きます。 **[ユニバーサル Windows プラットフォーム開発]** ワークロードをインストールします。また、SDK コンポーネントのインストールを求められた場合は、個々のコンポーネントをインストールします。
3. *MainPage.Xaml.cs* で、`ClientId` 変数の値を、先ほど登録したアプリケーションの**アプリケーション (クライアント) ID** に変更します。

    ```csharp
    private const string ClientId = "Enter_the_Application_Id_here";
    ```

    **アプリケーション (クライアント) ID** は、Microsoft Entra 管理センター (**Entra ID**&gt;&gt;) のアプリの *[概要*] ウィンドウにあります。
4. パッケージに使用する新しい自己署名テスト証明書を作成して選択します。

    1. **ソリューション エクスプローラー**で、*Package.appxmanifest* ファイルをダブルクリックします。
    2. **[パッケージ]**&gt;**[証明書の選択]**&gt;**[作成]** を選択します。
    3. パスワードを入力し、 **[OK]** を選択します。 *Native\_UWP\_V2\_TemporaryKey.pfx* という名前の証明書が作成されます。
    4. **[OK]** を選択して **[証明書の選択]** ダイアログを閉じ、ソリューション エクスプローラーに *Native\_UWP\_V2\_TemporaryKey.pfx* が表示されることを確認します。
    5. **[ソリューション エクスプローラー]** で、 **[Native\_UWP\_V2]** プロジェクトを右クリックし、 **[プロパティ]** を選択します。
    6. **[署名]** を選択し、 **[厳密な名前のキー ファイルを選択してください]** ボックスの一覧から、作成した .pfx を選択します。

##### 手順 4:アプリケーションの実行

ローカル コンピューターでサンプル アプリケーションを実行するには、次の手順に従います。

1. Visual Studio ツールバーで、適切なプラットフォーム (おそらく ARM ではなく、**x64** または **x86**) を選択します。 ターゲット デバイスが *[デバイス]* から *[ローカル コンピューター]* に変わります。
2. **[デバッグ]**&gt;**[デバッグなしで開始]** を選択します。

    **開発者モード**を有効にするよう求められた場合は、まず開発者モードを有効にしたうえで、 **[デバッグなしで開始]** を再度選択し、アプリを起動してください。

アプリのウィンドウが表示されたら、 **[Call Microsoft Graph API](Microsoft Graph API を呼び出す)** ボタンを選択し、資格情報を入力して、アプリケーションから要求されたアクセス許可に同意してください。 成功した場合、Microsoft Graph API を呼び出すことによって取得したデータとトークン情報が表示されます。

### このサンプルのしくみ

[Image: このクイックスタートで生成されたサンプル アプリの動作を示す図。]

#### MSAL.NET

MSAL ([Microsoft.Identity.Client](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client)) は、ユーザーをサインインし、セキュリティ トークンを要求するために使用されるライブラリです。 セキュリティ トークンは、Microsoft ID プラットフォームによって保護されている API にアクセスするために使用されます。 MSAL は、Visual Studio の "*パッケージ マネージャー コンソール*" で次のコマンドを実行してインストールできます。

```powershell
Install-Package Microsoft.Identity.Client
```

#### MSAL の初期化

MSAL への参照を追加するには、次のコードを追加します。

```csharp
using Microsoft.Identity.Client;
```

その後、MSAL は次のコードを使用して初期化されます。

```csharp
public static IPublicClientApplication PublicClientApp;
PublicClientApp = PublicClientApplicationBuilder.Create(ClientId)
                                                .WithRedirectUri("https://login.microsoftonline.com/common/oauth2/nativeclient")
                                                    .Build();
```

`ClientId` の値は、Microsoft Entra 管理センターに登録されているアプリの **アプリケーション (クライアント) ID**です。 この値は、Microsoft Entra 管理センターのアプリの **[概要]** ページで確認できます。

#### トークンの要求

MSAL には、UWP アプリでトークンを取得するための 2 つのメソッド [`AcquireTokenInteractive`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokeninteractiveparameterbuilder) および [`AcquireTokenSilent`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokensilentparameterbuilder) があります。

##### ユーザー トークンを対話形式で取得する

ユーザーは Microsoft ID プラットフォームの操作を強制される場合があります。その場合、各自の資格情報の検証または同意を行うポップアップ ウィンドウが表示されます。 次に例をいくつか示します。

- ユーザーが初めてアプリケーションにサインインした場合
- パスワードの有効期限が切れているため、ユーザーが資格情報を再入力する必要がある場合
- ご使用のアプリケーションが、ユーザーによる同意が必要なリソースへのアクセスを要求している場合
- 2 要素認証が必須である場合

```csharp
authResult = await PublicClientApp.AcquireTokenInteractive(scopes)
                      .ExecuteAsync();
```

`scopes` パラメーターには、要求するスコープが格納されます (Microsoft Graph の `{ "user.read" }`、カスタム Web API の `{ "api://<Application ID>/access_as_user" }` など)。

##### ユーザートークンを静かに取得する

最初の `AcquireTokenSilent` メソッドを呼び出した後、`AcquireTokenInteractive` メソッドを使用して、保護されたリソースにアクセスするためのトークンを取得します。 リソースへのアクセスを必要とするたびに自分の資格情報を確認するようユーザーに要求したくありません。 ほとんどの場合は、ユーザーの操作なしにトークンの取得や更新を求めます。

```csharp
var accounts = await PublicClientApp.GetAccountsAsync();
var firstAccount = accounts.FirstOrDefault();
authResult = await PublicClientApp.AcquireTokenSilent(scopes, firstAccount)
                                      .ExecuteAsync();
```

- `scopes` には、要求するスコープが格納されます (Microsoft Graph の `{ "user.read" }`、カスタム Web API の `{ "api://<Application ID>/access_as_user" }` など)。
- `firstAccount` は、キャッシュ内の最初のユーザー アカウントを指定します (MSAL は、1 つのアプリで複数のユーザーをサポート)。

### ヘルプとサポート

サポートが必要な場合、問題をレポートする場合、またはサポート オプションについて知りたい場合は、[開発者向けのヘルプとサポート](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-support-help-options)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-mobile-app-call-api"} -->
## クイック スタート - サンプル アプリでユーザーをサインインさせ、Web API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-call-api
- Service: identity-platform
- Article date: 2024-10-30
- Summary: ユーザーをサインインさせ、Microsoft ID プラットフォームを使用して Web API を呼び出すようにサンプル モバイル アプリを構成するためのクイック スタート。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

開始する前に、このページの上部にある **[テナントの種類** の選択] セレクターを使用して、テナントの種類を選択します。 Microsoft Entra ID には、 [従業員](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#workforce-tenants) と [外部](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#external-tenants)の 2 つのテナント構成が用意されています。 従業員テナントの構成は、従業員、内部アプリ、およびその他の組織リソースを対象としています。 外部テナントは、顧客向けのアプリ用です。

このガイドでは、ユーザーをサインインさせ、ASP.NET Core Web API を呼び出すようにサンプル モバイル アプリケーションを構成する方法について説明します。

## [Android](#tab/android-external)
この記事では、次のタスクを実行します。

- Web アプリケーションにプラットフォーム リダイレクト URL を追加します。
- パブリック クライアント フローを有効にします。
- 顧客テナントの詳細に独自の Microsoft Entra 外部 ID を使用するように Android 構成コードサンプル ファイルを更新します。
- サンプルの Android モバイル アプリケーションを実行してテストします。
- 保護された Web API を呼び出します。

## [iOS/macOS](#tab/ios-macos-external)
この記事では、次のタスクを実行します。

- Web アプリケーションにプラットフォーム リダイレクト URL を追加します。
- アプリケーションへのパブリック クライアント フローを有効にします。
- 顧客テナントの詳細に独自の Microsoft Entra 外部 ID を使用するように、iOS 構成コードサンプル ファイルを更新します。
- サンプルの iOS モバイル アプリケーションを実行してテストします。

---

### [前提条件]

## [Android](#tab/android-external)
- [Android Studio](https://developer.android.com/studio)。
- 外部テナント。 まだお持ちでない場合は、 [無料試用版にサインアップ](https://aka.ms/ciam-free-trial?wt.mc_id=ciamcustomertenantfreetrial_linkclick_content_cnl)してください。
- Microsoft [Entra 管理センター](https://entra.microsoft.com)に新しいクライアント Web アプリを登録します。 *これは、組織のディレクトリと個人用の Microsoft アカウントのアカウント*用に構成されています。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要** ] ページから次の値を記録します。

    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- 少なくとも 1 つのスコープ (委任されたアクセス許可) と、 *ToDoList.Read* などの 1 つのアプリ ロール (アプリケーションアクセス許可) を公開する Web API 登録。 まだ行っていない場合は、 [サンプルの Android モバイル アプリで API を呼び出して](https://learn.microsoft.com/ja-jp/entra/external-id/customers/sample-native-authentication-android-sample-app-call-web-api) 、コア Web API ASP.NET 機能を保護する手順に従ってください。 次の手順を完了していることを確認します。

    - API スコープを構成する
    - アプリ ロールを構成する
    - オプションクレームの設定
    - サンプル Web API を複製またはダウンロードする
    - サンプル Web API の構成と実行

## [iOS/macOS](#tab/ios-macos-external)
- [Xcode](https://developer.apple.com/xcode/resources/)。
- 外部テナント。 まだお持ちでない場合は、 [無料試用版にサインアップ](https://aka.ms/ciam-free-trial?wt.mc_id=ciamcustomertenantfreetrial_linkclick_content_cnl)してください。
- Microsoft [Entra 管理センター](https://entra.microsoft.com)に新しいクライアント Web アプリを登録します。 *これは、組織のディレクトリと個人用の Microsoft アカウントのアカウント*用に構成されています。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要** ] ページから次の値を記録します。

    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID\*
- 少なくとも 1 つのスコープ (委任されたアクセス許可) と、 *ToDoList.Read* などの 1 つのアプリ ロール (アプリケーションアクセス許可) を公開する API 登録。 まだ行っていない場合は、 [サンプルの iOS モバイル アプリで API を呼び出して](https://learn.microsoft.com/ja-jp/entra/external-id/customers/sample-native-authentication-ios-sample-app-call-web-api) 、コア Web API ASP.NET 機能を保護する手順に従ってください。 次の手順を完了していることを確認します。

    - API スコープを構成します。
    - アプリ ロールを構成します。
    - 省略可能な要求を構成します。
    - サンプル Web API を複製またはダウンロードします。
    - サンプル Web API を構成して実行します。

---

### プラットフォーム リダイレクト URL を追加する

## [Android](#tab/android-external)
アプリの登録にアプリの種類を指定するには、次の手順に従います。

1. [**管理**] で、[**認証**] を選択します。
2. [ **プラットフォームの構成** ] ページで、[ **プラットフォームの追加**] を選択し、[ **Android** ] オプションを選択します。
3. プロジェクトのパッケージ名を入力します。 [サンプル コード](https://github.com/Azure-Samples/ms-identity-ciam-browser-delegated-android-sample)をダウンロードした場合、この値は`com.azuresamples.msaldelegatedandroidkotlinsampleapp`。
4. [**Android アプリの構成**] ウィンドウの [**署名ハッシュ**] セクションで、[**開発署名ハッシュの生成] を選択します。これは開発環境ごとに変更されます。**ターミナルでオペレーティング システムの KeyTool コマンドをコピーして実行します。
5. KeyTool によって生成された **署名ハッシュ** を入力します。
6. **設定**を選択します。
7. **Android** **の構成ウィンドウから MSAL 構成**をコピーし、後でアプリを構成するために保存します。
8. **完了**を選択します。

## [iOS/macOS](#tab/ios-macos-external)
アプリの登録にアプリの種類を指定するには、次の手順に従います。

1. [**管理**] で、[**認証**] を選択します。
2. [ **プラットフォームの構成** ] ページで、[ **プラットフォームの追加**] を選択し、[ **iOS/ macOS** ] オプションを選択します。
3. プロジェクトのバンドル ID を入力します。 [サンプル コード](https://github.com/Azure-Samples/ms-identity-ciam-browser-delegated-ios-sample.git)をダウンロードした場合、この値は`com.microsoft.identitysample.ciam.MSALiOS`。
4. 後でアプリを**構成**するときに入力できるように、**iOS/macOS 構成**ウィンドウに表示される [構成] を選択して **MSAL** 構成を保存します。
5. **完了**を選択します。

---

### パブリック クライアント フローを有効にする

アプリをパブリック クライアントとして識別するには、次の手順に従います。

1. [**管理**] で、[**認証**] を選択します。
2. [ **詳細設定]** の [ **パブリック クライアント フローを許可**する] で、[ **はい**] を選択します。
3. **[保存]** を選択して変更を保存します。

### サンプル アプリに Web API のアクセス許可を付与する

クライアント アプリと Web API の両方を登録し、スコープを作成して API を公開したら、次の手順に従って API に対するクライアントのアクセス許可を構成できます。

1. [ **アプリの登録** ] ページで、作成したアプリケーション ( *ciam-client-app* など) を選択して **[概要]** ページを開きます。
2. **[管理]** の下にある **[API のアクセス許可]** を選択します。
3. [ **構成されたアクセス許可**] で、[ **アクセス許可の追加]** を選択します。
4. 組織で **使用している API** タブを選択します。
5. API の一覧で、 *ciam-ToDoList-api などの API* を選択します。
6. [ **委任されたアクセス許可** ] オプションを選択します。
7. アクセス許可の一覧から **ToDoList.Read、ToDoList.ReadWrite** を選択します (必要に応じて検索ボックスを使用します)。
8. [ **アクセス許可の追加]** ボタンを選択します。
9. この時点で、アクセス許可が正しく割り当てられました。 ただし、テナントは顧客のテナントであるため、コンシューマー ユーザー自身はこれらのアクセス許可に同意できません。 これに対処するには、管理者がテナント内のすべてのユーザーに代わってこれらのアクセス許可に同意する必要があります。

    1. テナント名の管理者同意を付与する を選択し、その後、[はい] を選択します。
    2. **更新**を選択し、**ステータス**の下に両方のアクセス許可について**&lt;あなたのテナント名&gt;**の「許可済み」と表示されることを確認します。
10. **[構成済みのアクセス許可**] ボックスの一覧で、**ToDoList.Read** および **ToDoList.ReadWrite** のアクセス許可を一度に 1 つずつ選択し、後で使用できるようにアクセス許可の完全な URI をコピーします。 完全なアクセス許可 URI は、 `api://{clientId}/{ToDoList.Read}` または `api://{clientId}/{ToDoList.ReadWrite}`のようになります。

### サンプル モバイル アプリケーションを複製する

## [Android](#tab/android-external)
サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-browser-delegated-android-sample
    ```

## [iOS/macOS](#tab/ios-macos-external)
サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-browser-delegated-ios-sample.git
    ```

---

### サンプル Android モバイル アプリケーションを構成する

## [Android](#tab/android-external)
認証と Web API リソースへのアクセスを有効にするには、次の手順に従ってサンプルを構成します。

1. Android Studio で、複製したプロジェクトを開きます。
2. */app/src/main/res/raw/auth\_config\_ciam.json* ファイルを開きます。
3. プレースホルダーを見つけます。

    - `Enter_the_Application_Id_Here` をクリックし、先ほど登録したアプリの **アプリケーション (クライアント) ID** に置き換えます。
    - `Enter_the_Redirect_Uri_Here` プラットフォーム リダイレクト URL を追加したときに前にダウンロードした Microsoft Authentication Library (MSAL) 構成ファイルの *redirect\_uri* の値に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com`されている場合は、 `contoso`を使用します。 テナント サブドメインがわからない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-customer-tenant-portal#get-the-customer-tenant-details)る方法について説明します。
4. */app/src/main/AndroidManifest.xml* ファイルを開きます。
5. プレースホルダーを見つけます。

    - `ENTER_YOUR_SIGNATURE_HASH_HERE` プラットフォームのリダイレクト URL を追加したときに生成した **署名ハッシュ** に置き換えます。
6. */app/src/main/java/com/azuresamples/msaldelegatedandroidkotlinsampleapp/MainActivity.kt* ファイルを開きます。
7. `WEB_API_BASE_URL`という名前のプロパティを検索し、URL を Web API に設定します。
8. `scopes`という名前のプロパティを検索し、「Web API のアクセス許可を Android サンプル アプリに付与する」に記録されているスコープを設定します。

    ```kotlin
    private const val scopes = "" // Developers should set the respective scopes of their web API here. For example, private const val scopes = "api://{clientId}/{ToDoList.Read} api://{clientId}/{ToDoList.ReadWrite}"
    ```

アプリを構成し、実行する準備ができました。

## [iOS/macOS](#tab/ios-macos-external)
認証と Web API リソースへのアクセスを有効にするには、次の手順に従ってサンプルを構成します。

1. Xcode で、複製したプロジェクトを開きます。
2. */MSALiOS/Configuration.swift ファイルを*開きます。
3. プレースホルダーを見つけます。

    - `Enter_the_Application_Id_Here` をクリックし、先ほど登録したアプリの **アプリケーション (クライアント) ID** に置き換えます。
    - `Enter_the_Redirect_URI_Here` プラットフォーム リダイレクト URL を追加したときに前にダウンロードした Microsoft Authentication Library (MSAL) 構成ファイルの *kRedirectUri* の値に置き換えます。
    - `Enter_the_Protected_API_Full_URL_Here` をあなたの Web API の URL に置き換えてください。 *Enter\_the\_Protected\_API\_Full\_URL\_Here*には、ASP.NET Web API のベース URL (デプロイされた Web API URL) とエンドポイント (/api/todolist) を含める必要があります。
    - `Enter_the_Protected_API_Scopes_Here`iOS サンプル アプリに Web API のアクセス許可を付与するに記録されているスコープに置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com`されている場合は、 `contoso`を使用します。 テナント サブドメインがわからない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-customer-tenant-portal#get-the-customer-tenant-details)る方法について説明します。

アプリを構成し、実行する準備ができました。

---

### サンプル アプリを実行して Web API を呼び出す

## [Android](#tab/android-external)
アプリをビルドして実行するには、次の手順に従います。

1. ツール バーの [実行構成] メニューからアプリを選択します。
2. ターゲット デバイス メニューで、アプリを実行するデバイスを選択します。

    デバイスが構成されていない場合は、Android Emulator を使用する Android 仮想デバイスを作成するか、物理 Android デバイスを接続する必要があります。
3. [ **実行** ] ボタンを選択します。
4. アクセス トークンを要求するには、[ **対話形式でトークンを取得** する] を選択します。
5. **[API - Get を実行**] を選択して、以前に設定した ASP.NET Core Web API を呼び出します。 Web API の呼び出しが成功すると HTTP 200 が返され、HTTP 403 は未承認のアクセスを意味します。

## [iOS/macOS](#tab/ios-macos-external)
アプリをビルドして実行するには、次の手順に従います。

1. コードをビルドして実行するには、Xcode の **[製品**] メニューから [**実行**] を選択します。 ビルドが成功すると、Xcode はシミュレーターでサンプル アプリを起動します。
2. アクセス トークンを要求するには、[ **対話形式でトークンを取得** する] を選択します。
3. **[API - Get を実行**] を選択して、以前に設定した ASP.NET Core Web API を呼び出します。 Web API の呼び出しが成功すると HTTP `200`が返され、HTTP `403` は未承認のアクセスを示します。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-mobile-app-sign-in"} -->
## クイック スタート - サンプル モバイル アプリでユーザーをサインインする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-sign-in
- Service: identity-platform
- Article date: 2024-10-30
- Summary: Microsoft ID プラットフォームを使用して従業員または顧客にサインインするようにサンプル モバイル アプリを構成するためのクイック スタート。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

開始する前に、このページの上部にある **[テナントの種類** の選択] セレクターを使用して、テナントの種類を選択します。 Microsoft Entra ID には、 [従業員](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#workforce-tenants) と [外部](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#external-tenants)の 2 つのテナント構成が用意されています。 従業員テナントの構成は、従業員、内部アプリ、およびその他の組織リソースを対象としています。 外部テナントは、顧客向けのアプリ用です。

::: zone pivot="workforce"

## [Android](#tab/android-workforce)
このクイック スタートでは、Android アプリケーションがユーザーをサインインさせ、Microsoft Graph API を呼び出すアクセス トークンを取得する方法を示すコード サンプルをダウンロードして実行します。

アプリケーションは、Microsoft ID プラットフォームがアプリケーションにトークンを提供できるように、Microsoft Entra ID のアプリ オブジェクトによって表される必要があります。

## [iOS/macOS](#tab/ios-macos-workforce)
このクイック スタートでは、ネイティブ iOS または macOS アプリケーションがユーザーをサインインさせ、Microsoft Graph API を呼び出すアクセス トークンを取得する方法を示すコード サンプルをダウンロードして実行します。

このクイック スタートは、iOS アプリと macOS アプリの両方に適用されます。 一部の手順は iOS アプリにのみ必要であり、そのように示されます。

---

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 次の Microsoft Entra ロールには、必要なアクセス許可が含まれます。
    - アプリケーション管理者
    - アプリケーション開発者
- 従業員テナント。 既定のディレクトリを使用するか、 [新しいテナントを設定](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)できます。

## [Android](#tab/android-workforce)
- [組織のディレクトリと個人用](https://entra.microsoft.com)の Microsoft アカウントのアカウント用に構成された Microsoft *Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- Android Studio
- Android 16 以降

## [iOS/macOS](#tab/ios-macos-workforce)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- XCode 10 以降
- iOS 10 以降
- macOS 10.12 以降

---

### リダイレクト URI を追加する

ダウンロードしたコード サンプルとの互換性を確保するには、アプリ登録で特定のリダイレクト URI を構成する必要があります。 これらの URI は、ユーザーが正常にサインインした後にアプリにリダイレクトするために不可欠です。

## [Android](#tab/android-workforce)
1. [**管理**] で、**認証**&gt;**プラットフォームの追加**&gt;Android を選択**します**。
2. 上記でダウンロードしたサンプルの種類に基づいて、プロジェクトのパッケージ名を入力します。

    - Java サンプル - `com.azuresamples.msalandroidapp`
    - Kotlin サンプル - `com.azuresamples.msalandroidkotlinapp`
3. [**Android アプリの構成**] ウィンドウの [**署名ハッシュ**] セクションで、[**開発署名ハッシュの生成**] を選択し、KeyTool コマンドをコマンド ラインにコピーします。

    - KeyTool.exe は、Java Development Kit (JDK) の一部としてインストールされます。 また、KeyTool コマンドを実行するには、OpenSSL ツールをインストールする必要があります。 詳細については、 [キーの生成に関する Android ドキュメントを](https://developer.android.com/studio/publish/app-signing#generate-key) 参照してください。
4. KeyTool によって生成された **署名ハッシュ** を入力します。
5. **後で**アプリを構成するときに入力できるように、[構成] を選択し、[**Android 構成**] ウィンドウに表示される **MSAL** 構成を保存します。
6. **完了**を選択します。

## [iOS/macOS](#tab/ios-macos-workforce)
1. [ **管理**] で、 **認証**&gt;**プラットフォームの追加**&gt;**iOS** を選択します。
2. アプリケーションの **バンドル識別子** を入力します。 バンドル識別子は、 `com.<yourname>.identitysample.MSALMacOS`など、アプリケーションを一意に識別する一意の文字列です。 使用する値を書き留めます。 iOS 構成は macOS アプリケーションにも適用されることに注意してください。
3. このクイック スタートの後半で、[ **構成]** を選択し **、MSAL 構成** の詳細を保存します。
4. **完了**を選択します。

---

### サンプル アプリをダウンロードする

## [Android](#tab/android-workforce)
- Java: [コードをダウンロードします](https://github.com/Azure-Samples/ms-identity-android-java/archive/master.zip)。
- Kotlin: [コードをダウンロードします](https://github.com/Azure-Samples/ms-identity-android-kotlin/archive/master.zip)。

## [iOS/macOS](#tab/ios-macos-workforce)
サンプル プロジェクトをダウンロードする

- [iOS 用のコード サンプルをダウンロードする](https://github.com/Azure-Samples/active-directory-ios-swift-native-v2/archive/master.zip)
- [macOS 用のコード サンプルをダウンロードする](https://github.com/Azure-Samples/active-directory-macOS-swift-native-v2/archive/master.zip)

#### 依存関係のインストール

1. zip ファイルを抽出します。
2. ターミナル ウィンドウで、ダウンロードしたコード サンプルを含むフォルダーに移動し、 `pod install` 実行して最新の MSAL ライブラリをインストールします。

---

### サンプル アプリケーションを構成する

## [Android](#tab/android-workforce)
1. Android Studio のプロジェクト ウィンドウで、 **app\src\main\res** に移動します。
2. **res** を右クリックし、[**新規**&gt;Directory] を選択**します**。 新しいディレクトリ名として「 `raw` 」と入力し、[ **OK] を選択します**。
3. **app**&gt;**src**&gt;**main**&gt;**res**&gt;**raw** で、`auth_config_single_account.json`という名前の JSON ファイルに移動し、前に保存した MSAL 構成を貼り付けます。

    リダイレクト URI の下に、次のように貼り付けます。

    ```json
      "account_mode" : "SINGLE",
    ```

    構成ファイルは次の例のようになります。

    ```json
    {
      "client_id": "00001111-aaaa-bbbb-3333-cccc4444",
      "authorization_user_agent": "WEBVIEW",
      "redirect_uri": "msauth://com.azuresamples.msalandroidapp/00001111%cccc4444%3D",
      "broker_redirect_uri_registered": false,
      "account_mode": "SINGLE",
      "authorities": [
        {
          "type": "AAD",
          "audience": {
            "type": "AzureADandPersonalMicrosoftAccount",
            "tenant_id": "common"
          }
        }
      ]
    }
    ```
4. */app/src/main/AndroidManifest.xml* ファイルを開きます。
5. プレースホルダーを見つけます。

    - `enter_the_signature_hash` プラットフォームのリダイレクト URL を追加したときに生成した **署名ハッシュ** に置き換えます。

    このチュートリアルでは、単一アカウント モードでアプリを構成する方法のみを示しています。詳細については、 [単一アカウント モードと複数アカウント モード](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-multi-account) の構成に関 [するページを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-configuration) 。

### サンプル アプリを実行する

Android Studio の **使用可能な** デバイス ドロップダウンからエミュレーターまたは物理デバイスを選択し、アプリを実行します。

サンプル アプリは **、シングル アカウント モード** 画面で起動します。 既定のスコープ **user.read** は既定で提供されます。これは、Microsoft Graph API 呼び出し中に独自のプロファイル データを読み取るときに使用されます。 既定では、Microsoft Graph API 呼び出しの URL が提供されます。 必要に応じて、これらの両方を変更できます。

[Image: 1 つのアカウントと複数のアカウントの使用状況を示す MSAL サンプル アプリのスクリーンショット。]

アプリ メニューを使用して、1 つのアカウント モードと複数のアカウント モードを変更します。

シングル アカウント モードでは、職場または自宅のアカウントを使用してサインインします。

1. [ **グラフ データを対話的に取得** する] を選択して、ユーザーに資格情報の入力を求めます。 Microsoft Graph API の呼び出しからの出力が画面の下部に表示されます。
2. サインインしたら、[ **グラフ データを自動的に取得** する] を選択して、ユーザーに資格情報の入力を求めずに Microsoft Graph API を呼び出します。 Microsoft Graph API の呼び出しからの出力が画面の下部に表示されます。

複数アカウント モードでは、同じ手順を繰り返すことができます。 さらに、サインインしているアカウントを削除できます。これにより、そのアカウントのキャッシュされたトークンも削除されます。

## [iOS/macOS](#tab/ios-macos-workforce)
上記のオプション 1 を選択した場合は、これらの手順をスキップできます。

1. XCode でプロジェクトを開きます。
2. **ViewController.swift を**編集し、'let kClientID' で始まる行を次のコード スニペットに置き換えます。 このクイック スタートでアプリを登録したときに保存した clientID を使用して、 `kClientID` の値を必ず更新してください。

    ```swift
    let kClientID = "Enter_the_Application_Id_Here"
    ```
3. [Microsoft Entra National クラウド](https://learn.microsoft.com/ja-jp/graph/deployments#app-registration-and-token-service-root-endpoints)用のアプリを構築する場合は、'let kGraphEndpoint' と 'let kAuthority' で始まる行を正しいエンドポイントに置き換えます。 グローバル アクセスの場合は、既定値を使用します。

    ```swift
    let kGraphEndpoint = "https://graph.microsoft.com/"
    let kAuthority = "https://login.microsoftonline.com/common"
    ```
4. その他のエンドポイントについては、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/graph/deployments#app-registration-and-token-service-root-endpoints)。 たとえば、Microsoft Entra Germany でクイック スタートを実行するには、次のコマンドを使用します。

    ```swift
    let kGraphEndpoint = "https://graph.microsoft.de/"
    let kAuthority = "https://login.microsoftonline.de/common"
    ```
5. プロジェクト設定を開きます。 [ID] セクション **で** 、[ **バンドル識別子**] を入力します。
6. **Info.plist** を右クリックし、[Open **As**&gt;**Source Code**] を選択します。
7. dict ルート ノードで、 `Enter_the_bundle_Id_Here` をポータルで使用した ***バンドル ID*** に置き換えます。 文字列内の `msauth.` プレフィックスに注目してください。

    ```xml
    <key>CFBundleURLTypes</key>
    <array>
       <dict>
          <key>CFBundleURLSchemes</key>
          <array>
             <string>msauth.Enter_the_Bundle_Id_Here</string>
          </array>
       </dict>
    </array>
    ```
8. アプリをビルドして実行します。

---

### サンプルのしくみ

## [Android](#tab/android-workforce)
[Image: このクイック スタートで生成されたサンプル アプリの動作を示す図。]

このコードは、1 つのアカウントと複数のアカウント MSAL アプリを記述する方法を示すフラグメントに編成されています。 コード ファイルは次のように編成されています。

| File | 対象 |
| --- | --- |
| MainActivity | UI を管理します |
| MSGraphRequestWrapper | MSAL によって提供されるトークンを使用して Microsoft Graph API を呼び出します |
| MultipleAccountModeFragment | マルチアカウント アプリケーションを初期化し、ユーザー アカウントを読み込み、Microsoft Graph API を呼び出すトークンを取得します |
| SingleAccountModeFragment | 単一アカウント アプリケーションを初期化し、ユーザー アカウントを読み込み、Microsoft Graph API を呼び出すトークンを取得します |
| res/auth\_config\_multiple\_account.json | 複数アカウント構成ファイル |
| res/auth\_config\_single\_account.json | 単一アカウント構成ファイル |
| Gradleスクリプト/build.grade (モジュール:app) | MSAL ライブラリの依存関係がここに追加されます |

次に、これらのファイルについて詳しく説明し、各ファイルで MSAL 固有のコードを呼び出します。

## [iOS/macOS](#tab/ios-macos-workforce)
[Image: このクイック スタートで生成されたサンプル アプリの動作を示す図。]

---

::: zone-end

::: zone pivot="external"

このクイック スタートでは、アプリケーションの登録、リダイレクト URL の設定、構成の更新、アプリのテストを行って、ユーザーをサインインさせるサンプル Android、.NET MAUI Android、iOS/macOS アプリを構成する方法について説明します。

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 次の Microsoft Entra ロールには、必要なアクセス許可が含まれます。
    - アプリケーション管理者
    - アプリケーション開発者
- 外部テナント。 作成するには、次の方法から選択します。
    - [Microsoft Entra 外部 ID 拡張機能](https://aka.ms/ciamvscode/samples/marketplace)を使用して、Visual Studio Code で外部テナントを直接設定します。 *(おすすめ)*
    - Microsoft Entra 管理センターで[新しい外部テナントを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)します。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID

## [Android](#tab/android-external)
- [Android Studio](https://developer.android.com/studio)。

## [Android(.NET MAUI)](#tab/android-netmaui-external)
- ユーザー フロー。 詳細については、 [外部テナント内のアプリのセルフサービス サインアップ ユーザー フローを作成する方法](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)に関するページを参照してください。 このユーザー フローは、複数のアプリケーションに使用できます。
- [アプリケーションをユーザー フローに追加](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)します。
- [.NET 7.0 SDK (英語)](https://dotnet.microsoft.com/download/dotnet/7.0)
- MAUI ワークロードがインストールされている [Visual Studio 2022](https://aka.ms/vsdownloads):
    - [Windows の手順](https://learn.microsoft.com/ja-jp/dotnet/maui/get-started/installation?tabs=vswin)
    - [macOS の手順](https://learn.microsoft.com/ja-jp/dotnet/maui/get-started/installation?tabs=vsmac)

## [iOS/macOS](#tab/ios-macos-external)
- [Xcode](https://developer.apple.com/xcode/resources/)。

---

### プラットフォーム リダイレクト URL を追加する

ダウンロードしたコード サンプルとの互換性を確保するには、アプリ登録で特定のリダイレクト URI を構成する必要があります。 これらの URI は、ユーザーが正常にサインインした後にアプリにリダイレクトするために不可欠です。

## [Android](#tab/android-external)
アプリの登録にアプリの種類を指定するには、次の手順に従います。

1. [**管理**] で、[**認証**] を選択します。
2. [ **プラットフォームの構成** ] ページで、[ **プラットフォームの追加**] を選択し、[ **Android** ] オプションを選択します。
3. プロジェクトのパッケージ名を入力します。 [サンプル コード](https://github.com/Azure-Samples/ms-identity-ciam-browser-delegated-android-sample)をダウンロードした場合、この値は`com.azuresamples.msaldelegatedandroidkotlinsampleapp`。
4. [**Android アプリの構成**] ウィンドウの [**署名ハッシュ**] セクションで、[**開発署名ハッシュの生成] を選択します。これは開発環境ごとに変更されます。**ターミナルでオペレーティング システムの KeyTool コマンドをコピーして実行します。
5. KeyTool によって生成された **署名ハッシュ** を入力します。
6. **設定**を選択します。
7. **Android** **の構成ウィンドウから MSAL 構成**をコピーし、後でアプリを構成するために保存します。
8. **完了**を選択します。

### パブリック クライアント フローを有効にする

アプリをパブリック クライアントとして識別するには、次の手順に従います。

1. [**管理**] で、[**認証**] を選択します。
2. [ **詳細設定]** の [ **パブリック クライアント フローを許可**する] で、[ **はい**] を選択します。
3. **[保存]** を選択して変更を保存します。

## [Android(.NET MAUI)](#tab/android-netmaui-external)
アプリの登録にアプリの種類を指定するには、次の手順に従います。

1. [**管理**] で、[**認証**] を選択します。
2. [ **プラットフォームの構成** ] ページで、[ **プラットフォームの追加**] を選択し、[ **モバイル アプリケーションとデスクトップ アプリケーション** ] オプションを選択します。
3. **[リダイレクト URI] に**「`msal{client_id}://auth`」と入力します。 `{client_id}`がアプリ登録の値と一致していることを確認します。 **設定**を選択します。
4. **[保存]** を選択して変更を保存します。

## [iOS/macOS](#tab/ios-macos-external)
アプリの登録にアプリの種類を指定するには、次の手順に従います。

1. [**管理**] で、[**認証**] を選択します。
2. [ **プラットフォームの構成** ] ページで、[ **プラットフォームの追加**] を選択し、[ **iOS/ macOS** ] オプションを選択します。
3. プロジェクトのバンドル ID を入力します。 [サンプル コード](https://github.com/Azure-Samples/ms-identity-ciam-browser-delegated-ios-sample.git)をダウンロードした場合、この値は`com.microsoft.identitysample.ciam.MSALiOS`。
4. 後でアプリを**構成**するときに入力できるように、**iOS/macOS 構成**ウィンドウに表示される [構成] を選択して **MSAL** 構成を保存します。
5. **完了**を選択します。

### パブリック クライアント フローを有効にする

アプリをパブリック クライアントとして識別するには、次の手順に従います。

1. [**管理**] で、[**認証**] を選択します。
2. [ **詳細設定]** の [ **パブリック クライアント フローを許可**する] で、[ **はい**] を選択します。
3. **[保存]** を選択して変更を保存します。

---

### サンプル アプリケーションの複製

## [Android](#tab/android-external)
サンプル アプリケーションを取得するには、GitHub から複製するか、 [.zip ファイルとしてダウンロード](https://github.com/Azure-Samples/ms-identity-ciam-browser-delegated-android-sample/archive/refs/heads/main.zip)します。

- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-browser-delegated-android-sample
    ```

## [Android(.NET MAUI)](#tab/android-netmaui-external)
.NET MAUI Android アプリケーションのサンプル コードを取得するには、次のコマンドを実行して [、.zip ファイルをダウンロード](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/archive/refs/heads/main.zip) するか、GitHub からサンプルの .NET MAUI Android アプリケーションを複製します。

```bash
git clone https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial.git
```

## [iOS/macOS](#tab/ios-macos-external)
サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-browser-delegated-ios-sample.git
    ```

---

### サンプル アプリケーションを構成する

## [Android](#tab/android-external)
認証と Microsoft Graph リソースへのアクセスを有効にするには、次の手順に従ってサンプルを構成します。

1. Android Studio で、複製したプロジェクトを開きます。
2. */app/src/main/res/raw/auth\_config\_ciam.json* ファイルを開きます。
3. プレースホルダーを見つけます。

    - `Enter_the_Application_Id_Here` をクリックし、先ほど登録したアプリの **アプリケーション (クライアント) ID** に置き換えます。
    - `Enter_the_Redirect_Uri_Here` プラットフォーム リダイレクト URL を追加したときに前にダウンロードした Microsoft Authentication Library (MSAL) 構成ファイルの *redirect\_uri* の値に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com`されている場合は、 `contoso`を使用します。 テナント サブドメインがわからない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-customer-tenant-portal#get-the-customer-tenant-details)る方法について説明します。
4. */app/src/main/AndroidManifest.xml* ファイルを開きます。
5. プレースホルダーを見つけます。

    - `ENTER_YOUR_SIGNATURE_HASH_HERE` プラットフォームのリダイレクト URL を追加したときに生成した **署名ハッシュ** に置き換えます。
6. */app/src/main/java/com/azuresamples/msaldelegatedandroidkotlinsampleapp/MainActivity.kt* ファイルを開きます。
7. `scopes`という名前のプロパティを検索し、[[管理者の同意の付与](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#grant-admin-consent-external-tenants-only)] に記録されているスコープを設定します。 スコープを記録していない場合は、このスコープ リストを空のままにすることができます。

    ```kotlin
    private const val scopes = "" // Developers should set the respective scopes of their Microsoft Graph resources here. For example, private const val scopes = "api://{clientId}/{ToDoList.Read} api://{clientId}/{ToDoList.ReadWrite}"
    ```

アプリを構成し、実行する準備ができました。

## [Android(.NET MAUI)](#tab/android-netmaui-external)
1. Visual Studio で、 *ms-identity-ciam-dotnet-tutorial-main/1-Authentication/2-sign-in-maui/appsettings.json* ファイルを開きます。
2. プレースホルダーを見つけます。
    1. `Enter_the_Tenant_Subdomain_Here` ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com`されている場合は、 `contoso`を使用します。 テナント名がない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。
    2. `Enter_the_Application_Id_Here` をクリックし、先ほど登録したアプリの **アプリケーション (クライアント) ID** に置き換えます。
3. Visual Studio で、 *ms-identity-ciam-dotnet-tutorial-main/1-Authentication/2-sign-in-maui/Platforms/Android/AndroidManifest.xml* ファイルを開きます。
4. プレースホルダーを見つけます。
    1. `Enter_the_Application_Id_Here` をクリックし、先ほど登録したアプリの **アプリケーション (クライアント) ID** に置き換えます。

## [iOS/macOS](#tab/ios-macos-external)
認証と Microsoft Graph リソースへのアクセスを有効にするには、次の手順に従ってサンプルを構成します。

1. Xcode で、複製したプロジェクトを開きます。
2. */MSALiOS/Configuration.swift ファイルを*開きます。
3. プレースホルダーを見つけます。

    - `Enter_the_Application_Id_Here` をクリックし、先ほど登録したアプリの **アプリケーション (クライアント) ID** に置き換えます。
    - `Enter_the_Redirect_URI_Here` プラットフォーム リダイレクト URL を追加したときに前にダウンロードした Microsoft Authentication Library (MSAL) 構成ファイルの *kRedirectUri* の値に置き換えます。
    - `Enter_the_Protected_API_Scopes_Here` をクリックし、「 [管理者の同意を付与](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#grant-admin-consent-external-tenants-only)する」に記録されているスコープに置き換えます。 スコープを記録していない場合は、このスコープ リストを空のままにすることができます。
    - `Enter_the_Tenant_Subdomain_Here` ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com`されている場合は、 `contoso`を使用します。 テナント サブドメインがわからない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。

アプリを構成し、実行する準備ができました。

---

### サンプル アプリを実行してテストする

## [Android](#tab/android-external)
アプリをビルドして実行するには、次の手順に従います。

1. ツール バーの [実行構成] メニューからアプリを選択します。
2. ターゲット デバイス メニューで、アプリを実行するデバイスを選択します。

    デバイスが構成されていない場合は、Android Emulator を使用する Android 仮想デバイスを作成するか、物理 Android デバイスを接続する必要があります。
3. [ **実行** ] ボタンを選択します。
4. アクセス トークンを要求するには、[ **対話形式でトークンを取得** する] を選択します。
5. **[API - Perform GET]** を選択して保護された ASP.NET Core Web API を呼び出すと、エラーが発生します。

保護された Web API の呼び出しの詳細については、次の手順を参照してください。

## [Android(.NET MAUI)](#tab/android-netmaui-external)
.NET MAUI アプリは、複数のオペレーティング システムとデバイスで実行するように設計されています。 アプリをテストしてデバッグするターゲットを選択する必要があります。

Visual Studio ツール バーの **デバッグ ターゲット** を、デバッグしてテストするデバイスに設定します。 次の手順は、 **デバッグ ターゲット** を Android に設定する方法を示 *しています*。

1. [ **デバッグ ターゲット]** ドロップダウンを選択します。
2. **[Android エミュレーター]** を選択します。
3. エミュレーター デバイスを選択します。

*F5* キーを押すか、Visual Studio の上部にある*再生ボタン*を選択して、アプリを実行します。

1. サンプルの .NET MAUI Android アプリをテストできるようになりました。 アプリを実行すると、エミュレーターに Android アプリ ウィンドウが表示されます。

    [Image: Android アプリケーションのサインイン ボタンのスクリーンショット。]
2. 表示された Android ウィンドウ **で、[サインイン** ] ボタンを選択します。 ブラウザー ウィンドウが開き、サインインするように求められます。

    [Image: Android アプリケーションで資格情報を入力するためのユーザー プロンプトのスクリーンショット。]

    サインイン プロセス中に、さまざまなアクセス許可を付与するように求められます (アプリケーションがデータにアクセスできるようにします)。 サインインと同意が成功すると、アプリケーション画面にメイン ページが表示されます。

    [Image: サインイン後の Android アプリケーションのメイン ページのスクリーンショット。]

## [iOS/macOS](#tab/ios-macos-external)
アプリをビルドして実行するには、次の手順に従います。

1. コードをビルドして実行するには、Xcode の **[製品**] メニューから [**実行**] を選択します。 ビルドが成功すると、Xcode はシミュレーターでサンプル アプリを起動します。
2. アクセス トークンを要求するには、[ **対話形式でトークンを取得** する] を選択します。
3. **[API - Perform GET]** を選択して保護された ASP.NET Core Web API を呼び出すと、エラーが発生します。

保護された Web API の呼び出しの詳細については、次の手順を参照してください。

---

### 次のステップ

## [Android](#tab/android-external)
- [サンプル Android (Kotlin) アプリでユーザーをサインインさせ、保護された Web API を呼び出](https://learn.microsoft.com/ja-jp/entra/external-id/customers/sample-mobile-app-android-kotlin-sign-in-call-api)します。

## [Android(.NET MAUI)](#tab/android-netmaui-external)
- [既定のブランドをカスタマイズします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers)。
- [Google でサインインを構成します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-google-federation-customers)。

## [iOS/macOS](#tab/ios-macos-external)
- [サンプル iOS (Swift) アプリでユーザーをサインインさせ、保護された Web API を呼び出します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/sample-mobile-app-ios-swift-sign-in-call-api)。

---

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-native-authentication-android-call-api"} -->
## サンプル Android モバイル アプリで Web API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-android-call-api
- Service: identity-platform / external
- Article date: 2024-08-21
- Summary: Android (Kotlin) サンプル アプリで Web API を呼び出す方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このクイックスタートでは、ASP.NET Core Web API を呼び出すようにサンプル Android モバイル アプリケーションを構成する方法について説明します。

### [前提条件]

- [ネイティブ認証を使用して、サンプル Android (Kotlin) モバイル アプリでユーザーをサインインします](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-android-sign-in)。
- [Microsoft Entra 管理センター](https://entra.microsoft.com)で、次の構成で Web API 用の新しいアプリケーションを登録します。 詳細な手順については、「 [アプリケーションの登録」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)参照してください。 後で使用するために、 **アプリケーション (クライアント) ID** と **ディレクトリ (テナント) ID を**記録します。
    - **名前**: *ciam-ToDoList-api*。
    - **サポートされているアカウントの種類**: *この組織のディレクトリ内のアカウントのみ (シングル テナント)*

#### API スコープを構成する

クライアント アプリがユーザーのアクセス トークンを正常に取得するために、API は少なくとも 1 つのスコープ ([委任されたアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)とも呼ばれます) を発行する必要があります。 スコープを発行するには、次の手順に従います。

1. **[アプリの登録]** ページで、作成した API アプリケーション (*ciam-ToDoList-api*) を選択して **[概要]** ページを開きます。
2. **[管理]** の **[API の公開]** を選択します。
3. ページ上部の **[アプリケーション ID URI]** の横にある **[追加]** リンクを選択して、このアプリに一意の URI を生成します。
4. 提案されたアプリケーション ID URI (`api://{clientId}` など) を受け入れ、**[保存]** を選択します。 Web アプリケーションで Web API のアクセス トークンを要求すると、API に対して定義する各スコープのプレフィックスとしてこの URI が追加されます。
5. **[この API で定義されるスコープ]** で、 **[スコープの追加]** を選択します。
6. API への読み取りアクセスを定義する次の値を入力した後に、**[スコープの追加]** を選択して変更を保存します。

    | プロパティ | 価値 |
    | --- | --- |
    | スコープ名 | *ToDoList.Read* |
    | 誰が同意できるか | **管理者のみ** |
    | 管理者の同意の表示名 | *'TodoListApi' を使用してユーザーの ToDo リストを読み取る* |
    | 管理者の同意の説明 | *'TodoListApi' を使用して、アプリがユーザーの ToDo リストを読み取ることを許可します*。 |
    | 状態 | **有効** |
7. もう一度 **[スコープの追加]** を選択し、API への読み取りおよび書き込みアクセスを定義する次の値を入力します。 **[スコープの追加]** を選択して変更を保存します。

    | プロパティ | 価値 |
    | --- | --- |
    | スコープ名 | *ToDoList.ReadWrite* |
    | 誰が同意できるか | **管理者のみ** |
    | 管理者の同意の表示名 | *'ToDoListApi' を使用した、ユーザーの ToDo リストの読み取りと書き込み* |
    | 管理者の同意の説明 | *'ToDoListApi' を使用して、アプリからユーザーの ToDo リストを読み書きできるようにする* |
    | 状態 | **有効** |

Web API の[アクセス許可を発行するときの最小限の特権の原則](https://learn.microsoft.com/ja-jp/security/zero-trust/develop/protected-api-example)について説明します。

#### アプリ ロールを構成する

API は、クライアント アプリがアクセス トークンをそれ自体として取得するために、アプリケーションに対して少なくとも 1 つのアプリ ロール ( [アプリケーションアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)とも呼ばれます) を発行する必要があります。 アプリケーションのアクセス許可は、クライアント アプリケーションが自身として正常に認証できるようにして、ユーザーをサインインさせる必要がないようにする場合に、API が発行するアクセス許可の種類です。 アプリケーションのアクセス許可を発行するには、次の手順に従います。

1. **[アプリの登録]** ページから、作成したアプリケーション (*ciam-ToDoList-api* など) を選択して、その **[概要]** ページを開きます。
2. **[管理]** で、**[アプリ ロール]** を選択します。
3. **[アプリ ロールの作成]** を選択し、次の値を入力し、**[適用]** を選択して変更を保存します:

    | プロパティ | 価値 |
    | --- | --- |
    | 表示名 | *ToDoList.Read.All* |
    | Allowed member types (許可されるメンバーの種類) | **アプリケーション** |
    | 価値 | *ToDoList.Read.All* |
    | 説明 | *'ToDoListApi' を使用して、アプリがすべてのユーザーの ToDo リストを読むことができるようにする* |
    | このアプリ ロールを有効にしますか? | オンのままにする |
4. もう一度 **[アプリ ロールの作成]** を選択し、2 番目のアプリ ロールに次の値を入力し、**[適用]** を選択して変更を保存します:

    | プロパティ | 価値 |
    | --- | --- |
    | 表示名 | *ToDoList.ReadWrite.All* |
    | Allowed member types (許可されるメンバーの種類) | **アプリケーション** |
    | 価値 | *ToDoList.ReadWrite.All* |
    | 説明 | *'ToDoListApi' を使用して、アプリがすべてのユーザーの ToDo リストを読み書きできるようにする* |
    | このアプリ ロールを有効にしますか? | オンのままにする |

#### オプションクレームの設定

**idtyp** の省略可能な要求を追加すると、Web API がトークンが **アプリ** トークンなのか **アプリ + ユーザー** トークンなのかを判断するのに役立ちます。 **scp** と**ロール**要求の組み合わせを同じ目的で使用できますが、**idtyp** 要求を使用すると、アプリ トークンとアプリ + ユーザー トークンを区別する最も簡単な方法です。 たとえば、トークンがアプリ専用トークンの場合、この要求の値は *app* です。

### Android サンプル アプリに API アクセス許可を付与する

クライアント アプリと Web API の両方を登録し、スコープを作成して API を公開したら、次の手順に従って、API に対するクライアントのアクセス許可を構成できます。

1. [**アプリの登録**] ページで、作成したアプリケーション (ciam-client-app など) を選択して、**の [概要]** ページを開きます。
2. [**管理**] で **API 許可**を選択します。
3. **[構成されたアクセス許可]** の下で **[アクセス許可の追加]** を選択します。
4. **[所属する組織で使用している API]** タブを選択します。
5. API の一覧で、API (*ciam-ToDoList-api* など) を選択します。
6. **[委任されたアクセス許可]** オプションを選択します。
7. アクセス許可の一覧で **[ToDoList.Read, ToDoList.ReadWrite]** を選択します (必要に応じて検索ボックスを使用します)。
8. **[アクセス許可の追加]** ボタンを選択します。
9. この時点で、アクセス許可が正しく割り当てられました。 ただし、このテナントは顧客のテナントであるため、コンシューマー ユーザー自身がこれらのアクセス許可に同意することはできません。 これに対処するには、管理者がテナント内のすべてのユーザーに代わってこれらのアクセス許可に同意する必要があります。

    1. **[&lt;テナント名&gt; に管理者の同意を与えます]** を選択してから、**[はい]** を選択します。
    2. **[最新の情報に更新]** を選択し、両方のアクセス許可の &lt; に "**&gt;テナント名 に付与されました**" と表示されていることを確認します。
10. **[Configured permissions] (構成されたアクセス許可)** の一覧で**ToDoList.Read** と **ToDoList.ReadWrite** のアクセス許可を一度に 1 つずつ選択し、後で使用するためにアクセス許可の完全な URI をコピーします。 完全なアクセス許可 URI は、`api://{clientId}/{ToDoList.Read}` または `api://{clientId}/{ToDoList.ReadWrite}` のようになります。

### サンプル Web API を複製またはダウンロードする

サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial.git
    ```
- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

### サンプル Web API の構成と実行

1. コード エディターで、`2-Authorization/1-call-own-api-aspnet-core-mvc/ToDoListAPI/appsettings.json` ファイルを開きます。
2. プレースホルダーを見つけてください。

    - `Enter_the_Application_Id_Here` を選択し、先ほどコピーした Web API の **アプリケーション (クライアント) ID** に置き換えます。
    - `Enter_the_Tenant_Id_Here` を選択し、先ほどコピーした **ディレクトリ (テナント) ID** に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。

それを呼び出すには、Android サンプル アプリ用の Web API をホストする必要があります。 [クイック スタート: ASP.NET Web アプリをデプロイして Web](https://learn.microsoft.com/ja-jp/azure/app-service/quickstart-dotnetcore) API をデプロイする方法に従います。

### Web API を呼び出すサンプル Android モバイル アプリを構成する

このサンプルでは、複数の Web API URL エンドポイントとスコープ のセットを構成できます。 この場合は、1 つの Web API URL エンドポイントとそれに関連付けられているスコープのみを構成します。

1. Android Studio で、 `/app/src/main/java/com/azuresamples/msalnativeauthandroidkotlinsampleapp/AccessApiFragment.kt` ファイルを開きます。
2. `WEB_API_URL_1`という名前のプロパティを検索し、URL を Web API に設定します。

    ```kotlin
    private const val WEB_API_URL_1 = "" // Developers should set the respective URL of their web API here
    ```
3. `scopesForAPI1`という名前のプロパティを検索し、「API のアクセス許可を Android サンプル アプリに付与する」に記録されているスコープを設定します。

    ```kotlin
    private val scopesForAPI1 = listOf<String>() // Developers should set the respective scopes of their web API here. For example, private val scopes = listOf<String>("api://{clientId}/{ToDoList.Read}", "api://{clientId}/{ToDoList.ReadWrite}")
    ```

### Android サンプル アプリを実行して Web API を呼び出す

アプリをビルドして実行するには、次の手順に従います。

1. ツール バーの [実行構成] メニューからアプリを選択します。
2. ターゲット デバイス メニューで、アプリを実行するデバイスを選択します。

    デバイスが構成されていない場合は、Android Emulator を使用する Android 仮想デバイスを作成するか、物理デバイスを接続する必要があります。
3. [ **実行** ] ボタンを選択します。 電子メールとワンタイム パスコード画面でアプリが開きます。
4. [API] タブを選択して API 呼び出しをテストします。 Web API の呼び出しが成功すると HTTP `200`が返され、HTTP `403` は未承認のアクセスを示します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-native-authentication-android-sign-in"} -->
## ネイティブ認証を使用して Android モバイル アプリでユーザーをサインインします。 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-android-sign-in
- Service: identity-platform / external
- Article date: 2025-11-17
- Summary: Microsoft Entra のネイティブ認証を使用して顧客ユーザーにサインインするようにサンプル Android (Kotlin) サンプル アプリを構成する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このクイックスタートでは、Microsoft Entra の [ネイティブ認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication)を使用したサインアップ、サインイン、サインアウト、パスワード リセットのシナリオを示す Android サンプル アプリケーションを実行する方法について説明します。

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 アカウントをまだお持ちでない場合は、[無料](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)アカウントを作成します。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 次の Microsoft Entra ロールには、必要なアクセス許可が含まれます。

    - アプリケーション管理者
    - アプリケーション開発者
- 外部テナント。 お持ちでない場合は、Microsoft Entra 管理センターで [新しい外部テナントを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal) します。
- まだ登録していない場合は、[Microsoft Entra 管理センターでアプリケーションを登録します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。 次のことを確認します。

    - 後で使用するために、 **アプリケーション (クライアント) ID** と **ディレクトリ (テナント) ID を** 記録します。
    - アプリケーション[に管理者の同意を付与](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#grant-admin-consent-external-tenants-only)します。
- まだ作成していない場合は、[Microsoft Entra 管理センターでユーザー フローを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)します
- [アプリの登録をユーザー フローに関連付ける](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)
- [Android Studio](https://developer.android.com/studio)。

### パブリック クライアントとネイティブ認証フローを有効にする

このアプリがパブリック クライアントであり、ネイティブ認証を使用できることを指定するには、パブリック クライアントとネイティブ認証フローを有効にします。

1. アプリ登録ページから、パブリック クライアントとネイティブ認証フローを有効にするアプリ登録を選択します。
2. **[管理]** で、 **[認証]** を選択します。
3. [詳細設定 で、パブリック クライアント フローを許可します。
    1. **[次のモバイルとデスクトップのフローを有効にする]** で、**[はい]** を選びます。
    2. ネイティブ認証 **を有効にする**には、[はい] を選択します。
4. **[保存]** ボタンを選びます。

### サンプルの Android モバイル アプリケーションを複製する

1. ターミナルを開き、コードを保持するディレクトリに移動します。
2. 次のコマンドを実行して、GitHub からアプリケーションを複製します。

    ```bash
    git clone https://github.com/Azure-Samples/ms-identity-ciam-native-auth-android-sample 
    ```

### サンプル Android モバイル アプリケーションを構成する

1. Android Studio で、複製したプロジェクトを開きます。
2. *app/src/main/res/raw/native\_auth\_sample\_app\_config.json* ファイルを開きます。
3. プレースホルダーを見つけてください。

    - `Enter_the_Application_Id_Here` を、前に登録したアプリの**アプリケーション (クライアント) ID** に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント サブドメインがわからない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。

これでアプリが構成され、実行する準備ができました。

### サンプルの Android モバイル アプリケーションを実行してテストする

アプリをビルドして実行するには、次の手順に従います。

1. ツール バーの [実行構成] メニューからアプリを選択します。
2. ターゲット デバイス メニューで、アプリを実行するデバイスを選択します。

    デバイスが構成されていない場合は、Android Emulator を使用する Android 仮想デバイスを作成するか、物理 Android デバイスを接続する必要があります。
3. **[実行]** ボタンを選択します。 アプリが **[電子メールと OTP** ] 画面を開きます。

    [Image: Android アプリケーションで電子メールを入力するためのユーザー プロンプトのスクリーンショット。]
4. 有効なメール アドレスを入力し、[ **サインアップ** ] ボタンを選択します。 アプリで送信コード画面が開き、電子メール アドレスに OTP コードが表示されます。

    [Image: Android アプリケーションでワンタイム パスコードを入力するためのユーザー プロンプトのスクリーンショット。]
5. 受信トレイに受信する OTP コードを入力し、[ **次へ**] を選択します。 サインアップが成功すると、アプリによって自動的にサインインされます。 メールの受信トレイに OTP コードが届かない場合は、[パスコードの再送信] を選択してしばらくしてから **再送信できます**。
6. サインアウトするには、[ **サインアウト** ] ボタンを選択します。

#### このサンプルでサポートされるその他のシナリオ

このサンプル アプリでは、次の認証フローもサポートされています。

- **電子メール + パスワード** は、パスワードを含む電子メールによるサインインまたはサインアップ フローを対象とします。
- **ユーザー属性を使用した電子メールとパスワードのサインアップ** では、電子メールとパスワードによるサインアップと、ユーザー属性の送信について説明します。
- **パスワード リセット** には、セルフサービス パスワード リセット (SSPR) が含まれます。
- **Access Protected API** では、ユーザーが正常にサインアップまたはサインインしてアクセス トークンを取得した後に、保護された API を呼び出す方法について説明します。
- **Web ブラウザーへのフォールバック** では、ユーザーが何らかの理由でネイティブ認証を使用して認証を完了できない場合に、ブラウザー ベースの認証をフォールバック メカニズムとして使用する方法について説明します。

### パスワード フローを使用して電子メールをテストする

このセクションでは、メールとパスワードのフロー、およびユーザー属性を使用したメールとパスワードサインアップ、SSPR などのバリエーションをテストします。

1. ユーザー フローを作成して新しい [ユーザー フローを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) する手順を使用しますが、今回は認証方法として **[パスワード付きの電子メール]** を選択します。 ユーザー属性として **国/地域** と **市区町村** を構成する必要があります。 または、既存のユーザー フローを変更して、**パスワードで電子メール**を使用することもできます (**外部 ID の**選択&gt;**ユーザー フロー**&gt;**SignInSignUpSample**&gt;**Identity プロバイダー**&gt;**Email with password**&gt;**Save**)。
2. [新しいユーザー フローにアプリを追加するには、アプリケーションを新しいユーザー フローに関連付ける](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)手順を使用します。
3. サンプル アプリを実行し、省略記号メニュー (**...**) を選択して、その他のオプションを開きます。
4. テストするシナリオ ( **[Email + password]\(電子メール + パスワード** \) や [ **Email + password sign-up with user attributes]\(ユーザー属性を使用した電子メール + パスワード + パスワードのサインアップ** \) や **[パスワードのリセット**]など) を選択し、プロンプトに従います。 **パスワードリセット**をテストするには、まずユーザーをサインアップし、テナント内のすべてのユーザーに対して[電子メールワンタイムパスコードを有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers)必要があります。

### 保護された API フローのテスト呼び出し

[「ネイティブ認証を使用してサンプル Android モバイル アプリで保護された Web API を呼び出](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-android-call-api)す」の手順を使用して、保護された Web API をサンプル Android モバイル アプリから呼び出します。

### エイリアスまたはユーザー名を使用してサインインを有効にする

メール アドレスとパスワードを使用してサインインするユーザーも、ユーザー名とパスワードでサインインできます。 代替サインイン識別子とも呼ばれるユーザー名には、顧客 ID、アカウント番号、またはユーザー名として使用する別の識別子を指定できます。

Microsoft Entra 管理センターを使用してユーザー アカウントにユーザー名を手動で割り当てたり、Microsoft Graph API を使用してアプリで自動化したりできます。

[「エイリアスまたはユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias)を使用してサインインする」の記事の手順を使用して、ユーザーがアプリケーションでユーザー名を使用してサインインできるようにします。

1. [サインインでユーザー名を有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#enable-username-in-sign-in-identifier-policy)。
2. [管理センターでユーザー名を持つユーザーを作成するか、ユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username) を [追加して既存のユーザーを更新します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)。 または、 [Microsoft Graph API を使用して、アプリでのユーザーの作成と更新を自動化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-native-authentication-ios-call-api"} -->
## ネイティブ認証を使用してサンプル iOS モバイル アプリでユーザーをサインインさせ、API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-ios-call-api
- Service: identity-platform / external
- Article date: 2024-08-21
- Summary: ネイティブ認証を使用してサンプル iOS (Swift) モバイル アプリでユーザーをサインインさせ、API を呼び出す方法について説明します

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このクイック スタートでは、ASP.NET Core Web API を呼び出すように iOS サンプル アプリケーションを構成する方法について説明します。

### [前提条件]

- [ネイティブ認証を使用して、サンプル iOS (Swift) モバイル アプリでユーザーをサインインします](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-ios-sign-in)。
- [任意の組織のディレクトリと個人用](https://entra.microsoft.com)の Microsoft アカウントのアカウント用に構成された、Web API 用の *Microsoft Entra 管理センター*に新しいアプリケーションを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID

#### Web API アプリケーションを登録する

1. [アプリケーション開発者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)にサインインします。
2. 複数のテナントにアクセスできる場合、上部のメニューの **[設定]** アイコン  を使用し、**[ディレクトリとサブスクリプション]** メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**アプリ登録**に移動します。
4. **[+ 新規登録]** を選択します。
5. 表示される **[アプリケーションの登録] ページ**で、アプリケーションの登録情報を入力します。

    1. [名前] セクションで、アプリのユーザーに表示されるわかりやすいアプリケーション名 (*ciam-ToDoList-api* など) を入力します。
    2. **[サポートされているアカウントの種類]** で、**[この組織のディレクトリ内のアカウントのみ]** を選択します。
6. **[登録]** を選択して、アプリケーションを作成します。
7. 登録が完了すると、アプリケーションの **[概要] ペイン**が表示されます。 ディレクトリ (テナント) ID と、アプリケーションのソース コードで使用するアプリケーション (クライアント) ID を記録します。

#### API スコープを構成する

クライアント アプリがユーザーのアクセス トークンを正常に取得するために、API は少なくとも 1 つのスコープ ([委任されたアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)とも呼ばれます) を発行する必要があります。 スコープを発行するには、次の手順に従います。

1. **[アプリの登録]** ページで、作成した API アプリケーション (*ciam-ToDoList-api*) を選択して **[概要]** ページを開きます。
2. **[管理]** の **[API の公開]** を選択します。
3. ページ上部の **[アプリケーション ID URI]** の横にある **[追加]** リンクを選択して、このアプリに一意の URI を生成します。
4. 提案されたアプリケーション ID URI (`api://{clientId}` など) を受け入れ、**[保存]** を選択します。 Web アプリケーションで Web API のアクセス トークンを要求すると、API に対して定義する各スコープのプレフィックスとしてこの URI が追加されます。
5. **[この API で定義されるスコープ]** で、 **[スコープの追加]** を選択します。
6. API への読み取りアクセスを定義する次の値を入力した後に、**[スコープの追加]** を選択して変更を保存します。

    | プロパティ | 価値 |
    | --- | --- |
    | スコープ名 | *ToDoList.Read* |
    | 誰が同意できるか | **管理者のみ** |
    | 管理者の同意の表示名 | *'TodoListApi' を使用してユーザーの ToDo リストを読み取る* |
    | 管理者の同意の説明 | *'TodoListApi' を使用して、アプリがユーザーの ToDo リストを読み取ることを許可します*。 |
    | 状態 | **有効** |
7. もう一度 **[スコープの追加]** を選択し、API への読み取りおよび書き込みアクセスを定義する次の値を入力します。 **[スコープの追加]** を選択して変更を保存します。

    | プロパティ | 価値 |
    | --- | --- |
    | スコープ名 | *ToDoList.ReadWrite* |
    | 誰が同意できるか | **管理者のみ** |
    | 管理者の同意の表示名 | *'ToDoListApi' を使用した、ユーザーの ToDo リストの読み取りと書き込み* |
    | 管理者の同意の説明 | *'ToDoListApi' を使用して、アプリからユーザーの ToDo リストを読み書きできるようにする* |
    | 状態 | **有効** |

Web API の[アクセス許可を発行するときの最小限の特権の原則](https://learn.microsoft.com/ja-jp/security/zero-trust/develop/protected-api-example)について説明します。

#### アプリ ロールを構成する

API は、クライアント アプリがアクセス トークンをそれ自体として取得するために、アプリケーションに対して少なくとも 1 つのアプリ ロール ( [アプリケーションアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)とも呼ばれます) を発行する必要があります。 アプリケーションのアクセス許可は、クライアント アプリケーションが自身として正常に認証できるようにして、ユーザーをサインインさせる必要がないようにする場合に、API が発行するアクセス許可の種類です。 アプリケーションのアクセス許可を発行するには、次の手順に従います。

1. **[アプリの登録]** ページから、作成したアプリケーション (*ciam-ToDoList-api* など) を選択して、その **[概要]** ページを開きます。
2. **[管理]** で、**[アプリ ロール]** を選択します。
3. **[アプリ ロールの作成]** を選択し、次の値を入力し、**[適用]** を選択して変更を保存します:

    | プロパティ | 価値 |
    | --- | --- |
    | 表示名 | *ToDoList.Read.All* |
    | Allowed member types (許可されるメンバーの種類) | **アプリケーション** |
    | 価値 | *ToDoList.Read.All* |
    | 説明 | *'ToDoListApi' を使用して、アプリがすべてのユーザーの ToDo リストを読むことができるようにする* |
    | このアプリ ロールを有効にしますか? | オンのままにする |
4. もう一度 **[アプリ ロールの作成]** を選択し、2 番目のアプリ ロールに次の値を入力し、**[適用]** を選択して変更を保存します:

    | プロパティ | 価値 |
    | --- | --- |
    | 表示名 | *ToDoList.ReadWrite.All* |
    | Allowed member types (許可されるメンバーの種類) | **アプリケーション** |
    | 価値 | *ToDoList.ReadWrite.All* |
    | 説明 | *'ToDoListApi' を使用して、アプリがすべてのユーザーの ToDo リストを読み書きできるようにする* |
    | このアプリ ロールを有効にしますか? | オンのままにする |

#### オプションクレームの設定

**idtyp** の省略可能な要求を追加すると、Web API がトークンが **アプリ** トークンなのか **アプリ + ユーザー** トークンなのかを判断するのに役立ちます。 **scp** と**ロール**要求の組み合わせを同じ目的で使用できますが、**idtyp** 要求を使用すると、アプリ トークンとアプリ + ユーザー トークンを区別する最も簡単な方法です。 たとえば、トークンがアプリ専用トークンの場合、この要求の値は *app* です。

### iOS サンプル アプリに API アクセス許可を付与する

クライアント アプリと Web API の両方を登録し、スコープを作成して API を公開したら、次の手順に従って、API に対するクライアントのアクセス許可を構成できます。

1. [**アプリの登録**] ページで、作成したアプリケーション (ciam-client-app など) を選択して、**の [概要]** ページを開きます。
2. [**管理**] で **API 許可**を選択します。
3. **[構成されたアクセス許可]** の下で **[アクセス許可の追加]** を選択します。
4. **[所属する組織で使用している API]** タブを選択します。
5. API の一覧で、API (*ciam-ToDoList-api* など) を選択します。
6. **[委任されたアクセス許可]** オプションを選択します。
7. アクセス許可の一覧で **[ToDoList.Read, ToDoList.ReadWrite]** を選択します (必要に応じて検索ボックスを使用します)。
8. **[アクセス許可の追加]** ボタンを選択します。
9. この時点で、アクセス許可が正しく割り当てられました。 ただし、このテナントは顧客のテナントであるため、コンシューマー ユーザー自身がこれらのアクセス許可に同意することはできません。 これに対処するには、管理者がテナント内のすべてのユーザーに代わってこれらのアクセス許可に同意する必要があります。

    1. **[&lt;テナント名&gt; に管理者の同意を与えます]** を選択してから、**[はい]** を選択します。
    2. **[最新の情報に更新]** を選択し、両方のアクセス許可の &lt; に "**&gt;テナント名 に付与されました**" と表示されていることを確認します。
10. **[Configured permissions] (構成されたアクセス許可)** の一覧で**ToDoList.Read** と **ToDoList.ReadWrite** のアクセス許可を一度に 1 つずつ選択し、後で使用するためにアクセス許可の完全な URI をコピーします。 完全なアクセス許可 URI は、`api://{clientId}/{ToDoList.Read}` または `api://{clientId}/{ToDoList.ReadWrite}` のようになります。

### サンプル Web API を複製またはダウンロードする

サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial.git
    ```
- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

### サンプル Web API の構成と実行

1. コード エディターで、`2-Authorization/1-call-own-api-aspnet-core-mvc/ToDoListAPI/appsettings.json` ファイルを開きます。
2. プレースホルダーを見つけてください。

    - `Enter_the_Application_Id_Here` を選択し、先ほどコピーした Web API の **アプリケーション (クライアント) ID** に置き換えます。
    - `Enter_the_Tenant_Id_Here` を選択し、先ほどコピーした **ディレクトリ (テナント) ID** に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。

それを呼び出すには、iOS サンプル アプリ用の Web API をホストする必要があります。 [クイック スタート: ASP.NET Web アプリをデプロイして Web](https://learn.microsoft.com/ja-jp/azure/app-service/quickstart-dotnetcore) API をデプロイする方法に従います。

### Web API を呼び出すサンプル iOS モバイル アプリを構成する

このサンプルでは、複数の Web API URL エンドポイントとスコープ のセットを構成できます。 この場合は、1 つの Web API URL エンドポイントとそれに関連付けられているスコープのみを構成します。

1. Xcode で、 `/NativeAuthSampleApp/ProtectedAPIViewController.swift` ファイルを開きます。 macOS を使用している場合は、 [ProtectedAPIViewController.swift](https://github.com/Azure-Samples/ms-identity-ciam-native-auth-ios-sample/blob/main/NativeAuthSampleApp/ProtectedAPIViewController.swift) コード ファイルのサンプルを次に示します。
2. `protectedAPIUrl1`を見つけて、その値として Web API URL を入力します。

    ```swift
    let protectedAPIUrl1: String? = nil // Developers should set the respective URL of their web API here. For example let protectedAPIUrl1: String? = "https://api.example.com/v1/resource"
    ```
3. `protectedAPIScopes1`を検索し、「API のアクセス許可を iOS サンプル アプリに付与する」に記録されているスコープを設定します。

    ```swift
    let protectedAPIScopes1: [String] = [] // Developers should set the respective scopes of their web API here.For example, let protectedAPIScopes = ["api://{clientId}/{ToDoList.Read}","api://{clientId}/{ToDoList.ReadWrite}"]
    ```

### iOS サンプル アプリを実行して Web API を呼び出す

アプリをビルドして実行するには、次の手順に従います。

1. コードをビルドして実行するには、Xcode の **[製品**] メニューから [**実行**] を選択します。 ビルドが成功すると、Xcode はシミュレーターでサンプル アプリを起動します。
2. [API] タブを選択して API 呼び出しをテストします。 Web API の呼び出しが成功すると HTTP `200`が返され、HTTP `403` は未承認のアクセスを示します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-native-authentication-ios-sign-in"} -->
## ネイティブ認証を使用してサンプル iOS (Swift) アプリでユーザーをサインインする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-ios-sign-in
- Service: identity-platform / external
- Article date: 2025-11-17
- Summary: Microsoft Entra External ID を使用して、iOS (Swift) サンプル アプリを構成して、サインアップ、サインイン、サインアウト、およびリセットのパスワード シナリオを構成する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このクイック スタートでは、Microsoft Entra External ID を使用して、サインアップ、サインイン、サインアウト、パスワードリセットのシナリオを示す iOS サンプル アプリケーションを実行する方法について説明します。

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 次の Microsoft Entra ロールには、必要なアクセス許可が含まれます。

    - アプリケーション管理者
    - アプリケーション開発者
- 外部テナント。 お持ちでない場合は、Microsoft Entra 管理センターで [新しい外部テナントを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal) します。
- まだ登録していない場合は、[Microsoft Entra 管理センターでアプリケーションを登録します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。 次のことを確認します。

    - 後で使用するために、 **アプリケーション (クライアント) ID** と **ディレクトリ (テナント) ID を** 記録します。
    - アプリケーション[に管理者の同意を付与](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#grant-admin-consent-external-tenants-only)します。
- まだ作成していない場合は、[Microsoft Entra 管理センターでユーザー フローを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)します
- [アプリの登録をユーザー フローに関連付ける](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)
- [Xcode](https://developer.apple.com/xcode/resources/)

### パブリック クライアントとネイティブ認証フローを有効にする

このアプリがパブリック クライアントであり、ネイティブ認証を使用できることを指定するには、パブリック クライアントとネイティブ認証フローを有効にします。

1. アプリ登録ページから、パブリック クライアントとネイティブ認証フローを有効にするアプリ登録を選択します。
2. **[管理]** で、 **[認証]** を選択します。
3. [詳細設定 で、パブリック クライアント フローを許可します。
    1. **[次のモバイルとデスクトップのフローを有効にする]** で、**[はい]** を選びます。
    2. ネイティブ認証 **を有効にする**には、[はい] を選択します。
4. **[保存]** ボタンを選びます。

### サンプルの iOS モバイル アプリケーションを複製する

1. ターミナルを開き、コードを保持するディレクトリに移動します。
2. 次のコマンドを実行して、GitHub から iOS モバイル アプリケーションを複製します。

    ```bash
    git clone https://github.com/Azure-Samples/ms-identity-ciam-native-auth-ios-sample.git
    ```
3. リポジトリが複製されたディレクトリに移動します。

    ```bash
    cd ms-identity-ciam-native-auth-ios-sample
    ```

### サンプル iOS モバイル アプリケーションを構成する

1. Xcode で *NativeAuthSampleApp.xcodeproj プロジェクトを* 開きます。
2. *NativeAuthSampleApp/Configuration.swift ファイルを*開きます。
3. プレースホルダーを見つけてください。

    - `Enter_the_Application_Id_Here` を、前に登録したアプリの**アプリケーション (クライアント) ID** に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com`されている場合は、contoso を使用します。 テナント サブドメインがない場合は、テナントの詳細を 読み取る方法について説明します。

注

ビルドするスキームとビルド先の製品を実行する場所を必ず選択してください。 各スキームには、使用可能な宛先を表す実際のデバイスまたはシミュレートされたデバイスの一覧が含まれています。

### サンプル iOS モバイル アプリケーションの実行とテスト

コードをビルドして実行するには、Xcode の **[製品**] メニューから [**実行**] を選択します。 ビルドが成功すると、Xcode はシミュレーターでサンプル アプリを起動します。

[Image: iOS アプリで電子メールを入力するためのユーザー プロンプトのスクリーンショット。]

このガイドでは **、電子メールワンタイム パスコードの使用状況を** テストします。 有効なメール アドレスを入力し、[ **サインアップ**] を選択して、送信コード画面を起動します。

[Image: iOS アプリでワンタイム パスコード (OTP) を入力するためのユーザー プロンプトのスクリーンショット。]

前の画面でメール アドレスを入力すると、アプリケーションから確認コードが送信されます。 受信したコードを送信すると、アプリケーションによって前の画面に戻り、自動的にサインインします。

### このサンプルでサポートされるその他のシナリオ

サンプル アプリでは、次のフローがサポートされています。

- **電子メール + パスワード** は、パスワードを含む電子メールによるサインインまたはサインアップ フローを対象とします。
- **ユーザー属性を使用した電子メールとパスワードのサインアップ** では、電子メールとパスワードによるサインアップと、ユーザー属性の送信について説明します。
- **パスワード リセット** には、セルフサービス パスワード リセット (SSPR) が含まれます。
- **Access Protected API** では、ユーザーが正常にサインアップまたはサインインしてアクセス トークンを取得した後に、保護された API を呼び出す方法について説明します。
- **Web ブラウザーへのフォールバック** では、ユーザーが何らかの理由でネイティブ認証を使用して認証を完了できない場合に、ブラウザー ベースの認証をフォールバック メカニズムとして使用する方法について説明します。

### パスワード フローを使用して電子メールをテストする

このセクションでは、パスワードを使用した電子メールのフロー、およびそのバリエーション（ユーザー属性とSSPRを使用したパスワードサインアップ）をテストします。

1. ユーザー フローを作成して新しい [ユーザー フローを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) する手順を使用しますが、今回は認証方法として **[パスワード付きの電子メール]** を選択します。 ユーザー属性として **国/地域** と **市区町村** を構成する必要があります。 または、既存のユーザー フローを変更して、**パスワードで電子メール**を使用することもできます (**外部 ID の**選択&gt;**ユーザー フロー**&gt;**SignInSignUpSample**&gt;**Identity プロバイダー**&gt;**Email with password**&gt;**Save**)。
2. [新しいユーザー フローにアプリを追加するには、アプリケーションを新しいユーザー フローに関連付ける](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)手順を使用します。
3. サンプル アプリを実行し、省略記号メニュー (**...**) を選択して、その他のオプションを開きます。
4. テストするシナリオ ( **[Email + password]\(電子メール + パスワード** \) や [ **Email + password sign-up with user attributes]\(ユーザー属性を使用した電子メール + パスワード + パスワードのサインアップ** \) や **[パスワードのリセット**]など) を選択し、プロンプトに従います。 **パスワードリセット**をテストするには、まずユーザーをサインアップし、テナント内のすべてのユーザーに対して[電子メールワンタイムパスコードを有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers)必要があります。

### 保護された API フローのテスト呼び出し

ネイティブ認証を使用して [サンプルの Android モバイル アプリから保護された Web API を呼び出すことで、サンプル iOS モバイル アプリで](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-ios-call-api) 保護された Web API を呼び出す方法に関するページの手順を使用します。

### エイリアスまたはユーザー名を使用してサインインを有効にする

メール アドレスとパスワードを使用してサインインするユーザーも、ユーザー名とパスワードでサインインできます。 代替サインイン識別子とも呼ばれるユーザー名には、顧客 ID、アカウント番号、またはユーザー名として使用する別の識別子を指定できます。

Microsoft Entra 管理センターを使用してユーザー アカウントにユーザー名を手動で割り当てたり、Microsoft Graph API を使用してアプリで自動化したりできます。

[「エイリアスまたはユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias)を使用してサインインする」の記事の手順を使用して、ユーザーがアプリケーションでユーザー名を使用してサインインできるようにします。

1. [サインインでユーザー名を有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#enable-username-in-sign-in-identifier-policy)。
2. [管理センターでユーザー名を持つユーザーを作成するか、ユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username) を [追加して既存のユーザーを更新します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)。 または、 [Microsoft Graph API を使用して、アプリでのユーザーの作成と更新を自動化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-native-authentication-macos-sign-in"} -->
## ネイティブ認証を使用してサンプル macOS (Swift) アプリでユーザーをサインインする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-macos-sign-in
- Service: identity-platform / external
- Article date: 2025-11-17
- Summary: Microsoft Entra External ID を使用してサインアップしてサインインするように macOS (Swift) サンプル アプリを構成する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このガイドでは、Microsoft Entra External ID を使用したサインアップとサインインのシナリオを示す macOS サンプル アプリケーションを実行する方法について説明します。

この記事では、次の方法について説明します。

- パブリック クライアントとネイティブ認証フローを有効にします。
- 独自の外部テナントの詳細を使用するように、サンプルのネイティブ macOS アプリケーションを更新します。
- サンプルのネイティブ macOS アプリケーションを実行してテストします。

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 次の Microsoft Entra ロールには、必要なアクセス許可が含まれます。

    - アプリケーション管理者
    - アプリケーション開発者
- 外部テナント。 お持ちでない場合は、Microsoft Entra 管理センターで [新しい外部テナントを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal) します。
- まだ登録していない場合は、[Microsoft Entra 管理センターでアプリケーションを登録します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。 次のことを確認します。

    - 後で使用するために、 **アプリケーション (クライアント) ID** と **ディレクトリ (テナント) ID を** 記録します。
    - アプリケーション[に管理者の同意を付与](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#grant-admin-consent-external-tenants-only)します。
- まだ作成していない場合は、[Microsoft Entra 管理センターでユーザー フローを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)します
- [アプリの登録をユーザー フローに関連付ける](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)
- [Xcode](https://developer.apple.com/xcode/resources/)

### パブリック クライアントとネイティブ認証フローを有効にする

このアプリがパブリック クライアントであり、ネイティブ認証を使用できることを指定するには、パブリック クライアントとネイティブ認証フローを有効にします。

1. アプリ登録ページから、パブリック クライアントとネイティブ認証フローを有効にするアプリ登録を選択します。
2. **[管理]** で、 **[認証]** を選択します。
3. [詳細設定 で、パブリック クライアント フローを許可します。
    1. **[次のモバイルとデスクトップのフローを有効にする]** で、**[はい]** を選びます。
    2. ネイティブ認証 **を有効にする**には、[はい] を選択します。
4. **[保存]** ボタンを選びます。

### サンプル macOS アプリケーションを複製する

1. ターミナルを開き、コードを保持するディレクトリに移動します。
2. 次のコマンドを実行して、GitHub から macOS アプリケーションを複製します。

    ```bash
    git clone https://github.com/Azure-Samples/ms-identity-ciam-native-auth-macos-sample.git
    ```
3. リポジトリが複製されたディレクトリに移動します。

    ```bash
    cd ms-identity-ciam-native-auth-macos-sample
    ```

### サンプル macOS アプリケーションを構成する

1. Xcode で *NativeAuthSampleAppMacOS.xcodeproj プロジェクトを* 開きます。
2. *NativeAuthSampleAppMacOS/Configuration.swift* ファイルを開きます。
3. プレースホルダーを見つけてください。

    - `Enter_the_Application_Id_Here` を、前に登録したアプリの**アプリケーション (クライアント) ID** に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com`されている場合は、contoso を使用します。 テナント サブドメインがない場合は、テナントの詳細を 読み取る方法について説明します。

注

ビルドするスキームとビルド先の製品を実行する場所を必ず選択してください。 各スキームには、使用可能な宛先を表す実際のデバイスまたはシミュレートされたデバイスの一覧が含まれています。

### サンプル macOS アプリケーションの実行とテスト

コードをビルドして実行するには、Xcode の **[製品**] メニューから [**実行**] を選択します。 ビルドが成功すると、Xcode はシミュレーターでサンプル アプリを起動します。

[Image: macOS アプリで電子メールとパスワードを入力するためのユーザー プロンプトのスクリーンショット。]

このガイドでは **、電子メールとパスワードの使用状況を** テストします。 有効なメール アドレスとパスワードを入力し、[ **サインアップ**] を選択して、送信コード画面を起動します。

[Image: macOS アプリでワンタイム パスコード (OTP) を入力するためのユーザー プロンプトのスクリーンショット。]

前の画面でメール アドレスを入力すると、アプリケーションから確認コードが送信されます。 受信したコードを送信すると、アプリケーションによって前の画面に戻り、自動的にサインインします。

### エイリアスまたはユーザー名を使用してサインインを有効にする

メール アドレスとパスワードを使用してサインインするユーザーも、ユーザー名とパスワードでサインインできます。 代替サインイン識別子とも呼ばれるユーザー名には、顧客 ID、アカウント番号、またはユーザー名として使用する別の識別子を指定できます。

Microsoft Entra 管理センターを使用してユーザー アカウントにユーザー名を手動で割り当てたり、Microsoft Graph API を使用してアプリで自動化したりできます。

[「エイリアスまたはユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias)を使用してサインインする」の記事の手順を使用して、ユーザーがアプリケーションでユーザー名を使用してサインインできるようにします。

1. [サインインでユーザー名を有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#enable-username-in-sign-in-identifier-policy)。
2. [管理センターでユーザー名を持つユーザーを作成するか、ユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username) を [追加して既存のユーザーを更新します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)。 または、 [Microsoft Graph API を使用して、アプリでのユーザーの作成と更新を自動化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-native-authentication-single-page-app-react-sign-in"} -->
## ネイティブ認証を使用して React シングルページ アプリ (SPA) でユーザーをサインインする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-single-page-app-react-sign-in
- Service: identity-platform / external
- Article date: 2025-11-17
- Summary: ネイティブ認証 API を使用してユーザーをサインアップするサンプル React シングルページ アプリ (SPA) を構成する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このクイックスタートでは、React シングルページアプリケーション (SPA) を使用して、[ユーザーを認証するためにネイティブ認証 API](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api)を使用する方法を示します。 サンプル アプリでは、ユーザーのサインアップ、サインイン、サインアウト、パスワードのリセットを電子メールとパスワードで示します。

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 アカウントをまだお持ちでない場合は、[無料](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)アカウントを作成します。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 以下のいずれの Microsoft Entra ロールにも、必要なアクセス許可が含まれています。
    - アプリケーション管理者
    - アプリケーション開発者
- 外部テナント。 お持ちでない場合は、Microsoft Entra 管理センターで [新しい外部テナントを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal) します。
- ユーザーの流れ。 詳細については、「 [外部テナントのアプリのセルフサービス サインアップ ユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)」を参照してください。 ユーザー フローに次のユーザー属性が含まれていることを確認します。
    - **指定の名前**
    - **姓**
- まだ登録していない場合は、[Microsoft Entra 管理センターでアプリケーションを登録します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。 次のことを確認してください。
    - 後で使用するために、 **アプリケーション (クライアント) ID** と **ディレクトリ (テナント) ID を** 記録します。
    - アプリの登録に[管理者の同意を与えます](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#grant-admin-consent-external-tenants-only)。
- [アプリの登録をユーザー フローに関連付ける](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)
- [Node.js](https://nodejs.org/en/download/)。
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコードエディター。

### パブリック クライアントとネイティブ認証フローを有効にする

このアプリがパブリック クライアントであり、ネイティブ認証を使用できることを指定するには、パブリック クライアントとネイティブ認証フローを有効にします。

1. アプリ登録ページから、パブリック クライアントとネイティブ認証フローを有効にするアプリ登録を選択します。
2. **[管理]** で、 **[認証]** を選択します。
3. [詳細設定 で、パブリック クライアント フローを許可します。
    1. **[次のモバイルとデスクトップのフローを有効にする]** で、**[はい]** を選びます。
    2. ネイティブ認証 **を有効にする**には、[はい] を選択します。
4. **[保存]** ボタンを選びます。

### サンプル SPA を複製またはダウンロードする

サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

```console
git clone https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples.git
```

または、[サンプル](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/archive/refs/heads/main.zip)をダウンロードし、名前の長さが 260 文字未満のファイル パスに抽出します。

### プロジェクトの依存関係をインストールする

1. ターミナル ウィンドウを開き、React サンプル アプリが含まれているディレクトリに移動します。

    ```console
    cd API\React\ReactAuthSimple
    ```
2. 次のコマンドを実行して、アプリの依存関係をインストールします。

    ```console
    npm install
    ```

### サンプル React アプリを構成する

1. コード エディターで、src\config.ts *ファイル* 開きます。
2. プレースホルダー `Enter_the_Application_Id_Here` 見つけて、先ほど登録したアプリのアプリケーション (クライアント) ID に置き換えます。
3. 変更を保存します。

### CORS プロキシ サーバーを構成する

ネイティブ認証 API では、クロスオリジン リソース共有 (CORS)  サポートされていないため、SPA アプリと API の間にプロキシ サーバーを設定する必要があります。

このコード サンプルには、ネイティブ認証 API URL エンドポイントに要求を転送する CORS プロキシ サーバーが含まれています。 CORS プロキシ サーバーは、ポート 3001 でリッスンする Node.js サーバーです。

プロキシ サーバーを構成するには、*proxy.config.js* ファイルを開き、プレースホルダーを見つけます。

- `tenantSubdomain` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント サブドメインがない場合は、テナントの詳細を 読み取る方法について説明します。
- `tenantId` をディレクトリ (テナント) ID に置き換えます。 テナント ID がわからない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください。

### アプリを実行してテストする

これで、サンプル アプリを構成し、実行する準備ができました。

1. ターミナル ウィンドウから、次のコマンドを実行して CORS プロキシ サーバーを起動します。

    ```console
    cd API\React\ReactAuthSimple
    npm run cors
    ```
2. React アプリを起動するには、別のターミナル ウィンドウを開き、次のコマンドを実行します。

    ```console
    cd API\React\ReactAuthSimple
    npm start
    ```
3. Web ブラウザーを開き、`http://localhost:3000/`に移動します。
4. アカウントにサインアップするには、**サインアップ**を選択し、プロンプトに従います。
5. サインアップした後、**サインイン** と **パスワードをリセット** をそれぞれ選択して、サインインとパスワードリセットをテストします。

### エイリアスまたはユーザー名を使用してサインインを有効にする

メール アドレスとパスワードを使用してサインインするユーザーも、ユーザー名とパスワードでサインインできます。 代替サインイン識別子とも呼ばれるユーザー名には、顧客 ID、アカウント番号、またはユーザー名として使用する別の識別子を指定できます。

Microsoft Entra 管理センターを使用してユーザー アカウントにユーザー名を手動で割り当てたり、Microsoft Graph API を使用してアプリで自動化したりできます。

[「エイリアスまたはユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias)を使用してサインインする」の記事の手順を使用して、ユーザーがアプリケーションでユーザー名を使用してサインインできるようにします。

1. [サインインでユーザー名を有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#enable-username-in-sign-in-identifier-policy)。
2. [管理センターでユーザー名を持つユーザーを作成するか、ユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username) を [追加して既存のユーザーを更新します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)。 または、 [Microsoft Graph API を使用して、アプリでのユーザーの作成と更新を自動化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-native-authentication-single-page-app-sdk-sign-in"} -->
## ネイティブ認証 SDK を使用してシングルページ アプリでユーザーをサインインする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-single-page-app-sdk-sign-in
- Service: identity-platform / external
- Article date: 2025-11-17
- Summary: ネイティブ認証 SDK を使用して React シングルページ アプリを構成して、ユーザーがサインアップ、サインイン、サインアウト、パスワードのリセットを行うことができるようにします。 このクイックスタートを始めてください。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このクイック スタートでは、シングルページ アプリケーション (SPA) を使用して、ネイティブ認証 SDK を使用してユーザーを認証する方法を示します。 サンプル アプリでは、パスワードを使用した電子メールと電子メールのワンタイム パスコード認証フローの両方に対して、ユーザーのサインアップ、サインイン、サインアウトを示します。

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 以下のいずれの Microsoft Entra ロールにも、必要なアクセス許可が含まれています。
    - アプリケーション管理者
    - アプリケーション開発者
- 外部テナント。 お持ちでない場合は、Microsoft Entra 管理センターで [新しい外部テナントを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal) します。
- ユーザーの流れ。 詳細については、「 [外部テナントのアプリのセルフサービス サインアップ ユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)」を参照してください。 **[ID プロバイダー**] で、優先する認証方法 (**パスワードを使用した電子メール**)、または**電子メールワンタイム パスコード**を選択します。 このコード サンプルでは、サンプル アプリがユーザーからそれらを収集する際に、ユーザー フローで次のユーザー属性を使用します。
    - **指定の名前**
    - **姓**
    - **役職**
    - **国/地域設定**
- まだ登録していない場合は、[Microsoft Entra 管理センターでアプリケーションを登録します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。 次のことを確認してください。
    - 後で使用するために、 **アプリケーション (クライアント) ID** と **ディレクトリ (テナント) ID を** 記録します。
    - アプリの登録に[管理者の同意を与えます](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#grant-admin-consent-external-tenants-only)。
- [アプリの登録をユーザー フローに関連付ける](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)
- [Node.js](https://nodejs.org/en/download/)。
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコード エディター。

### パブリック クライアントとネイティブ認証フローを有効にする

このアプリがパブリック クライアントであり、ネイティブ認証を使用できることを指定するには、パブリック クライアントとネイティブ認証フローを有効にします。

1. アプリ登録ページから、パブリック クライアントとネイティブ認証フローを有効にするアプリ登録を選択します。
2. **[管理]** で、 **[認証]** を選択します。
3. [詳細設定 で、パブリック クライアント フローを許可します。
    1. **[次のモバイルとデスクトップ フローを有効にする]** で、**[はい]** を選択します。
    2. ネイティブ認証 **を有効にする**には、[はい] を選択します。
4. [ **保存] ボタンを** 選択します。

### サンプル SPA を複製またはダウンロードする

サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples.git
    ```
- [サンプルをダウンロードします](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

### プロジェクトの依存関係をインストールする

## [React](#tab/react)
1. ターミナル ウィンドウを開き、React サンプル アプリが含まれているディレクトリに移動します。

    ```console
        cd typescript/native-auth/react-nextjs-sample
    ```
2. 次のコマンドを実行して、アプリの依存関係をインストールします。

    ```console
    npm install
    ```

## [角度](#tab/angular)
1. ターミナル ウィンドウを開き、React サンプル アプリが含まれているディレクトリに移動します。

    ```console
        cd typescript/native-auth/angular-sample
    ```
2. 次のコマンドを実行して、アプリの依存関係をインストールします。

    ```console
    npm install
    ```

---

### サンプル React アプリを構成する

## [React](#tab/react)
1. *src/config/auth-config.ts* を開き、次のプレースホルダーを Microsoft Entra 管理センターから取得した値に置き換えます。

    - プレースホルダー `Enter_the_Application_Id_Here` 見つけて、先ほど登録したアプリのアプリケーション (クライアント) ID に置き換えます。
    - プレースホルダー `Enter_the_Tenant_Subdomain_Here` 見つけて、Microsoft Entra 管理センターのテナント サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。
2. 変更を保存します。

## [角度](#tab/angular)
1. *src/app/config/auth-config.ts* を開き、次のプレースホルダーを Microsoft Entra 管理センターから取得した値に置き換えます。

    - プレースホルダー `Enter_the_Application_Id_Here` 見つけて、先ほど登録したアプリのアプリケーション (クライアント) ID に置き換えます。
    - プレースホルダー `Enter_the_Tenant_Subdomain_Here` 見つけて、Microsoft Entra 管理センターのテナント サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
2. 変更を保存します。

---

### CORS プロキシ サーバーを構成する

ネイティブ認証 API は [クロスオリジン リソース共有 (CORS)](https://developer.mozilla.org/docs/Web/HTTP/CORS) をサポートしていないため、SPA アプリと API の間にプロキシ サーバーを設定する必要があります。

このコード サンプルには、ネイティブ認証 API URL エンドポイントに要求を転送する CORS プロキシ サーバーが含まれています。 CORS プロキシ サーバーは、ポート 3001 でリッスンする Node.js サーバーです。

プロキシ サーバーを構成するには、*proxy.config.js* ファイルを開き、プレースホルダーを見つけます。

- `tenantSubdomain` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント サブドメインがない場合は、テナントの詳細を 読み取る方法について説明します。
- `tenantId` をディレクトリ (テナント) ID に置き換えます。 テナント ID がわからない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください。

### アプリを実行してテストする

これで、サンプル アプリを構成し、実行する準備ができました。

## [React](#tab/react)
1. ターミナル ウィンドウから、次のコマンドを実行して CORS プロキシ サーバーを起動します。

    ```console
    cd typescript/native-auth/react-nextjs-sample/
    npm run cors
    ```
2. React アプリを起動するには、別のターミナル ウィンドウを開き、次のコマンドを実行します。

    ```console
    cd typescript/native-auth/react-nextjs-sample/
    npm run dev
    ```
3. Web ブラウザーを開き、`http://localhost:3000/`に移動します。
4. アカウントにサインアップするには、**サインアップ**を選択し、プロンプトに従います。
5. サインアップ後、[サインイン] ボタンと [パスワードのリセット] ボタンをそれぞれ選択して、サインインとパスワードリセットをテストします。

## [角度](#tab/angular)
1. ターミナル ウィンドウから、次のコマンドを実行して CORS プロキシ サーバーを起動します。

    ```console
    cd typescript/native-auth/angular-sample/
    npm run cors
    ```
2. React アプリを起動するには、別のターミナル ウィンドウを開き、次のコマンドを実行します。

    ```console
    cd typescript/native-auth/angular-sample/
    npm run start
    ```
3. Web ブラウザーを開き、`http://localhost:4200`に移動します。
4. アカウントにサインアップするには、**サインアップ**を選択し、プロンプトに従います。
5. サインアップ後、[サインイン] および [パスワードのリセット] ボタンを選択して、それぞれ **サインイン** と **パスワードのリセット** をテストします。

---

### エイリアスまたはユーザー名を使用してサインインを有効にする

メール アドレスとパスワードを使用してサインインするユーザーも、ユーザー名とパスワードでサインインできます。 代替サインイン識別子とも呼ばれるユーザー名には、顧客 ID、アカウント番号、またはユーザー名として使用する別の識別子を指定できます。

Microsoft Entra 管理センターを使用してユーザー アカウントにユーザー名を手動で割り当てたり、Microsoft Graph API を使用してアプリで自動化したりできます。

[「エイリアスまたはユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias)を使用してサインインする」の記事の手順を使用して、ユーザーがアプリケーションでユーザー名を使用してサインインできるようにします。

1. [サインインでユーザー名を有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#enable-username-in-sign-in-identifier-policy)。
2. [管理センターでユーザー名を持つユーザーを作成するか、ユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username) を [追加して既存のユーザーを更新します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)。 または、 [Microsoft Graph API を使用して、アプリでのユーザーの作成と更新を自動化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-register-app"} -->
## Microsoft Entra IDにアプリを登録する方法 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app
- Service: identity-platform
- Article date: 2026-05-14
- Summary: Microsoft Entra ID でアプリを登録し、シングルテナントまたはマルチテナントで使用できるように構成する方法について説明します。

このハウツー ガイドでは、Microsoft Entra ID でアプリケーションを登録する方法について説明します。 このプロセスは、アプリケーションと Microsoft ID プラットフォームの間に信頼関係を確立するために不可欠です。 このクイックスタートを完了すると、アプリの ID とアクセス管理 (IAM) を有効にして、Microsoft のサービスと API と安全に対話できるようになります。

### 前提条件

- アクティブなサブスクリプションが含まれる Azure アカウント。 [アカウントを無料で作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- Azure アカウントは、少なくとも [Application Developer](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer) である必要があります。
- 従業員または外部テナント。 このクイック スタートでは **、既定のディレクトリ** を使用できます。 外部テナントが必要な場合は、 [外部テナントの設定を完了します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/quickstart-tenant-setup)。

### アプリケーションを登録する

Microsoft Entra にアプリケーションを登録すると、アプリと Microsoft ID プラットフォームの間に信頼関係が確立されます。 信頼は一方向です。 アプリは Microsoft ID プラットフォームを信頼しますが、他の方法では信頼しません。 一度作成すると、異なるテナント間でアプリケーション オブジェクトを移動することはできません。

アプリ登録を作成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション開発者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、アプリケーションを登録するテナントに切り替えます。
3. **Entra ID**&gt;**アプリの登録** に移動し、[**新規登録**] を選択します。
4. アプリのわかりやすい **名前** を入力します。たとえば、 *identity-client-app* です。 アプリ ユーザーはこの名前を表示でき、いつでも変更できます。 同じ名前で複数のアプリ登録を行うことができます。
5. [ **サポートされているアカウントの種類**] で、ドロップダウンを開き、アプリケーションを使用できるユーザーを選択します。 ほとんどのアプリケーションには、**シングル テナントのみ - &lt;ご利用のテナント&gt;**をお勧めします。 各オプションの詳細については、表を参照してください。

    | サポートされているアカウントの種類 | 説明 |
    | --- | --- |
    | **シングルテナントのみ - &lt;ご利用のテナント&gt;** | *テナント内の*ユーザー (またはゲスト) のみが使用するシングルテナント アプリの*場合*。 |
    | **複数の Entra ID テナント** | *マルチテナント* アプリの場合、*any* Microsoft Entra テナントのユーザーがアプリケーションを使用できるようにする場合。 複数の組織に提供する予定のサービスとしてのソフトウェア (SaaS) アプリケーションに最適です。 |
    | **任意の Entra ID テナント + 個人用 Microsoft アカウント** | 組織と個人の両方の Microsoft アカウント (Skype、Xbox、Live、Hotmail など) をサポートする *マルチテナント* アプリの場合。 |
    | **個人用アカウントのみ** | 個人のMicrosoft アカウントでのみ使用されるアプリの場合 (例: Xbox、Live、Hotmail)。 |
6. [ **登録** ] を選択してアプリの登録を完了します。
7. アプリケーションの **[概要]** ページが表示されます。 **アプリケーション (クライアント) ID を**記録します。この ID は、アプリケーションを一意に識別し、Microsoft ID プラットフォームから受け取るセキュリティ トークンの検証の一環としてアプリケーションのコードで使用されます。

重要

新しいアプリの登録は、既定ではユーザーに対して非表示になっています。 ユーザーが自分の [\[マイ アプリ\] ページ](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510) にアプリを表示する準備ができたら、それを有効にすることができます。 アプリを有効にするには、Microsoft Entra 管理センターで **Entra ID**&gt;**Enterprise apps** に移動し、アプリを選択します。 次に、[**プロパティ**] ページで [**ユーザーに表示**] を [**はい**] に設定します。

### 管理者の同意を付与する (外部テナントのみ)

アプリケーションを登録すると、 **User.Read** アクセス許可が割り当てられます。 ただし、外部テナントの場合、顧客ユーザーはアクセス許可自体に同意できません。 管理者は、テナント内のすべてのユーザーに代わって、このアクセス許可に同意する必要があります。

1. アプリ登録の **[概要**] ページの [**管理**] で **[API のアクセス許可**] を選択します。
2. [**テナント名&lt;&gt;管理者の同意を付与**する] を選択し、[**はい**] を選択します。
3. **[更新]** を選択し、その後、[**&lt;テナント名に付与されました&gt;**] が権限の **[状態]** に表示されていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-single-page-app-sign-in"} -->
## クイック スタート - シングルページ アプリ (SPA) でユーザーをサインインさせ、Microsoft Graph API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-sign-in
- Service: identity-platform
- Article date: 2025-01-27
- Summary: Microsoft ID プラットフォームを使用して従業員または顧客をサインインさせるサンプル SPA を構成する方法を示すクイック スタート

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このクイック スタートでは、サンプルシングルページ アプリ (SPA) を使用して、Proof Key for Code Exchange (PKCE) で [承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) を使用してユーザーをサインインさせ、Microsoft Graph API を呼び出す方法を示します。 このサンプルでは、 [Microsoft 認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) を使用して認証を処理します。

::: zone pivot="workforce"

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 以下のいずれの Microsoft Entra ロールにも、必要なアクセス許可が含まれています。
    - アプリケーション管理者
    - アプリケーション開発者
- 従業員テナント。 既定のディレクトリを使用するか、 [新しいテナントを設定](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)できます。
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコード エディター。

## [JavaScript](#tab/javascript-workforce)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **シングルページ アプリケーション** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:3000/`
- [Node.js](https://nodejs.org/en/download/)

## [反応する](#tab/react-workforce)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **シングルページ アプリケーション** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:3000/`
- [Node.js](https://nodejs.org/en/download/)

## [角度](#tab/angular-workforce)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **シングルページ アプリケーション** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:4200/`
- [Node.js](https://nodejs.org/en/download/)

## [Blazor](#tab/blazor-workforce)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **シングルページ アプリケーション** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:5000/authentication/login-callback.`
- [.NET SDK](https://dotnet.microsoft.com/download/dotnet)

---

### サンプル アプリケーションを複製またはダウンロードする

サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

## [JavaScript](#tab/javascript-workforce)
- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-docs-code-javascript.git
    ```
- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-docs-code-javascript/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

## [反応する](#tab/react-workforce)
- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-docs-code-javascript.git
    ```
- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-docs-code-javascript/tree/main)。 名前の長さが 260 文字未満のファイル パスに抽出します。

## [角度](#tab/angular-workforce)
- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-docs-code-javascript.git
    ```
- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-docs-code-javascript/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

## [Blazor](#tab/blazor-workforce)
- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-docs-code-dotnet.git
    ```
- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-docs-code-dotnet/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

---

### プロジェクトを構成する

## [JavaScript](#tab/javascript-workforce)
1. IDE で、サンプルを含むプロジェクト フォルダー *ms-identity-docs-code-javascript* を開きます。
2. *vanillajs-spa/App/public/authConfig.js* を開き、管理センターに記録された情報で次の値を更新します。

    ```JavaScript
    /**
     * Configuration object to be passed to MSAL instance on creation. 
     * For a full list of MSAL.js configuration parameters, visit:
     * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/configuration.md 
     */
    const msalConfig = {
        auth: {
             clientId: "Enter_the_Application_Id_Here",
             // WORKFORCE TENANT
             authority: "https://login.microsoftonline.com/Enter_the_Tenant_Info_Here", //  Replace the placeholder with your tenant info
             // EXTERNAL TENANT
             // authority: "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/", // Replace the placeholder with your tenant subdomain
            redirectUri: '/', // You must register this URI on App Registration. Defaults to window.location.href e.g. http://localhost:3000/
            navigateToLoginRequestUrl: true, // If "true", will navigate back to the original request location before processing the auth code response.
        },
        cache: {
            cacheLocation: 'sessionStorage', // Configures cache location. "sessionStorage" is more secure, but "localStorage" gives you SSO.
            storeAuthStateInCookie: false, // set this to true if you have to support IE
        },
        system: {
            loggerOptions: {
                loggerCallback: (level, message, containsPii) => {
                    if (containsPii) {
                        return;
                    }
                    switch (level) {
                        case msal.LogLevel.Error:
                            console.error(message);
                            return;
                        case msal.LogLevel.Info:
                            console.info(message);
                            return;
                        case msal.LogLevel.Verbose:
                            console.debug(message);
                            return;
                        case msal.LogLevel.Warning:
                            console.warn(message);
                            return;
                    }
                },
            },
        },
    };
    
    /**
     * Scopes you add here will be prompted for user consent during sign-in.
     * By default, MSAL.js will add OIDC scopes (openid, profile, email) to any login request.
     * For more information about OIDC scopes, visit: 
     * https://learn.microsoft.com/en-us/entra/identity-platform/permissions-consent-overview#openid-connect-scopes
     */
    const loginRequest = {
        scopes: ["User.Read"],
    };
    
    /**
     * An optional silentRequest object can be used to achieve silent SSO
     * between applications by providing a "login_hint" property.
     */
    
    // const silentRequest = {
    //   scopes: ["openid", "profile"],
    //   loginHint: "example@domain.net"
    // };
    
    // exporting config object for jest
    if (typeof exports !== 'undefined') {
        module.exports = {
            msalConfig: msalConfig,
            loginRequest: loginRequest,
        };
    }
    ```

    - `clientId` - アプリケーションの識別子 。クライアントとも呼ばれます。 引用符で囲まれたテキストを、前に記録した **アプリケーション (クライアント) ID** 値に置き換えます。
    - `authority` - 機関は、MSAL がトークンを要求できるディレクトリを示す URL です。 *Enter\_the\_Tenant\_Info\_Here*を、先ほど記録した**ディレクトリ (テナント) ID** の値に置き換えます。
    - `redirectUri` - アプリケーションの **リダイレクト URI** 。 必要に応じて、引用符で囲まれたテキストを、前に記録したリダイレクト URI に置き換えます。

## [反応する](#tab/react-workforce)
1. IDE で、サンプルを含む ms-identity-docs-code-javascript/react-spaプロジェクト フォルダーを開きます。
2. *react-spa/src/authConfig.js* を開き、管理センターに記録された情報で次の値を更新します。

    ```JavaScript
    /*
     * Copyright (c) Microsoft Corporation. All rights reserved.
     * Licensed under the MIT License.
     */
    
    import { LogLevel } from "@azure/msal-browser";
    
    /**
     * Configuration object to be passed to MSAL instance on creation. 
     * For a full list of MSAL.js configuration parameters, visit:
     * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/configuration.md 
     */
    
    export const msalConfig = {
        auth: {
            clientId: "Enter_the_Application_Id_Here",
            authority: "https://login.microsoftonline.com/Enter_the_Tenant_Info_Here",
            redirectUri: "http://localhost:3000",
        },
        cache: {
            cacheLocation: "sessionStorage", // This configures where your cache will be stored
            storeAuthStateInCookie: false, // Set this to "true" if you are having issues on IE11 or Edge
        },
        system: {	
            loggerOptions: {	
                loggerCallback: (level, message, containsPii) => {	
                    if (containsPii) {		
                        return;		
                    }		
                    switch (level) {
                        case LogLevel.Error:
                            console.error(message);
                            return;
                        case LogLevel.Info:
                            console.info(message);
                            return;
                        case LogLevel.Verbose:
                            console.debug(message);
                            return;
                        case LogLevel.Warning:
                            console.warn(message);
                            return;
                        default:
                            return;
                    }	
                }	
            }	
        }
    };
    
    /**
     * Scopes you add here will be prompted for user consent during sign-in.
     * By default, MSAL.js will add OIDC scopes (openid, profile, email) to any login request.
     * For more information about OIDC scopes, visit: 
     * https://docs.microsoft.com/en-us/azure/active-directory/develop/v2-permissions-and-consent#openid-connect-scopes
     */
    export const loginRequest = {
        scopes: ["User.Read"]
    };
    
    /**
     * Add here the scopes to request when obtaining an access token for MS Graph API. For more information, see:
     * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/resources-and-scopes.md
     */
    export const graphConfig = {
        graphMeEndpoint: "https://graph.microsoft.com/v1.0/me",
    };
    ```

    - `clientId` - アプリケーションの識別子 。クライアントとも呼ばれます。 引用符で囲まれたテキストを、前に記録した **アプリケーション (クライアント) ID** 値に置き換えます。
    - `authority` - 機関は、MSAL がトークンを要求できるディレクトリを示す URL です。 *Enter\_the\_Tenant\_Info\_Here*を、先ほど記録した**ディレクトリ (テナント) ID** の値に置き換えます。
    - `redirectUri` - アプリケーションの **リダイレクト URI** 。 必要に応じて、引用符で囲まれたテキストを、前に記録したリダイレクト URI に置き換えます。

## [角度](#tab/angular-workforce)
1. IDE で、サンプルを含むプロジェクト フォルダー *ms-identity-docs-code-javascript/angular-spa* を開きます。
2. *angular-spa/src/app/app.module.ts* を開き、管理センターに記録されている情報で次の値を更新します。

    ```JavaScript
    // Required for Angular multi-browser support
    import { BrowserModule } from '@angular/platform-browser';
    
    // Required for Angular
    import { NgModule } from '@angular/core';
    
    // Required modules and components for this application
    import { AppRoutingModule } from './app-routing.module';
    import { AppComponent } from './app.component';
    import { ProfileComponent } from './profile/profile.component';
    import { HomeComponent } from './home/home.component';
    
    // HTTP modules required by MSAL
    import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
    
    // Required for MSAL
    import { IPublicClientApplication, PublicClientApplication, InteractionType, BrowserCacheLocation, LogLevel } from '@azure/msal-browser';
    import { MsalGuard, MsalInterceptor, MsalBroadcastService, MsalInterceptorConfiguration, MsalModule, MsalService, MSAL_GUARD_CONFIG, MSAL_INSTANCE, MSAL_INTERCEPTOR_CONFIG, MsalGuardConfiguration, MsalRedirectComponent } from '@azure/msal-angular';
    
    const isIE = window.navigator.userAgent.indexOf('MSIE ') > -1 || window.navigator.userAgent.indexOf('Trident/') > -1;
    
    export function MSALInstanceFactory(): IPublicClientApplication {
      return new PublicClientApplication({
        auth: {
          // 'Application (client) ID' of app registration in the Microsoft Entra admin center - this value is a GUID
          clientId: "Enter_the_Application_Id_Here",
          // Full directory URL, in the form of https://login.microsoftonline.com/<tenant>
          authority: "https://login.microsoftonline.com/Enter_the_Tenant_Info_Here",
          // Must be the same redirectUri as what was provided in your app registration.
          redirectUri: "http://localhost:4200",
        },
        cache: {
          cacheLocation: BrowserCacheLocation.LocalStorage,
          storeAuthStateInCookie: isIE
        }
      });
    }
    
    // MSAL Interceptor is required to request access tokens in order to access the protected resource (Graph)
    export function MSALInterceptorConfigFactory(): MsalInterceptorConfiguration {
      const protectedResourceMap = new Map<string, Array<string>>();
      protectedResourceMap.set('https://graph.microsoft.com/v1.0/me', ['user.read']);
    
      return {
        interactionType: InteractionType.Redirect,
        protectedResourceMap
      };
    }
    
    // MSAL Guard is required to protect routes and require authentication before accessing protected routes
    export function MSALGuardConfigFactory(): MsalGuardConfiguration {
      return { 
        interactionType: InteractionType.Redirect,
        authRequest: {
          scopes: ['user.read']
        }
      };
    }
    
    // Create an NgModule that contains the routes and MSAL configurations
    @NgModule({
      declarations: [
        AppComponent,
        HomeComponent,
        ProfileComponent
      ],
      imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        MsalModule
      ],
      providers: [
        {
          provide: HTTP_INTERCEPTORS,
          useClass: MsalInterceptor,
          multi: true
        },
        {
          provide: MSAL_INSTANCE,
          useFactory: MSALInstanceFactory
        },
        {
          provide: MSAL_GUARD_CONFIG,
          useFactory: MSALGuardConfigFactory
        },
        {
          provide: MSAL_INTERCEPTOR_CONFIG,
          useFactory: MSALInterceptorConfigFactory
        },
        MsalService,
        MsalGuard,
        MsalBroadcastService
      ],
      bootstrap: [AppComponent, MsalRedirectComponent]
    })
    export class AppModule { }
    ```

    - `clientId` - アプリケーションの識別子 。クライアントとも呼ばれます。 引用符で囲まれたテキストを、前に記録した **アプリケーション (クライアント) ID** 値に置き換えます。
    - `authority` - 機関は、MSAL がトークンを要求できるディレクトリを示す URL です。 *Enter\_the\_Tenant\_Info\_Here*を、先ほど記録した**ディレクトリ (テナント) ID** の値に置き換えます。
    - `redirectUri` - アプリケーションの **リダイレクト URI** 。 必要に応じて、引用符で囲まれたテキストを、前に記録したリダイレクト URI に置き換えます。

## [Blazor](#tab/blazor-workforce)
1. IDE で、サンプルを含むプロジェクト フォルダー *ms-identity-docs-code-dotnet/spa-blazor-wasm* を開きます。
2. *spa-blazor-wasm/wwwroot/appsettings.json* を開き、管理センターで前に記録した情報で次の値を更新します。

    ```JavaScript
    {
      "AzureAd": {
        "Authority": "https://login.microsoftonline.com/<Enter the tenant ID obtained from the Microsoft Entra admin center>",
        "ClientId": "Enter the client ID obtained from the Microsoft Entra admin center",
        "ValidateAuthority": true
      }
    }
    ```

    - `Authority` - 機関は、MSAL がトークンを要求できるディレクトリを示す URL です。 *Enter\_the\_Tenant\_Info\_Here*を、先ほど記録した**ディレクトリ (テナント) ID** の値に置き換えます。
    - `ClientId` - アプリケーションの識別子 。クライアントとも呼ばれます。 引用符で囲まれたテキストを、前に記録した **アプリケーション (クライアント) ID** 値に置き換えます。

---

### アプリケーションを実行してサインインしてサインアウトする

## [JavaScript](#tab/javascript-workforce)
Node.js を使用して Web サーバーでプロジェクトを実行します。

1. サーバーを起動するには、プロジェクト ディレクトリ内から次のコマンドを実行します。

    ```console
    cd vanillajs-spa/App
    npm install
    npm start
    ```
2. ターミナルに表示される `https` URL (`https://localhost:3000`など) をコピーし、ブラウザーに貼り付けます。 プライベートまたはシークレット のブラウザー セッションを使用することをお勧めします。
3. 手順に従い、Microsoft アカウントでサインインするために必要な詳細を入力します。 ワンタイム パスコードを送信できるように、メール アドレスが要求されます。 メッセージが表示されたら、コードを入力します。
4. アプリケーションは、アクセス権を付与したデータへのアクセスを維持し、サインインしてプロファイルを読み取るアクセス許可を要求します。 [ **承諾]** を選択します。 次のスクリーンショットは、アプリケーションにサインインし、Microsoft Graph API からプロファイルの詳細にアクセスしたことを示しています。

    [Image: API 呼び出しの結果を示す JavaScript アプリのスクリーンショット。]

## [反応する](#tab/react-workforce)
Node.js を使用して Web サーバーでプロジェクトを実行します。

1. サーバーを起動するには、プロジェクト ディレクトリ内から次のコマンドを実行します。

    ```console
    cd react-spa
    npm install
    npm start
    ```
2. ターミナルに表示される `https` URL (`https://localhost:3000`など) をコピーし、ブラウザーに貼り付けます。 プライベートまたはシークレット のブラウザー セッションを使用することをお勧めします。
3. 手順に従い、Microsoft アカウントでサインインするために必要な詳細を入力します。 1 回限りパスコードを送信できるように、電子メール アドレスが要求されます。 メッセージが表示されたら、コードを入力します。
4. アプリケーションは、アクセス権を付与したデータへのアクセスを維持し、サインインしてプロファイルを読み取るアクセス許可を要求します。 [ **承諾]** を選択します。 次のスクリーンショットは、アプリケーションにサインインし、Microsoft Graph API からプロファイルの詳細にアクセスしたことを示しています。

    [Image: API 呼び出しの結果を示す JavaScript アプリのスクリーンショット。]

## [角度](#tab/angular-workforce)
Node.js を使用して Web サーバーでプロジェクトを実行します。

1. サーバーを起動するには、プロジェクト ディレクトリ内から次のコマンドを実行します。

    ```console
    cd angular-spa
    npm install
    npm start
    ```
2. ターミナルに表示される `https` URL ( `https://localhost:4200`など) をコピーし、ブラウザーのアドレス バーに貼り付けます。 プライベートまたはシークレット のブラウザー セッションを使用することをお勧めします。
3. 手順に従い、Microsoft アカウントでサインインするために必要な詳細を入力します。 ワンタイム パスコードを送信できるように、メール アドレスが要求されます。 メッセージが表示されたら、コードを入力します。
4. アプリケーションは、アクセス権を付与したデータへのアクセスを維持し、サインインしてプロファイルを読み取るアクセス許可を要求します。 [ **承諾]** を選択します。 次のスクリーンショットは、アプリケーションにサインインし、Microsoft Graph API からプロファイルの詳細にアクセスしたことを示しています。

    [Image: API 呼び出しの結果を示す JavaScript アプリのスクリーンショット。]

## [Blazor](#tab/blazor-workforce)
dotnet を使用して Web サーバーでプロジェクトを実行します。

1. サーバーを起動するには、プロジェクト ディレクトリ内から次のコマンドを実行します。

    ```console
    cd spa-blazor-wasm
    dotnet workload install wasm-tools
    dotnet run
    ```
2. ターミナルに表示される `http` URL (`http://localhost:5000`など) をコピーし、ブラウザーに貼り付けます。 プライベートまたはシークレット のブラウザー セッションを使用することをお勧めします。
3. 手順に従い、Microsoft アカウントでサインインするために必要な詳細を入力します。 ワンタイム パスコードを送信できるように、メール アドレスが要求されます。 メッセージが表示されたら、コードを入力します。
4. アプリケーションは、アクセス権を付与したデータへのアクセスを維持し、サインインしてプロファイルを読み取るアクセス許可を要求します。 [ **承諾]** を選択します。 次のスクリーンショットは、アプリケーションにサインインし、Microsoft Graph API からプロファイルの詳細にアクセスしたことを示しています。

    [Image: API 呼び出しの結果を示す Blazor WASM SPA アプリのスクリーンショット。]

---

::: zone-end

::: zone pivot="external"

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 以下のいずれの Microsoft Entra ロールにも、必要なアクセス許可が含まれています。
    - アプリケーション管理者
    - アプリケーション開発者
- 外部テナント。 作成するには、次の方法から選択します。
    - [Microsoft Entra 外部 ID 拡張機能](https://aka.ms/ciamvscode/samples/marketplace)を使用して、Visual Studio Code で外部テナントを直接設定します。 *(おすすめ)*
    - Microsoft Entra 管理センターで[新しい外部テナントを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)します。
- ユーザーの流れ。 詳細については、 [外部テナント内のアプリのセルフサービス サインアップ ユーザー フローを作成する方法](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)に関するページを参照してください。 このユーザー フローは、複数のアプリケーションに使用できます。
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコード エディター。

## [JavaScript](#tab/javascript-external)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **シングルページ アプリケーション** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:3000/`
- [アプリケーションをユーザー フローに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)
- [Node.js](https://nodejs.org/en/download/)

## [反応する](#tab/react-external)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **シングルページ アプリケーション** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:3000/`
- [アプリケーションをユーザー フローに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)
- [Node.js](https://nodejs.org/en/download/)

## [角度](#tab/angular-external)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **シングルページ アプリケーション** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:4200/`
- [アプリケーションをユーザー フローに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)
- [Node.js](https://nodejs.org/en/download/)

---

### サンプル SPA を複製またはダウンロードする

サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

## [JavaScript](#tab/javascript-external)
- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial.git
    ```
- [サンプルをダウンロードします](https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

## [反応する](#tab/react-external)
- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial.git
    ```
- [サンプルをダウンロードします](https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

## [角度](#tab/angular-external)
- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial.git
    ```
- [サンプルをダウンロードします](https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

---

### サンプル SPA を構成する

## [JavaScript](#tab/javascript-external)
1. `App/public/authConfig.js`開き、次の値を Microsoft Entra 管理センターから取得した値に置き換えます。

    - `Enter_the_Application_Id_Here` を、前に登録したアプリのアプリケーション (クライアント) ID に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。
2. ファイルを保存します。

## [反応する](#tab/react-external)
1. `SPA\src\authConfig.js`開き、次の値を Microsoft Entra 管理センターから取得した値に置き換えます。

    - `Enter_the_Application_Id_Here` を、前に登録したアプリのアプリケーション (クライアント) ID に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。
2. ファイルを保存します。

## [角度](#tab/angular-external)
1. `SPA/src/app/auth-config.ts`開き、次の値を Microsoft Entra 管理センターから取得した値に置き換えます。

    - `Enter_the_Application_Id_Here` を、前に登録したアプリのアプリケーション (クライアント) ID に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。
2. ファイルを保存します。

---

### プロジェクトを実行してサインインする

## [JavaScript](#tab/javascript-external)
1. サーバーを起動するには、プロジェクト ディレクトリ内から次のコマンドを実行します。

    ```console
    cd 1-Authentication\0-sign-in-vanillajs\App
    npm install
    npm start
    ```
2. ターミナルに表示される `https` URL (`https://localhost:3000`など) をコピーし、ブラウザーに貼り付けます。 プライベートまたはシークレット のブラウザー セッションを使用することをお勧めします。
3. テナントに登録されているアカウントでサインインします。
4. 次のスクリーンショットは、アプリケーションにサインインし、Microsoft Graph API からプロファイルの詳細にアクセスしたことを示しています。

    [Image: API 呼び出しの結果を示す JavaScript アプリのスクリーンショット。]

## [反応する](#tab/react-external)
1. サーバーを起動するには、プロジェクト ディレクトリ内から次のコマンドを実行します。

    ```console
    cd 1-Authentication\1-sign-in-react\SPA
    npm install
    npm start
    ```
2. ターミナルに表示される `https` URL (`https://localhost:3000`など) をコピーし、ブラウザーに貼り付けます。 プライベートまたはシークレット のブラウザー セッションを使用することをお勧めします。
3. 外部テナントに登録されているアカウントでサインインします。
4. 次のスクリーンショットは、アプリケーションにサインインし、Microsoft Graph API からプロファイルの詳細にアクセスしたことを示しています。

## [角度](#tab/angular-external)
1. サーバーを起動するには、プロジェクト ディレクトリ内から次のコマンドを実行します。

    ```console
    cd 1-Authentication\2-sign-in-angular\SPA
    npm install
    npm start
    ```
2. ターミナルに表示される `https` URL (`https://localhost:4200`など) をコピーし、ブラウザーに貼り付けます。 プライベートまたはシークレット のブラウザー セッションを使用することをお勧めします。
3. 外部テナントに登録されているアカウントでサインインします。
4. 次のスクリーンショットは、アプリケーションにサインインし、Microsoft Graph API からプロファイルの詳細にアクセスしたことを示しています。

    [Image: API 呼び出しの結果を示す JavaScript アプリのスクリーンショット。]

---

::: zone-end

### アプリケーションからサインアウトする

1. ページの **[サインアウト** ] ボタンを見つけて選択します。
2. サインアウト元のアカウントを選択するように求められます。 サインインに使用したアカウントを選択します。

サインアウトしたことを示すメッセージが表示されます。ブラウザー ウィンドウを閉じることができるようになりました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-v2-windows-desktop"} -->
## クイック スタート: Windows デスクトップ アプリケーションでユーザーをサインインさせ、Microsoft Graph を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-v2-windows-desktop
- Service: identity-platform
- Article date: 2022-01-14
- Summary: このクイック スタートでは、Windows Presentation Foundation (WPF) アプリがアクセス トークンを取得し、Microsoft ID プラットフォームによって保護された API を呼び出す方法について説明します。

ようこそ。 ご要望のページを表示できません。 問題の修正に取り組んでいますが、次のリンクから目的の記事にアクセスできるかお試しください。

>
> [クイック スタート: Windows デスクトップ アプリでユーザーをサインインさせ、Microsoft Graph を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-desktop-app-wpf-sign-in)

ご不便をおかけして申し訳ありませんが、問題が解決するまで今しばらくお待ちください。

このクイック スタートでは、Windows Presentation Foundation (WPF) アプリケーションがユーザーをサインインさせ、Microsoft Graph API を呼び出すアクセス トークンを取得する方法を示すコード サンプルをダウンロードして実行します。

図については、「このサンプルのしくみ」を参照してください。

##### 手順 1:Azure portal でのアプリケーションの構成

このクイックスタートのコード サンプルを機能させるには、と`https://login.microsoftonline.com/common/oauth2/nativeclient`の`ms-appx-web://microsoft.aad.brokerplugin/{client_id}`追加します。

[Image: 構成済み] アプリケーションはこれらの属性で構成されています。

##### 手順 2:Visual Studio プロジェクトをダウンロードする

Visual Studio 2019 を使用してプロジェクトを実行します。

ヒント

Windows におけるパスの長さの制限に起因したエラーを防ぐため、ドライブのルートに近いディレクトリをアーカイブの展開先またはリポジトリのクローン先とすることをお勧めします。

##### 手順 3:アプリが構成され、実行準備ができる

アプリのプロパティの値を使用してプロジェクトを構成したら、実行する準備は完了です。

注

`Enter_the_Supported_Account_Info_Here`

### 詳細情報

#### このサンプルのしくみ

[Image: このクイック スタートで生成されたサンプル アプリの動作の紹介]

#### MSAL.NET

MSAL ([Microsoft.Identity.Client](https://www.nuget.org/packages/Microsoft.Identity.Client)) は、ユーザーのサインインと、Microsoft ID プラットフォームによって保護された API へのアクセスに使用されるトークンの要求に使用されるライブラリです。 MSAL は、Visual Studio の "**パッケージ マネージャー コンソール**" で次のコマンドを実行してインストールできます。

```powershell
Install-Package Microsoft.Identity.Client -IncludePrerelease
```

#### MSAL の初期化

MSAL への参照を追加するには、次のコードを追加します。

```csharp
using Microsoft.Identity.Client;
```

続いて、次のコードを使用して MSAL を初期化します。

```csharp
IPublicClientApplication publicClientApp = PublicClientApplicationBuilder.Create(ClientId)
                .WithRedirectUri("https://login.microsoftonline.com/common/oauth2/nativeclient")
                .WithAuthority(AzureCloudInstance.AzurePublic, Tenant)
                .Build();
```

| 場所は: | 説明 |
| --- | --- |
| `ClientId` | Azure portal に登録されているアプリケーションの "**アプリケーション (クライアント) ID**"。 この値は、Azure portal のアプリの **[概要]** ページで確認できます。 |

#### トークンの要求

MSAL には、トークンを取得するための 2 つの方法 ( `AcquireTokenInteractive` と `AcquireTokenSilent`) があります。

##### ユーザー トークンを対話形式で取得する

状況によっては、ユーザーが資格情報を検証したり同意したりするために、ポップアップ ウィンドウを介して Microsoft ID プラットフォームとやり取りする必要があります。 いくつかの例を次に示します。

- ユーザーが初めてアプリケーションにサインインした場合
- パスワードの有効期限が切れているため、ユーザーが資格情報を再入力する必要がある場合
- アプリケーションが、ユーザーが同意する必要があるリソースへのアクセスを要求している場合
- 2 要素認証が必須である場合

```csharp
authResult = await App.PublicClientApp.AcquireTokenInteractive(_scopes)
                                      .ExecuteAsync();
```

| 場所は: | 説明 |
| --- | --- |
| `_scopes` | 要求するスコープを含む (Microsoft Graph 用の `{ "user.read" }` またはカスタム Web API 用の `{ "api://<Application ID>/access_as_user" }` など) |

##### ユーザートークンを静かに取得する

リソースにアクセスする必要があるたびに、ユーザーに資格情報の検証を要求する必要はありません。 ほとんどの場合、ユーザーの操作なしでトークンの取得と更新を行う必要があります。 `AcquireTokenSilent` メソッドを使用すると、最初の`AcquireTokenInteractive`メソッドの後に、保護されたリソースにアクセスするためのトークンを取得できます。

```csharp
var accounts = await App.PublicClientApp.GetAccountsAsync();
var firstAccount = accounts.FirstOrDefault();
authResult = await App.PublicClientApp.AcquireTokenSilent(scopes, firstAccount)
                                      .ExecuteAsync();
```

| 場所は: | 説明 |
| --- | --- |
| `scopes` | 要求するスコープを含む (Microsoft Graph 用の `{ "user.read" }` またはカスタム Web API 用の `{ "api://<Application ID>/access_as_user" }` など) |
| `firstAccount` | キャッシュ内の最初のユーザーを指定します (MSAL は 1 つのアプリで複数のユーザーをサポートします)。 |

### ヘルプとサポート

ヘルプが必要な場合、問題を報告したい場合、またはサポート オプションについて知りたい場合は、「 [開発者向けのヘルプとサポート](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-support-help-options)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-web-api-dotnet-protect-app"} -->
## クイック スタート: Microsoft ID プラットフォームによって保護されている Web API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-api-dotnet-protect-app
- Service: identity-platform
- Article date: 2025-04-03
- Summary: このクイック スタートでは、承認に Microsoft ID プラットフォームを使用して ASP.NET Web API を保護する方法を示すコード サンプルをダウンロードして変更します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このクイック スタートでは、サンプル Web アプリを使用して、Microsoft ID プラットフォームを使用して ASP.NET Web API を保護する方法について説明します。 このサンプルでは、 [Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) を使用して認証と承認を処理します。

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 [アカウントを無料で作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- [Microsoft Entra 管理センター](https://entra.microsoft.com)で新しいアプリを登録し、アプリ**の [概要**] ページからその識別子を記録します。 詳細については、「 [アプリケーションの登録」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)参照してください。
    - **名前**: *NewWebAPI1*
    - **サポートされているアカウントの種類**: *この組織のディレクトリ内のアカウントのみ (シングル テナント)*

## [ASP.NET](#tab/aspnet)
- Visual Studio 2022。 [Visual Studio を無料でダウンロードします](https://www.visualstudio.com/downloads/)。

## [ASP.NET Core](#tab/aspnet-core)
- [Visual Studio Code](https://code.visualstudio.com/download)

---

### API を公開する

API が登録されると、API がクライアント アプリケーションに公開するスコープを定義することで、そのアクセス許可を構成することができます。 クライアント アプリケーションは、アクセス トークンとその要求を保護された Web API に渡すことで、操作を実行するためのアクセス許可を要求します。 そして Web API は、受け取ったアクセス トークンに必要なスコープが含まれている場合のみ、要求された操作を実行します。

## [ASP.NET](#tab/aspnet)
1. [**管理**] で、[**API の公開**] を選択&gt;**スコープを追加します**。 `api://{clientId}` を選択して、提案されたアプリケーション ID URI () を受け入れ、次の情報を入力します。

    1. **[スコープ名]** に「`access_as_user`」と入力します。
    2. [ **同意できるユーザー**] で、[ **管理者とユーザー** ] オプションが選択されていることを確認します。
    3. [ **管理者の同意の表示名** ] ボックスに「 `Access TodoListService as a user`」と入力します。
    4. [ **管理者の同意の説明** ] ボックスに「 `Accesses the TodoListService web API as a user`」と入力します。
    5. [ **ユーザーの同意の表示名** ] ボックスに「 `Access TodoListService as a user`」と入力します。
    6. [ **ユーザーの同意の説明** ] ボックスに「 `Accesses the TodoListService web API as a user`」と入力します。
    7. **[状態]** の場合は、**[有効]**のままにします。
2. [ **スコープの追加] を選択します**。

## [ASP.NET Core](#tab/aspnet-core)
#### 委任されたアクセス許可 (スコープ) を追加する

API は、クライアント アプリがユーザーのアクセス トークンを正常に取得するために、少なくとも 1 つのスコープ ( [委任されたアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)とも呼ばれます) を発行する必要があります。 スコープを発行するには、次の手順に従います。

1. [ **アプリの登録** ] ページで、作成した API アプリケーション (*ciam-ToDoList-api*) を選択して **[概要** ] ページを開きます。
2. [ **管理**] で、[ **API の公開**] を選択します。
3. ページの上部にある [ **アプリケーション ID URI**] の横にある **[追加** ] リンクを選択して、このアプリに対して一意の URI を生成します。
4. 提案されたアプリケーション ID URI ( `api://{clientId}`など) を受け入れ、[ **保存]** を選択します。 Web アプリケーションで Web API のアクセス トークンを要求すると、API に対して定義する各スコープのプレフィックスとしてこの URI が追加されます。
5. **この API で定義されているスコープで**、[**スコープの追加]** を選択します。
6. API への読み取りアクセスを定義する次の値を入力し、[ **スコープの追加]** を選択して変更を保存します。

    | プロパティ | 価値 |
    | --- | --- |
    | スコープ名 | *ToDoList.Read* |
    | 誰が同意できるか | **管理者のみ** |
    | 管理者の同意の表示名 | *'TodoListApi' を使用してユーザーの ToDo リストを読み取る* |
    | 管理者の同意の説明 | *アプリが 'TodoListApi' を使用してユーザーの ToDo リストを読み取*れるようにします。 |
    | 状態 | **有効** |
7. もう一度 [ **スコープの追加]** を選択し、API への読み取りと書き込みのアクセス スコープを定義する次の値を入力します。 [ **スコープの追加]** を選択して変更を保存します。

    | プロパティ | 価値 |
    | --- | --- |
    | スコープ名 | *ToDoList.ReadWrite* |
    | 誰が同意できるか | **管理者のみ** |
    | 管理者の同意の表示名 | *'ToDoListApi' を使用したユーザーの ToDo リストの読み取りと書き込み* |
    | 管理者の同意の説明 | *アプリが 'ToDoListApi' を使用してユーザーの ToDo リストの読み取りと書き込みを行えるようにする* |
    | 状態 | **有効** |

Web API [のアクセス許可を発行するときの最小特権の原則](https://learn.microsoft.com/ja-jp/security/zero-trust/develop/protected-api-example) の詳細について説明します。

#### アプリケーションのアクセス許可 (アプリ ロール) を追加する

API は、クライアント アプリがアクセス トークンをそれ自体として取得するために、アプリケーションに対して少なくとも 1 つのアプリ ロール ( [アプリケーションアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)とも呼ばれます) を発行する必要があります。 アプリケーションのアクセス許可は、クライアント アプリケーションが自身として正常に認証できるようにして、ユーザーをサインインさせる必要がないようにする場合に、API が発行するアクセス許可の種類です。 アプリケーションのアクセス許可を発行するには、次の手順に従います。

1. [ **アプリの登録** ] ページで、作成したアプリケーション ( *ciam-ToDoList-api* など) を選択して **[概要** ] ページを開きます。
2. [ **管理**] で、[ **アプリ ロール**] を選択します。
3. [ **アプリ ロールの作成**] を選択し、次の値を入力し、[ **適用** ] を選択して変更を保存します。

    | プロパティ | 価値 |
    | --- | --- |
    | 表示名 | *ToDoList.Read.All* |
    | Allowed member types (許可されるメンバーの種類) | **アプリケーション** |
    | 価値 | *ToDoList.Read.All* |
    | 説明 | *アプリが 'TodoListApi' を使用してすべてのユーザーの ToDo リストを読み取れるようにする* |
    | このアプリ ロールを有効にしますか? | オンのままにする |
4. [ **アプリ ロールの作成** ] をもう一度選択し、2 つ目のアプリ ロールに次の値を入力し、[ **適用** ] を選択して変更を保存します。

    | プロパティ | 価値 |
    | --- | --- |
    | 表示名 | *ToDoList.ReadWrite.All* |
    | Allowed member types (許可されるメンバーの種類) | **アプリケーション** |
    | 価値 | *ToDoList.ReadWrite.All* |
    | 説明 | *アプリで 'ToDoListApi' を使用して、すべてのユーザーの ToDo リストの読み取りと書き込みを許可する* |
    | このアプリ ロールを有効にしますか? | オンのままにする |

[Image: API にスコープを追加するときのフィールド値を示すスクリーンショット。]

---

### サンプル アプリケーションを複製またはダウンロードする

サンプル アプリケーションを取得するには、GitHub から複製するか、 *.zip* ファイルとしてダウンロードします。

## [ASP.NET](#tab/aspnet)
```console
git clone https://github.com/AzureADQuickStarts/AppModelv2-NativeClient-DotNet.git
```

- [ZIP ファイルとしてダウンロードします](https://github.com/AzureADQuickStarts/AppModelv2-NativeClient-DotNet/archive/complete.zip)。

ヒント

Windows におけるパスの長さの制限に起因したエラーを防ぐため、ドライブのルートに近いディレクトリをアーカイブの展開先またはリポジトリのクローン先とすることをお勧めします。

## [ASP.NET Core](#tab/aspnet-core)
```console
git clone https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial.git
```

- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

---

### サンプル アプリケーションを構成する

登録済みの Web API と一致するようにコード サンプルを構成します。

## [ASP.NET](#tab/aspnet)
1. Visual Studio でソリューションを開き、TodoListService プロジェクトのルートにある *appsettings.json* ファイルを開きます。
2. `Enter_the_Application_Id_here` および  の両方のプロパティで、`ClientID` の値を`Audience`ポータルで登録したアプリケーションのクライアント ID (アプリケーション ID) に置き換えます。

#### 新しいスコープを app.config ファイルに追加する

TodoListClient *app.config* ファイルに新しいスコープを追加するには、次の手順に従います。

1. TodoListClient プロジェクトのルート フォルダーで、 *app.config* ファイルを開きます。
2. `TodoListServiceScope` パラメーターで TodoListService プロジェクトに登録したアプリケーションのアプリケーション ID を貼り付け、`{Enter the Application ID of your TodoListService from the app registration portal}` 文字列を置き換えます。

注

アプリケーション ID の形式が `api://{TodoListService-Application-ID}/access_as_user` になっていることを確認してください (`{TodoListService-Application-ID}` は TodoListService アプリのアプリケーション ID を表す GUID です)。

### Web アプリを登録する (TodoListClient)

Microsoft Entra 管理センターの **アプリ登録** で TodoListClient アプリを登録し、TodoListClient プロジェクトでコードを構成します。 クライアントとサーバーが同じアプリケーションと見なされる場合は、手順 2 で登録したアプリケーションを再利用できます。 ユーザーが個人用 Microsoft アカウントでサインインできるようにするには、同じアプリケーションを使用します。

#### アプリを登録する

TodoListClient アプリを登録するには、これらの手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**App registrations** に移動し、[**新規登録**] を選択します。
3. [ **新しい登録**] を選択します。
4. [ **アプリケーションの登録] ページ** が開いたら、アプリケーションの登録情報を入力します。

    1. [ **名前** ] セクションに、アプリのユーザーに表示されるわかりやすいアプリケーション名 ( **NativeClient-DotNet-TodoListClient** など) を入力します。
    2. **[サポートされているアカウントの種類**] で、**任意の組織ディレクトリの [アカウント**] を選択します。
    3. [ **登録** ] を選択してアプリケーションを作成します。

    注

    TodoListClient プロジェクト *app.config* ファイルでは、 `ida:Tenant` の既定値は `common` に設定されます。 指定できる値は次のとおりです。

    - `common`: 職場または学校のアカウントまたは個人の Microsoft アカウントを使用してサインインできます (前の手順 **で任意の組織のディレクトリで [アカウント]** を選択したため)。
    - `organizations`:職場または学校アカウントを使用してサインインできます。
    - `consumers`:Microsoft の個人用アカウントを使用してのみサインインできます。
5. アプリの **[概要** ] ページで [ **認証**] を選択し、次の手順を実行してプラットフォームを追加します。

    1. [ **プラットフォームの構成**] で、[ **プラットフォームの追加] ボタンを** 選択します。
    2. **モバイル アプリケーションとデスクトップ アプリケーションの場合は**、[**モバイル アプリケーションとデスクトップ アプリケーション**] を選択します。
    3. **[リダイレクト URI] で**、[`https://login.microsoftonline.com/common/oauth2/nativeclient`] チェック ボックスをオンにします。
    4. **構成**を選択します。
6. **API のアクセス許可**を選択し、次の手順を実行してアクセス許可を追加します。

    1. [ **アクセス許可の追加] ボタンを** 選択します。
    2. [ **マイ API** ] タブを選択します。
    3. API の一覧で、 **AppModelv2-NativeClient-DotNet-TodoListService API** または Web API に入力した名前を選択します。
    4. まだ選択されていない場合は、[ **access\_as\_user** アクセス許可] チェック ボックスをオンにします。 必要に応じて検索ボックスを使用します。
    5. [ **アクセス許可の追加]** ボタンを選択します。

#### プロジェクトを構成する

*app.config* ファイルにアプリケーション ID を追加して、TodoListClient プロジェクトを構成します。

1. **アプリ登録**ポータルの **[概要**] ページで、**アプリケーション (クライアント) ID** の値をコピーします。
2. TodoListClient プロジェクトのルート フォルダーから * 、app.config* ファイルを開き、 `ida:ClientId` パラメーターにアプリケーション ID の値を貼り付けます。

## [ASP.NET Core](#tab/aspnet-core)
1. IDE で、サンプルを含むプロジェクト フォルダー *ms-identity-ciam-dotnet-tutorial/2-Authorization/3-call-own-api-dotnet-core-daemon/ToDoListAPI* を開きます。
2. 次のコード スニペットを含む `appsettings.json` ファイルを開きます。

    ```json
    {
      "AzureAd": {
        "Instance": "Enter_the_Authority_URL_Here", //For external tenants, use instance in the form of "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/"
        "TenantId": "Enter_the_Tenant_Id_Here",
        "ClientId": "Enter_the_Application_Id_Here",
        "Scopes": {
          "Read": ["ToDoList.Read", "ToDoList.ReadWrite"],
          "Write": ["ToDoList.ReadWrite"]
        },
        "AppPermissions": {
          "Read": ["ToDoList.Read.All", "ToDoList.ReadWrite.All"],
          "Write": ["ToDoList.ReadWrite.All"]
        }
      },
      "Logging": {...},
      "AllowedHosts": "*"
    }
    ```

    次の値を見つけます。

    - `ClientId` - アプリケーションの識別子 。クライアントとも呼ばれます。 引用符で囲まれた`value`テキストを、登録済みアプリケーションの **[概要**] ページから先ほど記録したアプリケーション **(クライアント) ID** に置き換えます。
    - `TenantId` - アプリケーションが登録されているテナントの識別子。 引用符で囲まれた`value`テキストを、登録済みアプリケーションの **[概要**] ページから前に記録した**ディレクトリ (テナント) ID** 値に置き換えます。
    - `Instance` - Microsoft 認証ライブラリ (MSAL) がトークンを要求できるディレクトリを指定します。 シナリオに応じて、 `Enter_the_Authority_URL_Here`を次のいずれかの値に置き換えます。
        - 従業員テナントの場合は、インスタンスとして `https://login.microsoftonline.com/` を使用します。
        - 外部テナントの場合は、次の形式で機関 URL を追加します。 `https://<Enter_the_Tenant_Subdomain_Here>.ciamlogin.com/`

---

### サンプル アプリケーションの実行

## [ASP.NET](#tab/aspnet)
両方のプロジェクトを開始します。 Visual Studio ユーザーの場合は次のとおりです。

1. Visual Studio ソリューションを右クリックし、[**プロパティ**] を選択します
2. **[共通プロパティ] で**、[**スタートアップ プロジェクト**] を選択し、[**複数のスタートアップ プロジェクト**] を選択します。
3. 両方のプロジェクトで、アクションとして **[開始** ] を選択します
4. 上向きの矢印を使用して TodoListService サービスを一覧の最初の位置に移動し、最初に開始されるようにします。

TodoListClient プロジェクトにサインインして実行します。

1. F5 キーを押して、プロジェクトを開始します。 サービスのページと、デスクトップ アプリケーションが開きます。
2. TodoListClient の右上にある [ **サインイン**] を選択し、アプリケーションの登録に使用したのと同じ資格情報でサインインするか、同じディレクトリ内のユーザーとしてサインインします。

    初めてサインインする場合は、TodoListService Web API に同意するように求められることがあります。

    TodoListService Web API にアクセスして *To-Do リストを* 操作するために、サインインは *access\_as\_user* スコープへのアクセス トークンも要求します。

### クライアント アプリケーションを事前承認する

Web API にアクセスするクライアント アプリケーションを事前承認することで、他のディレクトリのユーザーに Web API へのアクセスを許可することができます。 これを行うには、クライアント アプリから Web API の事前認証されたアプリケーションの一覧にアプリケーション ID を追加します。 事前認証されたクライアントを追加することで、ユーザーは同意を得ることなく Web API にアクセスできるようになります。

1. **アプリ登録**ポータルで、TodoListService アプリのプロパティを開きます。
2. [ **API の公開** ] セクションの [ **承認されたクライアント アプリケーション**] で、[ **クライアント アプリケーションの追加]** を選択します。
3. [ **クライアント ID** ] ボックスに、TodoListClient アプリのアプリケーション ID を貼り付けます。
4. [ **承認されたスコープ** ] セクションで、 `api://<Application ID>/access_as_user` Web API のスコープを選択します。
5. [ **アプリケーションの追加] を選択します**。

#### プロジェクトの実行

1. F5 キーを押してプロジェクトを実行します。 TodoListClient アプリが開きます。
2. 右上にある [ **サインイン**] を選択し、個人の Microsoft アカウント ( *live.com* 、 *hotmail.com* アカウント、職場または学校アカウントなど) を使用してサインインします。

### 省略可能:サインイン アクセスを特定のユーザーに制限する

既定では、 *outlook.com* アカウントや *live.com* アカウント、Microsoft Entra ID と統合されている組織の職場または学校アカウントなどの個人アカウントは、トークンを要求して Web API にアクセスできます。

アプリケーションにサインインできるユーザーを指定するには、`TenantId` ファイルの  プロパティを変更します。

## [ASP.NET Core](#tab/aspnet-core)
1. Web API プロジェクト ディレクトリのルートから次のコマンドを実行して、アプリを起動します。

    ```bash
    dotnet run
    ```
2. すべてが正常に機能した場合、ターミナルには次のような出力が表示されます。

    ```bash
     Building...
         info: Microsoft.Hosting.Lifetime[14]
               Now listening on: https://localhost:{port}
         info: Microsoft.Hosting.Lifetime[0]
               Application started. Press Ctrl+C to shut down.
         info: Microsoft.Hosting.Lifetime[0]
               Hosting environment: Development
    ...
    ```

    ポート番号を `https://localhost:{port}` URL に記録します。
3. エンドポイントが保護されていることを確認するには、次の cURL コマンドのベース URL を、前の手順で受信した URL と一致するように更新し、コマンドを実行します。

    ```bash
    curl -k -X GET https://localhost:<your-api-port>/api/todolist -w "%{http_code}\n"
    ```

    想定される応答は 401 Unauthorized です。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-web-app-node-sign-in-call-api"} -->
## クイック スタート - サンプル Nodejs Web アプリから Web API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-node-sign-in-call-api
- Service: identity-platform / external
- Article date: 2025-03-10
- Summary: Node.js Web アプリのコード サンプルを構成して、ユーザーをサインインさせ、外部テナントで API を呼び出す方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このクイック スタートでは、ユーザーをサインインさせ、外部テナントの Node.js Web アプリケーションから Web API を呼び出す方法について説明します。 サンプル アプリケーションは.NET API を呼び出します。 サンプル Web アプリケーションでは、Node 用の [Microsoft Authentication Library (MSAL)](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) を使用して認証を処理します。

### [前提条件]

- 「[クイック スタート: サンプル Web アプリでユーザーをサインインする」](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in?pivots=external&tabs=node-external) 記事の手順と前提条件を完了します。 この記事では、サンプルの Node.js Web アプリを使用してユーザーにサインインする方法について説明します。
- 外部テナント。 作成するには、次の方法から選択します。
    - (推奨)[Microsoft Entra External ID 拡張機能](https://aka.ms/ciamvscode/samples/marketplace) を使用して、Visual Studio Code で外部テナントを直接設定します。
    - [Microsoft Entra 管理センターで新しい外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal) を作成します。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*で、Web API 用の新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコードエディター。
- [Node.js](https://nodejs.org)。
- [.NET 7.0](https://dotnet.microsoft.com/learn/dotnet/hello-world-tutorial/install) 以降。

### API のスコープとロールを構成する

Web API を登録することで、クライアント アプリケーションが Web API へのアクセスを要求できるアクセス許可を定義するように API スコープを構成する必要があります。 さらに、ユーザーまたはアプリケーションで使用できるロールを指定するようにアプリ ロールを設定し、Web API を呼び出せるようにするために必要な API アクセス許可を Web アプリに付与する必要があります。

#### API スコープを構成する

クライアント アプリがユーザーのアクセス トークンを正常に取得するために、API は少なくとも 1 つのスコープ ([委任されたアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)とも呼ばれます) を発行する必要があります。 スコープを発行するには、次の手順に従います。

1. **[アプリの登録]** ページで、作成した API アプリケーション (*ciam-ToDoList-api*) を選択して **[概要]** ページを開きます。
2. **[管理]** の **[API の公開]** を選択します。
3. ページ上部の **[アプリケーション ID URI]** の横にある **[追加]** リンクを選択して、このアプリに一意の URI を生成します。
4. 提案されたアプリケーション ID URI (`api://{clientId}` など) を受け入れ、**[保存]** を選択します。 Web アプリケーションで Web API のアクセス トークンを要求すると、API に対して定義する各スコープのプレフィックスとしてこの URI が追加されます。
5. **[この API で定義されるスコープ]** で、 **[スコープの追加]** を選択します。
6. API への読み取りアクセスを定義する次の値を入力した後に、**[スコープの追加]** を選択して変更を保存します。

    | プロパティ | 価値 |
    | --- | --- |
    | スコープ名 | *ToDoList.Read* |
    | 誰が同意できるか | **管理者のみ** |
    | 管理者の同意の表示名 | *'TodoListApi' を使用してユーザーの ToDo リストを読み取る* |
    | 管理者の同意の説明 | *'TodoListApi' を使用して、アプリがユーザーの ToDo リストを読み取ることを許可します*。 |
    | 状態 | **有効** |
7. もう一度 **[スコープの追加]** を選択し、API への読み取りおよび書き込みアクセスを定義する次の値を入力します。 **[スコープの追加]** を選択して変更を保存します。

    | プロパティ | 価値 |
    | --- | --- |
    | スコープ名 | *ToDoList.ReadWrite* |
    | 誰が同意できるか | **管理者のみ** |
    | 管理者の同意の表示名 | *'ToDoListApi' を使用した、ユーザーの ToDo リストの読み取りと書き込み* |
    | 管理者の同意の説明 | *'ToDoListApi' を使用して、アプリからユーザーの ToDo リストを読み書きできるようにする* |
    | 状態 | **有効** |

Web API の[アクセス許可を発行するときの最小限の特権の原則](https://learn.microsoft.com/ja-jp/security/zero-trust/develop/protected-api-example)について説明します。

#### アプリ ロールを構成する

API は、クライアント アプリがアクセス トークンをそれ自体として取得するために、アプリケーションに対して少なくとも 1 つのアプリ ロール ( [アプリケーションアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)とも呼ばれます) を発行する必要があります。 アプリケーションのアクセス許可は、クライアント アプリケーションが自身として正常に認証できるようにして、ユーザーをサインインさせる必要がないようにする場合に、API が発行するアクセス許可の種類です。 アプリケーションのアクセス許可を発行するには、次の手順に従います。

1. **[アプリの登録]** ページから、作成したアプリケーション (*ciam-ToDoList-api* など) を選択して、その **[概要]** ページを開きます。
2. **[管理]** で、**[アプリ ロール]** を選択します。
3. **[アプリ ロールの作成]** を選択し、次の値を入力し、**[適用]** を選択して変更を保存します:

    | プロパティ | 価値 |
    | --- | --- |
    | 表示名 | *ToDoList.Read.All* |
    | Allowed member types (許可されるメンバーの種類) | **アプリケーション** |
    | 価値 | *ToDoList.Read.All* |
    | 説明 | *'ToDoListApi' を使用して、アプリがすべてのユーザーの ToDo リストを読むことができるようにする* |
    | このアプリ ロールを有効にしますか? | オンのままにする |
4. もう一度 **[アプリ ロールの作成]** を選択し、2 番目のアプリ ロールに次の値を入力し、**[適用]** を選択して変更を保存します:

    | プロパティ | 価値 |
    | --- | --- |
    | 表示名 | *ToDoList.ReadWrite.All* |
    | Allowed member types (許可されるメンバーの種類) | **アプリケーション** |
    | 価値 | *ToDoList.ReadWrite.All* |
    | 説明 | *'ToDoListApi' を使用して、アプリがすべてのユーザーの ToDo リストを読み書きできるようにする* |
    | このアプリ ロールを有効にしますか? | オンのままにする |

#### オプションクレームの設定

**idtyp** の省略可能な要求を追加すると、Web API がトークンが **アプリ** トークンなのか **アプリ + ユーザー** トークンなのかを判断するのに役立ちます。 **scp** と**ロール**要求の組み合わせを同じ目的で使用できますが、**idtyp** 要求を使用すると、アプリ トークンとアプリ + ユーザー トークンを区別する最も簡単な方法です。 たとえば、トークンがアプリ専用トークンの場合、この要求の値は *app* です。

アクセス トークンに [idtyp](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims?tabs=appui) 要求を追加するには、*オプションの要求の構成*に関する記事の手順を使用します。

- **[トークンの種類**] で [**アクセス**] を選択します。
- 省略可能な要求の一覧から **idtyp** を選択します。

#### Web アプリに API のアクセス許可を付与する

クライアント アプリ (*ciam-client-app*) に API のアクセス許可を付与するには、次の手順に従います。

1. [**アプリの登録**] ページで、作成したアプリケーション (ciam-client-app など) を選択して、**の [概要]** ページを開きます。
2. [**管理**] で **API 許可**を選択します。
3. **[構成されたアクセス許可]** の下で **[アクセス許可の追加]** を選択します。
4. **[所属する組織で使用している API]** タブを選択します。
5. API の一覧で、API (*ciam-ToDoList-api* など) を選択します。
6. **[委任されたアクセス許可]** オプションを選択します。
7. アクセス許可の一覧で **[ToDoList.Read, ToDoList.ReadWrite]** を選択します (必要に応じて検索ボックスを使用します)。
8. **[アクセス許可の追加]** ボタンを選択します。 この時点で、アクセス許可が正しく割り当てられました。 ただし、このテナントは顧客のテナントであるため、コンシューマー ユーザー自身がこれらのアクセス許可に同意することはできません。 この問題に対処するには、管理者が次のように、テナント内のすべてのユーザーに代わってこれらのアクセス許可に同意する必要があります。

    1. **[&lt;テナント名&gt; に管理者の同意を与えます]** を選択してから、**[はい]** を選択します。
    2. **[最新の情報に更新]** を選択し、両方のスコープの &lt; に、"**&gt;テナント名 に付与されました**" と表示されていることを確認します。
9. **[Configured permissions] (構成されたアクセス許可)** の一覧で**ToDoList.Read** と **ToDoList.ReadWrite** のアクセス許可を一度に 1 つずつ選択し、後で使用するためにアクセス許可の完全な URI をコピーします。 完全なアクセス許可 URI は、`api://{clientId}/{ToDoList.Read}` または `api://{clientId}/{ToDoList.ReadWrite}` のようになります。

### サンプル Web アプリケーションと Web API を複製またはダウンロードする

サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial.git
    ```
- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

### プロジェクトの依存関係をインストールする

1. コンソール ウィンドウを開き、Node.js/Express サンプル アプリが含まれるディレクトリに移動します。

    ```console
    cd 2-Authorization\4-call-api-express\App
    ```
2. 次のコマンドを実行して Web アプリの依存関係をインストールします。

    ```console
    npm install && npm update
    ```

### サンプル Web アプリと API を構成する

クライアント Web アプリのサンプルでアプリの登録を使用するには:

1. コード エディターで、`App\authConfig.js` ファイルを開きます。
2. プレースホルダーを見つけてください。

    - `Enter_the_Application_Id_Here` をクリックし、先ほど登録したクライアント アプリのアプリケーション (クライアント) ID に置き換えます。 クライアント アプリは、前提条件に登録したアプリです。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。
    - `Enter_the_Client_Secret_Here` を、先ほどコピーしたアプリ シークレットの値に置き換えます。
    - `Enter_the_Web_Api_Application_Id_Here` 前提条件の一部として先ほどコピーした Web API のアプリケーション (クライアント) ID に置き換えます。

Web API サンプルでアプリの登録を使用するには:

1. コード エディターで、`API\ToDoListAPI\appsettings.json` ファイルを開きます。
2. プレースホルダーを見つけてください。

    - `Enter_the_Application_Id_Here` を、コピーした Web API のアプリケーション (クライアント) ID に置き換えます。 Web API アプリは、前提条件の一環として以前に登録したものです。
    - `Enter_the_Tenant_Id_Here` を、先ほどコピーしたディレクトリ (テナント) ID に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。

### サンプル Web アプリと API を実行してテストする

1. コンソール ウィンドウを開き、次のコマンドを使用して Web API を実行します。

    ```console
    cd 2-Authorization\4-call-api-express\API\ToDoListAPI
    dotnet run
    ```
2. 次のコマンドを使用して Web アプリ クライアントを実行します。

    ```console
    cd 2-Authorization\4-call-api-express\App
    npm install
    npm start
    ```
3. ブラウザーを開き、http://localhost:3000. に移動します
4. **[サインイン]** ボタンを選択します。 サインインするように要求されます。

    [Image: ノード Web アプリへのサインインのスクリーンショット。]
5. サインイン ページで、**[メール アドレス]** を入力して **[次へ]** を選択し、**[パスワード]** を入力してから **[サインイン]** を選択します。 アカウントをお持ちでない場合は、**[アカウントをお持ちではない場合、作成できます]** リンクを選択します。これで、サインアップ フローが開始されます。
6. サインアップ オプションを選択した場合は、メール、ワンタイム パスコード、新しいパスワード、その他のアカウントの詳細を入力すると、サインアップ フロー全体が完了します。 以下のスクリーンショットのようなページが表示されます。 サインイン オプションを選択すると、同様のページが表示されます。

    [Image: ノード Web アプリへのサインインと API の呼び出しのスクリーンショット。]

#### API の呼び出し

1. API を呼び出すには、**[View your todolist] (Todolist の表示)** リンクを選択します。 以下のスクリーンショットのようなページが表示されます。

    [Image: API To Do リストを操作するスクリーンショット。]
2. 項目を作成および削除して、To Do リストを操作します。

#### 動作方法

タスクを表示、追加、または削除するたびに、API 呼び出しをトリガーします。 API 呼び出しをトリガーするたびに、クライアント Web アプリは API エンドポイントを呼び出すために必要なアクセス許可 (スコープ) を持つアクセス トークンを取得します。 たとえば、タスクを読み取るために、クライアント Web アプリは `ToDoList.Read` アクセス許可/スコープを持つアクセス トークンを取得する必要があります。

Web API エンドポイントは、クライアント アプリによって提供されるアクセス トークンのアクセス許可またはスコープが有効かどうかを確認する必要があります。 アクセス トークンが有効な場合、エンドポイントは HTTP 要求に応答し、それ以外の場合は `401 Unauthorized` の HTTP エラーで応答します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-web-app-node-sign-in-edit-profile"} -->
## クイック スタート - サンプル Node.js Web アプリでプロファイルを編集する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-node-sign-in-edit-profile
- Service: identity-platform
- Article date: 2024-11-28
- Summary: ユーザーのプロファイルを編集できるようにサンプルの Web アプリを構成する方法について説明します。 プロファイルの編集操作を行うには、顧客ユーザーが多要素認証 (MFA) を完了する必要があります

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このクイック スタートでは、Web アプリ Node.js サンプルを使用して、Web アプリでサインインとプロファイルの編集を追加する方法について説明します。 このサンプル Web アプリでは、[Microsoft Authentication Library for Node (MSAL Node)](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) と Microsoft Graph API を使用して、サインインとプロファイルの編集操作を完了します。 プロファイルの編集操作では、ユーザーが多要素認証 (MFA) を完了する必要があります。

### 前提条件

- 「[クイック スタート: サンプル Web アプリでユーザーをサインインする」](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in?pivots=external&tabs=node-external) 記事の手順と前提条件を完了します。 このクイック スタートでは、Web アプリ Node.js サンプルを使用してユーザーをサインインさせる方法について説明します。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *edit-profile-service* という名前で、*Microsoft Entra 管理センター*で Web API 用の新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- アプリの登録にクライアント シークレットを追加します。 運用アプリではクライアント シークレットを使用**しないでください**。 代わりに、証明書またはフェデレーション資格情報を使用してください。 詳細については、「 [アプリケーションに資格情報を追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=client-secret)。

### API のスコープとロールを構成する

Web API を登録することで、クライアント アプリケーションが Web API へのアクセスを要求できるアクセス許可を定義するように API スコープを構成する必要があります。 さらに、ユーザーまたはアプリケーションで使用できるロールを指定するようにアプリ ロールを設定し、Web API を呼び出せるようにするために必要な API アクセス許可を Web アプリに付与する必要があります。

#### EditProfileService アプリの API スコープを構成する

EditProfileService アプリは、Web API を呼び出すためにクライアント アプリが取得するアクセス許可を公開する必要があります。

クライアント アプリがユーザーのアクセス トークンを正常に取得するために、API は少なくとも 1 つのスコープ ([委任されたアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)とも呼ばれます) を発行する必要があります。 スコープを発行するには、次の手順に従います。

1. **[アプリの登録]** ページで、作成した API アプリケーション (例: *edit-profile-service*) を選択して **[概要]** ページを開きます。
2. **[管理]** の **[API の公開]** を選択します。
3. ページ上部の **[アプリケーション ID URI]** の横にある **[追加]** リンクを選択して、このアプリに一意の URI を生成します。
4. 提案されたアプリケーション ID URI (`api://{clientId}` など) を受け入れ、**[保存]** を選択します。 Web アプリケーションで Web API のアクセス トークンを要求すると、API に対して定義する各スコープのプレフィックスとしてこの URI が追加されます。
5. **[この API で定義されるスコープ]** で、 **[スコープの追加]** を選択します。
6. API への読み取りアクセスを定義する次の値を入力した後に、**[スコープの追加]** を選択して変更を保存します。

    | プロパティ | 価値 |
    | --- | --- |
    | スコープ名 | *EditProfileService.ReadWrite* |
    | 誰が同意できるか | **管理者のみ** |
    | 管理者の同意の表示名 | *クライアントがプロファイルの編集サービスを使用してプロファイルを編集する* |
    | 管理者の同意の説明 | *クライアント Web アプリがプロファイルの編集サービスを呼び出してプロファイルを編集できるスコープ*。 |
    | 状態 | **有効** |

#### EditProfileService アプリに User.ReadWrite アクセス許可を付与する

*User.ReadWrite* は、ユーザーが自分のプロファイルを更新できるようにする Microsoft Graph API のアクセス許可です。 EditProfileService アプリに *User.ReadWrite* アクセス許可を付与するには、次の手順に従います。

1. **[アプリの登録]** ページで作成したアプリケーション (例: *edit-profile-service*) を選択し、**[概要]** ページを開きます。
2. [**管理**] で **API 許可**を選択します。
3. **[Microsoft API]** タブを選択し、**[よく使用される Microsoft API]** で **[Microsoft Graph]** を選択します。
4. **[委任されたアクセス許可]** を選択し、アクセス許可の一覧で **[User.ReadWrite]** を検索して選択します。
5. **[アクセス許可の追加]** ボタンを選択します。
6. EditProfileService アプリに *User.ReadWrite* アクセス許可が正しく割り当てられている。 ただし、テナントは外部テナントであるため、顧客ユーザー自身がこれらのアクセス許可に同意することはできません。 テナント管理者は、テナント内のすべてのユーザーに代わってこのアクセス許可に同意する必要があります。

    1. **[&lt;テナント名&gt; に管理者の同意を与えます]** を選択してから、**[はい]** を選択します。
    2. **[最新の情報に更新]** を選択し、両方のスコープの &lt; に、"**&gt;テナント名 に付与されました**" と表示されていることを確認します。

### クライアント Web アプリに API のアクセス許可を付与する

このセクションでは、前に登録したクライアント Web アプリ (前提条件) に API アクセス許可を付与します。

クライアント Web アプリに *EditProfileService.ReadWrite* アクセス許可を付与します。 このアクセス許可は EditProfileService アプリによって公開され、MFA を使用して更新プロファイル操作を保護します。 クライアント Web アプリに *EditProfileService.ReadWrite* アクセス許可を付与するには、次の手順に従います。

1. **[アプリの登録]** ページで、作成した API アプリケーション (例: *ciam-client-app*) を選択し、**[概要]** ページを開きます。
2. [**管理**] で **API 許可**を選択します。
3. **[構成されたアクセス許可]** の下で **[アクセス許可の追加]** を選択します。
4. **[所属する組織で使用している API]** タブを選択します。
5. API の一覧で、*edit-profile-service* などの API を選択します。
6. **[委任されたアクセス許可]** オプションを選択します。
7. アクセス許可の一覧から、**[EditProfileService.ReadWrite]** を選択します。
8. **[アクセス許可の追加]** ボタンを選択します。
9. **[構成されたアクセス許可]** の一覧で **[EditProfileService.ReadWrite]** アクセス許可を選択し、後で使用するためにアクセス許可の完全な URI をコピーします。 アクセス許可の完全な URI は、`api://{clientId}/{EditProfileService.ReadWrite}` のようになります。
10. \**EditProfileService.ReadWrite* アクセス許可がクライアント Web アプリに正しく割り当てられている。 ただし、テナントは外部テナントであるため、顧客ユーザー自身がこれらのアクセス許可に同意することはできません。 テナント管理者は、テナント内のすべてのユーザーに代わってこのアクセス許可に同意する必要があります。

    1. **[&lt;テナント名&gt; に管理者の同意を与えます]** を選択してから、**[はい]** を選択します。
    2. **[最新の情報に更新]** を選択し、両方のスコープの &lt; に、"**&gt;テナント名 に付与されました**" と表示されていることを確認します。

### 条件付きアクセス MFA ポリシーを作成する

先ほど登録した EditProfileService アプリは、MFA で保護するリソースです。

MFA 条件付きアクセス (CA) ポリシーを作成するには、「[アプリ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers)に多要素認証を追加する」の手順を使用します。 ポリシーを作成する際には、次の設定を使用します。

- **[名前]** には、"MFA ポリシー" を使用します。
- [ターゲット リソース] には、前に登録した EditProfileService API アプリ (*edit-profile-service* など) を選びます。

### サンプル Web API を複製またはダウンロードする

[サンプル アプリは前提条件から](https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial) 複製済みですが、まだ複製していない場合は、GitHub から複製するか、`.zip` ファイルとしてダウンロードできます。

[.zip ファイルをダウンロードする](https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial/archive/refs/heads/main.zip)か、次のコマンドを実行して GitHub からサンプル Web アプリを複製します。

```Console
git clone https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial.git
```

### サンプル Web アプリを構成する

このコード サンプルには、クライアント Web アプリと API アプリ (EditProfileService アプリ) の 2 つのアプリが含まれています。 外部テナント設定を使用するには、これらのアプリを更新する必要があります。 そのためには、次の手順を行ってください。

1. コード エディターで `1-Authentication\7-edit-profile-with-mfa-express\App\authConfig.js` ファイルを開き、次のプレースホルダーを検索します。

    - `Enter_the_Application_Id_Here` し、先ほど登録したクライアント Web アプリのアプリケーション (クライアント) ID に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-customer-tenant-portal#get-the-customer-tenant-details)。
    - `Enter_the_Client_Secret_Here` し、先ほどコピーしたクライアント Web アプリのアプリ シークレット値に置き換えます。
    - `graph_end_point`を Microsoft Graph API エンドポイントである`https://graph.microsoft.com/`に置き換えます。
    - `Add_your_protected_scope_here` を API アプリ (EditProfileService アプリ) スコープに置き換えます。 この値は、*api://{clientId}/EditProfileService.ReadWrite* のようになります。 `{clientId}` は、先ほど登録した *EditProfileService* のアプリケーション (クライアント) ID 値です。
2. コード エディターで `1-Authentication\7-edit-profile-with-mfa-express\Api\authConfig.js` ファイルを開き、次のプレースホルダーを検索します。

    - `Enter_the_Tenant_Subdomain_Here`。これを、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-customer-tenant-portal#get-the-customer-tenant-details)。
    - `Enter_the_Tenant_ID_Here`。これを、テナント ID に置き換えます。 テナント ID がわからない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-customer-tenant-portal#get-the-customer-tenant-details)方法を確認してください。
    - `Enter_the_Edit_Profile_Service_Application_Id_Here` を使用して、 *EditProfileService* アプリケーションのアプリケーション (クライアント) ID 値に置き換えます。
    - `Enter_the_Client_Secret_Here` 前提条件の一部として作成されたクライアント シークレット値に置き換えます。
    - `graph_end_point`を Microsoft Graph API エンドポイントである`https://graph.microsoft.com/`に置き換えます。

### プロジェクトの依存関係をインストールしてアプリを実行する

アプリをテストするには、クライアント アプリとサービス/API アプリの両方にプロジェクトの依存関係をインストールし、次にそれらのアプリを実行します。

1. クライアント アプリを実行するには、ターミナル ウィンドウを開き、次のコマンドを実行します。

    ```Console
    cd 1-Authentication\7-edit-profile-with-mfa-express\App
    npm install
    npm start
    ```
2. サービス/API の編集アプリを実行するには、ディレクトリをサービス/API の編集アプリ (*1-Authentication\7-edit-profile-with-mfa-express\Api*) に変更し、次のコマンドを実行します。

    ```Console
    npm install
    npm start
    ```
3. ブラウザーを開き、http://localhost:3000. に移動します SSL 証明書エラーが発生した場合は、`.env` ファイルを作成し、次の構成を追加します。

    ```Console
    # Use this variable only in the development environment. 
    # Remove the variable when you move the app to the production environment.
    NODE_TLS_REJECT_UNAUTHORIZED='0'
    ```
4. **[サインイン]** ボタンを選択し、サインインします。
5. サインイン ページで、**[メール アドレス]** を入力して **[次へ]** を選択し、**[パスワード]** を入力してから **[サインイン]** を選択します。 アカウントをお持ちでない場合は、**[アカウントをお持ちではない場合、作成できます]** リンクを選択します。これで、サインアップ フローが開始されます。
6. プロファイルを更新するには、**[プロファイル編集]** リンクを選択します。 次のスクリーンショットのようなページが表示されます。

    [Image: ユーザー プロファイルの更新のスクリーンショット。]
7. プロファイルを編集するには、**[プロファイルの編集]** ボタンを選択します。 MFA チャレンジをまだ完了していない場合、アプリから、完了するように求められます。
8. プロファイルの詳細を変更し、**[保存]** ボタンを選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-web-app-sign-in"} -->
## クイック スタート - サンプル Web アプリでユーザーをサインインする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in
- Service: identity-platform
- Article date: 2025-03-10
- Summary: 従業員テナントの従業員または外部テナントの顧客をサインインさせるサンプル Web アプリを構成する方法を示す Web アプリのクイック スタート

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

::: zone pivot="workforce"

このクイック スタートでは、サンプル Web アプリを使用して、従業員テナントでユーザーをサインインさせ、Microsoft Graph API を呼び出す方法を示します。 サンプル アプリでは、 [Microsoft 認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) を使用して認証を処理します。

開始する前に、このページの上部にある **[テナントの種類** の選択] セレクターを使用して、テナントの種類を選択します。 Microsoft Entra ID には、 [従業員](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#workforce-tenants) と [外部](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#external-tenants)の 2 つのテナント構成が用意されています。 従業員テナントの構成は、従業員、内部アプリ、およびその他の組織リソースを対象としています。 外部テナントは、顧客向けのアプリ用です。

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 次の Microsoft Entra ロールには、必要なアクセス許可が含まれます。
    - アプリケーション管理者
    - アプリケーション開発者
- 従業員テナント。 既定のディレクトリを使用するか、 [新しいテナントを設定](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)できます。
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコード エディター。

## [ノード](#tab/node-workforce)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **Web** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:3000/auth/redirect`
    - **フロント チャネル ログアウト URL**: `https://localhost:5001/signout-callback-oidc`
- アプリの登録にクライアント シークレットを追加します。 運用アプリではクライアント シークレットを使用**しないでください**。 代わりに、証明書またはフェデレーション資格情報を使用してください。 詳細については、「 [アプリケーションに資格情報を追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=client-secret)。
- [Node.js](https://nodejs.org/en/download/package-manager)

## [ASP.NET Core](#tab/asp-dot-net-core-workforce)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **Web** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `https://localhost:5001/signin-oidc`
    - **フロント チャネル ログアウト URL**: `https://localhost:5001/signout-callback-oidc`
- アプリの登録に自己署名証明書を追加します。 実稼働アプリでは自己署名証明書を使用**しないでください**。 代わりに、信頼された証明機関またはフェデレーション資格情報の証明書を使用してください。 詳細については、「 [アプリケーションに資格情報を追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=certificate)。 次のコマンドを使用して証明書を作成します。

    ```console
    dotnet dev-certs https -ep ./certificate.crt --trust
    ```
- [.NET 8.0 SDK](https://dotnet.microsoft.com/download/dotnet) の最小要件

## [Python Flask](#tab/python-flask-workforce)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **Web** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:5000/getAToken`
- [Python 3 +](https://www.python.org/downloads/)
- アプリの登録にクライアント シークレットを追加します。 運用アプリではクライアント シークレットを使用**しないでください**。 代わりに、証明書またはフェデレーション資格情報を使用してください。 詳細については、「 [アプリケーションに資格情報を追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=client-secret)。

---

### サンプル Web アプリケーションを複製またはダウンロードする

サンプル アプリケーションを取得するには、GitHub から複製するか、 *.zip* ファイルとしてダウンロードします。

## [ノード](#tab/node-workforce)
- [.zip ファイルをダウンロード](https://github.com/Azure-Samples/ms-identity-node/archive/refs/heads/main.zip)し、名前の長さが 260 文字未満のファイル パスに抽出するか、リポジトリを複製します。
- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-node.git
    ```

## [ASP.NET Core](#tab/asp-dot-net-core-workforce)
- [.zip ファイルをダウンロード](https://github.com/Azure-Samples/ms-identity-docs-code-dotnet/archive/refs/heads/main.zip)し、名前の長さが 260 文字未満のファイル パスに抽出するか、リポジトリを複製します。
- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-docs-code-dotnet.git
    ```

## [Python Flask](#tab/python-flask-workforce)
- [Python コード サンプルをダウンロード](https://github.com/Azure-Samples/ms-identity-docs-code-python/archive/refs/heads/main.zip) し、名前の長さが 260 文字未満のファイル パスに抽出するか、リポジトリを複製します。
- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

```Console
git clone https://github.com/Azure-Samples/ms-identity-docs-code-python/
```

---

### サンプル Web アプリを構成する

サンプル アプリを使用してユーザーをサインインさせるには、アプリとテナントの詳細でユーザーを更新する必要があります。

## [ノード](#tab/node-workforce)
*ms-identity-node* フォルダーで *App/.env* ファイルを開き、次のプレースホルダーを置き換えます。

| Variable | Description | 例 |
| --- | --- | --- |
| `Enter_the_Cloud_Instance_Id_Here` | アプリケーションが登録されている Azure クラウド インスタンス | `https://login.microsoftonline.com/` (末尾のスラッシュを含む) |
| `Enter_the_Tenant_Info_here` | テナント ID またはプライマリ ドメイン | `contoso.microsoft.com` または `aaaabbbb-0000-cccc-1111-dddd2222eeee` |
| `Enter_the_Application_Id_Here` | 登録したアプリケーションのクライアント ID | `00001111-aaaa-2222-bbbb-3333cccc4444` |
| `Enter_the_Client_Secret_Here` | 登録したアプリケーションのクライアント シークレット | `A1b-C2d_E3f.H4i,J5k?L6m!N7o-P8q_R9s.T0u` |
| `Enter_the_Graph_Endpoint_Here` | アプリが呼び出す Microsoft Graph API クラウド インスタンス | `https://graph.microsoft.com/` (末尾のスラッシュを含む) |
| `Enter_the_Express_Session_Secret_Here` | Express セッション Cookie の署名に使用されるランダムな文字列 | `A1b-C2d_E3f.H4...` |

変更を加えた後、ファイルは次のスニペットのようになります。

```env
CLOUD_INSTANCE=https://login.microsoftonline.com/
TENANT_ID=aaaabbbb-0000-cccc-1111-dddd2222eeee
CLIENT_ID=00001111-aaaa-2222-bbbb-3333cccc4444
CLIENT_SECRET=A1b-C2d_E3f.H4...

REDIRECT_URI=http://localhost:3000/auth/redirect
POST_LOGOUT_REDIRECT_URI=http://localhost:3000

GRAPH_API_ENDPOINT=https://graph.microsoft.com/

EXPRESS_SESSION_SECRET=6DP6v09eLiW7f1E65B8k
```

## [ASP.NET Core](#tab/asp-dot-net-core-workforce)
1. IDE で、サンプルを含むプロジェクト フォルダー *ms-identity-docs-code-dotnet\web-app-aspnet* を開きます。
2. *appsettings.json* を開き、ファイルの内容を次のスニペットに置き換えます。

    ```json
    {
    "AzureAd": {
      "Instance": "https://login.microsoftonline.com/",
      "TenantId": "Enter the tenant ID obtained from the Microsoft Entra admin center",
      "ClientId": "Enter the client ID obtained from the Microsoft Entra admin center",
      "ClientCredentials": [
        {
          "SourceType": "StoreWithThumbprint",
          "CertificateStorePath": "CurrentUser/My",
          "CertificateThumbprint": "Enter the certificate thumbprint obtained the Microsoft Entra admin center"
        }   
      ],
      "CallbackPath": "/signin-oidc"
    },
      "DownstreamApis": {
        "MicrosoftGraph" :{
          "BaseUrl": "https://graph.microsoft.com/v1.0/",
          "RelativePath": "me",
          "Scopes": [ 
            "user.read" 
          ]
       }
      },
      "Logging": {
        "LogLevel": {
          "Default": "Information",
          "Microsoft.AspNetCore": "Warning"
        }
      },
      "AllowedHosts": "*"
    }
    ```

    - `TenantId` - アプリケーションが登録されているテナントの識別子。 引用符で囲まれたテキストを、登録済みアプリケーションの概要ページから前に記録した `Directory (tenant) ID` に置き換えます。
    - `ClientId` - アプリケーションの識別子 。クライアントとも呼ばれます。 引用符で囲まれたテキストを、登録済みアプリケーションの概要ページから前に記録した `Application (client) ID` 値に置き換えます。
    - `ClientCertificates` - 自己署名証明書は、アプリケーションでの認証に使用されます。 `CertificateThumbprint`のテキストを、以前に記録した証明書の拇印に置き換えます。

## [Python Flask](#tab/python-flask-workforce)
1. IDE でダウンロードしたアプリケーションを開き、サンプル アプリのルート フォルダーに移動します。

    ```console
    cd flask-web-app
    ```
2. .*env.sample.entra-id* をガイドとして使用して、プロジェクトのルート フォルダーに .*env* ファイルを作成します。

    ```python
    # The following variables are required for the app to run.
    CLIENT_ID=<Enter_your_client_id>
    CLIENT_SECRET=<Enter_your_client_secret>
    AUTHORITY=<Enter_your_authority_url>
    ```

    - `CLIENT_ID`の値を、概要ページで使用可能な登録済みアプリケーションのアプリケーション **(クライアント) ID** に設定します。
    - `CLIENT_SECRET`の値を、登録済みアプリケーションの**証明書とシークレット**で作成したクライアント シークレットに設定します。
    - `AUTHORITY`の値を`https://login.microsoftonline.com/<TENANT_GUID>`に設定します。 **ディレクトリ (テナント) ID** は、アプリ登録の概要ページで使用できます。

    環境変数は *app\_config.py*で参照され、ソース管理から除外するために別の *.env* ファイルに保持されます。 指定された *.gitignore* ファイルを使用すると、 *.env* ファイルがチェックインされなくなります。

---

### サンプル Web アプリの実行とテスト

サンプル アプリを構成しました。 実行とテストに進むことができます。

## [ノード](#tab/node-workforce)
1. サーバーを起動するには、プロジェクト ディレクトリ内から次のコマンドを実行します。

    ```console
    cd App
    npm install
    npm start
    ```
2. `http://localhost:3000/` にアクセスします。
3. [ **サインイン** ] を選択してサインイン プロセスを開始します。

初めてサインインすると、アプリケーションによるサインインとプロファイルへのアクセスを許可する同意を求められます。 正常にサインインすると、アプリケーションのホーム ページにリダイレクトされます。

#### アプリのしくみ

このサンプルでは、localhost のポート 3000 で Web サーバーをホストします。 Web ブラウザーがこのアドレスにアクセスすると、アプリによってホーム ページがレンダリングされます。 ユーザーが **[サインイン**] を選択すると、アプリは MSAL Node ライブラリによって生成された URL を使用して、ブラウザーを Microsoft Entra サインイン画面にリダイレクトします。 ユーザーの同意後、ブラウザーは ID とアクセス トークンと共にユーザーをアプリケーションのホーム ページにリダイレクトします。

## [ASP.NET Core](#tab/asp-dot-net-core-workforce)
1. プロジェクト ディレクトリで、ターミナルを使用して次のコマンドを入力します。

    ```console
    cd ms-identity-docs-code-dotnet/web-app-aspnet
    dotnet run
    ```
2. ターミナルに表示される `https` URL ( `https://localhost:5001`など) をコピーし、ブラウザーに貼り付けます。 プライベートまたはシークレット のブラウザー セッションを使用することをお勧めします。
3. 手順に従い、Microsoft アカウントでサインインするために必要な詳細を入力します。 ワンタイム パスコードを送信できるように、電子メール アドレスを指定するように求められます。 メッセージが表示されたら、コードを入力します。
4. アプリケーションは、アクセス権を付与したデータへのアクセスを維持し、サインインしてプロファイルを読み取るアクセス許可を要求します。 [ **承諾]** を選択します。 次のスクリーンショットが表示されます。 これは、アプリケーションにサインインしていて、Microsoft Graph API からプロファイルの詳細を表示していることを示します。

    [Image: API 呼び出しの結果を示すスクリーンショット。]

#### アプリケーションからサインアウトする

1. ページの右上隅にある **[サインアウト** ] リンクを見つけて選択します。
2. サインアウトするアカウントを選択するように求められます。 サインインに使用したアカウントを選択します。
3. サインアウトしたことを示すメッセージが表示されます。ブラウザー ウィンドウを閉じることができるようになりました。

## [Python Flask](#tab/python-flask-workforce)
1. アプリの仮想環境を作成します。

    - **Windows** の場合は、次のコマンドを実行します。

    ```console
    py -m venv .venv
    .venv\scripts\activate
    ```

    - **macOS/Linux** の場合は、次のコマンドを実行します。

    ```console
    python3 -m venv .venv
    source .venv/bin/activate
    ```
2. `pip`を使用して要件をインストールします。

    ```Console
    pip install -r requirements.txt
    ```
3. コマンド ラインからアプリを実行します。 前に構成したリダイレクト URI と同じポートでアプリが実行されていることを確認します。

    ```Console
    flask run --debug --host=localhost --port=5000
    ```
4. ターミナルに表示される https URL ( https://localhost:5000など) をコピーし、ブラウザーに貼り付けます。 プライベートまたはシークレット のブラウザー セッションを使用することをお勧めします。
5. 手順に従い、Microsoft アカウントでサインインするために必要な詳細を入力します。 サインインするための電子メール アドレスとパスワードを指定するように求められます。
6. アプリケーションは、アクセスを許可するデータへのアクセスを維持し、サインインしてプロファイルを読み取るアクセス許可を要求します (スクリーンショットを参照)。 [ **承諾]** を選択します。

    [Image: 必要なアクセス許可にアクセスするための同意を要求するサンプル アプリを示す図。]
7. 次のスクリーンショットが表示されます。これは、アプリケーションに正常にサインインしたことを示しています。

[Image: サンプル アプリがユーザーに正常にサインインした方法を示す図。]

#### アプリのしくみ

次の図は、サンプル アプリのしくみを示しています。

[Image: このクイック スタートで生成されたサンプル アプリのしくみを示す図。]

1. アプリケーションは[、`identity` パッケージ](https://github.com/azure-samples/ms-identity-python)を使用して、Microsoft ID プラットフォームからアクセス トークンを取得します。 このパッケージは、Web アプリでの認証と承認を簡素化するために、Python 用 Microsoft Authentication Library (MSAL) の上に構築されています。
2. 前の手順で取得したアクセス トークンは、Microsoft Graph API を呼び出すときにユーザーを認証するためのベアラー トークンとして使用されます。

---

::: zone-end

::: zone pivot="external"

このクイック スタートでは、サンプル Web アプリを使用して、外部テナントのユーザーをサインインさせる方法を示します。 サンプル アプリでは、 [Microsoft 認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) を使用して認証を処理します。

開始する前に、このページの上部にある **[テナントの種類** の選択] セレクターを使用して、テナントの種類を選択します。 Microsoft Entra ID には、 [従業員](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#workforce-tenants) と [外部](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations#external-tenants)の 2 つのテナント構成が用意されています。 従業員テナントの構成は、従業員、内部アプリ、およびその他の組織リソースを対象としています。 外部テナントは、顧客向けのアプリ用です。

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 次の Microsoft Entra ロールには、必要なアクセス許可が含まれます。
    - アプリケーション管理者
    - アプリケーション開発者
- 外部テナント。 作成するには、次の方法から選択します。
    - [Microsoft Entra 外部 ID 拡張機能](https://aka.ms/ciamvscode/samples/marketplace)を使用して、Visual Studio Code で外部テナントを直接設定します。 *(おすすめ)*
    - Microsoft Entra 管理センターで[新しい外部テナントを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)します。
- ユーザー フロー。 詳細については、 [外部テナント内のアプリのセルフサービス サインアップ ユーザー フローを作成する方法](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)に関するページを参照してください。 このユーザー フローは、複数のアプリケーションに使用できます。
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコード エディター。

## [ノード](#tab/node-external)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **Web** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:3000/auth/redirect`
    - **フロント チャネル ログアウト URL**: `https://localhost:5001/signout-callback-oidc`
- アプリの登録にクライアント シークレットを追加します。 運用アプリではクライアント シークレットを使用**しないでください**。 代わりに、証明書またはフェデレーション資格情報を使用してください。 詳細については、「 [アプリケーションに資格情報を追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=client-secret)。
- [アプリケーションをユーザー フローに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)
- [Node.js](https://nodejs.org/en/download/package-manager)

## [ASP.NET Core](#tab/asp-dot-net-core-external)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **Web** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `https://localhost:5001/signin-oidc`
    - **フロント チャネル ログアウト URL**: `https://localhost:5001/signout-callback-oidc`
- アプリの登録にクライアント シークレットを追加します。 運用アプリではクライアント シークレットを使用**しないでください**。 代わりに、証明書またはフェデレーション資格情報を使用してください。 詳細については、「 [アプリケーションに資格情報を追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=client-secret)。
- [アプリケーションをユーザー フローに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)
- [.NET 8.0 SDK の](https://dotnet.microsoft.com/download/dotnet)最小バージョン。

## [Python - Django](#tab/python-django-external)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **Web** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:5000/getAToken`
- [アプリケーションをユーザー フローに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)
- [Python 3 +](https://www.python.org/downloads/)
- アプリの登録にクライアント シークレットを追加します。 運用アプリではクライアント シークレットを使用**しないでください**。 代わりに、証明書またはフェデレーション資格情報を使用してください。 詳細については、「 [アプリケーションに資格情報を追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=client-secret)。

## [Python Flask](#tab/python-flask-external)
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **Web** プラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **リダイレクト URI**: `http://localhost:5000/getAToken`
- [アプリケーションをユーザー フローに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)
- [Python 3 +](https://www.python.org/downloads/)
- アプリの登録にクライアント シークレットを追加します。 運用アプリではクライアント シークレットを使用**しないでください**。 代わりに、証明書またはフェデレーション資格情報を使用してください。 詳細については、「 [アプリケーションに資格情報を追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=client-secret)。

---

### サンプル Web アプリケーションを複製またはダウンロードする

## [ノード](#tab/node-external)
サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial.git
    ```
- または、 [サンプル .zip ファイルをダウンロード](https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial/archive/refs/heads/main.zip)し、名前の長さが 260 文字未満のファイル パスに抽出します。

#### プロジェクトの依存関係をインストールする

1. コンソール ウィンドウを開き、Node.js サンプル アプリを含むディレクトリに移動します。

    ```console
    cd 1-Authentication\5-sign-in-express\App
    ```
2. 次のコマンドを実行して、アプリの依存関係をインストールします。

    ```console
    npm install
    ```

## [ASP.NET Core](#tab/asp-dot-net-core-external)
サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial.git
    ```
- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-ciam-dotnet-tutorial/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

## [Python - Django](#tab/python-django-external)
サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-docs-code-python.git
    ```
- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-docs-code-python/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

#### プロジェクトの依存関係をインストールする

1. コンソール ウィンドウを開き、Flask サンプル Web アプリを含むディレクトリに移動します。

    ```console
    cd django-web-app
    ```
2. 仮想環境を設定します。

    - **Windows** の場合は、次のコマンドを実行します。

    ```console
    py -m venv .venv
    .venv\scripts\activate
    ```

    - **macOS/Linux** の場合は、次のコマンドを実行します。

    ```console
    python3 -m venv .venv
    source .venv/bin/activate
    ```
3. アプリの依存関係をインストールするには、次のコマンドを実行します。

    ```console
    python3 -m pip install -r requirements.txt
    ```

## [Python Flask](#tab/python-flask-external)
サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-docs-code-python.git
    ```
- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-docs-code-python/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

#### プロジェクトの依存関係をインストールする

1. コンソール ウィンドウを開き、Flask サンプル Web アプリを含むディレクトリに移動します。

    ```console
    cd flask-web-app
    ```
2. 仮想環境を設定します。

    - **Windows** の場合は、次のコマンドを実行します。

    ```console
    py -m venv .venv
    .venv\scripts\activate
    ```

    - **macOS/Linux** の場合は、次のコマンドを実行します。

    ```console
    python3 -m venv .venv
    source .venv/bin/activate
    ```
3. アプリの依存関係をインストールするには、次のコマンドを実行します。

    ```console
    python3 -m pip install -r requirements.txt
    ```

---

### サンプル Web アプリを構成する

サンプル アプリを使用してユーザーをサインインさせるには、アプリとテナントの詳細でユーザーを更新する必要があります。

## [ノード](#tab/node-external)
1. コード エディターで、 *App\authConfig.jsファイルを * 開きます。
2. プレースホルダーを見つけます。

    - `Enter_the_Application_Id_Here` をクリックし、先ほど登録したアプリのアプリケーション (クライアント) ID に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com`されている場合は、 `contoso`を使用します。 テナント名がない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-customer-tenant-portal#get-the-customer-tenant-details)る方法について説明します。
    - `Enter_the_Client_Secret_Here` を選択し、先ほどコピーしたアプリ シークレットの値に置き換えます。

## [ASP.NET Core](#tab/asp-dot-net-core-external)
1. ASP.NET Core サンプル アプリを含むルート ディレクトリに移動します。

    ```console
    cd 1-Authentication\1-sign-in-aspnet-core-mvc
    ```
2. *appsettings.json* ファイルを開きます。
3. **[機関]** で、`Enter_the_Tenant_Subdomain_Here`を見つけて、テナントのサブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが *caseyjensen@onmicrosoft.com*されている場合、入力する必要がある値は *casyjensen です*。
4. `Enter_the_Application_Id_Here`値を見つけ、Microsoft Entra 管理センターに登録したアプリのアプリケーション ID (clientId) に置き換えます。
5. `Enter_the_Client_Secret_Here`を設定したクライアント シークレットの値に置き換えます。

## [Python - Django](#tab/python-django-external)
1. Visual Studio Code または使用しているエディターでプロジェクト ファイルを開きます。
2. .*env.sample.external-id* ファイルをガイドとして使用して、プロジェクトのルート フォルダーに .*env* ファイルを作成します。
3. *.env* ファイルで、次の環境変数を指定します。

    1. `CLIENT_ID` これは、先ほど登録したアプリのアプリケーション (クライアント) ID です。
    2. `CLIENT_SECRET` これは、前にコピーしたアプリ シークレットの値です。
    3. `AUTHORITY` トークン機関を識別する URL です。 これは *、{subdomain}.ciamlogin.com/{subdomain}.onmicrosoft.com https://* 形式である必要があります。 *サブドメイン*をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com`されている場合は、 `contoso`を使用します。 テナント サブドメインがない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。
    4. `REDIRECT_URI` これは、前に登録したリダイレクト URI と同じにする必要があります。これは、構成と一致する必要があります。

## [Python Flask](#tab/python-flask-external)
1. Visual Studio Code または使用しているエディターでプロジェクト ファイルを開きます。
2. .*env.sample.external-id* ファイルをガイドとして使用して、プロジェクトのルート フォルダーに .*env* ファイルを作成します。
3. *.env* ファイルで、次の環境変数を指定します。

    - `CLIENT_ID` これは、先ほど登録したアプリのアプリケーション (クライアント) ID です。
    - `CLIENT_SECRET` これは、前にコピーしたアプリ シークレットの値です。
    - `AUTHORITY` トークン機関を識別する URL です。 これは *、{subdomain}.ciamlogin.com/{subdomain}.onmicrosoft.com https://* 形式である必要があります。 *サブドメイン*をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com`されている場合は、 `contoso`を使用します。 テナント サブドメインがない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-customer-tenant-portal#get-the-customer-tenant-details)る方法について説明します。
4. リダイレクト URI が適切に構成されていることを確認します。 前に登録したリダイレクト URI は、構成と一致している必要があります。 このサンプルでは、既定でリダイレクト URI パスが `/getAToken`に設定されます。 この構成は、 *REDIRECT\_PATHとしてapp\_config.py* ファイル内 *にあります*。

---

### サンプル Web アプリの実行とテスト

## [ノード](#tab/node-external)
Web アプリ Node.js サンプルをテストできるようになりました。 Node.js サーバーを起動し、 `http://localhost:3000`のブラウザーからアクセスする必要があります。

1. ターミナルで、次のコマンドを実行します。

    ```console
    npm start 
    ```
2. ブラウザーを開き、 `http://localhost:3000`に移動します。 次のスクリーンショットのようなページが表示されます。

    [Image: ノード Web アプリへのサインインのスクリーンショット。]
3. ページの読み込みが完了したら、メッセージが表示されたら **[サインイン** ] を選択します。
4. サインイン ページで、 **メール アドレス**を入力し、[ **次へ**] を選択し、 **パスワード**を入力して、[ **サインイン**] を選択します。 アカウントをお持ちでない場合は、[アカウントなし] を選択してください **。1 つの** リンクを作成して、サインアップ フローを開始します。
5. サインアップ オプションを選択した場合は、メール、ワンタイム パスコード、新しいパスワード、その他のアカウントの詳細を入力した後、サインアップ フロー全体を完了します。 次のスクリーンショットのようなページが表示されます。 サインイン オプションを選択すると、同様のページが表示されます。

    [Image: ビュー ID トークン要求のスクリーンショット。]
6. [ **サインアウト** ] を選択してユーザーを Web アプリからサインアウトするか、[ **ID トークン要求の表示** ] を選択して Microsoft Entra から返された ID トークン要求を表示します。

#### 動作方法

ユーザーが **[サインイン** ] リンクを選択すると、アプリは認証要求を開始し、ユーザーを Microsoft Entra 外部 ID にリダイレクトします。 表示されるサインインまたはサインアップ ページで、ユーザーが正常にサインインするか、アカウントを作成すると、Microsoft Entra External ID は ID トークンをアプリに返します。 アプリは ID トークンを検証し、要求を読み取り、セキュリティで保護されたページをユーザーに返します。

ユーザーが **[サインアウト** ] リンクを選択すると、アプリはセッションをクリアし、ユーザーを Microsoft Entra External ID サインアウト エンドポイントにリダイレクトして、ユーザーがサインアウトしたことを通知します。

実行したサンプルと同様のアプリを構築する場合は、「 [独自の Node.js Web アプリケーションの記事でユーザーをサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-web-app-node-sign-in-prepare-tenant) させる」の手順を実行します。

## [ASP.NET Core](#tab/asp-dot-net-core-external)
1. シェルまたはコマンド ラインから、次のコマンドを実行します。

    ```console
    dotnet run
    ```
2. Web ブラウザーを開き、 `https://localhost:7274`に移動します。
3. 外部テナントに登録されているアカウントでサインインします。
4. サインインすると、次のスクリーンショットに示すように、[ **サインアウト** ] ボタンの横に表示名が表示されます。

    [Image: ASP.NET Core Web アプリへのサインインのスクリーンショット。]
5. アプリケーションからサインアウトするには、[ **サインアウト** ] ボタンを選択します。

## [Python - Django](#tab/python-django-external)
アプリを実行して、サインイン エクスペリエンスを確認します。

1. ターミナルで、次のコマンドを実行します。

    ```console
    python manage.py runserver localhost:5000                                             
    ```

    選択したポート番号を使用できます。
2. ブラウザーを開き、 `http://localhost:5000`に移動します。 次のスクリーンショットのようなページが表示されます。

    [Image: Django Web アプリのサンプル サインイン ページのスクリーンショット。]
3. ページの読み込みが完了したら、[ **サインイン** ] リンクを選択します。 サインインするように求められます。
4. サインイン ページで、 **メール アドレス**を入力し、[ **次へ**] を選択し、 **パスワード**を入力して、[ **サインイン**] を選択します。 アカウントをお持ちでない場合は、[アカウントなし] を選択してください **。1 つの** リンクを作成して、サインアップ フローを開始します。
5. サインアップ オプションを選択した場合は、サインアップ フローを実行します。 メール、ワンタイム パスコード、新しいパスワード、その他のアカウントの詳細を入力して、サインアップ フロー全体を完了します。
6. サインインまたはサインアップすると、Web アプリにリダイレクトされます。 次のスクリーンショットのようなページが表示されます。

    [Image: 認証が成功した後の Flask Web アプリサンプルのスクリーンショット。]
7. **[ログアウト**] を選択してユーザーを Web アプリからサインアウトするか、[**ダウンストリーム API を呼び出**して Microsoft Graph エンドポイントを呼び出す] を選択します。

#### 動作方法

ユーザーが **[サインイン** ] リンクを選択すると、アプリは認証要求を開始し、ユーザーを Microsoft Entra 外部 ID にリダイレクトします。 ユーザーは、表示されるページにサインインするか、ページにサインアップします。 必要な資格情報を入力し、必要なスコープに同意すると、Microsoft Entra External ID によって、承認コードを使用してユーザーが Web アプリにリダイレクトされます。 その後、Web アプリはこの承認コードを使用して、Microsoft Entra 外部 ID からトークンを取得します。

ユーザーが **[ログアウト** ] リンクを選択すると、アプリはセッションをクリアし、ユーザーを Microsoft Entra External ID サインアウト エンドポイントにリダイレクトして、ユーザーがサインアウトしたことを通知します。その後、ユーザーは Web アプリにリダイレクトされます。

## [Python Flask](#tab/python-flask-external)
アプリを実行して、サインイン エクスペリエンスを確認します。

1. ターミナルで、次のコマンドを実行します。

    ```console
    python3 -m flask run --debug --host=localhost --port=3000
    ```

    選択したポートを使用できます。 これは、先ほど登録したリダイレクト URI のポートに似ています。
2. ブラウザーを開き、 `http://localhost:3000`に移動します。 次のスクリーンショットのようなページが表示されます。

    [Image: Flask Web アプリのサンプル サインイン ページのスクリーンショット。]
3. ページの読み込みが完了したら、[ **サインイン** ] リンクを選択します。 サインインするように求められます。
4. サインイン ページで、 **メール アドレス**を入力し、[ **次へ**] を選択し、 **パスワード**を入力して、[ **サインイン**] を選択します。 アカウントをお持ちでない場合は、[アカウントなし] を選択してください **。1 つの** リンクを作成して、サインアップ フローを開始します。
5. サインアップ オプションを選択した場合は、sign-uo フローを実行します。 サインアップ フロー全体を完了するには、メール、ワンタイム パスコード、新しいパスワード、その他のアカウントの詳細を入力します。
6. サインインまたはサインアップすると、Web アプリにリダイレクトされます。 次のスクリーンショットのようなページが表示されます。

    [Image: 認証が成功した後の Flask Web アプリサンプルのスクリーンショット。]
7. **[ログアウト**] を選択してユーザーを Web アプリからサインアウトするか、[**ダウンストリーム API を呼び出**して Microsoft Graph エンドポイントを呼び出す] を選択します。

#### 動作方法

ユーザーが **[サインイン** ] リンクを選択すると、アプリは認証要求を開始し、ユーザーを Microsoft Entra 外部 ID にリダイレクトします。 ユーザーは、表示されるページにサインインするか、ページにサインアップします。 必要な資格情報を入力し、必要なスコープに同意すると、Microsoft Entra External ID によって、承認コードを使用してユーザーが Web アプリにリダイレクトされます。 その後、Web アプリはこの承認コードを使用して、Microsoft Entra 外部 ID からトークンを取得します。

ユーザーが **[ログアウト** ] リンクを選択すると、アプリはセッションをクリアし、ユーザーを Microsoft Entra External ID サインアウト エンドポイントにリダイレクトして、ユーザーがサインアウトしたことを通知します。その後、ユーザーは Web アプリにリダイレクトされます。

---

### 関連コンテンツ

## [ノード](#tab/node-external)
- [Node.js Web アプリケーションでユーザーをサインインする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-web-app-node-sign-in-prepare-tenant)
- [クイック スタート - サンプル Node.js Web アプリでユーザーをサインインさせ、Web API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-node-sign-in-call-api)
- [クイック スタート - サンプル Node.js Web アプリでプロファイルを編集する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-node-sign-in-edit-profile)

## [ASP.NET Core](#tab/asp-dot-net-core-external)
- [マルチパートチュートリアルシリーズを使用して、この ASP.NET Web アプリケーションをゼロから構築する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-web-app-dotnet-sign-in-prepare-app)
- [パスワード リセットを有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers)
- [既定のブランドをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers)

## [Python - Django](#tab/python-django-external)
- [サンプルの Flask Web アプリケーションを使用してユーザーをサインインする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/sample-web-app-python-flask-sign-in)
- [パスワード リセットを有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers)
- [既定のブランドをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers)

## [Python Flask](#tab/python-flask-external)
- [パスワード リセットを有効にする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers)
- [既定のブランドをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-customize-branding-customers)

---

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/reference-app-manifest"} -->
## アプリ マニフェストについて (Azure AD Graph 形式) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest
- Service: identity-platform
- Article date: 2025-04-15
- Summary: Microsoft Entra テナントでのアプリケーションの ID 構成を表す Microsoft Entra アプリ マニフェストについて説明します。

アプリケーション マニフェストには、Microsoft ID プラットフォーム内のアプリケーション オブジェクトのすべての属性の定義が含まれます。 それは、アプリケーション オブジェクトを更新するメカニズムとしても機能します。 Application エンティティとそのスキーマの詳細については、 [Graph API アプリケーション エンティティのドキュメントを参照してください](https://learn.microsoft.com/ja-jp/graph/api/resources/application)。

アプリの属性は、Microsoft Entra 管理センターから構成することも、 [Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/application) または [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/?view=graph-powershell-1.0&preserve-view=true) を使用してプログラムで構成することもできます。 ただし、一部のシナリオでは、アプリ マニフェストを編集してアプリの属性を構成する必要があります。 これらのシナリオには、以下が含まれます。

- Microsoft Entra マルチテナントと個人用 Microsoft アカウントの設定でアプリを登録した場合、サポートされる Microsoft アカウントを UI で変更することはできません。 代わりに、アプリケーション マニフェスト エディターを使用して、サポートされるアカウントの種類を変更する必要があります。
- アプリでサポートされるアクセス許可とロールを定義するには、アプリケーション マニフェストを変更する必要があります。

### アプリ マニフェストを構成する

アプリケーション マニフェストを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション開発者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)としてサインインします。
2. **Entra ID**&gt;**App 登録に移動します**。
3. 構成するアプリを選択します。
4. アプリの **[管理** ] セクションで、[マニフェスト] を選択 **します**。 Web ベースのマニフェスト エディターが開き、マニフェストを編集できます。 必要に応じて、[ **ダウンロード** ] を選択してマニフェストをローカルで編集し、[ **アップロード]** を使用してアプリケーションに再適用できます。

### マニフェスト リファレンス

ここでは、アプリケーション マニフェストで見られる属性について説明します。

#### id 属性

| 鍵 | 値の型 |
| --- | --- |
| 身分証明書 | 糸 |

ディレクトリ内のアプリの一意識別子。 この ID は、いずれかのプロトコル トランザクション内のアプリを識別するために使用される識別子ではありません。 これは、ディレクトリ クエリ内のオブジェクトを参照するために使用されます。

例:

```json
"id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"
```

#### acceptMappedClaims 属性

| 鍵 | 値の型 |
| --- | --- |
| acceptMappedClaims | Null 許容ブール値 |

[`apiApplication` リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/apiapplication#properties)に関するドキュメントに記載されているように、これにより、アプリケーションはカスタム署名キーを指定せずに[要求マッピング](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)を使用できます。 トークンを受信するアプリケーションは、クレーム値が Microsoft Entra ID によって正式に発行され、改ざんできないという事実に依存しています。 ただし、クレームマッピング ポリシーによってトークンの内容を変更すると、この前提は通用しなくなります。 悪意のある目的で作成されたクレームマッピング ポリシーからアプリケーションを保護するため、アプリケーションでは、クレームマッピング ポリシーの作成者がトークンを変更したことを、明示的に確認する必要があります。

警告

マルチテナント アプリでは `acceptMappedClaims` プロパティを `true` に設定しないでください。悪意のある者がアプリのクレームマッピング ポリシーを作成することを許可してしまう場合があります。

例:

```json
"acceptMappedClaims": true
```

#### requestedAccessTokenVersion 属性

| 鍵 | 値の型 |
| --- | --- |
| requestedAccessTokenVersion | Null 許容の Int32 |

リソースで想定されているアクセス トークンのバージョンを指定します。 このパラメーターを使うと、アクセス トークンを要求するために使用されたエンドポイントまたはクライアントとは関係なく、生成される JWT のバージョンと形式を変更できます。

使用されるエンドポイント (v1.0 または v2.0) はクライアントによって選択され、id\_token のバージョンにのみ影響します。 リソースでは、サポートされるアクセス トークンの形式を明示的に示すように、`requestedAccessTokenVersion` を構成する必要があります。

`requestedAccessTokenVersion` に指定できる値は、1、2、または null です。 値が null の場合、このパラメーターの既定値は 1 であり、v1.0 のエンドポイントに対応します。

`signInAudience` が `AzureADandPersonalMicrosoftAccount` の場合は、値は `2` である必要があります。

例:

```json

"requestedAccessTokenVersion": 2

```

#### addIns 属性

| 鍵 | 値の型 |
| --- | --- |
| addIns | コレクション |

使用するサービスが特定のコンテキストでアプリの呼び出しに使用できるカスタム動作を定義します。 たとえば、ファイル ストリームをレンダリングできるアプリケーションでは、その "FileHandler" 機能の `addIns` プロパティを設定できます。 このパラメーターを使うと、Microsoft 365 などのサービスで、ユーザーが作業中のドキュメントのコンテキストでアプリケーションを呼び出すことができます。

例:

```json
"addIns": [
    {
        "id": "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
        "type": " FileHandler",
        "properties": [
            {
                "key": "version",
                "value": "2"
            }
        ]
    }
]
```

#### allowPublicClient 属性

| 鍵 | 値の型 |
| --- | --- |
| allowPublicClient | ブール値 |

フォールバック アプリケーションの種類を指定します。 Microsoft Entra ID は、既定では replyUrlsWithType からアプリケーションの種類を推測します。 Microsoft Entra ID がクライアント アプリの種類を判断できない特定のシナリオがあります。 たとえば、このようなシナリオの 1 つは、HTTP 要求が URL リダイレクトなしで行われる [ROPC](https://tools.ietf.org/html/rfc6749#section-4.3) フローです)。 そのような場合、Microsoft Entra ID では、このプロパティの値に基づいて、アプリケーションの種類を解釈します。 この値が true に設定されている場合、フォールバック アプリケーションの種類は、モバイル デバイス上で実行されているインストール済みのアプリなど、パブリック クライアントとして設定されます。 既定値は false です。これは、フォールバック アプリケーションの種類が、Web アプリなどの機密クライアントであることを意味します。

例:

```json
"allowPublicClient": false
```

#### appId 属性

| 鍵 | 値の型 |
| --- | --- |
| アプリID | 糸 |

Microsoft Entra ID によってアプリに割り当てられた一意識別子を指定します。

例:

```json
"appId": "00001111-aaaa-2222-bbbb-3333cccc4444"
```

#### appRoles 属性

| 鍵 | 値の型 |
| --- | --- |
| appRoles | コレクション |

アプリが宣言する可能性のあるロールのコレクションを指定します。 これらのロールは、ユーザー、グループ、またはサービス プリンシパルに割り当てることができます。 詳細な例と情報については、「 [アプリケーションにアプリ ロールを追加し、トークンで受け取る」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)。

アプリ ロールと公開された委任されたアクセス許可スコープは、アプリケーションまたはサービス プリンシパルごとに既定の制限である 700 個のアクセス許可定義を共有します。 無効な定義もカウントされます。 制限を超える既存のオブジェクトのルールと動作のカウントについては、「 [アプリ ロール](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-role-limits) の制限」を参照してください。

例:

```json
"appRoles": [
    {
        "allowedMemberTypes": [
            "User"
        ],
        "description": "Read-only access to device information",
        "displayName": "Read Only",
        "id": "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb",
        "isEnabled": true,
        "value": "ReadOnly"
    }
]
```

#### errorUrl 属性

| 鍵 | 値の型 |
| --- | --- |
| エラーURL | 糸 |

サポートされていません。

#### groupMembershipClaims 属性

| 鍵 | 値の型 |
| --- | --- |
| groupMembershipClaims | 糸 |

アプリが期待する、ユーザーまたは OAuth 2.0 アクセス トークンで発行される `groups` 要求を構成します。 この属性を設定するには、次のいずれかの有効な文字列値を使用します。

- `"None"`
- `"SecurityGroup"`(セキュリティ グループと Microsoft Entra ロールの場合)
- `"ApplicationGroup"` (このオプションには、アプリケーションに割り当てられているグループのみが含まれます)
- `"DirectoryRole"` (ユーザーがメンバーになっている Microsoft Entra ディレクトリ ロールを取得します)
- `"All"` (これは、サインイン ユーザーがメンバーになっているすべてのセキュリティ グループ、配布グループ、Microsoft Entra ディレクトリ ロールを取得します)。

例:

```json
"groupMembershipClaims": "SecurityGroup"
```

#### optionalClaims 属性

| 鍵 | 値の型 |
| --- | --- |
| optionalClaims | 糸 |

この特定のアプリのセキュリティ トークン サービスによってトークンで返される省略可能な要求。

個人アカウントと Microsoft Entra ID の両方をサポートするアプリでは、省略可能な要求を使用できません。 ただし、v2.0 エンドポイントを使用して Microsoft Entra ID のみに登録されたアプリは、マニフェストで要求した省略可能な要求を取得することができます。 詳細については、「 [省略可能な要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)」を参照してください。

例:

```json
"optionalClaims": null
```

#### identifierUris 属性

| 鍵 | 値の型 |
| --- | --- |
| identifierUris | 文字列配列 |

Microsoft Entra テナントまたは検証済みの顧客所有ドメイン内で Web アプリを一意に識別するユーザー定義 URI。 アプリケーションをリソース アプリとして使用する場合、リソースを一意に識別してアクセスするために、identifierUri 値が使用されます。 パブリック クライアント アプリケーションは、identifierUris の値を持つことはできません。

次の API および HTTP スキームベースのアプリケーション ID URI 形式がサポートされます。 表の後の一覧の説明に従って、プレースホルダーの値を置き換えてください。

| サポートされるアプリケーション IDURI 形式 | アプリ ID URI の例 |
| --- | --- |
| *api://&lt;appId&gt;* | *api://aaaabbbb-0000-cccc-1111-dddd2222eeee* |
| *api://&lt;tenantId&gt;/&lt;appId&gt;* | *api://aaaabbbb-0000-cccc-1111-dddd2222eeee/00001111-aaaa-2222-bbbb-3333cccc4444* |
| *api://&lt;tenantId&gt;/&lt;string&gt;* | *api://aaaabbbb-0000-cccc-1111-dddd2222eeee/api* |
| *api://&lt;string&gt;/&lt;appId&gt;* | *api://productapi/00001111-aaaa-2222-bbbb-3333cccc4444* |
| *https://&lt;tenantInitialDomain&gt;.onmicrosoft.com/&lt;string&gt;* | *`https://contoso.onmicrosoft.com/productsapi`* |
| *https://&lt;verifiedCustomDomain&gt;/&lt;string&gt;* | *`https://contoso.com/productsapi`* |
| *https://&lt;string&gt;.&lt;verifiedCustomDomain&gt;* | *`https://product.contoso.com`* |
| *https://&lt;string&gt;.&lt;verifiedCustomDomain&gt;/&lt;string&gt;* | *`https://product.contoso.com/productsapi`* |
| *api://&lt;string&gt;.&lt;verifiedCustomDomainOrInitialDomain&gt;/&lt;string&gt;* | *`api://contoso.com/productsapi`* |

- * &lt;appId&gt;* - アプリケーション オブジェクトのアプリケーション識別子 (appId) プロパティ。
- * &lt;string&gt;* - ホストまたは API パス セグメントの文字列値。
- * &lt;tenantId&gt;* - Azure 内のテナントを表すために Azure によって生成された GUID。
- * &lt;tenantInitialDomain&gt;* - *&lt;tenantInitialDomain&gt;.onmicrosoft.com*。*ここで、&lt;tenantInitialDomain&gt; はテナント*の作成時にテナント作成者が指定した初期ドメイン名です。
- * &lt;verifiedCustomDomain&gt;* - Microsoft Entra テナント用に構成 [された検証済みのカスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain) 。

注

*api://* スキームを使用する場合は、"api://" の直後に文字列値を追加します。 たとえば、 *api://&lt;string&gt;*。 その文字列値には、GUID または任意の文字列を指定できます。 GUID 値を追加する場合は、アプリ ID またはテナント ID と一致する必要があります。 文字列値を使用する場合は、テナントの検証済みのカスタム ドメインまたは初期ドメインを使用する必要があります。 *api://&lt;appId&gt;* を使用することをお勧めします。

重要

アプリケーション ID URI の値は、スラッシュ "/" 文字で終わる必要があります。

重要

アプリケーション ID URI の値は、テナント内で一意である必要があります。

例:

```json
"identifierUris": "https://contoso.onmicrosoft.com/00001111-aaaa-2222-bbbb-3333cccc4444"
```

#### informationalUrls 属性

| 鍵 | 値の型 |
| --- | --- |
| informationalUrls | 糸 |

アプリのサービス利用規約とプライバシーに関する声明へのリンクを指定します。 サービス利用規約とプライバシーに関する声明は、ユーザーの同意エクスペリエンスからユーザーに提示されます。 詳細については、「 [方法: 登録済みの Microsoft Entra アプリのサービス利用規約とプライバシーに関する声明を追加する」](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-terms-of-service-privacy-statement)を参照してください。

例:

```json
"informationalUrls": {
    "termsOfService": "https://MyRegisteredApp/termsofservice",
    "support": "https://MyRegisteredApp/support",
    "privacy": "https://MyRegisteredApp/privacystatement",
    "marketing": "https://MyRegisteredApp/marketing"
}
```

#### keyCredentials 属性

| 鍵 | 値の型 |
| --- | --- |
| keyCredentials | コレクション |

アプリで割り当てられた資格情報、文字列ベースの共有シークレット、および X.509 証明書への参照を保持します。 これらの資格情報は、アクセス トークンを要求するときに使用されます (そのアプリがリソースとしてではなく、クライアントとして機能している場合)。

例:

```json
"keyCredentials": [
    {
        "customKeyIdentifier": null,
        "endDateTime": "2018-09-13T00:00:00Z",
        "keyId": "<guid>",
        "startDateTime": "2017-09-12T00:00:00Z",
        "type": "AsymmetricX509Cert",
        "usage": "Verify",
        "value": null
    }
]
```

#### knownClientApplications 属性

| 鍵 | 値の型 |
| --- | --- |
| knownClientApplications | 文字列配列 |

クライアント アプリとカスタム Web API アプリの 2 つの部分を含むソリューションがある場合に、同意をバンドルするために使用されます。 この値にクライアント アプリの appID を入力すると、ユーザーは、クライアント アプリに 1 回同意するだけで済みます。 クライアントへの同意が Web API への暗黙的な同意を意味することが Microsoft Entra ID によって認識されます。 クライアントと Web API の両方のサービス プリンシパルが同時に自動でプロビジョニングされます。 クライアントと Web API アプリの両方が同じテナントに登録されている必要があります。

例:

```json
"knownClientApplications": ["00001111-aaaa-2222-bbbb-3333cccc4444"]
```

#### logoUrl 属性

| 鍵 | 値の型 |
| --- | --- |
| logoUrl | 糸 |

アップロードされたロゴへの CDN URL を指す値のみを読み取ります。

例:

```json
"logoUrl": "https://MyRegisteredAppLogo"
```

#### logoutUrl 属性

| 鍵 | 値の型 |
| --- | --- |
| logoutUrl | 糸 |

アプリからサインアウトするための URL。

例:

```json
"logoutUrl": "https://MyRegisteredAppLogout"
```

#### name 属性

| 鍵 | 値の型 |
| --- | --- |
| 名前 | 糸 |

アプリの表示名。

例:

```json
"name": "MyRegisteredApp"
```

#### oauth2AllowImplicitFlow 属性

| 鍵 | 値の型 |
| --- | --- |
| oauth2AllowImplicitFlow | ブール値 |

この Web アプリで OAuth2.0 暗黙的フロー アクセス トークンを要求できるかどうかを指定します。 既定値は false です。 このフラグは、ブラウザーベースのアプリ (JavaScript シングルページ アプリなど) に使用されます。 詳細については、目次に「`OAuth 2.0 implicit grant flow`」と入力し、暗黙的フローに関するトピックを表示します。 ただし、SPA でも暗黙的な許可を使用することはお勧めしません。PKCE で [承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) を使用することをお勧めします。

例:

```json
"oauth2AllowImplicitFlow": false
```

#### oauth2AllowIdTokenImplicitFlow 属性

| 鍵 | 値の型 |
| --- | --- |
| oauth2AllowIdTokenImplicitFlow | ブール値 |

この Web アプリで OAuth2.0 暗黙的フロー ID トークンを要求できるかどうかを指定します。 既定値は false です。 このフラグは、ブラウザーベースのアプリ (JavaScript シングルページ アプリなど) に使用されます。 ただし、SPA でも暗黙的な許可を使用することはお勧めしません。PKCE で [承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) を使用することをお勧めします。

Microsoft Graph アプリ マニフェストでは、この属性は、`enableIdTokenIssuance`属性の`implicitGrantSettings` プロパティの`web` プロパティに置き換えられます。

例:

```json
"oauth2AllowIdTokenImplicitFlow": false
```

#### oauth2Permissions 属性

| 鍵 | 値の型 |
| --- | --- |
| oauth2Permissions | コレクション |

Web API (リソース) アプリがクライアント アプリに公開する OAuth 2.0 アクセス許可スコープのコレクションを指定します。 これらのアクセス許可スコープは、同意中にクライアント アプリに付与できます。

例:

```json
"oauth2Permissions": [
    {
        "adminConsentDescription": "Allow the app to access resources on behalf of the signed-in user.",
        "adminConsentDisplayName": "Access resource1",
        "id": "<guid>",
        "isEnabled": true,
        "type": "User",
        "userConsentDescription": "Allow the app to access resource1 on your behalf.",
        "userConsentDisplayName": "Access resources",
        "value": "user_impersonation"
    }
]
```

#### oauth2RequiredPostResponse 属性

| 鍵 | 値の型 |
| --- | --- |
| oauth2RequiredPostResponse | ブール値 |

OAuth 2.0 トークン要求の一部として、Microsoft Entra ID が GET 要求ではなく、POST 要求を許可するかどうかを指定します。 既定値は false です。これは、GET 要求のみが許可されることを指定します。

例:

```json
"oauth2RequirePostResponse": false
```

#### parentalControlSettings 属性

| 鍵 | 値の型 |
| --- | --- |
| parentalControlSettings | 糸 |

- `countriesBlockedForMinors` は、未成年者に関してアプリがブロックされる国/地域を指定します。
- `legalAgeGroupRule` は、アプリのユーザーに適用される法的年齢グループ ルールを指定します。 `Allow`、`RequireConsentForPrivacyServices`、`RequireConsentForMinors`、`RequireConsentForKids`、`BlockMinors` のいずれかに設定できます。

例:

```json
"parentalControlSettings": {
    "countriesBlockedForMinors": [],
    "legalAgeGroupRule": "Allow"
}
```

#### passwordCredentials 属性

| 鍵 | 値の型 |
| --- | --- |
| passwordCredentials | コレクション |

`keyCredentials` プロパティの説明を参照してください。

例:

```json
"passwordCredentials": [
    {
        "customKeyIdentifier": null,
        "displayName": "Generated by App Service",
        "endDateTime": "2022-10-19T17:59:59.6521653Z",
        "hint": "Nsn",
        "keyId": "<guid>",
        "secretText": null,
        "startDateTime": "2022-10-19T17:59:59.6521653Z"
    }
]
```

#### preAuthorizedApplications 属性

| 鍵 | 値の型 |
| --- | --- |
| preAuthorizedApplications | コレクション |

暗黙的に同意するアプリケーションと要求されたアクセス許可がリストされます。 管理者がアプリケーションに同意する必要があります。 preAuthorizedApplications では、ユーザーが要求されたアクセス許可に同意する必要はありません。 PreAuthorizedApplications でリストされるアクセス許可にはユーザーの同意は必要ありません。 しかし、preAuthorizedApplications にリストされていない追加の要求されたアクセス許可にはユーザーの同意が必要です。

例:

```json
"preAuthorizedApplications": [
    {
        "appId": "00001111-aaaa-2222-bbbb-3333cccc4444",
        "permissionIds": [
            "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb"
        ]
    }
]
```

#### publisherDomain 属性

| 鍵 | 値の型 |
| --- | --- |
| publisherDomain | 糸 |

アプリケーションの確認された発行元のドメイン。 読み取り専用です。

例:

```json
"publisherDomain": "{tenant}.onmicrosoft.com"
```

#### replyUrlsWithType 属性

| 鍵 | 値の型 |
| --- | --- |
| replyUrlsWithType | コレクション |

この複数値プロパティには、トークンを返すときに Microsoft Entra ID が送信先として受け入れる登録済みの redirect\_uri 値の一覧が保持されます。 各 URI 値には、関連付けられているアプリの種類の値を含める必要があります。 サポートされる種類の値は次のとおりです。

- `Web`
- `InstalledClient`
- `Spa`

詳細については、 [replyUrl の制限事項と制限事項に関するページを](https://learn.microsoft.com/ja-jp/entra/identity-platform/reply-url)参照してください。

例:

```json
"replyUrlsWithType": [
    {
        "url": "https://localhost:4400/services/office365/redirectTarget.html",
        "type": "InstalledClient"
    }
]
```

#### requiredResourceAccess 属性

| 鍵 | 値の型 |
| --- | --- |
| requiredResourceAccess | コレクション |

動的な同意では、静的な同意を使用しているユーザーに対する管理者の同意エクスペリエンスおよびユーザーの同意エクスペリエンスが `requiredResourceAccess` によって作動します。 ただし、このパラメーターを使用しても、一般的な場合のユーザーの同意エクスペリエンスは促進されません。

- `resourceAppId` は、アプリがアクセスする必要があるリソースの一意識別子です。 この値は、ターゲット リソース アプリで宣言された appId に等しくなるようにしてください。
- `resourceAccess` は、指定されたリソースに対してアプリが必要とする OAuth2.0 アクセス許可スコープとアプリ ロールを格納する配列です。 指定されたリソースの `id` と `type` の値が格納されます。

例:

```json
"requiredResourceAccess": [
    {
        "resourceAppId": "00000002-0000-0000-c000-000000000000",
        "resourceAccess": [
            {
                "id": "311a71cc-e848-46a1-bdf8-97ff7156d8e6",
                "type": "Scope"
            }
        ]
    }
]
```

#### samlMetadataUrl 属性

| 鍵 | 値の型 |
| --- | --- |
| samlMetadataUrl | 糸 |

アプリの SAML メタデータへの URL。

例:

```json
"samlMetadataUrl": "https://MyRegisteredAppSAMLMetadata"
```

#### signInUrl 属性

| 鍵 | 値の型 |
| --- | --- |
| signInUrl | 糸 |

アプリのホーム ページへの URL を指定します。

例:

```json
"signInUrl": "https://MyRegisteredApp"
```

#### signInAudience 属性

| 鍵 | 値の型 |
| --- | --- |
| signInAudience | 糸 |

現在のアプリケーションでサポートされる Microsoft アカウントを指定します。 サポートされる値は次のとおりです。

- `AzureADMyOrg` - 自分の組織の Microsoft Entra テナント (たとえば、シングル テナント) に、Microsoft の職場アカウントまたは学校アカウントを持つユーザー
- `AzureADMultipleOrgs` - 任意の組織の Microsoft Entra テナント (たとえば、マルチテナント) に、Microsoft の職場アカウントまたは学校アカウントを持つユーザー
- `AzureADandPersonalMicrosoftAccount` - 個人用の Microsoft アカウント、または任意の組織の Microsoft Entra テナントに職場アカウントまたは学校アカウントを持つユーザー
- `PersonalMicrosoftAccount` - Xbox や Skype などのサービスのサインインに使用する個人用アカウント。

例:

```json
"signInAudience": "AzureADandPersonalMicrosoftAccount"
```

#### tags 属性

| 鍵 | 値の型 |
| --- | --- |
| タグ | 文字列配列 |

アプリケーションの分類と特定に使用できるカスタム文字列。

個々のタグは、1 ~ 256 文字 (両端を含む) である必要があります。 空白や重複するタグは使用できません。 一般的なマニフェスト サイズの制限に従って、追加できるタグの数に特定の制限はありません。

例:

```json
"tags": [
    "ProductionApp"
]
```

### 一般的な問題

#### マニフェストの制限

個々のコレクションにも制限があります。 アプリ ロールと公開された委任されたアクセス許可スコープは、集計マニフェストの制限とは別に、既定の制限である 700 個のアクセス許可定義を共有します。 「 [アプリ ロールの制限」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-role-limits)参照してください。

アプリケーション マニフェストには、appRoles、keyCredentials、knownClientApplications、identifierUris、redirectUris、requiredResourceAccess、oauth2Permissions など複数の属性があり、コレクションと呼ばれています。 任意のアプリケーションの完全なアプリケーション マニフェスト内では、すべてのコレクションを組み合わせたときのエントリの合計数は 1200 個に制限されています。 アプリケーション マニフェストで 100 個のリダイレクト URI を以前に指定した場合、マニフェストを構成する他の組み合わされたコレクション全体で使用できるエントリ数は残りの 1,100 個です。

注

アプリケーション マニフェストに 1,200 を超えるエントリを追加しようとすると、 **"アプリケーション xxxxxx の更新に失敗しました。エラーの詳細: マニフェストのサイズが制限を超えています。値の数を減らして、要求を再試行してください。**

#### サポート外の属性

アプリケーション マニフェストは、Microsoft Entra ID の基礎となっているアプリケーション モデルのスキーマを表しています。 基礎となるスキーマの進化に応じて、マニフェスト エディターは新しいスキーマを反映するように随時更新されます。 このため、アプリケーション マニフェストに新しい属性が表示される場合があります。 まれに、既存の属性が構文上またはセマンティックに変更されていたり、以前に存在していた属性がサポートされなくなっていたりする場合があります。 たとえば、アプリの登録に新しい属性が表示されます。これは、 [アプリ登録](https://go.microsoft.com/fwlink/?linkid=2083908) (レガシ) エクスペリエンスで別の名前で認識されます。

| アプリの登録 (レガシ) | アプリの登録 |
| --- | --- |
| `availableToOtherTenants` | `signInAudience` |
| `displayName` | `name` |
| `errorUrl` | - |
| `homepage` | `signInUrl` |
| `objectId` | `Id` |
| `publicClient` | `allowPublicClient` |
| `replyUrls` | `replyUrlsWithType` |

これらの属性の説明については、 マニフェストリファレンス セクションを参照してください。

以前にダウンロードしたマニフェストをアップロードしようとすると、次のいずれかのエラーが表示される場合があります。 マニフェスト エディターで現在サポートされている新しいバージョンのスキーマが、アップロードしようとしているスキーマと一致していないためにこのエラーが発生している可能性があります。

- "xxxxxx アプリケーションを更新できませんでした。 エラーの詳細:無効なオブジェクト識別子 'undefined' です。 []."
- "xxxxxx アプリケーションを更新できませんでした。 エラーの詳細:指定した 1 つ以上のプロパティ値が無効です。 []."
- "xxxxxx アプリケーションを更新できませんでした。 エラーの詳細:この API バージョンで更新用に availableToOtherTenants を設定することはできません。 []."
- "xxxxxx アプリケーションを更新できませんでした。 エラーの詳細:'replyUrls' プロパティの更新は、このアプリケーションでは許可されていません。 代わりに、'replyUrlsWithType' プロパティを使用してください。 []."
- "xxxxxx アプリケーションを更新できませんでした。 エラーの詳細:型名のない値が見つかりましたが、使用できる必要な型がありません。 モデルが指定されている場合、ペイロード内の値ごとに型が必要です。その型は、ペイロードで指定できる型か、呼び出し元による明示的な型か、親値から暗黙的に推定される型にすることができます。 []"

これらのエラーのいずれかが表示された場合、以下の操作を実行することをお勧めします。

1. 以前にダウンロードしたマニフェストのアップロードではなく、マニフェスト エディターで個別に属性を編集します。 関心のある属性を正常に編集できるように、 マニフェスト参照 テーブルを使用して、古い属性と新しい属性の構文とセマンティクスを理解します。
2. ワークフローで後で使用するためにソース リポジトリにマニフェストを保存する必要がある場合は、リポジトリに保存されたマニフェストを、 **アプリ登録** エクスペリエンスに表示されるマニフェストと再調整することをお勧めします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/reference-breaking-changes"} -->
## 更新プログラムと破壊的変更 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-breaking-changes
- Service: identity-platform
- Article date: 2024-04-10
- Summary: アプリケーションに影響を与える可能性があるMicrosoft ID プラットフォームの変更について説明します。

Microsoft は、セキュリティ、使いやすさ、標準へのコンプライアンスを向上させるために、Microsoft ID プラットフォームの機能を定期的に追加および変更します。

特に記載がない限り、ここで説明する変更は、指定された変更の有効日の後に登録されたアプリケーションにのみ適用されます。

この記事を定期的に調べて、以下の内容を確認してください。

- 既知の問題と修正
- プロトコルの変更
- 非推奨の機能

Tip

このページの更新に関する通知を受け取るには、この URL を RSS フィード リーダーに追加してください。`https://learn.microsoft.com/api/search/rss?search=%22Azure+Active+Directory+breaking+changes+reference%22&locale=en-us`

### 2024 年 8 月

#### フェデレーション ID 資格情報で大文字と小文字が区別される照合が使用されるようになりました

**有効日:** 2024 年 9 月

**影響を受けたプロトコル:** ワークロード ID フェデレーション

**Change**

以前は、外部 IdP によって Microsoft Entra ID に送信されたトークンに含まれる値と`issuer`、フェデレーション ID 資格情報 (FIC) `subject``audience`、および値を照合`issuer``subject`するときに、大文字と`audience`小文字を区別しない照合が使用されていました。 よりきめ細かな制御を顧客に提供するために、大文字と小文字を区別した照合の適用に切り替えています。

無効な例:

- トークンの件名: `repo:contoso/contoso-org:ref:refs/heads/main`
- FIC の件名: `repo:Contoso/Contoso-Org:ref:refs/heads/main`

これら 2 つのサブジェクト値は大文字と小文字が区別されないので、検証は失敗します。 同じメカニズムが適用 `issuer` され、 `audience` 検証されます。

この変更は、最初に後に作成された `August 14th, 2024`アプリケーションまたはマネージド ID に適用されます。 非アクティブなアプリケーションまたはマネージド ID は、その期間`August 1st, 2024``August 31st, 2024`の間にアプリケーションまたはマネージド ID によって行われたワークロード ID フェデレーション要求がゼロであることによって決定され、開始時`September 27th, 2024`に大文字と小文字を区別する照合を使用する必要があります。 アクティブなアプリケーションの場合、大文字と小文字が区別される照合は後日伝達されます。

大文字と小文字の区別によるエラーをより適切に強調表示するために、`AADSTS700213`のエラー メッセージを改良しています。 次に、次の状態になります。

```
`AADSTS700213: No matching federated identity record found for presented assertion subject '{subject}'. Please note that matching is done using a case-sensitive comparison. Check your federated identity credential Subject, Audience, and Issuer against the presented assertion.` 
```

プレースホルダー `'{subject}'` は、外部 IdP から Microsoft Entra ID に送信されるトークンに含まれるサブジェクト要求の値を提供します。 このエラー テンプレートは、大文字と小文字を区別しないエラーの周囲 `issuer` と `audience` 検証にも使用されます。 このエラーが発生した場合は、エラーに対応するフェデレーション ID 資格情報、または`issuer`エラーに`subject``audience`一覧表示されているフェデレーション ID 資格情報を見つけ、対応する値が大文字と小文字を区別する観点から同等であることを確認する必要があります。 不一致がある場合は、FIC の現在 `issuer`の値、 `subject`または `audience` 値を、エラー メッセージに `issuer`含まれていた 、 `subject`または `audience` 値に置き換える必要があります。

Note

GitHub Actions を使用し、このエラーが発生するAzure アプリサービスのお客様の場合 このガイダンスは、Azure アプリ サービスのシナリオにのみ適用されます。 別のシナリオでこのエラーが発生した場合は、上記のガイダンスを参照してください。

### 2024 年 6 月

#### アプリケーションは 1 つのディレクトリに登録する必要がある

**発効日**: 2024 年 6 月

**影響を受けたエンドポイント**: v2.0 と v1.0

**影響を受けたプロトコル**: すべてのフロー

**Change**

以前は、Microsoft Entra アプリ登録エクスペリエンス[を使用して](https://aka.ms/ra/prod)アプリケーションを登録するときに、ユーザーが個人の Microsoft アカウント (MSA) でサインインした場合、アプリケーションを自分の個人用アカウントにのみ関連付ける選択をできました。 つまり、アプリケーションはディレクトリ ("テナント" または "組織" とも呼ばれます) に関連付けられていないか、または含まれません。 ただし、2024 年 6 月以降は、すべてのアプリケーションを 1 つのディレクトリに登録する必要があります。 これは、既存のディレクトリ、または個人の Microsoft アカウント ユーザーが Microsoft Entra アプリケーションやその他の Microsoft リソースを格納するために作成する新しいディレクトリです。 ユーザーは、Microsoft 365 開発者プログラム[に参加するか](https://aka.ms/signUpForAzure)[、Azure](https://aka.ms/joinM365DeveloperProgram) にサインアップすることで、この目的に使用する新しいディレクトリを簡単に作成できます。

アプリケーションを個人用アカウントに関連付けるのではなく、ディレクトリに登録すると、さまざまな利点があります。 これらには次のものが含まれます。

- ディレクトリに登録されているアプリケーションには、アプリに複数の所有者を追加する機能や、アプリを [発行元が確認](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview) する機能など、より多くの機能があります。
- アプリケーションは、開発者が使用する他の Microsoft リソース (Azure リソースなど) と同じ場所にあります。
- アプリケーションは、回復性の向上の利点を受け取ります。

これは、個人用アカウントにのみ関連付けられている既存のアプリケーションを含め、既存のアプリケーションには影響しません。 新しいアプリケーションを登録する機能のみが影響を受けます。

### 2023 年 10 月

#### 更新された RemoteConnect UX プロンプト

**発効日**: 2023 年 10 月

**影響を受けたエンドポイント**: v2.0 と v1.0

**影響を受けたプロトコル**: RemoteConnect

RemoteConnect は、[プライマリ更新トークン](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token)など、Microsoft 認証ブローカーと Microsoft Intune 関連のシナリオに使用されるクロスデバイス フローです。 フィッシング攻撃を防ぐために、RemoteConnect フローは更新された UX 言語を受け取り、フローの正常な完了時にリモート デバイス (フローを開始したデバイス) が組織で使用されるすべてのアプリケーションにアクセスできることを呼び出しています。

表示されるプロンプトは次のようになります。

[Image: 更新後の Remote Connect プロンプトのスクリーンショット。「リモートのデバイスまたはサービスでサインインされ、組織で使用されているあらゆるアプリにアクセスできます」とあります。]

### 2023 年 6 月

#### 未確認のドメイン所有者を持つ電子メール要求の省略

**発効日**: 2023 年 6 月

**影響を受けたエンドポイント**: v2.0 と v1.0

**Change**

**マルチテナント アプリケーション**の場合、トークン ペイロードで省略可能な`email`要求が要求されると、ドメイン所有者が検証されていない電子メールは既定で省略されます。

次の場合、電子メールはドメイン所有者を確認済みと見なされます。

1. ユーザー アカウントが存在するテナントにドメインが属しており、テナント管理者によってドメインの確認が行われた。
2. Microsoft アカウント (MSA) からの電子メールである。
3. Google アカウントからの電子メールである。
4. ワンタイム パスコード (OTP) フローを使用した認証に使用された電子メールである。

また、Facebook アカウントと SAML/WS-Fed アカウントには、検証済みドメインがないことに注意する必要があります。

### 2023 年 5 月

#### Power BI 管理者ロールの名称は Fabric 管理者に変更されます。

**発効日**: 2023 年 6 月

**影響を受けたエンドポイント**:

- roleDefinitions の一覧表示 - Microsoft Graph v1.0
- directoryRoles の一覧表示 - Microsoft Graph v1.0

**Change**

Power BI 管理者ロールの名前がファブリック管理者に変更されました。

2023 年 5 月 23 日、Microsoft は Microsoft Fabric を発表しました。Data Factory によるデータ統合エクスペリエンス、Synapse によるデータ エンジニアリング、データ ウェアハウス、データ サイエンス、リアルタイム分析エクスペリエンス、Power BI を使ったビジネス インテリジェンス (BI) を備えており、これらすべてがレイク中心の SaaS ソリューションでホストされています。 これらのエクスペリエンス用のテナントと容量の管理は、Fabric 管理ポータル (旧称は Power BI 管理ポータル) に一元化されています。

2023 年 6 月以降、このロールのスコープと責任の変化に合わせて、Power BI 管理者ロールの名前がファブリック管理者に変更されます。 Microsoft Entra ID、Microsoft Graph API、Microsoft 365、GDAP を含むすべてのアプリケーションには、今後数週間で新しいロール名が反映される予定です。

アプリケーション コードやスクリプトでは、ロール名や表示名に基づいて決定を下さないように注意してください。

### 2021 年 12 月

#### AD FS ユーザーには、正しいユーザーがサインインしていることを確認するための追加のログイン プロンプトが表示されます。

**発効日**: 2021 年 12 月

**影響を受けたエンドポイント**: 統合Windows認証

**Protocol の影響を受けます**: 統合Windows認証

**Change**

現在、ユーザーは認証のために AD FS に送られると、AD FS とのセッションが既に存在するアカウントに自動的にサインインさせられます。 この自動サインインは、ユーザーが別のユーザー アカウントにサインインするつもりであっても発生します。 この誤ったサインインの発生頻度を減らすために、12 月より、Windows の Web アカウント マネージャーがサインイン時にサインインに特定のユーザーが必要であることを示すパラメーター `prompt=login` を Microsoft Entra ID に提供した場合、Microsoft Entra ID は AD FS にパラメーター `login_hint` を送信するようになります。

上記の要件が満たされている場合 (サインインに向けてユーザーを Microsoft Entra ID に送信するために WAM が使用され、`login_hint` が含まれており、[ユーザーのドメインの AD FS インスタンスが `prompt=login` をサポートしている場合](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/ad-fs-prompt-login))、ユーザーは自動的にサインインされません。代わりに AD FS にサインインし続けるユーザー名を指定する必要があります。 既存の AD FS セッションにサインインする場合は、ログイン プロンプトの下に表示される [現在のユーザーとして続行] オプションを選択できます。 それ以外の場合は、サインインに使用するユーザー名で続行できます。

この変更は、2021 年 12 月、数週間にかけて展開されます。 次の場合、サインインの動作は変更されません。

- IWA を直接使用するアプリケーション
- OAuth を使用するアプリケーション
- AD FS インスタンスにフェデレーションされていないドメイン

### 2021 年 10 月

#### 対話型認証中にエラー 50105 が`interaction_required`返されない問題を修正しました

**発効日**: 2021 年 10 月

**影響を受けたエンドポイント**: v2.0 と v1.0

**影響を受けたプロトコル**: ユーザー[割り当てを必要とする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-access-management#requiring-user-assignment-for-an-app)アプリのすべてのユーザー フロー

**Change**

割り当てられていないユーザーが、管理者がユーザー割り当てを要求するとマークしたアプリにサインインしようとすると、エラー 50105 (現在の指定) が生成されます。 これは一般的なアクセス制御パターンであり、ユーザーは多くの場合、アクセスのブロックを解除するために割り当てを要求する管理者を見つける必要があります。 エラーには、`interaction_required`エラー応答を正しく処理する適切にコード化されたアプリケーションで無限ループを引き起こすバグが存在しました。 `interaction_required` はアプリに対話型認証を実行するよう指示しますが、それを実行した後にも、Microsoft Entra ID が引き続き `interaction_required` エラー応答を返すようになっていました。

エラー シナリオが更新されたので、非対話型認証 (`prompt=none`はUX を非表示にする場合) に、`interaction_required`エラー応答を使用して対話型認証を実行するようにアプリに指示されます。 その後の対話型認証では、Microsoft Entra ID がユーザーを保持し、エラーメッセージを直接表示して、ループが発生しないようにします。

アプリケーション コードでは、`AADSTS50105` のようなエラー コード文字列に基づいて決定を行うべきではないことに注意してください。 代わりに、[エラー処理に関するガイダンスに従い](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes#handling-error-codes-in-your-application)、応答の標準  フィールドにある `interaction_required` や `login_required` のような`error`を使用します。 他の応答フィールドは、問題のトラブルシューティングを行う人間による使用のみを目的としています。

エラー検索サービス `https://login.microsoftonline.com/error?code=50105` では、50105 エラーの現在のテキストと詳細を確認できます。

#### シングル テナント アプリケーションの AppId URI には、既定のスキームまたは検証済みドメインを使用する必要があります

**発効日**: 2021 年 10 月

**影響を受けたエンドポイント**: v2.0 と v1.0

**影響を受けたプロトコル**: すべてのフロー

**Change**

シングル テナント アプリケーションの場合、AppId URI を追加または更新すると、HTTPS スキームの URI のドメインが顧客テナントの検証済みドメイン リストに含まれるか、または Microsoft Entra ID によって提供される既定のスキーム (`api://{appId}`) がその値で使われているかが検証されます。 これにより、ドメインが検証済みドメイン リストに含まれない場合、または値で既定のスキームが使用されていない場合は、アプリケーションで AppID URI を追加できないおそれがあります。 検証済みドメインの詳細については、[カスタム ドメインのドキュメント](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)を参照してください。

この変更は、AppID URI で未検証のドメインが使用されている既存のアプリケーションには影響しません。 検証が行われるのは、新しいアプリケーションの場合、または既存のアプリケーションで識別子 URI が更新されるか、新しいものが identifierUri コレクションに追加されるときだけです。 新しい制限は、2021 年 10 月 15 日より後にアプリの identifierUris コレクションに追加された URI にのみ適用されます。 2021 年 10 月 15 日に制限が有効になった時点でアプリケーションの identifierUris コレクションに既に存在している AppId URI は、そのコレクションに新しい URI が追加されても引き続き機能します。

検証チェックで要求に問題があった場合、作成および更新用のアプリケーション API からは HostNameNotOnVerifiedDomain を示す `400 badrequest` がクライアントに返されます。

次の API および HTTP スキームベースのアプリケーション ID URI 形式がサポートされます。 表の後の一覧の説明に従って、プレースホルダーの値を置き換えてください。

| サポートされるアプリケーション IDURI 形式 | アプリ ID URI の例 |
| --- | --- |
| *api://&lt;appId&gt;* | *api://aaaabbbb-0000-cccc-1111-dddd2222eeee* |
| *api://&lt;tenantId&gt;/&lt;appId&gt;* | *api://aaaabbbb-0000-cccc-1111-dddd2222eeee/00001111-aaaa-2222-bbbb-3333cccc4444* |
| *api://&lt;tenantId&gt;/&lt;string&gt;* | *api://aaaabbbb-0000-cccc-1111-dddd2222eeee/api* |
| *api://&lt;string&gt;/&lt;appId&gt;* | *api://productapi/00001111-aaaa-2222-bbbb-3333cccc4444* |
| *https://&lt;tenantInitialDomain&gt;.onmicrosoft.com/&lt;string&gt;* | *`https://contoso.onmicrosoft.com/productsapi`* |
| *https://&lt;verifiedCustomDomain&gt;/&lt;string&gt;* | *`https://contoso.com/productsapi`* |
| *https://&lt;string&gt;.&lt;verifiedCustomDomain&gt;* | *`https://product.contoso.com`* |
| *https://&lt;string&gt;.&lt;verifiedCustomDomain&gt;/&lt;string&gt;* | *`https://product.contoso.com/productsapi`* |
| *api://&lt;string&gt;.&lt;verifiedCustomDomainOrInitialDomain&gt;/&lt;string&gt;* | *`api://contoso.com/productsapi`* |

- * &lt;appId&gt;* - アプリケーション オブジェクトのアプリケーション ID (appId) プロパティ。
- * &lt;string&gt;* - ホストまたは API パスのセグメントの文字列値。
- * &lt;tenantId&gt;* - Azure 内のテナントを表すために Azure によって生成された GUID。
- * &lt;tenantInitialDomain&gt;* - *&lt;tenantInitialDomain&gt;.onmicrosoft.com*。*&lt;tenantInitialDomain&gt;* は、テナント作成時にテナント作成者が指定した初期ドメイン名です。
- * &lt;verifiedCustomDomain&gt;* - Microsoft Entra テナント用に構成された[検証済みのカスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)。

Note

*api://* スキームを使用している場合は、"api://" の直後に文字列値を追加します。 たとえば、*api://&lt;string&gt;* です。 その文字列値には、GUID または任意の文字列を指定できます。 GUID 値を追加する場合は、アプリ ID またはテナント ID と一致する必要があります。 文字列値を使用する場合は、テナントの検証済みのカスタム ドメインまたは初期ドメインを使用する必要があります。 *api://&lt;appId&gt;* を使用することをお勧めします。

Important

アプリケーション ID URI の値は、スラッシュ "/" 文字で終わる必要があります。

Important

アプリケーション ID URI の値は、テナント内で一意である必要があります。

Note

現在のテナント内でアプリ登録の identifierUris を削除しても問題はありませんが、identifierUris を削除すると、クライアントが他のアプリ登録で失敗する可能性があります。

### 2021 年 8 月

#### 条件付きアクセスは、明示的に要求されたスコープに対してのみトリガーされる

**発効日**: 2021 年 8 月。段階的なロールアウトは 4 月から開始されます。

**影響を受けたエンドポイント**: v2.0

**影響を受けたプロトコル**: [動的同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#user-consent)を使用するすべてのフロー

現在、動的な同意が使用されているアプリケーションでは、名前を指定して `scope` パラメーターで要求されなかった場合でも、同意があるすべてのアクセス許可が付与されます。 たとえば、`user.read` のみが要求されているが、`files.read` に対する同意も含まれているアプリでは、`files.read` のために割り当てられている条件付きアクセス要件が強制的に渡される場合があります。

不要な条件付きアクセスのプロンプト数を減らすため、Microsoft Entra ID では、スコープがアプリケーションに提供される方法を変更し、明示的に要求されたスコープのみによって条件付きアクセスがトリガーされるようにします。 *all*スコープをトークンに含めるというMicrosoft Entra IDの以前の動作に依存しているアプリケーションは、要求されたかどうかに関係なく、スコープが不足しているために中断される可能性があります。

今後、アプリでは、混合したアクセス許可 (要求されたトークンと、同意はあるが、条件付きアクセスのプロンプトが必要ないもの) が含まれたアクセス トークンを受け取ります。 トークンに対するアクセスのスコープは、トークン応答の `scope` パラメーターに反映されます。

この変更は、この動作に対して、依存関係が確認されたものを除く、すべてのアプリを対象に行われます。 それらが追加の条件付きアクセス プロンプトに依存している可能性があるため、この変更から除外されている場合、開発者はアウトリーチを受け取ります。

**Examples**

アプリには、`user.read`、`files.readwrite`、および `tasks.read` に対する同意があります。 `files.readwrite` には、条件付きアクセス ポリシーが適用されていますが、他の 2 つには適用されていません。 アプリで `scope=user.read` に対するトークン要求が行われ、現在サインインしているユーザーが条件付きアクセス ポリシーを渡していない場合、結果として得られるトークンは `user.read` と `tasks.read` のアクセス許可を対象としたものになります。 `tasks.read` が含まれている理由は、それに対する同意がアプリにあり、かつ条件付きアクセス ポリシーを適用する必要がないからです。

次に、アプリで `scope=files.readwrite` が要求されると、テナントによって要求される条件付きアクセスがトリガーされ、条件付きアクセス ポリシーを満たすことができる対話型認証プロンプトの表示がアプリに対して強制されます。 返されるトークンには、3 つすべてのスコープが含まれます。

次に、アプリがその 3 つのスコープのいずれか (たとえば、`scope=tasks.read`) に対して最後の要求を行うと、Microsoft Entra ID では、ユーザーが `files.readwrite` に必要な条件付きアクセス ポリシーを既に完了していることを確認し、3 つすべてのアクセス許可が含まれたトークンを再び発行します。

### 2021年 6 月

#### デバイス コード フロー UX にアプリの確認プロンプトが含まれるようになります

**発効日**: 2021 年 6 月。

**影響を受けたエンドポイント**: v2.0 と v1.0

**影響を受けたプロトコル**: [デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code)

フィッシング攻撃を防ぐために、デバイス コード フローに、ユーザーが予想するアプリにサインインしていることを検証するプロンプトが含まれるようになりました。

表示されるプロンプトは次のように表示されます。

### 2020 年 5 月

#### バグ修正: Microsoft Entra ID が状態パラメーターを 2 回 URL エンコードしなくなります

**発効日**: 2021 年 5 月

**影響を受けたエンドポイント**: v1.0 と v2.0

**影響を受けたプロトコル**: `/authorize` エンドポイントにアクセスするすべてのフロー (暗黙的フローと承認コード フロー)

Microsoft Entra 承認応答でバグが見つかり、修正されました。 認証の `/authorize` 段階では、応答に要求からの `state` パラメーターが含まれます。これにより、アプリの状態が維持され、CSRF 攻撃を防ぐことができます。 `state` パラメーターがエンコードされた応答に、このパラメーターを挿入する前に、Microsoft Entra ID により、誤ってこのパラメーターがもう一度 URL エンコードされます。 これにより、アプリケーションが Microsoft Entra ID からの応答を誤って拒否する可能性があります。

Microsoft Entra ID によるこのパラメーターのダブルエンコードがなくなり、アプリで結果を正しく解析できるようになります。 この変更はすべてのアプリケーションに対して行われます。

#### Azure Government エンドポイントの変更

**発効日**:2020年5月5日(2020年6月終了)

**影響を受けたエンドポイント**: すべて

**影響を受けたプロトコル**: すべてのフロー

2018 年 6 月 1 日、Azure Government の公式 Microsoft Entra Authority が `https://login-us.microsoftonline.com` から `https://login.microsoftonline.us` に変更されました。 この変更は、Azure Government Microsoft Entra ID でもサービスが提供される Microsoft 365 GCC High および DoD にも適用されます。 米国政府テナント内でアプリケーションを所有している場合は、`.us` エンドポイントでユーザーをサインインさせるようにアプリケーションを更新する必要があります。

2020 年 5 月 5 日に、Microsoft Entra ID でエンドポイントの変更の適用が開始され、政府ユーザーはパブリック エンドポイント (`microsoftonline.com`) を使用して米国政府テナントでホストされているアプリにサインインできなくなります。 影響を受けるアプリでは、`AADSTS900439` - `USGClientNotSupportedOnPublicEndpoint` エラーが表示されるようになります。 このエラーは、アプリがパブリック クラウド エンドポイントで米国政府ユーザーのサインインを試みていることを示します。 アプリがパブリック クラウド テナント内にあり、米国政府ユーザーのサポートを意図している場合は、[それらのユーザーを明示的にサポートするようにアプリを更新する](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud)必要があります。 これには、米国政府機関向けクラウドで新しいアプリの登録を作成することが必要になる場合があります。

この変更の適用は、米国政府のクラウドからアプリケーションにサインインするユーザーの頻度に基づいて、段階的なロールアウトを使用して行われます。米国政府ユーザーがサインインする頻度の少ないアプリは最初に適用され、米国政府ユーザーが頻繁に使用するアプリは最後に適用されます。 2020 年 6 月には、すべてのアプリで適用が完了するものと思われます。

詳細については、[この移行に関する Azure Government ブログ記事](https://devblogs.microsoft.com/azuregov/azure-government-aad-authority-endpoint-update/)を参照してください。

### 2020 年 3 月

#### ユーザーのパスワードは、256 文字に制限されます。

**発効日**: 2020 年 3 月 13 日

**影響を受けたエンドポイント**: すべて

**影響を受けたプロトコル**: すべてのユーザー フロー。

Microsoft Entra ID に直接サインインし、パスワードが 256 文字を超えるユーザー (AD FS のようなフェデレーション IDP ではない) は、サインインする前にパスワードの変更を求められます。 管理者は、ユーザーのパスワード リセットを支援するように要求される場合があります。

サインイン ログのエラーは、*AADSTS 50052: InvalidPasswordExceedsMaxLength* とほぼ同じです。

メッセージ: `The password entered exceeds the maximum length of 256. Please reach out to your admin to reset the password.`

Remediation:

パスワードが許可されている最大長を超えているため、ユーザーはログインできません。 パスワードをリセットするには、管理者に連絡する必要があります。 テナントで SSPR が有効になっている場合は、[パスワードを忘れた場合] のリンクを使用してパスワードをリセットできます。

### 2020 年 2 月

#### ログイン エンドポイントからのすべての HTTP リダイレクトに空のフラグメントが追加されます。

**発効日**: 2020 年 2 月 8 日

**影響を受けたエンドポイント**: v1.0 と v2.0 の両方

**影響を受けたプロトコル**: `response_type=query` を使用する OAuth フローと OIDC フロー 。 これは、場合によっては [承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) と [暗黙的フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow)を対象とします。

認証応答が HTTP リダイレクトを介して *login.microsoftonline.com* からアプリケーションに送信されると、サービスは応答 URL に空のフラグメントを追加します。 これにより、ブラウザーで認証要求内の既存のフラグメントがすべて消去され、リダイレクト攻撃のクラスを防止できます。 アプリはこの動作に依存しないようにしてください。

### 2019 年 8 月

#### POST フォームのセマンティクスがより厳密に適用され、スペースおよび引用符は無視されます

**発効日**: 2019 年 9 月 2 日

**影響を受けたエンドポイント**: v1.0 と v2.0 の両方

**影響を受けたプロトコル**: 任意の場所で POST が使用されます ([クライアント資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)、 [承認コードの引き換え](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)、 [ROPC](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc)、 [OBO](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)、 [および更新トークンの引き換え](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#refresh-the-access-token))

2019 年 9 月 2 日の週から、POST メソッドを使用する認証要求は、より厳格な HTTP 標準を使用して検証されます。 具体的には、スペースと二重引用符 (") が要求フォームの値から削除されなくなります。 これらの変更によって、既存のクライアントが中断されることはなく、Microsoft Entra ID に送信された要求は毎回確実に処理されます。 今後 (上記参照)、重複するパラメーターを拒否し、要求内の BOM を無視することをさらに計画しています。

Example:

現在、`?e=    "f"&g=h` は `?e=f&g=h` と同じように解析されるため、`e` == `f` となります。 この変更により、これは `e` == `    "f"` と解析されるようになりました。これは有効な引数である可能性が低く、要求は失敗します。

### 2019 年 7 月

#### シングルテナント アプリケーションのアプリ専用トークンは、クライアント アプリがリソース テナントに存在する場合にのみ発行される

**発効日**: 2019 年 7 月 26 日

**影響を受けたエンドポイント**: v1.0 と [v2.0 の](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)両方

**影響を受けたプロトコル**: クライアント資格情報 (アプリ専用トークン)

(クライアント資格情報の付与による) アプリ専用トークンの発行方法を変更するセキュリティの変更が、2019 年 7 月 26 日に有効になりました。 以前は、テナント内の存在またはそのアプリケーションに対して同意されているロールに関係なく、アプリケーションではトークンを取得して他のアプリを呼び出すことができました。 この動作が更新され、シングルテナント (既定) に設定されているリソース (Web API と呼ばれることもあります) の場合、クライアント アプリケーションはリソース テナント内に存在する必要があります。 クライアントと API の間の既存の同意は依然として必須ではなく、アプリでは、`roles` 要求が存在し、API に必要な値が含まれていることを確認するために、独自の承認チェックを実行する必要があります。

現在、このシナリオのエラー メッセージは次のようになっています。

`The service principal named <appName> was not found in the tenant named <tenant_name>. This can happen if the application has not been installed by the administrator of the tenant.`

この問題を解決するには、管理者の同意エクスペリエンスを使ってテナントにクライアント アプリケーション サービス プリンシパルを作成するか、手動で作成します。 この要件により、テナントによってアプリケーションにテナント内で動作するアクセス許可が付与されていることが確認されます。

##### 要求の例

`https://login.microsoftonline.com/contoso.com/oauth2/authorize?resource=https://gateway.contoso.com/api&response_type=token&client_id=00001111-aaaa-2222-bbbb-3333cccc4444&...` この例では、リソース テナント (機関) は contoso.com、リソース アプリは Contoso テナントに対する `gateway.contoso.com/api` という名前のシングルテナント アプリ、クライアント アプリは `00001111-aaaa-2222-bbbb-3333cccc4444` です。 クライアント アプリが Contoso.com 内にサービス プリンシパルを持っている場合は、この要求を続行できます。 そうでない場合、要求は上記のエラーで失敗します。

ただし、Contoso ゲートウェイ アプリがマルチテナント アプリケーションの場合は、クライアント アプリのサービス プリンシパルが Contoso.com 内にあるかどうかに関係なく、要求は続行されます。

#### リダイレクト URI にクエリ文字列パラメーターを含めることができるようになった

**発効日**: 2019 年 7 月 22 日

**影響を受けたエンドポイント**: v1.0 と v2.0 の両方

**影響を受けたプロトコル**: すべてのフロー

[RFC 6749](https://tools.ietf.org/html/rfc6749#section-3.1.2) ごとに、Microsoft Entra アプリケーションは、OAuth 2.0 要求の静的クエリ パラメーター (`https://contoso.com/oauth2?idp=microsoft` など) を使用してリダイレクト (応答) URI を登録して使用できるようになりました。 動的リダイレクト URI は、セキュリティ上のリスクがあり、認証要求全体で状態情報を保持するために使用できないため、引き続き許可されません。そのためには、`state` パラメーターを使用します。

静的クエリ パラメーターは、リダイレクト URI の他の部分と同様に、リダイレクト URI の文字列照合の対象になります。URI でデコードされたリダイレクト URI に一致する文字列が登録されていない場合、要求は拒否されます。 アプリの登録で URI が見つかった場合は、静的クエリ パラメーターを含む文字列全体が、ユーザーをリダイレクトするために使われます。

現時点 (2019 年 7 月末) では、Azure portal のアプリ登録 UX では、クエリ パラメーターが引き続きブロックされます。 ただし、アプリケーション マニフェストを手動で編集して、クエリ パラメーターを追加し、アプリでこれをテストすることができます。

### 2019 年 3 月

#### クライアントのループ処理が中断されます

**発効日**: 2019 年 3 月 25 日

**影響を受けたエンドポイント**: v1.0 と v2.0 の両方

**影響を受けたプロトコル**: すべてのフロー

クライアント アプリケーションが誤動作し、短期間に数百の同じログイン要求を発行する場合があります。 これらの要求は成功する場合もしない場合もありますが、そのすべてがユーザー エクスペリエンスの低下や IDP のワークロードの増加につながるため、すべてのユーザーの待ち時間が長くなり、IDP の可用性が低下します。 これらのアプリケーションは通常の使用の範囲外で動作しており、正しく動作するように更新する必要があります。

重複した要求を複数回発行するクライアントには `invalid_grant` エラー: `AADSTS50196: The server terminated an operation because it encountered a loop while processing a request` が送信されます。

ほとんどのクライアントは、動作を変更してこのエラーを回避する必要はありません。 正しく構成されていない (トークンのキャッシュを使用していない、または既にプロンプト ループを示している) クライアントのみがこのエラーの影響を受けます。 クライアントは、次の要因に対して (Cookie 経由で) ローカルにインスタンスごとに追跡されます。

- ユーザー ヒント (存在する場合)
- 要求されているスコープまたはリソース
- クライアント ID
- リダイレクトURI
- 応答の種類とモード

短期間 (5 分) に複数の (15 を超える) 要求を発行しているアプリは、ループ処理していることを示す `invalid_grant` エラーを受信します。 要求されているトークンには十分に長い有効期間 (最小 10 分、既定では 60 分) があるため、この期間にわたって繰り返される要求は必要ありません。

すべてのアプリが、確認なしでトークンを要求するのではなく、対話型プロンプトを示すことによって `invalid_grant` を処理する必要があります。 このエラーを回避するために、クライアントは、受信したトークンを正しくキャッシュしていることを確認する必要があります。

### 2018 年 10 月

#### 認証コードを再利用できなくなりました

**発効日**: 2018 年 11 月 15 日

**影響を受けたエンドポイント**: v1.0 と v2.0 の両方

**影響を受けたプロトコル**: [コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)

2018 年 11 月 15 日以降、Microsoft Entra ID では、以前使用されていた、アプリの認証コードの受け入れが停止されます。 このセキュリティの変更により、Microsoft Entra ID と OAuth の仕様が一致するようになります。この変更は、v1 と v2 両方のエンドポイントに適用されます。

お使いのアプリで承認コードを再利用して複数のリソースに対するトークンを取得している場合は、コードを使用して更新トークンを取得した後、その更新トークンを使用して他のリソース用のトークンを追加取得することお勧めします。 承認コードは 1 回しか使用できませんが、更新トークンは複数のリソースで複数回使用できます。 OAuth コード フローの使用時に新しいアプリで認証コードを再利用しようとすると、invalid\_grant エラーが発生します。

更新トークンについて詳しくは、「[アクセス トークンの更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#refresh-the-access-token)」をご覧ください。 MSAL を使用している場合、これはライブラリによって自動的に処理されます。 `AcquireTokenByAuthorizationCodeAsync` の 2 番目のインスタンスを `AcquireTokenSilentAsync`に置き換えます。

### 2018 年 5 月

#### ID トークンは、OBO フローに使用できません

**日付**: 2018 年 5 月 1 日

**影響を受けたエンドポイント**: v1.0 と v2.0 の両方

**影響を受けたプロトコル**: 暗黙的フローと [代理フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)

2018 年 5 月 1 日以降、id\_tokens は新しいアプリケーションの OBO フローでアサーションとして使用できません。 代わりに、アクセス トークンを使用して、同じアプリケーションのクライアントと中間層の間でも、API のセキュリティを確保する必要があります。 2018 年 5 月 1 日より前に登録されたアプリは、引き続き動作し、id\_tokens をアクセス トークンに交換することができますが、このパターンはベスト プラクティスとは見なされません。

この変更を回避するには、次の操作を行います。

1. 1 つ以上のスコープを使用して、アプリケーション用の Web API を作成します。 この明示的なエントリ ポイントにより、きめ細かな制御とセキュリティが可能になります。
2. アプリのマニフェストで、[Azure ポータル](https://portal.azure.com)または [app 登録ポータル](https://apps.dev.microsoft.com)で、暗黙的フローを介してアクセス トークンを発行できることを確認します。 これは `oauth2AllowImplicitFlow` キーによって制御されます。
3. クライアント アプリケーションで `response_type=id_token` を使用して id\_token を要求した場合、上記で作成した Web API に対してもアクセス トークン (`response_type=token`) を要求します。 したがって、v2.0 エンドポイントを使用する場合、`scope` パラメータは `api://GUID/SCOPE` と同様のものになります。 v1.0 エンドポイントでは、`resource` パラメータを Web API のアプリ URI にする必要があります。
4. このアクセストークンを、id\_token の代わりに、中間層に渡します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/reference-claims-customization"} -->
## 要求のカスタマイズ - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-claims-customization
- Service: identity-platform
- Article date: 2023-06-02
- Summary: カスタム要求ポリシーと要求マッピング ポリシーの種類について説明します。これは、Microsoft ID プラットフォームのトークンで出力される要求を変更するために使用されます。

ポリシー オブジェクトは、組織の個々のアプリケーションまたはすべてのアプリケーションに適用される規則のセットを表しています。 それぞれのポリシーの種類は、割り当てられているオブジェクトに適用されるプロパティのセットを含む一意の構造体を持ちます。

Microsoft Entra IDでは、アプリケーションに対して Microsoft Graph/PowerShell を使用して要求をカスタマイズする 2 つの方法がサポートされています。

- [カスタム クレーム ポリシー (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-customization-custom-claims-policy) の使用
- [要求マッピング ポリシー](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-customization-powershell)の使用

カスタム要求ポリシーと要求マッピング ポリシーは、トークン内に含まれる要求を変更するポリシー オブジェクトの異なる 2 つのタイプです。

[カスタム要求ポリシー (プレビュー)](https://learn.microsoft.com/ja-jp/graph/api/resources/customclaimspolicy) を使用すると、管理者はアプリケーションに対する追加の要求をカスタマイズできます。 [claims のカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)と同じ意味で使用できます。このカスタマイズはMicrosoft Entra 管理センターを通じて提供され、管理者はMicrosoft Entra 管理センターまたは MS Graph/PowerShell を使用して要求を管理できます。 カスタム要求ポリシーと[要求のカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)の両方Microsoft Entra 管理センター使用して、同じ基になるポリシーを使用して、サービス プリンシパルの追加要求を構成します。 ただし、管理者が 1 つのサービス プリンシパルに対して構成できる[カスタム要求ポリシー (プレビュー)](https://learn.microsoft.com/ja-jp/graph/api/resources/customclaimspolicy) は 1 つだけです。 `PUT` メソッドによって管理者はポリシー オブジェクトを作成したり、既存のポリシー オブジェクトを要求本文内で渡された値に置き換えたりすることができるのに対して、`PATCH` メソッドでは管理者は要求本文内で渡された値でポリシー オブジェクトを更新することができます。 カスタム要求ポリシーを使用して追加の要求を[構成して管理する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-customization-custom-claims-policy)をこちらで確認してください。

管理者は[要求マッピング ポリシー](https://learn.microsoft.com/ja-jp/graph/api/resources/claimsmappingpolicy)でもアプリケーションに対する追加の要求をカスタマイズすることができます。 管理者は、1 つの要求マッピング ポリシーを構成し、それをテナント内の複数のアプリケーションに割り当てることができます。 管理者が要求マッピング ポリシーを使用してアプリケーションの追加の要求を管理することを選択した場合、それらのアプリケーションのMicrosoft Entra 管理センターの [[c0>カスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization) ブレードで要求を編集または更新することはできません。 要求マッピング ポリシーを使用して追加の要求を[構成して管理する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-customization-powershell)をこちらで確認してください。

注

要求マッピング ポリシーは、カスタムクレーム ポリシーと [c0](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)Microsoft Entra 管理センターを通じて提供されるカスタマイズの両方に優先します。 要求マッピング ポリシーを使用してアプリケーションの要求をカスタマイズすると、そのアプリケーションに対して発行されたトークンは、カスタム要求ポリシーの構成、またはMicrosoft Entra 管理センターの [c0](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization) ブレードの構成が無視されることを意味します。 要求のカスタマイズの詳細については、「[エンタープライズ アプリケーションに対するトークン内で発行された要求のカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)」を参照してください。

### 要求セット

次の表に示す要求セットは、トークンで要求をいつ、どのように使用するかを定義したものです。

| 要求セット | 説明 |
| --- | --- |
| コア要求セット | ポリシーに関係なく、すべてのトークンに存在します。 また、この要求は制限付きと見なされ、変更できません。 |
| 基本要求セット | コア要求セットに加えて、既定でトークンに含められる要求が入っています。 カスタム要求ポリシーと要求マッピング ポリシーを使用することで、基本要求を省略または変更することができます。 |
| 制限付き要求セット | ポリシーを使用して変更することはできません。 データ ソースを変更できず、要求を生成するときに変換が適用されません。 |

#### JSON Web トークン (JWT) 制限付き要求セット

JWT の制限付き要求セットには、以下の要求が含まれます。

- `.`
- `_claim_names`
- `_claim_sources`
- `aai`
- `access_token`
- `account_type`
- `acct`
- `acr`
- `acrs`
- `actor`
- `actortoken`
- `ageGroup`
- `aio`
- `altsecid`
- `amr`
- `app_chain`
- `app_displayname`
- `app_res`
- `appctx`
- `appctxsender`
- `appid`
- `appidacr`
- `assertion`
- `at_hash`
- `aud`
- `auth_data`
- `auth_time`
- `authorization_code`
- `azp`
- `azpacr`
- `bk_claim`
- `bk_enclave`
- `bk_pub`
- `brk_client_id`
- `brk_redirect_uri`
- `c_hash`
- `ca_enf`
- `ca_policy_result`
- `capolids`
- `capolids_latebind`
- `cc`
- `cert_token_use`
- `child_client_id`
- `child_redirect_uri`
- `client_id`
- `client_ip`
- `cloud_graph_host_name`
- `cloud_instance_host_name`
- `cloud_instance_name`
- `CloudAssignedMdmId`
- `cnf`
- `code`
- `controls`
- `controls_auds`
- `credential_keys`
- `csr`
- `csr_type`
- `ctry`
- `deviceid`
- `dns_names`
- `domain_dns_name`
- `domain_netbios_name`
- `e_exp`
- `email`
- `endpoint`
- `enfpolids`
- `exp`
- `expires_on`
- `extn. as prefix`
- `fido_auth_data`
- `fido_ver`
- `fwd`
- `fwd_appidacr`
- `grant_type`
- `graph`
- `group_sids`
- `groups`
- `hasgroups`
- `hash_alg`
- `haswids`
- `home_oid`
- `home_puid`
- `home_tid`
- `iat`
- `identityprovider`
- `idp`
- `idtyp`
- `in_corp`
- `instance`
- `inviteTicket`
- `ipaddr`
- `isbrowserhostedapp`
- `iss`
- `isViral`
- `jwk`
- `key_id`
- `key_type`
- `login_hint`
- `mam_compliance_url`
- `mam_enrollment_url`
- `mam_terms_of_use_url`
- `mdm_compliance_url`
- `mdm_enrollment_url`
- `mdm_terms_of_use_url`
- `msgraph_host`
- `msproxy`
- `nameid`
- `nbf`
- `netbios_name`
- `nickname`
- `nonce`
- `oid`
- `on_prem_id`
- `onprem_sam_account_name`
- `onprem_sid`
- `openid2_id`
- `origin_header`
- `password`
- `platf`
- `polids`
- `pop_jwk`
- `preferred_username`
- `previous_refresh_token`
- `primary_sid`
- `prov_data`
- `puid`
- `pwd_exp`
- `pwd_url`
- `rdp_bt`
- `redirect_uri`
- `refresh_token`
- `refresh_token_issued_on`
- `refreshtoken`
- `request_nonce`
- `resource`
- `rh`
- `role`
- `roles`
- `rp_id`
- `rt_type`
- `scope`
- `scp`
- `secaud`
- `sid`
- `sid`
- `signature`
- `signin_state`
- `source_anchor`
- `src1`
- `src2`
- `sub`
- `target_deviceid`
- `tbid`
- `tbidv2`
- `tenant_ctry`
- `tenant_display_name`
- `tenant_id`
- `tenant_region_scope`
- `tenant_region_sub_scope`
- `thumbnail_photo`
- `tid`
- `tokenAutologonEnabled`
- `trustedfordelegation`
- `ttr`
- `unique_name`
- `upn`
- `user_agent`
- `user_setting_sync_url`
- `username`
- `uti`
- `ver`
- `verified_primary_email`
- `verified_secondary_email`
- `vnet`
- `vsm_binding_key`
- `wamcompat_client_info`
- `wamcompat_id_token`
- `wamcompat_scopes`
- `wids`
- `win_ver`
- `x5c_ca`
- `xcb2b_rclient`
- `xcb2b_rcloud`
- `xcb2b_rtenant`
- `ztdid`

注

`xms_` で始まる要求はすべて、制限付きです。

#### SAML 制限付き要求セット

次の表に、制限付き要求セットに含まれる SAML 要求を示します。

制限付き要求の種類 (URI):

- `http://schemas.microsoft.com/2012/01/devicecontext/claims/ismanaged`
- `http://schemas.microsoft.com/2014/02/devicecontext/claims/isknown`
- `http://schemas.microsoft.com/2014/03/psso`
- `http://schemas.microsoft.com/2014/09/devicecontext/claims/iscompliant`
- `http://schemas.microsoft.com/claims/authnmethodsreferences`
- `http://schemas.microsoft.com/claims/groups.link`
- `http://schemas.microsoft.com/identity/claims/accesstoken`
- `http://schemas.microsoft.com/identity/claims/acct`
- `http://schemas.microsoft.com/identity/claims/agegroup`
- `http://schemas.microsoft.com/identity/claims/aio`
- `http://schemas.microsoft.com/identity/claims/identityprovider`
- `http://schemas.microsoft.com/identity/claims/objectidentifier`
- `http://schemas.microsoft.com/identity/claims/openid2_id`
- `http://schemas.microsoft.com/identity/claims/puid`
- `http://schemas.microsoft.com/identity/claims/scope`
- `http://schemas.microsoft.com/identity/claims/tenantid`
- `http://schemas.microsoft.com/identity/claims/xms_et`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/authenticationinstant`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/authenticationmethod`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/confirmationkey`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/denyonlyprimarygroupsid`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/denyonlyprimarysid`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/denyonlywindowsdevicegroup`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/expiration`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/expired`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/groups`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/groupsid`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/ispersistent`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/role`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/role`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/samlissuername`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/wids`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/windowsdeviceclaim`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/windowsdevicegroup`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/windowsfqbnversion`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/windowssubauthority`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/windowsuserclaim`
- `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/authentication`
- `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/authorizationdecision`
- `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/denyonlysid`
- `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/privatepersonalidentifier`
- `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/spn`
- `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/upn`
- `http://schemas.xmlsoap.org/ws/2009/09/identity/claims/actor`

これらの要求\*は既定では\*制限されますが、[カスタム署名キー*](https://learn.microsoft.com/ja-jp/entra/identity-platform/jwt-claims-customization#configure-a-custom-signing-key)がある場合 は制限されません。 アプリ マニフェストで `acceptMappedClaims` を設定しないでください。

- `http://schemas.microsoft.com/ws/2008/06/identity/claims/windowsaccountname`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/primarysid`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/primarygroupsid`
- `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/sid`
- `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/x500distinguishedname`

これらの要求\*は既定では\*制限されますが、[カスタム署名キー*](https://learn.microsoft.com/ja-jp/entra/identity-platform/jwt-claims-customization#configure-a-custom-signing-key)がある場合 は制限されません：

- `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/upn`
- `http://schemas.microsoft.com/ws/2008/06/identity/claims/role`

### 要求のカスタマイズに使用されるポリシーのプロパティ

含められる要求と、データのソースを制御するには、要求のカスタマイズに対するポリシーのプロパティを使用します。 ポリシーがない場合、システムは、次の要求が含まれるトークンを発行します。

- コア要求セット。
- 基本要求セット。
- アプリケーションが受信することを選択した、[省略可能な要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)。

注

コア要求セット内の要求については、このプロパティの設定に関係なく、すべてのトークンに提示されます。

| 糸 | データ型 | まとめ |
| --- | --- | --- |
| **IncludeBasicClaimSet** | ブール値 (True または False) | このポリシーの影響を受けるトークンに、基本要求セットを含めるかどうかを決定します。 True に設定されている場合、基本要求セット内のすべての要求が、ポリシーの影響を受けるトークンに出力されます。 False に設定されている場合、基本要求セット内の要求は、同じポリシーの要求スキーマ プロパティに個別に追加されない限り、トークンに追加されません。 |
| **ClaimsSchema** | 1 つ以上の要求スキーマ エントリを含む JSON BLOB | 基本要求セットおよびコア要求セットに加えて、このポリシーの影響を受けるトークンに含める要求を定義します。 このプロパティで定義されている要求スキーマ エントリごとに、特定の情報が必要です。 データのソース (**Value**、**Source/ID ペア**、または **Source/ExtensionID ペア**) と、**要求の種類** (**JWTClaimType** または **SamlClaimType** として出力される) を指定します。 |

#### 要求スキーマ エントリ要素

- **Value** - 要求で出力されるデータとして静的な値を定義します。
- **SAMLNameForm**- この要求の NameFormat 属性の値を定義します。 存在する場合、使用可能な値は次のとおりです。
    - `urn:oasis:names:tc:SAML:2.0:attrname-format:unspecified`
    - `urn:oasis:names:tc:SAML:2.0:attrname-format:uri`
    - `urn:oasis:names:tc:SAML:2.0:attrname-format:basic`
- **Source/ID ペア** - 要求のデータのソースを定義します。
- **Source/ExtensionID ペア** - 要求のデータのソースであるディレクトリ拡張属性を定義します。 詳細については、「[要求でディレクトリ拡張属性を使用する](https://learn.microsoft.com/ja-jp/entra/identity-platform/schema-extensions)」を参照してください。
- **要求の種類** - **JwtClaimType** および **SamlClaimType**要素は、この要求スキーマ エントリが、どの要求を参照するかを定義します。
    - **JwtClaimType** には、JWT で出力される要求の名前を含める必要があります。
    - **SamlClaimType** には、SAML トークンで出力される要求の URI を含める必要があります。

**Source** 要素を、次の表のいずれかの値に設定します。

| 送信元の値 | 要求内のデータ |
| --- | --- |
| `user` | User オブジェクトのプロパティ。 |
| `application` | アプリケーション (クライアント) サービス プリンシパルのプロパティ。 |
| `resource` | リソース サービス プリンシパルのプロパティ。 |
| `audience` | トークンのオーディエンスであるサービス プリンシパル (クライアントまたはリソースのサービス プリンシパル) のプロパティ。 |
| `company` | リソース テナントの Company オブジェクトのプロパティ。 |
| `transformation` | 要求の変換。 この要求を使用する場合、**TransformationID** 要素を、要求の定義に含める必要があります。 **TransformationID** 要素は、この要求のデータを生成する方法を定義する、**ClaimsTransformation** プロパティの変換エントリの ID 要素と一致する必要があります。 |

ID 要素は、要求の値を提供するソースのプロパティを特定します。 次の表に、Source の各値に対する ID 要素の値を示します。

| source | ID | 説明 |
| --- | --- | --- |
| `user` | `surname` | ユーザーの姓。 |
| `user` | `givenname` | ユーザーの姓名の名。 |
| `user` | `displayname` | ユーザーの表示名です。 |
| `user` | `objectid` | ユーザーのオブジェクト ID。 |
| `user` | `mail` | ユーザーのメール アドレス。 |
| `user` | `userprincipalname` | ユーザーのユーザー プリンシパル名。 |
| `user` | `department` | ユーザーの部署。 |
| `user` | `onpremisessamaccountname` | ユーザーのオンプレミスの SAM アカウント名。 |
| `user` | `netbiosname` | ユーザーの NetBios 名。 |
| `user` | `dnsdomainname` | ユーザーの DNS ドメイン名。 |
| `user` | `onpremisesecurityidentifier` | ユーザーのオンプレミスのセキュリティ ID。 |
| `user` | `companyname` | ユーザーの組織名。 |
| `user` | `streetaddress` | ユーザーの番地。 |
| `user` | `postalcode` | ユーザーの郵便番号。 |
| `user` | `preferredlanguage` | ユーザーの、優先する言語。 |
| `user` | `onpremisesuserprincipalname` | ユーザーのオンプレミスの UPN。 代替 ID を使用する場合、オンプレミスの属性 `userPrincipalName` が `onPremisesUserPrincipalName` 属性と同期されます。 この属性は、代替 ID が構成されている場合にのみ使用できます。 |
| `user` | `mailnickname` | ユーザーのメール ニックネーム。 |
| `user` | `extensionattribute1` | 拡張属性 1。 |
| `user` | `extensionattribute2` | 拡張属性 2。 |
| `user` | `extensionattribute3` | 拡張属性 3。 |
| `user` | `extensionattribute4` | 拡張属性 4。 |
| `user` | `extensionattribute5` | 拡張属性 5。 |
| `user` | `extensionattribute6` | 拡張属性 6。 |
| `user` | `extensionattribute7` | 拡張属性 7。 |
| `user` | `extensionattribute8` | 拡張属性 8。 |
| `user` | `extensionattribute9` | 拡張属性 9。 |
| `user` | `extensionattribute10` | 拡張属性 10。 |
| `user` | `extensionattribute11` | 拡張属性 11。 |
| `user` | `extensionattribute12` | 拡張属性 12。 |
| `user` | `extensionattribute13` | 拡張属性 13。 |
| `user` | `extensionattribute14` | 拡張属性 14。 |
| `user` | `extensionattribute15` | 拡張属性 15。 |
| `user` | `othermail` | ユーザーの他のメール。 |
| `user` | `country` | ユーザーの国/地域。 |
| `user` | `city` | ユーザーの市区町村。 |
| `user` | `state` | ユーザーの都道府県。 |
| `user` | `jobtitle` | ユーザーの役職。 |
| `user` | `employeeid` | ユーザーの従業員 ID。 |
| `user` | `facsimiletelephonenumber` | ユーザーのファックスの電話番号。 |
| `user` | `assignedroles` | ユーザーに割り当てられたアプリ ロールの一覧。 |
| `user` | `accountEnabled` | ユーザー アカウントが有効かどうかを示します。 |
| `user` | `consentprovidedforminor` | 未成年者に対する同意が提供されたかどうかを示します。 |
| `user` | `createddatetime` | ユーザー アカウントが作成された日時。 |
| `user` | `creationtype` | ユーザー アカウントがどのように作成されたかを示します。 |
| `user` | `lastpasswordchangedatetime` | パスワードが最後に変更された日時。 |
| `user` | `mobilephone` | ユーザーの携帯電話。 |
| `user` | `officelocation` | ユーザーのオフィスの所在地。 |
| `user` | `onpremisesdomainname` | ユーザーのオンプレミスのドメイン名。 |
| `user` | `onpremisesimmutableid` | ユーザーのオンプレミスの不変 ID。 |
| `user` | `onpremisessyncenabled` | オンプレミスの同期が有効かどうかを示します。 |
| `user` | `preferreddatalocation` | ユーザーの、優先されるデータの場所を定義します。 |
| `user` | `proxyaddresses` | ユーザーのプロキシ アドレス。 |
| `user` | `usertype` | ユーザー アカウントの種類。 |
| `user` | `telephonenumber` | ユーザーの会社またはオフィスの電話。 |
| `application`、`resource`、`audience` | `displayname` | オブジェクトの表示名。 |
| `application`、`resource`、`audience` | `objectid` | オブジェクトの ID。 |
| `application`、`resource`、`audience` | `tags` | オブジェクトのサービス プリンシパル タグ。 |
| `company` | `tenantcountry` | テナントの国/地域。 |

ユーザー オブジェクトで使用可能な複数値要求ソースは、Active Directory Connect から同期された複数値の拡張属性のみです。 その他のプロパティ (`othermails` や `tags` など) は、複数値ではありますが、ソースとして選択されたときに出力される値は 1 つだけです。

制限付き要求セット内の要求の名前と URI は、要求の種類の要素に使用することはできません。

#### グループ フィルター

- **文字列** - GroupFilter
- **データ型:** - JSON BLOB
- **概要** - このプロパティを使用して、グループ要求に含めるユーザーのグループにフィルターを適用します。 このプロパティは、トークンのサイズを小さくする便利な方法です。
- **MatchOn:** - フィルターを適用するグループ属性を特定します。 **MatchOn**プロパティは、次のいずれかの値に設定します。
    - `displayname` - グループの表示名。
    - `samaccountname` - オンプレミスの SAM アカウント名。
- **Type** - **MatchOn** プロパティで選択された属性に適用するフィルターの種類を定義します。 **Type**プロパティは、次のいずれかの値に設定します。
    - `prefix` - **MatchOn** プロパティの先頭が、指定された **Value** プロパティであるグループを含めます。
    - `suffix` - **MatchOn** プロパティの末尾が、指定された **Value** プロパティであるグループを含めます。
    - `contains` - **MatchOn** プロパティに、指定された **Value** プロパティが含まれるグループを含めます。

#### 要求の変換

- **文字列** - ClaimsTransformation
- **データ型** - 1 つ以上の変換エントリを含む JSON BLOB
- **概要** - このプロパティを使用して、共通の変換をソース データに適用し、要求スキーマで指定された要求の出力データを生成します。
- **ID** - TransformationID 要求スキーマ エントリの変換エントリを参照します。 この値は、このポリシー内の変換エントリごとに一意である必要があります。
- **TransformationMethod** - 要求のデータを生成するときに実行される操作を特定します。

選択した方法に基づいて、一連の入力と出力が想定されます。 **InputClaims** 要素、**InputParameters** 要素、**OutputClaims** 要素を使用して入出力を定義します。

| トランスフォーメーションメソッド | 想定される入力 | 想定される出力 | 説明 |
| --- | --- | --- | --- |
| **接続** | string1、string2、separator | 出力要求 | 入力文字列の間に区切り記号を使用して、その文字列を結合します。 例えば、string1:`foo@bar.com`、string2:`sandbox`、separator:`.` の結果は、出力要求: `foo@bar.com.sandbox` になります。 |
| **ExtractMailPrefix** | 電子メールまたは UPN | 抽出された文字列 | ユーザーの UPN またはメール アドレスの値を格納する、拡張属性 1-15 または他のディレクトリ拡張。 たとえば、「 `johndoe@contoso.com` 」のように入力します。 メール アドレスのローカル部分を抽出します。 例えば、mail:`foo@bar.com` の結果は、出力要求: `foo` になります。 @ 記号がない場合、元の入力文字列が返されます。 |
| **ToLowercase()** | 文字列 | 出力文字列 | 選択した属性の文字を小文字に変換します。 |
| **ToUppercase()** | 文字列 | 出力文字列 | 選択した属性の文字を大文字に変換します。 |
| **RegexReplace()** |  |  | RegexReplace() 変換は、入力パラメーターとして次を受け入れます。- パラメーター1：正規表現入力としてのユーザー属性- ソースを複数値として信頼するオプション‐正規表現パターン‐置換パターン。 置換パターンには、正規表現出力グループを指す参照、および追加の入力パラメーターと共に、静的テキスト形式を含めることができます。 |

- **InputClaims** - 要求スキーマ エントリから変換にデータを渡すために使用されます。 **ClaimTypeReferenceId**、**TransformationClaimType**、**TreatAsMultiValue**という 3 つの属性があります。
    - **ClaimTypeReferenceId** - 要求スキーマ エントリの ID 要素と結合され、該当する入力要求を検索します。
    - **TransformationClaimType** は、この入力に一意の名前を指定します。 この名前は、変換方法に対する想定される入力のいずれかと一致する必要があります。
    - **TreatAsMultiValue** は、変換の適用対象がすべての値か、それとも最初の値のみかを示すブール値のフラグです。 既定では、複数値の要求における、最初の要素にのみ変換が適用されます。 この値を true に設定すると、すべてに適用されます。 複数値の要求として処理する可能性が高い入力要求の例として、ProxyAddresses と groups の 2 つがあります。
- **InputParameters** - 定数値を変換に渡します。 この要素には 2 つの属性があります。**Value** と **ID**です。
    - **Value** は、渡される実際の定数値です。
    - **ID** は、入力に一意の名前を指定するときに使用されます。 この名前は、変換方法に対する想定される入力のいずれかと一致する必要があります。
- **OutputClaims** - 変換によって生成されたデータを保持し、要求スキーマ エントリに関連付けます。 この要素には 2 つの属性があります。**ClaimTypeReferenceId** と **TransformationClaimType**です。
    - **ClaimTypeReferenceId** は要求スキーマ エントリの ID と結合され、該当する出力要求を検索します。
    - **TransformationClaimType** は、出力に一意の名前を指定するときに使用されます。 この名前は、変換方法に対する想定される出力のいずれかと一致する必要があります。

#### 例外と制限事項

**SAML NameID と UPN** - NameID と UPN の値のソースとなる属性と、許可される要求変換には、制限があります。 カスタム要求プロバイダー属性は、MICROSOFT ENTRA ID SAML SSO では NameID ソースとして使用できません。

| source | ID | 説明 |
| --- | --- | --- |
| `user` | `mail` | ユーザーのメール アドレス。 |
| `user` | `userprincipalname` | ユーザーのユーザー プリンシパル名。 |
| `user` | `onpremisessamaccountname` | オンプレミスの Sam アカウント名 |
| `user` | `employeeid` | ユーザーの従業員 ID。 |
| `user` | `telephonenumber` | ユーザーの会社またはオフィスの電話。 |
| `user` | `extensionattribute1` | 拡張属性 1。 |
| `user` | `extensionattribute2` | 拡張属性 2。 |
| `user` | `extensionattribute3` | 拡張属性 3。 |
| `user` | `extensionattribute4` | 拡張属性 4。 |
| `user` | `extensionattribute5` | 拡張属性 5。 |
| `user` | `extensionattribute6` | 拡張属性 6。 |
| `user` | `extensionattribute7` | 拡張属性 7。 |
| `user` | `extensionattribute8` | 拡張属性 8。 |
| `user` | `extensionattribute9` | 拡張属性 9。 |
| `user` | `extensionattribute10` | 拡張属性 10。 |
| `user` | `extensionattribute11` | 拡張属性 11。 |
| `user` | `extensionattribute12` | 拡張属性 12。 |
| `user` | `extensionattribute13` | 拡張属性 13。 |
| `user` | `extensionattribute14` | 拡張属性 14。 |
| `User` | `extensionattribute15` | 拡張属性 15。 |

次の表に示す変換方法は、SAML NameID に対して許可されています。

| トランスフォーメーションメソッド | 制限 |
| --- | --- |
| **ExtractMailPrefix** | なし |
| **接続** | 結合されているサフィックスは、リソース テナントの確認済みドメインである必要があります。 |

#### アプリケーション ID 付きの発行者

- **文字列** - issuerWithApplicationId
- **データ型**- ブール値 (True または False)
    - `True` に設定した場合、ポリシーの影響を受けるトークンの発行者の要求にアプリケーション ID が追加されます。
    - `False` に設定した場合、ポリシーの影響を受けるトークンの発行者の要求にアプリケーション ID が追加されません。 (既定値)。
- **概要** - 発行者の要求にアプリケーション ID を含めることができるようにします。 同じアプリケーションの複数のインスタンスが、各インスタンスで一意の要求値を持っていることを確認します。 アプリケーションのカスタム署名キーが構成されていない場合、この設定は無視されます。

#### 対象ユーザーのオーバーライド

- **文字列** - audienceOverride
- **データ型** - 文字列
- **概要** - アプリケーションに送信されたオーディエンス要求のオーバーライドを可能にします。 指定した値は有効な絶対 URI である必要があります。 アプリケーションのカスタム署名キーが構成されていない場合、この設定は無視されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/reference-credential-management-api"} -->
## Microsoft Entra 外部 ID資格情報管理 API リファレンス - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-credential-management-api
- Service: identity-platform / external
- Article date: 2026-10-05
- Summary: Microsoft Entra 外部 ID資格情報管理 API を使用して、サインインしている顧客がパスキーを一覧表示して登録できるようにします。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra 外部 ID資格情報管理 API を使用すると、アプリケーションはサインインしている顧客に、パスキーを一覧表示および登録するためのセルフサービス フローを提供できます。 アプリケーションはクライアント エクスペリエンスを所有し、顧客の代わりに API を呼び出します。

資格情報管理 API は、[ネイティブ認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication)Microsoft Entra補完します。アプリケーションでは、ブラウザーに委任するのではなく、サインイン エクスペリエンスをホストします。 顧客がサインインした後、資格情報管理 API を使用します。

アプリでパスキー管理をサポートするには、 [パスキー資格情報管理サンプル アプリ](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/tree/main/passkey-sample)を使用します。 このサンプルでは、委任されたアクセス許可を使用して、この記事の一覧と登録のフローを示します。 サンプルの README に従って、アプリを構成して実行します。

Von Bedeutung

サンプルの削除フローでは、高い特権を持つアプリケーションのアクセス許可と、ブラウザー コード内のクライアント シークレットを持つMicrosoft Graphが引き続き使用されます。 テスト テナントでのみサンプルを実行します。 運用環境にデプロイしないでください。

成功したリソース応答では、HAL+JSON (`application/hal+json`) が使用されます。 アクティブ化要求とエラーでは JSON (`application/json`) が使用されます。

### Prerequisites

- Microsoft Entra の外部テナント。 外部テナントをまだ作成していない場合は、[ここで作成してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
- [Microsoft Entra 管理センターに登録されている](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)アプリケーション。 後で使用するために、 **アプリケーション (クライアント) ID** と **ディレクトリ (テナント) ID を** 記録します。

### 資格情報管理 API アクセスの構成

アプリが資格情報管理 API のアクセス トークンを取得する前に、API のサービス プリンシパルをプロビジョニングし、委任されたアクセス許可を追加します。

1. テナントで資格情報管理 API のサービス プリンシパルをプロビジョニングします。 資格情報管理 API は、Microsoft発行されたリソースです。 自動プロビジョニングが使用可能になるまでは、そのサービス プリンシパルを手動で作成する必要があります。 [Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)で、プロビジョニングするテナントの管理者としてサインインし、次のコマンドを実行します。

    ```http
    POST https://graph.microsoft.com/v1.0/servicePrincipals
    Content-Type: application/json
    
    {
      "appId": "6bf38b3c-a70f-49aa-a1d9-10e4cc74dde9"
    }
    ```
2. 資格情報管理 API の委任されたアクセス許可をアプリに追加します。 パスキーの場合、最小特権の選択肢は `Me.UserAuthenticationMethod.Passkey.Read` (読み取り) または `Me.UserAuthenticationMethod.Passkey.ReadWrite` (書き込み) です。 承認済みアクセス許可の完全な一覧については、「 認証と承認 」を参照してください。 テナントで必要な場合は、管理者の同意を付与します。

### 認証と承認

資格情報管理 API は、サインインしている顧客に代わって機能します。 すべてのエンドポイント URL にはフォーム `https://{tenant-subdomain}.ciamlogin.com/{tenant-id}/api/v1.0/me/...`があります。 `{tenant-subdomain}` は外部テナントのサブドメイン、 `{tenant-id}` は外部テナントの ID、 `me` は要求で送信するアクセス トークンを持つ顧客です。 アプリケーション:

1. 顧客にサインインします。
2. 資格情報管理 API のアクセス トークンを取得します。
3. すべての呼び出しにそのトークンが含まれます。

サインインしている顧客がいなければ、 `me` には意味がなく、呼び出しを認証することはできません。 Microsoft Graphまたはその他のMicrosoft Entra REST API を使用したことがある場合、これは同じモデル (委任されたアクセス許可を持つ標準 OAuth 2.0) に従います。

すべての要求の標準 HTTP `Authorization` ヘッダーにアクセス トークンを含めます。

```http
Authorization: Bearer <access_token>
```

#### 委任されたアクセス トークンを取得する

委任されたアクセス トークンを使用して、サインインしている顧客の代わりに資格情報管理 API を呼び出します。 API は、クライアント資格情報フローを通じて取得されたトークンを含め、アプリ専用トークンを拒否します。 顧客がサインインしたら、「 必要なアクセス許可」に記載されているアクセス許可のいずれかを要求します。 要求されたアクセス許可によって、トークンの対象ユーザーが決定されます。対象ユーザーを個別に設定しません。

次の図は、顧客のサインインから、結果のトークンを使用した API の呼び出しまで、呼び出しがどのように認証されるかを示しています。

[Image: アプリが顧客にサインインして資格情報管理 API のアクセス トークンを取得し、そのトークンを使用して API を呼び出し、検証Microsoft Entra 外部 ID示すシーケンス図。]

アプリケーションに適用されるMicrosoft Entraサインイン フローを通じて、委任されたトークンを取得します。

- シングルページ アプリ、モバイル アプリ、デスクトップ アプリ: [Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) を使用します。
- サーバー側 Web アプリ: PKCE で [OAuth 2.0 承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) を使用します。
- ネイティブ認証を使用するアプリ: [ネイティブ認証 API](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api) を使用して顧客にサインインまたはサインアップし、 `/token` エンドポイントでアクセス トークンの結果を引き換えます。

Note

ネイティブ認証の場合、現時点では Web エクスペリエンスのみがサポートされています。 モバイル プラットフォーム (Android および iOS) でのネイティブ認証は、資格情報管理 API では現在サポートされていません。

結果のトークンを、すべての資格情報管理 API 呼び出しで `Authorization: Bearer <access_token>` として渡します。

#### 必要なアクセス許可

すべてのアクセス許可は、資格情報管理 API によって公開される委任されたアクセス許可です。 アプリ登録に追加し、顧客のサインイン時に要求します。 このリリースではパスキーのみがサポートされるため、パスキー スコープのアクセス許可は最小特権の選択肢です。

| Operation | 承認済みの委任されたアクセス許可 (最小特権優先) |
| --- | --- |
| 読み取り (顧客のパスキーの一覧など) | `Me.UserAuthenticationMethod.Passkey.Read`、`Me.UserAuthenticationMethod.Read`、`Me.UserAuthenticationMethod.Passkey.ReadWrite`、`Me.UserAuthenticationMethod.ReadWrite` |
| 書き込み (パスキーの登録など) | `Me.UserAuthenticationMethod.Passkey.ReadWrite`、`Me.UserAuthenticationMethod.ReadWrite` |

アプリケーションのみのアクセス (クライアント資格情報フロー) はサポートされていません。

### 継続トークン

パスキーの登録などの複数ステップ操作を呼び出すと、資格情報管理 API は応答で継続トークンを返します。 このトークンは、現在のフローを一意に識別し、エンドポイント全体の状態Microsoft Entra維持できるようにします。 そのフロー内の後続のすべての要求にトークンを含めます。 これは限られた時間だけ有効であり、同じフロー内の後続の要求にのみ使用できます。

### サポートされている資格情報の方法

このリリースでは、資格情報管理 API では、次の 1 種類の資格情報方法がサポートされています。

| Method | URL セグメント (`{type}`) | Description |
| --- | --- | --- |
| パスキー | `fido` | WebAuthn 標準に基づくフィッシングに強い資格情報。 |

ソフトウェア OATH ワンタイム パスコード、電子メール、電話、回復方法などの他の資格情報方法は、この API ではまだサポートされていません。

### ユーザー資格情報の方法を一覧表示する

サインインしている顧客が現在登録している資格情報メソッドと、まだ登録できるメソッドの種類を返します。 たとえば、顧客が既存のパスキーを確認し、新しいパスキーの登録を開始できるページをレンダリングするときに、アプリがこのエンドポイントを呼び出します。

次のシーケンス図は、リスト フローを示しています。

[Image: 顧客のアクセス トークンを使用してリスト エンドポイントを呼び出し、HAL+ JSON に登録され、登録可能なメソッドを返Microsoft Entra 外部 IDアプリを示すシーケンス図。]

アプリケーションがこのエンドポイントを呼び出すために必要なアクセス トークンの 認証と承認 を参照してください。 このエンドポイントは、必要なアクセス許可に記載されている読み取りまたは書き込み アクセス許可のいずれかを受け入れます。

#### HTTP 要求

```http
GET https://{tenant-subdomain}.ciamlogin.com/{tenant-id}/api/v1.0/me/methods
```

`{tenant-subdomain}`URL には、外部テナントのサブドメイン (*たとえば、contoso.ciamlogin.com* の *contoso*) があります。

サンプルリクエスト:

```http
GET https://contoso.ciamlogin.com/8f1c8e2a-1234-4abc-9876-1f1e1d1c1b1a/api/v1.0/me/methods HTTP/1.1
Host: contoso.ciamlogin.com
Authorization: Bearer <access_token>
```

#### 要求パラメーター

パス パラメーター:

| 名前 | 必須 | Description |
| --- | --- | --- |
| `tenant-id` | Yes | テナント ID (GUID) または検証済みドメインのいずれかの外部テナント識別子。 `common`、`client`、`organizations`、または`consumers`ではなく、テナント固有の値を使用します。 GUID 値は、アクセス トークン内のテナントと一致する必要があります。 |

要求ヘッダー:

| 名前 | 必須 | 価値 |
| --- | --- | --- |
| `Authorization` | Yes | `Bearer <access_token>` |

このエンドポイントは、 `Accept`ヘッダー コンテンツ ネゴシエーションを実行しません。 `Accept`ヘッダー値に関係なく、応答は常に`application/hal+json`されます。

#### 成功応答

アップロードの成功の例を次に示します:

```http
HTTP/1.1 200 OK
Content-Type: application/hal+json
```

```json
{
  "_embedded": {
    "methods": [
      {
        "aaGuid": "<authenticator-aaguid>",
        "attestationLevel": "notAttested",
        "model": "Windows Hello VBS Hardware Authenticator",
        "passkeyType": "deviceBound",
        "displayName": "<display-name>",
        "id": "<credential-id-1>",
        "type": "fido",
        "createdDateTime": "<timestamp>",
        "_links": {
          "self": {
            "href": "/{tenant-id}/api/v1.0/me/methods/fido/{credential-id-1}",
            "name": "self"
          },
          "delete": {
            "href": "/{tenant-id}/api/v1.0/me/methods/fido/{credential-id-1}",
            "name": "delete"
          }
        }
      },
      {
        "aaGuid": "<authenticator-aaguid>",
        "attestationCertificates": [
          "<certificate-thumbprint>"
        ],
        "attestationLevel": "attested",
        "model": "YubiKey 5 FIPS Series with NFC",
        "passkeyType": "deviceBound",
        "displayName": "<display-name>",
        "id": "<credential-id-2>",
        "type": "fido",
        "createdDateTime": "<timestamp>",
        "_links": {
          "self": {
            "href": "/{tenant-id}/api/v1.0/me/methods/fido/{credential-id-2}",
            "name": "self"
          },
          "delete": {
            "href": "/{tenant-id}/api/v1.0/me/methods/fido/{credential-id-2}",
            "name": "delete"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "/{tenant-id}/api/v1.0/me/methods",
      "name": "self"
    },
    "enroll": [
      {
        "href": "/{tenant-id}/api/v1.0/me/methods/fido",
        "name": "fido"
      }
    ],
    "methods": [
      {
        "href": "/{tenant-id}/api/v1.0/me/methods/fido/{credential-id-1}",
        "name": "<credential-id-1>"
      },
      {
        "href": "/{tenant-id}/api/v1.0/me/methods/fido/{credential-id-2}",
        "name": "<credential-id-2>"
      }
    ]
  }
}
```

識別子から操作 URL を作成する代わりに、返された HAL リンクに従って一覧表示と登録を行います。

| 財産 | タイプ | 必須 | Description |
| --- | --- | --- | --- |
| `_embedded.methods` | オブジェクトの配列 | Yes | 登録済みの資格情報メソッド。 顧客に登録済みのメソッドがない場合でも、配列が存在します。 |
| `_links.self` | オブジェクト | Yes | credential-method コレクションへのリンク。 |
| `_links.enroll` | オブジェクトの配列 | No | 登録リンク。顧客が登録できる資格情報の種類ごとに 1 つ。 |
| `_links.methods` | オブジェクトの配列 | No | 顧客の登録済みメソッドへのリンク。 |

##### 登録済みの passkey プロパティ

認証子固有のプロパティは、異なる場合と存在しない場合があります。 たとえば、未フォーマットのパスキーでは `attestationCertificates`を省略できますが、構成証明されたデバイス バインド パスキーには証明書の値を含めることができます。

| 財産 | タイプ | 必須 | Description |
| --- | --- | --- | --- |
| `id` | 文字列 | Yes | 登録されたパスキーの永続的な大文字と小文字を区別する base64url 識別子。 |
| `type` | 文字列 | Yes | 資格情報メソッドの種類。 値は `fido` です。 |
| `createdDateTime` | 文字列 | No | パスキーが登録された時刻。 |
| `lastUsedDateTime` | 文字列 | No | パスキーが最後に使用された時刻。 |
| `aaGuid` | 文字列 | No | Authenticator Attestation GUID (AAGUID)。 |
| `attestationCertificates` | 文字列の配列 | No | 認証子が構成証明を提供する場合の構成証明証明書の値。 |
| `attestationLevel` | 文字列 | No | 認証子に対して報告された構成証明レベル。 |
| `model` | 文字列 | No | Authenticator モデル。 |
| `passkeyType` | 文字列 | No | サービスによって報告されるパスキー分類。 |
| `displayName` | 文字列 | No | 顧客が指定したパスキー名。 |
| `_links` | オブジェクト | Yes | `self` 登録されたパスキーのリンクを `delete` します。 削除する場合は、返された`delete` リンクの代わりにMicrosoft Graphを使用します。 |

このエンドポイントは、 `400`、 `401`、または `403`を返すことができます。 共有エンベロープと呼び出し元のアクションについては、「 エラー応答」を参照してください。

### Provisioning

サインインしている顧客のパスキーをプロビジョニングすると、2 つの HTTP 呼び出しが行われます。 まず、アプリケーションが登録開始エンドポイントを呼び出します。このエンドポイントは、WebAuthn 作成オプション、継続トークン、および `activate` リンクを返します。 顧客の認証子は、作成オプションを使用して新しいパスキーを作成します。 その後、アプリケーションによってアクティブ化エンドポイントが呼び出され、結果が表示され、登録が完了します。 顧客がアプリケーションの資格情報管理エクスペリエンスにパスキーを追加することを選択したら、このフローを開始します。

次のシーケンス図は、2 段階のプロビジョニング フローを示しています。

[Image: WebAuthn チャレンジと継続トークンを返す登録の開始、パスキーを作成する認証子、およびパスキーを Microsoft Entra 外部 ID に登録するアクティブ化呼び出しを示すシーケンス図。]

アプリケーションがこれらのエンドポイントを呼び出すために必要なアクセス トークンの 認証と承認 を参照してください。 どちらの呼び出しにも、書き込みアクセス許可、 `Me.UserAuthenticationMethod.Passkey.ReadWrite` 、または `Me.UserAuthenticationMethod.ReadWrite` が必要です ( 「必要なアクセス許可」を参照)。

#### 手順 1: 登録を開始する

##### HTTP 要求

```http
POST https://{tenant-subdomain}.ciamlogin.com/{tenant-id}/api/v1.0/me/methods/{type}
```

`{tenant-subdomain}`URL には、外部テナントのサブドメイン (*たとえば、contoso.ciamlogin.com* の *contoso*) があります。

サンプルリクエスト:

```http
POST https://contoso.ciamlogin.com/8f1c8e2a-1234-4abc-9876-1f1e1d1c1b1a/api/v1.0/me/methods/fido HTTP/1.1
Host: contoso.ciamlogin.com
Authorization: Bearer <access_token>
```

##### 要求パラメーター

パス パラメーター:

| 名前 | 必須 | Description |
| --- | --- | --- |
| `tenant-id` | Yes | テナント ID (GUID) または検証済みドメインのいずれかの外部テナント識別子。 `common`、`client`、`organizations`、または`consumers`ではなく、テナント固有の値を使用します。 GUID 値は、アクセス トークン内のテナントと一致する必要があります。 |
| `type` | Yes | 登録する資格情報メソッドの種類。 現在、 `fido` (パスキー) のみがサポートされています。 |

要求ヘッダー:

| 名前 | 必須 | 価値 |
| --- | --- | --- |
| `Authorization` | Yes | `Bearer <access_token>` |

このエンドポイントは要求本文を受け取りません。 すべての入力は、URL パスと `Authorization` ヘッダーに格納されます。

##### 成功応答

アップロードの成功の例を次に示します:

```http
HTTP/1.1 202 Accepted
Content-Type: application/hal+json
```

```json
{
  "publicKey": {
    "rp": {
      "id": "<relying-party-id>",
      "name": "Microsoft"
    },
    "user": {
      "id": "<user-handle>",
      "name": "<user-name>",
      "displayName": "<user-display-name>"
    },
    "challenge": "<challenge>",
    "pubKeyCredParams": [
      {
        "type": "public-key",
        "alg": -7
      },
      {
        "type": "public-key",
        "alg": -257
      }
    ],
    "timeout": 0,
    "excludeCredentials": [
      {
        "type": "public-key",
        "id": "<excluded-credential-id>",
        "transports": []
      }
    ],
    "authenticatorSelection": {
      "authenticatorAttachment": "cross-platform",
      "requireResidentKey": true,
      "userVerification": "required"
    },
    "attestation": "direct",
    "extensions": {
      "hmacCreateSecret": true,
      "enforceCredentialProtectionPolicy": true,
      "credentialProtectionPolicy": "userVerificationOptional"
    }
  },
  "challengeTimeout": "<timestamp>",
  "id": "<registration-id>",
  "type": "fido",
  "continuationToken": "<continuation-token>",
  "state": "interactionRequired",
  "action": "activate",
  "_links": {
    "self": {
      "href": "/{tenant-id}/api/v1.0/me/methods/fido/{registration-id}",
      "name": "self"
    },
    "activate": {
      "href": "/{tenant-id}/api/v1.0/me/methods/fido/{registration-id}/activate",
      "name": "activate"
    }
  }
}
```

応答には、次のプロパティがあります。

| 財産 | タイプ | 必須 | Description |
| --- | --- | --- | --- |
| `id` | 文字列 | Yes | 進行中の登録の一時的な識別子。 |
| `type` | 文字列 | Yes | 資格情報メソッドの種類。 値は `fido` です。 |
| `publicKey` | オブジェクト | No | クライアントの式の WebAuthn 資格情報の作成オプション。 |
| `challengeTimeout` | 文字列 | No | チャレンジの有効期限が切れる時刻。 |
| `state` | 文字列 | Yes | フローの状態。 値は `interactionRequired` です。 |
| `action` | 文字列 | Yes | 次のアクション。 値は `activate` です。 |
| `continuationToken` | 文字列 | Yes | アクティブ化中に返される必要がある不透明な状態。 |
| `_links` | オブジェクト | Yes | `self` 進行中の登録のリンクを `activate` します。 |

`publicKey` オブジェクトは[、webauthnPublicKeyCredentialCreationOptions](https://learn.microsoft.com/ja-jp/graph/api/resources/webauthnpublickeycredentialcreationoptions) の共通フィールドと一致します。 サービスは、前方互換性のために追加の WebAuthn プロパティを保持することもできます。

| 財産 | タイプ | 必須 | Description |
| --- | --- | --- | --- |
| `rp` | オブジェクト | No | 証明書利用者情報。 |
| `user` | オブジェクト | No | 登録式のユーザー情報。 |
| `challenge` | 文字列 | No | Base64url でエンコードされた WebAuthn チャレンジ。 |
| `pubKeyCredParams` | オブジェクトの配列 | No | サポートされている公開キー資格情報の種類とアルゴリズム。 |
| `timeout` | 整数 | No | 式のタイムアウト (ミリ秒単位)。 |
| `excludeCredentials` | オブジェクトの配列 | No | 認証子が除外する必要がある既存の資格情報。 |
| `authenticatorSelection` | オブジェクト | No | Authenticator の選択要件。 |
| `attestation` | 文字列 | No | 要求された構成証明の伝達の優先順位。 |
| `hints` | 文字列の配列 | No | オプションの認証ヒント。 |
| `extensions` | オブジェクト | No | WebAuthn 拡張機能の入力。 |

`publicKey`を顧客の認証システムに渡して、パスキーを作成します。 `continuationToken`を正確に保持し、アクティブ化要求の`_links.activate.href`に従います。

登録開始エンドポイントは、 `400`、 `401`、 `403`、または `500`を返すことができます。 共有エンベロープと呼び出し元のアクションについては、「 エラー応答」を参照してください。

#### 手順 2: パスキーをアクティブにする

##### HTTP 要求

```http
POST https://{tenant-subdomain}.ciamlogin.com/{tenant-id}/api/v1.0/me/methods/{type}/{id}/activate
```

`{tenant-subdomain}`URL には、外部テナントのサブドメイン (*たとえば、contoso.ciamlogin.com* の *contoso*) があります。

サンプルリクエスト:

```http
POST https://contoso.ciamlogin.com/8f1c8e2a-1234-4abc-9876-1f1e1d1c1b1a/api/v1.0/me/methods/fido/{registration-id}/activate HTTP/1.1
Host: contoso.ciamlogin.com
Authorization: Bearer <access_token>
Content-Type: application/json

{
  "continuationToken": "<continuation-token>",
  "displayName": "My laptop",
  "publicKeyCredential": {
    "id": "<credential-id>",
    "attestationObject": "<attestation-object>",
    "clientDataJSON": "<client-data-json>"
  }
}
```

##### 要求パラメーター

パス パラメーター:

| 名前 | 必須 | Description |
| --- | --- | --- |
| `tenant-id` | Yes | テナント ID (GUID) または検証済みドメインのいずれかの外部テナント識別子。 `common`、`client`、`organizations`、または`consumers`ではなく、テナント固有の値を使用します。 GUID 値は、アクセス トークン内のテナントと一致する必要があります。 |
| `type` | Yes | 登録されている資格情報メソッドの種類。 現在、 `fido` (パスキー) のみがサポートされています。 登録の開始手順で使用した値と一致する必要があります。 |
| `id` | Yes | 登録の開始手順によって返される進行中の登録識別子。 自分で作成するのではなく、登録の開始応答の `_links.activate.href` から取得します。 |

要求ヘッダー:

| 名前 | 必須 | 価値 |
| --- | --- | --- |
| `Authorization` | Yes | `Bearer <access_token>` |
| `Content-Type` | Yes | `application/json` |

##### リクエスト本文

次のフィールドを持つ JSON オブジェクト。

| 名前 | タイプ | 必須 | Description |
| --- | --- | --- | --- |
| `continuationToken` | 文字列 | Yes | 登録開始応答によって返される不透明なトークン。 進行中の登録のサーバー側の状態が保持されるため、Microsoft Entraは中断したところから正確に再開できます。 不透明として扱い、解析や変更は行いません。 継続トークンを参照してください。 |
| `displayName` | 文字列 | No | お客様がパスキーを付けるフレンドリ名 ( *マイ ノート PC* など)。 |
| `publicKeyCredential` | オブジェクト | No | 顧客の認証子によって生成された WebAuthn 資格情報データ。 |

`publicKeyCredential`値は、WebAuthn [PublicKeyCredential](https://www.w3.org/TR/webauthn-2/#iface-publickeycredential) から派生します。 この API は、資格情報 ID、構成証明応答、拡張機能の結果を次のプロパティにフラット化します。

| 名前 | タイプ | 必須 | Description |
| --- | --- | --- | --- |
| `id` | 文字列 | No | 認証子によって返される Base64url でエンコードされた資格情報識別子。 |
| `attestationObject` | 文字列 | No | 認証子によって返される Base64url でエンコードされた構成証明オブジェクト。 |
| `clientDataJSON` | 文字列 | No | 認証子によって返される Base64url でエンコードされたクライアント データ。 |
| `clientExtensionResults` | 文字列 | No | シリアル化された WebAuthn クライアント拡張機能の結果。 |

##### 成功応答

アップロードの成功の例を次に示します:

```http
HTTP/1.1 201 Created
Content-Type: application/hal+json
```

```json
{
  "aaGuid": "<authenticator-aaguid>",
  "attestationLevel": "notAttested",
  "model": "Windows Hello VBS Hardware Authenticator",
  "passkeyType": "deviceBound",
  "displayName": "<display-name>",
  "id": "<credential-id>",
  "type": "fido",
  "createdDateTime": "<timestamp>",
  "_links": {
    "self": {
      "href": "/{tenant-id}/api/v1.0/me/methods/fido/{credential-id}",
      "name": "self"
    },
    "delete": {
      "href": "/{tenant-id}/api/v1.0/me/methods/fido/{credential-id}",
      "name": "delete"
    }
  }
}
```

アクティブ化応答には、次のプロパティがあります。

| 財産 | タイプ | 必須 | Description |
| --- | --- | --- | --- |
| `id` | 文字列 | Yes | 登録済みのパスキーに対して返される識別子。 |
| `type` | 文字列 | Yes | 資格情報メソッドの種類。 値は `fido` です。 |
| `createdDateTime` | 文字列 | No | 登録が完了した時刻。 |
| `displayName` | 文字列 | No | 顧客が指定したパスキー名。 |
| `aaGuid` | 文字列 | No | Authenticator Attestation GUID (AAGUID)。 |
| `attestationCertificates` | 文字列の配列 | No | 認証子が構成証明を提供する場合の構成証明証明書の値。 |
| `attestationLevel` | 文字列 | No | 認証子に対して報告された構成証明レベル。 |
| `model` | 文字列 | No | Authenticator モデル。 |
| `passkeyType` | 文字列 | No | サービスによって報告されるパスキー分類。 |
| `_links` | オブジェクト | Yes | `self` 登録されたパスキーのリンクを `delete` します。 削除する場合は、返された`delete` リンクの代わりにMicrosoft Graphを使用します。 |

認証子固有のプロパティは、異なる場合と存在しない場合があります。 アクティブ化エンドポイントは、 `400`、 `401`、または `403`を返すことができます。 共有エンベロープと呼び出し元のアクションについては、「 エラー応答」を参照してください。

### 資格情報メソッドを削除する

資格情報管理 API でのパスキー削除のサポートは近日公開予定です。 それまでは、Microsoft Graph [Delete fido2AuthenticationMethod API (beta)](https://learn.microsoft.com/ja-jp/graph/api/fido2authenticationmethod-delete?view=graph-rest-beta&preserve-view=true&tabs=http) を使用して、顧客の登録済みパスキーを削除します。

Note

リストとアクティブ化の応答には、登録済みのパスキーの `_links.delete` が含まれます。 これらのリンクはまだサポートされていません。代わりに、Microsoft Graphを使用してパスキーを削除します。

この操作の認証要件、アクセス許可、要求形式、応答については、Microsoft Graphリファレンスに従ってください。

### エラー応答

エラー応答では、共有 JSON エンベロープが使用されます。 次の例は、必要なプロパティを示しています。

```json
{
  "error": {
    "code": "invalidRequest",
    "message": "<error-message>",
    "timestamp": "<timestamp>",
    "traceId": "<trace-id>",
    "correlationId": "<correlation-id>"
  }
}
```

エラー応答には、次のプロパティがあります。

| 財産 | タイプ | 必須 | Description |
| --- | --- | --- | --- |
| `error` | オブジェクト | Yes | エラーの詳細。 |
| `error.code` | 文字列 | Yes | 機械可読のエラーコード。 |
| `error.message` | 文字列 | Yes | エラーの人間が判読できる説明。 プログラムによる分岐には、この値を使用しないでください。 |
| `error.timestamp` | 文字列 | Yes | エラーが発生した時刻。 |
| `error.traceId` | 文字列 | Yes | 要求のトレースに使用される識別子。 |
| `error.correlationId` | 文字列 | Yes | コンポーネント間で要求を関連付けるために使用される識別子。 |
| `error.target` | 文字列 | No | エラーに関連付けられている Request 要素。 |
| `error.innerError` | オブジェクト | No | 追加のエラーの詳細。 |
| `clientHints` | 文字列の配列 | No | 応答全体のヒント トークン (省略可能)。 |

これらのエンドポイントでは、次の状態とコードの組み合わせが確認されます。

| HTTP 状態 | エラー コード | 確認された原因 |
| --- | --- | --- |
| `400 Bad Request` | `invalidRequest` | ベアラー トークンが見つからないか、形式が正しくないか、無効なトークン署名、期限切れのトークン、アプリ専用トークン、委任されたスコープが不十分、特定でないテナント ルート、または無効な登録状態 (形式が正しくない継続トークンなど)。 |
| `401 Unauthorized` | `invalidRequest` | 資格情報管理 API に対してトークンが発行されませんでした。 |
| `403 Forbidden` | `forbidden` | 要求 URL のテナントが、アクセス トークン内のテナントと一致しません。 |
| `500 Internal Server Error` | `serverError` | 登録開始エンドポイントが、アップストリーム コンポーネントから無効な WebAuthn 作成オプション応答を受信しました。 |

共有エラー モデルでは、 `unknown`、 `invalidGrant`、 `expiredToken`、 `tooManyRequests`、 `unsupportedRedirect`も定義されます。 すべてのエンドポイントがすべてのコードを出力するとは想定しないでください。

確認されたエラーを次のように処理します。

| 応答 | 呼び出し元アクション |
| --- | --- |
| `400 invalidRequest` | `error.message`を使用して、無効な入力を診断します。 トークンが見つからない、形式が正しくない、無効、期限切れ、アプリ専用、または受け入れられたスコープがない場合に、新しい委任されたトークンを取得します。 テナント固有のルート値を使用します。 登録状態が無効な場合は、2 ステップの登録フローを再起動します。 |
| `401 invalidRequest` | トークンを取得するときに、受け入れられた資格情報管理 API のアクセス許可を要求してから、要求を再試行します。 |
| `403 forbidden` | 要求 URL の `{tenant-id}` が、アクセス トークンによって表されるテナントと一致することを確認します。 |
| `500 serverError` | 診断の `traceId` と `correlationId` を記録します。 操作が成功したとは想定しないでください。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/reference-entra-id-wam-api"} -->
## Microsoft Entra ID Windows Account Manager API リファレンス - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-entra-id-wam-api
- Service: identity-platform
- Article date: 2024-03-19
- Summary: Microsoft Entra ID Windows Account Manager (WAM) API の包括的なガイド。その使用方法、パラメーター、Windows アプリケーションでの統合について詳しく説明します。

Microsoft Entra ID Windows Account Manager (WAM) の一連の API を使うと、開発者は Windows アプリケーションを統合できます。 以下のパラメーターを Windows プラットフォーム上の MSAL で使うと、Microsoft Entra ID または Microsoft アカウント (MSA) を使ってユーザーがサインインできるようにするアプリケーションの開発が容易になります。

### Microsoft Entra ID WAM API リファレンス

| パラメーター | 値 | メモ |
| --- | --- | --- |
| `authority` | `organizations` または特定のトークン発行者 URL。  たとえば、`https://login.partner.microsoftonline.cn` はこのクラウド環境のトークン発行者 URL です。 | `authority` パラメーターでは、API 用の ID プロバイダーとクラウド環境を指定します。 |
| `resource` | 開発者がトークンを取得する URL (`https://www.sharepoint.com` など) | `resource` パラメーターでは、取得する認証トークンのターゲット URL を指定します。 |
| `redirect_uri` | アプリが正し認可されて認可コードまたはアクセス トークンが付与された後、承認サーバーによってユーザーを送られる場所。 | **注**: このパラメーターは、ユニバーサル Windows プラットフォーム (UWP) アプリケーションではサポートされていません。 呼び出し元には、中程度の整合性レベルのアクセス許可が必要です。 「[リダイレクト URI (応答 URL) に関する制約と制限](https://learn.microsoft.com/ja-jp/entra/identity-platform/reply-url)」をご覧ください |
| `correlationId` | データ ソース間で要求を結合するために使用できる、要求ごとに生成される一意の GUID | **注**: `correlationId` は従来のパラメーターです。 WAM API 自体のプロパティに置き換えられました。 Windows UWP アプリケーションの [WebTokenRequest.CorrelationId Property (Windows.Security.Authentication.Web.Core)](https://learn.microsoft.com/ja-jp/uwp/api/windows.security.authentication.web.core.webtokenrequest.correlationid) に関する記事をご覧ください |
| `validateAuthority` | ブール値 (`TRUE` または `FALSE`)。 | Microsoft Entra ID プラグインは、既定で機関の検証を実行します。 アプリケーションでこの検証を無効にする必要がある場合は、`FALSE` に対して値 `validateAuthority` を送信する必要があります。 これは、オンプレミスのシナリオで役に立ちます。 「[MsalAuthenticationOptions.ValidateAuthority プロパティ](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.authentication.webassembly.msal.msalauthenticationoptions.validateauthority)」をご覧ください |
| `certificateUsage` | `vpn` | このパラメーターは、証明書トークンの種類を要求するために使われます。 使用できる値は `vpn` のみです。 現時点では、これは VPN のシナリオにのみ適用されます。 |
| `certificateUIName` | VPN 証明書の証明書選択 UI に表示される任意の文字列。 | `certificateUIName` パラメーターでは、VPN 証明書を選ぶときに証明書選択ユーザー インターフェイスに表示される文字列を指定します。 |
| `certificateUIDescription` | VPN 証明書の証明書選択 UI に表示される任意の文字列。 | `certificateUIDescription` パラメーターでは、VPN 証明書を選ぶときに説明として証明書選択ユーザー インターフェイスに表示される文字列を指定します。 |
| `UserPictureEnabled` | `True` または `False` のいずれかです。 | アプリケーションがトークン応答の一部としてユーザーの画像を受け取ることを望むかどうかを示します。 |
| `Username` | ユーザー名 | Microsoft Entra ID のユーザー名を表します |
| `Password` | パスワード | Microsoft Entra ID ユーザーのパスワードを表します |
| `SamlAssertion` | SAML トークン | サード パーティの IDP から Microsoft Entra ID に認証成果物として送信される SAML トークン |
| `SamlAssertionType` | `"urn:ietf:params:oauth:grant-type:saml1_1-bearer"` または `"urn:ietf:params:oauth:grant-type:saml2-bearer"` のいずれか | 認証に使われる SAML トークンの種類を指定します。 |
| `LoginHint` | ユーザー名のヒント (UPN) | サインイン プロセスの間のユーザー名のヒントを提供します。 |
| `msafed` | `0` または `1` のいずれかです。 | Microsoft アカウントを持つユーザーが Entra テナント内でフェデレーション ID としてサインインできるようにするかどうかを決定します。 値 `1` はフェデレーションが有効にし、`0` は無効にします。 |
| `discover` | `Home` | `discover` パラメーターは、デバイスが参加しているクラウドではなく、ユーザーのホーム クラウドのコンテキストで、トークン要求が実行されることを示します。 |
| `domain_hint` | 関連するドメイン。 たとえば、`contoso.com` のように指定します。 | Microsoft Entra ID は、ヒントを使ってディレクトリ内のドメインを検索します **注**: 両方がパラメーターとして渡される場合、ドメイン ヒントはフォールバック ドメインよりも優先されます |
| `fallback_domain` | ドメイン (`contoso.com` など) | `fallback_domain` パラメーターは、Microsoft Entra ID がディレクトリ内のドメインの検索に使うヒントです。 これは主に、ユーザーの UPN の一部であるルーティング不可能なユーザー ドメインに使われます。 |
| `client_TokenType` | `DeviceAuth` | `client_TokenType` パラメーターは、デバイス専用トークンの要求にのみ使われます。 呼び出し元には、中程度の整合性レベルのアクセス許可が必要です。 |
| `IsFeatureSupported` | `CrossCloudB2B`,`redirect_uri`,`TokenBinding` | 機能が現在のフローでサポートされているかどうかを調べるために使われます。 |
| `prompt` | `no_select` または `select_account` のいずれか | prompt パラメーターは、プロンプト ウィンドウの動作を制御します。 `no_select` は、プロンプト動作の制御がプロンプト ウィンドウに追加されないことを意味します。 `select_account` は、アカウント ピッカーを表示します。 このパラメーターは、アカウント選択動作を制御するためにログイン サービスに渡されます。 |
| `minimum_token_lifetime` | ミリ秒単位の時間。 | これは、新しいトークンの要求と、キャッシュ トークンの破棄が必要になるまでの時間です |
| `telemetry` | `MATS` | 要求に関するテレメトリを返します |
| `enclave` | `sw` はソフトウェア キーを示します。`hw` はハードウェア キーを示します。`kg` はキーガード キーを示します。 | `enclave` パラメーターでは、使用するキーの種類を指定します。 |
| `token_type` | `pop` は所有証明トークンを示します `shr` は署名済み HTTP 要求トークンを示します | `token_type` パラメーターでは、使用するトークンの種類を指定します。 |
| `req_cnf` |  | `req_cnf` が `token_type` のときは、`pop` パラメーターが使われます。 このフィールドには、所有証明のためにクライアントがアクセス トークンにバインドするキーに関する情報が含まれます。 |
| `refresh_binding` |  | `refresh_binding` パラメーターは、今後リリースされる予定のトークン バインド機能の一部です。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/reference-error-codes"} -->
## Microsoft Entra 認証と承認のエラー コード - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes
- Service: identity-platform
- Article date: 2025-02-03
- Summary: Microsoft Entra セキュリティ トークン サービス (STS) から返される AADSTS エラー コードについて説明します。

Microsoft Entra セキュリティ トークン サービス (STS) から返される AADSTS エラー コードについての情報をお探しですか? AADSTS エラーの説明、修正、およびいくつかの推奨される回避策を見つけるには、このドキュメントをお読みください。

注

この情報は暫定的なもので、変更されることがあります。 ご質問がありますか。またはお探しの情報が見つかりませんでしたか。 GitHub の問題を作成するか、 [開発者向けのサポートとヘルプ オプション](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-support-help-options) を参照して、ヘルプとサポートを受けるその他の方法について学習します。

このドキュメントは、開発者と管理者向けのガイダンスとして提供されています。クライアント自体では決して使用しないでください。 エラー コードは予告なく変更される可能性があります。これは、より詳しいエラー メッセージを提供してアプリケーションを構築中の開発者に役立てていただくためです。 テキストやエラー コード番号に依存するアプリケーションは、時間の経過に伴い正常に機能しなくなります。

### 現在のエラー コード情報の参照

エラー コードとメッセージは変更される可能性があります。 最新の情報については、https://login.microsoftonline.com/error ページを参照して、AADSTS のエラーの説明、修正、およびいくつかの推奨される回避策を確認してください。

たとえば、"AADSTS50058" というエラー コードを受け取った場合は、https://login.microsoftonline.com/error で "50058" を検索します。 次のように URL にエラー コード番号を追加して、特定のエラーに直接リンクすることもできます。https://login.microsoftonline.com/error?code=50058

### アプリケーションでのエラー コードの処理

[OAuth2.0 仕様](https://tools.ietf.org/html/rfc6749#section-5.2)では、エラー応答の`error`部分を使用して認証中にエラーを処理する方法に関するガイダンスが提供されます。

サンプルのエラー応答を次に示します。

```json
{
  "error": "invalid_scope",
  "error_description": "AADSTS70011: The provided value for the input parameter 'scope' isn't valid. The scope https://example.contoso.com/activity.read isn't valid.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: 2016-01-09 02:02:12Z",
  "error_codes": [
    70011
  ],
  "timestamp": "2016-01-09 02:02:12Z",
  "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333",
  "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd", 
  "error_uri":"https://login.microsoftonline.com/error?code=70011"
}
```

| パラメーター | 説明 |
| --- | --- |
| `error` | 発生したエラーの種類を分類するために使用でき、またエラーに対処するために使用する必要のあるエラー コード文字列。 |
| `error_description` | 認証エラーの根本的な原因を開発者が特定しやすいように記述した具体的なエラー メッセージ。 このフィールドをコードでエラーに対処するために使用しないでください。 |
| `error_codes` | 診断に役立つ STS 固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻を返します。 |
| `trace_id` | 診断に役立つ、要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |
| `error_uri` | エラーに関する追加情報が含まれているエラー参照ページへのリンク。 これは、開発者による使用のみを目的にしています。ユーザーには提供しないでください。 エラー参照システムに、エラーに関する追加情報がある場合にのみ存在します。すべてのエラーで追加情報が提供されるわけではありません。 |

`error` フィールドには、いくつかの有効な値があります。特定のエラー（たとえば、[device code flow](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code) における `authorization_pending`）とそれらへの対処方法について詳しくは、プロトコル ドキュメントへのリンクおよび OAuth 2.0 仕様を参照してください。 いくつかの一般的なものを次に示します。

| エラー コード | 説明 | クライアント側の処理 |
| --- | --- | --- |
| `invalid_request` | 必要なパラメーターが不足しているなどのプロトコル エラーです。 | 要求を修正し再送信します。 |
| `invalid_grant` | 一部の認証情報 (承認コード、更新トークン、アクセス トークン、PKCE チャレンジ) が無効か、解析不能か、見つからないか、またはそれ以外の状態で確認できません。 | 新しい承認コードを取得するには、`/authorize` エンドポイントへの新しい要求を試してください。 そのアプリのプロトコルの使用を確認および検証することを検討してください。 |
| `unauthorized_client` | 認証されたクライアントは、この承認付与の種類を使用する権限がありません。 | これは通常、クライアント アプリケーションが Microsoft Entra ID に登録されていない、またはユーザーの Microsoft Entra テナントに追加されていないときに発生します。 アプリケーションでは、アプリケーションのインストールと Microsoft Entra ID への追加を求める指示をユーザーに表示できます。 |
| `invalid_client` | クライアント認証に失敗しました。 | クライアント資格情報が有効ではありません。 修正するには、アプリケーション管理者が資格情報を更新します。 |
| `unsupported_grant_type` | 認可付与タイプが承認サーバーでサポートされていません。 | 要求の付与の種類を変更します。 この種のエラーは、開発時にのみ発生し、初期テスト中に検出する必要があります。 |
| `invalid_resource` | ターゲット リソースは、存在しない、Microsoft Entra ID で見つけられない、または正しく構成されていないために無効です。 | これは、リソース (存在する場合) がテナントで構成されていないことを示します。 アプリケーションでは、アプリケーションのインストールと Microsoft Entra ID への追加を求める指示をユーザーに表示できます。 開発中の場合、これは通常、誤って設定されたテスト テナント、または要求されているスコープの名前の入力ミスを示します。 |
| `interaction_required` | 要求にユーザーの介入が必要です。 たとえば、別の認証手順が必要です。 | ユーザーが必要なチャレンジをすべて完了できるように、この要求を対話的に、同じリソースで再試行してください。 |
| `temporarily_unavailable` | サーバーが一時的にビジー状態であるため、要求を処理できません。 | 要求をやり直してください。 クライアント アプリケーションは、一時的な状況が原因で応答が遅れることをユーザーに説明する場合があります。 |

### AADSTS エラー コード

| エラー | 説明 |
| --- | --- |
| AADSTS16000 | InteractionRequired - ID プロバイダー '{idp}' のユーザー アカウント '{EmailHidden}' がテナント '{tenant}' に存在せず、そのテナント内のアプリケーション '{appid}'({appName}) にアクセスできません。 このアカウントは、まずテナントに外部ユーザーとして追加する必要があります。 サインアウト後、別の Microsoft Entra ユーザー アカウントで再度サインインしてください。 これは、ディレクトリが関連付けられていない個人用 Microsoft アカウントを使って Microsoft Entra 管理センターにサインインしようとすると発生する、非常に一般的なエラーです。 |
| AADSTS16001 | UserAccountSelectionInvalid - セッション選択ロジックが拒否したタイルをユーザーが選択すると、このエラーが表示されます。 このエラーがトリガーされた場合、ユーザーはタイル/セッションの最新の一覧から選択するか、別のアカウントを選択することで、回復することができます。 このエラーは、コードの欠陥や競合状態が原因で発生することがあります。 |
| AADSTS16002 | AppSessionSelectionInvalid - アプリで指定されている SID 要件が満たされていません。 |
| AADSTS160021 | AppSessionSelectionInvalidSessionNotExist - アプリケーションが存在しないユーザー セッションを要求しました。 この問題は、新しい Azure アカウントを作成することで解決できます。 |
| AADSTS16003 | SsoUserAccountNotFoundInResourceTenant - ユーザーがテナントに明示的に追加されていないことを示します。 |
| AADSTS17003 | CredentialKeyProvisioningFailed - Microsoft Entra ID はユーザー キーをプロビジョニングできません。 |
| AADSTS20001 | WsFedSignInResponseError - フェデレーション ID プロバイダーに問題があります。 この問題を解決するには、IDP に問い合わせてください。 |
| AADSTS20012 | WsFedMessageInvalid - フェデレーション ID プロバイダーに問題があります。 この問題を解決するには、IDP に問い合わせてください。 |
| AADSTS20033 | FedMetadataInvalidTenantName - フェデレーション ID プロバイダーに問題があります。 この問題を解決するには、IDP に問い合わせてください。 |
| AADSTS230109 | CachedCredentialNonGWAuthNRequestsNotSupported - バックアップ認証サービスでは、Microsoft Entra ゲートウェイからの AuthN 要求のみが許可されます。 このエラーは、トラフィックがリバース プロキシを経由せずに、バックアップ認証サービスを直接ターゲットとする場合に返されます。 |
| AADSTS28002 | 入力パラメーター スコープ '{scope}' に指定された値が、アクセス トークンを要求するときに有効ではありません。 有効なスコープを指定してください。 |
| AADSTS28003 | 指定された認証コードを使用してアクセス トークンを要求する場合、入力パラメーター スコープに指定された値を空にすることはできません。 有効なスコープを指定してください。 |
| AADSTS399284 | InboundIdTokenIssuerInvalid - フェデレーションで受信した受信 ID トークンに無効な発行者があります。 空であるか、領域識別子と一致しません。 |
| AADSTS40008 | OAuth2IdPUnretryableServerError - フェデレーション ID プロバイダーに問題があります。 この問題を解決するには、IDP に問い合わせてください。 |
| AADSTS40009 | OAuth2IdPRefreshTokenRedemptionUserError - フェデレーション ID プロバイダーに問題があります。 この問題を解決するには、IDP に問い合わせてください。 |
| AADSTS40010 | OAuth2IdPRetryableServerError - フェデレーション ID プロバイダーに問題があります。 この問題を解決するには、IDP に問い合わせてください。 |
| AADSTS40015 | OAuth2IdPAuthCodeRedemptionUserError - フェデレーション ID プロバイダーに問題があります。 この問題を解決するには、IDP に問い合わせてください。 |
| AADSTS50000 | TokenIssuanceError - サインイン サービスに問題があります。 この問題を解決するには[、サポート チケットを開きます](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-get-support)。 |
| AADSTS50001 | InvalidResource - リソースが無効になっているか、存在しません。 アプリのコードをチェックして、アクセスしようとしているリソースの正確なリソース URL を指定していることを確認します。 |
| AADSTS50002 | NotAllowedTenant - テナントでプロキシ アクセスが制限されているため、サインインが失敗しました。 自分が所有するテナント ポリシーの場合は、制限されたテナント設定を変更して、この問題を解決できます。 |
| AADSTS500011 | InvalidResourceServicePrincipalNotFound - {name} という名前のリソース プリンシパルが、{tenant} という名前のテナントで見つかりませんでした。 これは、アプリケーションがテナントの管理者によってインストールされていない場合や、テナント内のいずれのユーザーによっても同意されていない場合に発生することがあります。 間違ったテナントに認証要求を送信した可能性があります。 アプリのインストールが予想される場合、アプリを追加するために管理者アクセス許可が必要な場合があります。 リソースとアプリケーションの開発者に確認し、テナントに対する正しい設定を理解してください。 |
| AADSTS500014 | InvalidResourceServicePrincipalDisabled - リソース '{identifier}' のサービス プリンシパルが無効になっています。 これは、テナント内のサブスクリプションが期限切れになったか、このテナントの管理者がアプリケーションのサービス プリンシパルを無効にして、それに対してトークンが発行されないようにしたことを示します。 詳細については、「アプリケーションの [ユーザー サインインを無効にする」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/disable-user-sign-in-portal)参照してください。 |
| AADSTS500021 | '{tenant}' テナントへのアクセスが拒否されました。 AADSTS500021 は、テナント制限機能が構成されており、ユーザーが、ヘッダー `Restrict-Access-To-Tenant` で指定されている許可されたテナントの一覧にないテナントにアクセスしようとしていることを示します。 詳細については、「 [テナント制限を使用して SaaS クラウド アプリケーションへのアクセスを管理する」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tenant-restrictions)参照してください。 |
| AADSTS500022 | '{tenant}' テナントへのアクセスが拒否されました。 AADSTS500022 は、テナント制限機能が構成されており、ユーザーが、ヘッダー `Restrict-Access-To-Tenant` で指定されている許可されたテナントの一覧にないテナントにアクセスしようとしていることを示します。 詳細については、「 [テナント制限を使用して SaaS クラウド アプリケーションへのアクセスを管理する」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tenant-restrictions)参照してください。 |
| AADSTS50003 | MissingSigningKey - 署名キーまたは証明書がないために、サインインが失敗しました。 アプリで署名キーが構成されていない可能性があります。 詳細については、エラー [AADSTS50003](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/error-code-aadsts50003-cert-or-key-not-configured)のトラブルシューティングの記事を参照してください。 問題が引き続き発生する場合は、アプリの所有者またはアプリ管理者に問い合わせてください。 |
| AADSTS50005 | DevicePolicyError - ユーザーが、条件付きアクセス ポリシーで現在サポートされていないプラットフォームからデバイスにサインインしようとしました。 |
| エラーメッセージ: AADSTS50006 | InvalidSignature - 無効な署名のため、署名の検証が失敗しました。 |
| AADSTS50007 | PartnerEncryptionCertificateMissing - このアプリのパートナー暗号化証明書が見つかりませんでした。 これを修正するには、Microsoft の[サポート チケットを開きます](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-get-support)。 |
| AADSTS50008 | InvalidSamlToken - トークンに SAML アサーションがないか、正しく構成されていません。 フェデレーション プロバイダーに問い合わせてください。 |
| AADSTS5000224 | NotAllowedTenantBlockedTenantFraud - 申し訳ございません。このリソースはご利用いただけません。 間違ってこのメッセージが表示される場合は、Microsoft サポートにお問い合わせください。 |
| AADSTS5000819 | InvalidSamlTokenEmailMissingOrInvalid - SAML アサーションが無効です。 メール アドレス要求が見つからないか、外部領域のドメインと一致しません。 |
| AADSTS50010 | AudienceUriValidationFailed - トークン オーディエンスが構成されていないため、アプリのオーディエンス URI の検証が失敗しました。 |
| AADSTS50011 | InvalidReplyTo - 返信アドレスがないか、正しく構成されていません。または、アプリに対して構成されている返信アドレスと一致しません。 解決方法として、この不足している返信先アドレスを Microsoft Entra アプリケーションに追加するか、Microsoft Entra ID でアプリケーションを管理する権限を持つユーザーにこの作業を依頼してください。 詳細については、エラー [AADSTS50011](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/error-code-aadsts50011-reply-url-mismatch)のトラブルシューティングの記事を参照してください。 |
| AADSTS50012 | AuthenticationFailed - 次のいずれかの理由で認証に失敗しました。<br>- 署名証明書のサブジェクト名が承認されていない<br>- 承認されたサブジェクト名に一致する信頼された証明機関ポリシーが見つからなかった<br>- 証明書チェーンが無効<br>- 署名証明書が無効<br>- テナントにポリシーが設定されていません<br>- 署名証明書の拇印が承認されていない<br>- クライアント アサーションに無効な署名が含まれている |
| AADSTS50013 | InvalidAssertion - さまざまな理由によりアサーションが無効です。たとえば、トークンの発行者が有効期間内の API バージョンと一致しない、期限が切れている、形式が正しくない、アサーションの更新トークンがプライマリ更新トークンではない、などです。 アプリ開発者に問い合わせてください。 |
| AADSTS500133 | アサーションが有効な時間の範囲内ではありません。 アクセス トークンの有効期限が切れていないことを確認してからユーザー アサーションに使用します。それ以外の場合、新しいトークンを要求してください。 現在の時刻: {curTime}、アサーションの有効期限 {expTime}。 さまざまな理由により、アサーションは無効です。<br>- トークンの発行元が、その有効期間内の API バージョンと一致していません<br>- 期限切れ<br>- 不正な形式<br>- アサーション内のリフレッシュ トークンがプライマリ リフレッシュ トークンではありません |
| AADSTS50014 | GuestUserInPendingState - ユーザー アカウントがディレクトリに存在しません。 アプリケーションがサインインするテナントを間違えて選んだ可能性が高く、現在ログインしているユーザーがテナントに存在しないため、サインインできませんでした。 このユーザーがサインインできるようにする場合は、ゲストとして追加します。 詳細については、「 [B2B ユーザーの追加](https://learn.microsoft.com/ja-jp/azure/active-directory/b2b/add-users-administrator)」を参照してください。 |
| AADSTS50015 | ViralUserLegalAgeConsentRequiredState - ユーザーには法的年齢グループの同意が必要です。 |
| AADSTS50017 | CertificateValidationFailed - 次の理由から、証明書の検証が失敗しました。<br>- 信頼できる証明書に発行側の証明書が見つかりません<br>- 予期される CrlSegment が見つかりきません<br>- 信頼できる証明書に発行側の証明書が見つかりません<br>- 構成されている Delta CRL 配布点に、対応する CRL 配布点がありません<br>- タイムアウトの問題のため、有効な CRL セグメントを取得できません<br>- CRL をダウンロードできません<br><br>テナント管理者に問い合わせてください。 |
| AADSTS500141 | ユーザーの償還は完了しましたが、その要求はターゲット アプリケーションによって開始されたものではありません。 |
| AADSTS5001256 | 無効なid\_tokenのため、外部プロバイダーによる認証を完了できませんでした。 エラーの詳細: {details} |
| AADSTS50020 | UserUnauthorized - ユーザーがこのエンドポイントの呼び出しを承認されていません。 ID プロバイダー '{idp}' のユーザー アカウント '{email}' がテナント '{tenant}' に存在せず、そのテナントのアプリケーション '{appid}' ({appName}) にアクセスできません。 このアカウントは、まずテナントに外部ユーザーとして追加する必要があります。 サインアウト後、別の Microsoft Entra ユーザー アカウントで再度サインインしてください。 このユーザーがテナントのメンバーである必要がある場合は、 [B2B システム](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)経由で招待する必要があります。 詳細については、AADSTS50020を参照 [してください](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/error-code-aadsts50020-user-account-identity-provider-does-not-exist)。 |
| AADSTS500207 | アカウントの種類は、アクセスしようとしているリソースには使用できません。 |
| AADSTS500208 | ドメインがアカウントの種類に対して有効なログイン ドメインではありません。この状況は、ユーザーのアカウントが特定のテナントの想定されるアカウントの種類と一致しない場合に発生します。 たとえば、テナントが職場または学校アカウントのみを許可するように構成されていて、ユーザーが個人用 Microsoft アカウントでサインインしようとすると、このエラーが表示されます。 |
| AADSTS500212 | NotAllowedByOutboundPolicyTenant - ユーザーの管理者が、リソース テナントへのアクセスを許可しないように外向きアクセス ポリシーを設定しました。 |
| AADSTS500213 | NotAllowedByInboundPolicyTenant - リソース テナントのテナント間アクセス ポリシーが、このユーザーがこのテナントにアクセスすることを許可していません。 |
| AADSTS50027 | InvalidJwtToken - 次の理由により JWT トークンが無効です。<br>- nonce クレーム、サブクレームが含まれていない<br>- サブジェクト識別子の不一致<br>- idToken クレーム内の重複するクレーム<br>- 想定外の発行者<br>- 予期しない対象ユーザー<br>- 有効な時間範囲内でない<br>- トークンの形式が正しくない<br>- 発行元からの外部 ID トークンが、署名の検証に失敗しました。 |
| AADSTS50029 | 無効な URI - ドメイン名に無効な文字が含まれています。 テナント管理者に問い合わせてください。 |
| AADSTS50032 | WeakRsaKey - 脆弱な RSA キーを使用しようとする誤ったユーザー試行を示します。 |
| AADSTS50033 | RetryableError - データベース操作に関連しない一時的なエラーを示します。 |
| AADSTS50034 | UserAccountNotFound - このアプリケーションにサインインするには、アカウントがディレクトリに追加されている必要があります。 このエラーは、ユーザーがユーザー名を間違えて入力した、またはテナントにいないために発生する可能性があります。 アプリケーションがサインインするテナントを間違えて選択した可能性が高く、現在ログインしているユーザーがテナントに存在しないため、サインインできませんでした。 このユーザーがログインできるようにする場合は、ゲストとして追加します。 ドキュメントについては、 [B2B ユーザーの追加に](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)関するページを参照してください。 |
| AADSTS50042 | UnableToGeneratePairwiseIdentifierWithMissingSalt - ペアワイズ識別子の生成に必要なソルトがプリンシパルに存在しません。 テナント管理者に問い合わせてください。 |
| AADSTS50043 | 複数の塩を使用してペアワイズ識別子を生成できません。 |
| AADSTS50048 | SubjectMismatchesIssuer - Subject が、クライアント アサーション内の Issuer クレームと一致しません。 テナント管理者に問い合わせてください。 |
| AADSTS50049 | NoSuchInstanceForDiscovery - 不明または無効なインスタンス。 |
| AADSTS50050 | MalformedDiscoveryRequest - リクエストの形式が正しくありません。 |
| AADSTS50053 | このエラーは、以下の 2 つの異なる理由により発生する可能性があります。<br>- IdsLocked - ユーザーが間違ったユーザー ID またはパスワードで何度もサインインを試みたために、アカウントがロックされています。 サインインの試行が繰り返されたため、ユーザーがブロックされています。 [「リスクの修復とユーザーのブロック解除](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock)」を参照してください。<br>- または、サインインが、悪意のあるアクティビティを伴う IP アドレスからのものであるため、ブロックされました。<br><br>このエラーの原因となったエラーの原因を特定するには、少なくとも[クラウド アプリケーション管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。 Microsoft Entra テナントに移動し、**監視と正常性**から&gt;に進みます。 **サインイン エラー コード** 50053 で失敗したユーザー サインインを見つけて、**失敗の理由**を確認します。 |
| AADSTS50055: パスワードの有効期限が切れています。パスワードをリセットしてください。 | InvalidPasswordExpiredPassword - パスワードの期限が切れています。 ユーザーのパスワードの有効期限が切れているため、ログインまたはセッションが終了しました。 リセットする機会が提供されます。また、 [Microsoft Entra ID を使用してユーザーのパスワードをリセット](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-reset-password-azure-portal)してリセットするように管理者に依頼することもできます。 |
| AADSTS50056 | パスワードが無効または null です。パスワードはこのユーザーのディレクトリに存在しません。 ユーザーは、パスワードを再入力するよう求められます。 |
| AADSTS50057 | UserDisabled - ユーザー アカウントが無効にされています。 このアカウントの基礎となる Active Directory のユーザー オブジェクトが無効になっています。 管理者は [PowerShell を使用して](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/enable-adaccount)このアカウントを再度有効にすることができます |
| AADSTS50058 | UserInformationNotProvided - シングル サインオンに関するセッション情報が不十分です。 これは、ユーザーがサインインしていないことを意味します。 これは、ユーザーが認証されておらず、まだサインインしていないときに想定される一般的なエラーです。 ユーザーがその前にサインインした SSO のコンテキストでこのエラーが発生する場合は、SSO セッションが見つからなかったか無効であったことを意味します。 prompt=none が指定されている場合、このエラーがアプリケーションに返される可能性があります。 |
| AADSTS50059 | MissingTenantRealmAndNoUserInformationProvided - テナントを識別する情報が、リクエスト内にも、提供された資格情報から暗黙的に示される形でも見つかりませんでした。 ユーザーはテナント管理者に連絡して問題解決に協力してもらうことができます。 |
| AADSTS50061 | SignoutInvalidRequest - サインアウトを完了できません。要求は無効でした。 |
| AADSTS50064 | CredentialAuthenticationError - ユーザー名またはパスワードの資格情報の検証に失敗しました。 |
| AADSTS50068 | SignoutInitiatorNotParticipant - サインアウトに失敗しました。 サインアウトを開始したアプリケーションは現在のセッションの参加者ではありません。 |
| AADSTS50070 | SignoutUnknownSessionIdentifier - サインアウトに失敗しました。 サインアウト要求で、既存のセッションと一致していない名前識別子が指定されました。 |
| AADSTS50071 | SignoutMessageExpired - ログアウト要求の有効期限が切れています。 |
| AADSTS50072 | UserStrongAuthEnrollmentRequiredInterrupt - ユーザーは第 2 要素認証 (対話型) に登録する必要があります。 |
| AADSTS50074 | UserStrongAuthClientAuthNRequiredInterrupt - 強力な認証が必要です。ユーザーが MFA チャレンジに合格しませんでした。 |
| AADSTS50076 | UserStrongAuthClientAuthNRequired - 条件付きアクセス ポリシー、ユーザーごとの適用など、管理者が行った構成変更により、またはユーザーが新しい場所に移動したため、ユーザーはリソースにアクセスする際に多要素認証を使用する必要があります。 リソースの新しい承認要求で再試行してください。 |
| AADSTS50078 | UserStrongAuthExpired - 管理者によって構成されたポリシーにより、提示された多要素認証の有効期限が切れました。 '{resource}' にアクセスするには、多要素認証を更新する必要があります。 |
| AADSTS50079 | UserStrongAuthEnrollmentRequired - 条件付きアクセス ポリシー、ユーザーごとの適用など、管理者が行った構成変更により、またはユーザーが新しい場所に移動したため、ユーザーは多要素認証を使う必要があります。 マネージド ユーザーが多要素認証を完了するためにセキュリティ情報を登録するか、フェデレーション ユーザーがフェデレーション ID プロバイダーから多要素の要求を取得する必要があります。 |
| AADSTS50085 | 更新トークンにソーシャル IDP ログインが必要です。 ユーザーに、ユーザー名とパスワードで再度サインインを試行させます |
| AADSTS50086 | Sas再試行不可エラー |
| AADSTS50087 | SasRetryableError - 強力な認証中に一時的なエラーが発生しました。 再試行してください。 |
| AADSTS50088 | 通信の MFA 呼び出しの上限に達しました。 しばらくたってからもう一度試してください。 |
| AADSTS50089 | フロー トークンの有効期限切れのため、認証に失敗しました。 予想される動作 - 認証コード、更新トークン、セッションが時間の経過により期限切れになるか、ユーザーまたは管理者によって取り消されます。アプリは、ユーザーに新しいログインを要求します。 |
| AADSTS50097 | DeviceAuthenticationRequired - デバイス認証が必要です。 |
| AADSTS50098 | JWT 本文には '{field}' が含まれている必要があります。 |
| AADSTS50099 | PKeyAuthInvalidJwtUnauthorized - JWT 署名が無効です。 |
| AADSTS50100 | トークンの要求の変換中にエラーが発生しました。 |
| AADSTS50101 | プリンシパル '{principalId}' に不明な要求トランスフォーマー '{name}' が指定されました。 |
| AADSTS50102 | プリンシパル '{principalId}' に指定された CustomClaimsTransformer '{type}' を読み込めません。 |
| AADSTS50103 | トークンの要求の変換中にエラーが発生しました: {errorMessage} |
| AADSTS50105 | EntitlementGrantsNotFound - サインインしているユーザーには、サインイン先のアプリのロールが割り当てられていません。 アプリにユーザーを割り当ててください。 詳細については、エラー [AADSTS50105](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/error-code-aadsts50105-user-not-assigned-role)のトラブルシューティングの記事を参照してください。 |
| AADSTS50107 | 要求されたフェデレーション領域オブジェクト '{name}' が存在しません。 アプリケーション エラー - ログイン要求の形式が正しくなくなり、既存の認証エンドポイントまたはインスタンスと一致できませんでした。 |
| AADSTS50108 | 要求変換の構成を取得できませんでした。 |
| AADSTS50109 | クレーム変換は構成では認識されていません。 |
| AADSTS50111 | 不明な要求変換を適用するよう要求されました。 |
| AADSTS50117 | 要求の要求パラメーターで指定されたポリシーを逆シリアル化できませんでした。 |
| AADSTS50120 | 不明な資格情報の種類。JWT ヘッダーに関する問題。 テナント管理者に問い合わせてください。 |
| AADSTS50123 | プリンシパル '{principalId}' に不明な要求変換メソッド '{method}' が指定されました。 |
| AADSTS50124 | このアプリケーションの要求変換用に構成された正規表現が無効です。 要求マッピングの構成を修正するには、テナント管理者に問い合わせてください。 [SAML トークン要求のカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)を参照してください |
| AADSTS501241 | 変換 ID '{transformId}' に必須の入力 '{paramName}' がありません。 このエラーは、Microsoft Entra ID がアプリケーションに対する SAML 応答の作成を試みているときに返されます。 SAML 応答では NameID 要求または NameIdentifier が必須であり、Microsoft Entra ID が NameID 要求のソース属性を取得できなかった場合に、このエラーが返されます。 解決策として、クレーム規則を追加してください。 要求規則を追加するには、少なくとも[クラウド アプリケーション管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインし、**Entra ID**&gt;**Enterprise アプリ**を参照します。 アプリケーションを選択し、[ **シングル サインオン** ] を選択し **、[ユーザー属性] & [要求** ] に一意のユーザー識別子 (名前 ID) を入力します。 |
| AADSTS50125 | PasswordResetRegistrationRequiredInterrupt - パスワードのリセットまたはパスワード登録の入力により、サインインが中断されました。 |
| AADSTS50126 | InvalidUserNameOrPassword - 無効なユーザー名またはパスワードにより、資格情報の検証でエラーが発生しました。 ユーザーが適切な資格情報を入力しませんでした。 ユーザーの間違いにより、ログにこれらのエラーがいくつか表示されることが予想されます。 |
| AADSTS50127 | BrokerAppNotInstalled - ユーザーは、このコンテンツにアクセスするためにブローカー アプリをインストールする必要があります。 |
| AADSTS50128 | ドメイン名が無効です。テナントを識別する情報が要求内に見つからず、指定されたどの資格情報でも暗黙的に示されませんでした。 |
| AADSTS50129 | DeviceIsNotWorkplaceJoined - デバイスを登録するには、ワークプレースの参加が必要です。 |
| AADSTS50130 | 要求値 '{value}' は、既知の認証方法として解釈できません。 |
| AADSTS50131 | ConditionalAccessFailed - Windows デバイスの状態が無効である、疑わしいアクティビティ、アクセス ポリシー、またはセキュリティ ポリシーの判断によって要求がブロックされたなど、さまざまな条件付きアクセス エラーを示します。 |
| AADSTS50132 | SsoArtifactInvalidOrExpired - パスワードが期限切れまたは最近のパスワード変更により、セッションは無効です。 |
| AADSTS50133 | SsoArtifactRevoked - パスワードが期限切れまたは最近のパスワード変更により、セッションは無効です。 |
| AADSTS50134 | DeviceFlowAuthorizeWrongDatacenter - 誤ったデータセンターです。 OAuth 2.0 デバイス フローでアプリケーションによって開始された要求を承認するには、承認者が元の要求が存在する場所と同じデータ センターにいる必要があります。 |
| AADSTS50135 | PasswordChangeCompromisedPassword - アカウントにリスクがあるため、パスワードの変更が必要です。 |
| AADSTS50136 | RedirectMsaSessionToApp - 単一 MSA セッションが検出されました。 |
| AADSTS50137 | セキュリティ ポリシー規則により、パスワードを変更する必要があります。 |
| AADSTS50138 | 暗号化キー環境が無効です。 |
| AADSTS50139 | SessionMissingMsaOAuth2RefreshToken - 外部更新トークンがないためセッションが無効です。 |
| AADSTS50140 | KmsiInterrupt - ユーザーがサインインしたときの "サインインしたままにする" 割り込みによりエラーが発生しました。 これはサインイン フローの予想される部分であり、以降のログインが容易になるように現在のブラウザーにサインインしたままにするかユーザーに確認しています。 詳細については、[新しい Microsoft Entra のサインインと「ログイン状態を維持する」エクスペリエンスのロールアウトを参照](https://techcommunity.microsoft.com/t5/azure-active-directory-identity/the-new-azure-ad-sign-in-and-keep-me-signed-in-experiences/m-p/128267)してください。 関連付け ID、要求 ID、エラー コードを含む [サポート チケットを開](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-get-support) いて、詳細を取得できます。 |
| AADSTS50141 | 保護されたキーは、認証されたユーザーを対象としていません。 |
| AADSTS50142 | 条件付きアクセス ポリシーにより、パスワードの変更が必要です。 |
| AADSTS50143 | セッションが一致しません。異なるリソースにより、ユーザーのテナントがドメインのヒントと一致しないため、セッションが無効です。 関連付け ID、要求 ID、エラー コードを含む[サポート チケットを開](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-get-support)き、詳細を取得します。 |
| AADSTS50144 | InvalidPasswordExpiredOnPremPassword - ユーザーの Active Directory パスワードの有効期限が切れました。 ユーザーに新しいパスワードを生成するか、またはユーザーにセルフサービスのリセット ツールを使用させて、パスワードをリセットしてください。 |
| AADSTS50147 | コード チャレンジ パラメーターのサイズが無効です。 PKCE パラメーターの使用を修正するには、アプリケーション所有者に問い合わせてください。 |
| AADSTS50148 | PKCEの承認要求で指定されたコード チャレンジとcode\_verifierが一致しません。 PKCE パラメーターの使用を修正するには、アプリケーション所有者に問い合わせてください。 |
| AADSTS50146 | MissingCustomSigningKey - このアプリは、アプリ固有の署名キーを使って構成する必要があります。 そのように構成されていません。または、キーが有効期限切れか、またはまだ有効になっていません。 アプリケーションの所有者に問い合わせてください。 |
| AADSTS501461 | AcceptMappedClaims は、アプリケーション GUID と一致するトークン対象ユーザー、またはテナントの検証済みドメイン内の対象ユーザーでのみサポートされます。 リソース識別子を変更するか、アプリケーション固有の署名キーを使用します。 |
| AADSTS50147 | MissingCodeChallenge - コードのチャレンジ パラメーターのサイズが無効です。 |
| AADSTS501481 | Code\_Verifier が、承認要求で指定された code\_challenge と一致しません。 |
| AADSTS50149 | Code\_Challenge\_method パラメーターが無効です。 |
| AADSTS501491 | InvalidCodeChallengeMethodInvalidSize - Code\_Challenge パラメーターの無効なサイズ。 |
| AADSTS50150 | 指定された資格情報に、有効なユーザーの同意の承認情報がありません。 |
| AADSTS50155 | DeviceAuthenticationFailed - このユーザーのデバイス認証が失敗しました。 |
| AADSTS50156 | デバイス トークンは、V2 リソースではサポートされていません。 |
| AADSTS50157 | ルーティングに必要なユーザー リダイレクト。 |
| AADSTS50158 | 外部セキュリティ課題が対処されていません。 ユーザーは、追加の認証の課題を満たすために、別のページまたは認証プロバイダーにリダイレクトされます。 ユーザーは認証を終了する前に追加の要件を満たす必要があり、別のページ (使用条件やサードパーティの MFA プロバイダーなど) にリダイレクトされました。 このコードだけでは、ユーザーがサインインできなかったことを示すわけではありません。 サインイン ログは、このチャレンジが成功したか失敗したかを示す場合があります。 |
| AADSTS50159 | 外部プロバイダーから送信された請求では不十分です。 |
| AADSTS50160 | 別のターゲット テナントが推奨されます。 |
| AADSTS50161 | 外部クレーム プロバイダーの承認 URL を検証できませんでした。 |
| AADSTS50162 | 要求変換がタイムアウトしました。これは、このアプリケーションに対して構成されている変換が多すぎるか複雑すぎる可能性があることを示します。 要求を再試行すると成功する場合があります。 それ以外の場合は、管理者に問い合わせて構成を修正してください。 |
| AADSTS50163 | 要求変換の正規表現の置換により、サイズ制限を超える要求が発生しました。 構成を修正するには、管理者に問い合わせてください。 |
| AADSTS501631 | 要求変換の正規表現置換により、sourceClaimでの置換が多すぎます。 構成を修正するには、管理者に問い合わせてください。 |
| AADSTS501632 | クレーム変換のための正規表現の置換で、置換入力パラメータに含まれる代替パラメータが多すぎます。 構成を修正するには、管理者に問い合わせてください。 |
| AADSTS50164 | 指定されたアクセス トークンは、使用されている目的で発行されませんでした。 '{name}' の目的を持ったトークンが期待されています。 |
| AADSTS50165 | アプリケーションによって要求されたトークン暗号化アルゴリズム '{algorithm}' は、この種類のトークンではサポートされていません。 これは、アプリケーションが正しく構成されていないことを示します。 |
| AADSTS50166 | 外部 OIDC エンドポイントへの要求に失敗しました。 |
| AADSTS50167 | pop\_jwk キーが無効です。 |
| AADSTS50168 | クライアントは Windows 10 アカウント拡張機能を使用して SSO を実行できますが、要求で SSO トークンが見つからなかったか、トークンの有効期限が切れています。 SSO トークンのプルを試みる要求が中断されました。 |
| AADSTS50169 | InvalidRequestBadRealm - 領域が、現在のサービス名前空間の構成された領域ではありません。 |
| AADSTS50170 | MissingExternalClaimsProviderMapping - 外部コントロールのマッピングがありません。 |
| AADSTS50171 | 特定の対象ユーザーは、Mutual-TLS トークン呼び出しでのみ使用できます。 |
| AADSTS50172 | 外部クレーム プロバイダー {provider} が承認されていません。 |
| AADSTS50173 | 指定された許可が失効したため、有効期限が切れています。新しい認証トークンが必要です。 ユーザーが自分のパスワードを変更またはリセットした可能性があります。 助成金は '{authTime}' に発行され、このユーザーのトークンの有効期限開始日（それ以前はトークンが無効です）は '{validDate}' です。 詳細については、エラー [AADSTS50173](https://learn.microsoft.com/ja-jp/troubleshoot/entra/entra-id/app-integration/error-code-aadsts50173-grant-expired-revoked)のトラブルシューティングの記事を参照してください。 |
| AADSTS50176 | 外部コントロールの定義がありません: {controlId}。 |
| AADSTS50177 | ExternalChallengeNotSupportedForPassthroughUsers - 外部のチャレンジは、パススルー ユーザーに対してサポートされていません。 |
| AADSTS50178 | SessionControlNotSupportedForPassthroughUsers - セッション制御は、パススルー ユーザーに対してサポートされていません。 |
| AADSTS50180 | WindowsIntegratedAuthMissing - Windows 統合認証が必要です。 テナントで Seamless SSO を有効にしてください。 |
| AADSTS50187 | DeviceInformationNotProvided - サービスはデバイス認証を実行できませんでした。 |
| AADSTS50192 | 無効な要求 - RawCredentialExpectedNotFound - サインイン要求に資格情報が含まれていませんでした。 例: ユーザーが証明書ベースの認証 (CBA) を実行していて、サインイン要求でユーザー証明書が送信されていないか、(プロキシによって) ユーザー証明書が削除されています。 |
| AADSTS50194 | アプリケーション '{appId}'({appName}) はマルチテナント アプリケーションとして構成されていません。 '{time}' より後に作成されたそのようなアプリケーションでは、/common エンドポイントの使用はサポートされていません。 テナント固有のエンドポイントを使用するか、アプリケーションをマルチテナントとして構成してください。 |
| AADSTS50196 | LoopDetected - クライアント ループが検出されました。 アプリのロジックを調べて、確実にトークンのキャッシュが実装されていて、エラー状態が正しく処理されるようにします。 アプリが非常に短期間にあまりにも多くの同じ要求を行いました。これは、障害がある状態にあるか、またはトークンを不正に要求していることを示しています。 |
| AADSTS50197 | ConflictingIdentities - ユーザーが見つかりませんでした。 もう一度サインインしてみてください。 |
| AADSTS50199 | CmsiInterrupt - セキュリティ上の理由から、この要求にはユーザー確認が必要です。 中断は、モバイル ブラウザーでのすべてのスキーム リダイレクトで表示されます。 必要なアクションはありません。 ユーザーは、このアプリがサインインするつもりであったアプリケーションであることを確認するように求められました。 これは、スプーフィング攻撃を防ぐのに役立つセキュリティ機能です。 これは、システム Web ビューを使用してネイティブ アプリケーションのトークンを要求したために発生します。 このメッセージを表示しないようにするには、リダイレクト URI が次のセーフ リストに含まれている必要があります。http://https://chrome-extension://(デスクトップ Chrome ブラウザーのみ) |
| AADSTS51000 | RequiredFeatureNotEnabled - 機能が無効になっています。 |
| AADSTS51001 | DomainHintMustbePresent - ドメイン ヒントは、オンプレミスのセキュリティ識別子またはオンプレミスの UPN とともに存在している必要があります。 |
| AADSTS1000104 | XCB2BResourceCloudNotAllowedOnIdentityTenant - リソース クラウド {resourceCloud} は、ID テナント {identityTenant} では許可されていません。 {resourceCloud} - リソースを所有するクラウド インスタンス。 {identityTenant} - サインイン ID の発信元であるテナントです。 |
| AADSTS51004 | UserAccountNotInDirectory - ディレクトリにユーザー アカウントが存在しません。 アプリケーションがサインインするテナントを間違えて選んだ可能性が高く、現在ログインしているユーザーがテナントに存在しないため、サインインできませんでした。 このユーザーがログインできるようにする場合は、ゲストとして追加します。 詳細については、「 [B2B ユーザーの追加](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)」を参照してください。 |
| AADSTS51005 | TemporaryRedirect - 要求された情報が Location ヘッダーで指定された URI にあることを示す、HTTP ステータス 307 と同等です。 この状態が表示されたら、応答に関連付けられた Location ヘッダーに従います。 元の要求メソッドが POST の場合、リダイレクトされた要求も POST メソッドを使用します。 |
| AADSTS51006 | ForceReauthDueToInsufficientAuth - Windows 統合認証が必要です。 ユーザーは、統合 Windows 認証の要求がないセッション トークンを使用してログインしました。 もう一度ログインするように、ユーザーに要求します。 |
| AADSTS52004 | DelegationDoesNotExistForLinkedIn - ユーザーは、LinkedIn リソースへのアクセスに対する同意を提供していません。 |
| AADSTS53000 | DeviceNotCompliant 条件付きアクセス ポリシーでは準拠デバイスを要求していますが、デバイスが準拠していません。 ユーザーは、Intune などの承認済み MDM プロバイダーにデバイスを登録する必要があります。 詳細については、 [条件付きアクセス デバイスの修復に](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-conditional-access)関するページを参照してください。 |
| AADSTS53001 | DeviceNotDomainJoined - 条件付きアクセス ポリシーではドメイン参加デバイスを要求していますが、デバイスがドメインに参加していません。 ユーザーにドメイン参加デバイスを使用させます。 |
| AADSTS53002 | ApplicationUsedIsNotAnApprovedApp - 使用されているアプリは、条件付きアクセスの承認済みアプリではありません。 アクセスするには、ユーザーは承認されたアプリの一覧からアプリを 1 つ選んで使用する必要があります。 |
| AADSTS53003 | BlockedByConditionalAccess - 条件付きアクセス ポリシーにより、アクセスがブロックされました。 アクセス ポリシーでは、トークンの発行が許可されていません。 これが想定外の場合は、この要求に適用された条件付きアクセス ポリシーを確認するか、管理者に問い合わせてください。 詳細については、 [条件付きアクセスによるサインインのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-conditional-access)を参照してください。 |
| AADSTS530035 | BlockedBySecurityDefaults - セキュリティの既定値によってアクセスがブロックされました。 これは、リクエストでレガシ認証が使用されているか、セキュリティの既定値ポリシーによって安全ではないと判断されたことが原因です。 詳細については、 [適用されるセキュリティ ポリシーを](https://learn.microsoft.com/ja-jp/entra/fundamentals/security-defaults#enforced-security-policies)参照してください。 |
| AADSTS53004 | ProofUpBlockedDueToRisk - ユーザーは、このコンテンツにアクセスする前に、多要素認証登録プロセスを完了する必要があります。 ユーザーは多要素認証に登録する必要があります。 |
| AADSTS53010 | ProofUpBlockedDueToSecurityInfoAcr - 組織では、この情報を特定の場所またはデバイスから設定する必要があるため、多要素認証方法を構成できません。 |
| AADSTS53011 (エラーコード: 認証に失敗しました。原因を確認してください。) | ホーム テナントのリスクによりユーザーがブロックされました。 |
| AADSTS530034 | DelegatedAdminBlockedDueToSuspiciousActivity - 委任された管理者は、所属テナントでのアカウントのリスクのため、テナントへのアクセスをブロックされました。 |
| AADSTS54000 | 未成年ユーザー法定年齢グループ制限ルール |
| AADSTS54005 | OAuth2 認証コードは既に引き換え済みです。新しい有効なコードを使用してもう一度やり直すか、既存の更新トークンを使用してください。 |
| AADSTS65001 | DelegationDoesNotExist - X という ID でアプリケーションを使用することにユーザーまたは管理者が同意していません。このユーザーとリソースのインタラクティブな承認要求を送信してください。 |
| AADSTS65002 | ファースト パーティのアプリケーション '{applicationId}' とファースト パーティのリソース '{resourceId}' との間の同意は、事前承認で構成されている必要があります。Microsoft が所有し運営するアプリケーションは、API のトークンを要求する前に、その API の所有者から承認を得なければなりません。 テナント内の開発者が、Microsoft が所有するアプリ ID を再利用しようとしている可能性があります。 このエラーが発生すると、Microsoft のアプリケーションを偽装して他の API を呼び出すことができなくなります。 この場合、登録した別のアプリ ID に移行する必要があります。 |
| AADSTS65004 | UserDeclinedConsent - ユーザーはアプリへのアクセスの同意を拒否しました。 ユーザーに、再度サインインしてアプリに同意させてください |
| AADSTS65005 | MisconfiguredApplication - アプリの必須リソース アクセス リストに、リソースによって検出可能なアプリが含まれていません。または、必須リソース アクセス リストで指定されていないリソースへのアクセスをクライアント アプリが要求したか、Graph サービスから無効な要求が返されたか、リソースが見つかりません。 アプリが SAML をサポートしている場合、間違った識別子 (エンティティ) でアプリを構成している可能性があります。 詳細については、エラー [AADSTS650056](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/error-code-aadsts650056-misconfigured-app)のトラブルシューティングの記事を参照してください。 |
| AADSTS650052 | このアプリには、組織 `(\"{name}\")` がサブスクライブしていないか有効にしていない `\"{organization}\"` サービスへのアクセスが必要です。 サービス サブスクリプションの構成を確認するには、IT 管理者にお問い合わせください。 |
| AADSTS650054 | アプリケーションが、削除されたか使用できなくなったリソースにアクセスするためのアクセス許可を要求しました。 アプリが呼び出しているすべてのリソースが、操作中のテナントに存在していることを確認してください。 |
| AADSTS650056 | アプリケーションが正しく構成されていません。 この場合は、次のいずれかの原因が考えられます。クライアントが、クライアントのアプリケーション登録の要求されたアクセス許可に '{name}' のアクセス許可を記載していません。 または、管理者がテナントで同意していません。 あるいは、要求のアプリケーション識別子を調べて、構成したクライアント アプリケーション識別子に一致することを確認してください。 または、要求の証明書を確認して、有効であることを確認します。 構成を修正するか、テナントの代わりに同意するように管理者に連絡してください。 クライアント アプリ ID: {ID}。 構成を修正するか、テナントの代わりに同意するように管理者に連絡してください。 |
| AADSTS650057 | 無効なリソースです。 クライアントが、クライアントのアプリケーションの登録で要求されたアクセス許可に記載されていないリソースへのアクセスを要求しています。 クライアント アプリ ID: {appId}({appName})。 要求のリソース値: {resource}。 リソース アプリ ID: {resourceAppId}。 アプリの登録からの有効なリソースのリスト: {regList}。 |
| AADSTS67003 | アクターが有効なサービスIDではありません |
| AADSTS70000 | InvalidGrant - 認証に失敗しました。 更新トークンが無効です。 次のいずれかの理由によってエラーが発生している可能性があります。<br>- トークンのバインド ヘッダーが空<br>- トークンのバインド ハッシュが一致しない |
| AADSTS70001 | UnauthorizedClient - アプリケーションが無効です。 詳細については、エラー [のAADSTS70001](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/error-code-aadsts70001-app-not-found-in-directory)に関するトラブルシューティングの記事を参照してください。 |
| AADSTS700011 | UnauthorizedClientAppNotFoundInOrgIdTenant - 識別子 {appIdentifier} を持つアプリケーションがディレクトリ内で見つかりませんでした。 クライアント アプリケーションがテナントからトークンを要求しましたが、そのクライアント アプリがテナントに存在しないため、呼び出しに失敗しました。 |
| AADSTS70002 | InvalidClient - 資格情報の検証中にエラーが発生しました。 指定された client\_secret が、このクライアントに予期される値と一致しません。 client\_secret を修正してから、やり直してください。 詳細については、「 [承認コードを使用してアクセス トークンを要求する」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#redeem-a-code-for-an-access-token)参照してください。 |
| AADSTS700025 | InvalidClientPublicClientWithCredential - クライアントはパブリックなので、'client\_assertion' も 'client\_secret' も指定できません。 |
| AADSTS700027 | クライアント アサーションでシグネチャの検証に失敗しました。 開発者エラー - アプリは、必要な認証パラメーターまたは正しい認証パラメーターを使用せずにサインインしようとしています。 |
| AADSTS70003 | UnsupportedGrantType - アプリがサポートされていない付与タイプを返しました。 |
| AADSTS700030 | 無効な証明書 - 証明書のサブジェクト名が承認されていません。 トークン証明書の SubjectNames/SubjectAlternativeNames (最大 10 個) は、{certificateSubjects} です。 |
| AADSTS70004 | InvalidRedirectUri - アプリが無効なリダイレクト URI を返しました。 クライアントによって指定されているリダイレクト アドレスが、構成されているどのアドレス、または OIDC 承認リストのどのアドレスとも、一致しません。 |
| AADSTS70005 | UnsupportedResponseType - 次の理由により、アプリがサポートされていない応答の種類を返しました。<br>- 応答の種類 "token" がアプリに対して有効になっていません<br>- 応答の種類 "id\_token" には "OpenID" スコープが必要です。エンコードされた wctx にサポートされていない OAuth パラメーター値が含まれます |
| AADSTS700054 | Response\_type 'id\_token' がアプリケーションに対して有効になっていません。 アプリケーションが承認エンドポイントから ID トークンを要求しましたが、ID トークンの暗黙的な許可が有効になっていませんでした。 [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインし、**Entra ID**&gt;**App 登録**を参照します。 アプリケーションを選択し、[ **認証**] を選択します。 [ **暗黙的な許可とハイブリッド フロー**] で、 **ID トークン** が選択されていることを確認します。 |
| AADSTS70007 | UnsupportedResponseMode - アプリが、トークンを要求するときに、`response_mode` のサポートされていない値を返しました。 |
| AADSTS70008 | ExpiredOrRevokedGrant - 非アクティブのため、更新トークンの有効期限が切れました。 トークンは XXX に発行され、一定期間、非アクティブでした。 |
| AADSTS700082 | ExpiredOrRevokedGrantInactiveToken - リフレッシュ トークンは非アクティブ状態が続いたため、有効期限が切れました。 トークンは {issueDate} に発行され、{time} の間、非アクティブでした。 トークンのライフサイクルの想定される一環 - ユーザーが長期間アプリを使わなかったため、アプリがトークンを更新しようとしたときに期限切れになりました。 |
| AADSTS700084 | この更新トークンは、シングル ページ アプリ (SPA) に発行されたため、延長できない、制限された固定の有効期間 {time} が割り当てられています。 これは既に有効期限が切れているため、SPA がサインイン ページに新しいサインイン要求を送信する必要があります。 このトークンは {issueDate} に発行されました。 |
| AADSTS70011 | InvalidScope - アプリによって要求されたスコープが無効です。 |
| AADSTS70012 | MsaServerError - MSA (コンシューマー) ユーザーの認証中にサーバー エラーが発生しました。 やり直してください。 失敗が続く場合は、 [サポート チケットを開きます](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-get-support) |
| AADSTS70016 | AuthorizationPending - OAuth 2.0 デバイス フロー エラー。 承認は保留中です。 デバイスは要求のポーリング処理を再試行します。 |
| AADSTS70018 | BadVerificationCode - ユーザーがデバイス コード フローに誤ったユーザー コードを入力したため、確認コードが無効です。 承認が承認されていません。 |
| AADSTS70019 | CodeExpired - 確認コードの有効期限が切れました。 ユーザーにサインインを再試行させてください。 |
| AADSTS70043 | BadTokenDueToSignInFrequency - 条件付きアクセスによるサインイン頻度チェックが原因で、更新トークンは期限切れになっているか、無効になっています。 このトークンは {issueDate} に発行され、この要求で許可される最長の有効期間は {time} です。 |
| AADSTS75001 | BindingSerializationError - SAML メッセージ バインド中にエラーが発生しました。 |
| AADSTS75003 | UnsupportedBindingError - アプリが、サポートされていないバインドに関連するエラーを返しました (SAML プロトコルの応答は、HTTP POST 以外のバインド経由では送信できません)。 |
| AADSTS75005 | Saml2MessageInvalid - Microsoft Entra は、SSO 用のアプリによって送信された SAML 要求をサポートしていません。 詳細については、エラー [AADSTS75005](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/error-code-aadsts75005-not-a-valid-saml-request)のトラブルシューティングの記事を参照してください。 |
| AADSTS7500514 | サポートされている SAML 応答の種類が見つかりませんでした。 サポートされている応答の種類は "Response" (XML 名前空間 "urn:oasis:names:tc:SAML:2.0:protocol") または "Assertion" (XML 名前空間 "urn:oasis:names:tc:SAML:2.0:assertion") です。 アプリケーション エラー - 開発者がこのエラーを処理します。 |
| AADSTS750054 | SAML リダイレクト バインディング用の HTTP 要求に、SAMLRequest または SAMLResponse がクエリ文字列のパラメーターとしてある必要があります。 詳細については、エラー [AADSTS750054](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/error-code-aadsts750054-saml-request-not-present)のトラブルシューティングの記事を参照してください。 |
| AADSTS75008 | RequestDeniedError - SAML 要求に予期しない宛先が設定されているため、アプリからの要求は拒否されました。 |
| AADSTS75011 | NoMatchedAuthnContextInOutputClaims - ユーザーがサービスで認証を行った際に使用した認証方法が、要求された認証方法と一致しません。 詳細については、エラー [AADSTS75011](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/error-code-aadsts75011-auth-method-mismatch)のトラブルシューティングの記事を参照してください。 |
| AADSTS75016 | Saml2AuthenticationRequestInvalidNameIDPolicy - SAML2 認証要求の NameIdPolicy が無効です。 |
| AADSTS76021 | ApplicationRequiresSignedRequests - アプリケーションには署名されたリクエストが必要ですが、クライアントによって送信された要求は署名されていません |
| AADSTS76026 | RequestIssueTimeExpired - SAML2 認証要求の IssueTime の有効期限が切れています。 |
| AADSTS80001 | OnPremiseStoreIsNotAvailable - 認証エージェントが Active Directory に接続できません。 エージェント サーバーが、パスワードを検証する必要のあるユーザーと同じ AD フォレストのメンバーであり、Active Directory に接続できることを確認します。 |
| AADSTS80002 | OnPremisePasswordValidatorRequestTimedout - パスワード検証要求がタイムアウトしました。Active Directory が使用可能で、エージェントからの要求に応答していることを確認します。 |
| AADSTS80005 | OnPremisePasswordValidatorUnpredictableWebException - 認証エージェントからの応答の処理中に不明なエラーが発生しました。 要求をやり直してください。 失敗が続く場合は、 [サポート チケットを開](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-get-support) いてエラーの詳細を確認してください。 |
| AADSTS80007 | OnPremisePasswordValidatorErrorOccurredOnPrem - 認証エージェントはユーザーのパスワードを検証できません。 エージェント ログで詳細を確認し、Active Directory が期待通りに動作していることを確認してください。 |
| AADSTS80010 | OnPremisePasswordValidationEncryptionException - 認証エージェントはパスワードを復号化できません。 |
| AADSTS80012 | OnPremisePasswordValidationAccountLogonInvalidHours - ユーザーは許可されている時間範囲外にログオンしようとしました (時間は AD で指定されています)。 |
| AADSTS80013 | OnPremisePasswordValidationTimeSkew - 認証エージェントを実行しているコンピューターと AD の間に時間のずれがあるため、認証の試行を完了できませんでした。 時刻同期の問題を解決してください。 |
| AADSTS80014 | OnPremisePasswordValidationAuthenticationAgentTimeout - 検証要求からの応答が最大経過時間を超えました。 このエラーの詳細を調べるには、エラー コード、相関 ID、タイムスタンプを添えて、サポート チケットを開いてください。 |
| AADSTS81004 | DesktopSsoIdentityInTicketIsNotAuthenticated - Kerberos 認証の試行に失敗しました。 |
| AADSTS81005 | DesktopSsoAuthenticationPackageNotSupported - 認証パッケージがサポートされていません。 |
| AADSTS81006 | DesktopSsoNoAuthorizationHeader - Authorization ヘッダーが見つかりませんでした。 |
| AADSTS81007 | DesktopSsoTenantIsNotOptIn - テナントがシームレス SSO 用に有効化されていません。 |
| AADSTS81009 | DesktopSsoAuthorizationHeaderValueWithBadFormat - ユーザーの Kerberos チケットを検証できません。 |
| AADSTS81010 | DesktopSsoAuthTokenInvalid - ユーザーの Kerberos チケットが期限切れか無効のため、シームレス SSO に失敗しました。 |
| AADSTS81011 | DesktopSsoLookupUserBySidFailed - ユーザーの Kerberos チケット内の情報では、ユーザー オブジェクトが見つかりません。 |
| AADSTS81012 | DesktopSsoMismatchBetweenTokenUpnAndChosenUpn - Microsoft Entra ID にサインインしようとしているユーザーは、デバイスにサインインしているユーザーと異なります。 |
| AADSTS90002 | InvalidTenantName - データ ストアでテナント名が見つかりませんでした。 正しいテナント ID があることを確認します。 アプリケーション開発者の場合、アプリが見つからないテナントにサインインしようとすると、このエラーが表示されます。 多くの場合、これは、クラウド間アプリが間違ったクラウドに対して使われたか、開発者がメール アドレスから導かれるテナントにサインインしようとしたが、そのドメインが登録されていないことが原因です。 |
| AADSTS90004 | InvalidRequestFormat - 要求の形式が正しくありません。 |
| AADSTS90005 | InvalidRequestWithMultipleRequirements - リクエストを完了できません。 識別子とログイン ヒントを一緒に使用することはできないため、要求は無効です。 |
| AADSTS90006 | ExternalServerRetryableError - サービスは一時的に利用できません。 |
| AADSTS90007 | InvalidSessionId - 要求が正しくありません。 渡されたセッション ID を解析できません。 |
| AADSTS90008 | TokenForItselfRequiresGraphPermission - ユーザーまたは管理者がアプリケーションを使用することに同意していません。 少なくとも、アプリケーションではサインインおよびユーザー プロファイルの読み取りのアクセス許可を指定して Microsoft Entra ID にアクセスする必要があります。 |
| AADSTS90009 | TokenForItselfMissingIdenticalAppIdentifier - アプリケーションは、自分自身のトークンを要求しています。 このシナリオは、指定されたリソースが GUID ベースのアプリケーション ID を使用する場合にのみサポートされます。 |
| AADSTS90010 | NotSupported: アルゴリズムを生成できません。 |
| AADSTS9001023 | 付与タイプが /common または /consumers エンドポイントでサポートされていません。 /organizations またはテナント固有のエンドポイントを使用してください。 |
| AADSTS90012 | RequestTimeout - 要求がタイムアウトしました。 |
| AADSTS90013 | InvalidUserInput - ユーザーからの入力が無効です。 |
| AADSTS90014 | MissingRequiredField - このエラー コードは、さまざまなケースで、資格情報に想定されているフィールドが存在しないときに表示される場合があります。 |
| AADSTS900144 | 要求本文には、次のパラメーターが含まれている必要があります: '{name}'。 開発者エラー - アプリは、必要な認証パラメーターまたは正しい認証パラメーターを使用せずにサインインしようとしています。 |
| AADSTS90015 | QueryStringTooLong - クエリ文字列が長すぎます。 |
| AADSTS90016 | MissingRequiredClaim - アクセス トークンが無効です。 必要なクレームが見つかりません。 |
| AADSTS90019 | MissingTenantRealm - Microsoft Entra ID で要求からテナント識別子を特定できませんでした。 |
| AADSTS90020 | SAML 1.1 アサーションにユーザーの ImmutableID が含まれていません。 開発者エラー - アプリは、必要な認証パラメーターまたは正しい認証パラメーターを使用せずにサインインしようとしています。 |
| AADSTS90022 | AuthenticatedInvalidPrincipalNameFormat - プリンシパル名の形式が無効であるか、想定される `name[/host][@realm]` 形式を満たしていません。 プリンシパル名は必須です。ホストと領域は省略可能で、null に設定できます。 |
| AADSTS90023 | InvalidRequest - 認証サービス要求が有効ではありません。 |
| AADSTS900236 | InvalidRequestSamlPropertyUnsupported- SAML 認証要求プロパティ '{propertyName}' はサポートされておらず、設定してはなりません。 |
| AADSTS9002313 | InvalidRequest - 要求の形式が正しくないか、無効です。 - この問題は、特定のエンドポイントへの要求に何か問題があったために発生します。 この問題に対する提案としては、発生しているエラーの Fiddler トレースを取得し、リクエストが正しくフォーマットされているかどうかを確認することです。 |
| AADSTS9002332 | アプリケーション '{principalId}'({principalName}) は Microsoft Entra ユーザーのみが使用するように構成されています。 この要求に応答するために、/consumers エンドポイントを使用しないでください。 |
| AADSTS90024 | RequestBudgetExceededError - 一時的なエラーが発生しました。 やり直してください。 |
| AADSTS90027 | MSA テナントでは、この API バージョンからトークンを発行することができません。 これをサポートするには、プロトコルのバージョン 2.0 を使用してもらう必要があるため、アプリケーション ベンダーにお問い合わせください。 |
| AADSTS90033 | MsodsServiceUnavailable - Microsoft Online Directory Service (MSODS) が使用できません。 |
| AADSTS90036 | MsodsServiceUnretryableFailure - MSODS によってホストされる WCF サービスから再試行できない予期しないエラーが発生しました。 [エラーの詳細については、サポート チケットを開いてください](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-get-support) 。 |
| AADSTS90038 | NationalCloudTenantRedirection - 指定したテナント 'Y' は、国内クラウド 'X' に属しています。 現在のクラウド インスタンス 'Z' が X とフェデレーションしていません。クラウドのリダイレクト エラーが返されます。 |
| AADSTS900384 | JWT トークンが署名の検証に失敗しました。 実際のメッセージの内容はランタイム固有です。このエラーにはさまざまな原因があります。 詳細については、返された例外メッセージを参照してください。 |
| エラーコード: AADSTS90043 | NationalCloudAuthCodeRedirection - 機能が無効になっています。 |
| AADSTS900432 | Confidential Client はクロスクラウド要求でサポートされていません。 |
| AADSTS90051 | InvalidNationalCloudId - 国内クラウド識別子に無効なクラウド識別子が含まれています。 |
| AADSTS90055 | TenantThrottlingError - 着信要求が多すぎます。 この例外は、ブロックされたテナントに対して送出されます。 |
| AADSTS90056 | BadResourceRequest - コードをアクセス トークンと引き換えるには、アプリで `/token` エンドポイントに POST 要求を送信する必要があります。 また、その前に認証コードを提供し、それを POST 要求で `/token` エンドポイントに送信する必要があります。 [OAuth 2.0 承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)の概要については、この記事を参照してください。 ユーザーを authorization\_code を返す `/authorize` エンドポイントにリダイレクトしてください。 `/token` エンドポイントに要求をポストすることで、ユーザーはアクセス トークンを取得します。 2 つの&gt;正しく構成されていることを確認するには、エンドポイントアプリの登録を確認します。 |
| AADSTS900561 | BadResourceRequestInvalidRequest - エンドポイントは {valid\_verbs} 要求のみを受け入れます。 {invalid\_verb} リクエストを受信しました。 {valid\_verbs} はエンドポイントがサポートする HTTP 動詞 (たとえば、POST) の一覧を表し、{invalid\_verb} は現在の要求で使われる HTTP 動詞 (たとえば、GET) を表します。 この原因は開発者の誤りであるか、ユーザーがブラウザーの戻るボタンを押して不正な要求をトリガーした可能性があります。 これは無視できます。 |
| AADSTS900612 | プロバイダー署名キーの解析に失敗しました - JWKS エンドポイントによって提供される署名キーを検証する際に、予期される形式ではありません。 |
| AADSTS90072 | PassThroughUserMfaError - ユーザーがサインインに使用した外部アカウントが、ユーザーがサインインしているテナントに存在しません。そのため、ユーザーはテナントの MFA 要件を満たすことができません。 このエラーは、ユーザーが同期されていても、Active Directory と Microsoft Entra ID 間で ImmutableID (sourceAnchor) 属性に不一致がある場合にも発生する可能性があります。 アカウントをまずテナントに外部ユーザーとして追加する必要があります。 サインアウト後、別の Microsoft Entra ユーザー アカウントでサインインしてください。 詳細については、 [外部 ID の構成](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview)に関するページを参照してください。 |
| AADSTS90081 | OrgIdWsFederationMessageInvalid - サービスが WS-Federation メッセージを処理しようとしたときにエラーが発生しました。 メッセージが無効です。 |
| AADSTS90082 | OrgIdWsFederationNotSupported - 要求に対して選択された認証ポリシーは現在サポートされていません。 |
| AADSTS90084 | OrgIdWsFederationGuestNotAllowed - ゲスト アカウントは、このサイトでは許可されていません。 |
| AADSTS90085 | OrgIdWsFederationSltRedemptionFailed - 会社のオブジェクトがまだプロビジョニングされていないため、サービスはトークンを発行することができません。 |
| AADSTS90086 | OrgIdWsTrustDaTokenExpired - ユーザーの DA トークンの有効期限が切れています。 |
| AADSTS90087 | OrgIdWsFederationMessageCreationFromUriFailed - URI から WS-Federation メッセージを作成中にエラーが発生しました。 |
| AADSTS90090 | GraphRetryableError - サービスは一時的に利用できません。 |
| AADSTS90091 | グラフサービス未到達 |
| AADSTS90092 | グラフ非リトライ可能エラー |
| AADSTS90093 | GraphUserUnauthorized - この要求に対して、Graph から禁止エラー コードが返されました。 |
| AADSTS90094 | AdminConsentRequired - 管理者の同意が必要です。 |
| AADSTS900382 | Confidential Client はクロスクラウド要求でサポートされていません。 |
| AADSTS90095 | AdminConsentRequiredRequestAccess - 管理者の同意ワークフロー エクスペリエンスで、ユーザーが管理者に同意を求める必要があるということを示す際に表示される割り込みです。 |
| AADSTS90099 | アプリケーション '{appId}' ({appName}) は、テナント '{tenant}' で承認されていません。 アプリケーションは、パートナー代理管理者が使用できるようになる前に、外部テナントへのアクセスが承認されている必要があります。 事前同意を提供するか、適切な Partner Center API を実行してアプリケーションを承認します。 |
| AADSTS900971 | 応答アドレスが指定されていません。 |
| AADSTS90100 | InvalidRequestParameter - パラメーターが空であるか無効です。 |
| AADSTS901002 | AADSTS901002:"resource" 要求パラメーターはサポートされていません。 |
| AADSTS90101 | InvalidEmailAddress - 指定されたデータは有効な電子メール アドレスはありません。 電子メール アドレスは `someone@example.com` の形式である必要があります。 |
| AADSTS90102 | InvalidUriParameter - 値は有効な絶対 URI である必要があります。 |
| AADSTS90107 | InvalidXml - 要求は無効です。 データに無効な文字が含まれていないことを確認してください。 |
| AADSTS90112 | アプリケーション識別子は GUID である必要があります。 |
| AADSTS90114 | InvalidExpiryDate - 一括トークンの有効期限のタイムスタンプが原因で期限切れのトークンが発行されます。 |
| AADSTS90117 | 無効なリクエスト入力 |
| AADSTS90119 | InvalidUserCode - ユーザー コードが null 値または空です。 |
| AADSTS90120 | InvalidDeviceFlowRequest - 要求は既に承認されているか拒否されています。 |
| AADSTS90121 | InvalidEmptyRequest - 無効な空の要求です。 |
| AADSTS90123 | IdentityProviderAccessDenied - ID または要求の発行プロバイダーが要求を拒否したため、トークンを発行できません。 |
| AADSTS90124 | V1ResourceV2GlobalEndpointNotSupported - リソースは、`/common` または `/consumers` エンドポイント経由でサポートされていません。 `/organizations` またはテナント固有のエンドポイントを使用してください。 |
| AADSTS90125 | DebugModeEnrollTenantNotFound - ユーザーはシステムに存在しません。 ユーザー名を正しく入力したか確認してください。 |
| AADSTS90126 | DebugModeEnrollTenantNotInferred - このエンドポイントではこのユーザーの種類はサポートされていません。 システムは、ユーザー名からユーザーのテナントを推論できません。 |
| AADSTS90130 | NonConvergedAppV2GlobalEndpointNotSupported - アプリケーションは、`/common` または `/consumers` エンドポイント経由でサポートされていません。 `/organizations` またはテナント固有のエンドポイントを使用してください。 |
| AADSTS120000 | 現在のパスワードが正しくありません（パスワード変更） |
| AADSTS120002 | パスワード変更: 新しいパスワードが弱すぎます |
| AADSTS120003 | 新しいパスワードにメンバー名が含まれていますので、変更できません。 |
| AADSTS120004 | オンプレミス環境でのパスワード変更の複雑性 |
| AADSTS120005 | パスワード変更オンプレ成功クラウド失敗 |
| AADSTS120008 | PasswordChangeAsyncJobStateTerminated - 再試行できないエラーが発生しました。 |
| AADSTS120011 | 非同期パスワード変更のUPN推論に失敗しました |
| AADSTS120012 | パスワード変更はオンプレミス環境で行う必要があります。 |
| AADSTS120013 | パスワード変更オンプレミス接続障害 |
| AADSTS120014 | パスワード変更オンプレミスユーザーアカウントロックアウトまたは無効化 |
| AADSTS120015 | パスワード変更のためのAD管理者アクションが必要です |
| AADSTS120016 | SSPR によるパスワード変更でユーザーが見つかりませんでした |
| AADSTS120018 | パスワード変更: パスワードがあいまいなポリシーに準拠していません |
| AADSTS120020 | パスワード変更失敗 |
| AADSTS120021 | PartnerService Sspr内部サービスエラー |
| AADSTS130004 | NgcKeyNotFound - ユーザー プリンシパルには、構成された NGC ID キーがありません。 |
| AADSTS130005 | NgcInvalidSignature - NGC キー署名の検証に失敗しました。 |
| AADSTS130006 | NgcTransportKeyNotFound - NGC トランスポート キーがデバイスに構成されていません。 |
| AADSTS130007 | NgcDeviceIsDisabled - デバイスは無効になっています。 |
| AADSTS130008 | NgcDeviceIsNotFound - NGC キーが参照しているデバイスが見つかりませんでした。 |
| AADSTS135010 | キーが見つかりません |
| AADSTS135011 | 認証中に使用されるデバイスは無効になります。 |
| AADSTS140000 | InvalidRequestNonce - 要求の nonce が指定されていません。 |
| AADSTS140001 | InvalidSessionKey - セッション キーが無効です。 |
| AADSTS165004 | 実際のメッセージの内容はランタイム固有です。 詳細については、応答の例外メッセージを参照してください。 |
| AADSTS165900 | InvalidApiRequest - 無効な要求です。 |
| エラーコード: AADSTS220450 | UnsupportedAndroidWebViewVersion - Chrome WebView バージョンがサポートされていません。 |
| AADSTS220501 | 無効なCRLダウンロード |
| AADSTS221000 | DeviceOnlyTokensNotSupportedByResource - リソースは、デバイス専用のトークンを受け入れるように構成されていません。 |
| AADSTS240001 | BulkAADJTokenUnauthorized - ユーザーは、Microsoft Entra ID にデバイスを登録する権限がありません。 |
| AADSTS240002 | RequiredClaimIsMissing - id\_token を `urn:ietf:params:oauth:grant-type:jwt-bearer` grant として使用できません。 |
| AADSTS240003 | エンドポイント呼び出しの承認によって予期しない結果が発生しました。 |
| AADSTS240004 | 承認コードが承認エンドポイント呼び出しから受信されない。 エラー: {errorInfo}。 |
| AADSTS501621 | ClaimsTransformationTimeoutRegularExpressionTimeout - 要求変換の正規表現置換がタイムアウトしました。これは、このアプリケーションに対して構成された正規表現が複雑すぎる可能性を示しています。 要求を再試行すると成功する場合があります。 それ以外の場合は、管理者に問い合わせて構成を修正してください。 |
| AADSTS530032 | BlockedByConditionalAccessOnSecurityPolicy - テナント管理者が、この要求をブロックするセキュリティ ポリシーを設定しています。 テナント レベルで定義されているセキュリティ ポリシーを確認し、ご自身の要求がポリシーの要件を満たしているかどうか判断してください。 |
| AADSTS700016 | UnauthorizedClient\_DoesNotMatchRequest - アプリケーションがディレクトリ/テナント内に見つかりませんでした。 このエラーは、アプリケーションがテナントの管理者によってインストールされていない場合や、アプリケーションがテナント内のいずれのユーザーによっても同意されていない場合に発生することがあります。 アプリケーションの識別子の値を正しく構成していないか、または間違ったテナントに認証要求を送信した可能性があります。 |
| AADSTS700020 | InteractionRequired - アクセス許可には操作が必要です。 |
| AADSTS700022 | InvalidMultipleResourcesScope - 入力パラメーターのスコープに指定された値に複数のリソースが含まれているため無効です。 |
| AADSTS700023 | InvalidResourcelessScope - アクセス トークンを要求するときに、入力パラメーターのスコープに指定された値が無効です。 |
| AADSTS7000215 | 無効なクライアント シークレットが指定されています。 開発者エラー - アプリは、必要な認証パラメーターまたは正しい認証パラメーターを使用せずにサインインしようとしています。 |
| AADSTS7000218 | 要求本文には、次のパラメーターが含まれる必要があります: 'client\_assertion' または 'client\_secret'。 |
| AADSTS7000222 | InvalidClientSecretExpiredKeysProvided - 指定されたクライアント秘密鍵の有効期限が切れています。 アプリの新しいキーを作成するか、またはセキュリティを強化するために証明書資格情報を使用することを検討してください ([https://aka.ms/certCreds](https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials))。 |
| AADSTS700229 | ForbiddenTokenType - Microsoft Entra 発行者のフェデレーション ID 資格情報として使用できるのはアプリ専用トークンのみです。 ユーザー委任アクセス トークン (ユーザー コンテキストからの要求を表す) ではなく、アプリ専用アクセス トークン (クライアント資格情報フロー中に生成) を使用してください。 |
| AADSTS700005 | InvalidGrantRedeemAgainstWrongTenant - 指定された承認コードは別のテナントに対して使用することが想定されているため、そのため拒否されました。 OAuth2 承認コードは、それが取得されたときの同じテナント (必要に応じて /common または /{tenant-ID}) に対して引き換える必要があります。 |
| AADSTS1000000 | UserNotBoundError - Bind API では Microsoft Entra ユーザーも外部 IDP による認証が必要ですが、まだ行われていません。 |
| AADSTS1000002 | BindCompleteInterruptError - バインドは正常に完了しましたが、ユーザーに通知する必要があります。 |
| AADSTS100007 | Microsoft Entra Regional は、MSI、または Microsoft インフラストラクチャ テナント内の 1P アプリまたは 3P アプリの SN+I を使用した MSAL からの要求に対する認証のみをサポートします。 |
| AADSTS1000031 | 現時点では、アプリケーション {appDisplayName} にアクセスすることはできません。 管理者に問い合わせてください。 |
| AADSTS7000112 | UnauthorizedClientApplicationDisabled - アプリケーションが無効です。 |
| AADSTS7000114 | アプリケーション 'appIdentifier' には、アプリケーションの代理呼び出しを行うことが許可されていません。 |
| AADSTS7500529 | 値 SAMLId-Guid は有効な SAML ID ではありません- Microsoft Entra ID ではこの属性を使用して、返される応答の InResponseTo 属性が設定されます。 ID の 1 文字目に数字を使用することはできないので、一般的な方法としては、GUID の文字列表現の前に "ID" のような文字列を付加します。 たとえば、id6c1c178c166d486687be4aaf5e482730 は有効な ID です。 |
| AADSTS9002341 | V2Error: `invalid_grant` - ユーザーはシングル サインオン (SSO) を許可する必要があります。 このエラーは、ユーザーが SSO を実行するためにアプリケーションに必要なアクセス許可を付与していない場合に発生します。 必要なアクセス許可を付与するには、ユーザーを同意画面にリダイレクトする必要があります。 詳細については、 [このお知らせ](https://techcommunity.microsoft.com/t5/windows-it-pro-blog/upcoming-changes-to-windows-single-sign-on/ba-p/4008151) を参照してください。 |
| AADSTS901011 | NoEmailAddressCollectedFromExternalOidcIDP - 外部 OpenID Connect (OIDC) ID プロバイダーからメール アドレスを取得できませんでした。 これは通常、ユーザーがサインアップ時に [ **メールを非表示にする** ] を選択した場合に発生します。 |
| AADSTS901012 | EmailAddressCollectedFromExternalOidcIDPNotVerified - IDプロバイダーから確認済みのメール アドレスを取得できませんでした。 電子メール アドレスは、外部 OIDC ID プロバイダーからの ID トークンでは検証されません。 |
| AADSTS901014 | NoExternalIdentifierCollectedFromExternalOidcIDP - 外部識別子は、外部 OIDC ID プロバイダーからの ID トークンに存在しません。 |
| AADSTS650059 | アプリケーションがテナントで使用するように構成されていません。 アプリケーション プロパティ `AzureADMyOrg`に設定`signInAudience`値によって、テナントでの使用が制限されます。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/reference-microsoft-graph-app-manifest"} -->
## アプリ マニフェスト (Microsoft Graph 形式)を理解する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-microsoft-graph-app-manifest
- Service: identity-platform
- Article date: 2025-02-03
- Summary: Microsoft Entra テナントでのアプリケーションの ID 構成を表す Microsoft Entra アプリ マニフェスト (Microsoft Graph 形式) について説明します。

アプリケーション マニフェストには、Microsoft ID プラットフォームにおけるアプリ登録のすべての属性とその値が含まれます。

Microsoft Graph アプリ マニフェストは、アプリの登録を表す JSON オブジェクトです。 これは、 [Microsoft Graph アプリケーション リソースの種類または Microsoft](https://learn.microsoft.com/ja-jp/graph/api/resources/application) Graph アプリ オブジェクト (アプリケーション オブジェクト) とも呼ばれます。 これには、アプリ登録のすべての属性とその値が含まれます。

[Microsoft Graph Get Application メソッド](https://learn.microsoft.com/ja-jp/graph/api/application-get)を使用して受け取るアプリケーション オブジェクトは、**Microsoft Entra 管理センター**の[アプリ登録マニフェスト](https://entra.microsoft.com) ページに表示されるのと同じ JSON オブジェクトです。

注

個人の Microsoft アカウント(MSA アカウント)に登録されたアプリは、追って通知があるまで、Microsoft Entra 管理センターで Azure AD Graph 形式のアプリ マニフェストを引き続き参照します。 詳細については、「 [Microsoft Entra アプリ マニフェスト (Azure AD Graph 形式)」](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest)を参照してください。

### Microsoft Graph アプリ マニフェストを構成する

Microsoft Graph アプリ マニフェストをプログラムで構成する場合は、 [Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/api/resources/application) または [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/?view=graph-powershell-1.0&preserve-view=true) を使用できます。

Microsoft Entra 管理センターを使用してアプリ マニフェストを構成することもできます。 ほとんどの属性は、 **アプリ登録**の UI 要素を使用して構成できます。 ただし、一部の属性は、[ **マニフェスト** ] ページでアプリ マニフェストを直接編集して構成する必要があります。

#### Microsoft Entra 管理センターでアプリマニフェストを構成する

Microsoft Graph アプリ マニフェストを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)に、少なくとも[アプリケーション開発者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)としてサインインします。
2. **Entra ID**&gt;**App 登録に移動します**。
3. 構成するアプリを選択します。
4. アプリの **[概要** ] ページで、[ **マニフェスト** ] セクションを選択します。 Web ベースのマニフェスト エディターが開き、マニフェストを編集できます。 必要に応じて、[ **ダウンロード** ] を選択してマニフェストをローカルで編集し、[ **アップロード]** を使用してアプリケーションに再適用できます。

### マニフェスト リファレンス

ここでは、アプリケーション マニフェストで見られる属性について説明します。

#### id 属性

| 鍵 | 値の型 |
| --- | --- |
| 身分証明書 | 糸 |

このプロパティは、Microsoft Entra 管理センターではオブジェクト ID と呼ばれます。 これはディレクトリ内のアプリケーション オブジェクトの一意の識別子です。

この ID は、いずれかのプロトコル トランザクション内のアプリを識別するために使用される識別子ではありません。 これは、ディレクトリ クエリ内のオブジェクトを参照するために使用されます。

これは null 許容および読み取り専用の属性ではありません。

例:

```json
"id": "f7f9acfc-ae0c-4d6c-b489-0a81dc1652dd"
```

#### appId 属性

| 鍵 | 値の型 |
| --- | --- |
| アプリID | 糸 |

このプロパティは、Microsoft Entra 管理センターのアプリケーション (クライアント) ID と呼ばれます。 これはディレクトリ内のアプリケーション オブジェクトの一意の識別子です。

この ID は、いずれかのプロトコル トランザクション内のアプリを識別するために使用される識別子です。

これは null 許容および読み取り専用の属性ではありません。

例:

```json
"appId": "00001111-aaaa-2222-bbbb-3333cccc4444"
```

#### addIns 属性

| 鍵 | 値の型 |
| --- | --- |
| addIns | コレクション |

使用するサービスが特定のコンテキストでアプリの呼び出しに使用できるカスタム動作を定義します。 たとえば、ファイル ストリームをレンダリングできるアプリケーションでは、その "FileHandler" 機能の `addIns` プロパティを設定できます。 このパラメーターを使うと、Microsoft 365 などのサービスで、ユーザーが作業中のドキュメントのコンテキストでアプリケーションを呼び出すことができます。

例:

```json
"addIns": [
    {
        "id": "968A844F-7A47-430C-9163-07AE7C31D407",
        "type": " FileHandler",
        "properties": [
            {
                "key": "version",
                "value": "2"
            }
        ]
    }
]
```

#### appRoles

| 鍵 | 値の型 |
| --- | --- |
| appRoles | コレクション |

アプリが宣言する可能性のあるロールのコレクションを指定します。 これらのロールは、ユーザー、グループ、またはサービス プリンシパルに割り当てることができます。 詳細な例と情報については、「 [アプリケーションにアプリ ロールを追加し、トークンで受け取る」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)。

アプリ ロールと公開された委任されたアクセス許可スコープは、アプリケーションまたはサービス プリンシパルごとに既定の制限である 700 個のアクセス許可定義を共有します。 無効な定義もカウントされます。 制限を超える既存のオブジェクトのルールと動作のカウントについては、「 [アプリ ロール](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-role-limits) の制限」を参照してください。

例:

```json
"appRoles": [
    {
        "allowedMemberTypes": [
            "User"
        ],
        "description": "Read-only access to device information",
        "displayName": "Read Only",
        "id": "00001111-aaaa-2222-bbbb-3333cccc4444",
        "isEnabled": true,
        "value": "ReadOnly"
    }
]
```

#### groupMembershipClaims

| 鍵 | 値の型 |
| --- | --- |
| groupMembershipClaims | 糸 |

アプリが期待する、ユーザーまたは OAuth 2.0 アクセス トークンで発行される `groups` 要求を構成します。 この属性を設定するには、次のいずれかの有効な文字列値を使用します。

- `None`
- `SecurityGroup`(セキュリティ グループと Microsoft Entra ロールの場合)
- `ApplicationGroup` (このオプションには、アプリケーションに割り当てられているグループのみが含まれます)
- `DirectoryRole` (ユーザーがメンバーになっている Microsoft Entra ディレクトリ ロールを取得します)
- `All` (これは、サインイン ユーザーがメンバーになっているすべてのセキュリティ グループ、配布グループ、Microsoft Entra ディレクトリ ロールを取得します)。

例:

```json
"groupMembershipClaims": "SecurityGroup"
```

#### optionalClaims 属性

| 鍵 | 値の型 |
| --- | --- |
| optionalClaims | 糸 |

この特定のアプリのセキュリティ トークン サービスによってトークンで返される省略可能な要求。

個人アカウントと Microsoft Entra ID の両方をサポートするアプリでは、省略可能な要求を使用できません。 ただし、v2.0 エンドポイントを使用して Microsoft Entra ID のみに登録されたアプリは、マニフェストで要求した省略可能な要求を取得することができます。 詳細については、「 [省略可能な要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)」を参照してください。

例:

```json
"optionalClaims": {
    "idToken": [
        {
            "@odata.type": "microsoft.graph.optionalClaim"
        }
    ],
    "accessToken": [
        {
            "@odata.type": "microsoft.graph.optionalClaim"
        }
    ],
    "saml2Token": [
        {
            "@odata.type": "microsoft.graph.optionalClaim"
        }
    ]
}
```

#### identifierUris 属性

| 鍵 | 値の型 |
| --- | --- |
| identifierUris | 文字列配列 |

Microsoft Entra テナントまたは検証済みの顧客所有ドメイン内で Web アプリを一意に識別するユーザー定義 URI。 アプリケーションをリソース アプリとして使用する場合、リソースを一意に識別してアクセスするために、identifierUri 値が使用されます。

次の API および HTTP スキームベースのアプリケーション ID URI 形式がサポートされます。 表の後の一覧の説明に従って、プレースホルダーの値を置き換えてください。

| サポートされるアプリケーション IDURI 形式 | アプリ ID URI の例 |
| --- | --- |
| *api://&lt;appId&gt;* | *api://aaaabbbb-0000-cccc-1111-dddd2222eeee* |
| *api://&lt;tenantId&gt;/&lt;appId&gt;* | *api://aaaabbbb-0000-cccc-1111-dddd2222eeee/00001111-aaaa-2222-bbbb-3333cccc4444* |
| *api://&lt;tenantId&gt;/&lt;string&gt;* | *api://aaaabbbb-0000-cccc-1111-dddd2222eeee/api* |
| *api://&lt;string&gt;/&lt;appId&gt;* | *api://productapi/00001111-aaaa-2222-bbbb-3333cccc4444* |
| *https://&lt;tenantInitialDomain&gt;.onmicrosoft.com/&lt;string&gt;* | *`https://contoso.onmicrosoft.com/productsapi`* |
| *https://&lt;verifiedCustomDomain&gt;/&lt;string&gt;* | *`https://contoso.com/productsapi`* |
| *https://&lt;string&gt;.&lt;verifiedCustomDomain&gt;* | *`https://product.contoso.com`* |
| *https://&lt;string&gt;.&lt;verifiedCustomDomain&gt;/&lt;string&gt;* | *`https://product.contoso.com/productsapi`* |
| *api://&lt;string&gt;.&lt;verifiedCustomDomainOrInitialDomain&gt;/&lt;string&gt;* | *`api://contoso.com/productsapi`* |

- * &lt;appId&gt;* - アプリケーション オブジェクトのアプリケーション識別子 (appId) プロパティ。
- * &lt;string&gt;* - ホストまたは API パス セグメントの文字列値。
- * &lt;tenantId&gt;* - Azure 内のテナントを表すために Azure によって生成された GUID。
- * &lt;tenantInitialDomain&gt;* - *&lt;tenantInitialDomain&gt;.onmicrosoft.com*。*ここで、&lt;tenantInitialDomain&gt; はテナント*の作成時にテナント作成者が指定した初期ドメイン名です。
- * &lt;verifiedCustomDomain&gt;* - Microsoft Entra テナント用に構成 [された検証済みのカスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain) 。

注

*api://* スキームを使用する場合は、"api://" の直後に文字列値を追加します。 たとえば、 *api://&lt;string&gt;*。 その文字列値には、GUID または任意の文字列を指定できます。 GUID 値を追加する場合は、アプリ ID またはテナント ID と一致する必要があります。 文字列値を使用する場合は、テナントの検証済みのカスタム ドメインまたは初期ドメインを使用する必要があります。 *api://&lt;appId&gt;* を使用することをお勧めします。

重要

アプリケーション ID URI の値は、スラッシュ "/" 文字で終わる必要があります。

重要

アプリケーション ID URI の値は、テナント内で一意である必要があります。

例:

```json
"identifierUris": "https://contoso.onmicrosoft.com/fc4d2d73-d05a-4a9b-85a8-4f2b3a5f38ed"
```

#### keyCredentials 属性

| 鍵 | 値の型 |
| --- | --- |
| keyCredentials | コレクション |

アプリで割り当てられた資格情報、文字列ベースの共有シークレット、および X.509 証明書への参照を保持します。 これらの資格情報は、アクセス トークンを要求するときに使用されます (そのアプリがリソースとしてではなく、クライアントとして機能している場合)。

例:

```json
"keyCredentials": [
    {
        "customKeyIdentifier": null,
        "endDateTime": "2018-09-13T00:00:00Z",
        "keyId": "<guid>",
        "startDateTime": "2017-09-12T00:00:00Z",
        "type": "AsymmetricX509Cert",
        "usage": "Verify",
        "value": null
    }
]
```

#### displayName 属性

| 鍵 | 値の型 |
| --- | --- |
| ディスプレイ名 | 糸 |

アプリの表示名。

例:

```json
"displayName": "MyRegisteredApp"
```

#### oauth2RequiredPostResponse 属性

| 鍵 | 値の型 |
| --- | --- |
| oauth2RequiredPostResponse | ブール値 |

OAuth 2.0 トークン要求の一部として、Microsoft Entra ID が GET 要求ではなく、POST 要求を許可するかどうかを指定します。 既定値は false です。これは、GET 要求のみが許可されることを指定します。

例:

```json
"oauth2RequirePostResponse": false
```

#### parentalControlSettings 属性

| 鍵 | 値の型 |
| --- | --- |
| parentalControlSettings | 糸 |

- `countriesBlockedForMinors` は、未成年者に関してアプリがブロックされる国/地域を指定します。
- `legalAgeGroupRule` は、アプリのユーザーに適用される法的年齢グループ ルールを指定します。 `Allow`、`RequireConsentForPrivacyServices`、`RequireConsentForMinors`、`RequireConsentForKids`、`BlockMinors` のいずれかに設定できます。

例:

```json
"parentalControlSettings": {
    "countriesBlockedForMinors": [],
    "legalAgeGroupRule": "Allow"
}
```

#### passwordCredentials 属性

| 鍵 | 値の型 |
| --- | --- |
| passwordCredentials | コレクション |

`keyCredentials` プロパティの説明を参照してください。

例:

```json
"passwordCredentials": [
    {
        "customKeyIdentifier": null,
        "displayName": "Generated by App Service",
        "endDateTime": "2022-10-19T17:59:59.6521653Z",
        "hint": "Nsn",
        "keyId": "<guid>",
        "secretText": null,
        "startDateTime": "2022-10-19T17:59:59.6521653Z"
    }
]
```

#### publisherDomain 属性

| 鍵 | 値の型 |
| --- | --- |
| publisherDomain | 糸 |

アプリケーションの確認された発行元のドメイン。 読み取り専用です。 アプリ登録の発行元ドメインを編集するには、「アプリの [発行元ドメインを構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-publisher-domain)」に記載されている手順に従ってください。

例:

```json
"publisherDomain": "{tenant}.onmicrosoft.com"
```

#### requiredResourceAccess 属性

| 鍵 | 値の型 |
| --- | --- |
| requiredResourceAccess | コレクション |

動的な同意では、静的な同意を使用しているユーザーに対する管理者の同意エクスペリエンスおよびユーザーの同意エクスペリエンスが `requiredResourceAccess` によって作動します。 ただし、このパラメーターを使用しても、一般的な場合のユーザーの同意エクスペリエンスは促進されません。

- `resourceAppId` は、アプリがアクセスする必要があるリソースの一意識別子です。 この値は、ターゲット リソース アプリで宣言された appId に等しくなるようにしてください。
- `resourceAccess` は、指定されたリソースに対してアプリが必要とする OAuth2.0 アクセス許可スコープとアプリ ロールを格納する配列です。 指定されたリソースの `id` と `type` の値が格納されます。

例:

```json
"requiredResourceAccess": [
    {
        "resourceAppId": "00000002-0000-0000-c000-000000000000",
        "resourceAccess": [
            {
                "id": "311a71cc-e848-46a1-bdf8-97ff7156d8e6",
                "type": "Scope"
            }
        ]
    }
]
```

#### samlMetadataUrl 属性

| 鍵 | 値の型 |
| --- | --- |
| samlMetadataUrl | 糸 |

アプリの SAML メタデータへの URL。

例:

```json
"samlMetadataUrl": "https://MyRegisteredAppSAMLMetadata"
```

#### signInAudience 属性

| 鍵 | 値の型 |
| --- | --- |
| signInAudience | 糸 |

現在のアプリケーションでサポートされる Microsoft アカウントを指定します。 サポートされる値は次のとおりです。

- `AzureADMyOrg` - 自分の組織の Microsoft Entra テナント (たとえば、シングル テナント) に、Microsoft の職場アカウントまたは学校アカウントを持つユーザー
- `AzureADMultipleOrgs` - 任意の組織の Microsoft Entra テナント (たとえば、マルチテナント) に、Microsoft の職場アカウントまたは学校アカウントを持つユーザー
- `AzureADandPersonalMicrosoftAccount` - 個人用の Microsoft アカウント、または任意の組織の Microsoft Entra テナントに職場アカウントまたは学校アカウントを持つユーザー
- `PersonalMicrosoftAccount` - Xbox や Skype などのサービスのサインインに使用する個人用アカウント。

例:

```json
"signInAudience": "AzureADandPersonalMicrosoftAccount"
```

#### tags 属性

| 鍵 | 値の型 |
| --- | --- |
| タグ | 文字列配列 |

アプリケーションの分類と特定に使用できるカスタム文字列。

個々のタグは、1 ~ 256 文字 (両端を含む) である必要があります。 空白や重複するタグは使用できません。 一般的なマニフェスト サイズの制限に従って、追加できるタグの数に特定の制限はありません。

例:

```json
"tags": [
    "ProductionApp"
]
```

#### isFallbackPublicClient 属性

| 鍵 | 値の型 |
| --- | --- |
| isFallbackPublicClient | ブール値 |

モバイル デバイスで実行されているインストール済みアプリケーションなど、フォールバック アプリケーション タイプをパブリック クライアントとして指定します。 デフォルト値は false で、フォールバック アプリケーション タイプが Web アプリなどの機密クライアントであることを示します Microsoft Entra ID がクライアント アプリケーションの種類を判断できない特定のシナリオがあります。 たとえば、ROPC フローでは、リダイレクト URI を指定せずに構成されます。 そのような場合、Microsoft Entra ID では、このプロパティの値に基づいて、アプリケーションの種類を解釈します。

例:

```json
"isFallbackPublicClient": "false"
```

#### info 属性

| 鍵 | 値の型 |
| --- | --- |
| 情報 | [informationalUrl](https://learn.microsoft.com/ja-jp/graph/api/resources/informationalurl) |

アプリのマーケティング、サポート、サービス条件、プライバシーに関する声明、ロゴ URL など、アプリケーションの基本的なプロファイル情報を指定します。

以下の点に注意してください。

- "logoUrl" は読み取り専用プロパティです。 アプリ マニフェストでは編集できません。 目的のアプリ登録の [ブランドとプロパティ] ページに移動し、[新しいロゴのアップロード] を使用して新しいロゴをアップロードします。
- サービス利用規約とプライバシーに関する声明は、ユーザーの同意エクスペリエンスからユーザーに提示されます。 詳細については、「方法: [登録済みの Microsoft Entra アプリのサービス利用規約とプライバシーに関する声明を追加する](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/howto-add-terms-of-service-privacy-statement)」を参照してください。

例:

```json
"info": {
    "termsOfService": "https://MyRegisteredApp/termsofservice",
    "support": "https://MyRegisteredApp/support",
    "privacy": "https://MyRegisteredApp/privacystatement",
    "marketing": "https://MyRegisteredApp/marketing",
    "logoUrl": "https://MyRegisteredApp/logoUrl",
}
```

#### api の属性

| 鍵 | 値の型 |
| --- | --- |
| API (エーピーアイ) | [apiApplication リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/apiapplication) |

Web API を実装するアプリケーションの設定を指定します。 これには、次の 5 つのプロパティが含まれます。

| プロパティ | タイプ | 説明 |
| --- | --- | --- |
| acceptMappedClaims | ブール値 | true に設定すると、アプリケーションはカスタム署名キーを指定せずに要求マッピングを使用できます トークンを受信するアプリケーションは、クレーム値が Microsoft Entra ID によって正式に発行され、改ざんできないという事実に依存しています。 ただし、クレームマッピング ポリシーによってトークンの内容を変更すると、この前提は通用しなくなります。 悪意のある目的で作成されたクレームマッピング ポリシーからアプリケーションを保護するため、アプリケーションでは、クレームマッピング ポリシーの作成者がトークンを変更したことを、明示的に確認する必要があります。 警告: マルチテナント アプリでは acceptMappedClaims プロパティを true に設定しないでください。悪意のある者がアプリのクレームマッピング ポリシーを作成することを許可してしまう場合があります。 |
| knownClientApplications | コレクション | クライアント アプリとカスタム Web API アプリの 2 つの部分を含むソリューションがある場合に、同意をバンドルするために使用されます。 この値にクライアント アプリの appID を設定すると、ユーザーは、クライアント アプリに 1 回同意するだけで済みます。 Microsoft Entra ID は、クライアントへの同意が Web API への暗黙的な同意を示し、API の両方のサービス プリンシパルを同時に自動的にプロビジョニングすることを認識します。 クライアントと Web API アプリの両方が同じテナントに登録されている必要があります。 |
| oauth2PermissionScopes | permissionScope コレクション | このアプリケーション登録で提供される Web API によって公開される、委任されたアクセス許可の定義です これらの委任されたアクセス許可は、クライアント アプリケーションによって要求される場合があり、同意時にユーザーまたは管理者によって付与される場合があります。 委任されたアクセス許可は、OAuth 2.0 スコープと呼ばれることもあります。 |
| preAuthorizedApplications | preAuthorizedApplication コレクション | このアプリケーションの API へのアクセスが、委任された特定のアクセス許可で事前に承認されているクライアント アプリケーションの一覧を表示します ユーザーは、(指定されたアクセス許可に対して) 事前認証されたアプリケーションに同意する必要はありません。 ただし、preAuthorizedApplications に記載されていないその他のアクセス許可 (増分同意などを通じて要求) には、ユーザーの同意が必要です。 |
| requestedAccessTokenVersion | Int32 | このリソースで予期されるアクセス トークンのバージョンを指定します。 これにより、アクセス トークンを要求するために使用されたエンドポイントまたはクライアントとは関係なく、生成される JWT のバージョンと形式が変更されます。 使用されるエンドポイント (v1.0 または v2.0) はクライアントによって選択され、id\_token のバージョンにのみ影響します。 リソースでは、サポートされているアクセス トークン形式を示すように *requestedAccessTokenVersion* を明示的に構成する必要があります。 *requestedAccessTokenVersion* に指定できる値は、1、2、または null です。 値が null の場合の既定値は 1 で、v1.0 のエンドポイントに対応します。 アプリケーションの **signInAudience** が AzureADandPersonalMicrosoftAccount または PersonalMicrosoftAccount として構成されている場合、このプロパティの値は 2 である必要があります。 |

例:

```json
"api": {
    "acceptMappedClaims": true,
    "knownClientApplications": [
        "f7f9acfc-ae0c-4d6c-b489-0a81dc1652dd"
    ],
    "oauth2PermissionScopes": [
        {
            "adminConsentDescription": "Allow the app to access resources on behalf of the signed-in user.",
            "adminConsentDisplayName": "Access resource1",
            "id": "<guid>",
            "isEnabled": true,
            "type": "User",
            "userConsentDescription": "Allow the app to access resource1 on your behalf.",
            "userConsentDisplayName": "Access resources",
            "value": "user_impersonation"
        }
    ],
    "preAuthorizedApplications": [
        {
            "appId": "00001111-aaaa-2222-bbbb-3333cccc4444",
            "permissionIds": [
                "8748f7db-21fe-4c83-8ab5-53033933c8f1"
            ]
        }
    ],

    "requestedAccessTokenVersion": 2
}
```

#### web 属性

| 鍵 | 値の型 |
| --- | --- |
| Web | [webApplication リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/webapplication) |

Web アプリケーションの設定を指定します。 これには 4 つのプロパティが含まれます。

| プロパティ | タイプ | 説明 |
| --- | --- | --- |
| homePageUrl | 糸 | アプリケーションのホーム ページまたはランディング ページ |
| implicitGrantSettings | [implicitGrantSettings](https://learn.microsoft.com/ja-jp/graph/api/resources/implicitgrantsettings) | この Web アプリケーションが OAuth 2.0 暗黙的フローを使用してトークンを要求できるかどうかを指定します |
| logoutUrl | 糸 | Microsoft の承認サービスが [、フロント チャネル](https://openid.net/specs/openid-connect-frontchannel-1_0.html)、 [バック](https://openid.net/specs/openid-connect-backchannel-1_0.html) チャネル、または SAML サインアウト プロトコルを使用してユーザーをサインアウトするために使用する URL を指定します。 |
| redirectUris | 文字列コレクション | サインインに必要なユーザー トークンの送信先である URL、または Web プラットフォームで OAuth 2.0 の認証コードとアクセス トークンの送信先であるリダイレクト URL を指定しますサ。 |

例:

```json
"web": {
    "homePageUrl": "String",
    "implicitGrantSettings": {
        "enableIdTokenIssuance": "Boolean",
        "enableAccessTokenIssuance": "Boolean"
    },
    "logoutUrl": "String",
    "redirectUris": [
        "String"
    ]
}
```

#### spa 属性

| 鍵 | 値の型 |
| --- | --- |
| SPA | [spaApplication リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/spaapplication) |

承認コードとアクセス トークンのサインアウト URL やリダイレクト URI など、シングルページ アプリケーションの設定を指定します。

| プロパティ | タイプ | 説明 |
| --- | --- | --- |
| redirectUris | 文字列コレクション | サインインに必要なユーザー トークンの送信先である URL、または OAuth 2.0 の認証コードとアクセス トークンの送信先であるリダイレクト URL を指定します。 |

例:

```json
"spa": {
    "redirectUris": [
        "String"
    ]
}
```

#### publicClient 属性

| 鍵 | 値の型 |
| --- | --- |
| publicClient | [publicClientApplication リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/publicclientapplication) |

web 以外のアプリまたは非 Web API の設定を指定します (たとえば、iOS、Android、モバイル、またはデスクトップ デバイスで実行されているインストール済みアプリケーションなどの他のパブリック クライアント)。

| プロパティ | タイプ | 説明 |
| --- | --- | --- |
| redirectUris | 文字列コレクション | サインインに必要なユーザー トークンの送信先である URL、または OAuth 2.0 の認証コードとアクセス トークンの送信先であるリダイレクト URL を指定します。 |

例:

```json
"publicClient": {
    "redirectUris": [
        "String"
    ]
}
```

### 一般的な問題

#### マニフェストの制限

個々のコレクションにも制限があります。 アプリ ロールと公開された委任されたアクセス許可スコープは、集計マニフェストの制限とは別に、既定の制限である 700 個のアクセス許可定義を共有します。 「 [アプリ ロールの制限」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-role-limits)参照してください。

アプリケーション マニフェストには、appRoles、keyCredentials、knownClientApplications、identifierUris、redirectUris、requiredResourceAccess、oauth2PermissionScopes など複数の属性があり、コレクションと呼ばれています。 任意のアプリケーションの完全なアプリケーション マニフェスト内では、すべてのコレクションを組み合わせたときのエントリの合計数は 1200 個に制限されています。 アプリケーション マニフェストで 100 個のappRoles を以前に指定した場合、マニフェストを構成する他の組み合わされたコレクション全体で使用できるエントリ数は残りの 1,100 個です。

注

アプリケーション マニフェストに 1,200 を超えるエントリを追加しようとすると、 **"アプリケーション xxxxxx の更新に失敗しました。エラーの詳細: マニフェストのサイズが制限を超えています。値の数を減らして、要求を再試行してください。**

#### Azure AD Graph 形式から Microsoft Graph 形式へのマニフェスト移行のトラブルシューティング

以前にダウンロードしたアプリ マニフェストを Azure AD Graph 形式でアップロードすると、次のエラーが発生することがあります。

##### {app name} アプリケーションを更新できませんでした。 エラーの詳細: 無効なプロパティ '{property name}'。\*\*

これは、Azure AD Graph から Microsoft Graph アプリ マニフェストへの移行が原因である可能性があります。 まず、アプリ マニフェストが [Azure AD Graph 形式](https://learn.microsoft.com/ja-jp/entra/identity-platform/azure-active-directory-graph-app-manifest-deprecation#how-do-i-tell-the-format-of-my-app-manifest)であるかどうかを確認する必要があります。 その場合は、 [アプリ マニフェストを Microsoft Graph 形式に変換する](https://learn.microsoft.com/ja-jp/entra/identity-platform/azure-active-directory-graph-app-manifest-deprecation#convert-an-app-manifest-in-azure-ad-graph-format-to-microsoft-graph-format)必要があります。

trustedCertificateSubjects 属性が見つからない

これは Microsoft 内部プロパティです。 ポータルには、v1.0 バージョンの MS Graph アプリ マニフェストが表示されますが、このプロパティは MS Graph アプリ マニフェストのベータ 版にのみ存在します。 Entra ポータルで Azure AD Graph アプリ マニフェストを使用して、このプロパティの編集を続けてください。 Azure AD Graph アプリ マニフェストを非推奨にする前に、Entra ポータルで MS Graph アプリ マニフェストのベータ版を公開します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/reference-msa-server-side-api"} -->
## Microsoft アカウント (MSA) サーバー側 API リファレンス - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-msa-server-side-api
- Service: identity-platform
- Article date: 2024-03-19
- Summary: Microsoft Authentication Library (MSAL) と Microsoft アカウント (MSA) サーバー側 API の理解と使用のための包括的なガイド。

Microsoft アカウント (MSA) は、ユーザーが単一の資格情報セットを使用して Web サイト、アプリケーション、サービスにログインできるようにするシングル サインオン Web サービスです。 Microsoft アカウント (MSA) サーバー側 API には、認証プロセスをカスタマイズして、ユーザー エクスペリエンスを強化するために使用できるさまざまなパラメータが用意されています。

これらのパラメータは、Windows プラットフォーム上の MSAL で使用できるため、Microsoft アカウントを介してサインインするユーザーとシームレスに統合する Windows アプリケーションの開発が容易になります。

### MSA サーバー側 API

これらは、`login.microsoftonline.com` という形式で、URL パラメータとして `login.live.com` または `login.microsoftonline.com?parametername=value` に渡されます。

| パラメーター | 値 | メモ |
| --- | --- | --- |
| `ImplicitAssociate` | 0/1 | 1 に設定すると、ユーザーがサインインに使用する Microsoft アカウント (MSA) は、ユーザーの確認を必要とせずに、Windows 上のアプリケーションに自動的にリンクされます。 **注:** これにより、ユーザーのシングル サインオン (SSO) は有効になりません。 |
| `LoginHint` | ユーザー名 | 既に指定されている (たとえば、更新トークンを使用して) 場合を除き、サインイン時にユーザー名を事前入力します。 ユーザーは、必要に応じて、この事前入力されたユーザー名を変更できます。 |
| `cobrandid` | コブランディング GUID | このパラメータは、サインイン ユーザー エクスペリエンスにコブランディングを適用します。 コブランディングを使用すると、アプリのロゴ、背景画像、サブタイトル テキスト、説明テキスト、ボタンの色などの要素をカスタマイズできます。 アプリケーションを Microsoft アカウントのサインインと統合しており、サインイン スクリーンをカスタマイズする場合は、Microsoft サポートにお問い合わせください。 |
| `client_flight` | 糸 | `client_flight` は、サインイン プロセスの完了時にアプリケーションに返されるパススルー パラメータです。 このパラメータの値はテレメトリ ストリームにログされ、認証要求を関連付けるためにアプリケーションによって使用されます。 これは、すべてのアプリケーションがこのテレメトリ ストリームにアクセスできるわけではない場合でも、サインイン要求の関連付けに役立ちます。 Office Union や Teams などのアプリケーションは、このパラメータの代表的なユーザーです。 |
| `lw` | 0/1 | **注: この機能は非推奨です。** ライトウェイト サインアップを有効にします。 有効にした場合、ユーザーの地域に適用される法律で義務付けられている場合を除き、認証フローを通じてサインアップするユーザーは、名、姓、国/地域、生年月日を入力する必要はありません。 |
| `fl` | `phone2`,`email`,`wld`,`wld2`,`easi`,`easi2` | **注: この機能は非推奨です。** このパラメータは、サインアップ プロセス中に指定されるユーザー名オプションを制御します:`phone` – ユーザー名を電話番号に制限します、`phone2` – 既定値は電話番号に設定されていますが、他のオプションを使用できます、`email` – ユーザー名をメール (Outlook または EASI) に制限します、`wld` – ユーザー名を Outlook に制限します、–`wld2` 既定値は Outlook ですが、電話など、その他のオプションを使用できます、`easi` – ユーザー名を EASI に制限します、–`easi2` 既定値は EASI ですが、電話など、その他のオプションを使用できます。 |
| `nopa` | 0/1/2 | **注: この機能は非推奨です。** パスワードレス サインアップを有効にします。 値 1 を指定すると、パスワードなしでサインアップできますが、30 日後にパスワードの作成が強制されます。 値 2 を指定すると、パスワードなしで無期限にサインアップできます。 値 2 を使用するには、手動プロセスを使用してアプリを許可リストに追加する必要があります。 |
| `coa` | 0/1 | **注: この機能は非推奨です。** ユーザーの電話番号にコードを送信して、パスワードレス サインインを有効にします。 値 1 を使用するには、手動プロセスを使用してアプリを許可リストに追加する必要があります。 |
| `signup` | 0/1 | [サインイン] ページではなく、[アカウントの作成] ページ内で認証フローを開始します。 |
| `fluent` | 0/1 | **注: この機能は非推奨です。** サインイン フローで新しい *Fluent* ルック アンド フィールを有効にします。 値 1 を使用するには、手動プロセスを使用してアプリを許可リストに追加する必要があります。 |
| `api-version` | “”/”2.0” | `2.0` に設定した場合、このパラメータを使用すると、指定されたアプリ ID がこれを許可するように構成されている場合、clientid は Windows 呼び出しアプリによって登録されたものとは異なるアプリ ID を指定できます。 |
| `Clientid/client_id` | `app ID` | 認証フローを呼び出すアプリのアプリ ID。 |
| `Client_uiflow` | `new_account` | [AccountsSettingsPane](https://learn.microsoft.com/ja-jp/uwp/api/windows.ui.applicationsettings.accountssettingspane) を呼び出さずに、アプリで新しいアカウントを追加できるようにします。 ForceAuthentication プロンプトの種類も渡す必要があります。 [ForceAuthentication](https://learn.microsoft.com/ja-jp/uwp/api/windows.security.authentication.web.core.webtokenrequestprompttype) プロンプトの種類も渡す必要があります。 |

### MSA 承認トークン パラメータ

| パラメーター | 値 | メモ |
| --- | --- | --- |
| `scope` | 糸 | 承認およびトークン エンドポイントでクライアントによって要求されるアクセス許可の範囲を定義します。 |
| `claims` | 糸 | クライアント アプリケーションによって要求される省略可能な要求を指定します。 |
| `response_type` | 糸 | 承認プロセス フローを指定する OAuth 2.0 応答の種類の値を指定します。これには、それぞれのエンドポイントから返されるパラメータも含まれます。 |
| `phone` | 糸 | ホスト デバイスにリンクされている書式設定された電話番号のコンマ区切りの一覧を指定します。 |
| `qrcode_uri` | 糸 | 認証されたセッションを転送するための QR コード生成の要求では、この URL は QR コードに埋め込まれます。 |
| `Ttv` | 1/2 | 未定 |
| `qrcode_state` | 糸 | QR コード作成要求では、この状態パラメータは、QR コード内に表示される URL に組み込まれます。 |
| `Child_client_id` | 糸 | 二重ブローカー フロー内の子クライアント アプリ ID。 |
| `child_redirect_uri` | 糸 | 二重ブローカー フローで使用される子クライアント アプリのリダイレクト URI。 |
| `safe_rollout` | 糸 | 要求に適用される安全なロールアウト計画を指定するために使用されるパラメータ。 アプリの所有者がアプリ構成の変更をロールアウトしていて、安全な展開のために変更を徐々に適用したい場合に便利です。 |
| `additional_scope` | 糸 | 認証サービスが 1 つの要求で追加の同意を収集できるように、追加のスコープを指定します。 これにより、クライアント アプリは、今後別のスコープを要求するときに、同意の中断を回避できます。 |
| `x-client-info` | I/O | 値が 1.0 の場合、Client\_info トークンが返されます。 |
| `challenge` | 糸 | 最初はリソース アプリによって提供され、変更されることなくクライアント アプリによって MSA サーバーに転送されます。 |
| `max_age` | 整数型 | 最大認証期間。 エンド ユーザーが最後に MSA によってアクティブに認証されてから許容される経過時間を秒単位で指定します。 |
| `mfa_max_age` | 整数型 | ユーザーが MSA で多要素認証を最後に通過してから許容される経過時間を秒単位で指定します。 |
| `acr_values` | 糸 | 要求された認証コンテキスト クラス参照の値。 承認サーバーがこの認証要求の処理に使用するように要求されている acr 値を指定するスペース区切りの文字列。値は優先度順に表示されます。 実行された認証によって満たされた認証コンテキスト クラスが、acr 要求値として返されます。 |
| `redirect_uri` | 糸 | 応答が送信されるリダイレクト URI。 この URI は、MSA に事前登録されているクライアントのリダイレクト URI 値の 1 つと完全に一致する必要があります。 |
| `state` | 糸 | 要求とコールバックの間の状態を維持するために使用される不透明な値。 |
| `oauth2_response` | 1 | 1 に等しい場合、ws-trust 応答が OAuth 応答形式に従う必要があることを意味します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/reference-native-authentication-api"} -->
## ネイティブ認証 API リファレンスのドキュメント。 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api
- Service: identity-platform / external
- Article date: 2026-02-27
- Summary: ネイティブ認証 API を使用して、外部テナントのある顧客向けアプリに対してユーザーを認証する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entraの [native authentication](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication) を使用すると、ブラウザーに認証を委任する代わりに、クライアント アプリケーションでアプリのユーザー インターフェイスをホストできるため、ネイティブに統合された認証エクスペリエンスが得られます。 開発者は、サインイン インターフェイスの外観を完全に制御できます。

この API リファレンス記事では、フローを実行するために生の HTTP 要求を手動で行うときにのみ必要な詳細について説明します。 ただし、この方法は推奨しません。 そのため、可能なときは、Microsoft が構築し、サポートしている認証 SDK を使用します。 [ネイティブ認証 SDK](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication) の詳細を確認します。 API エンドポイントの呼び出しが成功すると、ユーザーを識別するための [ID トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens) と、保護された API を呼び出すための [アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens) の両方を受け取ります。 API からの応答はすべて JSON 形式です。

Microsoft Entraのネイティブ認証 API では、次の 2 つの認証フローのサインアップとサインインがサポートされています。

- メールとパスワード。メールとパスワードによるサインアップとサインイン、およびセルフサービス パスワード リセット (SSPR) をサポートします。

    - メールアドレスとパスワードでサインインしたユーザーは、 [ユーザー名とパスワードでサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias)することも可能です。
- メールのワンタイム パスコード。メールのワンタイム パスコードを使用したサインアップとサインインをサポートします。

注

現在、ネイティブ認証 API エンドポイントは、[クロスオリジン リソース共有 (CORS)](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS) をサポートしていません。

### 前提条件

1. Microsoft Entra外部テナント。 外部テナントをまだ作成していない場合は、[ここで作成してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal)。
2. まだ行っていない場合は、[Microsoft Entra管理センターでアプリケーションを登録します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。 次のことを確認します。

    - 後で使用するために、 **アプリケーション (クライアント) ID** と **ディレクトリ (テナント) ID を** 記録します。
    - アプリケーション[に管理者の同意を付与](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#grant-admin-consent-external-tenants-only)します。
    - [パブリック クライアントとネイティブ認証フローを有効にします](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-android-sign-in#enable-public-client-and-native-authentication-flows)。
3. まだ行っていない場合は、[Microsoft Entra管理センターでユーザー フローを作成します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers#to-add-a-new-user-flow)。 ユーザー フローを作成するときは、必要に応じて構成したユーザー属性を書き留めます。これらの属性は、アプリの送信Microsoft Entra想定される属性です。
4. [アプリの登録をユーザー フローに関連付けます](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)。
5. サインインフローでは、 [顧客ユーザーを登録](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-manage-customer-accounts#create-a-customer-account)し、それを使ってフローをテストします。 または、サインアップ フローを実行した後に、このテスト ユーザーを取得できます。
6. SSPR フローの場合は、顧客テナント内の顧客ユーザーに対して [セルフサービス パスワード リセットを有効](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-enable-password-reset-customers) にします。 SSPR は、パスワードの認証方式のメールを使用する顧客ユーザーが利用できます。
7. メールアドレスとパスワードでサインインしたユーザーもユーザー名とパスワードでサインインできるようにしたい場合は、「 [別名またはユーザー名でサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias) 」記事の手順をご利用ください。

    1. [サインインでユーザー名を有効にします](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#enable-username-in-sign-in-identifier-policy)。
    2. [管理センターでユーザー名を持つユーザーを作成するか、ユーザー名](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username) を [追加して既存のユーザーを更新します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-sign-in-alias#create-and-update-users-with-username)。 または、Microsoft Graph API を使用して、ユーザーの作成と更新を自動的に行うこともできます。
8. 顧客に多要素認証 (MFA) を適用するには、「 [アプリに多要素認証 (MFA) を追加して](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-multifactor-authentication-customers) サインイン フローに MFA を追加する」の手順を使用します。 ネイティブ認証では、MFA の 2 番目の要素として電子メール ワンタイム パスコードと SMS がサポートされます。

### 継続トークン

サインイン、サインアップ、または SSPR エンドポイントを呼び出すたびに、応答には *継続トークン*が含まれます。 このトークンは、現在のフローを一意に識別し、エンドポイント全体の状態Microsoft Entra ID維持できるようにします。 そのフロー内の後続のすべての要求にトークンを含めます。 これは限られた時間だけ有効であり、同じフロー内の後続の要求にのみ使用できます。

### サインアップのためのAPI参照

いずれかの認証方法でユーザーのサインアップ フローを完了するために、アプリでは、`/signup/v1.0/start`、`/signup/v1.0/challenge`、`/signup/v1.0/continue`、`/token` の 4 つのエンドポイントと対話します。

#### サインアップ用のAPIエンドポイント

| エンドポイント | 説明 |
| --- | --- |
| `/signup/v1.0/start` | このエンドポイントは、サインアップ フローを開始します。 有効なアプリケーション ID、新しいユーザー名、チャレンジ型を渡すと、新しい継続トークンが返されます。 エンドポイントは、アプリケーションが選択した認証方法がMicrosoft Entraでサポートされていない場合に、Web 認証フローを使用するようにアプリケーションに指示する応答を返すことができます。 |
| `/signup/v1.0/challenge` | アプリは、Microsoft Entraでサポートされている challenge 型の一覧を使用して、このエンドポイントを呼び出します。 Microsoft Entra、ユーザーが認証を行うためにサポートされている認証方法の 1 つを選択します。 |
| `/signup/v1.0/continue` | このエンドポイントは、パスワード ポリシーの要件や不適切な属性形式などの要件がないため、フローを続行してユーザー アカウントを作成したり、フローを中断したりするのに役立ちます。 このエンドポイントは継続トークンを生成し、アプリに返します。 エンドポイントは、アプリケーションがMicrosoft Entraによって選択された認証方法ではない場合に、Web ベースの認証フローを使用することをアプリケーションに示す応答を返すことができます。 |
| `oauth/v2.0/token` | アプリケーションはこのエンドポイントを呼び出して、最終的にセキュリティ トークンを要求します。 アプリは、 `/signup/v1.0/continue` エンドポイントへの最後に成功した呼び出しから取得した継続トークンを使用する必要があります。 |

#### サインアップ チャレンジ型

この API を使用すると、クライアント アプリは、Microsoft Entraの呼び出しを行うときに、サポートする認証方法をアドバタイズできます。 これを行うために、アプリではアプリの要求に `challenge_type` パラメーターを使用します。 このパラメーターには、さまざまな認証方法を表す定義済みの値が保持されます。

チャレンジ型の詳細については、「[ネイティブ認証のチャレンジ型](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication-challenge-types)」を参照してください。 この記事では、認証方法に使用するチャレンジの種類の値について説明します。

#### サインアップ フロー プロトコルの詳細

シーケンス図は、サインアップ プロセスのフローを示しています。

[Image: ネイティブ認証のサインアップ フローの図。]

この図は、アプリがユーザー名 (電子メール)、パスワード (パスワード認証フローを含む電子メールの場合)、およびユーザーからの属性を異なるタイミングで (場合によっては別の画面で) 収集することを示しています。 ただし、同じ画面でユーザー名 (電子メール)、パスワード、必要なすべての属性値、および省略可能な属性値を収集するようにアプリを設計し、それらのすべてを `/signup/v1.0/start` エンドポイントに送信できます。 アプリが必要なすべての情報を `/signup/v1.0/start` エンドポイントに送信する場合、アプリは呼び出しを行い、オプションの手順で応答を処理する必要はありません。

手順 21 では、ユーザーは既にサインアップしています。 ただし、アプリがサインアップ後にユーザーを自動的にサインインさせる必要がある場合、アプリは `oauth/v2.0/token` エンドポイントを呼び出してセキュリティ トークンを要求します。

#### ステップ 1: サインアップ フローの開始を要求する

サインアップ フローは、アプリケーションが `/signup/v1.0/start` エンドポイントに POST 要求を行ってサインアップ フローを開始することで始まります。

要求の例を次に示します (読みやすくするために、要求の例を複数行で示します)。

例 1:

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/signup/v1.0/start
Content-Type: application/x-www-form-urlencoded
client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&challenge_type=oob password redirect
&username=contoso-consumer@contoso.com 
```

例 2 (要求にユーザー属性とパスワードを含める):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/signup/v1.0/start
Content-Type: application/x-www-form-urlencoded
client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&challenge_type=oob password redirect
&password={secure_password}
&attributes={"displayName": "{given_name}", "extension_2588abcdwhtfeehjjeeqwertc_age": "{user_age}", "postalCode": "{user_postal_code}"}
&username=contoso-consumer@contoso.com 
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |
| `username` | はい | *contoso-consumer@contoso.com* など、サインアップさせる顧客ユーザーのメール。 |
| `challenge_type` | はい | `oob password redirect` など、アプリがサポートする認証チャレンジ型文字列のスペース区切りのリスト。 リストには、常に `redirect` チャレンジ型が含まれている必要があります。 この値は、メールとパスワードの認証方法では `oob redirect` または `oob password redirect` であると想定されます。 |
| `password` | いいえ | アプリが顧客ユーザーから収集するパスワード値。 ユーザーのパスワードは、`/signup/v1.0/start` エンドポイントの `/signup/v1.0/continue` 以降を使用して送信できます。 `{secure_password}` をアプリケーションが顧客ユーザーから収集するパスワードの値を置き換えます。 アプリの UI でパスワードの確認フィールドを指定して、ユーザーが使用するパスワードを認識していることを確認するのはユーザーの責任です。 また、組織のポリシーに従って、何が強力なパスワードであるかをユーザーに認識させる必要があります。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 **このパラメーターが適用されるのは、メールとパスワードの認証方法の場合のみです**。 |
| `attributes` | いいえ | アプリが顧客ユーザーから収取するユーザー属性値。 値は文字列ですが、キー値がユーザー属性の[プログラミング可能な名前](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes#built-in-user-attributes)である JSON オブジェクトとして書式設定されます。 これらの属性は、組み込みまたはカスタムにすることができ、必須または省略可能です。 オブジェクトのキー名は、管理者が管理センターで構成Microsoft Entra属性によって異なります。 一部またはすべてのユーザー属性は、`/signup/v1.0/start` エンドポイント経由で送信することも、後で `/signup/v1.0/continue` エンドポイントで送信することもできます。 `/signup/v1.0/start` エンドポイント経由ですべての必要な属性を送信する場合、`/signup/v1.0/continue` エンドポイントで属性を送信する必要はありません。 ただし、`/signup/v1.0/start` エンドポイント経由で一部の必要な属性を送信する場合は、後で `/signup/v1.0/continue` エンドポイントで残りの必須属性を送信できます。 `{given_name}`、`{user_age}`、`{postal_code}` は、アプリが顧客ユーザーから収集する名前、年齢、郵便番号の値にそれぞれ置き換えます。 **Microsoft Entraは、送信した属性 (存在しない属性**を無視します。 |
| `capabilities` | いいえ | クライアント アプリの機能を記述するスペース区切りのフラグ。 `challenge_type`はチャレンジできる方法を定義しますが、`capabilities`、クライアント アプリが処理できる追加フローと、ユーザーに表示できる UI をネイティブ認証 API に指示します。 たとえば、 `mfa_required` は別の `/challenge` と `/token` ループを意味します。 `registration_required` は、クライアント アプリが登録 API を呼び出し、登録 UI を表示します。 必要な機能がクライアント アプリによってアドバタイズされていない場合、API はリダイレクトを返します。 サポートされている値は、`mfa_required` と `registration_required`です。 [機能の詳細を確認します](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-challenge-types)。 |

##### 成功応答

アップロードの成功の例を次に示します:

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{
    "continuation_token": "AQABAAEAAA…",
} 
```

| 財産 | 説明 |
| --- | --- |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |

##### リダイレクト応答

クライアント アプリが、Microsoft Entra必要な認証方法または機能をサポートしていない場合は、Web ベースの認証フローへのフォールバックが必要です。 このシナリオでは、Microsoft Entraは応答で *redirect* チャレンジの種類を返すことによってアプリに通知します。

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{     
   "challenge_type": "redirect" 
} 
```

| 財産 | 説明 |
| --- | --- |
| `challenge_type` | Microsoft Entraはチャレンジの種類を持つ応答を返します。 このチャレンジ型の値は redirect です。これにより、アプリは Web ベースの認証フローを使用できます。 |

この応答は成功と見なされますが、アプリは Web ベースの認証フローに切り替える必要があります。 この場合は、[Microsoft が構築し、サポートされている認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用することをおすすめします。

##### エラー応答

例:

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{
    "error": "user_already_exists", 
    "error_description": "AADSTS1003037: It looks like you may already have an account.... .\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: yyyy-...", 
    "error_codes": [ 
        1003037 
    ],
    "timestamp": "yyyy-mm-dd 10:15:00Z",
    "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333", 
    "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd"
} 
```

| 財産 | 説明 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |
| `invalid_attributes` | 検証に失敗した属性の (オブジェクトの配列) のリスト。 この応答は、アプリがユーザー属性を送信し、 `suberror` プロパティの値が *attribute\_validation\_failed*場合に可能です。 |
| `suberror` | エラーの種類を詳細に分類するのに使用できるエラー コード文字列。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `invalid_request` | challenge\_type パラメーター値にサポートされていない認証方法が含まれている場合や、要求にクライアント ID 値が空または無効な `client_id` パラメーターが含まれていなかった場合に、要求パラメーターの検証に失敗しました。 `error_description` パラメーターを使用して、エラーの正確な原因を確認します。 |
| `invalid_client` | アプリが要求に含めるクライアント ID は、パブリック クライアントではない、ネイティブ認証が有効になっていないなど、ネイティブ認証構成がないアプリ用です。 `suberror` プロパティを使用して、エラーの正確な原因を確認します。 |
| `unauthorized_client` | 要求で使用されるクライアント ID には有効なクライアント ID 形式がありますが、外部テナントに存在しないか、正しくありません。 |
| `unsupported_challenge_type` | `challenge_type` パラメーター値には `redirect` チャレンジ型が含まれていません。 |
| `user_already_exists` | ユーザーは既に存在します。 |
| `invalid_grant` | アプリが送信するパスワードは、パスワードが短すぎるなど、複雑さの要件をすべて満たしていません。 `suberror` プロパティを使用して、エラーの正確な原因を確認します。 **このパラメーターが適用されるのは、メールとパスワードの認証方法の場合のみです**。 |

エラー パラメーターの値が *invalid\_grant* の場合、Microsoft Entraは応答に `suberror` プロパティを含めます。 invalid\_grant `suberror` プロパティの値を次に示します。

| サブエラー値 | 説明 |
| --- | --- |
| `password_too_weak` | 複雑さの要件を満たしていないため、パスワードが弱すぎます。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 この応答は、アプリがユーザー パスワードを送信した場合に可能です。 |
| `password_too_short` | 新しいパスワードは 8 文字未満です。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 この応答は、アプリがユーザー パスワードを送信した場合に可能です。 |
| `password_too_long` | 新しいパスワードが 256 文字を超えています。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 この応答は、アプリがユーザー パスワードを送信した場合に可能です。 |
| `password_recently_used` | 新しいパスワードは、最近使用したパスワードと同じにすることはできません。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 この応答は、アプリがユーザー パスワードを送信した場合に可能です。 |
| `password_banned` | 新しいパスワードには、禁止されている単語、語句、またはパターンが含まれています。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 この応答は、アプリがユーザー パスワードを送信した場合に可能です。 |
| `password_is_invalid` | パスワードは無効です。たとえば、許可されていない文字が使用されているためです。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 この応答は、アプリがユーザー パスワードを送信した場合に可能です。 |

エラー パラメーターの値が *invalid\_client* の場合、Microsoft Entraは応答に `suberror` プロパティを含めます。 invalid\_client エラーの `suberror` プロパティの値を次 *に* 示します。

| サブエラー値 | 説明 |
| --- | --- |
| `nativeauthapi_disabled` | ネイティブ認証が有効になっていないアプリのクライアント ID。 |

注

必要なすべての属性を `/signup/v1.0/start` エンドポイント経由で送信するが、すべての省略可能な属性を送信しない場合、後で `/signup/v1.0/continue` エンドポイント経由で追加の省略可能な属性を送信することはできません。 Microsoft Entraは、サインアップ フローを完了するために必須でないので、省略可能な属性を明示的に要求しません。  と `/signup/v1.0/start` エンドポイントに送信できるユーザー属性については、「`/signup/v1.0/continue`」セクションの表をご覧ください。

#### ステップ 2: 認証方法を選びます

アプリは、ユーザーが認証を行うためにサポートされているチャレンジの種類のいずれかを選択するようにMicrosoft Entra要求します。 これを行うために、アプリで `/signup/v1.0/challenge` エンドポイントを呼び出します。 アプリでは、`/signup/v1.0/start` エンドポイントから取得した継続トークンを要求に含める必要があります。

要求の例を次に示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/signup/v1.0/challenge
Content-Type: application/x-www-form-urlencoded
client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&challenge_type=oob password redirect
&continuation_token=AQABAAEAAA…
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |
| `challenge_type` | いいえ | アプリがサポートする認証チャレンジ型文字列のスペース区切りのリスト (例: `oob password redirect`). リストには、常に `redirect` チャレンジ型が含まれている必要があります。 この値は、認証方法がメールのワンタイム パスコードの場合は `oob redirect`、メールとパスワードの場合は `oob password redirect` であると想定されます。 |
| `continuation_token` | はい | 前の要求で返されたcontinuation tokenMicrosoft Entra。 |

##### 成功応答

Microsoft Entraユーザーの電子メールにワンタイム パスコードを送信し、チャレンジの種類として値が *oob* とワンタイム パスコードに関する追加情報で応答します。

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{
    "interval": 300,
    "continuation_token": "AQABAAEAAAYn...",
    "challenge_type": "oob",
    "binding_method": "prompt",
    "challenge_channel": "email",
    "challenge_target_label": "c***r@co**o**o.com",
    "code_length": 8
} 
```

| 財産 | 説明 |
| --- | --- |
| `interval` | アプリが OTP の再送信を試みる前に待機する必要がある時間 (秒単位)。 |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |
| `challenge_type` | 認証するユーザーに選択されたチャレンジ型。 |
| `binding_method` | 有効な値は *prompt* だけです。 このパラメータは、将来的に、さらに多くのワンタイム パスコード入力方法をユーザーに提供するために使用できます。 `challenge_type` が *oob* の場合発行されます |
| `challenge_channel` | ワンタイム パスコードが送信されたチャネルの種類。 現時点では、メール チャネルのみがサポートされています。 |
| `challenge_target_label` | ワンタイム パスコードが送信された難読化されたメール。 |
| `code_length` | Microsoft Entra生成されるワンタイム パスコードの長さ。 |

##### リダイレクト応答

クライアント アプリが、Microsoft Entra必要な認証方法または機能をサポートしていない場合は、Web ベースの認証フローへのフォールバックが必要です。 このシナリオでは、Microsoft Entraは応答で *redirect* チャレンジの種類を返すことによってアプリに通知します。

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{
   "challenge_type": "redirect"
}
```

| 財産 | 説明 |
| --- | --- |
| `challenge_type` | Microsoft Entraはチャレンジの種類を持つ応答を返します。 このチャレンジ型の値は redirect です。これにより、アプリは Web ベースの認証フローを使用できます。 |

この応答は成功と見なされますが、アプリは Web ベースの認証フローに切り替える必要があります。 この場合は、[Microsoft が構築し、サポートされている認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用することをおすすめします。

##### エラー応答

例:

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{ 
    "error": "invalid_request", 
    "error_description": "AADSTS901007: The challenge_type list parameter does not include the 'redirect' type.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: yyyy-...",
    "error_codes": [ 
        901007 
    ], 
    "timestamp": "yyyy-mm-dd 10:15:00Z",
    "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333", 
    "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd"
} 
```

| 財産 | 説明 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `invalid_request` | クライアント ID が空または無効など、要求パラメーターの検証に失敗しました。 |
| `expired_token` | 継続トークンが期限切れです。 |
| `unsupported_challenge_type` | `challenge_type` パラメーター値には `redirect` チャレンジ型が含まれていません。 |
| `invalid_grant` | 継続トークンが無効です。 |

#### 手順 3: ワンタイム パスコードを送信する

アプリは、ユーザーのメール アドレスに送信されたワンタイム パスコードを送信します。 ワンタイム パスコードを送信しているので、`oob` パラメータが必要であり、`grant_type` パラメータの値は *oob* である必要があります。

要求の例を次に示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/signup/v1.0/continue
Content-Type: application/x-www-form-urlencoded
continuation_token=uY29tL2F1dGhlbnRpY...
&client_id=00001111-aaaa-2222-bbbb-3333cccc4444 
&grant_type=oob 
&oob={otp_code}
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `continuation_token` | はい | 前の要求で返されたcontinuation tokenMicrosoft Entra。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |
| `grant_type` | はい | `/signup/v1.0/continue` エンドポイントへの要求を使って、ワンタイム パスコード、パスワード、またはユーザー属性を送信できます。 この場合、この `grant_type` 値はこれら 3 つのユース ケースを区別するために使用されます。 grant\_type に使用できる値は、*oob*、*password*、*attributes* です。 この呼び出しでは、ワンタイム パスコードを送信しているので、値は *oob* である必要があります。 |
| `oob` | はい | 顧客ユーザーがメールで受け取ったワンタイム パスコード。 `{otp_code}` を顧客ユーザーがメールで受け取ったワンタイム パスコードの値に置き換えます。 **ワンタイム パスコードを再送信**するには、アプリで `/signup/v1.0/challenge` エンドポイントにもう一度要求を行う必要があります。 |

アプリによるワンタイム パスコードの送信が成功した後のサインアップ フローは、次の表に示すようにシナリオによって異なります。

| シナリオ | 進め方 |
| --- | --- |
| アプリは、`/signup/v1.0/start` エンドポイント経由でユーザーのパスワード (パスワード認証方法を使用した電子メールの場合) を正常に送信し、Microsoft Entra管理センターで属性が構成されていないか、必要なすべてのユーザー属性が `/signup/v1.0/start` エンドポイント経由で送信されます。 | Microsoft Entraは継続トークンを発行します。 アプリでは、「セキュリティ トークンの要求」に示すように、継続トークンを使用して セキュリティ トークンを要求できます。 |
| アプリは、 を介してユーザーのパスワード (パスワード認証方法を使用した電子メールの場合) を正常に送信しますが、必要なすべてのユーザー属性ではなく、Microsoft Entraは、ユーザー属性が必要。 | アプリから、`/signup/v1.0/continue` エンドポイント経由で必要なユーザー属性を送信する必要があります。 応答は、「必要なユーザー属性」に示すもののようになります。 「ユーザー属性の送信」に示されているユーザー属性を送信します。 |
| アプリでは、`/signup/v1.0/start` エンドポイント経由でユーザーのパスワード (メールとパスワードの認証方法の場合) を送信しません。 | Microsoft Entraの応答は、資格情報が必要であることを示します。 応答を参照してください。 **この応答が返されるのは、メールとパスワードの認証方法の場合です**。 |

##### 回答

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{
    "error": "credential_required",
    "error_description": "AADSTS55103: Credential required. Trace ID: d6966055-...-80500 Correlation ID: 3944-...-60d6 Timestamp: yy-mm-dd 02:37:33Z",
    "error_codes": [
        55103
    ],
    "timestamp": "yy-mm-dd 02:37:33Z",
    "trace_id": "d6966055-...-80500",
    "correlation_id": "3944-...-60d6",
    "continuation_token": "AQABEQEAAAA..."
} 
```

| 財産 | 説明 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |
| `suberror` | エラーの種類を詳細に分類するのに使用できるエラー コード文字列。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `credential_required` | アカウントの作成には認証が必要であるため、`/signup/v1.0/challenge` エンドポイントを呼び出して、ユーザーが指定する必要がある資格情報を特定する必要があります。 |
| `invalid_request` | *継続トークン*の検証に失敗したか、要求に `client_id` パラメーターが含まれていない、クライアント ID 値が空か無効か、外部テナント管理者がすべてのテナント ユーザーに対してメール OTP を有効にしていないなど、要求パラメーターの検証に失敗しました。 |
| `invalid_grant` | 要求に含まれる許可の種類が有効またはサポートされていないか、OTP 値が正しくありません。 |
| `expired_token` | 要求に含まれる継続トークンの有効期限が切れています。 |

エラー パラメーターの値が *invalid\_grant* の場合、Microsoft Entraは応答に `suberror` プロパティを含めます。 invalid\_grant `suberror` プロパティの値を次に示します。

| サブエラー値 | 説明 |
| --- | --- |
| `invalid_oob_value` | ワンタイム パスコードの値が無効です。 |

ユーザーからパスワード資格情報を収集するには、アプリが `/signup/v1.0/challenge` エンドポイントを呼び出して、ユーザーが指定する必要がある資格情報を決定する必要があります。

要求の例を次に示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/signup/v1.0/challenge
Content-Type: application/x-www-form-urlencoded
client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&challenge_type=oob password redirect
&continuation_token=AQABAAEAAA…
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |
| `challenge_type` | いいえ | アプリがサポートする認証チャレンジ型文字列のスペース区切りのリスト (例: `oob password redirect`). リストには、常に `redirect` チャレンジ型が含まれている必要があります。 パスワード サインアップ フローを含むメールの場合、値には `password redirect` を含める必要があります。 |
| `continuation_token` | はい | 前の要求で返されたcontinuation tokenMicrosoft Entra。 |

##### 成功応答

パスワードがMicrosoft Entra管理センターでユーザー用に構成された認証方法である場合、継続トークンを含む成功応答がアプリに返されます。

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{
    "challenge_type": "password",
    "continuation_token": " AQABAAEAAAAty..."
}
```

| 財産 | 説明 |
| --- | --- |
| `challenge_type` | *password* は、必要な資格情報の応答で返されます。 |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |

##### リダイレクト応答

クライアント アプリが、Microsoft Entra必要な認証方法または機能をサポートしていない場合は、Web ベースの認証フローへのフォールバックが必要です。 このシナリオでは、Microsoft Entraは応答で *redirect* チャレンジの種類を返すことによってアプリに通知します。

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{     
    "challenge_type": "redirect" 
} 
```

| 財産 | 説明 |
| --- | --- |
| `challenge_type` | Microsoft Entraはチャレンジの種類を持つ応答を返します。 このチャレンジ型の値は redirect です。これにより、アプリは Web ベースの認証フローを使用できます。 |

この応答は成功と見なされますが、アプリは Web ベースの認証フローに切り替える必要があります。 この場合は、[Microsoft が構築し、サポートされている認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用することをおすすめします。

#### ステップ 4: サインアップするトークンを認証して取得する

アプリは、前の手順で要求Microsoft Entraユーザーの資格情報 (この場合はパスワード) を送信する必要があります。 `/signup/v1.0/start` エンドポイント経由でパスワード資格情報を送信しなかった場合、アプリはパスワード資格情報を送信する必要があります。 アプリは、 `/signup/v1.0/continue` エンドポイントにパスワードの送信を要求します。 パスワードを送信するため、`password` パラメーターが必要であり、`grant_type` パラメーターには値 *password* が必要です。

要求の例を次に示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/signup/v1.0/continue
Content-Type: application/x-www-form-urlencoded
continuation_token=uY29tL2F1dGhlbnRpY...
&client_id=00001111-aaaa-2222-bbbb-3333cccc4444 
&grant_type=password 
&password={secure_password}
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `continuation_token` | はい | 前の手順で返されたcontinuation tokenMicrosoft Entra。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |
| `grant_type` | はい | `/signup/v1.0/continue` エンドポイントへの要求を使って、ワンタイム パスコード、パスワード、またはユーザー属性を送信できます。 この場合、この `grant_type` 値はこれら 3 つのユース ケースを区別するために使用されます。 grant\_type に使用できる値は、*oob*、*password*、*attributes* です。 この呼び出しでは、ユーザーのパスワードを送信しているため、値は *password* である必要があります。 |
| `password` | はい | アプリが顧客ユーザーから収集するパスワード値。 `{secure_password}` をアプリケーションが顧客ユーザーから収集するパスワードの値を置き換えます。 アプリの UI でパスワードの確認フィールドを指定して、ユーザーが使用するパスワードを認識していることを確認するのはユーザーの責任です。 また、組織のポリシーに従って、何が強力なパスワードであるかをユーザーに認識させる必要があります。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 |

##### 成功応答

要求が成功しても、Microsoft Entra管理センターで属性が構成されていない場合、または必要なすべての属性が `/signup/v1.0/start` エンドポイント経由で送信された場合、アプリは属性を送信せずに継続トークンを取得します。 アプリでは、「セキュリティ トークンの要求」に示すように、継続トークンを使用して セキュリティ トークンを要求できます。 それ以外の場合、Microsoft Entraの応答は、アプリが必要な属性を送信する必要があることを示します。 これらの属性は、組み込みまたはカスタムで、テナント管理者によってMicrosoft Entra管理センターで構成されました。

###### 必要なユーザー属性

この応答は、 *名前*、 *年齢*、 *および電話* の属性の値を送信するようにアプリに要求します。

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{
    "error": "attributes_required",
    "error_description": "User attributes required",
    "error_codes": [
            55106
        ],
    "timestamp": "yy-mm-dd 02:37:33Z",
    "trace_id": "d6966055-...-80500",
    "correlation_id": "3944-...-60d6",
    "continuation_token": "AQABAAEAAAAtn...",
    "required_attributes": [
        {
            "name": "displayName",
            "type": "string",
            "required": true,
            "options": {
              "regex": ".*@.**$"
            }
        },
        {
            "name": "extension_2588abcdwhtfeehjjeeqwertc_age",
            "type": "string",
            "required": true
        },
        {
            "name": "postalCode",
            "type": "string",
            "required": true,
            "options": {
              "regex":"^[1-9][0-9]*$"
            }
        }
    ],
}
```

注

カスタム属性 (ディレクトリ拡張機能とも呼ばれます) は、規則 `extension_{appId-without-hyphens}_{attribute-name}` を使用して名前が付けられます。なお `{appId-without-hyphens}` は *拡張機能アプリ*のクライアント ID を取り除いたバージョンです。 たとえば、*拡張機能アプリ*のクライアント ID が `2588a-bcdwh-tfeehj-jeeqw-ertc` で、属性名が *hobbies* の場合、カスタム属性は `extension_2588abcdwhtfeehjjeeqwertc_hobbies` と命名されます。 [カスタム属性と拡張機能アプリ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes#create-custom-user-attributes)に関する詳細情報をご確認ください。

| 財産 | 説明 |
| --- | --- |
| `error` | この属性は、Microsoft Entra属性を検証または送信する必要があるため、ユーザー アカウントを作成できない場合に設定されます。 |
| `error_description` | エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |
| `required_attributes` | 続行するためにアプリが次の呼び出しを送信する必要がある属性のリスト (オブジェクトの配列)。 これらの属性は、アプリがユーザー名とは別に送信する必要がある追加の属性です。 このパラメーター Microsoft Entra含まれるのは、`error` パラメーターの値が *attributes\_required* の場合の応答です。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `invalid_request` | *継続トークン*の検証に失敗した、要求に `client_id`パ ラメータが含まれていない、クライアント ID の値が空または無効であるなど、要求パラメータの検証に失敗しました。 |
| `invalid_grant` | 要求に含まれる許可の種類が無効であるか、サポートされていません。 `grant_type` に使用できる値は、*oob*、*password*、*attributes* です |
| `expired_token` | 要求に含まれる継続トークンの有効期限が切れています。 |
| `attributes_required` | 1 つ以上のユーザー属性が必要です。 |

##### リダイレクト応答

クライアント アプリが、Microsoft Entra必要な認証方法または機能をサポートしていない場合は、Web ベースの認証フローへのフォールバックが必要です。 このシナリオでは、Microsoft Entraは応答で *redirect* チャレンジの種類を返すことによってアプリに通知します。

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{     
   "challenge_type": "redirect" 
} 
```

| 財産 | 説明 |
| --- | --- |
| `challenge_type` | Microsoft Entraはチャレンジの種類を持つ応答を返します。 このチャレンジ型の値は redirect です。これにより、アプリは Web ベースの認証フローを使用できます。 |

この応答は成功と見なされますが、アプリは Web ベースの認証フローに切り替える必要があります。 この場合は、[Microsoft が構築し、サポートされている認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用することをおすすめします。

##### エラー応答

例:

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{
    "error": "invalid_grant",
    "error_description": "New password is too weak",
    "error_codes": [
        399246
    ], 
    "timestamp": "yyyy-mm-dd 10:15:00Z",
    "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333", 
    "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd",
    "suberror": "password_too_weak"
}
```

| 財産 | 説明 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |
| `suberror` | エラーの種類を詳細に分類するのに使用できるエラー コード文字列。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `invalid_request` | `challenge_type` パラメーターに無効なチャレンジ型が含まれている場合など、要求パラメーターの検証に失敗しました。 |
| `invalid_grant` | 送信されたパスワードが短すぎるなど、送信された許可が無効です。 `suberror` プロパティを使用して、エラーの正確な原因を確認します。 |
| `expired_token` | 継続トークンが期限切れです。 |
| `attributes_required` | 1 つ以上のユーザー属性が必要です。 |

エラー パラメーターの値が *invalid\_grant* の場合、Microsoft Entraは応答に `suberror` プロパティを含めます。 `suberror` プロパティで使用できる値を次に示します。

| サブエラー値 | 説明 |
| --- | --- |
| `password_too_weak` | 複雑さの要件を満たしていないため、パスワードが弱すぎます。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 |
| `password_too_short` | 新しいパスワードは 8 文字未満です。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 |
| `password_too_long` | 新しいパスワードが 256 文字を超えています。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 |
| `password_recently_used` | 新しいパスワードは、最近使用したパスワードと同じにすることはできません。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 |
| `password_banned` | 新しいパスワードには、禁止されている単語、語句、またはパターンが含まれています。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 |
| `password_is_invalid` | パスワードは無効です。たとえば、許可されていない文字が使用されているためです。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 この応答は、アプリがユーザー パスワードを送信した場合に可能です。 |

##### ユーザー属性の送信

フローを続行するには、アプリで `/signup/v1.0/continue` エンドポイントを呼び出して、必要なユーザー属性を送信する必要があります。 属性を送信するため、`attributes` パラメーターが必要であり、`grant_type` パラメーターには *attributes* と等しい値が必要です。

要求の例を次に示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/signup/v1.0/continue
Content-Type: application/x-www-form-urlencoded
&client_id=00001111-aaaa-2222-bbbb-3333cccc4444 
&grant_type=attributes 
&attributes={"displayName": "{given_name}", "extension_2588abcdwhtfeehjjeeqwertc_age": "{user_age}", "postalCode": "{postal_code}"}
&continuation_token=AQABAAEAAAAtn...
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `continuation_token` | はい | 前の要求で返されたcontinuation tokenMicrosoft Entra。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |
| `grant_type` | はい | `/signup/v1.0/continue` エンドポイントへの要求を使って、ワンタイム パスコード、パスワード、またはユーザー属性を送信できます。 この場合、この `grant_type` 値はこれら 3 つのユース ケースを区別するために使用されます。 grant\_type に使用できる値は、*oob*、*password*、*attributes* です。 この呼び出しでは、ユーザー属性を送信しているため、値は *attributes* であることが期待されます。 |
| `attributes` | はい | アプリが顧客ユーザーから収集するユーザー属性値。 値は文字列ですが、組み込みまたはカスタムのユーザー属性の名前をキー値とする JSON オブジェクトとして書式設定されます。 オブジェクトのキー名は、管理者が管理センターで構成Microsoft Entra属性によって異なります。 `{given_name}`、`{user_age}`、`{postal_code}` は、アプリが顧客ユーザーから収集する名前、年齢、郵便番号の値にそれぞれ置き換えます。 **Microsoft Entraは、送信した属性 (存在しない属性**を無視します。 |

##### 成功応答

要求が成功した場合は、ユーザー Microsoft Entraサインアップし、継続トークンを発行します。 アプリは継続トークンを使用して、 `oauth/v2.0/token` エンドポイントからセキュリティ トークンを要求できます。

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{  
    "continuation_token": "AQABAAEAAAYn..."
} 
```

| 財産 | 説明 |
| --- | --- |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |

##### リダイレクト応答

クライアント アプリが、Microsoft Entra必要な認証方法または機能をサポートしていない場合は、Web ベースの認証フローへのフォールバックが必要です。 このシナリオでは、Microsoft Entraは応答で *redirect* チャレンジの種類を返すことによってアプリに通知します。

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{     
   "challenge_type": "redirect" 
} 
```

| 財産 | 説明 |
| --- | --- |
| `challenge_type` | Microsoft Entraはチャレンジの種類を持つ応答を返します。 このチャレンジ型の値は redirect です。これにより、アプリは Web ベースの認証フローを使用できます。 |

この応答は成功と見なされますが、アプリは Web ベースの認証フローに切り替える必要があります。 この場合は、[Microsoft が構築し、サポートされている認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用することをおすすめします。

##### エラー応答

例:

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{
    "error": "expired_token",
    "error_description": "AADSTS901007: The continuation_token is expired.  .\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: yyyy-...", 
    "error_codes": [
        552003
    ], 
    "timestamp": "yyyy-mm-dd 10:15:00Z",
    "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333", 
    "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd" 
}
```

| 財産 | 説明 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |
| `unverified_attributes` | 検証する必要がある属性キー名のリスト (オブジェクトの配列)。 このパラメーターは、`error` パラメーターの値が *verification\_required* の場合に応答に含まれます。 |
| `required_attributes` | アプリが送信する必要がある属性のリスト (オブジェクトの配列)。 Microsoft Entraは、`error` パラメーターの値が *attributes\_required*の場合に、応答にこのパラメーターを含めます。 |
| `invalid_attributes` | 検証に失敗した属性の (オブジェクトの配列) のリスト。 このパラメーターは、 `suberror` プロパティの値がattribute\_validation\_failedされるときに応答に含 *まれます*。 |
| `suberror` | エラーの種類を詳細に分類するのに使用できるエラー コード文字列。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `invalid_request` | *継続トークン*の検証に失敗した、要求に `client_id`パ ラメータが含まれていない、クライアント ID の値が空または無効であるなど、要求パラメータの検証に失敗しました。 |
| `invalid_grant` | 指定された許可の種類が無効であるか、サポートされていないか、検証に失敗しました (属性の検証に失敗するなど)。 `suberror` プロパティを使用して、エラーの正確な原因を確認します。 |
| `expired_token` | 要求に含まれる継続トークンの有効期限が切れています。 |
| `attributes_required` | 1 つ以上のユーザー属性が必要です。 |

エラー パラメーターの値が *invalid\_grant* の場合、Microsoft Entraは応答に `suberror` プロパティを含めます。 invalid\_grant `suberror` プロパティの値を次に示します。

| サブエラー値 | 説明 |
| --- | --- |
| `attribute_validation_failed` | ユーザー属性の検証に失敗しました。 `invalid_attributes` パラメーターには、検証に失敗した属性のリスト (オブジェクトの配列) が含まれています。 |

#### 手順 5: サインアップ後に自動的にサインインする

ユーザーがサインアップ後に自動的にサインインする必要がある場合、アプリは `oauth/v2.0/token` エンドポイントに POST 要求を行い、前の手順で取得した継続トークンを提供してセキュリティ トークンを取得します。 トークン エンドポイントを呼び出す方法について説明します。

### エンドポイントへのユーザー属性の送信

Microsoft Entra管理センターでは、必要に応じて、または省略可能なユーザー属性を構成できます。 この構成により、エンドポイントへの呼び出しを行ったときにMicrosoft Entraがどのように応答するかが決まります。 サインアップ フローが完了するために、省略可能な属性は必要ありません。 したがって、すべての属性が省略可能な場合は、ユーザー名が検証される前にそれらを送信する必要があります。 そうしないと、省略可能な属性なしでサインアップが完了します。

次の表は、Microsoft Entraエンドポイントにユーザー属性を送信できるタイミングをまとめたものです。

| エンドポイント | 必須の属性 | 省略可能な属性 | 必須属性と省略可能属性の両方 |
| --- | --- | --- | --- |
| `/signup/v1.0/start` エンドポイント | はい | はい | はい |
| ユーザー名検証前の `/signup/v1.0/continue` エンドポイント | はい | はい | はい |
| ユーザー名検証後の `/signup/v1.0/continue` エンドポイント | はい | いいえ | はい |

### ユーザー属性値の形式

Microsoft Entra管理センターでユーザー フロー設定を構成して、ユーザーから収集する情報を指定します。 「[サインアップ時にカスタム ユーザー属性](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes)」に関する記事を使用して、ビルトインとカスタム属性の両方の値を収集する方法を確認してください。

構成する属性の**ユーザーによる入力の種類**も指定することができます。 次の表は、サポートされているユーザー入力の種類と、UI コントロールによって収集された値をMicrosoft Entraに送信する方法をまとめたものです。

| ユーザーによる入力の種類 | 送信された値の形式 |
| --- | --- |
| テキストボックス | 役職、*ソフトウェア エンジニア*などの単一の値。 |
| SingleRadioSelect | 言語、*ノルウェー語*などの 1 つの値。 |
| チェックボックス複数選択 | 趣味などの 1 つまたは複数の値、*ダンス*、または*ダンス、水泳、旅行*。 |

属性の値を送信する方法を示す要求の例を次に示します:

```http
POST /{tenant_subdomain}.onmicrosoft.com/signup/v1.0/continue HTTP/1.1
Host: {tenant_subdomain}.ciamlogin.com
Content-Type: application/x-www-form-urlencoded
 
continuation_token=ABAAEAAAAtfyo... 
&client_id=00001111-aaaa-2222-bbbb-3333cccc4444 
&grant_type=attributes 
&attributes={"jobTitle": "Software Engineer", "extension_2588abcdwhtfeehjjeeqwertc_language": "Norwegian", "extension_2588abcdwhtfeehjjeeqwertc_hobbies": "Dancing,Swimming,Traveling"}
&continuation_token=AQABAAEAAAAtn...
```

ユーザー属性の入力型の詳細については、「[カスタム ユーザー属性の入力の種類](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes#custom-user-attributes-input-types)」の記事を参照してください。

#### ユーザー属性の参照方法

[サインアップ ユーザー フローを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)する場合は、サインアップ時にユーザーから収集するユーザー属性を構成します。 Microsoft Entra管理センターのユーザー属性の名前は、ネイティブ認証 API で参照する方法とは異なります。

たとえば、Microsoft Entra 管理センターの *Display Name* は、API で *displayName* として参照されます。

ネイティブ認証 API で組み込みユーザー属性とカスタム ユーザー属性の両方を参照する方法については、[ユーザー プロファイル属性](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes)に関する記事を参照してください。

### サインインフローのAPI参照

ユーザーは、サインアップに使用する認証方法でサインインする必要があります。 たとえば、メールとパスワードの認証方法を使用してサインアップするユーザーは、メールとパスワードでサインインする必要があります。

セキュリティ トークンを要求するために、アプリは 3 つのエンドポイント ( `oauth/v2.0/initiate`、 `oauth/v2.0/challenge`、 `oauth/v2.0/token`、必要に応じて `oauth/v2.0/introspect`) と対話します。

#### サインイン用のAPIエンドポイント

| エンドポイント | 説明 |
| --- | --- |
| `oauth/v2.0/initiate` | このエンドポイントは、サインイン フローを開始します。 アプリが既に存在するユーザー アカウントのユーザー名を使用して呼び出した場合、継続トークンを使用して成功応答を返します。 アプリがMicrosoft Entraでサポートされていない認証方法の使用を要求した場合、このエンドポイント応答は、ブラウザー ベースの認証フローを使用する必要があることをアプリに示すことができます。 |
| `oauth/v2.0/challenge` | アプリはこのエンドポイントを呼び出して、ユーザーの認証に使用するサポートされている sign-in チャレンジの種類のいずれかを選択するようにMicrosoft Entraを要求します。 テナント管理者が顧客ユーザーに対して MFA を適用する場合、アプリはこのエンドポイントを呼び出して、第 2 要素認証方法についてユーザーにチャレンジします。 |
| `oauth/v2.0/token` | このエンドポイントは、アプリから受け取ったユーザーの資格情報を検証し、アプリにセキュリティ トークンを発行します。 このエンドポイントからの応答は、ユーザーが MFA チャレンジを完了するか、強力な認証方法を登録する必要があるかを示すこともできます。 |
| `oauth/v2.0/introspect` | アプリが呼び出して、多要素認証 (MFA) に登録されている強力な認証方法の一覧を要求します。 イントロスペクト エンドポイントを使用する方法について説明します |

#### サインイン チャレンジ型

API を使用すると、アプリは、Microsoft Entraの呼び出しを行うときに、サポートする認証方法をアドバタイズできます。 これを行うために、アプリはその要求に `challenge_type` パラメーターを使用します。 このパラメーターには、さまざまな認証方法を表す定義済みの値が保持されます。

特定の認証方法では、サインアップ フロー中にアプリがMicrosoft Entraに送信するチャレンジの種類の値は、アプリがサインインしたときと同じです。 たとえば、メールとパスワードの認証方法では、サインアップとサインインの両方のフローで *oob*、*password*、*redirect* というチャレンジ型の値を使用します。

チャレンジ型の詳細については、「[ネイティブ認証のチャレンジ型](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication-challenge-types)」の記事を参照してください。

#### サインイン フロー プロトコルの詳細

シーケンス図は、サインイン プロセスのフローを示しています。 サインイン フローは、ユーザーの認証方法によって異なります。

## [ワンタイム パスコードの電子メール送信](#tab/emailOtp)
[Image: メール ワンタイム パスコードを使うネイティブ認証サインインの図。]

アプリはユーザーの OTP 付きのメールを確認した後、セキュリティ トークンを受け取ります。 ワンタイム パスコードの配信が遅れたり、ユーザーのメール アドレスに配信されない場合、ユーザーは別のワンタイム パスコードの送信を要求できます。 Microsoft Entra前のパスコードが検証されていない場合は、別のワンタイム パスコードを再送信します。 Microsoft Entraワンタイム パスコードを再送信すると、以前に送信されたコードが無効になります。

## [パスワードを含む電子メール](#tab/emailPassword)
[Image: メールとパスワード オプションを使用したネイティブ認証サインインの図。]

- この図は、アプリがユーザーのユーザー名 (メール) とパスワードを異なるタイミングで (場合によっては別の画面で) 収集することを示しています。 ただし、同じ画面で 2 つの値を収集するようにアプリを設計できます。
- 同じ画面でユーザー名 (メール) とパスワードを収集すると、ステップ **2** と **3** がステップ **8** と **9**にマージされます。 この場合、アプリはパスワードを保持し、必要に応じてステップ **10** で送信します。

テナント管理者がテナント ユーザーに対して MFA を有効にした場合、 `/oauth2/v2.0/token` エンドポイントからの応答は、ユーザーが既に登録済みの強力な認証方法を持っているかどうかによって異なります。

- ユーザーが強力な認証方法を登録している場合は、MFA チャレンジ フローを完了します。
- ユーザーが強力な認証方法を登録していない場合は、 強力な認証方法フローの登録を 完了します。

このシーケンス図は、MFA パスを示しています。 (1) ユーザーが既に登録済みの強力な認証方法を持っているか、(2) ユーザーが何も持っず、Just-In-Time を 1 つ登録する必要がある、という 2 つのケースについて説明します。 フローは、アプリがユーザーから正しいパスワードを収集し、/oauth2/v2.0/token を呼び出した後に開始されます。これにより、ユーザーが MFA を完了するか、強力な認証方法を登録する必要があるかを示す応答が返されます。

[Image: ネイティブ認証呼び出しトークン エンドポイントの登録認証方法または完全な MFA の図。]

---

次のセクションでは、サインイン フローを 3 つの基本的な手順にまとめます。

#### ステップ 1: サインイン フローの開始を要求する

認証フローは、アプリケーションが`/initiate` エンドポイントに POST 要求を行ってサインイン フローを開始することで始まります。

要求の例を次に示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/oauth2/v2.0/initiate
Content-Type: application/x-www-form-urlencoded
client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&challenge_type=password redirect
&username=contoso-consumer@contoso.com
&capabilities=registration_required mfa_required
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |
| `username` | はい | *contoso-consumer@contoso.com* などの顧客ユーザーのメール。 |
| `challenge_type` | はい | アプリがサポートする認証チャレンジ型文字列のスペース区切りのリスト (例: `oob password redirect`). リストには、常に `redirect` チャレンジ型が含まれている必要があります。 この値は、メールのワンタイム パスコードの場合は `oob redirect`、メールとパスワードの場合は `password redirect` であると想定されます。 |
| `capabilities` | いいえ | クライアント アプリの "方法" の準備状況を説明するスペース区切りのフラグ。 `challenge_type`はチャレンジできる方法を定義しますが、`capabilities`は、クライアント アプリが処理できる追加フローと表示できる UI をネイティブ認証 API に指示します。 たとえば、 `mfa_required` は、 `/introspect`、 `/challenge`、および `/token` ループを意味します。 `registration_required` は、クライアント アプリが登録 API を呼び出して登録 UI を表示します。 必要な機能がクライアント アプリに含まれていない場合、API はリダイレクトを返します。 サポートされている値は、`mfa_required` と `registration_required`です。 [機能の詳細を確認します](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-challenge-types)。 |

##### 成功応答

アップロードの成功の例を次に示します:

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{
    "continuation_token": "uY29tL2F1dGhlbnRpY..."
}
```

| 財産 | 説明 |
| --- | --- |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |

##### リダイレクト応答

クライアント アプリが、Microsoft Entra必要な認証方法または機能をサポートしていない場合は、Web ベースの認証フローへのフォールバックが必要です。 このシナリオでは、Microsoft Entraは応答で *redirect* チャレンジの種類を返すことによってアプリに通知します。

```http
HTTP/1.1 200 OK
Content-Type: application/json

```

```json
{     
   "challenge_type": "redirect" 
} 
```

| 財産 | 説明 |
| --- | --- |
| `challenge_type` | Microsoft Entraはチャレンジの種類を持つ応答を返します。 このチャレンジ型の値は redirect です。これにより、アプリは Web ベースの認証フローを使用できます。 |

この応答は成功と見なされますが、アプリは Web ベースの認証フローに切り替える必要があります。 この場合は、[Microsoft が構築し、サポートされている認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用することをおすすめします。

##### エラー応答

例:

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{ 
    "error": "invalid_request", 
    "error_description": "AADSTS901007: The challenge_type list parameter does not include the 'redirect' type.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: yyyy-...",
    "error_codes": [ 
        901007 
    ], 
    "timestamp": "yyyy-mm-dd 10:15:00Z",
    "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333", 
    "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd"
} 
```

| 財産 | 説明 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `invalid_request` | `challenge_type` パラメーターに無効なチャレンジ型が含まれている場合など、要求パラメーターの検証に失敗しました。 または、要求に `client_id` パラメーターが含まれていませんでした。クライアント ID 値が空または無効です。 `error_description` パラメーターを使用して、エラーの正確な原因を確認します。 |
| `unauthorized_client` | 要求で使用されるクライアント ID には有効なクライアント ID 形式がありますが、外部テナントに存在しないか、正しくありません。 |
| `invalid_client` | アプリが要求に含めるクライアント ID は、パブリック クライアントではない、ネイティブ認証が有効になっていないなど、ネイティブ認証構成がないアプリ用です。 `suberror` プロパティを使用して、エラーの正確な原因を確認します。 |
| `user_not_found` | username が存在しません。 |
| `unsupported_challenge_type` | `challenge_type` パラメーター値には `redirect` チャレンジ型が含まれていません。 |

エラー パラメーターの値が *invalid\_client* の場合、Microsoft Entraは応答に `suberror` プロパティを含めます。 invalid\_client エラーの `suberror` プロパティの値を次 *に* 示します。

| サブエラー値 | 説明 |
| --- | --- |
| `nativeauthapi_disabled` | ネイティブ認証が有効になっていないアプリのクライアント ID。 |

#### ステップ 2: 認証方法を選びます

フローを続行するために、アプリは前の手順で取得した継続トークンを使用してMicrosoft Entraを要求し、ユーザーが MFA チャレンジを認証または完了するためにサポートされているチャレンジの種類のいずれかを選択します。 アプリが `/oauth2/v2.0/challenge` エンドポイントに POST 要求を行います。

要求の例を次に示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/oauth2/v2.0/challenge
Content-Type: application/x-www-form-urlencoded
client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&challenge_type=password redirect 
&continuation_token=uY29tL2F1dGhlbnRpY... 
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |
| `continuation_token` | はい | 前の要求で返されたcontinuation tokenMicrosoft Entra。 前の要求では、 `/oauth2/v2.0/initiate` エンドポイントが呼び出されます。ユーザーが MFA チャレンジを完了した場合は、 `/oauth2/v2.0/introspect` エンドポイントが呼び出されます。 |
| `challenge_type` | いいえ | アプリがサポートする認証チャレンジ型文字列のスペース区切りのリスト (例: `oob password redirect`). リストには、常に `redirect` チャレンジ型が含まれている必要があります。 この値は、メールのワンタイム パスコードの場合は `oob redirect`、メールとパスワードの場合は `password redirect` であると想定されます。 |
| `id` | いいえ | `/oauth2/v2.0/introspect` エンドポイントから返される強力な認証方法の文字列識別子。 このパラメーターは、クライアント アプリがユーザーに第 2 要素認証を要求する場合に必要です。 イントロスペクト エンドポイントを使用する方法について説明します。 |

##### 成功応答

成功応答は、ユーザーの認証方法によって異なります。

## [ワンタイム パスコードの電子メール送信](#tab/emailOtp)
テナント管理者がMicrosoft Entra管理センターで電子メールワンタイム パスコードをユーザーの認証方法として構成した場合、Microsoft Entraはワンタイム パスコードをユーザーの電子メールに送信し、チャレンジの種類として *oob* に応答し、ワンタイム パスコードに関する詳細情報を提供します。

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{
    "continuation_token": "uY29tL2F1dGhlbnRpY...",
    "challenge_type": "oob",
    "binding_method": "prompt ", 
    "challenge_channel": "email",
    "challenge_target_label ": "c***r@co**o**o.com ",
    "code_length": 8
} 
```

| 財産 | 説明 |
| --- | --- |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |
| `challenge_type` | 認証するユーザーに選択されたチャレンジ型。 |
| `binding_method` | 有効な値は *prompt* だけです。 このパラメーターは、将来的に、さらに多くのワンタイム パスコード入力方法をユーザーに提供するために使用できます。 `challenge_type` が *oob* の場合発行されます |
| `challenge_channel` | ワンタイム パスコードが送信されたチャネルの種類。 現時点では、メールがサポートされています。 |
| `challenge_target_label` | ワンタイム パスコードが送信された難読化されたメール。 |
| `code_length` | Microsoft Entra生成されるワンタイム パスコードの長さ。 |

## [パスワードを含む電子メール](#tab/emailPassword)
テナント管理者がユーザーの認証方法としてMicrosoft Entra管理センターでパスワードを使用して電子メールを構成した場合、応答は、`/oauth2/v2.0/challenge` エンドポイントに対する要求が、ユーザーが認証する方法を選択するか、MFA チャレンジを完了するかによって異なります。

**要求が認証方法を選択する場合の応答**

`/oauth2/v2.0/challenge` エンドポイントに対する要求が、ユーザーの認証方法 (第 1 要素認証) を選択する場合、Microsoft Entraは成功応答を返します。これには、チャレンジの種類として *password* が含まれます。

例:

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{   
   "continuation_token": "uY29tL2F1dGhlbnRpY",   
   "challenge_type": "password" 
} 
```

| 財産 | 説明 |
| --- | --- |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |
| `challenge_type` | Microsoft Entraは、Microsoft Entra管理センターでユーザーに対して構成された、サポートされているチャレンジの種類を返します。 この場合、値は *password* であることが期待されます。 |

**要求が MFA チャレンジを完了する場合の応答**

`/oauth2/v2.0/challenge` エンドポイントへの要求が MFA チャレンジ (第 2 要素認証) を完了する場合、Microsoft Entraはユーザーが選択した MFA チャレンジ チャネルにワンタイム パスコードを送信し、ワンタイム パスコードに関する詳細情報を提供します。

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{
    "continuation_token": "uY29tL2F1dGhlbnRpY...",
    "challenge_type": "oob",
    "binding_method": "prompt ", 
    "challenge_channel": "email",
    "challenge_target_label ": "c***r@co**o**o.com ",
    "code_length": 8
} 
```

| 財産 | 説明 |
| --- | --- |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |
| `challenge_type` | ユーザーが MFA を完了するために選択したチャレンジの種類。 |
| `binding_method` | 有効な値は *prompt* だけです。 このパラメーターは、将来的に、さらに多くのワンタイム パスコード入力方法をユーザーに提供するために使用できます。 `challenge_type` が *oob* の場合発行されます |
| `challenge_channel` | ワンタイム パスコードが送信された MFA チャレンジ チャネルの種類。 サポートされている値: *電子メール、SMS*。 |
| `challenge_target_label` | ワンタイム パスコードが送信された難読化されたメール。 |
| `code_length` | Microsoft Entra生成されるワンタイム パスコードの長さ。 |

---

##### リダイレクト応答

次のシナリオでは、Web ベースの認証フローへのフォールバックが必要になる場合があります。

- クライアント アプリは、Microsoft Entraが必要とする認証方法や機能をサポートしていません。
- ユーザーは強力な認証方法として SMS を使用しようとしますが、不正な保護によって要求が高リスクと見なされた場合 (パスワード認証を使用したメールでのみ) 要求がブロックされます。

これらのシナリオでは、Microsoft Entraは応答で *redirect* チャレンジの種類を返すことによってアプリに通知します。

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{     
   "challenge_type": "redirect" 
} 
```

| 財産 | 説明 |
| --- | --- |
| `challenge_type` | Microsoft Entraはチャレンジの種類を持つ応答を返します。 このチャレンジ型の値は redirect です。これにより、アプリは Web ベースの認証フローを使用できます。 |

この応答は成功と見なされますが、アプリは Web ベースの認証フローに切り替える必要があります。 この場合は、[Microsoft が構築し、サポートされている認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用することをおすすめします。

##### エラー応答

例:

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{ 
    "error": "invalid_request", 
    "error_description": "AADSTS901007: The challenge_type list parameter does not include the 'redirect' type.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: yyyy-...",
    "error_codes": [ 
        901007 
    ], 
    "timestamp": "yyyy-mm-dd 10:15:00Z",
    "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333", 
    "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd"
} 
```

| 財産 | 説明 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `invalid_request` | `challenge_type` パラメーターに無効なチャレンジ型が含まれている場合など、要求パラメーターの検証に失敗しました。 |
| `invalid_grant` | 要求に含まれる継続トークンが有効ではありません。 |
| `expired_token` | 要求に含まれる継続トークンの有効期限が切れています。 |
| `unsupported_challenge_type` | `challenge_type` パラメーター値には `redirect` チャレンジ型が含まれていません。 |

#### ステップ 3: セキュリティ トークンの要求

アプリは、 `oauth2/v2.0/token` エンドポイントに POST 要求を行い、セキュリティ トークンを取得するために前の手順で選択したユーザーの資格情報を提供します。

要求の例を次に示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/oauth2/v2.0/token
Content-Type: application/x-www-form-urlencoded

continuation_token=uY29tL2F1dGhlbnRpY...
&client_id=00001111-aaaa-2222-bbbb-3333cccc4444 
&grant_type=password 
&password={secure_password}
&scope=openid offline_access 
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |
| `continuation_token` | はい | 前の要求で返された[continuation token](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api#continuation-token)Microsoft Entra。 |
| `grant_type` | はい | 許可の種類。 トークン エンドポイントを呼び出す場合、この値は、ユーザーの最初の要素認証を確認するために、サインイン フローのパスワード認証方法を使用した電子メールの  - *password* である必要があります。  - サインイン フローでの電子メール ワンタイム パスコード認証方法の *oob*。  - *サインアップ* フロー後の自動サインインのcontinuation\_token。  - セルフサービス パスワード リセット フロー後の自動サインインの*continuation\_token*。  - *強力* な [認証方法の登録フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api#register-a-strong-authentication-method-api-reference)の後にcontinuation\_tokenします。  - ユーザーの第 2 要素認証を確認するときに*mfa\_oob*します。 |
| `scope` | はい | スコープのスペース区切りリスト。 すべてのスコープは、 *プロファイル*、 *openid*、 *電子メール*などの OpenID Connect (OIDC) スコープと共に、1 つのリソースからのスコープである必要があります。 アプリは、ID トークンを発行するためにMicrosoft Entra*openid* スコープを含める必要があります。 Microsoft Entraが更新トークンを発行するには、アプリに *offline\_access* スコープを含める必要があります。 [Microsoft ID プラットフォームでのアクセス許可と同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)に関する詳細をご覧ください。 |
| `password` | いいえ | アプリがユーザーから収集するパスワード値。 `{secure_password}`を、アプリがユーザーから収集するパスワード値に置き換えます。 認証方法がパスワード付きの電子メールである場合は、このパラメーターが **必要** です。 |
| `oob` | いいえ | ユーザーが電子メールで受信するワンタイム パスコード。 必須の場合:  - プライマリ認証方法は電子メール ワンタイム パスコードです。  - プライマリ認証方法がパスワード付きの電子メールである場合、アプリは MFA チャレンジを満たすために電子メール ワンタイム パスコードを送信しています。  ワンタイム パスコードを再送信するには、 `/challenge` エンドポイントをもう一度呼び出します。 |
| `username` | いいえ | contoso-consumer@contoso.comなど、サインアップするユーザーの電子メール。 このパラメーターは、サインアップ フローで **必要** です。 |

##### 成功応答

アップロードの成功の例を次に示します:

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{
    "token_type": "Bearer",
    "scope": "openid profile",
    "expires_in": 4141,
    "access_token": "eyJ0eXAiOiJKV1Qi...",
    "refresh_token": "AwABAAAA...",
    "id_token": "eyJ0eXAiOiJKV1Q..."
}
```

| 財産 | 説明 |
| --- | --- |
| `token_type` | トークンの種類の値を示します。 Microsoft Entraがサポートする唯一の型は、*Bearer*です。 |
| `scopes` | アクセス トークンが有効なスコープのスペース区切りのリスト。 |
| `expires_in` | アクセス トークンが有効な時間の長さ (秒単位) です。 |
| `access_token` | アプリが `/token` エンドポイントから要求したアクセス トークン。 アプリはこのアクセス トークンを使用して、Web API などのセキュリティで保護されたリソースへのアクセスを要求できます。 |
| `refresh_token` | OAuth 2.0 更新トークン。 現在のアクセス トークンの有効期限が切れた後に他のアクセス トークンを取得するために、アプリでこのトークンを使用できます。 更新トークンの有効期間は長期です。 これは、リソースへのアクセスを長期間維持できます。 アクセス トークンの更新の詳細については、「アクセス トークン [の更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#refresh-the-access-token)」を参照してください。 **注**: *offline\_access* スコープを要求した場合にのみ発行されます。 |
| `id_token` | ユーザーを識別するために使用される JSON Web トークン (Jwt)。 アプリはトークンをデコードして、サインインしたユーザーに関する情報を要求できます。 アプリではこの値をキャッシュして表示できます。また、機密クライアントでは認可にこのトークンを使用できます。 ID トークンの詳細については、 [Microsoft ID プラットフォームの ID トークンを](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)参照してください。**注**: *openid* スコープを要求した場合にのみ発行されます。 |

##### エラー応答

例:

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{ 
    "error": "invalid_grant", 
    "error_description": "AADSTS901007: Error validating credentials due to invalid username or password.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: yyyy-...",
    "error_codes": [ 
        50126 
    ], 
    "timestamp": "yyyy-mm-dd 10:15:00Z",
    "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333", 
    "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd"
} 
```

| 財産 | 説明 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対応するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `invalid_request` | パラメーター検証の要求に失敗しました。 何が起こったかを理解するには、エラーの説明のメッセージを使用します。 |
| `invalid_grant` | 要求に含まれる継続トークンが無効です。要求に含まれているユーザー サインイン資格情報が無効であるか、ユーザーからさらに操作が必要であるか、要求に含まれる許可の種類が不明です。 |
| `invalid_client` | 要求に含まれるクライアント ID がパブリック クライアント用ではありません。 |
| `expired_token` | 要求に含まれる継続トークンの有効期限が切れています。 |
| `invalid_scope` | 要求に含まれる 1 つ以上のスコープが無効です。 |
| `unauthorized_client` | 要求に含まれるクライアント ID が無効であるか、存在しません。 |
| `unsupported_grant_type` | 要求に含まれる許可の種類がサポートされていないか、正しくありません。 |

エラー パラメーターの値が *invalid\_grant* の場合、Microsoft Entraは応答に `suberror` プロパティを含めます。 invalid\_grant `suberror` プロパティの値を次に示します。

| サブエラー値 | 説明 |
| --- | --- |
| `invalid_oob_value` | アプリが送信するワンタイム パスコードの値が無効です。 |
| `mfa_required` | MFA が必要です。 応答には [継続トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api#continuation-token)が含まれます。 `oauth2/v2.0/introspect` エンドポイントを呼び出して、ユーザーの登録済みの強力な認証方法を取得します。 **このサブエラーは、ユーザーのプライマリ認証方法がパスワード付きの電子メールである場合にのみ発生します。**[ユーザーが登録した強力な認証方法を取得する方法について説明します](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api#get-user-registered-strong-authentication-methods)。 **注**: 場合によっては MFA が必要ですが、ネイティブ認証では `mfa_required`が返されません。 たとえば、 [強力な認証方法の登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api#register-a-strong-authentication-method-api-reference) フローが `/oauth2/v2.0/token` の呼び出しの前にあり、そのフロー中に使用可能な唯一のメソッド (電子メール) が既に検証されている場合です。 |
| `registration_required` | ユーザーは MFA チャレンジを完了する必要がありますが、強力な認証方法が登録されていません。 ユーザーに登録を求めるメッセージを表示します。 **このエラーは、ユーザーのプライマリ認証方法がパスワード付きの電子メールである場合に発生します。**[強力な認証方法を登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api#register-a-strong-authentication-method-api-reference)方法について説明します。 |

##### リダイレクト応答

クライアント アプリが、Microsoft Entra必要な認証方法または機能をサポートしていない場合は、Web ベースの認証フローへのフォールバックが必要です。 このシナリオでは、Microsoft Entraは応答で *redirect* チャレンジの種類を返すことによってアプリに通知します。

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{     
    "challenge_type": "redirect",
    "redirect_reason": "Client is missing registration_required capability"
} 
```

| 財産 | 説明 |
| --- | --- |
| `challenge_type` | Microsoft Entraはチャレンジの種類を持つ応答を返します。 このチャレンジ型の値は redirect です。これにより、アプリは Web ベースの認証フローを使用できます。 |
| `redirect_reason` | リダイレクトが必要な理由。 たとえば、Microsoft Entraは、MFA または強力な認証方法の登録が必要であることを検出しますが、アプリでは要求にこれらの機能が含まれていませんでした。 |

この応答は成功と見なされますが、アプリは Web ベースの認証フローに切り替える必要があります。 この場合は、[Microsoft が構築し、サポートされている認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用することをおすすめします。

#### ユーザーが登録した強力な認証方法を取得する

`oauth2/v2.0/introspect` エンドポイントを使用して、登録されている強力な認証方法のユーザーの一覧を要求します。

要求の例を次に示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/oauth2/v2.0/introspect
Content-Type: application/x-www-form-urlencoded
continuation_token=uY29tL2F1dGhlbnRpY...
&client_id=00001111-aaaa-2222-bbbb-3333cccc4444
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |
| `continuation_token` | はい | 前の要求で返されたcontinuation tokenMicrosoft Entra。 |

##### 成功応答

例:

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{
    "continuation_token": "uY29tL2F1dGhlbnRpY...",
    "methods":[
        {
            "id":"0a0a0a0a-1111-bbbb-2222-3c3c3c3c3c3c",
            "challenge_type":"oob",
            "challenge_channel":"email",
            "login_hint":"c***r@co**o**o.com"
        },
        {   
          "id": "1b1b1b1b-2222-cccc-3333-4d4d4d4d4d4d",   
          "challenge_type": "oob",   
          "challenge_channel": "sms",   
          "login_hint": "+1********6"
        }
    ]
}
```

| 財産 | 説明 |
| --- | --- |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |
| `methods` | ユーザーが登録した強力な認証方法の一覧 (オブジェクト)。 |

MFA メソッド オブジェクトには、次のプロパティがあります。

| 財産 | 説明 |
| --- | --- |
| `id` | MFA メソッドの自動生成された一意の文字列識別子。 アプリは、`id` エンドポイントを呼び出すときに`/oauth2/v2.0/challenge`としてこの文字列を使用します。 |
| `challenge_type` | MFA メソッドとして使用するユーザーに対して選択されたチャレンジの種類。 現在サポートされているチャレンジの種類は *oob です*。 |
| `challenge_channel` | MFA メソッドが送信されるチャネルの種類。 現在サポートされているチャレンジ チャネルは *電子メールです*。 |
| `login_hint` | 難読化された電子メールなどの強力な認証方法のヒント。 |

##### リダイレクト応答

クライアント アプリが、Microsoft Entra必要な認証方法または機能をサポートしていない場合は、Web ベースの認証フローへのフォールバックが必要です。 このシナリオでは、Microsoft Entraは応答で *redirect* チャレンジの種類を返すことによってアプリに通知します。

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{     
   "challenge_type": "redirect" 
} 
```

##### エラー応答

例:

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{ 
    "error": "invalid_request", 
    "error_description": "AADSTS901007: The continuation_token provided is not valid for this endpoint.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: yyyy-...",
    "error_codes": [ 
        50126 
    ], 
    "timestamp": "yyyy-mm-dd 10:15:00Z",
    "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333", 
    "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd"
} 
```

| 財産 | 説明 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `invalid_request` | パラメーター検証の要求に失敗しました。 何が起こったかを理解するには、エラーの説明のメッセージを使用します。 |
| `invalid_client` | 要求に含まれるクライアント ID がパブリック クライアント用ではありません。 |
| `expired_token` | 要求に含まれる継続トークンの有効期限が切れています。 |
| `server_error` | 要求に問題が発生しました。 |

クライアント アプリがユーザーに登録されている強力な認証方法の一覧を正常に取得すると、ユーザーは MFA チャレンジを完了するために使用する方法を選択します。 その後、フローは次のように進みます。

1. クライアント アプリは `/oauth2/v2.0/challenge` を呼び出し、 `/oauth2/v2.0/introspect` から取得した継続トークンと、選択した MFA メソッドの `id` を含めます。

    ```http
    POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/oauth2/v2.0/challenge
    Content-Type: application/x-www-form-urlencoded    
    client_id=00001111-aaaa-2222-bbbb-3333cccc4444
    &id=0a0a0a0a-1111-bbbb-2222-3c3c3c3c3c3c 
    &continuation_token=uY29tL2F1dGhlbnRpY... 
    ```
2. Microsoft Entra、電子メールなどのチャレンジ コードをユーザーのチャレンジ チャネルに送信し、継続トークンと MFA チャレンジの詳細を使用してクライアント アプリに応答します。

    ```http
    HTTP/1.1 200 OK
    Content-Type: application/json
    ```

    ```json
    {
        "continuation_token": "uY29tL2F1dGhlbnRpY...",
        "challenge_type": "oob",
        "binding_method": "prompt ", 
        "challenge_channel": "email",
        "challenge_target_label ": "c***r@co**o**o.com ",
        "code_length": 8
    } 
    ```
3. アプリは、 `/oauth2/v2.0/token` エンドポイントに POST 要求を行い、継続トークン、正しい許可の種類、対応する許可の種類の値を含め、セキュリティ トークンを取得できるようになりました。 セキュリティ トークンの要求で想定される応答を参照してください。

    ```http
    POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/oauth2/v2.0/token
    Content-Type: application/x-www-form-urlencoded    
    client_id=00001111-aaaa-2222-bbbb-3333cccc4444 
    &continuation_token=uY29tL2F1dGhlbnRpY...   
    &grant_type=mfa_oob  
    &oob={otp_code}
    &scope=openid offline_access
    ```

### 強力な認証方法の API リファレンスを登録する

ネイティブ認証では、強力な認証方法の登録がサポートされます。 アプリが /oauth2/v2.0/token エンドポイントを呼び出し、MFA が必要であるが、ユーザーに厳密なメソッドが登録されていない場合、応答 *(registeration\_required*) は、トークンを発行する前にユーザーに登録するようアプリに指示します。

クライアント アプリは、強力な認証方法を登録するフローを完了した後、 `/oauth2/v2.0/token` エンドポイントを呼び出してセキュリティ トークンを要求します。

#### 強力な認証方法の登録エンドポイント

強力な認証方法登録 API を使用するために、アプリは次の表に示すエンドポイントを使用します。

| エンドポイント | 説明 |
| --- | --- |
| `/register/v1.0/introspect` | このエンドポイントを呼び出して、ユーザーが登録できる強力な認証方法の一覧をフェッチします。 |
| `/register/v1.0/challenge` | このエンドポイントを呼び出して、電子メールワンタイム パスコードなど、チャレンジをユーザーに送信します。 |
| `/register/v1.0/continue` | このエンドポイントを呼び出して、アプリがユーザーから収集するチャレンジ (ワンタイム パスコードなど) を送信して、強力な認証方法を登録するフローを完了します。 呼び出しが成功し、継続トークンを取得したら、 `/oauth2/v2.0/token` エンドポイントを呼び出してセキュリティ トークンを要求します。 トークン エンドポイントを呼び出す方法について説明します。 |

#### 手順 1: 強力な認証方法の一覧を取得する

登録フローは、ユーザーが登録を許可されている強力な認証方法の一覧をアプリが要求したときに開始されます。

要求の例を次に示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/register/v1.0/introspect
Content-Type: application/x-www-form-urlencoded
?continuation_token=uY29tL2F1dGhlbnRpY... 
&client_id=00001111-aaaa-2222-bbbb-3333cccc4444  
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |

##### 成功応答

アップロードの成功の例を次に示します:

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{
    "continuation_token": "uY29tL2F1dGhlbnRpY...",
    "methods":[
        {
            "id":"email",
            "challenge_type":"oob",
            "challenge_channel":"email",
            "login_hint":"caseyjensen@contoso.com"
        },
        {   
          "id": "sms",   
          "challenge_type": "oob",   
          "challenge_channel": "sms"
        }
    ]
}
```

| 財産 | 説明 |
| --- | --- |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |
| `methods` | ユーザーが登録できる強力な認証方法の (オブジェクトの) 一覧。 |

強力な認証方法オブジェクトには、次のプロパティがあります。

| 財産 | 説明 |
| --- | --- |
| `id` | メソッドの文字列キー。 サポートされている値 *の電子メール、SMS*。 |
| `challenge_type` | MFA メソッドとして使用するユーザーに対して選択されたチャレンジの種類。 現在サポートされているチャレンジの種類は *oob です*。 |
| `challenge_channel` | MFA メソッドが送信されるチャネルの種類。 サポートされている値 *の電子メール、SMS*。 |
| `login_hint` | 電子メールなどの強力な認証方法のヒント。 この値は、クライアント アプリによって電子メール テキスト ボックスを事前入力するために使用されます。 |

##### エラー応答

例:

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{ 
    "error": "invalid_request", 
    "error_description": "AADSTS901007: The continuation_token provided is not valid for this endpoint.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: yyyy-...",
    "error_codes": [ 
        50126 
    ], 
    "timestamp": "yyyy-mm-dd 10:15:00Z",
    "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333", 
    "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd"
} 
```

| 財産 | 説明 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `invalid_request` | *継続トークン*の検証に失敗したか、要求に `client_id` パラメーターが含まれていない、クライアント ID 値が空か無効か、外部テナント管理者がすべてのテナント ユーザーに対してメール OTP を有効にしていないなど、要求パラメーターの検証に失敗しました。 |
| `expired_token` | 要求に含まれる継続トークンの有効期限が切れています。 |

#### 手順 2: 強力な認証方法を選択する

この手順では、ユーザーが登録する強力な認証方法を送信します。 Microsoft Entra、電子メールワンタイム パスコードなどのチャレンジをユーザーに送信します。

要求の例を次に示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/register/v1.0/challenge 

?continuation_token=uY29tL2F1dGhlbnRpY... 
&client_id=00001111-aaaa-2222-bbbb-3333cccc4444  
&challenge_type=oob  
&challenge_channel=email 
&challenge_target=contoso-consumer@contoso.com 
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |
| `continuation_token` | はい | continuation トークン`/register/v1.0/introspect ` エンドポイントから返Microsoft Entra |
| `challenge_type` | はい | 認証方法のチャレンジの種類。 現在の型は *oob* です。 |
| `challenge_target` | はい | ユーザーが登録する電子メールまたは電話番号。 |
| `challenge_channel` | いいえ | チャレンジを送信するチャネル。 サポートされているチャレンジ チャネルの値: *電子メール、SMS*。 |

##### 成功応答

成功した応答の例を次に示します。

例 1:

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{ 
  "continuation_token": "uY29tL2F1dGhlbnRpY...", 
  "challenge_type": "oob", 
  "binding_method": "prompt", 
  "challenge_target": "contoso-consumer@contoso.com", 
  "challenge_channel": "email", 
  "code_length": 8 
} 
```

例 2:

サインアップ フローが強力な認証方法の登録フローの前にあり、 `/register/v1.0/challenge` エンドポイントに送信された電子メールがサインアップ フローで検証されたものと一致する場合、ネイティブ認証 API はチャレンジをユーザーに送信せずにメソッドを登録します。 この場合、応答は次のスニペットのようになります。

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{ 
  "continuation_token": "uY29tL2F1dGhlbnRpY...",
  "challenge_type": "preverified" 
}
```

| 財産 | 説明 |
| --- | --- |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |
| `challenge_type` | 認証に使用するユーザーに対して選択されたチャレンジの種類 ( *oob* など)、強力な認証方法が事前検証された場合は *事前* 検証済み。 |
| `binding_method` | 有効な値は *prompt* だけです。 このパラメータは、将来的に、さらに多くのワンタイム パスコード入力方法をユーザーに提供するために使用できます。 `challenge_type`が *oob* であり、強力な認証方法が事前検証されていない場合に発行されます。 |
| `challenge_channel` | ワンタイム パスコードが送信されたチャネルの種類。 サポートされている値 *の電子メール、SMS*。 強力な認証方法が事前検証されていない場合に返されます。 |
| `code_length` | `binding_method`がプロンプトの場合のワンタイム パスコードの長さ。 強力な認証方法が事前検証されていない場合に返されます。 |
| `challenge_target` | チャレンジが送信されたターゲット。 これは、要求で指定された入力と同じです。 強力な認証方法が事前検証されていない場合に返されます。 |
| `interval` | /register/continue のポーリングの間にクライアントが待機する間隔 (秒単位)。 `prompt=none`し、強力な認証方法が事前検証されていない場合にのみ返されます。 クライアントは、ネイティブ認証 API から `HTTP 429` を受け取るたびに間隔を 2 倍にする必要があります。 |

##### エラー応答

ここでのエラーは、 `/register/v1.0/introspect` エンドポイントを呼び出すときに発生する可能性があるエラーと似ています。 ただし、電話番号を登録するときに、電話番号が高リスクと見なされる場合は、要求がブロックされる可能性があります。

要求がブロックされた場合に発生する可能性のあるエラーを次に示します。

| エラー値 | 説明 |
| --- | --- |
| `access_denied` | SMS がブロックされました。 |

エラー パラメーターの値が *access\_denied* の場合、Microsoft Entraは応答にサブエラー プロパティを含めます。 invalid\_grant エラーのサブエラー プロパティの値を次に示します。

| サブエラー値 | 説明 |
| --- | --- |
| `provider_blocked_by_admin` | テナント管理者が電話リージョンをブロックしました。 |
| `provider_blocked_by_rep` | 多要素認証方法がブロックされています (電話番号は RepMap によってブロックされました)。 |

#### 手順 3: チャレンジを送信する

この手順では、 `/register/v1.0/continue` エンドポイントを呼び出して、強力な認証方法の登録を完了します。

要求の例を次に示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/register/v1.0/continue 

?continuation_token=uY29tL2F1dGhlbnRpY... 
&client_id=00001111-aaaa-2222-bbbb-3333cccc4444  
&grant_type=oob  
&oob={otp_code}
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `continuation_token` | はい | 前の要求で返されたcontinuation tokenMicrosoft Entra。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |
| `grant_type` | はい | 許可の種類。 現在サポートされている値は *oob* *です。*`/register/v1.0/challenge` エンドポイントで強力な認証方法が事前検証される場合はcontinuation\_token。 |
| `oob` | いいえ | 顧客ユーザーがメールで受け取ったワンタイム パスコード。 `{otp_code}` を顧客ユーザーがメールで受け取ったワンタイム パスコードの値に置き換えます。 **ワンタイム パスコードを再送信**するには、アプリで `/register/v1.0/challenge` エンドポイントにもう一度要求を行う必要があります。 `/register/v1.0/challenge` エンドポイントで強力な認証方法が事前検証されていない場合に必要です。 |

##### 成功応答

アップロードの成功の例を次に示します:

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{ 
  "continuation_token": "uY29tL2F1dGhlbnRpY..."
} 
```

| 財産 | 説明 |
| --- | --- |
| `continuation_token` | Microsoft Entraが返すコンティニュエーション トークン。 この継続トークンを使用して、 `/oauth2/v2.0/token` エンドポイントを呼び出してセキュリティ トークンを要求します。 トークン エンドポイントを呼び出す方法について説明します。 |

##### エラー応答

例:

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{ 
    "error": "invalid_request", 
    "error_description": "AADSTS55200: The continuation_token is invalid.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: yyyy-...",
    "error_codes": [ 
        55200 
    ], 
    "timestamp": "yyyy-mm-dd 10:15:00Z",
    "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333", 
    "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd"
}
```

| 財産 | 説明 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `invalid_request` | *継続トークン*の検証に失敗したか、要求に `client_id` パラメーターが含まれていない、クライアント ID 値が空か無効か、外部テナント管理者がすべてのテナント ユーザーに対してメール OTP を有効にしていないなど、要求パラメーターの検証に失敗しました。 |
| `invalid_grant` | 要求に含まれる許可の種類が有効またはサポートされていないか、OTP 値が正しくありません。 |
| `expired_token` | 要求に含まれる継続トークンの有効期限が切れています。 |

エラー パラメーターの値が *invalid\_grant* の場合、Microsoft Entraは応答に `suberror` プロパティを含めます。 invalid\_grant `suberror` プロパティの値を次に示します。

| サブエラー値 | 説明 |
| --- | --- |
| `invalid_oob_value` | ワンタイム パスコードの値が無効です。 |

### セルフサービス パスワード リセット (SSPR)

**プライマリ認証方法がパスワード付きの電子メールであるユーザーの場合は**、セルフサービス パスワード リセット (SSPR) API を使用して、顧客ユーザーが自分のパスワードをリセットできるようにします。 この API は、パスワードを忘れた場合やパスワードを変更するシナリオに使用できます。

#### セルフサービスパスワードリセット用のAPIエンドポイント

この API を使用するために、アプリは次の表に示すエンドポイントを使用します。

| エンドポイント | 説明 |
| --- | --- |
| `/resetpassword/v1.0/start` | 顧客ユーザーがアプリの **[パスワードを忘れた場合]** または **[パスワードの変更]** リンクまたはボタンを選択すると、アプリによってこのエンドポイントが呼び出されます。 このエンドポイントは、ユーザーのユーザー名 (電子メール) を検証し、パスワード リセット フローで使用する *継続トークン* を返します。 アプリがMicrosoft Entraでサポートされていない認証方法の使用を要求した場合、このエンドポイント応答は、ブラウザー ベースの認証フローを使用する必要があることをアプリに示すことができます。 |
| `/resetpassword/v1.0/challenge` | クライアントと*継続トークン*でサポートされているチャレンジ型の一覧を受け入れます。 優先する復旧資格情報のいずれかにチャレンジが発行されます。 たとえば、oob チャレンジは、顧客ユーザー アカウントに関連付けられているメール アドレスに帯域外ワンタイム パスコードを発行します。 アプリがMicrosoft Entraでサポートされていない認証方法の使用を要求した場合、このエンドポイント応答は、ブラウザー ベースの認証フローを使用する必要があることをアプリに示すことができます。 |
| `/resetpassword/v1.0/continue` | `/resetpassword/v1.0/challenge` エンドポイントによって発行されたチャレンジを検証し、 エンドポイントの`/resetpassword/v1.0/submit`を返すか、ユーザーに別のチャレンジを発行します。 |
| `/resetpassword/v1.0/submit` | ユーザーによる新しいパスワード入力と*継続トークン*を受け入れて、パスワード リセット フローを完了します。 このエンドポイントは、別の*継続トークン*を発行します。 |
| `/resetpassword/v1.0/poll_completion` | アプリは、 エンドポイントによって発行された`/resetpassword/v1.0/submit`を使用して、パスワード リセット要求の状態を確認できます。 |
| `oauth2/v2.0/token` | パスワードのリセットが成功した場合、アプリは、 `/resetpassword/v1.0/poll_completion` エンドポイントから取得した継続トークンを使用して、 `oauth2/v2.0/token` エンドポイントからセキュリティ トークンを取得できます。 |

#### セルフサービス パスワード リセット チャレンジ型

API を使用すると、アプリは、Microsoft Entraの呼び出しを行うときに、サポートする認証方法をアドバタイズできます。 これを行うために、アプリはその要求に `challenge_type` パラメーターを使用します。 このパラメーターには、さまざまな認証方法を表す定義済みの値が保持されます。

SSPR フローの場合、チャレンジ型の値は oob および redirect です。

チャレンジ型の詳細については、「[ネイティブ認証のチャレンジ型](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication-challenge-types)」を参照してください。

#### セルフサービス パスワード リセット フロー プロトコルの詳細

シーケンス図は、パスワード リセット プロセスのフローを示しています。

[Image: ネイティブ認証のセルフサービス パスワード リセットのフローを示す図。]

この図は、アプリがユーザーのユーザー名 (メール) とパスワードを異なるタイミングで (場合によっては別の画面で) 収集することを示しています。 ただし、同じ画面でユーザー名 (メール) と新しいパスワードを収集するようにアプリを設計できます。 この場合、アプリはパスワードを保持し、必要に応じて `/resetpassword/v1.0/submit` エンドポイントを介して送信します。

#### ステップ 1: セルフサービス パスワード リセット フローの開始を要求する

パスワード リセット フローは、アプリがエンドポイントに `/resetpassword/v1.0/start` POST 要求を行ってセルフサービス パスワード リセット フローを開始することから始まります。

要求の例を次に示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/resetpassword/v1.0/start
Content-Type: application/x-www-form-urlencoded
client_id=00001111-aaaa-2222-bbbb-3333cccc4444 
&challenge_type=oob redirect 
&username=contoso-consumer@contoso.com 
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |
| `username` | はい | *contoso-consumer@contoso.com* などの顧客ユーザーのメール。 |
| `challenge_type` | はい | アプリがサポートする認証チャレンジ型文字列のスペース区切りのリスト (例: `oob password redirect`). リストには、常に `redirect` チャレンジ型が含まれている必要があります。 この要求では、値に`oob redirect` を含める必要があります。 |
| `capabilities` | いいえ | クライアント アプリの "方法" の準備状況を説明するスペース区切りのフラグ。 `challenge_type`はチャレンジできる方法を定義しますが、`capabilities`は、クライアント アプリが処理できる追加フローと表示できる UI をネイティブ認証 API に指示します。 たとえば、 `mfa_required` は別の `/challenge` と `/token` ループを意味します。 `registration_required` は、クライアント アプリが登録 API を呼び出し、登録 UI を表示します。 必要な機能がクライアント アプリによってアドバタイズされていない場合、API はリダイレクトを返します。 サポートされている値は、`mfa_required` と `registration_required`です。 [機能の詳細を確認します](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-challenge-types)。 |

##### 成功応答

例:

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{
    "continuation_token": "uY29tL2F1dGhlbnRpY..."
}
```

| 財産 | 説明 |
| --- | --- |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |

##### リダイレクト応答

クライアント アプリが、Microsoft Entra必要な認証方法または機能をサポートしていない場合は、Web ベースの認証フローへのフォールバックが必要です。 このシナリオでは、Microsoft Entraは応答で *redirect* チャレンジの種類を返すことによってアプリに通知します。

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{     
   "challenge_type": "redirect" 
} 
```

| 財産 | 説明 |
| --- | --- |
| `challenge_type` | Microsoft Entraはチャレンジの種類を持つ応答を返します。 このチャレンジ型の値は redirect です。これにより、アプリは Web ベースの認証フローを使用できます。 |

この応答は成功と見なされますが、アプリは Web ベースの認証フローに切り替える必要があります。 この場合は、[Microsoft が構築し、サポートされている認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用することをおすすめします。

##### エラー応答

例:

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{ 
    "error": "invalid_request", 
    "error_description": "AADSTS901007: The challenge_type list parameter does not include the 'redirect' type.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: yyyy-...",
    "error_codes": [ 
        901007 
    ], 
    "timestamp": "yyyy-mm-dd 10:15:00Z",
    "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333", 
    "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd"
} 
```

| 財産 | 説明 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `invalid_request` | `challenge_type` パラメーターに無効なチャレンジ型が含まれる場合、または要求に `client_id` パラメーターが含まれない場合、クライアント ID 値が空または無効な場合など、要求パラメーター認証が失敗しました。 `error_description` パラメーターを使用して、エラーの正確な原因を確認します。 |
| `user_not_found` | username が存在しません。 |
| `unsupported_challenge_type` | `challenge_type` パラメーター値には `redirect` チャレンジ型が含まれていません。 |
| `invalid_client` | アプリが要求に含めるクライアント ID は、パブリック クライアントではない、ネイティブ認証が有効になっていないなど、ネイティブ認証構成がないアプリ用です。 `suberror` プロパティを使用して、エラーの正確な原因を確認します。 |
| `unauthorized_client` | 要求で使用されるクライアント ID には有効なクライアント ID 形式がありますが、外部テナントに存在しないか、正しくありません。 |

エラー パラメーターの値が *invalid\_client* の場合、Microsoft Entraは応答に `suberror` プロパティを含めます。 invalid\_client エラーの `suberror` プロパティの値を次 *に* 示します。

| サブエラー値 | 説明 |
| --- | --- |
| `nativeauthapi_disabled` | ネイティブ認証が有効になっていないアプリのクライアント ID。 |

#### ステップ 2: 認証方法を選びます

フローを続行するために、アプリは前の手順で取得した継続トークンを使用してMicrosoft Entraを要求し、ユーザーが認証に使用するためにサポートされているチャレンジの種類のいずれかを選択します。 アプリが `/resetpassword/v1.0/challenge` エンドポイントに POST 要求を行います。 この要求が成功した場合、Microsoft Entraはユーザーのアカウント電子メールにワンタイム パスコードを送信します。 現時点では、メール OTP のみがサポートされています。

次に例を示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/resetpassword/v1.0/challenge
Content-Type: application/x-www-form-urlencoded
client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&challenge_type=oob redirect
&continuation_token=uY29tL2F1dGhlbnRpY... 
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |
| `continuation_token` | はい | 前の要求で返されたcontinuation tokenMicrosoft Entra。 |
| `challenge_type` | いいえ | アプリがサポートする認証チャレンジ型文字列のスペース区切りのリスト (例: `oob redirect`). リストには、常に `redirect` チャレンジ型が含まれている必要があります。 この要求では、値に`oob redirect` を含める必要があります。 |

##### 成功応答

例:

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{
    "continuation_token": "uY29tL2F1dGhlbnRpY...",
    "challenge_type": "oob",
    "binding_method": "prompt ", 
    "challenge_channel": "email",
    "challenge_target_label ": "c***r@co**o**o.com ",
    "code_length": 8
} 
```

| 財産 | 説明 |
| --- | --- |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |
| `challenge_type` | 認証するユーザーに選択されたチャレンジ型。 |
| `binding_method` | 有効な値は *prompt* だけです。 このパラメータは、将来的に、さらに多くのワンタイム パスコード入力方法をユーザーに提供するために使用できます。 `challenge_type` が *oob* の場合発行されます |
| `challenge_channel` | ワンタイム パスコードが送信されたチャネルの種類。 現時点では、メールがサポートされています。 |
| `challenge_target_label` | ワンタイム パスコードが送信された難読化されたメール。 ユーザーに対して MFA が有効になっている場合、ワンタイム パスコードを含む電子メールが次のアドレスに送信されます。  - 電子メール アドレスがアカウントの電子メール アドレスと異なる場合に、強力な認証方法として使用される電子メール アドレス。  - 強力な認証方法が SMS の場合のアカウントの電子メール アドレス。 |
| `code_length` | Microsoft Entra生成されるワンタイム パスコードの長さ。 |

##### リダイレクト応答

クライアント アプリが、Microsoft Entra必要な認証方法または機能をサポートしていない場合は、Web ベースの認証フローへのフォールバックが必要です。 このシナリオでは、Microsoft Entraは応答で *redirect* チャレンジの種類を返すことによってアプリに通知します。

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{     
   "challenge_type": "redirect" 
} 
```

| 財産 | 説明 |
| --- | --- |
| `challenge_type` | Microsoft Entraはチャレンジの種類を持つ応答を返します。 このチャレンジ型の値は redirect です。これにより、アプリは Web ベースの認証フローを使用できます。 |

この応答は成功と見なされますが、アプリは Web ベースの認証フローに切り替える必要があります。 この場合は、[Microsoft が構築し、サポートされている認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用することをおすすめします。

##### エラー応答

例:

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{ 
    "error": "invalid_request", 
    "error_description": "AADSTS901007: The challenge_type list parameter does not include the 'redirect' type.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: yyyy-...",
    "error_codes": [ 
        901007 
    ], 
    "timestamp": "yyyy-mm-dd 10:15:00Z",
    "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333", 
    "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd"
} 
```

| 財産 | 説明 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `invalid_request` | `challenge_type` パラメーターに無効なチャレンジ型が含まれている場合や*継続トークン*の検証に失敗した場合など、要求パラメーターの検証に失敗しました。 |
| `expired_token` | 継続トークンが期限切れです。 |
| `unsupported_challenge_type` | `challenge_type` パラメーター値には `redirect` チャレンジ型が含まれていません。 |

#### 手順 3: ワンタイム パスコードを送信する

その後、アプリは `/resetpassword/v1.0/continue` エンドポイントに POST 要求を行います。 要求には、前のステップで選択したユーザーの資格情報と、`/resetpassword/v1.0/challenge` エンドポイントから発行された継続トークンをアプリに含める必要があります。

要求の例を次に示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/resetpassword/v1.0/continue
Content-Type: application/x-www-form-urlencoded
continuation_token=uY29tL2F1dGhlbnRpY... 
&client_id=00001111-aaaa-2222-bbbb-3333cccc4444 
&grant_type=oob 
&oob={otp_code}
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `continuation_token` | はい | 前の要求で返されたcontinuation tokenMicrosoft Entra。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |
| `grant_type` | はい | 有効な値は *oob* だけです。 |
| `oob` | はい | 顧客ユーザーがメールで受け取ったワンタイム パスコード。 `{otp_code}` を顧客ユーザーがメールで受け取ったワンタイム パスコードに置き換えます。 **ワンタイム パスコードを再送信**するには、アプリで `/resetpassword/v1.0/challenge` エンドポイントにもう一度要求を行う必要があります。 |

##### 成功応答

例:

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{ 
    "expires_in": 600,
    "continuation_token": "czZCaGRSa3F0MzpnW...",
} 
```

| 財産 | 説明 |
| --- | --- |
| `expires_in` | *continuation\_token* の有効期限が切れるまでの時間 (秒単位)。 `expires_in` の最大値は **600 秒**です。 |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |

##### エラー応答

例:

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{ 
    "error": "invalid_request", 
    "error_description": "AADSTS55200: The continuation_token is invalid.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: yyyy-...",
    "error_codes": [ 
        55200 
    ], 
    "timestamp": "yyyy-mm-dd 10:15:00Z",
    "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333", 
    "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd"
} 
```

| 財産 | 説明 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |
| `suberror` | エラーの種類を詳細に分類するのに使用できるエラー コード文字列。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `invalid_request` | *継続トークン*の検証に失敗したか、要求に `client_id` パラメーターが含まれていないか、クライアント ID 値が空か無効か、外部テナント管理者がすべてのテナント ユーザーに対して SSPR とメール OTP を有効にしていないなど、要求パラメーターの検証に失敗しました。 `error_description` パラメーターを使用して、エラーの正確な原因を確認します。 |
| `invalid_grant` | 許可の種類が不明であるか、予想される許可の種類の値と一致しません。 `suberror` プロパティを使用して、エラーの正確な原因を確認します。 |
| `expired_token` | 継続トークンが期限切れです。 |

エラー パラメーターの値が *invalid\_grant* の場合、Microsoft Entraは応答に `suberror` プロパティを含めます。 invalid\_grant `suberror` プロパティの値を次に示します。

| サブエラー値 | 説明 |
| --- | --- |
| `invalid_oob_value` | ワンタイム パスコードの値が無効です。 |

#### ステップ 4: 新しいパスワードを送信する

アプリはユーザーから新しいパスワードを収集し、 エンドポイントによって発行された`/resetpassword/v1.0/continue`を使用して、`/resetpassword/v1.0/submit` エンドポイントに POST 要求を行ってパスワードを送信します。

次に例を示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/resetpassword/v1.0/submit
Content-Type: application/x-www-form-urlencoded
client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&continuation_token=czZCaGRSa3F0Mzp...
&new_password={new_password}
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `continuation_token` | はい | 前の要求で返されたcontinuation tokenMicrosoft Entra。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |
| `new_password` | はい | ユーザーの新しいパスワード。 `{new_password}` をユーザーの新しいパスワードに置き換えます。 アプリの UI でパスワードの確認フィールドを指定して、ユーザーが使用するパスワードを認識していることを確認するのはユーザーの責任です。 また、組織のポリシーに従って、何が強力なパスワードであるかをユーザーに認識させる必要があります。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 |

##### 成功応答

例:

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{
    "continuation_token": "uY29tL2F1dGhlbnRpY...",
    "poll_interval": 2
}
```

| 財産 | 説明 |
| --- | --- |
| `continuation_token` | Microsoft Entraが返すContinuation token。 |
| `poll_interval` | `/resetpassword/v1.0/poll_completion` エンドポイントを介してパスワード リセット要求の状態を確認するために、ポーリング要求の間にアプリが待機する必要がある最小時間 (秒) (ステップ 5 を参照) |

##### エラー応答

例:

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{ 
    "error": "invalid_request", 
    "error_description": "AADSTS901007: The challenge_type list parameter does not include the 'redirect' type.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: yyyy-...",
    "error_codes": [ 
        901007 
    ], 
    "timestamp": "yyyy-mm-dd 10:15:00Z",
    "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333", 
    "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd"
} 
```

| 財産 | 説明 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |
| `suberror` | エラーの種類を詳細に分類するのに使用できるエラー コード文字列。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `invalid_request` | *継続トークン*の検証に失敗するなど、要求パラメーターの検証に失敗しました。 |
| `expired_token` | *継続トークン*が期限切れです。 |
| `invalid_grant` | 送信されたパスワードが短すぎるなど、送信された許可が無効です。 `suberror` プロパティを使用して、エラーの正確な原因を確認します。 |

エラー パラメーターの値が *invalid\_grant* の場合、Microsoft Entraは応答に `suberror` プロパティを含めます。 `suberror` プロパティで使用できる値を次に示します。

| サブエラー値 | 説明 |
| --- | --- |
| `password_too_weak` | 複雑さの要件を満たしていないため、パスワードが弱すぎます。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 |
| `password_too_short` | 新しいパスワードは 8 文字未満です。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 |
| `password_too_long` | 新しいパスワードが 256 文字を超えています。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 |
| `password_recently_used` | 新しいパスワードは、最近使用したパスワードと同じにすることはできません。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 |
| `password_banned` | 新しいパスワードには、禁止されている単語、語句、またはパターンが含まれています。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 |
| `password_is_invalid` | パスワードは無効です。たとえば、許可されていない文字が使用されているためです。 [Microsoft Entraのパスワード ポリシーの詳細については](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad-combined-policy)を参照してください。 この応答は、アプリがユーザー パスワードを送信した場合に可能です。 |

#### ステップ 5: パスワード リセットの状態をポーリングする

最後に、新しいパスワードを使用してユーザーの構成を更新すると多少の遅延が発生するため、アプリは `/resetpassword/v1.0/poll_completion` エンドポイントを使用して、パスワード リセット状態のMicrosoft Entraをポーリングできます。 ポーリング要求の間にアプリが待機する必要がある最小時間 (秒単位) は、`/resetpassword/v1.0/submit` パラメーターの `poll_interval` エンドポイントから返されます。

次に例を示します (読みやすくするために、要求の例を複数行で示します):

```http
POST https://{tenant_subdomain}.ciamlogin.com/{tenant_subdomain}.onmicrosoft.com/resetpassword/v1.0/poll_completion
Content-Type: application/x-www-form-urlencoded
client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&continuation_token=czZCaGRSa3F0... 
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant_subdomain` | はい | 作成した外部テナントのサブドメイン。 URL では、`{tenant_subdomain}` を、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、プライマリ ドメインが *contoso.onmicrosoft.com* の場合は、*contoso* を使用します。 テナントのサブドメイン名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 |
| `continuation_token` | はい | 前の要求で返されたcontinuation tokenMicrosoft Entra。 |
| `client_id` | はい | Microsoft Entra管理センターに登録したアプリのアプリケーション (クライアント) ID。 |

##### 成功応答

例:

```http
HTTP/1.1 200 OK
Content-Type: application/json
```

```json
{
    "status": "succeeded",
    "continuation_token":"czZCaGRSa3F0..."
} 
```

| 財産 | 説明 |
| --- | --- |
| `status` | パスワード リセット要求の状態。 Microsoft Entraが *failed* の状態を返す場合、アプリは、`/resetpassword/v1.0/submit` エンドポイントに別の要求を行って新しいパスワードを再送信し、新しい継続トークンを含めることができます。 |
| `continuation_token` | Microsoft Entraが返すContinuation token。 状態が *ucceeded* の場合、Microsoft Entraが返す継続トークンを使用して、セキュリティ トークンの`oauth2/v2.0/token` で説明されているように、 エンドポイント経由でセキュリティ トークンを要求できます。 つまり、ユーザーがパスワードを正常にリセットした後は、新しいサインイン フローを開始しなくても、アプリに直接サインインできます。 |

Microsoft Entraが返す可能性のある状態を次に示します (`status` パラメーターの使用可能な値)。

| エラー値 | 説明 |
| --- | --- |
| `succeeded` | パスワードのリセットが正常に完了しました。 |
| `failed` | パスワードのリセットに失敗しました。 アプリは、`/resetpassword/v1.0/submit` エンドポイントに別の要求を行うことで、新しいパスワードを再送信できます。 |
| `not_started` | パスワードのリセットが開始されていません。 アプリは後でもう一度状態を確認できます。 |
| `in_progress` | パスワードのリセットが進行中です。 アプリは後でもう一度状態を確認できます。 |

##### エラー応答

例:

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{ 
    "error": "expired_token", 
    "error_description": "AADSTS901007: The continuation_token is expired.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: yyyy-...",
    "error_codes": [ 
        552003 
    ], 
    "timestamp": "yyyy-mm-dd 10:15:00Z",
    "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333", 
    "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd"
} 
```

| 財産 | 説明 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を特定するのに役立つ特定のエラー メッセージ。 |
| `error_codes` | エラーの診断に役立つMicrosoft Entra固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | エラーの診断に役立つ要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |

発生する可能性があるエラー ( `error` プロパティの値) を次に示します。

| エラー値 | 説明 |
| --- | --- |
| `invalid_request` | *継続トークン*の検証に失敗するなど、要求パラメーターの検証に失敗しました。 |
| `expired_token` | *継続トークン*が期限切れです。 |

#### パスワードのリセット後に自動的にサインインする

パスワードのリセットが成功した後にユーザーがサインインする必要がある場合。 アプリは、 `/oauth2/v2.0/token` エンドポイントを呼び出す必要があります。 トークン エンドポイントを呼び出す方法について説明します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/reference-oidc-extensibility"} -->
## MICROSOFT IDENTITY PLATFORM OIDC 拡張機能リファレンス - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-oidc-extensibility
- Service: identity-platform
- Article date: 2026-06-23
- Summary: OpenID Connect (OIDC) 拡張機能の各Microsoft ID プラットフォームを、構成記事と、それをプログラムする Microsoft Graph API リソースにマップします。

この参照を使用して、OpenID Connect (OIDC) の動作Microsoft ID プラットフォーム拡張するためにサポートされているすべての方法を見つけます。 各行は、このリポジトリの概念またはハウツー記事と、サーフェスをプログラムする Microsoft Graph API リソースにリンクしています。

拡張性とは、OIDC トークンMicrosoft Entra発行したり、所有しているアプリの OIDC 要求を処理したりする方法を変更することです。たとえば、外部ストアからの要求の追加、アプリごとのトークン コンテンツのカスタマイズ、外部ワークロード ID からのトークンの信頼などです。 サインインにMicrosoft Entraを使用するように既存の OIDC アプリ (GitHub、Salesforce、または別の SaaS アプリなど) を構成することは、拡張機能ではなく*統合*です。 アプリ統合ガイダンスについては、[アプリケーション ギャラリー Microsoft Entra参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/overview-application-gallery)。

基になるエンドポイント コントラクトについては、[Microsoft ID プラットフォームの OpenID Connect を](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)参照してください。

### 拡張性サーフェスの概要

| 能力 | できること | 概念と使い方 | Microsoft Graph API |
| --- | --- | --- | --- |
| カスタム クレーム プロバイダー | トークンの発行中に外部 REST API を呼び出して、リモート ストアからの要求でトークンを強化します。 | [カスタム クレーム プロバイダーの概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview)、 [リファレンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-reference) | [customAuthenticationExtension](https://learn.microsoft.com/ja-jp/graph/api/resources/customauthenticationextension), [onTokenIssuanceStartListener](https://learn.microsoft.com/ja-jp/graph/api/resources/ontokenissuancestartlistener) |
| トークン発行開始イベント | トークンの発行中にカスタム クレーム プロバイダーをトリガーするイベント リスナーを構成します。 | [トークン発行開始イベントの設定](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-setup)、 [構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration) | [onTokenIssuanceStartCustomExtension](https://learn.microsoft.com/ja-jp/graph/api/resources/ontokenissuancestartcustomextension)、 [onTokenIssuanceStartHandler](https://learn.microsoft.com/ja-jp/graph/api/resources/ontokenissuancestarthandler)、 [onTokenIssuanceStartReturnClaim](https://learn.microsoft.com/ja-jp/graph/api/resources/ontokenissuancestartreturnclaim) |
| 選択可能な請求 | ID、アクセス、SAML トークンにMicrosoft Entraソース要求 (`groups`、`idtyp`、`login_hint`など) を追加します。 | [省略可能な要求をアプリに提供する](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)( [リファレンス)](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims-reference) | [optionalClaim](https://learn.microsoft.com/ja-jp/graph/api/resources/optionalclaim)、 [optionalClaims](https://learn.microsoft.com/ja-jp/graph/api/resources/optionalclaims) on [application](https://learn.microsoft.com/ja-jp/graph/api/resources/application) |
| カスタム要求ポリシー (アプリごと) | 変換を含む、特定のアプリに対して発行されたトークン内の要求にディレクトリ属性をマップします。 | [JWT 要求のカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity-platform/jwt-claims-customization)、 [SAML 要求のカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)、 [カスタム要求ポリシー](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-customization-custom-claims-policy) | [customClaimsPolicy](https://learn.microsoft.com/ja-jp/graph/api/resources/customclaimspolicy)、 [claimsMappingPolicy](https://learn.microsoft.com/ja-jp/graph/api/resources/claimsmappingpolicy) |
| トークンの有効期間ポリシー | アプリまたはテナントのアクセス、更新、ID トークンの有効期間を構成します。 | [構成可能なトークンの有効期間](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes)、 [構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/configure-token-lifetimes) | [tokenLifetimePolicy](https://learn.microsoft.com/ja-jp/graph/api/resources/tokenlifetimepolicy) |
| トークンの発行ポリシー | 発行時に SAML トークンの署名と暗号化の動作を構成します。 | [SAML 要求のカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization) | [tokenIssuancePolicy](https://learn.microsoft.com/ja-jp/graph/api/resources/tokenissuancepolicy) |
| フェデレーテッド ID 資格情報 | クライアント シークレットまたは証明書を使用する代わりに、外部発行者 (GitHub、Kubernetes、その他のクラウド) からの信頼トークン。 | [ワークロード ID フェデレーション](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation) | [federatedIdentityCredential](https://learn.microsoft.com/ja-jp/graph/api/resources/federatedidentitycredential)、 [フェデレーション ID 資格情報の概要](https://learn.microsoft.com/ja-jp/graph/api/resources/federatedidentitycredentials-overview) |
| アプリケーション マニフェスト | リダイレクト URI、対象ユーザー、許可される許可の種類、トークン設定を宣言によって構成します。 | [アプリケーション マニフェスト リファレンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest) | [application](https://learn.microsoft.com/ja-jp/graph/api/resources/application), [servicePrincipal](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal) |
| 委任された権限の付与 | ユーザーまたはテナントの委任されたスコープを承認します。 | [アクセス許可と同意の概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview) | [oAuth2PermissionGrant](https://learn.microsoft.com/ja-jp/graph/api/resources/oauth2permissiongrant) |
| アプリ ロールの割り当て | トークン ベースの承認のために、ユーザー、グループ、またはサービス プリンシパルにアプリ ロールを割り当てます。 | [アプリ ロールの概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) | [appRoleAssignment](https://learn.microsoft.com/ja-jp/graph/api/resources/approleassignment) |
| 継続的アクセス評価 (CAE) | ユーザーサインアウト、パスワード変更、リスク検出などのイベントに対して、ほぼリアルタイムでトークン失効を有効にします。 | [継続的アクセス評価](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation) | [conditionalAccessPolicy](https://learn.microsoft.com/ja-jp/graph/api/resources/conditionalaccesspolicy) |
| 要求チャレンジ (ステップアップ) | セッションの途中で、より強力な認証または新しい要求を要求します。 | [要求チャレンジ](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-challenge)、 [要求の検証](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-validation) | N/A (プロトコル レベル。 `claims` 要求パラメーターで通知) |

### 機能拡張サーフェイスの選択

次のガイダンスを使用して、シナリオに適合するサーフェスを決定します。

- **Microsoft Entra IDから取得した**要求を追加するには、[省略可能な要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)または[カスタム要求ポリシー](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-customization-custom-claims-policy)を使用します。
- **外部システムからソース化された要求を**追加するには、Azure Functions エンドポイントまたはその他の REST API によってサポートされる[カスタム クレーム プロバイダー](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview)を使用します。
- クライアント シークレットまたは証明書を使用する代わりに **外部ワークロード ID を信頼** するには、 [フェデレーション ID 資格情報](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation)を構成します。
- 既存 **のトークンのセキュリティ イベント** (取り消されたセッション、リスク変更、パスワードリセット) に対応するには、 [継続的なアクセス評価](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation)を有効にします。
- セッション中 **に新しい認証を要求** するには、 [要求チャレンジ](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-challenge)を発行します。

### プログラミング モデル

テーブル内のほとんどのサーフェスは、[Microsoft Graph アプリケーション](https://learn.microsoft.com/ja-jp/graph/api/resources/application)リソースと [servicePrincipal](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal) リソースを介して、または`policies`エンドポイントを介して構成されます。 認証ライブラリでは、これらのサーフェスは構成されません。MICROSOFT GRAPH SDK、[Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview)、または直接 REST 呼び出しを使用します。

カスタム認証拡張機能とトークン発行開始イベントを組み合わせたエンド ツー エンドの例については、「トークン発行開始イベントを [使用してカスタム要求プロバイダーを構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration)する」を参照してください。
<!-- /MSL-PAGE -->
