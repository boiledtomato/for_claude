# Microsoft Learn — Microsoft Entra / 開発者向け (Identity Platform・MSAL・Microsoft.Identity.Web) (part 2)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 59

---

<!-- MSL-PAGE {"url":"entra/identity-platform/app-only-access-primer"} -->
## Microsoft ID プラットフォームでのアプリ専用アクセスのシナリオ - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/app-only-access-primer
- Service: identity-platform / workforce
- Article date: 2025-05-21
- Summary: Microsoft ID プラットフォーム エンドポイントでアプリ専用アクセスを使用するタイミングと方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

アプリケーションが Microsoft Graph などのリソースに直接アクセスする場合、そのアクセスは、1 人のユーザーが使用できるファイルや操作に限定されません。 アプリは独自の ID を使用して API を直接呼び出し、管理者権限を持つユーザーまたはアプリがリソースへのアクセスを承認する必要があります。 このシナリオは、アプリケーション専用アクセスです。

### アプリケーション専用アクセスを使用する必要があるのはどのような場合ですか?

ほとんどの場合、アプリケーション専用アクセスは[委任されたアクセス](https://learn.microsoft.com/ja-jp/entra/identity-platform/delegated-access-primer)よりも広範かつ強力であるため、必要な場合にのみアプリ専用アクセスを使用する必要があります。 通常は、次の場合に適しています。

- ユーザーによる入力なしで、自動化された方法でアプリケーションを実行する必要がある。 たとえば、特定の連絡先からのメールをチェックして自動応答を送信する日次のスクリプトなどです。
- アプリケーションで、複数の異なるユーザーのリソースにアクセスする必要がある。 たとえば、バックアップ アプリやデータ損失防止アプリでは、それぞれ異なる参加者がいるさまざまなチャット チャンネルからメッセージを取得する必要がある場合があります。
- 資格情報をローカルに保存し、アプリでユーザーまたは管理者 "として" サインインできるようにする必要がある。

これに対し、ユーザーが通常サインインして独自のリソースを管理する場合は、アプリケーション専用アクセスを使用しないでください。 このようなシナリオでは、最小限の特権となるように委任されたアクセスを使用する必要があります。

[Image: 委任されたアクセス許可とアプリケーションのアクセス許可の違いを示す図。]

### アプリケーション専用の呼び出しをアプリに許可する

アプリ専用の呼び出しを行うには、クライアント アプリに適切なアプリ ロールを割り当てる必要があります。 アプリ ロールは、アプリケーション専用のアクセス許可とも呼ばれます。 これらは、ロールを定義するリソース アプリのコンテキストでのみアクセス権を付与するため、"アプリ" ロールです。

たとえば、組織内で作成されたすべてのチームの一覧を読み取るために、アプリケーションに Microsoft Graph `Team.ReadBasic.All` アプリ ロールを割り当てる必要があります。 このアプリ ロールでは、Microsoft Graph がリソース アプリである場合に、このデータを読み取る権限が付与されます。 これを割り当てても、クライアント アプリケーションに、他のサービスを介してこのデータを表示できる可能性のある Teams ロールは割り当てられません。

開発者は、アプリケーションの登録時に、必要なすべてのアプリ専用アクセス許可 (アプリ ロールとも呼ばれます) を構成する必要があります。 アプリによって要求されたアプリ専用のアクセス許可は、Azure portal または Microsoft Graph を使用して構成できます。 アプリ専用アクセスでは動的な同意がサポートされていないため、実行時に個々のアクセス許可やアクセス許可のセットを要求することはできません。

アプリに必要なすべてのアクセス許可を構成したら、リソースにアクセスするために [管理者の同意](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent) を得る必要があります。 たとえば、Microsoft Graph API のアプリ専用アクセス許可 (アプリ ロール) を付与できるのは、少なくとも特権ロール管理者ロールを持つユーザーだけです。 アプリケーション管理者やクラウド アプリケーション管理者などの他の管理者ロールを持つユーザーは、他のリソースに対するアプリ専用アクセス許可を付与できます。

管理者ユーザーは、Azure portal を使用するか、Microsoft Graph API を使用してプログラムで許可を作成することにより、アプリ専用アクセス許可を付与できます。 アプリ内から対話型の同意を求めることもできますが、アプリ専用アクセスではユーザーを必要としないため、このオプションは推奨されません。

Outlook.com や Xbox Live アカウントなどの Microsoft アカウントを持つコンシューマー ユーザーは、アプリケーション専用アクセスを承認することはできません。 常に最小限の特権の原則に従ってください。アプリで必要でないアプリ ロールは要求しないでください。 この原則に従えば、アプリが侵害された場合のセキュリティ リスクを限定するのに役立ち、管理者がアプリにアクセスを付与しやすくなります。 たとえば、アプリ専用で詳細なプロファイル情報を読み取らずにユーザーを識別する必要がある場合は、`User.ReadBasic.All` ではなく、より制限された Microsoft Graph `User.Read.All` アプリ ロールを要求する必要があります。

### リソース サービス用のアプリ ロールの設計と発行

他のクライアントが呼び出す API を公開するサービスを Microsoft Entra ID で構築している場合は、アプリ ロール (アプリ専用のアクセス許可) を使用した自動アクセスをサポートできます。 アプリケーションのアプリ ロールは、Microsoft Entra 監理センターの **[アプリ ロール]** セクションで定義できます。 アプリ ロールを作成する方法の詳細については、「[アプリケーションのロールを宣言する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#declare-roles-for-an-application)」を参照してください。

他のユーザーが使用するアプリ ロールを公開する場合は、そのロールを割り当てる管理者にシナリオの明確な説明を提供します。 アプリ専用アクセスはユーザー権限の制約を受けないため、通常アプリ ロールはできるだけ狭くし、特定の機能シナリオをサポートするようにする必要があります。 サービスに含まれるすべての API とリソースへのフル `read` アクセスまたはフル `read/write` アクセスを許可する単一のロールを公開することは避けてください。

Note

アプリ ロール (アプリ専用アクセス許可) は、ユーザーやグループへの割り当てをサポートするように構成することもできます。 目的のアクセス シナリオに合わせてアプリ ロールを正しく構成してください。 API のアプリ ロールをアプリ専用アクセスに使用する場合は、アプリ ロールの作成時に、許可される唯一のメンバーの種類としてアプリケーションを選択します。

### アプリケーション専用アクセスが機能する仕組み

アプリ専用アクセスについて覚えておくべき最も重要なことは、呼び出し元のアプリ自体が代理として、また独自の ID として機能することです。 ユーザーによる操作はありません。 アプリがリソースの特定のアプリ ロールに割り当てられている場合、アプリは、そのアプリ ロールによって管理されるすべてのリソースと操作に完全に制約のないアクセス権を持ちます。

アプリが 1 つ以上のアプリ ロール (アプリ専用アクセス許可) に割り当てられると、 [クライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow) またはその他のサポートされている認証フローを使用して、Microsoft Entra ID にアプリ専用トークンを要求できます。 割り当てられたロールは、アプリのアクセス トークンの `roles` 要求に追加されます。

一部のシナリオでは、委任された呼び出しのユーザー権限と同様に、アプリケーション ID がアクセスを許可するかどうかを決定することがあります。 たとえば、`Application.ReadWrite.OwnedBy` アプリ ロールでは、アプリ自体が所有するサービス プリンシパルを管理する権限がアプリに付与されます。

### アプリケーション専用アクセスの例 - Microsoft Graph を使用した自動電子メール通知

次の例は、現実的な自動化シナリオを示しています。

Alice は、Windows ファイル共有に存在する部門レポート フォルダーで新しいドキュメントが登録されるたびに、電子メールでチームに通知したいと考えています。 Alice は、フォルダーを調べて新しいファイルを検索する PowerShell スクリプトを実行するスケジュールされたタスクを作成します。 このスクリプトでは、リソース API である Microsoft Graph によって保護されたメールボックスを使用して電子メールを送信します。

スクリプトはユーザーの操作なしで実行されるため、認可システムではアプリケーションの認可のみがチェックされます。 Exchange Online は、呼び出しを行うクライアントに、管理者によって `Mail.Send` アプリケーションアクセス許可 (アプリ ロール) が付与されているかどうかを確認します。 `Mail.Send` がアプリに付与されていない場合、Exchange Online は要求に失敗します。

| POST /users/{id}/{userPrincipalName}/sendMail | クライアント アプリに Mail.Send が付与されている場合 | クライアント アプリに Mail.Send が付与されていない場合 |
| --- | --- | --- |
| このスクリプトでは、Alice のメールボックスを使用して電子メールを送信します。 | 200 - アクセスが付与されます。 管理者が、任意のユーザーとしてメールを送信することをアプリに許可しました。 | 403 - 承認されません。 管理者は、このクライアントが電子メールを送信することを許可しません。 |
| このスクリプトでは、電子メールを送信するための専用メールボックスを作成します。 | 200 - アクセスが付与されます。 管理者が、任意のユーザーとしてメールを送信することをアプリに許可しました。 | 403 - 承認されません。 管理者は、このクライアントが電子メールを送信することを許可しません。 |

上記の例は、アプリケーションの認可を簡単に示したものです。 運用環境の Exchange Online サービスでは、アプリケーションのアクセス許可を特定の Exchange Online メールボックスに制限するなど、他にもさまざまなアクセス シナリオがサポートされています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/app-resilience-continuous-access-evaluation"} -->
## 継続的アクセス評価が有効になった API をアプリケーションで使用する方法 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/app-resilience-continuous-access-evaluation
- Service: identity-platform
- Article date: 2024-11-18
- Summary: 継続的アクセス評価のサポートを追加し、重大なイベントとポリシー評価に基づいて取り消すことができる有効期間の長いアクセス トークンを実現することで、アプリのセキュリティと回復性を強化します。

[継続的アクセス評価 (CAE)](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation) は、有効期間をベースとしたトークンの失効に頼るのではなく、[クリティカル イベント](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation#critical-event-evaluation)と[ポリシー評価](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation#conditional-access-policy-evaluation)に基づいてアクセス トークンを取り消すことができる Microsoft Entra 機能です。

リスクとポリシーはリアルタイムで評価されるため、一部のリソース API のトークンの有効期間は最大で 28 時間延長される可能性があります。 このような有効期間が長いトークンは、[Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) によって事前に更新されるため、アプリケーションの回復性が向上します。

MSAL を使用していないアプリケーションでは、CAE を使用するための [要求のチャレンジ、要求要求、およびクライアント機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-challenge) のサポートを追加できます。

### 実装時の注意事項

CAE を使用するには、アプリもそれがアクセスするリソース API も CAE 対応であることが必要です。 リソース API によって CAE が実装されていて、CAE を処理できることがご利用のアプリケーションで宣言されている場合、アプリは、そのリソースに対する CAE トークンを受信します。 このため、アプリが CAE 対応であることを宣言する場合、アプリケーションは、Microsoft Identity アクセス トークンを受け入れるすべてのリソース API の CAE 要求チャレンジを処理する必要があります。

ただし、CAE 対応リソースをサポートするようにコードを準備しても、CAE をサポートしていない API を操作する機能が制限されるわけではありません。 アプリが CAE 応答を正しく処理しない場合、技術的には有効でも CAE が原因で取り消されるトークンを使用する API 呼び出しをアプリが繰り返し再試行する可能性があります。

### アプリケーション内での CAE の処理

まずは、CAE が原因で呼び出しを拒否しているリソース API からの応答を処理するコードを追加します。 CAE では、アクセス トークンが取り消されているとき、または API が使用されている IP アドレスの変更を検出すると、API は 401 状態と `WWW-Authenticate` ヘッダーを返します。 この `WWW-Authenticate` ヘッダーには、アプリケーションが新しいアクセス トークンを取得するために使用できる要求チャレンジが含まれています。

次に例を示します。

```console
// Line breaks for legibility only

HTTP 401; Unauthorized

Bearer authorization_uri="https://login.windows.net/common/oauth2/authorize",
  error="insufficient_claims",
  claims="eyJhY2Nlc3NfdG9rZW4iOnsibmJmIjp7ImVzc2VudGlhbCI6dHJ1ZSwgInZhbHVlIjoiMTYwNDEwNjY1MSJ9fX0="
```

アプリは以下がないかを確認します。

- 401 状態を返す API 呼び出し
- 以下を含む `WWW-Authenticate`ヘッダーの存在。
    - `error` という値を持つ `insufficient_claims` パラメーター
    - `claims` パラメーター

## [.NET](#tab/dotnet)
これらの条件が満たされると、アプリは [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/)`WwwAuthenticateParameters` クラスを使用して、要求チャレンジを抽出してデコードすることができます。

```csharp
if (APIresponse.IsSuccessStatusCode)
{
    // ...
}
else
{
    if (APIresponse.StatusCode == System.Net.HttpStatusCode.Unauthorized
        && APIresponse.Headers.WwwAuthenticate.Any())
    {
        string claimChallenge = WwwAuthenticateParameters.GetClaimChallengeFromResponseHeaders(APIresponse.Headers);
```

アプリは次に、要求チャレンジを使用して、リソースの新しいアクセス トークンを取得します。

```csharp
try
{
    authResult = await _clientApp.AcquireTokenSilent(scopes, firstAccount)
        .WithClaims(claimChallenge)
        .ExecuteAsync()
        .ConfigureAwait(false);
}
catch (MsalUiRequiredException)
{
    try
    {
        authResult = await _clientApp.AcquireTokenInteractive(scopes)
            .WithClaims(claimChallenge)
            .WithAccount(firstAccount)
            .ExecuteAsync()
            .ConfigureAwait(false);
    }
    // ...
```

アプリケーションで CAE 対応リソースから返された要求チャレンジを処理する準備ができたら、アプリが CAE 対応であることを Microsoft Identity に伝えることができます。 これを MSAL アプリケーション内で行うには、`"cp1"` というクライアント機能を使用してパブリック クライアントを構築します。

```csharp
_clientApp = PublicClientApplicationBuilder.Create(App.ClientId)
    .WithDefaultRedirectUri()
    .WithAuthority(authority)
    .WithClientCapabilities(new [] {"cp1"})
    .Build();
```

## [JavaScript](#tab/JavaScript)
これらの条件が満たされると、アプリは API 応答ヘッダーから次のように要求チャレンジを抽出できます。

```javascript
try {
  const response = await fetch(apiEndpoint, options);

  if (response.status === 401 && response.headers.get('www-authenticate')) {
    const authenticateHeader = response.headers.get('www-authenticate');
    const claimsChallenge = parseChallenges(authenticateHeader).claims;
    // use the claims challenge to acquire a new access token...
  }
} catch(error) {
  // ...
}

// helper function to parse the www-authenticate header
function parseChallenges(header) {
    const schemeSeparator = header.indexOf(' ');
    const challenges = header.substring(schemeSeparator + 1).split(',');
    const challengeMap = {};

    challenges.forEach((challenge) => {
        const [key, value] = challenge.split('=');
        challengeMap[key.trim()] = window.decodeURI(value.replace(/['"]+/g, ''));
    });
    return challengeMap;
}
```

アプリは次に、要求チャレンジを使用して、リソースの新しいアクセス トークンを取得します。

```javascript
const tokenRequest = {
    claims: window.atob(claimsChallenge), // decode the base64 string
    scopes: ['User.Read'],
    account: msalInstance.getActiveAccount()
};

let tokenResponse;

try {
    tokenResponse = await msalInstance.acquireTokenSilent(tokenRequest);
} catch (error) {
     if (error instanceof InteractionRequiredAuthError) {
        tokenResponse = await msalInstance.acquireTokenPopup(tokenRequest);
    }
}
```

アプリケーションで CAE 対応のリソースから返された要求チャレンジを処理する準備ができたら、MSAL 構成に `clientCapabilities` プロパティを追加することで、そのアプリが CAE に対応可能な状態にあることを Microsoft Identity に伝えることができます。

```javascript
const msalConfig = {
    auth: {
        clientId: 'Enter_the_Application_Id_Here', 
        clientCapabilities: ["CP1"]
        // remaining settings...
    }
}

const msalInstance = new PublicClientApplication(msalConfig);
```

## [MSAL-Python](#tab/Python)
これらの条件が満たされると、アプリは API 応答ヘッダーから次のように要求チャレンジを抽出できます。

```python
import msal  # pip install msal
import requests  # pip install requests
import www_authenticate  # pip install www-authenticate==0.9.2

# Once your application is ready to handle the claim challenge returned by a CAE-enabled resource, you can tell Microsoft Identity your app is CAE-ready. To do this in your MSAL application, build your Public Client using the Client Capabilities of "cp1".
app = msal.PublicClientApplication("your_client_id", client_capabilities=["cp1"])

...

# When these conditions are met, the app can extract the claims challenge from the API response header as follows:
response = requests.get("<your_resource_uri_here>")
if response.status_code == 401 and response.headers.get('WWW-Authenticate'):
    parsed = www_authenticate.parse(response.headers['WWW-Authenticate'])
    claims = parsed.get("bearer", {}).get("claims")

    # Your app would then use the claims challenge to acquire a new access token for the resource.
    if claims:
        auth_result = app.acquire_token_interactive(["scope"], claims_challenge=claims)
```

## [MSAL-Android](#tab/Java)
#### CP1 クライアント機能のサポートを宣言する

アプリケーション構成内では、`CP1` クライアント機能を含めることで、アプリケーションが CAE をサポートすることを宣言する必要があります。 これは、`client_capabilities` JSON プロパティを使用することで指定します。

```java
{
  "client_id" : "<your_client_id>",
  "authorization_user_agent" : "DEFAULT",
  "redirect_uri" : "msauth://<pkg>/<cert_hash>",
  "multiple_clouds_supported":true,
  "broker_redirect_uri_registered": true,
  "account_mode": "MULTIPLE",
  "client_capabilities": "CP1",
  "authorities" : [
    {
      "type": "AAD",
      "audience": {
        "type": "AzureADandPersonalMicrosoftAccount"
      }
    }
  ]
}
```

#### 実行時に CAE チャレンジに対応する

リソースに対して要求を行い、その応答に要求チャレンジが含まれている場合は、次の要求で使用するために、それを抽出し、MSAL に送り返します。

```java
final HttpURLConnection connection = ...;
final int responseCode = connection.getResponseCode();

// Check the response code...
if (200 == responseCode) {
    // ...
} else if (401 == responseCode) {
    final String authHeader = connection.getHeaderField("WWW-Authenticate");

    if (null != authHeader) {
        final ClaimsRequest claimsRequest = WWWAuthenticateHeader
                                                .getClaimsRequestFromWWWAuthenticateHeaderValue(authHeader);

        // Feed the challenge back into MSAL, first silently, then interactively if required
        final AcquireTokenSilentParameters silentParameters = new AcquireTokenSilentParameters.Builder()
            .fromAuthority(authority)
            .forAccount(account)
            .withScopes(scope)
            .withClaims(claimsRequest)
            .build();
        
        try {
            final IAuthenticationResult silentRequestResult = mPublicClientApplication.acquireTokenSilent(silentParameters);
            // If successful - your business logic goes here...

        } catch (final Exception e) {
            if (e instanceof MsalUiRequiredException) {
                // Retry the request interactively, passing in any claims challenge...
            }
        }
    }
} else {
    // ...
}

// Don't forget to close your connection
```

## [MSAL-ObjC](#tab/ObjC)
以下のコード スニペットでは、トークンをサイレントで取得し、リソース プロバイダーに対する HTTP 呼び出しを行った後、CAE ケースを処理するフローについて説明します。 サイレント呼び出しが要求で失敗した場合は、追加の対話呼び出しが必要になる場合があります。

#### CP1 クライアント機能のサポートを宣言する

アプリケーション構成内では、`CP1` クライアント機能を含めることで、アプリケーションが CAE をサポートすることを宣言する必要があります。 これは、`clientCapabilities` プロパティを使用することで指定します。

```objc
let clientConfigurations = MSALPublicClientApplicationConfig(clientId: "contoso-app-ABCDE-12345",
                                                            redirectUri: "msauth.com.contoso.appbundle://auth",
                                                            authority: try MSALAuthority(url: URL(string: "https://login.microsoftonline.com/organizations")!))
clientConfigurations.clientApplicationCapabilities = ["CP1"]
let applicationContext = try MSALPublicClientApplication(configuration: clientConfigurations)
```

要求チャレンジを解析するためのヘルパー関数を実装します。

```objc
func parsewwwAuthenticateHeader(headers:Dictionary<AnyHashable, Any>) -> String? {
    // !! This is a sample code and is not validated, please provide your own implementation or fully test the sample code provided here.
    // Can also refer here for our internal implementation: https://github.com/AzureAD/microsoft-authentication-library-common-for-objc/blob/dev/IdentityCore/src/webview/embeddedWebview/challangeHandlers/MSIDPKeyAuthHandler.m#L112
    guard let wwwAuthenticateHeader = headers["WWW-Authenticate"] as? String else {
        // did not find the header, handle gracefully
        return nil
    }
    
    var parameters = [String: String]()
    // regex mapping
    let regex = try! NSRegularExpression(pattern: #"(\w+)="([^"]*)""#)
    let matches = regex.matches(in: wwwAuthenticateHeader, range: NSRange(wwwAuthenticateHeader.startIndex..., in: wwwAuthenticateHeader))
    
    for match in matches {
        if let keyRange = Range(match.range(at: 1), in: wwwAuthenticateHeader),
           let valueRange = Range(match.range(at: 2), in: wwwAuthenticateHeader) {
            let key = String(wwwAuthenticateHeader[keyRange])
            let value = String(wwwAuthenticateHeader[valueRange])
            parameters[key] = value
        }
    }
    
    guard let jsonData = try? JSONSerialization.data(withJSONObject: parameters, options: .prettyPrinted) else {
        // cannot convert params into json date, end gracefully
        return nil
    }
    return String(data: jsonData, encoding: .utf8)
}
```

401 / 要求チャレンジをキャッチして解析します。

```objc
let response = .... // HTTPURLResponse object from 401'd service response

switch response.statusCode {
case 200:
    // ...succeeded!
    break
case 401:
    let headers = response.allHeaderFields

    // Parse header fields
    guard let wwwAuthenticateHeaderString = self.parsewwwAuthenticateHeader(headers: headers) else {
        // 3.7 no valid wwwAuthenticateHeaderString is returned from header, end gracefully
        return
    }
    
    let claimsRequest = MSALClaimsRequest(jsonString: wwwAuthenticateHeaderString, error: nil)
    // Create claims request
    let parameters = MSALSilentTokenParameters(scopes: "Enter_the_Protected_API_Scopes_Here", account: account)
    parameters.claimsRequest = claimsRequest
    // Acquire token silently again with the claims challenge
    applicationContext.acquireTokenSilent(with: parameters) { (result, error) in
        
        if let error = error {
            // error happened end flow gracefully, and handle error. (e.g. interaction required)
            return
        }
        
        guard let result = result else {
            
            // no result end flow gracefully
            return
        }                    
        // Success - You got a token!
    }
    
    break
default:
    break
}
```

## [MSAL-Go](#tab/Go)
これらの条件が満たされると、アプリは API 応答ヘッダーから次のように要求チャレンジを抽出できます。

クライアント機能をアドバタイズします。

```Go
client, err := New("client-id", WithAuthority(authority), WithClientCapabilities([]string{"cp1"}))
```

`WWW-Authenticate` ヘッダーを解析し、結果のチャレンジを MSAL-Go に渡します。

```Go
// No snippet provided at this time
```

要求チャレンジを使用してサイレントでのトークンの取得を試みます。

```Go
var ar AuthResult;
ar, err := client.AcquireTokenSilent(ctx, tokenScope, public.WithClaims(claims))
```

---

ユーザーをサインインさせてから、Azure portal を使用してユーザーのセッションを取り消すことで、アプリケーションをテストできます。 CAE 対応の API が次にアプリで呼び出されたとき、ユーザーは再認証を行うように求められます。

### コード サンプル

- [ユーザーをサインインさせ、Microsoft Graph を呼び出すことを Angular シングルページ アプリケーションで可能にする](https://github.com/Azure-Samples/ms-identity-docs-code-javascript/tree/main/angular-spa)
- [ユーザーをサインインさせ、Microsoft Graph を呼び出すことを React シングルページ アプリケーションで可能にする](https://github.com/Azure-Samples/ms-identity-javascript-react-tutorial/tree/main/2-Authorization-I/1-call-graph)
- [ユーザーをサインインさせ、Microsoft Graph を呼び出すことを ASP.NET Core Web アプリで可能にする](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/2-WebApp-graph-user/2-1-Call-MSGraph)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/app-sign-in-flow"} -->
## Microsoft ID プラットフォームを使用したアプリのサインイン フロー - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/app-sign-in-flow
- Service: identity-platform
- Article date: 2023-08-11
- Summary: Microsoft ID プラットフォームでの Web、デスクトップ、およびモバイル アプリのサインイン フローについて説明します。

このトピックでは、Microsoft ID プラットフォームを使用した Web アプリ、デスクトップ アプリ、およびモバイル アプリの基本的なサインイン フローについて説明します。 Microsoft ID プラットフォームでサポートされるサインイン シナリオの詳細については、[認証フローとアプリ シナリオ](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios)に関する記事を参照してください。

### Web アプリのサインイン フロー

ユーザーがブラウザーで Web アプリに移動すると、次のことが起こります。

- Web アプリで、ユーザーが認証されているかどうかが判断されます。
- ユーザーが認証されていない場合は、ユーザーをサインインさせるように Web アプリから Microsoft Entra ID に委任されます。 そのサインインは組織のポリシーに準拠します。したがって、ユーザーに資格情報を入力するように求めることもあれば、[多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) (2 要素認証や 2FA と呼ばれることがあります) を使用することも、パスワードをまったく使用しない (Windows Hello を使用するなど) 場合もあります。
- ユーザーは、クライアント アプリが必要とするアクセスに同意するように求められます。 これは、ユーザーが同意したアクセスを表すトークンを Microsoft ID プラットフォームで配信できるように、クライアント アプリを Microsoft Entra ID に登録する必要があるためです。

ユーザーが正常に認証されると、次のことが起こります。

- Microsoft ID プラットフォームから、Web アプリにトークンが送信されます。
- Cookie が保存され、Microsoft Entra のドメインに関連付けられ、ブラウザーの cookie jar にユーザーの ID が含まれます。 アプリが次にブラウザーを使用して Microsoft ID プラットフォーム承認エンドポイントに移動すると、ユーザーが再度サインインする必要がないように、ブラウザーによって Cookie が表示されます。 これも SSO の実現方法です。 クッキーはMicrosoft Entra ID によって生成され、Microsoft Entra ID でのみ理解できます。
- その後、Web アプリにより、トークンが検証されます。 検証が成功した場合、Web アプリで、保護されたページが表示され、セッション Cookie がブラウザーの cookie jar に保存されます。 ユーザーが別のページに移動すると、Web アプリでは、そのユーザーがセッション Cookie に基づいて認証されていることを認識します。

次のシーケンス図は、この相互作用をまとめたものです。

[Image: Web アプリの認証プロセス]

#### Web アプリで、ユーザーが認証されているかどうかが判断されるしくみ

Web アプリの開発者は、すべてのページまたは特定のページのみで認証を必要とするかどうかを指定できます。 たとえば、ASP.NET/ASP.NET Core では、`[Authorize]` 属性をコントローラー アクションに追加してこれを行います。

この属性により、ASP.NET で、ユーザーの ID が含まれるセッション Cookie の存在が確認されます。 Cookie が存在しない場合、ASP.NET により、指定された ID プロバイダーに認証がリダイレクトされます。 ID プロバイダーが Microsoft Entra ID 場合、Web アプリにより、`https://login.microsoftonline.com` に認証がリダイレクトされ、サインイン ダイアログが表示されます。

#### Web アプリでサインインが Microsoft ID プラットフォームに委任され、トークンが取得されるしくみ

ユーザー認証は、ブラウザーを介して行われます。 OpenID プロトコルで、標準の HTTP プロトコル メッセージが使用されます。

- Web アプリでは、Microsoft ID プラットフォームを使用するために HTTP 302 (リダイレクト) がブラウザーに送信されます。
- ユーザーが認証されると、Microsoft ID プラットフォームでは、ブラウザーからのリダイレクトを使用して Web アプリにトークンが送信されます。
- リダイレクトは、リダイレクト URI の形式で Web アプリから提供されます。 このリダイレクト URI は、Microsoft Entra アプリケーション オブジェクトに登録されます。 アプリケーションは複数の URL でデプロイされる可能性があるため、リダイレクト URI は複数存在する場合があります。 そのため、Web アプリで、使用するリダイレクト URI も指定する必要があります。
- Microsoft Entra ID では、Web アプリから送信されるリダイレクト URI が、アプリの登録されたリダイレクト URI のいずれかであることを確認します。

### デスクトップ アプリおよびモバイル アプリのサインイン フロー

上述したフローは、デスクトップ アプリケーションとモバイル アプリケーションに適用されますが、若干の違いがあります。

デスクトップ アプリケーションとモバイル アプリケーションでは、認証のために、埋め込み Web コントロールまたはシステム ブラウザーを使用できます。 次の図は、デスクトップ アプリまたはモバイル アプリで Microsoft 認証ライブラリ (MSAL) を使用してアクセス トークンを取得し、Web API を呼び出す方法を示しています。

[Image: デスクトップ アプリのしくみ]

MSAL では、ブラウザーを使用してトークンを取得します。 Web アプリと同様に、認証は Microsoft ID プラットフォームに委任されます。

Microsoft Entra ID は Web アプリの場合と同じ ID Cookie をブラウザーに保存するため、ネイティブ アプリまたはモバイル アプリでシステム ブラウザーを使用する場合は、対応する Web アプリを使用してすぐに SSO を取得します。

既定では、MSAL ではシステム ブラウザーが使用されます。 埋め込みコントロールを使用して、より統合されたユーザー エクスペリエンスを提供する .NET Framework デスクトップ アプリケーションは、例外となります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/apple-sso-plugin"} -->
## Apple デバイス用の Microsoft Enterprise SSO プラグイン - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin
- Service: identity-platform
- Article date: 2025-01-28
- Summary: iOS、iPadOS を使用する Apple デバイスと、macOS デバイスに対する Microsoft Entra SSO プラグインについて説明します。

**Apple デバイス用の Microsoft Enterprise SSO プラグイン**は、Apple の[エンタープライズ シングル サインオン](https://developer.apple.com/documentation/authenticationservices)機能をサポートするすべてのアプリケーションで、macOS、iOS、iPadOS 上の Microsoft Entra アカウントに対するシングル サインオン (SSO) を提供します。 このプラグインにより、業務に必要だが、最新の ID ライブラリやプロトコルはまだサポートしていない古いアプリケーションにも SSO が提供されます。 Microsoft は Apple と密接に連携してこのプラグインを開発し、アプリケーションの使いやすさを向上させ、利用可能な最高の保護を提供しています。

Enterprise SSO プラグインは現在、次のアプリの組み込み機能です。

- [Microsoft Authenticator](https://support.microsoft.com/account-billing/how-to-use-the-microsoft-authenticator-app-9783c865-0308-42fb-a519-8cf666fe0acc): iOS、iPadOS
- Microsoft Intune [ポータル サイト](https://learn.microsoft.com/ja-jp/mem/intune/apps/apps-company-portal-macos): macOS

### Features

Apple デバイス用の Microsoft Enterprise SSO プラグインには、次のような利点があります。

- Apple のエンタープライズ SSO 機能をサポートするすべてのアプリケーションで、Microsoft Entra アカウントに対する SSO を提供します。
- すべてのモバイル デバイス管理 (MDM) ソリューションで有効にすることができ、デバイスとユーザーの両方の登録でサポートされます。
- Microsoft Authentication Library (MSAL) をまだ使用していないアプリケーションにまで SSO を拡張します。
- OAuth 2、OpenID Connect、SAML を使用するアプリケーションに SSO を拡張します。
- MSAL とネイティブに統合されるため、Microsoft Enterprise SSO プラグインが有効になっている場合にエンド ユーザーにスムーズなネイティブ エクスペリエンスを提供します。

### Requirements

Apple デバイス用の Microsoft Enterprise SSO プラグインを使用するには:

- デバイスは、Apple デバイス用の Microsoft Enterprise SSO プラグインを備えたアプリを *サポート*し、インストールされている必要があります。
    - iOS 13.0 以降: [Microsoft Authenticator アプリ](https://support.microsoft.com/account-billing/how-to-use-the-microsoft-authenticator-app-9783c865-0308-42fb-a519-8cf666fe0acc)
    - iPadOS 13.0 以降: [Microsoft Authenticator アプリ](https://support.microsoft.com/account-billing/how-to-use-the-microsoft-authenticator-app-9783c865-0308-42fb-a519-8cf666fe0acc)
    - macOS 10.15 以降: [Intune ポータル サイト アプリ](https://learn.microsoft.com/ja-jp/mem/intune/user-help/enroll-your-device-in-intune-macos-cp)
- Microsoft Intune を使用するなどして、デバイスが *MDM に登録*されている必要があります。
- 構成を*デバイスにプッシュ*して、Enterprise SSO プラグインを有効にする必要があります。 このセキュリティ制約は Apple の要件です。
- Apple デバイスは、ID プロバイダーの URL と独自の URL の両方にアクセスできるようにする必要があります。 詳細については、「 [エンタープライズ ネットワークでの Apple 製品の使用」を](https://support.apple.com/101555)参照してください。

SSO プラグインが 2022 以降にリリースされ、プラットフォーム SSO を対象としていないオペレーティング システムのバージョンで機能するために許可する必要がある URL の最小セットは次のとおりです (最新のオペレーティング システム バージョンでは、Apple はその CDN に完全に依存しています)。

- `app-site-association.cdn-apple.com`
- `app-site-association.networking.apple`
- `config.edge.skype.com` - 実験構成サービス (ECS) との通信を維持することで、Microsoft が重大なバグにタイムリーに対応できるようになります。

SSO プラグインがプラットフォーム SSO 対象デバイスまたは 2022 より前にリリースされたオペレーティング システム バージョンで機能するために許可する必要がある URL の最小セット:

- `app-site-association.cdn-apple.com`
- `app-site-association.networking.apple`
- `login.microsoftonline.com`
- `login.microsoft.com`
- `sts.windows.net`
- `login.partner.microsoftonline.cn`(\*\*)
- `login.chinacloudapi.cn`(\*\*)
- `login.microsoftonline.us`(\*\*)
- `login-us.microsoftonline.com`(\*\*)
- `config.edge.skype.com`(\*\*\*)

( \* )Microsoft ドメインの許可は、2022 年より前にリリースされたオペレーティング システムのバージョンでのみ必要です。 最新のオペレーティング システム バージョンでは、Apple は CDN に完全に依存しています。 ( \*\* ) ソブリンクラウドドメインに依存している場合にのみ、それらを許可する必要があります。 ( \*\*\* )実験構成サービス (ECS) との通信を維持することで、Microsoft が重大なバグにタイムリーに対応できるようになります。

**デバイス登録フローで許可する必要がある URL**

[ここに](https://learn.microsoft.com/ja-jp/entra/identity/devices/plan-device-deployment#network-requirements-for-device-registration-with-microsoft-entra)記載されている URL へのトラフィックが既定で許可され、TLS インターセプトまたは検査から明示的に除外されていることを確認します。 これは、正常に完了するために TLS チャレンジに依存する登録フローにとって重要です。

Important

**注: 登録フローで使用される TLS エンドポイントに対する最新の更新が行われています。 中断を回避するために、環境の許可リストに最新の URL 要件が反映されていることを確認してください。**

Microsoft Enterprise SSO プラグインは、Apple の [Enterprise SSO](https://developer.apple.com/documentation/authenticationservices) フレームワークに依存しています。 Apple の Enterprise SSO フレームワークを使用すると、[Associated Domains](https://developer.apple.com/documentation/xcode/supporting-associated-domains) と呼ばれるテクノロジを利用して、承認された SSO プラグインのみが各 ID プロバイダーで機能できるようになります。 SSO プラグインの ID を確認するために、各 Apple デバイスは ID プロバイダーが所有するエンドポイントにネットワーク要求を送信し、承認された SSO プラグインに関する情報を読み取ります。Apple では、ID プロバイダーに直接アクセスするだけでなく、この情報に対する別のキャッシュも実装しています。

Warning

データ損失防止やテナントの制限などのシナリオで SSL トラフィックを傍受するプロキシ サーバーを組織で使用している場合、それらの URL へのトラフィックが TLS の中断と検査から除外されていることを確認してください。 これらの URL が除外されないと、クライアント証明書の認証に干渉し、デバイス登録とデバイスベースの条件付きアクセスに問題が発生する可能性があります。 SSO プラグインは、Apple CDN ドメインをインターセプトから完全に除外しないと安定して機能せず、そのようにするまで断続的に問題が発生します。 組織で 2022 年より後にリリースされた OS バージョンを使用している場合、MICROSOFT ログイン URL を TLS 間の検査から除外する必要はありません。 テナント制限機能を使用しているお客様は、Microsoft ログイン URL で TLS 検査を行い、要求に必要なヘッダーを追加できます。

Note

企業プロキシを使用してテナント制限が展開されている場合、プラットフォーム SSO は Microsoft Entra ID テナント制限 v2 機能と互換性がありません。 代替オプションが [TRv2 の既知の制限事項](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-restrictions-v2#known-limitation)に記載されている

これらの URL が組織でブロックされている場合、ユーザーに対して `1012 NSURLErrorDomain error`、`1000 com.apple.AuthenticationServices.AuthorizationError` や `1001 Unexpected` などのエラーが表示される場合があります。

許可する必要があるその他の Apple URL については、サポート記事「[エンタープライズ ネットワークで Apple 製品を使用する](https://support.apple.com/HT210060)」を参照してください。

#### iOS の要件

- iOS 13.0 以降がデバイスにインストールされている必要があります。
- Apple デバイス用の Microsoft Enterprise SSO プラグインを提供する Microsoft アプリケーションが、デバイスにインストールされている必要があります。 このアプリは、[Microsoft Authenticator アプリ](https://support.microsoft.com/account-billing/how-to-use-the-microsoft-authenticator-app-9783c865-0308-42fb-a519-8cf666fe0acc)です。

#### macOS の要件

- macOS 10.15 以降がデバイスにインストールされている必要があります。
- Apple デバイス用の Microsoft Enterprise SSO プラグインを提供する Microsoft アプリケーションが、デバイスにインストールされている必要があります。 このアプリは、[Intune ポータル サイト アプリ](https://learn.microsoft.com/ja-jp/mem/intune/user-help/enroll-your-device-in-intune-macos-cp)です。

### SSO プラグインの有効化

MDM を使用して SSO プラグインを有効にするには、次の情報を使用します。

#### Microsoft Intune の構成

MDM サービスとして Microsoft Intune を使用する場合は、組み込みの構成プロファイル設定を使用して、Microsoft Enterprise SSO プラグインを有効にすることができます。

1. 構成プロファイルの [SSO アプリ プラグイン](https://learn.microsoft.com/ja-jp/mem/intune/configuration/use-enterprise-sso-plug-in-ios-ipados-with-intune)の設定を構成します。
2. プロファイルがまだ割り当てられていない場合、[プロファイルをユーザーまたはデバイス グループに割り当てます](https://learn.microsoft.com/ja-jp/mem/intune/configuration/device-profile-assign)。

SSO プラグインを有効にするプロファイル設定は、各デバイスが次回 Intune でチェックインするときに、グループのデバイスに自動的に適用されます。

#### 他の MDM サービスの手動構成

MDM に Intune を使用しない場合は、Apple デバイス用に拡張可能なシングル サインオン プロファイル ペイロードを構成できます。 Microsoft Enterprise SSO プラグインとその構成オプションを構成するには、次のパラメーターを使用します。

iOS の設定:

- **拡張機能 ID**: `com.microsoft.azureauthenticator.ssoextension`
- **チーム ID**: iOS ではこのフィールドは不要です。

macOS の設定:

- **拡張機能 ID**: `com.microsoft.CompanyPortalMac.ssoextension`
- **チーム ID**: `UBF8T346G9`

一般的な設定:

- **型**: リダイレクト
    - `https://login.microsoftonline.com`
    - `https://login.microsoft.com`
    - `https://sts.windows.net`
    - `https://login.partner.microsoftonline.cn`
    - `https://login.chinacloudapi.cn`
    - `https://login.microsoftonline.us`
    - `https://login-us.microsoftonline.com`

#### デプロイ ガイド

選択した MDM ソリューションを使用して Microsoft Enterprise SSO プラグインを有効にするには、次のデプロイ ガイドを使用します。

##### Intune:

- [iOS ガイド](https://learn.microsoft.com/ja-jp/mem/intune/configuration/use-enterprise-sso-plug-in-ios-ipados-with-intune?tabs=prereq-intune%2Ccreate-profile-intune)
- [macOS ガイド](https://learn.microsoft.com/ja-jp/mem/intune/configuration/use-enterprise-sso-plug-in-macos-with-intune?tabs=prereq-intune%2Ccreate-profile-intune)

##### Jamf Pro:

- [iOS ガイド](https://learn.microsoft.com/ja-jp/mem/intune/configuration/use-enterprise-sso-plug-in-ios-ipados-with-intune?tabs=prereq-jamf-pro%2Ccreate-profile-jamf-pro)
- [macOS ガイド](https://learn.microsoft.com/ja-jp/mem/intune/configuration/use-enterprise-sso-plug-in-macos-with-intune?tabs=prereq-jamf-pro%2Ccreate-profile-jamf-pro)

##### その他の MDM:

- [iOS ガイド](https://learn.microsoft.com/ja-jp/mem/intune/configuration/use-enterprise-sso-plug-in-ios-ipados-with-intune?tabs=prereq-other-mdm%2Ccreate-profile-other-mdm)
- [macOS ガイド](https://learn.microsoft.com/ja-jp/mem/intune/configuration/use-enterprise-sso-plug-in-macos-with-intune?tabs=prereq-other-mdm%2Ccreate-profile-other-mdm)

#### その他の構成オプション

構成オプションをさらに追加して、SSO 機能を他のアプリに拡張することができます。

##### MSAL を使用しないアプリで SSO を有効にする

SSO プラグインを使用すると、Microsoft 認証ライブラリ (MSAL) などの Microsoft SDK を使用して開発されていないアプリケーションでも、SSO に参加させることができます。

SSO プラグインは、次の条件を満たすデバイスによって自動的にインストールされます。

- Authenticator アプリ (iOS、iPadOS の場合) または Intune ポータル サイト アプリ (macOS の場合) がダウンロード済みである。
- デバイスが組織に MDM 登録されている。

組織では、多要素認証、パスワードレス認証、条件付きアクセスなどのシナリオで Authenticator アプリを使用している可能性があります。 MDM プロバイダーを使用して、アプリケーション用の SSO プラグインを有効にすることができます。 Microsoft では、プラグインの構成を Microsoft Intune を使用して簡単にできるようにしました。 許可リストは、SSO プラグインを使用するようにこれらのアプリケーションを構成するために使用されます。

Important

Microsoft Enterprise SSO プラグインでは、ネイティブの Apple ネットワーク テクノロジまたは Web ビューを使用するアプリのみをサポートしています。 独自のネットワーク レイヤー実装が組み込まれているアプリケーションはサポートしていません。

MSAL を使用しないアプリ用に Microsoft Enterprise SSO プラグインを構成するには、次のパラメーターを使用します。

Important

Microsoft 認証ライブラリを使用するアプリをこの許可リストに追加する必要はありません。 これらのアプリは既定で SSO に参加します。 Microsoft で構築されたほとんどのアプリでは、Microsoft Authentication Library を使用します。

##### すべてのマネージド アプリに対して SSO を有効にする

- **キー**: `Enable_SSO_On_All_ManagedApps`
- **種類**: `Integer`
- **値**: 1 または 0。 既定では、この値は 0 に設定されます。

このフラグがオンの場合 (その値は `1` に設定されます)、`AppBlockList` に含まれていないすべての MDM マネージド アプリが SSO に参加する可能性があります。

##### SSO を特定のアプリで有効にする

- **キー**: `AppAllowList`
- **種類**: `String`
- **値**: SSO への参加が許可されているアプリケーションのアプリケーション バンドル ID のコンマ区切りの一覧。
- **例**: `com.contoso.workapp, com.contoso.travelapp`

Note

Safari と Safari View Service は、既定で SSO に参加できます。 AppBlockList に Safari と Safari View Service のバンドル ID を追加することで、SSO に参加"*しない*"構成を行えます。 iOS バンドル ID: [com.apple.mobilesafari、com.apple.SafariViewService] macOS バンドル ID: [com.apple.Safari]

##### SSO を特定のバンドル ID プレフィックスを持つすべてのアプリに対して有効にする

- **キー**: `AppPrefixAllowList`
- **種類**: `String`
- **値**: SSO への参加が許可されているアプリケーションのアプリケーション バンドル ID プレフィックスのコンマ区切りの一覧です。 このパラメーターによって、特定のプレフィックスで始まるすべてのアプリを SSO に参加させることができます。 iOS の場合、既定値は `com.apple.` に設定され、すべての Apple アプリの SSO が有効になります。 macOS の場合、既定値は `com.apple.` と `com.microsoft.` に設定され、すべての Apple と Microsoft アプリの SSO が有効になります。 管理者は、既定値をオーバーライドするか、 にアプリを `AppBlockList` 追加して、SSO に参加できないようにすることができます。
- **例**: `com.contoso., com.fabrikam.`

##### SSO を特定のアプリで無効にする

- **キー**: `AppBlockList`
- **種類**: `String`
- **値**: SSO への参加が許可されていないアプリケーションのアプリケーション バンドル ID のコンマ区切りの一覧。
- **例**: `com.contoso.studyapp, com.contoso.travelapp`

Safari または Safari View Service の SSO を"*無効*"にするには、バンドル ID を `AppBlockList` に追加して明示的に行う必要があります。

- iOS: `com.apple.mobilesafari`、`com.apple.SafariViewService`
- Macos： `com.apple.Safari`

Note

この設定を使用して Microsoft 認証ライブラリを使用するアプリでは、SSO を無効にすることはできません。

##### 特定のアプリケーションに対して Cookie を使用して SSO を有効にする

高度なネットワーク設定を持つ一部の iOS アプリでは、SSO が有効になっていると予期しない問題が発生するおそれがあります。 たとえば、ネットワーク要求が取り消されたか、中断されたことを示すエラーが表示される場合があります。

ユーザーがアプリケーションへサインインできず、他の設定からそれを有効にした後でも問題がある場合は、それを `AppCookieSSOAllowList` に追加して問題を解決してみてください。

Note

Cookie メカニズムを介した SSO の使用には、厳格な制限があります。 たとえば、Microsoft Entra ID 条件付きアクセス ポリシーとは互換性がありません。また、1 つのアカウントのみがサポートされます。 通常の SSO と互換性がないと判断された一連の限られたアプリケーションに対して、Microsoft のエンジニアリングまたはサポート チームが明示的に推奨しない限り、この機能を使用しないでください。

- **キー**: `AppCookieSSOAllowList`
- **種類**: `String`
- **値**: SSO への参加が許可されているアプリケーションのアプリケーション バンドル ID プレフィックスのコンマ区切りの一覧です。 一覧に含まれるプレフィックスで始まるすべてのアプリが、SSO への参加を許可されます。
- **例**: `com.contoso.myapp1, com.fabrikam.myapp2`

**その他の要件**: `AppCookieSSOAllowList`を使用してアプリケーションの SSO を有効にするには、バンドル ID プレフィックス `AppPrefixAllowList` も追加する必要があります。

この構成は、予期しないサインイン エラーが発生したアプリケーションに対してのみ試してください。 このキーは、iOS アプリにのみ使用され、macOS アプリでは使用されません。

##### キーの概要

Note

このセクションで説明するキーは、Microsoft 認証ライブラリを使用していないアプリにのみ適用されます。

| Key | タイプ | Value |
| --- | --- | --- |
| `Enable_SSO_On_All_ManagedApps` | Integer | `1` はすべてのマネージド アプリで SSO を有効にします。`0` はすべてのマネージド アプリで SSO を無効にします。 |
| `AppAllowList` | String*(コンマ区切りリスト)* | SSO に参加することが許可されているアプリケーションのバンドル ID。 |
| `AppBlockList` | String*(コンマ区切りリスト)* | SSO に参加することが許可されていないアプリケーションのバンドル ID。 |
| `AppPrefixAllowList` | String*(コンマ区切りリスト)* | SSO に参加することが許可されているアプリケーションのバンドル ID プレフィックス。 iOS の場合、既定値は `com.apple.` に設定され、すべての Apple アプリの SSO が有効になります。 macOS の場合、既定値は `com.apple.` と `com.microsoft.` に設定され、すべての Apple と Microsoft アプリの SSO が有効になります。 開発者、顧客または管理者は、既定値をオーバーライドするか、 にアプリを `AppBlockList` 追加して、SSO に参加できないようにすることができます。 |
| `AppCookieSSOAllowList` | String*(コンマ区切りリスト)* | SSO に参加することが許可されているが、特別なネットワーク設定を使用し、他の設定を使用した SSO に問題があるアプリケーションのバンドル ID プレフィックス。 `AppCookieSSOAllowList` に追加するアプリは、`AppPrefixAllowList` にも追加する必要があります。 このキーは、iOS アプリにのみ使用され、macOS アプリには使用されないことに注意してください。 |

##### 一般的なシナリオの設定

- *シナリオ*: SSO をほとんどの管理対象アプリケーションで有効にしたいが、すべてではない。

    | Key | Value |
    | --- | --- |
    | `Enable_SSO_On_All_ManagedApps` | `1` |
    | `AppBlockList` | SSO に参加できないようにしたいアプリのバンドル ID (コンマ区切りリスト)。 |
- *シナリオ* SSO を Safari で無効にしたい (既定では有効) が、SSO をすべてのマネージド アプリで有効にしたい。

    | Key | Value |
    | --- | --- |
    | `Enable_SSO_On_All_ManagedApps` | `1` |
    | `AppBlockList` | SSO に参加できないようにしたい Safari アプリのバンドル ID (コンマ区切りリスト)。<br>    - iOS の場合: `com.apple.mobilesafari`、`com.apple.SafariViewService`<br>    - macOS の場合: `com.apple.Safari` |
- *シナリオ*: SSO をすべてのマネージド アプリといくつかの非マネージド アプリで有効にしたいが、SSO を他のいくつかのアプリで無効にしたい。

    | Key | Value |
    | --- | --- |
    | `Enable_SSO_On_All_ManagedApps` | `1` |
    | `AppAllowList` | SSO の場合に参加できるようにしたいアプリのバンドル ID (コンマ区切りリスト)。 |
    | `AppBlockList` | SSO に参加できないようにしたいアプリのバンドル ID (コンマ区切りリスト)。 |

###### iOS デバイスでのアプリ バンドル ID の確認

Apple では、App Store からバンドル ID を簡単に取得する方法は提供していません。 SSO に使用するアプリのバンドル ID を取得するには、ベンダーまたはアプリ開発者に問い合わせるのが最も簡単です。 この選択肢が使用できない場合、MDM 構成を使用してバンドル ID を確認できます。

1. MDM 構成で次のフラグを一時的に有効にします。

    - **キー**: `admin_debug_mode_enabled`
    - **種類**: `Integer`
    - **値**: 1 または 0
2. このフラグがオンであるときに、バンドル ID を知りたいデバイス上の iOS アプリにサインインします。
3. Authenticator アプリで、**[ヘルプ]**&gt;**[ログの送信]**&gt;**[ログの表示]** を選択します。
4. ログ ファイルで、次の行を探します: `[ADMIN MODE] SSO extension has captured following app bundle identifiers`。 この行により、SSO 拡張機能から認識可能なすべてのアプリケーション バンドル ID が得られます。

これらのバンドル ID を使用して、アプリの SSO を構成します。 完了したら、管理者モードを無効にします。

##### MSAL を使用していないアプリケーションおよび Safari ブラウザーからユーザーがサインインできるようにする

既定では、Microsoft Enterprise SSO プラグインは、新しいトークンの取得中に MSAL を使用する別のアプリによって呼び出されたときに、共有資格情報を取得します。 Microsoft Enterprise SSO プラグインは、構成によっては MSAL を使用しないアプリによって呼び出されたときにも共有資格情報を取得できます。

`browser_sso_interaction_enabled` フラグを有効にすると、MSAL を使用していないアプリで、初期ブートストラップを実行して共有資格情報を取得できるようになります。 Safari ブラウザーでも、初期ブートストラップを実行して共有資格情報を取得できるようになります。

Microsoft Enterprise SSO プラグインにまだ共有資格情報がない場合、Safari ブラウザー、ASWebAuthenticationSession、SafariViewController、または別の許可されたネイティブ アプリケーション内の Microsoft Entra URL からサインインが要求されると、資格情報の取得が試行されます。

フラグを有効にするには、次のパラメーターを使用します。

- **キー**: `browser_sso_interaction_enabled`
- **種類**: `Integer`
- **値**: 1 または 0。 既定では、この値が 1 に設定されています。

Microsoft Enterprise SSO プラグインで提供されるエクスペリエンスがすべてのアプリで一貫性を持つように、iOS と macOS の両方にこの設定が必要です。 この設定は既定で有効になっており、エンド ユーザーが自分の資格情報でサインインできない場合にのみ無効にしてください。

##### OAuth 2 アプリケーションのプロンプトを無効にする

Microsoft Enterprise SSO プラグインがデバイス上の他のアプリケーションに対して機能している場合でも、アプリケーションからユーザーにサインインを求めるメッセージが表示される場合、アプリはプロトコル層で SSO をバイパスしている可能性があります。 このようなアプリケーションでは共有資格情報も無視されます。このプラグインでは、許可されたアプリケーションによって行われたネットワーク要求に資格情報を追加することで SSO が提供されます。

これらのパラメーターは、ネイティブアプリケーションと Web アプリケーションがプロトコル層で SSO をバイパスし、ユーザーにサインイン プロンプトを表示するように SSO 拡張機能を使用しないかどうかを指定します。

デバイス上のすべてのアプリで一貫した SSO エクスペリエンスを得られるように、MSAL を使用していないアプリでこれらの設定のいずれかを有効にすることをお勧めします。 この設定は、ユーザーに予期しないプロンプトが表示される場合にのみ、MSAL を使用するアプリで有効にしてください。

###### Microsoft Authentication Library を使用しないアプリ:

アプリ プロンプトを無効にして、アカウント ピッカーを表示します。

- **キー**: `disable_explicit_app_prompt`
- **種類**: `Integer`
- **値**: 1 または 0。 この値は既定で 1 に設定され、この既定の設定ではプロンプトが減ります。

アプリ プロンプトを無効にし、一致する SSO アカウントの一覧からアカウントを自動的に選択します。

- **キー**: `disable_explicit_app_prompt_and_autologin`
- **種類**: `Integer`
- **値**: 1 または 0。 既定では、この値は 0 に設定されます。

###### Microsoft Authentication Library を使用するアプリ:

[アプリ保護ポリシー](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-protection-policy)が使用されている場合、次の設定はお勧めしません。

アプリ プロンプトを無効にして、アカウント ピッカーを表示します。

- **キー**: `disable_explicit_native_app_prompt`
- **種類**: `Integer`
- **値**: 1 または 0。 既定では、この値は 0 に設定されます。

アプリ プロンプトを無効にし、一致する SSO アカウントの一覧からアカウントを自動的に選択します。

- **キー**: `disable_explicit_native_app_prompt_and_autologin`
- **種類**: `Integer`
- **値**: 1 または 0。 既定では、この値は 0 に設定されます。

##### 予期しない SAML アプリケーション プロンプト

Microsoft Enterprise SSO プラグインがデバイス上の他のアプリケーションに対して機能している場合でも、アプリケーションからユーザーにサインインを求めるメッセージが表示される場合、アプリはプロトコル層で SSO をバイパスしている可能性があります。 アプリケーションで SAML プロトコルが使用されている場合は、Microsoft Enterprise SSO プラグインを使ってアプリで SSO を実行できません。 アプリケーション ベンダーは、この動作に関する通知を受けて、SSO をバイパスしないようアプリに変更を加える必要があります。

##### MSAL 対応アプリケーションの iOS のエクスペリエンスを変更する

MSAL を使用するアプリでは常に、対話型要求で SSO 拡張機能をネイティブに呼び出します。 iOS デバイスによっては、この動作が望ましくない場合があります。 具体的には、ユーザーが Microsoft Authenticator アプリ内で多要素認証も完了する必要がある場合、そのアプリへの対話型リダイレクトの方が、より良いユーザー エクスペリエンスが得られる可能性があります。

この動作は、`disable_inapp_sso_signin` フラグを使用して構成できます。 このフラグが有効になっている場合、MSAL を使用するアプリは、すべての対話型要求で Microsoft Authenticator アプリにリダイレクトされます。 このフラグは、これらのアプリからのサイレント トークン要求、MSAL を使用しないアプリの動作、または macOS アプリには影響しません。 このフラグは既定で無効になっています。

- **キー**: `disable_inapp_sso_signin`
- **種類**: `Integer`
- **値**: 1 または 0。 既定では、この値は 0 に設定されます。

##### Microsoft Entra デバイス登録を構成する

Intune マネージド デバイスの場合、Microsoft Enterprise SSO プラグインを使用すると、ユーザーがリソースにアクセスしようとしたときに Microsoft Entra デバイスの登録を実行できます。 こうすると、より合理化されたエンド ユーザー エクスペリエンスが実現します。

Microsoft Intune を使用して iOS/iPadOS での Just-In Time Registration を有効にするには、次の構成を使用します。

- **キー**: `device_registration`
- **種類**: `String`
- **値**: {{DEVICEREGISTRATION}}

Just In Time Registration の詳細については、[こちら](https://techcommunity.microsoft.com/t5/intune-customer-success/just-in-time-registration-for-ios-ipados-with-microsoft-intune/ba-p/3660843)を参照してください。

##### 条件付きアクセス ポリシーとパスワードの変更

Apple デバイス用の Microsoft Enterprise SSO プラグインは、さまざまな [Microsoft Entra 条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)およびパスワード変更イベントと互換性があります。 互換性を実現するには、`browser_sso_interaction_enabled` を有効にする必要があります。

互換性のあるイベントとポリシーについては、次のセクションで説明します。

###### パスワードの変更とトークンの失効

ユーザーがパスワードをリセットすると、その前に発行されたすべてのトークンが取り消されます。 ユーザーがパスワード リセット イベントの後にリソースにアクセスしようとする場合、通常、ユーザーは各アプリでもう一度サインインする必要があります。 Microsoft Enterprise SSO プラグインが有効な場合、ユーザーは SSO に参加している最初のアプリケーションにサインインするように求められます。 現在アクティブなアプリケーションの上に、Microsoft Enterprise SSO プラグイン独自のユーザー インターフェイスが表示されます。

###### Microsoft Entra 多要素認証

[多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)は、サインイン プロセスでユーザーに別の形式の ID (携帯電話に示されるコードや指紋スキャンなど) を求めるプロセスです。 多要素認証は、特定のリソースで有効にすることができます。 Microsoft Enterprise SSO プラグインが有効になっている場合、ユーザーは、多要素認証を必要とする最初のアプリケーションで認証を実行するように求められます。 現在アクティブなアプリケーションの上に、Microsoft Enterprise SSO プラグイン独自のユーザー インターフェイスが表示されます。

###### ユーザー サインインの頻度

[サインインの頻度](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-session-lifetime#user-sign-in-frequency)は、ユーザーがリソースにアクセスしようとしたときにサインインし直すように求められるまでの期間を定義します。 さまざまなアプリでユーザーがこの期間の経過後にリソースにアクセスしようとする場合、通常、ユーザーはそれらの各アプリでサインインし直す必要があります。 Microsoft Enterprise SSO プラグインが有効な場合、ユーザーは SSO に参加している最初のアプリケーションにサインインするように求められます。 現在アクティブなアプリケーションの上に、Microsoft Enterprise SSO プラグイン独自のユーザー インターフェイスが表示されます。

##### Intune を使用して構成を簡略化する

Intune を MDM サービスとして使用し、Microsoft Enterprise SSO プラグインの構成を容易にすることができます。 たとえば、Intune を使用して、プラグインを有効にしたり、古いアプリを許可リストに追加して SSO に参加させたりできます。

詳細については、「[Intune を使用して Apple デバイス用の Microsoft Enterprise SSO プラグインを展開する](https://learn.microsoft.com/ja-jp/mem/intune/configuration/use-enterprise-sso-plug-in-ios-ipados-macos)」をご参照してください。

### アプリケーションで SSO プラグインを使用する

[Apple デバイス用の MSAL](https://github.com/AzureAD/microsoft-authentication-library-for-objc) のバージョン 1.1.0 以降では、Apple デバイス用の Microsoft Enterprise SSO プラグインがサポートされています。 Microsoft Enterprise SSO プラグインのサポートを追加するには、この方法をお勧めします。 これにより、Microsoft ID プラットフォームのすべての機能が試用できるようになります。

フロントライン ワーカーのシナリオ向けにアプリケーションを構築している場合は、「[iOS デバイスの共有デバイス モード](https://learn.microsoft.com/ja-jp/entra/msal/objc/shared-devices-ios)」でセットアップ情報を参照してください。

### SSO プラグインのしくみを理解する

Microsoft Enterprise SSO プラグインは、[Apple Enterprise SSO フレームワーク](https://developer.apple.com/documentation/authenticationservices/asauthorizationsinglesignonprovider?language=objc)に依存しています。 このフレームワークに参加している ID プロバイダーは、ドメインのネットワーク トラフィックを傍受し、それらの要求の処理方法を強化または変更することができます。 たとえば、SSO プラグインでは、エンドユーザーの資格情報を安全に収集する追加の UI を表示すること、MFA を要求すること、またはアプリケーションにトークンをサイレントで提供することができます。

ネイティブ アプリケーションは、カスタム操作を実装し、SSO プラグインと直接通信することもできます。 詳細については、こちらの [2019 Worldwide Developer Conference のビデオ (Apple 提供)](https://developer.apple.com/videos/play/tech-talks/301/) をご覧ください。

Tip

SSO プラグインのしくみと「[Apple デバイス向け SSO トラブルシューティング ガイド](https://learn.microsoft.com/ja-jp/entra/identity/devices/troubleshoot-mac-sso-extension-plugin)」を使用した Microsoft Enterprise SSO 拡張機能のトラブルシューティングの方法について説明します。

#### MSAL を使用するアプリケーション

[Apple デバイス用の MSAL](https://github.com/AzureAD/microsoft-authentication-library-for-objc) のバージョン 1.1.0 以降では、職場および学校アカウントに対して、Apple デバイス用の Microsoft Enterprise SSO プラグインがネイティブでサポートされています。

[すべての推奨される手順](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-v2-ios)を実行し、既定の[リダイレクト URI 形式](https://learn.microsoft.com/ja-jp/entra/msal/objc/redirect-uris-ios)を使用した場合、特別な構成は必要ありません。 SSO プラグインがあるデバイスでは、すべての対話型およびサイレントなトークン要求に対して、このプラグインが MSAL によって自動的に呼び出されます。 これは、アカウント列挙攻撃およびアカウントの削除の操作に対しても呼び出されます。 MSAL にはカスタム操作に依存するネイティブ SSO プラグイン プロトコルが実装されているため、この設定にすると、エンド ユーザーにとって最もスムーズなネイティブ エクスペリエンスが提供されます。

iOS と iPadOS デバイスでは、SSO プラグインが MDM で有効にされていないが、デバイスに Microsoft Authenticator アプリが存在する場合、MSAL では、対話型トークンの要求に対して Authenticator アプリが代わりに使用されます。 Microsoft Enterprise SSO プラグインでは、Authenticator アプリとの間で SSO を共有します。

#### MSAL を使用しないアプリケーション

MSAL を使用しないアプリケーションでも、管理者がそれらのアプリケーションを許可リストに追加すると、SSO を取得できます。

次の条件が満たされている限り、これらのアプリのコードを変更する必要はありません。

- アプリケーションでは、Apple のフレームワークを使用してネットワーク要求を実行している。 これらのフレームワークの例としては、[WKWebView](https://developer.apple.com/documentation/webkit/wkwebview) や [NSURLSession](https://developer.apple.com/documentation/foundation/nsurlsession) があります。
- アプリケーションで、標準プロトコルを使用して Microsoft Entra ID と通信している。 これらのプロトコルの例としては、OAuth 2、SAML、WS-Federation などがあります。
- アプリケーションのネイティブ UI で、プレーンテキストのユーザー名とパスワードを収集していない。

この場合、アプリケーションでネットワーク要求を作成し、Web ブラウザーを開いてユーザーをサインインすると、SSO が提供されます。 ユーザーが Microsoft Entra サインイン URL にリダイレクトされると、SSO プラグインによって URL が検証され、その URL の SSO 資格情報が確認されます。 資格情報が見つかった場合、SSO プラグインから Microsoft Entra ID に渡されます。これにより、ユーザーに資格情報の入力が求められることなく、アプリケーションがネットワーク要求を完了できるようになります。 さらに、デバイスが Microsoft Entra ID に認識されている場合、デバイスベースの条件付きアクセス チェックを満たすデバイス証明書が SSO プラグインによって渡されます。

非 MSAL アプリに対する SSO をサポートするために、SSO プラグインには、「[プライマリ更新トークンとは](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token#how-is-a-prt-used)」で説明されている Windows ブラウザー プラグインと同様のプロトコルが実装されています。

MSAL ベースのアプリと比較すると、SSO プラグインは、非 MSAL アプリに対してさらに透過的に機能します。 アプリで提供される既存のブラウザー ログイン エクスペリエンスと統合します。

エンド ユーザーには慣れ親しんだエクスペリエンスが提供され、アプリケーションごとにサインインを繰り返す必要はありません。 たとえば、SSO プラグインでは、ネイティブ アカウント ピッカーを表示する代わりに、SSO セッションを Web ベースのアカウント ピッカー エクスペリエンスに追加します。

### デバイス ID キー ストレージ

2024 年 3 月、Microsoft Entra ID は、デバイス ID キーを格納するために Apple のキーチェーンから Apple の Secure Enclave に移行すると発表しました。 2025 年 8 月以降、Secure Storage ロールアウトでは、Secure Enclave がすべての新しいデバイス登録の既定のキー ストレージになります。 新しいデバイス登録では、既定でセキュリティで保護されたストレージ モデルが使用されます。 セキュリティで保護されたエンクレーブをサポートしていない既存のデバイスでは、登録キーがユーザーのキーチェーンに格納されます (従来のログイン キーチェーンには保存されません)。 Secure Storage を使用しないデバイスの既存の機能は変わりません。

アプリケーションまたは MDM ソリューションがキーチェーンを使用して Microsoft Entra デバイス登録キーにアクセスすることに依存している場合は、Microsoft ID プラットフォームとの互換性を維持するために、Microsoft 認証ライブラリ (MSAL) と Enterprise SSO プラグインを使用するように更新する必要があります。

Important

デバイス ID キーを格納するために Secure Enclave を使用するマネージド デバイスは、デバイス ID を Microsoft Entra ID [に報告するために](https://learn.microsoft.com/ja-jp/intune/intune-service/configuration/platform-sso-macos)、Enterprise SSO または [Platform SSO](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview) を使用してプロビジョニングする必要もあります。

#### Microsoft Authentication Library (MSAL) を使用して登録デバイス情報を読み取る

この使用可能な MSAL API を呼び出して、デバイスの登録の詳細を確認できます。

>
>
> ```objc
> - (void)getWPJMetaDataDeviceWithParameters:(nullable MSALParameters *)parameters
>                                forTenantId:(nullable NSString *)tenantId
>                            completionBlock:
>                                (nonnull MSALWPJMetaDataCompletionBlock)
>                                    completionBlock;
> ```

この API ドキュメントの詳細については [、こちらをご覧ください](https://azuread.github.io/microsoft-authentication-library-for-objc/Classes/MSALPublicClientApplication.html#/c:objc%28cs%29MSALPublicClientApplication%28im%29getWPJMetaDataDeviceWithParameters:forTenantId:completionBlock:)。

#### セキュリティで保護されたエンクレーブ ベースのデバイス ID のトラブルシューティング

Secure Enclave ベースのストレージを有効にすると、アクセスできるようにデバイスを設定することを通知するエラー メッセージが表示されることがあります。 このエラー メッセージは、アプリケーションがデバイスのマネージド状態を認識できなかったことを示し、新しいキーの保存場所との互換性がないことを示唆しています。

[Image: このリソースにアクセスするためにデバイスを管理する必要があることをユーザーに通知する条件付きアクセス エラー メッセージのスクリーンショット。]

このエラーは、次の詳細を含む Microsoft Entra ID サインイン ログに表示されます。

- **サインイン エラー コード:**`530003`
- **エラーの理由:**`Device is required to be managed to access this resource.`

テスト中にこのエラー メッセージが表示される場合は、まず、SSO 拡張機能が正常に有効になっていることと、必要なアプリケーション固有の拡張機能 (たとえば、[Chrome 用 Microsoft シングル サインオン](https://chromewebstore.google.com/detail/microsoft-single-sign-on/ppnbnpeolgkicgegkbkbjmhlideopiji)) がインストールされていることを確認します。 このメッセージが引き続き表示される場合は、アプリケーションのベンダーに連絡して、新しい保存場所との非互換性について注意を喚起することをお勧めします。

##### セキュリティで保護されたエンクレーブのトラブルシューティング

セキュリティで保護されたエンクレーブに関する問題をトラブルシューティングする必要がある場合は、Apple デバイスの MDM 構成で次のキーを更新することで無効にすることができます。

- **キー**: `use_most_secure_storage`
- **種類**: `Integer`
- **値**: 0

Warning

セキュリティで保護されたエンクレーブの無効化は、トラブルシューティング中にのみ行う必要があります。

##### トラブルシューティング: テストのためにセキュリティで保護されたエンクレーブを無効にする

何らかの理由で Secure Enclave を無効にする必要がある場合は、次の推奨手順に従います。

1. **構成を更新する**: MDM 構成で整数型に対してフラグを`use_most_secure_storage`に設定して、`0`を無効にします。
2. **デバイスの登録を解除する**: 次のいずれかの方法を使用してデバイスの登録を削除します。

- **Microsoft Authenticator**: デバイス登録メニューに移動し、登録解除の手順に従います。
    1. 左上のホーム画面からメニュー `...` を選択します
    2. 設定、デバイス登録を押します。
    3. デバイスに現在登録されている会社名を登録ボタンを押してください。
    4. デバイスの登録解除を押します。
- **Intune ポータル サイト**: ポータル サイトにログインし、[デバイス] タブを選択します。
    1. デバイスの一覧からデバイスを選択します。
    2. 名前の下で `...`を押すと、メニューがポップアップ表示されます。
    3. [デバイスの削除] を選択します。

1. **デバイスを再登録する**: 登録を解除した後、次のいずれかを使用してデバイスをもう一度登録します。

- **Intune ポータル サイト**: デバイスを選択して再登録する
- **Microsoft Authenticator**: メニューに移動し、[デバイスの登録] を選択し、もう一度デバイスを登録します (注: この方法は macOS では使用できません)

Important

ストレージの場所の変更を有効にするには、デバイスの登録を解除して再登録する必要があります。 再登録せずに構成フラグを更新するだけでは、既存のデバイス登録の保存場所は変更されません。

##### Secure Storage のオプトアウト

セキュリティで保護されたストレージのロールアウトからテナントをオプトアウトするには、Microsoft カスタマー サポートに問い合わせて、セキュリティで保護されたストレージの展開からの除外を要求してください。 処理が完了すると、テナントは最大 6 か月間、このロールアウトから一時的に除外されます。 以前にセキュリティで保護されたストレージに登録されているテナント内のすべてのデバイスは、一時的なオプトアウトが完了した後にデバイスを削除して再追加するための前のガイダンスに従う必要があります。

Important

セキュリティで保護されたストレージからの一時的な除外は 6 か月に制限され、お勧めできません。これは、組織がこのセキュリティ強化と今後のセキュリティ強化の恩恵を受けるのを妨げる可能性があり、Microsoft が最終的に従来のストレージ方法を段階的に除外するときにサポート オプションを制限する可能性があるためです。

将来の日付に Secure Storage をオプトインするには、 [Microsoft カスタマー サポート](https://learn.microsoft.com/ja-jp/services-hub/unified/support/open-support-requests)にお問い合わせください。

#### 影響を受けたシナリオ

これらの変更の影響を受けるいくつかの一般的なシナリオを、次の一覧に示します。 既定では、Apple のキーチェーンを介したデバイス ID アーティファクトへのアクセスに依存するすべてのアプリケーションが影響を受けます。

Note

これは完全なリストではなく、アプリケーションのコンシューマーとベンダーの両方に、この新しいデータストアとの互換性のためにソフトウェアをテストすることをお勧めします。

##### ブラウザにおける登録/登録済みデバイスの条件付きアクセス ポリシーサポート

セキュリティで保護されたエンクレーブ ベースのストレージが有効になっている場合、ブラウザーはデバイスの条件付きアクセス ポリシーをサポートするために特定の構成を必要とします。

**Safari (iOS および macOS)**

- 組み込みの SSO 統合 - 追加の構成は必要ありません

**Google Chrome (macOS)**

- [Microsoft シングル サインオン](https://chromewebstore.google.com/detail/windows-accounts/ppnbnpeolgkicgegkbkbjmhlideopiji)拡張機能をインストールするか、
- Enterprise SSO の自動サポートのための Chrome 135+ への更新

**Microsoft Edge (iOS および macOS)**

- Microsoft SSO の自動統合のために Edge プロファイルにサインインする
- 詳細情報: [Microsoft Edge のセキュリティと ID](https://learn.microsoft.com/ja-jp/deployedge/microsoft-edge-security-identity#seamless-sso)

**Firefox (macOS)**

- ブラウザー統合用に MicrosoftEntraSSO ポリシーを構成する
- 参照: [Firefox ポリシー テンプレート](https://mozilla.github.io/policy-templates/#microsoftentrasso) と [Firefox Enterprise 133 リリース ノート](https://support.mozilla.org/en-US/kb/firefox-enterprise-133-release-notes)

### macOS 15.3 および iOS 18.1.1 の重要な更新プログラムが Enterprise SSO に影響を与える

#### Overview

macOS 15.3 と iOS 18.1.1 の最近の更新により、Enterprise SSO 拡張機能フレームワークが正しく機能しなくなり、Entra ID と統合されているすべてのアプリで予期しない認証エラーが発生します。 影響を受けるユーザーは、"4s8qh" というタグが付いたエラーが発生する可能性もあります。

#### 根本原因

この問題の根本原因は、基になる PluginKit レイヤーに回帰する可能性があるため、Microsoft Enterprise SSO Extension がオペレーティング システムによって起動されないようにします。 Apple は問題を調査し、解決策について協力しています。

#### 影響を受けたユーザーの特定

ユーザーが影響を受けるかどうかを判断するには、sysdiagnose を収集し、次のエラー情報を探します。

エラードメイン=PlugInKit コード=16 別のバージョンが使用中

このエラーの例を次に示します。

`Request for extension <EXConcreteExtension: 0x60000112d080> {id = com.microsoft.CompanyPortalMac.ssoextension} failed with error Error Domain=PlugInKit Code=16 "other version in use: <id<PKPlugIn>: 0x1526066c0; core = <[...] [com.microsoft.CompanyPortalMac.ssoextension(5.2412.0)],[...] [/Applications/Company Portal.app/Contents/PlugIns/Mac SSO Extension.appex]>, instance = [(null)], state = 1, useCount = 1>" UserInfo={NSLocalizedDescription=other version in use: <id<PKPlugIn>: 0x1526066c0; core = <[...] [com.microsoft.CompanyPortalMac.ssoextension(5.2412.0)],[...] [/Applications/Company Portal.app/Contents/PlugIns/Mac SSO Extension.appex]>, instance = [(null)], state = 1, useCount = 1>}`

#### 回復手順

ユーザーがこの問題の影響を受けた場合は、デバイスを再起動して回復できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/application-consent-experience"} -->
## Microsoft Entra ID でのアプリケーションの同意エクスペリエンス - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/application-consent-experience
- Service: identity-platform
- Article date: 2025-02-27
- Summary: Microsoft Entra の同意エクスペリエンスについて、Microsoft Entra ID でアプリケーションを管理および開発する場合の使用方法を詳しく説明します

この記事では、Microsoft Entra アプリケーションの同意ユーザー エクスペリエンスについて説明します。 これにより、ご自分の組織でアプリケーションをインテリジェントに管理したり、よりシームレスな同意エクスペリエンスでアプリケーションを開発したりすることができます。

同意は、保護されたリソースにアプリケーションが代理でアクセスする認証を、ユーザーが許可するプロセスです。 管理者またはユーザーは、組織または個人のデータへのアクセスを許可するように同意を求められることがあります。

同意を許可する実際のユーザー エクスペリエンスは、ユーザーのテナント、ユーザーの機関でのスコープ (またはロール)、クライアント アプリケーションによって要求されている[アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)の種類に設定されたポリシーによって異なります。 つまり、そのアプリケーション開発者とテナント管理者は、同意エクスペリエンスの一部を制御できます。 管理者は、テナントで同意エクスペリエンスを制御するために、テナント上でポリシーまたはアプリを柔軟に設定および無効化することができます。 アプリケーション開発者は、要求されるアクセス許可の種類を指定できます。 また、ユーザーの同意フローまたは管理者の同意フローをユーザーに案内するかどうかを決定することもできます。

- **ユーザーの同意フロー**は、現在のユーザーのみに対する同意を記録する目的で、アプリケーション開発者がユーザーを承認エンドポイントに直接アクセスさせます。
- **管理者の同意フロー**は、テナント全体に対する同意を記録する目的で、アプリケーション開発者がユーザーを管理者の同意エンドポイントに直接アクセスさせます。 管理者の同意フローが適切に動作するようにするため、アプリケーション開発者はアプリケーション マニフェストで `RequiredResourceAccess` プロパティのアクセス許可をすべて一覧する必要があります。 詳細については、[アプリケーション マニフェスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest)に関するページを参照してください。

注

外部テナントのアプリケーションの場合、お客様はアクセス許可自体に同意できません。 管理者は、アプリケーションが自分の代わりにリソースにアクセスすることに同意する必要があります。 詳細については、「管理者の [同意を付与する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#grant-admin-consent-external-tenants-only)」を参照してください。

### 同意プロンプトの構成要素

同意プロンプトは、代理でクライアント アプリケーションが保護されたリソースにアクセスすることを信頼するかどうかを判断するために必要な情報を、ユーザーが確実に所有できるように設計されています。 構成要素を理解することは、同意を与えるユーザーがより多くの情報に基づいた意思決定を行うのに役立ち、開発者がより良いユーザー エクスペリエンスを構築するのに役立ちます。

次の図と表では、同意プロンプトの構成要素に関する情報を示しています。

[Image: 同意プロンプトの構成要素]

| # | コンポーネント | 目的 |
| --- | --- | --- |
| 1 | ユーザー識別子 | この識別子は、クライアント アプリケーションが代わりに、保護されたリソースにアクセスすることを要求していることを表します。 |
| 2 | タイトル | このタイトルは、ユーザーの同意フローまたは管理者の同意フローのいずれをユーザーが経由しているかに基づいて変更されます。 ユーザーの同意フローでは、タイトルは "アクセス許可が要求されました" ですが、管理者の同意フローでは、タイトルには別の行 "Accept for your organization" があります。 |
| 3 | アプリのロゴ | このイメージは、ユーザーがアクセスしようとしているアプリがこのアプリであるかどうかの目印になります。 このイメージは、アプリケーション開発者によって提供され、このイメージの所有権は検証されていません。 |
| 4 | アプリの名前 | この値によって、ユーザーのデータへのアクセスを要求しているアプリケーションをユーザーに通知します。 この名前は開発者によって提供され、このアプリ名の所有権は検証されていないことに注意してください。 |
| 5 | 発行元の名前と検証 | 青い "検証済み" バッジは、アプリの発行元が Microsoft Partner Network アカウントを使用して ID を確認し、検証プロセスを完了したことを意味します。 アプリの発行元が検証済みの場合は、発行元の名前が表示されます。 アプリの発行元が確認されていない場合は、発行元名の代わりに [未確認] と表示されます。 詳細については、「[発行元の検証](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview)」を参照してください。 発行元名を選択すると、使用可能なアプリ情報が表示されます。 情報には、発行元名、発行元ドメイン、作成日、認定の詳細、応答 URL が含まれます。 |
| 6 | Microsoft 365 認定 | Microsoft 365 認定ロゴは、主要な業界標準フレームワークから派生したコントロールに対してアプリが審査されることを意味します。 これは、顧客データを保護するために、強力なセキュリティとコンプライアンスのプラクティスが実施されていることを示しています。 詳細については、[Microsoft 365 認定](https://learn.microsoft.com/ja-jp/microsoft-365-app-certification/docs/certification)に関する記事を参照してください。 |
| 7 | パブリッシャー情報 | アプリケーションが Microsoft によって公開されているかどうかが表示されます。 |
| 8 | アクセス許可 | この一覧には、クライアント アプリケーションによって要求されているアクセス許可が含まれます。 ユーザーは、要求されているアクセス許可の種類を常に評価して、クライアント アプリケーションが自分の代わりにアクセスする権限を持つデータを理解する必要があります。 アプリケーション開発者は、最小限の特権を含むアクセス許可を要求することをお勧めします。 |
| 9 | アクセス許可の説明 | この値は、アクセス許可を公開しているサービスによって提供されます。 アクセス許可の説明を表示するには、アクセス許可の横にあるシェブロンを切り替える必要があります。 |
| 10 | `https://myapps.microsoft.com` | このリンクを使用すると、ユーザーは現在データにアクセスできる Microsoft 以外のアプリケーションを確認および削除できます。 |
| 11 | こちらでご報告ください | このリンクは、アプリを信頼していない場合、別のアプリを偽装していると思われる場合、データを悪用する可能性がある場合、またはその他の理由で疑わしいアプリを報告するために使用されます。 |

### 一般的なシナリオと同意エクスペリエンス

次のセクションでは、一般的なシナリオと、それぞれの想定される同意エクスペリエンスについて説明します。

#### アプリにはユーザーに付与する権限があるアクセス許可が必要である

この同意シナリオでは、ユーザーは、そのユーザーの権限の範囲内にあるアクセス許可セットを必要とするアプリにアクセスします。 ユーザーは、ユーザーの同意フローにリダイレクトされます。

管理者は、従来の同意プロンプトに対して、テナント全体に代わって同意を与える別のコントロールを表示します。 コントロールは既定でオフに設定されているため、管理者が明示的にチェック ボックスをオンにした場合にのみ、テナント全体に代わって同意が付与されます。 このチェック ボックスは、少なくとも [特権ロール管理者ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)に対してのみ表示されるため、クラウド管理者とアプリ管理者にはこのチェック ボックスは表示されません。

[Image: シナリオ 1a の同意プロンプト]

ユーザーには従来の同意プロンプトが表示されます。

[Image: 従来の同意プロンプトを示しているスクリーンショット。]

#### アプリにはユーザーに付与する権限がないアクセス許可が必要である

この同意シナリオでは、ユーザーは、ユーザーの権限の範囲外にある少なくとも 1 つのアクセス許可を必要とするアプリにアクセスします。

管理者は、従来の同意プロンプトで、テナント全体に代わって同意できるようにする別のコントロールを表示します。

[Image: シナリオ 1a の同意プロンプト]

管理者ではないユーザーはアプリケーションへの同意をブロックされ、管理者にアプリへのアクセスを求めるように指示されます。 ユーザーのテナントで管理者の同意ワークフローが有効になっている場合、ユーザーは同意プロンプトから管理者の承認を求めるリクエストを送信できます。 管理者の同意ワークフローについて詳しくは、「[管理者の同意ワークフロー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/admin-consent-workflow-overview)」を参照してください。

[Image: アプリへのアクセスを管理者に依頼するようユーザーに通知する同意プロンプトのスクリーンショット。]

#### ユーザーが管理者の同意フローにリダイレクトされる

この同意シナリオでは、ユーザーは管理者の同意フローに移動するか、リダイレクトされます。

管理ユーザーには管理者の同意プロンプトが表示されます。 このプロンプトで変更されたタイトルとアクセス許可の説明は、このプロンプトを受け入れることで、テナント全体に代わって要求されたデータへのアクセス権をアプリに付与するという事実を強調しています。

[Image: シナリオ 3a の同意プロンプト]

ユーザーはアプリケーションへの同意の許可からブロックされ、そのアプリへのアクセス許可を管理者に要求するように指示されます。

[Image: アプリへのアクセスを管理者に依頼するようユーザーに通知する同意プロンプトのスクリーンショット。]

#### Microsoft Entra 管理センターを使用した管理者の同意

このシナリオでは、管理者はアプリケーションによって要求されるすべてのアクセス許可に同意します。これには、テナント内のすべてのユーザーに代わって委任されたアクセス許可を含めることができます。 管理者は、**Microsoft Entra 管理センター**のアプリケーション登録の [\[API のアクセス許可\]](https://entra.microsoft.com) ページで同意を許可します。

[Image: Microsoft Entra 管理センターでの明示的な管理者の同意のスクリーンショット。]

アプリケーションに新しいアクセス許可が必要でない限り、そのテナント内のすべてのユーザーには同意ダイアログが表示されません。 委任されたアクセス許可に同意できる管理者ロールについては、「[Microsoft Entra ID の管理者ロールのアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)」を参照してください。

重要

現時点では、MSAL.js を使用するシングルページ アプリケーション (SPA) では、**[アクセス許可の付与]** ボタンを使用して明示的に同意を付与する必要があります。 そうしないと、アクセス トークンが要求されたときにアプリケーションでエラーが発生します。

### 一般的な問題

このセクションでは、同意エクスペリエンスに関する一般的な問題と考えられるトラブルシューティングのヒントについて説明します。

- 403 エラー

    - このケースは委任されたシナリオ ですか? ユーザーはどのようなアクセス許可を持っていますか?
    - エンドポイントを使用するために必要なアクセス許可が追加されていますか?
    - [トークン](https://jwt.ms/)を確認して、エンドポイントを呼び出すために必要な要求があるかどうかを確かめます。
    - どのアクセス許可に同意しますか? だれが同意したのですか?
- ユーザーが同意できない

    - テナント管理者が組織のユーザーの同意を無効にしたかどうかを確認する
    - 要求するアクセス許可が、管理者によって制限されているアクセス許可であるかどうかを確認します。
- 管理者の同意後もユーザーがブロックされる

    - [静的アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/consent-types-developer)が、動的に要求されたアクセス許可のスーパーセットとして構成されているかどうかを確認します。
    - アプリにユーザー割り当てが必要かどうかを確認します。

### 既知のエラーのトラブルシューティングを行う

トラブルシューティング手順については、「[アプリケーションに同意すると、予期しないエラーが発生する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-sign-in-unexpected-user-consent-error)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/application-model"} -->
## アプリケーション モデル - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/application-model
- Service: identity-platform
- Article date: 2024-04-26
- Summary: アプリケーションを Microsoft ID プラットフォームと統合できるように登録するプロセスについて説明します。

アプリケーションは、ユーザー自身をサインインさせたり、サインインを ID プロバイダーに委任したりできます。 この記事では、アプリケーションを Microsoft ID プラットフォームに登録するために必要な手順について説明します。

### アプリケーションを登録する

ユーザーが特定のアプリにアクセスできるかどうかを ID プロバイダーが認識するには、ユーザーとアプリケーションの両方を ID プロバイダーに登録する必要があります。 アプリケーションを Microsoft Entra ID に登録すると、アプリケーションの ID 構成が提供されます。これにより、アプリケーションは Microsoft ID プラットフォームと統合できます。 アプリを登録すると、次のことができます。

- サインイン ダイアログ ボックスでアプリケーションのブランドをカスタマイズします。 サインインは、ユーザーがアプリで最初に使用するエクスペリエンスであるため、このブランド化は重要です。
- ユーザーが組織に属している場合にのみサインインを許可するかどうかを決定します。 このアーキテクチャは、シングルテナント アプリケーションと呼ばれます。 または、マルチテナント アプリケーションと呼ばれる任意の職場または学校アカウントを使用して、ユーザーにサインインを許可することもできます。 LinkedIn や Google などの個人用 Microsoft アカウントやソーシャル アカウントを許可することもできます。
- スコープのアクセス許可を要求します。 たとえば、サインインしているユーザーのプロファイルを読み取るためのアクセス許可を付与する "user.read" スコープを要求できます。
- Web API へのアクセスを定義するスコープを定義します。 通常、アプリが API にアクセスする場合は、定義したスコープへのアクセス許可を要求する必要があります。
- アプリの ID を証明する Microsoft ID プラットフォームとシークレットを共有します。 シークレットの使用は、アプリが機密クライアント アプリケーションである場合に関連します。 機密 [クライアント アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-glossary#client-application) は、 [Web クライアント](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-glossary#web-client)のように資格情報を安全に保持できるアプリケーションです。 資格情報を格納するには、信頼できるバックエンド サーバーが必要です。

アプリが登録されると、トークンを要求するときに Microsoft ID プラットフォームと共有する一意の識別子が付与されます。 アプリが機密クライアント アプリケーションの場合、証明書またはシークレットが使用されたかどうかに応じて、シークレットまたは公開キーも共有されます。

Microsoft ID プラットフォームは、次の 2 つの主要な機能を満たすモデルを使用してアプリケーションを表します。

- サポートされている認証プロトコルによってアプリを識別します。
- 認証に必要なすべての識別子、URL、シークレット、関連情報を指定します。

Microsoft ID プラットフォーム:

- 実行時に認証をサポートするために必要なすべてのデータを保持します。
- アプリがアクセスする必要があるリソースと、特定の要求を満たす必要がある状況を決定するためのすべてのデータを保持します。
- アプリ開発者のテナント内および他の Microsoft Entra テナントにアプリ プロビジョニングを実装するためのインフラストラクチャを提供します。
- トークン要求時にユーザーの同意を処理し、テナント間でのアプリの動的プロビジョニングを容易にします。

[*同意*](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-glossary#consent) とは、リソース所有者に代わって、特定のアクセス許可の下で、保護されたリソースにアクセスするための承認をクライアント アプリケーションに付与するリソース所有者のプロセスです。 Microsoft ID プラットフォームでは、次のことが可能になります。

- ユーザーと管理者は、アプリが自分の代わりにリソースにアクセスするための同意を動的に許可または拒否します。
- 管理者は最終的に、実行できるアプリと、特定のアプリを使用できるユーザー、およびディレクトリ リソースへのアクセス方法を決定します。

### マルチテナント アプリ

重要

マルチテナント アプリケーション (MTA) は、各クラウド内でサービス プリンシパル機関が分離されているため、クラウド境界を越えて機能しません。 たとえば、アプリケーション オブジェクトが商用クラウドでホストされている場合、関連付けられているサービス プリンシパルは、顧客のオンボード中にローカルに作成されます。 このプロセスは、機関 URL が異なるため (たとえば、 `.com` と `.us`)、非互換性が発生するため、クラウド境界を越えると失敗します。

Microsoft ID プラットフォームでは、 [アプリケーション オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-glossary#application-object) によってアプリケーションが記述されます。 デプロイ時に、Microsoft ID プラットフォームは、アプリケーション オブジェクトをブループリントとして使用して [サービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-glossary#service-principal-object)を作成します。これは、ディレクトリまたはテナント内のアプリケーションの具体的なインスタンスを表します。 サービス プリンシパルは、アプリが特定のターゲット ディレクトリで実際に実行できる内容、使用できるユーザー、アクセスできるリソースなどを定義します。 Microsoft ID プラットフォームは、同意を通じてアプリケーション オブジェクトからサービス プリンシパルを作成します。

次の図は、同意によって駆動される簡略化された Microsoft ID プラットフォームのプロビジョニング フローを示しています。 *A* と *B* の 2 つのテナントが表示されます。

- *テナント A* はアプリケーションを所有します。
- *テナント B* は、サービス プリンシパルを使用してアプリケーションをインスタンス化しています。

[Image: 同意に基づく簡略化されたプロビジョニング フローを示す図。]

このプロビジョニング フローでは、次の操作を行います。

1. テナント B のユーザーがアプリでサインインを試みます。 承認エンドポイントは、アプリケーションのトークンを要求します。
2. ユーザー資格情報が取得され、認証用に検証されます。
3. ユーザーは、アプリがテナント B にアクセスするための同意を求められます。
4. Microsoft ID プラットフォームは、テナント A のアプリケーション オブジェクトを、テナント B にサービス プリンシパルを作成するためのブループリントとして使用します。
5. ユーザーは要求されたトークンを受け取ります。

このプロセスは、より多くのテナントに対して繰り返すことができます。 テナント A は、アプリ (アプリケーション オブジェクト) のブループリントを保持します。 アプリに同意が与えられている他のすべてのテナントのユーザーと管理者は、各テナントの対応するサービス プリンシパル オブジェクトを介してアプリケーションが実行できる操作を制御します。 詳細については、「 [Microsoft ID プラットフォームのアプリケーション オブジェクトとサービス プリンシパル オブジェクト」を参照](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/authentication-flows-app-scenarios"} -->
## Microsoft ID プラットフォームのアプリの種類と認証フロー - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios
- Service: identity-platform
- Article date: 2025-04-14
- Summary: ID の認証、トークンの取得、保護された API の呼び出しなど、Microsoft ID プラットフォームのアプリケーション シナリオについて説明します。

Microsoft ID プラットフォームは、さまざまなモダン アプリケーション アーキテクチャのための認証をサポートしています。 アーキテクチャはいずれも、業界標準のプロトコル [OAuth 2.0 and OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols) に基づいています。 アプリケーションでは、[Microsoft ID プラットフォームの認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用して ID が認証され、保護された API にアクセスするためのトークンが取得されます。

この記事では、認証フローと、アプリケーションでそれらを使用するシナリオについて説明します。

### アプリケーションのカテゴリ

[セキュリティ トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-tokens)を取得できるアプリケーションには、以下をはじめとするいくつかの種類があります。

- ウェブアプリ
- モバイル アプリ
- デスクトップ アプリ
- Web API

また、ブラウザーがインストールされていないデバイスやモノのインターネット (IoT) 上で運用されているデバイスで稼働しているアプリからも、トークンを取得できます。

以降のセクションでは、アプリケーションのカテゴリについて説明します。

#### 保護されたリソースとクライアント アプリケーション

認証シナリオには、次の 2 つのアクティビティが含まれます。

- **保護された Web API のセキュリティ トークンの取得**:Microsoft が開発し、サポートしている [Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) の使用をお勧めします。
- **Web API (または Web アプリ) の保護**:これらのリソースの保護に関する課題の 1 つに、セキュリティ トークンの検証があります。 Microsoft では、一部のプラットフォームについて[ミドルウェア ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を提供しています。

#### ユーザーありまたはユーザーなし

ほとんどの認証シナリオでは、サインインしたユーザーのためにトークンを取得することになります。

[Image: ユーザーありのシナリオ]

ただし、デーモン アプリも存在します。 それらのシナリオではユーザーが存在せず、アプリケーションが自らのためにトークンを取得します。

[Image: デーモン アプリを使ったシナリオ]

#### シングルページ、パブリック クライアント、機密クライアント アプリケーション

セキュリティ トークンは、さまざまなアプリケーションから取得できます。 そのようなアプリケーションは多くの場合、次の 3 つのカテゴリに分類されます。 それぞれ、併用するライブラリとオブジェクトが異なります。

- **シングルページ アプリケーション**:SPA とも呼ばれる Web アプリで、ブラウザーで実行している JavaScript または TypeScript アプリからトークンを取得します。 モダン アプリケーションには、主に JavaScript で記述されたシングルページのアプリケーションがフロントエンドに備わっていることが少なくありません。 アプリケーションで Angular、React、Vue などのフレームワークを使用することもよくあります。 MSAL.js は、シングルページ アプリケーションをサポートする唯一の Microsoft 認証ライブラリです。
- **パブリック クライアント アプリケーション**:このカテゴリのアプリは、常にユーザーをサインインさせます。たとえば、次のタイプのアプリがあります。

    - サインイン ユーザーの代わりに Web API を呼び出すデスクトップ アプリケーション
    - モバイル アプリ
    - ブラウザーがインストールされていないデバイス (IoT 上で運用されているデバイスなど) で稼働しているアプリ
- **機密クライアント アプリケーション**:このカテゴリには、次のようなアプリが該当します。

    - Web API を呼び出す Web アプリ
    - Web API を呼び出すための Web API
    - デーモン アプリケーション (Linux デーモンや Windows サービスのようにコンソール サービスとして実装されている場合も含む)

#### サインイン対象ユーザー

利用できる認証フローは、サインインの対象ユーザーによって異なります。 一部のフローは、職場または学校アカウントでのみ利用できます。 職場または学校アカウントと個人用の Microsoft アカウントの両方で利用できるものもあります。

詳細については、「[サポートされているアカウントの種類](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-supported-account-types#account-type-support-in-authentication-flows)」を参照してください。

### アプリケーションのタイプ

Microsoft ID プラットフォームでは、これらのアプリ アーキテクチャのための認証がサポートされています。

- シングルページアプリケーション
- ウェブアプリ
- Web API
- モバイル アプリ
- ネイティブ アプリ
- デーモン アプリ
- サーバーサイド アプリ

アプリケーションでは、さまざまな認証フローを使用してユーザーのサインインを行い、トークンを取得して保護された API を呼び出します。

#### シングルページアプリケーション

最新の Web アプリの多くは、クライアント側のシングル ページ アプリケーションとして構築されています。 これらのアプリケーションでは、JavaScript またはフレームワーク (Angular、Vue、React など) が使用されています。 このようなアプリケーションは、Web ブラウザー内で稼働します。

シングルページ アプリケーションは、認証の特性の点で、従来からあるサーバー側の Web アプリとは異なります。 Microsoft ID プラットフォームを使うと、シングルページ アプリケーションでユーザーをサインインさせ、バックエンド サービスまたは Web API にアクセスするためのトークンを取得することができます。 Microsoft ID プラットフォームでは、JavaScript アプリケーション用の 2 つの付与タイプが提供されています。

| MSAL.js (2.x) | MSAL.js (1.x) |
| --- | --- |
| [Image: シングルページ アプリケーション: 認証] | [Image: シングルページ アプリケーション: 暗黙的] |

#### ユーザーをサインインさせる Web アプリ

[Image: ユーザーをサインインさせる Web アプリ]

ユーザーをサインインさせる Web アプリを効果的に保護するには、次の方法を使用します。

- 開発に .NET 環境を採用している場合には、ASP.NET OpenID Connect ミドルウェアを使用した ASP.NET または ASP.NET Core を使用します。 リソース保護の一環として発生するセキュリティ トークンの検証処理については、MSAL ライブラリではなく、[.NET 用の IdentityModel 拡張機能](https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnet/wiki)が担当します。
- 開発に Node.js を採用している場合には、[MSAL ノード](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) を使用します。

詳細については、「 [サンプル Web アプリでユーザーをサインインする」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in)参照してください。

#### ユーザーをサインインさせ、そのユーザーに代わって Web API を呼び出す Web アプリ

[Image: Web API を呼び出す Web アプリ]

ユーザーに代わって Web アプリから Web API を呼び出すには、承認コード フローを使用し、取得したトークンをトークン キャッシュに格納します。 必要に応じて、MSAL によりトークンが更新されるほか、コントローラーによりキャッシュからトークンが取得されます。

詳細については、[Web API を呼び出す Web アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-app-configuration)に関するページを参照してください。

#### サインイン済みのユーザーに代わって Web API を呼び出すデスクトップ アプリ

ユーザーをサインインさせるデスクトップ アプリで Web API を呼び出す場合には、MSAL に用意されている対話型のトークン取得メソッドを使用します。 このような対話型メソッドを使用すると、サインイン UI のエクスペリエンスを制御できます。 MSAL では、この対話に Web ブラウザーを使用します。

[Image: Web API を呼び出すデスクトップ アプリ]

Windows ドメインに参加しているか、Microsoft Entra ID を使用して参加しているコンピューターで稼働している Windows ホスト アプリケーションについては、もう 1 つ選択肢があります。 このようなアプリケーションでは、[統合 Windows 認証](https://aka.ms/msal-net-iwa)を使用すると、確認を表示せずにトークンを取得します。

ブラウザーがインストールされていないデバイス上で稼働しているアプリケーションであっても、ユーザーのために API を呼び出すことは可能です。 認証するには、Web ブラウザーがインストールされている別のデバイス上でユーザーがサインインする必要があります。 このシナリオでは、[デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code)を使用する必要があります。

[Image: デバイス コード フロー]

パブリック クライアント アプリケーションであれば[ユーザー名とパスワードを使ったフロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-username-password)を使用することもできますが、お勧めはしません。 もっとも、DevOps などの一部のシナリオではこのフローが必要になります。

ユーザー名/パスワード フローを使用すると、アプリケーションが制限され、セキュリティで保護されたとは見なされなくなります。 たとえば、アプリケーションでは、Microsoft Entra ID の多要素認証や条件付きアクセス ツールを使用する必要があるユーザーをサインインさせることができなくなります。 また、アプリケーションでシングル サインオン (SSO) のメリットを享受することもできません。 ユーザー名とパスワードを使った認証は先進認証の原則に反しており、レガシへの対応のためにのみ提供されています。

デスクトップ アプリでトークン キャッシュを永続的にする場合は、[トークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization)をカスタマイズすることができます。 デュアル トークン キャッシュのシリアル化を実装すると、後方互換性と前方互換性を備えたトークン キャッシュを利用できるようになります。

詳細については、[Web API を呼び出すデスクトップ アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-app-configuration)に関するページを参照してください。

#### 対話ユーザーに代わって Web API を呼び出すモバイル アプリ

モバイル アプリケーションでは、デスクトップ アプリケーションと同じように、MSAL に用意されている対話型のトークン取得メソッドを呼び出して、Web API を呼び出すためのトークンを取得します。

[Image: Web API を呼び出すモバイル アプリ]

MSAL iOS と MSAL Android では、既定でシステム Web ブラウザーが使用されます。 もっとも、代わりに埋め込みの Web ビューを使用するように指定することもできます。 モバイル プラットフォームには、iOS または Android に依存する固有性があります。

デバイス ID やデバイス登録に関連して条件付きアクセスを使用するシナリオなど、一部のシナリオでは、デバイス上にブローカーをインストールする必要があります。 ブローカーにはたとえば、Microsoft ポータル サイト (Android)、Microsoft Authenticator (Android および iOS) があります。

詳細については、[Web API を呼び出すモバイル アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-mobile-app-configuration)に関するページを参照してください。

注

MSAL iOS または MSAL Android を使用しているモバイル アプリでは、アプリ保護ポリシーを適用できます。 このポリシーを使うと、保護されているテキストをユーザーがコピーできないようにしたりすることができます。 モバイル アプリは Intune によって管理され、Intune によりマネージド アプリとして認識されます。 詳細については、「[Microsoft Intune App SDK の概要](https://learn.microsoft.com/ja-jp/mem/intune/developer/app-sdk)」を参照してください。

[Intune SDK](https://learn.microsoft.com/ja-jp/mem/intune/developer/app-sdk-get-started) は MSAL ライブラリとは別のものであり、独自に Microsoft Entra ID と対話します。

#### 保護された Web API

Microsoft ID プラットフォーム エンドポイントを使用すると、アプリの RESTful API などの Web サービスをセキュリティで保護できます。 保護された Web API は、アクセス トークンを使用して呼び出されます。 トークンは、API のデータの保護と受信要求の認証に役立てられます。 Web API の呼び出し元によって、HTTP 要求の Authorization ヘッダーにアクセス トークンが付加されます。

ASP.NET または ASP.NET Core Web API を保護する場合は、アクセス トークンを検証します。 この検証には、ASP.NET JWT ミドルウェアを使用します。 検証は MSAL.NET ではなく、[.NET ライブラリ用の IdentityModel 拡張機能](https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnet/wiki)によって行われます。

詳細については、[保護された Web API](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-protected-web-api-expose-scopes)に関するページを参照してください。

#### ユーザーに代わって別の Web API を呼び出す Web API

保護された Web API から、ユーザーに代わって別の Web API を呼び出すには、アプリでダウンストリームの Web API のトークンを取得する必要があります。 このような呼び出しは、"*サービス間*" 呼び出しと呼ばれることがあります。 他の Web API を呼び出す Web API では、カスタム キャッシュのシリアル化を提供する必要があります。

[Image: 別の Web API を呼び出す Web API]

詳細については、[Web API を呼び出す Web API](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-api-call-api-app-configuration) に関するページを参照してください。

#### デーモンの名前で Web API を呼び出すデーモン アプリ

長時間実行されるプロセスを含んだアプリや、ユーザーの介入なしで動作するアプリも、セキュリティで保護された Web API になんらかの形でアクセスする必要があります。 そのようなアプリでは、認証やトークンの取得にアプリの ID を使用します。 アプリの ID 証明には、クライアント シークレットまたは証明書が使用されます。

呼び出し元のアプリに代わってトークンを取得するデーモン アプリは、MSAL の[クライアント資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-daemon-acquire-token#acquiretokenforclient-api)取得メソッドを使用して作成できます。 これらのメソッドには、Microsoft Entra ID にアプリの登録を追加するクライアント シークレットが必要です。 そのうえで、そのアプリと呼び出されたデーモンとの間でシークレットが共有されます。 シークレットには、アプリケーションのパスワード、証明書アサーション、クライアント アサーションなどがあります。

[Image: 他のアプリと API によって呼び出されるデーモン アプリ]

詳細については、[Web API を呼び出すデーモン アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-daemon-app-configuration)に関するページを参照してください。

### シナリオとサポートされている認証フロー

トークンを要求するアプリケーションのシナリオを実装するには、認証フローを使用します。 アプリケーション シナリオと認証フローの間に 1 対 1 の対応関係はありません。

トークンの取得が必要なシナリオは、OAuth 2.0 認証フローにも対応します。 詳細については、「[Microsoft ID プラットフォームにおける OAuth 2.0 プロトコルと OpenID Connect プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols)」を参照してください。

| シナリオ | 詳細なシナリオのウォークスルー | OAuth 2.0 のフローと許可 | 対象ユーザー |
| --- | --- | --- | --- |
| [Image: シングル ページ アプリ (認証コード)] | [シングルページ アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-app-configuration) | PKCE を使用した[認可コード](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) | 職場または学校アカウント、個人用アカウント、Azure Active Directory B2C (Azure AD B2C) |
| [Image: シングルページアプリ（インプリシット）] | [シングルページ アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-app-configuration) | [暗黙的](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow) | 職場または学校アカウント、個人用アカウント、Azure Active Directory B2C (Azure AD B2C) |
| [Image: ユーザーをサインインさせる Web アプリ] | [ユーザーをサインインさせる Web アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in) | [承認コード](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) | 職場または学校アカウント、個人用アカウント、Azure AD B2C |
| [Image: Web API を呼び出す Web アプリ] | [Web API を呼び出す Web アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-app-configuration) | [承認コード](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) | 職場または学校アカウント、個人用アカウント、Azure AD B2C |
| [Image: Web API を呼び出すデスクトップ アプリ] | [Web API を呼び出すデスクトップ アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-app-configuration) | 対話型 (PKCE を使用した[認可コード](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)を利用) | 職場または学校アカウント、個人用アカウント、Azure AD B2C |
|  |  | 統合 Windows 認証 | 職場または学校アカウント |
|  |  | [リソース所有者のパスワード](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc) | 職場または学校アカウントと Azure AD B2C |
| [Image: ブラウザーレス アプリケーション] | [ブラウザーレス アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-device-code-flow) | [デバイス コード](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code) | Azure AD B2C でない職場または学校アカウント、個人用アカウント |
| [Image: Web API を呼び出すモバイル アプリ] | [Web API を呼び出すモバイル アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-mobile-app-configuration) | 対話型 (PKCE を使用した[認可コード](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)を利用) | 職場または学校アカウント、個人用アカウント、Azure AD B2C |
|  |  | [リソース所有者のパスワード](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc) | 職場または学校アカウントと Azure AD B2C |
| [Image: Web API を呼び出すデーモン アプリ] | [Web API を呼び出すデーモン アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-daemon-app-configuration) | [クライアントの資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow) | ユーザーが介在せず、Microsoft Entra 組織でのみ使用されるアプリ専用アクセス許可 |
| [Image: Web API を呼び出す Web API] | [Web API を呼び出す Web API](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-api-call-api-app-configuration) | [On-Behalf-Of](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow) | 職場または学校アカウントと個人用アカウント |

### シナリオとサポートされているプラットフォームと言語

Microsoft 認証ライブラリでは、さまざまなプラットフォームをサポートしています。

- .NET
- .NET Framework
- Java
- JavaScript
- macOS
- ネイティブ Android
- ネイティブ iOS
- Node.js
- Python

また、さまざまな言語を使用してアプリケーションをビルドすることもできます。

次の表の Windows の列で .NET と書いてある場合には、.NET Framework でも対応可能です。 後者は表を煩雑にしないため省略しています。

| シナリオ | ウィンドウズ | Linux | マック | iOS | Android |
| --- | --- | --- | --- | --- | --- |
| [シングルページ アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-app-configuration)[Image: シングル ページ アプリ認証] | [Image: MSAL.js]MSAL.js | [Image: MSAL.js]MSAL.js | [Image: MSAL.js]MSAL.js | [Image: MSAL.js] MSAL.js | [Image: MSAL.js]MSAL.js |
| [シングルページ アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-app-configuration)[Image: シングルページ アプリ暗黙] | [Image: MSAL.js]MSAL.js | [Image: MSAL.js]MSAL.js | [Image: MSAL.js]MSAL.js | [Image: MSAL.js] MSAL.js | [Image: MSAL.js]MSAL.js |
| [ユーザーをサインインさせる Web アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in)[Image: ユーザーをサインインさせる Web アプリ] | [Image: ASP.NET Core]ASP.NET Core [Image: MSAL ノード]MSAL ノード | [Image: ASP.NET Core]ASP.NET Core [Image: MSAL ノード]MSAL ノード | [Image: ASP.NET Core]ASP.NET Core [Image: MSAL ノード]MSAL ノード |  |  |
| [Web API を呼び出す Web アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-api-call-api-app-configuration)[Image: Web API を呼び出す Web アプリ] | [Image: ASP.NET Core]ASP.NET Core + [Image: MSAL.NET MSAL Java]MSAL Java[Image: MSAL Python]Flask + MSAL Python [Image: MSAL ノード]MSAL ノード | [Image: ASP.NET Core]ASP.NET Core + [Image: MSAL.NET MSAL Java]MSAL Java[Image: MSAL Python]Flask + MSAL Python [Image: MSAL ノード]MSAL ノード | [Image: ASP.NET Core]ASP.NET Core + [Image: MSAL.NET MSAL Java]MSAL Java[Image: MSAL Python]Flask + MSAL Python [Image: MSAL ノード]MSAL ノード |  |  |
| [Web API を呼び出すデスクトップ アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-app-configuration)[Image: Web API を呼び出すデスクトップ アプリ][Image: デバイス コード フロー] | [Image: .NET] MSAL.NET [Image: MSAL Java]MSAL Java[Image: MSAL Python]MSAL Python [Image: MSAL ノード]MSAL ノード | [Image: .NET] MSAL.NET [Image: MSAL Java]MSAL Java[Image: MSAL Python]MSAL Python [Image: MSAL ノード]MSAL ノード | [Image: .NET] MSAL.NET [Image: MSAL Java]MSAL Java[Image: MSAL Python]MSAL Python[Image: MSAL ノード]MSAL ノード[Image: iOS/Objective C または swift] MSAL.objc |  |  |
| [Web API を呼び出すモバイル アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-mobile-app-configuration)[Image: Web API を呼び出すモバイル アプリ] | [Image: UWP] MSAL.NET |  |  | [Image: iOS/Objective C または swift] MSAL.objc | [Image: アンドロイド] MSAL。アンドロイド |
| [デーモン アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-daemon-app-configuration)[Image: デーモン アプリ] | [Image: .NET] MSAL.NET [Image: MSAL Java]MSAL Java[Image: MSAL Python]MSAL Python [Image: MSAL ノード]MSAL ノード | [Image: .NET] MSAL.NET [Image: MSAL Java]MSAL Java[Image: MSAL Python]MSAL Python [Image: MSAL ノード]MSAL ノード | [Image: .NET] MSAL.NET [Image: MSAL Java]MSAL Java[Image: MSAL Python]MSAL Python [Image: MSAL ノード]MSAL ノード |  |  |
| [Web API を呼び出す Web API](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-api-call-api-app-configuration)[Image: Web API を呼び出す Web API] | [Image: ASP.NET Core]ASP.NET Core + [Image: MSAL.NET MSAL Java]MSAL Java[Image: MSAL Python]MSAL Python [Image: MSAL ノード]MSAL ノード | [Image: .NET]ASP.NET Core + [Image: MSAL.NET MSAL Java]MSAL Java[Image: MSAL Python]MSAL Python [Image: MSAL ノード]MSAL ノード | [Image: .NET]ASP.NET Core + [Image: MSAL.NET MSAL Java]MSAL Java[Image: MSAL Python]MSAL Python [Image: MSAL ノード]MSAL ノード |  |  |

詳細については、「[Microsoft ID プラットフォームの認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/authentication-national-cloud"} -->
## Microsoft Entra の認証と各国のクラウド - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud
- Service: identity-platform
- Article date: 2025-01-15
- Summary: 各国のクラウドのアプリ登録と認証エンドポイントについて説明します。

各国のクラウドは、物理的に分離された Azure のインスタンスです。 Azure のこれらのリージョンは、データ所在地、主権、およびコンプライアンス要件が地理的境界内で確実に守られるように設計されています。

グローバル Azure クラウドを含め、Microsoft Entra ID は次の各国のクラウドにデプロイされています。

- Azure Government（アジュール・ガバメント）
- 21Vianet が運用する Microsoft Azure
- Azure Germany ([2021 年 10 月 29 日は閉鎖](https://www.microsoft.com/cloud-platform/germany-cloud-regions))。 Azure Germany の移行について詳しくは、こちらをご覧ください。

個々の国内クラウドとグローバル Azure クラウドはクラウド *インスタンスです*。 各クラウド インスタンスは他のクラウド インスタンスとは異なり、独自の環境と *エンドポイントを持ちます*。 クラウド固有のエンドポイントには、OAuth 2.0 アクセス トークンと OpenID Connect ID トークン要求エンドポイント、アプリの管理とデプロイのための URL が含まれています。

アプリを開発する場合は、アプリケーションをデプロイするクラウド インスタンスのエンドポイントを使用します。

### アプリ登録エンドポイント

各国のクラウドには、それぞれ別個の Azure portal があります。 各国のクラウドでアプリケーションを Microsoft ID プラットフォームに統合するには、その環境に固有の各 Azure Portal で個別にアプリケーションを登録する必要があります。

注

Microsoft Entra ゲスト アカウントを持つ別のナショナル クラウドのユーザーは、コスト管理と請求機能にアクセスして EA 登録を管理することはできません。 次の表は、各国のクラウドごとにアプリケーションを登録するために使用される Microsoft Entra エンドポイントのベース URL の一覧を示しています。

| 各国のクラウド | Azure portal エンドポイント |
| --- | --- |
| 米国政府の Azure portal | `https://portal.azure.us` |
| 21Vianet が運営する中国の Azure portal | `https://portal.azure.cn` |
| Azure portal (グローバル サービス) | `https://portal.azure.com` |

### アプリケーション エンドポイント

アプリケーションの認証エンドポイントを見つけることができます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**App 登録**を参照してください。
3. 上部のメニューで [ **エンドポイント** ] を選択します。

    **[エンドポイント]** ページが表示され、アプリケーションの認証エンドポイントが表示されます。

    アプリケーション **(クライアント) ID** と組み合わせて使用している認証プロトコルと一致するエンドポイントを使用して、アプリケーションに固有の認証要求を作成します。

### Microsoft Entra の認証エンドポイント

各国のクラウドはすべて、各環境で別個にユーザーを認証し、別個の認証エンドポイントを備えています。

次の表は、各国のクラウドごとにトークンを取得するために使用される Microsoft Entra エンドポイントのベース URL の一覧を示しています。

| 各国のクラウド | Microsoft Entra の認証エンドポイント |
| --- | --- |
| 米国政府のMicrosoft Entra ID | `https://login.microsoftonline.us` |
| 21Vianet が運営する中国の Microsoft Entra | `https://login.partner.microsoftonline.cn` |
| Microsoft Entra ID (グローバル サービス) | `https://login.microsoftonline.com` |

適切なリージョン固有のベース URL を使用して、Microsoft Entra 承認またはトークン エンドポイントへの要求を構成できます。 たとえば、グローバル Azure の場合:

- 承認共通エンドポイントは `https://login.microsoftonline.com/common/oauth2/v2.0/authorize` です。
- トークン共通エンドポイントは `https://login.microsoftonline.com/common/oauth2/v2.0/token` です。

シングルテナント アプリケーションの場合は、前の URL にある "common" をテナント ID またはテナント名に置き換えます。 たとえば `https://login.microsoftonline.com/contoso.com` です。

### Azure Germany (Microsoft Cloud Deutschland)

Azure Germany からアプリケーションを移行していない場合は、 [Microsoft Entra の情報に従って Azure Germany からの移行](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/ms-cloud-germany-transition-azure-ad) を開始します。

### Microsoft Graph API

国内クラウド環境で Microsoft Graph API を呼び出す方法については、 [国内クラウドデプロイの Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/deployments) にアクセスしてください。

グローバル Azure クラウドのサービスや機能の中には、各国のクラウドのような他のクラウド インスタンスでは利用できないものがあります。

特定のクラウド インスタンスで使用できるサービスと機能を確認するには、「 [リージョン別に利用可能な製品](https://azure.microsoft.com/global-infrastructure/services/?products=all&amp;regions=usgov-non-regional,us-dod-central,us-dod-east,usgov-arizona,usgov-iowa,usgov-texas,usgov-virginia,china-non-regional,china-east,china-east-2,china-north,china-north-2,germany-non-regional,germany-central,germany-northeast)」を参照してください。

Microsoft ID プラットフォームを使用してアプリケーションを構築する方法については、 [認証コード フローを使用したシングルページ アプリケーション (SPA) のチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-angular-auth-code)に従ってください。 具体的には、このアプリはユーザーをサインインさせ、Microsoft Graph API を呼び出すためのアクセス トークンを取得します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/authentication-vs-authorization"} -->
## 認証と承認 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-vs-authorization
- Service: identity-platform
- Article date: 2025-03-21
- Summary: 認証、承認、および Microsoft ID プラットフォームが開発者向けにこれらのプロセスを簡素化する方法の基礎について説明します。

この記事では、認証と承認を定義します。 また、多要素認証と、Microsoft ID プラットフォームを使用して Web アプリ、Web API、または保護された Web API を呼び出すアプリのユーザーを認証および認可する方法についても簡単に説明します。 よく知らない用語がある場合は、[用語集](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-glossary)または基本的な概念を説明する [Microsoft ID プラットフォームの動画](https://learn.microsoft.com/ja-jp/entra/identity-platform/identity-videos)をご覧ください。

### 認証

"*認証*" は、ユーザーが身元を証明するプロセスです。 これは、ユーザーまたはデバイスの ID の検証によって実現されます。 *AuthN* と短縮される場合があります。 Microsoft ID プラットフォームでは、認証の処理に [OpenID Connect](https://openid.net/connect/) プロトコルが使用されます。

### 認可

*承認*は、認証された利用者に対し、何かを実行する権限を付与する行為です。 アクセスが許可されるデータと、そのデータで実行できる操作を指定します。 承認は *AuthZ* と短縮される場合があります。 Microsoft ID プラットフォームは、リソース所有者に承認を処理するために [OAuth 2.0](https://oauth.net/2/) プロトコルを使用する機能を提供しますが、Microsoft クラウドには、[Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)、[Azure RBAC](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/overview)、Exchange RBAC などの他の承認システムもあります。

### 多要素認証

"*多要素認証*" とは、アカウントに別の認証要素を追加する操作です。 これは、ブルート フォース攻撃から保護するためによく使用されます。 *MFA* または *2FA* と短縮される場合があります。 [Microsoft Authenticator](https://support.microsoft.com/account-billing/set-up-the-microsoft-authenticator-app-as-your-verification-method-33452159-6af9-438f-8f82-63ce94cf3d29) は、2 要素認証を処理するためのアプリとして使用できます。 詳細については、「[多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)」を参照してください。

### Microsoft ID プラットフォームを使用した認証と承認

それぞれ独自のユーザー名とパスワードの情報を保持するアプリを作成した場合、複数のアプリにまたがってユーザーを追加または削除するときに管理上の負担が大きくなります。 アプリでは、代わりにその責任を一元化された ID プロバイダーに委任できます。

Microsoft Entra ID は、クラウド内の一元化された ID プロバイダーです。 認証と承認をこれに委任することで、次のようなシナリオが可能になります。

- ユーザーが特定の場所にいることを求める条件付きアクセス ポリシー。
- ユーザーが特定のデバイスを所有していることを要求する多要素認証。
- ユーザーが一度サインインした後、同じ一元化されたディレクトリを共有するすべての Web アプリに自動的にサインインできるようにする。 この機能は、"*シングル サインオン (SSO)* " と呼ばれます。

Microsoft ID プラットフォームでは、ID をサービスとして提供することで、アプリケーション開発者を対象とする承認と認証が簡略化されます。 コーディングを迅速に開始できるように、さまざまなプラットフォーム用の業界標準プロトコルとオープンソース ライブラリがサポートされています。 これにより、開発者は、すべての Microsoft ID にサインインするアプリケーションの作成、[Microsoft Graph](https://developer.microsoft.com/graph/) を呼び出すためのトークンの取得、Microsoft API へのアクセス、または開発者が作成したその他の API へのアクセスを行うことができます。

次の動画では、Microsoft ID プラットフォームと、先進認証の基本について説明します。

Microsoft ID プラットフォームで使用されるプロトコルの比較を次に示します。

- **OAuth と OpenID Connect**:このプラットフォームでは、OAuth が承認に使用され、OpenID Connect (OIDC) が認証に使用されます。 OpenID Connect は OAuth 2.0 に基づいて構築されているため、これら 2 つの間では用語とフローが似ています。 さらに、(OpenID Connect を使用して) ユーザーを認証することと、(OAuth 2.0 を使用して) ユーザーが所有する保護されたリソースにアクセスするための承認を得ることを、1 回の要求で行うこともできます。 詳細については、[OAuth 2.0 と OpenID Connect プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols)および [OpenID Connect プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)に関するページを参照してください。
- **OAuth と SAML**:このプラットフォームでは、OAuth 2.0 が承認に使用され、SAML が認証に使用されます。 これらのプロトコルを一緒に使用することで、ユーザーの認証および保護されたリソースにアクセスするための承認の取得の両方を行う方法の詳細については、「[Microsoft ID プラットフォームと OAuth 2.0 SAML ベアラー アサーション フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-token-exchange-saml-oauth)」を参照してください。
- **OpenID Connect と SAML**:このプラットフォームでは OpenID Connect と SAML の両方が、ユーザーを認証し、シングル サインオンを有効にするために使用されます。 SAML 認証は一般的に、Microsoft Entra ID にフェデレーションされた Active Directory フェデレーション サービス (AD FS) などの ID プロバイダーと一緒に使用されるため、エンタープライズ アプリケーションで多く使用されます。 OpenID Connect は一般的に、モバイル アプリ、Web サイト、Web API など、純粋にクラウド内にあるアプリに対して使用されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/authorization-basics"} -->
## 承認の基本 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/authorization-basics
- Service: identity-platform
- Article date: 2024-08-25
- Summary: Microsoft ID プラットフォームの承認の基本について説明します。

**承認** (*AuthZ* と省略されることもあります) は、リソースまたは機能へのアクセスを評価できるようにするアクセス許可を設定するため使用されます。 これに対し、**認証** (*AuthN* と省略されることもあります) は、ユーザーまたはサービスのようなエンティティが、主張する本人であることを証明することに重点を置いています。

承認には、エンティティがアクセスできる機能、リソース、またはデータの指定を含めることができます。 承認では、データに対して実行できる処理も指定されます。 この承認アクションは、多くの場合 "アクセス制御" と呼ばれます。

認証と承認は、ユーザーのみに限定されない概念です。 サービスまたはデーモン アプリケーションは、多くの場合、特定のユーザーの代わりではなく、それら自身としてリソースに対する要求を行うように構築されます。 この記事では、ユーザーまたはアプリケーションのいずれかを示すために "エンティティ" という用語が使用されます。

### 承認方法

承認を処理するいくつかの一般的な方法があります。 [ロールベースのアクセス制御](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-rbac-for-developers)は、Microsoft ID プラットフォームを使用する、現在最も一般的な方法です。

#### 承認としての認証

おそらく承認の最も単純な形式は、要求を行うエンティティが認証されているかどうかに基づいてアクセスを許可または拒否することです。 要求元は、自分が本人であることを証明できる場合、保護されたリソースまたは機能にアクセスできます。

#### アクセス制御リスト

アクセス制御リスト (ACL) を使用した承認では、リソースまたは機能へのアクセスが許可されている、または許可されていない特定のエンティティの明示的なリストを保持する必要があります。 ACL では、承認としての認証よりも細かい制御が可能になりますが、エンティティの数が増えるにつれて管理が困難になります。

#### ロールベースのアクセス制御

ロールベースのアクセス制御 (RBAC) は、アプリケーションで承認を適用するためのおそらく最も一般的な方法です。 RBAC を使用すると、あるエンティティが実行できるアクティビティの種類を記述するためにロールが定義されます。 アプリケーション開発者は、個々のエンティティではなくロールに対してアクセスを許可します。 その後管理者は、さまざまなエンティティにロールを割り当てて、どのエンティティがどのリソースと機能にアクセスできるかを制御できます。

高度な RBAC 実装では、ロールがアクセス許可のコレクションにマップされることがあり、このときアクセス許可には実行できるアクションやアクティビティが細かく記述されます。 ロールはその後、アクセス許可の組み合わせとして構成されます。 エンティティの全体的なアクセス許可セットを計算するには、エンティティを割り当てる先の各種ロールに付与するアクセス許可を結合します。 このアプローチの好例として、Azure サブスクリプション内のリソースへのアクセスを制御する RBAC 実装が挙げられます。

注

[アプリケーション RBAC](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-rbac-for-developers) は [Azure RBAC](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/overview) および [Microsoft Entra RBAC](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview#understand-azure-ad-role-based-access-control) とは異なります。 Azure カスタム ロールと組み込みロールはどちらも Azure RBAC の一部であり、Azure リソースの管理に役立ちます。 Microsoft Entra RBAC を使用すると、Microsoft Entra リソースを管理できます。

#### 属性ベースのアクセス制御

属性ベースのアクセス制御 (ABAC) は、よりきめ細かいアクセス制御のメカニズムです。 この方法では、エンティティ、アクセス対象のリソース、現在の環境にルールが適用されます。 ルールによって、リソースと機能へのアクセスのレベルが決まります。 たとえば、マネージャーであるユーザーのみが、営業日の午前 9 時から午後 5 時までの時間帯に "営業時間中にマネージャーのみ" というメタデータ タグで識別されたファイルにアクセスできるようにすることができます。 この場合、アクセスは、ユーザーの属性 (マネージャーとしてのステータス)、リソースの属性 (ファイルのメタデータ タグ)、環境属性 (現在の時刻) を調べることによって決定されます。

ABAC の利点の 1 つは、ルールと条件の評価によってよりきめ細かい動的なアクセス制御を実現でき、具体的なロールと RBAC 割り当てを大量に作成せずに済むことです。

Microsoft Entra ID で ABAC を実現する 1 つの方法は、[動的メンバーシップ グループ](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule)を使用することです。 動的グループを使用すると、管理者は必要な値を持つ特定のユーザー属性に基づいて、ユーザーをグループに動的に割り当てることができます。 たとえば、作成者グループを作成して、作成者という役職を持つすべてのユーザーを作成者グループに動的に割り当てることができます。 動的グループを承認のための RBAC と組み合わせて使用して、ロールをグループにマップし、ユーザーをグループに動的に割り当てることができます。

[Azure ABAC](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/conditions-overview) は、現在利用できる ABAC ソリューションの一例です。 Azure ABAC は、Azure RBAC を基盤としたものであり、特定のアクションのコンテキストにおける属性に基づいたロールの割り当て条件を追加することにより構築されます。

### 承認の実装

承認ロジックは多くの場合、アクセス制御が必要なアプリケーションまたはソリューション内に実装されます。 多くの場合、アプリケーション開発プラットフォームには、承認の実装を簡略化するミドルウェアまたはその他の API ソリューションが提供されています。 例としては、ASP.NET の [AuthorizeAttribute](https://learn.microsoft.com/ja-jp/aspnet/core/security/authorization/simple?view=aspnetcore-5.0&preserve-view=true) や Angular の [Route Guards](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-sign-in?tabs=angular2#sign-in-with-a-pop-up-window) の使用などがあります。

認証対象のエンティティに関する情報に依存した承認方法の場合、アプリケーションでは認証時に交換される情報が評価されます。 たとえば、[セキュリティ トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-tokens)内に指定された情報を使用します。 承認のためにトークンからの情報を使用する予定の場合は、[クレームの検証を通じてアプリを適切にセキュリティで保護する方法に関するこちらのガイダンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-validation)に従うことをお勧めします。 セキュリティ トークンに含まれていない情報については、アプリケーションが外部リソースに対して追加の呼び出しを行う場合があります。

開発者がアプリケーション内に承認ロジックを完全に埋め込むことが絶対に必要というわけではありません。 代わりに、専用の承認サービスを使用して、承認の実装と管理を一元化することができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/azure-active-directory-graph-app-manifest-deprecation"} -->
## アプリ マニフェスト (Azure AD Graph 形式)の廃止 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/azure-active-directory-graph-app-manifest-deprecation
- Service: identity-platform
- Article date: 2024-09-18
- Summary: アプリ マニフェスト (Azure AD Graph 形式) の廃止と新しい形式での属性の違いについて説明します。

Azure AD Graph の廃止に伴い、アプリケーション マニフェストの Azure AD Graph 形式は廃止され、Microsoft Entra 管理センターではアプリ マニフェストが Microsoft Graph 形式で表示されます。 アプリ マニフェストの移行がユーザー エクスペリエンスにどのような影響を与えるかについて詳しくは、この記事をお読みください。

重要

個人用の Microsoft アカウント (MSA アカウント) に登録されたアプリは、この廃止の対象外です。 個人の MSA アカウントに登録されたアプリは、追って通知があるまで、Microsoft Entra 管理センターで Azure AD Graph 形式のアプリ マニフェストを引き続き管理します。

### 移行日

2024 年 6 月 13 日から 9 月 16 日まで、Microsoft Entra 管理センターの **アプリ登録** マニフェスト ページでは、Azure AD Graph 形式と Microsoft Graph 形式の両方でアプリ マニフェストを表示、編集、アップロード、ダウンロードできる新しいタブ付きエクスペリエンスが開始されました。

重要

新しいタブ付きエクスペリエンスは、エクスペリエンスの品質を確保するために、Microsoft Entra ユーザーにバッチでロールアウトされます。 新しいエクスペリエンスはすぐには表示されない場合があります。

2025 年 1 月 7 日以降、Microsoft Entra 管理センターの [アプリ **登録** マニフェスト] ページで Azure AD Graph アプリ マニフェストを表示、保存、アップロード、またはダウンロードすることはできません。

### アプリ マニフェストの移行はユーザー エクスペリエンスにどのような影響を与えますか?

アプリマニフェストを表示、編集、保存しない場合は、この移行はワークフローに影響しません。

アプリ マニフェストを表示または編集すると、 Azure AD Graph 形式と Microsoft Graph 形式の属性の違いに気付きます。 [Microsoft Graph 形式のリファレンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-microsoft-graph-app-manifest)に従って、アプリ マニフェストの表示と編集を開始することをお勧めします。

ワークフローで後で使用するためにソース リポジトリにマニフェストを保存する必要がある場合は、 Azure AD Graph 形式のアプリ マニフェストを Microsoft Graph 形式に変換する必要があります。

### Azure AD Graph と Microsoft Graph 形式の属性の違い

Azure AD Graph アプリのマニフェスト属性のほとんどは同じままです。 ただし、次の Azure AD Graph アプリ マニフェスト属性は、Microsoft Graph マニフェストで非推奨、名前変更、または再配置されています。

| Azure AD マニフェスト属性 | Microsoft Graph マニフェスト |
| --- | --- |
| `acceptMappedClaims` | `acceptMappedClaims` 属性の `api` プロパティとして再配置された |
| `accessTokenAcceptedVersion` | `requestedAccessTokenVersion` 属性の `api` プロパティとして再配置され、名前が変更された |
| `allowPublicClient` | `isFallbackPublicClient` という名前に変更されました |
| `errorUrl` | 非推奨/サポート終了 |
| `informationalUrls` | `info` という名前に変更されました |
| `knownClientApplications` | `api` 属性のプロパティとして再配置された |
| `logoUrl` | `info` 属性のプロパティとして再配置された |
| `logoutUrl` | `logoutUrl` 属性のプロパティ `web` として再配置された |
| `name` | `displayName` |
| `oauth2AllowIdTokenImplicitFlow` | `web` 属性内の `implicitGrantSettings` プロパティの `enableIdTokenIssuance` プロパティに移動し、名前が変更されました |
| `oauth2AllowImplicitFlow` | `web` 属性内で、プロパティ `implicitGrantSettings` のプロパティ `enableAccessTokenIssuance` に移動され、名前が変更されました |
| `oauth2Permissions` | `oauth2PermissionScopes` 属性の `api` プロパティとして再配置され、名前が変更された |
| `preAuthorizedApplications` | `preAuthorizedApplications` 属性の `api` プロパティとして再配置された |
| `replyUrlsWithType` | 複数の属性でプロパティ `redirectUris` として名前が変更された: `web` 属性、`spa` 属性、`publicClient` 属性 |
| `signInUrl` | `homePageUrl` 属性のプロパティ `web` として再配置され、名前変更された |
| `trustedCertificateSubjects` | これは Microsoft の内部プロパティです。 ポータルには MS Graph アプリ マニフェストの v1.0 バージョンが表示されますが、このプロパティは MS Graph アプリ マニフェストのベータ 版にのみ存在します。 Microsoft Entra 管理センターで Azure AD Graph アプリ マニフェストを使用して、このプロパティの編集を続けてください。 Azure AD Graph アプリ マニフェストを廃止する前に、Microsoft Entra 管理センターで MS Graph アプリ マニフェストのベータ版を公開します。 |

### アプリ マニフェストの形式を確認するにはどうすればよいですか?

アプリ マニフェストが Azure AD Graph 形式か Microsoft Graph 形式かを、それに含まれる属性で確認できます。 たとえば、

- アプリ マニフェストに属性 `replyUrlsWithType` がある場合、そのアプリは Azure AD Graph 形式です。
- アプリ マニフェストに属性 `implicitGrantSettings` がある場合、そのアプリは Microsoft Graph 形式です。

### Azure AD Graph 形式のアプリ マニフェストを Microsoft Graph 形式に変換する

アプリ マニフェストを Azure AD Graph 形式で保存していて、それを Microsoft Graph 形式に変換する場合は、次の手順を実行します。

2024 年 6 月 13 日から 2025 年 1 月 7 日までの間に、以下の手順に従い、ポータルを使用して Azure AD Graph 形式のアプリ マニフェストを Microsoft 形式に変換できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/)に、少なくとも[アプリケーション開発者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)としてサインインします。
2. **Entra ID**&gt;**アプリ登録**に移動します。
3. [ **新しい登録** ] を選択し、新しいアプリの登録を作成します。
4. そのアプリケーションの **[マニフェスト** ] ページで、[ **Azure AD Graph アプリ マニフェスト** ] タブを選択します。
5. 使用している Azure AD Graph 形式でアプリ マニフェストをアップロードします。
6. **Microsoft Graph アプリ マニフェスト** タブを選択します。
7. [ **ダウンロード**] を選択します。

2025 年 1 月 7 日以降、Microsoft Entra 管理センターでは、Azure AD Graph 形式のアプリ マニフェストはサポートされなくなります。 ただし、手動で変換を実行することもできます。

1. **Entra ID**&gt;**アプリ登録**に移動します。
2. [ **新しい登録** ] を選択し、新しいアプリの登録を作成します。
3. アプリ マニフェストは、既定値を使用して新しい形式で作成されます。
4. Azure AD Graph 形式のアプリ マニフェストの各属性を 1 つずつ確認し、Microsoft Graph 形式のアプリ マニフェスト内の対応する属性をその値と一致するように編集します。

    1. 属性が Azure AD Graph マニフェストと Microsoft Graph マニフェストの属性の違いに記載されている場合は、Microsoft Graph アプリ マニフェストで新しい属性の値を正常に編集できるように、古い属性と新しい属性の構文とセマンティクスを理解する必要があります。
    2. 属性が Azure AD Graph マニフェストと Microsoft Graph マニフェストの属性の違いに 記載されていないが、Microsoft Graph アプリ マニフェストで対応する属性が見つからない場合、この属性は非推奨になっている可能性が高く、この属性を破棄できます。
    3. その他のすべての属性については、Azure AD Graph アプリ マニフェストの属性の値をコピーし、Microsoft Graph アプリ マニフェストの属性の値に貼り付けることができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/certificate-credentials"} -->
## Microsoft ID プラットフォームの証明書資格情報 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials
- Service: identity-platform
- Article date: 2025-01-04
- Summary: この記事では、アプリケーションを認証するための証明書資格情報の登録と使用について説明します。

Microsoft ID プラットフォームでは、OAuth 2.0 [クライアント資格情報付与](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)フローや [On-Behalf-Of](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow) (OBO) フローなど、クライアント シークレットを使用できるあらゆる場所で、認証用の独自の資格情報をアプリケーションで使用することが許可されています。

アプリケーションが認証を行うために使用できる資格情報の 1 つの形式は、アプリケーションが所有する証明書を使用して署名された [JSON Web トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-tokens#json-web-tokens-and-claims) (JWT) アサーションです。 これについては、クライアント認証オプションの`private_key_jwt`仕様」を参照してください。

アプリケーションの資格情報として別の id プロバイダーによって発行された JWT の使用に関心がある場合は、[ワークロード IDフェデレーション](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation)でフェデレーションポリシーを設定する方法を参照してください。

### アサーションの形式

アサーションを計算するには、自分が選択した言語の多数ある JWT ライブラリの中からいずれかを使用できます。[MSAL は、`.WithCertificate()` のこのような使用をサポートしています](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/msal-net-client-assertions)。 この情報は、トークンによって**ヘッダー**、**要求**、および**署名**で伝達されます。

#### ヘッダー

| パラメーター | 注記 |
| --- | --- |
| `alg` | **PS256** である必要があります |
| `typ` | **JWT** です |
| `x5t#S256` | X.509 証明書の DER エンコーディングの Base64url でエンコードした SHA-256 サムプリント。 |

#### 要求 (ペイロード)

| 要求の種類 | 値 | 説明 |
| --- | --- | --- |
| `aud` | `https://login.microsoftonline.com/{tenantId}/oauth2/v2.0/token` | "aud" (対象ユーザー) 要求では、JWT が意図する受信者 (ここでは Microsoft Entra ID) を指定します。[\[RFC 7519、セクション 4.1.3\]](https://tools.ietf.org/html/rfc7519#section-4.1.3) を参照してください。 この場合、その受信者はログイン サーバー (`login.microsoftonline.com`) です。 |
| `exp` | 1601519414 | "exp" (有効期限) 要求は、それ以降 JWT の処理を受け入れることが**できなくなる**時刻を指定します。 [\[RFC 7519、セクション 4.1.4\]](https://tools.ietf.org/html/rfc7519#section-4.1.4) を参照してください。 これにより、そのときまでアサーションを使用できるようになるため、間隔を短く (最大でも `nbf` の 5 ～ 10 分後に) してください。 Microsoft Entra ID では、現在 `exp` の時刻に対して制限は設定されていません。 |
| `iss` | {クライアントID} | "iss" (発行者) 要求では、JWT を発行したプリンシパル (この場合はクライアント アプリケーション) を指定します。 GUID (アプリケーション ID) を使用します。 |
| `jti` | (Guid) | "jti" (JWT ID) 要求は、JWT の一意の識別子を提供します。 識別子の値は、同じ値が誤って異なるデータ オブジェクトに割り当てられる可能性が無視できるほど低くなる方法で割り当てる**必要があります**。複数の発行者を使用するアプリケーションの場合は、異なる発行者が生成した値が競合しないように防ぐ必要もあります。 "jti" 値は大文字と小文字が区別される文字列です。 [\[RFC 7519、セクション 4.1.7\]](https://tools.ietf.org/html/rfc7519#section-4.1.7) |
| `nbf` | 1601519114 | "nbf" (指定時刻よりも後) 要求では、指定した時刻よりも後に JWT の処理を受け入れることができるようになります。 [\[RFC 7519、セクション 4.1.5\]](https://tools.ietf.org/html/rfc7519#section-4.1.5) 現在の時刻を使用するのが適切です。 |
| `sub` | {クライアントID} | "sub" (主題) 要求では、JWT の件名 (この場合はお使いのアプリケーション) を指定します。 `iss` と同じ値を使用します。 |
| `iat` | 1601519114 | "iat" (発行時刻) 要求では、JWT が発行された時刻を指定します。 この要求を使用して、JWT の経過時間を特定できます。 [\[RFC 7519、セクション 4.1.5\]](https://tools.ietf.org/html/rfc7519#section-4.1.5) |

#### 署名

署名は、[JSON Web トークン RFC7519 仕様](https://tools.ietf.org/html/rfc7519)の説明に従って証明書を適用することによって計算されます。 PSS パディングを使用します。

### デコードされた JWT アサーションの例

```JSON
{
  "alg": "PS256",
  "typ": "JWT",
  "x5t#S256": "A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u"
}
.
{
  "aud": "https: //login.microsoftonline.com/contoso.onmicrosoft.com/oauth2/v2.0/token",
  "exp": 1484593341,
  "iss": "aaaabbbb-0000-cccc-1111-dddd2222eeee",
  "jti": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
  "nbf": 1484592741,
  "sub": "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb"
}
.
"A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u..."
```

### エンコードされた JWT アサーションの例

次の文字列は、エンコードされたアサーションの例です。 よく見ると、ドット (`.`) で区切られた 3 つのセクションがあることがわかります。

- 最初のセクションは、"*ヘッダー*" をエンコードしています。
- 2 番目のセクションは、"*要求*" (ペイロード) をエンコードしています。
- 最後のセクションは、最初の 2 つのセクションのコンテンツからの証明書を使用して計算された "*署名*" です。

```
"eyJhbGciOiJQUzI1NiIsIng1dCNTMjU2IjoiZ3g4dEd5c3lqY1JxS2pGUG5kN1JGd3Z3WkkwIn0.eyJhdWQiOiJodHRwczpcL1wvbG9naW4ubWljcm9zb2Z0b25saW5lLmNvbVwvam1wcmlldXJob3RtYWlsLm9ubWljcm9zb2Z0LmNvbVwvb2F1dGgyXC90b2tlbiIsImV4cCI6MTQ4NDU5MzM0MSwiaXNzIjoiOTdlMGE1YjctZDc0NS00MGI2LTk0ZmUtNWY3N2QzNWM2ZTA1IiwianRpIjoiMjJiM2JiMjYtZTA0Ni00MmRmLTljOTYtNjVkYmQ3MmMxYzgxIiwibmJmIjoxNDg0NTkyNzQxLCJzdWIiOiI5N2UwYTViNy1kNzQ1LTQwYjYtOTRmZS01Zjc3ZDM1YzZlMDUifQ.
Gh95kHCOEGq5E_ArMBbDXhwKR577scxYaoJ1P{a lot of characters here}KKJDEg"
```

### Microsoft ID プラットフォームに証明書を登録する

次のいずれかの方法を使用して、Microsoft Entra 管理センター経由で Microsoft ID プラットフォームのクライアント アプリケーションに証明書資格情報を関連付けることができます。

#### 証明書ファイルのアップロード

クライアント アプリケーションの **[アプリの登録]** タブで、次を実行します。

1. **[証明書とシークレット]**、&gt; の順に選択します。
2. **[証明書のアップロード]** を選択し、アップロードする証明書ファイルを選択します。
3. **[追加]** を選択します。 証明書がアップロードされると、サムプリント、開始日、有効期限の値が表示されます。

#### アプリケーション マニフェストの更新

証明書を取得した後、これらの値を計算します。

- `$base64Thumbprint` - Base64 でエンコードされた証明書ハッシュの値
- `$base64Value` - Base64 でエンコードされた証明書生データの値

GUID を指定して、アプリケーション マニフェストでキーを特定します (`$keyId`)。

クライアント アプリケーションの Azure アプリ登録で、以下を実行します。

1. **[マニフェスト]** を選択して、アプリケーション マニフェストを開きます。
2. Microsoft Graph アプリ マニフェストを選択します。
3. 次のスキーマを使用して、*keyCredentials* プロパティを新しい証明書情報に置き換えます。

    ```JSON
    "keyCredentials": [
        {
            "customKeyIdentifier": "$base64Thumbprint",
            "keyId": "$keyid",
            "type": "AsymmetricX509Cert",
            "usage": "Verify",
            "key":  "$base64Value"
        }
    ]
    ```
4. 編集内容をアプリケーション マニフェストに保存した後、そのマニフェストを Microsoft ID プラットフォームにアップロードします。

    `keyCredentials` プロパティは複数値であるため、複数の証明書をアップロードして、高度なキー管理を行うこともできます。

### クライアント アサーションの使用

クライアント アサーションは、クライアント シークレットが使用されるあらゆる場所で使用できます。 たとえば、[認証コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)では、`client_secret` を渡して、要求がアプリから送信されていることを証明することができます。 これを `client_assertion` パラメーターと `client_assertion_type` パラメーターに置き換えることができます。

| パラメーター | 値 | 説明 |
| --- | --- | --- |
| `client_assertion_type` | `urn:ietf:params:oauth:client-assertion-type:jwt-bearer` | これは固定値であり、証明書の資格情報を使用していることを示します。 |
| `client_assertion` | `JWT` | これは、上記で作成した JWT です。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/claims-challenge"} -->
## 要求のチャレンジ、クレーム要求、およびクライアントの能力 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-challenge
- Service: identity-platform
- Article date: 2024-04-10
- Summary: Microsoft ID プラットフォームのクレーム チャレンジ、クレーム要求、およびクライアントの機能についての説明。

*クレーム チャレンジ*とは、API から送信される応答であり、クライアント アプリケーションによって送信されたアクセス トークンのクレームが不十分であることを示します。 これは、トークンがその API に設定された条件付きアクセス ポリシーを満たしていないか、アクセス トークンが取り消されていることが原因である可能性があります。

"クレーム要求" はクライアント アプリケーションによって行われ、ユーザーを ID プロバイダーにリダイレクトして、満たされていないその他の要件を満たすクレームを含む新しいトークンを取得します。

[継続的アクセス評価 (CAE)](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation) や[条件付きアクセス認証コンテキスト](https://techcommunity.microsoft.com/blog/identity/granular-conditional-access-for-sensitive-data-and-actions/1751775)などの強化されたセキュリティ機能を使用するアプリケーションは、クレーム チャレンジを処理できるように準備する必要があります。

アプリケーションは、サービスの呼び出しでクライアントの機能を宣言する場合にのみ、[Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/overview) などの一般的なサービスからのクレーム チャレンジを受け取ります。

### クレーム チャレンジ ヘッダーの書式

クレーム チャレンジは、提示された[アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)が承認されない場合に API によって返される `www-authenticate` ヘッダーとしてのディレクティブであり、代わりに適切な機能がある新しいアクセス トークンが必要です。 クレーム チャレンジは複数の部分 (応答の HTTP 状態コードと `www-authenticate` ヘッダー) で構成されています。このヘッダー自体にも複数の部分があり、クレーム ディレクティブが含まれている必要があります。

次に例を示します。

```https
HTTP 401; Unauthorized

www-authenticate =Bearer realm="", authorization_uri="https://login.microsoftonline.com/common/oauth2/authorize", error="insufficient_claims", claims="eyJhY2Nlc3NfdG9rZW4iOnsiYWNycyI6eyJlc3NlbnRpYWwiOnRydWUsInZhbHVlIjoiY3AxIn19fQ=="
```

**HTTP 状態コード**: **401 Unauthorized** である必要があります。

次のものを含む **www-authenticate 応答ヘッダー**。

| パラメーター | 必須/省略可能 | 説明 |
| --- | --- | --- |
| 認証の種類 | 必須 | **ベアラー**である必要があります。 |
| Realm | 省略可能 | アクセスされるテナント ID またはテナント ドメイン名 (たとえば、microsoft.com)。 認証が[共通エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant#update-your-code-to-send-requests-to-common)を通過する場合は、空の文字列である必要があります。 |
| `authorization_uri` | 必須 | 必要に応じて対話型認証を実行できる `authorize` エンドポイントの URI。 領域で指定する場合、テナント情報を authorization\_uri に含める必要があります。 領域が空の文字列である場合、authorization\_uri は[共通エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant#update-your-code-to-send-requests-to-common)に対して存在する必要があります。 |
| `error` | 必須 | クレーム チャレンジを生成する必要がある場合は、"insufficient\_claims" である必要があります。 |
| `claims` | エラーが "insufficient\_claims" の場合は必須です。 | Base 64 でエンコードされた[クレーム要求](https://openid.net/specs/openid-connect-core-1_0.html#ClaimsParameter)を含む、引用符で囲まれた文字列。 クレーム要求は、JSON オブジェクトのトップ レベルにある"access\_token" のクレームを要求する必要があります。 値 (要求されたクレーム) はコンテキストに依存します。このドキュメントで後ほど指定します。 サイズ上の理由から、証明書利用者アプリケーションは、base64 エンコードの前に JSON のミニファイ処理を行う必要があります。 上記の例の未加工の JSON は `{"access_token":{"acrs":{"essential":true,"value":"cp1"}}}` です。 |

**401** 応答には、複数の `www-authenticate` ヘッダーが含まれる場合があります。 前の表のすべてのフィールドが、同じ `www-authenticate` ヘッダー内に含まれている必要があります。 クレーム チャレンジを含む `www-authenticate` ヘッダーには、他のフィールドを含めることが "*できます*"。 ヘッダー内のフィールドは順序指定されません。 RFC 7235 に従って、各パラメーター名は、認証スキームのチャレンジごとに 1 回だけ出現する必要があります。

### クレーム要求

アプリケーションがクレーム チャレンジを受け取った場合は、以前のアクセス トークンが有効とは見なされなくなったことを示しています。 このシナリオでは、アプリケーションがすべてのローカル キャッシュまたはユーザー セッションでトークンをクリアする必要があります。 その後、サインインしたユーザーを Microsoft Entra IDにリダイレクトして、[OAuth 2.0 認証コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)と *claims* パラメーターを使用して、満たされなかったその他の要件を満たす新しいトークンを取得する必要があります。

次に例を示します。

```https
GET https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/oauth2/v2.0/authorize
?client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&redirect_uri=https%3A%2F%contoso.com%3A44321%2Fsignin-oidc
&response_type=code
&scope=openid%20profile%20offline_access%20user.read%20Sites.Read.All
&response_mode=form_post
&login_hint=kalyan%ccontoso.onmicrosoft.com
&domain_hint=organizations
&claims=%7B%22access_token%22%3A%7B%22acrs%22%3A%7B%22essential%22%3Atrue%2C%22value%22%3A%22c1%22%7D%7D%7D
```

クレーム チャレンジは、トークンが正常に取得されるまで、Microsoft Entra の [/authorize](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#request-an-authorization-code) エンドポイントに対するすべての呼び出しの一部として渡される必要があります。 トークンが取得された後は、不要になります。

クレーム パラメーターを設定するには、開発者は次のことを行う必要があります。

1. 以前に受信した Base64 文字列をデコードします。
2. 文字列を URL エンコードし、claims パラメーターに **もう一度 を追加** します。

このフローが完了すると、アプリケーションは、ユーザーが必要な条件を満たしていることを証明するその他のクレームを含むアクセス トークンを受け取ります。

### クライアントの機能

クライアントの機能は、呼び出し元のクライアント アプリケーションがクレーム チャレンジを認識し、それに応じて応答をカスタマイズできるかどうかを Web API のようなリソース プロバイダーが検出するために役立ちます。 この機能は、すべての API クライアントがクレーム チャレンジを処理できるわけではなく、一部の旧バージョンでは別の応答が期待される場合に便利であることがあります。

[Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/overview) などの一般的なアプリケーションでは、呼び出し元のクライアント アプリが "*クライアントの機能*" を使用してクレーム チャレンジを処理できると宣言している場合にのみ、クレーム チャレンジが送信されます。

余分なトラフィックやユーザー エクスペリエンスへの影響を回避するために、Microsoft Entra ID では、明示的にオプトインしない限り、チャレンジされたクレームをアプリで処理できるとは想定していません。 アプリケーションは、"cp1" 機能を使用して、それらを処理する準備ができていると宣言しない限り、クレーム チャレンジを受け取りません (また、CAE トークンなどの関連機能を使用できません)。

#### クライアント機能を Microsoft Entra ID に伝える方法

次の claims パラメーターの例は、クライアント アプリケーションがその機能を [OAuth 2.0 認証コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)で Microsoft Entra ID に伝える方法を示しています。

```json
Claims: {"access_token":{"xms_cc":{"values":["cp1"]}}}
```

## [.NET](#tab/dotnet)
MSAL ライブラリを使用する場合は、次のコードを使用します。

```c
_clientApp = PublicClientApplicationBuilder.Create(App.ClientId)
 .WithDefaultRedirectUri()
 .WithAuthority(authority)
 .WithClientCapabilities(new [] {"cp1"})
 .Build();
```

Microsoft.Identity.Web を使用する場合は、次のコードを構成ファイルに追加できます。

```c
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "ClientId": 'Enter_the_Application_Id_Here' 
    "ClientCapabilities": [ "cp1" ],
    // remaining settings...
},
```

## [JavaScript](#tab/JavaScript)
MSAL.js または MSAL Node を使用しているユーザーは、構成オブジェクトに `clientCapabilities` プロパティを追加できます。 注: このオプションは、パブリック アプリケーションと機密のクライアント アプリケーションの両方で使用できます。

```javascript
const msalConfig = {
    auth: {
        clientId: 'Enter_the_Application_Id_Here', 
        clientCapabilities: ["CP1"]
        // remaining settings...
    }
}

const msalInstance = new msal.PublicClientApplication(msalConfig);
```

---

Microsoft Entra ID に対するサンプル要求を確認するには、次のスニペットを参照してください。

```https
GET https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/oauth2/v2.0/authorize
?client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&redirect_uri=https%3A%2F%contoso.com%3A44321%2Fsignin-oidc
&response_type=code
&scope=openid%20profile%20offline_access%20user.read%20Sites.Read.All
&response_mode=form_post
&login_hint=kalyan%ccontoso.onmicrosoft.com
&domain_hint=organizations
&claims=%7B%22access_token%22%3A%7B%22xms_cc%22%3A%7B%22values%22%3A%5B%22cp1%22%5D%7D%7D%7D
```

クレーム パラメーターのペイロードが既にある場合は、これを既存のセットに追加します。

たとえば、条件アクセス認証コンテキスト操作から次の応答を既に取得している場合、次のようになります。

```json
{"access_token":{"acrs":{"essential":true,"value":"c25"}}}
```

既存の **claims** ペイロードにクライアント機能を追加します。

```json
{"access_token":{"xms_cc":{"values":["cp1"]},"acrs":{"essential":true,"value":"c25"}}}
```

### アクセス トークンで xms\_cc クレームを受け取る

クライアント アプリケーションがクレーム チャレンジを処理できるかどうかに関する情報を受け取るには、API 実装者は、アプリケーション マニフェストのオプションのクレームとして **xms\_cc** を要求する必要があります。

アクセス トークン内の値が "cp1" の **xms\_cc** クレームは、クライアント アプリケーションがクレーム チャレンジを処理できることを識別するための信頼できる方法です。 **xms\_cc** は、省略可能なクレームであり、クライアントが "xms\_cc" を使用してクレーム要求を送信した場合でも、アクセス トークンで常に発行されるとは限りません。 アクセス トークンに **xms\_cc** クレームを含めるには、リソース アプリケーション (つまり、API 実装者) がアプリケーション マニフェストで[省略可能なクレーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)として xms\_cc を要求する必要があります。 **xms\_cc** は、省略可能なクレームとして要求された場合、クライアント アプリケーションがクレーム要求で **xms\_cc** を送信した場合にのみアクセス トークンに追加されます。 **xms\_cc** クレーム要求の値は、既知の値である場合、アクセス トークンの **xms\_cc** クレームの値として含まれます。 現在の既知の値は、**cp1** のみです。

値は、大文字と小文字が区別されず、順序指定もされません。 **xms\_cc** クレーム要求で複数の値が指定されている場合、それらの値は、 **xms\_cc** クレームの値として複数値のコレクションになります。

例として、次の要求を取り上げます。

```json
{ "access_token": { "xms_cc":{"values":["cp1","foo", "bar"] } }}
```

これにより、**cp1**、**foo**、および **bar** が既知の機能である場合、アクセス トークンに次のスニペットの要求が生成されます。

```json
"xms_cc": ["cp1", "foo", "bar"]
```

[オプションのクレーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)である **xms\_cc** が要求された後、アプリのマニフェストは次のように変化します。

```c
"optionalClaims":
{
    "accessToken": [
    {
        "additionalProperties": [],
        "essential": false,
        "name": "xms_cc",
        "source": null
    }],
    "idToken": [],
    "saml2Token": []
}
```

この後、API では、クライアントがクレーム チャレンジを処理できるかどうかに基づいて、応答をカスタマイズできます。

## [.NET](#tab/dotnet)
```c
Claim ccClaim = context.User.FindAll(clientCapabilitiesClaim).FirstOrDefault(x => x.Type == "xms_cc");
if (ccClaim != null && ccClaim.Value == "cp1")
{
    // Return formatted claims challenge as this client understands this
}
else
{
    // Throw generic exception
    throw new UnauthorizedAccessException("The caller does not meet the authentication bar to carry our this operation. The service cannot allow this operation");
}
```

## [JavaScript](#tab/JavaScript)
次のスニペットは、カスタム Express.js ミドルウェアを示しています。

```javascript
const checkIsClientCapableOfClaimsChallenge = (req, res, next) => {
    // req.authInfo contains the decoded access token payload
    if (req.authInfo['xms_cc'] && req.authInfo['xms_cc'].includes('CP1')) {
          // Return formatted claims challenge as this client understands this
    } else {
          return res.status(403).json({ error: 'Client is not capable' });
    }
}

```

---

### コード サンプル

- [ユーザーをサインインさせ、Microsoft Graph を呼び出すことを Angular シングルページ アプリケーションで可能にする](https://github.com/Azure-Samples/ms-identity-docs-code-javascript/tree/main/angular-spa)
- [ユーザーをサインインさせ、Microsoft Graph を呼び出すことを React シングルページ アプリケーションで可能にする](https://github.com/Azure-Samples/ms-identity-javascript-react-tutorial/tree/main/2-Authorization-I/1-call-graph)
- [ユーザーをサインインさせ、Microsoft Graph を呼び出すことを ASP.NET Core Web アプリで可能にする](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/2-WebApp-graph-user/2-1-Call-MSGraph)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/claims-customization-custom-claims-policy"} -->
## Microsoft Graph カスタム クレーム ポリシーを使用してクレームをカスタマイズする (プレビュー) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-customization-custom-claims-policy
- Service: identity-platform
- Article date: 2024-06-11
- Summary: この記事では、カスタム クレーム ポリシーを使用して Microsoft Entra ID でクレームをカスタマイズする方法について説明します。

要求とは、そのユーザーに発行するトークンの中にあるユーザーに関する ID プロバイダーが提示した情報を指します。 テナント管理者は、自身のテナント内の特定のアプリケーションに対してトークンに含まれるクレームをカスタマイズすることができます。 クレームのカスタマイズでは、SAML、OAuth、OpenID Connect のプロトコルを使用したアプリケーションのクレームの構成がサポートされています。 クレームのカスタマイズを使用すると、次のことを実行できます。

- トークンに含める要求を選択する。
- まだ存在しない種類のクレームを作成します。
- 特定の要求で出力されたデータのソースを選択または変更する。

この攻略ガイドでは、[カスタム クレーム ポリシー](https://learn.microsoft.com/ja-jp/graph/api/resources/customclaimspolicy)の使用方法を理解するのに役立ついくつかの一般的なシナリオを取り上げます。

### 前提条件

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/quickstart-tenant-setup)。
- Microsoft Entra 管理センターで構成された[エンタープライズ アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。
- PowerShell ユーザーの場合は、最新の [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) をダウンロードします。 このステップはオプションです。

### Microsoft Entra ID でのクレームのカスタマイズ

Microsoft Entra ID では、アプリケーションで Microsoft Graph/PowerShell を使用してクレームをカスタマイズするための次の 2 つの方法がサポートされています。

- [カスタム クレーム ポリシー (プレビュー)](https://learn.microsoft.com/ja-jp/graph/api/resources/customclaimspolicy) の使用
- [クレーム マッピング ポリシー](https://learn.microsoft.com/ja-jp/graph/api/resources/claimsmappingpolicy)の使用

次の例では、サービス プリンシパルのポリシーの作成、更新、置き換えを行います。 カスタム クレーム ポリシーは常に、[サービス プリンシパル](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal) オブジェクトにリンクされます。 アプリケーションまたはサービス プリンシパルのカスタム クレーム ポリシーを作成する前に、前提条件の一部としてエンタープライズ アプリケーションが構成されていることを確認してください。

ブラウザーで Microsoft Graph エクスプローラーを開き、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)として Microsoft Graph エクスプローラーにサインインし、次のいずれかのシナリオを選択します。

- トークンから基本要求を除外する
- EmployeeID と TenantCountry を要求としてトークンに含める
- トークンで要求変換を使用する

カスタム クレーム ポリシーを作成した後、カスタマイズされたクレームがトークンに含まれていることを確認するようにアプリケーションを構成する必要があります。 詳細については、「[セキュリティに関する考慮事項](https://learn.microsoft.com/ja-jp/entra/identity-platform/jwt-claims-customization#security-considerations)」を参照してください。

#### トークンから基本要求を除外する

この例では、リンクされたサービス プリンシパルに対して発行されたトークンから[基本クレーム セット](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-claims-customization#claim-sets)を削除するカスタム クレーム ポリシーを作成します。

1. Microsoft Graph エクスプローラーで、[サービス プリンシパル API](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal) を使用するためにカスタム クレーム ポリシーを構成するアプリケーションを識別します。
2. 次の API を実行してカスタム クレーム ポリシーを作成します。 このポリシーは、サービス プリンシパルにリンクされ、トークンから基本クレームを除外します。

    ```http
    PUT https://graph.microsoft.com/beta/servicePrincipals/<servicePrincipal-id>/claimsPolicy
    ```

    リクエスト本文

    ```json
    {
        "includeBasicClaimSet": false
    }
    ```
3. 新しいポリシーを表示するには、次のコマンドを実行します

    ```http
    GET https://graph.microsoft.com/beta/servicePrincipals/<servicePrincipal-id>/claimsPolicy
    ```

    応答:

    ```json
    HTTP/1.1 200 OK
    Content-type: application/json
    
    {
        "@odata.context": "…",
        "id": "aaaaaaaa-bbbb-cccc-1111-222222222222.",
        "includeBasicClaimSet": false,
        "includeApplicationIdInIssuer": false,
        "audienceOverride": null,
        "groupFilter": null,
        "claims": []
    }
    ```

#### トークンに要求として `EmployeeID` と `TenantCountry` を含める

この例では、トークンに `EmployeeID` と `TenantCountry` を追加するクレームのカスタマイズを作成します。 この例では、トークンに基本クレーム セットも含めます。

1. Microsoft Graph エクスプローラーで、[サービス プリンシパル API](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal) を使用するためにカスタム クレーム ポリシーを構成するアプリケーションを識別します。
2. 次の API を実行してカスタム クレーム ポリシーを作成します。 このポリシーは、サービス プリンシパルにリンクされ、トークンに EmployeeID クレームと TenantCountry クレームを追加します。

    ```http
    PUT https://graph.microsoft.com/beta/servicePrincipals/<servicePrincipal-id>/claimsPolicy
    ```

    リクエスト本文

    ```json
    {
        "includeBasicClaimSet": true,
        "claims": [
            {
                "@odata.type": "#microsoft.graph.customClaim",
                "name": "employeeId",
                "namespace": null,
                "tokenFormat": [
                    "jwt"
                ],
                "samlAttributeNameFormat": null,
                "configurations": [
                    {
                        "condition": null,
                        "attribute": {
                            "@odata.type": "#microsoft.graph.sourcedAttribute",
                            "id": " employeeId",
                            "source": "user",
                            "isExtensionAttribute": false
                        },
                        "transformations": []
                    }
                ]
            },
            {
                "@odata.type": "#microsoft.graph.customClaim",
                "name": "country",
                "namespace": null,
                "tokenFormat": [
                    "jwt"
                ],
                "samlAttributeNameFormat": null,
                "configurations": [
                    {
                        "condition": null,
                        "attribute": {
                            "@odata.type": "#microsoft.graph.sourcedAttribute",
                            "id": " tenantcountry",
                            "source": "user",
                            "isExtensionAttribute": false
                        },
                        "transformations": []
                    }
                ]
            }
        ]
    }
    ```
3. 新しいポリシーを表示するには、次のコマンドを実行します。

    ```http
    GET https://graph.microsoft.com/beta/servicePrincipals/<servicePrincipal-id>/claimsPolicy
    ```

    応答:

    ```json
    {
        "@odata.context": "…",
        "id": "aaaaaaaa-bbbb-cccc-1111-222222222222",
        "includeBasicClaimSet": true,
        "includeApplicationIdInIssuer": false,
        "audienceOverride": null,
        "groupFilter": null,
        "claims": [...]
    }
    ```

#### トークンでクレーム変換を使用する

この例では、リンクされたサービス プリンシパルに対して発行された JWT に、カスタム クレーム "JoinedData" を出力するようにポリシーを更新します。 この要求には、ユーザー オブジェクトの extensionattribute1 属性に格納されたデータと "-ext" を結合して作成された値が追加されます。

1. Microsoft Graph エクスプローラーで、[サービス プリンシパル API](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal) を使用するためにカスタム クレーム ポリシーを構成するアプリケーションを識別します。
2. 次の API を実行してカスタム クレーム ポリシーを作成します。 このポリシーは、カスタム クレーム `JoinedData` をトークンに出力します。

    ```http
    PATCH https://graph.microsoft.com/beta/servicePrincipals/<servicePrincipal-id>/claimsPolicy
    ```

    リクエスト本文

    ```json
    {
        "includeBasicClaimSet": true,
        "claims": 
        [
            {
                "@odata.type": "#microsoft.graph.customClaim",
                "name": "JoinedData",
                "namespace": null,
                "tokenFormat": [
                    "jwt"
                ],
                "samlAttributeNameFormat": null,
                "configurations": 
                [
                    {
                        "condition": null,
                        "attribute": null,
                        "transformations": 
                        [
                            {
                                "@odata.type": "#microsoft.graph.joinTransformation",
                                "separator": "-",
                                "input": 
                                {
                                    "treatAsMultiValue": false,
                                    "attribute": 
                                    {
                                        "@odata.type": "#microsoft.graph.sourcedAttribute",
                                        "id": "extensionattribute1",
                                        "source": "user",
                                        "isExtensionAttribute": false
                                    }
                                },
                                "input2": 
                                {
                                    "treatAsMultiValue": false,
                                    "attribute": 
                                    {
                                        "@odata.type":"#microsoft.graph.valueBasedAttribute",
                                        "value": "ext"
                                     }
                                }
                            }
                        ]
                    }
                ]
            }
        ]
    }
    ```

    注意

    カスタム クレーム ポリシーは、厳密に型指定されたポリシーであり、各変換では異なる `@odata.type` 値が使用されます。
3. 新しいポリシーを表示し、ポリシーの `ObjectId` を取得するには、次のコマンドを実行します。

    ```http
    GET https://graph.microsoft.com/beta/servicePrincipals/<servicePrincipal-id>/claimsPolicy
    ```

    応答:

    ```json
    {
        "@odata.context": "…",
        "id": "aaaaaaaa-bbbb-cccc-1111-222222222222",
        "includeBasicClaimSet": true,
        "includeApplicationIdInIssuer": false,
        "audienceOverride": null,
        "groupFilter": null,
        "claims": [...]
    }
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/claims-customization-powershell"} -->
## PowerShell とクレーム マッピング ポリシーを使用したクレームのカスタマイズ - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-customization-powershell
- Service: identity-platform
- Article date: 2023-11-02
- Summary: この記事では、PowerShell を使用して Microsoft Entra ID の要求をカスタマイズする方法について説明します

要求とは、そのユーザーに発行するトークンの中にあるユーザーに関する ID プロバイダーが提示した情報を指します。 テナント管理者は、自身のテナントの特定のアプリケーションに対するトークンに組み込むクレームをカスタマイズできます。 要求のマッピング ポリシーを使用すると、次の操作を行うことができます。

- トークンに組み込むクレームを選ぶ。
- まだ存在しない種類のクレームを作成する。
- 特定のクレームに入れるデータのソースを指定、変更する。

要求のカスタマイズでは、SAML、OAuth、OpenID Connect のプロトコルに対する要求のマッピング ポリシーを構成できます。

注

クレーム マッピング ポリシーは、カスタム クレーム ポリシーと、Microsoft Entra 管理センターを通じて提供される[クレームのカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)の両方に代わるものです。 クレーム マッピング ポリシーを使用してアプリケーションに対するクレームをカスタマイズすることは、そのアプリケーションに対して発行されたトークンで、カスタム クレーム ポリシーの構成や Microsoft Entra 管理センターの[\[クレームのカスタマイズ\]](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization) ブレード内の構成が無視されることを意味します。

### 前提条件

- [Microsoft Entra テナントを取得する方法](https://learn.microsoft.com/ja-jp/entra/external-id/customers/quickstart-tenant-setup)について学習します。
- 最新の [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) をダウンロードします。

### はじめに

次の例では、サービス プリンシパルのポリシーを作成、更新、リンク、および削除します。 クレームマッピング ポリシーは、サービス プリンシパル オブジェクトにのみ割り当てることができます。

要求のマッピング ポリシーを作成するときに、トークンのディレクトリ拡張属性から要求を発信するように設定することもできます。 `ExtensionID` 要素の ID ではなく、拡張属性の `ClaimsSchema` を使用します。 拡張属性の詳細については、[ディレクトリ拡張属性の使用](https://learn.microsoft.com/ja-jp/entra/identity-platform/schema-extensions)に関するページをご覧ください。

注

[要求のマッピング ポリシーを構成するには、Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) が必要です。

ターミナルを開き、次のコマンドを実行して Microsoft Entra 管理者アカウントにサインインします。 新しいセッションを開始するたびにこのコマンドを実行します。

```PowerShell
Import-Module Microsoft.Graph.Identity.SignIns

Connect-MgGraph -Scopes "Policy.ReadWrite.ApplicationConfiguration", "Policy.Read.All"
```

次に、要求のマッピング ポリシーを作成してサービス プリンシパルに割り当てます。 一般的なシナリオについては、次の例を参照してください。

- トークンから基本要求を除外する
- EmployeeID と TenantCountry を要求としてトークンに含める
- トークンで要求変換を使用する

クレームマッピング ポリシーを作成したら、トークンにカスタム クレームを組み込むことを承認するよう、アプリケーションを設定します。 詳しくは「[セキュリティに関する考慮事項](https://learn.microsoft.com/ja-jp/entra/identity-platform/jwt-claims-customization#security-considerations)」を読んでください。

#### トークンから基本要求を除外する

この例では、リンク サービス プリンシパルに対して発行されたトークンから、[基本要求セット](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-claims-customization#claim-sets)を削除するポリシーを作成します。

1. クレームマッピング ポリシーを作成します。 このポリシーは、特定のサービス プリンシパルにリンクされ、トークンから基本要求セットを削除します。
2. 開いているターミナルを使用して、次のコマンドを実行してポリシーを作成します。

    ```PowerShell
    New-MgPolicyClaimMappingPolicy -Definition @('{"ClaimsMappingPolicy":{"Version":1,"IncludeBasicClaimSet":"false"}}') -DisplayName "OmitBasicClaims"
    ```
3. 新しいポリシーを表示し、ポリシーの `ObjectId` を取得するには、次のコマンドを実行します。

    ```PowerShell
    Get-MgPolicyClaimMappingPolicy
    
    Definition                    DeletedDateTime Description DisplayName      Id
    ----------                    --------------- ----------- -----------      --
    {"ClaimsMappingPolicy":{..}}                              OmitBasicClaims  36d1aa10-f9ac...
    ```

#### トークンに要求として `EmployeeID` と `TenantCountry` を含める

この例では、リンクされたサービス プリンシパルに対して発行されたトークンに、`EmployeeID` と `TenantCountry` を追加するポリシーを作成します。 EmployeeID は、SAML トークンと JWT の両方で名前要求の種類として出力されます。 TenantCountry は、SAML トークンと JWT の両方で国/リージョン要求の種類として出力されます。 この例では、操作を続行し、トークンに基本要求セットを含めます。

1. クレームマッピング ポリシーを作成します。 このポリシーは、特定のサービス プリンシパルにリンクされ、EmployeeID 要求と TenantCountry 要求をトークンに追加します。
2. ポリシーを作成するには、ターミナルで次のコマンドを実行します。

    ```PowerShell
    New-MgPolicyClaimMappingPolicy -Definition @('{"ClaimsMappingPolicy":{"Version":1,"IncludeBasicClaimSet":"true", "ClaimsSchema": [{"Source":"user","ID":"employeeid","SamlClaimType":"http://schemas.xmlsoap.org/ws/2005/05/identity/claims/employeeid","JwtClaimType":"employeeid"},{"Source":"company","ID":"tenantcountry","SamlClaimType":"http://schemas.xmlsoap.org/ws/2005/05/identity/claims/country","JwtClaimType":"country"}]}}') -DisplayName "ExtraClaimsExample"
    ```
3. 新しいポリシーを表示し、ポリシーの `ObjectId` を取得するには、次のコマンドを実行します。

    ```PowerShell
    Get-MgPolicyClaimMappingPolicy
    ```

#### トークンで要求変換を使用する

この例では、リンクされたサービス プリンシパルに対して発行された JWT に、カスタム要求 "JoinedData" を出力するポリシーを作成します。 この要求には、ユーザー オブジェクトの extensionattribute1 属性に格納されたデータと "-ext" を結合して作成された値が追加されます。 この例では、トークンで基本要求セットを除外します。

1. クレームマッピング ポリシーを作成します。 このポリシーは、特定のサービス プリンシパルにリンクされ、トークンにカスタム要求 `JoinedData` を出力します。
2. ポリシーを作成するには、次のコマンドを実行します。

    ```PowerShell
    New-MgPolicyClaimMappingPolicy -Definition @('{"ClaimsMappingPolicy":{"Version":1,"IncludeBasicClaimSet":"true", "ClaimsSchema":[{"Source":"user","ID":"extensionattribute1"},{"Source":"transformation","ID":"DataJoin","TransformationId":"JoinTheData","JwtClaimType":"JoinedData"}],"ClaimsTransformations":[{"ID":"JoinTheData","TransformationMethod":"Join","InputClaims":[{"ClaimTypeReferenceId":"extensionattribute1","TransformationClaimType":"string1"}], "InputParameters": [{"ID":"string2","Value":"ext"},{"ID":"separator","Value":"-"}],"OutputClaims":[{"ClaimTypeReferenceId":"DataJoin","TransformationClaimType":"outputClaim"}]}]}}') -DisplayName "TransformClaimsExample"
    ```
3. 新しいポリシーを表示し、ポリシーの `ObjectId` を取得するには、次のコマンドを実行します。

    ```PowerShell
    Get-MgPolicyClaimMappingPolicy
    ```

### 要求のマッピング ポリシーをサービス プリンシパルに割り当てる

サービス プリンシパルにポリシーを割り当てるには、要求のマッピング ポリシーの `ObjectId` と、ポリシーを割り当てる必要があるサービス プリンシパルの `objectId` が必要です。

1. 組織のすべてのサービス プリンシパルを表示するには、[Microsoft Graph API にクエリを実行する](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-list)か、[Microsoft Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)でチェックします。
2. テナント内のすべての要求のマッピング ポリシーを表示し、ポリシー `ObjectId` を取得するには、次のコマンドを実行します。

    ```PowerShell
    Get-MgPolicyClaimMappingPolicy
    ```
3. 要求のマッピング ポリシーとサービス プリンシパルの `ObjectId` がある場合は、次のコマンドを実行します。

    ```PowerShell
    New-MgServicePrincipalClaimMappingPolicyByRef -ServicePrincipalId <servicePrincipalId> -BodyParameter @{"@odata.id" = "https://graph.microsoft.com/v1.0/policies/claimsMappingPolicies/<claimsMappingPolicyId>"}
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/claims-validation"} -->
## クレームを検証してアプリケーションと API をセキュリティで保護する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-validation
- Service: identity-platform
- Article date: 2025-03-21
- Summary: トークンでクレームを検証して、アプリケーションと API のビジネス ロジックをセキュリティで保護する方法について説明します。

トークンの操作は、ユーザーを認可するためのアプリケーション構築の中核部分です。 最小限の特権アクセスに関する[ゼロ トラスト原則](https://learn.microsoft.com/ja-jp/entra/identity-platform/zero-trust-for-developers)に従って、認可の実行時にアクセス トークン内にある特定のクレームの値をアプリケーションで検証する必要があります。

クレーム ベースの認可により、アプリケーションでは、トークン内にあるテナント、サブジェクト、アクターなどの正しい値がトークンに含まれていることを確認できます。 とはいえ、クレーム ベースの認可は、利用するためのさまざまな方法と追跡するシナリオを考えると複雑に思えるかもしれません。 この記事では、アプリケーションが最も安全なプラクティスに確実に準拠しているようにするため、クレーム ベースの認可プロセスを簡略化する予定です。

認可ロジックがセキュリティで保護されていることを確認するには、クレームで次の情報を検証する必要があります。

- トークンに適切な対象ユーザーが指定されている。
- トークンのテナント ID が、データが保存されているテナントの ID と一致している。
- トークンのサブジェクトが適切である。
- アクター (クライアント アプリ) が認可されている。

注

アクセス トークンは、クライアントによって取得された Web API でのみ検証されます。 クライアントはアクセス トークンを検証しないでください。

この記事で説明するクレームの詳細については、「[Microsoft ID プラットフォーム アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)」を参照してください。

### 対象ユーザーを検証する

`aud` クレームにより、トークンの想定されている対象ユーザーを識別します。 クレームを検証する前に、アクセス トークンに含まれる `aud` クレームの値がWeb API と一致することを常に確認する必要があります。 値は、クライアントがトークンを要求した方法によって異なります。 アクセス トークンの対象ユーザーは、エンドポイントによって異なります。

- v2.0 トークンの場合、対象ユーザーは Web API のクライアント ID です。 これは GUID です。
- v1.0 トークンの場合、対象ユーザーは、トークンを検証する Web API で宣言されている appID URI のいずれかです。 たとえば、`api://{ApplicationID}`、またはドメイン名で始まる一意の名前です (ドメイン名がテナントに関連付けられている場合)。

アプリケーションの appID URI の詳細については、「[アプリケーション ID URI](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-best-practices-for-app-registration#application-id-uri-also-known-as-identifier-uri)」を参照してください。

### テナントを検証する

トークンの `tid` が、アプリケーションでデータを保存するために使用されるテナント ID と一致することを常にチェックします。 テナントのコンテキストでアプリケーションの情報が保存されている場合、同じテナントで後でもう一度アクセスする必要があります。 あるテナント内のデータへの、別のテナントからのアクセスを許可しないでください。

テナントの検証は最初の手順ですが、この記事で説明するサブジェクトとアクターのチェックは引き続き必要です。 テナント内のすべてのユーザーを承認する場合は、これらのユーザーを明示的にグループに追加し、グループに基づいて承認することを強くお勧めします。 たとえば、テナント ID と `oid` 要求のプレゼンスを確認するだけで、API はユーザーに加えて、そのテナント内のすべてのサービス プリンシパルを誤って承認する可能性があります。

### サブジェクトを検証する

ユーザー (またはアプリ専用トークン場合はアプリケーション自体) などの、トークンのサブジェクトが認可されているかどうかを判断します。

特定の `sub` または `oid` のクレームのいずれかに対してチェックします。

または、

サブジェクトが、`roles`、`scp`、`groups`、`wids` のクレームを持つ適切なロールまたはグループに属していることを確認できます。 たとえば、変更できないクレーム値 `tid` および `oid` を、アプリケーション データの結合キーとして使用し、ユーザーにアクセスを付与するかどうかを判断します。

`roles`、`groups`、または`wids`要求を使用して、サブジェクトに操作を実行する権限があるかどうかを判断することもできますが、サブジェクトにアクセス許可を付与できるすべての方法の完全な一覧ではありません。 たとえば、管理者が API に書き込むためのアクセス許可を持っていても、通常のユーザーではない場合や、ユーザーが何らかのアクションを実行できるグループに属している場合があります。 `wid` クレームは、Microsoft Entra 組み込みロールに存在するロールからユーザーに割り当てられたテナント全体のロールを示します。 詳細については、「[Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)」を参照してください。

警告

`email`、`preferred_username`、`unique_name` のようなクレームを使用して、保存したり、アクセス トークン内のユーザーがデータにアクセスできるかどうかを判断したりしないでください。 これらの要求は一意ではなく、テナント管理者または場合によってはユーザーが制御できるため、承認の決定には適していません。 これらは表示目的でのみ使用できます。 また、`upn` クレームは認可に使用しないでください。 UPN は一意ですが、ユーザー プリンシパルの有効期間中に頻繁に変更されるため、認可用としては信頼できません。

### アクターを検証する

ユーザーに代わって動作するクライアント アプリケーション ("アクター" と呼ばれます) も認可されている必要があります。`scp` クレーム (スコープ) を使用して、操作を実行するためのアクセス許可がアプリケーションにあることを検証します。 `scp` のアクセス許可は、ユーザーが実際に必要とするものに限定し、[最小限の特権](https://learn.microsoft.com/ja-jp/entra/identity-platform/secure-least-privileged-access)の原則に従う必要があります。

ただし、トークンに `scp` が存在しない発生があります。 次のシナリオでは、`scp` の要求がないことを確認する必要があります。

- デーモン アプリ/アプリのみのアクセス許可
- ID トークン

スコープとアクセス許可の詳細については、「[Microsoft ID プラットフォームのスコープとアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc)」を参照してください。

注

アプリケーションが、アプリ専用トークン (デーモン アプリなど、ユーザーなしでのアプリケーションからの要求) を処理し、個々のサービス プリンシパル ID ではなく、複数のテナント間で特定のアプリケーションを認可する場合があります。 その場合、`appid` クレーム (v1.0 トークンの場合) または `azp` クレーム (v2.0 トークンの場合) をサブジェクト認可に使用できます。 ただし、これらのクレームを使用する場合、アプリケーションは、`idtyp` の省略可能なクレームを検証して、トークンがアプリケーションに対して直接発行されたことを確認する必要があります。 委任されたユーザー トークンはアプリケーション以外のエンティティによって取得される可能性があるため、この方法で認可できるのは `app` の種類のトークンのみです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/concept-native-authentication"} -->
## ネイティブ認証 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication
- Service: identity-platform / external
- Article date: 2026-06-22
- Summary: Microsoft Entra 外部 IDでネイティブ認証を設定する方法について説明します。 モバイルおよびデスクトップ アプリのユーザー インターフェイスをカスタマイズし、シームレスなサインイン エクスペリエンスを提供します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entraのネイティブ認証を使用すると、モバイルおよびデスクトップ アプリケーションのサインイン エクスペリエンスの設計を完全に制御できます。 ブラウザー ベースのソリューションとは異なり、ネイティブ認証を使用すると、アプリのインターフェイスにシームレスに溶け込む視覚的に魅力的なピクセル完璧な認証画面を作成できます。 この方法を使用すると、デザイン要素、ロゴの配置、レイアウトを含むユーザー インターフェイスを完全にカスタマイズできるため、一貫性がありブランド化された外観を作成できます。

ブラウザ委任認証に依存する標準のアプリ サインイン プロセスでは、認証中に中断が発生することがよくあります。 ユーザーは一時的にシステムブラウザにリダイレクトされ、認証を行った後、サインインが完了するとすぐにアプリに戻されます。

ブラウザー委任認証では、攻撃ベクトルの削減やシングル サインオン (SSO) のサポートなどの利点が得られますが、UI カスタマイズオプションは限られています。

### ネイティブ認証を使用する場合

外部 ID でモバイル アプリとデスクトップ アプリの認証を実装する場合、次の 2 つのオプションがあります。

- Microsoftホスト型ブラウザー委任認証。
- 完全なカスタム SDK ベースのネイティブ認証。

選択する方法は、アプリの特定の要件によって異なります。 アプリにはそれぞれ固有の認証ニーズがありますが、留意しておくべき共通の考慮事項がいくつか存在します。 ネイティブ認証とブラウザー委任認証のどちらを選択しても、Microsoft Entra 外部 IDは両方をサポートします。

機能の可用性、サポートされている言語とフレームワーク、ユーザー エクスペリエンス、カスタマイズ、セキュリティのトレードオフなど、2 つのアプローチを並べて比較する方法については、「 [認証方法の選択」を](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-choose-authentication-approach)参照してください。

### ネイティブ認証を有効にする方法

まず、 [ネイティブ認証を使用するタイミング](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication#when-to-use-native-authentication)に関するガイドラインを確認します。 次に、アプリケーションの経営者、デザイナー、開発チームと内部で話し合い、ネイティブ認証が必要かどうかを判断します。

アプリケーションにネイティブ認証が必要であるとチームが判断した場合は、次の手順に従って、Microsoft Entra 管理センターでネイティブ認証を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **アプリの登録**に移動し、パブリック クライアントとネイティブ認証フローを有効にするアプリの登録を選択します。
3. **[管理]** で、 **[認証]** を選択します。
4. [ **リダイレクト URI 構成**] タブで、パブリック クライアント フローを許可します。
    1. **[次のモバイルとデスクトップ フローを有効にする]** で、**[はい]** を選択します。
    2. ネイティブ認証 **を有効にする**には、[はい] を選択します。
5. **[保存]** ボタンを選択します。

### 構成コードを更新する

管理センターでネイティブ認証 API を有効にした後も、Android または iOS/macOS のネイティブ認証フローをサポートするようにアプリケーションの構成コードを更新する必要があります。 そのためには、チャレンジ型フィールドを構成に追加する必要があります。 チャレンジの種類は、アプリがサポートする認証方法についてMicrosoft Entraに通知するためにアプリが使用する値の一覧です。 ネイティブ認証チャレンジの種類の詳細については、「 [ネイティブ認証チャレンジの種類」を参照してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication-challenge-types)。 ネイティブ認証コンポーネントを統合するように構成が更新されていない場合、ネイティブ認証 SDK と API は使用できません。

### ネイティブ認証のセキュリティに関する考慮事項

ネイティブ認証を使用すると、開発チームは認証エクスペリエンスを完全に制御できます。 この制御により、セキュリティで保護されたトークン処理やトランスポート セキュリティ (HTTPS) など、アプリの実装におけるセキュリティのベスト プラクティスに従う必要があります。

### シングル サインオン (SSO)

ネイティブ認証では、埋め込み Web ビューのシングル サインオン (SSO) がサポートされます。 これにより、ユーザーはネイティブ アプリの UI を介して 1 回サインインした後、2 番目のログイン プロンプトを表示せずに、埋め込み Web ビュー `WKWebView` でホストされている Web リソース (iOS または Android 上の `WebView` など) にアクセスできます。

これを実現するには、Native Auth SDK または [ネイティブ認証 API](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api) を使用してアクセス トークンを取得し、 `Authorization` ヘッダーを介して Web ビューの HTTP 要求に挿入します。 Web リソースはトークンを検証し、セッションを確立し、ネイティブ エクスペリエンスから Web コンテンツへのシームレスな移行を提供します。

実装手順については、「 [ネイティブ アプリから埋め込み Web ビューへのシングル サインオンを実装する](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-native-authentication-webview-sso)」を参照してください。

Note

システム ブラウザーを介したアプリ間 SSO は、ネイティブ認証ではサポートされていません。

### ネイティブ認証を使用する方法

ネイティブ認証 API または Android、iOS、macOS、および Web アプリケーション用の Microsoft Authentication Library (MSAL) SDK を使用して、ネイティブ認証を使用するアプリを構築できます。 可能な限り、アプリへのネイティブ認証を追加するには、MSAL を使用することをお勧めします。

ネイティブ認証のサンプルとチュートリアルの詳細については、次の表を参照してください。

| 言語/プラットホーム | クイック スタート | ビルドと統合ガイド |
| --- | --- | --- |
| Android (Kotlin) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-run-native-authentication-sample-android-app) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-native-authentication-prepare-android-app) |
| iOS (Swift) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-run-native-authentication-sample-ios-app) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-native-authentication-prepare-ios-macos-app) |
| macOS (Swift) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-run-native-authentication-sample-macos-app) | • [ユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-native-authentication-prepare-ios-macos-app) |
| React (Next.js) | • [クイック スタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-single-page-app-sdk-sign-in) | • [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sdk-sign-up) |
| Angular（アンギュラー） | • [クイック スタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-single-page-app-sdk-sign-in) | • [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-angular-sign-up) |

現在 MSAL でサポートされていないフレームワークでアプリを作成する予定の場合は、認証 API を使用できます。 詳細については、 [ネイティブ認証 API リファレンスを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/concept-native-authentication-challenge-types"} -->
## ネイティブ認証のチャレンジタイプ - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-challenge-types
- Service: identity-platform / external
- Article date: 2026-02-27
- Summary: ネイティブ認証を使用するアプリが、サポートする認証フローについてネイティブ認証 API に通知する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ネイティブ認証では、次の 2 つの認証フローがサポートされます。

- ワンタイム パスコード (OTP) を使用した電子メール
- セルフサービス パスワード リセット (SSPR) をサポートする電子メールとパスワード

ネイティブ認証を使用してユーザーをサインインさせるクライアント アプリでは、どちらの認証フローも使用できます。 ネイティブ認証 API を正常に呼び出すには、アプリがサポートする認証フローと機能を宣言する必要があります。 ネイティブ認証 API を使用すると、クライアント アプリは定義済みの値を使用して、サポートされているチャレンジの種類と機能をアドバタイズできます。

### チャレンジの種類

チャレンジの種類は、クライアント アプリがネイティブ認証 API に対してサポートする認証フローを宣言するための要求に含まれる定義済みの値です。

次の表に、サポートされているチャレンジの種類の値を示します。

| チャレンジの種類 | Description |
| --- | --- |
| *パスワード* | このチャレンジの種類は、アプリがユーザーからのパスワード資格情報の収集をサポートしていることを示します。 |
| *oob* | このチャレンジの種類は、アプリケーションがセカンダリ チャネルを使用してユーザーに送信されるワンタイム パスワードまたはパスコード (OTP) コードの使用をサポートしていることを示します。 現時点では、この API は電子メールと SMS OTP のみをサポートしています。 |
| *リダイレクト* | このチャレンジの種類は、アプリケーションがブラウザー委任認証 (Web フォールバックとも呼ばれます) へのフォールバックをサポートしていることを示します。 すべてのネイティブ認証に準拠したアプリでは、この機能がサポートされている必要があります。 この要件は、アプリがネイティブ認証 API に対して行うすべての呼び出しで、このチャレンジの種類を含める必要があることを意味します。 クライアント アプリにこのチャレンジの種類を含めなければ、要求は失敗します。 |

ネイティブ認証で新しい認証方法がサポートされている場合は、新しい値が追加されます。

### ネイティブ認証フローの課題タイプの値

次の表は、アプリがさまざまな認証フローに使用するチャレンジの種類の値をまとめたものです。

| - | サインアップ フロー | サインインの流れ | SSPR |
| --- | --- | --- | --- |
| **パスワードを含む電子メール** | *oob*、 *パスワード*、 *リダイレクト* | *oob*、 *パスワード*、 *リダイレクト* | *oob* と *リダイレクト* |
| **電子メール OTP** | *oob* と *リダイレクト* | *oob* と *リダイレクト* | 適用なし |

**重要な注意事項:**

- [ネイティブ認証 API](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api) を直接使用するアプリでは、サポートされているチャレンジの種類を宣言するときに*、リダイレクト* チャレンジの種類を含める必要があります。
- ネイティブ認証 SDK (Android、iOS、または JavaScript) を使用するアプリでは、SDK に自動的に *含まれるリダイレクト* チャレンジの種類を含める必要はありません。

### 能力

クライアント アプリでは、チャレンジの種類に加えて、 *機能*の一覧を指定できます。 `challenge_type`では、アプリがサポートする認証方法を定義しますが、`capabilities`は、クライアント アプリが処理できる追加のフローと、ユーザーに提供できる UI エクスペリエンスを示します。

ネイティブ認証 API では、次の機能がサポートされています。

- `mfa_required`: クライアント アプリが多要素認証 (MFA) フローをインラインで処理できることを示します。これには、 `/introspect`、 `/challenge`、および `/token` エンドポイントを順番に呼び出し、必要に応じてユーザーが MFA チャレンジを完了するための適切な UI が表示されます。 アプリで条件付きアクセス認証コンテキストによって駆動されるリスクベースの MFA などのインライン MFA シナリオ (ネイティブ認証エンドポイントの前でWeb Application Firewallを使用するサードパーティのアカウント引き継ぎ保護統合など) を統合する場合に、この機能をアドバタイズします。 アプリが `mfa_required` をアドバタイズせず、MFA が必要な場合は、ネイティブ認証 API によって [Web フォールバック](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-web-fallback)が開始されます。
- `registration_required`: クライアントは強力な認証登録を処理できます。登録 API を呼び出し、UI を表示して、強力な認証方法の登録をユーザーに案内できます。

### サポートされていない課題の種類と機能の挙動

次の表は、ネイティブ認証 API またはクライアント アプリが特定のチャレンジの種類または機能をサポートしていない場合の動作をまとめたものです。

| Scenario | 行動 |
| --- | --- |
| **クライアント アプリにサポートされていないチャレンジの種類が含まれている** | ネイティブ認証 API はエラーを返し、要求を無効として扱います。 |
| **クライアント アプリにサポートされていない機能が含まれている** | ネイティブ認証 API はエラーを返し、要求を無効として扱います。 |
| **クライアント アプリに必要なチャレンジの種類を含めできない** | アプリは、管理者によって構成されたチャレンジの種類をサポートしていません。 ネイティブ認証 API は [、Web フォールバック](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-web-fallback)を開始します。 |
| **クライアント アプリに必要な機能を含めできない** | MFA または強力な認証登録が必要ない場合、アプリは通常機能します。 これらの機能が必要であってもサポートされていない場合、ネイティブ認証 API は [Web フォールバック](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-web-fallback) を開始して認証フローを完了します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/concept-native-authentication-sms-mfa-third-party-fraud-protection"} -->
## SMS MFA を使用したネイティブ認証に対するサード パーティの不正アクセス保護 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-sms-mfa-third-party-fraud-protection
- Service: identity-platform
- Article date: 2026-03-03
- Summary: サードパーティの不正アクセス保護とネイティブ認証フローを統合してリスクを評価し、SMS ベースの多要素認証を保護する方法について説明します。

ネイティブ認証を使用すると、モバイルおよびデスクトップ アプリケーションのサインイン エクスペリエンスの設計を完全に制御できます。 このモデルは、ユーザー エクスペリエンスに対する柔軟性と制御を提供しますが、特に SMS ベースの多要素認証 (MFA) の場合、不正防止リスクも発生します。

この記事では、ネイティブ認証に関連する不正行為のリスクについて説明した後、サードパーティの不正防止ソリューションを統合してネイティブ認証アプリケーションをセキュリティで保護するためのガイダンスを提供します。

### [前提条件]

- ネイティブ アプリケーションは、サードパーティの不正防止プロバイダーと統合して、SMS ワンタイム パスコード (OTP) を発行する前にリスクシグナルを安全に評価します。
- 顧客が管理する Web アプリケーション ファイアウォール (WAF) をデプロイして、詐欺対策の決定を実施し、SMS スロットリング制限の引き上げをサポートします。
- Microsoft Graph を使用して、サポートされている地域で SMS ベースの MFA に対して 地域のオプトインを有効にします。
- ネイティブ アプリケーションには、サインインしているユーザーの代わりにMicrosoft Graphにアクセスするために必要な[アクセス許可](https://learn.microsoft.com/ja-jp/graph/auth-v2-service?tabs=http)があります。

### ネイティブ認証における不正行為のリスク

Microsoft Entra External IDは、次のようなネイティブ認証アプリケーションのベースライン不正防止を提供します。

- 既知の不正行為の多い地域のブロック
- SMS ワンタイム パスコード (OTP) 要求の基本的な制御
- 電話番号の評判信号

SMS ベースの MFA を使用するネイティブ認証アプリケーションは、次のような追加のリスクにさらされたままです。

- **国際収益シェア詐欺 (IRSF)** は、通信終了と収益共有メカニズムを通じて収益を引き出すために、攻撃者が SMS トラフィックをプレミアム レートの国際目的地に人為的にインフレしたときに発生します。
- **アカウント引き継ぎ (ATO)** は、攻撃者が自動化されたスクリプト化された手法を使用して、侵害された資格情報または有効な資格情報でサインインを試みる一般的な攻撃パターンです。 ATO は SMS または MFA に固有ではありませんが、SMS 検証が有効になっている環境では、アクティビティが正当であるかのように SMS チャレンジが発行される可能性があります。

ブラウザーによって委任された認証フローでは、Microsoft Entra External IDは、豊富なデバイス テレメトリと CAPTCHA の課題を使用して、これらのリスクを軽減します。 ネイティブ認証アプリケーションでは、Microsoft がホストするブラウザーで委任されたサインイン エクスペリエンスは使用されないため、リスク プロファイルは豊富なデバイス テレメトリの恩恵を受けられません。 このため、既定で SMS を使用するネイティブ認証シナリオは、ブラウザーで委任されたフローよりも広範なリスク プロファイリングによって保護されません。 したがって、お客様は、サード パーティのプロバイダーを使用して追加のリスク検出と保護を設定することをお勧めします。

SMS を使用するネイティブ認証シナリオでの不正行為を効果的に軽減するには、

- ネイティブ認証アプリケーションの所有者は、追加の不正行為の検出と軽減策を実装する必要があります
- SMS ワンタイム パスコード (OTP) が送信される前に不正行為の決定が行われる必要がある
- サード パーティの不正行為プロバイダーは、Microsoft Entra External IDの外部で収集されたデバイス、行動、ネットワーク信号を使用してリスクを評価します

### 推奨される不正行為防止アーキテクチャ

Microsoft では、ネイティブ認証アプリケーション、サード パーティの不正アクセス防止プロバイダー、Web アプリケーション ファイアウォール (WAF)、およびMicrosoft Entra External IDで構成される SMS ベースの MFA をユーザーが使用するネイティブ認証アプリケーションをセキュリティで保護するための高度なアーキテクチャをお勧めします。

サード パーティの不正行為防止プロバイダーは、SMS MFA チャレンジが発行される前にリスクを評価します。 デバイス インテリジェンスや電話番号の評判などの外部リスク信号を組み込むことで、システムは以前にリスクの高いサインイン試行をブロックし、不正行為への露出を減らすことができます。

| コンポーネント | メモ |
| --- | --- |
| **ネイティブ アプリケーション** | ネイティブ アプリケーションは、サードパーティの不正検出 SDK を統合します。 アプリケーションは、サードパーティのプロバイダーのツールを使用して、制限付きのプライバシー保護デバイスと動作信号を収集し、それらの信号を現在の認証セッションに関連付けます |
| **サード パーティの不正行為防止プロバイダー** | サードパーティの不正行為プロバイダーは、ネイティブ アプリケーションから収集されたシグナルを評価し、認証試行のリスク レベルを決定します。 評価に基づいて、次のいずれかの結果が発生します。 - **Low or acceptable risk**: 認証フローが続行され、Microsoft Entra External ID がトリガーとなり、SMS ワンタイムパスコード (OTP) が発行されます。  - **追加の検証を必要とするリスクが高い**: フローを続行する前に、デバイスの所有が検証されます。 - **評価に失敗するリスクが高い**: サインイン試行はすぐにブロックされ、SMS チャレンジは送信されません。 [人間のセキュリティ](https://www.humansecurity.com/)や[証明](https://www.prove.com/)などのサード パーティの詐欺プロバイダーを使用できます。 |
| **Web アプリケーション ファイアウォール (WAF)** | WAF は、Microsoft Entra External ID エンドポイントの前に配置されるカスタマー マネージドの強制レイヤーです。 WAF はサードパーティプロバイダーからの不正行為に関する判定を利用し、一貫して適用します。 Microsoft は WAF を構成または運用していません。その動作 (フェールオープンポリシーやフェールクローズ ポリシーを含む) は、お客様が所有しています。 |
| **Microsoft Entra External ID** | Microsoft Entra External IDは、アップストリームの不正行為チェックに合格した要求のみを処理します。 未加工のデバイス テレメトリや、サードパーティのリスク スコアは受け取りません。 アップストリーム承認後にのみSMS OTPを発行し、スロットリング、地域制限、電話番号の評判評価信号などの組み込み制御に依存して、保護を強化するために機能します。 |

#### サインインフロー保護の例

この図は、ネイティブ アプリケーションがサードパーティの不正アクセス保護を SMS ベースの MFA サインイン フローに統合する方法を示しています。 ネイティブ アプリは、SMS OTP が送信される前にリスクを評価するために、Microsoft Entra External ID、Web アプリケーション ファイアウォール (WAF)、および外部詐欺プロバイダーと連携します。 リアルタイムのリスクシグナルで SMS MFA を制限することで、このフローは、正当なユーザーが認証を完了できるようにしながら、リスクの高いサインイン試行をブロックするのに役立ちます。

[Image: サインインが完了またはブロックされる前に、サードパーティのリスク評価によって SMS ベースの MFA がどのようにゲートされるかを示すエンド ツー エンドのネイティブ認証フローの図。]

SMS ベースの MFA を使用するネイティブ認証フローでは、アプリケーションは SMS ワンタイム パスコード (OTP) を送信Microsoft Entra External ID前に不正行為のリスクを評価します。 ネイティブ アプリは、プロバイダー固有のメカニズムを使用して、サインイン プロセスの早い段階でサード パーティの不正防止 SDK を初期化します。

Microsoft Entra External IDは認証状態を駆動し、MFA が必要なタイミングを決定します。 SMS MFA がトリガーされると、ネイティブ アプリはサードパーティ プロバイダーを通じてリアルタイムのリスク評価を実行します。 この評価の一環として、アプリはユーザーの登録された電話番号をMicrosoft Graphから取得し、詐欺プロバイダーと検証して評判と不正使用のシグナルを評価します。

カスタマー マネージド Web アプリケーション ファイアウォール (WAF) は、サード パーティプロバイダーから返された不正行為の決定を強制します。 プロバイダーが要求を許可すると、WAF によって要求がMicrosoft Entra External IDに転送され、SMS OTP が発行され、ユーザーがコードを送信した後に認証が完了します。 Microsoft が SMS チャレンジの高リスクを検出した場合、サービスは要求をブロックします。 サインイン試行が停止し、Microsoft は SMS を送信しません。 プロバイダーが追加の検証を必要とする場合、アプリは、フローを続行する前にプロバイダー固有のチャレンジを完了します。

アップストリーム リスク評価で SMS MFA を制限することで、このフローにより、正当なユーザーが認証を完了できるようにしながら、テレフォニー詐欺、アカウント引き継ぎ、その他の関連する脅威にさらされるリスクが軽減されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/concept-native-authentication-user-attribute-builder"} -->
## ネイティブ認証 SDK 属性ビルダー - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-user-attribute-builder
- Service: identity-platform / external
- Article date: 2024-08-01
- Summary: ネイティブ認証 Android および iOS SDK 属性ビルダーを使用して、組み込み属性とカスタム属性を準備する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ネイティブ認証では、サインアップ時にユーザーから収集した情報は、Microsoft Entra 管理センターのユーザー フローで構成されます。 Microsoft Entra 管理センターに表示されるユーザー属性の名前は、アプリで参照するときに使用する変数名とは異なります。

さいわい、ネイティブ認証 SDK を使用すると、SDK `signUp()` メソッドで使用する前に、ユーザー属性を構築し、値を割り当てることができます。

### ユーザー属性を作成する

## [Android (Kotlin)](#tab/android-kotlin)
Android SDK でユーザー属性を作成するには:

- SDK が提供するユーティリティ クラス `UserAttribute.Builder` を使用します。 `UserAttributes.Builder` クラスには、パラメーターがユーザーから収集した値であるメソッドが含まれています。
- ビルドするユーザー属性を特定し、次のコード スニペットを使用してそれらをビルドします。

    ```kotlin
        //build the user attributes, both built-in and custom attributes
        val userAttributes = UserAttributes.Builder()
            .country(country)
            .city(city)
            .displayName(displayName)
            .givenName(givenName)
            .jobTitle(jobTitle)
            .postalCode(postalCode)
            .state(state)
            .streetAddress(streetAddress)
            .surname(surname)
            .build() 
    
        CoroutineScope(Dispatchers.Main).launch {
            //use the userAttributes variable in your signUp method 
            val actionResult = authAuthClientInstance.signUp(
                username = emailAddress,
                attributes = userAttributes
            )
        }  
    ```
- [カスタム属性](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes#custom-user-attributes)を作成するには、クラス `UserAttribute.Builder` メソッド`customAttribute()`使用します。 このメソッドは、カスタム属性のプログラミング可能な名前と属性の値を受け入れます。

    ```kotlin
       val userAttributes = UserAttributes.Builder()
           .customAttribute("extension_2588abcdwhtfeehjjeeqwertc_loyaltyNumber", loyaltyNumber)
           .build() 
    
       CoroutineScope(Dispatchers.Main).launch {
           //use the userAttributes variable in your signUp method 
           val actionResult = authAuthClientInstance.signUp(
               username = emailAddress,
               attributes = userAttributes
           )
       }  
    ```

## [iOS/macOS (Swift)](#tab/ios-macos-swift)
iOS/macOS MSAL SDK でユーザー属性を作成するには:

- ビルドするユーザー属性を特定し、次の場所でディクショナリ変数を作成します。

    - `key`は、ユーザー属性のプログラム可能な名前を文字列として指定します。 プログラム可能な名前は、組み込み属性またはカスタム属性に対して指定できます。
    - ユーザーから収集したユーザー属性の値に含まれる `value` 。
- ビルドするユーザー属性を特定し、次のコード スニペットを使用してそれらをビルドします。

    ```swift
       let attributes = [
           "country": "United States",
           "city": "Redmond",
           "displayName": displayName,
           "givenName": givenName,
           "jobTitle": jobTitle,
           "postalCode": postalCode,
           "state": state,
           "streetAddress": streetAddress,
           "surname": surname
       ]
    
       authAuthClientInstance.signUp(username: email, attributes: attributes, delegate: self)
    ```
- [カスタム属性](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes#custom-user-attributes)を作成するには、クラス `UserAttribute.Builder` メソッド`customAttribute()`使用します。 このメソッドは、カスタム属性のプログラミング可能な名前と属性の値を受け入れます。

    ```swift
            let attributes = [
                "country": "United States",
                "extension_2588abcdwhtfeehjjeeqwertc_loyaltyNumber", loyaltyNumber
            ]
    
            authAuthClientInstance.signUp(username: email, attributes: attributes, delegate: self)
    ```

---

ユーザー プロファイル属性のプログラム可能な名前の詳細については、 [ユーザー プロファイル属性](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-user-attributes) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/concept-native-authentication-web-fallback"} -->
## ネイティブ認証の Web フォールバック - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-web-fallback
- Service: identity-platform / external
- Article date: 2025-08-08
- Summary: Web フォールバックを使用して、ネイティブ認証を使用する顧客アプリの回復性を向上させる方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Web フォールバックを使用すると、ネイティブ認証を使用するクライアント アプリで、回復性を向上させるためのフォールバック メカニズムとしてブラウザー委任認証を使用できます。 このシナリオは、認証フローを完了するのにネイティブ認証だけでは十分ではない場合に発生します。 たとえば、承認サーバーにクライアントが提供できない機能が必要な場合です。

ネイティブ認証を使用するすべてのクライアント アプリは、Web フォールバックをサポートする必要があります。

### Web フォールバック フロー

このフローは、Web フォールバックが発生する方法を示しています。

- クライアント アプリは、ユーザーから初期情報を収集し、Microsoft Entra に要求を行って認証フローを開始します。
- Microsoft Entra は成功またはエラー応答を返します。 成功応答は、クライアント アプリが引き続き Microsoft Entra への要求を実行できることを示します。 エラー応答は、クライアントが引き続きユーザーに詳細情報を求め、引き続き Microsoft Entra に要求できることを示します。 エラー応答は、クライアントがブラウザー委任認証を使用する必要があることを示すこともできます。
- エラー応答が、クライアントがブラウザー委任認証を使用する必要があることを示している場合、クライアントはブラウザーで認証フローを続行します。

#### サンプル シナリオ

Microsoft Entra が、クライアントがブラウザー委任認証を使用する必要があることを示す可能性がある場合の例を見てみましょう。

- Microsoft Entra 管理センターでは、管理者がパスワード認証方法で電子メールを使用するようにアプリを構成します。
- この構成は、Microsoft Entra では、クライアント アプリがユーザーからメール (ユーザー名) とパスワードを収集する機能を持っている必要があることを意味します。 クライアント アプリは、 *パスワード* チャレンジの種類を送信することで、この機能を Microsoft Entra に伝えます。 チャレンジの種類の詳細については、 [ネイティブ認証チャレンジの種類](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-challenge-types) に関する記事を参照してください。
- クライアント アプリでは、サーバーがユーザーの電子メールに送信するワンタイム パスコードを送信して、電子メールを検証する必要もあります。 クライアント アプリは *、oob* チャレンジの種類を送信することで、この機能を Microsoft Entra に伝えます。 チャレンジの種類の詳細については、 [ネイティブ認証チャレンジの種類に関する記事を参照](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication-challenge-types) してください
- クライアント アプリが *oob* チャレンジと *パスワード* チャレンジの種類の両方を送信しない場合、Microsoft Entra はそれをクライアント アプリが設定された要件を満たさないと解釈します。 この場合、Microsoft Entra は、クライアントがブラウザー委任認証を使用する必要があることを示すエラーを返します。

### Web フォールバックのサポート

Microsoft Entra の応答で、クライアント アプリがブラウザーによって委任された認証にフォールバックする必要があることを示している場合は、 [Microsoft が構築し、サポートされている認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用することをお勧めします。

ネイティブ認証を使用する場合に、次のアプリで Web フォールバックをサポートする方法について説明します。

- [Android アプリ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-native-authentication-android-support-web-fallback)。
- [iOS/macOS アプリ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-native-authentication-ios-macos-support-web-fallback)。
- [シングルページ アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-javascript-sdk-web-fallback)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/configurable-token-lifetimes"} -->
## 構成可能なトークンの有効期間 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes
- Service: identity-platform
- Article date: 2026-04-08
- Summary: セキュリティを強化するために、Microsoft Identity Platform でアクセス トークン、SAML トークン、ID トークンのトークンの有効期間を構成する方法について説明します。

Microsoft ID プラットフォームによって発行されたアクセス、ID、またはセキュリティ アサーション マークアップ言語 (SAML) トークンの有効期間を構成できます。 トークンの有効期間は、組織内のすべてのアプリ、マルチテナント アプリケーション、または特定のサービス プリンシパルに対して設定できます。 [マネージド ID サービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)のトークンの有効期間の構成はサポートされていません。

Microsoft Entra ID では、ポリシーは、個々のアプリケーションまたは組織内のすべてのアプリケーションに適用されるルールを定義します。 各ポリシーの種類には、割り当てられているオブジェクトに適用される方法を決定する一意のプロパティがあります。

ポリシーは、優先度の高いポリシーによってオーバーライドされない限り、すべてのアプリケーションに適用される、組織の既定値として指定できます。 ポリシーは、ポリシーの種類によって優先順位が異なる特定のアプリケーションに割り当てることもできます。

実際のガイダンスについては、 [トークンの有効期間を構成する方法の例を](https://learn.microsoft.com/ja-jp/entra/identity-platform/configure-token-lifetimes)参照してください。

### 制限事項と考慮事項

トークンの有効期間ポリシーを構成する前に、次の点に注意してください。

- **ポータル UI なし**: トークンの有効期間ポリシーは、 Microsoft Graph API と Microsoft GraphPowerShell SDK を介してのみ管理できます。 Microsoft Entra 管理センターに構成画面はありません。
- **SharePoint と OneDrive**: 構成可能なトークン有効期間ポリシーは、SharePoint Online および OneDrive for Business リソースにアクセスするモバイル およびデスクトップ クライアントにのみ適用されます。 Web ブラウザー セッションには適用されません。 Web ブラウザー セッションの有効期間を管理するには、 [条件付きアクセス セッションの有効期間](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime)を使用します。 アイドル セッション タイムアウトの構成については、 [SharePoint Online のブログ](https://techcommunity.microsoft.com/t5/SharePoint-Blog/Introducing-Idle-Session-Timeout-in-SharePoint-and-OneDrive/ba-p/119208) を参照してください。
- **個人用 Microsoft アカウント**: トークンの有効期間ポリシーは、個人の Microsoft アカウント用に開発されたアプリケーション ( `signInAudience` が `AzureADandPersonalMicrosoftAccount` または `PersonalMicrosoftAccount` に設定されている) ではサポートされていません。
- **マネージド ID**: [マネージド ID サービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) のトークンの有効期間の構成はサポートされていません。
- **更新とセッション トークンの有効期間**: 更新とセッション トークンの有効期間は、トークンの有効期間ポリシーを使用して構成できなくなりました。 Microsoft Entra ID では、以下で説明する既定値のみが使用されます。 ユーザーがサインインする必要がある頻度を制御するには、代わりに [条件付きアクセスのサインイン頻度を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime) 使用します。

### アクセス トークン、SAML トークン、および ID トークンのトークン有効期間ポリシー

アクセス トークン、SAML トークン、および ID トークンにトークン有効期間ポリシーを設定できます。

#### アクセス トークン

クライアントは保護されたリソースにアクセスするためにアクセス トークンを使用します。 アクセス トークンは、ユーザー、クライアント、およびリソースの特定の組み合わせに対してのみ使用できます。 アクセス トークンの有効期間の調整は、システム パフォーマンスの向上と、ユーザーのアカウントが無効になった後にクライアントがアクセスを保持する時間の増加との間で、トレードオフとなります。 システム パフォーマンスの向上は、クライアントが新しいアクセス トークンを取得しなければならない回数を減らすことで実現されます。

アクセス トークンの既定の有効期間は、変数です。 発行されると、アクセス トークンの既定の有効期間には、60 分から 90 分 (平均 75 分) の範囲のランダムな値が割り当てられます。 また、既定の有効期間は、トークンを要求するクライアント アプリケーション、トークンが発行されるリソース、テナントで条件付きアクセスが有効になっているかどうかによっても異なります。 詳細については、「[アクセス トークンの有効期間](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens#token-lifetime)」を参照してください。

クライアントとリソースの両方が [継続的アクセス評価 (CAE)](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation) をサポートしている場合、トークンの有効期間は安全な場合、自動的に 24 時間から 28 時間に延長される場合があります。 これらの有効期間の長いトークンは、アカウントの無効化やパスワードの変更などの重要なイベントに応答して、ほぼリアルタイムで取り消されます。 [CAE がトークンの有効期間に与える影響の](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation#token-lifetime)詳細を確認する

#### SAML トークン

SAML トークンは、Web ベースの SaaS アプリケーションの多くで使用され、Microsoft Entra ID の SAML2 プロトコル エンドポイントを使って取得されます。 これらはまた、WS-Federation を使用するアプリケーションでも使用されます。 トークンの既定の有効期間は 1 時間です。 アプリケーションの観点からは、トークンの有効期間は、そのトークン内の `<conditions …>` 要素の NotOnOrAfter 値によって指定されます。 トークンの有効期間が終了したら、クライアントは新しい認証要求を開始する必要があります。これは多くの場合、シングル サインオン (SSO) セッション トークンの結果として、対話型サインインなしで満たされます。

NotOnOrAfter の値は、`AccessTokenLifetime` 内の `TokenLifetimePolicy` パラメーターを使用して変更できます。 この値は、ポリシーで構成されている有効期間に設定され (構成されている場合)、クロック スキュー係数が 5 分になります。

`<SubjectConfirmationData>` 要素で指定されているサブジェクト確認 NotOnOrAfter は、トークンの有効期間の構成には影響されません。

#### ID トークン

ID トークンは、Web サイトとネイティブ クライアントに渡されます。 ID トークンは、ユーザーに関するプロファイル情報を格納します。 ID トークンは、ユーザーとクライアントの特定の組み合わせにバインドされます。 ID トークンは、それらの有効期限まで有効とみなされます。 通常、Web アプリケーションは、アプリケーションにおけるユーザーのセッションの有効期間と、ユーザーに対して発行された ID トークンの有効期間を照合します。 ID トークンの有効期間を調整して、Web アプリケーションがアプリケーション セッションを期限切れにする頻度と、ユーザーが Microsoft ID プラットフォームで再認証する必要がある頻度 (サイレントモードまたは対話形式) を制御できます。

### 構成可能なトークンの有効期間のプロパティ

トークンの有効期間ポリシーとは、トークンの有効期間の規則が含まれる一種のポリシー オブジェクトです。 このポリシーは、このリソースのアクセス トークン、SAML トークン、および ID トークンが有効とみなされる期間を制御します。 有効期間ポリシーは、更新トークンとセッション トークンに対して設定することはできません。 ポリシーが設定されていない場合は、既定の有効期間の値が適用されます。

#### アクセス、ID、および SAML2 トークンの有効期間ポリシーのプロパティ

アクセス トークンの有効期間を短縮すると、侵害されたアクセス トークンまたは ID トークンを悪意のあるアクターが使用できる時間を制限できます。 トレードオフは、トークンをより頻繁に置き換える必要があるため、パフォーマンスが悪影響を受けるということです。

例については、「[Web サインインのポリシーを作成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/configure-token-lifetimes)」を参照してください。

アクセス トークン、ID トークン、SAML2 トークンのトークンの有効期間は、次のポリシー プロパティによって制御されます。

- **プロパティ**: アクセス トークンの有効期間
- **ポリシー プロパティ文字列**: `AccessTokenLifetime`
- **影響**: アクセス トークン、ID トークン、SAML2 トークン
- **既定**:
    - アクセス トークン: トークンを要求するクライアント アプリケーションによって異なります。 CAE 対応セッションをネゴシエートする CAE 対応クライアントは、有効期間が長いトークン (最大 28 時間) を受け取る場合があります。
    - ID トークン、SAML2 トークン: 1 時間
- **最小**: 10 分 (`00:10:00`)
- **最大**: 1 日 (`23:59:59`)

注意

名前にもかかわらず、 `AccessTokenLifetime` はアクセス トークン、ID トークン、SAML2 トークンの有効期間を制御します。

### ポリシーの評価と優先順位付け

トークン有効期間ポリシーを作成して、特定のアプリケーションや組織に割り当てることができます。 複数のポリシーを、特定のアプリケーションに適用できます。 有効なトークン有効期間ポリシーは、次の規則に従います。

重要

トークンの有効期間ポリシーの場合、 **組織レベルの** ポリシーが **アプリケーション レベルの** ポリシーよりも優先されます。 アプリ レベルのポリシーが有効になっていないように見える場合は、組織レベルのポリシーが存在するかどうかを確認します。

- ポリシーが組織に明示的に割り当てられている場合は、そのポリシーが適用されます。
- 組織に明示的に割り当てられているポリシーがない場合、アプリケーションに割り当てられているポリシーが適用されます。
- 組織やアプリケーション オブジェクトに割り当てられているポリシーがない場合は、既定値が適用されます。 (構成可能なトークンの有効期間のプロパティの表を参照してください)。

トークンの有効性は、トークンの使用時に評価されます。 アクセスされているアプリケーションに対して、最も優先度が高いポリシーが有効になります。

### 更新トークンとセッション トークンのトークン有効期間ポリシー (廃止)

重要

2021 年 1 月 30 日の時点で、更新トークンとセッション トークンの有効期間は、トークンの有効期間ポリシーによって構成できなくなりました。 Microsoft Entra ID では、以下で説明する既定値のみが使用されます。 ユーザーがサインインする必要がある頻度を制御するには、代わりに [条件付きアクセスのサインイン頻度を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime) 使用します。

更新またはセッション トークンのプロパティを設定する既存のポリシーがある場合、これらのプロパティは無視されます。 新しいトークンは、常に既定の構成で発行されます。

#### 更新とセッション トークンの既定値 (構成できません)

次の表に、有効な既定値を示します。 これらの値は、トークンの有効期間ポリシーを使用して変更することはできません。

| プロパティ | ポリシーのプロパティ文字列 | 既定値 |
| --- | --- | --- |
| リフレッシュトークンの最大非アクティブ時間 | `MaxInactiveTime` | 90 日間 |
| 単一要素更新トークンの最長有効期間 | `MaxAgeSingleFactor` | Until-revoked |
| 多要素更新トークンの最長有効期間 | `MaxAgeMultiFactor` | Until-revoked |
| 単一要素セッション トークンの最大有効期間 | `MaxAgeSessionSingleFactor` | Until-revoked |
| 多要素セッション トークンの最長有効期間 | `MaxAgeSessionMultiFactor` | Until-revoked |

非永続的セッション トークンの最大非アクティブ時間は 24 時間です。永続セッション トークンの最大非アクティブ時間は 90 日です。 SSO セッション トークンが有効期間内に使用されると、有効期間はさらに 24 時間または 90 日間延長されます。

インベントリから削除された更新/セッション トークンのプロパティをまだ含む可能性がある既存のポリシーを見つけるには、 PowerShell コマンドレットを使用します。

### REST API リファレンス

ヒント

すべての期間は、C# [TimeSpan](https://learn.microsoft.com/ja-jp/dotnet/api/system.timespan) 形式 ( `hh:mm:ss`) を使用して書式設定されます。 最小値の 10 分は `00:10:00` され、最大値は `23:59:59`。

Microsoft Graph を使用して、トークンの有効期間ポリシーを構成し、これをアプリに割り当てることができます。 詳細については、[`tokenLifetimePolicy` リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/tokenlifetimepolicy)とそれに関連付けられているメソッドに関するページを参照してください。

### コマンドレット リファレンス

以下は、[Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) のコマンドレットです。

#### ポリシーの管理

次のコマンドを使用してポリシーを管理できます。

| コマンドレット | 説明 |
| --- | --- |
| [New-MgPolicyTokenLifetimePolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/new-mgpolicytokenlifetimepolicy) | 新しいポリシーを作成します。 |
| [Get-MgPolicyTokenLifetimePolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mgpolicytokenlifetimepolicy) | すべてのトークン有効期間ポリシー、または指定されたポリシーを取得します。 |
| [Update-Mgポリシートークンの有効期間ポリシー](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/update-mgpolicytokenlifetimepolicy) | 既存のポリシーを更新します。 |
| [Remove-MgPolicyTokenLifetimePolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/remove-mgpolicytokenlifetimepolicy) | 指定したポリシーを削除します。 |

#### アプリケーション ポリシー

アプリケーション ポリシーには、次のコマンドレットを使用できます。

| コマンドレット | 説明 |
| --- | --- |
| [New-MgApplicationTokenLifetimePolicyByRef](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/new-mgapplicationtokenlifetimepolicybyref) | 指定したポリシーをアプリケーションにリンクします。 |
| [Get-MgApplicationTokenLifetimePolicyByRef](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgapplicationtokenlifetimepolicybyref) | アプリケーションに割り当てられているポリシーを取得します。 |
| [Remove-MgApplicationTokenLifetimePolicyByRef](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/remove-mgapplicationtokenlifetimepolicybyref) | アプリケーションからポリシーを削除します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/configure-app-multi-instancing"} -->
## アプリのマルチインスタンス化を構成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/configure-app-multi-instancing
- Service: identity-platform
- Article date: 2023-06-09
- Summary: テナント内で同じアプリケーションの複数のインスタンスを構成するために必要なマルチインスタンス化について説明します。

アプリのマルチインスタンス化とは、テナント内の同じアプリケーションの複数のインスタンスの構成の必要性を指します。 たとえば、組織には複数のアカウントがあり、それぞれにインスタンス固有の要求マッピングとロールの割り当てを処理するための個別のサービス プリンシパルが必要です。 または、顧客がアプリケーションの複数のインスタンスを持っており、特別な要求マッピングは必要ありませんが、個別の署名キーに個別のサービス プリンシパルが必要です。

### サインイン方法

ユーザーは、次のいずれかの方法でアプリケーションにサインインできます。

- サービス プロバイダー (SP) によって開始されるシングル サインオン (SSO) と呼ばれるアプリケーションを直接使用します。
- IDP Initiated SSO と呼ばれる ID プロバイダー (IDP) に直接移動します。

組織内で使用される方法に応じて、この記事で説明されている適切な手順に従います。

### サービスプロバイダー（SP）から開始するSSO

SP によって開始される SSO の SAML 要求では、通常、指定された `issuer` はアプリ ID URI です。 アプリ ID URI を使用しても、SP によって開始される SSO を使用するときに、アプリケーションのどのインスタンスが対象になっているかを顧客が区別することはできません。

#### SP開始SSOの構成

各インスタンスのサービス プロバイダー内で構成された SAML シングル サインオン サービス URL を更新して、URL の一部としてサービス プリンシパル GUID を含めます。 たとえば、SAML の一般的な SSO サインイン URL が `https://login.microsoftonline.com/<tenantid>/saml2`され、URL を更新して、 `https://login.microsoftonline.com/<tenantid>/saml2/<issuer>`などの特定のサービス プリンシパルを対象にすることができます。

発行者の値には、GUID 形式のサービス プリンシパル識別子のみが受け入れられます。 サービス プリンシパル識別子は SAML 要求と応答の発行者をオーバーライドし、残りのフローは通常どおりに完了します。 1 つの例外があります。アプリケーションで要求に署名する必要がある場合、署名が有効であった場合でも要求は拒否されます。 拒否は、署名された要求の値を機能的にオーバーライドするセキュリティ リスクを回避するために行われます。

### アイデンティティプロバイダーによるSSOの開始

IDP Initiated SSO 機能は、アプリケーションごとに次の設定を公開します。

- クレーム マッピングまたはポータルを使って設定用に公開される**オーディエンスオーバーライド**オプション。 目的のユース ケースは、複数のインスタンスに同じ対象ユーザーを必要とするアプリケーションです。 アプリケーションにカスタム署名キーが構成されていない場合、この設定は無視されます。
- 発行者がテナントごとに一意ではなく、アプリケーションごとに一意である必要があることを示すアプリケーション ID フラグを **持つ発行者** 。 アプリケーションにカスタム署名キーが構成されていない場合、この設定は無視されます。

#### IDPから開始されるSSOの構成

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. SSO が有効なエンタープライズ アプリを開き、SAML シングル サインオン ブレードに移動します。
4. **[ユーザー属性と要求**] パネルで **[編集]** を選択します。
5. [ **編集] を** 選択して、[詳細オプション] ブレードを開きます。
6. 設定に従って両方のオプションを構成し、[ **保存]** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/configure-token-lifetimes"} -->
## トークンの有効期間を設定する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/configure-token-lifetimes
- Service: identity-platform
- Article date: 2026-04-08
- Summary: Microsoft ID プラットフォームによって発行されたアクセス トークン、SAML トークン、または ID トークンのトークンの有効期間を構成する方法について説明します。 セキュリティと認証の管理を強化します。

この記事では、Microsoft ID プラットフォームによって発行されたアクセス トークン、SAML トークン、ID トークンのトークン有効期間ポリシーを構成する方法について説明します。 セキュリティと認証の管理を向上させるために、組織内のすべてのアプリ、特定のアプリ、またはマルチテナント アプリケーションのトークンの有効期間を設定する方法について説明します。 スクリプトが 1 時間を超えて実行されるように、トークンの有効期間を長くできます。 Microsoft Graph PowerShell SDK などの多くの Microsoft ライブラリとアプリケーションは、必要に応じてアクセス トークンを事前に更新するため、アクセス トークン ポリシーを変更する必要はありません。 詳細については、[構成可能なトークンの有効期間](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes)に関する記事を参照してください。 ユーザーがサインインする必要がある頻度を制御するには、代わりに [条件付きアクセスのサインイン頻度を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime) 使用します。

### 前提条件

開始するには、最新の [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) をダウンロードします。

### ポリシーを作成してアプリに割り当てる

次の手順では、アクセス/ID トークンの有効期間を 4 時間に設定し、そのポリシーをアプリに割り当てるポリシーを作成します。

```powershell
Install-Module Microsoft.Graph

Connect-MgGraph -Scopes  "Policy.ReadWrite.ApplicationConfiguration","Policy.Read.All","Application.ReadWrite.All"

# Create a token lifetime policy
$params = @{
  Definition = @('{"TokenLifetimePolicy":{"Version":1,"AccessTokenLifetime":"04:00:00"}}') 
    DisplayName = "WebPolicyScenario"
  IsOrganizationDefault = $false
}
$tokenLifetimePolicyId=(New-MgPolicyTokenLifetimePolicy -BodyParameter $params).Id

# Display the policy
Get-MgPolicyTokenLifetimePolicy -TokenLifetimePolicyId $tokenLifetimePolicyId

# Assign the token lifetime policy to an app
$params = @{
  "@odata.id" = "https://graph.microsoft.com/v1.0/policies/tokenLifetimePolicies/$tokenLifetimePolicyId"
}

$applicationObjectId="aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb"

New-MgApplicationTokenLifetimePolicyByRef -ApplicationId $applicationObjectId -BodyParameter $params

# List the token lifetime policy on the app
Get-MgApplicationTokenLifetimePolicy -ApplicationId $applicationObjectId

# Remove the policy from the app
Remove-MgApplicationTokenLifetimePolicyByRef -ApplicationId $applicationObjectId -TokenLifetimePolicyId $tokenLifetimePolicyId

# Delete the policy
Remove-MgPolicyTokenLifetimePolicy -TokenLifetimePolicyId $tokenLifetimePolicyId
```

### ポリシーを作成してサービス プリンシパルに割り当てる

次の手順では、アクセス/ID トークンの有効期間を 8 時間に設定し、サービス プリンシパルにポリシーを割り当てるポリシーを作成します。

1. トークンの有効期間ポリシーを作成します。

    ```http
    POST https://graph.microsoft.com/v1.0/policies/tokenLifetimePolicies
    Content-Type: application/json
    {
        "definition": [
            "{\"TokenLifetimePolicy\":{\"Version\":1,\"AccessTokenLifetime\":\"08:00:00\"}}"
        ],
        "displayName": "Contoso token lifetime policy",
        "isOrganizationDefault": false
    }
    ```
2. サービス プリンシパルにポリシーを割り当てます。

    ```http
    POST https://graph.microsoft.com/v1.0/servicePrincipals/00001111-aaaa-2222-bbbb-3333cccc4444/tokenLifetimePolicies/$ref
    Content-Type: application/json
    {
      "@odata.id":"https://graph.microsoft.com/v1.0/policies/tokenLifetimePolicies/00aa00aa-bb11-cc22-dd33-44ee44ee44ee"
    }
    ```
3. サービス プリンシパルのポリシーを一覧表示します。

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals/00001111-aaaa-2222-bbbb-3333cccc4444/tokenLifetimePolicies
    ```
4. サービス プリンシパルからポリシーを削除します。

    ```http
    DELETE https://graph.microsoft.com/v1.0/servicePrincipals/00001111-aaaa-2222-bbbb-3333cccc4444/tokenLifetimePolicies/00aa00aa-bb11-cc22-dd33-44ee44ee44ee/$ref
    ```

### テナント内の既存のポリシーを表示する

組織で作成されたすべてのポリシーを表示するには、[Get-MgPolicyTokenLifetimePolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mgpolicytokenlifetimepolicy) コマンドレットを実行します。 更新またはセッション トークンのプロパティ ( `MaxInactiveTime`、 `MaxAgeSingleFactor`、 `MaxAgeMultiFactor`など) を定義する結果には、受け入れなくなったレガシ設定が含まれます。 これらのプロパティは、2021 年 1 月 30 日に廃止されました。 混乱を避けるために、これらのポリシーを更新または削除することを検討してください。

1. 組織で作成されているすべてのポリシーを表示するには、`Get-MgPolicyTokenLifetimePolicy` を実行します。

    ```powershell
    Get-MgPolicyTokenLifetimePolicy
    ```
2. 指定した特定のポリシーにどのアプリがリンクされているかを確認するには、任意のポリシー ID を指定して [List appliesTo](https://learn.microsoft.com/ja-jp/graph/api/tokenlifetimepolicy-list-appliesto) を実行します。

    ```powershell
    GET https://graph.microsoft.com/v1.0/policies/tokenLifetimePolicies/4d2f137b-e8a9-46da-a5c3-cc85b2b840a4/appliesTo
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/consent-types-developer"} -->
## Microsoft ID プラットフォームにおけるアクセス許可の要求および同委に関する開発者向けガイド - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/consent-types-developer
- Service: identity-platform
- Article date: 2025-05-21
- Summary: 開発者が Microsoft ID プラットフォーム エンドポイントで同意を通じてアクセス許可を要求する方法について説明します。

Microsoft ID プラットフォームのアプリケーションは、必要なリソースまたは API にアクセスするために同意に依存します。 異なる種類の同意は、さまざまなアプリケーション シナリオに適しています。 アプリに同意するための最適なアプローチを選択することで、ユーザーと組織でアプリをより成功させることができます。

この記事では、さまざまな種類の同意と、同意を通じてアプリケーションのアクセス許可を要求する方法について説明します。

注

外部テナントのアプリケーションの場合、お客様はアクセス許可自体に同意できません。 管理者は、アプリケーションが自分の代わりにリソースにアクセスすることに同意する必要があります。 詳細については、「管理者の [同意を付与する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#grant-admin-consent-external-tenants-only)」を参照してください。

### 静的なユーザーの同意

静的ユーザーの同意シナリオでは、Microsoft Entra 管理センターのアプリの構成で必要なすべてのアクセス許可を指定する必要があります。 ユーザー (または、必要に応じて管理者) がこのアプリに同意しない場合、Microsoft ID プラットフォームでは、その時点でユーザーに同意を求めるメッセージが表示されます。

静的なアクセス許可により、管理者は、組織内のすべてのユーザーの代理として同意することができます。

静的な同意と単一のアクセス許可リストに依存することで、コードは適切でシンプルに保たれますが、アプリが事前に必要とする可能性のあるすべてのアクセス許可を要求することも意味します。 この設定により、ユーザーと管理者がアプリのアクセス要求を承認できない可能性があります。

### 増分および動的なユーザーの同意

Microsoft ID プラットフォーム エンドポイントでは、Microsoft Entra 管理センターのアプリケーション登録情報で定義されている静的アクセス許可を無視できます。 代わりに、アプリケーションのコードからアクセス許可を動的に要求できます。 まず、最小限のアクセス許可セットを事前に要求し、顧客がより多くのアプリケーション機能を使用するように時間をかけて他のユーザーに要求することができます。 これを行うには、`scope`するときに  パラメーターにスコープを含めることで、アプリケーションが必要とするスコープをいつでも指定できます。アプリケーション登録情報で事前に定義する必要はありません。

ユーザーが要求のどのスコープにも同意していない場合は、その要求内のすべてのアクセス許可に対して同意を求めるプロンプトが表示されます。 これらは、そのアプリに対して既に付与されている他のアクセス許可 (つまり増分) に加えて付与されます。 増分同意は、委任されたアクセス許可にのみ適用され、アプリケーションのアクセス許可には適用されません。

`scope` パラメーターを使用してアプリケーションがアクセス許可を動的に要求できるようにすると、開発者はユーザーのエクスペリエンスを完全に制御できます。 同意操作を初期段階に組み込み、1 回の初期承認要求ですべてのアクセス許可を求めることができます。 アプリケーションで多数のアクセス許可が必要な場合は、時間の経過と同時にアプリケーションの特定の機能を使用しようとするときに、ユーザーからそれらのアクセス許可を段階的に収集できます。

重要

動的な同意は便利な場合もありますが、管理者の同意を必要とするアクセス許可の場合、顕著な問題が生じます。 ポータルの **[アプリの登録]** および **[エンタープライズ アプリケーション]** ブレードにおける管理者の同意のエクスペリエンスでは、同意の時点でこれらの動的アクセス許可が認識されていません。 開発者は、ポータルでアプリケーションに必要なすべての管理者特権アクセス許可を一覧表示することをお勧めします。

これにより、テナント管理者が、ポータルで一度にすべてのユーザーを代表して同意できます。 ユーザーは、サインイン時にこれらのアクセス許可に対する同意エクスペリエンスを実行する必要が生じません。 代わりに、これらのアクセス許可に動的な同意を使用します。 管理者の同意を付与するには、個々の管理者がアプリにサインインし、適切なアクセス許可の同意プロンプトをトリガーし、同意ダイアログで **組織全体の同意** を選択します。

### 個々のユーザーの同意を要求する

[OpenID Connect または OAuth 2.0](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols) 承認要求では、アプリケーションは `scope` クエリ パラメーターを使用して、必要なアクセス許可を要求できます。 たとえば、ユーザーがアプリにサインインすると、アプリケーションは次の例のような要求を送信します。 (改行は読みやすくするために追加されます)。

```HTTP
GET https://login.microsoftonline.com/common/oauth2/v2.0/authorize?
client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&response_type=code
&redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F
&response_mode=query
&scope=
https%3A%2F%2Fgraph.microsoft.com%2Fcalendars.read%20
https%3A%2F%2Fgraph.microsoft.com%2Fmail.send
&state=12345
```

`scope` パラメーターは、アプリケーションが要求している委任されたアクセス許可のスペース区切りの一覧です。 各アクセス許可は、リソースの識別子 (アプリケーション ID URI) にアクセス許可の値を追加することによって示されます。 要求の例では、アプリケーションには、ユーザーの予定表を読み取り、ユーザーとしてメールを送信するためのアクセス許可が必要です。

サインイン後、Microsoft ID プラットフォームは既存のユーザーの同意を確認します。 ユーザーが要求されたアクセス許可を承認せず、管理者も承認しない場合、プラットフォームはユーザーに同意を求めます。

次の例では、`offline_access` ("アクセス権を与えるデータへのアクセスを管理する") アクセス許可と `User.Read` ("サインインとプロファイルの読み取り") アクセス許可が、アプリケーションへの初期同意に自動的に組み込まれます。 これらのアクセス許可は、適切なアプリケーション機能のために必要です。

`offline_access`アクセス許可は、ネイティブ アプリと Web アプリにとって重要な更新トークンへのアクセス権をアプリケーションに付与します。 `User.Read`アクセス許可は、`sub`要求へのアクセスを許可します。 これにより、クライアントまたはアプリケーションは、時間の経過と同時にユーザーを正しく識別し、基本的なユーザー情報にアクセスできます。

[Image: 職場アカウントの同意を示すスクリーンショットの例。]

ユーザーがアクセス許可要求を承認すると、同意が記録されます。 ユーザーが後でアプリケーションにサインインするときに、もう一度同意する必要はありません。

### 管理者の同意を通じてテナント全体の同意を要求する

テナント全体の同意を要求するには、管理者の同意が必要です。 組織に代わって行われる管理者の同意には、アプリに登録されている静的アクセス許可が必要です。 組織全体に代わって管理者が同意する必要がある場合は、Microsoft Entra アプリ登録ポータルでこれらのアクセス許可を設定します。

#### 委任されたアクセス許可に対する管理者の同意

アプリが [管理者の同意を必要とする委任されたアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc#admin-restricted-permissions) を要求すると、ユーザーに "同意が承認されていません" というエラーが表示され、管理者にアクセスを求める必要があります。 管理者がテナント全体の同意を付与すると、同意が取り消されるか、新しいアクセス許可が追加されない限り、ユーザーに再びプロンプトが表示されることはありません。

同じアプリケーションを使用している管理者には、管理者の同意プロンプトが表示されます。 管理者の同意プロンプトには、テナント全体のユーザーに代わって、要求されたデータへのアクセス権をアプリケーションに付与できるチェック ボックスが表示されます。 ユーザーと管理者の同意エクスペリエンスの詳細については、「アプリケーションの [同意エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/application-consent-experience)」を参照してください。

管理者の同意を必要とする Microsoft Graph の委任されたアクセス許可の例を次に示します。

- User.Read.All を使用したすべてのユーザーの完全なプロファイルの読み取り
- Directory.ReadWrite.All を使用した組織のディレクトリへのデータの書き込み
- Groups.Read.All を使用した組織のディレクトリ内の全グループの読み取り

Microsoft Graph のアクセス許可の完全な一覧を表示するには、「[Microsoft Graph のアクセス許可リファレンス](https://learn.microsoft.com/ja-jp/graph/permissions-reference)を参照してください。

管理者の同意を必要とするように、独自のリソースに対するアクセス許可を構成することもできます。 管理者の同意を必要とするスコープを追加する方法の詳細については、「管理者の同意 [を必要とするスコープを追加する」](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-expose-web-apis#add-a-scope-requiring-admin-consent)を参照してください。

一部の組織では、テナントの既定のユーザーの同意ポリシーが変更される場合があります。 アプリケーションがアクセス許可へのアクセスを要求すると、これらのポリシーに対して評価されます。 ユーザーは、既定で必要でない場合でも、管理者の同意を要求する必要がある場合があります。 管理者がアプリケーションの同意ポリシーを管理する方法については、「アプリの [同意ポリシーの管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-app-consent-policies)」を参照してください。

注

Microsoft ID プラットフォームの承認、トークン、または同意要求で、`scope` パラメーターのリソース識別子を省略すると、既定で Microsoft Graph が使用されます。 たとえば、`scope=User.Read` は `https://graph.microsoft.com/User.Read` として処理されます。

#### アプリケーションのアクセス許可に対する管理者の同意

アプリケーションのアクセス許可には、常に管理者の同意が必要です。 アプリケーションのアクセス許可にはユーザー コンテキストがなく、特定のユーザーに代わって同意付与が行われることはありません。 代わりに、クライアント アプリケーションはアクセス許可を "直接" 付与されます。 これらの種類のアクセス許可は、バックグラウンドで実行されるデーモン サービスと他の非対話型アプリケーションでのみ使用されます。 管理者は、事前にアクセス許可を構成し、Microsoft Entra 管理センターを通じて [管理者の同意を付与](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent) する必要があります。

#### マルチテナント アプリケーションに対する管理者の同意

アクセス許可を要求しているアプリケーションがマルチテナント アプリケーションの場合、そのアプリケーション登録は作成されたテナントにのみ存在するため、ローカル テナントでアクセス許可を構成することはできません。 アプリケーションが管理者の同意を必要とするアクセス許可を要求する場合、管理者はユーザーの代わりに同意する必要があります。 これらのアクセス許可に同意するには、管理者がアプリケーション自体にサインインする必要があるため、管理者の同意サインイン エクスペリエンスがトリガーされます。 マルチテナント アプリケーションの管理者の同意エクスペリエンスを設定する方法については、「[マルチテナント ログインを有効にする](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant#understand-user-and-admin-consent-and-make-appropriate-code-changes) を参照してください

管理者は、次のオプションを使用して、アプリケーションの同意を付与できます。

#### 推奨:ユーザーをアプリにサインインさせる

通常、管理者の同意を必要とするアプリケーションをビルドする場合、アプリケーションには、管理者がアプリのアクセス許可を承認できるページまたはビューが必要です。 このページは次のようになります。

- アプリのサインアップ フローの一部。
- アプリの設定の一部。
- 専用の "接続" フロー。

多くの場合、ユーザーが職場の Microsoft アカウントまたは学校の Microsoft アカウントでサインインした後にのみ、アプリケーションで "接続" ビューが表示されます。

アプリにユーザーをサインインさせると、必要なアクセス許可の承認を求める前に、管理者が属する組織を特定できます。 この手順は厳密には必要ではありませんが、組織のユーザーにとってより直感的なエクスペリエンスを作成するのに役立ちます。

ユーザーにサインインするには、Microsoft ID プラットフォーム プロトコルのチュートリアル に従います。

#### Microsoft Entra アプリ登録ポータルでアクセス許可を要求する

Microsoft Entra アプリ登録ポータルでは、委任されたアクセス許可とアプリケーションのアクセス許可の両方を含め、アプリケーションで必要なアクセス許可を一覧表示できます。 このセットアップでは、 `.default` スコープと Microsoft Entra 管理センターの [ **管理者の同意の付与]** オプションを使用できます。

一般に、アクセス許可は特定のアプリケーションに対して静的に定義する必要があります。 これらは、アプリケーションが動的または増分的に要求するアクセス許可のスーパーセットであることが必要です。

注

アプリケーションのアクセス許可は、[`.default`](https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc#the-default-scope) を使用してのみ要求できます。 そのため、アプリケーションにアプリケーションのアクセス許可が必要な場合は、Microsoft Entra アプリ登録ポータルに表示されていることを確認します。

アプリケーションに対して静的に要求されたアクセス許可の一覧を構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**アプリケーションの登録**&gt;**すべてのアプリケーション**に移動します。
3. アプリケーションを選択するか、まだ作成していない場合は [アプリを作成](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) します。
4. アプリケーションの **[概要**] ページで、[**管理**] で [**API のアクセス許可**] を選択&gt;**アクセス許可を追加します**。
5. 使用可能な API の一覧から **Microsoft Graph** を選択します。 次に、アプリケーションに必要なアクセス許可を追加します。
6. **[アクセス許可の追加]** を選択します。

#### 成功応答

管理者がアプリのアクセス許可を承認した場合、成功した応答は次のようになります。

```HTTP
GET http://localhost/myapp/permissions?tenant=aaaabbbb-0000-cccc-1111-dddd2222eeee&state=state=12345&admin_consent=True
```

| パラメーター | 説明 |
| --- | --- |
| `tenant` | アプリケーションに要求されたアクセス許可を GUID 形式で付与したディレクトリ テナント。 |
| `state` | 要求に含まれ、トークンの応答で返される値。 任意のコンテンツの文字列を指定することができます。 状態は、認証要求が発生する前のアプリケーション内のユーザーの状態 (ページやビューなど) に関する情報をエンコードするために使用されます。 |
| `admin_consent` | は、`True` に設定した場合のみ有効になります。 |

管理者の同意エンドポイントから成功応答を受信した後で、アプリケーションは要求したアクセス許可を付与されます。 次に、必要なリソースのトークンを要求できます。

##### エラー応答

管理者がアプリのアクセス許可を承認しない場合、失敗した応答は次のようになります。

```HTTP
GET http://localhost/myapp/permissions?error=permission_denied&error_description=The+admin+canceled+the+request
```

| パラメーター | 説明 |
| --- | --- |
| `error` | 発生するエラーの種類を分類するために使用できるエラー コード文字列。 また、エラーに対応するためにも使用できます。 |
| `error_description` | 開発者がエラーの根本原因を特定するのに役立つ特定のエラー メッセージ。 |

### 同意後のアクセス許可の使用

ユーザーがアプリのアクセス許可に同意すると、アプリケーションは、一部の容量でリソースにアクセスするためのアプリのアクセス許可を表すアクセス トークンを取得できます。 アクセス トークンは、1 つのリソースにのみ使用できます。 ただし、アクセス トークンの内部には、そのリソースに関してアプリケーションに付与されたすべてのアクセス許可がエンコードされます。 アクセス トークンを取得するために、アプリケーションは次のように Microsoft ID プラットフォーム トークン エンドポイントに要求を行うことができます。

```HTTP
POST common/oauth2/v2.0/token HTTP/1.1
Host: https://login.microsoftonline.com
Content-Type: application/json

{
    "grant_type": "authorization_code",
    "client_id": "00001111-aaaa-2222-bbbb-3333cccc4444",
    "scope": "https://microsoft.graph.com/Mail.Read https://microsoft.graph.com/mail.send",
    "code": "AwABAAAAvPM1KaPlrEqdFSBzjqfTGBCmLdgfSTLEMPGYuNHSUYBrq...",
    "redirect_uri": "https://localhost/myapp",
    "client_secret": "A1bC2dE3f..."  // NOTE: Only required for web apps
}
```

結果のアクセス トークンは、リソースへの HTTP 要求で使用できます。 これは、アプリケーションが特定のタスクを実行するための適切なアクセス許可を持っていることをリソースに確実に示します。

OAuth 2.0 プロトコルとアクセス トークンを取得する方法の詳細については、 [Microsoft ID プラットフォーム エンドポイント プロトコルリファレンスを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/content-security-policy"} -->
## Microsoft Entra ID でのコンテンツ セキュリティ ポリシー (CSP) ロールアウト - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/content-security-policy
- Service: identity-platform
- Article date: 2025-11-13
- Summary: Microsoft Entra ID が CSP ヘッダーを適用してクロスサイト スクリプティング (XSS) 攻撃からサインイン ページを保護する方法と、段階的なロールアウトに備える方法について説明します。

コンテンツ セキュリティ ポリシー (CSP) は、信頼されたスクリプトとリソースの読み込みのみを許可するブラウザー セキュリティ ヘッダーです。 Microsoft Entra ID は、外部ソースからの未承認のスクリプトをブロックし、クロスサイト スクリプティング (XSS) などのスクリプトインジェクション攻撃による脆弱性のリスクを軽減するためのプロアクティブな手段として、サインイン ページに CSP を適用します。

Important

**CSP の適用は、2026 年 10 月中旬から後半にグローバルに開始されます。**`login.microsoftonline.com`でのブラウザー ベースのサインインにのみ適用されます。 カスタム ドメインと MSAL/API フローを使用している Microsoft Entra 外部 ID のお客様は **影響を受けません**。

Microsoft では、Microsoft Entra サインイン エクスペリエンスにコードを挿入するブラウザー拡張機能やツールを使用しないことをお勧めします。 このアドバイスに従えば、エクスペリエンスは変更されず、それ以上のアクションは必要ありません。

Microsoft Entra サインイン ページにコードを挿入するツールまたはブラウザー拡張機能を使用する場合は、この変更がリリースされる前にコードを挿入しない別のツールに切り替えます。

CSP 強制の準備の詳細については、「CSP 強制 の準備方法」を参照してください。

### スクリプトまたはコード挿入のリスク

スクリプトまたはコード挿入は、悪意のあるスクリプトが承認なしでユーザーのブラウザーで実行されるときに発生します。 この脆弱性は、次の原因となる可能性があります。

- **データの盗難**: 攻撃者は資格情報やトークンなどの機密情報を盗むことができます。
- **セッション ハイジャック**: 挿入されたスクリプトは、アクティブなセッションを制御できます。
- **マルウェアの配信**: 悪意のあるコードは、ユーザー デバイスに有害なソフトウェアをインストールする可能性があります。
- **信頼の喪失**: サインイン ページが侵害されると、ユーザーの信頼とブランドの評判が損なわれます。

XSS は、最も一般的なインジェクション攻撃の 1 つです。 これにより、攻撃者はユーザーのブラウザーで悪意のあるスクリプトを実行し、資格情報の盗用、セッションのハイジャック、機密データの侵害を行うことができます。

CSP は、ブラウザーで実行できるスクリプトを制限することで、これらの攻撃を防ぐことができます。 CSP を適用することで、Microsoft Entra ID では、信頼された Microsoft ドメインのスクリプトのみを認証中に実行できます。

### CSP は多層防御です

CSP は、XSS などのスクリプトインジェクション攻撃に対する重要な防御層を追加します。 現在、Microsoft Entra と最新のブラウザーでは、悪意のあるスクリプトが防御の第一層として Web サイトに挿入されるのを防ぐためのメカニズムが既に実装されています。 ただし、ユーザーがインストールした悪意のあるブラウザー拡張機能やゼロデイ脆弱性などのメカニズムにもかかわらず、スクリプトを挿入できる場合、CSP はそのスクリプトの実行を妨げるものです。 CSP は、信頼されたスクリプトの nonce と origin のみを許可リストし、既定で他のすべてをブロックすることでこれを実現します。 この多層防御アプローチにより、既存のセキュリティ対策が強化されます。

### CSP 施行範囲と主要な詳細

CSP の適用により、信頼された Microsoft ドメインからのスクリプトの実行のみを許可することで、Microsoft Entra サインイン エクスペリエンスのセキュリティがさらに強化されます。 これにより、承認されていない外部スクリプトの挿入の機会が最小限に抑えられます。 この分析では、ほとんどの違反は、外部のブラウザー拡張機能またはサード パーティ製ツールにリンクされた挿入されたスクリプトに起因することを示しています。

CSP の適用のスコープと主要な詳細について知る必要がある内容を次に示します。

- **ヘッダーの適用スコープ**: CSP の適用は、 `login.microsoftonline.com`でのブラウザー ベースのサインイン エクスペリエンスにのみ適用されます。 その他のドメインと非ブラウザー認証フローは影響を受けません。
- **Microsoft Authentication Library (MSAL) と API 認証**: Microsoft Entra セキュリティ トークン サービス (STS) API と対話する MSAL ベースの認証フローは、ブラウザーのサインイン URL に制限されているため、影響を受けません。
- **Microsoft Entra 外部 ID とカスタム ドメイン**: サインインにカスタム ドメインまたは CIAM ドメインを使用している外部 ID のお客様には影響はありません。

### CSP 違反を含む Microsoft 以外のツール

一部のサード パーティ製ツールでは、サインイン ページにスクリプトが挿入され、CSP 違反が発生する可能性があります。 `login.microsoftonline.com`に CSP が適用されると、これらのスクリプトの実行がブロックされます。 ユーザーは引き続き正常にサインインできますが、これにより、特定のサインインまたは監視ワークフローが中断される可能性があります。

挿入されたスクリプトに依存するツールを使用しているお客様は、ベンダーと直接協力して、CSP 要件に準拠する修正プログラムを特定して実装する必要があります。

### CSP の適用を準備する方法

早い段階でサインイン エクスペリエンスを確認します。 Microsoft Entra サインイン ページにスクリプトを挿入するブラウザー拡張機能とツールを削除または移行します。 組織がこのようなツールに依存している場合は、ロールアウトの前にベンダーと協力して準拠した代替手段を採用してください。

事前にサインイン フローをテストして、違反を特定して解決し、中断を最小限に抑え、ユーザーのエクスペリエンスをシームレスに保ちます。 テナントでの正確な効果を特定するには、次の手順に従います。

- **手順 1**: 開発コンソールを開いた状態でサインイン フローを実行し、違反を特定します。
- **手順 2**: 赤で表示された違反に関する情報を確認します。 特定のチームまたはユーザーが違反を引き起こした場合は、そのフローにのみ表示されます。 精度を確保するには、組織内のさまざまなサインイン シナリオを十分に評価します。 違反の例を次に示します。

    [Image: CSP 違反の例を示すスクリーンショット。]

この CSP の更新により、承認されていないスクリプトをブロックすることで保護レイヤーが追加され、進化するセキュリティ上の脅威から組織をさらに保護できます。 スムーズなロールアウトを確実に行うために、事前にサインイン フローを徹底的にテストします。 これにより、問題を早期にキャッチして対処できるため、ユーザーは保護され続け、サインイン エクスペリエンスはシームレスに維持されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/custom-claims-provider-overview"} -->
## カスタム クレーム プロバイダーの概要 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview
- Service: identity-platform
- Article date: 2025-09-16
- Summary: カスタム認証拡張機能フレームワークの一部としてのカスタム クレーム プロバイダーについて説明する概念に関する記事。

この記事では、Microsoft Entra カスタム クレーム プロバイダーの概要について説明します。

ユーザーがアプリケーションに対して認証を行うときは、カスタム クレーム プロバイダーを使用してトークンに要求を追加できます。 カスタム クレーム プロバイダーは、外部システムから要求をフェッチするために、外部 REST API を呼び出すカスタム拡張機能で構成されています。 カスタム クレーム プロバイダーは、ディレクトリ内の 1 つまたは複数のアプリケーションに割り当てることができます。

ユーザーに関するキー データは、多くの場合、Microsoft Entra ID の外部システムに格納されます。 たとえば、セカンダリ メール、課金レベル、機密情報などです。 一部のアプリケーションでは、アプリケーションが設計どおりに機能するために、これらの属性に依存する場合があります。 たとえば、アプリケーションでは、トークン内の要求に基づいて特定の機能へのアクセスをブロックできます。

次のビデオでは、Microsoft Entra カスタム認証拡張機能とカスタム クレーム プロバイダーの素晴らしい概要を紹介します:

次のシナリオでは、カスタム クレーム プロバイダーを使用します。

- **レガシ システムの移行** - ユーザーに関する情報を保持する Active Directory フェデレーション サービス (AD FS) やデータ ストア (LDAP ディレクトリ等) などのレガシ ID システムがある場合があります。 これらのアプリケーションを移行する必要がありますが、ID データを Microsoft Entra ID に完全に移行することはできません。 アプリはトークンに関する特定の情報に依存する可能性があり、再設計することはできません。
- **ディレクトリに同期できない他のデータ ストアとの統合** - サードパーティのシステム、またはユーザー データを格納する独自のシステムがある場合があります。 この情報は、[同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/what-is-cloud-sync)または直接移行によって Microsoft Entra ディレクトリに統合するのが理想的です。 しかし、それは必ずしも実現可能ではりません。 この制限は、データ所在地、規制、またはその他の要件が原因である可能性があります。

注

カスタム クレーム プロバイダーが、カスタム クレームをトークンに追加するための唯一の方法というわけではありません。 [エンタープライズ アプリケーション用に、JSON Web Token (JWT) 内で発行されたクレームをカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity-platform/jwt-claims-customization)することもできます。

### トークン発行開始イベント リスナー

イベント リスナーは、イベントの発生を待機するプロシージャです。 カスタム拡張機能では、**トークン発行開始**イベント リスナーが使用されます。 イベントは、トークンがアプリケーションに発行されるときにトリガーされます。 イベントがトリガーされると、外部システムから属性をフェッチするためにカスタム拡張機能 REST API が呼び出されます。

カスタム クレーム プロバイダーを設定するには、[トークン発行開始イベントを使用して REST API を作成](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-setup)してから、[トークン発行イベント用にカスタム クレーム プロバイダーを構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration)必要があります。

### .NET 用 Azure Functions クライアント ライブラリの認証イベント トリガー

Azure Functions の認証イベント トリガーを使用すると、Microsoft Entra ID 認証イベントを処理するカスタム拡張機能を実装できます。 認証イベント トリガーは、認証イベントの受信 HTTP 要求に対するすべてのバックエンド処理を処理します。

- API 呼び出しをセキュリティで保護するためのトークン検証
- オブジェクト モデル、型指定、IDE IntelliSense
- API 要求スキーマと応答スキーマの受信と送信の検証
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/custom-claims-provider-reference"} -->
## カスタム クレーム プロバイダー リファレンス - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-reference
- Service: identity-platform
- Article date: 2025-05-25
- Summary: カスタム クレーム プロバイダーのリファレンス ドキュメント

このハウツー ガイドでは、カスタム要求プロバイダー イベントの REST API スキーマと要求マッピング ポリシー構造について説明します。

### トークン発行開始イベント

カスタム クレーム プロバイダー トークン発行イベントを使用すると、外部システムからの情報を使用してアプリケーション トークンを強化またはカスタマイズできます。 この情報は、Microsoft Entra ディレクトリのユーザー プロファイルの一部として格納することができません。

#### コンポーネントの概要

カスタム拡張機能をアプリケーションと設定し統合するには、複数のコンポーネントを接続する必要があります。 次の図は、構成ポイントと、カスタム拡張機能を実装するために作成されたリレーションシップの概要を示しています。

[Image: カスタム クレーム プロバイダーを設定して統合するために Microsoft Entra ID で構成するコンポーネントを示すスクリーンショット。]

- **REST API エンドポイント**を一般公開する必要があります。 この図では、Azure 関数で表されています。 REST API はカスタム要求を生成し、カスタム拡張機能に返します。 これは、Microsoft Entraアプリケーションの登録に関連付けられています。
- API に接続するように構成されている Microsoft Entra ID で**カスタム拡張機能**を構成する必要があります。
- カスタマイズされたトークンを受け取る**アプリケーション**が必要です。 たとえば、トークンのデコードされた内容を表示する https://jwt.ms Microsoft 所有の Web アプリケーション。
- https://jwt.ms などのアプリケーションは、**アプリ登録**を使用して Microsoft Entra ID に登録する必要があります。
- アプリケーションとカスタム拡張機能間に関連付けを作成する必要があります。
- 必要に応じて、認証プロバイダーを使用して Azure 関数をセキュリティで保護できます。この記事では、Microsoft Entra ID を使用します。

#### REST API

REST API エンドポイントは、ダウンストリーム サービスとのインターフェイスを担当します。 たとえば、データベース、他の REST API、LDAP ディレクトリ、またはトークン構成に追加する属性を含む他のストアなどです。

REST API からは、属性を含む Microsoft Entra ID に HTTP 応答が返されます。 REST API によって返される属性は、トークンに自動的に追加されません。 代わりに、任意の属性をトークンに含めるために、アプリケーションの要求マッピング ポリシーを構成する必要があります。 Microsoft Entra ID では、クレーム マッピング ポリシーにより、特定のアプリケーションに対して発行されたトークンに出力されるクレームが変更されます。

#### REST API への要求

トークン発行開始イベント用の独自の REST API を開発するには、次の REST API データ コントラクトを使用します。 スキーマは、要求ハンドラーと応答ハンドラーを設計するコントラクトを表します。

Microsoft Entra ID のカスタム拡張機能では、JSON ペイロードを使用して REST API に HTTP 呼び出しを行います。 JSON ペイロードには、ユーザー プロファイル データ、認証コンテキスト属性、およびユーザーがサインインするアプリケーションに関する情報が含まれています。 次の JSON の `id` 値は、Microsoft Entra 認証イベント サービスを表す Microsoft アプリケーションです。 JSON 属性は、API によって追加のロジックを実行するために使用できます。

次の HTTP 要求は、Microsoft Entra が REST API を呼び出す方法を示しています。 この HTTP 要求は、Microsoft Entra からの要求をシミュレートすることで、REST API をデバッグするために使用できます。

```http
POST https://your-api.com/endpoint

Content-Type: application/json

[Request payload]
```

次の JSON ドキュメントでは、要求ペイロードの例を示します。

```json
{
    "type": "microsoft.graph.authenticationEvent.tokenIssuanceStart",
    "source": "/tenants/<Your tenant GUID>/applications/<Your Test Application App Id>",
    "data": {
        "@odata.type": "microsoft.graph.onTokenIssuanceStartCalloutData",
        "tenantId": "<Your tenant GUID>",
        "authenticationEventListenerId": "<GUID>",
        "customAuthenticationExtensionId": "<Your custom extension ID>",
        "authenticationContext": {
            "correlationId": "<GUID>",
            "client": {
                "ip": "30.51.176.110",
                "locale": "en-us",
                "market": "en-us"
            },
            "protocol": "OAUTH2.0",
            "clientServicePrincipal": {
                "id": "<Your Test Applications servicePrincipal objectId>",
                "appId": "<Your Test Application App Id>",
                "appDisplayName": "My Test application",
                "displayName": "My Test application"
            },
            "resourceServicePrincipal": {
                "id": "<Your Test Applications servicePrincipal objectId>",
                "appId": "<Your Test Application App Id>",
                "appDisplayName": "My Test application",
                "displayName": "My Test application"
            },
            "user": {
                "companyName": "Casey Jensen",
                "createdDateTime": "2016-03-01T15:23:40Z",
                "displayName": "Casey Jensen",
                "givenName": "Casey",
                "id": "90847c2a-e29d-4d2f-9f54-c5b4d3f26471", 
                "mail": "casey@contoso.com",
                "onPremisesSamAccountName": "caseyjensen",
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

Fabrikam 組織の B2B ユーザーが Contoso の組織に対して認証を行うと、REST API に送信される要求ペイロードには、以下の形式の `user` 要素が含まれています。

```json
"user": {
    "companyName": "Fabrikam",
    "createdDateTime": "2022-07-15T00:00:00Z",
    "displayName": "John Wright",
    "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
    "mail": "johnwright@fabrikam.com",
    "preferredDataLocation": "EUR",
    "userPrincipalName": "johnwright_fabrikam.com#EXT#@contoso.onmicrosoft.com",
    "userType": "Guest"
}
```

#### REST API からの応答

Microsoft Entra ID は、次の HTTP で REST API 応答を受け取ります。

```http
HTTP/1.1 200 OK

Content-Type: application/json

[JSON document]
```

HTTP 応答で、次の JSON ドキュメントを指定します。ここで、要求 `DateOfBirth` と `CustomRoles` が Microsoft Entra に返されます。

```json
{
    "data": {
        "@odata.type": "microsoft.graph.onTokenIssuanceStartResponseData",
        "actions": [
            {
                "@odata.type": "microsoft.graph.tokenIssuanceStart.provideClaimsForToken",
                "claims": {
                    "DateOfBirth": "01/01/2000",
                    "CustomRoles": [
                        "Writer",
                        "Editor"
                    ]
                }
            }
        ]
    }
}
```

追加の要求を返す必要がない場合は、空の `claims` オブジェクトを指定します。

```json
{
    "data": {
        "@odata.type": "microsoft.graph.onTokenIssuanceStartResponseData",
        "actions": [
            {
                "@odata.type": "microsoft.graph.tokenIssuanceStart.provideClaimsForToken",
                "claims": {
                }
            }
        ]
    }
}
```

#### サポートされるデータ型

次の表は、トークン発行開始イベントでカスタム クレーム プロバイダーによってサポートされるデータ型を示しています。

| データ型 | サポートされています |
| --- | --- |
| 糸 | 正しい |
| 文字列配列 | 正しい |
| ブール値 | いいえ |
| JSON | いいえ |

#### 要求サイズの制限

要求プロバイダーが返すことができる最大要求サイズは、3 KB に制限されています。 これは、REST API によって返されるすべてのキーと値のペアの合計です。

#### 要求のマッピング ポリシー

Microsoft Entra ID では、クレーム マッピング ポリシーにより、特定のアプリケーションに対して発行されたトークンに出力されるクレームが変更されます。 これには、カスタム クレーム プロバイダーからの要求と、トークンへの発行が含まれます。

```json
{
    "ClaimsMappingPolicy": {
        "Version": 1,
        "IncludeBasicClaimSet": "true",
        "ClaimsSchema": [{
            "Source": "CustomClaimsProvider",
            "ID": "dateOfBirth",
            "JwtClaimType": "birthdate"
        },
        {
            "Source": "CustomClaimsProvider",
            "ID": "customRoles",
            "JwtClaimType": "my_roles"
        },
        {
            "Source": "CustomClaimsProvider",
            "ID": "correlationId",
            "JwtClaimType": "correlation_Id"
        },
        {
            "Source": "CustomClaimsProvider",
            "ID": "apiVersion",
            "JwtClaimType": "apiVersion"
        },
        {
            "Value": "tokenaug_V2",
            "JwtClaimType": "policy_version"
        }]
    }
}
```

`ClaimsSchema` 要素には、次の属性にマップされる要求の一覧が含まれています。

- **ソース**は属性のソースである `CustomClaimsProvider` を表します。 最後の要素には、テスト目的でポリシー バージョンの固定値が含まれていることに注意してください。 したがって、`source` 属性は省略されます。
- **ID** は、作成した Azure 関数から返される要求の名前です。

    重要

    ID 属性の値では大文字と小文字が区別されます。 要求名は、Azure 関数から返されたとおりに入力してください。
- **JwtClaimType** は、OpenID Connect アプリ用に出力されたトークン内の要求の省略可能な名前です。 これにより、JWT トークンで返される別の名前を指定できます。 たとえば、API 応答の `ID` 値が `dateOfBirth` の場合、トークンで `birthdate` として出力できます。

クレーム マッピング ポリシーを作成したら、次の手順で Microsoft Entra テナントにアップロードします。 テナントで次の [claimsMappingPolicy](https://learn.microsoft.com/ja-jp/graph/api/claimsmappingpolicy-post-claimsmappingpolicies) Graph API を使用します。

重要

**定義**要素は、単一の文字列値を持つ配列である必要があります。 文字列は、要求マッピング ポリシーの文字列化およびエスケープされたバージョンである必要があります。 https://jsontostring.com/ などのツールを使用して、要求マッピング ポリシーを文字列化できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/custom-extension-attribute-collection"} -->
## 属性コレクションの開始および送信イベントのカスタム認証拡張機能を作成する (プレビュー) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-attribute-collection
- Service: identity-platform
- Article date: 2025-09-16
- Summary: Microsoft Entraカスタム認証拡張機能 REST API を開発して登録する方法について説明します。 カスタム認証拡張機能を使用すると、属性コレクションにロジックを追加できます。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、顧客のMicrosoft Entra External IDでのユーザー サインアップ エクスペリエンスを拡張する方法について説明します。 顧客サインアップ ユーザー フローでは、イベント リスナーを使用して、属性コレクションの前かつ属性の送信時に、属性コレクション プロセスを拡張できます。

- **OnAttributeCollectionStart** イベントは、属性コレクション ページがレンダリングされる前に、属性コレクション ステップの開始時に発生します。 値の事前入力やブロック エラーの表示などのアクションを追加できます。
- **OnAttributeCollectionSubmit** イベントは、ユーザーが属性を入力して送信した後に発生します。 ユーザーのエントリを検証したり変更したりするアクションを追加できます。

属性コレクションの開始イベントと送信イベントのカスタム認証拡張機能を作成するだけでなく、イベントごとに実行するワークフロー アクションを定義する REST API を作成する必要があります。 任意のプログラミング言語、フレームワーク、ホスティング環境を使用して、REST API を作成およびホストできます。 この記事では、C# Azure関数の使用を簡単に開始する方法について説明します。 Azure Functionsでは、最初に仮想マシン (VM) を作成したり、Web アプリケーションを発行したりする必要なく、サーバーレス環境でコードを実行できます。

### 前提条件

- Azure Functionsを含むAzure サービスを使用するには、Azure サブスクリプションが必要です。 既存のAzure アカウントがない場合は、無料試用版にサインアップするか、Visual Studioサブスクリプション特典を使用して、アカウントを作成<>。
- [サインアップとサインインのユーザー フロー](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)。

### 手順 1: カスタム認証拡張機能 REST API を作成する (Azure関数アプリ)

この手順では、Azure Functionsを使用して HTTP トリガー関数 API を作成します。 関数 API は、ユーザー フローのビジネス ロジックのソースです。 トリガー関数を作成した後、それを次のいずれかのイベント用に構成できます。

- 属性収集開始時
- OnAttributeCollectionSubmit

1. 管理者アカウントで [Azure portal](https://portal.azure.com) にサインインします。
2. Azure ポータル メニューまたは **Home** ページで、**リソースの作成**を選択します。
3. **関数アプリ**を検索して選択し、[**作成**] を選択します。
4. [ **基本** ] ページで、次の表に示すように関数アプリの設定を使用します。

    | 設定 | 提案された値 | 説明 |
    | --- | --- | --- |
    | **Subscription** | あなたのサブスクリプション | 新しい関数アプリを作成するサブスクリプション。 |
    | **リソース グループ ** | *myResourceGroup* | 関数アプリを作成する、既存のリソース グループを選ぶか、新しいリソース グループの名前を選びます。 |
    | **関数アプリ名** | グローバルに一意の名前 | 新しい関数アプリを識別する名前。 有効な文字は、`a-z` (大文字と小文字の区別をしない)、`0-9`、および `-`です。 |
    | **公開** | Code | コード ファイルまたは Docker コンテナーの発行オプション。 このチュートリアルでは、[ **コード**] を選択します。 |
    | **ランタイム スタック** | .NET | 好みのプログラミング言語。 このチュートリアルでは、**.NET** を選択します。 |
    | **バージョン** | 6 (LTS) 分離 (プロセス外) | .NET ランタイムのバージョン。 分離 (アウトプロセス) とは、サポートされているホスティング モデルを使用して関数を作成して実行できることを意味します。 |
    | **リージョン** | 優先リージョン | 自分の近く、または関数がアクセスできる他のサービスの近くの[リージョン](https://azure.microsoft.com/regions/)を選択します。 |
    | **オペレーティング システム** | Windows | オペレーティング システムは、ランタイム スタックの選択に基づいて、事前に自動的に選択されます。 |
    | **プランの種類** | "従量課金 (サーバーレス)" | Function App にどのようにリソースが割り当てられるかを定義するホスティング プラン。 |
5. [ **確認と作成** ] を選択してアプリ構成の選択を確認し、[ **作成**] を選択します。 デプロイには、数分かかります。
6. デプロイが完了したら、[ **リソースに移動** ] を選択して新しい関数アプリを表示します。

#### 1.1 HTTP トリガー関数を作成する

Azure関数アプリを作成したので、HTTP 要求で呼び出すアクションの HTTP トリガー関数を作成します。 HTTP トリガーは、Microsoft Entraカスタム認証拡張機能によって参照および呼び出されます。

1. 関数アプリの **Overview** ページ内で、**Functions** ペインを選択し、**Create in Azure portal** の下にある **Create function** を選択します。
2. [ **関数の作成** ] ウィンドウで、[ **開発環境** ] プロパティを **ポータルの [開発**] のままにします。 [ **テンプレート**] で、[ **HTTP トリガー**] を選択します。
3. [**テンプレートの詳細**] で、[*新しい関数]* プロパティに**「CustomAuthenticationExtensionsAPI**」と入力します。
4. **承認レベル**で、[**関数**] を選択します。
5. **を選択して**を作成します。

#### 1.2 OnAttributeCollectionStart の HTTP トリガーを構成する

1. メニューから、[ **コード + テスト**] を選択します。
2. 実装するシナリオの下のタブ ( **Continue**、 **Block**、 **または SetPrefillValues) を選択します**。 コードを指定されたコード スニペットに置き換えます。
3. コードを置き換えた後、上部のメニューから [ **関数 URL の取得**] を選択し、URL をコピーします。 この URL は 、「手順 2: ターゲット URL のカスタム認証拡張機能を作成して登録 する」 **で使用します**。

## [続ける](#tab/start-continue)
この HTTP トリガーを使用して、追加のアクションが必要ない場合にユーザーがサインアップ フローを続行できるようにします。

```csharp
#r "Newtonsoft.Json"

using System.Net;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Primitives;
using Newtonsoft.Json;
using System.Text;

public static async Task<object> Run(HttpRequest req, ILogger log)
{
    log.LogInformation("C# HTTP trigger function processed a request.");

    string requestBody = await new StreamReader(req.Body).ReadToEndAsync();
    dynamic request = JsonConvert.DeserializeObject(requestBody);

    var actions = new List<ContinueWithDefaultBehavior>{
        new ContinueWithDefaultBehavior { type = "microsoft.graph.attributeCollectionStart.continueWithDefaultBehavior"}
    };

    var dataObject = new Data {
        type = "microsoft.graph.onAttributeCollectionStartResponseData",
        actions= actions
    };

    dynamic response = new ResponseObject {
        data = dataObject
    };

    // Send the response
    return response;
}

public class ResponseObject
{
    public Data data { get; set; }
}

[JsonObject]
public class Data {
    [JsonProperty("@odata.type")]
    public string type { get; set; }
    public List<ContinueWithDefaultBehavior> actions { get; set; }
}

[JsonObject]
public class ContinueWithDefaultBehavior {
    [JsonProperty("@odata.type")]
    public string type { get; set; }
}
```

## [ブロック](#tab/start-block)
この HTTP トリガーを使用して、ユーザーがサインアップ プロセスを続行できないようにします。 たとえば、本人確認サービスや外部の ID データ ソースを使用して、ユーザーのメール アドレスを確認できます。

```csharp
#r "Newtonsoft.Json"

using System.Net;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Primitives;
using Newtonsoft.Json;
using System.Text;

public static async Task<object> Run(HttpRequest req, ILogger log)
{
    log.LogInformation("C# HTTP trigger function processed a request.");

    string requestBody = await new StreamReader(req.Body).ReadToEndAsync();
    
    dynamic request = JsonConvert.DeserializeObject(requestBody);       

    var actions = new List<BlockedActions>{
        new BlockedActions { 
            type = "microsoft.graph.attributeCollectionStart.showBlockPage", 
            message = "AttributeCollectionStart Custom Extension message: Sorry, your access request has been blocked. Try reaching an admin at admin@contoso.com."
        }
    };

    var dataObject = new Data {
        type = "microsoft.graph.onAttributeCollectionStartResponseData",
        actions= actions
    };

    dynamic response = new ResponseObject {
        data = dataObject
    };

    // Send the response
    return response;
}

public class ResponseObject
{
    public Data data { get; set; }
}

[JsonObject]
public class Data {
    [JsonProperty("@odata.type")]
    public string type { get; set; }
    public List<BlockedActions> actions { get; set; }
}

[JsonObject]
public class BlockedActions {
    [JsonProperty("@odata.type")]
    public string type { get; set; }
    public string message { get; set; }
}
```

## [事前入力値](#tab/start-set-prefill-values)
この HTTP トリガーを使用して、外部の人事システムやその他のデータ ソースなどから、ユーザー フローに関連付けられている値を事前入力します。 組み込みの属性 (住所など) やカスタム ユーザー属性 (ロイヤルティ番号など) の値を事前入力できます。

```csharp
#r "Newtonsoft.Json"

using System.Net;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Primitives;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.Text;

public static async Task<object> Run(HttpRequest req, ILogger log)
{
    log.LogInformation("C# HTTP trigger function processed a request.");

    string requestBody = await new StreamReader(req.Body).ReadToEndAsync();
    dynamic request = JsonConvert.DeserializeObject(requestBody);

    // The form fields will load with these default values
    var inputs = new Dictionary<string, object>()
    {
        { "postalCode", "<your-prefill-value>" },
        { "streetAddress", "<your-prefill-value>" },
        { "city", "<your-prefill-value>" },
        { "extension_appId_mailingList", false },
        { "extension_appId_memberSince", 2023 }      
    };

    var actions = new List<SetPrefillValuesAction>{
        new SetPrefillValuesAction { 
            type = "microsoft.graph.attributeCollectionStart.setPrefillValues", 
            inputs = inputs }
    };

    var dataObject = new Data {
        type = "microsoft.graph.onAttributeCollectionStartResponseData",
        actions= actions
    };

    dynamic response = new ResponseObject {
        data = dataObject
    };

    // Send the response
    return response;
}

public class ResponseObject
{
    public Data data { get; set; }
}

[JsonObject]
public class Data {
    [JsonProperty("@odata.type")]
    public string type { get; set; }
    public List<SetPrefillValuesAction> actions { get; set; }
}

[JsonObject]
public class SetPrefillValuesAction {
    [JsonProperty("@odata.type")]
    public string type { get; set; }
    public Dictionary<string, object> inputs { get; set; }
}
```

---

#### 1.3 OnAttributeCollectionSubmit の HTTP トリガーを構成する

1. メニューから、[ **コード + テスト**] を選択します。
2. 実装するシナリオとして、次のタブを選択します。 **続行**、 **ブロック**、 **値の変更**、または **検証エラー**。 コードを指定されたコード スニペットに置き換えます。
3. コードを置き換えた後、上部のメニューから [ **関数 URL の取得**] を選択し、URL をコピーします。 この URL は 、「手順 2: ターゲット URL のカスタム認証拡張機能を作成して登録 する」 **で使用します**。

## [続ける](#tab/submit-continue)
この HTTP トリガーを使用して、追加のアクションが必要ない場合にユーザーがサインアップ フローを続行できるようにします。

```csharp
#r "Newtonsoft.Json"

using System.Net;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Primitives;
using Newtonsoft.Json;
using System.Text;

public static async Task<object> Run(HttpRequest req, ILogger log)
{
    log.LogInformation("C# HTTP trigger function processed a request.");

    string requestBody = await new StreamReader(req.Body).ReadToEndAsync();
    dynamic request = JsonConvert.DeserializeObject(requestBody);
    
    var actions = new List<ContinueWithDefaultBehavior>{
        new ContinueWithDefaultBehavior { type = "microsoft.graph.attributeCollectionSubmit.continueWithDefaultBehavior"}
    };					
    var dataObject = new Data {
        type = "microsoft.graph.onAttributeCollectionSubmitResponseData",
        actions= actions
    };    dynamic response = new ResponseObject {
        data = dataObject
    };

    // Send the response
    return response;
}

public class ResponseObject
{
    public Data data { get; set; }
}

[JsonObject]
public class Data {
    [JsonProperty("@odata.type")]
    public string type { get; set; }
    
    public List<ContinueWithDefaultBehavior> actions { get; set; }
}

[JsonObject]
public class ContinueWithDefaultBehavior {
    [JsonProperty("@odata.type")]public string type { get; set; }
}
```

## [ブロック](#tab/submit-block)
この HTTP トリガーを使用して、ユーザーが入力した属性値に基づいて、ユーザーがサインアップ プロセスを続行できないようにします。 たとえば、リスクの高い電話番号に基づいてサインアップをブロックできます。 または、サインアップ フローを終了し、アカウントの作成のためにユーザーをカスタム承認システムに送ることもできます。

```csharp
#r "Newtonsoft.Json"

using System.Net;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Primitives;
using Newtonsoft.Json;
using System.Text;

public static async Task<object> Run(HttpRequest req, ILogger log)
{
    log.LogInformation("C# HTTP trigger function processed a request.");

    string requestBody = await new StreamReader(req.Body).ReadToEndAsync();
    dynamic request = JsonConvert.DeserializeObject(requestBody);
    
    var actions = new List<BlockedActions>{
        new BlockedActions { 
            type = "microsoft.graph.attributeCollectionSubmit.showBlockPage", 
            message = "AttributeCollectionSubmit Custom Extension Message: Thank you for your response. Your access request is processing. You'll be notified when your request has been approved."
        }
    };

    var dataObject = new Data {
        type = "microsoft.graph.onAttributeCollectionSubmitResponseData",
        actions= actions
    };

    dynamic response = new ResponseObject {
        data = dataObject
    };

    // Send the response
    return response;
}

public class ResponseObject
{
    public Data data { get; set; }
}

[JsonObject]
public class Data {
    [JsonProperty("@odata.type")]
    public string type { get; set; }
    public List<BlockedActions> actions { get; set; }
}

[JsonObject]
public class BlockedActions {
    [JsonProperty("@odata.type")]
    public string type { get; set; }
    public string message { get; set; }
}
```

## [値の変更](#tab/submit-modify-collected-values)
この HTTP トリガーを使用して、ユーザー提供の属性コレクションに変更を加えます。 たとえば、ユーザー指定の属性の形式を変更できます。 属性が変更された後は、ユーザーへの通知なしにサインアップが続行されます。 通知が必要な場合は、検証アクションを使用します。

```csharp
#r "Newtonsoft.Json"

using System.Net;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Primitives;
using Newtonsoft.Json;
using System.Text;

public static async Task<object> Run(HttpRequest req, ILogger log)
{
    log.LogInformation("C# HTTP trigger function processed a request.");

    // User attributes will be saved with these override values
    var attributes = new Dictionary<string, object>()
    {
        { "postalCode", "<your-override-value>" },
        { "streetAddress", "<your-override-value>" },
        { "city", "<your-override-value>" },
        { "extension_appId_mailingList", false }
        { "extension_appId_memberSince", 2010 }
    };

    var actions = new List<ModifiedAttributesAction>{
        new ModifiedAttributesAction { 
            type = "microsoft.graph.attributeCollectionSubmit.modifyAttributeValues", 
            attributes = attributes 
        }
    };

    log.LogInformation("actions: " + actions);

    var dataObject = new Data {
        type = "microsoft.graph.onAttributeCollectionSubmitResponseData",
        actions= actions
    };

    dynamic response = new ResponseObject {
        data = dataObject
    };

    // Send the response
    return response;
}

public class ResponseObject
{
    public Data data { get; set; }
}

[JsonObject]
public class Data {
    [JsonProperty("@odata.type")]
    public string type { get; set; }
    public List<ModifiedAttributesAction> actions { get; set; }
}

[JsonObject]
public class ModifiedAttributesAction {
    [JsonProperty("@odata.type")]
    public string type { get; set; }
    public Dictionary<string, object> attributes { get; set; }
}
```

## [検証エラー](#tab/submit-show-validation-error)
この HTTP トリガーを使用して、ユーザーが入力した属性を検証します。 たとえば、外部データ ストアに対して属性を検証できます。 組み込みの属性 (国など) やカスタム ユーザー属性 (ロイヤルティ番号など) を検証できます。

```csharp
#r "Newtonsoft.Json"

using System.Net;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Primitives;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.Text;

public static async Task<object> Run(HttpRequest req, ILogger log)
{
    log.LogInformation($"C# HTTP trigger function processed a request.");

    string requestBody = await new StreamReader(req.Body).ReadToEndAsync();
    dynamic request = JsonConvert.DeserializeObject(requestBody);

    // Parse specified attributes
    string cityValue = (JsonConvert.SerializeObject(request?.data?.userSignUpInfo?.attributes?.city?.value)).ToString();

    string postalCodeValue = (JsonConvert.SerializeObject(request?.data?.userSignUpInfo?.attributes?.postalCode?.value)).ToString();

    string streetAddressValue = (JsonConvert.SerializeObject(request?.data?.userSignUpInfo?.attributes?.streetAddress?.value)).ToString();

    // JSON convert makes the length different, so we put 7 here
    if(cityValue.Length < 7 || postalCodeValue.Length < 7 || streetAddressValue.Length < 7){
        var inputs = new Dictionary<string, string>();

        if(cityValue.Length < 7){
            inputs.Add("city", "Length of city string should be of at 5 characters at least");
        }

        if(postalCodeValue.Length < 7){
            inputs.Add("postalCode", "Length of postalCodeValue string should be of at 5 characters at least");
        }

        if(streetAddressValue.Length < 7){
            inputs.Add("streetAddress", "Length of streetAddress string should be of at 5 characters at least");
        }

        string pageMessage = "Please fix below errors to proceed";

        var actions = new List<ValidationErrorActions>{
            new ValidationErrorActions { type = "microsoft.graph.attributeCollectionSubmit.ShowValidationError", message = pageMessage, attributeErrors = inputs }
        };

        var dataObject = new Data {
            type = "microsoft.graph.onAttributeCollectionSubmitResponseData",
            actions= actions
        };

        dynamic response = new ResponseObject {
            data = dataObject
        };

        log.LogInformation($"Returning validation error");

        // Send the validation error response
        return response;

    }else{
        var actions = new List<ContinueWithDefaultBehavior>{
            new ContinueWithDefaultBehavior { type = "microsoft.graph.attributeCollectionSubmit.ContinueWithDefaultBehavior"}
            };

        var dataObject = new DataContinue {
            type = "microsoft.graph.onAttributeCollectionSubmitResponseData",
            actions= actions
            };

        dynamic response = new ResponseObjectContinue {
            data = dataObject
        };

        log.LogInformation($"Returning continue");
        
        // Send the continue response
        return response;
    }
}

public class ResponseObject
{
    public Data data { get; set; }
}

[JsonObject]
public class Data {
    [JsonProperty("@odata.type")]
    public string type { get; set; }
    public List<ValidationErrorActions> actions { get; set; }
}

[JsonObject]
public class ValidationErrorActions {
    [JsonProperty("@odata.type")]
    public string type { get; set; }
    public string message { get; set; }
    public Dictionary<string, string> attributeErrors {get; set;}
}

public class ResponseObjectContinue
{
    public DataContinue data { get; set; }
}

[JsonObject]
public class DataContinue {
    [JsonProperty("@odata.type")]
    public string type { get; set; }
    public List<ContinueWithDefaultBehavior> actions { get; set; }
}

[JsonObject]
public class ContinueWithDefaultBehavior {
    [JsonProperty("@odata.type")]public string type { get; set; }
}
```

---

### 手順 2: カスタム認証拡張機能を作成して登録する

この手順では、Azure関数を呼び出すためにMicrosoft Entra IDによって使用されるカスタム認証拡張機能を登録します。 カスタム認証拡張機能には、REST API エンドポイントに関する情報、REST API から解析する属性コレクションの開始アクションと送信アクション、REST API に対する認証方法が含まれています。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)および[認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)としてサインインします。
2. **Entra ID**&gt;**外部 ID**&gt;**カスタム認証拡張機能**を参照します。
3. [ **カスタム拡張機能の作成] を選択します**。
4. **[基本**] で、**AttributeCollectionStart** イベントまたは **AttributeCollectionSubmit** イベントを選択し、[**次へ**] を選択します。 これが 前の手順の構成と一致していることを確認してください。
5. **エンドポイント構成**で、次のプロパティを入力します。

    - **名前** - カスタム認証拡張機能の名前。 たとえば、*属性コレクションイベント*の場合。
    - **Target Url** - Azure関数 URL の `{Function_Url}`。
    - **説明** - カスタム認証拡張機能の説明。
6. **次へ**を選択します。
7. **API 認証**で、[**新しいアプリ登録の作成**] オプションを選択して、*関数アプリ*を表すアプリ登録を作成します。
8. アプリに、例えば**Azure Functions 認証イベント API**のような名前を付けます。
9. **次へ**を選択します。
10. [ **作成]** を選択すると、カスタム認証拡張機能と関連付けられているアプリケーションの登録が作成されます。

#### 2.2 管理者の同意を付与する

カスタム認証拡張機能が作成された後は、登録済みアプリに管理者の同意を付与します。これにより、カスタム認証拡張機能が API に対する認証を行えるようになります。

1. **Entra ID**&gt;**外部 ID**&gt;**カスタム認証拡張機能**を参照します。
2. 一覧からカスタム認証拡張機能を選択します。
3. [ **概要** ] タブで、[ **アクセス許可の付与** ] ボタンを選択して、登録済みのアプリに管理者の同意を与えます。 カスタム認証拡張機能では、`client_credentials` を使用して、`Receive custom authentication extension HTTP requests` アクセス許可を使用してAzure関数アプリに対する認証を行います。 **[Accept](https://learn.microsoft.com/ja-jp/entra/identity-platform/承認)** を選択します。

### 手順 3: カスタム認証拡張機能をユーザー フローに追加する

カスタム認証拡張機能を 1 つ以上のユーザー フローに関連付けることができるようになりました。

注

ユーザー フローを作成する必要がある場合は、「 [顧客向けのサインアップとサインイン ユーザー フローの作成」の手順に](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)従います。

#### 3.1 カスタム認証拡張機能を既存のユーザー フローに追加する

1. Microsoft Entra管理センターに少なくともアプリケーション管理者および認証管理者
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して外部テナントに切り替えます。
3. **Entra ID**&gt;**外部アイデンティティ**&gt;**ユーザーフロー**を参照します。
4. 一覧からユーザー フローを選択します。
5. [ **カスタム認証拡張機能**] を選択します。
6. [ **カスタム認証拡張機能** ] ページでは、カスタム認証拡張機能をユーザー フローの 2 つの異なる手順に関連付けることができます。

    - **ユーザーから情報を収集する前に***、OnAttributeCollectionStart* イベントに関連付けられています。 編集 (鉛筆) を選択します。 *OnAttributeCollectionStart* イベント用に構成されたカスタム拡張機能のみが表示されます。 属性コレクションの開始イベント用に構成したアプリケーションを選択し、[選択] を **選択します**。
    - **ユーザーが自分の情報を送信すると***、OnAttributeCollectionSubmit* イベントに関連付けられます。 *OnAttributeCollectionSubmit* イベント用に構成されたカスタム拡張機能のみが表示されます。 属性コレクション送信イベント用に構成したアプリケーションを選択し、[選択] を **選択します**。
7. 両方の属性コレクションの手順の横に一覧表示されているアプリケーションが正しいことを確認します。
8. **[保存]** アイコンを選択します。

### ステップ 4: アプリケーションをテストする

トークンを取得してカスタム認証拡張機能をテストするには、https://jwt.ms アプリを使用できます。 これは Microsoft 所有の Web アプリケーションであり、トークンのデコードされた内容を表示します (トークンの内容がお使いのブラウザーの外に出ることはありません)。

jwt.ms Web アプリケーションを登録するには、次 **の手順に** 従います。

#### 4.1 jwt.ms Web アプリケーションを登録する

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)としてサインインします。
2. **Entra ID**&gt;**App registrations** に移動します。
3. **[新規登録]** を選択します。
4. アプリケーションの **名前** を入力します。 たとえば、 **My Test アプリケーション**です。
5. **[サポートされているアカウントの種類]** で、 **[この組織のディレクトリ内のアカウントのみ]** を選択します。
6. [**リダイレクト URI**] の [**プラットフォームの選択**] ドロップダウンで[**Web**] を選択し、[URL] テキスト ボックスに「`https://jwt.ms`」と入力します。
7. [ **登録** ] を選択してアプリの登録を完了します。

#### 4.2 アプリケーション ID を取得する

アプリの登録の [ **概要**] で、 **アプリケーション (クライアント) ID をコピーします**。 後の手順では、アプリ ID を `<client_id>` として参照します。 Microsoft Graphでは、**appId** プロパティによって参照されます。

#### 4.3 暗黙的なフローを有効にする

**jwt.ms** テスト アプリケーションでは、暗黙的なフローが使用されます。 次の手順を使用して、 *My Test アプリケーション* の登録で暗黙的なフローを有効にします。

重要

Microsoft では、使用可能な最も安全な認証フローを使用することをお勧めします。 この手順で使用する認証フローでは、アプリケーションで非常に高い信頼度が要求されるため、他のフローには存在しないリスクが伴います。 この方法は、運用アプリに対するユーザーの認証には使用しないでください ([詳細を参照](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow))。

1. **[管理]** で、 **[認証]** を選択します。
2. [ **暗黙的な許可とハイブリッド フロー**] で、 **ID トークン (暗黙的およびハイブリッド フローに使用)** チェック ボックスをオンにします。
3. **保存**を選びます。

### 手順 5: Azure関数を保護する

カスタム認証拡張機能Microsoft Entraサーバー間フローを使用して、HTTP `Authorization` ヘッダーでAzure関数に送信されるアクセス トークンを取得します。 特に運用環境で関数をAzureに発行する場合は、承認ヘッダーで送信されたトークンを検証する必要があります。

Azure関数を保護するには、次の手順に従って、Microsoft Entra認証を統合し、受信トークンを *Azure Functions 認証イベント API* アプリケーション登録と検証します。

注

Azure関数アプリが、カスタム認証拡張機能が登録されているテナントとは異なるAzure テナントでホストされている場合は、5.1 OpenID Connect ID プロバイダーの使用手順に進みます。

#### 5.1 Azure関数に ID プロバイダーを追加する

1. [Azure ポータル](https://portal.azure.com)にサインインします。
2. 以前に公開した関数アプリを探して選択します。
3. 左側のメニューで [ **認証** ] を選択します。
4. [ **ID プロバイダーの追加] を選択します**。
5. ID プロバイダーとして **Microsoft** を選択します。
6. テナントの種類として **[顧客** ] を選択します。
7. **App registration**で、カスタム クレーム プロバイダーを登録する際に事前に作成した`client_id`アプリ登録のを入力します。
8. **発行者 URL** には、次の URL `https://{domainName}.ciamlogin.com/{tenant_id}/v2.0`を入力します。

    - `{domainName}` は外部テナントのドメイン名です。
    - `{tenantId}` は外部テナントのテナント ID です。 カスタム認証拡張機能をここに登録する必要があります。
9. [ **認証されていない要求**] で、ID プロバイダーとして **[HTTP 401 Unauthorized** ] を選択します。
10. **[トークン ストア**] オプションの選択を解除します。
11. **Add** を選択して、Azure関数に認証を追加します。

    [Image: 外部テナントで関数アプリに認証を追加する方法を示すスクリーンショット。]

#### 5.2 OpenID Connect ID プロバイダーの使用

Step 5: Protect your Azure Function を構成した場合は、この手順をスキップします。 それ以外の場合、Azure関数が、カスタム認証拡張機能が登録されているテナントとは異なるテナントでホストされている場合は、次の手順に従って関数を保護します。

1. [Azure ポータル](https://portal.azure.com)にサインインし、前に発行した関数アプリに移動して選択します。
2. 左側のメニューで [ **認証** ] を選択します。
3. [ **ID プロバイダーの追加] を選択します**。
4. ID プロバイダーとして **OpenID Connect** を選択します。
5. Contoso Microsoft Entra ID。
6. [ **メタデータ] エントリ**で、ドキュメント URL に次の **URL を入力します**。 `{tenantId}`をMicrosoft Entraテナント ID に置き換えます。

    ```http
    https://login.microsoftonline.com/{tenantId}/v2.0/.well-known/openid-configuration
    ```
7. **App registration**で、事前に作成した*Azure Functions の認証イベント API* アプリ登録のアプリケーション ID (クライアント ID) を入力します。
8. Microsoft Entra管理センターで、次の手順を実行します。

    1. *Azure Functions認証イベント API*事前に作成したアプリの登録を選択します。
    2. **[証明書とシークレット**&gt;**クライアント シークレット**&gt;**新しいクライアント シークレット**を選択します。
    3. クライアント シークレットの説明を追加します。
    4. シークレットの有効期限を選択するか、カスタムの有効期間を指定します。
    5. [**] を選択し、[**] を追加します。
    6. クライアント アプリケーション コードで使用する **シークレットの値** を記録します。 このページからの移動後は、このシークレットの値は "二度と表示されません"。
9. Azure関数に戻り、**App registration** で、**Client シークレット**を入力します。
10. **[トークン ストア**] オプションの選択を解除します。
11. OpenID Connect ID プロバイダーを追加するには、[ **追加]** を選択します。

### 手順 6: アプリケーションをテストする

次の手順に従って、カスタム認証拡張機能をテストします。

1. 新しいプライベート ブラウザーを開き、次の URL に移動します。

    ```http
    https://<domainName>.ciamlogin.com/<tenant_id>/oauth2/v2.0/authorize?client_id=<client_id>&response_type=code+id_token&redirect_uri=https://jwt.ms&scope=openid&state=12345&nonce=12345
    ```

    - `<domainName>` を外部テナント名に置き換え、`<tenant-id>` を外部テナント ID に置き換えます。
    - `<client_id>` をユーザー フローに追加したアプリケーションの ID に置き換えます。
2. サインインすると、`https://jwt.ms` でデコードされたトークンが表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/custom-extension-email-otp-get-started"} -->
## ワンタイム パスコード送信イベント用にカスタム 電子メール プロバイダーを構成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-email-otp-get-started
- Service: identity-platform
- Article date: 2025-06-25
- Summary: ワンタイム パスコード送信イベントの種類を使用してカスタム メール プロバイダーを構成および設定する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、ワンタイム パスコード (OTP) 送信イベントの種類用のカスタム メール プロバイダーの構成と設定について説明します。 このイベントは、OTP メールがアクティブ化されたときにトリガーされ、REST API を呼び出して独自のメール プロバイダーを使用できるようになります。

このビデオでは、Azure Logic Appsに基づく Web API を使用して、Microsoft Entraカスタム認証拡張機能を使用して検証メールをカスタマイズする方法について説明します。 ロジック アプリを使用すると、ユーザーはビジュアル デザイナーを使用してワークフローを作成できます。

### 前提条件

- [カスタム認証拡張機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview)で説明されている概念の知識と理解。
- Azure サブスクリプション。 既存のAzure アカウントをお持ちでない場合は、[無料試用版](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)にサインアップするか、[Visual Studio サブスクリプション](https://visualstudio.microsoft.com/subscriptions/)特典を使用して、[アカウント](https://account.windowsazure.com/Home/Index)を作成します。
- Microsoft Entra ID [外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/quickstart-tenant-setup)。
- メール リレー サービス プロバイダー:

## [Azure Communication Services](#tab/azure-communication-services)
- Azure Communications Services リソース。 お持ちでない場合は、「クイック スタート: 新規または既存のリソース グループを使用して [Communication Services リソースを作成および管理](https://learn.microsoft.com/ja-jp/azure/communication-services/quickstarts/create-communication-resource?tabs=windows&pivots=platform-azp) する」で作成します。
    - プロビジョニングされたドメインで作成および準備が整った Azure Email Communication Services リソース。 お持ちでない場合は、「[Quickstart: Email Communication Service リソースの作成と管理](https://learn.microsoft.com/ja-jp/azure/communication-services/quickstarts/email/create-email-communication-resource?pivots=platform-azp)を参照し、Azure Communication Servicesと同じリソース グループを使用します。
    - メール ドメインに接続されたアクティブな Communication Services リソース。 [「クイック スタート: 確認済みメール ドメインを接続する方法](https://learn.microsoft.com/ja-jp/azure/communication-services/quickstarts/email/connect-email-communication-resource?branch=main&pivots=azure-portal)」を参照してください
    - (省略可能) [Azure Communication Services](https://learn.microsoft.com/ja-jp/azure/communication-services/quickstarts/email/send-email?tabs=windows%2Cconnection-string%2Csend-email-and-get-status-async%2Csync-client&pivots=platform-azportal) を使用して電子メールを送信し、Azure Communication Servicesを使用して目的の受信者に電子メールを送信するテストを行い、アプリケーションが電子メールを送信するための構成を確認します。

## [SendGrid](#tab/sendgrid)
- SendGrid アカウント。 まだアカウントをお持ちでない場合は、まず SendGrid アカウントを設定してください。 セットアップ手順については、「[SendGrid アカウントの作成](https://docs.sendgrid.com/for-developers/partners/microsoft-azure-2021#create-a-sendgrid-account)」セクションを参照してください[SendGrid と Azure](https://docs.sendgrid.com/for-developers/partners/microsoft-azure-2021#create-a-twilio-sendgrid-accountcreate-a-twilio-sendgrid-account) を使用して電子メールを送信する方法。

---

### 手順 1: Azure関数アプリを作成する

このセクションでは、Azure ポータルでAzure関数アプリを設定する方法について説明します。 関数 API は、メール プロバイダーへのゲートウェイです。 HTTP トリガー関数をホストし、関数の設定を構成するAzure関数アプリを作成します。

ヒント

この記事の手順は、開始するポータルによって若干異なる場合があります。

1. [Azure ポータル](https://portal.azure.com)に少なくとも [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer) および [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)としてサインインします。
2. Azure ポータル メニューまたは **Home** ページで、**リソースの作成**を選択します。
3. **関数アプリ**を検索して選択し、[**作成**] を選択します。
4. **[関数アプリの作成]** ページで、**[従量課金]** を選択し、**[選択]** を選択します。
5. [ **関数アプリの作成 (従量課金)]** ページの [ **基本** ] タブで、次の表に示すように設定を使用して関数アプリを作成します。

    | 設定 | 推奨値 | 説明 |
    | --- | --- | --- |
    | **Subscription** | 該当するサブスクリプション | この新しい関数アプリが作成されるサブスクリプション。 |
    | **[リソース グループ](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview)** | *myResourceGroup* | 前提条件の一部として、[Azure Communications Service](https://learn.microsoft.com/ja-jp/azure/communication-services/quickstarts/create-communication-resource?tabs=windows&pivots=platform-azp) および [Email Communication Service](https://learn.microsoft.com/ja-jp/azure/communication-services/quickstarts/email/create-email-communication-resource?pivots=platform-azp) リソースの設定に使用するリソース グループを選択します |
    | **関数アプリ名** | グローバルに一意の名前 | 新しい関数アプリを識別する名前。 有効な文字は、`a-z` (大文字と小文字の区別をしない)、`0-9`、および `-`です。 |
    | **コードまたはコンテナー イメージをデプロイする** | Code | コード ファイルまたは Docker コンテナーの発行オプション。 このチュートリアルでは、[ **コード**] を選択します。 |
    | **ランタイム スタック** | .NET | 好みのプログラミング言語。 このチュートリアルでは、**.NET** を選択します。 |
    | **バージョン** | 8 (LTS) インプロセス | .NET ランタイムのバージョン。 インプロセスとは、ポータルで関数を作成および変更できることを意味します。このガイドではこれが推奨されています。 |
    | **リージョン** | 優先リージョン | 自分の近く、または関数がアクセスできる他のサービスの近くの[リージョン](https://azure.microsoft.com/regions/)を選択します。 |
    | **オペレーティング システム** | Windows | オペレーティング システムは、ランタイム スタックの選択に基づいて、事前に自動的に選択されます。 |
6. [ **確認と作成** ] を選択してアプリ構成の選択を確認し、[ **作成**] を選択します。 デプロイには、数分かかります。
7. デプロイが完了したら、[ **リソースに移動** ] を選択して新しい関数アプリを表示します。

#### 1.1 HTTP トリガー関数を作成する

Azure関数アプリが作成されたら、HTTP トリガー関数を作成します。 HTTP トリガーでは、HTTP 要求で関数を呼び出すことができます。 Microsoft Entraカスタム認証拡張機能は、この HTTP トリガー関数にリンクされています。

1. **関数アプリ**内で、メニューから [**関数**] を選択します。
2. [ **関数の作成] を**選択します。
3. [ **関数の作成** ] ウィンドウの [ **テンプレートの選択**] で、 **HTTP トリガー** テンプレートを検索して選択します。 **次へ**を選択します。
4. [**テンプレートの詳細**] で、[*関数名]* プロパティに**「CustomAuthenticationExtensionsAPI**」と入力します。
5. **承認レベル**で、[**関数**] を選択します。
6. **を選択して**を作成します。

#### 1.2 関数を編集する

コードは、受信した JSON オブジェクトの読み取りから始まります。 Microsoft Entra IDは、[JSON オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-reference)を API に送信します。 この例では、メール アドレス (識別子) と OTP を読み取ります。 次に、コードは通信サービスに詳細を送信し、 [動的テンプレート](https://sendgrid.com/en-us/solutions/email-api/dynamic-email-templates)を使用して電子メールを送信します。

このハウツー ガイドでは、Azure Communication Servicesと SendGrid を使用した OTP 送信イベントについて説明します。 タブを使用して実装を選択します。

## [Azure Communication Services](#tab/azure-communication-services)
1. メニューから、[ **コード + テスト**] を選択します。
2. コード全体を次のコード スニペットに置き換えます。

    ```csharp
    using System.Dynamic;
    using System.Text.Json;
    using System.Text.Json.Nodes;
    using System.Text.Json.Serialization;
    using Azure.Communication.Email;
    using Microsoft.AspNetCore.Http;
    using Microsoft.AspNetCore.Http.HttpResults;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.Azure.Functions.Worker;
    using Microsoft.Extensions.Logging;
    
    namespace Company.AuthEvents.OnOtpSend.CustomEmailACS
    {
        public class CustomEmailACS
        {
            private readonly ILogger<CustomEmailACS> _logger;
    
            public CustomEmailACS(ILogger<CustomEmailACS> logger)
            {
                _logger = logger;
            }
    
            [Function("OnOtpSend_CustomEmailACS")]
            public async Task<IActionResult> RunAsync([HttpTrigger(AuthorizationLevel.Function, "post")] HttpRequest req)
            {
                _logger.LogInformation("C# HTTP trigger function processed a request.");
    
                // Get the request body
                string requestBody = await new StreamReader(req.Body).ReadToEndAsync();
                JsonNode jsonPayload = JsonNode.Parse(requestBody)!;
    
                // Get OTP and mail to
                string emailTo = jsonPayload["data"]!["otpContext"]!["identifier"]!.ToString();
                string otp = jsonPayload["data"]!["otpContext"]!["onetimecode"]!.ToString();
    
                // Send email
                await SendEmailAsync(emailTo, otp);
    
                // Prepare response
                ResponseObject responseData = new ResponseObject("microsoft.graph.OnOtpSendResponseData");
                responseData.Data.Actions = new List<ResponseAction>() { new ResponseAction(
                    "microsoft.graph.OtpSend.continueWithDefaultBehavior") };
    
                return new OkObjectResult(responseData);
            }
    
            private async Task SendEmailAsync(string emailTo, string code)
            {
                // Get app settings
                var connectionString = Environment.GetEnvironmentVariable("mail_connectionString");
                var sender = Environment.GetEnvironmentVariable("mail_sender");
                var subject = Environment.GetEnvironmentVariable("mail_subject");
    
                try
                {
                    if (!string.IsNullOrEmpty(connectionString))
                    {
                        var emailClient = new EmailClient(connectionString);
                        var body = EmailTemplate.GenerateBody(code);
    
                        _logger.LogInformation($"Sending OTP to {emailTo}");
    
                        EmailSendOperation emailSendOperation = await emailClient.SendAsync(
                        Azure.WaitUntil.Started,
                        sender,
                        emailTo,
                        subject,
                        body);
                    }
                }
                catch (System.Exception ex)
                {
                    _logger.LogError(ex.Message);
                }
            }
        }
    
        public class ResponseObject
        {
            [JsonPropertyName("data")]
            public Data Data { get; set; }
    
            public ResponseObject(string dataType)
            {
                Data = new Data(dataType);
            }
        }
    
        public class Data
        {
            [JsonPropertyName("@odata.type")]
            public string DataType { get; set; }
            [JsonPropertyName("actions")]
            public List<ResponseAction> Actions { get; set; }
    
            public Data(string dataType)
            {
                DataType = dataType;
            }
        }
    
        public class ResponseAction
        {
            [JsonPropertyName("@odata.type")]
            public string DataType { get; set; }
    
            public ResponseAction(string dataType)
            {
                DataType = dataType;
            }
        }
    
        public class EmailTemplate
        {
            public static string GenerateBody(string oneTimeCode)
            {
                return @$"<html><body>
                <div style='background-color: #1F6402!important; padding: 15px'>
                    <table>
                    <tbody>
                        <tr>
                            <td colspan='2' style='padding: 0px;font-family: "Segoe UI Semibold", "Segoe UI Bold", "Segoe UI", "Helvetica Neue Medium", Arial, sans-serif;font-size: 17px;color: white;'>Woodgrove Groceries live demo</td>
                        </tr>
                        <tr>
                            <td colspan='2' style='padding: 15px 0px 0px;font-family: "Segoe UI Light", "Segoe UI", "Helvetica Neue Medium", Arial, sans-serif;font-size: 35px;color: white;'>Your Woodgrove verification code</td>
                        </tr>
                        <tr>
                            <td colspan='2' style='padding: 25px 0px 0px;font-family: "Segoe UI", Tahoma, Verdana, Arial, sans-serif;font-size: 14px;color: white;'> To access <span style='font-family: "Segoe UI Bold", "Segoe UI Semibold", "Segoe UI", "Helvetica Neue Medium", Arial, sans-serif; font-size: 14px; font-weight: bold; color: white;'>Woodgrove Groceries</span>'s app, please copy and enter the code below into the sign-up or sign-in page. This code is valid for 30 minutes. </td>
                        </tr>
                        <tr>
                            <td colspan='2' style='padding: 25px 0px 0px;font-family: "Segoe UI", Tahoma, Verdana, Arial, sans-serif;font-size: 14px;color: white;'>Your account verification code:</td>
                        </tr>
                        <tr>
                            <td style='padding: 0px;font-family: "Segoe UI Bold", "Segoe UI Semibold", "Segoe UI", "Helvetica Neue Medium", Arial, sans-serif;font-size: 25px;font-weight: bold;color: white;padding-top: 5px;'>
                            {oneTimeCode}</td>
                            <td rowspan='3' style='text-align: center;'>
                                <img src='https://woodgrovedemo.com/custom-email/shopping.png' style='border-radius: 50%; width: 100px'>
                            </td>
                        </tr>
                        <tr>
                            <td style='padding: 25px 0px 0px;font-family: "Segoe UI", Tahoma, Verdana, Arial, sans-serif;font-size: 14px;color: white;'> If you didn't request a code, you can ignore this email. </td>
                        </tr>
                        <tr>
                            <td style='padding: 25px 0px 0px;font-family: "Segoe UI", Tahoma, Verdana, Arial, sans-serif;font-size: 14px;color: white;'> Best regards, </td>
                        </tr>
                        <tr>
                            <td>
                                <img src='https://woodgrovedemo.com/Company-branding/headerlogo.png' height='20'>
                            </td>
                            <td style='font-family: "Segoe UI", Tahoma, Verdana, Arial, sans-serif;font-size: 14px;color: white; text-align: center;'>
                                <a href='https://woodgrovedemo.com/Privacy' style='color: white; text-decoration: none;'>Privacy Statement</a>
                            </td>
                        </tr>
                    </tbody>
                    </table>
                </div>
                </body></html>";
            }
        }
    }
    ```
3. [ **関数 URL の取得] を**選択し、 **関数キー** の URL をコピーします。この URL は、今後使用され、 `{Function_Url}`と呼ばれます。 関数を終了します。

## [SendGrid](#tab/sendgrid)
1. メニューから、[ **コード + テスト**] を選択します。
2. コード全体を次のコード スニペットに置き換えます。

    ```csharp
    using System.Dynamic;
    using System.Text.Json;
    using System.Text.Json.Nodes;
    using System.Text.Json.Serialization;
    using Azure.Communication.Email;
    using Microsoft.AspNetCore.Http;
    using Microsoft.AspNetCore.Http.HttpResults;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.Azure.Functions.Worker;
    using Microsoft.Extensions.Logging;
    
    namespace Company.AuthEvents.OnOtpSend.CustomEmailSendGrid
    {
        public class CustomEmailSendGrid
        {
            private readonly ILogger<CustomEmailSendGrid> _logger;
    
            public CustomEmailSendGrid(ILogger<CustomEmailSendGrid> logger)
            {
                _logger = logger;
            }
    
            [Function("OnOtpSend_CustomEmailSendGrid")]
            public async Task<IActionResult> RunAsync([HttpTrigger(AuthorizationLevel.Function, "post")] HttpRequest req)
            {
                _logger.LogInformation("C# HTTP trigger function processed a request.");
    
                // Get the request body
                string requestBody = await new StreamReader(req.Body).ReadToEndAsync();
                JsonNode jsonPayload = JsonNode.Parse(requestBody)!;
    
                // Get OTP and mail to
                string emailTo = jsonPayload["data"]!["otpContext"]!["identifier"]!.ToString();
                string otp = jsonPayload["data"]!["otpContext"]!["onetimecode"]!.ToString();
    
                // Send email
                await SendEmailAsync(emailTo, otp);
    
                // Prepare response
                ResponseObject responseData = new ResponseObject("microsoft.graph.OnOtpSendResponseData");
                responseData.Data.Actions = new List<ResponseAction>() { new ResponseAction(
                    "microsoft.graph.OtpSend.continueWithDefaultBehavior") };
    
                return new OkObjectResult(responseData);
            }
    
            private async Task SendEmailAsync(string emailTo, string code)
            {
                // Get app settings
                var apiKey = Environment.GetEnvironmentVariable("mail_sendgridKey");
                var sender = Environment.GetEnvironmentVariable("mail_sender");
                var senderName = Environment.GetEnvironmentVariable("mail_senderName");
                var template = Environment.GetEnvironmentVariable("mail_template");
    
                _logger.LogInformation($"Sending OTP to {emailTo}");
    
                try
                {
                    if (!string.IsNullOrEmpty(apiKey))
                    {
                        var client = new HttpClient();
                        var request = new HttpRequestMessage(HttpMethod.Post, "https://api.sendgrid.com/v3/mail/send");
                        request.Headers.Add("Authorization", $"Bearer {apiKey}");
    
                        SendGridMessage msg = new SendGridMessage()
                        {
                            template_id = template,
                            from = new Person { email = sender, name = senderName },
                            personalizations = new List<Personalization> {
    
                    new Personalization(emailTo, code)
                    }
                        };
    
                        request.Content = new StringContent(msg.ToString(), null, "application/json");
    
                        var response = await client.SendAsync(request);
                        _logger.LogInformation($"Sendgrid response: {response.StatusCode}");
                        response.EnsureSuccessStatusCode();
                        _logger.LogInformation(await response.Content.ReadAsStringAsync());
                    }
                }
                catch (System.Exception ex)
                {
                    _logger.LogError(ex.Message);
                }
            }
        }
    
        public class ResponseObject
        {
            [JsonPropertyName("data")]
            public Data Data { get; set; }
    
            public ResponseObject(string dataType)
            {
                Data = new Data(dataType);
            }
        }
    
        public class Data
        {
            [JsonPropertyName("@odata.type")]
            public string DataType { get; set; }
            [JsonPropertyName("actions")]
            public List<ResponseAction> Actions { get; set; }
    
            public Data(string dataType)
            {
                DataType = dataType;
            }
        }
    
        public class ResponseAction
        {
            [JsonPropertyName("@odata.type")]
            public string DataType { get; set; }
    
            public ResponseAction(string dataType)
            {
                DataType = dataType;
            }
        }
    
        public class EmailTemplate
        {
            public static string GenerateBody(string oneTimeCode)
            {
                return @$"<html><body>
                <div style='background-color: #1F6402!important; padding: 15px'>
                    <table>
                    <tbody>
                        <tr>
                            <td colspan='2' style='padding: 0px;font-family: "Segoe UI Semibold", "Segoe UI Bold", "Segoe UI", "Helvetica Neue Medium", Arial, sans-serif;font-size: 17px;color: white;'>Woodgrove Groceries live demo</td>
                        </tr>
                        <tr>
                            <td colspan='2' style='padding: 15px 0px 0px;font-family: "Segoe UI Light", "Segoe UI", "Helvetica Neue Medium", Arial, sans-serif;font-size: 35px;color: white;'>Your Woodgrove verification code</td>
                        </tr>
                        <tr>
                            <td colspan='2' style='padding: 25px 0px 0px;font-family: "Segoe UI", Tahoma, Verdana, Arial, sans-serif;font-size: 14px;color: white;'> To access <span style='font-family: "Segoe UI Bold", "Segoe UI Semibold", "Segoe UI", "Helvetica Neue Medium", Arial, sans-serif; font-size: 14px; font-weight: bold; color: white;'>Woodgrove Groceries</span>'s app, please copy and enter the code below into the sign-up or sign-in page. This code is valid for 30 minutes. </td>
                        </tr>
                        <tr>
                            <td colspan='2' style='padding: 25px 0px 0px;font-family: "Segoe UI", Tahoma, Verdana, Arial, sans-serif;font-size: 14px;color: white;'>Your account verification code:</td>
                        </tr>
                        <tr>
                            <td style='padding: 0px;font-family: "Segoe UI Bold", "Segoe UI Semibold", "Segoe UI", "Helvetica Neue Medium", Arial, sans-serif;font-size: 25px;font-weight: bold;color: white;padding-top: 5px;'>
                            {oneTimeCode}</td>
                            <td rowspan='3' style='text-align: center;'>
                                <img src='https://woodgrovedemo.com/custom-email/shopping.png' style='border-radius: 50%; width: 100px'>
                            </td>
                        </tr>
                        <tr>
                            <td style='padding: 25px 0px 0px;font-family: "Segoe UI", Tahoma, Verdana, Arial, sans-serif;font-size: 14px;color: white;'> If you didn't request a code, you can ignore this email. </td>
                        </tr>
                        <tr>
                            <td style='padding: 25px 0px 0px;font-family: "Segoe UI", Tahoma, Verdana, Arial, sans-serif;font-size: 14px;color: white;'> Best regards, </td>
                        </tr>
                        <tr>
                            <td>
                                <img src='https://woodgrovedemo.com/Company-branding/headerlogo.png' height='20'>
                            </td>
                            <td style='font-family: "Segoe UI", Tahoma, Verdana, Arial, sans-serif;font-size: 14px;color: white; text-align: center;'>
                                <a href='https://woodgrovedemo.com/Privacy' style='color: white; text-decoration: none;'>Privacy Statement</a>
                            </td>
                        </tr>
                    </tbody>
                    </table>
                </div>
                </body></html>";
            }
        }
    
        /********* SendGrid data ********/
        public class SendGridMessage
        {
            public List<Personalization> personalizations { get; set; }
            public string template_id { get; set; }
            public Person from { get; set; }
    
            public override string ToString()
            {
                return JsonSerializer.Serialize(this, new JsonSerializerOptions { WriteIndented = true });
            }
        }
    
        public class Personalization
        {
            public Personalization(string to, string otp)
            {
                this.to = new List<Person>() { new Person() { email = to } };
                this.dynamic_template_data = new DynamicTemplateData() { otp = otp };
            }
    
            public List<Person> to { get; set; }
            public DynamicTemplateData dynamic_template_data { get; set; }
        }
    
        public class DynamicTemplateData
        {
            public string otp { get; set; }
        }
    
        public class Person
        {
            public string email { get; set; }
    
            [JsonIgnore(Condition = JsonIgnoreCondition.WhenWritingNull)]
            public string name { get; set; }
        }
    
        public class OTPCodeTemplateData
        {
            [JsonPropertyName("otp")]
            public string OTPCode { get; set; }
    
        }
    }
    ```
3. [ **関数 URL の取得] を**選択し、 **関数キー** の URL をコピーします。この URL は、今後使用され、 `{Function_Url}`と呼ばれます。 関数を終了します。

---

### 手順 2: Azure関数に接続文字列を追加する

接続文字列を使用すると、関数アプリはメール リレー サービスに接続して認証できるようになります。 Azure Communication Servicesと SendGrid の両方で、これらの接続文字列を環境変数としてAzure関数アプリに追加します。

## [Azure Communication Services](#tab/azure-communication-services)
#### 2.1: Azure Communication Services リソースから接続文字列とサービス エンドポイントを抽出する

Communication Services の接続文字列とサービス エンドポイントには、Azure ポータルからアクセスすることも、Azure Resource Manager API を使用してプログラムでアクセスすることもできます。

1. [Azure ポータルの **Home** ページから](https://portal.azure.com/#home)ポータル メニューを開き、**すべてのリソース** を検索して選択します。
2. この記事のPrerequisitesの一部として作成されたAzure Communications Service を検索して選択します。
3. 左側のウィンドウで、[ **設定]** ドロップダウンを選択し、[ **キー**] を選択します。
4. **エンドポイント**をコピーし、**主キーからキー** **と**接続文字列の値をコピー**します**。

    [Image: エンドポイントとキーの場所を示す Azure Communications Service Keys ページのスクリーンショット。]

#### 2.2: 接続文字列を Azure 関数に追加する

1. Azure関数アプリの作成で作成したAzure関数に戻ります。
2. 関数アプリの **[概要**] ページで、左側のメニューで **[設定]** を選択&gt;**Environment 変数**で次のアプリ設定を追加します。 すべての設定が追加されたら、[ **適用**]、[ **確認**] の順に選択します。

    | 設定 | 値 (例) | 説明 |
    | --- | --- | --- |
    | **mail\_connectionString** | `https://ciamotpcommsrvc.unitedstates.communication.azure.com/:accesskey=` | Azure Communication Services エンドポイント |
    | **mail\_sender** | from.email@myemailprovider.com | メール送信元アドレス。 |
    | **メール件名** | アカウント確認コード | 電子メールの件名。 |

## [SendGrid](#tab/sendgrid)
#### 2.1: 接続文字列を Azure 関数に追加する

1. Azure 関数アプリを作成するで作成した Azure 関数に戻ります。
2. 関数アプリの **[概要**] ページで、左側のメニューで **[設定]** を選択&gt;**Environment 変数**で次のアプリ設定を追加します。 すべての設定が追加されたら、[ **適用**]、[ **確認**] の順に選択します。

    | 設定 | 値 (例) | 説明 |
    | --- | --- | --- |
    | **mail\_sendgridKey** | SG.12a3456789... | SendGrid API キー。 |
    | **mail\_sender** | from.email@myemailprovider.com | メール送信元アドレス。 |
    | **mail\_senderName** | Contoso | メール送信元の名前。 |
    | **mail\_template** | d-01234567.... | SendGrid の動的テンプレート ID。 |

---

### 手順 3: カスタム認証拡張機能を登録する

この手順では、Azure関数の呼び出しに使用Microsoft Entra IDカスタム認証拡張機能を構成します。 カスタム認証拡張機能には、REST API エンドポイントに関する情報、REST API から解析するクレーム、REST API に対する認証方法が含まれています。 Azure ポータルまたはMicrosoft Graphを使用して、カスタム認証拡張機能を認証するアプリケーションを Azure 関数に登録します。

## [Azure portal](#tab/azure-portal)
#### カスタム認証拡張機能を登録する

1. [Azure ポータル](https://portal.azure.com)に少なくとも [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer) および [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)としてサインインします。
2. **Microsoft Entra ID** を検索して選択し、 **Enterprise アプリケーション**を選択します。
3. [ **カスタム認証拡張機能**] を選択し、[ **カスタム拡張機能の作成**] を選択します。
4. **[基本**] で、**EmailOtpSend** イベントの種類を選択し、[**次へ**] を選択します。

    電子メール OTP 送信イベントが強調表示された Azure ポータルのスクリーンショット
5. [ **エンドポイント構成** ] タブで、次のプロパティを入力し、[ **次へ** ] を選択して続行します。

    - **名前** - カスタム認証拡張機能の名前。 たとえば、 *電子メール OTP 送信*です。
    - **Target Url** - Azure関数 URL の `{Function_Url}`。 Azure Function アプリの **Overview** ページに移動し、作成した関数を選択します。 [関数の **概要** ] ページで、[ **関数 URL の取得** ] を選択し、コピー アイコンを使用して **customauthenticationextension\_extension (システム キー)** URL をコピーします。
    - **説明** - カスタム認証拡張機能の説明。
6. [ **API 認証** ] タブで、[ **新しいアプリ登録の作成** ] オプションを選択して、 *関数アプリ*を表すアプリ登録を作成します。
7. アプリに **Azure Functions 認証イベント API** などの名前を付け、**Next** を選択します。
8. [アプリケーション] タブ **で** 、カスタム認証拡張機能に関連付けるアプリケーションを選択します。 **次へ**を選択します。 チェックボックスをオンにして、テナント全体に適用することもできます。 **[次へ]** を選択して続行します。
9. [ **確認** ] タブで、カスタム認証拡張機能の詳細が正しいことを確認します。 Azure Function アプリでの認証を構成するために、**API 認証** の下にある **App ID** に注意してください。 **を選択して**を作成します。

## [Microsoft Graph](#tab/microsoft-graph)
#### 3.1 Graph エクスプローラーにアプリケーションを登録する

1. カスタム認証拡張機能を管理するテナントがホーム テナントであるアカウントを使用して [Graph Explorer](https://aka.ms/ge) にサインインします。 アカウントには、テナント内でアプリケーションの登録を作成および管理する特権が必要です。
2. 次の要求を実行します。

    ```http
    POST https://graph.microsoft.com/v1.0/applications
    Content-type: application/json
    
    {
        "displayName": "authenticationeventsAPI"
    }
    ```
3. 応答から、新しく作成されたアプリ登録の **ID** と **appId** の値を記録します。 この記事では、これらの値はそれぞれ `{authenticationeventsAPI_ObjectId}` および `{authenticationeventsAPI_AppId}` として参照されています。
4. **authenticationeventsAPI** アプリ登録用のサービス プリンシパルをテナントに作成します。
5. Graph エクスプローラーで、次の要求を実行します。 `{authenticationeventsAPI_AppId}`を、前の手順で記録した **appId** の値に置き換えます。

    ```http
    POST https://graph.microsoft.com/v1.0/servicePrincipals
    Content-type: application/json
    
    {
        "appId": "{authenticationeventsAPI_AppId}"
    }
    ```

#### 3.2 アプリ ID の URI、アクセス トークンのバージョン、必要なリソース アクセスを設定する

新しく作成したアプリケーションを更新して、アプリケーション ID URI の値、アクセス トークンのバージョン、必要なリソース アクセスを設定します。

1. Graph エクスプローラーで、次の要求を実行します。

- *identifierUris* プロパティでアプリケーション ID URI 値を設定します。 `{Function_Url_Hostname}` を、前に記録した `{Function_Url}` のホスト名に置き換えます。
- `{authenticationeventsAPI_AppId}` の値を、先ほど記録した **appId** に設定します。
- 値の例が `api://authenticationeventsAPI.azurewebsites.net/00001111-aaaa-2222-bbbb-3333cccc4444`。 この値をメモしておきます。 この記事の後半で `{functionApp_IdentifierUri}` の代わりに必要になります。

    ```http
    PATCH https://graph.microsoft.com/v1.0/applications/{authenticationeventsAPI_ObjectId}
    Content-type: application/json
    {
        "identifierUris": [
            "api://{Function_Url_Hostname}/{authenticationeventsAPI_AppId}"
        ],    
        "api": {
            "requestedAccessTokenVersion": 2,
            "acceptMappedClaims": null,
            "knownClientApplications": [],
            "oauth2PermissionScopes": [],
            "preAuthorizedApplications": []
        },
        "requiredResourceAccess": [
            {
                "resourceAppId": "00000003-0000-0000-c000-000000000000",
                "resourceAccess": [
                    {
                        "id": "214e810f-fda8-4fd7-a475-29461495eb00",
                        "type": "Role"
                    }
                ]
            }
        ]
    }
    ```

#### 3.3 カスタム認証拡張機能を登録する

次に、カスタム認証拡張機能を登録し、それを Azure 関数のアプリ登録と関連付け、Azure関数エンドポイント `{Function_Url}`。

1. Graph エクスプローラーで、次の要求を実行します。 `{Function_Url}`をAzure関数アプリのホスト名に置き換えます。 `{functionApp_IdentifierUri}` は、前のステップで使った identifierUri に置き換えます。

    - *CustomAuthenticationExtension.ReadWrite.All* の委任されたアクセス許可が必要です。

    ```http
    POST https://graph.microsoft.com/beta/identity/customAuthenticationExtensions
    Content-type: application/json
    
    {
        "@odata.type": "#microsoft.graph.OnOtpSendCustomExtension",
        "displayName": "onEmailOtpSendCustomExtension",
        "description": "Use an external Email provider to send OTP Codes.",
        "authenticationConfiguration": {
            "@odata.type": "#microsoft.graph.azureAdTokenAuthentication",
            "resourceId": "{functionApp_IdentifierUri}"
        },
        "endpointConfiguration": {
            "@odata.type": "#microsoft.graph.httpRequestEndpoint",
            "targetUrl": "{Function_Url}"
        }
    }
    ```
2. 作成したカスタム電子メール OTP プロバイダー オブジェクトの **ID** 値を記録します。これは、この記事の後半で `{customExtensionObjectId}`の代わりに使用します。

#### 3.4 カスタム メール プロバイダーをアプリに割り当てる

カスタム メールを使用するには、アプリケーションにカスタム メール プロバイダーを割り当てる必要があります。 カスタム 電子メール プロバイダーは、 **OTP 送信** イベント リスナーで構成されたカスタム認証拡張機能に依存します。

*My Test アプリケーション*をカスタム認証拡張機能に接続するには、次の手順に従います。

1. カスタム認証拡張機能を管理するテナントがホーム テナントであるアカウントを使用して [Graph Explorer](https://aka.ms/ge) にサインインします。
2. 次の要求を実行します。 `{App_to_sendotp_ID}` を、前に記録した "マイ テスト アプリケーション" のアプリ ID に置き換えます。`{customExtensionObjectId}` を、先ほど記録したカスタム認証拡張機能の ID に置き換えます。

    - *EventListener.ReadWrite.All* の委任されたアクセス許可が必要です。

    ```json
    POST https://graph.microsoft.com/beta/identity/authenticationEventListeners
    Content-type: application/json
    
    {
        "@odata.type": "#microsoft.graph.onEmailOtpSendListener",
        "conditions": {
            "applications": {
                "includeAllApplications": false,
                "includeApplications": [
                    {
                        "appId": "{App_to_sendotp_ID}"
                    }
                ]
            }
        },
        "priority": 500,
        "handler": {
            "@odata.type": "#microsoft.graph.onOtpSendCustomExtensionHandler",
            "customExtension": {
                "id": "{customExtensionObjectId}"
            }
        }
    }
    ```
3. 作成されたリスナー オブジェクトの **ID** 値を記録します。これは、この記事の後半で `{customListenerObjectId}`の代わりに使用します。

---

#### 管理者の同意の付与

カスタム認証拡張機能が作成されたら、ポータルの **App registrations** でアプリケーションを開き、**API アクセス許可** を選択します。

**[API のアクセス許可**] ページで、[**YourTenant] ボタンに管理者の同意を付与**して登録済みアプリに管理者の同意を与えます。これにより、カスタム認証拡張機能が API に対して認証できるようになります。 カスタム認証拡張機能では、`client_credentials` を使用して、`Receive custom authentication extension HTTP requests` アクセス許可を使用してAzure関数アプリに対する認証を行います。

アクセス許可を付与する方法を示すスクリーンショットを次に示します。

[Image: Azure ポータルのスクリーンショットと、管理者の同意を付与する方法。]

### 手順 4: テストに使用する OpenID Connect アプリを構成する

トークンを取得してカスタム認証拡張機能をテストするには、https://jwt.ms アプリを使用できます。 これは Microsoft 所有の Web アプリケーションであり、トークンのデコードされた内容を表示します (トークンの内容がお使いのブラウザーの外に出ることはありません)。

jwt.ms Web アプリケーションを登録するには、次 **の手順に** 従います。

#### 4.1 テスト Web アプリケーションを登録する

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)としてサインインします。
2. **Entra ID**&gt;**App registrations** に移動します。
3. **[新規登録]** を選択します。
4. アプリケーションの **名前** を入力します。 たとえば、 **My Test アプリケーション**です。
5. **[サポートされているアカウントの種類]** で、 **[この組織のディレクトリ内のアカウントのみ]** を選択します。
6. [**リダイレクト URI**] の [**プラットフォームの選択**] ドロップダウンで [**Web**] を選択し、[URL] テキスト ボックスに「`https://jwt.ms`」と入力します。
7. [ **登録** ] を選択してアプリの登録を完了します。
8. アプリの登録の [ **概要**] で、アプリケーション **(クライアント) ID を**コピーします。これは後で使用され、 `{App_to_sendotp_ID}`と呼ばれます。 Microsoft Graphでは、**appId** プロパティによって参照されます。

次のスクリーンショットは、"マイ テスト アプリケーション" の登録方法を示したものです。

[Image: サポートされているアカウントの種類とリダイレクト URI を選択する方法を示すスクリーンショット。]

#### 4.1 アプリケーション ID を取得する

アプリの登録の [ **概要**] で、 **アプリケーション (クライアント) ID をコピーします**。 後の手順では、アプリ ID を `{App_to_sendotp_ID}` として参照します。 Microsoft Graphでは、**appId** プロパティによって参照されます。

#### 4.2 暗黙的なフローを有効にする

**jwt.ms** テスト アプリケーションでは、暗黙的なフローが使用されます。 *マイ テスト アプリケーション*の登録で暗黙的なフローを有効にします。

重要

Microsoft では、使用可能な最も安全な認証フローを使用することをお勧めします。 この手順のテストに使用される認証フローでは、アプリケーションに対する高度な信頼が必要であり、他のフローには存在しないリスクを伴います。 この方法は、運用アプリに対するユーザーの認証には使用しないでください ([詳細を参照](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow))。

1. **[管理]** で、 **[認証]** を選択します。
2. [ **暗黙的な許可とハイブリッド フロー**] で、 **ID トークン (暗黙的およびハイブリッド フローに使用)** チェック ボックスをオンにします。
3. **保存**を選びます。

### 手順 5: Azure関数を保護する

カスタム認証拡張機能Microsoft Entraサーバー間フローを使用して、HTTP `Authorization` ヘッダーでAzure関数に送信されるアクセス トークンを取得します。 特に運用環境で関数をAzureに発行する場合は、承認ヘッダーで送信されたトークンを検証する必要があります。

Azure関数を保護するには、次の手順に従って、Microsoft Entra認証を統合し、受信トークンを *Azure Functions 認証イベント API* アプリケーション登録と検証します。

注

Azure関数アプリがカスタム認証拡張機能が登録されているテナントとは異なる Azure テナントでホストされている場合は、OpenID Connect アイデンティティプロバイダー の手順に進んでください。

1. [Azure ポータル](https://portal.azure.com)にサインインします。
2. 以前公開した関数アプリに移動して選択します。
3. 左側のメニューで [ **認証** ] を選択します。
4. [ **ID プロバイダーの追加] を選択します**。
5. ドロップダウン メニューから、ID プロバイダーとして **Microsoft** を選択します。
6. **App registration**-&gt;**App registration type** で、**このディレクトリ内の既存のアプリ登録を選択します**。カスタム電子メール プロバイダーを登録するときに事前に作成した*Azure Functions認証イベント API* アプリの登録を選択します。
7. アプリの **クライアント シークレット** の有効期限を追加します。
8. [ **認証されていない要求**] で、ID プロバイダーとして **[HTTP 401 Unauthorized** ] を選択します。
9. **[トークン ストア**] オプションの選択を解除します。
10. **Add** を選択して、Azure関数に認証を追加します。

[Image: 関数アプリに認証を追加する方法を示すスクリーンショット。]

#### 5.1 OpenID Connect ID プロバイダーの使用

Microsoft ID プロバイダーを構成した場合は、この手順をスキップします。 それ以外の場合、Azure関数が、カスタム認証拡張機能が登録されているテナントとは異なるテナントでホストされている場合は、次の手順に従って関数を保護します。

1. [Azure ポータル](https://portal.azure.com)にサインインし、前に発行した関数アプリに移動して選択します。
2. 左側のウィンドウで [ **認証** ] を選択します。
3. [ **ID プロバイダーの追加] を選択します**。
4. ID プロバイダーとして **OpenID Connect** を選択します。
5. Contoso Microsoft Entra ID。
6. [ **メタデータ] エントリ**で、ドキュメント URL に次の **URL を入力します**。 `{tenantId}`をMicrosoft Entraテナント ID に置き換え、`{tenantname}` を 'onmicrosoft.com' を持たないテナントの名前に置き換えます。

    ```http
    https://{tenantname}.ciamlogin.com/{tenantId}/v2.0/.well-known/openid-configuration
    ```
7. **App registration** で、*Azure Functions 認証イベント API* アプリ登録 以前に作成したアプリケーション ID (クライアント ID) を入力。
8. Microsoft Entra管理センターで、次の手順を実行します。

    1. *Azure Functions認証イベント API*のアプリ登録を、以前に作成したものから選択します。
    2. **[証明書とシークレット**&gt;**クライアント シークレット**&gt;**新しいクライアント シークレット**を選択します。
    3. クライアント シークレットの説明を追加します。
    4. シークレットの有効期限を選択するか、カスタムの有効期間を指定します。
    5. [**] を選択し、[**] を追加します。
    6. クライアント アプリケーション コードで使用する **シークレットの値** を記録します。 このページからの移動後は、このシークレットの値は "二度と表示されません"。
9. Azure関数に戻り、**App registration** で、**Client シークレット**を入力します。
10. **[トークン ストア**] オプションの選択を解除します。
11. OpenID Connect ID プロバイダーを追加するには、[ **追加]** を選択します。

### ステップ 6: アプリケーションをテストする

カスタム メール プロバイダーをテストするには、次の手順に従います。

1. 新しいプライベート ブラウザーを開き、次の URL に移動してサインインします。

    ```http
    https://{tenantname}.ciamlogin.com/{tenant-id}/oauth2/v2.0/authorize?client_id={App_to_sendotp_ID}&response_type=id_token&redirect_uri=https://jwt.ms&scope=openid&state=12345&nonce=12345
    ```
2. `{tenant-id}` は、テナント ID、テナント名、または検証済みドメイン名の 1 つに置き換えます。 たとえば、「 `contoso.onmicrosoft.com` 」のように入力します。
3. `{tenantname}` を "onmicrosoft.com" を除いたテナントの名前に置き換えます。
4. `{App_to_sendotp_ID}`を My Test アプリケーション登録 ID に置き換えます。
5. [電子メール ワンタイム パスコード アカウント](https://learn.microsoft.com/ja-jp/entra/external-id/one-time-passcode)を使用してサインインしていることを確認します。 次に、[ **コードの送信**] を選択します。 登録されたメール アドレスに送信されるコードが、上記で登録したカスタム プロバイダーを使用していることを確認します。

### 手順 7: Microsoft プロバイダーにフォールバックする

拡張機能 API 内でエラーが発生した場合、既定では Entra ID はユーザーに OTP を送信しません。 代わりに、エラー時の動作を Microsoft プロバイダーにフォールバックするように設定することができます。

これを有効にするには、次の要求を実行します。 `{customListenerObjectId}` を、先ほど記録したカスタム認証リスナー ID に置き換えます。

- *EventListener.ReadWrite.All* の委任されたアクセス許可が必要です。

```json
PATCH https://graph.microsoft.com/beta/identity/authenticationEventListeners/{customListenerOjectId}

{
    "@odata.type": "#microsoft.graph.onEmailOtpSendListener",
    "handler": {
        "@odata.type": "#microsoft.graph.onOtpSendCustomExtensionHandler",
        "configuration": {
            "behaviorOnError": {
                "@odata.type": "#microsoft.graph.fallbackToMicrosoftProviderOnError"
            }
        }
    }
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/custom-extension-email-otp-send-data"} -->
## emailOtpSend イベントからデータを取得して返す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-email-otp-send-data
- Service: identity-platform
- Article date: 2025-05-20
- Summary: 外部 ID 顧客構成の emailOtpSend イベントを呼び出すカスタム認証拡張機能のリファレンス ドキュメント。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

[電子メール ワンタイム パスコード (OTP) 送信](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-email-otp-get-started)イベントのカスタム 電子メール プロバイダーを構成するには、カスタム認証拡張機能を作成し、ユーザー フロー内の特定のポイントで呼び出します。 **emailOtpSend** イベントがアクティブになると、Microsoft Entra は、所有する指定された REST API にワンタイム パスコードを送信します。

その後、REST API は、選択したメール プロバイダー (Azure Communication Service や SendGrid など) を使用して、アドレス、電子メールの件名などのカスタム電子メール テンプレートでワンタイム パスコードを送信します。 この記事では、emailOtpSend イベントの REST API スキーマについて説明します。

### 外部 REST API への要求

Microsoft Entra ID で定義したカスタム認証拡張機能は、JSON ペイロードを使用して REST API への HTTP 呼び出しを行います。 JSON ペイロードには、ユーザーのメール アドレスとワンタイム パスコードが含まれています。 要求には、認証コンテキスト属性と、ユーザーがサインインしようとしているアプリケーションに関する情報も含まれます。

次の HTTP 要求は、Microsoft Entra が REST API を呼び出す方法を示しています。 この HTTP 要求は、Microsoft Entra からの要求をシミュレートすることで、REST API をデバッグするために使用できます。

```http
POST https://example.azureWebsites.net/api/functionName

Content-Type: application/json

[Request payload]
```

次の JSON ドキュメントでは、要求ペイロードの例を示します。

```json
{
    "type": "microsoft.graph.authenticationEvent.emailOtpSend",
    "source": "/tenants/ffff5f5f-aa6a-bb7b-cc8c-dddddd9d9d9d/applications/bbbbbbbb-cccc-dddd-2222-333333333333",
    "data": {
        "@odata.type": "microsoft.graph.onOtpSendCalloutData",
        "otpContext": {
            "identifier": "someone@example.com",
            "oneTimeCode": "12345678"
        },
        "tenantId": "aaaabbbb-0000-cccc-1111-dddd2222eeee",
        "authenticationEventListenerId": "00001111-aaaa-2222-bbbb-3333cccc4444",
        "customAuthenticationExtensionId": "11112222-bbbb-3333-cccc-4444dddd5555",
        "authenticationContext": {
            "correlationId": "aaaa0000-bb11-2222-33cc-444444dddddd",
            "client": {
                "ip": "192.168.0.0",
                "locale": "en-us",
                "market": "en-us"
            },
            "protocol": "OAUTH2.0",
            "requestType": "signUp",
            "clientServicePrincipal": {
                "id": "aaaaaaaa-bbbb-cccc-1111-222222222222",
                "appId": "00001111-aaaa-2222-bbbb-3333cccc4444",
                "appDisplayName": "My Test application",
                "displayName": "My Test application"
            },
            "resourceServicePrincipal": {
                "id": "aaaaaaaa-bbbb-cccc-1111-222222222222",
                "appId": "00001111-aaaa-2222-bbbb-3333cccc4444",
                "appDisplayName": "My Test application",
                "displayName": "My Test application"
            }
        }
    }
}
```

#### 外部 REST API からの応答

Microsoft Entra ID は、次の HTTP で REST API 応答を受け取ります。

```http
HTTP/1.1 200 OK

Content-Type: application/json

[JSON document]
```

HTTP 応答で、次の JSON ドキュメントを指定します。

```json
{
    "data": {
        "@odata.type": "microsoft.graph.OnOtpSendResponseData",
        "actions": [
            {
                "@odata.type": "microsoft.graph.OtpSend.continueWithDefaultBehavior"
            }
        ]
    }
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/custom-extension-onattributecollectionstart-retrieve-return-data"} -->
## OnAttributeCollectionStart イベントからデータを取得して返す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-onattributecollectionstart-retrieve-return-data
- Service: identity-platform
- Article date: 2025-09-16
- Summary: 外部 ID 顧客構成の OnAttributeCollectionStart イベントを呼び出すカスタム認証拡張機能のリファレンス ドキュメント。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

顧客のセルフサービス サインアップ ユーザー フローのサインアップ エクスペリエンスを変更するには、カスタム認証拡張機能を作成し、ユーザー フロー内の特定のポイントで呼び出します。 OnAttributeCollectionStart イベントは、属性コレクション ページがレンダリングされる前に、属性コレクション ステップの開始時に発生します。 このイベントを使用すると、ユーザーから属性を収集する前にアクションを定義できます。 たとえば、ユーザーがフェデレーション ID または電子メールに基づいてサインアップ フローを続行するのをブロックしたり、指定した値を持つ属性を事前入力したりできます。 次のアクションを構成できます。

- **continueWithDefaultBehavior** - 属性コレクション ページを通常どおりにレンダリングします。
- **setPreFillValues** - サインアップ フォームの事前入力属性。
- **showBlockPage** - エラー メッセージを表示し、ユーザーのサインアップをブロックします。

この記事では、OnAttributeCollectionStart イベントの REST API スキーマについて説明します。 (関連記事「 [OnAttributeCollectionSubmit イベントのカスタム拡張機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-onattributecollectionsubmit-reference)」も参照してください)。

### REST API スキーマ

属性コレクション開始イベント用の独自の REST API を開発するには、次の REST API データ コントラクトを使用します。 スキーマは、要求ハンドラーと応答ハンドラーを設計するコントラクトを記述します。

Microsoft Entra ID のカスタム認証拡張機能は、JSON ペイロードを使用して REST API への HTTP 呼び出しを行います。 JSON ペイロードには、ユーザー プロファイル データ、認証コンテキスト属性、およびユーザーがサインインするアプリケーションに関する情報が含まれます。 JSON 属性を使用して、API によって追加のロジックを実行できます。

#### 外部 REST API への要求

REST API への要求は、次に示す形式です。 この例では、要求には、組み込みの属性 (givenName と companyName) とカスタム属性 (universityGroups、graduationYear、onMailingList) と共にユーザー ID 情報が含まれています。

要求には、セルフサービス サインアップ時に収集のためにユーザー フローで選択されたユーザー属性が含まれます。これには、組み込み属性 (givenName や companyName など) や [、既に定義されているカスタム属性](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes) (universityGroups、graduationYear、onMailingList など) が含まれます。 REST API で新しい属性を追加することはできません。

要求にはユーザー ID も含まれます。これには、サインアップに検証済みの資格情報として使用された場合のユーザーの電子メールも含まれます。 パスワードは送信されません。

開始要求の属性には、既定値が含まれています。 複数の値を持つ属性の場合、値はコンマ区切りの文字列として送信されます。 属性はまだユーザーから収集されていないため、ほとんどの属性には値が割り当てられません。 次の HTTP 要求は、Microsoft Entra が REST API を呼び出す方法を示しています。

```http
POST https://example.azureWebsites.net/api/functionName

Content-Type: application/json

[Request payload]
```

次の JSON ドキュメントでは、要求ペイロードの例を示します。

```json
{
  "type": "microsoft.graph.authenticationEvent.attributeCollectionStart",
  "source": "/tenants/aaaabbbb-0000-cccc-1111-dddd2222eeee/applications/<resourceAppguid>",
  "data": {
    "@odata.type": "microsoft.graph.onAttributeCollectionStartCalloutData",
    "tenantId": "aaaabbbb-0000-cccc-1111-dddd2222eeee",
    "authenticationEventListenerId": "00001111-aaaa-2222-bbbb-3333cccc4444",
    "customAuthenticationExtensionId": "11112222-bbbb-3333-cccc-4444dddd5555",
    "authenticationContext": {
        "correlationId": "<GUID>",
        "client": {
            "ip": "30.51.176.110",
            "locale": "en-us",
            "market": "en-us"
        },
        "protocol": "OAUTH2.0",
        "clientServicePrincipal": {
            "id": "<Your Test Applications servicePrincipal objectId>",
            "appId": "<Your Test Application App Id>",
            "appDisplayName": "My Test application",
            "displayName": "My Test application"
        },
        "resourceServicePrincipal": {
            "id": "<Your Test Applications servicePrincipal objectId>",
            "appId": "<Your Test Application App Id>",
            "appDisplayName": "My Test application",
            "displayName": "My Test application"
        }
    },
    "userSignUpInfo": {
      "attributes": {
        "givenName": {
          "@odata.type": "microsoft.graph.stringDirectoryAttributeValue",
          "value": "Larissa Price",
          "attributeType": "builtIn"
        },
        "companyName": {
          "@odata.type": "microsoft.graph.stringDirectoryAttributeValue",
          "value": "Contoso University",
          "attributeType": "builtIn"
        },
        "extension_<appid>_universityGroups": {
          "@odata.Type": "microsoft.graph.stringDirectoryAttributeValue",
          "value": "Alumni,Faculty",
          "attributeType": "directorySchemaExtension"
        },
        "extension_<appid>_graduationYear": {
          "@odata.type": "microsoft.graph.int64DirectoryAttributeValue",
          "value": 2010,
          "attributeType": "directorySchemaExtension"
        },
        "extension_<appid>_onMailingList": {
          "@odata.type": "microsoft.graph.booleanDirectoryAttributeValue",
          "value": false,
          "attributeType": "directorySchemaExtension"
        }
      },
      "identities": [
        {
          "signInType": "email",
          "issuer": "contoso.onmicrosoft.com",
          "issuerAssignedId": "larissa.price@contoso.onmicrosoft.com"
        }
      ]
    }
  }
}
```

#### 外部 REST API からの応答

応答値の型は、要求値の型と一致します。次に例を示します。

- 要求に`graduationYear`の`@odata.type`を持つ属性`int64DirectoryAttributeValue`が含まれている場合、応答には、`graduationYear`などの整数値を持つ`2010`属性を含める必要があります。
- 要求にコンマ区切り文字列として指定された複数の値を持つ属性が含まれている場合、応答にはコンマ区切り文字列の値が含まれている必要があります。

Microsoft Entra ID は、次の形式の REST API 応答を受け取ります。

```http
HTTP/1.1 200 OK

Content-Type: application/json

[JSON document]
```

HTTP 応答で、次のいずれかの JSON ドキュメントを指定します。 **continueWithDefaultBehavior** アクションは、外部 REST API が継続応答を返していることを指定します。

```json
{
  "data": {
    "@odata.type": "microsoft.graph.onAttributeCollectionStartResponseData",
    "actions": [
      {
        "@odata.type": "microsoft.graph.attributeCollectionStart.continueWithDefaultBehavior"
      }
    ]
  }
}
```

**setPrefillValues** アクションは、外部 REST API が既定値を持つプリフィル属性への応答を返すように指定します。 REST API で新しい属性を追加することはできません。 返されるが、属性コレクションの一部ではない追加の属性は無視されます。

```json
{
  "data": {
    "@odata.type": "microsoft.graph.onAttributeCollectionStartResponseData",
    "actions": [
      {
        "@odata.type": "microsoft.graph.attributeCollectionStart.setPrefillValues",
        "inputs": {
          "key1": "value1,value2,value3",
          "key2": true
        }
      }
    ]
  }
}
```

**showBlockPage** アクションは、外部 REST API がブロック応答を返していることを指定します。

```json
{
  "data": {
    "@odata.type": "microsoft.graph.onAttributeCollectionStartResponseData",
    "actions": [
      {
        "@odata.type": "microsoft.graph.attributeCollectionStart.showBlockPage",
        "title": "Hold tight...",
        "message": "Your access request is already processing. You'll be notified when your request has been approved."
      }
    ]
  }
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/custom-extension-onattributecollectionsubmit-retrieve-return-data"} -->
## OnAttributeCollectionSubmit イベントからデータを取得して返す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-onattributecollectionsubmit-retrieve-return-data
- Service: identity-platform
- Article date: 2025-09-16
- Summary: 外部 ID の顧客構成に対して OnAttributeCollectionSubmit イベントを呼び出すカスタム認証拡張機能のリファレンス ドキュメント。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

顧客のセルフサービス サインアップ ユーザー フローのサインアップ エクスペリエンスを変更するには、カスタム認証拡張機能を作成し、ユーザー フロー内の特定のポイントで呼び出します。 OnAttributeCollectionSubmit イベントは、ユーザーが属性を入力して送信した後に発生し、ユーザーが提供する情報を検証するために使用できます。 たとえば、招待コードまたはパートナー番号を検証したり、アドレス形式を変更したり、ユーザーが続行したり、検証またはブロック ページを表示したりできます。 次のアクションを構成できます。

- **continueWithDefaultBehavior** - サインアップ フローを続行します。
- **modifyAttributeValues** - サインアップ フォームでユーザーが送信した値を上書きします。
- **showValidationError** - 送信された値に基づいてエラーを返します。
- **showBlockPage** - エラー メッセージを表示し、ユーザーのサインアップをブロックします。

この記事では、OnAttributeCollectionSubmit イベントの REST API スキーマについて説明します。 (関連記事「 [OnAttributeCollectionStart イベントのカスタム拡張機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-onattributecollectionstart-reference)」も参照してください)。

### REST API スキーマ

属性コレクション送信イベント用の独自の REST API を開発するには、次の REST API データ コントラクトを使用します。 スキーマは、要求ハンドラーと応答ハンドラーを設計するコントラクトを記述します。

Microsoft Entra ID のカスタム認証拡張機能は、JSON ペイロードを使用して REST API への HTTP 呼び出しを行います。 JSON ペイロードには、ユーザー プロファイル データ、認証コンテキスト属性、およびユーザーがサインインするアプリケーションに関する情報が含まれます。 JSON 属性を使用して、API によって追加のロジックを実行できます。

#### 外部 REST API への要求

REST API への要求は、次の例に示す形式です。 この例では、要求には、組み込みの属性 (givenName と companyName) とカスタム属性 (universityGroups、graduationYear、onMailingList) と共にユーザー ID 情報が含まれています。

要求には、セルフサービス サインアップ時にコレクション用にユーザー フローで選択されたユーザー属性が含まれます。 組み込みの属性 (givenName や companyName など) と [、既に定義されているカスタム属性](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-define-custom-attributes) (universityGroups、graduationYear、onMailingList など) が含まれます。 REST API で新しい属性を追加することはできません。

要求にはユーザー ID も含まれます。これには、サインアップに検証済みの資格情報として使用された場合のユーザーの電子メールも含まれます。 パスワードは送信されません。 複数の値を持つ属性の場合、値はコンマ区切りの文字列として送信されます。 次の HTTP 要求は、Microsoft Entra が REST API を呼び出す方法を示しています。

```http
POST https://example.azureWebsites.net/api/functionName

Content-Type: application/json

[Request payload]
```

次の JSON ドキュメントでは、要求ペイロードの例を示します。

```json
{
  "type": "microsoft.graph.authenticationEvent.attributeCollectionSubmit",
  "source": "/tenants/aaaabbbb-0000-cccc-1111-dddd2222eeee/applications/<resourceAppguid>",
  "data": {
    "@odata.type": "microsoft.graph.onAttributeCollectionSubmitCalloutData",
    "tenantId": "aaaabbbb-0000-cccc-1111-dddd2222eeee",
    "authenticationEventListenerId": "00001111-aaaa-2222-bbbb-3333cccc4444",
    "customAuthenticationExtensionId": "11112222-bbbb-3333-cccc-4444dddd5555",
    "authenticationContext": {
        "correlationId": "<GUID>",
        "client": {
            "ip": "30.51.176.110",
            "locale": "en-us",
            "market": "en-us"
        },
        "protocol": "OAUTH2.0",
        "clientServicePrincipal": {
            "id": "<Your Test Applications servicePrincipal objectId>",
            "appId": "<Your Test Application App Id>",
            "appDisplayName": "My Test application",
            "displayName": "My Test application"
        },
        "resourceServicePrincipal": {
            "id": "<Your Test Applications servicePrincipal objectId>",
            "appId": "<Your Test Application App Id>",
            "appDisplayName": "My Test application",
            "displayName": "My Test application"
        }
    },
    "userSignUpInfo": {
      "attributes": {
        "givenName": {
          "@odata.type": "microsoft.graph.stringDirectoryAttributeValue",
          "value": "Larissa Price",
          "attributeType": "builtIn"
        },
        "companyName": {
          "@odata.type": "microsoft.graph.stringDirectoryAttributeValue",
          "value": "Contoso University",
          "attributeType": "builtIn"
        },
        "extension_<appid>_universityGroups": {
          "@odata.Type": "microsoft.graph.stringDirectoryAttributeValue",
          "value": "Alumni,Faculty",
          "attributeType": "directorySchemaExtension"
        },
        "extension_<appid>_graduationYear": {
          "@odata.type": "microsoft.graph.int64DirectoryAttributeValue",
          "value": 2010,
          "attributeType": "directorySchemaExtension"
        },
        "extension_<appid>_onMailingList": {
          "@odata.type": "microsoft.graph.booleanDirectoryAttributeValue",
          "value": false,
          "attributeType": "directorySchemaExtension"
        }
      },
      "identities": [
        {
          "signInType": "email",
          "issuer": "contoso.onmicrosoft.com",
          "issuerAssignedId": "larissa.price@contoso.onmicrosoft.com"
        }
      ]
    }
  }
}
```

#### 外部 REST API からの応答

応答値の型は、要求値の型と一致します。次に例を示します。

- 要求に`graduationYear`の`@odata.type`を持つ属性`int64DirectoryAttributeValue`が含まれている場合、応答には、`graduationYear`などの整数値を持つ`2010`属性を含める必要があります。
- 要求にコンマ区切り文字列として指定された複数の値を持つ属性が含まれている場合、応答にはコンマ区切り文字列の値が含まれている必要があります。

Microsoft Entra ID は、次の形式の REST API 応答を受け取ります。

```http
HTTP/1.1 200 OK

Content-Type: application/json

[JSON document]
```

HTTP 応答で、次のいずれかの JSON ドキュメントを指定します。 **continueWithDefaultBehavior** アクションは、外部 REST API が継続応答を返していることを指定します。

```json
{
  "data": {
    "@odata.type": "microsoft.graph.onAttributeCollectionSubmitResponseData",
    "actions": [
      {
        "@odata.type": "microsoft.graph.attributeCollectionSubmit.continueWithDefaultBehavior"
      }
    ]
  }
}
```

**modifyAttributeValues** アクションは、属性の収集後に、外部 REST API が属性を変更し、既定値でオーバーライドする応答を返すように指定します。 REST API で新しい属性を追加することはできません。 返されるが、属性コレクションの一部ではない追加の属性は無視されます。

```json
{
  "data": {
    "@odata.type": "microsoft.graph.onAttributeCollectionSubmitResponseData",
    "actions": [
      {
        "@odata.type": "microsoft.graph.attributeCollectionSubmit.modifyAttributeValues",
        "attributes": {
          "key1": "value1,value2,value3",
          "key2": true
        }
      }
    ]
  }
}
```

**showBlockPage** アクションは、外部 REST API がブロック応答を返していることを指定します。

```json
{
  "data": {
    "@odata.type": "microsoft.graph.onAttributeCollectionSubmitResponseData",
    "actions": [
      {
        "@odata.type": "microsoft.graph.attributeCollectionSubmit.showBlockPage",
        "title": "Hold tight...",
        "message": "Your access request is already processing. You'll be notified when your request has been approved."
      }
    ]
  }
}
```

**showValidationError** アクションは、REST API が検証エラーと適切なメッセージと状態コードを返していることを指定します。

```json
{
  "data": {
    "@odata.type": "microsoft.graph.onAttributeCollectionSubmitResponseData",
    "actions": [
      {
        "@odata.type": "microsoft.graph.attributeCollectionSubmit.showValidationError",
        "message": "Please fix the below errors to proceed.",
        "attributeErrors": {
          "city": "City cannot contain any numbers",
          "extension_<appid>_graduationYear": "Graduation year must be at least 4 digits"
        }
      }
    ]
  }
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/custom-extension-overview"} -->
## カスタム認証拡張機能の概要 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview
- Service: identity-platform
- Article date: 2025-06-25
- Summary: Microsoft Entra カスタム認証拡張機能を使用して、REST API または送信 Webhook によるユーザーのサインイン エクスペリエンスをカスタマイズします。

Microsoft Entra ID 認証パイプラインは、ユーザー資格情報の検証、条件付きアクセス ポリシー、多要素認証、セルフサービス パスワード リセットなど、いくつかの組み込みの認証イベントで構成されます。

Microsoft Entra カスタム認証拡張機能を使用すると、認証フロー内の特定のポイントで独自のビジネス ロジックを使用して認証フローを拡張できます。 カスタム認証拡張機能は、基本的にイベント リスナーであり、アクティブ化されると、ワークフロー アクションを定義する REST API エンドポイントへの HTTP 呼び出しを行います。

たとえば、カスタム クレーム プロバイダーを使用して、トークンが発行される前に外部ユーザー データをセキュリティ トークンに追加できます。 属性コレクション ワークフローを追加して、サインアップ時にユーザーが入力した属性を検証できます。 この記事では、Microsoft Entra ID カスタム認証拡張機能の概要について説明します。

[Microsoft Entra カスタム認証拡張機能の概要](https://youtu.be/ZU90avf0Qyc?si=Gf77u4HS_5uw6Qjp)ビデオでは、カスタム認証拡張機能の主な機能の概要を説明します。

### コンポーネントの概要

構成する必要があるコンポーネントは 2 つあります。Microsoft Entra のカスタム認証拡張機能と REST API です。 カスタム認証拡張機能は、REST API エンドポイント、REST API を呼び出すタイミング、および REST API を呼び出す資格情報を指定します。

このビデオでは、Microsoft Entra カスタム認証拡張機能の構成について詳しく説明し、最適な実装のためのベスト プラクティスと貴重なヒントを提供します。

### サインインの流れ

次の図は、カスタム認証拡張機能と統合されたサインイン フローを示しています。

[Image: 外部ソースからの要求で拡張されているトークンを示す図。]

1. ユーザーがアプリにサインインしようとすると、Microsoft Entra サインイン ページにリダイレクトされます。
2. ユーザーが認証で特定の手順を完了すると、**イベント リスナー**がトリガーされます。
3. **カスタム認証拡張機能**は、**REST API エンドポイント**に HTTP 要求を送信します。 要求には、イベント、ユーザー プロファイル、セッション データ、およびその他のコンテキスト情報に関する情報が含まれています。
4. **REST API** はカスタム ワークフローを実行します。
5. **REST API** は Microsoft Entra ID に HTTP 応答を返します。
6. Microsoft Entra の**カスタム拡張機能**は応答を処理し、イベントの種類と HTTP 応答ペイロードに基づいて認証をカスタマイズします。
7. **アプリ**に**トークン**が返されます。

### REST API エンドポイント

イベントがトリガーされると、Microsoft Entra ID は所有する REST API エンドポイントを呼び出します。 REST API にはパブリックにアクセスできる必要があります。 これは、Azure Functions、Azure App Service、Azure Logic Apps、または他のパブリックに利用可能な API エンドポイントを使用してホストできます。

REST API の開発とデプロイには、任意のプログラミング言語、フレームワーク、またはコードなしのコードなしのソリューション (Azure Logic Apps など) を柔軟に使用できます。 簡単な方法で作業を開始するには、Azure 関数の使用を検討してください。 これにより、最初に仮想マシン (VM) を作成したり、Web アプリケーションを発行したりする必要なく、サーバーレス環境でコードを実行できます。

REST API で次の処理を行う必要があります。

- REST API 呼び出しをセキュリティで保護するためのトークン検証。
- ビジネス ロジック
- データとアクションの種類を返す
- HTTP 要求スキーマと応答スキーマの受信と送信の検証。
- 監査とログ記録。
- 可用性、パフォーマンス、およびセキュリティ制御。

このビデオでは、コードを記述せずに、Azure Logic Apps で認証拡張機能 REST API エンドポイントを作成する方法について説明します。 Azure Logic App を使用すると、ユーザーはビジュアル デザイナーを使用してワークフローを構築できます。 このビデオでは、検証メールのカスタマイズについて説明し、カスタム要求プロバイダーを含むすべての種類のカスタム認証拡張機能に適用されます。

#### 要求ペイロード

REST API への要求には、イベント、ユーザー プロファイル、認証要求データ、およびその他のコンテキスト情報に関する詳細を含む JSON ペイロードが含まれています。 JSON ペイロード内の属性を使用して、API でロジックを実行できます。

たとえば、 トークン発行開始 イベントでは、要求ペイロードにユーザーの一意識別子が含まれる場合があり、独自のデータベースからユーザー プロファイルを取得できます。 要求ペイロード データは、イベント ドキュメントで指定されているスキーマに従う必要があります。

#### データとアクションの種類を返す

Web API は、ビジネス ロジックを使用してワークフローを実行した後、認証プロセスの続行方法を Microsoft Entra に指示する **アクションの種類** を返す必要があります。

たとえば、 属性コレクションの開始 イベントと 属性コレクション送信 イベントの場合、Web API によって返される **アクションの種類** は、アカウントをディレクトリに作成できるか、検証エラーを表示できるか、サインアップ フローを完全にブロックできるかを示します。

REST API 応答には、データが含まれる場合があります。 たとえば、 on トークン発行開始 イベントでは、セキュリティ トークンにマップできる一連の属性を提供できます。

#### REST API を保護する

カスタム認証拡張機能と REST API 間の通信を適切にセキュリティで保護するには、複数のセキュリティ制御を適用する必要があります。

1. カスタム認証拡張機能が REST API を呼び出すと、Microsoft Entra ID によって発行されたベアラー トークンを含む HTTP `Authorization` ヘッダーが送信されます。
2. ベアラー トークンには、`appid` または `azp` 要求が含まれています。 それぞれの要求に `99045fe1-7639-4a75-9d4a-577b6ca3810f`値が含まれていることを検証します。 この値により、Microsoft Entra ID で REST API が確実に呼び出されます。
    1. **V1** アプリケーションの場合は、`appid` 要求を検証します。
    2. **V2** アプリケーションの場合は、`azp` 要求を検証します。
3. ベアラー トークン `aud` 対象ユーザー要求には、関連付けられているアプリケーション登録の ID が含まれています。 REST API エンドポイントでは、その特定の対象ユーザーに対してベアラー トークンが発行されていることを検証する必要があります。
4. ベアラー トークン `iss`発行者のクレームには、Microsoft Entra 発行者の URL が含まれています。 テナントの構成に応じて、発行者の URL は次のいずれかになります。
    - 従業員: `https://login.microsoftonline.com/{tenantId}/v2.0`。
    - お客様: `https://{domainName}.ciamlogin.com/{tenantId}/v2.0`。

### カスタム認証イベントの種類

このセクションでは、Microsoft Entra ID の従業員と外部テナントで使用できるカスタム認証拡張機能イベントの一覧を示します。 イベントの詳細については、それぞれのドキュメントを参照してください。

| 出来事 | 従業員テナント | 外部テナント |
| --- | --- | --- |
| トークン発行の開始 |  |  |
| 属性コレクションの開始 |  |  |
| 属性コレクションの送信 |  |  |
| ワンタイム パスコード送信 |  |  |
| アカウントの回復 (追加の要求検証) |  |  |

#### トークン発行の開始

トークン発行開始イベント **OnTokenIssuanceStart** は、トークンがアプリケーションに発行されるときにトリガーされます。 これは、 [カスタム クレーム プロバイダー](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview)内で設定されるイベントの種類です。 カスタム クレーム プロバイダーは、REST API を呼び出して外部システムから要求をフェッチするカスタム認証拡張機能です。 カスタム クレーム プロバイダーは、外部システムからのクレームをトークンにマップします。また、プロバイダーはディレクトリ内の 1 つまたは複数のアプリケーションに割り当てることができます。

#### 属性コレクションの開始

[属性コレクションの開始](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-attribute-collection) イベントをカスタム認証拡張機能と共に使用して、ユーザーから属性を収集する前にロジックを追加できます。 **OnAttributeCollectionStart** イベントは、属性収集ページがレンダリングされる前の、属性収集ステップの開始時に発生します。 このイベントにより、値の事前入力やブロッキング エラーの表示などのアクションを追加できます。

#### 属性コレクションを提出する

[属性コレクションの送信](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-attribute-collection) イベントは、カスタム認証拡張機能と共に使用して、ユーザーから属性を収集した後にロジックを追加できます。 **OnAttributeCollectionSubmit** イベントは、ユーザーが属性を入力して送信した後にトリガーされ、エントリの検証や属性の変更などのアクションを追加できます。

#### ワンタイムパスコードを送信する

**OnOtpSend** イベントは、ワンタイム パスコード電子メールがアクティブになるとトリガーされます。 REST [API を呼び出して、独自の電子メール プロバイダーを使用](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-email-otp-get-started)できます。 このイベントを使用すると、メール アドレスでサインアップする、メールワンタイム パスコード (電子メール OTP) でサインインする、メール OTP を使用してパスワードをリセットする、または多要素認証 (MFA) に電子メール OTP を使用するユーザーに、カスタマイズされた電子メールを送信できます。

**OnOtpSend** イベントがアクティブになると、Microsoft Entra は、所有する指定した REST API にワンタイム パスコードを送信します。 その後、REST API は、選択した電子メール プロバイダー (Azure Communication Service や SendGrid など) を使用して、アドレス、電子メールの件名からカスタム電子メール テンプレートを使用してワンタイム パスコードを送信し、ローカライズもサポートします。

#### アカウントの回復 (追加の要求検証)

**OnVerifiedIdClaimValidation** イベントは、ユーザーが検証済み ID 要求を提示して ID を再確立すると、[アカウントの回復](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-account-recovery-overview)中にトリガーされます。 アカウントの回復にカスタム認証拡張機能を使用する主な理由は、回復を要求するユーザーが、有効な人間だけでなく、実際の従業員であることを確認することです。 ユーザーが検証済み ID を提示すると、Microsoft Entraは資格情報からカスタム認証拡張機能に要求を渡します。 その後、REST API は、これらの要求を人事システムや従業員レコード データベースなどの権限のあるデータ ソースと比較し、合格または失敗の決定を返すことができます。

アカウント回復プロファイルを Eval モードから運用モードに移行する場合は、カスタム認証拡張機能を使用することを強くお勧めします。 組み込みの名と姓の一致は、大規模なユーザー グループでは十分に信頼性が高くなく、ごく少数のユーザーにのみ使用する必要があります。

詳細については、「 [アカウント回復要求の検証用のカスタム認証拡張機能を作成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-custom-authentication-extension-account-recovery)」を参照してください。 このイベントの種類では、テナントごとに 1 つのカスタム認証拡張機能のみが許可されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/custom-extension-tokenissuancestart-configuration"} -->
## カスタム クレーム プロバイダー: トークン発行イベントを構成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration
- Service: identity-platform
- Article date: 2025-05-04
- Summary: Microsoft Entra IDでトークン発行開始イベントのカスタム クレーム プロバイダーを構成する方法について説明します。 トークンを発行する前に、カスタム クレームをトークンに追加できます。

この記事では、 [トークン発行開始イベント](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview#token-issuance-start-event-listener)用にカスタム クレーム プロバイダーを構成する方法について説明します。 既存の Azure Functions REST API を使用して、カスタム認証エクステンションを登録し、REST API から解析が期待される属性を追加します。 カスタム認証拡張機能をテストするため、サンプルの OpenID Connect アプリケーションを登録してトークンを取得し、クレームを表示します。

このビデオでは、カスタム クレーム プロバイダーを使用して外部システムからの要求をセキュリティ トークンMicrosoft Entraマッピングする手順について説明します。

### 前提条件

- Azure Functionsを作成できるAzure サブスクリプション。 既存のAzure アカウントをお持ちでない場合は、[無料試用版](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)にサインアップするか、[Visual Studio サブスクリプション](https://visualstudio.microsoft.com/subscriptions/)特典を使用して、[アカウント](https://account.windowsazure.com/Home/Index)を作成します。
- Azure Functionsにデプロイされたトークン発行イベント用に構成された HTTP トリガー関数。 お持ちでない場合は、Azure Functionsでトークン発行開始イベント用の REST API を作成します。
- [カスタム認証拡張機能の概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview)で説明されている概念の基本的な理解。
- Microsoft Entra ID テナント。 この攻略ガイドには、顧客または従業員のどちらかのテナントを使用できます。
    - 外部テナントの場合は、 [サインアップとサインインのユーザー フローを使用します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)。

### 手順 1: カスタム認証拡張機能を登録する

次に、Azure関数を呼び出すためにMicrosoft Entra IDによって使用されるカスタム認証拡張機能を構成します。 カスタム認証拡張機能には、REST API エンドポイントに関する情報、REST API から解析するクレーム、REST API に対する認証方法が含まれています。 カスタム認証拡張機能を Azure Function アプリに登録するには、次の手順に従います。

注

最大 100 個のカスタム拡張機能ポリシーを使用できます。

## [Azure portal](#tab/azure-portal)
#### カスタム認証拡張機能を登録する

1. [Azure ポータル](https://portal.azure.com)に少なくとも [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer) および [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)としてサインインします。
2. **Microsoft Entra ID** を検索して選択し、 **Enterprise アプリケーション**を選択します。
3. [ **カスタム認証拡張機能**] を選択し、[ **カスタム拡張機能の作成**] を選択します。
4. **[基本**] で、**TokenIssuanceStart** イベントの種類を選択し、[**次へ**] を選択します。
5. **エンドポイント構成**で、次のプロパティを入力します。
    - **名前** - カスタム認証拡張機能の名前。 たとえば、 *トークン発行イベント*です。
    - **Target Url** - Azure関数 URL の `{Function_Url}`。 Azure Function アプリの **Overview** ページに移動し、作成した関数を選択します。 [関数の **概要** ] ページで、[ **関数 URL の取得** ] を選択し、コピー アイコンを使用して **customauthenticationextension\_extension (システム キー)** URL をコピーします。
    - **説明** - カスタム認証拡張機能の説明。
6. **次へ**を選択します。
7. **API 認証**で、[**新しいアプリ登録の作成**] オプションを選択して、*関数アプリ*を表すアプリ登録を作成します。
8. アプリに、例えば**Azure Functions 認証イベント API**のような名前を付けます。
9. **次へ**を選択します。
10. **クレーム**には、カスタム認証拡張機能がREST APIから解析してトークンにマージする属性を入力します。 次のクレームを追加します。
    - 生年月日
    - カスタムロール
    - ApiVersion
    - CorrelationId
11. [ **次へ**] を選択し、[ **作成**] を選択します。これにより、カスタム認証拡張機能と関連付けられているアプリケーションの登録が登録されます。
12. Azure Function アプリでの認証を構成するために、**API 認証** の下にある **App ID** に注意してください。

## [Microsoft Graph](#tab/microsoft-graph)
#### アプリケーションの登録

1. カスタム認証拡張機能を管理するテナントがホーム テナントであるアカウントを使用して [Graph Explorer](https://aka.ms/ge) にサインインします。 このアカウントには、テナントでアプリケーション登録を作成および管理するための特権が必要です。
2. 次の要求を実行します。

    ```http
    POST https://graph.microsoft.com/v1.0/applications
    Content-type: application/json
    
    {
        "displayName": "authenticationeventsAPI"
    }
    ```
3. 応答から、新しく作成されたアプリ登録の **ID** と **appId** の値を記録します。 この記事では、これらの値はそれぞれ `{authenticationeventsAPI_ObjectId}` および `{authenticationeventsAPI_AppId}` として参照されています。

#### authenticationeventsAPI アプリ登録用のサービス プリンシパルをテナントに作成します。

Graph エクスプローラーで、次の要求を実行します。 `{authenticationeventsAPI_AppId}`を、前の手順で記録した **appId** の値に置き換えます。

```http
POST https://graph.microsoft.com/v1.0/servicePrincipals
Content-type: application/json
    
{
    "appId": "{authenticationeventsAPI_AppId}"
}
```

#### アプリ ID URI、アクセス トークンのバージョン、必要なリソース アクセスを設定する

新しく作成したアプリケーションを更新して、アプリケーション ID URI の値、アクセス トークンのバージョン、必要なリソース アクセスを設定します。

Graph エクスプローラーで、次の要求を実行します。

- *identifierUris* プロパティでアプリケーション ID URI 値を設定します。 `{Function_Url_Hostname}` を、前に記録した `{Function_Url}` のホスト名に置き換えます。
- `{authenticationeventsAPI_AppId}` の値を、先ほど記録した **appId** に設定します。
- 値の例が `api://authenticationeventsAPI.azurewebsites.net/00001111-aaaa-2222-bbbb-3333cccc4444`。 この値は、 `{functionApp_IdentifierUri}`の代わりにこの記事の後半で使用するのでメモしておきます。

```http
POST https://graph.microsoft.com/v1.0/applications/{authenticationeventsAPI_ObjectId}
Content-type: application/json

{
"identifierUris": [
    "api://{Function_Url_Hostname}/{authenticationeventsAPI_AppId}"
],    
"api": {
    "requestedAccessTokenVersion": 2,
    "acceptMappedClaims": null,
    "knownClientApplications": [],
    "oauth2PermissionScopes": [],
    "preAuthorizedApplications": []
},
"requiredResourceAccess": [
    {
        "resourceAppId": "00000003-0000-0000-c000-000000000000",
        "resourceAccess": [
            {
                "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
                "type": "Role"
            }
        ]
    }
]
}
```

#### カスタム認証拡張機能を登録する

カスタム認証拡張機能を登録するには、Azure関数のアプリ登録と、Azure関数エンドポイント `{Function_Url}`に関連付けます。

1. Graph エクスプローラーで、次の要求を実行します。 `{Function_Url}`をAzure関数アプリのホスト名に置き換えます。 `{functionApp_IdentifierUri}` は、前のステップで使った identifierUri に置き換えます。

    - *CustomAuthenticationExtension.ReadWrite.All* の委任されたアクセス許可が必要です。

    ```http
    POST https://graph.microsoft.com/beta/identity/customAuthenticationExtensions
    Content-type: application/json
    
    {
        "@odata.type": "#microsoft.graph.onTokenIssuanceStartCustomExtension",
        "displayName": "onTokenIssuanceStartCustomExtension",
        "description": "Fetch additional claims from custom user store",
        "endpointConfiguration": {
            "@odata.type": "#microsoft.graph.httpRequestEndpoint",
            "targetUrl": "{Function_Url}"
        },
        "authenticationConfiguration": {
            "@odata.type": "#microsoft.graph.azureAdTokenAuthentication",
            "resourceId": "{functionApp_IdentifierUri}"
        },
        "claimsForTokenConfiguration": [
            {
                "claimIdInApiResponse": "DateOfBirth"
            },
            {
                "claimIdInApiResponse": "CustomRoles"
            }
        ]
    }
    ```
2. 作成されたカスタム クレーム プロバイダー オブジェクトの `id` 値を記録します。 この値は、このチュートリアルの後半で `{customExtensionObjectId}`の代わりに使用します。

---

#### 1.2 管理者の同意の付与

カスタム認証拡張機能が作成されたら、API にアクセス許可を付与する必要があります。 カスタム認証拡張機能では、`client_credentials` を使用して、`Receive custom authentication extension HTTP requests` アクセス許可を使用してAzure関数アプリに対する認証を行います。

1. 新しいカスタム認証拡張機能の **[概要]** ページを開きます。 ID プロバイダーを追加するときに必要であるため、**API 認証**の下の**アプリ ID** を書き留めます。
2. [ **API 認証**] で、[ **アクセス許可の付与**] を選択します。
3. 新しいウィンドウが開き、サインインすると、カスタム認証拡張機能の HTTP 要求を受信するためのアクセス許可が要求されます。 これで、API に対してカスタム認証拡張機能を認証できるようになります。 **[Accept](https://learn.microsoft.com/ja-jp/entra/identity-platform/承認)** を選択します。

    [Image: 管理者の同意を付与する方法を示すスクリーンショット。]

### 手順 2: エンリッチされたトークンを受信するように OpenID Connect アプリを構成する

トークンを取得してカスタム認証拡張機能をテストするには、https://jwt.ms アプリを使用できます。 これは Microsoft 所有の Web アプリケーションであり、トークンのデコードされた内容を表示します (トークンの内容がお使いのブラウザーの外に出ることはありません)。

#### 2.1 テスト Web アプリケーションを登録する

jwt.ms Web アプリケーションを登録するには、次 **の手順に** 従います。

1. Azure ポータルの **Home** ページで、**Microsoft Entra ID** を選択します。
2. **App registrations**&gt;**New registration** を選択します。
3. アプリケーションの **名前** を入力します。 たとえば、 **My Test アプリケーション**です。
4. **[サポートされているアカウントの種類]** で、 **[この組織のディレクトリ内のアカウントのみ]** を選択します。
5. [**リダイレクト URI**] の [**プラットフォームの選択**] ドロップダウンで[**Web**] を選択し、[URL] テキスト ボックスに「`https://jwt.ms`」と入力します。
6. [ **登録** ] を選択してアプリの登録を完了します。

    [Image: サポートされているアカウントの種類とリダイレクト URI を選択する方法を示すスクリーンショット。]
7. アプリ登録の **[概要** ] ページで、 **アプリケーション (クライアント) ID をコピーします**。 後の手順では、アプリ ID を `{App_to_enrich_ID}` として参照します。 Microsoft Graphでは、**appId** プロパティによって参照されます。

    [Image: アプリケーション ID をコピーする方法を示すスクリーンショット。]

#### 2.2 暗黙的なフローを有効にする

**jwt.ms** テスト アプリケーションでは、暗黙的なフローが使用されます。 *My Test アプリケーション*の登録で暗黙的なフローを有効にします。

重要

Microsoft では、使用可能な最も安全な認証フローを使用することをお勧めします。 この手順でテストに使用される認証フローは、アプリケーションで非常に高いレベルの信頼を必要とするため、他のフローには存在しないリスクが伴います。 この方法は、運用アプリに対するユーザーの認証には使用しないでください ([詳細を参照](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow))。

1. **[管理]** で、 **[認証]** を選択します。
2. [ **暗黙的な許可とハイブリッド フロー**] で、 **ID トークン (暗黙的およびハイブリッド フローに使用)** チェック ボックスをオンにします。
3. **保存**を選びます。

#### 2.3 クレーム マッピング ポリシーに対してアプリを有効にする

カスタム認証拡張機能から返された属性から、トークンにマップされるものを選ぶには、クレーム マッピング ポリシーを使います。 トークンを拡張できるようにするには、アプリケーション登録によるマップされたクレームの受け入れを明示的に有効にする必要があります。

1. *マイ テスト アプリケーション*の登録で、[**管理**] で **[マニフェスト**] を選択します。
2. マニフェストで、 `acceptMappedClaims` 属性を見つけて、値を `true` に設定します。
3. `requestedAccessTokenVersion` を `2` に設定します。
4. **[保存]** を選択して変更を保存します。

次の JSON スニペットでは、これらのプロパティを構成する方法を示します。

```json
{"id": "22222222-0000-0000-0000-000000000000","acceptMappedClaims": true,"requestedAccessTokenVersion": 2,  
    ...
}
```

警告

`acceptMappedClaims`プロパティをマルチテナント アプリの`true`に設定しないでください。これにより、悪意のあるアクターがアプリのクレーム マッピング ポリシーを作成できます。 代わりに [、カスタム署名キーを構成します](https://learn.microsoft.com/ja-jp/graph/application-saml-sso-configure-api#option-2-create-a-custom-signing-certificate)。

## [従業員テナント](#tab/workforce-tenant)
次の手順に進 み、カスタム要求プロバイダーをアプリに割り当てます。

## [外部テナント](#tab/external-tenant)
#### 3.4 アプリをユーザー フローに関連付ける

外部テナントの場合は、アプリをユーザー フローに関連付ける必要があります。 ユーザー フローによって、顧客がアプリケーションにサインインするために使用できる認証方法と、サインアップ中に指定する必要のある情報が定義されます。 ユーザー フローに[マイ テスト](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)*アプリケーションを追加する前に、「アプリケーションをユーザー フロー*に追加する」の手順を完了していることを確認します。

---

### 手順 3: カスタム クレーム プロバイダーをアプリに割り当てる

カスタム認証拡張機能から受け取ったクレームでトークンが発行されるようにするには、カスタム クレーム プロバイダーをアプリケーションに割り当てる必要があります。 これはトークンの対象ユーザーに基づいているため、ID トークンで要求を受け取るにはクライアント アプリケーションにプロバイダーを割り当て、アクセス トークンで要求を受け取るにはリソース アプリケーションにプロバイダーを割り当てる必要があります。 カスタム クレーム プロバイダーは、 **トークン発行開始** イベント リスナーで構成されたカスタム認証拡張機能に依存します。 カスタム クレーム プロバイダーからのクレームのすべてまたはサブセットのどちらを、トークンにマップするかを選択できます。

注

アプリケーションとカスタム拡張機能の間で作成できる一意の割り当ては 250 個のみです。 複数のアプリに同じカスタム拡張機能呼び出しを適用する場合は、[authenticationEventListeners](https://learn.microsoft.com/ja-jp/graph/api/identitycontainer-post-authenticationeventlisteners) Microsoft Graph API を使用して複数のアプリケーションのリスナーを作成することをお勧めします。 これは、Azure ポータルではサポートされていません。

*My Test アプリケーション*をカスタム認証拡張機能に接続するには、次の手順に従います。

## [Azure portal](#tab/azure-portal)
カスタム認証拡張機能をカスタム クレーム プロバイダー ソースとして割り当てるには、次を行います。

1. Azure ポータルの **Home** ページで、**Microsoft Entra ID** を選択します。
2. **[エンタープライズ アプリケーション**] を選択し、[**管理**] で [**すべてのアプリケーション**] を選択します。 一覧から *[マイ テスト アプリケーション* ] を見つけて選択します。
3. **マイ テスト アプリケーション**の *[概要*] ページで、[**管理**] に移動し、[**シングル サインオン**] を選択します。
4. **属性とクレーム**の下で、**編集**を選択します。

    [Image: アプリ要求を構成する方法を示すスクリーンショット。]
5. [ **詳細設定** ] メニューを展開します。
6. **[カスタム クレーム プロバイダー] の**横にある [**構成**] を選択します。
7. [ **カスタム クレーム プロバイダー** ] ドロップダウン ボックスを展開し、前に作成した *トークン発行イベント* を選択します。
8. **保存**を選びます。

次に、カスタム クレーム プロバイダーから属性を割り当てます。これは、クレームとしてトークンに発行されている必要があります。

1. [ **新しい要求の追加]** を選択して、新しい要求を追加します。 発行する要求に名前を付けてください (例えば *DateOfBirth*)。
2. [**ソース**] で [**属性**] を選択し、[*ソース属性*] ドロップダウン ボックスから **customClaimsProvider.DateOfBirth** を選択します。

    [Image: 要求マッピングをアプリに追加する方法を示すスクリーンショット。]
3. **保存**を選びます。
4. このプロセスを繰り返して、 *customClaimsProvider.customRoles*、 *customClaimsProvider.apiVersion* 、 *customClaimsProvider.correlationId* 属性、および対応する名前を追加します。 クレームの名前と属性の名前は一致させることをお勧めします。

## [Microsoft Graph](#tab/microsoft-graph)
まず、トークン発行開始イベントを使用して *My Test アプリケーション* のカスタム認証拡張機能をトリガーするイベント リスナーを作成します。

1. カスタム認証拡張機能を管理するテナントがホーム テナントであるアカウントを使用して [Graph Explorer](https://aka.ms/ge) にサインインします。
2. 次の要求を実行します。 `{App_to_enrich_ID}` を、前に記録した "マイ テスト アプリケーション" のアプリ ID に置き換えます。`{customExtensionObjectId}` を、先ほど記録したカスタム認証拡張機能の ID に置き換えます。

    - *EventListener.ReadWrite.All* の委任されたアクセス許可が必要です。

    ```http
    POST https://graph.microsoft.com/beta/identity/authenticationEventListeners
    Content-type: application/json
    
    {
        "@odata.type": "#microsoft.graph.onTokenIssuanceStartListener",
        "conditions": {
            "applications": {
                "includeAllApplications": false,
                "includeApplications": [
                    {
                        "appId": "{App_to_enrich_ID}"
                    }
                ]
            }
        },
        "priority": 500,
        "handler": {
            "@odata.type": "#microsoft.graph.onTokenIssuanceStartCustomExtensionHandler",
            "customExtension": {
                "id": "{customExtensionObjectId}"
            }
        }
    }
    ```

次に、カスタム クレーム プロバイダーからアプリケーションに発行できるクレームが記述されているクレーム マッピング ポリシーを作成します。

1. 引き続き Graph エクスプローラーで、次の要求を実行します。 *Policy.ReadWrite.ApplicationConfiguration* の委任されたアクセス許可が必要です。

    ```http
    POST https://graph.microsoft.com/v1.0/policies/claimsMappingPolicies
    Content-type: application/json
    
    {
        "definition": [
            "{\"ClaimsMappingPolicy\":{\"Version\":1,\"IncludeBasicClaimSet\":\"true\",\"ClaimsSchema\":[{\"Source\":\"CustomClaimsProvider\",\"ID\":\"DateOfBirth\",\"JwtClaimType\":\"dob\"},{\"Source\":\"CustomClaimsProvider\",\"ID\":\"CustomRoles\",\"JwtClaimType\":\"my_roles\"},{\"Source\":\"CustomClaimsProvider\",\"ID\":\"CorrelationId\",\"JwtClaimType\":\"correlationId\"},{\"Source\":\"CustomClaimsProvider\",\"ID\":\"ApiVersion\",\"JwtClaimType\":\"apiVersion \"},{\"Value\":\"tokenaug_V2\",\"JwtClaimType\":\"policy_version\"}]}}"
        ],
        "displayName": "MyClaimsMappingPolicy",
        "isOrganizationDefault": false
    }
    ```
2. 応答で生成された `ID` を記録します。後で `{claims_mapping_policy_ID}` と呼ばれます。

サービス プリンシパル オブジェクト ID を取得します。

1. Graph エクスプローラーで、次の要求を実行します。 `{App_to_enrich_ID}`を**マイ テスト アプリケーション**の *appId に*置き換えます。

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals(appId='{App_to_enrich_ID}')
    ```

ID の値を記録 **します**。

要求マッピング ポリシーを *マイ テスト アプリケーション*のサービス プリンシパルに割り当てます。

1. Graph エクスプローラーで、次の要求を実行します。 *Policy.ReadWrite.ApplicationConfiguration* と *Application.ReadWrite.All* の委任されたアクセス許可が必要です。

    ```http
    POST https://graph.microsoft.com/v1.0/servicePrincipals/{test_App_Service_Principal_ObjectId}/claimsMappingPolicies/$ref
    Content-type: application/json
    
    {
        "@odata.id": "https://graph.microsoft.com/v1.0/policies/claimsMappingPolicies/{claims_mapping_policy_ID}"
    }
    ```

---

### 手順 4: Azure関数を保護する

カスタム認証拡張機能Microsoft Entraサーバー間フローを使用して、HTTP `Authorization` ヘッダーでAzure関数に送信されるアクセス トークンを取得します。 特に運用環境で関数をAzureに発行する場合は、承認ヘッダーで送信されたトークンを検証する必要があります。

Azure関数を保護するには、次の手順に従って、Microsoft Entra認証を統合し、受信トークンを *Azure Functions 認証イベント API* アプリケーション登録と検証します。 テナントの種類に基づいて、次のいずれかのタブを選択します。

注

Azure関数アプリが、カスタム認証拡張機能が登録されているテナントとは異なるAzure テナントでホストされている場合は、Open ID Connect タブを選択します。

#### 4.1 Microsoft Entra ID プロバイダーの使用

id プロバイダーとしてMicrosoft EntraをAzure関数アプリに追加するには、次の手順に従います。

## [従業員テナント](#tab/workforce-tenant)
1. [Azure ポータル](https://portal.azure.com)で、以前に発行した関数アプリを見つけて選択します。
2. **[設定]** で **[認証]** を選択します。
3. [ **ID プロバイダーの追加] を選択します**。
4. ID プロバイダーとして **Microsoft** を選択します。
5. テナントの種類として **[Workforce** ] を選択します。
6. **App 登録**の下で、**このディレクトリ内の既存のアプリ登録を選択** を**App 登録の種類**として選択し、カスタム クレーム プロバイダーを登録する際に*Azure Functions 認証イベント API* アプリ登録を作成済みを選択します。
7. 次の発行者 URL ( `https://login.microsoftonline.com/{tenantId}/v2.0`) を入力します。ここで、 `{tenantId}` は従業員テナントのテナント ID です。
8. [ **クライアント アプリケーションの要件**] で、[ **特定のクライアント アプリケーションからの要求を許可する** ] を選択し、「 `99045fe1-7639-4a75-9d4a-577b6ca3810f`」と入力します。
9. [ **テナント要件**] で、[ **特定のテナントからの要求を許可する** ] を選択し、従業員のテナント ID を入力します。
10. [ **認証されていない要求**] で、ID プロバイダーとして **[HTTP 401 Unauthorized** ] を選択します。
11. **[トークン ストア**] オプションの選択を解除します。
12. **Add** を選択して、Azure関数に認証を追加します。

    [Image: ワークフォース テナント内で関数アプリに認証を追加する方法を示すスクリーンショット。]

## [外部テナント](#tab/external-tenant)
1. [Azure ポータル](https://portal.azure.com)で、以前に発行した関数アプリを見つけて選択します。
2. **[設定]** で **[認証]** を選択します。
3. [ **ID プロバイダーの追加] を選択します**。
4. ID プロバイダーとして **Microsoft** を選択します。
5. テナントの種類として **[外部構成** ] を選択します。
6. **App registration** で、**App registration type** の「既存のアプリ登録の詳細を提供」を選択し、カスタム クレーム プロバイダーを登録するときに作成済みの*Azure Functions 認証イベント API* アプリ登録の`client_id`を入力します。
7. **発行者 URL** には、次の URL `https://{domainName}.ciamlogin.com/{tenant_id}/v2.0`を入力します。

    - `{domainName}` は、外部テナントのドメイン名です ( `{domainName}.contoso.com`形式)。
    - `{tenantId}` は外部テナントのテナント ID です。
8. [ **クライアント アプリケーションの要件**] で、[ **特定のクライアント アプリケーションからの要求を許可する** ] を選択し、「 `99045fe1-7639-4a75-9d4a-577b6ca3810f`」と入力します。
9. [ **テナント要件**] で、[ **特定のテナントからの要求を許可する** ] を選択し、外部テナント ID を入力します。
10. [ **認証されていない要求**] で、ID プロバイダーとして **[HTTP 401 Unauthorized** ] を選択します。
11. **[トークン ストア**] オプションの選択を解除します。
12. **Add** を選択して、Azure関数に認証を追加します。

    [Image: 外部テナントで関数アプリに認証を追加する方法を示すスクリーンショット。]

---

#### 4.2 OpenID Connect ID プロバイダーの使用

Microsoft ID プロバイダーを構成した場合は、この手順をスキップします。 それ以外の場合、Azure関数が、カスタム認証拡張機能が登録されているテナントとは異なるテナントでホストされている場合は、次の手順に従って関数を保護します。

##### クライアント シークレットの作成

1. Azure ポータルの **Home** ページで、**Microsoft Entra ID**&gt;**App registrations** を選択します。
2. *Azure Functions認証イベント API*のアプリ登録を、以前に作成したものから選択します。
3. **[証明書とシークレット**&gt;**クライアント シークレット**&gt;**新しいクライアント シークレット**を選択します。
4. シークレットの有効期限を選択するか、カスタム有効期間を指定し、説明を追加して、[ **追加]** を選択します。
5. クライアント アプリケーション コードで使用する **シークレットの値** を記録します。 このページからの移動後は、このシークレットの値は "二度と表示されません"。

##### Azure関数アプリに OpenID Connect ID プロバイダーを追加します。

1. 前に発行した関数アプリを探して選択します。
2. **[設定]** で **[認証]** を選択します。
3. [ **ID プロバイダーの追加] を選択します**。
4. ID プロバイダーとして **OpenID Connect** を選択します。
5. Contoso Microsoft Entra ID。
6. [ **メタデータ] エントリ**で、ドキュメント URL に次の **URL を入力します**。 `{tenantId}`をMicrosoft Entraテナント ID に置き換えます。

    ```http
    https://login.microsoftonline.com/{tenantId}/v2.0/.well-known/openid-configuration
    ```
7. **App registration** で、*Azure Functions 認証イベント API* アプリ登録 以前に作成したアプリケーション ID (クライアント ID) を入力。
8. Azure関数に戻り、**App registration** で、**Client シークレット**を入力します。
9. **[トークン ストア**] オプションの選択を解除します。
10. OpenID Connect ID プロバイダーを追加するには、[ **追加]** を選択します。

### ステップ 5: アプリケーションをテストする

カスタム クレーム プロバイダーをテストするには、次のステップに従います。

## [従業員テナント](#tab/workforce-tenant)
1. 新しいプライベート ブラウザーを開き、次の URL に移動してサインインします。

    ```http
    https://login.microsoftonline.com/{tenantId}/oauth2/v2.0/authorize?client_id={App_to_enrich_ID}&response_type=id_token&redirect_uri=https://jwt.ms&scope=openid&state=12345&nonce=12345
    ```
2. `{tenantId}` は、テナント ID、テナント名、または検証済みドメイン名の 1 つに置き換えます。 たとえば、「 `contoso.onmicrosoft.com` 」のように入力します。
3. `{App_to_enrich_ID}`を *My Test アプリケーション* クライアント ID に置き換えます。
4. ログインすると、デコードされたトークンが `https://jwt.ms`に表示されます。 Azure関数からの要求がデコードされたトークン (例: `DateOfBirth`) に表示されることを検証します。

## [外部テナント](#tab/external-tenant)
1. 新しいプライベート ブラウザーを開き、次の URL に移動してサインインします。

    ```http
    https://{domainName}.ciamlogin.com/{tenantId}/oauth2/v2.0/authorize?client_id={App_to_enrich_ID}&response_type=id_token&redirect_uri=https://jwt.ms&scope=openid&state=12345&nonce=12345
    ```
2. `{domainName}`をドメイン名に置き換えます (例: `contoso`)。
3. `{tenantId}` は、テナント ID、テナント名、または検証済みドメイン名の 1 つに置き換えます。 たとえば、「 `contoso.onmicrosoft.com` 」のように入力します。
4. `{App_to_enrich_ID}`を *My Test アプリケーション* クライアント ID に置き換えます。
5. 構成したサインイン ユーザー フローを確認し、要求されたアクセス許可を受け入れます。
6. ログインすると、デコードされたトークンが `https://jwt.ms`に表示されます。 Azure関数からの要求がデコードされたトークン (例: `DateOfBirth`) に表示されることを検証します。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/custom-extension-tokenissuancestart-setup"} -->
## Azure Functionsでトークン発行イベントの REST API を作成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-setup
- Service: identity-platform
- Article date: 2025-05-04
- Summary: Azure Functions ライブラリの認証イベント トリガーを使用して、トークン発行開始イベントを使用するトリガー関数を作成する方法について説明します。

::: zone pivot="azure-portal"

この記事では、Azure ポータルでAzure Functionsを使用して、[token 発行開始イベント](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview#token-issuance-start-event-listener) を使用して REST API を作成する方法について説明します。 Azure関数アプリと、トークンの追加要求を返すことができる HTTP トリガー関数を作成します。

このビデオでは、カスタム クレーム プロバイダーを使用して外部システムからの要求をセキュリティ トークンMicrosoft Entraマッピングする手順について説明します。

### 前提条件

- [カスタム認証拡張機能の概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview)で説明されている概念の基本的な理解。
- Azure Functionsを作成できるAzure サブスクリプション。 既存のAzure アカウントをお持ちでない場合は、[無料試用版](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)にサインアップするか、[Visual Studio サブスクリプション](https://visualstudio.microsoft.com/subscriptions/)特典を使用して、[アカウント](https://account.windowsazure.com/Home/Index)を作成します。
- Microsoft Entra ID テナント。 この攻略ガイドには、顧客または従業員のどちらかのテナントを使用できます。

::: zone-end

::: zone pivot="nuget-library"

この記事では、[Microsoft を使用して、](https://github.com/Azure/azure-sdk-for-net/tree/main/sdk/entra/Microsoft.Azure.WebJobs.Extensions.AuthenticationEvents)[token 発行開始イベント](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview#token-issuance-start-event-listener)用の REST API を作成する方法について説明します。Azure。WebJobs.Extensions.AuthenticationEvents NuGet ライブラリを認証用に設定します。 Visual StudioまたはVisual Studio Codeで HTTP トリガー関数を作成し、認証用に構成し、Azure ポータルにデプロイします。この関数は、Azure Functions経由でアクセスできます。

### 前提条件

- [カスタム認証拡張機能の概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-overview)で説明されている概念の基本的な理解。
- Azure Functionsを作成できるAzure サブスクリプション。 既存のAzure アカウントをお持ちでない場合は、[無料試用版](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)にサインアップするか、[Visual Studio サブスクリプション](https://visualstudio.microsoft.com/subscriptions/)特典を使用して、[アカウント](https://account.windowsazure.com/Home/Index)を作成します。
- Microsoft Entra ID テナント。 この攻略ガイドには、顧客または従業員のどちらかのテナントを使用できます。
- 次のいずれかの IDE と構成。
    - Visual Studio に [Visual Studio 用の Azure 開発ワークロード](https://learn.microsoft.com/ja-jp/dotnet/azure/configure-visual-studio) が設定されています。
    - Visual Studio Code、[Azure Functions](https://marketplace.visualstudio.com/items?itemName=ms-azuretools.vscode-azurefunctions) 拡張機能が有効になっています。

注

[Microsoft。Azure。WebJobs.Extensions.AuthenticationEvents](https://github.com/Azure/azure-sdk-for-net/tree/main/sdk/entra/Microsoft.Azure.WebJobs.Extensions.AuthenticationEvents) NuGet ライブラリは現在プレビュー段階です。 この記事の手順は変更される可能性があります。 トークン発行開始イベントを実装する一般提供の実装については、Azure ポータルを使用して行うことができます。

::: zone-end

::: zone pivot="azure-portal"

### Azure関数アプリを作成する

Azure ポータルで、HTTP トリガー関数の作成を続行する前に、Azure関数アプリとそれに関連付けられているリソースを作成します。

1. [Azure ポータル](https://portal.azure.com)に少なくとも [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer) および [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)としてサインインします。
2. Azure ポータル メニューまたは **Home** ページで、**リソースの作成**を選択します。
3. **関数アプリ**を検索して選択し、[**作成**] を選択します。
4. **[関数アプリの作成]** ページで、**[従量課金]** を選択し、**[選択]** を選択します。
5. [ **関数アプリの作成 (従量課金)]** ページの [ **基本** ] タブで、次の表に示すように設定を使用して関数アプリを作成します。

    | 設定 | 提案された値 | 説明 |
    | --- | --- | --- |
    | **Subscription** | あなたのサブスクリプション | 新しい関数アプリを作成するサブスクリプション。 |
    | **[リソース グループ](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview)** | *myResourceGroup* | 関数アプリを作成する、既存のリソース グループを選ぶか、新しいリソース グループの名前を選びます。 |
    | **関数アプリ名** | グローバルに一意の名前 | 新しい関数アプリを識別する名前。 有効な文字は、`a-z` (大文字と小文字の区別をしない)、`0-9`、および `-`です。 |
    | **コードまたはコンテナー イメージをデプロイする** | Code | コード ファイルまたは Docker コンテナーの発行オプション。 このチュートリアルでは、[ **コード**] を選択します。 |
    | **ランタイム スタック** | .NET | 好みのプログラミング言語。 このチュートリアルでは、**.NET** を選択します。 |
    | **バージョン** | 8 (LTS) 進行中 | .NET ランタイムのバージョン。 インプロセスは、ポータルで関数の作成と変更ができることを意味します。このガイドでは、これが推奨されます |
    | **リージョン** | 優先リージョン | 自分の近く、または関数がアクセスできる他のサービスの近くの[リージョン](https://azure.microsoft.com/regions/)を選択します。 |
    | **オペレーティング システム** | Windows | オペレーティング システムは、ランタイム スタックの選択に基づいて、事前に自動的に選択されます。 |

    [Image: 開発環境とテンプレートを選択する方法を示すスクリーンショット。]
6. [ **確認と作成** ] を選択してアプリ構成の選択を確認し、[ **作成**] を選択します。 デプロイには、数分かかります。
7. デプロイが完了したら、[ **リソースに移動** ] を選択して新しい関数アプリを表示します。

### HTTP トリガー関数を作成する

Azure関数アプリが作成されたら、アプリ内に HTTP トリガー関数を作成します。 HTTP トリガーを使用すると、HTTP 要求を使用して関数を呼び出すことができます。これは、Microsoft Entraカスタム認証拡張機能によって参照されます。

1. 関数アプリの **Overview** ページ内で、**Functions** ペインを選択し、**Create in Azure portal** の下にある **Create function** を選択します。
2. [ **関数の作成** ] ウィンドウで、 **HTTP トリガー** 関数の種類を選択し、[ **次へ**] を選択します。
3. **[テンプレートの詳細**] で、[*関数名関数*] プロパティに**「CustomAuthenticationExtensionsAPI**」と入力し、**承認レベル**として **[関数**] を選択します。
4. **を選択して**を作成します。

### 関数を編集する

このコードは、受信 JSON オブジェクトを読み取り、Microsoft Entra ID[JSON オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-reference)を API に送信します。 この例では、関連付け ID の値を読み取ります。 次に、元の`CorrelationId`、Azure関数の`ApiVersion`、Microsoft Entra IDに返される`DateOfBirth`、`CustomRoles`など、カスタマイズされた要求のコレクションが返されます。

1. メニューの [ **開発者**] で、[ **コード + テスト**] を選択します。
2. コード全体を次のスニペットに置き換え、[保存] を選択 **します**。

    ```csharp
    #r "Newtonsoft.Json"
    using System.Net;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.Extensions.Primitives;
    using Newtonsoft.Json;
    public static async Task<IActionResult> Run(HttpRequest req, ILogger log)
    {
        log.LogInformation("C# HTTP trigger function processed a request.");
        string requestBody = await new StreamReader(req.Body).ReadToEndAsync();
        dynamic data = JsonConvert.DeserializeObject(requestBody);
    
        // Read the correlation ID from the Microsoft Entra request    
        string correlationId = data?.data.authenticationContext.correlationId;
    
        // Claims to return to Microsoft Entra
        ResponseContent r = new ResponseContent();
        r.data.actions[0].claims.CorrelationId = correlationId;
        r.data.actions[0].claims.ApiVersion = "1.0.0";
        r.data.actions[0].claims.DateOfBirth = "01/01/2000";
        r.data.actions[0].claims.CustomRoles.Add("Writer");
        r.data.actions[0].claims.CustomRoles.Add("Editor");
        return new OkObjectResult(r);
    }
    public class ResponseContent{
        [JsonProperty("data")]
        public Data data { get; set; }
        public ResponseContent()
        {
            data = new Data();
        }
    }
    public class Data{
        [JsonProperty("@odata.type")]
        public string odatatype { get; set; }
        public List<Action> actions { get; set; }
        public Data()
        {
            odatatype = "microsoft.graph.onTokenIssuanceStartResponseData";
            actions = new List<Action>();
            actions.Add(new Action());
        }
    }
    public class Action{
        [JsonProperty("@odata.type")]
        public string odatatype { get; set; }
        public Claims claims { get; set; }
        public Action()
        {
            odatatype = "microsoft.graph.tokenIssuanceStart.provideClaimsForToken";
            claims = new Claims();
        }
    }
    public class Claims{
        [JsonProperty(NullValueHandling = NullValueHandling.Ignore)]
        public string CorrelationId { get; set; }
        [JsonProperty(NullValueHandling = NullValueHandling.Ignore)]
        public string DateOfBirth { get; set; }
        public string ApiVersion { get; set; }
        public List<string> CustomRoles { get; set; }
        public Claims()
        {
            CustomRoles = new List<string>();
        }
    }
    ```
3. 上部のメニューから [ **関数 URL の取得**] を選択し、 **URL** 値をコピーします。 この関数 URL は、カスタム認証拡張機能を設定するときに使用できます。

::: zone-end

::: zone pivot="nuget-library"

### Azure Function アプリを作成してビルドする

このステップでは、お使いの IDE を使用して HTTP トリガー関数 API を作成し、必要な NuGet パッケージをインストールし、サンプル コードでコピーします。 プロジェクトをビルドし、関数をローカルで実行して関数の URL を抽出します。

#### アプリケーションを作成する

Azure関数アプリを作成するには、次の手順に従います。

## [Visual Studio](#tab/visual-studio)
1. Visual Studioを開き、**新しいプロジェクトの作成** を選択します。
2. **Azure Functions** を検索して選択し、 **Next** を選択します。
3. *プロジェクトに AuthEventsTrigger* などの名前を付けます。 ソリューション名とプロジェクト名を一致させることをお勧めします。
4. プロジェクトの 場所 を選択します。 **次へ**を選択します。
5. ターゲット フレームワークとして **.NET 8.0 (長期サポート)** を選択します。
6. *[関数*の種類] として **[Http トリガー**] を選択し、その**承認レベル**が *[関数]* に設定されます。 **を選択して**を作成します。
7. **Solution Explorer**で、*Function1.cs* ファイルの名前を *AuthEventsTrigger.cs* に変更し、名前変更の提案をそのまま使用します。

## [Visual Studio Code](#tab/visual-studio-code)
1. Visual Studio Codeを開きます。
2. **エクスプローラー** ウィンドウで **[新しいフォルダー]** アイコンを選択し、プロジェクトの新しいフォルダー (*AuthEventsTrigger* など) を作成します。
3. 画面の左側にあるAzure拡張機能アイコンを選択します。 まだサインインしていない場合は、Azure アカウントにサインインします。
4. **Workspace** バーで、**Azure Functions** アイコン &gt;**新しいプロジェクトの作成**を選択します。

    [Image: Visual Studio CodeでAzure関数を追加する方法を示すスクリーンショット。]
5. 上部のバーで、プロジェクトを作成する場所を選択します。
6. 言語として **C#** を選択し、.NET ランタイムとして **.NET 6.0 LTS** を選択します。
7. テンプレートとして **HTTP トリガー** を選択します。
8. プロジェクトの名前 ( *AuthEventsTrigger* など) を指定します。
9. **名前空間として Company.Function** を受け入れ、**AccessRights** を *Function* に設定します。

---

#### NuGet パッケージのインストールとプロジェクトのビルド

プロジェクトを作成したら、必要な NuGet パッケージをインストールし、プロジェクトをビルドする必要があります。

## [Visual Studio](#tab/visual-studio)
1. Visual Studioの上部メニューで、**Project**、**Manage NuGet パッケージ**を選択します。
2. **Browse** タブを選択し、右側のウィンドウで *Microsoft.Azure.WebJobs.Extensions.AuthenticationEvents* を検索して選択します。 **インストール**を選択します。
3. 表示されるポップアップで変更を適用し、承認します。

## [Visual Studio Code](#tab/visual-studio-code)
1. Visual Studio Codeで **Terminal** を開き、プロジェクト フォルダーに移動します。
2. コンソールに次のコマンドを入力して、*Microsoft.Azure.WebJobs.Extensions.AuthenticationEvents* NuGet パッケージをインストールします。

    ```console
    dotnet add package Microsoft.Azure.WebJobs.Extensions.AuthenticationEvents
    ```

---

#### サンプル コードを追加する

関数 API は、トークンに対する追加クレームのソースです。 この記事では、サンプル アプリの値をハードコーディングしています。 運用環境では、外部データ ストアからユーザーに関する情報を取り込むことができます。 既存のプロパティについては、 [WebJobsAuthenticationEventsContext クラス](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.azure.webjobs.extensions.authenticationevents.tokenissuancestart.webjobsauthenticationeventscontext#properties) を参照してください。

*AuthEventsTrigger.cs* ファイルで、ファイルの内容全体を次のコードに置き換えます。

```csharp
using System;
using Microsoft.Azure.WebJobs;
using Microsoft.Extensions.Logging;
using Microsoft.Azure.WebJobs.Extensions.AuthenticationEvents.TokenIssuanceStart;
using Microsoft.Azure.WebJobs.Extensions.AuthenticationEvents;

namespace AuthEventsTrigger
{
    public static class AuthEventsTrigger
    {
        [FunctionName("onTokenIssuanceStart")]
        public static WebJobsAuthenticationEventResponse Run(
            [WebJobsAuthenticationEventsTrigger] WebJobsTokenIssuanceStartRequest request, ILogger log)
        {
            try
            {
                // Checks if the request is successful and did the token validation pass
                if (request.RequestStatus == WebJobsAuthenticationEventsRequestStatusType.Successful)
                {
                    // Fetches information about the user from external data store
                    // Add new claims to the token's response
                    request.Response.Actions.Add(
                        new WebJobsProvideClaimsForToken(
                            new WebJobsAuthenticationEventsTokenClaim("dateOfBirth", "01/01/2000"),
                            new WebJobsAuthenticationEventsTokenClaim("customRoles", "Writer", "Editor"),
                            new WebJobsAuthenticationEventsTokenClaim("apiVersion", "1.0.0"),
                            new WebJobsAuthenticationEventsTokenClaim(
                                "correlationId", 
                                request.Data.AuthenticationContext.CorrelationId.ToString())));
                }
                else
                {
                    // If the request fails, such as in token validation, output the failed request status, 
                    // such as in token validation or response validation.
                    log.LogInformation(request.StatusMessage);
                }
                return request.Completed();
            }
            catch (Exception ex) 
            { 
                return request.Failed(ex);
            }
        }
    }
}
```

#### プロジェクトをローカルで構築して実行する

プロジェクトが作成され、サンプル コードが追加されました。 お使いの IDE を使用して、プロジェクトをローカルでビルドして実行し、ローカル関数の URL を抽出する必要があります。

## [Visual Studio](#tab/visual-studio)
1. 上部のメニューで **[ビルド** ] に移動し、[ **ソリューションのビルド**] を選択します。
2. **F5** キーを押すか、上部のメニューから *AuthEventsTrigger* を選択して関数を実行します。
3. 関数の実行時にポップアップ表示されるターミナルから Function **URL を** コピーします。 これは、カスタム認証拡張機能を設定するときに使用できます。

## [Visual Studio Code](#tab/visual-studio-code)
1. 上部のメニューで、[**実行**] &gt;**[デバッグの開始**] を選択するか**、F5** キーを押して関数を実行します。
2. ターミナルで、表示される **関数の URL を** コピーします。 これは、カスタム認証拡張機能を設定するときに使用できます。

---

### 関数をローカルで実行する (推奨)

Azureにデプロイする前に、関数をローカルでテストすることをお勧めします。 Microsoft Entra ID が REST API に送信する要求を模倣するダミーの JSON 本文を使用できます。 お好みの API テスト ツールを使用して、関数を直接呼び出します。

1. IDE で *local.settings.json* を開き、コードを次の JSON に置き換えます。 ローカル テストの目的で、 `"AuthenticationEvents__BypassTokenValidation"` を `true` に設定できます。

    ```json
    {
      "IsEncrypted": false,
      "Values": {
        "AzureWebJobsStorage": "",
        "AzureWebJobsSecretStorageType": "files",
        "FUNCTIONS_WORKER_RUNTIME": "dotnet",
        "AuthenticationEvents__BypassTokenValidation" : true
      }
    }
    ```
2. 優先する API テスト ツールを使用して、新しい HTTP 要求を作成し、 **HTTP メソッド** を `POST` に設定します。
3. REST API に送信される Microsoft Entra ID のリクエストを模倣する次の JSON 本文を使用します。

    ```json
    {
        "type": "microsoft.graph.authenticationEvent.tokenIssuanceStart",
        "source": "/tenants/aaaabbbb-0000-cccc-1111-dddd2222eeee/applications/00001111-aaaa-2222-bbbb-3333cccc4444",
        "data": {
            "@odata.type": "microsoft.graph.onTokenIssuanceStartCalloutData",
            "tenantId": "aaaabbbb-0000-cccc-1111-dddd2222eeee",
            "authenticationEventListenerId": "11112222-bbbb-3333-cccc-4444dddd5555",
            "customAuthenticationExtensionId": "22223333-cccc-4444-dddd-5555eeee6666",
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
                "resourceServicePrincipal": {
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
4. [ **送信]** を選択すると、次のような JSON 応答が表示されます。

    ```json
    {
        "data": {
            "@odata.type": "microsoft.graph.onTokenIssuanceStartResponseData",
            "actions": [
                {
                    "@odata.type": "microsoft.graph.tokenIssuanceStart.provideClaimsForToken",
                    "claims": {
                        "customClaim1": "customClaimValue1",
                        "customClaim2": [
                            "customClaimString1",
                            "customClaimString2" 
                        ]
                    }
                }
    
            ]
        }
    }
    ```

### 関数をデプロイしてAzureに発行する

この関数は、IDE を使用してAzureにデプロイする必要があります。 関数を発行できるように、Azure アカウントに正しくサインインしていることを確認します。

## [Visual Studio](#tab/visual-studio)
1. Solution Explorerでプロジェクトを右クリックし、**Publish** を選択します。
2. **Target** で **Azure** を選択し、 **Next** を選択します。
3. **Azure Function App (Windows)** の**特定のターゲット** を選択し、**Azure Function App (Windows)** を選択してから、**次へ** を選択します。
4. **関数インスタンス**で、[**サブスクリプション名**] ドロップダウンを使用して、新しい関数アプリを作成するサブスクリプションを選択します。
5. 新しい関数アプリを発行する場所を選択し、[ **新規作成**] を選択します。
6. **Function App (Windows)** ページで、次の表に示すように関数アプリの設定を使用し、**Create** を選択します。

    | 設定 | 提案された値 | 説明 |
    | --- | --- | --- |
    | **名前** | グローバルに一意の名前 | 新しい関数アプリを識別する名前。 有効な文字は、`a-z` (大文字と小文字の区別をしない)、`0-9`、および `-`です。 |
    | **Subscription** | あなたのサブスクリプション | 新しい関数アプリが作成されるサブスクリプション。 |
    | **[リソース グループ](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/overview)** | *myResourceGroup* | 既存のリソース グループを選択するか、関数アプリを作成する新しいリソース グループに名前を付けます。 |
    | **プランの種類** | "従量課金 (サーバーレス)" | 関数アプリにどのようにリソースが割り当てられるかを定義するホスティング プラン。 |
    | **場所** | 優先リージョン | 自分の近く、または関数がアクセスできる他のサービスの近くの[リージョン](https://azure.microsoft.com/regions/)を選択します。 |
    | **Azure Storage** | ストレージ アカウント | Functions ランタイムでは、Azureストレージ アカウントが必要です。 [新規] を選択して汎用ストレージ アカウントを構成します。 |
    | **Application Insights** | *デフォルト* | Azure Monitorの特徴。 これは自動選択です。使用するものを選択するか、新しいものを構成します。 |
7. 関数アプリがデプロイされるまでしばらく待ちます。 ウィンドウが閉じたら、[ **完了]** を選択します。
8. 新しい **発行** ウィンドウが開きます。 上部にある [発行] を選択 **します**。 関数アプリがデプロイされるまで数分待ち、Azure ポータルに表示されます。

## [Visual Studio Code](#tab/visual-studio-code)
1. **Azure**拡張機能アイコンを選択します。 [ **リソース]** で、 **+** アイコンを選択して **リソースを作成します**。
2. Azure で **関数アプリを作成** を選択します。 関数アプリを設定するには、次の設定を使用します。
3. 関数アプリに *AuthEventsTriggerNuGet* などの名前を付けて、 **Enter キー**を押します。
4. **.NET 6 (LTS) In-Process** ランタイム スタックを選択します。
5. 関数アプリの場所 ( *米国東部*など) を選択します。
6. 関数アプリがデプロイされるまで数分待ち、Azure ポータルに表示されます。

---

### Azure関数の認証を構成する

Azure関数の認証を設定するには、次の 3 つの方法があります。

- 環境変数を使用してAzure ポータルで認証を設定する (推奨)
- を使用してコードで認証を設定する `WebJobsAuthenticationEventsTriggerAttribute`
- [Azure App サービスの認証と承認](https://learn.microsoft.com/ja-jp/azure/app-service/configure-authentication-provider-aad?tabs=workforce-tenant)

既定では、コードは環境変数を使用してAzure ポータルで認証用に設定されています。 環境変数を実装する方法を選択するには、次のタブを使用します。または、組み込みの[Azure Appサービスの認証と承認](https://learn.microsoft.com/ja-jp/azure/app-service/overview-authentication-authorization)を参照してください。 環境変数を設定する場合は、次の値を使用します。

| 名前 | 値 |
| --- | --- |
| *AuthenticationEvents\_\_AudienceAppId* | *トークン発行イベントのカスタム要求プロバイダーの構成*に関するトピックで設定されているカスタム[認証拡張機能アプリ ID](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration) |
| *AuthenticationEvents\_\_AuthorityUrl* | • ワークフォース テナント `https://login.microsoftonline.com/<tenantID>` • 外部テナント `https://<mydomain>.ciamlogin.com/<tenantID>` |
| *AuthenticationEvents\_\_AuthorizedPartyAppId* | `99045fe1-7639-4a75-9d4a-577b6ca3810f` または別の承認されたパーティ |

## [Azure ポータルで認証を設定します](#tab/azure-portal)
#### 環境変数を使用してAzure ポータルで認証を設定する

1. [Azure ポータル](https://portal.azure.com)に少なくとも [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer) または [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)としてサインインします。
2. 作成した関数アプリに移動し、[ **設定]** で [ **構成**] を選択します。
3. [ **アプリケーション設定]** で [ **新しいアプリケーション設定** ] を選択し、テーブルとそれに関連付けられている値から環境変数を追加します。
4. [ **保存] を** 選択して、アプリケーション設定を保存します。

## [コードで認証を設定する](#tab/nuget-library)
#### コード内で`WebJobsAuthenticationEventsTriggerAttribute`を使用して認証を設定する

1. IDE で *AuthEventsTrigger.cs* ファイルを開きます。
2. 次のスニペットに示すように、`WebJobsAuthenticationEventsTriggerAttribute`、`AuthorityUrl`、`AudienceAppId`プロパティを含む`AuthorizedPartyAppId`を変更します。

```csharp
    [FunctionName("onTokenIssuanceStart")]
    public static WebJobsAuthenticationEventResponse Run(
        [WebJobsAuthenticationEventsTriggerAttribute(
            AudienceAppId = "Enter custom authentication extension app ID here",
            AuthorityUrl = "Enter authority URI here", 
            AuthorizedPartyAppId = "Enter the Authorized Party App Id here")]WebJobsTokenIssuanceStartRequest request, ILogger log)
```

---

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/custom-extension-troubleshoot"} -->
## カスタム認証拡張機能のトラブルシューティング - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-troubleshoot
- Service: identity-platform
- Article date: 2024-04-10
- Summary: カスタム クレーム プロバイダー API のトラブルシューティングと監視。  ログ記録と Microsoft Entra サインイン ログを使用して、カスタム クレーム プロバイダー API のエラーと問題を見つける方法について説明します。

認証イベントと [カスタム クレーム プロバイダーを](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview) 使用すると、外部システムと統合することで、Microsoft Entra 認証エクスペリエンスをカスタマイズできます。 たとえば、カスタム クレーム プロバイダー API を作成し、外部ストアからの要求を含むトークンを受信するように [OpenID Connect アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration) を構成できます。

### Error behavior

API 呼び出しが失敗すると、エラーの動作は次のようになります。

- OpenId Connect アプリの場合 - Microsoft Entra ID は、ユーザーをエラーとともにクライアント アプリケーションにリダイレクトします。 トークンは作成されません。
- SAML アプリの場合 - Microsoft Entra ID は、認証エクスペリエンスでユーザーにエラー画面を表示します。 ユーザーはクライアント アプリケーションにリダイレクトされません。

アプリケーションまたはユーザーに送り返されるエラー コードは汎用的です。 To troubleshoot, check the sign-in logs for the error codes.

### Logging

カスタム クレーム プロバイダー REST API エンドポイントに関する問題をトラブルシューティングするには、REST API でログ記録を処理する必要があります。 Azure Functions やその他の API 開発プラットフォームには、詳細なログ記録ソリューションが用意されています。 それらのソリューションを使用して、API の動作に関する詳細情報を取得し、API ロジックのトラブルシューティングを行います。

### Microsoft Entra サインイン ログ

REST API ログに加えて [Microsoft Entra サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins) を使用したり、環境診断ソリューションをホストしたりすることもできます。 Microsoft Entra サインイン ログを使用すると、ユーザーのサインインに影響する可能性があるエラーを見つけることができます。Microsoft Entra サインイン ログには、API が Microsoft Entra ID によって呼び出されたときに発生した HTTP 状態、エラー コード、実行時間、再試行回数に関する情報が表示されます。

Microsoft Entra sign-in logs also integrate with [Azure Monitor](https://learn.microsoft.com/ja-jp/azure/azure-monitor/). アラートと監視を設定し、データを視覚化し、セキュリティ情報イベント管理 (SIEM) ツールと統合できます。 たとえば、エラーの数が選択した特定のしきい値を超えた場合の通知を設定できます。

Microsoft Entraサインイン ログにアクセスするには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Browse to **Entra ID**&gt;**Enterprise apps**.
3. Select **Sign-in logs**, and then select the latest sign-in log.
4. For more details, select the **Authentication Events** tab. Information related to the custom authentication extension REST API call is displayed, including any error codes.

    [Image: 認証イベント情報を示すスクリーンショット。]

### エラー コードのリファレンス

次の表を使用して、エラー コードを診断します。

| Error code | Error name | Description |
| --- | --- | --- |
| 1003000 | EventHandlerUnexpectedError | イベント ハンドラーの処理時に予期しないエラーが発生しました。 |
| 1003001 | CustomExtensionUnexpectedError | カスタム拡張機能 API の呼び出し中に予期しないエラーが発生しました。 |
| 1003002 | CustomExtensionInvalidHTTPStatus | カスタム拡張 API から無効な HTTP 状態コードが返されました。 API から、そのカスタム拡張機能の種類に対して定義されている「受理されました」ステータス コードが返されることを確認します。 |
| 1003003 | CustomExtensionInvalidResponseBody | カスタム拡張機能の応答本文の解析で問題が発生しました。 API 応答本文のスキーマが、そのカスタム拡張機能の種類に対して受け入れ可能であることを確認します。 |
| 1003004 | CustomExtensionThrottlingError | カスタム拡張機能の要求が多すぎます。 この例外は、スロットリングの制限に達したときにカスタム拡張 API 呼び出しに対してスローされます。 |
| 1003005 | CustomExtensionTimedOut | カスタム拡張機能が、許可されたタイムアウト内に応答しませんでした。 カスタム拡張機能の構成済みタイムアウト内に API が応答していることを確認します。 アクセス トークンが無効であることを示している可能性もあります。 REST API を直接呼び出す手順に従います。 |
| 1003006 | CustomExtensionInvalidResponseContentType | カスタム拡張機能の応答コンテンツ タイプが 'application/json' ではありません。 |
| 1003007 | CustomExtensionNullClaimsResponse | カスタム拡張 API が null の要求バッグで応答しました。 |
| 1003008 | CustomExtensionInvalidResponseApiSchemaVersion | カスタム拡張 API が、呼び出されたのと同じ apiSchemaVersion で応答しませんでした。 |
| 1003009 | CustomExtensionEmptyResponse | 予期していないのに、カスタム拡張 API の応答本文が null でした。 |
| 1003010 | CustomExtensionInvalidNumberOfActions | カスタム拡張 API 応答に、そのカスタム拡張機能の種類でサポートされているものとは異なる数のアクションが含まれていました。 |
| 1003011 | CustomExtensionNotFound | イベント リスナーに関連付けられているカスタム拡張機能が見つかりませんでした。 |
| 1003012 | CustomExtensionInvalidActionType | カスタム拡張機能が、そのカスタム拡張機能の種類に対して定義されている無効なアクションの種類を返しました。 |
| 1003014 | CustomExtensionIncorrectResourceIdFormat | The *identifierUris* property in the manifest for the application registration for the custom extension, should be in the format of "api://{fully qualified domain name}/{appid}. |
| 1003015 | CustomExtensionDomainNameDoesNotMatch | カスタム拡張機能の targetUrl と resourceId には、同じ完全修飾ドメイン名が必要です。 |
| 1003016 | CustomExtensionResourceServicePrincipalNotFound | カスタム拡張機能の resourceId の appId は、テナント内の実際のサービス プリンシパルに対応している必要があります。 |
| 1003017 | CustomExtensionClientServicePrincipalNotFound | カスタム拡張機能のリソース サービス プリンシパルがテナントに見つかりません。 |
| 1003018 | CustomExtensionClientServiceDisabled | カスタム拡張機能のリソース サービス プリンシパルがテナントで無効になっています。 |
| 1003019 | CustomExtensionResourceServicePrincipalDisabled | カスタム拡張機能のリソース サービス プリンシパルがテナントで無効になっています。 |
| 1003020 | CustomExtensionIncorrectTargetUrlFormat | ターゲット URL の形式が正しくありません。 https で始まる有効な URL である必要があります。 |
| 1003021 | CustomExtensionPermissionNotGrantedToServicePrincipal | サービス プリンシパルには、Microsoft Graph の CustomAuthenticationExtensions.Receive.Payload アプリ ロール (アプリケーションのアクセス許可とも呼ばれます) に関する管理者の同意がありません。これは、アプリがカスタム認証拡張機能の HTTP 要求を受信するために必須です。 |
| 1003022 | CustomExtensionMsGraphServicePrincipalDisabledOrNotFound | MS Graph サービス プリンシパルが無効になっているか、このテナントで見つかりません。 |
| 1003023 | CustomExtensionBlocked | カスタム拡張機能に使用されるエンドポイントは、サービスによってブロックされています。 |
| 1003024 | CustomExtensionResponseSizeExceeded | カスタム拡張機能の応答サイズが上限を超えました。 |
| 1003025 | CustomExtensionResponseClaimsSizeExceeded | カスタム拡張機能応答の要求の合計サイズが上限を超えました。 |
| 1003026 | CustomExtensionNullOrEmptyClaimKeyNotSupported | カスタム拡張 API が、null または空のキーを含む要求で応答しました。 |
| 1003027 | CustomExtensionConnectionError | カスタム拡張 API への接続中にエラーが発生しました。 |

### REST API を直接呼び出してください

REST API は Microsoft Entra アクセス トークンによって保護されています。 次の方法で API をテストできます:

- Obtaining an access token with an [application registration](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration#12-grant-admin-consent) associated with the custom authentication extensions
- API テスト ツールを使用して API をローカルでテストします。

## [API テスト ツール](#tab/api-testing-tools)
1. For local development and testing purposes, open *local.settings.json* and replace the code with the following JSON:

    ```json
    {
      "IsEncrypted": false,
      "Values": {
        "AzureWebJobsStorage": "",
        "AzureWebJobsSecretStorageType": "files",
        "FUNCTIONS_WORKER_RUNTIME": "dotnet",
        "AuthenticationEvents__BypassTokenValidation" : false
      }
    }
    ```

    Note

    If you used the [Microsoft.Azure.WebJobs.Extensions.AuthenticationEvents](https://github.com/Azure/azure-sdk-for-net/tree/main/sdk/entra/Microsoft.Azure.WebJobs.Extensions.AuthenticationEvents) NuGet package, be sure to set `"AuthenticationEvents__BypassTokenValidation" : true` for local testing purposes.
2. Using your preferred API testing tool, create a new HTTP request and set the **HTTP method** to `POST`.
3. Microsoft Entra ID から REST API に送信される要求を模倣する次の JSON 本文を使用します。

    ```json
    {
        "type": "microsoft.graph.authenticationEvent.tokenIssuanceStart",
        "source": "/tenants/aaaabbbb-0000-cccc-1111-dddd2222eeee/applications/00001111-aaaa-2222-bbbb-3333cccc4444",
        "data": {
            "@odata.type": "microsoft.graph.onTokenIssuanceStartCalloutData",
            "tenantId": "aaaabbbb-0000-cccc-1111-dddd2222eeee",
            "authenticationEventListenerId": "11112222-bbbb-3333-cccc-4444dddd5555",
            "customAuthenticationExtensionId": "22223333-cccc-4444-dddd-5555eeee6666",
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
                "resourceServicePrincipal": {
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

    Tip

    If you're using an access token obtained from Microsoft Entra ID, select **Authorization** and then select **Bearer token**, then paste the access token you received from Microsoft Entra ID.
4. Select **Send**, and you should receive a JSON response similar to the following:

    ```json
    {
        "data": {
            "@odata.type": "microsoft.graph.onTokenIssuanceStartResponseData",
            "actions": [
                {
                    "@odata.type": "microsoft.graph.tokenIssuanceStart.provideClaimsForToken",
                    "claims": {
                        "customClaim1": "customClaimValue1",
                        "customClaim2": [
                            "customClaimString1",
                            "customClaimString2" 
                        ]
                    }
                }
    
            ]
        }
    }
    ```

## [アクセス トークンを取得する](#tab/obtain-an-access-token)
アクセス トークンを取得したら、それを HTTP `Authorization` ヘッダーを渡します。 アクセス トークンを取得するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Browse to **Entra ID**&gt;**App registrations**.
3. 以前に*トークン発行イベント用のカスタム要求プロバイダーの設定*で構成した[Azure Functions 認証イベント API](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration#step-1-register-a-custom-authentication-extension)のアプリ登録を選択します。
4. Copy the [application ID](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration#12-grant-admin-consent).
5. アプリ シークレットを作成していない場合は、次の手順に従います。

    1. **[証明書とシークレット**&gt;**クライアント シークレット**&gt;**新しいクライアント シークレット**を選択します。
    2. クライアント シークレットの説明を追加します。
    3. シークレットの有効期限を選択するか、カスタムの有効期間を指定します。
    4. Select **Add**.
    5. Record the **secret's value** for use in your client application code. このページからの移動後は、このシークレットの値は "二度と表示されません"。
6. メニューから [API の **公開** ] を選択し、 **アプリケーション ID URI** の値をコピーします。 たとえば、`api://contoso.azurewebsites.net/aaaabbbb-0000-cccc-1111-dddd2222eeee` のようにします。
7. 任意の API テスト ツールを開き、新しい HTTP クエリを作成します。
8. Change the **HTTP method** to `POST`.
9. 次の URL を入力します。 テナントIDを`{tenantID}`に置き換えてください。

    ```http
    https://login.microsoftonline.com/{tenantID}/oauth2/v2.0/token
    ```
10. Under the **Body**, select **form-data** and add the following keys:

    | Key | Value |
    | --- | --- |
    | `grant_type` | `client_credentials` |
    | `client_id` | The **Client ID** of your application. |
    | `client_secret` | The **Client Secret** of your application. |
    | `scope` | **アプリケーションのアプリケーション ID URI を**指定し、`.default`を追加します。 たとえば、`api://contoso.azurewebsites.net/aaaabbbb-0000-cccc-1111-dddd2222eeee/.default` のように指定します。 |
11. HTTP クエリを実行し、`access_token` を https://jwt.ms Web アプリにコピーします。
12. `iss`と[、API で構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration#step-4-protect-your-azure-function)した発行者名を比較します。
13. `aud`と[、API で構成した](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration#step-4-protect-your-azure-function)クライアント ID を比較します。

---

### 一般的なパフォーマンスの大幅な向上

最も一般的な問題の 1 つは、カスタム クレーム プロバイダー API が 2 秒以内に応答しないでタイムアウトになることです。 REST API が後続の再試行で応答しない場合、認証は失敗します。 REST API のパフォーマンスを向上させるには、以下の推奨事項に従います。

1. API がダウンストリーム API にアクセスする場合は、すべての実行で新しいトークンを取得する必要はなくなるように、これらの API の呼び出しに使用されるアクセス トークンをキャッシュします。
2. パフォーマンスの問題は、多くの場合、ダウンストリーム サービスに関連しています。 ログ記録を追加します。これにより、ダウンストリーム サービスへの呼び出しの処理時間が記録されます。
3. クラウド プロバイダーを使用して API をホストしている場合は、API を常に "ウォーム" に保つホスティング プランを使用します。 Azure Functions の場合は、 [Premium プランまたは専用プラン](https://learn.microsoft.com/ja-jp/azure/azure-functions/functions-scale)を使用できます。
4. 認証[の自動統合テストを実行](https://learn.microsoft.com/ja-jp/entra/identity-platform/test-automate-integration-testing)します。 API テスト ツールを使用して、API のパフォーマンスのみをテストすることもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/custom-rbac-for-developers"} -->
## アプリケーション開発者向けのカスタム ロールベースのアクセス制御 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-rbac-for-developers
- Service: identity-platform
- Article date: 2023-01-06
- Summary: カスタム RBAC とは何か、およびアプリケーションに実装することが重要な理由について説明します。

ロールベースのアクセス制御 (RBAC) を使用すると、特定のユーザーまたはグループがリソースにアクセスして管理するための特定のアクセス許可を持つことができます。 アプリケーション RBAC は [、Azure ロールベースのアクセス制御](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/overview) と [Microsoft Entra ロールベースのアクセス制御](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview#understand-azure-ad-role-based-access-control)とは異なります。 Azure カスタム ロールと組み込みロールはどちらも Azure RBAC の一部であり、Azure リソースの管理に使用されます。 Microsoft Entra RBAC は、Microsoft Entra リソースを管理するために使用されます。 この記事では、アプリケーション固有の RBAC について説明します。 アプリケーション固有の RBAC の実装の詳細については、「 [アプリケーションにアプリ ロールを追加し、トークンで受け取る方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)」を参照してください。

### ロールの定義

RBAC は、アプリケーションで承認を適用するための一般的なメカニズムです。 組織が RBAC を使用する場合、アプリケーション開発者は、個々のユーザーまたはグループを承認するのではなく、ロールを定義します。 管理者は、さまざまなユーザーとグループにロールを割り当てて、コンテンツと機能にアクセスできるユーザーを制御できます。

RBAC は、アプリケーション開発者がリソースとその使用状況を管理するのに役立ちます。 RBAC を使用すると、アプリケーション開発者は、ユーザーがアクセスできるアプリケーションの領域を制御することもできます。 管理者は、ユーザー *割り当て必須* プロパティを使用して、アプリケーションにアクセスできるユーザーを制御できます。 開発者は、アプリケーション内の特定のユーザーと、アプリケーション内でユーザーが実行できる操作を考慮する必要があります。

アプリケーション開発者は、まず、Microsoft Entra 管理センターのアプリケーションの登録セクション内にロール定義を作成します。 ロール定義には、そのロールに割り当てられているユーザーに返される値が含まれます。 開発者は、この値を使用してアプリケーション ロジックを実装し、ユーザーがアプリケーションで実行できることとできないことを判断できます。

### RBAC オプション

アプリケーションにロールベースのアクセス制御承認を含める場合は、次のガイダンスを適用する必要があります。

- アプリケーションの承認ニーズに必要なロールを定義します。
- 認証されたユーザーに関連するロールを適用、格納、および取得します。
- 現在のユーザーに割り当てられているロールに基づいて、アプリケーションの動作を決定します。

ロールが定義されると、Microsoft ID プラットフォームでは、認証されたユーザーのロール情報の適用、格納、取得に使用できるいくつかの異なるソリューションがサポートされます。 これらのソリューションには、アプリ ロール、Microsoft Entra グループ、ユーザー ロール情報のカスタム データストアの使用が含まれます。

開発者は、ロールの割り当てをアプリケーションのアクセス許可として解釈する方法について、独自の実装を柔軟に提供できます。 このアクセス許可の解釈では、アプリケーションまたは関連するライブラリのプラットフォームによって提供されるミドルウェアやその他のオプションを使用できます。 通常、アプリケーションはユーザー ロール情報を要求として受け取り、それらの要求に基づいてユーザーのアクセス許可を決定します。

#### アプリの役割

Microsoft Entra ID を使用すると、アプリケーションの [アプリ ロールを定義](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) し、それらのロールをユーザーや他のアプリケーションに割り当てることができます。 ユーザーまたはアプリケーションに割り当てるロールは、アプリケーション内のリソースと操作へのアクセスレベルを定義します。

認証されたユーザーまたはアプリケーションに対して Microsoft Entra ID がアクセス トークンを発行すると、アクセス トークンの [`roles`](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-token-claims-reference#payload-claims) 要求にエンティティ (ユーザーまたはアプリケーション) を割り当てたロールの名前が含まれます。 要求でそのアクセス トークンを受け取る Web API などのアプリケーションは、 `roles` 要求の値に基づいて承認の決定を行うことができます。

#### グループ

開発者は [、Microsoft Entra グループ](https://learn.microsoft.com/ja-jp/entra/fundamentals/concept-learn-about-groups) を使用してアプリケーションに RBAC を実装することもできます。この場合、特定のグループ内のユーザーのメンバーシップはロール メンバーシップとして解釈されます。 組織がグループを使用する場合、トークンには [グループ要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-token-claims-reference#payload-claims)が含まれます。 グループ要求は、テナント内のユーザーの割り当てられたすべてのグループの識別子を指定します。

Von Bedeutung

グループを使用する場合、開発者は [超過分](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-token-claims-reference#payload-claims)要求の概念を認識する必要があります。 既定では、ユーザーが超過分の制限を超えるメンバーである場合 (SAML トークンの場合は 150、JWT トークンの場合は 200、暗黙的フローを使用する場合は 6)、Microsoft Entra ID はトークンにグループ要求を出力しません。 代わりに、トークンのコンシューマーが Microsoft Graph API にクエリを実行してユーザーのグループ メンバーシップを取得する必要があることを示す "超過分要求" がトークンに含まれています。 超過分の要求の操作の詳細については、「 [アクセス トークンの要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-token-claims-reference)」を参照してください。 アプリケーションに割り当てられているグループのみを出力できますが、 [グループベースの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal) には Microsoft Entra ID P1 または P2 エディションが必要です。

#### カスタム データ ストア

アプリ ロールとグループの両方に、ユーザーの割り当てに関する情報が Microsoft Entra ディレクトリに格納されます。 開発者が使用できるユーザー ロール情報を管理するもう 1 つのオプションは、カスタム データ ストア内のディレクトリの外部に情報を保持することです。 たとえば、SQL データベース、Azure Table Storage、または Azure Cosmos DB for Table などです。

カスタム ストレージを使用すると、開発者はロールをユーザーに割り当てる方法とその表現方法をカスタマイズして制御できます。 ただし、柔軟性が高いほど、より多くの責任も生まれます。 たとえば、現在、Microsoft Entra ID から返されるトークンにこの情報を含めるメカニズムはありません。 ロール情報がカスタム データ ストアに保持されている場合、アプリケーションはロールを取得する必要があります。 通常、ロールの取得は、アプリケーションの開発に使用されているプラットフォームで使用できるミドルウェアで定義された拡張ポイントを使用して行われます。 開発者は、カスタム データ ストアを適切にセキュリティで保護する必要があります。

### アプローチを選択する

一般に、アプリ ロールが推奨されるソリューションです。 アプリ ロールは最も単純なプログラミング モデルを提供し、RBAC 実装の目的です。 ただし、特定のアプリケーション要件は、別のアプローチがより優れたソリューションであることを示している可能性があります。

開発者は、アプリ ロールを使用して、ユーザーがアプリケーションにサインインできるか、アプリケーションが Web API のアクセス トークンを取得できるかを制御できます。 アプリ ロールは、開発者がアプリケーションで承認のパラメーターを記述および制御する場合に、Microsoft Entra グループよりも優先されます。 たとえば、承認にグループを使用するアプリケーションは、グループ識別子と名前の両方が異なる可能性があり、次のテナントで中断します。 アプリ ロールを使用するアプリケーションは安全なままです。

承認にはアプリロールまたはグループを使用できますが、それらの主な違いは、特定のシナリオに最適なソリューションに影響を与える可能性があります。

| - | アプリ ロール | Microsoft Entra グループ | カスタム データ ストア |
| --- | --- | --- | --- |
| **プログラミング モデル** | **最も単純です**。 これらはアプリケーションに固有であり、アプリケーションの登録で定義されます。 これらはアプリケーションと共に移動します。 | **より複雑です**。 グループ識別子はテナントによって異なり、超過分の要求を考慮する必要がある場合があります。 グループは、アプリケーションに固有のものではなく、Microsoft Entra テナントに固有のものです。 | **最も複雑です**。 開発者は、ロール情報を格納および取得する手段を実装する必要があります。 |
| **ロールの値は Microsoft Entra テナント間で静的です** | イエス | いいえ | 実装によって異なります。 |
| **ロール値は複数のアプリケーションで使用できます** | いいえ (各アプリケーションの登録でロールの構成が重複していない場合)。 | イエス | イエス |
| **ディレクトリ内に格納されている情報** | イエス | イエス | いいえ |
| **情報はトークンを介して配信されます** | はい (ロール要求) | はい (超過分の場合は、 *実行時にグループ要求* を取得する必要がある場合があります) | いいえ (カスタム コードを使用して実行時に取得されます)。 |
| **有効期間**。 | ディレクトリ内のアプリケーション登録に存在します。 アプリケーションの登録が削除されると削除されます。 | ディレクトリ内に存在します。 アプリケーションの登録が削除された場合でも、そのまま残ります。 | カスタム データ ストアに存在します。 アプリケーションの登録に関連付けされていません。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/delegated-access-primer"} -->
## Microsoft ID プラットフォームでの委任されたアクセスのシナリオ - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/delegated-access-primer
- Service: identity-platform
- Article date: 2024-11-07
- Summary: Microsoft ID プラットフォーム エンドポイントで委任されたアクセスを使用するタイミングと方法について説明します。

ユーザーがアプリにサインインし、アプリを使用して Microsoft Graph などの他のリソースにアクセスするときには、まず、ユーザーの代わりにアプリから、このリソースにアクセスするためのアクセス許可を要求する必要があります。 この一般的なシナリオは、委任されたアクセスと呼ばれます。

### 委任されたアクセスを使用する必要がある理由

ユーザーはクラウド サービスから自分のデータにアクセスするために、異なるアプリケーションを使用することがよくあります。 たとえば、OneDrive に格納されているファイルを、お気に入りの PDF リーダー アプリケーションを使用して表示したい場合があります。 もう 1 つの例は、要求のレビュー担当者を簡単に選択できるように同僚に関する共有情報を取得する場合がある、会社の基幹業務アプリケーションが挙げられます。 そうした場合、クライアント アプリケーション、PDF リーダー、または会社の要求承認ツールが、アプリケーションにサインインしたユーザーの代わりにこのデータにアクセスすることの承認を受ける必要があります。

サインインしているユーザーが、アクセスできる自分自身のリソースやリソースを操作できるようにするときは必ず、委任されたアクセスを使用します。 組織全体のポリシーを設定する管理者であっても、受信トレイのメールを削除するユーザーであっても、ユーザー アクションを伴うすべてのシナリオで、委任されたアクセスを使用する必要があります。

[Image: 委任されたアクセスのシナリオを表す図を示します。]

その一方、委任されたアクセスは、自動化など、サインインしたユーザーがいない状態で実行する必要があるシナリオには、通常は適さない選択肢です。 また、データ損失防止やバックアップなど、多くのユーザーのリソースにアクセスする必要のあるシナリオには適さない選択肢である場合があります。 これらの種類の操作には、[アプリケーション専用アクセス](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)の使用を検討してください。

### クライアント アプリとしてのスコープの要求

アクセスするリソース アプリについての特定のスコープ、またはスコープのセットを付与するように、アプリからユーザーに依頼する必要があります。 スコープは、委任されたアクセス許可とも呼ばれます。 これらのスコープは、ユーザーの代わりにアプリで実行したいリソースや操作について記述するものです。 たとえば、最近受信したメール メッセージやチャット メッセージの一覧をアプリで表示する場合は、Microsoft Graph の `Mail.Read` スコープと `Chat.Read` スコープに同意するようユーザーに求めることができます。

アプリからスコープを要求されたら、ユーザーまたは管理者が、要求されたアクセスを付与する必要があります。 Outlook.com や Xbox Live のアカウントなど、Microsoft アカウントを持っているコンシューマー ユーザーは、いつでも自分自身のためにスコープを付与できます。 Microsoft Entra アカウントを持っている組織のユーザーは、組織の設定に応じて、スコープを付与できる場合とできない場合があります。 組織のユーザーがスコープに直接同意できない場合、ユーザーが組織の管理者に、同意するよう依頼する必要があります。

常に最小限の特権の原則に従ってください。アプリで必要でないスコープは要求しないでください。 この原則に従えば、アプリが侵害された場合のセキュリティ リスクを限定するのに役立ち、管理者がアプリにアクセスを付与しやすくなります。 たとえば、アプリで必要なのはユーザーが属するチャットを一覧表示することだけで、チャット メッセージ自体を表示する必要がない場合は、Microsoft Graph のスコープとして `Chat.Read` ではなく、より制限の多い `Chat.ReadBasic` を要求する必要があります。 openID スコープの詳細については、[OpenID スコープ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc)に関するページを参照してください。

### リソース サービス用のスコープの設計と発行

API を構築しようとしていて、委任されたアクセスをユーザーに代わって許可したい場合は、他のアプリから要求できるスコープを作成する必要があります。 これらのスコープでは、クライアントから使用できるアクションまたはリソースを記述する必要があります。 スコープの設計時には、開発者のシナリオを考慮に入れる必要があります。

### 自己トークン

このシナリオでは、次のようになります。

- リソース アプリケーションとクライアント アプリケーションは同じです。
- アプリケーションに Web API が登録されていません。
- アプリケーションが自身を公開する委任されたアクセス許可のトークンを要求しています

このトークン要求に対する同意は必要はなく、表示もされません。 さらに、テナント内で作成され、自身にトークンを要求するアプリは、プロファイル データへのアクセス権を既に持っていると推論でき、プロファイル アクセスが自動的に付与されます。

### 委任されたアクセスはどのように機能するか

委任されたアクセスについて最も重要なのは、クライアント アプリとサインインしているユーザーの両方が適切に承認される必要があることです。 スコープの付与では不十分です。 クライアント アプリに適切なスコープがないか、ユーザーがリソースの読み取りまたは変更のために十分な権限を持っていないか、どちらの場合も呼び出しが失敗します。

- **クライアント アプリの承認** - クライアント アプリの承認は、スコープを付与することで行われます。 ユーザーまたは管理者が、何らかのリソースにアクセスするためのスコープをクライアント アプリに付与すると、その許可は Microsoft Entra ID に記録されます。 適切なユーザーの代わりにリソースにアクセスするためにクライアントによって要求される、委任されたすべてのアクセス トークンの `scp` 要求には、それらのスコープの要求値が含まれます。 リソース アプリではこの要求を調べて、その呼び出しの正しいスコープがクライアント アプリに付与されているかどうかを特定します。
- **ユーザーの承認** - ユーザーの承認は、呼び出そうとしているリソースによって行われます。 リソース アプリでは、ユーザー承認のために 1 つ以上のシステムを使用できます。[ロールベースのアクセス制御](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-rbac-for-developers)、所有権/メンバーシップ関係、アクセス制御リスト、その他の確認などです。 たとえば Microsoft Entra ID では、組織のアプリケーションを削除することをユーザーに許可する前に、そのユーザーがアプリの管理ロールまたは一般管理者ロールに割り当てられていることを確認します。しかしまた、すべてのユーザーが、自分が所有するアプリケーションを削除できます。 同様に、SharePoint Online サービスでは、ファイルを開くことをユーザーに許可する前に、そのユーザーがファイルに対する適切な所有者または閲覧者の権限を持っていることを確認します。

### 委任されたアクセスの例 - Microsoft Graph から OneDrive

次の例を確認してください。

Alice は、クライアント アプリを使用して、リソース API の Microsoft Graph によって保護されているファイルを開こうとしています。 ユーザーの承認については、OneDrive サービスによって、そのファイルが Alice のドライブに格納されているかどうかが調べられます。 別のユーザーのドライブに格納されている場合、Alice には他のユーザーのドライブを読み取る権限がないため、Alice の要求は未承認として OneDrive に拒否されます。

クライアント アプリの承認については、呼び出しを行うクライアントに、サインインしているユーザーの代わりの `Files.Read` スコープが付与されているかどうかが、OneDrive によって調べられます。 この場合、サインインしているユーザーは Alice です。 Alice のためにアプリに `Files.Read` が付与されていない場合、この要求も OneDrive によって失敗とされます。

| GET /drives/{id}/files/{id} | クライアント アプリに Alice の`Files.Read`スコープが付与された | クライアント アプリに Alice の`Files.Read`スコープが付与されなかった |
| --- | --- | --- |
| ドキュメントは Alice の OneDrive にあります。 | 200 - アクセスが付与されます。 | 403 - 承認されません。 Alice (またはその管理者) は、ファイルの読み取りをこのクライアントに許可していません。 |
| ドキュメントは別のユーザーの OneDrive にあります\*。 | 403 - 承認されません。 Alice にはこのファイルを読み取る権限がありません。 クライアントに `Files.Read` が付与されている場合でも、Alice のために操作する場合は拒否される必要があります。 | 403 - 承認されません。 Alice にはこのファイルを読み取る権限がありません。また、クライアントには、Alice がアクセス権を持っているファイルの読み取りも許可されません。 |

示した例は、委任された承認の例を示すために簡略化されています。 運用中の OneDrive サービスでは、共有ファイルなど、他の多くのアクセス シナリオがサポートされています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/deploy-web-app-authentication-pipeline"} -->
## パイプラインで App Service 認証を使用して Web アプリをデプロイする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/deploy-web-app-authentication-pipeline
- Service: identity-platform
- Article date: 2023-07-17
- Summary: Azure Pipelines でパイプラインを設定して、Web アプリをビルドして Azure にデプロイし、Azure App Service の組み込み認証を有効にする方法について説明します。 この記事では、Azure リソースの構成、Web アプリケーションのビルドとデプロイ、Microsoft Entra アプリの登録の作成、および Azure Pipelines を使用した App Service の組み込み認証の構成を行う手順について説明します。

この記事では、[Azure Pipelines](https://learn.microsoft.com/ja-jp/azure/devops/pipelines/) でパイプラインを設定して、Web アプリをビルドして Azure にデプロイし、[Azure App Service の組み込み認証](https://learn.microsoft.com/ja-jp/azure/app-service/overview-authentication-authorization)を有効にする方法について説明します。

次の方法について学習します。

- Azure Pipelines でスクリプトを使用して Azure リソースを構成する
- Azure Pipelines を使用して Web アプリケーションをビルドし、App Service にデプロイする
- Azure Pipelines でMicrosoft Entra アプリの登録を作成する
- Azure Pipelines で App Service の組み込み認証を構成する。

### [前提条件]

- アクティブなサブスクリプションを持つ Azure アカウント。 [無料でアカウントを作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- Azure DevOps 組織。 [無料で作成できます](https://learn.microsoft.com/ja-jp/azure/devops/pipelines/get-started/pipelines-sign-up)。
    - Microsoft でホストされるエージェントを使用するには、Azure DevOps 組織が Microsoft でホストされている並列ジョブにアクセスできる必要があります。 [並列ジョブを確認し、無料付与を要求する](https://learn.microsoft.com/ja-jp/azure/devops/pipelines/troubleshooting/troubleshooting#check-for-available-parallel-jobs)。
- Microsoft Entra [テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- [GitHub アカウント](https://github.com)と Git の[ローカル セットアップ](https://docs.github.com/en/get-started/quickstart/set-up-git)。
- [.NET 6.0 SDK](https://dotnet.microsoft.com/download/dotnet) の最小要件。

### サンプル ASP.NET Core Web アプリを作成する

サンプル アプリを作成し、GitHub リポジトリにプッシュします。

#### GitHub でリポジトリを作成してクローンする

GitHub で[新しいリポジトリを作成](https://docs.github.com/en/get-started/quickstart/create-a-repo?tool=webui)し、"PipelinesTest" のような名前を指定します。 これを**プライベート**に設定し、 を含む `.gitignore template: VisualStudio` ファイルを追加します。

ターミナル ウィンドウを開き、現在の作業ディレクトリをクローンしたディレクトリの場所に変更します。

```
cd c:\temp\
```

次のコマンドを入力して、リポジトリをクローンします。

```
git clone https://github.com/YOUR-USERNAME/PipelinesTest
cd PipelinesTest
```

#### ASP.NET Core Web アプリを作成する

1. 自分のマシンでターミナル ウィンドウを開き、作業ディレクトリに移動します。 [dotnet new webapp](https://learn.microsoft.com/ja-jp/dotnet/core/tools/dotnet-new#web-options) コマンドを使用して新しい ASP.NET Core Web アプリを作成し、ディレクトリを新しく作成したアプリに変更します。

    ```dotnetcli
    dotnet new webapp -n PipelinesTest --framework net7.0
    cd PipelinesTest
    dotnet new sln
    dotnet sln add .
    ```
2. 同じターミナル セッションから dotnet run コマンドを使用して、アプリケーションをローカルで実行します。

    ```dotnetcli
    dotnet run --urls=https://localhost:5001/
    ```
3. Web アプリが実行されていることを確認するには、Web ブラウザーを開き、`https://localhost:5001` にあるアプリに移動します。

コア Web アプリ ASP.NET テンプレートがページに表示されます。

[Image: ローカルで実行されている Web アプリを示すスクリーン ショット。]

コマンド ラインで *Ctrl + C* キーを押して、Web アプリの実行を停止します。

#### サンプルを GitHub にプッシュする

変更をコミットし、GitHub にプッシュします。

```
git add .
git commit -m "Initial check-in"
git push origin main
```

### Azure DevOps 環境を設定する

Azure DevOps 組織にサインインします (`https://dev.azure.com/{yourorganization}`)。

新しいプロジェクトを作成します。

1. **[新しいプロジェクト]** を選択します。
2. **[プロジェクト名]** を入力します ("PipelinesTest" など)。
3. 可視性に **[プライベート]** を選択します。
4. **を選択して**を作成します。

### 新しいパイプラインを作成する

プロジェクトが作成されたら、パイプラインを追加します。

1. 左側のナビゲーション ウィンドウで、**[パイプライン]** -&gt;**[パイプライン]** を選択し、**[パイプラインの作成]** を選択します。
2. **[GitHub YAML]** を選択します。
3. **[接続]** タブで **[GitHub YAML]** を選択します。 確認を求められたら GitHub の資格情報を入力します。
4. リポジトリの一覧が表示されたら、`PipelinesTest` リポジトリを選択します。
5. Azure Pipelines アプリをインストールするために、GitHub にリダイレクトされる場合があります。 その場合は、**[承認してインストール]** を選択します。
6. **[パイプラインを構成する]** で、**[スタート パイプライン]** を選択します。
7. 基本構成を持つ新しいパイプラインが表示されます。 既定の構成では、Microsoft ホステッド エージェントを使用します。
8. 準備ができたら、[ **保存して実行]** を選択します。 GitHub に変更内容をコミットしてパイプラインを開始するには、[メイン ブランチに直接コミットする] を選択し、もう一度 [保存および実行] を選択します。 "**この実行を続行する前に、リソースにアクセスするためのアクセス許可がこのパイプラインに必要です**" のようなメッセージでアクセス許可を付与するよう求められた場合は、**[表示]** を選択し、プロンプトに従ってアクセスを許可します。

### ビルド ステージとビルド タスクをパイプラインに追加する

機能するパイプラインが作成されたので、Web アプリをビルドするためにビルド ステージとビルド タスクを追加できます。

*azure-pipelines.yml* を更新し、基本のパイプライン構成を次のように置き換えます。

```yml
trigger:
- main

stages:
- stage: Build
  jobs: 
  - job: Build

    pool:
      vmImage: 'windows-latest'

    variables:
      solution: '**/*.sln'
      buildPlatform: 'Any CPU'      
      buildConfiguration: 'Release'      

    steps:
    - task: NuGetToolInstaller@1

    - task: NuGetCommand@2
      inputs:
        restoreSolution: '$(solution)'

    - task: VSBuild@1
      inputs:
        solution: '$(solution)'
        msbuildArgs: '/p:DeployOnBuild=true /p:WebPublishMethod=Package /p:PackageAsSingleFile=true /p:SkipInvalidConfigurations=true /p:DesktopBuildPackageLocation="$(build.artifactStagingDirectory)\WebApp.zip" /p:DeployIisAppPath="Default Web Site"'
        platform: '$(buildPlatform)'
        configuration: '$(buildConfiguration)'
        
    - task: PublishBuildArtifacts@1
      inputs:
        PathtoPublish: '$(Build.ArtifactStagingDirectory)'
        ArtifactName: 'drop'
        publishLocation: 'Container'
```

変更を保存し、パイプラインを実行します。

ステージ `Build` は、Web アプリをビルドするために定義されます。 `steps` セクションの下には、Web アプリをビルドし、ビルド成果物をパイプラインに発行するためのさまざまなタスクが表示されます。

- [NuGetToolInstaller@1](https://learn.microsoft.com/ja-jp/azure/devops/pipelines/tasks/reference/nuget-tool-installer-v1) は、NuGet を取得して PATH に追加します。
- [NuGetCommand@2](https://learn.microsoft.com/ja-jp/azure/devops/pipelines/tasks/reference/nuget-command-v2) は、ソリューション内の NuGet パッケージを復元します。
- [VSBuild@1](https://learn.microsoft.com/ja-jp/azure/devops/pipelines/tasks/reference/vsbuild-v1) は、MSBuild を使用してソリューションをビルドし、アプリのビルド結果 (依存関係を含む) を .zip ファイルとしてフォルダーにパッケージ化します。
- [PublishBuildArtifacts@1](https://learn.microsoft.com/ja-jp/azure/devops/pipelines/tasks/reference/publish-build-artifacts-v1) は、.zip ファイルを Azure Pipelines に発行します。

### サービス接続を作成する

[サービス接続](https://learn.microsoft.com/ja-jp/azure/devops/pipelines/library/service-endpoints)を追加して、パイプラインがリソースを Azure に接続してデプロイできるようにします。

1. **[Project settings]** を選択します。
2. 左側のナビゲーション ウィンドウで、**[サービス接続]** を選択し、**[サービス接続の作成]** を選択します。
3. **[Azure Resource Manager]**、**[次へ]** の順に選択します。
4. **[サービス プリンシパル (自動)]**、**[次へ]** の順に選択します。
5. **スコープ レベル**として **[サブスクリプション]** を選択し、ご使用の Azure サブスクリプションを選択します。 "PipelinesTestServiceConnection" などのサービス接続名を入力し、**[次へ]** を選択します。 サービス接続名は、次の手順で使用されます。

アプリケーションは、パイプラインの ID を提供する Microsoft Entra テナントにも作成されます。 後の手順でアプリの登録の表示名が必要になります。 表示名を確認するには:

1. [アプリケーション開発者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)にサインインします。
2. **Entra ID**&gt;**アプリケーションの登録**&gt;**すべてのアプリケーション**に移動します。
3. アプリの登録の表示名 (形式が `{organization}-{project}-{guid}`) を見つけます。

パイプラインにアクセスするためのアクセス許可をサービス接続に付与します。

1. 左側のナビゲーション ウィンドウで、**[プロジェクト設定]**、**[サービス接続]** の順に選択します。
2. **[PipelinesTestServiceConnection]** サービス接続、**省略記号**、ドロップダウン メニューから **[セキュリティ]** の順に選択します。
3. **[パイプラインのアクセス許可]** セクションで、**[パイプラインを追加します]** を選択し、一覧から **[PipelinesTest]** サービス接続を選択します。

### 変数グループを追加する

次の セクションで作成する `DeployAzureResources` ステージでは、いくつかの値を使用してリソースを作成し、Azure にデプロイします。

- Microsoft Entraテナント ID ([Microsoft Entra 管理センター](https://entra.microsoft.com/)で確認)。
- リソースがデプロイされるリージョンまたは場所。
- リソース グループ名。
- App Service サービス プラン名。
- Web アプリの名前。
- パイプラインを Azure に接続するために使用されるサービス接続の名前。 パイプラインでは、この値は Azure サブスクリプションに使用されます。

[変数グループ](https://learn.microsoft.com/ja-jp/azure/devops/pipelines/library/variable-groups)を作成し、パイプラインで変数として使用する値を追加します。

左側のナビゲーション ウィンドウで **[ライブラリ]** を選択し、新しい**変数グループ**を作成します。 "AzureResourcesVariableGroup" という名前を付けます。

次の変数と値を追加します。

| 変数名 | 値の例 |
| --- | --- |
| 場所 | セントララス |
| TENANTID | {tenant-id} |
| RESOURCEGROUPNAME | パイプラインテストグループ |
| SVCPLANNAME | パイプラインテスト計画 |
| WEBAPPNAMETEST | パイプラインテストウェブアプリ |
| AZURESUBSCRIPTION | パイプラインテストサービス接続 |

**保存** を選択します。

変数グループにアクセスするためのアクセス許可をパイプラインに付与します。 変数グループのページで、**[パイプラインのアクセス許可]** を選択し、パイプラインを追加して、ウィンドウを閉じます。

*azure-pipelines.yml* を更新し、変数グループをパイプラインに追加します。

```yml
variables: 
- group: AzureResourcesVariableGroup
   
trigger:
- main

stages:
- stage: Build
  jobs: 
  - job: Build

    pool:
      vmImage: 'windows-latest'
  
```

変更を保存し、パイプラインを実行します。

### Azure リソースを展開する

次に、Azure リソースをデプロイするパイプラインにステージを追加します。 パイプラインは[インライン スクリプト](https://learn.microsoft.com/ja-jp/azure/devops/pipelines/scripts/powershell)を使用して、App Service インスタンスを作成します。 後の手順では、インライン スクリプトで、App Service 認証用の Microsoft Entra アプリの登録を作成します。 Azure Resource Manager (および Azure パイプライン タスク) ではアプリの登録を作成できないため、Azure CLI bash スクリプトが使用されます。

インライン スクリプトはパイプラインのコンテキストで実行され、スクリプトがアプリの登録を作成できるように [Application.Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロールをアプリに割り当てます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**役割と管理者**に移動します。
3. 組み込みロールの一覧から **[Application Administrator](https://learn.microsoft.com/ja-jp/entra/identity-platform/アプリケーション管理者)**、**[割り当ての追加]** の順に選択します。
4. 表示名でパイプライン アプリの登録を検索します。
5. 一覧からアプリの登録を選択し、**[追加]** を選択します。

*azure-pipelines.yml* を更新してインライン スクリプトを追加します。これにより、Azure にリソース グループが作成され、App Service プランが作成され、App Service インスタンスが作成されます。

```yml
variables: 
- group: AzureResourcesVariableGroup
   
trigger:
- main

stages:
- stage: Build
  jobs: 
  - job: Build

    pool:
      vmImage: 'windows-latest'

    variables:
      solution: '**/*.sln'
      buildPlatform: 'Any CPU'
      buildConfiguration: 'Release'      

    steps:
    - task: NuGetToolInstaller@1

    - task: NuGetCommand@2
      inputs:
        restoreSolution: '$(solution)'

    - task: VSBuild@1
      inputs:
        solution: '$(solution)'
        msbuildArgs: '/p:DeployOnBuild=true /p:WebPublishMethod=Package /p:PackageAsSingleFile=true /p:SkipInvalidConfigurations=true /p:DesktopBuildPackageLocation="$(build.artifactStagingDirectory)\WebApp.zip" /p:DeployIisAppPath="Default Web Site"'
        platform: '$(buildPlatform)'
        configuration: '$(buildConfiguration)'
        
    - task: PublishBuildArtifacts@1
      inputs:
        PathtoPublish: '$(Build.ArtifactStagingDirectory)'
        ArtifactName: 'drop'
        publishLocation: 'Container'  
    
- stage: DeployAzureResources
  displayName: 'Deploy resources to Azure'
  dependsOn: Build
  condition: |
    succeeded()    
  jobs: 
  - job: DeployAzureResources
    pool: 
      vmImage: 'windows-latest'
    steps:
      - task: AzureCLI@2
        inputs:
          azureSubscription: $(AZURESUBSCRIPTION)
          scriptType: 'bash'
          scriptLocation: 'inlineScript'
          inlineScript: |
            # Create a resource group
            az group create --location $LOCATION --name $RESOURCEGROUPNAME
            echo "Created resource group $RESOURCEGROUPNAME"    

            # Create App Service plan
            az appservice plan create -g $RESOURCEGROUPNAME -n $SVCPLANNAME --sku FREE
            echo "Created App Service plan $SVCPLANNAME"
            
            ### Create Test resources
            # create and configure an Azure App Service web app
            az webapp create -g $RESOURCEGROUPNAME -p $SVCPLANNAME -n $WEBAPPNAMETEST -r "dotnet:7"
                        
        name: DeploymentScript
```

変更を保存し、パイプラインを実行します。 [Azure portal](https://portal.azure.com) で、**[リソース グループ]** に移動し、新しいリソース グループと App Service インスタンスが作成されていることを確認します。

### Web アプリを App Service にデプロイする

パイプラインが Azure でリソースを作成するようになったので、次は Web アプリを App Service にデプロイするためのデプロイ ステージです。

*azure-pipelines.yml* を更新して、デプロイ ステージを追加します。

```yml
variables: 
- group: AzureResourcesVariableGroup
   
trigger:
- main

stages:
- stage: Build
  jobs: 
  - job: Build

    pool:
      vmImage: 'windows-latest'

    variables:
      solution: '**/*.sln'
      buildPlatform: 'Any CPU'
      buildConfiguration: 'Release'      

    steps:
    - task: NuGetToolInstaller@1

    - task: NuGetCommand@2
      inputs:
        restoreSolution: '$(solution)'

    - task: VSBuild@1
      inputs:
        solution: '$(solution)'
        msbuildArgs: '/p:DeployOnBuild=true /p:WebPublishMethod=Package /p:PackageAsSingleFile=true /p:SkipInvalidConfigurations=true /p:DesktopBuildPackageLocation="$(build.artifactStagingDirectory)\WebApp.zip" /p:DeployIisAppPath="Default Web Site"'
        platform: '$(buildPlatform)'
        configuration: '$(buildConfiguration)'
        
    - task: PublishBuildArtifacts@1
      inputs:
        PathtoPublish: '$(Build.ArtifactStagingDirectory)'
        ArtifactName: 'drop'
        publishLocation: 'Container'  
    
- stage: DeployAzureResources
  displayName: 'Deploy resources to Azure'
  dependsOn: Build
  condition: |
    succeeded()    
  jobs: 
  - job: DeployAzureResources
    pool: 
      vmImage: 'windows-latest'
    steps:
      - task: AzureCLI@2
        inputs:
          azureSubscription: $(AZURESUBSCRIPTION)
          scriptType: 'bash'
          scriptLocation: 'inlineScript'
          inlineScript: |
            # Create a resource group
            az group create --location $LOCATION --name $RESOURCEGROUPNAME
            echo "Created resource group $RESOURCEGROUPNAME"    

            # Create App Service plan
            az appservice plan create -g $RESOURCEGROUPNAME -n $SVCPLANNAME --sku FREE
            echo "Created App Service plan $SVCPLANNAME"
            
            ### Create Test resources
            # create and configure an Azure App Service web app
            az webapp create -g $RESOURCEGROUPNAME -p $SVCPLANNAME -n $WEBAPPNAMETEST -r "dotnet:7"
            
        name: DeploymentScript

- stage: DeployWebApp
  displayName: 'Deploy the web app'
  dependsOn: DeployAzureResources
  condition: |
    succeeded()    
  
  jobs: 
  - job: DeployWebApp
    displayName: 'Deploy Web App'
    pool: 
      vmImage: 'windows-latest'
    
    steps:
      
    - task: DownloadBuildArtifacts@0
      inputs:
        buildType: 'current'
        downloadType: 'single'
        artifactName: 'drop'
        downloadPath: '$(System.DefaultWorkingDirectory)'
    - task: AzureRmWebAppDeployment@4
      inputs:
        ConnectionType: 'AzureRM'
        azureSubscription: $(AZURESUBSCRIPTION)
        appType: 'webApp'
        WebAppName: '$(WEBAPPNAMETEST)'
        packageForLinux: '$(System.DefaultWorkingDirectory)/**/*.zip'
```

変更を保存し、パイプラインを実行します。

`DeployWebApp` ステージは、いくつかのタスクで定義されます。

- [DownloadBuildArtifacts@1](https://learn.microsoft.com/ja-jp/azure/devops/pipelines/tasks/reference/download-build-artifacts-v1) は、前のステージでパイプラインに発行されたビルド成果物をダウンロードします。
- [AzureRmWebAppDeployment@4](https://learn.microsoft.com/ja-jp/azure/devops/pipelines/tasks/reference/azure-rm-web-app-deployment-v4) は、Web アプリを App Service にデプロイします。

App Service にデプロイされた Web サイトを表示する App Service に移動し、インスタンスの**既定のドメイン** (`https://pipelinetestwebapp.azurewebsites.net`) を選択します。

[Image: 既定のドメイン URL を示すスクリーンショット。]

*pipelinetestwebapp* が App Service に正常にデプロイされました。

[Image: Azure で実行されている Web アプリを示すスクリーンショット。]

### App Service 認証を構成する

パイプラインが Web アプリを App Service にデプロイするようになったので、[App Service 組み込み認証](https://learn.microsoft.com/ja-jp/azure/app-service/overview-authentication-authorization)を構成できます。 `DeployAzureResources` 内のインライン スクリプトを次のように変更します。

1. Web アプリの ID として Microsoft Entra アプリの登録を作成します。 アプリの登録を作成するには、パイプラインを実行するサービス プリンシパルにディレクトリ内のアプリケーション管理者ロールが必要です。
2. アプリからシークレットを取得します。
3. App Service Web アプリのシークレット設定を構成します。
4. App Service Web アプリのリダイレクト URI、ホーム ページ URI、発行者の設定を構成します。
5. Web アプリで他の設定を構成します。

```yml
variables: 
- group: AzureResourcesVariableGroup
   
trigger:
- main

stages:
- stage: Build
  jobs: 
  - job: Build

    pool:
      vmImage: 'windows-latest'

    variables:
      solution: '**/*.sln'
      buildPlatform: 'Any CPU'
      buildConfiguration: 'Release'      

    steps:
    - task: NuGetToolInstaller@1

    - task: NuGetCommand@2
      inputs:
        restoreSolution: '$(solution)'

    - task: VSBuild@1
      inputs:
        solution: '$(solution)'
        msbuildArgs: '/p:DeployOnBuild=true /p:WebPublishMethod=Package /p:PackageAsSingleFile=true /p:SkipInvalidConfigurations=true /p:DesktopBuildPackageLocation="$(build.artifactStagingDirectory)\WebApp.zip" /p:DeployIisAppPath="Default Web Site"'
        platform: '$(buildPlatform)'
        configuration: '$(buildConfiguration)'
        
    - task: PublishBuildArtifacts@1
      inputs:
        PathtoPublish: '$(Build.ArtifactStagingDirectory)'
        ArtifactName: 'drop'
        publishLocation: 'Container'  
    
- stage: DeployAzureResources
  displayName: 'Deploy resources to Azure'
  dependsOn: Build
  condition: |
    succeeded()    
  jobs: 
  - job: DeployAzureResources
    pool: 
      vmImage: 'windows-latest'
    steps:
      - task: AzureCLI@2
        inputs:
          azureSubscription: $(AZURESUBSCRIPTION)
          scriptType: 'bash'
          scriptLocation: 'inlineScript'
          inlineScript: |
            # Create a resource group
            az group create --location $LOCATION --name $RESOURCEGROUPNAME
            echo "Created resource group $RESOURCEGROUPNAME"    

            # Create App Service plan
            az appservice plan create -g $RESOURCEGROUPNAME -n $SVCPLANNAME --sku FREE
            echo "Created App Service plan $SVCPLANNAME"
            
            ### Create Test resources
            # create and configure an Azure App Service web app
            az webapp create -g $RESOURCEGROUPNAME -p $SVCPLANNAME -n $WEBAPPNAMETEST -r "dotnet:7"

            redirectUriTest="https://$WEBAPPNAMETEST.azurewebsites.net/.auth/login/aad/callback"
            homePageUrlTest="https://$WEBAPPNAMETEST.azurewebsites.net"
            issuerTest="https://sts.windows.net/$TENANTID"
            
            # Required resource access.  Access Microsoft Graph with delegated User.Read permissions.
            cat > manifest.json << EOF
            [
                {
                    "resourceAppId": "00000003-0000-0000-c000-000000000000",
                    "resourceAccess": [
                        {
                            "id": "e1fe6dd8-ba31-4d61-89e7-88639da4683d",
                            "type": "Scope"
                        }
                    ]
                }
            ]
            EOF
            
            # Create app registration for App Service authentication
            appIdTest=$(az ad app create --display-name $WEBAPPNAMETEST --sign-in-audience AzureADMyOrg --enable-id-token-issuance true --query appId --output tsv)
            echo "Created app registration $appIdTest"

            # Set identifier URI, homepage, redirect URI, and resource access
            az ad app update --id $appIdTest --identifier-uris api://$appIdTest --web-redirect-uris $redirectUriTest  --web-home-page-url $homePageUrlTest --required-resource-accesses @manifest.json
            echo "Updated app $appIdTest"

            # Get secret from the app for App Service authentication
            secretTest=$(az ad app credential reset --id $appIdTest --query password --output tsv)
            echo "Added secret to app $appIdTest"

            az config set extension.use_dynamic_install=yes_without_prompt
            az extension add --name authV2                      

            az webapp config appsettings set --name $WEBAPPNAMETEST --resource-group $RESOURCEGROUPNAME --slot-settings MICROSOFT_PROVIDER_AUTHENTICATION_SECRET=$secretTest
            echo "Updated settings for web app $WEBAPPNAMETEST"

            az webapp auth microsoft update --name $WEBAPPNAMETEST --resource-group $RESOURCEGROUPNAME --client-id $appIdTest --secret-setting MICROSOFT_PROVIDER_AUTHENTICATION_SECRET --allowed-audiences $redirectUriTest  --issuer $issuerTest
            echo "Updated authentication settings for $WEBAPPNAMETEST"
            
        name: DeploymentScript

- stage: DeployWebApp
  displayName: 'Deploy the web app'
  dependsOn: DeployAzureResources
  condition: |
    succeeded()    
  
  jobs: 
  - job: DeployWebApp
    displayName: 'Depoy Web App'
    pool: 
      vmImage: 'windows-latest'
    
    steps:
      
    - task: DownloadBuildArtifacts@0
      inputs:
        buildType: 'current'
        downloadType: 'single'
        artifactName: 'drop'
        downloadPath: '$(System.DefaultWorkingDirectory)'
    - task: AzureRmWebAppDeployment@4
      inputs:
        ConnectionType: 'AzureRM'
        azureSubscription: $(AZURESUBSCRIPTION)
        appType: 'webApp'
        WebAppName: '$(WEBAPPNAMETEST)'
        packageForLinux: '$(System.DefaultWorkingDirectory)/**/*.zip'
```

変更を保存し、パイプラインを実行します。

### Web アプリへの制限付きアクセスを確認する

アプリへのアクセスが組織内のユーザーに制限されていることを確認するには、自身の App Service に移動し、インスタンスの**既定のドメイン**を選択します: `https://pipelinetestwebapp.azurewebsites.net`。

セキュリティで保護されたサインイン ページが表示されるので、認証されていないユーザーにはサイトへのアクセスが許可されないことを確認できます。 サイトにアクセスするために、組織内のユーザーとしてサインインします。

新しいブラウザーを起動し、個人用アカウントを使用してサインインしてみることで、組織外のユーザーにはアクセス権がないことを確認することもできます。

### リソースをクリーンアップする

Azure リソースと Azure DevOps 環境をクリーンアップして、完了後にリソースに対して課金されないようにします。

#### リソース グループを削除します

メニューから **[リソース グループ]** を選択し、デプロイされた Web アプリが含まれるリソース グループを選択します。

**[リソース グループの削除]** を選択して、リソース グループとすべてのリソースを削除します。

#### パイプラインを無効にする、または Azure DevOps プロジェクトを削除する

GitHub リポジトリを参照するプロジェクトを作成しました。 GitHub リポジトリに変更をプッシュするたびにパイプラインが実行され、無料のビルド時間 (分) またはリソースが消費されます。

##### オプション 1: パイプラインを無効にする

プロジェクトとビルド パイプラインを今後の参照用に保持したい場合は、このオプションを選択します。 必要な場合は、パイプラインを後でもう一度有効にできます。

1. Azure DevOps プロジェクトで、**[パイプライン]** を選択したら、パイプラインを選択します。
2. 右端にある省略記号ボタンを選択し、**[設定]** を選択します。
3. **[無効]** を選択してから、**[保存]** を選択します。 パイプラインによる新しい実行要求は処理されなくなります。

##### オプション 2: プロジェクトを削除する

今後の参照用に DevOps プロジェクトが必要でない場合は、このオプションを選択します。 これにより、Azure DevOps プロジェクトが削除されます。

1. Azure DevOps プロジェクトに移動します。
2. 左下隅にある **[プロジェクトの設定** ] を選択します。
3. **[概要]** で、ページの下部まで下にスクロールしたら、**[削除]** を選択します。
4. テキスト ボックスにプロジェクト名を入力したら、**[削除]** を選択します。

#### Microsoft Entra ID でアプリの登録を削除する

[Microsoft Entra 管理センター](https://entra.microsoft.com/)で、[**Entra ID**&gt;**App registrations**&gt;**All applications**] を選択します。

パイプラインのアプリケーション (表示名の形式が `{organization}-{project}-{guid}`) を選択し、削除します。

Web アプリのアプリケーション (*pipelinetestwebapp*) を選択し、削除します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/developer-glossary"} -->
## Microsoft ID プラットフォームの用語集 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-glossary
- Service: identity-platform
- Article date: 2025-05-12
- Summary: Microsoft ID プラットフォームのドキュメント、Microsoft Entra 管理センター、および Microsoft 認証ライブラリ (MSAL) などの認証 SDK で使用される主な用語について説明します。

この用語集では、Microsoft Identity Platform、Microsoft Entra 管理センター、および Microsoft Graph API の重要な用語について説明します。 セキュリティで保護されたアプリケーションを構築するための OAuth プロトコルと認証の概念について説明します。

### アクセス トークン

承認サーバーによって発行されるセキュリティ トークンの一種。クライアント アプリケーションが、保護されたリソース サーバーにアクセスする目的で使用します。 要求されたレベルのアクセスに関して、[リソース所有者](https://tools.ietf.org/html/rfc7519)がクライアントに付与しているアクセス権限を通常 JSON Web トークン (JWT) の形式で 1 つにまとめたものがこのトークンです。 このトークンは、認証対象に関して当てはまる要求をすべて含んでおり、クライアント アプリケーションが特定のリソースにアクセスする際に一種の資格情報として使用することができます。 また、これを使用すると、リソース所有者がクライアントに資格情報を開示する必要がなくなります。

アクセス トークンは短時間のみ有効で、取り消すことはできません。 承認サーバーは、アクセス トークンが発行されたときに更新トークンを発行することもあります。 更新トークンは、通常、confidential クライアント アプリケーションにのみ提供されます。

表現の対象となる資格情報によっては、アクセス トークンを "User+App" や "App-Only" と呼ぶこともあります。 たとえばクライアント アプリケーションが使用する承認付与には、次のようなタイプがあります。

- "承認コード" 型の承認付与: エンド ユーザーはまず、リソース所有者として認証を行い、リソースにアクセスするための承認をクライアントに委任します。 その後クライアントは、アクセス トークンを取得した時点で認証を行います。 このトークンは、クライアント アプリケーションを承認したユーザーとアプリケーションの両方を表すことから、より具体的に "User+App" トークンと呼ばれることがあります。
- "クライアント資格情報" 型の承認付与: クライアントが行うのは単一の認証のみです。クライアントがリソース所有者の認証/承認なしで機能することから、このトークンは、"App-Only" トークンと呼ばれることがあります。

詳細については、[アクセス トークンのリファレンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)を参照してください。

### 俳優

クライアント アプリケーションのもう 1 つの用語。 アクターは、サブジェクト (リソース所有者) に代わって行動するパーティです。

### アプリケーション (クライアント) ID

アプリケーション ID (*[クライアント ID](https://datatracker.ietf.org/doc/html/rfc6749#section-2.2)*) は、アプリケーションを Microsoft Entra ID に登録するときにMicrosoft ID プラットフォームがアプリケーションに割り当てる値です。 アプリケーション ID は、ID プラットフォーム内でアプリケーションとその構成を一意に識別する GUID 値です。 アプリケーションのコードにアプリ ID を追加します。認証ライブラリには、アプリケーションの実行時に ID プラットフォームへの要求に値が含まれます。 アプリケーション (クライアント) ID はシークレットではありません。パスワードやその他の資格情報として使用しないでください。

### アプリケーション マニフェスト

アプリケーション マニフェストは、関連する [Application](https://learn.microsoft.com/ja-jp/graph/api/resources/application) と [ServicePrincipal](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal) エンティティを更新するメカニズムとして使用される、アプリケーションの ID 構成の JSON 表現を生成する機能です。 詳細については、「[Microsoft Entra のアプリケーション マニフェストについて](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest)」を参照してください。

### アプリケーション オブジェクト

アプリケーションを登録または更新すると、そのテナントを対象に、アプリケーション オブジェクトおよび対応するサービス プリンシパル オブジェクトの両方が作成または更新されます。 アプリケーション オブジェクトは、アプリケーションの ID 構成をグローバルに (アクセス権を持つすべてのテナントで) *定義* し、対応するサービス プリンシパル オブジェクトが実行時にローカルで (特定のテナント内で) 使用されるように *派生する* テンプレートを提供します。

詳細については、[アプリケーション オブジェクトとサービス プリンシパル オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)に関するページを参照してください。

### アプリケーションの登録

Microsoft Entra ID と統合し、ID とアクセス管理の機能を委任するためには、アプリケーションを Microsoft Entra テナントに登録する必要があります。 アプリケーションを Microsoft Entra ID に登録するとき、アプリケーションに使用する ID 構成を指定します。これによって Microsoft Entra ID との連携が可能となり、次のような機能が使用できるようになります:。

- Microsoft Entra の ID 管理と [OpenID Connect](https://openid.net/specs/openid-connect-core-1_0.html) プロトコル実装を使用したシングル サインオンの強固な管理
- クライアント アプリケーションによる保護されたリソースへの OAuth 2.0 承認サーバーを介したブローカー アクセス
- 同意フレームワークは、リソース所有者の認可に基づきクライアントの保護されたリソースへのアクセスを管理します。

詳細については、「[Microsoft Entra ID を使用したアプリケーションの統合](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)」を参照してください。

### 認証

特定の当事者に対し、本物の資格情報の提示を要求する行為。ID 管理とアクセス制御に必要なセキュリティ プリンシパルの拠り所となります。 たとえば OAuth 2.0 承認付与時には、使用する付与形態に応じて、リソース所有者またはクライアント アプリケーションの役割を果たす当事者が、本物であることを証明する側になります。

### 認可

認証済みのセキュリティ プリンシパルに対し、何かを実行する権限を付与する行為。 Microsoft Entra プログラミング モデルでは、主に次の 2 つのユース ケースが存在します。

- OAuth 2.0 認可付与フローの中で - リソース所有者がクライアント アプリケーションに認可を付与すると、その所有者のリソースにクライアントがアクセスできます。
- クライアントによるリソース アクセス時: リソース サーバー側で実装されます。アクセス トークン内に存在する要求値に基づいてアクセス制御の判断を行います。

### Authorization code (承認コード)

OAuth 2.0 承認コード付与フロー中に、承認エンドポイントによって*クライアント アプリケーション*に提供される有効期間の短い値。4 つの OAuth 2.0 承認許可のいずれか。 *認証コード*とも呼ばれる承認コードは、リソース所有者の認証に応答してクライアント アプリケーションに返されます。 認証コードは、リソース所有者がリソースにアクセスするための承認をクライアント アプリケーションに委任したことを示します。 このフローの中で、承認コードは後で アクセス トークンと引き換えられます。

### Authorization endpoint (承認エンドポイント)

承認サーバーによって実装されるエンドポイントの 1 つ。OAuth 2.0 承認付与フローの過程で承認付与を提供するための、リソース所有者との対話に使用されます。 実際に付与される内容は、使用されている承認付与フローによって異なる場合があります (承認コード、セキュリティ トークンなど)。

詳細については、OAuth 2.0 仕様の[承認付与タイプ](https://tools.ietf.org/html/rfc6749#section-1.3)と[承認エンドポイント](https://tools.ietf.org/html/rfc6749#section-3.1)に関するセクションおよび [OpenIDConnect 仕様](https://openid.net/specs/openid-connect-core-1_0.html#AuthorizationEndpoint)を参照してください。

### 認可付与

リソース所有者の保護されたリソースにアクセスしてよいという承認を表す資格情報。クライアント アプリケーションに対して付与されます。 クライアント アプリケーションは、その種類や要件に応じて、[OAuth 2.0 Authorization Framework によって規定された 4 つの付与タイプ](https://tools.ietf.org/html/rfc6749#section-1.3) ("承認コード付与"、"クライアント資格情報付与"、"暗黙的付与"、"リソース所有者パスワード資格情報付与") のいずれかを使ってアクセス許可を得ることができます。 クライアントに返される資格情報は、使用された承認付与のタイプに応じて、アクセス トークンと承認コード (その後アクセス トークンに交換される) のいずれかになります。

リソース所有者のパスワード資格情報の付与は、他のフローを使用できないシナリオを除いて[使用しない](https://news.microsoft.com/features/whats-solution-growing-problem-passwords-says-microsoft/)でください。 SPA を構築する場合は、 [暗黙的な許可ではなく、PKCE で承認コード フロー](https://devblogs.microsoft.com/identity/migrate-to-auth-code-flow/)を使用します。

### 承認サーバー

[OAuth 2.0 Authorization Framework](https://tools.ietf.org/html/rfc6749#page-6) の定義によれば、リソース所有者を認証し、その承認を得た後にアクセス トークンをクライアントに発行するサーバーをいいます。 クライアント アプリケーションは実行時に、その承認エンドポイントおよびトークン エンドポイントを介し、OAuth 2.0 によって定義された承認付与に従って承認サーバーと対話します。

Microsoft ID プラットフォーム アプリケーション統合の場合、Microsoft Entra アプリケーションと Microsoft サービス API ([Microsoft Graph API](https://developer.microsoft.com/graph) など) に使用する承認サーバー ロールは、Microsoft ID プラットフォームで実装されます。

### 要求

要求は、あるエンティティによって作成されたアサーションを別のエンティティに提供する セキュリティ トークン内の名前と値のペアです。 通常、これらのエンティティはクライアント アプリケーションまたはリソースオーナーであり、リソース サーバーにアサーションを提供します。 要求は、承認サーバーによって認証されたセキュリティ プリンシパルの ID など に関する事実を伝達します。 トークンに存在する要求は、トークンの種類、サブジェクトの認証に使用される資格情報の種類、アプリケーション構成など、いくつかの要因によって異なる場合があります。

詳細については、[Microsoft ID プラットフォーム トークンのリファレンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)に関するページを参照してください。

### クライアント アプリケーション

"アクター" とも呼ばれます。 [OAuth 2.0 Authorization Framework](https://tools.ietf.org/html/rfc6749#page-6) の定義によれば、リソース所有者に代わって、保護されたリソースを要求するアプリケーションをいいます。 スコープの形式でリソース所有者からアクセス許可を受け取ります。 "クライアント" という言葉の意味には、特定のハードウェア実装上の特性 (アプリケーションがサーバーで実行されるのか、デスクトップで実行されるのか、またはそれ以外のデバイスで実行されるのか、など) は含まれません。

クライアント アプリケーションは、リソース所有者に承認を要求することによって、OAuth 2.0 承認付与フローに参加し、リソース所有者に代わって API やデータにアクセスすることができます。 OAuth 2.0 Authorization Framework では、資格情報の機密維持に対するクライアントの能力に基づき、"confidential" と "public" という [2 種類のクライアントを定義](https://tools.ietf.org/html/rfc6749#section-2.1)しています。 アプリケーションは、Web サーバー上で実行される Web クライアント (confidential)、デバイス上にインストールされるネイティブ クライアント (public)、またはデバイスのブラウザーで実行されるユーザーエージェントベース クライアント (public) を実装できます。

### 同意

リソース所有者からクライアント アプリケーションに承認 (リソース所有者に代わって特定の権限で保護されたリソースにアクセスするための) を付与するプロセス。 クライアントから要求された権限によっては、組織データまたは個人データへのアクセスを許可することへの同意が管理者 (組織データの場合) またはユーザー (個人データの場合) に求められます。 マルチテナント シナリオでは、アプリケーションのサービス プリンシパルも同意ユーザーのテナントに記録されることに注意してください。

詳細については、「[同意フレームワーク](https://learn.microsoft.com/ja-jp/entra/identity-platform/application-consent-experience)」を参照してください。

### ID プロバイダー

ID プロバイダー (IdP) は、アプリケーションに認証サービスを提供しながら、ユーザーの ID 情報を作成、維持、管理するサービスです。 Microsoft Entra のコンテキストでは、ID プロバイダーには、テナントの構成に応じて、Microsoft アカウントや Google や Facebook などのソーシャル ID プロバイダーを含めることができます。 ユーザーがサインインしようとすると、アプリケーションは認証のためにユーザーを適切な ID プロバイダーにリダイレクトします。

### ID トークン

[承認サーバー](https://openid.net/specs/openid-connect-core-1_0.html#IDToken)の承認エンドポイントから提供される OpenID Connectセキュリティ トークン。このトークンには、エンド ユーザーのリソース所有者の認証に関連した要求が格納されます。 ID トークンもアクセス トークンと同様、デジタル署名された [JSON Web トークン (JWT)](https://tools.ietf.org/html/rfc7519) として表現されます。 ただし、アクセス トークンとは異なり、ID トークンの要求は、リソース アクセス (特にアクセス制御) に関連した目的には使用されません。

詳細については、[ID トークンのリファレンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)を参照してください。

### マネージドアイデンティティ

開発者は資格情報を管理する必要がなくなります。 マネージド ID は、Microsoft Entra 認証をサポートするリソースに接続するときに使用する ID をアプリケーションに提供します。 アプリケーションは、マネージド ID を使用して Microsoft ID プラットフォーム トークンを取得できます。 たとえば、アプリケーションはマネージド ID を使用することで、開発者が安全に資格情報を格納できる Azure キー コンテナーなどのリソースにアクセスしたり、ストレージ アカウントにアクセスしたりできるようになります。 詳細については、[マネージド ID の概要](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)に関するページを参照してください。

### Microsoft ID プラットフォーム

Microsoft ID プラットフォームは、Microsoft Entra ID サービスおよび開発者プラットフォームの進化版です。 これにより、開発者はすべての Microsoft ID にサインインし、Microsoft Graph、その他の Microsoft API、または開発者が作成した API を呼び出すトークンを取得することができます。 これは多彩な機能を備えたプラットフォームであり、認証サービス、ライブラリ、アプリケーションの登録と構成、完全な開発者向けドキュメント、サンプル コード、およびその他の開発者向けコンテンツによって構成されています。 Microsoft ID プラットフォームでは、OAuth 2.0 や OpenID Connect など業界標準のプロトコルがサポートされています。

### マルチテナント アプリケーション

クライアントが登録されたテナントに限らず、任意の Microsoft Entra テナントにプロビジョニングされたユーザーによるサインインと同意を有効にするアプリケーションのクラス。 ネイティブ クライアント アプリケーションは既定ではマルチテナントです。一方、 Web クライアント と Web リソース/API アプリケーションでは、単一テナントまたはマルチテナントのどちらかを選択できます。 一方シングル テナントとして登録される Web アプリケーションの場合は、アプリケーションの登録先と同じテナントにプロビジョニングされたユーザー アカウントからのみサインインが許可されます。

詳細については、 [マルチテナント アプリケーション パターンを使用して Microsoft Entra ユーザーをサインインさせる方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant) を参照してください。

### ネイティブ クライアント

デバイス上にネイティブでインストールされるタイプの クライアント アプリケーション 。 すべてのコードがデバイス上で実行され、機密性を保った状態でプライベートに資格情報を保存することができないため、"public" クライアントと見なされます。 詳細については、[OAuth 2.0 のクライアント タイプとプロファイル](https://tools.ietf.org/html/rfc6749#section-2.1)に関するページを参照してください。

### アクセス許可

クライアント アプリケーションは、アクセス許可要求を宣言することでリソース サーバーへのアクセス権を取得します。 次の 2 種類があります。

- "委任" されたアクセス許可。サインインしたリソース所有者から委任された承認を使用して、スコープに基づくアクセス権を指定します。実行時には、クライアントのアクセス トークンの "scp" 要求としてリソースに提示されます。 これらは、サブジェクトによってアクターに付与されたアクセス許可を示します。
- "アプリケーション" のアクセス許可。クライアント アプリケーションの資格情報/ID を使用してロールベースのアクセス権を指定します。実行時には、クライアントのアクセス トークンの "roles" 要求としてリソースに提示されます。 これらは、テナントによってサブジェクトに付与されたアクセス許可を示します。

これらの要求は 同意 プロセス時にも出現し、管理者またはリソース所有者には、そのテナント内のリソースに対するクライアント アクセスを許可/拒否する機会が与えられます。

アクセス許可要求の構成は、**[API アクセス許可]** ページで、目的の "委任されたアクセス許可" と "アプリケーションのアクセス許可" (後者の場合、グローバル管理者ロールに属している必要がある) を選択することによって行います。 public クライアントは、資格情報を安全に維持できないため、要求できるのは委任されたアクセス許可のみです。一方 confidential クライアントは、委任されたアクセス許可とアプリケーションのアクセス許可のどちらでも要求することができます。 クライアントのアプリケーション オブジェクトは、宣言された権限をその [requiredResourceAccess プロパティ](https://learn.microsoft.com/ja-jp/graph/api/resources/application)に格納します。

### リフレッシュトークン

承認サーバーによって発行されるセキュリティ トークンの種類。 アクセス トークンの有効期限が切れる前に、承認サーバーから新しいアクセス トークンを要求するときに、関連付けられている更新トークンがクライアント アプリケーションに含まれます。 更新トークンは通常、 [JSON Web トークン (JWT)](https://tools.ietf.org/html/rfc7519) として書式設定されます。

アクセス トークンとは異なり、更新トークンは取り消すことができます。 承認サーバーは、取り消された更新トークンを含むクライアント アプリケーションからの要求を拒否します。 承認サーバーが取り消された更新トークンを含む要求を拒否すると、クライアント アプリケーションはリソース所有者の代わりにリソース サーバーにアクセスするアクセス許可を失います。

詳細については、[更新トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/refresh-tokens)に関するセクションをご覧ください。

### リソース所有者

[OAuth 2.0 Authorization Framework](https://tools.ietf.org/html/rfc6749#page-6) の定義によれば、保護されたリソースへのアクセス権を付与することのできるエンティティをいいます。 リソース所有者が人である場合は、"エンド ユーザー" と呼ばれます。 たとえばクライアント アプリケーションは、[Microsoft Graph API](https://developer.microsoft.com/graph) を介してユーザーのメールボックスにアクセスする必要があるとき、そのメールボックスのリソース所有者に権限を要求する必要があります。 "リソース所有者" は、サブジェクトと呼ばれることもあります。

すべてのセキュリティ トークンは、リソース所有者を表します。 トークン内のサブジェクトのクレーム、オブジェクト ID のクレーム、および個人データが表しているのは、リソースの所有者です。 リソース所有者は、委任されたアクセス許可をクライアント アプリケーションにスコープの形式で付与するパーティです。 リソース所有者は、テナントまたはアプリケーション内の拡張されたアクセス許可を示すロールの受信者でもあります。

### リソース サーバー

[OAuth 2.0 Authorization Framework](https://tools.ietf.org/html/rfc6749#page-6) の定義によれば、保護されたリソースのホストとして、アクセス トークンを提示するクライアント アプリケーションからのリソース要求 (保護されたリソースに対する要求) を受理し、応答する機能を備えたサーバーをいいます。 保護されたリソース サーバーまたはリソース アプリケーションと呼ばれることもあります。

リソース サーバーは API を公開しており、そこで保護されているリソースに対しては、OAuth 2.0 Authorization Framework を使用して、スコープとロールを介したアクセスが強制的に適用されます。 たとえば、Microsoft Entra テナント データへのアクセスを提供する [Microsoft Graph API](https://developer.microsoft.com/graph) や、メール、カレンダーなどのデータへのアクセスを提供する Microsoft 365 API があります。

リソース アプリケーションの ID 構成は、クライアント アプリケーションと同様、Microsoft Entra テナントへの 登録 を通じて確立され、アプリケーション オブジェクトとサービス プリンシパル オブジェクトの両方が得られます。 Microsoft Graph API など、Microsoft が提供する一部の API には、プロビジョニング中にすべてのテナントで使用できるサービス プリンシパルが事前登録されています。

### 役割

アプリ ロールは、スコープと同様、リソース サーバーの保護されたリソースへのアクセスを管理するための手段です。 スコープとは異なり、ロールは、ベースラインを超えてサブジェクトに付与されている特権を表します。つまり、自分の電子メールを読み取ることがスコープであり、全員の電子メールを読み取ることができる電子メール管理者であることがロールであるということです。

アプリ ロールでは、2 つの割り当てタイプをサポートできます。"ユーザー" 割り当ては、リソースへのアクセスを必要とするユーザー/グループに対してロールベースのアクセス制御を実装します。これに対して "アプリケーション" 割り当てで実装するのは、アクセスを要求するクライアント アプリケーションの場合と同じです。 アプリ ロールは、ユーザー割り当て可能、アプリ割り当て可能、またはその両方として定義できます。

ロールは、リソースによって定義される文字列 ("経費承認者"、"読み取り専用"、"Directory.ReadWrite.All" など) です。リソースのアプリケーション マニフェストを介して管理され、リソースの [appRoles プロパティ](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal)に格納されます。 ユーザーを "ユーザー" 割り当て可能なロールに割り当てることができ、クライアント アプリケーションのアクセス許可を構成して"アプリケーション" 割り当て可能なロールを要求できます。

Microsoft Graph API によって公開されているアプリケーション ロールの詳しい説明については、[Graph API のアクセス許可スコープ](https://learn.microsoft.com/ja-jp/graph/permissions-reference)に関するページを参照してください。 実装手順の例については、「[Azure ロールの割り当てを追加または削除する](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)」をご覧ください。

### スコープ

スコープは、ロールと同様、リソース サーバーの保護されたリソースへのアクセスを管理するための手段です。 リソースへの委任されたアクセス権を所有者から付与されている[クライアント アプリケーション](https://tools.ietf.org/html/rfc6749#section-3.3)に対し、スコープベースのアクセス制御を実装する目的で使用されます。

スコープは、リソースによって定義される文字列 ("Mail.Read"、"Directory.ReadWrite.All" など) です。リソースのアプリケーション マニフェストを介して管理され、リソースの [oauth2Permissions プロパティ](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal)に格納されます。 クライアント アプリケーションの委任されたアクセス許可は、スコープにアクセスするように構成できます。

推奨される名前付け規則は、"resource.operation.constraint" 形式です。 Microsoft Graph API によって公開されているスコープの詳しい説明については、[Graph API のアクセス許可スコープ](https://learn.microsoft.com/ja-jp/graph/permissions-reference)に関するページを参照してください。 Microsoft 365 サービスによって公開されているスコープについては、[Microsoft 365 API のアクセス許可のリファレンス](https://learn.microsoft.com/ja-jp/graph/permissions-reference)に関するページを参照してください。

### セキュリティ トークン

要求を含んだ署名付きのドキュメント (OAuth 2.0 トークン、SAML 2.0 アサーションなど)。 OAuth 2.0 承認付与の場合、アクセス トークン (OAuth2)、更新トークン、[ID トークン](https://openid.net/specs/openid-connect-core-1_0.html#IDToken)がセキュリティ トークンの種類になります。いずれも [JSON Web トークン (JWT)](https://tools.ietf.org/html/rfc7519) として実装されます。

### サービス プリンシパル オブジェクト

アプリケーションを登録または更新すると、そのテナントを対象に、アプリケーション オブジェクトおよび対応するサービス プリンシパル オブジェクトの両方が作成または更新されます。 アプリケーション オブジェクトは、アプリケーションの ID 構成をグローバルに (関連するアプリケーションがアクセスできるすべてのテナントに対して) "*定義*" します。このオブジェクトをテンプレートとして、対応するサービス プリンシパル オブジェクトが "*生成*" され、実行時にローカル (特定のテナント) で使用されます。

詳細については、[アプリケーション オブジェクトとサービス プリンシパル オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)に関するページを参照してください。

### サインイン

クライアント アプリケーションがエンド ユーザーの認証を開始し、関連する状態を収集するプロセス。セキュリティ トークンを要求すると共に、アプリケーションのセッションをその状態に限定します。 状態には、ユーザー プロファイル情報などのアーティファクトや、トークンの要求から生成される情報が含まれている場合があります。

アプリケーションのサインイン機能は通常、シングル サインオン (SSO) を実装するために使用されます。 また、この機能は、エンド ユーザーが (初回サインイン時に) アプリケーションにアクセスするためのエントリ ポイントとして "サインアップ" 機能の前に実行されることがあります。 サインアップ機能は、ユーザーごとの特別な状態を収集して永続化するために使用され、 ユーザーの同意を必要とする場合があります。

### サインアウト

エンド ユーザーの認証を取り消し、サインインの過程でクライアント アプリケーションのセッションに関連付けられたユーザーの状態を解除するプロセス。

### サブジェクト

リソース所有者とも呼ばれます。

### テナント

Microsoft Entra ディレクトリのインスタンスは、Microsoft Entra テナントと呼ばれます。 次のような機能が用意されています。

- 統合アプリケーションのレジストリ サービス
- ユーザー アカウントや登録済みアプリケーションの認証
- OAuth 2.0S および SAML などの各種プロトコルをサポートするうえで必要な REST エンドポイント (承認エンドポイント、トークン エンドポイントのほか、マルチテナント アプリケーションによって使用される "共通" エンドポイントなど)。

Microsoft Entra テナントはサインアップ時に作成され、Azure サブスクリプションおよび Microsoft 365 サブスクリプションに関連付けられます。これにより、そのサブスクリプションの ID およびアクセス管理機能が提供されます。 Azure サブスクリプション管理者は、追加の Microsoft Entra テナントを作成することもできます。 テナントを利用するための各種方法の詳細については、「[Microsoft Entra テナントを取得する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)」を参照してください。 サブスクリプションと Microsoft Entra テナントの関係と、サブスクリプションの Microsoft Entra テナントへの関連付けまたは追加の方法については、「[Azure サブスクリプションを Microsoft Entra テナントに関連付けるまたは追加する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-subscriptions-associated-directory)」を参照してください。

テナントは、従業員向けまたは外部向けの用途に対して設定することができます。 選択するテナント構成は、アプリケーションで認証および承認するユーザーの種類によって異なります。 詳細については、「 [従業員と外部テナントでサポートされる機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-supported-features-customers)」を参照してください。

### トークンエンドポイント

承認サーバーによって実装されるエンドポイントの 1 つ。OAuth 2.0 承認付与をサポートするために使用されます。 付与された承認によっては、クライアントへのアクセス トークン (と関連する "更新" トークン) を取得したり、OpenID Connect プロトコルと共に使用する場合に [ID トークン](https://openid.net/specs/openid-connect-core-1_0.html)を取得したりできます。

### ユーザー エージェント ベースのクライアント

Web サーバーからコードをダウンロードしてユーザー エージェント (Web ブラウザーなど) 内で実行するクライアント アプリケーションの一種。その例としてシングル ページ アプリケーション (SPA) が挙げられます。 すべてのコードがデバイス上で実行され、機密性を保った状態でプライベートに資格情報を保存することができないため、"public" クライアントと見なされます。 詳細については、[OAuth 2.0 のクライアント タイプとプロファイル](https://tools.ietf.org/html/rfc6749#section-2.1)に関するページを参照してください。

### ユーザー フロー (外部テナントのみ)

ユーザー フローは、ユーザーがサインアップまたはサインインするために実行する手順を定義する、定義済みの構成可能なポリシーです。 ユーザー フローは、エンド ユーザーにカスタマイズ可能なエクスペリエンスを提供するために、Microsoft Entra External で使用されます。 これにより、使用する ID プロバイダー、収集される属性、使用可能な UI カスタマイズ オプションなど、ユーザー体験を定義できます。 詳細については、「 [Microsoft Entra External ID でユーザー フローを作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers)する」を参照してください。

### ユーザー プリンシパル

サービス プリンシパル オブジェクトはアプリケーション インスタンスを表現するためのセキュリティ プリンシパルです。一方、ユーザー プリンシパル オブジェクトも、セキュリティ プリンシパルのひとつですが、表現の対象となるのはユーザーです。 ユーザー オブジェクトのスキーマは、Microsoft Graph の[`User` リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/user)によって定義されます (姓や名をはじめとするユーザー関連のプロパティ、ユーザー プリンシパル名、ディレクトリ ロール メンバーシップなど)。 これにより、実行時にユーザー プリンシパルを設定するための Microsoft Entra ID のユーザー ID 構成が提供されます。 ユーザー プリンシパルは、シングル サインオン、同意の委任の記録、アクセス制御の意思決定などの際に、認証済みのユーザーを表す目的で使用されます。

### Web クライアント

Web サーバーですべてのコードを実行するクライアント アプリケーションの一種。資格情報をサーバー上に安全に保存することで、*資格情報クライアント*として動作することができます。 詳細については、[OAuth 2.0 のクライアント タイプとプロファイル](https://tools.ietf.org/html/rfc6749#section-2.1)に関するページを参照してください。

### ワークロード識別子

他のサービスやリソースを認証してアクセスするためにソフトウェア ワークロード (アプリケーション、サービス、スクリプト、コンテナーなど) によって使用される ID。 Microsoft Entra では、ワークロード ID はアプリ、サービス プリンシパル、マネージド ID です。 詳細については、[ワークロード ID の概要](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-overview)に関する記事を参照してください。

### ワークロードアイデンティティフェデレーション

(サポートされるシナリオ用に) シークレットを管理することなく、外部のアプリやサービスから Microsoft Entra によって保護されたリソースに安全にアクセスできるようにします。 詳細については、[ワークロード ID フェデレーション](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation)に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/developer-guide-conditional-access-authentication-context"} -->
## Microsoft Entra 条件付きアクセス認証コンテキストに関する開発者ガイド - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-guide-conditional-access-authentication-context
- Service: identity-platform / workforce
- Article date: 2025-09-11
- Summary: Microsoft Entra 条件付きアクセスの認証コンテキストの開発者ガイドとシナリオ

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)は、あらゆるアプリ、つまり新旧、プライベート、パブリック、オンプレミス、マルチクラウドのアプリへのアクセスに対してポリシーを適用できるようにするゼロ トラスト コントロール プレーンです。 [条件付きアクセス認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#authentication-context)を使用して、それらのアプリ内でさまざまなポリシーを適用できます。

条件付きアクセス認証コンテキスト (認証コンテキスト) を使用すると、アプリ レベルだけでなく、機密データとアクションに対して詳細なポリシーを適用できます。 ゼロ トラスト ポリシーを調整して、最小限の特権アクセスを実現しながら、ユーザーの摩擦を最小限に抑え、ユーザーの生産性とリソースの安全性を高めることができます。 現在、これは、[OpenId Connect](https://openid.net/specs/openid-connect-core-1_0.html) を使用するアプリケーションで利用され、高価値のトランザクションや従業員の個人データの表示など、機密性の高いリソースを保護するために会社で開発された認証用に使用されます。

アプリケーションとサービス内からステップアップ認証をトリガーするには、Microsoft Entra 条件付きアクセス エンジンの認証コンテキスト機能を使用します。 開発者は、アプリケーション内からエンド ユーザーに対して MFA などの強化された強力な認証を選択的に要求できるようになりました。 この機能により、開発者はアプリケーションのほとんどの部分でよりスムーズなユーザー エクスペリエンスを構築できるようになると同時に、より強力な認証制御に基づいて、より安全な操作やデータへのアクセスが保持されます。

### 問題の説明

IT 管理者と規制機関は、多くの場合、認証の追加要素とユーザーのプロンプトのバランスを取り、アプリケーションの適切なセキュリティとポリシーの準拠を実現することに苦労しています。 これは、ユーザーの生産性に影響を与える強力なポリシーか、機密性の高いリソースに対して十分な強度を持たないポリシーのどちらかを選択できます。

では、アプリで両方を組み合わせることができた場合はどうでしょうか。 セキュリティレベルが低く、ほとんどのシナリオでプロンプトが少ない機能。 その後、より機密性の高いデータにアクセスしているときに、条件付きでセキュリティ要件をステップアップしますか?

### 一般的なシナリオ

たとえば、ユーザーが多要素認証を使用して SharePoint にサインインできるときに、機密性の高いドキュメントを含む SharePoint のサイト コレクションへのアクセスには、準拠しているデバイスを使用する必要があり、かつ信頼できる IP 範囲からしかアクセスできないようにすることができます。

### 前提条件

**最初に**、認証と許可に [OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)/ [OAuth 2.0](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) プロトコルを使用して、アプリを Microsoft ID プラットフォームと統合する必要があります。 [Microsoft ID プラットフォームの認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用してアプリケーションを統合し、Microsoft Entra ID でセキュリティ保護することが推奨されます。 アプリと Microsoft ID プラットフォームの統合方法の学習は、[Microsoft ID プラットフォームのドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/)をお読みになることから始めることをお勧めします。 条件付きアクセス認証コンテキスト機能のサポートは、業界標準の [OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc) プロトコルによって提供されるプロトコル拡張機能に基づいて構築されています。 開発者は、[条件付きアクセス認証コンテキスト参照](https://learn.microsoft.com/ja-jp/graph/api/conditionalaccessroot-list-authenticationcontextclassreferences)の**値**と[クレーム要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-challenge)パラメーターを使用して、アプリがポリシーをトリガーして満たすことができるようにします。

**2 番目に**、[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)では、Microsoft Entra ID P1 ライセンスが必要になります。 ライセンスの詳細については、[Microsoft Entra の価格に関するページ](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)を参照してください。

**3 番目に**、現時点では、ユーザーがサインインするアプリケーションでのみ使用できます。 それ自体として認証されるアプリケーションはサポートされていません。 Microsoft ID プラットフォームでサポートされている認証アプリの種類とフローについては、「[認証フローとアプリケーションのシナリオ](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios)」ガイドを参照してください。

### 統合手順

サポートされている認証プロトコルを使用して統合され、条件付きアクセス機能を使用できる Microsoft Entra テナントに登録されると、この機能をアプリケーションに統合できます。

Note

この機能の詳細なチュートリアルは、[Use Conditional Access Auth Context in your app for step-up authentication](https://www.youtube.com/watch?v=_iO7CfoktTY) の録画されたセッションでも参照できます。

**最初に**、認証コンテキストを宣言し、テナントで使用できるようにします。 詳しくは、[認証コンテキストの構成](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#configure-authentication-contexts)に関する記事をご覧ください。

テナントの**認証コンテキスト ID** として値 **C1 から C99** を使用できます。 認証コンテキストの例を次に示します。

- **C1** - 強力な認証が必要
- **C2** – 準拠しているデバイスが必須
- **C3** – 信頼できる場所が必要

条件付きアクセス認証コンテキストを使用するには、条件付きアクセス ポリシーを作成または変更します。 ポリシーの例を次に示します。

- この Web アプリケーションにサインインするすべてのユーザーは、認証コンテキスト ID **C1** の 2FA を正常に完了する必要があります。
- この Web アプリケーションにサインインするすべてのユーザーは、2FA を正常に完了し、認証コンテキスト ID **C3** の定義済みの IP アドレス範囲からアプリにアクセスする必要があります。

Note

条件付きアクセス認証コンテキストの値は、アプリケーションとは別に宣言および管理されます。 アプリケーションが認証コンテキスト ID に強く依存することは推奨されません。 IT 管理者は通常、利用可能なリソースについて理解を深めるので、条件付きアクセス ポリシーを作成します。 同様に、アプリケーションが複数のテナントで使用されている場合、使用される認証コンテキスト ID は異なる可能性があり、場合によっては、まったく使用できない場合もあります。

**2 番目**: 条件付きアクセス認証コンテキストを使用する予定のアプリケーションの開発者は、まず、アプリケーション管理者または IT 管理者に、潜在的に機密性の高いアクションを認証コンテキスト ID にマップする方法を提供することをお勧めします。 手順は大まかに次のとおりです。

1. 認証コンテキスト ID にマップすることができるコード内のアクションを識別します。
2. IT 管理者が、機密性の高いアクションを使用可能な認証コンテキスト ID にマップするために使用できる、アプリの管理ポータル内の画面 (または同等の機能) を作成します。
3. コード サンプル「 [条件付きアクセス認証コンテキストを使用してステップアップ認証を実行する](https://github.com/Azure-Samples/ms-identity-ca-auth-context/blob/main/README.md) 」を参照してください。

以下の手順は、コード ベースで実行する必要がある変更です。 これらの手順は、大まかに以下で構成されています。

- MS Graph にクエリを実行して、[使用可能なすべての認証コンテキスト を一覧表示します](https://learn.microsoft.com/ja-jp/graph/api/conditionalaccessroot-list-authenticationcontextclassreferences)。
- IT 管理者が機密性が高く、高い特権を持つ操作を選択し、使用可能な認証コンテキストに対して条件付きアクセス ポリシーを使用してそれらを割り当てることができるようにします。
- このマッピング情報をデータベースまたはローカル ストアに保存します。

[Image: 認証コンテキストを作成するためのセットアップ フロー]

**3 番目**: アプリケーション (この例では、Web API を想定します) では、保存されたマッピングに対して呼び出しを評価し、その結果に応じて、クライアント アプリの要求チャレンジを発生させる必要があります。 このアクションを準備するには、次の手順を実行します。

1. 機密性が高く、認証コンテキストによって保護されている操作の場合は、前に保存した認証コンテキスト ID マッピングに対して **acrs** 要求の値を評価し、次のコード スニペットに示す[クレーム チャレンジ](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-challenge)を発生させます。
2. 次の図は、ユーザー、クライアント アプリ、Web API の間の相互作用を示しています。

    [Image: ユーザー、Web アプリ、API、Microsoft Entra ID の相互作用を示す図]

    次のコード スニペットは、[条件付きアクセス認証コンテキストを使用したステップアップ認証の実行](https://github.com/Azure-Samples/ms-identity-ca-auth-context/blob/main/README.md)に関するページのコード サンプルです。 API の最初のメソッド `CheckForRequiredAuthContext()` は、以下を実行します。

    - 呼び出されているアプリケーションのアクションにステップアップ認証が必要かどうかを確認します。 そのために、このメソッド用に保存されたマッピングがデータベースにあるかどうかを確認します。
    - このアクションで昇格された認証コンテキストが実際に必要な場合は、既存の一致する認証コンテキスト ID があるか **acrs** 要求を確認します。
    - 一致する認証コンテキスト ID が見つからない場合は、[クレーム チャレンジ](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-challenge#claims-challenge-header-format)が発生します。

    ```csharp
    public void CheckForRequiredAuthContext(string method)
    {
        string authType = _commonDBContext.AuthContext.FirstOrDefault(x => x.Operation == method
                    && x.TenantId == _configuration["AzureAD:TenantId"])?.AuthContextId;
    
        if (!string.IsNullOrEmpty(authType))
        {
            HttpContext context = this.HttpContext;
            string authenticationContextClassReferencesClaim = "acrs";
    
            if (context == null || context.User == null || context.User.Claims == null
                || !context.User.Claims.Any())
            {
                throw new ArgumentNullException("No Usercontext is available to pick claims from");
            }
    
            Claim acrsClaim = context.User.FindAll(authenticationContextClassReferencesClaim).FirstOrDefault(x
                => x.Value == authType);
    
            if (acrsClaim == null || acrsClaim.Value != authType)
            {
                if (IsClientCapableofClaimsChallenge(context))
                {
                    string clientId = _configuration.GetSection("AzureAd").GetSection("ClientId").Value;
                    var base64str = Convert.ToBase64String(Encoding.UTF8.GetBytes("{\"access_token\":{\"acrs\":{\"essential\":true,\"value\":\"" + authType + "\"}}}"));
    
                    context.Response.Headers.Append("WWW-Authenticate", $"Bearer realm=\"\", authorization_uri=\"https://login.microsoftonline.com/common/oauth2/authorize\", client_id=\"" + clientId + "\", error=\"insufficient_claims\", claims=\"" + base64str + "\", cc_type=\"authcontext\"");
                    context.Response.StatusCode = (int)HttpStatusCode.Unauthorized;
                    string message = string.Format(CultureInfo.InvariantCulture, "The presented access tokens had insufficient claims. Please request for claims requested in the WWW-Authentication header and try again.");
                    context.Response.WriteAsync(message);
                    context.Response.CompleteAsync();
                    throw new UnauthorizedAccessException(message);
                }
                else
                {
                    throw new UnauthorizedAccessException("The caller does not meet the authentication  bar to carry our this operation. The service cannot allow this operation");
                }
            }
        }
    }
    ```

    Note

    クレーム チャレンジの形式については、[Microsoft ID プラットフォームのクレーム チャレンジ](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-challenge)に関する記事で説明しています。
3. クライアント アプリケーションで、クレーム チャレンジをインターセプトし、ユーザーを Microsoft Entra ID に再度リダイレクトして、さらにポリシーを評価します。 次のコード スニペットは、[条件付きアクセス認証コンテキストを使用したステップアップ認証の実行](https://github.com/Azure-Samples/ms-identity-ca-auth-context/blob/main/README.md)に関するページのコード サンプルです。

    ```csharp
    internal static string ExtractHeaderValues(WebApiMsalUiRequiredException response)
    {
        if (response.StatusCode == System.Net.HttpStatusCode.Unauthorized && response.Headers.WwwAuthenticate.Any())
        {
            AuthenticationHeaderValue bearer = response.Headers.WwwAuthenticate.First(v => v.Scheme == "Bearer");
            IEnumerable<string> parameters = bearer.Parameter.Split(',').Select(v => v.Trim()).ToList();
            var errorValue = GetParameterValue(parameters, "error");
    
            try
            {
                // read the header and checks if it contains error with insufficient_claims value.
                if (null != errorValue && "insufficient_claims" == errorValue)
                {
                    var claimChallengeParameter = GetParameterValue(parameters, "claims");
                    if (null != claimChallengeParameter)
                    {
                        var claimChallenge = ConvertBase64String(claimChallengeParameter);
    
                        return claimChallenge;
                    }
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
        return null;
    }
    ```

    Web API の呼び出しで例外を処理します。クレーム チャレンジが提示された場合、ユーザーを再び Microsoft Entra ID にリダイレクトして、さらに処理を行います。

    ```csharp
    try
    {
        // Call the API
        await _todoListService.AddAsync(todo);
    }
    catch (WebApiMsalUiRequiredException hex)
    {
        // Challenges the user if exception is thrown from Web API.
        try
        {
            var claimChallenge =ExtractHeaderValues(hex);
            _consentHandler.ChallengeUser(new string[] { "user.read" }, claimChallenge);
    
            return new EmptyResult();
        }
        catch (Exception ex)
        {
            _consentHandler.HandleException(ex);
        }
    
        Console.WriteLine(hex.Message);
    }
    return RedirectToAction("Index");
    ```
4. (省略可能) クライアントの機能を宣言します。 クライアント機能は、Web API などのリソース プロバイダー (RP) が、クライアント アプリケーションが要求の課題を理解しているかどうかを検出し、それに応じて応答をカスタマイズするのに役立ちます。 この機能は、すべての API クライアントがクレーム チャレンジを処理できるわけではなく、以前の一部のクライアントでは依然として別の応答が必要になる場合に便利であることがあります。 詳細については、「[クライアント機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-challenge#client-capabilities)」のセクションを参照してください。

### 注意事項とレコメンデーション

アプリで認証コンテキストの値をハードコーディングしないでください。 アプリは、[MS Graph 呼び出しを使用して](https://learn.microsoft.com/ja-jp/graph/api/resources/authenticationcontextclassreference)、認証コンテキストの読み取りと適用を行う必要があります。 この方法は、[マルチテナント アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant)の場合に重要になります。 認証コンテキストの値は Microsoft Entra テナントによって異なり、Microsoft Entra ID Free エディションでは使用できません。 アプリがコードで認証コンテキストのクエリ、設定、使用を行う方法の詳細については、「 [条件付きアクセス認証コンテキストを使用してステップアップ認証を実行する」](https://github.com/Azure-Samples/ms-identity-ca-auth-context/blob/main/README.md)コード サンプルを参照してください。

アプリ自体が条件付きアクセス ポリシーのターゲットになる場合には認証コンテキストを使用しないでください。 この機能は、アプリケーションの一部で、ユーザーが高いレベルの認証を満たす必要がある場合に最適です。

### コード サンプル

- [条件付きアクセス認証コンテキストを使用して、Web アプリで高特権の操作のステップアップ認証を実行する](https://github.com/Azure-Samples/ms-identity-dotnetcore-ca-auth-context-app/blob/main/README.md)
- [条件付きアクセス認証コンテキストを使用して、Web API で高特権の操作のステップアップ認証を実行する](https://github.com/Azure-Samples/ms-identity-ca-auth-context/blob/main/README.md)
- [条件付きアクセス認証コンテキストを使用して、React シングルページ アプリケーションと Express Web API で高特権の操作のステップアップ認証を実行する](https://github.com/Azure-Samples/ms-identity-javascript-react-tutorial/tree/main/6-AdvancedScenarios/3-call-api-acrs)

### 条件付きアクセスの想定される動作における認証コンテキスト [ACR]

### 要求における認証コンテキストの明示的な充足

クライアントは、要求本文の要求を通じて、認証コンテキスト (ACRS) を持つトークンを明示的に要求できます。 ACRS が要求された場合、条件付きアクセスでは、すべてのチャレンジが完了した場合に、要求された ACRS でトークンを発行できます。

### テナントの条件付きアクセスによって認証コンテキストが保護されていない場合の想定される動作

条件付きアクセスは、ACRS 値に割り当てられているすべての条件付きアクセス ポリシーが満たされている場合に、トークンの要求で ACRS を発行できます。 条件付きアクセス ポリシーが ACRS 値に割り当てられていない場合でも、すべてのポリシー要件が満たされているため、要求が発行される可能性があります。

### ACRS が明示的に要求された場合に想定される動作の概要表

| ACRS の要求 | ポリシーの適用 | 制御が満たされているか | 要求に ACRS が追加されたか |
| --- | --- | --- | --- |
| あり | いいえ | あり | あり |
| あり | あり | いいえ | いいえ |
| あり | あり | あり | あり |
| あり | ACRS で構成されたポリシーはない | あり | あり |

### 日和見的な評価による暗黙的な認証コンテキストの充足

リソース プロバイダーは、オプションの "acrs" 要求をオプトインできます。 条件付きアクセスは、Microsoft Entra ID への新しいトークンを取得するためのラウンド トリップを回避するために、日和見的に ACRS をトークン要求に追加しようと試みます。 この評価では、条件付きアクセスによって認証コンテキストのチャレンジを保護するポリシーが既に満たされているかどうかの確認が行われ、満たされている場合はトークン要求に ACRS が追加されます。

Note

各トークンの種類は、個別にオプトインする必要があります (ID トークン、アクセス トークン)。

リソース プロバイダーがオプションの "acrs" 要求をオプトインしない場合、トークンで ACRS を取得する唯一の方法は、トークン要求で明示的に要求することです。 日和見評価の利点は得られません。そのため、必要な ACRS がトークン要求から欠落するたびに、リソース プロバイダーは要求に含まれる新しいトークンを取得するようクライアントに要求します。

### 暗黙的な ACRS の日和見的な評価における認証コンテキストとセッション制御で想定される動作

#### サインイン頻度 (間隔別)

条件付きアクセスは、現在存在するすべての認証要素の認証インスタンスがサインイン頻度の間隔に収まっている場合、ACRS の日和見的な評価における「サインイン頻度 (間隔別)」が満たされていると見なします。 いずれかの認証要素が古い場合、間隔によるサインイン頻度は満たされておらず、ACRS は日和見的にトークンで発行されません。

#### Cloud App Security (CAS)

条件付きアクセスでは、その要求中に CAS セッションが確立された場合、CAS セッション制御は日和見的 ACRS 評価に対して満たされていると見なされます。 たとえば、ある要求が届き、これに何らかの条件付きアクセス ポリシーが適用されて CAS セッションが強制され、さらに CAS セッションを要求する条件付きアクセス ポリシーがあった場合、CAS セッションが強制されるため、日和見的な評価の CAS セッション制御は満たされることになります。

### テナントに認証コンテキストを保護する条件付きアクセス ポリシーが含まれている場合の想定される動作

次の表は、ACRS がオポチュニスティック評価によってトークンのクレームに追加されるすべてのコーナーケースを示しています。

**ポリシー A**: "c1" acrs を要求する際に、ユーザー "Ariel" を除くすべてのユーザーから MFA を要求します。 **ポリシー B**: "c2" または "c3" acrs を要求する際に、ユーザー "Jay" を除くすべてのユーザーをブロックします。

| Flow | ACRS の要求 | ポリシーの適用 | 制御が満たされているか | 要求に ACRS が追加されたか |
| --- | --- | --- | --- | --- |
| Ariel がアクセス トークンを要求する | "c1" | None | はい ("c1" の場合)。 いいえ ("c2" と "c3" の場合) | "c1" (要求済み) |
| Ariel がアクセス トークンを要求する | "c2" | ポリシー B | ポリシー B によってブロックされる | None |
| Ariel がアクセス トークンを要求する | None | None | はい ("c1" の場合)。 いいえ ("c2" と "c3" の場合) | "c1" (ポリシー A から日和見的に追加) |
| Jay がアクセス トークンを要求する (MFA なし) | "c1" | ポリシー A | いいえ | None |
| Jay がアクセス トークンを要求する (MFA あり) | "c1" | ポリシー A | あり | "c1" (要求済み)、"c2" (ポリシー B から日和見的に追加)、"c3" (ポリシー B から日和見的に追加) |
| Jay がアクセス トークンを要求する (MFA なし) | "c2" | None | はい ("c2" と "c3" の場合)。 いいえ ("c1" の場合) | "c2" (要求済み)、"c3" (ポリシー B から日和見的に追加) |
| Jay がアクセス トークンを要求する (MFA あり) | "c2" | None | はい ("c1"、"c2"、"c3" の場合) | "c1" (A からのベスト エフォート)、"c2" (要求済み)、"c3" (ポリシー B から日和見的に追加) |
| Jay がアクセス トークンを要求する (MFA あり) | None | None | はい ("c1"、"c2"、"c3" の場合) | "c1"、"c2"、"c3" がすべて日和見的に追加 |
| Jay がアクセス トークンを要求する (MFA なし) | None | None | はい ("c2" と "c3" の場合)。 いいえ ("c1" の場合) | "c2"、"c3" がすべて日和見的に追加 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/developer-support-help-options"} -->
## Microsoft ID プラットフォーム開発者向けのサポートとヘルプのオプション - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-support-help-options
- Service: identity-platform
- Article date: 2023-05-31
- Summary: Microsoft Entra ID と Microsoft ID プラットフォームのその他のコンポーネントと統合される ID およびアクセス管理 (IAM) ソリューションを構築する際に役立つ情報を入手し、質問の答えを見つける方法について説明します。

ドキュメントに記載されていない問題を解決するために質問する必要がある場合は、専門家にご連絡ください。 ここでは、Microsoft ID プラットフォームと統合するアプリケーションを開発する際に、問題を解決するための推奨事項をいくつか紹介します。

### Azure サポート要求を作成する

[Image: Azure サポート]

さまざまな [Azure サポート オプションを調べて、最も適したプランを選択します](https://azure.microsoft.com/support/plans)。 Microsoft Entra 管理センターでは、サポート リクエストの作成と管理に関する次のオプションを使用できます。

- Azure サポート プランをお持ちの場合は、[こちらからサポート要求をオープン](https://entra.microsoft.com/#view/Microsoft_Azure_Support/NewSupportRequestV3Blade/callerName/ActiveDirectory/issueType/technical)します。
- 外部テナントで Microsoft Entra 外部 ID を使っている場合、現在、サポート リクエスト機能は外部テナントの技術的な問題には使用できません。 ただし、**[新しいサポート リクエスト]** ページの **[フィードバックの提供]** リンクを使用してフィードバックを提供できます。 または、Microsoft Entra ワークフォース テナントに切り替えて、[サポート リクエストを開く](https://entra.microsoft.com/#view/Microsoft_Azure_Support/NewSupportRequestV3Blade/callerName/ActiveDirectory/issueType/technical)ことができます。
- Azure のお客様でない場合は、 [ビジネス向け Microsoft サポート](https://support.serviceshub.microsoft.com/supportforbusiness)を使用してサポート リクエストを開くことができます。

### Microsoft Q&A に質問を投稿する

[Image: Microsoft Q & A]

ID アプリの開発に関する質問については、Microsoft のエンジニア、Azure Most Valuable Professionals (MVP)、およびエキスパー トコミュニティのメンバーから直接回答が得られます。

[Microsoft Q&A](https://learn.microsoft.com/ja-jp/answers/products/) は、Azure のコミュニティ サポートの推奨される情報源です。

Microsoft Q&A を検索しても問題に対する回答が見つからない場合は、新しい質問を送信します。 [質の高い質問](https://learn.microsoft.com/ja-jp/answers/articles/24951/how-to-write-a-quality-question.html)をするときは、次のタグのいずれかを使います。

| コンポーネント/領域 | Tags |
| --- | --- |
| Microsoft Entra 外部 ID/External Identities | [Microsoft Entra 外部 ID](https://aka.ms/microsoftentraexternalid) |
| Microsoft Entra B2B / External Identities | [Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/answers/tags/438/entra-external-id) |
| Azure AD B2C | [Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/answers/tags/438/entra-external-id) |
| その他のすべての Microsoft Entra 領域 | [Microsoft Entra ID](https://learn.microsoft.com/ja-jp/answers/tags/49/azure-active-directory) |
| Azure RBAC | [Azure ロールベースのアクセス制御](https://learn.microsoft.com/ja-jp/answers/tags/189/azure-rbac) |
| Azure Key Vault | [Azure Key Vault](https://learn.microsoft.com/ja-jp/answers/tags/5/azure-key-vault) |
| Microsoft Security | [Microsoft Defender for Cloud](https://learn.microsoft.com/ja-jp/answers/tags/392/defender-for-cloud) |
| Microsoft Sentinel | [Microsoft Sentinel](https://learn.microsoft.com/ja-jp/answers/tags/423/microsoft-sentinel) |
| Microsoft Entra Domain Services | [Microsoft Entra Domain Services](https://learn.microsoft.com/ja-jp/answers/tags/222/azure-active-directory-domain) |
| Azure Windows および Linux Virtual Machines | [Azure Virtual Machines](https://learn.microsoft.com/ja-jp/answers/tags/94/azure-virtual-machines) |

### GitHub の issue を作成する

[Image: GitHub イメージ]

Microsoft 認証ライブラリ (MSAL) のいずれかに関するヘルプが必要な場合は、GitHub のリポジトリで問題を開きます。

| MSAL | GitHub の問題の URL |
| --- | --- |
| MSAL for Android | https://github.com/AzureAD/microsoft-authentication-library-for-android/issues |
| MSAL Angular | https://github.com/AzureAD/microsoft-authentication-library-for-js/issues |
| iOS および macOS 用の MSAL | https://github.com/AzureAD/microsoft-authentication-library-for-objc/issues |
| MSAL Java | https://github.com/AzureAD/microsoft-authentication-library-for-java/issues |
| MSAL.js | https://github.com/AzureAD/microsoft-authentication-library-for-js/issues |
| MSAL.NET | https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/issues |
| MSAL Node | https://github.com/AzureAD/microsoft-authentication-library-for-js/issues |
| MSAL Python | https://github.com/AzureAD/microsoft-authentication-library-for-python/issues |
| MSAL React | https://github.com/AzureAD/microsoft-authentication-library-for-js/issues |

### 更新プログラムと新しいリリースに関する最新情報を入手する

[Image: 最新情報を入手]

- [Azure の更新情報](https://azure.microsoft.com/updates/?category=identity): 重要な製品の更新プログラム、ロードマップ、および発表について確認できます。
- [ドキュメントの最新情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/whats-new-docs): Microsoft ID プラットフォームのドキュメントの最新情報を確認できます。
- [Microsoft Entra ブログ](https://techcommunity.microsoft.com/t5/microsoft-entra-azure-ad-blog/bg-p/Identity): Microsoft Entra ID に関するニュースと情報を取得します。
- [技術コミュニティ](https://techcommunity.microsoft.com/t5/azure-active-directory-identity/bg-p/Identity/): 経験を共有し、エキスパートとの交流を通じて学ぶことができます。

### 製品のアイデアを共有する

Microsoft ID プラットフォームを改善するためのアイデアをお持ちですか? 他のユーザーが送信したアイデアを参照して投票したり、自分のアイデアを送信したりすることができます。

https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/enterprise-app-role-management"} -->
## ロール要求を構成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/enterprise-app-role-management
- Service: identity-platform
- Article date: 2023-06-09
- Summary: Microsoft Entra ID でエンタープライズ アプリケーション用の SAML トークン内に発行されるロール要求を構成する方法について説明します。

アプリケーションが承認された後に受け取るアクセス トークンのロール要求をカスタマイズできます。 トークンでカスタム ロールを想定するアプリケーションの場合は、この機能を使用します。 ロールは、必要な数だけ作成できます。

### 前提条件

- テナントが構成されている Microsoft Entra サブスクリプション。 詳細については、「 [クイック スタート: テナントを設定する」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)参照してください。
- テナントに追加されているエンタープライズ アプリケーション。 詳細については、「 [クイック スタート: エンタープライズ アプリケーションを追加する」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)参照してください。
- アプリケーションでシングル サインオン (SSO) が構成されている。 詳細については、「 [エンタープライズ アプリケーションのシングル サインオンを有効にする」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso)参照してください。
- ロールに割り当てられているユーザー アカウント。 詳細については、「 [クイック スタート: ユーザー アカウントを作成して割り当てる」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)参照してください。

注意

この記事では、API を使用して、サービス プリンシパルでアプリケーション ロールを作成、更新、削除する方法について説明します。 アプリ ロールに新しいユーザー インターフェイスを使用するには、「 [アプリケーションにアプリ ロールを追加し、トークンで受け取る](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)」を参照してください。

### エンタープライズ アプリケーションを見つける

次の手順を実行して、エンタープライズ アプリケーションを見つけます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**すべてのアプリケーション**を参照します。
3. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。
4. アプリケーションを選択したら、概要ペインからオブジェクト ID をコピーします。

### ロールを追加する

Microsoft Graph Explorer を使用してエンタープライズ アプリケーションにロールを追加します。

1. 別のウィンドウで [Microsoft Graph Explorer を](https://developer.microsoft.com/graph/graph-explorer) 開き、テナントの管理者資格情報を使用してサインインします。

    注意

    クラウド アプリケーション管理者とアプリケーション管理者ロールは、このシナリオでは機能しません。特権ロール管理者を使用してください。
2. **[アクセス許可の変更]** を選択し、リスト内のおよび`Application.ReadWrite.All`のアクセス許可に対して `Directory.ReadWrite.All` を選択します。
3. 次の要求の `<objectID>` を、以前に記録したオブジェクト ID に置き換えてからクエリを実行します。

    `https://graph.microsoft.com/v1.0/servicePrincipals/<objectID>`
4. エンタープライズ アプリケーションは、サービス プリンシパルとも呼ばれます。 返されたサービス プリンシパル オブジェクトから **appRoles** プロパティを記録します。 次の例は、一般的な appRoles プロパティを示しています。

    ```json
    {
      "appRoles": [
        {
          "allowedMemberTypes": [
            "User"
          ],
          "description": "msiam_access",
          "displayName": "msiam_access",
          "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
          "isEnabled": true,
          "origin": "Application",
          "value": null
        }
      ]
    }
    ```
5. Graph エクスプローラーで、メソッドを **GET** から **PATCH** に変更します。
6. Graph エクスプローラーの **[要求本文** ] ウィンドウに以前に記録した appRoles プロパティをコピーし、新しいロール定義を追加し、[ **クエリの実行** ] を選択してパッチ操作を実行します。 成功メッセージにより、ロールの作成が確認されます。 次の例は、 *管理者* ロールの追加を示しています。

    ```json
    {
      "appRoles": [
        {
          "allowedMemberTypes": [
            "User"
          ],
          "description": "msiam_access",
          "displayName": "msiam_access",
          "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
          "isEnabled": true,
          "origin": "Application",
          "value": null
        },
        {
          "allowedMemberTypes": [
            "User"
          ],
          "description": "Administrators Only",
          "displayName": "Admin",
          "id": "11bb11bb-cc22-dd33-ee44-55ff55ff55ff",
          "isEnabled": true,
          "origin": "ServicePrincipal",
          "value": "Administrator"
        }
      ]
    }
    ```

    要求本文には、新しいロールに加えて `msiam_access` ロール オブジェクトを含める必要があります。 要求本文に既存のロールを含めなくすると、 **appRoles** オブジェクトから削除されます。 また、組織が必要とする数のロールを追加できます。 SAML 応答の要求値として、これらのロールの値が送信されます。 新しいロールの ID の GUID 値を生成するには、 [オンライン GUID/UUID ジェネレーター](https://www.guidgenerator.com/)などの Web ツールを使用します。 応答の appRoles プロパティには、クエリの要求本文の内容が含まれます。

### 属性の編集

属性を更新して、トークンに含まれるロール要求を定義します。

1. Microsoft Entra 管理センターでアプリケーションを見つけて、左側のメニューで [ **シングル サインオン** ] を選択します。
2. [ **属性と要求** ] セクションで、[編集] を選択 **します**。
3. [ **新しい要求の追加] を選択します**。
4. [ **名前** ] ボックスに、属性名を入力します。 この例では、 **要求名としてロール名** を使用します。
5. **[名前空間**] ボックスは空白のままにします。
6. **ソース属性**の一覧から **user.assignedroles** を選択します。
7. **[保存] を選択します**。 新しい **ロール名** 属性が [ **属性と要求** ] セクションに表示されます。 これで、アプリケーションにサインインするときに、この要求がアクセス トークンに含まれるようになります。

### ロールを割り当てる

より多くのロールでサービス プリンシパルを修正したら、対応するロールにユーザーを割り当てることができます。

1. Microsoft Entra 管理センターで、ロールが追加されたアプリケーションを見つけます。
2. 左側のメニューで [ **ユーザーとグループ** ] を選択し、新しいロールを割り当てるユーザーを選択します。
3. ウィンドウの上部にある **[割り当ての編集]** を選択して、ロールを変更します。
4. [ **選択なし**] を選択し、一覧からロールを選択し、[選択] を **選択します**。
5. [ **割り当て]** を選択して、ユーザーにロールを割り当てます。

### ロールを更新する

既存のロールを更新するには、以下の手順を実行します。

1. [Microsoft Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)を開きます。
2. Graph Explorer サイトに特権ロール管理者としてサインインします。
3. 次の要求にある `<objectID>` を、概要ペインに記載されたアプリケーションのオブジェクト ID に置き換え、クエリを実行します。

    `https://graph.microsoft.com/v1.0/servicePrincipals/<objectID>`
4. 返されたサービス プリンシパル オブジェクトから **appRoles** プロパティを記録します。
5. Graph エクスプローラーで、メソッドを **GET** から **PATCH** に変更します。
6. 以前に記録した appRoles プロパティを Graph エクスプローラーの **[要求本文** ] ウィンドウにコピーし、ロール定義を更新して、[ **クエリの実行** ] を選択してパッチ操作を実行します。

### ロールの削除

既存のロールを削除するには、以下の手順を実行します。

1. [Microsoft Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)を開きます。
2. Graph Explorer サイトに特権ロール管理者としてサインインします。
3. 次の要求にある `<objectID>` を、Azure portal の概要ペインに記載されたアプリケーションのオブジェクト ID に置き換え、クエリを実行します。

    `https://graph.microsoft.com/v1.0/servicePrincipals/<objectID>`
4. 返されたサービス プリンシパル オブジェクトから **appRoles** プロパティを記録します。
5. Graph エクスプローラーで、メソッドを **GET** から **PATCH** に変更します。
6. 以前に記録した appRoles プロパティを Graph エクスプローラーの **[要求本文** ] ウィンドウにコピーし、削除するロールの **IsEnabled** 値を **false** に設定し、[ **クエリの実行** ] を選択してパッチ操作を実行します。 ロールを削除するには、まず無効にする必要があります。
7. ロールが無効になった後、 **appRoles** セクションからそのロール ブロックを削除します。 メソッドを **PATCH** のままにして、[クエリの **実行** ] をもう一度選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/federation-metadata"} -->
## Microsoft Entra フェデレーション メタデータ - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/federation-metadata
- Service: identity-platform
- Article date: 2024-10-01
- Summary: この記事では、Microsoft Entra ID が Microsoft Entra トークンを受け入れるサービスに対して発行するフェデレーション メタデータ ドキュメントについて説明します。

Microsoft Entra ID は、Microsoft Entra ID が発行するセキュリティ トークンを受け入れるように構成されているサービスのフェデレーション メタデータ ドキュメントを発行します。 フェデレーション メタデータ ドキュメントの形式は、「[Web Services Federation Language (WS-Federation) Version 1.2](https://docs.oasis-open.org/wsfed/federation/v1.2/os/ws-federation-1.2-spec-os.html)」で説明されています。これは、[OASIS SAML (Security Assertion Markup Language) v2.0 のメタデータ](https://docs.oasis-open.org/security/saml/v2.0/saml-metadata-2.0-os.pdf)の拡張です。

### テナント固有およびテナント独立のメタデータ エンドポイント

Microsoft Entra ID は、テナント固有およびテナント独立のエンドポイントを発行します。

テナント固有のエンドポイントは、特定のテナント用に設計されています。 テナント固有のフェデレーション メタデータには、テナント固有の発行者とエンドポイントの情報など、テナントに関する情報が含まれます。 単一のテナントにアクセスを制限するアプリケーションでは、テナント固有のエンドポイントを使用します。

テナント独立のエンドポイントは、すべての Microsoft Entra テナントに共通する情報を提供します。 この情報は、 *login.microsoftonline.com* でホストされているテナントに適用され、テナント全体で共有されます。 マルチテナント アプリケーションの場合は、特定のテナントに関連付けられていないため、テナント独立のエンドポイントをお勧めします。

### フェデレーション メタデータ エンドポイント

Microsoft Entra ID は、フェデレーション メタデータを `https://login.microsoftonline.com/<TenantDomainName>/FederationMetadata/2007-06/FederationMetadata.xml`で発行します。

**テナント固有のエンドポイント**の場合、`TenantDomainName` に次の種類のいずれかを指定できます。

- Microsoft Entra テナントの登録済みドメイン名 (例: `contoso.onmicrosoft.com`)。
- `aaaabbbb-0000-cccc-1111-dddd2222eeee`など、ドメインの変更できないテナント ID。

**テナント独立のエンドポイントの場合**、`TenantDomainName` は `common` です。 このドキュメントでは、login.microsoftonline.com でホストされているすべての Microsoft Entra テナントに共通するフェデレーション メタデータの要素のみを示します。

たとえば、テナント固有のエンドポイントは、 `https://login.microsoftonline.com/contoso.onmicrosoft.com/FederationMetadata/2007-06/FederationMetadata.xml`にすることができます。 テナント独立のエンドポイントは、https://login.microsoftonline.com/common/FederationMetadata/2007-06/FederationMetadata.xml です。 ブラウザーにこの URL を入力することで、フェデレーション メタデータ ドキュメントを表示できます。

### フェデレーション メタデータの内容

次のセクションでは、Microsoft Entra ID によって発行されたトークンを使うサービスに必要な情報を提供します。

#### エンティティ ID

`EntityDescriptor` 要素は `EntityID` 属性を含みます。 `EntityID` 属性の値は発行者、つまり、トークンを発行した Security Token Service (STS) を表します。 トークンを受信したときに、発行者を検証することが重要です。

次のメタデータは、`EntityDescriptor` 要素を含む、サンプルのテナント固有の `EntityID` 要素を示しています。

```xml
<EntityDescriptor
xmlns="urn:oasis:names:tc:SAML:2.0:metadata"
ID="_00aa00aa-bb11-cc22-dd33-44ee44ee44ee"
entityID="https://sts.windows.net/00aa00aa-bb11-cc22-dd33-44ee44ee44ee/">
```

テナント独立のエンドポイントのテナント ID を自分のテナント ID に置き換えて、テナント固有の `EntityID` 値を作成することができます。 結果の値は、トークンの発行者と同じになります。 この戦略では、マルチテナント アプリケーションを使用して特定のテナントの発行者を検証できます。

次のメタデータは、テナント独立の `EntityID` 要素のサンプルを示します。 なお、 `{tenant}` はリテラルであり、プレースホルダーではないことに注意してください。

```xml
<EntityDescriptor
xmlns="urn:oasis:names:tc:SAML:2.0:metadata"
ID="="_aaaabbbb-0000-cccc-1111-dddd2222eeee"
entityID="https://sts.windows.net/{tenant}/">
```

#### トークン署名証明書

サービスが Microsoft Entra テナントによって発行されたトークンを受信するとき、トークンの署名は、フェデレーション メタデータ ドキュメントで発行された署名キーによって、検証される必要があります。 フェデレーション メタデータには、テナントでトークンの署名に使用される証明書の公開部分が含まれています。 証明書の未加工のバイト数は、 `KeyDescriptor` 要素にあります。 トークン署名証明書が署名で有効なのは、`use` 属性の値が `signing` の場合だけです。

Microsoft Entra ID によって発行されたフェデレーション メタデータ ドキュメントには、Microsoft Entra ID によって署名証明書の更新が準備されているときなどに、複数の署名キーが含まれている可能性があります。 フェデレーション メタデータ ドキュメントに複数の証明書が含まれている場合、トークンを検証するサービスは、ドキュメント内のすべての証明書をサポートする必要があります。

次のメタデータは、署名キーを含むサンプルの `KeyDescriptor` 要素を示しています。

```xml
<KeyDescriptor use="signing">
<KeyInfo xmlns="https://www.w3.org/2000/09/xmldsig#">
<X509Data>
<X509Certificate>
aB1cD2eF-3gH4i...J5kL6-mN7oP8qR=
</X509Certificate>
</X509Data>
</KeyInfo>
</KeyDescriptor>
```

`KeyDescriptor` 要素は、フェデレーション メタデータ ドキュメントでは、WS-Federation 固有のセクションと SAML 固有のセクションという 2 つの場所にあります。 両方のセクションで発行された証明書は同じになります。

WS-Federation 固有のセクションで、WS-Federation メタデータ リーダーは、`RoleDescriptor` 型を含む `SecurityTokenServiceType` 要素から証明書を読み取ります。

`RoleDescriptor` 要素の例を次に示します。

```xml
<RoleDescriptor xmlns:xsi="https://www.w3.org/2001/XMLSchema-instance" xmlns:fed="https://docs.oasis-open.org/wsfed/federation/200706" xsi:type="fed:SecurityTokenServiceType" protocolSupportEnumeration="https://docs.oasis-open.org/wsfed/federation/200706">
```

SAML に固有のセクションで、WS-Federation メタデータ リーダーは、 `IDPSSODescriptor` 要素から証明書を読み取ります。

`IDPSSODescriptor` 要素の例を次に示します。

```xml
<IDPSSODescriptor protocolSupportEnumeration="urn:oasis:names:tc:SAML:2.0:protocol">
```

テナント固有の証明書とテナント独立の証明書の形式には、違いはありません。

#### WS-Federation エンドポイントの URL

フェデレーション メタデータには、WS-Federation プロトコルで Microsoft Entra ID がシングル サインインとシングル サインアウトに使う URL が含まれています。 このエンドポイントは `PassiveRequestorEndpoint` 要素にあります。

次のメタデータは、テナント固有のエンドポイントに対するサンプルの `PassiveRequestorEndpoint` 要素を示しています。

```xml
<fed:PassiveRequestorEndpoint>
<EndpointReference xmlns="https://www.w3.org/2005/08/addressing">
<Address>
https://login.microsoftonline.com/aaaabbbb-0000-cccc-1111-dddd2222eeee/wsfed
</Address>
</EndpointReference>
</fed:PassiveRequestorEndpoint>
```

テナント独立のエンドポイントの場合は、次の例に示すように、WS-Federation URL は WS-Federation エンドポイントにあります。

```xml
<fed:PassiveRequestorEndpoint>
<EndpointReference xmlns="https://www.w3.org/2005/08/addressing">
<Address>
https://login.microsoftonline.com/common/wsfed
</Address>
</EndpointReference>
</fed:PassiveRequestorEndpoint>
```

#### SAML プロトコル エンドポイントの URL

フェデレーション メタデータには、SAML 2.0 プロトコルで Microsoft Entra ID がシングル サインインとシングル サインアウトに使う URL が含まれています。 これらのエンドポイントは、 `IDPSSODescriptor` 要素にあります。

サインイン URL とサインアウト URL は、`SingleSignOnService` 要素と `SingleLogoutService` 要素にあります。

次のメタデータは、テナント固有のエンドポイントに対するサンプルの `PassiveResistorEndpoint` を示しています。

```xml
<IDPSSODescriptor protocolSupportEnumeration="urn:oasis:names:tc:SAML:2.0:protocol">
…
    <SingleLogoutService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect" Location="https://login.microsoftonline.com/contoso.onmicrosoft.com/saml2" />
    <SingleSignOnService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect" Location="https://login.microsoftonline.com/contoso.onmicrosoft.com/saml2" />
  </IDPSSODescriptor>
```

同様に、次の例に示すように、共通の SAML 2.0 プロトコル エンドポイントのエンドポイントは、テナント独立のフェデレーション メタデータに発行されます。

```xml
<IDPSSODescriptor protocolSupportEnumeration="urn:oasis:names:tc:SAML:2.0:protocol">
…
    <SingleLogoutService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect" Location="https://login.microsoftonline.com/common/saml2" />
    <SingleSignOnService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect" Location="https://login.microsoftonline.com/common/saml2" />
  </IDPSSODescriptor>
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/how-applications-are-added"} -->
## アプリを Microsoft Entra ID に追加する方法と理由 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/how-applications-are-added
- Service: identity-platform
- Article date: 2022-10-26
- Summary: Microsoft Entra ID にアプリケーションを追加する意味とその方法

Microsoft Entra ID には、2 つの表現のアプリケーションがあります。

- [アプリケーション オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals#application-object) - 例外がありますが、アプリケーション オブジェクトはアプリケーションの定義と見なすことができます。
- [サービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals#service-principal-object) - アプリケーションのインスタンスと見なすことができます。 一般的に、サービス プリンシパルはアプリケーション オブジェクトを参照し、1 つのアプリケーション オブジェクトは複数のディレクトリの複数のプリンシパルによって参照されます。

### アプリケーション オブジェクトの概要とその由来

Microsoft Entra 管理センターで [アプリケーション オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals#application-object) を管理するには、 [アプリの登録エクスペリエンスを](https://entra.microsoft.com/#view/Microsoft_AAD_RegisteredApps/ApplicationsListBlade) 使用します。 アプリケーション オブジェクトは、Microsoft Entra ID に対するアプリケーションについて記述します。アプリケーション オブジェクトはアプリケーションの定義と考えることができます。これにより、サービスは設定に基づいてアプリケーションにトークンを発行する方法を知ることができます。 他のディレクトリ内のサービス プリンシパルをサポートするマルチテナント アプリケーションであっても、アプリケーション オブジェクトはそのホーム ディレクトリにのみ存在します。 アプリケーション オブジェクトには、次のいずれかを含めることができます (ただし、これらに限定されません)。

- 名前、ロゴ、発行元
- リダイレクト URI
- シークレット (アプリケーションの認証に使用される対称キーまたは非対称キー)
- API の依存関係 (OAuth)
- 発行済みの API/リソース/スコープ (OAuth)
- アプリ ロール
- シングル サインオン (SSO) メタデータと構成
- ユーザー プロビジョニングのメタデータと構成
- プロキシのメタデータと構成

アプリケーション オブジェクトは、以下のような複数の経路で作成できます。

- Microsoft Entra 管理センターでのアプリケーションの登録
- Visual Studio を使用して新しいアプリケーションを作成し、Microsoft Entra 認証を使用するように構成する
- 管理者がアプリ ギャラリーからアプリケーションを追加するとき (これによってサービス プリンシパルも作成されます)
- Microsoft Graph API または PowerShell を使用して新しいアプリケーションを作成する
- Azure でのさまざまな開発者エクスペリエンスや、デベロッパー センターでの API エクスプローラー エクスペリエンスなど、その他多数

### サービス プリンシパルの概要とその由来

Microsoft Entra 管理センターで [サービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals#service-principal-object) を管理するには、 [Enterprise Applications エクスペリエンスを](https://entra.microsoft.com/#blade/Microsoft_AAD_IAM/StartboardApplicationsMenuBlade/AllApps/menuId/) 使用します。 サービス プリンシパルは、実際には Microsoft Entra ID に接続するアプリケーションを制御するものであり、ディレクトリ内のアプリケーションのインスタンスと考えることができます。 どのアプリケーションでも、最大で 1 つの ("ホーム" ディレクトリに登録されている) アプリケーション オブジェクトと、アプリケーションが動作する各ディレクトリ内のアプリケーションのインスタンスを表す 1 つ以上のサービス プリンシパル オブジェクトを持つことができます。

サービス プリンシパルには、以下を含めることができます。

- アプリケーション ID プロパティを使用したアプリケーション オブジェクトへの参照
- ローカル ユーザーとグループ アプリケーション ロールの割り当ての記録
- ローカル ユーザーとアプリケーションに許可された管理アクセス許可の記録
    - 例: 特定のユーザーの電子メールにアクセスするためのアプリケーションに対するアクセス許可
- 条件付きアクセス ポリシーを含むローカル ポリシーの記録
- アプリケーションの代替ローカル設定の記録
    - 要求変換ルール
    - 属性マッピング (ユーザーのプロビジョニング)
    - ディレクトリ固有のアプリ ロール (アプリケーションがカスタム ロールをサポートする場合)
    - ディレクトリ固有の名前またはロゴ

アプリケーション オブジェクトと同様に、サービス プリンシパルも以下のような複数の経路で作成できます。

- ユーザーが Microsoft Entra ID と統合されたサードパーティ アプリケーションにサインインするとき
    - サインイン中、ユーザーのプロファイルにアプリケーションがアクセスするための許可とその他のアクセス許可を与えるように求められます。 最初のユーザーがそれに同意した時点で、アプリケーションを表すサービス プリンシパルがディレクトリに追加されます。
- ユーザーが Microsoft 365、Microsoft Entra ID、Microsoft Azure などの Microsoft オンライン サービスを使用またはサインインする場合。
    - Microsoft サービスを初めて使用する場合、サービスの配信に使用されるさまざまな Microsoft サービス ID を表す 1 つ以上のサービス プリンシパルがディレクトリに作成される場合があります。 この "Just-In-Time" プロビジョニングは、多くの場合、バックグラウンド プロセスの一部として、いつでも行われる可能性があります。 まれに、作成される Microsoft サービス プリンシパルに、"ディレクトリ 閲覧者" などのディレクトリ ロールが割り当てられることもあります。
    - SharePoint Online などの一部の Microsoft サービスでは、ワークフローを含むコンポーネント間で安全に通信できるようにするために、継続的にサービス プリンシパルが作成されます。
- 管理者がアプリ ギャラリーからアプリケーションを追加するとき (これによって基になるアプリケーション オブジェクトも作成されます)
- [Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)を使用するアプリケーションを追加する
- SAML またはパスワード SSO を使用してアプリケーションを SSO 用に接続する
- Microsoft Graph API または PowerShell でプログラムを使用する

### アプリケーション オブジェクトとサービス プリンシパルの相互の関係

アプリケーションには、ホーム ディレクトリ内に 1 つのアプリケーション オブジェクトがあり、そのアプリケーション オブジェクトは、アプリケーションが動作する各ディレクトリ (アプリケーションのホーム ディレクトリを含む) 内の 1 つ以上のサービス プリンシパルから参照されます。

[Image: アプリ オブジェクトとサービス プリンシパルの関係を示します]

上の図では、Microsoft はアプリケーションを発行するために使用する 2 つのディレクトリを内部的に保持しています (左側)。

- 1 つはマイクロソフトのアプリ用 (Microsoft サービス ディレクトリ)
- 1 つは事前に統合されたサードパーティのアプリケーション用 (アプリ ギャラリー ディレクトリ)

Microsoft Entra ID と統合するアプリケーションのパブリッシャー/ベンダーには、発行ディレクトリが必要です (右側の "サービスとしてのソフトウェア (SaaS) ディレクトリ")。

自分で追加するアプリケーション (図では **アプリ (自分のアプリ)** として表されます) には、次のものが含まれます。

- 開発したアプリ (Microsoft Entra ID と統合)
- SSO 用に接続したアプリ
- Microsoft Entra アプリケーション プロキシを使用して発行したアプリ

#### エラーと例外

- すべてのサービス プリンシパルがアプリケーション オブジェクトを逆参照するわけではありません。 Microsoft Entra ID が最初に構築された時点では、アプリケーションに提供されるサービスははるかに限定的であり、サービス プリンシパルはアプリケーション ID を確立するのに十分でした。 元のサービス プリンシパルは、Windows Server Active Directory サービス アカウントとよく似ていました。 このため、最初にアプリケーション オブジェクトを作成しなくても、 [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) の使用など、さまざまな経路を通じてサービス プリンシパルを作成することはできます。 Microsoft Graph API では、サービス プリンシパルを作成する前に、アプリケーション オブジェクトが必要です。
- 現在、このような情報の中にはプログラムによって公開されていないものがあります。 次の情報は UI でのみ使用できます。
    - 要求変換ルール
    - 属性マッピング (ユーザーのプロビジョニング)
- サービス プリンシパル オブジェクトおよびアプリケーション オブジェクトの詳細については、Microsoft Graph API のリファレンス ドキュメントを参照してください。
    - [アプリケーション](https://learn.microsoft.com/ja-jp/graph/api/resources/application)
    - [サービス プリンシパル](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal)

### アプリケーションがMicrosoft Entra ID と統合される理由

アプリケーションは、次のような Microsoft Entra ID が提供するサービスを利用するために Microsoft Entra ID に追加されます。

- アプリケーションの認証と承認
- ユーザーの認証と承認
- フェデレーションまたはパスワードを使用するSSO
- ユーザーのプロビジョニングと同期
- ロール ベースのアクセス制御 (RBAC) - ディレクトリを使用して、アプリケーション内でロールに基づく承認チェックを実行するためのアプリケーション ロールを定義します
- OAuth 承認サービス (Microsoft 365 や他の Microsoft アプリケーションによって API/リソースへのアクセスを承認するために使用されます)
- アプリケーションの発行とプロキシ。プライベート ネットワークからインターネットにアプリケーションを発行します。
- ディレクトリ スキーマ拡張属性 - [サービス プリンシパルとユーザー オブジェクトのスキーマを拡張して](https://learn.microsoft.com/ja-jp/entra/identity-platform/schema-extensions) 、Microsoft Entra ID に追加データを格納する

### Microsoft Entra インスタンスにアプリケーションを追加する権限のあるユーザー

既定ではディレクトリ内のすべてのユーザーが、開発しているアプリケーション オブジェクトを登録する権限を持ち、同意によって共有するアプリケーションまたは組織データにアクセスできるアプリケーションを決定できます。 ディレクトリ内で、アプリケーションにサインインし、同意を許可した最初のユーザーの場合、テナントにサービス プリンシパルが作成されます。 それ以外の場合、同意の許可情報は既存のサービス プリンシパルに格納されます。

アプリケーションへの登録と同意をユーザーに許可することは、最初は心配かもいれませんが、以下の点に留意してください。

- アプリケーションは、長い間、登録されたりディレクトリに記録されたりしなくても、ユーザー認証に Windows Server Active Directory を利用することができました。 現在では、いくつのアプリケーションがどのような目的でディレクトリを使用しているかを正確に認識できるようになっています。
- これらの責任をユーザーに委ねることで、管理主導のアプリケーション登録と公開プロセスの必要がなくなります。 Active Directory フェデレーション サービス (ADFS) では、開発者に代わって管理者が証明書利用者としてアプリケーションを追加することが必要な場合がありました。 今では開発者がセルフ サービスできます。
- ユーザーがビジネス目的で組織のアカウントを使用してアプリケーションにサインインするのは良いことです。 後でユーザーが組織を離れると、自動的に使用していたアプリケーションのアカウントにアクセスできなくなります。
- どのようなデータがどのアプリケーションと共有されていたのかについての記録を残すのは良いことです。 データはかつてより移動性が高くなっており、誰がどのデータをどのアプリケーションと共有したかについての明確な記録があると便利です。
- Microsoft Entra ID を OAuth に使用する API 所有者は、そのユーザーがアプリケーションに与えることができるアクセス許可および管理者が同意する必要があるアクセス許可を厳密に決定します。 ユーザーの同意はそのユーザー自身のデータと機能に限定されますが、管理者だけはより大きな範囲とより重要なアクセス許可に同意することができます。
- ユーザーがデータへのアクセスをアプリケーションに追加または許可すると、そのイベントを監査できるので、Microsoft Entra 管理センター内の監査レポートを表示して、アプリケーションがどのようにディレクトリに追加されたかを判断することができます。

それでもディレクトリ内のユーザーが管理者の承認なしにアプリケーションの登録とアプリケーションへのサインインを実行できないようにするには、これらの機能を無効にするように変更できる 2 つの設定があります。

- 組織内のユーザーの同意設定を変更するには、「 [ユーザーがアプリケーションに同意する方法を構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent)」を参照してください。
- ユーザーが自分のアプリケーションを登録できないようにするには:

    1. Microsoft Entra 管理センターで、 **Entra ID**&gt;**Users**&gt;**User 設定**を参照します。
    2. [ **ユーザーはアプリケーションを登録できます]** を **[いいえ**] に変更します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/how-to-add-credentials"} -->
## Microsoft Entra ID でアプリの資格情報を追加および管理する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials
- Service: identity-platform
- Article date: 2025-03-26
- Summary: セキュリティで保護されたアプリ認証のために Microsoft Entra で証明書、クライアント シークレット、およびフェデレーション資格情報を構成する方法について説明します。

機密クライアント アプリケーションを構築する場合、資格情報を効果的に管理することが重要です。 この記事では、Microsoft Entra のアプリ登録にクライアント証明書、フェデレーション ID 資格情報、またはクライアント シークレットを追加する方法について説明します。 これらの資格情報を使用すると、ユーザーの操作なしでアプリケーション自体を安全に認証し、Web API にアクセスできます。

### [前提条件]

[クイック スタート: Microsoft Entra ID でアプリを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。

### アプリケーションに資格情報を追加する

機密クライアント アプリケーションの資格情報を作成する場合:

- アプリケーションを運用環境に移行する前に、クライアント シークレットの代わりに証明書を使用することをお勧めします。 証明書の使用方法の詳細については、 [Microsoft ID プラットフォーム アプリケーション認証証明書の資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials)の手順を参照してください。
- テスト目的で、自己署名証明書を作成し、それに対して認証するようにアプリを構成できます。 ただし、 **運用環境では**、既知の証明機関によって署名された証明書を購入し、 [Azure Key Vault](https://learn.microsoft.com/ja-jp/azure/key-vault/general/overview) を使用して証明書のアクセスと有効期間を管理する必要があります。

クライアント シークレットの脆弱性の詳細については、「シークレット [ベースの認証からアプリケーションを移行する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-applications-from-secrets)」を参照してください。

## [証明書を追加する](#tab/certificate)
*公開キー*と呼ばれることもあります。証明書は、クライアント シークレットよりも安全であると見なされるため、推奨される資格情報の種類です。

1. Microsoft Entra 管理センターの **[アプリの登録]** でアプリケーションを選びます。
2. **証明書とシークレット**&gt;**Certificates**&gt;**証明書のアップロード**を選択します。
3. アップロードするファイルを選択します。 *.cer*、*.pem*、*.crt* のいずれかのファイル形式である必要があります。
4. [**] を選択し、[**] を追加します。
5. クライアント アプリケーション コードで使用する証明書 **の拇印** を記録します。

    [Image: Microsoft Entra 管理センターのスクリーンショット。アプリの登録の [証明書とシークレット] ウィンドウに [証明書] タブが表示されています。]

## [クライアント シークレットの追加](#tab/client-secret)
クライアント シークレットは、"アプリケーション パスワード" とも呼ばれ、アプリで自身を識別するために証明書の代わりに使用できる文字列値です。

クライアント シークレットは証明書やフェデレーション資格情報よりも安全性が低いため、運用環境では **使用しないでください** 。 ローカル アプリの開発には便利ですが、運用環境で実行されているアプリケーションの証明書またはフェデレーション資格情報を使用して、セキュリティを強化することが不可欠です。

1. Microsoft Entra 管理センターの **[アプリの登録]** でアプリケーションを選びます。
2. **[証明書とシークレット**&gt;**クライアント シークレット**&gt;**新しいクライアント シークレット**を選択します。
3. クライアント シークレットの説明を追加します。
4. シークレットの有効期限を選択するか、カスタムの有効期間を指定します。

    - クライアント シークレットの有効期間は、2 年間 (24 か月) 以下に制限されています。 24 か月を超えるカスタムの有効期間を指定することはできません。
    - Microsoft では、有効期限の値は 12 か月未満に設定することをお勧めしています。
5. [**] を選択し、[**] を追加します。
6. クライアント アプリケーション コードで使用するクライアント シークレット **値** を記録します。 このページを終了しても、このシークレット値は *再び表示されません* 。

    [Image: Microsoft Entra 管理センターのスクリーンショット。アプリの登録の [証明書とシークレット] ウィンドウの [クライアント シークレット] タブが表示されています。]

注

サービス プリンシパルを自動的に作成する Azure DevOps サービス接続を使用している場合は、クライアント シークレットを直接更新するのではなく、Azure DevOps ポータル サイトからクライアント シークレットを更新する必要があります。 Azure DevOps ポータル サイトからクライアント シークレットを更新する方法については、「Azure [Resource Manager サービス接続のトラブルシューティング](https://learn.microsoft.com/ja-jp/azure/devops/pipelines/release/azure-rm-endpoint#service-principals-token-expired)」を参照してください。

## [フェデレーション資格情報を追加する](#tab/federated-credential)
フェデレーション ID 資格情報は、ワークロード (GitHub Actions、Kubernetes で実行されているワークロード、Azure 以外のコンピューティング プラットフォームで実行されているワークロードなど) が、[ワークロード ID フェデレーション](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation)を使用してシークレットを管理することなく Microsoft Entra で保護されたリソースにアクセスできるようにする資格情報の一種です。

フェデレーション資格情報を追加するには、次の手順に従います。

1. Microsoft Entra 管理センターの **[アプリの登録]** でアプリケーションを選びます。
2. [**証明書 & シークレット**&gt;**Federated 資格情報**&gt;資格情報の追加] を**選択します**。

    [Image: Microsoft Entra 管理センターのスクリーンショット。[アプリの登録] の [証明書およびシークレット] ペインが表示されています。]
3. [ **フェデレーション資格情報シナリオ** ] ドロップダウン ボックスで、サポートされているシナリオのいずれかを選択し、対応するガイダンスに従って構成を完了します。

    - 別のテナントの Azure Key Vault を使用してテナント内のデータを暗号化するための**カスタマー マネージド キー**。
    - アプリケーションのトークンを取得し、Azure に資産をデプロイするように **GitHub ワークフローを構成**するための Azure [リソースをデプロイする GitHub アクション](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-create-trust#github-actions)。
    - **Kubernetes が Azure リソースにアクセス** して、アプリケーションのトークンを取得し、Azure リソースにアクセスするように [Kubernetes サービス アカウント](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-create-trust#kubernetes) を構成します。
    - 他の発行者 によって、マネージド ID または外部の OpenID Connect プロバイダーによって管理される ID を信頼するようにアプリケーションを構成し、アプリケーションのトークンを取得して Azure リソース にアクセスできるようにすることができます。

フェデレーション資格情報を使用してアクセス トークンを取得する方法の詳細については、 [Microsoft ID プラットフォームと OAuth 2.0 クライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow#third-case-access-token-request-with-a-federated-credential)を参照してください。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/how-to-add-redirect-uri"} -->
## アプリケーションにリダイレクト URI を追加する方法 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri
- Service: identity-platform
- Article date: 2026-05-14
- Summary: Microsoft Entra でアプリケーションにリダイレクト URI を追加して、認証トークンを安全に処理し、アプリのセキュリティを強化する方法について説明します。

ユーザーをサインインさせるには、アプリケーションで、パラメーターとして指定されたリダイレクト URI を使用して、Microsoft Entra 承認エンドポイントにログイン要求を送信する必要があります。 リダイレクト URI は、Microsoft Entra 認証サーバーが承認コードとアクセス トークンのみを目的の受信者に送信することを保証する重要なセキュリティ機能です。

### [前提条件]

- [クイック スタート: Microsoft Entra ID でアプリを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。

### リダイレクト URI を追加する

*リダイレクト URI* は、認証後に Microsoft ID プラットフォームがセキュリティ トークンを送信する場所です。 リダイレクト URI は、Microsoft Entra 管理センターの **Authentication** ページで構成されます。 **Web** アプリケーションと**シングルページ アプリケーションの**場合は、リダイレクト URI を手動で指定します。 **モバイルおよびデスクトップ** プラットフォームの場合は、生成されたリダイレクト URI から選択します。

ターゲット プラットフォームまたはデバイスに基づいて設定を構成するには、次の手順に従います。

1. Microsoft Entra 管理センターの **[アプリの登録]** でアプリケーションを選びます。
2. **[管理]** で、 **[認証]** を選択します。
3. [ **リダイレクト URI 構成** ] タブで、[ **リダイレクト URI の追加]** を選択します。
4. [ **リダイレクト URI を追加するプラットフォームの選択** ] ウィンドウで、アプリケーションの種類 (プラットフォーム) のタイルを選択してその設定を構成します。

    | プラットホーム | 構成設定 | 例 |
    | --- | --- | --- |
    | **ウェブ** | サーバー上で実行される Web アプリの **リダイレクト URI を** 入力します。 **フロント チャネルログアウト URL を**入力することもできます。 | `https://contoso.com/auth-response` または`http://localhost:3000/auth-response` アプリをローカルで実行する場合は〘。 |
    | **シングルページ アプリケーション** | JavaScript、Angular、React.js、Blazor WebAssembly を使用して、クライアント側アプリの **リダイレクト URI を** 入力します。 **フロント チャネルログアウト URL を**入力することもできます。 | `https://contoso.com/auth-response` または`http://localhost:3000/auth-response` アプリをローカルで実行する場合は〘。 |
    | **iOS / macOS** | リダイレクト URI を生成するアプリ **バンドル ID を**入力します。 **ビルド設定**または *Info.plist* の Xcode で見つけます。 | `com.microsoft.identityapp.ciam.MSALiOS`。 |
    | **アンドロイド** | アプリの **パッケージ名**を入力すると、リダイレクト URI が生成されます。 *AndroidManifest.xml* ファイルで見つけます。 また、 **署名ハッシュ**を生成して入力します。 | パッケージ名: • `com.azuresamples.msalandroidapp` 署名には次の内容があります。 • `aB1cD2eF-3gH4iJ5kL6-mN7oP8qR=`。 |
    | **モバイルアプリケーションとデスクトップアプリケーション** | MSAL またはブローカーを使用しないデスクトップ アプリまたはモバイル アプリの場合は、このプラットフォームを選択します。 推奨される **リダイレクト URI を**選択するか、1 つ以上の **カスタム リダイレクト URI を指定します** | `https://login.microsoftonline.com/common/oauth2/nativeclient` |
5. [ **構成] を** 選択して、プラットフォームの構成を完了します。

#### リダイレクトURIの制限

アプリ登録に追加するリダイレクト URI の形式にはいくつかの制限があります。 これらの制限の詳細については、「 [リダイレクト URI (応答 URL) の制限事項と制限](https://learn.microsoft.com/ja-jp/entra/identity-platform/reply-url)事項」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/how-to-native-authentication-cors-solution-production-environment"} -->
## ネイティブ認証で SPA のプロキシ サーバーとして Azure Front Door を使用する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-native-authentication-cors-solution-production-environment
- Service: identity-platform / external
- Article date: 2025-02-07
- Summary: ネイティブ認証を使用するシングルページ アプリの運用環境で、Azure Front Door をリバース プロキシとして設定する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、ネイティブ認証 API [を使用するシングルページ アプリ (SPA) のリバース プロキシとして Azure Front Door](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api)使用する方法について説明します。

ネイティブ認証 API では、クロスオリジン リソース共有 (CORS) はサポートされていません。 そのため、この API をユーザー認証に使用するシングルページ アプリ (SPA) では、フロントエンド JavaScript コードから要求を正常に行うことはできません。 この問題を解決するには、SPA とネイティブ認証 API の間にプロキシ サーバーを追加します。 プロキシ サーバーは、適切な CORS ヘッダーを応答に挿入します。

運用環境では、[Azure Front Door と Standard/Premium サブスクリプション](https://learn.microsoft.com/ja-jp/azure/frontdoor/standard-premium/troubleshoot-cross-origin-resources) をリバース プロキシとして使用することをお勧めします。

### [前提条件]

- Azure サブスクリプション。 [無料でアカウントを作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- `http://www.contoso.com`などの URL を介してアクセスできるサンプル SPA:
    - 「[クイック スタート: ネイティブ認証 API](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-single-page-app-react-sign-in)を使用して、サンプルの React SPA にユーザーをサインインする」で説明されている React アプリを使用できます。 ただし、このガイドでは設定が説明されるため、プロキシ サーバーを構成したり実行したりしないでください。
    - アプリを実行したら、このガイドで後で使用するためにアプリの URL を記録します。 運用環境では、この URL には、カスタム ドメイン URL として使用するドメイン (`http://www.contoso.com` など) が含まれます。
- [Azure Developer CLI (azd)](https://learn.microsoft.com/ja-jp/azure/developer/azure-developer-cli/install-azd?tabs=winget-windows%2Cbrew-mac%2Cscript-linux&pivots=os-windows) をインストールします。

### Azure Front Door をリバース プロキシとして設定する

1. CORS での Azure Front Door Standard/Premium の使用に関する記事を読んで、 [CORS で Azure Front Door を使用](https://learn.microsoft.com/ja-jp/azure/frontdoor/standard-premium/troubleshoot-cross-origin-resources)する方法について理解します。
2. [「外部テナントのアプリのカスタム URL ドメインを有効にする」の](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain)手順に従って、カスタム ドメイン名を外部テナントに追加します。
    - Azure Front Door を作成するには、 azd テンプレートを使用します。
3. サンプル SPA で、*API\React\ReactAuthSimple\src\config.ts* ファイルを開き、`BASE_API_URL`*http://localhost:3001/api*の値を `https://Enter_Custom_Domain_URL/Enter_the_Tenant_ID_Here`に置き換えます。 プレースホルダーを次のように置き換えます。
    1. `Enter_Custom_Domain_URL` を、`contoso.com` など、カスタム ドメイン URL で置き換えます。
    2. `Enter_the_Tenant_ID_Here` をディレクトリ (テナント) ID で置き換えます。 テナント ID がわからない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください。
4. 必要に応じて、サンプル SPA を再実行します。

### Azure Developer CLI (azd) テンプレートを使用してリバース プロキシとして Azure Front Door を作成する

1. azd テンプレートを初期化するには、次のコマンドを実行します。

    ```console
    azd init --template https://github.com/azure-samples/ms-identity-extid-cors-proxy-frontdoor
    ```

    プロンプトが表示されたら、azd 環境の名前を入力します。 この名前はリソース グループのプレフィックスとして使用されるため、Azure サブスクリプション内で一意である必要があります。
2. Azure にサインインするには、次のコマンドを実行します。

    ```console
    azd auth login
    ```
3. アプリ リソースをビルド、プロビジョニング、デプロイするには、次のコマンドを実行します。

    ```console
    azd up
    ```

    メッセージが表示されたら、次の情報を入力してリソースの作成を完了します。

    - `Azure Location`: リソースがデプロイされている Azure の場所。
    - `Azure Subscription`: リソースがデプロイされている Azure サブスクリプション。
    - `corsAllowedOrigin`: SCHEME://DOMAIN:PORT の形式で CORS 要求を許可する配信元ドメイン(例: http://localhost:3000.
    - `tenantSubdomain`: プロキシしている外部テナントのサブドメイン。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント サブドメインがない場合は、テナントの詳細を 読み取る方法について説明します。
    - `customDomain`: 外部 ID 内で構成されたカスタム ドメインの完全な URL (たとえば、 *login.example.com)。*

### Azure Front Door をリバース プロキシとして使用するためのガイドライン

運用環境で CORS ヘッダーを管理するためのリバース プロキシとして Azure Front Door を設定する場合は、次のガイドラインをお勧めします。

#### 配信元を制限する

Azure Front Door を構成する場合は、配信元として SPA ドメイン URL (`https://www.contoso.com`など) のみを許可します。 セキュリティの脆弱性につながる可能性がある `*` など、すべての配信元を許可する構成は避けてください。

#### 単純な要求を使用する

ネイティブ認証要求は、単純な要求のすべての条件を既に満たしています。

- `Http Method: POST`を使用します。
- `Content-Type: application/x-www-form-urlencoded`を使用します。
- 要求にはカスタム ヘッダーは必要ありません。
- 要求には、要求 `ReadableStream` オブジェクトは含まれません。
- 要求では、`XMLHttpRequest`の使用は必要ありません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/how-to-native-authentication-cors-solution-test-environment"} -->
## Azure Function App を使用して SPA のリバース プロキシを設定する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-native-authentication-cors-solution-test-environment
- Service: identity-platform / external
- Article date: 2025-02-07
- Summary: Azure Function App を使用してネイティブ認証 API を呼び出すシングルページ アプリのリバース プロキシを設定する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、Azure Functions アプリを使用してリバース プロキシを設定し、ネイティブ認証 API [を使用するシングルページ アプリ (SPA) のテスト環境で CORS ヘッダー](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api)管理する方法について説明します。

ネイティブ認証 API では、クロスオリジン リソース共有 (CORS) はサポートされていません。 そのため、この API をユーザー認証に使用するシングルページ アプリ (SPA) では、フロントエンド JavaScript コードから要求を正常に行うことはできません。 この問題を解決するには、SPA とネイティブ認証 API の間にプロキシ サーバーを追加する必要があります。 このプロキシ サーバーは、適切な CORS ヘッダーを応答に挿入します。

このソリューションはテスト目的であり、運用環境 では使用しないでください。 運用環境で使用するソリューションをお探しの場合は、Azure Front Door ソリューションを使用することをお勧めします。実稼働 [で SPA の CORS ヘッダーを管理するには、リバース プロキシとして Azure Front Door を使用する](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-native-authentication-cors-solution-production-environment)に関するページの手順を参照してください。

### [前提条件]

- Azure サブスクリプション。 [無料でアカウントを作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- リソース プロバイダー `Microsoft.App` 登録する方法については、「 [リソース プロバイダーを登録する方法」を](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/resource-providers-and-types)参照してください。 この手順を完了する必要があるのは、新しく作成されたサブスクリプションごとに 1 回だけです。
- [Azure Developer CLI (azd)](https://learn.microsoft.com/ja-jp/azure/developer/azure-developer-cli/install-azd?tabs=winget-windows%2Cbrew-mac%2Cscript-linux&pivots=os-windows) をインストールします。
- `http://www.contoso.com`などの URL を介してアクセスできるサンプル SPA:
    - 「[クイック スタート: ネイティブ認証 API](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-single-page-app-react-sign-in)を使用して、サンプルの React SPA にユーザーをサインインする」で説明されている React アプリを使用できます。 ただし、このガイドでは設定が説明されるため、プロキシ サーバーを構成したり実行したりしないでください。
    - アプリを実行したら、このガイドで後で使用するためにアプリの URL を記録します。

### Azure Developer CLI (azd) テンプレートを使用して Azure 関数アプリでリバース プロキシを作成する

1. azd テンプレートを初期化するには、次のコマンドを実行します。

    ```console
    azd init --template https://github.com/azure-samples/ms-identity-extid-cors-proxy-function
    ```

    プロンプトが表示されたら、azd 環境の名前を入力します。 この名前はリソース グループのプレフィックスとして使用されるため、Azure サブスクリプション内で一意である必要があります。
2. Azure にサインインするには、次のコマンドを実行します。

    ```console
    azd auth login
    ```
3. アプリ リソースをビルド、プロビジョニング、デプロイするには、次のコマンドを実行します。

    ```console
    azd up
    ```

    メッセージが表示されたら、次の情報を入力してリソースの作成を完了します。

    - `Azure Location`: リソースがデプロイされている Azure の場所。
    - `Azure Subscription`: リソースがデプロイされている Azure サブスクリプション。
    - `corsAllowedOrigin`: SCHEME://DOMAIN:PORT の形式で CORS 要求を許可する配信元ドメイン (たとえば、 *http://localhost:3000*)。
    - `tenantSubdomain`: プロキシしている外部テナントのサブドメイン。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント サブドメインがない場合は、テナントの詳細を 読み取る方法について説明します。

### リバース プロキシを使用してサンプル SPA をテストする

1. サンプル SPA で、 *API\React\ReactAuthSimple\src\config.ts* ファイルを開き、次のコードを置き換えます。

    - `BASE_API_URL`、*http://localhost:3001/api*、`https://Enter_App_Function_Name_Here.azurewebsites.net`の値。
    - 関数アプリの名前で `Enter_App_Function_Name_Here` プレースホルダーを置き換えます。 必要に応じて、サンプル SPA を再実行します。
2. サンプルの SPA URL を参照し、サインアップ、サインイン、パスワードリセットのフローをテストします。 リバース プロキシが CORS ヘッダーを正しく管理する場合、SPA アプリは正しく動作するはずです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/how-to-native-authentication-single-page-app-javascript-sdk-set-up-local-cors"} -->
## ネイティブ認証 JavaScript SDK を使用して SPA のヘッダーを管理するように CORS プロキシ サーバーを設定する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-native-authentication-single-page-app-javascript-sdk-set-up-local-cors
- Service: identity-platform / external
- Article date: 2025-02-07
- Summary: ネイティブ認証 JavaScript SDK を使用するシングルページ アプリケーション用に CORS プロキシ サーバーを設定する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このガイドでは、シングルページ アプリ (SPA) からネイティブ認証 API を操作しながら CORS ヘッダーを管理するようにローカル CORS プロキシ サーバーを設定する方法について説明します。 CORS プロキシ サーバーは、ネイティブ認証 API がクロスオリジン リソース共有 (CORS) サポートできないことを解決するソリューションです。

この記事で設定した CORS サーバーは、ローカル アプリ開発に使用できます。 また：

- テスト環境 [に Azure Function App を使用して、CORS ヘッダーを管理するリバース プロキシ サーバーを設定](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-native-authentication-cors-solution-test-environment) できます。
- 運用環境では、 [Azure Front Door をリバース プロキシとして使用](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-native-authentication-cors-solution-production-environment)できます。

### CORS プロキシ サーバーを作成する

1. SPA のルート フォルダーに、 *cors.js*という名前のファイルを作成し、次のコードを追加します。

    ```javascript
    const http = require("http");
    const https = require("https");
    const url = require("url");
    const proxyConfig = require("./proxy.config");
    
    const extraHeaders = [
        "x-client-SKU",
        "x-client-VER",
        "x-client-OS",
        "x-client-CPU",
        "x-client-current-telemetry",
        "x-client-last-telemetry",
        "client-request-id",
    ];
    http.createServer((req, res) => {
        const reqUrl = url.parse(req.url);
        const domain = url.parse(proxyConfig.proxy).hostname;
    
        // Set CORS headers for all responses including OPTIONS
        const corsHeaders = {
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS",
            "Access-Control-Allow-Headers": "Content-Type, Authorization, " + extraHeaders.join(", "),
            "Access-Control-Allow-Credentials": "true",
            "Access-Control-Max-Age": "86400", // 24 hours
        };
    
        // Handle preflight OPTIONS request
        if (req.method === "OPTIONS") {
            res.writeHead(204, corsHeaders);
            res.end();
            return;
        }
    
        if (reqUrl.pathname.startsWith(proxyConfig.localApiPath)) {
            const targetUrl = proxyConfig.proxy + reqUrl.pathname?.replace(proxyConfig.localApiPath, "") + (reqUrl.search || "");
    
            console.log("Incoming request -> " + req.url + " ===> " + reqUrl.pathname);
    
            const newHeaders = {};
            for (let [key, value] of Object.entries(req.headers)) {
                if (key !== 'origin') {
                    newHeaders[key] = value;
                }
            }
    
            const proxyReq = https.request(
                targetUrl,
                {
                    method: req.method,
                    headers: {
                        ...newHeaders,
                        host: domain,
                    },
                },
                (proxyRes) => {
                    res.writeHead(proxyRes.statusCode, {
                        ...proxyRes.headers,
                        ...corsHeaders,
                    });
    
                    proxyRes.pipe(res);
                }
            );
    
            proxyReq.on("error", (err) => {
                console.error("Error with the proxy request:", err);
                res.writeHead(500, { "Content-Type": "text/plain" });
                res.end("Proxy error.");
            });
    
            req.pipe(proxyReq);
        } else {
            res.writeHead(404, { "Content-Type": "text/plain" });
            res.end("Not Found");
        }
    }).listen(proxyConfig.port, () => {
        console.log("CORS proxy running on http://localhost:3001");
        console.log("Proxying from " + proxyConfig.localApiPath + " ===> " + proxyConfig.proxy);
    });
    ```
2. SPA のルート フォルダーに、 *proxy.config.js*という名前のファイルを作成し、次のコードを追加します。

    ```javascript
    const tenantSubdomain = "Enter_the_Tenant_Subdomain_Here";
    const tenantId = "Enter_the_Tenant_Id_Here";
    
    const config = {
        localApiPath: "/api",
        port: 3001,
        proxy: `https://${tenantSubdomain}.ciamlogin.com/${tenantId}`,
    };
    module.exports = config;
    ```

    - プレースホルダー `Enter_the_Tenant_Subdomain_Here` を見つけて、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント サブドメインがない場合は、テナントの詳細を 読み取る方法について説明します。
    - `tenantId` をディレクトリ (テナント) ID に置き換えます。 テナント ID がわからない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください。
3. SPA *package.json* ファイルを開き、 *scripts* オブジェクトに次のコマンドを追加します。

    ```json
    "cors": "node cors.js",
    ```

この時点で、CORS プロキシ サーバーを実行する準備ができました。

### CORS サーバーを実行する

CORS プロキシ サーバーを起動するには、ターミナルで次のコマンドを実行します。

```console
npm run cors
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/how-to-native-authentication-webview-sso"} -->
## ネイティブ アプリから埋め込み Web ビューへの単一 Sign-On の実装 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-native-authentication-webview-sso
- Service: identity-platform / external
- Article date: 2026-04-04
- Summary: Microsoft Entra External ID ネイティブ認証を使用して、埋め込み Web ビューでネイティブ モバイル アプリと Web リソースの間に SSO を実装する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

モバイル アプリにプロファイルの更新ページやリワード ダッシュボードなどの Web ベースの機能が含まれている場合、ユーザーはシームレスなシングル サインオン エクスペリエンスを期待します。 ネイティブ アプリを使用して既にサインインした後に、2 番目のログイン プロンプトが表示されないようにする必要があります。

この記事では、ネイティブ モバイル アプリケーションと、埋め込み Web ビューでホストされている Web リソース (たとえば、iOS での `WKWebView` や Android 上の `WebView` ) の間にシングル サインオン (SSO) を実装する方法について説明します。 システム ブラウザーとは異なり、埋め込み Web ビューを使用すると、送信前にネットワーク要求を操作できます。 この機能により、アプリはユーザーの認証状態を要求ヘッダーに直接挿入できます。

推奨されるフローは次のようになります。

1. ユーザーは、ネイティブ認証 SDK またはネイティブ [認証 API](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api) を使用して、モバイル アプリのネイティブ UI を使用してサインインします。
2. Web ビューを読み込む前に、アプリは SDK または API から有効なアクセス トークンを取得します。
3. アプリは、 `Authorization: Bearer <access_token>` ヘッダーにアクセス トークンを含むカスタム要求を含む Web ビューを読み込みます。
4. Web リソースはトークンを検証し、すぐにアクセス権を付与します。

次の図は、Web リソース、モバイル アプリ、SDK、ID サービス (ESTS) の間の相互作用を示しています。

[Image: モバイル アプリが SDK 経由でサインインし、トークンを受け取り、アクセス トークンを含む Web ビューを Authorization ヘッダーに読み込む SSO フローを示すシーケンス図。]

### 前提条件

- ネイティブ認証 SDK またはネイティブ認証 API を使用して構成された [ネイティブ認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api)を備えたモバイル アプリ。 SDK を使用していて、まだアプリを設定していない場合は、「 [ネイティブ認証用に Android アプリを準備](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-android-app) する」または「 [ネイティブ認証用に iOS/macOS アプリを準備する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-ios-macos-app)」を参照してください。
- ネイティブ アプリでの完了したサインイン フロー。 ガイダンスについては、「 [Android モバイル アプリでユーザーをサインインする」](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-android-sign-in) または [「iOS モバイル アプリでユーザーをサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-ios-sign-in)する」を参照してください。
- HTTPS (TLS) 経由で提供される Web リソース。 HTTP 経由でトークンを送信しないでください。
- モバイル アプリと Web リソースの間の共有クライアント ID (アプリケーション ID)。 詳細については、「 制限事項と構成要件」を参照してください。

### ネイティブ認証でサインインする

Native Auth SDK または [ネイティブ認証 API](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api) を使用して、標準のサインイン フローを完了します。 SDK を使用してサインインに成功すると、アクセス トークン、ID トークン、および更新トークンが安全にキャッシュされます。 API を直接使用する場合、アプリは、受け取ったトークンを安全に格納する役割を担います。

サインインの実装手順の詳細については、次を参照してください。

- **Android**: [Android (Kotlin) モバイル アプリでユーザーをサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sign-in-sign-out)する
- **iOS/macOS**: [iOS (Swift) モバイル アプリでユーザーをサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-macos-sign-in-sign-out)する

### アクセス トークンを取得する

ユーザーが Web ビューを開くアクションをトリガーしたら、Web リソースを読み込む前に、アプリに有効な期限切れのないアクセス トークンがあることを確認します。

Native Auth SDK を使用する場合は、トークンをサイレント モードで要求します。 SDK には、キャッシュから有効なトークンを取得するか、自動的に更新する `getAccessToken()` メソッドが用意されています。 特定のスコープでアクセス トークンを取得する方法の詳細については、次を参照してください。

- **Android**: [複数のアクセス トークンを取得する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-android-sign-in-call-api)
- **iOS/macOS**: [複数のアクセス トークンを取得する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-ios-sign-in-call-api)

ネイティブ認証 API を直接使用する場合、アプリは API の `/oauth/v2.0/token` エンドポイントを介してトークンを取得します。 詳細については、 [ネイティブ認証 API リファレンスを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-native-authentication-api)。

Web リソースに必要な正確なスコープでトークンを要求します。 スコープの要件については、「 制限事項と構成要件」を参照してください。

### 認証を使用して Web ビューを読み込む

認証状態を Web ビューに渡すには、2 つの方法があります。 推奨される方法では、承認ヘッダーが使用されます。 従来のシナリオでは Cookie ベースのフォールバックを使用できますが、推奨されません。

#### オプション A: HTTP ヘッダー経由でベアラー トークンを使用する (推奨)

Web ビューの読み込みに使用する初期 HTTP 要求の `Authorization` ヘッダーにアクセス トークンを直接挿入します。 これは、最も安全で堅牢な方法です。

この方法は、次の理由で推奨されます。

- **ステートレス:** クライアント側の永続的な Cookie には依存しません。
- **トークンを分離します。トークン**をこの特定の要求フローに厳密に制限します。
- **Web ベースの攻撃ベクトルを回避**します。ブラウザーで管理されるセッションに関連する一般的なセキュリティの問題を回避します。

ヘッダーベースの認証を使用して Web ビューを読み込むには:

1. Web リソースの URL を作成します。 HTTPS を使用していることを確認します。
2. カスタム ネットワーク要求オブジェクトを作成します。
3. 要求にヘッダー `Authorization: Bearer <access_token>` を追加します。
4. Web ビュー コンポーネントに要求を読み込みます (たとえば、iOS の `WKWebView` や Android の `WebView` )。

#### オプション B: Cookie を使用する (フォールバックのみ)

ターゲット Web リソースがヘッダーベースの認証 (たとえば、特定のレガシ シングルページ アプリケーション) を処理できない場合は、トークンを Cookie として挿入できます。 一般に、この方法はセキュリティ 上のリスクがあるためお勧めしません。

Web ビューに Cookie を挿入すると、認証状態がブラウザーで管理されるメカニズムに委ねられます。 これにより、セッションが "アンビエント" (要求に自動的にアタッチされます) になり、アプリが標準の Web 攻撃クラスに公開されます。

- **XSS (クロスサイト スクリプティング):** Web コンテンツが侵害された場合、セッションはハイジャックに対して脆弱です。
- **CSRF (クロスサイト リクエスト フォージェリ):** 意図しない認証要求のリスクがあります。
- **セッション固定**: 攻撃者がセッションの状態を制御する可能性があります。
- **コンプライアンス**: このアプローチは、Web ビューの Cookie jar での機密性の高い状態の保持に関するセキュリティのベスト プラクティス (MASTG-KNOW-0018 など) と競合します。

Warning

Cookie ベースのアプローチは条件付きで承認されており、通常は推奨されません。 ターゲット Web リソースがヘッダーベースの認証をサポートできない場合にのみ使用します。

Cookie ベースのアプローチを使用する場合は、次の要件が適用されます。

- 可能な場合は、サーバーによって発行されたセッション Cookie を使用します。
- 生のアクセス トークンを Cookie に直接配置することは避けてください。
- `HttpOnly`、`Secure`、および適切な`SameSite`属性を使用して Cookie を設定します。
- サーバー側に厳密な CSRF 保護を適用します。

### バックエンドでトークンを検証して永続化する

要求が Web リソースに到達すると、バックエンドはトークンを処理してセッションを確立します。

#### トークンを検証する

Web サーバーは受信要求をインターセプトし、トークンの署名と要求を検証します。 ASP.NET Core バックエンドの場合は、 `Microsoft.Identity.Web` (MISE) を使用して検証を自動的に処理します。

トークンの対象ユーザー (`aud`) 要求が Web API の識別子と一致し、発行者 (`iss`) 要求が予想される機関と一致していることを確認します。

#### セッションを保持する

Web ビューは、後続のナビゲーション イベント (ユーザーがリンクを選択した場合など) にカスタム ヘッダーを保持しません。 最初の要求後に認証された状態を維持するために、サーバーは初期ベアラー トークンの検証に成功したときに標準セッション Cookie (`Set-Cookie`) を発行します。

次の属性を使用してセッション Cookie を構成します。

- `HttpOnly`
- `Secure`
- 適切な `SameSite` ポリシー

### 制限事項と構成要件

モバイル アプリに発行されたトークンが Web リソースによって受け入れられるようにするには、次の構成に注意してください。

- **共有クライアント ID**: モバイル アプリと Web アプリは、同じクライアント ID (アプリケーション ID) を共有する必要があります。 ID が異なる場合、バックエンドは対象ユーザーの不一致としてモバイル アプリのトークンを拒否します。
- **スコープの配置**: モバイル アプリは、Web リソースに必要な正確なスコープ ( `Profile.Read`、 `Orders.Write`など) を使用してアクセス トークンを要求します。

Note

このソリューションは、Web ビューシナリオ専用に調整されています。 SSO 機能をシステム ブラウザーやその他の複雑なシナリオに拡張するより汎用的なソリューションは、今後のリリースで予定されています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/how-to-prepare-app-coop-enforcement"} -->
## Microsoft Entra COOP ポリシー用にアプリを準備する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-prepare-app-coop-enforcement
- Service: identity-platform
- Article date: 2026-09-24
- Summary: Microsoft Entra Cross-Origin-Opener-Policy にアプリとの互換性を確保し、移行中のポリシー適用を管理する方法について説明します。

Cross-Origin-Opener-Policy (COOP) は、トップレベル ドキュメントをクロスオリジン ウィンドウから分離するブラウザーのセキュリティ ポリシーです。 この分離により、悪意のあるページが正当なアプリケーションを開くソーシャル エンジニアリング攻撃を防ぐことができます。 アプリケーションがサインインに移動すると、悪意のあるページがサインイン フローをハイジャックし、アクセスされているアプリケーションについてユーザーに誤解を与える可能性があります。

この記事は、ユーザーが中断することなくサインインし続けることができるように、Microsoft Entra COOP ポリシーとアプリの互換性を保つために役立ちます。 これは、アプリで JavaScript 用 Microsoft Authentication Library (MSAL.js) ポップアップ メソッドまたは同様の SDK を使用する開発者向けです。

### COOP ポリシーがサインインに与える影響

Microsoft Entra COOP ポリシーは、ポップアップ ベースのサインインに依存する新しいアプリケーションの破壊的変更です。 Microsoft Entra は、ポップアップをそのオープナーから切り離します。 この変更により、悪意のあるページが正当なアプリケーションを開き、そのサインイン フローをハイジャックするのを防ぐことができます。

### 新しいアプリの適用タイムラインを確認する

Microsoft Entra COOP ポリシーは、2027 年 1 月 31 日以降に作成された新しいアプリのすべてのMicrosoft Entra Web プロトコルに適用されます。 新しいアプリケーションを作成する場合は、2027 年 1 月 31 日より前に互換性があることを確認してください。

### 認証方法を更新する

推奨される修正は、親ウィンドウ メッセージングを回避するために認証方法を更新して COOP 互換になることです。 MSAL.jsを使用する場合は、サポートされている最新バージョン (v5) にアップグレードします。 MSAL.js v5 では、COOP と互換性のあるウィンドウ通信が使用されます。

移行手順については、「 [MSAL Browser v4 から v5 への移行](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v4-migration)」を参照してください。

1. MSAL.js パッケージを、サポートされている最新バージョン (v5) に更新します。
2. 対話型サインインをテストして、COOP を有効にしてポップアップが正常に完了したことを確認します。

### COOP ポリシーの適用を管理する

MSAL.js v5 にすぐにアップグレードできない場合、または別のライブラリを使用できない場合は、COOP の適用を管理して、既存のフローを一時的に使用し続けます。 COOP と互換性のある認証フローを実装したり、MSAL.js v5 にアップグレードしたりするときに、Microsoft Graphセルフサービス API を使用します。

`coopEnforcement` アプリケーション プロパティを使用すると、サポート要求を開かずに適用をオプトアウトできます。 アプリケーションが COOP 対応になったら、同じプロパティを使用してオプトインします。

Warnung

COOP ポリシーをオプトアウトしても、基になる脆弱性は修正されません。 ポリシーが無効になっている間、アプリケーションは COOP ヘッダーが防ぐのに役立つクロスオリジン攻撃に対して引き続き公開されます。

アプリケーションがサインインにリダイレクトする前に親ウィンドウの関係を切断し、信頼されたサイトのみがサインイン ポップアップを開くことができるようにする場合にのみ、オプトアウトします。 アプリケーションが互換性のあるフローを使用するとすぐに、適用を再度有効にします。

1. Microsoft Graph[を使用してアプリケーション認証の動作を構成](https://learn.microsoft.com/ja-jp/graph/applications-authenticationbehaviors?tabs=http)し、移行中にオプトアウトする手順に従います。
2. COOP と互換性のある認証フローを実装するか、MSAL.js v5 にアップグレードします。
3. アプリケーションの認証フローをテストします。
4. `coopEnforcement` プロパティを使用してオプトバックします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/how-to-web-app-node-edit-profile-prepare-app"} -->
## プロファイル編集用の Node.js Web アプリケーションを設定する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-edit-profile-prepare-app
- Service: identity-platform
- Article date: 2025-03-16
- Summary: 外部テナントで多要素認証保護を使用してプロファイル編集用の Node.js Web アプリケーションを設定する方法を学習する

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

顧客ユーザーが外部向けアプリに正常にサインインした後、ユーザーによるプロファイルの編集を有効にできます。 顧客ユーザーが [Microsoft Graph API の](https://learn.microsoft.com/ja-jp/graph/api/user-get)`/me` エンドポイントを使って、自分のプロファイルを管理できるようにします。 `/me` エンドポイントを呼び出すにはサインインしているユーザーが必要であり、したがって委任されたアクセス許可が必要です。

ユーザー本人のみが自分のプロフィールに変更を加えられるようにするために、ユーザーは MFA チャレンジを完了する必要があります。

このガイドでは、多要素認証 (MFA) 保護を用いたプロフィール編集をサポートするように Web アプリを設定する方法について学習します。

- アプリでは、条件付きアクセス ポリシーを使用して MFA 要件を有効にします。
- Web アプリのセットアップには、クライアント Web アプリと中間層サービス アプリの 2 つの Web サービスが含まれます。
- ユーザーのサインインで、クライアント Web アプリがユーザーのプロフィールを読み取り表示します。
- 中間層サービス アプリでアクセス トークンが取得され、ユーザーの代理でプロフィールが編集されます。

**更新できるプロパティ**

顧客ユーザーがプロファイルで編集できるフィールドをカスタマイズするには、「*Microsoft Graph API とアクセス許可*」の表の「[プロファイルの更新](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-user-permissions#microsoft-graph-apis-and-permissions)」行で示されているプロパティから選びます。

### [前提条件]

- [Node.js Web アプリでユーザーをサインインさせるための外部テナントの設定](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-sign-in-prepare-app)に関するチュートリアル シリーズの手順を完了します。 このチュートリアルでは、外部テナントでアプリを登録し、ユーザーをサインインさせる Web アプリを構築する方法が示されています。 ここでは、この Web アプリケーションをクライアント Web アプリと呼びます。
- 「[サンプルの Node.js Web アプリケーションでのユーザーのサインインとプロフィールの編集](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-node-sign-in-edit-profile)」の手順を完了していること。 この記事では、プロフィール編集用に外部テナントを設定する方法を説明しています。

### クライアント Web アプリの更新

次のファイルを Node.js クライアント Web アプリ (*App* ディレクトリ) に追加します。

- *views/gatedUpdateProfile.hbs* および *views/updateProfile.hbs* を作成します。
- *fetch.js* を作成します。

#### アプリ UI コンポーネントを更新する

1. コード エディターで、*App/views/index.hbs* ファイルを開き、次のコード スニペットを使用して**プロフィールの編集**リンクを追加します。

    ```html
    <a href="/users/gatedUpdateProfile">Edit profile</a>
    ```

    更新後、*App/views/index.hbs* ファイルは次のファイルのようになるはずです。

    ```html
    <h1>{{title}}</h1>
    {{#if isAuthenticated }}
    <p>Hi {{username}}!</p>
    <a href="/users/id">View ID token claims</a>
    <br>
    <a href="/users/gatedUpdateProfile">Profile editing</a>
    <br>
    <a href="/auth/signout">Sign out</a>
    {{else}}
    <p>Welcome to {{title}}</p>
    <a href="/auth/signin">Sign in</a>
    {{/if}}
    ```
2. コード エディターで、*App/views/gatedUpdateProfile.hbs* ファイルを開き、次のコードを追加します。

    ```html
    <h1>Microsoft Graph API</h1>
    <h3>/me endpoint response</h3>
    <div style="display: flex; justify-content: left;">
        <div style="size: 400px;">
            <label>Id :</label>
            <label> {{profile.id}}</label>
            <br />
            <label for="email">Email :</label>
            <label> {{profile.mail}}</label>
            <br />
            <label for="userName">Display Name :</label>
            <label> {{profile.displayName}}</label>
            <br />
            <label for="userName">Given Name :</label>
            <label> {{profile.givenName}}</label>
            <br />    
            <label for="userSurname">Surname :</label>
            <label> {{profile.surname}}</label>
            <br />
        </div>
        <div>
            <br />
            <br />
            <a href="/users/updateProfile">
                <button>Edit Profile</button>
            </a>
        </div>
    </div>
    <br />
    <br />
    <a href="/">Go back</a>
    ```

    - このファイルには、[編集可能なユーザーの詳細](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-user-permissions#microsoft-graph-apis-and-permissions)を表す HTML フォームが含まれています。
    - ユーザーが表示名を更新するには **[プロフィールの編集]** ボタンを選択する必要があります。ただし、MFA チャレンジをまだ完了していない場合は、それを完了する必要があります。
3. コード エディターで、*App/views/updateProfile.hbs* ファイルを開き、次のコードを追加します。

    ```html
    <h1>Microsoft Graph API</h1>
    <h3>/me endpoint response</h3>
    <div style="display: flex; justify-content: left;">
        <div style="size: 400px;">
            <form id="userInfoForm" action="/users/update" method="POST">
                <label>Id :</label>
                <label> {{profile.id}}</label>
                <br />
                <label>Email :</label>
                <label> {{profile.mail}}</label>
                <br />
                <label for="userName">Display Name :</label>
                <input
                    type="text"
                    id="displayName"
                    name="displayName"
                    value="{{profile.displayName}}"
                />
                <br />
                <label for="userName">Given Name :</label>
                <input
                    type="text"
                    id="givenName"
                    name="givenName"
                    value="{{profile.givenName}}"
                />
                <br />    
                <label for="userSurname">Surname :</label>
                <input
                    type="text"
                    id="surname"
                    name="surname"
                    value="{{profile.surname}}"
                />
                <br />    
                <button type="submit" id="button">Save</button>
            </form>
        </div>
        <br />
    </div>
    <a href="/">Go back</a>
    ```

このファイルには、[編集可能なユーザーの詳細](https://learn.microsoft.com/ja-jp/entra/external-id/customers/reference-user-permissions#microsoft-graph-apis-and-permissions)を表す HTML フォームが含まれていますが、これは顧客ユーザーが MFA チャレンジを完了した後にしか表示されません。

#### アプリの依存関係をインストールする

ターミナルで、次のコマンドを実行し、`axios`、`cookie-parser`、`body-parser`、`method-override` といった Node パッケージをさらにインストールします。

```console
npm install axios cookie-parser body-parser method-override 
```

### 中間層アプリを設定する

このセクションでは、中間層アプリを設定します。

1. *API* ディレクトリを作成します。
2. 中間層アプリ プロジェクトを作成するには、*API* ディレクトリに移動して、次のコマンドを実行します。

    ```console
    npm init -y
    ```
3. *API* ディレクトリで、新しいファイル (*authConfig.js*、*fetch.js*、*index.js*) を作成します。
4. 中間層アプリの依存関係をインストールするには、次のコマンドを実行します。

```console
npm install express express-session axios cookie-parser http-errors @azure/msal-node body-parser uuid 
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/how-to-web-app-node-edit-profile-update-profile"} -->
## Node.js Web アプリでプロファイルを編集する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-edit-profile-update-profile
- Service: identity-platform
- Article date: 2025-03-14
- Summary: 外部向けの Node.js Web アプリで多要素認証保護を使用してプロファイルを編集する方法について説明します

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事は、Node.js Web アプリにプロファイル編集ロジックを追加する方法を示すシリーズのパート 2 です。 [このシリーズのパート 1](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-edit-profile-prepare-app) では、プロファイル編集用にアプリを設定しました。

この攻略ガイドでは、プロファイル編集のために Microsoft Graph API を呼び出す方法について説明します。

### [前提条件]

- このガイド シリーズの第 2 部「 [プロファイル編集用に Node.js Web アプリケーションを設定する](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-edit-profile-prepare-app)」の手順を完了します。

### クライアント Web アプリを完成させる

このセクションでは、クライアント Web アプリの ID 関連コードを追加します。

#### authConfig.js ファイルを更新する

クライアント Web アプリの *authConfig.js* ファイルを更新します。

1. コード エディターで *App/authConfig.jsファイルを * 開き、 `GRAPH_API_ENDPOINT`、 `GRAPH_ME_ENDPOINT` 、 `editProfileScope`の 3 つの新しい変数を追加します。 次の 3 つの変数を必ずエクスポートしてください。

    ```JavaScript
    //...
    const GRAPH_API_ENDPOINT = process.env.GRAPH_API_ENDPOINT || "https://graph.microsoft.com/";
    // https://learn.microsoft.com/graph/api/user-update?tabs=http
    const GRAPH_ME_ENDPOINT = GRAPH_API_ENDPOINT + "v1.0/me";
    const editProfileScope = process.env.EDIT_PROFILE_FOR_CLIENT_WEB_APP || 'api://{clientId}/EditProfileService.ReadWrite';
    
    module.exports = {
        //...
        editProfileScope,
        GRAPH_API_ENDPOINT,
        GRAPH_ME_ENDPOINT,
        //...
    };
    ```

    - `editProfileScope` 変数は、MFA で保護されたリソース、つまりミッドティア アプリ (EditProfileService アプリ) を表します。
    - `GRAPH_ME_ENDPOINT` は Microsoft Graph API エンドポイントです。
2. `{clientId}` を、先ほど登録した ミッドティア アプリ (EditProfileService アプリ) のアプリケーション (クライアント) ID で置き換えます。

#### クライアント Web アプリでアクセス トークンを取得する

コード エディターで*、App/auth/AuthProvider.jsファイルを*開き、`getToken` クラスの `AuthProvider` メソッドを更新します。

```javascript
    class AuthProvider {
    //...
        getToken(scopes, redirectUri = "http://localhost:3000/") {
            return  async function (req, res, next) {
                const msalInstance = authProvider.getMsalInstance(authProvider.config.msalConfig);
                try {
                    msalInstance.getTokenCache().deserialize(req.session.tokenCache);
    
                    const silentRequest = {
                        account: req.session.account,
                        scopes: scopes,
                    };
                    const tokenResponse = await msalInstance.acquireTokenSilent(silentRequest);
    
                    req.session.tokenCache = msalInstance.getTokenCache().serialize();
                    req.session.accessToken = tokenResponse.accessToken;
                    next();
                } catch (error) {
                    if (error instanceof msal.InteractionRequiredAuthError) {
                        req.session.csrfToken = authProvider.cryptoProvider.createNewGuid();
    
                        const state = authProvider.cryptoProvider.base64Encode(
                            JSON.stringify({
                                redirectTo: redirectUri,
                                csrfToken: req.session.csrfToken,
                            })
                        );
                        
                        const authCodeUrlRequestParams = {
                            state: state,
                            scopes: scopes,
                        };
    
                        const authCodeRequestParams = {
                            state: state,
                            scopes: scopes,
                        };
    
                        authProvider.redirectToAuthCodeUrl(
                            req,
                            res,
                            next,
                            authCodeUrlRequestParams,
                            authCodeRequestParams,
                            msalInstance
                        );
                    }
    
                    next(error);
                }
            };
        }
    }
    //...
```

`getToken` メソッドは、指定したスコープを使用してアクセス トークンを取得します。 `redirectUri` パラメーターは、アプリがアクセス トークンを取得した後のリダイレクト URL です。

#### users.js ファイルを更新する

コード エディターで *、App/routes/users.js* ファイルを開き、次のルートを追加します。

```JavaScript
    //...
    
    var { fetch } = require("../fetch");
    const { GRAPH_ME_ENDPOINT, editProfileScope } = require('../authConfig');
    //...
    
router.get(
  "/gatedUpdateProfile",
  isAuthenticated,
  authProvider.getToken(["User.Read"]), // check if user is authenticated
  async function (req, res, next) {
    const graphResponse = await fetch(
      GRAPH_ME_ENDPOINT,
      req.session.accessToken,
    );
    if (!graphResponse.id) {
      return res 
        .status(501) 
        .send("Failed to fetch profile data"); 
    }
    res.render("gatedUpdateProfile", {
      profile: graphResponse,
    });
  },
);

router.get(
  "/updateProfile",
  isAuthenticated, // check if user is authenticated
  authProvider.getToken(
    ["User.Read", editProfileScope],
    "http://localhost:3000/users/updateProfile",
  ),
  async function (req, res, next) {
    const graphResponse = await fetch(
      GRAPH_ME_ENDPOINT,
      req.session.accessToken,
    );
    if (!graphResponse.id) {
      return res 
        .status(501) 
        .send("Failed to fetch profile data"); 
    }
    res.render("updateProfile", {
      profile: graphResponse,
    });
  },
);

router.post(
  "/update",
  isAuthenticated,
  authProvider.getToken([editProfileScope]),
  async function (req, res, next) {
    try {
      if (!!req.body) {
        let body = req.body;
        fetch(
          "http://localhost:3001/updateUserInfo",
          req.session.accessToken,
          "POST",
          {
            displayName: body.displayName,
            givenName: body.givenName,
            surname: body.surname,
          },
        )
          .then((response) => {
            if (response.status === 204) {
              return res.redirect("/");
            } else {
              next("Not updated");
            }
          })
          .catch((error) => {
            console.log("error,", error);
          });
      } else {
        throw { error: "empty request" };
      }
    } catch (error) {
      next(error);
    }
  },
);
    //...
```

- 顧客ユーザーが `/gatedUpdateProfile` リンクを選択したら、 ルートをトリガーします。 このアプリによって次のことが行われます。

    1. *User.Read* アクセス許可を持つアクセス トークンを取得します。
    2. Microsoft Graph API を呼び出して、サインインしているユーザーのプロファイルを読み取ります。
    3. *gatedUpdateProfile.hbs* UI にユーザーの詳細を表示します。
- ユーザーが表示名を更新するとき、つまり `/updateProfile` ボタンを選択したときに、 ルートをトリガーします。 このアプリによって次のことが行われます。

    1. *editProfileScope* スコープを使用して、中間層アプリ (EditProfileService アプリ) を呼び出します。 ミッドティア アプリ (EditProfileService アプリ) を呼び出すと、ユーザーがまだ MFA チャレンジを完了していない場合は、完了する必要があります。
    2. *updateProfile.hbs* UI にユーザーの詳細を表示します。
- ユーザーが `/update` または **updateProfile.hbs** の *[保存]* ボタンを選択すると、 ルートがトリガーされます。 このアプリによって次のことが行われます。

    1. アプリ セッションのアクセス トークンを取得します。 次のセクションでは、ミッドティア アプリ (EditProfileService アプリ) がアクセス トークンを取得する方法について説明します。
    2. すべてのユーザーの詳細を収集します。
    3. Microsoft Graph API を呼び出して、ユーザーのプロファイルを更新します。

#### fetch.js ファイルを更新する

アプリは、 *App/fetch.js* ファイルを使用して、実際の API 呼び出しを行います。

コード エディターで、 *App/fetch.jsファイルを * 開き、PATCH 操作オプションを追加します。 ファイルを更新すると、結果のファイルは次のコードのようになります。

```JavaScript
var axios = require('axios');
var authProvider = require("./auth/AuthProvider");

/**
 * Makes an Authorization "Bearer" request with the given accessToken to the given endpoint.
 * @param endpoint
 * @param accessToken
 * @param method
 */
const fetch = async (endpoint, accessToken, method = "GET", data = null) => {
    const options = {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    };
    console.log(`request made to ${endpoint} at: ` + new Date().toString());

    switch (method) {
        case 'GET':
            const response = await axios.get(endpoint, options);
            return await response.data;
        case 'POST':
            return await axios.post(endpoint, data, options);
        case 'DELETE':
            return await axios.delete(endpoint + `/${data}`, options);
        case 'PATCH': 
            return await axios.patch(endpoint, ReqBody = data, options);
        default:
            return null;
    }
};

module.exports = { fetch };
```

### ミッドティア アプリを完成させる

このセクションでは、ミッドティア アプリ (EditProfileService アプリ) の ID 関連コードを追加します。

1. コード エディターで *Api/authConfig.js* ファイルを開き、次のコードを追加します。

    ```JavaScript
    require("dotenv").config({ path: ".env.dev" });
    
    const TENANT_SUBDOMAIN =
      process.env.TENANT_SUBDOMAIN || "Enter_the_Tenant_Subdomain_Here";
    const TENANT_ID = process.env.TENANT_ID || "Enter_the_Tenant_ID_Here";
    const REDIRECT_URI =
      process.env.REDIRECT_URI || "http://localhost:3000/auth/redirect";
    const POST_LOGOUT_REDIRECT_URI =
      process.env.POST_LOGOUT_REDIRECT_URI || "http://localhost:3000";
    
    /**
     * Configuration object to be passed to MSAL instance on creation.
     * For a full list of MSAL Node configuration parameters, visit:
     * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/configuration.md
     */
    const msalConfig = {
      auth: {
        clientId:
          process.env.CLIENT_ID ||
          "Enter_the_Edit_Profile_Service_Application_Id_Here", // 'Application (client) ID' of the Edit_Profile Service App registration in Microsoft Entra admin center - this value is a GUID
        authority:
          process.env.AUTHORITY || `https://${TENANT_SUBDOMAIN}.ciamlogin.com/`, // Replace the placeholder with your external tenant name
        clientSecret: process.env.CLIENT_SECRET || "Enter_the_Client_Secret_Here ", // Client secret generated from the app registration in Microsoft Entra admin center
      },
      system: {
        loggerOptions: {
          loggerCallback(loglevel, message, containsPii) {
            console.log(message);
          },
          piiLoggingEnabled: false,
          logLevel: "Info",
        },
      },
    };
    
    const GRAPH_API_ENDPOINT = process.env.GRAPH_API_ENDPOINT || "graph_end_point";
    // Refers to the user that is single user singed in.
    // https://learn.microsoft.com/graph/api/user-update?tabs=http
    const GRAPH_ME_ENDPOINT = GRAPH_API_ENDPOINT + "v1.0/me";
    
    module.exports = {
      msalConfig,
      REDIRECT_URI,
      POST_LOGOUT_REDIRECT_URI,
      TENANT_SUBDOMAIN,
      GRAPH_API_ENDPOINT,
      GRAPH_ME_ENDPOINT,
      TENANT_ID,
    };
    ```

    プレースホルダーを見つけてください。

    - `Enter_the_Tenant_Subdomain_Here`。これを、ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。
    - `Enter_the_Tenant_ID_Here`。これを、テナント ID に置き換えます。 テナント ID がない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。
    - `Enter_the_Edit_Profile_Service_Application_Id_Here` これを、 [先ほど登録した EditProfileService](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-node-sign-in-edit-profile) のアプリケーション (クライアント) ID 値に置き換えます。
    - `Enter_the_Client_Secret_Here` をクリックし、先ほどコピーした [EditProfileService アプリ シークレット](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-node-sign-in-edit-profile) 値に置き換えます。
    - `graph_end_point`を Microsoft Graph API エンドポイントである`https://graph.microsoft.com/`に置き換えます。
2. コード エディターで Api */fetch.jsファイルを * 開き、 *[Api/fetch.js](https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial/blob/main/1-Authentication/7-edit-profile-with-mfa-express/Api/fetch.js)* ファイルからコードを貼り付けます。 `fetch` 関数はアクセス トークンとリソース エンドポイントを使用して、実際の API 呼び出しを行います。
3. コード エディターで Api */index.jsファイルを * 開き、 *[Api/index.js](https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial/blob/main/1-Authentication/7-edit-profile-with-mfa-express/Api/index.js)* ファイルからコードを貼り付けます。

#### acquireTokenOnBehalfOf を使用してアクセス トークンを取得する

*Api/index.js* ファイルでは、中間層アプリ (EditProfileService アプリ) は [acquireTokenOnBehalfOf](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/confidentialclientapplication#@azure-msal-node-confidentialclientapplication-acquiretokenonbehalfof) 関数を使用してアクセス トークンを取得します。この関数を使用して、そのユーザーの代わりにプロファイルを更新します。

```javascript
async function getAccessToken(tokenRequest) {
  try {
    const response = await cca.acquireTokenOnBehalfOf(tokenRequest);
    return response.accessToken;
  } catch (error) {
    console.error("Error acquiring token:", error);
    throw error;
  }
}
```

`tokenRequest` パラメーターは、次のコードに示すように定義されます。

```javascript
    const tokenRequest = {
      oboAssertion: req.headers.authorization.replace("Bearer ", ""),
      authority: `https://${TENANT_SUBDOMAIN}.ciamlogin.com/${TENANT_ID}`,
      scopes: ["User.ReadWrite"],
      correlationId: `${uuidv4()}`,
    };
```

同じファイル *の API/index.js*では、中間層アプリ (EditProfileService アプリ) が Microsoft Graph API を呼び出してユーザーのプロファイルを更新します。

```JavaScript
   let accessToken = await getAccessToken(tokenRequest);
    fetch(GRAPH_ME_ENDPOINT, accessToken, "PATCH", req.body)
      .then((response) => {
        if (response.status === 204) {
          res.status(response.status);
          res.json({ message: "Success" });
        } else {
          res.status(502);
          res.json({ message: "Failed, " + response.body });
        }
      })
      .catch((error) => {
        res.status(502);
        res.json({ message: "Failed, " + error });
      });

```

### アプリをテストする

アプリをテストするには、次の手順に従います。

1. クライアント アプリを実行するには、ターミナル ウィンドウを形成し、 *App* ディレクトリに移動し、次のコマンドを実行します。

    ```Console
    npm start
    ```
2. クライアント アプリを実行するには、ターミナル ウィンドウを形成し、 *API* ディレクトリに移動し、次のコマンドを実行します。

    ```Console
    npm start
    ```
3. ブラウザーを開き、http://localhost:3000. に移動します SSL 証明書エラーが発生した場合は、`.env` ファイルを作成し、次の構成を追加します。

    ```Console
    # Use this variable only in the development environment. 
    # Remove the variable when you move the app to the production environment.
    NODE_TLS_REJECT_UNAUTHORIZED='0'
    ```
4. **[サインイン**] ボタンを選択し、サインインします。
5. サインイン ページで、 **メール アドレス**を入力し、[ **次へ**] を選択し、 **パスワード**を入力して、[ **サインイン**] を選択します。 アカウントをお持ちでない場合は、[アカウントなし] を選択してください **。1 つの** リンクを作成して、サインアップ フローを開始します。
6. プロファイルを更新するには、[ **プロファイル編集** ] リンクを選択します。 次のスクリーンショットのようなページが表示されます。

    [Image: ユーザー更新プロファイルのスクリーンショット。]
7. プロファイルを編集するには、[ **プロファイルの編集]** ボタンを選択します。 MFA チャレンジをまだ完了していない場合、アプリから、完了するように求められます。
8. プロファイルの詳細を変更し、[ **保存]** ボタンを選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/how-to-web-app-node-sign-in-call-api-call-api"} -->
## Node.js Web アプリケーションで API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-sign-in-call-api-call-api
- Service: identity-platform
- Article date: 2025-03-16
- Summary: Microsoft Entra External ID からのアクセス トークンを使用して、Node.js Web アプリケーションで保護された API を呼び出す方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、「 [アクセス トークンの取得](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-sign-in-call-api-sign-in-acquire-access-token#acquire-access-token)」で取得したアクセス トークンを使用して、Node.js クライアント Web アプリから Web API を呼び出す方法について説明します。 Web API は Microsoft Entra 外部 ID によって保護されています。 この記事は、4 部構成のガイド シリーズの 4 番目と最後の部分です。

### 前提条件

- このガイド シリーズの最初のパートの「Node.js Web アプリケーション [で API を呼び出す外部テナントを準備する」](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-sign-in-call-api-prepare-tenant)手順を完了します。
- このガイド シリーズの第 2 部「 [Node.js Web アプリケーションで API を呼び出すアプリを準備する」の](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-sign-in-call-api-prepare-app)手順を完了します。
- このガイド シリーズの第 3 部の手順を完了して [、Node.js Web アプリの記事のアクセス トークンを取得します](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-sign-in-call-api-sign-in-acquire-access-token) 。

### コードを更新する

1. コード エディターで *、routes/todos.js* ファイルを開き、次のコードを追加します。

    ```javascript
        const express = require('express');
        const router = express.Router();
    
        const toDoListController = require('../controller/todolistController');
        const authProvider = require('../auth/AuthProvider');
        const { protectedResources } = require('../authConfig');
    
        // custom middleware to check auth state
        function isAuthenticated(req, res, next) {
            if (!req.session.isAuthenticated) {
                return res.redirect('/auth/signin'); // redirect to sign-in route
            }
    
            next();
        }        
        // isAuthenticated checks if user is authenticated
        router.get('/',isAuthenticated, authProvider.getToken(protectedResources.toDoListAPI.scopes.read),toDoListController.getToDos);
    
        router.delete('/', isAuthenticated,authProvider.getToken(protectedResources.toDoListAPI.scopes.write),toDoListController.deleteToDo);
    
        router.post('/',isAuthenticated,authProvider.getToken(protectedResources.toDoListAPI.scopes.write),toDoListController.postToDo);
    
        module.exports = router;
    ```

    このファイルには、保護された API でリソースを作成、読み取り、削除するための高速ルートが含まれています。 各ルートでは、そのシーケンスで実行される 3 つのミドルウェア関数が使用されます。

    - `isAuthenticated` は、ユーザーが認証されているかどうかを確認します。
    - `getToken` はアクセス トークンを要求します。 この関数は、「 [アクセス トークンの取得](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-sign-in-call-api-sign-in-acquire-access-token#acquire-access-token)」で前に定義しました。 たとえば、リソースの作成ルート (POST 要求) は、読み取りと書き込みのアクセス許可を持つアクセス トークンを要求します。
    - 最後に、 `postToDo` メソッドまたは `deleteToDo``getToDos` メソッドは、リソースを操作するための実際のロジックを処理します。 これらの関数は、 *コントローラー/todolistController.js* ファイルで定義されています。
2. コード エディターで *コントローラー/todolistController.jsファイルを * 開き、次のコードを追加します。

    ```javascript
        const { callEndpointWithToken } = require('../fetch');
        const { protectedResources } = require('../authConfig');
    
        exports.getToDos = async (req, res, next) => {
            try {
                const todoResponse = await callEndpointWithToken(
                    protectedResources.toDoListAPI.endpoint,
                    req.session.accessToken,
                    'GET'
                );
                res.render('todos', { isAuthenticated: req.session.isAuthenticated, todos: todoResponse.data });
            } catch (error) {
                next(error);
            }
        };
    
        exports.postToDo = async (req, res, next) => {
            try {
                if (!!req.body.description) {
                    let todoItem = {
                        description: req.body.description,
                    };
    
                    await callEndpointWithToken(
                        protectedResources.toDoListAPI.endpoint,
                        req.session.accessToken,
                        'POST',
                        todoItem
                    );
                    res.redirect('todos');
                } else {
                    throw { error: 'empty request' };
                }
            } catch (error) {
                next(error);
            }
        };
    
        exports.deleteToDo = async (req, res, next) => {
            try {
                await callEndpointWithToken(
                    protectedResources.toDoListAPI.endpoint,
                    req.session.accessToken,
                    'DELETE',
                    req.body._id
                );
                res.redirect('todos');
            } catch (error) {
                next(error);
            }
        };
    ```

    これらの各関数は、API を呼び出すために必要なすべての情報を収集します。 次に、 `callEndpointWithToken` 関数に作業を委任し、応答を待機します。 `callEndpointWithToken`関数は、*fetch.js* ファイルで定義されます。 たとえば、API でリソースを作成するために、 `postToDo` 関数はエンドポイント、アクセス トークン、HTTP メソッド、要求本文を `callEndpointWithToken` 関数に渡し、応答を待機します。 次に、ユーザーを *todo.hbs* ビューにリダイレクトして、すべてのタスクを表示します。
3. コード エディターでファイル *fetch.js* 開き、次のコードを追加します。

    ```javascript
        const axios = require('axios');
    
        /**
         * Makes an Authorization "Bearer" request with the given accessToken to the given endpoint.
         * @param endpoint
         * @param accessToken
         * @param method
         */
        const callEndpointWithToken = async (endpoint, accessToken, method, data = null) => {
            const options = {
                headers: {
                    Authorization: `Bearer ${accessToken}`,
                },
            };
    
            switch (method) {
                case 'GET':
                    return await axios.get(endpoint, options);
                case 'POST':
                    return await axios.post(endpoint, data, options);
                case 'DELETE':
                    return await axios.delete(endpoint + `/${data}`, options);
                default:
                    return null;
            }
        };
    
        module.exports = {
            callEndpointWithToken,
        };
    ```

    この関数は、実際の API 呼び出しを行います。 アクセス トークンをベアラー トークンの値として HTTP 要求ヘッダーに含める方法に注目してください。

    ```javascript
        //...        
        headers: {
            Authorization: `Bearer ${accessToken}`,
        }        
        //...
    ```
4. コード エディターで *.env* ファイルを開き、次の構成を追加します。

    ```text
        # Use this variable only in the development environment. 
        # Please remove the variable when you move the app to the production environment.
        NODE_TLS_REJECT_UNAUTHORIZED='0'
    ```

    .env ファイルの `NODE_TLS_REJECT_UNAUTHORIZED='0'` 設定は、自己署名証明書エラーなどの SSL 証明書エラーを無視するように Node.js に指示します。
5. コード エディターで、 `app.js` ファイルを開き、次の手順を実行します。

    1. 次のコードを使用して、todo ルーターを追加します。

        ```javascript
            var todosRouter = require('./routes/todos');
        ```
    2. 次のコードを使用して、todo ルーターを使用します。

        ```javascript
            app.use('/todos', todosRouter); 
        ```

### Web アプリと API の実行とテスト

この時点で、クライアント Web アプリから Web API を呼び出す準備ができました。

1. Web API アプリを起動 [するには、ASP.NET Web API のセキュリティ](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-protect-web-api-dotnet-core-build-app) 保護に関する記事の手順を使用します。 これで、Web API でクライアント要求を処理する準備ができました。
2. ターミナルで、 `ciam-sign-in-call-api-node-express-web-app`などのクライアント Web アプリが含まれているプロジェクト フォルダーにいることを確認し、次のコマンドを実行します。

    ```console
    npm start
    ```

    クライアント Web アプリが起動します。
3. サンプル [Web アプリと API の実行とテスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-node-sign-in-call-api#run-and-test-sample-web-app-and-api) の手順を使用して、クライアント アプリが Web API を呼び出す方法を示します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/how-to-web-app-node-sign-in-call-api-prepare-app"} -->
## Web API を呼び出す Node.js Web アプリを作成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-sign-in-call-api-prepare-app
- Service: identity-platform
- Article date: 2025-03-16
- Summary: Microsoft Entra 外部 ID からのアクセス トークンを使用して保護された API を呼び出す Node.js クライアント Web アプリを準備する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、「[チュートリアル: Web API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-sign-in-prepare-app) Node.js Web アプリでユーザーをサインインさせる外部テナントを準備する」で作成したアプリ プロジェクトを準備します。 この記事は、4 部構成のガイド シリーズの第 2 部です。

### 前提 条件

- このガイド シリーズの最初のパートの「Node.js Web アプリケーション [で API を呼び出す外部テナントを準備する」](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-sign-in-call-api-prepare-tenant)手順を完了します。

### プロジェクト ファイルを更新する

より多くのファイルを作成し、*fetch.js*、*todolistController.js*、*todos.js*、*todos.hbs*、*.env*を作成し、それらを整理して以下のプロジェクト構造を実現します。

```powershell
    ciam-sign-in-call-api-node-express-web-app/
    ├── .env
    └── server.js
    └── app.js
    └── authConfig.js
    └── fetch.js
    └── package.json
    └── auth/
        └── AuthProvider.js
    └── controller/
        └── authController.js
        └── todolistController.js
    └── routes/
        └── auth.js
        └── index.js
        └── todos.js
        └── users.js
    └── views/
        └── layouts.hbs
        └── error.hbs
        └── id.hbs
        └── index.hbs   
        └── todos.hbs 
    └── public/stylesheets/
        └── style.css
```

### アプリの依存関係をインストールする

ターミナルで、次のコマンドを実行して、さらにノード パッケージ (`axios`、`cookie-parser`、`body-parser`、`method-override`) をインストールします。

```console
    npm install axios cookie-parser body-parser method-override 
```

#### アプリ UI コンポーネントを更新する

1. 最初に、コードエディタで *views/index.hbs* ファイルを開き、次に *[Todolist* の表示] リンクを追加します。

    ```html
        <a href="/todos">View your todolist</a>
    ```

    *views/index.hbs* ファイルは、次のファイルのようになります。

    ```html
        <h1>{{title}}</h1>
        {{#if isAuthenticated }}
        <p>Hi {{username}}!</p>
        <a href="/users/id">View your ID token claims</a>
        <br>
        <a href="/todos">View your todolist</a>
        <br>
        <a href="/auth/signout">Sign out</a>
        {{else}}
        <p>Welcome to {{title}}</p>
        <a href="/auth/signin">Sign in</a>
        {{/if}}
    ```

    *ciam-ToDoList-api*を操作できる UI へのリンクを追加します。 このガイドの後半で、このエンドポイントの高速ルートを定義します。
2. コード エディターでファイル `views/todos.hbs` 開き、次のコードを追加します。

    ```html
        <h1>Todolist</h1>
        <div>
            <form action="/todos" method="POST">
                <input type="text" name="description" class="form-control" placeholder="Enter a task" aria-label="Enter a task"
                    aria-describedby="button-addon">
                <button type="submit" id="button-addon">Add</button>
            </form>
        </div>
        <div class="row" style="margin: 10px;">
            <ol id="todoListItems" class="list-group"> 
                {{#each todos}} 
                <li class="todoListItem" id="todoListItem">
                    <span>{{description}}</span>
                    <form action='/todos?_method=DELETE' method='POST'>
                        <span><input type='hidden' name='_id' value='{{id}}'></span>
                        <span><button type='submit'>Remove</button></span>
                    </form>
                </li> 
                {{/each}} 
            </ol>
        </div>
        <a href="/">Go back</a>
    ```

    このビューを使用すると、ユーザーは API 呼び出しを開始するタスクを実行できます。 たとえば、ユーザーがサインインし、アプリがアクセス トークンを取得した後、ユーザーはフォームを送信することで API アプリにリソース (タスク) を作成できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/how-to-web-app-node-sign-in-call-api-prepare-tenant"} -->
## Node.js Web アプリで API を呼び出す外部テナントを準備する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-sign-in-call-api-prepare-tenant
- Service: identity-platform
- Article date: 2025-03-16
- Summary: 外部テナントを準備してユーザーをサインインさせ、Node.js Web アプリケーションで API を呼び出す方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、認可のために外部テナントを準備します。 この記事は、4 部構成のガイドの最初の部分です。

### 前提条件

- [「チュートリアル: Microsoft ID プラットフォームを使用してユーザーをサインインさせる Node.js Web アプリを設定する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-sign-in-prepare-app)」の手順を完了します。
- [「チュートリアル: 外部テナントに登録されている ASP.NET Web API をセキュリティで保護する」の手順を完了します](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-protect-web-api-dotnet-core-build-app)。 このチュートリアルを完了したら、顧客のテナントに Web API を登録します。これにより、API のアクセス許可が公開され、アプリケーション ロールが発行されます。 セキュリティで保護された Web API もあります。 この Web API は、クライアント Web アプリケーションから呼び出します。

### idtyp トークン クレームを構成する [オプション]

**idtyp** の省略可能な要求を追加すると、Web API がトークンが **アプリ** トークンなのか **アプリ + ユーザー** トークンなのかを判断するのに役立ちます。 **scp** と**ロール**要求の組み合わせを同じ目的で使用できますが、**idtyp** 要求を使用すると、アプリ トークンとアプリ + ユーザー トークンを区別する最も簡単な方法です。 たとえば、トークンがアプリ専用トークンの場合、この要求の値は *app* です。

アクセス トークンに idtyp 要求を追加するには、 [オプションの要求の構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims?tabs=appui) に関する記事の手順を使用します。

- **[トークンの種類**] で [**アクセス**] を選択します。
- 省略可能な要求の一覧から **idtyp** を選択します。

#### Web アプリに API のアクセス許可を付与する

前提条件から、顧客のテナントにクライアント アプリを登録しました。 また、顧客に Web API アプリを登録しました。 次に、クライアント アプリに API アクセス許可を付与する必要があります。

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
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/how-to-web-app-node-sign-in-call-api-sign-in-acquire-access-token"} -->
## Node.js Web アプリでアクセス トークンを取得する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-sign-in-call-api-sign-in-acquire-access-token
- Service: identity-platform
- Article date: 2025-03-16
- Summary: 独自の Node.js Web アプリケーションで API を呼び出すためのアクセス トークンを取得する方法について説明します。

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

この記事では、コードを更新して、Web アプリがアクセス トークンを取得できるようにします。 ノード Web アプリケーションへの認証と承認の追加を簡略化するには、 [Node 用 Microsoft Authentication Library (MSAL)](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) を使用します。 この記事は、4 部構成のガイド シリーズの第 3 部です。

### [前提条件]

- このガイド シリーズの最初のパートの「Node.js Web アプリケーション [で API を呼び出す外部テナントを準備する」](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-sign-in-call-api-prepare-tenant)手順を完了します。
- このガイド シリーズの第 2 部「 [Node.js Web アプリケーションで API を呼び出すアプリを準備する」の](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-sign-in-call-api-prepare-app)手順を完了します。

### MSAL 構成オブジェクトを更新する

コード エディターでファイル *authConfig.js* 開き、 `protectedResources` オブジェクトを追加してコードを更新します。

```javascript
    //..   
    const toDoListReadScope = process.env.TODOLIST_READ || 'api://Enter_the_Web_Api_Application_Id_Here/ToDoList.Read';
    const toDoListReadWriteScope = process.env.TODOLIST_READWRITE || 'api://Enter_the_Web_Api_Application_Id_Here/ToDoList.ReadWrite';
    
    const protectedResources = {
        toDoListAPI: {
            endpoint: 'https://localhost:44351/api/todolist',
            scopes: {
                read: [toDoListReadScope],
                write: [toDoListReadWriteScope],
            },
        },
    };    
    module.exports = {
        //..
        protectedResources,
        //..
    };
```

*authConfig.js* ファイルで、`Enter_the_Web_Api_Application_Id_Here`を、顧客のテナントに登録した Web API アプリのアプリケーション (クライアント) ID に置き換えます。

`todolistReadScope`変数と`todolistReadWriteScope`変数には、外部テナントで設定した Web API のフル スコープ URL が保持されます。 `protectedResources` オブジェクトをエクスポートしてください。

### アクセス トークンを取得する

コード エディターで*、auth/AuthProvider.jsファイルを*開き、`getToken` クラスの`AuthProvider` メソッドを更新します。

```javascript
    const axios = require('axios');
    class AuthProvider {
    //...
        getToken(scopes) {
            return  async function (req, res, next) {
                const msalInstance = authProvider.getMsalInstance(authProvider.config.msalConfig);
                try {
                    msalInstance.getTokenCache().deserialize(req.session.tokenCache);
    
                    const silentRequest = {
                        account: req.session.account,
                        scopes: scopes,
                    };
    
                    const tokenResponse = await msalInstance.acquireTokenSilent(silentRequest);
    
                    req.session.tokenCache = msalInstance.getTokenCache().serialize();
                    req.session.accessToken = tokenResponse.accessToken;
                    next();
                } catch (error) {
                    if (error instanceof msal.InteractionRequiredAuthError) {
                        req.session.csrfToken = authProvider.cryptoProvider.createNewGuid();
    
                        const state = authProvider.cryptoProvider.base64Encode(
                            JSON.stringify({
                                redirectTo: 'http://localhost:3000/todos',
                                csrfToken: req.session.csrfToken,
                            })
                        );
                        
                        const authCodeUrlRequestParams = {
                            state: state,
                            scopes: scopes,
                        };
    
                        const authCodeRequestParams = {
                            state: state,
                            scopes: scopes,
                        };
    
                        authProvider.redirectToAuthCodeUrl(
                            req,
                            res,
                            next,
                            authCodeUrlRequestParams,
                            authCodeRequestParams,
                            msalInstance
                        );
                    }
    
                    next(error);
                }
            };
        }
    //...
    }
```

- まず、この関数はアクセス トークンをサイレントに (ユーザーに資格情報の入力を求めずに) 取得しようとします。

    ```javascript
    const silentRequest = {
        account: req.session.account,
        scopes: scopes,
    };
    
    const tokenResponse = await msalInstance.acquireTokenSilent(silentRequest);
    ```
- トークンをサイレントに正常に取得した場合は、それをセッションに格納します。 API を呼び出すときに、セッションからトークンを取得します。

    ```javascript
    req.session.accessToken = tokenResponse.accessToken;
    ```
- トークンをサイレントに取得できない (`InteractionRequiredAuthError` 例外などによる) 場合、アクセス トークンを新たに要求します。

注

クライアント アプリケーションがアクセス トークンを受け取ったら、それを不透明な文字列として扱う必要があります。 アクセス トークンは、クライアント アプリケーションではなく API を対象としています。 そのため、クライアント アプリケーションはアクセス トークンの読み取りまたは処理を試行しないでください。 代わりに、API への要求の *Authorization* ヘッダーにアクセス トークン as-is を含める必要があります。 API は、アクセス トークンを解釈し、それを使用してクライアント アプリケーションの要求を認証および承認する役割を担います。
<!-- /MSL-PAGE -->
