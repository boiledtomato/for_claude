# Microsoft Learn — Microsoft Entra / 開発者向け (Identity Platform・MSAL・Microsoft.Identity.Web) (part 9)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 110

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/web-apps-apis/on-behalf-of-flow"} -->
## MSAL.NET を使用したOn-Behalf-Of フロー - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/on-behalf-of-flow
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: MSAL.NET を使用してユーザーに代わって認証する方法。

### ASP.NET Coreまたはクラシック ASP.NET を使用している場合

ASP.NET Coreまたはクラシック ASP.NET 上に Web API を構築する場合は、`Microsoft.Identity.Web`を使用することをお勧めします。 [`Microsoft.Identity.Web`を使用した Web API を](https://github.com/AzureAD/microsoft-identity-web/wiki/web-apis)参照してください。

デシジョン ツリーを確認する: [MSAL.NET は自分に適していますか?](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/choosing-msal-dotnet)

### ユーザーに代わってトークンを取得する

#### シナリオ

- 次の図に示されていないクライアント (Web サイト、デスクトップ、モバイル、シングルページ アプリケーション) は、保護された Web API を呼び出し、その "Authorization" HTTP ヘッダーに JWT ベアラー トークンを提供します。
- 保護された Web API は、受信したユーザー トークンを検証し、MSAL.NET `AcquireTokenOnBehalfOf` メソッドを使用して Microsoft Entra に別のトークンを要求します。これにより、その Web API 自身が、ユーザーに代わって、ダウンストリーム Web API と呼ばれる別の Web API (たとえば Graph) を呼び出すことができます。

このフローは、On-Behalf-Of フロー (OBO) と呼ばれ、下図の上部に示されています。 一番下の部分はデーモン シナリオであり、Web API でも可能です。

[Image: 画像]

#### OBO を呼び出す方法

OBO 呼び出しは、[AcquireTokenOnBehalfOf(IEnumerable&lt;String&gt;, UserAssertion)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.iconfidentialclientapplication.acquiretokenonbehalfof#microsoft-identity-client-iconfidentialclientapplication-acquiretokenonbehalfof%28system-collections-generic-ienumerable%28%28system-string%29%29-microsoft-identity-client-userassertion%29) インターフェイスで `IConfidentialClientApplication` メソッドを呼び出すことによって行われます。

この呼び出しではキャッシュ自体が検索されるため、 `AcquireTokenSilent`を呼び出す必要はありません。また、更新トークンは格納されません。

アサーションなしで継続的アクセスが必要なシナリオについては、[有効期間が長いプロセスの OBO を](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/on-behalf-of-flow)参照してください

>
> **メモ：** ID トークンではなくアクセス トークンを `AcquireTokenOnBehalfOf` メソッドに渡してください。 ID トークンの目的は、ユーザーが認証されたことの確認であり、ユーザーに関連する情報が含まれています。 これに対し、アクセス トークンは、ユーザーがリソースにアクセスできるかどうかを決定します。これは、この on-Behalf-Of シナリオでより適切です。 MSAL は、適切なアクセス トークンを取得することに重点を置っています。 ID トークンも取得され、キャッシュされますが、有効期限は追跡されません。 そのため、ID トークンは期限切れになり、 `AcquireTokenSilent` は更新されません。

```csharp
private async Task AddAccountToCacheFromJwt(IEnumerable<string> scopes, JwtSecurityToken jwtToken, ClaimsPrincipal principal, HttpContext httpContext)
{
  if (jwtToken == null)
  {
    throw new ArgumentOutOfRangeException("tokenValidationContext.SecurityToken should be a JWT Token");
  }
  UserAssertion userAssertion = new UserAssertion(jwtToken.RawData, "urn:ietf:params:oauth:grant-type:jwt-bearer");
  IEnumerable<string> requestedScopes = scopes ?? jwtToken.Audiences.Select(a => $"{a}/.default");

  // Create the application
  var application = BuildConfidentialClientApplication(httpContext, principal);

  // await to make sure that the cache is filled in before the controller tries to get access tokens
  var result = await application.AcquireTokenOnBehalfOf(requestedScopes, userAssertion).ExecuteAsync();                     
}
```

#### ゲスト ユーザーを使用した On-Behalf-Of (OBO) フローに関する重要な注意事項

特にゲスト ユーザーで On-Behalf-Of (OBO) フローを実行する場合は、クライアント トークンからの `tid` 要求で示される特定のテナントをターゲットにすることが重要です。 トークンはユーザーのホーム テナント用であるため、OBO で `/common` または `/organizations` を使用しないでください。

##### 正しい使用パターン

1. **クライアント アサーション トークンから `tid` 要求を抽出**します。これにより、特定のテナントが識別されます。
2. **テナント固有の機関を使用**する: 抽出された `tid` 要求を使用して機関 URL を形成します。

##### 正しくないパターン

多くの実装では、 `/common` エンドポイントを誤って使用して OBO を実行しています。 この方法は推奨されておらず、特にゲスト ユーザーに問題が発生する可能性があります。

### 多要素認証 (MFA)、条件付きアクセス、増分同意の処理

#### 失敗シナリオ

これは、テナント管理者がエンドユーザーに Multi-Factor Authentication (MFA) チャレンジの完了を要求することで、ダウンストリーム API (Graph など) へのアクセスを制限する一般的なシナリオです。ただし、多くの場合、Web API に同じ制限は適用されません。

1. クライアント (デスクトップ アプリや Web サイトなど) は、Web API のトークンを要求します。 この時点では MFA は適用されません。
2. Web API は、このトークンを、代理フローを介してダウンストリーム Web API (Graph など) のトークンと交換しようとします。 Graph を介したアクセスでは、ユーザーが MFA チャレンジを完了している必要があるため、これは失敗します。 `AcquireTokenOnBehalfOf` の呼び出しは `MsalUiRequiredException` で失敗し、その `MsalUiRequiredException` には `Claims` プロパティも設定されます。

#### MFA が必要であることをクライアントに通知する方法

Web API は、要求文字列 [を使用して例外をクライアントに送り返す](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-oauth2-on-behalf-of-flow#error-response-example) 必要があります。 このエラーをクライアントに通知する [標準的なパターン](https://datatracker.ietf.org/doc/html/rfc6750#section-3.1) は、 `HTTP 401` とエラーの詳細をカプセル化する `WWW-Authenticate` ヘッダーを使用して応答することです。

##### Web API は 401 + WWW-Authenticate で応答します

```csharp
// This example is for an ASP.NET Core web API
public void ReplyUnauthorizedWithWwwAuthenticateHeader(MsalUiRequiredException ex)
{
     httpResponse.StatusCode = (int)HttpStatusCode.Unauthorized; // HTTP 401
     httpResponse.Headers[HeaderNames.WWWAuthenticate] = $"Bearer claims={ex.Claims}, error={ex.Message}";
}
```

##### クライアントでのエラーの処理

クライアントは、 `401` メッセージを解釈し、ヘッダー `WWW-Authenticate` 解析する必要があります。 MSAL.NET では、解析 API が提供されます。

```csharp
// assuming an HttpResponseMessage response with StatusCode=HttpStatusCode.Unauthorized
WwwAuthenticateParameters wwwParams = WwwAuthenticateParameters.CreateFromAuthenticationHeaders(response.HttpResponseHeaders, "Bearer");
string claims = wwwParams.Claims; // you may also extract other parameters such as Error and Authority

// desktop or mobile app
app.AcquireTokenInteractive(scopes).WithClaims(wwwParams.Claims);

// web app - redirect to the login page and add the claims to the authorization URL
RedirectToLogin(wwwParams.ConsentUri);
```

### 実行時間の長い OBO プロセス

OBO シナリオの 1 つは、Web API がユーザーに代わって実行時間の長いプロセスを実行する場合です (たとえば、アルバムを作成するOneDrive)。 これは、次のように実装できます。

1. 長時間実行されるプロセスを開始する前に、次を呼び出してください。

```csharp
string sessionKey = // custom key or null
var authResult = await ((ILongRunningWebApi)confidentialClientApp)
         .InitiateLongRunningProcessInWebApi(
              scopes,
              userAccessToken,
              ref sessionKey)
         .ExecuteAsync();
```

`userAccessToken` は、この Web API を呼び出すために使用されるユーザー アクセス トークンです。 `sessionKey` は、OBO トークンをキャッシュおよび取得するときにキーとして使用されます。 `null`に設定すると、MSAL によって、渡されたユーザー トークンのアサーション ハッシュに設定されます。 また、開発者は、ユーザー トークンからの省略可能な `sid` 要求など、特定のユーザー セッションを識別するものに設定することもできます (詳細については、「 [アプリに省略可能な要求を提供する](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/active-directory-optional-claims)」を参照してください)。 `InitiateLongRunningProcessInWebApi`はキャッシュをチェックしません。ユーザー トークンを使用して、Microsoft Entra IDから新しい OBO トークンを取得し、キャッシュして返します。

1. 実行時間の長いプロセスでは、OBO トークンが必要な場合は常に、次のパターンで `AcquireTokenInLongRunningProcess` を呼び出します。

```csharp
try {  
    authResult = await ((ILongRunningWebApi)confidentialClientApp)  
         .AcquireTokenInLongRunningProcess(  
              scopes,  
              sessionKey)  
         .ExecuteAsync();  
}
catch (MsalClientException ex) {  
    // No tokens were found with this cache key.  
    // First call InitiateLongRunningProcessInWebApi with a valid user assertion
    // to acquire tokens from Microsoft Entra ID and cache them.
    if (ex.ErrorCode == MsalError.OboCacheKeyNotInCacheError)
    {
          authResult = await ((ILongRunningWebApi)confidentialClientApp)
         .InitiateLongRunningProcessInWebApi(
              scopes,
              userAccessToken, // Valid access token
              ref sessionKey)
         .ExecuteAsync();
    }

} catch (MsalUiRequiredException ex) {  
    // A refresh token was used to acquire new tokens  
    // but Microsoft Entra ID requires the user to sign in again.  
    // Trigger your app's user sign-in again by replying with a 401 + WWW-Authenticate  
    // Then call InitiateLongRunningProcessInWebApi once a new access token is acquired from the user
    httpResponse.StatusCode = (int)HttpStatusCode.Unauthorized;
    httpResponse.Headers[HeaderNames.WWWAuthenticate] = $"Bearer claims={ex.Claims}, error={ex.Message}";
}
```

現在のユーザーのセッションに関連付けられている `sessionKey` を渡し、関連する OBO トークンを取得するために使用されます。 トークンの有効期限が切れている場合、MSAL はキャッシュされた更新トークンを使用して、Microsoft Entra IDから新しい OBO アクセス トークンを取得し、キャッシュします。 この `sessionKey`でトークンが見つからない場合、MSAL は `MsalClientException` または `MsalUiRequiredException`をスローします。 有効なユーザー トークンを取得し、その場合は `InitiateLongRunningProcessInWebApi` を呼び出してください。

#### 実行時間の長い OBO プロセスのキャッシュ削除

Web API シナリオでは [、分散永続化キャッシュを使用](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-token-cache-serialization?tabs=aspnetcore) することを強くお勧めします。 これらの API は更新トークンを格納するため、更新トークンの有効期間が長く、何度も繰り返し使用できるため、MSAL は有効期限を提案しません。

L1 キャッシュの最大サイズや L2 のスライディング有効期限など、L1 と L2 の削除ポリシーを手動で設定することをお勧めします。

#### 例外処理

トークンが見つからないときに `AcquireTokenInLongRunningProcess` が例外をスローし、L2 キャッシュに同じキャッシュ キーのキャッシュ エントリがある場合は、L2 キャッシュの読み取り操作が正常に完了したことを確認します。 `AcquireTokenInLongRunningProcess`は`InitiateLongRunningProcessInWebApi`と`AcquireTokenOnBehalfOf`とは異なり、キャッシュの読み取りが失敗した場合、このメソッドは元のユーザー アサーションがないため、Microsoft Entra IDから新しいトークンを取得できません。 Microsoftを使用している場合。Identity.Web.TokenCache を使用して分散キャッシュを有効にしたり、[OnL2CacheFailure](https://github.com/AzureAD/microsoft-identity-web/wiki/Token-Cache-Troubleshooting#i-configured-a-distributed-l2-cache-but-nothings-gets-written-to-it) イベントを設定して L2 呼び出しを再試行したり、[組み込みの MSAL 機能を使用して](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/msal-logging)有効にできる追加のログを追加したりします。

#### アカウントの削除

MSAL 4.51.0 以降では、キャッシュされたトークンを削除するには、キャッシュ キーを渡して `StopLongRunningProcessInWebApiAsync` を呼び出します。 以前のバージョンの MSAL では、L2 キャッシュ削除ポリシーを使用することをお勧めします。 即時削除が必要な場合は、 `sessionKey`に関連付けられている L2 キャッシュ ノードを削除します。

#### Troubleshooting

MSAL.NET を 4.51.0 以降に更新する場合、長時間実行プロセスがすでに開始されており、指定されたキャッシュ キーに対応するトークンがキャッシュ内に存在する状態でトークンを返すことを `InitiateLongRunningProcessInWebApi` に依存していると、`InitiateLongRunningProcessInWebApi` がトークンを返さなくなり、例外をスローする可能性があります。 `InitiateLongRunningProcessInWebApi` は、トークンを取得するためにキャッシュを検査しなくなりました。 `AcquireTokenInLongRunningProcess`を使用して、現在アクティブな実行時間の長いプロセスに引き続きアクセスしてください。 `InitiateLongRunningProcessInWebApi`は、プロセスの開始にのみ使用する必要があります。 これらの変更をすぐに行えず、MSAL 4.54.1 以降に更新する場合は、`InitiateLongRunningProcessInWebApi().WithSearchInCacheForLongRunningProcess()` を使用して `InitiateLongRunningProcessInWebApi` の動作に戻すことができます。

### アプリの登録の変更

- Web API はスコープを公開します。 詳細については、「 [クイック スタート: Web API を公開するようにアプリケーションを構成する (プレビュー)」を](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/quickstart-configure-app-expose-web-apis)参照してください。
- Web API は、受け入れるトークンのバージョンを決定します。 独自の Web API の場合は、 `accessTokenAcceptedVersion` という名前のマニフェスト内のプロパティを ( `1` または `2`に) 変更できます。 バージョン `1`が必要であることがわかっている場合を除き、常に `2`を選択してください。 詳細については、[アプリ マニフェストMicrosoft Entra](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest)参照してください。

### ASP.NET・ASP.NET CoreアプリケーションでのOBOの実用化

ASP.NET Coreの上に Web API を構築する場合は、`Microsoft.Identity.Web`を使用することをお勧めします。 [`Microsoft.Identity.Web`を使用した Web API を](https://github.com/AzureAD/microsoft-identity-web/wiki/web-apis)参照してください。

ASP.NET/ASP.NET Core Web API では、OBO は通常、`OnTokenValidated`の`JwtBearerOptions` イベントで呼び出されます。 トークンはすぐには使用されませんが、この呼び出しはユーザー トークン キャッシュにデータを設定する効果があります。 その後、コントローラーは `AcquireTokenSilent` を呼び出します。これにより、キャッシュが使用され、必要に応じてアクセストークンが更新されるか、新しいリソース用の新しいアクセストークンが取得されますが、いずれの場合も同じユーザーに対するものです。

JWT ベアラー トークンが Web API によって受信および検証されると、次のようになります。

```csharp
public static IServiceCollection AddProtectedApiCallsWebApis(this IServiceCollection services, IConfiguration configuration, IEnumerable<string> scopes)
{
 ...
 services.Configure<JwtBearerOptions>(AzureADDefaults.JwtBearerAuthenticationScheme, options =>
 {
  options.Events.OnTokenValidated = async context =>
  {
   var tokenAcquisition = context.HttpContext.RequestServices.GetRequiredService<ITokenAcquisition>();
   context.Success();

   // Adds the token to the cache, and also handles the incremental consent and claim challenges
   tokenAcquisition.AddAccountToCacheFromJwt(context, scopes);
   await Task.FromResult(0);
  };
 });
 return services;
}
```

また、ダウンストリーム API を呼び出す API コントローラーのアクションのコードを次に示します。

```csharp
private async Task GetTodoList(bool isAppStarting)
{
 ...
 //
 // Get an access token to call the To Do service.
 //
 AuthenticationResult result = null;
 try
 {
  result = await _app.AcquireTokenSilent(Scopes, accounts.FirstOrDefault())
                     .ExecuteAsync()
                     .ConfigureAwait(false);
 }
...

// Once the token has been returned by MSAL, add it to the http authorization header, before making the call to access the To Do list service.
// Make sure to use an access token and not an ID token
_httpClient.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", result.AccessToken);

// Call the To Do list service.
HttpResponseMessage response = await _httpClient.GetAsync(TodoListBaseAddress + "/api/todolist");
...
}
```

GetAccountIdentifier メソッドは、Web API が JWT を受信したユーザーの ID に関連付けられている要求を使用します。

```csharp
public static string GetMsalAccountId(this ClaimsPrincipal claimsPrincipal)
{
 string userObjectId = GetObjectId(claimsPrincipal);
 string tenantId = GetTenantId(claimsPrincipal);

 if (!string.IsNullOrWhiteSpace(userObjectId) && !string.IsNullOrWhiteSpace(tenantId))
 {
  return $"{userObjectId}.{tenantId}";
 }

 return null;
}
```

### プロトコル

On-Behalf-Of プロトコルの詳細については、「[v2.0 と OAuth 2.0 On-Behalf-Of フローのAzure Active Directory](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-oauth2-on-behalf-of-flow)」を参照してください。

### On-Behalf-Of フローを示すサンプル

| Sample | Platform | 説明 |
| --- | --- | --- |
| [active-directory-aspnetcore-webapi-tutorial-v2](https://github.com/Azure-Samples/active-directory-dotnet-native-aspnetcore-v2/tree/master/2.%20Web%20API%20now%20calls%20Microsoft%20Graph) | ASP.NET Core 2.2 Web API、Desktop (WPF) | ASP.NET Core 2.1 Web API 呼び出し Microsoft Graph。それ自体は、Azure AD v2 [Image: On-Behalf-Of フローの図] を使用して WPF アプリケーションから呼び出されます |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/web-apps-apis/workload-identity-federation"} -->
## フェデレーション ワークロード ID を使用してトークンを取得する - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/workload-identity-federation
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: MSAL.NET でフェデレーション ワークロード ID を持つトークンを取得する方法

[ワークロード ID フェデレーション](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation)を使用すると、クライアント アプリケーション シークレットMicrosoft Entra管理することなく、保護されたリソースにアクセスできます。 まず、アプリ登録でワークロード ID フェデレーションを設定します。 アプリケーション コードで、外部プロバイダーからトークンをフェッチし、それを [WithClientAssertion(Func&lt;AssertionRequestOptions,Task&lt;String&gt;&gt;)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.confidentialclientapplicationbuilder.withclientassertion#microsoft-identity-client-confidentialclientapplicationbuilder-withclientassertion%28system-func%28%28microsoft-identity-client-assertionrequestoptions-system-threading-tasks-task%28%28system-string%29%29%29%29%29)に渡す関数を作成します。 トークン要求ごとに、MSAL はこの関数を呼び出して、Microsoft Entra トークンを取得する外部トークンを取得します。 外部プロバイダーへの呼び出しが多くなりすぎないように、この関数がトークンをキャッシュしていることを確認します。

```csharp
using Microsoft.Identity.Client;

var app = ConfidentialClientApplicationBuilder
            .Create(clientId)
            .WithClientAssertion((AssertionRequestOptions options) => FetchExternalTokenAsync())
            .WithCacheOptions(CacheOptions.EnableSharedCacheOptions) // for more cache options see https://learn.microsoft.com/entra/msal/dotnet/how-to/token-cache-serialization?tabs=msal
            .Build()

var result = await app.AcquireTokenForClient(scope).ExecuteAsync();

public async Task<string> FetchExternalTokenAsync() 
{
    // Logic to get token from cache or other sources, like GitHub, Kubernetes, etc.
    // Caching is the responsability of the implementer.
    return token;
}

```

[Microsoft。Identity.Web.Certificateless](https://www.nuget.org/packages/Microsoft.Identity.Web.Certificateless) パッケージには、フェデレーション トークンを取得するためのヘルパー メソッドがいくつか用意されています。 マネージド ID フェデレーションには [ManagedIdentityClientAssertion](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.web.managedidentityclientassertion) を使用します。

```csharp
using Microsoft.Identity.Web;

// Reuse this instance so that the assertion is cached and only refreshed once it expires.
ManagedIdentityClientAssertion managedIdentityClientAssertion = new ManagedIdentityClientAssertion(userAssignedId);

public async Task<string> FetchExternalTokenAsync() 
{
    return await managedIdentityClientAssertion.GetSignedAssertion(default);
}

```

Azure Kubernetes クラスターでフェデレーション トークンを取得するには、[AzureIdentityForKubernetesClientAssertion](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.web.azureidentityforkubernetesclientassertion)を使用します。

```csharp
using Microsoft.Identity.Web;

// Reuse this instance so that the assertion is cached and only refreshed once it expires.
AzureIdentityForKubernetesClientAssertion aksClientAssertion = new AzureIdentityForKubernetesClientAssertion();

public async Task<string> FetchExternalTokenAsync() 
{
    return await aksClientAssertion.GetSignedAssertion(default);
}

```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/android-ios-emulator"} -->
## Android および iOS エミュレーターでの MSAL.NET の使用 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/android-ios-emulator
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: Android および iOS デバイス エミュレーターで MSAL.NET を使用する方法。

Note

MSAL .NET チームは、エミュレーターとデバイスでの認証には微妙な違いがあるため、可能な限り Android または iOS デバイスでテストすることをお勧めします。

これらの問題の一部は、 [ネイティブの Android MSAL ライブラリ Wiki](https://github.com/AzureAD/microsoft-authentication-library-for-android/wiki/Android-Emulator-with-MSAL) に記載されています。

iOS の場合、エミュレーターまたはデバイスを使用する場合、SSO とキーチェーンへのアクセスには違いがあります。 問題を開くかバグを報告する前に、問題がデバイスでレプリケートされるかどうかを確認してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/backup-authentication-system"} -->
## バックアップ認証システム - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/backup-authentication-system
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: Microsoft Entra バックアップ認証システムでは、Evolved Security Token Service (ESTS) によって処理される資格情報のキャッシュを有効にして、Microsoft Entra認証サービスの停止中の回復性を提供します。

Microsoft Entra IDには、Microsoft Entra認証サービスの停止時の回復性を提供するために資格情報のキャッシュを有効にするバックアップ認証システムがあります。

バックアップ認証システムからのトークン取得を高速化するために、MSAL はヘッダーの形式でルーティング ヒントを提供するか、ESTS に送信される認証要求に追加のクエリ パラメーターを提供します。 MSAL はほとんどの認証シナリオでこれを試みますが、ユーザー データがないために MSAL でこのヒントを提供できない場合があります。 この問題は、 [WithCcsRoutingHint(String)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokenbyauthorizationcodeparameterbuilder.withccsroutinghint#microsoft-identity-client-acquiretokenbyauthorizationcodeparameterbuilder-withccsroutinghint%28system-string%29) と [WithCcsRoutingHint(String, String)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokenbyauthorizationcodeparameterbuilder.withccsroutinghint#microsoft-identity-client-acquiretokenbyauthorizationcodeparameterbuilder-withccsroutinghint%28system-string-system-string%29)を使用することで解決できます。

[WithCcsRoutingHint(String, String)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokenbyauthorizationcodeparameterbuilder.withccsroutinghint#microsoft-identity-client-acquiretokenbyauthorizationcodeparameterbuilder-withccsroutinghint%28system-string-system-string%29)の使用方法の例を次に示します。

```csharp
ConfidentialClientApplication app = ConfidentialClientApplicationBuilder.Create(TestConstants.ClientId)
                                                  .WithClientSecret(clientSecret)
                                                  .Build();
// When creating an authorization Uri
var uri = await app
               .GetAuthorizationRequestUrl(TestConstants.s_scope)
               .WithCcsRoutingHint(userObjectIdentifier, tenantIdentifier)
               .ExecuteAsync();

// When Acquiring a Token
app.AcquireTokenByAuthorizationCode(scopes, authCode)
               .WithCcsRoutingHint(userObjectIdentifier, tenantIdentifier)
               .ExecuteAsync()
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/clearing-token-cache"} -->
## トークン キャッシュのクリーニング - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/clearing-token-cache
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: MSAL.NET によって使用されるトークン キャッシュをクリアする方法

トークン キャッシュをクリアするには、キャッシュからアカウントを削除します。 これにより、ブラウザーにあるセッション Cookie は削除されません。

次の例では、 [IClientApplicationBase](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.iclientapplicationbase)のインスタンスを使用しています。

```csharp
// Clear the cache
var accounts = await app.GetAccountsAsync();
while (accounts.Any())
{
   await app.RemoveAsync(accounts.First());
   accounts = await app.GetAccountsAsync();
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/client-and-server-throttling"} -->
## MSAL.NET におけるクライアントとサーバーのスロットリングについて理解する - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/client-and-server-throttling
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: 認証 API の呼び出し頻度が高すぎると、Microsoft Entra ID はアプリケーションをスロットリングします。 これを MSAL.NET で処理する方法について説明します。

### サーバーの調整

Microsoft Entra ID では、認証 API の呼び出し頻度が高すぎる場合、アプリケーションにスロットル制限がかかります。 ほとんどの場合、これは、次の理由でトークン キャッシュが使用されない場合に発生します。

1. トークン キャッシュが正しく設定されていません ( [「トークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-token-cache-serialization)」を参照)。
2. [AcquireTokenSilent(IEnumerable&lt;String&gt;, String)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.clientapplicationbase.acquiretokensilent#microsoft-identity-client-clientapplicationbase-acquiretokensilent%28system-collections-generic-ienumerable%28%28system-string%29%29-system-string%29)を呼び出す前に[AcquireTokenInteractive(IEnumerable&lt;String&gt;)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.ipublicclientapplication.acquiretokeninteractive#microsoft-identity-client-ipublicclientapplication-acquiretokeninteractive%28system-collections-generic-ienumerable%28%28system-string%29%29%29)を呼び出さない、[AcquireTokenByUsernamePassword(IEnumerable&lt;String&gt;, String, String)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.ipublicclientapplication.acquiretokenbyusernamepassword#microsoft-identity-client-ipublicclientapplication-acquiretokenbyusernamepassword%28system-collections-generic-ienumerable%28%28system-string%29%29-system-string-system-string%29)。
3. `User.ReadBasic.All`など、Microsoft アカウント (MSA) ユーザーに適用されないスコープを要求すると、キャッシュ ミスが発生します。

サーバーは、次の 2 つの方法で調整を通知します。

- `client_credentials`許可の場合([AcquireTokenForClient(IEnumerable&lt;String&gt;)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.confidentialclientapplication.acquiretokenforclient#microsoft-identity-client-confidentialclientapplication-acquiretokenforclient%28system-collections-generic-ienumerable%28%28system-string%29%29%29))、Microsoft Entra IDは`429 Too Many Requests`ヘッダーを含む`Retry-After: 60`で返信します。
- ユーザー向け呼び出しの場合、Microsoft Entra ID は、エラー コードが `invalid_grant` で、メッセージが `AADSTS50196: The server terminated an operation because it encountered a loop while processing a request` に設定された [MsalUiRequiredException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msaluirequiredexception) を返すメッセージを送信します。

### クライアント スロットリング

MSAL は、アプリケーションがMicrosoft Entra IDを繰り返し呼び出してはならない特定の条件を検出します。 呼び出しが行われた場合、[MsalThrottledServiceException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalthrottledserviceexception) または [MsalThrottledUiRequiredException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalthrottleduirequiredexception) 例外がスローされます。 これらは [MsalServiceException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalserviceexception)のサブタイプであるため、この動作では重大な変更は発生しません。

MSAL がクライアント側のスロットリングを適用しなかったとしても、Microsoft Entra ID はいずれにせよエラーを返すため、アプリケーションはトークンを取得できません。

### スロットリングされる条件

#### Microsoft Entra IDは、アプリケーションにバックオフを指示しています

サーバーで問題が発生している場合、またはアプリケーションがトークンを要求する頻度が高すぎる場合、Microsoft Entra IDは `HTTP 429 (Too Many Requests)` と `Retry-After` ヘッダーで応答`Retry-After X seconds`。 アプリケーションには、[MsalServiceException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalserviceexception)を含むが表示されます。 スロットリング状態は X 秒間維持されます。 この制限は、すべてのフローに影響します。

最も可能性の高い原因は、トークンキャッシュを設定していないということです。 詳細については、[MSAL.NET でのトークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-token-cache-serialization)に関するページを参照してください。

#### 問題が発生しているMicrosoft Entra ID

Microsoft Entra IDで問題が発生している場合は、`HTTP 5xx` ヘッダーのない`Retry-After`エラー コードで応答する可能性があります。 スロットリング状態は 1 分間保持されます。 パブリック クライアント フローにのみ影響します。

#### アプリケーションが無視している `MsalUiRequiredException`

MSAL は、認証をサイレント モードで解決できず、エンド ユーザーがブラウザーを使用する必要がある場合に、 [MsalUiRequiredException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msaluirequiredexception) をスローします。 これは、テナント管理者が Multi-Factor Authentication (MFA) を導入したとき、またはユーザーのパスワードの有効期限が切れたときによくあることです。 サイレント認証の再試行は成功できません。 スロットリング状態は 2 分間維持されます。 [AcquireTokenSilent(IEnumerable&lt;String&gt;, String)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.clientapplicationbase.acquiretokensilent#microsoft-identity-client-clientapplicationbase-acquiretokensilent%28system-collections-generic-ienumerable%28%28system-string%29%29-system-string%29) フローにのみ影響します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/client-credential-multi-tenant"} -->
## マルチテナント サービスでのクライアント資格情報フローに MSAL.NET を使用する - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/client-credential-multi-tenant
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: MSAL.NET、トークン キャッシュ、Microsoft.Identity.Web for ASP.NET Core を使用した Microsoft の高度なクライアント資格情報によるマルチテナントを学ぶ

### 決定ポイント - Microsoft。Identity.Web または Microsoft。Identity.Client (MSAL)?

ASP.NET Coreを使用する場合は、トークン取得よりも高いレベルの API を提供し、より優れた既定値を持つ[`Microsoft.Identity.Web`](https://github.com/AzureAD/microsoft-identity-web/wiki)を採用することをお勧めします。 [「MSAL.NET は自分に適しているか」を参照してください。](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/choosing-msal-dotnet)

### 決定ポイント - トークン キャッシュ

MSAL は、取得した各トークンに合わせて拡張されるトークン キャッシュを保持します。 MSAL はトークンの有効期間をスマートな方法で管理するため、キャッシュを使用する必要があります。 インメモリ キャッシュまたは分散キャッシュを使用するオプションがあります。

[MSAL.NET Token Cache Serialization](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-token-cache-serialization) を参照してください。

すべてのユーザー フローに永続化された分散キャッシュ (Redis、Cosmos など) を使用することをお勧めします。

また、マルチテナント サービス 2 サービス アプリでは、永続化された分散キャッシュを使用することをお勧めします。 ただし、限られた数のテナントに対してサービスにアプリ トークンが必要であることがわかっている場合は、削除を伴うメモリ キャッシュの使用から解放される可能性があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/custom-authority-aliases"} -->
## カスタム認証エイリアス - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/custom-authority-aliases
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: MSAL.NET アプリケーションでカスタム権限エイリアスを使用する方法。

### インスタンス検出とは

トークンを取得する前に、MSAL はMicrosoft Entra機関検出エンドポイントに対してネットワーク呼び出しを行います。

```text
https://login.microsoftonline.com/common/discovery/instance?api-version=1.1&authorization_endpoint=https%3A%2F%2Flogin.microsoftonline.com%2Fcommon%2Foauth2%2Fv2.0%2Fauthorize
```

返される情報は、次の用途に使用されます。

- 各クラウド (Azure パブリック、ドイツ クラウド、China Cloud など) のエイリアスの一覧を確認します。 セット内の権限に対して発行されたトークンは、セット内の他のすべての権限に対して有効です。
- Microsoft Entra IDとの通信に preferred\_network エイリアスを使用する
- preferred\_cache エイリアスを使用してトークンをキャッシュに格納する
- 権限の検証レベルを指定します。存在しない機関が使用されている場合、Microsoft Entra IDは "invalid\_instance" エラーを返します。

    ```json
    {
        "error":"invalid_instance",
        "error_description":"AADSTS50049: Unknown or invalid instance.\r\nTrace ID: 3adb62d2-11d5-4bb0-acac-7d97451c0000\r\nCorrelation ID: ce374500-8786-4739-ac5b-9a57f9cc0140\r\nTimestamp: 2023-03-27 16:25:19Z",
        "error_codes":[
            50049
        ],
        "timestamp":"2023-03-27 16:25:19Z",
        "trace_id":"0000aaaa-11bb-cccc-dd22-eeeeee333333",
        "correlation_id":"aaaa0000-bb11-2222-33cc-444444dddddd",
        "error_uri":"https://login.microsoftonline.com/error?code=50049"
    }
    ```

### インスタンスの検証

検証は、権限を動的に取得する場合に重要です。たとえば、保護 API を呼び出すと、トークンを生成できる機関を指すヘッダーを含めることができる 401 Unauthorized HTTP 応答が返されます。 API がハッキングされた場合、Microsoft Entra IDに属していない機関をアドバタイズし、ユーザー資格情報を盗む可能性があります。

### インスタンス検出の無効化

MSAL ライブラリでは、このデータに対してさまざまなキャッシュ メカニズムが既に採用されています。 一部の PublicClientApplication シナリオでパフォーマンスをさらに最適化するために、インスタンス探索ネットワーク呼び出しをバイパスすることもできますが、これは上記のセキュリティ リスクを理解している場合にのみ行う必要があります。 独自のインスタンス メタデータを指定した場合、MSAL は常にそれを使用し、この種のデータのネットワークには移動しません。

```csharp
var app = PublicClientApplicationBuilder
    .Create(MsalTestConstants.ClientId)
     // or a Guid instead of common
    .WithAuthority(new Uri("https://login.microsoftonline.com/common/"), false) // or a tenanted authority ending in a GUID
    .WithInstanceDicoveryMetadata(instanceMetadataJson) // a json string similar to https://aka.ms/aad-instance-discovery
    .Build();
```

Note

検証はカスタム探索メタデータに対してのみ行われるため、 `validateAuthority` フラグを `false` に設定する必要があります。

#### インスタンス メタデータの例

権限が `https://login.contoso.net` であると仮定すると、有効なインスタンス検出が以下に示されます。 この値を文字列に渡す必要があります。

```json
{
    "api-version": "1.1",
    "metadata": [
        {
            "preferred_network": "login.contoso.net",
            "preferred_cache": "login.contoso.net",
            "aliases": [
                "login.contoso.net"
            ]
        }
    ]
}
```

### 関連する MsalError 定数

この機能を使用するときに取得できる `MsalError` は次のとおりです。

| エラー | 説明 |
| --- | --- |
| `InvalidUserInstanceMetadata ` | 独自のカスタム インスタンス検出メタデータを構成しましたが、指定した JSON は無効なようです。 有効な `ValidateAuthorityOrCustomMetadata`が必要です。 または、独自のインスタンス メタデータを構成したが、機関の検証を要求している可能性があります。 検証機関フラグを false に設定する必要があります。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/exceptions"} -->
## MSAL.NET の例外 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: この包括的なガイドで、MSAL.NET での例外処理を習得しましょう。 さまざまな種類の例外、一般的な問題、再試行ポリシーを実装する方法について説明します。

MSAL.NET の例外は、アプリ開発者がトラブルシューティングを行い、エンド ユーザーに表示しないことを目的としています。 例外メッセージはローカライズされません。

### さまざまな種類の例外

[Image: 画像]

| 例外 | 説明 |
| --- | --- |
| `MsalException` | MSALの例外の基底クラス。 |
| `MsalClientException` | 不完全な構成など、ライブラリ自体で発生するエラー。 |
| `MsalServiceException` | トークン プロバイダー (Microsoft Entra ID) によって送信されるエラーを表します。 [Microsoft Entraエラーを](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/reference-aadsts-error-codes#handling-error-codes-in-your-application)参照してください。 サービスに問題があることを示すサービス利用不可エラー (HTTP 500 など) にエラー コードがある `service_not_available` |
| `MsalUiRequiredException` | ユーザーが対話形式でログインする必要があることを示す特殊なMicrosoft Entra エラーです。 |

MSAL では、それ以外の例外はキャッチされません。 ネットワークの問題やキャンセルなどは、アプリケーションにバブルアップされます。

MSAL は、ライブラリ内で問題が発生した場合（不適切な構成など）には `MsalClientException` をスローし、サービス側またはブローカー内で問題が発生した場合（シークレットの有効期限が切れた場合など）には [`MsalServiceException`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalserviceexception) をスローします。

#### 一般的な例外

1. ユーザーによる認証の取り消し (パブリック クライアントのみ)

`AcquireTokenInteractive`を呼び出すと、ブラウザーまたはブローカーが呼び出され、ユーザーの操作が処理されます。 ユーザーがこのプロセスを閉じた場合、またはブラウザーの [戻る] ボタンをクリックした場合、MSAL はエラー コード [`MsalClientException`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalclientexception) (`authentication_canceled`) を含む`MsalError.AuthenticationCanceledError`を生成します。

Android では、 [タブ付きのブラウザー](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-system-browser-android-considerations) が使用できない場合にも、この例外が発生する可能性があります。

1. HTTP 例外

開発者は、MSAL を呼び出すときに独自の再試行ポリシーを実装する必要があります。 MSAL は、Microsoft Entra サービスに対して HTTP 呼び出しを行います。ネットワークがダウンしたり、サーバーが過負荷になったりするなど、エラーが発生することがあります。 HTTP 5xx 状態コードの応答は 1 回再試行されます。

#### 例外の種類

例外を処理する場合は、例外の種類自体と `ErrorCode` メンバーを使用して例外を区別できます。 `ErrorCode`の値は、[`MsalError`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalerror)の定数です。

また、 [`MsalClientException`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalclientexception)、 [`MsalServiceException`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalserviceexception)、 [`MsalUiRequiredException`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msaluirequiredexception)のフィールドを見ることもできます。

[`MsalServiceException`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalserviceexception)の場合、エラーには[認証エラー コードと承認エラー コード](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/reference-aadsts-error-codes)で確認できるコードが含まれている場合があります。

##### MsalUiRequiredException

"UI Required" は、`MsalServiceException`という名前の`MsalUiRequiredException`の特殊化です。 つまり、トークンを取得する非対話型のメソッド (AcquireTokenSilent など) を使用しようとしましたが、MSAL ではサイレントモードでは実行できませんでした。 次の理由が考えられます。

- サインインする必要がある
- 同意する必要がある
- 多要素認証エクスペリエンスを使用する必要があります。

修復するには、パブリック クライアントでの `AcquireTokenInteractive` 、Web サイトへのログインへのユーザーのリダイレクト、Web API での 401 による応答などをユーザーに求める AcquireToken\* メソッドを呼び出します。

#### 継続的アクセス評価

[アプリケーションで継続的アクセス評価が有効な API を使用する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-resilience-continuous-access-evaluation)を参照してください。

#### MSAL.NET での要求チャレンジ例外の処理

場合によっては、Microsoft Entra テナント管理者が条件付きアクセス ポリシーを有効にした場合、アプリケーションで要求チャレンジの例外を処理する必要があります。 これは、`Claims` プロパティが空にならない `MsalServiceException` として表示されます。 たとえば、条件付きアクセス ポリシーでマネージド デバイス (Intune) を使用する場合、エラーは `AADSTS53000: Your device is required to be managed to access this resource` のようなものになります。

要求チャレンジを処理するには、 [WithClaims(String)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.abstractacquiretokenparameterbuilder-1.withclaims#microsoft-identity-client-abstractacquiretokenparameterbuilder-1-withclaims%28system-string%29) メソッドを使用する必要があります。

#### 再試行ポリシー

[再試行ポリシーを](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/retry-policy)参照してください
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/exceptions/broker"} -->
## ブローカー アプリケーションのトラブルシューティング - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/broker
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: トラブルシューティング ガイドを使用した Android でのマスター ブローカー認証。 リダイレクト URI、ブローカーのバージョン、優先順位、およびログの取得について説明します。

### Android ブローカー認証のヒント

Android でブローカー認証を実装するときの問題を回避するためのヒントをいくつか次に示します。

- **リダイレクト URI** - [Azure ポータル](https://portal.azure.com/)でアプリケーション登録にリダイレクト URI を追加します。 リダイレクト URI が見つからないか正しくないかは、開発者が遭遇する一般的な問題です。
- **ブローカーのバージョン** - ブローカー アプリの最低限必要なバージョンをインストールします。 これら 2 つのアプリのいずれかを Android でのブローカー認証に使用できます。

    - [InTune ポータル サイト](https://play.google.com/store/apps/details?id=com.microsoft.windowsintune.companyportal) (バージョン 5.0.4689.0 以降)
    - [Microsoft Authenticator](https://play.google.com/store/apps/details?id=com.azure.authenticator) (バージョン 6.2001.0140 以降)。
- **ブローカーの優先順位** - MSAL は、複数のブローカーがインストールされている場合に、デバイスに *インストールされた最初* のブローカーと通信します。

    例: 最初にMicrosoft Authenticatorをインストールしてから Intune ポータル サイトをインストールした場合、ブローカー認証はMicrosoft Authenticator*でのみ*行われます。
- **ログ** - ブローカー認証で問題が発生した場合は、ブローカーのログを表示すると、原因の診断に役立つ可能性があります。

    - Microsoft Authenticator ログの取得:

        1. アプリの右上隅にあるメニュー ボタンを選択します。
        2. **フィードバックの送信**&gt;**問題が発生した場合** を選択します。
        3. 説明を追加するには、[ **何をしようとしているか]** のオプションのいずれかを選択します。
        4. その後、画面の右上にある矢印をクリックしてログを送信できます。
        5. ログを送信すると、 **インシデント ID** を含むポップアップが表示されます。 サポートを要求するときは、このインシデント ID を指定してください。
    - Intune ポータル サイト ログの取得:

        1. アプリの左上隅にあるメニュー ボタンを選択する
        2. **[ヘルプ**&gt;**Email サポート**] を選択する
        3. [ **ログのみをアップロード** ] を選択してログを送信します。
        4. ログを送信すると、 **インシデント ID** を含むポップアップが表示されます。 サポートを要求するときは、このインシデント ID を指定してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/exceptions/confidential-client-troubleshoot"} -->
## 機密クライアント アプリケーションのトラブルシューティング - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/confidential-client-troubleshoot
- Service: msal / msal-dotnet
- Article date: 2026-05-18
- Summary: 調整、ソケット不足、OBO エラー、クライアント資格情報エラー、トークン キャッシュ ミスなど、MSAL.NET を使用して機密クライアント アプリの一般的なエラーを診断して解決します。

このガイドでは、機密クライアント アプリケーション (Web アプリ、Web API、デーモン/サービス アプリ) に固有の一般的な問題について説明します。 一般的な例外処理については、「[MSAL.NET でのエラーと例外の処理](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/msal-error-handling)」を参照してください。

Important

この記事の `AADSTS` エラー コードは、問題を診断している人間向けのリファレンスです。 エラー メッセージから抽出したり、プログラムで分岐したりしないでください。これらは安定した API ではなく、変更される可能性があります。 動的処理の場合は、代わりに MSAL 例外 *の種類* で分岐します。 たとえば、`MsalUiRequiredException` を捕捉してユーザーに再度プロンプトを表示し、クレーム チャレンジをクライアントに送り返すには `MsalServiceException.Claims` を確認します。

### スロットリング (HTTP 429 および AADSTS50196)

#### Symptoms

- `MsalServiceException` HTTP ステータス コード 429 で
- エラー コード `AADSTS50196` — "サーバーがループを発生したために操作を終了しました"
- `MsalThrottledServiceException` (MSAL 4.47.0 以降)

#### 一般的な原因

- **トークン キャッシュが見つからないか、正しく構成されていません**。 すべての呼び出しは、キャッシュからトークンを提供するのではなく、Microsoft Entra IDに送られます。
- **厳密なループでトークンを要求する** — たとえば、最初にキャッシュを確認せずに、すべての受信要求で `AcquireTokenForClient` を呼び出します。
- **異なるスコープ/リソースが多すぎます** 。 一意のスコープごとに、キャッシュされた個別のトークンが生成されます。

#### Resolution

- **常に最初 `AcquireTokenSilent` (** 委任されたフローの場合) を呼び出すか、トークン キャッシュが (クライアント資格情報用に) 構成されていることを確認します。 MSAL の組み込みキャッシュは、適切に構成されると、重複除去を自動的に処理します。
- **トークン キャッシュが動作していることを確認します。**`AuthenticationResult.AuthenticationResultMetadata.TokenSource`を確認します。`Cache`ではなく常に`IdentityProvider`が表示される場合は、キャッシュが使用されていません。

    ```csharp
    var result = await app.AcquireTokenForClient(scopes).ExecuteAsync();
    
    if (result.AuthenticationResultMetadata.TokenSource == TokenSource.IdentityProvider)
    {
        // This should NOT happen on every call - investigate cache configuration
        logger.LogWarning("Token was fetched from IdP, not cache. CacheRefreshReason: {Reason}",
            result.AuthenticationResultMetadata.CacheRefreshReason);
    }
    ```
- **`Retry-After` ヘッダーを尊重してください。** 429 を受け取ると、 `MsalServiceException.Headers` プロパティには `RetryAfter` 値が含まれます。 デルタまたは絶対日付として表すことができるので、両方を処理し、負の遅延を `Task.Delay`に渡すことはありません。

    ```csharp
    catch (MsalServiceException ex) when (ex.StatusCode == 429)
    {
        TimeSpan delay = TimeSpan.FromSeconds(60);
    
        var retryAfter = ex.Headers?.RetryAfter;
        if (retryAfter?.Delta is TimeSpan delta)
        {
            delay = delta;
        }
        else if (retryAfter?.Date is DateTimeOffset date)
        {
            delay = date - DateTimeOffset.UtcNow;
        }
    
        // Clock skew between the server and the client can produce a negative value.
        if (delay < TimeSpan.Zero)
        {
            delay = TimeSpan.Zero;
        }
    
        await Task.Delay(delay);
    }
    ```
- **セッションごとに 1 つの `ConfidentialClientApplication` インスタンスを使用** します (要求ごとではありません)。 ガイダンスについては [、高可用性](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/high-availability) を参照してください。

Tip

MSAL 4.47.0 以降では、スロットリングされた応答の場合は `MsalThrottledServiceException`（`MsalServiceException` のサブクラス）がスローされるため、スロットリングを他のサービス エラーと区別しやすくなります。

### ネットワークの不安定性とソケットの例外

#### Symptoms

- トークンの取得時に `HttpRequestException`（多くの場合、内部 `SocketException` を伴う）を使用します。
- Microsoft Entra トークン エンドポイントの呼び出し中の断続的なエラー。
- MSAL 操作の待機時間が長い (`AuthenticationResult.AuthenticationResultMetadata.DurationTotalInMs`)。

#### 一般的な原因

- **トークンをキャッシュしない** — キャッシュがないと、すべてのトークン要求でMicrosoft Entra IDへのネットワーク呼び出しが行われます。 これにより、一時的なネットワーク障害やソケットの枯渇にさらされる可能性が高くなります。
- **サービスの停止またはローカル ネットワークの問題** - トークン エンドポイントが一時的に使用できないか、ローカル ネットワークが不安定である可能性があります。
- **カスタム `HttpClient` MSAL の既定値をオーバーライドします** 。MSAL の組み込み `HttpClient` はスケーラブルに設計されています。 オーバーライドした場合、接続管理はユーザーの責任になります。
- **ファイアウォールまたはネットワーク規則** — ネットワークまたはファイアウォール規則に対する最近の更新により、 `login.microsoftonline.com`への送信トラフィックがブロックされている可能性があります。

#### ソリューション

トークン キャッシュを有効にして確認します。 キャッシュを使用すると、送信ネットワーク呼び出しの数が減り、アプリが一時的なネットワーク障害から保護されます。

トークンがキャッシュから提供されていることを確認するには、認証結果の `TokenSource` プロパティを確認します。

```csharp
var result = await app.AcquireTokenForClient(scopes).ExecuteAsync();

if (result.AuthenticationResultMetadata.TokenSource == TokenSource.IdentityProvider)
{
    // Token was fetched from the network - this should only happen on the first call or after expiry
    logger.LogWarning("Token not served from cache. CacheRefreshReason: {Reason}",
        result.AuthenticationResultMetadata.CacheRefreshReason);
}
```

`TokenSource``Cache`ではなく一貫して`IdentityProvider`を返す場合、トークン キャッシュは正しく構成されていません。

Web アプリと Web API の場合は、分散トークン キャッシュ (Redis など) を使用します。 構成ガイダンスについては [、高可用性](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/high-availability#use-the-token-cache) を参照してください。

カスタム `HttpClient`を指定する場合は、有効期間が長く、接続プールが適切に管理されていることを確認します。 [独自の HttpClient の提供を](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/httpclient)参照してください。

`login.microsoftonline.com` およびリージョン エンドポイントへの接続の問題を引き起こした可能性のある、ネットワークまたはファイアウォールの規則に最近の変更がないか確認します。

### On-Behalf-Of (OBO) の失敗

#### MsalUiRequiredException - 条件付きアクセス、MFA、または増分同意

##### Symptoms

`AcquireTokenOnBehalfOf` は多くの場合、 `interaction_required` を伴い、`Claims` プロパティが設定された状態で `MsalUiRequiredException` をスローします。 これは通常、ダウンストリーム API (Microsoft Graph など) が、Web API 自体が適用しない条件付きアクセス ポリシー (MFA 要件など) によって保護されている場合に発生します。

##### 一般的な原因

- ダウンストリーム リソースの条件付きアクセス ポリシーでは、受信トークンが満たしていないユーザー操作 (MFA、準拠デバイス、サインイン頻度) が必要です。
- ユーザーがまだダウンストリーム スコープに同意していません (増分同意)。

##### Resolution

Web API は独自にポリシーを満たすことはできません。ユーザーの操作を所有するクライアントのみがポリシーを満たすことができます。 API は要求チャレンジをクライアントに返す必要があり、クライアントはポリシーを満たすトークンを再取得する必要があります。

1. `MsalUiRequiredException`をキャッチし、`ex.Claims`からの要求を含む`WWW-Authenticate` ヘッダーで`HTTP 401`を返します。

    ```csharp
    catch (MsalUiRequiredException ex) when (ex.Claims != null)
    {
        // ASP.NET Core web API
        httpResponse.StatusCode = (int)HttpStatusCode.Unauthorized; // HTTP 401
        httpResponse.Headers[HeaderNames.WWWAuthenticate] =
            $"Bearer realm=\"\", authorization_uri=\"https://login.microsoftonline.com/common/oauth2/authorize\", error=\"insufficient_claims\", claims=\"{Convert.ToBase64String(Encoding.UTF8.GetBytes(ex.Claims))}\"";
    }
    ```
2. クライアントで、ヘッダーを解析し、要求を次のトークン要求に渡します。

    ```csharp
    WwwAuthenticateParameters wwwParams =
        WwwAuthenticateParameters.CreateFromAuthenticationHeaders(response.Headers, "Bearer");
    
    // Desktop or mobile app
    await app.AcquireTokenInteractive(scopes)
             .WithClaims(wwwParams.Claims)
             .ExecuteAsync();
    ```

例外の種類 (`MsalUiRequiredException`) で分岐し、 `Claims` が設定されているかどうかを確認します。メッセージから `AADSTS` コードを解析しないでください。

エラー シナリオやクライアント側の処理など、完全なパターンについては、「 [多要素認証 (MFA)、条件付きアクセス、増分同意の処理](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/on-behalf-of-flow#handling-multi-factor-auth-mfa-conditional-access-and-incremental-consent)」を参照してください。

#### AADSTS50013 — アサーション検証に失敗しました

##### Symptoms

`MsalServiceException` エラー コード `AADSTS50013: Assertion failed signature validation` または `invalid_grant`。

##### 一般的な原因

- 受信トークン (ユーザー アサーション) の有効期限が切れています。
- トークンは、予想とは異なる機関によって発行されました。
- トークンの対象ユーザー (`aud`) が、アプリのクライアント ID またはアプリ ID URI と一致しません。

##### Resolution

- `.WithUserAssertion()`に渡されるアクセス トークンが新しく、API 用であることを確認します。
- API のアプリの登録に正しい `accessTokenAcceptedVersion` があることを確認します (v2 トークンは対象ユーザーとして `api://{clientId}` 使用します)。
- `ConfidentialClientApplication`の機関がトークン発行者のテナントと一致することを確認します。

#### AADSTS65001 — OBO に対して同意が付与されない

##### Symptoms

`AcquireTokenOnBehalfOf` を呼び出した際、`MsalServiceException` でエラーコード `AADSTS65001` が発生しました。

##### 一般的な原因

ダウンストリーム API スコープは、ユーザーまたは管理者が同意していません。OBO では、ユーザー (またはテナント管理者) がダウンストリームのアクセス許可に対する同意を付与している必要があります。

##### Resolution

- アプリの登録の **API permissions** で、必要なダウンストリーム API のアクセス許可が構成されていることを確認します。
- マルチテナント アプリの場合は、管理者の同意 URL を使用して管理者の同意をトリガーします。

    ```
    https://login.microsoftonline.com/{tenant}/adminconsent?client_id={clientId}&redirect_uri={redirectUri}
    ```

    `redirect_uri`値は必須であり、URL エンコードされ、**認証**でアプリ登録に登録されている応答 URL のいずれかと完全に一致する必要があります。 Microsoft Entra IDは、同意が許可または拒否された後に管理者をこの URL にリダイレクトするため、値が見つからないか、登録済みの応答 URL と一致しない場合、要求は失敗します。
- シングルテナント アプリの場合は、テナント管理者にMicrosoft Entra 管理センターを通じて同意を与えます。

#### OBO トークンが大きすぎます

##### Symptoms

ダウンストリーム API からの HTTP 431（リクエスト ヘッダー フィールドが大きすぎます）、または `MsalServiceException` は、トークン応答が大きすぎることを示します。

##### 一般的な原因

グループ メンバーシップが多いユーザーは、大きなトークンを生成します。 OBO がこれらを交換すると、結果のトークンが HTTP ヘッダー サイズの制限を超える可能性があります。

##### Resolution

- **フィルターでグループ要求**を使用するようにアプリの登録を構成するか、要求を`hasgroups` / `_claim_names`に切り替えます (すべてのグループを埋め込む代わりに Graph URL を返します)。
- 実行時にグループ メンバーシップを照会するには、[groups overage pattern](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-token-claims-reference#groups-overage-claim) を使用して Microsoft Graph にクエリを実行します。

### クライアント資格情報エラー

Important

クライアント シークレットは、最も安全性の低い形式のクライアント資格情報です。 漏えいしやすく、期限切れになり、ローテーションは手動で障害を招きやすいプロセスです。 次の順序で優先します。

- ** アプリが Azure 上で実行される場合の[マネージド ID](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/managed-identity)** — 保存やローテーションが必要な資格情報が一切ありません。
- アプリがAzureの外部 (GitHub Actionsや別のクラウドなど) で実行されている場合の**[フェデレーション ID 資格情報 (ワークロード ID フェデレーション](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation)**)。
- **[証明書または署名付きクライアント アサーション](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/confidential-client-assertions)**。上記のいずれも利用できない場合。

ローカル開発またはプロトタイプ作成にのみクライアント シークレットを使用し、ソース管理にチェックインしないでください。

#### AADSTS7000215 — クライアント シークレットが無効です

##### Symptoms

`MsalServiceException` と `AADSTS7000215: Invalid client secret provided`。

##### Resolution

- (シークレット ID ではなく) シークレット値が構成で使用されていることを確認します。
- [**証明書と**シークレット] のMicrosoft Entra 管理センターでシークレットの有効期限が切れていないことを確認します。
- 構成/Key Vaultからシークレットを読み込むときに、末尾に空白やエンコードの問題がないことを確認します。
- 前のメモで説明したように、シークレットを完全に削除することを検討してください。

#### AADSTS700024 — クライアント アサーションの有効期限が切れています

##### Symptoms

`MsalServiceException` と `AADSTS700024: Client assertion is not within its valid time range`。

##### 一般的な原因

クライアント アサーションの署名に使用される証明書の有効期限が切れているか、システム クロックが大幅に歪んでいる。

##### Resolution

- 証明書の有効期限を確認する: 証明書の `NotAfter` 日が経過していないことを確認します。
- システム クロック同期 (NTP) を確認します。
- 証明書にAzure Key Vaultを使用する場合は、アプリが最新バージョンを読み込んでいます。 ベスト プラクティスについては、「 [証明書のローテーション](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/high-availability#certificate-rotation) 」を参照してください。

#### AADSTS700016 — アプリケーションが見つかりません

##### Symptoms

`MsalServiceException` と `AADSTS700016: Application with identifier '{clientId}' was not found in the directory '{tenant}'`。

##### 一般的な原因

- 構成の `ClientId` が正しくありません。
- アプリの登録は、使用されている機関とは異なるテナントに存在します。
- マルチテナント アプリの場合、アプリはターゲット テナントで同意されていません。

##### Resolution

- 構成で `ClientId` と `TenantId` を再確認します。
- `/common`または`/organizations`機関を使用している場合は、アプリがマルチテナント アクセスをサポートしていることを確認します。

### トークンキャッシュミスの診断

#### Symptoms

`AuthenticationResult.AuthenticationResultMetadata.TokenSource`同じパラメーターを使用して繰り返し呼び出しても、`Cache`ではなく`IdentityProvider`が常に返されます。

#### 診断手順

1. `AuthenticationResultMetadata` で**`CacheRefreshReason`を確認します**:

    | 価値 | Meaning |
    | --- | --- |
    | `NoCachedAccessToken` | キャッシュに一致するトークンがありません。最初の呼び出しまたはキャッシュがクリアされました |
    | `Expired` | キャッシュされたトークンの有効期限が切れています (トークン &gt; 1 時間経過した場合は通常) |
    | `ProactivelyRefreshed` | 有効期限が切れる前にトークンが更新されています (通常、可用性が向上します) |
    | `ForceRefreshOrClaims` | `.WithForceRefresh(true)` を明示的に呼び出した、またはクレームを渡したアプリ |
2. **キャッシュ キーの配置を確認します。** トークンは、機関 + クライアント ID + スコープ + (OBO の場合) ユーザー アサーション ハッシュによってキャッシュされます。 これらの呼び出しのいずれかが異なる場合は、キャッシュ ミスが発生します。
3. **コード内に意図しない`WithForceRefresh(true)`**がないか確認してください。これがあると、キャッシュは完全にバイパスされます。
4. **分散キャッシュ (Redis、SQL) の場合:** シリアル化コールバック(`SetBeforeAccessAsync`/`SetAfterAccessAsync`) が登録されており、サイレントに例外をスローしていないことを確認してください。

    ```csharp
    // Verify cache callbacks are firing
    app.AppTokenCache.SetBeforeAccessAsync(async args =>
    {
        logger.LogDebug("Cache read for {SuggestedKey}", args.SuggestedCacheKey);
        // Load from distributed store
    });
    ```

### マネージド ID のエラー

#### IMDS タイムアウトまたは使用不可

##### Symptoms

- `MsalServiceException` IMDS (インスタンス メタデータ サービス) に到達できないというエラー メッセージが表示されます。
- トークンの取得が失敗するまでの長い遅延 (2 秒以上)。
- マネージド ID の呼び出し中に `HttpRequestException` または `TaskCanceledException`。

##### 一般的な原因

- マネージド ID をサポートするAzure環境 (ローカルまたはサポートされていないホスティング環境での実行など) でアプリケーションが実行されていません。
- ネットワーク セキュリティ グループ (NSG) 規則は、IMDS エンドポイント (`169.254.169.254`) へのアクセスをブロックします。
- ユーザー割り当てマネージド ID が指定されていますが、存在しないか、リソースに割り当てられません。

##### Resolution

- **ホスティング環境で**マネージド ID (App Service、Azure Functions、VM、AKS、Container Apps など) がサポートされていることを確認します。
- **ローカル開発の場合**は、MI が使用できないときに開発者の資格情報に至る`Azure.Identity`から`DefaultAzureCredential`を使用するか、環境変数を使用して MI をローカルで無効にします。
- **NSG ルールを確認** する — `169.254.169.254:80` への送信アクセスが許可されていることを確認します。
- **ユーザー割り当て MI の場合**は、`ManagedIdentityId`値が、Azure リソースに割り当てられている ID のクライアント ID、リソース ID、またはオブジェクト ID と一致することを確認します。

    ```csharp
    var miApp = ManagedIdentityApplicationBuilder
        .Create(ManagedIdentityId.WithUserAssignedClientId("your-client-id"))
        .Build();
    ```

#### AADSTS70021 — フェデレーション ID レコードが一致しません

##### Symptoms

 マネージド ID を使用してワークロード ID フェデレーションを利用する場合の `MsalServiceException` と `AADSTS70021`。

##### Resolution

- ターゲット アプリの登録時にフェデレーション ID 資格情報が正しく構成されていることを確認します。
- フェデレーション資格情報の `subject`、 `issuer`、および `audience` の値が、マネージド ID トークンに含まれている値と一致することを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/exceptions/device-authentication-errors"} -->
## デバイス認証エラー - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/device-authentication-errors
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: MSAL.NET でデバイス認証を使用するときに表示されるエラー。

### 症状は何ですか?

"AADSTS50097" や "デバイス認証が必要です" などのエラーが表示されます。

### どうなりますか?

このエラーは、アクセスしているリソースに条件付きアクセス ポリシーが適用されている場合に発生します。このポリシーでは、トークンの取得元のデバイスを組織で管理する必要があり、MSAL.NET がこの ID を証明する必要があります。

これは、テナント管理者によって適用される条件付きアクセス ポリシーです。詳細については、「[方法: 条件付きアクセスを使用してクラウド アプリへのアクセスにマネージド デバイスを要求する」](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/require-managed-devices)を参照してください

### これを修正する方法

この要件を満たすには、Windowsまたはシステム ブラウザーで WAM を利用する必要があります。 モバイル プラットフォームでは、ブローカー (Microsoft Authenticatorとポータル サイト) を有効にする必要があります。

- Windowsで実行されているデスクトップ アプリケーションを作成する場合は、[デスクトップ アプリケーションの WAM 統合](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam)に関するページを参照してください。
- [iOS および Android では](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/mobile-applications)、[認証ブローカーを有効](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-use-brokers-with-xamarin-apps)にすることをお勧めします
- Web アプリケーションにも同じ原則が適用されますが、ブラウザーを使用している場合は、WAM (Chromium の Edge または Microsoft Entra 拡張機能を備えた Chrome) を "通信" できるブラウザーを利用する必要があります。 詳細については、「 [条件付きアクセス条件」](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/concept-conditional-access-conditions#chrome-support)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/exceptions/msal-error-handling"} -->
## MSAL.NET でエラーと例外を処理する - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/msal-error-handling
- Service: msal / msal-dotnet
- Article date: 2024-06-04
- Summary: MSAL.NET でエラーと例外、条件付きアクセス要求チャレンジ、再試行を処理する方法について説明します。

この記事では、さまざまな種類のエラーの概要と、一般的なサインイン エラーを処理するための推奨事項について説明します。

### MSAL エラー処理の基本

Microsoft Authentication Library (MSAL) の例外は、エンド ユーザーに表示されるのではなく、アプリ開発者がトラブルシューティングを行うために使用されます。 例外メッセージはローカライズされません。

例外とエラーを処理する場合は、例外の種類自体とエラー コードを使用して例外を区別できます。 エラー コードの一覧については、[認証と承認のエラー コードMicrosoft Entra](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/reference-error-codes)参照してください。

サインイン エクスペリエンス中に、同意、条件付きアクセス (MFA、デバイス管理、場所ベースの制限)、トークンの発行と利用、およびユーザー プロパティに関するエラーが発生する場合があります。

次のセクションでは、アプリのエラー処理の詳細について説明します。

### MSAL.NET でのエラー処理

#### 例外の種類

ライブラリ自体が不適切な構成などのエラー状態を検出すると、[MsalClientException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalexception) がスローされます。

[MsalServiceException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalserviceexception) は、ID プロバイダー (Microsoft Entra ID) がエラーを返すときにスローされます。 サーバー エラーの翻訳です。

[MsalUIRequiredException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msaluirequiredexception) は [MsalServiceException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalserviceexception) の種類であり、ユーザーの操作が必要であることを示します。 たとえば、多要素認証 (MFA) が必要な場合や、ユーザーが自分のパスワードを変更し、トークンをサイレントで取得できない場合などです。

#### 例外の処理

例外.NET処理するときは、例外の種類自体と`ErrorCode`メンバーを使用して例外を区別できます。 `ErrorCode` 値は [MsalError](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalerror) 型の定数です。

[MsalClientException、MsalServiceException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalexception)、[MsalUIRequiredException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalserviceexception) の各フィールドを見ることもできます。

[MsalServiceException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalserviceexception) がスローされた場合は、[認証と承認のエラー コード](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/reference-error-codes)を試して、コードが一覧表示されているかどうかを確認します。

[MsalUIRequiredException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msaluirequiredexception) がスローされた場合、ユーザーがその問題を解決するには対話型フローを実行する必要があることを示しています。 デスクトップ アプリやモバイル アプリなどのパブリック クライアント アプリでは、ブラウザーを表示する `AcquireTokenInteractive` を呼び出すことによって解決されます。 機密クライアント アプリでは、Web アプリはユーザーを承認ページにリダイレクトし、Web API は認証エラーを示す HTTP 状態コードとヘッダー (401 Unauthorized と WWW-Authenticate ヘッダー) を返す必要があります。

#### 一般的な.NET例外

スローされる可能性がある一般的な例外と、可能な軽減策を次に示します。

| 例外 | エラー コード | 緩和策 |
| --- | --- | --- |
| [MsalUiRequiredException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msaluirequiredexception) | AADSTS65001: ユーザーまたは管理者は、'{appName}' という名前の ID '{appId}' のアプリケーションの使用に同意していません。 このユーザーとリソースに、対話形式の承認要求を送信してください。 | 最初にユーザーの同意を取得します。 .NET Core（Web UI を備えていない）を使用していない場合は、`AcquireTokenInteractive` を 1 回だけ呼び出してください。 .NETコアを使用している場合、または`AcquireTokenInteractive`を行いたくない場合、ユーザーは URL に移動して同意を与えることができます:`https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id={clientId}&response_type=code&scope=user.read`。 `AcquireTokenInteractive`を呼び出す場合:`app.AcquireTokenInteractive(scopes).WithAccount(account).WithClaims(ex.Claims).ExecuteAsync();` |
| [MsalUiRequiredException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msaluirequiredexception) | AADSTS50079: ユーザーは [多要素認証 (MFA)](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/concept-mfa-howitworks) を使用する必要があります。 | 軽減策はありません。 MFA がテナントに対して構成されており、Microsoft Entra ID がその適用を決定した場合は、`AcquireTokenInteractive` などの対話型フローに切り替えます。 |
| [MsalServiceException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalserviceexception) | AADSTS90010: グラントの種類は、*/common* または */consumers* エンドポイントではサポートされていません。 */organizations* またはテナント固有のエンドポイントを使用します。 */common* を使用しました。 | Microsoft Entra IDからのメッセージで説明されているように、機関にはテナントが必要です。それ以外の場合は */organizations* が必要です。 |
| [MsalServiceException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalserviceexception) | AADSTS70002: 要求本文には、次のパラメーターが含まれている必要があります: `client_secret or client_assertion`。 | この例外は、アプリケーションが Microsoft Entra ID でパブリック クライアント アプリケーションとして登録されていなかった場合にスローされる可能性があります。 Microsoft Entra 管理センターで、アプリケーションのマニフェストを編集し、`allowPublicClient`を `true` に設定します。 |
| [MsalClientException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalclientexception) | `unknown_user Message`: ログインしているユーザーを識別できませんでした | ライブラリは、現在 Windows にログインしているユーザーを照会できなかったか、このユーザーが Active Directory または Microsoft Entra に参加していません（ワークプレース参加ユーザーはサポートされていません）。 軽減策: ユーザー名 (たとえば john@contoso.com) を取得する独自のロジックを実装し、ユーザー名を引数として受け取る `AcquireTokenByIntegratedWindowsAuth` 形式を使用します。 |
| [MsalClientException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalclientexception) | 管理対象ユーザーでは統合 Windows 認証はサポートされていません | このメソッドは、Active Directory (AD) によって公開されるプロトコルに依存します。 ユーザーが AD バッキング ("マネージド" ユーザー) なしでMicrosoft Entra IDで作成された場合、このメソッドは失敗します。 AD で作成され、Microsoft Entra ID ("フェデレーション" ユーザー) によってサポートされているユーザーは、この非対話型の認証方法の恩恵を受けることができます。 軽減策: 対話型認証を使用します。 |

#### `MsalUiRequiredException`

`AcquireTokenSilent()`の呼び出し時に MSAL.NET から返される一般的な状態コードの 1 つが`MsalError.InvalidGrantError`。 この状態コードは、アプリケーションが認証ライブラリを再度呼び出す必要があることを意味しますが、対話型モード (パブリック クライアント アプリケーションの場合は AcquireTokenInteractive または AcquireTokenByDeviceCodeFlow、Web アプリでは課題があります)。 これは、認証トークンを発行する前に追加のユーザー操作が必要になるためです。

ほとんどの場合、 `AcquireTokenSilent` が失敗するのは、トークン キャッシュに要求に一致するトークンがないためです。 アクセス トークンは 1 時間で期限切れになり、 `AcquireTokenSilent` は更新トークンに基づいて新しいトークンをフェッチしようとします (OAuth2 の用語では、これは "更新トークン" フローです)。 このフローは、テナント管理者がより厳格なサインイン ポリシーを構成する場合など、さまざまな理由で失敗する場合もあります。

この操作は、ユーザーにアクションを実行してもらうことを目的としています。 これらの条件の中には、ユーザーが簡単に解決できる条件 (たとえば、1 回のクリックで使用条件に同意する) と、現在の構成で解決できない条件があります (たとえば、該当するマシンは特定の企業ネットワークに接続する必要があります)。 ユーザーが多要素認証を設定したり、デバイスにMicrosoft Authenticatorをインストールしたりするのに役立つものもあります。

#### `MsalUiRequiredException` 分類列挙

MSAL は `Classification` フィールドを公開します。これを読んで、ユーザー エクスペリエンスを向上させることができます。 たとえば、パスワードの有効期限が切れたことをユーザーに伝えたり、一部のリソースを使用するために同意する必要があることをユーザーに伝えたりします。 サポートされている値は、 [`UiRequiredExceptionClassification`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.uirequiredexceptionclassification) 列挙型の一部です。

| Classification | Meaning | 推奨される処理 |
| --- | --- | --- |
| BasicAction | 条件は、対話型認証フロー中にユーザーの操作によって解決できます。 | AcquireTokenInteractively() を呼び出します。 |
| 追加アクション | 条件は、対話型認証フローの外部で、システムとの追加の修復操作によって解決できます。 | AcquireTokenInteractively() を呼び出して、修復アクションを説明するメッセージを表示します。 呼び出し元のアプリケーションでは、ユーザーが修復アクションを完了する可能性が低い場合に、additional\_actionを必要とするフローを非表示にすることができます。 |
| MessageOnly | 現時点では、条件を解決できません。 対話型認証フローを起動すると、条件を説明するメッセージが表示されます。 | AcquireTokenInteractively() を呼び出して、条件を説明するメッセージを表示します。 AcquireTokenInteractively() は、ユーザーがメッセージを読んでウィンドウを閉じた後に UserCanceled エラーを返します。 呼び出し元のアプリケーションは、ユーザーがメッセージの恩恵を受ける可能性が低い場合にmessage\_onlyになるフローを非表示にすることを選択できます。 |
| 同意が必要です | ユーザーの同意がないか、取り消されています。 | ユーザーが同意を得るために AcquireTokenInteractively() を呼び出します。 |
| UserPasswordExpired | ユーザーのパスワードの有効期限が切れています。 | ユーザーが自分のパスワードをリセットできるように AcquireTokenInteractively() を呼び出します。 |
| PromptNeverFailed | パラメーター prompt=never を使用して対話型認証が呼び出され、MSAL はブラウザーの Cookie に依存し、ブラウザーを表示しないように強制されました。 これは失敗しました。 | Prompt.None を指定せずに AcquireTokenInteractively() を呼び出す |
| AcquireTokenSilentFailed | MSAL SDK には、キャッシュからトークンをフェッチするための十分な情報がありません。 これは、キャッシュにトークンが存在しないか、アカウントが見つからなかったことが原因である可能性があります。 エラー メッセージに詳細が表示されます。 | AcquireTokenInteractively() を呼び出します。 |
| なし | 詳細は提供されません。 条件は、対話型認証フロー中にユーザーの操作によって解決される場合があります。 | AcquireTokenInteractively() を呼び出します。 |

### .NETコード例

```csharp
AuthenticationResult res;
try
{
 res = await application.AcquireTokenSilent(scopes, account)
        .ExecuteAsync();
}
catch (MsalUiRequiredException ex) when (ex.ErrorCode == MsalError.InvalidGrantError)
{
 switch (ex.Classification)
 {
  case UiRequiredExceptionClassification.None:
   break;
  case UiRequiredExceptionClassification.MessageOnly:
  // You might want to call AcquireTokenInteractive(). Azure AD will show a message
  // that explains the condition. AcquireTokenInteractively() will return UserCanceled error
  // after the user reads the message and closes the window. The calling application may choose
  // to hide features or data that result in message_only if the user is unlikely to benefit 
  // from the message
  try
  {
      res = await application.AcquireTokenInteractive(scopes).ExecuteAsync();
  }
  catch (MsalClientException ex2) when (ex2.ErrorCode == MsalError.AuthenticationCanceledError)
  {
   // Do nothing. The user has seen the message
  }
  break;

  case UiRequiredExceptionClassification.BasicAction:
  // Call AcquireTokenInteractive() so that the user can, for instance accept terms
  // and conditions

  case UiRequiredExceptionClassification.AdditionalAction:
  // You might want to call AcquireTokenInteractive() to show a message that explains the remedial action. 
  // The calling application may choose to hide flows that require additional_action if the user 
  // is unlikely to complete the remedial action (even if this means a degraded experience)

  case UiRequiredExceptionClassification.ConsentRequired:
  // Call AcquireTokenInteractive() for user to give consent.
  
  case UiRequiredExceptionClassification.UserPasswordExpired:
  // Call AcquireTokenInteractive() so that user can reset their password
  
  case UiRequiredExceptionClassification.PromptNeverFailed:
  // You used WithPrompt(Prompt.Never) and this failed
  
  case UiRequiredExceptionClassification.AcquireTokenSilentFailed:
  default:
  // May be resolved by user interaction during the interactive authentication flow.
  res = await application.AcquireTokenInteractive(scopes)
                         .ExecuteAsync(); break;
 }
}
```

### 条件付きアクセスと要求の課題

トークンをサイレントで取得すると、アクセスしようとしている API で MFA ポリシーなどの [条件付きアクセス要求チャレンジ](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-conditional-access-dev-guide) が必要な場合、アプリケーションでエラーが発生する可能性があります。

このエラーを処理するパターンは、MSAL を使用して対話形式でトークンを取得することです。 これにより、ユーザーにプロンプトが表示され、必要な条件付きアクセス ポリシーを満たす機会が提供されます。

場合によっては、条件付きアクセスが必要な API を呼び出す際に、API から返されるエラー内でクレーム チャレンジを受け取ることがあります。 たとえば、条件付きアクセス ポリシーでマネージド デバイス (Intune) を使用する場合、エラーは [AADSTS53000 のようになります。このリソースや同様のリソースにアクセスするには、デバイスを管理する必要があります](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/reference-error-codes) 。 この場合、取得トークン呼び出しで要求を渡して、ユーザーが適切なポリシーを満たすように求めることができます。

MSAL.NET からの条件付きアクセスを必要とする API を呼び出す場合、アプリケーションは要求チャレンジの例外を処理する必要があります。 これは、[MsalServiceException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalserviceexception) として表示され、その [Claims](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalserviceexception.claims) プロパティは空ではありません。

要求チャレンジを処理するには、 [WithClaims(String)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.abstractacquiretokenparameterbuilder-1.withclaims#microsoft-identity-client-abstractacquiretokenparameterbuilder-1-withclaims%28system-string%29)を使用します。

### エラーと例外の後の再試行

MSAL を呼び出すときに、独自の再試行ポリシーを実装する必要があります。 MSAL では、Microsoft Entra サービスへの HTTP 呼び出しが行われ、エラーが発生することがあります。 たとえば、ネットワークがダウンしたり、サーバーが過負荷になったりする可能性があります。

#### HTTP 429

サービス トークン サーバー (STS) が多すぎる要求でオーバーロードされると、HTTP エラー 429 が返され、 `Retry-After` 応答フィールドで再試行できるまでの時間に関するヒントが返されます。

#### HTTP エラー コード 500 から 600

MSAL.NET は、HTTP エラー コード 500 から 600 のエラーに対する単純な再試行 1 回のメカニズムを実装します。

[MsalServiceException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalserviceexception) では、`System.Net.Http.Headers.HttpResponseHeaders` がプロパティ `namedHeaders` として公開されます。 エラー コードの追加情報を使用して、アプリケーションの信頼性を向上させることができます。 このケースでは、(`RetryAfter` 型の) `RetryConditionHeaderValue` プロパティを使用して、再試行するタイミングを計算できます。

クライアント資格情報フローを使用するデーモン アプリケーションの例を次に示します。 これは、トークンを取得するための任意のメソッドに適応させることができます。

```csharp

bool retry = false;
do
{
    TimeSpan? delay;
    try
    {
         result = await publicClientApplication.AcquireTokenForClient(scopes, account).ExecuteAsync();
    }
    catch (MsalServiceException serviceException)
    {
         if (serviceException.ErrorCode == "temporarily_unavailable")
         {
             RetryConditionHeaderValue retryAfter = serviceException.Headers.RetryAfter;
             if (retryAfter.Delta.HasValue)
             {
                 delay = retryAfter.Delta;
             }
             else if (retryAfter.Date.HasValue)
             {
                 delay = (retryAfter.Date.Value – DateTimeOffset.Now).TotalMilliseconds;
             }
         }
    }
    // . . .
    if (delay.HasValue)
    {
        Thread.Sleep((int)delay.Value.TotalMilliseconds); // sleep or other
        retry = true;
    }
} while (retry);
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/exceptions/msal-logging"} -->
## MSAL.NET でのエラーと例外のログ記録 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/msal-logging
- Service: msal / msal-dotnet
- Article date: 2022-10-21
- Summary: MSAL.NET でエラーと例外をログに記録する方法について説明します

MSAL.NET アプリは、問題の診断に役立つログ メッセージを生成します。 数行のコードを使用してログ記録を構成し、詳細レベルと、個人データと組織データをログに記録するかどうかをカスタムで制御できます。 ログ記録は既定では有効になっていません。 MSAL ログを有効にして、ユーザーが認証の問題がある場合にログを送信する方法を提供することをお勧めします。 MSAL はログを格納せず、ロガーの実装で提供される宛先にログを出力します。

Note

MSAL.NET 4.58.0 以降の開発者は[、OpenTelemetry を使用](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/monitoring#opentelemetry)してログを集計し、アプリケーションのパフォーマンスを測定することもできます。

### ログ記録のレベル

ログの詳細には、いくつかのレベルがあります。

- `LogAlways`: MSAL 操作の診断に役立つ重要な正常性メトリックのログを含む基本レベル。
- `Critical`: 回復不能なアプリケーションまたはシステムのクラッシュ、または直ちに注意が必要な致命的な障害を示すログ。
- `Error`: 問題が発生し、エラーが生成されたことを示します。 問題のデバッグと特定に使用されます。
- `Warning`: 必ずしもエラーや障害が発生していない場合でも、診断や問題箇所の特定を目的としたシナリオのログが含まれます。 これは、運用アプリで有効にする必要がある推奨される最小レベルです。
- `Informational`: MSAL は、情報を目的としたイベントをログに記録します。必ずしもデバッグを目的としたものではありません。
- `Verbose`:MSAL は、ライブラリの動作の詳細をログに記録します。 運用環境では、特定のデバッグ目的でログを収集するために、詳細レベルを一時的にのみ有効にする必要があります。

### 個人データと組織データ

既定では、MSAL ロガーは機密性の高い個人データや組織データをキャプチャしません。 ライブラリには、個人データと組織データのログ記録を有効にするオプションが用意されています (これを行う場合)。 詳細については、[MSAL.NET での個人を特定できる情報の処理を](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/resources/handling-pii)参照してください。

### MSAL.NET でログ記録を構成する

MSAL では、 [WithLogging(IIdentityLogger, Boolean)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.baseabstractapplicationbuilder-1.withlogging#microsoft-identity-client-baseabstractapplicationbuilder-1-withlogging%28microsoft-identitymodel-abstractions-iidentitylogger-system-boolean%29) ビルダーを使用して、アプリケーションの作成時にログ記録が設定されます。 このメソッドは、次のパラメーターを受け取ります。

- `identityLogger`は、デバッグまたは正常性チェックの目的でログを生成するために MSAL.NET によって使用されるログの実装です。 ログは、ログ記録が有効になっている場合にのみ送信されます。
- `enablePiiLogging` true に設定すると、個人データと組織データ (PII) のログ記録が有効になります。 既定では、アプリケーションが機密データをログに記録しないように、このパラメーターは false に設定されます。

#### IIdentityLogger インターフェイス

```csharp
namespace Microsoft.IdentityModel.Abstractions
{
    public interface IIdentityLogger
    {
        //
        // Summary:
        //     Checks to see if logging is enabled at given eventLogLevel.
        //
        // Parameters:
        //   eventLogLevel:
        //     Log level of a message.
        bool IsEnabled(EventLogLevel eventLogLevel);

        //
        // Summary:
        //     Writes a log entry.
        //
        // Parameters:
        //   entry:
        //     Defines a structured message to be logged at the provided Microsoft.IdentityModel.Abstractions.LogEntry.EventLogLevel.
        void Log(LogEntry entry);
    }
}
```

Note

上位レベルのライブラリ (`Microsoft.Identity.Web`、`Microsoft.IdentityModel`) は、さまざまな環境 (特に ASP.NET Core) に対してこのインターフェイスの実装を既に提供しています。

#### IIdentityLogger の実装

##### 設定ファイルのログレベル

コードでログ レベルを設定するために、環境内の構成ファイルを使用するようにコードを構成することを強くお勧めします。そのため、アプリケーションをリビルドまたは再起動しなくても、コードで MSAL ログ レベルを変更できます。 これは診断目的で重要であり、運用環境に現在デプロイされているアプリケーションから必要なログをすばやく収集できます。 詳細ログはコストがかかる可能性があるため、既定では `Informational` レベルを使い、問題が発生したときに詳細ログを有効にすることをお勧めします。 アプリケーションを再起動せずに構成ファイルからデータを読み込む方法の例については、 [JSON 構成プロバイダー](https://learn.microsoft.com/ja-jp/aspnet/core/fundamentals/configuration#json-configuration-provider) を参照してください。

##### 環境変数から取得するログレベル

推奨されるもう 1 つのオプションは、コンピューター上の環境変数を使用してログ レベルを設定するようにコードを構成することです。ログ レベルを設定すると、アプリケーションをリビルドしなくても、コードで MSAL ログ レベルを変更できるようになります。

使用可能なログ レベルの詳細については、 [EventLogLevel](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identitymodel.abstractions.eventloglevel) を参照してください。

例：

```csharp
class MyIdentityLogger : IIdentityLogger
{
    public EventLogLevel MinLogLevel { get; }

    public MyIdentityLogger()
    {
        //Retrieve the log level from an environment variable
        var msalEnvLogLevel = Environment.GetEnvironmentVariable("MSAL_LOG_LEVEL");

        if (Enum.TryParse(msalEnvLogLevel, out EventLogLevel msalLogLevel))
        {
            MinLogLevel = msalLogLevel;
        }
        else
        {
            //Recommended default log level
            MinLogLevel = EventLogLevel.Informational;
        }
    }

    public bool IsEnabled(EventLogLevel eventLogLevel)
    {
        return eventLogLevel <= MinLogLevel;
    }

    public void Log(LogEntry entry)
    {
        //Log Message here:
        Console.WriteLine(entry.Message);
    }
}
```

`MyIdentityLogger`の使用

```csharp
MyIdentityLogger myLogger = new MyIdentityLogger();

var app = ConfidentialClientApplicationBuilder
    .Create(TestConstants.ClientId)
    .WithClientSecret("secret")
    .WithLogging(myLogger, enablePiiLogging)
    .Build();
```

### 分散トークン キャッシュでのログ記録

.NET の [Microsoft.Identity.Web.TokenCache](https://www.nuget.org/packages/Microsoft.Identity.Web.TokenCache) パッケージのトークン キャッシュ シリアライザーを使用している場合は、追加のキャッシュ ログを有効にできます。

分散キャッシュ ログを有効にするには、 [MinLevel](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.extensions.logging.loggerfilteroptions.minlevel#microsoft-extensions-logging-loggerfilteroptions-minlevel) プロパティを [Debug](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.extensions.logging.loglevel#microsoft-extensions-logging-loglevel-debug) に設定します。

```csharp
     app.AddDistributedTokenCache(services =>
     {
          services.AddDistributedMemoryCache();
          services.AddLogging(configure => configure.AddConsole())
               .Configure<LoggerFilterOptions>(options => options.MinLevel = Microsoft.Extensions.Logging.LogLevel.Debug);
     });
```

詳細については [、「カスタム ログ プロバイダーの実装](https://learn.microsoft.com/ja-jp/dotnet/core/extensions/custom-logging-provider) 」を参照してください。

### 相関 ID

ログは、クライアント側での MSAL の動作を理解するのに役立ちます。 サービス側で何が起こっているかを理解するには、チームに関連付け ID が必要です。 この ID は、さまざまなバックエンド サービスを介して認証要求をトレースします。

関連付け ID は、次の 3 つの方法で取得できます。

1. 成功した認証結果から: [AuthenticationResult.CorrelationId](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.authenticationresult.correlationid#microsoft-identity-client-authenticationresult-correlationid)。
2. サービス例外から: [MsalException.CorrelationId](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalexception.correlationid)。
3. トークン要求の作成時にカスタム関連付け ID を [WithCorrelationId(Guid)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.baseabstractacquiretokenparameterbuilder-1.withcorrelationid#microsoft-identity-client-baseabstractacquiretokenparameterbuilder-1-withcorrelationid%28system-guid%29) に渡すこと。

独自の関連付け ID を指定する場合は、要求ごとに異なる ID 値を使用します。 要求を区別できないため、定数を使用しないでください。

### ネットワークトレース

Important

通常、ネットワーク トレースには個人を特定できる情報と資格情報が含まれます。 GitHubにログを投稿する前に**、機密情報を削除します**。

詳細ログで十分な分析情報が得られない場合は、 [Fiddler](https://www.telerik.com/fiddler) や [`mitmproxy`](https://mitmproxy.org/) などのツールを使用してネットワーク トレースを取得できます。 選択したツールをローカル プロキシとして構成し、ローカル ネットワーク上のデバイスからのトラフィックを受け入れることで、iPhone や Android フォンなどの他のデバイスからのトレースをキャプチャできます。 ログをキャプチャする前に、プラットフォーム固有の構成が必要になる場合があります。

このようなツールを使用できない場合は、MSAL によって使用される `HttpClient` を変更して HTTP トラフィックをログに記録できます。 詳細については、 [このカスタム `HttpClient` の実装とログ記録を](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/blob/b259cf00936a11a9cff789bf094935d8d31aea7f/tests/Microsoft.Identity.Test.Common/Core/Helpers/HttpSnifferClientFactory.cs#L11)参照してください。

Warning

このクライアントは、運用環境では使用せず、ログ記録にのみ使用してください。

カスタム `HttpClient` は次のように追加できます。

```csharp
var msalPublicClient = PublicClientApplicationBuilder
       .Create(ClientId)
       .WithHttpClientFactory(new HttpSnifferClientFactory())
       .Build();
```

#### WAM 使用時のネットワーク トレース

Fiddler を[使用してWindowsで Web アカウント マネージャー (WAM)](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam) のネットワーク トレースを収集するには、いくつかの追加の手順が必要です。

1. Fiddler で AppContainer ループバックを有効にするには、 **WinConfig** をクリックし、[ **すべて除外** ] を選択して変更を保存します。

[Image: Fiddler の除外インターフェイス。WinConfig ダイアログにすべてのアプリケーションが表示されます。]

1. HTTPS 復号化を有効にしますが、HTTPS 復号化から ADFS (`msft.sts.microsoft.com`) を除外します。

[Image: HTTPS 復号化を構成する方法を示す Fiddler オプションのスクリーンショット]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/exceptions/retry-policy"} -->
## 再試行ポリシー - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/retry-policy
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: MSAL を使用して.NETでトークン取得操作のカスタム再試行ポリシーを実装する方法について説明します。 詳細なガイドを使用して、サービスの可用性を向上させます。

MSAL には、独自の再試行ポリシーがあります。 まれに、内部再試行ポリシーを無効にして独自の再試行ポリシーを追加することもできます。 [HttpClient のヒント](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/httpclient)を参照してください。

#### MSAL は、HTTP エラー コード 5xx のエラーに対して単純な "retry-once" を実装します

MSAL.NET は、トークン エンドポイントに対して HTTP エラー コード 500 から 600 のエラーに対して 1 秒の遅延メカニズムを備えた単純な再試行を 1 回実装します。 マネージド ID の場合、再試行は各ソースのガイドラインに従います。

### HTTP スタックをカスタマイズする

プロキシの使用など、HTTP スタックをカスタマイズしたい場合があります。 詳細については [、HttpClient のヒント](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/httpclient) を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/exceptions/tls-issues"} -->
## TLS の問題 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/tls-issues
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: MSAL.NET を使用するときに TLS の問題を診断して対処する方法

### 状況

Microsoftには、セキュリティ上の理由から、TLS 1.2 以外を無効にするイニシアチブがあります。 [Microsoft TLS 1.0 実装](https://support.microsoft.com/help/3117336/schannel-implementation-of-tls-1-0-in-windows-security-status-update-n)においては、セキュリティに関する既知の脆弱性はありません。 ただし、将来のプロトコル ダウングレード攻撃やその他の TLS の脆弱性の可能性があるため、たとえば Office では、Microsoft 365での TLS 1.0 と 1.1 のサポートが[中止](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/prepare-tls-1.2-in-office-365)されています。

この取り組みが進むにつれて、Azureにデプロイされた一部のサービスに TLS 2.0 が必要であり、これは MSAL.NET によってキャッチされるという事実について、ますます多くの質問をします。 インスタンス [#657](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/issues/657) を参照してください

MSAL.NET は既に TLS 2.0 (以前のバージョン) をサポートしています。 一部のユーザーは System.Net.ServicePointManager.SecurityProtocol を System.Net.SecurityProtocolType.Tls12 に設定することを提案していますが、これは TLS 1.3 が表示されたときのように適切な修正プログラムではないため、アプリを変更する必要があります。

### 適切な修正プログラムは何ですか?

[.NET Framework でトランスポート層セキュリティ (TLS) のベスト プラクティスを](https://learn.microsoft.com/ja-jp/dotnet/framework/network-programming/tls)読むことをお勧めします。 最も簡単な修正は、可能であれば、アプリが .NET Framework 4.7 以降に移行することを確認することです。それ以外の場合は、ベスト プラクティスドキュメントにオプションが詳細に記載されています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/exceptions/understanding-msaluirequiredexception"} -->
## MsalUiRequiredException について - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/understanding-msaluirequiredexception
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: MSAL.NET の MsalUiRequiredException とそのプロパティ、および効果的な処理方法について説明します。

`AcquireTokenSilent()`の呼び出し時に MSAL.NET から返される一般的な状態コードの 1 つが`MsalError.InvalidGrantError`。 この状態コードは、アプリケーションが認証ライブラリをもう一度呼び出す必要があることを意味しますが、対話型モード (パブリック クライアント アプリケーションの場合は AcquireTokenInteractive または AcquireTokenByDeviceCodeFlow、Web アプリではチャレンジを行います)。 これは、認証トークンを発行する前に追加のユーザー操作が必要になるためです。

ほとんどの場合、 `AcquireTokenSilent` が失敗するのは、トークン キャッシュに要求に一致するトークンがないためです。 アクセス トークンは 1 時間で期限切れになり、 `AcquireTokenSilent` は更新トークンに基づいて新しいトークンをフェッチしようとします (OAuth2 の用語では、これは "更新トークン" フローです)。 このフローは、テナント管理者がより厳格なログイン ポリシーを構成する場合など、さまざまな理由で失敗する可能性もあります。

この操作は、ユーザーにアクションを実行してもらうことを目的としています。 これらの条件の中には、ユーザーが簡単に解決できる条件 (たとえば、1 回のクリックで使用条件に同意する) と、現在の構成で解決できない条件があります (たとえば、該当するマシンは特定の企業ネットワークに接続する必要があります)。 ユーザーが多要素認証を設定したり、デバイスにMicrosoft Authenticatorをインストールしたりするのに役立つものもあります。

### `MsalUiRequiredException` 分類

MSAL は `Classification` フィールドを公開します。このフィールドを読んでユーザー エクスペリエンスを向上させることができます。たとえば、パスワードの有効期限が切れたことや、一部のリソースを使用するために同意する必要があることをユーザーに伝えます。 サポートされている値は、 `UiRequiredExceptionClassification` 列挙型の一部です。

| Classification | Meaning | 推奨される処理 |
| --- | --- | --- |
| BasicAction | 条件は、対話型認証フロー中にユーザーの操作によって解決できます。 | AcquireTokenInteractively() を呼び出します。 |
| 追加アクション | 条件は、対話型認証フローの外部で、システムとの追加の修復操作によって解決できます。 | AcquireTokenInteractively() を呼び出して、修復アクションを説明するメッセージを表示します。 呼び出し元のアプリケーションでは、ユーザーが修復アクションを完了する可能性が低い場合に、additional\_actionを必要とするフローを非表示にすることができます。 |
| MessageOnly | 現時点では、条件を解決できません。 対話型認証フローを起動すると、条件を説明するメッセージが表示されます。 | AcquireTokenInteractively() を呼び出して、条件を説明するメッセージを表示します。 AcquireTokenInteractively() は、ユーザーがメッセージを読んでウィンドウを閉じた後に UserCanceled エラーを返します。 呼び出し元のアプリケーションは、ユーザーがメッセージの恩恵を受ける可能性が低い場合にmessage\_onlyになるフローを非表示にすることを選択できます。 |
| 同意が必要です | ユーザーの同意がないか、取り消されています。 | ユーザーが同意を得るために AcquireTokenInteractively() を呼び出します。 |
| UserPasswordExpired | ユーザーのパスワードの有効期限が切れています。 | ユーザーが自分のパスワードをリセットできるように AcquireTokenInteractively() を呼び出します。 |
| PromptNeverFailed | パラメーター prompt=never を使用して対話型認証が呼び出され、MSAL はブラウザーの Cookie に依存し、ブラウザーを表示しないように強制されました。 これは失敗しました。 | Prompt.None を指定せずに AcquireTokenInteractively() を呼び出す |
| AcquireTokenSilentFailed | MSAL SDK には、キャッシュからトークンをフェッチするための十分な情報がありません。 これは、キャッシュにトークンが存在しないか、アカウントが見つからなかったことが原因である可能性があります。 エラー メッセージに詳細が表示されます。 | AcquireTokenInteractively() を呼び出します。 |
| なし | 詳細は提供されません。 条件は、対話型認証フロー中にユーザーの操作によって解決される場合があります。 | AcquireTokenInteractively() を呼び出します。 |

### コード例

```csharp
AuthenticationResult res;
try
{
 res = await application.AcquireTokenSilent(scopes, account)
        .ExecuteAsync();
}
catch (MsalUiRequiredException ex) when (ex.ErrorCode == MsalError.InvalidGrantError)
{
 switch (ex.Classification)
 {
  case UiRequiredExceptionClassification.None:
   break;
  case UiRequiredExceptionClassification.MessageOnly:
  // You might want to call AcquireTokenInteractive(). Azure AD will show a message
  // that explains the condition. AcquireTokenInteractively() will return UserCanceled error
  // after the user reads the message and closes the window. The calling application may choose
  // to hide features or data that result in message_only if the user is unlikely to benefit 
  // from the message
  try
  {
   res = await application.AcquireTokenInteractive(scopes)
                          .ExecuteAsync();
  }
  catch (MsalClientException ex2) when (ex2.ErrorCode == MsalError.AuthenticationCanceledError)
  {
   // Do nothing. The user has seen the message
  }
  break;

  case UiRequiredExceptionClassification.BasicAction:
  // Call AcquireTokenInteractive() so that the user can, for instance accept terms
  // and conditions

  case UiRequiredExceptionClassification.AdditionalAction:
  // You might want to call AcquireTokenInteractive() to show a message that explains the remedial action. 
  // The calling application may choose to hide flows that require additional_action if the user 
  // is unlikely to complete the remedial action (even if this means a degraded experience)

  case UiRequiredExceptionClassification.ConsentRequired:
  // Call AcquireTokenInteractive() for user to give consent.
  
  case UiRequiredExceptionClassification.UserPasswordExpired:
  // Call AcquireTokenInteractive() so that user can reset their password
  
  case UiRequiredExceptionClassification.PromptNeverFailed:
  // You used WithPrompt(Prompt.Never) and this failed
  
  case UiRequiredExceptionClassification.AcquireTokenSilentFailed:
  default:
  // May be resolved by user interaction during the interactive authentication flow.
  res = await application.AcquireTokenInteractive(scopes)
                         .ExecuteAsync(); break;
 }
}
```

詳細については、問題 [#1148](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/issues/1148) を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/exceptions/understanding-statemismatcherror"} -->
## StateMismatchError について - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/understanding-statemismatcherror
- Service: msal / msal-dotnet
- Article date: 2025-05-20

MSAL は、セキュリティ対策として、サーバーから返された state を元の state と照合して検証します。 状態が異なる場合は、この例外がスローされます。

### 既知の問題

33 文字以上であることが確認されている長い Facebook ID（たとえば somelongemailaddressfortest@gmail.com）を使用するアプリでは、この例外が発生します。 デスクトップ アプリの埋め込み Web ビューでは、Internet Explorerが使用され、URL が 2083 文字に切り捨てられ、URL の状態パラメーターの値が切り捨てられます。 これにより、返された状態が元の状態と異なります。

軽減するには、`.WithUseEmbeddedWebView(false)`を使用し、「[Web ブラウザーの使用 (MSAL.NET)」](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-web-browsers)を参照してください。

### References

- [Internet Explorerの最大 URL 長は 2,083 文字です](https://support.microsoft.com/help/208427/maximum-url-length-is-2-083-characters-in-internet-explorer)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/exceptions/unity"} -->
## Unity アプリケーションでの MSAL.NET のトラブルシューティング - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/unity
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: Unity アプリケーションで MSAL.NET のトラブルシューティングを行う方法について説明します。 ランタイム例外の原因を理解し、効果的な解決策を見つけ出します。

MSAL 4.48.0 以降では、`net6` ターゲットに対するリフレクションの使用を停止しました。 これは Unity で進む唯一のパスです。

### 実行時にメンバーが見つかりません

#### 問題

Unity アプリで MSAL.NET を使用すると、アプリケーションが正常にビルドされます。 ただし、実行時には、以下のように、一部のメンバーが MSAL.NET のコード内に存在しないことを示す例外が発生します。

```bash
Error on deserializing read-only members in the class: No set method for property 'Claims' in type 'Microsoft.Identity.Client.OAuth2.OAuth2ResponseBase'.
  at System.Runtime.Serialization.DataContract+DataContractCriticalHelper.ThrowInvalidDataContractException
   (System.String message, System.Type type) [0x00000] in <00000000000000000000000000000000>:0 
  at System.Runtime.Serialization.DataContract.ThrowInvalidDataContractException
   (System.String message, System.Type type) [0x00000] in <00000000000000000000000000000000>:0 
```

```bash
Error setting value to 'TenantDiscoveryEndpoint' on 'Microsoft.Identity.Client.Instance.Discovery.InstanceDiscoveryResponse'.
 at Microsoft.Identity.Json.Serialization.ExpressionValueProvider.SetValue
   (System.Object target, System.Object value) [0x00000] in <00000000000000000000000000000000>:0 \r\n
 at Microsoft.Identity.Json.Serialization.JsonSerializerInternalReader.SetPropertyValue
   (Microsoft.Identity.Json.Serialization.JsonProperty property, Microsoft.Identity.Json.JsonConverter propertyConverter,
     Microsoft.Identity.Json.Serialization.JsonContainerContract containerContract, Microsoft.Identity.Json.Serialization.JsonProperty containerProperty,
     Microsoft.Identity.Json.JsonReader reader, System.Object target) [0x00000] in <00000000000000000000000000000000>:0
```

#### 原因と解決策

この問題は、Unity IL2CPP プラグインから発生します。 コードを最適化する (コードの削除を使用する) と、リフレクションが機能するために必要な依存関係が削除されます (その使用法を適切に検出できないため)。 MSAL.NET チームは、MSAL からリフレクション関連のコードを削除することを調査しましたが、非常に実用的ではありません。 Unity 自体は、ドキュメントに記載されています ([マネージド コードの削除](https://docs.unity3d.com/Manual/ManagedCodeStripping.html#LinkXML)) と、この問題の解決策の 1 つとして Link XML メソッドを使用することをお勧めします。 これは私たちの推奨事項です。

ルート `Assets/link.xml` フォルダーに以下のエントリを追加します。

```xml
<linker>
 <assembly fullname="Microsoft.Identity.Client" preserve="all" />
 <assembly fullname="System" preserve="all" />
 <assembly fullname="System.Core" preserve="all" />
</linker>
```

#### こちらも参照ください

[#1185](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/issues/1185)、 [#2231](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/issues/2231)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/exceptions/wam-errors"} -->
## Web アカウント マネージャー (WAM) に関連付けられているエラー - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/wam-errors
- Service: msal / msal-dotnet
- Article date: 2023-08-30
- Summary: MSAL.NET と Web アカウント マネージャー (WAM) を使用してアプリケーションをビルドすると、開発者が問題を発生する可能性があります。 この記事では、潜在的なエラーと軽減策について説明します。

一般に、Web アカウント マネージャー (WAM) と対話する MSAL コンポーネントによって生成されたエラーは、のインスタンスに[MsalException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalexception)。 これにより、開発者は内部を気にせず、代わりに慣用的な.NETコンストラクトを使用してエラーを処理できます。 ただし、さらに詳細な情報を得るために、開発者は特定のエラー メッセージを調査する必要があることがよくあります。

次の表は、最も一般的なエラーの一部と、潜在的なミティット化戦略を示しています。 その他の例外については、例外に関するドキュメントを参照 [してください](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/)。

Warning

エラー コードとエラー メッセージは参照用にのみ表示されます。 これらに基づいて例外処理戦略を手動で実装することはお勧めしません。代わりに、標準の [MsalException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalexception)ベースのアプローチを使用してください。

| エラー コード | エラー メッセージ | 緩和策 |
| --- | --- | --- |
| 2147943631 | ネットワークの場所に到達できません。 ネットワークのトラブルシューティングについては、「Windows ヘルプ」を参照してください。 | 断続的なエラーが発生する可能性があります。 後でコードを実行して、コンピューターにアクティブなインターネット アクセスがあることを確認してください。 |
| 2147943717 | 指定したアカウントが存在しません。 | WAM で使用されるアカウントが存在することを確認します。 |
| 2148074254 | セキュリティ パッケージ内に使用可能な資格情報がありません |  |
| 2156265477 | サインインする前に、オンライン ID アカウントのプロパティを更新する必要があります。 | 指定したアカウントの場合は、 [アカウントにログイン](https://account.microsoft.com/) して、アカウントが完全に設定されていることを確認します。 |
| 2156265478 | オンライン ID アカウントを保護するには、もう一度サインインする必要があります。 | WAM を使用してターゲット アカウントのサインインを実行します。 |
| 2156265481 | オンライン ID のサインイン名はまだ検証されていません。 サインイン前に電子メールの確認が必要です。 | メールでアカウントを確認し、使用できることを確認します。 |
| 2156265482 | お客様のオンライン ID アカウントで異常なアクティビティが見られます。 他のユーザーがアカウントを使用していないことを確認するには、アクションが必要です。 | [アカウントにログイン](https://account.microsoft.com/) し、アカウントが中断されていないことを確認します。 |
| 2156265483 | オンライン ID アカウントで不審なアクティビティが検出されました。 お客様の保護に役立つよう、アカウントが一時的にブロックされました。 | 選択したアカウントは、現在認証に使用できません。 |
| 2156265484 | 認証にはユーザーの操作が必要です。 | ユーザーを認証するときに、WAM はキャッシュされたトークンを使用できませんでした。 ユーザーは、 [AcquireTokenInteractive](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplication.acquiretokeninteractive)経由で認証を求めるメッセージを表示する必要があります。 |
| 3399548929 | 続行するには、ユーザーの操作が必要です。 | ユーザーを認証するときに、WAM はキャッシュされたトークンを使用できませんでした。 ユーザーは、 [AcquireTokenInteractive](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplication.acquiretokeninteractive)経由で認証を求めるメッセージを表示する必要があります。 |
| 3399614467 | V2Error: invalid\_grant AADSTS500341: ユーザー アカウント {ID} が {TENANT\_ID} ディレクトリから削除されました。 このアプリケーションにサインインするには、そのアカウントがディレクトリに追加されている必要があります。 | ユーザーがサインインを試みるアカウントがMicrosoft Entra IDに登録されていることを確認します。 |
| 3399614476 | V2Error: invalid\_grant AADSTS50078: 管理者によって構成されたポリシーにより、提示された多要素認証の有効期限が切れています。{API\_TARGET} にアクセスするには、多要素認証を更新する必要があります。 | アカウントは、Microsoft Entra 管理者によって最新の MFA 設定で構成されている必要があります。 |
| 2148073494 | Keyset が存在しない内部エラー コード: 545133655 |  |
| 2148073520 | この暗号化プロバイダーに必要なデバイスは、使用する準備ができていません。 内部エラー コード: 545133655 |  |
| 80090016 | NTE\_BAD\_KEYSET | デバイスのトラステッド プラットフォーム モジュール (TPM) に関する問題。 [デバイスの回復手順](https://learn.microsoft.com/ja-jp/microsoft-365/troubleshoot/authentication/connection-issue-when-sign-in-office-2016#manual-recovery)に従って、PC を適切な状態にします。 |

### 一覧に表示されないエラー

WAM は新しいコンポーネントであるため、エラーが発生した場合は、 [`AdditionalExceptionData`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalexception.additionalexceptiondata) からデータをログに記録し、 [バグをログに記録](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/issues)することをお勧めします。 できるだけ早く問題を文書化します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/experimental-features"} -->
## MSAL.NET の試験的特徴 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/experimental-features
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: 地域探索やその他の高度な機能を含む、MSAL.NET の実験的な機能について説明します。

### API promise

MSAL はセマンティック バージョン管理に厳密であり、メジャー バージョンをインクリメントせずに破壊的変更を導入することはありません。

### 試験的な API

MSALs によって公開される新しい API の一部は、 `Experimental`としてマークされます。 これらの API は、上記の約束を果たさずに変更される可能性があります。 そのため、運用環境でこれらの API を使用することはお勧めしませんが、試してみる、フィードバックを提供するなどすることをお勧めします。

MSAL 4.8 以降では、開発者は実験的な機能を使用できるようにフラグを追加する必要があります。それ以外の場合は例外がスローされます。

```csharp
 var pca = PublicClientApplicationBuilder
                .Create(clientId)
                .WithExperimentalFeatues()
                .Build();
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/extensibility-points"} -->
## MSAL.NET 機能拡張ポイント - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/extensibility-points
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: スケーラブルなアプリの MSAL.NET の高度な機能拡張ポイントについて調べる。 HttpClient ファクトリの調整、トークン要求の変更、クエリ パラメーターの挿入などを行います。

MSAL では、"単純なシナリオをシンプルにし、複雑なシナリオを可能にする" という戦略を採用しています。

### 独自の HttpClient を使用する

アプリで、ASP.NET Coreの [IHttpClientFactory](https://learn.microsoft.com/ja-jp/aspnet/core/fundamentals/http-requests?view=aspnetcore-6.0) などの高度にスケーラブルな HttpClient ファクトリを適応できるようにします。 複雑なプロキシ構成に対処する必要があるデスクトップ アプリとモバイル アプリを支援します。 アプリが HTTP メッセージを完全に制御できるようにします。

[IMsalHttpClientFactory](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.imsalhttpclientfactory)の詳細。

### /token 要求を変更する

パラメーターとヘッダーの一覧と実行される URI へのアクセスを提供することで、アプリケーションが `/token` 要求を変更できるようにします。 MSAL がまだサポートしていない新しいフローを試す場合に便利です。

```csharp
public string GetTokenAsync()
{
   var result = await app.AcquireTokenForClient(scope)
         .OnBeforeTokenRequest(ModifyRequestAsync)
         .ExecuteAsync();
 
    // log result.AuthenticationResultMetadata.DurationTotalInMs and other metrics

    return result.Token;
}

private static Task ModifyRequestAsync(OnBeforeTokenRequestData requestData)
{
    requestData.BodyParameters.Add("param1", "val1");
    requestData.BodyParameters.Add("param2", "val2");

    requestData.Headers.Add("header1", "hval1");
    requestData.Headers.Add("header2", "hval2");

    return Task.CompletedTask;
}
   
```

### 追加のクエリ パラメーターを挿入する

アプリでクエリ (GET) パラメーターをアプリケーションに追加し、エクスペリエンスをカスタマイズできるようにします。 これは主に、 `/authorize` エンドポイントによって公開される UX ログイン エクスペリエンスを制御しますが、パラメーターは `/token` エンドポイント要求にも送信されます。

新しい機能やバグ修正が最初にデプロイされるサービス スライスMicrosoft Entraターゲットにしたり、MSAL によって公開されていない機能を使用して UX エクスペリエンスをカスタマイズしたりする場合に便利です。 MSAL は、ASP.NET/ASP.NET Core のシナリオでは`/authorize`要求を実行しないため、これらの呼び出しは影響を受けないことに注意してください。

詳細 [はこちら](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.abstractacquiretokenparameterbuilder-1.withextraqueryparameters?view=azure-dotnet#microsoft-identity-client-abstractacquiretokenparameterbuilder-1-withextraqueryparameters%28system-string%29)

### デスクトップ/モバイル アプリ - ICustomWebUi

Caution

**ICustomWebUi は、セキュリティ リスクと現在のサービスの制限により、運用環境での使用はお勧めしません。また、非推奨のパスにあります。**

このパターンではセキュリティ リスクが発生し、Entra IDクラウド サービスではサポートされません。 カスタム Web UI 実装でネイティブ クライアント リダイレクト URI ( `https://login.microsoftonline.com/common/oauth2/nativeclient` など) を使用するには、通常、ユーザーが URL から承認コードを手動でコピーする必要があります。これは、 `nativeclient` URI で最もよく見られるアンチパターンです。 このパターンは、ほとんどの構成では機能せず、セキュリティ 上のリスクが伴います。

- **推奨される代替手段**:
    - Windows 10+ アプリケーションに**[ブローカー認証 (WAM)](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam) を使用**する - 最高のセキュリティとユーザー エクスペリエンスを提供します
    - [Web ブラウザーの使用](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/using-web-browsers) で説明されているように、**埋め込みブラウザーフローを使用します**

ICustomWebUi では、MSAL によって提供される埋め込み/システム ブラウザーではなく、デスクトップ アプリとモバイル アプリが独自のブラウザーを使用できますが、移行がまだ不可能なテストまたはレガシ シナリオにのみ使用する必要があります。

詳細 [はこちら](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.extensibility.icustomwebui?view=azure-dotnet)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/extract-authentication-parameters"} -->
## WWW-Authenticate ヘッダーから認証パラメーターを抽出する - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/extract-authentication-parameters
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: この記事は、WWW 認証ヘッダーから情報を取得する理由と、その方法の概念に関する記事です。

### Scenarios

#### 保護された Web API に対する認証されていない呼び出し

保護された Web API は、受信要求がリソースへのアクセスを完全に承認されていない場合に、 `HTTP 401 Unauthorized` エラーを送信します。 応答には、チャレンジを含む `WWW-Authenticate` ヘッダーも含まれる場合があります。このリソースへの正しいアクセス トークンを取得する方法を指定する追加情報です。 リソースは、要求に承認トークンが含まれていないか、トークンが無効な場合に、このエラー応答を返すことができます。 Web API は、アクセス トークンが古い場合にもチャレンジを返します (たとえば、ユーザーは多要素認証を使用して再度ログインする必要があります)。 Web API がユーザーとやり取りできない場合は、 `WWW-Authenticate` ヘッダーの情報を使用して要求をクライアントに伝達する必要があります。 クライアント アプリは、これらの要求を抽出し、ID プロバイダーへのトークン要求に含める役割を担います。 これにより、ユーザーが再ログインするための UI がトリガーされます。 その後、ID プロバイダーは、web API への要求に含める必要がある up-to-date アクセス トークンを返します。

[Image: Web API とクライアント間のフロー]

例を表示するには、 `https://graph.microsoft.com/v1.0/me`に移動します。 Microsoft Graphは`HTTP 401 Unauthorized` エラーを返し、`WWW-Authenticate` ヘッダーは、このリソースのトークンとクライアント ID を取得する承認 URI を指定します。

```text
HTTP 401; Unauthorized
WWW-Authenticate: Bearer realm="", authorization_uri="https://login.microsoftonline.com/common/oauth2/authorize", client_id="00000003-0000-0000-c000-000000000000"
```

`https://yourVault.vault.azure.net/secrets/CertName/CertVersion`に移動して、次のようなヘッダーを受け取ります。

```text
HTTP 401; Unauthorized
WWW-Authenticate: Bearer authorization="https://login.windows.net/yourTenantId", resource="https://vault.azure.net"
```

#### 継続的アクセス評価

[継続的アクセス評価](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/concept-continuous-access-evaluation) (CAE) を使用すると、リソースはユーザーとアプリの変更をMicrosoft Entra IDで継続的に追跡し、そのポリシーをタイムリーに更新できます。 変更されたポリシーに基づいて、CAE 対応 Web API は適切な要求チャレンジを含む `WWW-Authenticate` ヘッダーを送信します。 詳細については、 [アプリケーションで継続的アクセス評価が有効な API を使用する方法](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/app-resilience-continuous-access-evaluation)に関するページを参照してください。 `WWW-Authenticate` ヘッダーの形式は次のとおりです。

```text
HTTP 401; Unauthorized
WWW-Authenticate=Bearer
  authorization_uri="https://login.windows.net/common/oauth2/authorize",
  error="insufficient_claims",
  claims="eyJhY2Nlc3NfdG9rZW4iOnsibmJmIjp7ImVzc2VudGlhbCI6dHJ1ZSwgInZhbHVlIjoiMTYwNDEwNjY1MSJ9fX0="
```

#### 条件付きアクセス認証コンテキスト

条件付きアクセス認証コンテキスト (CA 認証コンテキスト) を使用すると、アプリ レベルだけでなく、機密データとアクションにきめ細かいポリシーを適用できます。 CA 認証コンテキストは、 `WWW-Authenticate` ヘッダーを返す Web API にも依存します。 CA 認証コンテキストの詳細については、以下を参照してください。

- [条件付きアクセス認証コンテキストの開発者ガイド](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/developer-guide-conditional-access-authentication-context)
- [Web API で CA 認証コンテキストを使用するコード サンプル](https://github.com/Azure-Samples/ms-identity-ca-auth-context/blob/main/README.md)
- [記録されたセッション: アプリで条件付きアクセス認証コンテキストを使用してステップアップ認証を行う – 2021 年 5 月](https://www.youtube.com/watch?v=_iO7CfoktTY)

CA 認証コンテキスト シナリオで返される `WWW-Authenticate` ヘッダーは、CAE 対応 Web API によって返されるヘッダーと似ています。

```text
HTTP 401; Unauthorized
WWW-Authenticate=Bearer
  client_id="Resource GUID"
  authorization_uri="https://login.windows.net/common/oauth2/authorize",
  error="insufficient_claims",
  claims="eyJhY2Nlc3NfdG9rZW4iOnsibmJmIjp7ImVzc2VudGlhbCI6dHJ1ZSwgInZhbHVlIjoiMTYwNDEwNjY1MSJ9fX0="
```

### コード例

#### MSAL.NET

要求チャレンジを処理するために、クライアント アプリケーションは `insufficient_claims` ヘッダーで`WWW-Authenticate` エラーを検索し、`claims` プロパティを抽出します。 要求値が Base64 でエンコードされている場合は、デコードする必要があります。 要求チャレンジは JSON 形式にする必要があります。 最後に、トークンを取得するときに、この値を [WithClaims(String)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.abstractacquiretokenparameterbuilder-1.withclaims#microsoft-identity-client-abstractacquiretokenparameterbuilder-1-withclaims%28system-string%29) に渡します。 MSAL.NET は、要求の抽出に役立つ[WwwAuthenticateParameters](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.wwwauthenticateparameters) クラスを提供します。

保護された Web API に対して認証されていない呼び出しを行う場合は、リソースの URI を渡 [CreateFromAuthenticationResponseAsync](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.wwwauthenticateparameters.createfromauthenticationresponseasync) 呼び出します。 MSAL はリソースに要求を行い、 `WWW-Authenticate` ヘッダーの値を抽出して返します。

```csharp
WwwAuthenticateParameters parameters = 
    await WwwAuthenticateParameters.CreateFromResourceResponseAsync("https://yourVault.vault.azure.net/secrets/secret/version");

IConfidentialClientApplication app = ConfidentialClientApplicationBuilder.Create(clientId)
    .WithAuthority(parameters.Authority)     
    .Build();

// For details about token caching, see https://aka.ms/msal-net-token-cache-serialization .
app.AppTokenCache.SetCacheOptions(CacheOptions.EnableSharedCacheOptions);

AuthenticationResult authenticationResult = await app.AcquireTokenForClient(new[] {"scope") // You should already know the scope in advance.
    .WithClaims(parameters.Claims)
    .ExecuteAsync();
```

条件付きアクセスまたは継続的アクセス評価をサポートする Web API を呼び出す場合は、 [GetClaimChallengeFromResponseHeaders(HttpResponseHeaders, String)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.wwwauthenticateparameters.getclaimchallengefromresponseheaders#microsoft-identity-client-wwwauthenticateparameters-getclaimchallengefromresponseheaders%28system-net-http-headers-httpresponseheaders-system-string%29) を使用して `HTTP 401 Unauthorized` 応答を処理します。

```csharp
using HttpRequestMessage httpRequestMessage = new HttpRequestMessage(
        new HttpMethod(httpMethod),
        apiUrl);

httpRequestMessage.Headers.Add(
                    "Authorization",
                    authenticationResult.CreateAuthorizationHeader());

HttpResponseMessage httpResponse = await httpClient.SendAsync(httpRequestMessage).ConfigureAwait(false);

if (httpResponse.StatusCode == System.Net.HttpStatusCode.Unauthorized)
{
    string claims = WwwAuthenticateParameters.GetClaimChallengeFromResponseHeaders(httpResponse.Headers);
    // Acquire a new token with these claims
    // Call the web API again with the new token
}

// Handle a successful web API response.
```

#### Microsoft。Identity.Web

[Microsoft.Identity.Web](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/microsoft-identity-web/) を使用する Web API では、[ReplyForbiddenWithWwwAuthenticateHeader(IEnumerable&lt;String&gt;, MsalUiRequiredException, HttpResponse)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.web.itokenacquisition.replyforbiddenwithwwwauthenticateheader#microsoft-identity-web-itokenacquisition-replyforbiddenwithwwwauthenticateheader%28system-collections-generic-ienumerable%28%28system-string%29%29-microsoft-identity-client-msaluirequiredexception-microsoft-aspnetcore-http-httpresponse%29) メソッドを使用して、`WWW-Authenticate` ヘッダーを含む `HTTP 401 Unauthorized` 応答を返します。

Microsoft.Identity.Web によって含まれる情報（標準外のプロパティを含む）:

- 同意 URL (マルチテナント Web API 開発者が、テナントに Web API をインストールするための同意にテナント ユーザーまたは管理者が使用できるリンクを提供するのに役立ちます)
- 請求（請求に対する異議申し立ての場合）
- リソースのスコープ。
- `ProposedAction` (つまり、"同意")
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/high-availability"} -->
## MSAL.NET での高可用性に関する考慮事項 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/high-availability
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: トークン キャッシュ、MSAL 操作の監視、ログ記録、再試行ポリシー、証明書のローテーションなど、MSAL.NET の高可用性に関する考慮事項について説明します。 詳細については、こちらをご覧ください。

クライアント資格情報フローについては、最初に [クライアント資格情報フローのドキュメントを](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/client-credential-flows#ensuring-high-availability-of-your-applications) 参照してください。

### 上位レベルの API を使用する

MSAL は下位レベルの API です。 新しいアプリを作成する場合は、ASP.NET Coreと ASP.NET Classic とのすぐに使用できる高レベルの[`Microsoft.Identitity.Web`](https://github.com/AzureAD/microsoft-identity-web)を使用することを検討してください。

### 最新の MSAL を使用する

最新の MSAL を使用して、バグの修正とパフォーマンスの向上を取得します。 [セマンティック バージョン管理](https://semver.org/) 規則に従います。

また、Web アプリと Web API の上位レベルのライブラリである Microsoft Identity Web を使用する必要があるかどうかを確認することもできます。これは、以下で説明する多くのことを行います。 プラットフォームと制約に応じて最適なソリューションを選択するためのデシジョン ツリーを提案する [MSAL.NET のバージョン](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/choosing-msal-dotnet)の選択を参照してください。

### トークン キャッシュを使用する

**既定の動作:** MSAL はトークンをメモリにキャッシュします。 各 `ConfidentialClientApplication` インスタンスには、独自の内部トークン キャッシュがあります。 メモリ内キャッシュは、オブジェクト インスタンスが破棄された場合や、アプリケーション全体が停止した場合などに失われる可能性があります。

**推薦：** すべてのアプリでトークン キャッシュを保持する必要があります。 Web アプリと Web API では、L1/L2 トークン キャッシュを使用する必要があります。L2 は、スケールを処理するために Redis などの分散ストアです。 デスクトップ アプリでは [、適切なトークン キャッシュのシリアル化戦略を使用する](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-token-cache-serialization?tabs=desktop)必要があります。

Note

Microsoft.Identity.Web を使用している場合、適切なキャッシュ動作が標準で実装されているため、キャッシュについて心配する必要はありません。 Microsoft.Identity.Web は使用していないものの、Web アプリまたは Web API を構築している場合は、[ハイブリッド アプローチ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/choosing-msal-dotnet#when-do-you-use-the-hybrid-model-msalnet-and-microsoft-identity-web)を検討するとよいでしょう

**既定の動作:** MSAL は、ADAL と MSAL の間の移行シナリオ用のセカンダリ ADAL トークン キャッシュを保持します。 ADAL キャッシュ操作は非常に低速です。 **推薦：** ADAL からの移行に関心がない場合は、ADAL キャッシュを無効にします。 これにより、 **大きな** パフォーマンスが向上します。パフォーマンスの測定値 [については、こちらをご覧ください](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/pull/2309)。

アプリを構築するときに [`WithLegacyCacheCompatibility(false)`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.abstractapplicationbuilder-1.withlegacycachecompatibility) を追加して、ADAL キャッシュを無効にします。

### MSAL 操作に関する監視を追加する

MSAL は、 [AuthenticationResult.AuthenticationResultMetadata](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.authenticationresult.authenticationresultmetadata) オブジェクトの一部として重要なメトリックを公開します。

| Metric | Meaning | アラームをトリガーするタイミング |
| --- | --- | --- |
| `DurationTotalInMs` | ネットワーク呼び出しとキャッシュを含む、MSAL で費やされた合計時間 | 全体的な待機時間が長い (&gt; 1 秒) のアラーム。 値はトークン ソースによって異なります。 キャッシュから: 1 つのキャッシュ アクセス。 Microsoft Entra IDから: 2 つのキャッシュ アクセス + 1 つの HTTP 呼び出し。 最初の呼び出し (プロセスごと) は、1 つの余分な HTTP 呼び出しのために時間がかかります。 |
| `DurationInCacheInMs` | トークン キャッシュの読み込みまたは保存に費やされた時間。これはアプリ開発者によってカスタマイズされます (たとえば、Redis に保存)。 | スパイク時のアラーム。 |
| `DurationInHttpInMs` | Microsoft Entra IDへの HTTP 呼び出しの作成に費やされた時間。 | スパイク時のアラーム。 |
| `TokenSource` | トークンのソースを示します。 トークンはキャッシュからはるかに高速に取得されます (たとえば、約 100 ミリ秒と約 700 ミリ秒)。 キャッシュ ヒット率を監視およびアラームするために使用できます。 | `DurationTotalInMs` で使用します。 |
| `CacheRefreshReason` | ID プロバイダーからアクセス トークンを取得する理由を指定します。 「 [可能な値」を参照してください](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.cacherefreshreason)。 | `TokenSource` で使用します。 |

### Logging

MSAL ログから出力される `Warning` レベルおよび `Error` レベルのメッセージを監視します。 これらは、サイレント エラーや、別の構成を使用するための強い推奨事項です。多くのメッセージを生成し、パフォーマンスに影響を与えるので、運用環境で `Verbose` ログ記録を設定することはお勧めしません。

ログ記録の詳細については、MSAL.NET [ガイドの「ログ記録](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-logging-dotnet)」を参照してください。

### 再試行ポリシー

**既定の動作**: MSAL は失敗した 5xx 要求を 1 回再試行します。

**推奨事項**:

- Polly を使用 [して再試行ポリシー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/retry-policy) を記述する場合は、再試行ポリシーのドキュメントを参照してください

### セッションごとに 1 つの Confidential Client

各セッションで新しい `ConfidentialClientApplication` を使用し、同じ方法 (セッションごとに 1 つのトークン キャッシュ) でシリアル化することをお勧めします。 これは適切にスケーリングされ、セキュリティも向上します。 [公式サンプル](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/sample-v2-code)は、これを行う方法を示しています。 これを正しく機能させるには [、トークン キャッシュを構成](https://aka.ms/msal-net-token-cache-serialization) する必要があります。

Note

`Microsoft.Identity.Web` は、このアプローチを適用します。トークン キャッシュが有効になっている要求ごとに 1 つの機密クライアント アプリ インスタンスです。

### Httpクライアント

**既定の動作**: MSAL で作成された `HttpClient` は、Web サイト/Web API では適切にスケーリングされません。ここでは、ユーザー セッションごとに `ClientApplication` オブジェクトを用意することをお勧めします。

**推奨事項**: 独自のスケーラブルな HttpClientFactory を提供します。 .NET Core では、[System.Net.Http.IHttpClientFactory](https://learn.microsoft.com/ja-jp/aspnet/core/fundamentals/http-requests) を依存性注入することをお勧めします。 詳細については、[独自の HttpClient の提供、HTTP プロキシのサポート、ユーザー エージェント ヘッダーのカスタマイズ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/httpclient)に関するガイドと[.NETドキュメントを参照してください](https://learn.microsoft.com/ja-jp/dotnet/api/system.net.http.httpclient#net-framework--mono)。

### プロアクティブ トークンの更新

#### ゴール

有効期間の長いアクセス トークンを発行してアプリケーションの可用性を向上させ、有効期限よりも早く更新されるようにします。

#### 現状

既定では、Microsoft Entra IDは 1 時間の有効期限でアクセス トークンを発行します。 トークンを更新する必要があるときにMicrosoft Entra停止が発生した場合、MSAL は失敗します。 エラーは呼び出し元のアプリケーションに伝達され、可用性に影響します。

#### プロセス

可用性を向上させるために、MSAL はアプリが常に新しい有効なトークンを保持できるようにします。 Microsoft Entraの停止に数時間以上かかることはめったにないため、MSAL がトークンに少なくとも数時間の可用性が常に残っていることを保証できる場合、アプリケーションはMicrosoft Entraの停止の影響を受けなくなります。

有効期間の長いトークンを取得するには、テナントを構成する必要があります (注: 内部Microsoftテナントは既に構成されています)。 client\_credentials (サービス 2 サービス) の場合は、これで十分です。 ユーザー資格情報の場合は、CAE - /azure/active-directory/conditional-access/concept-continuous-access-evaluation も構成する必要があります。

Microsoft Entra IDが有効期間の長いトークンを返すと、`refresh_in` フィールドが含まれます。 通常、アクセス トークンの有効期限の半分に設定されます。

[Image: アクセストークン要求の Fiddler トレース]

注: MSAL 4.37.0 以降では、 `AuthenticationResult.AuthenticationResultMetadata.RefreshOn`を調べることでこの値を確認できます。

さらに、Microsoft ID プラットフォーム [(プレビュー) の構成可能なトークン](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/active-directory-configurable-token-lifetimes)の有効期間の説明に従って、既定の 1 時間を超えるトークンの有効期間を構成できます。

**同じトークンに対して要求を**行うたびに、つまり MSAL がキャッシュからトークンを提供できる場合は常に、MSAL によって`refresh_in`値が自動的にチェックされます。 有効期限が切れている場合、MSAL はバックグラウンドで Microsoft Entra ID にトークン要求を発行しますが、既存の有効なトークンをアプリケーションに返します。 万が一、バックグラウンド更新が失敗した場合 (Microsoft Entraの停止など)、アプリは影響を受けなくなります。

### 証明書のローテーション

機密クライアント アプリの証明書は、セキュリティ上の理由からローテーションする必要があります (prod ではシークレットを使用しないでください)。 最も望ましいものからそうでないものの順に、証明書ローテーションに対処する方法はいくつかあります。

1. マネージド ID の使用

[マネージド ID では](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/managed-identity)、Azureでアプリをホストして信頼が確立されます。 維持するシークレットはなく、ローテーションする証明書もありません。

1. `Microsoft.Identity.Web`証明書処理ロジックを使用する

Web アプリと Web API では、MSAL よりも上位レベルの API `Microsoft.Identity.Web`を使用します。 証明書がAzure Key Vaultに格納されるときに証明書のローテーションを処理し、マネージド ID ケースも処理します。

詳細については、[Microsoft.Identity.Web の証明書](https://github.com/AzureAD/microsoft-identity-web/wiki/Certificates#getting-certificates-from-key-vault) ガイドを参照してください。

これは、ASP.NET Coreを使用するMicrosoft以外の内部サービスに推奨されるソリューションです。

1. (**Microsoft内部のみ**) サブジェクト名/発行者証明書に依存します。

このメカニズムにより、Microsoft Entra IDは拇印 (x5t) ではなく SN/I に基づいて証明書を識別できます。 これはストップギャップソリューションです。Microsoft以外のアプリケーションで使用できるようにする予定はありません。

これは、マネージド ID を使用できない内部サービスMicrosoft推奨されるソリューションです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/httpclient"} -->
## 独自の HttpClient の提供、HTTP プロキシのサポート、ユーザー エージェント ヘッダーのカスタマイズ - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/httpclient
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: 開発者は、プロキシの構成や、ASP.NET Coreの効率的な HttpClient プール方法の使用など、HttpClient インスタンスをきめ細かく制御する必要がある場合があります。

開発者は、プロキシの構成や、`HttpClient`をプールする ASP.NET Coreの効率的な方法の使用など、`HttpClient` インスタンスをきめ細かく制御する必要がある場合があります。 [HttpClientFactory で回復性のある HTTP 要求ドキュメントを実装する方法](https://learn.microsoft.com/ja-jp/dotnet/standard/microservices-architecture/implement-resilient-applications/use-httpclientfactory-to-implement-resilient-http-requests)の詳細を確認できます。 `HttpClient`をカスタマイズするには、開発者が`IMsalHttpClientFactory`を実装する必要があります。この場合、MSAL は各 HTTP 要求の`HttpClient`を取得するために使用します。

### IMsalHttpClientFactory の実装ガイドライン

- ASP.NET Coreの[System.Net.Http.HttpClient](https://learn.microsoft.com/ja-jp/dotnet/api/system.net.http.httpclient)など、このインターフェイスに適合できるスケーラブルな`IHttpClientFactory`例については、を参照してください。
- 実装はスレッド セーフである必要があります。
- `HttpClient`に新しい`GetHttpClient`を作成しないでください。これによりポートが枯渇します。
- MSAL は、`Dispose()`で`HttpClient`を呼び出しません。
- アプリ[で統合Windows認証を](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/integrated-windows-authentication)使用している場合は、[System.Net.Http.HttpClientHandler.UseDefaultCredentials](https://learn.microsoft.com/ja-jp/dotnet/api/system.net.http.httpclienthandler.usedefaultcredentials#system-net-http-httpclienthandler-usedefaultcredentials)が `true` に設定されていることを確認します。

### 実装例

```csharp
IMsalHttpClientFactory httpClientFactory = new MyHttpClientFactory();

var pca = ConfidentialClientApplication.Create("client_id") 
                                        .WithHttpClientFactory(httpClientFactory)
                                        .Build();
```

`IMsalHttpClientFactory` の簡単な実装

```csharp
public class StaticClientWithProxyFactory : IMsalHttpClientFactory
{
    private static readonly HttpClient s_httpClient;

    static StaticClientWithProxyFactory()
    {
        var webProxy = new WebProxy(
            new Uri("http://my.proxy"),
            BypassOnLocal: false);

        webProxy.Credentials = new NetworkCredential("user", "pass");

        var proxyHttpClientHandler = new HttpClientHandler
        {
            Proxy = webProxy,
            UseProxy = true,
        };

        s_httpClient = new HttpClient(proxyHttpClientHandler);
        
    }

    public HttpClient GetHttpClient()
    {
        return s_httpClient;
    }
}
```

### HttpClient と Xamarin iOS

Xamarin iOS を使用する場合は、iOS 7 以降の`HttpClient` ベースのハンドラーを明示的に使用する`NSURLSession`を作成することをお勧めします。 MSAL.NET は、iOS 7 以降では `NSURLSessionHandler` を使用する `HttpClient` を自動的に作成します。 詳細については、[httpClient の Xamarin iOS ドキュメントを](https://learn.microsoft.com/ja-jp/xamarin/cross-platform/macios/http-stack)参照してください。

### Troubleshooting

**問題**: デスクトップ アプリケーションでは、承認エクスペリエンスで定義した HttpClient が使用されない

**解決方法:**

デスクトップ アプリとモバイル アプリでは、MSAL によってブラウザーが開き、承認 URL に移動します。 組み込みブラウザーを使用する場合は、HttpClient を使用しません。次の手法に従ってプロキシを制御できます。https://blogs.msdn.microsoft.com/jpsanders/2011/04/26/how-to-set-the-proxy-for-the-webbrowser-control-in-net/これは、システム ブラウザーのみが使用できる .NET Core では作成できません。 MSAL は、システム ブラウザーを制御しません。

**問題**: ブラウザーがプロキシに接続できますが、MSAL から HTTP 407 エラーが発生する

**解決策**: HTTP 407 にプロキシ認証の問題が表示されます。 .NET フレームワークでは、IE のプロキシ設定が使用されます。既定では、"UseDefaultCredential" 設定は含まれません。 一部のユーザーは、.config ファイルに以下を追加して、この問題の修正を報告しています。

```xml
<system.net>
        <defaultProxy enabled="true" useDefaultCredentials="true" />  
</system.net>
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/managed-identity"} -->
## MSAL.NET を使用したマネージド ID - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/managed-identity
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: MSAL.NET アプリケーションでAzureマネージド ID を使用する方法。

Note

この機能は、[MSAL.NET](https://www.nuget.org/packages/Microsoft.Identity.Client/) バージョン 4.54.0 以降で使用できます。

開発者にとって一般的な課題は、サービス間の通信をセキュリティで保護するために使用されるシークレット、資格情報、証明書、およびキーの管理です。 Azureの[マネージド ID](https://learn.microsoft.com/ja-jp/azure/active-directory/managed-identities-azure-resources/overview) により、開発者がこれらの資格情報を手動で処理する必要がなくなります。 MSAL.NET では、次のようなAzureインフラストラクチャ内で実行されているアプリケーションで使用する場合、マネージド ID サービスを介したトークンの取得がサポートされます。

- [Azure App Service](https://azure.microsoft.com/products/app-service/) (API バージョン `2019-08-01` 以降)
- [Azure VM](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)
- [Azure Arc](https://learn.microsoft.com/ja-jp/azure/azure-arc/overview)
- [Azure クラウド シェル](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview)
- [Azure Service Fabric](https://learn.microsoft.com/ja-jp/azure/service-fabric/service-fabric-overview)

完全な一覧については、[マネージド ID を使用して他のサービスにアクセスできる](https://learn.microsoft.com/ja-jp/azure/active-directory/managed-identities-azure-resources/managed-identities-status)サービスAzureを参照してください。

### どの SDK を使用するか - Azure SDK または MSAL?

MSAL ライブラリは、OAuth2 プロトコルと OIDC プロトコルに近い下位レベルの API を提供します。

MSAL.NET と[Azure SDK](https://learn.microsoft.com/ja-jp/dotnet/api/overview/azure/identity-readme?view=azure-dotnet&preserve-view=true)の両方で、マネージド ID を介してトークンを取得できます。 内部的には、Azure SDKは MSAL.NET を使用し、`DefaultAzureCredential`と`ManagedIdentityCredential`抽象化を介して上位レベルの API を提供します。

アプリケーションでいずれかの SDK が既に使用されている場合は、引き続き同じ SDK を使用します。 新しいアプリケーションを作成し、他のAzure リソースを呼び出す予定の場合は、Azure SDKを使用します。この SDK では、マネージド ID が存在しないプライベート開発者マシンでアプリを実行できるようにすることで、開発者エクスペリエンスが向上します。 Microsoft Graphや独自の Web API などの他のダウンストリーム Web API を呼び出す必要がある場合は、MSAL の使用を検討してください。

Note

[Microsoft。Identity.Web](https://github.com/AzureAD/microsoft-identity-web) は、内部で MSAL を使用しながら、ASP.NET Coreと ASP.NET Classic との統合を提供する上位レベルの API です。 ライブラリには、MSAL.NET によって使用される資格情報 (証明書、署名付きアサーション) をクライアント資格情報として読み込む方法も用意されています。 証明書の場合、 `DefaultAzureCredentials` を使用して KeyVault から証明書をフェッチします。 また、マネージド ID 資格情報を使用したワークロード ID フェデレーションも提供されます。 詳細については、「 [CredentialDescription」](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.abstractions.credentialdescription.keyvaulturl?view=msal-model-dotnet-latest#microsoft-identity-abstractions-credentialdescription-keyvaulturl&preserve-view=true)を参照してください。

### 簡単スタート

Azure Managed Identity をすぐに使い始めて実際の動作を確認するには、このためにチームが用意したサンプルのいずれかを使用できます。

### マネージド ID の使用方法

開発者が使用できるマネージド ID には、**システム割り当てとユーザー割り当ての** 2 種類があります。 違いの詳細については、 [マネージド ID の種類](https://learn.microsoft.com/ja-jp/azure/active-directory/managed-identities-azure-resources/overview#managed-identity-types) に関する記事を参照してください。 MSAL.NET では、両方を使用したトークンの取得がサポートされています。 [MSAL.NET ログ](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-logging-dotnet)記録を使用すると、要求と関連メタデータを追跡できます。

MSAL.NET のマネージド ID を使用する前に、開発者は、Azure CLIまたはAzure portalで使用するリソースに対して有効にする必要があります。

### 例示

ユーザー割り当て ID とシステム割り当て ID の両方で、開発者は [ManagedIdentityApplicationBuilder](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.managedidentityapplicationbuilder) クラスを使用できます。

#### システム割り当てのマネージド ID

システム割り当てマネージド ID の場合、開発者は、 [IManagedIdentityApplication](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.imanagedidentityapplication)のインスタンスを作成するときに追加情報を渡す必要はありません。これは、割り当てられた ID に関する関連メタデータが自動的に推論されるためです。

[AcquireTokenForManagedIdentity(String)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.imanagedidentityapplication.acquiretokenformanagedidentity#microsoft-identity-client-imanagedidentityapplication-acquiretokenformanagedidentity%28system-string%29) は、 `https://management.azure.com`などのトークンを取得するためにリソースと共に呼び出されます。

```csharp
IManagedIdentityApplication mi = ManagedIdentityApplicationBuilder.Create(ManagedIdentityId.SystemAssigned)
    .Build();

AuthenticationResult result = await mi.AcquireTokenForManagedIdentity(resource)
    .ExecuteAsync()
    .ConfigureAwait(false);
```

#### ユーザー割り当て済みマネージド ID

ユーザー割り当てマネージド ID の場合、開発者は、 [IManagedIdentityApplication](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.imanagedidentityapplication)を作成するときに、クライアント ID、完全なリソース識別子、またはマネージド ID のオブジェクト ID を渡す必要があります。

システム割り当てマネージド ID の場合と同様に、 [AcquireTokenForManagedIdentity(String)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.imanagedidentityapplication.acquiretokenformanagedidentity#microsoft-identity-client-imanagedidentityapplication-acquiretokenformanagedidentity%28system-string%29) はリソースと共に呼び出され、 `https://management.azure.com`などのトークンを取得します。

```csharp
IManagedIdentityApplication mi = ManagedIdentityApplicationBuilder.Create(ManagedIdentityId.WithUserAssignedClientId(clientIdOfUserAssignedManagedIdentity))
    .Build();

AuthenticationResult result = await mi.AcquireTokenForManagedIdentity(resource)
    .ExecuteAsync()
    .ConfigureAwait(false);
```

### Caching

既定では、MSAL.NET はメモリ内キャッシュをサポートします。 MSAL では、分散キャッシュを使用する際のセキュリティ上の懸念があるため、マネージド ID のキャッシュ拡張はサポートされていません。 マネージド ID 用に取得されたトークンはAzure リソースに属しているため、分散キャッシュを使用すると、キャッシュを共有する他のAzure リソースに公開される可能性があります。

### Troubleshooting

失敗した要求の場合、エラー応答には、さらに診断とログ分析に使用できる関連付け ID が含まれています。 MSAL.NET で生成された、または MSAL に渡された相関 ID は、サーバー エラー応答で返されるものとは異なることに注意してください。これは、MSAL.NET では相関 ID をマネージド ID のトークン取得エンドポイントに渡せないためです。

#### 潜在的なエラー

##### `MsalServiceException` エラー コード: `managed_identity_failed_response` エラー メッセージ: AAD トークンのフェッチ中に予期しないエラーが発生しました

この例外は、トークンを取得しようとしているリソースがサポートされていないか、間違ったリソース ID 形式を使用して提供されていることを意味する可能性があります。 正しいリソース ID 形式の例としては、 `https://management.azure.com/.default`、 `https://management.azure.com`、 `https://graph.microsoft.com`などがあります。

##### `MsalServiceException` エラー コード: `managed_identity_unreachable_network`。

この例外は、MSAL.NET がマネージド ID のトークンの取得をサポートしていないリソースを使用しているか、マネージド ID のトークンを取得するエンドポイントが到達できない開発マシンからサンプル コードを実行している可能性があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/monitoring"} -->
## MSAL.NET を使用したアプリケーションの監視 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/monitoring
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: MSAL.NET によって提供されるメトリックを使用してアプリケーションを監視する方法。

MSAL.NET を使用した認証サービスが正しく実行されていることを確認するために、MSAL には、運用環境で発生する前に問題を特定して対処できるように、その動作を監視するさまざまな方法が用意されています。 MSAL の不適切な使用は (トークンのライフサイクルとキャッシュに関連するため) すぐに失敗することはありませんが、アプリが一定期間運用環境に入った後、トラフィックの多いシナリオではバブルアップすることがあります。

たとえば、 [機密クライアント アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-applications#public-client-and-confidential-client-authorization) のインスタンスが 1 つだけ使用され、MSAL がトークン キャッシュをシリアル化するように構成されていない場合、キャッシュは永久に拡張されます。 もう 1 つの問題は、新しい機密クライアント アプリケーションを作成し、キャッシュを使用しない場合に発生します。これにより、ID プロバイダーからの調整などの問題が発生します。 MSAL を適切に利用する方法に関する推奨事項については、「 [高可用性](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/high-availability)」を参照してください。

### Logging

MSAL が運用環境の問題に対処するために提供するツールの 1 つは、MSAL が正しく構成されていないときにエラーをログに記録することです。 ログでエラーを監視し、問題のあるイベントの診断に役立つログを可能な限り有効にすることが重要です。 詳細については、「[MSAL.NET ログイン」](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/msal-logging)を参照してください。

次のエラーが MSAL に記録されます。

- `/common` または `/organizations` で終わる Authority を、クライアント資格情報認証 ([AcquireTokenForClient(IEnumerable&lt;String&gt;)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.confidentialclientapplication.acquiretokenforclient#microsoft-identity-client-confidentialclientapplication-acquiretokenforclient%28system-collections-generic-ienumerable%28%28system-string%29%29%29)) に使用する場合。
    - 現在の機関は、推奨されない `/common` または `/organizations` エンドポイントをターゲットにしています。 詳細については、 [クライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/client-credential-flows) を参照してください。
- 機密クライアント アプリケーションの使用中に既定の内部トークン キャッシュが使用される場合。
    - MSAL によって提供される既定のトークン キャッシュは、機密性の高いクライアント アプリケーションで使用する場合にパフォーマンスを発揮するようには設計されていません。 詳細については、[MSAL.NET のトークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization?tabs=aspnetcore)に関するページを参照してください。

### Metrics

MSAL では、ログに加えて、 [AuthenticationResult.AuthenticationResultMetadata](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.authenticationresult.authenticationresultmetadata#microsoft-identity-client-authenticationresult-authenticationresultmetadata)で重要なメトリックが公開されます。 詳細については、 [MSAL 操作に関する監視の追加](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/high-availability#add-monitoring-around-msal-operations) を参照してください。

- [DurationTotalInMs](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.authenticationresultmetadata.durationtotalinms#microsoft-identity-client-authenticationresultmetadata-durationtotalinms) - ネットワーク呼び出しやキャッシュ操作を含む、トークンの取得に MSAL で費やされた合計時間。 全体的な待機時間 (1 秒を超える) に関するアラートを作成します。 通常、初めてのトークン取得呼び出しでは、追加の HTTP 呼び出しが行われます。
- [DurationInCacheInMs](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.authenticationresultmetadata.durationincacheinms#microsoft-identity-client-authenticationresultmetadata-durationincacheinms) - トークン キャッシュの読み込みまたは保存に費やされた時間。これはアプリ開発者によってカスタマイズされます (たとえば、Redis に保存)。 急増のアラートを作成します。

    Note

    トークン キャッシュをカスタマイズする方法については、[MSAL.NET でのトークン キャッシュのシリアル化に関](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-token-cache-serialization)するページを参照してください。
- [DurationInHttpInMs](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.authenticationresultmetadata.durationinhttpinms#microsoft-identity-client-authenticationresultmetadata-durationinhttpinms) - ID プロバイダーへの HTTP 呼び出しの作成に費やされた時間。 急増時のアラートを作成します。
- [TokenSource](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.authenticationresultmetadata.tokensource#microsoft-identity-client-authenticationresultmetadata-tokensource)- トークンのソース (通常はキャッシュまたは ID プロバイダー) を示します。 トークンはキャッシュからはるかに高速に取得されます (たとえば、約 100 ミリ秒と約 700 ミリ秒)。 このメトリックを使用して、キャッシュ ヒット率を監視できます。
- [CacheRefreshReason](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.authenticationresultmetadata.cacherefreshreason#microsoft-identity-client-authenticationresultmetadata-cacherefreshreason) - ID プロバイダーからアクセス トークンをフェッチする理由を指定します。 [CacheRefreshReason](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.cacherefreshreason)を参照してください。 `TokenSource`と組み合わせて使用します。
- [TokenEndpoint](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.authenticationresultmetadata.tokenendpoint#microsoft-identity-client-authenticationresultmetadata-tokenendpoint) - トークンのフェッチに使用される実際のトークン エンドポイント URI。 MSAL がサイレント呼び出しでテナントをどのように特定し、リージョン指定の呼び出しでリージョンをどのように特定するかを理解するのに役立ちます。

    Note

    地域化は、内部Microsoft アプリケーションでのみ使用できます。
- [RegionDetails](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.authenticationresultmetadata.regiondetails#microsoft-identity-client-authenticationresultmetadata-regiondetails) - 使用されているリージョンや自動検出エラーなど、呼び出しに使用されるリージョンに関する詳細。

    Note

    地域化は、内部Microsoft アプリケーションでのみ使用できます。

### OpenTelemetry

MSAL 4.58.0 以降、ライブラリでは [OpenTelemetry](https://opentelemetry.io/) がサポートされています。これは、一貫性のある標準化された方法でテレメトリ データのインストルメンテーション、生成、収集を可能にする一連の API です。 作業を開始するには、次のことを確認します。

1. 最新バージョンの [MSAL.NET](https://www.nuget.org/packages/Microsoft.Identity.Client) をインストールします。
2. [OpenTelemetry](https://www.nuget.org/packages/OpenTelemetry#readme-body-tab) パッケージの依存関係をプロジェクトに追加します。
3. ログをエクスポートできるエクスポーターの依存関係を追加します。たとえば、[OpenTelemetry.NET の Console exporter](https://www.nuget.org/packages/OpenTelemetry.Exporter.Console/1.7.0-alpha.1) です。

Note

コンソール エクスポーターはローカル デバッグと診断に適していますが、運用環境でデプロイされたアプリケーションには最適な選択肢ではありません。 使用可能なオプションの詳細については、 [公式の輸出者のドキュメント](https://opentelemetry.io/docs/instrumentation/net/exporters/) を参照することをお勧めします。 Azureでアプリケーションをホストしている場合は、Azure Data Explorerまたは[Azure Monitor](https://learn.microsoft.com/ja-jp/azure/data-explorer/open-telemetry-connector?tabs=command-line)に OpenTelemetry データを取り込することを検討してください。

アプリケーション初期化コードでは、MSAL 認証クライアント ( [PublicClientApplication](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplication) や [ConfidentialClientApplication](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.confidentialclientapplication) など) をブートストラップする前に、次のコードを使用して新しい [`MeterProvider`](https://opentelemetry.io/docs/specs/otel/metrics/api/#meterprovider) インスタンスを宣言します。

```csharp
using var meterProvider = Sdk.CreateMeterProviderBuilder()
    .AddMeter("MicrosoftIdentityClient_Common_Meter")
    .AddConsoleExporter()
    .Build();
```

これにより、メーター プロバイダーが初期化され、一連のカウンターとヒストグラムをキャプチャする組み込みの MSAL.NET メーター (`MicrosoftIdentityClient_Common_Meter`) が使用されます。 コンソール エクスポーターを使用すると、出力がターミナルで直接パイプ処理されていることがわかります。

[Image: ターミナルにメトリックを出力する OpenTelemetry の例]

次のセクションでは、既定のメーターでサポートされているカウンターとヒストグラムについて説明します。

#### カウンタ

##### `msalsuccess_counter`

MSAL で成功したリクエストの集計を取得するためのカウンター。

###### Metadata

| フィールド | 説明 |
| --- | --- |
| `MsalVersion` | 使用される MSAL のバージョン。 |
| `Platform` | 使用された .NET SKU |
| `ApiId` | トークンの取得に使用される API の ID。 |
| `TokenSource` | トークンのソース (ID プロバイダーやキャッシュなど)。 |
| `CacheRefreshReason` | キャッシュ更新の理由。 |
| `CacheLevel` | カスタム キャッシュが使用されていてもレベルが記録されない場合は、L1、L2、または不明。 |

##### `msalfailure_counter`

MSAL で失敗した要求の集計をキャプチャするカウンター。

###### Metadata

| フィールド | 説明 |
| --- | --- |
| `MsalVersion` | 使用される MSAL のバージョン。 |
| `Platform` | 使用された.NET SKU。 |
| `ErrorCode` | `MsalServiceException` の場合は Microsoft Entra ID のエラー コード、`MsalErrorCode` の場合は `MsalClientException`、`MsalException` でない場合は例外名を指定します。 |
| `ApiId` | トークンの取得に使用される API の ID。 |
| `CacheRefreshReason` | キャッシュ更新の理由。 |

#### ヒストグラム

##### `MsalTotalDuration_1a_histogram`

MSAL を使用したトークン取得の合計待機時間をミリ秒単位でキャプチャするヒストグラム。

###### Metadata

| フィールド | 説明 |
| --- | --- |
| `MsalVersion` | 使用される MSAL のバージョン。 |
| `Platform` | 使用された .NET SKU。 |
| `ApiId` | トークンの取得に使用される API の ID。 |
| `CacheLevel` | カスタム キャッシュが使用されていてもレベルが記録されない場合は、L1、L2、または不明。 |
| `TokenSource` | トークンのソース (ID プロバイダーやキャッシュなど)。 |
| `CacheRefreshReason` | キャッシュ更新の理由。 |

##### `MsalDurationInL1CacheInUs_1b_histogram`

L1 キャッシュが使用されたときの待機時間をキャプチャするヒストグラム。 MSAL を使用したトークン取得の値はマイクロ秒単位です。

###### Metadata

| フィールド | 説明 |
| --- | --- |
| `MsalVersion` | 使用される MSAL のバージョン。 |
| `Platform` | 使用された .NET SKU。 |
| `ApiId` | トークンの取得に使用される API の ID。 |
| `CacheLevel` | カスタム キャッシュが使用されていてもレベルが記録されない場合は、L1、L2、または不明。 |
| `TokenSource` | トークンのソース (ID プロバイダーやキャッシュなど)。 |
| `CacheRefreshReason` | キャッシュ更新の理由。 |

##### `MsalDurationInL2Cache_1a_histogram`

MSAL を使用したトークン取得の L2 キャッシュ待機時間をミリ秒単位でキャプチャするヒストグラム。

###### Metadata

| フィールド | 説明 |
| --- | --- |
| `MsalVersion` | 使用される MSAL のバージョン。 |
| `Platform` | .NET SKU を使用。 |
| `ApiId` | トークンの取得に使用される API の ID。 |
| `CacheRefreshReason` | キャッシュ更新の理由。 |

##### `MsalDurationInHttp_1a_histogram`

MSAL を使用したトークン取得の HTTP 待機時間をミリ秒単位でキャプチャするヒストグラム。

###### Metadata

| フィールド | 説明 |
| --- | --- |
| `MsalVersion` | 使用される MSAL のバージョン。 |
| `Platform` | 使用された .NET SKU。 |
| `ApiId` | トークンの取得に使用される API の ID。 |

#### 追加情報

.NET アプリケーションでの OpenTelemetry の使用の詳細については、[OpenTelemetry での.NET可観測性](https://learn.microsoft.com/ja-jp/dotnet/core/diagnostics/observability-with-otel)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/multicloud-support-instance-awareness"} -->
## マルチクラウドのサポートとインスタンス認識 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/multicloud-support-instance-awareness
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: インスタンス認識機能は、環境の既定値を使用して、任意のクラウドのアカウントをサインインできるシナリオを完了するのに役立ちます。

Warning

この機能は、すべてのクラウドで同じクライアント ID を持つファースト パーティ アプリケーション (Microsoft アプリケーション) でのみ使用できます。 サード パーティ製アプリケーションは、クラウドごとに異なるクライアント ID を持ち、この機能を使用することはできません。

### インスタンス認識とは

- インスタンス認識機能は、環境の既定値を使用して、任意のクラウドのアカウントをサインインできるシナリオを完了するのに役立ちます。 インスタンス認識がアクティブ化されていない場合、呼び出し元アプリはアカウントに適切な環境を提供する必要があります。
- これにより、アプリケーションは既定のパブリック クラウド機関をライブラリに渡すことができます。また、国内クラウドからリソース (Graph) のトークンを取得することもできます。
- ユーザーとリソースは、単一の国内クラウドに属している必要があります。
- これは、テナント URL ではなく、 `/organizations` または `/common` 機関 URL を使用する場合にのみ適用されます。

### MSAL でマルチクラウド サポートを有効にするとはどういう意味ですか?

マルチクラウド サポートを有効にすると、ユーザーはグローバル機関を使用して `PublicClientApplication` を作成できます。また、ユーザーが国内クラウドからユーザー名を入力すると、MSAL は国内クラウド上のリソースにアクセスするためのトークンを返します。

現時点では、トークンを対話形式で取得するときに、マルチクラウド のサポートを利用できます。

### マルチクラウド サポートを有効にするサンプル

```csharp
    IPublicClientApplication pca = PublicClientApplicationBuilder
        .Create(AppId)
        .WithAuthority("https://login.microsoftonline.com/common")
        .WithMultiCloudSupport(true)
        .Build();

    // Acquire a token interactively
    AuthenticationResult result = await pca
        .AcquireTokenInteractive(s_scopes)
        .ExecuteAsync()
        .ConfigureAwait(false);

    // Get accounts
    var accounts = await pca.GetAccountsAsync().ConfigureAwait(false);

    // Acquire a token silently
    result = await pca
        .AcquireTokenSilent(s_scopes, accounts.FirstOrDefault()) \\ Use the account to make the silent call
        .ExecuteAsync(CancellationToken.None)
        .ConfigureAwait(false);
```

Note

トークンの取得に使用される環境は、 `account.Environment` を使用して、各国のクラウド上の各リソース エンドポイントへのマッピングを作成するために見つけることができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/performance-testing"} -->
## MSAL.NET のパフォーマンス テスト - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/performance-testing
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: BenchmarkDotNet を使用した MSAL.NET のパフォーマンス テストについて調べる。 テストの実行、結果の表示、テストの自動化、MSAL.NET パフォーマンスの向上について説明します。

[Microsoft。Identity.Test.Performance](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/tree/main/tests/Microsoft.Identity.Test.Performance) プロジェクトでは、MSAL 機能のパフォーマンス テストに [BenchmarkDotNet](https://benchmarkdotnet.org/articles/overview.html) ライブラリを使用します。 [Program.cs](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/blob/main/tests/Microsoft.Identity.Test.Performance/Program.cs) には、さまざまなシナリオをテストするために使用されるベンチマーク クラスが含まれています。

このパフォーマンス テスト プロジェクトはコンソール アプリです。 プロジェクトが実行されると、バックグラウンドで BenchmarkDotNet によってこのテスト プロジェクトがビルドされ、一時的な作業ディレクトリに出力されます。 その後、すべてのベンチマーク測定が行われる別のプロセスが作成されます。

BenchmarkDotNet はカスタマイズ可能です。 BenchmarkDotNet テストは、属性を使用して、単体テストと同様に設定されます。 ベンチマークはパラメーター化できます。 実際のテストを実行する前に、環境のセットアップに使用できるグローバルおよびイテレーションの [セットアップとクリーンアップ](https://benchmarkdotnet.org/articles/features/setup-and-cleanup.html) の方法があります。 ベンチマークを実行する必要がある回数はカスタマイズできますが、既定の値を使用することをお勧めします。BenchmarkDotNet では、最適な実行数を見つけるための独自の前処理が行われます。 [動作](https://benchmarkdotnet.org/articles/guides/how-it-works.html) ガイドでは、BenchmarkDotNet がベンチマークを実行するために実行する手順について説明します。 BenchmarkDotNet では、複数のフレームワークでのテストの実行 [がサポートされています](https://benchmarkdotnet.org/articles/configs/toolchains.html#multiple-frameworks-support)。

### テストの実行

テストをローカルで実行する方法は複数あります。

1 つの方法:

- `Release` モードで Microsoft.Client.Test.Performance プロジェクトをビルドします。
- `{project directory}/bin/Release/{framework directory}/`に移動し、プロジェクトの実行可能ファイルを実行します。
- 結果がコンソール ウィンドウに出力されます。

別の方法:

- プロジェクト ディレクトリに移動します。
- コンソール ウィンドウで `dotnet run -c Release` を実行します。

`BenchmarkDotNet.Artifacts` エクスポートされた結果を含むフォルダーは、実行可能ファイルの実行元のディレクトリに作成されます。

テスト プロジェクトは、上記のメソッドを使用して複数回実行し、結果を手動で集計できます。 プロジェクトを複数回実行するもう 1 つの方法は、BenchmarkDotNet ジョブを設定するときに`WithLaunchCount(this Job job, int count)`に`Program.cs`メソッドを追加することです。 これにより、BenchmarkDotNet がベンチマーク プロセスを起動する回数が指定されます。 これは、テストの実行間のばらつきを減らすのに役立ちます。

#### コード変更のテスト

パフォーマンスクリティカルなコードにコードを変更する場合は、必ずテストを実行して回帰を確認してください。

ローカルでテストするには:

- "before" コード状態でパフォーマンス プロジェクトをビルドして実行し、ベースライン番号を確立します。
- 必要な MSAL コードを変更します。
- もう一度 perf プロジェクトをビルドして実行し、'after' 状態の結果を取得します。
- 実行間の結果を比較します。
- 前と後の結果を、これらの変更を含むプル要求に含めます。 また、以下の「改善とテスト結果」セクションの PR と改善点についても説明します。

比較は、ビルド パイプラインを使用して行うこともできます。 新しい変更が加わった機能ブランチで自動テストを実行するだけです。 メイン ブランチでの実行の以前の結果と結果を比較します。

#### 結果の表示

概要結果を含むサンプル テーブル:

| Method | CacheSize | キャッシュのシリアル化を有効にする | 平均 | 第0世代 | 割り当て済 |
| --- | --- | --- | --- | --- | --- |
| AcquireTokenForClient | ? | ? | 261.408 マイクロ秒 | - | 69.58 KB |
| AcquireTokenForClient | (1, 10) | False | 16.461 マイクロ秒 | 0.8850 | 22.13 KB |
| AcquireTokenForClient | (1, 10) | True | 163.788 マイクロ秒 | 10.9863 | 271.28 KB |
| AcquireTokenForClient | (10000, 10) | False | 33.558 マイクロ秒 | 0.8545 | 22.14 KB |
| AcquireTokenForClient | (10000, 10) | True | 176.403 マイクロ秒 | 10.9863 | 271.28 KB |
| AcquireTokenForClient | (1, 10) | False | 16.461 マイクロ秒 | 0.8850 | 22.13 KB |
| AcquireTokenForClient | (1, 10) | True | 163.788 us | 10.9863 | 271.28 KB |
| AcquireTokenForClient | (1, 1000) | False | 226.126 マイクロ秒 | 5.1270 | 130.85 KB |
| AcquireTokenForClient | (1, 1000) | True | 25,559.469 us | 1093.7500 | 18362.87 KB |

結果は、すべてのイテレーションと起動にわたって統合されます。 これらは、実行の最後にコンソールに書き込まれ、既定で`.md` フォルダー内の`.csv`、`.html`、および`BenchmarkDotNet.Artifacts` ファイルにもエクスポートされます。 結果は、ベンチマーク メソッドと任意のパラメーターによってグループ化されます。 注意が必要な主なメトリックは、平均速度と割り当てられたメモリです。 コードが変更される前と後の実行間でこれらの値を比較します。 実行ログには、ベンチマークが実行された回数と一般的なデバッグ情報が含まれ、同じフォルダーにもエクスポートされます。

### テスト自動化

上記のテストを実行するプロセスは、2 つの方法で自動化されます。 テスト スイートは、変更がメイン ブランチにマージされるときに、Azure DevOps ビルド パイプラインの一部として実行され、機能ブランチで手動で実行することもできます。 現在、これらの結果は手動で比較する必要があります。 テスト プロジェクトは、メイン ブランチへのマージまたは手動での[継続的ベンチマーク](https://github.com/marketplace/actions/continuous-benchmark) GitHub アクションによっても実行されます。 指定したしきい値を超えて回帰が検出された場合、アクションは失敗します。 テストの実行結果は、GitHub Pages [ダッシュボード](https://azuread.github.io/microsoft-authentication-library-for-dotnet/benchmarks/)にアップロードされます。

### テスト事例

テストでは、一般的な MSAL の使用シナリオについて説明します。

- **メソッド** - エンドツーエンドのトークン取得 (クライアント、on-behalf-of、サイレント) またはその他の操作 (アカウントの取得、アカウントの削除)。 焦点は、高スループット アプリケーションでよく使用されるメソッドをテストすることです。 JSON 操作やビルダー テストなど、必要に応じて実行できる他のテストもあります。
- **トークン キャッシュ サイズ** - キャッシュに事前設定されているトークンの数。 内部トークン キャッシュは、キーと値のペアにパーティション分割されます。 上の表のキャッシュ サイズ列には、 `(number of keys/partitions, number of tokens per cache entry/partition/key)`として表示されます。 さまざまなシナリオでのキャッシュ キーの詳細については、 [こちらを](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/blob/main/src/client/Microsoft.Identity.Client/TokenCacheNotificationArgs.cs#L188-L191)参照してください。
    - 最初に取り上げるシナリオは、キャッシュが空の場合です。そのため、要求は HTTP マネージャーに至ります (テストでは Web 呼び出しがモックされます)。
    - 単純なシナリオでは、各テナントまたは 1 人のユーザーがアクセスするリソースが少ないなど、トークンが少ないキャッシュ エントリはほとんどありません。
    - より一般的なシナリオは、それぞれにトークンが少ない多数のキャッシュ エントリです。 また、トークンを検索するフィルター処理操作は O(n) 時間で取得される特定のキャッシュ エントリでのみ実行されるため、キャッシュ エントリ/キーの数がパフォーマンスに大きく影響しないことを示します (内部.NET操作以外)。
    - あまり一般的ではないシナリオは、キャッシュ エントリごとに多数のトークン (通常はクライアント資格情報アプリ トークン用) です。
- **有効なトークン キャッシュのシリアル化** - キャッシュのシリアル化が有効か無効か ( [ドキュメント](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-token-cache-serialization?tabs=aspnetcore#monitor-cache-hit-ratios-and-cache-performance)で説明)。 無効にした場合、MSAL は内部メモリ内キャッシュを使用し、前述のようにトークンが事前に設定されます。 有効にすると、MSAL は、.NET メモリ キャッシュ構造に事前設定してシリアル化します。 シリアル化によって、これら 2 つのオプションの間にパフォーマンス ヒットが追加されます。

### 機能強化とテスト結果

MSAL.NET パフォーマンスの向上に定期的に取り組む。 パフォーマンス関連のGitHubの問題には、[パフォーマンス](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/labels/performance) ラベルが付けられます。 最近の主な機能強化の一部を示します。 パフォーマンス データの pull request を参照してください。

**PR [#2261](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/pull/2261)** には、特に内部トークン キャッシュが大きい (100k 以上の項目) 場合に、 `AcquireTokenForClient` メソッドの機能強化が含まれています。 テストでは、10% - 30% 速度の向上が示されました。 MSAL [4.24.0](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/releases/tag/4.24.0) でリリースされました。

**PR [#2309](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/pull/2309)** には、 `WithLegacyCacheCompatibility` ビルダー メソッドを使用してレガシ キャッシュを無効にする方法が含まれています。 レガシ キャッシュを無効にすると、特に大規模なキャッシュの場合、MSAL キャッシュ操作が高速化されます (使用しない場合)。 MSAL [4.25.0](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/releases/tag/4.25.0) でリリースされました。

**PR [#2834](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/pull/2834)** では、不要なシリアル化を削除し、クライアント資格情報フローで使用される既定のアプリ トークン キャッシュにパーティション分割を追加することで、パフォーマンスが向上しました。 MSAL [4.36.0](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/releases/tag/4.36.0) でリリースされました。 図は、クライアント資格情報呼び出しの P99 待機時間のパフォーマンスの向上をミリ秒単位で示しています。

[Image: MSAL.NET の待機時間の図]

**PR [#2881](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/pull/2881)** では、ユーザー フローで使用される既定のメモリ内ユーザー キャッシュにパーティション分割を追加することで、キャッシュのパフォーマンスが大幅に向上しました (承認コードによるトークンの代理取得など)。 MSAL [4.37.0](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/releases/tag/4.37.0) でリリースされました。

**PR [#3233](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/pull/3233)** では、トークンのフィルター処理が向上し、割り当てられたメモリが削減されます。 MSAL [4.43.0](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/releases/tag/4.43.0) でリリースされました。

**PR [#3250](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/pull/3250)** は、新しいアプリ ビルダーを作成するパフォーマンスと、シリアル化を使用するトークン呼び出しを取得する場合と使用しないトークン呼び出しの違いを示しています。

**PR [#3605](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/pull/3605)** では、JSON 操作に System.Text.Json (STJ) ライブラリを使用する新しい .NET 6 ターゲットが追加されます (Newtonsoft Json の代わりに.NET)。 Json.NET を使用する .NET Core 2.1 バイナリと比較して、STJ を使用する .NET 6 MSAL バイナリでは、テスト結果から、JSON 操作における速度とメモリ割り当て量が平均で 60% 向上し、エンドツーエンドのトークン取得呼び出しの速度も平均で 20% 向上することが示されています。 MSAL [4.48.0](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/releases/tag/4.48.0) でリリースされました。

### Metrics

[トークン キャッシュのドキュメント](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-token-cache-serialization?tabs=aspnetcore#monitor-cache-hit-ratios-and-cache-performance)で、MSAL で提供されるメトリックに関する情報を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/powershell-support"} -->
## PowerShell での MSAL.NET の使用 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/powershell-support
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: MSAL.NET を使用して PowerShell スクリプトからトークンを取得する方法。

Entra SDK チームによって管理されている MSAL ライブラリの **公式 PowerShell モジュールまたはラッパーはありません** 。 より高いレベルの SDK を使用することを検討してください。

- [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)
- [Azure PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/azure/new-azureps-module-az)

PowerShell は、.NETコードを呼び出すことができるように設計されており、これを行う方法を説明する[追加のリソース](https://stackoverflow.com/questions/3079346/how-to-reference-net-assemblies-using-powershell)があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/proof-of-possession-tokens"} -->
## 所有証明 (PoP) トークン - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/proof-of-possession-tokens
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: MSAL.NET で公開および機密クライアントの所有証明トークンを取得する方法について説明します

ベアラー トークンは、最新の ID フローでは標準です。ただし、トークン キャッシュから盗まれるのに対して脆弱です。

[RFC 7800](https://tools.ietf.org/html/rfc7800) で説明されているように、所有証明 (PoP) トークンは、この脅威を軽減します。 PoP トークンは、公開/プライベート PoP キーを介してクライアント コンピューターにバインドされます。 トークン発行者 (Entra ID) によって PoP 公開キーがトークンに挿入され、クライアントも秘密 PoP キーを使用してトークンに署名します。 完全形式の PoP トークンには、トークン発行者とクライアントの 2 つのデジタル署名があります。 PoP プロトコルには、次の 2 つの保護が用意されています。

- **トークン キャッシュの侵害に対する保護**。 MSAL は、完全形式の PoP トークンをキャッシュに格納しません。 代わりに、アプリがトークンを要求した場合にのみ、トークンに署名します。 トークン キャッシュを侵害できる攻撃者は、PoP 秘密キーにアクセスできないため、そこで不完全なトークンにデジタル署名することはできません。 攻撃者が秘密キーを盗む能力は、ハードウェアで保護されたキーを使用して軽減できます。
- **中間者攻撃に対する保護**。 サーバーノンスがプロトコルに追加されます。

Warning

PoP プロトコルの強度は、PoP キーの強度によって異なります。 Microsoftでは、可能な場合は[トラステッド プラットフォーム モジュール (TPM)](https://support.microsoft.com/topic/what-is-tpm-705f241d-025d-4470-80c5-4feeb24fa1ee) 経由でハードウェア キーを使用することをお勧めします。

### PoP バリエーション

いくつかの PoP プロトコルとバリエーションがあります。 Microsoft Entra ID インフラストラクチャは、次の 2 種類をサポートすることを目的としています。

- [mTLS POP - RFC 8705](https://datatracker.ietf.org/doc/html/rfc8705)。 サービス間通信を目的としています。たとえば、Azure KeyVault からシークレットを取得するワークロード向けです。
- [DPOP - RFC 9449](https://datatracker.ietf.org/doc/html/rfc9449)。 パブリック クライアント アプリケーションを対象としています。

2 つのプロトコルが必要な理由mTLS POP はより高速であり、TLS レイヤーに nonce 保護を含めるという利点があります。ただし、クライアントと ID プロバイダーの間、およびクライアントとリソースの間に mTLS トンネルを確立することは困難な場合があります。 dPOP はトランスポート プロトコルの変更に依存しません。ただし、サーバー nonce はアプリ開発者が明示的に処理する必要があります。

### PoP SHR のレガシー サポート

Microsoft**署名付き HTTP 要求 (SHR) を介した PoP** のサポートを提供しました。 詳細な仕様については、 [PoP キーの配布](https://datatracker.ietf.org/doc/html/draft-ietf-oauth-pop-key-distribution-07) と [SHR](https://datatracker.ietf.org/doc/html/draft-ietf-oauth-signed-http-request-03) を参照してください。 このプロトコルは段階的に廃止され、DPOP に置き換えられます。

### トークンの検証

Microsoftは、https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnetを介して.NETのトークン検証プリミティブを提供します。どちらの種類のトークンもこのように検証できます。

### 使用方法

#### パブリック クライアント アプリケーション

パブリック クライアント フロー上の PoP は、[Windows ブローカー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam) (WAM) を使用して実現できます。 その他の MSAL ライブラリでは、WAM 経由の PoP もサポートされています。

ブローカー (MSAL 経由) は、コンピューター上に存在する最適な使用可能なキー (通常はハードウェア キー ( [TPM](https://learn.microsoft.com/ja-jp/windows/security/hardware-security/tpm/tpm-fundamentals) など) を使用します。 独自のキーを持ち込むオプションはありません。

クライアントが PoP トークンの作成をサポートしていない可能性があります。 これは、ブローカー (WAM や ポータル サイト など) が常にデバイスに存在しないか、SDK が特定のオペレーティング システムにプロトコルを実装していないことが原因で発生します。 現在、PoP トークンは、Windows 10以降とWindows Server 2019以上で使用できます。 [`IsProofOfPossessionSupportedByClient()`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplication.isproofofpossessionsupportedbyclient#microsoft-identity-client-publicclientapplication-isproofofpossessionsupportedbyclient)を使用して、PoP がクライアントでサポートされているかどうかを確認します。

##### 例

```csharp
// Required for the use of the broker 
using Microsoft.Identity.Client.Broker; 

// The PoP token will be bound to this user / machine and to `GET https://www.contoso.com/tranfers` (the query parameters are not bound).
// The nonce is a requirement in this case and needs to be acquired from the resource before using this API.

// Server nonce is required
string nonce = "nonce";

//HttpMethod is optional
HttpMethod method = HttpMethod.Get;

//Request URI
Uri requestUri = new Uri("https://www.contoso.com/tranfers?user=me");
          
var pca = PublicClientApplicationBuilder.Create(CLIENT_ID)
    .WithBroker()  //Enables the use of broker on public clients only
    .Build();

//Interactive request
AuthenticationResult result = await pca
      .AcquireTokenInteractive(new[] { "scope" })
      .WithProofOfPossession(nonce, method, requestUri)
      .ExecuteAsync()
      .ConfigureAwait(false);

// The PoP token will be available in the AuthenticationResult.AccessToken returned form the acquire token call

//To create the auth header
var authHeader = new AuthenticationHeaderValue(result.TokenType, result.AccessToken);

//Silent request
var accounts = await pca.GetAccountsAsync().ConfigureAwait(false);
var result = await pca.AcquireTokenSilent(new[] { "scope" }, accounts.FirstOrDefault())
       .WithProofOfPossession(nonce, method, requestUri)
       .ExecuteAsync()
       .ConfigureAwait(false);
```

##### クレームをさらに追加する、または PoP トークンの SHR リクエスト部分を作成する

SHR を自分で作成するには、 [実装例を](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/blob/300fba16bd8096dceba3684311550b4b52a56177/tests/Microsoft.Identity.Test.Integration.netfx/HeadlessTests/PoPTests.cs#L286)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/spa-authorization-code"} -->
## シングルページ アプリケーション (SPA) と承認コード - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/spa-authorization-code
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: このフローにより、機密クライアント アプリケーションは eSTS /token エンドポイントから追加の SPA 認証コードを要求できます。この承認コードは、ブラウザーで実行されているフロントエンドによってサイレントモードで引き換えることができます。

このフローにより、機密クライアント アプリケーションは eSTS /token エンドポイントから追加の SPA 認証コードを要求できます。この承認コードは、ブラウザーで実行されているフロントエンドによってサイレントモードで引き換えることができます。 この機能は、MSAL.net や MSAL Node サーバー側などの機密 SDK を使用してサーバー側 (Web アプリ) とブラウザー側 (SPA) 認証を実行し、ブラウザーで MSAL.js するアプリケーション (React シングルページ アプリケーションをホストする ASP.net Web アプリケーションなど) を対象としています。 このようなシナリオでは、アプリケーションにはブラウザー側 (たとえば、MSAL.jsを使用するパブリック クライアント) とサーバー側 (たとえば、MSAL.net を使用する機密クライアント) の両方の認証が必要になり、各アプリケーション コンテキストで独自のトークンを取得する必要があります。

現在、このアーキテクチャを使用するアプリケーションは、まず機密クライアント アプリケーションを使用してユーザーを対話形式で認証し、パブリック クライアントで 2 回目にユーザーをサイレント認証しようとします。 残念ながら、このプロセスはどちらも比較的遅く、サードパーティの Cookie が無効またはブロックされている場合、クライアント側 (非表示の iframe) で行われたサイレント ネットワーク要求は決定的に失敗します。 サーバー側で 2 つ目の承認コードを取得することで、MSAL.js 非表示の iframe ステップをスキップし、/token エンドポイントに対して承認コードをすぐに使用できます。 これにより、サードパーティ Cookie のブロックによって引き起こされる問題を軽減でき、パフォーマンスも向上します。

### 可用性

MSAL 4.40 以降を使用すると、機密クライアントは、Microsoft Entra ID トークン エンドポイントから追加の SPA 認証コードを要求できます。

### フローをサポートするために必要なリダイレクト URI のセットアップ

スパ認証コードの取得に使用するredirect\_uriは、Web 型である必要があります。

### バックエンドで SPA 認証コードを取得する

MSAL.Net では、新しい `WithSpaAuthorizationCode` API を使用して `SpaAuthCode`を取得します。

```csharp
private async Task OnAuthorizationCodeReceived(AuthorizationCodeReceivedNotification context)
{
 try
 {
  // Upon successful sign in, get the access token & cache it using MSAL
  IConfidentialClientApplication clientApp = MsalAppBuilder.BuildConfidentialClientApplication();
  AuthenticationResult result = await clientApp.AcquireTokenByAuthorizationCode(new[] { "user.read" }, context.Code)
      .WithSpaAuthorizationCode(true)
      .ExecuteAsync();

   HttpContext.Current.Session.Add("Spa_Auth_Code", result.SpaAuthCode);
 }
 catch
 {
 
 }
}
```

### フロントエンドでの SPA 認証コードの使用

シングルページ アプリケーションで MSAL.js から PublicClientApplication を構成します。

```JS
const msalInstance = new msal.PublicClientApplication({
    auth: {
        clientId: "{{clientId}}",
        redirectUri: "http://localhost:3000/auth/client-redirect",
        authority: "{{authority}}"
    }
})
```

次に、取得したコードをサーバー側にレンダリングし、MSAL.js PublicClientApplication インスタンスの acquireTokenByCode API に渡します。 最初のログイン要求に含まれていない追加のスコープは含めないでください。それ以外の場合は、ユーザーに同意を求められる場合があります。

アプリケーションは、両方の要求に同じユーザーが確実に使用されるように、対話型の要求に必要であるため、アカウント ヒントもレンダリングする必要があります

```js
const code = "{{code}}";
const loginHint = "{{loginHint}}";

const scopes = [ "user.read" ];

return msalInstance.acquireTokenByCode({
    code,
    scopes
})
    .catch(error => {
         if (error instanceof msal.InteractionRequiredAuthError) {
            // Use loginHint/sid from server to ensure same user
            return msalInstance.loginRedirect({
                loginHint,
                scopes
            })
        }
    });
```

新しい MSAL.js `acquireTokenByCode` API を使用してアクセス トークンが取得されると、トークンはユーザーのプロファイルの読み取りに使用されます

```js
function callMSGraph(endpoint, token, callback) {
    const headers = new Headers();
    const bearer = `Bearer ${token}`;
    headers.append("Authorization", bearer);

    const options = {
        method: "GET",
        headers: headers
    };

    console.log('request made to Graph API at: ' + new Date().toString());

    fetch(endpoint, options)
        .then(response => response.json())
        .then(response => callback(response, endpoint))
        .then(result => {
            console.log('Successfully Fetched Data from Graph API:', result);
        })
        .catch(error => console.log(error))
}
```

### Sample

[フロントエンドで SPA 承認コードを使用する ASP.NET MVC プロジェクト](https://github.com/Azure-Samples/ms-identity-aspnet-webapp-openidconnect)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/ssh-certificates"} -->
## MSAL.NET での SSH 証明書の使用 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/ssh-certificates
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: Microsoft Entra IDはベアラー トークンではなく SSH 証明書を発行できます。

Note

この機能は MSAL 4.3.2 以降から利用できます

Microsoft Entra IDはベアラー トークンではなく SSH 証明書を発行できます。 これらは SSH 公開キーと同じではありません。 現在、これは `AcquireTokenSilent` と `AcquireTokenInteractive`の拡張メソッドとして使用できます。

```csharp
var result = await pca
    .AcquireTokenSilent(s_scopes, account)
    .WithSSHCertificateAuthenticationScheme(jwk, "keyID1")
    .ExecuteAsync();
```

パラメーター:

- `jwk` - https://tools.ietf.org/html/rfc7517 で説明されている JWK 形式の SSH 公開鍵。 現在サポートされているのは、最小キー サイズが 2048 バイトの RSA のみです。
- `keyID` - キーを区別する文字列 (通常はキーのハッシュですが、形式は重要ではありません)

JWK の作成例

```csharp
private string CreateJwk()
{
     RSACryptoServiceProvider rsa = new RSACryptoServiceProvider(2048);
     RSAParameters rsaKeyInfo = rsa.ExportParameters(false);

     // Algorithm behind Base64UrlHelpers.Encode is described here https://www.rfc-editor.org/rfc/rfc7515.html#appendix-C
     string modulus = Base64UrlHelpers.Encode(rsaKeyInfo.Modulus); 
     string exp = Base64UrlHelpers.Encode(rsaKeyInfo.Exponent);
     string jwk = $"{{\"kty\":\"RSA\", \"n\":\"{modulus}\", \"e\":\"{exp}\"}}";

     return jwk;
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/testing-apps-using-msal"} -->
## MSAL.NET を使用したアプリケーションのテスト - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/testing-apps-using-msal
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: トークンの取得に MSAL.NET を使用するアプリケーションをテストする方法。

### 単体テスト

MSAL.NET の API では、ビルダー パターンが頻繁に使用されます。 ビルダーをモックするのは難しく、面倒です。 代わりに、すべての認証ロジックをインターフェイスの背後にラップし、アプリでモックすることをお勧めします。

### エンドツーエンドのテスト

エンド ツー エンドテストでは、テスト アカウント、テスト アプリケーション、または個別のディレクトリを設定できます。 ユーザー名とパスワードは、継続的インテグレーション パイプライン (Azure DevOps内のシークレット ビルド変数など) を使用してデプロイできます。 もう 1 つの方法は、KeyVault にテスト資格情報を保持し、証明書をインストールするなどして、KeyVault にアクセスするようにテストを実行するマシンを構成することです。

トークンの取得が行われると、アクセス トークンと更新トークンの両方がキャッシュされることに注意してください。 1 番目の有効期間は 1 時間で、後者は数か月です。 アクセス トークンの有効期限が切れると、MSAL は自動的に更新トークンを使用して、ユーザーの操作なしで新しいトークンを取得します。 この動作に依存して、テストをプロビジョニングできます。

条件付きアクセスを構成している場合は、それを自動化することは困難になります。 条件付きアクセス (MFA など) を処理する手動の手順が簡単になります。これにより、MSAL キャッシュにトークンが追加され、サイレント トークンの取得 (つまり、事前ログインユーザーに依存) に依存します。

#### Web アプリ

**戦略 1**: Selenium または同等のテクノロジを使用して、Web アプリを自動化します。 KeyVault からユーザー名とパスワードを取得します。

長所: 実際のトークンを使用したエンド ツー エンドのテスト

短所: UI オートメーションは不安定です。 ログイン画面を自動化するのは面倒です。 ライブ アカウントと "職場と学校" の UI フローは少し異なります。

**戦略 2**: ROPC (ユーザー名/パスワード フロー) を使用してトークンを取得し、コントローラーのみをテストします。 Microsoftは、他のフローには存在しないセキュリティ リスクがあるため、運用環境で ROPC フローを使用することはお勧めしません。 テスト目的でのみ、このフローを使用します。

長所: UI オートメーションなし

短所: ROPC がサポートされていない Live アカウントでは機能しません。

**戦略 3**: トークン キャッシュを事前に設定するために手動でログインします。 `AcquireTokenSilent`を呼び出して、更新トークンに基づいて新しいアクセス トークンを**サイレント モード**で取得します。 更新トークンは 90 日間有効ですが、更新も行われます。

長所: UI オートメーションなし。は、"Live" アカウントと "Work and School" アカウントの両方で動作します。

短所: 一部の条件付きアクセス ポリシーは異なるマシン間では機能しない、初回に一部手動でのセットアップが必要。

アプリ間でのトークン キャッシュ共有の例: https://github.com/Azure-Samples/ms-identity-dotnet-advanced-token-cache

#### デーモン アプリ

デーモン アプリは、事前にデプロイされたシークレット (パスワードまたは証明書) を使用してMicrosoft Entra IDと通信します。 テスト環境にシークレットをデプロイすることも、トークン キャッシュ手法を使用してテストをプロビジョニングすることもできます。 デーモン アプリで使用されるクライアント資格情報付与では、更新トークンはフェッチされず、アクセス トークンは 1 時間で期限切れになります。

#### ネイティブ クライアント アプリ

ネイティブ クライアントの場合、テストにはいくつかの方法があります。

- 非対話型の方法でトークンをフェッチするには、 [ユーザー名とパスワード](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/username-password-authentication) の付与を使用します。 このフローは運用環境では推奨されませんが、テストに使用するのが妥当です。
- Appium や Xamarin.Test など、アプリと MSAL によって作成されたブラウザーの両方に自動化インターフェイスを提供するフレームワークを使用します。
- MSAL は、開発者が独自のブラウザー エクスペリエンスを挿入できる機能拡張ポイントを公開します。 MSAL チームは、これを内部的に使用して対話型認証シナリオをテストします。

### ライブラリに関するフィードバック

[問題をログに記録](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/issues)するか、テストに関連する質問をしてください。 優れたテスト エクスペリエンスを提供することは、チームの目標の 1 つです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/using-in-azure-functions"} -->
## Azure Functionsでの MSAL.NET の使用 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/using-in-azure-functions
- Service: msal / msal-dotnet
- Article date: 2023-03-17
- Summary: Azure Functions で MSAL.NET を使用する方法について説明します

Azure Functionsで MSAL.NET を使用すると、ライブラリがディレクトリにコピーされないことがあります。

これを防ぐために、 `<_FunctionsSkipCleanOutput>true</_FunctionsSkipCleanOutput>` を .csproj ファイルに追加できます。

詳細については、[Azure/azure-functions-host#5894 を参照](https://github.com/Azure/azure-functions-host/issues/5894)してください。

Microsoftを使用してAzure関数を構築する方法も参照してください[。Identity.Web](https://github.com/AzureAD/microsoft-identity-web/wiki/Azure-Functions)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/advanced/webview2"} -->
## MSAL.NET での WebView2 の使用 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/webview2
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: MSAL.NET アプリケーションでMicrosoft Edgeに基づいて最新の埋め込みブラウザーを使用する方法。

WebView2 は、Microsoft Edgeに基づく最新の埋め込みブラウザー ランタイムであり、Windows Hello認証、FIDO キーを使用したログインなどを実行できます。 このブラウザーは、Internet Explorerに基づいて従来の Web ビューを置き換えます。

### 可用性

アプリケーションで WebView2 を使用するには、次の要件を満たす必要があります。

- Windows 10 OS 以降 (WebView2 ランタイムでサポート)。
- MSAL.NET バージョン 4.28.0 以降。
- [WebView2 ランタイム](https://learn.microsoft.com/ja-jp/microsoft-edge/webview2/) をコンピューターにインストールする必要があります。

#### パッケージの要件

必要なパッケージは、ターゲット フレームワークとアプリケーションの種類によって異なります。

- **.Net Framework** : 使用 [`Microsoft.Identity.Client.Desktop`](https://www.nuget.org/packages/Microsoft.Identity.Client.Desktop/)
- **.NET MAUI 9 以降および WinAppSDK パッケージ アプリ**: 使用[`Microsoft.Identity.Client.Desktop.WinUI3`](https://www.nuget.org/packages/Microsoft.Identity.Client.Desktop.WinUI3/)

### 呼び出しパターン

新しい WebView2 ランタイムを使用する場合は、次の必要な変更に注意してください。

- `net6.0-windows` 以降に対して記述されたアプリケーションでは、変更は必要ありません。
- .NET Framework、.NET Core、またはベース .NET 5 以上に対して記述されたアプリケーションでは、[`Microsoft.Identity.Client.Desktop`](https://www.nuget.org/packages/Microsoft.Identity.Client.Desktop/)への参照を追加し、パブリック クライアント アプリケーションをインスタンス化するときに[`.WithWindowsEmbeddedBrowserSupport()`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.desktop.desktopextensions.withwindowsembeddedbrowsersupport)を追加します。

```csharp
var pca = PublicClientApplicationBuilder
        .Create("0f887a65-3c78-4b16-9529-6209b8741c26")
        .WithWindowsEmbeddedBrowserSupport()
        .Build();
```

Important

WebView2 は、Microsoft Entra ID (旧称Azure Active Directory) 機関では**サポートされていません**。 そのコンテキストで [`.WithWindowsEmbeddedBrowserSupport()`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.desktop.desktopextensions.withwindowsembeddedbrowsersupport) を使用すると、パブリック クライアント アプリケーションのインスタンス化中に指定された設定に関係なく、常にレガシ Web ビューが既定になります。 これは、MSAL の開発中に特定された安定性のバグによって発生します。 B2C および Active Directory フェデレーション サービス (AD FS) (ADFS) 機関の場合、WebView2 が表示されます。

### 行動

| フレームワーク | 埋め込み Web ビュー | 既定の Web ビュー |
| --- | --- | --- |
| .NET Framework | レガシー、Legacy^1^ へのフォールバックを備えた WebView2 | レガシ埋め込み |
| .NET コア | Legacy^2^ へのフォールバックを備えた WebView2 | システム |
| .NET 6^3^ | レガシー^2^ へのフォールバックを備えた WebView2 | システム |
| .NET 6 Windows | WebView2、レガシへのフォールバック | WebView2、埋め込み済み |
| MAUI 9+/WinAppSDK パッケージ アプリ | WebView2 と WinUI 3、レガシ^4^ へのフォールバックなし | WebView2、埋め込み済み |

^1^ レガシ Web ビューは、.NET Framework アプリの既定値です。 WebView2 は、Microsoft.Identity.Client.Desktop パッケージを介して使用できます。 ^2^ .NET Core および .NET 6 以上のアプリでは、Microsoft経由でのみ Web ビューを使用できます。Identity.Client パッケージ。 ^3^ MSAL.NET では、明示的な`net5.0` バイナリは提供されません。 .NET 5 つのアプリは、.NET Core の動作に従います。 ^4^ MAUI 9 以降および WinAppSDK パッケージ アプリケーションの B2C/ADFS サポートで WinUI 3 ベースの WebView2 実装を使用します。

#### 使用するタイミング

次のようなときは `Microsoft.Identity.Client.Desktop.WinUI3` を使います。

- .NET MAUI 9 以上のアプリケーションのビルド
- WinAppSDK パッケージ アプリケーションの開発
- 信頼性の高い B2C または ADFS 認証フローの要求
- WinRT フレームワークの互換性の要求

他のすべてのシナリオで `Microsoft.Identity.Client.Desktop` を使用します。

### Troubleshooting

#### パッケージの選択に関する問題

WebView2 統合で問題が発生した場合は、ターゲット フレームワークに適切なパッケージを使用していることを確認してください。

- **エラー**: .NET MAUI 9 以降または WinAppSDK でパッケージ化されたアプリで、ファイルまたはアセンブリ 'Microsoft.Web.WebView2.Core' を読み込めない、または WinRT 関連の例外が発生する
- **解決策**: MAUI 9 以降および WinAppSDK パッケージ アプリケーションの `Microsoft.Identity.Client.Desktop` から `Microsoft.Identity.Client.Desktop.WinUI3` に切り替えます。

#### .NET Framework 上の WebView2

.NET Framework を対象とし、[MSAL.NET NuGet パッケージ](https://www.nuget.org/packages/Microsoft.Identity.Client/)を参照するアプリが WebView2 埋め込みブラウザーを使用して対話形式でトークンを取得しようとすると、WebView2 が例外をスローするシナリオがあります。 これらのエラーは、`System.BadImageFormatException: An attempt was made to load a program with an incorrect format.` または `System.DllNotFoundException: 'Unable to load DLL 'WebView2Loader.dll': The specified module could not be found.` である可能性があります。MSAL.NET 4.32.0 以降、これらの例外は [`MsalClientException`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalclientexception) にラップされます。

これは、ライブラリがその依存関係のターゲット プラットフォームを把握できないため、.NET Framework プラットフォーム上の WebView2 SDK の既存のバグが原因で発生します。 回避策の 1 つは、値が`<PlatformTarget>`、`AnyCPU`、または`x86`を含む`x64`をアプリケーション プロジェクト ファイルに追加することです。 `x86` または `x64` コンピューターにインストールされている WebView2 のターゲット フレームワークと一致する必要があります。 もう 1 つの回避策は、アプリのプロジェクト ファイルに `<PlatformTarget>AnyCPU</PlatformTarget>` を追加し、 [WebView2 NuGet パッケージ](https://www.nuget.org/packages/Microsoft.Web.WebView2)を直接参照することです。 詳細については、問題 [#2482](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/issues/2482)、 [#730](https://github.com/MicrosoftEdge/WebView2Feedback/issues/730#issuecomment-803132248) を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/getting-started/best-practices"} -->
## MSAL.NET のベスト プラクティス - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/best-practices
- Service: msal / msal-dotnet
- Article date: 2023-03-17
- Summary: アプリケーション開発シナリオで MSAL.NET を使用する場合のベスト プラクティスについて説明します。

### アクセス トークンを解析しない

(たとえば、 https://jwt.msを使用して) アクセス トークンの内容を確認できますが、教育やデバッグの目的で、クライアント コードの一部としてアクセス トークンを解析しないでください。 アクセス トークンは、Web API または取得されたリソースのみを対象としています。 ほとんどの場合、Web API はミドルウェア レイヤー (.NET の[.NETの ID モデル拡張機能](https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnet/wiki)など) を使用します。これは複雑なコードであり、Web アプリと Web API の保護に関する複雑なコードであり、重要なパスを忘れることでセキュリティの脆弱性を導入したくない場合があります。

### Microsoft Entra IDからトークンを取得しすぎないようにする

トークンを取得する標準的なパターンは、(i) キャッシュからサイレントモードでトークンを取得し、(ii) 動作しない場合は、Microsoft Entra IDから新しいトークンを取得することです。 最初の手順をスキップすると、アプリがMicrosoft Entraからトークンを取得する頻度が高すぎる可能性があります。 ID プロバイダーによって調整される可能性があるため、低速でエラーが発生しやすいため、ユーザー エクスペリエンスが低下します。

### トークンの有効期限を自分で処理しない

`AuthenticationResult`がトークンの有効期限を返した場合でも、アクセス トークンの有効期限と更新を自分で処理しないでください。 MSAL.NET がこれを行います。 ユーザー アカウントのトークンを取得するフローでは、これらの書き込みトークンをユーザー トークン キャッシュに書き込み、トークンがサイレントモードで取得および更新 (必要な場合) するため、推奨パターンを使用する必要があります。 `AcquireTokenSilent`

```csharp
AuthenticationResult result;
try
{
 // will handle expired Access Tokens by fetching new ones using the Refresh Token
 result = await AcquireTokenSilent(scopes).ExecuteAsync();
}
catch(MsalUiRequiredException ex)
{
 result = AcquireTokenXXXX(scopes, ..).WithXXX(…).ExecuteAsync();
}
```

クライアント資格情報フローで `AcquireTokenForClient` を使用する場合、このメソッドはトークンをアプリケーション キャッシュに格納するだけでなく、必要に応じて検索して更新するため、キャッシュについて心配する必要はありません。 これは、アプリケーション トークン キャッシュ (アプリケーション自体のトークンのキャッシュ) と対話する唯一の方法です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/getting-started/choosing-msal-dotnet"} -->
## MSAL.NET のバージョンの選択 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/choosing-msal-dotnet
- Service: msal / msal-dotnet
- Article date: 2023-03-17
- Summary: アプリケーションの種類と基になるプラットフォームに基づいて、開発シナリオに合ったバージョンの MSAL.NET を選択する方法について説明します。

Important

2025 年 5 月 1 日より、Azure AD B2C は新規顧客向けに購入できなくなります。 [詳細については、FAQ を参照してください](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale)。

構築するアプリケーションの種類とその基になるプラットフォームに応じて、MSAL.NET、[Microsoft Identity Web](https://github.com/AzureAD/microsoft-identity-web)、またはその両方を使用できます。

Microsoft Identity Web は、Microsoft ID プラットフォームと統合する Web アプリと Web API への認証と承認のサポートの追加を簡略化する一連の ASP.NET Core ライブラリです。 これは、ASP.NET Core、その認証ミドルウェア、および.NETのMicrosoft Authentication Library (MSAL) を結び付けた単一サーフェス API の便利なレイヤーを提供します。

次のデシジョン ツリーに従って、シナリオで MSAL.NET、Microsoft Identity Web、またはその両方が必要かどうかを判断します。

[Image: .NET認証ライブラリを使用するときのデシジョン ツリーの画像]

### MSAL.NET を使用するタイミング

デスクトップまたはモバイル アプリを構築している。 MSAL.NET を直接使用し、パブリック クライアント アプリケーションのトークンの取得を開始します。 詳細については、次のページを参照してください。

- [デスクトップ アプリでトークンを取得](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-desktop-acquire-token?tabs=dotnet)し、[WAM](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam) を使用する
- [モバイル アプリケーションでのトークンの取得](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-mobile-acquire-token)

### [Microsoft Identity Web を使用する](https://github.com/AzureAD/microsoft-identity-web/)

ASP.NET Core、ASP.NET OWIN、または .NET フレームワーク/.NET Core で実行されている機密クライアント アプリケーション (Web アプリ、Web API、デーモン/サービス アプリ) を構築しています。 Identity Web が提供するMicrosoftを確認します。

- Microsoft Entra ID、Azure AD B2C、および Microsoft Entra 外部 ID アプリケーションで Web アプリ経由でユーザーをサインインする
    - 個人アカウントMicrosoftサポートする
    - ゲスト ユーザーのサポート
    - Web アプリでの増分同意と条件付きアクセス
    - SameSite の処理
    - "App Services 認証" と統合されます
    - 機密クライアント アプリケーションの PKCE をサポート
    - 分散を含む、パフォーマンスの高いトークン キャッシュ シリアライザーを提供します
- Web API を保護する (Microsoft Entra ID、Azure AD B2C、またはMicrosoft Entra 外部 IDを使用)
    - 発行者 (マルチテナント 内アプリ、任意のクラウドを含む) を検証します。
    - では、Web API のトークン暗号化解除証明書がサポートされます
    - Web API のスコープとアプリロールを検証します
    - API (CA、CAE) で `WWW-authenticate` ヘッダーを生成します
    - gRPC サービスとAzure関数を保護する
- ダウンストリーム API を呼び出す Web アプリ/API (B2C を除くグラフを含む)
    - 認証とトークンを自分で管理することなく、ダウンストリーム API を呼び出します。
    - graph SDK と統合され、Azure SDK
    - クライアント資格情報とMicrosoftについて説明します。Identity.Web はそれらを自動的にフェッチします (たとえば、Key Vaultからの証明書や、[Azure Kubernetes Service (AKS)](https://azure.microsoft.com/products/kubernetes-service)と[マネージド ID との](https://learn.microsoft.com/ja-jp/azure/active-directory/managed-identities-azure-resources/overview)[ワークロード ID フェデレーション](https://learn.microsoft.com/ja-jp/azure/active-directory/workload-identities/workload-identity-federation)など)
- ASP.NET Coreで複数の認証スキームをサポート
- 所有証明プロトコルをサポート
- 回復性 (トークン バックアップ システムのリージョントークン取得とルーティング ヒントをサポート)

#### 新しいアプリケーションをビルドする

Project テンプレートと`msidentity-app-sync` ツールを使用します。 Web MVC、Razor、Blazor サーバー、Blazorwasm 用の Web アプリ テンプレートがホストされ、ホストされていません。 すべて Microsoft Entra ID または Azure AD B2C 用。

[Image: Web アプリを構築するための ASP.NET Core プロジェクト テンプレートを示す画像]

[Web アプリ プロジェクト テンプレート](https://github.com/AzureAD/microsoft-identity-web/wiki/web-app-template)。

gRPC とAzure Functions用の Web API テンプレートがあります。

[Web API プロジェクト テンプレート](https://github.com/AzureAD/microsoft-identity-web/wiki/web-api-template)。

ここでは、[msidentity-app-sync-tool](https://github.com/AzureAD/microsoft-identity-web/blob/master/tools/app-provisioning-tool/README.md) を実行する方法について説明します。これは、テナント (Microsoft Entra ID または Azure AD B2C) にMicrosoft ID プラットフォーム アプリケーションを作成し、ASP.NET Core アプリケーションの構成コードを更新するコマンド ライン ツールです。 このツールを使用して、既存のMicrosoft Entra アプリケーションまたは AD B2C アプリケーションAzureコードを更新することもできます。

[NuGet](https://www.nuget.org/packages/msidentity-app-sync/) で使用できます。

#### 既存のアプリに認証を追加しているか、ADAL から移行しています

アプリを更新するために必要なコードMicrosoft Identity Web から取得するだけです。 次に例を示します。

[Image: Web API を呼び出す Web アプリをビルドするときのコードの更新を示す画像]

[Image: B2C Web アプリまたは API をビルドするときのコードの更新を示す画像]

[Image: ユーザーと保護された Web API をサインインさせる B2C Web アプリのコード更新を示す画像]

[Image: ダウンストリーム API を呼び出す Web アプリまたは Web API のコード更新を示す画像]

### ハイブリッド モデル (MSAL.NET と [Microsoft Identity Web](https://github.com/AzureAD/microsoft-identity-web/)) を使用するタイミング

機密クライアント アプリケーション用の SDK を構築しており、低レベルの API MSAL.NET 使用したいと考えています。 MSAL.NET では、メモリ内トークン キャッシュが既定で提供されますが、Web アプリまたは Web API の場合、キャッシュは、正しくパーティション分割する必要があるため、パブリック クライアント アプリケーション (デスクトップまたはモバイル アプリ) の場合とは異なる方法で管理する必要があります。 分散キャッシュ、(Redis、Cosmos、SQL Server、メモリ キャッシュに分散など)、メモリ キャッシュに正しくパーティション分割されたトークン キャッシュ シリアライザーを利用することを強くお勧めします。

トークン キャッシュ シリアライザーを使用すると、キャッシュがストレージと MSAL のメモリ間でスワップされるため、使用されるキャッシュ キーに応じてトークン キャッシュをパーティション分割します。 このキャッシュ キーは、使用するフローの関数として MSAL.NET によって計算されます

[Image: カスタム シリアライザーの有無に関係なくトークン キャッシュを示す画像]

#### Microsoftが必要な理由。Identity.Web.TokenCache?

[Microsoft。Identity.Web.TokenCache](https://www.nuget.org/packages/Microsoft.Identity.Web.TokenCache) では、トークン キャッシュのシリアル化が提供されます。 詳細については、 [トークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization) に関するページを参照してください。

Web アプリと Web API にトークン キャッシュを使用する方法の例については、フェーズ [2-2 トークン キャッシュ](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/2-WebApp-graph-user/2-2-TokenCache)の [ASP.NET Core Web アプリのチュートリアル](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/)を参照してください。 実装については、Microsoftの [TokenCacheProviders](https://github.com/AzureAD/microsoft-identity-web/tree/master/src/Microsoft.Identity.Web/TokenCacheProviders) フォルダーを参照してください[。Identity.Web](https://github.com/AzureAD/microsoft-identity-web) リポジトリ。

Microsoft Identity Web は[、証明書の読み込み](https://github.com/AzureAD/microsoft-identity-web/wiki/asp-net#help-loading-certificates)にも役立ちます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/getting-started/initializing-client-applications"} -->
## MSAL.NET クライアント アプリケーションを初期化する - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/initializing-client-applications
- Service: msal / msal-dotnet
- Article date: 2024-06-04
- Summary: .NET (MSAL.NET) のMicrosoft Authentication Libraryを使用したパブリック クライアント アプリケーションと機密クライアント アプリケーションの初期化について説明します。

この記事では、.NET (MSAL.NET) のMicrosoft Authentication Libraryを使用してパブリック クライアント アプリケーションと機密クライアント アプリケーションを初期化する方法について説明します。 クライアント アプリケーションの種類の詳細については、 [パブリック クライアント アプリケーションと機密クライアント アプリケーション](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-client-applications)に関するページを参照してください。

MSAL.NET 3.x では、アプリケーションビルダー (`PublicClientApplicationBuilder`と`ConfidentialClientApplicationBuilder`) を使用してアプリケーションをインスタンス化することをお勧めします。 コード、構成ファイル、または両方の方法を混在させることで、アプリケーションを構成するための強力なメカニズムを提供します。

### Prerequisites

アプリケーションを初期化する前に、まずアプリケーションを登録して、アプリをMicrosoft ID プラットフォームと統合できるようにする必要があります。 詳細については、「[クイック スタート: Microsoft ID プラットフォームにアプリケーションを登録](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/quickstart-register-app)する」を参照してください。 登録後、次の情報が必要になります。この情報は、Microsoft Entra 管理センターのアプリ登録ページにあります。

- **アプリケーション (クライアント) ID** - GUID を表す文字列です。
- **ディレクトリ (テナント) ID** - 組織で使用されるアプリケーションとリソースに ID とアクセス管理 (IAM) 機能を提供します。 組織専用の基幹業務アプリケーション (シングルテナント アプリケーションとも呼ばれます) を作成するかどうかを指定できます。
- アプリケーションの ID プロバイダー URL ( **インスタンス**という名前) とサインイン対象ユーザー。 これら 2 つのパラメーターは、総称して機関と呼ばれます。
- **クライアント資格情報** - 機密クライアント アプリの場合は、アプリケーション シークレット (クライアント シークレット文字列) または証明書 ( `X509Certificate2` 型) の形式を使用できます。
- Web アプリの場合や、パブリック クライアント アプリの場合 (特にアプリでブローカーを使用する必要がある場合)、ID プロバイダーがセキュリティ トークンを使用してアプリケーションに連絡する **リダイレクト URI を** 設定する必要があります。

### アプリケーションの初期化

クライアント アプリケーションをインスタンス化するには、さまざまな方法があります。

#### コードからのパブリック クライアント アプリケーションの初期化

次のコードでは、パブリック クライアント アプリケーションをインスタンス化し、職場、学校、または個人のMicrosoft アカウントを使用して、Microsoft Azureパブリック クラウド内のユーザーをサインインします。

```csharp
IPublicClientApplication app = PublicClientApplicationBuilder.Create(clientId)
    .Build();
```

#### コードからの機密クライアント アプリケーションの初期化

同様に、次のコードは、Microsoft Azureパブリック クラウド内のユーザー、職場と学校のアカウント、または個人のMicrosoft アカウントを使用してトークンを処理する機密アプリケーション (`https://myapp.azurewebsites.net`にある Web アプリ) をインスタンス化します。 アプリケーションは、クライアント シークレットを共有することによって ID プロバイダーと識別されます。

```csharp
string redirectUri = "https://myapp.azurewebsites.net";
IConfidentialClientApplication app = ConfidentialClientApplicationBuilder.Create(clientId)
    .WithClientSecret(clientSecret)
    .WithRedirectUri(redirectUri )
    .Build();
```

ただし、運用環境では、証明書はクライアント シークレットよりも安全であるため、推奨されます。 作成してMicrosoft Entra 管理センターにアップロードできます。 その後、コードは次のようになります。

```csharp
IConfidentialClientApplication app = ConfidentialClientApplicationBuilder.Create(clientId)
    .WithCertificate(certificate)
    .WithRedirectUri(redirectUri )
    .Build();
```

#### 構成オプションからのパブリック クライアント アプリケーションの初期化

次のコードは、構成オブジェクトからパブリック クライアント アプリケーションをインスタンス化します。構成オブジェクトは、プログラムで入力することも、構成ファイルから読み取る場合もあります。

```csharp
PublicClientApplicationOptions options = GetOptions(); // your own method
IPublicClientApplication app = PublicClientApplicationBuilder.CreateWithApplicationOptions(options)
    .Build();
```

#### 構成オプションからの機密クライアント アプリケーションの初期化

機密クライアント アプリケーションにも同じ種類のパターンが適用されます。 `.WithXXX`修飾子を使用して他のパラメーターを追加することもできます。 この例では、 `.WithCertificate`を使用します。

```csharp
ConfidentialClientApplicationOptions options = GetOptions(); // your own method
IConfidentialClientApplication app = ConfidentialClientApplicationBuilder.CreateWithApplicationOptions(options)
    .WithCertificate(certificate)
    .Build();
```

### ビルダー修飾子

アプリケーション ビルダーを使用するコード スニペットでは、多くの `.With` メソッドを修飾子 ( `.WithCertificate` や `.WithRedirectUri`など) として適用できます。

#### パブリック クライアント アプリケーションと機密クライアント アプリケーションに共通する修飾子

パブリック クライアントまたは機密クライアント アプリケーション ビルダーで設定できる修飾子は、 `AbstractApplicationBuilder<T>` クラスにあります。 さまざまな方法については、[.NETドキュメントのAzure SDKを参照してください](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.abstractapplicationbuilder-1)。

#### Xamarin.iOS アプリケーションに固有の修飾子

Xamarin.iOS のパブリック クライアント アプリケーション ビルダーで設定できる修飾子は次のとおりです。

| 修飾子 | 説明 |
| --- | --- |
| `.WithIosKeychainSecurityGroup()` | **Xamarin.iOS のみ**: iOS キー チェーン セキュリティ グループ (キャッシュ永続化用) を設定します。 |

#### 機密クライアント アプリケーションに固有の修飾子

機密クライアント アプリケーション ビルダーに固有の修飾子は、 `ConfidentialClientApplicationBuilder` クラスにあります。 さまざまな方法については、[.NETドキュメントのAzure SDKを参照してください](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.confidentialclientapplicationbuilder)。

`.WithCertificate(X509Certificate2 certificate)`や`.WithClientSecret(string clientSecret)`などの修飾子は相互に排他的です。 両方を指定すると、MSAL は意味のある例外をスローします。

#### 修飾子の使用例

アプリケーションが基幹業務アプリケーションであり、組織専用であるとします。 その後、次のように記述できます。

```csharp
IPublicClientApplication app;
app = PublicClientApplicationBuilder.Create(clientId)
        .WithAuthority(AzureCloudInstance.AzurePublic, tenantId)
        .Build();
```

国内クラウドのプログラミングが簡素化されたため、アプリケーションを国内クラウドのマルチテナント アプリケーションにする場合は、次のように記述できます。

```csharp
IPublicClientApplication app;
app = PublicClientApplicationBuilder.Create(clientId)
        .WithAuthority(AzureCloudInstance.AzureUsGovernment, AadAuthorityAudience.AzureAdMultipleOrgs)
        .Build();
```

ADFS のオーバーライドもあります (MSAL.NET は ADFS 2019 以降のみをサポートします)。

```csharp
IPublicClientApplication app;
app = PublicClientApplicationBuilder.Create(clientId)
        .WithAdfsAuthority("https://consoso.com/adfs")
        .Build();
```

最後に、AZURE AD B2C 開発者の場合は、次のようにテナントを指定できます。

```csharp
IPublicClientApplication app;
app = PublicClientApplicationBuilder.Create(clientId)
        .WithB2CAuthority("https://fabrikamb2c.b2clogin.com/tfp/{tenant}/{PolicySignInSignUp}")
        .Build();
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/getting-started/instantiate-confidential-client-config-options"} -->
## 機密クライアント アプリをインスタンス化する (MSAL.NET) - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/instantiate-confidential-client-config-options
- Service: msal / msal-dotnet
- Article date: 2023-08-24
- Summary: .NET (MSAL.NET) のMicrosoft Authentication Libraryを使用して、構成オプションを使用して機密クライアント アプリケーションをインスタンス化する方法について説明します。

この記事では、.NET (MSAL.NET) のMicrosoft Authentication Libraryを使用して[機密クライアント アプリケーション](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-client-applications)をインスタンス化する方法について説明します。 アプリケーションは、設定ファイルで定義された構成オプションを使用してインスタンス化されます。

アプリケーションを初期化する前に、まずアプリケーションを[登録](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/quickstart-register-app)して、アプリをMicrosoft ID プラットフォームと統合できるようにする必要があります。 登録後、次の情報が必要になる場合があります (Azure ポータルで確認できます)。

- クライアント ID (GUID を表す文字列)
- アプリケーションの ID プロバイダー URL (インスタンスという名前) とサインイン対象ユーザー。 これら 2 つのパラメーターは、総称して機関と呼ばれます。
- 組織専用の基幹業務アプリケーション (シングルテナント アプリケーションとも呼ばれます) を作成する場合のテナント ID。
- 機密クライアント アプリの場合は、アプリケーション シークレット (クライアント シークレット文字列) または証明書 (X509Certificate2 型)。
- Web アプリの場合や、パブリック クライアント アプリ (特にアプリでブローカーを使用する必要がある場合) の場合は、id プロバイダーがセキュリティ トークンを使用してアプリケーションに連絡する redirectUri も設定します。

### 構成ファイルからアプリケーションを構成する

MSAL.NET のオプションのプロパティの名前は、ASP.NET Coreの`AzureADOptions`のプロパティの名前と一致するため、接着コードを記述する必要はありません。

ASP.NET Core アプリケーションの構成については、*appsettings.json* ファイルで説明します。

```json
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "Domain": "[Enter the domain of your tenant, e.g. contoso.onmicrosoft.com]",
    "TenantId": "[Enter 'common', or 'organizations' or the Tenant Id (Obtained from the Azure portal. Select 'Endpoints' from the 'App registrations' blade and use the GUID in any of the URLs), e.g. aaaabbbb-0000-cccc-1111-dddd2222eeee]",
    "ClientId": "[Enter the Client Id (Application ID obtained from the Azure portal), e.g. 00001111-aaaa-2222-bbbb-3333cccc4444]",
    "CallbackPath": "/signin-oidc",
    "SignedOutCallbackPath ": "/signout-callback-oidc",

    "ClientSecret": "[Copy the client secret added to the app from the Azure portal]"
  },
  "Logging": {
    "LogLevel": {
      "Default": "Warning"
    }
  },
  "AllowedHosts": "*"
}
```

MSAL.NET v3.x 以降では、構成ファイルから機密クライアント アプリケーションを構成できます。

アプリケーションを構成してインスタンス化するクラスで、 `ConfidentialClientApplicationOptions` オブジェクトを宣言します。 [Microsoft.Extensions.Configuration.Binder NuGet パッケージ](https://www.nuget.org/packages/Microsoft.Extensions.Configuration.Binder)の`IConfigurationRoot.Bind()`メソッドを使用して、ソースから読み取った構成 (appconfig.json ファイルを含む) をアプリケーション オプションのインスタンスにバインドします:

```csharp
using Microsoft.Identity.Client;

private ConfidentialClientApplicationOptions _applicationOptions;
_applicationOptions = new ConfidentialClientApplicationOptions();
configuration.Bind("AzureAD", _applicationOptions);
```

これにより、 *appsettings.json* ファイルの "AzureAD" セクションのコンテンツを、 `ConfidentialClientApplicationOptions` オブジェクトの対応するプロパティにバインドできます。 次に、 `ConfidentialClientApplication` オブジェクトをビルドします。

```csharp
IConfidentialClientApplication app;
app = ConfidentialClientApplicationBuilder.CreateWithApplicationOptions(_applicationOptions)
        .Build();
```

### ランタイム構成の追加

機密クライアント アプリケーションでは、通常、ユーザーごとにキャッシュがあります。 そのため、ユーザーに関連付けられているキャッシュを取得し、それを使用することをアプリケーション ビルダーに通知する必要があります。 同様に、動的に計算されたリダイレクト URI がある場合もあります。 この場合、コードは次のようになります。

```csharp
IConfidentialClientApplication app;
var request = httpContext.Request;
var currentUri = UriHelper.BuildAbsolute(request.Scheme, request.Host, request.PathBase, _azureAdOptions.CallbackPath ?? string.Empty);
app = ConfidentialClientApplicationBuilder.CreateWithApplicationOptions(_applicationOptions)
       .WithRedirectUri(currentUri)
       .Build();
TokenCache userTokenCache = _tokenCacheProvider.SerializeCache(app.UserTokenCache,httpContext, claimsPrincipal);
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/getting-started/instantiate-public-client-config-options"} -->
## パブリック クライアント アプリをインスタンス化する (MSAL.NET) - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/instantiate-public-client-config-options
- Service: msal / msal-dotnet
- Article date: 2023-08-24
- Summary: .NET (MSAL.NET) のMicrosoft Authentication Libraryを使用して、構成オプションを使用してパブリック クライアント アプリケーションをインスタンス化する方法について説明します。

この記事では、.NET (MSAL.NET) のMicrosoft Authentication Libraryを使用して[パブリック クライアント アプリケーション](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-client-applications)をインスタンス化する方法について説明します。 アプリケーションは、設定ファイルで定義された構成オプションを使用してインスタンス化されます。

アプリケーションを初期化する前に、まずアプリケーションを[登録](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/quickstart-register-app)して、アプリをMicrosoft ID プラットフォームと統合できるようにする必要があります。 登録後、次の情報が必要になる場合があります (Azure ポータルで確認できます)。

- クライアント ID (GUID を表す文字列)
- アプリケーションの ID プロバイダー URL (インスタンスという名前) とサインイン対象ユーザー。 これら 2 つのパラメーターは、総称して機関と呼ばれます。
- 組織専用の基幹業務アプリケーション (シングルテナント アプリケーションとも呼ばれます) を作成する場合のテナント ID。
- Web アプリの場合や、パブリック クライアント アプリ (特にアプリでブローカーを使用する必要がある場合) の場合は、id プロバイダーがセキュリティ トークンを使用してアプリケーションに連絡する redirectUri も設定します。

### 既定の応答 URI

MSAL.NET 4.1 以降では、既定のリダイレクト URI (応答 URI) を `public PublicClientApplicationBuilder WithDefaultRedirectUri()` メソッドで設定できるようになりました。 このメソッドは、パブリック クライアント アプリケーションのリダイレクト URI プロパティを推奨される既定値に設定します。

このメソッドの動作は、その時点で使用しているプラットフォームによって異なります。 特定のプラットフォームで設定されるリダイレクト URI について説明する表を次に示します。

| Platform | リダイレクト URI |
| --- | --- |
| デスクトップ アプリ (.NET Framework) | `https://login.microsoftonline.com/common/oauth2/nativeclient` |
| .NET コア | `http://localhost` |

.NETの場合、MSAL.NET は、ユーザーが対話型認証にシステム ブラウザーを使用できるように、ホストに値を設定します。

Note

デスクトップ シナリオの埋め込みブラウザーの場合、使用されるリダイレクト URI は MSAL によってインターセプトされ、認証コードが返されたことを ID プロバイダーから応答が返されることを検出します。 そのため、この URI は、その URI への実際のリダイレクトを表示することなく、任意のクラウドで使用できます。 つまり、任意のクラウドで `https://login.microsoftonline.com/common/oauth2/nativeclient` を使用できます。また、使用する必要があります。 必要に応じて、MSAL とアプリの登録でリダイレクト URI を正しく構成する限り、他の URI を使用することもできます。 アプリケーション登録で既定の URI を指定すると、MSAL でのセットアップの量が最も少ないことになります。

.NET Core コンソール アプリケーションには、次の *appsettings.json* 構成ファイルが含まれる場合があります。

```json
{
  "Authentication": {
    "AzureCloudInstance": "AzurePublic",
    "AadAuthorityAudience": "AzureAdMultipleOrgs",
    "ClientId": "00001111-aaaa-2222-bbbb-3333cccc4444"
  },

  "WebAPI": {
    "MicrosoftGraphBaseEndpoint": "https://graph.microsoft.com"
  }
}
```

次のコードでは、.NET構成フレームワークを使用してこのファイルを読み取ります。

```csharp
public class SampleConfiguration
{
    /// <summary>
    /// Authentication options
    /// </summary>
    public PublicClientApplicationOptions PublicClientApplicationOptions { get; set; }

    /// <summary>
    /// Base URL for Microsoft Graph (it varies depending on whether the application is ran
    /// in Microsoft Azure public clouds or national / sovereign clouds
    /// </summary>
    public string MicrosoftGraphBaseEndpoint { get; set; }

    /// <summary>
    /// Reads the configuration from a json file
    /// </summary>
    /// <param name="path">Path to the configuration json file</param>
    /// <returns>SampleConfiguration as read from the json file</returns>
    public static SampleConfiguration ReadFromJsonFile(string path)
    {
        // .NET configuration
        IConfigurationRoot Configuration;
        var builder = new ConfigurationBuilder()
          .SetBasePath(Directory.GetCurrentDirectory())
        .AddJsonFile(path);
        Configuration = builder.Build();

        // Read the auth and graph endpoint config
        SampleConfiguration config = new SampleConfiguration()
        {
            PublicClientApplicationOptions = new PublicClientApplicationOptions()
        };
        Configuration.Bind("Authentication", config.PublicClientApplicationOptions);
        config.MicrosoftGraphBaseEndpoint = Configuration.GetValue<string>("WebAPI:MicrosoftGraphBaseEndpoint");
        return config;
    }
}
```

次のコードでは、設定ファイルの構成を使用して、アプリケーションを作成します。

```csharp
SampleConfiguration config = SampleConfiguration.ReadFromJsonFile("appsettings.json");
var app = PublicClientApplicationBuilder.CreateWithApplicationOptions(config.PublicClientApplicationOptions)
           .Build();
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/getting-started/scenarios"} -->
## MSAL.NET シナリオ - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/scenarios
- Service: msal / msal-dotnet
- Article date: 2023-03-17
- Summary: MSAL.NET でサポートされるアプリケーション シナリオと認証フローについて説明します

### はじめに

.NET認証ライブラリでは、**Web API を保護し、保護された Web API** のトークンを**取得**するシナリオがサポートされています。 MSAL.NET は後者にのみ使用されます。

開発者は、ブラウザー (または iOT) のないデバイスで実行されている Web アプリケーション、モバイル アプリケーション、デスクトップ アプリケーション、Web API、アプリケーションなど、さまざまな **種類**のアプリケーションからトークンを取得できます。 これらの種類のアプリケーションは、次の 2 つのカテゴリに分かれています。

- パブリック クライアント アプリケーション (デスクトップとモバイル) では、 [PublicClientApplication](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplication) クラスを使用します
- 機密クライアント アプリケーション (Web アプリ、Web API、デーモン アプリケーション - デスクトップまたは Web)。 これらの種類のアプリでは、 [ConfidentialClientApplication](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.confidentialclientapplication)が使用されます。

MSAL.NET では、**ユーザー**[Image: ユーザー アイコン]の名前、または (および機密クライアント アプリケーションの場合のみ) アプリケーション自体の名前 (ユーザーなし) でトークンを取得できます。 その場合、機密クライアント アプリケーションは、Microsoft Entra ID Microsoft Entra ID アイコンを使用してシークレット[Image: を共有します]

MSAL.NET では、さまざまな**プラットフォーム** (.NET Framework、.NET、.NET MAUI) がサポートされています。 .NETアプリは、異なるオペレーティング システム (Windows、Linux、macOS) でも実行できます。 シナリオは、プラットフォームによって異なる場合があります。

### シナリオ

次の図は、サポートされているシナリオをまとめたものであり、どのプラットフォームで、どのMicrosoft Entraプロトコルに対応するかを示しています。

[Image: サポートされているシナリオとプラットフォームを示す画像]

#### ユーザーをサインインさせ、ユーザーの代わりに Web API を呼び出す Web アプリ

Web アプリ (ユーザーのサインイン) を保護するには、ASP.NET OpenID Connect ミドルウェアで ASP.NET または ASP.NET Coreを使用します。 これには、MSAL.NET ではなく、[.NET ライブラリの IdentityModel 拡張機能](https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnet/wiki)によって実行されるトークンの検証が含まれます。

ユーザーの名前で Web API を呼び出すには、MSAL.NET `ConfidentialClientApplication`を使用し、[Authorization コード フロー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/authorization-codes)を利用し、取得したトークンをトークン キャッシュに格納し、必要に応じキャッシュから[トークンをサイレントで取得](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/acquiretokensilentasync-api#recommended-call-pattern-in-web-apps-using-the-authorization-code-flow-to-authenticate-the-user)します。 必要に応じて、MSAL によってトークンが更新されます。

[Image: ユーザーをサインインさせ、ユーザーに代わって Web API を呼び出す Web アプリのフローを示す画像]

#### 対話形式でサインインしているユーザーの代わりに Web API を呼び出すモバイル アプリ

モバイル アプリケーションから Web API を呼び出すには、MSAL.NET の PublicClientApplication の[対話型](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/acquiring-tokens-interactively)トークン取得メソッドを使用します。 これらの対話型メソッドを使用すると、サインイン UI エクスペリエンスと、一部のプラットフォームでの対話型ダイアログの場所を制御できます。

この対話を有効にするために、MSAL.NET [は Web ブラウザー](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-web-browsers)を利用します。 モバイル プラットフォームに応じて固有性があります。 iOS および Android では、システム ブラウザー (既定) または埋め込み Web ブラウザーのどちらを利用するかを選択できます。 iOS でトークン キャッシュ共有を有効にすることができます。

[Image: ユーザーの代わりに Web API を呼び出すモバイル アプリのフローを示す画像]

##### Intune を使用したアプリ自体の保護

モバイル アプリ (Xamarin.iOS または Xamarin で記述)。Android) にアプリ保護ポリシーを適用して、[InTune で管理](https://learn.microsoft.com/ja-jp/mem/intune/developer/app-sdk)し、Intune で管理対象アプリとして認識できるようにすることができます。 [InTune SDK](https://learn.microsoft.com/ja-jp/mem/intune/developer/app-sdk-get-started) は MSAL とは別であり、単独でMicrosoft Entra IDと通信します。

#### (独自の名前で) Web API をそれ自体として呼び出すデスクトップまたはサービス デーモン アプリ

MSAL.NET の ConfidentialClientApplication [のクライアント資格情報](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/client-credential-flows)取得方法を使用して、独自の ID を使用してトークンを取得するデーモン アプリを作成できます。 これらは、アプリが以前にシークレット (アプリケーション パスワードまたは証明書) をMicrosoft Entra IDに登録し、それをこの呼び出しと共有したとします。

[Image: 独自の ID を使用して Web API を呼び出すデーモン アプリを示す画像]

#### サインイン済みのユーザーに代わって Web API を呼び出すデスクトップ アプリ

デスクトップ アプリケーションでは、モバイル アプリケーションと同じ[対話型認証](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/acquiring-tokens-interactively)を使用できます。

[Image: サインインしているユーザーの代わりに Web API を呼び出すデスクトップ アプリのフローを示す画像]

Windowsホストされているアプリケーションの場合は、Windows ドメインに参加しているコンピューターまたは参加しているMicrosoft Entraで実行されているアプリケーションが、[統合Windows認証](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/integrated-windows-authentication)を使用してトークンをサイレントに取得することもできます。

デスクトップ アプリケーションが Linux または Mac で実行されている .NET Core アプリケーションの場合、対話型認証フロー (.NET Core では [Web ブラウザー](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-web-browsers)が提供されないため) も、統合Windows認証も使用できません。 その場合の最適なオプションは、 ブラウザーを使用しないアプリケーション、またはユーザーの名前で API を呼び出す iOT アプリケーションで説明されているように、デバイス コード フローを使用することです。

推奨されませんが、パブリック クライアント アプリケーションでは [ユーザー名とパスワードのフロー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/username-password-authentication) を使用できます。一部のシナリオ (DevOps など) では引き続き必要ですが、使用するとアプリケーションに制約が課されることに注意してください。 たとえば、Multi Factor Authentication (条件付きアクセス) を実行する必要があるユーザーをサインインさせたり、シングル サインオン (SSO) の利点を活用したりすることはできません。 ユーザー名とパスワードのフローは先進認証の原則に反し、従来の理由でのみ提供されます。

デスクトップ アプリケーションでは、トークン キャッシュを永続的にする場合は、 [トークン キャッシュのシリアル化をカスタマイズする](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-token-cache-serialization)必要があります。

#### ブラウザーのないアプリケーション、またはユーザーの名前で API を呼び出す iOT アプリケーション

ブラウザーのないデバイスで実行されているアプリケーションは、ユーザーが Web ブラウザーを持つ別のデバイスにサインインした後も、ユーザー名で API を呼び出すことが可能です。 このためには、[デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/device-code-flow)を使用する必要があります

[Image: ユーザーの代わりに API を呼び出すブラウザーレス アプリのフローを示す画像]

#### 呼び出されたユーザーの名前で別のダウンストリーム Web API を呼び出す Web API

ASP.NET または ASP.NET Core保護された Web API で、アクセス トークンによって表されるユーザーに代わって別の Web API を呼び出して API を呼び出す場合は、次の操作を行う必要があります。

- トークンを検証します。 このためには、内部で ASP.NET JWT ミドルウェアを使用します。 これには、MSAL.NET ではなく、[.NET ライブラリの IdentityModel 拡張機能](https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnet/wiki)によって行われるトークンの検証も含まれます。
- 次に、ConfidentialClientApplication のメソッドを使用してダウンストリーム Web API のトークンを取得する必要があります。サービス間呼び出しで [ユーザーに代わって](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/on-behalf-of-flow) トークンを取得します。
- 他の Web API を呼び出す Web API も、 [カスタム キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-token-cache-serialization)を提供する必要があります。

[Image: ダウンストリーム Web API を呼び出す Web API のフローを示す画像]

#### 独自の名前で別の API を呼び出す Web API

デスクトップまたはサービス デーモン アプリケーションと同様に、デーモン Web API (またはデーモン Web アプリ) でも、MSAL.NET の ConfidentialClientApplication の[クライアント資格情報](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/client-credential-flows)取得方法を使用できます。

### 横の特徴

すべてのシナリオで、次の操作を実行できます。

- [ログ](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-logging-dotnet)またはテレメトリをアクティブ化して自分のトラブルシューティングを行う
- Microsoft Entra サービスの[`MsalServiceException`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalserviceexception?view=azure-dotnet-preview#fields&preserve-view=true)、またはクライアント自体で何か問題が発生したために[例外](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/)に対応する方法を理解する[`MsalClientException`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalclientexception?view=azure-dotnet-preview#fields&preserve-view=true)
- [プロキシ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/httpclient)で MSAL.NET を使用する
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/build-apps-on-linux-ubuntu"} -->
## Linux での MSAL.NET アプリケーションのビルド - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/build-apps-on-linux-ubuntu
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: Linux での MSAL.NET アプリケーションのビルド

Linux テスト用のコンソール アプリを作成します。 現時点では、 [#2839](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/issues/2839) をテストしています。

```csharp
class Program
    {
        public static string ClientID = "your client id"; //msidentity-samples-testing tenant
        public static string[] Scopes = { "User.Read" };
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");

            var pcaBuilder = PublicClientApplicationBuilder.Create(ClientID)
                .WithRedirectUri("http://localhost")
                .Build();

            AcquireTokenInteractiveParameterBuilder atparamBuilder = pcaBuilder.AcquireTokenInteractive(Scopes);

            AuthenticationResult authenticationResult = atparamBuilder.ExecuteAsync().GetAwaiter().GetResult();
            System.Console.WriteLine(authenticationResult.AccessToken);
        }
    }
```

### セットアップ方法

Ubuntu マシン上

- VS Code のダウンロード
- ダウンロード フォルダーから "アプリ" フォルダーにファイルをコピーします。
- フォルダーに NuGet パッケージ `~/LocalNuget` ダウンロードします。

### ビルド方法

VS Code ターミナルから:

- [アプリ] フォルダーに移動する
- コマンドの実行 `dotnet add package Microsoft.Identity.Client --prerelease -s ~/LocalNuget` これにより、最新のパッケージがプロジェクトに追加されます
- コマンドの実行 `dotnet build` これにより、デバッグ モードでアプリがビルドされます

### 実行方法

PowerShell ターミナルから:

- `app/bin/Debug/net6`フォルダーに移動しました。
- `dotnet TestApp.dll` を実行します。 これにより、アプリが実行されます。
- sudo モードでテストするには、次のコマンドを実行します。 `sudo dotnet TestApp.dll`
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/cache-options"} -->
## MSAL.NET のキャッシュ オプション - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/cache-options
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: MSAL.NET のキャッシュ オプション

### キャッシュ オプションの設定

```csharp

var app = ConfidentialClientApplicationBuilder.Create(ClientId)
               .WithCertificate(cert)                                                               
               .Build();

// The App token cache is used by `AcquireTokenForClient`, which gets tokens on behalf of service principals
app.AppTokenCache.SetCacheOptions(CacheOptions.EnableSharedCacheOptions);

// The User token cache is used by all other AcquireToken* methods, which get tokens on behalf of users
app.UserTokenCache.SetCacheOptions(CacheOptions.EnableSharedCacheOptions);
```

### [キャッシュ オプション]

`EnableSharedCacheOptions` - キャッシュを静的にし、 `ConfidentialClientApplication`のすべてのインスタンス間で共有されるようにします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/create-config-for-mam-conditional-access"} -->
## Intune モバイル アプリ管理の条件付きアクセスの構成の作成 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/create-config-for-mam-conditional-access
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: このシナリオには、バックエンド アプリケーションと、iOS および Android クライアント アプリケーションが含まれます。 この記事では、Intune MAM 用にこれらのアプリケーションを正しく構成する手順について説明します。

### シナリオ

クライアント アプリケーションのユーザーが、バックエンド アプリケーション内の特定のアクセス許可 (スコープ) によって保護されたリソースにアクセスする必要があるシナリオがあります。 リソースにアクセスできるのは、特定のアプリ保護ポリシーとアクセス条件が満たされている場合のみです。 このような状況では、アクセス トークンは条件が満たされた場合にのみ発行されます。

このシナリオには、バックエンド アプリケーションと、iOS および Android クライアント アプリケーションが含まれます。 2 つのプラットフォームのセットアップが若干異なります。 この記事では、上記のシナリオが機能するようにこれらのアプリケーションを正しく構成する手順について説明します。 同時に、この記事では詳細な情報が表示されないようにします。 詳細については、 [Intune モバイル アプリ管理に関するページを参照してください](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-management)。

### アプリケーションのセットアップ

#### テスト用にユーザーとグループを設定する

1. [Microsoft Entra ID](https://portal.azure.com/#blade/Microsoft_AAD_IAM/ActiveDirectoryMenuBlade/Overview) にサインインします。
2. テスト ユーザー (例: `XamTestuser@XamTester.onmicrosoft.com`) を作成します。
3. ユーザー プロファイル ページで、[ライセンス] に移動 **します**。
4. **割り当て** をクリックし、次を選択します：

    - Microsoft Entra ID P1 または P2 ライセンス
    - Enterprise Mobility + Security
    - Intune
    - Microsoft 365 Business標準

    Note

    これらのポリシーは、ゲスト ユーザーには適用されません。
5. テスト グループを作成します (例: `MAM_Test_Users`)。

    Note

    グループの名前を忘れないでください。 これは後の段階で割り当てる必要があります。
6. 新しいユーザーをこのグループに追加します。

#### エンタープライズ アプリと条件付きアクセス ポリシーを設定する

バックエンド アプリケーションを登録します。

1. [Microsoft Entra ID](https://portal.azure.com/#blade/Microsoft_AAD_IAM/ActiveDirectoryMenuBlade/Overview)で、[エンタープライズ アプリケーション] セクションに移動します。
2. [ **新しいアプリケーション**] をクリックします。
3. [ **独自のアプリケーションの作成**] をクリックします。
4. [**アプリケーションを登録してMicrosoft Entra IDと統合する (開発中のアプリ)] オプションを**選択します。
5. **[作成**] 画面の後、[**アプリケーションの登録**] 画面が表示されます。
6. **[マルチテナント**] を選択し、[**登録**] をクリックします。
7. これにより、#1 の画面が表示されます。

条件付きアクセスを有効にする:

1. **エンタープライズ アプリケーション**に移動します。
2. 作成したアプリケーションを選択します。
3. 前に作成したユーザー グループを割り当てます。
4. [ **条件付きアクセス**] をクリックします。
5. **新規ポリシー**をクリックし、次を選択します:
    - **[ユーザーまたはワークロード ID]** で、先ほど作成したグループを選択します。
    - **Cloud Apps またはアクション**で、作成されたエンタープライズ アプリケーションがあることを確認します。
    - **[条件]**で、複数のオプションを選択します。
        - **デバイス プラットフォーム** - **[はい** ] を選択し、[ **iOS + Android**] を選択します。
        - **クライアント アプリ** - **[はい** ] を選択し、すべてのオプションを選択します。
    - [ **許可]** で、[ **アプリ保護ポリシーを要求する**] を選択します。
    - [ **ポリシーの有効化**] 画面の下部にある **[オン**] を選択します。
6. **Create** をクリックしてください。

アクセス許可 (スコープなど) を構成します。

1. **[アプリの登録**] に移動します。 (注: これは **エンタープライズ アプリケーション**ではありません)。
2. 作成したアプリを選択します。
3. [**アプリケーション ID URI の追加] を**クリックします。
4. [ **スコープの追加]** をクリックします (例: Hello.World)。
5. Guid と **アプリケーション ID URI が** 生成され、スコープを作成するように求められます。
6. スコープの URI をメモします。 これは、クライアント アプリケーションで必要です。
7. [ **API のアクセス許可** ] セクションをクリックします。
8. [ **アクセス許可の追加]** をクリックします。
9. 前のステージで作成したアクセス許可を選択し、[ **アクセス許可の追加**] をクリックします。
10. もう一度 [ **アクセス許可の追加]** をクリックします。
11. **[所属する組織で使用している API]** を選択します。
12. **Microsoftモバイル アプリケーション管理 - DeviceManagementManagedApps.ReadWrite** を選択します。
13. [ **アクセス許可の追加]** をクリックします。
14. **管理者の同意を付与します** 。

#### クライアント アプリのセットアップ

1. [Microsoft Entra ID](https://portal.azure.com/#blade/Microsoft_AAD_IAM/ActiveDirectoryMenuBlade/Overview)で、[**アプリの登録]** に移動します。
2. 新しいアプリを作成し、 **マルチテナント** オプションを選択します。
3. iOS 用のプラットフォーム URI を追加します。
4. Android 用のプラットフォーム URI を追加します。
5. **API アクセス許可**に移動します。
6. バックエンド アプリケーションで作成されたスコープのアクセス許可を追加します。
    1. [ **アクセス許可の追加]** をクリックします。
    2. **[マイ API] を選択します**。
    3. バックエンド アプリで追加されたスコープ (つまり、Hello.World) を選択します。
    4. もう一度 [ **アクセス許可の追加]** をクリックします
    5. **[所属する組織で使用している API]** を選択します。
    6. **Microsoftモバイル アプリケーション管理 - DeviceManagementManagedApps.ReadWrite** を選択します。
    7. [ **アクセス許可の追加]** をクリックします。
    8. [ **テナントに管理者の同意を付与する** ] を選択します ( **[管理者の同意が必要]** 列に **[いいえ**] と表示されている場合でも)。

### Intune でのセットアップ

#### iOS クライアント アプリをビルドする

1. Microsoft Entra IDのクライアント ID を使用してスケルトン アプリを構築します。
2. iOS アプリが **Xamarin.Intune.MAM.SDK.iOS** パッケージを参照していることを確認してください。
3. iOS の場合、IPA ファイルをビルドする必要があります。

#### Android クライアント アプリをビルドする

1. Microsoft Entra IDのクライアント ID を使用してスケルトン アプリを構築します。
2. Android アプリが **Microsoft.Intune.MAM.Xamarin.Android** パッケージを参照していることを確認してください。
3. Android の場合、APK ファイルをビルドする必要があります。

#### Intune でアプリをセットアップする

同じ手順を 2 回実行する必要があります。iOS アプリの場合は 1 回、Android アプリの場合は、記載されている違いがあります。

1. **[Intune ポータル](https://endpoint.microsoft.com/)に移動します。**
2. アプリを作成します。
    - iOS の場合は、[ **アプリ**&gt;**iOS アプリ** ] セクションをクリックします。
    - Android の場合は、[**アプリ**] -&gt;**[Android アプリ**] セクションをクリックします。
3. [**] を選択し、[**] を追加します。
4. 基幹**業務アプリ**として **[アプリの種類]** を選択します。
5. ビルド ファイルをアップロードします。
    - iOS の場合は、ビルドされた .ipa ファイルを選択します。
    - Android の場合は、ビルドされた.apk ファイルを選択します。
6. アプリの情報 (**Publisher**名など) に**情報**を入力する必要がある場合があります。
7. [ **割り当て** ] 画面の [ **登録済みデバイスで使用可能]**の下で、次の手順を実行します。
    - [ **すべてのユーザーの追加] を選択します**。
8. [ **割り当て] 画面の** [ **登録の有無にかかわらず使用可能]**で、次の手順を実行します。
    - **[グループの追加**] を選択します。
    - 作成されたグループを選択します。
9. [ **作成]** を選択して、クライアント アプリケーションの登録を完了します。

#### Intune でアプリ保護ポリシーを設定する

同じ手順を 2 回実行する必要があります。iOS アプリの場合は 1 回、Android アプリの場合は、記載されている違いがあります。

1. **[Intune ポータル](https://endpoint.microsoft.com/)に移動します。**
2. **アプリ**&gt;**App Protection ポリシー**に移動します。
    - iOS の場合は、[ **ポリシーの作成] iOS/MacOS** をクリックします。
    - Android の場合は、[ **ポリシー Android の作成**] をクリックします。
3. **[基本**] 画面の後、[**アプリ**] 画面に移動します。
4. [ **アプリ** ] 画面で、[ **ターゲット ポリシー]** を **[選択したアプリ]** に設定します。
5. [ **カスタム アプリ**] で、作成したアプリを選択します。
6. **[データ保護**] 画面で、必要なオプションを選択します。次に例を示します。
    - **組織データを他のアプリに送信する** - **ポリシーで管理されているアプリ**。
    - **組織データのコピーを保存します** - **ブロック**。
7. [ **次へ** ] をクリックして、[ **アクセス要件** ] 画面に進みます。
8. **[アクセスの要件**] で、既定値をそのまま使用します。
9. **条件付き起動**では、既定値をそのまま使用します。
10. **割り当て**&gt;**含まれるグループ**で、作成したグループを追加します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/custom-token-cache-in-public-client-applications"} -->
## パブリック クライアント アプリケーションのカスタム トークン キャッシュ - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/custom-token-cache-in-public-client-applications
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: この記事では、パブリック クライアント アプリケーションのカスタム トークン キャッシュ実装について説明します。

この記事では、パブリック クライアント アプリケーションのカスタム トークン キャッシュ実装について説明します。 トークン キャッシュのシリアル化に関するコンテキストと一般的な情報については、「 [トークン キャッシュのシリアル化」](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-token-cache-serialization)を参照してください。

### 単純なトークン キャッシュのシリアル化 (MSAL のみ)

デスクトップ アプリケーションのトークン キャッシュのカスタム シリアル化の単純な実装の例を次に示します。 ここでは、アプリケーションと同じフォルダー内のファイル内のユーザー トークン キャッシュ。

アプリケーションをビルドした後、アプリケーションを渡す `TokenCacheHelper.EnableSerialization()` を呼び出してシリアル化を有効にします `UserTokenCache`

```csharp
app = PublicClientApplicationBuilder.Create(ClientId)
    .Build();
TokenCacheHelper.EnableSerialization(app.UserTokenCache);
```

このヘルパー クラスは次のようになります。

```csharp
static class TokenCacheHelper
 {
  public static void EnableSerialization(ITokenCache tokenCache)
  {
   tokenCache.SetBeforeAccess(BeforeAccessNotification);
   tokenCache.SetAfterAccess(AfterAccessNotification);
  }

  /// <summary>
  /// Path to the token cache
  /// </summary>
  public static readonly string CacheFilePath = System.Reflection.Assembly.GetExecutingAssembly().Location + ".msalcache.bin3";

  private static readonly object FileLock = new object();

  private static void BeforeAccessNotification(TokenCacheNotificationArgs args)
  {
   lock (FileLock)
   {
    args.TokenCache.DeserializeMsalV3(File.Exists(CacheFilePath)
            ? ProtectedData.Unprotect(File.ReadAllBytes(CacheFilePath),
                                      null,
                                      DataProtectionScope.CurrentUser)
            : null);
   }
  }

  private static void AfterAccessNotification(TokenCacheNotificationArgs args)
  {
   // if the access operation resulted in a cache update
   if (args.HasStateChanged)
   {
    lock (FileLock)
    {
     // reflect changes in the persistent store
     File.WriteAllBytes(CacheFilePath,
                         ProtectedData.Protect(args.TokenCache.SerializeMsalV3(),
                                                 null,
                                                 DataProtectionScope.CurrentUser)
                         );
    }
   }
  }
 }
```

パブリック クライアント アプリケーション (Windows、Mac、Linux で実行されるデスクトップ アプリケーション向け) 用の、製品品質のトークン キャッシュ ファイルベースのシリアライザーのプレビューは、オープン ソース ライブラリ [Microsoft.Identity.Client.Extensions.Msal](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/tree/main/src/client/Microsoft.Identity.Client.Extensions.Msal) から入手できます。 これは、次の NuGet パッケージからアプリケーションに含めることができます: [Microsoft。Identity.Client.Extensions.Msal](https://www.nuget.org/packages/Microsoft.Identity.Client.Extensions.Msal/)。

>
> 免責 Microsoft。Identity.Client.Extensions.Msal ライブラリは、MSAL.NET 上の拡張機能です。 これらのライブラリのクラスは、将来、そのまま、または破壊的変更を伴って、MSAL.NET に取り込まれる可能性があります。

### デュアル トークン キャッシュのシリアル化 (MSAL 統合キャッシュ)

統合キャッシュ形式でトークン キャッシュのシリアル化を実装する場合は、次のサンプルを参照してください。

```csharp
string appLocation = Path.GetDirectoryName(Assembly.GetEntryAssembly().Location;
string cacheFolder = Path.GetFullPath(appLocation) + @"..\..\..\..");
string adalV3cacheFileName = Path.Combine(cacheFolder, "cacheAdalV3.bin");
string unifiedCacheFileName = Path.Combine(cacheFolder, "unifiedCache.bin");

IPublicClientApplication app;
app = PublicClientApplicationBuilder.Create(clientId)
                                    .Build();
FilesBasedTokenCacheHelper.EnableSerialization(app.UserTokenCache,
                                               unifiedCacheFileName,
                                               adalV3cacheFileName);

```

今回のヘルパー クラスは次のようになります。

```csharp
using System;
using System.IO;
using System.Security.Cryptography;
using Microsoft.Identity.Client;

namespace CommonCacheMsalV3
{
 /// <summary>
 /// Simple persistent cache implementation of the dual cache serialization (ADAL V3 legacy
 /// and unified cache format) for a desktop applications (from MSAL 2.x)
 /// </summary>
 static class FilesBasedTokenCacheHelper
 {
  /// <summary>
  /// Get the user token cache
  /// </summary>
  /// <param name="adalV3CacheFileName">File name where the cache is serialized with the
  /// ADAL V3 token cache format. Can
  /// be <c>null</c> if you don't want to implement the legacy ADAL V3 token cache
  /// serialization in your MSAL 2.x+ application</param>
  /// <param name="unifiedCacheFileName">File name where the cache is serialized
  /// with the Unified cache format, common to
  /// ADAL V4 and MSAL V2 and above, and also across ADAL/MSAL on the same platform.
  ///  Should not be <c>null</c></param>
  /// <returns></returns>
  public static void EnableSerialization(ITokenCache tokenCache, string unifiedCacheFileName, string adalV3CacheFileName)
  {
   UnifiedCacheFileName = unifiedCacheFileName;
   AdalV3CacheFileName = adalV3CacheFileName;

   tokenCache.SetBeforeAccess(BeforeAccessNotification);
   tokenCache.SetAfterAccess(AfterAccessNotification);
  }

  /// <summary>
  /// File path where the token cache is serialized with the unified cache format
  /// (ADAL.NET V4, MSAL.NET V3)
  /// </summary>
  public static string UnifiedCacheFileName { get; private set; }

  /// <summary>
  /// File path where the token cache is serialized with the legacy ADAL V3 format
  /// </summary>
  public static string AdalV3CacheFileName { get; private set; }

  private static readonly object FileLock = new object();

  public static void BeforeAccessNotification(TokenCacheNotificationArgs args)
  {
   lock (FileLock)
   {
    args.TokenCache.DeserializeAdalV3(ReadFromFileIfExists(AdalV3CacheFileName));
    try
    {
     args.TokenCache.DeserializeMsalV3(ReadFromFileIfExists(UnifiedCacheFileName));
    }
    catch(Exception ex)
    {
     // Compatibility with the MSAL v2 cache if you used one
     args.TokenCache.DeserializeMsalV2(ReadFromFileIfExists(UnifiedCacheFileName));
    }
   }
  }

  public static void AfterAccessNotification(TokenCacheNotificationArgs args)
  {
   // if the access operation resulted in a cache update
   if (args.HasStateChanged)
   {
    lock (FileLock)
    {
     WriteToFileIfNotNull(UnifiedCacheFileName, args.TokenCache.SerializeMsalV3());
     if (!string.IsNullOrWhiteSpace(AdalV3CacheFileName))
     {
      WriteToFileIfNotNull(AdalV3CacheFileName, args.TokenCache.SerializeAdalV3());
     }
    }
   }
  }

  /// <summary>
  /// Read the content of a file if it exists
  /// </summary>
  /// <param name="path">File path</param>
  /// <returns>Content of the file (in bytes)</returns>
  private static byte[] ReadFromFileIfExists(string path)
  {
   byte[] protectedBytes = (!string.IsNullOrEmpty(path) && File.Exists(path))
       ? File.ReadAllBytes(path) : null;
   byte[] unprotectedBytes = encrypt ?
       ((protectedBytes != null) ? ProtectedData.Unprotect(protectedBytes, null, DataProtectionScope.CurrentUser) : null)
       : protectedBytes;
   return unprotectedBytes;
  }

  /// <summary>
  /// Writes a blob of bytes to a file. If the blob is <c>null</c>, deletes the file
  /// </summary>
  /// <param name="path">path to the file to write</param>
  /// <param name="blob">Blob of bytes to write</param>
  private static void WriteToFileIfNotNull(string path, byte[] blob)
  {
   if (blob != null)
   {
    byte[] protectedBytes = encrypt
      ? ProtectedData.Protect(blob, null, DataProtectionScope.CurrentUser)
      : blob;
    File.WriteAllBytes(path, protectedBytes);
   }
   else
   {
    File.Delete(path);
   }
  }

  // Change if you want to test with an un-encrypted blob (this is a json format)
  private static bool encrypt = true;
 }
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/default-reply-uri"} -->
## 既定の応答 URI - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/default-reply-uri
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: MSAL.NET を使用してアプリケーションの応答 URI をカスタマイズする方法。

MSAL.NET 既定のリダイレクト URI (応答 URI とも呼ばれます) は、[WithDefaultRedirectUri()](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplicationbuilder.withdefaultredirecturi#microsoft-identity-client-publicclientapplicationbuilder-withdefaultredirecturi)で設定できます。 このメソッドは、パブリック クライアント アプリケーションのリダイレクト URI プロパティを、パブリック クライアント アプリケーションの既定の推奨リダイレクト URI に設定します。

このメソッドの動作は、その時点で使用しているプラットフォームによって異なります。 特定のプラットフォームで設定されるリダイレクト URI について説明する表を次に示します。

| Platform | リダイレクトURI |
| --- | --- |
| Desktop (.NET Framework) | `https://login.microsoftonline.com/common/oauth2/nativeclient` |
| .NET コア | `http://localhost` |

.NET Core では、.NET Core には現在埋め込み Web ビューの UI がないため、ユーザーが対話型認証にシステム ブラウザーを使用できるように、ローカル ホストに値を設定しています。

Note

デスクトップ シナリオの埋め込みブラウザーの場合、使用されるリダイレクト URI は MSAL によってインターセプトされ、認証コードが返されたことを ID プロバイダーから応答が返されることを検出します。 この URI は、その URI への実際のリダイレクトを表示せずに、任意のクラウドで使用できます。 つまり、任意のクラウドで `https://login.microsoftonline.com/common/oauth2/nativeclient` を使用できます。また、使用する必要があります。 必要に応じて、MSAL でリダイレクト URI を正しく構成する限り、これを別の URI に変換することもできます。 アプリケーション登録で上記を指定すると、MSAL のセットアップ量が最も少ないことになります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/differences-adal-msal-net"} -->
## ADAL.NET アプリと MSAL.NET アプリの違い - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/differences-adal-msal-net
- Service: msal / msal-dotnet
- Article date: 2023-08-24
- Summary: .NET (MSAL.NET) のMicrosoft Authentication Libraryと .NET 用 AZURE AD Authentication Library (ADAL.NET) の違いについて説明します。

アプリケーションを ADAL の使用から MSAL の使用に移行するには、セキュリティと回復性の利点があります。 この記事では、MSAL.NET と ADAL.NET の違いについて説明します。 すべての新しいアプリケーションでは、MSAL.NET とMicrosoft ID プラットフォームを使用する必要があります。これは、Microsoft認証ライブラリの最新世代です。 MSAL.NET を使用すると、ユーザーが Microsoft Entra ID (職場および学校アカウント)、Microsoft (個人) アカウント (MSA)、または AD B2C Azureを使用してアプリケーションにサインインするためのトークンを取得します。 ADAL を使用している既存のアプリケーションがある場合.NET MSAL.NET に移行します。

アプリケーションで以前のバージョンの [Active Directory フェデレーション サービス (AD FS) (ADFS)](https://learn.microsoft.com/ja-jp/windows-server/identity/active-directory-federation-services) を使用してユーザーをサインインさせる必要がある場合は.NET ADAL を引き続き使用する必要があります。 詳細については、 [ADFS のサポート](https://aka.ms/msal-net-adfs-support)を参照してください。

### Prerequisites

[MSAL の概要](https://learn.microsoft.com/ja-jp/entra/msal/overview)を参照して、MSAL の詳細を確認してください。

### 相違点

| - | **ADAL NET** | **MSAL NET** |
| --- | --- | --- |
| **NuGet パッケージと名前空間** | Microsoftから ADAL が使用されました[。IdentityModel.Clients.ActiveDirectory](https://www.nuget.org/packages/Microsoft.IdentityModel.Clients.ActiveDirectory) NuGet パッケージ。 名前空間が `Microsoft.IdentityModel.Clients.ActiveDirectory`されました。 | Microsoftを追加します[。Identity.Client](https://www.nuget.org/packages/Microsoft.Identity.Client) NuGet パッケージ。`Microsoft.Identity.Client`名前空間を使用します。 機密クライアント アプリケーションを構築している場合は、Microsoftを確認してください[。Identity.Web](https://www.nuget.org/packages/Microsoft.Identity.Web)。 |
| **スコープとリソース** | ADAL.NET は*リソース*のトークンを取得します。 | MSAL.NET スコープのトークンを取得*します*。 いくつかの MSAL.NET `AcquireTokenXXX`オーバーライドには、scopes(`IEnumerable<string> scopes`) というパラメーターが必要です。 このパラメーターは、要求されるアクセス許可とリソースを宣言する文字列の単純なリストです。 既知のスコープは[、Microsoft Graphのスコープです](https://learn.microsoft.com/ja-jp/graph/permissions-reference)。 MSAL.NET を使用して v1.0 リソースにアクセスすることもできます。 |
| **コア クラス** | ADAL.NET [AuthenticationContext](https://github.com/AzureAD/azure-activedirectory-library-for-dotnet/wiki/AuthenticationContext:-the-connection-to-Azure-AD) は、機関を介したセキュリティ トークン サービス (STS) または承認サーバーへの接続の表現として使用されます。 | MSAL.NET は[、クライアント アプリケーションを中心に設計されています](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/wiki/Client-Applications)。 パブリック クライアント アプリケーションの `IPublicClientApplication` インターフェイスと機密クライアント アプリケーションの `IConfidentialClientApplication` 、および両方の種類のアプリケーションに共通するコントラクトの基本インターフェイス `IClientApplicationBase` を定義します。 |
| **トークンの取得** | パブリック クライアントでは、ADAL は認証呼び出しに `AcquireTokenAsync` と `AcquireTokenSilentAsync` を使用します。 | パブリック クライアントでは、MSAL は同じ認証呼び出しに `AcquireTokenInteractive` と `AcquireTokenSilent` を使用します。 パラメーターは ADAL とは異なります。 Confidential クライアント アプリケーションには、シナリオに応じて明示的な名前を持つ [トークン取得方法](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/wiki/Acquiring-Tokens) があります。 もう 1 つの違いは、MSAL.NET では、AcquireTokenXX 呼び出しごとにアプリケーションの`ClientID`を渡す必要がなくなったということです。 `ClientID`は、`IPublicClientApplication`または`IConfidentialClientApplication`を構築するときに 1 回だけ設定されます。 |
| **IAccount と IUser** | ADAL は、IUser インターフェイスを介してユーザーの概念を定義します。 ただし、ユーザーは人間またはソフトウェア エージェントです。 そのため、ユーザーは、Microsoft ID プラットフォーム内の 1 つ以上のアカウント (複数のMicrosoft Entra アカウント、Azure AD B2C、Microsoft個人用アカウント) を所有できます。 ユーザーは、1 つ以上のMicrosoft ID プラットフォーム アカウントに対する責任を負うこともできます。 | MSAL.NET では、(IAccount インターフェイスを使用して) アカウントの概念を定義します。 IAccount インターフェイスは、1 つのアカウントに関する情報を表します。 ユーザーは、異なるテナントに複数のアカウントを持つことができます。 MSAL.NET は、ホーム アカウント情報が提供されるため、ゲスト シナリオでより適切な情報を提供します。 [IUser と IAccount の違いの](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/wiki/msal-net-2-released#iuser-is-replaced-by-iaccount)詳細を確認できます。 |
| **キャッシュの永続化** | ADAL.NET を使用すると、`TokenCache` クラスを拡張して、`BeforeAccess`メソッドと`BeforeWrite`メソッドを使用して、セキュリティで保護されたストレージ (.NET Framework と .NET コア) のないプラットフォームに必要な永続化機能を実装できます。 詳細については、[ADAL でのトークン キャッシュのシリアル化.NET](https://github.com/AzureAD/azure-activedirectory-library-for-dotnet/wiki/Token-cache-serialization)を参照してください。 | MSAL.NET トークン キャッシュをシール クラスにし、それを拡張する機能を削除します。 そのため、トークン キャッシュ永続化の実装は、シールされたトークン キャッシュと対話するヘルパー クラスの形式である必要があります。 この相互作用については、[MSAL.NET 記事のトークン キャッシュのシリアル化で](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/wiki/token-cache-serialization)説明されています。 パブリック クライアント アプリケーションのシリアル化 ( [パブリック クライアント アプリケーションのトークン キャッシュを](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/wiki/token-cache-serialization#token-cache-for-a-public-client-application)参照) は、機密クライアント アプリケーションのシリアル化とは異なります ( [Web アプリまたは Web API のトークン キャッシュを](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/wiki/token-cache-serialization#token-cache-for-a-public-client-application)参照)。 |
| **共通機関** | ADAL では、Azure AD v1.0 が使用されます。 `https://login.microsoftonline.com/common`Azure AD v1.0 (ADAL が使用する) の機関を使用すると、ユーザーは任意のMicrosoft Entra組織 (職場または学校) アカウントを使用してサインインできます。 AZURE AD v1.0 では、個人アカウントMicrosoftサインインできません。 詳細については、[ADAL での権限検証.NET](https://github.com/AzureAD/azure-activedirectory-library-for-dotnet/wiki/AuthenticationContext:-the-connection-to-Azure-AD#authority-validation)を参照してください。 | MSAL では、Azure AD v2.0 が使用されます。 `https://login.microsoftonline.com/common`Azure AD v2.0 (MSAL が使用する) の機関を使用すると、ユーザーは任意のMicrosoft Entra組織 (職場または学校) アカウントまたはMicrosoft個人アカウントでサインインできます。 MSAL の組織アカウント (職場または学校アカウント) のみを使用してサインインを制限するには、 `https://login.microsoftonline.com/organizations` エンドポイントを使用する必要があります。 詳細については、[パブリック クライアント アプリケーション](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/wiki/Client-Applications#publicclientapplication)の `authority` パラメーターを参照してください。 |

### サポートされている許可

パブリック クライアント アプリケーションと機密クライアント アプリケーションの両方.NETサポートされている許可 MSAL.NET と ADAL を比較した概要を次に示します。

#### パブリック クライアント アプリケーション

次の図は、パブリック クライアント アプリケーションの ADAL.NET と MSAL.NET の違いをいくつかまとめたものです。

[Image: パブリック クライアント アプリケーションの ADAL.NET と MSAL.NET の違いを示すスクリーンショット。]

デスクトップ アプリケーションとモバイル アプリケーションの ADAL.NETおよび MSAL.NET でサポートされている許可を次に示します。

| Grant | MSAL.NET | ADAL.NET |
| --- | --- | --- |
| Interactive | [MSAL.NET で対話形式でトークンを取得する](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-desktop-acquire-token-interactive) | [対話型認証](https://github.com/AzureAD/azure-activedirectory-library-for-dotnet/wiki/Acquiring-tokens-interactively---Public-client-application-flows) |
| 統合Windows認証 | [統合 Windows 認証](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-desktop-acquire-token-integrated-windows-authentication) | [Windowsでの統合認証 (Kerberos)](https://github.com/AzureAD/azure-activedirectory-library-for-dotnet/wiki/AcquireTokenSilentAsync-using-Integrated-authentication-on-Windows-%28Kerberos%29) |
| ユーザー名/パスワード | [ユーザー名とパスワードの認証](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-desktop-acquire-token-username-password) | [ユーザー名とパスワードを使用してトークンを取得する](https://github.com/AzureAD/azure-activedirectory-library-for-dotnet/wiki/Acquiring-tokens-with-username-and-password) |
| デバイス コード フロー | [デバイス コード フロー](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-desktop-acquire-token-device-code-flow) | [Web ブラウザーのないデバイスのデバイス プロファイル](https://github.com/AzureAD/azure-activedirectory-library-for-dotnet/wiki/Device-profile-for-devices-without-web-browsers) |

#### 機密クライアント アプリケーション

次の図は、機密クライアント アプリケーションの ADAL.NET と MSAL.NET の違いをいくつかまとめたものです。

[Image: 機密クライアント アプリケーションの ADAL.NET と MSAL.NET の違いをいくつか示すスクリーンショット。]

ADAL、.NET、MSAL.NET、Microsoftでサポートされている許可を次に示します。Web アプリケーション、Web API、デーモン アプリケーション用の Identity.Web。

| アプリの種類 | Grant | MSAL.NET | ADAL.NET |
| --- | --- | --- | --- |
| Web アプリ, Web API, デーモン | クライアントの資格情報 | [MSAL.NET 内のクライアント資格情報フロー](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-daemon-acquire-token#acquiretokenforclient-api) | [ADAL のクライアント資格情報フロー.NET](https://github.com/AzureAD/azure-activedirectory-library-for-dotnet/wiki/Client-credential-flows) |
| Web API | 代理 | [MSAL.NET の代理](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/wiki/on-behalf-of) | [ADAL を使用してユーザーに代わってサービス間呼び出しを行います.NET](https://github.com/AzureAD/azure-activedirectory-library-for-dotnet/wiki/Service-to-service-calls-on-behalf-of-the-user) |
| Web アプリケーション | 認証コード | [A MSAL.NET を使用して Web アプリで承認コードを使用してトークンを取得する](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-web-app-call-api-acquire-token) | [ADAL を使用した Web アプリでの承認コードを使用したトークンの取得.NET](https://github.com/AzureAD/azure-activedirectory-library-for-dotnet/wiki/Acquiring-tokens-with-authorization-codes-on-web-apps) |

### 更新トークンを使用した ADAL 2.x からの移行

ADAL では.NET v2 です。X、更新トークンが公開され、これらのトークンをキャッシュし、ADAL 2.x によって提供される`AcquireTokenByRefreshToken`メソッドを使用して、これらのトークンの使用に関するソリューションを開発できるようになりました。

これらのソリューションの一部は、次のようなシナリオで使用されました。

- ユーザーがアプリに接続またはサインインしなくなったときにユーザーのダッシュボードを更新するなど、アクションを実行する実行時間の長いサービス。
- クライアントが更新トークンを Web サービスに持ち込むことができるようにする WebFarm シナリオ (キャッシュはクライアント側で行われ、暗号化された Cookie はサーバー側ではありません)。

MSAL.NET では、セキュリティ上の理由から更新トークンは公開されません。 MSAL は更新トークンを自動的に処理します。

さいわい、MSAL.NET には、以前の更新トークン (ADAL で取得) を`IConfidentialClientApplication`に移行できる API があります。

```csharp
/// <summary>
/// Acquires an access token from an existing refresh token and stores it and the refresh token into
/// the application user token cache, where it will be available for further AcquireTokenSilent calls.
/// This method can be used in migration to MSAL from ADAL v2 and in various integration
/// scenarios where you have a RefreshToken available.
/// (see https://aka.ms/msal-net-migration-adal2-msal2)
/// </summary>
/// <param name="scopes">Scope to request from the token endpoint.
/// Setting this to null or empty will request an access token, refresh token and ID token with default scopes</param>
/// <param name="refreshToken">The refresh token from ADAL 2.x</param>
IByRefreshToken.AcquireTokenByRefreshToken(IEnumerable<string> scopes, string refreshToken);
```

このメソッドを使用すると、以前に使用した更新トークンと、必要なスコープ (リソース) を指定できます。 更新トークンは新しいものと交換され、アプリケーションにキャッシュされます。

この方法は一般的ではないシナリオを対象としているため、最初に`IByRefreshToken`にキャストしないと、`IConfidentialClientApplication`で簡単にアクセスできません。

次のコード スニペットは、機密クライアント アプリケーションの移行コードを示しています。

```csharp
TokenCache userCache = GetTokenCacheForSignedInUser();
string rt = GetCachedRefreshTokenForSignedInUser();

IConfidentialClientApplication app;
app = ConfidentialClientApplicationBuilder.Create(clientId)
 .WithAuthority(Authority)
 .WithRedirectUri(RedirectUri)
 .WithClientSecret(ClientSecret)
 .Build();
IByRefreshToken appRt = app as IByRefreshToken;

AuthenticationResult result = await appRt.AcquireTokenByRefreshToken(null, rt)
                                         .ExecuteAsync()
                                         .ConfigureAwait(false);
```

`GetCachedRefreshTokenForSignedInUser` は、ADAL 2.x を使用するために使用されていた以前のバージョンのアプリケーションによって、ストレージに格納された更新トークンを取得します。 `GetTokenCacheForSignedInUser` は、サインインしているユーザーのキャッシュを逆シリアル化します (機密クライアント アプリケーションにはユーザーごとに 1 つのキャッシュが必要です)。

新しい更新トークンがキャッシュに格納されている間、アクセス トークンと ID トークンが `AuthenticationResult` 値で返されます。 このメソッドは、更新トークンを使用できるさまざまな統合シナリオにも使用できます。

### v1.0 トークンと v2.0 トークン

トークンには、v1.0 トークンと v2.0 トークンの 2 つのバージョンがあります。 v1.0 エンドポイント (ADAL で使用) は v1.0 ID トークンを出力し、v2.0 エンドポイント (MSAL で使用) は v2.0 ID トークンを出力します。 ただし、両方のエンドポイントは、Web API が受け入れるトークンのバージョンのアクセス トークンを出力します。 Web API のアプリケーション マニフェストのプロパティを使用すると、開発者は受け入れられるトークンのバージョンを選択できます。 [アプリケーション マニフェスト](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/reference-app-manifest)のリファレンス ドキュメントの`accessTokenAcceptedVersion`を参照してください。

v1.0 および v2.0 アクセス トークンの詳細については、「[Microsoft Entraアクセス トークン](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/access-tokens)」を参照してください。

### 例外

#### 相互作用が必要な例外

MSAL.NET を使用すると、「[AcquireTokenSilent](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/acquire-token-silently)」の説明に従って`MsalUiRequiredException`をキャッチできます

```csharp
catch(MsalUiRequiredException exception)
{
 try {"try to authenticate interactively"}
}
```

詳細については、[MSAL.NET でのエラーと例外の処理に関するページを](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/)参照してください。

ADAL.NETの明示的な例外は少ありませんでした。 たとえば、ADAL でサイレント認証が失敗した場合、プロシージャは例外をキャッチし、 `user_interaction_required` エラー コードを探しました。

```csharp
catch(AdalException exception)
{
 if (exception.ErrorCode == "user_interaction_required")
 {
  try
  {“try to authenticate interactively”}}
 }
}
```

詳細については、ADAL を使用[してパブリック クライアント アプリケーションでトークンを取得するために推奨されるパターン](https://github.com/AzureAD/azure-activedirectory-library-for-dotnet/wiki/AcquireTokenSilentAsync-using-a-cached-token#recommended-pattern-to-acquire-a-token).NETを参照してください。

#### プロンプトの動作

MSAL.NET でのプロンプト動作は、ADAL でのプロンプト動作と同じです.NET:

| ADAL.NET | MSAL.NET | 説明 |
| --- | --- | --- |
| `PromptBehavior.Auto` | `NoPrompt` | Microsoft Entra IDは、最適な動作を選択します (ユーザーが 1 つのアカウントでのみサインインしている場合は自動的にサインインするか、複数のアカウントでサインインしている場合はアカウント セレクターを表示します)。 |
| `PromptBehavior.Always` | `ForceLogin` | サインイン ボックスをリセットし、ユーザーに資格情報の再入力を強制します。 |
| `PromptBehavior.RefreshSession` | `Consent` | ユーザーがすべてのアクセス許可に再度同意するように強制します。 |
| `PromptBehavior.Never` | `Never` | 使用しないでください。代わりに、 [パブリック クライアント アプリに推奨されるパターンを使用してください](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-desktop-acquire-token?tabs=dotnet)。 |
| `PromptBehavior.SelectAccount` | `SelectAccount` | アカウント セレクターを表示し、ユーザーにアカウントの選択を強制します。 |

#### 要求チャレンジ例外の処理

トークンを取得するときに、リソースがユーザーからの要求を増やす必要がある場合 (たとえば、2 要素認証) に対して、Microsoft Entra IDは例外をスローします。

MSAL.NET では、要求チャレンジの例外は次のように処理されます。

- `Claims`が`MsalServiceException`に表示されます。
- `AcquireTokenXXX` ビルダーに適用できる[WithClaims(String)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.abstractacquiretokenparameterbuilder-1.withclaims#microsoft-identity-client-abstractacquiretokenparameterbuilder-1-withclaims%28system-string%29)メソッドがあります。

詳細については、「 [MsalUiRequiredException の処理](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/msal-error-handling#msaluirequiredexception)」を参照してください。

ADAL.NET では、要求チャレンジの例外は次のように処理されました。

- `AdalClaimChallengeException` は例外です ( `AdalServiceException`から派生)。 `Claims` メンバーには、要求を含むいくつかの JSON フラグメントが含まれています。これは想定されています。
- この例外を受け取るパブリック クライアント アプリケーションは、要求パラメーターを持つ `AcquireTokenInteractive` オーバーライドを呼び出す必要があります。 `AcquireTokenInteractive`のこのオーバーライドは、必要がないため、キャッシュにヒットしようともしません。 その理由は、キャッシュ内のトークンに適切な要求がないためです (それ以外の場合、 `AdalClaimChallengeException` はスローされません)。 そのため、キャッシュを見る必要はありません。 `ClaimChallengeException`は OBO を実行している WebAPI で受信できますが、この Web API を呼び出すパブリック クライアント アプリケーションで`AcquireTokenInteractive`を呼び出す必要があります。

サンプルを含む詳細については、 [AdalClaimChallengeException の処理を](https://github.com/AzureAD/azure-activedirectory-library-for-dotnet/wiki/Exceptions-in-ADAL.NET#handling-adalclaimchallengeexception)参照してください。

### スコープ

ADAL では、`resourceId`文字列を持つリソースの概念が使用されますが、MSAL.NET ではスコープが使用されます。 Microsoft Entra IDで使用されるロジックは次のとおりです。

- v1.0 アクセス トークン (唯一可能) を持つ ADAL (v1.0) エンドポイントの場合は、 `aud=resource`。
- v2.0 トークンを受け入れるリソースのアクセス トークンを要求する MSAL (v2.0 エンドポイント) の場合は、 `aud=resource.AppId`。
- v1.0 アクセス トークンを受け入れるリソースのアクセス トークンを要求する MSAL (v2.0 エンドポイント) の場合、Microsoft Entra IDは要求されたスコープから目的の対象ユーザーを解析します。 これは、最後のスラッシュの前のすべてを取得し、それをリソース識別子として使用することによって行われます。 そのため、`https://database.windows.net``https://database.windows.net/`の対象ユーザーが予想される場合は、`https://database.windows.net//.default`のスコープを要求する必要があります (./default の前に二重スラッシュがあることに注意してください)。 以下に、例 1 と 2 を示します。

#### 例 1

v1.0 トークンを受け入れるアプリケーションのトークン (たとえば、`https://graph.microsoft.com`の Microsoft Graph API) を取得する場合は、目的のリソース識別子をそのリソースの目的の OAuth2 アクセス許可と連結して`scopes`を作成する必要があります。

たとえば、アプリ ID URI が `ResourceId` v1.0 Web API 経由でユーザーの名前にアクセスするには、次を使用します。

```csharp
var scopes = new [] { ResourceId+"/user_impersonation" };
```

Microsoft Graph API (`https://graph.microsoft.com/`) を使用して MSAL.NET Microsoft Entra IDで読み書きする場合は、次のコード スニペットのようにスコープの一覧を作成します。

```csharp
string ResourceId = "https://graph.microsoft.com/"; 
string[] scopes = { ResourceId + "Directory.Read", ResourceId + "Directory.Write" }
```

#### 例 2

resourceId が '/' で終わる場合は、スコープ値を書き込むときに、二重の '/' が必要です。 たとえば、Azure Resource Manager API (`https://management.core.windows.net/`) に対応するスコープを記述する場合は、次のスコープを要求します (2 つのスラッシュに注意してください)。

```csharp
var resource = "https://management.core.windows.net/"
var scopes = new[] {"https://management.core.windows.net//user_impersonation"};
var result = await app.AcquireTokenInteractive(scopes).ExecuteAsync();

// then call the API: https://management.azure.com/subscriptions?api-version=2016-09-01
```

これは、Resource Manager API が対象ユーザー要求 (`aud`) でスラッシュを受け取り、スコープから API 名を区切るスラッシュが存在するためです。

v1.0 アプリケーションのすべての静的スコープのトークンを取得する場合は、次のコード スニペットに示すようにスコープ リストを作成します。

```csharp
ResourceId = "someAppIDURI";
var scopes = new [] { ResourceId+"/.default" };
```

クライアント資格情報フローの場合、渡すスコープも `/.default`。 このスコープは、"管理者がアプリケーション登録で同意したすべてのアプリ レベルのアクセス許可" をMicrosoft Entra IDするように指示します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/get-tenant-profiles"} -->
## MSAL.NET を使用したテナント プロファイルの取得 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/get-tenant-profiles
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: MSAL.NET を使用したテナント プロファイルの取得

同じアカウントのトークンを取得し、異なるテナントでトークンを取得し、各テナントの ID トークンのテナントと要求を表示するコード サンプルを次に示します。

```csharp
using Microsoft.Identity.Client;
using System;
using System.Threading.Tasks;

class Program
{
    static async Task Main(string[] args)
    {
        IPublicClientApplication app = PublicClientApplicationBuilder.Create("4a1aa1d5-c567-49d0-ad0b-cd957a47f842")
            .WithDefaultRedirectUri()                                    
            .Build();

        // Authenticate in my home tenant (Authority is 'common')
        AuthenticationResult result = await app.AcquireTokenInteractive(new[] { "user.read" })
            .ExecuteAsync();

        // Get a new token for myself, but in another tenant.
        result = await app.AcquireTokenSilent(new[] { "user.read" }, result.Account)
            .WithAuthority(app.Authority.Replace("common", "msidentitysamplestesting.onmicrosoft.com"))
            .ExecuteAsync();

        // Display tenants, and claims
        foreach (var tenantProfile in result.Account.GetTenantProfiles())
        {
            Console.WriteLine($"Tenant= {tenantProfile.TenantId}");
            foreach(var claim in tenantProfile.ClaimsPrincipal.Claims)
            {
                Console.WriteLine($"  {claim.Type}={claim.Value}");
            }
        }
    }
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/install-nuget-custom-source"} -->
## カスタム NuGet パッケージ ソースからの MSAL.NET のインストール - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/install-nuget-custom-source
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: NuGet パッケージ フィード以外のソースから NuGet をインストールする方法。

MSAL の非公式バージョンに依存する必要がある場合があります。

- MSAL 開発者の手がバグの修正プログラムを作成し、それを検証していただきたい
- 自分で MSAL に変更を加え、MSAL をパッケージ化し、アプリで試してみたい

### ローカル ソースからパッケージをインストールする

最も簡単な方法は、NuGet パッケージ ソースとして [ローカル フォルダー](https://learn.microsoft.com/ja-jp/nuget/hosting-packages/local-feeds) を使用することです。 これにより、開発者は、パッケージをリモート サーバーにアップロードせずに、自分のコンピューターからコンテンツを読み取ります。

### 何をしないか

- `*.nupkg` ファイルを抽出せず、ダイナミック リンク ライブラリ (DLL) 自体の参照を取得しないでください。パッケージには多くの DLL があり、間違った DLL を使用している可能性があります。
- NuGet キャッシュ内の既存のパッケージに対して新しいパッケージのコピー、貼り付け、または名前変更を試みないでください。公式以外のバージョンから公式バージョンに戻り、キャッシュを正しくクリアするときに問題が発生します。

### 署名を確認する

パッケージが署名されていることを確認する必要があります。 MSAL は、Microsoftによって署名する必要があります。 NuGet にはこの情報が表示され、 [NuGet パッケージ エクスプローラー](https://github.com/NuGetPackageExplorer/NuGetPackageExplorer)などのツールを使用してパッケージをいつでも確認できます。 Microsoftは、公式以外のリリースとプレビュー リリースの場合でも、常に両方のパッケージと内部の DLL に署名します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/migrate-android-broker"} -->
## ブローカー Xamarin使用して Android アプリを MSAL.NET に移行する - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/migrate-android-broker
- Service: msal / msal-dotnet
- Article date: 2020-08-31
- Summary: Microsoft Authenticatorまたは Intune ポータル サイトを使用Xamarin Android アプリを ADAL.NET から MSAL.NET に移行する方法について説明します。

現在 Xamarin Android アプリで、.NET 用 Azure Active Directory Authentication Library (ADAL.NET) と [認証ブローカー](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-android-single-sign-on) を使用している場合は、[Microsoft Authentication Library for .NET](https://learn.microsoft.com/ja-jp/entra/msal)(MSAL.NET) に移行するタイミングです。

### Prerequisites

- Xamarin Android アプリは、MSAL.NET に移行する必要があるブローカー ([Microsoft Authenticator](https://play.google.com/store/apps/details?id=com.azure.authenticator)または [Intune ポータル サイト](https://play.google.com/store/apps/details?id=com.microsoft.windowsintune.companyportal)) と ADAL.NETに既に統合されています。

### 手順 1: ブローカーを有効にする

| 現在の ADAL コード: | MSAL の対応項目: |
| --- | --- |
| ADAL.NET では、ブローカーのサポートは認証コンテキストごとに有効になります。 <br>ブローカーを呼び出すには、`useBroker`コンストラクターでを `PlatformParameters` に設定する必要がありました。<br><br><br>```CSharp<br>public PlatformParameters(<br>        Activity callerActivity,<br>        bool useBroker)<br>```<br><br>Android 用のプラットフォーム固有のページ レンダラー コードで、 `useBroker` フラグを true に設定します。<br><br><br>```CSharp<br>page.BrokerParameters = new PlatformParameters(<br>        this,<br>        true,<br>        PromptBehavior.SelectAccount);<br>```<br><br>次に、取得トークン呼び出しにパラメーターを含めます。<br><br><br>```CSharp<br>AuthenticationResult result =<br>        await<br>            AuthContext.AcquireTokenAsync(<br>                Resource,<br>                ClientId,<br>                new Uri(RedirectURI),<br>                platformParameters)<br>                .ConfigureAwait(false);<br>``` | MSAL.NET では、ブローカーのサポートは PublicClientApplication ごとに有効になります。 <br>ブローカーを呼び出すには、 `WithBroker()` パラメーター (既定では true に設定されています) を使用します。<br><br><br>```CSharp<br>var app = PublicClientApplicationBuilder<br>                .Create(ClientId)<br>                .WithBroker()<br>                .WithRedirectUri(redirectUriOnAndroid)<br>                .Build();<br>```<br><br>次に、AcquireToken 呼び出しで次の手順を実行します。<br><br><br>```CSharp<br>result = await app.AcquireTokenInteractive(scopes)<br>             .WithParentActivityOrWindow(App.RootViewController)<br>             .ExecuteAsync();<br>``` |

### 手順 2: アクティビティを設定する

ADAL.NET では、「手順 1: ブローカーを有効にする」に示すように、PlatformParameters の一部としてアクティビティ (通常は MainActivity) を渡しました。

MSAL.NET はアクティビティも使用しますが、ブローカーなしでの通常の Android の使用では必要ありません。 ブローカーを使用するには、ブローカーからの応答を送受信するアクティビティを設定します。

| 現在の ADAL コード: | MSAL の対応項目: |
| --- | --- |
| アクティビティは、Android 固有のプラットフォームの PlatformParameters に渡されます。 <br><br>```CSharp<br>page.BrokerParameters = new PlatformParameters(<br>          this,<br>          true,<br>          PromptBehavior.SelectAccount);<br>``` | MSAL.NET で、Android のアクティビティを設定するには、次の 2 つの操作を行います。<br><br>1. `MainActivity.cs`で、`App.RootViewController`を`MainActivity`に設定して、ブローカーの呼び出しでアクティビティがあることを確認します。<br><br>    正しく設定されていない場合は、次のエラーが発生する可能性があります。 `"Activity_required_for_android_broker":"Activity is null, so MSAL.NET cannot invoke the Android broker. See https://aka.ms/Brokered-Authentication-for-Android"`<br>2. AcquireTokenInteractive 呼び出しで、 `.WithParentActivityOrWindow(App.RootViewController)` を使用し、使用するアクティビティへの参照を渡します。 この例では MainActivity を使用します。<br><br><br>**例:**<br><br>*App.cs* では:<br><br><br>```CSharp<br>   public static object RootViewController { get; set; }<br>```<br><br>*MainActivity.cs* 内:<br><br><br>```CSharp<br>   LoadApplication(new App());<br>   App.RootViewController = this;<br>```<br><br>AcquireToken 呼び出しで次の手順を実行します。<br><br><br>```CSharp<br>result = await app.AcquireTokenInteractive(scopes)<br>             .WithParentActivityOrWindow(App.RootViewController)<br>             .ExecuteAsync();<br>``` |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/migrate-confidential-client"} -->
## 機密クライアント アプリケーションを MSAL.NET に移行する - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/migrate-confidential-client
- Service: msal / msal-dotnet
- Article date: 2023-08-24
- Summary: 機密クライアント アプリケーションを Azure Active Directory Authentication Library for .NET から .NET のMicrosoft Authentication Libraryに移行する方法について説明します。

このハウツー ガイドでは、機密クライアント アプリケーションを Azure Active Directory Authentication Library for .NET (ADAL.NET) から .NET のMicrosoft Authentication Library (MSAL.NET) に移行します。 機密クライアント アプリケーションには、独自のサービスを呼び出す Web アプリ、Web API、デーモン アプリケーションが含まれます。 機密アプリの詳細については、「 [認証フローとアプリケーションシナリオ](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/authentication-flows-app-scenarios)」を参照してください。 アプリが ASP.NET Core に基づいている場合は、[Microsoft.Identity.Web](https://learn.microsoft.com/ja-jp/entra/msidweb/) を参照してください。

アプリの登録の場合:

- 新しいアプリ登録を作成する必要はありません。 (同じクライアント ID を保持します)。
- 事前認証 (管理者が同意した API アクセス許可) を変更する必要はありません。

### 移行の手順

1. アプリで ADAL.NET を使用するコードを見つけます。

    機密クライアント アプリで ADAL を使用するコードは、 `AuthenticationContext` をインスタンス化し、次のパラメーターを使用して `AcquireTokenByAuthorizationCode` または `AcquireTokenAsync` のオーバーライドのいずれかを呼び出します。

    - `resourceId`文字列。 この変数は、呼び出す Web API のアプリ ID URI です。
    - `IClientAssertionCertificate`または`ClientAssertion`のインスタンス。 このインスタンスは、アプリの ID を証明するためのクライアント資格情報をアプリに提供します。
2. ADAL.NET を使用しているアプリがあることを特定したら、MSAL.NET NuGet パッケージ [Microsoft.Identity.Client](https://www.nuget.org/packages/Microsoft.Identity.Client) をインストールし、プロジェクトのライブラリ参照を更新します。 詳細については、「 [NuGet パッケージのインストール」を](https://www.bing.com/search?q=install+nuget+package)参照してください。 トークン キャッシュ シリアライザーを使用するには、[Microsoftをインストールします。Identity.Web.TokenCache](https://www.nuget.org/packages/Microsoft.Identity.Web.TokenCache)。
3. 機密クライアントのシナリオに従ってコードを更新します。 一部の手順は一般的であり、すべての機密クライアント シナリオに適用されます。 その他の手順は、各シナリオに固有です。

    機密顧客シナリオ:

    - Web アプリ、Web API、デーモン コンソール アプリケーションでサポートされるデーモン [シナリオ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/migrate-confidential-client?tabs=daemon#migrate-daemon-apps)。
    - [ダウンストリーム Web API を呼び出す Web API](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/migrate-confidential-client?tabs=obo#migrate-a-web-api-that-calls-downstream-web-apis) は、ユーザーに代わってダウンストリーム Web API を呼び出す Web API をサポートします。
    - [Web API を呼び出す Web アプリ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/migrate-confidential-client?tabs=authcode#migrate-a-web-api-that-calls-downstream-web-apis) は、ユーザーをサインインさせてダウンストリーム Web API を呼び出す Web アプリでサポートされます。

証明書とキャッシュを処理するために、ADAL.NET のラッパーを提供している可能性があります。 このガイドでは、同じアプローチを使用して、ADAL.NET から MSAL.NET に移行するプロセスを示します。 ただし、このコードはデモンストレーションのみを目的としています。 これらのラッパーをコピー/貼り付けしたり、そのままコードに統合したりしないでください。

## [デーモン](#tab/daemon)
#### デーモン アプリを移行する

デーモン シナリオでは、OAuth2.0 [クライアント資格情報フロー](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-oauth2-client-creds-grant-flow)が使用されます。 サービス間呼び出しとも呼ばれます。 アプリは、ユーザーの代わりにではなく、それ自体に代わってトークンを取得します。

##### コードでデーモン シナリオが使用されているかどうかを確認する

アプリの ADAL コードには、次のパラメーターを指定した `AuthenticationContext.AcquireTokenAsync` の呼び出しが含まれている場合、デーモン シナリオが使用されます。

- 最初のパラメーターとしてのリソース (アプリ ID URI)
- `IClientAssertionCertificate`または 2 番目のパラメーターとして`ClientAssertion`

`AuthenticationContext.AcquireTokenAsync` には、 `UserAssertion`型のパラメーターがありません。 その場合、アプリは Web API であり、 [ダウンストリーム Web API を呼び出す Web API シナリオを](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/migrate-confidential-client?tabs=obo#migrate-a-web-api-that-calls-downstream-web-apis) 使用します。

##### デーモン シナリオのコードを更新する

コードを更新するための次の手順は、すべての機密クライアント シナリオに適用されます。

1. ソース コードに MSAL.NET 名前空間 (`using Microsoft.Identity.Client;`) を追加します。
2. `AuthenticationContext`をインスタンス化する代わりに、`ConfidentialClientApplicationBuilder.Create`を使用して`IConfidentialClientApplication`をインスタンス化します。
3. `resourceId`文字列の代わりに、MSAL.NET はスコープを使用します。 ADAL.NET を使用するアプリケーションは事前認証されているため、常に次のスコープを使用できます: `new string[] { $"{resourceId}/.default" }`。
4. `AuthenticationContext.AcquireTokenAsync`の呼び出しを`IConfidentialClientApplication.AcquireTokenXXX`の呼び出しに置き換えます。*XXX* はシナリオによって異なります。

この場合は、 `AuthenticationContext.AcquireTokenAsync` の呼び出しを `IConfidentialClientApplication.AcquireTokenClient`の呼び出しに置き換えます。

デーモン シナリオの ADAL.NET と MSAL.NET コードの比較を次に示します。

ADAL

MSAL

```csharp
using Microsoft.IdentityModel.Clients.ActiveDirectory;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;

public partial class AuthWrapper
{
const string ClientId = "Guid (AppID)";
const string authority 
= "https://login.microsoftonline.com/{tenant}";
// App ID URI of web API to call
const string resourceId = "https://target-api.domain.com";
X509Certificate2 certificate = LoadCertificate();

public async Task<AuthenticationResult> GetAuthenticationResult()
{

var authContext = new AuthenticationContext(authority);
var clientAssertionCert = new ClientAssertionCertificate(
ClientId,
certificate);

var authResult = await authContext.AcquireTokenAsync(
resourceId,
clientAssertionCert,
);

return authResult;
}
}
```

```csharp
using Microsoft.Identity.Client;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;

public partial class AuthWrapper
{
const string ClientId = "Guid (Application ID)";
const string authority 
= "https://login.microsoftonline.com/{tenant}";
// App ID URI of web API to call
const string resourceId = "https://target-api.domain.com";
X509Certificate2 certificate = LoadCertificate();

IConfidentialClientApplication app;

public async Task<AuthenticationResult> GetAuthenticationResult()
{

var app = ConfidentialClientApplicationBuilder.Create(ClientId)
.WithCertificate(certificate)
.WithAuthority(authority)
.Build();

// Setup token caching https://learn.microsoft.com/azure/active-directory/develop/msal-net-token-cache-serialization?tabs=aspnet
// For example, for an in-memory cache with 1GB limit, use  
app.AddInMemoryTokenCache(services =>
{
// Configure the memory cache options
services.Configure<MemoryCacheOptions>(options =>
{
options.SizeLimit = 1024 * 1024 * 1024; // in bytes (1 GB of memory)
});
}

var authResult = await app.AcquireTokenForClient(
new [] { $"{resourceId}/.default" })
// .WithTenantId(specificTenant)
// See https://aka.ms/msal.net/withTenantId
.ExecuteAsync()
.ConfigureAwait(false);

return authResult;
}
}
```

##### トークン キャッシュの利点

トークン キャッシュを設定しない場合、トークン発行者によって調整が行われるので、エラーが発生します。 また、キャッシュからトークンを取得する時間 (10 ~ 20 ミリ秒) は、ESTS (500 ~ 30000 ミリ秒) よりもかなり少なくなります。

分散トークン キャッシュを実装する場合は、 [Web アプリまたは Web API (機密クライアント アプリケーション) のトークン キャッシュ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization?tabs=aspnet)に関するページを参照してください。

[デーモン シナリオの詳細](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-daemon-overview)と、新しいアプリケーションでそれを MSAL.NET または Microsoft.Identity.Web を使用して実装する方法について説明します。

## [ダウンストリーム Web API を呼び出す Web API](#tab/obo)
#### ダウンストリーム Web API を呼び出す Web API を移行する

ダウンストリーム Web API を呼び出す Web API は、OAuth2.0 [On-Behalf-of (OBO)](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-oauth2-on-behalf-of-flow) フローを使用します。 Web API は、HTTP **Authorize** ヘッダーから取得したアクセス トークンを使用し、このトークンを検証します。 その後、このトークンは、ダウンストリーム Web API を呼び出すためにトークンに対して交換されます。 このトークンは、ADAL.NET と MSAL.NET の両方で`UserAssertion` インスタンスとして使用されます。

##### コードで OBO が使用されているかどうかを確認する

アプリの ADAL コードには、次のパラメーターを持つ `AuthenticationContext.AcquireTokenAsync` の呼び出しが含まれている場合、OBO が使用されます。

- 最初のパラメーターとしてのリソース (アプリ ID URI)
- `IClientAssertionCertificate`または 2 番目のパラメーターとして`ClientAssertion`
- `UserAssertion` 型のパラメーター

##### OBO を使用してコードを更新する

コードを更新するための次の手順は、すべての機密クライアント シナリオに適用されます。

1. ソース コードに MSAL.NET 名前空間 (`using Microsoft.Identity.Client;`) を追加します。
2. `AuthenticationContext`をインスタンス化する代わりに、`ConfidentialClientApplicationBuilder.Create`を使用して`IConfidentialClientApplication`をインスタンス化します。
3. `resourceId`文字列の代わりに、MSAL.NET はスコープを使用します。 ADAL.NET を使用するアプリケーションは事前認証されているため、常に次のスコープを使用できます: `new string[] { $"{resourceId}/.default" }`。
4. `AuthenticationContext.AcquireTokenAsync`の呼び出しを`IConfidentialClientApplication.AcquireTokenXXX`の呼び出しに置き換えます。*XXX* はシナリオによって異なります。

この場合、 `AuthenticationContext.AcquireTokenAsync` の呼び出しを `IConfidentialClientApplication.AcquireTokenOnBehalfOf`の呼び出しに置き換えます。

ADAL.NET と MSAL.NET のサンプル OBO コードの比較を次に示します。

ADAL

MSAL

```csharp
using Microsoft.IdentityModel.Clients.ActiveDirectory;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;

public partial class AuthWrapper
{
const string ClientId = "Guid (AppID)";
const string authority 
= "https://login.microsoftonline.com/common";
X509Certificate2 certificate = LoadCertificate();

public async Task<AuthenticationResult> GetAuthenticationResult(
string resourceId, 
string tokenUsedToCallTheWebApi)
{

var authContext = new AuthenticationContext(authority);
var clientAssertionCert = new ClientAssertionCertificate(
ClientId,
certificate);

var userAssertion = new UserAssertion(tokenUsedToCallTheWebApi);

var authResult = await authContext.AcquireTokenAsync(
resourceId,
clientAssertionCert,
userAssertion,
);

return authResult;
}
}
```

```csharp
using Microsoft.Identity.Client;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;

public partial class AuthWrapper
{
const string ClientId = "Guid (Application ID)";
const string authority 
= "https://login.microsoftonline.com/common";
X509Certificate2 certificate = LoadCertificate();

IConfidentialClientApplication app;

public async Task<AuthenticationResult> GetAuthenticationResult(
string resourceId,
string tokenUsedToCallTheWebApi)
{

var app = ConfidentialClientApplicationBuilder.Create(ClientId)
.WithCertificate(certificate)
.WithAuthority(authority)
.Build();

// Setup token caching https://learn.microsoft.com/azure/active-directory/develop/msal-net-token-cache-serialization?tabs=aspnet
// For example, for an in-memory cache with 1GB limit. For OBO, it is recommended to use a distributed cache like Redis.
app.AddInMemoryTokenCache(services =>
{
// Configure the memory cache options
services.Configure<MemoryCacheOptions>(options =>
{
options.SizeLimit = 1024 * 1024 * 1024; // in bytes (1 GB of memory)
});
}

var userAssertion = new UserAssertion(tokenUsedToCallTheWebApi);

var authResult = await app.AcquireTokenOnBehalfOf(
new string[] { $"{resourceId}/.default" },
userAssertion)
// .WithTenantId(specificTenant) 
// See https://aka.ms/msal.net/withTenantId
.ExecuteAsync()
.ConfigureAwait(false);

return authResult;
}
}
```

##### トークン キャッシュの利点

OBO でのトークン キャッシュの場合は、分散トークン キャッシュを使用します。 詳細については、 [Web アプリまたは Web API (機密クライアント アプリ) のトークン キャッシュ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization?tabs=aspnet)に関するページを参照してください。

```CSharp
app.UseInMemoryTokenCaches(); // or a distributed token cache.
```

[ダウンストリーム Web API を呼び出す Web API について詳しくは、こちらをご覧ください](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-web-api-call-api-overview)。また、新しいアプリで MSAL.NET または Microsoft.Identity.Web を使用してそれらを実装する方法についても説明します。

## [Web アプリ呼び出し Web API](#tab/authcode)
#### Web API を呼び出す Web アプリを移行する

アプリで ASP.NET Coreを使用している場合は、Microsoftに更新することを強くお勧めします。Identity.Web は、すべてを自動的に処理するためです。 手短な紹介については、[Microsoft.Identity.Web の一般提供開始のお知らせ](https://github.com/AzureAD/microsoft-identity-web/wiki/1.0.0)をご覧ください。 Web アプリでの使用方法の詳細については、[Web アプリで Microsoft.Identity.Web を使用する理由](https://aka.ms/ms-id-web/webapp)を参照してください。

ユーザーをサインインさせ、ユーザーに代わって Web API を呼び出す Web アプリは、OAuth2.0 [承認コード フロー](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-oauth2-auth-code-flow)を使用します。 通常：

1. アプリは、Microsoft ID プラットフォーム承認エンドポイントに移動して、承認コード フローの第 1 段階を実行してユーザーをサインインさせます。 ユーザーはサインインし、必要に応じて多要素認証を実行します。 この操作の結果として、アプリは承認コードを受け取ります。 この段階では、認証ライブラリは使用されません。
2. アプリは、承認コード フローの第 2 区間を実行します。 認証コードを使用して、アクセス トークン、ID トークン、および更新トークンを取得します。 アプリケーションでは、`redirectUri`値 (Microsoft ID プラットフォーム エンドポイントがセキュリティ トークンを提供する URI) を指定する必要があります。 アプリはその URI を受け取った後、通常、ADAL または MSAL の `AcquireTokenByAuthorizationCode` を呼び出してコードを引き換え、トークン キャッシュに格納されるトークンを取得します。
3. アプリは ADAL または MSAL を使用して `AcquireTokenSilent` を呼び出し、Web アプリ コントローラーから必要な Web API を呼び出すためのトークンを取得します。

##### コードで認証コード フローが使用されているかどうかを確認する

アプリの ADAL コードには、 `AuthenticationContext.AcquireTokenByAuthorizationCodeAsync`の呼び出しが含まれている場合、認証コード フローが使用されます。

##### 承認コード フローを使用してコードを更新する

コードを更新するための次の手順は、すべての機密クライアント シナリオに適用されます。

1. ソース コードに MSAL.NET 名前空間 (`using Microsoft.Identity.Client;`) を追加します。
2. `AuthenticationContext`をインスタンス化する代わりに、`ConfidentialClientApplicationBuilder.Create`を使用して`IConfidentialClientApplication`をインスタンス化します。
3. `resourceId`文字列の代わりに、MSAL.NET はスコープを使用します。 ADAL.NET を使用するアプリケーションは事前認証されているため、常に次のスコープを使用できます: `new string[] { $"{resourceId}/.default" }`。
4. `AuthenticationContext.AcquireTokenAsync`の呼び出しを`IConfidentialClientApplication.AcquireTokenXXX`の呼び出しに置き換えます。*XXX* はシナリオによって異なります。

この場合は、 `AuthenticationContext.AcquireTokenAsync` の呼び出しを `IConfidentialClientApplication.AcquireTokenByAuthorizationCode`の呼び出しに置き換えます。

ADAL.NET と MSAL.NET のサンプル承認コード フローの比較を次に示します。

ADAL

MSAL

```csharp
using Microsoft.IdentityModel.Clients.ActiveDirectory;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;

public partial class AuthWrapper
{
const string ClientId = "Guid (AppID)";
const string authority 
= "https://login.microsoftonline.com/common";
private Uri redirectUri = new Uri("host/login_oidc");
X509Certificate2 certificate = LoadCertificate();

public async Task<AuthenticationResult> GetAuthenticationResult(
string resourceId,
string authorizationCode)
{

var ac = new AuthenticationContext(authority);
var clientAssertionCert = new ClientAssertionCertificate(
ClientId,
certificate);

var authResult = await ac.AcquireTokenByAuthorizationCodeAsync(
authorizationCode,
redirectUri,
clientAssertionCert,
resourceId,
);
return authResult;
}
}
```

```csharp
using Microsoft.Identity.Client;
using Microsoft.Identity.Web;
using System;
using System.Security.Claims;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;

public partial class AuthWrapper
{
const string ClientId = "Guid (Application ID)";
const string authority
= "https://login.microsoftonline.com/{tenant}";
private Uri redirectUri = new Uri("host/login_oidc");
X509Certificate2 certificate = LoadCertificate();

public IConfidentialClientApplication CreateApplication()
{
IConfidentialClientApplication app;

app = ConfidentialClientApplicationBuilder.Create(ClientId)
.WithCertificate(certificate)
.WithAuthority(authority)
.WithRedirectUri(redirectUri.ToString())
.WithLegacyCacheCompatibility(false)
.Build();

// Add a token cache. For details about other serialization
// see https://aka.ms/msal-net-cca-token-cache-serialization
app.AddInMemoryTokenCache();

return app;
}

// Called from 'code received event'.
public async Task<AuthenticationResult> GetAuthenticationResult(
string resourceId,
string authorizationCode)
{
IConfidentialClientApplication app = CreateApplication();

var authResult = await app.AcquireTokenByAuthorizationCode(
new[] { $"{resourceId}/.default" },
authorizationCode)
.ExecuteAsync()
.ConfigureAwait(false);

return authResult;
}
}
```

`AcquireTokenByAuthorizationCode`を呼び出すと、承認コードの受信時にトークンがトークン キャッシュに追加されます。 他のリソースまたはテナントの追加トークンを取得するには、コントローラーで `AcquireTokenSilent` を使用します。

```csharp
public partial class AuthWrapper
{
 // Called from controllers
 public async Task<AuthenticationResult> GetAuthenticationResult(
      string resourceId2,
      string authority)
 {
  IConfidentialClientApplication app = CreateApplication();
  AuthenticationResult authResult;

  var scopes = new[] { $"{resourceId2}/.default" };
  var account = await app.GetAccountAsync(ClaimsPrincipal.Current.GetMsalAccountId());

  try
  {
   // try to get an already cached token
   authResult = await app.AcquireTokenSilent(
               scopes,
               account)
                // .WithTenantId(specificTenantId) 
                // See https://aka.ms/msal.net/withTenantId
                .ExecuteAsync().ConfigureAwait(false);
  }
  catch (MsalUiRequiredException)
  {
   // The controller will need to challenge the user
   // including asking for claims={ex.Claims}
   throw;
  }
  return authResult;
 }
}
```

##### トークン キャッシュの利点

Web アプリでは `AcquireTokenByAuthorizationCode`を使用するため、トークン キャッシュには分散トークン キャッシュを使用する必要があります。 詳細については、 [Web アプリまたは Web API のトークン キャッシュに関するページを](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization?tabs=aspnet)参照してください。

```CSharp
app.UseInMemoryTokenCaches(); // or a distributed token cache.
```

##### MsalUiRequiredException の処理

コントローラーがさまざまなスコープ/リソースに対してサイレント モードでトークンを取得しようとすると、ユーザーが再サインインする必要がある場合、またはリソースへのアクセスに (条件付きアクセス ポリシーが原因で) より多くの要求が必要な場合、MSAL.NET は期待どおりに`MsalUiRequiredException`をスローする可能性があります。 軽減策の詳細については、[MSAL.NET でエラーと例外を処理](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/msal-error-handling)する方法を参照してください。

[Web API を呼び出す Web アプリについてさらに詳しく学び](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-web-app-call-api-overview)、新しいアプリケーションでそれらが MSAL.NET または Microsoft.Identity.Web を使用してどのように実装されるかを確認してください。

---

### MSAL の利点

アプリの MSAL.NET の主な利点は次のとおりです。

- **回復力**。 MSAL.NET は、次の方法でアプリの回復性を高めます。

    - Microsoft Entra ID キャッシュ資格情報サービス (CCS) のメリット CCS は、Microsoft Entra バックアップとして動作します。
    - 呼び出した API が [継続的なアクセス評価](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/app-resilience-continuous-access-evaluation)を通じて有効期間の長いトークンを有効にする場合、トークンのプロアクティブな更新。
- **セキュリティ**。 呼び出す Web API で必要な場合は、所有証明 (PoP) トークンを取得できます。 詳細については、[MSAL.NET の所有証明トークンを](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/proof-of-possession-tokens)参照してください。
- **パフォーマンスとスケーラビリティ**。 キャッシュを ADAL.NET と共有する必要がない場合は、機密クライアント アプリケーション (`.WithLegacyCacheCompatibility(false)`) を作成するときにレガシ キャッシュの互換性を無効にして、パフォーマンスを大幅に向上させます。

    ```csharp
    app = ConfidentialClientApplicationBuilder.Create(ClientId)
            .WithCertificate(certificate)
            .WithAuthority(authority)
            .WithLegacyCacheCompatibility(false)
            .Build();
    ```

### Troubleshooting

#### MsalServiceException

次のトラブルシューティング情報では、2 つの前提条件があります。

- ADAL.NET コードが動作していました。
- 同じクライアント ID を保持して MSAL に移行しました。

次のいずれかのメッセージで例外が発生した場合:

>
> `AADSTS700027: Client assertion contains an invalid signature. [Reason - The key was not found.]`

>
> `AADSTS90002: Tenant 'aaaabbbb-0000-cccc-1111-dddd2222eeee' not found. This may happen if there are no active``subscriptions for the tenant. Check to make sure you have the correct tenant ID. Check with your subscription``administrator.`

次の手順を使用して例外のトラブルシューティングを行います。

1. 最新バージョンの [MSAL.NET](https://www.nuget.org/packages/Microsoft.Identity.Client/) を使用していることを確認します。
2. 機密クライアント アプリを構築するときに設定した機関ホストと、ADAL で使用した機関ホストが類似していることを確認します。 特に、それは同じ[クラウド](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-national-cloud)（Azure Government、21Vianet が運営する Microsoft Azure、または Azure Germany）ですか。

#### MsalClientException

マルチテナント アプリでは、Web API を呼び出すときのユーザーのテナントなど、特定のテナントを対象とするアプリを構築する際に共通の権限を指定します。 MSAL.NET 4.37.0 以降、アプリの作成時に`.WithAzureRegion`を指定すると、トークン要求中に`.WithAuthority`を使用して機関を指定できなくなります。 その場合、以前のバージョンの MSAL.NET から更新すると、次のエラーが発生します。

`MsalClientException - "You configured WithAuthority at the request level, and also WithAzureRegion. This is not supported when the environment changes from application to request. Use WithTenantId at the request level instead."`

この問題を修復するには、AcquireTokenXXX 式の `.WithAuthority` を `.WithTenantId`に置き換えます。 GUID またはドメイン名を使用してテナントを指定します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/migrate-ios-broker"} -->
## ブローカーを使用してXamarinアプリを MSAL.NET に移行する - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/migrate-ios-broker
- Service: msal / msal-dotnet
- Article date: 2019-09-08
- Summary: Microsoft Authenticatorを使用Xamarin iOS アプリを ADAL.NET から MSAL.NET に移行する方法について説明します。

Warning

Azure Active Directory認証ライブラリ (ADAL) **は非推奨になりました**。 ADAL を使用する既存のアプリは引き続き機能しますが、Microsoftは ADAL のセキュリティ修正プログラムをリリースしなくなります。 アプリのセキュリティを危険にさらさないようにするには、[Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/msal/) を使用します。

.NET用のAzure Active Directory認証ライブラリ (ADAL.NET) と iOS ブローカーを使用している場合は、バージョン 4.3 以降の iOS 上のブローカーをサポートする .NET のMicrosoft Authentication Library (MSAL) に移行する必要があります。 この記事は、.NET iOS アプリケーションを ADAL から MSAL に移行する際に役立ちます。

### Prerequisites

この記事では、iOS ブローカーと統合された MAUI または Xamarin iOS アプリがあることを前提としています。 既存の ADAL ベースのアプリケーションがない場合は、MSAL.NET ライブラリの組み込みブローカー実装を直接使用できます。 新しいアプリケーションを使用して MSAL.NET で iOS ブローカーを呼び出す方法については、「[MAUI での MSAL.NET の使用](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/mobile-applications)」を参照してください。

### 経歴

#### 認証ブローカーとは

認証ブローカーは、iOS および Android のMicrosoft Authenticatorや Android 上の Intune [ポータル サイト](https://support.microsoft.com/en-us/account-billing/download-microsoft-authenticator-351498fc-850a-45da-b7b6-27e523b8702a) アプリなど、Android および iOS 上のMicrosoftによって提供されるアプリケーションです。

認証ブローカーでは、次のシナリオが有効になります。

- シングル サインオン (SSO)。
- 一部の [条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)で必要なデバイス識別。 詳細については、「 [デバイス管理](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions#device-platforms)」を参照してください。
- アプリケーション識別の検証。これは、一部のエンタープライズ シナリオでも必要です。 詳細については、 [Intune モバイル アプリケーション管理 (MAM)](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-management) に関するページを参照してください。

### ADAL から MSAL への移行

#### 手順 1: ブローカーを有効にする

| 現在の ADAL コード: | MSAL の対応項目: |
| --- | --- |
| ADAL.NET では、ブローカーのサポートは認証コンテキストごとに有効になりました。 既定では無効になっています。 ブローカーを呼び出すには、'PlatformParameters' コンストラクターで 'useBroker' フラグを 'true' に設定する必要がありました。 <br><br>```csharp<br>public PlatformParameters(<br>        UIViewController callerViewController,<br>        bool useBroker)<br>```<br><br>プラットフォーム固有のコードで、iOS 用のページ レンダラー内で、 `useBroker` フラグを true に設定します。<br><br><br>```csharp<br>page.BrokerParameters = new PlatformParameters(<br>          this,<br>          true,<br>          PromptBehavior.SelectAccount);<br>```<br><br>次に、トークン取得呼び出しにパラメーターを含めます。<br><br><br>```csharp<br> AuthenticationResult result =<br>                    await<br>                        AuthContext.AcquireTokenAsync(<br>                              Resource,<br>                              ClientId,<br>                              new Uri(RedirectURI),<br>                              platformParameters)<br>                              .ConfigureAwait(false);<br>``` | MSAL.NET では、ブローカーのサポートは各 [`PublicClientApplication`](xref:Microsoft.Identity.Client.PublicClientApplication) インスタンスごとに個別に有効になります。 既定では無効になっています。 有効にするには、ブローカーを呼び出すために [`WithBroker()`](xref:Microsoft.Identity.Client.PublicClientApplicationBuilder.WithBroker(System.Boolean)) を使用します (既定では true に設定されています):<br><br>```csharp<br>var app = PublicClientApplicationBuilder<br>                .Create(ClientId)<br>                .WithBroker()<br>                .WithReplyUri(redirectUriOnIos)<br>                .Build();<br>```<br><br>トークン取得呼び出しで次の手順を実行します。<br><br><br>```csharp<br>result = await app.AcquireTokenInteractive(scopes)<br>             .WithParentActivityOrWindow(App.RootViewController)<br>             .ExecuteAsync();<br>``` |

#### 手順 2: UIViewController() を設定する

ADAL.NET では、`UIViewController`の一部として`PlatformParameters`を渡しました。 MSAL.NET では、開発者に柔軟性を与えるためにオブジェクト ウィンドウが使用されますが、通常の iOS シナリオでは必要ありません。 ブローカーを使用するには、ブローカーからの応答を送受信するためにオブジェクト ウィンドウを設定します。

| 現在の ADAL コード: | MSAL の対応項目: |
| --- | --- |
| 'UIViewController' が 'PlatformParameters' に渡されます。 <br><br>```csharp<br>page.BrokerParameters = new PlatformParameters(<br>          this,<br>          true,<br>          PromptBehavior.SelectAccount);<br>``` | MSAL.NET では、iOS のオブジェクト ウィンドウを設定するには、次の 2 つの操作を行う必要があります。<br>1. `AppDelegate.cs`で、`App.RootViewController`を新しい`UIViewController()`に設定します。 この割り当てにより、ブローカーへの呼び出しに `UIViewController` が含まれるようになります。 正しく設定されていない場合は、次のエラーが発生する可能性があります。<br><br>    `"uiviewcontroller_required_for_ios_broker":"UIViewController is null, so MSAL.NET cannot invoke the iOS broker. See https://aka.ms/msal-net-ios-broker"`<br>2. `AcquireTokenInteractive`呼び出しで、[`.WithParentActivityOrWindow(App.RootViewController)`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokeninteractiveparameterbuilder.withparentactivityorwindow#microsoft-identity-client-acquiretokeninteractiveparameterbuilder-withparentactivityorwindow%28system-object%29)を使用し、使用するオブジェクト ウィンドウへの参照を渡します。<br><br><br>**例:**<br><br>`App.cs`の場合:<br><br><br>```csharp<br>   public static object RootViewController { get; set; }<br>```<br><br>`AppDelegate.cs`の場合:<br><br><br>```csharp<br>   LoadApplication(new App());<br>   App.RootViewController = new UIViewController();<br>```<br><br>トークン取得呼び出しで次の手順を実行します。<br><br><br>```csharp<br>result = await app.AcquireTokenInteractive(scopes)<br>             .WithParentActivityOrWindow(App.RootViewController)<br>             .ExecuteAsync();<br>``` |

#### 手順 3: コールバックを処理するように AppDelegate を更新する

ADAL と MSAL はどちらもブローカーを呼び出し、ブローカーは `OpenUrl` クラスの `AppDelegate` メソッドを使用してアプリケーションにコールバックします。 詳細については、「 [AppDelegate を更新してコールバックを処理する」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-net-use-brokers-with-xamarin-apps#step-3-update-appdelegate-to-handle-the-callback)参照してください。

ここでは、ADAL.NET と MSAL.NET の間に変更はありません。

#### 手順 4: URL スキームを登録する

ADAL.NETと MSAL.NET URL を使用してブローカーを呼び出し、ブローカーの応答をアプリに返します。 アプリの `Info.plist` ファイルに URL スキームを登録します。

| 現在の ADAL コード: | MSAL の対応項目: |
| --- | --- |
| URL スキームはアプリに固有です。 | 'CFBundleURLSchemes' の名前には、プレフィックスとして 'msauth.' を含め、その後に 'CFBundleURLName' が続く必要があります。 <br>例えば： `$"msauth.(BundleId")`<br><br><br>```csharp<br><key>CFBundleURLTypes</key><br><array><br>  <dict><br>    <key>CFBundleTypeRole</key><br>    <string>Editor</string><br>    <key>CFBundleURLName</key><br>    <string>com.yourcompany.xforms</string><br>    <key>CFBundleURLSchemes</key><br>    <array><br>      <string>msauth.com.yourcompany.xforms</string><br>    </array><br>  </dict><br></array><br>```<br><br>Note<br><br>この URL スキームは、ブローカーから応答を受け取ったときにアプリを一意に識別するために使用されるリダイレクト URI の一部になります。 |

#### 手順 5: ブローカー識別子を LSApplicationQueriesSchemes セクションに追加する

ADAL.NETと MSAL.NET の両方で`-canOpenURL:`を使用して、ブローカーがデバイスにインストールされているかどうかを確認します。 iOS ブローカーの正しい識別子を、`LSApplicationQueriesSchemes` ファイルの `info.plist` セクションに追加します。

| 現在の ADAL コード: | MSAL 対応項目: |
| --- | --- |
| 'msauth' を使用します <br><br>```csharp<br><key>LSApplicationQueriesSchemes</key><br><array><br>     <string>msauth</string><br></array><br>``` | 'msauthv2' を使用します <br><br>```csharp<br><key>LSApplicationQueriesSchemes</key><br><array><br>     <string>msauthv2</string><br>     <string>msauthv3</string><br></array><br>``` |

#### 手順 6: Azure ポータルにリダイレクト URI を登録する

ADAL.NETと MSAL.NET の両方で、ブローカーを対象とするリダイレクト URI に追加の要件が追加されます。 AzureまたはMicrosoft Entra ポータルで、アプリケーションにリダイレクト URI を登録します。

| 現在の ADAL コード: | MSAL の対応項目: |
| --- | --- |
| `"<app-scheme>://<your.bundle.id>"`<br><br>例：<br><br><br>```http<br>mytestiosapp://com.mycompany.myapp`<br>``` | `$"msauth.{BundleId}://auth"`<br><br>例：<br><br><br>```csharp<br>public static string redirectUriOnIos = "msauth.com.yourcompany.XForms://auth";<br>``` |

Azure ポータルでリダイレクト URI を登録する方法の詳細については、「[アプリの登録にリダイレクト URI を追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-net-use-brokers-with-xamarin-apps#step-7-add-a-redirect-uri-to-your-app-registrationn)。

#### 手順 7: Entitlements.plist を設定する

`Entitlements.plist` ファイルでキーチェーン アクセスを有効にします。

```xml
<key>keychain-access-groups</key>
<array>
  <string>$(AppIdentifierPrefix)com.microsoft.adalcache</string>
</array>
```

キーチェーン アクセスの有効化の詳細については、「キーチェーン アクセスを [有効にする」](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-net-xamarin-ios-considerations#enable-keychain-access)を参照してください。

### MSAL を使用したログ記録の重要性

多くの機能の中で、Microsoft Authentication Library (MSAL) には堅牢な組み込みの[ログ機能があります](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/msal-logging)。 アプリケーションでログ記録を有効にすると、認証の問題を直接把握でき、独自のアプリケーションで簡単に診断でき、MSAL チームが潜在的な問題に迅速に対処できます。 運用環境のシナリオでデプロイする場合は、アプリケーションのログ記録を有効にすることを強くお勧めします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/migrate-public-client"} -->
## パブリック クライアント アプリケーションを MSAL.NET に移行する - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/migrate-public-client
- Service: msal / msal-dotnet
- Article date: 2024-05-20
- Summary: パブリック クライアント アプリケーションを Azure Active Directory Authentication Library for .NET から Microsoft Authentication Library for .NET に移行する方法について説明します。

Warning

Azure Active Directory認証ライブラリ (ADAL) **は非推奨になりました**。 ADAL を使用する既存のアプリは引き続き機能しますが、Microsoftは ADAL のセキュリティ修正プログラムをリリースしなくなります。 アプリのセキュリティを危険にさらさないようにするには、[Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/msal/) を使用します。

この記事では、パブリック クライアント アプリケーションを Azure Active Directory Authentication Library for .NET (ADAL.NET) から .NET (MSAL.NET) のMicrosoft Authentication Libraryに移行する方法について説明します。 パブリック クライアント アプリケーションは、ネイティブ (Win32) や、Microsoft Entra ID ユーザーの代わりに別のサービスまたは API を呼び出すWPF プロジェクトを含むデスクトップおよびモバイル アプリケーションです。 パブリック クライアント アプリケーションの詳細については、「 [認証フローとアプリケーションのシナリオ](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios)」を参照してください。

### 移行の手順

1. アプリで ADAL.NET を使用してコードを検索します。

    パブリック クライアント アプリケーションで ADAL を使用するコードは、 `AuthenticationContext` をインスタンス化し、次のパラメーターを使用して `AcquireTokenAsync` のオーバーライドを呼び出します。

    - `resourceId`文字列。 この変数は、呼び出す Web API のアプリ ID URI です。
    - アプリケーションの識別子 (アプリ ID とも呼ばれます) である `clientId` 。
2. ADAL を使用しているアプリケーションがあることを確認したら.NET MSAL.NET NuGet パッケージ ([`Microsoft.Identity.Client`](https://www.nuget.org/packages/Microsoft.Identity.Client)) をインストールし、プロジェクト ライブラリ参照を更新します。 詳細については、「 [NuGet の概要」を](https://learn.microsoft.com/ja-jp/nuget/what-is-nuget)参照してください。
3. パブリック クライアント アプリケーションのシナリオに従ってコードを更新します。 一部の手順は一般的であり、すべてのパブリック クライアント シナリオに適用されます。 その他の手順は、各シナリオに固有です。

    パブリック クライアントのシナリオは次のとおりです。

    - [Web アカウント マネージャー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam)。認証ブローカー コンポーネントを使用して、Windows アプリケーションに推奨される認証方法です。
    - [対話型認証](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/acquiring-tokens-interactively)。サインイン プロセスを完了するための Web ベースのインターフェイスがユーザーに表示されます。
    - [統合Windows認証 (IWA)](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/integrated-windows-authentication)。ユーザーは、Windows ドメインにサインインしたのと同じ ID を使用してサインインします (ドメイン参加済みまたはMicrosoft Entra ID参加済みマシンの場合)。
    - [ユーザー名/パスワード。ユーザー名/パスワード](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/username-password-authentication)の資格情報を指定してサインインが行われます。 **Microsoftでは、ユーザー名とパスワードのフローは推奨されません**。これはセキュリティで保護されていないパターンであり、アプリケーションがユーザーに直接パスワードを要求するためです。
    - [デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/device-code-flow)。UX 機能が制限されたデバイスでは、代替デバイスで認証フローを完了するためのデバイス コードがコンシューマーに表示されます。

## [インタラクティブ](#tab/interactive)
対話型のシナリオでは、パブリック クライアント アプリケーションでブラウザーでホストされているログイン ユーザー インターフェイスが表示され、ユーザーは対話形式でサインインする必要があります。

##### コードで対話型シナリオが使用されているかどうかを確認する

対話型認証を使用するパブリック クライアント アプリケーションのアプリの ADAL コードは、 `AuthenticationContext` をインスタンス化し、次のパラメーターを使用して `AcquireTokenAsync`の呼び出しを含みます。

- アプリケーションの登録を表す GUID である `clientId` 。
- トークンを要求するリソースを示す `resourceUrl` 。
- 返信 URL である URI。
- `PlatformParameters` オブジェクト。

##### 対話型シナリオのコードを更新する

コードを更新するための次の手順は、すべての機密クライアント シナリオに適用されます。

1. ソース コードに MSAL.NET 名前空間 (`using Microsoft.Identity.Client;`) を追加します。
2. `AuthenticationContext`をインスタンス化する代わりに、`PublicClientApplicationBuilder.Create`を使用して`IPublicClientApplication`をインスタンス化します。
3. `resourceId`文字列の代わりに、MSAL.NET はスコープを使用します。 ADAL.NET を使用するアプリケーションは事前認証されているため、常に次のスコープを使用できます: `new string[] { $"{resourceId}/.default" }`。
4. `AuthenticationContext.AcquireTokenAsync`の呼び出しを`IPublicClientApplication.AcquireTokenXXX`の呼び出しに置き換えます。*XXX* はシナリオによって異なります。

この場合、 `AuthenticationContext.AcquireTokenAsync` の呼び出しを `IPublicClientApplication.AcquireTokenInteractive`の呼び出しに置き換えます。

対話型シナリオの ADAL.NET コードと MSAL.NET コードの比較を次に示します。

ADAL

MSAL

```csharp
var ac = new AuthenticationContext("https://login.microsoftonline.com/<tenantId>");
AuthenticationResult result;
result = await ac.AcquireTokenAsync("<clientId>",
"https://resourceUrl",
new Uri("https://ClientReplyUrl"),
new PlatformParameters(PromptBehavior.Auto));
```

```csharp
var scopes = new[] { "User.Read" };

BrokerOptions options = new BrokerOptions(BrokerOptions.OperatingSystems.Windows);
options.Title = "My Awesome Application";

IPublicClientApplication app =
PublicClientApplicationBuilder.Create("YOUR_CLIENT_ID")
.WithDefaultRedirectUri()
.WithParentActivityOrWindow(GetConsoleOrTerminalWindow)
.WithBroker(options)
.Build();

AuthenticationResult result = null;

// Try to use the previously signed-in account from the cache
IEnumerable<IAccount> accounts = await app.GetAccountsAsync();
IAccount existingAccount = accounts.FirstOrDefault();

try
{    
if (existingAccount != null)
{
result = await app.AcquireTokenSilent(scopes, existingAccount).ExecuteAsync();
}
// Next, try to sign in silently with the account that the user is signed into Windows
else
{    
result = await app.AcquireTokenSilent(scopes, PublicClientApplication.OperatingSystemAccount)
.ExecuteAsync();
}
}
// Can't get a token silently, go interactive
catch (MsalUiRequiredException ex)
{
result = await app.AcquireTokenInteractive(scopes).ExecuteAsync();
}
```

上記の MSAL コードでは WAM (Web アカウント マネージャー) が使用されています。これは、Windowsでユーザーを認証するための推奨される方法です。 WAM なしで対話型認証を使用する場合は、「 [対話型認証](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/acquiring-tokens-interactively)」を参照してください。

WAM を使用するようにアプリケーションを構成するためのその他の要件については、[Web アカウント マネージャー (WAM) での MSAL.NET の使用](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam)に関するドキュメントを参照してください。

Note

WAM は、Windowsでのみ使用できます。 クロスプラットフォーム アプリケーションを構築する場合は、 [WAM を使用せずに対話型認証](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/acquiring-tokens-interactively)にフォールバックできることを確認する必要があります。

## [統合Windows認証 (IWA)](#tab/iwa)
統合Windows認証を使用すると、パブリック クライアント アプリケーションは、Windows ドメインへのサインインに使用したのと同じ ID を使用してユーザーにサインインできます (ドメイン参加済みまたはMicrosoft Entra参加済みマシンの場合)。

##### コードで統合Windows認証を使用しているかどうかを確認する

アプリの ADAL コードには、`AcquireTokenAsync` クラスの拡張メソッドとして使用できる`AuthenticationContextIntegratedAuthExtensions`の呼び出しが含まれている場合に、次のパラメーターを使用して、統合Windows 認証 シナリオが使用されます。

- トークンを要求するリソースを表す`resource`
- アプリケーションの登録を表す GUID である`clientId`
- トークンを要求しようとしているユーザーを表す `UserCredential` オブジェクト。

##### 統合Windows認証シナリオのコードを更新する

コードを更新するための次の手順は、すべての機密クライアント シナリオに適用されます。

1. ソース コードに MSAL.NET 名前空間 (`using Microsoft.Identity.Client;`) を追加します。
2. `AuthenticationContext`をインスタンス化する代わりに、`PublicClientApplicationBuilder.Create`を使用して`IPublicClientApplication`をインスタンス化します。
3. `resourceId`文字列の代わりに、MSAL.NET はスコープを使用します。 ADAL.NET を使用するアプリケーションは事前認証されているため、常に次のスコープを使用できます: `new string[] { $"{resourceId}/.default" }`。
4. `AuthenticationContext.AcquireTokenAsync`の呼び出しを`IPublicClientApplication.AcquireTokenXXX`の呼び出しに置き換えます。*XXX* はシナリオによって異なります。

この場合、 `AuthenticationContext.AcquireTokenAsync` の呼び出しを `IPublicClientApplication.AcquireTokenByIntegratedWindowsAuth`の呼び出しに置き換えます。

統合Windows認証シナリオの ADAL.NET と MSAL.NET コードの比較を次に示します。

ADAL

MSAL

```csharp
var ac = new AuthenticationContext("https://login.microsoftonline.com/<tenantId>");
AuthenticationResult result;
result = await context.AcquireTokenAsync(resource, clientId,
new UserCredential("john@contoso.com"));
```

```csharp
string authority = "https://login.microsoftonline.com/contoso.com";
string[] scopes = new string[] { "user.read" };
IPublicClientApplication app = PublicClientApplicationBuilder
.Create(clientId)
.WithAuthority(authority)
.Build();

var accounts = await app.GetAccountsAsync();

AuthenticationResult result = null;
if (accounts.Any())
{
result = await app.AcquireTokenSilent(scopes, accounts.FirstOrDefault())
.ExecuteAsync();
}
else
{
try
{
result = await app.AcquireTokenByIntegratedWindowsAuth(scopes)
.ExecuteAsync(CancellationToken.None);
}
catch (MsalUiRequiredException ex)
{
// MsalUiRequiredException: AADSTS65001: The user or administrator has not consented to use the application
// with ID '{appId}' named '{appName}'.Send an interactive authorization request for this user and resource.

// you need to get user consent first. This can be done, if you are not using .NET Core (which does not have any Web UI)
// by doing (once only) an AcquireToken interactive.

// If you are using .NET core or don't want to do an AcquireTokenInteractive, you might want to suggest the user to navigate
// to a URL to consent: https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id={clientId}&response_type=code&scope=user.read

// AADSTS50079: The user is required to use multi-factor authentication.
// There is no mitigation - if MFA is configured for your tenant and Azure AD decides to enforce it,
// you need to fallback to an interactive flows such as AcquireTokenInteractive or AcquireTokenByDeviceCode
}
catch (MsalServiceException ex)
{
// Kind of errors you could have (in ex.Message)

// MsalServiceException: AADSTS90010: The grant type is not supported over the /common or /consumers endpoints. Please use the /organizations or tenant-specific endpoint.
// you used common.
// Mitigation: as explained in the message from Azure AD, the authority needs to be tenanted or otherwise organizations

// MsalServiceException: AADSTS70002: The request body must contain the following parameter: 'client_secret or client_assertion'.
// Explanation: this can happen if your application was not registered as a public client application in Azure AD
// Mitigation: in the Azure portal, edit the manifest for your application and set the `allowPublicClient` to `true`
}
catch (MsalClientException ex)
{
// Error Code: unknown_user Message: Could not identify logged in user
// Explanation: the library was unable to query the current Windows logged-in user or this user is not AD or Azure AD
// joined (work-place joined users are not supported).

// Mitigation: Implement your own logic to fetch the username (e.g. john@contoso.com) and use the
// AcquireTokenByIntegratedWindowsAuth form that takes in the username

// Error Code: integrated_windows_auth_not_supported_managed_user
// Explanation: This method relies on a protocol exposed by Active Directory (AD). If a user was created in Azure
// Active Directory without AD backing ("managed" user), this method will fail. Users created in AD and backed by
// Azure AD ("federated" users) can benefit from this non-interactive method of authentication.
// Mitigation: Use interactive authentication
}
}

Console.WriteLine(result.Account.Username);
}
```

## [ユーザー名とパスワード](#tab/up)
ユーザー名とパスワードの認証は、ユーザー名とパスワードの資格情報をアプリケーションに直接提供することによってサインインが行われる場所です。

Warning

**Microsoftでは、他のフローに存在しないセキュリティ リスクが存在するため、このフローは推奨されません**。 運用環境でユーザー名とパスワードのフローを使用している場合は、この記事で説明する他のより安全な代替手段に切り替えることをお勧めします。

##### コードでユーザー名とパスワード認証が使用されているかどうかを確認する

アプリの ADAL コードには、`AcquireTokenAsync` クラスの拡張メソッドとして使用できる`AuthenticationContextIntegratedAuthExtensions`の呼び出しが含まれている場合に、次のパラメーターを指定して、ユーザー名パスワード認証シナリオが使用されます。

- トークンを要求するリソースを表す `resource` 。
- アプリケーションの登録を表す GUID である `clientId` 。
- トークンを要求しようとしているユーザーのユーザー名とパスワードを含む `UserPasswordCredential` オブジェクト。

##### ユーザー名パスワード認証シナリオのコードを更新する

この場合、 `AuthenticationContext.AcquireTokenAsync` の呼び出しを `IPublicClientApplication.AcquireTokenByUsernamePassword`の呼び出しに置き換えます。

ユーザー名のパスワード シナリオの ADAL.NET と MSAL.NET コードの比較を次に示します。

コードを更新するための次の手順は、すべての機密クライアント シナリオに適用されます。

1. ソース コードに MSAL.NET 名前空間 (`using Microsoft.Identity.Client;`) を追加します。
2. `AuthenticationContext`をインスタンス化する代わりに、`PublicClientApplicationBuilder.Create`を使用して`IPublicClientApplication`をインスタンス化します。
3. `resourceId`文字列の代わりに、MSAL.NET はスコープを使用します。 ADAL.NET を使用するアプリケーションは事前認証されているため、常に次のスコープを使用できます: `new string[] { $"{resourceId}/.default" }`。
4. `AuthenticationContext.AcquireTokenAsync`の呼び出しを`IPublicClientApplication.AcquireTokenXXX`の呼び出しに置き換えます。*XXX* はシナリオによって異なります。

ADAL

MSAL

```csharp
var ac = new AuthenticationContext("https://login.microsoftonline.com/<tenantId>");
AuthenticationResult result;
result = await context.AcquireTokenAsync(
resource, clientId, 
new UserPasswordCredential("john@contoso.com", johnsPassword));

```

```csharp
string authority = "https://login.microsoftonline.com/contoso.com";
string[] scopes = new string[] { "user.read" };
IPublicClientApplication app;
app = PublicClientApplicationBuilder.Create(clientId)
.WithAuthority(authority)
.Build();
var accounts = await app.GetAccountsAsync();

AuthenticationResult result = null;
if (accounts.Any())
{
result = await app.AcquireTokenSilent(scopes, accounts.FirstOrDefault())
.ExecuteAsync();
}
else
{
try
{
var securePassword = new SecureString();
foreach (char c in "dummy")        // you should fetch the password
securePassword.AppendChar(c);  // keystroke by keystroke

result = await app.AcquireTokenByUsernamePassword(scopes,
"joe@contoso.com",
securePassword)
.ExecuteAsync();
}
catch(MsalException)
{
// See details below
}
}
Console.WriteLine(result.Account.Username);
```

## [デバイス コード](#tab/devicecode)
デバイス コード フロー認証では、UX が制限されたデバイスに、代替デバイスで認証フローを完了するためのデバイス コードが表示されます。

##### コードでデバイス コード フロー認証が使用されているかどうかを確認する

アプリの ADAL コードには、次のパラメーターを使用した `AuthenticationContext.AcquireTokenByDeviceCodeAsync` の呼び出しが含まれている場合、デバイス コード フロー シナリオが使用されます。

- トークンを要求するリソースの`DeviceCodeResult`と、アプリケーションを表す GUID である`resourceID`を使用してインスタンス化される`clientId` オブジェクト インスタンス。

##### デバイス コード フロー シナリオのコードを更新する

コードを更新するための次の手順は、すべての機密クライアント シナリオに適用されます。

1. ソース コードに MSAL.NET 名前空間 (`using Microsoft.Identity.Client;`) を追加します。
2. `AuthenticationContext`をインスタンス化する代わりに、`PublicClientApplicationBuilder.Create`を使用して`IPublicClientApplication`をインスタンス化します。
3. `resourceId`文字列の代わりに、MSAL.NET はスコープを使用します。 ADAL.NET を使用するアプリケーションは事前認証されているため、常に次のスコープを使用できます: `new string[] { $"{resourceId}/.default" }`。
4. `AuthenticationContext.AcquireTokenAsync`の呼び出しを`IPublicClientApplication.AcquireTokenXXX`の呼び出しに置き換えます。*XXX* はシナリオによって異なります。

この場合、 `AuthenticationContext.AcquireTokenAsync` の呼び出しを `IPublicClientApplication.AcquireTokenWithDeviceCode`の呼び出しに置き換えます。

デバイス コード フロー シナリオの ADAL.NET と MSAL.NET コードの比較を次に示します。

ADAL

MSAL

```csharp
static async Task<AuthenticationResult> GetTokenViaCode(AuthenticationContext ctx)
{
AuthenticationResult result = null;
try
{
result = await ac.AcquireTokenSilentAsync(resource, clientId);
}
catch (AdalException adalException)
{
if (adalException.ErrorCode == AdalError.FailedToAcquireTokenSilently
|| adalException.ErrorCode == AdalError.InteractionRequired)
{
try
{
DeviceCodeResult codeResult = await ctx.AcquireDeviceCodeAsync(resource, clientId);
Console.WriteLine("You need to sign in.");
Console.WriteLine("Message: " + codeResult.Message + "\n");
result = await ctx.AcquireTokenByDeviceCodeAsync(codeResult);
}
catch (Exception exc)
{
Console.WriteLine("Something went wrong.");
Console.WriteLine("Message: " + exc.Message + "\n");
}
}
return result;
}

```

```csharp
private const string ClientId = "<client_guid>";
private const string Authority = "https://login.microsoftonline.com/contoso.com";
private readonly string[] scopes = new string[] { "user.read" };

static async Task<AuthenticationResult> GetATokenForGraph()
{
IPublicClientApplication pca = PublicClientApplicationBuilder
.Create(ClientId)
.WithAuthority(Authority)
.WithDefaultRedirectUri()
.Build();

var accounts = await pca.GetAccountsAsync();

// All AcquireToken* methods store the tokens in the cache, so check the cache first
try
{
return await pca.AcquireTokenSilent(scopes, accounts.FirstOrDefault())
.ExecuteAsync();
}
catch (MsalUiRequiredException ex)
{
// No token found in the cache or Azure AD insists that a form interactive auth is required (e.g. the tenant admin turned on MFA)
// If you want to provide a more complex user experience, check out ex.Classification

return await AcquireByDeviceCodeAsync(pca);
}
}

private static async Task<AuthenticationResult> AcquireByDeviceCodeAsync(IPublicClientApplication pca)
{
try
{
var result = await pca.AcquireTokenWithDeviceCode(scopes,
deviceCodeResult =>
{
// This will print the message on the console which tells the user where to go sign-in using
// a separate browser and the code to enter once they sign in.
// The AcquireTokenWithDeviceCode() method will poll the server after firing this
// device code callback to look for the successful login of the user via that browser.
// This background polling (whose interval and timeout data is also provided as fields in the
// deviceCodeCallback class) will occur until:
// * The user has successfully logged in via browser and entered the proper code
// * The timeout specified by the server for the lifetime of this code (typically ~15 minutes) has been reached
// * The developing application calls the Cancel() method on a CancellationToken sent into the method.
//   If this occurs, an OperationCanceledException will be thrown (see catch below for more details).
Console.WriteLine(deviceCodeResult.Message);
return Task.FromResult(0);
}).ExecuteAsync();

Console.WriteLine(result.Account.Username);
return result;
}

// TODO: handle or throw all these exceptions depending on your app
catch (MsalServiceException ex)
{
// Kind of errors you could have (in ex.Message)

// AADSTS50059: No tenant-identifying information found in either the request or implied by any provided credentials.
// Mitigation: as explained in the message from Azure AD, the authoriy needs to be tenanted. you have probably created
// your public client application with the following authorities:
// https://login.microsoftonline.com/common or https://login.microsoftonline.com/organizations

// AADSTS90133: Device Code flow is not supported under /common or /consumers endpoint.
// Mitigation: as explained in the message from Azure AD, the authority needs to be tenanted

// AADSTS90002: Tenant <tenantId or domain you used in the authority> not found. This may happen if there are
// no active subscriptions for the tenant. Check with your subscription administrator.
// Mitigation: if you have an active subscription for the tenant this might be that you have a typo in the
// tenantId (GUID) or tenant domain name.
}
catch (OperationCanceledException ex)
{
// If you use a CancellationToken, and call the Cancel() method on it, then this *may* be triggered
// to indicate that the operation was cancelled.
// See https://learn.microsoft.com/dotnet/standard/threading/cancellation-in-managed-threads
// for more detailed information on how C# supports cancellation in managed threads.
}
catch (MsalClientException ex)
{
// Possible cause - verification code expired before contacting the server
// This exception will occur if the user does not manage to sign-in before a time out (15 mins) and the
// call to `AcquireTokenWithDeviceCode` is not cancelled in between
}
}
```

---

#### MSAL の利点

MSAL ライブラリの利点については、[アプリケーションを Microsoft Authentication Library に移行する (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-migration) に関する記事を参照してください。

### MSAL を使用したログ記録の重要性

多くの機能の中で、Microsoft Authentication Library (MSAL) には堅牢な組み込みの[ログ機能があります](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/msal-logging)。 アプリケーションでログ記録を有効にすると、認証の問題を直接把握でき、独自のアプリケーションで簡単に診断でき、MSAL チームが潜在的な問題に迅速に対処できます。 運用環境のシナリオでデプロイする場合は、アプリケーションのログ記録を有効にすることを強くお勧めします。

#### Troubleshooting

次のトラブルシューティング情報では、2 つの前提条件があります。

- ADAL.NET コードが動作していました。
- 同じクライアント ID を保持して MSAL に移行しました。

次のメッセージで例外が発生した場合:

>
> `AADSTS90002: Tenant 'aaaabbbb-0000-cccc-1111-dddd2222eeee' not found. This may happen if there are no active``subscriptions for the tenant. Check to make sure you have the correct tenant ID. Check with your subscription``administrator.`

次の手順を使用して、例外のトラブルシューティングを行うことができます。

1. 最新バージョンの MSAL.NET を使用していることを確認します。
2. 機密クライアント アプリケーションをビルドするときに設定した機関ホストと、ADAL で使用した機関ホストが類似していることを確認します。 特に、それは同じ[クラウド](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-national-cloud)（Azure Government、21Vianet が運営する Microsoft Azure、または Azure Germany）ですか。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/msal-net-migration"} -->
## MSAL.NET と Microsoft.Identity.Web への移行 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/msal-net-migration
- Service: msal / msal-dotnet
- Article date: 2024-06-04
- Summary: .NET用 AZURE AD 認証ライブラリ (ADAL.NET) から .NET (MSAL.NET) またはMicrosoftのMicrosoft Authentication Libraryに移行する理由と方法について説明します。Identity.Web

Warning

Azure Active Directory認証ライブラリ (ADAL) **は非推奨になりました**。 ADAL を使用する既存のアプリは引き続き機能しますが、Microsoftは ADAL のセキュリティ修正プログラムをリリースしなくなります。 アプリのセキュリティを危険にさらさないようにするには、[Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/msal/) を使用します。

### 移行する理由

Azure AD Authentication Library for .NET (ADAL.NET) [は非推奨となり](https://devblogs.microsoft.com/identity/update-your-applications-from-adal-to-msal/)、セキュリティのバグを含む新機能やバグ修正は実装されません。 ADAL を使用するアプリケーションは引き続き機能します。

#### ADAL を直接使用するアプリの移行ガイド

MSAL.NET と ADAL.NET の詳細を調べると、MSAL.NET または [`Microsoft.Identity.Web`](https://learn.microsoft.com/ja-jp/entra/msidweb/) などの上位レベルのライブラリを使用するかどうかを確認できます。 以下のデシジョン ツリーの詳細については、[MSAL.NET またはMicrosoftを参照してください。Identity.Web](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/choosing-msal-dotnet)。

- Azure AD B2C と Microsoft Entra 外部 ID を使用して、職場または学校アカウント、個人のMicrosoft アカウント、ソーシャル アカウントまたはローカル アカウントなど、より広範なMicrosoft ID のセットを認証できます。
- ユーザーは、最高のシングル サインオン (SSO) エクスペリエンスを実現します。
- アプリケーションでは、増分同意、条件付きアクセス、およびその他の新しいセキュリティ機能を有効にすることができます。
- セキュリティと回復性の面で継続的なイノベーションの恩恵を受けます。

Important

**MSAL.NET またはMicrosoft。Identity.Web は、Microsoft ID プラットフォームで使用する推奨される認証ライブラリになりました**。 ADAL には新しい機能は実装されません。 詳細については、「お知らせ: [アプリケーションを ADAL から MSAL に更新する](https://devblogs.microsoft.com/identity/update-your-applications-from-adal-to-msal/)」を参照してください。

### MSAL.NET と Microsoft.Identity.Web のどちらに移行すべきか

MSAL.NET と ADAL.NET の詳細を調べると、MSAL.NET または [`Microsoft.Identity.Web`](https://learn.microsoft.com/ja-jp/entra/msidweb/) などの上位レベルのライブラリを使用するかどうかを確認できます。 以下のデシジョン ツリーの詳細については、[MSAL.NET またはMicrosoftを参照してください。Identity.Web](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/choosing-msal-dotnet)。

#### ADAL を間接的に使用するアプリの移行ガイド

知らないうちに他の SDK の ADAL 依存関係を使用する可能性があります。 言い換えると、ADAL は推移的な依存関係です。 これは、潜在的なセキュリティの問題を修正したり、セキュリティの向上の恩恵を受けたりするために、アプリケーションが ADAL をアップグレードできないため、アプリケーションに対するリスクを引き続き表します。

移行するには、まず、ADAL を使用するルート依存関係を特定する必要があります。 ほとんどの場合、ルート依存関係自体は非推奨です。 ルート依存関係を識別するには、`dotnet nuget why`[command](https://learn.microsoft.com/ja-jp/dotnet/core/tools/dotnet-nuget-why) Visual Studio使用できます

最も一般的な非推奨パッケージとその MSAL の代替手段を次に示します。 移行の詳細については、該当する [Azure SDK for .NET](https://learn.microsoft.com/ja-jp/dotnet/api/overview/azure/) ライブラリのページにある [AppAuthentication to Azure.Identity Migration Guidance](https://learn.microsoft.com/ja-jp/dotnet/api/overview/azure/app-auth-migration) および **Migration guide** のリンクを参照してください。

| レガシ パッケージ (ADAL 依存、非推奨) | サポートされているパッケージ (MSAL 依存、現在) |
| --- | --- |
| `Microsoft.Azure.KeyVault` | `Azure.Security.KeyVault.Secrets, Azure.Security.KeyVault.Keys, Azure.Security.KeyVault.Certificates` |
| `Microsoft.Azure.Management.Compute` | `Azure.ResourceManager.Compute` |
| `Microsoft.Azure.Services.AppAuthentication` | `Azure.Identity` |
| `Microsoft.Azure.Management.StorageSync` | `Azure.ResourceManager.StorageSync` |
| `Microsoft.Azure.Management.Fluent` | `Azure.ResourceManager` |
| `Microsoft.Azure.Management.EventGrid` | `Azure.ResourceManager.EventGrid` |
| `Microsoft.Azure.Management.Automation` | `Azure.ResourceManager.Automation` |
| `Microsoft.Azure.Management.Compute.Fluent` | `Azure.ResourceManager.Compute` |
| `Microsoft.Azure.Management.MachineLearning.Fluent` | `Azure.ResourceManager.MachineLearningCompute` |
| `Microsoft.Azure.Management.Media, windowsazure.mediaservices` | `Azure.ResourceManager.Media` |
| `Microsoft.Kusto.Client` | `Microsoft.Azure.Kusto.Data` |
| `Microsoft.Kusto.Ingest` | `Microsoft.Azure.Kusto.Ingest` |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/override-target-framework"} -->
## ターゲット フレームワークのオーバーライド - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/override-target-framework
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: まれに、MSAL のフレームワーク バージョンを決定する NuGet のアルゴリズムをオーバーライドすることが必要になる場合があります。

まれに、MSAL のフレームワーク バージョンを決定する NuGet のアルゴリズムをオーバーライドすることが必要になる場合があります。 これは、機密クライアント アプリケーションがあり、MSAL はこれらのプラットフォームでWindows フォームを使用するため、`net5.0-windows10.x`をターゲットにする必要がある場合に便利です。これにより、一部の環境 (Azure Functions など) でビルド エラーが発生します。

要件を実装する方法を示すサンプルについては、 [`TfmOverride`](https://github.com/bgavrilMS/TfmOverride) プロジェクトを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/overriding-authority"} -->
## 権限のオーバーライド - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/overriding-authority
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: MSAL.NET アプリケーションの既定の機関をオーバーライドする方法。

[マルチテナント アプリのクライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/client-credential-multi-tenant)など、多くのシナリオでは、アプリケーション ビルダーではなく、要求ビルダーで Microsoft Entra テナントを指定すると便利です。 `WithTenantId` は、テナント ID 文字列を受け入れる、このシナリオで使用する推奨 API です。 `WithTenantIdFromAuthority` は、MSAL 4.46.0 以降で使用できるもう 1 つの同様の方法です。 `WithAuthority`を使用することもできますが、アプリケーションの権限と要求ビルダーは常に同じクラウド用である必要があります。つまり、機関 URL のホストが異なってはなりません。

```csharp
var app =  ConfidentialClientApplicationBuilder
                .Create(PublicCloudConfidentialClientID)
                .WithAuthority("https://login.microsoftonline.com/common", true)
                .Build();

var result = await app.AcquireTokenForClient(scopes)
                      .WithTenantId("123456-1234-2345-1234561234");
// OR
var result = await app.AcquireTokenForClient(scopes)
                      .WithTenantIdFromAuthority("https://login.microsoftonline.com/123456-1234-2345-1234561234");
```

パブリックまたは機密のクライアント アプリケーション インスタンスは、1 つのクラウドにのみ関連付けることができます。 クライアント アプリケーションで複数のクラウドを同時に処理する必要がある場合は、それぞれのパブリック クライアント インスタンスまたは機密クライアント インスタンスを個別に作成します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/protect-ios-android-mam-intune"} -->
## InTune を使用した iOS および Android アプリケーションの保護 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/protect-ios-android-mam-intune
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: MSAL.NET に依存する Android および iOS アプリケーションで InTune を使用する方法。

### シナリオ

特定のリソースを保護するには、ユーザー認証だけでは不十分な場合があります。 アクセスするデバイスは、InTune で定義されているポリシーに従って準拠している必要もあります。

Microsoft Entra IDは、条件付きアクセス ポリシーに従ってデバイスが準拠するまでアクセス トークンが発行されないようにします。 このページでは、InTune [Mobile Application Management (MAM)](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/deployment-guide-enrollment-mamwe) によって保護されている間に、MSAL.NET によってリソースにアクセスする方法について説明します。

### システム構成の概要

システムは、ホストされているリソースへのアクセスを提供するバックエンド アプリと、リソースにアクセスする必要があるクライアント アプリの 2 つのアプリで構成されます。リソースはスコープによって定義されます。 クライアント アプリがリソースを必要とする場合は、スコープへのアクセスを要求します。

Microsoft Entra IDは、リソースに条件付きアクセスを適用することによってリソースを保護します。 アクセスの条件の 1 つは、クライアント アプリにアプリ保護ポリシーを設定することです。

アプリ保護 ポリシーは、アプリの InTune ポータルで作成でき、1 つ以上のユーザー グループに適用できます。

詳細な [セットアップ手順](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/create-config-for-mam-conditional-access)を次に示します。

### iOS のワークフロー

セットアップの結果、アプリがリソースに到達しようとしたときに、デバイスが準拠していない場合、Microsoft Entra IDはサブエラー`protection_policy_required`返します。

MSAL.NET エラーをキャッチし、`IntuneAppProtectionPolicyRequiredException`をスローします。

アプリは、エラーをキャッチし、InTune MAM SDK を呼び出してデバイスを準拠させる必要があります。 デバイスが準拠すると、InTune MAM SDK によってキーチェーンに enrollmentID が書き込まれます。

その後、アプリは、MSAL.NET のサイレント トークン取得メソッドを呼び出してトークンを取得できます。 MSAL.NET キーチェーンから登録 ID を取得し、バックエンドを呼び出します。

これにより、アクセス トークンが返されます。

#### コード断片

保護されたスコープ "Hello.World" へのアクセスをシークするアプリ コード

```csharp
// The following parameters are for sample app in lab4. Please configure them as per your app registration.
// And also update corresponding entries in info.plist -> IntuneMAMSettings -> ADALClientID and ADALRedirectUri
string clientId = "00001111-aaaa-2222-bbbb-3333cccc4444";
string redirectURI = $"msauth.com.xamarin.microsoftintunemamsample://auth";
string tenantID = "aaaabbbb-0000-cccc-1111-dddd2222eeee";
string[] Scopes = { "api://aaaabbbb-0000-cccc-1111-dddd2222eeee/Hello.World" }; // needs admin consent
string[] clientCapabilities = { "ProtApp" }; // Important: This must be passed to the PCABuilder

try
{
        string authority = $"https://login.microsoftonline.com/{tenantID}/";
        var pcaBuilder = PublicClientApplicationBuilder.Create(clientId)
                                                            .WithRedirectUri(redirectURI)
                                                            .WithIosKeychainSecurityGroup("com.microsoft.adalcache")
                                                            .WithLogging(MSALLogCallback, LogLevel.Verbose)
                                                            .WithAuthority(authority)
                                                            .WithClientCapabilities(clientCapabilities)
                                                            .WithHttpClientFactory(new HttpSnifferClientFactory())
                                                            .WithBroker(true);
        PCA = pcaBuilder.Build();
    // attempt silent login.
    // If this is very first time and the device is not enrolled, it will throw MsalUiRequiredException
    // If the device is enrolled, this will succeed.
    var authResult = await DoSilentAsync(Scopes).ConfigureAwait(false);
    ShowAlert("Success Silent 1", authResult.AccessToken);
}
catch (MsalUiRequiredException _)
{
    // This executes UI interaction
    try
    {
        var interParamBuilder = PCA.AcquireTokenInteractive(Scopes)
                                    .WithParentActivityOrWindow(this)
                                    .WithUseEmbeddedWebView(true);

        var authResult = await interParamBuilder.ExecuteAsync().ConfigureAwait(false);
        ShowAlert("Success Interactive", authResult.AccessToken);
    }
    catch (IntuneAppProtectionPolicyRequiredException ex)
}
```

アプリ コードは例外をキャッチし、MAM SDK を呼び出してアプリを準拠させます。 コンプライアンスを待機します。

```csharp
 catch (IntuneAppProtectionPolicyRequiredException ex)
{
    _manualReset.Reset();

    IntuneMAMComplianceManager.Instance.RemediateComplianceForIdentity(ex.Upn, false);
    _manualReset.WaitOne();
}
```

アプリが準拠すると、代理人に通知されます。 デリゲートは、セマフォにフラグを設定します。

```csharp
public async override void IdentityHasComplianceStatus(string identity, IntuneMAMComplianceStatus status, string errorMessage, string errorTitle)
{
    if (status == IntuneMAMComplianceStatus.Compliant)
    {
        _manualReset.Set();
    }
}
```

セマフォが解放されると、アプリはサイレント トークン取得メソッドを呼び出す必要があります。

```csharp
 var accts = await PCA.GetAccountsAsync().ConfigureAwait(false);
var acct = accts.FirstOrDefault();
if (acct != null)
{
    try
    {
        var silentParamBuilder = PCA.AcquireTokenSilent(Scopes, acct);
        var authResult = await silentParamBuilder.ExecuteAsync().ConfigureAwait(false);
        ShowAlert("Success Silent", authResult.AccessToken);
    }
}
```

### Android 用ワークフロー

セットアップの結果、アプリがリソースに到達しようとしたときに、デバイスが準拠していない場合、Microsoft Entra IDはサブエラー`protection_policy_required`返します。

MSAL.NET エラーをキャッチし、`IntuneAppProtectionPolicyRequiredException`をスローします。

アプリは、エラーをキャッチし、Intune MAM SDK を呼び出してデバイスを準拠させる必要があります。 デバイスが準拠するためには、アプリが `IMAMEnrollmentManager`のコールバックを登録する必要があります。

コールバックは、 `upn`、 `aaid` 、および `resourceID`で提供されます。 `resourceID`は MAM API を指し、コールバックはサイレント トークン取得を使用してリソースのトークンを返す必要があります。

アプリでは、 `MAMNotificationType.MamEnrollmentResult`のコールバックも登録する必要があります。 登録が成功すると、アプリは MSAL.NET のサイレント トークン取得メソッドを呼び出してトークンを取得できます。

これにより、アクセス トークンが返されます。

#### コード断片

コールバックの登録 `OnMAMCreate()`

```csharp
IMAMEnrollmentManager mgr = MAMComponents.Get<IMAMEnrollmentManager>();
mgr.RegisterAuthenticationCallback(new MAMWEAuthCallback());

// Register the notification receivers to receive MAM notifications.
// Along with other, this will receive notification that the device has been enrolled.
IMAMNotificationReceiverRegistry registry = MAMComponents.Get<IMAMNotificationReceiverRegistry>();
registry.RegisterReceiver(new EnrollmentNotificationReceiver(), MAMNotificationType.MamEnrollmentResult);
```

保護されたスコープ "Hello.World" へのアクセスをシークするアプリ コードは、PCA をラップするラッパー クラスでメソッドを呼び出します。

```csharp
try
{
    // attempt silent login.
    // If this is very first time and the device is not enrolled, it will throw MsalUiRequiredException
    // If the device is enrolled, this will succeed.
    result = await PCAWrapper.Instance.DoSilentAsync(Scopes).ConfigureAwait(false);

    _ = await ShowMessage("Silent 1", result.AccessToken).ConfigureAwait(false);
}
catch (MsalUiRequiredException )
{
    try
    {
        // This executes UI interaction
        result = await PCAWrapper.Instance.DoInteractiveAsync(Scopes, this).ConfigureAwait(false);

        _ = await ShowMessage("Interactive 1", result.AccessToken).ConfigureAwait(false);
    }
    catch (IntuneAppProtectionPolicyRequiredException exProtection)
    {
        // if the scope requires App Protection Policy,  IntuneAppProtectionPolicyRequiredException is thrown.
        // Perform registration operation here and then do the silent token acquisition
        _ = await DoMAMRegister(exProtection).ContinueWith(async (s) =>
            {
                try
                {
                    // Now the device is registered, perform silent token acquisition
                    result = await PCAWrapper.Instance.DoSilentAsync(Scopes).ConfigureAwait(false);

                    _ = await ShowMessage("Silent 2", result.AccessToken).ConfigureAwait(false) ;
                }
                catch (Exception ex)
                {
                    _ = await ShowMessage("Exception 1", ex.Message).ConfigureAwait(false);
                }
            }).ConfigureAwait(false);
    }
}
```

MAM 登録のコードは次のとおりです。

```csharp
private async Task DoMAMRegister(IntuneAppProtectionPolicyRequiredException exProtection)
{
    // reset the registered event
    IntuneSampleApp.MAMRegsiteredEvent.Reset();
    
    // Invoke compliance API on a different thread
    await Task.Run(() =>
                        {
                            IMAMComplianceManager mgr = MAMComponents.Get<IMAMComplianceManager>();
                            mgr.RemediateCompliance(exProtection.Upn, exProtection.AccountUserId, exProtection.TenantId, exProtection.AuthorityUrl, false);
                        }).ConfigureAwait(false);

    // wait till the registration completes
    // Note: This is a sample app for MSAL.NET. Scenarios such as what if enrollment fails or user chooses not to enroll will be as
    // per the business requirements of the app and not considered in the sample app.
    IntuneSampleApp.MAMRegsiteredEvent.WaitOne();
}
```

アプリが準拠すると、コールバックで通知されます。 コールバックによって、セマフォにフラグが設定されます。 これにより、 `DoMAMRegister`のブロックが解除されます。

```csharp
if (notification.Type == MAMNotificationType.MamEnrollmentResult)
{
    IMAMEnrollmentNotification enrollmentNotification = notification.JavaCast<IMAMEnrollmentNotification>();
    MAMEnrollmentManagerResult result = enrollmentNotification.EnrollmentResult;

    if (result.Equals(MAMEnrollmentManagerResult.EnrollmentSucceeded))
    {
        // this signals that MAM registration is complete and the app can proceed
        IntuneSampleApp.MAMRegsiteredEvent.Set();
    }
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/synchronous-programming"} -->
## MSAL.NET を使用した同期プログラミング - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/synchronous-programming
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: MSAL.NET は、タスク ベースの非同期パターン (TAP) に基づいています。 このページでは、非同期メソッドを同期的に使用する方法に関するガイダンスへのリンクを示します。 これには、すべてに適合するソリューションはありません。 そのため、さまざまなベスト プラクティスをお勧めします。

パフォーマンスと応答性の高いアプリを向上させるために、非同期プログラミングプラクティスを使用することを強くお勧めします。 ただし、一部のレガシ アプリでは非同期プログラミングを使用できません。

MSAL.NET は、タスク ベースの非同期パターン (TAP) に基づいています。 このページでは、非同期メソッドを同期的に使用する方法に関するガイダンスへのリンクを示します。 これには、すべてに適合するソリューションはありません。 そのため、さまざまなベスト プラクティスをお勧めします。

### 非同期プログラミング

非同期プログラミングに慣れていない場合は、「 [async と await を使用した非同期プログラミング](https://learn.microsoft.com/ja-jp/dotnet/csharp/programming-guide/concepts/async/)」を参照してください。

LinkedInの[「C# の高度なプログラミング](https://www.linkedin.com/learning/async-programming-in-c-sharp/introduction?u=3322)」コースもご確認ください。

### 同期コードからの非同期メソッドの呼び出し

同期コードから非同期コードを実行するには、いくつかの方法があります。 さまざまなリンクがここに記載されています。

[Task.RunSynchronously](https://learn.microsoft.com/ja-jp/dotnet/api/system.threading.tasks.task.runsynchronously)

```csharp
var getAcctsTasks = PCA.RemoveAsync(acct);
// there is no timeout for RunSynchronously
if (!getAcctsTasks.IsCompleted)
{
   getAcctsTasks.RunSynchronously();
}
```

[Task.Wait を使用してタスクが完了するのを待ちます](https://learn.microsoft.com/ja-jp/dotnet/api/system.threading.tasks.task.wait)

```csharp
// wait can optionally have timeout, and cancellation token (not shown)
int timeoutMilliSec = 3000;
PCA.RemoveAsync(acct).Wait(timeoutMilliSec);
```

[Task.Result で結果が得られるのを待つ](https://learn.microsoft.com/ja-jp/dotnet/api/system.threading.tasks.task-1.result#remarks)

```csharp
var authResult = PCA.AcquireTokenSilent(Scopes, acct).ExecuteAsync().Result;
return authResult;
```

複数のタスクをラップする前に一度に複数のタスクを実行する必要がある場合は、 [タスク ベースの非同期パターンの使用に関する説明を](https://learn.microsoft.com/ja-jp/dotnet/standard/asynchronous-programming-patterns/consuming-the-task-based-asynchronous-pattern)参照すると便利な場合があります。

### 例外とデッドロックに注意する

`.ConfigureAwait(false)`を使用して例外をキャッチし、デッドロックを防ぐ方法を次に示します。

```csharp
try
{
    Console.WriteLine("Pre AcquireTokenInteractive");
    // Run with wait command
    // create the builder
    var builder = PCA.AcquireTokenInteractive(Scopes);

    // run it interactively.
    // make sure to have ConfigureAwait(false) to avoid any potential deadlocks
    var authResult = builder.ExecuteAsync()
                .ConfigureAwait(false)
                .GetAwaiter()
                .GetResult();
    Console.WriteLine("Post AcquireTokenInteractive - Got the token");

    return result;
}
catch (MsalClientException ex)
{
    // catch MSAL exception
    Console.WriteLine(ex.Message);
}
catch (Exception ex)
{
    Console.WriteLine(ex.Message);
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/how-to/token-cache-serialization"} -->
## トークン キャッシュのシリアル化 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization
- Service: msal / msal-dotnet
- Article date: 2024-05-20
- Summary: .NET (MSAL.NET) のMicrosoft Authentication Libraryを使用したトークン キャッシュのシリアル化とカスタム シリアル化について説明します。

Microsoft Authentication Library (MSAL) は[、トークンを取得](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-acquire-cache-tokens)した後、そのトークンをキャッシュします。 パブリック クライアント アプリケーション (デスクトップ アプリとモバイル アプリ) は、別の方法でトークンを取得する前に、キャッシュからトークンを取得しようとする必要があります。 機密クライアント アプリケーションの取得方法は、キャッシュ自体を管理します。 この記事では、MSAL.NET でのトークン キャッシュの既定のシリアル化とカスタムシリアル化について説明します。

### まとめ

推奨事項は次のとおりです。

- モバイル アプリを作成する場合、キャッシュは MSAL によって既に事前に構成されています。
- デスクトップ アプリケーションを記述するときは、 [デスクトップ アプリ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization?tabs=desktop)で説明されているようにクロスプラットフォーム トークン キャッシュを使用します。
- 新しい機密クライアント アプリケーション ([Web アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-web-app-call-api-overview)、[Web API](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-web-api-call-api-overview)、または [サービス間またはデーモン アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-daemon-overview)) を作成するときは、高水準 API として [Microsoft.Identity.Web](https://learn.microsoft.com/ja-jp/entra/msidweb/) を使用します。 ASP.NET Core、ASP.NET クラシックとの統合を提供し、スタンドアロンでも動作します。
- MSAL.NET を直接利用する既存の機密クライアント アプリケーションは、引き続きこれを行うことができます。
- [Web アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-web-app-call-api-overview)と [Web API では](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-web-api-call-api-overview)、制約付きメモリ キャッシュと組み合わせて[分散トークン キャッシュ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization?tabs=aspnet#distributed-caches) (Redis、SQL Server、Azure Cosmos DBなど) を使用する必要があります。
- 保存時の暗号化は、必要に応じて [ASP.NET Core Data Protection](https://learn.microsoft.com/ja-jp/aspnet/core/security/data-protection/introduction) を使用して構成できます。
- [Web アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/scenario-web-app-call-api-overview) は、セッション Cookie に依存する場合もあります。ただし、Cookie サイズのため、このオプションは推奨されません。
- [サービス間アプリとデーモン アプリ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization?tabs=aspnet#distributed-caches) は、メモリ キャッシュのみに依存できます。 アプリが多数のテナントにサービスを提供している場合は、削除ポリシーを構成します。
- マネージド ID トークンはメモリ内でのみキャッシュされます。

## [Microsoft.Identity.Web を使用する機密クライアント](#tab/aspnetcore)
[Microsoft.Identity.Web.TokenCache](https://www.nuget.org/packages/Microsoft.Identity.Web.TokenCache) NuGet パッケージは、[Microsoft.Identity.Web](https://github.com/AzureAD/microsoft-identity-web) ライブラリ内でトークン キャッシュのシリアル化を提供します。 ライブラリは、ASP.NET Coreと ASP.NET Classic の両方との統合を提供し、その抽象化を使用して他の Web アプリまたは API フレームワークを駆動できます。

Note

次の例は、ASP.NET Core用です。 ASP.NET の場合、コードは同様です。参照実装については、[`ms-identity-aspnet-wepapp-openidconnect` Web アプリのサンプル](https://github.com/Azure-Samples/ms-identity-aspnet-webapp-openidconnect/blob/archive/WebApp/App_Start/Startup.Auth.cs)を参照してください。

| 拡張メソッド | 説明 |
| --- | --- |
| [AddInMemoryTokenCaches](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.web.microsoftidentityappcallswebapiauthenticationbuilder.addinmemorytokencaches) | トークンの格納と取得のためにメモリ内に一時キャッシュを作成します。 メモリ内トークン キャッシュは他のキャッシュの種類よりも高速ですが、そのトークンはアプリケーションの再起動の間に保持されず、キャッシュ サイズを制御することはできません。 メモリ内キャッシュは、アプリの再起動の間にトークンを保持する必要がないアプリケーションに適しています。 サービス、デーモン、 [AcquireTokenForClient](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokenforclientparameterbuilder) (クライアント資格情報付与) を使用する他のサービスなどのコンピューター間認証シナリオに参加するアプリでメモリ内トークン キャッシュを使用します。 メモリ内トークン キャッシュは、サンプル アプリケーションやローカル アプリ開発時にも適しています。 Microsoft。Identity.Web バージョン 1.19.0 以降では、すべてのアプリケーション インスタンスでメモリ内トークン キャッシュが共有されます。 |
| [AddSessionTokenCaches](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.web.microsoftidentityappcallswebapiauthenticationbuilderextension.addsessiontokencaches) | トークン キャッシュはユーザー セッションにバインドされます。 このオプションは、ID トークンに多くの要求が含まれている場合は理想的ではありません。これは、Cookie が大きすぎるためです。 |
| `AddDistributedTokenCaches` | トークン キャッシュは、ASP.NET Core `IDistributedCache`実装に対するアダプターです。 これにより、分散メモリ キャッシュ、Redis キャッシュ、分散 NCache、またはSQL Server キャッシュのいずれかを選択できます。 `IDistributedCache`実装の詳細については、「[分散メモリ キャッシュ](https://learn.microsoft.com/ja-jp/aspnet/core/performance/caching/distributed)」を参照してください。 |

#### メモリ内トークン キャッシュ

ASP.NET Core アプリケーションの [Startup](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.aspnetcore.hosting.startupbase.configureservices) クラスの [ConfigureServices](https://learn.microsoft.com/ja-jp/aspnet/core/fundamentals/startup) メソッドでメモリ内キャッシュを使用するコードの例を次に示します。

```csharp
using Microsoft.Identity.Web;

public class Startup
{
 const string scopesToRequest = "user.read";
  
  public void ConfigureServices(IServiceCollection services)
  {
   // code before
   services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
           .AddMicrosoftIdentityWebApp(Configuration)
             .EnableTokenAcquisitionToCallDownstreamApi(new string[] { scopesToRequest })
                .AddInMemoryTokenCaches();
   // code after
  }
  // code after
}
```

`AddInMemoryTokenCaches` は、アプリ専用トークンを要求する場合に運用環境に適しています。 ユーザー トークンを使用する場合は、分散トークン キャッシュの使用を検討してください。

トークン キャッシュ構成コードは、ASP.NET Core Web アプリと Web API の間で似ています。

#### 分散トークン キャッシュ

考えられる分散キャッシュの例を次に示します。

```csharp
// or use a distributed Token Cache by adding
   services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
           .AddMicrosoftIdentityWebApp(Configuration)
             .EnableTokenAcquisitionToCallDownstreamApi(new string[] { scopesToRequest }
               .AddDistributedTokenCaches();

// Distributed token caches have a L1/L2 mechanism.
// L1 is in memory, and L2 is the distributed cache
// implementation that you will choose below.
// You can configure them to limit the memory of the 
// L1 cache, encrypt, and set eviction policies.
services.Configure<MsalDistributedTokenCacheAdapterOptions>(options => 
  {
    // Optional: Disable the L1 cache in apps that don't use session affinity
    //                 by setting DisableL1Cache to 'true'.
    options.DisableL1Cache = false;
    
    // Or limit the memory (by default, this is 500 MB)
    options.L1CacheOptions.SizeLimit = 1024 * 1024 * 1024; // 1 GB

    // You can choose if you encrypt or not encrypt the cache
    options.Encrypt = false;

    // And you can set eviction policies for the distributed
    // cache.
    options.SlidingExpiration = TimeSpan.FromHours(1);
  });

// Then, choose your implementation of distributed cache
// -----------------------------------------------------

// good for prototyping and testing, but this is NOT persisted and it is NOT distributed - do not use in production
services.AddDistributedMemoryCache();

// Or a Redis cache
// Requires the Microsoft.Extensions.Caching.StackExchangeRedis NuGet package
services.AddStackExchangeRedisCache(options =>
{
 options.Configuration = "localhost";
 options.InstanceName = "SampleInstance";
});

// You can even decide if you want to repair the connection
// with Redis and retry on Redis failures. 
services.Configure<MsalDistributedTokenCacheAdapterOptions>(options => 
{
  options.OnL2CacheFailure = (ex) =>
  {
    if (ex is StackExchange.Redis.RedisConnectionException)
    {
      // action: try to reconnect or something
      return true; //try to do the cache operation again
    }
    return false;
  };
});

// Or even a SQL Server token cache
// Requires the Microsoft.Extensions.Caching.SqlServer NuGet package
services.AddDistributedSqlServerCache(options =>
{
 options.ConnectionString = _config["DistCache_ConnectionString"];
 options.SchemaName = "dbo";
 options.TableName = "TestCache";
});

// Or an Azure Cosmos DB cache
// Requires the Microsoft.Extensions.Caching.Cosmos NuGet package
services.AddCosmosCache((CosmosCacheOptions cacheOptions) =>
{
    cacheOptions.ContainerName = Configuration["CosmosCacheContainer"];
    cacheOptions.DatabaseName = Configuration["CosmosCacheDatabase"];
    cacheOptions.ClientBuilder = new CosmosClientBuilder(Configuration["CosmosConnectionString"]);
    cacheOptions.CreateIfNotExists = true;
});
```

詳細については、以下を参照してください:

- [分散キャッシュ暗号化とその他の詳細オプション](https://github.com/AzureAD/microsoft-identity-web/wiki/L1-Cache-in-Distributed-%28L2%29-Token-Cache)
- [L2 キャッシュの削除を処理する](https://github.com/AzureAD/microsoft-identity-web/wiki/Handle-L2-cache-eviction)
- [Docker で Redis Cache を設定する](https://github.com/AzureAD/microsoft-identity-web/wiki/Set-up-a-Redis-cache-in-Docker)
- [Troubleshooting](https://github.com/AzureAD/microsoft-identity-web/wiki/Token-Cache-Troubleshooting)

分散キャッシュの使用方法は、[フェーズ 2-2 トークン キャッシュ](https://learn.microsoft.com/ja-jp/aspnet/core/tutorials/first-mvc-app/)の [ASP.NET Core Web アプリのチュートリアル](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/2-WebApp-graph-user/2-2-TokenCache)で紹介されています。

## [MSAL.NET を使用する機密クライアント](#tab/msal)
.NET の機密クライアントでは、MSAL.NET に基づく [Microsoft.Identity.Web](https://learn.microsoft.com/ja-jp/entra/msidweb/) の使用が推奨されます。これは、より高レベルの API で複雑なシナリオ（ゲスト ユーザー、継続的アクセス評価、Proof-of-Possession トークンなど）に標準で対応できるためです。 AZURE API にアクセスする必要があるアプリケーションでは、MSAL を内部的に活用する[Azure SDK](https://azure.github.io/azure-sdk/)を使用する必要があります。

MSAL.NET を直接使用している場合は、次の資料が関連します。

#### 使用可能なキャッシュ テクノロジ

- 削除なしのメモリ キャッシュ
- 削除を含むメモリ キャッシュ
- 分散キャッシュとメモリ キャッシュの組み合わせ

##### 削除なしのメモリ キャッシュ

`.WithCacheOptions(CacheOptions.EnableSharedCacheOptions)`を使用して、多くの (100,000 を超える) テナントを対象としない`AcquireTokenForClient`サービス間アプリケーションを構築します。

Important

このオプションを使用してキャッシュのサイズを制御する方法はありません。 Web サイト、Web API、またはマルチテナント サービス間アプリを構築する場合は、 `Memory cache with eviction` セクションを参照してください。

```csharp
    // Create the confidential client application
    app= ConfidentialClientApplicationBuilder.Create(clientId)
       // Alternatively to the certificate, you can use .WithClientSecret(clientSecret)
       .WithCertificate(cert)
       .WithLegacyCacheCompatibility(false)
       .WithCacheOptions(CacheOptions.EnableSharedCacheOptions)
       .WithAuthority(authority)
       .Build();
```

`WithCacheOptions(CacheOptions.EnableSharedCacheOptions)` は、MSAL クライアント アプリケーション インスタンス間で共有される内部 MSAL トークン キャッシュを作成します。 トークン キャッシュの共有は、トークン キャッシュのシリアル化を使用するよりも高速ですが、内部メモリ内トークン キャッシュには削除ポリシーがありません。 既存のトークンは更新されますが、ユーザー、テナント、リソースごとにトークンをフェッチすると、それに応じてキャッシュが拡張されます。

#### 削除を含むメモリ キャッシュ

プロジェクトで[Microsoft.Identity.Web.TokenCache](https://www.nuget.org/packages/Microsoft.Identity.Web.TokenCache) NuGet パッケージを参照します。

次のコードは、削除を使用してメモリ内キャッシュを追加する方法を示しています。

```CSharp
using Microsoft.Identity.Web;
using Microsoft.Identity.Client;
using Microsoft.Extensions.DependencyInjection;

public static async Task<AuthenticationResult> GetTokenAsync(string clientId, X509Certificate cert, string authority, string[] scopes)
 {
     // Create the confidential client application
     app= ConfidentialClientApplicationBuilder.Create(clientId)       
       .WithCertificate(cert)
       .WithLegacyCacheCompatibility(false)
       .WithAuthority(authority)
       .Build();

     // Add a static in-memory token cache and set an eviction policy
     app.AddInMemoryTokenCache(services =>
     {
         // Configure the memory cache options
         services.Configure<MemoryCacheOptions>(options =>
         {
              options.SizeLimit = 500 * 1024 * 1024; // in bytes (500 MB)
         });
      });
  }
```

#### 分散キャッシュ

`app.AddDistributedTokenCache`を使用する場合、トークン キャッシュは.NET `IDistributedCache`実装に対するアダプターです。 そのため、SQL Server キャッシュ、Redis キャッシュ、Azure Cosmos DB キャッシュ、または [IDistributedCache](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.extensions.caching.distributed.idistributedcache?view=dotnet-plat-ext-6.0&preserve-view=true) インターフェイスを実装するその他のキャッシュのいずれかを選択できます。

テスト目的では、`services.AddDistributedMemoryCache()`のメモリ内実装である `IDistributedCache` を使用できます。

SQL Server キャッシュのコードを次に示します。

```csharp
     // SQL Server token cache
     app.AddDistributedTokenCache(services =>
     {
      services.AddDistributedSqlServerCache(options =>
      {
       
       // Requires to reference Microsoft.Extensions.Caching.SqlServer
       options.ConnectionString = @"Data Source=(localdb)\MSSQLLocalDB;Initial Catalog=TestCache;Integrated Security=True;Connect Timeout=30;Encrypt=False;TrustServerCertificate=False;ApplicationIntent=ReadWrite;MultiSubnetFailover=False";
       options.SchemaName = "dbo";
       options.TableName = "TestCache";

       // You don't want the SQL token cache to be purged before the access token has expired. Usually
       // access tokens expire after 1 hour (but this can be changed by token lifetime policies), whereas
       // the default sliding expiration for the distributed SQL database is 20 mins. 
       // Use a value above 60 mins (or the lifetime of a token in case of longer-lived tokens)
       options.DefaultSlidingExpiration = TimeSpan.FromMinutes(90);
      });
     });
```

Redis Cache のコードを次に示します。

```csharp
    // Redis token cache
    app.AddDistributedTokenCache(services =>
    {
      // Requires to reference Microsoft.Extensions.Caching.StackExchangeRedis
       services.AddStackExchangeRedisCache(options =>
       {
         options.Configuration = "localhost";
         options.InstanceName = "Redis";
       });

      // You can even decide if you want to repair the connection
      // with Redis and retry on Redis failures. 
      services.Configure<MsalDistributedTokenCacheAdapterOptions>(options => 
      {
        options.OnL2CacheFailure = (ex) =>
        {
          if (ex is StackExchange.Redis.RedisConnectionException)
          {
            // action: try to reconnect or something
            return true; //try to do the cache operation again
          }
          return false;
        };
      });
    });
```

Azure Cosmos DB キャッシュのコードを次に示します。

```csharp
      // Azure Cosmos DB token cache
      app.AddDistributedTokenCache(services =>
      {
        // Requires to reference Microsoft.Extensions.Caching.Cosmos
        services.AddCosmosCache((CosmosCacheOptions cacheOptions) =>
        {
          cacheOptions.ContainerName = Configuration["CosmosCacheContainer"];
          cacheOptions.DatabaseName = Configuration["CosmosCacheDatabase"];
          cacheOptions.ClientBuilder = new CosmosClientBuilder(Configuration["CosmosConnectionString"]);
          cacheOptions.CreateIfNotExists = true;
        });
       });
```

分散キャッシュの詳細については、以下を参照してください。

- [分散キャッシュ暗号化と高度なオプション](https://github.com/AzureAD/microsoft-identity-web/wiki/L1-Cache-in-Distributed-%28L2%29-Token-Cache)
- [L2 キャッシュの削除を処理する](https://github.com/AzureAD/microsoft-identity-web/wiki/Handle-L2-cache-eviction)
- [Docker で Redis Cache を設定する](https://github.com/AzureAD/microsoft-identity-web/wiki/Set-up-a-Redis-cache-in-Docker)
- [Troubleshooting](https://github.com/AzureAD/microsoft-identity-web/wiki/Token-Cache-Troubleshooting)

#### レガシ トークン キャッシュの無効化

MSAL には、レガシ Microsoft Authentication Library (ADAL) キャッシュとの対話を可能にするために特別に内部コードがいくつかあります。 MSAL と ADAL がサイド バイ サイドで使用されていない場合、レガシ キャッシュは使用されず、関連するレガシ コードは不要です。 MSAL [4.25.0 では](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/releases/tag/4.25.0) 、従来の ADAL キャッシュ コードを無効にし、キャッシュの使用パフォーマンスを向上させる機能が追加されました。 レガシ キャッシュを無効にする前と無効にした後のパフォーマンス比較については、[pull request 2309 GitHub](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/pull/2309)参照してください。

次のコードのように、アプリケーション ビルダーで `.WithLegacyCacheCompatibility(false)` を呼び出します。

```csharp
var app = ConfidentialClientApplicationBuilder.Create(clientId).WithClientSecret(clientSecret).WithLegacyCacheCompatibility(false).Build();
```

#### Samples

- 次のサンプルは ASP.NET Web アプリです: [OpenID Connect を使用してユーザーを Microsoft ID プラットフォームにサインインさせる](https://github.com/Azure-Samples/ms-identity-aspnet-webapp-openidconnect)。

## [デスクトップ アプリ](#tab/desktop)
デスクトップ アプリケーションでは、クロスプラットフォーム トークン キャッシュを使用することをお勧めします。 MSAL.NET は、[Microsoft.Identity.Client.Extensions.MSAL](https://www.nuget.org/packages/Microsoft.Identity.Client.Extensions.Msal) という名前の別個のライブラリで、クロスプラットフォームのトークン キャッシュを提供しています。

##### NuGet パッケージの参照設定

[Microsoft.Identity.Client.Extensions.Msal](https://www.nuget.org/packages/Microsoft.Identity.Client.Extensions.Msal/) NuGet パッケージをプロジェクトに追加します。

##### トークン キャッシュの構成

次の例は、クロスプラットフォーム トークン キャッシュを使用する方法を示しています。

```csharp
 var storageProperties =
     new StorageCreationPropertiesBuilder(Config.CacheFileName, Config.CacheDir)
     .WithLinuxKeyring(
         Config.LinuxKeyRingSchema,
         Config.LinuxKeyRingCollection,
         Config.LinuxKeyRingLabel,
         Config.LinuxKeyRingAttr1,
         Config.LinuxKeyRingAttr2)
     .WithMacKeyChain(
         Config.KeyChainServiceName,
         Config.KeyChainAccountName)
     .Build();

 IPublicClientApplication pca = PublicClientApplicationBuilder.Create(clientId)
    .WithAuthority(Config.Authority)
    .WithRedirectUri("http://localhost")  // make sure to register this redirect URI for the interactive login 
    .Build();

// This hooks up the cross-platform cache into MSAL
var cacheHelper = await MsalCacheHelper.CreateAsync(storageProperties );
cacheHelper.RegisterCache(pca.UserTokenCache);    
```

##### プレーンテキスト フォールバック モード

クロスプラットフォーム トークン キャッシュを使用すると、暗号化されていないトークンを ACL で制限されたプレーンテキスト ファイルに格納できます。 これは、保存データの暗号化が失敗する場合に役立ちます。こうした失敗は、環境要因により時折発生します。 プレーンテキスト フォールバック モードは、次のコード パターンを使用して使用できます。

```csharp
storageProperties =
    new StorageCreationPropertiesBuilder(
        Config.CacheFileName + ".plaintext",
        Config.CacheDir)
    .WithUnprotectedFile()
    .Build();

var cacheHelper = await MsalCacheHelper.CreateAsync(storageProperties).ConfigureAwait(false);
```

## [モバイル アプリ](#tab/mobile)
MSAL.NET は、既定でメモリ内トークン キャッシュを提供します。 MAUI モバイル ターゲットでは、シリアル化が既定で提供されます。

## [独自のキャッシュを書き込む](#tab/custom)
独自のトークン キャッシュ シリアライザーを記述する場合、MSAL.NET は、.NET Framework と .NET Core サブプラットフォームでカスタム トークン キャッシュのシリアル化を提供します。 キャッシュにアクセスされたときに、イベントが発火します。 アプリは、キャッシュをシリアル化または逆シリアル化するかどうかを選択できます。

ユーザーを処理する機密クライアント アプリケーション (ユーザーをサインインして Web API を呼び出す Web アプリ、ダウンストリーム Web API を呼び出す Web API) には、多くのユーザーが存在する可能性があります。 ユーザーは並行して処理されます。 セキュリティとパフォーマンス上の理由から、ユーザーごとに 1 つのキャッシュをシリアル化することをお勧めします。 シリアル化イベントは、処理されたユーザーの ID に基づいてキャッシュ キーを計算し、そのユーザーのトークン キャッシュをシリアル化または逆シリアル化します。

カスタム シリアル化はモバイル プラットフォームでは使用できないことに注意してください。 MSAL では、これらのプラットフォームのセキュリティで保護されたパフォーマンスの高いシリアル化メカニズムが既に定義されています。 ただし、.NETデスクトップ アプリケーションと .NET Core アプリケーションには、さまざまなアーキテクチャがあります。 また、MSAL は汎用シリアル化メカニズムを実装できません。

たとえば、Web サイトが Redis Cache にトークンを格納することを選択したり、デスクトップ アプリが暗号化されたファイルにトークンを格納したりする場合があります。 そのため、シリアル化はすぐには提供されません。 .NET デスクトップまたは .NET Core で永続的なトークン キャッシュを使用するには、シリアル化をカスタマイズします。

トークン キャッシュのシリアル化では、次のクラスとインターフェイスが使用されます。

- `ITokenCache` は、さまざまな形式 (MSAL 2.x および MSAL 3.x) でキャッシュをシリアル化または逆シリアル化するためのトークン キャッシュのシリアル化要求とメソッドをサブスクライブするイベントを定義します。
- `TokenCacheCallback` は、シリアル化を処理できるようにイベントに渡されるコールバックです。 これらは、 `TokenCacheNotificationArgs`型の引数を使用して呼び出されます。
- `TokenCacheNotificationArgs` は、アプリケーションの `ClientId` 値と、トークンが使用可能なユーザーへの参照のみを提供します。

[Image: トークン キャッシュのシリアル化のクラスを示す図。]

Important

MSAL.NET はトークン キャッシュを作成します。 アプリケーションの`IToken`と`UserTokenCache`のプロパティを呼び出すときに、`AppTokenCache` キャッシュを提供します。 インターフェイスを自分で実装することは想定されていません。

カスタム トークン キャッシュのシリアル化を実装する場合は、 `BeforeAccess` イベントと `AfterAccess` イベント (またはその `Async` の種類) に対応する必要があります。 `BeforeAccess` デリゲートはキャッシュの逆シリアル化を担当し、`AfterAccess`はキャッシュのシリアル化を担当します。 これらのイベントの一部は BLOB を格納または読み込みます。BLOB は、イベント引数を介して任意のストレージに渡されます。

戦略は、 [パブリック クライアント アプリケーション](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-client-applications) (デスクトップ) または [機密](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-client-applications) クライアント アプリケーション (Web アプリ、Web API、デーモン アプリ) のトークン キャッシュのシリアル化を記述するかどうかによって異なります。

#### Web アプリまたは Web API のカスタム トークン キャッシュ (機密クライアント アプリケーション)

機密クライアント アプリケーション用に独自のトークン キャッシュ シリアライザーを作成する場合は、[Microsoft.Identity.Web.MsalAbstractTokenCacheProvider](https://github.com/AzureAD/microsoft-identity-web/blob/master/src/Microsoft.Identity.Web.TokenCache/MsalAbstractTokenCacheProvider.cs) を継承し、`WriteCacheBytesAsync` メソッドと `ReadCacheBytesAsync` メソッドをオーバーライドすることをお勧めします。

トークン キャッシュ シリアライザーの例は、Microsoftで提供されています[。Identity.Web/TokenCacheProviders](https://github.com/AzureAD/microsoft-identity-web/blob/master/src/Microsoft.Identity.Web.TokenCache)。

#### デスクトップまたはモバイル アプリのカスタム トークン キャッシュ (パブリック クライアント アプリケーション)

v2.x 以降のバージョン MSAL.NET、パブリック クライアントのトークン キャッシュをシリアル化するためのオプションがいくつか用意されています。 キャッシュは、MSAL.NET 形式にのみシリアル化できます (統合形式のキャッシュは、MSAL とプラットフォーム間で共通です)。 ADAL.NET 3.x、ADAL.NET 5.x、および MSAL.NET の間でシングル サインオン状態を共有するようにトークン キャッシュのシリアル化をカスタマイズする方法については、[active-directory-dotnet-v1-to-v2](https://github.com/Azure-Samples/active-directory-dotnet-v1-to-v2) サンプルの一部で説明します。

##### 単純なトークン キャッシュのシリアル化 (MSAL のみ)

次のコードは、デスクトップ アプリケーション用のトークン キャッシュのカスタム シリアル化の単純な実装の例です。 ここでは、ユーザー トークン キャッシュは、アプリケーションと同じフォルダー内のファイルです。

アプリケーションをビルドしたら、 `TokenCacheHelper.EnableSerialization()` メソッドを呼び出し、アプリケーションの `UserTokenCache` プロパティを渡すことによってシリアル化を有効にします。

```csharp
app = PublicClientApplicationBuilder.Create(ClientId)
    .Build();
TokenCacheHelper.EnableSerialization(app.UserTokenCache);
```

`TokenCacheHelper` ヘルパー クラスは次のように定義されます。

```csharp
static class TokenCacheHelper
 {
  public static void EnableSerialization(ITokenCache tokenCache)
  {
   tokenCache.SetBeforeAccess(BeforeAccessNotification);
   tokenCache.SetAfterAccess(AfterAccessNotification);
  }

  /// <summary>
  /// Path to the token cache. Note that this could be something different, for instance, for MSIX applications:
  /// private static readonly string CacheFilePath =
  /// $"{Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData)}\{AppName}\msalcache.bin";
  /// </summary>
  public static readonly string CacheFilePath = System.Reflection.Assembly.GetExecutingAssembly().Location + ".msalcache.bin3";

  private static readonly object FileLock = new object();

  private static void BeforeAccessNotification(TokenCacheNotificationArgs args)
  {
   lock (FileLock)
   {
    args.TokenCache.DeserializeMsalV3(File.Exists(CacheFilePath)
            ? ProtectedData.Unprotect(File.ReadAllBytes(CacheFilePath),
                                      null,
                                      DataProtectionScope.CurrentUser)
            : null);
   }
  }

  private static void AfterAccessNotification(TokenCacheNotificationArgs args)
  {
   // if the access operation resulted in a cache update
   if (args.HasStateChanged)
   {
    lock (FileLock)
    {
     // reflect changes in the persistent store
     File.WriteAllBytes(CacheFilePath,
                         ProtectedData.Protect(args.TokenCache.SerializeMsalV3(),
                                                 null,
                                                 DataProtectionScope.CurrentUser)
                         );
    }
   }
  }
 }
```

パブリック クライアント アプリケーション向けの、製品品質のファイル ベースのトークン キャッシュ シリアライザーは、[Microsoft.Identity.Client.Extensions.Msal](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/tree/main/src/client/Microsoft.Identity.Client.Extensions.Msal) オープンソース ライブラリで利用できます（Windows、Mac、Linux で実行されるデスクトップ アプリケーション用）。 これは、次の NuGet パッケージからアプリケーションに含めることができます: [Microsoft。Identity.Client.Extensions.Msal](https://www.nuget.org/packages/Microsoft.Identity.Client.Extensions.Msal/)。

---

### キャッシュ ヒット率とキャッシュ パフォーマンスを監視する

MSAL は、 [AuthenticationResult.AuthenticationResultMetadata](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.authenticationresultmetadata) オブジェクトの一部として重要なメトリックを公開します。 これらのメトリックをログに記録して、アプリケーションの正常性を評価できます。

| Metric | Meaning | アラームをトリガーするタイミング |
| --- | --- | --- |
| `DurationTotalInMs` | ネットワーク呼び出しとキャッシュを含む、MSAL で費やされた合計時間。 | 全体的な待機時間が長い場合のアラーム (&gt; 1 秒)。 値はトークン ソースによって異なります。 キャッシュから: 1 つのキャッシュ アクセス。 Microsoft Entra IDから: 2 つのキャッシュ アクセスと 1 つの HTTP 呼び出し。 最初の呼び出し (プロセスごと) は、1 つの余分な HTTP 呼び出しのために時間がかかります。 |
| `DurationInCacheInMs` | トークン キャッシュの読み込みまたは保存に費やされた時間。これはアプリ開発者によってカスタマイズされます (たとえば、Redis に保存)。 | スパイク時のアラーム。 |
| `DurationInHttpInMs` | Microsoft Entra IDへの HTTP 呼び出しの作成に費やされた時間。 | スパイク時のアラーム。 |
| `TokenSource` | トークンのソース。 トークンはキャッシュからはるかに高速に取得されます (たとえば、約 100 ミリ秒と約 700 ミリ秒)。 キャッシュ ヒット率を監視およびアラームするために使用できます。 | `DurationTotalInMs` で使用します。 |
| `CacheRefreshReason` | ID プロバイダーからアクセストークンを取得する理由。 | `TokenSource` で使用します。 |

### サイズの近似値

トークン キャッシュを使用する場合は、キャッシュの潜在的なサイズ (特に高可用性および分散アプリケーションの場合) を考慮することが重要です。 ユーザーがログインすると、サイズが約 7 KB の各ユーザーのキャッシュ エントリが表示されます。 複数のダウンストリーム API を呼び出す場合、サイズは大きくなります。 サービス間認証の場合、各テナントとダウンストリーム API のキャッシュ エントリが約 2 KB のサイズになります。

詳細な見積もりを次に示します。

#### アプリケーション フロー (`AcquireTokenForClient`、 `AcquireTokenForManagedIdentity`)

- アクセス トークンのみがキャッシュされます。 永続化した場合、1トークンあたり約2～3KB。 *アプリ クライアント ID* \* テナント \* ダウンストリーム リソースごとに 1 つのトークンがあります。 たとえば、1000 テナントにサービスを提供し、Graph と SharePoint のトークンを必要とするマルチテナント アプリでは、3 KB \* 1000 \* 2 つまり約 6 MB が使用されます。

#### ダウンストリーム Web API を呼び出す Web サイト (`AcquireTokenByAuthCode`)

- **アクセス トークン** – 4 KB; *アプリ クライアント ID* \* ユーザー \* テナント \* ダウンストリーム リソースごとに 1 つのトークン。
- **更新トークン** – 2 KB; *クライアント アプリ ID* \* ユーザーごとに 1 つのトークン。
- **ID トークン** – 2 KB; *クライアント アプリ ID* あたり 1 トークン \* ユーザー \* そのユーザーがログインするテナントの数。

Note

この用途では、MSAL を直接使用するのではなく、[`Microsoft.Identity.Web`](https://www.nuget.org/packages/Microsoft.Identity.Web/) で提供される、より高レベルの API を使用することを強くお勧めします。 キャッシュに関する考慮事項は同じです。

#### 他の Web API を呼び出す Web API (`AcquireTokenOnBehalfOf`)

Web サイトのシナリオと同じですが、ユーザーごとにではなく、セッションごとに 1 つのノードが存在します。 既定では、MSAL はアップストリーム アサーションをハッシュすることによってセッションを識別しますが、これは変更できます。 [実行時間の長い OBO プロセス](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/on-behalf-of-flow#long-running-obo-processes)を参照してください。

Note

この目的には、MSAL を直接使用するのではなく、[`Microsoft.Identity.Web`](https://www.nuget.org/packages/Microsoft.Identity.Web/) の高レベル API を使用することを強くお勧めします。 キャッシュに関する考慮事項は同じです。

### トークン キャッシュの種類

MSAL.NET は、**ユーザー**と**アプリケーション**の 2 種類のトークン キャッシュで動作します。

この **アプリケーション** のアクセス トークンを保持するアプリケーション トークン キャッシュ。 [AcquireTokenForClient](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.confidentialclientapplication.acquiretokenforclient#microsoft-identity-client-confidentialclientapplication-acquiretokenforclient%28system-collections-generic-ienumerable%28%28system-string%29%29%29) を呼び出すときは、サイレントモードで維持および更新されます。

**ユーザー トークン キャッシュ**には、MSAL.NET がやり取りするアカウントの ID トークン、アクセス トークン、および更新トークンが保持されます。 [AcquireTokenSilent](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.clientapplicationbase.acquiretokensilent#microsoft-identity-client-clientapplicationbase-acquiretokensilent%28system-collections-generic-ienumerable%28%28system-string%29%29-microsoft-identity-client-iaccount%29) を呼び出すときに必要に応じて、サイレントモードで使用および更新されます。 これは、アプリケーション キャッシュのみを使用する [AcquireTokenForClient](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.confidentialclientapplication.acquiretokenforclient#microsoft-identity-client-confidentialclientapplication-acquiretokenforclient%28system-collections-generic-ienumerable%28%28system-string%29%29%29) を除き、各トークン取得メソッドによって更新されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/resources/handling-pii"} -->
## MSAL.NET における個人を特定できる情報の取り扱い - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/resources/handling-pii
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: MSAL が個人を特定できる情報と見なす内容について説明します。

### データの分類

Microsoftは、次の[データ分類](https://www.microsoft.com/trust-center/privacy/customer-data-definitions)を定義します。 MSAL ライブラリは、わかりやすくするために、ログ記録用の 1 つの PII (個人を特定できる情報) 有効化フラグを公開します。 この単一フラグは、データ分類ドキュメントの対象となるすべてのカテゴリを結合します。

### ログ記録の方法

MSAL.NET がログを記録する方法の詳細については、「[MSAL.NET のログ記録](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/msal-logging)」を参照してください。 具体的には、個人を特定できる情報 (PII) を含むデータをログに記録するには、[WithLogging(IIdentityLogger, Boolean)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.baseabstractapplicationbuilder-1.withlogging#microsoft-identity-client-baseabstractapplicationbuilder-1-withlogging%28microsoft-identitymodel-abstractions-iidentitylogger-system-boolean%29)を使用するときに`enablePiiLogging` フラグを使用する必要があります。

Note

`enablePiiLogging`を使用すると、MSAL 例外メッセージに表示される PII に影響します。[これには、Web アカウント マネージャー (WAM) の使用](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam)に起因するものも含まれます。 また、UPN、名前、電子メールなど、エンド ユーザーを特定できる情報 (EUII) についても説明します。

### MSAL がログに記録しない内容

- アクセス トークン、ID トークン、更新トークン、MSAL によって生成されたクライアント アサーションを含むトークン。
- MSAL はユーザー名とパスワードフローの間にのみパスワードを与えられるので、パスワード。 MSAL は、ユーザーがブラウザーで入力したパスワードにアクセスできません。
- 承認コード。
- PKCE コード。
- トークンまたは認証コードが含まれている可能性があるため、 `/authorize` または `/token` エンドポイントからのネットワーク応答が成功しました。
- ネットワーク要求にはパスワードが含まれている可能性があるためです。
- 証明書の秘密キー。

### MSAL が PII と見なす内容

- ユーザー名。
- ログイン ヒント。
- ID トークン要求。名前、アドレス、またはその他のユーザーの詳細が含まれます。 MSAL では ID トークンのみが解析され、アクセス トークンや更新トークンは検索されません。
- ログイン ヒントが含まれている可能性があるため、承認 URI。
- オブジェクト ID (つまり、要求 `oid` )。

### MSAL が PII と見なさないもの

- テナント ID、ディレクトリ ID、ディレクトリ名 (例: `contoso.onmicrosoft.com`) など、組織またはテナント (ユーザーではない) に関連する ID。
- 権限。
- スコープとリソース名。
- クライアント (アプリケーション) ID。
- オブジェクト ID やクライアント ID などのサービス プリンシパルの詳細。
- 例外メッセージとスタック トレース (Microsoft Entra IDからのエラー コードを含む)。
- 要求と応答以外の HTTP の詳細 (HTTP 状態コードやペイロード サイズなど)。
- 相関 ID。
- OS 名、.NET プラットフォーム バージョンなどのランタイムの詳細。
- クラス名、メソッド名などの内部 API の詳細。
- アルゴリズム名 (RSA など) や OIDC 定数などの要求の詳細。
- キー ID 以外の証明書の拇印。

### 例外での PII

MSAL は、PII を含まない例外メッセージを生成します。 [MsalException](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalexception)MSAL によって生成されるか、Microsoft Entra IDから渡されたインスタンスには PII が含まれていないと見なされます。

一部のフレームワーク例外には PII が含まれている場合がありますが、これはまれです (たとえば、 `PathInvalidException` にユーザー名が含まれている場合があります)。 MSAL は、PII を含む可能性があるフレームワーク例外をログに記録しないように注意します。

### 組織を特定できる情報

MSAL では、組織を特定できる情報 (OII) をログに記録できます。これは、公式のデータ分類に従って、組織を特定できる情報は PII とは見なされないためです。 OII には、テナント ID、サービス プリンシパルのオブジェクト ID、スコープ名などのデータが含まれます。 アプリケーション開発者は、このログ データの宛先を引き続き制御します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/resources/known-issues"} -->
## MSAL.NET に関する既知の問題 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/resources/known-issues
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: デバイスコンプライアンスエラー、AndroidActivityNotFound 例外、ビルドの問題など、既知の問題に関するガイドで MSAL.NET のトラブルシューティングを行います。

MSAL は、いくつかの種類の例外をスローします。 [例外](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/)を参照してください。

### Confidential Client

[高可用性](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/high-availability)に関するガイドをお読みください。

### パブリック クライアント

#### Windows 10でのデバイス コンプライアンスエラー

ユーザーは対話形式でログインできず、次の場合に "デバイスが準拠していません" というエラーが表示されます。

- テナント管理者が"デバイスを準拠としてマークする必要がある" 条件付きアクセス ポリシーを有効にしました
- アプリがパブリック クライアント フローを呼び出している (つまり、Web サイトではなくリッチ クライアント アプリ)
- アプリは、ADAL または MSAL で使用できる埋め込みブラウザー コントロールを使用しています (これは、.NET Framework アプリの既定値です)

##### 緩和策

- 推奨される方法は、 [WAM](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam) を使用することです。
- システム (既定の OS) ブラウザーを使用するように MSAL を構成することもできます。 詳細については、「[Web ブラウザーの使用 (MSAL.NET)](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-web-browsers#how-to-use-the-default-os-browser)」を参照してください。 Microsoft Edgeブラウザーと Chrome ブラウザーの両方が、デバイス ポリシーを満たすことができる。
- ADAL を使用している場合は、 [**MSAL に移行します**](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-migration)。 ADAL の軽減策はありません。

#### Android

Android では、デバイスにタブ付きのブラウザーがない場合、 `AndroidActivityNotFound` 例外がスローされます。 [MSAL.NET の使用Xamarin Android システム ブラウザーに関する考慮事項を](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-system-browser-android-considerations#known-issues)参照してください

#### iOS

[iOS に関する考慮事項Xamarin](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-xamarin-ios-considerations#known-issues-with-ios-12-and-authentication)参照してください。

#### デスクトップ

デスクトップ アプリでは、埋め込みブラウザーと組み合わせて長い Facebook ID (B2C 経由) を使用すると、 `StateMismatchError` 例外がスローされます。 詳細については、 [ドキュメントを参照](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/understanding-statemismatcherror)してください。

### ビルドの問題

動作: `Microsoft.Windows.SDK.Contracts.targets(4,5): error : Must use PackageReference` のようなエラーがスローされます

バージョン 4.23 以降では、MSAL 参照 `Microsoft.Windows.SDK.Contracts`。 NuGet でこの参照を解決できるのは、MSAL を使用するアプリケーションが、レガシ `packages.config` メカニズムではなく、`<PackageReference>`として参照している場合のみです。 これを修正する方法の詳細については、 [#2247](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/issues/2247) を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/resources/region-discovery-troubleshooting"} -->
## リージョン検出のトラブルシューティング - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/resources/region-discovery-troubleshooting
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: Microsoft Entra IDのリージョン STS (ESTS-Regional) のトラブルシューティングを行います。 ファースト パーティ アプリのサービス間フローとオプトイン プロセスについて説明します。

Microsoft Entra IDには、リージョン STS (ESTS-Regional) のサポートが追加されています。 現在、ファースト パーティ アプリに対してのみオプトインを使用して使用できるのは、サービス間フロー (client\_credentials/ AcquireTokenForClient) のみです。

詳細については [、内部ガイダンス](https://aka.ms/msal/estsr/guidance)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/resources/semantic-versioning-api-change-management"} -->
## セマンティック バージョン管理と API 変更管理 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/resources/semantic-versioning-api-change-management
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: ライブラリ リリースのバージョン管理に関する MSAL.NET 戦略

MSAL.NET では、オープンソース プロジェクトの業界標準である[セマンティック バージョン管理](https://semver.org/)が採用されています。

セマンティック バージョン管理の仕様に従って、重大な変更 (互換性のない変更) はメジャー バージョンのバンプでのみリリースされます。 その場合は、 [リリース ノート](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/releases)でその変更を文書化します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/resources/telemetry-overview"} -->
## MSAL.NET テレメトリの概要 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/resources/telemetry-overview
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: Microsoft Entra トークン エンドポイント要求に対する MSAL.NET のテレメトリ機能について説明します。 クライアント側の状態、エラー追跡、SDK API の使用メタデータについて説明します。

MSAL.NET は、要求時のクライアント側の状態に関する基本的なテレメトリをMicrosoft Entra トークン エンドポイントに送信します。 テレメトリ データは、Microsoft Entra IDによってログに記録されます。 このテレメトリは、オープンソース SDK に追加のテレメトリ パイプライン依存関係を導入することなく、ファースト パーティとサード パーティの両方のアプリの正常性を可視化します。

MSAL.NET は、このテレメトリを収集して、より優れたサービスを提供するために、サーバー側の障害またはライブラリの回帰を事前に検出します。

基本的なライブラリ テレメトリには、次のものが含まれます。

- 要求時のクライアント側の状態。 クライアント アプリの要求プロンプト、キャッシュされたトークンがない、アクセスの期限切れなど、要求の実行の理由が表示されます。
- 失敗した前の要求のエラー。
- 要求に使用された API やパラメーターなど、SDK API の使用メタデータ。

Important

個人を特定できる情報 (PII) または組織を特定できる情報 (OII) の処理方法の詳細については、「[MSAL.NET での個人を特定できる情報の取り扱い](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/resources/handling-pii)」を参照してください。

### データ

トークン エンドポイントに対する MSAL 要求には、2 つの追加ヘッダーがあります。

- 現在の要求ヘッダー: `x-client-current-telemetry`
    - 現在の要求には、現在のパブリック API 要求に関する情報が含まれます。
- 最後の要求ヘッダー: `x-client-last-telemetry`
    - 最後の要求には、以前の要求の失敗に関する情報が含まれています。

現在の要求と最後の要求は、トークン エンドポイントの呼び出しに追加されます。

#### 現在の要求の例

現在の要求は、サーバー側の問題やライブラリの回帰を可能な限りお客様にほとんど影響を与えない状態で事前に検出するのに役立つテレメトリで使用されます。 現在の要求ヘッダー形式の例を [次に示します](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/blob/3d9cb46d824820a580b7f826a71ecd5beb8131a8/src/client/Microsoft.Identity.Client/TelemetryCore/Http/HttpTelemetryManager.cs#L108)。

#### 最後の要求の例

失敗した要求は、サーバー側の問題やライブラリの回帰を可能な限りお客様にほとんど影響を与えない状態で事前に検出するために、テレメトリで使用されます。 最後の要求ヘッダー形式の例を [次に示します](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/blob/3d9cb46d824820a580b7f826a71ecd5beb8131a8/src/client/Microsoft.Identity.Client/TelemetryCore/Http/HttpTelemetryManager.cs#L51)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/resources/troubleshooting"} -->
## MSAL.NET のトラブルシューティング - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/resources/troubleshooting
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: 包括的なガイドで MSAL.NET の問題のトラブルシューティングを行います。 Microsoftの公式サイトで、JavaScript エラー、リージョンの自動検出エラーなどを修正する方法について説明します。

WAM の使用に関する問題については、「 [Web アカウント マネージャー」を](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam)参照してください。

### 埋め込みブラウザーでの JavaScript エラー

#### 症状:

認証に使用されるウィンドウでブラウザーを起動するデスクトップ アプリケーションを使用しています。

また、次のいずれかの問題があります。

- Javascript エラーが発生し、ナビゲーションがブロックされます。
- を示すメッセージ `browser is not up-to-date or is not supported`

#### 考えられる原因

WebView1 (WebBrowser) コントロールは、Windows上の MSAL によって使用されます。 既定では、IE7 に相当する非常に古いエンジンが使用されます。これは、特に独自の ADFS サーバーまたはカスタム MFA プロバイダーを使用している場合に中断されます。

#### 長期的な修正

WIN 10 以降と Win Server 2019 以降で使用できる [WAM](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam) を使用してログインに移動します。

#### 対処法

上記が不可能な場合、これを処理する別の方法は、埋め込みブラウザーに新しいバージョンの IE を強制的に適用することです。 レジストリ プロパティがあり、内部ブラウザーで IE11 エミュレーションを有効にします。

これは `ssms.exe`用ですが、アプリに置き換えることができます。

```powershell
New-ItemProperty -path 'HKLM:\SOFTWARE\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BROWSER_EMULATION' -name 'Ssms.exe' -value '11000' -PropertyType 'DWord'
```

あなたは同様に追加された Powershell.exe/Powershell\_ISE.exe のためにそれをしなければならないかもしれません

### リージョンの自動検出エラー

#### 症状:

リージョン エンドポイントではなく、グローバル ESTS エンドポイントにヒットします。

#### 考えられる原因

1. アプリがデプロイされるAzure環境は、リージョンの検出をサポートしていません (たとえば、IMDS サービスが使用できない、REGION\_NAME変数Azureチームによって設定されていません)。
2. IMDS 呼び出しはタイムアウトまたは失敗します。 現在、応答を最大 2 秒間待機するように設定されています。

#### 問題をデバッグする方法

自動検出は、トークンを取得する最初の呼び出しの前に 1 回だけ発生します。 パフォーマンス上の理由から、結果はメモリに格納され、自動検出は再試行されません。 そのため、自動検出が失敗する理由を理解するには、アプリケーションの起動時にログをキャプチャする必要があります。

1. ヒットしたエンドポイントを理解するには、 `AuthenticationResult.AuthenticationResultMetadata.TokenEndpoint` と `AuthenticationResult.AuthenticationResultMetadata.RegionDetails`を監視します。 リージョン化されたエンドポイントは、 `<region>.login.microsoft.com/<tenant>/oauth2/v2.0/token`形式です。
2. MSAL に詳細ログを追加します。 ログの詳細については、 [ドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/msal-logging)。 パフォーマンスに影響を与えるので、運用環境で長時間詳細ログを実行しないでください。
3. サービスを再起動します。

サービスを再起動し、ログをキャプチャします。 `AuthenticationResult.AuthenticationResultMetadata.RegionDetails`を調べて、自動検出に失敗したかどうかを確認します。 MSAL チームにログを送信します。

### GetAccountAsync はクラウド シナリオでアカウントを返しません

#### 症状:

- `AcquireTokenX().WithAuthority()`を使用してソブリン クラウドのトークンを取得するアプリケーションを構築しています。
- 特定のアカウント ID のアカウントを取得しようとすると、アプリケーションのメソッド `GetAccountAsync` は null を返します。

#### ソリューション

アプリケーションをビルドするときは、テナントがまだわからない場合でも、トークンを取得する機関と同じクラウドの機関を必ず使用してください。

#### 説明

`ConfidentialClientApplicaiton` オブジェクトを作成するときに、権限を指定しない場合、既定では `https://login.microsoftonline.com/common` になります。 その後、要求レベルでこれをオーバーライドします。 これは、トークンを取得する場合に適しています。 問題は、 `app.GetAccountsAsync()`を呼び出すときに、MSAL は環境 (パブリック クラウド、Fairfax など) でフィルター処理する必要がありますが、この情報がないため、既定の (パブリック クラウド) を使用するためです。 したがって、すべてのアカウントが Fairfax であるため、 `GetAccountsAsync()` は 0 個のアカウントを返します。

これを解決するには、テナントがわからない場合でも (たとえば、`common`を使用する)、`ConfidentialClientApplicationBuilder`に`WithAuthority`を追加するだけです。これは、要求レベルでオーバーライドするためです。 ただし、アプリ オブジェクトは適切なクラウドに関連付ける必要があります。

### 証明書にアクセスするときに Null ポインター例外がスローされる

#### 症状:

指定された証明書を MSAL が使用しようとすると、Null ポインター例外がスローされます。

#### ソリューション

コードで、MSAL に提供される証明書インスタンスが途中で破棄されていないことを確認します。 これは、証明書インスタンスが `using` ブロック内で使用され、スコープ外になると破棄されるときに発生する可能性があります。

### `Keyset does not exist` 例外

#### 症状:

MSAL が指定された証明書を使用しようとすると、 `Keyset does not exist` 暗号化例外がスローされます。

#### ソリューション

考えられる解決策の 1 つは、指定された証明書に秘密キーがあり、それを表示するための適切なアクセス許可が実行中のアプリケーションに与えられていることを確認することです。

もう 1 つのシナリオは、アプリが App Service または Azure Function インスタンスにデプロイされ、証明書がローテーションされる場合です。 App Service の既定の動作では、キー コンテナーと共に証明書ストアから古い証明書を削除し、新しい証明書をインストールします。 アプリが古い証明書インスタンスをキャッシュしている場合は、ローテーションのために古い証明書が削除されても、コードでキャッシュが使用されている場合があります。 そのため、キャッシュされた証明書が削除されたキー コンテナーにアクセスしようとすると、"Keyset not found" エラーが発生します。

アプリ設定 `WEBSITE_RECYCLE_ON_CERT_ROTATION` を `1` に設定します。これにより、証明書のローテーション後にワーカー プロセスが確実にリサイクルされます。 上記のアプリ設定を設定しない場合、または何らかの理由で (リサイクルが可用性に影響する重いアプリでのリサイクルを最小限に抑える必要がある場合など) は、アプリ設定 `WEBSITE_DELAY_CERT_DELETION` を使用して `1`できます。 この設定を使用して、証明書を検索するときに適切な証明書を選択する必要があります (たとえば、有効期限が最新の証明書を探すなど)。

この設定が存在する場合、証明書のローテーション時に、ローテーションの前に開始されたワーカー プロセス (および古い証明書へのハンドルがある可能性があります) が終了するまで、古い証明書を保持します。 この設定では、緊急証明書のローテーション シナリオ (アプリで古い証明書が侵害される可能性があるため、できるだけ早く使用を停止する) 場合は、ワーカー プロセスのリサイクルを強制する必要があることに注意してください (これを行う最も簡単な方法は、サイト再起動 API またはポータルの再起動ボタンを使用することです)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/go"} -->
## Go のMicrosoft Authentication Library (MSAL) - Microsoft Authentication Library for Go

- Source: https://learn.microsoft.com/ja-jp/entra/msal/go
- Service: entra-id
- Article date: 2025-02-11
- Summary: Go のMicrosoft Authentication Library (MSAL) の使用の概要。

Note

Go 用Microsoft Authentication Library (MSAL) は、MSAL ライブラリ ファミリに新たに追加されています。 お客様の関心を評価し、コミュニティからフィードバックを収集するために、運用準備完了プレビューで利用できるようになりました。 ライブラリの改善に役立つすべての共同作成者 ( [ライブラリ リポジトリの投稿ガイドライン](https://github.com/AzureAD/microsoft-authentication-library-for-go/blob/main/CONTRIBUTING.md)を参照) を歓迎します。

Go のMicrosoft Authentication Library (MSAL) は[、開発者向け Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview)の一部です。 これにより、Microsoft ID ([Azure AD](https://azure.microsoft.com/services/active-directory/) と [Microsoft アカウント](https://account.microsoft.com)) を使用してユーザーまたはアプリにサインインし、Microsoft Graphや[Microsoft ID プラットフォーム](https://graph.microsoft.io/)に登録されている独自の API などの API を呼び出すトークンを取得できます。 業界標準の OAuth2 プロトコルと OpenID Connect プロトコルを使用して構築されています。

最新のコードは、[ライブラリ GitHub リポジトリの `dev`](https://github.com/AzureAD/microsoft-authentication-library-for-go) ブランチにあります。

### Installation

#### Go の設定

Go をインストールするには、 [このリンク](https://golang.org/dl/)を参照してください。

#### MSAL Go のインストール

```bash
go get -u github.com/AzureAD/microsoft-authentication-library-for-go/
```

### 使用方法

MSAL Go を使用する前に、[アプリケーションをMicrosoft ID プラットフォームに登録する必要があります](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/quickstart-v2-register-an-app)。

#### パブリック サーフェス

ライブラリのパブリック API は、次の `apps` の下のディレクトリにあります。

- `confidential` - 機密アプリケーション API
- `public` - パブリック アプリケーション API
- `cache` - 資格情報の永続化キャッシュ ストレージを提供するために実装できるキャッシュ インターフェイス
- `managedidentity` - マネージド ID API

MSAL Go を使用してトークンを取得するには、この一般的な 3 つのステップ パターンに従います。 他のトークン取得フローには若干の違いがある可能性があります。 基本的な例を次に示します。

1. MSAL は [、パブリック クライアント アプリケーションと機密クライアント アプリケーションを分離します](https://tools.ietf.org/html/rfc6749#section-2.1)。 そのため、 `PublicClientApplication` と `ConfidentialClientApplication` のインスタンスを作成し、アプリケーションの有効期間を通じてこれを使用します。

    - パブリック クライアントの初期化:

    ```go
    publicClientApp, err := public.New("client_id", public.WithAuthority("https://login.microsoftonline.com/Enter_The_Tenant_Name_Here"))
    ```

    - 機密クライアントの初期化:

    ```go
    // Initializing the client credential
    cred, err := confidential.NewCredFromSecret("client_secret")
    if err != nil {
        return nil, fmt.Errorf("could not create a cred from a secret: %w", err)
    }
    confidentialClientApp, err := confidential.New("client_id", cred, confidential.WithAuthority("https://login.microsoftonline.com/Enter_The_Tenant_Name_Here"))
    ```

    マネージド ID アプリケーションを使用する場合は、[マネージド ID](https://learn.microsoft.com/ja-jp/entra/msal/go/advanced/managed-identity) を参照してください
2. MSAL にはメモリ内キャッシュがパッケージ化されています。 キャッシュの利用は省略可能ですが、強くお勧めします。

    ```go
    var userAccount public.Account
    accounts := publicClientApp.Accounts()
    if len(accounts) > 0 {
        // Assuming the user wanted the first account
        userAccount = accounts[0]
        // found a cached account, now see if an applicable token has been cached
        result, err := publicClientApp.AcquireTokenSilent(context.Background(), []string{"your_scope"}, public.WithSilentAccount(userAccount))
        accessToken := result.AccessToken
    }
    ```

    キャッシュに適切なトークンがない場合、またはこの手順をスキップすることを選択した場合は、トークンを取得する要求Azure AD に送信します。 トークンを取得する方法は、アプリケーションの種類とシナリオに基づいて異なります。 ここでは、プレースホルダー フローを示します。

    ```go
    result, err := publicClientApp.AcquireTokenByOneofTheActualMethods([]string{"your_scope"}, ...(other parameters depending on the function))
    if err != nil {
        log.Fatal(err)
    }
    accessToken := result.AccessToken
    ```

さまざまなシナリオのさまざまなアプリケーションの種類で MSAL Go を使用する方法に関する [開発者サンプル アプリ](https://github.com/AzureAD/microsoft-authentication-library-for-go/tree/main/apps/tests/devapps) を表示できます。

### リリース

ライブラリ リリースの完全な一覧については、ライブラリ のソース コード リポジトリの「 [リリース](https://github.com/AzureAD/microsoft-authentication-library-for-go/releases) 」セクションを参照してください。

### コミュニティのヘルプとサポート

[Stack Overflow](https://stackoverflow.com/questions/tagged/azure-ad-msal) を使用して、この SDK を含むAzure Active Directoryとその SDK のサポートについてコミュニティと連携します。 Stack Overflow について質問することを強くお勧めします。 また、既存の質問を参照して、以前に問題が発生した人がいるかどうかを確認することもできます。 質問するときは、 `azure-ad-msal` タグを使用してください。

バグが見つかるか、機能要求がある場合は、[ [問題](https://github.com/AzureAD/microsoft-authentication-library-for-go/issues) ] セクションで新しい問題を開いてください。

### フィードバックを送信

ライブラリに関するフィードバックがある場合は、GitHubに関する機能要求と[バグ レポートを](https://github.com/AzureAD/microsoft-authentication-library-for-go/issues)送信してください。

### セキュリティ ライブラリ

このライブラリは、ユーザーがサービスにサインインしてアクセスする方法を制御します。 可能であれば、アプリで最新バージョンのライブラリを使用することをお勧めします。 [セマンティック バージョン管理を](http://semver.org/)使用するため、アプリの更新に関連するリスクを制御できます。 たとえば、常に最新のマイナー バージョン番号 (x.y.x など) をダウンロードすると、最新のセキュリティと機能の強化が保証されますが、API サーフェスは変わりません。 最新バージョンとリリース ノートは、GitHubの [\[リリース\] タブで](https://github.com/AzureAD/microsoft-authentication-library-for-go/releases)いつでも確認できます。

### セキュリティ レポート

ライブラリまたはサービスに関するセキュリティの問題が見つかる場合は、できるだけ詳細に secure@microsoft.com するように報告してください。 提出物は、Microsoft 報奨金プログラムを通じて [報奨金](https://aka.ms/bugbounty) の対象となる場合があります。 GitHubの問題やその他のパブリック サイトにセキュリティの問題を投稿しないでください。 情報をお受け取り次第、お客様に連絡いたします。 [このページ](https://www.microsoft.com/msrc/technical-security-notifications)にアクセスし、セキュリティ アドバイザリ アラートをサブスクライブすることで、セキュリティ インシデントが発生した場合の通知を受け取ることをお勧めします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/go/advanced/managed-identity"} -->
## MSAL GO でのマネージド ID - Microsoft Authentication Library for Go

- Source: https://learn.microsoft.com/ja-jp/entra/msal/go/advanced/managed-identity
- Service: entra-id
- Article date: 2025-02-11
- Summary: MSAL GO アプリケーションでAzureマネージド ID を使用する方法。

Note

この機能は、MSAL for Go バージョン [`1.3.1`](https://github.com/AzureAD/microsoft-authentication-library-for-go/releases/tag/v1.3.1)以降で使用できます。

開発者にとって一般的な課題は、サービス間の通信をセキュリティで保護するために使用されるシークレット、資格情報、証明書、およびキーの管理です。 Azureの[マネージド ID](https://learn.microsoft.com/ja-jp/azure/active-directory/managed-identities-azure-resources/overview) により、開発者がこれらの資格情報を手動で処理する必要がなくなります。 MSAL for Go では、次のようなAzureインフラストラクチャ内で実行されているアプリケーションで使用する場合、マネージド ID サービスを介したトークンの取得がサポートされます。

- [Azure VM](https://azure.microsoft.com/free/virtual-machines/)
- [Azure Arc](https://learn.microsoft.com/ja-jp/azure/azure-arc/overview)

完全な一覧については、[マネージド ID を使用して他のサービスにアクセスできる](https://learn.microsoft.com/ja-jp/azure/active-directory/managed-identities-azure-resources/managed-identities-status)サービスAzureを参照してください。

### どの SDK を使用するか - Azure SDK または MSAL?

MSAL ライブラリは、OAuth2 プロトコルと OIDC プロトコルに近い下位レベルの API を提供します。

MSAL for Go と [Azure SDK for Go](https://learn.microsoft.com/ja-jp/azure/developer/go/) の両方で、マネージド ID を使用してトークンを取得できます。 内部的には、Azure SDKは GO 用の MSAL を使用し、`DefaultAzureCredential`と`ManagedIdentityCredential`抽象化を介して上位レベルの API を提供します。

アプリケーションで前述の SDK のいずれかを既に使用している場合は、引き続き同じ SDK を使用します。 新しいアプリケーションを作成し、他のAzure リソースを呼び出す予定の場合は、Azure SDKを使用します。 Azure SDKでは、アプリがローカル対応 API (`DefaultAzureCredential` など) を使用できるようにすることで、マネージド ID が存在しないマシンでのテストを有効にすることで、開発者エクスペリエンスが簡単になります。 Microsoft Graphや独自の Web API などの他のダウンストリーム Web API を呼び出す必要がある場合は、MSAL の使用を検討してください。

### マネージド ID の使用方法

**システム割り当てとユーザー割り当て**の 2 種類のマネージド ID を開発者が使用できます。 違いの詳細については、 [マネージド ID の種類](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types) に関する記事を参照してください。 MSAL for Go では、両方のトークンの取得がサポートされています。 それぞれの簡単な概要は次のとおりです。

**システム割り当て** - Azureによって作成および管理され、リソースのライフサイクルに関連付けられます。 リソースが削除されると、システム割り当て ID も削除されます**ユーザー割り当て** - Azureのスタンドアロン リソースとして作成されます。 特定のリソースに関連付けされていません。 複数のリソースに割り当て、個別に管理できます。 同じ ID とアクセス許可を共有する複数のリソースが必要な場合に便利です

MSAL for Go のマネージド ID を使用する前に、開発者は、Azure CLIまたはAzure portalで使用するリソースに対して有効にする必要があります。

### Azure リソースの作成

Azure portalを使用してサンプルを手動で実行するために必要なリソースを作成したり、[Azure CLI](https://portal.azure.com/#home)または powershell Azureを使用してサンプルを実行する方法を簡単かつ簡潔に説明するために必要なリソースを作成するには、「[マネージド ID を使用した Go のAzure SDKを使用した認証](https://learn.microsoft.com/ja-jp/azure/developer/go/azure-sdk-authentication-managed-identity?tabs=azure-cli)」の手順に従います。

### 簡単スタート

マネージド ID の動作をすばやく開始して確認するには、次のいずれかのサンプルを使用できます。

>
> [マネージド ID の使用のサンプル](https://github.com/Azure-Samples/msal-managed-identity/tree/main/src/go)

### 例示

ユーザー割り当て ID とシステム割り当て ID の両方について、開発者は [`managedidentity.go`](https://github.com/AzureAD/microsoft-authentication-library-for-go/blob/c5febcbae287a26a0cfedd45f4edeaf3c41ad7dc/apps/managedidentity/managedidentity.go#L107) で `New` 関数を使用でき

#### システム割り当てのマネージド ID

システム割り当てマネージド ID の場合は、`SystemAssigned()`関数に`New`を渡します

```go
mi.New(mi.SystemAssigned())
```

[AcquireToken](https://github.com/AzureAD/microsoft-authentication-library-for-go/blob/c5febcbae287a26a0cfedd45f4edeaf3c41ad7dc/apps/managedidentity/managedidentity.go#L216) は、任意のオプションと共に、 `https://management.azure.com`などのトークンを取得するリソースをコンテキストと共に呼び出します。

```go
miClient, err := mi.New(mi.SystemAssigned())
if err != nil {
    log.Fatalf("failed to create a new managed identity client: %v", err)
    return
}

accessToken, err := miClient.AcquireToken(context.Background(), "https://vault.azure.net")
if err != nil {
    log.Fatalf("failed to acquire token: %v", err)
    return
}
```

#### ユーザー割り当て済みマネージド ID

ユーザー割り当てマネージド ID の場合、開発者は、 [`New`](https://github.com/AzureAD/microsoft-authentication-library-for-go/blob/c5febcbae287a26a0cfedd45f4edeaf3c41ad7dc/apps/managedidentity/managedidentity.go#L107)を作成するときに、クライアント ID、完全なリソース識別子、またはマネージド ID のオブジェクト ID を渡す必要があります。

システム割り当てマネージド ID と同様に、 [`AcquireToken`](https://github.com/AzureAD/microsoft-authentication-library-for-go/blob/c5febcbae287a26a0cfedd45f4edeaf3c41ad7dc/apps/managedidentity/managedidentity.go#L216) はリソースと共に呼び出され、 `https://management.azure.com`などのトークンを取得します。

```go
miClient, err := mi.New(mi.UserAssignedClientID("my-client-id"))
miClient, err := mi.New(mi.UserAssignedObjectID("my-object-id"))
miClient, err := mi.New(mi.UserAssignedResourceID("my-resource-id"))

accessToken, err := miClient.AcquireToken(context.Background(), "https://vault.azure.net")
if err != nil {
    log.Fatalf("failed to acquire token: %v", err)
    return
}
```

### Caching

既定では、MSAL for Go はメモリ内キャッシュをサポートしています。MSAL では、分散キャッシュを使用する際のセキュリティ上の問題により、マネージド ID のキャッシュ拡張機能はサポートされません。 マネージド ID に対して取得されたトークンはAzure リソースに属しているため、分散キャッシュを使用すると、キャッシュを共有する他のAzure リソースに公開される可能性があります。

### トラブルシューティングとエラー処理

MSAL のエラーは、アプリ開発者がトラブルシューティングを行い、エンド ユーザーには表示しないことを目的としています。

MSAL からのエラーを処理する方法の詳細については、[error_design.md](https://learn.microsoft.com/ja-jp/entra/msal/go/error-design) を参照してください。 返されたエラー (マネージド ID サービスから発生) には、軽減策の手順を実行するのに役立つアクション可能なコンテキストが含まれています。

#### 潜在的なエラー

マネージド ID サービスから返される可能性のあるエラーの詳細については、[エラー コードの一覧](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/go/design"} -->
## Microsoft Authentication Library for Go デザイン ガイド - Microsoft Authentication Library for Go

- Source: https://learn.microsoft.com/ja-jp/entra/msal/go/design
- Service: entra-id
- Article date: 2026-04-29
- Summary: Go のMicrosoft Authentication Libraryの設計ガイドライン。

### 一般的な構造

パブリック サーフェス

```bash
apps/ - Contains all our code
  confidential/ - The confidential application API
  public/ - The public application API
  cache/ - The cache interface that can be implemented to provide persistence cache storage of credentials
```

内部構造

```bash
apps/
  internal/
    client/ - Shared package for common calls that Public and Confidential apps share
    json/ - Our own json encoder/decoder for special needs
    shared/ - Holds types that need to be in multiple packages and can't be moved into a single one due to import cycles
    requests/ - The pacakge to communicate to services to get tokens
```

#### Go の特別な内部/ディレクトリの使用

Go では、 `internal/` という名前のディレクトリには、同じ場所にルート化された他のパッケージのみが使用する必要があるパッケージが含まれています。 これは公式の [Go ドキュメント](https://golang.org/doc/go1.4#internalpackages)で説明されています。

たとえば、パッケージ `.../a/b/c/internal/d/e/f` は、 `.../a/b/c`をルートとするディレクトリ ツリー内のコードによってのみインポートできます。 `.../a/b/g`またはその他のリポジトリ内のコードでインポートすることはできません。

この機能は、内部パッケージを使用しているものを明確にするために非常に自由に使用します。 例えば次が挙げられます。

```bash
apps/internal/base - Only can be used by packages defined at apps/
apps/internal/base/internal/storage - Only can be use by package client
```

### パブリック API

パブリック API は、 `apps/`にカプセル化されます。 `apps/` には、ユーザーに関心のある 3 つのパッケージがあります。

- `public/` - パブリック アプリケーション クライアントについて説明します。 これらは、コマンド ライン インターフェイス アプリなど、ユーザーのコンピューター上で実行されるアプリです。
- `confidential/` - 機密アプリケーション クライアントについて説明します。 これらは、サーバー上で実行されるアプリです。Web アプリ、ユーザーのトークンを取得する Web API、および自身の代わりにトークンを取得する Web API (サービス間通信)
- `cache/` - これにより、MSAL クライアントの永続キャッシュを作成するために実装する必要があるインターフェイスが提供されます。

### 内部構造

このセクションでは、 `internal/`について説明します。

#### JSON の処理

JSON は、アプリで特別に処理する必要があります。 基本は、構造体に含まれていないフィールドを受け取った場合、下位互換性と将来の互換性を確保するために、それらを削除することはできません。

これを処理するには、これを処理する独自のカスタム `json` パッケージを使用します。

#### バックエンド通信

バックエンドへの通信は、 `requests/` パッケージを介して行われます。 `oauth.Token` は、すべての通信のクライアントです。

`oauth.Token` は、 `ops/` クライアントにカプセル化された REST 呼び出しを介して通信します。

### 機能の追加

これは、MSAL に新しい機能を追加する一般的な方法です。

- `ops.REST` に REST 呼び出しを追加
- より高レベルの操作を`oauth.Token`に追加する
- `app/\<client\>` にロジックを追加し、`oauth.Token` 経由でサービスにアクセスします。

### 他のクライアントとの顕著な違い

#### 機密アプリケーションでは、1 つの大きなキャッシュなしで複数のユーザーを処理する必要があります

MSAL キャッシュの設計は、かなり単純です。 これらの設計上の決定事項と、異なる言語の複数のアプリケーションがキャッシュを共有できることは、キャッシュを簡単に変更できないことを意味します。

`confidential.Client`のキャッシュコンテンツ全体は、外部キャッシュとの間で送受信されるほぼすべてのアクションで読み取られ、書き込まれます。

スケーリングの問題を防ぐために、機密クライアントがユーザーごとに必要であることは、ユーザーには明らかではありません。

現時点では MSAL キャッシュの設計を変更できないため、ユーザーごとに `confidential.Client` を行う必要があることを明確にする必要があります。

#### x509.Certificate と CertFromPEM() 関数の使用

このパッケージの元のバージョンでは、証明書に基づいて承認を行うために拇印と秘密キーを使用しました。 しかし、拇印を取得する本当の方法はありませんでした。

拇印は OAuth 仕様で定義されており、追跡する必要がありました。 x509 証明書の DER でエンコードされた ASN1 バイトからの SHA-1 ハッシュです。

ユーザーが x509 を必要としていたため、ユーザーに `x509.Certificate` オブジェクトを提供してもらうよう移動しました。

内部向けにフィンガープリント生成機能を実装しました。 秘密キーも必要であり、取得するのは簡単ではないため、`CertFromPEM()`と秘密キーを抽出する`x509.Certificate`関数を追加しました。 暗号化された PEM がサポートされました。

Note

なお、Key Vault では項目が PKCS12 と PEM 形式で保存されます。

### Logging

エラーについては、 [エラーの設計](https://learn.microsoft.com/ja-jp/entra/msal/go/error-design)を参照してください。

このライブラリでは、個人を特定できる情報 (PII) は記録されません。 PII の定義については、「 [顧客データ定義](https://www.microsoft.com/trust-center/privacy/customer-data-definitions)」を参照してください。 MSAL Go では、Web サイトに記載されている 3 つのデータ カテゴリはログに記録されません。

ライブラリは、テナント ID、機関、クライアント ID など、組織に関連する情報のほか、要求の関連付け ID、HTTP 状態コード、その他の一般化可能なメタデータなど、ユーザーに関連付けられない情報をログに記録する場合があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/go/error-design"} -->
## Go 向け Microsoft Authentication Library のエラー設計 - Microsoft Authentication Library for Go

- Source: https://learn.microsoft.com/ja-jp/entra/msal/go/error-design
- Service: entra-id
- Article date: 2026-04-29
- Summary: Go のMicrosoft Authentication Libraryのエラー設計ガイドライン。

### 経歴

MSAL のエラーは、アプリ開発者がトラブルシューティングを行い、エンド ユーザーには表示しないことを目的としています。

#### MSAL エラーの目的

- ユーザーに診断を提供し、バグやクライアントの構成ミスを追跡するために使用できるチケットを提供する
- 一時的で再試行可能なエラーを検出する
- プログラムが応答できる特定のエラー (登録の必要性をユーザーに通知する) をユーザーが特定できるようにする

#### Go エラー処理と他の言語

ほとんどの最新言語では、例外ベースのエラーが使用されます。 簡単に言うと、例外は「スロー」され、上位スタック内のどこかのルーチンでキャッチされなければ、最終的にプログラムをクラッシュさせます。

Go では例外は使用されません。代わりに、複数の戻り値に依存します。そのうちの 1 つは組み込みのエラー インターフェイス型です。 何を行うかはユーザーが決める必要があります。

#### Go カスタム エラーの種類

Go でエラーを作成するには、 `errors.New()` または `fmt.Errorf()` を使用して "エラー" を作成します。

カスタム エラーは、複数の方法で作成できます。 より堅牢な方法の 1 つは、単にエラー インターフェイスを満たすことです。

```go
type MyCustomErr struct {
  Msg string
}
func (m MyCustomErr) Error() string { // This implements "error"
  return m.Msg
}
```

### クライアント側エラーの実装

クライアント側のエラーは、構成の誤りや、回復不可能な無効な引数の受け渡しを示します。 再試行はできません。

これらのエラーは、 `errors.New()` または `fmt.Errorf()`によって作成される標準的な Go エラーです。 行の下にカスタム エラーが必要な場合は、それを紹介できますが、現時点では、エラー メッセージは問題の内容を明確にする必要があります。

### サーバー側エラーの実装

サービス側エラーは、外部 RPC が HTTP エラー コードで応答するか、エラーを含むメッセージを返したときに発生します。

これらのエラーは一時的なもの (遅くしてください) または永続的 (HTTP 404) である可能性があります。 診断目標を提供するには、これらのエラーを他のエラーと区別する機能が必要です。

現在の実装には、サーバーからのエラーをキャプチャする特殊化された型が含まれています。

```go
// CallErr represents an HTTP call error. Has a Verbose() method that allows getting the
// http.Request and Response objects. Implements error.
type CallErr struct {
    Req  *http.Request
    Resp *http.Response
    Err  error
}

// Errors implements error.Error().
func (e CallErr) Error() string {
    return e.Err.Error()
}

// Verbose prints a versbose error message with the request or response.
func (e CallErr) Verbose() string {
    e.Resp.Request = nil // This brings in a bunch of TLS stuff we don't need
    e.Resp.TLS = nil     // Same
    return fmt.Sprintf("%s:\nRequest:\n%s\nResponse:\n%s", e.Err, prettyConf.Sprint(e.Req), prettyConf.Sprint(e.Resp))
}
```

ユーザーは、常に最も簡潔なエラーを受け取ります。 Go エラー パッケージを使用して、サーバー側のエラーであるかどうかを確認できます。

```go
var callErr CallErr
if errors.As(err, &callErr) {
  ...
}
```

指定したエラーから最も詳細なメッセージを取得できる `Verbose()` 関数を提供します。

```go
fmt.Println(errors.Verbose(err))
```

さらに差別化が必要な場合は、診断目標を達成するために、 `CallErr` の上に Go エラー ラッピングを使用するカスタム エラーを追加できます (一時的なエラーによる呼び出しを再試行するタイミングの検出など)。

`CallErr` は、(すべての HTTP リクエストを処理する) comm パッケージから常に送出され、次のようなものです。

```go
return nil, errors.CallErr{
    Req:  req,
    Resp: reply,
    Err:  fmt.Errorf("http call(%s)(%s) error: reply status code was %d:\n%s", req.URL.String(), req.Method, reply.StatusCode, ErrorResponse), //ErrorResponse is the json body extracted from the http response
    }
```

### 今後の決定

呼び出しを再試行する機能には、一元的な責任が必要です。 ユーザーが実行しているか、クライアントが実行しています。

ユーザーが責任を負う必要がある場合は、エラー パッケージに `CanRetry()` 関数が含まれます。この関数は、ユーザーに提供されたエラーが再試行可能かどうかをユーザーに通知します。 これは、HTTP エラー コードと、返されたエラーの種類に基づいています。 サーバーが待機する時間を返した場合は、スリープ時間も含まれます。

それ以外の場合は、内部的にこれを行い、再試行は私たちに任されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/go/packages/cache"} -->
## cache パッケージ - Microsoft Authentication Library for Go

- Source: https://learn.microsoft.com/ja-jp/entra/msal/go/packages/cache
- Service: entra-id
- Article date: 2026-04-29
- Summary: パッケージ キャッシュを使用すると、サード パーティは、分散システムまたは複数のローカル アプリケーション アクセス用にトークン データをキャッシュするための外部ストレージを実装できます。

```go
import "github.com/AzureAD/microsoft-authentication-library-for-go/apps/cache"
```

パッケージ `cache` を使用すると、サード パーティは、分散システムまたは複数のローカル アプリケーション アクセス用にトークン データをキャッシュするための外部ストレージを実装できます。

格納および抽出されたデータは、キャッシュ全体を表します。 そのため、ユーザーごとに 1 つの msal インスタンスをお勧めします。 このデータは不透明と見なされ、渡される形式で実装者に保証はありません。

### 型 ExportHints

ExportHints は、データを格納するための提案です。

```go
type ExportHints struct {
    // PartitionKey is a suggested key for partitioning the cache
    PartitionKey string
}
```

### type ExportReplace

ExportReplace は、メモリ内キャッシュ データのエクスポートと置換を行います。 nil Context をサポートしたり、渡した結果を定義したりすることはありません。 タイムアウトのないコンテキストは、実装者によって指定された既定のタイムアウトを受け取る必要があります。 再試行は、実装内で実装する必要があります。

```go
type ExportReplace interface {
    // Replace replaces the cache with what is in external storage. Implementors should honor
    // Context cancellations and return context.Canceled or context.DeadlineExceeded in those cases.
    Replace(ctx context.Context, cache Unmarshaler, hints ReplaceHints) error
    // Export writes the binary representation of the cache (cache.Marshal()) to external storage.
    // This is considered opaque. Context cancellations should be honored as in Replace.
    Export(ctx context.Context, cache Marshaler, hints ExportHints) error
}
```

### 型 Marshaler

マーシャラーは、内部キャッシュ内のデータを格納可能なバイト列にマーシャリングします。

```go
type Marshaler interface {
    Marshal() ([]byte, error)
}
```

### 型 ReplaceHints

ReplaceHints は、データの読み込みに関するヒントです。

```go
type ReplaceHints struct {
    // PartitionKey is a suggested key for partitioning the cache
    PartitionKey string
}
```

### 型 Serializer

シリアライザーは、キャッシュをバイナリまたはバイナリからキャッシュにシリアル化できます。

```go
type Serializer interface {
    Marshaler
    Unmarshaler
}
```

### 型 Unmarshaler

Unmarshaler は、ストレージ メディアから内部キャッシュにデータをアンマーシャリングし、上書きします。

```go
type Unmarshaler interface {
    Unmarshal([]byte) error
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/go/packages/confidential"} -->
## confidential Package - Microsoft Authentication Library for Go

- Source: https://learn.microsoft.com/ja-jp/entra/msal/go/packages/confidential
- Service: entra-id
- Article date: 2026-04-29
- Summary: パッケージ機密は、機密アプリケーションの認証用のクライアントを提供します。

```go
import "github.com/AzureAD/microsoft-authentication-library-for-go/apps/confidential"
```

パッケージ `confidential` は、"機密" アプリケーションの認証用のクライアントを提供します。 "機密" アプリケーションは、サーバー上で実行されるアプリとして定義されます。 アクセスが困難であると見なされ、そのためアプリケーション シークレットを保持できます。 機密クライアントは、構成時のシークレットを保持できます。

### func AutoDetectRegion

```go
func AutoDetectRegion() string
```

AutoDetectRegion は、Azureリージョン トークン サービスのリージョンを自動検出するように MSAL Go に指示します。

### func CertFromPEM

```go
func CertFromPEM(pemData []byte, password string) ([]*x509.Certificate, crypto.PrivateKey, error)
```

CertFromPEM は、[NewCredFromCert] で使用するために PEM ファイル (.pem または.key) を変換します。 ファイルには、公開証明書と秘密キーが含まれている必要があります。 PEM ブロックが暗号化されていて、パスワードが空の文字列でない場合は、パスワードを使用して PEM ブロックの暗号化を解除しようとします。 複数の証明書は、ルートからリーフに署名する TLS などのユース ケースの証明書チェーンが原因です。

### func WithChallenge

```go
func WithChallenge(challenge string) interface {
    AcquireByAuthCodeOption
    options.CallOption
}
```

WithChallengeを使用すると、のための課題を提供することができます.AcquireTokenByAuthCode() 呼び出し。

### func WithClaims

```go
func WithClaims(claims string) interface {
    AcquireByAuthCodeOption
    AcquireByCredentialOption
    AcquireOnBehalfOfOption
    AcquireSilentOption
    AuthCodeURLOption
    options.CallOption
}
```

WithClaims は、条件付きアクセス ポリシーで必要な要求など、トークンを要求する追加の要求を設定します。 AD Azureが以前の要求の要求チャレンジを返した場合は、このオプションを使用します。 引数はデコードする必要があります。 このオプションは、任意のトークン取得方法に対して有効です。

### func WithDomainHint

```go
func WithDomainHint(domain string) interface {
    AuthCodeURLOption
    options.CallOption
}
```

WithDomainHint は、IdP ドメインdomain\_hint認証 URL のクエリ パラメーターとして追加します。

### func WithLoginHint

```go
func WithLoginHint(username string) interface {
    AuthCodeURLOption
    options.CallOption
}
```

WithLoginHint は、ログイン プロンプトにユーザー名を事前に設定します。

### func WithSilentAccount

```go
func WithSilentAccount(account Account) interface {
    AcquireSilentOption
    options.CallOption
}
```

WithSilentAccount は、AcquireTokenSilent() 呼び出し中に渡されたアカウントを使用します。

### func WithTenantID

```go
func WithTenantID(tenantID string) interface {
    AcquireByAuthCodeOption
    AcquireByCredentialOption
    AcquireOnBehalfOfOption
    AcquireSilentOption
    AuthCodeURLOption
    options.CallOption
}
```

WithTenantID は、1 つの認証のテナントを指定します。 [新規] のテナント セットとは異なる場合があります。 このオプションは、任意のトークン取得方法に対して有効です。

### type Account

```go
type Account = shared.Account
```

### type AcquireByAuthCodeOption

AcquireByAuthCodeOption は AcquireTokenByAuthCode のオプションによって実装されます

```go
type AcquireByAuthCodeOption interface {
    // contains filtered or unexported methods
}
```

### 型 AcquireByCredentialOption

AcquireByCredentialOption は AcquireTokenByCredential のオプションによって実装されます

```go
type AcquireByCredentialOption interface {
    // contains filtered or unexported methods
}
```

### 型 AcquireOnBehalfOfOption

AcquireOnBehalfOfOption は AcquireTokenOnBehalfOf のオプションによって実装されます

```go
type AcquireOnBehalfOfOption interface {
    // contains filtered or unexported methods
}
```

### type AcquireSilentOption

AcquireSilentOption は AcquireTokenSilent のオプションによって実装されます

```go
type AcquireSilentOption interface {
    // contains filtered or unexported methods
}
```

### type AssertionRequestOptions

AssertionRequestOptions には、クライアント アサーション要求に必要な情報があります

```go
type AssertionRequestOptions = exported.AssertionRequestOptions
```

### type AuthCodeURLOption

AuthCodeURLOption は AuthCodeURL のオプションによって実装されます

```go
type AuthCodeURLOption interface {
    // contains filtered or unexported methods
}
```

### type AuthResult

AuthResult には、1 つのトークン取得操作の結果が含まれています。 詳細については、以下を参照してください。 https://aka.ms/msal-net-authenticationresult

```go
type AuthResult = base.AuthResult
```

### type Client

クライアントは、パッケージ ドキュメントで定義されている機密アプリケーションの認証クライアントの表現です。サービス ユーザーごとに新しいクライアントを作成する必要があります。 詳細については、 [MSAL クライアント アプリケーション](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-client-applications)に関するドキュメントを参照してください。

```go
type Client struct {
    // contains filtered or unexported fields
}
```

#### func New

```go
func New(authority, clientID string, cred Credential, options ...Option) (Client, error)
```

New は Client のコンストラクターです。 authority は、"https://login.microsoftonline.com/&lt;テナント&gt;" などのトークン機関の URL です。 クライアントが AD FS に直接接続する場合は、テナントに "adfs" を使用します。 clientID は、アプリケーションのクライアント ID ("アプリケーション ID" とも呼ばれます) です。

#### func (クライアント) アカウント

```go
func (cca Client) Account(ctx context.Context, accountID string) (Account, error)
```

Account は、指定された homeAccountID を持つトークン キャッシュ内のアカウントを取得します。

#### func (クライアント) AcquireTokenByAuthCode

```go
func (cca Client) AcquireTokenByAuthCode(ctx context.Context, code string, redirectURI string, scopes []string, opts ...AcquireByAuthCodeOption) (AuthResult, error)
```

AcquireTokenByAuthCode は、承認コードを使用して、機関からセキュリティ トークンを取得する要求です。 指定されたリダイレクト URI は、承認コードが要求されたときに使用されたものと同じ URI である必要があります。

オプション: [WithChallenge]、[WithClaims]、[WithTenantID]

#### func (クライアント) AcquireTokenByCredential

```go
func (cca Client) AcquireTokenByCredential(ctx context.Context, scopes []string, opts ...AcquireByCredentialOption) (AuthResult, error)
```

AcquireTokenByCredential は、クライアント資格情報の付与を使用して、機関からセキュリティ トークンを取得します。

オプション: [WithClaims]、[WithTenantID]

#### func (クライアント) AcquireTokenOnBehalfOf

```go
func (cca Client) AcquireTokenOnBehalfOf(ctx context.Context, userAssertion string, scopes []string, opts ...AcquireOnBehalfOfOption) (AuthResult, error)
```

AcquireTokenOnBehalfOf は、中間層アプリのアクセス トークンを使用してアプリのセキュリティ トークンを取得します。 [On Behalf Flow のドキュメントを参照してください](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-oauth2-on-behalf-of-flow)。

オプション: [WithClaims]、[WithTenantID]

#### func (クライアント) AcquireTokenSilent

```go
func (cca Client) AcquireTokenSilent(ctx context.Context, scopes []string, opts ...AcquireSilentOption) (AuthResult, error)
```

AcquireTokenSilent は、キャッシュから、または更新トークンを使用してトークンを取得します。

オプション: [WithClaims]、[WithSilentAccount]、[WithTenantID]

#### func (クライアント) AuthCodeURL

```go
func (cca Client) AuthCodeURL(ctx context.Context, clientID, redirectURI string, scopes []string, opts ...AuthCodeURLOption) (string, error)
```

AuthCodeURL は、承認コードの取得に使用される URL を作成します。 ユーザーは CreateAuthorizationCodeURLParameters を呼び出して渡す必要があります。

オプション: [WithClaims]、[WithDomainHint]、[WithLoginHint]、[WithTenantID]

#### func (クライアント) RemoveAccount

```go
func (cca Client) RemoveAccount(ctx context.Context, account Account) error
```

RemoveAccount はアカウントをサインアウトし、トークン キャッシュからアカウントを忘れる。

### type Credential

資格情報は、機密クライアント フローで使用される資格情報を表します。

```go
type Credential struct {
    // contains filtered or unexported fields
}
```

#### func NewCredFromAssertionCallback

```go
func NewCredFromAssertionCallback(callback func(context.Context, AssertionRequestOptions) (string, error)) Credential
```

NewCredFromAssertionCallback は、アプリケーションを認証するアサーションを取得するコールバックを呼び出す資格情報を作成します。 コールバックはスレッド セーフである必要があります。

#### func NewCredFromCert

```go
func NewCredFromCert(certs []*x509.Certificate, key crypto.PrivateKey) (Credential, error)
```

NewCredFromCert は、[CertFromPEM] によって返される証明書または証明書チェーンと RSA 秘密キーから資格情報を作成します。

例 (Pem)

```go
package main

import ("fmt""log""os"
"github.com/AzureAD/microsoft-authentication-library-for-go/apps/confidential"
)

func main() {b, err := os.ReadFile("key.pem")if err != nil {	log.Fatal(err)}
// This extracts our public certificates and private key from the PEM file. If it is// encrypted, the second argument must be password to decode.certs, priv, err := confidential.CertFromPEM(b, "")if err != nil {	log.Fatal(err)}
cred, err := confidential.NewCredFromCert(certs, priv)if err != nil {	log.Fatal(err)}fmt.Println(cred) // Simply here so cred is used, otherwise won't compile.
}
```

#### func NewCredFromSecret

```go
func NewCredFromSecret(secret string) (Credential, error)
```

NewCredFromSecret はシークレットから資格情報を作成します。

#### func NewCredFromTokenProvider

```go
func NewCredFromTokenProvider(provider func(context.Context, TokenProviderParameters) (TokenProviderResult, error)) Credential
```

NewCredFromTokenProvider は、アクセス トークンを提供する関数から資格情報を作成します。 この関数はコンカレンシー セーフである必要があります。 これは、Azure SDKが MSI トークンをキャッシュすることを許可することのみを目的としています。 トークン プロバイダーはすべての認証ロジックを実装する必要があるため、一般的にアプリケーションには役立ちません。

### type オプション

オプションは New() の省略可能な引数です。

```go
type Option func(o *clientOptions)
```

#### func WithAzureRegion

```go
func WithAzureRegion(val string) Option
```

WithAzureRegion では、自動検出リージョンの region(preferred) または Confidential.AutoDetectRegion() が設定されます。 [Azure サイトで定義されている](https://azure.microsoft.com/global-infrastructure/geographies/)リージョン名。 リージョン名の詳細については、 https://aka.ms/region-map を参照してください。 リージョンの値は、サービスがデプロイされているリージョンの短いリージョン名にする必要があります。 たとえば、"centralus" は米国中部リージョンの短い名前です。 すべての認証フローでリージョン トークン サービスを使用できるわけではありません。 サービス間 (クライアント資格情報フロー) トークンは、リージョン サービスから取得できます。 テナント レベルでの構成が必要です。 自動検出は、限られた数のAzure成果物 (VM、Azure関数) で動作します。 自動検出が失敗した場合は、リージョン以外のエンドポイントが使用されます。 無効なリージョン名が指定されている場合、リージョン以外のエンドポイントが使用されるか、トークン要求が失敗する可能性があります。

#### func WithCache

```go
func WithCache(accessor cache.ExportReplace) Option
```

WithCache には、認証データを外部で管理されたキャッシュに読み書きするアクセサーが用意されています。

#### func WithClientCapabilities

```go
func WithClientCapabilities(capabilities []string) Option
```

WithClientCapabilities を使用すると、"CP1" などの 1 つ以上のクライアント機能を構成できます

#### func WithHTTPClient

```go
func WithHTTPClient(httpClient ops.HTTPClient) Option
```

WithHTTPClient を使用すると、カスタム HTTP クライアントを設定できます。

#### func WithInstanceDiscovery

```go
func WithInstanceDiscovery(enabled bool) Option
```

機関の検証を無効にする場合 (プライベート クラウド のシナリオをサポートするため) に WithInstanceDiscovery を false に設定する

#### func WithX5C

```go
func WithX5C() Option
```

WithX5C では、サブジェクト名発行者認証を有効にするために x5c claim (証明書の公開キー) を STS に送信するかどうかを指定します。

### 型 TokenProviderParameters

TokenProviderParameters は、トークン プロバイダーに渡される認証パラメーターです

```go
type TokenProviderParameters = exported.TokenProviderParameters
```

### 型 TokenProviderResult

TokenProviderResult は、カスタム トークン プロバイダーによって返される認証結果です

```go
type TokenProviderResult = exported.TokenProviderResult
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/go/packages/errors"} -->
## errors パッケージ - Microsoft Authentication Library for Go

- Source: https://learn.microsoft.com/ja-jp/entra/msal/go/packages/errors
- Service: entra-id
- Article date: 2026-04-29
- Summary: Microsoft Authentication Library for Go のエラーを処理するように設計されたパッケージ。

```go
import "github.com/AzureAD/microsoft-authentication-library-for-go/apps/errors"
```

### func As

```go
func As(err error, target interface{}) bool
```

ターゲットと一致するエラー チェーン内の最初のエラーが見つかると、その場合はターゲットをそのエラー値に設定し、true を返します。 それ以外の場合は false を返します。

### func Is

```go
func Is(err, target error) bool
```

エラー チェーン内のエラーがターゲットと一致するかどうかを報告します。

### func New

```go
func New(text string) error
```

New はエラーに相当します。New()。

### func Verbose

```go
func Verbose(err error) string
```

詳細は、エラー メッセージに含まれる最も詳細なエラーを出力します。

### Type CallErr

CallErr は HTTP 呼び出しエラーを表します。 http を取得できる Verbose() メソッドがあります。要求オブジェクトと応答オブジェクト。 エラーを実装します。

```go
type CallErr struct {
    Req *http.Request
    // Resp contains response body
    Resp *http.Response
    Err  error
}
```

#### func (CallErr) エラー

```go
func (e CallErr) Error() string
```

エラーはエラーを実装します。Error().

#### func (CallErr) Verbose

```go
func (e CallErr) Verbose() string
```

Verbose は、要求または応答と共に versbose エラー メッセージを出力します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/go/packages/public"} -->
## パブリック パッケージ - Microsoft Authentication Library for Go

- Source: https://learn.microsoft.com/ja-jp/entra/msal/go/packages/public
- Service: entra-id
- Article date: 2026-04-29
- Summary: パッケージ パブリックは、パブリック アプリケーションの認証用のクライアントを提供します。

```go
import "github.com/AzureAD/microsoft-authentication-library-for-go/apps/public"
```

パッケージ `public` は、"パブリック" アプリケーションの認証用のクライアントを提供します。 "パブリック" アプリケーションは、クライアント デバイス (android、ios、windows、linux、...) で実行されるアプリとして定義されます。これらのデバイスは "信頼されていません" であり、認証が必要な Web API を介してリソースにアクセスします。

### func WithChallenge

```go
func WithChallenge(challenge string) interface {
    AcquireByAuthCodeOption
    options.CallOption
}
```

WithChallenge を使用すると、.AcquireTokenByAuthCode() 呼び出し。

### func WithClaims

```go
func WithClaims(claims string) interface {
    AcquireByAuthCodeOption
    AcquireByDeviceCodeOption
    AcquireByUsernamePasswordOption
    AcquireInteractiveOption
    AcquireSilentOption
    AuthCodeURLOption
    options.CallOption
}
```

WithClaims は、条件付きアクセス ポリシーで必要な要求など、トークンを要求する追加の要求を設定します。 AD Azureが以前の要求の要求チャレンジを返した場合は、このオプションを使用します。 引数はデコードする必要があります。 このオプションは、任意のトークン取得方法に対して有効です。

### func WithDomainHint

```go
func WithDomainHint(domain string) interface {
    AcquireInteractiveOption
    AuthCodeURLOption
    options.CallOption
}
```

WithDomainHint は、IdP ドメインdomain\_hint認証 URL のクエリ パラメーターとして追加します。

### func WithLoginHint

```go
func WithLoginHint(username string) interface {
    AcquireInteractiveOption
    AuthCodeURLOption
    options.CallOption
}
```

WithLoginHint は、ログイン プロンプトにユーザー名を事前に設定します。

### func WithRedirectURI

```go
func WithRedirectURI(redirectURI string) interface {
    AcquireInteractiveOption
    options.CallOption
}
```

WithRedirectURI は、対話型認証で使用されるローカル サーバーのポートを設定します (例: http://localhost:port. ポート以外のすべての URI コンポーネントは無視されます。

### func WithSilentAccount

```go
func WithSilentAccount(account Account) interface {
    AcquireSilentOption
    options.CallOption
}
```

WithSilentAccount は、AcquireTokenSilent() 呼び出し中に渡されたアカウントを使用します。

### func WithTenantID

```go
func WithTenantID(tenantID string) interface {
    AcquireByAuthCodeOption
    AcquireByDeviceCodeOption
    AcquireByUsernamePasswordOption
    AcquireInteractiveOption
    AcquireSilentOption
    AuthCodeURLOption
    options.CallOption
}
```

WithTenantID は、1 つの認証のテナントを指定します。 [WithAuthority] によって [新規] に設定されているテナントとは異なる場合があります。 このオプションは、任意のトークン取得方法に対して有効です。

### type Account

```go
type Account = shared.Account
```

### type AcquireByAuthCodeOption

AcquireByAuthCodeOption は AcquireTokenByAuthCode のオプションによって実装されます

```go
type AcquireByAuthCodeOption interface {
    // contains filtered or unexported methods
}
```

### type AcquireByDeviceCodeOption

AcquireByDeviceCodeOption は AcquireTokenByDeviceCode のオプションによって実装されます

```go
type AcquireByDeviceCodeOption interface {
    // contains filtered or unexported methods
}
```

### type AcquireByUsernamePasswordOption

Warning

AcquireTokenByUsernamePassword は、セキュリティ 上のリスクのため非推奨になりました。 Microsoft では、より安全な認証フローを使用することをお勧めします。 [ROPC から移行](https://aka.ms/msal-ropc-migration)する方法に関する公式ガイダンスに従ってください。

AcquireByUsernamePasswordOption は AcquireTokenByUsernamePassword のオプションによって実装されます

```go
type AcquireByUsernamePasswordOption interface {
    // contains filtered or unexported methods
}
```

### type AcquireInteractiveOption

AcquireInteractiveOption は AcquireTokenInteractive のオプションによって実装されます

```go
type AcquireInteractiveOption interface {
    // contains filtered or unexported methods
}
```

### type AcquireSilentOption

AcquireSilentOption は AcquireTokenSilent のオプションによって実装されます

```go
type AcquireSilentOption interface {
    // contains filtered or unexported methods
}
```

### type AuthCodeURLOption

AuthCodeURLOption は AuthCodeURL のオプションによって実装されます

```go
type AuthCodeURLOption interface {
    // contains filtered or unexported methods
}
```

### type AuthResult

AuthResult には、1 つのトークン取得操作の結果が含まれています。 詳細については、以下を参照してください。 https://aka.ms/msal-net-authenticationresult

```go
type AuthResult = base.AuthResult
```

### type Client

クライアントは、パッケージ ドキュメントで定義されているパブリック アプリケーションの認証クライアントの表現です。詳細については、 [MSAL クライアント アプリケーションに関するドキュメントを参照してください](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-client-applications)。

```go
type Client struct {
    // contains filtered or unexported fields
}
```

#### func New

```go
func New(clientID string, options ...Option) (Client, error)
```

New は Client のコンストラクターです。

#### func (クライアント) アカウント

```go
func (pca Client) Accounts(ctx context.Context) ([]Account, error)
```

アカウントは、トークン キャッシュ内のすべてのアカウントを取得します。 キャッシュにアカウントがない場合、返されるスライスは空です。

#### func (クライアント) AcquireTokenByAuthCode

```go
func (pca Client) AcquireTokenByAuthCode(ctx context.Context, code string, redirectURI string, scopes []string, opts ...AcquireByAuthCodeOption) (AuthResult, error)
```

AcquireTokenByAuthCode は、承認コードを使用して、機関からセキュリティ トークンを取得する要求です。 指定されたリダイレクト URI は、承認コードが要求されたときに使用されたものと同じ URI である必要があります。

オプション: [WithChallenge]、[WithClaims]、[WithTenantID]

#### func (クライアント) AcquireTokenByDeviceCode

```go
func (pca Client) AcquireTokenByDeviceCode(ctx context.Context, scopes []string, opts ...AcquireByDeviceCodeOption) (DeviceCode, error)
```

AcquireTokenByDeviceCode は、デバイス コードを取得し、そのコードを使用してトークンを取得することで、機関からセキュリティ トークンを取得します。 ユーザーは AcquireTokenDeviceCodeParameters インスタンスを作成して渡す必要があります。

オプション: [WithClaims]、[WithTenantID]

#### func (Client) AcquireTokenByUsernamePassword

Warning

この API は、セキュリティ リスクのため非推奨となりました。より安全なフローを使用してください。 移行 [ガイダンスについては、このガイド](https://aka.ms/msal-ropc-migration) に従ってください。

```go
func (pca Client) AcquireTokenByUsernamePassword(ctx context.Context, scopes []string, username, password string, opts ...AcquireByUsernamePasswordOption) (AuthResult, error)
```

AcquireTokenByUsernamePassword は、ユーザー名/パスワード認証を使用して、機関からセキュリティ トークンを取得します。 注: このフローは推奨されません。

オプション: [WithClaims]、[WithTenantID]

#### func (クライアント) AcquireTokenInteractive

```go
func (pca Client) AcquireTokenInteractive(ctx context.Context, scopes []string, opts ...AcquireInteractiveOption) (AuthResult, error)
```

AcquireTokenInteractive は、既定の Web ブラウザーを使用してアカウントを選択して、機関からセキュリティ トークンを取得します。 [対話型フローと非対話型フローに関するドキュメント](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-authentication-flows#interactive-and-non-interactive-authentication)を参照してください。

オプション: [WithDomainHint]、[WithLoginHint]、[WithRedirectURI]、[WithTenantID]

#### func (クライアント) AcquireTokenSilent

```go
func (pca Client) AcquireTokenSilent(ctx context.Context, scopes []string, opts ...AcquireSilentOption) (AuthResult, error)
```

AcquireTokenSilent は、キャッシュから、または更新トークンを使用してトークンを取得します。

オプション: [WithClaims]、[WithSilentAccount]、[WithTenantID]

#### func (クライアント) AuthCodeURL

```go
func (pca Client) AuthCodeURL(ctx context.Context, clientID, redirectURI string, scopes []string, opts ...AuthCodeURLOption) (string, error)
```

AuthCodeURL は、承認コードの取得に使用される URL を作成します。

オプション: [WithClaims]、[WithDomainHint]、[WithLoginHint]、[WithTenantID]

#### func (クライアント) RemoveAccount

```go
func (pca Client) RemoveAccount(ctx context.Context, account Account) error
```

RemoveAccount はアカウントをサインアウトし、トークン キャッシュからアカウントを忘れる。

### type DeviceCode

DeviceCode は、2 番目のデバイスで入力する必要があるデバイス コード フローの最初のステージ (コードを含む) の結果を提供し、そのコードが入力されて検証された後に AuthenticationResult を取得するメソッドを提供します。

```go
type DeviceCode struct {
    // Result holds the information about the device code (such as the code).
    Result DeviceCodeResult
    // contains filtered or unexported fields
}
```

#### func (DeviceCode) AuthenticationResult

```go
func (d DeviceCode) AuthenticationResult(ctx context.Context) (AuthResult, error)
```

AuthenticationResult は、ユーザーが 2 番目のデバイスでコードを入力すると、AuthenticationResult を取得します。 それまでは、 .AcquireTokenByDeviceCode() コンテキストが取り消されるか、トークンの有効期限が切れます。

### type DeviceCodeResult

```go
type DeviceCodeResult = accesstokens.DeviceCodeResult
```

### type オプション

オプションは、New コンストラクターの省略可能な引数です。

```go
type Option func(o *clientOptions)
```

#### func WithAuthority

```go
func WithAuthority(authority string) Option
```

WithAuthority を使用すると、カスタム権限を設定できます。 これは有効な https URL である必要があります。

#### func WithCache

```go
func WithCache(accessor cache.ExportReplace) Option
```

WithCache には、外部で管理されているキャッシュに対して認証データの読み取りと書き込みを行うアクセサーが用意されています。

#### func WithClientCapabilities

```go
func WithClientCapabilities(capabilities []string) Option
```

WithClientCapabilities を使用すると、"CP1" などの 1 つ以上のクライアント機能を構成できます

#### func WithHTTPClient

```go
func WithHTTPClient(httpClient ops.HTTPClient) Option
```

WithHTTPClient を使用すると、カスタム HTTP クライアントを設定できます。

#### func WithInstanceDiscovery

```go
func WithInstanceDiscovery(enabled bool) Option
```

機関の検証を無効にする場合 (プライベート クラウド のシナリオをサポートするため) に WithInstanceDiscovery を false に設定する
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java"} -->
## Java 用 Microsoft Authentication Library - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java
- Service: msal / msal-java
- Article date: 2025-03-27
- Summary: Java 用 Microsoft Authentication Library (通常は MSAL Javaまたは MSAL4J に短縮) を使用すると、アプリケーションをMicrosoft ID プラットフォームと統合できます。

Java 用 Microsoft Authentication Library (MSAL Java または MSAL4J) は、アプリケーションを[Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview)と統合します。 これにより、Microsoft ID (Microsoft Entra ID、Microsoft アカウント、AZURE AD B2C アカウント) を使用してユーザーまたはアプリにサインインし、[Microsoft Graphや独自](https://graph.microsoft.io/)の API などのMICROSOFT API を呼び出すトークンを取得できます。 MSAL Javaでは、業界標準の OAuth2 プロトコルと OpenID Connect プロトコルが使用されます。

### Overview

1. [MSAL4J を使用する理由](https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/why-use-msal4j)
2. **前提条件**: MSAL4J を使用する前に、[アプリケーションをMicrosoft Entra IDに登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)する必要があります。
3. MSAL4J の使用を開始するには、 [クライアント アプリケーション](https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/client-applications)をインスタンス化して構成します。
4. MSAL4J を使用して [トークンを取得する](https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/acquiring-tokens) 方法について説明します。
5. [堅牢なエンタープライズ対応アプリケーションのベスト プラクティスに](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/best-practices-enterprise)従います。
6. 一般的な問題と既知のバグについては [、FAQ](https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/faq) を参照してください。

### MSAL Java シナリオ

MSAL4J は、アプリケーションが保護された API にアクセスするためのトークンを取得するために使用できます。 トークンは、デスクトップ アプリケーション、Web アプリケーション、Web API、ブラウザーのないデバイス (IoT デバイスなど) で実行されているアプリケーションなど、さまざまな **種類のアプリケーション**によって取得できます。 MSAL4J では、アプリケーションは次のように分類されます。

- **パブリック クライアント アプリケーション (デスクトップとモバイル)**。 これらの種類のアプリでは、アプリ シークレットを安全に格納できません。
- **機密クライアント アプリケーション (Web アプリ、Web API、デーモン アプリケーション)。** これらの種類のアプリは、Microsoft Entra IDに登録されたシークレットを安全に格納します。

上記のインスタンス化と構成の詳細については、「 [クライアント アプリケーション」トピックを](https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/client-applications) 参照してください。

MSAL4J では、ユーザー名またはアプリケーション自体の名前 (ユーザーなし) でトークンを取得できます。 後者の場合は、機密クライアント アプリケーションを使用する必要があります。

MSAL4J は、さまざまなオペレーティング システム (Windows、Linux、macOS) で実行されているアプリケーションで使用できます。

MSAL4J でサポートされる主なシナリオ:

- [ユーザーをサインインさせる Web アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-sign-user-overview)
- [ユーザーにサインインし、ユーザーの名前で Web API を呼び出す Web アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-overview)
- [サインインしているユーザーの名前で Web API を呼び出すデスクトップ アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-overview)
- [ユーザーなしで Web API を呼び出すデスクトップ/サービス デーモン アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-daemon-overview)
- [ブラウザーのないアプリケーション、またはユーザーの名前で API を呼び出す IOT アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token?tabs=java#command-line-tool-without-web-browser)

探しているシナリオが見つかりませんか? MSAL ライブラリ全体で [サポートされているシナリオとプラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios#scenarios-and-supported-platforms-and-languages) を確認してください。

### リリース

GitHubの [MSAL Java リリースを](https://github.com/AzureAD/microsoft-authentication-library-for-java/releases)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/aad-b2c"} -->
## MSAL4J を使用してソーシャル ID を持つユーザーをサインインさせる - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/aad-b2c
- Service: msal / msal-java
- Article date: 2024-01-27
- Summary: MSAL4J を使用すると、AZURE AD B2C を使用して、ソーシャル ID を持つユーザーにサインインできます。

MSAL4J を使用して、[Azure Active Directory B2C (Azure AD B2C](https://aka.ms/aadb2c)) を使用して、ソーシャル ID でユーザーをサインインさせることができます。 Azure AD B2C は、ポリシーの概念を中心に構築[されています](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/custom-policy-overview)。 MSAL4J では、ポリシーの指定が機関の提供に変換されます。クライアント アプリケーションをインスタンス化する場合は、機関の構成でポリシーを指定する必要があります

### B2C テナントおよびポリシーに対する権限

一般に、使用する権限は `https://login.microsoftonline.com/tfp/{tenant}/{policyName}` です。ここで、次のとおりです。

- `tenant`は、Azure AD B2C テナントの名前です
- `policyName` 適用するポリシーの名前 (サインイン/サインアップの `b2c_1_susi` など)。

Note

Azure AD B2C チームの現在のガイダンスは、`b2clogin.com`を機関として使用することです。 たとえば、「 `https://{your-tenant-name}.b2clogin.com/tfp/{your-tenant-ID}/{policyname}` 」のように入力します。 詳細については、「[Azure Active Directory B2C のリダイレクト URL を b2clogin.com に設定](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/b2clogin)する」を参照してください。

### ユーザー名とパスワードのフローの制限事項

Microsoftでは、リソース所有者のパスワード資格情報 (ROPC) (ユーザー名とパスワード のフローとも呼ばれます) を使用しないことをお勧めします。 ほとんどのシナリオでは、より安全な代替手段が利用でき、推奨されます。 このフローでは、アプリケーションに非常に高い信頼が必要であり、他のフローに存在しないリスクが伴います。 このフローは、より安全なフローが実行可能ではない場合にのみ使用してください。 この許可の使用を避けたい理由の詳細については、[パスワードを過去のものにするためにMicrosoftが取り組んでいる理由を](https://news.microsoft.com/features/whats-solution-growing-problem-passwords-says-microsoft/)参照してください。

MSAL4J でユーザー名とパスワードのフローを使用している場合は、次の制限事項に注意してください。

- フローはローカル アカウントでのみ機能し、電子メールまたはユーザー名を使用して Azure AD B2C に登録します。 このフローは、B2C (Facebook、Google など) でサポートされている ID プロバイダーのいずれかにフェデレーションする場合は機能しません。
- 現時点では、MSAL から ROPC フローを実装するときに B2C から返される `id_token` はありません。 つまり、アカウント オブジェクトを作成できないため、キャッシュにはアカウントもユーザーも存在しません。 このシナリオでは、 [`acquireTokenSilently`](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.abstractclientapplicationbase#com-microsoft-aad-msal4j-abstractclientapplicationbase-acquiretokensilently%28com-microsoft-aad-msal4j-silentparameters%29) フローは機能しません。 ただし、ROPC には UI が表示されないため、ユーザー エクスペリエンスには影響しません。

### アプリケーションのインスタンス化

機密クライアント アプリケーションの場合:

```java
 ConfidentialClientApplication cca = ConfidentialClientApplication.builder(APP_ID, credential)
    .b2cAuthority(B2C_AUTHORITY)
    .build();
```

パブリック クライアント アプリケーションの場合:

```java
PublicClientApplication pca = new PublicClientApplication.Builder(APP_ID)
    .b2cAuthority(B2C_AUTHORITY)
    .build();
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/authorization-code-url-builder"} -->
## 承認コード URL ビルダー - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/authorization-code-url-builder
- Service: msal / msal-java
- Article date: 2024-01-27
- Summary: Oauth2 承認コード フローを実行する場合、最初の手順は、ユーザーを認証する承認エンドポイントにユーザーを誘導し、ID プロバイダーが認証コードで応答することです。

Oauth2 承認コード フローを実行する場合、最初の手順は、ユーザーを認証する承認エンドポイントにユーザーを誘導し、ID プロバイダーが認証コードで応答することです。 その後、認証コードを使用して、MSALs [`PublicClientApplication.acquireToken(AuthorizationCodeParameters)`](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.abstractclientapplicationbase#com-microsoft-aad-msal4j-abstractclientapplicationbase-acquiretoken%28com-microsoft-aad-msal4j-authorizationcodeparameters%29) または [`ConfidentialClientApplication.acquireToken(AuthorizationCodeParameters)`](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.abstractclientapplicationbase#com-microsoft-aad-msal4j-abstractclientapplicationbase-acquiretoken%28com-microsoft-aad-msal4j-authorizationcodeparameters%29)を呼び出すことによってトークンを引き換えることができます。

### 承認 URL ビルダー

MSAL4J 1.4 の時点で、OAuth2 承認コード フローの最初の手順で使用される承認コード URL の作成に使用できるヘルパー メソッド ( [`getAuthorizationRequestUrl`](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.abstractclientapplicationbase#com-microsoft-aad-msal4j-abstractclientapplicationbase-getauthorizationrequesturl%28com-microsoft-aad-msal4j-authorizationrequesturlparameters%29)) が追加されました。

```java
PublicClientApplication publicClientApplication =
        PublicClientApplication
                .builder(CLIENT_ID)
                .authority(AUTHORITY)
                .build();

AuthorizationRequestUrlParameters parameters =
        AuthorizationRequestUrlParameters
                .builder(interactiveRequestParameters.redirectUri().toString(),
                        interactiveRequestParameters.scopes())
                .codeChallenge(verifier)
                .codeChallengeMethod("S256")
                .state(state);
                .build();

URL authorizationCodeUrl = publicClientApplication.getAuthorizationRequestUrl(parameters);
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/best-practices-enterprise"} -->
## エンタープライズのベスト プラクティス - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/best-practices-enterprise
- Service: msal / msal-java
- Article date: 2024-01-27
- Summary: 堅牢でエンタープライズ対応のアプリケーションを構築するには、MSAL Javaを使用する際に最適なパターンとプラクティスに従う必要があります。

堅牢でエンタープライズ対応のアプリケーションを構築するには、追加のガードレールをいくつか実装する必要があります。 開発者は次のことを行うことをお勧めします。

- トークンを取得する場合と、保護された Web API を呼び出すときの両方で、例外を処理します。 特に、テナント管理者が Multi Factor Authentication (MFA) を適用するように[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) ポリシーを設定しているMicrosoft Entra テナントでアプリケーションを実行する場合は、「[例外](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/exceptions)」で説明されている要求チャレンジを処理する必要があります。
- [ログ記録](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/msal-logging-java)を有効にして、ユーザーのプライバシーを尊重しながらアプリケーションのトラブルシューティングを行い、GDPR などのプライバシー規制に準拠し続けます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/configure-http-client"} -->
## カスタム HTTP クライアントを構成する - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/configure-http-client
- Service: msal / msal-java
- Article date: 2024-01-27
- Summary: MSAL を使用すると、きめ細かな構成をサポートするための独自の HTTP クライアントを提供できます。

MSAL を使用すると、きめ細かな構成をサポートするための独自の HTTP クライアントを提供できます。 カスタム Http クライアントを使用するには、 `IHttpClient`を実装する必要があります。 このクラスは、 `HttpClientAdapter`クラスで実装する必要があります `send`。このクラスは、MSAL によって構成された `HttpRequest` を受け取り、それを実行し、実行結果と共に `IHttpResponse` を構築します。

```java
class OkHttpClientAdapter implements IHttpClient{

    private OkHttpClient client;

    OkHttpClientAdapter(){

        // You can configure OkHttpClient 
        this.client = new OkHttpClient();  
    }

    @Override
    public IHttpResponse send(HttpRequest httpRequest) throws IOException {
        
        // Map URL, headers, and body from MSAL's HttpRequest to OkHttpClient request object  
        Request request = buildOkRequestFromMsalRequest(httpRequest);

        // Execute Http request with OkHttpClient
        Response okHttpResponse= client.newCall(request).execute();
     
        // Map status code, headers, and response body from OkHttpClient's Response object to MSAL's IHttpResponse
        return buildMsalResponseFromOkResponse(okHttpResponse);
    }
}
```

`HttpClientAdapter`を実装したら、それを使用するクライアント アプリケーションで設定できます。

```java
IHttpClient httpClient = new OkHttpClientAdapter();
PublicClientApplication pca = PublicClientApplication.builder(
        APP_ID).
        authority(AUTHORITY).
        httpClient(httpClient).
        build();
```

これで、すべての HTTP 要求が `httpClient`経由でルーティングされるようになります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/deployment-instructions-for-msal-java-samples"} -->
## MSAL Java サンプルの展開手順 - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/deployment-instructions-for-msal-java-samples
- Service: msal / msal-java
- Article date: 2024-01-27
- Summary: MSAL Javaは、多数の Web およびアプリケーション サーバーにデプロイできます。 ビルドとデプロイの正確な手順は環境と既存のセットアップによって異なりますが、一般的な Web/アプリ サーバーで MSAL Java サンプルを実行する手順を次に示します。

MSAL Javaは、多数の Web およびアプリケーション サーバーにデプロイできます。 ビルドとデプロイの正確な手順は環境と既存のセットアップによって異なりますが、一般的な Web/アプリ サーバーで MSAL Java サンプルを実行する手順を次に示します。

### Maven を使用して .war ファイルをビルドする

すべてのサンプルは Maven を使用してビルドできます。 プロジェクトの pom.xml ファイルが含まれているディレクトリに移動し (通常、サンプルを実行する場合はメイン README と同じです)、次の Maven コマンドを実行します。

`mvn clean package`

これにより、さまざまな Web/アプリ サーバーで実行できる .war ファイルが生成されます

パッケージをビルドする前に、ほとんどのサンプルの構成ファイルと README 命令で既定のリダイレクト URL として `http://localhost:8080` または `https://localhost:8443` が使用されることに注意してください。 これらのポートは、IDE から直接実行する場合は Apache Tomcat と組み込みのアプリ サーバーで機能しますが、Weblogic、JBoss、Websphere などの一部のエンタープライズ アプリ サーバーでは、異なる既定のポートが使用されます。 これらのサーバーでサンプルを実行するには、特定のファイルと、そのサンプルのAzure アプリ登録で構成を変更する必要があります。以下の手順には、これらの変更を行う場所と既定のポートが含まれます。

### Apache Tomcat での実行

(これらの手順では、Tomcat がシステムにインストールされていることを前提としています)

Tomcat でサンプルを実行するには:

1. Tomcat のインストールで、アプリケーションをホストするアドレスの `<connector>` エントリが tomcat/conf/server.xml にあることを確認します

    - 既定では、サンプルは、authentication.properties ファイルの app.homePage 値で定義されているように、 `http://localhost:8080` または `https://localhost:8443`に接続することを想定しています
2. Maven で生成した .war ファイルを Tomcat インストールの /webapps/ ディレクトリにコピーし、Tomcat サーバーを起動します
3. Tomcat が起動したら、ブラウザーを開き、手順 1 で定義した任意の URL に移動すると、アプリケーションにアクセスできるようになります。

### WebLogic での実行

(これらの手順では、WebLogic をインストールし、いくつかのサーバー ドメインを設定していることを前提としています)

WebLogic にデプロイする前に、サンプル自体で構成を変更し、パッケージを (再) ビルドする必要があります。

1. このサンプルには、クライアント ID、テナント、リダイレクト URL などを構成した `application.properties` または `authentication.properties` ファイルがある可能性があります。
2. 上記のファイルでは、webLogic の URL/ポートへの `localhost:8080` または `localhost:8443` への変更された参照が実行されます。既定では、次のようになります。 `localhost:7001`
3. また、Azure アプリの登録でも同じ変更を行う必要があります。ここで、[認証] タブの [リダイレクト URI] として設定します。

Web コンソールを使用してサンプルを WebLogic にデプロイするには:

1. DOMAIN\_NAME\bin\startWebLogic.cmd を使用して WebLogic サーバーを起動する
2. ブラウザーで WebLogic Web コンソールに移動します。 `http://localhost:7001/console`
3. [Domain Structure &gt; Deployments] に移動し、[インストール] をクリックし、[ファイルのアップロード] をクリックして、Maven でビルドした .war ファイルを見つけます。
4. [この展開をアプリケーションとしてインストールする] を選択し、[次へ] をクリックし、[完了]、[保存] の順にクリックします。

    - 既定の設定のほとんどは、サンプルの構成/Azure アプリの登録で設定した "リダイレクト URI" に一致するようにアプリケーションに名前を付ける必要がある点、つまりリダイレクト URI が`http://localhost:7001/msal4j-servlet-auth`場合は、アプリケーションに "msal4j-サーブレット-auth" という名前を付ける必要がある点を除けば問題ありません。
5. ドメイン構造 &gt; 展開に戻り、アプリケーションを起動する
6. アプリケーションが起動したら、 `http://localhost:7001/{whatever you named the application}/`に移動すると、アプリケーションにアクセスできるようになります。

### JBoss EAP での実行

JBoss にデプロイする前に、サンプル自体で構成を変更し、パッケージを (再) ビルドする必要があります。

1. このサンプルには、クライアント ID、テナント、リダイレクト URL などを構成した `application.properties` または `authentication.properties` ファイルがある可能性があります。
2. 上記のファイルで、`localhost:8080` または `localhost:8443` への参照を、JBoss が使用する URL/ポートに変更してください。既定では `localhost:9990` です。
3. また、Azure アプリの登録でも同じ変更を行う必要があります。ここで、[認証] タブの [リダイレクト URI] として設定します。

Web コンソールを使用してサンプルを JBoss EAP にデプロイするには:

1. %JBOSS\_HOME%\bin\standalone.bat を使用して JBoss サーバーを起動する
2. ブラウザーで JBoss Web コンソールに移動します。 `http://localhost:9990`
3. [デプロイ] に移動し、[追加] をクリックし、ビルドした .war をアップロードします
4. 既定の設定のほとんどは、サンプルの構成/Azure アプリの登録で設定した "リダイレクト URI" に一致するようにアプリケーションに名前を付ける必要がある点、つまりリダイレクト URI が`http://localhost:9990/msal4j-servlet-auth/`場合は、アプリケーションに "msal4j-サーブレット-auth" という名前を付ける必要がある点を除けば問題ありません。
5. アップロードした .war ファイルを選択し、[En/Disable] をクリックし、[確認] をクリックしてアプリケーションを起動します
6. アプリケーションが起動したら、 `http://localhost:9990/{whatever you named the application}/`に移動すると、アプリケーションにアクセスできるようになります。

### Websphere での実行

(これらの手順では、Websphere をインストールし、いくつかのサーバーを設定していることを前提としています)Websphere にデプロイする前に、サンプル自体で構成を変更し、パッケージを (再) ビルドする必要があります。

1. このサンプルには、クライアント ID、テナント、リダイレクト URL などを構成した `application.properties` または `authentication.properties` ファイルがある可能性があります。
2. 上記のファイルで、`localhost:8080` または `localhost:8443` への参照を、Websphere が実行される URL/ポートに変更しました。既定では `localhost:9080` です。
3. また、Azure アプリの登録でも同じ変更を行う必要があります。ここで、[認証] タブの [リダイレクト URI] として設定します。

Websphere の統合ソリューション コンソールを使用してサンプルをデプロイします。

1. [アプリケーション] タブで、[新しいアプリケーション]、[新しいエンタープライズ アプリケーション] の順に選択します。
2. 構築した .war を選択し、[Web モジュールのコンテキスト ルートのマップ] インストール手順に移動するまで [次へ] をクリックします (他の既定の設定は問題ありません)
3. コンテキスト ルートの場合は、サンプル構成/Azure アプリ登録で設定した "リダイレクト URI" のポート番号の後と同じ値に設定します。つまり、リダイレクト URI が`http://localhost:9080/msal4j-servlet-auth/`場合、コンテキスト ルートは単に 'msal4j-サーブレット-auth' である必要があります
4. [完了] をクリックし、アプリケーションのインストールが完了したら、[アプリケーション] タブの [Websphere エンタープライズ アプリケーション] セクションに移動します。
5. アプリケーションの一覧からインストールした .war を選択し、[開始] をクリックして展開します
6. デプロイが完了したら、 `http://localhost:9080/{whatever you set as the context root}` に移動すると、アプリケーションが表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/exceptions"} -->
## MSAL Javaの例外 - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/exceptions
- Service: msal / msal-java
- Article date: 2024-01-27
- Summary: 例外を処理するときは、例外の種類自体と ErrorCode メンバーを使用して例外を区別できます。

例外を処理するときは、例外の種類自体と ErrorCode メンバーを使用して例外を区別できます。 例外には、 `MsalClientException`、 `MsalServiceException`、 `MsalInteractionRequiredException`の 3 種類があり、これらはすべて `MsalException`から継承されます。

- **MsalClientException** は、ライブラリまたはデバイス側でエラーが発生したときにスローされます。
- **MsalServiceException** は、STS サービスからエラー応答が返されたときか、別のネットワーク エラーが発生したときにスローされます。
- 認証を成功させるために UI 操作が必要な場合、**MsalInteractionRequiredException** がスローされます。

### MsalServiceException

MsalServiceException は、STS に要求を返した HTTP ヘッダーを公開します。 ユーザーは、次の方法でアクセスできます。 `MsalServiceException.headers()`

### MsalInteractionRequiredException

`AcquireTokenSilently()`の呼び出し時に MSAL4J から返される一般的な状態コードの 1 つが`InvalidGrantError`。 この状態コードは、アプリケーションが認証ライブラリをもう一度呼び出す必要があることを意味しますが、対話型モード (パブリック クライアント アプリケーションには AuthorizationCodeParameters または DeviceCodeParameters を使用)。 これは、認証トークンを発行する前に追加のユーザー操作が必要であるためです。

AcquireTokenSilently が失敗するほとんどの場合、トークン キャッシュに要求に一致するトークンがないためです。 アクセス トークンは 1 時間で期限切れになり、AcquireTokenSilently は更新トークンに基づいて新しいトークンをフェッチしようとします (OAuth2 の用語では、これは "更新トークン" フローです)。 このフローは、テナント管理者がより厳格なログイン ポリシーを構成する場合など、さまざまな理由で失敗する可能性もあります。

この操作は、ユーザーにアクションを実行してもらうことを目的としています。 これらの条件の中には、ユーザーが簡単に解決できる条件 (たとえば、1 回のクリックで使用条件に同意する) と、現在の構成で解決できない条件があります (たとえば、該当するマシンは特定の企業ネットワークに接続する必要があります)。

MSAL は、 `reason` フィールドを公開します。このフィールドを読んでユーザー エクスペリエンスを向上させることができます。たとえば、パスワードの有効期限が切れたことをユーザーに伝えたり、一部のリソースを使用するために同意する必要があることをユーザーに伝えたりすることができます。 サポートされている値は、InteractionRequiredExceptionReason 列挙型の一部です。

| 理由 | Meaning | 推奨される処理 |
| --- | --- | --- |
| BasicAction | 対話型認証フロー中にユーザーの操作によって条件を解決できます | 対話型パラメーターを使用して acquireToken を呼び出す |
| 追加アクション | 条件は、対話型認証フローの外部で、システムとの追加の修復操作によって解決できます。 | 対話型パラメーターを指定して acquireToken を呼び出し、修復アクションを説明するメッセージを表示します。 呼び出し元のアプリケーションでは、ユーザーが修復アクションを完了する可能性が低い場合に、additional\_actionを必要とするフローを非表示にすることができます。 |
| MessageOnly | 現時点では、条件を解決できません。 対話型認証フローを起動すると、条件を説明するメッセージが表示されます。 | 対話型パラメーターを使用して acquireToken を呼び出し、条件を説明するメッセージを表示します。 acquireTokenCall は、ユーザーがメッセージを読んでウィンドウを閉じた後、UserCanceled エラーを返します。 呼び出し元のアプリケーションは、ユーザーがメッセージの恩恵を受ける可能性が低い場合にmessage\_onlyになるフローを非表示にすることを選択できます。 |
| 同意が必要です | ユーザーの同意がないか、取り消されています。 | すべての acquireToken を対話型パラメーターで呼び出して、ユーザーが同意できるようにします。 |
| UserPasswordExpired | ユーザーのパスワードの有効期限が切れています。 | ユーザーがパスワードをリセットできるように、対話型パラメーターを使用して acquireToken を呼び出す |
| 同意が必要です | ユーザーの同意がない、または取り消された | ユーザーがパスワードをリセットできるように、対話型パラメーターを使用して acquireToken を呼び出す |
| なし | 詳細は提供されません。 条件は、対話型認証フロー中にユーザーの操作によって解決される場合があります。 | 対話型パラメーターを使用して acquireToken を呼び出す |

### コード例

```java
IAuthenticationResult result;
try {
    PublicClientApplication application = PublicClientApplication
            .builder("clientId")
            .b2cAuthority("authority")
            .build();

    SilentParameters parameters = SilentParameters
            .builder(Collections.singleton("scope"))
            .build();

    result = application.acquireTokenSilently(parameters).join();
}
catch (Exception ex){
    if(ex instanceof MsalInteractionRequiredException){
        // AcquireToken by either AuthorizationCodeParameters or DeviceCodeParameters
    } else{
        // Log and handle exception accordingly
    }
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/instance-discovery"} -->
## 独自のインスタンス検出メタデータの提供 - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/instance-discovery
- Service: msal / msal-java
- Article date: 2024-01-27
- Summary: MSAL4J は、acquireToken() または GetAccount() API 呼び出しの前にインスタンス検出を実行します。 このリクエストは、トークン キャッシュで使用される OpenID メタデータ エンドポイントや認証機関のエイリアスなどを取得するために使用されます（これらの詳細は重要ではありません。MSAL4J が開発者に代わって抽象化しているためです）。

### Context

MSAL4J は、 `acquireToken()` または `GetAccount()` API 呼び出しの前にインスタンス検出を実行します。 このリクエストは、トークン キャッシュで使用される OpenID メタデータ エンドポイントや認証局エイリアスなどを取得するために使用されます（これらの詳細は重要ではありません。MSAL4J が開発者から隠蔽しているためです）。

MSAL4J は、毎回このネットワーク呼び出しを行う必要がないように、このメタデータをメモリ内にキャッシュします。 このシナリオでは、最初の API 呼び出しによってネットワーク要求が行われ、データがキャッシュされ、MSAL はプロセスの有効期間中、キャッシュされたデータを引き続き使用します。

アプリケーションが操作ごとに新しいプロセスを作成するシナリオ (一部の CLI ツールなど) では、メモリ内キャッシュではデータ インスタンス検出データを使用できません。また、ライブラリは常にネットワーク呼び出しを行う必要があります。 特定のアプリケーションでは、この動作はパフォーマンス上の理由から望ましくない可能性があります。特に、アプリケーションが `acquireTokenSilently()` または `getAccounts()`の呼び出しを行っている場合は、トークンまたはアカウントが既にクライアント上にあり、インスタンス検出要求が待機時間の最大の原因となります。

### 対処法

このシナリオが該当するアプリケーションがある場合は、MSAL4J クライアント アプリケーション オブジェクトの作成時にインスタンス検出応答データを渡すことができます。 MSAL4J は、ネットワーク呼び出しを行う代わりに、このデータを使用します。

これを行うには、次の手順を実行します。

`https://{host}/common/discovery/instance?api-version=1.1&authorization_endpoint={authorizeEndpoint}` に対して HTTP GET リクエストを送信する

`{host}` - あなたの権威のホスト。 たとえば、login.microsoftonline.com

`{authorizeEndpoint}` - 承認エンドポイント

例えば- `https://login.microsoftonline.com/common/discovery/instance?api-version=1.1&authorization_endpoint=https://login.microsoftonline.com/organizations/oauth2/v2.0/authorize`

これにより、次のような JSON 形式の BLOB が返されます。

```json
{
  "tenant_discovery_endpoint": "https://login.microsoftonline.com/common/.well-known/openid-configuration",
  "api-version": "1.1",
  "metadata": [
    {
      "preferred_network": "login.microsoftonline.com",
      "preferred_cache": "login.windows.net",
      "aliases": [
        "login.microsoftonline.com",
        "login.windows.net",
        "login.microsoft.com",
        "sts.windows.net"
      ]
    },
    {
      "preferred_network": "login.partner.microsoftonline.cn",
      "preferred_cache": "login.partner.microsoftonline.cn",
      "aliases": [
        "login.partner.microsoftonline.cn",
        "login.chinacloudapi.cn"
      ]
    },
    {
      "preferred_network": "login.microsoftonline.de",
      "preferred_cache": "login.microsoftonline.de",
      "aliases": [
        "login.microsoftonline.de"
      ]
    },
    {
      "preferred_network": "login.microsoftonline.us",
      "preferred_cache": "login.microsoftonline.us",
      "aliases": [
        "login.microsoftonline.us",
        "login.usgovcloudapi.net"
      ]
    },
    {
      "preferred_network": "login-us.microsoftonline.com",
      "preferred_cache": "login-us.microsoftonline.com",
      "aliases": [
        "login-us.microsoftonline.com"
      ]
    }
  ]
}
```

その後、この情報を格納し、 `PublicClientApplication` または `ConfidentialClientApplication` オブジェクトをビルドするときに渡すことができます。

```java
String instanceDiscoveryResponse = readResource(
        "/aad_instance_discovery_response.json");

PublicClientApplication app = PublicClientApplication.builder("client_id")
        .instanceDiscoveryMetadata(instanceDiscoveryResponse)
        .build();
```

次の点に注意してください。

- 渡されるデータは有効な JSON である必要があります
- この機能を使用する場合、お客様はデータを格納し、適切に更新する責任を負います
- MSAL はこのデータを既にキャッシュしています。 これにより、アプリケーションが操作ごとに新しいプロセスを作成するシナリオの待機時間のみが短縮されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/integrated-windows-authentication"} -->
## 統合 Windows 認証 - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/integrated-windows-authentication
- Service: msal / msal-java
- Article date: 2024-01-27
- Summary: デスクトップまたはモバイル アプリケーションがWindows上で実行され、Windows ドメインに接続されているコンピューター (Active Directoryまたは参加Microsoft Entra) で実行されている場合は、統合Windows認証 (IWA) を使用してトークンをサイレントで取得できます。

デスクトップまたはモバイル アプリケーションがWindows上で実行され、Windows ドメインに接続されているコンピューター (Active Directoryまたは参加Microsoft Entra) で実行されている場合は、統合Windows認証 (IWA) を使用してトークンをサイレントで取得できます。 アプリケーションを使用するときに UI は必要ありません。

```java
final String AUTHORITY;
final String APP_ID;
String userName;
List<String> scopes;

PublicClientApplication app = PublicClientApplication.builder(APP_ID)
        .authority(AUTHORITY)
        .build();

IntegratedWindowsAuthenticationParameters parameters =
        IntegratedWindowsAuthenticationParameters.builder(scope, userName).build();

IAuthenticationResult future = app.acquireToken(parameters).get();
```

#### 制約

- *フェデレーション*\* ユーザーのみ。つまり、認証がオンプレミス機関 (ADFS など) にフェデレーションされている場合、またはシームレス SSO が有効になっている [ハイブリッド シナリオ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity) 。 ユーザーがMicrosoft Entra IDに直接存在する純粋なクラウド テナントでは、Active Directoryバッキングなしでは、このフローを使用できません。
- IWA は MFA (多要素認証) をバイパスしません。 MFA が構成されている状況では、MFA チャレンジが必要な場合に IWA が失敗する可能性があります。これは、MFA でユーザーの操作が必要になるためです。

>
> これは難しいです。 IWA は非対話型ですが、2FA にはユーザーの対話機能が必要です。 ID プロバイダーが 2FA の実行を要求するタイミングは制御しません。テナント管理者は実行します。 私たちの観察から、2FAは、あなたが別の国/地域からログインするとき、VPN経由で企業ネットワークに接続されていない場合、時にはVPN経由で接続されている場合でも必要です。 決定論的な一連のルールを想定しないでください。Microsoft Entra IDは AI を使用して、2FA が必要かどうかを継続的に学習します。 IWA が失敗した場合は、ユーザー プロンプトにフォールバックする必要があります

- `PublicApplication`で渡される権限は、次のようにする必要があります。

    - テナント指定（`https://login.microsoftonline.com/{tenant}/` の形式。ここで、`tenant` はテナント ID を表す GUID、またはテナントに関連付けられたドメインのいずれかです。）
    - 任意の職場および学校アカウントの場合 (`https://login.microsoftonline.com/organizations/`)

>
> Microsoft の個人用アカウントはサポート対象外です（/common または /consumers テナントは使用できません）
- 統合Windows認証はサイレント フローであるため、

    - アプリケーションのユーザーは、アプリケーションの使用に以前に同意している必要があります
    - または、テナント管理者が、アプリケーションを使用するためにテナント内のすべてのユーザーに以前に同意している必要があります。
    - これは、次のことを意味します。
        - 開発者が自分用に Azure portal 上の **[許可]** ボタンをクリックしておきます。
        - または、テナント管理者が、アプリケーションの登録の **[API アクセス許可**] タブにある **{tenant domain} に対する管理者の同意の付与/取り消**しボタンを押しました
        - または、ユーザーがアプリケーションに同意する方法を提供している
        - または、テナント管理者がアプリケーションに同意する方法を提供している
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/managed-identity"} -->
## MSAL Javaを使用したマネージド ID - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/managed-identity
- Service: msal / msal-java
- Article date: 2024-01-27
- Summary: MSAL Java アプリケーションでAzureマネージド ID を使用する方法。

Note

この機能は、[MSAL Java](https://github.com/AzureAD/microsoft-authentication-library-for-java/releases/tag/v1.15.0) バージョン 1.15.0 以降で使用できます

開発者にとって一般的な課題は、サービス間の通信をセキュリティで保護するために使用されるシークレット、資格情報、証明書、およびキーの管理です。 Azureの[マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) により、開発者がこれらの資格情報を手動で処理する必要がなくなります。 MSAL Javaでは、次のような、Azure インフラストラクチャ内で実行されているアプリケーションで使用する場合、マネージド ID サービスを介したトークンの取得がサポートされます。

- [Azure App Service](https://azure.microsoft.com/products/app-service/) (API バージョン `2019-08-01` 以降)
- [Azure VM](https://azure.microsoft.com/free/virtual-machines/)
- [Azure Arc](https://learn.microsoft.com/ja-jp/azure/azure-arc/overview)
- [Azure クラウド シェル](https://learn.microsoft.com/ja-jp/azure/cloud-shell/overview)
- [Azure Service Fabric](https://learn.microsoft.com/ja-jp/azure/service-fabric/service-fabric-overview)

完全な一覧については、[マネージド ID を使用して他のサービスにアクセスできる](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/managed-identities-status)サービスAzureを参照してください。

### どの SDK を使用するか - Azure SDK または MSAL?

MSAL ライブラリは、OAuth2 プロトコルと OIDC プロトコルに近い下位レベルの API を提供します。

MSAL Javaと[Azure SDK](https://learn.microsoft.com/ja-jp/azure/developer/java/sdk/overview)の両方で、マネージド ID 経由でトークンを取得できます。 内部的には、Azure SDKは MSAL Javaを使用します。 [DefaultAzureCredential](https://learn.microsoft.com/ja-jp/java/api/com.azure.identity.defaultazurecredential)と[ManagedIdentityCredential](https://learn.microsoft.com/ja-jp/java/api/com.azure.identity.managedidentitycredential)抽象化を使用して、より高いレベルの API を提供します。

アプリケーションでいずれかの SDK が既に使用されている場合は、引き続き同じ SDK を使用します。 新しいアプリケーションを作成し、他のAzure リソースを呼び出す予定の場合は、Azure SDKを使用します。この SDK では、マネージド ID が存在しないプライベート開発者マシンでアプリを実行できるようにすることで、開発者エクスペリエンスが向上します。 Microsoft Graphや独自の Web API などの他のダウンストリーム Web API を呼び出す必要がある場合は、MSAL の使用を検討してください。

### 簡単スタート

Azure Managed Identity をすぐに使い始めて実際の動作を確認するには、このためにチームが用意したサンプルのいずれかを使用できます。

### マネージド ID の使用方法

開発者が使用できるマネージド ID には、**システム割り当てとユーザー割り当ての** 2 種類があります。 違いの詳細については、 [マネージド ID の種類](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types) に関する記事を参照してください。 MSAL Javaでは、両方を使用したトークンの取得がサポートされています。 [MSAL Javaログ](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/msal-logging-java)記録を使用すると、要求と関連するメタデータを追跡できます。

MSAL Javaのマネージド ID を使用する前に、開発者は、Azure CLIまたはAzure portalを通じて使用するリソースに対して有効にする必要があります。

### 例示

ユーザー割り当て ID とシステム割り当て ID の両方で、開発者は [ManagedIdentityApplication.Builder](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.managedidentityapplication.builder) クラスを使用できます。

#### システム割り当てのマネージド ID

システム割り当てマネージド ID の場合、開発者は、 [IManagedIdentityApplication](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.imanagedidentityapplication)のインスタンスを作成するときに追加情報を渡す必要はありません。これは、割り当てられた ID に関する関連メタデータが自動的に推論されるためです。

[acquireTokenForManagedIdentity(ManagedIdentityParameters parameters)](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.imanagedidentityapplication#com-microsoft-aad-msal4j-imanagedidentityapplication-acquiretokenformanagedidentity%28com-microsoft-aad-msal4j-managedidentityparameters%29) は、 `https://management.azure.com`などのトークンを取得するためにリソースと共に呼び出されます。

```java
ManagedIdentityApplication miApp = ManagedIdentityApplication
                .builder(ManagedIdentityId.systemAssigned())
                .build();
                
ManagedIdentityParameters parameters = ManagedIdentityParameters.builder(resource).build();
                
IAuthenticationResult result = miApp.acquireTokenForManagedIdentity(parameters).get();
    
```

#### ユーザー割り当て済みマネージド ID

ユーザー割り当てマネージド ID の場合、開発者は、 [IManagedIdentityApplication](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.imanagedidentityapplication)を作成するときに、クライアント ID、完全なリソース識別子、またはマネージド ID のオブジェクト ID を渡す必要があります。

システム割り当てマネージド ID の場合と同様に、 [acquireTokenForManagedIdentity(ManagedIdentityParameters parameters)](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.imanagedidentityapplication#com-microsoft-aad-msal4j-imanagedidentityapplication-acquiretokenformanagedidentity%28com-microsoft-aad-msal4j-managedidentityparameters%29) はリソースと共に呼び出され、 `https://management.azure.com`などのトークンを取得します。

```java
ManagedIdentityApplication miApp = ManagedIdentityApplication
                .builder(ManagedIdentityId.userAssignedClientId(CLIENT_ID))
                .build();
                
 ManagedIdentityParameters parameters = ManagedIdentityParameters.builder(resource).build();
               
 IAuthenticationResult result = miApp.acquireTokenForManagedIdentity(
                ManagedIdentityParameters.builder(resource)
                        .build()).get();
```

### Caching

既定では、MSAL Javaではメモリ内キャッシュがサポートされます。 MSAL では、分散キャッシュを使用する際のセキュリティ上の懸念があるため、マネージド ID のキャッシュ拡張はサポートされていません。 マネージド ID 用に取得されたトークンはAzure リソースに属しているため、分散キャッシュを使用すると、キャッシュを共有する他のAzure リソースに公開される可能性があります。

### Troubleshooting

失敗した要求の場合、エラー応答には、さらに診断とログ分析に使用できる関連付け ID が含まれています。 MSAL Javaで生成されるか、MSAL に渡される関連付け ID は、MSAL Javaマネージド ID トークン取得エンドポイントに関連付け ID を渡すことができないので、サーバー エラー応答で返される関連付け ID とは異なることに注意してください。

#### 潜在的なエラー

##### `MsalServiceException`エラー コード: `managed_identity_failed_response` エラー メッセージ: Microsoft Entra トークンのフェッチ中に予期しないエラーが発生しました

この例外は、トークンを取得しようとしているリソースがサポートされていないか、間違ったリソース ID 形式を使用して提供されていることを意味する可能性があります。 正しいリソース ID 形式の例としては、 `https://management.azure.com/.default`、 `https://management.azure.com`、 `https://graph.microsoft.com`などがあります。

##### `MsalServiceException` エラー コード: `managed_identity_unreachable_network`。

この例外は、MSAL Javaがマネージド ID のトークンの取得をサポートしていないリソースを使用しているか、マネージド ID のトークンを取得するエンドポイントが到達できない開発マシンからサンプル コードを実行している可能性があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/migrate-adal-msal-java"} -->
## ADAL から MSAL への移行ガイド (MSAL4j) - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/migrate-adal-msal-java
- Service: msal / msal-java
- Article date: 2024-01-27
- Summary: Azure Active Directory認証ライブラリ (ADAL) Java アプリを Microsoft Authentication Library (MSAL) に移行する方法について説明します。

この記事では、Azure Active Directory認証ライブラリ (ADAL) を使用するアプリケーションを Microsoft Authentication Library (MSAL) に移行するために必要な変更について説明します。

Java 用 Microsoft Authentication Library (MSAL4J) と Azure AD Authentication Library for Java (ADAL4J) の両方を使用して、Microsoft Entra エンティティを認証し、Microsoft Entra IDからトークンを要求します。 これまで、ほとんどの開発者は、Azure AD (v1.0) と協力して、Azure AD 認証ライブラリ (ADAL) を使用してトークンを要求することで、職場や学校アカウントなどのさまざまな ID で認証してきました。

MSAL には、次の利点があります。

- 新しい Microsoft ID プラットフォーム を使用しているため、Microsoft Entra ID、Microsoft アカウント、Azure AD Business to Consumer (Azure AD B2C) を介したソーシャル アカウントとローカル アカウント、さらに Microsoft Entra 外部 ID を介したソーシャルまたはローカルの顧客アカウントなど、より幅広い種類の Microsoft ID を認証できます。
- ユーザーは最高のシングル サインオン エクスペリエンスを実現します。
- アプリケーションで増分同意を有効にしたり、条件付きアクセスなどの新機能をサポートしたりできます。

Java用の MSAL は、Microsoft ID プラットフォームで使用することをお勧めする認証ライブラリです。 ADAL4J には新しい機能は実装されません。 今後の取り組みはすべて、MSAL の改善に重点を置いて行っています。

MSAL の詳細については、[Microsoft Authentication Libraryの概要を](https://learn.microsoft.com/ja-jp/entra/msal/java/)参照してください。

### スコープであり、リソースではない

ADAL4J はリソースのトークンを取得し、Javaの MSAL はスコープのトークンを取得します。 Java クラスの多くの MSAL には、スコープ パラメーターが必要です。 このパラメーターは、要求される必要なアクセス許可とリソースを宣言する文字列の一覧です。 スコープの例については、[Microsoft Graphのスコープ](https://learn.microsoft.com/ja-jp/graph/permissions-reference)を参照してください。

`/.default` スコープ サフィックスをリソースに追加すると、アプリを ADAL から MSAL に移行するのに役立ちます。 たとえば、 `https://graph.microsoft.com`のリソース値の場合、同等のスコープ値は `https://graph.microsoft.com/.default`。 リソースが URL 形式ではなく、フォームのリソース ID `XXXXXXXX-XXXX-XXXX-XXXXXXXXXXXX`場合でも、スコープ値を `XXXXXXXX-XXXX-XXXX-XXXXXXXXXXXX/.default`として使用できます。

さまざまな種類のスコープの詳細については、[Microsoft ID プラットフォームのアクセス許可と同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)、および [v1.0 トークンを受け入れる Web API のスコープ](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-v1-app-scopes)に関する記事を参照してください。

### コア クラス

ADAL4J では、 `AuthenticationContext` クラスは、機関を介したセキュリティ トークン サービス (STS) または承認サーバーへの接続を表します。 ただし、Java用の MSAL は、クライアント アプリケーションを中心に設計されています。 クライアント アプリケーションを表す `PublicClientApplication` と `ConfidentialClientApplication` という 2 つの異なるクラスが用意されています。 後者の `ConfidentialClientApplication`は、デーモン アプリのアプリケーション識別子などのシークレットを安全に保持するように設計されたアプリケーションを表します。

次の表は、ADAL4J 関数がJava関数の新しい MSAL にどのようにマップされるかを示しています。

| ADAL4J メソッド | MSAL4J メソッド |
| --- | --- |
| acquireToken(String resource, ClientCredential credential, AuthenticationCallback callback) | [ClientCredentialParameters](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.clientcredentialparameters) |
| acquireToken(String resource, ClientAssertion assertion, AuthenticationCallback callback) | [ClientCredentialParameters](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.clientcredentialparameters) |
| acquireToken(String resource, AsymmetricKeyCredential credential, AuthenticationCallback callback) | [ClientCredentialParameters](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.clientcredentialparameters) |
| acquireToken(String resource, String clientId, String username, String password, AuthenticationCallback callback) | [UserNamePasswordParameters](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.usernamepasswordparameters) |
| acquireToken(String resource, String clientId, String username, String password=null, AuthenticationCallback callback) | [IntegratedWindowsAuthenticationParameters](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.integratedwindowsauthenticationparameters) |
| acquireToken(String resource, UserAssertion userAssertion, ClientCredential credential, AuthenticationCallback callback) | [OnBehalfOfParameters](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.onbehalfofparameters) |
| acquireTokenByAuthorizationCode() | [AuthorizationCodeParameters](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.authorizationcodeparameters) |
| acquireDeviceCode() と acquireTokenByDeviceCode() | [DeviceCodeFlowParameters](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.devicecodeflowparameters) |
| acquireTokenByRefreshToken() | [SilentParameters](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.silentparameters) |

### IUser の代わりに IAccount

ADAL4J がユーザーを処理しました。 ユーザーは 1 つの人間またはソフトウェア エージェントを表しますが、Microsoft ID システムに 1 つ以上のアカウントを持つことができます。 たとえば、ユーザーが複数のMicrosoft Entra ID、AZURE AD B2C、または個人アカウントMicrosoft持っている場合があります。

Java用の MSAL は、`IAccount` インターフェイスを介してアカウントの概念を定義します。 これは ADAL4J からの破壊的変更です。 これは、同じユーザーが複数のアカウントを持つ可能性があり、おそらく異なるMicrosoft Entraディレクトリに存在する可能性があるという事実をキャプチャします。 MSAL for Javaは、ホーム アカウント情報が提供されるため、ゲスト シナリオでより適切な情報を提供します。

### キャッシュの永続化

ADAL4J にはトークン キャッシュのサポートがありませんでした。 msAL for Java では[、トークン キャッシュ](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-acquire-cache-tokens)が追加され、可能な場合は期限切れのトークンを自動的に更新し、可能な場合はユーザーに資格情報を提供するための不要なプロンプトが表示されないようにすることで、トークンの有効期間の管理を簡略化できます。

### 共通機関

v1.0 では、`https://login.microsoftonline.com/common`機関を使用する場合、ユーザーは任意のMicrosoft Entra アカウント (任意の組織) でサインインできます。

v2.0 で`https://login.microsoftonline.com/common`機関を使用する場合、ユーザーは任意のMicrosoft Entra組織、またはMicrosoft個人アカウント (MSA) でサインインできます。 Javaの MSAL では、ログインを任意のMicrosoft Entra アカウントに制限する場合は、`https://login.microsoftonline.com/organizations`機関 (ADAL4J と同じ動作) を使用します。 権限を指定するには、`authority` クラスをインスタンス化するときに、[PublicClientApplication.Builder](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.publicclientapplication.builder) メソッドで `PublicClientApplication` パラメーターを設定します。

### v1.0 トークンと v2.0 トークン

v1.0 エンドポイント (ADAL によって使用) は、v1.0 トークンのみを出力します。

(MSAL によって使用される) v2.0 エンドポイントは、v1.0 トークンと v2.0 トークンを出力できます。 Web API のアプリケーション マニフェストのプロパティを使用すると、開発者は受け入れられるトークンのバージョンを選択できます。 `accessTokenAcceptedVersion`のリファレンス ドキュメントのを参照してください。

v1.0 および v2.0 トークンの詳細については、[アクセス トークンMicrosoft Entra](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)参照してください。

### ADAL から MSAL への移行

ADAL4J では、更新トークンが公開されました。これにより、開発者はトークンをキャッシュできます。 その後、 `AcquireTokenByRefreshToken()` を使用して、ユーザーが接続されなくなったときにユーザーの代わりにダッシュボードを更新する実行時間の長いサービスの実装などのソリューションを有効にします。

Javaの MSAL では、セキュリティ上の理由から更新トークンは公開されません。 代わりに、MSAL がトークンの更新を処理します。

Java用の MSAL には、ADAL4J で取得した更新トークンを `ClientApplication`: [RefreshTokenParameters](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.refreshtokenparameters)に移行できる API があります。 このメソッドを使用すると、以前に使用した更新トークンと、必要なスコープ (リソース) を指定できます。 更新トークンは新しいトークンと交換され、アプリケーションで使用するためにキャッシュされます。

次のコード スニペットは、機密クライアント アプリケーションの単純な移行コード スニペットを示しています。

```java
String rt = GetCachedRefreshTokenForSignedInUser(); // Get refresh token from where you have them stored
Set<String> scopes = Collections.singleton("SCOPE_FOR_REFRESH_TOKEN");

RefreshTokenParameters parameters = RefreshTokenParameters.builder(scopes, rt).build();

PublicClientApplication app = PublicClientApplication.builder(CLIENT_ID) // ClientId for your application
                .authority(AUTHORITY)  //plug in your authority
                .build();

IAuthenticationResult result = app.acquireToken(parameters);
```

`IAuthenticationResult`はアクセス トークンと ID トークンを返しますが、新しい更新トークンはキャッシュに格納されます。 アプリケーションには、次の `IAccount`も含まれるようになります。

```java
Set<IAccount> accounts =  app.getAccounts().join();
```

現在キャッシュ内にあるトークンを使用するには、次を呼び出します。

```java
SilentParameters parameters = SilentParameters.builder(scope, accounts.iterator().next()).build();
IAuthenticationResult result = app.acquireToken(parameters);
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/msal-error-handling-java"} -->
## MSAL4J でエラーと例外を処理する - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/msal-error-handling-java
- Service: msal / msal-java
- Article date: 2024-01-27
- Summary: MSAL4J アプリケーションでエラーと例外、条件付きアクセス要求チャレンジ、再試行を処理する方法について説明します。

この記事では、さまざまな種類のエラーの概要と、それらを処理するための推奨事項について説明します。

### MSAL エラー処理の基本

Microsoft Authentication Library (MSAL) の例外は、エンド ユーザーに表示されるのではなく、アプリ開発者がトラブルシューティングを行うために使用されます。 例外メッセージはローカライズされません。

例外とエラーを処理する場合は、例外の種類自体とエラー コードを使用して例外を区別できます。 エラー コードの一覧については、[認証と承認のエラー コードMicrosoft Entra](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes)参照してください。

サインイン エクスペリエンス中に、同意、条件付きアクセス (多要素認証、デバイス管理、場所ベースの制限など)、トークンの発行と引き換え、およびユーザー プロパティに関するエラーが発生する場合があります。

次のセクションでは、アプリのエラー処理の詳細について説明します。

### Javaの MSAL でのエラー処理

MSAL for Javaには、`MsalClientException`、`MsalServiceException`、`MsalInteractionRequiredException`の 3 種類の例外があります。これらはすべて`MsalException`から継承されます。

- `MsalClientException` は、ライブラリまたはデバイスに対してローカルとなるエラーの発生時にスローされます。
- `MsalServiceException` は、セキュリティで保護されたトークン サービス (STS) がエラー応答を返すか、別のネットワーク エラーが発生したときにスローされます。
- `MsalInteractionRequiredException` は、認証を成功させるために UI との対話が必要な場合にスローされます。

#### MsalServiceException

`MsalServiceException` は、要求で返された HTTP ヘッダーを STS に公開します。 `MsalServiceException.headers()` 経由でアクセスします

#### MsalInteractionRequiredException

`AcquireTokenSilently()`の呼び出し時に MSAL からJavaに返される一般的な状態コードの 1 つが`InvalidGrantError`。 これは、認証トークンを発行する前に、追加のユーザー操作が必要であることを意味します。 アプリケーションは認証ライブラリをもう一度呼び出す必要がありますが、パブリック クライアント アプリケーションの `AuthorizationCodeParameters` または `DeviceCodeParameters` を送信して対話型モードで呼び出す必要があります。

ほとんどの場合、 `AcquireTokenSilently` が失敗するのは、トークン キャッシュに要求に一致するトークンがないためです。 アクセス トークンは 1 時間で期限切れになり、 `AcquireTokenSilently` は更新トークンに基づいて新しいトークンを取得しようとします。 OAuth2 の用語では、これは更新トークン フローです。 このフローは、テナント管理者がより厳格なログイン ポリシーを構成する場合など、さまざまな理由で失敗する場合もあります。

このエラーが発生するいくつかの条件は、ユーザーが簡単に解決できます。 たとえば、使用条件に同意する必要がある場合や、マシンが特定の企業ネットワークに接続する必要があるため、現在の構成で要求を満たすことはできません。

MSAL では、 `reason` フィールドが公開されており、ユーザー エクスペリエンスを向上させるために使用できます。 たとえば、 `reason` フィールドを使用すると、パスワードの有効期限が切れたことをユーザーに伝えたり、一部のリソースを使用するために同意する必要があることをユーザーに通知したりすることができます。 サポートされている値は、 `InteractionRequiredExceptionReason` 列挙型の一部です。

| 理由 | Meaning | 推奨される処理 |
| --- | --- | --- |
| `BasicAction` | 条件は、対話型認証フロー中にユーザーの操作によって解決できます。 | 対話型パラメーターを使用して `acquireToken` を呼び出します。 |
| `AdditionalAction` | 条件は、対話型認証フローの外部にあるシステムとの追加の修復操作によって解決できます。 | 対話型パラメーターを使用して `acquireToken` を呼び出し、実行する修復アクションを説明するメッセージを表示します。 呼び出し元のアプリは、ユーザーが修復アクションを完了する可能性が低い場合に、追加のアクションを必要とするフローを非表示にすることを選択できます。 |
| `MessageOnly` | 現時点では、条件を解決できません。 対話型認証フローを起動して、条件を説明するメッセージを表示します。 | 対話型パラメーターを使用して `acquireToken` を呼び出して、条件を説明するメッセージを表示します。 `acquireToken` は、ユーザーがメッセージを読み取ってウィンドウを閉じた後に、 `UserCanceled` エラーを返します。 ユーザーがメッセージの恩恵を受ける可能性が低い場合、アプリはメッセージを生成するフローを非表示にすることを選択できます。 |
| `ConsentRequired` | ユーザーの同意がないか、取り消されています。 | ユーザーが同意できるように、対話型パラメーターを使用して `acquireToken` を呼び出します。 |
| `UserPasswordExpired` | ユーザーのパスワードの有効期限が切れています。 | ユーザーが自分のパスワードをリセットできるように、対話型パラメーターを使用して `acquireToken` を呼び出します。 |
| `None` | 詳細については、以下を参照してください。 この条件は、対話型認証フロー中にユーザーの操作によって解決される場合があります。 | 対話型パラメーターを使用して `acquireToken` を呼び出します。 |

#### コード例

```java
IAuthenticationResult result;
try {
    PublicClientApplication application = PublicClientApplication
            .builder("clientId")
            .b2cAuthority("authority")
            .build();

    SilentParameters parameters = SilentParameters
            .builder(Collections.singleton("scope"))
            .build();

    result = application.acquireTokenSilently(parameters).join();
}
catch (Exception ex){
    if(ex instanceof MsalInteractionRequiredException){
        // AcquireToken by either AuthorizationCodeParameters or DeviceCodeParameters
    } else{
        // Log and handle exception accordingly
    }
}
```

### 条件付きアクセスと要求の課題

トークンをサイレントで取得すると、アクセスしようとしている API で [条件付きアクセス要求チャレンジ](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-conditional-access-dev-guide) (MFA ポリシーなど) が必要な場合、アプリケーションでエラーが発生する可能性があります。

このエラーを処理するパターンは、MSAL を使用して対話形式でトークンを取得することです。 これにより、ユーザーにプロンプトが表示され、必要な条件付きアクセス ポリシーを満たす機会が提供されます。

条件付きアクセスを必要とする API を呼び出すときに、API からエラーのクレーム チャレンジを受け取る場合があります。 たとえば、条件付きアクセス ポリシーでマネージド デバイス (Intune) を使用する場合、エラーは [AADSTS53000 のようになります。このリソースや同様のリソースにアクセスするには、デバイスを管理する必要があります](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes) 。 この場合、取得トークン呼び出しで要求を渡して、ユーザーが適切なポリシーを満たすように求めることができます。

### エラーと例外の後の再試行

MSAL を呼び出すときに、独自の再試行ポリシーを実装する必要があります。 MSAL では、Microsoft Entra サービスへの HTTP 呼び出しが行われ、エラーが発生することがあります。 たとえば、ネットワークがダウンしたり、サーバーが過負荷になったりする可能性があります。

#### HTTP 429

サービス トークン サーバー (STS) が多すぎる要求でオーバーロードされると、HTTP エラー 429 が返され、 `Retry-After` 応答フィールドで再試行できるまでの時間に関するヒントが返されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/msal-java-get-remove-accounts-token-cache"} -->
## トークン キャッシュからアカウントを取得および削除する (MSAL4j) - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/msal-java-get-remove-accounts-token-cache
- Service: msal / msal-java
- Article date: 2024-01-27
- Summary: Java 用 Microsoft Authentication Libraryを使用してトークン キャッシュからアカウントを表示および削除する方法について説明します。

MSAL for Javaでは、既定でメモリ内トークン キャッシュが提供されます。 メモリ内トークン キャッシュは、アプリケーションの実行期間中保持されます。

### キャッシュ内のアカウントを確認する

次の例に示すように、 `PublicClientApplication.getAccounts()` を呼び出すことで、キャッシュ内のアカウントを確認できます。

```java
PublicClientApplication pca = new PublicClientApplication.Builder(
                labResponse.getAppId()).
                authority(TestConstants.ORGANIZATIONS_AUTHORITY).
                build();

Set<IAccount> accounts = pca.getAccounts().join();
```

### キャッシュからアカウントを削除する

キャッシュからアカウントを削除するには、削除する必要があるアカウントを見つけて、次の例に示すように `PublicClientApplication.removeAccount()` を呼び出します。

```java
Set<IAccount> accounts = pca.getAccounts().join();

IAccount accountToBeRemoved = accounts.stream().filter(
                x -> x.username().equalsIgnoreCase(
                        UPN_OF_USER_TO_BE_REMOVED)).findFirst().orElse(null);

pca.removeAccount(accountToBeRemoved).join();
```

### 詳細情報

Javaに MSAL を使用している場合は、[MSAL でのJavaのカスタム トークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/msal-java-token-cache-serialization)について説明します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/msal-java-token-cache-serialization"} -->
## カスタム トークン キャッシュのシリアル化 (MSAL4j) - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/msal-java-token-cache-serialization
- Service: msal / msal-java
- Article date: 2024-01-27
- Summary: MSAL for Javaのトークン キャッシュをシリアル化する方法について説明します

アプリケーションのインスタンス間でトークン キャッシュを保持するには、シリアル化ロジックをカスタマイズする必要があります。 トークン キャッシュのシリアル化に関連するJavaクラスとインターフェイスは次のとおりです。

- [ITokenCache](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.itokencache): セキュリティ トークン キャッシュを表すインターフェイス。
- [ITokenCacheAccessAspect](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.itokencacheaccessaspect) : アクセスの前後にコードを実行する操作を表すインターフェイス。 `@Override`*beforeCacheAccess* と *afterCacheAccess* には、キャッシュのシリアル化とデシリアル化を担当するロジックを実装します。
- [ITokenCacheAccessContext](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.itokencacheaccesscontext) : トークン キャッシュにアクセスするコンテキストを表すインターフェイス。

トークン キャッシュのカスタム シリアル化の単純な実装を次に示します。

Warning

以下のサンプル コードではキャッシュ ストレージのライフサイクル全体が示されないため、運用環境にコピーして貼り付けないことを強くお勧めします。 トークン キャッシュのセキュリティとアクセスの要件を認識していることを確認します。

```java
static class TokenPersistence implements ITokenCacheAccessAspect {
String data;

TokenPersistence(String data) {
        this.data = data;
}

@Override
public void beforeCacheAccess(ITokenCacheAccessContext iTokenCacheAccessContext) {
        iTokenCacheAccessContext.tokenCache().deserialize(data);
}

@Override
public void afterCacheAccess(ITokenCacheAccessContext iTokenCacheAccessContext) {
        data = iTokenCacheAccessContext.tokenCache().serialize();
}
```

```java
// Loads cache from file
String dataToInitCache = readResource(this.getClass(), "/cache_data/serialized_cache.json");

ITokenCacheAccessAspect persistenceAspect = new TokenPersistence(dataToInitCache);

// By setting *TokenPersistence* on the PublicClientApplication, MSAL will call *beforeCacheAccess()* before accessing the cache and *afterCacheAccess()* after accessing the cache. 
PublicClientApplication app = 
PublicClientApplication.builder("my_client_id").setTokenCacheAccessAspect(persistenceAspect).build();
```

### 詳細情報

[Javaの MSAL を使用してトークン キャッシュからアカウントを取得および削除する](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/msal-java-get-remove-accounts-token-cache)方法について説明します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/msal-logging-java"} -->
## Javaの MSAL でのエラーと例外のログ記録 - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/msal-logging-java
- Service: msal / msal-java
- Article date: 2024-02-27
- Summary: JAVAの MSAL でエラーと例外をログに記録する方法について説明します

Microsoft Authentication Library (MSAL) アプリは、問題の診断に役立つログ メッセージを生成します。 アプリでは、数行のコードでログ記録を構成し、詳細レベルと個人データと組織データをログに記録するかどうかをカスタム制御できます。 MSAL ログの実装を作成し、ユーザーが認証の問題がある場合にログを送信する方法を提供することをお勧めします。

### ログ記録のレベル

MSAL には、いくつかのレベルのログの詳細が用意されています。

- `LogAlways`: このログ レベルでは、レベル のフィルター処理は行われません。 すべてのレベルのログ メッセージがログに記録されます。
- `Critical`: 回復不能なアプリケーションまたはシステムのクラッシュ、または直ちに注意が必要な致命的な障害を示すログ。
- `Error`: 問題が発生し、エラーが生成されたことを示します。 問題のデバッグと特定に使用されます。
- `Warning`: エラーや失敗は必ずしも発生していませんが、診断と問題の特定を目的としています。
- `Informational`: MSAL は、必ずしもデバッグを目的としていない情報目的のイベントをログに記録します。
- `Verbose` (既定値): MSAL は、ライブラリの動作の詳細をログに記録します。

Note

すべての MSAL SDK ですべてのログ レベルを使用できるわけではありません。

### 個人データと組織データ

既定では、MSAL ロガーは機密性の高い個人データや組織データをキャプチャしません。 ライブラリには、個人データと組織データのログ記録を有効にするオプションが用意されています (これを行う場合)。

次のセクションでは、アプリケーションの MSAL エラー ログの詳細について説明します。

### Java ログ記録用の MSAL

Java用 MSAL を使用すると、アプリで既に使用しているログ ライブラリを使用できます。これは、[Java用の簡易ログ ファサード (SLF4J)](https://www.slf4j.org/) と互換性がある限りです。 Java用の MSAL では、[java.util.logging](https://docs.oracle.com/javase/7/docs/api/java/util/logging/package-summary.html)、[Logback](https://logback.qos.ch/)、[Log4j](https://logging.apache.org/log4j/2.x/) など、さまざまなログ フレームワークの単純な抽象化として SLF4J が使用されます。 SLF4J を使用すると、ユーザーはデプロイ時に目的のログ 記録フレームワークをプラグインし、デプロイ時に自動的に Logback にバインドできます。 MSAL ログがコンソールに書き込まれます。 この記事では、Spring Boot Web アプリケーションで logback フレームワークを使用して MSAL4J ログを有効にする方法について説明します。

1. ログを実装するには、`logback`に`pom.xml` パッケージを含めます。

    ```xml
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.2.3</version>
    </dependency>
    ```
2. `resources` フォルダーに移動し、`logback.xml`という名前のファイルを追加し、次のコードを挿入します。 これにより、コンソールにログが追加されます。 アペンダー `class` を変更して、任意のファイル、データベース、または任意のアペンダーにログを書き込むことができます。

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <configuration>
        <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
            <encoder>
                <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
            </encoder>
        </appender>
        <root level="debug">
            <appender-ref ref="STDOUT" />
        </root>    
    </configuration>
    ```
3. `logging.config` プロパティを、main メソッドの前の`logback.xml` ファイルの場所に設定します。 `MsalWebSampleApplication.java`に移動し、次のコードを `MsalWebSampleApplication` パブリック クラスに追加します。

    ```java
    @SpringBootApplication
    public class MsalWebSampleApplication {
    
        static { System.setProperty("logging.config", "C:\Users\<your path>\src\main\resources\logback.xml"); }
        public static void main(String[] arrgs) {
            // Console.log("main");
            // System.console().printf("Hello");
            // System.out.printf("Hello %s!%n", "World");
            System.out.printf("%s%n", "Hello World");
            SpringApplication.run(MsalWebSampleApplication.class, args);
        }
    }
    ```

テナントでは、Web アプリと Web API に対して個別のアプリ登録が必要になります。 アプリの登録と Web API スコープの公開については、ユーザーを [認証して Web API を呼び出す Web アプリの](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-overview)シナリオの手順に従います。

他のログ 記録フレームワークにバインドする方法については、 [SLF4J のドキュメントを参照してください](https://www.javadoc.io/doc/org.slf4j/slf4j-api/latest/index.html)。

#### 個人と組織の情報

既定では、MSAL ログは個人または組織のデータをキャプチャまたはログに記録しません。 次の例では、個人または組織のデータのログ記録は既定でオフになっています。

```java
PublicClientApplication app2 = PublicClientApplication.builder(PUBLIC_CLIENT_ID)
        .authority(AUTHORITY)
        .build();
```

クライアント アプリケーション ビルダーで `logPii()` を設定して、個人および組織のデータ ログを有効にします。 個人または組織のデータログを有効にする場合、アプリは機密性の高いデータを安全に処理し、規制要件に準拠する責任を負う必要があります。

次の例では、個人または組織のデータのログ記録が有効になっています。

```java
PublicClientApplication app2 = PublicClientApplication.builder(PUBLIC_CLIENT_ID)
        .authority(AUTHORITY)
        .logPii(true)
        .build();
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/service-to-service-calls"} -->
## サービス間認証 - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/service-to-service-calls
- Service: msal / msal-java
- Article date: 2024-02-27
- Summary: Web API は、ユーザー アサーションを利用して、ユーザーの名前でトークンを取得できます。

Web API は、ユーザー アサーションを利用して、ユーザーの名前でトークンを取得できます。 Web API はユーザー操作を行うことができないため、Web API ("Web API #1" という名前) がユーザーの名前で別の Web API ("Web API #2" という名前) を呼び出す必要がある場合は、 [On Behalf Of OAuth 2.0 フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)を使用する必要があります。

このフローは機密クライアント フローであるため、最初の Web API はクライアント資格情報 (クライアント シークレットまたは証明書) と `UserAssertion`を提供します。 最初の Web API はベアラー トークンを受け取り、それをMicrosoft Entra IDに送信します。これを`UserAssertion`に埋め込んで、ダウンストリームの 2 番目の Web API に別のトークンを要求します。

```java
// This is the confidential client application representing Web Api #1
ConfidentialClientApplication cca =
        ConfidentialClientApplication.builder(clientId, ClientCredentialFactory.create(CLIENT_SECRET)).
                authority(AUTHORITY).
                build();

// Create an UserAssertion with the access token received from the client application 
UserAssertion userAssertion = new UserAssertion(accessToken);

AuthenticationResult result =
        cca.acquireToken(
                OnBehalfOfParameters.builder(
                        Scope,             
                        userAssertion).
                        build()).
                        get();
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/support-for-adfs"} -->
## ADFS のサポート - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/support-for-adfs
- Service: msal / msal-java
- Article date: 2024-02-27
- Summary: Windows Server の Active Directory フェデレーション サービス (AD FS) (AD FS) を使用すると、開発しているアプリケーションに OpenID Connect と OAuth 2.0 ベースの認証と承認を追加できます。

Windows Server の Active Directory フェデレーション サービス (AD FS) (AD FS) を使用すると、開発しているアプリケーションに OpenID Connect と OAuth 2.0 ベースの認証と承認を追加できます。 これらのアプリケーションは、AD FS に対してユーザーを直接認証できます。 詳細については、「 [開発者向け AD FS シナリオ」を](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/ad-fs-development)参照してください。

通常、AD FS に対して認証する方法は 2 つあります。

- MSAL はMicrosoft Entra IDに接続し、AD FS にフェデレーションします。
- MSAL は AD FS 機関に直接接続します。

MSAL4J では、これらの両方のフローがサポートされています。

### MSAL はMicrosoft Entra IDに接続し、AD FS にフェデレーションします

MSAL4J は、マネージド ユーザー (Microsoft Entra ID で管理されているユーザー) またはフェデレーション ユーザー (AD FS などの別の ID プロバイダーによって管理されているユーザー) をサインインさせるMicrosoft Entra IDへの接続をサポートしています。 MSAL4J は、ユーザーがフェデレーションされているという事実を知りません。 それについては、Microsoft Entra ID と通信します。

この場合に使用する権限は、通常の機関 (機関ホスト名 + テナント、共通、または組織) です。

#### フェデレーション ユーザーのトークンを対話形式で取得する

AuthorizationCodeParameters または DeviceCodeParameters を使用して AcquireToken を呼び出すと、ユーザー エクスペリエンスは通常次のようになります。

1. ユーザーが自分のアカウント ID を入力します。
2. Microsoft Entra IDには、"組織のページに移動する" というメッセージが簡単に表示されます。 ユーザーは、ID プロバイダーのサインイン ページにリダイレクトされます。 サインイン ページは通常、組織のロゴでカスタマイズされます。
3. このフェデレーション シナリオでサポートされている AD FS のバージョンは、AD FS v2、AD FS v3 (Windows Server 2012 R2)、AD FS v4 (AD FS 2016) です。

### MSAL が AD FS 機関に直接接続する

MSAL4J は、AD FS 2019 で直接認証するためのサポートを提供します。 この場合、クライアントの初期化時に、MSAL4J に ADFS 固有の機関 URL を指定できます。 権限の値は、 `https://adfs.contoso.com/adfs`のようになります。

### IntegratedWindowsAuthenticationParameters または UsernamePasswordParameters を使用した AcquireToken によるトークンの取得

IntegratedWindowsAuthenticationParameters または UsernamePasswordParameters で AcquireToken を使用してトークンを取得する場合、MSAL4J はユーザー名に基づいて連絡する ID プロバイダーを取得します。 MSAL4J は、ID プロバイダーに接続した後に SAML トークンを受け取ります。 その後、MSAL4J は JWT を取得するために、SAML トークンをユーザー アサーションとして（on-behalf-of フローと同様に）Microsoft Entra ID に提示します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/telemetry"} -->
## テレメトリの構成 - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/telemetry
- Service: msal / msal-java
- Article date: 2024-02-27
- Summary: コールバックを登録して、実行している認証フローからテレメトリを取得できます。

コールバックを登録して、実行している認証フローからテレメトリを取得できます。 コールバックを登録するには、まずテレメトリ イベントを受け取るメソッドを定義します。 テレメトリ イベントは、 `List<HashMap<String, String>>` として返送されます。

```java
public class Telemetry {
    private static List<HashMap<String,String>> eventsReceived = new ArrayList<>();

    public static class MyTelemetryConsumer {

        Consumer<List<HashMap<String, String>>> telemetryConsumer =
                (List<HashMap<String, String>> telemetryEvents) -> {
                    eventsReceived.addAll(telemetryEvents);
                    System.out.println("Received " + telemetryEvents.size() + " events");
                    telemetryEvents.forEach(event -> {
                        System.out.print("Event Name: " + event.get("event_name"));
                        event.entrySet().forEach(entry -> System.out.println("   " + entry));
                    });
                };
     }
}
```

次に、テレメトリ コンシューマーをクライアント アプリケーションに登録します。

```java
PublicClientApplication app = PublicClientApplication.builder(APP_ID)
        .authority(AUTHORITY)
        .telemetryConsumer(new MyTelemetryConsumer().telemetryConsumer)
        .build();
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/advanced/using-wam-and-the-msal4jbrokers-package"} -->
## MSAL Javaでの Web アカウント マネージャーの使用 - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/using-wam-and-the-msal4jbrokers-package
- Service: msal / msal-java
- Article date: 2024-02-27
- Summary: Web アカウント マネージャー (WAM) は、認証ブローカーとして機能する Windows 10+ コンポーネントであり、ユーザーは外部 ID プロバイダーとMicrosoftで簡単に認証できます。

[Web アカウント マネージャー](https://learn.microsoft.com/ja-jp/windows/uwp/security/web-account-manager) (WAM) は、認証ブローカーとして機能する Windows 10+ コンポーネントであり、ユーザーは外部 ID プロバイダーとMicrosoftで簡単に認証できます。 MSAL Javaは、Java アプリケーションからネイティブ API にアクセスしようとする手間を省くために、アプリで WAM の使用を簡単に開始するための簡単な API を提供します。

### 開始する前に

まず、Azure portalを使用して Web アカウント マネージャーを使用するようにアプリケーションを構成する必要があります。 そのためには、アプリケーションに [WAM 互換のリダイレクト URI が](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-wam#redirect-uri)必要です。

次に、メインの `msal4j` パッケージに加えて、プロジェクトには新しい依存関係 [msal4j-brokers](https://mvnrepository.com/artifact/com.microsoft.azure/msal4j-brokers) が必要になります。 このパッケージには、MSAL Javaがユーザーに代わってネイティブ WAM API にアクセスするために必要なすべてのものが含まれています。

### アプリケーションでブローカーを有効にする方法

プロジェクトに依存関係として `msal4j` して `msal4j-brokers` すると、次の 2 つの重要なクラスと 1 つの重要なパラメーターを見つけることができます。

- msal4j における IBroker は、MSAL Java に既にあるものと同様の acquireToken API を備えたインターフェイスです
- `msal4j-brokers` 内の[Broker](https://github.com/AzureAD/microsoft-authentication-library-for-java/blob/dev/msal4j-brokers/src/main/java/com/microsoft/aad/msal4jbrokers/Broker.java)。これは IBroker を実装し、WAM とやり取りする基盤となるコードへのアクセスを提供します。
- msal4j の  ビルダーの`PublicClientApplication` パラメーター。これを使用して、使用する IBroker 実装を MSAL Java指定できます。

アプリケーションで WAM の使用を開始するには、単に `Broker` のインスタンスを `broker` の `PublicClientApplication` パラメーターに渡す必要があります。

```java
//Create broker, indicating that you want it to be used when the app is running on a Windows OS
Broker broker = new Broker.Builder().supportWindows(true).build();

PublicClientApplication pca = PublicClientApplication.builder(clientId)
        .authority(authority)
        .broker(broker) //Add the broker when creating your PublicClientApplication
        .build();
```

そして、それはそれです。 ブローカーが設定されると、MSAL Javaの既存の acquireToken API のいずれかを呼び出すたびに、指定したブローカー (この場合は WAM 用) の使用が試行されます。

### Limitations

すべてのプラットフォームが WAM をサポートしているわけではありません。現在、MSAL Javaでは Windows 上の WAM のみがサポートされています。 WAM がサポートされていない OS でアプリケーションが実行されている場合、MSAL Javaは警告をログに記録し、従来の認証フローにフォールバックします。

さらに、WAM では特定のシナリオのみがサポートされます。 現在サポートされているのはパブリック クライアント シナリオのみで、これらの認証フローに対してのみサポートされています。

| 認証フロー | サポートされている | メモ |
| --- | --- | --- |
| 静か | はい | キャッシュされたトークンを取得する既存の MSAL Java動作と、既定の OS アカウントを使用してサイレント サインインを試行する新しい動作の両方をサポートします |
| Interactive | はい | WAM は現在、ブラウザー タブを開くのではなく、ユーザーが資格情報を入力できる UI ウィンドウをポップアップ表示し、そのウィンドウをユーザーに適切に表示するには、要求の一部としてアプリケーションの [ウィンドウ ハンドルを指定](https://github.com/AzureAD/microsoft-authentication-library-for-java/blob/7b64feac207fb67aeaa21e1bb19d2a3d37f1c359/msal4j-sdk/src/main/java/com/microsoft/aad/msal4j/InteractiveRequestParameters.java#L103) する必要があります。 アプリケーションがコンソール ベースの場合は、ウィンドウ ハンドルを検出できる必要があります。ただし、アプリケーションに複数のカスタム UI 要素が含まれている場合は、問題を回避するためにウィンドウ ハンドルを指定する必要があります (他のウィンドウの背後にポップアップが表示される、関連のない UI 要素がブロックされるなど)。 |
| ユーザー名/パスワード | はい | このフローは、近い将来 MSAL Javaで非推奨になる可能性があり、msal4j-brokers では既に非推奨としてマークされています |

### ログ記録と例外

MSAL Java の API は単純ですが、基になる機能は、いくつかのJavaおよびネイティブ パッケージによって処理されます。

例外とエラーは、このチェーンのどの部分からも発生する可能性があります。ただし、MSAL Javaは、可能な限りログと例外にコンテキストを追加しようとします。

MSAL Javaは、操作するネイティブ API からログを転送して WAM にアクセスすることもできます。この動作は切り替えることができます。

```java
Broker broker = ...;

//Allow MSAL to forward all logs from WAM
broker.enableBrokerLogging(true);

//Allow PII to appear in WAM logs
broker.enableBrokerPIILogging(true);
```

既定では、MSAL Javaはこれらのログを転送しないため、非常に詳細になり、デバッグ時に主に役立ちます。

### 追加の詳細

注: このセクションは WAM または msal4j-brokers を使用するために必要ではなく、関係するすべてのコンポーネントで明確さと背景を追加することを目的としたものです。

ブローカーを有効にする API は非常に単純ですが、この機能を提供するために多くのパッケージが使用されます。

- [msal4j-brokers](https://mvnrepository.com/artifact/com.microsoft.azure/msal4j-brokers) - 基本的に、msal4j と javamsalruntime の間の薄いレイヤー。msal4j からの要求と javamsalruntime からの結果の間の変換を処理することを意味します
- [javamsalruntime](https://mvnrepository.com/artifact/com.microsoft.azure/javamsalruntime) - [JNA](https://mvnrepository.com/artifact/com.microsoft.azure/javamsalruntime) を使用してネイティブ コードを呼び出し、Javaクラスと変数を C#/C++ に変換し、その逆を行うJava プロジェクト
- MSALRuntime - WAM とその他の多くの機能、および最終的には他の認証ブローカーにアクセスするための API を提供する C++/C# プロジェクト。 このプロジェクトは、javamsalruntime が JNA を介して呼び出す dll パッケージを生成し、msal4j ブローカーがサポートできるシナリオの最終的な決定者です
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/build/fiddler"} -->
## MSAL Javaでの Fiddler の使用 - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/build/fiddler
- Service: msal / msal-java
- Article date: 2024-01-27
- Summary: MSAL4J を Fiddler などのプロキシと共に使用して、要求と応答をデバッグできます。

MSAL4J を [Fiddler](https://www.telerik.com/fiddler) などのプロキシと共に使用して、要求と応答をデバッグできます。

### 1. キーストア ファイルを設定する

- Fiddler から、ツール -&gt; オプション -&gt; HTTPS -&gt; アクション ボタンを使用してFiddlerRoot.cerをデスクトップにエクスポートします
- 管理者としてコマンド プロンプトを開く
- JDK の keytool を実行してキーストアを作成する

`<JDK_HOME>\bin\keytool.exe -import -file C:\Users\<username>\Desktop\FiddlerRoot.cer^ -keystore FiddlerKeystore -alias Fiddler`

- キーストアのパスワードを入力します。 後で必要になるので、このことを忘れないでください。

### 2a. IntelliJ を設定する

- IntelliJ で、実行/デバッグ構成を開きます
- "Fiddler Trace" という名前の新しいデバッグ構成を作成する
- 次の VM オプションを追加します。

`-DproxySet=true -DproxyHost=127.0.0.1 -DproxyPort=8888 -Djavax.net.ssl.trustStorePassword="yourpassword" -Djavax.net.ssl.trustStore="path\to\keystore\FiddlerKeystore"`

#### 2b. Eclipse を設定する

- Eclipse で、[Run -&gt; Run Configurations]\(実行-実行構成\) を開きます
- 使用する実行構成を選択します
- [引数] タブを選択する
- 次の引数を追加します。

`-DproxySet=true -DproxyHost=127.0.0.1 -DproxyPort=8888 -Djavax.net.ssl.trustStore="path\to\keystore\FiddlerKeystore" -Djavax.net.ssl.trustStorePassword="yourpassword"`
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/build/known-issues"} -->
## 既知の問題 - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/build/known-issues
- Service: msal / msal-java
- Article date: 2024-01-27
- Summary: このページでは、MSAL4J が依存している依存関係やサービスに見られる問題など、MSAL4J の外部に存在する既知の問題と、それらの問題が解決されるまで使用する可能性のある回避策について説明します。

このページでは、MSAL4J が依存している依存関係やサービスに見られる問題など、MSAL4J の外部に存在する既知の問題と、それらの問題が解決されるまで使用する可能性のある回避策について説明します。

### B2C

#### B2C 使用時にアクセス トークンが見つからない

原因: MSAL4J には、 [OpenID Connect](https://openid.net/specs/openid-connect-core-1_0.html#TokenResponse) と [OAuth 2.0](https://tools.ietf.org/html/rfc6749#section-4.1.4) の仕様に従って、承認サーバーが有効な要求への応答で常にアクセス トークンを返すという前提があります。 ただし、カスタム スコープが要求に含まれている場合にのみ B2C サービスが [アクセス トークンを返](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/openid-connect#get-a-token) すので、特定の B2C フロー シナリオでは MSAL4J で例外が発生します。

回避策: B2C 側でこの問題が解決されるまでは、トークン要求にカスタム スコープを含めることで、MSAL4J の例外を回避できます。 B2C ドキュメントでは、アプリケーション [のクライアント ID をスコープとして使用することをお勧めします](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/openid-connect#get-a-token) 。

詳細については [、この問題](https://github.com/AzureAD/microsoft-authentication-library-for-java/issues/140) を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/build/maven"} -->
## Maven を使用したビルド - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/build/maven
- Service: msal / msal-java
- Article date: 2024-01-27
- Summary: maven を使用してビルドできるようにするには、Javaと Maven の作業インストールが必要です。

maven を使用してビルドできるようにするには、[Java](https://www.oracle.com/technetwork/java/javase/downloads/index.html)と Maven の作業インストールが必要[です](https://maven.apache.org/download.cgi)。

Javaと Maven を正常にインストールしたら、microsoft-authentication-library-for-java リポジトリを複製します。

シェルまたはコマンド ラインから:

- `$ git clone https://github.com/AzureAD/microsoft-authentication-library-for-java.git`
- `$ cd microsoft-authentication-library-for-java`

次に、以下を実行します。

- `mvn clean`
- `mvn package`

これで、 `msal4j-x.x.x.jar`を含む "ターゲット" ディレクトリが作成されます。

インストールするには、次のコマンドを実行します。

- `mvn install -DskipITs`

### テストの実行

プロジェクトのテスト スイートを実行するには、次のコマンドを実行します。

- `mvn test`
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/getting-started/acquiring-tokens"} -->
## トークンを取得する - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/acquiring-tokens
- Service: msal / msal-java
- Article date: 2024-02-27
- Summary: 一般にトークンを取得する方法は、アプリケーションの種類 (パブリック クライアント アプリケーション (デスクトップ/モバイル) または機密クライアント アプリケーション (Web アプリ、Web API、Windows サービスなどのデーモン アプリケーション) によって異なります。

[Javaシナリオの MSAL](https://learn.microsoft.com/ja-jp/entra/msal/java/#msal-java-scenarios) で説明したように、トークンを取得する方法は多数あります。 一部では、Web ブラウザーを介したユーザー操作が必要です。 一部はユーザーの操作を必要としません。

一般にトークンを取得する方法は、アプリケーションの種類 (パブリック クライアント アプリケーション (デスクトップ/モバイル) または機密クライアント アプリケーション (Web アプリ、Web API、Windows サービスなどのデーモン アプリケーション) によって異なります。

### 前提条件

MSAL4J でトークンを取得する前に、[クライアント アプリケーション](https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/client-applications)をインスタンス化してください

### トークンの取得方法

各トークン取得方法の MSAL4J コード使用法の詳細については、以下のトピックに従ってください。

#### パブリック クライアント アプリケーション

- [システム ブラウザーを使用して対話形式でトークンを](https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/acquiring-tokens-interactively)取得する
- [ユーザーが承認](https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/acquiring-tokens-with-authorization-codes)要求 URL を使用してサインインした後、承認コードによってトークンを取得します。
- [ユーザー名とパスワード](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token?tabs=java#username--password)を使用してトークンを取得することもできます。(このフローは非推奨になりました)
- Windowsマシンで実行され、ドメインまたはMicrosoft Entra IDに参加しているアプリケーションの場合は、[統合Windows認証 (IWA)](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/integrated-windows-authentication) を利用して、トークンをサイレントで取得できます。
- 最後に、Web ブラウザーがないデバイスで実行されているアプリケーションの場合は、 [デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/device-code-flow)を通じてトークンを取得できます。これにより、ユーザーに URL とコードが提供されます。 ユーザーは別のデバイス上の Web ブラウザーに移動し、コードを入力してサインインした後、Microsoft Entra IDブラウザーのないデバイスにトークンを返します。

#### 機密クライアント アプリケーション

- ユーザーではなく、**クライアント資格情報**を使用して、[アプリケーション自体として](https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/client-credentials)トークンを取得します。 たとえば、ユーザーをバッチ処理し、同期ツールなどの特定のユーザーを処理しないアプリなどです。
- Web Apps または Web API が**ユーザーに代わって別のダウンストリーム Web API を呼び出す**場合は、[On Behalf Of フロー](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/service-to-service-calls)を使用して、ユーザー アサーション（たとえば SAML や JWT トークン）に基づくトークンを取得します。
- **ユーザーの名前の Web アプリの場合**は、承認要求 URL を介してユーザーがサインインできるようにした後、承認 [コード](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-app-call-api-acquire-token?tabs=java) によってトークンを取得します。 これは通常、OpenID Connect を使用してユーザーがサインインできるアプリケーションで使用されるメカニズムですが、この特定のユーザーの Web API にアクセスしたいと考えています。

### MSAL4J はトークンをキャッシュします

パブリック クライアント アプリケーションと社外秘クライアント アプリケーションの両方で、MSAL はトークン キャッシュを保持し、アプリケーションは他の手段の前に最初にキャッシュからトークンを取得する必要があります ( [クライアント資格情報](https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/client-credentials)の場合は、キャッシュ自体を調べます)。 トークンの取得に [推奨されるパターン](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token?tabs=java) を見てみましょう。

キャッシュを利用できるようにするには、アプリケーションで [トークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/msal-java-token-cache-serialization)をカスタマイズする必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/getting-started/acquiring-tokens-interactively"} -->
## MSAL Javaで対話形式でトークンを取得する - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/acquiring-tokens-interactively
- Service: msal / msal-java
- Article date: 2024-02-27
- Summary: MSAL Javaでは、システム OS ブラウザーを使用してパブリック クライアントで対話形式でトークンを取得できます。

### システム ブラウザー

MSAL では、システム ブラウザーを使用してパブリック クライアントで対話形式でトークンを取得できます。 既定では、MSAL は別のプロセスとしてシステム ブラウザーを起動し、ユーザーを承認 URL に誘導し、承認応答をインターセプトします。 対話型要求の パラメーターを構成することで、ブラウザー ウィンドウを開く方法や承認後にユーザーに表示される内容など、`SystemBrowserOptions`できます。

MSAL は `http://localhost:port` をリッスンし、ユーザーの認証が完了したときに機関が送信するコードをインターセプトします。 MSAL は、ユーザーが移動したかどうか、または単にブラウザーを閉じるかどうかを検出できません。 この手法を使用するアプリでは、タイムアウトを定義することをお勧めします。 パスワードの変更または 2FA の実行を求めるメッセージが表示される場合は、少なくとも数分のタイムアウトを考慮することをお勧めします。

### 既定の OS ブラウザーを使用する方法

アプリの登録時に、リダイレクト URI として `http://localhost` を構成します。 B2C の場合は、特定のポート `http://locahost:port`を登録する必要があります。

```java
PublicClientApplication publicClientApplication =
        PublicClientApplication
                .builder(CLIENT_ID)
                .authority(AUTHORITY)
                .build();

InteractiveRequestParameters parameters = InteractiveRequestParameters
        .builder(new URI("http://localhost"))
        .scopes(scope)
        .build();

IAuthenticationResult result = publicClientApplication.acquireToken(parameters).join();
```

### エクスペリエンスのカスタマイズ

MSAL は、既定のシステム ブラウザー (使用可能な場合) をユーザー コンピューターで開こうとします。 独自の実装を提供することで、このロジックをカスタマイズできます。 `OpenBrowserAction`

- `OpenBrowserAction` を実装する
- カスタムアクションを `SystemBrowserOptions` に渡す

```java
class CustomOpenBrowserAction implements OpenBrowserAction {
    @Override
    public void openBrowser(URL url){
            //Custom logic to open URL 
    }
}

SystemBrowserOptions options =  
        SystemBrowserOptions
                .builder()
                .openBrowserAction(new CustomOpenBrowserAction())
                .build();
```

認証後にユーザーに表示される HTML メッセージをカスタマイズしたり、認証後にユーザーをリダイレクトする URL を指定したりすることもできます。

```java
SystemBrowserOptions options =  
        SystemBrowserOptions
                .builder()
                .htmlMessageSuccess("CUSTOM_HTML_HERE")
                .htmlMessageError("CUSTOM_HTML_HERE")
                .build();
// OR

SystemBrowserOptions options =
        SystemBrowserOptions
                .builder()
                .browserRedirectSuccess(new URI("http://localhost:port"))
                .browserRedirectError(new URI ("http://localhost:port"))
                .build();
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/getting-started/acquiring-tokens-with-authorization-codes"} -->
## 承認コードを使用してトークンを取得する - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/acquiring-tokens-with-authorization-codes
- Service: msal / msal-java
- Article date: 2024-02-27
- Summary: 認証コード フローは、認証時にユーザーが Microsoft Entra STS と対話する必要がある場合に適しています。

認証コード フローは、認証時にユーザーが Microsoft Entra STS と対話する必要がある場合に適しています。 このようなケースの 1 つは、ユーザーが OpenID Connect を使用して Web アプリケーション (Web サイト) にログインする場合です。 Web アプリケーションは、Web API のトークンを取得するために使用できる承認コードを受け取ります。

承認コードの要求は開発者に委任されます。 承認コードを要求する方法については、承認 [コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)を参照してください。 ユーザーが資格情報を入力する承認コード URL を作成するには、[承認コード URL ビルダー](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/authorization-code-url-builder)を使用できます。

### コードスニペット

```java
PublicClientApplication pca = new PublicClientApplication.Builder(APP_ID)
        .authority(AUTHORITY)
        .build();

IAuthenticationResult result = pca.acquireToken(AuthorizationCodeParameters
        .builder(authCode, new URI(REPLY_URL))
        .scopes(scope)
        .build())
        .get();
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/getting-started/acquiring-tokens-with-username-and-password"} -->
## ユーザー名とパスワードを使用してトークンを取得する - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/acquiring-tokens-with-username-and-password
- Service: msal / msal-java
- Article date: 2024-02-27
- Summary: MSAL4J では、パブリック クライアント アプリケーションのユーザー名とパスワード のフローがサポートされます。

Warning

セキュリティ リスクのため、リソース所有者パスワード資格情報 (ROPC) フローは非推奨になりました。 Microsoft では、より安全な認証フローを使用することをお勧めします。 [ROPC から移行](https://aka.ms/msal-ropc-migration)する方法に関する公式ガイダンスに従ってください。

MSAL4J では、パブリック クライアント アプリケーションのユーザー名とパスワード のフローがサポートされます。 一般に、Microsoftは、他のフローよりも安全性が低いため、ユーザーに使用するよう勧めるものではありません。また、条件付きアクセスと互換性がありません。これは、リソースに条件付きアクセスが必要な場合、トークンを取得するための呼び出しは、対話型フローではないことを考えると失敗します (STS には、複数要素認証を行う必要があることをユーザーに伝えるダイアログを表示する機会がありません)。

Windowsドメイン参加済みマシンでトークンをサイレントモードで取得するための推奨フローは、[統合Windows認証です](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/integrated-windows-authentication)。 それ以外の場合は、[デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/device-code-flow)を使用することもできます

Note

これは場合によっては便利ですが (DevOps シナリオ)、独自の UI を提供する対話型シナリオでユーザー名/パスワードを使用する場合は、そこから離れる方法を実際に検討する必要があります。 ユーザー名/パスワードを使用すると、次のような多くのことがあきらめられます。

- 現代のアイデンティティの中核原則: パスワードはフィッシングされ、リプレイされる。 これは、インターセプトできる共有シークレットのこの概念があるためです。 これはパスワードレスと互換性がありません。
- MFA を実行する必要があるユーザーはサインインできません (操作がないため)
- ユーザーはシングル サインオンを実行できません

```java
final String AUTHORITY;
final String APP_ID;
String userName;
String password;
List<String> scopes;

PublicClientApplication pca = new PublicClientApplication.Builder(APP_ID).
        authority(AUTHORITY).
        build();

UserNamePasswordParameters paramaters = 
                UserNamePasswordParameters.builder(
                    scopes,
                    userName,
                    password.toCharArray()).build();

IAuthenticationResult result = pca.acquireToken(parameters).get();
```

この許可の使用を避けたい理由の詳細については、パスワードを[過去のものにするためにMicrosoftが取り組んでいる](https://news.microsoft.com/features/whats-solution-growing-problem-passwords-says-microsoft/)理由について説明します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/getting-started/client-applications"} -->
## クライアント アプリケーション - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/client-applications
- Service: msal / msal-java
- Article date: 2024-02-27
- Summary: MSAL Javaを使用してクライアント アプリケーションの構成を開始する方法。

### アプリケーションをインスタンス化する

#### 前提条件

MSAL4J を使用してアプリをインスタンス化する前に、次の手順を実行します。

1. 使用可能なクライアント アプリケーションの種類 ( [パブリック クライアント アプリケーションと機密クライアント アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-applications)) について説明します。
2. アプリケーションをMicrosoft Entra IDに[登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)する必要があります。 したがって、次のことが分かります。
    - その `clientID` (GUID を表す文字列)
    - アプリケーションの ID プロバイダー URL (インスタンスという名前) とサインイン対象ユーザー。 これら 2 つのパラメーターは、総称して機関と呼ばれます。
    - おそらく、自分の組織専用の基幹業務アプリケーション (シングルテナント アプリケーションとも呼ばれます) を作成している場合の `TenantID`
    - 機密クライアント アプリ、そのアプリケーション シークレット (`clientSecret` 文字列) または証明書の場合
    - Web アプリの場合は、ID プロバイダーがセキュリティ トークンを使用してアプリケーションに連絡する `redirectUri` も設定します。

#### パブリック クライアント アプリケーションをインスタンス化する

```java
String PUBLIC_CLIENT_ID;
String AUTHORITY;

PublicClientApplication app = 
    PublicClientApplication
        .builder(PUBLIC_CLIENT_ID)
        .authority(AUTHORITY)
        .build();
```

#### Confidential Client アプリケーションをインスタンス化する

[クライアント資格情報](https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/client-credentials)の説明に従って、シークレットまたは証明書が必要です。

シークレットがある場合:

```java
String PUBLIC_CLIENT_ID;
String AUTHORITY;
String CLIENT_SECRET;

IClientCredential credential = ClientCredentialFactory.createFromSecret(CLIENT_SECRET);
ConfidentialClientApplication app = 
    ConfidentialClientApplication
        .builder(PUBLIC_CLIENT_ID, credential)
        .authority(AUTHORITY)
        .build();
```

証明書がある場合:

```java
String PUBLIC_CLIENT_ID;
String AUTHORITY;
PrivateKey PRIVATE_KEY;  
X509Certificate PUBLIC_KEY;

IClientCredential credential = ClientCredentialFactory.createFromCertificate(PRIVATE_KEY, PUBLIC_KEY);
ConfidentialClientApplication app = 
    ConfidentialClientApplication
        .builder(PUBLIC_CLIENT_ID, credential)
        .authority(AUTHORITY)
        .build();
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/getting-started/client-credentials"} -->
## MSAL Javaのクライアント資格情報 - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/client-credentials
- Service: msal / msal-java
- Article date: 2024-02-27
- Summary: MSAL Javaでは、アプリケーション シークレットと証明書という 2 種類のクライアント資格情報がサポートされています。

MSAL4J には、次の 3 種類のクライアント シークレットがあります。

- アプリケーション シークレット
- 証明書
- クライアント アサーション

### MSAL4J のアプリケーション シークレットを使用したクライアント資格情報

Microsoft Entra IDを使用して機密クライアント アプリケーションを登録すると、クライアント シークレット (アプリケーション パスワードの一種) が生成されます。 クライアントが独自の名前でトークンを取得する場合、次のようになります。

- `IClientCredential`を使用して`ClientCredentialFactory`を作成し、文字列にする必要があるクライアント シークレットを渡します。

```java
String CLIENT_SECRET; 
IClientCredential credential = ClientCredentialFactory.createFromSecret(CLIENT_SECRET)
```

### 証明書を使用したクライアント資格情報

この場合、アプリケーションがMicrosoft Entra IDに登録されると、証明書の公開キーがアップロードされます。 クライアント アプリケーションがトークンを取得しようとする場合は

- `IClientCredential`を使用して`ClientCredentialFactory`を作成し、公開キーと秘密キーの両方または pkcs12 の InputStream を渡します

```java
PrivateKey privateKey;  
X509Certificate publicKey;  
IClientCredential credential = ClientCredentialFactory.createFromCertificate(privateKey, publicKey)
```

または

```java
InputStream inputStream;  
String password;  
IClientCredential credential = ClientCredentialFactory.create(inputStream, password)
```

- 次に、機密クライアント アプリケーションを作成し、クライアント資格情報を渡します。

```java
ConfidentialClientApplication app =
                    ConfidentialClientApplication.builder(
                        CLIENT_ID,
                        credential)
                .build();
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/getting-started/device-code-flow"} -->
## デバイス コード フロー - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/device-code-flow
- Service: msal / msal-java
- Article date: 2024-02-27
- Summary: Microsoft Entra ID による対話型認証には Web ブラウザーが必要です。 ただし、Web ブラウザーを提供しないデバイスやオペレーティング システムの場合、デバイス コード フローを使用すると、ユーザーは別のデバイス (別のコンピューターや携帯電話など) を使用して対話形式でサインインできます。

Microsoft Entra ID による対話型認証には Web ブラウザーが必要です。 ただし、Web ブラウザーを提供しないデバイスやオペレーティング システムの場合、デバイス コード フローを使用すると、ユーザーは別のデバイス (別のコンピューターや携帯電話など) を使用して対話形式でサインインできます。 アプリケーションは、デバイス コード フローを使用して、特にこれらのデバイス/OS 用に設計された 2 段階のプロセスを通じてトークンを取得します。 このようなアプリケーションの例としては、IoT で実行されているアプリケーションや、Command-Line ツール (CLI) などがあります。

一般的なデバイス コード フローは、次の手順に従います。

1. ユーザー認証が必要な場合は常に、アプリによってユーザーのコードが提供されます。 ユーザーは、インターネットに接続されたスマートフォンなどの別のデバイスを使用して URL (たとえば、 `https://microsoft.com/devicelogin`) に移動し、コードを入力するように求められます。 これにより、Web ページは通常の認証エクスペリエンスを通じてユーザーを誘導します。これには、必要に応じて同意プロンプトと多要素認証が含まれます。
2. 認証が成功すると、コマンド ライン アプリはバック チャネルを介して必要なトークンを受け取り、それらを使用して必要な Web API 呼び出しを実行します。

### 制約

- デバイス コード フローは、パブリック クライアント アプリケーションでのみ使用できます
- `PublicClientApplication`で渡される権限は、次のいずれかのオプションとして構成する必要があります。
    - `https://login.microsoftonline.com/{tenant}/` の形式のテナント。ここで、`tenant` はテナント ID を表す GUID、またはテナントに関連付けられたドメインのいずれかです。
    - すべての勤務先または学校のアカウント (`https://login.microsoftonline.com/organizations/`)

>
> Microsoft個人アカウントは、Azure AD v2.0 エンドポイントでまだサポートされていません (`/common`または`/consumers`テナントを使用することはできません)。

### コードスニペット

```java
PublicClientApplication app = PublicClientApplication.builder(PUBLIC_CLIENT_ID)
        .authority(AUTHORITY)
        .build();

Consumer<DeviceCode> deviceCodeConsumer = (DeviceCode deviceCode) -> {
    System.out.println(deviceCode.message());
};

CompletableFuture<IAuthenticationResult> future = app.acquireToken(
        DeviceCodeFlowParameters.builder(scope, deviceCodeConsumer).build());

future.handle((res, ex) -> {
    if(ex != null) {
        System.out.println("message - " + ex.getMessage());
        return "Unknown!";
    }
    System.out.println("Access Token - " + res.accessToken());
    System.out.println("ID Token - " + res.idToken());
    return res;
});

future.join();
```

### 追加情報

デバイス コード フローの詳細を確認する場合:

- [OAuth 標準 - デバイスフロー](https://tools.ietf.org/html/draft-ietf-oauth-device-flow-07#section-3.4)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/getting-started/faq"} -->
## よく寄せられる質問 - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/faq
- Service: msal / msal-java
- Article date: 2024-02-27
- Summary: MSAL Javaについてよく寄せられる質問がいくつかあります。

### MSAL4J スコープ

#### MSAL の主な機能は何ですか?

クライアント アプリケーションが保護されたリソースにアクセスするためのセキュリティ トークン サービス (STS) からトークンを取得する。

#### MSAL4J とは

MSAL は、多くのプログラミング言語とプラットフォームで使用できます。 MSAL4J は、Java仮想マシン上で実行されるすべてのアプリケーションで使用するように設計されています。

#### トークンの取得に関して MSAL はどのような標準プロトコルに従いますか?

MSAL は、OAuth2 プロトコルのカスタム バージョンを実装しています。 また、一部の特定のシナリオでは、内部的に他のプロトコル (WS-Trustなど) を使用する場合があります。

#### MSAL は、OAuth2 プロトコルを使用したトークン取得用の一般的なライブラリですか?

No. MSAL は、Microsoft Entra ID、Active Directory フェデレーション サービス (AD FS) (ADFS)、および B2C Azure Active Directory用のクライアント ライブラリです。 一般的な OAuth2 プロトコル 仕様の拡張機能と見なされ、他の STS ではサポートされていない、ADAL に必要な "リソース" などのカスタム概念がいくつかあります。

### APIの拡大

#### コンストラクターに false を渡して権限の検証をオフにする必要がありますか?

どの種類の当局者と話をするかによって異なります。 ADFS の場合、ADFS は現在機関の検証をサポートしていないため、false を渡す必要があります。 Microsoft Entra IDの場合でも、false を渡すオプションがありますが、特に第三者から権限のアドレスを取得する場合は true にすることをお勧めします (例: 401 チャレンジ)。 これは、アプリケーションとユーザーが悪意のあるエンドポイントにリダイレクトされて資格情報を入力しないように保護するためです。

#### AcquireToken のどのオーバーロードを呼び出すべきですか?

使用するクライアント アプリケーションの種類と、トークンが必要なシナリオによって異なります。 [トークンの取得](https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/acquiring-tokens)に関する記事に記載されているガイダンスを参照してください。

### Debugging

#### MSAL を使用できない一般的な理由は何ですか?

MSAL の問題には、さまざまな理由が考えられます。 一般的な原因は次のとおりです。

1. コンピューターに接続の問題があります。
2. アプリケーション/ユーザーが Microsoft Entra ID または ADFS で正しく構成されていません。
3. タスクに不適切な API を使用しています (MSAL には、AcquireToken メソッドに似たオーバーロードがいくつかあります)。
4. MSAL にバグがあります。 はい。これは常に可能です。 上記のどの項目もエラーの原因でないと確信している場合は、Microsoft に報告してください。バグが存在する場合は調査および修正します。

#### ADAL で問題を診断するために使用できるツールは何ですか?

使用できる診断ツールがいくつかあります。

1. MSAL サンプル: 最初の最適なツールは、MSAL と共に公開されたサンプルのセットです ([ライブラリ リポジトリ内](https://github.com/AzureAD/microsoft-authentication-library-for-java/tree/dev/msal4j-sdk/src/samples)と[、AzureSamples GitHub組織](https://github.com/AzureSamples)で利用可能なサンプルが投稿されています)。 アプリケーションに最も近いサンプルを見つけて、コンピューター上でダウンロードして実行してみてください。 サンプルが正常に動作する場合は、アプリケーション内のサンプル アプリと同じ手順に従う必要があります。
2. MSAL 診断ログ: ログ記録を有効にすることができます。 これにより、MSAL の内部ステップに関する情報を含むいくつかのログが書き込まれます。 ログを分析して問題を見つけることができます。 また、MSAL チームに問い合わせる場合は、分析に役立つログを送信する必要があります。 MSAL ログを有効にする方法の手順については[、公式ドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/msal-logging-java)。
3. ネットワーク トレース: MSAL がサーバーと行うすべての http 通信を記録するために [Fiddler などのツールを使用](https://learn.microsoft.com/ja-jp/entra/msal/java/build/fiddler) します。 Fiddler の使用は、Windowsデスクトップ コンピューターでは特に簡単です。 問題の診断に関与している場合は、ネットワーク トレース ファイルを MSAL チームと共有してください。

#### MSAL からどのような種類のエラーが例外として返され、どのような種類がユーザーに報告されますか?

ほとんどのエラーは、例外の形式で MSAL から返されます。ただし、MSAL がブラウザー コントロールにエラーを表示するケースは限られています。 これらのケースは、クライアントを検証できない場合や、機関サーバーに到達できない場合に発生します。

#### MSAL には、内部に何らかの再試行ロジックがありますか?

No. 操作が失敗した場合、MSAL は例外を介してエラーを報告します。 例外には、エラー コードと、機関からエラーが返された場合の状態コードも含まれます。 このような場合は、例外の状態コード (主に応答の http 状態コードを反映) を調べて、再試行するかどうかを決定するのは開発者の仕事です。 通常、502 は再試行を保証する状態コードです。

### MSAL リリースモデル

#### MSAL はどのくらいの頻度で新しいバージョンをリリースしますか?

事前に決定されたスケジュールはありません。 Microsoft では、バグを修正し、お客様のブロックを解除するために、サービス リリースを非常に定期的に公開しようとします。 メジャー リリースには通常時間がかかり、メジャー バージョンの一般提供前にいくつかのプレビュー バージョンがリリースされます。

#### MSAL バージョンの互換性モデルは何ですか?

目標は、メジャー バージョン内で下位互換性を維持することです。 そのため、バグの修正やサービス リリースの新機能の追加のみを試みます (マイナー バージョンが増加します)。 ただし、メジャー バージョン間の互換性の保証はありません。 特定のプラットフォームまたはシナリオのサポートを追加または削除する場合があるため、運用環境のコードに切り替える前に、変更の範囲を完全に理解し、新しいバージョンを完全にテストすることをお勧めします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/getting-started/using-the-acquired-token-to-call-a-protected-web-api"} -->
## 取得したトークンを使用して保護された API を呼び出す - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/using-the-acquired-token-to-call-a-protected-web-api
- Service: msal / msal-java
- Article date: 2024-02-27
- Summary: MSAL4J で取得したトークンを使用して、セキュリティで保護された API を呼び出す方法。

### トークンの使用

トークンを取得することはそれ自体の目標ではありません。 これは、保護された API を呼び出すために必要な手順です。 トークンは、Web API にアクセスするために使用する必要があります。 これを行う方法は、Authorization ヘッダーを "Bearer" に設定し、その後にスペースを続けてアクセス トークンを設定することです。

### アクセス トークンを使用して保護された Web API を呼び出す

次のコードは、HttpURLConnection を使用して Web API を直接呼び出す方法を示しています。 アクセス トークン (DocumentDb など) のみを必要とし、ヘッダーの詳細を管理するライブラリを使用することもできます。 実際には、使用するライブラリに応じてコードが変更される場合があります。

```java
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestProperty("Authorization", "Bearer " + accessToken);
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/java/getting-started/why-use-msal4j"} -->
## MSAL4J を使用する理由 - Microsoft Authentication Library for Java

- Source: https://learn.microsoft.com/ja-jp/entra/msal/java/getting-started/why-use-msal4j
- Service: msal / msal-java
- Article date: 2024-02-27
- Summary: MSAL4J を使用してユーザーを認証するタイミングを決定します。

Java 用 Microsoft Authentication Library (msAL for Javaまたは MSAL4J) を使用すると、開発者は**セキュリティで保護された Web API を呼び出すために[トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-glossary#security-token)を取得できます**。 これらの Web API には、Microsoft Graph、他のMicrosoft API、サード パーティの Web API、または独自の Web API が含まれます。

### 複数のアプリケーション アーキテクチャ

MSAL for Javaでは、次のようなすべての可能なアプリケーション トポロジがサポートされます。

- ユーザーの名前で API (Microsoft Graph など) を呼び出す[ネイティブ クライアント](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-glossary#native-client) (デスクトップ アプリケーション)。
- デーモン/サービスまたは [Web クライアント](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-glossary#web-client)（Web アプリ/Web API）が、ユーザーに代わって、またはユーザーなしで、Microsoft Graph などの他の API を呼び出す。

MSAL4J は、JavaScript でのみサポートされている [ユーザー エージェント ベースのクライアント](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-glossary#user-agent-based-client)をサポートしていません。

サポートされているシナリオの詳細については、 [入門セクションを](https://learn.microsoft.com/ja-jp/entra/msal/java/#msal-java-scenarios)参照してください。

### 汎用ライブラリに対する MSAL4J の値

MSAL4J はトークン取得ライブラリです。 シナリオに応じて、トークンを取得するさまざまな方法が提供され、多数のプラットフォームで一貫した API が提供されます。

また、次の方法で値を追加します。

- **トークン キャッシュ**を維持し、有効期限が近づくと**トークンを自動的に更新**します。
- アプリケーションでサインインする**対象ユーザー** (組織、複数の組織、職場と学校、Microsoftの個人アカウント、Azure AD B2C を使用したソーシャル ID、ソブリン クラウドと国内クラウドのユーザー) を指定するのに役立ちます。
- アクション可能な例外、ログ記録、テレメトリを公開することで、アプリの**トラブルシューティングに役立ちます**。

### トークンの取得

MSAL4J はトークンの取得に使用されます。 Web API の保護には使用されません。 Microsoft Entra IDを使用した Web API の保護に関心がある場合は、次のリソースを確認してください。

- [Microsoft Entra ID 向け Spring Starter](https://learn.microsoft.com/ja-jp/azure/developer/java/spring-framework/spring-boot-starter-for-azure-active-directory-developer-guide?tabs=SpringCloudAzure4x)
- [トークンの手動検証](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens#validating-tokens)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript"} -->
## JavaScript 用のMicrosoft認証ライブラリの概要 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript
- Service: msal / msal-js
- Article date: 2026-03-15
- Summary: JavaScript 用のMicrosoft認証ライブラリの概要

JavaScript 用 Microsoft Authentication Libraryを使用すると、クライアント側とサーバー側の JavaScript アプリケーションの両方で、職場と学校のアカウント、Microsoft個人用アカウント (MSA)、ソーシャル ID プロバイダーなどの[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview)を使用してユーザーを認証できます。Azure [AD B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/active-directory-b2c-overview#identity-providers) サービスを通じて、Facebook、Google、LinkedIn、Microsoft アカウントなど。 また、アプリがトークンを取得して、[Microsoft Graph](https://www.microsoft.com/enterprise)などの[Microsoft Cloud](https://graph.microsoft.com) サービスにアクセスすることもできます。

### コア ライブラリとラッパー ライブラリ

[`lib`](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib) フォルダーには、開発中の MSAL.js ライブラリのソース コードが含まれています。 また、ライブラリの **インストール** に関するすべての詳細は、それぞれの README.md ファイルにも表示されます。

- [Node.js用Microsoft認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/initialize-public-client-application) (v5.0.6):JavaScript アプリケーションのMicrosoft ID プラットフォームで認証とトークンの取得を可能にする [Node.jsライブラリ。](https://nodejs.org/en/) 次の OAuth 2.0 プロトコルを実装し、 [OpenID に準拠しています](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)。 [GitHubのソースを](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node/)参照してください。

    - [PKCE](https://oauth.net/2/grant-types/authorization-code/) を使用[した承認コードの付与](https://oauth.net/2/pkce/)
    - [デバイス コードの付与](https://oauth.net/2/grant-types/device-code/)
    - [更新トークンの付与](https://oauth.net/2/grant-types/refresh-token/)
    - [クライアントクレデンシャルグラント](https://oauth.net/2/grant-types/client-credentials/)
    - [サイレント フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-acquire-cache-tokens#acquiring-tokens-silently-from-the-cache)
    - [On-Behalf-Of フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)
    - [対話型フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)
    - [マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)
- [JavaScript 用 Microsoft Authentication Library](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/about-msal-browser) (v5.4.0): JavaScript アプリケーションのMicrosoft ID プラットフォームでの認証とトークンの取得を可能にする、ブラウザー ベースのフレームワークに依存しないブラウザー ライブラリ。 PKCE を使用して OAuth 2.0 [承認コード フローを](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)実装し、 [OpenID に準拠しています](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)。 [GitHubのソースを](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-browser/)参照してください。
- **MSAL での[ネイティブ認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication)のサポート**: MSAL JS は、アプリケーションが Web アプリケーションでエンド ツー エンドのカスタマイズ可能なフローを使用してネイティブ エクスペリエンスを実装できるようにするネイティブ認証 API を提供します。 ネイティブ認証を使用すると、ユーザーはデザイン要素、ロゴの配置、レイアウトなど、ユーザー インターフェイスを完全にカスタマイズして、一貫性のあるブランド化された外観を確保できます。 ネイティブ認証機能は、[顧客の外部 ID](https://learn.microsoft.com/ja-jp/entra/identity-platform/concept-native-authentication) の SPA で使用できます
- [React 用 Microsoft Authentication Library](https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/getting-started) (v5.0.6): React を使用するアプリ用 msal-browser ライブラリのラッパー。 [GitHubのソースを](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-react/)参照してください。
- [Angular 用 Microsoft Authentication Library](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/initialization) (v5.1.1): Angular フレームワークを使用するアプリ用の msal-browser ライブラリのラッパー。 [GitHubのソースを](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-angular/)参照してください。
- [ノードのMicrosoft認証拡張機能: ノード](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/extensions/msal-node-extensions/)のMicrosoft認証拡張機能は、クライアント アプリケーションがクロスプラットフォーム トークン キャッシュのシリアル化と永続化を実行するためのセキュリティで保護されたメカニズムを提供します。 ノード用 Microsoft Authentication Library (MSAL) に対する追加のサポートが提供されます。

#### v5 の新機能

MSAL.js v5 には、いくつかの主要な機能が導入されています。

- **クロスオリジン -Opener-Policy (COOP) のサポート**: 厳密な COOP ヘッダーを持つ環境でポップアップ認証フローを有効にします。
- **モデル コンテキスト プロトコル (MCP) 認証**: AI エージェントとツールの統合のための認証フローをサポートします。
- **ネストされたアプリ認証 (NAA):**`createNestablePublicClientApplication`を使用して、Microsoft 365 ホスト アプリ内で実行されるアプリの認証を有効にします。
- **localStorage AES-GCM 暗号化**: セキュリティを強化するために、AES-GCM を使用して localStorage 内のトークン キャッシュを暗号化します。
- **ファクトリ関数**: `createStandardPublicClientApplication` または `createNestablePublicClientApplication` を使用して、非同期構成で MSAL を初期化します。
- **プラットフォーム ブローカー (WAM) 統合**: `allowPlatformBroker`構成オプションを使用して、Windows Authentication Manager (WAM) ブローカー認証を有効にします。

MSAL JavaScript v5.x への移行ガイドについては、次を参照してください。

- [MSAL Browser v4 から v5 への移行](https://learn.microsoft.com/ja-jp/entra/msal/javascript/browser/v4-migration)
- [MSAL Node v3 から v5 への移行](https://learn.microsoft.com/ja-jp/entra/msal/javascript/node/v5-migration)
- [MSAL Angular v4 から v5 へのアップグレード](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/v4-v5-upgrade-guide)
- [MSAL React 移行ガイド](https://learn.microsoft.com/ja-jp/entra/msal/javascript/react/migration-guide)

#### 長期サポート (LTS) 対象ライブラリ

次の表に、各 MSAL.js ライブラリのアクティブバージョンと LTS バージョンを示します。

| Library | アクティブなバージョン | LTS のバージョン |
| --- | --- | --- |
| msal-browser | v5.x | v2.x |
| msal-node | v5.x | v1.x |
| msal-react | v5.x | v1.x |
| msal-angular | v5.x | v2.x |

[`msal-lts` ブランチ](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/msal-lts)でホストされている LTS ライブラリは、アクティブな開発ではなくなりましたが、引き続き重要なセキュリティ バグ修正のサポートを受けています。

Note

`msal-lts` ブランチには、アクティブなサポートから移行された msal-browser の v3.x と v4.x も含まれています。

- [JavaScript 用 Microsoft Authentication Library v2.x](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/msal-lts/lib/msal-browser)
- [Node.js v1.x 向け Microsoft Authentication Library](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/msal-lts/lib/msal-node)
- [React 用 Microsoft Authentication Library v1.x](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/msal-lts/lib/msal-react)
- [Angular 用 Microsoft Authentication Library v2.x](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/msal-lts/lib/msal-angular)

### パッケージ構造

プラットフォームごとにさまざまなパッケージが用意されています。 パッケージと、パッケージが実装するさまざまな認証フローの関係は、以下のパッケージ構造で確認できます。

[Image: MSAL JavaScript パッケージ構造図のスクリーンショット。]

### Samples

この[`code samples`](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples)では、ID プラットフォームでの JavaScript 用のMicrosoft認証ライブラリの使用方法を示します。 各コード サンプルには、プロジェクトをビルドし (該当する場合)、サンプル アプリケーションを実行する方法を説明する `README.md` ファイルが含まれています。

JavaScript およびその他の言語、フレームワーク、プラットフォームを対象とするサンプルの完全な一覧については、[Microsoft ID プラットフォームコード サンプル](https://learn.microsoft.com/ja-jp/entra/identity-platform/sample-v2-code)を参照してください。

ネイティブ認証機能の場合、 [サンプル アプリ](https://github.com/Azure-Samples/ms-identity-ciam-native-javascript-samples/tree/main/typescript/native-auth) は React および Angular Web アプリケーションでネイティブ認証を使用する方法を示しています。 各コード サンプルには、プロジェクトをビルドしてサンプル アプリケーションを実行する方法を説明する `README.md` ファイルが含まれています。 現在のネイティブ認証 API はクロスオリジン リソース共有 (CORS) をサポートしていません。サンプル アプリはローカル プロキシを使用して実行されます。

### パッケージのバージョン管理

すべてのライブラリは、 [セマンティック バージョン管理に](https://semver.org)従います。 最新バージョンの各ライブラリを使用して、最新のセキュリティ パッチとバグ修正を確実に行うことをお勧めします。

### セキュリティ レポート

ライブラリまたはサービスでセキュリティの問題が見つかる場合は、できるだけ詳しく[Microsoft Security Response Center (MSRC) に報告してください](https://aka.ms/report-security-issue)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/avoid-page-reloads"} -->
## ページの再読み込みを回避する (MSAL.js) - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/avoid-page-reloads
- Service: msal / msal-angular
- Article date: 2019-05-29
- Summary: JavaScript 用 Microsoft Authentication Library (MSAL.js) を使用してトークンをサイレント モードで取得および更新するときに、ページの再読み込みを回避する方法について説明します。

JavaScript 用 Microsoft Authentication Library (MSAL.js) は、非表示の`iframe`要素を使用して、バックグラウンドでトークンを自動的に取得および更新します。 Microsoft Entra IDは、トークン要求で指定された登録済みの`redirect_uri`にトークンを返します (既定では、これはアプリのルート ページです)。 応答は 302 であるため、 `redirect_uri` に対応する HTML が `iframe`に読み込まれます。 通常、アプリの `redirect_uri` はルート ページであり、これにより再読み込みされます。

また、アプリのルート ページに移動する際に認証が必要な場合は、入れ子になった `iframe` 要素や `X-Frame-Options: deny` エラーが発生する可能性があります。

MSAL.js Microsoft Entra IDによって発行された 302 を無視できず、返されたトークンを処理する必要があるため、`redirect_uri`が`iframe`に読み込まれるのを防ぐことはできません。

アプリ全体の再読み込みまたはこれに起因するその他のエラーを回避するには、次の回避策に従ってください。

### iframe に別の HTML を指定する

config の `redirect_uri` プロパティを、認証を必要としない単純なページに設定します。 Microsoft Entra 管理センターに登録されている`redirect_uri`と一致していることを確認する必要があります。 ユーザーがログイン プロセスを開始し、ログインが完了した後に正確な場所にリダイレクトされるときに、MSAL によってスタート ページが保存されるため、これはユーザーのログイン エクスペリエンスには影響しません。

### メイン アプリ ファイルでの初期化

アプリの初期化、ルーティング、その他のものを定義する 1 つの中央 JavaScript ファイルが存在するようにアプリが構成されている場合は、アプリが `iframe` に読み込まれているかどうかに基づいて、アプリ モジュールを条件付きで読み込むことができます。 例えば次が挙げられます。

AngularJS の場合: app.js

```javascript
// Check that the window is an iframe and not popup
if (window !== window.parent && !window.opener) {
angular.module('todoApp', ['ui.router', 'MsalAngular'])
    .config(['$httpProvider', 'msalAuthenticationServiceProvider','$locationProvider', function ($httpProvider, msalProvider,$locationProvider) {
        msalProvider.init(
            // msal configuration
        );

        $locationProvider.html5Mode(false).hashPrefix('');
    }]);
}
else {
    angular.module('todoApp', ['ui.router', 'MsalAngular'])
        .config(['$stateProvider', '$httpProvider', 'msalAuthenticationServiceProvider', '$locationProvider', function ($stateProvider, $httpProvider, msalProvider, $locationProvider) {
            $stateProvider.state("Home", {
                url: '/Home',
                controller: "homeCtrl",
                templateUrl: "/App/Views/Home.html",
            }).state("TodoList", {
                url: '/TodoList',
                controller: "todoListCtrl",
                templateUrl: "/App/Views/TodoList.html",
                requireLogin: true
            })

            $locationProvider.html5Mode(false).hashPrefix('');

            msalProvider.init(
                // msal configuration
            );
        }]);
}
```

Angular では: app.module.ts

```javascript
// Imports...
@NgModule({
  declarations: [
    AppComponent,
    MsalComponent,
    MainMenuComponent,
    AccountMenuComponent,
    OsNavComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ServiceWorkerModule.register('ngsw-worker.js', { enabled: environment.production }),
    MsalModule.forRoot(environment.MsalConfig),
    SuiModule,
    PagesModule
  ],
  providers: [
    HttpServiceHelper,
    {provide: HTTP_INTERCEPTORS, useClass: MsalInterceptor, multi: true},
    AuthService
  ],
  entryComponents: [
    AppComponent,
    MsalComponent
  ]
})
export class AppModule {
  constructor() {
    console.log('APP Module Constructor!');
  }

  ngDoBootstrap(ref: ApplicationRef) {
    if (window !== window.parent && !window.opener)
    {
      console.log("Bootstrap: MSAL");
      ref.bootstrap(MsalComponent);
    }
    else
    {
    //this.router.resetConfig(RouterModule);
      console.log("Bootstrap: App");
      ref.bootstrap(AppComponent);
    }
  }
}
```

MsalComponent:

```javascript
import { Component} from '@angular/core';
import { MsalService } from '@azure/msal-angular';

// This component is used only to avoid Angular reload
// when doing acquireTokenSilent()

@Component({
  selector: 'app-root',
  template: '',
})
export class MsalComponent {
  constructor(private Msal: MsalService) {
  }
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/configuration"} -->
## MSAL Angular の構成 - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/configuration
- Service: msal / msal-angular
- Article date: 2026-03-06
- Summary: MsalGuardConfiguration、MsalInterceptorConfiguration、ブラウザー設定を使用して MSAL Angular を構成する方法について説明します

Angular 用の MSAL は、複数の方法で構成できます。 この記事では、MSAL Angular で使用可能な構成オプション (静的および動的アプローチを含む) について説明し、認証をアプリに統合するためのコード サンプルを提供します。 このガイドを使用して、アプリケーションの要件に最も適した構成方法を選択し、ユーザーにシームレスなサインイン エクスペリエンスを提供します。

### 構成オプション

`@azure/msal-angular` は、次の 3 つの構成オブジェクトを受け入れます。

1. [構成](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#configuration): これは、コア `@azure/msal-browser` ライブラリに使用されるのと同じ構成オブジェクトです。 すべての構成オプションについては、 [こちらをご覧ください](https://azuread.github.io/microsoft-authentication-library-for-js/ref/types/_azure_msal_browser.Configuration.html)。
2. [`MsalGuardConfiguration`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/src/msal.guard.config.ts): Angular ガード専用の一連のオプション。
3. [`MsalInterceptorConfiguration`](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-angular/src/msal.interceptor.config.ts): Angular インターセプター専用のオプションのセット。

#### Angular 固有の設定

- `interactionType`は、`MsalGuardConfiguration`と`MsalInterceptorConfiguration`で指定する必要があり、`Popup`または`Redirect`に設定できます。
- `protectedResourceMap`の`MsalInterceptorConfiguration` オブジェクトは、ルートを保護するために使用されます。
- オプションの `authRequest` オブジェクトは、 `MsalGuardConfiguration` および `MsalInterceptorConfiguration` で指定して、追加のオプションを設定できます。
- 省略可能な `loginFailedRoute` 文字列は、 `MsalGuardConfiguration`に設定できます。 ログインが必要で失敗した場合、Msal Guard はこのルートにリダイレクトされます。

MSAL Angular v1 の構成、使用方法、および相違点の詳細については、 [MsalInterceptor](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/msal-interceptor) と [MsalGuard](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/msal-guard) のドキュメントを参照してください。

#### リダイレクトの構成

リダイレクトを使用する場合は、 `MsalRedirectComponent` をインポートし、 `AppComponent` でブートストラップすることをお勧めします。 詳細については、 [リダイレクトのドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/redirects) を参照してください。

**メモ：** MSAL v3.x の時点で、アプリケーション オブジェクトの初期化が必要になりました。 詳細については、 [v2-v3 アップグレード ガイド](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/v2-v3-upgrade-guide) を参照してください。 MSAL Angular v5 へのアップグレードの詳細については、 [v4-v5 アップグレード ガイド](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/v4-v5-upgrade-guide)を参照してください。

### MsalModule.forRoot

`MsalModule` クラスには、`app.module.ts` ファイルで呼び出すことができる静的メソッドが含まれています。

```typescript
import { NgModule } from "@angular/core";
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { AppComponent } from "./app.component";
import { MsalModule, MsalService, MsalGuard, MsalInterceptor, MsalBroadcastService, MsalRedirectComponent } from "@azure/msal-angular";
import { PublicClientApplication, InteractionType, BrowserCacheLocation } from "@azure/msal-browser";

@NgModule({
  imports: [
    MsalModule.forRoot(
      new PublicClientApplication({
        // MSAL Configuration
        auth: {
          clientId: "clientid",
          authority: "https://login.microsoftonline.com/common/",
          redirectUri: "http://localhost:4200/",
          postLogoutRedirectUri: "http://localhost:4200/",
        },
        cache: {
          cacheLocation: BrowserCacheLocation.LocalStorage,
        },
        system: {
          loggerOptions: {
            loggerCallback: () => {},
            piiLoggingEnabled: false,
          },
        },
      }),
      {
        interactionType: InteractionType.Popup, // MSAL Guard Configuration
        authRequest: {
          scopes: ["user.read"],
        },
        loginFailedRoute: "/login-failed",
      },
      {
        interactionType: InteractionType.Redirect, // MSAL Interceptor Configuration
        protectedResourceMap,
      }
    ),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: MsalInterceptor,
      multi: true,
    },
    MsalGuard,
  ],
  bootstrap: [AppComponent, MsalRedirectComponent],
})
export class AppModule {}
```

### ファクトリ プロバイダー

また、ファクトリ プロバイダーを介して構成オプションを指定することもできます。

```typescript
import { MsalModule, MsalService, MsalInterceptor, MsalInterceptorConfiguration, MsalGuard, MsalGuardConfiguration, MsalBroadcastService, MsalRedirectComponent } from "@azure/msal-angular";
import { IPublicClientApplication, PublicClientApplication, InteractionType, BrowserCacheLocation } from "@azure/msal-browser";

export function MSALInstanceFactory(): IPublicClientApplication {
  return new PublicClientApplication({
    auth: {
      clientId: "00001111-aaaa-2222-bbbb-3333cccc4444",
      redirectUri: "http://localhost:4200",
      postLogoutRedirectUri: "http://localhost:4200",
    },
    cache: {
      cacheLocation: BrowserCacheLocation.LocalStorage,
    },
  });
}

export function MSALInterceptorConfigFactory(): MsalInterceptorConfiguration {
  const protectedResourceMap = new Map<string, Array<string>>();
  protectedResourceMap.set("https://graph.microsoft.com/v1.0/me", ["user.read"]);

  return {
    interactionType: InteractionType.Redirect,
    protectedResourceMap,
  };
}

export function MSALGuardConfigFactory(): MsalGuardConfiguration {
  return {
    interactionType: InteractionType.Redirect,
    authRequest: {
      scopes: ["user.read"],
    },
    loginFailedRoute: "./login-failed",
  };
}

@NgModule({
  imports: [MsalModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: MsalInterceptor,
      multi: true,
    },
    {
      provide: MSAL_INSTANCE,
      useFactory: MSALInstanceFactory,
    },
    {
      provide: MSAL_GUARD_CONFIG,
      useFactory: MSALGuardConfigFactory,
    },
    {
      provide: MSAL_INTERCEPTOR_CONFIG,
      useFactory: MSALInterceptorConfigFactory,
    },
    MsalGuard,
    MsalBroadcastService,
    MsalService,
  ],
  bootstrap: [AppComponent, MsalRedirectComponent],
})
export class AppModule {}
```

### platformBrowserDynamic

MSAL Angular を動的に構成する必要がある場合 (たとえば、API から返された値に基づいて)、 `platformBrowserDynamic`を使用できます。 `platformBrowserDynamic` はプラットフォーム ファクトリであり、アプリケーションをブートストラップするために使用され、構成オプションを取り込むことが可能です。 `platformBrowserDynamic` は、Angular アプリケーションのセットアップ時に既に存在している必要があります。

`@azure/msal-angular`と json ファイルを使用して`platformBrowserDynamic`を動的に構成する方法の例を次に示します。

`app.module.ts`

```typescript
import { MsalModule, MsalInterceptor, MsalService } from "@azure/msal-angular";

@NgModule({
  imports: [MsalModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: MsalInterceptor,
      multi: true,
    },
    MsalService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
```

`main.ts`

```typescript
import { enableProdMode } from "@angular/core";
import { platformBrowserDynamic } from "@angular/platform-browser-dynamic";

import { AppModule } from "./app/app.module";
import { environment } from "./environments/environment";
import { MSAL_INSTANCE, MSAL_GUARD_CONFIG, MSAL_INTERCEPTOR_CONFIG } from "@azure/msal-angular";
import { PublicClientApplication, Configuration } from "@azure/msal-browser";

if (environment.production) {
  enableProdMode();
}

function loggerCallback(logLevel: LogLevel, message: string) {
  console.log("MSAL Angular: ", message);
}

fetch("/assets/configuration.json")
  .then((response) => response.json())
  .then((json) => {
    platformBrowserDynamic([
      {
        provide: MSAL_INSTANCE,
        useValue: new PublicClientApplication({
          auth: json.msal.auth,
          cache: json.msal.cache,
          system: {
            loggerOptions: {
              loggerCallback,
              logLevel: LogLevel.Info,
              piiLoggingEnabled: false,
            },
          },
        }),
      },
      {
        provide: MSAL_GUARD_CONFIG,
        useValue: {
          interactionType: json.guard.interactionType,
          authRequest: json.guard.authRequest,
          loginFailedRoute: json.guard.loginFailedRoute,
        } as MsalGuardConfiguration,
      },
      {
        provide: MSAL_INTERCEPTOR_CONFIG,
        useValue: {
          interactionType: json.interceptor.interactionType,
          protectedResourceMap: new Map(json.interceptor.protectedResourceMap),
        } as MsalInterceptorConfiguration,
      },
    ])
      .bootstrapModule(AppModule)
      .catch((err) => console.error(err));
  });
```

`src/assets/configuration.json`

```json
{
  "msal": {
    "auth": {
      "clientId": "clientid",
      "authority": "https://login.microsoftonline.com/common/",
      "redirectUri": "http://localhost:4200/",
      "postLogoutRedirectUri": "http://localhost:4200/",
      "navigateToLoginRequestUrl": true
    },
    "cache": {
      "cacheLocation": "localStorage",
      "storeAuthStateInCookie": true
    }
  },
  "guard": {
    "interactionType": "redirect",
    "authRequest": {
      "scopes": ["user.read"]
    },
    "loginFailedRoute": "/login-failed"
  },
  "interceptor": {
    "interactionType": "redirect",
    "protectedResourceMap": [["https://graph.microsoft.com/v1.0/me", ["user.read"]]]
  }
}
```

### ファクトリ プロバイダーとAPP\_INITIALIZERを使用した動的構成

MSAL Angular を動的に構成するには、APP\_INITIALIZERでファクトリ プロバイダーを使用できます。

`src/app/config.service.ts`

```typescript
import { Injectable } from "@angular/core";
import { HttpClient, HttpBackend } from "@angular/common/http";
import { map } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class ConfigService {
  private settings: any;
  private http: HttpClient;

  constructor(private readonly httpHandler: HttpBackend) {
    this.http = new HttpClient(httpHandler);
  }

  init(endpoint: string): Promise<boolean> {
    return new Promise<boolean>((resolve, reject) => {
      this.http
        .get(endpoint)
        .pipe(map((result) => result))
        .subscribe(
          (value) => {
            this.settings = value;
            resolve(true);
          },
          (error) => {
            reject(error);
          }
        );
    });
  }

  getSettings(key?: string | Array<string>): any {
    if (!key || (Array.isArray(key) && !key[0])) {
      return this.settings;
    }

    if (!Array.isArray(key)) {
      key = key.split(".");
    }

    let result = key.reduce((account: any, current: string) => account && account[current], this.settings);

    return result;
  }
}
```

`src/app/msal-config-dynamic.module.ts`

```typescript
import { InjectionToken, NgModule, APP_INITIALIZER } from "@angular/core";
import { IPublicClientApplication, PublicClientApplication, LogLevel } from "@azure/msal-browser";
import { MsalGuard, MsalInterceptor, MsalBroadcastService, MsalInterceptorConfiguration, MsalModule, MsalService, MSAL_GUARD_CONFIG, MSAL_INSTANCE, MSAL_INTERCEPTOR_CONFIG, MsalGuardConfiguration } from "@azure/msal-angular";
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { ConfigService } from "./config.service";

const AUTH_CONFIG_URL_TOKEN = new InjectionToken<string>("AUTH_CONFIG_URL");

export function initializerFactory(env: ConfigService, configUrl: string): any {
  const promise = env.init(configUrl).then((value) => {
    console.log("finished getting configurations dynamically.");
  });
  return () => promise;
}

export function loggerCallback(logLevel: LogLevel, message: string) {
  console.log(message);
}

export function MSALInstanceFactory(config: ConfigService): IPublicClientApplication {
  return new PublicClientApplication({
    auth: config.getSettings("msal").auth,
    cache: config.getSettings("msal").cache,
    system: {
      loggerOptions: {
        loggerCallback,
        logLevel: LogLevel.Info,
        piiLoggingEnabled: false,
      },
    },
  });
}

export function MSALInterceptorConfigFactory(config: ConfigService): MsalInterceptorConfiguration {
  const protectedResourceMap = new Map<string, Array<string>>(config.getSettings("interceptor").protectedResourceMap);

  return {
    interactionType: config.getSettings("interceptor").interactionType,
    protectedResourceMap,
  };
}

export function MSALGuardConfigFactory(config: ConfigService): MsalGuardConfiguration {
  return {
    interactionType: config.getSettings("guard").interactionType,
    authRequest: config.getSettings("guard").authRequest,
    loginFailedRoute: config.getSettings("guard").loginFailedRoute,
  };
}

@NgModule({
  providers: [],
  imports: [MsalModule],
})
export class MsalConfigDynamicModule {
  static forRoot(configFile: string) {
    return {
      ngModule: MsalConfigDynamicModule,
      providers: [
        ConfigService,
        { provide: AUTH_CONFIG_URL_TOKEN, useValue: configFile },
        { provide: APP_INITIALIZER, useFactory: initializerFactory, deps: [ConfigService, AUTH_CONFIG_URL_TOKEN], multi: true },
        {
          provide: MSAL_INSTANCE,
          useFactory: MSALInstanceFactory,
          deps: [ConfigService],
        },
        {
          provide: MSAL_GUARD_CONFIG,
          useFactory: MSALGuardConfigFactory,
          deps: [ConfigService],
        },
        {
          provide: MSAL_INTERCEPTOR_CONFIG,
          useFactory: MSALInterceptorConfigFactory,
          deps: [ConfigService],
        },
        MsalService,
        MsalGuard,
        MsalBroadcastService,
        {
          provide: HTTP_INTERCEPTORS,
          useClass: MsalInterceptor,
          multi: true,
        },
      ],
    };
  }
}
```

`src/app/app.module.ts`

```typescript
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgModule } from "@angular/core";

import { MatButtonModule } from "@angular/material/button";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatListModule } from "@angular/material/list";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { HomeComponent } from "./home/home.component";
import { ProfileComponent } from "./profile/profile.component";

import { HttpClientModule } from "@angular/common/http";
import { MsalRedirectComponent } from "@azure/msal-angular";
import { DetailComponent } from "./detail/detail.component";
import { MsalConfigDynamicModule } from "./msal-config-dynamic.module";

@NgModule({
  declarations: [AppComponent, HomeComponent, ProfileComponent, DetailComponent],
  imports: [BrowserModule, BrowserAnimationsModule, AppRoutingModule, MatButtonModule, MatToolbarModule, MatListModule, HttpClientModule, MsalConfigDynamicModule.forRoot("assets/configuration.json")],
  providers: [],
  bootstrap: [AppComponent, MsalRedirectComponent],
})
export class AppModule {}
```

`src/assets/configuration.json`

```json
{
  "msal": {
    "auth": {
      "clientId": "clientid",
      "authority": "https://login.microsoftonline.com/common/",
      "redirectUri": "http://localhost:4200/",
      "postLogoutRedirectUri": "http://localhost:4200/",
      "navigateToLoginRequestUrl": true
    },
    "cache": {
      "cacheLocation": "localStorage",
      "storeAuthStateInCookie": true
    }
  },
  "guard": {
    "interactionType": "redirect",
    "authRequest": {
      "scopes": ["user.read"]
    },
    "loginFailedRoute": "/login-failed"
  },
  "interceptor": {
    "interactionType": "redirect",
    "protectedResourceMap": [["https://graph.microsoft.com/v1.0/me", ["user.read"]]]
  }
}
```

#### MsalGuard - 動的認証要求

**MsalGuard** では、実行時に **authRequest** を動的に変更することもできます。 これにより、ルートに対して別の機関を選択したり、 **RouterStateSnapshot** に基づいてスコープを動的に追加することができます。

```js
export function MSALGuardConfigFactory(): MsalGuardConfiguration {
  return {
    interactionType: InteractionType.Redirect,
    authRequest: (authService, state) => {
      return {
        scopes: state.root.url.some((x) => x.path === "calendar") ? ["user.read", "	Calendars.Read"] : ["user.read"],
      };
    },
    loginFailedRoute: "./login-failed",
  };
}
```

### スタンドアロン コンポーネントを使用した Angular アプリの構成

スタンドアロン コンポーネントを使用する Angular アプリケーションは、上記の ファイル内の`app.config.ts`と共に使用でき、ブートストラップのために`main.ts`にインポートされます。

使用については、 [Angular スタンドアロン サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-angular-samples/angular-standalone-sample) を参照してください。

```ts
// app.config.ts
import { ApplicationConfig, importProvidersFrom } from "@angular/core";
import { provideRouter } from "@angular/router";
import { routes } from "./app.routes";
import { BrowserModule } from "@angular/platform-browser";
import { provideHttpClient, withInterceptorsFromDi, HTTP_INTERCEPTORS, withFetch, withInterceptors } from "@angular/common/http";
import { provideNoopAnimations } from "@angular/platform-browser/animations";
import { IPublicClientApplication, PublicClientApplication, InteractionType, BrowserCacheLocation, LogLevel } from "@azure/msal-browser";
import { MsalInterceptor, MSAL_INSTANCE, MsalInterceptorConfiguration, MsalGuardConfiguration, MSAL_GUARD_CONFIG, MSAL_INTERCEPTOR_CONFIG, MsalService, MsalGuard, MsalBroadcastService } from "@azure/msal-angular";

export function loggerCallback(logLevel: LogLevel, message: string) {
  console.log(message);
}

export function MSALInstanceFactory(): IPublicClientApplication {
  return new PublicClientApplication({
    auth: {
      clientId: "clientid",
      authority: "https://login.microsoftonline.com/common/",
      redirectUri: "/",
      postLogoutRedirectUri: "/",
    },
    cache: {
      cacheLocation: BrowserCacheLocation.LocalStorage,
    },
    system: {
      allowPlatformBroker: false, // Disables WAM Broker
      loggerOptions: {
        loggerCallback,
        logLevel: LogLevel.Info,
        piiLoggingEnabled: false,
      },
    },
  });
}

export function MSALInterceptorConfigFactory(): MsalInterceptorConfiguration {
  const protectedResourceMap = new Map<string, Array<string>>();
  protectedResourceMap.set("https://graph.microsoft.com/v1.0/me", ["user.read"]);

  return {
    interactionType: InteractionType.Redirect,
    protectedResourceMap,
  };
}

export function MSALGuardConfigFactory(): MsalGuardConfiguration {
  return {
    interactionType: InteractionType.Redirect,
    authRequest: {
      scopes: ["user.read"],
    },
    loginFailedRoute: "/login-failed",
  };
}

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    importProvidersFrom(BrowserModule),
    provideNoopAnimations(),
    provideHttpClient(withInterceptorsFromDi(), withFetch()),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: MsalInterceptor,
      multi: true,
    },
    {
      provide: MSAL_INSTANCE,
      useFactory: MSALInstanceFactory,
    },
    {
      provide: MSAL_GUARD_CONFIG,
      useFactory: MSALGuardConfigFactory,
    },
    {
      provide: MSAL_INTERCEPTOR_CONFIG,
      useFactory: MSALInterceptorConfigFactory,
    },
    MsalService,
    MsalGuard,
    MsalBroadcastService,
  ],
};
```

```ts
// main.ts
import { bootstrapApplication } from "@angular/platform-browser";
import { appConfig } from "./app/app.config";
import { AppComponent } from "./app/app.component";

bootstrapApplication(AppComponent, appConfig).catch((err) => console.error(err));
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/javascript/angular/errors"} -->
## MSAL Angular のエラー - Microsoft Authentication Library for JavaScript

- Source: https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/errors
- Service: msal / msal-angular
- Article date: 2025-05-21
- Summary: MSAL Angular で発生するエラーへの対処方法について説明します

### BrowserAuthErrors

#### インタラクション進行中

**エラー メッセージ**: 相互作用は現在進行中です。 対話型 API を呼び出す前に、この操作が完了していることを確認してください。

このエラーは、ある対話型 API (`loginPopup`、`loginRedirect`、`acquireTokenPopup`、`acquireTokenRedirect`) が呼び出されたときに、別の対話型 API がまだ進行中だった場合に発生します。 login API と acquireToken API は非同期であるため、別の約束を呼び出す前に、結果として得られる約束が解決されていることを確認する必要があります。

`@azure/msal-angular`では、これが発生する可能性がある 2 つの一般的なシナリオがあります。

1. アプリケーションがリダイレクトを正しく処理していません。 このエラーは、アプリまたはユーザーが対話型 API を呼び出そうとしたときに発生します。
2. アプリケーションは、他の場所で既に相互作用が進行中かどうかを最初に確認することなく、上記のいずれかの API を呼び出しています。

リダイレクトは、`MsalRedirectComponent`を使用するか、`handleRedirectObservable()`を呼び出して**処理する必要があります**。 これら 2 つの方法のいずれかでリダイレクトを明示的に処理しないと、上記のエラーが発生します。 両方の方法の詳細については、リダイレクトに関するドキュメント [を参照](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/redirects) してください。

さらに、監視可能な `inProgress$` をサブスクライブし、 `InteractionStatus.None`のフィルター処理を行った後で、対話を行う必要があります。 別の操作が進行中の間の操作はサポートされていないため、上記のエラーが発生します。 `InteractionStatus.None`の確認は、これが発生しないことを確認する方法です。 詳細については [、イベントドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/javascript/angular/events#the-inprogress-observable) を参照してください。

このエラーの詳細については、 [`@azure/msal-browser` エラードキュメント](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/errors.md) を参照してください。

 ❌ 次の例では、別のコンポーネントが進行中の対話型 API 呼び出しをすでに開始している場合、このエラーが発生します。

```javascript
import { Component, OnInit } from '@angular/core';
import { MsalService, MsalBroadcastService, InteractionStatus } from '@azure/msal-angular';
import { filter } from 'rxjs/operators';

@Component()
export class ExampleComponent implements OnInit {

  constructor(
    private msalBroadcastService: MsalBroadcastService,
    private authService: MsalService
  ) {}

  ngOnInit(): void {
    this.authService.loginRedirect();
  }
```

✔️ 前の例を修正するには、 `loginRedirect`を呼び出す前に、他の操作が進行中でないことを確認します。

```javascript
import { Component, OnInit } from '@angular/core';
import { InteractionStatus } from '@azure/msal-browser';
import { MsalService, MsalBroadcastService } from '@azure/msal-angular';
import { filter } from 'rxjs/operators';

@Component()
export class ExampleComponent implements OnInit {

  constructor(
    private msalBroadcastService: MsalBroadcastService,
    private authService: MsalService
  ) {}

  ngOnInit(): void {
    this.msalBroadcastService.inProgress$
      .pipe(
        filter((status: InteractionStatus) => status === InteractionStatus.None),
      )
      .subscribe(() => {
        this.authService.loginRedirect();
      })
  }
```

##### トラブルシューティングの手順

- [詳細ログを有効に](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/configuration.md#using-the-config-object) し、イベントの順序をトレースします。 別の API が解決される前に対話型 API が呼び出されていないことを確認します。
- リダイレクト フローを使用する場合は、 `MsalRedirectComponet` が正しくブートストラップされているか、リダイレクト先のすべてのページで `handleRedirectObservable` が呼び出されていることを確認します。

このエラーが発生する原因がわからない場合は、[issue を作成し](https://github.com/AzureAD/microsoft-authentication-library-for-js/issues/new/choose)、次の情報を共有する準備をしてください。

- 詳細ログ
- 問題の再現に使用できるサンプル アプリやコード スニペット
- ページを更新します。 エラーは消えませんか?
- 新しいタブでアプリケーションを開きます。エラーは消えませんか?
<!-- /MSL-PAGE -->
