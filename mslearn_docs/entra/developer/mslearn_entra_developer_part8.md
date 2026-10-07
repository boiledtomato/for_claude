# Microsoft Learn — Microsoft Entra / 開発者向け (Identity Platform・MSAL・Microsoft.Identity.Web) (part 8)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 63

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-web-app-node-sign-in-sign-out"} -->
## チュートリアル: Microsoft ID プラットフォームを使用して Node/Express.js Web アプリにサインインを追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-sign-in-sign-out
- Service: identity-platform
- Article date: 2025-01-03
- Summary: Microsoft ID プラットフォームを使用して、外部テナントまたは従業員テナントを使用して Node.js Web アプリにサインインを追加する方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、Node/Express Web アプリにサインインとサインアウトのロジックを追加します。 このコードを使用すると、外部テナントまたは従業員テナントの従業員で、顧客向けアプリにユーザーをサインインさせることができます。

このチュートリアルは、3 部構成のチュートリアル シリーズのパート 2 です。

このチュートリアルでは、次の操作を行います。

- サインインとサインアウトのロジックを追加する
- ID トークン要求を表示する
- アプリを実行し、サインインとサインアウトのエクスペリエンスをテストします。

### 前提条件

- [「チュートリアル: Microsoft ID プラットフォームを使用してユーザーをサインインさせる Node.js Web アプリを設定する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-sign-in-prepare-app)」の手順を完了します。

### MSAL 構成オブジェクトを作成する

コード エディターでファイル *authConfig.js* 開き、次のコードを追加します。

```javascript
require('dotenv').config();

const TENANT_SUBDOMAIN = process.env.TENANT_SUBDOMAIN || 'Enter_the_Tenant_Subdomain_Here';
const REDIRECT_URI = process.env.REDIRECT_URI || 'http://localhost:3000/auth/redirect';
const POST_LOGOUT_REDIRECT_URI = process.env.POST_LOGOUT_REDIRECT_URI || 'http://localhost:3000';
const GRAPH_ME_ENDPOINT = process.env.GRAPH_API_ENDPOINT + "v1.0/me" || 'Enter_the_Graph_Endpoint_Here';

/**
 * Configuration object to be passed to MSAL instance on creation.
 * For a full list of MSAL Node configuration parameters, visit:
 * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/configuration.md
 */
const msalConfig = {
    auth: {
        clientId: process.env.CLIENT_ID || 'Enter_the_Application_Id_Here', // 'Application (client) ID' of app registration in Azure portal - this value is a GUID
        //For external tenant
        authority: process.env.AUTHORITY || `https://${TENANT_SUBDOMAIN}.ciamlogin.com/`, // replace "Enter_the_Tenant_Subdomain_Here" with your tenant name
        //For workforce tenant
        //authority: process.env.CLOUD_INSTANCE + process.env.TENANT_ID
        clientSecret: process.env.CLIENT_SECRET || 'Enter_the_Client_Secret_Here', // Client secret generated from the app registration in Azure portal
    },
    system: {
        loggerOptions: {
            loggerCallback(loglevel, message, containsPii) {
                console.log(message);
            },
            piiLoggingEnabled: false,
            logLevel: 'Info',
        },
    },
};

module.exports = {
    msalConfig,
    REDIRECT_URI,
    POST_LOGOUT_REDIRECT_URI,
    TENANT_SUBDOMAIN,
    GRAPH_ME_ENDPOINT
};
```

`msalConfig` オブジェクトには、認証フローの動作をカスタマイズするために使用する一連の構成オプションが含まれています。

*authConfig.js* ファイルで、次の値を置き換えます。

- `Enter_the_Application_Id_Here` を、前に登録したアプリのアプリケーション (クライアント) ID に置き換えます。
- `Enter_the_Tenant_Subdomain_Here` 外部ディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。 **この値は、外部テナントにのみ必要です**。
- `Enter_the_Client_Secret_Here` を、前にコピーしたアプリ シークレットの値に置き換えます。
- `Enter_the_Graph_Endpoint_Here` アプリが呼び出す Microsoft Graph API クラウド インスタンスを使用します。 *`https://graph.microsoft.com/`*値を使用する (末尾のスラッシュを含む)

*.env* ファイルを使用して構成情報を格納する場合:

1. コード エディターで *.env* ファイルを開き、次のコードを追加します。

    ```
        CLIENT_ID=Enter_the_Application_Id_Here
        TENANT_SUBDOMAIN=Enter_the_Tenant_Subdomain_Here 
        CLOUD_INSTANCE="Enter_the_Cloud_Instance_Id_Here" # cloud instance string should end with a trailing slash
        TENANT_ID=Enter_the_Tenant_ID_here
        CLIENT_SECRET=Enter_the_Client_Secret_Here
        REDIRECT_URI=http://localhost:3000/auth/redirect
        POST_LOGOUT_REDIRECT_URI=http://localhost:3000
        GRAPH_API_ENDPOINT=Enter_the_Graph_Endpoint_Here # graph api endpoint string should end with a trailing slash
        EXPRESS_SESSION_SECRET=Enter_the_Express_Session_Secret_Here # express session secret, just any random text
    ```
2. プレースホルダーを置き換えてください。

    1. `Enter_the_Application_Id_Here`、 `Enter_the_Tenant_Subdomain_Here` 、および `Enter_the_Client_Secret_Here` 前に説明しました。
    2. `Enter_the_Cloud_Instance_Id_Here` アプリケーションが登録されている Azure クラウド インスタンスを使用します。 *`https://login.microsoftonline.com/`*を値として使用します (末尾のスラッシュを含めます)。 **この値は、従業員テナントにのみ必要です**。
    3. `Enter_the_Tenant_ID_here` ワークフォース テナント ID またはプライマリ ドメイン ( *aaaabbbb-0000-cccc-1111-dddd2222eeee* 、 *contoso.microsoft.com* など) を使用します。 **この値は、従業員テナントにのみ必要です**。

authConfig.jsファイル内の`msalConfig`、`REDIRECT_URI`、`TENANT_SUBDOMAIN`、`GRAPH_ME_ENDPOINT`、および*`POST_LOGOUT_REDIRECT_URI`*変数をエクスポートして、他のファイルでアクセスできるようにします。

#### アプリの権限 URL

外部テナントと従業員テナントのアプリケーション権限は異なります。 次のようにビルドします。

## [従業員テナント](#tab/workforce-tenant)
```javascript
//Authority for workforce tenant
authority: process.env.CLOUD_INSTANCE + process.env.TENANT_ID
```

## [外部テナント](#tab/external-tenant)
```javascript
//Authority for external tenant
authority: process.env.AUTHORITY || `https://${TENANT_SUBDOMAIN}.ciamlogin.com/`
```

---

#### カスタム URL ドメインを使用する (省略可能)

## [従業員テナント](#tab/workforce-tenant)
カスタム URL ドメインは、従業員テナントではサポートされていません。

## [外部テナント](#tab/external-tenant)
カスタム ドメインを使用して、認証 URL を完全にブランド化します。 ユーザーの視点から見ると、認証プロセスの間、ユーザーは *ciamlogin.com* ドメイン名にリダイレクトされず、あなたのドメインにとどまります。

カスタム ドメインを使用するには、以下の手順を実行します。

1. 「[外部テナント内のアプリに対するカスタム URL ドメインの有効化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain)」の手順を使用し、外部テナントに対してカスタム URL ドメインを有効にします。
2. *authConfig.js* ファイルで、`auth` オブジェクトを見つけて、次のようにします。

    1. `authority` プロパティの値を *https://Enter\_the\_Custom\_Domain\_Here/Enter\_the\_Tenant\_ID\_Here* に更新します。 `Enter_the_Custom_Domain_Here` を実際のカスタム URL ドメインに、`Enter_the_Tenant_ID_Here` を実際のテナント ID に置き換えます。 テナント ID がわからない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください。
    2. `knownAuthorities` という値を持つ  プロパティを追加します。

*authConfig.js* ファイルを変更した後、カスタム URL ドメインが *login.contoso.com* で、テナント ID が *aaaabbbb-0000-cccc-1111-dddd2222eeee* であるとすると、ファイルは次のスニペットのようになるはずです。

```JavaScript
//...
const msalConfig = {
    auth: {
        authority: process.env.AUTHORITY || 'https://login.contoso.com/aaaabbbb-0000-cccc-1111-dddd2222eeee', 
        knownAuthorities: ["login.contoso.com"],
        //Other properties
    },
    //...
};
```

---

### Express Route を追加する

Express ルートは、サインイン、サインアウト、ID トークン要求の表示などの操作を実行できるエンドポイントを提供します。

#### アプリケーションの開始地点

コード エディターで "routes/index.js" ファイルを開き、次のコードを追加します。

```javascript
const express = require('express');
const router = express.Router();

router.get('/', function (req, res, next) {
    res.render('index', {
        title: 'MSAL Node & Express Web App',
        isAuthenticated: req.session.isAuthenticated,
        username: req.session.account?.username !== '' ? req.session.account?.username : req.session.account?.name,
    });
});    
module.exports = router;
```

`/` ルートは、アプリケーションへのエントリ ポイントです。 ビルド *アプリ UI コンポーネント*で前に作成した [views/index.hbs](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-sign-in-prepare-app#build-app-ui-components) ビューがレンダリングされます。 `isAuthenticated` は、ビューに表示される内容を決定するブール変数です。

#### サインインとサインアウト

1. コード エディターで *、routes/auth.js* ファイルを開き、次のコードを追加します。

    ```javascript
    const express = require('express');
    const authController = require('../controller/authController');
    const router = express.Router();
    
    router.get('/signin', authController.signIn);
    router.get('/signout', authController.signOut);
    router.post('/redirect', authController.handleRedirect);
    
    module.exports = router;
    ```
2. コード エディターで *コントローラー/authController.jsファイルを * 開き、次のコードを追加します。

    ```javascript
    const authProvider = require('../auth/AuthProvider');
    
    exports.signIn = async (req, res, next) => {
        return authProvider.login(req, res, next);
    };
    
    exports.handleRedirect = async (req, res, next) => {
        return authProvider.handleRedirect(req, res, next);
    }
    
    exports.signOut = async (req, res, next) => {
        return authProvider.logout(req, res, next);
    };
    
    ```
3. コード エディターで *、auth/AuthProvider.jsファイルを * 開き、次のコードを追加します。

    ```javascript
    const msal = require('@azure/msal-node');
    const axios = require('axios');
    const { msalConfig, TENANT_SUBDOMAIN, REDIRECT_URI, POST_LOGOUT_REDIRECT_URI, GRAPH_ME_ENDPOINT} = require('../authConfig');
    
    class AuthProvider {
        config;
        cryptoProvider;
    
        constructor(config) {
            this.config = config;
            this.cryptoProvider = new msal.CryptoProvider();
        }
    
        getMsalInstance(msalConfig) {
            return new msal.ConfidentialClientApplication(msalConfig);
        }
    
        async login(req, res, next, options = {}) {
            // create a GUID for crsf
            req.session.csrfToken = this.cryptoProvider.createNewGuid();
    
            /**
             * The MSAL Node library allows you to pass your custom state as state parameter in the Request object.
             * The state parameter can also be used to encode information of the app's state before redirect.
             * You can pass the user's state in the app, such as the page or view they were on, as input to this parameter.
             */
            const state = this.cryptoProvider.base64Encode(
                JSON.stringify({
                    csrfToken: req.session.csrfToken,
                    redirectTo: '/',
                })
            );
    
            const authCodeUrlRequestParams = {
                state: state,
    
                /**
                 * By default, MSAL Node will add OIDC scopes to the auth code url request. For more information, visit:
                 * https://learn.microsoft.com/azure/active-directory/develop/v2-permissions-and-consent#openid-connect-scopes
                 */
                scopes: [],
            };
    
            const authCodeRequestParams = {
                state: state,
    
                /**
                 * By default, MSAL Node will add OIDC scopes to the auth code request. For more information, visit:
                 * https://learn.microsoft.com/azure/active-directory/develop/v2-permissions-and-consent#openid-connect-scopes
                 */
                scopes: [],
            };
    
            /**
             * If the current msal configuration does not have cloudDiscoveryMetadata or authorityMetadata, we will
             * make a request to the relevant endpoints to retrieve the metadata. This allows MSAL to avoid making
             * metadata discovery calls, thereby improving performance of token acquisition process.
             */
            if (!this.config.msalConfig.auth.authorityMetadata) {
                const authorityMetadata = await this.getAuthorityMetadata();
                this.config.msalConfig.auth.authorityMetadata = JSON.stringify(authorityMetadata);
            }
    
            const msalInstance = this.getMsalInstance(this.config.msalConfig);
    
            // trigger the first leg of auth code flow
            return this.redirectToAuthCodeUrl(
                req,
                res,
                next,
                authCodeUrlRequestParams,
                authCodeRequestParams,
                msalInstance
            );
        }
    
        async handleRedirect(req, res, next) {
            const authCodeRequest = {
                ...req.session.authCodeRequest,
                code: req.body.code, // authZ code
                codeVerifier: req.session.pkceCodes.verifier, // PKCE Code Verifier
            };
    
            try {
                const msalInstance = this.getMsalInstance(this.config.msalConfig);
                msalInstance.getTokenCache().deserialize(req.session.tokenCache);
    
                const tokenResponse = await msalInstance.acquireTokenByCode(authCodeRequest, req.body);
    
                req.session.tokenCache = msalInstance.getTokenCache().serialize();
                req.session.idToken = tokenResponse.idToken;
                req.session.account = tokenResponse.account;
                req.session.isAuthenticated = true;
    
                const state = JSON.parse(this.cryptoProvider.base64Decode(req.body.state));
                res.redirect(state.redirectTo);
            } catch (error) {
                next(error);
            }
        }
    
        async logout(req, res, next) {
            /**
             * Construct a logout URI and redirect the user to end the
             * session with Microsoft Entra ID. For more information, visit:
             * https://learn.microsoft.com/azure/active-directory/develop/v2-protocols-oidc#send-a-sign-out-request
             */
            //For external tenant
            //const logoutUri = `${this.config.msalConfig.auth.authority}${TENANT_SUBDOMAIN}.onmicrosoft.com/oauth2/v2.0/logout?post_logout_redirect_uri=${this.config.postLogoutRedirectUri}`;
    
            //For workforce tenant
            let logoutUri = `${this.config.msalConfig.auth.authority}/oauth2/v2.0/logout?post_logout_redirect_uri=${this.config.postLogoutRedirectUri}`;
            req.session.destroy(() => {
                res.redirect(logoutUri);
            });
        }
    
        /**
         * Prepares the auth code request parameters and initiates the first leg of auth code flow
         * @param req: Express request object
         * @param res: Express response object
         * @param next: Express next function
         * @param authCodeUrlRequestParams: parameters for requesting an auth code url
         * @param authCodeRequestParams: parameters for requesting tokens using auth code
         */
        async redirectToAuthCodeUrl(req, res, next, authCodeUrlRequestParams, authCodeRequestParams, msalInstance) {
            // Generate PKCE Codes before starting the authorization flow
            const { verifier, challenge } = await this.cryptoProvider.generatePkceCodes();
    
            // Set generated PKCE codes and method as session vars
            req.session.pkceCodes = {
                challengeMethod: 'S256',
                verifier: verifier,
                challenge: challenge,
            };
    
            /**
             * By manipulating the request objects below before each request, we can obtain
             * auth artifacts with desired claims. For more information, visit:
             * https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_node.html#authorizationurlrequest
             * https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_node.html#authorizationcoderequest
             **/
    
            req.session.authCodeUrlRequest = {
                ...authCodeUrlRequestParams,
                redirectUri: this.config.redirectUri,
                responseMode: 'form_post', // recommended for confidential clients
                codeChallenge: req.session.pkceCodes.challenge,
                codeChallengeMethod: req.session.pkceCodes.challengeMethod,
            };
    
            req.session.authCodeRequest = {
                ...authCodeRequestParams,
                redirectUri: this.config.redirectUri,
                code: '',
            };
    
            try {
                const authCodeUrlResponse = await msalInstance.getAuthCodeUrl(req.session.authCodeUrlRequest);
                res.redirect(authCodeUrlResponse);
            } catch (error) {
                next(error);
            }
        }
    
        /**
         * Retrieves oidc metadata from the openid endpoint
         * @returns
         */
        async getAuthorityMetadata() {
            // For external tenant
            //const endpoint = `${this.config.msalConfig.auth.authority}${TENANT_SUBDOMAIN}.onmicrosoft.com/v2.0/.well-known/openid-configuration`;
    
            // For workforce tenant
            const endpoint = `${this.config.msalConfig.auth.authority}/v2.0/.well-known/openid-configuration`;
            try {
                const response = await axios.get(endpoint);
                return await response.data;
            } catch (error) {
                console.log(error);
            }
        }
    }
    
    const authProvider = new AuthProvider({
        msalConfig: msalConfig,
        redirectUri: REDIRECT_URI,
        postLogoutRedirectUri: POST_LOGOUT_REDIRECT_URI,
    });
    
    module.exports = authProvider;
    
    ```

    `/signin`、`/signout`、および`/redirect`ルートは*ルート/auth.js* ファイルで定義されますが、そのロジックは*認証/AuthProvider.js* クラスに実装します。

- `login` メソッドは、次のように `/signin` ルートを処理します。

    - 認証コード フローの第 1 段階をトリガーして、サインイン フローを開始します。
    - 前に作成した MSAL 構成オブジェクト () を使用して、`msalConfig` インスタンスを初期化します。

        ```javascript
            const msalInstance = this.getMsalInstance(this.config.msalConfig);
        ```

        `getMsalInstance` メソッドは次のように定義されます。

        ```javascript
            getMsalInstance(msalConfig) {
                return new msal.ConfidentialClientApplication(msalConfig);
            }
        ```
    - 認証コード フローの最初の区間が、認証コード要求 URL を生成し、その URL にリダイレクトして、承認コードを取得します。 この第 1 段階は、 `redirectToAuthCodeUrl` メソッドで実装されます。 MSAL の [getAuthCodeUrl](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/confidentialclientapplication#@azure-msal-node-confidentialclientapplication-getauthcodeurl) メソッドを使用して承認コード URL を生成する方法に注目してください。

        ```javascript
        //...
        const authCodeUrlResponse = await msalInstance.getAuthCodeUrl(req.session.authCodeUrlRequest);
        //...
        ```

        次に、承認コード URL 自体にリダイレクトします。

        ```javascript
        //...
        res.redirect(authCodeUrlResponse);
        //...
        ```
- `handleRedirect` メソッドは、次のように `/redirect` ルートを処理します。

    - この URL は、「クイック スタート: サンプル Web アプリでユーザーをサインインさせる」の Microsoft Entra 管理センター [で、Web アプリの](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in?pivots=external&tabs=node-external)リダイレクト URI として設定しました。
    - このエンドポイントは、認証コード フローで使用する 2 番めの区間を実装します。 これは、認証コードを使用し、MSAL の [acquireTokenByCode](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node/confidentialclientapplication#@azure-msal-node-confidentialclientapplication-acquiretokenbycode) メソッドを使用して ID トークンを要求します。

        ```javascript
        //...
        const tokenResponse = await msalInstance.acquireTokenByCode(authCodeRequest, req.body);
        //...
        ```
    - 応答を受け取ったら、Express セッションを作成し、必要な任意の情報をそこに格納できます。 `isAuthenticated` を含め、それを `true` に設定する必要があります。

        ```javascript
        //...        
        req.session.idToken = tokenResponse.idToken;
        req.session.account = tokenResponse.account;
        req.session.isAuthenticated = true;
        //...
        ```
- `logout` メソッドは、次のように `/signout` ルートを処理します。

    ```javascript
    async logout(req, res, next) {
        /**
         * Construct a logout URI and redirect the user to end the
            * session with Azure AD. For more information, visit:
            * https://learn.microsoft.com/azure/active-directory/develop/v2-protocols-oidc#send-a-sign-out-request
            */
        const logoutUri = `${this.config.msalConfig.auth.authority}${TENANT_SUBDOMAIN}.onmicrosoft.com/oauth2/v2.0/logout?post_logout_redirect_uri=${this.config.postLogoutRedirectUri}`;
    
        req.session.destroy(() => {
            res.redirect(logoutUri);
        });
    }
    ```

    - サインアウト要求を開始します。
    - ユーザーをアプリケーションからサインアウトさせるときに、ユーザーのセッションを終了させるだけでは処理不足です。 ユーザーを *logoutUri* にリダイレクトする必要があります。 そうしないと、そのユーザーが、資格情報を再入力せずに、アプリケーションに対する再認証を行うことができる可能性があります。 テナントの名前が *contoso* の場合、 *logoutUri* は `https://contoso.ciamlogin.com/contoso.onmicrosoft.com/oauth2/v2.0/logout?post_logout_redirect_uri=http://localhost:3000`のようになります。

#### アプリのログアウト URI と認証メタデータ エンドポイント

外部テナントおよび従業員テナントのアプリのログアウトURI `logoutUri` と機関メタデータエンドポイント `endpoint` は異なるように見えます。 次のようにビルドします。

## [従業員テナント](#tab/workforce-tenant)
```javascript
//Logout URI for workforce tenant
const logoutUri = `${this.config.msalConfig.auth.authority}/oauth2/v2.0/logout?post_logout_redirect_uri=${this.config.postLogoutRedirectUri}`;

//authority metadata endpoint for workforce tenant
const endpoint = `${this.config.msalConfig.auth.authority}/v2.0/.well-known/openid-configuration`;
```

## [外部テナント](#tab/external-tenant)
```javascript
//Logout URI for external tenant
const logoutUri = `${this.config.msalConfig.auth.authority}${TENANT_SUBDOMAIN}.onmicrosoft.com/oauth2/v2.0/logout?post_logout_redirect_uri=${this.config.postLogoutRedirectUri}`;
...

//authority metadata endpoint for external tenant
const endpoint = `${this.config.msalConfig.auth.authority}${TENANT_SUBDOMAIN}.onmicrosoft.com/v2.0/.well-known/openid-configuration`;

```

---

#### ID トークン要求を表示する

コード エディターで "routes/users.js" ファイルを開き、次のコードを追加します。

```javascript
const express = require('express');
const router = express.Router();

// custom middleware to check auth state
function isAuthenticated(req, res, next) {
    if (!req.session.isAuthenticated) {
        return res.redirect('/auth/signin'); // redirect to sign-in route
    }

    next();
};

router.get('/id',
    isAuthenticated, // check if user is authenticated
    async function (req, res, next) {
        res.render('id', { idTokenClaims: req.session.account.idTokenClaims });
    }
);        
module.exports = router;
```

ユーザーが認証されると、`/id`ルートでは*views/id.hbs*ビューを使用してIDトークンのクレームを表示します。 このビューは、前に「[アプリ UI コンポーネントをビルドする](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-sign-in-prepare-app#build-app-ui-components)」で追加しました。

特定の ID トークン クレーム ("指定された名前" など) を抽出するには、次のようにします。

```javascript
const givenName = req.session.account.idTokenClaims.given_name
```

### Web アプリの最終処理

1. コード エディターでファイル *app.js* 開き、 [app.js](https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial/blob/main/1-Authentication/5-sign-in-express/App/app.js) からコードを追加します。
2. コード エディターでファイル *server.js* 開き、 [server.js](https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial/blob/main/1-Authentication/5-sign-in-express/App/server.js) からコードを追加します。
3. コード エディターでファイル *package.json* 開き、 `scripts` プロパティを次の内容に更新します。

    ```json
    "scripts": {
    "start": "node server.js"
    }
    ```

### Node/Express.js Web アプリを実行してテストする

この時点で、ノード Web アプリをテストできます。

## [従業員テナント](#tab/workforce-tenant)
1. 「 [新しいユーザーの作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users#create-a-new-user) 」の手順に従って、ワークフォース テナントにテスト ユーザーを作成します。 テナントにアクセスできない場合は、テナント管理者にユーザーの作成を依頼してください。
2. サーバーを起動するには、プロジェクト ディレクトリ内から次のコマンドを実行します。

    ```console
    npm start
    ```
3. ブラウザーを開き、 `http://localhost:3000`に移動します。 以下のスクリーンショットのようなページが表示されます。

    [Image: ノード Web アプリへのサインインのスクリーンショット。]
4. **サインイン**を選択して、サインイン プロセスを開始します。 初めてサインインすると、次のスクリーンショットに示すように、アプリケーションによるサインインとプロファイルへのアクセスを許可するための同意を求められます。

    [Image: ユーザーの同意画面を表示するスクリーンショット]

正常にサインインすると、アプリケーションのホーム ページにリダイレクトされます。

## [外部テナント](#tab/external-tenant)
1. ターミナルで、 `ciam-sign-in-node-express-web-app`などの Web アプリを含むプロジェクト フォルダーにいることを確認します。
2. ご利用のターミナルで、次のコマンドを実行します。

    ```powershell
    npm start
    ```
3. ブラウザーを開き、 `http://localhost:3000`に移動します。 以下のスクリーンショットのようなページが表示されます。

    [Image: ノード Web アプリへのサインインのスクリーンショット。]
4. ページの読み込みが完了したら、**[サインイン]** リンクを選択します。 サインインするように要求されます。
5. サインイン ページで、**[メール アドレス]** を入力して **[次へ]** を選択し、**[パスワード]** を入力してから **[サインイン]** を選択します。 アカウントをお持ちでない場合は、**[アカウントをお持ちではない場合、作成できます]** リンクを選択します。これで、サインアップ フローが開始されます。
6. サインアップ オプションを選択した場合は、メール、ワンタイム パスコード、新しいパスワード、その他のアカウントの詳細を入力すると、サインアップ フロー全体が完了します。 以下のスクリーンショットのようなページが表示されます。 サインイン オプションを選択すると、同様のページが表示されます。

    [Image: ID トークン要求の表示のスクリーンショット。]
7. [ **サインアウト** ] を選択してユーザーを Web アプリからサインアウトするか、[ **ID トークン要求の表示** ] を選択してすべての ID トークン要求を表示します。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-web-app-python-flask-call-microsoft-graph-api"} -->
## チュートリアル: Python Flask Web アプリから Microsoft Graph API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-python-flask-call-microsoft-graph-api
- Service: identity-platform
- Article date: 2025-02-24
- Summary: Python Flask Web アプリから Microsoft Graph API を呼び出す

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、Python Flask Web アプリから Microsoft Graph API を呼び出します。 前の [チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-python-flask-sign-in-out)では、サインインエクスペリエンスとサインアウト エクスペリエンスをアプリケーションに追加しました。 ユーザーがサインインすると、アプリは Microsoft Graph API を呼び出すアクセス トークンを取得します。

このチュートリアルでは、次の操作を行います。

- 既存の Python Flask Web アプリを更新してアクセス トークンを取得する
- アクセス トークンを使用して Microsoft Graph API を呼び出します。

### [前提条件]

[「チュートリアル: Microsoft ID プラットフォームを使用して Python Flask Web アプリにサインインを追加](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-sign-in-sign-out)する」の手順を完了します。

### スコープと API エンドポイントを定義する

この例では、Microsoft Graph API を呼び出して、サインインしているユーザーのプロファイル情報を取得します。 アプリが従業員テナント内にある場合、サインイン時に、ユーザーは Microsoft Graph API にアクセスするためにアプリで必要なスコープに同意します。 アプリが外部テナントにある場合は、テナント内 [のユーザーに代わって管理者の同意を付与](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#grant-admin-consent-external-tenants-only)してください。 その後、アプリはアクセス トークンを使用して API を呼び出し、結果を表示します。

*.env* ファイルに、呼び出すエンドポイントと、Microsoft Graph API を呼び出すために必要なスコープを追加します。

```
SCOPE=User.Read
ENDPOINT=https://graph.microsoft.com/v1.0/me
```

*app\_config.py* ファイルを更新して、アプリ内の新しい構成を読み取ります。

```python
# other configs go here
SCOPE = os.getenv("SCOPE")
ENDPOINT = os.getenv("ENDPOINT")
```

### 保護された API を呼び出す

1. API エンドポイントをホーム ページに渡します。 これにより、エンドポイントを呼び出します。 次のコード スニペットに示すように、 `/` ルートを更新します。

    ```python
    @app.route("/")
    @auth.login_required
    def index(*, context):
        return render_template(
            'index.html',
            user=context['user'],
            title="Flask Web App Sample",
            api_endpoint=os.getenv("ENDPOINT") # added this line
        )
    ```
2. 次のコード スニペットに示すように、保護された Microsoft Graph API を呼び出します。 アプリで使用する必要があるスコープの一覧を渡します。 スコープが存在する場合、コンテキストにはアクセス トークンが含まれます。 アクセス トークンは、ダウンストリーム API を呼び出すために使用されます。 *app.py* ファイルに次のコードを追加します。

    ```python
    @app.route("/call_api")
    @auth.login_required(scopes=os.getenv("SCOPE", "").split())
    def call_downstream_api(*, context):
        api_result = requests.get(  # Use access token to call a web api
            os.getenv("ENDPOINT"),
            headers={'Authorization': 'Bearer ' + context['access_token']},
            timeout=30,
        ).json() if context.get('access_token') else "Did you forget to set the SCOPE environment variable?"
        return render_template('display.html', title="API Response", result=api_result)
    ```

    アプリがアクセス トークンを正常に取得すると、 `requests.get(...)` メソッドを使用してダウンストリーム API に HTTP 要求が行われます。 要求では、ダウンストリーム API URL は `app_config.ENDPOINT` で指定され、アクセス トークンは要求ヘッダーの `Authorization` フィールドに渡されます。

    ダウンストリーム API (Microsoft Graph API) への要求が成功すると、 `api_result` 変数に格納され、レンダリングのために `display.html` テンプレートに渡される JSON 応答が返されます。

### API の結果を表示する

*templates* フォルダーに *display.html* という名前のファイルを作成します。 このページには、Microsoft Graph エンドポイントへの呼び出しの結果が表示されます。 *display.html* ファイルに次のコードを追加します。

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Microsoft Identity Python Web App: API</title>
</head>
<body>
    <a href="javascript:window.history.go(-1)">Back</a> <!-- Displayed on top of a potentially large JSON response, so it will remain visible -->
    <h1>{{title}}</h1>
    <pre>{{ result |tojson(indent=4) }}</pre> <!-- Just a generic json viewer -->
</body>
</html>
```

### サンプル Web アプリを実行してテストする

## [従業員テナント](#tab/workforce-tenant)
1. ご利用のターミナルで、次のコマンドを実行します。

    ```console
    python3 -m flask run --debug --host=localhost --port=3000
    ```

    選択したポートを使用できます。 このポートは、先ほど登録したリダイレクト URI のポートに似ている必要があります。
2. ブラウザーを開き、 `http://localhost:3000`に移動します。 サインイン ページが表示されます。
3. 手順に従って、Microsoft アカウントでサインインします。 サインインするための電子メール アドレスとパスワードを指定するように求められます。
4. アプリケーションで必要なスコープがある場合は、同意画面が表示されます。 アプリケーションは、アクセスを許可するデータへのアクセスを維持し、サインインするためのアクセス許可を要求します。 **[Accept](https://learn.microsoft.com/ja-jp/entra/identity-platform/承認)** を選択します。 スコープが定義されていない場合、この画面は表示されません。

## [外部テナント](#tab/external-tenant)
1. ご利用のターミナルで、次のコマンドを実行します。

    ```console
    python3 -m flask run --debug --host=localhost --port=3000
    ```

    選択したポートを使用できます。 このポートは、先ほど登録したリダイレクト URI のポートに似ている必要があります。
2. ブラウザーを開き、 `http://localhost:3000`に移動します。 サインイン ページが表示されます。
3. サインイン ページで、**[メール アドレス]** を入力して **[次へ]** を選択し、**[パスワード]** を入力してから **[サインイン]** を選択します。 アカウントをお持ちでない場合は、**[アカウントをお持ちではない場合、作成できます]** リンクを選択します。これで、サインアップ フローが開始されます。
4. サインアップ オプションを選択した場合は、サインアップ フローを実行します。 サインアップ フロー全体を完了するには、メール、ワンタイム パスコード、新しいパスワード、その他のアカウントの詳細を入力します。
5. アプリケーションで必要なスコープがある場合は、同意画面が表示されます。 アプリケーションは、アクセスを許可するデータへのアクセスを維持し、サインインしてプロファイルを読み取るアクセス許可を要求します (スクリーンショットを参照)。 **[Accept](https://learn.microsoft.com/ja-jp/entra/identity-platform/承認)** を選択します。

---

### API の呼び出し

1. ホーム ページで [ **API の呼び出し** ] リンクを選択します。 アプリは Microsoft Graph API を呼び出して、サインインしているユーザーのプロファイル情報を取得します。 アプリには、API の呼び出しの結果が表示されます。
2. [ **ログアウト** ] を選択してアプリからサインアウトします。 サインアウトするアカウントを選択するように求められます。 サインインに使用したアカウントを選択します。

### 参考資料

[ms_identity_python](https://github.com/azure-samples/ms-identity-python)は、MSAL ライブラリの詳細を抽象化します。 詳細については、 [MSAL Python のドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/msal/python/)。 このリファレンス 資料は、MSAL Python を使用してアプリを初期化し、トークンを取得する方法を理解するのに役立ちます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/tutorial-web-app-python-flask-sign-in-out"} -->
## チュートリアル: Microsoft ID プラットフォームを使用して Python Flask Web アプリにユーザーをサインインさせる - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-python-flask-sign-in-out
- Service: identity-platform
- Article date: 2025-02-24
- Summary: Microsoft ID プラットフォームを使用して Python Flask Web アプリにユーザーをサインインさせる方法について説明します

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 従業員テナント [Image: :次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このチュートリアルでは、Python Flask Web アプリのセキュリティ保護について説明します。

このチュートリアルでは、次の操作を行います。

- Python Flask プロジェクトを作成する
- 必要な依存関係をインストールする
- 認証に Microsoft ID プラットフォームを使用するように Flask Web アプリを構成する
- Flask Web アプリでサインインとサインアウトのエクスペリエンスをテストする

### [前提条件]

## [従業員テナント](#tab/workforce-tenant)
- 従業員テナント。 [既定のディレクトリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)を使用するか、新しいテナントを設定できます。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- アプリの登録にクライアント シークレットを追加します。 運用アプリではクライアント シークレットを使用**しないでください**。 代わりに、証明書またはフェデレーション資格情報を使用してください。 詳細については、「 [アプリケーションに資格情報を追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=client-secret)。

## [外部テナント](#tab/external-tenant)
- テナントにアプリの登録が行われていることを確認してください。 アプリの登録の詳細から次の情報があることを確認します。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- アプリの登録にクライアント シークレットを追加します。 運用アプリではクライアント シークレットを使用**しないでください**。 代わりに、証明書またはフェデレーション資格情報を使用してください。 詳細については、「 [アプリケーションに資格情報を追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials?tabs=client-secret)。
- Web アプリを登録した *ディレクトリ (テナント) サブドメイン* を抽出します。 テナント名がない場合は、[テナントの詳細を読み取る方法を確認してください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)。
- Microsoft Entra 管理センターでアプリをユーザー フローに関連付けます。 このユーザー フローは、複数のアプリケーションで使用できます。 詳細については、「 [外部テナント内のアプリのセルフサービス サインアップ ユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) 」および「 [ユーザー フローにアプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)」を参照してください。

---

- [Python 3 以降](https://www.python.org/)。
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコードエディター。

### Flask プロジェクトを作成する

1. *Flask-web-app* など、Flask アプリケーションをホストするフォルダーを作成します。
2. コンソール ウィンドウを開き、コマンドを使用して Flask Web アプリ フォルダーのディレクトリに移動します

    ```bash
    cd flask-web-app
    ```
3. 仮想環境を設定する

    オペレーティング システムに応じて、次のコマンドを実行して仮想環境を設定し、アクティブ化します。

    Windows オペレーティング システムの場合:

    ```bash
    py -m venv .venv
    .venv\scripts\activate
    ```

    macOS または Linux オペレーティング システムの場合:

    ```Bash
    python3 -m venv .venv
    source .venv/bin/activate
    ```

### アプリの依存関係をインストールする

アプリの依存関係をインストールするには、次のコマンドを実行します。

```console
pip install flask
pip install python-dotenv
pip install requests
pip install "ms_identity_python[flask] @ git+https://github.com/azure-samples/ms-identity-python@0.9"
```

インストールする *ms\_identity\_python* ライブラリでは、依存関係として Python 用 Microsoft Authentication Library (MSAL) が自動的にインストールされます。 MSAL Python は、ユーザーの認証とアクセス トークンの管理を可能にするライブラリです。

必要なライブラリをインストールしたら、次のコマンドを実行して要件ファイルを更新します。

```console
pip freeze > requirements.txt
```

### 認証用にアプリケーションを構成する

Microsoft ID プラットフォームを使用してユーザーをサインインする Web アプリケーションは、構成ファイル *.env* を使用して構成されます。 Python Flask では、次の値を指定する必要があります。

## [従業員テナント](#tab/workforce-tenant)
| 環境変数 | Description |
| --- | --- |
| `AUTHORITY` | アプリケーションが登録されているクラウド インスタンスの URL。 形式: `https://{Instance}/{TenantId}`. 次のいずれかのインスタンス値を使用します。 - `https://login.microsoftonline.com/` (Azure パブリック クラウド) - `https://login.microsoftonline.us/` (Azure 米国政府機関) - `https://login.microsoftonline.de/` (Microsoft Entra Germany) - `https://login.partner.microsoftonline.cn/` (21Vianet が運営する Microsoft Entra China) |
| `TENANT_ID` | アプリが登録されているテナントの識別子。 アプリの登録からテナント ID を優先するか、次のいずれかを使用します。 - `organizations`: 職場または学校アカウントのユーザーのサインイン - `common`: 職場または学校アカウントまたは Microsoft 個人アカウントを使用してユーザーをサインインします - `consumers`: Microsoft 個人アカウントでのみユーザーをサインインさせる |
| `CLIENT_ID` | アプリ登録から取得したアプリケーション (クライアント) の識別子。 |
| `CLIENT_SECRET` | Microsoft Entra 管理センターで [資格情報を追加](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials) して取得したシークレット値。 |
| `REDIRECT_URI` | 認証後に Microsoft ID プラットフォームがセキュリティ トークンを送信する URI。 |

## [外部テナント](#tab/external-tenant)
| 環境変数 | Description |
| --- | --- |
| `AUTHORITY` | アプリケーションが登録されている外部テナントの URL。 形式: `https://<tenant_subdomain>.ciamlogin.com/` (または `https://<tenant_subdomain>.onmicrosoft.com/`)。 テナント サブドメインを取得するには、 [外部テナントの作成](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details) に関するページを参照してください。 |
| `CLIENT_ID` | アプリ登録からのアプリケーション (クライアント) の識別子。 |
| `CLIENT_SECRET` | Microsoft Entra 管理センターで [資格情報を追加](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials) して取得したクライアント シークレットの値。 |
| `REDIRECT_URI` | 認証後に Microsoft ID プラットフォームがセキュリティ トークンを送信する URI。 |

---

#### 構成ファイルを更新する

1. ルート フォルダーに *.env* ファイルを作成して、アプリの構成を安全に格納します。 *.env* ファイルには、次の環境変数が含まれている必要があります。

## [従業員テナント](#tab/workforce-tenant)
```env
    CLIENT_ID="<Enter_your_client_id>"
    CLIENT_SECRET="<Enter_your_client_secret>"
    AUTHORITY="https://login.microsoftonline.com/<Enter_tenant_id>"
    REDIRECT_URI="<Enter_redirect_uri>"
    ```

プレースホルダーを次の値に置き換えます。

    - `<Enter_your_client_id>`を、登録したクライアント Web アプリの*アプリケーション (クライアント) ID* に置き換えます。
    - `<Enter_tenant_id>`を、Web アプリを登録した*ディレクトリ (テナント) ID* に置き換えます。
    - `<Enter_your_client_secret>`を、作成した Web アプリの*クライアント シークレット*値に置き換えます。 このチュートリアルでは、デモンストレーションのためにシークレットを使用します。 運用環境では、 [証明書やフェデレーション ID 資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials)などのより安全な方法を使用します。
    - `<Enter_redirect_uri>`を、先ほど登録したリダイレクト URI に置き換えます。 このチュートリアルでは、リダイレクト URI パスを `http://localhost:3000/getAToken` に設定します。

## [外部テナント](#tab/external-tenant)
```env
    CLIENT_ID="<Enter_your_client_id>"
    CLIENT_SECRET="<Enter_your_client_secret>"
    AUTHORITY=https://<Enter_your_subdomain>.ciamlogin.com/<Enter_your_subdomain>.onmicrosoft.com
    REDIRECT_URI="<Enter_redirect_uri>"
    ```

プレースホルダーを次の値に置き換えます。

    - `<Enter_your_client_id>`を、登録したクライアント Web アプリの*アプリケーション (クライアント) ID* に置き換えます。
    - `<Enter_your_subdomain>`を、Web アプリを登録した*ディレクトリ (テナント) サブドメイン*に置き換えます。
    - `<Enter_your_client_secret>`を、作成した Web アプリの*クライアント シークレット*値に置き換えます。 このチュートリアルでは、デモンストレーションのためにシークレットを使用します。 運用環境では、 [証明書やフェデレーション ID 資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials)などのより安全な方法を使用します。
    - `<Enter_redirect_uri>`を、先ほど登録したリダイレクト URI に置き換えます。 このチュートリアルでは、リダイレクト URI パスを `http://localhost:3000/getAToken` に設定します。

---
2. 環境変数を読み取り、必要な他の構成を追加する *app\_config.py* ファイルを作成します。

    ```python
    import os
    
    AUTHORITY = os.getenv("AUTHORITY")
    CLIENT_ID = os.getenv("CLIENT_ID")
    CLIENT_SECRET = os.getenv("CLIENT_SECRET")
    REDIRECT_URI = os.getenv("REDIRECT_URI")
    SESSION_TYPE = "filesystem" # Tells the Flask-session extension to store sessions in the filesystem. Don't use in production apps.
    ```

### アプリ エンドポイントを構成する

この段階では、Web アプリ エンドポイントを作成し、ビジネス ロジックをアプリケーションに追加します。

1. ルート フォルダーに *app.py* という名前のファイルを作成します。
2. 必要な依存関係を *app.py* ファイルの先頭にインポートします。

    ```python
    import os
    import requests
    from flask import Flask, render_template
    from identity.flask import Auth
    import app_config
    ```
3. Flask アプリを初期化し、 *app\_config.py* ファイルで指定したセッション ストレージの種類を使用するように構成します。

    ```python
    app = Flask(__name__)
    app.config.from_object(app_config)
    ```
4. アプリ クライアントを初期化します。 Flask Web アプリは機密クライアントです。 クライアントシークレットを、機密クライアントが安全に保存できるため、提供します。 内部では、ID ライブラリは MSAL ライブラリの `ConfidentialClientApplication` クラスを呼び出します。

    ```python
    auth = Auth(
        app,
        authority=app.config["AUTHORITY"],
        client_id=app.config["CLIENT_ID"],
        client_credential=app.config["CLIENT_SECRET"],
        redirect_uri=app.config["REDIRECT_URI"]
    )
    ```
5. 必要なエンドポイントを Flask アプリに追加します。 Web アプリは、承認コード フローを使用してユーザーをサインインします。 *ms\_identity\_python* MSAL ラッパー ライブラリは、MSAL ライブラリとの対話に役立ちます。そのため、サインインを追加してアプリにサインアウトすることが容易になります。 インデックス ページを追加し、`login_required`提供される デコレーターを使用して保護します。 `login_required` デコレーターにより、認証されたユーザーのみがインデックス ページにアクセスできるようになります。

    ```python
    @app.route("/")
    @auth.login_required
    def index(*, context):
        return render_template(
            'index.html',
            user=context['user'],
            title="Flask Web App Sample",
        )
    ```

    このビューを `@login_required`で装飾したため、ユーザーが存在することが保証されます。

### アプリ テンプレートを作成する

ルート フォルダーにテンプレートという名前 *の* フォルダーを作成します。 templates フォルダーに、 *index.html*という名前のファイルを作成します。 これはアプリのホームページです。 *index.html* ファイルに次のコードを追加します。

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>{{ title }}</title>
</head>
<body>
    <h1>{{ title }}</h1>
    <h2>Welcome {{ user.get("name") }}!</h2>

    <img src="https://github.com/Azure-Samples/ms-identity-python-webapp-django/raw/main/static/topology.png" alt="Topology">

    <ul>
    {% if api_endpoint %}
        <!-- If an API endpoint is declared and scopes defined, this link will show. We set this in the call an API tutorial. For this tutorial, we do not define this endpoint. -->
        <li><a href='/call_api'>Call an API</a></li>
    {% endif %}

    <li><a href="{{ url_for('identity.logout') }}">Logout</a></li>
    </ul>

    <hr>
    <footer style="text-align: right">{{ title }}</footer>
</body>
</html>
```

### サンプル Web アプリを実行してテストする

## [従業員テナント](#tab/workforce-tenant)
1. ご利用のターミナルで、次のコマンドを実行します。

    ```console
    python3 -m flask run --debug --host=localhost --port=3000
    ```

    選択したポートを使用できます。 このポートは、先ほど登録したリダイレクト URI のポートに似ている必要があります。
2. ブラウザーを開き、 `http://localhost:3000`に移動します。 サインイン ページが表示されます。
3. 手順に従って、Microsoft アカウントでサインインします。 サインインするための電子メール アドレスとパスワードを指定するように求められます。
4. アプリケーションで必要なスコープがある場合は、同意画面が表示されます。 アプリケーションは、アクセスを許可するデータへのアクセスを維持し、サインインするためのアクセス許可を要求します。 **を選択し、**を同意します。 スコープが定義されていない場合、この画面は表示されません。

## [外部テナント](#tab/external-tenant)
1. ご利用のターミナルで、次のコマンドを実行します。

    ```console
    python3 -m flask run --debug --host=localhost --port=3000
    ```

    選択したポートを使用できます。 このポートは、先ほど登録したリダイレクト URI のポートに似ている必要があります。
2. ブラウザーを開き、 `http://localhost:3000`に移動します。 サインイン ページが表示されます。
3. サインイン ページで、**[メール アドレス]** を入力して **[次へ]** を選択し、**[パスワード]** を入力してから **[サインイン]** を選択します。 アカウントをお持ちでない場合は、**[アカウントをお持ちではない場合、作成できます]** リンクを選択します。これで、サインアップ フローが開始されます。
4. サインアップ オプションを選択した場合は、サインアップ フローを実行します。 サインアップ フロー全体を完了するには、メール、ワンタイム パスコード、新しいパスワード、その他のアカウントの詳細を入力します。
5. アプリケーションで必要なスコープがある場合は、同意画面が表示されます。 アプリケーションは、アクセスを許可するデータへのアクセスを維持し、サインインしてプロファイルを読み取るアクセス許可を要求します (スクリーンショットを参照)。 **を選択し、**を同意します。

---

サインインまたはサインアップすると、Web アプリにリダイレクトされます。 次のスクリーンショットのようなページが表示されます。

[Image: 認証が成功した後の Flask Web アプリサンプルのスクリーンショット。]

[ **ログアウト** ] を選択してアプリからサインアウトします。 サインアウトするアカウントを選択するように求められます。 サインインに使用したアカウントを選択します。

### カスタム URL ドメインを使用する (省略可能)

## [従業員テナント](#tab/workforce-tenant)
ワークフォース テナントは、カスタム URL ドメインをサポートしていません。

## [外部テナント](#tab/external-tenant)
カスタム URL ドメインを使用して、認証 URL を完全にブランド化します。 ユーザーの視点から見ると、認証プロセスの間、ユーザーは *ciamlogin.com* ドメイン名にリダイレクトされず、あなたのドメインにとどまります。

カスタム URL ドメインを使用するには、次の手順に従います。

1. 「[外部テナント内のアプリに対するカスタム URL ドメインの有効化](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-custom-url-domain)」の手順を使用し、外部テナントに対してカスタム URL ドメインを有効にします。
2. *.env* ファイルで、変数`OIDC_AUTHORITY`変数を追加し、`AUTHORITY`変数を削除します。 その値を *https://Enter\_the\_Custom\_Domain\_Here/Enter\_the\_Tenant\_ID\_Here/v2.0*に設定します。 `Enter_the_Custom_Domain_Here` を実際のカスタム URL ドメインに、`Enter_the_Tenant_ID_Here` を実際のテナント ID に置き換えます。 テナント ID がわからない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください。
3. app\_config.py ファイルに次のコードを追加して、 `OIDC_AUTHORITY` 環境変数を読み取ります。 `AUTHORITY`変数は削除できます。

    ```python
    # other configs go here
    OIDC_AUTHORITY = os.getenv("OIDC_AUTHORITY")
    ```
4. *app.py* ファイルで、*権限*の代わりに oidc\_authority 引数*を*使用するように認証オブジェクトを更新します。

    ```python
    auth = Auth(
        app,
        oidc_authority=app.config["OIDC_AUTHORITY"],
        client_id=app.config["CLIENT_ID"],
        client_credential=app.config["CLIENT_SECRET"],
        redirect_uri=app.config["REDIRECT_URI"]
    )
    ```

### 参考資料

[ms_identity_python](https://github.com/azure-samples/ms-identity-python)は、MSAL ライブラリの詳細を抽象化します。 詳細については、 [MSAL Python のドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/msal/python/)。 このリファレンス 資料は、MSAL Python を使用してアプリを初期化し、トークンを取得する方法を理解するのに役立ちます。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/userinfo"} -->
## Microsoft ID プラットフォーム UserInfo エンドポイント - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/userinfo
- Service: identity-platform
- Article date: 2023-12-19
- Summary: Microsoft ID プラットフォーム上の UserInfo エンドポイントについて説明します。

OpenID Connect (OIDC) 標準の一部として、 [UserInfo エンドポイント](https://openid.net/specs/openid-connect-core-1_0.html#UserInfo) は認証済みユーザーに関する情報を返します。

### .well-known 構成エンドポイントを見つける

プログラムで UserInfo エンドポイントを見つけるには、`userinfo_endpoint`の OpenID 構成ドキュメントの`https://login.microsoftonline.com/common/v2.0/.well-known/openid-configuration` フィールドを読み取ります。 アプリケーションで UserInfo エンドポイントをハードコーディングすることはお勧めしません。 代わりに、OIDC 構成ドキュメントを使用して、実行時にエンドポイントを検索します。

UserInfo エンドポイントは、通常、 [OIDC 準拠ライブラリ](https://openid.net/developers/certified/) によって自動的に呼び出され、ユーザーに関する情報を取得します。 [OIDC 標準で識別された要求の一覧](https://openid.net/specs/openid-connect-core-1_0.html#StandardClaims)から、Microsoft ID プラットフォームは、名前要求、サブジェクト要求、および電子メールが使用可能で同意された場合に生成します。

### 代わりに ID トークンを使用することを検討してください

ID トークン内の情報は、UserInfo エンドポイントで使用できる情報のスーパーセットです。 UserInfo エンドポイントを呼び出すトークンを取得すると同時に ID トークンを取得できるため、UserInfo エンドポイントを呼び出す代わりに、トークンからユーザーの情報を取得することをお勧めします。 UserInfo エンドポイントを呼び出す代わりに ID トークンを使用すると、最大 2 つのネットワーク要求が不要になります。これにより、アプリケーションの待機時間が短縮されます。

マネージャーや役職などのユーザーの詳細が必要な場合は、 [Microsoft Graph `/user` API](https://learn.microsoft.com/ja-jp/graph/api/user-get) を呼び出します。 [また、省略可能な要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)を使用して、ID とアクセス トークンに追加のユーザー情報を含めることもできます。

### UserInfo エンドポイントを呼び出す

UserInfo は、Microsoft Graph によってホストされる標準の OAuth ベアラー トークン API です。 アプリケーションが Microsoft Graph へのアクセスを要求したときに受け取ったアクセス トークンを使用して、Microsoft Graph API を呼び出すのと同様に、UserInfo エンドポイントを呼び出します。 UserInfo エンドポイントは、ユーザーに関する要求を含む JSON 応答を返します。

#### 権限

UserInfo API を呼び出すには、次の [OIDC アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc#openid-connect-scopes) を使用します。 `openid`要求が必要であり、`profile`スコープと`email`スコープにより、応答に追加情報が確実に提供されます。

| アクセス許可の種類 | 権限 |
| --- | --- |
| 委任 (勤務先または学校アカウント) | `openid` (必須)、 `profile`、 `email` |
| 委任 (個人用 Microsoft アカウント) | `openid` (必須)、 `profile`、 `email` |
| アプリケーション | 適用なし |

ヒント

ブラウザーでこの URL をコピーして、UserInfo エンドポイントのアクセス トークンと [ID トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)を取得します。 クライアント ID とリダイレクト URI をアプリ登録の値に置き換えます。

`https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id=<yourClientID>&response_type=token+id_token&redirect_uri=<YourRedirectUri>&scope=user.read+openid+profile+email&response_mode=fragment&state=12345&nonce=678910`

次のセクションのクエリで返されるアクセス トークンを使用できます。

Microsoft Graph では、アプリの読み取りまたは検証機能に影響を与える可能性がある特別なトークン発行パターンを使用します。 他の Microsoft Graph トークンと同様に、ここで受け取るトークンは JWT ではない可能性があり、アプリでは不透明と見なす必要があります。 Microsoft アカウント ユーザーにサインインした場合は、暗号化されたトークン形式になります。 ただし、これらの要因はいずれも、UserInfo エンドポイントへの要求でアクセス トークンを使用するアプリの機能に影響しません。

#### API の呼び出し

UserInfo API は、GET 要求と POST 要求の両方をサポートします。

```http
GET or POST /oidc/userinfo HTTP/1.1
Host: graph.microsoft.com
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJub25jZSI6Il…
```

#### UserInfo 応答

```jsonc
{
    "sub": "OLu859SGc2Sr9ZsqbkG-QbeLgJlb41KcdiPoLYNpSFA",
    "name": "Mikah Ollenburg", // all names require the “profile” scope.
    "family_name": " Ollenburg",
    "given_name": "Mikah",
    "picture": "https://graph.microsoft.com/v1.0/me/photo/$value",
    "email": "mikoll@contoso.com" // requires the “email” scope.
}
```

応答に表示される要求は、UserInfo エンドポイントが返すことができるすべての要求です。 これらの値は、 [ID トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)に含まれているのと同じ値です。

### UserInfo エンドポイントに関する注意事項と注意事項

UserInfo エンドポイントによって返される情報を追加またはカスタマイズすることはできません。

認証と承認時に ID プラットフォームによって返される情報をカスタマイズするには、 [要求マッピング](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization) とオプションの [要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims) を使用してセキュリティ トークンの構成を変更します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/v2-admin-consent"} -->
## Microsoft ID プラットフォーム管理者の同意プロトコル - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-admin-consent
- Service: identity-platform
- Article date: 2023-11-08
- Summary: スコープ、アクセス許可、同意など、Microsoft ID プラットフォームでの承認の説明。

一部のアクセス許可は、テナント内で付与する前に管理者の同意が必要です。 管理者の同意エンドポイントを使用して、テナント全体にアクセス許可を付与することもできます。

### 推奨:ユーザーをアプリにサインインさせる

通常、管理者の同意エンドポイントを使用するアプリケーションを構築する場合は、アプリ側に管理者がアプリのアクセス許可を承認できるページやビューが必要です。 このページは、アプリのサインアップのフローの一部、アプリの設定の一部、または専用の "接続" フローにすることができます。 多くの場合、ユーザーが職場または学校の Microsoft アカウントでサインインした後にのみ、アプリがこの "接続" ビューを表示することが合理的です。

ユーザーをアプリにサインインさせる場合、管理者に必要なアクセス許可の承認を求める前に、管理者が所属する組織を特定できます。 絶対に必要というわけではないものの、組織ユーザーにとってより直感的なエクスペリエンスを作成するのに役立ちます。

### ディレクトリ管理者にアクセス許可を要求する

組織の管理者にアクセス許可を要求する準備ができたら、ユーザーを Microsoft ID プラットフォーム *管理者の同意エンドポイント*にリダイレクトできます。

```none
https://login.microsoftonline.com/{tenant}/v2.0/adminconsent
        ?client_id=00001111-aaaa-2222-bbbb-3333cccc4444
        &scope=https://graph.microsoft.com/Calendars.Read https://graph.microsoft.com/Mail.Send
        &redirect_uri=http://localhost/myapp/permissions
        &state=12345
```

| パラメーター | 条件 | 説明 |
| --- | --- | --- |
| `tenant` | 必須 | アクセス許可を要求するディレクトリ テナント。 GUID またはフレンドリ名形式で指定することも、例に示すように `organizations` で一般的に参照することもできます。 個人アカウントはテナントのコンテキストを除いて管理者の同意を提供できないため、"共通" を使用しないでください。 テナントを管理する個人用アカウントとの最善の互換性を確保するには、可能であればテナント ID を使用します。 |
| `client_id` | 必須 | **[Microsoft Entra 管理センター - アプリの登録]** エクスペリエンスからアプリに割り当てられた[アプリケーション (クライアント) ID](https://go.microsoft.com/fwlink/?linkid=2083908)。 |
| `redirect_uri` | 必須 | アプリが処理するために応答を送信するリダイレクト URI。 アプリケーション登録ポータルで登録したリダイレクト URI のいずれかと完全に一致させる必要があります。 |
| `state` | 推奨 | 要求に含まれ、かつトークンの応答として返される値。 任意のコンテンツの文字列を指定することができます。 この状態は、認証要求の前にアプリ内でユーザーの状態 (表示中のページやビューなど) に関する情報をエンコードする目的に使用します。 |
| `scope` | 必須 | アプリケーションによって要求されるアクセス許可のセットを定義します。 静的スコープ ( `/.default` を使用) または動的スコープのいずれかを指定できます。 これには、OIDC スコープ (`openid`、 `profile`、 `email`) を含めることができます。 |

現在 Microsoft Entra ID では、テナント管理者がサインインして、要求を完了する必要があります。 管理者は、 `scope` パラメーターで要求したすべてのアクセス許可を承認するように求められます。 静的 (`/.default`) 値を使用した場合、v1.0 管理者の同意エンドポイントと同様に機能し、必要なアクセス許可 (ユーザーとアプリの両方) で見つかったすべてのスコープに対して同意を要求します。 アプリのアクセス許可を要求するには、 `/.default` 値を使用する必要があります。 `/.default`を使用するときに管理者の同意画面で常に特定のアクセス許可を管理者に表示させたくない場合は、必要なアクセス許可セクションにアクセス許可を配置しないことをお勧めします。 代わりに、動的同意を使用して、 `/.default`を使用するのではなく、実行時に同意画面に表示するアクセス許可を追加できます。

#### 成功応答

管理者がアプリのアクセス許可を承認した場合、成功した応答は次のようになります。

```none
http://localhost/myapp/permissions
    ?admin_consent=True
    &tenant=aaaabbbb-0000-cccc-1111-dddd2222eeee
    &scope=https://graph.microsoft.com/Calendars.Read https://graph.microsoft.com/Mail.Send
    &state=12345
```

| パラメーター | 説明 |
| --- | --- |
| `tenant` | アプリケーションに要求されたアクセス許可を GUID 形式で付与したディレクトリ テナント。 |
| `state` | トークン応答にも返される要求に含まれる値。 任意のコンテンツの文字列を指定することができます。 状態は、認証要求が発生する前のユーザーの状態 (ページやビューなど) に関する情報をエンコードするために使用されます。 |
| `scope` | アプリケーションに対するアクセス権が付与されたアクセス許可のセット。 |
| `admin_consent` | `True`に設定されます。 |

Warnung

ユーザーの認証または承認には、 パラメーターの`tenant` 値を使用しないでください。 テナント ID の値は、アプリへの応答を偽装するために、不適切なアクターによって更新および送信できます。 これにより、アプリケーションがセキュリティ インシデントに公開される可能性があります。

#### エラー応答

```none
http://localhost/myapp/permissions
        ?admin_consent=True
        &error=consent_required
        &error_description=AADSTS65004%3a+The+resource+owner+or+authorization+server+denied+the+request.%0d%0aTrace+ID%3a+0000aaaa-11bb-cccc-dd22-eeeeee333333%0d%0aCorrelation+ID%3a+8478d534-5b2c-4325-8c2c-51395c342c89%0d%0aTimestamp%3a+2019-09-24+18%3a34%3a26Z
        &state=12345
```

正常な応答に表示されるパラメーターに追加すると、エラー パラメーターは次のようになります。

| パラメーター | 説明 |
| --- | --- |
| `error` | 発生したエラーの種類を分類したりエラーに対処したりする際に使用するエラー コード文字列。 |
| `error_description` | 開発者がエラーの根本原因を特定するのに役立つ特定のエラー メッセージ。 |
| `state` | トークン応答にも返される要求に含まれる値。 任意のコンテンツの文字列を指定することができます。 状態は、認証要求が発生する前のユーザーの状態 (ページやビューなど) に関する情報をエンコードするために使用されます。 |
| `admin_consent` | この応答が管理者の同意フローで発生したことを示す `True` に設定されます。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/v2-app-types"} -->
## Microsoft ID プラットフォームのアプリケーションの種類 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-app-types
- Service: identity-platform
- Article date: 2025-01-04
- Summary: Microsoft ID プラットフォームでサポートされているアプリの種類とシナリオです。

Microsoft ID プラットフォームでは、さまざまな最新アプリ アーキテクチャ向けの認証がサポートされています。そのいずれも、業界標準のプロトコルである [OAuth 2.0 または OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols) に基づいています。 この記事では、使用する言語やプラットフォームを問わず、Microsoft ID プラットフォームを使用して作成できるアプリの種類について説明します。 情報は、[アプリケーションのシナリオ](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios#application-types)でコードを詳しく確認する前に大まかなシナリオを理解するうえで役立ちます。

### 基本

Microsoft ID プラットフォームを使用する各アプリを、Microsoft Entra 管理センターの [\[アプリの登録\]](https://entra.microsoft.com/#view/Microsoft_AAD_RegisteredApps/ApplicationsListBlade/quickStartType%7E/null/sourceType/Microsoft_AAD_IAM) で登録する必要があります。 アプリの登録プロセスでは、次の値が収集され、対象のアプリに割り当てられます。

- アプリを一意に識別する **アプリケーション (クライアント) ID**
- 応答をアプリにリダイレクトして戻すために使用できる**リダイレクト URI**。
- 他にいくつかのシナリオ固有の値 (サポートされているアカウントの種類など)

詳細については、[アプリの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)方法を参照してください。

登録の済んだアプリは、エンドポイントに要求を送ることによって、Microsoft ID プラットフォームと通信します。 これらの要求の詳細に対処するオープン ソース フレームワークとライブラリをご用意しています。 これらのエンドポイントへの要求を作成して、自分で認証ロジックを実装してもかまいません。

```HTTP
https://login.microsoftonline.com/common/oauth2/v2.0/authorize
https://login.microsoftonline.com/common/oauth2/v2.0/token
```

Microsoft ID プラットフォームでサポートされているアプリの種類:

- シングルページ アプリ (SPA)
- Web アプリ
- Web API
- モバイル アプリとネイティブ アプリ
- サービス、デーモン、スクリプト

### シングルページ アプリ

最新アプリの多くには、主に JavaScript で記述されたシングル ページ アプリ (SPA) のフロントエンドがあり、Angular、React、Vue などのフレームワークと共に使用されることもあります。 Microsoft ID プラットフォームでは、認証に [OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc) プロトコルを使用し、OAuth 2.0 で定義されている 2 種類の承認付与のいずれかを使用するという方法でこれらのアプリがサポートされます。 SPA を開発するときに、[認証コード フローを PKCE (Proof Key for Code Exchange) と一緒に](https://devblogs.microsoft.com/identity/migrate-to-auth-code-flow/)使用します。 このフローは、推奨されなくなった暗黙的フローよりも安全です。 詳細については、[認証コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow#prefer-the-auth-code-flow)に関するセクションを参照してください。

このフロー図は、OAuth 2.0 認証コードの付与フローを示しています (PKCE に関する詳細は省略されています)。ここでは、アプリが Microsoft ID プラットフォームの `authorize` エンドポイントからコードを受信し、クロスサイト Web 要求を使用して、それをアクセス トークンと更新トークンと引き換えています。 SPA の場合、アクセス トークンは 1 時間有効であり、有効期限が切れると、更新トークンを使用して別のコードを要求する必要があります。 通常、アクセス トークンに加えて、クライアント アプリケーションにサインインしたユーザーを表す `id_token` も、同じフローまたは別の OpenID Connect 要求 (ここには示されていません) を介して要求されます。

[Image: シングル ページ アプリとセキュリティ トークン サービス エンドポイントの間の OAuth 2.0 認証コード フローを示す図。]

この実際の動作については、「[クイックスタート: シングルページ アプリ (SPA) でユーザーをサインインし、JavaScript を使用して Microsoft Graph API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-sign-in)」を参照してください。

### Web アプリ

ブラウザーからアクセスされる Web アプリ (.NET、PHP、Java、Ruby、Python、Node) の場合、ユーザーのサインインに [OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols) を使うことができます。 OpenID Connect では、Web アプリは ID トークンを受け取ります。 ID トークンは、ユーザーの ID を検証し、要求の形でユーザーに関する情報を提供するセキュリティ トークンです。

```JSON
// Partial raw ID token
abC1dEf2Ghi3jkL4mNo5Pqr6stU7vWx8Yza9...

// Partial content of a decoded ID token
{
    "name": "Casey Jensen",
    "email": "casey.jensen@onmicrosoft.com",
    "oid": "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb"
    ...
}
```

Microsoft ID プラットフォームで使われている各種のトークンの詳細については、[アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)のリファレンスと [id_token](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens) のリファレンスを参照してください。

Web サーバー アプリにおけるサインイン認証フローは、主に次のステップで構成されます。

[Image: Web アプリの認証フロー]

Microsoft ID プラットフォームから受け取った公開署名キーを使用して ID トークンを検証することにより、ユーザーの ID を保証することができます。 以降のページ要求でユーザーを識別するために使用できるセッション Cookie が設定されます。

構築の詳細については、[ASP.NET Core Web アプリからユーザーのサインインと Microsoft Graph API の呼び出し](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-dotnet-core-sign-in)に関する記事を参照してください

Web サーバー アプリは、単純なサインインを実行するだけでなく、[Representational State Transfer (REST) API](https://learn.microsoft.com/ja-jp/rest/api/azure/) をはじめとする他の Web サービスにアクセスすることが必要な場合があります。 この場合、Web サーバー アプリは、OpenID Connect と OAuth 2.0 を組み合わせたフローに関与します。その際使用されるのが [OAuth 2.0 認証コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)です。 このシナリオの詳細については、コード [サンプル](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/2-WebApp-graph-user/2-1-Call-MSGraph/README.md)を参照してください。

### Web API

Microsoft ID プラットフォームを使用すると、アプリの RESTful Web API などの Web サービスをセキュリティで保護できます。 Web API は、さまざまなプラットフォームや言語で実装できます。 また、Azure Functions で HTTP トリガーを使用して実装することもできます。 Web API では、ID トークンとセッション Cookie の代わりに OAuth 2.0 アクセス トークンを使って、そのデータをセキュリティで保護し、受信要求を認証します。

Web API の呼び出し元によって、次のように HTTP 要求の Authorization ヘッダーにアクセス トークンが追加されます。

```HTTP
GET /api/items HTTP/1.1
Host: www.mywebapi.com
Authorization: Bearer abC1dEf2Ghi3jkL4mNo5Pqr6stU7vWx8Yza9...
Accept: application/json
...
```

Web API では、そのアクセス トークンを使って API の呼び出し元の ID を検証し、アクセス トークンでエンコードされている要求から呼び出し元に関する情報を抽出します。 Microsoft ID プラットフォームで使われている各種のトークンの詳細については、[アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)のリファレンスと [ID トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)のリファレンスを参照してください。

Web API を使用すると、アクセス許可 ([スコープ](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)とも呼ばれる) を公開することで、ユーザーが特定の機能またはデータをオプトイン/オプトアウトできるようになります。 呼び出し元のアプリがスコープに対するアクセス許可を取得するには、ユーザーがフローの途中でスコープに同意する必要があります。 Microsoft ID プラットフォームでは、ユーザーにアクセス許可を求め、Web API が受信するすべてのアクセス トークンにアクセス許可を記録します。 Web API では、呼び出しごとに受信するアクセス トークンを検証し、承認チェックを実行します。

Web API では、すべての種類のアプリ (Web サーバー アプリ、デスクトップ アプリとモバイル アプリ、シングル ページ アプリ、サーバー側のデーモン、さらにそれ以外の Web API など) からアクセス トークンを受信できます。 Web API の大まかなフローは次のとおりです。

[Image: Web API の認証フロー]

OAuth2 アクセス トークンを使用して Web API をセキュリティ保護する方法については、[保護された Web API のチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-api-dotnet-register-app)に関するページの Web API コード サンプルを確認してください。

多くの場合、Web API は Microsoft ID プラットフォームで保護されているその他のダウンストリーム Web API に、送信要求を行う必要もあります。 そのために、Web API では**代理 (OBO)** フローを利用できます。それにより、Web API は受信アクセス トークンを、送信要求で使用される別のアクセス トークンに交換できます。 詳細については、「[Microsoft ID プラットフォームと OAuth 2.0 On-Behalf-Of フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)」をご覧ください。

### モバイル アプリとネイティブ アプリ

多くの場合、モバイル アプリやデスクトップ アプリなど、デバイスにインストールされているアプリは、データを格納し、ユーザーの代わりにさまざまな機能を実行するバックエンド サービスや Web API にアクセスする必要があります。 これらのアプリは、[OAuth 2.0 承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)を使ってバックエンド サービスにサインインと承認を追加します。

このフローでは、ユーザーがサインインすると、アプリに Microsoft ID プラットフォームから承認コードが渡されます。 承認コードは、サインインしているユーザーに代わってバックエンド サービスを呼び出すためのアプリのアクセス許可を表します。 アプリはバックグラウンドで承認コードを OAuth 2.0 のアクセス トークンおよび更新トークンと交換します。 アプリではそのアクセス トークンを使って HTTP 要求で Web API を認証できます。また、古いアクセス トークンの有効期限が切れた場合は、更新トークンを使って新しいアクセス トークンを取得できます。

[Image: ネイティブ アプリの認証フロー]

注

アプリケーションで既定のシステム Web ビューが使用されている場合は、「`AADSTS50199`」の「サインインの確認」機能とエラーコード  の情報を確認してください。

### サーバー、デーモン、スクリプト

長時間実行されるプロセスを含むアプリや、ユーザーとのやりとりはなく動作するアプリにも、セキュリティで保護されたリソース (Web API など) にアクセスする方法が必要です。 これらのアプリは、OAuth 2.0 クライアント資格情報フローで (ユーザーの委任 ID ではなく) アプリの ID を使用して認証を行い、トークンを取得することができます。 アプリの ID は、クライアント シークレットまたは証明書を使用して証明することができます。 詳細については、[Microsoft ID プラットフォームを使用する .NET デーモン コンソール アプリケーション](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2)に関するページを参照してください。

このフローでは、アプリは `/token` エンドポイントと直接対話してアクセスを取得します。

[Image: デーモン アプリの認証フロー]

デーモン アプリを作成するには、[クライアントの資格情報に関する記述](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)を参照するか、[.NET サンプル アプリ](https://github.com/Azure-Samples/active-directory-dotnet-daemon-v2)をお試しください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/v2-conditional-access-dev-guide"} -->
## Microsoft Entra 条件付きアクセスの開発者向けガイダンス - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-conditional-access-dev-guide
- Service: identity-platform / workforce
- Article date: 2020-05-18
- Summary: Microsoft Entra 条件付きアクセスと Microsoft ID プラットフォームの開発者ガイドとシナリオ。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra ID の条件付きアクセス機能は、アプリをセキュリティで保護し、サービスを保護するために使用できるいくつかの方法の 1 つを提供します。 条件付きアクセスを使用すると、開発者や企業のお客様は、次のようなさまざまな方法でサービスを保護できます。

- [多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)
- Intune に登録されているデバイスのみが特定のサービスにアクセスできるようにする
- ユーザーの場所と IP 範囲の制限

条件付きアクセスのすべての機能の詳細については、記事「[条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)」を参照してください。

Microsoft Entra のアプリをビルドしている開発者のために、この記事では、条件付きアクセスを使用する方法について示し、条件付きアクセス ポリシーが適用される可能性のある制御不能なリソースにアクセスした場合の影響についても学習します。 この記事では、On-Behalf-Of フロー、Web アプリ、Microsoft Graph へのアクセス、API の呼び出しに対し条件付きアクセスが与える影響についても説明します。

[シングル](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)テナント アプリと[マルチテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant) アプリと[一般的な認証パターンに関する](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-vs-authorization)知識が前提です。

注

この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 要件に対する適切なライセンスを確認するには、「[Free、Basic、および Premium エディションの一般公開されている機能の比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)」をご覧ください。 [Microsoft 365 Business ライセンス](https://learn.microsoft.com/ja-jp/office365/servicedescriptions/office-365-service-descriptions-technet-library)をお持ちのお客様も、条件付きアクセス機能にアクセスできます。

### 条件付きアクセスはアプリにどのように影響しますか?

#### アプリの種類が影響を受ける

最も一般的なケースでは、条件付きアクセスはアプリの動作を変更したり、開発者からの変更を必要としたりしません。 アプリが間接的、またはサイレントでサービスに対するトークンを要求する特定の場合のみ、アプリで条件付きアクセス チャレンジを処理するためにコードを変更する必要があります。 対話型のサインイン要求を実行するのと同じくらい簡単な場合があります。

具体的には、次のシナリオでは、条件付きアクセスの課題を処理するコードが必要です。

- On-Behalf-Of フローを実行するアプリ
- 複数のサービス/リソースにアクセスするアプリ
- MSAL.js を使用したシングルページ アプリ
- リソースを呼び出す Web Apps

条件付きアクセス ポリシーは、アプリに適用できますが、アプリがアクセスする Web API にも適用できます。 条件付きアクセス ポリシーを構成する方法の詳細については、「[クイック スタート: Microsoft Entra の条件付きアクセスを使用して特定のアプリケーションに対して MFA を必要にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)」を参照してください。

シナリオに応じて、企業のお客様はいつでも条件付きアクセス ポリシーを適用および削除できます。 新しいポリシーが適用されたときにアプリが機能し続けるには、チャレンジ処理を実装します。 チャレンジ処理の例は次のとおりです。

#### 条件付きアクセスの例

条件付きアクセスを処理するためにコードの変更が必要なシナリオもあれば、そのまま動作するシナリオもあります。 条件付きアクセスを使用して多要素認証を実行するシナリオをいくつか次に示します。その結果、その違いについていくつかの分析情報が得られます。

- シングル テナントの iOS アプリをビルドして、条件付きアクセス ポリシーを適用するとします。 アプリはユーザーをサインインさせ、API へのアクセスを要求しません。 ユーザーがサインインすると、ポリシーが自動的に呼び出され、ユーザーは多要素認証 (MFA) を実行する必要があります。
- 中間層サービスを使用してダウンストリーム API にアクセスするネイティブ アプリを構築しています。 このアプリを使用している企業のお客様は、ダウンストリーム API にポリシーを適用します。 エンドユーザーがサインインすると、ネイティブ アプリケーションは中間層へのアクセスを要求し、トークンを送信します。 中間層は、ダウンストリーム API へのアクセスを要求するために、代理フローを実行します。 この時点で、クレーム "チャレンジ" が中間層に提示されます。 中間層では、条件付きアクセス ポリシーに準拠する必要があるネイティブのアプリにチャレンジを送信します。

##### Microsoft Graph

Microsoft Graph では、条件付きアクセスの環境でアプリを構築する場合に、特別な考慮事項があります。 通常、条件付きアクセスのメカニズムは同様に動作しますが、ユーザーに表示されるポリシーは、アプリがグラフから要求している基本データに基づくものとなります。

具体的に言うと、Microsoft Graph のスコープはすべて、ポリシーを個別に適用できる、いくつかのデータセットを表します。 条件付きアクセス ポリシーには特定のデータセットが割り当てられるため、Microsoft Entra ID は Graph 自体ではなく、Graph の背後にあるデータに基づいて条件付きアクセス ポリシーを適用します。

たとえば、アプリが次の Microsoft Graph スコープを要求したとします。

```
scopes="ChannelMessages.Read.All Mail.Read"
```

この場合アプリでは、Teams と Exchange に対して設定されたすべてのポリシーをユーザーが満たすことを想定できます。 一部のスコープは、複数のデータセットにマップされる場合があります (アクセスを許可する場合)。

#### 条件付きアクセス ポリシーへの準拠

いくつかの異なるアプリ トポロジでは、セッションが確立されたときに、条件付きアクセス ポリシーが評価されます。 条件付きアクセス ポリシーは、アプリやサービスの粒度に従って動作するため、実行しようとしているシナリオによって呼び出されるポイントが大きく異なります。

アプリが条件付きアクセス ポリシーを使用してサービスにアクセスしようとすると、条件付きアクセスのチャレンジが発生する可能性があります。 このチャレンジは、Microsoft Entra ID からの応答に含まれる `claims` パラメーターにエンコードされています。 このチャレンジ パラメーターの例を次に示します。

```
claims={"access_token":{"polids":{"essential":true,"Values":["<GUID>"]}}}
```

開発者は、このチャレンジを取得して、Microsoft Entra ID への新しい要求に追加できます。 この状態を渡すと、エンド ユーザーに、条件付きアクセス ポリシーに準拠するために必要な操作を実行することを求めるメッセージが表示されます。 次のシナリオでは、エラーおよびパラメーターを抽出する方法について説明します。

### シナリオ

#### [前提条件]

Microsoft Entra 条件付きアクセスは、[Microsoft Entra ID P1 または P2](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing) に含まれている機能です。 [Microsoft 365 Business ライセンス](https://learn.microsoft.com/ja-jp/office365/servicedescriptions/office-365-service-descriptions-technet-library)をお持ちのお客様も、条件付きアクセス機能にアクセスできます。

#### 特定のシナリオの考慮事項

次の情報は、これらの条件付きアクセス シナリオでのみ適用されます。

- On-Behalf-Of フローを実行するアプリ
- 複数のサービス/リソースにアクセスするアプリ
- MSAL.js を使用したシングルページ アプリ

以下のセクションでは、より複雑な一般的なシナリオについて説明します。 主要な運用原則では、条件付きアクセス ポリシーは、条件付きアクセス ポリシーが適用されるサービスに対してトークンが要求されたときに評価されます。

### シナリオ:On-Behalf-Of フローを実行するアプリ

このシナリオでは、ネイティブ アプリが Web サービス/API を呼び出す場合について説明します。 呼び出されたサービスは、On-Behalf-Of フローでダウンストリーム サービスを呼び出します。 ここでは、ダウンストリーム サービス (Web API 2) に、条件付きアクセス ポリシーを適用し、サーバー/デーモン アプリではなく、ネイティブ アプリを使用しています。

[Image: On-Behalf-Of フローを実行するアプリのフロー ダイアグラム]

Web API 1 の初期トークン要求では、Web API 1 が常にダウンストリーム API にヒットするとは限らないため、エンド ユーザーに多要素認証を求めるメッセージは表示されません。 Web API 1 が Web API 2 のユーザーに代わってトークンを要求しようとすると、ユーザーが多要素認証でサインインしていないため、要求は失敗します。

Microsoft Entra ID は、いくつかの興味深いデータを HTTP 応答で返します。

注

この場合は多要素認証エラーの説明ですが、条件付きアクセスに関連するさまざまな `interaction_required` があります。

```
HTTP 400; Bad Request
error=interaction_required
error_description=AADSTS50076: Due to a configuration change made by your administrator, or because you moved to a new location, you must use multifactor authentication to access '<Web API 2 App/Client ID>'.
claims={"access_token":{"polids":{"essential":true,"Values":["<GUID>"]}}}
```

Web API 1 では、エラー `error=interaction_required` をキャッチし、`claims` チャレンジをデスクトップ アプリケーションに返送します。 この時点では、デスクトップ アプリケーションは新しい `acquireToken()` 呼び出しを行い、追加のクエリ文字列パラメーターとして `claims` チャレンジを追加できます。 この新しい要求では、ユーザーが多要素認証を行い、この新しいトークンを Web API 1 に送り返し、代理フローを完了する必要があります。

このシナリオを試すには、[.NET コード サンプル](https://github.com/Azure-Samples/active-directory-dotnet-native-aspnetcore-v2/tree/master/2.%20Web%20API%20now%20calls%20Microsoft%20Graph#handling-required-interactions-with-the-user-dynamic-consent-mfa-etc-)を参照してください。 これは、Web API 1 から返された要求チャレンジをネイティブ アプリケーションに渡し、クライアント アプリケーション内で新しい要求を作成する方法を示しています。

### シナリオ:複数のサービスにアクセスするアプリ

このシナリオでは、Web アプリが、いずれかに条件付きアクセス ポリシーが割り当てられている 2 つのサービスにアクセスする場合について説明します。 アプリケーション ロジックによっては、Web アプリが両方の Web サービスにアクセスする必要のないパスが存在する場合があります。 このシナリオでは、トークンを要求する順序が、エンド ユーザー エクスペリエンスに重要な役割を果たします。

A と B の Web サービスがあり、Web サービス B に条件付きアクセス ポリシーが適用されているとします。 最初の対話型の認証要求では、両方のサービスの同意が必要ですが、条件付きアクセス ポリシーがすべての場合に必要なわけではありません。 アプリが Web サービス B のトークンを要求すると、ポリシーが呼び出され、Web サービス A に対する後続の要求も成功します。

[Image: 複数のサービスにアクセスするアプリのフロー ダイアグラム]

また、最初にアプリで Web サービス A に対するトークンを要求している場合、エンド ユーザーは条件付きアクセス ポリシーを呼び出しません。 これにより、アプリ開発者は、エンドユーザー エクスペリエンスを制御しながらも、条件付きアクセス ポリシーを常に強制的に呼び出す必要がなくなります。 難しいケースは、アプリが後で Web サービス B のトークンを要求する場合です。この時点で、エンド ユーザーは条件付きアクセス ポリシーに準拠する必要があります。 アプリが `acquireToken` しようとすると、次のエラー (次の図参照) が発生する可能性があります。

```
HTTP 400; Bad Request
error=interaction_required
error_description=AADSTS50076: Due to a configuration change made by your administrator, or because you moved to a new location, you must use multifactor authentication to access '<Web API App/Client ID>'.
claims={"access_token":{"polids":{"essential":true,"Values":["<GUID>"]}}}
```

[Image: 新しいトークンを要求する複数のサービスにアクセスするアプリ]

アプリが MSAL ライブラリを使用しており、トークンの取得に失敗した場合、常に対話形式で再試行されます。 この対話型の要求が発生すると、エンド ユーザーには、条件付きアクセスに準拠する機会が与えられます。 これは、要求が `AcquireTokenSilentAsync` または `PromptBehavior.Never` でない限り該当し、この場合、アプリは対話型の `AcquireToken` 要求を実行し、エンドユーザーはポリシーに準拠する機会が与えられます。

### シナリオ:MSAL.js を使用するシングルページ アプリ (SPA)

このシナリオでは、シングルページ アプリ (SPA) が存在し、条件付きアクセスで保護されている Web API を、このアプリから MSAL.js を使用して呼び出す場合について説明します。 これは、シンプルなアーキテクチャですが、条件付きアクセスの周辺を開発するときに考慮する必要がある点がいくつかあります。

MSAL.js では、`acquireTokenSilent()`、`acquireTokenPopup()`、および `acquireTokenRedirect()` トークンを取得する関数があります。

- アクセス トークンのサイレント取得に `acquireTokenSilent()` が使用されます。この場合、どのような状況でも UI は表示されません。
- `acquireTokenPopup()` と `acquireTokenRedirect()` の両方を対話形式でリソースのトークンを要求するために使用され、この場合、常にサインイン UI が表示されます。

Web API を呼び出すためにアクセス トークンが必要な場合は、アプリは `acquireTokenSilent()` を試行します。 トークンの期限が切れているか、条件付きアクセス ポリシーに準拠する必要がある場合は、*acquireToken* 関数が失敗して、アプリでは `acquireTokenPopup()` または `acquireTokenRedirect()` が使用されます。

[Image: MSAL を使用するシングルページ アプリケーションのフロー ダイアグラム]

条件付きアクセスのシナリオでの例を見てみましょう。 エンドユーザーがサイトに到着し、セッションは開始されていません。 `loginPopup()`呼び出しを実行し、多要素認証なしで ID トークンを取得します。 ユーザーがボタンを押し、これにより、アプリは Web API からデータを要求する必要があります。 アプリは `acquireTokenSilent()` 呼び出しを試みますが、ユーザーがまだ多要素認証を実行しておらず、条件付きアクセス ポリシーに準拠する必要があるため、失敗します。

Microsoft Entra ID は、次の HTTP 応答を返します。

```
HTTP 400; Bad Request
error=interaction_required
error_description=AADSTS50076: Due to a configuration change made by your administrator, or because you moved to a new location, you must use multifactor authentication to access '<Web API App/Client ID>'.
```

アプリは `error=interaction_required` をキャッチする必要があります。 アプリは、同じソースの `acquireTokenPopup()` または `acquireTokenRedirect()` を使用できます。 ユーザーは多要素認証を強制されます。 ユーザーが多要素認証を完了すると、要求されたリソースの新しいアクセス トークンがアプリに発行されます。

このシナリオを試すには、[On-Behalf-Of フローを使用して Node.js Web API を呼び出す React SPA](https://github.com/Azure-Samples/ms-identity-javascript-react-tutorial/tree/main/6-AdvancedScenarios/1-call-api-obo) のコード サンプルを参照してください。 このコード サンプルでは、条件付きアクセス ポリシーと、このシナリオを説明するために、上記で React SPA に登録された Web API が使用されます。 クレーム チャレンジを正しく処理し、Web API で使用できるアクセス トークンを取得する方法を示します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/v2-oauth-ropc"} -->
## Microsoft ID プラットフォームと OAuth 2.0 リソース所有者のパスワード資格情報 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc
- Service: identity-platform
- Article date: 2025-01-04
- Summary: リソース所有者パスワード資格情報 (ROPC) の付与を使用して、ブラウザーレスの認証フローをサポートします。

Microsoft ID プラットフォームでは、[OAuth 2.0 リソース所有者パスワード資格情報 (ROPC) の付与](https://tools.ietf.org/html/rfc6749#section-4.3)がサポートされています。これにより、アプリケーションは自分のパスワードを直接処理してユーザーにサインインできます。 この記事では、アプリケーション内のプロトコルに対して直接プログラムする方法について説明します。 可能な場合は、代わりにサポートされている Microsoft 認証ライブラリ (MSAL) を使用してトークンを取得し、セキュリティで保護された Web API [呼び出](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios#scenarios-and-supported-authentication-flows)することをお勧めします。 また、MSAL [を使用する](https://learn.microsoft.com/ja-jp/entra/identity-platform/sample-v2-code)サンプル アプリも参照してください。

警告

Microsoft は、ROPC フローを使用 "しない" ことをお勧めします。多要素認証 (MFA) と互換性がありません。 ほとんどのシナリオでは、より安全な代替手段が利用でき、推奨されます。 このフローでは、アプリケーションに非常に高い信頼が必要であり、他のフローに存在しないリスクが伴います。 このフローは、より安全なフローが実行できない場合にのみ使用してください。

重要

- Microsoft ID プラットフォームでは、個人アカウントではなく、Microsoft Entra テナント内の ROPC 許可のみがサポートされます。 つまり、テナント固有のエンドポイント (`https://login.microsoftonline.com/{TenantId_or_Name}`) または `organizations` エンドポイントを使用する必要があります。
- Microsoft Entra テナントに招待された個人アカウントでは、ROPC フローを使用できません。
- パスワードを持たないアカウントは ROPC でサインインできません。つまり、SMS サインイン、FIDO、Authenticator アプリなどの機能は、そのフローでは機能しません。 アプリまたはユーザーがこれらの機能を必要とする場合は、ROPC 以外の許可の種類を使用します。
- ユーザーが [多要素認証 (MFA)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) を使用してアプリケーションにログインする必要がある場合は、代わりにブロックされます。
- ROPC は、ハイブリッド ID フェデレーション [シナリオ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed) ではサポートされていません (たとえば、オンプレミス アカウントの認証に使用される Microsoft Entra ID と AD FS)。 ユーザーがフル ページでオンプレミスの ID プロバイダーにリダイレクトされた場合、Microsoft Entra ID は、その ID プロバイダーに対してユーザー名とパスワードをテストできません。 ただし、[パススルー認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta) は ROPC でサポートされています。
- ハイブリッド ID フェデレーション シナリオの例外は、次のようになります。AllowCloudPasswordValidation **が TRUE に設定** ホーム領域検出ポリシーを使用すると、オンプレミスのパスワードがクラウドに同期されるときに、フェデレーション ユーザーに対して ROPC フローが機能するようになります。 詳細については、「[レガシ アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/home-realm-discovery-policy#enable-direct-ropc-authentication-of-federated-users-for-legacy-applications)のフェデレーション ユーザーの直接 ROPC 認証を有効にする」を参照してください。
- 先頭または末尾の空白を含むパスワードは、ROPC フローではサポートされていません。

### ROPC から移行する方法

MFA の普及に合わせて、一部の Microsoft Web API は、MFA 要件に合格した場合にのみアクセス トークンを受け入れます。 ROPC に依存するアプリケーションとテスト リグはロックアウトされます。Microsoft Entra はトークンを発行しないか、リソースが要求を拒否します。

ROPC を使用して保護されたダウンストリーム API を呼び出すトークンを取得する場合は、セキュリティで保護されたトークン取得戦略に移行します。

#### ユーザー コンテキストが使用可能な場合

エンド ユーザーがリソースにアクセスする必要がある場合、そのユーザーに代わって動作するクライアント アプリケーションは、対話型認証の形式を使用する必要があります。 エンド ユーザーは、ブラウザーでプロンプトが表示された場合にのみ MFA にチャレンジできます。

- Web アプリケーションの場合:
    - フロントエンドで認証が行われる場合は、「シングル ページ アプリケーションの 」を参照してください。
    - バックエンドで認証が行われる場合は、web アプリケーションの を参照してください。
- Web API はブラウザーを表示できません。 代わりに、クライアント アプリケーションにチャレンジを返す必要があります。 詳細については、[Web API](https://learn.microsoft.com/ja-jp/entra/identity-platform/sample-v2-code?tabs=apptype#web-api) と、[Web API でユーザーにチャレンジする方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow#error-response-example)に関する記事を参照してください。
- デスクトップ アプリケーションでは、ブローカー ベースの認証を使用する必要があります。 ブローカーはブラウザーベースの認証を使用するため、MFA を適用し、可能な限り最も安全な体制を実現できます。
- また、ブローカー (Authenticator、ポータル サイト) ベースの認証を使用するようにモバイル アプリケーションを構成する必要があります。

#### ユーザー コンテキストが使用できない場合

ユーザー コンテキストが関係しないシナリオの例を次に示しますが、これらに限定されません。

- CI パイプラインの一部として実行されているスクリプト。
- ユーザーの詳細なしで、それ自体に代わってリソースを呼び出す必要があるサービス。

アプリケーション開発者は、[サービス プリンシパル認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)を使用する必要があります。これは、[デーモンのサンプル](https://learn.microsoft.com/ja-jp/entra/identity-platform/sample-v2-code?tabs=apptype#service--daemon)に示されています。 MFA はサービス プリンシパルには適用されません。

サービス プリンシパルとして認証するには、複数の方法があります。

- アプリが Azure インフラストラクチャで実行されている場合は、マネージド ID 使用します。 マネージド ID を使用すると、シークレットと証明書を維持およびローテーションするオーバーヘッドが排除されます。
- GitHub などの別の OAuth2 準拠 ID プロバイダーによって管理されているシステムでアプリが実行されている場合は、[フェデレーション ID 資格情報](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-create-trust?pivots=identity-wif-apps-methods-azp)を使用します。
- マネージド ID またはフェデレーション ID を使用できない場合は、[証明書資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials)を使用します。

警告

ユーザー コンテキストが使用可能な場合は、サービス プリンシパル認証を使用しないでください。 アプリ専用アクセスは本質的に高い特権であり、多くの場合、テナント全体のアクセス権が付与され、悪いアクターが任意のユーザーの顧客データにアクセスできる可能性があります。

### プロトコル図

次の図は、ROPC フローを示しています。

[Image: リソース所有者のパスワード資格情報フローの] を示す図

### 承認要求

ROPC フローは 1 つの要求です。クライアント ID とユーザーの資格情報を ID プロバイダーに送信し、その代わりにトークンを受け取ります。 クライアントは、その前にユーザーの電子メール アドレス (UPN) とパスワードを要求する必要があります。 要求が成功した直後に、クライアントはユーザーの資格情報をメモリから安全に破棄する必要があります。 保存は決してしないでください。

```HTTP
// Line breaks and spaces are for legibility only.  This is a public client, so no secret is required.

POST {tenant}/oauth2/v2.0/token
Host: login.microsoftonline.com
Content-Type: application/x-www-form-urlencoded

client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&scope=user.read%20openid%20profile%20offline_access
&username=MyUsername@myTenant.com
&password=SuperS3cret
&grant_type=password
```

| パラメーター | 条件 | 説明 |
| --- | --- | --- |
| `tenant` | 必須 | ユーザーをログインさせるディレクトリ テナント。 テナントは GUID またはフレンドリ名の形式で指定できます。 ただし、そのパラメーターを `common` または `consumers`に設定することはできませんが、`organizations`に設定できます。 |
| `client_id` | 必須 | [Microsoft Entra 管理センターのアプリ登録](https://go.microsoft.com/fwlink/?linkid=2083908) ページで、あなたのアプリに割り当てられたアプリケーション (クライアント) ID。 |
| `grant_type` | 必須 | `password`に設定する必要があります。 |
| `username` | 必須 | ユーザーのメール アドレス。 |
| `password` | 必須 | ユーザーのパスワード。 |
| `scope` | 推奨 | アプリが必要とする[スコープ](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)、つまりアクセス許可をスペースで区切った一覧。 対話型フローでは、管理者またはユーザーは事前にこれらのスコープに同意する必要があります。 |
| `client_secret` | 必要な場合がある | アプリがパブリック クライアントの場合、`client_secret` または `client_assertion` を含めることはできません。 アプリが機密クライアントの場合は、アプリを含む必要があります。 |
| `client_assertion` | 必要な場合がある | 証明書を使用して生成された別の形式の `client_secret`。 詳細については、「証明書資格情報 」を参照してください。 |

#### 成功した認証応答

次の例は、成功したトークン応答を示しています。

```json
{
    "token_type": "Bearer",
    "scope": "User.Read profile openid email",
    "expires_in": 3599,
    "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik5HVEZ2ZEstZnl0aEV1Q...",
    "refresh_token": "AwABAAAAvPM1KaPlrEqdFSBzjqfTGAMxZGUTdM0t4B4...",
    "id_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.eyJhdWQiOiIyZDRkMTFhMi1mODE0LTQ2YTctOD..."
}
```

| パラメーター | 形式 | 説明 |
| --- | --- | --- |
| `token_type` | 糸 | 常に `Bearer`に設定します。 |
| `scope` | スペース区切り文字列 | アクセス トークンが返された場合、このパラメーターはアクセス トークンが有効なスコープを一覧表示します。 |
| `expires_in` | INT | 含まれているアクセス トークンが有効な秒数。 |
| `access_token` | 不透明な文字列 | 要求された[スコープ](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)に対して発行されます。 |
| `id_token` | JWT | 元の `scope` パラメーターに `openid` スコープが含まれている場合に発行されます。 |
| `refresh_token` | 不透明な文字列 | `scope` に元のパラメーター `offline_access`が含まれている場合、発行されます。 |

更新トークンを使用すると、[OAuth コード フローのドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#refresh-the-access-token)で説明されているのと同じフローを使用して、新しいアクセス トークンと更新トークンを取得できます。

警告

この例のトークンをコードに含め、所有していない API のトークンの検証や読み取りを試みないでください。 Microsoft サービスのトークンでは、JWT として検証されない特殊な形式を使用できます。また、コンシューマー (Microsoft アカウント) ユーザー向けに暗号化することもできます。 トークンの読み取りは便利なデバッグおよび学習ツールですが、コード内でこれに依存したり、制御する API 用ではないトークンに関する詳細を想定したりしないでください。

#### エラー応答

ユーザーが正しいユーザー名またはパスワードを指定していない場合、またはクライアントが要求された同意を受け取っていない場合、認証は失敗します。

| エラー | 説明 | クライアント アクション |
| --- | --- | --- |
| `invalid_grant` | 認証に失敗しました | 資格情報が正しくないか、クライアントが要求されたスコープに対する同意を持っていません。 スコープが付与されていない場合は、`consent_required` エラーが返されます。 このエラーを解決するには、クライアントは Web ビューまたはブラウザーを使用してユーザーを対話型プロンプトに送信する必要があります。 |
| `invalid_request` | 要求が不適切に構築されました | 許可の種類は、`/common` または `/consumers` 認証コンテキストではサポートされていません。 代わりに、`/organizations` またはテナント ID を使用してください。 |

### 詳細情報

ROPC フローの実装例については、GitHub の [.NET コンソール アプリケーション](https://github.com/azure-samples/active-directory-dotnetcore-console-up-v2) コード サンプルを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/v2-oauth2-auth-code-flow"} -->
## Microsoft ID プラットフォームと OAuth 2.0 認証コード フロー - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow
- Service: identity-platform
- Article date: 2026-01-09
- Summary: OAuth 2.0 認可コード付与の Microsoft ID プラットフォームの実装に関するプロトコル リファレンス

OAuth 2.0 認可コード付与タイプ ("*認可コード フロー*") を使用すると、クライアント アプリケーションは Web API などの保護されたリソースへの認可されたアクセスを取得できます。 認可コード フローには、認可サーバー (Microsoft ID プラットフォーム) からアプリケーションへのリダイレクトをサポートするユーザー エージェントが必要です。 たとえば、ユーザーがアプリにサインインしてデータにアクセスするために操作する Web ブラウザー、デスクトップ、モバイル アプリケーションなどです。

この記事では、フローを実行するために生の HTTP 要求を手動で作成して発行する場合にのみ必要な低レベルのプロトコルの詳細について説明します。これはお勧め**しません**。 代わりに、[Microsoft が構築し、サポートされている認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)を使用してセキュリティ トークンを取得し、アプリで保護された Web API を呼び出します。

### 認可コード フローをサポートするアプリケーション

Proof Key for Code Exchange (PKCE) および OpenID Connect (OIDC) とペアになっている認可コード フローを使用して、次の種類のアプリのアクセス トークンと ID トークンを取得します。

- [単一ページの Web アプリケーション (SPA)](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-app-types#single-page-apps)
- [標準 (サーバー ベース) Web アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-app-types#web-apps)
- [デスクトップ アプリとモバイル アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-app-types#mobile-and-native-apps)

### プロトコルの詳細

OAuth 2.0 承認コード フローは、 [OAuth 2.0 仕様のセクション 4.1](https://tools.ietf.org/html/rfc6749)で規定されています。 OAuth 2.0 認可コード フローを使用するアプリは、Microsoft ID プラットフォームによって保護されたリソース (通常は API) への要求に含める `access_token` を取得します。 アプリでは、更新メカニズムを使用して、以前に認証されたエンティティの新しい ID とアクセス トークンを要求することもできます。

次の図は、認証フローの概要を示しています。

[Image: OAuth 認可コード フローを示す図。ネイティブ アプリと Web API は、この記事で説明するトークンを使用して対話します。]

### 単一ページ アプリ (SPA) のリダイレクト URI

認可コード フローを使用する SPA のリダイレクト URI には、特別な構成が必要です。

- PKCE とクロスオリジン リソース共有 (CORS) を使用して認証コード フローをサポートする**リダイレクト URI を追加**します。[アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)に関するページの手順に従います。
- **リダイレクト URI を更新する**: Microsoft Entra 管理センターの`type`を使用し、リダイレクト URI の `spa` を  に設定します。

`spa` のリダイレクトの種類は、暗黙的なフローと下位互換性があります。 トークンを取得するために暗黙的なフローを現在使用しているアプリは、問題なく `spa` のリダイレクト URI の種類に移行し、暗黙的なフローを引き続き使用することができます。 この下位互換性にもかかわらず、SPA には、PKCE を使用した認証コード フローを使用することをお勧めします。

リダイレクト URI に CORS を設定せずに認可コード フローを使用しようとすると、コンソールに次のエラーが表示されます。

```http
access to XMLHttpRequest at 'https://login.microsoftonline.com/common/oauth2/v2.0/token' from origin 'yourApp.com' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
```

その場合は、アプリの登録にアクセスし、アプリのリダイレクト URI を更新して `spa` の種類を使用するようにします。

アプリケーションが SPA 以外のフロー (ネイティブ アプリケーションまたはクライアント資格情報フローなど) で `spa` リダイレクト URI を使用することはできません。 セキュリティとベスト プラクティスを確保するために、`spa` ヘッダーなしで `Origin` リダイレクト URI を使用しようとすると、Microsoft ID プラットフォームからエラーが返されます。 同様に、Microsoft ID プラットフォームでは、シークレットがブラウザー内から使用されないようにするために、`Origin` ヘッダーが存在するすべてのフローでクライアント資格情報を使用することもできません。

### 承認コードを要求する

承認コード フローは、クライアントがユーザーを `/authorize` エンドポイントにリダイレクトさせることから始まります。 この要求の例では、クライアントはユーザーの `openid`、`offline_access`、`https://graph.microsoft.com/mail.read` のアクセス許可を要求します。

`Directory.ReadWrite.All` を使用した組織のディレクトリへのデータの書き込みなど、一部のアクセス許可は管理者によって制限されます。 アプリケーションが組織のユーザーにこれらのアクセス許可のいずれかへのアクセスを要求すると、ユーザーは、アプリのアクセス許可に同意する権限がないという内容のエラー メッセージを受け取ります。 管理者によって制限されるスコープへのアクセスを要求するには、全体管理者から直接要求する必要があります。 詳細については、「[管理者によって制限されるアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc#admin-restricted-permissions)」を参照してください。

特に指定しない限り、省略可能なパラメーターには既定値はありません。 ただし、省略可能なパラメーターを省略する要求の既定の動作があります。 既定の動作では、現在のユーザーのみにサインインしたり、複数のユーザーがいる場合はアカウント選択を表示したり、サインインしたユーザーがいない場合はログインページを表示したりします。

```http
// Line breaks for legibility only

https://login.microsoftonline.com/{tenant}/oauth2/v2.0/authorize?
client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&response_type=code
&redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F
&response_mode=query
&scope=https%3A%2F%2Fgraph.microsoft.com%2Fmail.read
&state=12345
&code_challenge=YTFjNjI1OWYzMzA3MTI4ZDY2Njg5M2RkNmVjNDE5YmEyZGRhOGYyM2IzNjdmZWFhMTQ1ODg3NDcxY2Nl
&code_challenge_method=S256
```

| パラメーター | 必須/省略可能 | 内容 |
| --- | --- | --- |
| `tenant` | 必須 | 要求パスの `{tenant}` の値を使用して、アプリケーションにサインインできるユーザーを制御します。 有効な値は、`common`、`organizations`、`consumers`、およびテナント識別子です。 あるテナントから別のテナントにユーザーをサインインさせるゲスト シナリオでは、ユーザーをリソース テナントにサインインさせるためにテナント識別子を指定する "必要があります"。 詳しくは、「[エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols#endpoints)」をご覧ください。 |
| `client_id` | 必須 | **[Microsoft Entra 管理センター - アプリの登録]** エクスペリエンスからアプリに割り当てられた[アプリケーション (クライアント) ID](https://go.microsoft.com/fwlink/?linkid=2083908)。 |
| `response_type` | 必須 | 承認コード フローでは `code` を指定する必要があります。 `id_token`を使用する場合は、`id_token` または  を含めることもできます。 |
| `redirect_uri` | 必須 | アプリの `redirect_uri`。ここでは、認証応答をアプリで送受信できます。 これは、Microsoft Entra 管理センターに登録したリダイレクト URI のいずれかと完全に一致する必要があります。ただし、URL エンコードが行われている必要があります。 ネイティブまたはモバイル アプリの場合は、推奨される値 (埋め込みのブラウザーを使用するアプリの場合は `https://login.microsoftonline.com/common/oauth2/nativeclient`、システム ブラウザーを使用するアプリの場合は `http://localhost`) のいずれかを使用します。 |
| `scope` | 必須 | ユーザーに同意を求める [スコープ](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview) の、スペースで区切られたリスト。 要求の `/authorize` 段階では、このパラメーターは複数のリソースを対象にすることができます。 この値を指定すると、呼び出す複数の Web API に対してアプリで同意を得ることができます。 |
| `response_mode` | 推奨 | ID プラットフォームが要求されたトークンをアプリにどのように返すかを指定します。 サポートされる値: - `query`: アクセス トークンを要求する場合の既定値。 リダイレクト URI でクエリ文字列パラメーターとしてコードを提供します。 `query` パラメーターは、暗黙的なフローを使用して ID トークンを要求する場合はサポートされません。  - `fragment`: 暗黙的なフローを使用して ID トークンを要求する場合の既定値。 また、コード *のみ* を要求する場合サポートされます。 - `form_post`: リダイレクト URI に対するコードを含んだ POST が実行されます。 コードを要求するときにサポートされます。 |
| `prompt` | 省略可能 | ユーザーとの必要な対話の種類を指定します。 有効な値は、`login`、`none`、`consent`、`select_account` です。 - `prompt=login` を指定すると、ユーザーはその要求に対して自分の資格情報の入力を強制され、シングル サインオンが無効になります。 - `prompt=none` はその逆です。 これを指定すると、ユーザーに対して対話形式のプロンプトは表示されません。 シングル サインオンを使用して確認なしで要求を完了できない場合は、Microsoft ID プラットフォームから `interaction_required` エラーが返されます。 - `prompt=consent` を指定すると、ユーザーがサインインした後で OAuth 同意ダイアログが表示され、アプリへのアクセス許可の付与をユーザーは求められます。 - `prompt=select_account` を指定すると、シングル サインオンは中断され、まったく別のアカウントの使用を選択するためのオプションとして、セッション内または記憶されているアカウント内のいずれかにある全アカウントを一覧表示するアカウント選択エクスペリエンスが提供されます。 |
| `login_hint` | 省略可能 | このパラメーターを使用すると、ユーザーに代わって、サインイン ページのユーザー名およびメール アドレス フィールドに事前入力できます。 アプリでは、以前のサインインから `login_hint`[オプション クレーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)を抽出した後、再認証時にこのパラメーターが使用されます。 |
| `domain_hint` | 省略可能 | これが含まれていると、サインイン ページでユーザーが経由するメール ベースの検出プロセスがアプリでスキップされ、多少効率化されたユーザー エクスペリエンスが提供されます。 たとえば、フェデレーション ID プロバイダーにユーザーを送信するなどです。 アプリでは、前回のサインインから `tid` を抽出することで、再認証時にこのパラメーターを使用できます。 |
| `code_challenge` | 推奨/必須 | Proof Key for Code Exchange (PKCE) を使用して認可コード付与をセキュリティ保護するために使用されます。 `code_challenge_method` が含まれている場合は必須です。 詳細については、「[PKCE RFC](https://tools.ietf.org/html/rfc7636)」を参照してください。 このパラメーターは、すべての種類のアプリケーション (パブリックと機密の両方のクライアント) で推奨されるようになり、[認可コード フローを使用するシングル ページ アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-third-party-cookies-spas)の場合は、Microsoft ID プラットフォームにより必須となりました。 |
| `code_challenge_method` | 推奨/必須 | `code_verifier` パラメーターの `code_challenge` をエンコードするために使用されるメソッド。 これは  である "べき"`S256` ですが、クライアントで SHA256 がサポートできない場合、仕様では `plain` の使用が許可されています。 除外されていると、`code_challenge` が含まれている場合、`code_challenge` はプレーンテキストであると見なされます。 Microsoft ID プラットフォームは `plain` と `S256` の両方をサポートします。 詳細については、「[PKCE RFC](https://tools.ietf.org/html/rfc7636)」を参照してください。 このパラメーターは、[認可コード フローを使用するシングル ページ アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-third-party-cookies-spas)には必須です。 |
| `state` | 推奨 | 要求に含まれ、トークンの応答としても返される値。 任意の文字列を指定することができます。 [クロスサイト リクエスト フォージェリ攻撃を防ぐ](https://tools.ietf.org/html/rfc6749#section-10.12)ために通常、ランダムに生成された一意の値が使用されます。 また、この値で、認証要求の発生前のアプリにおけるユーザーの状態に関する情報のエンコードができます。 たとえば、表示していたページまたはビューをエンコードできます。 |

セキュリティとプライバシーのために、状態パラメーターに URL やその他の機密データを直接配置しないでください。 代わりに、localStorage や sessionStorage などのブラウザー ストレージに格納されているデータに対応するキーまたは識別子を使用します。 この方法により、アプリは認証後に必要なデータを安全に参照できます。

この時点で、ユーザーに資格情報の入力と認証が求められます。 また、Microsoft ID プラットフォームでは、ユーザーが `scope` クエリ パラメーターに示されたアクセス許可に同意していることも確認されます。 いずれのアクセス許可にもユーザーが同意しなかった場合、必要なアクセス許可に同意するようユーザーは求められます。 詳細については、「[Microsoft ID プラットフォーム エンドポイントでのアクセス許可と同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)」を参照してください。

ユーザーが認証され、同意すると、Microsoft ID プラットフォームは `redirect_uri` パラメーターで指定されたメソッドを使用して、指定された `response_mode` でアプリに応答を返します。

##### 成功応答

この例では、`response_mode=query` を使用した正常な応答を示します。

```HTTP
GET http://localhost?
code=AwABAAAAvPM1KaPlrEqdFSBzjqfTGBCmLdgfSTLEMPGYuNHSUYBrq...
&state=12345
```

| パラメーター | 内容 |
| --- | --- |
| `code` | アプリが要求した `authorization_code`。 アプリは認証コードを使用して、ターゲット リソースのアクセス トークンを要求できます。 認可コードの有効期間は短時間です。 通常は、約 1 分後に有効期限が切れます。 |
| `state` | 要求に `state` パラメーターが含まれている場合、同じ値が応答にも含まれることになります。 要求と応答に含まれる状態値が同一であることをアプリ側で確認する必要があります。 |

また、ID トークンを要求し、アプリケーションの登録で暗黙的な許可が有効になっている場合には、その ID トークンを受け取ることができます。 この動作は、"ハイブリッド フロー" と呼ばれる場合があります。 これは、ASP.NET のようなフレームワークによって使用されます。

##### エラー応答

アプリ側でエラーを適切に処理できるよう、 `redirect_uri` にはエラー応答も送信されます。

```HTTP
GET http://localhost?
error=access_denied
&error_description=the+user+canceled+the+authentication
```

| パラメーター | 内容 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 エラーのこの部分は、アプリがエラーに適切に対処できるよう提供されますが、エラーが発生した理由を詳しく説明するものではありません。 |
| `error_description` | 認証エラーの原因を開発者が特定するのに役立つ具体的なエラー メッセージ。 エラーのこの部分には、エラーが発生した"理由" に関する有用な情報のほとんどが含まれています。 |

##### 承認エンドポイント エラーのエラー コード

次の表で、エラー応答の `error` パラメーターで返される可能性のあるさまざまなエラー コードを説明します。

| エラー コード | 内容 | クライアント側の処理 |
| --- | --- | --- |
| `invalid_request` | 必要なパラメーターが不足しているなどのプロトコル エラーです。 | 要求を修正し再送信します。 このエラーは、開発エラーであり、通常は初期テスト中に発生します。 |
| `unauthorized_client` | クライアント アプリケーションは、承認コードの要求を許可されていません。 | 通常、このエラーは、クライアント アプリケーションが Microsoft Entra ID に登録されていない、またはユーザーの Microsoft Entra テナントに追加されていないときに発生します。 アプリケーションでは、アプリケーションのインストールと Microsoft Entra ID への追加を求める指示をユーザーに表示できます。 |
| `access_denied` | リソースの所有者が同意を拒否しました。 | クライアント アプリケーションは、ユーザーが同意しないと続行できないことを、ユーザーに通知できます。 |
| `unsupported_response_type` | 承認サーバーでは、要求に含まれる応答の種類がサポートされていません。 | 要求を修正し再送信します。 このエラーは、開発エラーであり、通常は初期テスト中に発生します。 ハイブリッド フローでは、このエラーは、クライアント アプリの登録で ID トークンの暗黙的な許可設定を有効にする必要があることを示します。 |
| `server_error` | サーバーで予期しないエラーが発生しました。 | 要求をやり直してください。 これらのエラーは一時的な状況によって発生します。 クライアント アプリケーションは、一時的なエラーのため応答が遅れることをユーザーに説明する場合があります。 |
| `temporarily_unavailable` | サーバーが一時的にビジー状態であるため、要求を処理できません。 | 要求をやり直してください。 クライアント アプリケーションは、一時的な状況が原因で応答が遅れることをユーザーに説明する場合があります。 |
| `invalid_resource` | ターゲット リソースは、存在しない、Microsoft Entra ID で見つけられない、または正しく構成されていないために無効です。 | このエラーは、リソース (存在する場合) がテナントで構成されていないことを示します。 アプリケーションでは、アプリケーションのインストールと Microsoft Entra ID への追加を求める指示をユーザーに表示できます。 |
| `login_required` | ユーザーが多すぎるか、見つかりません。 | クライアントからサイレント認証が要求されましたが (`prompt=none`)、ユーザーは 1 人も見つかりませんでした。 このエラーは、セッションで複数のユーザーがアクティブになっているか、ユーザーがいないことを意味する可能性があります。 このエラーでは、選択したテナントが考慮されます。 たとえば、2 つのアクティブな Microsoft Entra アカウントと 1 つの Microsoft アカウントがあり、`consumers` が選択されている場合、サイレント認証は機能します。 |
| `interaction_required` | 要求にユーザーの介入が必要です。 | 別の認証手順または同意が必要になります。 `prompt=none` なしで要求を再試行してください。 |

#### ID トークンも要求する (ハイブリッド フロー)

認可コードの引き換え前にユーザーが誰であるかを確認するには、一般的に、アプリケーションで認可コードを要求するときに ID トークンも要求します。 この手法は、OIDC と OAuth2 の認可コードフローが混在するため、*ハイブリッド フロー* と呼ばれます。

ハイブリッド フローは、コードの引き換え時にブロックなしでユーザーにページをレンダリングする ために Web アプリで一般的に使用されます (特に [ASP.NET](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-v2-aspnet-core-webapp))。 シングルページ アプリも従来の Web アプリも、このモデルの待機時間短縮のメリットを得ることができます。

ハイブリッド フローは、前述の認可コード フローと同じですが、3 つの追加があります。 ID トークンを要求するためには、これらの追加 (新しいスコープ、新しい response\_type、新しい `nonce` クエリ パラメーター) のすべてが必要です。

```http
// Line breaks for legibility only

https://login.microsoftonline.com/{tenant}/oauth2/v2.0/authorize?
client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&response_type=code%20id_token
&redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F
&response_mode=fragment
&scope=openid%20offline_access%20https%3A%2F%2Fgraph.microsoft.com%2Fuser.read
&state=12345
&nonce=abcde
&code_challenge=YTFjNjI1OWYzMzA3MTI4ZDY2Njg5M2RkNmVjNDE5YmEyZGRhOGYyM2IzNjdmZWFhMTQ1ODg3NDcxY2Nl
&code_challenge_method=S256
```

| 更新対象パラメーター | 必須/省略可能 | 内容 |
| --- | --- | --- |
| `response_type` | 必須 | `id_token` の追加により、アプリケーションが `/authorize` エンドポイントからの応答で ID トークンを要求していることがサーバーに示されます。 |
| `scope` | 必須 | ID トークンの場合、このパラメーターを更新して ID トークン スコープ (`openid` と、省略可能な `profile` および `email`) を含める必要があります。 |
| `nonce` | 必須 | アプリによって生成された、要求に含まれる値。この値が、最終的な `id_token` に要求として含まれます。 アプリでこの値を確認することにより、トークン再生攻撃を緩和することができます。 通常、この値はランダム化された一意の文字列であり、要求の送信元を特定する際に使用できます。 |
| `response_mode` | 推奨 | 結果として得られたトークンをアプリに返す際に使用するメソッドを指定します。 既定値は、認可コードのみ向けに `query` ですが、`fragment`で指定されているように、要求に `id_token``response_type` を含める場合は  に設定します。特にリダイレクト URI として `form_post` を使用する場合は、アプリで `http://localhost` を使用することをお勧めします。 |

`fragment` を応答モードとして使用すると、リダイレクトからコードを読み取る Web アプリに問題が発生します。 このフラグメントはブラウザーから Web サーバーに渡されません。 このような状況では、すべてのデータがサーバーに送信されるようにするために、アプリで `form_post` 応答モードを使用してください。

##### 成功応答

この例では、`response_mode=fragment` を使用した正常な応答を示します。

```http
GET https://login.microsoftonline.com/common/oauth2/nativeclient#
code=AwABAAAAvPM1KaPlrEqdFSBzjqfTGBCmLdgfSTLEMPGYuNHSUYBrq...
&id_token=eYj...
&state=12345
```

| パラメーター | 内容 |
| --- | --- |
| `code` | アプリが要求した承認コード。 アプリは認証コードを使用して、ターゲット リソースのアクセス トークンを要求できます。 承認コードは有効期間が短く、通常は約 1 分後に期限切れになります。 |
| `id_token` | "暗黙的な許可" を使用して発行された、ユーザーの ID トークン。 同じ要求に `c_hash` のハッシュである特別な `code` 要求が含まれます。 |
| `state` | 要求に `state` パラメーターが含まれている場合、同じ値が応答にも含まれることになります。 要求と応答に含まれる状態値が同一であることをアプリ側で確認する必要があります。 |

### アクセス トークンのコードを引き換える

すべての機密クライアントでは、クライアント シークレットまたは証明書資格情報の使用を選択できます。 対称共有シークレットは、Microsoft ID プラットフォームによって生成されます。 証明書の資格情報は、開発者によってアップロードされた非対称キーです。 詳細については、「[Microsoft ID プラットフォーム アプリケーションの認証証明書資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials)」を参照してください。

最高のセキュリティのために、証明書資格情報を使用することをお勧めします。 ネイティブ アプリケーションとシングル ページ アプリを含むパブリック クライアントでは、認可コードの引き換え時にシークレットも証明書も使用しないでください。 リダイレクト URI がアプリケーションの種類を含み、[一意である](https://learn.microsoft.com/ja-jp/entra/identity-platform/reply-url#localhost-exceptions)ことを必ず確認してください。

#### client\_secret を使用してアクセス トークンを要求する

`authorization_code` を取得し、ユーザーからアクセス許可を得たら、リソースに対する `code` の `access_token` を引き換えることができます。 `code` を引き換えるには、`POST` 要求を `/token` エンドポイントに送信します。

```http
// Line breaks for legibility only

POST /{tenant}/oauth2/v2.0/token HTTP/1.1
Host: https://login.microsoftonline.com
Content-Type: application/x-www-form-urlencoded

client_id=11112222-bbbb-3333-cccc-4444dddd5555
&scope=https%3A%2F%2Fgraph.microsoft.com%2Fmail.read
&code=OAAABAAAAiL9Kn2Z27UubvWFPbm0gLWQJVzCTE9UkP3pSx1aXxUjq3n8b2JRLk4OxVXr...
&redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F
&grant_type=authorization_code
&code_verifier=ThisIsntRandomButItNeedsToBe43CharactersLong 
&client_secret=sampleCredentia1s    // NOTE: Only required for web apps. This secret needs to be URL-Encoded.
```

| パラメーター | 必須/省略可能 | 内容 |
| --- | --- | --- |
| `tenant` | 必須 | 要求パスの `{tenant}` の値を使用して、アプリケーションにサインインできるユーザーを制御します。 有効な値は、`common`、`organizations`、`consumers`、およびテナント識別子です。 詳しくは、「[エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols#endpoints)」をご覧ください。 |
| `client_id` | 必須 | **[Microsoft Entra管理センター - アプリの登録]** ページからアプリに割り当てられた[アプリケーション (クライアント) ID](https://go.microsoft.com/fwlink/?linkid=2083908)。 |
| `scope` | 省略可能 | スペースで区切られたスコープのリスト。 このスコープはすべて、OIDC スコープ (`profile`、`openid`、`email`) に沿って、1 つのリソースからである必要があります。 詳細については、「[Microsoft ID プラットフォーム エンドポイントでのアクセス許可と同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)」を参照してください。 このパラメーターは、認可コード フローに対する Microsoft 拡張機能であり、トークンの引き換え時にトークンが必要なリソースをアプリで宣言できるようにするためのものです。 |
| `code` | 必須 | フローの最初の段階で取得した `authorization_code`。 |
| `redirect_uri` | 必須 | `redirect_uri` を取得するために使用されたものと同じ `authorization_code` 値。 |
| `grant_type` | 必須 | 承認コード フローでは `authorization_code` を指定する必要があります。 |
| `code_verifier` | 推奨 | authorization\_code を取得するために使用されたのと同じ `code_verifier`。 承認コード付与要求で PKCE が使用された場合は必須です。 詳細については、「[PKCE RFC](https://tools.ietf.org/html/rfc7636)」を参照してください。 |
| `client_secret` | 機密 Web アプリには必須 | アプリ登録ポータルで作成した、アプリケーションのシークレット。 `client_secret` をデバイスや Web ページに確実に保存することはできないため、ネイティブ アプリやシングル ページ アプリではアプリケーション シークレットを使用しないでください。 サーバー側で `client_secret` を安全に保存できる Web アプリや Web API では、これは必須です。 ここにあるすべてのパラメーターと同様に、クライアン トシークレットは、送信前に URL エンコードする必要があります。 この手順は、SDK によって行われます。 URI エンコードの詳細については、[URI の一般構文の仕様](https://tools.ietf.org/html/rfc3986#page-12)に関する記事を参照してください。 [RFC 6749](https://datatracker.ietf.org/doc/html/rfc6749#section-2.3.1) に従って、代わりに Authorization ヘッダーで資格情報を提供する基本認証パターンもサポートされています。 |

#### 証明書資格情報を使用してアクセス トークンを要求する

```http
// Line breaks for legibility only
POST /{tenant}/oauth2/v2.0/token HTTP/1.1
Host: login.microsoftonline.com
Content-Type: application/x-www-form-urlencoded

client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&scope=https%3A%2F%2Fgraph.microsoft.com%2Fmail.read
&code=OAAABAAAAiL9Kn2Z27UubvWFPbm0gLWQJVzCTE9UkP3pSx1aXxUjq3n8b2JRLk4OxVXr...
&redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F
&grant_type=authorization_code
&code_verifier=ThisIsntRandomButItNeedsToBe43CharactersLong
&client_assertion_type=urn%3Aietf%3Aparams%3Aoauth%3Aclient-assertion-type%3Ajwt-bearer
&client_assertion=eyJhbGciOiJSUzI1NiIsIng1dCI6Imd4OHRHeXN5amNScUtqRlBuZDdSRnd2d1pJMCJ9.eyJ{a lot of characters here}M8U3bSUKKJDEg
```

| パラメーター | 必須/省略可能 | 内容 |
| --- | --- | --- |
| `tenant` | 必須 | 要求パスの `{tenant}` の値を使用して、アプリケーションにサインインできるユーザーを制御します。 有効な値は、`common`、`organizations`、`consumers`、およびテナント識別子です。 詳細については、「[エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols#endpoints)」を参照してください。 |
| `client_id` | 必須 | **[Microsoft Entra管理センター - アプリの登録]** ページからアプリに割り当てられた[アプリケーション (クライアント) ID](https://go.microsoft.com/fwlink/?linkid=2083908)。 |
| `scope` | 省略可能 | スペースで区切られたスコープのリスト。 このスコープはすべて、OIDC スコープ (`profile`、`openid`、`email`) に沿って、1 つのリソースからである必要があります。 詳細については、[アクセス許可、同意、スコープ](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)に関する記事を参照してください。 このパラメーターは、認可コード フローに対する Microsoft 拡張機能です。 この拡張機能により、トークンの引き換え時に、トークンを必要とするリソースをアプリで宣言できるようになります。 |
| `code` | 必須 | フローの最初の段階で取得した `authorization_code`。 |
| `redirect_uri` | 必須 | `redirect_uri` を取得するために使用されたものと同じ `authorization_code` 値。 |
| `grant_type` | 必須 | 承認コード フローでは `authorization_code` を指定する必要があります。 |
| `code_verifier` | 推奨 | `code_verifier` を取得するために使用されたものと同じ `authorization_code`。 承認コード付与要求で PKCE が使用された場合は必須です。 詳細については、「[PKCE RFC](https://tools.ietf.org/html/rfc7636)」を参照してください。 |
| `client_assertion_type` | 機密 Web アプリには必須 | 証明書資格情報を使用するには、この値を `urn:ietf:params:oauth:client-assertion-type:jwt-bearer` に設定する必要があります。 |
| `client_assertion` | 機密 Web アプリには必須 | JSON Web トークン (JWT) であるアサーション。これを作成し、アプリケーションの資格情報として登録した証明書で署名する必要があります。 証明書の登録方法とアサーションの形式の詳細については、[証明書資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials)に関する記事を参照してください。 |

パラメーターは共有シークレットによる要求の場合と同じです。ただし、`client_secret` パラメーターが `client_assertion_type` と `client_assertion` の 2 つのパラメーターに置き換えられている点を除きます。

#### 成功応答

次の例は、正常なトークンの応答を示しています。

```json
{
    "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik5HVEZ2ZEstZnl0aEV1Q...",
    "token_type": "Bearer",
    "expires_in": 3599,
    "scope": "https%3A%2F%2Fgraph.microsoft.com%2Fmail.read",
    "refresh_token": "AwABAAAAvPM1KaPlrEqdFSBzjqfTGAMxZGUTdM0t4B4...",
    "id_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.eyJhdWQiOiIyZDRkMTFhMi1mODE0LTQ2YTctOD..."
}
```

| パラメーター | 内容 |
| --- | --- |
| `access_token` | 要求されたアクセス トークン。 アプリはこのトークンを使用して、Web API など、保護されたリソースに対して認証できます。 |
| `token_type` | トークン タイプ値を指定します。 Microsoft Entra ID でサポートされる種類は `Bearer` のみです。 |
| `expires_in` | アクセス トークンの有効期間 (秒)。 |
| `scope` | `access_token` が有効である範囲。 省略可能。 これは非標準です。省略した場合、トークンはフローの最初の段階で要求されたスコープ用になります。 |
| `refresh_token` | OAuth 2.0 更新トークン。 現在のアクセス トークンの有効期限が切れた後に他のアクセス トークンを取得するために、アプリでこのトークンを使用できます。 更新トークンの有効期間は長期です。 これは、リソースへのアクセスを長期間維持できます。 アクセス トークンの更新の詳細については、この記事で後述する「アクセス トークンを更新する」を参照してください。**注:**`offline_access` スコープが要求された場合のみ提供されます。 |
| `id_token` | JSON Web トークン。 アプリは、このトークンのセグメントをデコードして、サインインしたユーザーに関する情報を要求することができます。 アプリではこの値をキャッシュして表示できます。また、機密クライアントでは認可にこのトークンを使用できます。 id\_token の詳細については、[`id_token reference`](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)を参照してください。 **注:**`openid` スコープが要求された場合のみ提供されます。 |

#### エラー応答

この例は、エラー応答です。

```json
{
  "error": "invalid_scope",
  "error_description": "AADSTS70011: The provided value for the input parameter 'scope' is not valid. The scope https://foo.microsoft.com/mail.read is not valid.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: 2016-01-09 02:02:12Z",
  "error_codes": [
    70011
  ],
  "timestamp": "2016-01-09 02:02:12Z",
  "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333",
  "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd"
}
```

| パラメーター | 内容 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの原因を開発者が特定するのに役立つ具体的なエラー メッセージ。 |
| `error_codes` | 診断に役立つ STS 固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | 診断に役立つ、要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |

#### トークン エンドポイント エラーのエラー コード

| エラー コード | 内容 | クライアント側の処理 |
| --- | --- | --- |
| `invalid_request` | 必要なパラメーターが不足しているなどのプロトコル エラーです。 | 要求またはアプリの登録を修正し、要求を再送信します。 |
| `invalid_grant` | 承認コードまたは PKCE コード検証機能が無効か、有効期限切れです。 | `/authorize` エンドポイントに対する新しい要求を試し、`code_verifier` パラメーターが正しいことを確認します。 |
| `unauthorized_client` | 認証されたクライアントは、この承認付与の種類を使用する権限がありません。 | 通常、このエラーは、クライアント アプリケーションが Microsoft Entra ID に登録されていない、またはユーザーの Microsoft Entra テナントに追加されていないときに発生します。 アプリケーションでは、アプリケーションのインストールと Microsoft Entra ID への追加を求める指示をユーザーに表示できます。 |
| `invalid_client` | クライアント認証に失敗しました。 | クライアント資格情報が有効ではありません。 修正するには、アプリケーション管理者が資格情報を更新します。 |
| `unsupported_grant_type` | 認可付与タイプが承認サーバーでサポートされていません。 | 要求の付与の種類を変更します。 この種のエラーは、開発時にのみ発生し、初期テスト中に検出する必要があります。 |
| `invalid_resource` | ターゲット リソースは、存在しない、Microsoft Entra ID で見つけられない、または正しく構成されていないために無効です。 | このコードは、リソース (存在する場合) がテナントで構成されていないことを示します。 アプリケーションでは、アプリケーションのインストールと Microsoft Entra ID への追加を求める指示をユーザーに表示できます。 |
| `interaction_required` | このコードは、OIDC 仕様では `/authorize` エンドポイントでのみ必要になるため、非標準です。 要求にユーザーの介入が必要です。 たとえば、別の認証手順が必要です。 | 同じスコープで `/authorize` 要求を再試行します。 |
| `temporarily_unavailable` | サーバーが一時的にビジー状態であるため、要求を処理できません。 | 短い遅延後に要求を再試行します。 クライアント アプリケーションは、一時的な状況が原因で応答が遅れることをユーザーに説明する場合があります。 |
| `consent_required` | 要求にはユーザーの同意が必要です。 このエラーは非標準です。 これは通常、OIDC の仕様に従って `/authorize` エンドポイントでのみ返されます。 要求するアクセス許可がクライアント アプリにないコード引き換えフローで `scope` パラメーターが使用された場合に返されます。 | クライアントでは、同意をトリガーするために、正しいスコープの `/authorize` エンドポイントにユーザーを返信する必要があります。 |
| `invalid_scope` | アプリによって要求されたスコープが無効です。 | 認証要求の `scope` パラメーターの値を有効な値に更新します。 |

注意

シングル ページ アプリで `invalid_request` エラーが発生することがあります。これは、クロスオリジン トークンの使用が、"シングル ページ アプリケーション" クライアント タイプにしか許可されないことを示します。 これは、トークンを要求するために使用されるリダイレクト URI が `spa` リダイレクト URI としてマークされていないことを示します。 このフローを有効にする方法については、アプリケーションの登録手順に関する記事を参照してください。

### アクセス トークンを使用する

`access_token` を無事取得したら、そのトークンを `Authorization` ヘッダーに追加することによって、Web API への要求に使用することができます。

```http
GET /v1.0/me/messages
Host: https://graph.microsoft.com
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik5HVEZ2ZEstZnl0aEV1Q...
```

### アクセス トークンを更新する

アクセス トークンの有効期間は短時間です。 リソースへのアクセスを継続するには、有効期限が切れた後に更新してください。 トークンを更新するには、もう一度 `POST` 要求を `/token` エンドポイントに送信します。 `refresh_token` の代わりに `code` を指定します。 更新トークンは、クライアントが既に同意を受け取ったすべてのアクセス許可に対して有効です。 たとえば、`scope=mail.read` の要求に対して発行された更新トークンを使用して、`scope=api://contoso.com/api/UseResource` の新しいアクセス トークンを要求できます。

Web アプリとネイティブ アプリの更新トークンには、指定された有効期間はありません。 通常、更新トークンの有効期間は比較的長いです。 ただし、場合によっては、更新トークンの有効期限が切れる、失効する、または操作のための十分な特権がないことがあります。 アプリケーションでは、トークン発行エンドポイントから返されるエラーを想定し、処理する必要があります。 シングル ページ アプリでは有効期間が 24 時間のトークンを取得するため、新しい認証が毎日必要になります。 このアクションは、サードパーティの Cookie が有効になっている場合に iframe でサイレントに実行できます。 これは、Safari などのサードパーティの Cookie を使用しないブラウザーで、最上位フレーム (ページ 全体のナビゲーションまたはポップアップ ウィンドウ) で実行される必要があります。

更新トークンは、新しいアクセス トークンの取得に使用されたときに取り消されません。 ご自分で古い更新トークンを破棄することが求められます。 [OAuth 2.0 仕様](https://tools.ietf.org/html/rfc6749#section-6)には次のようにあります。"承認サーバーで新しい更新トークンが発行される場合があります。この場合、クライアントは古い更新トークンを破棄し、新しい更新トークンに置き換える必要があります。 承認サーバーは新しい更新トークンをクライアントに発行した後に、古い更新トークンを取り消す場合があります。"

重要

`spa` として登録されたリダイレクト URI に送信される更新トークンの場合、更新トークンは 24 時間後に期限切れになります。 初期更新トークンを使用して取得された追加の更新トークンは、その有効期限を引き継ぎます。そのため、24 時間ごとに新しい更新トークンを取得するために、対話型認証を使用して認可コード フローを再実行するようにアプリを準備する必要があります。 ユーザーは自分の資格情報を入力する必要はなく、通常はユーザーエクスペリエンスも表示されず、アプリケーションを再読み込みするだけです。 ブラウザーは、ログイン セッションを表示するために、最上位フレームのログイン ページにアクセスする必要があります。 これは、[サード パーティの Cookie をブロックするブラウザーのプライバシー機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-third-party-cookies-spas)のためです。

```http
// Line breaks for legibility only

POST /{tenant}/oauth2/v2.0/token HTTP/1.1
Host: https://login.microsoftonline.com
Content-Type: application/x-www-form-urlencoded

client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&scope=https%3A%2F%2Fgraph.microsoft.com%2Fmail.read
&refresh_token=OAAABAAAAiL9Kn2Z27UubvWFPbm0gLWQJVzCTE9UkP3pSx1aXxUjq...
&grant_type=refresh_token
&client_secret=sampleCredentia1s    // NOTE: Only required for web apps. This secret needs to be URL-Encoded
```

| パラメーター | タイプ | 内容 |
| --- | --- | --- |
| `tenant` | 必須 | 要求パスの `{tenant}` の値を使用して、アプリケーションにサインインできるユーザーを制御します。 有効な値は、`common`、`organizations`、`consumers`、およびテナント識別子です。 詳しくは、「[エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols#endpoints)」をご覧ください。 |
| `client_id` | 必須 | **[Microsoft Entra 管理センター - アプリの登録]** エクスペリエンスからアプリに割り当てられた[アプリケーション (クライアント) ID](https://go.microsoft.com/fwlink/?linkid=2083908)。 |
| `grant_type` | 必須 | この段階の承認コード フローでは `refresh_token` を指定する必要があります。 |
| `scope` | 省略可能 | スペースで区切られたスコープのリスト。 この段階で要求するスコープは、当初の `authorization_code` 要求の段階で要求したスコープと同じか、またはそのサブセットである必要があります。 この要求で指定したスコープが複数のリソース サーバーにまたがる場合、Microsoft ID プラットフォームからは、最初のスコープで指定したリソースのトークンが返されます。 詳細については、「[Microsoft ID プラットフォーム エンドポイントでのアクセス許可と同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)」を参照してください。 |
| `refresh_token` | 必須 | フローの第 2 段階で取得した `refresh_token`。 |
| `client_secret` | Web アプリの場合は必須 | アプリ登録ポータルで作成した、アプリケーションのシークレット。 ネイティブ アプリでは使用しないでください。デバイスに `client_secret` を確実に保存できないためです。 サーバー側で `client_secret` を安全に保存できる Web アプリや Web API では、これは必須です。 このシークレットは URL エンコードする必要があります。 詳細については、[URI の一般構文の仕様](https://tools.ietf.org/html/rfc3986#page-12)に関する記事を参照してください。 |

##### 成功応答

次の例は、正常なトークンの応答を示しています。

```json
{
    "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik5HVEZ2ZEstZnl0aEV1Q...",
    "token_type": "Bearer",
    "expires_in": 3599,
    "scope": "https%3A%2F%2Fgraph.microsoft.com%2Fmail.read",
    "refresh_token": "AwABAAAAvPM1KaPlrEqdFSBzjqfTGAMxZGUTdM0t4B4...",
    "id_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.eyJhdWQiOiIyZDRkMTFhMi1mODE0LTQ2YTctOD..."
}
```

| パラメーター | 内容 |
| --- | --- |
| `access_token` | 要求されたアクセス トークン。 アプリはこのトークンを使用して、Web API など、保護されたリソースに対して認証できます。 |
| `token_type` | トークン タイプ値を指定します。 Microsoft Entra ID でサポートされる種類はベアラーのみです。 |
| `expires_in` | アクセス トークンの有効期間 (秒)。 |
| `scope` | `access_token` が有効である範囲。 |
| `refresh_token` | 新しい OAuth 2.0 更新トークン。 できるだけ長い時間、更新トークンを有効な状態に維持するために、この新しく取得した更新トークンで古い更新トークンを置き換えます。 **注:**`offline_access` スコープが要求された場合のみ提供されます。 |
| `id_token` | 無署名の JSON Web トークン。 アプリは、このトークンのセグメントをデコードして、サインインしたユーザーに関する情報を要求することができます。 アプリはこの値をキャッシュして表示できますが、承認やセキュリティ境界のためにこの値を使用することはできません。 `id_token` の詳細については、「`id_token`」を参照してください。 **注:**`openid` スコープが要求された場合のみ提供されます。 |

警告

この例のトークンを含めて、自分が所有していないすべての API について、トークンの検証や読み取りを行わないでください。 Microsoft サービスのトークンには、JWT として検証されない特殊な形式を使用できます。また、コンシューマー (Microsoft アカウント) ユーザーに対して暗号化される場合もあります。 トークンの読み取りは便利なデバッグおよび学習ツールですが、コード内でこれに対する依存関係を取得したり、自分で制御する API 用ではないトークンについての詳細を想定したりしないでください。

##### エラー応答

```json
{
  "error": "invalid_scope",
  "error_description": "AADSTS70011: The provided value for the input parameter 'scope' is not valid. The scope https://foo.microsoft.com/mail.read is not valid.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: 2016-01-09 02:02:12Z",
  "error_codes": [
    70011
  ],
  "timestamp": "2016-01-09 02:02:12Z",
  "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333",
  "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd"
}
```

| パラメーター | 内容 |
| --- | --- |
| `error` | エラーの種類を分類し、エラーに対処するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの根本的な原因を開発者が特定しやすいように記述した具体的なエラー メッセージ。 |
| `error_codes` | 診断に役立つ STS 固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | 診断に役立つ、要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |

エラー コードとクライアントに推奨される対処法については、「トークン エンドポイント エラーのエラー コード」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/v2-oauth2-client-creds-grant-flow"} -->
## Microsoft ID プラットフォームでの OAuth 2.0 クライアント資格情報フロー - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow
- Service: identity-platform
- Article date: 2026-01-30
- Summary: OAuth 2.0 認証プロトコルの Microsoft ID プラットフォーム実装を使用して Web アプリケーションを構築します。

OAuth 2.0 クライアント資格情報付与フローでは、Web サービス (機密クライアント) が、別の Web サービスを呼び出すときにユーザーを偽装するのではなく、独自の資格情報を使用して認証を行うことができます。 RFC 6749で指定された許可 (2 本足の OAuthとも呼ばれます) は、アプリケーションの ID を使用して Web ホスト型リソースにアクセスするために使用できます。 この型は、ユーザーとすぐにやり取りすることなくバックグラウンドで実行する必要があるサーバー間の対話に一般的に使用され、多くの場合、*デーモン* または *サービス アカウント*と呼ばれます。

注

[Microsoft Entra External ID](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview) に対してマシン間 (M2M) 認証を構成する場合は、[M2M Premium アドオン](https://www.microsoft.com/security/pricing/microsoft-entra-external-id/)を使用する必要があります。 組織のプレミアム アドオンの使用ポリシーを確認して、コストへの影響を理解し、実装が内部ガバナンスとライセンスのガイドラインに準拠していることを確認します。

クライアント資格情報フローでは、アクセス許可は管理者によってアプリケーション自体に直接付与されます。 アプリがリソースにトークンを提示すると、認証に関係するユーザーがいないため、リソースはアプリ自体がアクションを実行する承認を持っていることを強制します。 この記事では、次の両方の手順について説明します。

- API を呼び出すアプリケーションを承認する
- APIを呼び出すために必要なトークンを取得する方法について説明します。

この記事では、アプリケーション内のプロトコルに対して直接プログラムする方法について説明します。 可能な場合は、代わりにサポートされている Microsoft 認証ライブラリ (MSAL) を使用してトークンを取得し、セキュリティで保護された Web API呼び出 することをお勧めします。 MSALを使用する サンプル アプリを参照することもできます。 このフローでは、サイドノートとして、更新トークンは付与されることはありません。代わりに、`client_id` および `client_` を使ってアクセス トークンを取得することができます。更新トークンを取得するためには `client_` も必要ですが、ここではアクセス トークンの取得に使用します。

より高いレベルの保証のために、Microsoft ID プラットフォームでは、呼び出し元サービスは、共有シークレットではなく、証明書 またはフェデレーション資格情報を使用して認証することもできます。 アプリケーション独自の資格情報が使用されているため、これらの資格情報は安全に保管する必要があります。 *、その資格情報をソース コードに公開したり、Web ページに埋め込んだり、広く分散されたネイティブ アプリケーションで使用したり* しないでください。 クライアント資格情報フロー ページを使用した認証要求は許可されません。

### プロトコル図

クライアント資格情報フロー全体は、次の図のようになります。 各手順については、この記事の後半で説明します。

[Image: クライアント資格情報フローの] を示す図

### 直接承認を取得する

アプリは、通常、次の 2 つの方法のいずれかでリソースにアクセスするための直接承認を受け取ります。

- リソースのアクセス制御リスト (ACL) を利用する
- Microsoft Entra ID のアプリケーション許可の割り当てによる

これら 2 つの方法は Microsoft Entra ID で最も一般的であり、クライアント資格情報フローを実行するクライアントとリソースに推奨されます。 リソースは、他の方法でクライアントを承認することもできます。 各リソース サーバーは、そのアプリケーションに最も適した方法を選択できます。

#### アクセス制御リスト

リソース プロバイダーは、特定のレベルのアクセス権を認識して付与するアプリケーション (クライアント) ID の一覧に基づいて、承認チェックを適用できます。 リソースは、Microsoft ID プラットフォームからトークンを受け取ると、トークンをデコードし、`appid` および `iss` 要求からクライアントのアプリケーション ID を抽出できます。 次に、アプリケーションが保持するアクセス制御リスト (ACL) とアプリケーションを比較します。 ACL の細分性と方法は、リソースによって大きく異なる場合があります。

一般的なユース ケースは、ACL を使用して Web アプリケーションまたは Web API のテストを実行することです。 Web API は、特定のクライアントに完全なアクセス許可のサブセットのみを付与できます。 API でエンド ツー エンドのテストを実行するには、Microsoft ID プラットフォームからトークンを取得して API に送信するテスト クライアントを作成します。 次に、API は、API の機能全体へのフル アクセスについて、テスト クライアントのアプリケーション ID の ACL をチェックします。 この種類の ACL を使用する場合は、呼び出し元の `appid` 値だけでなく、トークンの `iss` 値が信頼されていることを確認してください。

この種類の承認は、個人の Microsoft アカウントを持つコンシューマー ユーザーが所有するデータにアクセスする必要があるデーモンとサービス アカウントに共通です。 組織が所有するデータの場合は、アプリケーションのアクセス許可を通じて必要な承認を取得することをお勧めします。

##### `roles` 要求なしでトークンを制御する

この ACL ベースの承認パターンを有効にするために、Microsoft Entra ID では、アプリケーションが別のアプリケーションのトークンを取得することを承認する必要はありません。 したがって、アプリ専用トークンは、`roles` 要求なしで発行できます。 API を公開するアプリケーションでは、トークンを受け入れるためにアクセス許可チェックを実装する必要があります。

アプリケーションに対するロールのないアプリケーション専用のアクセス トークンをアプリケーションで取得できないようにするには、[割り当て要件がアプリに対して有効になるようにします](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-access-management#requiring-user-assignment-for-an-app)。 これにより、ロールが割り当てされていないユーザーとアプリケーションは、このアプリケーションのトークンを取得できなくなります。

#### アプリケーションのアクセス許可

ACL を使用する代わりに、API を使用して一連の **アプリケーションのアクセス許可**公開できます。 これらは、組織の管理者によってアプリケーションに付与され、その組織とその従業員が所有するデータへのアクセスにのみ使用できます。 たとえば、Microsoft Graph では、次の操作を行うために、いくつかのアプリケーションアクセス許可が公開されています。

- すべてのメールボックスでメールを読み取る
- すべてのメールボックスのメールの読み取りと書き込み
- 任意のユーザーとしてメールを送信する
- ディレクトリ データの読み取り

(Microsoft Graph ではなく) 独自の API でアプリ ロール (アプリケーションのアクセス許可) を使用するには、まず、Microsoft Entra 管理センターの API のアプリ登録で アプリ ロールを公開 必要があります。 次 [、クライアント アプリケーションのアプリ登録でこれらのアクセス許可を選択して、必要なアプリ ロール](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#assign-app-roles-to-applications) を構成します。 API のアプリ登録でアプリ ロールを公開していない場合は、Microsoft Entra 管理センターでクライアント アプリケーションのアプリ登録でその API に対するアプリケーションのアクセス許可を指定することはできません。

(ユーザーではなく) アプリケーションとして認証する場合、委任されたアクセス許可使用することはできません。これは、アプリに代わって動作するユーザーが存在しないためです。 管理者または API の所有者によって付与されるアプリケーションのアクセス許可 (アプリ ロールとも呼ばれます) を使用する必要があります。

アプリケーションのアクセス許可の詳細については、「[アクセス許可と同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#types-of-permissions)」を参照してください。

##### 推奨: アプリロールを割り当てるために管理者をアプリにサインインさせる

通常、アプリケーションのアクセス許可を使用するアプリケーションをビルドする場合、アプリには、管理者がアプリのアクセス許可を承認するページまたはビューが必要です。 このページは、アプリケーションのサインイン フローやアプリの設定の一部、または専用の "接続" フローにすることができます。 多くの場合、ユーザーが職場または学校の Microsoft アカウントでサインインした後にのみ、この *接続* ビューをアプリに表示することは理にかなっています。

ユーザーをアプリにサインインさせる場合は、ユーザーにアプリケーションのアクセス許可の承認を求める前に、ユーザーが属している組織を特定できます。 厳密には必要ではありませんが、ユーザーにとってより直感的なエクスペリエンスを作成するのに役立ちます。 ユーザーにサインインするには、Microsoft ID プラットフォーム プロトコルのチュートリアル に従います。

##### ディレクトリ管理者にアクセス許可を要求する

組織の管理者にアクセス許可を要求する準備ができたら、管理者の同意エンドポイントMicrosoft ID プラットフォームにユーザーをリダイレクトできます。

```HTTP
// Line breaks are for legibility only.

GET https://login.microsoftonline.com/{tenant}/adminconsent?
client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&state=12345
&redirect_uri=http://localhost/myapp/permissions
```

ヒント: ブラウザーに次の要求を貼り付けてみます。

```
https://login.microsoftonline.com/common/adminconsent?client_id=00001111-aaaa-2222-bbbb-3333cccc4444&state=12345&redirect_uri=http://localhost/myapp/permissions
```

| パラメーター | 条件 | 説明 |
| --- | --- | --- |
| `tenant` | 必須 | アクセス許可を要求するディレクトリ テナント。 これは GUID またはフレンドリ名の形式で指定できます。 ユーザーが属しているテナントがわからない場合に、ユーザーが任意のテナントでサインインできるようにする場合は、`common`を使用します。 |
| `client_id` | 必須 | **[Microsoft Entra 管理センター - アプリの登録]** エクスペリエンスからアプリに割り当てられた[アプリケーション (クライアント) ID](https://go.microsoft.com/fwlink/?linkid=2083908)。 |
| `redirect_uri` | 必須 | アプリが処理するために応答を送信するリダイレクト URI。 ポータルに登録したリダイレクト URI のいずれかと完全に一致する必要があります。ただし、URL エンコードする必要があり、追加のパス セグメントを含めることができる点が異なります。 |
| `state` | 推奨 | 要求に含まれ、トークン応答でも返される値。 任意のコンテンツの文字列を指定できます。 状態は、認証要求が発生する前のユーザーの状態 (ページやビューなど) に関する情報をエンコードするために使用されます。 |

この時点で、Microsoft Entra ID は、テナント管理者のみがサインインして要求を完了できるように強制します。 管理者は、アプリ登録ポータルでアプリに対して要求したすべての直接アプリケーションのアクセス許可を承認するように求められます。

###### 成功した応答

管理者がアプリケーションのアクセス許可を承認した場合、成功した応答は次のようになります。

```HTTP
GET http://localhost/myapp/permissions?tenant=aaaabbbb-0000-cccc-1111-dddd2222eeee&state=state=12345&admin_consent=True
```

| パラメーター | 説明 |
| --- | --- |
| `tenant` | アプリケーションに要求されたアクセス許可を GUID 形式で付与したディレクトリ テナント。 |
| `state` | トークン応答にも返される要求に含まれる値。 任意のコンテンツの文字列を指定できます。 状態は、認証要求が発生する前のユーザーの状態 (ページやビューなど) に関する情報をエンコードするために使用されます。 |
| `admin_consent` | **を True**に設定します。 |

###### エラー応答

管理者がアプリケーションのアクセス許可を承認しない場合、失敗した応答は次のようになります。

```HTTP
GET http://localhost/myapp/permissions?error=permission_denied&error_description=The+admin+canceled+the+request
```

| パラメーター | 説明 |
| --- | --- |
| `error` | エラーの種類を分類するために使用でき、エラーに対応するために使用できるエラー コード文字列。 |
| `error_description` | エラーの根本原因を特定するのに役立つ特定のエラー メッセージ。 |

アプリ プロビジョニング エンドポイントから正常な応答を受け取った後、アプリは要求した直接のアプリケーションアクセス許可を取得しました。 これで、必要なリソースのトークンを要求できます。

### トークンを取得する

アプリケーションに必要な承認を取得したら、API のアクセス トークンの取得に進みます。 クライアント資格情報の付与を使用してトークンを取得するには、`/token` Microsoft ID プラットフォームに POST 要求を送信します。 いくつかの異なるケースがあります。

- 共有シークレットを使ったアクセス トークン要求
- 証明書を使ったアクセス トークン要求
- フェデレーション資格情報を使用してアクセス トークンの要求を行う

#### 最初のケース: 共有シークレットを使用してトークン要求にアクセスする

```HTTP
POST /{tenant}/oauth2/v2.0/token HTTP/1.1           //Line breaks for clarity
Host: login.microsoftonline.com:443
Content-Type: application/x-www-form-urlencoded

client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default
&client_secret=A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u
&grant_type=client_credentials
```

```bash
# Replace {tenant} with your tenant!
curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'client_id=00001111-aaaa-2222-bbbb-3333cccc4444&scope=https%3A%2F%2Fgraph.microsoft.com%2F.default&client_secret=A1bC2dE3f...&grant_type=client_credentials' 'https://login.microsoftonline.com/{tenant}/oauth2/v2.0/token'
```

| パラメーター | 条件 | 説明 |
| --- | --- | --- |
| `tenant` | 必須 | アプリケーションが操作を計画しているディレクトリ テナント (GUID またはドメイン名形式)。 |
| `client_id` | 必須 | アプリに割り当てられているアプリケーション ID。 この情報は、アプリを登録したポータルで確認できます。 |
| `scope` | 必須 | この要求で `scope` パラメーターに渡される値は、必要なリソースのリソース識別子 (アプリケーション ID URI) で、サフィックスは `.default`である必要があります。 含まれるすべてのスコープは、1 つのリソースに対するスコープである必要があります。 複数のリソースのスコープを含めると、エラーが発生します。 Microsoft Graph の例では、値は `https://graph.microsoft.com/.default`です。 この値は、アプリ用に構成したすべての直接アプリケーションアクセス許可のうち、エンドポイントが使用するリソースに関連付けられているトークンを発行する必要があることを Microsoft ID プラットフォームに通知します。 `/.default` スコープの詳細については、[同意に関するドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc#the-default-scope)を参照してください。 |
| `client_secret` | 必須 | アプリ登録ポータルでアプリ用に生成したクライアント シークレット。 クライアント シークレットは、送信前に URL でエンコードする必要があります。 代わりに Authorization ヘッダーに資格情報を提供する基本的な認証パターン [RFC 6749](https://datatracker.ietf.org/doc/html/rfc6749#section-2.3.1) もサポートされています。 |
| `grant_type` | 必須 | `client_credentials`に設定する必要があります。 |

#### 2 番目のケース: 証明書を使用したアクセス トークン要求

```HTTP
POST /{tenant}/oauth2/v2.0/token HTTP/1.1               // Line breaks for clarity
Host: login.microsoftonline.com:443
Content-Type: application/x-www-form-urlencoded

scope=https%3A%2F%2Fgraph.microsoft.com%2F.default
&client_id=11112222-bbbb-3333-cccc-4444dddd5555
&client_assertion_type=urn%3Aietf%3Aparams%3Aoauth%3Aclient-assertion-type%3Ajwt-bearer
&client_assertion=eyJhbGciOiJSUzI1NiIsIng1dCI6Imd4OHRHeXN5amNScUtqRlBuZDdSRnd2d1pJMCJ9.eyJ{a lot of characters here}M8U3bSUKKJDEg
&grant_type=client_credentials
```

| パラメーター | 条件 | 説明 |
| --- | --- | --- |
| `tenant` | 必須 | アプリケーションが操作を計画しているディレクトリ テナント (GUID またはドメイン名形式)。 |
| `client_id` | 必須 | アプリに割り当てられているアプリケーション (クライアント) ID。 |
| `scope` | 必須 | この要求で `scope` パラメーターに渡される値は、必要なリソースのリソース識別子 (アプリケーション ID URI) で、サフィックスは `.default`である必要があります。 含まれるすべてのスコープは、1 つのリソースに対するスコープである必要があります。 複数のリソースのスコープを含めると、エラーが発生します。 Microsoft Graph の例では、値は `https://graph.microsoft.com/.default`です。 この値は、アプリ用に構成したすべての直接アプリケーションアクセス許可のうち、エンドポイントが使用するリソースに関連付けられているトークンを発行する必要があることを Microsoft ID プラットフォームに通知します。 `/.default` スコープの詳細については、[同意に関するドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc#the-default-scope)を参照してください。 |
| `client_assertion_type` | 必須 | 値は `urn:ietf:params:oauth:client-assertion-type:jwt-bearer`に設定する必要があります。 |
| `client_assertion` | 必須 | 作成する必要があるアサーション (JSON Web トークン) です。このアサーションは、アプリケーションの資格情報として登録した証明書で署名する必要があります。 証明書の登録方法とアサーションの形式の詳細については、[証明書資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials)に関する記事を参照してください。 |
| `grant_type` | 必須 | `client_credentials`に設定する必要があります。 |

証明書ベースの要求のパラメーターは、共有シークレット ベースの要求とは 1 つの方法でのみ異なります。`client_secret` パラメーターは、`client_assertion_type` パラメーターと `client_assertion` パラメーターに置き換えられます。

#### 3 番目のケース: フェデレーション資格情報を使用したアクセス トークン要求

```HTTP
POST /{tenant}/oauth2/v2.0/token HTTP/1.1               // Line breaks for clarity
Host: login.microsoftonline.com:443
Content-Type: application/x-www-form-urlencoded

scope=https%3A%2F%2Fgraph.microsoft.com%2F.default
&client_id=11112222-bbbb-3333-cccc-4444dddd5555
&client_assertion_type=urn%3Aietf%3Aparams%3Aoauth%3Aclient-assertion-type%3Ajwt-bearer
&client_assertion=eyJhbGciOiJSUzI1NiIsIng1dCI6Imd4OHRHeXN5amNScUtqRlBuZDdSRnd2d1pJMCJ9.eyJ{a lot of characters here}M8U3bSUKKJDEg
&grant_type=client_credentials
```

| パラメーター | 条件 | 説明 |
| --- | --- | --- |
| `client_assertion` | 必須 | アプリケーションが、Kubernetes などの Microsoft ID プラットフォームの外部にある別の ID プロバイダーから取得するアサーション (JWT または JSON Web トークン)。 この JWT の詳細は、[フェデレーション ID 資格情報](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation-create-trust)としてアプリケーションに登録する必要があります。 [ワークロード ID フェデレーション](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation) について読むことで、他の ID プロバイダーから生成されたアサーションをどのように設定して使用するかを学びましょう。 |

要求内のすべてが証明書ベースのフローと同じですが、`client_assertion`のソースを除く重要な例外があります。 このフローでは、アプリケーションは JWT アサーション自体を作成しません。 代わりに、アプリは別の ID プロバイダーによって作成された JWT を使用します。 これは *[ワークロード ID フェデレーション](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation)*と呼ばれ、別の ID プラットフォーム内のアプリ ID を使用して Microsoft ID プラットフォーム内のトークンを取得します。 これは、Azure の外部でコンピューティングをホストしているが、Microsoft ID プラットフォームによって保護されている API にアクセスするなど、クラウド間のシナリオに最適です。 他の ID プロバイダーによって作成される JWT の必要な形式については、[アサーション形式](https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials#assertion-format)を参照してください。

#### 成功した応答

任意のメソッドからの正常な応答は次のようになります。

```json
{
  "token_type": "Bearer",
  "expires_in": 3599,
  "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik1uQ19WWmNBVGZNNXBP..."
}
```

| パラメーター | 説明 |
| --- | --- |
| `access_token` | 要求されたアクセス トークン。 アプリは、このトークンを使用して、セキュリティで保護されたリソース (Web API など) に対する認証を行うことができます。 |
| `token_type` | トークンの種類の値を示します。 Microsoft ID プラットフォームがサポートする唯一の種類は、`bearer`です。 |
| `expires_in` | アクセス トークンが有効な時間 (秒単位)。 |

警告

この例のトークンをコードに含め、所有していない API のトークンの検証や読み取りを試みないでください。 Microsoft サービスのトークンでは、JWT として検証されない特殊な形式を使用できます。また、コンシューマー (Microsoft アカウント) ユーザー向けに暗号化することもできます。 トークンの読み取りは便利なデバッグおよび学習ツールですが、コード内でこれに依存したり、制御する API 用ではないトークンに関する詳細を想定したりしないでください。

#### エラー応答

エラー応答 (400 Bad Request) は次のようになります。

```json
{
  "error": "invalid_scope",
  "error_description": "AADSTS70011: The provided value for the input parameter 'scope' is not valid. The scope https://foo.microsoft.com/.default is not valid.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: 2016-01-09 02:02:12Z",
  "error_codes": [
    70011
  ],
  "timestamp": "YYYY-MM-DD HH:MM:SSZ",
  "trace_id": "0000aaaa-11bb-cccc-dd22-eeeeee333333",
  "correlation_id": "aaaa0000-bb11-2222-33cc-444444dddddd"
}
```

| パラメーター | 説明 |
| --- | --- |
| `error` | 発生するエラーの種類を分類し、エラーに対応するために使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの根本原因を特定するのに役立つ可能性のある特定のエラー メッセージ。 |
| `error_codes` | 診断に役立つ STS 固有のエラー コードの一覧。 |
| `timestamp` | エラーが発生した時刻。 |
| `trace_id` | 診断に役立つ、要求の一意の識別子。 |
| `correlation_id` | コンポーネント間での診断に役立つ、要求の一意の識別子。 |

### トークンを使用する

トークンを取得したので、トークンを使用してリソースに要求を行います。 トークンが期限切れになったら、`/token` エンドポイントに要求を繰り返して、新しいアクセス トークンを取得します。

```HTTP
GET /v1.0/users HTTP/1.1
Host: graph.microsoft.com:443
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbG...
```

ターミナルで次のコマンドを試し、トークンを実際のトークンに置き換えてください。

```bash
curl -X GET -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbG..." 'https://graph.microsoft.com/v1.0/users'
```

### コード サンプルとその他のドキュメント

Microsoft 認証ライブラリの[クライアントの資格情報の概要に関するドキュメント](https://aka.ms/msal-net-client-credentials)を参照してください。

| サンプル | プラットホーム | 説明 |
| --- | --- | --- |
| [active-directory-dotnetcore-daemon-v2](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2) | .NET 6.0 以降 | ユーザーの代わりに、アプリケーションの ID を使用して Microsoft Graph に対してクエリを実行するテナントのユーザーを表示する ASP.NET Core アプリケーション。 このサンプルでは、認証に証明書を使用するバリエーションも示しています。 |
| [active-directory-dotnet-daemon-v2](https://github.com/Azure-Samples/active-directory-dotnet-daemon-v2) | ASP.NET MVC | ユーザーの代わりに、アプリケーションの ID を使用して Microsoft Graph のデータを同期する Web アプリケーション。 |
| [ms-identity-javascript-nodejs-console](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-console) | Node.js コンソール | アプリケーションの ID を使用して Microsoft Graph にクエリを実行してテナントのユーザーを表示する Node.js アプリケーション |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/v2-oauth2-device-code"} -->
## OAuth 2.0 デバイス承認付与 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code
- Service: identity-platform
- Article date: 2025-01-04
- Summary: ブラウザーを使用せずにユーザーをサインインします。 デバイス承認付与を使用して、埋め込み認証フローとブラウザーレス認証フローを構築します。

Microsoft ID プラットフォームでは、 [デバイス承認付与](https://tools.ietf.org/html/rfc8628)がサポートされています。これにより、ユーザーはスマート テレビ、IoT デバイス、プリンターなどの入力に制約のあるデバイスにサインインできます。 このフローを有効にするには、ユーザーが別のデバイスのブラウザーの Web ページにアクセスしてサインインするようにデバイスに設定します。 ユーザーがサインインすると、デバイスは必要に応じてアクセス トークンと更新トークンを取得できます。

この記事では、アプリケーション内のプロトコルに対して直接プログラムする方法について説明します。 可能な場合は、代わりにサポートされている Microsoft 認証ライブラリ (MSAL) を使用してトークンを取得し、セキュリティで保護された Web API [呼び出](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios#scenarios-and-supported-authentication-flows)することをお勧めします。 例として [MSAL を使用するサンプル アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/sample-v2-code) を参照できます。

### プロトコル図

デバイス コード フロー全体を次の図に示します。 各手順については、この記事全体で説明します。

[Image: デバイス コード フロー]

### デバイス承認要求

クライアントは最初に、認証を開始するために使用するデバイスとユーザー コードを認証サーバーで確認する必要があります。 クライアントは、 `/devicecode` エンドポイントからこの要求を収集します。 要求には、ユーザーから取得する必要があるアクセス許可もクライアントに含める必要があります。

要求が送信された時点から、ユーザーはサインインに 15 分かかります。 これは、`expires_in`の既定値です。 要求は、ユーザーがサインインの準備ができていることを示した場合にのみ行う必要があります。

```HTTP
// Line breaks are for legibility only.

POST https://login.microsoftonline.com/{tenant}/oauth2/v2.0/devicecode
Content-Type: application/x-www-form-urlencoded

client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&scope=user.read%20openid%20profile

```

| パラメーター | 条件 | 説明 |
| --- | --- | --- |
| `tenant` | 必須 | `/common`、`/consumers`、または `/organizations` を指定できます。 GUID またはフレンドリ名形式でアクセス許可を要求するディレクトリ テナントを指定することもできます。 |
| `client_id` | 必須 | **[Microsoft Entra 管理センター - アプリの登録]** エクスペリエンスからアプリに割り当てられた[アプリケーション (クライアント) ID](https://go.microsoft.com/fwlink/?linkid=2083908)。 |
| `scope` | 必須 | ユーザーに同意を求める [スコープ](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview) の、スペースで区切られたリスト。 |

#### デバイス承認応答

成功した応答は、ユーザーがサインインできるようにするために必要な情報を含む JSON オブジェクトです。

| パラメーター | 形式 | 説明 |
| --- | --- | --- |
| `device_code` | 糸 | クライアントと承認サーバーの間のセッションを確認するために使用される長い文字列。 クライアントはこのパラメーターを使用して、承認サーバーにアクセス トークンを要求します。 |
| `user_code` | 糸 | セカンダリ デバイス上のセッションを識別するために使用されるユーザーに表示される短い文字列。 |
| `verification_uri` | URI（統一リソース識別子） | サインインするためにユーザーが `user_code` で移動する URI。 |
| `expires_in` | 整数 (int) | `device_code` と `user_code` の有効期限か切れるまでの秒数。 |
| `interval` | 整数 (int) | ポーリング要求の間にクライアントが待機する秒数。 |
| `message` | 糸 | ユーザーの指示を含む人間が判読できる文字列。 これをローカライズするには、フォームの要求に`?mkt=xx-XX`を含め、適切な言語カルチャ コードを入力します。 |

注

現時点では、 `verification_uri_complete` 応答フィールドは含まれていないか、サポートされていません。 [これは、標準](https://tools.ietf.org/html/rfc8628)を読んだ場合、`verification_uri_complete`がデバイス コード フロー標準の省略可能な部分として一覧表示されるからです。

### ユーザーの認証

クライアントが `user_code` と `verification_uri`を受信すると、値が表示され、ユーザーはモバイルまたは PC ブラウザーを使用してサインインするように指示されます。

ユーザーが `/common` または `/consumers`を使用して個人アカウントで認証を行った場合、認証状態をデバイスに転送するために、もう一度サインインするように求められます。 これは、デバイスがユーザーの Cookie にアクセスできないためです。 クライアントによって要求されたアクセス許可に同意するように求められます。 ただし、これは認証に使用される職場または学校アカウントには適用されません。

ユーザーが`verification_uri`で認証している間、クライアントは、`/token`を使用して、要求されたトークンの`device_code` エンドポイントをポーリングする必要があります。

```HTTP
POST https://login.microsoftonline.com/{tenant}/oauth2/v2.0/token
Content-Type: application/x-www-form-urlencoded

grant_type=urn:ietf:params:oauth:grant-type:device_code&client_id=00001111-aaaa-2222-bbbb-3333cccc4444&device_code=GMMhmHCXhWEzkobqIHGG_EnNYYsAkukHspeYUk9E8...
```

| パラメーター | 必須 | 説明 |
| --- | --- | --- |
| `tenant` | 必須 | 最初の要求で使用されるのと同じテナントまたはテナントエイリアス。 |
| `grant_type` | 必須 | `Default` である必要があります。 |
| `client_id` | 必須 | 最初の要求で使用される `client_id` と一致する必要があります。 |
| `device_code` | 必須 | デバイス承認要求で返される `device_code` 。 |

#### 予期されるエラー

デバイス コード フローはポーリング プロトコルであるため、ユーザー認証が完了する前にクライアントに提供されるエラーが予想される必要があります。

| エラー | 説明 | クライアント側の処理 |
| --- | --- | --- |
| `authorization_pending` | ユーザーは認証を完了していませんが、フローを取り消していません。 | 少なくとも `interval` 秒後に要求を繰り返します。 |
| `authorization_declined` | エンド ユーザーが承認要求を拒否しました。 | ポーリングを停止し、認証されていない状態に戻します。 |
| `bad_verification_code` | `device_code` エンドポイントに送信された`/token`が認識されませんでした。 | クライアントが要求で正しい `device_code` を送信していることを確認します。 |
| `expired_token` | `expires_in`の値を超え、`device_code`では認証できなくなります。 | ポーリングを停止し、認証されていない状態に戻します。 |

#### 成功した認証応答

成功したトークン応答は次のようになります。

```json
{
    "token_type": "Bearer",
    "scope": "User.Read profile openid email",
    "expires_in": 3599,
    "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik5HVEZ2ZEstZnl0aEV1Q...",
    "refresh_token": "AwABAAAAvPM1KaPlrEqdFSBzjqfTGAMxZGUTdM0t4B4...",
    "id_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.eyJhdWQiOiIyZDRkMTFhMi1mODE0LTQ2YTctOD..."
}
```

| パラメーター | 形式 | 説明 |
| --- | --- | --- |
| `token_type` | 糸 | 常に `Bearer` です。 |
| `scope` | スペース区切り文字列 | アクセス トークンが返された場合、アクセス トークンが有効なスコープが一覧表示されます。 |
| `expires_in` | 整数 (int) | 含まれているアクセス トークンが有効な秒数。 |
| `access_token` | 不透明な文字列 | 要求された[スコープ](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)に対して発行されます。 |
| `id_token` | JWT | 元の `scope` パラメーターに `openid` スコープが含まれている場合に発行されます。 |
| `refresh_token` | 不透明な文字列 | `scope` に元のパラメーター `offline_access`が含まれている場合、発行されます。 |

更新トークンを使用すると、 [OAuth コード フローのドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#refresh-the-access-token)に記載されているのと同じフローを使用して、新しいアクセス トークンと更新トークンを取得できます。

警告

この例のトークンをコードに含め、所有していない API のトークンの検証や読み取りを試みないでください。 Microsoft サービスのトークンでは、JWT として検証されない特殊な形式を使用できます。また、コンシューマー (Microsoft アカウント) ユーザー向けに暗号化することもできます。 トークンの読み取りは便利なデバッグおよび学習ツールですが、コード内でこれに依存したり、制御する API 用ではないトークンに関する詳細を想定したりしないでください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/v2-oauth2-implicit-grant-flow"} -->
## Microsoft ID プラットフォームと OAuth 2.0 暗黙的な許可のフロー - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow
- Service: identity-platform
- Article date: 2025-01-04
- Summary: Microsoft ID プラットフォームの暗黙的なフローを使用してシングルページ アプリをセキュリティで保護します。

Microsoft ID プラットフォームでは、[OAuth 2.0 の仕様](https://tools.ietf.org/html/rfc6749#section-4.2)で説明されているように、OAuth 2.0 の暗黙的な許可フローがサポートされています。 暗黙的な許可には、トークン (ID トークンまたはアクセス トークン) が /token エンドポイントではなく /authorize エンドポイントから直接返されるという特徴があります。 これは多くの場合、"ハイブリッド フロー" と呼ばれる[承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)の一部として使用され、承認コードと共に /authorize 要求で ID トークンを取得します。

この記事では、Microsoft Entra ID からのトークンを要求するために、アプリケーションでプロトコルに対して直接プログラミングする方法について説明します。 可能な場合は、[トークンを取得してセキュリティで保護された Web API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios#scenarios-and-supported-authentication-flows)代わりに、サポートされている Microsoft 認証ライブラリ (MSAL) を使用することをお勧めします。 MSAL を使用するコード サンプルの一覧については、[Microsoft ID プラットフォーム コード サンプル](https://learn.microsoft.com/ja-jp/entra/identity-platform/sample-v2-code)を参照してください。

警告

マイクロソフトは、暗黙的な許可のフローを*使用しない*ようお勧めします。 ほとんどのシナリオでは、より安全な代替手段を利用でき、推奨されます。 このフローの構成によっては、アプリケーションで非常に高い信頼度が要求されるため、他のフローには存在しないリスクが伴います。 このフローは、より安全なフローが実行可能ではない場合にのみ使用してください。 詳細については、「暗黙的な許可フローに関するセキュリティの問題」を参照してください。

### プロトコルのダイアグラム

次の図は、暗黙的なサインイン フローの全体像を示しています。各手順については、この後のセクションで詳しく説明します。

[Image: 暗黙的なサインイン フローを示す図。]

#### 認証コード フローを優先する

[サード パーティのクッキーのサポートを削除する](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-third-party-cookies-spas)ブラウザーでは、**暗黙的な許可のフローは、認証方法として適切ではありません**。 暗黙的なフローのサイレント シングル サインオン （SSO） 機能は、サード パーティのクッキーがないと機能しないため、新しいトークンを取得しようとするとアプリケーションが中断します。 新しいアプリケーションではすべて、暗黙的なフローの代わりにシングル ページ アプリをサポートする[認証コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)を使用することを強くお勧めします。 既存のシングル ページ アプリも[認証コード フローに移行](https://learn.microsoft.com/ja-jp/entra/identity-platform/migrate-spa-implicit-to-auth-code)するようにしてください。

#### 暗黙的な許可フローに関するセキュリティの問題

[RFC 9700 OAuth 2.0 セキュリティのベスト カレント プラクティス、セクション 2.1.2 では、](https://datatracker.ietf.org/doc/html/rfc9700#name-implicit-grant) 暗黙的なトークンが推奨されていない理由について説明します。 承認コード フローを使用する必要があります。

### サインイン要求を送信する

最初にユーザーをアプリにサインインするために、[OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc) 認証要求を送信し、Microsoft ID プラットフォームから `id_token` を取得します。

重要

ID トークンおよびアクセス トークンを正しく要求するには、[Microsoft Entra 管理センターの \[アプリの登録\]](https://go.microsoft.com/fwlink/?linkid=2083908) ページのアプリ登録で、**[暗黙の付与およびハイブリッド フロー]** セクションの **[ID トークン]** および **[アクセス トークン]** を選択して、対応する暗黙的な許可フローを有効にする必要があります。 それが有効でない場合は、`unsupported_response` エラー

`The provided value for the input parameter 'response_type' is not allowed for this client. Expected value is 'code'`

```https
// Line breaks for legibility only

https://login.microsoftonline.com/{tenant}/oauth2/v2.0/authorize?
client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&response_type=id_token
&redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F
&scope=openid
&response_mode=fragment
&state=12345
&nonce=678910
```

| パラメーター | タイプ | 内容 |
| --- | --- | --- |
| `tenant` | 必須 | 要求パスの `{tenant}` の値を使用して、アプリケーションにサインインできるユーザーを制御します。 使用できる値は、`common`、`organizations`、`consumers` およびテナント識別子です。 詳細については、 [プロトコルの基礎](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols#endpoints)に関するページを参照してください。 重要なこととして、あるテナントから別のテナントにユーザーをサインインさせるゲスト シナリオでは、ユーザーをリソース テナントに正しくサインインさせるためにテナント識別子を指定する**必要があります**。 |
| `client_id` | 必須 | アプリに割り当てられている [\[Microsoft Entra管理センター - アプリの登録\]](https://go.microsoft.com/fwlink/?linkid=2083908) ページのアプリケーション (クライアント) ID。 |
| `response_type` | 必須 | OpenID Connect サインインでは、 `id_token` を指定する必要があります。 `response_type`、`token` が含まれる場合もあります。 ここで `token` を使用すると、アプリでは /authorize エンドポイントへ 2 度目の要求を行うことなく、/authorize エンドポイントからアクセス トークンをすぐに受け取ることができます。 `token` response\_type を使用する場合、`scope` パラメーターには、トークンを発行するリソースを示すスコープを含める必要があります (たとえば、Microsoft Graph では `user.read`)。 また、`code`で使用するため、承認コードを提供するのに `token` の代わりに  を含めることもできます。 この`id_token`+`code` 応答は、ハイブリッド フローと呼ばれることもあります。 |
| `redirect_uri` | 推奨 | アプリのリダイレクト URI。アプリは、この URI で認証応答を送受信します。 これは、Microsoft Entra 管理センターに登録したリダイレクト URI のいずれかと完全に一致する必要があります。ただし、URL エンコードが行われている必要があります。 |
| `scope` | 必須 | [スコープ](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)のスペース区切りリスト。 OpenID Connect (`id_tokens`) では、スコープとして `openid` を指定する必要があります。このスコープは、承認 UI で "サインイン" アクセス許可に変換されます。 必要に応じて、その他のユーザー データにアクセスするために `email` および `profile` スコープを含めることも可能です。 アクセス トークンを要求する場合、さまざまなリソースに対する同意を求めるこの要求に、他のスコープが含まれていてもかまいません。 |
| `response_mode` | 推奨 | 結果として得られたトークンをアプリに返す際に使用するメソッドを指定します。 既定値は、アクセス トークンだけの `query` (ただし、要求に id\_token が含まれている場合は `fragment`) です。 セキュリティ上の理由から、暗黙的フローの `form_post` を使用して、トークンが URL フラグメントで公開されないようにすることをお勧めします。 |
| `state` | 推奨 | トークン応答にも返される要求に含まれる値。 任意の文字列を指定することができます。 [クロスサイト リクエスト フォージェリ攻撃を防ぐ](https://tools.ietf.org/html/rfc6749#section-10.12)ために通常、ランダムに生成された一意の値が使用されます。 この状態は、認証要求の前にアプリ内でユーザーの状態 (表示中のページやビューなど) に関する情報をエンコードする目的にも使用されます。 |
| `nonce` | 必須 | 要求に追加する (アプリによって生成された) 値。この値が、最終的な ID トークンに要求として追加されます。 アプリでこの値を確認することにより、トークン再生攻撃を緩和することができます。 通常、この値はランダム化された一意の文字列であり、要求の送信元を特定する際に使用できます。 Id\_token が要求された場合のみ必須です。 |
| `prompt` | 省略可能 | ユーザーとの必要な対話の種類を指定します。 現時点で有効な値は、`login`、`none`、`select_account`、`consent` のみです。 `prompt=login` を指定すると、ユーザーはその要求に対して自分の認証情報の入力を強制され、シングル サインオンが無効になります。 `prompt=none` はその反対であり、ユーザーにどのような対話型プロンプトも表示されないようにします。 SSO で確認なしで要求を完了できない場合は、Microsoft ID プラットフォームからエラーが返されます。 `prompt=select_account` は、ユーザーを、セッションで記憶されているすべてのアカウントが表示されるアカウント ピッカーに送ります。 `prompt=consent` では、ユーザーがサインインした後で OAuth 同意ダイアログが表示され、アプリへのアクセス許可の付与をユーザーに求めます。 |
| `login_hint` | 省略可能 | このパラメーターを使用すると、ユーザー名が事前にわかっている場合、ユーザーに代わって、サインイン ページのユーザー名とメール アドレスのフィールドに事前に入力することができます。 多くの場合、アプリは、以前のサインインから `login_hint`[オプション クレーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)を抽出した後、認証時にこのパラメーターを使用します。 |
| `domain_hint` | 省略可能 | これが含まれていると、ユーザーがサインイン ページで実行する電子メール ベースの検出プロセスがスキップされ、多少効率化されたユーザー エクスペリエンスが提供されます。 このパラメーターは、1 つのテナントで動作する基幹業務アプリで一般的に使用され、アプリでは特定のテナント内のドメイン名が提供されます。これにより、ユーザーがそのテナントのフェデレーション プロバイダーに転送されます。 このヒントは、ゲストがこのアプリケーションにサインインできないようにし、FIDO などのクラウド認証情報の使用を制限します。 |

この時点で、ユーザーに認証情報の入力と認証が求められます。 また、Microsoft ID プラットフォームでは、ユーザーが `scope` クエリ パラメーターに示されたアクセス許可に同意していることも確認されます。 ユーザーが同意したアクセス許可がこれらの中に**ない**場合、必要なアクセス許可に同意するようユーザーに求めます。 詳細については、「[アクセス許可、同意、およびマルチテナント アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)」を参照してください。

ユーザーが認証され、同意すると、Microsoft ID プラットフォームは `redirect_uri` パラメーターで指定されたメソッドを使用して、指定された `response_mode` でアプリに応答を返します。

##### 成功応答

`response_mode=fragment` と `response_type=id_token+code` を使用した成功応答は、次のようになります (読みやすいように改行してあります)。

```https
GET https://localhost/myapp/#
code=0.AgAAktYV-sfpYESnQynylW_UKZmH-C9y_G1A
&id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik5HVEZ2ZEstZnl0aEV1Q...
&state=12345
```

| パラメーター | 内容 |
| --- | --- |
| `code` | `response_type` に `code` が含まれる場合に含まれます。 これは、[承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)での使用に適した承認コードです。 |
| `access_token` | `response_type` に `token` が含まれる場合に含まれます。 アプリが要求したアクセス トークン。 アクセス トークンはデコードしないようにする必要があります。そうしないと、検証した場合に不透明な文字列として扱われます。 |
| `token_type` | `response_type` に `token` が含まれる場合に含まれます。 これは常に `Bearer`です。 |
| `expires_in` | `response_type` に `token` が含まれる場合に含まれます。 キャッシュ用に有効なトークンの秒数を示します。 |
| `scope` | `response_type` に `token` が含まれる場合に含まれます。 `access_token`が有効な 1 つ以上のスコープを示します。 要求されたスコープであっても、ユーザーに該当しなければ含まれない場合もあります。 たとえば、個人アカウントを使用してログインするときに Microsoft Entra 専用スコープが要求された場合がそれに当たります。 |
| `id_token` | 署名付き JSON Web トークン (JWT)。 アプリは、このトークンのセグメントをデコードして、サインインしたユーザーに関する情報を要求することができます。 アプリはこの値をキャッシュして表示できますが、承認やセキュリティ境界のためにこの値を使用することはできません。 ID トークンの詳細については、[`id_token reference`](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens) を参照してください。 **注:**`openid` スコープが要求され、`response_type` に `id_tokens` が含まれる場合のみ提供されます。 |
| `state` | 要求に state パラメーターが含まれている場合、同じ値が応答にも含まれることになります。 要求と応答に含まれる状態値が同一であることをアプリ側で確認する必要があります。 |

セキュリティとプライバシーのために、状態パラメーターに URL やその他の機密データを直接配置しないでください。 代わりに、localStorage や sessionStorage などのブラウザー ストレージに格納されているデータに対応するキーまたは識別子を使用します。 この方法により、アプリは認証後に必要なデータを安全に参照できます。

警告

この例のトークンを含めて、自分が所有していないすべての API について、トークンの検証や読み取りを行わないでください。 Microsoft サービスのトークンには、JWT として検証されない特殊な形式を使用できます。また、コンシューマー (Microsoft アカウント) ユーザーに対して暗号化される場合もあります。 トークンの読み取りは便利なデバッグおよび学習ツールですが、コード内でこれに対する依存関係を取得したり、自分で制御する API 用ではないトークンについての詳細を想定したりしないでください。

#### エラー応答

アプリ側でエラーを適切に処理できるよう、 `redirect_uri` にはエラー応答も送信されます。

```https
GET https://localhost/myapp/#
error=access_denied
&error_description=the+user+canceled+the+authentication
```

| パラメーター | 内容 |
| --- | --- |
| `error` | 発生したエラーの種類を分類したりエラーに対処したりする際に使用するエラー コード文字列。 |
| `error_description` | 認証エラーの根本的な原因を開発者が特定しやすいように記述した具体的なエラー メッセージ。 |

### アクセス トークンをサイレントに取得する

これでユーザーをシングルページ アプリにサインインさせたので、[Microsoft Graph](https://developer.microsoft.com/graph) などの Microsoft ID プラットフォームによってセキュリティ保護された Web API を呼び出すためのアクセス トークンをサイレントに取得できます。 このメソッドを使用すると、`token` response\_type を使用してトークンを既に取得している場合でも、ユーザーをリダイレクトさせて再度サインインさせることなく、その他のリソースのトークンを取得できます。

重要

暗黙のフローのこの部分は、[既定でサード パーティの Cookie が 削除される](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-third-party-cookies-spas)ため、異なるブラウザーをまたいでアプリケーションを使用すると、そのアプリケーションでは機能しない可能性があります。 現在でも Incognito で使用されていない Chromium ベースのブラウザーでは機能しますが、開発者はフローのこの部分を使用することを再検討する必要があります。 サード パーティの Cookie をサポートしていないブラウザーでは、ログイン ページのセッション Cookie がブラウザーによって削除されるため、ユーザーがサインインしていないことを示すエラーが表示されます。

通常の OpenID Connect/OAuth フローでは、これは Microsoft ID プラットフォームの `/token` エンドポイントに要求を発行することによって行います。 非表示の iframe でこの要求を実行し、他の Web API 用の新しいトークンを取得できます。

```https
// Line breaks for legibility only

https://login.microsoftonline.com/{tenant}/oauth2/v2.0/authorize?
client_id=00001111-aaaa-2222-bbbb-3333cccc4444&response_type=token
&redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F
&scope=https%3A%2F%2Fgraph.microsoft.com%2Fuser.read
&response_mode=fragment
&state=12345
&nonce=678910
&prompt=none
&login_hint=myuser@mycompany.com
```

URL のクエリ パラメーターの詳細については、「サインイン要求を送信する」を参照してください。

ヒント

ご使用のアプリの登録から実際の `client_id` と `username` を使用して、次の要求をブラウザー タブにコピーして貼り付けてみてください。 これにより、サイレント トークン要求の動作を確認できます。

```https
https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id={your-client-id}&response_type=token&redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F&scope=https%3A%2F%2Fgraph.microsoft.com%2Fuser.read&response_mode=fragment&state=12345&nonce=678910&prompt=none&login_hint={username}
```

これは、iframe 内で開くのではなく、ブラウザー バーに直接入力するため、サード パーティの Cookie がサポートされていないブラウザーでも機能します。

`prompt=none` パラメーターに応じて、要求はすぐに成功または失敗し、アプリケーションに戻ります。 `redirect_uri` パラメーターで指定された方法を使用して、指定された `response_mode` でアプリに応答が送信されます。

#### 成功応答

`response_mode=fragment` を使用した場合の正常な応答は次のようになります。

```https
GET https://localhost/myapp/#
access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik5HVEZ2ZEstZnl0aEV1Q...
&state=12345
&token_type=Bearer
&expires_in=3599
&scope=https%3A%2F%2Fgraph.microsoft.com%2Fdirectory.read
```

| パラメーター | 内容 |
| --- | --- |
| `access_token` | `response_type` に `token` が含まれる場合に含まれます。 この場合は、Microsoft Graph 用にアプリケーションが要求したアクセス トークンです。 アクセス トークンはデコードしないようにする必要があります。そうしないと、検証した場合に不透明な文字列として扱われます。 |
| `token_type` | これは常に `Bearer`です。 |
| `expires_in` | キャッシュ用に有効なトークンの秒数を示します。 |
| `scope` | アクセス トークンが有効な 1 つ以上のスコープを示します。 要求されたスコープをユーザーに適用できなかった場合 (サインインのために個人用アカウントが使用されているときに Microsoft Entra 専用スコープが要求された場合)、必ずしもすべてのスコープが含まれないことがあります。 |
| `id_token` | 署名付き JSON Web トークン (JWT)。 `response_type` に `id_token` が含まれる場合に含まれます。 アプリは、このトークンのセグメントをデコードして、サインインしたユーザーに関する情報を要求することができます。 アプリはこの値をキャッシュして表示できますが、承認やセキュリティ境界のためにこの値を使用することはできません。 id\_token の詳細については、[`id_token` のリファレンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)を参照してください。 **注:**`openid` スコープが要求された場合のみ提供されます。 |
| `state` | 要求に state パラメーターが含まれている場合、同じ値が応答にも含まれることになります。 要求と応答に含まれる状態値が同一であることをアプリ側で確認する必要があります。 |

##### エラー応答

アプリ側で適切に処理できるように、 `redirect_uri` にエラーの応答が送信される場合もあります。 `prompt=none` の場合、次のエラーが発生します。

```https
GET https://localhost/myapp/#
error=user_authentication_required
&error_description=the+request+could+not+be+completed+silently
```

| パラメーター | 内容 |
| --- | --- |
| `error` | 発生したエラーの種類を分類したりエラーに対処したりする際に使用するエラー コード文字列。 |
| `error_description` | 認証エラーの根本的な原因を開発者が特定しやすいように記述した具体的なエラー メッセージ。 |

Iframe 要求でこのエラーを受信した場合、ユーザーは対話形式でもう一度サインインして新しいトークンを取得する必要があります。 この場合は、アプリケーションに適した任意の方法で処理できます。

### トークンを更新する

暗黙的な許可では、更新トークンは提供されません。 ID トークンとアクセス トークンはどちらも短時間で期限切れになるため、これらのトークンを定期的に更新するようにアプリを準備しておく必要があります。 どちらの種類のトークンを更新する場合も、ID プラットフォームの動作を制御するための `prompt=none` パラメーターを使用して、この前で言及した非表示の iframe 要求を実行できます。 新しい ID トークンを受け取りたい場合は、`id_token` と `response_type` で `scope=openid` を使うことに加えて、`nonce` パラメーターも使用してください。

サード パーティの Cookie をサポートしていないブラウザーでは、ユーザーがサインインしていないことを示すエラーが発生します。

### サインアウト要求を送信する

OpenID Connect `end_session_endpoint` を使用すると、ユーザーのセッションを終了させ、Microsoft ID プラットフォームによって設定された Cookie をクリアする要求がアプリから Microsoft ID プラットフォームへ送信されます。 ユーザーが Web アプリケーションから完全にサインアウトするには、アプリがユーザーとのセッションを終了し (通常、トークン キャッシュをクリアするか Cookie を切断する)、ブラウザーを以下にリダイレクトする必要があります。

```https
https://login.microsoftonline.com/{tenant}/oauth2/v2.0/logout?post_logout_redirect_uri=https://localhost/myapp/
```

| パラメーター | タイプ | 内容 |
| --- | --- | --- |
| `tenant` | 必須 | 要求パスの `{tenant}` の値を使用して、アプリケーションにサインインできるユーザーを制御します。 使用できる値は、`common`、`organizations`、`consumers` およびテナント識別子です。 詳細については、 [プロトコルの基礎](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols#endpoints)に関するページを参照してください。 |
| `post_logout_redirect_uri` | 推奨 | サインアウト完了後にユーザーが戻る URL。 この値は、アプリケーションに登録されているリダイレクト URI のいずれかと一致する必要があります。 含まれていない場合、Microsoft ID プラットフォームにより汎用メッセージが表示されます。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/v2-oauth2-on-behalf-of-flow"} -->
## Microsoft ID プラットフォームと OAuth2.0 On-Behalf-Of フロー - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow
- Service: identity-platform
- Article date: 2025-01-04
- Summary: この記事では、HTTP メッセージを使用して、OAuth2.0 On-Behalf-Of フローを使用するサービス間の認証を実装する方法について説明します。

On-Behalf-Of (OBO) フローでは、独自の ID 以外の ID を使用して別の Web API を呼び出す Web API のシナリオについて説明します。 OAuth では委任と呼ばれ、この目的は、要求チェーンを介してユーザーの ID とアクセス許可を渡すことです。

中間層サービスでは、ダウンストリーム サービスに認証済み要求を発行するために、Microsoft ID プラットフォームからのアクセス トークンをセキュリティ保護する必要があります。 アプリケーション ロールでなく、委任されたスコープのみを使用します。*ロール* はプリンシパル (ユーザー) にアタッチされたままであり、ユーザーの代わりに動作するアプリケーションにはアタッチされません。 これは、アクセス許可を持つべきではないリソースに対し、ユーザーがアクセス許可を取得できないようにするために発生します。

この記事では、アプリケーション内のプロトコルに対して直接プログラムする方法について説明します。 可能であれば、サポートされている Microsoft 認証ライブラリ (MSAL) を使用して [トークンを取得し、セキュリティで保護された Web API を呼び出](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios#scenarios-and-supported-authentication-flows)すようにすることをお勧めします。 例については、 [MSAL を使用するサンプル アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/sample-v2-code) も参照してください。

### クライアントの制限事項

サービス プリンシパルがアプリ専用トークンを要求して API に送信した場合、その API は、元のサービス プリンシパルを表さないトークンを交換します。 これは、OBO フローはユーザー プリンシパルに対してのみ機能するためです。 代わりに、 [クライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow) を使用してアプリ専用トークンを取得する必要があります。 シングルページ アプリ (SPA) の場合、中間層の機密クライアントにアクセス トークンを渡して、OBO フローを代わりに実行する必要があります。

クライアントで暗黙的フローを使って id\_token を取得する場合、また、応答 URL にワイルドカードが含まれている場合には、id\_token を OBO フローで使用することはできません。 ワイルドカードは、`*` 文字で終わる URL です。 たとえば、`https://myapp.com/*` が応答 URL の場合、id\_token は、クライアントを識別するのに十分に明確でないため、使用できません。 これにより、トークンが発行されるのが回避されます。 ただし、暗黙的な許可フローを通じて取得されたアクセス トークンは、開始側クライアントにワイルドカード応答 URL が登録されている場合でも、機密クライアントによって引き換えられます。 これは、機密クライアントがアクセス トークンを取得したクライアントを識別できるためです。 その後、機密クライアントはアクセス トークンを使用して、ダウンストリーム API の新しいアクセス トークンを取得できます。

また、カスタム署名キーを持つアプリケーションは、OBO フローで中間層 API として使用することはできません。 これには、シングル サインオン用に構成されたエンタープライズ アプリケーションが含まれます。 中間層 API がカスタム署名キーを使用する場合、ダウンストリーム API は、それに渡されるアクセス トークンの署名を検証しません。 これにより、クライアントによって制御されるキーで署名されたトークンを安全に受け入れることができないため、エラーが発生します。

### プロトコル図

ユーザーが [OAuth 2.0 承認コード付与フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) または別のサインイン フローを使用してアプリケーションを認証したとします。 この時点で、アプリケーションは *API A* (トークン A) のアクセス トークンを持ち、ユーザーの要求と中間層 Web API (API A) へのアクセスに同意します。 ここで、API A はダウンストリーム Web API (API B) に認証済み要求を発行する必要があります。

OBO フローを構成するための以下の手順について、次の図を使用して説明します。

[Image: OAuth2.0 On-Behalf-Of フローを表示します]

1. クライアント アプリケーションは、トークン A (API A の `aud` 要求) を使用して API A に要求を発行します。
2. API A が Microsoft ID プラットフォーム トークン発行エンドポイントに対して認証を行い、API B にアクセスするためのトークンを要求します。
3. Microsoft ID プラットフォーム トークン発行エンドポイントはトークン A を使用して API A の資格情報を検証し、API B (トークン B) から API B へのアクセス トークンを発行します。
4. API A によって、API B への要求の承認ヘッダー内にトークン B が設定されます。
5. セキュリティで保護されたリソースからのデータが API B によって API A に返され、次に、クライアントに返されます。

このシナリオでは、中間層サービスに、ダウンストリーム API にアクセスするユーザーの同意を得るためのユーザー操作はありません。 そのため、ダウンストリーム API へのアクセス権を付与するオプションは、認証中の同意手順の一部として事前に提供されます。 アプリでこれを実装する方法については、「 中間層アプリケーションの同意を得る」を参照してください。

### 中間層アクセス トークン要求

アクセス トークンを要求するには、次のパラメーターを使用して、テナントに固有の Microsoft ID プラットフォーム トークン エンドポイントへの HTTP POST を作成します。

```
https://login.microsoftonline.com/<tenant>/oauth2/v2.0/token
```

警告

中間層に発行されたアクセス トークンは、トークンの対象ユーザーを除く任意の場所に送信**しないでください**。 中間層に発行されるアクセス トークンは、その中間層 *のみが* 目的の対象ユーザー エンドポイントと通信するために使用することを目的としています。

中間層リソースから、(アクセス トークン自体を取得するクライアントではなく)クライアントにアクセス トークンを中継する場合のセキュリティ上のリスクは次のとおりです。

- 侵害された SSL/TLS チャネル経由でのトークン傍受リスクの増加。
- 要求のステップアップ (MFA、サインイン頻度など) が必要なトークン バインディングや条件付きアクセスのシナリオを満たすことができない。
- 管理者が構成したデバイス ベースのポリシー (MDM、場所ベースのポリシーなど) との非互換性。

クライアント アプリケーションのセキュリティ保護に共有シークレットまたは証明書のどちらを使うかに応じて、2 つのケースがあります。

#### 最初のケース:共有シークレットを使ったアクセス トークン要求

共有シークレットを使用する場合、サービス間のアクセス トークン要求には、次のパラメーターが含まれてます。

| パラメーター | タイプ | 説明 |
| --- | --- | --- |
| `grant_type` | 必須 | トークン要求の種類。 JWT を使用した要求では、この値は `urn:ietf:params:oauth:grant-type:jwt-bearer` にする必要があります。 |
| `client_id` | 必須 | [Microsoft Entra 管理センターの \[アプリの登録](https://go.microsoft.com/fwlink/?linkid=2083908)] ページがアプリに割り当てられているアプリケーション (クライアント) ID。 |
| `client_secret` | 必須 | Microsoft Entra管理センター - アプリの登録 ページ でアプリ用に生成したクライアント シークレット。 代わりに、 [RFC 6749](https://datatracker.ietf.org/doc/html/rfc6749#section-2.3.1) に従って Authorization ヘッダーに資格情報を提供する基本的な認証パターンもサポートされています。 |
| `assertion` | 必須 | 中間層 API に送信されたアクセス トークン。 このトークンには、この OBO 要求を行うアプリ (`aud` フィールドに示されるアプリ) の対象ユーザー (`client-id`) 要求が必要です。 アプリケーションは別のアプリ用のトークンを利用することはできません (たとえば、クライアントが API に Microsoft Graph 用のトークンを送信した場合、API は OBO を使用してそれを利用することはできません。代わりに、トークンを拒否する必要があります)。 |
| `scope` | 必須 | トークン要求のスコープのスペース区切りリスト。 詳細については、 [スコープを](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)参照してください。 |
| `requested_token_use` | 必須 | 要求の処理方法を指定します。 OBO フローでは、この値は `on_behalf_of` に設定する必要があります。 |

##### 例

次の HTTP POST は、 `user.read` Web API に対する `https://graph.microsoft.com` スコープを含むアクセス トークンと更新トークンを要求します。 要求はクライアント シークレットで署名され、機密クライアントによって作成されます。

```HTTP
//line breaks for legibility only
    
POST /oauth2/v2.0/token HTTP/1.1
Host: login.microsoftonline.com/<tenant>
Content-Type: application/x-www-form-urlencoded
    
grant_type=urn:ietf:params:oauth:grant-type:jwt-bearer
&client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&client_secret=A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u
&assertion=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6InowMzl6ZHNGdWl6cEJmQlZLMVRuMjVRSFlPMCJ9.eyJhdWQiOiIyO{a lot of characters here}
&scope=https://graph.microsoft.com/user.read+offline_access
&requested_token_use=on_behalf_of
```

#### 2 番目のケース:証明書を使ったアクセス トークン要求

証明書を含むサービス間アクセス トークン要求には、前の例のパラメーターに加えて、次のパラメーターが含まれています。

| パラメーター | タイプ | 説明 |
| --- | --- | --- |
| `grant_type` | 必須 | トークン要求の種類。 JWT を使用した要求では、この値は `urn:ietf:params:oauth:grant-type:jwt-bearer` にする必要があります。 |
| `client_id` | 必須 | [Microsoft Entra 管理センターの \[アプリの登録](https://go.microsoft.com/fwlink/?linkid=2083908)] ページがアプリに割り当てられているアプリケーション (クライアント) ID。 |
| `client_assertion_type` | 必須 | 値は `urn:ietf:params:oauth:client-assertion-type:jwt-bearer` である必要があります。 |
| `client_assertion` | 必須 | 作成する必要があるアサーション (JSON Web トークン) です。このアサーションは、アプリケーションの資格情報として登録した証明書で署名する必要があります。 証明書を登録する方法とアサーションの形式については、 [証明書の資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials)を参照してください。 |
| `assertion` | 必須 | 中間層 API に送信されたアクセス トークン。 このトークンには、この OBO 要求を行うアプリ (`aud` フィールドに示されるアプリ) の対象ユーザー (`client-id`) 要求が必要です。 アプリケーションは別のアプリ用のトークンを利用することはできません (たとえば、クライアントが API に MS Graph 用のトークンを送信した場合、API は OBO を使用してそれを利用することはできません。代わりに、トークンを拒否する必要があります)。 |
| `requested_token_use` | 必須 | 要求の処理方法を指定します。 OBO フローでは、この値は `on_behalf_of` に設定する必要があります。 |
| `scope` | 必須 | トークン要求のスコープのスペース区切りリスト。 詳細については、 [スコープを](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)参照してください。 |

パラメーターは、共有シークレットによる要求の場合とほぼ同じであることに注意してください。ただし、 `client_secret` パラメーターは、 `client_assertion_type` と `client_assertion`の 2 つのパラメーターに置き換えられます。 `client_assertion_type` パラメーターが `urn:ietf:params:oauth:client-assertion-type:jwt-bearer` に設定され、`client_assertion` パラメーターが証明書の秘密キーで署名された JWT トークンに設定されます。

##### 例

次の HTTP POST は、証明書を使用して `user.read` Web API に対する `https://graph.microsoft.com` スコープを含むアクセス トークンを要求します。 要求はクライアント シークレットで署名され、機密クライアントによって作成されます。

```HTTP
// line breaks for legibility only
    
POST /oauth2/v2.0/token HTTP/1.1
Host: login.microsoftonline.com/<tenant>
Content-Type: application/x-www-form-urlencoded
    
grant_type=urn%3Aietf%3Aparams%3Aoauth%3Agrant-type%3Ajwt-bearer
&client_id=11112222-bbbb-3333-cccc-4444dddd5555
&client_assertion_type=urn%3Aietf%3Aparams%3Aoauth%3Aclient-assertion-type%3Ajwt-bearer
&client_assertion=eyJhbGciOiJSUzI1NiIsIng1dCI6Imd4OHRHeXN5amNScUtqRlBuZDdSRnd2d1pJMCJ9.eyJ{a lot of characters here}
&assertion=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6InowMzl6ZHNGdWl6cEJmQlZLMVRuMjVRSFlPMCIsImtpZCI6InowMzl6ZHNGdWl6cEJmQlZLMVRuMjVRSFlPMCJ9.eyJhdWQiO{a lot of characters here}
&requested_token_use=on_behalf_of
&scope=https://graph.microsoft.com/user.read+offline_access
```

### 中間層アクセス トークン応答

成功応答は、次のパラメーターを含む JSON OAuth 2.0 応答です。

| パラメーター | 説明 |
| --- | --- |
| `token_type` | トークンの種類の値を示します。 Microsoft ID プラットフォームでサポートされる種類は `Bearer` のみです。 ベアラー トークンの詳細については、「 [OAuth 2.0 Authorization Framework: Bearer Token Usage (RFC 6750)」](https://www.rfc-editor.org/rfc/rfc6750.txt)を参照してください。 |
| `scope` | トークンで付与されるアクセスのスコープ。 |
| `expires_in` | アクセス トークンが有効な時間の長さ (秒単位)。 |
| `access_token` | 要求されたアクセス トークン。 呼び出し元のサービスは、このトークンを使用して受信側のサービスへの認証を行うことができます。 |
| `refresh_token` | 要求されたアクセス トークンの更新トークン。 呼び出し元のサービスは、現在のアクセス トークンの期限が切れた後に、このトークンを使用して別のアクセス トークンを要求できます。 更新トークンは、`offline_access` スコープが要求された場合にのみ提供されます。 |

#### 成功応答の例

次の例に、 `https://graph.microsoft.com` Web API へのアクセス トークン要求に対する成功応答を示します。 応答にはアクセス トークンと更新トークンが含まれており、証明書の秘密キーで署名されます。

```JSON
{
    "token_type": "Bearer",
    "scope": "https://graph.microsoft.com/user.read",
    "expires_in": 3269,
    "ext_expires_in": 0,
    "access_token": "eyJhbGciO...",
    "refresh_token": "OAQABAAAAAABnfiG-mA6NTae7CdWW7QfdAALzDWjw6qSn4GUDfxWzJDZ6lk9qRw4An{a lot of characters here}"
}
```

このアクセス トークンは、Microsoft Graph 用に v1.0 でフォーマットされたトークンです。 これは、トークン形式はアクセスされる **リソース** に基づいており、要求に使用されるエンドポイントとは無関係であるためです。 Microsoft Graph は v1.0 トークンを受け入れるように設定されているため、クライアントが Microsoft Graph のトークンを要求すると、Microsoft ID プラットフォームによって v1.0 アクセス トークンが生成されます。 他のアプリは、v2.0 形式のトークン、v1.0 形式のトークン、または独自のまたは暗号化されたトークン形式が必要であることを示している可能性があります。 v1.0 と v2.0 のエンドポイントは両方とも、どちらの形式のトークンも出力できます。 これにより、クライアントがトークンを要求する方法や場所に関係なく、リソースは常に適切な形式のトークンを取得できます。

警告

この例のトークンをコードに含め、所有していない API のトークンの検証や読み取りを試みないでください。 Microsoft サービスのトークンでは、JWT として検証されない特殊な形式を使用できます。また、コンシューマー (Microsoft アカウント) ユーザー向けに暗号化することもできます。 トークンの読み取りは便利なデバッグおよび学習ツールですが、コード内でこれに依存したり、制御する API 用ではないトークンに関する詳細を想定したりしないでください。

#### エラー応答の例

ダウンストリーム API に条件付きアクセス ポリシー ( [多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)など) が設定されている場合、ダウンストリーム API のアクセス トークンを取得しようとすると、トークン エンドポイントからエラー応答が返されます。 クライアント アプリケーションが条件付きアクセス ポリシーを満たすためのユーザー操作を提供できるように、中間層サービスでこのエラーをクライアント アプリケーションに示す必要があります。

[このエラー](https://datatracker.ietf.org/doc/html/rfc6750#section-3.1)をクライアントに返すために、中間層サービスは HTTP 401 Unauthorized で応答し、エラーと要求チャレンジを含む WWW-Authenticate HTTP ヘッダーを使用して応答します。 クライアントは、このヘッダーを解析し、要求チャレンジが存在する場合はそれを提示し、トークン発行者から新しいトークンを取得する必要があります。 クライアントは、キャッシュされたアクセス トークンを使用して中間層サービスへのアクセスを再試行しないでください。

```JSON
{
    "error":"interaction_required",
    "error_description":"AADSTS50079: Due to a configuration change made by your administrator, or because you moved to a new location, you must enroll in multifactor authentication to access 'aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb'.\r\nTrace ID: 0000aaaa-11bb-cccc-dd22-eeeeee333333\r\nCorrelation ID: aaaa0000-bb11-2222-33cc-444444dddddd\r\nTimestamp: 2017-05-01 22:43:20Z",
    "error_codes":[50079],
    "timestamp":"2017-05-01 22:43:20Z",
    "trace_id":"0000aaaa-11bb-cccc-dd22-eeeeee333333",
    "correlation_id":"aaaa0000-bb11-2222-33cc-444444dddddd",
    "claims":"{\"access_token\":{\"polids\":{\"essential\":true,\"values\":[\"00aa00aa-bb11-cc22-dd33-44ee44ee44ee\"]}}}"
}
```

### アクセス トークンを使用して、セキュリティ保護されたリソースにアクセスする

中間層サービスは、以前に取得したトークンを使用して、 `Authorization` ヘッダーにトークンを設定することで、ダウンストリーム Web API に対して認証された要求を行うことができます。

#### 例

```HTTP
    GET /v1.0/me HTTP/1.1
    Host: graph.microsoft.com
    Authorization: Bearer eyJ0eXAiO ... 0X2tnSQLEANnSPHY0gKcgw
```

### OAuth2.0 OBO フローにより取得した SAML アサーション

一部の OAuth ベースの Web サービスは、非対話型フローで SAML アサーションを受け入れるその他の Web サービス API にアクセスする必要があります。 Microsoft Entra ID は、SAML ベースの Web サービスをターゲット リソースとして使用する On-Behalf-Of フローに応答して SAML アサーションを提供できます。

これは、OAuth 2.0 On-Behalf-Of フローに対する非標準の拡張機能であり、OAuth2 ベースのアプリケーションが SAML トークンを使用する Web サービス API エンドポイントにアクセスできるようにします。

ヒント

フロントエンド Web アプリケーションから SAML で保護された Web サービスを呼び出す場合、API を呼び出すだけで、ユーザーの既存のセッションで通常の対話型認証フローを開始できます。 サービス間の呼び出しでユーザー コンテキストを提供するために SAML トークンが必要なときのみ OBO フローを使用する必要があります。

#### 共有シークレットを持つ OBO 要求を使用して SAML トークンを取得する

サービス間の SAML アサーション要求には、次のパラメーターが含まれています。

| パラメーター | タイプ | 説明 |
| --- | --- | --- |
| grant\_type（グラントタイプ） | 必須 | トークン要求の種類。 JWT を使用する要求の場合、値は `urn:ietf:params:oauth:grant-type:jwt-bearer` である必要があります。 |
| 主張 | 必須 | 要求で使用されるアクセス トークンの値。 |
| クライアントID | 必須 | Microsoft Entra ID での登録時に呼び出し元のサービスに割り当てられるアプリ ID。 Microsoft Entra 管理センターでアプリ ID を見つけるには、 **Entra ID**&gt;**App 登録** に移動し、アプリケーション名を選択します。 |
| クライアントシークレット | 必須 | 呼び出し元のサービスに対して Microsoft Entra ID に登録されているキー。 この値は、登録時に注意する必要があります。 代わりに、 [RFC 6749](https://datatracker.ietf.org/doc/html/rfc6749#section-2.3.1) に従って Authorization ヘッダーに資格情報を提供する基本的な認証パターンもサポートされています。 |
| 範囲 | 必須 | トークン要求のスコープのスペース区切りリスト。 詳細については、 [スコープを](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)参照してください。 SAML 自体にはスコープの概念がありませんが、トークンを受信するターゲットの SAML アプリケーションを識別するために使用されます。 この OBO フローでは、スコープ値は常に、`/.default` が追加された SAML エンティティ ID である必要があります。 たとえば、SAML アプリケーションのエンティティ ID が `https://testapp.contoso.com` の場合、要求されたスコープは `https://testapp.contoso.com/.default` となります。 エンティティ ID の先頭が `https:` などの URI スキームではない場合、そのエンティティ ID には、Microsoft Entra によって `spn:` がプレフィックスとして付けられます。 その場合は、スコープ `spn:<EntityID>/.default` を要求する必要があります (たとえば、エンティティ ID が `spn:testapp/.default` の場合は `testapp`)。 ここで要求するスコープ値によって、SAML トークン内の結果の `Audience` 要素が決まります。これは、トークンを受信する SAML アプリケーションにとって重要である可能性があります。 |
| 要求されたトークン使用 | 必須 | 要求の処理方法を指定します。 On-Behalf-Of フローでは、値は `on_behalf_of` である必要があります。 |
| 要求されたトークンタイプ | 必須 | 要求するトークンの種類を指定します。 アクセス先のリソースの要件に応じて、値は `urn:ietf:params:oauth:token-type:saml2` または `urn:ietf:params:oauth:token-type:saml1` のいずれかです。 |

応答には、UTF8 と Base 64url でエンコードされた SAML トークンが含まれています。

- **OBO 呼び出しからソース化された SAML アサーションの SubjectConfirmationData**: ターゲット アプリケーションで `Recipient` に`SubjectConfirmationData`値が必要な場合は、リソース アプリケーション構成で最初の非ワイルドカード応答 URL として値を構成する必要があります。 既定の応答 URL は `Recipient` 値を決定するために使用されないため、最初の非ワイルドカード応答 URL が使用されるように、アプリケーション構成の応答 URL の順序を変更する必要がある場合があります。 詳細については、「 [応答 URL」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/reply-url)参照してください。
- **SubjectConfirmationData ノード**: ノードは SAML 応答の一部ではないため、 `InResponseTo` 属性を含めることはできません。 SAML トークンを受け取るアプリケーションは、`InResponseTo` 属性なしで SAML アサーションを受け入れることができる必要があります。
- **API のアクセス許可**: SAML アプリケーションの スコープのトークンを要求できるように、中間層アプリケーションに`/.default`を追加して SAML アプリケーションへのアクセスを許可する必要があります。
- **同意**: OAuth フローでユーザー データを含む SAML トークンを受け取るために同意する必要があります。 詳細については、「 中間層アプリケーションの同意を得る」を参照してください。

#### SAML アサーションの応答

| パラメーター | 説明 |
| --- | --- |
| トークンタイプ | トークンの種類の値を示します。 Microsoft Entra ID がサポートする唯一の型は **Bearer です**。 ベアラー トークンの詳細については、「 [OAuth 2.0 Authorization Framework: Bearer Token Usage (RFC 6750)」](https://www.rfc-editor.org/rfc/rfc6750.txt)を参照してください。 |
| 範囲 | トークンで付与されるアクセスのスコープ。 |
| 有効期限 | アクセス トークンが有効な時間の長さ (秒単位)。 |
| 有効期限 | アクセス トークンの有効期限が切れる時刻。 日付は、1970-01-01T0:0:0Z UTC から有効期限までの秒数として表されます。 この値は、キャッシュされたトークンの有効期間を調べるために使用されます。 |
| リソース | 受信側のサービスのアプリ ID URI (セキュリティ保護されたリソース)。 |
| アクセス トークン | SAML アサーションを返すパラメーター。 |
| リフレッシュ トークン (refresh\_token) | 更新トークン。 呼び出し元のサービスは、現在の SAML アサーションの期限が切れた後に、このトークンを使用して別のアクセス トークンを要求できます。 |

- token\_type:Bearer
- expires\_in:3296
- ext\_expires\_in:0
- expires\_on:1529627844
- 資源： `https://api.contoso.com`
- access\_token: &lt;SAML アサーション&gt;
- issued\_token\_type: urn:ietf:params:oauth:token-type:saml2
- refresh\_token: &lt;更新トークン&gt;

### 中間層アプリケーションの同意の取得

OBO フローの目標は、クライアント アプリから中間層アプリを呼び出すことができ、バックエンド リソースを呼び出すアクセス許可を中間層アプリに持たせるように、適切な同意を与えることです。 アプリケーションのアーキテクチャまたは使用に応じて、OBO フローが正常に実行されるように、次の点を考慮する必要があります。

#### .default と組み合わせ同意

中間層アプリケーションは、マニフェスト内の既知の [クライアント アプリケーション リスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest#knownclientapplications-attribute) (`knownClientApplications`) にクライアントを追加します。 同意プロンプトがクライアントによってトリガーされた場合、同意フローはそれ自体と中間層アプリケーションの両方に対して行われます。 Microsoft ID プラットフォームでは、[これは`.default` スコープ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc#the-default-scope)を使用して行われます。 `.default` スコープは、アプリケーションがアクセス許可を持つすべてのスコープにアクセスするための同意を要求するために使用される特殊なスコープです。 これは、アプリケーションが複数のリソースにアクセスする必要があるときに、ユーザーに 1 回だけ同意を求めるようにする場合に便利です。

既知のクライアント アプリケーションと `.default`を使用して同意画面をトリガーすると、同意画面に、中間層 API に対するクライアント **の両方** のアクセス許可が表示され、中間層 API で必要なすべてのアクセス許可も要求されます。 ユーザーが両方のアプリケーションに対して同意を行うと、OBO フローが動作します。

要求で識別されるリソース サービス (API) は、クライアント アプリケーションがユーザーのサインインの結果としてアクセス トークンを要求するための API である必要があります。 たとえば、`scope=openid https://middle-tier-api.example.com/.default` (中間層 API のアクセス トークンを要求するため)、または `scope=openid offline_access .default` (リソースが識別されない場合は既定で Microsoft Graph) です。

承認要求で識別される API に関係なく、同意プロンプトは、クライアント アプリ用に構成されたすべての必要なアクセス許可と組み合わされます。 クライアントを既知のクライアント アプリケーションとして識別した、クライアントの必要なアクセス許可の一覧に記載されている各中間層 API に対して構成されたすべての必要なアクセス許可も含まれます。

Von Bedeutung

`scope=openid https://resource/.default`を含む結合された同意フローでを使用することは有効ですが、同じ要求で、`.default`、`User.Read`、`Mail.Read`などの他の委任されたスコープと`profile`を組み合わせ`User.ReadWrite.All`。 これにより、`AADSTS70011`は事前に同意された静的アクセス許可を表し、他のユーザーは実行時に動的なユーザーの同意を必要とするため、`.default` エラーが発生します。

`offline_access` は、更新トークンを有効にするために `.default` で受け入れられる場合がありますが、他の委任されたスコープと組み合わせてはなりません。 不明な場合は、スコープ型の競合を回避するためにトークン要求を分割します。

#### 事前認証されたアプリケーション

特定のアプリケーションが特定のスコープを受け取る許可を常に持つことをリソースで示すことができます。 これは、フロントエンド クライアントとバックエンド リソース間の接続をよりシームレスに行う場合に役立ちます。 リソースは、マニフェストで [複数の事前認証されたアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest#preauthorizedapplications-attribute) (`preAuthorizedApplications`) を宣言できます。 このようなアプリケーションは、OBO フローでこれらのアクセス許可を要求し、ユーザーによる同意なしで、それらのアクセス許可を受け取ることができます。

#### 管理者の同意

テナント管理者は、中間層アプリケーションに対して管理者同意を提供することで、アプリケーションが必要な API を呼び出すためのアクセス許可を確実に持つようにすることができます。 これを行うために、管理者は、テナントで中間層アプリケーションを見つけ、必要なアクセス許可ページを開き、アプリに対してアクセス許可を付与することを選択できます。 管理者の同意の詳細については、 [同意とアクセス許可のドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)を参照してください。

#### 単一アプリケーションの使用

一部のシナリオでは、中間層とフロントエンド クライアントのペアリングが 1 つだけでした。 このシナリオでは、これを単一のアプリケーションにする方が簡単で、中間層アプリケーションの必要性を完全に否定できます。 フロントエンドと Web API 間で認証を行うために、アプリケーション自体に要求された cookie、id\_token、またはアクセス トークンを使用できます。 その後、この単一アプリケーションからバックエンド リソースへの同意を要求します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/v2-overview"} -->
## Microsoft ID プラットフォームの概要 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview
- Service: identity-platform
- Article date: 2024-03-20
- Summary: Microsoft ID プラットフォームのコンポーネントの概要と、それらを使用してアプリケーションに ID およびアクセス管理 (IAM) のサポートを組み込む方法について説明します。

Microsoft ID プラットフォームは、ユーザーや顧客が各自の Microsoft ID やソーシャル アカウントを使用してサインインできるアプリケーションを構築できるクラウド ID サービスです。 独自の API や、Microsoft Graph などの Microsoft API へのアクセスを承認します。 ID プラットフォームは、シングルテナント、基幹業務 (LOB) アプリケーション、およびマルチテナント サービスとしてのソフトウェア (SaaS) アプリケーションの構築をサポートする開発者をサポートします。

次の図は、アプリケーションの登録エクスペリエンス、SDK、エンドポイント、サポートされている ID やアカウント タイプを含めた、高レベルでの Microsoft ID プラットフォームを示しています。

[Image: Microsoft ID プラットフォームのコンポーネントを示す図。]

Microsoft ID プラットフォームは、次のようないくつかのコンポーネントで構成されています。

- 開発者が次のような種類の ID を認証できるようにする **OAuth 2.0 および OpenID Connect 標準準拠の認証サービス**

    - Microsoft Entra ID を通じてプロビジョニングされる職場または学校アカウント
    - 個人用 Microsoft アカウント (Skype、Xbox、Outlook.com)
    - ソーシャル アカウントまたはローカル アカウント (Azure AD B2C を使用)
    - Microsoft Entra 外部 ID を使用したソーシャルまたはローカルの顧客アカウント
- **オープンソース ライブラリ**: Microsoft Authentication Libraries (MSAL) と、その他の標準準拠ライブラリへのサポート 条件付きアクセスのシナリオの組み込みサポート、ユーザー向けのシングル サインオン (SSO) エクスペリエンス、組み込みのトークン キャッシュのサポートなどを提供するオープン ソースの MSAL ライブラリが推奨されます。 MSAL では、さまざまなアプリケーションの種類とシナリオで使用される、さまざまな認可許可とトークン フローがサポートされています。
- **Microsoft ID プラットフォーム エンドポイント** - Microsoft ID プラットフォーム エンドポイントは OIDC 認定です。 Microsoft Authentication Libraries (MSAL) またはその他の標準準拠ライブラリと連動します。 業界標準に準拠し、人間が読めるスコープを実装します。
- **アプリケーション管理ポータル**: Microsoft Entra 管理センターでの登録および構成エクスペリエンスと、その他のアプリケーション管理機能。
- **アプリケーション構成 API および PowerShell**:Microsoft Graph API および PowerShell を使用した、プログラムによるアプリケーションの構成。これにより、DevOps タスクを自動化できます。
- **開発者向けコンテンツ**: クイックスタート、チュートリアル、攻略ガイド、API リファレンス、コード サンプルなどの技術ドキュメント。

開発者は Microsoft ID プラットフォームにより、パスワードレス認証、ステップアップ認証、条件付きアクセスなど、ID およびセキュリティ領域における最新の革新的技術を統合できます。 このような機能を自分で実装する必要はありません。 Microsoft ID プラットフォームと統合されたアプリケーションは、このような革新的技術をネイティブに利用します。

Microsoft ID プラットフォームでは、一度コードを記述すればすべてのユーザーに対応できます。 1 回のビルドでアプリを多数のプラットフォームで動作させたり、クライアントとリソース アプリケーション (API) の両方として機能するアプリを構築したりできます。

### テナント構成

テナントは、登録されたアプリやユーザーのディレクトリなどの組織のリソースを含む、Microsoft Entra ID の専用の信頼できるインスタンスです。 Microsoft ID プラットフォームには、従業員と外部の 2 つの異なるテナント構成が用意されています。 選択するテナント構成は、アプリケーションで認証および承認するユーザーの種類によって異なります。

- **従業員**構成は、従業員、社内ビジネス アプリ、およびその他の組織リソースを対象としています。 外部のビジネス パートナーとゲストを従業員テナントに招待できますが、主な焦点は内部ユーザーです。 従業員テナントは、Microsoft Entra テナントの既定の構成です。
- **外部**構成は、組織の一部ではないコンシューマーまたはビジネス顧客にアプリを発行する外部 ID シナリオ専用に使用されます。 外部テナントを使用すると、顧客向けにカスタマイズされたサインインとサインアップ エクスペリエンスを作成し、ID を管理し、アプリへのアクセスを行うことができます。

従業員と外部テナントには、さまざまな機能と制限があります。 適切なテナント構成を選択すると、アプリケーションに適した ID およびアクセス管理ソリューションを構築するのに役立ちます。 両方の構成の機能の詳細な比較については、「 [従業員と外部テナントでサポートされる機能](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-supported-features-customers)」を参照してください。

### はじめに

希望する[アプリケーションのシナリオ](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios)を選択します。 これらの各シナリオのパスには、概要と、作業を開始するのに役立つクイックスタートへのリンクがあります。

ブラウザー ベースの認証 (Workforce テナントと外部テナント):

- ブラウザー ベースの認証を使用した [React シングルページ アプリ (SPA)。](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-react-sign-in)
- [ASP.NET Core Web アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-dotnet-core-sign-in)。
- [ASP.NET Core API](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-api-aspnet-core-protect-api)。
- [デスクトップ アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-app-configuration)。
- [デーモン アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-daemon-app-configuration)。
- 従業員テナント内の[モバイル アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-mobile-app-configuration)。

ネイティブ認証 (外部テナントのみ):

- [React シングルページ アプリ (SPA)](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-single-page-app-react-sign-in)
- [Android アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-android-sign-in)
- [iOS アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-ios-sign-in)
- [macOS アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-native-authentication-macos-sign-in)

Microsoft ID プラットフォームを使用してアプリケーションを構築する方法の詳細については、次のアプリケーションの「マルチパート チュートリアル シリーズ」を参照してください。

ブラウザー ベースの認証 (Workforce テナントと外部テナント):

- [React シングルページ アプリ (SPA)](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-react-prepare-app)
- [ASP.NET Core Web アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-dotnet-prepare-app)
- [ASP.NET Core API](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-api-dotnet-register-app)

ネイティブ認証 (外部テナントのみ):

- [React シングルページ アプリ (SPA)](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-single-page-app-react-sign-up)
- [Android Kotlin アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-android-app)
- [iOS/macOS スイッチ アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-native-authentication-prepare-ios-macos-app)

Microsoft IDプラットフォーム を使用してアプリに認証と承認を組み込む際に、ほとんどの一般的なアプリ シナリオとその ID コンポーネントの概要を示した次の図を参考にすることができます。 図を選択すると、フルサイズで表示されます。

[Image: Microsoft ID プラットフォームでのアプリケーション シナリオを示したメトロ マップ]

### 認証の概念について学習する

以下の一連の推奨される記事で、認証および Microsoft Entra の主要な概念が Microsoft ID プラットフォームにどのように適用されるかについて学習します。

- [認証の基本](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-vs-authorization)
- [アプリケーションとサービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)
- [対象ユーザー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-supported-account-types)
- [アクセス許可と同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)
- [ID トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)
- [アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)
- [認証フローとアプリケーションのシナリオ](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios)

### その他の ID およびアクセス管理のオプション

[Azure AD B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/overview) - ユーザーが自分のソーシャル アカウント (Facebook や Google など) を使用するかまたは電子メール アドレスとパスワードを使用してサインインできる、顧客向けアプリケーションをビルドします。 2025 年 5 月 1 日より、Azure AD B2C は新規のお客様向けに購入できなくなります。 詳細については、FAQ で [Azure AD B2C を引き続き購入できますか](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) ? を参照してください。

[従業員テナントの Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) - 外部ユーザーを "ゲスト" ユーザーとして Microsoft Entra テナントに招待し、認証に既存の資格情報を使用しながら承認のアクセス許可を割り当てます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/v2-protocols"} -->
## OAuth 2.0 および OpenID Connect プロトコル - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols
- Service: identity-platform
- Article date: 2026-06-23
- Summary: OAuth 2.0 と OpenID Connect については、Microsoft ID プラットフォームで学習します。 認証フロー、エンドポイント、安全なユーザー認証について調べます。

Microsoft ID プラットフォームを使用するために、プロトコル レベルで OAuth または OpenID Connect (OIDC) を学習する必要はありません。 ただし、ID プラットフォームを使ってアプリに認証を追加するときに、プロトコルの用語と概念が出現します。 Microsoft Entra 管理センター、Microsoft のドキュメント、認証ライブラリを使用して作業する際に、いくつかの基礎を理解することが、統合と全体的なエクスペリエンスの助けとなります。

### OAuth 2.0 でのロール

OAuth 2.0 と OpenID Connect での認証と承認の交換には、通常、4 つのパーティーが関与します。 このような交換は、多くの場合、"認証フロー" と呼ばれます。

[Image: OAuth 2.0 の役割 (承認サーバー、クライアント、リソース所有者、リソース サーバーなど) を示す図のスクリーンショット]

- **承認サーバー** - Microsoft ID プラットフォームが承認サーバーです。 *ID プロバイダー*または *IdP* とも呼ばれ、エンド ユーザーの情報、アクセス、および認証フロー内の関係者間の信頼関係を安全に処理します。 承認サーバーは、ユーザーがサインインした (認証された) 後に、リソースへのアクセスの許可、拒否、または取り消し (承認) のためにアプリと API が使用するセキュリティ トークンを発行します。
- **クライアント** - OAuth 交換のクライアントは、保護されたリソースへのアクセスを要求するアプリケーションです。 クライアントには、サーバーで実行されている Web アプリ、ユーザーの Web ブラウザーで実行されているシングルページ Web アプリ、または別の Web API を呼び出す Web API を使用できます。 多くの場合、クライアントは "クライアント アプリケーション"、"アプリケーション"、または "アプリ" と呼ばれます。
- **リソース所有者** - 認証フローのリソース所有者は、通常、アプリケーション ユーザー、または OAuth 用語の *エンド ユーザー* です。 エンド ユーザーは、アプリがユーザーの代わりにアクセスする保護されたリソース (ユーザーのデータ) を "所有" します。 リソース所有者は、所有するリソースへのアプリ (クライアント) によるアクセスを許可または拒否できます。 たとえば、アプリは、外部システムの API を呼び出して、そのシステム上のプロファイルからユーザーの電子メール アドレスを取得できます。 プロファイル データは、エンド ユーザーが外部システム上に所有しているリソースであり、エンド ユーザーは、アプリによるデータへのアクセス要求に同意または拒否できます。
- **リソース サーバー** - リソース サーバーは、リソース所有者のデータをホストまたはアクセスします。 ほとんどの場合、リソース サーバーは、データ ストアに対処する Web API です。 リソース サーバーは、承認サーバーを利用して、認証を実行します。また、承認サーバーによって発行されたベアラー トークン内の情報を使用して、リソースへのアクセスを許可または拒否します。

### トークン

認証フローの関係者は **、ベアラー トークン** を使用してプリンシパル (ユーザー、ホスト、またはサービス) を保証、検証、認証し、保護されたリソース (承認) へのアクセスを許可または拒否します。 Microsoft ID プラットフォームのベアラー トークンは、 [JSON Web トークン (JWT)](https://tools.ietf.org/html/rfc7519) として書式設定されます。

ID プラットフォームでは、次の 3 種類のベアラー トークンが "セキュリティ トークン" として使用されます。

- [アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens) - アクセス トークンは、承認サーバーによってクライアント アプリケーションに発行されます。 クライアントは、アクセス トークンをリソース サーバーに渡します。 アクセス トークンには、承認サーバーによってクライアントに付与されたアクセス許可が含まれます。
- [ID トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens) - ID トークンは、承認サーバーによってクライアント アプリケーションに発行されます。 クライアントは、ユーザーをサインインするときに、ID トークンを使用して、ユーザーに関する基本情報を取得します。
- [更新トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/refresh-tokens) - クライアントは、更新トークン ( *RT*) を使用して、承認サーバーに新しいアクセストークンと ID トークンを要求します。 更新トークンとその文字列コンテンツは、承認サーバーによる使用のみを目的としているため、コードでは機密データとして扱う必要があります。

### アプリの登録

Microsoft ID プラットフォームによってクライアント アプリに発行されたセキュリティ トークンを信頼する方法が必要です。 信頼を確立するための最初の手順は、 [アプリを登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)することです。 アプリを登録すると、ID プラットフォームは、それにいくつかの値を自動的に割り当てます。他の値は、アプリケーションの種類に基づいて手動で構成します。

最も一般的に参照されるアプリ登録設定は次の 2 つです。

- **アプリケーション (クライアント) ID** - *アプリケーション ID* と *クライアント ID* とも呼ばれ、この値は ID プラットフォームによってアプリに割り当てられます。 クライアント ID は、ID プラットフォーム内のアプリを一意に識別し、プラットフォームが発行するセキュリティ トークンに含まれます。
- **リダイレクト URI** - 承認サーバーは、リダイレクト URI を使用して、リソース所有者の *ユーザー エージェント* (Web ブラウザー、モバイル アプリ) を、対話の完了後に別の宛先にリダイレクトします。 たとえば、エンド ユーザーが承認サーバーで認証された後にです。 すべてのクライアントの種類でリダイレクト URI が使用されるとは限りません。

アプリの登録時には、ID とアクセス トークンを取得するためにコードで使用する認証と承認の "エンドポイント" に関する情報も保持されます。

### エンドポイント

Microsoft ID プラットフォームは、OAuth 2.0 と OpenID Connect (OIDC) 1.0 の標準に準拠した実装を使用して、認証と承認のサービスを提供します。 ID プラットフォームなどの標準準拠の承認サーバーは、フローを実行するために認証フロー内でパーティによって使用される HTTP エンドポイントのセットを提供します。

アプリのエンドポイント URI は、アプリを登録または構成するときに自動的に生成されます。 アプリのコードで使用するエンドポイントは、アプリケーションの種類と、それがサポートする必要がある ID (アカウントの種類) によって異なります。

一般的に使用される 2 つのエンドポイントは、 [承認エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#request-an-authorization-code) と [トークン エンドポイントです](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#redeem-a-code-for-an-access-token)。 `authorize` と `token` のエンドポイントの例をこちらに示します。

```
# Authorization endpoint - used by client to obtain authorization from the resource owner.
https://login.microsoftonline.com/<issuer>/oauth2/v2.0/authorize
# Token endpoint - used by client to exchange an authorization grant or refresh token for an access token.
https://login.microsoftonline.com/<issuer>/oauth2/v2.0/token

# NOTE: These are examples. Endpoint URI format may vary based on application type,
#       sign-in audience, and Azure cloud instance (global or national cloud).

#       The {issuer} value in the path of the request can be used to control who can sign into the application. 
#       The allowed values are **common** for both Microsoft accounts and work or school accounts, 
#       **organizations** for work or school accounts only, **consumers** for Microsoft accounts only, 
#       and **tenant identifiers** such as the tenant ID or domain name.
```

登録したアプリケーションのエンドポイントを検索するには、 [Microsoft Entra 管理センター](https://entra.microsoft.com) で次の場所に移動します。

**Entra ID**&gt;**アプリの登録**&gt;&lt;YOUR-APPLICATION&gt;&gt;**エンドポイント**

すべての OIDC エンドポイント (検出、承認、トークン、UserInfo、JWKS、ログアウト) の統合リファレンスについては、[Microsoft ID プラットフォームの OpenID Connect を](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/v2-protocols-oidc"} -->
## Microsoft ID プラットフォームでの OpenID Connect (OIDC) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc
- Service: identity-platform
- Article date: 2026-06-30
- Summary: Microsoft ID プラットフォームに実装されている OAuth 2.0 への OpenID Connect 拡張機能を使用して、Microsoft Entra ユーザーをサインインさせます。

OpenID Connect (OIDC) は、OAuth 2.0 の認可プロトコルを別の認証プロトコルとして使用できるように拡張したものです。 OIDC を使用し、"ID トークン" と呼ばれるセキュリティ トークンを使用することで、OAuth 対応アプリケーション間でシングル サインオン (SSO) を有効にすることができます。

ヒント

OIDC 動作を拡張するためにサポートされているすべての方法のマップについては、[OIDC 拡張機能のリファレンスMicrosoft ID プラットフォーム参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-oidc-extensibility)。

OIDC の完全な仕様は、OpenID [Connect Core 1.0 仕様の OpenID](https://openid.net/specs/openid-connect-core-1_0.html) Foundation の Web サイトで入手できます。

### OIDC エンドポイントの概要

Microsoft ID プラットフォームは、次の OpenID Connect エンドポイントを公開します。 すべてのエンドポイント (UserInfo を除く) は、テナント スコープの機関 `https://login.microsoftonline.com/{tenant}/v2.0`で提供されます。

| エンドポイント | URL のパス | Method | Purpose | 詳細 |
| --- | --- | --- | --- | --- |
| 発見 | `/.well-known/openid-configuration` | GET | エンドポイント URL、サポートされている要求、および署名キーのメタデータを含む OpenID プロバイダー構成ドキュメントを返します。 | OpenID 構成ドキュメントをフェッチする |
| 承認 | `/oauth2/v2.0/authorize` | GET | ユーザーを認証し、承認コード、ID トークン、またはその両方を返します。 | サインイン要求を送信する |
| トークン | `/oauth2/v2.0/token` | 投稿 | 認証コード、更新トークン、またはクライアント資格情報をトークンに使用します。 | [OAuth 2.0 認証コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) |
| UserInfo | `https://graph.microsoft.com/oidc/userinfo` | GET | 認証されたユーザーに関する要求 ( `openid`、 `profile`、 `email`でスコープ指定) を返します。 | [UserInfo エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity-platform/userinfo) |
| JWKS | `/discovery/v2.0/keys` | GET | トークン署名検証の公開署名キーを返します。 | ID トークンの検証、 [署名キーのロールオーバー](https://learn.microsoft.com/ja-jp/entra/identity-platform/signing-key-rollover) |
| ログアウト | `/oauth2/v2.0/logout` | GET、POST | ユーザーのセッションを終了し、フロント チャネルログアウトをトリガーします。 | サインアウト要求を送信する |

OIDC 動作を拡張するためにサポートされているすべての方法 (カスタム要求、トークン発行イベント、フェデレーション資格情報) のマップについては、 [OIDC 拡張機能のリファレンスを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-oidc-extensibility)。

### プロトコル フロー: サインイン

以下の図は、OpenID Connect の基本的なサインイン フローを示しています。 フローの手順については、記事の後半のセクションで詳しく説明します。

[Image: OpenID Connect 承認フローを示すスイムレーン図。]

### ID トークンを有効にする

OpenID Connect によって導入された "ID トークン" は、クライアント アプリケーションがユーザー認証中に ID トークンを要求したときに、承認サーバー (Microsoft ID プラットフォーム) によって発行されます。 ID トークンによって、クライアント アプリケーションはユーザーが本人であることを確認でき、そのユーザーに関するその他の情報 (要求) を取得できます。

ID トークンは、Microsoft ID プラットフォームに登録されているアプリケーションに対して既定では発行されません。 アプリケーションの ID トークンを有効にするには、次のいずれかの方法を使用します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**アプリの登録**&gt;&lt;*アプリケーション&gt;*&gt;**認証**に移動します。
3. [ **プラットフォームの構成**] で、[ **プラットフォームの追加]** を選択します。
4. ウィンドウが開いたら、アプリケーションに最適なプラットフォームを選択します。 たとえば、Web アプリケーションの **Web** を選択します。
5. [リダイレクト URI] で、アプリケーションのリダイレクト URI を追加します。 たとえば、`https://localhost:8080/` のようにします。
6. [ **暗黙的な許可とハイブリッド フロー**] で、 **ID トークン (暗黙的およびハイブリッド フローに使用)** チェック ボックスをオンにします。

または、Microsoft Graph アプリ マニフェストで次の手順を実行します。

1. **[Entra ID**&gt;**アプリの登録**&gt;*&lt;アプリケーション&gt;*&gt;**Manifest** を選択します。
2. `web`属性の`implicitGrantSettings` プロパティ内で`true`するように`enableIdTokenIssuance`を設定します。

アプリに対して ID トークンが有効になっていない場合に、ID トークンが要求されると、Microsoft ID プラットフォームは次のような `unsupported_response` エラーを返します。

>
> *The provided value for the input parameter 'response\_type' isn't allowed for this client. (入力パラメーター 'response\_type' に入力された値はこのクライアントで許可されません。) Expected value is 'code' (入力パラメーター 'response\_type' に入力された値はこのクライアントで許可されません。入力できる値は 'code' です。) が返されます*。

`response_type`の`id_token`を指定して ID トークンを要求する方法については、後の記事の「サインイン要求を送信する」で説明します。

### OpenID 構成ドキュメントを取得する

Microsoft ID プラットフォームなどの OpenID プロバイダーは、プロバイダーの OIDC エンドポイント、サポートされている要求、およびその他のメタデータを含むパブリックにアクセス可能なエンドポイントで [OpenID プロバイダー構成ドキュメント](https://openid.net/specs/openid-connect-discovery-1_0.html) を提供します。 クライアント アプリケーションは、メタデータを使用して、認証に使用する URL や認証サービスの公開署名キーを検出できます。

認証ライブラリは、認証 URL、プロバイダーの公開署名キー、その他のサービス メタデータの検出に使用される OpenID 構成ドキュメントの最も一般的なコンシューマーです。 アプリで認証ライブラリを使用する場合、OpenID 構成ドキュメント エンドポイントに対する要求と応答を手動でコーディングする必要はほとんどの場合ありません。

#### アプリの OpenID 構成ドキュメント URI を見つける

Microsoft Entra ID でのすべてのアプリ登録で、OpenID 構成ドキュメントを提供するパブリック アクセス可能なエンドポイントが提供されます。 アプリの構成ドキュメントのエンドポイントの URI を確認するには、"既知の OpenID 構成" パスをアプリ登録の "機関 URL" に追加します。

- 既知の構成ドキュメント パス: `/.well-known/openid-configuration`
- 機関 URL: `https://login.microsoftonline.com/{tenant}/v2.0`

`{tenant}` の値は、次の表に示されているように、アプリケーションのサインイン対象ユーザーによって異なります。 機関 URL は [、クラウド インスタンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud#azure-ad-authentication-endpoints)によっても異なります。

| 値 | 説明 |
| --- | --- |
| `common` | 個人の Microsoft アカウントを持つユーザーと Microsoft Entra ID の職場または学校アカウントを持つユーザーのどちらもアプリケーションにサインインできます。 |
| `organizations` | Microsoft Entra ID の職場/学校アカウントを持つユーザーのみがアプリケーションにサインインできます。 |
| `consumers` | 個人の Microsoft アカウントを持つユーザーのみがアプリケーションにサインインできます。 |
| `Directory (tenant) ID` または `contoso.onmicrosoft.com` | 特定の Microsoft Entra テナントのユーザー (職場または学校のアカウントを持つディレクトリ メンバー、または個人の Microsoft アカウントを持つディレクトリ ゲスト) のみ、アプリケーションにサインインできます。 値として、Microsoft Entra テナントのドメイン名、または GUID 形式のテナント ID を指定できます。 |

ヒント

個人の Microsoft アカウントに対して `common` または `consumers` 機関を使用する場合は、 [signInAudience](https://learn.microsoft.com/ja-jp/entra/identity-platform/supported-accounts-validation) に従ってこのような種類のアカウントをサポートするように消費リソース アプリケーションを構成する必要があることに注意してください。

Microsoft Entra 管理センターで OIDC 構成ドキュメントを見つけるには、 [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインし、次の手順を実行します。

1. **Entra ID**&gt;**アプリの登録**&gt;*&lt;your application&gt;*&gt;**Endpoints** に移動します。
2. **OpenID Connect メタデータ ドキュメント**で URI を見つけます。

#### 要求のサンプル

以下の要求では、Azure パブリック クラウド上の `common` 機関の OpenID 構成ドキュメント エンドポイントから OpenID 構成メタデータが取得されます。

```https
GET /common/v2.0/.well-known/openid-configuration
Host: login.microsoftonline.com
```

ヒント

試してみる アプリケーションの `common` 機関の OpenID 構成ドキュメントを確認するには、https://login.microsoftonline.com/common/v2.0/.well-known/openid-configuration に移動します。

#### 応答のサンプル

構成メタデータは、次の例に示すように JSON 形式で返されます (簡潔にするために一部省略しています)。 JSON 応答で返されるメタデータについては、 [OpenID Connect 1.0 の検出仕様](https://openid.net/specs/openid-connect-discovery-1_0.html#rfc.section.4.2)で詳しく説明されています。

```JSON
{
  "authorization_endpoint": "https://login.microsoftonline.com/{tenant}/oauth2/v2.0/authorize",
  "token_endpoint": "https://login.microsoftonline.com/{tenant}/oauth2/v2.0/token",
  "token_endpoint_auth_methods_supported": [
    "client_secret_post",
    "private_key_jwt"
  ],
  "jwks_uri": "https://login.microsoftonline.com/{tenant}/discovery/v2.0/keys",
  "userinfo_endpoint": "https://graph.microsoft.com/oidc/userinfo",
  "subject_types_supported": [
      "pairwise"
  ],
  ...
}
```

### サインイン要求を送信する

ユーザーを認証し、アプリケーションで使用する ID トークンを要求するには、ユーザー エージェントを Microsoft ID プラットフォームの */authorize* エンドポイントに転送します。 要求は [OAuth 2.0 承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) の最初の区間に似ていますが、次の違いがあります。

- `openid` パラメーターに `scope` スコープを含める。
- `id_token` パラメーターで `response_type` を指定する。
- `nonce` パラメーターを含める。

サインイン要求の例 (読みやすくするために改行しています):

```https
GET https://login.microsoftonline.com/{tenant}/oauth2/v2.0/authorize?
client_id=00001111-aaaa-2222-bbbb-3333cccc4444
&response_type=id_token
&redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F
&response_mode=form_post
&scope=openid
&state=12345
&nonce=678910
```

| パラメーター | 条件 | 説明 |
| --- | --- | --- |
| `tenant` | 必須 | 要求パスの `{tenant}` の値を使用して、アプリケーションにサインインできるユーザーを制御できます。 使用できる値は、`common`、`organizations`、`consumers` およびテナント識別子です。 詳細については、プロトコルの [基本](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols#endpoints)を参照してください。 重要なことに、あるテナントから別のテナントにユーザーを署名するゲスト シナリオでは、リソース テナントに正しくサインインするためにテナント識別子を指定する *必要があります* 。 |
| `client_id` | 必須 | **Microsoft Entra 管理センター – アプリ**に割り当てられたアプリ登録エクスペリエンスのアプリケーション [(クライアント) ID](https://go.microsoft.com/fwlink/?linkid=2083908)。 |
| `response_type` | 必須 | OpenID Connect サインインでは、 `id_token` を指定する必要があります。 |
| `redirect_uri` | 推奨 | アプリのリダイレクト URI。アプリは、この URI で認証応答を送受信することができます。 ポータルで登録したいずれかのリダイレクト URI と完全に一致させる必要があります (ただし、URL エンコードが必要)。 存在しない場合、エンドポイントでは登録されている `redirect_uri` がランダムに 1 つ選択され、ユーザーはそこに戻されます。 |
| `scope` | 必須 | スコープのスペース区切りリスト。 OpenID Connect には、同意 UI の`openid`アクセス許可に変換されるスコープを含める必要があります。 同意を求めるこの要求には他のスコープが含まれていてもかまいません。 |
| `nonce` | 必須 | ID トークンの要求でアプリによって生成され送信される値。 Microsoft ID プラットフォームによってアプリに返される ID トークンにも、同じ `nonce` 値が含まれます。 トークン再生攻撃を軽減するために、ID トークン内の `nonce` 値がトークンの要求時に送信された値と同じであることをアプリで確認する必要があります。 この値は、通常、ランダムな一意の文字列です。 |
| `response_mode` | 推奨 | 結果として得られた承認コードをアプリに返す際に使用するメソッドを指定します。 `form_post` または `fragment` を指定できます。 Web アプリケーションでは、トークンをアプリケーションに最も安全に転送できるように、`response_mode=form_post` を使用することをお勧めします。  信頼性のために `form_post` の使用も推奨されます。 `fragment`を使用すると、URL に応答が返されます。URL には 2,048 文字の長さの制限が適用されます。 トークン ペイロードがこの制限を超えると、応答が切り捨てられ、認証エラーが発生する可能性があります。 `form_post`を使用すると、URL ではなく HTTP 要求本文でトークンが送信されるため、この制限は回避されます。 |
| `state` | 推奨 | 要求に含まれ、トークンの応答としても返される値。 任意のコンテンツの文字列を指定することができます。 ランダムに生成された一意の値は、通常、 [クロスサイト リクエスト フォージェリ攻撃を防ぐために使用されます](https://tools.ietf.org/html/rfc6749#section-10.12)。 この状態は、認証要求の前にアプリ内でユーザーの状態 (表示中のページやビューなど) に関する情報をエンコードする目的にも使用されます。 |
| `prompt` | 省略可能 | ユーザーとの必要な対話の種類を指定します。 現時点で有効な値は、`login`、`none`、`consent`、`select_account` のみです。 `prompt=login` 要求は、その要求においてユーザーに資格情報の入力を強制させ、シングル サインオンを無効にします。 `prompt=none` パラメーターは反対のものであり、 ユーザーがサインインする必要があることを示すために、`login_hint` と組み合わせて使用する必要があります。 これらのパラメーターは、ユーザーに対して対話形式のプロンプトをいっさい表示しません。 シングル サインオンで確認なしで要求を完了できない場合は、Microsoft ID プラットフォームからエラーが返されます。 原因は、サインインしているユーザーがいない、ヒントを表示するユーザーがサインインしていない、複数のユーザーがサインインしている一方で、ヒントが提供されなかった、などです。 `prompt=consent` 要求は、ユーザーがサインインした後に OAuth 同意ダイアログをトリガーします。 ダイアログでは、ユーザーにアプリへのアクセス許可を付与するよう要求します。 最後に、`select_account` がユーザーにアカウント セレクターを表示し、シングル サインアウトを無効にします。ただし、ユーザーは資格情報の入力を必要とせずに、サインインする意図のあるアカウントを選択できます。 `login_hint` と `select_account` はどちらも使用できません。 |
| `login_hint` | 省略可能 | このパラメーターを使用すると、ユーザー名が事前にわかっている場合、ユーザーに代わって、サインイン ページのユーザー名とメール アドレスのフィールドに事前に入力することができます。 多くの場合、アプリは、以前のサインインから `login_hint`[optional 要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims) を既に抽出した後、再認証中にこのパラメーターを使用します。 |
| `domain_hint` | 省略可能 | フェデレーション ディレクトリ内のユーザーの領域。 これで、サインイン ページでユーザーが行う電子メール ベースの検出プロセスがスキップされ、ユーザー エクスペリエンスは若干簡素化されたものになります。 AD FS のようなオンプレミス ディレクトリを介してフェデレーションされているテナントの場合、この結果、既存のログイン セッションがあるためにシームレスなサインインになることがよくあります。 |

現時点では、ユーザーに資格情報の入力と認証が求められます。 Microsoft ID プラットフォームではまた、ユーザーが `scope` クエリ パラメーターに示されたアクセス許可に同意していることが確認されます。 いずれのアクセス許可にもユーザーが同意しなかった場合、Microsoft ID プラットフォームに、必要なアクセス許可に同意するようユーザーに求めるプロンプトが表示されます。 [アクセス許可、同意、マルチテナント アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)の詳細を確認できます。

ユーザーが認証され、同意すると、Microsoft ID プラットフォームは `response_mode` パラメーターで指定されたメソッドを使用して、指定されたリダイレクト URI でアプリに応答を返します。

#### 成功応答

`response_mode=form_post` を使用した場合、成功応答は次のようになります。

```https
POST /myapp/ HTTP/1.1
Host: localhost
Content-Type: application/x-www-form-urlencoded

id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik1uQ19WWmNB...&state=12345
```

| パラメーター | 説明 |
| --- | --- |
| `id_token` | アプリが要求した ID トークン。 `id_token` パラメーターを使用してユーザーの本人性を確認し、そのユーザーとのセッションを開始することができます。 ID トークンとその内容の詳細については、 [ID トークンのリファレンスを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-token-claims-reference)。 |
| `state` | 要求に `state` パラメーターが含まれている場合、同じ値が応答にも含まれることになります。 要求と応答に含まれる状態値が同一であることをアプリ側で確認する必要があります。 |

#### エラー応答

アプリ側でエラーを処理できるように、リダイレクト URI にエラー応答が送信される場合もあります。次に例を示します。

```https
POST /myapp/ HTTP/1.1
Host: localhost
Content-Type: application/x-www-form-urlencoded

error=access_denied&error_description=the+user+canceled+the+authentication
```

| パラメーター | 説明 |
| --- | --- |
| `error` | 発生したエラーの種類を分類したりエラーに対処したりする際に使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの根本的な原因を特定しやすいように記述した具体的なエラー メッセージ。 |

#### 承認エンドポイント エラーのエラー コード

次の表で、エラー応答の `error` パラメーターで返される可能性のあるエラー コードを説明します。

| エラー コード | 説明 | クライアント側の処理 |
| --- | --- | --- |
| `invalid_request` | 必要なパラメーターが不足しているなどのプロトコル エラーです。 | 要求を修正し再送信します。 この開発エラーは、アプリケーションのテスト時に見つける必要があります。 |
| `unauthorized_client` | クライアント アプリケーションは、承認コードを要求できません。 | このエラーは、クライアント アプリケーションが Microsoft Entra ID に登録されていない、またはユーザーの Microsoft Entra テナントに追加されていないときに発生する可能性があります。 アプリケーションでは、アプリケーションのインストールと Microsoft Entra ID への追加を求める指示をユーザーに表示できます。 |
| `access_denied` | リソースの所有者が同意を拒否しました。 | クライアント アプリケーションは、ユーザーが同意しないと続行できないことを、ユーザーに通知できます。 |
| `unsupported_response_type` | 承認サーバーでは、要求に含まれる応答の種類がサポートされていません。 | 要求を修正し再送信します。 この開発エラーは、アプリケーションのテスト時に見つける必要があります。 |
| `server_error` | サーバーで予期しないエラーが発生しました。 | 要求をやり直してください。 これらのエラーは一時的な状況によって発生します。 クライアント アプリケーションは、一時的なエラーが原因で応答が遅れることをユーザーに説明する場合があります。 |
| `temporarily_unavailable` | サーバーが一時的にビジー状態であるため、要求を処理できません。 | 要求をやり直してください。 クライアント アプリケーションは、一時的な状況が原因で応答が遅れることをユーザーに説明する場合があります。 |
| `invalid_resource` | ターゲット リソースは、存在しない、Microsoft Entra ID で見つけられない、または正しく構成されていないために無効です。 | このエラーは、リソース (存在する場合) がテナントで構成されていないことを示します。 アプリケーションでは、アプリケーションのインストールと Microsoft Entra ID への追加を求める指示をユーザーに表示できます。 |

### ID トークンの検証

アプリで ID トークンを受け取るだけでは、ユーザーを完全に認証できない場合があります。 場合により、アプリの要件に従って、ID トークンの署名を検証し、トークンの要求を確認する必要もあります。 すべての OpenID プロバイダーと同様に、Microsoft ID プラットフォームの ID トークンは、公開キー暗号化を使用して署名された [JSON Web トークン (JWT)](https://tools.ietf.org/html/rfc7519) です。

承認に ID トークンを使用する Web アプリや Web API はデータにアクセスできるため、ID トークンを検証する必要があります。 ただし、その他の種類のアプリケーションでは、ID トークンの検証からメリットを得られない場合があります。 たとえば、ネイティブ アプリケーションとシングルページ アプリケーション (SPA) では、ID トークンの検証からメリットをほとんど得ることができません。これは、デバイスやブラウザーに物理的にアクセスするエンティティが、検証をバイパスする可能性があるためです。

トークン検証がバイパスされる 2 つの例を次に示します。

- デバイスへのネットワーク トラフィックを変更することで偽のトークンまたはキーを提供する
- アプリケーションをデバッグし、プログラムの実行中に検証ロジックをステップオーバーする。

アプリケーションで ID トークンを検証する場合、手動では "行わない" ことをお勧めします。 代わりに、トークン検証ライブラリを使用してトークンを解析および検証します。 トークン検証ライブラリは、ほとんどの開発言語、フレームワーク、プラットフォームで使用できます。

#### ID トークンで検証する内容

ID トークンの署名の検証に加えて、「ID トークンの検証」の説明に従って、いくつかの要求 [を検証する](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens#validate-tokens)必要があります。 キー [ロールオーバーの署名に関する重要な情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/signing-key-rollover)も参照してください。

その他のいくつかの検証は一般的であり、次のようなアプリケーション シナリオによって異なります。

- ユーザー/組織がアプリにサインアップ済みであることを確認する。
- 適切な承認/特権がユーザーにあることを確認する。
- [多要素](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)認証など、一定の強度の認証が行われたことを確認します。

ID トークンを検証したら、ユーザーとのセッションを開始し、アプリの個人用設定、表示、またはデータの保存のために、トークンの要求内の情報を使用できます。

### プロトコルのダイアグラム: アクセス トークンの取得

多くのアプリケーションでは、ユーザーをサインインさせるだけでなく、ユーザーの代わりに Web API などの保護されたリソースにアクセスする必要があります。 このシナリオでは、OpenID Connect でユーザーを認証するための ID トークンを取得することと、OAuth 2.0 で保護されたリソースのアクセス トークンを取得することを組み合わせます。

OpenID Connect によるサインインとトークン取得の完全なフローは、次の図のようになります。

[Image: OpenID Connect プロトコル: トークンの取得]

### UserInfo エンドポイントのアクセス トークンを取得する

認証されたユーザーの情報は、ID トークンに加えて OIDC [UserInfo エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity-platform/userinfo)でも使用できます。

OIDC の UserInfo エンドポイントのアクセス トークンを取得するには、次に示されているようにサインイン要求を変更します。

```https
// Line breaks are for legibility only.

GET https://login.microsoftonline.com/{tenant}/oauth2/v2.0/authorize?
client_id=00001111-aaaa-2222-bbbb-3333cccc4444        // Your app registration's Application (client) ID
&response_type=id_token%20token                       // Requests both an ID token and access token
&redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F       // Your application's redirect URI (URL-encoded)
&response_mode=form_post                              // 'form_post' (recommended) or 'fragment'. form_post avoids URL length limits.
&scope=openid+profile+email                           // 'openid' is required; 'profile' and 'email' provide information in the UserInfo endpoint as they do in an ID token. 
&state=12345                                          // Any value - provided by your app
&nonce=678910                                         // Any value - provided by your app
```

の代わりに[、承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code)、[デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#refresh-the-access-token)、または`response_type=token`を使用して、アプリのアクセス トークンを取得できます。

#### 正常なトークン応答

`response_mode=form_post` を使用した場合の正常な応答を次に示します。

```https
POST /myapp/ HTTP/1.1
Host: localhost
Content-Type: application/x-www-form-urlencoded
 access_token=eyJ0eXAiOiJKV1QiLCJub25jZSI6I....
 &token_type=Bearer
 &expires_in=3598
 &scope=email+openid+profile
 &id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI....
 &state=12345
```

応答パラメーターは、その取得に使用されたフローに関係なく、同じことを意味します。

| パラメーター | 説明 |
| --- | --- |
| `access_token` | UserInfo エンドポイントを呼び出すために使用されるトークン。 |
| `token_type` | 常に "Bearer" です |
| `expires_in` | アクセス トークンの有効期限が切れるまでの時間 (秒単位)。 |
| `scope` | アクセス トークンに付与されるアクセス許可。 UserInfo エンドポイントは Microsoft Graph でホストされるため、`scope` には、アプリケーションに以前付与された他のもの (たとえば、`User.Read`) を含めることができます。 |
| `id_token` | アプリが要求した ID トークン。 この ID トークンを使用してユーザーの本人性を確認し、そのユーザーとのセッションを開始することができます。 ID トークンとその内容の詳細については、 [ID トークンリファレンスを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-token-claims-reference)。 |
| `state` | 要求に state パラメーターが含まれている場合、同じ値が応答にも含まれることになります。 要求と応答に含まれる状態値が同一であることをアプリ側で確認する必要があります。 |

セキュリティとプライバシーのために、状態パラメーターに URL やその他の機密データを直接配置しないでください。 代わりに、localStorage や sessionStorage などのブラウザー ストレージに格納されているデータに対応するキーまたは識別子を使用します。 この方法により、アプリは認証後に必要なデータを安全に参照できます。

警告

この例のトークンを含めて、自分が所有していないすべての API について、トークンの検証や読み取りを行わないでください。 Microsoft サービスのトークンには、JWT として検証されない特殊な形式を使用できます。また、コンシューマー (Microsoft アカウント) ユーザーに対して暗号化される場合もあります。 トークンの読み取りは便利なデバッグおよび学習ツールですが、コード内でこれに対する依存関係を取得したり、自分で制御する API 用ではないトークンについての詳細を想定したりしないでください。

#### エラー応答

アプリ側でエラーを適切に処理できるように、リダイレクト URI にエラー応答が送信される場合もあります。

```https
POST /myapp/ HTTP/1.1
Host: localhost
Content-Type: application/x-www-form-urlencoded

error=access_denied&error_description=the+user+canceled+the+authentication
```

| パラメーター | 説明 |
| --- | --- |
| `error` | 発生したエラーの種類を分類したりエラーに対処したりする際に使用できるエラー コード文字列。 |
| `error_description` | 認証エラーの根本的な原因を特定しやすいように記述した具体的なエラー メッセージ。 |

考えられるエラー コードと推奨されるクライアント応答の説明については、 承認エンドポイント エラーのエラー コードを参照してください。

承認コードと ID トークンがある場合は、ユーザーをサインインさせ、代わりにアクセス トークンを取得できます。 ユーザーをサインインさせるには、検証トークンの説明に従って ID [トークンを検証する](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens#validate-tokens)必要があります。 アクセス トークンを取得するには、 [OAuth コード フローのドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#redeem-a-code-for-an-access-token)で説明されている手順に従います。

#### UserInfo エンドポイントを呼び出す

このトークンを使用して UserInfo エンドポイントを呼び出す方法については、 [UserInfo のドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/userinfo#calling-the-api) を参照してください。

### サインアウト要求を送信する

ユーザーをサインアウトするには、次の両方の操作を実行します。

1. ユーザーのユーザー エージェントを Microsoft ID プラットフォームのログアウト URI にリダイレクトします。
2. アプリの Cookie をクリアするか、アプリケーションでユーザーのセッションを終了します。

これらの操作のいずれかを実行しなかった場合、ユーザーは認証されたままになり、次回アプリを使用するときにサインインするように求められない場合があります。

OpenID Connect 構成ドキュメントに示されているように、ユーザー エージェントを `end_session_endpoint` にリダイレクトします。 `end_session_endpoint` は、HTTP の GET 要求と POST 要求の両方をサポートしています。

```https
GET https://login.microsoftonline.com/common/oauth2/v2.0/logout?
post_logout_redirect_uri=http%3A%2F%2Flocalhost%2Fmyapp%2F
```

| パラメーター | 条件 | 説明 |
| --- | --- | --- |
| `post_logout_redirect_uri` | 推奨 | サインアウトの正常終了後にユーザーをリダイレクトする URL。このパラメーターを含めない場合、Microsoft ID プラットフォームによって生成された汎用メッセージがユーザーに表示されます。 この URL は、アプリ登録ポータルのアプリケーションに対する登録済みリダイレクト URI のいずれかと一致させる必要があります。 |
| `logout_hint` | 省略可能 | ユーザーにアカウントの選択を求めるプロンプトを出さずに、サインアウトを実行できるようにします。 `logout_hint` を使用するには、クライアント アプリケーションで`login_hint` を有効にし、`login_hint` パラメーターとしてオプションのクレーム `logout_hint` の値を使用します。 `logout_hint` パラメーターの値として UPN や電話番号は使用しないでください。 |

注

サインアウトが成功すると、アクティブなセッションは非アクティブに設定されます。 サインアウトしたユーザーに対する有効なプライマリ更新トークン (PRT) が存在している場合に、新しいサインインが実行されると、シングル サインアウトは中断され、アカウント ピッカーを含むプロンプトがユーザーに表示されます。 PRT を参照する接続されているアカウントのオプションを選んだ場合、新しい資格情報を挿入しなくてもサインインは自動的に続けられます。

### シングル サインアウト

ユーザーをアプリケーションの `end_session_endpoint` にリダイレクトすると、Microsoft ID プラットフォームはこのアプリケーションのユーザー セッションを終了します。 ただし、ユーザーは認証に同じ Microsoft アカウントを使用する他のアプリケーションにサインインしたままになることがあります。

ユーザーがこのディレクトリ (テナントとも呼ばれます) に登録されている複数の Web または SPA アプリケーションにサインインしている場合、このユーザーがアプリケーションのいずれかでサインアウトすると、シングル サインアウト により、すべてのアプリケーションから即時にサインアウトできます。

Entra アプリケーションのシングル サインアウトを有効にするには、OpenID Connect フロント チャネル ログアウト機能を使用する必要があります。 この機能を使用すると、アプリケーションはユーザーがログアウトしたことを他のアプリケーションに通知できます。ユーザーが 1 つのアプリケーションからログアウトすると、Microsoft ID プラットフォームは、ユーザーが現在サインインしているすべてのアプリケーションのフロント チャネル ログアウト URL に HTTP GET 要求を送信します。

これらのアプリケーションは、シングル サインアウトを成功させるために次の 2 つのアクションを実行して、この要求に応答する必要があります。

1. ユーザーを識別するセッションをクリアします。
2. アプリケーションは、ユーザーを識別するすべてのセッションを消去し、`200` 応答を返すことで、この要求に応答する必要があります。

#### フロント チャネル ログアウト URL とは何ですか?

フロント チャネル ログアウト URL は、Web または SPA アプリケーションが Entra 認証サーバーからサインアウト要求を受信し、シングル サインアウト機能を実行する場所です。 各アプリケーションには 1 つのフロント チャネル ログアウト URL があります。

#### フロント チャネル ログアウト URL はいつ設定する必要がありますか?

ユーザーまたは開発者がアプリケーションにシングル サインアウトが必要であると判断した場合は、このアプリケーションのアプリ登録のフロント チャネル ログアウト URL を設定する必要があります。 このアプリケーションのアプリ登録に対してフロント チャネル ログアウト URL が設定されると、Microsoft ID プラットフォームは、サインインしているユーザーが別のアプリケーションからサインアウトしたときに、このアプリケーションのフロント チャネル ログアウト URL に HTTP GET 要求を送信します。

### フロント チャネル ログアウト機能を使用してシングル サインアウトを設定する方法

一連のアプリケーションでフロント チャネル ログアウト機能を使用するには、次の 2 つのタスクを完了する必要があります。

- 同時にサインアウトする必要があるすべてのアプリケーションについて、 [Microsoft Entra 管理センター](https://entra.microsoft.com) でフロント チャネルログアウト URL を設定します。 通常、各アプリケーションには独自の専用フロント チャネル ログアウト URL があります。
- アプリケーション コードを編集して、Microsoft ID プラットフォームからフロント チャネル ログアウト URL に送信された HTTP GET 要求をリッスンし、ユーザーを識別するセッションをクリアして 200 応答を返すことで、この要求に応答します。

#### フロント チャネル ログアウト URL を選択する方法

フロント チャネル ログアウト URL は、HTTP GET 要求を受信して応答できる URL であり、ユーザーを識別するセッションをクリアできる必要があります。 フロント チャネル ログアウト URL の例としては、次のような場合があります。ただし、これらに限定されるわけではありません。

- `https://example.com/frontchannel_logout`
- `https://example.com/signout`
- `https://example.com/logout`
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/v2-saml-bearer-assertion"} -->
## Active Directory フェデレーション サービス (AD FS) によって発行された SAML トークンを Microsoft Graph アクセス トークンに交換する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-saml-bearer-assertion
- Service: identity-platform
- Article date: 2024-09-24
- Summary: SAML ベアラー アサーション フローを使用して、AD FS フェデレーション ユーザーに資格情報の入力を求めずに Microsoft Graph からデータをフェッチする方法について説明します。

Active Directory フェデレーション サービス (AD FS) によって発行された SAML トークンを使用するシングル サインオン (SSO) をアプリケーションで有効にして、Microsoft Graph へのアクセスも要求する場合は、この記事の手順に従ってください。

SAML ベアラー アサーション フローを有効にして、フェデレーション AD FS インスタンスによって発行された SAMLv1 トークンを、Microsoft Graph 用の OAuth 2.0 アクセス トークンに交換します。 ユーザーの認証のためにユーザーのブラウザーが Microsoft Entra ID にリダイレクトされると、ユーザーに自分の資格情報を入力するよう求めるのではなく、ブラウザーによって SAML サインインからセッションが取得されます。

重要

このシナリオは、AD FS が、元の SAMLv1 トークンを発行したフェデレーション ID プロバイダーである場合に**のみ**機能します。 Microsoft Entra ID によって発行された SAMLv2 トークンを Microsoft Graph アクセス トークンに交換することは**できません**。

### 前提条件

- シングル サインオン用の ID プロバイダーとしての AD FS フェデレーション。例については、「[AD FS のセットアップと Office 365 へのシングル サインオンの有効化](https://learn.microsoft.com/ja-jp/archive/blogs/canitpro/step-by-step-setting-up-ad-fs-and-enabling-single-sign-on-to-office-365)」を参照してください。
- HTTP 要求を行う Rest クライアント。

### シナリオの概要

OAuth 2.0 SAML ベアラー アサーション フローでは、クライアントが既存の信頼関係を使用する必要があるときに、SAML アサーションを使用して OAuth アクセス トークンを要求することができます。 SAML アサーションに適用される署名は、承認されたアプリの認証を提供します。 SAML アサーションは、ID プロバイダーによって発行され、サービス プロバイダーによって使用される XML セキュリティ トークンです。 サービス プロバイダーはその内容に依存して、セキュリティ関連の目的でアサーションの対象を識別します。

SAML アサーションは、OAuth トークン エンドポイントにポストされます。 エンドポイントはアサーションを処理し、アプリの事前の承認に基づいてアクセス トークンを発行します。 クライアントでは、更新トークンを持つ必要も保存する必要もありません。また、トークン エンドポイントにクライアント シークレットを渡す必要もありません。

#### Microsoft Entra ID にアプリケーションを登録する

アプリケーションを Microsoft Entra ID に登録するには、「[Microsoft ID プラットフォームにアプリケーションを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)」の記事の手順を完了します。

#### AD FS から SAML アサーションを取得する

SOAP エンベロープを使用して SAML アサーションをフェッチするために、AD FS エンドポイントへの POST 要求を作成します。

`POST https://ADFSFQDN/adfs/services/trust/2005/usernamemixed`

**パラメーター値:**

| キー | 値 |
| --- | --- |
| client-request-id | CLIENT\_ID |

**ヘッダー値:**

| キー | 値 |
| --- | --- |
| SOAPAction | http://schema.xlmsoap.org/ws/2005/02/trust/RST/Issue |
| コンテンツタイプ | application/soap+xml |
| client-request-id | CLIENT\_ID |
| return-client-request-id | true |
| 同意する | application/json |

**AD FS 要求本文:**

```xml
<s:Envelope xmlns:s="http://www.w3.org/2003/05/soap-envelope">
  <s:Header>
    <a:Action s:mustUnderstand="1" xmlns:a="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://schemas.xmlsoap.org/ws/2005/02/trust/RST/Issue</a:Action>
    <a:MessageID>urn:uuid:9af3303f-1f9e-466c-9938-c9a982822557</a:MessageID>
    <a:ReplyTo>
      <a:Address>http://www.w3.org/2005/08/addressing/anonymous</a:Address>
    </a:ReplyTo>
    <o:Security s:mustUnderstand="1" xmlns:o="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">
      <o:UsernameToken u:Id="uuid-2525825F-6A4A-44D8-83BA-68E26F4DD99">
        <o:Username>USERNAME</o:Username>
        <o:Password>PASSWORD</o:Password>
      </o:UsernameToken>
    </o:Security>
  </s:Header>
  <s:Body>
    <trust:RequestSecurityToken xmlns:trust="http://schemas.xmlsoap.org/ws/2005/02/trust">
      <wsp:AppliesTo xmlns:wsp="http://schemas.xmlsoap.org/ws/2004/09/policy">
        <a:EndpointReference>
          <a:Address>urn:federation:MicrosoftOnline</a:Address>
        </a:EndpointReference>
      </wsp:AppliesTo>
      <trust:KeyType>http://schemas.xmlsoap.org/ws/2005/05/identity/NoProofKey</trust:KeyType>
      <trust:RequestType>http://schemas.xmlsoap.org/ws/2005/02/trust/Issue</trust:RequestType>
      <trust:TokenType>http://schemas.xmlsoap.org/ws/2005/02/sc/sct</trust:TokenType>
    </trust:RequestSecurityToken>
  </s:Body>
</s:Envelope>
```

この要求が正常にポストされると、AD FS から SAML アサーションを受け取るはずです。 **SAML:Assertion** タグ データのみが必要です。今後の要求で使用するには、base64 エンコードに変換してください。

#### SAML アサーションを使用して OAuth 2.0 トークンを取得する

AD FS アサーション応答を使用して OAuth 2.0 トークンをフェッチします。

以下に示すように、ヘッダー値を使用して POST 要求を作成します。

| キー | 値 | 説明 |
| --- | --- | --- |
| Host | login.microsoftonline.com |  |
| コンテンツタイプ | application/x-www-form-urlencoded |  |

要求の本文で、client\_id、client\_secret、および assertion (前の手順で取得した base64 エンコードの SAML アサーション) を置き換えます。

| キー | 値 | 説明 |
| --- | --- | --- |
| grant\_type | urn:ietf:params:oauth:grant-type:saml2-bearer | 許可の種類を指定します |
| client\_id | CLIENTID | アプリケーションのクライアント ID を取得する |
| client\_secret | CLIENTSECRET | アプリケーションのクライアント シークレット |
| assertion | ASSERTION | base64 でエンコードされた SAML アサーション |
| scope | openid https://graph.microsoft.com/.default | トークンが有効なスコープ。 |

要求が成功すると、Microsoft Entra ID からアクセス トークンを受け取ります。

#### OAuth 2.0 トークンを使用してデータを取得する

アクセス トークンを受け取ったら、Graph API (この例では Outlook タスク) を呼び出します。

前の手順でフェッチしたアクセス トークンを使用して GET 要求を作成します。

| キー | 値 | 説明 |
| --- | --- | --- |
| コンテンツタイプ | application/x-www-form-urlencoded |  |
| 承認 | Bearer ACCESS\_TOKEN | OAuth 2.0 トークン要求から取得されたアクセス トークン |

要求が成功すると、JSON 応答を受け取ります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/v2-supported-account-types"} -->
## サポートされているアカウントの種類 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-supported-account-types
- Service: identity-platform
- Article date: 2024-04-24
- Summary: Microsoft ID プラットフォームでの対象ユーザーとサポートされているアカウントの種類に関する概念ドキュメント

この記事では、Microsoft ID プラットフォーム アプリケーションでサポートされているアカウントの種類 ("*対象ユーザー*" とも呼ばれます) について説明します。

### パブリック クラウドのアカウントの種類

Microsoft Azure パブリック クラウドでは、ほとんどの種類のアプリで、どのようなオーディエンスのユーザーでもサインインさせることができます。

- 基幹業務 (LOB) アプリケーションを記述している場合は、お客様の組織内のユーザーをサインインさせられます。 このようなアプリケーションは、*シングル テナント*と呼ばれることもあります。
- 独立系ソフトウェア ベンダー (ISV) の場合は、ユーザーをサインインさせるアプリケーションを作成できます。

    - 任意の組織に。 このようなアプリケーションは、*マルチ テナント* Web アプリケーションと呼ばれます。 職場または学校のアカウンでユーザーをサインインさせると解釈できる場合もあります。
    - 職場、学校または個人用の Microsoft アカウントを使用して。
    - 個人用の Microsoft アカウントのみを使用して。
- ビジネスから消費者へのアプリケーションを作成している場合は、Azure Active Directory B2C (Azure AD B2C) を使用して、ソーシャル ID でユーザーをサインインさせることもできます。 2025 年 5 月 1 日より、Azure AD B2C は新規のお客様向けに購入できなくなります。 詳細については、FAQ で [Azure AD B2C を引き続き購入できますか](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) ? を参照してください。

### 認証フローでのアカウントの種類のサポート

一部のアカウントの種類は、特定の認証フローでは使用できません。 たとえば、デスクトップ、ユニバーサル Windows プラットフォーム (UWP)、デーモン アプリケーションでは、次のようになります。

- デーモン アプリケーションは Microsoft Entra 組織でのみ使用できます。 Microsoft の個人アカウントを操作するためにデーモン アプリケーションの使用を試みる意味はありません。 管理者の同意は付与されません。
- 統合 Windows 認証フローは、(お客様の組織または任意の組織の) 職場または学校のアカウントでのみ使用できます。 統合 Windows 認証はドメイン アカウントで動作し、マシンをドメインに参加させるか Microsoft Entra に参加させる必要があります。 このフローは、個人の Microsoft アカウントには意味がありません。
- [リソース所有者のパスワード資格情報付与](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc) (ユーザー名/パスワード) は、個人の Microsoft アカウントでは使用できません。 個人の Microsoft アカウントでは、ユーザーがサインイン セッションごとに個人のリソースへのアクセスに同意する必要があります。 このため、この動作は非対話型フローとは互換性がありません。

### 各国のクラウドのアカウントの種類

アプリは、[各国のクラウド](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud)にユーザーをサインさせることもできます。 ただし、Microsoft の個人アカウントは、これらのクラウドでサポートされていません。 そのため、これらのクラウドでサポートされるアカウントの種類は、お客様の組織 (シングル テナント) または任意の組織 (マルチ テナント アプリケーション) に限定されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/web-api-tutorial-02-prepare-api"} -->
## チュートリアル: 認証のための ASP.NET Core プロジェクトを作成して構成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/web-api-tutorial-02-prepare-api
- Service: active-directory / develop
- Article date: 2022-11-01
- Summary: IDE で API を作成して構成し、認証のための構成を追加し、必要なパッケージをインストールする

登録が完了すると、統合開発環境 (IDE) を使用して ASP.NET Core プロジェクトを作成することができます。 このチュートリアルでは、IDE を使用して ASP.NET Core プロジェクトを作成し、認証と承認のために構成する方法について説明します。

このチュートリアルの内容:

- **ASP.NET Core (空)** を作成する
- アプリケーションの設定を構成する
- 必須の NuGet パッケージを見つけてインストールする

### 前提条件

- 「[チュートリアル: Web API を Microsoft ID プラットフォームに登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/web-api-tutorial-01-register-app)」の前提条件と手順を完了していること。
- このチュートリアルで使用する IDE は、[\[ダウンロード\]](https://visualstudio.microsoft.com/downloads)ページからダウンロードできます。
    - Visual Studio 2022
    - Visual Studio Code
    - Visual Studio 2022 for Mac

- [.NET Core 6.0 SDK](https://dotnet.microsoft.com/download/dotnet) の最小要件。

### ASP.NET Core プロジェクトを作成する

IDE 内で ASP.NET Core プロジェクトを作成するには、次のタブを使用します。

## [Visual Studio](#tab/visual-studio)
1. Visual Studio を開き、 **[新しいプロジェクトの作成]** を選択します。
2. **ASP.NET Core (空)** テンプレートを探して選択し、**[次へ]** を選択します。
3. プロジェクトの名前 (*NewWebAPILocal* など) を入力します。
4. プロジェクトの場所を選択するか既定のオプションをそのまま使用して、**[次へ]** を選択します。
5. **[フレームワーク]** と **[HTTPS 用の構成]** で既定値をそのまま使用します。
6. **[作成]** を選択します。

## [Visual Studio Code](#tab/visual-studio-code)
1. Visual Studio Code を開き、**[ファイル] &gt; [フォルダーを開く...]** の順に選択します。プロジェクトを作成する場所に移動して選択します。
2. 上部のバーで **[ターミナル]** を選択してから **[新しいターミナル]** を選択して、新しいターミナルを開きます。
3. **[エクスプローラー]** ペインの **[新しいフォルダー...]** アイコンを使用して、新しいフォルダーを作成します。 以前に登録した名前と同様の名前、たとえば *NewWebAPILocal* を指定します。
4. **[ターミナル] &gt; [新しいターミナル]** を選択して、新しいターミナルを開きます。
5. **ASP.NET Core (空)** のテンプレートを作成するには、ターミナルで次のコマンドを実行してディレクトリに移動し、プロジェクトを作成します。

    ```powershell
    cd NewWebAPILocal
    dotnet new web
    ```

## [Visual Studio for Mac](#tab/visual-studio-for-mac)
1. Visual Studio を開き、**[新規]** を選択します。
2. 左側のナビゲーション バーの **[Web とコンソール]** で、**[アプリ]** を選択 します。
3. **[ASP.NET Core]** で **[API]** を選択し、ドロップダウン メニューで **[C#]** が選択されていることを確認し、**[続行]** を選択します。
4. **[ターゲット フレームワーク]** と **[詳細設定]** の既定値はそのまま使用し、**[続行]** を選択します。
5. **プロジェクト名**の名前を入力します。これは**ソリューション名**に反映されます。 Azure portal に登録されているのと同様の名前、*NewAPI1* などを指定します。
6. プロジェクトの既定の場所をそのまま使用するか、別の場所を選択して、**[作成]** を選択します。

Note

Visual Studio for Mac は、Microsoft の [モダン ライフサイクル ポリシー](https://learn.microsoft.com/ja-jp/lifecycle/policies/modern)に従って、2024 年 8 月 31 日までに廃止される予定です。 Visual Studio for Mac 17.6 は 2024 年 8 月 31 日までサポートが継続され、セキュリティ問題や Apple からのプラットフォーム更新のためのサービス更新プログラムが提供されます。 詳細については、「[Visual Studio for Mac の動作](https://learn.microsoft.com/ja-jp/visualstudio/mac/what-happened-to-vs-for-mac)」を参照してください。

---

### ASP.NET Core プロジェクトを構成する

前に記録した値は、アプリケーションを認証用に構成するために *appsettings.json* で使用されます。 *appsettings.json* は、実行時に使用されるアプリケーション設定を格納するために使用される構成ファイルです。

1. *appsettings.json* を開き、ファイルの内容を次のコード スニペットに置き換えます。

    ```json
    {
      "AzureAd": {
        "Instance": "https://login.microsoftonline.com/",
        "ClientId": "Enter the client ID here",
        "TenantId": "Enter the tenant ID here",
        "Scopes": "Forecast.Read"
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

    - `Instance` - クラウド プロバイダーのエンドポイント。 [各国のクラウド](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud#azure-ad-authentication-endpoints)の利用可能なさまざまなエンドポイントで確認します。
    - `TenantId` - アプリケーションが登録されているテナントの識別子。 引用符で囲まれた文字を、登録したアプリケーションの概要ページから先ほど記録した **ディレクトリ (テナント) ID** の値に置き換えます。
    - `ClientId` - クライアントとも呼ばれる、アプリケーションの識別子。 引用符で囲まれた文字を、登録したアプリケーションの概要ページから先ほど記録した **アプリケーション (クライアント) ID** の値に置き換えます。
    - `Scopes` - アプリケーションへのアクセスを要求するために使用されるスコープ。 このチュートリアルでは、スコープは `Forecast.Read` です。
2. 変更をファイルに保存します。

### ID パッケージをインストールする

ユーザーの認証を有効にするためには、ID 関連の **NuGet パッケージ**がプロジェクトにインストールされている必要があります。

## [Visual Studio](#tab/visual-studio)
1. トップ メニューで、**[ツール]**&gt;**[NuGet パッケージ マネージャー]**&gt;**[ソリューションの NuGet パッケージの管理]** の順に選択します。
2. **[参照]** タブを選択した状態で、**Microsoft.Identity.Web** を検索し、`Microsoft.Identity.Web` パッケージを選択し、**[プロジェクト]** チェック ボックスをオンにして、**[インストール]** を選択します。
3. 表示される可能性がある他のウィンドウについては、**[OK]** または **[同意する]** を選択します。

## [Visual Studio Code](#tab/visual-studio-code)
1. 前のセクションで開いたターミナルで、次のコマンドを入力します。

    ```powershell
    dotnet add package Microsoft.Identity.Web
    ```

## [Visual Studio for Mac](#tab/visual-studio-for-mac)
1. トップ メニューで、**[ツール]**&gt;**[NuGet パッケージの管理]** の順に選択します。
2. **Microsoft.Identity.Web** を検索し、`Microsoft.Identity.Web` パッケージを選択し、**[プロジェクト]** を選択して **[パッケージの追加]** を選択します。
3. ポップアップで、正しいプロジェクトが選択されていることを確認し、**[OK]** を選択します。
4. 他の **[ライセンスの同意]** ウィンドウが表示される場合は、**[承諾]** を選択します。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/whats-new-docs"} -->
## Microsoft ID プラットフォームに関するドキュメントの新着情報 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/whats-new-docs
- Service: identity-platform
- Article date: 2026-01-10
- Summary: Microsoft ID プラットフォーム ドキュメントの新規および更新された記事。

Microsoft ID プラットフォームに関するドキュメントの新着情報へようこそ。 この記事では、過去 3 か月間に追加された新しい記事と、重要な更新があった記事の一覧を示します。

### 2025 年 12 月

#### 更新された記事

- [チュートリアル: ユーザーを認証する ASP.NET Core Web アプリを設定する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-dotnet-prepare-app) - 更新により、コンテンツのわかりやすさが向上しました。
- [チュートリアル: Microsoft ID プラットフォームを使用して Python Flask Web アプリにユーザーをサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-python-flask-sign-in-out) させる - 更新によりコンテンツのわかりやすさが向上しました。

### 2025 年 11 月

#### 新しい記事

- [Microsoft Entra ID のコンテンツ セキュリティ ポリシーの概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/content-security-policy)

### 2025 年 10 月

今月は更新プログラムを公開しませんでした。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/zero-trust-for-developers"} -->
## ゼロ トラストの原則を使用してアプリケーションのセキュリティを強化する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/zero-trust-for-developers
- Service: identity-platform
- Article date: 2023-01-06
- Summary: ゼロ トラストの原則を使用して、アプリケーションとそのデータのセキュリティを強化する方法について説明します。

開発されたアプリケーションの周囲にセキュリティで保護された安全なネットワーク境界があるとは想定できません。 開発されたほとんどすべてのアプリケーションには、設計上、ネットワーク境界の外部からのアクセスがあります。 アプリケーションは、開発されるとき、セキュリティで保護されて安全であるとは保証できず、またデプロイされた後でもそれは同じです。 アプリケーションのセキュリティを最大限に高めるだけでなく、アプリケーションが侵害された場合に発生する可能性のある損害を最小限に抑えることもアプリケーション開発者の責任です。

さらに、この責任には、アプリケーションがゼロ トラスト セキュリティ要件を満たしていることを期待する顧客およびユーザーの変化するニーズをサポートすることも含まれます。 [ゼロ トラスト モデル](https://www.microsoft.com/security/business/zero-trust?rtc=1)の原則について学習し、実施項目を採用してください。 この原則を学習して採用することで、より安全で、かつセキュリティが侵害された場合に発生する可能性のある損害を最小限に抑えることができるアプリケーションを開発できます。

ゼロ トラスト モデルでは、暗黙的な信頼ではなく、明示的な検証の文化を規定します。 このモデルは、3 つの主要な[基本原則](https://learn.microsoft.com/ja-jp/security/zero-trust/#guiding-principles-of-zero-trust)に支えられています。

- 明示的に検証する
- 最小限の特権アクセスを使用する
- 侵害を想定する

### ゼロ トラストのベスト プラクティス

これらのベスト プラクティスに従って、[Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview)とそのツールを使用して、ゼロ トラスト対応アプリケーションを構築します。

#### 明示的に検証する

Microsoft ID プラットフォームには、リソースにアクセスするユーザーまたはサービスの ID を確認するための認証メカニズムが用意されています。 以下に示すベスト プラクティスを適用して、データまたはリソースへのアクセスが必要なエンティティを "明示的に検証" します。

| ベスト プラクティス | アプリケーションのセキュリティに対する利点 |
| --- | --- |
| [Microsoft 認証ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries) (MSAL) を使用します。 | MSAL は、開発者向けの Microsoft 認証ライブラリのセットです。 MSAL を使用すると、ユーザーとアプリケーションを認証でき、数行のコードを使用するだけで企業のリソースにアクセスするためのトークンを取得できます。 MSAL では、最新のプロトコル ([OpenID Connect と OAuth 2.0](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols)) が使用されます。これにより、アプリケーションでユーザーの資格情報を直接処理する必要がなくなります。 この資格情報の処理により、ID プロバイダーがセキュリティ境界になるため、ユーザーとアプリケーションの両方のセキュリティが大幅に向上します。 また、これらのプロトコルは、ID セキュリティの新しいパラダイム、機会、課題に対応するために継続的に進化します。 |
| 必要に応じて、[継続的アクセス評価](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation) (CAE) や条件付きアクセス認証コンテキストなどの強化されたセキュリティ拡張機能を導入します。 | Microsoft Entra ID で最も使用される拡張機能には、[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)、[条件付きアクセス認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-guide-conditional-access-authentication-context)、CAE が含まれます。 CAE や条件付きアクセス認証コンテキストなどの強化されたセキュリティ機能を使用するアプリケーションは、クレーム チャレンジを処理するようにコーディングする必要があります。 オープン プロトコルにより、追加のクライアント機能を呼び出すために、[クレーム チャレンジとクレーム要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-challenge)を使用できます。 これらの機能は、異常が発生した場合や、ユーザー認証条件が変更された場合などでも、Microsoft Entra ID との対話を続行できます。 これらの拡張機能は、認証のプライマリ コード フローを妨げることなく、アプリケーションにコード化できます。 |
| **アプリケーションの種類**別に正しい[認証フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-app-types)を使用します。 Web アプリケーションの場合は、[機密クライアント フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios#single-page-public-client-and-confidential-client-applications)を常に使用するようにします。 モバイル アプリケーションの場合は、認証に[ブローカー](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-android-single-sign-on#sso-through-brokered-authentication)または[システム ブラウザー](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-android-single-sign-on#sso-through-system-browser)を使用するようにします。 | シークレット (機密クライアント) を保持できる Web アプリケーションのフローは、パブリック クライアント (デスクトップやコンソールのアプリケーションなど) よりも安全性が高いと見なされます。 システム Web ブラウザーを使用してモバイル アプリケーションを認証する場合、セキュリティで保護された[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on) (SSO) エクスペリエンスを使用すると、アプリケーション保護ポリシーを使用できます。 |

#### 最小限の特権アクセスを使用する

開発者は、Microsoft ID プラットフォームを使用して、アクセス許可 (スコープ) を付与し、アクセスを許可する前に呼び出し元に適切なアクセス許可が付与されていることを確認します。 必要最小限のアクセス権を付与できるきめ細かなアクセス許可を有効にして、アプリケーションで最小限の特権アクセスを適用します。 [最小特権の原則](https://learn.microsoft.com/ja-jp/entra/identity-platform/secure-least-privileged-access)を確実に遵守するために、以下を実施することを検討してください。

- 要求されたアクセス許可を評価して、ジョブを実行するために絶対的に必要となる最小限の特権が設定されるようにします。 API サーフェス全体へのアクセス権を持つ "キャッチオール" アクセス許可は作成しないでください。
- API を設計する際は、最小限の特権アクセスを許可する粒度の細かいアクセス許可を与えます。 最初に、機能とデータ アクセスを、[スコープ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc)と[アプリ ロール](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)を使用して制御できるセクションに分割します。 アクセス許可のセマンティクスを変更する方法で、既存のアクセス許可に API を追加しないでください。
- **読み取り専用**アクセス許可を与えます。 `Write` アクセスには、作成、更新、削除操作の権限が含まれます。 クライアントは、データを読み取るだけのために書き込みアクセスを要求してはなりません。
- [委任されたアクセス許可とアプリケーションのアクセス許可](https://learn.microsoft.com/ja-jp/graph/auth/auth-concepts#delegated-and-application-permissions)の両方を与えます。 アプリケーションのアクセス許可を省略すると、自動化、マイクロサービスなどの一般的なシナリオを実現するために、クライアントに対して厳しい要件が発生する可能性があります。
- 機密データを処理する場合は、"標準" と "フル" のアクセス許可を検討します。 機密プロパティを制限し、"標準" アクセス許可 (たとえば、`Resource.Read`) を使用してそれらにアクセスできないようにします。 次に、"フル" アクセス許可 (たとえば、機密情報を含むすべての使用可能なプロパティを返す `Resource.ReadFull`) を実装します。

#### 侵害を想定する

Microsoft ID プラットフォームのアプリケーション登録ポータルは、プラットフォームを認証と関連ニーズのために使用するアプリケーションのプライマリ エントリ ポイントです。 アプリケーションを登録して構成するとき、セキュリティが侵害された場合に発生する可能性がある損害を最小限に抑えるために、以下に示す実施項目に従います。 詳細については、[Microsoft Entra アプリケーションの登録に関するセキュリティのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-best-practices-for-app-registration)に関するページを参照してください。

セキュリティ侵害を防止する次のアクションを検討してください。

- アプリケーションのリダイレクト URI を適切に定義します。 複数のアプリケーションに同じアプリケーション登録を使用しないでください。
- 所有権のため、およびドメインの乗っ取りの回避のために、アプリケーション登録で使用されるリダイレクト URI を確認します。 アプリケーションをマルチテナントにする目的がない限り、アプリケーションはマルチテナントとして作成しないでください。
- アプリケーションとサービス プリンシパルの所有者が、テナントに登録されているアプリケーションに対して常に定義され管理されていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal"} -->
## Microsoft Authentication Libraries (MSAL) のドキュメント

- Source: https://learn.microsoft.com/ja-jp/entra/msal
- Service: msal
- Article date: 2024-02-27
- Summary: Microsoft 認証ライブラリ (MSAL) を使用して、認証と承認を任意のアプリに統合する方法について説明します。

Microsoft 認証ライブラリ (MSAL) を使用して、認証と承認を任意のアプリに統合する方法について説明します。

概要
[Microsoft 認証ライブラリとは](https://learn.microsoft.com/ja-jp/entra/msal/overview)

概念
[サポートされている認証フロー](https://learn.microsoft.com/ja-jp/entra/msal/msal-authentication-flows)

攻略ガイド
[アプリケーションを MSAL に移行する](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-migration)

sample
[Microsoft ID プラットフォームのコード サンプル](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/sample-v2-code)

sample
[Microsoft Entra B2C のコード サンプル](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/integrate-with-app-code-samples)

sample
[コンシューマー向け Microsoft Entra ID (CIAM) コード サンプル](https://learn.microsoft.com/ja-jp/samples/browse/?expanded=entra&products=entra-external-id)

### ライブラリとサポートされているプラットフォーム

適切なライブラリを選択してください。

#### .NET

- [.NET 用 Microsoft Authentication Library](https://learn.microsoft.com/ja-jp/entra/msal/dotnet)
- [MSAL.NET のバージョンの選択](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/choosing-msal-dotnet)
- [パブリック クライアント アプリケーション](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client)
- [機密クライアント アプリケーション](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft-authentication-library-dotnet/confidentialclient)

#### Java

- [Java 用 Microsoft Authentication Library](https://learn.microsoft.com/ja-jp/entra/msal/java)
- [パブリック クライアント アプリケーション](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.publicclientapplication)
- [機密クライアント アプリケーション](https://learn.microsoft.com/ja-jp/java/api/com.microsoft.aad.msal4j.confidentialclientapplication)

#### JavaScript

- [JavaScript 用 Microsoft Authentication Library](https://learn.microsoft.com/ja-jp/entra/msal/javascript)
- [MSAL Angular](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-angular)
- [MSAL ブラウザー](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-browser)
- [MSAL Node](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-node)
- [MSAL React](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-react)

#### Python

- [Python 用 Microsoft Authentication Library](https://learn.microsoft.com/ja-jp/entra/msal/python)
- [パブリック クライアント アプリケーション](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.publicclientapplication)
- [機密クライアント アプリケーション](https://learn.microsoft.com/ja-jp/python/api/msal/msal.application.confidentialclientapplication)

#### Android

- [Android 用 Microsoft Authentication Library](https://learn.microsoft.com/ja-jp/entra/msal/android)
- [パブリック クライアント アプリケーション](https://javadoc.io/static/com.microsoft.identity.client/msal/2.2.3/com/microsoft/identity/client/IPublicClientApplication.html)

#### iOS と macOS

- [iOS および macOS 用 Microsoft Authentication Library](https://learn.microsoft.com/ja-jp/entra/msal/objc)
- [パブリック クライアント アプリケーション](https://azuread.github.io/microsoft-authentication-library-for-objc/Classes/MSALPublicClientApplication.html)

#### Go

- [Microsoft Authentication Library for Go](https://learn.microsoft.com/ja-jp/entra/msal/go)
- [パブリック クライアント アプリケーション](https://learn.microsoft.com/ja-jp/entra/msal/go/packages/public)
- [機密クライアント アプリケーション](https://learn.microsoft.com/ja-jp/entra/msal/go/packages/confidential)

#### パートナーと顧客を認証する

企業間 (B2B) シナリオでパートナー組織のユーザーをサインインさせるか、企業消費者間取引 (B2C) シナリオで顧客向けにカスタムのサインアップおよびサインイン エクスペリエンスを作成します。

- [External Identities のドキュメント](https://learn.microsoft.com/ja-jp/azure/active-directory/external-identities/)

#### Microsoft Graph に接続する

Azure Active Directory に格納されている組織、ユーザー、アプリケーションのデータへのプログラムによるアクセス。 アプリケーションから Microsoft Graph を呼び出して Azure AD ユーザーとグループの作成と管理、ユーザーのプロファイル、予定表、メール アドレスなどのユーザー データの取得と変更を行います。

- [Microsoft Graph API のドキュメント](https://learn.microsoft.com/ja-jp/graph/overview)

#### アプリを管理および販売する

Dropbox、Salesforce、ServiceNow などの既存の SaaS アプリケーションを組織のユーザーが使用できるようにし、SSO を構成し、セキュリティを管理します。 または、Azure AD を使用する他の組織で使用するために独自の SaaS アプリケーションを発行することによって、独立系ソフトウェア ベンダー (ISV) になります。

- [アプリケーション管理のドキュメント](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/what-is-application-management)

#### アプリケーション ユーザーとそのアクセスの管理

組織のインストールされている SaaS アプリケーションで、ユーザー ID とそのロールを自動的に作成します。 HR 主導のプロビジョニング、クロスドメイン ID 管理システム (SCIM) など。

- [アプリケーション ユーザーとロールのプロビジョニングに関するドキュメント](https://learn.microsoft.com/ja-jp/azure/active-directory/app-provisioning/user-provisioning)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android"} -->
## MSAL Android の概要

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android
- Service: msal / msal-android
- Article date: 2024-01-24
- Summary: Android 用 Microsoft Authentication Libraryについて学習する

Android 用の Microsoft Authentication Library (MSAL) は、Android アプリケーションが Microsoft ID プラットフォーム (以前のAzure Active Directory) でユーザーを認証し、OAuth2 プロトコルと OpenID Connect プロトコルを使用して保護された Web API にアクセスできるようにするライブラリです。 MSAL Android を使用すると、開発者はMicrosoft ID プラットフォームからセキュリティ トークンを取得してユーザーを認証し、Android ベースのアプリケーションのセキュリティで保護された Web API にアクセスできます。

MSAL Android では、シングル サインオン (SSO)、条件付きアクセス、ブローカー認証など、複数の認証シナリオがサポートされています。 これにより、Microsoft Entra ID (職場および学校アカウント)、Microsoft アカウント (Outlook.com、hotmail.com、その他複数)、Azure AD B2C (ソーシャル アカウントとローカル アカウント) など、複数の ID を簡単にターゲットにできます。

ここでのガイダンスは、MSAL Android に関連する一般的な機能を文書化することを目的としています。 Microsoft Entra ID、Microsoft アカウント、または AD B2C Azureの使用開始に関するその他のヘルプが必要な場合は、[Microsoft ID プラットフォームドキュメント](https://aka.ms/aaddev)を参照してください。Microsoft Graph API の詳細については、[Microsoft Graphドキュメント](https://graph.microsoft.io)を参照してください。

### MSAL でのネイティブ認証のサポート

MSAL Android では、モバイル アプリケーションでエンド ツー エンドのカスタマイズ可能なフローを使用してネイティブ認証エクスペリエンスを実装することもできます。 ネイティブ認証を使用すると、ユーザーは、アプリを離れることなく、豊富でネイティブなモバイルファーストのサインアップとサインインの過程を案内されます。 ネイティブ認証機能は、 [顧客の外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication) 上のモバイル アプリでのみ使用できます。

### Azure Active Directory認証ライブラリ (ADAL) からの移行

Android 用の Azure Active Directory 認証ライブラリ (ADAL) は、2023 年 6 月に廃止されました。 お客様または組織が Android 用の Azure Active Directory 認証ライブラリ (ADAL) を使用している場合は、アプリのセキュリティを危険にさらさないように [MSAL Android に移行](https://learn.microsoft.com/ja-jp/entra/msal/android/migrate-android-adal-msal)する必要があります。 Android 用Microsoft Authentication Library (MSAL) は、認証とトークンの取得に使用できるサポートされているライブラリです。

### MSAL Android の使用を開始する

アプリケーションで MSAL Android を使用するには、次の手順を実行する必要があります。

- [アプリをMicrosoft Entra IDに登録します](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。
- [クライアント アプリケーションの種類](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-applications) (パブリック クライアントと機密クライアント) について説明します。

MSAL Android では、ブラウザーによる委任された認証エクスペリエンスとネイティブ認証エクスペリエンスの両方がサポートされているため、シナリオに基づいて次のチュートリアルの手順に従います。

- ブラウザーで委任された認証シナリオについては、「クイック スタート」の「[ユーザーをサインインさせ、Android アプリからMicrosoft Graphを呼び出す」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-android-sign-in)参照してください。
- ネイティブ認証シナリオについては、Microsoft Entra 外部 IDサンプル ガイド「[チュートリアル: ネイティブ認証用に Android アプリを準備する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/tutorial-native-authentication-prepare-android-app)」を参照してください。

#### Requirements

- 最小 SDK バージョン 16 以降
- ターゲット SDK バージョン 33 以降

#### 手順 1: MSAL への依存関係を宣言する

アプリの build.gradle に追加します。

```gradle
dependencies {
    implementation 'com.microsoft.identity.client:msal:4.9.+'
}
```

gradle スクリプトのリポジトリ セクションに次の行も追加してください。

```gradle
maven { 
    url 'https://pkgs.dev.azure.com/MicrosoftDeviceSDK/DuoSDK-Public/_packaging/Duo-SDK-Feed/maven/v1' 
}
```

#### 手順 2: MSAL 構成ファイルを作成する

**ブラウザー委任認証:**

構成ファイルをプロジェクトの "生" リソースとして作成します。 `PublicClientApplication` インスタンスを構築するときに、生成されたリソース識別子を使用して参照します。 アプリを初めてMicrosoft Entra 管理センターに登録する場合は、詳細な MSAL [Android 構成ファイル](https://learn.microsoft.com/ja-jp/entra/msal/android/msal-configuration)も提供されます

```javascript
{
  "client_id" : "<YOUR_CLIENT_ID>",
  "redirect_uri" : "msauth://<YOUR_PACKAGE_NAME>/<YOUR_BASE64_URL_ENCODED_PACKAGE_SIGNATURE>",
  "broker_redirect_uri_registered": true,
}
```

`redirect_uri`では、`<YOUR_PACKAGE_NAME>`は、`context.getPackageName()` メソッドによって返されるパッケージ名を参照します。 このパッケージ名は、[`application_id`](https://developer.android.com/studio/build/application-id) ファイルで定義されている`build.gradle`と同じです。

上記の値は、最低限必要な構成です。 MSAL は、他のすべての設定に対してライブラリに付属する既定値に依存します。 ライブラリの既定値については、 [MSAL Android 構成ファイルのドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/android/msal-configuration) を参照してください。

**ネイティブ認証:**

1. res を右クリックし、[新しい &gt; ディレクトリ] を選択します。 新しいディレクトリ名として raw を入力し、[OK] を選択します。
2. この新しいフォルダー (アプリ &gt; src &gt; main &gt; res &gt; raw) に、auth\_config\_native\_auth.json という名前の新しい JSON ファイルを作成し、次のテンプレート MSAL 構成を貼り付けます。

```
{ 
  "client_id": "Enter_the_Application_Id_Here", 
  "authorities": [ 
    { 
      "type": "CIAM", 
      "authority_url": "https://Enter_the_Tenant_Subdomain_Here.ciamlogin.com/Enter_the_Tenant_Subdomain_Here.onmicrosoft.com/" 
    } 
  ], 
  "challenge_types": ["oob"], 
  "logging": { 
    "pii_enabled": false, 
    "log_level": "INFO", 
    "logcat_enabled": true 
  } 
 }
```

#### 手順 3: ブラウザーで委任された認証の AndroidManifest.xml を構成する

1. Android マニフェストを使用して次のアクセス許可を要求する

```XML
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```

1. リダイレクト URI を使用して Android マニフェストで意図フィルターを構成する

構成で指定したリダイレクト URI に一致する意図フィルターを含めなかった場合、対話型トークン要求が失敗します。

```XML
    <!--Intent filter to capture authorization code response from the default browser on the device calling back to our app after interactive sign in -->
    <activity
        android:name="com.microsoft.identity.client.BrowserTabActivity">
        <intent-filter>
            <action android:name="android.intent.action.VIEW" />
            <category android:name="android.intent.category.DEFAULT" />
            <category android:name="android.intent.category.BROWSABLE" />
            <data
                android:scheme="msauth"
                android:host="<YOUR_PACKAGE_NAME>"
                android:path="/<YOUR_BASE64_ENCODED_PACKAGE_SIGNATURE>" />
        </intent-filter>
    </activity>
```

一般的なリダイレクト URI の問題の詳細については、 [MSAL Android](https://learn.microsoft.com/ja-jp/entra/msal/android/frequently-asked-questions) に関する FAQ を参照してください。

### ProGuard

MSAL は、実行時に `.class` ファイルに格納されているリフレクションとジェネリック型の情報を使用して、さまざまな永続化とシリアル化に関連する機能をサポートします。 縮小と難読化のライブラリのサポートは限られています。 このライブラリにはデフォルト設定が含まれています。問題を見つけた場合は、[issue を報告](https://github.com/AzureAD/microsoft-authentication-library-for-android/issues/new/choose)してください。

### レコメンデーション

MSAL はセキュリティ ライブラリです。 ユーザーがサービスにサインインしてアクセスする方法を制御します。 可能な限り、アプリで最新バージョンのライブラリを使用することをお勧めします。 アプリを更新するリスクを制御できるように、 [セマンティック バージョン](http://semver.org) 管理を使用します。 たとえば、常に最新のマイナー バージョン番号 (*x.y.x* など) をダウンロードすると、API のサーフェス領域が変更されていないことを保証して、最新のセキュリティと機能を確実に利用できます。 最新バージョンとリリース ノートは、GitHubの [[リリース](https://github.com/AzureAD/microsoft-authentication-library-for-android/releases)] タブでいつでも確認できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/accounts-overview"} -->
## Microsoft ID プラットフォーム の Android におけるアカウントとテナント プロファイル

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/accounts-overview
- Service: msal / msal-android
- Article date: 2022-09-14
- Summary: Android 用のMicrosoft ID プラットフォーム アカウントの概要

この記事では、Microsoft ID プラットフォームの`account`の概要について説明します。

Microsoft Authentication Library (MSAL) API は、*ユーザー*という用語を用語*アカウント*に置き換えます。 1 つの理由は、ユーザー (人間またはソフトウェア エージェント) が複数のアカウントを持っているか、複数のアカウントを使用できることです。 これらのアカウントは、ユーザー自身の組織、またはユーザーがメンバーになっている他の組織にある場合があります。

Microsoft ID プラットフォームのアカウントは、次の要素で構成されます。

- 一意の識別子。
- アカウントの所有権/制御を示すために使用される 1 つ以上の資格情報。
- 次のような属性で構成される 1 つ以上のプロファイル。
    - 図、指定された名前、ファミリ名、タイトル、オフィスの場所
- アカウントには、権限のソースまたはレコードのシステムがあります。 これは、アカウントが作成され、そのアカウントに関連付けられている資格情報が格納されるシステムです。 Microsoft ID プラットフォームのようなマルチテナント システムでは、レコードシステムはアカウントが作成された`tenant`です。 このテナントは、 `home tenant`とも呼ばれます。
- Microsoft ID プラットフォームのアカウントには、次の記録システムがあります。
    - Azure Active Directory B2C を含むMicrosoft Entra ID。
    - Microsoft アカウント (Live)。
- Microsoft ID プラットフォーム 以外の基幹システムのアカウントは、Microsoft ID プラットフォーム 内では、次のものを含むアカウントとして表されます。
    - 接続されたオンプレミス ディレクトリからの ID (Windows Server Active Directory)
    - LinkedIn、GitHubなどの外部 ID。 このような場合、アカウントには、レコードの配信元システムと、Microsoft ID プラットフォーム内のレコードのシステムの両方があります。
- Microsoft ID プラットフォームでは、1 つのアカウントを使用して、複数の組織 (Microsoft Entra テナント) に属するリソースにアクセスできます。
    - あるレコード システム (Microsoft Entra テナント A) のアカウントが、別のレコード システム (Microsoft Entra テナント B) 内のリソースにアクセス可能であることを記録するには、リソースが定義されているテナントでアカウントを表す必要があります。 これを行うには、システム B のシステム A からアカウントのローカル レコードを作成します。
    - アカウントの表現であるこのローカル レコードは、元のアカウントにバインドされます。
    - MSAL は、このローカル レコードを `Tenant Profile`として公開します。
    - テナント プロファイルには、役職、オフィスの場所、連絡先情報など、ローカル コンテキストに適したさまざまな属性を持つことができます。
- 1 つのアカウントが 1 つ以上のテナントに存在する可能性があるため、1 つのアカウントに複数のプロファイルが存在する可能性があります。

Note

MSAL は、Microsoft アカウント システム (Live、MSA) をMicrosoft ID プラットフォーム内の別のテナントとして扱います。 Microsoft アカウント テナントのテナント ID は次のとおりです。`9188040d-6c67-4c5b-b112-36a304b66dad`

### アカウントの概要図

[Image: アカウントの概要図]

上の図では、次のようになります。

- アカウント `bob@contoso.com`は、オンプレミスのWindows Server Active Directory (オンプレミスのレコードの配信元システム) に作成されます。
- アカウント `tom@live.com`は、Microsoft アカウント テナントに作成されます。
- `bob@contoso.com`は、次のMicrosoft Entra テナント内の少なくとも 1 つのリソースにアクセスできます。
    - contoso.com (レコードのクラウド システム - オンプレミスのレコード システムにリンク)
    - fabrikam.com
    - woodgrovebank.com
    - `bob@contoso.com`のテナント プロファイルは、これらの各テナントに存在します。
- `tom@live.com`は、次のMicrosoft テナント内のリソースにアクセスできます。
    - contoso.com
    - fabrikam.com
    - `tom@live.com`のテナント プロファイルは、これらの各テナントに存在します。
- 他のテナントの Tom と Bob に関する情報は、記録システムの情報とは異なる場合があります。 役職、オフィスの場所などの属性によって異なる場合があります。 これらは、各組織内のグループやロールのメンバー (Microsoft Entra テナント) である場合があります。 この情報を bob@contoso.com テナント プロファイルと呼びます。

この図では、bob@contoso.comとtom@live.comは、異なるMicrosoft Entra テナント内のリソースにアクセスできます。 詳細については、 [Azure portal での Microsoft Entra B2B コラボレーション ユーザーの追加に関するページを参照](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)してください。

### アカウントとシングル サインオン (SSO)

MSAL トークン キャッシュには、アカウントごとに *1 つの更新トークン* が格納されます。 この更新トークンを使用すると、複数のMicrosoft ID プラットフォーム テナントからアクセス トークンをサイレント に要求できます。 ブローカーがデバイスにインストールされると、アカウントはブローカーによって管理され、デバイス全体のシングル サインオンが可能になります。

Important

企業間 (B2C) アカウントと更新トークンの動作は、Microsoft ID プラットフォームの残りの部分とは異なります。 詳細については、「 B2C ポリシーとアカウント」を参照してください。

### アカウント識別子

MSAL アカウント ID はアカウント オブジェクト ID ではありません。 Microsoft ID プラットフォーム内で一意性以外のものを伝えるために解析したり依存したりすることはありません。

Azure AD 認証ライブラリ (ADAL) との互換性を確保し、ADAL から MSAL への移行を容易にするために、MSAL では、MSAL キャッシュで使用可能なアカウントの有効な識別子を使用してアカウントを検索できます。 たとえば、次の例では、各識別子が有効であるため、常に tom@live.com の同じアカウント オブジェクトを取得します。

```java
// The following would always retrieve the same account object for tom@live.com because each identifier is valid

IAccount account = app.getAccount("<tome@live.com msal account id>");
IAccount account = app.getAccount("<tom@live.com contoso user object id>");
IAccount account = app.getAccount("<tom@live.com woodgrovebank user object id>");
```

### アカウントのクレームへのアクセス

MSAL は、アクセス トークンを要求するだけでなく、常に各テナントに ID トークンを要求します。 これは、常に次のスコープを要求することによって行われます。

- openid（オープンID認証プロトコル）
- プロファイル

ID トークンには、要求の一覧が含まれています。 `Claims` はアカウントに関する名前と値のペアであり、要求を行うために使用されます。

前述のように、アカウントが存在する各テナントには、役職、オフィスの場所などの属性を含むがこれらに限定されない、アカウントに関する異なる情報が格納される場合があります。

1 つのアカウントが複数の組織のメンバーまたはゲストである場合でも、MSAL はサービスに対してクエリを実行して、アカウントがメンバーになっているテナントの一覧を取得しません。 代わりに、MSAL は、行われたトークン要求の結果に基づいて、そのアカウントが存在するテナントの一覧を作成していきます。

アカウント オブジェクトで公開される要求は、常にアカウントの "ホーム テナント"/{authority} からの要求です。 そのアカウントがホーム テナントのトークンを要求するために使用されていない場合、MSAL はアカウント オブジェクトを介して要求を提供できません。 例えば次が挙げられます。

```java
// Pseudo Code
IAccount account = getAccount("accountid");

String username = account.getClaims().get("preferred_username");
String tenantId = account.getClaims().get("tid"); // tenant id
String objectId = account.getClaims().get("oid"); // object id
String issuer = account.getClaims().get("iss"); // The tenant specific authority that issued the id_token
```

Tip

アカウント オブジェクトから使用可能な要求の一覧を表示するには、[id_tokenの要求を](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)参照してください。

Tip

id\_tokenに追加の要求を含めるには、「[方法: Microsoft Entra アプリに省略可能な要求を提供する」の省略可能な要求ドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)

#### テナント プロファイル クレームにアクセスする

他のテナントに表示されるアカウントに関する要求にアクセスするには、まずアカウント オブジェクトを `IMultiTenantAccount`にキャストする必要があります。 すべてのアカウントはマルチテナントである可能性がありますが、MSAL 経由で使用できるテナント プロファイルの数は、現在のアカウントを使用してトークンを要求したテナントに基づきます。 例えば次が挙げられます。

```java
// Pseudo Code
IAccount account = getAccount("accountid");
IMultiTenantAccount multiTenantAccount = (IMultiTenantAccount)account;

multiTenantAccount.getTenantProfiles().get("tenantid for fabrikam").getClaims().get("family_name");
multiTenantAccount.getTenantProfiles().get("tenantid for contoso").getClaims().get("family_name");
```

### B2C ポリシーとアカウント

Important

2025 年 5 月 1 日より、Azure AD B2C は新規のお客様向けに購入できなくなります。 詳細については、[AZURE AD B2C を引き続き購入できますか?](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale)に関する FAQ を参照してください。

アカウントの更新トークンは、B2C ポリシー間で共有されません。 そのため、トークンを使用したシングル サインオンは実行できません。 これは、シングル サインオンができないという意味ではありません。 つまり、シングル サインオンでは、Cookie を使用してシングル サインオンを有効にする対話型エクスペリエンスを使用する必要があります。

これは、MSAL の場合、異なる B2C ポリシーを使用してトークンを取得した場合、それぞれ独自の識別子を持つ個別のアカウントとして扱われることも意味します。 アカウントを使用して `acquireTokenSilent`を使用してトークンを要求する場合は、トークン要求で使用しているポリシーと一致するアカウントの一覧からアカウントを選択する必要があります。 例えば次が挙げられます。

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

<!-- MSL-PAGE {"url":"entra/msal/android/acquire-tokens"} -->
## MSAL Android を使用してトークンを取得する

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/acquire-tokens
- Service: msal / msal-android
- Article date: 2024-01-24
- Summary: MSAL Android を使用してトークンを取得する方法について説明します

### MSAL PublicClientApplication を作成する

この例では、MultipleAccountPublicClientApplication のインスタンスを作成しています。これは、同じアプリケーション内で複数のアカウントを使用できるようにするアプリを操作するように設計されています。 SingleAccount モードを使用する場合は、 [単一アカウントとマルチアカウントのドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/android/single-multi-account)を参照してください。 この使用方法の例については、 [MSAL Android のクイック スタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-v2-android) を参照してください。

1. 新しい MultipleAccountPublicClientApplication インスタンスを作成します。

```Java

String[] scopes = {"User.Read"};
IMultipleAccountPublicClientApplication mMultipleAccountApp = null;
IAccount mFirstAccount = null;

PublicClientApplication.createMultipleAccountPublicClientApplication(getContext(),
    R.raw.msal_config,
    new IPublicClientApplication.IMultipleAccountApplicationCreatedListener() {
        @Override
        public void onCreated(IMultipleAccountPublicClientApplication application) {
            mMultipleAccountApp = application;
        }

        @Override
        public void onError(MsalException exception) {
            //Log Exception Here
        }
    });
```

### 対話形式でトークンを取得する

```java

final AcquireTokenParameters.Builder builder = new AcquireTokenParameters.Builder();
builder.startAuthorizationFromActivity(activity)
        .withScopes(scopes)
        .withCallback(getAuthInteractiveCallback());
final AcquireTokenParameters parameters = builder.build();
mMultipleAccountApp.acquireToken(parameters);

...

private AuthenticationCallback getAuthInteractiveCallback() {
    return new AuthenticationCallback() {
        @Override
        public void onSuccess(IAuthenticationResult authenticationResult) {
            /* Successfully got a token, use it to call a protected resource */
            String accessToken = authenticationResult.getAccessToken();
            // Record account used to acquire token
            mFirstAccount = authenticationResult.getAccount();
        }
        @Override
        public void onError(MsalException exception) {
            if (exception instanceof MsalClientException) {
                //And exception from the client (MSAL)
            } else if (exception instanceof MsalServiceException) {
                //An exception from the server
            }
        }
        @Override
        public void onCancel() {
            /* User canceled the authentication */
        }
    };
}
```

### トークンをサイレントで取得する

```java

/*
    Before getting a token silently for the account used to previously acquire a token interactively, we recommend that you verify that the account is still present in the local cache or on the device in case of brokered auth
    Let's use the synchronous methods here which can only be invoked from a Worker thread
*/

//On a worker thread
IAccount account = mMultipleAccountApp.getAccount(mFirstAccount.getId());

if(account != null){
    //Now that we know the account is still present in the local cache or not the device (broker authentication)

    //Request token silently
    String[] newScopes = {"Calendars.Read"};
    
    String authority = mMultipleAccountApp.getConfiguration().getDefaultAuthority().getAuthorityURL().toString();

    //Use default authority to request token from pass null
    final AcquireTokenSilentParameters.Builder builder = new AcquireTokenSilentParameters.Builder();
    builder.forAccount(account)
            .withScopes(newScopes)
            .fromAuthority(authority);
    final AcquireTokenSilentParameters parameters = builder.build();
    final IAuthenticationResult result = mMultipleAccountApp.acquireTokenSilent(parameters);
}

```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/android-emulator-with-msal"} -->
## MSAL Android での Android エミュレーターの使用

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/android-emulator-with-msal
- Service: msal / msal-android
- Article date: 2024-01-24
- Summary: MSAL Android で Android エミュレーターを使用する方法について説明します

MSAL を使用するには、Chrome または Chrome のカスタム タブをサポートするエミュレーターをテストする必要があります。 メイン IDE では、これを実現できます。

- Visual Studioでは、Android 25 以降でエミュレーターを使用します。
- Android Studio では、Android 21 以降で Pixel または Nexus エミュレーターを使用します。

### 手動手順

1. Android 21 以降でエミュレーターを使用していることを確認します。
2. 評判の良いサイトから最新の Chrome APK をインストールします。 たとえば、[APK ミラー](https://www.apkmirror.com/apk/google-inc/chrome/)の [Chrome Browser](http://www.apkmirror.com/apk/google-inc/chrome/) などです。 Android のバージョンとエミュレーター アーキテクチャに適した APK を選択してください。
3. APK ファイルをエミュレーターにドラッグすると、アプリが自動的にインストールされます。 アプリの一覧に移動し、Chrome がインストールされていることを確認します。

### Troubleshooting

- 状況によっては、Hyper-V を無効にし、HAXM または AEHD を有効にする必要がある場合があります。 HAXM または AEHD を有効にする方法の詳細については、[Android エミュレーターでハードウェア アクセラレーションを有効にする方法 (Hyper-V & AEHD)](https://learn.microsoft.com/ja-jp/dotnet/maui/android/emulator/hardware-acceleration) を参照してください。
- Android エミュレーターのデプロイは、初めてである場合、特定のマシンで非常に長い時間がかかる場合があります。
- 既知の問題: Windows 10で実行されている一部の Android エミュレーター インスタンスは、`java.io.EOFException`で断続的に失敗します。 推奨される回避策は、物理デバイスや、別のオペレーティング環境でホストされているエミュレーターを使用することです。 [問題へのリンク](https://github.com/square/okhttp/issues/1517)。

### ヘルプ

上記の手順がうまくいかない場合、または問題が発生した場合は、Github の問題を開くか、Stackoverflow に投稿してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/architect-your-mobile-app"} -->
## モバイル アプリの設計

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/architect-your-mobile-app
- Service: msal / msal-android
- Article date: 2024-01-24
- Summary: MSAL Android で使用するアプリをビルドするときに採用できるアプリ アーキテクチャについて説明します

### Introduction

組織内のユーザー向けに小規模なアプリを構築する場合でも、次に発生する SaaS ヒットの場合でも、Identity をアプリに統合する方法を意識する必要があります。 この記事では、強力なセキュリティ体制を維持しながら、Microsoft ID プラットフォームで使用できるさまざまなアプリ アーキテクチャに焦点を当て、開発の容易さ、スケーリング機能、操作の速度をすべて強調します。

技術的な詳細を確認する前に、いくつかの重要な原則に留意する必要があります。

- ユーザーは、操作は素早く、システムの状態が分かることを期待します。
- モバイルでのユーザーエンゲージメントには、対話の品質、速度、一貫性が不可欠です。
- 運用上、モバイル アプリには通常、他のクライアント (Web アプリ、デスクトップ アプリ) が伴います。

### Basics

Microsoft ID プラットフォームは、OpenId Connect や OAuth2.0 などのオープン標準に基づいて構築されています。 説明するアーキテクチャでは、これらのプロトコルでサポートされる最も一般的なトポロジに焦点を当てます。 各セクションでは、基本構成、各アーキテクチャの長所と短所、その構成を使い始めるためのリンクやベストプラクティスについて順を追って解説します。

### Architectures

- スタンドアロン モバイル アプリ
- モバイル アプリ + サービス
- モバイル アプリ + サーバーレス

#### アーキテクチャ 0: スタンドアロン モバイル アプリ

最終的に選択したアーキテクチャに関係なく、目標は、シームレスなエクスペリエンスをユーザーに提供する優れたモバイル アプリを構築することです。 この最も簡単な症状は、ユーザーのデバイスからすべての API 要求を直接実行するスタンドアロン モバイル アプリです。

[Image: スタンドアロン]

このアーキテクチャでは、サインイン、API の呼び出し、データへの意味の集約と適用、ユーザーへのエクスペリエンスの提示など、デバイスで実行されているコードが責任を負います。 このアーキテクチャでは、専用サービスの使用は除外されませんが、モバイル アプリによって実行される API 呼び出しの大部分に重点を置いています。

##### 長所と短所

###### 利点

- 開発と保守が簡単です。
- ユーザー データの処理の複雑さを軽減します。
    - データがサービスを通過する場合、データのプライバシーと規制への影響があります。
- プライバシーに配慮したユーザーとの信頼を築くことができます。
    - ユーザー データがデバイスから離れる必要はありません。
    - ユーザー データは、ユーザーのデバイスでのみ収集および使用されるという点でより分散されます。

###### デメリット

- ネットワーク経由のネットワーク要求とデータの数が大幅に増えます。
    - 多くのユーザーのデータプランは限られています。
    - ユーザーはネットワークが低く、デバイスに送信される大量のデータの処理が困難になる可能性があります。
- ユーザーの動作のテレメトリと診断の機能を減らします。
- 分析情報とパーソナライズされた経験のために大量のデータを処理する機能を減らします。
    - アプリでは、いくつかの Microsoft Graph API呼び出しを実行し、これをMachine Learning サービスにフィードして、よりパーソナライズされたエクスペリエンスを提供することができます。 これは、モバイル デバイスにとっては計算負荷が高い場合があります。

##### ベスト プラクティス

MSAL を使用してスタンドアロン アプリ アーキテクチャを統合して構築することを選択した場合、アプリに不足するベスト プラクティスはほとんどありません。 MSAL は更新トークンを自動的に更新し、最も一意のMicrosoft Entraシナリオ (多要素認証、SSO、条件付きアクセスなど) を処理し、アカウントとトークンのキャッシュ/管理を行います。

アプリでは、MSAL が自動的に行う処理に加えて、次のプラクティスも考慮できます。

- MSAL + ID
    - API 呼び出しを行う直前に、MSAL を使用してアクセス トークンを取得します。
    - クライアント アプリのアクセス トークン内を見ないでください。これらはいつでも変更される可能性があります。
        - ユーザーに関する情報が必要な場合は、[Microsoft Graph /me](https://developer.microsoft.com/en-us/graph/docs/api-reference/v1.0/resources/users) に要求を送信できます。
    - `acquireTokenSilent(...)`する前に、常に`acquireToken(...)`要求を試してください。
- ネットワーク要求
    - データを要求する API を調査し、すべてのエラー状態を処理します。
        - ほとんどの Microsoft の API では、HTTP 400、401、403、429 がサポートされており、返される可能性があります。
    - 可能な場合は、非同期要求を使用してみてください。
    - API 呼び出しを行う必要があることがわかっている場合は、待ち時間を最小限に抑えるために事前に行ってください。
    - ブロック要求の場合は、視覚的な手掛かりを使用して現在の状態を伝えます。

##### スタンドアロン モバイル アプリの使用を開始する

[MSAL。Android ファースト ステップ サンプル](https://github.com/Azure-Samples/active-directory-android-native-v2)では、スタンドアロンのモバイル アプリ アーキテクチャを使用します。

詳細については、[Github の問題](https://github.com/AzureAD/microsoft-authentication-library-for-android/issues)を作成するか、[タグ `azure-active-directory`を使用して StackOverflow](https://stackoverflow.com/questions/ask) に投稿してください。

#### アーキテクチャ 1.0: モバイル アプリ + サービス

大規模な優れたユーザー エクスペリエンスを提供するモバイル アプリを構築するには、モバイル アプリ + サービス アーキテクチャの使用を検討することをお勧めします。 このアーキテクチャでは、いくつかの新しい課題が導入されていますが、モバイルでシームレスに実行される最先端のエクスペリエンスを提供しようとするアプリには最適なオプションです。

[Image: AppServer]

このアーキテクチャでは、モバイル アプリはユーザーのサインインと承認の取得を担当しますが、API 呼び出しとデータ集計をサービスに延期します。 このアーキテクチャにはいくつかの症状があります。ここでは、ユーザーのコンテキストですべての要求を行う `On-behalf-of` フローをサービスで使用することに重点を置きます。

##### 長所と短所

###### 利点

- 構築できる他のクライアント (より多くのプラットフォーム、Web アプリ、その他のサービス) に簡単に拡張できます。
    - 要求の共有データ モデルを定義する関数を強制し、エクスペリエンス間で一定の一貫性を確保します。
- ユーザーのデバイスに渡されるネットワーク要求とデータを最小限に抑えます。
- データに対するより多くの計算負荷の高い操作を可能にします。
    - これらの操作は、マイクロサービス アーキテクチャ全体に分散できます。
    - 使用パターンと動作に関するより多くのテレメトリをキャプチャする機会。
- データ アクセスとアクセス許可の境界は拡張されません。ユーザーは新しいアクセス権を取得しません。

###### デメリット

- 開発と保守がより困難です。
- あなたのサービスでユーザーデータを処理し、場合によっては保存する必要があります。
    - 規制の監視または必要な機能の可能性を紹介します。
    - 一部のユーザーは、自分のデバイスからデータを離れないことを好む場合があります。
- リスク プロファイルが高いため、セキュリティに関する追加の考慮事項が必要になる場合があります。
    - ユーザー データと更新トークンは、一元化された場所にあります。
    - データを慎重に保護および削除することで、部分的に軽減できます。

##### ベスト プラクティス

MSAL を使用してモバイル アプリとサービスを統合してビルドすることを選択した場合、アプリに不足するベスト プラクティスはほとんどありません。 MSAL は更新トークンを自動的に更新し、最も一意のMicrosoft Entraシナリオ (多要素認証、SSO、条件付きアクセスなど) を処理し、アカウントとトークンのキャッシュ/管理を行います。

アプリでは、MSAL が自動的に行う処理に加えて、次のプラクティスも考慮できます。

- MSAL + ID (ネイティブ アプリ)
    - API 呼び出しを行う直前に、MSAL を使用してアクセス トークンを取得します。
    - クライアント アプリのアクセス トークン内を見ないでください。これらはいつでも変更される可能性があります。
        - ユーザーに関する情報が必要な場合は、[Microsoft Graph /me](https://developer.microsoft.com/en-us/graph/docs/api-reference/v1.0/resources/users) に要求を送信できます。
    - `acquireTokenSilent(...)`する前に、常に`acquireToken(...)`要求を試してください。
- MSAL + ID (サービス)
    - MSAL を使用して、On-Behalf-Of 要求を実行します。
    - ダウンストリーム API 用のアクセス トークン内を検索しないでください。これらはいつでも変更される可能性があります。
    - 次の操作を行う場合は、可能な限りユーザー データを格納しないでください。
        - 業界標準の手法を使用してデータを保護します。
        - キー値データベースでは、受信トークンに `sub` 一意識別子を使用します。
        - 規制またはMicrosoft使用条件の要件を調査して実装します。
    - `acquireTokenSilent(...)`する前に、常に`acquireTokenInteractive(...)`要求を試してください。
- ネットワーク要求
    - データを要求する API を調査し、すべてのエラー状態を処理します。
        - ほとんどの Microsoft の API では、HTTP 400、401、403、429 がサポートされており、返される可能性があります。
        - 独自のサービスでは:
            - サービス拒否攻撃や不適切なクライアントから保護するため、スロットリング（HTTP 429）を実装します。
            - 適切な HTTP コードを使用した API の保護に関するベスト プラクティスに従ってください。
    - 可能な場合は、非同期要求を使用してみてください。
    - API 呼び出しを行う必要があることがわかっている場合は、待ち時間を最小限に抑えるために事前に行ってください。
    - ブロック要求の場合は、視覚的な手掛かりを使用して現在の状態を伝えます。
- 安全
    - サービスに ID を熱心に実装するだけでなく、アプリが[Azure Security Centerを使用してセキュリティ体制を強化](https://azure.microsoft.com/blog/strengthen-your-security-posture-and-protect-against-threats-with-azure-security-center/)する必要があるかどうかを調査します。

##### モバイル アプリ + サービスの使用を開始する

[MSAL。Android ファースト ステップ サンプル](https://github.com/Azure-Samples/active-directory-android-native-v2)では、スタンドアロンのモバイル アプリ アーキテクチャを使用しますが、独自のサービスを呼び出すために簡単に変更できます。

そのためには、次の操作を行う必要があります。

1. サービス アプリを登録し、一連のスコープ/アクセス許可を公開します。
2. これらのスコープを要求するには、このアプリのコードを変更します。
3. 新しいサービスへのアクセス トークンの送信を開始します。

サービスの構築については、 [Node.js Web API クイック スタート](https://github.com/Azure-Samples/active-directory-javascript-nodejs-webapi-v2)を参照してください。

詳細については、[Github の問題](https://github.com/AzureAD/microsoft-authentication-library-for-android/issues)を作成するか、[タグ `azure-active-directory`を使用して StackOverflow](https://stackoverflow.com/questions/ask) に投稿してください。

#### アーキテクチャ 2: モバイル アプリ + サーバーレス

もうすぐです。 今すぐ必要ですか? 私たちにお知らせください！
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/calling-an-api"} -->
## スコープ

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/calling-an-api
- Service: msal / msal-android
- Article date: 2024-01-24
- Summary: API を呼び出すときに必要なスコープについて説明します

### Introduction

ほとんどのユーザーは、API を呼び出すために MSAL を使用してアクセス トークンを取得します。 Microsoft GraphなどのMicrosoft API を呼び出している場合や、自分や組織が発行し、Microsoft Entra IDで保護している API を呼び出している可能性があります。

どちらの場合も、要求を行うために知っておく必要がある基本的な事柄がいくつかあります。 最も重要なのは、アプリケーションで対応する機能を有効にするためにクライアント アプリケーションに必要なスコープの名前です。

### スコープ

スコープは OAuth プロトコルで使用される用語ですが、多くの場合、アクセス許可という用語は、Microsoftドキュメント内で同じ意味で使用されます。 スコープとは、アプリケーションによって要求される、またはアプリケーションに付与される認可（アクセス許可）の範囲を指します。

Microsoft Graphに関連付けられているスコープ (アクセス許可) の一覧は、[アクセス許可のリファレンスMicrosoft Graph](https://learn.microsoft.com/ja-jp/graph/permissions-reference)公開されています

組織が発行した API のスコープを要求する必要がある場合は、API 開発者が提供するドキュメントを参照するか、api に関連付けられているアプリケーションの登録を https://apps.dev.microsoft.com または[Azure portal](https://portal.azure.com)で確認できます。

#### 一意のスコープ

OAuth 承認サーバーとしてのMicrosoft Entra IDは、複数の API (リソース サーバー) を保護するために使用されます。 スコープ名内での名前の競合を回避し、スコープが要求されている API を明確にするため。 スコープには、通常、リソース サーバーに関連付けられているアプリケーション ID (GUID) または、その API サーバーのアプリケーション登録内の 1 つ以上の識別子 URI がプレフィックスとして付けられます。

Microsoft Graphは、スコープ値のプレフィックスが識別子 URI 内にない場合は、Microsoft Graphに属していると見なされる点で特別です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/code-samples"} -->
## Samples

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/code-samples
- Service: msal / msal-android
- Article date: 2024-01-24
- Summary: コード サンプルを参照して MSAL Android の詳細を確認する

以下のサンプル アプリを参照して、選択した言語で Android 用 MSAL の素晴らしい機能をいくつか調べることができます。

[Java のサンプル](https://github.com/Azure-Samples/ms-identity-android-java)

[Kotlin サンプル](https://github.com/Azure-Samples/ms-identity-android-kotlin)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/common-token-and-sigin-parameters"} -->
## 一般的に使用されるサインインとトークンのパラメーター

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/common-token-and-sigin-parameters
- Service: msal / msal-android
- Article date: 2024-01-24
- Summary: トークンと sigin パラメーターを使用するメソッドを操作する方法について説明します

MSAL API 全体で使いやすさと一貫性を高めるために、唯一のパラメーターとして `TokenParameters` サブクラスを使用するメソッドオーバーライドを優先しています。 `SCOPE`、`PROMPT`、`account`、その他のフィールドに個別のパラメーターを使用する他のオーバーライドは非推奨になりました。 以下は、`TokenParameters`サブクラスと、`SignInParameters`の`SignIn()`に`SingleAccountPublicAccountApplication`クラスを使用するためのガイドです。

### `SignInParameters`

新しい`SignInParameters`用の`builder()`クラスでは、ユーザーは`signIn`フローで使用する 5 つのパラメーター（activity、loginHint、スコープ、プロンプト、コールバック）を追加できます。 スコープは、`withScope()`を介して文字列オブジェクトとして個別に指定することも、`<String>`を介して List `withScopes()` オブジェクトとして一度に指定することもできます。

Null 以外: スコープ、アクティビティ、コールバック

Nullable: ログイン ヒント、プロンプト

```java
final SignInParameters signInParameters = SignInParameters.builder()
        .withScope(SCOPE)
        .withScopes(SCOPES)
        .withActivity(ACTIVITY)
        .withLoginHint(LOGINHINT)
        .withPrompt(PROMPT)
        .withCallback(new AuthenticationCallback() {
            @Override
            public void onCancel() {
                // Handle the signIn flow being cancelled
            }
            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {
                // Handle successful signIn flow with authenticationResult returned
            }
            @Override
            public void onError(MsalException exception) {
                // Handle an exception being thrown during the signIn flow
            }
        }).build();
```

### `AcquireTokenParameters`

Null 以外: スコープ、アクティビティ、コールバック

Nullable: アカウント、機関、認証スキーム、クレーム要求、関連付け ID、フラグメント、ログイン ヒント、プロンプト、OtherScopesToAuthorize

```java
final AcquireTokenParameters parameters = new AcquireTokenParameters.Builder()
        .withScopes(SCOPES)
        .startAuthorizationFromActivity(ACTIVITY)
        .forAccount(ACCOUNT)
        .fromAuthority(AUTHORITY)
        .withAuthenticationScheme(SCHEME)
        .withClaims(CLAIMS)
        .withCorrelationId(CORRELATION_ID)
        .withFragment(FRAGMENT)
        .withLoginHint(LOGINHINT)
        .withPrompt(PROMPT)
        .withOtherScopesToAuthorize(OTHER_SCOPES)
        .withCallback(new AuthenticationCallback() {
            @Override
            public void onCancel() {
                // Handle the acquireToken flow being cancelled
            }
            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {
                // Handle successful acquireToken flow with authenticationResult returned
            }
            @Override
            public void onError(MsalException exception) {
                // Handle an exception being thrown during the acquireToken flow
            }
        }).build();
```

### `AcquireTokenSilentParameters`

Null 以外: スコープ、コールバック、アカウント

Nullable: 権限、認証スキーム、クレーム要求、相関 ID、強制更新

```java
final AcquireTokenSilentParameters silentParameters = new AcquireTokenSilentParameters.Builder()
        .withScopes(SCOPES)
        .forAccount(ACCOUNT)
        .fromAuthority(AUTHORITY)
        .withAuthenticationScheme(SCHEME)
        .withClaims(CLAIMS)
        .withCorrelationId(CORRELATION_ID)
        .forceRefresh(false)
        .withCallback(new SilentAuthenticationCallback() {
            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {
                // Handle successful acquireTokenSilent flow with authenticationResult returned
            }

            @Override
            public void onError(MsalException exception) {
                // Handle an exception being thrown during the acquireTokenSilent flow
            }
        }).build();
```

### `PublicClientApplication` クラスでの優先オーバーライド

次に、推奨される `PublicClientApplication` メソッドのオーバーライドを示します。同じメソッドの他のオーバーライドが非推奨としてマークされていることに注意してください。

#### `ISingleAccountPublicClientApplication`

```java
/**
 * Acquire token interactively, will pop-up webUI. Interactive flow will skip the cache lookup.
 * Default value for {@link Prompt} is {@link Prompt#SELECT_ACCOUNT}.
 * <p>
 * Convey parameters via the AcquireTokenParameters object
 *
 * @param acquireTokenParameters {@link AcquireTokenParameters} instance containing the necessary fields. Activity, scopes, and callback must be non-null.
 */
 void acquireToken(@NonNull final AcquireTokenParameters acquireTokenParameters);

/**
 * Perform acquire token silent call. If there is a valid access token in the cache, the sdk will return the access token; If
 * no valid access token exists, the sdk will try to find a refresh token and use the refresh token to get a new access token. If refresh token does not exist
 * or it fails the refresh, exception will be sent back via callback.
 *
 * @param acquireTokenSilentParameters the {@link AcquireTokenSilentParameters} containing the needed parameters for acquireTokenSilent flow. Scopes and authority must be non-null.
 */
IAuthenticationResult acquireTokenSilent(@NonNull final AcquireTokenSilentParameters acquireTokenSilentParameters) throws InterruptedException, MsalException;

/**
 * Perform acquire token silent call. If there is a valid access token in the cache, the sdk will return the access token; If
 * no valid access token exists, the sdk will try to find a refresh token and use the refresh token to get a new access token. If refresh token does not exist
 * or it fails the refresh, exception will be sent back via callback.
 *
 * @param acquireTokenSilentParameters the {@link AcquireTokenSilentParameters} containing the needed fields for acquireTokenSilent flow. Scopes, authority, and callback must be non-null.
 */
void acquireTokenSilentAsync(@NonNull final AcquireTokenSilentParameters acquireTokenSilentParameters);

/**
 * Allows a user to sign in to your application with one of their accounts. This method may only
 * be called once: once a user is signed in, they must first be signed out before another user
 * may sign in. If you wish to prompt the existing user for credentials use
 * {@link #signInAgain(SignInParameters)} or
 * {@link #acquireToken(AcquireTokenParameters)}.
 * <p>
 * Note: The authority used to make the sign in request will be either the MSAL default: https://login.microsoftonline.com/common
 * or the default authority specified by you in your configuration.
 *
 * @param signInParameters the {@link SignInParameters} containing the needed fields for signIn flow. Activity, scopes, and callback must be non-null. loginHint and prompt are nullable
 */
void signIn(@NonNull final SignInParameters signInParameters);

/**
 * Reauthorizes the current account according to the supplied scopes and prompt behavior.
 * <p>
 * Note: The authority used to make the sign in request will be either the MSAL default:
 * https://login.microsoftonline.com/common or the default authority specified by you in your
 * configuration. This flow requires activity, scopes, and callback. Prompt is optional.
 *
 * @param signInParameters the {@link SignInParameters} containing the needed fields for signIn flow. Activity, scopes, and callback must be non-null.
 */
void signInAgain(@NonNull final SignInParameters signInParameters);
```

#### `IMultipleAccountPublicClientApplication`

```java
/**
 * Acquire token interactively, will pop-up webUI. Interactive flow will skip the cache lookup.
 *
 * @param acquireTokenParameters {@link AcquireTokenParameters} instance containing the necessary fields. Activity, scopes, and callback must be non-null.
 */
void acquireToken(@NonNull final AcquireTokenParameters acquireTokenParameters);

/**
 * Perform acquire token silent call. If there is a valid access token in the cache, the sdk will return the access token; If
 * no valid access token exists, the sdk will try to find a refresh token and use the refresh token to get a new access token. If refresh token does not exist
 * or it fails the refresh, exception will be sent back via callback.
 *
 * @param acquireTokenSilentParameters {@link AcquireTokenSilentParameters} instance containing the necessary fields. Scopes, account, and authority must be non-null.
 */
@WorkerThread
IAuthenticationResult acquireTokenSilent(@NonNull final AcquireTokenSilentParameters acquireTokenSilentParameters) throws MsalException, InterruptedException;

/**
 * Perform acquire token silent call. If there is a valid access token in the cache, the sdk will return the access token; If
 * no valid access token exists, the sdk will try to find a refresh token and use the refresh token to get a new access token. If refresh token does not exist
 * or it fails the refresh, exception will be sent back via callback.
 *
 * @param acquireTokenSilentParameters {@link AcquireTokenSilentParameters} instance containing the necessary fields. Scopes, account, authority, and callback must be non-null.
 */
void acquireTokenSilentAsync(@NonNull final AcquireTokenSilentParameters acquireTokenSilentParameters);
```

#### `IPublicClientApplication`

```java
/**
 * Acquire token interactively, will pop-up webUI. Interactive flow will skip the cache lookup.
 * Default value for {@link Prompt} is {@link Prompt#SELECT_ACCOUNT}.
 * <p>
 * Convey parameters via the AcquireTokenParameters object
 *
 * @param acquireTokenParameters AcquireTokenParameters instance containing the necessary fields. Activity, scopes, and callback must be non-null.
 */
void acquireToken(@NonNull final AcquireTokenParameters acquireTokenParameters);
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/configure-your-app"} -->
## MSAL.Android アプリを設定する

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/configure-your-app
- Service: msal / msal-android
- Article date: 2024-01-24
- Summary: MSAL Android を使用するようにアプリを構成する方法について説明します

### Introduction

MSAL。Android は高度に拡張可能であり、開発者はエンド ユーザー エクスペリエンス、アプリのパフォーマンス、地域、およびその他のいくつかのフィールドを変更できるいくつかの要因をカスタマイズできます。 アプリの構成を開始するには、MSAL 構成オブジェクトについて理解する必要があります。 この仕組みがどのように動作するのかと、アプリで調整できるさまざまな項目について説明します。

### Basics

#### 1. 構成を作成する

構成オブジェクトは JSON であり、アプリと共にファイル内に存在します。 アプリ内の任意の場所に自由にドロップできますが、 `res/raw/auth_config.json`でカスタム構成を作成することをお勧めします。

#### 2. MSAL に検索する場所を指定する

次に、構成を検索する場所を MSAL に指示する必要があります。 これは、 `PublicClientApplication`のインスタンス化で行われます。次に例を示します。

```java
sampleApp = new PublicClientApplication(this.getApplicationContext(), R.raw.auth_config);
```

#### 3. カスタム構成を定義する

構成には、必要なフィールドと省略可能なフィールドがあります。 省略可能なものを指定しない場合、ライブラリには既定値が設定されている可能性があります。または、他の場所で提供されたデータを使用してアプリの構成プロファイルを完了します。

すべてのMicrosoft Entra IDおよびMicrosoftアカウント ユーザーを対象とする基本事項のみを含む構成例を次に示します。

```json
{
  "client_id" : "<CLIENT_ID_FROM_https://apps.dev.microsoft.com>",
  "authorization_user_agent" : "DEFAULT",
  "redirect_uri" : "<CLIENT_ID_FROM_https://apps.dev.microsoft.com>://auth",
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

### 構成プロパティ

#### General

| 財産 | データの種類 | 必須 | メモ |
| --- | --- | --- | --- |
| クライアント ID | String | Yes | https://apps.dev.microsoft.com のアプリのクライアント ID |
| リダイレクトURI (redirect\_uri) | String | Yes | https://apps.dev.microsoft.com からのアプリのリダイレクト URI |
| authorities | 一覧&lt;権限&gt; | No | アプリに必要な機関の一覧 |
| authorization\_user\_agent | AuthorizationAgent (列挙型) | No | 詳細については、SSO Wiki の記事「オプション: DEFAULT、BROWSER、WEBVIEW」を参照してください。 |
| http | HttpConfiguration | No | タイムアウトなどの HTTP 構成 |
| ログ | ロギング構成 | No | ロガーが取得する詳細レベル、オプション設定: pii\_enabled (ブール値)、log\_level ([値](https://github.com/AzureAD/microsoft-authentication-library-for-android/blob/dev/msal/src/main/java/com/microsoft/identity/client/Logger.java#L81)) |

#### Authority のプロパティ

| 財産 | データの種類 | 必須 | メモ |
| --- | --- | --- | --- |
| 型 | String | Yes | 対象ユーザーまたはアカウントの種類をアプリのターゲットに反映します。オプション: Microsoft Entra ID、B2C |
| 対象者 | Object | No | type=AAD にのみ適用され、アプリがターゲットとする ID を指定し、アプリの登録構成をミラー化します |
| authority\_url | String | Yes | type=B2C の場合にのみ必要です。アプリで使用する機関の URL またはポリシーを示します |
| デフォルト | boolean | Yes | 1 つ以上の権限が指定されている場合は、1 つの default=true が必要です。 |

#### 対象ユーザーのプロパティ

| 財産 | データの種類 | 必須 | メモ |
| --- | --- | --- | --- |
| 型 | String | Yes | アプリがターゲットにする対象ユーザーを示します。オプション: AzureADandPersonalMicrosoftAccount、PersonalMicrosoftAccount、AzureADMultipleOrgs、または AzureADMyOrg |
| tenant\_id | String | Yes | type=AzureADMyOrg の場合にのみ必要です。 その他の型値の場合は省略可能です。 これには、テナント ドメイン (contoso.com など) またはテナント ID (aaaabbbb-0000-cccc-1111-dddd2222eeee など) を指定できます。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/customize-browsers-and-webviews"} -->
## ブラウザーと Web ビューをカスタマイズする

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/customize-browsers-and-webviews
- Service: msal / msal-android
- Article date: 2025-05-23
- Summary: ブラウザーまたは Web ビューを使用して対話型サインイン エクスペリエンスを起動する方法について説明します

この記事では、Microsoft Authentication Library (MSAL) がアプリで使用できる承認エージェントの主な違いと、それらを有効にする方法について説明します。 承認エージェントの特定の戦略の選択は省略可能であり、アプリで追加の機能をカスタマイズできます。 ほとんどのアプリでは、MSAL の既定の設定が使用されます。

Android アプリケーションで MSAL を使用する場合は、ブラウザーまたは WebView を使用して対話型サインイン エクスペリエンスを起動するかを選択します。

[Image: MSAL でのログイン ユーザー エクスペリエンスのスクリーンショット。]

#### シングル サインオンの影響

既定では、アプリケーションはブラウザーまたはカスタム タブ戦略を使用します。 これにより、ユーザーはシングル サインオン (SSO) を実現し、Microsoftブラウザーで Cookie を保持できるため、資格情報を入力する必要がある回数を減らすことができます。 これにより、他のネイティブ Android または Web アプリでも SSO を実現できます。

Authenticator または ポータル サイト サポートを統合せずにアプリケーションで WebView 戦略を使用する場合、ユーザーは単一のアプリケーションでシングル サインオンを行うことができますが、デバイス全体やネイティブ アプリと Web アプリの間では実現できません。

アプリケーションで Authenticator または ポータル サイト サポートで MSAL を使用している場合、ユーザーは、いずれかのアプリでアクティブなサインインがある場合に、これらのアプリを介してアプリケーション間でシングル サインオンを実現できます。

### WebViews

アプリは、MSAL に渡される構成 JSON で次の行を指定することで、アプリ内 WebView を使用します。

```json
"authorization_user_agent" : "WEBVIEW"
```

アプリ内 WebView を使用すると、ユーザーはアプリに直接サインインします。 トークンはアプリのサンドボックス内に保持され、アプリの Cookie jar の外部では使用できません。 その結果、Authenticator または ポータル サイト と統合しない限り、ユーザーはアプリケーション間で SSO を取得できません。

Webview には、開発者がサインイン エクスペリエンスをカスタマイズするために使用できるオプションが用意されていますが、MSAL では Web ビュー内でのズームの有効化のみがサポートされています。 さらに、これは MSAL のみのシナリオでのみ使用できます。ブローカー Web ビューでは、MSAL 構成の設定に関係なく、ズームの有効化はサポートされていません (ブローカー ホスティング アプリがデバイスにインストールされている場合、MSAL は既定でブローカー Webivew を使用します)。 また、アプリが独自の WebView インスタンスを作成し、Microsoft Entraでサインインしようとすると、Broker でのシングル サインオン (SSO) は機能しません。 MSAL WebView の Cookie は、サードパーティ製アプリによって個別に作成された WebView インスタンスと共有されません。

### 既定のブラウザー + カスタム タブ

#### Basics

既定では、MSAL はブラウザーとカスタム タブ戦略を使用します。 MSAL を使用すると、アプリはこの戦略を明示的に示して、JSON 構成を使用して今後のリリースで `DEFAULT` に変更されないようにすることができます。

```json
"authorization_user_agent" : "BROWSER"
```

`BROWSER`アプローチを使用すると、ユーザーはデバイス ブラウザーで SSO を実現できます。 MSAL は共有 Cookie jar を使用し、他のネイティブ アプリまたは Web アプリが、Microsoftによって設定された永続的なセッション Cookie を使用してデバイス上で SSO を取得できるようにします。

#### ブラウザーヒューリスティック

Android OEM の多様な性質により、MSAL では、異なる Android フォン間で正確なブラウザー パッケージを指定することはできません。 その結果、MSAL は、最適なクロスデバイス SSO の提供に重点を置いたブラウザー選択ヒューリスティックを開発しました。 MSAL のロジックは次のメソッドに含まれています。

```
[com.microsoft.identity.common.internal.ui.browser.BrowserSelector.select(final Context context)](https://github.com/AzureAD/microsoft-authentication-library-common-for-android/blob/dev/common/src/main/java/com/microsoft/identity/common/internal/ui/browser/BrowserSelector.java#L57)
```

使用するブラウザーを選択するために、MSAL はデバイスにインストールされているブラウザーの完全な一覧を取得します。 リストはパッケージ マネージャーから返された順序であるため、ユーザーの設定 (設定されている場合は既定のブラウザー) がリストの最初のエントリであることを間接的に反映します。 カスタム タブがサポートされているかどうかに関係なく、一覧の *最初* のブラウザーが選択されます。 ただし、サポートされている場合は、MSAL によってカスタム タブが起動されます。カスタム タブは、アプリ内 WebView に近い外観を持ち、いくつかの基本的な UI 要素をカスタマイズできます。 詳細については、「 [Android のカスタム タブ」](https://developer.chrome.com/multidevice/android/customtabs)を参照してください。

デバイスにブラウザー パッケージがない場合、MSAL はフォールバックしてアプリ内 WebView を使用します。

#### 追加メモ

>
> ブラウザーの一覧の一貫性に関する注意: オペレーティング システムはブラウザーの順序を決定し、最適から最悪の順序に一覧表示します。 デバイスの既定の設定が変更されていない場合は、サインインごとに同じブラウザーが起動し、SSO が確保されます。

>
> Chrome の注: 別のブラウザーが既定として設定されている場合、MSAL は常に Chrome を優先しないようになりました。 たとえば、Samsung ブラウザーと Chrome の両方がプレインストールされている Samsung S7 では、Samsung Browser が既定のブラウザーとして設定されます。 ユーザーが設定を変更しない限り、MSAL は Samsung Browser を使用します。

>
> ブローカー ブラウザーに関する注意: 一部のブラウザーでは、OAuth 2.0 承認コード フローがサポートされていません。 今後のリリースでは、Microsoftは拒否リストを保持して、これらが選択されないようにします。

#### テスト済みブラウザー

| ブラウザー | 組み込みのブラウザー | クロム | オペラ | Microsoft Edge | UC ブラウザー | Firefox |
| --- | --- | --- | --- | --- | --- | --- |
| Nexus 4 (API 17) | pass | pass | 適用されません | 適用されません | 適用されません | 適用されません |
| Samsung S7 (API 25) | 合格\* | pass | pass | pass | 失敗 | pass |
| ファーウェイ (API 26) | 合格\*\* | pass | 失敗 | pass | pass | pass |
| Vivo (API 26) | pass | pass | pass | pass | pass | 失敗 |
| ピクセル 2 (API 26) | pass | pass | pass | pass | 失敗 | pass |
| Oppo | pass | 適用されません\*\*\* | 適用されません | 適用されません | 適用されません | 適用されません |
| OnePlus (API 25) | pass | pass | pass | pass | 失敗 | pass |
| Nexus (API 28) | pass | pass | pass | pass | 失敗 | pass |
| MI | pass | pass | pass | pass | 失敗 | pass |

\*Samsungの組み込みブラウザはサムスンインターネットです。

\*\*ファーウェイの組み込みブラウザはHuaweiブラウザです。

Oppo デバイス設定内で既定のブラウザーを変更することはできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/diagnostic-data-collection"} -->
## MSAL ログ収集を設定する

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/diagnostic-data-collection
- Service: msal / msal-android
- Article date: 2024-01-24
- Summary: MSAL ログ収集を設定する方法について説明します

問題を効果的にデバッグできるようにするには、次の手順に従ってください。

#### 1. MSAL ログ収集を設定する

ロガーを接続するには、 [こちらの](https://github.com/AzureAD/microsoft-authentication-library-for-android/wiki/Logging) 手順に従ってください。 可能であれば、(logcat への印刷ではなく) 別のファイルに印刷してください。

社内で問題を再現できる場合は、構成ファイルで log\_level プロパティを設定して *、VERBOSE* ログを有効にするのが理想的です。

```
{
  "client_id" : [YOUR_CLIENT_ID],
  "authorization_user_agent" : "DEFAULT",
  "redirect_uri" : [YOUR_REDIRECT_URI],
  "authorities" : [YOUR_AUTHORITIES]
  "logging": {
    "log_level": "verbose"
  }
}
```

これを行うには、PublicClientApplication を初期化 ***した後*** に次のメソッドを呼び出します。 それは必ず初期化の後で行う必要があります。そうしないと、設定の初期化によって上書きされてしまいます。

```Java
Logger.getInstance().setLogLevel(LogLevel.VERBOSE);
```

#### 2. 問題を再現する

- 問題を再現できる場合は、
    - 可能であれば、すべてをアンインストールし、すべてを再インストールして、問題をゼロから再現します。
    - 一度に問題を再現します。
    - ログをすぐに収集します。
- 問題を社内で再現できない場合は、問題を特定できるように、(推定) タイムスタンプや関連付け ID を指定してください。

#### 3. ログと問題に関する情報をアップロードします。

1. シナリオの詳細 - 達成しようとしているもの。
2. 問題を再現する手順。
3. 現在使用している ADAL/MSAL のバージョン。
4. MSAL の構成ファイル。
5. MSAL ログ。
6. ポータル サイト と Authenticator のログ (インストールされている場合)。
    - これは、アプリの ***ヘルプ/サポート*** ページからアップロードできます。
    - ログをアップロードすると、 *関連付け ID* のセットが提供されます。 これらの ID を転送してください。
    - [MS Authenticator と ポータル サイト で PowerLift インシデントを作成する](https://stackoverflow.microsoft.com/questions/148465)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/frequently-asked-questions"} -->
## MSAL Android に関してよく寄せられる質問

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/frequently-asked-questions
- Service: msal / msal-android
- Article date: 2024-01-24
- Summary: MSAL Android に関してよく寄せられる質問

### リダイレクト URI の問題

MSAL は、既定でブローカー認証を使用するように構成されています。 これを機能させるには、アプリケーションに関連付けられている署名キーを使用してリダイレクト URI を作成する必要があります。 その後、リダイレクト URI は、アプリケーション構成ファイルと Android マニフェストの両方に含める必要があります。 残念ながら、それぞれに異なるエンコードが必要です。 簡単に見ていきましょう。

1. 最初に行う必要があるのは、アプリケーションの署名キーを配置することです。 通常、デバッグ キーは、debug.keystore と呼ばれる Java キーストアの ".android" という隠しフォルダーの下にあるホーム フォルダーにあります。 Azure portal には、次のようにアプリケーションの署名を生成するための便利なコマンドが用意されています。

- Macos：

```
keytool -exportcert -alias androiddebugkey -keystore ~/.android/debug.keystore | openssl sha1 -binary | openssl base64
```

- ウィンドウズ：

```
keytool -exportcert -alias androiddebugkey -keystore %HOMEPATH%\.android\debug.keystore | openssl sha1 -binary | openssl base64
```

>
> 警告:パイプコマンドの入出力が便利です。ただし、エラーが非表示になる可能性があります。 たとえば、キーストア ファイルが見つからない場合、エラー メッセージは openssl にパイプ処理され、完全なコマンドによって期待どおりの結果が得られます。 疑わしい場合は、最初のコマンドを個別に実行して動作していることを確認します。

>
> 警告: キーストアのパスワードを指定するように求められます。 デバッグ キーストアのパスワードは単に "android" です。 パスワードの入力を求められなかった場合は、キーストア ファイルが見つかったことを確認するために、上記のコマンドの一部を個別に実行する必要がある可能性があります。

>
> 警告: キーストア ファイルが別の場所にある場合は、`%HOMEPATH%\.android\debug.keystore` (Windows) または `~/.android/debug.keystore` (MacOS) をキーストア ファイルへのパスに置き換える必要があります。

1. base64 でエンコードされた署名を取得したら、 これを使用して、ブラウザーがアプリケーションに承認コードを正しく返すことができるように、アプリケーションのインテント フィルターを構成できます。 デバイス上でインテント フィルターを一意にするために、アプリケーションの署名に加えて、カスタム スキーム "msauth" とパッケージ名が含まれます。 次に例を示します。

```xml
//NOTE: the slash before your signature value added to the path attribute
//This uses the base64 encoded signature produced above.
<data android:scheme="msauth"
                    android:host="com.microsoft.identity.client.sample.local"
                    android:path="/1wIqXSqBj7w+h11ZifsnqwgyKrY="/>
```

1. msal 構成ファイルには、少し異なるものが必要です。 既に base64 でエンコードされているだけでなく、署名値を URL でエンコードする必要があります。 例を次に示します。

```javascript
//NOTE that the signature part of the uri has been url encoded.  In this example.  The "+" character and the "=" character are affected.
"redirect_uri" : "msauth://com.microsoft.identity.client.sample.local/1wIqXSqBj7w%2Bh11ZifsnqwgyKrY%3D",
```

### アセンブリされたアプリのリダイレクト URI 構成を表示する

アプリ登録の作成 (またはトラブルシューティング) を支援するために、リダイレクト URI に関する問題の構成と診断に役立つヘルパー関数を `PublicClientApplication`に追加し、リダイレクト `Activity`を追加しました。

MSAL 2.0.0 以降では、次のメソッドを呼び出して、アプリケーションの推奨される構成を出力できます。

```java
// From any Activity in your app, call this static method
PublicClientApplication.showExpectedMsalRedirectUriInfo(MyActivity.this);
```

[Image: MSAL リダイレクト URI 情報]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/handling-exceptions"} -->
## エラーと例外 (MSAL Android)

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/handling-exceptions
- Service: msal / msal-android
- Article date: 2023-01-27
- Summary: MSAL Android アプリケーションでエラーと例外、条件付きアクセス、要求のチャレンジを処理する方法について説明します。

Microsoft Authentication Library (MSAL) の例外は、アプリ開発者がアプリケーションのトラブルシューティングを行うのに役立ちます。 例外メッセージはローカライズされません。

例外とエラーを処理する場合は、例外の種類自体とエラー コードを使用して例外を区別できます。 エラー コードの一覧については、[認証と承認のエラー コードMicrosoft Entra](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes)参照してください。

サインイン エクスペリエンス中に、同意、条件付きアクセス (MFA、デバイス管理、場所ベースの制限)、トークンの発行と利用、およびユーザー プロパティに関するエラーが発生する可能性があります。

| エラークラス | 原因/エラー文字列 | 対処法 |
| --- | --- | --- |
| `MsalUiRequiredException` | - `INVALID_GRANT`: アクセス トークンの引き換えに使用される更新トークンが無効、期限切れ、または取り消されています。 この例外は、パスワードの変更が原因である可能性があります。<br>- `NO_TOKENS_FOUND`: アクセス トークンが存在せず、アクセス トークンを使用するための更新トークンが見つかりません。<br>- ステップアップが必要<br>    - MFA<br>    - 見つからない請求<br>- 条件付きアクセスによってブロックされる ( [認証ブローカー](https://learn.microsoft.com/ja-jp/entra/msal/android/single-sign-on) のインストールが必要な場合など)<br>- `NO_ACCOUNT_FOUND`: サイレント認証用にキャッシュに使用できるアカウントがありません。 | `acquireToken()`を呼び出して、ユーザー名とパスワードの入力を求め、場合によっては同意して多要素認証を実行します。 |
| `MsalDeclinedScopeException` | - `DECLINED_SCOPE`: ユーザーまたはサーバーがすべてのスコープを受け入れていません。 要求されたスコープが特定のアカウントでサポートされていない、認識されない、またはサポートされていない場合、サーバーはスコープを拒否する可能性があります。 | 開発者は、許可されたスコープで認証を続行するか、認証プロセスを終了するかを決定する必要があります。 付与されたスコープに対してのみトークン取得要求を再送信し、`silentParametersForGrantedScopes` を渡して `acquireTokenSilent` を呼び出すことで、どのアクセス許可が付与されているかについてのヒントを提供するオプション。 |
| `MsalServiceException` | - `INVALID_REQUEST`: この要求に必要なパラメーターがないか、無効なパラメーターが含まれているか、パラメーターが複数回含まれているか、形式が正しくありません。<br>- `SERVICE_NOT_AVAILABLE`: サービスが停止しているための 500/503/506 エラー コードを表します。<br>- `UNAUTHORIZED_REQUEST`: クライアントが承認コードを要求する権限がありません。<br>- `ACCESS_DENIED`: リソース所有者または承認サーバーが要求を拒否しました。<br>- `INVALID_INSTANCE`: `AuthorityMetadata` 検証に失敗しました<br>- `UNKNOWN_ERROR`: サーバーへの要求は失敗しましたが、エラーは発生せず、 `error_description` はサービスから返されません。 | この例外クラスは、サービスとの通信時のエラーを表します。承認エンドポイントまたはトークン エンドポイントから取得できます。 MSAL は、サーバーの応答からエラーとerror\_descriptionを読み取ります。 一般に、これらのエラーは、コードまたはアプリ登録ポータルでアプリ構成を修正することで解決されます。 サービスの停止によってこの警告がトリガーされることはほとんどありません。この警告は、サービスの復旧を待機することによってのみ軽減できます。 |
| `MsalClientException` | - `MULTIPLE_MATCHING_TOKENS_DETECTED`: 複数のキャッシュ エントリが見つかり、SDK がキャッシュからの正しいアクセストークンまたは更新トークンを識別できません。 通常、この例外は、トークンを格納するための SDK のバグ、またはサイレント要求で機関が指定されておらず、一致する複数のトークンが見つかったことを示します。<br>- `DEVICE_NETWORK_NOT_AVAILABLE`: デバイスでアクティブなネットワークを使用できません。<br>- `NO_NETWORK_CONNECTION_POWER_OPTIMIZATION`: 電源の最適化が有効になっているため、アクティブなネットワークは使用できません。 デバイスがドーズ モードであるか、アプリがスタンバイ状態です。<br>- `JSON_PARSE_FAILURE`: SDK が JSON 形式を解析できませんでした。<br>- `IO_ERROR`: 発生した `IOException` は、デバイスまたはネットワーク エラーである可能性があります。<br>- `MALFORMED_URL`: URL の形式が正しくありません。 認証要求、機関、またはリダイレクト URI を構築するときに発生する可能性があります。<br>- `UNSUPPORTED_ENCODING`: エンコードはデバイスではサポートされていません。<br>- `NO_SUCH_ALGORITHM`: [PKCE](https://tools.ietf.org/html/rfc7636) チャレンジの生成に使用されるアルゴリズムはサポートされていません。<br>- `INVALID_JWT`: `JWT` サーバーから返された値が無効であるか、空であるか、形式が正しくありません。<br>- `STATE_MISMATCH`: 承認応答からの状態が、承認要求の状態と一致しませんでした。 承認要求の場合、SDK はリダイレクトから返された状態と要求で送信された状態を確認します。<br>- `UNSUPPORTED_URL`: サポートされていない URL は、ADFS 機関の検証を実行できません。<br>- `AUTHORITY_VALIDATION_NOT_SUPPORTED`: この authority は authority の検証ではサポートされていません。 SDK は B2C 機関をサポートしますが、B2C 機関の検証はサポートしていません。 既知のホストのみがサポートされています。<br>- `CHROME_NOT_INSTALLED`: Chrome がデバイスにインストールされていません。 SDK は、使用可能な場合は承認要求に chrome カスタム タブを使用し、chrome ブラウザーにフォールバックします。<br>- `REQUEST_THROTTLED_AT_ESTS_GATEWAY`: クライアントからの大量のリクエストにより、このリクエストは現在 ESTS ゲートウェイでスロットルされています。 しばらくしてから再試行してください。<br>- `USER_MISMATCH`: トークン取得要求で指定されたユーザーが、サーバーから返されたユーザーと一致しません。 | この例外クラスは、ライブラリに対してローカルな一般的なエラーを表します。 これらの例外は、要求を修正することで処理できます。 |
| `MsalUserCancelException` | - `USER_CANCELED`: ユーザーが対話型フローを開始し、トークンを受信する前に要求を取り消しました。 |  |
| `MsalArgumentException` | - `ILLEGAL_ARGUMENT_ERROR_CODE`<br>- `AUTHORITY_REQUIRED_FOR_SILENT`: `acquireTokenSilent`に権限を指定する必要があります。 | これらのエラーは、開発者が引数を修正し、対話型認証、完了コールバック、スコープ、および有効な ID を持つアカウントのアクティビティが提供されていることを確認することで軽減できます。 |

### エラーのキャッチ

次のコード スニペットは、サイレント `acquireToken` 呼び出しのエラーをキャッチする例を示しています。

```java
/**
 * Callback used in for silent acquireToken calls.
 */
private SilentAuthenticationCallback getAuthSilentCallback() {
    return new SilentAuthenticationCallback() {

        @Override
        public void onSuccess(IAuthenticationResult authenticationResult) {
            Log.d(TAG, "Successfully authenticated");

            /* Successfully got a token, use it to call a protected resource - MSGraph */
            callGraphAPI(authenticationResult);
        }

        @Override
        public void onError(MsalException exception) {
            /* Failed to acquireToken */
            Log.d(TAG, "Authentication failed: " + exception.toString());
            displayError(exception);

            if (exception instanceof MsalClientException) {
                /* Exception inside MSAL, more info inside MsalError.java */
            } else if (exception instanceof MsalServiceException) {
                /* Exception when communicating with the STS, likely config issue */
            } else if (exception instanceof MsalUiRequiredException) {
                /* Tokens expired or no session, retry with interactive */
            }
        }
    };
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/logging"} -->
## ANDROID 用 MSAL でのエラーと例外のログ記録。

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/logging
- Service: msal / msal-android
- Article date: 2021-01-25
- Summary: ANDROID 用 MSAL でエラーと例外をログに記録する方法について説明します。

Microsoft Authentication Library (MSAL) アプリは、問題の診断に役立つログ メッセージを生成します。 アプリでは、数行のコードでログ記録を構成し、詳細レベルと個人データと組織データをログに記録するかどうかをカスタム制御できます。 MSAL ログの実装を作成し、ユーザーが認証の問題がある場合にログを送信する方法を提供することをお勧めします。

### ログ記録のレベル

MSAL には、いくつかのレベルのログの詳細が用意されています。

- LogAlways: このログ レベルでは、レベル のフィルター処理は行われません。 すべてのレベルのログ メッセージがログに記録されます。
- 重大: 回復不能なアプリケーションまたはシステムのクラッシュ、または直ちに注意が必要な致命的な障害を記述するログ。
- エラー: 問題が発生し、エラーが生成されたことを示します。 問題のデバッグと特定に使用されます。
- 警告: 必ずしもエラーや障害が発生したことを示すものではなく、診断や問題の特定を目的としています。
- 情報: MSAL は、必ずしもデバッグを目的としていない情報提供目的のイベントをログに記録します。
- 詳細 (既定): MSAL は、ライブラリの動作の詳細をログに記録します。

Note

すべての MSAL SDK のすべてのログ レベルが使用できるわけではありません

### 個人データと組織データ

既定では、MSAL ロガーは機密性の高い個人データや組織データをキャプチャしません。 ライブラリには、個人データと組織データのログ記録を有効にするオプションが用意されています (これを行う場合)。

次のセクションでは、アプリケーションの MSAL エラー ログの詳細について説明します。

### Java を使用した Android 向け MSAL でのログ記録

ログ 記録コールバックを作成して、アプリの作成時にログ記録を有効にします。 コールバックは、次のパラメーターを受け取ります。

- `tag` は、ライブラリによってコールバックに渡される文字列です。 ログ エントリに関連付けられているため、ログ メッセージの並べ替えに使用できます。
- `logLevel` を使用すると、必要なログ記録のレベルを決定できます。 サポートされているログ レベルは、 `Error`、 `Warning`、 `Info`、および `Verbose`です。
- `message` はログ エントリの内容です。
- `containsPII` は、個人データまたは組織データを含むメッセージをログに記録するかどうかを指定します。 既定では、これは false に設定されているため、アプリケーションは個人データをログに記録しません。 `containsPII`が`true`されている場合、このメソッドはメッセージを 2 回受信します。1 回は `containsPII` パラメーターを `false` に設定し、`message`は個人データなしで、もう 1 回は `containsPii` パラメーターを `true` に設定し、メッセージに個人データが含まれる場合があります。 場合によっては (メッセージに個人データが含まれていない場合)、メッセージは同じになります。

```java
private StringBuilder mLogs;

mLogs = new StringBuilder();
Logger.getInstance().setExternalLogger(new ILoggerCallback()
{
   @Override
   public void log(String tag, Logger.LogLevel logLevel, String message, boolean containsPII)
   {
      mLogs.append(message).append('\n');
   }
});
```

既定では、MSAL ロガーは個人を特定できる情報や組織を特定できる情報をキャプチャしません。 個人を特定できる情報または組織を特定できる情報のログ記録を有効にするには:

```java
Logger.getInstance().setEnablePII(true);
```

個人データと組織データのログ記録を無効にするには:

```java
Logger.getInstance().setEnablePII(false);
```

既定では、logcat へのログ記録は無効になっています。 有効にするには:

```java
Logger.getInstance().setEnableLogcatLog(true);
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/migrate-android-adal-msal"} -->
## Android 用の ADAL から MSAL への移行ガイド

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/migrate-android-adal-msal
- Service: msal / msal-android
- Article date: 2020-10-14
- Summary: Azure Active Directory認証ライブラリ (ADAL) Android アプリを Microsoft Authentication Library (MSAL) に移行する方法について説明します。

この記事では、Azure Active Directory認証ライブラリ (ADAL) を使用してMicrosoft Authentication Library (MSAL) を使用するアプリを移行するために必要な変更について説明します。

### 相違点の強調表示

ADAL は、Azure AD v1.0 エンドポイントで動作します。 Microsoft Authentication Library (MSAL) は、以前は Azure AD v2.0 エンドポイントと呼ばれるMicrosoft ID プラットフォームで動作します。 Microsoft ID プラットフォームは、Azure AD v1.0 とは異なります。

サポート：

- 組織 ID (Microsoft Entra ID)
- Outlook.com、Xbox Live などの組織以外の ID
- (Azure AD B2C のみ) Google、Facebook、X、Amazon とのフェデレーション ログイン
- 標準は次と互換性があります。

    - OAuth v2.0
    - OpenID Connect (OIDC)

MSAL パブリック API では、次のような重要な変更が導入されています。

- トークンにアクセスするための新しいモデル:
    - ADAL は、サーバーを表す `AuthenticationContext`を介してトークンへのアクセスを提供します。 MSAL は、クライアントを表す `PublicClientApplication`を介してトークンへのアクセスを提供します。 クライアント開発者は、対話する必要があるすべての機関に対して新しい `PublicClientApplication` インスタンスを作成する必要はありません。 必要な `PublicClientApplication` 構成は 1 つだけです。
    - リソース識別子に加えて、スコープを使用してアクセス トークンを要求するためのサポート。
    - 段階的な同意をサポート。 開発者は、アプリの登録時に含まれていない機能を含め、ユーザーがアプリの機能にアクセスするにつれてスコープを要求できます。
    - 権限は実行時に検証されなくなりました。 代わりに、開発者は開発中に "既知の機関" の一覧を宣言します。
- トークン API の変更:
    - ADAL では、 `AcquireToken()` は最初にサイレント要求を行います。 それができない場合は、対話型要求を行います。 この動作により、一部の開発者は `AcquireToken`のみに依存し、その結果、ユーザーは予期せず資格情報の入力を求められる場合がありました。 MSAL では、ユーザーが UI プロンプトを受け取るタイミングについて開発者が意図的に行う必要があります。
        - `AcquireTokenSilent` は常に、成功または失敗するサイレント要求になります。
        - `AcquireToken` は常に、UI 経由でユーザーにプロンプトを表示する要求になります。
- MSAL では、既定のブラウザーまたは埋め込み Web ビューからのサインインがサポートされています。
    - 既定では、デバイスの既定のブラウザーが使用されます。 これにより、MSAL は、サインインしている 1 つ以上のアカウントに既に存在する可能性がある認証状態 (Cookie) を使用できます。 認証状態が存在しない場合、MSAL による承認時に認証を行うと、同じブラウザーで使用される他の Web アプリケーションの利点のために認証状態 (Cookie) が作成されます。
- 新しい例外モデル:
    - 例外により、発生したエラーの種類と、開発者がそれを解決するために何を行う必要があるかがより明確に定義されます。
- MSAL では、 `AcquireToken` 呼び出しと `AcquireTokenSilent` 呼び出しのパラメーター オブジェクトがサポートされています。
- MSAL では、次の宣言型構成がサポートされています。
    - クライアント ID、リダイレクト URI。
    - 埋め込みブラウザーと既定のブラウザー
    - 当局
    - 読み取りと接続タイムアウトなどの HTTP 設定

### アプリの登録と MSAL への移行

MSAL を使用するために既存のアプリ登録を変更する必要はありません。 増分/プログレッシブ同意を利用する場合は、登録を確認して、増分的に要求する特定のスコープを特定することが必要になる場合があります。 スコープと増分同意の詳細については、以下を参照してください。

ポータルでアプリを登録すると、[ **API のアクセス許可** ] タブが表示されます。これにより、アプリが現在アクセスを要求するように構成されている API とアクセス許可 (スコープ) の一覧が表示されます。 また、各 API アクセス許可に関連付けられているスコープ名の一覧も表示されます。

#### ユーザーの同意

ADAL と Azure AD v1.0 エンドポイントでは、ユーザーが所有するリソースに対するユーザーの同意が最初の使用時に付与されました。 MSAL とMicrosoft ID プラットフォームを使用すると、同意を段階的に要求できます。 増分同意は、ユーザーが高い特権を考慮する可能性があるアクセス許可に役立ちます。また、アクセス許可が必要な理由について明確な説明が提供されていない場合は疑問を持つ場合があります。 ADAL では、これらのアクセス許可により、ユーザーがアプリへのサインインを中止した可能性があります。

Tip

増分同意を使用して、アプリにアクセス許可が必要な理由に関する追加のコンテキストをユーザーに提供します。

#### 管理者の同意

組織の管理者は、組織のすべてのメンバーに代わってアプリケーションに必要なアクセス許可に同意できます。 一部の組織では、管理者のみがアプリケーションに同意できます。 管理者の同意を得るには、アプリケーションによって使用されるすべての API アクセス許可とスコープをアプリの登録に含める必要があります。

Tip

アプリの登録に含まれていないものに対して MSAL を使用してスコープを要求できますが、ユーザーがアクセス許可を付与できるすべてのリソースとスコープを含むようにアプリの登録を更新することをお勧めします。

### リソース ID からスコープへの移行

#### 初回使用時に認証を行い、必要なすべての権限の承認を要求します

現在 ADAL を使用していて、増分同意を使用する必要がない場合、MSAL の使用を開始する最も簡単な方法は、新しい`acquireToken` オブジェクトを使用して`AcquireTokenParameter`要求を行い、リソース ID 値を設定することです。

Caution

スコープとリソース ID の両方を設定することはできません。両方を設定しようとすると、 `IllegalArgumentException`が発生します。

これにより、使用されているのと同じ v1 動作が発生します。 アプリの登録で要求されたすべてのアクセス許可は、最初の操作中にユーザーから要求されます。

#### 必要に応じてのみ認証し、アクセス許可を要求する

増分同意を利用するには、アプリの登録からアプリが使用するアクセス許可 (スコープ) の一覧を作成し、次に基づいて 2 つのリストに整理します。

- サインイン時にユーザーがアプリと最初にやり取りするときに要求するスコープ。
- ユーザーに説明する必要があるアプリの重要な機能に関連付けられているアクセス許可。

スコープを整理したら、トークンを要求するリソース (API) ごとに各リストを整理します。 および、同時にユーザーに承認してもらいたいその他のスコープ。

MSAL への要求を行うために使用される parameters オブジェクトは、次をサポートします。

- `Scope`: アクセス トークンの承認と受信を要求するスコープの一覧。
- `ExtraScopesToConsent`: 別のリソースのアクセス トークンを要求している間に承認を要求するスコープの追加リスト。 このスコープの一覧を使用すると、ユーザー承認を要求する必要がある回数を最小限に抑えることができます。 つまり、ユーザーの承認または同意のプロンプトが少なくなります。

### AuthenticationContext から PublicClientApplications への移行

#### PublicClientApplication の構築

MSAL を使用する場合は、 `PublicClientApplication`をインスタンス化します。 このオブジェクトはアプリ ID をモデル化し、1 つ以上の機関に要求を行うために使用されます。 このオブジェクトを使用すると、クライアント ID、リダイレクト URI、既定の機関、デバイス ブラウザーと埋め込み Web ビュー、ログ レベルなどを使用するかどうかを構成します。

このオブジェクトは、ファイルとして指定するか、APK 内のリソースとして格納する JSON を使用して宣言によって構成できます。

このオブジェクトはシングルトンではありませんが、内部的には対話型要求とサイレント要求の両方に共有 `Executors` を使用します。

#### BtoB

ADAL では、アクセス トークンを要求するすべての組織に、 `AuthenticationContext`の個別のインスタンスが必要です。 MSAL では、これは要件ではなくなりました。 サイレントまたは対話型の要求の一部としてトークンを要求する機関を指定できます。

#### 機関検証から既知の機関への移行

MSAL には、機関の検証を有効または無効にするフラグがありません。 機関の検証は、ADAL および MSAL の初期リリースの機能であり、悪意のある可能性のある機関からコードがトークンを要求するのを防ぎます。 MSAL は、Microsoft に認識されている認証機関の一覧を取得し、その一覧を構成設定で指定した認証機関とマージします。

Tip

Azure Business to Consumer (B2C) ユーザーの場合は、機関の検証を無効にする必要がなくなりました。 代わりに、サポートされている各Azure AD B2C ポリシーを MSAL 構成の機関として含めます。 2025 年 5 月 1 日より、Azure AD B2C は新規のお客様による購入ができなくなります。 詳細については、FAQ の [「Azure AD B2C を引き続き購入できますか?」を](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) 参照してください。

Microsoft に認識されておらず、かつ構成に含まれていない認証機関を使用しようとした場合、`UnknownAuthorityException` が表示されます。

#### Logging

次のように、構成の一部としてログ記録を宣言的に構成できるようになりました。

```json
"logging": {
  "pii_enabled": false,
  "log_level": "WARNING",
  "logcat_enabled": true
}
```

### UserInfo からアカウントへの移行

ADAL では、 `AuthenticationResult` は、認証されたアカウントに関する情報を取得するために使用される `UserInfo` オブジェクトを提供します。 人間またはソフトウェア エージェントを意味する "user" という用語は、複数のアカウントを持つ 1 人のユーザー (人間またはソフトウェア エージェント) を一部のアプリがサポートしていることを伝えにくくする方法で適用されました。

銀行口座について考えてみましょう。 複数の金融機関で複数のアカウントを持っている可能性があります。 アカウントを開くと、お客様 (ユーザー) には、残高へのアクセス、支払いの請求などに使用される、ATM カードや PIN などの資格情報がアカウントごとに発行されます。 これらの資格情報は、発行した金融機関でのみ使用できます。

金融機関のアカウントと同様に、Microsoft ID プラットフォームのアカウントには資格情報を使用してアクセスします。 これらの資格情報は、Microsoftに登録されているか、Microsoftによって発行されます。 または、Microsoft が組織に代わって行う。

Microsoft ID プラットフォームが金融機関と異なる場合、この例では、Microsoft ID プラットフォームは、ユーザーが 1 つのアカウントとそれに関連付けられた資格情報を使用して、複数の個人と組織に属するリソースにアクセスできるようにするフレームワークを提供することです。 これは、ある銀行が発行したカードを、さらに別の金融機関で使用できるようなものです。 これは、問題のすべての組織がMicrosoft ID プラットフォームを使用しているために機能します。これにより、複数の組織で 1 つのアカウントを使用できます。 次に例を示します。

Sam は Contoso.com で機能しますが、Fabrikam.com に属Azure仮想マシンを管理します。 Sam が Fabrikam の仮想マシンを管理するには、それらにアクセスする権限が必要です。 このアクセス権を付与するには、Sam のアカウントを Fabrikam.com に追加し、仮想マシンを操作できるロールを自分のアカウントに付与します。 これは、Azure ポータルで行います。

Sam の Contoso.com アカウントを Fabrikam.com のメンバーに追加すると、Fabrikam.com の Microsoft Entra ID に Sam の新しいレコードが作成されます。 Microsoft Entra IDでの Sam のレコードは、ユーザー オブジェクトと呼ばれます。 この場合、そのユーザー オブジェクトは Contoso.com の Sam のユーザー オブジェクトを指します。 Sam の Fabrikam ユーザー オブジェクトは Sam のローカル表現であり、sam に関連付けられているアカウントに関する情報を Fabrikam.com のコンテキストに格納するために使用されます。 Contoso.com では、Sam のタイトルはシニア DevOps コンサルタントです。 Fabrikam では、Sam のタイトルは Contractor-Virtual Machines です。 Contoso.com では、Sam は仮想マシンを管理する責任も承認もされません。 Fabrikam.com では、それが彼の唯一の仕事の機能です。 ただし、Sam は追跡する資格情報のセットを 1 つだけ持っています。これは、Contoso.com によって発行された資格情報です。

`acquireToken`呼び出しが成功すると、後の`IAccount`要求で使用できる`acquireTokenSilent` オブジェクトへの参照が表示されます。

#### IMultiTenantAccount

アカウントが表されている各テナントのアカウントに関する要求にアクセスするアプリがある場合は、 `IAccount` オブジェクトを `IMultiTenantAccount`にキャストできます。 このインターフェイスは、テナント ID でキー指定された `ITenantProfiles`のマップを提供します。これにより、現在のアカウントを基準にして、トークンを要求した各テナントのアカウントに属する要求にアクセスできます。

`IAccount`および`IMultiTenantAccount`のルートにある要求には、常にホーム テナントからの要求が含まれます。 ホーム テナント内でトークンの要求をまだ行っていない場合、このコレクションは空になります。

### その他の変更

#### 新しい AuthenticationCallback を使用する

```java
// Existing ADAL Interface
public interface AuthenticationCallback<T> {

    /**
     * This will have the token info.
     *
     * @param result returns <T>
     */
    void onSuccess(T result);

    /**
     * Sends error information. This can be user related error or server error.
     * Cancellation error is AuthenticationCancelError.
     *
     * @param exc return {@link Exception}
     */
    void onError(Exception exc);
}
```

```java
// New Interface for Interactive AcquireToken
public interface AuthenticationCallback {

    /**
     * Authentication finishes successfully.
     *
     * @param authenticationResult {@link IAuthenticationResult} that contains the success response.
     */
    void onSuccess(final IAuthenticationResult authenticationResult);

    /**
     * Error occurs during the authentication.
     *
     * @param exception The {@link MsalException} contains the error code, error message and cause if applicable. The exception
     *                  returned in the callback could be {@link MsalClientException}, {@link MsalServiceException}
     */
    void onError(final MsalException exception);

    /**
     * Will be called if user cancels the flow.
     */
    void onCancel();
}

// New Interface for Silent AcquireToken
public interface SilentAuthenticationCallback {

    /**
     * Authentication finishes successfully.
     *
     * @param authenticationResult {@link IAuthenticationResult} that contains the success response.
     */
    void onSuccess(final IAuthenticationResult authenticationResult);

    /**
     * Error occurs during the authentication.
     *
     * @param exception The {@link MsalException} contains the error code, error message and cause if applicable. The exception
     *                  returned in the callback could be {@link MsalClientException}, {@link MsalServiceException} or
     *                  {@link MsalUiRequiredException}.
     */
    void onError(final MsalException exception);
}
```

### 新しい例外に移行する

ADAL には、 `AuthenticationException`という 1 種類の例外があります。これには、 `ADALError` 列挙値を取得するためのメソッドが含まれています。 MSAL には例外の階層があり、それぞれに固有の関連する特定のエラー コードのセットがあります。

| 例外 | 説明 |
| --- | --- |
| `MsalArgumentException` | 1 つ以上の入力引数が無効な場合にスローされます。 |
| `MsalClientException` | エラーがクライアント側の場合にスローされます。 |
| `MsalDeclinedScopeException` | 1 つ以上の要求したスコープがサーバーに拒否された場合にスローされます。 |
| `MsalException` | MSAL によってスローされた既定のチェック例外。 |
| `MsalIntuneAppProtectionPolicyRequiredException` | リソースで MAMCA 保護ポリシーが有効になっている場合にスローされます。 |
| `MsalServiceException` | エラーがサーバー側の場合にスローされます。 |
| `MsalUiRequiredException` | トークンをサイレントで更新できない場合にスローされます。 |
| `MsalUserCancelException` | ユーザーが認証フローをキャンセルした場合にスローされます。 |

#### ADALError から MsalException への変換

| ADAL でこれらのエラーをキャッチしている場合... | ...次の MSAL 例外をキャッチします。 |
| --- | --- |
| *同等の ADALError がない* | `MsalArgumentException` |
| - `ADALError.ANDROIDKEYSTORE_FAILED`<br>- `ADALError.AUTH_FAILED_USER_MISMATCH`<br>- `ADALError.DECRYPTION_FAILED`<br>- `ADALError.DEVELOPER_AUTHORITY_CAN_NOT_BE_VALIDED`<br>- `ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_INSTANCE`<br>- `ADALError.DEVELOPER_AUTHORITY_IS_NOT_VALID_URL`<br>- `ADALError.DEVICE_CONNECTION_IS_NOT_AVAILABLE`<br>- `ADALError.DEVICE_NO_SUCH_ALGORITHM`<br>- `ADALError.ENCODING_IS_NOT_SUPPORTED`<br>- `ADALError.ENCRYPTION_ERROR`<br>- `ADALError.IO_EXCEPTION`<br>- `ADALError.JSON_PARSE_ERROR`<br>- `ADALError.NO_NETWORK_CONNECTION_POWER_OPTIMIZATION`<br>- `ADALError.SOCKET_TIMEOUT_EXCEPTION` | `MsalClientException` |
| *同等の ADALError がない* | `MsalDeclinedScopeException` |
| - `ADALError.APP_PACKAGE_NAME_NOT_FOUND`<br>- `ADALError.BROKER_APP_VERIFICATION_FAILED`<br>- `ADALError.PACKAGE_NAME_NOT_FOUND` | `MsalException` |
| *同等の ADALError がない* | `MsalIntuneAppProtectionPolicyRequiredException` |
| - `ADALError.SERVER_ERROR`<br>- `ADALError.SERVER_INVALID_REQUEST` | `MsalServiceException` |
| - `ADALError.AUTH_REFRESH_FAILED_PROMPT_NOT_ALLOWED` | `MsalUiRequiredException` |
| *同等の ADALError がない* | `MsalUserCancelException` |

#### ADAL のログ記録から MSAL のログ記録へ

```java
// Legacy Interface
    StringBuilder logs = new StringBuilder();
    Logger.getInstance().setExternalLogger(new ILogger() {
            @Override
            public void Log(String tag, String message, String additionalMessage, LogLevel logLevel, ADALError errorCode) {
                logs.append(message).append('\n');
            }
        });
```

```java
// New interface
  StringBuilder logs = new StringBuilder();
  Logger.getInstance().setExternalLogger(new ILoggerCallback() {
      @Override
      public void log(String tag, Logger.LogLevel logLevel, String message, boolean containsPII) {
          logs.append(message).append('\n');
      }
  });

// New Log Levels:
public enum LogLevel
{
    /**
     * Error level logging.
     */
    ERROR,
    /**
     * Warning level logging.
     */
    WARNING,
    /**
     * Info level logging.
     */
    INFO,
    /**
     * Verbose level logging.
     */
    VERBOSE
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/msal-android-b2c"} -->
## Azure AD B2C (MSAL Android)

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/msal-android-b2c
- Service: msal / msal-android
- Article date: 2023-09-18
- Summary: Android 用 Microsoft Authentication Library (MSAL) で Azure AD B2C を使用する場合の具体的な考慮事項について説明します。Android)

Microsoft Authentication Library (MSAL) を使用すると、アプリケーション開発者は[、Azure Active Directory B2C (Azure AD B2C)](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/) を使用して、ソーシャル ID とローカル ID でユーザーを認証できます。 AZURE AD B2C は ID 管理サービスです。 これを使用して、顧客がアプリケーションを使用する際のプロファイルのサインアップ、サインイン、および管理方法をカスタマイズおよび制御します。

Important

2025 年 5 月 1 日より、Azure AD B2C は新規のお客様向けに購入できなくなります。 詳細については、[AZURE AD B2C を引き続き購入できますか?](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale)に関する FAQ を参照してください。

### 互換性のあるauthorization\_user\_agentの選択

B2C ID 管理システムは、Google、Facebook、Twitter、Amazon などの多くのソーシャル アカウント プロバイダーによる認証をサポートしています。 アプリでこのようなアカウントの種類をサポートする予定の場合は、一部の外部 ID プロバイダーでの WebView ベースの認証の使用が禁止されているため、マニフェストの`DEFAULT`を指定するときに、`BROWSER`または[`authorization_user_agent`](https://learn.microsoft.com/ja-jp/entra/msal/android/msal-configuration#authorization_user_agent)の値を使用するように MSAL パブリック クライアント アプリケーションを構成することをお勧めします。 さらに、WebView ベースの認証では、現在、otpauth:// を含むカスタム URI スキームの処理もサポートされていません。 アプリケーションの認証フローでこのような URI をホストしてリダイレクトする場合は、DEFAULT または BROWSER をauthorization\_user\_agentとして使用することをお勧めします。

### 既知の認証局とリダイレクト URI を設定する

Android 用 MSAL では、B2C ポリシー (ユーザー体験) が個々の機関として構成されます。

次の 2 つのポリシーを持つ B2C アプリケーションを指定します。

- サインアップ/サインイン
    - `B2C_1_SISOPolicy` と呼ばれる
- プロファイルの編集
    - `B2C_1_EditProfile`と呼ばれる

アプリの構成ファイルでは、2 つの `authorities`が宣言されます。 ポリシーごとに 1 つ。 各権限の `type` プロパティが `B2C`。

>
> 注: B2C アプリケーションでは、 `account_mode` を **MULTIPLE** に設定する必要があります。 [複数アカウントのパブリック クライアント アプリ](https://learn.microsoft.com/ja-jp/entra/msal/android/single-multi-account#multiple-account-public-client-application)の詳細については、ドキュメントを参照してください。

#### `app/src/main/res/raw/msal_config.json`

```json
{
  "client_id": "<your_client_id_here>",
  "redirect_uri": "<your_redirect_uri_here>",
  "account_mode" : "MULTIPLE",
  "authorization_user_agent" : "DEFAULT",
  "authorities": [
    {
      "type": "B2C",
      "authority_url": "https://contoso.b2clogin.com/tfp/contoso.onmicrosoft.com/B2C_1_SISOPolicy/",
      "default": true
    },
    {
      "type": "B2C",
      "authority_url": "https://contoso.b2clogin.com/tfp/contoso.onmicrosoft.com/B2C_1_EditProfile/"
    }
  ]
}
```

`redirect_uri` は、アプリ構成で登録する必要があります。さらに、[承認コード付与フロー](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/authorization-code-flow)中にリダイレクトをサポートするために、`AndroidManifest.xml` に登録する必要があります。

### IPublicClientApplication を初期化する

`IPublicClientApplication` は、アプリケーション構成を非同期的に解析できるようにファクトリ メソッドによって構築されます。

```java
PublicClientApplication.createMultipleAccountPublicClientApplication(
    context, // Your application Context
    R.raw.msal_config, // Id of app JSON config
    new IPublicClientApplication.ApplicationCreatedListener() {
        @Override
        public void onCreated(IMultipleAccountPublicClientApplication pca) {
            // Application has been initialized.
        }

        @Override
        public void onError(MsalException exception) {
            // Application could not be created.
            // Check Exception message for details.
        }
    }
);
```

### トークンを対話形式で取得する

MSAL を使用して対話形式でトークンを取得するには、 `AcquireTokenParameters` インスタンスをビルドし、 `acquireToken` メソッドに提供します。 以下のトークン要求では、 `default` 機関が使用されます。

```java
IMultipleAccountPublicClientApplication pca = ...; // Initialization not shown

AcquireTokenParameters parameters = new AcquireTokenParameters.Builder()
    .startAuthorizationFromActivity(activity)
    .withScopes(Arrays.asList("https://contoso.onmicrosoft.com/contosob2c/read")) // Provide your registered scope here
    .withPrompt(Prompt.LOGIN)
    .callback(new AuthenticationCallback() {
        @Override
        public void onSuccess(IAuthenticationResult authenticationResult) {
            // Token request was successful, inspect the result
        }

        @Override
        public void onError(MsalException exception) {
            // Token request was unsuccessful, inspect the exception
        }

        @Override
        public void onCancel() {
            // The user cancelled the flow
        }
    }).build();

pca.acquireToken(parameters);
```

### トークンを自動的に更新する

MSAL を使用してトークンをサイレントモードで取得するには、 `AcquireTokenSilentParameters` インスタンスをビルドし、 `acquireTokenSilentAsync` メソッドに提供します。 `acquireToken` メソッドとは異なり、トークンをサイレントで取得するには、`authority`を指定する必要があります。

```java
IMultipleAccountPublicClientApplication pca = ...; // Initialization not shown
AcquireTokenSilentParameters parameters = new AcquireTokenSilentParameters.Builder()
    .withScopes(Arrays.asList("https://contoso.onmicrosoft.com/contosob2c/read")) // Provide your registered scope here
    .forAccount(account)
    // Select a configured authority (policy), mandatory for silent auth requests
    .fromAuthority("https://contoso.b2clogin.com/tfp/contoso.onmicrosoft.com/B2C_1_SISOPolicy/")
    .callback(new SilentAuthenticationCallback() {
        @Override
        public void onSuccess(IAuthenticationResult authenticationResult) {
            // Token request was successful, inspect the result
        }

        @Override
        public void onError(MsalException exception) {
            // Token request was unsuccessful, inspect the exception
        }
    })
    .build();

pca.acquireTokenSilentAsync(parameters);
```

### ポリシーを指定する

B2C のポリシーは別々の機関として表されるため、既定以外のポリシーを呼び出す場合は、`fromAuthority`または`acquireToken`パラメーターを作成するときに`acquireTokenSilent`句を指定します。 例えば次が挙げられます。

```java
AcquireTokenParameters parameters = new AcquireTokenParameters.Builder()
    .startAuthorizationFromActivity(activity)
    .withScopes(Arrays.asList("https://contoso.onmicrosoft.com/contosob2c/read")) // Provide your registered scope here
    .withPrompt(Prompt.LOGIN)
    .callback(...) // provide callback here
    .fromAuthority("<url_of_policy_defined_in_configuration_json>")
    .build();
```

### パスワード変更ポリシーの処理

ローカル アカウントのサインアップまたはサインイン ユーザー フローに [**パスワードを忘れた**場合] が表示されます。 リンク。 このリンクをクリックしても、パスワード リセット ユーザー フローは自動的にトリガーされません。

代わりに、エラー コード `AADB2C90118` がアプリに返されます。 アプリでは、パスワードをリセットする特定のユーザー フローを実行して、このエラー コードを処理する必要があります。

パスワード リセット エラー コードをキャッチするために、 `AuthenticationCallback`内で次の実装を使用できます。

```java
new AuthenticationCallback() {

    @Override
    public void onSuccess(IAuthenticationResult authenticationResult) {
        // ..
    }

    @Override
    public void onError(MsalException exception) {
        final String B2C_PASSWORD_CHANGE = "AADB2C90118";

        if (exception.getMessage().contains(B2C_PASSWORD_CHANGE)) {
            // invoke password reset flow
        }
    }

    @Override
    public void onCancel() {
        // ..
    }
}
```

### IAuthenticationResult を使用する

トークンの取得が成功すると、 `IAuthenticationResult` オブジェクトになります。 これには、アクセス トークン、ユーザー要求、およびメタデータが含まれます。

#### アクセス トークンと関連プロパティを取得する

```java
// Get the raw bearer token
String accessToken = authenticationResult.getAccessToken();

// Get the scopes included in the access token
String[] accessTokenScopes = authenticationResult.getScope();

// Gets the access token's expiry
Date expiry = authenticationResult.getExpiresOn();

// Get the tenant for which this access token was issued
String tenantId = authenticationResult.getTenantId();
```

#### 承認されたアカウントを取得する

```java
// Get the account from the result
IAccount account = authenticationResult.getAccount();

// Get the id of this account - note for B2C, the policy name is a part of the id
String id = account.getId();

// Get the IdToken Claims
//
// For more information about B2C token claims, see reference documentation
// https://learn.microsoft.com/azure/active-directory-b2c/active-directory-b2c-reference-tokens
Map<String, ?> claims = account.getClaims();

// Get the 'preferred_username' claim through a convenience function
String username = account.getUsername();

// Get the tenant id (tid) claim through a convenience function
String tenantId = account.getTenantId();
```

#### IdToken クレーム

IdToken で返される要求は、MSAL ではなく、セキュリティ トークン サービス (STS) によって設定されます。 使用される ID プロバイダー (IdP) によっては、一部の要求が存在しない場合があります。 一部の IdP は現在、 `preferred_username` 要求を提供していません。 この要求はキャッシュのために MSAL によって使用されるため、プレースホルダー値 `MISSING FROM THE TOKEN RESPONSE`が代わりに使用されます。 B2C IdToken 要求の詳細については、「Azure Active Directory [B2C のトークンの概要](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/tokens-overview#claims)」を参照してください。

### アカウントとポリシーの管理

B2C は、各ポリシーを個別の機関として扱います。 したがって、各ポリシーから返されるアクセス トークン、更新トークン、ID トークンは交換できません。 つまり、各ポリシーは、他のポリシーの呼び出しにトークンを使用できない個別の `IAccount` オブジェクトを返します。

各ポリシーは、各ユーザーのキャッシュに `IAccount` を追加します。 ユーザーがアプリケーションにサインインし、2 つのポリシーを呼び出すと、2 つの `IAccount`があります。 このユーザーをキャッシュから削除するには、ポリシーごとに `removeAccount()` を呼び出す必要があります。

`acquireTokenSilent`を使用してポリシーのトークンを更新する場合は、ポリシーの以前の呼び出しから`IAccount`に返されたものと同じ`AcquireTokenSilentParameters`を指定します。 別のポリシーによって返されたアカウントを指定すると、エラーが発生します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/msal-configuration"} -->
## MSAL Android 構成ファイル

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/msal-configuration
- Service: msal / msal-android
- Article date: 2023-09-12
- Summary: Microsoft Entra IDでのアプリケーションの構成を表す Android Microsoft Authentication Library (MSAL) 構成ファイルの概要。

Android Microsoft Authentication Library (MSAL) には、既定の機関、使用する機関などのパブリック クライアント アプリの動作を定義するためにカスタマイズする既定の[構成 JSON ファイル](https://github.com/AzureAD/microsoft-authentication-library-for-android/blob/dev/msal/src/main/res/raw/msal_default_config.json)が付属しています。

この記事では、構成ファイルのさまざまな設定と、MSAL ベースのアプリで使用する構成ファイルを指定する方法について説明します。

### 構成設定

#### 一般設定

| 財産 | データの種類 | 必須 | メモ |
| --- | --- | --- | --- |
| `client_id` | String | Yes | [[アプリケーションの登録\] ページ](https://portal.azure.com/#blade/Microsoft_AAD_RegisteredApps/ApplicationsListBlade)からアプリのクライアント ID を取得する |
| `redirect_uri` | String | Yes | アプリケーション[登録ページ](https://portal.azure.com/#blade/Microsoft_AAD_RegisteredApps/ApplicationsListBlade)からのアプリのリダイレクト URI |
| `broker_redirect_uri_registered` | ブール値 | No | 使用可能な値: `true`、 `false` |
| `authorities` | List&lt;Authority&gt; | No | アプリに必要な機関の一覧 |
| `authorization_user_agent` | AuthorizationAgent (enum) | No | 使用可能な値: `DEFAULT`、 `BROWSER`、 `WEBVIEW` |
| `http` | HttpConfiguration | No | `HttpUrlConnection``connect_timeout`の構成と`read_timeout` |
| `logging` | ロギング構成 | No | ログの詳細のレベルを指定します。 オプションの構成には、ブール値を受け取る`pii_enabled`と、`ERROR`、`WARNING`、`INFO`、または`VERBOSE`を受け取る`log_level`が含まれます。 |

#### クライアント ID

アプリケーションを登録したときに作成されたクライアント ID またはアプリ ID。

#### リダイレクトURI (redirect\_uri)

アプリケーションの登録時に登録したリダイレクト URI。 リダイレクト URI がブローカー アプリに対する場合は、 [パブリック クライアント アプリのリダイレクト URI](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-application-configuration#redirect-uri-for-public-client-apps) を参照して、ブローカー アプリの正しいリダイレクト URI 形式を使用していることを確認します。

#### broker\_redirect\_uri\_registered

ブローカー認証を使用する場合は、 `broker_redirect_uri_registered` プロパティを `true` に設定する必要があります。 ブローカー認証シナリオでは、「 [パブリック クライアント アプリのリダイレクト URI](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-application-configuration#redirect-uri-for-public-client-apps)」で説明されているように、アプリケーションがブローカーと通信するための正しい形式でない場合、アプリケーションはリダイレクト URI を検証し、起動時に例外をスローします。

#### authorities

ユーザーが知り、信頼している機関の一覧。 MSAL は、ここに記載されている機関に加えて、Microsoftクエリを実行して、Microsoft知られているクラウドと機関の一覧を取得します。 この機関の一覧で、機関の種類と、アプリの登録に基づいてアプリの対象ユーザーと一致する必要がある追加の省略可能なパラメーター ( `"audience"`など) を指定します。 権限の一覧の例を次に示します。

```javascript
// Example AzureAD and Personal Microsoft Account
{
    "type": "AAD",
    "audience": {
        "type": "AzureADandPersonalMicrosoftAccount"
    },
    "default": true // Indicates that this is the default to use if not provided as part of the acquireToken call
},
// Example AzureAD My Organization
{
    "type": "AAD",
    "audience": {
        "type": "AzureADMyOrg",
        "tenant_id": "contoso.com" // Provide your specific tenant ID here
    }
},
// Example AzureAD Multiple Organizations
{
    "type": "AAD",
    "audience": {
        "type": "AzureADMultipleOrgs"
    }
},
//Example PersonalMicrosoftAccount
{
    "type": "AAD",
    "audience": {
        "type": "PersonalMicrosoftAccount"
    }
}
```

##### Microsoft Entra機関と対象ユーザーを Microsoft ID プラットフォーム エンドポイントにマップする

| タイプ | オーディエンス | テナント ID | Authority\_Url | 結果のエンドポイント | メモ |
| --- | --- | --- | --- | --- | --- |
| Microsoft Entra ID | Azure AD と個人用 Microsoft アカウント |  |  | `https://login.microsoftonline.com/common` | `common` は、アカウントがある場所のテナント エイリアスです。 特定のMicrosoft Entra テナントやMicrosoft アカウント システムなど。 |
| Microsoft Entra ID | AzureADMyOrg | contoso.com |  | `https://login.microsoftonline.com/contoso.com` | トークンを取得できるのは、contoso.com に存在するアカウントだけです。 検証済みドメインまたはテナント GUID は、テナント ID として使用できます。 |
| Microsoft Entra ID | AzureADMultipleOrgs |  |  | `https://login.microsoftonline.com/organizations` | このエンドポイントでは、Microsoft Entraアカウントのみを使用できます。 Microsoft アカウントは、組織のメンバーにすることができます。 組織内のリソースのMicrosoft アカウントを使用してトークンを取得するには、トークンを取得する組織のテナントを指定します。 |
| Microsoft Entra ID | 個人用 Microsoft アカウント |  |  | `https://login.microsoftonline.com/consumers` | このエンドポイントを使用できるのは、Microsoft アカウントのみです。 |
| B2C |  |  | 結果のエンドポイントを参照してください | `https://login.microsoftonline.com/tfp/contoso.onmicrosoft.com/B2C_1_SISOPolicy/` | トークンを取得できるのは、contoso.onmicrosoft.com テナントに存在するアカウントだけです。 この例では、B2C ポリシーは機関 URL パスの一部です。 |

Note

MSAL で機関の検証を有効または無効にすることはできません。 機関は、構成で指定された開発者として知られているか、メタデータを使用してMicrosoftすることが知られています。 MSAL が不明な機関に対するトークンの要求を受け取った場合、型の`MsalClientException``UnknownAuthority`結果になります。 ブローカー認証は、Azure AD B2C では機能しません。

##### Authority プロパティ

| 財産 | データの種類 | 必須 | メモ |
| --- | --- | --- | --- |
| `type` | String | Yes | 対象ユーザーまたはアカウントの種類をアプリのターゲットに反映します。 使用可能な値: `AAD`、 `B2C` |
| `audience` | Object | No | type=`AAD` の場合にのみ適用されます。 アプリがターゲットとする ID を指定します。 アプリ登録の値を使用する |
| `authority_url` | String | Yes | type=`B2C` の場合にのみ必要です。 type=`AAD` の場合は省略可能です。 アプリで使用する機関の URL またはポリシーを指定します |
| `default` | boolean | Yes | 1 つ以上の権限が指定されている場合は、1 つの `"default":true` が必要です。 |

##### 対象ユーザーのプロパティ

| 財産 | データの種類 | 必須 | メモ |
| --- | --- | --- | --- |
| `type` | String | Yes | アプリがターゲットにする対象ユーザーを指定します。 使用可能な値: `AzureADandPersonalMicrosoftAccount`、 `PersonalMicrosoftAccount`、 `AzureADMultipleOrgs`、 `AzureADMyOrg` |
| `tenant_id` | String | Yes | `"type":"AzureADMyOrg"`する場合にのみ必要です。 その他の `type` 値の場合は省略可能です。 これには、 `contoso.com`などのテナント ドメイン、または次のようなテナント ID を指定できます。 `aaaabbbb-0000-cccc-1111-dddd2222eeee` |

#### authorization\_user\_agent

アカウントにサインインするとき、またはリソースへのアクセスを承認するときに、埋め込み Web ビューを使用するか、デバイス上の既定のブラウザーを使用するかを示します。

指定できる値

- `DEFAULT`: システム ブラウザーを優先します。 ブラウザーがデバイスで使用できない場合は、埋め込み Web ビューを使用します。
- `WEBVIEW`: 埋め込み Web ビューを使用します。
- `BROWSER`: デバイスの既定のブラウザーを使用します。

#### multiple\_clouds\_supported

複数の国内クラウドをサポートするクライアントの場合は、 `true`を指定します。 その後、Microsoft ID プラットフォームは、承認とトークンの引き換え中に、適切な国内クラウドに自動的にリダイレクトされます。 `AuthenticationResult`に関連付けられている機関を調べることで、サインインアカウントの国内クラウドを特定できます。 `AuthenticationResult`では、トークンを要求するリソースの国内クラウド固有のエンドポイント アドレスは提供されません。

#### broker\_redirect\_uri\_registered

Microsoft Identity Broker と互換性のあるブローカー内リダイレクト URI を使用しているかどうかを示すブール値。 アプリ内でブローカーを使用しない場合は、 `false` に設定します。

対象ユーザーを `"MicrosoftPersonalAccount"` に設定して Microsoft Entra 機関を使用している場合、ブローカーは使用されません。

#### http

HTTP タイムアウトのグローバル設定を構成します。次に例を示します。

| 財産 | データの種類 | 必須 | メモ |
| --- | --- | --- | --- |
| `connect_timeout` | int | No | ミリ秒単位の時間 |
| `read_timeout` | int | No | ミリ秒単位の時間 |

#### ログ

ログ記録用のグローバル設定を次に示します。

| 財産 | データの種類 | 必須 | メモ |
| --- | --- | --- | --- |
| `pii_enabled` | boolean | No | 個人データを出力するかどうか |
| `log_level` | 文字列 | No | 出力するログ メッセージ。 サポートされるログ レベルには、 `ERROR`、`WARNING`、`INFO`、 `VERBOSE`が含まれます。 |
| `logcat_enabled` | boolean | No | ログインターフェイスに加えてcatをログに出力するかどうか |

#### account\_mode

アプリ内で一度に使用できるアカウントの数を指定します。 指定できる値は次のとおりです。

- `MULTIPLE` (既定値)
- `SINGLE`

この設定と一致しないアカウント モードを使用して `PublicClientApplication` を構築すると、例外が発生します。

1 つのアカウントと複数のアカウントの違いの詳細については、「 [単一アカウントアプリと複数アカウント アプリ](https://learn.microsoft.com/ja-jp/entra/msal/android/single-multi-account)」を参照してください。

#### browser\_safelist

MSAL と互換性のあるブラウザーの許可リスト。 これらのブラウザーは、カスタム 意図へのリダイレクトを正しく処理します。 この一覧に追加できます。 既定値は、次に示す既定の構成で提供されます。 ``

### 既定の MSAL 構成ファイル

MSAL に付属する既定の MSAL 構成を次に示します。 [GitHub](https://github.com/AzureAD/microsoft-authentication-library-for-android/blob/dev/msal/src/main/res/raw/msal_default_config.json)で最新バージョンを確認できます。

この構成は、指定した値によって補完されます。 指定した値は既定値よりも優先されます。

```javascript
{
  "authorities": [
    {
      "type": "AAD",
      "audience": {
        "type": "AzureADandPersonalMicrosoftAccount"
      },
      "default": true
    }
  ],
  "authorization_user_agent": "DEFAULT",
  "multiple_clouds_supported": false,
  "broker_redirect_uri_registered": false,
  "http": {
    "connect_timeout": 10000,
    "read_timeout": 30000
  },
  "logging": {
    "pii_enabled": false,
    "log_level": "WARNING",
    "logcat_enabled": false
  },
  "shared_device_mode_supported": false,
  "account_mode": "MULTIPLE",
  "browser_safelist": [
    {
      "browser_package_name": "com.android.chrome",
      "browser_signature_hashes": [
        "7fmdu...2NDJg=="
      ],
      "browser_use_customTab" : true,
      "browser_version_lower_bound": "45"
    },
    {
      "browser_package_name": "com.android.chrome",
      "browser_signature_hashes": [
        "7fmdu...2NDJg=="
      ],
      "browser_use_customTab" : false
    },
    {
      "browser_package_name": "org.mozilla.firefox",
      "browser_signature_hashes": [
        "2gCe6...idpVQ=="
      ],
      "browser_use_customTab" : false
    },
    {
      "browser_package_name": "org.mozilla.firefox",
      "browser_signature_hashes": [
        "2gCe6...idpVQ=="
      ],
      "browser_use_customTab" : true,
      "browser_version_lower_bound": "57"
    },
    {
      "browser_package_name": "com.sec.android.app.sbrowser",
      "browser_signature_hashes": [
        "ABi2f...4O1Xgg=="
      ],
      "browser_use_customTab" : true,
      "browser_version_lower_bound": "4.0"
    },
    {
      "browser_package_name": "com.sec.android.app.sbrowser",
      "browser_signature_hashes": [
        "ABi2f...O1Xgg=="
      ],
      "browser_use_customTab" : false
    },
    {
      "browser_package_name": "com.cloudmosa.puffinFree",
      "browser_signature_hashes": [
        "1WqG8...Mn8Ag=="
      ],
      "browser_use_customTab" : false
    },
    {
      "browser_package_name": "com.duckduckgo.mobile.android",
      "browser_signature_hashes": [
        "S5Av4...jAi4Q=="
      ],
      "browser_use_customTab" : false
    },
    {
      "browser_package_name": "com.explore.web.browser",
      "browser_signature_hashes": [
        "BzDzB...YHCag=="
      ],
      "browser_use_customTab" : false
    },

    {
      "browser_package_name": "com.ksmobile.cb",
      "browser_signature_hashes": [
        "lFDYx...7nouw=="
      ],
      "browser_use_customTab" : false
    },

    {
      "browser_package_name": "com.microsoft.emmx",
      "browser_signature_hashes": [
        "Ivy-R...A6fVQ=="
      ],
      "browser_use_customTab" : false
    },

    {
      "browser_package_name": "com.opera.browser",
      "browser_signature_hashes": [
        "FIJ3I...jWJWw=="
      ],
      "browser_use_customTab" : false
    },

    {
      "browser_package_name": "com.opera.mini.native",
      "browser_signature_hashes": [
        "TOTyH...mmUYQ=="
      ],
      "browser_use_customTab" : false
    },

    {
      "browser_package_name": "mobi.mgeek.TunnyBrowser",
      "browser_signature_hashes": [
        "RMVoX...bkyyQ=="
      ],
      "browser_use_customTab" : false
    },

    {
      "browser_package_name": "org.mozilla.focus",
      "browser_signature_hashes": [
        "L72dT...q0oYA=="
      ],
      "browser_use_customTab" : false
    }
  ]
}
```

### 基本的な構成の例

次の例は、クライアント ID、リダイレクト URI、ブローカー リダイレクトを登録するかどうか、および権限の一覧を指定する基本的な構成を示しています。

```javascript
{
  "client_id" : "00001111-aaaa-2222-bbbb-3333cccc4444",
  "redirect_uri" : "msauth://com.microsoft.identity.client.sample.local/1wIqXSqBj7w%2Bh11ZifsnqwgyKrY%3D",
  "broker_redirect_uri_registered": true,
  "authorities" : [
    {
      "type": "AAD",
      "audience": {
        "type": "AzureADandPersonalMicrosoftAccount"
      }
      "default": true
    }
  ]
}
```

### 構成ファイルの使用方法

1. 構成ファイルを作成します。 `res/raw/auth_config.json`でカスタム構成ファイルを作成することをお勧めします。 しかし、あなたはあなたが望む任意の場所にそれを置くことができます。
2. `PublicClientApplication`を構築するときに、構成を検索する場所を MSAL に指示します。 例えば次が挙げられます。

    ```java
    //On Worker Thread
    IMultipleAccountPublicClientApplication sampleApp = null; 
    sampleApp = new PublicClientApplication.createMultipleAccountPublicClientApplication(getApplicationContext(), R.raw.auth_config);
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/prompt-enumeration"} -->
## プロンプト列挙

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/prompt-enumeration
- Service: msal / msal-android
- Article date: 2024-01-24
- Summary: プロンプト列挙について学ぶ

MSAL 1.0 では、認証要求に関する OpenId Connect 仕様に合わせて、列挙 UIBehavior の名前が Prompt に変更されました。 「[認証要求」](https://openid.net/specs/openid-connect-core-1_0.html#AuthRequest)を参照してください。

Android 用 MSAL では、Prompt 列挙型内で次の値がサポートされています。

| 名前 | 説明 |
| --- | --- |
| アカウントを選択 | ユーザーが、権限を持つアクティブなセッションを持つアカウントを選択できるようにする場合に使用します。 |
| LOGIN | ユーザーが再び明示的に認証する場合に使用します。 |
| 同意 | ユーザーが再び明示的に同意する場合に使用します。 ユーザーが既存の可能性のある同意レコードを更新して、追加のスコープ/アクセス許可を含める場合に便利です。 |
| 必要な場合 | 適切な動作を権限を持つ側に決定させる場合に使用します。 アクティブなセッションがない場合は認証します。 複数のセッションがある場合は、アカウントを選択します。 同意が必要な場合は同意します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/shared-devices"} -->
## Android デバイスの共有デバイス モード

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/shared-devices
- Service: msal / msal-android
- Article date: 2024-09-03
- Summary: 共有デバイス モードを有効にして、現場担当者が Android デバイスを共有できるようにする方法について説明します

小売関係者、フライト クルー、フィールド サービス ワーカーなどの現場担当者は、多くの場合、共有モバイル デバイスを使用して作業を実行します。 これらの共有デバイスは、ユーザーが自分のパスワードや PIN を意図的に共有したり、共有デバイス上の顧客データやビジネス データにアクセスしたりする場合に、セキュリティ 上のリスクを引き出す可能性があります。

[共有デバイス モード](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-shared-devices) では、従業員がデバイスを安全に共有できるように Android 8.0 以降のデバイスを構成できます。 従業員は 1 回サインインして、この機能をサポートするすべてのアプリにシングル サインオン (SSO) を行い、情報にすばやくアクセスできます。 シフトまたはタスクの完了後に従業員がサインアウトすると、デバイスとサポートされているすべてのアプリケーションから自動的にサインアウトされ、デバイスは次のユーザーに対応できるようになります。

共有デバイス モード機能を利用するには、アプリ開発者とクラウド デバイス管理者が連携して作業します。

1. **デバイス管理者は、**デバイスを手動で共有するか、Microsoft Intuneなどのモバイル デバイス管理 (MDM) プロバイダーを使用してデバイスを準備します。 推奨されるオプションは、MDM を使用することです。これにより、ゼロタッチ プロビジョニングを使用して共有デバイス モードで大規模にデバイスをセットアップできます。 MDM は[、Microsoft Authenticator アプリ](https://support.microsoft.com/account-billing/how-to-use-the-microsoft-authenticator-app-9783c865-0308-42fb-a519-8cf666fe0acc)をデバイスにプッシュし、デバイスへのマネージド構成の更新を通じて各デバイスの "共有モード" をオンにします。 この共有モード設定は、デバイスでサポートされているアプリの動作を変更します。 MDM プロバイダーからのこの構成は、デバイスの共有デバイス モードを設定し、Authenticator アプリを使用して共有デバイスの登録をトリガーします。
2. **アプリケーション開発者は** 、次のシナリオを処理するために、単一アカウント アプリを作成します (複数アカウント アプリは共有デバイス モードではサポートされていません)。

    - サポートされているアプリケーションを使用して、デバイス全体でユーザーをサインインする
    - サポートされているアプリケーションを使用して、デバイス全体でユーザーをサインアウトする
    - デバイスの状態を照会して、アプリケーションが共有デバイス モードのデバイス上にあるかどうかを判断する
    - ユーザーのデバイスの状態を照会して、前回の使用以降のアプリケーションの変更を確認します

    共有デバイス モードのサポートは、アプリケーションの機能アップグレードと見なす必要があり、複数のユーザー間で同じデバイスが使用されている環境での導入を増やすのに役立ちます。

    Important

    Android で共有デバイス モードをサポートするMicrosoftアプリケーションは、変更を必要とせず、共有デバイス モードに付属する利点を得るためにデバイスにインストールする必要があります。

### 共有デバイス モードでデバイスを設定する

共有デバイス モードをサポートするように Android デバイスを構成するには、Android OS 8.0 以降を実行している必要があります。 また、デバイスは、工場出荷時の設定にリセットするか、Microsoft アプリおよび共有デバイス モードが有効なその他すべてのアプリをアンインストールして再インストールすることによって、消去する必要があります。

Microsoft Intuneでは、Microsoft Entra共有デバイス モードのデバイスに対するゼロタッチ プロビジョニングがサポートされています。つまり、現場担当者からの最小限の操作でデバイスをセットアップして Intune に登録できます。 Microsoft Intuneを MDM として使用するときに共有デバイス モードでデバイスを設定するには、「[Microsoft Entra共有デバイス モードでデバイスの登録を設定](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/automated-device-enrollment-shared-device-mode/)する」を参照してください。

### 共有デバイス モードをサポートするように Android アプリケーションを変更する

ユーザーは、自分のデータが他のユーザーに漏えいしないよう、あなたが確実に守ることを期待しています。 次のセクションでは、変更が発生し、処理する必要があることをアプリケーションに示すために役立つシグナルを提供します。 アプリが使用されるたびにデバイス上のユーザーの状態を確認し、前のユーザーのデータをクリアする責任があります。 これには、マルチタスクでバックグラウンドから再読み込みされる場合も含まれます。 ユーザーの変更時に、前のユーザーのデータがクリアされていることと、アプリケーションに表示されているキャッシュされたデータが削除されていることを確認する必要があります。 共有デバイス モードをサポートするようにアプリを更新した後、お客様と会社でセキュリティ レビュー プロセスを実施することを強くお勧めします。

#### アプリケーションの依存関係に Microsoft Authentication Library (MSAL) SDK を追加する

次のように、MSAL ライブラリを依存関係として build.gradle ファイルに追加します。

```gradle
dependencies{
  implementation 'com.microsoft.identity.client.msal:5.+'
}
```

#### 共有デバイス モードを使用するようアプリを構成する

Microsoft Authentication Library (MSAL) SDK を使用して記述されたアプリケーションは、単一のアカウントまたは複数のアカウントを管理できます。 詳細については、 [単一アカウント モードまたは複数アカウント モードを](https://learn.microsoft.com/ja-jp/entra/msal/android/single-multi-account)参照してください。 共有デバイス モード アプリは、単一アカウント モードでのみ動作します。

複数アカウント モードをサポートする予定がない場合は、msal 構成ファイルで `"account_mode"` を `"SINGLE"` に設定します。 これにより、お客様のアプリで常に `ISingleAccountPublicClientApplication` が取得されるようになり、MSAL の統合が大幅に簡素化されます。 `"account_mode"` の既定値は `"MULTIPLE"` であるため、`"single account"` モードを使用する場合は、構成ファイルでこの値を変更することが重要です。

構成ファイルの例を次に示します。

```json
{
  "client_id": "Client ID after app registration at https://aka.ms/MobileAppReg",
  "authorization_user_agent": "WEBVIEW",
  "redirect_uri": "Redirect URI after app registration at https://aka.ms/MobileAppReg",
  "account_mode": "SINGLE",
  "broker_redirect_uri_registered": true,
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

構成ファイルの設定の詳細については、 [構成ドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/android/msal-configuration) を参照してください。

##### 単一アカウントと複数アカウントの両方のサポート

アプリは、個人デバイスと共有デバイスの両方での実行をサポートするように構築できます。 現在、アプリが複数のアカウントをサポートしており、共有デバイス モードをサポートするようにしたい場合は、単一アカウント モードのサポートを追加してください。

アプリが実行されているデバイスの種類に応じて、アプリの動作を変えたい場合もあるでしょう。 いつ単一アカウント モードで実行するかを決定するには、`ISingleAccountPublicClientApplication.isSharedDevice()` を使用します。

アプリケーションが実行しているデバイスの種類を表す 2 つの異なるインターフェイスが存在します。 MSAL のアプリケーション ファクトリにアプリケーション インスタンスを要求すると、適切なアプリケーション オブジェクトが自動的に提供されます。

次のオブジェクト モデルは、受信する可能性のあるオブジェクトの型と、それが共有デバイスのコンテキストで何を示すかを示しています。

[Image: パブリック クライアント アプリケーションの継承モデル]

`PublicClientApplication` オブジェクトを取得したら、型のチェックを行い、適切なインターフェイスにキャストする必要があります。 次のコードは、複数アカウント モードまたは単一アカウント モードをチェックし、アプリケーション オブジェクトを適切にキャストします。

```java
private IPublicClientApplication mApplication;

        // Running in personal-device mode?
        if (mApplication instanceOf IMultipleAccountPublicClientApplication) {
          IMultipleAccountPublicClientApplication multipleAccountApplication = (IMultipleAccountPublicClientApplication) mApplication;
          ...
        // Running in shared-device mode?
        } else if (mApplication instanceOf ISingleAccountPublicClientApplication) {
           ISingleAccountPublicClientApplication singleAccountApplication = (ISingleAccountPublicClientApplication) mApplication;
            ...
        }
```

アプリが共有デバイスまたは個人デバイスのどちらで実行されているかに応じて、次の違いが適用されます。

| - | 共有モードのデバイス | 個人デバイス |
| --- | --- | --- |
| **アカウント** | 1 つのアカウント | 複数のアカウント |
| **サインイン** | グローバル | グローバル |
| **サインアウト** | グローバル | 各アプリケーションは、サインアウトがアプリに対してローカルかどうかを制御できます。 |
| **サポートされているアカウントの種類** | 職場アカウントのみ | サポートされている個人アカウントと業務用アカウント |

#### PublicClientApplication オブジェクトを初期化する

MSAL 構成ファイルで `"account_mode":"SINGLE"` を設定した場合、返されるアプリケーション オブジェクトを `ISingleAccountPublicCLientApplication` として安全にキャストできます。

```java
private ISingleAccountPublicClientApplication mSingleAccountApp;

PublicClientApplication.create(
    this.getApplicationCOntext(),
    R.raw.auth_config_single_account,
    new PublicClientApplication.ApplicationCreatedListener() {

        @Override
        public void onCreated(IPublicClientApplication application){
            mSingleAccountApp = (ISingleAccountPublicClientApplication)application;
        }

        @Override
        public void onError(MsalException exception){
            /*Fail to initialize PublicClientApplication */
        }
    });
```

#### 共有デバイス モードを検出する

共有デバイス モードの検出は、アプリケーションにとって重要です。 多くのアプリケーションでは、共有デバイスでアプリケーションを使用するときに、ユーザー エクスペリエンス (UX) を変更する必要があります。 たとえば、アプリケーションに "サインアップ" 機能があるとします。これは、既にアカウントを持っている可能性があるため、現場担当者には適していません。 共有デバイス モードの場合は、アプリケーションのデータ処理にセキュリティを強化することもできます。

`isSharedDevice`の`IPublicClientApplication` API を使用して、アプリが共有デバイス モードでデバイスで実行されているかどうかを判断します。

次のコード スニペットは、 `isSharedDevice` API の使用例を示しています。

```Java
deviceModeTextView.setText(mSingleAccountApp.isSharedDevice() ? "Shared" : "Non-Shared");
```

#### サインインしているユーザーを取得し、ユーザーがデバイスで変更されたかどうかを判断する

共有デバイス モードをサポートするもう 1 つの重要な部分は、デバイス上のユーザーの状態を判断し、ユーザーが変更された場合、またはデバイスにユーザーがまったくいない場合にアプリケーション データをクリアすることです。 データが別のユーザーに漏洩しないようにする責任があります。

`getCurrentAccountAsync` API を使用して、デバイスで現在サインインしているアカウントに対してクエリを実行できます。

`loadAccount` メソッドでは、サインインしているユーザーのアカウントを取得します。 `onAccountChanged` メソッドでは、サインインしているユーザーが変更されたかどうかを特定し、その場合はクリーンアップします。

```java
private void loadAccount()
{
  mSingleAccountApp.getCurrentAccountAsync(new ISingleAccountPublicClientApplication.CurrentAccountCallback())
  {
    @Override
    public void onAccountLoaded(@Nullable IAccount activeAccount)
    {
      if (activeAccount != null)
      {
        signedInUser = activeAccount;
        final AcquireTokenSilentParameters silentParameters = new AcquireTokenSilentParameters.Builder()
                        .fromAuthority(signedInUser.getAuthority())
                        .forAccount(signedInUser)
                        .withScopes(Arrays.asList(getScopes()))
                        .withCallback(getAuthSilentCallback())
                        .build();
        mSingleAccountApp.acquireTokenSilentAsync(silentParameters);
      }
    }
    @Override
    public void onAccountChanged(@Nullable IAccount priorAccount, @Nullable Iaccount currentAccount)
    {
      if (currentAccount == null)
      {
        //Perform a cleanup task as the signed-in account changed.
        cleaUp();
      }
    }
    @Override
    public void onError(@NonNull Exception exception)
    {
        //getCurrentAccountAsync failed
    }
  }
}
```

#### ユーザーをグローバルにサインインさせる

デバイスが共有デバイスとして構成されている場合、アプリケーションは `signIn` API を呼び出してアカウントにサインインできます。 アカウントは、最初のアプリがアカウントにサインインした後、デバイス上のすべての対象アプリでグローバルに利用できるようになります。

```java
final SignInParameters signInParameters = ... /* create SignInParameters object */
mSingleAccountApp.signIn(signInParameters);
```

#### ユーザーをグローバルにサインアウトさせる

次のコードは、サインインしているアカウントを削除し、キャッシュされたトークンをアプリだけでなく、共有デバイス モードのデバイスからもクリアします。 ただし、アプリケーションから *データ* をクリアすることはありません。 アプリケーションからデータをクリアし、アプリケーションがユーザーに表示している可能性があるキャッシュされたデータをクリアする必要があります。

```java
mSingleAccountApp.signOut(new ISingleAccountPublicClientApplication.SignOutCallback() {
    @Override
    public void onSignOut() {
        // clear data from your application
    }

    @Override
    public void onError(@NonNull MsalException exception) {
        // signout failed, show error
    }
});
```

#### ブロードキャストを受信して、他のアプリケーションから開始されたグローバル サインアウトを検出する

アカウント変更ブロードキャストを受信するには、ブロードキャスト レシーバーを登録する必要があります。 [コンテキストに登録された](https://developer.android.com/guide/components/broadcasts#context-registered-receivers)レシーバーを介してブロードキャスト レシーバーを登録することをお勧めします。

アカウント変更ブロードキャストを受信したら、すぐに サインインしているユーザーを取得し、デバイスでユーザーが変更されたかどうかを判断します。 変更が検出された場合は、前にサインインしていたアカウントのデータのクリーンアップを開始します。 すべての操作を適切に停止し、データのクリーンアップを行うことをお勧めします。

次のコード スニペットは、ブロードキャスト レシーバーを登録する方法を示しています。

```java
private static final String CURRENT_ACCOUNT_CHANGED_BROADCAST_IDENTIFIER = "com.microsoft.identity.client.sharedmode.CURRENT_ACCOUNT_CHANGED";
private BroadcastReceiver mAccountChangedBroadcastReceiver;
private void registerAccountChangeBroadcastReceiver(){
    mAccountChangedBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //INVOKE YOUR PRIOR ACCOUNT CLEAN UP LOGIC HERE
        }
    };
    IntentFilter filter = new

    IntentFilter(CURRENT_ACCOUNT_CHANGED_BROADCAST_IDENTIFIER);
    this.registerReceiver(mAccountChangedBroadcastReceiver, filter);
}
```

### 共有デバイス モードをサポートする Microsoft アプリケーション

次のMicrosoft アプリケーションでは、共有デバイス モードMicrosoft Entraサポートされています。

- [Microsoft Teams](https://learn.microsoft.com/ja-jp/microsoftteams/platform/)
- [Microsoft Viva Engage](https://learn.microsoft.com/ja-jp/viva/engage/overview) (以前[の Yammer](https://learn.microsoft.com/ja-jp/viva/engage/overview))
- [アウトルック](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-configuration-policies-outlook)
- [Microsoft Power Apps](https://learn.microsoft.com/ja-jp/power-apps/)
- [Microsoft 365](https://apps.apple.com/us/app-bundle/microsoft-365/id1450038993?mt=12)
- [Microsoft Power BI Mobile](https://learn.microsoft.com/ja-jp/power-bi/consumer/mobile/mobile-app-shared-device-mode)
- [Microsoft Edge](https://learn.microsoft.com/ja-jp/microsoft-edge/)
- [マネージド ホーム画面](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-configuration-managed-home-screen-app)

### 共有デバイス モードをサポートするサード パーティの MMM

次のサード パーティ製モバイル デバイス管理 (MDM) プロバイダーは、共有デバイス モードMicrosoft Entraサポートしています。

- [VMware Workspace ONE](https://blogs.vmware.com/euc/2023/08/announcing-general-availability-of-shared-device-conditional-access-with-vmware-workspace-one-and-microsoft-entra-id.html)
- [SOTI MobiControl](https://soti.net/resources/blog/2023/soti-mobicontrol-supports-microsoft-shared-device-mode/)
- [42Gears SureMDM](https://docs.42gears.com/suremdm/intergrations/integrations_reports/conditional-access-suremdm/shared-device-mode-with-microsoft-entra)

### 共有デバイスのサインアウトとアプリのライフサイクル全体

ユーザーがサインアウトしたら、ユーザーのプライバシーとデータを保護するためのアクションを実行する必要があります。 たとえば、医療記録アプリを作成する場合は、ユーザーが以前に表示された患者レコードをサインアウトしたときにクリアされるようにする必要があります。 アプリケーションは、データのプライバシーのために準備し、フォアグラウンドに入るたびに確認する必要があります。

アプリで MSAL を使用して、共有モードのデバイスで実行されているアプリでユーザーをサインアウトすると、サインインしたアカウントとキャッシュされたトークンがアプリとデバイスの両方から削除されます。

次の図は、アプリのライフサイクル全体と、アプリの実行中に発生する可能性がある一般的なイベントを示しています。 この図は、アクティビティの起動時、アカウントのサインインとサインアウト、アクティビティの一時停止、再開、停止などのイベントがどのように適合するかを示しています。

[Image: 共有デバイス アプリのライフサイクル]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/single-multi-account"} -->
## 1 つのアカウントと複数のアカウントのパブリック クライアント アプリ

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/single-multi-account
- Service: msal / msal-android
- Article date: 2023-09-26
- Summary: 1 つのアカウントと複数のアカウントのパブリック クライアント アプリの概要。

この記事では、単一アカウントのパブリック クライアント アプリと複数のアカウントのパブリック クライアント アプリで使用される種類について、単一アカウントのパブリック クライアント アプリに焦点を当てて理解するのに役立ちます。

Azure Active Directory認証ライブラリ (ADAL) は、サーバーをモデル化します。 代わりに、Microsoft Authentication Library (MSAL) によってクライアント アプリケーションがモデル化されます。 Android アプリの大部分は、パブリック クライアントと見なされます。 パブリック クライアントは、シークレットを安全に保持できないアプリです。

MSAL は、一度に 1 つのアカウントのみを使用できるアプリの開発エクスペリエンスを簡略化し、明確にするために、 `PublicClientApplication` の API サーフェスを専門としています。 `PublicClientApplication` は、 `SingleAccountPublicClientApplication` と `MultipleAccountPublicClientApplication`によってサブクラス化されます。 次の図は、これらのクラス間の関係を示しています。

[Image: SingleAccountPublicClientApplication UML クラス図]

### 単一アカウントのパブリック クライアント アプリケーション

`SingleAccountPublicClientApplication` クラスを使用すると、一度に 1 つのアカウントのみをサインインできるようにする MSAL ベースのアプリを作成できます。 `SingleAccountPublicClientApplication` は、次の点で `PublicClientApplication` とは異なります。

- MSAL は、現在サインインしているアカウントを追跡します。
    - アプリがブローカー (Microsoft Entra アプリ登録時の既定値) を使用していて、ブローカーが存在するデバイスにインストールされている場合、MSAL はアカウントがデバイスで引き続き使用できるかどうかを確認します。
- `signIn` では、スコープの要求とは別に、アカウントを明示的にサインインすることができます。
- `acquireTokenSilent` では、アカウント パラメーターは必要ありません。 アカウントを指定し、指定したアカウントが MSAL によって追跡されている現在のアカウントと一致しない場合は、`MsalClientException` がスローされます。
- `acquireToken` では、ユーザーがアカウントを切り替えることはできません。 ユーザーが別のアカウントに切り替えようとすると、例外がスローされます。
- `getCurrentAccount`は、次を提供する結果オブジェクトを返します。
    - アカウントが変更されたかどうかを示すブール値。 たとえば、デバイスから削除された結果、アカウントが変更される場合があります。
    - 前のアカウント。 これは、アカウントがデバイスから削除されたとき、または新しいアカウントがサインインしたときに、ローカル データのクリーンアップを行う必要がある場合に便利です。
    - 現在のアカウント。
- `signOut` は、クライアントに関連付けられているトークンをデバイスから削除します。

Microsoft Authenticator、Windowsへのリンク (LTW)、Intune ポータル サイトなどの Android 認証ブローカーがデバイスにインストールされていて、ブローカーを使用するようにアプリが構成されている場合、`signOut`はデバイスからアカウントを削除しません。

### 単一アカウントのシナリオ

次の擬似コードは、 `SingleAccountPublicClientApplication`の使用を示しています。

```java
// Construct Single Account Public Client Application
ISingleAccountPublicClientApplication app = PublicClientApplication.createSingleAccountPublicClientApplication(getApplicationContext(), R.raw.msal_config);

String[] scopes = {"User.Read"};
IAccount mAccount = null;

// Acquire a token interactively
// The user will get a UI prompt before getting the token.
SignInParameters signInParameters = SignInParameters.builder()
        .withActivity(getActivity()) // Pass the current activity
        .withScopes(scopes) // Specify the scopes
        .withCallback(new AuthenticationCallback() {
            @Override
            public void onSuccess(IAuthenticationResult authenticationResult){
                mAccount = authenticationResult.getAccount();
            }
    
            @Override
            public void onError(MsalException exception){
            }
    
            @Override
            public void onCancel(){
            }
        })
        .build();

app.signIn(signInParameters);

// Load Account Specific Data
getDataForAccount(account);

// Get Current Account
ICurrentAccountResult currentAccountResult = app.getCurrentAccount();
if (currentAccountResult.didAccountChange()){
    // Account Changed Clear existing account data
    clearDataForAccount(currentAccountResult.getPriorAccount());
    mAccount = currentAccountResult.getCurrentAccount();
    if (account != null){
        //load data for new account
        getDataForAccount(account);
    }
}

// Sign out
if (app.signOut()) {
    clearDataForAccount(mAccount);
    mAccount = null;
}
```

### 複数アカウントのパブリック クライアント アプリケーション

`MultipleAccountPublicClientApplication` クラスは、複数のアカウントを同時にサインインできるようにする MSAL ベースのアプリを作成するために使用されます。 これにより、次のようにアカウントを取得、追加、削除できます。

#### [アカウントの追加]

アプリケーションで 1 つ以上のアカウントを使用するには、 `acquireToken` 1 回以上呼び出します。

#### アカウントを取得する

- `getAccount`を呼び出して、特定のアカウントを取得します。
- `getAccounts`呼び出して、アプリに現在認識されているアカウントの一覧を取得します。

アプリは、ブローカー アプリに認識されているデバイス上のすべてのMicrosoft ID プラットフォーム アカウントを列挙することはできません。 アプリで使用されているアカウントのみを列挙できます。 デバイスから削除されたアカウントは、これらの関数によって返されません。

#### アカウントを削除する

アカウント識別子を使用して `removeAccount` を呼び出して、アカウントを削除します。

アプリがブローカーを使用するように構成されていて、ブローカーがデバイスにインストールされている場合、 `removeAccount`を呼び出しても、アカウントはブローカーから削除されません。 クライアントに関連付けられているトークンのみが削除されます。

### 複数アカウントのシナリオ

次の擬似コードは、複数のアカウント アプリを作成し、デバイス上のアカウントを一覧表示し、トークンを取得する方法を示しています。

```java
// Construct Multiple Account Public Client Application
IMultipleAccountPublicClientApplication app = PublicClientApplication.createMultipleAccountPublicClientApplication(getApplicationContext(), R.raw.msal_config);

String[] scopes = {"User.Read"};
IAccount mAccount = null;

// Acquire a token interactively
// The user will be required to interact with a UI to obtain a token
AcquireTokenParameters acquireTokenParameters = new AcquireTokenParameters.Builder()
        .startAuthorizationFromActivity(getActivity())
        .withScopes(scopes)
        .withCallback(new AuthenticationCallback(){
    
            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {
                mAccount = authenticationResult.getAccount();
            }
    
            @Override
            public void onError(MsalException exception){
            }
    
            @Override
            public void onCancel(){
            }
         })
        .build();
app.acquireToken(acquireTokenParameters);

...

// Get the default authority
String authority = app.getConfiguration().getDefaultAuthority().getAuthorityURL().toString();

// Get a list of accounts on the device
List<IAccount> accounts = app.getAccounts();

// Pick an account to obtain a token from without prompting the user to sign in
IAccount selectedAccount = accounts.get(0);

// Get a token without prompting the user
AcquireTokenSilentParameters acquireTokenSilentParameters = new AcquireTokenSilentParameters.Builder()
        .withScopes(scopes)
        .forAccount(selectedAccount)
        .fromAuthority(authority)
        .withCallback(new SilentAuthenticationCallback() {

            @Override
            public void onSuccess(IAuthenticationResult authenticationResult) {
                mAccount = authenticationResult.getAccount();
            }
    
            @Override
            public void onError(MsalException exception){
            }
        })
        .build();
app.acquireTokenSilentAsync(acquireTokenSilentParameters);

```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/android/single-sign-on"} -->
## MSAL を使用して Android でアプリ間 SSO を有効にする方法

- Source: https://learn.microsoft.com/ja-jp/entra/msal/android/single-sign-on
- Service: msal / msal-android
- Article date: 2025-04-10
- Summary: Android 用のMicrosoft Authentication Library (MSAL) を使用して、アプリケーション全体でシングル サインオンを有効にする方法。

シングル サインオン (SSO) を使用すると、ユーザーは資格情報を 1 回だけ入力でき、これらの資格情報はアプリケーション間で自動的に機能します。 ユーザーが管理する必要があるパスワードの数を減らし、パスワードの疲労や関連する脆弱性のリスクを軽減することで、ユーザー エクスペリエンスが向上し、セキュリティが向上します。

[Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/)とMicrosoft Authentication Library (MSAL) は、一連のアプリケーションで SSO を有効にするのに役立ちます。 Broker 機能を有効にすると、デバイス全体で SSO を拡張できます。

このハウツーでは、アプリケーションで使用されるソフトウェア開発キット (SDK) を構成して、顧客に SSO を提供する方法について説明します。

### 前提条件

このハウツーでは、次の方法を知っていると想定しています。

- アプリを構成します。 詳細については、[Android チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-mobile-app-android-prepare-app)でアプリを作成する手順を参照してください。
- [アプリケーションを MSAL for Android](https://github.com/AzureAD/microsoft-authentication-library-for-android) と統合する

### SSO の方法

MSAL for Android を使用するアプリケーションで SSO を実現するには、次の 2 つの方法があります。

- ブローカー アプリケーションを使用する
- システム ブラウザーを使用する

    デバイス全体の SSO、アカウント管理、条件付きアクセスなどの利点を得るために、ブローカー アプリケーションを使用することをお勧めします。 ただし、ユーザーは追加のアプリケーションをダウンロードする必要があります。

### ブローカー認証による SSO

Microsoftのいずれかの認証ブローカーを使用して、デバイス全体の SSO に参加し、組織の条件付きアクセス ポリシーを満たすことをお勧めします。 ブローカーとの統合には、次の利点があります。

- デバイス SSO
- 条件付きアクセス:
    - Intune App Protection
    - デバイス登録 (職場への参加)
    - モバイル デバイス管理
- デバイス全体のアカウント管理
    - Android AccountManager を使用してアカウント設定
    - "職場アカウント" - カスタム アカウントの種類

Android では、Microsoft認証ブローカーは、[Microsoft Authenticator](https://play.google.com/store/apps/details?id=com.azure.authenticator)、[Intune ポータル サイト](https://play.google.com/store/apps/details?id=com.microsoft.windowsintune.companyportal)、および [Windows アプリへのリンクに](https://play.google.com/store/apps/details?id=com.microsoft.appmanager)含まれるコンポーネントです。

次の図は、アプリ、MSAL、およびMicrosoftの認証ブローカー間の関係を示しています。

[Image: アプリケーションが MSAL、ブローカー アプリ、Android アカウント マネージャーにどのように関連しているかを示す図。]

#### ブローカーをホストするアプリのインストール

ブローカー ホスティング アプリは、デバイス所有者がアプリ ストア (通常は Google Play ストア) からいつでもインストールできます。 ただし、一部の API (リソース) は、デバイスを次のようにする必要がある条件付きアクセス ポリシーによって保護されています。

- 登録済み（ワークプレイスに参加済み）および/または
- デバイス管理に登録されている、または
- Intune App Protection に登録されている

上記の要件を持つデバイスにブローカー アプリがまだインストールされていない場合、MSAL は、アプリが対話形式でトークンを取得しようとするとすぐにインストールするようにユーザーに指示します。 その後、アプリは、デバイスを必要なポリシーに準拠させる手順をユーザーに案内します。 ポリシー要件がない場合、またはユーザーがMicrosoft アカウントでサインインしている場合、Broker アプリのインストールは必要ありません。

#### ブローカーのインストールとアンインストールの影響

##### ブローカーがインストールされている場合

ブローカーがデバイスにインストールされている場合、後続のすべての対話型トークン要求 ( `acquireToken()`への呼び出し) は、MSAL によってローカルではなくブローカーによって処理されます。 MSAL で以前に使用できる SSO 状態は、ブローカーでは使用できません。 その結果、ユーザーはもう一度認証するか、デバイスに認識されているアカウントの既存の一覧からアカウントを選択する必要があります。

ブローカーをインストールする場合、ユーザーはもう一度サインインする必要はありません。 ユーザーが `MsalUiRequiredException` を解決する必要がある場合にのみ、次の要求がブローカーに送信されます。 `MsalUiRequiredException` は、いくつかの理由で発生することがあり、対話的に解決する必要があります。 例えば次が挙げられます。

- ユーザーが自分のアカウントに関連付けられているパスワードを変更しました。
- ユーザーのアカウントが条件付きアクセス ポリシーを満たしていない。
- ユーザーは、アプリが自分のアカウントに関連付けられるための同意を取り消しました。

**複数のブローカー** - デバイスに複数のブローカーがインストールされている場合、MSAL は認証プロセスを完了するためにアクティブなブローカーを単独で識別します

##### ブローカーがアンインストールされたとき

ブローカー ホスティング アプリが 1 つしかインストールされておらず、削除された場合、ユーザーはもう一度サインインする必要があります。 アクティブなブローカーをアンインストールすると、アカウントと関連付けられているトークンがデバイスから削除されます。

Microsoft Authenticator、Intune ポータル サイト、または Windows へのリンクをアンインストールした場合、ユーザーはもう一度サインインするように求められる*場合があります*。

#### ブローカーとの統合

##### ブローカーのリダイレクト URI を生成する

ブローカーと互換性のあるリダイレクト URI を登録する必要があります。 ブローカーのリダイレクト URI には、アプリのパッケージ名と、アプリの署名の Base64 エンコード表現が含まれている必要があります。

リダイレクト URI の形式は次のとおりです。 `msauth://<yourpackagename>/<base64urlencodedsignature>`

[keytool](https://manpages.debian.org/buster/openjdk-11-jre-headless/keytool.1.en.html) を使用して、アプリの署名キーを使用して Base64 でエンコードされた署名ハッシュを生成し、そのハッシュを使用してリダイレクト URI を生成できます。

Linux と macOS:

```bash
keytool -exportcert -alias androiddebugkey -keystore ~/.android/debug.keystore | openssl sha1 -binary | openssl base64
```

ウィンドウズ：

```powershell
keytool -exportcert -alias androiddebugkey -keystore %HOMEPATH%\.android\debug.keystore | openssl sha1 -binary | openssl base64
```

*keytool* で署名ハッシュを生成したら、Azure ポータルを使用してリダイレクト URI を生成します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニューの **[設定]** アイコン  を使用し、**[ディレクトリとサブスクリプション]** メニューからアプリケーション登録が含まれるテナントに切り替えます。
3. **Entra ID**&gt;**アプリ登録**に移動します。
4. アプリケーションを選択し、**認証**&gt;**プラットフォームの追加**&gt;Android を選択**します**。
5. 開いた [ **Android アプリの構成** ] ウィンドウで、前に生成した **署名ハッシュ** と **パッケージ名**を入力します。
6. **[構成]** ボタンを選択します。

リダイレクト URI が自動的に生成され、 **Android 構成** ウィンドウの **[リダイレクト URI** ] フィールドに表示されます。

アプリへの署名の詳細については、「Android Studio ユーザー ガイド」の [「アプリに署名](https://developer.android.com/studio/publish/app-signing) する」を参照してください。

##### ブローカーを使用するように MSAL を構成する

アプリでブローカーを使用するには、ブローカー リダイレクトを構成したことを証明する必要があります。 たとえば、ブローカーが有効なリダイレクト URI の両方を含め、MSAL 構成ファイルに次の設定を含めることで、登録したことを示します。

```json
"redirect_uri" : "<yourbrokerredirecturi>",
"broker_redirect_uri_registered": true
```

##### ブローカー関連の例外

MSAL は、次の 2 つの方法でブローカーと通信します。

- ブローカー バインド サービス
- Android AccountManager

このサービスを呼び出しても Android のアクセス許可は必要ないため、MSAL は最初にブローカー バインド サービスを使用します。 バインドされたサービスへのバインドが失敗した場合、MSAL は Android AccountManager API を使用します。 MSAL は、アプリに `"READ_CONTACTS"` アクセス許可が既に付与されている場合にのみ実行されます。

エラー コード `"BROKER_BIND_FAILURE"` の `MsalClientException` が返された場合は、次の 2 つのオプションがあります。

- Microsoft Authenticator アプリと Intune ポータル サイトの電源最適化を無効にするようにユーザーに依頼します。
- `"READ_CONTACTS"`アクセス許可を付与するようにユーザーに依頼する

#### ブローカー統合の確認

ブローカー統合が機能していることはすぐには明らかではないかもしれませんが、次の手順を使用して確認できます。

1. Android デバイスで、ブローカーを使用して要求を完了します。
2. Android デバイスの設定で、認証したアカウントに対応する新しく作成されたアカウントを探します。 アカウントは *、職場アカウント*の種類である必要があります。

テストを繰り返す場合は、設定からアカウントを削除できます。

### システム ブラウザーを使用した SSO

Android アプリケーションには、認証ユーザー エクスペリエンスに `WEBVIEW`、システム ブラウザー、または Chrome カスタム タブを使用するオプションがあります。 アプリケーションがブローカー認証を使用していない場合、SSO を実現するには、ネイティブ Web ビューではなくシステム ブラウザーを使用する必要があります。

#### 認可エージェント

承認エージェントの特定の戦略を選択することは重要であり、アプリがカスタマイズできる追加の機能を表します。 'WEBVIEW' を使用することをお勧めします。 その他の構成値の詳細については、「 [Android MSAL 構成ファイルについて](https://learn.microsoft.com/ja-jp/entra/msal/android/msal-configuration)」を参照してください。

MSAL では、 `WEBVIEW`またはシステム ブラウザーを使用した承認がサポートされています。 次の図は、 `WEBVIEW`、または CustomTabs を使用したシステム ブラウザー、または CustomTabs を使用しないシステム ブラウザーの外観を示しています。

[Image: MSAL ログインの例]

#### SSO への影響

アプリケーションがブローカー認証とアプリに統合せずに `WEBVIEW` 戦略を使用する場合、ユーザーはデバイス全体、またはネイティブ アプリと Web アプリの間で SSO エクスペリエンスを持つことはありません。

アプリケーションを MSAL と統合して、 `BROWSER` を使用して承認することができます。 WEBVIEW とは異なり、 `BROWSER` 既定のシステム ブラウザーと Cookie jar を共有することで、カスタム タブと統合された Web やその他のネイティブ アプリとのサインインを減らすことができます。

アプリケーションが Microsoft Authenticator、Intune ポータル サイト、または Link to Windows などのブローカーで MSAL を使用している場合、ユーザーは、いずれかのアプリでアクティブなサインインを行っている場合、アプリケーション間で SSO エクスペリエンスを持つことができます。

Note

ブローカーを使用する MSAL は WebView を利用し、MSAL ライブラリを使用し、ブローカー認証に参加するすべてのアプリケーションに SSO を提供します。ブローカーからの SSO 状態は、MSAL を使用しない他のアプリには拡張されません。

#### ウェブビュー

アプリ内 WebView を使用するには、MSAL に渡されるアプリ構成 JSON に次の行を配置します。

```json
"authorization_user_agent" : "WEBVIEW"
```

アプリ内 `WEBVIEW`を使用する場合、ユーザーはアプリに直接サインインします。 トークンはアプリのサンドボックス内に保持され、アプリの Cookie jar の外部では使用できません。 その結果、アプリが Microsoft Authenticator アプリ、Intune ポータル サイト、または Link to Windowsと統合されていない限り、ユーザーはアプリケーション間で SSO エクスペリエンスを持つことができません。

ただし、 `WEBVIEW` では、サインイン UI の外観をカスタマイズする機能が提供されます。 このカスタマイズを行う方法の詳細については、 [Android WebViews](https://developer.android.com/reference/android/webkit/WebView) を参照してください。

#### ブラウザー

WEBVIEW を使用することをお勧めしますが、ブラウザーと [カスタム タブ](https://developer.chrome.com/multidevice/android/customtabs) 戦略を使用するオプションが用意されています。 カスタム構成ファイルで次の JSON 構成を使用して、この戦略を明示的に指定できます。

```json
"authorization_user_agent" : "BROWSER"
```

デバイスのブラウザーを使用して SSO エクスペリエンスを提供するには、このアプローチを使用します。 MSAL は共有 Cookie jar を使用します。これにより、他のネイティブ アプリまたは Web アプリは、MSAL によって設定された永続化されたセッション Cookie を使用して、デバイス上で SSO を実現できます。

#### ブラウザーの選択ヒューリスティック

MSAL では、さまざまな Android フォンで使用する正確なブラウザー パッケージを指定することは不可能であるため、MSAL は最適なクロスデバイス SSO を提供しようとするブラウザー選択ヒューリスティックを実装します。

MSAL は、主にパッケージ マネージャーから既定のブラウザーを取得し、テスト済みの安全なブラウザーの一覧に含まれているかどうかを確認します。 そうでない場合、MSAL は、セーフ リストから別の既定以外のブラウザーを起動するのではなく、Webview の使用にフォールバックします。 既定のブラウザーは、カスタム タブをサポートしているかどうかに関係なく選択されます。 ブラウザーでカスタム タブがサポートされている場合、MSAL はカスタム タブを起動します。カスタム タブはアプリ内の `WebView` に近い外観になり、基本的な UI のカスタマイズが可能になります。 詳細については、 [Android のカスタム タブ](https://developer.chrome.com/multidevice/android/customtabs) を参照してください。

デバイスにブラウザー パッケージがない場合、MSAL はアプリ内 `WebView`を使用します。 デバイスの既定の設定が変更されていない場合は、SSO エクスペリエンスを確保するために、サインインごとに同じブラウザーを起動する必要があります。

##### テスト済みブラウザー

次のブラウザーは、構成ファイルで指定された `"redirect_uri"` に正しくリダイレクトされるかどうかを確認するためにテストされています。

| デバイス | 組み込みのブラウザー | クロム | オペラ | Microsoft Edge | UC ブラウザー | Firefox |
| --- | --- | --- | --- | --- | --- | --- |
| Nexus 4 (API 17) | pass | pass | 適用されません | 適用されません | 適用されません | 適用されません |
| Samsung S7 (API 25) | 合格^1^ | pass | pass | pass | 失敗 | pass |
| Vivo (API 26) | pass | pass | pass | pass | pass | 失敗 |
| ピクセル 2 (API 26) | pass | pass | pass | pass | 失敗 | pass |
| Oppo | pass | 該当なし^2^ | 適用されません | 適用されません | 適用されません | 適用されません |
| OnePlus (API 25) | pass | pass | pass | pass | 失敗 | pass |
| Nexus (API 28) | pass | pass | pass | pass | 失敗 | pass |
| MI | pass | pass | pass | pass | 失敗 | pass |

^1^Samsung の組み込みブラウザーは Samsung インターネットです。^2^Oppo デバイス設定内で既定のブラウザーを変更することはできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet"} -->
## .NET 向け Microsoft Authentication Library - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet
- Service: msal / msal-dotnet
- Article date: 2024-06-04
- Summary: .NET (MSAL.NET) のMicrosoft Authentication Libraryを使用して、Microsoft ID プラットフォームからトークンを取得し、保護された Web API にアクセスする方法について説明します。

MSAL.NET ([Microsoft。Identity.Client](https://www.nuget.org/packages/Microsoft.Identity.Client)) は、Microsoft Entra IDからトークンを取得して、保護された Web API (Microsoft API または Microsoft Entra ID に登録されたアプリケーション) にアクセスできるようにする認証ライブラリです。

MSAL.NET は、複数の.NET プラットフォーム (デスクトップ、モバイル、Web) で利用できます。

### サポートされているプラットフォームとアプリケーション アーキテクチャ

MSAL.NET では、次のようなさまざまなアプリケーション トポロジがサポートされています。

- ユーザーの代わりに Microsoft Graph APIを呼び出す[ネイティブ クライアント](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/active-directory-dev-glossary#native-client) (モバイルまたはデスクトップ アプリケーション)。
- ユーザーの代わりに、またはユーザーなしで Microsoft Graph APIを呼び出すデーモン、サービス、または [Web クライアント](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/active-directory-dev-glossary#web-client) (Web アプリまたは Web API)。

サポートされているシナリオの詳細については、「 [シナリオ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/scenarios)」を参照してください。

MSAL.NET では、[.NET](https://dotnet.microsoft.com/)、[.NET Framework、.NET MAUI](https://dotnet.microsoft.com/download/dotnet-framework)など、複数のプラットフォーム[がサポートされます](https://dotnet.microsoft.com/apps/maui)。

Note

すべての認証機能がすべてのプラットフォームで使用できるわけではありません。

- モバイル プラットフォームでは、機密クライアント フローは許可されません。 これらはバックエンドとして機能するためのものではありません。また、シークレットを安全に格納することはできません。
- パブリック クライアント (モバイルとデスクトップ) では、既定のブラウザーとリダイレクト URI はプラットフォームによって異なり、ブローカーの可用性は異なります ( [ブラウザーの使用ドキュメントの](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/using-web-browsers)詳細)。

Note

MSAL.NET は、ID プロバイダー (IDP) としてMicrosoft Entra IDで使用できるように最適化されています。 OAuth 2 をサポートするサード パーティの IDP で MSAL.NET を使用することは可能ですが。 0 (特に、組み込みブラウザーまたはシステム ブラウザー ( [ブラウザーの使用ドキュメントを](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/using-web-browsers)参照) を使用する場合)、相互運用性は保証されません。 Microsoftでは、サード パーティの IDP 統合に起因する問題のサポートは提供されません。 このようなシナリオはベスト エフォートと見なされ、対処できない可能性があります。

Note

MSAL.NET バージョン 4.61.0 以降では、ユニバーサル Windows プラットフォーム、Xamarin Android、iOS Xamarinはサポートされません。 この非推奨化については、「[Announcing the Upcoming Deprecation of MSAL.NET for Xamarin and UWP](https://devblogs.microsoft.com/identity/uwp-xamarin-msal-net-deprecation/)」で詳しく説明しています。

### MSAL.NET を使用する理由

MSAL.NET には、トークンを取得するいくつかの方法が用意されています。 MSAL.NET の使用は、汎用 OAuth ライブラリを使用したり、プロトコルに対する呼び出しを記述したりするよりも簡単です。 MSAL.NET には、開発者ワークフローを簡略化する、すぐに使用できるいくつかの利点があります。

- 有効期限が近づくと、**トークンキャッシュ**と**リフレッシュ トークン**を代わりに保持します。
- アプリケーションでサインインする**対象ユーザー** (組織、複数の組織、職場、学校、Microsoft個人アカウント、Microsoft Entra 外部 IDを使用したソーシャル ID、ソブリン クラウドと国内クラウドのユーザー) を指定するのに役立ちます。
- **構成**ファイルを使用してアプリケーションを設定するのに役立ちます。
- アクション可能な例外、ログ記録、テレメトリを公開することで、アプリのトラブルシューティングに役立ちます。

### MSAL.NET の使用を開始する

1. [MSAL.NET の使用シナリオ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/scenarios)について説明します。
2. [アプリをMicrosoft Entra IDに登録します](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/quickstart-register-app)。
3. [クライアント アプリケーションの種類](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-applications) (パブリック クライアントと機密クライアント) について説明します。
4. 保護された API にアクセスするための [トークンの取得](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/overview) について説明します。

### Considerations

MSAL.NET はトークンを取得するために使用されます。 Web API の保護には使用されません。 Microsoft Entra IDを使用した Web API の保護に関心がある場合は、次のページを参照してください。

- [ASP.NET Core での Microsoft Entra ID](https://learn.microsoft.com/ja-jp/aspnet/core/security/authentication/azure-active-directory/) 例では、MSAL.NET を使用して Web API を呼び出す Web アプリを紹介します。
- [active-directory-dotnet-native-aspnetcore-v2](https://github.com/azure-samples/active-directory-dotnet-native-aspnetcore-v2) は、Microsoft Entra IDを使用してWPF アプリケーションから ASP.NET Core Web API を呼び出す方法を示しています。
- [.NET オープンソース ライブラリの IdentityModel 拡張機能](https://github.com/AzureAD/azure-activedirectory-identitymodel-extensions-for-dotnet)は、API を保護するために ASP.NET および ASP.NET Coreによって使用されるミドルウェアを提供します。

### Azure Active Directory認証ライブラリ (ADAL) からの移行

.NETのMicrosoft Authentication Library (MSAL) は、認証トークンの取得に使用できるサポートされているライブラリです。 ユーザーまたは組織が Azure Active Directory 認証ライブラリ (ADAL) を使用している場合は、[MSAL に移行する](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-migration)必要があります。 ADAL は **2023 年 6 月 30 日に**終了しました。

Note

ADAL は 2023 年 6 月 30 日以降非推奨ですが、基になるエンドポイントはアクティブなままであるため、ADAL に依存するアプリケーションは中断しないでください。 ただし、ADAL の新機能やサポートは提供されません。

### リリース

以前のリリースについては、[GitHubのリリースを](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/releases)参照してください。

進行中のリリースと今後のリリースについては、「 [マイルストーン](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/milestones)」を参照してください。

バージョン管理の詳細については、「[セマンティック バージョン管理 - パブリック API の変更を理解するための API 変更管理](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/resources/semantic-versioning-api-change-management) MSAL.NET 参照してください。

### Samples

[包括的なサンプル リストを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/sample-v2-code)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/acquire-token-silently"} -->
## キャッシュからトークンを取得する (MSAL.NET) - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/acquire-token-silently
- Service: msal / msal-dotnet
- Article date: 2023-08-24
- Summary: .NET (MSAL.NET) のMicrosoft Authentication Libraryを使用して(トークン キャッシュから) アクセス トークンをサイレントで取得する方法について説明します。

.NET (MSAL.NET) のMicrosoft Authentication Libraryを使用してアクセス トークンを取得すると、トークンがキャッシュされます。 アプリケーションがトークンを必要とする場合は、最初にキャッシュからトークンをフェッチする必要があります。

[`AuthenticationResult.AuthenticationResultMetadata.TokenSource`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.authenticationresultmetadata.tokensource?view=msal-dotnet-latest&preserve-view=true) プロパティを調べることで、トークンのソースを監視できます。

### Web サイトと Web API

ASP.NET Core と ASP.NET Classic の Web サイトは、MSAL.NET のラッパーである [Microsoft.Identity.Web](https://learn.microsoft.com/ja-jp/entra/msidweb/) と統合する必要があります。 メモリ トークン キャッシュまたは分散トークン キャッシュは、 [トークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization?tabs=aspnetcore)の説明に従って構成できます。

ASP.NET Core 上の Web API では、Microsoft.Identity.Web を使用する必要があります。 ASP.NET クラシック上の Web API は、`AcquireTokenOnBehalfOf`を呼び出して MSAL を直接使用し、メモリまたは分散キャッシュを構成する必要があります。 詳細については、「[MSAL.NET でのトークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization?tabs=aspnet)」を参照してください。 キャッシュをクリアする API がないため、 `AcquireTokenSilent` API を呼び出す理由はありません。 キャッシュ サイズは、MemoryCache、Redis などの基になるキャッシュ ストアに削除ポリシーを設定することで管理できます。

### Web サービス/デーモン アプリ

ユーザーが関係しないアプリ ID のトークンを要求するアプリケーションは、 `AcquireTokenForClient` を呼び出すことによって、MSAL の内部キャッシュに依存し、独自のメモリ トークン キャッシュまたは分散トークン キャッシュを定義できます。 手順と詳細については、「[MSAL.NET でのトークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization?tabs=aspnet)」を参照してください。

ユーザーが関与していないため、 `AcquireTokenSilent`を呼び出す理由はありません。 `AcquireTokenForClient` キャッシュをクリアする API がないため、キャッシュを単独で検索します。 キャッシュ サイズは、トークンが必要なテナントとリソースの数に比例します。 キャッシュ サイズは、MemoryCache、Redis などの基になるキャッシュ ストアに削除ポリシーを設定することで管理できます。

### デスクトップ、コマンド ライン、モバイル アプリケーション

デスクトップ、コマンド ライン、モバイル アプリケーションでは、最初に `AcquireTokenSilent` メソッドを呼び出して、受け入れ可能なトークンがキャッシュ内にあるかどうかを確認する必要があります。 多くの場合、キャッシュ内のトークンに基づいて、より多くのスコープを持つ別のトークンを取得できます。 有効期限が近づいているときにトークンを更新することもできます (トークン キャッシュにも更新トークンが含まれるため)。

ユーザー操作を必要とする認証フローの場合、MSAL はアクセス トークン、更新トークン、ID トークン、および 1 つのアカウントに関する情報を表す `IAccount` オブジェクトをキャッシュします。 [IAccount](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.iaccount?view=msal-dotnet-latest&preserve-view=true) の詳細を確認します。 [クライアント資格情報](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-authentication-flows#client-credentials)などのアプリケーション フローでは、`IAccount` オブジェクトと ID トークンにはユーザーが必要であり、更新トークンは適用できないため、アクセス トークンのみがキャッシュされます。

推奨されるパターンは、最初に `AcquireTokenSilent` メソッドを呼び出す方法です。 `AcquireTokenSilent`失敗した場合は、他のメソッドを使用してトークンを取得します。

次の例では、アプリケーションは最初にトークン キャッシュからトークンを取得しようとします。 `MsalUiRequiredException`例外がスローされた場合、アプリケーションは対話形式でトークンを取得します。

```csharp
var accounts = await app.GetAccountsAsync();

AuthenticationResult result = null;
try
{
     result = await app.AcquireTokenSilent(scopes, accounts.FirstOrDefault())
                       .ExecuteAsync();
}
catch (MsalUiRequiredException ex)
{
    // A MsalUiRequiredException happened on AcquireTokenSilent.
    // This indicates you need to call AcquireTokenInteractive to acquire a token
    Debug.WriteLine($"MsalUiRequiredException: {ex.Message}");

    try
    {
        result = await app.AcquireTokenInteractive(scopes)
                          .ExecuteAsync();
    }
    catch (MsalException msalex)
    {
        ResultText.Text = $"Error Acquiring Token:{System.Environment.NewLine}{msalex}";
    }
}
catch (Exception ex)
{
    ResultText.Text = $"Error Acquiring Token Silently:{System.Environment.NewLine}{ex}";
    return;
}

if (result != null)
{
    string accessToken = result.AccessToken;
    // Use the token
}
```

#### キャッシュのクリア

パブリック クライアント アプリケーションでは、キャッシュからアカウントを削除するとクリアされます。 ただし、ブラウザーにあるセッション Cookie は削除されません。

```csharp
var accounts = (await app.GetAccountsAsync()).ToList();

// clear the cache
while (accounts.Any())
{
   await app.RemoveAsync(accounts.First());
   accounts = (await app.GetAccountsAsync()).ToList();
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/acquiretokensilentasync-api"} -->
## AcquireTokenAsync API について - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/acquiretokensilentasync-api
- Service: msal / msal-dotnet
- Article date: 2023-03-17
- Summary: MSAL.NET を使用してパブリック クライアント アプリケーションと機密クライアント アプリケーションでトークンをサイレントで取得する方法について説明します

### トークンがキャッシュされる

#### パブリック クライアント アプリケーション

MSAL.NET Web API を呼び出すユーザー トークンを取得すると、キャッシュされます。 パブリック クライアント アプリケーションを構築していて、トークンを取得する場合は、最初に `AcquireTokenSilent`呼び出して、受け入れ可能なトークンがキャッシュ内にあるか、更新できるか、派生できるかを確認します。 そうでない場合は、関心のあるフローに応じて AcquireToken*ForFlow* メソッドを呼び出します。

#### 機密クライアント アプリケーション

ASP.NET Core アプリケーションをビルドする場合は、[`Microsoft.Identity.Web`](https://github.com/AzureAD/microsoft-identity-web)を使用して、これらをすべて自動的に処理します。

それ以外の場合、機密クライアント アプリケーションでは、次の前に `AcquireTokenSilent` を呼び出さないでください。

- `AcquireTokenForClient` ([クライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/client-credential-flows))。ユーザー トークン キャッシュは使用されず、アプリケーション トークン キャッシュが使用されます。 このメソッドは、STS に要求を送信する前に、このアプリケーション トークン キャッシュの検証を処理します
- `AcquireTokenByAuthorizationCode`Web Appsでは、アプリケーションがユーザーをサインインさせて、より多くのスコープに同意することで取得したコードを引き換えます。 コードはアカウントではなくパラメーターとして渡されるため、メソッドはコードを引き換える前にキャッシュを検索できません。そのためには、サービスの呼び出しが必要です。
- `AcquireTokenOnBehalfOf` Web API では、Web API に送信されるトークンを使用し、このトークンをキーとして使用してトークン キャッシュを検証および更新するためです。 `AcquireTokenSilent` には、必要な情報 (受信トークン) がありません。

### AcquireTokenXYZ がキャッシュからトークンを取得しない

パブリック クライアント アプリケーションでは、ADAL.NET での動作とは対照的に、MSAL.NET の設計は、キャッシュ`AcquireTokenInteractive`見ないようになっています。 アプリケーション開発者は、最初に `AcquireTokenSilent` を呼び出す必要があります。 `AcquireTokenSilent` は、多くの場合、キャッシュ内のトークンに基づいて、より多くのスコープを持つ別のトークンをサイレントで取得できます。 また、有効期限が近づいているときにトークンを更新することもできます (トークン キャッシュにも更新トークンが含まれるため)

#### パブリック クライアント アプリケーションでの推奨される呼び出しパターン

推奨される呼び出しパターンは、最初に `AcquireTokenSilent`を呼び出そうとし、 `MsalUiRequiredException`で失敗した場合は、 `AcquireTokenXYZ`を呼び出します。

#### MSAL.NET 4.x のパブリック クライアント アプリケーションで推奨される呼び出しパターン

```csharp
AuthenticationResult result = null;
var accounts = await app.GetAccountsAsync();

try
{
 result = await app.AcquireTokenSilent(scopes, accounts.FirstOrDefault())
        .ExecuteAsync();
}
catch (MsalUiRequiredException ex)
{
 // A MsalUiRequiredException happened on AcquireTokenSilent.
 // This indicates you need to call AcquireTokenInteractive to acquire a token
 System.Diagnostics.Debug.WriteLine($"MsalUiRequiredException: {ex.Message}");

 try
 {
    result = await app.AcquireTokenInteractive(scopes)
          .ExecuteAsync();
 }
 catch (MsalException msalex)
 {
    ResultText.Text = $"Error Acquiring Token:{System.Environment.NewLine}{msalex}";
 }
}
catch (Exception ex)
{
 ResultText.Text = $"Error Acquiring Token Silently:{System.Environment.NewLine}{ex}";
 return;
}

if (result != null)
{
 string accessToken = result.AccessToken;
 // Use the token
}
```

コンテキスト内のコードについては、 [active-directory-dotnet-desktop-msgraph-v2](https://github.com/Azure-Samples/active-directory-dotnet-desktop-msgraph-v2/blob/master/active-directory-wpf-msgraph-v2/MainWindow.xaml.cs#L45-L67) サンプルを参照してください。

##### MSAL.NET 2.x のパブリック クライアント アプリケーションで推奨される呼び出しパターン

```csharp
AuthenticationResult result = null;
var accounts = await app.GetAccountsAsync();

try
{
 result = await app.AcquireTokenSilentAsync(scopes, accounts.FirstOrDefault());
}
catch (MsalUiRequiredException ex)
{
 // A MsalUiRequiredException happened on AcquireTokenSilentAsync.
 // This indicates you need to call AcquireTokenAsync to acquire a token
 System.Diagnostics.Debug.WriteLine($"MsalUiRequiredException: {ex.Message}");

 try
 {
    result = await app.AcquireTokenAsync(scopes);
 }
 catch (MsalException msalex)
 {
    ResultText.Text = $"Error Acquiring Token:{System.Environment.NewLine}{msalex}";
 }
}
catch (Exception ex)
{
 ResultText.Text = $"Error Acquiring Token Silently:{System.Environment.NewLine}{ex}";
 return;
}

if (result != null)
{
 string accessToken = result.AccessToken;
 // Use the token
}
```

#### MSAL.NET 1.x のパブリック クライアント アプリケーションで推奨される呼び出しパターン

以前のバージョンの MSAL.NET では、`IAccount`ではなく`IUser`が使用されていました。 コードは次のとおりです。

```csharp
AuthenticationResult result = null;
try
{
    result = await app.AcquireTokenSilentAsync(scopes, app.Users.FirstOrDefault());
}
catch (MsalUiRequiredException ex)
{
    // A MsalUiRequiredException happened on AcquireTokenSilentAsync.
    // This indicates you need to call AcquireTokenAsync to acquire a token
    System.Diagnostics.Debug.WriteLine($"MsalUiRequiredException: {ex.Message}");

    try
    {
        result = await app.AcquireTokenAsync(scopes);
    }
    catch (MsalException msalex)
    {
        ResultText.Text = $"Error Acquiring Token:{System.Environment.NewLine}{msalex}";
    }
}
catch (Exception ex)
{
    ResultText.Text = $"Error Acquiring Token Silently:{System.Environment.NewLine}{ex}";
    return;
}

if (result != null)
{
    string accessToken = result.AccessToken;
    // Use the token
}

```

コンテキスト内のコードについては、 [active-directory-dotnet-desktop-msgraph-v2](https://github.com/Azure-Samples/active-directory-dotnet-desktop-msgraph-v2/blob/master/active-directory-wpf-msgraph-v2/MainWindow.xaml.cs#L45-L67) サンプルを参照してください

#### 認証コード フローを使用してユーザーを認証するWeb Appsで推奨される呼び出しパターン

OpenID Connect [承認コード](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/authorization-codes) フローを使用する Web アプリケーションの場合、コントローラーで推奨されるパターンは次のとおりです。

- シリアル化をカスタマイズしたトークン キャッシュを使用して`ConfidentialClientApplication`をインスタンス化する方法については、「[MSAL.NET でのトークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-token-cache-serialization?tabs=aspnet)」を参照してください。
- `AcquireTokenByAuthorizationCode` を呼び出す

次に、Web アプリで API のトークンを取得するたびに、 `AcquireTokenSilent`を呼び出します。 `AcquireTokenSilent`が`MsalUiRequiredException`をスローした場合、Web API はユーザーにチャレンジする必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/clear-token-cache"} -->
## トークン キャッシュをクリアする (MSAL.NET) - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/clear-token-cache
- Service: msal / msal-dotnet
- Article date: 2023-08-24
- Summary: .NET (MSAL.NET) のMicrosoft Authentication Libraryを使用してトークン キャッシュをクリアする方法について説明します。

### Web API とデーモン アプリ

キャッシュからトークンを削除する API はありません。 キャッシュ サイズは、基になるストレージに削除ポリシーを設定することによって処理する必要があります。 メモリ キャッシュまたは分散キャッシュの使用方法の詳細については、キャッシュ [のシリアル化](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization?tabs=aspnetcore) に関するページを参照してください。

### デスクトップ、コマンド ライン、モバイル アプリケーション

.NET (MSAL.NET) のMicrosoft Authentication Libraryを使用してアクセス トークンを[取得](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-acquire-cache-tokens)すると、トークンがキャッシュされます。 アプリケーションでトークンが必要な場合は、最初に `AcquireTokenSilent` メソッドを呼び出して、受け入れ可能なトークンがキャッシュ内にあるかどうかを確認する必要があります。

キャッシュをクリアするには、キャッシュからアカウントを削除します。 ただし、ブラウザーにあるセッション Cookie は削除されません。 次の例では、パブリック クライアント アプリケーションをインスタンス化し、アプリケーションのアカウントを取得して、アカウントを削除します。

```csharp
private readonly IPublicClientApplication _app;
private static readonly string ClientId = ConfigurationManager.AppSettings["ida:ClientId"];
private static readonly string Authority = string.Format(CultureInfo.InvariantCulture, AadInstance, Tenant);

_app = PublicClientApplicationBuilder.Create(ClientId)
                .WithAuthority(Authority)
                .Build();

var accounts = (await _app.GetAccountsAsync()).ToList();

// clear the cache
while (accounts.Any())
{
   await _app.RemoveAsync(accounts.First());
   accounts = (await _app.GetAccountsAsync()).ToList();
}

```

トークンの取得とキャッシュの詳細については、[アクセス トークンの取得に関するページを](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-acquire-cache-tokens)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/desktop-mobile/acquiring-tokens-interactively"} -->
## 対話形式でトークンを取得する - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/acquiring-tokens-interactively
- Service: msal / msal-dotnet
- Article date: 2023-12-13
- Summary: MSAL.NET とユーザー操作を使用してトークンを取得する方法。

対話型トークンを取得するには、MSAL が起動するブラウザーでホストされている認証ダイアログをユーザーが *操作* する必要があります。 これに対し、Web アプリでは、ユーザーは承認ページにリダイレクトされ、別の API が使用されます。 認証ダイアログでは、資格情報、パスワードの変更、多要素認証などを要求できます。フローとビジュアル コンテンツはサービスによって決められます。

MSAL.NET では、対話形式でトークンを取得するために使用するメソッドが[AcquireTokenInteractive(IEnumerable&lt;String&gt;)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplication.acquiretokeninteractive#microsoft-identity-client-publicclientapplication-acquiretokeninteractive%28system-collections-generic-ienumerable%28%28system-string%29%29%29)。

次の例は、Microsoft Graphを使用してユーザーのプロファイルを読み取るためのトークンを取得するベアボーン コードを示しています。

```csharp
string[] scopes = new string[] { "user.read" };

var app = PublicClientApplicationBuilder.Create("YOUR_CLIENT_ID")
    .WithDefaultRedirectUri()
    .Build();

var accounts = await app.GetAccountsAsync();

AuthenticationResult result;
try
{
    result = await app.AcquireTokenSilent(scopes, accounts.FirstOrDefault())
      .ExecuteAsync();
}
catch (MsalUiRequiredException)
{
    result = await app.AcquireTokenInteractive(scopes).ExecuteAsync();
}
```

Note

[AcquireTokenSilent(IEnumerable&lt;String&gt;, IAccount)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.clientapplicationbase.acquiretokensilent#microsoft-identity-client-clientapplicationbase-acquiretokensilent%28system-collections-generic-ienumerable%28%28system-string%29%29-microsoft-identity-client-iaccount%29)を使用するには、開発者がトークン キャッシュを設定する必要があります。 トークン キャッシュがないと、ユーザーが以前にログインした場合でも、アプリの再起動後に対話型プロンプトが常に表示されます。 トークン キャッシュの設定の詳細については、[MSAL.NET でのトークン キャッシュのシリアル化に](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization)関するページを参照してください。

### ブローカーの使用

ユーザー認証の推奨される方法は、ブラウザーではなくブローカーを使用することです (たとえば、Windowsの [Web アカウント マネージャー (WAM)](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam) など)。 WAM を使用すると、開発者は、Windowsに既に接続されている個人またはMicrosoft Entra IDアカウントにアプリケーションを接続するシームレスなエクスペリエンスを提供できます。 さらに、ブローカーはトークン保護によってセキュリティを強化します。

### 必須のパラメーター

[AcquireTokenInteractive(IEnumerable&lt;String&gt;)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplication.acquiretokeninteractive#microsoft-identity-client-publicclientapplication-acquiretokeninteractive%28system-collections-generic-ienumerable%28%28system-string%29%29%29) には、トークンが必要なスコープを定義する文字列の列挙を含む `scopes` という必須パラメーターが 1 つだけ含まれています。 トークンがMicrosoft Graph用の場合、必要なスコープは、各Microsoft Graph API の API リファレンスの **Permissions** という名前のセクションにあります。 たとえば、 [ユーザーの連絡先を一覧表示](https://learn.microsoft.com/ja-jp/graph/api/user-list-contacts)するには、 `User.Read` スコープと `Contacts.Read` スコープを使用する必要があります。 詳細については、[Microsoft Graphアクセス許可のリファレンスを参照してください](https://learn.microsoft.com/ja-jp/graph/permissions-reference)。

Android では、 [WithParentActivityOrWindow(Func&lt;IntPtr&gt;)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplicationbuilder.withparentactivityorwindow#microsoft-identity-client-publicclientapplicationbuilder-withparentactivityorwindow%28system-func%28%28system-intptr%29%29%29)を使用して親アクティビティを指定し、対話が完了した後にトークンが親アクティビティに戻されるようにする必要もあります。 それを指定しない場合は、例外がスローされます。

### 省略可能なパラメーター

#### WithParentActivityOrWindow

[AcquireTokenInteractive(IEnumerable&lt;String&gt;)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplication.acquiretokeninteractive#microsoft-identity-client-publicclientapplication-acquiretokeninteractive%28system-collections-generic-ienumerable%28%28system-string%29%29%29)には、開発者が親 UI コンポーネント (たとえば、Windowsのウィンドウ、Android のアクティビティ) への参照を提供できるようにする省略可能なパラメーターが 1 つ用意されています。 この親 UI は、 [WithParentActivityOrWindow(Func&lt;IntPtr&gt;)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplicationbuilder.withparentactivityorwindow#microsoft-identity-client-publicclientapplicationbuilder-withparentactivityorwindow%28system-func%28%28system-intptr%29%29%29)を使用して指定します。 UI ダイアログは通常、その親要素の中央に表示されます。 上で説明したように、Android では親アクティビティが *必須* のパラメーターです。

[WithParentActivityOrWindow(Func&lt;IntPtr&gt;)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplicationbuilder.withparentactivityorwindow#microsoft-identity-client-publicclientapplicationbuilder-withparentactivityorwindow%28system-func%28%28system-intptr%29%29%29) には、使用されるプラットフォームに応じて異なる引数の種類があります。

```csharp
// Android
WithParentActivityOrWindow(Activity activity)

// .NET Framework
WithParentActivityOrWindow(IntPtr windowPtr)
WithParentActivityOrWindow(IWin32Window window)

// macOS
WithParentActivityOrWindow(NSWindow window)

// iOS
WithParentActivityOrWindow(IUIViewController viewController)

// .NET Standard (this will be on all platforms at runtime, but only on .NET Standard at build time)
WithParentActivityOrWindow(object parent).
```

解説:

- .NET Standard では、想定される`object`は次のとおりです。

    - `Activity` Android の場合。
    - `UIViewController` iOS の場合。
    - `IntPr`Windows - [親ウィンドウ ハンドルのガイドラインを](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam#parent-window-handles)参照してください。
- Windows では、埋め込みブラウザーが適切な UI 同期コンテキストを取得するように、UI スレッドから [AcquireTokenInteractive(IEnumerable&lt;String&gt;)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplication.acquiretokeninteractive#microsoft-identity-client-publicclientapplication-acquiretokeninteractive%28system-collections-generic-ienumerable%28%28system-string%29%29%29) を呼び出す必要があります。 UI スレッドから呼び出さないと、メッセージが適切にポンプされない、または UI でデッドロックのシナリオが発生する可能性があります。 UI スレッドを使用していない場合にこれを実現する 1 つの方法は、 [Dispatcher](https://learn.microsoft.com/ja-jp/dotnet/api/system.windows.threading.dispatcher)を使用することです。

    ```csharp
    result = await app.AcquireTokenInteractive(scopes)
                      .WithParentActivityOrWindow(new WindowInteropHelper(this).Handle)
                      .ExecuteAsync();
    ```

#### WithPrompt

[WithPrompt(Prompt)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokeninteractiveparameterbuilder.withprompt#microsoft-identity-client-acquiretokeninteractiveparameterbuilder-withprompt%28microsoft-identity-client-prompt%29) は、対話型認証プロンプトの動作を制御するために使用されます。

呼び出しの中で、使用可能な [Prompt](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.prompt) 値のいずれかを指定できます。

- `SelectAccount` - トークン サービスが、ユーザーがセッションを持つアカウントを含むアカウント選択ダイアログを強制的に表示します。 これは、アプリケーション開発者がコンピューターで使用できるさまざまな ID の中からユーザーを選択できるようにする場合に便利です。 これを行うには、ID プロバイダーに `prompt=select_account` を送信します。 これは既定の構成であり、使用可能な情報 (アカウント、ユーザーのセッションの存在など) に基づいて最適なエクスペリエンスを提供します。 *通常*、この値は変更しないでください。
- `Consent` - 同意が以前に付与された場合でも、アプリケーション開発者がユーザーに同意を求めるメッセージを強制的に表示できるようにします。 これを行うには、ID プロバイダーに `prompt=consent` を送信します。 これは、セキュリティに重点を置いた一部のアプリケーションで使用できます。組織のガバナンスでは、アプリケーションが使用されるたびにユーザーに同意ダイアログが表示されることを要求します。
- `ForceLogin` - アプリケーション開発者は、これが必要ない場合でも、サービスによって資格情報の入力を求められます。 これは、トークンの取得に失敗し、開発者がユーザーにもう一度サインインさせる場合に役立ちます。 これを行うには、ID プロバイダーに `prompt=login` を送信します。 これは主に、組織のガバナンスがアプリケーションの特定の部分にアクセスするたびにユーザーがサインインする必要があることを要求する、セキュリティに重点を置いた一部のアプリケーションで使用されます。
- `Create` - ID プロバイダーに `prompt=create` を送信することで、外部 ID に使用されるサインアップ エクスペリエンスをトリガーします。 これは、MSAL.NET 4.29.0 以降で使用できます。 このプロンプトは、Azure AD B2C アプリには送信しないでください。 詳細については、「[セルフサービス サインアップのユーザー フローをアプリに追加する](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-user-flow)」を参照してください。
- `Never`(.NET 4.5 および WinRT のみ) - ユーザーにメッセージを表示せず、代わりに非表示の埋め込み Web ビューに格納されている Cookie の使用を試みます。 これは失敗する可能性があります。その場合、 [AcquireTokenInteractive(IEnumerable&lt;String&gt;)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplication.acquiretokeninteractive#microsoft-identity-client-publicclientapplication-acquiretokeninteractive%28system-collections-generic-ienumerable%28%28system-string%29%29%29) は例外をスローして、UI の操作が必要であることを通知します。
- `NoPrompt` - ID プロバイダーにプロンプトを送信しません。 これは、AZURE AD B2C 編集プロファイル ポリシーの場合にのみ役立ちます ([「MSAL.NET を使用してソーシャル ID を持つユーザーをサインインさせる](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/social-identities)」を参照してください)。

#### WithUseEmbeddedWebView

[WithUseEmbeddedWebView(Boolean)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokeninteractiveparameterbuilder.withuseembeddedwebview#microsoft-identity-client-acquiretokeninteractiveparameterbuilder-withuseembeddedwebview%28system-boolean%29)を使用すると、開発者は埋め込み Web ビューとシステム ブラウザー (使用可能な場合) のどちらを使用するかを指定できます。 埋め込み Web ビューは、実質的には、クライアント構成に応じて WebView1 または WebView2 コンポーネントを含むポップアップです。 詳細については、「[Web ブラウザー (MSAL.NET) の使用](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/using-web-browsers)」および「[MSAL.NET での WebView2 の使用](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/webview2)」を参照してください。

トークンを取得するときに埋め込み Web ビューを使用するかどうかを指定できます。

```csharp
result = await app.AcquireTokenInteractive(scopes)
                  .WithUseEmbeddedWebView(true)
                  .ExecuteAsync();
```

Note

Microsoft Entra ID機関で埋め込み Web ビューを使用すると、常に従来の Web ビュー (WebView1) エンジンが使用されます。これにより、開発者が Windows Hello または FIDO 認証に依存しているシナリオが壊れる可能性があります。

#### WithExtraScopesToConsent

[WithExtraScopesToConsent(IEnumerable&lt;String&gt;)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokeninteractiveparameterbuilder.withextrascopestoconsent#microsoft-identity-client-acquiretokeninteractiveparameterbuilder-withextrascopestoconsent%28system-collections-generic-ienumerable%28%28system-string%29%29%29)は、開発者がユーザーに複数のリソースへの同意を事前にまとめて与えてもらい、通常 Microsoft ID プラットフォーム で使用される増分同意を使わずに済むようにしたい高度なケースで役立ちます。 詳細については、「 方法: 以下のいくつかのリソースに対してユーザーの同意を得る」を 参照してください。

```csharp
var result = await app.AcquireTokenInteractive(scopesForCustomerApi)
                     .WithExtraScopeToConsent(scopesForVendorApi)
                     .ExecuteAsync();
```

### ブラウザーのサポート

| ブラウザー | Pro | 短所 |
| --- | --- | --- |
| Embedded WebView1 (Internet Explorer に基づく) | - サポート対象のすべての Windows バージョンに付属 - ID ライブラリで 10 年以上使用されている | - FIDO のサポートなし (例: YubiKey)- Windows Helloのサポートなし- 以前のバージョンのWindowsでの条件付きアクセスの問題- Windows のみ |
| Embedded WebView2 (Microsoft Edge に基づく) | - FIDO とWindows Helloのサポート | - 一部の古いWindows バージョンでの条件付きアクセスの問題。- Windows のみ |
| システム ブラウザー | - 既定のシステム ブラウザーを使用します。- Chrome、Edge、Firefox は、条件付きアクセス、Windows Hello、FIDO と統合されています。- macOS、Linux、および使用可能なすべてのバージョンのWindowsで動作します。 | - やや中断を伴うユーザー エクスペリエンス (コンテキストがブラウザーに切り替わります)。 |
| [Windows Broker](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam) | - FIDO、Windows Hello、条件付きアクセス ポリシーのサポート。- Windowsと完全に統合されています。- セキュリティの向上。- Windowsでの認証のための長期的な戦略的コンポーネント。 | - レガシ MSA パススルー構成が機能しません。 MSA パススルーから離れる場合は、新しいアプリを作成することをお勧めします。- Windowsのみ (10 以降、Server 2016、Server 2019 以降)。 |

### やり方

#### 複数のリソースに対して事前にユーザーの同意を得る

Note

複数のリソースに対する同意を得ることは、Microsoft Entra IDに対しては機能しますが、Microsoft Entra B2C では機能しません。 B2C シナリオでは、管理者の同意のみがサポートされます。

Microsoft Entra ID エンドポイントでは、一度に複数のリソースのトークンを取得することはできません。 scopes パラメーターには、1 つのリソースのスコープのみを含める必要があります。 ただし、開発者は、 `extraScopesToConsent` 引数を使用して、ユーザーが複数のリソースに事前に同意していることを確認できます。

たとえば、2 つのリソースがあり、それぞれに 2 つのスコープがある場合は、次のようになります。

- `https://mytenant.onmicrosoft.com/customerapi` (2 つのスコープ `customer.read` と `customer.write`)
- `https://mytenant.onmicrosoft.com/vendorapi` (2 つのスコープ `vendor.read` と `vendor.write`)

アプリケーションは、[WithExtraScopesToConsent(IEnumerable&lt;String&gt;)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokeninteractiveparameterbuilder.withextrascopestoconsent#microsoft-identity-client-acquiretokeninteractiveparameterbuilder-withextrascopestoconsent%28system-collections-generic-ienumerable%28%28system-string%29%29%29)引数を持つトークンを対話形式で取得するときに、`extraScopesToConsent`関数を使用する必要があります。

```csharp
string[] scopesForCustomerApi = new string[]
{
  "https://mytenant.onmicrosoft.com/customerapi/customer.read",
  "https://mytenant.onmicrosoft.com/customerapi/customer.write"
};

string[] scopesForVendorApi = new string[]
{
 "https://mytenant.onmicrosoft.com/vendorapi/vendor.read",
 "https://mytenant.onmicrosoft.com/vendorapi/vendor.write"
};

var accounts = await app.GetAccountsAsync();
var result = await app.AcquireTokenInteractive(scopesForCustomerApi)
                     .WithAccount(accounts.FirstOrDefault())
                     .WithExtraScopesToConsent(scopesForVendorApi)
                     .ExecuteAsync();
```

これにより、最初の Web API のアクセス トークンが取得されます。 2 番目の API を呼び出すときは、次のように実行できます。

```csharp
AcquireTokenSilent(scopesForVendorApi, accounts.FirstOrDefault()).ExecuteAsync();
```

### Microsoft 個人アカウント

Microsoft 個人アカウントの場合、承認のためにネイティブ クライアントを呼び出すたびに同意を再度求めるメッセージが表示されるのは、想定された動作です。 ネイティブ クライアント ID は本質的に安全ではありません。Microsoft ID プラットフォームは、アプリケーションが承認されるたびに同意を求めることで、コンシューマー サービスに対してこれを軽減することを選択しました。

### プラットフォーム固有の詳細

プラットフォームによっては、対話型プロンプトに追加の構成が必要になる場合があります。

- [MSAL.NET を使用した Android Xamarinの構成要件とトラブルシューティングのヒント](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-net-xamarin-android-considerations)
- [MSAL.NET での iOS Xamarin使用に関する考慮事項](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-net-xamarin-ios-considerations)

### Samples

| Sample | Platform | 説明 |
| --- | --- | --- |
| [active-directory-dotnet-desktop-msgraph-v2](https://github.com/azure-samples/active-directory-dotnet-desktop-msgraph-v2) | デスクトップ (WPF) | Microsoft Graph API を呼び出す Windows デスクトップ .NET (WPF) アプリケーション。 [Image: WPF アプリ トポロジ] |
| https://github.com/Azure-Samples/active-directory-dotnet-native-aspnetcore-v2 | WPF、ASP.NET Core 2.0 Web API | Azure AD v2.0 を使用して ASP.NET Core Web API を呼び出すWPF アプリケーション。 [Image: デスクトップ と Web アプリの対話トポロジ] |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/desktop-mobile/adfs-support"} -->
## MSAL.NET での ADFS のサポート - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/adfs-support
- Service: msal / msal-dotnet
- Article date: 2023-08-24
- Summary: .NET (MSAL.NET) のMicrosoft Authentication Libraryでの Active Directory フェデレーション サービス (AD FS) (ADFS) のサポートについて説明します。

Windows Server の Active Directory フェデレーション サービス (AD FS) (ADFS) を使用すると、開発しているアプリケーションに OpenID Connect と OAuth 2.0 ベースの認証と承認を追加できます。 これらのアプリケーションは、ADFS に対してユーザーを直接認証できます。 詳細については、「 [開発者向け AD FS シナリオ](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/overview/ad-fs-openid-connect-oauth-flows-scenarios)」を参照してください。

.NET (MSAL.NET) のMicrosoft Authentication Libraryでは、AD FS に対する認証に次の 2 つのシナリオがサポートされています。

- MSAL.NET は、それ自体が AD FS と*フェデレーションされている*Microsoft Entra IDと話します。
- MSAL.NET は、ADFS 機関と**直接**やり取りします。 これは、ADFS 2019 以降でのみサポートされています。 この重要なシナリオの 1 つは、[Azure Stack](https://azure.microsoft.com/overview/azure-stack/)サポートです。

### ID プロバイダーが Microsoft Entra ID とフェデレーションされている場合

MSAL.NET は、マネージド ユーザー (Microsoft Entra ID に登録されているユーザー) またはフェデレーション ユーザー (別の ID プロバイダーによって管理され、ADFS を介してフェデレーションされるユーザー) をサインインさせる Microsoft Entra ID とやり取りすることをサポートしています。 MSAL.NET は、管理対象ユーザーとフェデレーション ユーザーを区別するわけではありません。これは、すべてのシナリオでMicrosoft Entra IDと直接通信しているかのように動作するためです。

このシナリオで使用する [機関](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-applications) は、通常の機関 (`common`、 `organizations`、またはテナント ID) です。

#### 対話形式でトークンを取得する

`AcquireTokenInteractive`を呼び出すと、ユーザー エクスペリエンスは通常、次の手順に従います。

- ユーザーは、ユーザー ID (または`loginHint`の呼び出しの一部として提供されるアカウントまたは&lt;xref:Microsoft.Identity.Client.IPublicClientApplication.AcquireTokenAsync(System.Collections.Generic.IEnumerable{System.String})&gt;) を入力します。
- Microsoft Entra IDでは、簡単な "組織のページに移動する" UI が表示されます。
- Microsoft Entra IDは、ユーザーを ID プロバイダーのサインイン ページにリダイレクトします (通常は組織のブランド化でカスタマイズされます)。

このシナリオでサポートされている ADFS バージョンは、ADFS v2、ADFS v3 (Windows Server 2012 R2)、ADFS v4 (Windows Server 2016) です。

#### `AcquireTokenByIntegratedWindowsAuth`または を使用してトークンを取得する`AcquireTokenByUsernamePassword`

[`AcquireTokenByIntegratedWindowsAuth`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokenbyintegratedwindowsauthparameterbuilder)メソッドまたは[`AcquireTokenByUsernamePassword`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokenbyusernamepasswordparameterbuilder)メソッドを使用してトークンを取得する場合、MSAL.NET はユーザー名に基づいて ID プロバイダーを取得します。 MSAL.NET は、ID プロバイダーに接続した後に [SAML 1.1 トークン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/saml-tutorial)を受け取ります。 次に、MSAL.NET は、JSON Web Token (JWT) を取得するための [On-Behalf-Of フロー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/on-behalf-of-flow)と同様に、SAML トークンをユーザー アサーションとして Microsoft Entra ID に提供します。

Warning

**Microsoftでは、ユーザー名とパスワードのフローの使用は推奨されません**。 ほとんどのシナリオでは、より安全な代替手段が利用でき、( [Web アカウント マネージャー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam)の使用など) 推奨されます。 このフローでは、アプリケーションに非常に高い信頼が必要であり、他のフローに存在しないリスクが伴います。 このフローは、より安全なフローが実行可能ではない場合にのみ使用してください。 この許可の使用を避けたい理由の詳細については、[パスワードを過去のものにするためにMicrosoftが取り組んでいる理由を](https://news.microsoft.com/features/whats-solution-growing-problem-passwords-says-microsoft/)参照してください。

### MSAL が ADFS に直接接続する場合

MSAL.NET は、OpenID Connect (OIDC) に準拠し、[Proof Key for Code Exchange (PKCE)](https://oauth.net/2/pkce/) とスコープをサポートする ADFS 2019 に接続することをサポートしています。 このサポートでは、必要な更新プログラム ([KB 4490481](https://support.microsoft.com/help/4490481/windows-10-update-kb4490481)) をWindows Serverインストールに適用する必要があります。 ADFS に直接接続する場合、アプリケーションのビルドに使用する権限は、 `https://mysite.contoso.com/adfs/`に似ています。

現時点では、以下への直接接続をサポートする予定はありません。

- WINDOWS SERVER 2016上の ADFS は PKCE をサポートせず、(スコープではなく) リソースを引き続き使用しているためです。
- OIDC に準拠していない ADFS v2。

MSAL.NET では、Windows Server 2016上の ADFS への直接接続をサポートする予定はありません。 オンプレミス システムを ADFS 2019 にアップグレードすると、MSAL.NET を使用できるようになります。

MSAL では、ADFS への直接接続に対する統合Windows認証 (`AcquireTokenByIntegratedWindowsAuth`呼び出しによる) はサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/desktop-mobile/device-code-flow"} -->
## MSAL.NET でのデバイス コード フローの使用 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/device-code-flow
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: Microsoft Entra ID による対話型認証には Web ブラウザーが必要です。 ただし、Web ブラウザーを提供しないデバイスやオペレーティング システムの場合、デバイス コード フローを使用すると、ユーザーは別のデバイス (別のコンピューターや携帯電話など) を使用して対話形式でサインインできます。

Note

デバイス コード フローは、Azure AD B2C ではサポートされていません。

### デバイス コード フローを使用する理由

Microsoft Entra IDを使用した対話型認証には、Web ブラウザーが必要です (詳細については、[Web ブラウザーの使用](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-web-browsers)を参照してください)。 ただし、Web ブラウザーを提供しないデバイスやオペレーティング システムの場合、デバイス コード フローを使用すると、ユーザーは別のデバイス (別のコンピューターや携帯電話など) を使用して対話形式でサインインできます。 アプリケーションは、デバイス コード フローを使用して、特にこれらのデバイス/OS 用に設計された 2 段階のプロセスを通じてトークンを取得します。 このようなアプリケーションの例としては、IoT で実行されているアプリケーションや、Command-Line ツール (CLI) などがあります。 考え方は次のとおりです。

1. ユーザー認証が必要な場合は常に、アプリによってコードが提供され、別のデバイス (インターネットに接続されたスマートフォンなど) を使用して URL ( `https://microsoft.com/devicelogin` など) に移動するようにユーザーに求められます。ここで、ユーザーはコードの入力を求められます。 これにより、Web ページは、同意プロンプトや必要に応じて多要素認証など、通常の認証エクスペリエンスを通じてユーザーを誘導します。
2. 認証が成功すると、コマンドライン アプリはバック チャネルを介して必要なトークンを受け取り、それを使用して必要な Web API 呼び出しを実行します。

### 制約

- デバイス コード フローは、パブリック クライアント アプリケーションでのみ使用できます
- [PublicClientApplicationBuilder](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplicationbuilder)で渡される権限は、次のようにする必要があります。
    - テナント指定あり。形式は `https://login.microsoftonline.com/{tenant}/``tenant` で、`tenant` はテナント ID を表す GUID、またはそのテナントに関連付けられたドメインです。

#### Microsoft個人アカウントを使用したデバイス コード フロー

MSAL.NET 4.5 リリース以降、Microsoft個人用アカウントでデバイス コード フローが可能になります。 これは、デバイス コード フローが次の場合に機能することを意味します。

- すべての職場または学校アカウント (`https://login.microsoftonline.com/organizations/`) と、
- Microsoft 個人アカウント (`/common` または `/consumers` テナント)

### それを使用する方法?

#### アプリケーションの登録

アプリの **[登録](https://go.microsoft.com/fwlink/?linkid=2083908)** 中に、アプリケーションの **[認証** ] セクションで次の手順を実行します。

- 応答 URI は次のようになります。 `https://login.microsoftonline.com/common/oauth2/nativeclient`
- **既定のクライアントの種類** の段落にある **アプリケーションをパブリック クライアントとして扱う** という質問に対して、**はい** を選択する必要があります。

    [Image: Microsoft Entra クライアントの種類]

#### Code

[Image: IPublicClientApplication インターフェイス]

`IPublicClientApplication`という名前のメソッドが含まれています `AcquireTokenWithDeviceCode`

```csharp
 AcquireTokenWithDeviceCode(IEnumerable<string> scopes, 
                            Func<DeviceCodeResult, Task> deviceCodeResultCallback)
```

このメソッドは、次のパラメーターを取ります:

- アクセストークンの要求対象である`scopes`
- `DeviceCodeResult` を受信するコールバック

    [Image: DeviceCoreResult クラス]

次のように呼び出して、省略可能なパラメーターを渡すことができます。

- `.WithExtraQueryParameters(Dictionary{string, string})` を使用して、追加のクエリ パラメーターを渡します。 これは、テスト環境をターゲットにしたり、グローバリゼーションに役立ちます (以下を参照)。 `string.Empty`を渡すことができます。
- `.WithAuthority(string, bool)` は、アプリケーションの構築時に設定されたデフォルト権限をオーバーライドするために使用されます。 オーバーライドする権限は、アプリケーション構築に追加される既知の権限の一部である必要があることに注意してください。

### コードスニペット

次のサンプル コードでは、最新のケースと、取得できる例外の種類とその軽減策について説明します。

```csharp
private const string ClientId = "<client_guid>";
private const string Authority = "https://login.microsoftonline.com/contoso.com";
private readonly string[] Scopes = new string[] { "user.read" };

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
        return await pca.AcquireTokenSilent(Scopes, accounts.FirstOrDefault())
            .ExecuteAsync();
    }
    catch (MsalUiRequiredException ex)
    {
        // No token found in the cache or Azure AD insists that a form interactive auth is required (e.g. the tenant admin turned on MFA)
        // If you want to provide a more complex user experience, check out ex.Classification 

        return await AcquireByDeviceCodeAsync(pca);
    }         
}

private async Task<AuthenticationResult> AcquireByDeviceCodeAsync(IPublicClientApplication pca)
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
    // TODO: handle or throw all these exceptions
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
        // See /dotnet/standard/threading/cancellation-in-managed-threads 
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

### MSAL.NET を使用したデバイス コード フローを使用したトークンの取得を示すサンプル

| Sample | Platform | 説明 |
| --- | --- | --- |
| [active-directory-dotnetcore-devicecodeflow-v2](https://github.com/Azure-Samples/active-directory-dotnetcore-devicecodeflow-v2) | コンソール (.NET Core) | .NET Core 2.1 コンソール アプリケーションでは、ユーザーは Azure AD v2.0 エンドポイントを使用して、Web ブラウザーを備えた別のデバイス経由でサインインすることにより、Microsoft Graph のトークンを取得できます [Image: デバイス コード フローの構成] |

### 追加情報

デバイス コード フローの詳細を確認する場合:

- [OAuth 標準 - デバイスフロー](https://tools.ietf.org/html/draft-ietf-oauth-device-flow-07#section-3.4)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/desktop-mobile/integrated-windows-authentication"} -->
## 統合Windows認証での MSAL.NET の使用 (IWA) - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/integrated-windows-authentication
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: デスクトップまたはモバイル アプリケーションがWindowsで実行され、Windows ドメインに接続されているコンピューター (Active Directoryまたは参加Microsoft Entra) 上で実行されている場合は、統合Windows認証 (IWA) を使用してトークンをサイレントで取得できます。 アプリケーションを使用するときに UI は必要ありません。

Note

統合Windows認証 (IWA) は非推奨となり、サイレント トークン取得のためのより堅牢で最新のメカニズムである [WAM](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam) に置き換えられました。 WAM では、複雑な構成を必要とせずに、現在のWindows ユーザーに対してサイレント シングル サインオン (SSO) を有効にします。 また、個人のMicrosoft アカウントもサポートしています。 WAM は内部的には、IWA とプライマリ 更新トークン (PRT) の引き換えなど、複数の戦略を活用してトークンをサイレントで取得するため、従来の IWA に関連する多くの制限に対処します。

IWA ドキュメントは、既存の運用デプロイを維持するためにのみ参照する必要があります。 OS アカウントを使用して WAM for Single Sign-On (SSO) への移行を計画している場合は、以下のサンプル実装のガイダンスを参照し、 [詳細については WAM](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam) を参照してください。

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

try
{    
    result = await app.AcquireTokenSilent(scopes, PublicClientApplication.OperatingSystemAccount)
                          .ExecuteAsync(); // this will try to SSO silently with Windows OS logged in account. 
}
// Can't get a token silently, go interactive
catch (MsalUiRequiredException ex)
{
    result = app.AcquireTokenInteractive(scopes)
                         .WithAccount(PublicClientApplication.OperatingSystemAccount)
                         .ExecuteAsync();
}

```

デスクトップまたはモバイル アプリケーションがWindowsで実行され、Windows ドメインに接続されているコンピューター (Active Directoryまたは参加Microsoft Entra) 上で実行されている場合は、統合Windows認証 (IWA) を使用してトークンをサイレントで取得できます。 アプリケーションを使用するときに UI は必要ありません。

### IWAの制約

- **フェデレーション** ユーザーのみ(つまり、Active Directoryで作成され、Microsoft Entra IDによってサポートされているユーザー)。 MICROSOFT ENTRA IDで直接作成されたユーザー (AD バッキングなし、 **マネージド** ユーザー) は、この認証フローを使用できません。 この制限は、ユーザー名/パスワード フローには影響しません。
- MSA ユーザーでは機能しません。 MSA の場合は [WAM](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam) を試す
- IWA は、.NET および .NET Framework 用に記述されたアプリケーション用です。
- IWA は MFA (多要素認証) をバイパスしません。 MFA が構成されている状況では、MFA チャレンジが必要な場合に IWA が失敗する可能性があります。これは、MFA でユーザーの操作が必要になるためです。

>
> これは難しいです。 IWA は非対話型ですが、2FA にはユーザーの対話機能が必要です。 ID プロバイダーが 2FA の実行を要求するタイミングは制御しません。テナント管理者は実行します。 私たちの観察から、2FAは、あなたが別の国からログインするとき、VPN経由で企業ネットワークに接続されていない場合、時にはVPN経由で接続されている場合でも必要です。 決定論的な一連のルールを想定しないでください。Microsoft Entra IDは AI を使用して、2FA が必要かどうかを継続的に学習します。 IWA が失敗した場合は、 [ユーザー プロンプト](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/acquiring-tokens-interactively) にフォールバックする必要があります
- `PublicClientApplicationBuilder`で渡される権限は、次のようにする必要があります。

    - テナント指定（`https://login.microsoftonline.com/{tenant}/` の形式。ここで、`tenant` はテナント ID を表す GUID、またはテナントに関連付けられたドメインのいずれかです。）
    - 任意の職場および学校アカウントの場合 (`https://login.microsoftonline.com/organizations/`)

>
> Microsoft の個人用アカウントはサポート対象外です（/common または /consumers テナントは使用できません）
- 統合Windows認証はサイレント フローであるため、

    - アプリケーションのユーザーは、アプリケーションの使用に以前に同意している必要があります
    - または、テナント管理者が、アプリケーションを使用するためにテナント内のすべてのユーザーに以前に同意している必要があります。
    - これは、次のことを意味します。
        - 開発者が自分用に Azure portal 上の **[許可]** ボタンをクリックしておきます。
        - または、テナント管理者が、アプリケーションの登録の **API アクセス許可**タブにある **{tenant domain} に対する管理者の同意の付与/取り消**しボタンを押しました ([Web API にアクセスするためのアクセス許可の追加を](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/quickstart-configure-app-access-web-apis#add-permissions-to-access-web-apis)参照)
        - または、ユーザーがアプリケーションに同意する方法を提供している場合 ( [「個々のユーザーの同意を要求する」を](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-permissions-and-consent#requesting-individual-user-consent)参照)
        - または、テナント管理者がアプリケーションに同意する方法を提供している場合 (管理者の [同意](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-permissions-and-consent#requesting-consent-for-an-entire-tenant)を参照)
- このフローは、.net デスクトップ、.net core、および Windows Universal Apps で有効になります。

同意の詳細については、[v2.0 のアクセス許可と同意に](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-permissions-and-consent)関するページを参照してください

### それを使用する方法?

#### アプリケーションの登録

アプリの **[登録](https://go.microsoft.com/fwlink/?linkid=2083908)** 中に、アプリケーションの **[認証** ] セクションで次の手順を実行します。

- 応答 URI を指定する必要はありません
- [アプリケーションを**パブリック クライアントとして扱う**] という質問の回答として [**はい**] を選択する必要があります (**[既定のクライアントの種類**] 段落)

    [Image: Microsoft Entra クライアントの種類]

#### Code

[IPublicClientApplication](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.ipublicclientapplication) には、次のメソッドが含まれています。 `AcquireTokenByIntegratedWindowsAuth`

[Image: IPublicClientApplication インターフェイス]

```csharp
AcquireTokenByIntegratedWindowsAuth(IEnumerable<string> scopes)
```

通常は、1 つのパラメーター (`scopes`) のみを使用する必要があります。 ただし、Windows管理者がポリシーを設定する方法によっては、windows コンピューター上のアプリケーションがログインユーザーを参照できない可能性があります。 その場合は、2 つ目の方法 `.WithUsername()` を使用し、ログインしているユーザーのユーザー名を UPN 形式 ( `joe@contoso.com`) として渡します。

次の例では、最新のケースと、取得できる例外の種類とその軽減策について説明します。

```csharp
static async Task GetATokenForGraph()
{
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
    // Mitigation: as explained in the message from Azure AD, the authoriy needs to be tenanted or otherwise organizations

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
      // Explanation: This method relies on an a protocol exposed by Active Directory (AD). If a user was created in Azure 
      // Active Directory without AD backing ("managed" user), this method will fail. Users created in AD and backed by 
      // Azure AD ("federated" users) can benefit from this non-interactive method of authentication.
      // Mitigation: Use interactive authentication
   }
 }

 Console.WriteLine(result.Account.Username);
}
```

注: 次のエラーが発生した場合:

*"Microsoft.Identity.Client.MsalClientException: ユーザー名を取得できませんでした ---&gt; System.ComponentModel.Win32Exception: アカウント名とセキュリティ ID の間でマッピングは行われませんでした"*

つまり、Active Directory (AD) アカウントではなく、ローカル コンピューター アカウントを使ってデバイスにサインインしている可能性があります。 デバイスがドメインに追加されていること、および AD によってサポートされている現在サインインしているユーザーであることを確認してください。 デバイス上のローカル アカウントが AD 資格情報にアクセスできないため、コンピューターをドメインだけに参加させるだけでは不十分です。

### MSAL.NET を使用した統合Windows認証によるトークンの取得を示すサンプル

| Sample | Platform | 説明 |
| --- | --- | --- |
| [active-directory-dotnet-iwa-v2](https://github.com/Azure-Samples/active-directory-dotnet-iwa-v2) | コンソール (.NET) | .NET Core コンソール アプリケーションを使用すると、ユーザーはWINDOWSにサインインし、AZURE AD v2.0 エンドポイントを使用して IWA [Image: コンソール アプリケーションのMicrosoft Graph トポロジのトークンを]取得できます |

### 追加情報

統合Windows認証の詳細については、以下をご覧ください。

- V1 エンドポイントでこれを実現する方法: [ADAL.NET で Windows (Kerberos) の統合認証を使用して AcquireTokenSilent を行う](https://github.com/AzureAD/azure-activedirectory-library-for-dotnet/wiki/AcquireTokenSilentAsync-using-Integrated-authentication-on-Windows-%28Kerberos%29)

### Troubleshooting

エラー コード "parsing\_wstrust\_response\_failed" が発生した場合は、ADFS 環境で多数の構成の問題が原因である可能性があります。

これらの問題には、次のようなものがあります。

- IWA を実行するためにアカウントを使用できない
- IWA ポリシーで自動 IWA 認証が禁止されている
- プロキシまたは構成の問題により、NTLM プロトコルを使用できないことがあります（通常は、Windows 認証のためにエンドポイントから 401 [Negotiate](https://www.ietf.org/rfc/rfc4559.txt)/NTLM チャレンジが提示される場合に発生します。この問題を回避するには、独自の [HttpClient](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/httpclient) を使用するか、現在使用している .NET のバージョンを変更してみてください）。
- エラー メッセージが "オブジェクト参照がオブジェクトのインスタンスに設定されていません" の場合。警告レベルで [MSAL ログを有効](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-logging-dotnet) にして、詳細を表示します。

詳細については、「[AD FS のトラブルシューティング - 統合Windows認証](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/troubleshooting/ad-fs-tshoot-iwa)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/desktop-mobile/linux-dotnet-sdk"} -->
## Linux 上のブローカーでの MSAL.Net の使用 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/linux-dotnet-sdk
- Service: msal
- Article date: 2025-05-08
- Summary: MSAL.NET と認証ブローカーを使用してネイティブ Linux アプリでMicrosoft Entra ID認証を統合する方法について説明します。

Microsoft Authentication Library (MSAL) はソフトウェア開発キット (SDK) です。これにより、アプリは Linux ディストリビューションとは独立して配布される Linux コンポーネントである Microsoft シングル サインオンを Linux ブローカーに呼び出しますが、`sudo apt install microsoft-identity-broker`または`sudo dnf install microsoft-identity-broker`を使用してパッケージ マネージャーを使用してインストールされます。

このコンポーネントは認証ブローカーとして機能し、アプリのユーザーは、Linux に知られているアカウント (ブローカーから使用するアプリの Linux セッションにサインインしたアカウントなど) との統合の恩恵を受けることができます。

ブローカーは、Microsoft ([ポータル サイト](https://learn.microsoft.com/ja-jp/mem/intune-service/user-help/enroll-device-linux) など) によって開発されたアプリケーションの依存関係としてもバンドルされています。 インストールされているブローカーのインストールの例として、Linux コンピューターが[、Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune)などのエンドポイント管理ソリューションを介して会社のデバイスフリートに登録されている場合があります。

Note

Microsoftシングル サインオン (SSO) for Linux 認証ブローカーのサポートは、`Microsoft.Identity.Client` バージョン v4.69.1 で導入されています。

### ブローカーとは

認証ブローカーは、接続されているアカウントの認証ハンドシェイクとトークンメンテナンスを管理するユーザーのマシン上で実行されるアプリケーションです。 Linux オペレーティング システムでは、認証ブローカーとして Linux の Microsoft シングル サインオンが使用されます。 開発者と顧客にとって、次のような多くの利点があります。

- **シングル サインオンを有効にする**: アプリを使用すると、ユーザーがMicrosoft Entra IDで認証する方法を簡略化し、Microsoft Entra ID更新トークンを流出や誤用から保護できます
- **セキュリティの強化。** 多くのセキュリティ強化は、アプリケーション ロジックを更新する必要なく、ブローカーと共に提供されます。
- **機能のサポート。** ブローカー開発者の助けを借りて、豊富な OS とサービス機能にアクセスできます。
- **システム統合。** 組み込みのアカウント ピッカーでブローカー プラグ アンド プレイを使用するアプリケーションにより、ユーザーは同じ資格情報を何度も再入力する代わりに、既存のアカウントをすばやく選択できます。
- **トークン保護。** Linux Microsoftシングル サインオンを使用すると、更新トークンがデバイスにバインドされ、アプリがデバイス バインドアクセス トークンを取得[できるようになります](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/proof-of-possession-tokens)。 [「トークン保護」](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/concept-token-protection)を参照してください。

### ユーザー サインイン エクスペリエンス

このビデオでは、Linux 上のブローカー フローでのサインイン エクスペリエンスを示します

[Image: Linux ログイン コンポーネントのデモ]

### ブローカーの使用をオプトインする方法

#### アプリケーション定義の更新

MSAL Python ライブラリでは、WSL とスタンドアロン Linux の両方でブローカーを有効にする`enable_broker_on_linux` フラグを導入しました。

- Azure CLIの WSL でのみブローカー サポートを有効にすることが目的の場合は、WSL でのみ `enable_broker_on_wsl` フラグをアクティブ化するように Azure CLI アプリ コードを変更することを検討できます。
- クロスプラットフォーム アプリケーションを作成する場合は、「`enable_broker_on_windows`」の記事で説明されているように、も使用する必要があります。
- 次のオプトイン パラメーターの任意の組み合わせを true に設定できます。

| オプトイン フラグ | アプリが実行されている場合 | アプリがこれをデスクトップ プラットフォームリダイレクト URI として Azure ポータルに登録しました |
| --- | --- | --- |
| Windows でブローカーを有効にする | Windows 10+ | ms-appx-web://Microsoft.AAD.BrokerPlugin/your\_client\_id |
| enable\_broker\_on\_wsl | WSL | ms-appx-web://Microsoft.AAD.BrokerPlugin/your\_client\_id |
| enable\_broker\_on\_mac | ポータル サイトがインストールされている Mac | msauth.com.msauth.unsignedapp://auth |
| enable\_broker\_on\_linux | Intune がインストールされている Linux | `https://login.microsoftonline.com/common/oauth2/nativeclient` (有効にする必要があります) |

アプリケーションでは、ブローカー固有のリダイレクト URI をサポートする必要があります。 `Linux`具体的には、リダイレクト URI の URL は次のようにする必要があります。

```text
https://login.microsoftonline.com/common/oauth2/nativeclient
```

#### .NET インストール

ID 統合は、Linux ディストリビューションに dotnet 8 をインストールすることに依存し、 [インストール スクリプト](https://learn.microsoft.com/ja-jp/dotnet/core/install/linux-scripted-manual#scripted-install)を使用してインストールすることをお勧めします。

```bash
wget https://dot.net/v1/dotnet-install.sh -O dotnet-install.sh
chmod +x ./dotnet-install.sh
./dotnet-install.sh --version latest
```

#### パッケージの依存関係

Linux プラットフォームに次の依存関係をインストールします。

- `libsecret-tools` は、Linux キーチェーンとのインターフェイスに必要です
- `libx11-6` パッケージ。 `libx11` ライブラリを使用して Linux 上のコンソール ウィンドウ ハンドルを取得します。
- `Microsoft.Identity.Client.NativeInterop` v0.20.2 以降では、`libwebkit2gtk-4.1-37`が必要です。 v0.20.2 より前のバージョンの場合は、 `libwebkit2gtk-4.0-37`をインストールします。

## [Ubuntu](#tab/ubuntudep)
debian/Ubuntu ベースの Linux ディストリビューションにインストールするには:

```bash
sudo apt install libx11-6 libc++1 libc++abi1 libsecret-1-0 libwebkit2gtk-4.1-37 -y
```

## [Red Hat Enterprise Linux](#tab/rheldep)
Red Hat/Fedora ベースの Linux ディストリビューションにインストールするには:

```bash
sudo dnf install libx11-6 libc++1 libc++abi1 libsecret-1-0 libwebkit2gtk-4.1-37 -y
```

---

### 親ウィンドウのハンドル

ブローカーを使用するには、アプリで `libx11` ライブラリを使用して、モーダル ダイアログの親となるウィンドウ ハンドルを指定する必要があります。 ウィンドウ ハンドルは、MSAL 自体が親ウィンドウを推論することは不可能であるため、開発者が提供する必要があります。 以前は、親ウィンドウの処理が不足すると、認証ウィンドウがアプリケーション ウィンドウの背後に隠れていたユーザー エクスペリエンスが悪くなっていました。

コンソール アプリケーションの場合、 `libx11`を使用するサンプル コードを次に示します。

```csharp
using System;
using System.Runtime.InteropServices;

class X11Interop
{
    [DllImport("libX11")]
    public static extern IntPtr XOpenDisplay(IntPtr display);

    [DllImport("libX11")]
    public static extern IntPtr XDefaultRootWindow(IntPtr display);

    public static void Main()
    {
        IntPtr display = XOpenDisplay(IntPtr.Zero);
        if (display == IntPtr.Zero)
        {
            Console.WriteLine("Unable to open X display.");
            return;
        }

        IntPtr rootWindow = XDefaultRootWindow(display);
        Console.WriteLine($"Root window handle: {rootWindow}");
    }
}
```

### サンプル アプリを実行する

テスト アプリを設定するには、次に示すように独自のコンソール アプリを作成するか、パス [/tests/devapps/WAM/NetWSLWam/Class1.cs](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet)の下にある [microsoft-authentication-library-for-dotnet](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/blob/main/tests/devapps/WAM/NetWSLWam/Class1.cs) で提供されているサンプル アプリを使用して更新します。

Linux プラットフォームでブローカーを使用するには、次のコード スニペットに示すように、 `BrokerOptions` を `OperatingSystems.Linux` に設定します。

サンプル アプリケーションは[、MSAL.NET GitHub リポジトリ](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/tree/main/tests/devapps/WAM/NetWSLWam)で入手できます。

```csharp
using Microsoft.Identity.Client;
using Microsoft.Identity.Client.Broker;

class Program
{
    /// <summary>
    /// Get the handle of the console window for Linux
    /// </summary>
    [DllImport("libX11")]
    private static extern IntPtr XOpenDisplay(string display);

    [DllImport("libX11")]
    private static extern IntPtr XRootWindow(IntPtr display, int screen);

    [DllImport("libX11")]
    private static extern IntPtr XDefaultRootWindow(IntPtr display);

    public static string ClientID = "your client id";
    public static string[] Scopes = { "User.Read" };

    public static async Task InvokeBrokerAsync()
    {
        IntPtr _parentHandle = XRootWindow(XOpenDisplay(null), 0);
        if (_parentHandle == IntPtr.Zero)
        {
            Console.WriteLine("Unable to open X display.");
            return;
        }

        Func<IntPtr> consoleWindowHandleProvider = () => _parentHandle;

        // 1. Configuration - read below about redirect URI
        var pca = PublicClientApplicationBuilder.Create(ClientID)
                        .WithAuthority("https://login.microsoftonline.com/common")
                        .WithDefaultRedirectUri()
                        .WithBroker(new BrokerOptions(BrokerOptions.OperatingSystems.Linux){
                            ListOperatingSystemAccounts = true,
                            MsaPassthrough = true,
                            Title = "MSAL WSL Test App"
                        })
                        .WithParentActivityOrWindow(consoleWindowHandleProvider)
                        .WithLogging((x, y, z) => Console.WriteLine($"{x} {y}"), LogLevel.Verbose, true)
                        .Build();

        // Add a token cache, see https://learn.microsoft.com/entra/msal/dotnet/how-to/token-cache-serialization?tabs=desktop

        // 2. GetAccounts
        var accounts = await pca.GetAccountsAsync().ConfigureAwait(false);
        var accountToLogin = accounts.FirstOrDefault();

        try
        {
            var authResult = await pca.AcquireTokenSilent(Scopes, accountToLogin)
                                    .ExecuteAsync().ConfigureAwait(false);
        }
        catch (MsalUiRequiredException ex)
        {
            Console.WriteLine(ex.Message);
            Console.WriteLine(ex.ErrorCode);
        }

        try
        {
            var authResult = await pca.AcquireTokenInteractive(Scopes)
                                    .ExecuteAsync().ConfigureAwait(false);

            Console.WriteLine(authResult.Account);

            Console.WriteLine("Acquired Token Successfully!!!");

            Console.WriteLine("Account: " + authResult.Account.Username);
            Console.WriteLine("Token: " + authResult.AccessToken);
            Console.WriteLine("Expires On: " + authResult.ExpiresOn.ToString());
            Console.WriteLine("Scopes: " + string.Join(", ", authResult.Scopes));
        }
        catch (MsalUiRequiredException ex)
        {
            Console.WriteLine(ex.Message);
            Console.WriteLine(ex.ErrorCode);

        }
        catch (MsalClientException ex)
        {
            int errorCode = Marshal.GetHRForException(ex) & ((1 << 16) - 1);
            Console.WriteLine("MsalClientException (ErrCode " + errorCode + "): " + ex.Message);
        }
        catch (MsalException ex)
        {
            Console.WriteLine($"MsalException Error signing-out user: {ex.Message}");
        }
        catch (Exception ex)
        {
            int errorCode = Marshal.GetHRForException(ex) & ((1 << 16) - 1);
            Console.WriteLine("Error Acquiring Token (ErrCode " + errorCode + "): " + ex);
        }
        Console.Read();
    }

    public static void Main(string[] args)
    {
        InvokeBrokerAsync().Wait();
    }
}

```

サンプル アプリを実行するには:

```bash
# Run From the root folder of microsoft-authentication-library-dotnet directory
dotnet run --project tests/devapps/WAM/NetWSLWam/test.csproj
```

### ユーザー名とパスワードのフロー

このフロー (リソース所有者パスワード資格情報 (ROPC) とも呼ばれます) は、テスト シナリオや、リソースへのサービス プリンシパル アクセスによってアクセスが多くなりすぎて、ユーザー フローでのみスコープを絞り込むことができるシナリオを除き、推奨されません。 ブローカーを使用する場合、 [`AcquireTokenByUsernamePassword`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplication.acquiretokenbyusernamepassword) はブローカーがプロトコルを管理し、トークンをフェッチできるようにします。

Warning

アプリケーションがユーザーにパスワードを直接求めるので、Microsoftはユーザー名とパスワードのフローを使用しないことをお勧めします。これは安全でないパターンです。 さらに、ROPC フローでは、**個人のMicrosoft アカウント**と**、多要素認証が有効なMicrosoft Entra アカウント**はサポートされません。 完全な概要については、[Microsoft ID プラットフォームと OAuth 2.0 リソース所有者のパスワード資格情報](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-oauth-ropc)を確認してください。

### Linux のMicrosoft シングル サインオンの制限事項

- Azure B2C および Active Directory フェデレーション サービス (AD FS) (ADFS) 機関はサポートされていません。 MSAL は、ユーザー認証にブラウザーを使用するようにフォールバックします。
- サポートされていない構成では、MSAL はブラウザーにフォールバックします。

### 統合のベスト プラクティス

お客様が MSAL+Broker の優れた経験を持っていることを確認するために、次の原則に従うことが強くお勧めします。

1. **認証の前にユーザー コンテキストを指定**します。 認証の理由と共に、認証が必要であることをユーザーに通知する UI またはウィンドウを描画します。 アプリケーションがバックグラウンド サービスである場合の利点について説明します。
2. **ユーザー アクションに基づいて認証を呼び出します**。 ユーザーは、リンクまたはボタンをクリックするか、別のジェスチャを実行して、特定のアプリケーションで認証プロセスをトリガーしたことを理解する必要があります。 ユーザーは、コンテキストやアクションがアタッチされていないオペレーティング システム内にポップアップ表示されるウィンドウに資格情報を入力しないでください。
3. **最初に [トークンを自動的に取得](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokensilentparameterbuilder) し、失敗した場合は [対話型プロンプト](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokeninteractiveparameterbuilder) にフォールバック**します。 お客様は、資格情報の再入力またはポリシー要件を満たす明示的な必要がある場合にのみ、対話型認証を求めるメッセージを表示する必要があります。

### Troubleshooting

#### "MsalClientException (ErrCode 5376): この認証フローでは、少なくとも 1 つのスコープを要求する必要があります。" というエラー メッセージ

このメッセージは、他の OIDC スコープ (`user.read`、`profile`、または`email`) と共に、少なくとも 1 つのアプリケーション スコープ (`offline_access` など) を要求する必要があることを示します。

```csharp
var authResult = await pca.AcquireTokenInteractive(new[] { "user.read" })
                 .ExecuteAsync();
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/desktop-mobile/linux-dotnet-sdk-wsl"} -->
## Linux 用 Windows サブシステムでの MSAL.Net の使用 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/linux-dotnet-sdk-wsl
- Service: msal
- Article date: 2025-05-08
- Summary: MSAL.NET と Linux ブローカーの Microsoft シングル サインオンを使用して WSL アプリでMicrosoft Entra ID認証を統合する方法について説明します。

MSAL は、Linux ディストリビューションとは無関係に配布される Linux コンポーネントである Microsoft シングル サインオンを Linux に呼び出すことが可能ですが、`sudo apt install microsoft-identity-broker`または`sudo dnf install microsoft-identity-broker`を使用してパッケージ マネージャーを使用してインストールされます。

このコンポーネントは認証ブローカーとして機能し、アプリのユーザーは、Linux に知られているアカウント (ブローカーから使用するアプリの Linux セッションにサインインしたアカウントなど) との統合の恩恵を受けることができます。 また、[ポータル サイト](https://learn.microsoft.com/ja-jp/mem/intune-service/user-help/enroll-device-linux)など、Microsoftによって開発されたアプリケーションの依存関係としてバンドルされています。 これらのアプリケーションは、Linux コンピューターが、[Microsoft Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/what-is-intune)などのエンドポイント管理ソリューションを介して会社のデバイス フリートに登録されるときにインストールされます。

Note

Microsoftシングル サインオン (SSO) for Linux 認証ブローカーのサポートは、`Microsoft.Identity.Client` バージョン v4.69.1 で導入されています。

Linux で認証ブローカーを使用すると、ユーザーがアプリケーションからMicrosoft Entra IDで認証する方法を簡略化し、Microsoft Entra ID更新トークンを流出や誤用から保護する将来の機能を利用できます。

MSAL.NET を使用して WSL アプリで SSO を有効にするには、キーチェーンが設定され、ロック解除されていることを確認する必要があります。MSAL はキーリング デーモンと通信するために`libsecret`を使用するためです。

### ユーザー サインイン エクスペリエンス

このビデオでは、Linux 上のブローカー フローでのサインイン エクスペリエンスを示します

[Image: Linux ログイン コンポーネントのデモ]

### WSL の最新バージョンへの更新

最新の WSL リリースに更新していることを確認します。 WAM アカウント制御ダイアログは、WSL バージョン 2.4.13 以降でサポートされています。

```powershell
# To check what distros are available:
wsl.exe --list --online

wsl.exe --install Ubuntu-22.04

# To check the WSL version:
wsl --version

# To update WSL:
wsl --update
```

### Prerequisites

#### .NET インストール

ID 統合は、Linux ディストリビューションに dotnet 8 をインストールすることに依存し、 [インストール スクリプト](https://learn.microsoft.com/ja-jp/dotnet/core/install/linux-scripted-manual#scripted-install)を使用してインストールすることをお勧めします。

```bash
# Download the install script
wget https://dot.net/v1/dotnet-install.sh -O dotnet-install.sh
chmod +x ./dotnet-install.sh
./dotnet-install.sh --version latest

# To update the path if using bash (remember to reset your connection afterword):
vi .bashrc
export DOTNET_ROOT=~/.dotnet
export PATH=$PATH:$DOTNET_ROOT:$DOTNET_ROOT/tools
```

#### パッケージの依存関係

Linux プラットフォームに次の依存関係をインストールします。

- `libsecret-tools` は、Linux キーチェーンとのインターフェイスに必要です
- `libx11-dev` パッケージ。 `libx11` ライブラリを使用して Linux 上のコンソール ウィンドウ ハンドルを取得します。
- `Microsoft.Identity.Client.NativeInterop` v0.20.2 以降では、`libwebkit2gtk-4.1-37`が必要です。 v0.20.2 より前のバージョンの場合は、 `libwebkit2gtk-4.0-37`をインストールします。

## [Ubuntu](#tab/ubuntudep)
debian/Ubuntu ベースの Linux ディストリビューションにインストールするには:

```bash
sudo apt install libx11-6 libc++1 libc++abi1 libsecret-1-0 libwebkit2gtk-4.1-37 -y

#from Powershell, run
wsl.exe --shutdown
```

## [Red Hat Enterprise Linux](#tab/rheldep)
Red Hat/Fedora ベースの Linux ディストリビューションにインストールするには:

```bash
sudo dnf install libx11-6 libc++1 libc++abi1 libsecret-1-0 libwebkit2gtk-4.1-37 -y

#from Powershell, run
wsl.exe --shutdown
```

---

Important

キーチェーンが意図したとおりに機能するようにするには、次のことを確認してください。1. 依存関係をインストールします。2. WSL を再起動します。3. キーチェーンを構成します。 正しい順序で手順を実行しないと、キーチェーンに "パスワード キーチェーン" オプションが表示されなくなります。

#### WSL でキーリングを設定する

MSAL は Linux 上で `libsecret` を使用します。 `keyring` デーモンと通信する必要があります。 ユーザーは [、シーホース](https://wiki.gnome.org/Apps/Seahorse/) (暗号化キーとパスワードを管理するための GNOME アプリケーション) を使用して、グラフィカル ユーザー インターフェイス (GUI) を使用して `keyring` コンテンツを管理できます。

Debian ベースのディストリビューションでは、 `sudo apt install seahorse` を実行し、次の手順に従ってパッケージをインストールできます。

1. ターミナルで (sudo ではなく) 通常のユーザーとして `seahorse` を実行する

    [Image: 既定のキーチェーン ダイアログ]
2. 左上隅にある [ **+** を選択し、 **パスワード** キーリングを作成します。

    [Image: キーチェーン ダイアログでパスワード キーリングを選択する]
3. 'login' という名前のキーリングを作成する

    [Image: プロンプトへのログインの入力]
4. 次のダイアログでパスワードを設定します。 [Image: パスワードの選択と確認]
5. Windows ターミナルから`wsl.exe --shutdown`を実行します。
6. 新しい WSL セッションを開始し、サンプルを実行します。 キーリング パスワードの入力を求められます。

### サンプル アプリを実行する

Linux プラットフォームでブローカーを使用するには、次のコード スニペットに示すように、 `BrokerOptions` を `OperatingSystems.Linux` に設定してください。

プロジェクトを構成する方法については、「[MSAL.NET を使用してネイティブ Linux アプリで SSO を有効にする](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/linux-dotnet-sdk)」を参照してください。

テスト アプリを設定するには、パス [/tests/devapps/WAM/NetWSLWam/Class1.cs](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet) の下にある [microsoft-authentication-library-for-dotnet](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/blob/main/tests/devapps/WAM/NetWSLWam/Class1.cs) で提供されているサンプル アプリを使用します。

サンプル アプリを実行するには:

```bash
# Run From the root folder of microsoft-authentication-library-dotnet directory
dotnet run --project tests/devapps/WAM/NetWSLWam/test.csproj
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/desktop-mobile/macos-broker-dotnet-sdk"} -->
## macOS ブローカーでの MSAL.NET の使用 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/macos-broker-dotnet-sdk
- Service: msal / msal-dotnet
- Article date: 2026-01-28
- Summary: MSAL.NET アプリケーションで macOS 認証ブローカーを有効にして使用し、システム アカウントと統合し、macOS で安全でシームレスなサインイン エクスペリエンスを提供する方法について説明します。

MSAL.NET macOS 認証ブローカーと統合して、オペレーティング システムに知られているアカウントを利用して、リッチ シングル サインオン (SSO) とセキュリティで保護されたトークンの取得を提供できます。 これにより、アプリのユーザーは、既存のMicrosoft Entra ID (Azure AD) またはMicrosoft アカウントを使用してサインインできます。プロンプトが少なく、セキュリティ体制が向上します。

### ブローカーとは

認証ブローカーは、ユーザーのマシン上で実行され、接続されているアカウントの認証ハンドシェイクとトークンのライフサイクルを管理するコンポーネントまたはアプリケーションです。 Windowsでは、このロールは Web アカウント マネージャー (WAM) によって実行されます。 macOS では、ブローカーには ポータル サイト アプリが付属しています。 主な利点は次のとおりです。

- **セキュリティの強化。** トークン処理と資格情報プロンプトに対するセキュリティの強化は、アプリ コードの更新を必要とせずに、OS の更新プログラムまたはブローカーの更新プログラムを介して提供されます。
- **機能のサポート。** ブローカーは、使用可能なWindows同等の機能 (条件付きアクセス、デバイス コンプライアンス チェック) へのアクセスを可能にし、macOS のセキュリティで保護されたエンクレーブとシステム キーチェーンを活用します。
- **システム統合。** ユーザーは、既存のサインインしたアカウントを再利用して、資格情報の再入力を減らし、生産性を向上させることができます。
- **トークン保護。** ブローカーは、更新トークンがデバイス コンテキストに適切にバインドされていることを確認し、構成時に所有証明 (PoP) アクセス トークンの取得をサポートします。

### macOS ブローカーの有効化

Important

macOS ブローカーのサポートを得るには、MSAL.NET 4.73.1 以降を使用します。 最新バージョンの `Microsoft.Identity.Client` と `Microsoft.Identity.Client.Broker`を使用することをお勧めします。

Important

[ポータル サイト アプリ](https://learn.microsoft.com/ja-jp/intune/intune-service/user-help/enroll-your-device-in-intune-macos-cp)を使用して macOS デバイスを登録します。 登録が完了したら、他のMicrosoft アプリが SSO 拡張機能を介してブローカーと通信できることを確認します (たとえば、ポータル サイト経由でWordサインインできます)。

ブローカーのサポートは、次の 2 つのパッケージに分割されます。

- [Microsoft。Identity.Client](https://www.nuget.org/packages/Microsoft.Identity.Client/) – トークン取得用のコア ライブラリ。
- [Microsoft。Identity.Client.Broker](https://www.nuget.org/packages/Microsoft.Identity.Client.Broker/) – プラットフォーム ブローカー (WAM、macOS ブローカー、Linux ブローカーなどWindows) を介した認証のサポートを追加します。

関連するパッケージを参照した後、macOS ブローカー構成で [`WithBroker(BrokerOptions)`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.broker.brokerextension.withbroker) を呼び出します。 Windowsとは異なり、macOS ブローカー フローでは、親ウィンドウ ハンドルの設定はサポートされていません。

Note

`using Microsoft.Identity.Client.Broker;`ステートメントを追加して、正しい`WithBroker`拡張オーバーロードにアクセスしてください。 ブローカー API は統合されています。では、 `BrokerOptions`内でサポートされているオペレーティング システムを指定します。

#### サンプル: macOS .NET アプリでブローカーを有効にする

macOS ブローカー フローの有効化は、次のように簡単です。

```csharp
    PublicClientApplicationBuilder builder = PublicClientApplicationBuilder
        .Create("04b07795-8ddb-461a-bbee-02f9e1bf7b46") // Azure CLI client id
        .WithRedirectUri("msauth.com.msauth.unsignedapp://auth")
        .WithAuthority("https://login.microsoftonline.com/organizations");

    builder = builder.WithLogging(SampleLogging);

    builder = builder.WithBroker(new BrokerOptions(BrokerOptions.OperatingSystems.OSX)
    // For Windows, please use BrokerOptions(BrokerOptions.OperatingSystems.Windows)
    {
        ListOperatingSystemAccounts = false,
        MsaPassthrough = false,
        Title = "MSAL Dev App .NET FX"
    }
    );

    IPublicClientApplication pca = builder.Build();

    // All the interactive APIs are required to be executed on the main thread
    AcquireTokenInteractiveParameterBuilder interactiveBuilder = pca.AcquireTokenInteractive(new string[] { "https://graph.microsoft.com/.default" });
    AuthenticationResult result = await interactiveBuilder.ExecuteAsync(CancellationToken.None).ConfigureAwait(false);

    IAccount account = result.Account;
    AcquireTokenSilentParameterBuilder silentBuilder = pca.AcquireTokenSilent(new string[] { "https://graph.microsoft.com/.default" }, account);
    result = await silentBuilder.ExecuteAsync(CancellationToken.None).ConfigureAwait(false);
```

ブローカー プロンプトではネイティブ macOS UI が使用されるため、対話型トークンの取得はメイン (UI) スレッドで実行する必要があります。 **すべての対話型 MSAL.NET ブローカー呼び出し (`AcquireTokenInteractive` など) は、メイン スレッドで行う必要があります**。 バックグラウンド スレッドからこれらの API を呼び出すと、例外がスローされます。

```
Microsoft.Identity.Client.MsalClientException: Interactive requests with mac broker enabled must be executed on the main thread on macOS.
```

UI ベースのアプリ (.NET MAUI や AppKit アプリなど) では、フレームワークのメッセージ ループがメイン スレッドで実行されるため、通常はシームレスです。[この MAUI サンプル アプリ](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/blob/main/tests/devapps/MacMauiAppWithBroker/MainPage.xaml.cs)を参照してください。

コンソール アプリケーションでは、非同期操作 (HTTP 要求など) がスレッド プール スレッドで再開されることがよくあります。対話型 API を呼び出す前に、必ずメイン スレッドにマーシャリングしてください。

#### サンプル: メイン スレッド スケジューラを使用したコンソール アプリ

MSAL.NET は、メイン スレッド コンテキストを維持するための [MacMainThreadScheduler](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/blob/4c04a5860490c56716a92715e92b2d607aaf1a9e/src/client/Microsoft.Identity.Client/Utils/MacMainThreadScheduler.cs#L30) を提供します。

```csharp
class MacConsoleAppWithBroker
{
    private static MacMainThreadScheduler macMainThreadScheduler = MacMainThreadScheduler.Instance();
    static void Main(string[] args)
    {
        _ = Task.Run(() => BackgroundWorker());
        macMainThreadScheduler.StartMessageLoop();
    }
    private static async Task BackgroundWorker()
    {
        await macMainThreadScheduler.RunOnMainThreadAsync(async () =>
        {
            // Your code here will be running on main thread
        }).ConfigureAwait(false);
    }
}

class YourDotnetConsoleApp
{
    private static MacMainThreadScheduler macMainThreadScheduler = MacMainThreadScheduler.Instance();

    static void Main(string[] args)
    {
        _ = Task.Run(() => BackgroundWorker());

        macMainThreadScheduler.StartMessageLoop(); // Enter main thread scheduler's message loop
    }

    private static async Task BackgroundWorker()
    {
        AcquireTokenInteractiveParameterBuilder interactiveBuilder = pca.AcquireTokenInteractive(new string[] { "https://graph.microsoft.com/.default" });
        AuthenticationResult? result = null;

        // Acquire token interactively on main thread
        await macMainThreadScheduler.RunOnMainThreadAsync(async () =>
        {
            result = await interactiveBuilder.ExecuteAsync(CancellationToken.None).ConfigureAwait(false);
            Console.WriteLine("Interactive authentication completed successfully.");
        }).ConfigureAwait(false);
    }

```

詳細については、 [このコンソール サンプル アプリ](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/blob/main/tests/devapps/MacConsoleAppWithBroker/MacConsoleAppWithBroker.cs)を参照してください。

[Image: ブローカーを使用した mac コンソール アプリのデモ]

### macOS では、MSAL.NET は親ウィンドウの設定をサポートしていません

セキュリティ上の理由から、macOS ではコンソール アプリがターミナル アプリのウィンドウ情報を取得できません。MSAL.NET は、macOS での親ウィンドウの設定をサポートしていません。 ポップアップされたブローカー プロンプトは画面の中央にあり、常にフォアグラウンドになります。

### 所有証明 (PoP) アクセス トークン

macOS ブローカーは、パブリック クライアント フローの PoP トークンの取得をサポートしています。 構成の詳細とシナリオについては、「 [所有証明トークン](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/proof-of-possession-tokens) 」を参照してください。

### リダイレクト URI

正しい Apple パッケージ バンドルを含む GUI アプリの場合:

- redirect\_uriとして `msauth.[bundle_id]://auth` を使用する

スクリプトの場合:

- 署名されていない実行可能ファイル: 使用 `msauth.com.msauth.unsignedapp://auth`
- 署名付き実行ファイル: 近日対応予定です。 現在、ブローカーは、バンドルされたアプリではない署名付き実行可能ファイルからの要求をブロックします。

### サポートされている macOS のバージョンとアーキテクチャ

macOS ブローカーでは、次の構成がサポートされています。

| コンポーネント | サポートされているバージョン |
| --- | --- |
| **Architecture** | ARM64 (Apple Silicon) と x64 (Intel) |
| **macOS バージョン** | macOS 10.15 (Catalina) 以降 |

Tip

最新のセキュリティ機能とブローカー機能との互換性を確保するために、最新の macOS バージョンに更新することをお勧めします。

### macOS ブローカーの制限事項

- Azure AD B2C および Active Directory フェデレーション サービス (AD FS) (ADFS) 機関は、macOS ブローカーを介してサポートされていません。
- 古い macOS バージョン (10.15 より前) はサポートされていません。
- 非対話型コンテキスト (メイン以外のスレッド コンテキスト) で実行すると、設計上、対話型ブローカー フローでは失敗します。
- サード パーティの IDP はサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/desktop-mobile/mobile-applications"} -->
## .NET MAUIでの MSAL.NET の使用 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/mobile-applications
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: モバイル プラットフォームで MSAL.NET を使用する方法。

MSAL.NET は、[.NET マルチプラットフォーム アプリ UI (MAUI](https://dotnet.microsoft.com/apps/maui)) で構築されたアプリケーションを介して、モバイル デバイス (iOS と Android の両方) で実行できます。

Note

.NET チームは、[既存のXamarin アプリケーションを MAUI に移行](https://learn.microsoft.com/ja-jp/dotnet/maui/migration/)することをお勧めします。 新しいアプリケーションでは常に MAUI を使用する必要があります。 MSAL.NET バージョン 4.61.0 以降では、Xamarin Android および Xamarin iOS はサポートされていません。

### モバイル デバイスでのブローカーでの MSAL.NET の使用

MSAL.NET は、Microsoft Authenticatorやポータル サイトなどのモバイル デバイス上の認証ブローカーで使用できます。 iOS および Android でブローカーを使用するようにアプリケーションを構成する方法の詳細については、「Xamarin アプリケーションで [Microsoft Authenticator または Intune ポータル サイトを使用](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-use-brokers-with-xamarin-apps)する」を参照してください。

### ANDROID でのマウイ島

Android での MSAL.NET 統合を開始するには、次のリソースを参照してください。

- [Xamarin ADAL アプリを ANDROID 用 MSAL に移行する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-net-migration-android-broker)
- [Xamarin Android の構成に関するヒント + トラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-net-xamarin-android-considerations)
- [Xamarin の Android システム ブラウザ情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-net-system-browser-android-considerations)

Android デバイスでの MSAL のテストの詳細については、 [Android Wiki 用の MSAL](https://github.com/AzureAD/microsoft-authentication-library-for-android/wiki/Android-Emulator-with-MSAL) を参照してください。

### iOS の MAUI

iOS での MSAL.NET 統合を開始するには、次のリソースを参照してください。

- [Xamarin ADAL アプリを MSAL for iOS に移行する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-net-migration-ios-broker)
- [Xamarin iOS の構成に関するヒント + トラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-net-xamarin-ios-considerations)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/desktop-mobile/social-identities"} -->
## MSAL.NET を使用してソーシャル ID を持つユーザーをサインインさせる - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/social-identities
- Service: msal / msal-dotnet
- Article date: 2025-05-30
- Summary: MSAL.NET を使用して、Azure AD B2C を使用してソーシャル ID を持つユーザーをサインインさせることができます。 Azure AD B2C は、ポリシーの概念を中心に構築されています。 MSAL.NET では、ポリシーを指定すると、権限の提供に変換されます。

Important

2025 年 5 月 1 日より、Azure AD B2C は新規のお客様向けに購入できなくなります。 [詳細については、FAQ を参照してください](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale)。

MSAL.NET を使用して、[Azure AD B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/overview) を使用してソーシャル ID を持つユーザーをサインインさせることができます。 Azure AD B2C は、ポリシーの概念を中心に構築されています。 MSAL.NET では、ポリシーを指定すると、権限の提供に変換されます。

- パブリック クライアント アプリケーションをインスタンス化する場合は、機関でポリシーを指定する必要があります
- ポリシーを適用する場合は、`AcquireTokenInteractive` パラメーターを含む`authority`のオーバーライドを呼び出す必要があります

### Azure AD B2C テナントとポリシーの権限

使用する権限は `https://login.microsoftonline.com/tfp/{tenant}/{policyName}` です。ここで:

- `tenant`は、Azure AD B2C テナントの名前です。
- `policyName` 適用するポリシーの名前 (サインイン/サインアップの場合は "b2c\_1\_susi" など)。

B2C からの現在のガイダンスは、 `b2clogin.com` を機関として使用することです。 たとえば、「 `$"https://{your-tenant-name}.b2clogin.com/tfp/{your-tenant-ID}/{policyname}"` 」のように入力します。 詳細については、「 [リダイレクト URL を b2clogin.com に設定する」を](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/b2clogin)参照してください。

```csharp
// Azure AD B2C Coordinates
public static string Tenant = "fabrikamb2c.onmicrosoft.com";
public static string ClientID = "00001111-aaaa-2222-bbbb-3333cccc4444";
public static string PolicySignUpSignIn = "b2c_1_susi";
public static string PolicyEditProfile = "b2c_1_edit_profile";
public static string PolicyResetPassword = "b2c_1_reset";

public static string AuthorityBase = $"https://fabrikamb2c.b2clogin.com/tfp/{Tenant}/";
public static string Authority = $"{AuthorityBase}{PolicySignUpSignIn}";
public static string AuthorityEditProfile = $"{AuthorityBase}{PolicyEditProfile}";
public static string AuthorityPasswordReset = $"{AuthorityBase}{PolicyResetPassword}";
```

#### アプリケーションのインスタンス化

アプリケーションをビルドするときは、上記のように構築された機関を通常どおり指定する必要があります

```csharp
application = PublicClientApplicationBuilder.Create(ClientID)
               .WithB2CAuthority(Authority)
               .Build();
```

### ポリシーを適用するトークンの取得

Note

MSAL .NET 4.15.0 以降では、開発者は独自のキャッシュ フィルタリング ロジックを記述する必要がなくなりました。

Azure AD B2C では、各ポリシーまたはユーザー フローは個別の承認サーバーです。 独自のトークンを発行します。 そのため、 `b2c_1_editprofile` ユーザー フローを使用して取得されたトークンは、 `b2c_1_susi` ユーザー フローの背後で保護されたリソースでは動作しません。 そのため、保護された API を呼び出すとき、アプリケーション開発者は、対象となるユーザー フローに基づいて、キャッシュから使用するトークンを MSAL に通知する必要があります。

パブリック クライアント アプリケーションで Azure AD B2C で保護された API のトークンを取得するには、次を使用する必要があります。

- AcquireTokenSilent を呼び出す前のユーザー フローでの GetAccountsAsync() のオーバーライド。
- AcquireTokenInteractive を B2C 権限でオーバーライドします。

```csharp
IEnumerable<IAccount> accounts = await application.GetAccountsAsync(B2CConstants.PolicySignUpSignIn);
AuthenticationResult ar = await application.AcquireTokenInteractive(B2CConstants.Scopes)
                                           .WithAccount(accounts.FirstOrDefault())
                                           .ExecuteAsync();

```

バージョン 4.15.0 &gt; では、開発者は独自のキャッシュ フィルタリング ロジックを記述する必要がありました。 これは、 &gt;= 4.15.0 ではなくなりました。開発者はポリシーまたはユーザー フローのみを指定する必要があり、MSAL はその特定のユーザー フローに対応するアカウントを返します。

| MSAL.NET では、次の記述のみを行います。 | MSAL < 4.15.0 では、次のように記述する必要がありました。 |
| --- | --- |
| ```csharp<br>var accounts = await app.GetAccountsAsync(App.PolicySignUpSignIn);<br>``` | ```csharp<br>private IAccount GetAccountByPolicy(IEnumerable<IAccount> accounts, string policy)<br>{<br> foreach (var account in accounts)<br> {<br>  string userIdentifier = account.HomeAccountId.ObjectId.Split('.')[0];<br>  if (userIdentifier.EndsWith(policy.ToLower()))<br>   return account;<br> }<br> return null;<br>}<br>``` |

ポリシーの適用 (たとえば、エンド ユーザーが自分のプロファイルを編集したり、パスワードをリセットしたりするなど) は、現在、AcquireTokenInteractive を呼び出すことによって行われます。

>
> これら 2 つのポリシーの場合、返されるトークン/認証結果は使用しません。

### EditProfile ポリシーと ResetPassword ポリシーの特殊なケース

エンド ユーザーがソーシャル ID でサインインし、そのプロファイルを編集するエクスペリエンスを提供する場合は、B2C EditProfile ポリシーを適用します。 これを行う方法は、そのポリシーの特定の権限を持つ `AcquireTokenInteractive` を呼び出し、アカウント選択ダイアログが表示されないように `Prompt.NoPrompt` に設定されたプロンプトを呼び出すことです (ユーザーが既にサインインしている場合)

```csharp
private async void EditProfileButton_Click(object sender, RoutedEventArgs e)
{
 IEnumerable<IAccount> accounts = await app.GetAccountsAsync();
 try
 {
  var authResult = await app.AcquireToken(scopes:App.ApiScopes)
                               .WithAccount(GetUserByPolicy(accounts, App.PolicyEditProfile)),
                               .WithPrompt(Prompt.NoPrompt),
                               .WithB2CAuthority(App.AuthorityEditProfile)
                               .ExecuteAsync();
  DisplayBasicTokenInfo(authResult);
 }
 catch
 {
  . . .
}
```

プレビュー段階の [セルフサービス パスワード リセット](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/add-password-reset-policy?pivots=b2c-user-flow#self-service-password-reset-recommended)は、新しいパスワード リセット エクスペリエンスがサインインまたはサインアップ/サインイン (推奨) ユーザー フローの一部になったことを意味します。 これは、このプレビュー機能を有効にしたら、次のコードセクションを削除できることを意味します。

```csharp
 if (ex.Message.Contains("AADB2C90118"))
{
       authResult = await app.AcquireTokenInteractive(App.ApiScopes)
              .WithParentActivityOrWindow(new WindowInteropHelper(this).Handle)
              .WithPrompt(Prompt.SelectAccount)
              .WithB2CAuthority(App.AuthorityResetPassword)
              .ExecuteAsync();
}
```

または、 `AADB2C90118` エラーを処理するために実行していた特別なロジック。

### B2C でのリソース所有者パスワード認証情報 (ROPC)

ROPC フローの詳細については、 [ユーザー名とパスワード フローのドキュメント](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/username-password-authentication)を参照してください。

#### このフローは推奨されません

ユーザーにパスワードを要求するアプリケーションがセキュリティで保護されていないため、このフローは **推奨されません** 。 この問題の詳細については、[Microsoftがパスワードを過去のものにするために取り組んでいる理由を](https://news.microsoft.com/features/whats-solution-growing-problem-passwords-says-microsoft/)参照してください。

ユーザー名/パスワードを使用すると、次のような多くのことがあきらめられます。

- モダン ID の中核原則: パスワードはフィッシングで盗まれ、リプレイ攻撃に悪用される。 インターセプトできる共有シークレットのこの概念があるためです。 これはパスワードレスと互換性がありません。
- MFA を実行する必要があるユーザーはサインインできません (操作がないため)
- ユーザーはシングル サインオンを実行できません

#### Azure AD B2C で ROPC フローを構成する

Azure AD B2C テナントで、新しいユーザー フローを作成し、[**ROPC を使用してサインイン**] を選択します。 これにより、テナントの ROPC ポリシーが有効になります。 詳細については、「 [リソース所有者のパスワード資格情報フローの構成](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/configure-ropc) 」を参照してください。

`IPublicClientApplication` には、 `AcquireTokenByUsernamePassword`と呼ばれるメソッドが含まれています。

```csharp
AcquireTokenByUsernamePassword(
            IEnumerable<string> scopes,
            string username,
            SecureString password)
```

このメソッドは、次のパラメーターを取ります:

- アクセス トークンを要求する `scopes`
- ユーザー名
- ユーザーの SecureString パスワード

ROPC ポリシーを含む機関を必ず使用してください。

#### ROPC フローの制限事項

このフローは **、ローカル アカウント (** 電子メールまたはユーザー名を使用して B2C に登録する) でのみ機能します。 このフローは、B2C (Facebook、Google など) でサポートされている IdP のいずれかにフェデレーションする場合は機能しません。

### Google 認証と埋め込み Web ビュー

Google を ID プロバイダーとして使用している B2C 開発者の場合は、 [Google では埋め込み Web ビューからの認証](https://developers.googleblog.com/2016/08/modernizing-oauth-interactions-in-native-apps.html)が許可されないため、システム ブラウザーを使用することをお勧めします。 現在、 `login.microsoftonline.com` は Google の信頼できる機関です。 この権限を使用すると、埋め込み Web ビューで動作します。 ただし、 `b2clogin.com` の使用は Google の信頼できる機関ではないため、ユーザーは認証できません。

### MSAL.NET での B2C を使用したキャッシュ

#### Azure AD B2C に関する既知の問題

MSAL.Net は [トークン キャッシュ](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.tokencache)をサポートします。 トークン キャッシュ キーは、ID プロバイダーによって返される要求に基づいています。 現在 MSAL.Net トークン キャッシュ キーを構築するには、次の 2 つの要求が必要です。

1. `tid`これはMicrosoft Entraテナント ID です
2. `preferred_username`

これらの要求は、Azure AD B2C シナリオの多くで欠落しています。

ユーザーに与える影響は、ユーザー名フィールドを表示しようとしたときに、値として "トークン応答から欠落しています" が返されるということです。 その場合は、ソーシャル アカウントと外部 ID プロバイダー (IdP) に制限があるため、B2C が preferred\_username の IdToken の値を返さないためです。 Microsoft Entra IDは、ユーザーが誰であるかを知っているため、preferred\_usernameの値を返しますが、B2C の場合は、ユーザーがローカル アカウント、Facebook、Google、GitHubなどでサインインできるためです。B2C がpreferred\_usernameに使用する一貫性のある値はありません。 MSAL で ADAL とのキャッシュ互換性をロールアウトできるようにするため、B2C アカウントを扱う際に IdToken から preferred\_username が返されない場合は、「トークンの応答にありません」を使用することにしました。 MSAL は、ライブラリ間のキャッシュ互換性を維持するために、preferred\_usernameの値を返す必要があります。

#### 対処方法

##### `tid` の不足の緩和

推奨される回避策は、 [ポリシーによるキャッシュを使用することです](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-b2c-considerations#known-issue-with-azure-ad-b2c)。

また、`tid`を使用している場合は、アプリケーションに追加の要求を返す機能が提供されるため、要求を使用することもできます。 [要求変換](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/claims-transformation-technical-profile)の詳細については、こちらを参照してください。

##### "トークン応答から欠落しています" の軽減策

1 つのオプションは、"name" 要求を優先ユーザー名として使用することです。 このプロセスは、通常、この [B2C ドキュメント](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/active-directory-b2c-reference-policies#frequently-asked-questions)で説明されています。

>
> [返されるクレーム] 列で、プロファイル編集が正常に完了した後にアプリケーションに送り返される認可トークンに含めるクレームを選択します。 たとえば、[表示名]、[郵便番号] の順に選択します。

### UI のカスタマイズ

[Azure AD B2C を使用してユーザー インターフェイスをカスタマイズ](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/customize-ui-overview)します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/desktop-mobile/username-password-authentication"} -->
## MSAL.NET を使用したユーザー名とパスワード (ROPC) 認証 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/username-password-authentication
- Service: msal / msal-dotnet
- Article date: 2025-05-20
- Summary: デスクトップ アプリケーションでは、ユーザー名とパスワード のフローを使用して、トークンをサイレント モードで取得できます。 アプリケーションを使用するときに UI は必要ありません。

デスクトップ アプリケーションでは、ユーザー名とパスワード のフロー (リソース所有者のパスワード資格情報または ROPC とも呼ばれます) を使用して、トークンをサイレントモードで取得できます。 アプリケーションを使用するときに UI は必要ありません。

Warning

ROPC フローは、セキュリティ リスクのために非推奨になりました。より安全なフローを使用してください。 移行 [ガイダンスについては、このガイド](https://aka.ms/msal-ropc-migration) に従ってください。 ROPC フローがもたらすリスクと課題の詳細については、[「深刻化するパスワード問題の解決策とは？ それはあなた自身だと Microsoft は言う」](https://news.microsoft.com/features/whats-solution-growing-problem-passwords-says-microsoft/)を参照してください。 Windows でトークンをサイレントモードで取得するための推奨フローは、[Windows 認証 ブローカー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam)を使用することです。 または、開発者は、Web ブラウザーに アクセスせずに[デバイスでデバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/device-code-flow) を使用することもできます。

ROPC フローは、開発者が資格情報の取得に独自の UI を提供する場合に役立ちますが、重要なトレードオフがいくつかあります。 このフローを使用することで、開発者は多くのことをあきらめている。

- パスワードレス パターンなど、最新の ID の中核的な教義 - パスワードがフィッシングされた場合は、それを再生できます。
- 多要素認証 (MFA) を実行する必要があるユーザーは、対話アフォーダンスがないため、サインインできません。
- シングル Sign-On (SSO) のサポート。

### 制約

[統合Windows認証の制約](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/integrated-windows-authentication#iwa-constraints)に加えて、以下も適用されます。

- MSAL 2.1.0 以降で使用できます。
- 条件付きアクセスと多要素認証と互換性がありません。 その結果、テナント管理者が多要素認証を必要とするMicrosoft Entra テナントでアプリを実行する場合、フローを使用することはできません。
- 個人のMicrosoft アカウント**ではなく**、職場および学校アカウントでのみ使用できます。
- .NET Framework および .NET/.NET Core で使用できます。

#### 権限への影響

| Tenant | 説明 | ROPC をサポート |
| --- | --- | --- |
| `common` | 職場、学校、個人アカウント。 | ❌ いいえ |
| `organizations` | 職場と学校のアカウント。 | ✅ はい |
| `consumers` | 個人用 Microsoft アカウント。 | ❌ いいえ |
| 特定のテナント (GUID または `contoso.onmicrosoft.com` などの完全修飾名) | 特定のテナントの職場および学校アカウント。 | ✅ はい |

Note

AZURE AD B2C での ROPC フローの使用の詳細については、「[MSAL.NET を使用してソーシャル ID を持つユーザーをサインインさせる」を](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-aad-b2c-considerations)参照してください。

### 使用方法

#### アプリケーションの登録

アプリの[登録](https://go.microsoft.com/fwlink/?linkid=2083908)時に、アプリケーションの **[認証**] セクションで、[**パブリック クライアント フローを許可**する] という質問の答えとして [**はい**] を選択します (これには、**アプリがプレーンテキスト パスワードを収集する (リソース所有者パスワード資格情報フロー)** が含まれます)。

[Image: ROPC フロー フラグを示す、Microsoft EdgeのAzure portalのスクリーンショット]

Note

アプリケーションが個人のMicrosoft アカウントでの認証をサポートしている場合、アプリケーションが職場および学校アカウントでの認証もサポート**している場合でも**、ROPC フローは使用できません。

#### サンプル コード

ROPC フローは、パブリック クライアント アプリケーションでのみ使用できます。 これを使用するために、開発者は、[`PublicClientApplication`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplication) メソッドを含む [`AcquireTokenByUsernamePassword`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokenbyusernamepasswordparameterbuilder) クラスを利用できます。

次の例では、簡略化されたユース ケースを示します。

Note

機関 URL の `/contoso.com` をテナント ID または `/organizations`に置き換えます。

次の例は、最新のユース ケースを示しています。

```csharp
static async Task GetATokenForGraph()
{
    string authority = "https://login.microsoftonline.com/contoso.com";
    string[] scopes = new string[]
    {
        "user.read"
    };

    IPublicClientApplication app;
    app = PublicClientApplicationBuilder.Create(clientId).WithAuthority(authority).Build();

    var accounts = await app.GetAccountsAsync();
    AuthenticationResult result = null;

    if (accounts.Any())
    {
        result = await app.AcquireTokenSilent(scopes, accounts.FirstOrDefault()).ExecuteAsync();
    }
    else
    {
        try
        {
            result = await app.AcquireTokenByUsernamePassword(scopes, "joe@contoso.com", "joepassword").ExecuteAsync();
        }
        catch (MsalException)
        {
            // Handle various potential exceptions.
        }
    }
    Console.WriteLine(result.Account.Username);
}
```

### プロトコルに関するドキュメント

基になるプロトコルの詳細については、[v2.0 Azure Active Directoryと OAuth 2.0 リソース所有者のパスワード資格情報](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-oauth-ropc)を参照してください。

### エンドツーエンドのサンプル

| Sample | Platform | 説明 |
| --- | --- | --- |
| [active-directory-dotnetcore-console-up-v2](https://github.com/Azure-Samples/active-directory-dotnetcore-console-up-v2) | コンソール (.NET Core) | .NET Core コンソール アプリケーションを使用すると、ユーザーはユーザー名とパスワードを使用して Azure AD v2.0 エンドポイントでサインインし、Microsoft Graphのトークンを取得できます。 ! [コンソール アプリトポロジ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/media/console-app-topology.png) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam"} -->
## Web アカウント マネージャー (WAM) での MSAL.NET の使用 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam
- Service: msal / msal-dotnet
- Article date: 2023-06-29
- Summary: MSAL は、OS に付属する Windows コンポーネントである Web アカウント マネージャー (WAM) を呼び出すことが可能です。 このコンポーネントは認証ブローカーとして機能し、アプリのユーザーは、Windows セッションにサインインしたアカウントなど、Windows知られているアカウントとの統合の恩恵を受けることができます。

MSAL は、OS に付属する Windows コンポーネントである Web アカウント マネージャー (WAM) を呼び出すことが可能です。 このコンポーネントは認証ブローカーとして機能し、アプリのユーザーは、Windows セッションにサインインしたアカウントなど、Windows知られているアカウントとの統合の恩恵を受けることができます。

Note

WAM は、Windows 10 (バージョン 1703 - Creators Update) 以降およびWindows Server 2019以降の MSAL.NET ベースのアプリケーションで使用できます。 WAM を使用できない場合、MSAL は自動的にブラウザーにフォールバックします。

### ブローカーとは

認証ブローカーは、接続されているアカウントの認証ハンドシェイクとトークンメンテナンスを管理するユーザーのマシン上で実行されるアプリケーションです。 Windows オペレーティング システムは、認証ブローカーとして Web アカウント マネージャー (WAM) を使用します。 開発者と顧客にとって、次のような多くの利点があります。

- **セキュリティの強化。** 多くのセキュリティ強化は、アプリケーション ロジックを更新することなく、ブローカーと共に提供されます。
- **機能のサポート。** ブローカー開発者の助けを借りて、余分なスキャフォールディング コードを記述することなく、Windows Hello、条件付きアクセス ポリシー、FIDO キーなどの豊富な OS およびサービス機能にアクセスできます。
- **システム統合。** 組み込みのアカウント ピッカーでブローカー プラグ アンド プレイを使用するアプリケーションにより、ユーザーは同じ資格情報を何度も再入力する代わりに、既存のアカウントをすばやく選択できます。
- **トークン保護。** WAM では、更新トークンがデバイスバインドされ、アプリがデバイスバインドアクセストークンを取得 [できるようにします](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/proof-of-possession-tokens) 。 [「トークン保護」](https://learn.microsoft.com/ja-jp/azure/active-directory/conditional-access/concept-token-protection)を参照してください。

### WAM の有効化

Important

ブローカーのサポートを得るには、MSAL.NET 4.52.0 以降を使用します。

Important

WAM では、Microsoft Entra IDのみがサポートされ、サード パーティの ID プロバイダー (IDP) では機能しません。

WAM のサポートは、次の 2 つのパッケージに分割されます。

- [Microsoft。Identity.Client](https://www.nuget.org/packages/Microsoft.Identity.Client/) (つまり、MSAL) - トークン取得用のコア ライブラリ。
- [Microsoft。Identity.Client.Broker](https://www.nuget.org/packages/Microsoft.Identity.Client.Broker/) - ブローカーでの認証のサポートを追加します。

Note

移行のために、*両方*の WAM と [埋め込みブラウザー](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-web-browsers#embedded-vs-system-web-ui) を使用する必要がある .NET 6、.NET Core、または .NET Standard のアプリケーションでは、[Microsoft.Identity.Client.Desktop](https://www.nuget.org/packages/Microsoft.Identity.Client.Desktop/) パッケージも使用する必要があります。 追加すると、開発者はパブリック クライアント アプリケーションを設定するときに [`WithWindowsDesktopFeatures`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.desktop.desktopextensions.withwindowsdesktopfeatures) を使用できます。

アプリケーションが`net-windows` (Windows のバージョン依存のターゲット フレームワーク モニカー) をターゲットとする場合、WAM は MSAL.NET パッケージに含まれます。

関連するパッケージを参照した後、ブローカー構成オプションとブローカーがバインドされる[`WithBroker(BrokerOptions)`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.desktop.wamextension.withbroker)使用してを呼び出します。

Note

ほとんどのアプリでは、この統合を使用するには [`Microsoft.Identity.Client.Broker`](https://www.nuget.org/packages/Microsoft.Identity.Client.Broker/) パッケージを参照する必要があります。 適切な`using Microsoft.Identity.Client.Broker;`オーバーロードを使用できるように、アプリケーション コードに [`WithBroker`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.desktop.wamextension.withbroker) ステートメントを追加してください。 .NET MAUIアプリケーションでは、機能が MSAL に埋め込まれているため、依存関係を追加する必要はありません。

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

ブローカーを使用する場合、使用する[機関](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-client-application-configuration#authority)がMicrosoft Entra IDと個人のMicrosoft アカウントを対象としている場合、ユーザーはまず、組み込みのシステム アカウント ピッカーを使用してアカウントを選択するように求められます。

[Image: WAM コンポーネントのデモ]

[`WithTenantId`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.abstractapplicationbuilder-1.withtenantid)を使用してテナントごとに構成が設定されている場合、または権限が個人のMicrosoft アカウントを含[まない](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.aadauthorityaudience)*対象ユーザー*に設定されている場合、ネイティブ Windows アカウント ピッカーは表示されず、代わりにユーザーに一般的なMicrosoft認証プロンプトが表示されます。

[Image: テナントごとに構成され、OS ベースのアカウント ピッカーが表示されない WAM コンポーネントのデモ]

アカウントが追加または選択されると、以前にアプリケーションを使用したことがない場合、またはアプリケーションに追加のアクセス許可が必要な場合、ユーザーは追加の同意を求められます。

### 親ウィンドウのハンドル

ブローカーを使用するには、[`WithParentActivityOrWindow`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplicationbuilder.withparentactivityorwindow) API を使用して、WAM モーダル ダイアログの親ウィンドウとなるウィンドウ ハンドルを指定することが必須になりました。 ウィンドウ ハンドルは、MSAL 自体が親ウィンドウを推論することは不可能であるため、開発者が提供する必要があります。これは、以前は、認証ウィンドウがアプリケーション ウィンドウの背後に隠れていたという不適切なユーザー エクスペリエンスを引き起こしたためです。

Windows フォーム、Windows Presentation Foundation (WPF)、WinUI3 を使用する UI アプリについては、「[ウィンドウ ハンドルの取得 (HWND)](https://learn.microsoft.com/ja-jp/windows/apps/develop/ui-input/retrieve-hwnd)」を参照してください。

コンソール アプリケーションの場合は、次のスニペットのようなコードを使用できます。

```csharp
enum GetAncestorFlags
{   
    GetParent = 1,
    GetRoot = 2,
    /// <summary>
    /// Retrieves the owned root window by walking the chain of parent and owner windows returned by GetParent.
    /// </summary>
    GetRootOwner = 3
}

/// <summary>
/// Retrieves the handle to the ancestor of the specified window.
/// </summary>
/// <param name="hwnd">A handle to the window whose ancestor is to be retrieved.
/// If this parameter is the desktop window, the function returns NULL. </param>
/// <param name="flags">The ancestor to be retrieved.</param>
/// <returns>The return value is the handle to the ancestor window.</returns>
[DllImport("user32.dll", ExactSpelling = true)]
static extern IntPtr GetAncestor(IntPtr hwnd, GetAncestorFlags flags);

[DllImport("kernel32.dll")]
static extern IntPtr GetConsoleWindow();

// This is your window handle!
public IntPtr GetConsoleOrTerminalWindow()
{
    IntPtr consoleHandle = GetConsoleWindow();
    IntPtr handle = GetAncestor(consoleHandle, GetAncestorFlags.GetRootOwner );
    
    return handle;
}
```

### 所持証明アクセストークン

WAM ブローカーでは、パブリック クライアント フローの PoP トークンを取得できます。 詳細については [、所有証明トークンを](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/proof-of-possession-tokens) 参照してください。

### リダイレクト URI

WAM リダイレクト URI は MSAL で構成する必要はありませんが、アプリの登録で構成する必要があります。 次のパターンに従う必要があります。

```text
ms-appx-web://microsoft.aad.brokerplugin/{client_id}
```

Note

Azure portalでリダイレクト URL を構成する場合は、[**Mobile and desktop applications]\(モバイルアプリケーションとデスクトップ アプリケーション**\) セクションで設定していることを確認します。

### ユーザー名とパスワードのフロー

このフロー (リソース所有者パスワード資格情報 (ROPC) とも呼ばれます) は、テスト シナリオや、リソースへのサービス プリンシパル アクセスによってアクセスが多くなりすぎて、ユーザー フローでのみスコープを絞り込むことができるシナリオを除き、推奨されません。 WAM を使用する場合、 [`AcquireTokenByUsernamePassword`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.publicclientapplication.acquiretokenbyusernamepassword) は WAM がプロトコルを管理し、トークンをフェッチできるようにします。

Warning

Microsoftは、ユーザー名とパスワードのフローを使用することはお勧めしません。これはセキュリティで保護されていないパターンであり、アプリケーションがユーザーに直接パスワードを求めるのでです。 さらに、ROPC フローでは、**個人のMicrosoft アカウント**と**、多要素認証が有効なMicrosoft Entra アカウント**はサポートされません。 完全な概要については、[Microsoft ID プラットフォームと OAuth 2.0 リソース所有者のパスワード資格情報](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-oauth-ropc)を確認してください。

### WAM の制限事項

- Azure B2C および Active Directory フェデレーション サービス (AD FS) (ADFS) 機関はサポートされていません。 MSAL は、ユーザー認証にブラウザーを使用するようにフォールバックします。
- Mac、Linux、および 10 より前のバージョンのWindowsまたはWindows Server 2019では、MSAL はブラウザーにフォールバックします。

### パッケージの可用性

ブローカーを使用するには、開発者は、[WithBroker(PublicClientApplicationBuilder, BrokerOptions)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.broker.brokerextension.withbroker#microsoft-identity-client-broker-brokerextension-withbroker%28microsoft-identity-client-publicclientapplicationbuilder-microsoft-identity-client-brokeroptions%29) パッケージでホストされている[Microsoft.Identity.Client.Broker](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.broker)を呼び出す必要があります。 MSAL.NET でサポートされている.NETプラットフォームバリアントのほとんどは、そのパッケージのみを必要としますが、いくつかの例外があります。 詳細なマッピングについては、次の表を参照してください。

| フレームワーク | [Microsoft.Identity.Client](https://www.nuget.org/packages/Microsoft.Identity.Client/) | [Microsoft。Identity.Client.Broker](https://www.nuget.org/packages/Microsoft.Identity.Client.Broker/) | [Microsoft。Identity.Client.Desktop](https://www.nuget.org/packages/Microsoft.Identity.Client.Desktop/) |
| --- | --- | --- | --- |
| .NET 6 以降 | ⛔ いいえ | ✅ はい | ⛔ いいえ |
| .NET 6 以上のWindows† | ⛔ いいえ | ✅ はい | ✅ はい (推奨されません) |
| .NET MAUI | ✅ はい | ⛔ いいえ | ⛔ いいえ |
| .NET 4.6.2 以降 | ⛔ いいえ | ✅ はい | ✅ はい (推奨されません) |
| .NET Standard | ⛔ いいえ | ✅ はい | ✅ はい (推奨されません) |
| .NET コア | ⛔ いいえ | ✅ はい | ✅ はい (推奨されません) |

**†**`Microsoft.Identity.Client` バージョン 4.61.0 以降には、バイナリ `net6.0-windows7.0` 含まれていません。 `net6.0-windows`を対象とする既存のデスクトップ アプリケーションは、Windows Broker で対話型認証を使用する場合は`Microsoft.Identity.Client.Broker`を参照し、[WithBroker(PublicClientApplicationBuilder, BrokerOptions)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.broker.brokerextension.withbroker#microsoft-identity-client-broker-brokerextension-withbroker%28microsoft-identity-client-publicclientapplicationbuilder-microsoft-identity-client-brokeroptions%29)を呼び出すか、`Microsoft.Identity.Client.Desktop`してを呼び出すときに[WithWindowsEmbeddedBrowserSupport(PublicClientApplicationBuilder)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.desktop.desktopextensions.withwindowsembeddedbrowsersupport#microsoft-identity-client-desktop-desktopextensions-withwindowsembeddedbrowsersupport%28microsoft-identity-client-publicclientapplicationbuilder%29)を参照する必要があります。

### 統合のベスト プラクティス

Important

WAM を使用する場合、アプリケーションはアクティブで対話型のWindowsユーザー セッションのコンテキストで実行され、UI を表示できる必要があります。 Windows サービスとして実行中、タスク スケジューラを使用して (特にログインユーザーとして実行している場合を除く)、またはを使用して別`runas`アカウントを偽装しているときに、WAM を使用してトークンを取得しようとすると、設計上エラーが発生します。

お客様が WAM の優れた経験を持っていることを確認するために、次の原則に従うことが強くお勧めします。

1. **認証の前にユーザー コンテキストを指定**します。 認証の理由と共に、認証が必要であることをユーザーに通知する UI またはウィンドウを描画します。 アプリケーションがバックグラウンド サービスである場合の利点について説明します。
2. **ユーザー アクションに基づいて認証を呼び出します**。 ユーザーは、リンクまたはボタンをクリックするか、別のジェスチャを実行して、特定のアプリケーションで認証プロセスをトリガーしたことを認識する必要があります。 ユーザーは、コンテキストやアクションがアタッチされていないオペレーティング システム内にポップアップ表示されるウィンドウに資格情報を入力しないでください。
3. **最初に [トークンを自動的に取得](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokensilentparameterbuilder) し、失敗した場合は [対話型プロンプト](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokeninteractiveparameterbuilder) にフォールバック**します。 お客様は、資格情報を再入力するか、ポリシー要件を満たす明示的な必要がある場合にのみ、対話型認証を求めるメッセージを表示する必要があります。

### Troubleshooting

#### "MsalClientException (ErrCode 5376): この認証フローでは、少なくとも 1 つのスコープを要求する必要があります。" というエラー メッセージ

このメッセージは、他の OIDC スコープ (`user.read`、`profile`、または`email`) と共に、少なくとも 1 つのアプリケーション スコープ (`offline_access` など) を要求する必要があることを示します。

```csharp
var authResult = await pca.AcquireTokenInteractive(new[] { "user.read" })
                 .ExecuteAsync();
```

#### アカウント選択画面が表示されない

Windowsの更新がアカウント ピッカー コンポーネントに誤って影響を与えることがあり、Windowsのアカウントの一覧と新しいアカウントを追加するオプションが表示されます。 症状としては、ごく一部のユーザーではピッカーが表示されません。

考えられる回避策は、コンポーネントを再登録することです。 管理者のアクセス許可でターミナルから次のスクリプトを実行します。

```powershell
if (-not (Get-AppxPackage Microsoft.AccountsControl))
{ 
    Add-AppxPackage -Register "$env:windir\SystemApps\Microsoft.AccountsControl_cw5n1h2txyewy\AppxManifest.xml" -DisableDevelopmentMode -ForceApplicationShutdown 
}

Get-AppxPackage Microsoft.AccountsControl
```

#### 接続に関する問題

アプリケーション ユーザーに、 `Please check your connection and try again`のようなエラー メッセージが表示されます。 この問題が定期的に発生する場合は、WAM も使用する [Office のトラブルシューティング ガイドを](https://learn.microsoft.com/ja-jp/microsoft-365/troubleshoot/authentication/connection-issue-when-sign-in-office-2016)参照してください。

#### WAM エラー コード

WAM エラーの詳細については、 [Web アカウント マネージャー (WAM) に関連付けられている](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/wam-errors) エラーを参照してください。

WAM は比較的新しいコンポーネントであるため、エラーが発生した場合は、 [`AdditionalExceptionData`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.msalexception.additionalexceptiondata)からデータをログに記録することをお勧めします。 これは、構成または WAM コンポーネントに関する特定の問題を特定するのに役立ちます。 WAM の問題が発生した場合は、 [バグをログに記録](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/issues) してください。これは、問題にタイムリーに対処するのに役立ちます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/overview"} -->
## トークンの取得 - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/overview
- Service: msal / msal-dotnet
- Article date: 2024-05-20
- Summary: MSAL.NET を使用して、パブリック および機密クライアント アプリケーションでセキュリティ トークンを取得する方法について説明します。

### アプリケーションのタイプ

[シナリオ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/scenarios)で説明したように、MSAL.NET を使用してトークンを取得する方法は多数あります。 操作が必要な場合もあれば、ユーザーに対して完全に透過的なものもあります。 トークンの取得に使用される方法は、開発者がパブリック クライアント (デスクトップまたはモバイル) または機密クライアント アプリケーション (Web アプリ、Web API、Windows サービスなどのデーモン) を構築しているかどうかによって異なります。 一般に、パブリック クライアントではユーザーの操作が必要ですが、機密クライアントは証明書やシークレットなどの事前プロビジョニングされた資格情報に依存します。

### トークンのキャッシュ

パブリック クライアント アプリケーションと機密クライアント アプリケーションの両方で、MSAL.NET は認証トークンと更新トークンを保持するトークン キャッシュの追加をサポートし、必要に応じて事前に更新します。 詳細については、[MSAL.NET でのトークン キャッシュのシリアル化に関するページ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization)を参照してください。

.NETデスクトップ アプリケーション (.NET、.NET Framework、.NET Core) の場合、アプリケーションはトークン キャッシュのシリアル化とストレージを直接処理する必要があります。ただし、プロセスを簡略化するためにヘルパー クラスを使用できます。

### トークンの取得方法

#### パブリック クライアント アプリケーション

- 多くの場合、対話 [形式でトークンを取得](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/acquiring-tokens-interactively)し、ユーザーがサインインします。
- また、ドメインに参加しているWindows コンピューターで実行されているデスクトップ アプリケーションや、[統合Windows認証 (IWA/Kerberos) を使用](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/integrated-windows-authentication)してトークンをサイレントで取得Microsoft Entra IDすることもできます。
    - IWA アプローチは **推奨されないこと**に注意してください。 [Web アカウント マネージャー (WAM)](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/wam) を使用して、より安全な方法を使用できます。
- .NET Framework デスクトップ アプリケーションの場合、限られたシナリオでは[、ユーザー名とパスワードを使用してトークンを取得](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/username-password-authentication)できます。 セキュリティ上の考慮事項のため、この方法はお勧めしません。
- Web ブラウザーを持たないデバイスで実行されているアプリケーションでは、 [デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/device-code-flow)を使用してトークンを取得できます。これは、アプリケーション ユーザーに URL とコードを提供します。 その後、ユーザーは別のデバイス上の Web ブラウザーに移動し、コードを入力してサインインします。 認証デバイスは、正常なサインインとアクセス トークンの確認を受け取るまで、Microsoft Entra ID サービスをポーリングします。

次の表は、パブリック クライアント アプリケーションでトークンを取得するために使用できる方法をまとめたものです。

| オペレーティング システム | Platform | アプリの種類 | [インタラクティブ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/acquiring-tokens-interactively) | [Iwa](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/integrated-windows-authentication) | [デバイス コード](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/device-code-flow) |
| --- | --- | --- | --- | --- | --- |
| Windows (デスクトップ) | .NET | デスクトップ (WPF、Windows フォーム、コンソール) | ✅ | ✅ | ✅ |
| Android | .NET MAUI | Mobile | ✅ | ❌ | ❌ |
| iOS | .NET MAUI | Mobile | ✅ | ❌ | ✅ |
| macOS、Linux、Windows | .NET コア | Console | N/A Web [ブラウザーの使用](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/using-web-browsers)を参照してください | ✅ | ✅ |

#### 機密クライアント アプリケーション

- ユーザーではなく **、アプリケーション自体の**トークンを取得します。 トークンの取得は、 [クライアント資格情報](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/client-credential-flows)を使用して行われます。 このフローは、特定の ID がアタッチされていないデータまたはユーザー情報を処理するツールまたはツールを同期する場合に便利です。
- ユーザーに代わって API を呼び出す Web API の場合、開発者は [On Behalf Of フローを](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/on-behalf-of-flow)使用できます。 アプリケーション自体は、クライアント資格情報を使用して、ユーザー アサーション ( [SAML](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol) や [JWT](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-tokens#json-web-tokens-and-claims) など) に基づいてトークンを取得します。 このフローは、サービス間呼び出しで特定のユーザーのリソースにアクセスする必要があるアプリケーションに使用できます。
- **Web アプリの場合**、トークンの取得は [、承認](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/authorization-codes) 要求 URL を使用してユーザーにサインインした後、承認コードを使用して行われます。 これは通常、アプリケーションによって使用されるメカニズムです。これにより、ユーザーは [OpenID Connect](https://openid.net/developers/how-connect-works/) を使用してサインインし、この特定のユーザーの代わりに Web API にアクセスできます。

次の表は、機密クライアント アプリケーションでトークンを取得する方法をまとめたものです。

| オペレーティング システム | Platform | アプリの種類 | [クライアント資格情報](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/client-credential-flows) | [On-Behalf-Of](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/on-behalf-of-flow) | [認証コード](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/authorization-codes) |
| --- | --- | --- | --- | --- | --- |
| Windows | .NET Framework | Web アプリケーション | ✅ | ❌ | ✅ |
| Windows、macOS、Linux | ASP.NET Core | Web アプリケーション | ✅ | ❌ | ✅ |
| Windows | .NET Framework | Web API | ✅ | ✅ | ❌ |
| Windows、macOS、Linux | ASP.NET Core | Web API | ✅ | ✅ | ❌ |
| Windows | .NET Framework | デーモン (Windows サービス) | ✅ | ❌ | ❌ |
| Windows、macOS、Linux | .NET コア | Daemon | ✅ | ❌ | ❌ |

#### MSAL.NET でトークンを取得するパターン

MSAL.NET のすべての Acquire Token メソッドには、次のパターンがあります。

- アプリケーションから、使用するフローに対応する AcquireToken*XXX* メソッドを呼び出し、このフローの必須パラメーター (一般的なフロー) を渡します。
- これにより、コマンド ビルダーが返されます。これを使用して、省略可能なパラメーターを追加できます。*YYY* メソッドを使用する
- 次に、ExecuteAsync() を呼び出して認証結果を取得します。

パターンを次に示します。

```csharp
AuthenticationResult result = app.AcquireTokenXXX(mandatory-parameters)
 .WithYYYParameter(optional-parameter)
 .ExecuteAsync();
```

### `AuthenticationResult`MSAL.NET の宿泊施設

上記のすべてのケースで、トークンを取得するメソッドは `AuthenticationResult` を返します (または非同期メソッドの場合は `Task<AuthenticationResult>`。

MSAL.NET では、AuthenticationResult は次を公開します。

- `AccessToken` Web API がリソースにアクセスできるようにします。 これは文字列であり、通常は base64 でエンコードされた JWT ですが、クライアントはアクセス トークン内を検索しないでください。 この形式が変わらないことは保証されておらず、リソース用に暗号化できます。 クライアント上のアクセス トークンの内容に応じてコードを記述するユーザーは、エラーとクライアント ロジックの中断の最大の原因の 1 つです
- `IdToken` ユーザーの場合 (これは JWT です)
- `ExpiresOn` トークンの有効期限が切れる日時を指定します。
- `TenantId` には、ユーザーが存在するテナントが含まれています。 ゲスト ユーザー (Microsoft Entra B2B シナリオ) の場合、TenantId は一意のテナントではなくゲスト テナントであることに注意してください。 トークンがユーザーの名前で配信されると、 `AuthenticationResult` にはこのユーザーに関する情報も含まれます。 (アプリケーションの) ユーザーなしでトークンが要求される機密クライアント フローの場合、このユーザー情報は null です。
- トークンが発行された `Scopes` ( [リソースではなくスコープを](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-differences-adal-net)参照)
- ユーザーの一意の ID。

### `IAccount`

MSAL.NET では、(`IAccount` インターフェイスを介して) アカウントの概念を定義します。 この破壊的変更により、適切なセマンティクスが提供されます。つまり、同じユーザーが異なるMicrosoft Entraディレクトリに複数のアカウントを持つことができます。 また、MSAL.NET では、ホーム アカウント情報が提供されるため、ゲスト シナリオの場合は、より適切な情報が提供されます。 次の図は、 `IAccount` インターフェイスの構造を示しています。

[Image: 画像]

`AccountId` クラスは、特定のテナント内のアカウントを識別します。 次のプロパティがあります:

| 財産 | 説明 |
| --- | --- |
| `TenantId` | アカウントが存在するテナントの ID である GUID の文字列表現 |
| `ObjectId` | テナント内のアカウントを所有するユーザーの ID である GUID の文字列表現 |
| `Identifier` | アカウントの一意識別子 (これは、 `ObjectId` と `TenantId` をコンマで区切って連結したもので、base64 でエンコードされません) |

`IAccount` インターフェイスは 1 つのアカウントに関する情報を表します。 同じユーザーを異なるテナントに存在させることができます。つまり、1 人のユーザーが複数のアカウントを持つことができます。 そのメンバーは次のとおりです。

| 財産 | 説明 |
| --- | --- |
| `Username` | userPrincipalName (UPN) 形式の表示可能な値を含む文字列 (たとえば、 john.doe@contoso.com)。 これは null でもかまいませんが、HomeAccountId と HomeAccountId.Identifier は null になりません。 このプロパティは、MSAL.NET の以前のバージョンの `DisplayableId` の `IUser` プロパティを置き換えます。 |
| `Environment` | このアカウントの ID プロバイダーを含む文字列 (たとえば、 `login.microsoftonline.com`)。 このプロパティは、`IUser`の`IdentityProvider`プロパティを置き換えます。ただし、`IdentityProvider`には (クラウド環境に加えて) テナントに関する情報も含まれているのに対し、ここではホストのみです。 |
| `HomeAccountId` | ユーザーのホーム アカウントの AccountId。 これにより、Microsoft Entraテナント間でユーザーが一意に識別されます。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/using-web-browsers"} -->
## Web ブラウザーの使用 (MSAL.NET) - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/using-web-browsers
- Service: msal / msal-dotnet
- Article date: 2023-08-24
- Summary: .NET (MSAL.NET) のMicrosoft Authentication Libraryでブラウザーを使用する方法について説明します。

ブローカーを使用して認証することをお勧めします。ブラウザーと比較して、より多くの利点が提供されるためです。 Windowsコンピューターでは、ブローカーは [Web アカウント マネージャー (WAM)](https://aka.ms/msal-net-wam) であり、Android と iOS では [Microsoft Authenticator または Intune ポータル サイト](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-net-use-brokers-with-xamarin-apps)。 対話型認証では、ブローカーまたは Web ブラウザーを使用する必要があります。 MSAL.NET は、システム Web ブラウザーまたは埋め込み Web ビューをサポートします。

### MSAL.NET の Web ブラウザー

#### Web ブラウザーで対話が行われる

対話形式でトークンを取得する場合、ダイアログ ボックスの内容はライブラリではなくMicrosoft Entra IDによって提供されることを理解しておくことが重要です。 認証エンドポイントは、Web ブラウザーまたは Web コントロールでレンダリングされる対話を制御する HTML と JavaScript を返します。 Microsoft Entra IDが HTML の対話を処理できるようにするには、多くの利点があります。

- パスワードが入力された場合、アプリケーションや認証ライブラリには保存されません。
- これにより、他の ID プロバイダー (職場または学校アカウントでのサインイン、MSAL を使用した個人アカウント、Azure AD B2C を使用したソーシャル アカウント) へのリダイレクトが可能になります。
- これにより、Microsoft Entra ID で条件付きアクセスを制御できます。たとえば、認証時にユーザーに [多要素認証 (MFA)](https://learn.microsoft.com/ja-jp/azure/active-directory/authentication/concept-mfa-howitworks) を求めることで制御できます。具体的には、Windows Hello の PIN を入力したり、ユーザーの電話への着信に応答したり、電話上の認証アプリを使用したりします。 必要な多要素認証がまだ設定されていない場合、ユーザーは同じダイアログで Just-In-Time を設定できます。 ユーザーは携帯電話番号を入力し、認証アプリケーションをインストールし、QR タグをスキャンしてアカウントを追加するように案内されます。 このサーバー駆動型の操作は、優れたエクスペリエンスです。
- パスワードの有効期限が切れたときに、ユーザーはこの同じダイアログでパスワードを変更できます (古いパスワードと新しいパスワードの追加フィールドを指定します)。
- これにより、Microsoft Entra テナント管理者またはアプリケーション所有者によって制御されるテナントまたはアプリケーション (イメージ) のブランド化が可能になります。
- これにより、ユーザーは認証の直後にアプリケーションが自分の名前のリソースとスコープにアクセスすることに同意できます。

#### 埋め込み Web ビューとシステム ブラウザー

MSAL.NET はマルチフレームワーク ライブラリであり、UI コントロールでブラウザーをホストするためのフレームワーク固有のコードを持っています (たとえば、WinForms または WebView2 の.NET、.NET MAUI、ネイティブ モバイル コントロールなど)。 このコントロールは *、埋め込み* Web ビューと呼ばれます。 または、MSAL.NET はシステム Web ブラウザーを開くこともできます。

一般に、プラットフォームの既定値を使用することをお勧めします。これは通常、システム ブラウザーです。 システム ブラウザーは、以前にログインしたユーザーを記憶する方が優れています。 この動作を変更するには、 [WithUseEmbeddedWebView(Boolean)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokeninteractiveparameterbuilder.withuseembeddedwebview#microsoft-identity-client-acquiretokeninteractiveparameterbuilder-withuseembeddedwebview%28system-boolean%29)

#### ブラウザーの可用性

| フレームワーク | 内蔵型 | システム† | Default |
| --- | --- | --- | --- |
| .NET 6 + †† | ⛔ いいえ | ✅ はい | システム |
| .NET 6 以上のWindows | ⛔ いいえ††† | ✅ はい | システム |
| .NET MAUI | ✅ はい | ✅ はい | システム |
| .NET 5 + †† | ⛔ いいえ | ✅ はい | システム |
| .NET 4.6.2 以降 | ✅ はい | ✅ はい | 内蔵型 |
| .NET Standard | ⛔ いいえ††† | ✅ はい | システム |
| .NET コア | ⛔ いいえ††† | ✅ はい | システム |

**†** システム ブラウザーには `http://localhost` リダイレクト URI が必要です。

**††** 埋め込みブラウザーを使用するには、 `net6.0-windows` 以上をターゲットにします。

**†††**参照[Microsoft。Identity.Client.Desktop](https://www.nuget.org/packages/Microsoft.Identity.Client.Desktop) を呼び出し、[WithWindowsDesktopFeatures](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.desktop.desktopextensions.withwindowsdesktopfeatures)を呼び出して埋め込みブラウザーを使用します。

### システム Web ブラウザー

システム ブラウザーを使用すると、ブローカー (WAM、ポータル サイト、Authenticator など) を必要とせずに、単一 Sign-On (SSO) 状態を Web アプリケーションやその他のアプリケーションと共有できるという大きな利点があります。

ただし、デスクトップ アプリケーションの場合、システム ブラウザーを起動すると、ユーザーにブラウザーが表示され、他のタブが既に開かれている可能性があるため、サブパー ユーザー エクスペリエンスが発生します。 認証が行われると、ユーザーにこのウィンドウを閉じるように求めるページが表示されます。 ユーザーが注意を払わない場合は、プロセス全体 (認証とは無関係な他のタブを含む) を閉じる可能性があります。 デスクトップでシステム ブラウザーを利用するには、ローカル ポートを開いてリッスンする必要もあります。そのためには、アプリケーションの高度なアクセス許可が必要になる場合があります。 開発者、ユーザー、または管理者は、この要件に消極的である可能性があります。

#### 既定のシステム ブラウザーを使用する方法

.NETでは、MSAL は別のプロセスとしてシステム ブラウザーを起動します。 MSAL.NET はこのブラウザーを制御できませんが、ユーザーが認証を完了すると、パブリック クライアント インスタンスの作成時に指定されたリダイレクト URI の呼び出し MSAL.NET インターセプトできるように Web ページがリダイレクトされます。

MSAL.NET は、ユーザーが離れて移動したか、単にブラウザーを閉じるのか検出できません。 この手法を使用するアプリでは、 [CancellationToken](https://learn.microsoft.com/ja-jp/dotnet/api/system.threading.cancellationtoken)を使用してタイムアウトを定義することをお勧めします。 パスワードの変更または多要素認証の実行をユーザーに求められる場合を考慮するために、少なくとも数分のタイムアウトをお勧めします。

ユーザーの認証が完了したときに Microsoft Entra ID から返されるコードを受け取るため、MSAL.NET は `http://localhost:port` で待ち受ける必要があります。 詳細については、 [承認コード フロー](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-oauth2-auth-code-flow) を参照してください。

システム ブラウザーを有効にするには:

1. ポータルでのアプリの登録時に、`http://localhost`をリダイレクト URI として構成します (現在、Azure B2C ではサポートされていません)。
2. パブリック クライアント アプリを構築するときは、このリダイレクト URI を指定します。
3. `.WithUseEmbeddedWebView(false)`を追加します。

```csharp
var pca = PublicClientApplicationBuilder
            .Create("<CLIENT_ID>")
            // or use a known port if you wish "http://localhost:1234"
            .WithRedirectUri("http://localhost")
            .Build();

var result = await pca.AcquireTokenInteractive(s_scopes)
                    .WithUseEmbeddedWebView(false)
                    .ExecuteAsync();
```

`http://localhost`を構成すると、MSAL.NET はランダムに開いているポートを見つけて使用します。 リダイレクト URI として `http://localhost` を使用しても安全です。 別のプロセスは、MSAL によって既にリッスンされているローカル ソケットでリッスンできません。 ブラウザーがこの URI にリダイレクトされるときに、ネットワーク通信は行われません。 何らかの方法で悪意のあるアプリが認証コードを傍受した場合でも (そのような既知の攻撃はありませんが、悪意のあるアプリがコンピューターに管理者アクセスできる場合は可能です)、 [PKCE](https://oauth.net/2/pkce/) プロトコルで説明されているように、アプリだけが認識する一時的なシークレットが必要なため、トークンと交換できません。 ポート 443 が予約されており、MSAL がリッスンできないため、アプリは HTTPS localhost エンドポイント (`https://localhost`) でリッスンできません。

##### Limitations

Azure B2C と ADFS 2019 では、どの*ポート* オプションもまだ実装されていません。 そのため、 `http://localhost` (ポートなし) リダイレクト URI は設定できませんが、(ポートを含む) URI のみを `http://localhost:1234` 。 つまり、独自のポート管理を行う必要があります。たとえば、いくつかのポートを予約し、リダイレクト URI として構成できます。 その後、アプリはポートが空になるまでそれらを循環させることができます。これは MSAL で使用できます。

詳細については、 [Localhost の例外](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/reply-url#localhost-exceptions)に関するページを参照してください。

#### Linux と macOS

Linux では、MSAL.NET は [xdg-open](https://manpages.ubuntu.com/manpages/questing/en/man1/xdg-open.1.html) などのツールを使用して既定のシステム ブラウザーを開きます。 `sudo`でブラウザー\*を開く方法は MSAL ではサポートされていないため、MSAL によって例外\*がスローされます。

macOS では、 `open <url>`を呼び出すことによってブラウザーが開きます。

#### エクスペリエンスのカスタマイズ

MSAL.NET は、トークンを受信したとき、またはエラーが発生したときに、HTTP メッセージまたは HTTP リダイレクトで応答できます。

```csharp
var options = new SystemWebViewOptions()
{
    HtmlMessageError = "<p> An error occurred: {0}. Details {1}</p>",
    BrowserRedirectSuccess = new Uri("https://www.microsoft.com");
}

await pca.AcquireTokenInteractive(s_scopes)
         .WithUseEmbeddedWebView(false)
         .WithSystemWebViewOptions(options)
         .ExecuteAsync();
```

#### 特定のブラウザーを開く

MSAL.NET でのブラウザーの開き方をカスタマイズできます。 たとえば、既定のブラウザーを使用する代わりに、特定のブラウザーを強制的に開くことができます。

```csharp
var options = new SystemWebViewOptions()
{
    OpenBrowserAsync = SystemWebViewOptions.OpenWithEdgeBrowserAsync
}
```

### モバイル アプリケーションの Web ビュー

Note

MSAL.NET バージョン 4.61.0 以降では、Xamarin Android および Xamarin iOS はサポートされていません。

埋め込み Web ビューは、.NET MAUI アプリケーションで有効にすることができます。 埋め込み Web ビューまたはシステム ブラウザーのいずれかを使用できます。 これは、ターゲットとするユーザー エクスペリエンスとセキュリティ上の問題に応じて選択できます。

#### 埋め込み Web ビューとシステム ブラウザーの違い

MSAL.NET の埋め込み Web ビューとシステム ブラウザーには、いくつかの視覚的な違いがあります。

**埋め込み Web ビューを使用した MSAL.NET での対話型サインイン:**

[Image: 埋め込み Web ビューの外観]

**システム ブラウザーを使用した MSAL.NET での対話型サインイン:**

[Image: システム ブラウザーの外観]

#### 開発者向けオプション

MSAL.NET を使用する開発者には、Microsoft Entra IDから対話型サインイン ダイアログを表示するためのオプションがいくつかあります。

- **システム ブラウザー。** システム ブラウザーは、ライブラリで既定で設定されます。 Android を使用している場合は、認証でサポートされているブラウザーの詳細については、 [システム](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-system-browser-android-considerations) ブラウザーを参照してください。 Android でシステム ブラウザーを使用する場合は、Chrome カスタム タブをサポートするブラウザーをデバイスにインストールすることをお勧めします。そうしないと、認証が失敗する可能性があります。
- **埋め込み Web ビュー。** MSAL.NET で埋め込み Web ビューのみを使用するには、`AcquireTokenInteractive` ビルダーに [WithUseEmbeddedWebView](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.acquiretokeninteractiveparameterbuilder.withuseembeddedwebview) メソッドが含まれています。

iOS アプリの場合:

```csharp
var result = app.AcquireTokenInteractive(scopes)
                .WithUseEmbeddedWebView(useEmbeddedWebview)
                .ExecuteAsync();
```

Android アプリの場合:

```csharp
var result = app.AcquireTokenInteractive(scopes)
                .WithParentActivityOrWindow(activity)
                .WithUseEmbeddedWebView(useEmbeddedWebview)
                .ExecuteAsync();
```

##### iOS での埋め込み Web ビューまたはシステム ブラウザーの選択

iOS アプリでは、 `AppDelegate.cs` で `ParentWindow` を初期化して `null`できます。 iOS では使用されません。

```csharp
App.ParentWindow = null; // no UI parent on iOS
```

##### Android での埋め込み Web ビューまたはシステム ブラウザーの選択

Android アプリでは、 `MainActivity.cs` で親アクティビティを設定して、認証結果が返されるようにすることができます。

```csharp
 App.ParentWindow = this;
```

次に、 `MainPage.xaml.cs`で次の手順を実行します。

```csharp
var result = await App.PCA.AcquireTokenInteractive(App.Scopes)
                      .WithParentActivityOrWindow(App.ParentWindow)
                      .WithUseEmbeddedWebView(true)
                      .ExecuteAsync();
```

##### Android でのカスタム タブの存在の検出

システム Web ブラウザーを使用して、ブラウザーで実行されているアプリで Single-Sign On を有効にしたいが、Android デバイスのユーザー エクスペリエンスにカスタム タブがサポートされていない場合は、 [IPublicClientApplication.IsSystemWebViewAvailable](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.ipublicclientapplication.issystemwebviewavailable)を呼び出して決定できます。 このメソッドは、Android パッケージ マネージャーがカスタム タブを検出した場合に `true` を返し、デバイスで検出されない場合は `false` します。

このメソッドによって返される値と要件に基づいて、次の決定を行うことができます。

- カスタム エラー メッセージをユーザーに返すことができます。たとえば、"Chrome をインストールして認証を続行してください" などです。
- フォールバックして、埋め込み Web ビューでサインイン ページを起動できます。

```csharp
bool useSystemBrowser = app.IsSystemWebViewAvailable();

authResult = await App.PCA.AcquireTokenInteractive(App.Scopes)
                      .WithParentActivityOrWindow(App.ParentWindow)
                      .WithUseEmbeddedWebView(!useSystemBrowser)
                      .ExecuteAsync();
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/web-apps-apis/authorization-codes"} -->
## MSAL.NET を使用して承認コードでトークンを取得する (Web サイトの場合) - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/authorization-codes
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: ユーザーが OpenID Connect を使用して Web アプリケーション (Web サイト) にログインすると、Web アプリケーションは承認コードを受け取り、これを利用して Web API を呼び出すトークンを取得できます。

ユーザーが OpenID Connect を使用して Web アプリケーション (Web サイト) にログインすると、Web アプリケーションは承認コードを受け取り、これを利用して Web API を呼び出すトークンを取得できます。 ASP.NET および ASP.NET Core Web アプリでは、`AcquireTokenByAuthorizationCode`の唯一の目的はトークン キャッシュにトークンを追加することです。これにより、`AcquireTokenSilent`を使用して API のトークンを取得するアプリケーション (通常はコントローラー内) でトークンを使用できるようになります。

### ASP.NET と ASP.NET Core: Microsoft.Identity.Web を使用する

ASP.NET Coreで Web アプリを構築する場合は、次の方法を使用することをお勧めします。[`Microsoft.Identity.Web`](https://github.com/AzureAD/microsoft-identity-web/)

### アプリケーションの登録

Microsoft Entra IDが承認コードとトークンをアプリケーションに返すことができるように、応答 URI を登録する必要があります。

また、[Azure ポータル](https://portal.azure.com/#blade/Microsoft_AAD_IAM/ActiveDirectoryMenuBlade/RegisteredAppsPreview)の対話型エクスペリエンスを使用するか、コマンド ライン ツール (PowerShell など) を使用して、アプリケーション シークレットを登録する必要もあります。

#### アプリケーション登録ポータルを使用したクライアント シークレットの登録

クライアント資格情報の管理は、アプリケーションの **[証明書とシークレット** ] ページで行われます。

[Image: Azure portal の Microsoft Entra 証明書ブレード]

- アプリケーション シークレット (名前付きクライアント シークレット) は、[**新しいクライアント シークレット**] を選択すると、機密クライアント アプリケーションの登録中にMicrosoft Entra IDによって生成されます。 その時点で、[ **保存**] を選択する前に、アプリで使用するシークレット文字列をクリップボードにコピーする必要があります。 この文字列は表示されなくなります。
- 証明書は、[証明書のアップロード] ボタンを使用してアプリケーション登録に **アップロード** されます

##### PowerShell を使用したクライアント シークレットの登録

[active-directory-dotnetcore-daemon-v2](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2) サンプルは、アプリケーション シークレットまたは証明書を Microsoft Entra アプリケーションに登録する方法を示しています。

- アプリケーション シークレットを登録する方法の詳細については、「[AppCreationScripts/Configure.ps1](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2/blob/5199032b352a912e7cc0fce143f81664ba1a8c26/AppCreationScripts/Configure.ps1#L190)」を参照してください。
- アプリケーションに証明書を登録する方法の詳細については、[AppCreationScripts-withCert/Configure.ps1](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2/blob/5199032b352a912e7cc0fce143f81664ba1a8c26/AppCreationScripts-withCert/Configure.ps1#L162-L178) を参照してください。

#### クライアント資格情報を使用した ConfidentialClientApplication の構築

このフローは、機密クライアント フローでのみ使用できます。したがって、保護された Web API は、クライアント資格情報 (クライアント シークレットまたは証明書) を、[ConfidentialClientApplicationBuilder](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.confidentialclientapplicationbuilder)メソッドまたは`WithClientSecret`メソッドを使用して、それぞれ`WithCertificate`に提供します。

[Image: IConfidentialClientApplication インターフェイス]

```csharp
IConfidentialClientApplication app;

#if !VariationWithCertificateCredentials
app = ConfidentialClientApplicationBuilder.Create(config.ClientId)
           .WithClientSecret(config.ClientSecret)
           .Build();
#else
// Building the client credentials from a certificate
X509Certificate2 certificate = ReadCertificate(config.CertificateName);
app = ConfidentialClientApplicationBuilder.Create(config.ClientId)
    .WithCertificate(certificate)
    .Build();
#endif
```

#### MSAL.NET での承認コードによるトークンの取得

認証コードを引き換えてトークンを取得してキャッシュするには、 `IConfidentialClientApplication` には `AcquireTokenByAuthorizationCode` というメソッドが含まれています。

```csharp
AcquireTokenByAuthorizationCode(
            IEnumerable<string> scopes,
            string authorizationCode)
```

この原則は、`Startup.cs` ファイルにあるアプリケーションの初期化を実行するコードの下に示されています。また、Microsoft ID プラットフォームで認証を追加するには、次のコードを追加する必要があります (コード内のコメントは自明である必要があります)。

```csharp
 services.AddAuthentication(AzureADDefaults.AuthenticationScheme)
         .AddAzureAD(options => configuration.Bind("AzureAd", options));

 services.Configure<OpenIdConnectOptions>(AzureADDefaults.OpenIdScheme, options =>
 {
  // The ASP.NET core templates are currently using Azure AD v1.0, and compute
  // the authority (as {Instance}/{TenantID}). We want to use the Microsoft Identity Platform v2.0 endpoint
  options.Authority = options.Authority + "/v2.0/";

  // Response type. We ask ASP.NET to request an Auth Code, and an IDToken
   options.ResponseType = OpenIdConnectResponseType.CodeIdToken;

  // The "offline_access" scope is needed to get a refresh token when users sign-in with
  // their Microsoft personal accounts
  // (it's required by MSAL.NET and automatically provided by Azure AD when users
  // sign-in with work or school accounts, but not with their Microsoft personal accounts)
  options.Scope.Add(OidcConstants.ScopeOfflineAccess);
  options.Scope.Add("user.read"); // for instance
 
  // If you want to restrict the users that can sign-in to specific organizations
  // Set the tenant value in the appsettings.json file to 'organizations', and add the
  // issuers you want to accept to options.TokenValidationParameters.ValidIssuers collection.
  // Otherwise validate the issuer
  options.TokenValidationParameters.IssuerValidator = 
         AadIssuerValidator.ForAadInstance(options.Authority).ValidateAadIssuer;

  // Set the nameClaimType to be preferred_username.
  // This change is needed because certain token claims from Azure AD v1.0 endpoint
  // (on which the original .NET core template is based) are different in Azure AD v2.0 endpoint. 
  // For more details see [ID Tokens](/azure/active-directory/develop/id-tokens) 
  // and [Access Tokens](/azure/active-directory/develop/access-tokens)
  options.TokenValidationParameters.NameClaimType = "preferred_username";

  // Handling the auth redemption by MSAL.NET so that a token is available in the token cache
  // where it will be usable from Controllers later (through the TokenAcquisition service)
  var handler = options.Events.OnAuthorizationCodeReceived;
  options.Events.OnAuthorizationCodeReceived = async context =>
  {
   // As AcquireTokenByAuthorizationCode is asynchronous we want to tell ASP.NET core
   // that we are handing the code even if it's not done yet, so that it does 
   // not concurrently call the Token endpoint.
   context.HandleCodeRedemption();

    // Call MSAL.NET AcquireTokenByAuthorizationCode
    var application = BuildConfidentialClientApplication(context.HttpContext,
                                                         context.Principal);
    var result = await application.AcquireTokenByAuthorizationCode(scopes.Except(scopesRequestedByMsalNet),
                                                                   context.ProtocolMessage.Code)
                                  .ExecuteAsync();

    // Do not share the access token with ASP.NET Core otherwise ASP.NET will cache it
    // and will not send the OAuth 2.0 request in case a further call to
    // AcquireTokenByAuthorizationCode in the future for incremental consent 
    // (getting a code requesting more scopes)
    // Share the ID Token so that the identity of the user is known in the application (in 
    // HttpContext.User)
    context.HandleCodeRedemption(null, result.IdToken);

    // Call the previous handler if any
    await handler(context);
   };
```

ASP.NET Coreでは、機密クライアント アプリケーションを構築すると、`HttpContext`にある情報が利用されます。この情報には、特に Web サイトの URL が含まれており、`RedirectUri`の構築に役立ちます。

```csharp
/// <summary>
/// Creates an MSAL Confidential client application
/// </summary>
/// <param name="httpContext">HttpContext associated with the OIDC response</param>
/// <param name="claimsPrincipal">Identity for the signed-in user</param>
/// <returns></returns>
private IConfidentialClientApplication BuildConfidentialClientApplication(HttpContext httpContext, ClaimsPrincipal claimsPrincipal)
{
 var request = httpContext.Request;

 // Find the URI of the application)
 string currentUri = UriHelper.BuildAbsolute(request.Scheme, request.Host, request.PathBase, azureAdOptions.CallbackPath ?? string.Empty);

 // Updates the authority from the instance (including national clouds) and the tenant
 string authority = $"{azureAdOptions.Instance}{azureAdOptions.TenantId}/";

 // Instantiates the application based on the application options (including the client secret)
 var app = ConfidentialClientApplicationBuilder.CreateWithApplicationOptions(_applicationOptions)
               .WithRedirectUri(currentUri)
               .WithAuthority(authority)
               .Build();

 // Initialize token cache providers. In the case of Web applications, there must be one
 // token cache per user. 
 // Here the key of the token cache is in the claimsPrincipal
 // which contains the identity of the signed-in user,
 if (this.UserTokenCacheProvider != null)
 {
  this.UserTokenCacheProvider.Initialize(app.UserTokenCache, httpContext, claimsPrincipal);
 }
 if (this.AppTokenCacheProvider != null)
 {
  this.AppTokenCacheProvider.Initialize(app.AppTokenCache, httpContext);
 }
 return app;
}
```

Web アプリでは、トークン キャッシュのシリアル化も実装する必要があります。 これについては、MSAL.NET での[トークン キャッシュのシリアル化で説明します](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-token-cache-serialization)。

##### ゲスト ユーザー

テナント内のゲスト ユーザーは、最初はそのテナントではなく、他のテナントで作成されたユーザー アカウントです。 MSAL でトークンを取得する場合、ホーム アカウント ID がユーザーの適切なホーム テナントを表示するには、OWIN ASP.NET Coreと ASP.NET で特定の設定を行う必要があります。 Microsoft。Identity.Web を使用すると、両方のプラットフォームのゲスト ユーザーのログインが簡素化されます。 詳細については、 [OWIN サンプル アプリ](https://github.com/AzureAD/microsoft-identity-web/tree/master/tests/DevApps/aspnet-mvc) を参照してください。

#### Troubleshooting

- このコードは、トークンを引き換えるために **1 回** だけ使用できます。 `AcquireTokenByAuthorizationCode` は、同じ承認コードを使用して複数回呼び出すべきではありません (プロトコル標準仕様では明示的に禁止されています)。 コードを何度か、意識的に引き換える場合、またはフレームワークによっても実行されることを認識していない場合は、エラーが発生します。 `'invalid_grant', 'AADSTS70002: Error validating credentials. AADSTS54005: OAuth2 Authorization code was already redeemed, please retry with a new valid code or use an existing refresh token`
- 特に、ASP.NET/ASP.NET Core アプリケーションを記述している場合は、コードを既に使用していることを ASP.NET/Core フレームワークに伝えない場合に発生する可能性があります。 そのためには、`AuthorizationCodeReceived`イベント ハンドラーの一部として `context.HandleCodeRedemption()` を呼び出す必要があります。
- 最後に、アクセス トークンを ASP.NET と共有しないようにします。そうしないと、増分同意が正しく行われるのを妨げる可能性があります (詳細については、問題 #[693](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet/issues/693) を参照してください)。

この操作ではトークン キャッシュにトークンが追加されるため、後でトークンを必要とするコントローラーは、[HomeController.cs#L55-L76](https://github.com/Azure-Samples/active-directory-dotnet-webapp-openidconnect-v2/blob/c2087374e849fd58b5bf75ffebef1ac0e106884d/WebApp/Controllers/HomeController.cs#L56-L76) の SendMail() メソッドと同様に、トークンをサイレントで取得できます。

#### プロトコルに関するドキュメント

プロトコルの詳細については、[v2.0 プロトコル - OAuth 2.0 承認コード フロー](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-oauth2-auth-code-flow)に関するページを参照してください。

#### 承認コード フローを使用した興味深いサンプル

| Sample | 説明 |
| --- | --- |
| 分岐 [aspnetcore2-2-signInAndCallGraph](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/aspnetcore2-2-signInAndCallGraph) の active-directory-aspnetcore-webapp-openidconnect-v2 | ユーザーが職場/学校アカウントまたはMicrosoft アカウントの両方を使用してサインインできるように、Microsoft ID プラットフォーム エンドポイント経由でサインオンを処理する Web アプリケーション。 このサンプルでは、MSAL を使用して、増分同意の処理方法など、Microsoft Graphを呼び出すためのトークンを取得する方法も示します。 [Image: Web アプリ トポロジ] |
| [active-directory-dotnet-webapp-openidconnect-v2](https://github.com/Azure-Samples/active-directory-dotnet-webapp-openidconnect-v2) | ユーザーが職場/学校アカウントまたはMicrosoft アカウントの両方を使用してサインインできるように、Microsoft ID プラットフォーム エンドポイント経由でサインオンを処理する Web アプリケーション。 このサンプルでは、MSAL を使用してMicrosoft Graphを呼び出すためのトークンを取得する方法も示します。 [Image: 複雑な Web アプリケーション トポロジ] |
| [active-directory-dotnet-admin-restricted-scopes-v2](https://github.com/azure-samples/active-directory-dotnet-admin-restricted-scopes-v2) | Azure AD v2.0 エンドポイントを使用して、管理上の同意を必要とするアクセス許可の同意を収集する方法を示す ASP.NET MVC アプリケーション。 [Image: グループ マネージャー Web アプリトポロジ] |

[Web アプリでの承認コードを使用したトークンの取得を](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/authorization-codes)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/web-apps-apis/client-credential-flows"} -->
## クライアント資格情報フロー - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/client-credential-flows
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: クライアント資格情報認証フローを使用すると、サービス、API、デーモン アプリケーションは、直接ユーザー操作なしでトークンを取得できます。

### サポートされているプラットフォーム

MSAL.NET はマルチフレームワーク ライブラリですが、機密クライアント フローは、アプリケーションでシークレットをデプロイする安全な方法がないため、モバイルおよびクライアント向けプラットフォームでは使用できません。

### サポートされているクライアント資格情報

MSAL.NET では、Microsoft Entra ポータルに登録する必要がある 2 種類のクライアント資格情報がサポートされています。

- アプリケーション シークレット (*運用環境のシナリオでは推奨されません*)。
- 証明 書。

高度なシナリオでは、他の 2 種類の資格情報を使用できます。

- 署名されたクライアント アサーション。
- 送付する証明書および追加要求。

追加の詳細については、[機密クライアント アサーション](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-client-assertions) ドキュメントを参照してください。

#### 使用例

```csharp
// This object will cache tokens in-memory - keep it as a singleton
var singletonApp = ConfidentialClientApplicationBuilder.Create(config.ClientId)
        // Don't specify authority here, we'll do it on the request 
        .WithCertificate(certificate) // or .WithClientSecret(secret)
        .Build();

// If instead you need to re-create the ConfidentialClientApplication on each request, you MUST customize 
// the cache serialization (see below)

// When making the request, specify the tenant-based authority
var authResult = await app.AcquireTokenForClient(scopes: new [] {  "some_app_id_uri/.default"}) // Uses the token cache automatically, which is optimized for multi-tenant access
        // Do not use "common" or "organizations"!
        .WithTenantId("{tenantID}") // or .WithTenantIdFromAuthority({"authority"})
        .ExecuteAsync();
```

Important

クライアント資格情報フローに `common` または `organizations` 機関を使用しないでください。 認証機関にテナント ID を指定します。

### カスタム キャッシュのシリアル化

サービスがマルチテナントの場合 (つまり、別のテナントにあるリソースのトークンが必要です)、 [マルチテナント サービスのクライアント資格情報フローについては MSAL](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/client-credential-multi-tenant) を参照してください。

トークン キャッシュは、(メモリ内や Redis などの分散システムを使用して) 好みの場所にシリアル化できます。 これを行うには、次の操作を行います。

- [`ConfidentialClientApplication`](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.confidentialclientapplication)の複数のインスタンス間でトークン キャッシュを共有します。
- トークン キャッシュを保持して、異なるマシン間で共有します。

実装の詳細については、 [分散キャッシュの実装](https://github.com/AzureAD/microsoft-identity-web/tree/master/src/Microsoft.Identity.Web.TokenCache/Distributed) と [トークン キャッシュのバインド](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/msal-net-token-cache-serialization) に関するページを参照してください。

[サンプル](https://github.com/Azure-Samples/active-directory-dotnet-v1-to-v2/blob/b48c10180665260a1aec78a9acf7d1b1ff97e5ba/ConfidentialClientTokenCache/Program.cs)を確認して、トークン キャッシュのシリアル化のしくみを確認してください。

### アプリケーションの高可用性の確保

#### サービスのメモリ不足

MSAL.NET でのマルチテナント アーキテクチャの詳細な概要については、[マルチテナント サービスでのクライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/client-credential-multi-tenant)に対する MSAL.NET の使用に関するページを参照してください。

サービスを実行しているマシンに十分な RAM をプロビジョニングするか、分散キャッシュを使用してください。 1 つのトークンのサイズは数キロバイト (KB) で、アプリケーションが対話するテナントごとに 1 つのトークンが格納されます。

#### 分散サービスの各マシンで新しいトークンを要求しないようにする

Redis などの分散キャッシュを使用 [します](https://redis.io/)。

#### キャッシュ ヒット 率の監視

認証結果オブジェクトは、トークンがキャッシュから取得されたかどうかを示します。

```csharp
authResult.AuthenticationResultMetadata.TokenSource == TokenSource.Cache
```

#### "ループが検出されました" エラーの処理

トークンを取得するために Microsoft Entra ID を頻繁に呼び出しすぎているため、サービスによってスロットルされています。 この問題を軽減するには、キャッシュを使用する必要があります。使用するのは、インメモリ キャッシュ（上記のサンプルのとおり）または永続化されたキャッシュのいずれかです。

#### トークン取得の待機時間が長い

トークン キャッシュヒット率が高く設定されていることを確認してください。 メモリ内キャッシュは、異なるクライアント ID または異なるテナント ID から取得されたトークンを検索するために最適化されています。 これは、異なるスコープを持つトークンを格納するために最適化されていません。 スコープを含む別のキャッシュ キーを使用する必要があります。 その他の推奨事項については、 [パフォーマンス テスト](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/performance-testing) を参照してください。

### Microsoft Entra IDを使用したアプリケーション シークレットまたは証明書の構成

アプリケーション シークレットは、[Azure ポータル](https://portal.azure.com/)の対話型エクスペリエンスを使用するか、PowerShell などのコマンド ライン ツールを使用して登録できます。

#### アプリケーション登録ポータルを使用したクライアント シークレットの登録

クライアント資格情報の管理は、Microsoft Entra ポータルの登録済みアプリケーションの **[証明書とシークレット**] ページで行われます。

[Image: 証明書とAzure portalのシークレット ビュー]

#### PowerShell を使用したクライアント シークレットの登録

[`active-directory-dotnetcore-daemon-v2`](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2)サンプルは、アプリケーション シークレットまたは証明書をMicrosoft Entra アプリケーションに登録する方法を示しています。

- アプリケーション シークレットを登録します。 [`AppCreationScripts/Configure.ps1`](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2/blob/5199032b352a912e7cc0fce143f81664ba1a8c26/AppCreationScripts/Configure.ps1#L190)
- アプリケーションに証明書を登録します。 [`AppCreationScripts-withCert/Configure.ps1`](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2/blob/5199032b352a912e7cc0fce143f81664ba1a8c26/AppCreationScripts-withCert/Configure.ps1#L162-L178)

### クライアント資格情報の使用

MSAL.NET では、[ConfidentialClientApplication](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.confidentialclientapplication)インスタンス化中にクライアント資格情報がパラメーターとして渡されます。 機密クライアント アプリケーションが構築されたら、トークンを取得するには、 [AcquireTokenForClient(IEnumerable&lt;String&gt;)](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.confidentialclientapplication.acquiretokenforclient#microsoft-identity-client-confidentialclientapplication-acquiretokenforclient%28system-collections-generic-ienumerable%28%28system-string%29%29%29) またはそのオーバーロードの 1 つを呼び出し、スコープを渡し、トークンの更新が必要かどうかを示す必要があります。

### クライアント アサーション

機密クライアント アプリケーションは、クライアント シークレットまたは証明書の代わりに、クライアント アサーションを使用してその ID を証明することもできます。 このシナリオは、[機密クライアント アサーション](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/confidential-client-assertions) ドキュメントで詳しく説明されています。

### 注釈

#### `AcquireTokenForClient` アプリケーション トークン キャッシュを使用する

[AcquireTokenForClient](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.confidentialclientapplication.acquiretokenforclient#microsoft-identity-client-confidentialclientapplication-acquiretokenforclient%28system-collections-generic-ienumerable%28%28system-string%29%29%29) は、 **(** ユーザー トークン キャッシュではなく) アプリケーション トークン キャッシュを使用します。

[AcquireTokenSilent](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.clientapplicationbase.acquiretokensilent#microsoft-identity-client-clientapplicationbase-acquiretokensilent%28system-collections-generic-ienumerable%28%28system-string%29%29-microsoft-identity-client-iaccount%29) が[ユーザー](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.confidentialclientapplication.acquiretokenforclient#microsoft-identity-client-confidentialclientapplication-acquiretokenforclient%28system-collections-generic-ienumerable%28%28system-string%29%29%29) トークン キャッシュを使用する場合は、[AcquireTokenForClient を](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.clientapplicationbase.acquiretokensilent#microsoft-identity-client-clientapplicationbase-acquiretokensilent%28system-collections-generic-ienumerable%28%28system-string%29%29-microsoft-identity-client-iaccount%29)呼び出す前に **AcquireTokenSilent** を呼び出さないでください。

[AcquireTokenForClient は](https://learn.microsoft.com/ja-jp/dotnet/api/microsoft.identity.client.confidentialclientapplication.acquiretokenforclient#microsoft-identity-client-confidentialclientapplication-acquiretokenforclient%28system-collections-generic-ienumerable%28%28system-string%29%29%29)**、アプリケーション** トークン キャッシュ自体をチェックして更新します。

アプリケーション [とユーザーのトークン キャッシュ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization#token-cache-types) の違いの詳細については、「トークン キャッシュの種類」を参照してください。

#### 要求するスコープ

クライアントの資格情報フローのために要求するスコープは、後に `/.default` が続くリソースの名前です。 この表記は、アプリケーションの登録時に静的に宣言された**アプリケーション レベルのアクセス許可**を使用するようにMicrosoft Entra IDに指示します。 API のアクセス許可は、テナント管理者が付与する必要があります。

この構成は次のようになります。

```csharp
ResourceId = "someAppIDURI";
var scopes = new [] {  ResourceId+"/.default"};

var result = app.AcquireTokenForClient(scopes);
```

#### アプリがデーモンの場合、応答 URL は必要ありません

機密クライアント アプリケーションでクライアント資格情報フロー **のみを** 使用する場合は、コンストラクターで応答 URL を指定する必要はありません。

### Samples

| Sample | Platform | 説明 |
| --- | --- | --- |
| [active-directory-dotnetcore-daemon-v2](https://github.com/Azure-Samples/active-directory-dotnetcore-daemon-v2) | .NET | ユーザーの代わりに、アプリケーションの ID を使用してMicrosoft Graphクエリを実行するテナントのユーザーを表示する単純な.NET アプリケーション。<br>[Image: デーモン アプリ トポロジ]<br>このサンプルでは、証明書のバリエーションも示しています。<br>[Image: デーモン証明書ベースの認証トポロジ] |
| [active-directory-dotnet-daemon-v2](https://github.com/Azure-Samples/active-directory-dotnet-daemon-v2) | ASP.NET MVC | ユーザーの代わりに、アプリケーションの ID を使用してMicrosoft Graphからデータを同期する Web アプリケーション。<br>[Image: UserSync アプリ トポロジ] |

### 詳細情報

詳細については、プロトコルのドキュメントを [参照してください](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/v2-oauth2-client-creds-grant-flow)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/msal/dotnet/acquiring-tokens/web-apps-apis/confidential-client-assertions"} -->
## クライアント アサーション (MSAL.NET) - Microsoft Authentication Library for .NET

- Source: https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/web-apps-apis/confidential-client-assertions
- Service: msal / msal-dotnet
- Article date: 2025-05-22
- Summary: .NET (MSAL.NET) のMicrosoft Authentication Libraryでの機密クライアント アプリケーションに対する署名付きクライアント アサーションのサポートについて説明します。

機密クライアント アプリケーションは、ID を証明するために、シークレットをMicrosoft Entra IDと交換します。 シークレットは次のようになります。

- クライアント シークレット (アプリケーション パスワード)。
- 証明書。標準の要求を含む署名付きアサーションの構築に使用されます。

このシークレットは直接署名されたアサーションの場合もあります。

MSAL.NET には、機密クライアント アプリに資格情報またはアサーションを提供する 4 つの方法があります。

- `.WithClientSecret()`
- `.WithCertificate()`
- `.WithClientAssertion()`
- `.WithClientClaims()`

Note

`WithClientAssertion()` API を使用して機密クライアントのトークンを取得することは可能ですが、既定では使用しないことをお勧めします。これは、より高度であり、一般的ではない非常に具体的なシナリオを処理するように設計されているためです。 `.WithCertificate()` API を使用すると、MSAL.NET はこれを処理できます。 この API は、必要に応じて認証要求をカスタマイズする機能を提供しますが、ほとんどの認証シナリオでは、 `.WithCertificate()` によって作成された既定のアサーションで十分です。 この API は、MSAL.NET が内部的に署名操作を実行できない場合の回避策としても使用できます。 2 つの違いは、`WithCertificate()`を使用するには、アサーションを作成するコンピューターで証明書と秘密キーを使用できるようにする必要があり、`WithClientAssertion()`を使用すると、Azure Key Vault内やマネージド ID から、またはハードウェア セキュリティ モジュールを使用して、他の場所でアサーションを計算できます。

#### クライアント アサーション

これは、証明書を自分で処理する場合に便利です。 たとえば、署名Azure KeyVault の API を使用する場合、証明書をダウンロードする必要がなくなります。 署名付きクライアント アサーションは、base64 でエンコードされた Microsoft Entra ID で義務付けられている必要な認証要求を含むペイロードを含む署名付き JWT の形式をとります。 また、"フェデレーション ID 資格情報" シナリオでは、別の ID プロバイダーの JWT を使用することもできます。

デリゲートを使用すると、MSAL が ID プロバイダーから新しいトークンを取得する必要があるたびにアサーションを計算できます。 キャッシュにトークンが見つかった場合、MSAL はデリゲートを呼び出しません。

```csharp
string signedClientAssertion = GetOrComputeAssertion();
app = ConfidentialClientApplicationBuilder.Create(config.ClientId)
                                          .WithClientAssertion(async (AssertionRequestOptions options) => {
                                            // use 'options.ClientID' or 'options.TokenEndpoint' to generate client assertion
                                            return await GetClientAssertionAsync(options.ClientID, options.TokenEndpoint, options.CancellationToken); 
                                          })
                                          .Build();
```

署名付きアサーションの[Microsoft Entra ID が想定するクレーム](https://learn.microsoft.com/ja-jp/azure/active-directory/develop/certificate-credentials)は次のとおりです。

| 要求の種類 | 価値 | 説明 |
| --- | --- | --- |
| aud | `https://login.microsoftonline.com/{tenantId}/oauth2/v2.0/token` | "aud" (対象ユーザー) 要求は、JWT が意図されている受信者を識別します (こちら Microsoft Entra ID) [RFC 7519、セクション 4.1.3](https://tools.ietf.org/html/rfc7519#section-4.1.3) を参照してください。 この場合、その受信者は ID プロバイダーのトークン エンドポイントです |
| 経験値 | 1601519414 | "exp" (有効期限) 要求は、JWT の処理を受け入れることができなくなる時刻を指定します。 [RFC 7519、セクション 4.1.4](https://tools.ietf.org/html/rfc7519#section-4.1.4) を参照してください。 これにより、その時点まではアサーションを使用できるため、`nbf` の後、長くても 5～10 分以内の短い時間にしてください。 Microsoft Entra IDでは、現在`exp`時間に制限はありません。 |
| iss | {ClientID} | "iss" (発行者) 要求は、JWT を発行したプリンシパル (この場合はクライアント アプリケーション) を識別します。 GUID アプリケーション ID を使用します。 |
| jti | （1つの Guid） | "jti" (JWT ID) 要求は、JWT の一意の識別子を提供します。 識別子の値は、同じ値が誤って別のデータ オブジェクトに割り当てられる可能性がごくわずかであることを保証する方法で割り当てる必要があります。 アプリケーションで複数の発行者を使用する場合は、異なる発行者によって生成された値間でも競合を防ぐ必要があります。 "jti" 値は、大文字と小文字を区別する文字列です。 [RFC 7519、セクション 4.1.7](https://tools.ietf.org/html/rfc7519#section-4.1.7) |
| nbf | 1601519114 | "nbf" (not before) クレームは、指定した時刻より前には JWT を処理に受け入れてはならないことを示します。 [RFC 7519、セクション 4.1.5](https://tools.ietf.org/html/rfc7519#section-4.1.5)。 現在の時刻を使用することが適切です。 |
| サブ | {ClientID} | "sub" (サブジェクト) 要求は JWT のサブジェクトを識別します。この場合は、アプリケーションも識別します。 `iss`と同じ値を使用します。 |

証明書をクライアント シークレットとして使用する場合は、証明書を安全にデプロイする必要があります。 Windows上の証明書ストアやAzure Key Vaultを使用して、プラットフォームでサポートされているセキュリティで保護された場所に証明書を格納することをお勧めします。

#### アサーションの作成

これは、[Microsoft.IdentityModel.JsonWebTokens](https://www.nuget.org/packages/Microsoft.IdentityModel.JsonWebTokens/) を使用してアサーションを作成する例です。

```csharp
        string GetSignedClientAssertion(X509Certificate2 certificate, string tenantId, string clientId)
        {                            
            // no need to add exp, nbf as JsonWebTokenHandler will add them by default.
            var claims = new Dictionary<string, object>()
            {
                { "aud", tokenEndpoint },
                { "iss", clientId },
                { "jti", Guid.NewGuid().ToString() },
                { "sub", clientId }
            };

            var securityTokenDescriptor = new SecurityTokenDescriptor
            {
                Claims = claims,
                SigningCredentials = new X509SigningCredentials(certificate)
            };

            var handler = new JsonWebTokenHandler();
            var signedClientAssertion = handler.CreateToken(securityTokenDescriptor);
        }
```

または、Microsoft.IdentityModel.JsonWebTokens を使用したくない場合:

```csharp
static string Base64UrlEncode(byte[] arg)
{
    char Base64PadCharacter = '=';
    char Base64Character62 = '+';
    char Base64Character63 = '/';
    char Base64UrlCharacter62 = '-';
    char Base64UrlCharacter63 = '_';

    string s = Convert.ToBase64String(arg);
    s = s.Split(Base64PadCharacter)[0]; // RemoveAccount any trailing padding
    s = s.Replace(Base64Character62, Base64UrlCharacter62); // 62nd char of encoding
    s = s.Replace(Base64Character63, Base64UrlCharacter63); // 63rd char of encoding

    return s;
}

static string GetSignedClientAssertion(X509Certificate2 certificate, string tenantId, string clientId)
{
    // Get the RSA with the private key, used for signing.
    var rsa = certificate.GetRSAPrivateKey();

    //alg represents the desired signing algorithm, which is SHA-256 in this case
    //x5t represents the certificate thumbprint base64 url encoded
    var header = new Dictionary<string, string>()
    {
        { "alg", "PS256"},
        { "typ", "JWT" },
        { "x5t#S256", Base64UrlHelpers.Encode(certificate.GetCertHash(HashAlgorithmName.SHA256))},
    };

    //Please see the previous code snippet on how to craft claims for the GetClaims() method
    var claims = GetClaims(tenantId, clientId);

    var headerBytes = JsonSerializer.SerializeToUtf8Bytes(header);
    var claimsBytes = JsonSerializer.SerializeToUtf8Bytes(claims);
    string token = Base64UrlEncode(headerBytes) + "." + Base64UrlEncode(claimsBytes);

    string signature = Base64UrlEncode(rsa.SignData(Encoding.UTF8.GetBytes(token), HashAlgorithmName.SHA256, RSASignaturePadding.Pss));
    string signedClientAssertion = string.Concat(token, ".", signature);
    return signedClientAssertion;
}
```

#### WithClientClaims

場合によっては、開発者はアサーションにいくつかの要求を挿入する必要がありますが、それでも MSAL でアサーションと署名の作成を処理したいと考えています。

`WithClientClaims(X509Certificate2 certificate, IDictionary<string, string> claimsToSign, bool mergeWithDefaultClaims = true)` は、Microsoft Entra ID で想定される要求に加えて、送信したい追加のクライアント要求を含む署名付きアサーションを生成します。

```csharp
string ipAddress = "192.168.1.2";
X509Certificate2 certificate = ReadCertificate(config.CertificateName);
app = ConfidentialClientApplicationBuilder.Create(config.ClientId)
                                          .WithAuthority(new Uri(config.Authority))
                                          .WithClientClaims(certificate, 
                                                                      new Dictionary<string, string> { { "client_ip", ipAddress } })
                                          .Build();

```

渡すディクショナリ内のいずれかの要求が必須の要求の 1 つと同じ場合、追加の要求の値が考慮されます。 これは、MSAL.NET が計算したクレームを上書きします。

Microsoft Entra IDで想定される必須の要求を含め、独自の要求を指定する場合は、`false` パラメーターに`mergeWithDefaultClaims`を渡します。
<!-- /MSL-PAGE -->
