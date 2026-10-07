# Microsoft Learn — Microsoft Entra / 開発者向け (Identity Platform・MSAL・Microsoft.Identity.Web) (part 3)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 73

---

<!-- MSL-PAGE {"url":"entra/identity-platform/how-to-web-app-node-use-certificate"} -->
## Node.js Web アプリでの認証にクライアント証明書を使用する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-node-use-certificate
- Service: identity-platform
- Article date: 2025-03-16
- Summary: Node.js Web アプリでの認証に、シークレットではなくクライアント証明書を使用する方法について説明します

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

Microsoft Entra External ID は、 [機密クライアント アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-applications)の 2 種類の認証をサポートしています。パスワードベースの認証 (クライアント シークレットなど) と証明書ベースの認証。 より高いレベルのセキュリティを実現するには、機密クライアント アプリケーションで (クライアント シークレットではなく) 証明書を資格情報として使用することをお勧めします。

運用環境では、既知の証明機関によって署名された証明書を購入し、 [Azure Key Vault](https://azure.microsoft.com/products/key-vault/) を使用して証明書のアクセスと有効期間を管理する必要があります。 ただし、テストの目的で自己署名証明書を作成し、これを使って認証するようにアプリを構成できます。

この記事では、Azure portal、OpenSSL、または PowerShell で [Azure Key Vault](https://azure.microsoft.com/products/key-vault/) を使用して自己署名証明書を生成する方法について説明します。 クライアント シークレットが既にある場合は、それを安全に削除する方法について説明します。

必要に応じて、 [.NET](https://learn.microsoft.com/ja-jp/azure/key-vault/certificates/quick-create-net)、 [Node.js](https://learn.microsoft.com/ja-jp/azure/key-vault/certificates/quick-create-node)、 [Go](https://learn.microsoft.com/ja-jp/azure/key-vault/certificates/quick-create-go)、 [Python](https://learn.microsoft.com/ja-jp/azure/key-vault/certificates/quick-create-python) 、または [Java](https://learn.microsoft.com/ja-jp/azure/key-vault/certificates/quick-create-java) クライアント ライブラリを使用して、プログラムによって自己署名証明書を作成することもできます。

### [前提条件]

- [Node.js](https://nodejs.org)。
- [Visual Studio Code](https://code.visualstudio.com/download) または別のコード エディター。
- 外部テナント。 まだお持ちでない場合は、 [無料試用版にサインアップ](https://aka.ms/ciam-free-trial?wt.mc_id=ciamcustomertenantfreetrial_linkclick_content_cnl)してください。
- [OpenSSL](https://wiki.openssl.org/index.php/Binaries) または [Chocolatey](https://community.chocolatey.org/packages/openssl) を使用して Windows に [OpenSSL](https://chocolatey.org/) を簡単にインストールできます。
- [Windows PowerShell](https://learn.microsoft.com/ja-jp/powershell/scripting/windows-powershell/install/installing-windows-powershell) または Azure サブスクリプション。

### 自己署名証明書を作成する

ローカル コンピューターに既存の自己署名証明書がある場合は、この手順をスキップして、 アプリの登録に証明書をアップロードするに進むことができます。

## [Azure Key Vault - Azure portal を通じて](#tab/azure-key-vault)
[Azure Key Vault](https://learn.microsoft.com/ja-jp/azure/key-vault/certificates/quick-create-portal) を使用して、アプリの自己署名証明書を生成できます。 Azure Key Vault を使用すると、パートナー証明機関 (CA) の割り当てや証明書ローテーションの自動化などの利点があります。

Azure Key Vault に既存の自己署名証明書があり、それをダウンロードせずに使用する場合は、この手順をスキップして、「 Azure Key Vault から自己署名証明書を直接使用する」に進みます。 それ以外の場合は、次の手順に従って証明書を生成します

1. 「 [Azure portal を使用して Azure Key Vault から証明書を設定して取得](https://learn.microsoft.com/ja-jp/azure/key-vault/certificates/quick-create-portal) する」の手順に従って、証明書を作成してダウンロードします。
2. 証明書を作成したら、 *.cer* ファイルと *.pfx* ファイル ( *ciam-client-app-cert.cer* 、 *ciam-client-app-cert.pfx* など) の両方をダウンロードします。 *.cer* ファイルには公開キーが含まれており、Microsoft Entra 管理センターにアップロードするファイルです。
3. ターミナルで次のコマンドを実行して、 *.pfx* ファイルから秘密キーを抽出します。 パスフレーズを入力するように求められたら、設定しない場合はただ **Enter** キーを押してください。 それ以外の場合は、任意のパス フレーズを入力します。

    ```console
    openssl pkcs12 -in ciam-client-app-cert.pfx -nocerts -out ciam-client-app-cert.key
    ```

    *ciam-client-app-cert.key* ファイルは、アプリで使用するファイルです。

## [Windows PowerShell](#tab/windows-powershell)
1. 「 [自己署名パブリック証明書を作成してアプリケーションを認証する」の手順を使用します](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-create-self-signed-certificate)。 必ず、秘密キーがある公開証明書をエクスポートしてください。 `certificateName`には、*ciam-client-app-cert* を使用します。
2. ターミナルで次のコマンドを実行して、 *.pfx* ファイルから秘密キーを抽出します。 パス フレーズを入力するように求められたら、任意のパス フレーズを入力します。

    ```console
    openssl pkcs12 -in ciam-client-app-cert.pfx -nocerts -out ciam-client-app-cert.key
    ```

これらの手順を完了すると、 *.cer* ファイルと *、ciam-client-app-cert.keyやciam-client-app-cert.cer* などの *.key* ファイル *が作成されます*。 *.key* ファイルは、アプリで使用するファイルです。 *.cer* ファイルは、Microsoft Entra 管理センターにアップロードするファイルです。

## [OpenSSL](#tab/openssl)
ご利用のターミナルで、次のコマンドを実行します。 パス フレーズを入力するように求められたら、任意のパス フレーズを入力します。

```console
openssl req -x509 -newkey rsa:2048 -keyout ciam-client-app-cert.key -out ciam-client-app-cert.crt -subj "/CN=ciamclientappcert.com"
```

コマンドの実行が完了すると、*.crt* と*、ciam-client-app-cert.key*や *ciam-client-app-cert.crt* などの*.key* ファイルが必要になります。 *.key* ファイルは、アプリで使用するファイルです。 *.cer* ファイルは、Microsoft Entra 管理センターにアップロードするファイルです。

---

### アプリ登録に証明書をアップロードする

クライアント アプリ証明書を使用するには、Microsoft Entra 管理センターで登録したアプリを証明書に関連付ける必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部メニューの **[設定]** アイコン  を使用して、[ **ディレクトリ + サブスクリプション** ] メニューから外部テナントに切り替えます。
3. **Entra ID**&gt;**App 登録に移動します**。
4. アプリ登録の一覧から、証明書に関連付けるアプリ ( *ciam-client-app* など) を選択します。
5. [ **管理**] で、[ **証明書とシークレット**] を選択します。
6. [ **証明書**] を選択し、[ **証明書のアップロード**] を選択します。
7. [ファイル ファイルの **選択** ] アイコンを選択し、アップロードする証明書 ( *ciam-client-app-cert.pem* 、 *ciam-client-app-cert.cer* 、 *ciam-client-app-cert.crt* など) を選択します。
8. [ **説明]** に、 *CIAM クライアント アプリ証明書*などの説明を入力し、[ **追加]** を選択して証明書をアップロードします。 証明書がアップロードされると、 **拇印**、 **開始日**、有効期限 *の* 値が表示されます。
9. 後でクライアント アプリを構成するときに使用する **拇印** の値を記録します。

アプリケーション用のクライアント シークレットが既に用意されている場合は、アプリケーションを偽装する悪意のあるアプリケーションを回避するために、それを削除する必要があります。

1. **[クライアント シークレット**] タブに移動し、[**削除**] アイコンを選択します。
2. 表示されるポップアップ ウィンドウで、[ **はい**] を選択します。

### 証明書を使用するように Node.js アプリを構成する

アプリの登録を証明書に関連付けたら、証明書の使用を開始するようにアプリ コードを更新する必要があります。

1. `msalConfig` の  など、MSAL 構成オブジェクトが含まれるファイルを見つけて、次のコードのように更新します。 クライアント シークレットが存在する場合は、必ずそれを削除します。

    ```javascript
    require('dotenv').config();
    const fs = require('fs'); //// import the fs module for reading the key file
    const crypto = require('crypto');
    const TENANT_SUBDOMAIN = process.env.TENANT_SUBDOMAIN || 'Enter_the_Tenant_Subdomain_Here';
    const REDIRECT_URI = process.env.REDIRECT_URI || 'http://localhost:3000/auth/redirect';
    const POST_LOGOUT_REDIRECT_URI = process.env.POST_LOGOUT_REDIRECT_URI || 'http://localhost:3000';
    
    const privateKeySource = fs.readFileSync('PATH_TO_YOUR_PRIVATE_KEY_FILE')
    
    const privateKeyObject = crypto.createPrivateKey({
        key: privateKeySource,
        passphrase: 'Add_Passphrase_Here',
        format: 'pem'
    });
    
    const privateKey = privateKeyObject.export({
        format: 'pem',
        type: 'pkcs8'
    });
    
    /**
     * Configuration object to be passed to MSAL instance on creation.
     * For a full list of MSAL Node configuration parameters, visit:
     * https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/configuration.md
     */
        const msalConfig = {
            auth: {
                clientId: process.env.CLIENT_ID || 'Enter_the_Application_Id_Here', // 'Application (client) ID' of app registration in Azure portal - this value is a GUID
                authority: process.env.AUTHORITY || `https://${TENANT_SUBDOMAIN}.ciamlogin.com/`, 
                clientCertificate: {
                    thumbprint: "YOUR_CERT_THUMBPRINT", // replace with thumbprint obtained during step 2 above
                    privateKey: privateKey
                }
            },
            //... Rest of code in the msalConfig object
        };
    
    module.exports = {
        msalConfig,
        REDIRECT_URI,
        POST_LOGOUT_REDIRECT_URI,
        TENANT_SUBDOMAIN
    };
    ```

    コードで、次のプレースホルダーを置き換えます。

    - `Add_Passphrase_Here` を、秘密キーの暗号化に使用したパス フレーズに置き換えます。
    - `YOUR_CERT_THUMBPRINT` 前に記録した **拇印** の値を使用します。
    - `PATH_TO_YOUR_PRIVATE_KEY_FILE` を、秘密キー ファイルへのファイル パスに置き換えます。
    - `Enter_the_Application_Id_Here` を、前に登録したアプリのアプリケーション (クライアント) ID に置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名がない場合は、 [テナントの詳細を読み取](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)る方法について説明します。

    ここではキーを暗号化 (推奨) したので 、MSAL 構成オブジェクトに渡す前に暗号化を解除する必要があります。

    ```javascript
    //...
    const privateKeyObject = crypto.createPrivateKey({
        key: privateKeySource,
        passphrase: 'Add_Passphrase_Here',
        format: 'pem'
    });
    
    const privateKey = privateKeyObject.export({
        format: 'pem',
        type: 'pkcs8'
    });
    //...
    ```
2. [「Web アプリを実行してテストする」の手順を使用してアプリをテストします。](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-sign-in-sign-out#run-and-test-the-nodeexpressjs-web-app)

### Azure Key Vault から自己署名証明書を直接使用する

次のように、既存の証明書を Azure Key Vault から直接使用できます。

1. `msalConfig` の  など、MSAL 構成オブジェクトが含まれるファイルを見つけて、`clientSecret` プロパティを削除します。

    ```java
    const msalConfig = {
        auth: {
            clientId: process.env.CLIENT_ID || 'Enter_the_Application_Id_Here', // 'Application (client) ID' of app registration in Azure portal - this value is a GUID
            authority: process.env.AUTHORITY || `https://${TENANT_SUBDOMAIN}.ciamlogin.com/`, 
        },
        //...
    };
    ```
2. [Azure CLI を](https://learn.microsoft.com/ja-jp/cli/azure/install-azure-cli)インストールし、コンソールで次のコマンドを入力してサインインします。

    ```console
    az login --tenant YOUR_TENANT_ID
    ```

    プレースホルダー `YOUR_TENANT_ID` を、、前にコピーしたディレクトリ (テナント) ID に置き換えます。
3. コンソールで次のコマンドを入力して、必要なパッケージをインストールします。

    ```console
    npm install --save @azure/identity @azure/keyvault-certificates @azure/keyvault-secrets
    ```
4. クライアント アプリで、次のコードを使用して `thumbprint` と `privateKey` を生成します。

    ```javascript
    const identity = require("@azure/identity");
    const keyvaultCert = require("@azure/keyvault-certificates");
    const keyvaultSecret = require('@azure/keyvault-secrets');
    
    const KV_URL = process.env["KEY_VAULT_URL"] || "ENTER_YOUR_KEY_VAULT_URL"
    const CERTIFICATE_NAME = process.env["CERTIFICATE_NAME"] || "ENTER_THE_NAME_OF_YOUR_CERTIFICATE_ON_KEY_VAULT";
    
    // Initialize Azure SDKs
    const credential = new identity.DefaultAzureCredential();
    const certClient = new keyvaultCert.CertificateClient(KV_URL, credential);
    const secretClient = new keyvaultSecret.SecretClient(KV_URL, credential);
    
    async function getKeyAndThumbprint() {
    
        // Grab the certificate thumbprint
        const certResponse = await certClient.getCertificate(CERTIFICATE_NAME).catch(err => console.log(err));
        const thumbprint = certResponse.properties.x509Thumbprint.toString('hex')
    
        // When you upload a certificate to Key Vault, a secret containing your private key is automatically created
        const secretResponse = await secretClient.getSecret(CERTIFICATE_NAME).catch(err => console.log(err));;
    
        // secretResponse contains both public and private key, but we only need the private key
        const privateKey = secretResponse.value.split('-----BEGIN CERTIFICATE-----\n')[0]
    }
    
    getKeyAndThumbprint();        
    ```

    コードで、次のプレースホルダーを置き換えます。

    - `ENTER_YOUR_KEY_VAULT_URL` を実際の Azure Key Vault の URL に置き換えます。
    - `ENTER_THE_NAME_OF_YOUR_CERTIFICATE_ON_KEY_VAULT` を、Azure Key Vault 内の証明書の名前に置き換えます。
5. `thumbprint` と `privateKey` の値を使用して構成を更新します。

    ```javascript
    let clientCert = {
        thumbprint: thumbprint, 
        privateKey: privateKey,
    };
    
    msalConfig.auth.clientCertificate = clientCert; //For this to work, you can't declares your msalConfig using const modifier 
    ```
6. 次に、`getMsalInstance` メソッドに示すように、機密クライアントのインスタンス化に進みます。

    ```javascript
    class AuthProvider {
        //...
        getMsalInstance(msalConfig) {
            return new msal.ConfidentialClientApplication(msalConfig);
        }
        //...
    }
    ```
7. [「Web アプリを実行してテストする」の手順を使用してアプリをテストします。](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-sign-in-sign-out#run-and-test-the-nodeexpressjs-web-app)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/how-to-web-app-role-based-access-control"} -->
## Node.js Web アプリでロールベースのアクセス制御を使用する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-web-app-role-based-access-control
- Service: identity-platform
- Article date: 2025-03-16
- Summary: Node.js アプリケーションのセキュリティ トークンで要求として受け取ることができるように、外部テナントでグループとユーザー ロールを構成する方法について説明します

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

ロールベースのアクセス制御 (RBAC) は、アプリケーションにおいて認可を実施するメカニズムです。 Microsoft Entra External ID を使用すると、アプリケーションのアプリケーション ロールを定義し、それらのロールをユーザーとグループに割り当てることができます。 ユーザーまたはグループに割り当てたロールによって、アプリケーション内のリソースと操作へのアクセスのレベルが定義されます。 認証されたユーザーのセキュリティ トークンを発行する外部 ID には、ユーザーまたはグループに割り当てたロールの名前がセキュリティ トークンのロール要求に含まれます。

ユーザーのグループ メンバーシップを返すように外部テナントを構成することもできます。 その後、開発者はアプリケーションに RBAC を実装するためにセキュリティ グループを使用できます。この場合、特定グループ内でのユーザーのメンバーシップが、ロール メンバーシップとして解釈されます。

ユーザーとグループをロールに割り当てると、"*ロール*" 要求がセキュリティ トークンに出力されます。 ただし、セキュリティ トークンで *グループ* メンバーシップ要求を出力するには、外部テナントに追加の構成が必要です。

この記事では、Node.js Web アプリのセキュリティ トークンで、ユーザー ロールまたはグループ メンバーシップ、あるいはその両方を要求として受け取る方法について説明します。

### [前提条件]

- まだ行っていない場合は、「[アプリケーションのロールベースのアクセス制御の使用](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-use-app-roles-customers)」の手順を実行します。 この記事では、アプリケーションのロールを作成する方法、それらのロールにユーザーとグループを割り当てる方法、グループにメンバーを追加する方法、セキュリティ トークンにグループ要求を追加する方法について説明します。 [ID トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)と[アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)の詳細を確認してください。
- まだ行っていない場合は、「[独自の Node.js Web アプリケーションでのユーザーのサインイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-node-sign-in-prepare-app)」の手順を実行します

### Node.js Web アプリでグループとロールの要求を受信する

外部テナントを構成したら、クライアント アプリで *ロール* と *グループ* の要求を取得できます。 "*ロール*" と "*グループ*" の要求はどちらも ID トークンとアクセス トークンに存在しますが、クライアント アプリでは ID トークンでこれらの要求をチェックするだけで、クライアント側で認可を実装できます。 API アプリでは、アクセス トークンを受け取ったときにこれらの要求を取得することもできます。

次のコード スニペットの例に示すように、"*ロール*" の要求値をチェックします。

```javascript
const msal = require('@azure/msal-node');
const { msalConfig, TENANT_SUBDOMAIN, REDIRECT_URI, POST_LOGOUT_REDIRECT_URI } = require('../authConfig');

...
class AuthProvider {
...
    async handleRedirect(req, res, next) {
        const authCodeRequest = {
            ...req.session.authCodeRequest,
            code: req.body.code, // authZ code
            codeVerifier: req.session.pkceCodes.verifier, // PKCE Code Verifier
        };
    
        try {
            const msalInstance = this.getMsalInstance(this.config.msalConfig);
            const tokenResponse = await msalInstance.acquireTokenByCode(authCodeRequest, req.body);
            let roles = tokenResponse.idTokenClaims.roles;
        
            //Check roles
            if (roles && roles.includes("Orders.Manager")) {
                //This user can view the ID token claims page.
                res.redirect('/id');
            }
            
            //User can only view the index page.
            res.redirect('/');
        } catch (error) {
            next(error);
        }
    }
...
}

```

複数のロールにユーザーを割り当てる場合、`roles` 文字列には、`Orders.Manager,Store.Manager,...` のように、すべてのロールがコンマで区切られて含まれています。 次の条件を処理するようにアプリケーションをビルドしてください。

- トークンに `roles` 要求がない
- ユーザーがロールに割り当てられていない
- ユーザーを複数のロールに割り当てる場合、`roles` クレームに複数の値が含まれることがあります。

次のコード スニペットの例に示すように、"*グループ*" の要求値をチェックすることもできます。

```javascript
const tokenResponse = await msalInstance.acquireTokenByCode(authCodeRequest, req.body);
let groups = tokenResponse.idTokenClaims.groups;
```

グループの要求値は、グループの *objectId* です。 ユーザーが複数のグループのメンバーである場合、`groups` 文字列には、`7f0621bc-b758-44fa-a2c6-...,6b35e65d-f3c8-4c6e-9538-...` のように、すべてのグループがコンマで区切られて含まれています。

注

ユーザーに [Microsoft Entra の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)または一般にディレクトリ ロールと呼ばれるロールを割り当てると、それらのロールはセキュリティ トークンの *グループ* 要求に表示されます。

### グループの超過分の処理

セキュリティ トークンのサイズが HTTP ヘッダー のサイズ制限を超えないようにするために、外部 ID によって *、グループ* 要求に含まれるオブジェクト ID の数が制限されます。 超過制限は、**SAML トークンの場合は 150、JWT トークンの場合は 200 です**。 ユーザーが多数のグループに属していて、すべてのグループに対して要求する場合は、この制限を超える可能性があります。

#### ソース コードでグループの超過分を検出する

グループの超過分を回避できない場合は、コードで処理する必要があります。 超過制限を超えた場合、トークンには "*グループ*" 要求は含まれません。 代わりに、トークンには、配列の "*グループ*" メンバーを含む *\_claim\_names* 要求が含まれます。 それで、超過が発生したことを確認するために、*\_claim\_names* クレームの存在をチェックする必要があります。 次のコード スニペットでは、グループの超過分を検出する方法を示しています。

```javascript
const tokenResponse = await msalInstance.acquireTokenByCode(authCodeRequest, req.body);

if(tokenResponse.idTokenClaims.hasOwnProperty('_claim_names') && tokenResponse.idTokenClaims['_claim_names'].hasOwnProperty('groups')) {
    //overage has occurred
}
```

「[トークンでのグループ要求とアプリ ロールの構成](https://learn.microsoft.com/ja-jp/security/zero-trust/develop/configure-tokens-group-claims-app-roles#group-overages)」の記事の手順を使用して、グループの超過分が発生した場合にグループ全体の一覧に対する要求を確認します。

### Node.js Web アプリでグループとロールの値を使用する方法

クライアント アプリでは、サインインしているユーザーが保護されたルートにアクセスしたり、API エンドポイントを呼び出したりするために必要なロールを持っているかどうかを確認できます。 これを行うには、ID トークンで `roles` 要求を確認します。 この保護をアプリに実装するには、カスタム ミドルウェアを使用してガードを構築します。

サービス アプリ (API アプリ) では、API エンドポイントを保護することもできます。 クライアント アプリによって送信された[アクセス トークンを検証](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens#validate-tokens)した後、アクセス トークンのペイロード要求の "*ロール*" または "*グループ*" 要求をチェックできます。

### アプリ ロールまたはグループを使用しますか?

この記事では、"*アプリ ロール*" または "*グループ*" を使用して、アプリケーションに RBAC を実装できることを学習しました。 アプリケーション レベルでアクセス/アクセス許可を管理する際に細かい制御を提供するため、アプリ ロールを使用することをお勧めします。 アプローチの選択方法について詳しくは、「[アプローチの選択](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-rbac-for-developers#choose-an-approach)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-add-app-roles-in-apps"} -->
## アプリ ロールを追加してトークンから取得する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps
- Service: identity-platform
- Article date: 2026-09-25
- Summary: Microsoft Entra ID に登録されたアプリケーションにアプリ ロールを追加する方法について説明します。 それらのロールにユーザーとグループを割り当て、それらをトークンの "roles" 要求内で受け取ります。

ロールベースのアクセス制御 (RBAC) は、アプリケーションにおいて承認を実施する一般的なメカニズムです。 RBAC を使用すると、管理者は特定のユーザーまたはグループに対してではなく、ロールに対してアクセス許可を付与できます。 その後、管理者はロールをさまざまなユーザーやグループに割り当てて、コンテンツや機能にだれがアクセスできるかを制御できます。

RBAC をアプリケーション ロールおよびロール要求と一緒に使用すると、開発者はあまり手間をかけずにアプリでの承認を確実に行うことができます。

もう 1 つの方法は、GitHub の [active-directory-aspnetcore-webapp-openidconnect-v2](https://aka.ms/groupssample) コード サンプルに示されているように、Microsoft Entra グループとグループ要求を使用することです。 Microsoft Entra グループとアプリケーション ロールは相互に排他的ではありません。一緒に使うことで、さらにきめ細かいアクセス制御を提供できます。

### アプリケーションのロールを宣言する

アプリの役割は、アプリ[登録プロセス](https://entra.microsoft.com)中に [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)を使用して定義します。 アプリ ロールは、サービス、アプリ、または API を表すアプリケーションの登録で定義します。 ユーザーがアプリケーションにサインインすると、Microsoft Entra ID は、ユーザーまたはサービス プリンシパルが付与された各ロールに対して `roles` 要求を出力します。これは、 [要求ベースの承認](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-validation)を実装するために使用できます。 アプリ ロールは、 [ユーザーまたはユーザーのグループに](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)割り当てることができます。 アプリ ロールは、別のアプリケーションのサービス プリンシパルまたは [マネージド ID のサービス プリンシパルに](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-assign-app-role-managed-identity)割り当てることもできます。

現時点、サービス プリンシパルをグループに追加してから、そのグループにアプリ ロールを割り当てる場合、Microsoft Entra ID では、そこで発行されるトークンに `roles` 要求は追加されません。

アプリ ロールは、[Microsoft Entra 管理センター] のアプリ ロール UI を使用して宣言されます。

アプリ ロールには、公開されている委任されたアクセス許可スコープと共有される、アプリケーションまたはサービス プリンシパルごとに既定の制限である 700 のアクセス許可定義が適用されます。 また、別途の 1,200 エントリのアプリケーション マニフェスト制限にも算入されます。 既存のオブジェクトのカウント ルールとガイダンスについては、「 アプリ ロールの制限」を参照してください。

#### アプリ ロール UI

[Microsoft Entra 管理センター] のユーザー インターフェイスを使用してアプリ ロールを作成するには次の手順に従ってください:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニューの **[設定]** アイコン  を使用して、[ **ディレクトリとサブスクリプション** ] メニューからアプリの登録を含むテナントに切り替えます。
3. **Entra ID**&gt;**App 登録**に移動し、アプリ ロールを定義するアプリケーションを選択します。
4. [管理] で [ **アプリ ロール**] を選択し、[ **アプリ ロールの作成**] を選択します。

    [Image: Azure portal のアプリ登録の [アプリ ロール] ウィンドウ]
5. [ **アプリ ロールの作成** ] ウィンドウで、ロールの設定を入力します。 図の下の表では、各設定とそのパラメーターについて説明します。

    [Image: Azure portal の [アプリの登録] のアプリ ロールによって作成されるコンテキスト ペイン]

    | フィールド | 説明 | 例 |
    | --- | --- | --- |
    | **表示名** | 管理者の同意やアプリの割り当て時に表示されるアプリのロールの表示名です。 この値にはスペースを含めることができます。 | `Survey Writer` |
    | **許可されるメンバー型** | このアプリのロールをユーザー、アプリケーション、またはその両方に割り当てることができるかどうかを指定します。で使用可能な場合、アプリのロールは、アプリ登録の 管理 セクションにある [API アクセス許可]  [アクセス許可の追加  ] [My APIs ] [API 選択 ] [アプリケーションのアクセス許可] として表示されます。 | `Users/Groups` |
    | **価値** | アプリケーション側でトークンに想定するロール要求の値を指定します。 この値は、アプリケーションのコードで参照される文字列と正確に一致する必要があります。 値にスペースを含めることはできません。 | `Survey.Create` |
    | **説明** | 管理者のアプリの割り当てと同意エクスペリエンスの間に表示されるアプリのロールの詳細な説明。 | `Writers can create surveys.` |
    | **このアプリ ロールを有効にしますか?** | アプリ ロールを有効にするかどうかを指定します。 アプリのロールを削除するには、このチェックボックスをオフにして、変更を適用してから削除操作を試行してください。 この設定は、アプリ ロールの使用状況と可用性を制御します。アプリ ロールは完全に削除するのではなく、一時的または永続的に無効にできます。 | *チェック* |
6. [ **適用]** を選択して変更を保存します。

アプリ ロールが **[有効]** に設定されている場合、割り当てられているユーザー、アプリケーション、またはグループには、トークンにアプリ ロールが含まれます。 これらのトークンは、アプリで API を呼び出す場合はアクセス トークンであり、アプリがユーザーのサインインを行う場合は ID トークンになります。

アプリ ロールが **[無効]** に設定されると、非アクティブになり、割り当てできなくなります。 ただし、ユーザー、グループ、アプリケーションへの現在のアプリ ロールの割り当ては残り、アプリ ロールは引き続きトークンを渡します。 ユーザー、グループ、またはアプリケーションからアプリ ロールを削除して、アプリ ロールもトークンから削除されるようにします。

### アプリ ロールの制限

Microsoft Entra IDでは、各[アプリケーション](https://learn.microsoft.com/ja-jp/graph/api/resources/application)と[サービス プリンシパル](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal)に既定の制限である 700 個のアクセス許可定義が適用されます。 アプリ ロール (`appRoles`) と公開された委任されたアクセス許可スコープ (アプリケーションで`api.oauth2PermissionScopes` 、またはサービス プリンシパルの `oauth2PermissionScopes` ) は、基になる同じ `Entitlement` コレクションに格納されるため、この制限を共有します。

次のカウントルールが適用されます。

- この制限は、ロールに割り当てられているユーザー、グループ、またはアプリケーションではなく、アクセス許可の定義をカウントします。 アプリ ロールの割り当てには [、個別の制限があります](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions)。
- 有効な定義と無効な定義の両方がカウントされます。 `isEnabled`を `false` に設定しても、容量は解放されません。
- 組み合わされたロールとスコープ コレクション内の各個別のアクセス許可 ID は 1 回カウントされます。 ID を共有し、一致する共有プロパティを持つロールとスコープは、1 つの定義として格納されます。
- カウントは、要求に追加されたエントリだけでなく、各オブジェクトの結果のコレクションに適用されます。 サービス プリンシパルには、アプリケーションから継承された定義と、サービス プリンシパルに直接追加された定義が含まれます。

たとえば、個別の ID を持つ 650 個のアプリ ロールと 50 個の公開された委任されたアクセス許可スコープでは、700 個のエントリがすべて使用されます。 個別の [1,200 エントリアプリケーション マニフェストの制限](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-microsoft-graph-app-manifest#manifest-limits) では、この制限を超えることはできません。

#### 制限を超えている既存のオブジェクト

既存のオブジェクトには、700 を超える権限定義を含めることができます。 カウント チェックでは、結果が該当する制限を超えた場合でも、定義の数を変更せずに保持したり、減らしたりする更新が許可されます。 その他の検証規則は引き続き適用されます。 該当する制限を超えてカウントを増やす更新プログラムは拒否されます。

既存のオブジェクトの中には、サービス割り当て制限が高いものもあります。 1 つのオブジェクトの上限が別のオブジェクトまたはテナントに適用されると想定しないでください。 アプリケーション マニフェストを使用してこの制限を構成することはできません。

700 値の制限によって更新が拒否されると、エラーは `appRoles`ではなく、基になるプロパティを識別できます。

```text
The total count of values: 701 exceeds the set maxValuesCount limit: 700 for property: Entitlement
```

#### 制限内での設計

すべてのリソース、顧客、または個々のアクションのロールを定義するのではなく、 `Reader`、 `Writer`、 `Administrator`などの安定した承認カテゴリにアプリ ロールを使用します。 アプリケーションの承認データにリソースのアクセス許可を細かく保持し、アプリケーションで適用します。

古いロールとスコープを削除して容量を解放します。 アプリ ロールを削除する前に、そのロールを無効にし、その割り当てと、それに依存するアプリケーションの動作を確認します。 既存のアプリ ロールにグループを割り当てると、割り当ての管理が簡素化されますが、保存できるロール定義の数は増えません。

### アプリケーションの所有者を割り当てる

アプリケーションにアプリ ロールを割り当てる前に、自分をアプリケーション所有者として割り当てる必要があります。

1. アプリの登録で、[ **管理**] で [ **所有者**] を選択し、[ **所有者の追加]** を選択します。
2. 新しいウィンドウで、アプリケーションに割り当てる所有者を見つけて選びます。 選択した所有者が右側のパネルに表示されます。 完了したら、[ **選択** ] で確認し、アプリの所有者が所有者の一覧に表示されます。

Note

必ず、API アプリケーションと、アクセス許可を追加するアプリケーションの両方に所有者を設定してください。そうしなければ、API のアクセス許可を要求するときに API が一覧表示されません。

### アプリケーションへのアプリ ロールの割り当て

アプリケーションにアプリ ロールを追加した後は、Microsoft Entra 管理センターを使用するか、Microsoft [Graph](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-post-approleassignments?tabs=http) を使用してプログラムでクライアント アプリにアプリ ロールを割り当てることができます。 アプリケーションへのアプリ ロールの割り当ては、 [ユーザーへのロールの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)と混同しないでください。

アプリケーションにアプリ ロールを割り当てると、 *アプリケーションのアクセス許可*が作成されます。 通常、アプリケーションのアクセス許可は、認証および承認された API 呼び出しをユーザーによる操作なしで行う必要がある、デーモン アプリまたはバックエンド サービスによって使用されます。

[Microsoft Entra 管理センター] を使用して、アプリケーションにアプリロールを割り当てるには、次の手順に従ってください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**App 登録**に移動し、[**すべてのアプリケーション**] を選択します。
3. **[すべてのアプリケーション**] を選択して、すべてのアプリケーションの一覧を表示します。 アプリケーションが一覧に表示されない場合は、[ **すべてのアプリケーション** ] リストの上部にあるフィルターを使用して一覧を制限するか、一覧を下にスクロールしてアプリケーションを見つけます。
4. アプリ ロールを割り当てるアプリケーションを選択します。
5. **[API のアクセス許可**] を選択&gt;**アクセス許可を追加します**。
6. [ **マイ API** ] タブを選択し、アプリ ロールを定義したアプリを選択します。
7. [ **アクセス許可**] で、割り当てるロールを選択します。
8. [ **アクセス許可の追加]** ボタンを選択して、ロールの追加を完了します。

新しく追加されたロールは、アプリ登録の **API アクセス許可** ウィンドウに表示されます。

#### 管理者の同意の付与

これらは委任された *アクセス許可ではなくアプリケーションのアクセス許可*であるため、管理者はアプリケーションに割り当てられたアプリ ロールを使用するための同意を付与する必要があります。

1. アプリ登録の **[API アクセス許可**] ウィンドウで、[&lt;**テナント名に管理者の同意を付与する&gt;**を選択します。
2. 要求されたアクセス許可の同意を付与するように求められたら、[ **はい** ] を選択します。

**[状態]** 列には、&lt;&gt;同意が付与が反映されている必要があります。

### アプリ ロールの使用シナリオ

アプリケーション シナリオでユーザーをサインインさせるアプリ ロール ビジネス ロジックを実装する場合は、まずアプリ **の登録**でアプリ ロールを定義します。 次に、管理者は、[ **エンタープライズ アプリケーション** ] ウィンドウでユーザーとグループに割り当てます。 シナリオに応じて、これらの割り当てられたアプリ ロールは、アプリケーションに対して発行されるさまざまなトークンに含められます。 たとえば、ユーザーをサインインさせるアプリの場合は、ロール要求が ID トークンに含められます。 アプリケーションが API を呼び出すと、ロール要求がアクセス トークンに含められます。

アプリ呼び出し API のシナリオでアプリ ロールのビジネス ロジックを実装している場合は、2 つのアプリ登録があります。 1 番目のアプリ登録はアプリ用であり、2 番目のアプリ登録は API 用です。 この場合は、アプリ ロールを定義して、API のアプリ登録でユーザーまたはグループに割り当てます。 ユーザーがアプリを使用して認証し、アクセス トークンを要求して API が呼び出されると、ロール要求がトークンに含められます。 次の手順は、API が呼び出されたときにこれらのロールを確認するコードを Web API に追加することです。

Web API に承認を追加する方法については、「 [保護された Web API: スコープとアプリ ロールを確認する](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-protected-web-api-verification-scope-app-roles)」を参照してください。

### アプリ ロールとグループ

承認にはアプリ ロールまたはグループを使用できますが、両者の間の重要な違いは、実際のシナリオでどちらを使用するかの決定に影響する可能性があります。

| アプリケーションの役割 | グループ |
| --- | --- |
| アプリケーションに固有のものであり、アプリの登録で定義されます。 これらはアプリケーションと共に移動します。 | これらは、アプリケーション固有ではなく、Microsoft Entra テナント固有です。 |
| アプリ ロールは、アプリの登録が削除されると削除されます。 | アプリが削除されても、グループはそのまま残ります。 |
| `roles` 要求で提供されます。 | `groups` 要求で提供されます。 |

開発者はアプリ ロールを使用して、ユーザーがアプリにサインインできるか、Web API のアクセス トークンをアプリで取得できるかを制御できます。 このセキュリティ制御をグループにまで拡張するために、開発者と管理者は、セキュリティ グループをアプリ ロールに割り当てることもできます。

開発者がアプリ自体に承認のパラメーターを記述して制御する必要がある場合は、アプリ ロールの使用が好まれます。 たとえば、承認にグループを使用するアプリは、グループ ID と名前の両方が異なる可能性があるため、次のテナントで中断されます。 アプリ ロールを使用するアプリは安全なままです。 実際、SaaS アプリが複数のテナントにプロビジョニングされることが許可されるため、同じ理由で、SaaS アプリではアプリ ロールにグループがよく割り当てられます。

### ユーザーとグループを Microsoft Entra のロールに割り当てる

アプリケーションにアプリ ロールを追加したら、ユーザーとグループを [Microsoft Entra ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に割り当てることができます。 ユーザーとグループをロールに割り当てるには、ポータルの UI を使用するか、 [Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/api/user-post-approleassignments) を使用してプログラムを使用します。 さまざまなロールに割り当てられたユーザーがアプリケーションにサインインすると、割り当てられたロールが `roles` 要求でトークンに付与されます。

[Microsoft Entra 管理センター] を使用して、ユーザーとグループをロールに割り当てるには、次の手順に従ってください。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニューの **[設定]** アイコン  を使用して、[ **ディレクトリとサブスクリプション** ] メニューからアプリの登録を含むテナントに切り替えます。
3. **Entra ID**&gt;**エンタープライズ アプリケーション**を確認します。
4. **[すべてのアプリケーション**] を選択して、すべてのアプリケーションの一覧を表示します。 アプリケーションが一覧に表示されない場合は、[ **すべてのアプリケーション** ] リストの上部にあるフィルターを使用して一覧を制限するか、一覧を下にスクロールしてアプリケーションを見つけます。
5. ユーザーまたはグループをロールに割り当てるアプリケーションを選択します。
6. [ **管理**] で、[ **ユーザーとグループ**] を選択します。
7. [ **ユーザーの追加]** を選択して **、[割り当ての追加]** ウィンドウを開きます。
8. [**割り当ての追加**] ウィンドウから [**ユーザーとグループ**] セレクターを選択します。 ユーザーとセキュリティ グループの一覧が表示されます。 特定のユーザーまたはグループを検索することや、一覧に表示される複数のユーザーやグループを選択することができます。 [選択] ボタンを **選択** して続行します。
9. [**割り当ての追加**] ウィンドウで [**ロールの選択**] を選択します。 アプリケーションに対して定義されているすべてのロールが表示されます。
10. ロールを選択し、[選択] ボタンを **選択** します。
11. [ **割り当て** ] ボタンを選択して、アプリへのユーザーとグループの割り当てを完了します。

追加したユーザーとグループが [ **ユーザーとグループ** ] の一覧に表示されることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-add-branding-in-apps"} -->
## Microsoft のブランド化のガイドラインでのサインイン - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-branding-in-apps
- Service: identity-platform
- Article date: 2023-12-15
- Summary: Microsoft ID プラットフォームのアプリケーションのブランド化ガイドラインについて説明します。

Microsoft ID プラットフォームを使用してアプリケーションを開発する場合は、(Microsoft Entra ID で管理されている) 職場や学校のアカウントまたは個人のアカウントをサインアップやサインインに使いたいと考えているアプリケーションの利用者に案内する必要があります。

この記事では、次のことについて説明します。

- Microsoft によって管理される 2 種類のユーザー アカウントと、アプリケーションで Microsoft Entra アカウントを参照する方法の詳細
- アプリで Microsoft ロゴを使用するための要件について説明します。
- アプリで使用する公式の**サインイン**または**Microsoft アカウントでサインイン**のイメージのダウンロード
- ブランド化とナビゲーションの注意事項の詳細

### Microsoft の個人アカウントと職場または学校アカウント

Microsoft は次の 2 種類のユーザー アカウントを管理しています。

- **個人アカウント** (以前の Windows Live ID): このアカウントは、"*個々の*" ユーザーと Microsoft の関係を表し、コンシューマー向けデバイスや Microsoft のサービスにアクセスする際に使用されます。 このアカウントは個人的に使用するためのものです。
- **職場または学校アカウント:** このアカウントは、Microsoft Entra ID を使う組織に代わって Microsoft が管理しています。 このアカウントは、Microsoft 365 や Microsoft の他のビジネス サービスにサインインする際に使用されます。

通常、Microsoft の職場または学校アカウントは、組織 (企業、学校、政府機関) がエンド ユーザー (従業員、学生、公務員) に割り当てます。 このアカウントは、Azure AD プラットフォームのクラウド (Microsoft Entra ID) で直接管理されるか、Windows Server Active Directory などのオンプレミス ディレクトリから Microsoft Entra ID に同期されます。 職場または学校アカウントの " *管理人* " は Microsoft ですが、アカウントは組織が所有し、管理しています。

### アプリケーションで Microsoft Entra アカウントを参照する

Microsoft は、Azure または Active Directory のブランド名をエンド ユーザーに表示していません。開発者もこれらを表示しないようにする必要があります。

- ユーザーがサインインしたら、できるだけ組織の名前とロゴを使用します。 "組織" のような総称を使用するのではなく、この方法をお勧めします。
- ユーザーがサインインしていないときは、アカウントを "職場または学校アカウント" と呼び、Microsoft のロゴを使用して、Microsoft がこれらのアカウントを管理していることを伝えます。 "企業アカウント"、"ビジネス アカウント"、"会社アカウント" などの言葉はユーザーの混乱を招くので使用しないでください。

### ユーザー アカウントのピクトグラム

以前のバージョンのガイドラインでは、"ブルー バッジ" のピクトグラムを使用することを推奨していました。 ユーザーと開発者のフィードバックに基づき、現在は代わりに Microsoft のロゴを使用することを推奨しています。 Microsoft のロゴを使用することにより、ユーザーは、アプリケーションにサインインするときに、Microsoft 365 や他の Microsoft ビジネス サービスで使用しているアカウントを再利用できることを理解しやすくなります。

### Microsoft Entra ID を使ったサインアップとサインイン

アプリケーションでは、サインアップとサインインに別々のパスを示すことがあります。以下のセクションでは、この 2 つのシナリオの表示に関するガイダンスを示します。

**アプリがエンド ユーザーのサインアップをサポートしている場合 (無料試用版やフリーミアム モデルなど)** : ユーザーが、職場アカウントまたは個人のアカウントを使用してアプリにアクセスできるようにするための**サインイン** ボタンを表示できます。 Microsoft Entra ID は、ユーザーがアプリケーションに初めてアクセスしたときに同意プロンプトを表示します。

**アプリに管理者だけが同意できるアクセス許可が必要な場合、またはアプリに組織のライセンスが必要な場合**: 管理者による取得をユーザー サインインから分離します。 **"このアプリケーションを入手" ボタン** で管理者をサインインにリダイレクトし、組織のユーザーに代わって同意するよう求めることにより、エンドユーザーにアプリケーションで同意画面が表示されないという追加のメリットもあります。

### アプリケーションの取得の表示に関するガイダンス

"アプリケーションの入手" リンクでは、ユーザーを Microsoft Entra ID のアクセス権の付与 (承認) ページにリダイレクトする必要があります。こうすることで、組織の管理者は、Microsoft がホストする組織のデータへのアクセス権をアプリケーションに付与できます。 アクセス権の要求方法の詳細については、[Microsoft Entra ID へのアプリケーションの統合](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)に関する記事を参照してください。

管理者は、アプリケーションに同意したら、ユーザーの Microsoft 365 アプリ起動ツール (ワッフルおよび https://www.office.com/からアクセス可能) にアプリケーションを追加することを選択できます。 この機能を公表する場合は、"このアプリケーションを組織に追加" のような言葉を使って、次のようなボタンを表示できます。

[Image: Microsoft ロゴと]

ただし、ボタンに頼るのではなく、説明文を作成することをお勧めします。 次に例を示します。

>
> *「Microsoft 365 や Microsoft の他のビジネス サービスを既にお使いの場合は、組織のデータへのアクセス権を &lt;your\_app\_name&gt; に付与できます。 これにより、ユーザーは既存の職場アカウントを使用して &lt;your\_app\_name&gt; にアクセスできるようになります。」*

公式の Microsoft ロゴをダウンロードしてアプリで使用するためには、使用するロゴを右クリックして、コンピューターに保存します。

| 資産 | PNG 形式 | SVG 形式 |
| --- | --- | --- |
| Microsoft のロゴ | [Image: PNG 形式のダウンロード可能な Microsoft ロゴ] | [Image: SVG 形式のダウンロード可能な Microsoft ロゴ] |

### サインインの表示に関するガイダンス

アプリケーションでは、Microsoft Entra ID との統合に使うプロトコルに対応したサインイン エンドポイントにユーザーをリダイレクトするサインイン ボタンを表示する必要があります。 次のセクションでは、ボタンの外観について詳しく説明します。

#### ピクトグラムと "Microsoft でサインイン"

これは、Microsoft のロゴと "Microsoft でサインイン" という言葉とを関連付けたものであり、アプリケーションがサポートしている可能性があるさまざまな ID プロバイダーの中で Microsoft Entra ID を一意に表します。 スペースが狭く "Microsoft でサインイン" という文字列が収まりきらない場合は、単に "サインイン" としても問題ありません。 ボタンには、薄い色調または濃い色調を使用できます。

次の図は、アプリで資産を使用する場合に Microsoft が推奨する赤線です。 赤線は "Microsoft アカウントでサインイン" や短縮バージョンの "サインイン" に適用します。

公式の画像をダウンロードしてアプリで使用するためには、使用する画像を右クリックして、コンピューターに保存します。

| 資産 | PNG 形式 | SVG 形式 |
| --- | --- | --- |
| Microsoft アカウントでサインイン (濃い色調) | [Image: ダウンロード可能な濃い色調の] | [Image: ダウンロード可能な濃い色調の] |
| Microsoft アカウントでサインイン (薄い色調) | [Image: ダウンロード可能な薄い色調の] | [Image: ダウンロード可能な薄い色調の] |
| サインイン (濃い色調) | [Image: ダウンロード可能な濃い色調の] | [Image: ダウンロード可能な濃い色調の] |
| サインイン (薄い色調) | [Image: ダウンロード可能な薄い色調の] | [Image: ダウンロード可能な薄い色調の] |

### ローカライズされた用語と UI 文字列

Microsoft Terminologyを使用すると、ローカライズされたバージョンのアプリケーションの用語が Microsoft 製品の対応する用語と一致することを確認できます。 [Microsoft Terminology 検索ページ](https://msit.powerbi.com/view?r=eyJrIjoiODJmYjU4Y2YtM2M0ZC00YzYxLWE1YTktNzFjYmYxNTAxNjQ0IiwidCI6IjcyZjk4OGJmLTg2ZjEtNDFhZi05MWFiLTJkN2NkMDExZGI0NyIsImMiOjV9) を使用して 、Microsoft Terminology に対してクエリを実行できます。

Microsoft UI 文字列の翻訳を使用して、ローカライズされたバージョンのアプリケーションの翻訳が Microsoft 製品の対応する UI 文字列と一致することを確認できます。 [Microsoft UI 文字列検索ページ](https://msit.powerbi.com/view?r=eyJrIjoiMmE2NjJhMDMtNTY3MC00MmI2LWFmOWUtYWM5YTVjODI5MjQwIiwidCI6IjcyZjk4OGJmLTg2ZjEtNDFhZi05MWFiLTJkN2NkMDExZGI0NyIsImMiOjV9) を使用して 、Microsoft UI 文字列に対してクエリを実行できます。

### ブランド化に関する注意事項

"職場または学校アカウント" は、"Microsoft でサインイン" ボタンと組み合わせて**使用してください**。補足的な説明を与えることで、その使用の可否をエンド ユーザーが認識しやすいようにします。 "企業アカウント"、"ビジネス アカウント"、"会社アカウント" などの言葉は**使用しないでください**。

"Microsoft 365 ID" または "Azure ID" は**使用しないでください**。 Microsoft 365 は、Microsoft Entra ID を認証に使用しない Microsoft のコンシューマー向け製品の名前でもあります。

Microsoft のロゴを変更 **しない** でください。

Azure または Active Directory のブランドをエンド ユーザーに表示**しない**でください。 ただし、開発者、IT プロフェッショナル、管理者に対しては、これらの用語を使用してもかまいません。

### ナビゲーションに関する注意事項

ユーザーがサインアウトし、別のユーザー アカウントに切り替える方法を提供してく**ださい**。 ほとんどのユーザーは Microsoft/Facebook/Google/X の個人用アカウントを 1 つ持っていますが、多くの場合、ユーザーは 1 つ以上の組織に関与しています。 間もなく、複数のサインイン ユーザーがサポートされるようになります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-add-terms-of-service-privacy-statement"} -->
## アプリのサービス利用規約とプライバシーに関する声明 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-terms-of-service-privacy-statement
- Service: identity-platform
- Article date: 2023-12-15
- Summary: Microsoft Entra ID を使用するために登録されているアプリに対して、サービス使用条件とプライバシーに関する声明を構成する方法について学習します。

Microsoft Entra ID および Microsoft アカウントと統合されているマルチテナント アプリを構築して管理する開発者は、アプリのサービス使用条件とプライバシーに関する声明へのリンクを含める必要があります。 サービス利用規約とプライバシーに関する声明は、ユーザーの同意エクスペリエンスからユーザーに提示されます。 これは、ユーザーがアプリを信頼できることを知るのに役立ちます。 サービス利用規約とプライバシーに関する声明は、ユーザー向けマルチテナント アプリに特に重要です。アプリは複数のディレクトリによって使用され、すべての Microsoft アカウントで利用できます。

お客様は自分のアプリのサービス利用規約とプライバシーに関する声明ドキュメントを作成し、これらのドキュメントへの URL を提供する責任があります。 これらのリンクを提供できないマルチテナント アプリの場合、アプリに対するユーザーの同意エクスペリエンスで、ユーザーがアプリに同意することを防ぐためのアラートが表示されます。

注

- シングルテナント アプリの場合、サービス使用条件とプライバシー ステートメントのリンクは該当しません。
- この 2 つのリンクの一方または両方が存在しない場合は、アプリにアラートが表示されます。

### ユーザーの同意エクスペリエンス

次の例では、サービス利用規約とプライバシーに関する声明を設定し、これらのリンクを設定していないときに、マルチテナント アプリのユーザーの同意エクスペリエンスが表示されます。

[Image: 提供されているプライバシーに関する声明とサービス条件の有無に関するスクリーンショット]

### サービス利用規約とプライバシーに関する声明のドキュメントへのリンクを書式設定する

自分のアプリのサービス利用規約とプライバシーに関する声明のドキュメントへのリンクを追加する前に、URL が以下のガイドラインに従っていることを確認します。

| ガイドライン | 説明 |
| --- | --- |
| フォーマット | 有効な URL |
| 有効なスキーマ | HTTP および HTTPSHTTPS を推奨 |
| 最大長 | 2048 文字 |

例: `https://myapp.com/terms-of-service`、`https://myapp.com/privacy-statement`

### サービス利用規約とプライバシーに関する声明へのリンクを追加する

サービス利用規約とプライバシーに関する声明の準備ができたら、次のメソッドのいずれかを使用して、自分のアプリにこれらのドキュメントへのリンクを追加できます。

- Microsoft Entra 管理センターを通じて
- アプリ オブジェクト JSON の使用
- Microsoft Graph API の使用

#### Microsoft Entra 管理センターの使用

リンクを追加するには、次の手順に従います:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション開発者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)としてサインインします。
2. **Entra ID**&gt;**Custom Branding** に移動します。
3. [**はじめに**] を選択し、[**既定のサインイン エクスペリエンス**] で **[編集]** を選択します。
4. **[フッター**] を選択し、[**利用規約**と**プライバシー] > [Cookie]** の URL を入力します。
5. [ **確認と保存]** を選択します。

#### アプリ オブジェクト JSON を使用する

アプリ オブジェクト JSON を直接変更する場合、マニフェスト エディターを使用して、自分のアプリのサービス利用規約とプライバシーに関する声明へのリンクを含めることができます。

1. [ **アプリの登録** ] セクションに移動し、アプリを選択します。
2. **[マニフェスト**] ウィンドウを開きます。
3. Ctrl + F キーを押し、"informationalUrls" を検索します。 情報を入力します。
4. アプリ マニフェストをダウンロードして変更し、アップロードして、変更を保存します。

```json
    "informationalUrls": { 
        "termsOfService": "<your_terms_of_service_url>", 
        "privacy": "<your_privacy_statement_url>" 
    }
```

#### Microsoft Graph API を使用する

[プログラムでアプリを更新](https://learn.microsoft.com/ja-jp/graph/api/application-update)するには、Microsoft Graph API を使用してすべてのアプリを更新し、サービス利用規約とプライバシーに関する声明ドキュメントへのリンクを含めることができます。

```
PATCH https://graph.microsoft.com/v1.0/applications/{applicationObjectId}
{ 
    "appId": "{your application object id}", 
    "info": { 
        "termsOfServiceUrl": "<your_terms_of_service_url>", 
        "supportUrl": null, 
        "privacyStatementUrl": "<your_privacy_statement_url>", 
        "marketingUrl": null, 
        "logoUrl": null 
    }
}
```

注

- 次のフィールド (`supportUrl`、`marketingUrl`、`logoUrl`) に割り当てた既存の値を上書きしないように注意してください。
- Microsoft Graph API は、Microsoft Entra アカウントを使用してサインインした場合にのみ機能します。 個人用 Microsoft アカウントはサポートされません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-authenticate-service-principal-powershell"} -->
## Azure アプリ ID を作成する (PowerShell) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-authenticate-service-principal-powershell
- Service: identity-platform
- Article date: 2025-04-16
- Summary: Azure PowerShell を使用して、Microsoft Entra アプリケーションとサービス プリンシパルを作成し、ロールベースのアクセス制御によって、リソースへのアクセス権を付与する方法について説明します。 証明書を使ってアプリケーションを認証する方法を示します。

リソースへのアクセスを必要とするアプリやスクリプトがある場合は、アプリの ID を設定し、アプリを独自の資格情報で認証できます。 この ID は、サービス プリンシパルと呼ばれます。 このアプローチを使用すると、以下のことを実行できます。

- ユーザー自身のアクセス許可とは異なるアクセス許可を、アプリケーション ID に割り当てることができます。 通常、こうしたアクセス許可は、アプリが行う必要があることに制限されます。
- 無人スクリプトを実行するときに、証明書を使用して認証できます。

重要

サービス プリンシパルを作成する代わりに、アプリケーション ID 用に Azure リソースのマネージド ID を使用することを検討します。 コードが、マネージド ID をサポートするサービス上で実行され、Microsoft Entra 認証をサポートするリソースにアクセスする場合、マネージド ID は優れた選択肢となります。 Azure リソースのマネージド ID の詳細 (どのサービスが現在マネージド ID をサポートしているかなど) については、「[Azure リソースのマネージド ID とは](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)」を参照してください。

この記事では、証明書を使用して認証するサービス プリンシパルの作成方法について説明します。 パスワードを使用するサービス プリンシパルを設定するには、「[Azure PowerShell で Azure サービス プリンシパルを作成する](https://learn.microsoft.com/ja-jp/powershell/azure/create-azure-service-principal-azureps)」を参照してください。

このアーティクルには、PowerShell の[最新バージョン](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)が必要です。

注

Azure を操作するには、Azure Az PowerShell モジュールを使用することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「[AzureRM から Az への Azure PowerShell の移行](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)」を参照してください。

### 必要なアクセス許可

この記事を完了するには、Microsoft Entra ID と Azure サブスクリプションの両方で十分なアクセス許可を持っている必要があります。 具体的には、Microsoft Entra ID でアプリケーションを作成し、ロールにサービス プリンシパルを割り当てることができる必要があります。

自分のアカウントに適切なアクセス許可があるかどうかを確認する最も簡単な方法は、Microsoft Entra 管理センターを使用することです。

### アプリケーションをロールに割り当てる

サブスクリプション内のリソースにアクセスするには、アプリケーションをロールに割り当てる必要があります。 どのロールがそのアプリケーションに適切なアクセス許可を提供するかを判断します。 利用できるロールの詳細については、「[Azure 組み込みロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles)」を参照してください。

スコープは、サブスクリプション、リソース グループ、またはリソースのレベルで設定できます。 アクセス許可は、スコープの下位レベルに継承されます。 たとえば、アプリケーションをリソース グループの*閲覧者*ロールに追加すると、そのリソース グループと、その中にあるどのリソースも読み取りができることになります。 アプリケーションがインスタンスの再起動、開始、停止などのアクションを実行できるようにするには、 *[共同作成者]* ロールを選択します。

### 自己署名証明書を使用したサービス プリンシパルの作成

以下の例では、単純なシナリオについて説明します。 ここでは、[New-​AzAD​Service​Principal](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/new-azadserviceprincipal) を使用して自己署名証明書でサービス プリンシパルを作成し、[New-AzRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/new-azroleassignment) を使用して[閲覧者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#reader)ロールをサービス プリンシパルに割り当てます。 ロールの割り当ては、現在選択されている Azure サブスクリプションに制限されます。 別のサブスクリプションを選択するには、[Set-AzContext](https://learn.microsoft.com/ja-jp/powershell/module/az.accounts/set-azcontext) を使用します。

注

New-SelfSignedCertificate コマンドレットと PKI モジュールは、現在、PowerShell Core ではサポートされていません。

```powershell
$cert = New-SelfSignedCertificate -CertStoreLocation "cert:\CurrentUser\My" `
  -Subject "CN=exampleappScriptCert" `
  -KeySpec KeyExchange
$keyValue = [System.Convert]::ToBase64String($cert.GetRawCertData())

$sp = New-AzADServicePrincipal -DisplayName exampleapp `
  -CertValue $keyValue `
  -EndDate $cert.NotAfter `
  -StartDate $cert.NotBefore
Sleep 20
New-AzRoleAssignment -RoleDefinitionName Reader -ServicePrincipalName $sp.AppId
```

この例では、新しいサービス プリンシパルが Microsoft Entra ID 全体に反映されるまでの時間を設けるために、20 秒間スリープします。 スクリプトの待機時間が不足している場合は、"プリンシパル {ID} がディレクトリ {DIR-ID} に存在しません。" というエラーが表示されます。このエラーを解決するには、しばらく待ってから、**New-AzRoleAssignment** コマンドを再度実行します。

**ResourceGroupName**パラメーターを使用して、特定のリソース グループにロールの割り当てをスコープできます。 **ResourceType**と**ResourceName**パラメーターを使用して、特定のリソースをスコープすることもできます。

**Windows 10 または Windows Server 2016 がない**場合は、PKI ソリューションから [New-SelfSignedCertificateEx コマンドレット](https://www.pkisolutions.com/tools/pspki/New-SelfSignedCertificateEx/) をダウンロードしてください。 ダウンロードしたファイルを展開し、必要なコマンドレットをインポートします。

```powershell
# Only run if you could not use New-SelfSignedCertificate
Import-Module -Name c:\ExtractedModule\New-SelfSignedCertificateEx.ps1
```

このスクリプトでは、証明書を生成するために次の 2 行を置き換えます。

```powershell
New-SelfSignedCertificateEx -StoreLocation CurrentUser `
  -Subject "CN=exampleapp" `
  -KeySpec "Exchange" `
  -FriendlyName "exampleapp"
$cert = Get-ChildItem -path Cert:\CurrentUser\my | where {$PSitem.Subject -eq 'CN=exampleapp' }
```

#### 自動化された PowerShell スクリプトから証明書を渡す

サービス プリンシパルとしてサインインするときは常に、お使いのAD アプリのディレクトリのテナント ID を指定します。 テナントとは、Microsoft Entra ID のインスタンスです。

```powershell
$TenantId = (Get-AzSubscription -SubscriptionName "Contoso Default").TenantId
$ApplicationId = (Get-AzADApplication -DisplayNameStartWith exampleapp).AppId

$Thumbprint = (Get-ChildItem cert:\CurrentUser\My\ | Where-Object {$_.Subject -eq "CN=exampleappScriptCert" }).Thumbprint
Connect-AzAccount -ServicePrincipal `
  -CertificateThumbprint $Thumbprint `
  -ApplicationId $ApplicationId `
  -TenantId $TenantId
```

### 認証局の証明書を使用したサービスプリンシパルの作成

次の例では、証明機関から発行された証明書を使用して、サービス プリンシパルを作成します。 割り当ては、指定された Azure サブスクリプションに制限されます。 [閲覧者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#reader)ロールにサービス プリンシパルが追加されます。 ロールの割り当て中にエラーが発生した場合は、割り当てが再試行されます。

```powershell
Param (
 [Parameter(Mandatory=$true)]
 [String] $ApplicationDisplayName,

 [Parameter(Mandatory=$true)]
 [String] $SubscriptionId,

 [Parameter(Mandatory=$true)]
 [String] $CertPath,

 [Parameter(Mandatory=$true)]
 [String] $CertPlainPassword
 )

 Connect-AzAccount
 Import-Module Az.Resources
 Set-AzContext -Subscription $SubscriptionId

 $CertPassword = ConvertTo-SecureString $CertPlainPassword -AsPlainText -Force

 $PFXCert = New-Object -TypeName System.Security.Cryptography.X509Certificates.X509Certificate2 -ArgumentList @($CertPath, $CertPassword)
 $KeyValue = [System.Convert]::ToBase64String($PFXCert.GetRawCertData())

 $ServicePrincipal = New-AzADServicePrincipal -DisplayName $ApplicationDisplayName
 New-AzADSpCredential -ObjectId $ServicePrincipal.Id -CertValue $KeyValue -StartDate $PFXCert.NotBefore -EndDate $PFXCert.NotAfter
 Get-AzADServicePrincipal -ObjectId $ServicePrincipal.Id 

 $NewRole = $null
 $Retries = 0;
 While ($NewRole -eq $null -and $Retries -le 6)
 {
    # Sleep here for a few seconds to allow the service principal application to become active (should only take a couple of seconds normally)
    Sleep 15
    New-AzRoleAssignment -RoleDefinitionName Reader -ServicePrincipalName $ServicePrincipal.AppId | Write-Verbose -ErrorAction SilentlyContinue
    $NewRole = Get-AzRoleAssignment -ObjectId $ServicePrincipal.Id -ErrorAction SilentlyContinue
    $Retries++;
 }

 $NewRole
```

#### 自動化された PowerShell スクリプトから証明書を渡す

サービス プリンシパルとしてサインインするときは常に、お使いのAD アプリのディレクトリのテナント ID を指定します。 テナントとは、Microsoft Entra ID のインスタンスです。

```powershell
Param (

 [Parameter(Mandatory=$true)]
 [String] $CertPath,

 [Parameter(Mandatory=$true)]
 [String] $CertPlainPassword,

 [Parameter(Mandatory=$true)]
 [String] $ApplicationId,

 [Parameter(Mandatory=$true)]
 [String] $TenantId
 )

 $CertPassword = ConvertTo-SecureString $CertPlainPassword -AsPlainText -Force
 $PFXCert = New-Object `
  -TypeName System.Security.Cryptography.X509Certificates.X509Certificate2 `
  -ArgumentList @($CertPath, $CertPassword)
 $Thumbprint = $PFXCert.Thumbprint

 Connect-AzAccount -ServicePrincipal `
  -CertificateThumbprint $Thumbprint `
  -ApplicationId $ApplicationId `
  -TenantId $TenantId
```

アプリケーション ID とテナント ID は機密情報ではないため、スクリプトに直接埋め込むことができます。 テナント ID を取得する必要がある場合は、次のコマンドを使用します。

```powershell
(Get-AzSubscription -SubscriptionName "Contoso Default").TenantId
```

アプリケーション ID を取得する必要がある場合は、次のコマンドを使用します。

```powershell
(Get-AzADApplication -DisplayNameStartWith {display-name}).AppId
```

### 資格情報の変更

セキュリティ侵害の発生または資格情報の期限切れのために AD アプリの資格情報を変更するには、[Remove-AzADAppCredential](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/remove-azadappcredential) コマンドレットと [New-AzADAppCredential](https://learn.microsoft.com/ja-jp/powershell/module/az.resources/new-azadappcredential) コマンドレットを使用します。

アプリケーションのすべての資格情報を削除するには、次のコマンドレットを使用します。

```powershell
Get-AzADApplication -DisplayName exampleapp | Remove-AzADAppCredential
```

証明書の値を追加するには、この記事の説明に従って自己署名証明書を作成します。 その後、以下を使用します。

```powershell
Get-AzADApplication -DisplayName exampleapp | New-AzADAppCredential `
  -CertValue $keyValue `
  -EndDate $cert.NotAfter `
  -StartDate $cert.NotBefore
```

### デバッグ

サービス プリンシパルの作成時に、以下のエラーが発生する場合があります。

- **"Authentication\_Unauthorized"** または **"サブスクリプションがコンテキストで見つかりませんでした。"** - Microsoft Entra ID でアプリを登録するために必要なアクセス許可がアカウントにない場合、このエラーが表示されます。 通常は、Microsoft Entra ID の管理者ユーザーのみがアプリを登録できるときに、自分のアカウントが管理者でない場合に、このエラーが発生します。管理者に連絡して、自分を管理者ロールに割り当ててもらうか、ユーザーがアプリケーションを登録できるようにしてもらいます。
- アカウントに **"'/subscriptions/{guid}' をスコープとした 'Microsoft.Authorization/roleAssignments/write' のアクションを実行するためのアクセス権限がありません"** - このエラーは、自分のアカウントが ID にロールを割り当てるのに十分なアクセス許可を持っていない場合に表示されます。 サブスクリプション管理者に連絡して、自分をユーザー アクセス管理者ロールに追加してもらいます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-call-a-web-api-with-curl"} -->
## cURL で ASP.NET Core の Web API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-call-a-web-api-with-curl
- Service: identity-platform
- Article date: 2025-03-06
- Summary: Microsoft ID プラットフォームと cURL を使用して保護された ASP.NET Core Web API を呼び出す方法についての説明。

::: zone pivot="no-api"

この記事では、Client URL (cURL) を使用して保護された ASP.NET Core の Web API を呼び出す方法について説明します。 cURLは、開発者がサーバーとの間でデータを転送するために使用するコマンド ライン ツールです。 この記事では、テナントで Web アプリと Web API を登録します。 Web アプリは、Microsoft ID プラットフォームによって生成されたアクセス トークンを取得するために使用します。 次にこのトークンを使って、cURL を使用して Web API に承認された呼び出しを行います。

::: zone-end

::: zone pivot="api"

この記事では、Client URL (cURL) を使用して保護された ASP.NET Core の Web API を呼び出す方法について説明します。 cURLは、開発者がサーバーとの間でデータを転送するために使用するコマンド ライン ツールです。 「[チュートリアル: API に保護されたエンドポイントを実装する](https://learn.microsoft.com/ja-jp/entra/identity-platform/web-api-tutorial-03-protect-endpoint)」では保護された API を作成しましたが、その後に Web アプリケーションを Microsoft ID プラットフォームに登録してアクセス トークンを生成する必要があります。 次にこのトークンを使って、cURL を使用して API に承認された呼び出しを行います。

::: zone-end

### 前提条件

::: zone pivot="no-api"

- アクティブなサブスクリプションが含まれる Azure アカウント。 [無料でアカウントを作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 以下のいずれの Microsoft Entra ロールにも、必要なアクセス許可が含まれています。
    - アプリケーション管理者
    - アプリケーション開発者
    - クラウド アプリケーション管理者
- お使いのワークステーション コンピューターに [cURL をダウンロードしてインストール](https://curl.se/download.html)します。
- [.NET 8.0 SDK](https://dotnet.microsoft.com/download/dotnet) の最小要件。

::: zone-end

::: zone pivot="api"

- アクティブなサブスクリプションが含まれる Azure アカウント。 [無料でアカウントを作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 以下のいずれの Microsoft Entra ロールにも、必要なアクセス許可が含まれています。
    - アプリケーション管理者
    - アプリケーション開発者
    - クラウド アプリケーション管理者
- チュートリアル シリーズの完了:
    - [チュートリアル: Microsoft ID プラットフォームに Web API を登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/web-api-tutorial-01-register-app)。
    - [チュートリアル: 認証のための ASP.NET Core プロジェクトを作成して構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/web-api-tutorial-02-prepare-api)。
    - [チュートリアル: API に保護されたエンドポイントを実装する](https://learn.microsoft.com/ja-jp/entra/identity-platform/web-api-tutorial-03-protect-endpoint)。
- お使いのワークステーション コンピューターに [cURL をダウンロードしてインストール](https://curl.se/download.html)します。

::: zone-end

### Microsoft ID プラットフォームにアプリケーションを登録する

Microsoft ID プラットフォームでは、ID およびアクセス管理サービスを利用する前に、アプリケーションを登録する必要があります。 アプリケーションの登録では、アプリケーションの名前と種類とサインイン対象ユーザーを指定できます。 サインイン対象ユーザーは、特定のアプリケーションにサインインできるユーザー アカウントの種類を指定します。

::: zone pivot="no-api"

#### Web API を登録する

Web API の登録を作成するには、次の手順のようにします。

1. [アプリケーション開発者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニューの **[設定]** アイコン  を使い、**[ディレクトリとサブスクリプション]** メニューからアプリケーションを登録するテナントに切り替えます。
3. **Entra ID**&gt;**アプリ登録**に移動します。
4. **[新規登録]** を選択します。
5. アプリケーションの**名前** (*NewWebAPI1* など) を入力します。
6. **[サポートされているアカウントの種類]** で、 **[この組織のディレクトリ内のアカウントのみ]** を選択します。 さまざまなアカウントの種類については、**[選択に関するヘルプ]** オプションを選択します。
7. **[登録]** を選択します。

    [Image: 名前を入力し、アカウントの種類を選択する方法を示すスクリーンショット。]
8. アプリケーションの **[概要]** ペインは、登録が完了すると表示されます。 アプリケーションのソース コードで使用する**ディレクトリ (テナント) ID** と**アプリケーション (クライアント) ID** を記録します。

    [Image: 概要ページの識別子の値を示すスクリーンショット。]

注意

**サポートされているアカウントの種類**は、「[アプリケーションによってサポートされるアカウントを変更する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-modify-supported-accounts)」を参照して変更することができます。

##### API を公開する

API が登録されると、API がクライアント アプリケーションに公開するスコープを定義することで、そのアクセス許可を構成することができます。 クライアント アプリケーションは、アクセス トークンとその要求を保護された Web API に渡すことで、操作を実行するためのアクセス許可を要求します。 Web API が要求された操作を実行するのは、受け取ったアクセス トークンが有効な場合だけに限られます。

1. **[管理]** で、**[API の公開] &gt; [スコープの追加]** の順に選択します。 **[保存してから続行]** を選択することで、提案された`(api://{clientId})` を受け入れます。 `{clientId}` は、**[概要]** ページから記録した値です。 そして、次の情報を入力します。

    1. **[スコープ名]** に「`Forecast.Read`」と入力します。
    2. **[同意できるユーザー]** で **[管理者とユーザー]** オプションが選択されていることを確認します。
    3. **[管理者の同意の表示名]** ボックスには、「`Read forecast data`」と入力します。
    4. **[管理者の同意の説明]** ボックスには、「`Allows the application to read weather forecast data`」と入力します。
    5. **[ユーザーの同意の表示名]** ボックスには、「`Read forecast data`」と入力します。
    6. **[ユーザーの同意の説明]** ボックスには、「`Allows the application to read weather forecast data`」と入力します。
    7. **[状態]** が **[有効]** に設定されていることを確認します。
2. **[スコープの追加]** を選択します。 スコープが正しく入力されている場合は、[ **API の公開** ] ウィンドウに一覧表示されます。

    [Image: API にスコープを追加するときのフィールド値を示すスクリーンショット。]

::: zone-end

#### Web アプリを登録する

ただし、Web API があるだけでは不十分で、作成した Web API にアクセスするためのアクセス トークンを取得する Web アプリも必要です。

Web アプリの登録を作成するには、次の手順のようにします。

::: zone pivot="no-api"

1. **[ホーム]** を選択してホーム ページに戻ります。 **Entra ID**&gt;**アプリ登録**に移動します。
2. **[新規登録]** を選択します。
3. アプリケーションの**名前** (`web-app-calls-web-api` など) を入力します。
4. **[サポートされているアカウントの種類]** で、 **[この組織のディレクトリ内のアカウントのみ]** を選択します。 さまざまなアカウントの種類の詳細については、**[選択に関するヘルプ]** オプションを選択します。
5. **[リダイレクト URI (省略可能)]** で、**[Web]** を選択し、URL テキスト ボックスに「`http://localhost`」と入力します。
6. **[登録]** を選択します。

::: zone-end

::: zone pivot="api"

1. [アプリケーション開発者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニューの **[設定]** アイコン  を使い、**[ディレクトリとサブスクリプション]** メニューからアプリケーションを登録するテナントに切り替えます。
3. **Entra ID**&gt;**アプリ登録**に移動します。
4. **[新規登録]** を選択します。
5. アプリケーションの名前 (`web-app-calls-web-api` など) を入力します。
6. **[サポートされているアカウントの種類]** で、 **[この組織のディレクトリ内のアカウントのみ]** を選択します。 さまざまなアカウントの種類の詳細については、**[選択に関するヘルプ]** オプションを選択します。
7. **[リダイレクト URI (省略可能)]** で、**[Web]** を選択し、URL テキスト ボックスに「`http://localhost`」と入力します。
8. **[登録]** を選択します。

::: zone-end

登録が完了すると、アプリの登録が **[概要]** ペインに表示されます。 **ディレクトリ (テナント) ID** と**アプリケーション (クライアント) ID** は、後の手順で使用するので記録しておきます。

##### クライアント シークレットの追加

クライアント シークレットは、アプリが自分自身を識別するために使用できる文字列値であり、" アプリケーション パスワード"と呼ばれることもあります。 Web アプリは、トークンを要求するときに、このクライアント シークレットを使用してその ID を証明します。

次の手順に従って、クライアント シークレットを構成します。

1. **[概要]** ペインの **[管理]** で、**[証明書とシークレット]**&gt;**[クライアント シークレット]**&gt;**[新しいクライアント シークレット]** の順に選択します。
2. クライアント シークレットの説明 (例: *自分のクライアント シークレット*) を追加します。
3. シークレットの有効期限を選択するか、カスタムの有効期間を指定します。

    - クライアント シークレットの有効期間は、2 年間 (24 か月) 以内に制限されています。 24 か月を超えるカスタムの有効期間を指定することはできません。
    - Microsoft では、有効期限の値は 12 か月未満に設定することをお勧めしています。
4. **[追加]** を選択します。
5. クライアント シークレットの**値**は必ず記録しておいてください。 このページからの移動後は、このシークレットの値は "**二度と表示されません**"。

##### Web API へのアクセスを許可するアプリケーションのアクセス許可を追加する

Web アプリの登録で Web API のスコープを指定することにより、Web アプリは Microsoft ID プラットフォームが提供するスコープを含むアクセス トークンを取得することができます。 次にコード内で、Web API はアクセス トークンに含まれるスコープに基づいて、リソースに対するアクセス許可ベースのアクセスを提供することができます。

次の手順に従って、Web API に対する Web アプリのアクセス許可を構成します。

1. Web アプリケーション (**web-app-that-calls-web-api**) の *[概要]* ペインにある **[管理]** で、**[API のアクセス許可]**&gt;**[アクセス許可の追加]**&gt;**[所属する組織で使用している API]** の順に選択します。
2. **NewWebAPI1** またはアクセス許可を追加したい API を選択します。
3. **[アクセス許可を選択]** で、**Forecast.Read** の横にあるボックスをチェックします。 場合によっては、[ **アクセス許可]** リストを展開する必要があります。 これにより、サインインしているユーザーに代わってクライアント アプリがもつべきアクセス許可が選択されます。
4. **[アクセス許可の追加]** を選択してプロセスを完了します。

これらのアクセス許可をご自身の API に追加した後、選択したアクセス許可が **[構成されたアクセス許可]** に表示されます。

Microsoft Graph API に対する **User.Read** アクセス許可も表示されています。 このアクセス許可は、アプリを登録すると自動的に追加されます。

::: zone pivot="no-api"

### Web API をテストする

1. [ms-identity-docs-code-dotnet](https://github.com/Azure-Samples/ms-identity-docs-code-dotnet) リポジトリを複製します。

    ```bash
    git clone https://github.com/Azure-Samples/ms-identity-docs-code-dotnet.git 
    ```
2. `ms-identity-docs-code-dotnet/web-api` フォルダーに移動し、`./appsettings.json` ファイルを開き、`{APPLICATION_CLIENT_ID}` と `{DIRECTORY_TENANT_ID}` を以下のように置き換えます。

    - `{APPLICATION_CLIENT_ID}` は、アプリの **[概要]** ペインの **[アプリの登録]** にある Web API **アプリケーション (クライアント) ID** です。
    - `{DIRECTORY_TENANT_ID}` は、アプリの **[概要]** ペインの **[アプリの登録]** にある Web API **ディレクトリ (テナント) ID** です。
3. 次のコマンドを実行して、アプリを起動します。

## [.NET 6.0 の場合](#tab/dotnet6)
```bash
    dotnet run
    ```

## [.NET 7.0 の場合](#tab/dotnet7)
```bash
    dotnet run --launch-profile https
    ```

---
4. 次のような出力が表示されます。 ポート番号を `https://localhost:{port}` URL に記録します。

    ```bash
    ... 
    info: Microsoft.Hosting.Lifetime[14]
          Now listening on: https://localhost:{port}
    ...
    ```

::: zone-end

::: zone pivot="api"

### Web API をテストする

1. 「[チュートリアル: ASP.NET Core プロジェクトを作成し、API を構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/web-api-tutorial-02-prepare-api)」で作成した Web API (たとえば *NewWebAPILocal*) に移動し、フォルダーを開きます。
2. 新しいターミナル ウィンドウを開き、Web API プロジェクトが置いてあるフォルダーに移動します。

## [.NET 6.0 の場合](#tab/dotnet6)
1. 次のコマンドを実行して、アプリを起動します。

    ```bash
    dotnet run
    ```

## [.NET 7.0 の場合](#tab/dotnet7)
1. 次のコマンドを実行して、`https` プロファイルでアプリを起動します。

    ```bash
    dotnet run --launch-profile https
    ```

---

1. 次のような出力が表示されます。 ポート番号を `https://localhost:{port}` URL に記録します。

    ```bash
    ... 
    info: Microsoft.Hosting.Lifetime[14]
          Now listening on: https://localhost:{port}
    ...
    ```

::: zone-end

#### 承認コードを要求する

承認コード フローは、クライアントがユーザーを `/authorize` エンドポイントにリダイレクトさせることから始まります。 この要求では、クライアントは、ユーザーからの `Forecast.Read` のアクセス許可を要求します。

```http
https://login.microsoftonline.com/{tenant_id}/oauth2/v2.0/authorize?client_id={web-app-calls-web-api_application_client_id}&response_type=code&redirect_uri=http://localhost&response_mode=query&scope=api://{web_API_application_client_id}/Forecast.Read
```

1. URL をコピーし、次のパラメーターを置き換えてブラウザーに貼り付けます。

    - `{tenant_id}` は Web アプリ **Directory (tenant) ID** です。
    - `{web-app-calls-web-api_application_client_id}` は、Web アプリの (**web-app-calls-web-api**) *[概要]* ペインの**アプリケーション (クライアント) ID** です。
    - `{web_API_application_client_id}` は、Web API の (**NewWebAPI1**) *[概要]* ペインの**アプリケーション (クライアント) ID** です。
2. アプリが登録されている Microsoft Entra テナントでユーザーとしてサインインします。 必要に応じて、アクセス時に求められる事項に同意します。
3. ブラウザーが `http://localhost/`にリダイレクトされます。 ブラウザーのナビゲーション バーを参照し、次の手順で使用する `{authorization_code}` をコピーします。 URL は、次のスニペットの形式になります。

    ```http
    http://localhost/?code={authorization_code}
    ```

#### 承認コードと cURL を使用してアクセス トークンを取得する

cURL を使用して、Microsoft ID プラットフォームからアクセス トークンを要求できるようになりました。

1. 次のスニペットで cURL コマンドをコピーします。 かっこ内の値を、ターミナルに合わせて次のパラメーターに置き換えます。 かっこは必ず削除してください。

## [バッシュ](#tab/bash)
```bash
    curl -X POST https://login.microsoftonline.com/{tenant_id}/oauth2/v2.0/token \
    -d 'client_id={web-app-calls-web-api_application_client_id}' \
    -d 'api://{web_API_application_client_id}/Forecast.Read' \
    -d 'code={authorization_code}&session_state={web-app-calls-web-api_application_client_id}' \
    -d 'redirect_uri=http://localhost' \
    -d 'grant_type=authorization_code' \
    -d 'client_secret={client_secret}'
    ```

## [Windows コマンド プロンプト](#tab/command-prompt)
```bash
    curl -X POST https://login.microsoftonline.com/{tenant_id}/oauth2/v2.0/token ^
     -d "client_id={web-app-calls-web-api_application_client_id}" ^
     -d "api://{web_API_application_client_id}/Forecast.Read" ^
     -d "code={authorization_code}&session_state={web-app-calls-web-api_application_client_id}" ^
     -d "redirect_uri=http://localhost" ^
     -d "grant_type=authorization_code" ^
     -d "client_secret={client_secret}"
    ```

---

    - `{tenant_id}` は Web アプリ **Directory (tenant) ID** です。
    - `client_id={web-app-calls-web-api_application_client_id}` と `session_state={web-app-calls-web-api_application_client_id}` は、Web アプリの (**web-app-calls-web-api**) *[概要]* ペインの**アプリケーション (クライアント) ID** です。
    - `api://{web_API_application_client_id}/Forecast.Read` は、Web API の (**NewWebAPI1**) *[概要]* ペインの**アプリケーション (クライアント) ID** です。
    - `code={authorization_code}` は、「承認コードを要求する」で受信した承認コードです。 これにより、cURL ツールはアクセス トークンを要求できます。
    - `client_secret={client_secret}` は、「**クライアント シークレットを追加する**」で記録したクライアント シークレットの値です。
2. cURL コマンドを実行します。正しく入力すると、次の出力のような JSON 応答が表示されます。

    ```json
    {
       "token_type": "Bearer",
       "scope": "api://{web_API_application_client_id}/Forecast.Read",
       "expires_in": 3600,
       "ext_expires_in": 3600,
       "access_token": "{access_token}"
    }
    ```

#### アクセス トークンを使用して Web API を呼び出す

前の cURL コマンドを実行すると、Microsoft ID プラットフォームによってアクセス トークンが提供されました。 取得したトークンを HTTP 要求のベアラーとして使用して、Web API を呼び出せるようになりました。

1. Web API を呼び出すには、次の cURL コマンドをコピーし、かっこ内の次の値を置き換えて、ターミナルに貼り付けます。

    ```bash
    curl -X GET https://localhost:{port}/weatherforecast -ki \
    -H 'Content-Type: application/json' \
    -H "Authorization: Bearer {access_token}"
    ```

    - `{access_token}` 前のセクションの JSON 出力から記録されたアクセス トークン値。
    - `{port}` ターミナルで API を実行するときに記録された Web API からのポート番号。 `https` ポート番号であることを確認します。
2. 要求に有効なアクセス トークンが含まれている場合、想定される応答は `HTTP/2 200` で、出力は次のようになります。

    ```bash
    HTTP/2 200
    content-type: application/json; charset=utf-8
    date: Day, DD Month YYYY HH:MM:SS
    server: Kestrel
    [{"date":"YYYY-MM-DDTHH:MM:SS","temperatureC":36,"summary":"Hot","temperatureF":96},{"date":"YYYY-MM-DDTHH:MM:SS","temperatureC":43,"summary":"Warm","temperatureF":109},{"date":"YYYY-MM-DDTHH:MM:SS","temperatureC":18,"summary":"Warm","temperatureF":64},{"date":"YYYY-MM-DDTHH:MM:SS","temperatureC":50,"summary":"Chilly","temperatureF":121},{"date":"YYYY-MM-DDTHH:MM:SS","temperatureC":3,"summary":"Bracing","temperatureF":37}]
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-call-a-web-api-with-rest-client"} -->
## Insomnia で ASP.NET Core の Web API を呼び出す - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-call-a-web-api-with-rest-client
- Service: identity-platform
- Article date: 2024-05-21
- Summary: Microsoft ID プラットフォームと Insomnia を使って、保護された ASP.NET Core Web API を呼び出す方法について説明します。

::: zone pivot="no-api"

この記事では、[Insomnia](https://insomnia.rest/download) を使用して保護された ASP.NET Core の Web API を呼び出す方法について説明します。 Insomnia は、Web API に HTTP 要求を送信して、その承認やアクセス制御 (認証) ポリシーをテストすることができるアプリケーションです。 この記事では、テナントで Web アプリと Web API を登録します。 Web アプリは、Microsoft ID プラットフォームによって生成されたアクセス トークンを取得するために使用します。 次にこのトークンを使って、Insomniaで Web API に承認された呼び出しを行います。

::: zone-end

::: zone pivot="api"

この記事では、[Insomnia](https://insomnia.rest/download) を使用して保護された ASP.NET Core の Web API を呼び出す方法について説明します。 Insomnia は、Web API に HTTP 要求を送信して、その承認やアクセス制御 (認証) ポリシーをテストすることができるアプリケーションです。 「[チュートリアル: API に保護されたエンドポイントを実装する](https://learn.microsoft.com/ja-jp/entra/identity-platform/web-api-tutorial-03-protect-endpoint)」では保護された API を作成しましたが、その後に Web アプリケーションを Microsoft ID プラットフォームに登録してアクセス トークンを生成する必要があります。 次にこのトークンを使って、Insomnia で API に承認された呼び出しを行います。

::: zone-end

### 前提条件

::: zone pivot="no-api"

- アクティブなサブスクリプションが含まれる Azure アカウント。 [無料でアカウントを作成できます](https://azure.microsoft.com/free/)。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 以下のいずれの Microsoft Entra ロールにも、必要なアクセス許可が含まれています。
    - アプリケーション管理者
    - アプリケーション開発者
    - クラウド アプリケーション管理者
- [Insomnia をダウンロードおよびインストールしておきます](https://insomnia.rest/download)。 Insomnia で、API 要求に使用するアクセス トークンを取得します。
- [.NET 8.0 SDK](https://dotnet.microsoft.com/download/dotnet) の最小要件。

::: zone-end

::: zone pivot="api"

- アクティブなサブスクリプションが含まれる Azure アカウント。 [無料でアカウントを作成できます](https://azure.microsoft.com/free/)。
- この Azure アカウントには、アプリケーションを管理するためのアクセス許可が必要です。 以下のいずれの Microsoft Entra ロールにも、必要なアクセス許可が含まれています。
    - アプリケーション管理者
    - アプリケーション開発者
    - クラウド アプリケーション管理者
- チュートリアル シリーズの完了:
    - [チュートリアル: Microsoft ID プラットフォームに Web API を登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/web-api-tutorial-01-register-app)。
    - [チュートリアル: 認証のための ASP.NET Core プロジェクトを作成して構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/web-api-tutorial-02-prepare-api)。
    - [チュートリアル: API に保護されたエンドポイントを実装する](https://learn.microsoft.com/ja-jp/entra/identity-platform/web-api-tutorial-03-protect-endpoint)。
- [Insomnia をダウンロードおよびインストールしておきます](https://insomnia.rest/download)。

::: zone-end

### アプリケーションの登録

Microsoft ID プラットフォームでは、ID およびアクセス管理サービスを利用する前に、アプリケーションを登録する必要があります。 アプリケーションの登録では、アプリケーションの名前と種類とサインイン対象ユーザーを指定できます。 サインイン対象ユーザーは、特定のアプリケーションにサインインできるユーザー アカウントの種類を指定します。

::: zone pivot="no-api"

#### Web API を登録する

Web API の登録を作成するには、次の手順のようにします。

1. [アプリケーション開発者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニューの **[設定]** アイコン  を使い、**[ディレクトリとサブスクリプション]** メニューからアプリケーションを登録するテナントに切り替えます。
3. **Entra ID**&gt;**アプリ登録**に移動します。
4. **[新規登録]** を選択します。
5. アプリケーションの**名前** (*NewWebAPI1* など) を入力します。
6. **[サポートされているアカウントの種類]** で、 **[この組織のディレクトリ内のアカウントのみ]** を選択します。 さまざまなアカウントの種類については、**[選択に関するヘルプ]** オプションを選択します。
7. **[登録]** を選択します。

    [Image: 名前を入力し、アカウントの種類を選択する方法を示すスクリーンショット。]
8. 登録が完了すると、アプリケーションの **[概要]** ペインが表示されます。 **ディレクトリ (テナント) ID** と**アプリケーション (クライアント) ID** は、後の手順で使用するので記録しておきます。

    [Image: 概要ページの識別子の値を示すスクリーンショット。]

注意

**サポートされているアカウントの種類**は、「[アプリケーションによってサポートされるアカウントを変更する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-modify-supported-accounts)」を参照して変更することができます。

##### API を公開する

API が登録されると、API がクライアント アプリケーションに公開するスコープを定義することで、そのアクセス許可を構成することができます。 クライアント アプリケーションは、アクセス トークンとその要求を保護された Web API に渡すことで、操作を実行するためのアクセス許可を要求します。 Web API が要求された操作を実行するのは、受け取ったアクセス トークンが有効な場合だけに限られます。

1. **[管理]** で、**[API の公開] &gt; [スコープの追加]** の順に選択します。 **[保存してから続行]** を選択することで、提案された`(api://{clientId})` を受け入れます。 `{clientId}` は、**[概要]** ページから記録した値です。 そして、次の情報を入力します。

    1. **[スコープ名]** に「`Forecast.Read`」と入力します。
    2. **[同意できるユーザー]** で **[管理者とユーザー]** オプションが選択されていることを確認します。
    3. **[管理者の同意の表示名]** ボックスには、「`Read forecast data`」と入力します。
    4. **[管理者の同意の説明]** ボックスには、「`Allows the application to read weather forecast data`」と入力します。
    5. **[ユーザーの同意の表示名]** ボックスには、「`Read forecast data`」と入力します。
    6. **[ユーザーの同意の説明]** ボックスには、「`Allows the application to read weather forecast data`」と入力します。
    7. **[状態]** が **[有効]** に設定されていることを確認します。
2. **[スコープの追加]** を選択します。 スコープが正しく入力されている場合は、**[API の公開]** ペインに表示されます。

    [Image: API にスコープを追加するときのフィールド値を示すスクリーンショット。]

::: zone-end

#### Web アプリを登録する

Web API を用意するだけでは不十分です。アクセス トークンを取得するための Web アプリも入手して、Web API にアクセスする必要があります。

Web アプリの登録を作成するには、次の手順のようにします。

::: zone pivot="no-api"

1. **[ホーム]** を選択してホーム ページに戻ります。 **Entra ID**&gt;**アプリ登録**に移動します。
2. **[新規登録]** を選択します。
3. **[名前]** に、アプリケーションの名前を **identity-client-web-app** などと入力します。
4. **[サポートされているアカウントの種類]** で、 **[この組織のディレクトリ内のアカウントのみ]** を選択します。 さまざまなアカウントの種類の詳細については、**[選択に関するヘルプ]** オプションを選択します。
5. **[リダイレクト URI (省略可能)]** で、**[Web]** を選択し、URL テキスト ボックスに「`http://localhost`」と入力します。
6. **[登録]** を選択します。

::: zone-end

::: zone pivot="api"

1. [アプリケーション開発者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニューの **[設定]** アイコン  を使い、**[ディレクトリとサブスクリプション]** メニューからアプリケーションを登録するテナントに切り替えます。
3. **Entra ID**&gt;**アプリ登録**に移動します。
4. **[新規登録]** を選択します。
5. **[名前]** に、アプリケーションの名前を **identity-client-web-app** などと入力します。
6. **[サポートされているアカウントの種類]** で、 **[この組織のディレクトリ内のアカウントのみ]** を選択します。 さまざまなアカウントの種類の詳細については、**[選択に関するヘルプ]** オプションを選択します。
7. **[リダイレクト URI (省略可能)]** で、**[Web]** を選択し、URL テキスト ボックスに「`http://localhost`」と入力します。
8. **[登録]** を選択します。

::: zone-end

登録が完了すると、アプリケーションの **[概要]** ペインが表示されます。 **ディレクトリ (テナント) ID** と**アプリケーション (クライアント) ID** は、後の手順で使用するので記録しておきます。

##### クライアント シークレットの追加

クライアント シークレットは、アプリが自分自身を識別するために使用できる文字列値であり、" アプリケーション パスワード"と呼ばれることもあります。 Web アプリは、トークンを要求するときに、このクライアント シークレットを使用してその ID を証明します。

次の手順に従って、クライアント シークレットを構成します。

1. **[概要]** ペインの **[管理]** で、**[証明書とシークレット]**&gt;**[クライアント シークレット]**&gt;**[新しいクライアント シークレット]** の順に選択します。
2. クライアント シークレットの説明 (例: *自分のクライアント シークレット*) を追加します。
3. シークレットの有効期限を選択するか、カスタムの有効期間を指定します。

    - クライアント シークレットの有効期間は、2 年間 (24 か月) 以内に制限されています。 24 か月を超えるカスタムの有効期間を指定することはできません。
    - Microsoft では、有効期限の値は 12 か月未満に設定することをお勧めしています。
4. **[追加]** を選択します。
5. クライアント シークレットの**値**は必ず記録しておいてください。 このページからの移動後は、このシークレットの値は "**二度と表示されません**"。

クライアント シークレットを安全に格納する方法の詳細については、「[Key Vault でのシークレットの管理に関するベスト プラクティス](https://learn.microsoft.com/ja-jp/azure/key-vault/secrets/secrets-best-practices#configuration-and-storing)」を参照してください。

##### Web API にアクセスするためのアクセス許可を追加する

Web API のスコープを指定することにより、Web アプリは Microsoft ID プラットフォームが提供するスコープを含むアクセス トークンを取得することができます。 次にコード内で、Web API はアクセス トークンに含まれるスコープに基づいて、リソースに対するアクセス許可ベースのアクセスを提供することができます。

次の手順に従って、Web API に対するクライアントのアクセス許可を構成します。

1. アプリケーションの **[概要]** ペインの **[管理]** で、**[API のアクセス許可]**&gt;**[アクセス許可の追加]**&gt;**[所属する組織で使用している API]** の順に選択します。
2. **NewWebAPI1** またはアクセス許可を追加したい API を選択します。
3. **[アクセス許可を選択]** で、**Forecast.Read** の横にあるボックスをチェックします。 **[アクセス許可]** の一覧を展開する必要があるかもしれません。 これにより、サインインしているユーザーに代わってクライアント アプリがもつべきアクセス許可が選択されます。
4. **[アクセス許可の追加]** を選択してプロセスを完了します。

これらのアクセス許可をご自身の API に追加した後、選択したアクセス許可が **[構成されたアクセス許可]** に表示されます。

Microsoft Graph API に対する **User.Read** アクセス許可も表示されています。 このアクセス許可は、アプリを登録すると自動的に追加されます。

::: zone pivot="no-api"

### Web API をテストする

API が動作し、要求を処理する準備ができていることを確認するには、次の手順に従います。

1. [ms-identity-docs-code-dotnet](https://github.com/Azure-Samples/ms-identity-docs-code-dotnet) リポジトリを複製します。

    ```bash
    git clone https://github.com/Azure-Samples/ms-identity-docs-code-dotnet.git
    ```
2. `ms-identity-docs-code-dotnet/web-api` に移動して `appsettings.json` を開き、`{APPLICATION_CLIENT_ID}` と `{DIRECTORY_TENANT_ID}` を次の値に置き換えます。

    - `{APPLICATION_CLIENT_ID}` は、アプリの **[概要]** ウィンドウにある Web API **アプリケーション (クライアント) ID** です。
    - `{DIRECTORY_TENANT_ID}` は、アプリの **[概要]** ウィンドウにある Web API **ディレクトリ (テナント) ID** です。
3. 次のコマンドを実行して、アプリを起動します。

    ```Console
    dotnet run
    ```
4. 次のような出力が表示されます。 ポート番号を `https://localhost:{port}` URL に記録します。

    ```Console
    ...
    info: Microsoft.Hosting.Lifetime[14]
          Now listening on: https://localhost:{port}
    ...
    ```

::: zone-end

::: zone pivot="api"

### Web API をテストする

API が動作し、要求を処理する準備ができていることを確認するには、次の手順に従います。

1. 「[チュートリアル: ASP.NET Core プロジェクトを作成し、API を構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/web-api-tutorial-02-prepare-api)」で作成した Web API (たとえば *NewWebAPILocal*) に移動し、フォルダーを開きます。
2. 新しいターミナル ウィンドウを開き、Web API プロジェクトが置いてあるフォルダーに移動します。

## [.NET 6.0 の場合](#tab/dotnet6)
1. 次のコマンドを実行して、アプリを起動します。

        ```Console
        dotnet run
        ```

## [.NET 7.0 の場合](#tab/dotnet7)
1. 新しいターミナルを開き、次のコマンドを実行してアプリを `https` プロファイルで起動します。

        ```Console
        dotnet run -launch-profile https`
        ```

---
3. 次のような出力が表示されます。 ポート番号を `https://localhost:{port}` URL に記録します。

    ```Console
    ...
    info: Microsoft.Hosting.Lifetime[14]
          Now listening on: https://localhost:{port}
    ...
    ```

::: zone-end

#### Insomnia で Web API に対する承認済み要求を構成する

API 要求に使用するアクセス トークンを取得するには、次の手順に従います。

1. **Insomnia** アプリケーションを起動します。
2. **[新しい HTTP 要求]** を選択するか、"Ctrl + N" を使用して新しい HTTP 要求を作成できます。
3. [新しい要求] モーダルで、ドロップダウンから **GET** メソッドを選択します。
4. 要求 URL に、Web API によって公開されるエンドポイントの URL、`https://localhost:{port}/weatherforecast` を入力します。
5. **[認証]** ドロップダウン メニューから、**[OAuth 2.0]** を選択します。 これで、**OAuth 2.0** フォームが表示されます。
6. **OAuth 2.0** フォームに、次の値を入力します。

    | 設定 | 価値 |
    | --- | --- |
    | **付与タイプ** | **[Authorization Code] (認可コード)** を選びます |
    | **承認 URL** | `https://login.microsoftonline.com/{tenantId}/oauth2/v2.0/authorize``{tenantId}` を**ディレクトリ (テナント) ID** に置き換えます |
    | **アクセス トークン URL** | `https://login.microsoftonline.com/{tenantId}/oauth2/v2.0/token``{tenantId}` を**ディレクトリ (テナント) ID** に置き換えます |
    | **クライアント ID** | Web アプリ登録の **アプリケーション (クライアント) ID** 値 |
    | **クライアント シークレット** | Web アプリ登録のクライアント シークレットの**値** |
    | **リダイレクト URL** | 「`http://localhost`」と入力すると、リダイレクト URL が、Microsoft Entra ID に登録されているリダイレクト URI に設定されます。 |
    | **[詳細オプション]**&gt;**[スコープ]** | `api://{application_client_id}/Forecast.Read` Web アプリ登録に移動し、**[管理]** で、**[API のアクセス許可]** を選択し、**Forecast.Read** を選択します  テキスト ボックス内の、**[スコープ]** 値を含む値をコピーします |

##### アクセス トークンを取得し、Web API に要求を送信する

1. 値を入力したら、フォームの末尾で **[トークンをフェッチする]** を選択します。 これにより Insomnia のブラウザー ウィンドウが起動し、ユーザー資格情報での認証が行われ ます。 必ず、ブラウザーで Insomnia アプリケーションからのポップアップを許可してください。
2. 認証後、**[送信]** を選択して、要求を保護された Web API エンドポイントに送信します。

要求に有効なアクセス トークンが含まれている場合、期待される応答は 200 OK で、出力は次のようになります。

```json
[
  {
    "date": "YYYY-MM-DDTHH:MM:SS",
    "temperatureC": -16,
    "summary": "Scorching",
    "temperatureF": 4
  },
  {
    "date": "YYYY-MM-DDTHH:MM:SS",
    "temperatureC": 1,
    "summary": "Sweltering",
    "temperatureF": 33
  },
  {
    "date": "YYYY-MM-DDTHH:MM:SS",
    "temperatureC": 26,
    "summary": "Freezing",
    "temperatureF": 78
  },
  {
    "date": "YYYY-MM-DDTHH:MM:SS",
    "temperatureC": 54,
    "summary": "Mild",
    "temperatureF": 129
  },
  {
    "date": "YYYY-MM-DDTHH:MM:SS",
    "temperatureC": 11,
    "summary": "Bracing",
    "temperatureF": 51
  }
]
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-configure-app-instance-property-locks"} -->
## アプリケーションでアプリ インスタンス プロパティ ロックを構成する方法 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-app-instance-property-locks
- Service: identity-platform
- Article date: 2026-10-01
- Summary: アプリケーションの機密性の高いプロパティのプロパティ変更ロックを構成して、アプリのセキュリティを強化する方法。

アプリケーション インスタンス ロックは、アプリケーションのサービス プリンシパルの機密性の高いプロパティを変更できないようにロックできるようにする、Microsoft Entra IDの機能です。 これは、シングルテナント アプリケーションとマルチテナント アプリケーションの両方に適用されます。 この機能により、アプリケーション開発者は、これらのプロパティの構成を必要とするシナリオがアプリケーションでサポートされていない場合に、特定のプロパティをロックできます。

### 機密性の高いプロパティとは

次のプロパティ使用シナリオは、機密性が高いと見なされます。

- 使用の種類が `Sign` である資格情報。 これは、アプリケーションが SAML フローをサポートするシナリオです。
- 使用の種類が `Verify` である資格情報。 このシナリオでは、アプリケーションで OIDC クライアント資格情報フローがサポートされています。
- keyCredentials コレクションの公開キーの keyId を指定する `TokenEncryptionKeyId`。 構成した場合、Microsoft Entra ID は、このプロパティが指すキーを使用して、出力するすべてのトークンを暗号化します。 暗号化されたトークンを受け取るアプリケーション コードでは、対応する秘密キーを使用してトークンを復号化する必要があります。その後で、現在サインインしているユーザー用にトークンを使用できるようになります。

注

2026 年 6 月以降、新しいアプリケーションではプロパティ **ロックの有効化** 設定が既定で **有効** になっています。 ロック設定を確認して、アプリケーションで使用される機密性の高いプロパティが保護されていることを確認してください。

### アプリ インスタンスロックを構成する

アプリ インスタンスロックを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニューの **[設定]** アイコン  を使用して、[ **ディレクトリとサブスクリプション** ] メニューからアプリの登録を含むテナントに切り替えます。
3. **[Entra ID]**&gt;**[アプリの登録]** に移動します。
4. 構成するアプリケーションを選択します。
5. [**認証**] を選択し、[**アプリ インスタンスのプロパティ ロック**] セクションで [*構成*] を選択します。

    [Image: アプリ登録のアプリ インスタンス ロックのスクリーンショット。]
6. **[アプリ インスタンス のプロパティ ロック**] ウィンドウで、ロックの設定を入力します。 図の下の表では、各設定とそのパラメーターについて説明します。

    [Image: アプリ登録のアプリ インスタンス プロパティのロック コンテキスト ウィンドウのスクリーンショット。]

    | フィールド | 説明 |
    | --- | --- |
    | **プロパティ ロックを有効にする** | プロパティ ロックを有効にするかどうかを指定します。 |
    | **すべてのプロパティ** | すべての機密性の高いプロパティをロックし、各プロパティ シナリオを選択する必要をなくします。 |
    | **検証に使用される資格情報** | 検証に使用される資格情報プロパティを追加または更新する機能をロックします。 |
    | **トークンの署名に使用される資格情報** | トークンへの署名に使用される資格情報プロパティを追加または更新する機能をロックします。 |
    | **Token Encryption KeyId (トークン暗号化 KeyId)** | `tokenEncryptionKeyId` プロパティを変更する機能をロックします。 |
7. [ **保存] を** 選択して変更を保存します。

### Microsoft Graph を使用してアプリ インスタンスロックを構成する

アプリ インスタンス ロック機能は、**アプリケーション** オブジェクトの [servicePrincipalLockConfiguration](https://learn.microsoft.com/ja-jp/graph/api/resources/application) プロパティを使用して管理します。 詳細については、「 [サービス プリンシパルの機密性の高いプロパティをロックする」を](https://learn.microsoft.com/ja-jp/graph/tutorial-applications-basics#lock-sensitive-properties-for-service-principals)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-configure-publisher-domain"} -->
## アプリの発行元ドメインを構成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-publisher-domain
- Service: identity-platform
- Article date: 2023-04-27
- Summary: アプリの発行元ドメインを構成して、情報の送信先をユーザーに知らせる方法について説明します。

アプリの発行元ドメインは、情報が送信されている場所をユーザーに通知します。 パブリッシャー ドメインは、パブリッシャー検証 [の入力または前提条件](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview)としても機能します。 アプリが登録された日時とパブリッシャー検証の状態に応じて、[アプリケーションの同意プロンプト](https://learn.microsoft.com/ja-jp/entra/identity-platform/application-consent-experience)にユーザーに直接表示されます。 信頼のために情報が送信されている場所をユーザーに知らせるために、同意 UX のユーザーにアプリケーションの発行元ドメインが (発行元検証の状態に応じて) 表示されます。

アプリの同意プロンプトに、発行元ドメインまたは発行元の確認状態が表示されます。 表示される情報は、アプリが [マルチテナント アプリ](https://learn.microsoft.com/ja-jp/azure/architecture/guide/multitenant/overview)かどうか、アプリが登録されたとき、アプリの発行元の確認状態によって異なります。

### マルチテナント アプリについて

*マルチテナント アプリ* は、1 つの組織ディレクトリの外部にあるユーザー アカウントをサポートするアプリです。 たとえば、マルチテナント アプリでは、Microsoft Entra のすべての職場または学校アカウントがサポートされている場合や、Microsoft Entra の職場または学校アカウントと個人の Microsoft アカウントの両方をサポートしている場合があります。

### 既定の発行元ドメインの値を理解する

いくつかの要因によって、アプリの発行元ドメインに設定される既定値が決まります。

- アプリがテナントに登録されているかどうか。
- テナントにテナント検証済みドメインがあるかどうか。
- アプリの登録日。

#### テナントの登録とテナント検証済みドメイン

新しいアプリを登録すると、アプリの発行元ドメインが既定値に設定される場合があります。 既定値は、アプリが登録されている場所によって異なります。 発行元ドメインの値は、アプリがテナントに登録されているかどうかと、テナントにテナント検証済みドメインがあるかどうかによって特に異なります。

アプリにテナント検証ドメインがある場合、アプリの発行元ドメインは既定でテナントのプライマリ検証済みドメインになります。 アプリにテナント検証ドメインが存在せず、アプリがテナントに登録されていない場合、アプリの既定の発行元ドメインは null になります。

次の表では、シナリオ例を使用して、パブリッシャー ドメインの既定値を説明します。

| テナント検証済みドメイン | パブリッシャー ドメインの既定値 |
| --- | --- |
| 無効 | 無効 |
| `*.onmicrosoft.com` | `*.onmicrosoft.com` |
| - `*.onmicrosoft.com`- `domain1.com` - `domain2.com` (プライマリ) | `domain2.com` |

#### アプリの登録日

アプリの登録日によって、アプリの既定の発行元ドメインの値も決定されます。

マルチテナント アプリが 2019 年 5 月 21 日から 2020 年 11 月 30 日の間に *登録された場合*:

- アプリの発行元ドメインが設定されていない場合、または `.onmicrosoft.com`で終わるドメインに設定されている場合、アプリの同意プロンプトには、発行元ドメインの値 *未確認の* が表示されます。
- アプリに検証済みのアプリ ドメインがある場合は、同意プロンプトに検証済みドメインが表示されます。
- アプリが発行元の検証済みである場合、発行元ドメインには、状態を示す [青い *検証済みバッジ* が](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview) 表示されます。

2020年11月30日以降にマルチテナントが*として登録された場合*:

- アプリが発行元検証されていない場合は、アプリの同意プロンプトに未確認の 表示されます。 発行元ドメインに関連する情報は表示されません。
- アプリが発行元の検証済みの場合、アプリの同意プロンプトに、[青い *検証済みの* バッジ](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview)が表示されます。

##### 2019 年 5 月 21 日より前に作成されたアプリ

2019 年 5 月 21 日 *より前にアプリが*登録されている場合、発行元ドメインを設定していない場合でも、アプリの同意プロンプトに未確認の 表示されます。 ユーザーがアプリの同意プロンプトでこの情報を表示できるように、発行元ドメインの値を設定することをお勧めします。

### Microsoft Entra 管理センターで発行元ドメインを設定する

Microsoft Entra 管理センターを使用してアプリの発行元ドメインを設定するには:

1. Microsoft Entra 管理センター にサインインします。
2. 複数のテナントにアクセスできる場合は、右上にある [**設定]** アイコン  を使用し、[**ディレクトリとサブスクリプション] メニューからアプリが登録されているテナント** 選択します。
3. Microsoft Entra 管理センターで、 **Entra ID**&gt;**App 登録を参照します**。
4. 構成するアプリを検索して選択します。
5. **概要**のリソース メニューの [**管理]**で、[ブランド化] 選択します。
6. **パブリッシャー ドメイン**で、次のいずれかのオプションを選択します。

    - ドメインをまだ構成していない場合は、[ **ドメイン**の構成] を選択します。
    - ドメインを構成している場合は、[ドメイン 更新] を選択します。
7. アプリがテナントに登録されている場合は、次の 2 つのオプションから選択します。

    - **検証済みドメインの** を選択する
    - **新しいドメインの** を確認する

    ドメインがテナントに登録されていない場合は、アプリの新しいドメインを確認するオプションのみが表示されます。

#### アプリの新しいドメインを確認する

アプリの新しい発行元ドメインを確認するには:

1. *microsoft-identity-association.json*という名前のファイルを作成します。 次の JSON をコピーし、*microsoft-identity-association.json* ファイルに貼り付けます。

    ```json
    {
       "associatedApplications": [
          {
             "applicationId": "<your-app-id>"
          },
          {
             "applicationId": "<another-app-id>"
          }
       ]
     }
    ```
2. `<your-app-id>` をアプリのアプリケーション (クライアント) ID に置き換えます。 複数のアプリの新しいドメインを確認する場合は、関連するすべてのアプリ ID を使用します。
3. `https://<your-domain>.com/.well-known/microsoft-identity-association.json`でファイルをホストします。 `<your-domain>` を検証済みドメインの名前に置き換えます。
4. ドメイン **を確認および保存し、**を選択します。

ドメインを検証した後に検証に使用されるリソースを維持する必要はありません。 検証が完了したら、ホストされているファイルを削除できます。

#### 確認済みドメインを選択する

テナントに確認済みドメインがある場合は、**[確認済みドメインの選択]** ドロップダウンで、いずれかのドメインを選択します。

手記

コンテンツは逆シリアル化のために UTF-8 JSON として解釈されます。 返される必要があるサポートされる `Content-Type` ヘッダーは、`application/json`、`application/json; charset=utf-8`、または です。 他のヘッダーを使用すると、次のエラー メッセージが表示されることがあります。

`Verification of publisher domain failed. Error getting JSON file from https:///.well-known/microsoft-identity-association. The server returned an unexpected content type header value.`

### 発行元ドメインとアプリの同意プロンプト

発行元ドメインの構成は、ユーザーがアプリの同意プロンプトに表示する内容に影響します。 同意プロンプトのコンポーネントの詳細については、「[アプリケーションの同意エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/application-consent-experience)を理解する」を参照してください。

次の図は、2019 年 5 月 21 日より前に作成されたアプリのアプリ同意プロンプトに発行元ドメインがどのように表示されるかを示しています。

[Image: 2019 年 5 月 21 日より前に作成されたアプリの同意プロンプトの動作を示す図。]

2019 年 5 月 21 日から 2020 年 11 月 30 日までの間に作成されたアプリの場合、アプリの同意プロンプトで発行元ドメインがどのように表示されるかは、発行元ドメインとアプリの種類によって異なります。 次の図は、さまざまな構成の組み合わせに対する同意プロンプトに表示される内容を示しています。

[Image: 2019 年 5 月 21 日から 2020 年 11 月 30 日までの間に作成されたアプリの同意プロンプトの動作を示す図。]

2020 年 11 月 30 日以降に作成されたマルチテナント アプリの場合、アプリの同意プロンプトには発行元の確認状態のみが表示されます。 次の表では、アプリが検証されているかどうかに応じて、同意プロンプトに表示される内容について説明します。 シングルテナント アプリの同意プロンプトは変わりません。

[Image: 2020 年 11 月 30 日以降に作成されたアプリの同意プロンプトの結果を示す図。]

### パブリッシャー ドメインとリダイレクト URI

職場または学校アカウントを使用するか、Microsoft アカウント (マルチテナント) を使用してユーザーをサインインさせるアプリには、リダイレクト URI でいくつかの制限があります。

#### 単一のルート ドメインの制限

マルチテナント アプリの発行元ドメインの値が null に設定されている場合、アプリはリダイレクト URI の 1 つのルート ドメインの共有に制限されます。 たとえば、ルート ドメインの `contoso.com` がルート ドメインの `fabrikam.com`と一致しないため、次の値の組み合わせは許可されません。

```json
"https://contoso.com",  
"https://fabrikam.com",
```

#### サブドメインの制限

サブドメインは許可されますが、ルート ドメインを明示的に登録する必要があります。 たとえば、次の URI は 1 つのルート ドメインを共有しますが、組み合わせは許可されません。

```json
"https://app1.contoso.com",
"https://app2.contoso.com",
```

ただし、開発者がルート ドメインを明示的に追加した場合、組み合わせは許可されます。

```json
"https://contoso.com",
"https://app1.contoso.com",
"https://app2.contoso.com",
```

#### 制限の例外

次の場合は、単一ルート ドメインの制限の対象になりません。

- 単一ディレクトリ内のアカウントを対象とするシングルテナント アプリまたはアプリ。
- リダイレクト URI として localhost を使用します。
- カスタム スキーム (HTTP または HTTPS 以外) を持つリダイレクト URI。

### プログラムでパブリッシャー ドメインを構成する

現時点では、REST API または PowerShell を使用してパブリッシャー ドメインをプログラムで設定することはできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-convert-app-to-be-multi-tenant"} -->
## Microsoft Entra ID でシングルテナント アプリをマルチテナントに変換する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant
- Service: identity-platform
- Article date: 2024-11-13
- Summary: 既存のシングルテナント アプリをマルチテナント アプリに変換して、任意の Microsoft Entra テナントからのユーザーをサインインできるようにする方法について説明します。

サービスとしてのソフトウェア (SaaS) アプリケーションを多数の組織に提供する場合は、アプリケーションをマルチテナントに変換することで、すべての Microsoft Entra テナントからのサインインを受け入れるように構成することができます。 すべての Microsoft Entra テナントのユーザーは、アプリケーションで自分のアカウントを使用することに同意すれば、そのアプリケーションにサインインできるようになります。

独自のアカウント システムを持つ既存のアプリ (または他のクラウド プロバイダーからのその他のサインイン) の場合は、OAuth2、OpenID Connect、または Security Assertion Markup Language (SAML) を使用してサインイン コードを追加し、アプリケーションに [\[Microsoft アカウントでサインイン\] ボタン](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-branding-in-apps)を配置する必要があります。

このハウツー ガイドでは、シングル テナント アプリを Microsoft Entra マルチテナント アプリに変換するために必要な、次の 4 つの手順を実行します。

1. アプリケーション登録をマルチテナントに更新する
2. コードを更新して、要求を `/common` エンドポイントに送信するようにします
3. 複数の issuer 値を処理するようにコードを更新する
4. ユーザーおよび管理者の同意について理解し、コードに適切な変更を加える

いずれかのサンプルの使用を試す場合は、「[Azure AD と OpenID Connect を使用して Microsoft Graph を呼び出すマルチテナント SaaS Web アプリケーションを構築する](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/2-WebApp-graph-user/2-3-Multi-Tenant/README.md)」を参照してください

### 前提条件

- Microsoft Entra テナント。 お持ちでない場合は、「[クイック スタート: Microsoft Entra ID で新しいテナントを作成する](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant)」で作成できます。
- Microsoft ID プラットフォームに登録されたアプリケーション。 お持ちでない場合は、「[クイック スタート: Microsoft ID プラットフォームにアプリケーションを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)」で作成できます。
- [Microsoft Entra ID のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-and-multi-tenant-apps)に関する知識。
- アプリケーション コードを編集できる統合開発環境 (IDE)。

### 登録をマルチテナントに更新する

既定では、Microsoft Entra ID での Web アプリケーション/API 登録は、作成時点でシングルテナントです。 登録をマルチテナントにするには、[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインし、更新するアプリ登録を選択します。 アプリ登録を開いた状態で、**[認証]** ペインを選択し、**[サポートされるアカウントの種類]** セクションに移動します。 設定を **[任意の組織のディレクトリ内のアカウント]** に変更します。

Microsoft Entra 管理センターでシングル テナント アプリケーションが作成されると、**[概要]** ページに一覧表示される項目の 1 つが**アプリケーション ID URI** になります。 これは、プロトコル メッセージでアプリケーションが識別される方法の 1 つであり、いつでも追加できます。 シングル テナント アプリのアプリ ID URI は、そのテナント内でグローバルに一意であれば問題ありません。 これに対し、マルチテナント アプリの場合は、すべてのテナントでグローバルに一意である必要があります。これにより、Microsoft Entra ID がすべてのテナントでアプリを見つけられるようになります。

たとえば、テナントの名前が `contoso.onmicrosoft.com` である場合、有効なアプリケーション ID URI は、`https://contoso.onmicrosoft.com/myapp` のようになります。 アプリ ID URI がこのパターンに従っていないと、アプリケーションのマルチテナントとしての設定が失敗します。

### コードを更新して、要求を `/common` エンドポイントに送信するようにする

マルチテナント アプリケーションでは、アプリケーションがユーザーのサインイン元のテナントをすぐには認識できないため、要求をテナントのエンドポイントに送信することはできません。 代わりに、要求は Microsoft Entra のすべてのテナントにまたがる共通のエンドポイント (`https://login.microsoftonline.com/common`) に送信され、要求を処理するセントラル ハブとして機能します。

IDE でアプリを開き、コードを編集して、テナント ID の値を `/common` に変更してください。 SAML アプリの場合、これは ID プロバイダーの XML ファイルで構成できます。 このエンドポイントはテナントや発行者自体ではありません。 Microsoft ID プラットフォームは、`/common` エンドポイントで要求を受信すると、ユーザーのサインインを行い、それによってユーザーのサインイン元のテナントを特定します。 このエンドポイントは、Microsoft Entra ID でサポートされるすべての認証プロトコル (OpenID Connect、OAuth 2.0、SAML 2.0、WS-Federation) に対応しています。

その後のアプリケーションに対するサインイン応答には、ユーザーを表すトークンが含まれます。 アプリケーションは、トークンの issuer 値に基づいてユーザーのサインイン元のテナントを特定できます。 `/common` エンドポイントから応答が返された場合、トークン内の issuer 値はユーザーのテナントに対応しています。

注

実際には、マルチテナント アプリケーションには次の 2 つの機関があります。

- 任意の組織のディレクトリ (Microsoft Entra ディレクトリ) 内のアカウントと、個人用の Microsoft アカウント (Skype、Xbox など) を処理するアプリケーション向けの `https://login.microsoftonline.com/common`。
- 任意の組織ディレクトリ内のアカウント (任意の Microsoft Entra ディレクトリ) を処理するアプリケーション向けの `https://login.microsoftonline.com/organizations`。

このドキュメントの説明では、`common` を使用します。 ただし、アプリケーションで Microsoft 個人アカウントがサポートされていない場合は、`organizations` で置き換えることができます。

### 複数の issuer 値を処理するようにコードを更新する

Web アプリケーションと Web API は、Microsoft ID プラットフォームからトークンを受信して検証します。 ネイティブ クライアント アプリケーションでは、アクセス トークンを検証せず、トークンを不透明なものとして処理する必要があります。 これらのアプリケーションは、Microsoft ID プラットフォームにトークンを要求して受信し、それらを API に送信します。その後、API でトークンが検証されます。

マルチテナント アプリケーションは、トークンの検証時に追加のチェックを実行する必要があります。 マルチテナント アプリケーションは、`/organizations` または `/common` キー URL からのキー メタデータを使用するように構成されています。 アプリケーションでは、公開されたメタデータ内の `issuer` プロパティが、トークン内の `iss` 要求にテナント ID (`iss`) 要求が含まれていることを示す通常のチェックに加えて、トークン内の `tid` 要求と一致することを検証する必要があります。 詳細については、「[トークンの検証](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens#validate-tokens)」を参照してください。

### ユーザーおよび管理者の同意について理解し、コードに適切な変更を加える

Microsoft Entra ID のアプリケーションにユーザーがサインインするには、そのアプリケーションがユーザーのテナントに表示される必要があります。 このようにすることで、組織では、ユーザーがテナントからアプリケーションにサインインする場合に一意のポリシーを適用するなどの操作を行うことができます。 シングルテナント アプリケーションの場合は、[Microsoft Entra 管理センター](https://entra.microsoft.com)を通じて登録を使用できます。

マルチテナント アプリケーションの場合、アプリケーションの最初の登録は、開発者が使用する Microsoft Entra テナントに保存されます。 ユーザーが初めて別のテナントからこのアプリケーションにサインインすると、Microsoft Entra ID により、アプリケーションで要求されるアクセス許可に同意するかどうかを尋ねられます。 同意した場合、アプリケーションを表す "*サービス プリンシパル*" と呼ばれるものがユーザーのテナントに作成され、サインインを続行できます。 また、アプリケーションに対するユーザーの同意を記録するデリゲートが、ディレクトリに作成されます。 アプリケーションのアプリケーション オブジェクトおよびサービス プリンシパル オブジェクトの詳細と、それらの関係については、[アプリケーション オブジェクトとサービス プリンシパル オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)に関するページを参照してください。

[Image: 単一層アプリに対するユーザーの同意を示す図。]

アプリケーションから要求されるアクセス許可は、同意のエクスペリエンスに影響します。 Microsoft ID プラットフォームでは、次の 2 種類のアクセス許可がサポートされています。

- **委任**: このアクセス許可を付与されると、アプリケーションは、サインイン済みのユーザーとして、そのユーザーが実行可能な操作の一部を行うことができます。 たとえば、アプリケーションに対し、サインイン済みユーザーのカレンダーを読み取る委任アクセス許可を付与できます。
- **アプリケーション専用**: このアクセス許可は、アプリケーションの ID に直接付与されます。 たとえば、アプリケーションに、アプリケーションにサインインしているユーザーに関係なく、テナントのユーザーの一覧を読み取るアプリケーション専用アクセス許可を付与できます。

通常のユーザーが一部のアクセス許可に同意できる一方、それ以外のユーザーにはテナント管理者の同意が必要なものがあります。

ユーザーおよび管理者の同意の詳細については、「[管理者の同意ワークフローの構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-admin-consent-workflow)」を参照してください。

#### 管理者の同意

アプリケーション専用アクセス許可では、常にテナント管理者の同意が必要になります。 アプリケーションがアプリケーション専用アクセス許可を要求する場合に、ユーザーがそのアプリケーションにサインインしようとすると、このユーザーは同意できないことを示すエラー メッセージが表示されます。

一部の委任アクセス許可でも、テナント管理者の同意が必要になります。 たとえば、サインイン済みユーザーとして Microsoft Entra ID に書き戻しを行うアクセス許可には、テナント管理者の同意が必要です。 アプリケーション専用アクセス許可と同様に、管理者の同意が必要な委任アクセス許可を要求するアプリケーションに通常のユーザーがサインインしようとすると、アプリでエラーが発生します。 リソースを公開した開発者は、アクセス許可に管理者の同意が必要かどうかを決定します。この情報はリソースのドキュメントで確認できます。 [Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/permissions-reference) のアクセス許可に関するドキュメントには、管理者の同意が必要なアクセス許可が示されています。

アプリケーションで管理者の同意が必要なアクセス許可を使用する場合は、管理者が操作を開始できるボタンやリンクを追加することを検討してください。 通常、この操作に対してアプリケーションから送信される要求は OAuth2/OpenID Connect 承認要求ですが、この要求には `prompt=consent` クエリ文字列パラメーターも含まれています。 管理者が同意し、ユーザーのテナントにサービス プリンシパルが作成されると、以降のサインイン要求では `prompt=consent` パラメーターは不要になります。 管理者は要求されたアクセス許可を承認しているため、テナント内の他のユーザーが同意を求められることはありません。

テナント管理者は、通常ユーザーによるアプリケーションへの同意を無効にすることができます。 通常ユーザーによる同意が無効化された場合、テナントでアプリケーションを使用するには常に管理者の同意が必要になります。 Microsoft Entra 管理センターでは、エンドユーザーの同意を無効にしてアプリケーションをテストできます。 **[エンタープライズ アプリケーション]**&gt;[\[同意とアクセス許可\]](https://entra.microsoft.com/#view/Microsoft_AAD_IAM/ConsentPoliciesMenuBlade/%7E/UserSettings) で、**[ユーザーの同意を許可しない]** を選択します。

`prompt=consent` パラメーターは、管理者の同意を必要としないアクセス許可を要求するアプリケーションでも使用できます。 ユース ケースの一例は、テナント管理者が 1 回 "サインアップする" と、その時点からは他のユーザーが同意を求められないというエクスペリエンスを必須とするアプリケーションです。

アプリケーションが管理者の同意を必要とし、管理者はサインインしたが、`prompt=consent` パラメーターが送信されない場合、管理者がアプリケーションへの同意に成功すると、**自分のユーザー アカウントについてのみ**適用されます。 通常のユーザーは、アプリケーションへのサインインも同意も実行できません。 この機能は、他のユーザーのアクセスを許可する前に、テナント管理者がアプリケーションを調査できるようにしたい場合に役立ちます。

#### 同意と多層アプリケーション

一部のアプリケーションは多層化されており、それぞれの層が個別の Microsoft Entra ID 登録で表されている場合があります。 たとえば、Web API を呼び出すネイティブ アプリケーションや、Web API を呼び出す Web アプリケーションなどです。 どちらの場合でも、クライアント (ネイティブ アプリケーションまたは Web アプリケーション) は、リソース (Web API) を呼び出すアクセス許可を要求します。 クライアントがユーザーのテナントに対する同意を得られるようにするには、アクセス許可を要求されるリソースがすべて、あらかじめユーザーのテナントに存在する必要があります。 この条件が満たされない場合、Microsoft Entra ID は、最初にリソースを追加する必要があることを示すエラーを返します。

##### 1 つのテナント内の複数の階層

論理アプリケーションが 2 つ以上のアプリケーション登録 (別々のクライアントとリソースなど) で構成されている場合は、問題が発生する場合があります。 たとえば、まず外部テナントにリソースを追加するにはどうすればいいのでしょうか。 Microsoft Entra ID では、ワンステップでクライアントとリソースが同意されるようにすることによって、この状況に対応します。 ユーザーには、同意ページにクライアントとリソースの両方によって要求されたアクセス許可の合計が表示されます。 この動作を有効にするには、リソースのアプリケーション登録で、`knownClientApplications`に  というクライアントのアプリ ID を含める必要があります。 次に例を示します。

```json
"knownClientApplications": ["12ab34cd-56ef-78gh-90ij11kl12mn"]
```

デモについては、[マルチテナント アプリケーションのサンプル](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/blob/master/2-WebApp-graph-user/2-3-Multi-Tenant/README.md)を参照してください。 次の図は、1 つのテナントに登録されている多層アプリケーションのための同意の概要を示しています。

[Image: 多層の既知のクライアント アプリへの同意を示す図。]

##### 複数のテナント内の複数の階層

同様のケースは、アプリケーションの各層を別々のテナントに登録する場合にも起こります。 たとえば、Exchange Online API を呼び出すネイティブ クライアント アプリケーションを構築する場合を考えます。 ネイティブ アプリケーションを開発するため、また開発後にユーザーのテナントでこのネイティブ アプリケーションを実行するために、Exchange Online のサービス プリンシパルが存在する必要があります。 ここでは、開発者とユーザーは、テナントでサービス プリンシパルを作成するために、Exchange Online を購入する必要があります。

API が Microsoft 以外の組織によって作成されている場合、この API の開発者は、ユーザーがユーザーのテナントでアプリケーションに対して同意する手段を提供する必要があります。 推奨される設計は、サード パーティー開発者向けに、サインアップを実装する Web クライアントとしても機能できるような API を構築することです。 次のことが行えます。

1. 前述のセクションに従って、API がマルチテナント アプリケーションの登録およびコード要件を実装していることを確認します。
2. API のスコープとロールの公開に加えて、登録に、"サインインとユーザー プロファイルの読み取り" アクセス許可 (既定で提供) が含まれていることを確認します。
3. Web クライアントでサインイン/サインアップ ページを実装し、管理者の同意のガイダンスに従います。
4. ユーザーがアプリケーションに同意し、テナントにサービス プリンシパルと同意の委任のリンクが作成されたら、ネイティブ アプリケーションで API のトークンを取得できます。

次の図に、異なるテナントに登録されている多層アプリケーションの同意の概要を示します。

[Image: 多層の複数のアプリへの同意を示す図。]

#### 同意の取り消し

ユーザーおよび管理者は、次の方法により、いつでもアプリケーションに対する同意を取り消すことができます。

- ユーザーは、[\[アクセス パネル アプリケーション\]](https://myapps.microsoft.com) リストから個々のアプリケーションを削除することで、アプリケーションへのアクセス許可を取り消します。
- 管理者は、Microsoft Entra 管理センターの [\[エンタープライズ アプリケーション\]](https://entra.microsoft.com/#view/Microsoft_AAD_IAM/StartboardApplicationsMenuBlade/%7E/AppAppsPreview) セクションを使用してアプリケーションを削除することで、アプリケーションへのアクセス許可を取り消します。 アプリケーションを選択し、**[アクセス許可]** タブに移動してアクセスを取り消します。

管理者が、テナント内のすべてのユーザーについてアプリケーションへの同意を行った場合、ユーザーが個別にアクセス許可を取り消すことはできません。 アクセス許可を取り消すことができるのは管理者のみであり、取り消しの対象はすべてのアプリケーションのみになります。

### マルチテナント アプリケーションとアクセス トークンのキャッシュ

マルチテナント アプリケーションでは、Microsoft Entra ID で保護されている API を呼び出すアクセス トークンを取得することもできます。 マルチテナント アプリケーションで Active Directory 認証ライブラリ (MSAL) を使用する際によくあるエラーは、最初に `/common` を使用してユーザーのトークンを要求し、応答を受信した後で、同じユーザーの後続のトークンも `/common` を使用して要求することです。 Microsoft Entra ID からの応答は `/common` ではなくテナントから送信されるため、MSAL ではトークンがテナントから送信されたものとしてキャッシュされます。 ユーザーのアクセス トークンを取得するためのその後の `/common` への呼び出しでは、キャッシュ エントリが見つからないため、ユーザーはもう一度サインインするように求められます。 キャッシュが見つからない問題を回避するために、サインイン済みのユーザーに対する以降の呼び出しは、テナントのエンドポイントに向けて行われるようにしてください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-create-self-signed-certificate"} -->
## 自己署名公開証明書を作成してアプリケーションを認証する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-create-self-signed-certificate
- Service: identity-platform
- Article date: 2025-01-14
- Summary: 自己署名公開証明書を作成してアプリケーションを認証します。

Microsoft Entra ID では、サービス プリンシパル用に、**パスワードベースの認証** (アプリ シークレット) と**証明書ベースの認証**という 2 種類の認証がサポートされています。 アプリ シークレットは Azure portal や、 Microsoft Graph のような Microsoft API を使用して簡単に作成できますが、有効期間が長く、証明書ほど安全ではありません。 そのため、アプリケーションではシークレットではなく証明書を使用することをお勧めします。

テストでは、証明機関 (CA) で署名された証明書ではなく、自己署名公開証明書を使用できます。 この方法では、PowerShell を使用して自己署名証明書を作成およびエクスポートします。

注意事項

自己署名証明書は、信頼されたサード パーティ CA によって署名されていないデジタル証明書です。 自己署名証明書は、Web サイトまたはソフトウェアの署名を担当する企業または開発者によって作成、発行、署名されます。 このため、自己署名証明書は、公開 Web サイトやアプリケーションでは安全でないと見なされます。

PowerShell を使用して証明書を作成する際には、暗号化アルゴリズムやハッシュ アルゴリズム、証明書の有効期間、ドメイン名などのパラメーターを指定できます。 その後、秘密キーを含めるかどうかをアプリケーションのニーズに応じて選択したうえで、証明書をエクスポートできます。

認証セッションを開始するアプリケーションでは秘密キーが必要ですが、認証を確認するアプリケーションでは公開キーが必要です。 そのため、PowerShell デスクトップ アプリから Microsoft Entra ID に対して認証を行っている場合は、公開キー (*.cer* ファイル) のみをエクスポートして Azure portal にアップロードします。 PowerShell アプリではローカル証明書ストアの秘密キーを使用して認証を開始し、Microsoft Graph のような Microsoft API を呼び出すためのアクセス トークンを取得します。

アプリケーションが、Azure Automation などの別のマシンから実行されている場合もあります。 このシナリオでは、公開キーと秘密キーのペアをローカル証明書ストアからエクスポートして、公開キーは Azure portal に、秘密キー (*.pfx* ファイル) は Azure Automation にアップロードします。 Azure Automation で実行されているアプリケーションでは、秘密キーを使用して認証を開始し、Microsoft Graph のような Microsoft API を呼び出すためのアクセス トークンを取得します。

この記事では、`New-SelfSignedCertificate` PowerShell コマンドレットを使用して自己署名証明書を作成し、`Export-Certificate` コマンドレットを使用して、簡単にアクセスできる場所にエクスポートします。 これらのコマンドレットは、最新バージョンの Windows (Windows 8.1 以降、および Windows Server 2012R2 以降) に組み込まれています。 自己署名証明書には、以下の構成があります。

- 2,048 ビットのキー長。 より長い値がサポートされていますが、セキュリティとパフォーマンスの組み合わせが最適な 2,048 ビットのサイズを強くお勧めします。
- RSA 暗号アルゴリズムを使用します。 Microsoft Entra ID では現在、RSA のみがサポートされています。
- 証明書は、SHA256 ハッシュ アルゴリズムで署名されています。 Microsoft Entra ID では、SHA384 および SHA512 ハッシュ アルゴリズムで署名された証明書もサポートされています。
- 証明書は 1 年間のみ有効です。
- 証明書は、クライアントとサーバーの両方の認証で使用できるようにサポートされています。

証明書の開始日と有効期限、およびその他のプロパティをカスタマイズするには、「[New-SelfSignedCertificate](https://learn.microsoft.com/ja-jp/powershell/module/pki/new-selfsignedcertificate?view=windowsserver2019-ps&preserve-view=true)」を参照してください。

### 公開証明書を作成してエクスポートする

この方法を使用して作成した証明書を使用して、自分のマシンで実行されているアプリケーションから認証を行います。 たとえば、PowerShell から認証します。

PowerShell プロンプトで次のコマンドを実行し、PowerShell コンソール セッションを開いたままにします。 `{certificateName}` を、証明書に付ける名前に置き換えます。

```powershell
$certname = "{certificateName}"    ## Replace {certificateName}
$cert = New-SelfSignedCertificate -Subject "CN=$certname" -CertStoreLocation "Cert:\CurrentUser\My" -KeyExportPolicy Exportable -KeySpec Signature -KeyLength 2048 -KeyAlgorithm RSA -HashAlgorithm SHA256

```

前のコマンドの `$cert` 変数には現在のセッションの証明書が格納されるため、それをエクスポートできます。

次のコマンドでは、証明書を *.cer* 形式でエクスポートします。 また、*.pem*、*.crt* など、Azure portal でサポートされている他の形式でエクスポートすることもできます。

```powershell

Export-Certificate -Cert $cert -FilePath "C:\Users\admin\Desktop\$certname.cer"   ## Specify your preferred location

```

これで、証明書を Azure portal にアップロードする準備ができました。 アップロードが完了したら、アプリケーションの認証に使用する証明書の拇印を取得します。

### (オプション): 秘密キーがある公開証明書をエクスポートする

アプリケーションが別のコンピューターやクラウド (Azure Automation など) から実行される場合は、秘密キーも必要になります。

前のコマンドに続いて、証明書の秘密キーのパスワードを作成し、変数に保存します。 `{myPassword}` を、証明書の秘密キーを保護するために使用するパスワードに置き換えてください。

```powershell

$mypwd = ConvertTo-SecureString -String "{myPassword}" -Force -AsPlainText  ## Replace {myPassword}

```

`$mypwd` 変数に格納したパスワードを使用して、秘密キーを保護し、エクスポートします。次のコマンドを使用します。

```powershell

Export-PfxCertificate -Cert $cert -FilePath "C:\Users\admin\Desktop\$certname.pfx" -Password $mypwd   ## Specify your preferred location

```

これで、証明書 (*.cer* ファイル) を Azure portal にアップロードする準備ができました。 秘密キー (*.pfx* ファイル) は暗号化され、他の関係者からは読み取ることができません。 アップロードが完了したら、証明書の拇印を取得します。これは、アプリケーションの認証に使用できます。

### 省略可能なタスク: キーストアから証明書を削除する。

次のコマンドを実行して証明書の拇印を取得することで、個人用ストアからキー ペアを削除できます。

```powershell

Get-ChildItem -Path "Cert:\CurrentUser\My" | Where-Object {$_.Subject -Match "$certname"} | Select-Object Thumbprint, FriendlyName

```

次に、表示されている拇印をコピーし、それを使用して証明書とその秘密キーを削除します。

```powershell

Remove-Item -Path Cert:\CurrentUser\My\{pasteTheCertificateThumbprintHere} -DeleteKey

```

#### 証明書の有効期限を確認する

上の手順に従って作成した自己署名証明書には、有効期限があります。 Azure portal の **[アプリの登録]** セクションで、**[証明書とシークレット]** 画面に証明書の有効期限が表示されます。 Azure Automation を使用している場合は、Automation アカウントの **[証明書]** 画面に証明書の有効期限が表示されます。 前の手順に従って、新しい自己署名証明書を作成します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-create-service-principal-portal"} -->
## Microsoft Entra アプリを登録し、サービス プリンシパルを作成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-create-service-principal-portal
- Service: identity-platform
- Article date: 2025-05-26
- Summary: Azure Resource Manager でロールベースのアクセス制御を使用してリソースへのアクセスを管理するために、新しいMicrosoft Entra アプリとサービス プリンシパルを作成します。

この記事では、ロールベースのアクセス制御 (RBAC) で使用できる、Microsoft Entra のアプリケーションとサービス プリンシパルを作成する方法について説明します。 Microsoft Entra ID に新しいアプリケーションを登録すると、アプリの登録用にサービス プリンシパルが自動的に作成されます。 サービス プリンシパルは、Microsoft Entra テナント内のアプリの ID です。 リソースへのアクセスはサービス プリンシパルに割り当てられているロールによって制限されるため、どのリソースに、どのレベルでアクセスできるかを制御することができます。 セキュリティ上の理由から、自動化ツールにはユーザー ID でのサインインを許可するのではなく、常にサービス プリンシパルを使用することを推奨します。

この例は、1 つの組織内で使用される基幹業務アプリケーションに適用できます。 [Azure PowerShell](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-authenticate-service-principal-powershell) または [Azure CLI](https://learn.microsoft.com/ja-jp/cli/azure/create-an-azure-service-principal-azure-cli) を使用してサービス プリンシパルを作成することもできます。

重要

サービス プリンシパルを作成する代わりに、アプリケーション ID 用に Azure リソースのマネージド ID を使用することを検討します。 コードが、マネージド ID をサポートするサービス上で実行され、Microsoft Entra 認証をサポートするリソースにアクセスする場合、マネージド ID は優れた選択肢となります。 現在サポートされているサービスなど、Azure リソースのマネージド ID の詳細については、「 [Azure リソースのマネージド ID とは」を](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview)参照してください。

アプリの登録、アプリケーション オブジェクト、およびサービス プリンシパル間の関係の詳細については、 [Microsoft Entra ID のアプリケーション オブジェクトとサービス プリンシパル オブジェクトを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)。

### 前提条件

Microsoft Entra テナントにアプリケーションを登録するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーションを Microsoft Entra テナントに登録し、Azure サブスクリプションでそのアプリケーションにロールを割り当てるために、十分なアクセス許可。 これらのタスクを完了するには、`Application.ReadWrite.All` アクセス許可が必要です。

### Microsoft Entra ID にアプリケーションを登録し、サービス プリンシパルを作成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**App registrations** に移動し、[**新しい登録**] を選択します。
3. *アプリケーションに例アプリ*などの名前を付けます。
4. [ **サポートされているアカウントの種類**] *で、[この組織のディレクトリ内のアカウントのみ*] を選択します。
5. [ **リダイレクト URI**] で、作成するアプリケーションの種類として **[Web** ] を選択します。 アクセス トークンの送信先の URI を入力します。
6. [ **登録**] を選択します。

    [Image: アプリケーション登録ページを示すスクリーンショット。]

### アプリケーションにロールを割り当てる

サブスクリプション内のリソースにアクセスするには、アプリケーションにロールを割り当てる必要があります。 これは、Azure portal を使用して行う必要があります。 どのロールがそのアプリケーションに適切なアクセス許可を提供するかを判断します。 使用可能なロールの詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles)参照してください。

スコープは、サブスクリプション、リソース グループ、またはリソースのレベルで設定できます。 アクセス許可は、スコープの下位レベルに継承されます。

1. [Azure portal](https://portal.azure.com) にサインインします。
2. 画面の上部にある検索バーで、[サブスクリプション] を検索して選択 **します**。
3. 新しいウィンドウで、変更するサブスクリプションを選択します。 探しているサブスクリプションが表示されない場合は、 **グローバル サブスクリプション フィルターを選択します**。 必要なサブスクリプションがテナントで選択されていることを確認してください。
4. 左側のウィンドウで、[ **アクセス制御 (IAM)]** を選択します。
5. [ **追加]** を選択し、[ **ロールの割り当ての追加]** を選択します。
6. [ **ロール** ] タブで、一覧からアプリケーションに割り当てるロールを選択し、[ **次へ**] を選択します。
7. [ **メンバー** ] タブの [ **アクセスの割り当て]** で、[ **ユーザー、グループ、またはサービス プリンシパル**] を選択します。
8. [ **メンバーの選択] を選択します**。 既定では、Microsoft Entra アプリケーションは、使用可能なオプションに表示されません。 アプリケーションを検索するには、名前で検索します。
9. [選択] ボタンを **選択** し、[ **確認と割り当て**] を選択します。

    [Image: ロールの割り当てと、メンバーの追加方法の強調表示を示すスクリーンショット。]

サービス プリンシパルが設定されました。 それを使用してスクリプトまたはアプリの実行を開始できます。 サービス プリンシパル (アクセス許可、ユーザーが同意したアクセス許可、同意したユーザーの確認、アクセス許可の確認、サインイン情報の表示など) を管理するには、 **エンタープライズ アプリケーション**に移動します。

次のセクションでは、プログラムでサインインするときに必要な値を取得する方法を示します。

### アプリケーションにサインインする

プログラムでサインインするときは、認証要求でディレクトリ (テナント) ID とアプリケーション (クライアント) ID を渡します。 証明書または認証キーも必要です。 ディレクトリ ID とアプリケーション ID を取得するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)**のホーム ページを**開きます。
2. **Entra ID**&gt;**App 登録**に移動し、アプリケーションを選択します。
3. アプリの [概要] ページで、ディレクトリ (テナント) ID 値をコピーし、アプリケーション コードに保存します。
4. アプリケーション (クライアント) ID 値をコピーし、アプリケーション コードに保存します。

### 認証の設定

サービス プリンシパルで使用できる認証には、パスワードベースの認証 (アプリケーション シークレット) と証明書ベースの認証の 2 種類があります。 *証明機関によって発行された信頼された証明書を使用することをお勧めします*が、アプリケーション シークレットを作成したり、テスト用に自己署名証明書を作成したりすることもできます。

#### オプション 1 (推奨): 証明機関によって発行された信頼された証明書をアップロードする

証明書ファイルをアップロードする。

1. **Entra ID**&gt;**App 登録**に移動し、アプリケーションを選択します。
2. **[証明書とシークレット**] を選択します。
3. [ **証明書**] を選択し、[ **証明書のアップロード** ] を選択し、アップロードする証明書ファイルを選択します。
4. **追加**を選択します。 証明書がアップロードされると、サムプリント、開始日、有効期限の値が表示されます。

アプリケーション登録ポータルでアプリケーションに証明書を登録した後、 [機密クライアント アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios#single-page-public-client-and-confidential-client-applications) コードで証明書を使用できるようにします。

#### オプション 2: テストのみ: 自己署名証明書を作成してアップロードする

必要に応じて、テスト目的でのみ自己署名証明書を作成できます。 自己署名証明書を作成するには、Windows PowerShell を開き、次のパラメーターを指定 [して New-SelfSignedCertificate](https://learn.microsoft.com/ja-jp/powershell/module/pki/new-selfsignedcertificate) を実行して、コンピューター上のユーザー証明書ストアに証明書を作成します。

```powershell
$cert=New-SelfSignedCertificate -Subject "CN=DaemonConsoleCert" -CertStoreLocation "Cert:\CurrentUser\My"  -KeyExportPolicy Exportable -KeySpec Signature
```

Windows コントロール パネルからアクセスできる [ユーザー証明書の管理](https://learn.microsoft.com/ja-jp/dotnet/framework/wcf/feature-details/how-to-view-certificates-with-the-mmc-snap-in) MMC スナップインを使用して、この証明書をファイルにエクスポートします。

1. **[スタート**] メニューから [**実行**] を選択し、「**certmgr.msc**」と入力します。 現在のユーザーの証明書マネージャー ツールが表示されます。
2. 証明書を表示するには、左側のウィンドウの [ **証明書 - 現在のユーザー** ] で、[ **個人用** ] ディレクトリを展開します。
3. 作成した証明書を右クリックし、[ **すべてのタスク- &gt;Export**] を選択します。
4. 証明書のエクスポート ウィザードに従います。

証明書をアップロードするには、次の手順に従います。

1. **Entra ID**&gt;**App 登録**に移動し、アプリケーションを選択します。
2. **[証明書とシークレット**] を選択します。
3. [ **証明書**] を選択し、[ **証明書のアップロード** ] を選択し、証明書 (既存の証明書またはエクスポートした自己署名証明書) を選択します。
4. **追加**を選択します。

アプリケーション登録ポータルでアプリケーションに証明書を登録した後、 [機密クライアント アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios#single-page-public-client-and-confidential-client-applications) コードで証明書を使用できるようにします。

#### 手順 3: 新しいクライアント シークレットを作成する

証明書を使わないことを選んだ場合は、新しいクライアント シークレットを作成できます。

1. **Entra ID**&gt;**App 登録**に移動し、アプリケーションを選択します。
2. **[証明書とシークレット**] を選択します。
3. [ **クライアント シークレット**] を選択し、[ **新しいクライアント シークレット**] を選択します。
4. シークレットの説明と期間を指定します。
5. **追加**を選択します。

クライアント シークレットを保存すると、クライアント シークレットの値が表示されます。 これは一度しか表示されないので、この値をコピーし、アプリケーションから取得できる場所 (通常、アプリケーションが `clientId` や `authority` のような値をソース コードに保持する場所) に格納してください。 アプリケーションとしてサインインするアプリケーションのクライアント ID と共にシークレット値を指定します。

### リソースに対するアクセス ポリシーを構成する

アプリケーションからアクセスする必要があるリソースに対する追加のアクセス許可の構成が必要になる場合があります。 たとえば、 [キー コンテナーのアクセス ポリシーを更新](https://learn.microsoft.com/ja-jp/azure/key-vault/general/security-features#privileged-access) して、アプリケーションがキー、シークレット、または証明書にアクセスできるようにする必要もあります。

アクセス ポリシーを構成するには:

1. [Azure portal](https://portal.azure.com) にサインインします。
2. キー コンテナーを選択し、[ **アクセス ポリシー**] を選択します。
3. [ **アクセス ポリシーの追加]** を選択し、アプリケーションに付与するキー、シークレット、証明書のアクセス許可を選択します。 以前に作成したサービス プリンシパルを選択します。
4. [ **追加]** を選択してアクセス ポリシーを追加し、[ **保存]** を選択します。

    [Image: アクセス ポリシーを追加する]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-get-list-of-all-auth-library-apps"} -->
## 方法: テナントで Active Directory 認証ライブラリ (ADAL) を使用しているすべてのアプリの一覧を取得する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-get-list-of-all-auth-library-apps
- Service: identity-platform
- Article date: 2024-05-01
- Summary: この攻略ガイドでは、テナントで ADAL を使用しているすべてのアプリの完全な一覧を取得します。

この記事では、Azure Monitor ブックを使用して、テナントで ADAL を使用するすべてのアプリの一覧を取得する方法について説明します。

Azure Active Directory 認証ライブラリ (ADAL) は非推奨です。 ADAL に代わる Microsoft 認証ライブラリ (MSAL) に移行することを強くお勧めします。 Microsoft **は、ADAL の新機能とセキュリティ修正プログラムをリリースしなくなりました**。 ADAL を使用するアプリケーションでは最新のセキュリティ機能を利用できず、将来のセキュリティ上の脅威に対して脆弱なままです。 ADAL を使用する既存のアプリケーションがある場合は、必ず [MSAL に移行](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-migration)してください。

### サインイン ブック

ブックは、Microsoft Entra ログで使用できる情報を収集して視覚化するクエリのセットです。 [サインイン ログ スキーマの詳細については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-azure-monitor-sign-ins-log-schema)。

Microsoft Entra 管理センターのサインイン ブックには、対話型、非対話型、サービス プリンシパルのサインインなど、さまざまな種類のサインイン イベントのログが統合されています。この集計では、テナント全体での ADAL アプリケーションの使用に関する詳細な分析情報が提供され、ADAL アプリケーションの移行を完全に理解して管理するのに役立ちます。

以下では、ブックへのアクセスに関する包括的な手順を示し、その後、アプリケーションの一覧を視覚化するための効果的な方法を示します。

### 手順 1: Microsoft Entra サインイン イベントを Azure Monitor に送信する

Microsoft Entra ID では、サインイン イベントは既定で Azure Monitor に送信されません。これは Azure Monitor のサインイン ブックで必要になります。

[「Microsoft Entra サインインと監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-integrate-activity-logs-with-azure-monitor-logs)を Azure Monitor と統合する」の手順に従って、Azure Monitor にサインイン イベントを送信するように AD を構成します。 **診断設定**の構成手順**で、[SignInLogs**] チェック ボックスをオンにします。

Azure Monitor にイベントを送信するように Microsoft Entra ID を構成する "前" に発生したサインイン イベントは、サインイン ブックに表示されません。

### 手順 2: Microsoft Entra 管理センターでサインイン ブックにアクセスする

Microsoft Entra サインインと監査ログを、Azure Monitor 統合に指定されている Azure Monitor と統合したら、サインイン ブックにアクセスします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;**Monitoring & health**&gt;**Workbooks** に移動します。
3. **使用法**セクションで、**サインイン**ワークブックを開きます。

[Image: Microsoft Entra 管理センターのワークブックインターフェースでサインイン ワークブックがハイライトされているスクリーンショット。]

### 手順 3: ADAL を使用するアプリを識別する

[サインイン ブック] ページの下部にあるテーブルには、過去 30 日間にアクティブだった ADAL アプリが一覧表示されます。 ダウンロード ボタンを選択して、これらのアプリのリストをエクスポートすることもできます。 MSAL を使用するようにこれらのアプリを更新します。

[Image: Active Directory 認証ライブラリを使用するアクティブなアプリが表示されているサインイン ブックのスクリーンショット。]

ADAL を使用しているアプリがない場合、ブックのこのセクションには、次に示すようなビューが表示されます。

[Image: アプリが Active Directory 認証ライブラリを使用していない場合のサインイン ブックのスクリーンショット。]

ブックの次のセクションには、すべてのアプリのサインイン データが表示されます。 これには、場所やデバイスを含むアプリとサインインのアクティビティの合計数が含まれます。

[Image: アプリの詳細なサインイン情報を示すサインイン ブックのスクリーンショット。]

### 手順 4: 詳細を調べ、アプリケーションの使用と認証データを分析する

テナント内の ADAL アプリケーションの影響を十分に評価するには、単なる識別以外の詳細なデータを分析することが重要です。

- **アプリケーション ID**: 各アプリケーションの一意の識別子。
- **アプリの表示名**: 組織全体でアプリを簡単に識別するのに役立つアプリケーションの名前。
- **SigninCount**: アプリケーションごとのサインインの数。
- **ADAL バージョン**: アプリケーションで使用される ADAL の特定のバージョン。
- **IP アドレス**: サインイン試行の送信元のクライアントの IP アドレスを表示します。
- **場所**: 市区町村、都道府県、国/地域、およびサインイン要求が行われた場所を提供します。
- **デバイスによるサインイン: 特定**のバージョンを含むデバイスの OS の詳細を共有します。

この拡張データ ビューにアクセスするには、ブック内でカスタム フィルターとクエリを適用します。 この情報は、重要なアプリケーションを特定するのに役立つだけでなく、その使用と公開レベルに基づいてアプリケーションを優先順位付けすることで、移行戦略を計画するのにも役立ちます。

### 手順 5: ADAL アプリケーションを更新する

ADAL を使用してアプリケーションを特定したら、MSAL への更新に進みます。 移行プロセスは、使用しているアプリケーションの種類によって異なります。 アプリケーションの種類ごとに、以下に示すガイドラインに従ってください。

**シングルページ アプリ (SPA)**

- [ADAL.js から MSAL.jsまで](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-compare-msal-js-and-adal-js)

**Web アプリ**

- [ADAL ノードから MSAL ノードへ](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-node-migration)
- [ADAL.NET から MSAL.NET へ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/msal-net-migration)

**ウェブAPI**

- [ADAL Java から MSAL Java へ](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/migrate-adal-msal-java)
- [ADAL Python から MSAL Python へ](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/migrate-python-adal-msal)
- [ADAL.NET から MSAL.NET へ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/msal-net-migration)

**デスクトップ アプリ**

- [ADAL Java から MSAL Java へ](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/migrate-adal-msal-java)
- [ADAL Python から MSAL Python へ](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/migrate-python-adal-msal)
- [ADAL.NET から MSAL.NET へ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/msal-net-migration)

**モバイル アプリ**

- [ADAL.Android から MSAL.Android へ](https://learn.microsoft.com/ja-jp/entra/identity-platform/migrate-android-adal-msal)
- [ADAL.iOS から MSAL.iOS](https://learn.microsoft.com/ja-jp/entra/msal/objc/migrate-objc-adal-msal)

**サービス/デーモン アプリ**

- [ADAL Python から MSAL Python へ](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/migrate-python-adal-msal)
- [ADAL.NET から MSAL.NET へ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/msal-net-migration)
- [ADAL ノードから MSAL ノードへ](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-node-migration)
- [ADAL Java から MSAL Java へ](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/migrate-adal-msal-java)

### 手順 6: 移行が成功したことを監視して検証する

手順 4 の詳細なデータを使用すると、アプリケーションの MSAL への移行プロセスの優先度付けと管理を効果的に行うことができます。 このデータを使用してサインイン シナリオを調査し、スムーズな移行を実現する方法を次に示します。

- **優先順位付け**: `SigninCount` が多く、 `ADAL Version` が古いアプリケーションは、使用率が高く、リスクが高くなる可能性があるため、優先順位を付ける必要があります。 これらのアプリケーションを最初に移行して、組織にとって最も重大なリスクを最小限に抑えます。
- **セキュリティ分析**: `IP Address` を使用してサインイン パターンを検出します。 たとえば、ユーザーまたは組織が所有するサービスからサインイン要求が行われ、通話元を識別する場合などです。
- **互換性チェック**: 移行する前に、アプリケーションによって使用される `ADAL Version` を評価します。 一部のバージョンでは、特定の MSAL 機能に関する既知の問題が発生している可能性があります。 これらの違いを理解することは、機能の中断を最小限に抑える移行の計画に役立ちます。
- **テスト シナリオ**: MSAL に更新した後、監視して移行前と移行後の動作を比較します。 この比較は、移行が成功し、新しい環境でアプリケーションが期待どおりに動作することを確認するのに役立ちます。

サインイン ブックの詳細なデータを活用すると、組織は ADAL から MSAL への移行を戦略的に計画および実行し、中断を最小限に抑え、堅牢なセキュリティ プロトコルを維持できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-handle-samesite-cookie-changes-chrome-browser"} -->
## Chrome ブラウザーにおける SameSite Cookie の変更を処理する方法 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-handle-samesite-cookie-changes-chrome-browser
- Service: identity-platform
- Article date: 2024-02-09
- Summary: Chrome ブラウザーにおける SameSite Cookie の変更の処理について説明します。

### SameSite とは

`SameSite` は、Web アプリケーションでのクロスサイト リクエスト フォージェリ (CSRF) 攻撃を防ぐために、HTTP Cookie で設定できるプロパティです。

- `SameSite` が **Lax** に設定されている場合、Cookie は同じサイト内の要求と、他のサイトからの GET 要求で送信されます。 ドメインを越えた GET 要求では送信されません。
- **Strict** の値を指定すると、同じサイト内の要求でのみ Cookie が送信されることが保証されます。

既定では、`SameSite` の値はブラウザーで設定されていません。そのため、要求で送信される Cookie に制限はありません。 アプリケーションは、要件に応じて **Lax** または **Strict** を設定して、CSRF 保護をオプトインする必要があります。

### SameSite の変更と認証への影響

最近行われた [SameSite の標準への更新](https://tools.ietf.org/html/draft-west-cookie-incrementalism-00)では、Lax に設定されている値が 1 つもない場合、`SameSite` の既定の動作を行うことでアプリを保護することが提案されています。 この軽減策は、 Cookie が、他のサイトから行われた GET 以外の HTTP 要求に制限されることを意味します。 また、送信される Cookie に対する制限を除去するために、**None** の値が導入されました。 これらの更新は間もなく、Chrome ブラウザーの今後のバージョンでリリースされる予定です。

Web アプリが応答モード "form\_post" を使用して Microsoft ID プラットフォームで認証すると、ログイン サーバーは、トークンまたは認証コードを送信するために、HTTP POST を使用してアプリケーションに応答します。 この要求はドメイン間要求 (`login.microsoftonline.com` から自分のドメイン、たとえば `https://contoso.com/auth`) のため、お使いのアプリによって設定された Cookie は、Chrome の新しいルールに該当するようになりました。 クロスサイトのシナリオで使用する必要がある Cookie は、 *state* と *nonce* の値を保持する Cookie で、これはログイン要求でも送信されす。 セッションを保持するために Microsoft Entra ID によって削除された他の Cookie があります。

Web アプリを更新しないと、この新しい動作によって認証エラーが発生します。

### 軽減策とサンプル

認証エラーが発生しないようにするために、Microsoft ID プラットフォームで認証を行う Web アプリで、Chrome ブラウザーでの実行時にクロスドメイン シナリオで使用される Cookie の `SameSite` プロパティを `None` に設定できます。 その他のブラウザー (完全な一覧については[こちら](https://www.chromium.org/updates/same-site/incompatible-clients)を参照) は、`SameSite` の以前の動作に従い、`SameSite=None` が設定されている場合は Cookie が含まれません。 そのため、複数のブラウザーでの認証をサポートするには、Web アプリで、Chrome に対してのみ `SameSite` 値を `None` に設定し、他のブラウザーに対してはこの値を空のままにする必要があります。

このアプローチを次のサンプル コードで示します。

## [.NET](#tab/dotnet)
次の表は、ASP.NET と ASP.NET Core サンプルでの SameSite の変更を回避するプル要求を示しています。

| サンプル | Pull request |
| --- | --- |
| [ASP.NET Core Web アプリの増分チュートリアル](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2) | [SameSite Cookie の修正 #261](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/pull/261) |
| [ASP.NET MVC Web アプリのサンプル](https://github.com/Azure-Samples/ms-identity-aspnet-webapp-openidconnect) | [SameSite Cookie の修正 #35](https://github.com/Azure-Samples/ms-identity-aspnet-webapp-openidconnect/pull/35) |
| [active-directory-dotnet-admin-restricted-scopes-v2](https://github.com/azure-samples/active-directory-dotnet-admin-restricted-scopes-v2) | [SameSite Cookie の修正 #28](https://github.com/Azure-Samples/active-directory-dotnet-admin-restricted-scopes-v2/pull/28) |

ASP.NET と ASP.NET Core で SameSite Cookie を処理する方法の詳細については、以下も参照してください:

- [ASP.NET Core での SameSite cookie の使用](https://learn.microsoft.com/ja-jp/aspnet/core/security/samesite)。
- [SameSite の問題に関する ASP.NET ブログ](https://devblogs.microsoft.com/aspnet/upcoming-samesite-cookie-changes-in-asp-net-and-asp-net-core/)

## [Python](#tab/python)
| サンプル |
| --- |
| [ms-identity-python-webapp](https://github.com/Azure-Samples/ms-identity-python-webapp) |

## [Java](#tab/java)
| サンプル | Pull request |
| --- | --- |
| [ms-identity-java-webapp](https://github.com/Azure-Samples/ms-identity-java-webapp) | [SameSite Cookie の修正 #24](https://github.com/Azure-Samples/ms-identity-java-webapp/pull/24) |
| [ms-identity-java-webapi](https://github.com/Azure-Samples/ms-identity-java-webapi) | [SameSite Cookie の修正 #4](https://github.com/Azure-Samples/ms-identity-java-webapi/pull/4) |

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-implement-rbac-for-apps"} -->
## アプリケーションにロールベースのアクセス制御を実装する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-implement-rbac-for-apps
- Service: identity-platform
- Article date: 2024-10-29
- Summary: アプリケーションにロールベースのアクセス制御を実装する方法について説明します。

ロールベースのアクセス制御 (RBAC) を使用すると、ユーザーまたはグループに、リソースにアクセスして管理するための特定のアクセス許可を付与できます。 一般に、RBAC を実装してリソースを保護することには、Web アプリケーション、シングルページ アプリケーション (SPA)、または API を保護することが含まれます。 この保護は、アプリケーションまたは API 全体、特定の領域と機能、あるいは API メソッドを対象にすることができます。 認可の基本の詳細については、「[承認の基本](https://learn.microsoft.com/ja-jp/entra/identity-platform/authorization-basics)」を参照してください。

「[アプリケーション開発者向けのロールベースのアクセス制御](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-rbac-for-developers)」で説明したように、Microsoft ID プラットフォームを使用して RBAC を実装するには、次の 3 つの方法があります。

- **アプリ ロール** – アプリケーション内のロジックを使用する[アプリケーションのアプリ ロール機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#declare-roles-for-an-application)を使用して、受信したアプリ ロールの割り当てを解釈します。
- **グループ** – アプリケーション内のロジックを使用する受信 ID のグループ割り当てを使用して、グループ割り当てを解釈します。
- **カスタム データ ストア** – アプリケーション内のロジックを使用して、ロールの割り当てを取得して解釈します。

*アプリ ロール*は実装が最も簡単なため、これを使用することをお勧めします。 このアプローチは、Microsoft ID プラットフォームを利用するアプリの構築に使用される SDK によって直接サポートされています。 アプローチの選択方法について詳しくは、「[アプローチの選択](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-rbac-for-developers#choose-an-approach)」を参照してください。

### アプリ ロールを定義する

アプリケーションに RBAC を実装するための最初の手順は、アプリケーション用のアプリ ロールを定義し、ユーザーまたはグループをそれに割り当てることです。 このプロセスの概要は、「[方法: アプリケーションにアプリ ロールを追加してトークンで受け取る](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)」に記載されています。 アプリ ロールを定義し、それらにユーザーまたはグループを割り当てた後、アプリケーションに送信されるトークンでロール割り当てにアクセスし、それらを適宜操作します。

### ASP.NET Core で RBAC を実装する

ASP.NET Core では、ASP.NET Core Web アプリケーションまたは Web API に RBAC を追加することがサポートされます。 ASP.NET Core の *Authorize* 属性で[ロール チェック](https://learn.microsoft.com/ja-jp/aspnet/core/security/authorization/roles?view=aspnetcore-5.0&preserve-view=true#adding-role-checks)を使用することで、RBAC を追加して簡単に実装できます。 また、[ポリシーベースのロール チェック](https://learn.microsoft.com/ja-jp/aspnet/core/security/authorization/roles?view=aspnetcore-5.0&preserve-view=true#policy-based-role-checks)に対する ASP.NET Core のサポートも使用できます。

#### ASP.NET Core MVC Web アプリケーション

ASP.NET Core MVC Web アプリケーションに RBAC を実装するのは簡単です。 多くの場合、*Authorize* 属性を使用して、特定のコントローラーまたはコントローラーでのアクションへのアクセスを許可するロールを指定する必要があります。 ASP.NET Core MVC アプリケーションに RBAC を実装するには、次の手順に従います。

1. 上記の「*アプリ ロールを定義する*」で説明されているように、アプリ ロールおよび割り当てと共にアプリケーションの登録を作成します。
2. 次のいずれかの手順を実行します。

    - **dotnet cli** を使用して、新しい ASP.NET Core MVC Web アプリケーション プロジェクトを作成します。 `SingleOrg` (シングルテナント認証の場合) または `MultiOrg` (マルチテナント認証の場合) のいずれかと共に `--auth` フラグを指定します。アプリケーション登録からの場合はクライアントで `--client-id` フラグ、Microsoft Entra テナントからの場合はテナントで `--tenant-id` フラグを指定します。

        ```bash
        dotnet new mvc --auth SingleOrg --client-id <YOUR-APPLICATION-CLIENT-ID> --tenant-id <TENANT-ID>  
        ```
    - Microsoft.Identity.Web および Microsoft.Identity.Web.UI ライブラリを既存の ASP.NET Core MVC プロジェクトに追加します。

        ```bash
        dotnet add package Microsoft.Identity.Web 
        dotnet add package Microsoft.Identity.Web.UI 
        ```
3. 「[クイック スタート: ASP.NET Core Web アプリに Microsoft サインインを追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-v2-aspnet-core-webapp?view=aspnetcore-5.0&preserve-view=true)」で指定されている手順に従って、アプリケーションに認証を追加します。
4. [ロール チェックの追加](https://learn.microsoft.com/ja-jp/aspnet/core/security/authorization/roles?view=aspnetcore-5.0&preserve-view=true#adding-role-checks)に関するページで説明されているように、コントローラー アクションに対するロール チェックを追加します。
5. 保護されている MVC ルートのいずれかへのアクセスを試して、アプリケーションをテストします。

#### ASP.NET Core Web API

ASP.NET Core Web API で RBA を実装するには多くの場合、*Authorize* 属性を使用して、特定のコントローラーまたはコントローラーでのアクションへのアクセスを許可するロールを指定する必要があります。 ASP.NET Core Web API に RBAC を実装するには、次の手順に従います。

1. 上記の「*アプリ ロールを定義する*」で説明されているように、アプリ ロールおよび割り当てと共にアプリケーションの登録を作成します。
2. 次のいずれかの手順を実行します。

    - **dotnet cli** を使用して、新しい ASP.NET Core MVC Web API プロジェクトを作成します。 `SingleOrg` (シングルテナント認証の場合) または `MultiOrg` (マルチテナント認証の場合) のいずれかと共に `--auth` フラグを指定します。アプリケーション登録からの場合はクライアントで `--client-id` フラグ、Microsoft Entra テナントからの場合はテナントで `--tenant-id` フラグを指定します。

        ```bash
        dotnet new webapi --auth SingleOrg --client-id <YOUR-APPLICATION-CLIENT-ID> --tenant-id <TENANT-ID> 
        ```
    - Microsoft.Identity.Web および Swashbuckle.AspNetCore ライブラリを既存の ASP.NET Core Web API プロジェクトに追加します。

        ```bash
        dotnet add package Microsoft.Identity.Web
        dotnet add package Swashbuckle.AspNetCore 
        ```
3. 「[クイック スタート: ASP.NET Core Web アプリに Microsoft サインインを追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-v2-aspnet-core-webapp?view=aspnetcore-5.0&preserve-view=true)」で指定されている手順に従って、アプリケーションに認証を追加します。
4. [ロール チェックの追加](https://learn.microsoft.com/ja-jp/aspnet/core/security/authorization/roles?view=aspnetcore-5.0&preserve-view=true#adding-role-checks)に関するページで説明されているように、コントローラー アクションに対するロール チェックを追加します。
5. クライアント アプリケーションから API を呼び出します。 エンド ツー エンドのサンプルについては、[ASP .NET Core Web API を呼び出して、アプリ ロールを使用してロールベースのアクセス制御を実装する、Angular シングルページ アプリケーション](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-angular-samples)に関するページを参照してください。

### 他のプラットフォームに RBAC を実装する

#### Angular SPA

Angular SPA に RBAC を実装するには、アプリケーション内に含まれる Angular ルートへのアクセスを認可するために、[Angular 用 Microsoft Authentication Library](https://www.npmjs.com/package/@azure/msal-angular) を使用する必要があります。 例は、[MSAL Angular v3 サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-angular-samples)の中に示されています。

注

クライアント側 RBAC の実装では、認可されていないアプリケーションによる機密性の高いリソースへのアクセスを防ぐために、サーバー側 RBAC と組み合わせる必要があります。

#### Express アプリケーションを使用する Node.js

Express アプリケーションを使用して Node.js に RBAC を実装するには、アプリケーション内に含まれる Express ルートへのアクセスを認可するために MSAL を使用する必要があります。 [Node.js Web アプリで、Microsoft ID プラットフォームを使用してユーザーをサインインできるようにし、かつ API を呼び出せるようにする](https://github.com/Azure-Samples/ms-identity-javascript-nodejs-tutorial#chapter-4-control-access-to-your-app-using-app-roles-and-security-groups)ことを示すサンプルで例が示されています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-modify-supported-accounts"} -->
## 方法: アプリケーションによってサポートされるアカウントの種類を変更する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-modify-supported-accounts
- Service: identity-platform
- Article date: 2023-09-15
- Summary: このハウツー記事では、アプリケーションにアクセスできるユーザー (アカウント) を変更する目的で Microsoft ID プラットフォームに登録されているアプリケーションを構成します。

アプリケーションを Microsoft ID プラットフォームに登録するときに、アプリケーションにアクセスできるユーザー (アカウントの種類) を指定しました。 たとえば、組織内のアカウントを指定した場合、それは "*シングルテナント*" アプリです。 また、(自分の組織を含む) 任意の組織内のアカウントを指定した場合、それは "*マルチテナント*" アプリです。

後続のセクションでは、アプリの登録を変更して、アプリケーションにアクセスできる人やアカウントの種類を変更する方法について説明します。

### 前提条件

- [Microsoft Entra テナントに登録されているアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)

### 異なるアカウントをサポートするようにアプリケーションの登録を変更する

既存のアプリ登録でサポートされているアカウントの種類に別の設定を指定するには:

1. [アプリケーション開発者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニューの **[設定]** アイコン  を使用し、**[ディレクトリとサブスクリプション]** メニューからアプリケーション登録が含まれるテナントに切り替えます。
3. **Entra ID**&gt;**アプリ登録**に移動します。
4. アプリケーションを選択し、**[マニフェスト]** を選択して、マニフェスト エディターを使用します。
5. マニフェスト JSON ファイルをローカルにダウンロードします。
6. ここで、対象のアプリケーションを使用できるユーザーを指定します。これは、"*サインインの対象ユーザー*" と呼ばれる場合もあります。 マニフェスト JSON ファイルで *signInAudience* プロパティを探し、次のいずれかのプロパティ値に設定します。

    | プロパティ値 | サポートされているアカウントの種類 | 説明 |
    | --- | --- | --- |
    | **AzureADMyOrg** | [Accounts in this organizational directory only (Microsoft only - Single tenant) ] (この組織ディレクトリのみに含まれるアカウント (Microsoft のみ - 単一テナント)) | ディレクトリ内のすべてのユーザー アカウントとゲスト アカウントが、このアプリケーションまたは API を使用できます。 対象ユーザーが組織の内部にいる場合は、このオプションを使用します。 |
    | **AzureADMultipleOrgs** | 任意の組織ディレクトリ (Microsoft Entra ディレクトリ - マルチテナント) 内のアカウント | Microsoft の職場または学校アカウントを持つすべてのユーザーが、このアプリケーションまたは API を使用できます。 これには、Office 365 を使用する学校や企業が含まれます。 対象者がビジネスまたは教育関係のお客様であり、マルチテナント機能を有効にする場合にこのオプションを使用します。 |
    | **Azure AD と個人用 Microsoft アカウント** | 任意の組織ディレクトリ (Microsoft Entra ディレクトリ - マルチテナント) 内のアカウントと、個人用の Microsoft アカウント (Skype、Xbox など) | 職場または学校アカウントあるいは個人用 Microsoft アカウントを持つすべてのユーザーが、このアプリケーションまたは API を使用できます。 これには、Office 365 を使用する学校と企業、および Xbox や Skype などのサービスへのサインインに使用されている個人用アカウントが含まれます。 最も幅広い Microsoft ID のセットを対象として、マルチテナント機能を有効にする場合にこのオプションを使用します。 |
    | **PersonalMicrosoftアカウント** | 個人用 Microsoft アカウントのみ | Xbox や Skype などのサービスのサインインに使用する個人用アカウント。 さまざまな Microsoft ID を対象とする場合に、このオプションを使用します。 |
7. 変更をローカルの JSON ファイルに保存してから、マニフェスト エディターで **[アップロード]** を選択し、更新されたマニフェスト JSON ファイルをアップロードします。

#### マルチテナントへの変更が失敗する理由

アプリケーション ID URI (アプリ ID URI) の名前の競合が原因で、アプリの登録をシングルテナントからマルチテナントに切り替える操作が失敗することがあります。 `https://contoso.onmicrosoft.com/myapp` は、アプリ ID URI の一例です。

アプリ ID URI は、プロトコル メッセージでアプリケーションを識別する手段の 1 つです。 シングル テナント アプリケーションの場合、アプリ ID URI はそのテナント内でのみ一意である必要があります。 マルチテナント アプリケーションの場合、Microsoft Entra ID が全テナントから該当するアプリを特定できるように、アプリ ID URI がグローバルで一意になっている必要があります。 グローバルな一意性を確保するには、アプリ ID URI のホスト名が Microsoft Entra テナントの[検証済み発行元ドメイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-publisher-domain)のいずれかと一致している必要があります。

たとえば、テナントの名前が *contoso.onmicrosoft.com* の場合、`https://contoso.onmicrosoft.com/myapp` は有効なアプリ ID URI です。 また、テナントの検証済みドメインが *contoso.com* の場合、有効なアプリ ID URI は `https://contoso.com/myapp` のようになります。 アプリ ID URI が 2 番目のパターン `https://contoso.com/myapp` に従っていない場合は、アプリ登録のマルチテナントへの変換は失敗します。

検証済み発行元ドメインの構成の詳細については、[検証済みドメインの構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-publisher-domain)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-remove-app"} -->
## 方法: Microsoft ID プラットフォームから登録済みのアプリを削除する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-remove-app
- Service: identity-platform
- Article date: 2023-06-21
- Summary: Microsoft ID プラットフォームに登録されたアプリケーションを削除する方法について説明します。

アプリケーションを Microsoft ID プラットフォームに登録したエンタープライズ開発者や SaaS (サービスとしてのソフトウェア) プロバイダーは、アプリケーションの登録の削除が必要になる場合があります。

ヒント

アプリケーションを完全に削除する前に、代わりに [非アクティブ化することを](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/deactivate-application-portal) 検討してください。 無効化により、調査や再アクティブ化が可能な状態でアプリケーションの構成を保持しつつ、トークンの発行を防ぎます。これにより、削除よりも破壊的でない選択肢となります。

後続のセクションでは、次の操作を行う方法について学習します。

- 自分または自分の組織が作成したアプリケーションを削除する
- 他の組織が作成したアプリケーションを削除する

### 前提条件

- [Microsoft Entra テナントに登録されているアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)

### 自分または自分の組織が作成したアプリケーションを削除する

自分または自分の組織が登録したアプリケーションは、テナント内のアプリケーション オブジェクトとサービス プリンシパル オブジェクトの両方で表されます。 詳細については、[アプリケーション オブジェクトとサービス プリンシパル オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)に関するページを参照してください。

注意

アプリケーションを削除すると、アプリケーションのホーム ディレクトリ内のサービス プリンシパル オブジェクトも削除されます。 マルチテナント アプリケーションの場合、他のディレクトリ内のサービス プリンシパル オブジェクトは削除されません。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニューの **[設定]** アイコン  を使用し、**[ディレクトリとサブスクリプション]** メニューからアプリケーション登録が含まれるテナントに切り替えます。
3. **Entra ID**&gt;**App 登録**に移動し、構成するアプリケーションを選択します。 アプリを選択すると、アプリケーションの **[概要]** ページが表示されます。
4. **[概要]** ページで **[削除]** を選択します。
5. 削除の結果を確認します。 ペインの下部にボックスが表示されている場合は、オンにします。
6. アプリの削除を確認する画面で **[削除]** を選択します。

### 他の組織が作成したアプリケーションを削除する

テナントのコンテキストで **[アプリの登録]** を表示している場合、**[すべてのアプリ]** タブに表示されるアプリケーションの一部は、別のテナントからのものであり、同意プロセス中にご自分のテナントに登録されました。 さらに具体的には、組織のテナントの中に対応するアプリケーション オブジェクトが存在せず、サービス プリンシパル オブジェクトのみによって表されるアプリケーションです。 アプリケーション オブジェクトとサービス プリンシパル オブジェクトの違いの詳細については、「[Microsoft Entra ID のアプリケーション オブジェクトとサービス プリンシパル オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)」を参照してください。

(同意を与えた後に) ディレクトリに対するアプリケーションのアクセス権を削除するには、会社の管理者がアプリケーションのサービス プリンシパルを削除する必要があります。 管理者は、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)アクセス権を持っている必要があります。 サービス プリンシパルを削除する方法については、「[エンタープライズ アプリケーションの削除](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-application-portal)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-restore-app"} -->
## 方法: Microsoft ID プラットフォームで最近削除されたアプリケーションを復元または削除する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-restore-app
- Service: identity-platform
- Article date: 2023-06-21
- Summary: このハウツーでは、Microsoft ID プラットフォームに登録されていて最近削除されたアプリケーションを復元または完全に削除する方法について説明します。

アプリの登録を削除した後、アプリは 30 日間、停止状態のままになります。 その 30 日の期間中に、アプリの登録をそのすべてのプロパティと共に復元することができます。 その 30 日の期間が経過した後は、アプリの登録を復元できず、永続的な削除プロセスが自動的に開始される可能性があります。 この機能は、ディレクトリに関連付けられているアプリケーションにのみ適用されます。 個人用 Microsoft アカウントからアプリケーションを使用することはできず、復元できません。

削除されたアプリケーションを表示したり、削除されたアプリケーションを復元したり、Microsoft Entra 管理センターの **Entra ID**&gt;**App 登録** を使用してアプリケーションを完全に削除したりできます。

お客様も Microsoft カスタマー サポートも、完全に削除されたアプリケーション、または 30 日より前に削除されたアプリケーションを復元することはできません。

### 前提条件

アプリケーションを完全に削除するには、次のいずれかのロールが必要です。

- アプリケーション管理者
- クラウド アプリケーション管理者
- ハイブリッド ID の管理者
- アプリケーション所有者

アプリケーションを復元するには、次のいずれかのロールが必要です。

- アプリケーション所有者

### 削除されたアプリケーションを表示する

論理的に削除された状態のすべてのアプリケーションを表示することができます。 復元できるのは、削除されてから 30 日未満のアプリケーションのみです。

復元可能なアプリケーションを表示するには、次のようにします。

1. 前提条件に記載されているロールのいずれかを使用して [、Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. **Entra ID**&gt;**App 登録**に移動し、[**削除されたアプリケーション**] タブを選択します。

アプリケーションの一覧を確認します。 復元できるのは、過去 30 日以内に削除されたアプリケーションのみです。 アプリの登録の検索プレビューを使用している場合は、[削除日] 列でフィルター処理して、これらのアプリケーションのみを表示できます。

### 最近削除されたアプリケーションを復元する

アプリの登録が組織から削除されると、そのアプリは停止状態になり、構成が保持されます。 アプリの登録を復元すると、その構成も復元されます。 ただし、アプリケーションのホーム テナントの **エンタープライズ アプリケーション** に格納されている特定の組織のアクセス許可の同意やユーザーとグループの割り当てなど、組織固有の設定があった場合は、アプリの登録と共に復元されます。

アプリケーションを復元するには、次のようにします。

1. [ **削除されたアプリケーション** ] タブに移動します。30 日以内に削除されたアプリケーションのいずれかを検索して選択します。
2. [ **アプリの登録の復元**] を選択します。

### アプリケーションを完全に削除する

組織からアプリケーションを手動で完全に削除することができます。 完全に削除されたアプリケーションは、担当の管理者も、別の管理者も、Microsoft カスタマー サポートも復元することができません。 ただし、この操作で対応するサービス プリンシパルが完全に削除されるわけではありません。 対応するアクティブなアプリケーションがない場合、サービス プリンシパルは復元できません。そのため、サービス プリンシパルは手動で (この場合は完全に) 削除できます。 何もしない場合、アプリケーションの削除から 30 日後にサービス プリンシパルは完全に削除されます。

アプリケーションを完全に削除するには、次のようにします。

1. [ **削除されたアプリケーション** ] タブに移動します。使用可能なアプリケーションのいずれかを検索して選択します。
2. [ **完全に削除] を選択します**。
3. 警告テキストを読み、[ **はい**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-restrict-your-app-to-a-set-of-users"} -->
## Microsoft Entra アプリを一連のユーザーのみに制限する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-restrict-your-app-to-a-set-of-users
- Service: identity-platform
- Article date: 2024-07-19
- Summary: Microsoft Entra ID に登録されたアプリへのアクセスを選択したユーザーのセットに制限する方法についてご確認ください。

Microsoft Entra テナントに登録されたアプリケーションは、既定ではテナントの正常に認証されたすべてのユーザーが利用できます。 アプリケーションを一連のユーザーに制限するには、ユーザーの割り当てを要求するようにアプリケーションを構成します。 アプリケーションまたはサービスにアクセスしようとするユーザーやサービスをこのアプリケーションに割り当てる必要があります。そうしないと、サインインしたりアクセス トークンを取得したりできなくなります。

同様に、[マルチ テナント型](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant)のアプリケーションでは、アプリケーションがプロビジョニングされている Microsoft Entra テナント内のすべてのユーザーは、それぞれのテナントで正常に認証されると、アプリケーションにアクセスできます。

テナントの管理者と開発者には、多くの場合、アプリを特定のユーザーまたはアプリ (サービス) のセットに制限しなければならない要件があります。 アプリケーションを特定のユーザー、アプリ、またはセキュリティ グループのセットに制限するには、次の 2 つの方法があります。

- 開発者は、Azure ロールベースのアクセス制御 (Azure RBAC) のような一般的な [承認パターンを使用できます](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-implement-rbac-for-apps)。
- テナント管理者および開発者は、Microsoft Entra ID の組み込み機能を使用できます。

### 前提条件

- Microsoft Entra ユーザー アカウント。 まだお持ちでない場合は、[無料のアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- [Microsoft Entra テナントに登録されているアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)
- アプリケーションの所有者であるか、少なくともテナント内の[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)である必要があります。

### サポートされているアプリの構成

テナントのユーザー、アプリ、またはセキュリティ グループの特定のセットにアプリを制限するオプションは、次の種類のアプリケーションで動作します。

- SAML ベースの認証を使用したフェデレーション シングル サインオン用に構成されたアプリケーション。
- Microsoft Entra 事前認証を使用する アプリケーション プロキシのアプリケーション。
- ユーザーまたは管理者がそのアプリケーションに同意した後に OAuth 2.0/OpenID Connect 認証を使用する Microsoft Entra アプリケーション プラットフォームに直接構築されたアプリケーション。

### アプリを更新してユーザーの割り当てを要求にする

アプリケーションを更新してユーザーの割当を要求するには、Enterprise アプリのアプリケーションの所有者であるか、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)である必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニューの **[ディレクトリとサブスクリプション]** フィルター  を使用し、**[ディレクトリとサブスクリプション]** メニューからアプリケーション登録が含まれるテナントに切り替えます。
3. **Entra ID**&gt;**Enterprise アプリ**に移動し、[**すべてのアプリケーション**] を選択します。
4. 構成して割り当てを要求するアプリケーションを選択します。 ウィンドウの上部にあるフィルターを使用して、特定のアプリケーションを検索します。
5. アプリケーションの **[概要]** ページの **[管理]** の下で **[プロパティ]** を選択します。
6. **[割り当てが必要ですか?]** という設定を見つけ、それを **[はい]** に設定します。
7. 上部バーにある **[保存]** を選択します。

アプリケーションが割り当てを要求する場合、そのアプリケーションに対するユーザーの同意は許可されません。 これは、そのアプリに対するユーザーの同意がそれ以外の場合に許可されている場合でも当てはまります。 割り当てを必要とするアプリに対して、[テナント全体の管理者の同意を付与](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent)してください。

注

ユーザーが全体管理者の場合、ユーザー割り当ての要件は適用されません。 グローバル管理者は、Microsoft Entra ID のすべての管理機能へのアクセスを許可し、アクセス権を昇格させてすべての Azure サブスクリプションと管理グループを管理できる、高い特権を持つロールです。

### アプリをユーザーとグループに割り当ててアクセスを制限する

ユーザーの割り当てを有効にするようにアプリを構成したら、次はユーザーとグループにそのアプリを割り当てることができます。

1. **[管理]** で、**[ユーザーとグループ]** を選択し、**[ユーザーまたはグループの追加]** を選択します。
2. **[ユーザー]** で **[選択されていません]** を選択すると、**[ユーザー]** セレクター ウィンドウが開くので、そこで複数のユーザーやグループを選択できます。
3. ユーザーやグループの追加が完了したら、**[選択]**を選択します。
    1. (オプション) アプリケーションでアプリ ロールを定義している場合、 **[ロールの選択]** オプションを使用して、選択したユーザーとグループにアプリのロールを割り当てることができます。
4. **[割り当て]** を選択して、ユーザーとグループへのアプリの割り当てを完了します。
5. **[ユーザーとグループ]** ページに戻ると、新しく追加されたユーザーやグループが、更新された一覧に表示されます。

### 他のサービス (クライアント アプリ) を割り当てて、アプリ (リソース) へのアクセスを制限する

このセクションの手順に従って、テナントのアプリ間認証アクセスをセキュリティで保護します。

1. テナントのサービス プリンシパル サインイン ログに移動して、テナント内のリソースにアクセスするための認証サービスを見つけます。
2. アプリ ID を使用して、アクセスを管理するテナント内のリソースとクライアントの両方のアプリにサービス プリンシパルが存在するかどうかを確認します。

    ```powershell
    Get-MgServicePrincipal `
    -Filter "AppId eq '$appId'"
    ```
3. サービス プリンシパルが存在しない場合は、アプリ ID を使用してを作成します。

    ```powershell
    New-MgServicePrincipal `
    -AppId $appId
    ```
4. リソース アプリにクライアント アプリを明示的に割り当てます (この機能は API でのみ使用でき、Microsoft Entra 管理センターでは使用できません)。

    ```powershell
    $clientAppId = “[guid]”
                   $clientId = (Get-MgServicePrincipal -Filter "AppId eq '$clientAppId'").Id
    New-MgServicePrincipalAppRoleAssignment `
    -ServicePrincipalId $clientId `
    -PrincipalId $clientId `
    -ResourceId (Get-MgServicePrincipal -Filter "AppId eq '$appId'").Id `
    -AppRoleId "00000000-0000-0000-0000-000000000000"
    ```
5. 明示的に割り当てられたユーザーまたはサービスにのみアクセスを制限するには、リソース アプリケーションの割り当てが必要です。

    ```powershell
    Update-MgServicePrincipal -ServicePrincipalId (Get-MgServicePrincipal -Filter "AppId eq '$appId'").Id -AppRoleAssignmentRequired:$true
    ```

注

アプリケーションに対してトークンを発行しない場合、またはテナント内のユーザーまたはサービスからアプリケーションにアクセスできないようにするには、アプリケーションのサービス プリンシパルを作成し、それに対する[ユーザー サインインを無効にします](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/disable-user-sign-in-portal)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/howto-update-permissions"} -->
## Microsoft Entra ID でアプリの要求されたアクセス許可を更新する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-update-permissions
- Service: identity-platform
- Article date: 2024-10-01
- Summary: 開発者が、アプリケーションによる不要なアクセス許可の要求を防ぎ、Microsoft ID プラットフォームでアプリケーションに新しいアクセス許可を追加する方法について説明します。

Microsoft Entra ID でアプリケーションを設定するとき、開発者はアクセス許可を使って他のアプリやサービスのデータへのアクセスを要求できます。 アクセス許可の要求は、アプリのマニフェストに静的アクセス許可を追加するか、実行時にアクセス許可を動的に要求することで、行うことがことできます。 その後、ユーザーまたは管理者は、同意の際にアクセス許可を付与して、アプリが必要とするデータにアクセスできるようにします。

アプリケーションの機能が進化すると、アクセスする必要があるリソースも変わります。 これらの変更には、新機能の有効化、不要なアクセスの排除、高い特権のアクセス許可の低い特権のものへの置き換えが含まれる場合があります。 この記事では、Microsoft Entra 管理センターと Microsoft Graph API の呼び出しを使って、アプリケーションが要求するアクセス許可を更新する方法について説明します。

アプリのアクセス許可の更新は、セキュリティのベスト プラクティスであるだけでなく、アプリのユーザー エクスペリエンスと導入を強化する方法でもあります。 次のセクションでは、アプリのアクセス許可を更新するいくつかの利点について説明します。

- アプリに新しい機能ができた場合は、さらに多くのアクセス許可を要求して、アプリが必要とする追加のリソースにアクセスできるようにします。
- 顧客は、アプリケーションが機能するために必要な最小限の特権のアクセス許可のみを要求する場合、それを導入する可能性が高くなります。 これは、アプリが顧客のプライバシーとデータ保護を尊重し、必要以上のリソースにアクセスしないことを示しています。
- さらに、アプリが侵害された場合、特権アクセス許可が少ない、または低いほど、影響を受ける範囲が狭くなります。 つまり、攻撃者がアクセスできる顧客のデータやリソースが少なくなり、可能性のある損害が軽減されます。
- アプリのアクセス許可を更新することで、アプリのセキュリティ、使いやすさ、コンプライアンスを向上させ、顧客との信頼を築くことができます。

### Prerequisites

アプリの要求されたアクセス許可を更新するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだお持ちでない場合は、[無料のアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- 次のいずれかのロール: アプリケーション管理者、クラウド アプリケーション管理者。 管理者ではないアプリケーション所有者は、アプリの要求されたアクセス許可を更新できます。

### アクセス許可の更新のシナリオ

次のセクションでは、アプリケーションが要求するアクセス許可を更新する必要がある 3 つの主なシナリオを示します。

- アクセス許可をアプリケーションに追加する
- 使われていないアクセス許可をアプリケーションから削除する
- アクセス許可を差し替える

Note

アプリケーションの要求されたアクセス許可を更新しても、保護されたリソースへのアクセスをアプリが自動的に許可または取り消しを行うことはありません。 顧客または組織内の管理者が、新しく追加されたアクセス許可への同意を許可するか、手動でアクセス許可自体を取り消す必要があります。

### アクセス許可をアプリケーションに追加する

アプリに、以前は必要なかったアクセス許可を必要とする新しい機能がある場合は、アクセス許可を追加できます。

アプリが機能するために必要な最小限のアクセス許可へのアクセスのみを要求するのがベスト プラクティスです。 アプリの新機能をサポートするために新しいアクセス許可を追加する必要がある場合は、その機能のための最小特権のアクセス許可のみを要求するようにします。 たとえば、メール通知機能をアプリケーションに追加するには、ユーザーのメールにアクセスする必要があります。 そのためには、`Mail.ReadWrite` アクセス許可でのアクセスを要求する必要があります。

#### 静的な同意へアクセス許可を追加する

静的同意は、実行時ではなく、アプリケーションの登録時にユーザーまたは管理者にアクセス許可を要求する方法です。 静的同意を行うには、Microsoft Entra 管理センターの [ **アプリの登録** ] ウィンドウで、必要なすべてのアクセス許可をアプリで宣言する必要があります。 Microsoft Entra 管理センターを使って更新できるのは、静的同意に関するアクセス許可のみです。 同意のさまざまな種類について詳しくは、[同意の種類](https://learn.microsoft.com/ja-jp/entra/identity-platform/consent-types-developer)に関する記事をご覧ください。 動的同意のアクセス許可を更新する方法については、この記事の Microsoft Graph のタブをご覧ください。

::: zone pivot="portal"

このセクションでは、静的同意にアクセス許可を追加する方法について説明します。

Microsoft Entra 管理センターでは、2 つの方法で静的同意にアクセス許可を追加できます。

#### オプション 1: **[API のアクセス** 許可] ウィンドウでアクセス許可を追加する

1. 少なくとも[クラウド アプリケーション管理者](https://entra.microsoft.com)またはアプリケーション所有者として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**アプリケーションの登録**&gt;**すべてのアプリケーション**に移動します。
3. アクセス許可を追加するアプリ登録を見つけて選択します。 アクセス許可は 2 つの方法で追加できます。
4. **[API のアクセス許可**] ウィンドウでアクセス許可を追加します。
    1. **[API のアクセス許可**] ウィンドウを見つけて、[**アクセス許可の追加]** を選択します。
    2. アクセスする API と、使用可能なオプションの一覧から要求するアクセス許可を選択し、[ **アクセス許可の追加]** を選択します。

        [Image: [API のアクセス許可] ウィンドウのスクリーンショット。]

#### オプション 2: アプリケーション マニフェストにアクセス許可を追加する

1. 左側のナビゲーション ウィンドウの [ **管理** ] メニュー グループで、[マニフェスト] を選択 **します**。 選択するとエディターが開き、アプリ登録オブジェクトの属性を直接編集できます。
2. アプリケーションのマニフェスト ファイルで `requiredResourceAccess` プロパティを慎重に編集します。
3. `resourceAppId` プロパティと `resourceAccess` プロパティを追加し、必要なアクセス許可を割り当てます。
4. 変更を保存します。

::: zone-end

::: zone pivot="ms-graph"

アクセス許可を追加する以下の手順を完了するには、次のリソースと特権が必要です。

- アプリ内や Graph エクスプローラーなど、任意のツールで HTTP 要求を実行します。
- 少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)であるユーザーまたはターゲット アプリ登録の所有者として、API を実行します。
- これらの変更を行うために使うアプリには、`Application.ReadWrite.All` アクセス許可が付与されている必要があります。

1. アプリで必要なアクセス許可、そのアクセス許可 ID、およびそれがアプリの役割 (アプリケーションのアクセス許可) か委任されたアクセス許可かを明らかにします。 たとえば、Microsoft Graph のアクセス許可を要求する場合は、[Microsoft Graph のアクセス許可](https://learn.microsoft.com/ja-jp/graph/permissions-reference#permission-scenarios)に関する記事で、アクセス許可とその ID の一覧を参照してください。
2. 必要な Microsoft Graph のアクセス許可をアプリに追加します。

    次の例では、 [Update アプリケーション](https://learn.microsoft.com/ja-jp/graph/api/application-update) API を呼び出して、オブジェクト ID `aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb`で識別されるアプリ登録に必要な Microsoft Graph アクセス許可を追加します。 この例では、委任されたアクセス許可 `Analytics.Read` とアプリケーションのアクセス許可 `Application.Read.All` を使います。 Microsoft Graph は、グローバルに一意の `00000003-0000-0000-c000-000000000000` の値 `AppId` によって、ServicePrincipal オブジェクトとして識別されます。

    ```http
    PATCH https://graph.microsoft.com/v1.0/applications/aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb
    Content-Type: application/json
     {
         "requiredResourceAccess": [
             {
                 "resourceAppId": "00000003-0000-0000-c000-000000000000",
                 "resourceAccess": [
                     {
                         "id": "e03cf23f-8056-446a-8994-7d93dfc8b50e",
                         "type": "Scope"
                     },
                     {
                         "id": "9a5d68dd-52b0-4cc2-bd40-abcf44ac3a30",
                         "type": "Role"
                     }
                 ]
             }
         ]
     }
    ```

#### 動的同意にアクセス許可を追加する

動的同意は、実行時にユーザーまたは管理者にアクセス許可を要求する方法であり、[ **アプリの登録** ] ウィンドウで静的に宣言する方法です。 動的同意を使うと、アプリは特定の機能に必要なアクセス許可のみを要求し、必要なときにユーザーまたは管理者から同意を得ることができます。 動的同意は、委任されたアクセス許可と共に使用でき、`/.default` スコープと組み合わせて、すべてのアクセス許可に対する管理者の同意を要求できます。

動的同意にアクセス許可を追加するには:

- **Microsoft Graph を使う**: アプリの登録に必要な Microsoft Graph のアクセス許可を追加します。 この例では、委任されたアクセス許可 `Analytics.Read` とアプリケーションのアクセス許可 `Application.Read.All` を使います。 `scopes` の値を、アプリ用に構成したい任意の Microsoft Graph の委任アクセス許可の値に置き換えます。

    要求は次の例のようになります。

    `https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id=00001111-aaaa-2222-bbbb-3333cccc4444&response_type=code&scope=Analytics.Read+Application.Read`
- **MSAL.js**使用: "スコープ" の値を、アプリ用に構成する Microsoft Graph の委任されたアクセス許可の値に置き換えます。

    ```msal
      const Request = {
          scopes: ["openid", "profile"],
          loginHint: "example@domain.net"
      };
    
      myMSALObj.ssoSilent(Request)
          .then((response) => {
              // your logic
          }).catch(error => {
              console.error("Silent Error: " + error);
              if (error instanceof msal.InteractionRequiredAuthError) {
                  myMSALObj.loginRedirect(loginRequest);
              }
      });
    ```

::: zone-end

### エンタープライズ アプリケーションの追加されるアクセス許可に対する同意を許可する

アクセス許可がアプリケーションに追加された後、ユーザーまたは管理者は新しいアクセス許可に対する同意を許可する必要があります。 管理者ではないユーザーがアプリにサインインすると、同意を求めるメッセージが表示されます。 一方、管理者ユーザーは、アプリに初めてサインインするとき、または Microsoft Entra 管理センターで、組織内のすべてのユーザーに代わって、新しいアクセス許可に対する同意を許可できます。

追加されたアクセス許可で管理者の同意が必要な場合、必要なアクションはアプリの種類によって異なります。

- **ホーム テナント内のシングル テナント アプリとマルチテナント アプリ**: ユーザーは、少なくとも[特権ロール管理者ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインし、[テナント全体の同意を許可する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent)必要があります。
- **顧客のテナントのマルチテナント アプリ**: ユーザーが次にサインインを試みたときに、新しい同意プロンプトが表示されます。 アクセス許可で必要なのがユーザーの同意のみの場合、ユーザーは同意を許可できます。 アクセス許可で管理者の同意が必要な場合、ユーザーは管理者に連絡して同意の許可を求める必要があります。

#### 使われていないアクセス許可の要求を停止する

アクセス許可を削除すると、機密データの漏えいやセキュリティの侵害が発生するリスクが減り、ユーザーまたは管理者の同意プロセスが簡単になります。 アプリでアクセス許可が不要になった場合は、アプリの登録の必要なリソース アクセスとコードからアクセス許可を削除することで、アプリがアクセス許可を要求しないようにする必要があります。 たとえば、メール通知を送信しなくなったアプリケーションでは、`Mail.ReadWrite` アクセス許可を削除できます。

Important

アプリの登録からアクセス許可を削除しても、アプリに既に付与されているアクセス許可は自動的に取り消されません。 手動でアクセス許可を取り消す必要があります。 詳しくは、この記事の「エンタープライズ アプリケーションの削除されたアクセス許可の同意を取り消す」セクションをご覧ください。

### 静的同意に対するアクセス許可の要求を停止する

::: zone pivot="portal"

静的な同意を必要とするアクセス許可の要求を停止するには、[ **アプリの登録** ] ウィンドウからアクセス許可を削除する必要があります。 テナントの管理者は、[ **エンタープライズ アプリケーション** ] ウィンドウでアクセス許可を取り消す必要もあります。 エンタープライズ アプリケーションに付与されたアクセス許可を取り消す方法について詳しくは、[エンタープライズ アプリケーションのアクセス許可の取り消し](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-application-permissions#review-and-revoke-permissions-in-the-microsoft-entra-admin-center)に関する記事をご覧ください。

このセクションでは、静的同意のアクセス許可の要求を停止する方法について説明します。

Microsoft Entra 管理センターでは、2 つの方法で静的同意からアクセス許可を取り消すことができます。

#### オプション 1: **[API のアクセス許可** ] ウィンドウから

1. 少なくとも[クラウド アプリケーション管理者](https://entra.microsoft.com)またはアプリケーション所有者として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**アプリケーションの登録**&gt;**すべてのアプリケーション**に移動します。
3. アクセス許可を削除する対象のアプリ登録を見つけて、選択します。
4. **[API のアクセス許可**] ウィンドウからアクセス許可を削除します。
    1. **[API のアクセス許可**] ウィンドウを見つけて、削除するアクセス許可を見つけます。
    2. 削除する API を選んでから、**[管理者の同意を取り消す]**、**[アクセス許可の削除]** の順に選びます。 これにより、付与されたアクセス許可がお客様のテナントから削除されるようになります。

        [Image: [API のアクセス許可] ウィンドウを使ってアクセス許可を削除する方法を示すスクリーンショット。]

#### オプション 2: アプリケーション マニフェストから

1. 左側のナビゲーション ウィンドウの [ **管理** ] メニュー グループで、[マニフェスト] を選択 **します**。 エディターが開き、アプリ登録オブジェクトの属性を直接編集できます。
2. アプリケーションのマニフェスト ファイルで `requiredResourceAccess` プロパティを慎重に編集します。
3. 不要なアクセス許可を `resourceAppId` プロパティと `resourceAccess` プロパティから削除します。
4. 変更を保存します。

::: zone-end

::: zone pivot="ms-graph"

アクセス許可を削除する以下の手順を完了するには、次のリソースと特権が必要です。

- アプリ内や Graph エクスプローラーなど、任意のツールで HTTP 要求を実行します。
- 少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)またはターゲット アプリ登録の所有者として、API を呼び出します。
- これらの変更を行うために使うアプリには、`Application.ReadWrite.All` アクセス許可が付与されている必要があります。

1. アプリのアクセス許可を特定します。
2. たとえば、アプリが Microsoft Graph のアクセス許可を要求しないようにするには、アプリの Microsoft Graph のアクセス許可、そのアクセス許可 ID、およびそれがアプリの役割 (アプリケーションのアクセス許可) か委任されたアクセス許可かを明らかにします。
3. 不要な Microsoft Graph のアクセス許可をアプリから削除します。 次の例では、 [Update アプリケーション](https://learn.microsoft.com/ja-jp/graph/api/application-update) API を呼び出して、サンプル クライアント ID `00001111-aaaa-2222-bbbb-3333cccc4444`で識別されるアプリ登録から不要な Microsoft Graph アクセス許可を削除します。 この例では、アプリケーションには `Analytics.Read`、`User.Read`、`Application.Read.All` があります。 委任されたアクセス許可 `Analytics.Read` とアプリケーションのアクセス許可 `Application.Read.All` を削除する必要があります。 Microsoft Graph は、グローバルに一意の `00000003-0000-0000-c000-000000000000` である `AppId` と、`DisplayName` および `AppDisplayName` である Microsoft Graph を持つ ServicePrincipal オブジェクトとして識別されます。

    ```http
    PATCH https://graph.microsoft.com/v1.0/applications/00001111-aaaa-2222-bbbb-3333cccc4444
    Content-Type: application/json
    {
        "requiredResourceAccess": [
            {
                "resourceAppId": "00000003-0000-0000-c000-000000000000",
                "resourceAccess": [
                    {
                        "id": "311a71cc-e848-46a1-bdf8-97ff7156d8e6 ",
                        "type": "Scope"
                    }
                ]
            }
        ]
    }
    ```

#### 動的同意でのアクセス許可の要求を停止する

動的同意要求から委任されたアクセス許可を削除する必要がある場合は、削除するアクセス許可を除外してスコープ パラメーターを指定します。 アクセス許可を削除すると、アプリは対応する API を呼び出さなくなります。

このメソッドは、委任されたアクセス許可に対してのみ機能します。 アプリケーションのアクセス許可は、静的な同意を通じて管理者によって要求および付与され、OAuth 2.0 認可要求中にスコープ パラメーターに含まれません。

動的同意でのアクセス許可の要求を停止するには:

- **Microsoft Graph の使用**: "scopes" パラメーターから不要な Microsoft Graph の委任されたアクセス許可を削除します。 この例のアプリケーションは、3 つの委任されたアクセス許可 `Analytics.Read`、`User.Read`、`Application.Read` を要求しています。 委任されたアクセス許可 `Analytics.Read` と `Application.Read` は、このアプリでは不要になりました。 `User.Read` だけが必要です。

要求は次の例のようになる必要があります。

`https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id=00001111-aaaa-2222-bbbb-3333cccc4444&response_type=code&scope=User.Read`

- **using MSAL.js**: 'scopes' の不要な Microsoft Graph の委任されたアクセス許可を削除します。

    ```msal
        const Request = {
            scopes: ["openid", "profile"],
            loginHint: "example@domain.net"
        };
    
        myMSALObj.ssoSilent(Request)
            .then((response) => {
                // your logic
            }).catch(error => {
                console.error("Silent Error: " + error);
                if (error instanceof msal.InteractionRequiredAuthError) {
                    myMSALObj.loginRedirect(loginRequest);
                }
        });
    ```

::: zone-end

### エンタープライズ アプリケーションの削除されたアクセス許可の同意を取り消す

アプリの登録からアクセス許可を削除した後、テナントの管理者は、組織のデータを保護するために同意を取り消す必要もあります。 削除されたアクセス許可で管理者の同意が必要な場合、必要なアクションはアプリの種類によって異なります。

- **シングル テナント アプリとホーム テナントのマルチテナント アプリ**: シングル テナント アプリの場合は、テナントの管理者に連絡し、アプリに既に付与されているアクセス許可を取り消してもらいます。 マルチテナント アプリの場合は、アプリケーションのインスタンスが存在するすべてのテナントの管理者に連絡して、[エンタープライズ アプリケーションに付与されているアクセス許可を取り消してもらいます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-application-permissions)。 削除されたアクセス許可への同意を取り消すと、削除されたアクセス許可を通じてアプリケーションがアクセスを維持することがなくなります。
- **顧客のテナント内のマルチテナント アプリ**: お知らせ、ブログ、その他の通信チャネルを通じたアクセス許可を取り消すよう、顧客に伝えます。

シングル テナントアプリとマルチテナント アプリの両方で、ユーザーの同意が有効になっているテナントの管理者以外のユーザーは、MyApps ポータルを使って、以前に付与したアクセス許可への同意を取り消すことができます。 エンド ユーザーが MyApps ポータルでアクセス許可を取り消す方法について詳しくは、[エンド ユーザーの同意の取り消し](https://support.microsoft.com/account-billing/edit-or-revoke-application-permissions-in-the-my-apps-portal-169be2b4-ee26-4338-aea8-d19bb2f329ee)に関する記事をご覧ください。

#### アクセス許可を差し替える

低い特権のアクセス許可で十分な場合は、高い特権を持つアクセス許可を置き換える必要があります。

また、アクセス許可を置き換えると、機密データが公開されたり、セキュリティが侵害されたりするリスクが減り、ユーザー エクスペリエンスと信頼が向上します。 アプリで `Directory.ReadWrite.All` などの高い特権のアクセス許可を使っている場合は、`User.ReadWrite.All` などの低い特権のアクセス許可でアプリの機能に十分かどうかを検討する必要があります。

Note

静的同意に対するアプリの要求されたアクセス許可を変更する場合は、顧客が同意し直す必要があります。 同意し直すことで、以前に許可したすべてのアクセス許可が取り消され、新しいものへの同意が許可されます。 動的同意に対するアプリの要求されたアクセス許可を変更しても、以前に許可したアクセス許可は取り消されません。 顧客は、アクセス許可を手動で取り消す必要があります。

アクセス許可を置き換えるには、不要なアクセス許可を削除し、代わりのものを追加する必要があります。 この手順は、この記事の「使われていないアクセス許可の要求を停止する」およびアクセス許可の追加に関するセクションで説明されている手順に似ています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/id-token-claims-reference"} -->
## ID トークンの要求のリファレンス - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/id-token-claims-reference
- Service: identity-platform
- Article date: 2023-05-30
- Summary: Microsoft ID プラットフォームから発行される ID トークンに含まれる要求の詳細について説明します。

ID トークンは [JSON Web トークン (JWT)](https://wikipedia.org/wiki/JSON_Web_Token) です。 v1.0 と v2.0 の ID トークンは、含まれる情報に違いがあります。 バージョンは、要求元のエンドポイントに基づきます。 既存のアプリケーションでは Azure AD v1.0 エンドポイントを使用する場合が多いですが、新しいアプリケーションでは v2.0 エンドポイントを使用する必要があります。

- v1.0: `https://login.microsoftonline.com/common/oauth2/authorize`
- v2.0: `https://login.microsoftonline.com/common/oauth2/v2.0/authorize`

次のセクションに示す JWT 要求はすべて、特に明記されていない限り、v1.0 と v2.0 のトークンの両方に表示されます。 ID トークンは、ヘッダー、ペイロード、署名で構成されます。 ヘッダーと署名は、トークンの信頼性を確認するために使用されます。ペイロードには、クライアントによって要求されたユーザーの情報が含まれます。

### ヘッダーのクレーム

次の表は、ID トークンに存在するヘッダーの要求をまとめたものです。

| 要求 | フォーマット | 説明 |
| --- | --- | --- |
| `typ` | 文字列 - 常に "JWT" | トークンが JWT トークンであることを示します。 |
| `alg` | 糸 | トークンの署名に使用されたアルゴリズムを示します。 例: "RS256" |
| `kid` | 糸 | トークンの署名を検証するために使用できる公開キーの拇印を指定します。 v1.0 と v2.0 のどちらの ID トークンでも生成されます。 |
| `x5t` | 糸 | `kid` と同様に機能します (使用方法も値も同じ)。 `x5t` は、互換性を目的として v1.0 ID トークンでのみ生成されるレガシ クレームです。 |

### ペイロードのクレーム

次の表は、(明記されている場合を除き) 既定でほとんどの ID トークンに含まれる要求をまとめたものです。 ただし、アプリは、[省略可能なクレーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)を使用して、ID トークンで追加のクレームを要求することができます。 省略可能なクレームは、`groups` 要求から、ユーザーの名前に関する情報にまで及ぶ場合があります。

| 要求 | フォーマット | 説明 |
| --- | --- | --- |
| `aud` | 文字列、アプリケーション ID GUID | トークンの受信者を示します。 `id_tokens`では、対象の受信者は Azure portal でアプリに割り当てられたアプリのアプリケーション ID です。 この値は検証する必要があります。 アプリのアプリケーション ID と一致しない場合は、トークンを拒否する必要があります。 |
| `iss` | 文字列、発行者 URI | トークンを作成して返す発行者、つまり "承認サーバー" を特定します。 また、ユーザーが認証されたテナントも識別します。 トークンが v2.0 エンドポイントによって発行された場合、URI の末尾は `/v2.0` になります。 ユーザーが Microsoft アカウントを持つコンシューマー ユーザーであることを示す GUID は `9188040d-6c67-4c5b-b112-36a304b66dad` です。 要求の GUID 部分を使用して、アプリにサインインできるテナントのセットを制限します (該当する場合)。 |
| `iat` | int、Unix タイムスタンプ | トークンの認証がいつ行われたのかを示します。 |
| `idp` | 文字列 (通常は STS URI) | トークンのサブジェクトを認証した ID プロバイダーを記録します。 この値は、発行者とテナントが異なるユーザー アカウント (たとえばゲスト) の場合を除いて、発行者要求の値と同じです。 クレームが存在しない場合は、代わりに `iss` の値を使用できることを示しています。 個人用アカウントが組織のコンテキストで使用されている場合 (たとえば、個人用アカウントがテナントに招待された場合)、`idp` 要求は "live.com" または Microsoft アカウント テナント `9188040d-6c67-4c5b-b112-36a304b66dad` を含む STS URI である可能性があります。 この要求は、ゲスト ユーザーが関係するフェデレーション ドメイン シナリオで生成され、常にマネージド ドメイン のシナリオで送信する必要があります。 |
| `nbf` | int、Unix タイムスタンプ | JWT が有効になる日時を示します。これ以前にその JWT を受け入れて処理することはできません。 |
| `exp` | int、Unix タイムスタンプ | それ以降 JWT の処理を受け入れることができなくなる時刻を示します。 特定の状況下では、この時点より前にリソースによってトークンが拒否される可能性があります。 たとえば、認証の変更が必要な場合や、トークンの失効が検出された場合などです。 |
| `c_hash` | 糸 | コード ハッシュは、ID トークンが OAuth 2.0 認証コードと共に発行される場合にのみ、ID トークンに含まれます。 これを使用して、認証コードの信頼性を検証できます。 この検証を実行する方法については、[OpenID Connect の仕様](https://openid.net/specs/openid-connect-core-1_0.html#HybridIDToken)を参照してください。 この要求は、/token エンドポイントからの ID トークンでは返されません。 |
| `at_hash` | 糸 | アクセス トークン ハッシュは、ID トークンが `/authorize` エンドポイントから OAuth 2.0 アクセス トークンと共に発行される場合にのみ、ID トークンに含まれます。 これを使用して、アクセス トークンの信頼性を検証できます。 この検証を実行する方法については、[OpenID Connect の仕様](https://openid.net/specs/openid-connect-core-1_0.html#HybridIDToken)を参照してください。 この要求は、`/token` エンドポイントからの ID トークンでは返されません。 |
| `aio` | あいまいな文字列 | トークン再利用のためにデータの記録に使用される内部要求。 無視してください。 |
| `preferred_username` | 糸 | ユーザーを表すプライマリ ユーザー名です。 電子メール アドレス、電話番号、または指定された書式のない一般的なユーザー名を指定できます。 その値は、変更可能であり、時間の経過と共に変化することがあります。 これは変更可能であるため、この値は承認の決定には使用できません。 これは、ユーザー名のヒントとして使用することや、人間が判読できる UI でユーザー名として使用することができます。 この要求を受け取るには `profile` スコープが必要です。 v2.0 トークンにのみ存在します。 |
| `email` | 糸 | メールアドレスを持つゲスト アカウントに対して既定で使用されます。 アプリでは、`email`である  を使用して、管理対象ユーザー (リソースと同じテナントのユーザー) の電子メール要求を要求できます。 この値は正しいとは限りません。また、時間の経過と共に変化する場合があります。 認可に使用したり、ユーザーのデータを保存したりすることはできません。 アプリでアドレス指定可能なメール アドレスが必要な場合は、この要求を提案として使用するか、UX に事前に入力して、このデータをユーザーに直接要求します。 v2.0 エンドポイントでは、アプリで `email` OpenID Connect スコープを要求することもできます (要求を取得するためにオプション要求とスコープの両方を要求する必要はありません)。 |
| `name` | 糸 | `name`要求は、トークンのサブジェクトを識別する、人が認識できる値を示します。 この値は一意であるとは限らず、変更可能であり、表示目的でのみ使用する必要があります。 この要求を受け取るには `profile` スコープが必要です。 |
| `nonce` | 糸 | nonce は、IDP に対する元の承認要求に含まれるパラメーターと一致します。 一致しない場合は、アプリケーションによってトークンが拒否されます。 |
| `oid` | 文字列、GUID | オブジェクト (ここではユーザー アカウント) に対する変更不可の識別子です。 この ID によって、複数のアプリケーションでユーザーが一意に識別されます。同じユーザーにサインインする 2 つの異なるアプリケーションは `oid` 要求で同じ値を受け取ります。 Microsoft Graph は、この ID をユーザー アカウントの `id` プロパティとして返します。 `oid` では複数のアプリがユーザーを関連付けることができるため、この要求を受け取るには `profile` スコープが必要です。 1 人のユーザーが複数のテナントに存在する場合、そのユーザーのオブジェクト ID はテナントごとに異なります。つまり、そのユーザーが同じ資格情報で各アカウントにログインしても、それぞれ異なるアカウントと見なされます。 `oid` 要求は GUID であり、再利用することはできません。 |
| `roles` | 文字列の配列 | ログインしているユーザーに割り当てられた一連のロール。 |
| `rh` | あいまいな文字列 | トークンの再検証に使用される内部要求。 無視してください。 |
| `sub` | 糸 | トークン内の情報のサブジェクト。 たとえば、アプリのユーザーです。 この値は変更不可で、再割り当ても再利用もできません。 サブジェクトはペアワイズ識別子で、アプリケーション ID に一意です。 1 人のユーザーが 2 つの異なるクライアント ID を使用して 2 つの異なるアプリにサインインすると、そのアプリは、サブジェクト要求に対して 2 つの異なる値を受け取ることになります。 2 つの値が必要かどうかは、アーキテクチャやプライバシーの要件によって異なります。 |
| `tid` | 文字列、GUID | ユーザーがサインインしているテナントを表します。 職場または学校アカウントの場合、GUID はユーザーがサインインしている組織の不変のテナント ID です。 個人用 Microsoft アカウント テナント (Xbox、Teams for Life、Outlook のようなサービス) へのサインインの場合、値は `9188040d-6c67-4c5b-b112-36a304b66dad` です。 |
| `sid` | 文字列、GUID | セッションの一意識別子を表し、新しいセッションが確立されたときに生成されます。 |
| `unique_name` | 糸 | v1.0 トークンにのみ存在します。 トークンのサブジェクトを識別する、人が判読できる値を提供します。 この値は、テナント内で一意であることが保証されているわけではなく、表示目的でのみ使用する必要があります。 |
| `uti` | 糸 | トークン識別子要求。JWT 仕様の `jti` と同等です。 大文字と小文字を区別する一意のトークンごとの識別子。 |
| `ver` | 文字列、1.0 または 2.0 | ID トークンのバージョンを示します。 |
| `hasgroups` | ボーリアン | 存在する場合、常に true であり、ユーザーが 1 つ以上のグループに属していることを示します。 クライアントが Microsoft Graph API を使用して、ユーザーのグループを決定する必要があることを示します (`https://graph.microsoft.com/v1.0/users/{userID}/getMemberObjects`)。 |
| `groups:src1` | JSON オブジェクト | 長さは制限されていない (`hasgroups` を参照) が、まだトークンには大きすぎるトークン要求の場合は、そのユーザーの完全なグループ リストへのリンクが含まれます。 SAML では `groups` 要求の代わりに新しい要求として、JWT では分散要求として使用されます。 **JWT 値の例**: `"groups":"src1"``"_claim_sources`: `"src1" : { "endpoint" : "https://graph.microsoft.com/v1.0/users/{userID}/getMemberObjects" }` 詳細については、「グループ超過要求」を参照してください。 |

### 要求を使用してユーザーを確実に識別する

ユーザーを識別する場合は、時間が経過しても一定で一意の情報を使用することが重要です。 レガシ アプリケーションでは、メールアドレス、電話番号、UPN などのフィールドが使われることがあります。 これらのフィールドはすべて時間の経過と共に変わることがあり、時間の経過と共に再利用されることもあります。 たとえば、従業員の名前が変わったり、または従業員に以前の存在しなくなった従業員のメール アドレスに一致するアドレスが与えられたりする場合などです。 ユーザーを識別するために、人間が判読できるデータをアプリケーションで使用することはできません。 `sub`や`oid`要求など、Microsoft が提供する拡張要求を使用できます。

ユーザーごとに情報を正しく格納するには、`sub` または `oid` を単独で使用し (GUID は一意であるため)、必要に応じて `tid` をルーティングまたはシャーディングに使用します。 サービス間でデータを共有する必要がある場合は、すべてのアプリで、テナントで活動するユーザーに対して同じ `oid` と `tid` 要求が取得されるため、`oid` と `tid` が最適です。 `sub` 要求は、一意のペアワイズ値です。 値は、トークンの受信者、テナント、ユーザーの組み合わせに基づきます。 ユーザーに対して ID トークンを要求する 2 つのアプリは、`sub` 要求は異なっていても、そのユーザーに対して同じ `oid` 要求を受け取ることになります。

注意

テナント間でユーザーを関連付けようとして、`idp` 要求を使用して、ユーザーに関する情報を格納しないでください。 これは機能しません。ユーザーの `oid` および `sub` 要求は、設計によってテナント間で変わり、アプリケーションで、テナントを越えてユーザーを追跡できないようにしているためです。

ユーザーが 1 つのテナントに所属し、別のテナントで認証するゲスト シナリオでは、ユーザーがサービスに対して新しいユーザーであるかのように、ユーザーを処理する必要があります。 あるテナントのドキュメントと特権は、別のテナントには適用できません。 この制限は、テナント間での偶発的なデータ漏えいを防ぎ、データのライフサイクルを実施するために重要です。 テナントからゲストを退去させると、そのテナントで作成したデータへのゲストのアクセスも削除されます。

### グループ超過要求

トークンのサイズが HTTP ヘッダー サイズの上限を超えないよう、`groups` 要求に含まれるオブジェクト ID の数は制限されています。 超過制限 (SAML トークンの場合は 150、JWT トークンの場合は 200) を超えるグループのメンバーにユーザーがなっている場合、グループ要求はトークンに出力されません。 代わりに、Microsoft Graph API に照会してユーザーのグループ メンバーシップを取得するようアプリケーションに指示する超過要求がトークンに追加されます。

```json
{
  ...
  "_claim_names": {
   "groups": "src1"
    },
    {
  "_claim_sources": {
    "src1": {
        "endpoint":"[Url to get this user's group membership from]"
        }
       }
     }
  ...
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/id-tokens"} -->
## Microsoft ID プラットフォームの ID トークン - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens
- Service: identity-platform
- Article date: 2025-05-14
- Summary: Microsoft ID プラットフォームで使用される ID トークンについて説明します。

ID トークンは、認証の証明として機能し、ユーザーが正常に認証されたことを確認するセキュリティ トークンです。 ID トークンの情報により、クライアントは、電話会議での名前タグと同様に、ユーザーが自分の主張するユーザーであることを確認できます。 ID トークンは承認サーバーによって発行され、ユーザーについての情報を伝えるクレームを含んでいます。 これらはアクセス トークンと共に、またはその代わりに送信することができ、常に JWT (JSON Web トークン) 形式です。

ID トークンは、認可の証明として機能する [アクセス トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)とは異なります。 機密クライアントは ID トークンを検証する必要があります。 API を呼び出すために ID トークンを使用しないでください。

サードパーティのアプリケーションは ID トークンを理解するように設計されます。 承認のために ID トークンを使用しないでください。 承認には、アクセス トークンを使用します。 ID トークンによって提供されるクレームは、アプリケーション内の UX でデータベースのキーとして使用でき、クライアント アプリケーションへのアクセスが提供されます。 ID トークンで使用されるクレームの詳細については、「[ID トークン クレーム リファレンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-token-claims-reference)」を参照してください。 クレームベースの認可の詳細については、「[クレームを検証してアプリケーションと API をセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-validation)」を参照してください。

### トークンの形式

Microsoft ID プラットフォームで使用できる ID トークンには、v1.0 と v2.0 の 2 つのバージョンがあります。 トークンに含まれるクレームは、これらのバージョンによって決まります。 v1.0 と v2.0 の ID トークンは、含まれる情報に違いがあります。 バージョンは、要求元のエンドポイントに基づきます。 新しいアプリケーションでは、v2.0 を使用してください。

- v1.0: `https://login.microsoftonline.com/common/oauth2/authorize`
- v2.0: `https://login.microsoftonline.com/common/oauth2/v2.0/authorize`

#### v1.0 ID トークンのサンプル

```
eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IjdfWnVmMXR2a3dMeFlhSFMzcTZsVWpVWUlHdyIsImtpZCI6IjdfWnVmMXR2a3dMeFlhSFMzcTZsVWpVWUlHdyJ9.eyJhdWQiOiJiMTRhNzUwNS05NmU5LTQ5MjctOTFlOC0wNjAxZDBmYzljYWEiLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC9mYTE1ZDY5Mi1lOWM3LTQ0NjAtYTc0My0yOWYyOTU2ZmQ0MjkvIiwiaWF0IjoxNTM2Mjc1MTI0LCJuYmYiOjE1MzYyNzUxMjQsImV4cCI6MTUzNjI3OTAyNCwiYWlvIjoiQVhRQWkvOElBQUFBcXhzdUIrUjREMnJGUXFPRVRPNFlkWGJMRDlrWjh4ZlhhZGVBTTBRMk5rTlQ1aXpmZzN1d2JXU1hodVNTajZVVDVoeTJENldxQXBCNWpLQTZaZ1o5ay9TVTI3dVY5Y2V0WGZMT3RwTnR0Z2s1RGNCdGsrTExzdHovSmcrZ1lSbXY5YlVVNFhscGhUYzZDODZKbWoxRkN3PT0iLCJhbXIiOlsicnNhIl0sImVtYWlsIjoiYWJlbGlAbWljcm9zb2Z0LmNvbSIsImZhbWlseV9uYW1lIjoiTGluY29sbiIsImdpdmVuX25hbWUiOiJBYmUiLCJpZHAiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC83MmY5ODhiZi04NmYxLTQxYWYtOTFhYi0yZDdjZDAxMWRiNDcvIiwiaXBhZGRyIjoiMTMxLjEwNy4yMjIuMjIiLCJuYW1lIjoiYWJlbGkiLCJub25jZSI6IjEyMzUyMyIsIm9pZCI6IjA1ODMzYjZiLWFhMWQtNDJkNC05ZWMwLTFiMmJiOTE5NDQzOCIsInJoIjoiSSIsInN1YiI6IjVfSjlyU3NzOC1qdnRfSWN1NnVlUk5MOHhYYjhMRjRGc2dfS29vQzJSSlEiLCJ0aWQiOiJmYTE1ZDY5Mi1lOWM3LTQ0NjAtYTc0My0yOWYyOTU2ZmQ0MjkiLCJ1bmlxdWVfbmFtZSI6IkFiZUxpQG1pY3Jvc29mdC5jb20iLCJ1dGkiOiJMeGVfNDZHcVRrT3BHU2ZUbG40RUFBIiwidmVyIjoiMS4wIn0=.UJQrCA6qn2bXq57qzGX_-D3HcPHqBMOKDPx4su1yKRLNErVD8xkxJLNLVRdASHqEcpyDctbdHccu6DPpkq5f0ibcaQFhejQNcABidJCTz0Bb2AbdUCTqAzdt9pdgQvMBnVH1xk3SCM6d4BbT4BkLLj10ZLasX7vRknaSjE_C5DI7Fg4WrZPwOhII1dB0HEZ_qpNaYXEiy-o94UJ94zCr07GgrqMsfYQqFR7kn-mn68AjvLcgwSfZvyR_yIK75S_K37vC3QryQ7cNoafDe9upql_6pB2ybMVlgWPs_DmbJ8g0om-sPlwyn74Cc1tW3ze-Xptw_2uVdPgWyqfuWAfq6Q
```

この v1.0 のサンプル トークンは [jwt.ms](https://jwt.ms/#id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IjdfWnVmMXR2a3dMeFlhSFMzcTZsVWpVWUlHdyIsImtpZCI6IjdfWnVmMXR2a3dMeFlhSFMzcTZsVWpVWUlHdyJ9.eyJhdWQiOiJiMTRhNzUwNS05NmU5LTQ5MjctOTFlOC0wNjAxZDBmYzljYWEiLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC9mYTE1ZDY5Mi1lOWM3LTQ0NjAtYTc0My0yOWYyOTU2ZmQ0MjkvIiwiaWF0IjoxNTM2Mjc1MTI0LCJuYmYiOjE1MzYyNzUxMjQsImV4cCI6MTUzNjI3OTAyNCwiYWlvIjoiQVhRQWkvOElBQUFBcXhzdUIrUjREMnJGUXFPRVRPNFlkWGJMRDlrWjh4ZlhhZGVBTTBRMk5rTlQ1aXpmZzN1d2JXU1hodVNTajZVVDVoeTJENldxQXBCNWpLQTZaZ1o5ay9TVTI3dVY5Y2V0WGZMT3RwTnR0Z2s1RGNCdGsrTExzdHovSmcrZ1lSbXY5YlVVNFhscGhUYzZDODZKbWoxRkN3PT0iLCJhbXIiOlsicnNhIl0sImVtYWlsIjoiYWJlbGlAbWljcm9zb2Z0LmNvbSIsImZhbWlseV9uYW1lIjoiTGluY29sbiIsImdpdmVuX25hbWUiOiJBYmUiLCJpZHAiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC83MmY5ODhiZi04NmYxLTQxYWYtOTFhYi0yZDdjZDAxMWRiNDcvIiwiaXBhZGRyIjoiMTMxLjEwNy4yMjIuMjIiLCJuYW1lIjoiYWJlbGkiLCJub25jZSI6IjEyMzUyMyIsIm9pZCI6IjA1ODMzYjZiLWFhMWQtNDJkNC05ZWMwLTFiMmJiOTE5NDQzOCIsInJoIjoiSSIsInN1YiI6IjVfSjlyU3NzOC1qdnRfSWN1NnVlUk5MOHhYYjhMRjRGc2dfS29vQzJSSlEiLCJ0aWQiOiJmYTE1ZDY5Mi1lOWM3LTQ0NjAtYTc0My0yOWYyOTU2ZmQ0MjkiLCJ1bmlxdWVfbmFtZSI6IkFiZUxpQG1pY3Jvc29mdC5jb20iLCJ1dGkiOiJMeGVfNDZHcVRrT3BHU2ZUbG40RUFBIiwidmVyIjoiMS4wIn0=.UJQrCA6qn2bXq57qzGX_-D3HcPHqBMOKDPx4su1yKRLNErVD8xkxJLNLVRdASHqEcpyDctbdHccu6DPpkq5f0ibcaQFhejQNcABidJCTz0Bb2AbdUCTqAzdt9pdgQvMBnVH1xk3SCM6d4BbT4BkLLj10ZLasX7vRknaSjE_C5DI7Fg4WrZPwOhII1dB0HEZ_qpNaYXEiy-o94UJ94zCr07GgrqMsfYQqFR7kn-mn68AjvLcgwSfZvyR_yIK75S_K37vC3QryQ7cNoafDe9upql_6pB2ybMVlgWPs_DmbJ8g0om-sPlwyn74Cc1tW3ze-Xptw_2uVdPgWyqfuWAfq6Q) で表示できます。

#### v2.0 ID トークンのサンプル

```
eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IjFMVE16YWtpaGlSbGFfOHoyQkVKVlhlV01xbyJ9.eyJ2ZXIiOiIyLjAiLCJpc3MiOiJodHRwczovL2xvZ2luLm1pY3Jvc29mdG9ubGluZS5jb20vOTEyMjA0MGQtNmM2Ny00YzViLWIxMTItMzZhMzA0YjY2ZGFkL3YyLjAiLCJzdWIiOiJBQUFBQUFBQUFBQUFBQUFBQUFBQUFJa3pxRlZyU2FTYUZIeTc4MmJidGFRIiwiYXVkIjoiNmNiMDQwMTgtYTNmNS00NmE3LWI5OTUtOTQwYzc4ZjVhZWYzIiwiZXhwIjoxNTM2MzYxNDExLCJpYXQiOjE1MzYyNzQ3MTEsIm5iZiI6MTUzNjI3NDcxMSwibmFtZSI6IkFiZSBMaW5jb2xuIiwicHJlZmVycmVkX3VzZXJuYW1lIjoiQWJlTGlAbWljcm9zb2Z0LmNvbSIsIm9pZCI6IjAwMDAwMDAwLTAwMDAtMDAwMC02NmYzLTMzMzJlY2E3ZWE4MSIsInRpZCI6IjkxMjIwNDBkLTZjNjctNGM1Yi1iMTEyLTM2YTMwNGI2NmRhZCIsIm5vbmNlIjoiMTIzNTIzIiwiYWlvIjoiRGYyVVZYTDFpeCFsTUNXTVNPSkJjRmF0emNHZnZGR2hqS3Y4cTVnMHg3MzJkUjVNQjVCaXN2R1FPN1lXQnlqZDhpUURMcSFlR2JJRGFreXA1bW5PcmNkcUhlWVNubHRlcFFtUnA2QUlaOGpZIn0.1AFWW-Ck5nROwSlltm7GzZvDwUkqvhSQpm55TQsmVo9Y59cLhRXpvB8n-55HCr9Z6G_31_UbeUkoz612I2j_Sm9FFShSDDjoaLQr54CreGIJvjtmS3EkK9a7SJBbcpL1MpUtlfygow39tFjY7EVNW9plWUvRrTgVk7lYLprvfzw-CIqw3gHC-T7IK_m_xkr08INERBtaecwhTeN4chPC4W3jdmw_lIxzC48YoQ0dB1L9-ImX98Egypfrlbm0IBL5spFzL6JDZIRRJOu8vecJvj1mq-IUhGt0MacxX8jdxYLP-KUu2d9MbNKpCKJuZ7p8gwTL5B7NlUdh_dmSviPWrw
```

この v2.0 のサンプル トークンは [jwt.ms](https://jwt.ms/#id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IjFMVE16YWtpaGlSbGFfOHoyQkVKVlhlV01xbyJ9.eyJ2ZXIiOiIyLjAiLCJpc3MiOiJodHRwczovL2xvZ2luLm1pY3Jvc29mdG9ubGluZS5jb20vOTEyMjA0MGQtNmM2Ny00YzViLWIxMTItMzZhMzA0YjY2ZGFkL3YyLjAiLCJzdWIiOiJBQUFBQUFBQUFBQUFBQUFBQUFBQUFJa3pxRlZyU2FTYUZIeTc4MmJidGFRIiwiYXVkIjoiNmNiMDQwMTgtYTNmNS00NmE3LWI5OTUtOTQwYzc4ZjVhZWYzIiwiZXhwIjoxNTM2MzYxNDExLCJpYXQiOjE1MzYyNzQ3MTEsIm5iZiI6MTUzNjI3NDcxMSwibmFtZSI6IkFiZSBMaW5jb2xuIiwicHJlZmVycmVkX3VzZXJuYW1lIjoiQWJlTGlAbWljcm9zb2Z0LmNvbSIsIm9pZCI6IjAwMDAwMDAwLTAwMDAtMDAwMC02NmYzLTMzMzJlY2E3ZWE4MSIsInRpZCI6IjkxMjIwNDBkLTZjNjctNGM1Yi1iMTEyLTM2YTMwNGI2NmRhZCIsIm5vbmNlIjoiMTIzNTIzIiwiYWlvIjoiRGYyVVZYTDFpeCFsTUNXTVNPSkJjRmF0emNHZnZGR2hqS3Y4cTVnMHg3MzJkUjVNQjVCaXN2R1FPN1lXQnlqZDhpUURMcSFlR2JJRGFreXA1bW5PcmNkcUhlWVNubHRlcFFtUnA2QUlaOGpZIn0.1AFWW-Ck5nROwSlltm7GzZvDwUkqvhSQpm55TQsmVo9Y59cLhRXpvB8n-55HCr9Z6G_31_UbeUkoz612I2j_Sm9FFShSDDjoaLQr54CreGIJvjtmS3EkK9a7SJBbcpL1MpUtlfygow39tFjY7EVNW9plWUvRrTgVk7lYLprvfzw-CIqw3gHC-T7IK_m_xkr08INERBtaecwhTeN4chPC4W3jdmw_lIxzC48YoQ0dB1L9-ImX98Egypfrlbm0IBL5spFzL6JDZIRRJOu8vecJvj1mq-IUhGt0MacxX8jdxYLP-KUu2d9MbNKpCKJuZ7p8gwTL5B7NlUdh_dmSviPWrw) で表示できます。

### トークンの有効期間

既定では、ID トークンの有効期間は 1 時間です。1 時間後、クライアントは新しい ID トークンを取得する必要があります。

ID トークンの有効期間を調整すると、クライアント アプリケーションがアプリケーション セッションを期限切れにする頻度と、ユーザーに再認証を自動的にまたは対話形式で要求する頻度を制御できます。 詳細については、[構成可能なトークンの有効期間](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes)に関する記事を参照してください。

### トークンを検証する

ID トークンを検証するために、クライアントは、トークンが改ざんされていないかどうかを確認することができます。 また、発行者を検証して、正しい発行者がトークンを送り返したことを確認することもできます。 ID トークンは常に JWT トークンであるため、トークンを検証する多くのライブラリが存在しています。自ら検証するのではなく、これらのライブラリのいずれかを使用することをお勧めします。 ID トークンの検証は必ず Confidential クライアントで行う必要があります。 詳しくは、「[要求を検証してアプリケーションと API をセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-validation)」をご覧ください。

パブリック アプリケーション (ユーザーのブラウザーやホーム ネットワークなど、制御できないデバイスまたはネットワークで完全に実行されるコード) は、ID トークンの検証から利点が得られません。 このケースでは、悪意のあるユーザーによって、トークンの検証に使用するキーがインターセプトされて編集されるおそれがあります。

以下の JWT クレームは、トークン上の署名を検証した後、ID トークンで検証する必要があります。 トークン検証ライブラリで、次のクレームを検証することもできます。

- タイムスタンプ: `iat`、`nbf`、`exp` の各タイムスタンプがすべて、現在の時刻の前か後であることが必要です (該当する場合)。
- 対象: `aud` 要求がアプリケーションのアプリ ID と一致する必要があります。
- nonce: ペイロードの `nonce` 要求が、最初の要求で `/authorize` エンドポイントに渡された nonce パラメーターと一致する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/identifier-uri-restrictions"} -->
## Microsoft Entra アプリケーションの識別子 URI に関する制限事項 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/identifier-uri-restrictions
- Service: identity-platform
- Article date: 2025-01-29
- Summary: アプリ管理ポリシーが識別子 URI の追加をブロックする理由を理解し、識別子 URI に適用されるポリシーと制限の詳細を確認する

Microsoft Entra アプリケーションの `identifierUri` ( `Application ID URI` - プロパティとも呼ばれます) は、通常、リソース (API) アプリケーションで構成されるプロパティです。 このプロパティを安全に構成することは、リソースのセキュリティにとって重要です。

### セキュリティで保護されたパターン

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

注

*api://* スキームを使用している場合は、"api://" の直後に文字列値を追加します。 たとえば、*api://&lt;string&gt;* です。 その文字列値には、GUID または任意の文字列を指定できます。 GUID 値を追加する場合は、アプリ ID またはテナント ID と一致する必要があります。 文字列値を使用する場合は、テナントの検証済みのカスタム ドメインまたは初期ドメインを使用する必要があります。 *api://&lt;appId&gt;* を使用することをお勧めします。

Von Bedeutung

アプリケーション ID URI の値は、スラッシュ "/" 文字で終わってはいけません。

Von Bedeutung

アプリケーション ID URI の値は、テナント内で一意である必要があります。

### ポリシーを使用してセキュリティで保護されたパターンを適用する

Microsoft は、Microsoft Entra アプリケーションで識別子 URI ("アプリ ID URI" とも呼ばれます) の安全でない構成から保護するセキュリティ設定を導入しました。 このセキュリティ設定により、v1 アプリケーションに新しく追加された URI が、上記の セキュリティで保護されたパターン に準拠します。

#### ポリシーの動作

この設定を有効にすると、セキュリティで保護されたパターンが厳密に適用されます。 有効にすると、組織内の誰かが セキュリティで保護されたパターンに準拠していない識別子 URI を追加しようとすると、次のようなエラーが表示されます。

`Failed to add identifier URI {uri}. All newly added URIs must contain a tenant verified domain, tenant ID, or app ID, as per the default tenant policy of your organization. See https://aka.ms/identifier-uri-addition-error for more information on this error.`

アプリケーションの `api.requestedAccessTokenVersion` プロパティを `2` に設定することで、v2.0 Entra ID トークンを使用するように構成されているアプリケーションは、既定で除外されます。 サービス プリンシパルの `preferredSingleSignOnMode` プロパティを `SAML` に設定することで、SSO に SAML プロトコルを使用するように構成されているアプリケーションも、既定で除外されます。

アプリで既に構成されている既存の識別子 URI は影響を受けず、すべてのアプリは引き続き通常どおりに機能します。 これは、Microsoft Entra アプリ構成の新しい更新にのみ影響します。

有効になっていない場合でも、一部の安全でないパターンを引き続き使用できます。 たとえば、 `api://{string}` 形式の URI は引き続き追加できます。 ただし、設定が無効になっている場合でも、一部のシナリオ (たとえば、 `https://` スキームを使用する場合) には、テナントの検証済みドメインまたは初期ドメインが必要になることがあります。

#### ポリシーの有効化と管理

Microsoft は、セキュリティを強化するために、組織内でこのポリシーを既に有効にしている可能性があります。 [このスクリプト](https://aka.ms/check-identifier-uri-protection-state)を実行して確認できます。

Microsoft が組織内のポリシーを有効にした場合でも、テナント管理者はそのポリシーを完全に制御できます。 特定の Microsoft Entra アプリケーション、自分自身、組織内の別のユーザー、または組織が使用する任意のサービスまたはプロセスに [除外を付与](https://aka.ms/identifier-uri-protection-grant-exemptions) できます。 または、管理者は [ポリシーを無効](https://aka.ms/disable-identifier-uri-protection) にすることができます (**推奨されません**)。

変更によって中断される可能性のあるプロセスが組織で検出された場合、Microsoft は組織内のポリシーを有効にしません。 代わりに、組織内の管理者が自分で [有効にすることができます](https://aka.ms/enable-identifier-uri-protection) (**推奨**)。

#### 開発者向けのガイダンス

開発者で、所有する Microsoft Entra API に識別子 URI (アプリ ID URI とも呼ばれます) を追加しようとしているが、 このエラーが発生した場合は、このセクションをお読みください。

アプリに識別子 URI を追加するには、3 つの方法があります。 次の順序で行うことをお勧めします。

1. セキュリティで保護された URI パターンのいずれかを使用する
2. このエラーが発生した場合は、API で現在 v1.0 トークンが使用されていることを意味します。 v2.0 トークンを受け入れるようにサービスを更新することで、自分でブロックを解除できます。 V2.0 トークンは v1.0 に似ていますが、いくつかの [違いがあります](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-token-claims-reference)。 サービスが v2.0 トークンを処理できるようになったら、Microsoft Entra から v2.0 トークンが送信されるようにアプリ構成を更新できます。 これを行う簡単な方法は、 [Microsoft Entra 管理センターのアプリ登録エクスペリエンス](https://aka.ms/ra/prod)のマニフェスト エディターを使用することです。

    [Image: 更新トークンのバージョン エクスペリエンスのスクリーンショット。]

    ただし、 **この変更を行う場合は注意が**必要です。 これは、アプリが v2.0 トークン形式に更新されると、非準拠識別子 URI が構成されている場合、除外が許可されていない限り v1.0 トークンに切り替えることができないためです (オプション 3 を参照)。
3. v2.0 トークン形式に更新する前に、準拠していない識別子 URI をアプリに追加する必要がある場合は、 [アプリに除外を付与](https://aka.ms/identifier-uri-protection-grant-exemptions)するように管理者に要求できます。

### 追加のセキュリティ設定

Microsoft では、 `identifierUris` プロパティに対してより制限の厳しいセキュリティ ポリシーも提供しています。 このより制限の厳しいポリシーは、 `nonDefaultUriAddition`と呼ばれます。

この保護を有効にすると、既知のセキュリティで保護されたシナリオを除き、その組織内のどのアプリケーションにも新しいカスタム識別子 URI を追加できません。 具体的には、次のいずれかの条件が満たされている場合でも、識別子 URI を追加できます。

- アプリに追加される識別子 URI は、"既定" URI の 1 つであり、 `api://{appId}` または `api://{tenantId}/{appId}`
- アプリは、 `v2.0` Entra トークンを受け入れます。 これは、アプリの `api.requestedAccessTokenVersion` プロパティが `2` に設定されている場合に当てはまります。
- このアプリでは、シングル サインオン (SSO) に SAML プロトコルを使用します。 これは、アプリのサービス プリンシパルの `preferredSingleSignOnMode` プロパティが `SAML` に設定されている場合に当てはまります。
- URI が追加されているアプリ、または追加を実行しているユーザーまたはサービスに対して、管理者によって [除外](https://aka.ms/exempt-identifier-uri-additional-restriction) が付与されています。

この保護が有効になると、組織内の誰かが v1 アプリケーションにカスタム識別子 URI を追加しようとすると、次のようなエラーが表示されます。

`The newly added URI {uri} must comply with the format 'api://{appId}' or 'api://{tenantId}/{appId}' as per the default app management policy of your organization. If the requestedAccessTokenVersion is set to 2, this restriction may not apply. See https://aka.ms/identifier-uri-addition-error for more information on this error. `

このより制限の厳しいポリシーは、 `audience` 要求の一般的なトークン検証エラーから組織を保護するのに役立ちます。 可能であれば有効にすることをお勧めしますが、Microsoft はユーザーに代わって有効にしません。

組織内でこのより制限の厳しいポリシーを有効にするには、 [このスクリプト](https://aka.ms/enable-identifier-uri-additional-restriction)を実行します。

他のポリシーと同様に、管理者はこのポリシーに [除外を付与](https://aka.ms/exempt-identifier-uri-additional-restriction) したり、有効にした後 [で無効](https://aka.ms/disable-identifier-uri-additional-restriction) にしたりすることもできます。

### FAQ

#### 識別子 URI とは

識別子 URI ("アプリ ID URI" とも呼ばれます) を使用すると、リソース (API) 開発者はアプリケーションの文字列値をその識別子として指定できます。 API のトークンを取得したクライアントは、OAuth 要求中にこの文字列値を使用できます。 たとえば、API が `https://api.contoso.com` の識別子 URI を構成している場合、API のクライアントは、Microsoft Entra への OAuth 要求でその値を指定できます。 この識別子 URI は、v1.0 アクセス トークンの対象ユーザー要求として使用されます。

識別子 URI は、アプリ登録の [API の公開] ページを使用して構成 [されます](https://aka.ms/ra/prod)。 アプリの登録では、識別子 URI はアプリケーション ID URI と呼ばれます。これは識別子 URI と同義です。

[Image: 識別子 URI 構成エクスペリエンスのスクリーンショット。]

#### これらのポリシーのしくみ

適用は、組織の [アプリ管理ポリシー](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy?view=graph-rest-beta&preserve-view=true)を構成することによって有効になります。 テナント管理者は、そのオンとオフを切り替えることができます。 Microsoft では、2025 年 6 月と 7 月の間に、一部の組織で既定で有効にしています。

[組織で保護が有効になっているかどうかを確認する方法について説明します](https://aka.ms/check-identifier-uri-protection-state)

Microsoft では既定でこの設定を有効にしていますが、テナント管理者は管理を維持します。 オン、オフ、または例外を許可することができます。

#### SAML アプリケーションを構成するときにこのエラーが発生する理由

SAML アプリケーションは、既定では識別子 URI の制限から除外されます。 ただし、除外を適用するには、明示的に SAML アプリケーションとして指定する必要があります。

[エンタープライズ アプリケーション] の [シングル サインオン] ページを使用して SAML セットアップが構成されている場合、アプリは SAML アプリとして自動的に示されます。 そうでない場合は、サービス プリンシパルの `preferredSingleSignOn` mode プロパティを `SAML` に設定することで、アプリを SAML アプリとして指定できます。

これを行うには、次の要求を行います。 サービス プリンシパルのオブジェクト ID は、 [Enterprise アプリケーション エクスペリエンス](https://entra.microsoft.com/#view/Microsoft_AAD_IAM/StartboardApplicationsMenuBlade/%7E/AppAppsPreview)から取得できます。

```http
PATCH https://graph.microsoft.com/v1.0/servicePrincipals/{objectIdOfServicePrincipal}
```

```json
{
    "preferredSingleSignOnMode": "SAML"
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/identity-platform-integration-checklist"} -->
## Microsoft ID プラットフォームに関するベスト プラクティス - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/identity-platform-integration-checklist
- Service: identity-platform
- Article date: 2023-11-22
- Summary: Microsoft ID プラットフォームと統合する場合のベスト プラクティス、推奨事項、よくある見落としについて説明します。

この記事では、Microsoft ID プラットフォームと統合する場合のベスト プラクティス、推奨事項、よくある見落としについて説明します。 このチェックリストに従うと、高品質で安全な統合を実現できます。 この一覧を定期的に確認して、アプリと ID プラットフォームとの統合の品質とセキュリティを維持してください。 チェックリストは、アプリケーション全体を確認することを目的としていません。 チェックリストの内容は、プラットフォームの改善に伴って変更される可能性があります。

まだ使い始めたばかりの場合は、[Microsoft ID プラットフォームのドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/)を確認して、Microsoft ID プラットフォームでの認証の基本、アプリケーション シナリオなどについて学習してください。

次のチェックリストは、アプリケーションが [Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/)と効果的に統合されていることを確認するために使用します。

ヒント

"*統合アシスタント*" は、これらのベスト プラクティスと推奨事項の多くを適用するのに役立ちます。 アシスタントを使い始めるには、いずれかのアプリの登録を選び、**[統合アシスタント]** メニュー項目を選択します。

### 基本

[Image: チェックボックス][Microsoft プラットフォーム ポリシー](https://learn.microsoft.com/ja-jp/legal/microsoft-identity-platform/terms-of-use)を読んで理解します。 ユーザーとプラットフォームを保護するために設計時に要点をまとめた条項にアプリケーションが従っていることを確認します。

### 所有権

[Image: チェックボックス] アプリの登録と管理に使用したアカウントに関連付けられている情報が最新であることを確認します。

### ブランディング

[Image: チェックボックス] 「[アプリケーションのブランド化ガイドライン](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-branding-in-apps)」に従います。

[Image: チェックボックス] アプリケーションにわかりやすい名前とロゴを用意します。 この情報は、[アプリケーションの同意プロンプト](https://learn.microsoft.com/ja-jp/entra/identity-platform/application-consent-experience)に表示されます。 ユーザーが情報に基づいて決定を下せるように、名前とロゴが会社や製品を表現するものにします。 商標に違反していないことを確認します。

### プライバシー

[Image: チェックボックス] アプリのサービス使用条件とプライバシーに関する声明のリンクを用意します。

### セキュリティ

[Image: チェックボックス] リダイレクト URI を管理します。

- すべてのリダイレクト URI の所有権を維持し、それらの DNS レコードを最新の状態に保ちます。
- URI にワイルドカード (\*) を使用しないでください。
- Web アプリの場合は、すべての URI がセキュリティで保護され、暗号化されていることを確認します (たとえば、https スキームの使用など)。
- パブリック クライアントの場合、該当する場合 (主に iOS および Android) はプラットフォーム固有のリダイレクト URI を使用します。 それ以外の場合は、アプリにコール バックするときの競合を防ぐために、ランダム性が高いリダイレクト URI を使用します。
- アプリが独立した Web エージェントから使用されている場合は、`https://login.microsoftonline.com/common/oauth2/nativeclient` を使用できます。
- 未使用または不要なリダイレクト URI を定期的に確認して整理します。

[Image: チェックボックス] アプリがディレクトリに登録されている場合は、アプリ登録所有者の一覧を最小化して手動で監視します。

[Image: チェックボックス] 明示的に必要な場合を除き、[OAuth2 の暗黙的な許可フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow)のサポートを有効にしないでください。

[Image: チェックボックス] ユーザー名/パスワードにうまく対処します。 ユーザーのパスワードを直接処理する[リソース所有者パスワード資格情報フロー (ROPC)](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc) は使わないでください。 このフローには高度な信頼とユーザーの公開が必要であり、他のより安全なフローを使用できない場合にのみ使用します。 このフローは、一部のシナリオ (DevOps など) ではまだ必要ですが、それを使用するとアプリケーションに制約が課せされることに注意してください。 最新の手法については、「[認証フローとアプリケーションのシナリオ](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios)」を参照してください。

[Image: チェックボックス] Web アプリ、Web API、およびデーモン アプリに対応した機密アプリの資格情報を保護および管理します。 パスワードの資格情報 (クライアント シークレット) ではなく、[証明書の資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials)を使用します。 パスワードの資格情報を使用する必要がある場合は、手動で設定しないでください。 資格情報は、コードまたは構成に格納しないでください。また、資格情報の人間による処理を許可しないでください。 可能であれば、[Azure リソースのマネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) または [Azure Key Vault](https://learn.microsoft.com/ja-jp/azure/key-vault/general/basic-concepts) を使用して資格情報を格納し、定期的にローテーションします。

[Image: チェックボックス] アプリケーションで最低限の特権のアクセス許可が要求されていることを確認します。 アプリケーションに絶対に必要なアクセス許可のみを必要なときにのみ必須とします。 [各種アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#types-of-permissions)を理解します。 必要に応じて、アプリケーションのアクセス許可のみを使用します。可能であれば、委任されたアクセス許可を使用してください。 Microsoft Graph のアクセス許可の一覧については、こちらの[アクセス許可リファレンス](https://learn.microsoft.com/ja-jp/graph/permissions-reference)を参照してください。

[Image: チェックボックス] Microsoft ID プラットフォームを使用して API をセキュリティで保護している場合は、公開する必要があるアクセス許可について慎重に検討します。 ソリューションに適した細分性と、管理者の同意が必要なアクセス許可を考慮します。 どのような承認でも、決定する前に受信トークンで予想されるアクセス許可を確認します。

### 実装

[Image: チェックボックス] 最新の認証ソリューション (OAuth 2.0、[OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)) を使用して安全にユーザーのサインインを行います。

[Image: チェックボックス] OAuth 2.0 や Open ID などのプロトコルに対する直接的なプログラミングは行いません。 代わりに、[Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) を活用してください。 MSAL ライブラリでは、使いやすいライブラリ内に安全にセキュリティ プロトコルがラップされており、[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)のシナリオに対する組み込みのサポート、デバイス全体の[シングル サインオン (SSO)](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)、および組み込みのトークン キャッシュ サポートを利用できます。 詳細については、Microsoft でサポートされている[クライアント ライブラリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries)の一覧を参照してください。 認証プロトコル用に手作業でコーディングする必要がある場合、[Microsoft SDL](https://www.microsoft.com/sdl/default.aspx) か同様の開発手法に従ってください。 各プロトコルの標準仕様におけるセキュリティの考慮事項に十分注意してください。

[Image: チェックボックス] アクセス トークンの値を調べたり、クライアントとして解析を試みたり**しないでください**。 値や形式が変化したり、警告なしで暗号化されたりする可能性があります。クライアントでユーザーに関する情報が必要な場合は、常に ID トークンを使ってください。 アクセス トークンの解析は、Web API でのみ行う必要があります (これは、形式の定義と暗号化キーの設定は、Web API で行われているためです)。 アクセス トークンは、特定のリソースへのアクセスを許可する機密の資格情報であるため、クライアントがそれを API に直接送信することはセキュリティ リスクです。 開発者は、アクセス トークンを検証するためにクライアントを信頼できると想定しないようにする必要があります。

[Image: チェックボックス] Azure Active Directory Authentication Library (ADAL) から [Microsoft Authentication Library](https://learn.microsoft.com/ja-jp/entra/msal/) へ既存のアプリを移行します。 MSAL は Microsoft の最新の ID プラットフォーム ソリューションであり、.NET、JavaScript、Android、iOS、macOS、Python、Java で利用できます。 [ADAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/msal-net-migration)、[ADAL.js](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-compare-msal-js-and-adal-js)、および [ADAL.NET と iOS ブローカー](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-net-migration-ios-broker)アプリの移行に関する詳細を確認してください。

[Image: チェックボックス] モバイル アプリの場合、アプリケーションの登録エクスペリエンスを使用して、各プラットフォームを構成します。 アプリケーションでのシングル サインインに Microsoft Authenticator または Microsoft ポータル サイトを利用するには、アプリに "ブローカー リダイレクト URI" が構成されている必要があります。 これにより、認証後に Microsoft からアプリケーションに制御を返すことができます。 各プラットフォームを構成するときに、アプリの登録エクスペリエンスにプロセスが表示されます。 クイックスタートを使用して、実際の例をダウンロードします。 iOS 上では、可能な限りブローカーと System Webview を使用します。

[Image: チェックボックス] Web アプリまたは Web API では、アカウントごとに 1 つのトークン キャッシュを保持します。 Web アプリの場合、トークン キャッシュは、アカウント ID によってキー指定されている必要があります。 Web API の場合、アカウントは、API の呼び出しに使用されるトークンのハッシュによって、キー指定されている必要があります。 MSAL.NET では、.NET と .NET Framework の両方でカスタム トークン キャッシュのシリアル化が提供されます。 セキュリティとパフォーマンス上の理由から、ユーザーごとに 1 つのキャッシュをシリアル化することをお勧めします。 詳細については、[トークン キャッシュのシリアル化](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/token-cache-serialization)に関するページを参照してください。

[Image: チェックボックス] アプリに必要なデータを [Microsoft Graph](https://developer.microsoft.com/graph) を介して入手できる場合は、個々の API ではなく Microsoft Graph エンドポイントを使用してこのデータに対するアクセス許可を要求します。

### エンド ユーザー エクスペリエンス

[Image: チェックボックス][同意エクスペリエンスを理解し](https://learn.microsoft.com/ja-jp/entra/identity-platform/application-consent-experience)、アプリを信頼するかどうかを判断できる十分な情報がエンド ユーザーと管理者に与えられるように、アプリの同意プロンプトを構成します。

[Image: チェックボックス] 対話フローの前にサイレント認証 (サイレント トークン取得) を試行することで、アプリの使用中にユーザーがログイン資格情報を入力する必要がある回数を最小限に抑えます。

[Image: チェックボックス] サインインするたびに "prompt=consent" を使わないでください。 追加のアクセス許可について同意を求める必要があると判断した場合 (たとえば、アプリに必要なアクセス許可を変更した場合など) に限り、"prompt=consent" を使います。

[Image: チェックボックス] 該当する場合は、ユーザー データを使用してアプリケーションを強化します。 これを行うには、[Microsoft Graph API](https://developer.microsoft.com/graph) を使用するのが簡単です。 初めて利用するときに役立つ [Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer) ツール。

[Image: チェックボックス] 管理者がテナントに簡単に同意を許可できるように、アプリに必要なすべてのアクセス許可を登録します。 実行時に[増分同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#consent)を使用して、最初の起動時に要求されたときにユーザーが懸念する、または混乱する可能性があるアクセス許可をアプリケーションが要求する理由をわかりやすくします。

[Image: チェックボックス][クリーン シングル サインアウト エクスペリエンス](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/1-WebApp-OIDC/1-6-SignOut)を実装します。 これはプライバシーとセキュリティの要件であり、優れたユーザー エクスペリエンスのために役立ちます。

### テスト

[Image: チェックボックス] ユーザーがアプリケーションを使うことができるかどうかに影響する[条件付きアクセス ポリシー](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/1-WebApp-OIDC/1-6-SignOut)をテストします。

[Image: チェックボックス] サポートする予定がある可能性のあるすべてのアカウント (職場または学校のアカウント、個人用の Microsoft アカウント、子供のアカウント、ソブリン アカウントなど) を使用してアプリケーションをテストします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/identity-videos"} -->
## Microsoft ID プラットフォームのビデオ - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/identity-videos
- Service: identity-platform
- Article date: 2023-01-06
- Summary: 先進認証および Microsoft ID プラットフォームに関するビデオの一覧

先進認証の基礎、Microsoft ID プラットフォームと Microsoft 認証ライブラリ (MSAL) について説明します。

### 開発者向け Microsoft ID プラットフォーム

Microsoft ID プラットフォームの主要なコンポーネントと機能について説明します。

[Microsoft ID プラットフォームとは](https://www.youtube.com/watch?v=uDU1QTSw7Ps) (14:54)

[先進認証の基本 - Microsoft ID プラットフォーム](https://www.youtube.com/watch?v=tkQJSHFsduY) (12:28)

[概要:モバイル アプリケーションでのシングル サインオンの実装 - Microsoft ID プラットフォーム](https://www.youtube.com/watch?v=JpeMeTjQJ04) (20:30)

[先進認証: ここまでの軌跡 – Microsoft ID プラットフォーム](https://www.youtube.com/watch?v=7_vxnHiUA1M) (15:47)

### 開発者向けトレーニング シリーズ

"開発者向け ID" のビデオ シリーズでは、Matthijs Hoekstra と Kyle Marsh が Microsoft ID プラットフォームに関する概要をガイド付きで提供します。 プラットフォームの主要なコンポーネントと機能、およびその認証ライブラリを使用してアプリへの最新のセキュリティ保護された認証の追加を始める方法について学習してください。

このシリーズの内容は実施された多くのトレーニング セッションを通して厳選され、磨き上げられており、開発者が Azure での ID の使用を始めるのに最適です。

1 - [開発者向け Microsoft ID プラットフォームの概要](https://www.youtube.com/watch?v=zjezqZPPOfc&amp;list=PLLasX02E8BPBxGouWlJV-u-XZWOc2RkiX&amp;index=1) (33:55)

2 - [Microsoft ID プラットフォームを使用してアプリのユーザーを認証する方法](https://www.youtube.com/watch?v=Mtpx_lpfRLs&amp;list=PLLasX02E8BPBxGouWlJV-u-XZWOc2RkiX&amp;index=2) (29:09)

3 - [Microsoft ID プラットフォームのアクセス許可と同意のフレームワーク](https://www.youtube.com/watch?v=toAWRNqqDL4&amp;list=PLLasX02E8BPBxGouWlJV-u-XZWOc2RkiX&amp;index=3) (45:08)

4 - [Microsoft ID プラットフォームを使用して API を保護する方法](https://www.youtube.com/watch?v=IIQ7QW4bYqA&amp;list=PLLasX02E8BPBxGouWlJV-u-XZWOc2RkiX&amp;index=4) (33:17)

5 - [Microsoft ID プラットフォームでのアプリケーション ロールとセキュリティ グループ](https://www.youtube.com/watch?v=-BK2iBDrmNo&amp;list=PLLasX02E8BPBxGouWlJV-u-XZWOc2RkiX&amp;index=5) (15:52)

### 認証の基礎

ID プロバイダー、セキュリティ トークン、要求、対象ユーザーなどの概念に詳しくない場合は、このビデオ シリーズを見ると、最新の認証における概念とコンポーネントのことがよくわかります。

1 - [基本: 先進認証の概念](https://www.youtube.com/watch?v=fbSVgC8nGz4&amp;list=PLLasX02E8BPD5vC2XHS_oHaMVmaeHHPLy&amp;index=1) (4:33)

2 - [Web アプリケーションの先進認証](https://www.youtube.com/watch?v=tCNcG1lcCHY&amp;list=PLLasX02E8BPD5vC2XHS_oHaMVmaeHHPLy&amp;index=2) (6:02)

3 - [Web シングル サインオン](https://www.youtube.com/watch?v=51B-jSOBF8U&amp;list=PLLasX02E8BPD5vC2XHS_oHaMVmaeHHPLy&amp;index=3) (4:13)

4 - [フェデレーション Web 認証](https://www.youtube.com/watch?v=CjarTgjKcX8&amp;list=PLLasX02E8BPD5vC2XHS_oHaMVmaeHHPLy&amp;index=4) (6:19)

5 - [ネイティブ クライアント アプリケーション - パート 1](https://www.youtube.com/watch?v=OGMDnuDrAcQ&amp;list=PLLasX02E8BPD5vC2XHS_oHaMVmaeHHPLy&amp;index=5) (8:12)

6 - [ネイティブ クライアント アプリケーション - パート 2](https://www.youtube.com/watch?v=2RE6IhXfmHY&amp;list=PLLasX02E8BPD5vC2XHS_oHaMVmaeHHPLy&amp;index=6) (5:33)

### Microsoft ID プラットフォームの基礎

Microsoft ID プラットフォームである Microsoft Authentication Library (MSAL) のコンポーネントの概要と、これらのコンポーネントが Microsoft Entra ID とどのように連携するかについて説明します。 One Dev Question のビデオの長さは 1 から 2 分です。

[Microsoft ID プラットフォームの概要](https://www.youtube.com/watch?v=bNlcFuIo3r8)

[ライブラリの MSAL ファミリとは何ですか?](https://www.youtube.com/watch?v=yLVEBU9Z96Q)

[範囲の説明](https://www.youtube.com/watch?v=eiPHOoLmGJs)

[ブローカーとは](https://www.youtube.com/watch?v=Zd_Uubnu0U0)

[リダイレクト URI の機能](https://www.youtube.com/watch?v=znSN_3JAuoU)

[テナントの説明](https://www.youtube.com/watch?v=mDhT4Zv1fZU)

[Microsoft Entra ID のロール](https://www.youtube.com/watch?v=zDEC7A5ZS2Q)

[Microsoft Entra アプリ オブジェクトのロール](https://www.youtube.com/watch?v=HEpq_YSmuWw)

[Microsoft の組織アカウントと個人アカウントの違い](https://www.youtube.com/watch?v=E2OUluQQKSk)

[SPA と Web アプリの違い](https://www.youtube.com/watch?v=ZJirt7eTVw8)

[アプリケーションのアクセス許可と委任されたアクセス許可の違いは何か?](https://www.youtube.com/watch?v=6R3W9T01gdE)

[Microsoft ID プラットフォーム OpenID Connect 認定とは何か?](https://www.youtube.com/watch?v=Gm6sALdXtpg)

[Microsoft Entra アプリにはどのような種類があり、どのような違いがあるか?](https://www.youtube.com/watch?v=NrydwrckYaw)

[MSAL を使用する場合にプロトコルの本質的な概念としてどのようなことを知っておく必要があるか?](https://www.youtube.com/watch?v=cZKgTqF4o88)

[ID トークン、アクセス トークン、更新トークン、セッション トークンの違いは何か?](https://www.youtube.com/watch?v=41vmzPdbfXM)

[承認要求とトークンの関係はどのようなものか?](https://www.youtube.com/watch?v=jEEwN7XAtUo)

[MSAL ライブラリによってプロトコルがどのように使いやすくなるか?](https://www.youtube.com/watch?v=4pwuRYcZbz4)

### v1.0 から v2.0 に移行する

Active Directory 認証ライブラリ (ADAL) から MSAL への移行など、最新バージョンの Microsoft ID プラットフォームへの移行について説明します。

[ADAL から MSAL に移行する理由](https://www.youtube.com/watch?v=qpdC45tZYDg)

[ADAL コードベースを MSAL に移行する](https://www.youtube.com/watch?v=xgL_z9yCnrE)

[MSAL が ADAL より優れている点](https://www.youtube.com/watch?v=q-TDszj2O-4)

[v1 認証と v2 認証の違いは何か?](https://www.youtube.com/watch?v=aBMUxC4evhU)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/index-desktop"} -->
## デスクトップ アプリケーション認証のドキュメント - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/index-desktop
- Service: identity-platform
- Article date: 2025-02-11
- Summary: Microsoft が提供するクイック スタート、チュートリアル、および詳細な攻略ガイドを使用して、デスクトップ アプリケーションでのユーザーのサインインと Web API へのアクセスの方法について説明します。 

Microsoft が提供するクイック スタート、チュートリアル、および詳細な攻略ガイドを使用して、デスクトップ アプリケーションでのユーザーのサインインと Web API へのアクセスの方法について説明します。

### 作業の開始

#### クイックスタート

- [Node.js - Electron](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-desktop-app-nodejs-electron-sign-in)
- [Windows Presentation Foundation (WPF)](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-desktop-app-wpf-sign-in)

### 構築して学習する

#### チュートリアル

- [Node.js - Electron](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-nodejs-desktop)
- [Windows Presentation Foundation (WPF)](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-windows-desktop)

### シナリオの詳細

#### 攻略ガイド

- [Web API を呼び出すデスクトップ アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-app-configuration)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/index-mobile"} -->
## モバイル アプリケーション認証に関するドキュメント - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/index-mobile
- Service: identity-platform
- Article date: 2025-02-11
- Summary: クイック スタート、チュートリアル、詳細なハウツー ガイドを使用して、モバイル アプリケーションでユーザーをサインインさせ、Web API にアクセスする方法について説明します。 

クイック スタート、チュートリアル、詳細なハウツー ガイドを使用して、モバイル アプリケーションでユーザーをサインインさせ、Web API にアクセスする方法について説明します。

### 始めましょう

#### クイックスタート

- [アンドロイド](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-android-sign-in)
- [iOS と macOS](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-mobile-app-ios-sign-in)

### 構築して学習する

#### チュートリアル

- [アンドロイド](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-android)
- [Android - 共有デバイス モード](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-shared-device-mode)
- [iOS と macOS](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-ios)

### シナリオの詳細

#### 攻略ガイド

- [Web API を呼び出すモバイル アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-mobile-app-configuration)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/index-service"} -->
## バックエンド サービス、デーモン、スクリプトの認証に関するドキュメント - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/index-service
- Service: identity-platform
- Article date: 2025-02-11
- Summary: デーモン、サービス、非対話型スクリプトの保護された Web API にアクセスする方法については、クイック スタート、チュートリアル、詳細なハウツー ガイドを参照してください。 

デーモン、サービス、非対話型スクリプトの保護された Web API にアクセスする方法については、クイック スタート、チュートリアル、詳細なハウツー ガイドを参照してください。

### 始めましょう

#### クイックスタート

- [ASP.NET コア](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-dotnet-acquire-token)
- [ジャワ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-app-java-acquire-token)
- [Node.js](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-console-app-nodejs-acquire-token)
- [Python（プログラミング言語）](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-daemon-app-python-acquire-token)

### 構築して学習する

#### チュートリアル

- [ASP.NET](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-aspnet-daemon-web-app)
- [Node.js](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-nodejs-console)

### シナリオの詳細

#### 攻略ガイド

- [Web API を呼び出すサービス、デーモン、またはスクリプト](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-daemon-app-configuration)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/index-spa"} -->
## シングルページ アプリケーション (SPA) のドキュメント - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/index-spa
- Service: identity-platform
- Article date: 2025-02-11
- Summary: Microsoft が提供するクイック スタート、チュートリアル、および詳細な攻略ガイドを使用して、シングルページ アプリでのユーザーのサインインと Web API へのアクセスの方法について説明します。 

Microsoft が提供するクイック スタート、チュートリアル、および詳細な攻略ガイドを使用して、シングルページ アプリでのユーザーのサインインと Web API へのアクセスの方法について説明します。

### 作業の開始

#### クイックスタート

- [JavaScript](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-sign-in)
- [反応する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-sign-in)
- [Angular（アンギュラー）](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-sign-in)
- [Blazor WebAssembly（ブレイザー ウェブアセンブリ）](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-single-page-app-blazor-wasm-sign-in)

### 構築して学習する

#### チュートリアル

- [JavaScript](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-javascript-prepare-app)
- [反応する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-react-prepare-app)
- [Angular（アンギュラー）](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-angular-auth-code)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/index-web-api"} -->
## Web API のドキュメント - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/index-web-api
- Service: identity-platform
- Article date: 2025-04-04
- Summary: Microsoft が提供するクイック スタート、チュートリアル、および詳細な攻略ガイドを使用して、Web API の保護、およびアップストリーム API からのダウンストリーム Web API の呼び出しの方法について説明します。 

Microsoft が提供するクイック スタート、チュートリアル、および詳細な攻略ガイドを使用して、Web API の保護、およびアップストリーム API からのダウンストリーム Web API の呼び出しの方法について説明します。

### 作業の開始

#### クイックスタート

- [ASP.NET](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-api-aspnet-protect-api)
- [ASP.NET コア](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-api-aspnet-core-protect-api)

### 構築して学習する

#### チュートリアル

- [ASP.NET Core - Web API の構築と保護](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-api-dotnet-core-build-app)
- [ASP.NET Core - 保護された Web API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-api-dotnet-core-call-protected-api)

### 詳細なシナリオ

#### 攻略ガイド

- [cURL を使用して Web API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-call-a-web-api-with-curl)
- [Insomnia を使用して Web API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-call-a-web-api-with-rest-client)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/index-web-app"} -->
## Web アプリケーションのドキュメント - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/index-web-app
- Service: identity-platform
- Article date: 2025-02-11
- Summary: Microsoft が提供するクイック スタート、チュートリアル、および詳細な攻略ガイドを使用して、サーバーベースの Web アプリでのユーザーのサインインと Web API へのアクセスの方法について説明します。 

Microsoft が提供するクイック スタート、チュートリアル、および詳細な攻略ガイドを使用して、サーバーベースの Web アプリでのユーザーのサインインと Web API へのアクセスの方法について説明します。

### 概要

#### クイックスタート

- [ユーザーがサインインする Web アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-web-app-sign-in)

### 構築して学習する

#### チュートリアル

- [ASP.NET Core](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-dotnet-prepare-app)
- [Node.js (Express 使用)](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-nodejs-webapp-msal)
- [パイソンフラスコ](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-python-register-app)

### Web アプリの詳細

#### 攻略ガイド

- [Web API を呼び出す Web アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-api-call-api-app-configuration)

### 詳細なシナリオ

#### チュートリアル

- [Web アプリ アクセスのストレージと Microsoft Graph をセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-overview)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/ios-qr-code-pin-authentication"} -->
## iOS/macOS アプリで QR コードと PIN 認証を設定する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/ios-qr-code-pin-authentication
- Service: identity-platform
- Article date: 2025-07-24
- Summary: iOS および macOS 用の Microsoft 認証ライブラリを使用して QR コードと PIN 認証を使用するように iOS アプリを構成する方法について説明します。

QR コード認証方法を使用すると、現場担当者は共有デバイス上のアプリにすばやく簡単にサインインできます。 ユーザーは、管理者が提供する一意の QR コードを使用し、PIN を入力してサインインできるため、ユーザー名とパスワードを入力する必要がなくなります。

*login.microsoft.com* で利用できる QR コード Web サインイン エクスペリエンスを使用できます。 このユーザー エントリ ポイントでは、開発者の変更は必要ありません。 ユーザーが [**サインイン オプション**] を選択&gt;**組織にサインイン**&gt;**QR コードでサインイン**します。 サインイン ページにエントリ ポイントを指定することで、QR コードのサインイン エクスペリエンスを最適化し、ユーザーが 2 回クリックする必要がなくなります。 QR コード認証方法を利用するために、アプリ開発者と [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) は連携して作業します。

- アプリ開発者は、iOS および macOS 用の Microsoft Authentication Library (MSAL) を使用して、QR コード認証の最適化されたエントリ ポイントをアプリに統合します。
- 認証ポリシー管理者は、Microsoft Entra IDで[認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-authentication-qr-code)を構成します。

### QR コード認証を使用するようにアプリを構成する

QR コード認証を使用するようにアプリを構成するには、MSAL で `getDeviceInformationWithParameters` API を呼び出して、 `MSALDeviceInformation` オブジェクトを受け取ることができます。 このオブジェクトでは、シングル サインオン (SSO) 拡張機能の構成で管理者が構成した QR コード認証を反映する新しいフラグを使用できます。 次のコード スニペットは、優先する認証方法を取得する方法を示しています。

```objectivec

@property (nonatomic, readonly) MSALPreferredAuthMethod configuredPreferredAuthMethod; 

```

`MSALPreferredAuthMethod` は、使用可能なさまざまな認証方法を記述する列挙体です。 `configuredPreferredAuthMethod` プロパティを使用すると、アプリケーションの優先認証方法を取得できます。 現在、QR コードはプライベート列挙値 1 です。 一般提供 (GA) としてリリースされると、それは `MSALPreferredAuthMethodQRPIN` です。

`MSALInteractiveTokenParameters` また、 `MSALPreferredAuthMethod: preferredAuthMethod`型の新しい省略可能なパラメーターも定義します。 このパラメーターが QR コード認証に設定されている場合、結果として得られる対話型サインイン UI は、ユーザーを QR コード認証エントリ ページに直接移動します。 次のコード スニペットは、QR コード認証を使用するようにアプリを構成する方法を示しています。

```objectivec
MSALWebviewParameters *webParameters = [[MSALWebviewParameters alloc] initWithAuthPresentationViewController:viewController]; 

MSALInteractiveTokenParameters *interactiveParams = [[MSALInteractiveTokenParameters alloc] initWithScopes:scopes webviewParameters:webParameters]; 

interactiveParams.preferredAuthMethod = 1; //Currently need to use the private enum value 

[application acquireTokenWithParameters:interactiveParams completionBlock:^(MSALResult *result, NSError *error) { 

    // When token acquisition completes 

}]; 

```

このコード スニペットは、QR コード認証に重点を置いて、iOS アプリで MSAL を使用してトークンを構成して取得します。 認証 Web ビューのビュー コントローラーを使用して `MSALWebviewParameters` を初期化し、必要なスコープと Web パラメーターを使用して `MSALInteractiveTokenParameters` を作成します。 推奨される認証方法は、QR コード認証に設定されます。

最後に、構成されたパラメーターと完了ブロックを使用して、`acquireTokenWithParameters` インスタンスの`MultipleAccountPublicClientApplication`を呼び出して結果を処理します。 この設定により、認証フローで安全で便利なユーザー認証に QR コード認証方法が使用されるようになります。

管理者が QR コード認証方法を構成しているかどうかを調べるには、MSAL で `getDeviceInformationWithParameters` API を呼び出すようにお勧めします。 存在する場合、アプリは UI を更新して、QR コード認証方法がサインイン オプションとして使用できることを示すことができます。

### カメラの同意プロンプトを表示しない

既定では、QR コード認証は、カメラを使用して QR コードをスキャンする必要があるたびに、ユーザーにカメラのアクセス許可を求めます。

[Image: iOS でカメラへのアクセスを許可する方法のスクリーンショット。]

管理者は、この動作を抑制し、カメラのアクセス許可の要求をスキップできます。 要求を構成するには、次の SSO 拡張機能の構成を設定します。

- **キー**: suppress\_camera\_consent
- **型**: 整数
- **値**: 1 または 0。 既定では、この値は 0 に設定されます。

場所は、preferred\_auth\_methodを構成できる場所と同じです。 SSO 拡張機能の構成の詳細については、「 [Apple デバイス用の Microsoft Enterprise SSO プラグインのその他の構成オプション](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin#more-configuration-options)」を参照してください。

Note

オペレーティング システムの要件により、カメラの同意プロンプトに 1 回表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/jwt-claims-customization"} -->
## アプリの JSON Web Token (JWT) クレームをカスタマイズする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/jwt-claims-customization
- Service: identity-platform
- Article date: 2025-05-30
- Summary: Microsoft ID プラットフォームによってエンタープライズ アプリケーション用に JSON Web Token (JWT) トークンで発行されるクレームをカスタマイズする方法について説明します。

Microsoft ID プラットフォームは、Microsoft Entra アプリケーション ギャラリーとカスタム アプリケーションのほとんどの事前統合アプリケーションで [シングル サインオン (SSO)](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on) をサポートしています。 ユーザーが OIDC プロトコルを使用して Microsoft ID プラットフォーム経由でアプリケーションに対する認証を行うと、アプリケーションにトークンが送信されます。 アプリケーションはトークンを検証し、ユーザー名とパスワードの入力を求める代わりに、そのトークンを使ってユーザーをサインインさせます。

OIDC および OAuth アプリケーションで使用されるこれらの JSON Web トークン (JWT) には、 *クレーム*と呼ばれるユーザーに関する情報が含まれています。 要求とは、そのユーザーに発行するトークンの中にあるユーザーに関する ID プロバイダーが提示した情報を指します。 [OIDC 応答](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)では、要求データは通常、JWT の形式で ID プロバイダーによって発行された ID トークンに含まれます。

### クレームを表示または編集する

オプションの JWT クレームは、元のアプリケーションの登録で構成できますが、エンタープライズ アプリケーション内でも構成できます。 アプリケーションの JWT で発行されたクレームを表示または編集するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[すべてのアプリケーション]** を参照します。
3. アプリケーションを選択し、左側のメニューで **[シングル サインオン**] を選択し、[**属性と要求**] セクションで **[編集]** を選択します。

アプリケーションでは、さまざまな理由でクレームのカスタマイズが必要になる場合があります。 たとえば、アプリケーションで、別の一連のクレーム URI やクレーム値が必要な場合です。 **[属性と要求**] セクションを使用すると、アプリケーションの要求を追加または削除できます。 ユース ケースに基づいて、アプリケーションに固有のカスタム クレームを作成することもできます。

次の手順は、定数値を割り当てる方法を示しています。

1. 変更するクレームを選択します。
2. 組織に従って **Source 属性** に引用符なしで定数値を入力し、[保存] を選択 **します**。

属性の概要には、定数値が表示されます。

### 特別なクレームの変換

特別なクレームの変換関数を使用できます。

| Function | 説明 |
| --- | --- |
| **ExtractMailPrefix()** | メール アドレスまたはユーザー プリンシパル名からドメイン サフィックスを除去します。 この関数は、ユーザー名の最初の部分のみを抽出します。 たとえば、`joe_smith` の代わりに `joe_smith@contoso.com` となります。 |
| **ToLower()** | 選択した属性の文字を小文字に変換します。 |
| **ToUpper()** | 選択した属性の文字を大文字に変換します。 |

### アプリケーション固有のクレームを追加する

アプリケーション固有の要求を追加するには:

1. **[ユーザー属性] & [要求**] で、[**新しい要求の追加]** を選択して、[**ユーザー要求の管理**] ページを開きます。
2. 要求の **名前** を入力します。 値は、URI パターンに厳密に従う必要はありません。 URI パターンが必要な場合は、[ **名前空間]** フィールドに配置できます。
3. 要求の値を取得する **ソース** を選択します。 ソース属性のドロップダウンからユーザー属性を選択するか、または要求として生成する前にユーザー属性に変換を適用することができます。

#### 要求の変換

ユーザー属性に変換を適用するには、次の手順を行います。

1. [ **要求の管理**] で、要求ソースとして *[変換* ] を選択し、[ **変換の管理** ] ページを開きます。
2. 変換ドロップダウンから関数を選択します。 選択した関数に応じて、変換で評価するパラメーターと定数値を指定します。
3. **ソースを複数値として扱う** は、変換がすべての値に適用されるか、最初の値のみに適用されるかを示します。 既定では、複数値クレームの最初の要素に変換が適用されます。 このチェックボックスをオンにすると、すべてに適用されます。 このチェックボックスは、複数値の属性に対してのみ有効になります。 たとえば、「 `user.proxyaddresses` 」のように入力します。
4. 複数の変換を適用するには、[ **変換の追加]** を選択します。 要求には、最大 2 つの変換を適用できます。 たとえば、最初に `user.mail` のメール プレフィックスを抽出できます。 次に、文字列を大文字にします。

次の関数を使用して、要求を変換できます。

| Function | 説明 |
| --- | --- |
| **ExtractMailPrefix()** | メール アドレスまたはユーザー プリンシパル名からドメイン サフィックスを除去します。 この関数は、ユーザー名の最初の部分のみを抽出します。 たとえば、`joe_smith` の代わりに `joe_smith@contoso.com` となります。 |
| **Join()** | 2 つの属性を結合することで、新しい値を作成します。 必要に応じて、2 つの属性の間に区切り記号を使用できます。 NameID の要求の変換で、変換入力にドメイン部分がある場合、Join() 関数は特定の動作をします。 入力からドメイン部分を削除した後、区切り記号および選択されたパラメーターを結合します。 たとえば、変換の入力が `joe_smith@contoso.com`、区切り記号が `@`、パラメーターが `fabrikam.com` の場合、この入力の組み合わせでは `joe_smith@fabrikam.com` という結果になります。 |
| **ToLowercase()** | 選択した属性の文字を小文字に変換します。 |
| **ToUppercase()** | 選択した属性の文字を大文字に変換します。 |
| **Contains()** | 入力が指定した値と一致する場合、属性または定数を出力します。 一致しない場合は、別の出力を指定できます。 たとえば、値がユーザーのメール アドレスで、そこに `@contoso.com` というドメインが含まれる場合、そのクレームは出力し、それ以外の場合はユーザーのプリンシパル名を出力したいとします。 この関数を実行するには、次の値を構成します。*パラメーター 1 (入力):* user.email*値*: "@contoso.com"Parameter 2 (出力): user.emailParameter 3 (一致しない場合の出力): user.userprincipalname |
| **EndWith()** | 入力が指定した値で終わっている場合、属性または定数を出力します。 一致しない場合は、別の出力を指定できます。たとえば、ユーザーの従業員 ID が `000` で終わっている場合は従業員 ID を値とするクレームを出力し、それ以外の場合は拡張属性を出力するとします。 この関数を実行するには、次の値を構成します。*パラメーター 1 (入力):*user.employeeid*値*: "000"Parameter 2 (出力): user.employeeidParameter 3 (一致しない場合の出力): user.extensionattribute1 |
| **StartWith()** | 入力が指定した値で始まっている場合、属性または定数を出力します。 一致しない場合は、別の出力を指定できます。たとえば、国または地域が `US` で始まっている場合はユーザーの従業員 ID を値とするクレームを出力し、それ以外の場合は拡張属性を出力するとします。 この関数を実行するには、次の値を構成します。*パラメーター 1 (入力)*: user.country*値*: "US"Parameter 2 (出力): user.employeeidParameter 3 (一致しない場合の出力): user.extensionattribute1 |
| **Extract() - 照合後** | 指定した値との一致より後の部分文字列を返します。たとえば、入力の値が `Finance_BSimon` で、一致する値が `Finance_` の場合、クレームの出力は `BSimon` です。 |
| **Extract() - マッチングの前** | 指定した値との一致より前の部分文字列を返します。たとえば、入力の値が `BSimon_US` で、一致する値が `_US` の場合、クレームの出力は `BSimon` です。 |
| **Extract() - 一致の間** | 指定した値との一致より前の部分文字列を返します。たとえば、入力の値が `Finance_BSimon_US`、1 番目の一致する値が `Finance_`、2 番目の一致する値が `_US` の場合、クレームの出力は `BSimon` です。 |
| **ExtractAlpha() - プレフィックス** | 文字列のプレフィックスのアルファベット部分を返します。たとえば、入力の値が `BSimon_123` の場合、`BSimon` が返されます。 |
| **ExtractAlpha() - サフィックス** | 文字列のサフィックスのアルファベット部分を返します。たとえば、入力の値が `123_Simon` の場合、`Simon` が返されます。 |
| **ExtractNumeric() - プレフィックス** | 文字列のプレフィックスの数字部分を返します。たとえば、入力の値が `123_BSimon` の場合、`123` が返されます。 |
| **ExtractNumeric() - サフィックス** | 文字列のサフィックスの数字部分を返します。たとえば、入力の値が `BSimon_123` の場合、`123` が返されます。 |
| **IfEmpty()** | 入力が null または空の場合、属性または定数を出力します。たとえば、特定のユーザーの従業員 ID が空の場合に、拡張属性に格納されている属性を出力したいとします。 この関数を実行するには、次の値を構成します。Parameter 1 (入力): user.employeeidParameter 2 (出力): user.extensionattribute1Parameter 3 (一致しない場合の出力): user.employeeid |
| **IfNotEmpty()** | 入力が null または空ではない場合、属性または定数を出力します。たとえば、特定のユーザーの従業員 ID が空でない場合に、拡張属性に格納されている属性を出力したいとします。 この関数を実行するには、次の値を構成します。Parameter 1 (入力): user.employeeidParameter 2 (出力): user.extensionattribute1 |
| **Substring() - 固定長** | 指定した位置にある文字から始まる文字列要求の種類の一部が抽出され、指定した文字数が返されます。SourceClaim - 実行する必要がある変換のクレーム ソース。StartIndex - このインスタンス内の substring の 0 から始まる開始文字位置。Length - substring の文字の長さ。例えば次が挙げられます。sourceClaim - これを今すぐ抽出してくださいStartIndex - 6長さ - 11出力: ExtractThis |
| **Substring() - EndOfString** | 指定した位置にある文字から始まる文字列要求の種類の一部が抽出され、指定した開始インデックスから要求の残りが返されます。 SourceClaim - 変換のクレーム ソース。StartIndex - このインスタンス内の substring の 0 から始まる開始文字位置。例えば次が挙げられます。sourceClaim - これを今すぐ抽出してくださいStartIndex - 6出力: ExtractThisNow |
| **RegexReplace()** | RegexReplace() 変換は、入力パラメーターとして次を受け入れます。- パラメーター1：正規表現入力としてのユーザー属性- ソースを複数値として信頼するオプション‐正規表現パターン‐置換パターン。 置換パターンには、正規表現出力グループを指す参照、および追加の入力パラメーターと共に、静的テキスト形式を含めることができます。 |

他の変換が必要な場合は、[SaaS アプリケーション](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789) カテゴリ*の Microsoft Entra ID のフィードバック フォーラムで*アイデアを送信してください。

### 正規表現ベースのクレーム変換

正規表現を使用して要求を変換できます。 正規表現ベースの要求変換を使用する場合、最大 20 個の正規表現置換を行うことができます。

次の画像は、変換の第 1 レベルの例を示したものです。

[Image: 変換の最初のレベルのスクリーンショット。]

次の表では、変換の第 1 レベルに関する情報を示します。 表に示されているアクションは、前の画像のラベルに対応しています。 [ **編集] を** 選択して要求変換ブレードを開きます。

| アクション | フィールド | 説明 |
| --- | --- | --- |
| 1 | `Transformation` | **[変換**] オプションから **RegexReplace()** オプションを選択して、要求変換に正規表現ベースの要求変換メソッドを使用します。 |
| 2 | `Parameter 1` | 正規表現変換の入力。 たとえば、`admin@fabrikam.com` のようなユーザー メール アドレスを含む user.mail。 |
| 3 | `Treat source as multivalued` | 一部の入力ユーザー属性は、複数値のユーザー属性になれます。 選択したユーザー属性が複数の値をサポートしていて、変換に複数の値を使用する場合は、[ **ソースを複数値として扱う**] を選択する必要があります。 オンにした場合は、すべての値が正規表現の照合に使われ、オフにした場合は、最初の値のみが使われます。 |
| 4 | `Regex pattern` | "パラメーター 1" として選ばれたユーザー属性の値に対して評価される正規表現。 たとえば、ユーザーのメール アドレスからユーザーの別名を抽出する正規表現は、`(?'domain'^.*?)(?i)(\@fabrikam\.com)$` と表します。 |
| 5 | `Add additional parameter` | 複数のユーザー属性を変換に使用できます。 その場合、属性の値は正規表現の変換出力とマージされます。 最大 5 つの追加パラメーターがサポートされています。 |
| 6 | `Replacement pattern` | 置換パターンは、正規表現の結果に対するプレースホルダーを含むテキスト テンプレートです。 {group-name} のように、すべてのグループ名を中かっこで囲む必要があります。 たとえば、管理者は他のドメイン名 (例: `xyz.com`) と共にユーザーの別名を使い、国名をそれとマージしたいとします。 このケースでは、置換パターンは `{country}.{domain}@xyz.com` になります。`{country}` は入力パラメーターの値であり、`{domain}` は正規表現の評価からのグループ出力です。 このようなケースでは、予想される結果は `US.swmal@xyz.com` になります。 |

次の画像は、変換の 2 番目のレベルの例を示したものです。

[Image: 要求変換の第 2 レベルのスクリーンショット。]

次の表では、変換の 2 番目のレベルに関する情報を示します。 表に示されているアクションは、前の画像のラベルに対応しています。

| アクション | フィールド | 説明 |
| --- | --- | --- |
| 1 | `Transformation` | 正規表現ベースのクレーム変換は、第 1 の変換に限定されず、第 2 レベルの変換としても使用できます。 その他のいかなる変換方法も、最初の変換として使用できます。 |
| 2 | `Parameter 1` | **RegexReplace()** が第 2 レベル変換として選択されている場合、第 1 レベル変換の出力が第 2 レベル変換の入力として使用されます。 変換を適用するには、第 2 レベルの正規表現式が、第 1 の変換の出力と一致する必要があります。 |
| 3 | `Regex pattern` | **正規表現パターン** は、第 2 レベル変換の正規表現です。 |
| 4 | `Parameter input` | 第 2 レベルの変換に対するユーザー属性の入力。 |
| 5 | `Parameter input` | パラメーターが不要になった場合、管理者は選んだ入力パラメーターを削除できます。 |
| 6 | `Replacement pattern` | 置換パターンは、正規表現の結果グループ名、入力パラメーター グループ名、静的テキスト値のプレースホルダーを含むテキスト テンプレートです。 `{group-name}` のように、すべてのグループ名を中かっこで囲む必要があります。 たとえば、管理者は他のドメイン名 (例: `xyz.com`) と共にユーザーの別名を使い、国名をそれとマージしたいとします。 このケースでは、置換パターンは `{country}.{domain}@xyz.com` になります。`{country}` は入力パラメーターの値であり、`{domain}` は正規表現の評価からのグループ出力です。 このようなケースでは、予想される結果は `US.swmal@xyz.com` になります。 |
| 7 | `Test transformation` | RegexReplace() 変換は、 *パラメーター 1* に対して選択されたユーザー属性の値が **、Regex パターン** テキスト ボックスに指定された正規表現と一致する場合にのみ評価されます。 一致しない場合は、既定のクレーム値がトークンに追加されます。 入力パラメーター値に対して正規表現を検証するには、変換ブレード内でテスト エクスペリエンスを使用できます。 このテスト エクスペリエンスはダミー値でのみ動作します。 追加の入力パラメーターを使うと、実際の値ではなく、パラメーターの名前がテスト結果に追加されます。 テスト セクションにアクセスするには、[ **テスト変換**] を選択します。 |

次の図は、変換のテストの例を示したものです。

[Image: 変換のテストのスクリーンショット。]

次の表では、変換のテストに関する情報を示します。 表に示されているアクションは、前の画像のラベルに対応しています。

| アクション | フィールド | 説明 |
| --- | --- | --- |
| 1 | `Test transformation` | [閉じる] または [(X)] ボタンを選択してテスト セクションを非表示にし、ブレードで [ **テスト変換** ] ボタンをもう一度レンダリングします。 |
| 2 | `Test regex input` | 正規表現テストの評価に使われる入力を受け入れます。 正規表現ベースのクレーム変換が第 2 レベルの変換として構成されている場合、第 1 の変換の予想される出力を値として指定します。 |
| 3 | `Run test` | テスト正規表現入力が指定され、 **Regex パターン**、 **置換パターン** 、 **および入力パラメーター** が構成されたら、[ **テストの実行**] を選択して式を評価できます。 |
| 4 | `Test transformation result` | 評価に成功すると、テスト変換の出力が **テスト変換の結果** ラベルに対してレンダリングされます。 |
| 5 | `Remove transformation` | 2 番目のレベルの変換は、[変換の **削除**] を選択して削除できます。 |
| 6 | `Specify output if no match` | *正規表現*と一致しない**パラメーター 1** に対して正規表現入力値が構成されている場合、変換はスキップされます。 このような場合は、代替ユーザー属性を構成できます。これは、 **一致しない場合に出力を指定**するチェック をオンにして、要求のトークンに追加されます。 |
| 7 | `Parameter 3` | 条件に一致しない場合に代替ユーザー属性を返す必要があり、**一致しない場合の出力を指定**が選択されている場合は、ドロップダウンメニューを使用して代替ユーザー属性を選択できます。 このドロップダウンは **、パラメーター 3 (一致しない場合の出力)** に対して使用できます。 |
| 8 | `Summary` | ブレードの下部には、簡単なテキストで変換の意味を説明するフォーマットの概要が表示されます。 |
| 9 | `Add` | 変換の構成設定が検証されたら、[ **追加**] を選択して要求ポリシーに保存できます。 [**要求の管理**] ブレードで **[保存]** を選択して変更を保存します。 |

RegexReplace() 変換は、グループ要求変換でも使用できます。

#### 変換の検証

**[テストの追加**] または **[実行**] を選択した後に次の条件が発生した場合に、メッセージに詳細が表示されます。

- ユーザー属性が重複する入力パラメーターが使用された。
- 使われていない入力パラメーターが見つかりました。 定義された入力パラメーターは、置換パターン テキストでそれぞれ使用される必要があります。
- 指定されたテスト正規表現入力が、指定された正規表現と一致しません。
- 置換パターンのグループのソースが見つからない。

### 条件に基づいてクレームを出力する

ユーザーの種類とユーザーが属するグループに基づいて、要求のソースを指定することができます。

ユーザーの種類は次のとおりです。

- **Any** - すべてのユーザーがアプリケーションにアクセスできます。
- **メンバー**: テナントのネイティブ メンバー
- **すべてのゲスト**: ユーザーは、Microsoft Entra ID の有無にかかわらず外部組織から移動しました。
- **Microsoft Entra ゲスト**: ゲスト ユーザーは、Microsoft Entra ID を使用して別の組織に属しています。
- **外部ゲスト**: ゲスト ユーザーは、Microsoft Entra ID を持たない外部組織に属しています。

ユーザーの種類が役立つのは、クレームのソースが、ゲストとアプリケーションにアクセスする従業員とで異なる場合です。 ユーザーが従業員である場合は NameID を user.email から取得するように指定できます。 ユーザーがゲストの場合は、NameID は user.extensionattribute1 から取得されます。

要求条件を追加するには、次の手順を行います。

1. [ **要求の管理]** で、[要求の条件] を展開します。
2. ユーザーの種類を選択します。
3. ユーザーが属するグループを選択します。 特定のアプリケーションに対するすべての要求で、最大 50 個の一意のグループを選択できます。
4. 要求の値を取得する **ソース** を選択します。 ソース属性のドロップダウンからユーザー属性を選択するか、または要求として生成する前にユーザー属性に変換を適用することができます。

条件を追加する順序は重要です。 Microsoft Entra では、まず、すべての条件をソース `Attribute` で評価し、次に、すべての条件をソース `Transformation` で評価して、クレームに出力する値を決定します。 Microsoft Entra ID では、上から下まで同じソースで条件を評価します。 クレームによって、クレームの式と一致する最後の値が出力されます。 `IsNotEmpty` や `Contains` などの変換は、制限のように機能します。

たとえば、Britta Simon は Contoso テナントのゲスト ユーザーです。 Britta は、Microsoft Entra ID も使用する別の組織に属しています。 Fabrikam アプリケーションが次のように構成されている場合、Britta が Fabrikam にサインインしようとすると、Microsoft ID プラットフォームで条件が評価されます。

まず、Microsoft ID プラットフォームは、Britta のユーザーの種類が **[すべてのゲスト]** であるかどうかを確認します。 種類は **[すべてのゲスト]** であるため、Microsoft ID プラットフォームは要求のソースを `user.extensionattribute1`に割り当てます。 次に、Microsoft ID プラットフォームは、Britta のユーザーの種類が **Microsoft Entra ゲスト**であるかどうかを確認します。 種類は **[すべてのゲスト]** であるため、Microsoft ID プラットフォームは要求のソースを `user.mail`に割り当てます。 最終的に、クレームは Britta の `user.mail` の値を使って出力されます。

もう 1 つの例として、Britta Simon が次の構成を使用してサインインしようとする場合を考えてみます。 Microsoft Entra では、まず、すべての条件をソース `Attribute` で評価します。 Britta のユーザータイプが `user.mail` である場合、要求の根拠は  です。 次に、Microsoft Entra ID によって変換が評価されます。 Britta はゲストであるため、`user.extensionattribute1` がクレームの新しいソースです。 Britta は **Microsoft Entra ゲスト**にあるため、 `user.othermail` がこの要求の新しいソースです。 最終的に、クレームは Britta の `user.othermail` の値を使って出力されます。

最後の例として、Britta の `user.othermail` が構成されていない場合、または空の場合にどうなるかを考えます。 どちらの場合も、条件エントリは無視され、クレームは `user.extensionattribute1` にフォールバックします。

### セキュリティに関する考慮事項

トークンを受信するアプリケーションは、改ざんできないクレーム値に依存しています。 クレームのカスタマイズによってトークンの内容を変更すると、この前提が通用しなくなることがあります。 アプリケーションでは、悪意のあるアクターによって作成されたカスタマイズから自身を保護するために、トークンが変更されていることを明示的に承認する必要があります。 次のいずれかの方法で不適切なカスタマイズから保護します。

- カスタム署名キーを構成する
- は、マップされた要求を受け入れるようにアプリケーション マニフェストを更新します。

これを指定しないと、Microsoft Entra ID は [AADSTS50146エラー コード](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes#aadsts-error-codes)を返します。

### カスタム署名キーを構成する

マルチテナント アプリでは、カスタム署名キーを使用する必要があります。 アプリ マニフェストには `acceptMappedClaims` を設定しないでください。 Azure portal でアプリをセットアップすると、アプリ登録オブジェクトとサービス プリンシパルがテナントに作成されます。 そのアプリでは Azure グローバル サインイン キーを使用します。このキーではトークンのクレームをカスタマイズできません。 トークンにカスタム クレームを使用するには、証明書からカスタム サインイン キーを作成してサービス プリンシパルに追加します。 テスト目的で、自己署名証明書を使用してもかまいません。 カスタム署名キーを構成したら、アプリケーション コードでトークン署名キーを検証する必要があります。

次の情報をサービス プリンシパルに追加します。

- 秘密キー ( [キー資格情報](https://learn.microsoft.com/ja-jp/graph/api/resources/keycredential?view=graph-rest-1.0&preserve-view=true)として)
- パスワード ([パスワード資格情報](https://learn.microsoft.com/ja-jp/graph/api/resources/passwordcredential?view=graph-rest-1.0&preserve-view=true))
- 公開キー ( [キー資格情報](https://learn.microsoft.com/ja-jp/graph/api/resources/keycredential?view=graph-rest-1.0&preserve-view=true)として)

証明書からエクスポートした PFX ファイルから、base-64 でエンコードした秘密キーと公開キーを抽出します。 “Sign” に使用する `keyId` の `keyCredential` が `keyId` の `passwordCredential` に一致することを確認します。 証明書の拇印のハッシュを取得することで、`customkeyIdentifier` を生成できます。

### リクエスト

注

最初に、Microsoft Entra 管理センターのアプリ登録ブレードから新しく作成されたアプリの [サービス プリンシパル ロック構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-app-instance-property-locks) を無効にしてから、サービス プリンシパルに対して PATCH を実行すると、400 無効な要求が発生します。

次の例は、カスタム署名キーをサービス プリンシプルに追加する HTTP PATCH 要求の形式を示しています。 `keyCredentials` プロパティの “key” の値は、読みやすいように短縮してあります。 この値は base-64 でエンコードしてあります。 秘密キーでは、プロパティの "usage" (用途) は `Sign` です。 公開キーでは、プロパティの "usage" (用途) は `Verify` です。

```JSON
PATCH https://graph.microsoft.com/v1.0/servicePrincipals/aaaaaaaa-bbbb-cccc-1111-222222222222

Content-type: servicePrincipals/json
Authorization: Bearer {token}

{
    "keyCredentials":[
        {
            "customKeyIdentifier": "aB1cD2eF3gH4iJ5kL6-mN7oP8qR=", 
            "endDateTime": "2021-04-22T22:10:13Z",
            "keyId": "aaaaaaaa-0b0b-1c1c-2d2d-333333333333",
            "startDateTime": "2020-04-22T21:50:13Z",
            "type": "X509CertAndPassword",
            "usage": "Sign",
            "key":"cD2eF3gH4iJ5kL6mN7-oP8qR9sT==",
            "displayName": "CN=contoso"
        },
        {
            "customKeyIdentifier": "aB1cD2eF3gH4iJ5kL6-mN7oP8qR=",
            "endDateTime": "2021-04-22T22:10:13Z",
            "keyId": "bbbbbbbb-1c1c-2d2d-3e3e-444444444444",
            "startDateTime": "2020-04-22T21:50:13Z",
            "type": "AsymmetricX509Cert",
            "usage": "Verify",
            "key": "cD2eF3gH4iJ5kL6mN7-oP8qR9sT==",
            "displayName": "CN=contoso"
        }

    ],
    "passwordCredentials": [
        {
            "customKeyIdentifier": "aB1cD2eF3gH4iJ5kL6-mN7oP8qR=",
            "keyId": "cccccccc-2d2d-3e3e-4f4f-555555555555",
            "endDateTime": "2022-01-27T19:40:33Z",
            "startDateTime": "2020-04-20T19:40:33Z",
            "secretText": "mypassword"
        }
    ]
}
```

### PowerShell でカスタム署名キーを設定する

PowerShell を使用して [MSAL パブリック クライアント アプリケーションをインスタンス化](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/getting-started/initializing-client-applications#initializing-a-public-client-application-from-code) し、 [承認コード付与](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) フローを使用して Microsoft Graph の委任されたアクセス許可アクセス トークンを取得します。 このアクセス トークンを使用して Microsoft Graph を呼び出し、サービス プリンシパルに対してカスタム署名キーを設定します。 カスタム署名キーを構成したら、アプリケーション コードで トークン署名キーを検証する必要があります。

このスクリプトを実行するには、次のものが必要です。

- アプリケーションのサービス プリンシパルのオブジェクト ID。Azure portal の [エンタープライズ アプリケーション] から、目的のアプリケーションの項目の **[概要]** ブレードで確認できます。
- ユーザーをサインインさせ、Microsoft Graph を呼び出すアクセス トークンを取得するためのアプリの登録。 Azure portal の [App registrations]\(アプリの登録)\ の目的のアプリケーションの項目の [Overview]\(概要\) ブレードで、このアプリのアプリケーション (クライアント) ID を取得します。 アプリの登録には次の構成が必要です。
    - "http://localhost" のリダイレクト URI。モバイル **アプリケーションとデスクトップアプリケーション** プラットフォームの構成に記載されています。
    - **API のアクセス許可**で、Microsoft Graph によって委任されたアクセス許可 **Application.ReadWrite.All** と **User.Read** (これらのアクセス許可に対する管理者の同意を必ず付与してください)。
- ログインして Microsoft Graph アクセス トークンを取得するユーザー。 このユーザーは、次の Microsoft Entra 管理者ロールのいずれかである必要があります (サービス プリンシパルの更新に必要です)。
    - クラウド アプリケーション管理者
    - アプリケーション管理者
- アプリケーションのカスタム署名キーに設定する証明書。 自己署名証明書を作成する方法と、信頼できる証明機関から証明書を取得する方法があります。 スクリプトでは、証明書に含まれる次の要素を使用します。
    - 公開キー (通常は *.cer* ファイル)
    - PKCS#12 形式の秘密キー ( *.pfx* ファイル内)
    - 秘密キーのパスワード (*.pfx* ファイル)

重要

Microsoft Entra ID では他の形式をサポートしていないため、秘密キーは PKCS#12 形式である必要があります。 誤った形式を使用した場合、`keyCredentials` に証明書情報が格納されているサービス プリンシパルに対して、Microsoft Graph で PATCH を実行すると、"Invalid certificate: Key value is invalid certificate" (無効な証明書: キー値が無効な証明書です) というエラーが発生する可能性があります。

```powershell
##########################################################
# Replace the variables below with the appropriate values 

$fqdn="yourDomainHere" # This is used for the 'issued to' and 'issued by' field of the certificate
$pwd="password" # password for exporting the certificate private key
$tenantId   = "aaaabbbb-0000-cccc-1111-dddd2222eeee" # Replace with your Tenant ID
$appObjId = "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb" # Replace with the Object ID of the App Registration

##########################################################

# Create a self-signed cert

$cert = New-SelfSignedCertificate -certstorelocation cert:\currentuser\my -DnsName $fqdn
$pwdSecure = ConvertTo-SecureString -String $pwd -Force -AsPlainText
$path = 'cert:\currentuser\my\' + $cert.Thumbprint
$location="C:\\temp" # path to folder where both the pfx and cer file will be written to
$cerFile = $location + "\\" + $fqdn + ".cer"
$pfxFile = $location + "\\" + $fqdn + ".pfx"
 
# Export the public and private keys
Export-PfxCertificate -cert $path -FilePath $pfxFile -Password $pwdSecure
Export-Certificate -cert $path -FilePath $cerFile

$pfxpath = $pfxFile # path to pfx file
$cerpath = $cerFile # path to cer file
$password = $pwd  # password for the pfx file
 
# Check PowerShell version (minimum 5.1) (.Net) or PowerShell Core (.Net Core) and read the certificate file accordingly
 
if ($PSVersionTable.PSVersion.Major -gt 5)
    { 
        $core = $true
    }
else
    { 
        $core = $false
    }
 
    #  this is for PowerShell Core
    $Secure_String_Pwd = ConvertTo-SecureString $password -AsPlainText -Force
 
    # reading certificate files and creating Certificate Object
    if ($core)
    {
        $pfx_cert = get-content $pfxpath -AsByteStream -Raw
        $cer_cert = get-content $cerpath -AsByteStream -Raw
        $cert = Get-PfxCertificate -FilePath $pfxpath -Password $Secure_String_Pwd
    }
    else
    {
        $pfx_cert = get-content $pfxpath -Encoding Byte
        $cer_cert = get-content $cerpath -Encoding Byte
        # calling Get-PfxCertificate in PowerShell 5.1 prompts for password - using alternative method
        $cert = [System.Security.Cryptography.X509Certificates.X509Certificate2]::new($pfxpath, $password)
    }

    # base 64 encode the private key and public key
    $base64pfx = [System.Convert]::ToBase64String($pfx_cert)
    $base64cer = [System.Convert]::ToBase64String($cer_cert)
 
    # getting id for the keyCredential object
    [string]$guid1 = New-Guid
    [string]$guid2 = New-Guid
 
    # get the custom key identifier from the certificate thumbprint:
    $hasher = [System.Security.Cryptography.HashAlgorithm]::Create('sha256')
    $hash = $hasher.ComputeHash([System.Text.Encoding]::UTF8.GetBytes($cert.Thumbprint))
    $customKeyIdentifier = [System.Convert]::ToBase64String($hash)
 
    # get end date and start date for our keycredentials
    $endDateTime = ($cert.NotAfter).ToUniversalTime().ToString( "yyyy-MM-ddTHH:mm:ssZ" )
    $startDateTime = ($cert.NotBefore).ToUniversalTime().ToString( "yyyy-MM-ddTHH:mm:ssZ" )
 
    # building our json payload
    $object = [ordered]@{    
    keyCredentials = @(       
         [ordered]@{            
            customKeyIdentifier = $customKeyIdentifier
            endDateTime = $endDateTime
            keyId = $guid1
            startDateTime = $startDateTime 
            type = "AsymmetricX509Cert"
            usage = "Sign"
            key = $base64pfx
            displayName = "CN=$fqdn" 
        },
        [ordered]@{            
            customKeyIdentifier = $customKeyIdentifier
            endDateTime = $endDateTime
            keyId = $guid2
            startDateTime = $startDateTime 
            type = "AsymmetricX509Cert"
            usage = "Verify"
            key = $base64cer
            displayName = "CN=$fqdn"   
        }
        )  
    passwordCredentials = @(
        [ordered]@{
            customKeyIdentifier = $customKeyIdentifier
            displayName = "CN=$fqdn"
            keyId = $guid1           
            endDateTime = $endDateTime
            startDateTime = $startDateTime
            secretText = $password
            hint = $null
        }
    )
    }
 
Connect-MgGraph -tenantId $tenantId -Scopes Application.ReadWrite.All
$graphuri = "https://graph.microsoft.com/v1.0/applications/$appObjId"
Invoke-MgGraphRequest -Method PATCH -Uri $graphuri -Body $object

    $json = $object | ConvertTo-Json -Depth 99
    Write-Host "JSON Payload:"
    Write-Output $json
```

### トークン署名キーを承認する

要求マッピングが有効になっているアプリでは、`appid={client_id}`にを追加してトークン署名キーを検証する必要があります。 次の例は、使用する必要がある OpenID Connect メタデータ ドキュメントの形式を示しています。

```http
https://login.microsoftonline.com/{tenant}/v2.0/.well-known/openid-configuration?appid={client-id}
```

### アプリケーション マニフェストを更新する

シングル テナント アプリの場合は、`acceptMappedClaims` プロパティを`true`でするように設定できます。 [`apiApplication` リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/apiapplication?view=graph-rest-1.0&preserve-view=true#properties)に関するドキュメントに記載されているとおり。 このプロパティを設定すると、アプリケーションでカスタム署名キーを指定せずにクレーム マッピングを使用できるようになります。

警告

マルチテナント アプリでは、acceptMappedClaims プロパティを true に設定しないでください。悪意のあるアクターが、アプリのクレームマッピング ポリシーを作成できるようになる可能性があります。

要求されたトークンの対象者は、Microsoft Entra テナントの検証済みドメイン名を使用する必要があります。つまり、(アプリケーション マニフェストで `Application ID URI` で表される) `identifierUris` を、たとえば `https://contoso.com/my-api` に設定するか、(単に既定のテナント名を使用して) `https://contoso.onmicrosoft.com/my-api` に設定する必要があります。

検証済みドメインを使用していない場合、Microsoft Entra ID から `AADSTS501461` エラー コードが返され、"\_AcceptMappedClaims is only supported for a token audience matching the application GUID or an audience within the tenant's verified domains. Either change the resource identifier or use an application-specific signing key." (\_AcceptMappedClaims は、アプリケーション GUID と一致するトークン対象者、またはテナントの検証済みドメイン内の対象者でのみサポートされます。リソース識別子を変更するか、アプリケーション固有の署名キーを使用してください。) というメッセージが表示されます。

### クレームの詳細オプション

同じクレームを SAML トークンとして公開するには、OIDC アプリケーションに対してクレームの詳細オプションを構成します。 また、SAML2.0 と OIDC の両方の応答トークンに同じクレームを使用するアプリケーションについても同様です。

[要求の管理] ブレードの [ **詳細要求オプション** ] の下にあるチェック ボックスをオンにして、詳細 **要求** オプションを構成します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/mark-app-as-publisher-verified"} -->
## アプリを発行者確認済みとしてマークする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/mark-app-as-publisher-verified
- Service: identity-platform
- Article date: 2024-05-31
- Summary: アプリを発行者確認済みとしてマークする方法を説明します。 アプリケーションが発行者確認済みとしてマークされている場合は、発行者 （アプリケーション開発者）が検証プロセスを完了したクラウド パートナー プログラム （CPP） アカウントを使用して組織の真正性を検証し、この CPP アカウントをそのアプリケーション登録に関連付けたことを意味します。

アプリの登録に確認済み発行者が含まれている場合は、アプリの発行者がMicrosoft AI Cloud Partner Program アカウントを使用して自身の ID を[確認](https://learn.microsoft.com/ja-jp/partner-center/verification-responses)し、このアカウントと自身のアプリの登録を関連付けていることを意味します。 この記事では、[発行者の確認](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview)プロセスを完了する方法について説明します。

### クイックスタート

[Microsoft AI Cloud Partner Program](https://learn.microsoft.com/ja-jp/partner-center/intro-to-cloud-partner-program-membership) に既に登録していて、[前提条件](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview#requirements)を満たしている場合は、すぐに始めることができます。

1. [多要素認証](https://aka.ms/PublisherVerificationPreview)を使用して、[アプリ登録ポータル](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-licensing)にサインインします
2. アプリを選択し、**[ブランド化とプロパティ]** を選択します。
3. **[Add Partner ID to verify publisher] (発行者を検証するための Partner ID を追加する)** を選択し、表示される要件を確認します。
4. 自分の Partner One ID を入力し、**[Verify and save] (確認して保存)** を選択します。

具体的な利点、要件、およびよく寄せられる質問の詳細については、[概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview)に関するページを参照してください。

### アプリを発行者確認済みとしてマークする

[前提条件](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview#requirements)が満たされていることを確認した後、以下の手順に従って、アプリを発行者確認済みとしてマークします。

1. パートナー センターの Microsoft AI Cloud Partner Program アカウントで、発行者確認済みとしてマークするアプリを変更することが許可されている組織 (Microsoft Entra) アカウントに、[多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-licensing)を使用してサインインします。

    - Microsoft Entra ユーザーには、次のいずれかの[ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)が必要です: アプリケーション管理者、クラウド アプリケーション管理者、全体管理者。
    - パートナー センターでは、このユーザーには次の[ロール](https://learn.microsoft.com/ja-jp/partner-center/permissions-overview)が必要です。Microsoft AI Cloud Partner Program Admin、または Accounts Admin。
2. **[アプリの登録]** ブレードに移動します。
3. 発行者確認済みとしてマークするアプリを選択し、**[ブランド化とプロパティ]** ブレードを開きます。
4. アプリの[パブリッシャー ドメイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-publisher-domain)が設定されていることを確認します。
5. テナントのパブリッシャー ドメインまたは DNS 検証済みの[カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)のいずれかが、CPP アカウントの検証プロセスで使用されたメール アドレスのドメインと一致することを確認します。
6. ページの下部近くにある **[Add Partner ID to verify publisher] (発行者を検証するための Partner ID を追加する)** を選択します。
7. **Partner ID** を入力してください。

- 確認プロセスが完了している有効な クラウド パートナー プログラム アカウント。

    - 組織のパートナー グローバル アカウント (PGA)。

1. **[Verify and save]** を選択します。
2. 要求が処理されるまで待ちます。これには数分かかることがあります。
3. 確認が成功した場合は、発行者確認ウィンドウが閉じ、**[ブランド化とプロパティ]** ブレードに戻ります。 確認済みの **[発行者の表示名]** の横に、青い確認済みバッジが表示されます。
4. アプリへの同意を求めるメッセージが表示されるユーザーに対しては、プロセスが正常に終了した直後から、バッジが表示されるようになります。ただし、この情報がシステム全体に複製されるまで、少し時間がかかる場合があります。
5. アプリケーションにサインインし、確認済みバッジが同意画面に表示されることを確認して、この機能をテストします。 アプリへの同意を既に許可しているユーザーとしてサインインしている場合は、*prompt=consent* クエリ パラメーターを使用して、同意プロンプトを強制することができます。 このパラメーターはテストのみに使用し、アプリの要求にはハードコーディングしないでください。
6. バッジを表示する追加のアプリについて、必要に応じてこれらの手順を繰り返します。 Microsoft Graph を使用してこの処理を一括ですばやく行うことができ、間もなく PowerShell コマンドレットが利用可能になります。 詳細については、「[Microsoft Graph API の呼び出しを行う](https://learn.microsoft.com/ja-jp/entra/identity-platform/troubleshoot-publisher-verification#making-microsoft-graph-api-calls)」を参照してください。

これで終了です。 プロセス、結果、または一般的な機能に関するフィードバックがある場合は、お知らせください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/migrate-spa-implicit-to-auth-code"} -->
## 暗黙的な許可から承認ワークフロー フローに JavaScript のシングルページ アプリを移行する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/migrate-spa-implicit-to-auth-code
- Service: identity-platform
- Article date: 2025-05-12
- Summary: MSAL.js 2.x を使用して JavaScript SPA を更新し、PKCE と CORS をサポートする承認コード フローを更新する方法。

Microsoft Authentication Library for JavaScript (MSAL.js) v2.0 では、Microsoft ID プラットフォームでのシングルページ アプリケーションに対する、PKCE、CORS を使用した承認コード フローがサポートされます。 暗黙的な許可を使用して MSAL.js 1.x アプリケーションを MSAL.js 2.0 以降 (以降 *2.x*) と認証コード フローに移行するには、以下のセクションの手順に従います。

MSAL.js 2.x は、ブラウザーで暗黙的な許可のフローではなく承認コード フローをサポートすることで、MSAL.js 1.x よりも強化されています。 MSAL.js 2.x では、暗黙的フローはサポート **されません** 。

### 移行手順を実行する

MSAL.js 2.x と承認コード フローにアプリケーションを更新するには、主に 3 つの手順があります。

1. アプリ登録リダイレクト URI を **Web** プラットフォームから**シングルページ アプリケーション** プラットフォームに切り替えます。
2. コードを MSAL.js 1.x から **2.x** に更新します。
3. 登録を共有するすべてのアプリケーションが MSAL.js 2.x と認証コード フローに更新されている場合は、アプリの登録で 暗黙的な許可 を無効にします。

後続のセクションでは、各手順についてさらに詳しく説明します。

### リダイレクト URI を SPA プラットフォームに切り替える

アプリケーションの既存のアプリ登録を使い続ける場合、Microsoft Entra 管理センター を使用し、登録のリダイレクト URI を SPA プラットフォームに更新します。 これを行うと、登録を利用するアプリに対して PKCE および CORS サポートを使用した承認コード フローが有効になります (ただし、アプリケーションのコードを MSAL.js v2.x に更新する必要があります)。

**Web** プラットフォーム リダイレクト URI で現在構成されているアプリの登録については、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**App 登録**に移動し、アプリケーションを選択して、[**認証**] を選択します。
3. [**リダイレクト URI] の** [**Web** プラットフォーム] タイルで、URI を移行する必要があることを示す警告バナーを選択します。

    [Image: Entra 管理センターの Web アプリ タイルの暗黙的なフロー警告バナー。]
4. アプリケーションで MSAL.js 2.x を使用するもののみリダイレクト URI *を*選択し、次に [**設定**] を選択します。

    [Image: Entra 管理センターの SPA ペインでリダイレクト URI ペインを選択します。]

これらのリダイレクト URI が **シングルページ アプリケーション** プラットフォーム タイルに表示され、承認コード フローでの CORS のサポートと、これらの URI に対する PKCE が有効であることが示されます。

[Image: Azure portal でのアプリ登録のシングルページ アプリケーション タイル]

### コードを MSAL.js 2.x に更新する

MSAL 1.x で、次のように UserAgentApplication を初期化し、アプリケーション インスタンスを作成しました。

```javascript
// MSAL 1.x
import * as msal from "msal";

const msalInstance = new msal.UserAgentApplication(config);
```

MSAL 2.x では、代わりに [PublicClientApplication][msal-js-publicclientapplication] を初期化します。

```javascript
// MSAL 2.x
import * as msal from "@azure/msal-browser";

const msalInstance = new msal.PublicClientApplication(config);
```

コードに加える必要があるその他の変更については、GitHub の [移行ガイド](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/v1-migration.md) を参照してください。

### 暗黙的な許可設定の無効化

このアプリ登録とそのクライアント ID を使用するすべての運用アプリケーションを MSAL 2.x と承認コード フローに更新したら、アプリ登録の **[認証** ] メニューの下にある暗黙的な許可設定をオフにする必要があります。

アプリ登録で暗黙的な許可設定のチェックマークをオフにすると、登録とそのクライアント ID を利用するすべてのアプリケーションに対して暗黙的フローが無効になります。

すべてのアプリケーションを MSAL.js 2.x と [PublicClientApplication][msal-js-publicclientapplication] に更新する前に、暗黙的な許可フローを無効に**しないでください**。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/mobile-sso-support-overview"} -->
## 開発するモバイル アプリでシングル サインオンとアプリ保護ポリシーをサポートする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/mobile-sso-support-overview
- Service: identity-platform / workforce
- Article date: 2020-10-14
- Summary: MicrosoftID プラットフォームを使用し、Microsoft Entra ID と統合してシングル サインオンとアプリ保護ポリシーをサポートするモバイル アプリケーションの構築に関する説明と概要。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

シングル サインオン (SSO) は、アプリ ユーザーに簡単で安全なログインを提供する、Microsoft ID プラットフォームと Microsoft Entra ID の重要なオファリングです。 さらに、アプリ保護ポリシー (APP) を使用すると、ユーザーのデータを安全に保つ主要なセキュリティ ポリシーをサポートできます。 これらの機能を併用することにより、ユーザーのログインとアプリのデータの管理がセキュリティで保護されます。

この記事では、SSO と APP が重要である理由について説明し、これらの機能をサポートするモバイル アプリケーションをビルドするための概略的なガイダンスを示します。 これは、電話とタブレットの両方のアプリに適用されます。 組織の Microsoft Entra テナント全体に SSO をデプロイする IT 管理者は、[シングル サインオンのデプロイを計画するためのガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-sso-deployment)を確認してください。

### シングル サインオンとアプリ保護ポリシーについて

[シングル サインオン (SSO)](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-sso-deployment) を使用すると、ユーザーは 1 回サインインするだけで、資格情報を再入力することなく他のアプリケーションにアクセスできます。 これにより、アプリへのアクセスが簡単になり、ユーザーがユーザー名とパスワードの長いリストを記憶する必要がなくなります。 これをアプリに実装すると、アプリへのアクセスと使用が簡単になります。

さらに、アプリでシングル サインオンを有効にすると、[パスワードなしログイン](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-passkeys-fido2)などの先進認証を使用する新しい認証メカニズムがロック解除されます。 ユーザー名とパスワードは、アプリケーションに対する最も一般的な攻撃ベクトルの 1 つであり、SSO を有効にすると、条件付きアクセスまたはパスワードレス ログインを適用して、セキュリティを強化したり、より安全な認証メカニズムに依存したりすることで、このリスクを軽減できます。 最後に、シングル サインオンを有効にすると、[シングル サインアウト](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc#single-sign-out)も有効になります。これは、共有デバイスで使用される作業アプリケーションのような状況で役立ちます。

[アプリ保護ポリシー (APP)](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-protection-policy) を使用すると、組織のデータが安全に格納され続けます。 これにより、企業はアプリ内のデータを管理および保護し、アプリとそのデータにアクセスできるユーザーを制御できるようになります。 アプリ保護ポリシーを実装すると、アプリは条件付きアクセス ポリシーによって保護されているリソースにユーザーを接続し、他の保護されたアプリとの間で安全にデータを転送することができます。 アプリ保護ポリシーによってロック解除されるシナリオには、アプリを開くために PIN を要求する、アプリ間のデータ共有を制御する、会社のアプリ データが個人用ストレージの場所に保存されないようにする、などがあります。

### シングル サインオンの実装

アプリでシングル サインオンを利用できるようにするには、次のことをお勧めします。

#### Microsoft 認証ライブラリ (MSAL) を使用する

アプリケーションでシングル サインオンを実装する場合は、[Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) を使用することをお勧めします。 MSAL を使用すると、最小限のコードおよび API 呼び出しでアプリに認証を追加し、[Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/)のすべての機能を取得して、セキュリティで保護された認証ソリューションのメンテナンスを Microsoft で処理できるようにすることができます。 既定では、MSAL によってアプリケーションの SSO サポートが追加されます。 さらに、アプリ保護ポリシーも実装する場合は、MSAL の使用が必須となります。

注

埋め込み Web ビューを使用するように MSAL を構成することもできます。 この場合は、シングル サインオンが阻止されます。 SSO を確実に機能させるには、既定の動作 (つまり、システム Web ブラウザー) を使用します。

iOS アプリケーションの場合、MSAL を使用してサインインを設定する方法と、[さまざまな SSO シナリオで MSAL を構成するためのガイダンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-v2-ios)を示す[クイック スタート](https://learn.microsoft.com/ja-jp/entra/msal/objc/single-sign-on-macos-ios)があります。

Android アプリケーションの場合は、MSAL を使用してサインインを設定する方法を示す[クイックスタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-v2-android)と、[Android で MSAL を使用してクロスアプリ SSO を有効にする方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-android-single-sign-on)についてのガイダンスが用意されています。

#### システム Web ブラウザーを使用する

対話型の認証には Web ブラウザーが必要です。 MSAL 以外の先進認証ライブラリ (つまり、他の OpenID Connect または SAML ライブラリ) を使用するモバイル アプリの場合、あるいは独自の認証コードを実装する場合は、認証画面としてシステム ブラウザーを使用して SSO を有効にする必要があります。

Google では、Android アプリケーションでこれを行うためのガイダンス: 「[Chrome Custom Tabs - Google Chrome](https://developer.chrome.com/multidevice/android/customtabs)」(Chrome のカスタム タブ - Google Chrome) が用意されています。

Apple では、iOS アプリケーションでこれを行うためのガイダンス: 「[Authenticating a User Through a Web Service | Apple Developer Documentation](https://developer.apple.com/documentation/authenticationservices/authenticating_a_user_through_a_web_service)」(Web サービスを使用したユーザーの認証 | Apple 開発者ドキュメント) が用意されています。

ヒント

[Apple デバイス用の SSO プラグイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)を使用すると、Intune により、管理対象デバイスで埋め込み Web ビューを使用する iOS アプリに対して SSO が許可されます。 すべてのユーザーに対して SSO を有効にするアプリを開発するための最適なオプションとして、MSAL とシステム ブラウザーを使用することをお勧めします。ただし、この場合、SSO を使用できない一部のシナリオで SSO が許可されます。

### アプリ保護ポリシーを有効にする

アプリ保護ポリシーを有効にするには、[Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview) を使用します。 MSAL は Microsoft ID プラットフォームの認証および承認ライブラリであり、Intune SDK はこれと連動するように開発されています。

また、認証にブローカー アプリを使用する必要があります。 ブローカーにより、アプリのコンプライアンスを確保するために、アプリケーションとデバイスの情報を提供するようアプリに要求されます。 [ブローカー認証](https://support.microsoft.com/account-billing/sign-in-to-your-accounts-using-the-microsoft-authenticator-app-582bdc07-4566-4c97-a7aa-56058122714c)に、iOS ユーザーは [Microsoft Authenticator アプリ](https://play.google.com/store/apps/details?id=com.microsoft.windowsintune.companyportal)を使用し、Android ユーザーは Microsoft Authenticator アプリまたは[ポータル サイト アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-android-single-sign-on)を使用します。 既定では、MSAL によって、認証要求を満たすための最初の選択肢としてブローカーが使用されます。したがって、既製の MSAL を使用する場合、認証のためのブローカーの使用がアプリに対して自動的に有効になります。

最後に、アプリ保護ポリシーを有効にするために、アプリに [Intune SDK を追加](https://learn.microsoft.com/ja-jp/mem/intune/developer/app-sdk-get-started)します。 ほとんどの場合、SDK はインターセプト モデルに従い、アプリ保護ポリシーが自動的に適用されて、アプリで実行されているアクションが許可されるかどうかが判断されます。 特定のアクションに制限があるかどうかをアプリに通知するために、手動で呼び出すことができる API もあります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-acquire-cache-tokens"} -->
## Microsoft Authentication Library (MSAL) を使用してトークンを取得しキャッシュする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-acquire-cache-tokens
- Service: identity-platform
- Article date: 2025-05-14
- Summary: MSAL を使用したトークンの取得とキャッシュについて説明します。

[Access tokens](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens) enable clients to securely call web APIs protected by Azure. Microsoft Authentication Library (MSAL) を使用してトークンを取得するには、いくつかの方法があります。 Web ブラウザーを使用したユーザー操作が必要なものもあれば、ユーザーの操作を必要としないものもあります。 通常、トークンを取得するために使われる方法は、アプリケーションがパブリック クライアント アプリケーション (デスクトップまたはモバイル) か、機密クライアント アプリケーション (Web アプリ、Web API、またはデーモン アプリ) かによって異なります。

MSAL では、トークンは取得された後でキャッシュされます。 アプリケーション コードでは、他の手段でトークンの取得を試行する前に、まず、キャッシュからのトークンの自動的な取得を試みる必要があります。

また、キャッシュからアカウントを削除することで、トークン キャッシュをクリアすることもできます。 ただし、これによってブラウザーにあるセッション Cookie が削除されることはありません。

### トークンを取得するときのスコープ

[Scopes](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview) are the permissions that a web API exposes that client applications can request access to. クライアント アプリケーションでは、Web API にアクセスするためのトークンを取得するために認証要求を行うとき、これらのスコープに対するユーザーの同意を要求します。 MSAL を使うと、Microsoft ID プラットフォーム API にアクセスするためのトークンを取得できます。 v2.0 プロトコルでは、リソースではなくスコープが要求で使用されます。 受け付けるトークンのバージョンに関する Web API の構成に基づいて、v2.0 エンドポイントから MSAL にアクセス トークンが返されます。

いくつかの MSAL のトークン取得方法には、`scopes` パラメーターが必要です。 `scopes` パラメーターは、要求された必要とするアクセス許可とリソースを宣言する文字列のリストです。 よく知られたスコープとしては、[Microsoft Graph アクセス許可](https://learn.microsoft.com/ja-jp/graph/permissions-reference)があります。

#### Web API のスコープを要求する

アプリケーションでリソース API に対する特定のアクセス許可を備えたアクセス トークンを要求する必要がある場合、API のアプリ ID URI を含むスコープを、`<app ID URI>/<scope>` 形式で渡します。

さまざまなリソースのスコープ値の例を次に示します。

- Microsoft Graph API: `https://graph.microsoft.com/User.Read`
- カスタム Web API: `api://aaaabbbb-0000-cccc-1111-dddd2222eeee/api.read`

スコープ値の形式は、アクセス トークンを受け取るリソース (API) と、それが受け入れる `aud` 要求の値によって異なります。

Microsoft Graph の場合のみ、`user.read` スコープは `https://graph.microsoft.com/User.Read` にマップされ、両方のスコープ形式を同じ意味で使用できます。

Azure Resource Manager API (`https://management.core.windows.net/`) などの特定の Web API では、アクセス トークンの audience クレーム (`/`) に末尾のスラッシュ (`aud`) が必要です。 この場合は、二重スラッシュ (`https://management.core.windows.net//user_impersonation`) を含めて、スコープを `//` として渡します。

その他の API では、スコープ値に*スキームやホストが含まれない*ことが必要になる場合があり、アプリ ID (GUID) とスコープ名のみを想定する場合もあります。次に例を示します。

```json
00001111-aaaa-2222-bbbb-3333cccc4444/api.read
```

Tip

ダウンストリーム リソースが制御下にない場合、アクセス トークンをリソースに渡すときに `401` またはその他のエラーが発生した場合は、異なるスコープ値の形式 (たとえば、スキームとホストを含めたり省略したりする) を試すことが必要な場合もあります。

#### 増分同意のために動的スコープを要求する

アプリケーションによって提供される機能やその要件が変更されると、スコープ パラメーターを使用して、必要に応じて追加のアクセス許可を要求できます。 Such *dynamic scopes* allow your users to provide incremental consent to scopes.

たとえば、ユーザーをサインインさせますが、最初はすべてのリソースへのアクセスを拒否します。 その後、トークン取得メソッドで予定表のスコープを要求して、そうすることへのユーザーの同意を取得することにより、ユーザーの予定表を表示する機能を提供できます。 たとえば、`https://graph.microsoft.com/User.Read` と `https://graph.microsoft.com/Calendar.Read` のスコープを要求して行います。

### (キャッシュからの) トークンの自動的な取得

MSAL は、1 つのトークン キャッシュ (または、機密クライアント アプリケーションの場合は 2 つのキャッシュ) を保持しており、取得した後のトークンをキャッシュします。 多くの場合、トークンを自動的に取得しようとすると、キャッシュ内のトークンに基づいて、より多くのスコープを備える別のトークンが取得されます。 また、期限切れが近いトークンを更新することもできます (トークン キャッシュには更新トークンも含まれるため)。

#### パブリック クライアント アプリケーションの推奨される呼び出しパターン

アプリケーションのソース コードでは、まず、キャッシュからトークンを自動的に取得することを試みる必要があります。 メソッドの呼び出しで "UI が必要" エラーまたは例外が返される場合、他の手段でトークンの取得を試みます。

There are two flows where you **should not** attempt to silently acquire a token:

- [クライアント資格情報フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-authentication-flows#client-credentials)では、ユーザー トークン キャッシュは使用されず、アプリケーション トークン キャッシュが使用されます。 この方法では、セキュリティ トークン サービス (STS) に要求を送信する前に、このアプリケーション トークン キャッシュの確認が行われます。
- Web アプリの[承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-authentication-flows#authorization-code)では、ユーザーをサインインさせることでアプリケーションが取得したコードを引き換えて、より多くのスコープに同意させます。 (アカウントでなく) コードがパラメーターとして渡されるため、メソッドはコードを引き換える前にキャッシュを参照することができません。そのため、サービスの呼び出しを起動します。

#### 承認コード フローが使用される Web アプリで推奨される呼び出しパターン

[OpenID Connect 認可コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)が使われる Web アプリケーションの場合、コントローラーで推奨されるパターンは次のとおりです。

- カスタマイズされたシリアル化を使用してトークン キャッシュで機密クライアント アプリケーションをインスタンス化します。
- 承認コード フローを使用してトークンを取得します

### Acquiring tokens

トークンを取得する方法は、アプリケーションがパブリック クライアントか機密クライアントかによって決まります。

#### パブリック クライアント アプリケーション

パブリック クライアント アプリケーション (デスクトップとモバイル) では、以下のことができます。

- UI またはポップアップ ウィンドウを使用してユーザーをサインインさせ、対話形式でトークンを取得します。
- ドメインまたは Azure に参加済みの Windows コンピューターでデスクトップ アプリケーションが実行されている場合、[統合 Windows 認証](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-authentication-flows#integrated-windows-authentication-iwa) (IWA および Kerberos) を使用して、サインインしたユーザーのトークンを、確認を表示せずに取得します。
- .NET Framework デスクトップ クライアント アプリケーションで、[ユーザー名とパスワード](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-authentication-flows#usernamepassword-ropc)を使用してトークンを取得します (推奨されません)。 機密クライアント アプリケーションでは、ユーザー名とパスワードを使わないでください。
- Web ブラウザーがないデバイスで実行されているアプリケーションでは、[デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-authentication-flows#device-code)を使用してトークンを取得します。 ユーザーは URL とコードを提供された後、別のデバイスの Web ブラウザーに移動し、コードを入力してサインインします。 その後、Microsoft Entra ID はブラウザーのないデバイスにトークンを送り返します。

#### 機密クライアント アプリケーション

機密クライアント アプリケーション (Web アプリ、Web API、または Windows サービスなどのデーモン アプリ) の場合は、以下のことができます。

- **クライアント資格情報フロー**を使用して、ユーザーではなく[アプリケーション自体に対する](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-authentication-flows#client-credentials)トークンを取得します。 この手法は、同期ツールに対して、または特定のユーザーではなくユーザー一般を処理するツールに対して、使用できます。
- ユーザーに代わって API を呼び出す Web API に対しては、[On-Behalf-Of (OBO) フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-authentication-flows#on-behalf-of-obo)を使用します。 ユーザー アサーションに基づいてトークンを取得するため、アプリケーションはクライアントの資格情報で識別されます (たとえば、SAML または JWT トークン)。 このフローは、サービス間呼び出しで特定のユーザーのリソースにアクセスする必要があるアプリケーションによって使用されます。 トークンは、ユーザー単位ではなくセッション単位でキャッシュする必要があります。
- Web アプリでは、ユーザーが承認要求 URL でサインインした後、[承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-authentication-flows#authorization-code)を使用してトークンを取得します。 OpenID Connect アプリケーションでは、通常、このメカニズム (ユーザーは OpenID Connect を使用してサインイン可能) を使用してから、ユーザーに代わって Web API にアクセスします。 トークンは、ユーザー単位またはセッション単位でキャッシュできます。 ユーザーベースでトークンをキャッシュする場合は、Microsoft Entra ID が条件付きアクセス ポリシーの状態を頻繁に確認できるように、セッションの有効期間を制限することをお勧めします。

### Authentication results

クライアントがアクセス トークンを要求すると、Microsoft Entra ID はアクセス トークンに関するメタデータを含む認証結果も返します。 この情報には、アクセス トークンの有効期限や、それが有効なスコープが含まれます。 このデータを使用すると、アプリはアクセス トークン自体を解析しなくても、そのアクセス トークンのインテリジェントなキャッシュを実行できます。 認証結果では以下が公開されます。

- The [access token](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens) for the web API to access resources. This string is usually a Base64-encoded JWT, but the client should **never** look inside the access token. この形式が変わらないことは保証されておらず、リソース用に暗号化できます。 クライアント上のアクセス トークンのコンテンツに応じてコードを記述している人は、エラーとクライアント ロジックの中断を起こす最も一般的な原因の 1 つです。
- The [ID token](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens) for the user (a JWT).
- トークンの有効期限。トークンの有効期限が切れる日付と時刻を示します。
- テナント ID には、ユーザーが見つかったテナントが含まれています。 ゲスト ユーザー (Microsoft Entra B2B のシナリオ) の場合、テナント ID は一意のテナントではなく、ゲスト テナントです。 トークンがユーザーの名前で提供されると、認証結果にはこのユーザーに関する情報も含まれます。 (アプリケーションの) ユーザーなしでトークンが要求される機密クライアント フローの場合、このユーザー情報は null です。
- トークンが発行されたスコープ。
- ユーザーの一意の ID。

### (上級) バックグラウンドのアプリやサービスで、キャッシュされたユーザーのトークンにアクセスする

不在のユーザーに代わって操作を続けられるよう、アクセス トークン キャッシュの使用をバックグラウンドのアプリ、API、サービスに許可するため、MSAL のトークン キャッシュ実装を利用できます。 これは特に、ユーザーがフロントエンド Web アプリを終了した後、バックグラウンドのアプリやサービスがユーザーの代わりに作業を続けなければならない場合に便利です。

Today, most background processes use [application permissions](https://learn.microsoft.com/ja-jp/graph/auth/auth-concepts#microsoft-graph-permissions) when they need to work with a user's data without them being present to authenticate or reauthenticate. アプリケーションのアクセス許可は多くの場合、特権の昇格を必要とする管理者の同意を必要とするため、不要な衝突が発生します。その理由は、開発者のアプリに対してユーザーが最初に同意した以上のアクセス権を取得することを開発者が意図しなかったことにあります。

GitHub にあるこのコード サンプルからは、バックグラウンド アプリから MSAL のトークン キャッシュにアクセスすることでこの不要な衝突を回避する方法を確認できます。

[バックグラウンドのアプリ、API、サービスからログイン ユーザーのトークン キャッシュにアクセスする](https://github.com/Azure-Samples/ms-identity-dotnet-advanced-token-cache)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-authentication-flows"} -->
## MSAL での認証フローのサポート - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-authentication-flows
- Service: identity-platform
- Article date: 2025-03-21
- Summary: アプリを効果的にセキュリティで保護するために、承認コード、クライアント資格情報、デバイス コードなど、MSAL でサポートされる認証フローについて説明します。

Microsoft Authentication Library (MSAL) では、さまざまなアプリケーションの種類とシナリオで使用できる複数の承認許可および関連付けられたトークン・フローがサポートされています。

| 認証フロー | 可能になること | サポートされているアプリケーションの種類 |
| --- | --- | --- |
| 承認コード | ユーザーに代わって、ユーザーのサインインと Web API へのアクセスを行います。 | [デスクトップ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-app-configuration)[モバイル](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-mobile-app-configuration)[シングルページ アプリ (SPA)](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-app-registration) (PKCE が必要) [ウェブ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-api-call-api-app-configuration) |
| クライアント資格情報 | アプリケーション自体の ID を使用して Web API にアクセスします。 通常は、サーバー間通信や、ユーザー操作を必要としない自動化されたスクリプトに使用されます。 | [デーモン](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-daemon-app-configuration) |
| デバイス コード | スマート TV や IoT デバイスなど、入力に制約のあるデバイスでユーザーに代わって、ユーザーのサインインと Web API へのアクセスを行います。 コマンド ライン インターフェイス (CLI) アプリケーションでも使用されます。 | [デスクトップ、モバイル](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-device-code-flow) |
| 暗黙的な許可 | ユーザーに代わって、ユーザーのサインインと Web API へのアクセスを行います。 *このフローは使用しないでください。代わりに PKCE で承認コードを使用してください。* | \* [シングルページ アプリ (SPA)](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-app-registration) \* [ウェブ](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-api-call-api-app-configuration) |
| On-Behalf-of (OBO) | ユーザーに代わって、"アップストリーム" Web API から "ダウンストリーム" Web API にアクセスします。 ユーザーの ID と委任されたアクセス許可は、アップストリーム API からダウンストリーム API に渡されます。 | [Web API](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-web-api-call-api-app-configuration) |
| ユーザー名/パスワード (ROPC) | アプリケーションはパスワードを直接処理することによって、ユーザーをサインインさせることができます。 *このフローは使用しないでください。* | [デスクトップ、モバイル](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-username-password) |
| 統合 Windows 認証 (IWA) | ドメインまたは Microsoft Entra 参加済みコンピューター上のアプリケーションが、(ユーザーからの UI 操作なしで) トークンを自動的に取得 *できるようにします。Workforce テナントのみ*。 | [デスクトップ、モバイル](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-desktop-acquire-token-integrated-windows-authentication) |

### トークン

アプリケーションでは、1 つ以上の認証フローを使用できます。 各フローでは、認証、承認、トークンの更新に特定のトークンの種類が使用され、一部では承認コードも使用されます。

| 認証フローまたはアクション | 必要 | ID トークン | アクセス トークン | 更新トークン | Authorization code (承認コード) |
| --- | --- | --- | --- | --- | --- |
| [承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) |  | [Image: 承認フローは ID トークンに対して機能する] | [Image: 承認フローはアクセス トークンに対して機能する] | [Image: 承認フローは更新トークンに対して機能する] | [Image: 承認コードは機能する] |
| [クライアント資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow) |  |  | [Image: 承認フローはアクセス トークンに対して機能する] (アプリのみ) |  |  |
| [デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code) |  | [Image: 承認フローは ID トークンに対して機能する] | [Image: 承認フローはアクセス トークンに対して機能する] | [Image: 承認フローは更新トークンに対して機能する] |  |
| [暗黙的なフロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow) |  | [Image: 承認フローは ID トークンに対して機能する] | [Image: 承認フローはアクセス トークンに対して機能する] |  |  |
| [On-Behalf-Of フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow) | アクセス トークン | [Image: 承認フローは ID トークンに対して機能する] | [Image: 承認フローはアクセス トークンに対して機能する] | [Image: 承認フローは更新トークンに対して機能する] |  |
| [ユーザー名/パスワード](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc) (ROPC) | ユーザー名、パスワード | [Image: 承認フローは ID トークンに対して機能する] | [Image: 承認フローはアクセス トークンに対して機能する] | [Image: 承認フローは更新トークンに対して機能する] |  |
| [ハイブリッド OIDC フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc#protocol-diagram-access-token-acquisition) |  | [Image: 承認フローは ID トークンに対して機能する] |  |  | [Image: 承認コードは機能する] |
| [更新トークンの使用](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow#refresh-the-access-token) | 更新トークン | [Image: 承認フローは ID トークンに対して機能する] | [Image: 承認フローはアクセス トークンに対して機能する] | [Image: 承認フローは更新トークンに対して機能する] |  |

#### 対話型と非対話型の認証

これらのフローのいくつかでは、対話型と非対話型の両方のトークンの取得がサポートされています。

- **対話型** - ユーザーに対して、承認サーバーから入力を求めるプロンプトが出される場合があります。 たとえば、サインインを要求したり、多要素認証 (MFA) を実行したり、追加のリソース アクセス許可に対する同意を付与したりすることができます。
- **非対話型 (サイレント)** - ユーザーに入力を求めるプロンプトを*出せません*。 "サイレント" トークン取得とも呼ばれ、アプリケーションは、承認サーバーがユーザーに入力を求めるプロンプトを*出さない*方法を使用してトークンを取得します。

MSAL ベースのアプリケーションでは、最初にトークンをサイレントで取得しようとします。その後、非対話型での試行が失敗した場合にのみ対話型の方法を試みます。 このパターンの詳細については、「[Microsoft Authentication Library (MSAL) を使用してトークンを取得し、キャッシュする](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-acquire-cache-tokens)」を参照してください。

### Authorization code (承認コード)

[OAuth 2.0 承認コード付与](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)は、Web アプリ、シングルページ アプリ (SPA)、ネイティブ (モバイルおよびデスクトップ) アプリで、Web API などの保護されたリソースにアクセスへのアクセス権を付与するために使用できます。

ユーザーが Web アプリケーションにサインインすると、アプリケーションは、Web API を呼び出すアクセス トークンに引き換えることができる承認コードを受け取ります。

下の図では、アプリケーションは次の処理を行います。

1. アクセス トークンと引き換えられた承認コードを要求します
2. アクセス トークンを使用して Web API (Microsoft Graph) を呼び出します

[Image: 承認コード フローの図。]

#### 承認コードの制約

- シングルページ アプリケーションでは、承認コード付与フローを使用するときに、*Proof Key for Code Exchange (PKCE)* が必要です。 PKCE は MSAL でサポートされています。
- OAuth 2.0 仕様では、承認コードを使用してアクセス トークンを "1 回だけ" 引き換える必要があります。

    同じ承認コードを使用してアクセス トークンを複数回取得しようとすると、Microsoft ID プラットフォームから次のようなエラーが返されます。 一部のライブラリとフレームワークでは自動的に承認コードが要求されるため、そのような場合に手動でコードを要求した場合にもこのエラーが発生します。

    ```console
    AADSTS70002: Error validating credentials. AADSTS54005: OAuth2 Authorization code was already redeemed, please retry with a new valid code or use an existing refresh token.
    ```

### クライアント資格情報

[OAuth 2.0 クライアントの資格情報フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)により、アプリケーションの ID を使って Web でホストされているリソースにアクセスできます。 この種類の許可は、バックグラウンドでの実行が必要なサーバー間の相互作用に使用され、ユーザーとの即時の相互動作は必要ありません。 これらのアプリケーションは、デーモンまたはサービス アカウントと呼ばれます。

クライアント資格情報許可フローでは、Web サービス (Confidential クライアント) が別の Web サービスを呼び出すときに、ユーザーを偽装する代わりに、独自の資格情報を使用して認証することができます。 このシナリオでは、クライアントは通常、中間層の Web サービス、デーモン サービス、または Web サイトです。 高いレベルの保証では、Microsoft ID プラットフォームにより、呼び出し元サービスが、資格情報として (共有シークレットではなく) 証明書を使用することもできます。

#### アプリケーション シークレット

下の図では、アプリケーションは次の処理を行います。

1. アプリケーションのシークレットまたはパスワード資格情報を使用してトークンを取得します
2. トークンを使用してリソースの要求を行います

[Image: パスワードを使用する機密クライアントの図。]

#### 証明書

下の図では、アプリケーションは次の処理を行います。

1. 証明書の資格情報を使用してトークンを取得します
2. トークンを使用してリソースの要求を行います

[Image: 証明書を使用する機密クライアントの図。]

これらのクライアント資格情報では以下のことが必要です。

- Microsoft Entra ID に登録されている
- コード内の機密クライアント アプリケーションのオブジェクトの構築時に渡される

#### クライアント資格情報の制約

機密クライアントフローは、Android、iOS、UWP などのモバイル プラットフォームでは**サポートされません**。 モバイル アプリケーションは、資格情報の機密性を保証できないパブリック クライアント アプリケーションと見なされます。

### デバイス コード

[OAuth 2.0 デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code)では、ユーザーがスマート TV、IoT デバイス、プリンターなどの入力制限のあるデバイスにサインインできます。 Microsoft Entra ID による対話型認証には Web ブラウザーが必要です。 Web ブラウザーを提供しないデバイスまたはオペレーティング システムでは、ユーザーはデバイス コード フローによって別のデバイス (コンピューターや携帯電話など) を使用して対話形式でサインインできます。

デバイス コード フローを使用すると、アプリケーションでは、これらのデバイスおよびオペレーティング システム用に設計された 2 ステップ プロセスを通じてトークンを取得します。 このようなアプリケーションには、IoT デバイスで実行されているアプリケーションやコマンドライン インターフェイス (CLI) ツールなどがあります。

次の図をご覧ください。

1. ユーザー認証が必要になるたびに、アプリがコードを提供し、ユーザーに別のデバイス (インターネットに接続されたスマートフォンなど) を使用して特定の URL (たとえば、`https://microsoft.com/devicelogin`) に移動するよう求めます。 その後、ユーザーはコードの入力を求められ、必要に応じて、同意のプロンプトや[多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)を含む通常の認証エクスペリエンスが実行されます。
2. 認証が成功すると、コマンドライン アプリはバック チャネル経由で必要なトークンを受信し、それらを使用して必要な Web API の呼び出しを実行します。

[Image: デバイス コード フローの図。]

#### デバイス コードの制約

- デバイス コード フローは、パブリック クライアント アプリケーションでのみ使用できます。
- MSAL でパブリック クライアント アプリケーションを初期化する場合は、次のいずれかの形式の機関を使用します。
    - テナント: `https://login.microsoftonline.com/{tenant}/,` ここで、`{tenant}` は、テナント ID またはテナントに関連付けられているドメイン名です。
    - 職場または学校のアカウント: `https://login.microsoftonline.com/organizations/`

### Implicit grant (暗黙的な付与)

暗黙的な許可フローは、クライアント側のシングルページ アプリケーション (SPA) で推奨される、よりセキュアなトークン許可フローとして、[PKCE を使用した承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)に置き換えられました

警告

暗黙的な許可フローを使用することは推奨されなくなりました。 SPA を構築する場合は、代わりに PKCE を使用した承認コード フローを使用してください。

JavaScript で記述されたシングルページ Web アプリ (Angular、Vue.js、React.js などのフレームワークを含む) がサーバーからダウンロードされ、そのコードがブラウザーで直接実行されます。 クライアント側のコードが Web サーバーではなくブラウザーで実行されるため、従来のサーバー側の Web アプリケーションとは異なるセキュリティ特性を持ちます。 承認コード フローに対して Proof Key for Code Exchange (PKCE) が使用可能になる前は、アクセストークンを取得する際の応答性と効率を向上させるために、暗黙的な許可フローが SPA によって使用されていました。

[OAuth 2.0 の暗黙的な許可のフロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow)では、バックエンド サーバーとの資格情報交換を実行しなくても、アプリによって Microsoft ID プラットフォームからアクセス トークンを取得できます。 暗黙的な許可フローを使用すると、アプリで、ユーザーのサインイン、セッションの維持、ユーザー エージェント (通常は Web ブラウザー) によってダウンロードおよび実行される JavaScript コード内からの他の Web API のトークンの取得が可能になります。

[Image: 暗黙的な許可のフローの図]

#### 暗黙的な許可の制約

暗黙的な許可フローには、Electron や React Native などのクロスプラットフォーム JavaScript フレームワークを使用するアプリケーション シナリオは含まれていません。 このようなクロスプラットフォーム フレームワークでは、それらが動作するデスクトップおよびモバイルのネイティブ プラットフォームと対話するための追加の機能が必要です。

暗黙的モードで発行されたトークンには、URL によってブラウザーに返されるために**長さの制限**があります (`response_mode` は `query` または `fragment` のいずれか)。 一部のブラウザーでは、ブラウザーのバー内の URL の長さが制限され、長すぎると失敗します。 そのため、これらの暗黙的なフロー トークンには `groups` または `wids` 要求が含まれていません。

### オンビハーフ・オブ (OBO)

[OAuth 2.0 の On-Behalf-Of 認証フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)は、アプリケーションでサービスまたは Web API を呼び出し、それがさらに別のサービスまたは Web API を呼び出す必要がある場合に、使用されます。 その目的は、委任されたユーザー ID とアクセス許可を要求チェーンを介して伝達することです。 中間層サービスがダウンストリーム サービスに認証済み要求を発行するには、そのサービスは Microsoft ID プラットフォームからのアクセス トークンをユーザーに*代わって*セキュリティ保護する必要があります。

次の図をご覧ください。

1. アプリケーションは Web API 用のアクセス トークンを取得します。
2. クライアント (Web、デスクトップ、モバイル、またはシングルページ アプリケーション) が保護された Web API を呼び出し、HTTP 要求の認証ヘッダーのベアラー トークンとしてアクセス トークンを追加します。 Web API がユーザーを認証します。
3. クライアントが Web API を呼び出すと、Web API はユーザーの代わりに別のトークンを要求します。
4. 保護された Web API は、このトークンを使用して、ユーザーの代わりにダウンストリームの Web API を呼び出します。 Web API は、他のダウンストリーム API のトークンを (ただし、ここでも同じユーザーの代わりとして) 後で要求することもできます。

[Image: on-behalf-of フローの図。]

### ユーザー名/パスワード (ROPC)

[OAuth 2.0 のリソース所有者のパスワード資格情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc) (ROPC) の付与により、アプリケーションでは、ユーザーのパスワードを直接処理することでユーザーをサインインさせることができます。 デスクトップ アプリケーションでは、ユーザー名/パスワードのフローを使ってサイレントにトークンを取得できます。 アプリケーションを使用するときに UI は必要ありません。

警告

ROPC フローは非推奨となりました。より安全なフローを使用してください。 移行 [ガイダンスについては、このガイド](https://aka.ms/msal-ropc-migration) に従ってください。 ROPC には、高度な信頼と資格情報の公開が必要です。詳細については、「 [パスワードの増大する問題の解決策とは」](https://news.microsoft.com/features/whats-solution-growing-problem-passwords-says-microsoft/)を参照してください。

下の図では、アプリケーションは次の処理を行います。

1. ID プロバイダーにユーザー名とパスワードを送信することによって、トークンを取得します
2. トークンを使用して Web API を呼び出します

[Image: ユーザー名/パスワード フローの図。]

Windows ドメインに参加済みのマシン上でサイレントにトークンを取得する場合は、ROPC ではなく統合 Windows 認証 (IWA) をお勧めします。 その他のシナリオでは、デバイス コード フローを使用してください。

#### ROPC の制約

ROPC フローを使用するアプリケーションには、次の制約が適用されます。

- シングル サインオンは**サポートされません**。
- 多要素認証 (MFA) は**サポートされません**。
    - このフローを使用する前に、テナント管理者に確認してください。MFA は一般的に使用されている機能です。
- 条件付きアクセスは**サポートされません**。
- ROPC は職場および学校のアカウントに*のみ*有効です。
- ROPC では、個人の Microsoft アカウント (MSA) は**サポートされません**。
- ROPC は、.NET デスクトップおよび ASP.NET Core のアプリケーションで**サポートされます**。
- ROPC は、ユニバーサル Windows プラットフォーム (UWP) アプリケーションでは**サポートされません**。
- Azure AD B2C での ROPC は、ローカル アカウントで*のみ*サポートされます。
    - MSAL.NET と Azure AD B2C の ROPC の詳細については、[Azure AD B2C での ROPC の使用](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/social-identities#resource-owner-password-credentials-ropc)に関する記事を参照してください。

### 統合 Windows 認証 (IWA)

MSAL では、ドメイン参加済みまたは Microsoft Entra 参加済み Windows コンピューター上で実行されるデスクトップおよびモバイルのアプリケーションに対して統合 Windows 認証 (IWA) がサポートされています。 IWA を使用すると、これらのアプリケーションは、ユーザーによる UI 操作なしでトークンをサイレントに取得します。

下の図では、アプリケーションは次の処理を行います。

1. 統合 Windows 認証を使用してトークンを取得します
2. トークンを使用してリソースの要求を行います

[Image: 統合 Windows 認証の図。]

#### IWA の制約

**互換性**

統合 Windows 認証 (IWA) は、.NET デスクトップ、.NET、および Windows ユニバーサル プラットフォームの各アプリに対して有効です。

IWA は AD FS フェデレーション ユーザー、つまり Active Directory で作成され、Microsoft Entra ID による支援があるユーザー*のみ*をサポートします。 Microsoft Entra ID で直接作成され、Active Directory のサポートのないユーザー (マネージド ユーザー) はこの認証フローを使用できません。

**多要素認証 (MFA)**

Microsoft Entra テナントで MFA が有効になっていて、Microsoft Entra ID によって MFA チャレンジが発行されている場合、IWA の非対話型 (サイレント) 認証は失敗する可能性があります。 IWA が失敗した場合は、前述のように、対話型の認証方式に切り替える必要があります。

Microsoft Entra ID では、AI を使用して、2 要素認証が必要な状況を判別します。 通常、2 要素認証は、ユーザーが別の国/地域からサインインする場合、VPN を使用せずに企業ネットワークに接続する場合、および VPN 経由で \*接続されている場合に必要になります。 MFA の構成とチャレンジの頻度は開発者が制御できないため、アプリケーションが、IWA のサイレント トークン取得の失敗を適切に処理する必要があります。

**機関 URI の制限**

パブリック クライアント アプリケーションを構築するときに渡される機関は、次のいずれかである必要があります。

- `https://login.microsoftonline.com/{tenant}/` - この機関は、サインイン対象ユーザーが指定された Microsoft Entra テナント内のユーザーに制限されている、シングル テナント アプリケーションを示します。 `{tenant}` の値には、GUID 形式のテナント ID、またはテナントに関連付けられているドメイン名を指定できます。
- `https://login.microsoftonline.com/organizations/` - この機関は、サインイン対象ユーザーが任意の Microsoft Entra テナントのユーザーであるマルチテナント アプリケーションを示します。

個人の Microsoft アカウント (MSA) は IWA でサポートされないため、機関の値に `/common` または `/consumers` を含めてはなりません。

**同意の要件**

IWA はサイレント フローであるため、次のようになります。

- アプリケーションのユーザーが、アプリケーションの使用に事前に同意しておく必要があります。

    *又は*
- テナント管理者が、テナント内のすべてのユーザーによるアプリケーションの使用に事前に同意しておく必要があります。

いずれかの要件を満たすには、次のいずれかの操作が完了している必要があります。

- アプリケーション開発者が Azure portal で自分自身に対して**[許可]**を選択しておきます。
- テナント管理者が Azure portal のアプリ登録の **[API のアクセス許可]** タブにある **[{テナント ドメイン} の管理者の同意を付与/取り消す]** を選択しておきます (「[Web API にアクセスするためのアクセス許可を追加する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-access-web-apis#add-permissions-to-access-your-web-api)」を参照)。
- ユーザーがアプリケーションに同意する方法を指定しておきます (「[ユーザーの同意](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/user-admin-consent-overview#user-consent)」を参照)。
- テナント管理者がアプリケーションに同意する方法を指定しておきます ([管理者の同意](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/user-admin-consent-overview#admin-consent)に関するセクションを参照)。

同意の詳細については、[アクセス許可と同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#consent)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-b2c-overview"} -->
## Azure AD B2C で MSAL.js を使用する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-b2c-overview
- Service: identity-platform
- Article date: 2023-03-07
- Summary: Microsoft Authentication Library for JavaScript (MSAL.js) を使用すると、アプリケーションは Azure AD B2C を操作し、セキュリティで保護された Web API を呼び出すトークンを取得できます。 これらの Web API には、Microsoft Graph、他の Microsoft API、他のユーザーの Web API、または独自の Web API を使用できます。

Von Bedeutung

2025 年 5 月 1 日より、Azure Active Directory B2C (Azure AD B2C) は新規のお客様が購入できなくなります。 詳細については、FAQ の [「Azure AD B2C を引き続き購入できますか?」を](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq?tabs=app-reg-ga#azure-ad-b2c-end-of-sale) 参照してください。

[JavaScript 用 Microsoft Authentication Library (MSAL.js)](https://github.com/AzureAD/microsoft-authentication-library-for-js) を使用すると、JavaScript 開発者は [Azure Active Directory B2C (Azure AD B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/overview)) を使用して、ソーシャル ID とローカル ID でユーザーを認証できます。

Id 管理サービスとして Azure AD B2C を使用すると、顧客がアプリケーションを使用する際のプロファイルのサインアップ、サインイン、および管理方法をカスタマイズおよび制御できます。

Azure AD B2C を使用すると、認証プロセス中にアプリケーションに表示される UI をブランド化およびカスタマイズすることもできます。

### サポートされているアプリの種類とシナリオ

MSAL.js を使用すると、[シングルページ アプリケーション](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/application-types#single-page-applications)は、PKCE を用いた[認可コードフロー](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/authorization-code-flow)を利用して Azure AD B2C にユーザーがサインインできます。 MSAL.js と Azure AD B2C の場合:

- ユーザー **は、** ソーシャル ID とローカル ID で認証できます。
- ユーザーは、Azure AD B2C で保護されたリソースへのアクセスを承認 **できます** (ただし、Microsoft Entra で保護されたリソースにはアクセスできません)。
- ユーザー**は**[、委任されたアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#types-of-permissions)を使用して Microsoft API (MS Graph API など) のトークンを取得できません。
- 管理者特権を持つユーザーは、**委任されたアクセス許可**を使用して Microsoft API (MS Graph API など) のトークンを取得[できます](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#types-of-permissions)。

詳細については、「[Azure AD B2C の使用](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/working-with-b2c.md)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-client-application-configuration"} -->
## クライアント アプリケーションの構成 (MSAL) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-application-configuration
- Service: identity-platform
- Article date: 2025-05-14
- Summary: Microsoft Authentication Library (MSAL) を使用したパブリック クライアント アプリケーションと機密クライアント アプリケーションの構成オプションについて説明します。

認証してトークンを取得するために、コードで新しいパブリックまたは機密クライアント アプリケーションを初期化します。 Microsoft Authentication Library (MSAL) でクライアント アプリを初期化するときに、いくつかの構成オプションを設定できます。 これらのオプションは、次の 2 つのグループに分類されます。

- 登録オプション。以下が含まれます。
    - 機関 (アプリの ID プロバイダー インスタンス とサインイン 対象ユーザー で構成され、場合によってはテナント ID)
    - クライアント ID
    - リダイレクト URI
    - クライアント シークレット (機密クライアント アプリケーション用)
    - 証明書 (機密クライアント アプリケーション用)
    - フェデレーション ID 資格情報 (機密クライアント アプリケーション用)
- ログ レベル、個人データの制御、ライブラリを使用したコンポーネントの名前などのログ オプション

### 権威

機関は、MSAL がトークンを要求できるディレクトリを示す URL です。

一般的な機関を次に示します。

| 一般的な機関の URL | 使用する場合 |
| --- | --- |
| `https://login.microsoftonline.com/<tenant>/` | 特定の組織のユーザーのみのサインイン。 URL の `<tenant>` は、Microsoft Entra テナントのテナント ID (GUID) またはそのテナント ドメインです。 |
| `https://login.microsoftonline.com/common/` | 職場および学校アカウント、または個人用 Microsoft アカウントを持つユーザーのサインイン。 |
| `https://login.microsoftonline.com/organizations/` | 職場および学校アカウントを持つユーザーのサインイン。 |
| `https://login.microsoftonline.com/consumers/` | 個人用の Microsoft アカウント (MSA) のみを持つユーザーのサインイン。 |

コードで指定する機関は、Azure portal のアプリ登録でアプリに対して指定 **したサポートされているアカウントの種類** と一致 **している** 必要があります。

以下の機関が可能です。

- Microsoft Entra クラウド機関。
- Azure AD B2C 機関。 [B2C の詳細を参照してください](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/social-identities)。
- Active Directory フェデレーション サービス (AD FS) 機関。 [AD FS のサポート](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/adfs-support)を参照してください。

Microsoft Entra クラウド機関には、次の 2 つの部分があります。

- ID プロバイダー *インスタンス*
- アプリのサインイン*対象ユーザー*

インスタンスと対象ユーザーを連結して、機関 URL として指定できます。 次の図は、機関 URL がどのように構成されるかを示しています。

[Image: 権威 URL の構成方法]

### クラウド インスタンス

*このインスタンス*は、アプリが Azure パブリック クラウドまたは国内クラウドのユーザーに署名するかどうかを指定するために使用されます。 コードで MSAL を使用すると、列挙を使用するか、 メンバーとして`Instance`に URL を渡すことによって、Azure クラウド インスタンスを設定できます。

`Instance`と`AzureCloudInstance`の両方が指定されている場合、MSAL.NET は明示的な例外をスローします。

インスタンスを指定しない場合、アプリは Azure パブリック クラウド インスタンス (URL `https://login.onmicrosoftonline.com`のインスタンス) を対象とします。

### アプリケーションの対象ユーザー

サインイン対象ユーザーは、アプリのビジネス ニーズによって異なります。

- 基幹業務 (LOB) 開発者の場合は、組織でのみ使用されるシングルテナント アプリケーションを作成する可能性があります。 その場合、テナント ID (Microsoft Entra インスタンスの ID) か、Microsoft Entra インスタンスに関連付けられたドメイン名で組織を指定します。
- ISV の方であれば、ユーザーに、すべての組織または一部の組織で職場および学校アカウントを使用してサインインしてもらうケースが考えられます (マルチテナント アプリ)。 ただし、ユーザーに個人用 Microsoft アカウントでサインインしてもらうこともあります。

#### コード/構成で対象ユーザーを指定する方法

コードで MSAL を使用する場合は、次のいずれかの値を使用して対象ユーザーを指定します。

- Microsoft Entra 機関の対象ユーザー列挙
- テナント ID (次のいずれか)
    - シングルテナント アプリケーションの GUID (Microsoft Entra インスタンスの ID)
    - Microsoft Entra インスタンスに関連付けられているドメイン名 (これもシングルテナント アプリケーション用)
- Microsoft Entra 機関の対象ユーザーの列挙に代わるテナント ID として、次のいずれかのプレースホルダーを使用します:
    - `organizations`: マルチテナント アプリケーションの場合
    - `consumers`: ユーザーの個人アカウントでのみサインインする場合
    - `common`: 職場および学校アカウント、または個人用 Microsoft アカウントを持つユーザーのサインインに使用

Microsoft Entra 機関の対象ユーザーとテナント ID の両方を指定すると、MSAL は意味のある例外をスローします。

対象ユーザーを指定することをお勧めします。多くのテナントとその中にデプロイされるアプリケーションにはゲスト ユーザーが含まれるためです。 アプリケーションが外部ユーザーを対象としている場合は、 `common` と `organization` エンドポイントを避けてください。 対象ユーザーを指定しない場合、アプリは Microsoft Entra ID と個人の Microsoft アカウントを対象ユーザーとして対象とし、 `common` が指定されたかのように動作します。

#### 有効な対象ユーザー

アプリケーションで有効な対象ユーザーは、アプリに設定した対象ユーザーとアプリの登録で指定された対象ユーザーの最小値 (共通集合がある場合) になります。 実際、 [アプリの登録](https://aka.ms/appregistrations) エクスペリエンスでは、アプリの対象ユーザー (サポートされているアカウントの種類) を指定できます。 詳細については、「 [クイック スタート: アプリケーションを Microsoft ID プラットフォームに登録する」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)参照してください。

現時点では、個人用 Microsoft アカウントのみを持つユーザーのサインインをアプリで実行する唯一の方法は、次の両方の設定を構成することです。

- アプリの登録の対象ユーザーを `Work and school accounts and personal accounts` に設定する。
- コードまたは構成で、対象ユーザーを `AadAuthorityAudience.PersonalMicrosoftAccount` (または `TenantID` = "consumers") に設定する。

### クライアントID

クライアント ID は、アプリが登録されたときに Microsoft Entra ID によってアプリに割り当てられた一意のアプリケーション **(クライアント)** ID です。 **アプリケーション (クライアント) ID** は、アプリケーションの [概要] ページの **Entra ID**&gt;**Enterprise アプリ**にあります。

### リダイレクト URI

リダイレクト URI は、ID プロバイダーがセキュリティ トークンを返送する URI です。

#### パブリック クライアント アプリ用のリダイレクト URI

MSAL を使用してパブリック クライアント アプリを開発している場合:

- デスクトップ アプリケーション (MSAL.NET 4.1 以降) で `.WithDefaultRedirectUri()` を使用する必要があります。 `.WithDefaultRedirectUri()` メソッドは、パブリック クライアント アプリケーションのリダイレクト URI プロパティを、パブリック クライアント アプリケーションの既定の推奨リダイレクト URI に設定します。

    | プラットフォーム | リダイレクト URI |
    | --- | --- |
    | デスクトップ アプリ (.NET Framework) | `https://login.microsoftonline.com/common/oauth2/nativeclient` |
    | ユニバーサル Windows プラットフォーム (UWP) | `WebAuthenticationBroker.GetCurrentApplicationCallbackUri()` の値。 これは、登録する必要がある WebAuthenticationBroker.GetCurrentApplicationCallbackUri() の結果に値を設定することによって、ブラウザーでのシングル サインオン (SSO) を有効にします |
    | 。網 | 今のところ、埋め込み Web ビュー用の UI が .NET には存在しないため、`https://localhost` によって、ユーザーはシステム ブラウザーを使用して対話型認証を実行できるようになります。 |

`RedirectUri` プロパティを使用して、このリダイレクト URI をオーバーライドできます (ブローカーを使用する場合など)。 そのシナリオでのリダイレクト URI の例を次に示します。

- `RedirectUriOnAndroid` = `"msauth-00001111-aaaa-2222-bbbb-3333cccc4444://com.microsoft.identity.client.sample";`
- `RedirectUriOnIos` = `$"msauth.{Bundle.ID}://auth";`

Android の詳細については、 [Android でのブローカー認証に](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-android-single-sign-on)関するページを参照してください。

- MSAL Android を使用してアプリをビルドする場合は、初期`redirect_uri` 手順の中で  を構成するか、後で追加できます。

    - リダイレクト URI の形式は次のとおりです。`msauth://<yourpackagename>/<base64urlencodedsignature>`
    - 例: `redirect_uri` = `msauth://com.azuresamples.myapp/6/aB1cD2eF3gH4iJ5kL6-mN7oP8qR=`
- MSAL Android アプリの構成の詳細については、 [MSAL Android の構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-configuration)に関するページを参照してください。
- [アプリの登録](https://aka.ms/appregistrations)でリダイレクト URI を構成します。

    [Image: [アプリの登録] ページの [リダイレクト URI] ウィンドウとオプションを示すスクリーンショット。]

#### 機密性の高いクライアント アプリ用のリダイレクト URI

Web アプリの場合、リダイレクト URI (または応答 URL) は、Microsoft Entra ID がアプリケーションにトークンを戻すために使用する URI です。 機密性の高いアプリが Web アプリと Web API のどちらかである場合は、その URL を使用できます。 リダイレクト URI は、アプリの登録で登録する必要があります。 この登録は、最初にローカルでテストを行ったアプリをデプロイするときに特に重要です。 アプリケーション登録ポータルで、デプロイ対象のアプリの応答 URL を追加する必要があります。

デーモン アプリの場合は、リダイレクト URI を指定する必要はありません。

### アプリケーション資格情報

機密クライアント アプリケーションでは、資格情報を効果的に管理することが不可欠です。 資格情報には、フェデレーション資格情報 (推奨)、証明書、またはクライアント シークレットを指定できます。

#### フェデレーション ID 資格情報

フェデレーション ID 資格情報は、ワークロード (GitHub Actions、Kubernetes で実行されているワークロード、Azure 以外のコンピューティング プラットフォームで実行されているワークロードなど) が、[ワークロード ID フェデレーション](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identity-federation)を使用してシークレットを管理することなく Microsoft Entra で保護されたリソースにアクセスできるようにする資格情報の一種です。

#### 証書

このオプションでは、機密クライアント アプリの証明書を指定します。 *公開キー*と呼ばれることもあります。証明書は、クライアント シークレットよりも安全であると見なされるため、推奨される資格情報の種類です。

#### クライアント シークレット

このオプションでは、機密性の高いクライアント アプリ用のクライアント シークレットを指定します。 クライアント シークレット (アプリ パスワード) は、アプリケーション登録ポータルによって提供されるか、PowerShell Microsoft Entra ID、PowerShell AzureRM、Azure CLI のいずれかを使用したアプリの登録時に Microsoft Entra ID に対して提供されます。

### ロギング（記録）

デバッグと認証エラーのトラブルシューティングのシナリオを支援するために、MSAL は組み込みのログ記録をサポートしています。 各ライブラリでのログ記録については、次の記事で説明されています。

- [MSAL.NET のログイン](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/advanced/exceptions/msal-logging)
- [Android 用 MSAL でのログ記録](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-logging-android)
- [MSAL.jsのログイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-logging-js)

- [iOS/macOS 用 MSAL でのログ記録](https://learn.microsoft.com/ja-jp/entra/msal/objc/logging-ios)
- [MSAL for Java でのログ記録](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/msal-logging-java)
- [Python 用 MSAL でのログ記録](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/msal-logging-python)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-client-applications"} -->
## パブリックおよび機密のクライアント アプリ (MSAL) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-applications
- Service: identity-platform
- Article date: 2025-05-14
- Summary: Microsoft Authentication Library (MSAL) でのパブリック クライアント アプリケーションと機密クライアント アプリケーションについて説明します。

Microsoft Authentication Library (MSAL) には、パブリック クライアントと機密クライアントの 2 種類のクライアントが定義されています。 クライアントは、ID プロバイダーによって割り当てられた一意識別子を持つソフトウェア エンティティです。 クライアントの種類は、承認サーバーで安全に認証する機能と、ID を提供する機密情報を保持して、アクセスのスコープ内でその情報がユーザーにアクセスされたり知られたりできないようにする機能によって区別されます。

| パブリック クライアント アプリ | 機密クライアント アプリ |
| --- | --- |
| [Image: デスクトップ アプリ] デスクトップ アプリ | [Image: Web アプリ] Web アプリ |
| [Image: ブラウザーレス API] ブラウザーレス API | [Image: ウェブAPI] ウェブAPI |
| [Image: モバイル アプリ] モバイル アプリ | [Image: デーモン/サービス] サービス/デーモン |

### パブリック クライアントと機密クライアントの認可

特定のクライアントの性質がパブリックであるか機密であるかを調べる場合、そのクライアントが認証サーバーに自身の ID を証明できるかどうかを評価します。 承認サーバーがアクセス トークンを発行するには、クライアントの ID を信頼できる必要があるため、これは重要です。

- **パブリック クライアント アプリケーション**はデスクトップなどのデバイス上で実行され、ブラウザーレス API、モバイル アプリ、クライアント側のブラウザー アプリなどがあります。 これらは、ユーザーに代わって Web API にアクセスできるだけであり、アプリケーション シークレットが安全に保持されるかどうかは信頼できません。 特定のアプリのソースまたはコンパイル済みバイトコードが、信頼されていないパーティによる読み取り、逆アセンブル、またはその他の方法での検査が可能な場所にいつでも送信される場合、それはパブリック クライアントです。 また、パブリック クライアントは、パブリック クライアント フローのみをサポートし、構成時のシークレットを保持できないため、クライアント シークレットを持つことはできません。
- **機密クライアント アプリケーション**はサーバー上で実行され、Web アプリ、Web API アプリ、サービス/デーモン アプリなどがあります。 これらはユーザーや攻撃者によるアクセスが難しいと考えられており、そのため、構成時のシークレットを適切に保持して、その ID の証拠をアサートすることができます。 クライアント ID は Web ブラウザーを介して公開されますが、シークレットはバック チャネルでのみ渡され、直接公開されることはありません。

### アプリの登録でパブリック クライアント フローを有効にする必要があるのはいつですか?

ビルドするクライアント アプリケーションの種類を決定すると、アプリの登録でパブリック クライアント フローを有効にするかどうかを決定できます。 既定では、ユーザーまたは開発者がパブリック クライアント アプリケーションをビルドし、次の OAuth 認可プロトコルまたは機能を使用していない限り、アプリの登録でパブリック クライアント フローを許可することを無効にする必要があります。

| OAuth 認可プロトコル/機能 | パブリック クライアント アプリケーションの種類 | 例/メモ |
| --- | --- | --- |
| [ネイティブ認証](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication) | Microsoft Entra 外部 ID アプリケーションは、デザイン要素、ロゴの配置、レイアウトなど、ユーザー インターフェイスの完全なカスタマイズが必要で、一貫性のあるブランド化された外観が得られます。 | **注:** ネイティブ認証は、Microsoft Entra 外部 ID テナントでのアプリ登録でのみ使用できます。 [詳しくはこちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/external-id/customers/concept-native-authentication) |
| [デバイス コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code) | スマート テレビ、IoT デバイス、プリンターなど、入力に制約のあるデバイスで実行されるアプリケーション |  |
| [リソース所有者のパスワード資格情報のフロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc) | ユーザーを Entra でホストされているログイン Web サイトにリダイレクトし、Entra に安全な方法でユーザー パスワードを処理させる代わりに、ユーザーが直接入力するパスワードを処理するアプリケーション。 | **ROPC フローは使用しないことをお勧めします**。 ほとんどのシナリオでは、承認コード フローなどのより安全な代替手段が使用でき、推奨されます。 |
| [Windows 統合認証フロー](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/acquiring-tokens/desktop-mobile/integrated-windows-authentication) | Web アカウント マネージャーではなく Windows 統合認証フローを使用して、Windows 上、または Windows ドメイン (Microsoft Entra ID または Microsoft Entra ID 参加済み) に接続されているコンピューター上で実行されている、デスクトップ アプリケーションまたはモバイル アプリケーション | ユーザーが Microsoft Entra 資格情報を使用して Windows PC システムにサインインした後は、自動的にサインインするようになっているデスクトップ アプリケーションまたはモバイル アプリケーション |

#### ID を証明する上でのシークレットとその重要性

以下に、クライアントが承認サーバーに対して自身の ID を証明する方法の例をいくつか示します。

- **Azure リソースのマネージド ID** - アプリのみの認証シナリオの場合、Azure 上に構築するアプリケーションおよびサービスの開発者には、シークレットの管理、ローテーション、保護をプラットフォーム自体にオフロードするオプションがあります。 マネージド ID を使用すると、ID は Azure リソースと共に提供および削除され、[全体管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)を含め誰も、基になる資格情報にアクセスすることはできません。 マネージド ID を使用すると、機密漏洩のリスクを防ぎ、プロバイダーにセキュリティへの対処を任せることができます。
- **クライアント ID とシークレット** - このパターンでは、クライアントの登録時に承認サーバーによって値のペアが生成されます。 クライアント ID はアプリケーションを識別するパブリック値であり、クライアント シークレットはアプリケーションの ID を証明するために使用される機密値です。
- **証明書の所有の証明** - [X.509](https://learn.microsoft.com/ja-jp/azure/iot-hub/reference-x509-certificates) などの標準を含む公開キー基盤 (PKI) は、インターネット上での安全な通信を可能にし、インターネット プライバシーのバックボーンを形成する基本的なテクノロジです。 PKI は、オンライン通信に関与するパーティの ID を確認するデジタル証明書を発行するために使用され、Web トラフィックのセキュリティ保護に広く使用されている HTTPS などのプロトコルを強化するための基盤となるテクノロジです。 同様に、証明書を使用すると、サービス間の相互認証を有効にして、Azure でのサービス間 (S2S) 通信をセキュリティで保護できます。 このためには、各サービスがその ID を証明する手段として証明書を他のサービスに提示する必要があります。
- **署名付きアサーションの提示** - 署名付きアサーションをワークロード ID フェデレーションで使用すると、信頼されたサード パーティの ID プロバイダー トークンを Microsoft ID プラットフォームと交換して、Microsoft Entra で保護されたリソースを呼び出すためのアクセス トークンを取得できます。 ワークロード ID フェデレーションを使用すると、Azure Kubernetes Service、アマゾン ウェブ サービス EKS、GitHub Actions など、さまざまなフェデレーション シナリオを有効にすることができます。

### クライアントの ID を証明することが重要な場合

機密データやリソースへのアクセスを許可する前に、クライアント アプリケーションの信頼性と承認の両方を検証する必要がある場合、クライアント ID を証明することが重要になります。 次に例をいくつか示します。

- **API アクセスの制御** - 測定される (たとえば、従量制課金など) API がある場合、または機密データやリソースを公開する場合、アクセスを許可する前にクライアントの ID を確認します。 たとえば、これは、確実に承認されたアプリケーションのみが API にアクセスできるようにする場合、および従量制課金 API の使用に対して適切な顧客に確実に課金されるようにする場合に重要です。
- **アプリの偽装からのユーザー保護** - サービスにデプロイされ、機密データやサービスにアクセスするユーザー向けアプリケーション (バックエンド駆動型 Web アプリなど) がある場合、クライアント シークレットを使用して、そのアプリケーションで使用されるリソースを保護すると、不正なアクターが正規のクライアントを偽装してユーザーをフィッシングしたり、データや不正アクセスを流出させたりするのを防ぐことができます。
- **S2S 通信** - 相互に通信する必要がある複数のバックエンド サービス (ダウンストリーム API など) がある場合は、各サービスの ID を確認して、その機能を実行するために必要なリソースに対してのみアクセスが許可されていることを確認できます。

一般に、ユーザーとは関係なく、またはユーザーに加えて、クライアントを認証および承認する必要がある場合、クライアント ID を証明することが重要になります。

### 機密クライアント: シークレットを管理するためのベスト プラクティス

**マネージド ID を使用してデプロイとセキュリティを簡略化する** - [マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) を使用すると、Microsoft Entra 認証をサポートするリソースに接続するときにアプリケーションで使用される Microsoft Entra ID のマネージド ID が自動的に提供されます。 アプリケーションでは、マネージド ID を使用して Microsoft Entra ID アプリ専用トークンを取得でき、資格情報を管理する必要はありません。 これにより、セキュリティと回復性を高めながら、シークレット管理に伴う複雑さの多くを取り除くことができます。 マネージド ID を使用している場合は、次のベスト プラクティスのすべてではないとしてもほとんどが、既に対応されています。

**セキュリティで保護されたストレージを使用する** - [Key Vault](https://azure.microsoft.com/products/key-vault/) や[暗号化された構成ファイル](https://learn.microsoft.com/ja-jp/azure/devops/pipelines/process/set-secret-variables)など、セキュリティで保護された場所にクライアント シークレットを格納します。 クライアント シークレットをプレーンテキストで格納したり、バージョン管理システムにチェックイン済みファイルとして格納したりしないようにしてください。

**アクセスを制限する** - クライアント シークレットへのアクセスを、承認された担当者のみに制限します。 クライアント シークレットへのアクセスを、運用業務を履行するために必要とするユーザーのみに制限するには、[ロールベースのアクセス制御](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/overview)を使用します。

**クライアント シークレットをローテーションする** - 必要に応じて、またはスケジュールに基づいて[クライアント シークレットをローテーションする](https://learn.microsoft.com/ja-jp/azure/key-vault/keys/how-to-configure-key-rotation)と、不正アクセスを取得するために侵害されたシークレットが使用されるリスクを最小限に抑えることができます。 適用する場合、提案されるキーの使用継続期間は、使用される暗号アルゴリズムの強度、または標準への準拠や規制コンプライアンス プラクティスによる影響を受けます。

**長いシークレットと強力な暗号化を使用する** - 前のポイントと密接に関連しますが、転送中 (ネットワーク上) と保存時 (ディスク上) の両方のデータに強力な暗号化アルゴリズムを使用すると、エントロピーの高いシークレットがブルート フォース攻撃を受ける可能性が確実に低くなります。 AES-128 (以上) などのアルゴリズムは保存データの保護に役立ち、RSA-2048 (以上) は転送中のデータの効率的な保護に役立ちます。 サイバーセキュリティは進化し続ける性質があるため、セキュリティの専門家に相談し、アルゴリズムの選択を定期的に見直すことが常にベスト プラクティスです。

**シークレットのハードコーディングを回避する** - クライアント シークレットをソース コードにハードコーディングしないでください。 シークレットをソース コードに含めないようにすると、不正なアクターがソース コードにアクセスする価値を最小限に抑えることができます。 また、このようなシークレットが、安全ではないリポジトリに誤ってプッシュされたり、ソースにアクセスできるがシークレットにはアクセスできないプロジェクトの共同作成者が使用できるようになったりするのを防ぐこともできます。

**リポジトリでシークレットの漏えいを監視する** - 残念ながら、ソース コードを扱うときに不正なチェックインが発生するのは事実です。 Git の事前コミット フックは、誤ったチェックインを防ぐための推奨される方法ですが、監視の代わりにもなる方法ではありません。 リポジトリの自動監視を使用すると、漏洩したシークレットを特定でき、侵害された資格情報をローテーションする計画があれば、セキュリティ インシデントの削減に役立ちます。

**不審なアクティビティを監視する** - クライアント シークレット関連の不審なアクティビティに関する [Monitor](https://learn.microsoft.com/ja-jp/azure/azure-monitor) ログと監査証跡。 可能な場合、自動化されたアラートと応答プロセスを使用して担当者に通知し、クライアント シークレットに関連する異常なアクティビティに対するコンティンジェンシーを定義します。

**クライアントの秘密を念頭に置いてアプリケーションを設計する** - セキュリティ モデルの強度は、チェーン内の最も弱いリンクと同じくらいです。 クライアント シークレット データがパブリック クライアントに移動されると、機密クライアントの偽装が可能になるおそれがあるため、[セキュリティ資格情報またはセキュリティ トークンを機密クライアントからパブリック クライアントに転送しないでください](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow#protocol-diagram)。

**信頼できるソースの最新のライブラリと SDK を使用する** - Microsoft ID プラットフォームは、アプリケーションの安全性を維持しながら生産性を向上するように設計されたさまざまなクライアント SDK、サーバー SDK、ミドルウェアを提供します。 [Microsoft.Identity.Web](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/microsoft-identity-web) を使用すると、Microsoft ID プラットフォーム上の Web アプリと API に対する認証と承認の追加が簡略化されます。 依存関係を常に最新の状態に保つと、アプリケーションとサービスがセキュリティに関する最新のイノベーションと更新の恩恵を受けることができます。

### クライアントの種類と機能の比較

パブリック クライアント アプリと機密クライアント アプリの共通点および相違点をいくつか以下に示します。

- どちらの種類のアプリもユーザー トークン キャッシュを保持し、トークンをサイレントに取得できます (トークンがキャッシュに存在する場合)。 機密クライアント アプリには、アプリ自体によって取得されたトークン用のアプリ トークン キャッシュもあります。
- どちらの種類のアプリでも、ユーザー アカウントの管理、ユーザー トークン キャッシュからのアカウントの取得、識別子からのアカウントの取得、またはアカウントの削除を行うことができます。
- MSAL では、パグリック クライアント アプリには、個別の認証フローを通じてトークンを取得するための 4 つの方法があります。 機密クライアント アプリには、トークンを取得する方法は 3 つのみで、ID プロバイダーの承認エンドポイントの URL を計算するための方法が 1 つあります。 "クライアント ID" は、アプリケーションの構築時に 1 回渡され、アプリでトークンを取得するときに再度渡す必要はありません。 詳細については、[トークンの取得](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-acquire-cache-tokens)に関するページを参照してください。

パブリック クライアントは、保護されたリソースへのユーザー委任アクセスを有効にするのに役立ちますが、独自のアプリケーション ID を証明することはできません。 一方、機密クライアントはユーザーとアプリケーションの両方の認証と承認を実行でき、シークレットがパブリック クライアントや他のサード パーティと共有されないようにセキュリティを念頭に置いてビルドする必要があります。

S2S 通信などの場合、マネージド ID などのインフラストラクチャは、サービスの開発とデプロイを簡略化し、シークレット管理に通常伴う複雑さの多くを取り除くのに大いに役立ちます。 マネージド ID を使用できない場合は、シークレットをセキュリティで保護し、シークレット関連のセキュリティ インシデントに対応するためのポリシー、予防措置、コンティンジェンシーを設定することが重要です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-compare-msal-js-and-adal-js"} -->
## JavaScript アプリケーションを ADAL.js から MSAL.js に移行する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-compare-msal-js-and-adal-js
- Service: identity-platform
- Article date: 2021-07-06
- Summary: Active Directory認証ライブラリ (ADAL) ではなく、認証と承認に Microsoft Authentication Library (MSAL) を使用するように既存の JavaScript アプリケーションを更新する方法。

[Microsoft Authentication Library for JavaScript](https://github.com/AzureAD/microsoft-authentication-library-for-js) (MSAL.js, `msal-browser`) 2.x は、Microsoft ID プラットフォーム上の JavaScript アプリケーションで使用することをお勧めする認証ライブラリです。 この記事では、ADAL.js を使用しているアプリを MSAL.js 2.x に移行するために必要な変更について説明します。

注記

MSAL.js 1.x ではなく MSAL.js 2.x を強くお勧めします。 認証コードの付与フローがより安全になり、サードパーティーの Cookie をブロックするために Safari などのブラウザーに実装されているプライバシー対策に関係なく、シングルページ アプリケーションが良好なユーザー エクスペリエンスを維持できるなどの利点があります。

### 前提条件

- アプリ登録ポータルで **Platform** / **Reply URL Type** を **シングルページ アプリケーション** に設定する必要があります ( **Web** など、アプリ登録に他のプラットフォームが追加されている場合は、リダイレクト URI が重複しないようにする必要があります。参照: [リダイレクト URI の制限](https://learn.microsoft.com/ja-jp/entra/identity-platform/reply-url))
- ES6 機能 (例えば、promises) に対して [polyfills](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-use-ie-browser) を指定し、**Internet Explorer** でアプリを実行する必要があります。MSAL.js がこれらの機能に依存しているためです。
- Microsoft Entra アプリを [v2 エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview) に移行していない場合は>

### MSAL をインストールしてインポートする

MSAL.js 2.x のライブラリをインストールするには 2 つの方法があります。

#### npm経由

```console
npm install @azure/msal-browser
```

この場合、お使いのモジュール システムに応じて、次のようにインポートします。

```javascript
import * as msal from "@azure/msal-browser"; // ESM

const msal = require('@azure/msal-browser'); // CommonJS
```

#### CDN 経由:

HTML ドキュメントのヘッダー セクションにスクリプトを読み込みます。

```html
<!DOCTYPE html>
<html>
  <head>
    <script type="text/javascript" src="https://alcdn.msauth.net/browser/2.14.2/js/msal-browser.min.js"></script>
  </head>
</html>
```

CDN を使用する場合の代替 CDN リンクとベスト プラクティスについては、「[CDN の使用状況](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/cdn-usage.md)」を参照してください。

### MSAL の初期化

ADAL.jsでは、 [AuthenticationContext](https://github.com/AzureAD/azure-activedirectory-library-for-js/wiki/Config-authentication-context#authenticationcontext) クラスをインスタンス化し、認証を実現するために使用できるメソッド (`login`、 `acquireTokenPopup` など) を公開します。 このオブジェクトは、アプリケーションから承認サーバーや ID プロバイダーへの接続を表すものです。 初期化時に必須のパラメーターは **clientId** のみです。

```javascript
window.config = {
  clientId: "YOUR_CLIENT_ID"
};

var authContext = new AuthenticationContext(config);
```

MSAL.jsでは、代わりに [PublicClientApplication](https://azuread.github.io/microsoft-authentication-library-for-js/ref/classes/_azure_msal_node.PublicClientApplication.html) クラスをインスタンス化します。 ADAL.jsと同様に、コンストラクターは少なくとも  パラメーターを含む`clientId`を受け取ります。 詳細については、「[MSAL.jsを初期化する](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/initialization.md)」を参照してください。

```javascript
const msalConfig = {
  auth: {
      clientId: 'YOUR_CLIENT_ID'
  }
};

const msalInstance = new msal.PublicClientApplication(msalConfig);
```

ADAL.js と MSAL.jsの両方で、指定しない場合、機関 URI は既定で `https://login.microsoftonline.com/common` されます。

注記

v2.0 で `https://login.microsoftonline.com/common` 機関を使用する場合、ユーザーは任意のMicrosoft Entra組織または個人の Microsoft アカウント (MSA) でサインインできるようになります。 MSAL.jsでは、ログインを任意のMicrosoft Entra アカウント (ADAL.jsと同じ動作) に制限する場合は、代わりに `https://login.microsoftonline.com/organizations` を使用します。

### MSAL を構成する

[AuthenticationContext](https://github.com/AzureAD/azure-activedirectory-library-for-js/wiki/Config-authentication-context) の初期化時に使用される [ADAL.jsの構成オプション](https://github.com/AzureAD/azure-activedirectory-library-for-js/wiki/Config-authentication-context#authenticationcontext)の一部は、MSAL.jsでは非推奨ですが、いくつかの新しいオプションが導入されています。 [使用可能なオプションの完全な一覧を](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/configuration.md)参照してください。 重要なのは、 `clientId`を除くこれらのオプションの多くは、トークンの取得中にオーバーライドできるため、 *要求ごとに* 設定できます。 たとえば、トークンの取得時に初期化時に設定した機関 URI とは異なる **機関 URI** または **リダイレクト URI を** 使用できます。

さらに、構成オプションでログイン エクスペリエンス (ポップアップ ウィンドウを使用するか、ページをリダイレクトするかなど) を指定する必要もなくなりました。 代わりに、`MSAL.js`は、`loginPopup` インスタンスを介して`loginRedirect`メソッドと`PublicClientApplication` メソッドを公開します。

### ログの有効化

ADAL.js の場合、コード内の任意の場所でログを別途構成します。

```javascript
window.config = {
  clientId: "YOUR_CLIENT_ID"
};

var authContext = new AuthenticationContext(config);

var Logging = {
  level: 3,
  log: function (message) {
      console.log(message);
  },
  piiLoggingEnabled: false
};

authContext.log(Logging)
```

MSAL.jsでは、ログは構成オプションの一部であり、 `PublicClientApplication`の初期化中に作成されます。

```javascript
const msalConfig = {
  auth: {
      // authentication related parameters
  },
  cache: {
      // cache related parameters
  },
  system: {
      loggerOptions: {
          loggerCallback(loglevel, message, containsPii) {
              console.log(message);
          },
          piiLoggingEnabled: false,
          logLevel: msal.LogLevel.Verbose,
      }
  }
}

const msalInstance = new msal.PublicClientApplication(msalConfig);
```

### MSAL API に切り替える

ADAL.js の一部のパブリック メソッドには、MSAL.js に同等のものがあります。

| ADAL | MSAL | メモ |
| --- | --- | --- |
| `acquireToken` | `acquireTokenSilent` | 名前が変更され、 [アカウント](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_common.html#accountinfo) オブジェクトが必要になりました |
| `acquireTokenPopup` | `acquireTokenPopup` | 非同期になり、Promise が返されるようになりました |
| `acquireTokenRedirect` | `acquireTokenRedirect` | 非同期になり、Promise が返されるようになりました |
| `handleWindowCallback` | `handleRedirectPromise` | リダイレクト機能を使用する場合に必要です |
| `getCachedUser` | `getAllAccounts` | 名前が変更され、アカウントの配列が返されるようになりました。 |

その他のものは非推奨になりましたが、MSAL.js に新しいメソッドが用意されています。

| ADAL | MSAL | メモ |
| --- | --- | --- |
| `login` | 該当なし | 非推奨になりました。 `loginPopup`または`loginRedirect`を使用する |
| `logOut` | 該当なし | 非推奨になりました。 `logoutPopup`または`logoutRedirect`を使用する |
| 該当なし | `loginPopup` |  |
| 該当なし | `loginRedirect` |  |
| 該当なし | `logoutPopup` |  |
| 該当なし | `logoutRedirect` |  |
| 該当なし | `getAccountByHomeId` | ホーム ID (oid + テナント ID) を使用してアカウントをフィルター処理します |
| 該当なし | `getAccountLocalId` | ローカル ID を使用してアカウントをフィルター処理します (ADFS に役立ちます) |
| 該当なし | `getAccountUsername` | ユーザー名を使用してアカウントをフィルター処理します (存在する場合) |

さらに、MSAL.js は ADAL.js とは異なり、TypeScript で実装されているため、プロジェクトで使用できるさまざまな型とインターフェイスが公開されています。 詳細については、 [MSAL.js API リファレンス](https://azuread.github.io/microsoft-authentication-library-for-js/ref/) を参照してください。

### リソースの代わりにスコープを使用する

Azure Active Directory v1.0 と 2.0 エンドポイントの重要な違いは、リソースへのアクセス方法です。 **v1.0** エンドポイントで ADAL.js を使用する場合は、まずアプリ登録ポータルにアクセス許可を登録し、次に示すようにリソース (Microsoft Graph など) のアクセス トークンを要求します。

```javascript
authContext.acquireTokenRedirect("https://graph.microsoft.com", function (error, token) {
  // do something with the access token
});
```

MSAL.js では **、v2.0** エンドポイントのみがサポートされます。 **v2.0** エンドポイントでは、*スコープ中心の*モデルを使用してリソースにアクセスします。 したがって、リソースのアクセス トークンを要求するときは、そのリソースのスコープも指定する必要があります。

```javascript
msalInstance.acquireTokenRedirect({
  scopes: ["https://graph.microsoft.com/User.Read"]
});
```

スコープ中心モデルの利点の 1 つは、動的スコープを使用 *できることです*。 v1.0 エンドポイントを使用してアプリケーションをビルドする場合、ユーザーがログイン時に同意するためにアプリケーションに必要なアクセス許可の完全なセット ( *静的スコープ*と呼ばれます) を登録する必要があります。 v2.0 では、スコープ パラメーターを使用して、必要な時点でアクセス許可を要求できます (そのため、 *動的スコープ*)。 これにより、ユーザーはスコープに **増分同意** を提供できます。 最初ユーザーにはアプリケーションへのサインインだけを行わせ、どのような種類のアクセスも必要としない場合、そうすることができます。 その後、ユーザーの予定表を読み取る機能が必要になった場合は、acquireToken メソッドで予定表のスコープを要求してユーザーの同意を得ることができます。 詳細については、「[リソースとスコープ](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/resources-and-scopes.md)」を参照してください。

### コールバックの代わりに promise を使用する

ADAL.js では、認証が成功し、応答が取得された後に、すべての操作にコールバックが使用されます。

```javascript
authContext.acquireTokenPopup(resource, extraQueryParameter, claims, function (error, token) {
  // do something with the access token
});
```

MSAL.js では、Promise が代わりに使用されます。

```javascript
msalInstance.acquireTokenPopup({
      scopes: ["User.Read"] // shorthand for https://graph.microsoft.com/User.Read
  }).then((response) => {
      // do something with the auth response
  }).catch((error) => {
      // handle errors
  });
```

ES8 に付属する **async/await** 構文を使用することもできます。

```javascript
const getAccessToken = async() => {
  try {
      const authResponse = await msalInstance.acquireTokenPopup({
          scopes: ["User.Read"]
      });
  } catch (error) {
      // handle errors
  }
}
```

### トークンのキャッシュと取得

ADAL.jsと同様に、MSAL.js は [、Web Storage API](https://developer.mozilla.org/docs/Web/API/Web_Storage_API) を使用して、トークンやその他の認証成果物をブラウザー ストレージにキャッシュします。 `sessionStorage` オプション (構成を参照) を使用することをお勧めします。ユーザーが取得したトークンを格納する方が安全ですが、`localStorage`ではタブとユーザー セッション間で[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-sso)が提供されるためです。

重要な点は、キャッシュに直接アクセスすることは想定されていないということです。 その代わり、適切な MSAL.js の API を使用して、アクセス トークンやユーザー アカウントなどの認証成果物を取得する必要があります。

### 更新トークンを使用してトークンを更新する

ADAL.js は、セキュリティ上の理由から更新トークンを返さない [OAuth 2.0 暗黙的フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow)を使用します (更新トークンの有効期間はアクセス トークンよりも長いため、悪意のあるアクターの手では危険です)。 そのため、ADAL.js の場合、ユーザーが何度も認証を求められないように、トークンの更新に非表示のフレームが使用されています。

PKCE をサポートする認証コード フローの場合、MSAL.js 2.x を使用するアプリは、ID トークンとアクセス トークンと共に更新トークンを受け取ります。更新にこれを使用することができます。 更新トークンの使用方法は抽象化されており、開発者がそれに関するロジックを構築することは想定されていません。 その代わり、更新トークンを使用したトークンの更新は、MSAL によって自動的に管理されます。 ADAL.js を使用した以前のトークン キャッシュは MSAL.js に転送できません。これは、トークン キャッシュのスキーマが変更され、ADAL.js で使用されているスキーマとは互換性がないためです。

### エラーと例外を処理する

MSAL.jsを使用する場合、発生する可能性がある最も一般的なエラーの種類は、 `interaction_in_progress` エラーです。 このエラーは、別の対話型 API が呼び出し中であるときに、対話型 API (`loginPopup`、`loginRedirect`、`acquireTokenPopup`、`acquireTokenRedirect`) を呼び出そうとするとスローされます。 `login*` API と `acquireToken*` API は*非同期*であるため、別の約束を呼び出す前に、結果として得られる約束が解決されていることを確認する必要があります。

もう 1 つの一般的なエラーは `interaction_required`です。 多くの場合、このエラーは対話型トークン取得のプロンプトを開始するだけで解決されます。 たとえば、アクセスしようとしている Web API に [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) ポリシーが設定されている場合、ユーザーは [多要素認証 (MFA)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) を実行する必要があります。 その場合、`interaction_required`または`acquireTokenPopup`をトリガーして`acquireTokenRedirect`エラーを処理すると、ユーザーに MFA の入力を求められます。これにより、ユーザーはそのエラーをフルフィルできるようになります。

さらに、発生する可能性があるもう 1 つの一般的なエラーは `consent_required`です。これは、保護されたリソースのアクセス トークンを取得するために必要なアクセス許可がユーザーによって同意されていない場合に発生します。 `interaction_required`と同様に、`consent_required` エラーの解決策は、多くの場合、`acquireTokenPopup`または`acquireTokenRedirect`を使用して、対話型のトークン取得プロンプトを開始します。

詳細については、以下を参照してください。 [一般的な MSAL.js エラーとその処理方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-error-handling-js)

### イベント API を使用する

MSAL.js (&gt;=v2.4) には、アプリで使用できるイベント API が導入されています。 これらのイベントは、認証プロセスと、その時点で MSAL によって実行されていることに関連しており、UI の更新、エラー メッセージの表示、何らかの対話が進行中かどうかの確認などに使用できます。 たとえば、次のイベント コールバックは、何らかの理由でログイン処理が失敗したときに呼び出されます。

```javascript
const callbackId = msalInstance.addEventCallback((message) => {
  // Update UI or interact with EventMessage here
  if (message.eventType === EventType.LOGIN_FAILURE) {
      if (message.error instanceof AuthError) {
          // Do something with the error
      }
    }
});
```

パフォーマンスのためには、イベント コールバックが不要になったら登録を解除することが重要です。 詳細については、「 [MSAL.js イベント API](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/events.md)」を参照してください。

### 複数のアカウントを処理する

ADAL.js には、現在認証されているエンティティを表す *ユーザー* の概念があります。 MSAL.jsは、ユーザーに複数のアカウントが関連付けられる可能性があることから、「ユーザー」を「アカウント」に置き換えています。 これは、複数のアカウントを管理し、適切なアカウントを選択する必要があることも意味します。 次のスニペットは、このプロセスを表しています。

```javascript
let homeAccountId = null; // Initialize global accountId (can also be localAccountId or username) used for account lookup later, ideally stored in app state

// This callback is passed into `acquireTokenPopup` and `acquireTokenRedirect` to handle the interactive auth response
function handleResponse(resp) {
  if (resp !== null) {
      homeAccountId = resp.account.homeAccountId; // alternatively: resp.account.homeAccountId or resp.account.username
  } else {
      const currentAccounts = myMSALObj.getAllAccounts();
      if (currentAccounts.length < 1) { // No cached accounts
          return;
      } else if (currentAccounts.length > 1) { // Multiple account scenario
          // Add account selection logic here
      } else if (currentAccounts.length === 1) {
          homeAccountId = currentAccounts[0].homeAccountId; // Single account scenario
      }
  }
}
```

詳細については、「[MSAL.jsのアカウント](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/accounts.md)」を参照してください。

### ラッパー ライブラリを使用する

Angular フレームワークと React フレームワーク用に開発している場合は、 [それぞれ MSAL Angular v2](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-angular) と [MSAL React](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-react) を使用できます。 これらのラッパーにより、MSAL.js と同じパブリック API が公開されています。さらに、フレームワーク固有のメソッドやコンポーネントが用意されているので、認証とトークンの取得プロセスを効率化することができます。

### アプリを実行する

変更が完了したら、アプリを実行し、認証シナリオをテストします。

```console
npm start
```

### 例: ADAL.js と MSAL.js の対比を使用した SPA のセキュリティ保護

次のスニペットは、Microsoft Identity プラットフォームを使用してユーザーを認証し、Microsoft Graph のアクセス トークンを取得するシングルページアプリケーションに必要な最小限のコードを示しており、まず ADAL.js を使用し、次に MSAL.js に移行する方法を示しています。

| ADAL.js の使用 | MSAL.js の使用 |
| --- | --- |
| ```html<br><br><head><br>    <meta charset="UTF-8"><br>    <meta http-equiv="X-UA-Compatible" content="IE=edge"><br>    <meta name="viewport" content="width=device-width, initial-scale=1.0"><br>    <script type="text/javascript" src="https://alcdn.msauth.net/lib/1.0.18/js/adal.min.js"></script><br></head><br><br><body><br>    <div><br>        <p id="welcomeMessage" style="visibility: hidden;"></p><br>        <button id="loginButton">Login</button><br>        <button id="logoutButton" style="visibility: hidden;">Logout</button><br>        <button id="tokenButton" style="visibility: hidden;">Get Token</button><br>    </div><br>    <script><br>        // DOM elements to work with<br>        var welcomeMessage = document.getElementById("welcomeMessage");<br>        var loginButton = document.getElementById("loginButton");<br>        var logoutButton = document.getElementById("logoutButton");<br>        var tokenButton = document.getElementById("tokenButton");<br><br>        // if user is logged in, update the UI<br>        function updateUI(user) {<br>            if (!user) {<br>                return;<br>            }<br><br>            welcomeMessage.innerHTML = 'Hello ' + user.profile.upn + '!';<br>            welcomeMessage.style.visibility = "visible";<br>            logoutButton.style.visibility = "visible";<br>            tokenButton.style.visibility = "visible";<br>            loginButton.style.visibility = "hidden";<br>        };<br><br>        // attach logger configuration to window<br>        window.Logging = {<br>            piiLoggingEnabled: false,<br>            level: 3,<br>            log: function (message) {<br>                console.log(message);<br>            }<br>        };<br><br>        // ADAL configuration<br>        var adalConfig = {<br>            instance: 'https://login.microsoftonline.com/',<br>            clientId: "ENTER_CLIENT_ID_HERE",<br>            tenant: "ENTER_TENANT_ID_HERE",<br>            redirectUri: "ENTER_REDIRECT_URI_HERE",<br>            cacheLocation: "sessionStorage",<br>            popUp: true,<br>            callback: function (errorDesc, token, error, tokenType) {<br>                if (error) {<br>                    console.log(error, errorDesc);<br>                } else {<br>                    updateUI(authContext.getCachedUser());<br>                }<br>            }<br>        };<br><br>        // instantiate ADAL client object<br>        var authContext = new AuthenticationContext(adalConfig);<br><br>        // handle redirect response or check for cached user<br>        if (authContext.isCallback(window.location.hash)) {<br>            authContext.handleWindowCallback();<br>        } else {<br>            updateUI(authContext.getCachedUser());<br>        }<br><br>        // attach event handlers to button clicks<br>        loginButton.addEventListener('click', function () {<br>            authContext.login();<br>        });<br><br>        logoutButton.addEventListener('click', function () {<br>            authContext.logOut();<br>        });<br><br>        tokenButton.addEventListener('click', () => {<br>            authContext.acquireToken(<br>                "https://graph.microsoft.com",<br>                function (errorDesc, token, error) {<br>                    if (error) {<br>                        console.log(error, errorDesc);<br><br>                        authContext.acquireTokenPopup(<br>                            "https://graph.microsoft.com",<br>                            null, // extraQueryParameters<br>                            null, // claims<br>                            function (errorDesc, token, error) {<br>                                if (error) {<br>                                    console.log(error, errorDesc);<br>                                } else {<br>                                    console.log(token);<br>                                }<br>                            }<br>                        );<br>                    } else {<br>                        console.log(token);<br>                    }<br>                }<br>            );<br>        });<br>    </script><br></body><br><br></html><br><br>``` | ```html<br><br><head><br>    <meta charset="UTF-8"><br>    <meta http-equiv="X-UA-Compatible" content="IE=edge"><br>    <meta name="viewport" content="width=device-width, initial-scale=1.0"><br>    <script type="text/javascript" src="https://alcdn.msauth.net/browser/2.34.0/js/msal-browser.min.js"></script><br></head><br><br><body><br>    <div><br>        <p id="welcomeMessage" style="visibility: hidden;"></p><br>        <button id="loginButton">Login</button><br>        <button id="logoutButton" style="visibility: hidden;">Logout</button><br>        <button id="tokenButton" style="visibility: hidden;">Get Token</button><br>    </div><br>    <script><br>        // DOM elements to work with<br>        const welcomeMessage = document.getElementById("welcomeMessage");<br>        const loginButton = document.getElementById("loginButton");<br>        const logoutButton = document.getElementById("logoutButton");<br>        const tokenButton = document.getElementById("tokenButton");<br><br>        // if user is logged in, update the UI<br>        const updateUI = (account) => {<br>            if (!account) {<br>                return;<br>            }<br><br>            welcomeMessage.innerHTML = `Hello ${account.username}!`;<br>            welcomeMessage.style.visibility = "visible";<br>            logoutButton.style.visibility = "visible";<br>            tokenButton.style.visibility = "visible";<br>            loginButton.style.visibility = "hidden";<br>        };<br><br>        // MSAL configuration<br>        const msalConfig = {<br>            auth: {<br>                clientId: "ENTER_CLIENT_ID_HERE",<br>                authority: "https://login.microsoftonline.com/ENTER_TENANT_ID_HERE",<br>                redirectUri: "ENTER_REDIRECT_URI_HERE",<br>            },<br>            cache: {<br>                cacheLocation: "sessionStorage"<br>            },<br>            system: {<br>                loggerOptions: {<br>                    loggerCallback(loglevel, message, containsPii) {<br>                        console.log(message);<br>                    },<br>                    piiLoggingEnabled: false,<br>                    logLevel: msal.LogLevel.Verbose,<br>                }<br>            }<br>        };<br><br>        // instantiate MSAL client object<br>        const pca = new msal.PublicClientApplication(msalConfig);<br><br>        // handle redirect response or check for cached user<br>        pca.handleRedirectPromise().then((response) => {<br>            if (response) {<br>                pca.setActiveAccount(response.account);<br>                updateUI(response.account);<br>            } else {<br>                const account = pca.getAllAccounts()[0];<br>                updateUI(account);<br>            }<br>        }).catch((error) => {<br>            console.log(error);<br>        });<br><br>        // attach event handlers to button clicks<br>        loginButton.addEventListener('click', () => {<br>            pca.loginPopup().then((response) => {<br>                pca.setActiveAccount(response.account);<br>                updateUI(response.account);<br>            })<br>        });<br><br>        logoutButton.addEventListener('click', () => {<br>            pca.logoutPopup().then((response) => {<br>                window.location.reload();<br>            });<br>        });<br><br>        tokenButton.addEventListener('click', () => {<br>            const account = pca.getActiveAccount();<br><br>            pca.acquireTokenSilent({<br>                account: account,<br>                scopes: ["User.Read"]<br>            }).then((response) => {<br>                console.log(response);<br>            }).catch((error) => {<br>                if (error instanceof msal.InteractionRequiredAuthError) {<br>                    pca.acquireTokenPopup({<br>                        scopes: ["User.Read"]<br>                    }).then((response) => {<br>                        console.log(response);<br>                    });<br>                }<br><br>                console.log(error);<br>            });<br>        });<br>    </script><br></body><br><br></html><br><br>``` |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-error-handling-js"} -->
## MSAL.js におけるエラーと例外の処理 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-error-handling-js
- Service: identity-platform
- Article date: 2023-12-19
- Summary: MSAL.js アプリケーションで、エラーと例外、条件付きアクセス、クレーム チャレンジ、再試行を処理する方法について説明します。

この記事では、さまざまな種類のエラーの概要と、一般的なサインイン エラーを処理するための推奨事項を提供します。

### MSAL のエラー処理の基本

Microsoft Authentication Library (MSAL) での例外は、エンド ユーザーに表示するためのものではなく、アプリ開発者がトラブルシューティングに使うことが意図されています。 例外のメッセージはローカライズされていません。

例外やエラーを処理するときは、例外の種類自体とエラー コードを使って、例外を区別できます。 エラー コードの一覧については、「[Microsoft Entra 認証と認可のエラー コード](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes)」をご覧ください。

サインイン中に、同意、条件付きアクセス (MFA、デバイス管理、場所に基づく制限)、トークンの発行と引き換え、ユーザー プロパティに関するエラーが発生することがあります。

次のセクションでは、アプリのエラー処理の詳細について詳しく説明します。

### MSAL.js でのエラー処理

MSAL.js には、さまざまな種類の一般的エラーを抽象化して分類するエラー オブジェクトが用意されています。 エラーを適切に処理するために、エラー メッセージなど、エラーの特定の詳細にアクセスするためのインターフェイスも提供されています。

#### エラー オブジェクト

```javascript
export class AuthError extends Error {
    // This is a short code describing the error
    errorCode: string;
    // This is a descriptive string of the error,
    // and may also contain the mitigation strategy
    errorMessage: string;
    // Name of the error class
    this.name = "AuthError";
}
```

エラー クラスを拡張することで、次のプロパティにアクセスできます。

- `AuthError.message`: `errorMessage` と同一です。
- `AuthError.stack`:スローされたエラーのスタック トレースです。

#### エラーの種類

次のエラーの種類を使用できます。

- `AuthError`:MSAL.js ライブラリの基本エラー クラス、予期しないエラーにも使用されます。
- `ClientAuthError`: クライアント認証の問題を示すエラー クラス。 ライブラリから発生するほとんどのエラーは ClientAuthError です。 これらのエラーは、ログインが既に進行中のときにログイン メソッドを呼び出した場合、ユーザーがログインをキャンセルした場合などに発生します。
- `ClientConfigurationError`: `ClientAuthError` を拡張するエラー クラス。 これは、指定されたユーザー設定パラメーターの形式が不正であるか欠落している場合、リクエストが行われる前にスローされます。
- `ServerError`:認証サーバーによって送信されるエラー文字列を表すエラー クラス。 これらは、無効な要求形式やパラメーターなどのエラー、またはサーバーがユーザーを認証または承認できない他のエラーです。
- `InteractionRequiredAuthError`:対話型の呼び出しを必要とするサーバー エラーを表す、`ServerError` を拡張するエラー クラス。 このエラーは、認証/承認に対する資格情報または同意を提供するためにユーザーがサーバーと対話する必要がある場合に、`acquireTokenSilent` によってスローされます。 エラーコードには、`"interaction_required"`、`"login_required"`、`"consent_required"` が含まれます。

リダイレクト メソッド (`loginRedirect`、`acquireTokenRedirect`) での認証フローにおけるエラー処理の場合、次のように、`handleRedirectPromise()` メソッドを使用するリダイレクトの後に成功または失敗で呼び出されるリダイレクト Promise を処理する必要があります。

```javascript
const msal = require('@azure/msal-browser');
const myMSALObj = new msal.PublicClientApplication(msalConfig);

// Register Callbacks for redirect flow
myMSALObj.handleRedirectPromise()
    .then(function (response) {
        //success response
    })
    .catch((error) => {
        console.log(error);
    })
myMSALObj.acquireTokenRedirect(request);
```

ポップアップ エクスペリエンスのメソッド (`loginPopup`、`acquireTokenPopup`) から Promise が返されるので、Promise パターン (`.then` と `.catch`) を使用して、次のように処理できます。

```javascript
myMSALObj.acquireTokenPopup(request).then(
    function (response) {
        // success response
    }).catch(function (error) {
        console.log(error);
    });
```

#### 対話を必要とするエラー

`acquireTokenSilent` などのトークンを取得する非対話型メソッドを使用しようとしましたが、MSAL ではそれをサイレントで行うことができないとき、エラーが返されます。

次のような原因が考えられます。

- サインインする必要がある
- 同意する必要がある
- 多要素認証エクスペリエンスを経由する必要がある。

修復するには、`acquireTokenPopup` や `acquireTokenRedirect` などの対話型メソッドを呼び出します。

```javascript
// Request for Access Token
myMSALObj.acquireTokenSilent(request).then(function (response) {
    // call API
}).catch( function (error) {
    // call acquireTokenPopup in case of acquireTokenSilent failure
    // due to interaction required
    if (error instanceof InteractionRequiredAuthError) {
        myMSALObj.acquireTokenPopup(request).then(
            function (response) {
                // call API
            }).catch(function (error) {
                console.log(error);
            });
    }
});
```

### 条件付きアクセスとクレーム チャレンジ

トークンをサイレントで取得するとき、アクセスしようとしている API で MFA ポリシーなどの[条件付きアクセス クレーム チャレンジ](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-conditional-access-dev-guide)が必要な場合、ご利用のアプリケーションはエラーを受け取ることがあります。

このエラーを処理するパターンは、MSAL を使用して対話形式でトークンを取得することです。 これによりユーザーにメッセージが表示され、ユーザーは必要な条件付きアクセス ポリシーを満たす機会を与えられます。

条件付きアクセスを必要とする API を呼び出す特定のケースでは、API からのエラーでクレーム チャレンジを受け取ることがあります。 たとえば、条件付きアクセス ポリシーがマネージド デバイス (Intune) を使用するものである場合、エラーは [AADSTS53000: Your device is required to be managed to access this resource](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes) (このリソースにアクセスするには、デバイスがマネージドである必要があります) のようなものになります。 この場合、トークン取得呼び出しでクレームを渡して、適切なポリシーを満たすようユーザーに求めることができます。

MSAL.js を使用してトークンをサイレントに (`acquireTokenSilent` を使用して) 取得する場合、アクセスしようとしている API に MFA ポリシーなどの[条件付きアクセス クレーム チャレンジ](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-conditional-access-dev-guide)が必要な場合、アプリケーションでエラーが発生する可能性があります。

このエラーを処理するパターンは、次の例のように、`acquireTokenPopup` や `acquireTokenRedirect` など、MSAL.js でトークンを取得する対話型の呼び出しを行うことです。

```javascript
myMSALObj.acquireTokenSilent(accessTokenRequest).then(function(accessTokenResponse) {
    // call API
}).catch(function(error) {
    if (error instanceof InteractionRequiredAuthError) {
    
        // extract, if exists, claims from the error object
        if (error.claims) {
            accessTokenRequest.claims = error.claims,
        
        // call acquireTokenPopup in case of InteractionRequiredAuthError failure
        myMSALObj.acquireTokenPopup(accessTokenRequest).then(function(accessTokenResponse) {
            // call API
        }).catch(function(error) {
            console.log(error);
        });
    }
});
```

対話形式でトークンを取得すると、ユーザーにメッセージが表示され、ユーザーは必要な条件付きアクセス ポリシーを満たす機会を与えられます。

条件付きアクセスを必要とする API を呼び出すと、API からのエラーでクレーム チャレンジを受け取ることがあります。 この場合、エラーで返されたクレームを`claims`の  パラメーターに渡すと、該当するポリシーを満たすことができます。

詳細については、「[継続的アクセス評価が有効になった API をアプリケーションで使用する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-resilience-continuous-access-evaluation)」をご覧ください。

#### 他のフレームワークの使用

登録されたシングル ページ アプリケーション (SPA) に Tauri などのツールキットを ID プラットフォームと共に使用した場合、運用アプリでは認識されません。 SPA は、本番アプリでは `https` で始まる URL、ローカル開発では `http://localhost` で始まる URL のみをサポートします。 `tauri://localhost` のようなプレフィックスは、ブラウザー アプリには使用できません。 この形式は、ブラウザー アプリとは異なり機密コンポーネントがあるため、モバイル アプリまたは Web アプリのみでサポートできます。

### エラーおよび例外の後の再試行

MSAL を呼び出すときには、独自の再試行ポリシーを実装することが求められます。 MSAL では Microsoft Entra サービスに対して HTTP 呼び出しが行われ、場合によってはエラーが発生することがあります。 たとえば、ネットワークがダウンしたり、サーバーが過負荷になったりする可能性があります。

#### HTTP 429

サービス トークン サーバー (STS) が過剰な要求で過負荷になると、HTTP エラー 429 が返され、`Retry-After` 応答フィールドで再試行できるまでの期間に関するヒントが示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-js-avoid-page-reloads"} -->
## ページのリロードを回避する (MSAL.js) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-avoid-page-reloads
- Service: identity-platform
- Article date: 2019-05-29
- Summary: JavaScript (MSAL.js) 用 Microsoft 認証ライブラリを使用してトークンを自動的に取得、更新するときにページのリロードを回避する方法を説明します。

JavaScript (MSAL.js) 用 Microsoft Authentication Library では非表示の `iframe` 要素を使用して、バックグラウンドでトークンが自動的に取得、更新されます。 Microsoft Entra ID によって、トークン要求で指定された登録済み `redirect_uri` にトークンが戻されます (既定では、これはアプリのルート ページです)。 応答は 302 なので、結果は `redirect_uri` にロードされる `iframe` に対応する HTML になります。 通常、アプリの `redirect_uri` はルート ページで、これにより、リロードされます。

他のケースでは、アプリのルートページへの移動に認証が必要な場合、入れ子状の`iframe`要素や`X-Frame-Options: deny`エラーが発生することがあります。

MSAL.js は Microsoft Entra ID によって発行された 302 を無視できず、返されたトークンを処理する必要があるため、 `redirect_uri` が `iframe`に読み込まれないようにすることはできません。

アプリ全体のリロードやこれにより発生するその他のエラーを回避するには、次の回避策に従ってください。

### iframe に別の HTML を指定する

config の `redirect_uri` プロパティを、認証を必要としない単純なページに設定します。 Microsoft Entra 管理センターに登録されている `redirect_uri` と一致していることを確認する必要があります。 ユーザーがログイン プロセスを開始し、ログインが完了した後に正確な場所にリダイレクトされるときに、MSAL によってスタート ページが保存されるため、これはユーザーのログイン エクスペリエンスには影響しません。

### メインのアプリケーションファイルで初期化を行う

アプリの初期化、ルーティングなどを定義する中央の単一 JavaScript ファイルが存在するようにアプリが構成されている場合、アプリが `iframe` に読み込まれるかどうかに基づいてアプリ モジュールを条件付きで読み込むことができます。 例えば次が挙げられます。

AngularJS: app.js の場合

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

Angular で: app.module.ts

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

MsalComponent です。

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

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-js-initializing-client-applications"} -->
## MSAL.js クライアント アプリを初期化する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-initializing-client-applications
- Service: identity-platform
- Article date: 2025-05-12
- Summary: JavaScript 用 Microsoft Authentication Library (MSAL.js) を使用することによるクライアント アプリケーションの初期化について説明します。

この記事では、ユーザー エージェント アプリケーションのインスタンスを使用して JavaScript 用 Microsoft Authentication Library (MSAL.js) を初期化する方法について説明します。

ユーザー エージェント アプリケーションは、Web ブラウザーなどのユーザー エージェントでクライアント コードが実行されるパブリック クライアント アプリケーションの一種です。 ブラウザー コンテキストがオープンにアクセスできるため、このようなクライアントでは、シークレットは保存されません。

クライアント アプリケーションの種類とアプリケーションの構成オプションの詳細については、[MSAL のパブリック クライアント アプリケーションと機密クライアント アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-applications)に関するページを参照してください。

### 前提条件

アプリケーションを初期化する前に、まず、その[アプリケーションを Microsoft Entra 管理センターに登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-spa-app-configuration)して、アプリケーションと Microsoft ID プラットフォーム間で信頼関係を確立する必要があります。

アプリの登録後、Microsoft Entra 管理センターで確認できる次の値の一部またはすべてが必要になります。

| 価値 | 必須 | 説明 |
| --- | --- | --- |
| アプリケーション (クライアント) ID | 必須 | Microsoft ID プラットフォーム内でアプリケーションを一意に識別する GUID。 |
| 権威 | 任意 | ID プロバイダーの URL (*インスタンス*) とアプリケーションの*サインイン対象ユーザー*。 インスタンスとサインイン対象ユーザーが連結されると、*Authority* が構成されます。 |
| ディレクトリ (テナント) ID | 任意 | 組織専用の基幹業務アプリケーション (*シングルテナント アプリケーション*とも呼ばれる) をビルドしている場合は、ディレクトリ(テナント)IDを指定します。 |
| リダイレクト URI | 任意 | Web アプリを構築している場合、`redirectUri` では、ID プロバイダー (Microsoft ID プラットフォーム) が発行済みのセキュリティ トークンを返す場所を指定します。 |

### MSAL.js 2.x アプリの初期化

[Configuration][msal-js-configuration] オブジェクトを使用して [PublicClientApplication][msal-js-publicclientapplication] をインスタンス化して、MSAL.js 認証コンテキストを初期化します。 最低限必要な構成プロパティは、アプリケーションの `clientID` です。これは、Microsoft Entra 管理センターのアプリ登録の **[概要]** ページに**アプリケーション (クライアント) ID** として表示されます。

次に、構成オブジェクトと `PublicClientApplication` のインスタンス化の例を示します。

```javascript
const msalConfig = {
  auth: {
    clientId: "Enter_the_Application_Id_Here",
    authority: "https://login.microsoftonline.com/Enter_the_Tenant_Info_Here",
    knownAuthorities: [],
    redirectUri: "https://localhost:{port}/redirect",
    postLogoutRedirectUri: "https://localhost:{port}/redirect",
    navigateToLoginRequestUrl: true,
  },
  cache: {
    cacheLocation: "sessionStorage",
    storeAuthStateInCookie: false,
  },
  system: {
    loggerOptions: {
      loggerCallback: (
        level: LogLevel,
        message: string,
        containsPii: boolean
      ): void => {
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
        }
      },
      piiLoggingEnabled: false,
    },
    windowHashTimeout: 60000,
    iframeHashTimeout: 6000,
    loadFrameTimeout: 0,
  },
};

// Create an instance of PublicClientApplication
const msalInstance = new PublicClientApplication(msalConfig);

// Handle the redirect flows
msalInstance
  .handleRedirectPromise()
  .then((tokenResponse) => {
    // Handle redirect response
  })
  .catch((error) => {
    // Handle redirect error
  });
```

#### `handleRedirectPromise`

アプリケーションでリダイレクト フローが使用されている場合は、[handleRedirectPromise][msal-js-handleredirectpromise] を呼び出します。 リダイレクト フローを使用する場合は、すべてのページ読み込みで `handleRedirectPromise` を実行する必要があります。

Promise から次の 3 つの結果が得られます。

- `.then` が呼び出され、`tokenResponse` が truthy である: アプリケーションは成功したリダイレクト操作から戻っています。
- `.then` が呼び出され、`tokenResponse` が 偽物 (`null`) である: リダイレクト操作では、アプリケーションに戻りません。
- `.catch` が呼び出される: リダイレクト操作によりアプリケーションに戻りますが、エラーが発生しています。

### MSAL.js 1.x アプリの初期化

構成オブジェクトを使用して UserAgentApplication をインスタンス化することで、MSAL 1.x 認証コンテキストを初期化します。 最低限必要な構成プロパティは、お使いのアプリケーションの `clientID` です。これは、Microsoft Entra 管理センターのアプリ登録の **[概要]** ページに**アプリケーション (クライアント) ID** として表示されます。

MSAL.js 1.2.x 以前のリダイレクト フロー (loginRedirect と acquireTokenRedirect) を使用した認証メソッドの場合、`handleRedirectCallback()` メソッドを介して成功またはエラーに対するコールバックを明示的に登録する必要があります。 MSAL.js 1.2. x以前ではコールバックを明示的にレジスタする必要がある。これは、リダイレクトフローが、ポップアップエクスペリエンスを持つメソッドと同様に promise を返さないためです。 バージョン 1.3.x 以降の MSAL.js では、コールバックの登録は*省略可能*です。

```javascript
// Configuration object constructed
const msalConfig = {
  auth: {
    clientId: "Enter_the_Application_Id_Here",
  },
};

// Create UserAgentApplication instance
const msalInstance = new UserAgentApplication(msalConfig);

function authCallback(error, response) {
  // Handle redirect response
}

// Register a redirect callback for Success or Error (when using redirect methods)
// **REQUIRED** in MSAL.js 1.2.x and earlier
// **OPTIONAL** in MSAL.js 1.3.x and later
msalInstance.handleRedirectCallback(authCallback);
```

### 単一インスタンスと構成

MSAL.js 1.x および 2.x では、`UserAgentApplication` または`PublicClientApplication` の 1 つのインスタンスと構成によって、それぞれが 1 つの認証コンテキストを表すように設計されています。

`UserAgentApplication` または `PublicClientApplication` の複数のインスタンスは、ブラウザーでキャッシュ エントリと動作の競合を引き起こす可能性があるため、お勧めしません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-js-known-issues-ie-edge-browsers"} -->
## Internet Explorer および Microsoft Edge での問題 (MSAL.js) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-known-issues-ie-edge-browsers
- Service: identity-platform
- Article date: 2020-05-18
- Summary: JavaScript 用 Microsoft Authentication Library (MSAL.js) を Internet Explorer および Microsoft Edge ブラウザーで使用するときの既知の問題について説明します。

### セキュリティ ゾーンに起因する問題

IE と Microsoft Edge での認証に関する問題が複数報告されています (*Microsoft Edge ブラウザー バージョン 40.15063.0.0* へのアップデート以来)。 Microsoft はこれらの問題を追跡中で、Microsoft Edge チームに通知済みです。 Microsoft Edge については解決に向けて取り組んでいますが、以下では、頻繁に発生する問題と、考えられる実装可能な回避策について説明します。

#### 原因

これらのほとんどの問題の原因は次のとおりです。 Microsoft Edge ブラウザーでは、セッション ストレージとローカル ストレージはセキュリティ ゾーンによって分割されています。 この特定のバージョンの Microsoft Edge では、アプリケーションがゾーンをまたいでリダイレクトされたときに、セッション ストレージとローカル ストレージがクリアされます。 具体的には、セッション ストレージは通常のブラウザー ナビゲーションでクリアされ、セッション ストレージとローカル ストレージの両方がブラウザーの InPrivate モードでクリアされます。 MSAL.js は特定の状態をセッション ストレージに保存し、認証フロー中にこの状態のチェックに依存します。 セッション ストレージがクリアされると、この状態が失われるためエクスペリエンスが途切れることになります。

#### 問題

- **認証時に無限のリダイレクト ループとページの再読み込みが発生する**。 ユーザーが Microsoft Edge でアプリケーションにサインインすると、Microsoft Entra のログイン ページからリダイレクトされて戻され、無限のリダイレクト ループに陥って、ページの再読み込みが繰り返し発生します。 これは通常、セッション ストレージの `invalid_state` エラーを伴います。
- **トークンの取得の無限ループと AADSTS50058 エラー**。 Microsoft Edge で実行されているアプリケーションがリソースのトークンを取得しようとすると、アプリケーションはトークン取得呼び出しの無限ループに陥ります。 ネットワーク トレースで、Microsoft Entra ID から次のエラーが返されます。

    `Error :login_required; Error description:AADSTS50058: A silent sign-in request was sent but no user is signed in. The cookies used to represent the user's session were not sent in the request to Azure AD. This can happen if the user is using Internet Explorer or Edge, and the web app sending the silent sign-in request is in different IE security zone than the Azure AD endpoint (login.microsoftonline.com)`
- **ポップアップ ウィンドウ経由のログインを使用して認証するとき、ポップアップ ウィンドウが閉じない、または動作が停止する**。 Microsoft Edge または Internet Explorer (InPrivate) でポップアップ ウィンドウを使用して認証するとき、資格情報を入力してサインインした後、セキュリティ ゾーンをまたぐ複数のドメインがナビゲーションに関係している場合、`MSAL.js` がポップアップ ウィンドウのハンドルを失うためポップアップ ウィンドウが閉じなくなります。
- **プレフィックス tauri が付いたリダイレクト URL を使用するとログインできない**。 リダイレクト URI でサポートされているスキームは、運用アプリの場合は `https:`、ローカル開発の場合は `http://localhost` のみです。 モバイルまたはデスクトップ アプリケーションに `tauri://localhost` などの別のスキームを使用しようとすると、次のエラー メッセージが表示されます。 このエラーは、SPA のバックエンドの設計方法の結果として発生します。

    `AADSTS90023: Cross-origin token redemption is permitted only for the 'Single-Page Application' client-type or 'Native' client-type with origin registered in AllowedOriginForNativeAppCorsRequestInOAuthToken allow list.`

#### 更新: MSAL.js 0.2.3 に修正が利用可能

認証のリダイレクト ループの問題に対する修正プログラムが [MSAL.js 0.2.3](https://github.com/AzureAD/microsoft-authentication-library-for-js/releases) でリリースされました。 この修正プログラムを利用するには、MSAL.js 構成ファイルのフラグ `storeAuthStateInCookie` を有効にします。 既定では、このフラグは false に設定されています。

`storeAuthStateInCookie` フラグが有効になると、MSAL.js では、ブラウザーの Cookie を使用して、認証フローを検証するために必要な要求の状態が格納されます。

注

`msal-angular` と `msal-angularjs` のラッパーについては、この修正プログラムはまだ利用できません。 この修正プログラムは、ポップアップ ウィンドウの問題には対処していません。

##### その他の回避策

これらの回避策を採用する前に、この問題が Microsoft Edge ブラウザーの特定のバージョンでのみ発生していて、その他のブラウザーは動作することをテストしてください。

1. これらの問題を回避する最初の手順として、アプリケーション ドメインと、認証フローのリダイレクトに関与するその他のすべてのサイトが、ブラウザーのセキュリティ設定の信頼済みサイトとして追加されてることを確認します。 これにより、リダイレクトが同じセキュリティ ゾーンに属していることが保証されます。 これを行うには、次のステップに従います。

    - **Internet Explorer** を開き、右上隅にある **[設定]** (歯車アイコン) をクリックします。
    - **[インターネット オプション]** を選択します
    - **[セキュリティ]** タブを選択します
    - **[信頼済みサイト]** オプションで、 **[サイト]** ボタンをクリックし、表示されたダイアログ ボックスに URL を追加します。
2. 前述した通り、通常のナビゲーション中にセッション ストレージのみがクリアされるため、代わりにローカル ストレージを使用するように MSAL.js を構成できます。 これは MSAL の初期化中に `cacheLocation` 構成パラメーターとして設定できます。

この回避策ではセッションとローカルの両方のストレージがクリアされるため、InPrivate での参照の問題は解決されないことに注意してください。

### ポップアップ ブロック機能に起因する問題

たとえば、[多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)中に 2 つ目のポップアップが開いたときに、IE や Microsoft Edge でポップアップがブロックされることがあります。 ポップアップ ウィンドウを許可するのを 1 回のみにするか、常時にするかについてブラウザーでアラートが表示されます。 許可することを選択した場合、ブラウザーにより自動的にポップアップ ウィンドウが開かれ、`null` ハンドルが返されます。 その結果、ライブラリにウィンドウのハンドルがないため、ポップアップ ウィンドウを閉じる方法がなくなります。 Chrome では、ポップアップ ウィンドウが自動的に開かれないため、ポップアップ ウィンドウを許可するプロンプトを表示するときに同じ問題は発生しません。

**回避策**として、開発者は、アプリの使用を開始する前に IE と Microsoft Edge でポップアップを許可して、この問題を回避する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-js-pass-custom-state-authentication-request"} -->
## 認証要求でカスタム状態を渡す (MSAL.js) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-pass-custom-state-authentication-request
- Service: identity-platform
- Article date: 2026-01-09
- Summary: Microsoft Authentication Library for JavaScript (MSAL.js) を使用して、認証要求でカスタム状態パラメーター値を渡す方法について説明します。

OAuth 2.0 で定義されている *状態* パラメーターは認証要求に含まれており、クロスサイト要求フォージェリ攻撃を防ぐためにトークン応答にも返されます。 既定では、JavaScript 用 Microsoft Authentication Library (MSAL.js) は、ランダムに生成された一意の *状態* 認証要求のパラメーター値を渡します。

状態パラメーターを使用して、リダイレクト前にアプリの状態の情報をエンコードすることもできます。 このパラメーターへの入力として、アプリ内のユーザーの状態 (オンだったページやビューなど) を渡すことができます。 MSAL.js ライブラリを使用すると、[Request](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_browser.html#redirectrequest) オブジェクトの状態パラメーターとしてカスタム状態を渡すことができます。 例えば：

セキュリティとプライバシーのために、状態パラメーターに URL やその他の機密データを直接配置しないでください。 代わりに、localStorage や sessionStorage などのブラウザー ストレージに格納されているデータに対応するキーまたは識別子を使用します。 この方法により、アプリは認証後に必要なデータを安全に参照できます。

```javascript
import {PublicClientApplication} from "@azure/msal-browser";

const myMsalObj = new PublicClientApplication({
    clientId: "ENTER_CLIENT_ID_HERE"
});

let loginRequest = {
    scopes: ["user.read"],
    state: "page_url"
}

myMSALObj.loginRedirect(loginRequest);
```

渡された状態は、要求の送信時に MSAL.js によって設定された一意の GUID に追加されます。 応答が返されると、MSAL.js 状態の一致を確認し、[Response](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_common.html#authenticationresult) オブジェクトで渡されたカスタムを `state`として返します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-js-prompt-behavior"} -->
## MSAL.js でのプロンプトの動作 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-prompt-behavior
- Service: identity-platform
- Article date: 2019-04-24
- Summary: JavaScript 用 Microsoft Authentication Library (MSAL.js) を使用して、プロンプトの動作をカスタマイズする方法について説明します。

MSAL.js では、ログインまたはトークン要求のメソッドの一部としてプロンプト値を渡すことができます。 アプリケーションのシナリオに基づいて、**要求オブジェクト**の [prompt](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_common.html#commonauthorizationurlrequest) パラメーターを設定することで、要求に対する Microsoft Entra プロンプトの動作をカスタマイズできます:

```javascript
import { PublicClientApplication } from "@azure/msal-browser";

const pca = new PublicClientApplication({
    auth: {
        clientId: "YOUR_CLIENT_ID"
    }
});

const loginRequest = {
    scopes: ["user.read"],
    prompt: 'select_account',
}

pca.loginPopup(loginRequest)
    .then(response => {
        // do something with the response
    })
    .catch(error => {
        // handle errors
    });
```

### サポートされているプロンプト値

Microsoft ID プラットフォームで認証を行うときは、次のプロンプト値を使用できます。

| パラメーター | 行動 |
| --- | --- |
| `login` | その要求でユーザーに資格情報の入力を強制させ、シングル サインオンを無効にします。 |
| `none` | ユーザーに対話形式のプロンプトが表示されないようにします。 シングル サインオンを使ってサイレントに要求を完了できない場合は、Microsoft ID プラットフォームから *login\_required* または *interaction\_required* エラーが返されます。 |
| `consent` | ユーザーがサインインした後で OAuth 同意ダイアログをトリガーし、アプリへのアクセス許可の付与をユーザーに求めます。 |
| `select_account` | セッション内の全アカウントを一覧表示するアカウント選択エクスペリエンス、またはまったく別のアカウントを選択するためのオプションを提供することで、シングル サインオンを中断します。 |
| `create` | 外部ユーザーがアカウントを作成できるようにするサインアップ ダイアログをトリガーします。 詳しくは、「[セルフサービス サインアップ](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-sign-up-overview)」をご覧ください |

サポートされていないプロンプト値の場合、MSAL.js は `invalid_prompt` エラーをスローします。

```console
invalid_prompt_value: Supported prompt values are 'login', 'select_account', 'consent', 'create' and 'none'. Please see here for valid configuration options: https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_common.html#commonauthorizationurlrequest Given value: my_custom_prompt
```

### 既定のプロンプト値

MSAL.js が使う既定のプロンプト値を次に示します。

| MSAL.js のメソッド | 既定のプロンプト | 許可されるプロンプト |
| --- | --- | --- |
| `loginPopup` | なし | [任意] |
| `loginRedirect` | なし | [任意] |
| `ssoSilent` | `none` | 該当なし (無視) |
| `acquireTokenPopup` | なし | [任意] |
| `acquireTokenRedirect` | なし | [任意] |
| `acquireTokenSilent` | `none` | 該当なし (無視) |

注

**prompt** はプロトコル レベルのパラメーターであり、必要な認証動作を ID プロバイダーに通知することに注意してください。 MSAL.js の動作には影響を与えず、MSAL.js はサービスが最終的に要求を処理する方法を制御できません。 ほとんどの場合、Microsoft Entra は要求を尊重しようとします。 これが不可能な場合は、エラー応答を返すか、指定されたプロンプト値を完全に無視する可能性があります。

### prompt=none を使用した対話型要求

通常、サイレントで要求を行う必要がある場合は、サイレントの MSAL.js メソッド (`ssoSilent`、`acquireTokenSilent`) を使い、*login\_required* または *interaction\_required* エラーは対話型メソッド (`loginPopup`、`loginRedirect`、`acquireTokenPopup`、`acquireTokenRedirect`) で処理します。

ただし、プロンプト値 `none` を対話型の MSAL.js メソッドと共に使って、サイレント認証を実現できる場合もあります。 たとえば、一部のブラウザーでは、サードパーティの Cookie の制限のため、Microsoft Entra ID とのアクティブなユーザー セッションがあっても、`ssoSilent` 要求は失敗します。 これを解決するには、プロンプト値 `none` を `loginPopup` などの対話型要求に渡すことができます。 その後、MSAL.js は Microsoft Entra ID に対してポップアップ ウィンドウを開き、Microsoft Entra ID は既存のセッション Cookie を使うことでプロンプト値を尊重します。 この場合、ユーザーには簡単なポップアップ ウィンドウが表示されますが、資格情報の入力を求めるメッセージは表示されません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-js-sso"} -->
## シングル サインオン (MSAL.js) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-sso
- Service: identity-platform
- Article date: 2023-01-16
- Summary: JavaScript 用 Microsoft Authentication Library (MSAL.js) を使用したシングル サインオン エクスペリエンスのビルドについて説明します。

シングル サインオン (SSO) を使用すると、ユーザーが資格情報の入力を求められる回数が減り、よりシームレスなエクスペリエンスが提供されます。 ユーザーが資格情報を 1 回入力すると、それ以上プロンプトは表示されず、確立されたセッションを同じデバイス上の他のアプリケーションで再利用できます。

Microsoft Entra ID では、ユーザーが初めて認証を行ったときに、セッション Cookie を設定することで SSO が有効になります。 また、MSAL.js では、アプリケーション ドメインごとに、ユーザーの ID トークンとアクセス トークンがブラウザーのストレージにキャッシュされます。 2 つのメカニズム (Microsoft Entra セッション Cookie と Microsoft Authentication Library (MSAL) キャッシュ) は相互に独立していますが、連携して SSO の動作を提供します。

### 同じアプリのブラウザー タブ間の SSO

ユーザーが複数のタブでアプリケーションを開き、そのうちの 1 つにサインインすると、他のタブで開かれている同じアプリにも、プロンプトなしでサインインできます。 これを行うには、次の例で示すように、MSAL.js 構成オブジェクトの *cacheLocation* を `localStorage` に設定する必要があります:

```javascript
const config = {
  auth: {
    clientId: "1111-2222-3333-4444-55555555",
  },
  cache: {
    cacheLocation: "localStorage",
  },
};

const msalInstance = new msal.PublicClientApplication(config);
```

この場合、異なるブラウザー タブのアプリケーション インスタンスが同じ MSAL キャッシュを使うため、それらの間で認証状態が共有されます。 MSAL イベントを使って、ユーザーが別のブラウザー タブまたはウィンドウからログインしたときに、アプリケーション インスタンスを更新することもできます。 詳しくは、「[タブ間およびウィンドウ間でのログイン状態の同期](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/events.md#syncing-logged-in-state-across-tabs-and-windows)」をご覧ください

### 異なるアプリ間の SSO

ユーザーが認証を行うと、ブラウザーの Microsoft Entra ドメインでセッション Cookie が設定されます。 MSAL.js では、このセッション Cookie に依存して、ユーザーに異なるアプリケーション間の SSO が提供されます。 具体的には、MSAL.js では、対話なしでのユーザーのサインインとトークンの取得のための `ssoSilent` メソッドが提供されています。 ただし、ユーザーが Microsoft Entra とのセッションに複数のユーザー アカウントを持っている場合は、サインインするアカウントの選択を求められます。 そのため、`ssoSilent` メソッドを使用して SSO を行うには 2 つの方法があります。

#### ユーザーヒントと共に

パフォーマンスを向上させ、承認サーバーが正しいアカウント セッションを探すようにするには、`ssoSilent` メソッドの要求オブジェクトで次のいずれかのオプションを渡して、トークンをサイレントで取得できます。

- `login_hint` は `account` オブジェクトのユーザー名プロパティまたは ID トークンの `upn` 要求から取得できます。 アプリが B2C でユーザー認証を行っている場合、「[ID トークンでユーザー名を放出するために B2C ユーザー フローを構成する](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/FAQ.md#why-is-getaccountbyusername-returning-null-even-though-im-signed-in)」を参照してください
- セッション ID `sid` は `idTokenClaims` オブジェクトの `account` から取得できます。
- `account` は [アカウント メソッド](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/login-user.md#account-apis) を 1 つ使用して取得できます

サイレントと対話型の要求の最も信頼性の高いアカウント ヒントであるため、`login_hint` として  に提供される `ssoSilent``loginHint`を使うことをお勧めします。

##### ログイン ヒントの使用

オプションの要求 `login_hint` は、サインインを試みるユーザー アカウントに関するヒントを Microsoft Entra ID に提供します。 対話型認証要求時に通常表示されるアカウント選択プロンプトをバイパスするには、次のように `loginHint` を指定します:

```javascript
const silentRequest = {
    scopes: ["User.Read", "Mail.Read"],
    loginHint: "user@contoso.com"
};

try {
    const loginResponse = await msalInstance.ssoSilent(silentRequest);
} catch (err) {
    if (err instanceof InteractionRequiredAuthError) {
        const loginResponse = await msalInstance.loginPopup(silentRequest).catch(error => {
            // handle error
        });
    } else {
        // handle error
    }
}
```

この例では、`loginHint` には、対話型トークン要求時にヒントとして使用されるユーザーの電子メールまたは UPN が含まれています。 アプリケーション間でヒントを渡して、サイレント SSO を容易にできます。アプリケーション A は、ユーザーをサインインさせ、`loginHint` を読み取り、アプリケーション B に要求と現在のテナント コンテキストを送信できます。Microsoft Entra ID は、サインイン フォームの事前入力またはアカウント選択プロンプトのバイパスを試み、指定されたユーザーの認証プロセスを直接続行します。

`login_hint` 要求内の情報が既存のユーザーと一致しない場合は、アカウントの選択など、標準のサインイン エクスペリエンスを行うようにリダイレクトされます。

##### セッション ID の使用

セッション ID を使用するには、`sid`として  をアプリの ID トークンに追加します。 `sid` 要求により、アプリケーションは、ユーザーのアカウント名やユーザー名とは関係なく、ユーザーの Microsoft Entra セッションを識別できます。 `sid` のようなオプションの要求を追加する方法については、「[アプリにオプションの要求を設定する](https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims)」をご覧ください。 MSAL.js で `ssoSilent` を使って行うサイレント認証要求で、セッション ID (SID) を使用します。

```javascript
const request = {
  scopes: ["user.read"],
  sid: sid,
};

 try {
    const loginResponse = await msalInstance.ssoSilent(request);
} catch (err) {
    if (err instanceof InteractionRequiredAuthError) {
        const loginResponse = await msalInstance.loginPopup(request).catch(error => {
            // handle error
        });
    } else {
        // handle error
    }
}
```

##### アカウント オブジェクトの使用

ユーザー アカウント情報を把握している場合は、次の `getAccountByUsername()` または `getAccountByHomeId()` メソッドを使用してユーザー アカウントを取得することもできます。

```javascript
const username = "test@contoso.com";
const myAccount  = msalInstance.getAccountByUsername(username);

const request = {
    scopes: ["User.Read"],
    account: myAccount
};

try {
    const loginResponse = await msalInstance.ssoSilent(request);
} catch (err) {
    if (err instanceof InteractionRequiredAuthError) {
        const loginResponse = await msalInstance.loginPopup(request).catch(error => {
            // handle error
        });
    } else {
        // handle error
    }
}
```

#### ユーザーのヒントを使わない

以下のコードに示すように、`ssoSilent`、`account`、または `sid` を渡さずに、`login_hint` メソッドの使用を試みることができます。

```javascript
const request = {
    scopes: ["User.Read"]
};

try {
    const loginResponse = await msalInstance.ssoSilent(request);
} catch (err) {
    if (err instanceof InteractionRequiredAuthError) {
        const loginResponse = await msalInstance.loginPopup(request).catch(error => {
            // handle error
        });
    } else {
        // handle error
    }
}
```

ただし、アプリケーションの 1 つのブラウザー セッションに複数のユーザーがいる場合、またはユーザーがその 1 つのブラウザー セッションに対して複数のアカウントを持っている場合は、サイレント サインイン エラーが発生する可能性があります。 複数のアカウントが使用可能な場合は、次のエラーが表示されることがあります。

```txt
InteractionRequiredAuthError: interaction_required: AADSTS16000: Either multiple user identities are available for the current request or selected account is not supported for the scenario.
```

このエラーは、どのアカウントにサインインするかをサーバーが判断できなかったことを示しており、アカウントを選択するには、前の例のパラメーター (`account`、`login_hint`、`sid`) のいずれか、または対話型サインインが必要になります。

### `ssoSilent` を使用する場合の考慮事項

#### リダイレクト URI (応答 URL)

パフォーマンスを向上させ、問題を回避するには、空白ページ、または MSAL を使用しない他のページに `redirectUri` を設定します。

- アプリケーションでポップアップ メソッドとサイレント メソッドのみを使用する場合は、`redirectUri` 構成オブジェクトに `PublicClientApplication` を設定します。
- アプリケーションでリダイレクト メソッドも使用する場合は、要求ごとに `redirectUri` を設定します。

#### サード パーティの Cookie

`ssoSilent` は、非表示の iframe を開き、Microsoft Entra ID との既存のセッションを再利用しようとします。 これは、Safari などのサードパーティの Cookie をブロックするブラウザーでは機能せず、対話エラーが発生します。

```txt
InteractionRequiredAuthError: login_required: AADSTS50058: A silent sign-in request was sent but no user is signed in. The cookies used to represent the user's session were not sent in the request to Azure AD
```

このエラーを解決するために、ユーザーは `loginPopup()` または `loginRedirect()` を使用して対話型認証要求を作成する必要があります。 場合によっては、プロンプト値 **none** を対話型の MSAL.js メソッドと共に使って、SSO を実現できます。 詳しくは、「[prompt=none の対話型要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-prompt-behavior#interactive-requests-with-promptnone)」をご覧ください。 ユーザーのサインイン情報が既にある場合は、`loginHint` または `sid` のどちらかの省略可能なパラメーターを渡して、特定のアカウントにサインインすることができます。

### prompt=login での SSO の否定

認可サーバーとのアクティブなセッションがあるにもかかわらず、Microsoft Entra ID でユーザーに資格情報の入力を求めたい場合は、MSAL.js の要求で **login** プロンプト パラメーターを使用できます。 詳しくは、[MSAL.js プロンプトの動作](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-prompt-behavior)に関する記事をご覧ください。

### ADAL.js と MSAL.js の間での認証状態の共有

MSAL.js には、Microsoft Entra ID の認証シナリオに対する ADAL.js での機能パリティがあります。 ADAL.js から MSAL.js への移行を容易にし、アプリ間で認証状態を共有するために、ライブラリは、ADAL.js キャッシュ内のユーザーのセッションを表す ID トークンを読み取ります。 ADAL.js から移行するときにこれを利用するには、ライブラリでトークンのキャッシュに `localStorage` を使っていることを確認する必要があります。 次のように、初期化時に、MSAL.js と ADAL.js 両方の構成で、`cacheLocation` を `localStorage` に設定します。

```javascript

// In ADAL.js
window.config = {
  clientId: "1111-2222-3333-4444-55555555",
  cacheLocation: "localStorage",
};

var authContext = new AuthenticationContext(config);

// In latest MSAL.js version
const config = {
  auth: {
    clientId: "1111-2222-3333-4444-55555555",
  },
  cache: {
    cacheLocation: "localStorage",
  },
};

const msalInstance = new msal.PublicClientApplication(config);
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-js-use-ie-browser"} -->
## Internet Explorer の問題 (MSAL.js) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-js-use-ie-browser
- Service: identity-platform
- Article date: 2021-12-01
- Summary: Internet Explorer ブラウザーで JavaScript 用 Microsoft Authentication Library (MSAL.js) を使用します。

Internet Explorer との互換性を高めるために、Microsoft Authentication Library for JavaScript (MSAL.js) for [JavaScript ES5](https://262.ecma-international.org/5.1/) が生成されますが、アプリケーションの開発時に考慮すべき点は他にもあります。

### Internet Explorer でアプリを実行する

Internet Explorer には、MSAL.jsで必要な JavaScript Promise のネイティブ サポートがありません。

Internet Explorer アプリで JavaScript Promise をサポートするには、MSAL.jsを参照する前に Promise ポリフィルを参照してください。

```html
<script
  src="https://cdnjs.cloudflare.com/ajax/libs/bluebird/3.3.4/bluebird.min.js"
  class="pre"
></script>
```

### Internet Explorer で実行されているアプリケーションのデバッグ

#### 運用環境での実行

エンド ユーザーがポップアップを受け入れた場合、運用環境 (Azure Web アプリなど) へのアプリケーションのデプロイは通常正常に動作します。 Internet Explorer 11 でテストしました。

#### ローカルでの実行

アプリケーションをローカルでデバッグするには、デバッグ セッション中に Internet Explorer の *保護モード* を一時的に無効にします。

1. Internet Explorer で、[**ツール**&gt;&gt;] タブ &gt; ゾーンを選択します。
2. [ **保護モードを有効にする (Internet Explorer の再起動が必要)]** チェック ボックスをオフにします。
3. [ **OK] を** 選択して Internet Explorer を再起動します。

デバッグが完了したら、前の手順に従い、保護 **モードを有効にする (Internet Explorer を再起動する必要があります** ) チェック ボックスをオンにします (オフではなく)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-logging-js"} -->
## MSAL.js でのエラーと例外のログ記録 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-logging-js
- Service: identity-platform
- Article date: 2023-12-19
- Summary: MSAL.js でエラーと例外をログに記録する方法について説明します

Microsoft Authentication Library (MSAL) アプリは、問題の診断に役立つログ メッセージを生成します。 アプリでは、数行のコードでログ記録を構成し、詳細レベルと個人データと組織データをログに記録するかどうかをカスタム制御できます。 MSAL ログの実装を作成し、ユーザーが認証の問題がある場合にログを送信する方法を提供することをお勧めします。

### ログ記録のレベル

MSAL には、いくつかのレベルのログの詳細が用意されています。

- LogAlways: このログ レベルでは、レベル のフィルター処理は行われません。 すべてのレベルのログ メッセージがログに記録されます。
- 重大: 回復不能なアプリケーションまたはシステムのクラッシュ、または直ちに注意が必要な致命的な障害を記述するログ。
- エラー: 問題が発生し、エラーが生成されたことを示します。 問題のデバッグと特定に使用されます。
- Warning: 必ずしもエラーや障害が発生したわけではありませんが、診断や問題の特定が想定されています。
- 情報: MSAL は、必ずしもデバッグを目的としていない情報提供目的のイベントをログに記録します。
- Verbose (既定値): MSAL は、ライブラリの動作の完全な詳細をログします。

注

すべての MSAL SDK のすべてのログ レベルが使用できるわけではありません

### 個人データと組織データ

既定では、MSAL ロガーは機密性の高い個人データや組織データをキャプチャしません。 ライブラリには、個人データと組織データのログ記録を有効にするオプションが用意されています (これを行う場合)。

次のセクションでは、アプリケーションの MSAL エラー ログの詳細について説明します。

### MSAL.js でログ記録を構成する

`PublicClientApplication` インスタンスを作成するための構成中に loggerOptions オブジェクトを渡して、MSAL.js (JavaScript) のログ記録を有効にします。 必要な構成パラメーターは、アプリケーションのクライアント ID のみです。 それ以外はすべて省略可能ですが、テナントとアプリケーション モデルによっては必要になる場合があります。

loggerOptions オブジェクトには、次のプロパティがあります。

- `loggerCallback`: カスタムの方法で MSAL ステートメントのログ記録を処理するために開発者が提供できるコールバック関数。 ログのリダイレクト方法に応じて、 `loggerCallback` 関数を実装します。 loggerCallback 関数の形式は次のとおりです。 `(level: LogLevel, message: string, containsPii: boolean): void`
    - サポートされているログ レベルは、 `Error`、 `Warning`、 `Info`、および `Verbose`です。 既定値は `Info`です。
- `piiLoggingEnabled` (省略可能): true に設定すると、個人データと組織データがログに記録されます。 アプリケーションが個人データをログに記録しないように、既定ではこれは false です。 個人データ ログは、コンソール、Logcat、NSLog などの既定の出力に書き込まれることはありません。

```javascript
import msal from "@azure/msal-browser"

const msalConfig = {
    auth: {
        clientId: "enter_client_id_here",
        authority: "https://login.microsoftonline.com/common",
        knownAuthorities: [],
        cloudDiscoveryMetadata: "",
        redirectUri: "enter_redirect_uri_here",
        postLogoutRedirectUri: "enter_postlogout_uri_here",
        navigateToLoginRequestUrl: true,
        clientCapabilities: ["CP1"]
    },
    cache: {
        cacheLocation: "sessionStorage",
        storeAuthStateInCookie: false,
        secureCookies: false
    },
    system: {
        loggerOptions: {
            logLevel: msal.LogLevel.Verbose,
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
            piiLoggingEnabled: false
        },
    },
};
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-migration"} -->
## Microsoft Authentication Library (MSAL) への移行 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-migration
- Service: identity-platform
- Article date: 2025-02-27
- Summary: Microsoft Authentication Library (MSAL) と Azure AD Authentication Library (ADAL) の違いと、MSAL への移行方法について説明します。

お使いのアプリケーションで認証と承認の機能に Azure Active Directory 認証ライブラリ (ADAL) を使用している場合は、[Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/msal) に移行する時期です。

- セキュリティ修正プログラムを含め、ADAL についてのすべての Microsoft のサポートと開発は、2023 年 6 月 30 日に終了しました。
- 廃止日より前に、ADAL 機能リリースまたは新しいプラットフォーム バージョンのリリースが計画されていませんでした。
- 2020 年 6 月 30 日以降、ADAL に新しい機能は追加されていません。

警告

Azure Active Directory 認証ライブラリ (ADAL) は非推奨です。 ADAL を使用している既存のアプリは引き続き機能しますが、Microsoft は今後 ADAL のセキュリティ修正プログラムをリリースしません。 [Microsoft Authentication Library (MSAL)](https://learn.microsoft.com/ja-jp/entra/msal/) を使用して、アプリのセキュリティを危険にさらさないようにする必要があります。

### MSAL に切り替える理由

Azure AD (v1.0) エンドポイントを使用してアプリを開発したことがある場合は、ADAL を使用している可能性が高くなります。 Microsoft ID プラットフォーム (v2.0) エンドポイントが大幅に変更されたため、新しいエンドポイント用にまったく新しいライブラリ (MSAL) が構築されました。

MSAL は、開発者が実装の詳細について心配することなく、セキュリティで保護されたソリューションを実現できるように設計されています。 これにより、トークンの取得、管理、キャッシュ、および更新が簡素化されて管理され、回復性のためのベスト プラクティスが使用されます。 MSAL を使用して、[開発するクライアント アプリケーションでの認証と承認の回復性を向上させる](https://learn.microsoft.com/ja-jp/entra/architecture/resilience-client-app?tabs=csharp#use-the-microsoft-authentication-library-msal)ことをお勧めします。

MSAL には、次の機能を含め、ADAL と比較して複数の利点があります。

| 機能 | MSAL | ADALの |
| --- | --- | --- |
| **セキュリティ** |  |  |
| 2023 年 6 月以降のセキュリティ修正プログラム | [Image: 2023 年 6 月以降のセキュリティ修正プログラム: MSAL ではこの機能が提供されます] | [Image: 2023 年 6 月以降のセキュリティ修正プログラム: ADAL ではこの機能が提供されません] |
| [継続的アクセス評価 (CAE)](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-resilience-continuous-access-evaluation) をサポートする Microsoft Graph およびその他の API のポリシーまたは重要なイベントに基づいてトークンを事前に更新および取り消す。 | [Image: 継続的アクセス評価 (CAE) をサポートする Microsoft Graph およびその他の API のポリシーまたは重要なイベントに基づいてトークンを事前に更新および取り消す: MSAL ではこの機能が提供されます] | [Image: 継続的アクセス評価 (CAE) をサポートする Microsoft Graph およびその他の API のポリシーまたは重要なイベントに基づいてトークンを事前に更新および取り消す: ADAL ではこの機能が提供されません] |
| OAuth v2.0 および OpenID Connect (OIDC) の標準への準拠 | [Image: OAuth v2.0 および OpenID Connect (OIDC) の標準への準拠: MSAL ではこの機能が提供されます] | [Image: OAuth v2.0 および OpenID Connect (OIDC) の標準への準拠: ADAL ではこの機能が提供されません] |
| **ユーザー アカウントとエクスペリエンス** |  |  |
| Microsoft Entra アカウント | [Image: Microsoft Entra アカウント - MSAL で提供される機能] | [Image: Microsoft Entra アカウント - ADAL で提供される機能] |
| Microsoft アカウント (MSA) | [Image: Microsoft アカウント (MSA): MSAL ではこの機能が提供されます] | [Image: Microsoft アカウント (MSA): ADAL ではこの機能が提供されません] |
| Azure AD B2C アカウント | [Image: Azure AD B2Cアカウント: MSAL ではこの機能が提供されます] | [Image: Azure AD B2Cアカウント: ADAL ではこの機能が提供されません] |
| 最適なシングル サインオン エクスペリエンス | [Image: 最適なシングル サインオン エクスペリエンス: MSAL ではこの機能が提供されます] | [Image: 最適なシングル サインオン エクスペリエンス: ADAL ではこの機能が提供されません] |
| **認証エクスペリエンス** |  |  |
| 事前のトークン更新による継続的アクセス評価 | [Image: トークンの事前更新: MSAL ではこの機能が提供されます] | [Image: トークンの事前更新: ADAL ではこの機能が提供されません] |
| スロットリング | [Image: 調整: MSAL ではこの機能が提供されます] | [Image: 調整: ADAL ではこの機能が提供されません] |
| 認証ブローカーのサポート | [Image: デバイスベースの条件付きアクセス ポリシー - MSAL に組み込まれている機能] | [Image: デバイスベースの条件付きアクセス ポリシー - ADAL で提供されない機能] |
| トークン保護 | [Image: トークン保護 - MSAL で提供される機能] | [Image: トークン保護 - ADAL で提供されない機能] |

### ADAL を介した MSAL の追加機能

- 所有証明トークン
- モバイルでの Microsoft Entra 証明書ベースの認証 (CBA)
- モバイル デバイス上のシステム ブラウザー
- ADAL に認証コンテキスト クラスのみが含まれている場合、MSAL はクライアント アプリ (パブリック クライアントと機密クライアント) のコレクションの概念を公開します。

### MSAL での Active Directory フェデレーション サービス (AD FS) のサポート

MSAL.NET、MSAL Java、MSAL.js、MSAL Python を使用して、Active Directory フェデレーション サービス (AD FS) (AD FS) 2019 以降からトークンを取得できます。 AD FS 2016 を含め、以前のバージョンの AD FS は MSAL ではサポートされていません。

AD FS を引き続き使用する必要がある場合は、ADAL から MSAL にアプリケーションを更新する前に、AD FS 2019 以降にアップグレードする必要があります。

### MSAL への移行方法

移行を開始する前に、認証に ADAL を使用しているアプリを特定する必要があります。 こちらの記事の手順に従って、Azure portal を使用して一覧を取得してください。

- [方法: テナントで ADAL を使用しているアプリの一覧を取得する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-get-list-of-all-auth-library-apps)

ADAL を使用しているアプリケーションを特定した後、アプリの種類に応じて MSAL に移行します。

**シングルページ アプリ (SPA)**

- [ADAL.js から MSAL.js へ](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-compare-msal-js-and-adal-js)

**Web アプリ**

- [ADAL ノードから MSAL ノードへ](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-node-migration)
- [ADAL.NET から MSAL.NET へ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/msal-net-migration)

**ウェブAPI**

- [ADAL Java から MSAL Java へ](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/migrate-adal-msal-java)
- [ADAL Python から MSAL Python へ](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/migrate-python-adal-msal)
- [ADAL.NET から MSAL.NET へ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/msal-net-migration)

**デスクトップ アプリ**

- [ADAL Java から MSAL Java へ](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/migrate-adal-msal-java)
- [ADAL Python から MSAL Python へ](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/migrate-python-adal-msal)
- [ADAL.NET から MSAL.NET へ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/msal-net-migration)

**モバイル アプリ**

- [ADAL.Android から MSAL.Android へ](https://learn.microsoft.com/ja-jp/entra/identity-platform/migrate-android-adal-msal)
- [ADAL.iOS から MSAL.iOS へ](https://learn.microsoft.com/ja-jp/entra/msal/objc/migrate-objc-adal-msal)

**サービス/デーモン アプリ**

- [ADAL Python から MSAL Python へ](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/migrate-python-adal-msal)
- [ADAL.NET から MSAL.NET へ](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/msal-net-migration)
- [ADAL ノードから MSAL ノードへ](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-node-migration)
- [ADAL Java から MSAL Java へ](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/migrate-adal-msal-java)

MSAL は、さまざまなアプリケーションの種類とシナリオをサポートします。 [いくつかのアプリケーションの種類に対する Microsoft 認証ライブラリのサポート](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-v2-libraries#single-page-application-spa)に関する記事を参照してください。

次のリンクからさまざまなプラットフォームの ADAL から MSAL への移行ガイドを入手できます。

- [MSAL iOS と macOS への移行](https://learn.microsoft.com/ja-jp/entra/msal/objc/migrate-objc-adal-msal)
- [MSAL Java への移行](https://learn.microsoft.com/ja-jp/entra/msal/java/advanced/migrate-adal-msal-java)
- [MSAL.js への移行](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-compare-msal-js-and-adal-js)
- [MSAL .NET への移行](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/how-to/msal-net-migration)
- [MSAL Node への移行](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-node-migration)
- [MSAL Python への移行](https://learn.microsoft.com/ja-jp/entra/msal/python/advanced/migrate-python-adal-msal)

### 移行のヘルプ

ADAL から MSAL へのアプリの移行について質問がある場合、こちらにいくつかのオプションを示しています。

- [Microsoft Q&A](https://learn.microsoft.com/ja-jp/answers/topics/azure-ad-adal-deprecation.html) で、タグ `[azure-ad-adal-deprecation]` を使用して質問を投稿する。
- ライブラリの GitHub リポジトリでイシューを開く。 各ライブラリのリポジトリへのリンクについては、MSAL の概要に関する記事の「[言語とフレームワーク](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview#msal-languages-and-frameworks)」セクションを参照してください。

アプリケーションの開発で独立系ソフトウェア ベンダー (ISV) と提携している場合は、MSAL への移行手順を把握するために、先方に直接連絡することをお勧めします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-national-cloud"} -->
## 国内クラウド アプリで MSAL を使用する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-national-cloud
- Service: identity-platform
- Article date: 2021-09-21
- Summary: Microsoft Authentication Library (MSAL) を使用すると、アプリケーション開発者はセキュリティで保護された Web API を呼び出すためにトークンを取得できます。 これらの Web API には、Microsoft Graph、他の Microsoft API、パートナー Web API、または独自の Web API を使用できます。 MSAL は、複数のアプリケーション アーキテクチャとプラットフォームをサポートします。

[国内クラウド](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud) (ソブリン クラウドとも呼ばれます) は、Azure の物理的に分離されたインスタンスです。 これらの Azure リージョンは、データ所在地、主権、コンプライアンスの要件が地理的な境界内で確実に遵守されるようにするのに役立ちます。

Microsoft の世界規模のクラウドに加えて、Microsoft Authentication Library (MSAL) を使用すると、各国のクラウドのアプリケーション開発者は、セキュリティで保護された Web API を認証して呼び出すためにトークンを取得できます。 これらの Web API には、Microsoft Graph または他の Microsoft API を使用できます。

グローバル Azure クラウドを含め、Microsoft Entra ID は次の国内クラウドにデプロイされます。

- Azure Government（アジュール・ガバメント）
- 21Vianet によって運営される Microsoft Azure
- Azure Germany ([2021 年 10 月 29 日終了](https://www.microsoft.com/cloud-platform/germany-cloud-regions))

このガイドでは、職場と学校のアカウントにサインインし、アクセス トークンを取得し、 [Azure Government クラウド](https://azure.microsoft.com/global-infrastructure/government/) 環境で Microsoft Graph API を呼び出す方法について説明します。

### Azure Germany (Microsoft Cloud Deutschland)

Warnung

Azure Germany (Microsoft Cloud Deutschland) は [、2021 年 10 月 29 日に閉鎖](https://www.microsoft.com/cloud-platform/germany-cloud-regions)されます。 その日付より前にグローバル Azure のリージョンに移行 *しないことを* 選択したサービスとアプリケーションはアクセスできなくなります。

Azure Germany からアプリケーションを移行していない場合は、 [Microsoft Entra の情報に従って Azure Germany からの移行](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/ms-cloud-germany-transition-azure-ad) を開始します。

### [前提条件]

開始する前に、これらの前提条件を満たしていることを確認してください。

#### 適切な ID を選択する

[Azure Government](https://learn.microsoft.com/ja-jp/azure/azure-government/) アプリケーションでは、Microsoft Entra Government ID と Microsoft Entra パブリック ID を使用してユーザーを認証できます。 これらの ID のいずれかを使用できるため、シナリオに対して選択する機関エンドポイントを決定します。

- Microsoft Entra Public: 組織に Microsoft 365 (パブリックまたは GCC) または別のアプリケーションをサポートする Microsoft Entra パブリック テナントが既にある場合に一般的に使用されます。
- Microsoft Entra Government: 組織に Office 365 (GCC High または DoD) をサポートする Microsoft Entra Government テナントが既にある場合、または Microsoft Entra Government で新しいテナントを作成している場合に一般的に使用されます。

決定した後は、アプリの登録を実行する場所が特別な考慮事項になります。 Azure Government アプリケーションの Microsoft Entra パブリック ID を選択した場合は、アプリケーションを Microsoft Entra パブリック テナントに登録する必要があります。

#### Azure Government サブスクリプションを取得する

Azure Government サブスクリプションを取得するには、「Azure [Government でのサブスクリプションの管理と接続](https://learn.microsoft.com/ja-jp/azure/azure-government/compare-azure-government-global-azure)」を参照してください。

Azure Government サブスクリプションをお持ちでない場合は、開始する前に [無料アカウント](https://azure.microsoft.com/global-infrastructure/government/request/) を作成してください。

特定のプログラミング言語で各国のクラウドを使用する方法の詳細については、言語に一致するタブを選択します。

## [.NET](#tab/dotnet)
MSAL.NET を使用して、ユーザーのサインイン、トークンの取得、国内クラウドでの Microsoft Graph API の呼び出しを行うことができます。

次のチュートリアルでは、ASP.NET Core Web アプリを構築する方法を示します。 このアプリでは、OpenID Connect を使用して、国内クラウドに属する組織の職場および学校アカウントでユーザーをサインインさせます。

- ユーザーをサインインさせ、トークンを取得するには、次のチュートリアルに従います。 [Microsoft ID プラットフォームを使用してソブリン クラウド内の ASP.NET Core Web アプリのサインイン ユーザーを構築します](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/1-WebApp-OIDC/1-4-Sovereign#build-an-aspnet-core-web-app-signing-in-users-in-sovereign-clouds-with-the-microsoft-identity-platform)。
- Microsoft Graph API を呼び出すには、次のチュートリアルに従います。 [Microsoft Id プラットフォームを使用して、Microsoft National Cloud の職場および学校アカウントを使用してユーザーのサインインに代わって、ASP.NET Core Web アプリから Microsoft Graph API を呼び出](https://github.com/Azure-Samples/active-directory-aspnetcore-webapp-openidconnect-v2/tree/master/2-WebApp-graph-user/2-4-Sovereign-Call-MSGraph#using-the-microsoft-identity-platform-to-call-the-microsoft-graph-api-from-an-an-aspnet-core-2x-web-app-on-behalf-of-a-user-signing-in-using-their-work-and-school-account-in-microsoft-national-cloud)します。

## [JavaScript](#tab/javascript)
ソブリンクラウドに対して MSAL.js アプリケーションを有効化するには:

- クラウドに応じて、特定のポータルにアプリケーションを登録します。 ポータルを選択する方法の詳細については、「[アプリ登録エンドポイント」](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud#app-registration-endpoints)を参照してください
- 次に説明するクラウドに応じて、構成にいくつかの変更を加えて、リポジトリの [サンプル](https://github.com/Azure-Samples/ms-identity-javascript-tutorial) のいずれかを使用します。
- アプリケーションを登録したクラウドに応じて、特定の機関を使用します。 さまざまなクラウドの機関の詳細については、 [Microsoft Entra 認証エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud#azure-ad-authentication-endpoints)を参照してください。
- Microsoft Graph API を呼び出すには、使用しているクラウドに固有のエンドポイント URL が必要です。 すべての国内クラウドの Microsoft Graph エンドポイントを検索するには、 [Microsoft Graph と Graph Explorer サービスのルート エンドポイント](https://learn.microsoft.com/ja-jp/graph/deployments#microsoft-graph-and-graph-explorer-service-root-endpoints)を参照してください。

権限の例を次に示します。

```json
"authority": "https://login.microsoftonline.us/Enter_the_Tenant_Info_Here"
```

スコープを持つ Microsoft Graph エンドポイントの例を次に示します。

```json
"endpoint" : "https://graph.microsoft.us/v1.0/me"
"scope": "User.Read"
```

ソブリン クラウドを使用してユーザーを認証し、Microsoft Graph を呼び出すための最小限のコードを次に示します。

```javascript
const msalConfig = {
    auth: {
        clientId: "Enter_the_Application_Id_Here",
        authority: "https://login.microsoftonline.us/Enter_the_Tenant_Info_Here",
        redirectUri: "/",
    }
};

// Initialize MSAL
const msalObj = new PublicClientApplication(msalConfig);

// Get token using popup experience
try {
    const graphToken = await msalObj.acquireTokenPopup({
        scopes: ["User.Read"]
    });
} catch(error) {
    console.log(error)
}

// Call the Graph API
const headers = new Headers();
const bearer = `Bearer ${graphToken}`;

headers.append("Authorization", bearer);

fetch("https://graph.microsoft.us/v1.0/me", {
    method: "GET",
    headers: headers
})
```

## [Python](#tab/python)
ソブリン クラウドに対して MSAL Python アプリケーションを有効にするには:

- クラウドに応じて、特定のポータルにアプリケーションを登録します。 ポータルを選択する方法の詳細については、「[アプリ登録エンドポイント」](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud#app-registration-endpoints)を参照してください
- 次に説明するクラウドに応じて、構成にいくつかの変更を加えて、リポジトリの [サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-python/tree/dev/sample) のいずれかを使用します。
- アプリケーションを登録したクラウドに応じて、特定の機関を使用します。 さまざまなクラウドの機関の詳細については、 [Microsoft Entra 認証エンドポイントを](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud#azure-ad-authentication-endpoints)参照してください。

    権限の例を次に示します。

    ```json
    "authority": "https://login.microsoftonline.us/Enter_the_Tenant_Info_Here"
    ```
- Microsoft Graph API を呼び出すには、使用しているクラウドに固有のエンドポイント URL が必要です。 すべての国内クラウドの Microsoft Graph エンドポイントを検索するには、 [Microsoft Graph と Graph Explorer サービスのルート エンドポイント](https://learn.microsoft.com/ja-jp/graph/deployments#microsoft-graph-and-graph-explorer-service-root-endpoints)を参照してください。

    スコープを持つ Microsoft Graph エンドポイントの例を次に示します。

    ```json
    "endpoint" : "https://graph.microsoft.us/v1.0/me"
    "scope": "User.Read"
    ```

## [ジャワ](#tab/java)
ソブリン クラウドに対して MSAL for Java アプリケーションを有効にするには:

- クラウドに応じて、特定のポータルにアプリケーションを登録します。 ポータルを選択する方法の詳細については、「[アプリ登録エンドポイント」](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud#app-registration-endpoints)を参照してください
- 次に説明するクラウドに応じて、構成にいくつかの変更を加えて、リポジトリの [サンプル](https://github.com/AzureAD/microsoft-authentication-library-for-java/tree/dev/msal4j-sdk/src/samples) のいずれかを使用します。
- アプリケーションを登録したクラウドに応じて、特定の機関を使用します。 さまざまなクラウドの機関の詳細については、 [Microsoft Entra 認証エンドポイントを](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud#azure-ad-authentication-endpoints)参照してください。

権限の例を次に示します。

```json
"authority": "https://login.microsoftonline.us/Enter_the_Tenant_Info_Here"
```

- Microsoft Graph API を呼び出すには、使用しているクラウドに固有のエンドポイント URL が必要です。 すべての国内クラウドの Microsoft Graph エンドポイントを検索するには、 [Microsoft Graph と Graph Explorer サービスのルート エンドポイント](https://learn.microsoft.com/ja-jp/graph/deployments#microsoft-graph-and-graph-explorer-service-root-endpoints)を参照してください。

スコープを持つグラフ エンドポイントの例を次に示します。

```json
"endpoint" : "https://graph.microsoft.us/v1.0/me"
"scope": "User.Read"
```

## [Objective-C](#tab/objc)
iOS および macOS 用の MSAL を使用して各国のクラウドでトークンを取得できますが、 `MSALPublicClientApplication`の作成時に追加の構成が必要です。

たとえば、アプリケーションを国内クラウド (米国政府) のマルチテナント アプリケーションにする場合は、次のように記述できます。

```objc
MSALAADAuthority *aadAuthority =
                [[MSALAADAuthority alloc] initWithCloudInstance:MSALAzureUsGovernmentCloudInstance
                                                   audienceType:MSALAzureADMultipleOrgsAudience
                                                      rawTenant:nil
                                                          error:nil];

MSALPublicClientApplicationConfig *config =
                [[MSALPublicClientApplicationConfig alloc] initWithClientId:@"<your-client-id-here>"
                                                                redirectUri:@"<your-redirect-uri-here>"
                                                                  authority:aadAuthority];

NSError *applicationError = nil;
MSALPublicClientApplication *application =
                [[MSALPublicClientApplication alloc] initWithConfiguration:config error:&applicationError];
```

## [速い](#tab/swift)
iOS および macOS 用の MSAL を使用して各国のクラウドでトークンを取得できますが、 `MSALPublicClientApplication`の作成時に追加の構成が必要です。

たとえば、アプリケーションを国内クラウド (米国政府) のマルチテナント アプリケーションにする場合は、次のように記述できます。

```swift
let authority = try? MSALAADAuthority(cloudInstance: .usGovernmentCloudInstance, audienceType: .azureADMultipleOrgsAudience, rawTenant: nil)

let config = MSALPublicClientApplicationConfig(clientId: "<your-client-id-here>", redirectUri: "<your-redirect-uri-here>", authority: authority)
if let application = try? MSALPublicClientApplication(configuration: config) { /* Use application */}
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-net-system-browser-android-considerations"} -->
## Xamarin Android のシステム ブラウザーに関する考慮事項 (MSAL.NET) - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-net-system-browser-android-considerations
- Service: identity-platform
- Article date: 2024-06-05
- Summary: Microsoft Authentication Library for .NET (MSAL.NET) で Xamarin Android 上のシステム ブラウザーを使用する場合の考慮事項について説明します。

この記事では、Microsoft Authentication Library for .NET (MSAL.NET) で Xamarin Android 上のシステム ブラウザーを使用する場合に考慮すべきことについて説明します。

注

MSAL.NET バージョン 4.61.0 以降では、ユニバーサル Windows プラットフォーム (UWP)、Xamarin Android、Xamarin iOS はサポートされていません。 Xamarin アプリケーションを MAUI などの最新のフレームワークに移行することをお勧めします。 この非推奨化の詳細については、「[Xamarin および UWP 用 MSAL.NET で予定されている廃止のお知らせ](https://devblogs.microsoft.com/identity/uwp-xamarin-msal-net-deprecation/)」をお読みください。

MSAL.NET 2.4.0 Preview 以降では、MSAL.NET は Chrome 以外のブラウザーをサポートしています。 認証のために、Android デバイスに Chrome をインストールする必要がなくなりました。

カスタム タブをサポートするブラウザーを使用することをお勧めします。 これらのブラウザーの例をいくつか次に示します。

| カスタム タブをサポートするブラウザー | パッケージ名 |
| --- | --- |
| クロム | com.android.chrome |
| Microsoft Edge | com.microsoft.emmx |
| Firefox | org.mozilla.firefox の |
| エコシア | com.ecosia.android |
| キーウィ | com.kiwibrowser.browser |
| 勇ましい | com.brave.browser (英語) |

Microsoft のテストでは、カスタム タブをサポートするブラウザーが特定されただけでなく、カスタム タブをサポートしていないいくつかのブラウザーも認証用に使用できることが示されました。 これらのブラウザーには、Opera、Opera Mini、InBrowser、Maxthon などがあります。

### テストしたデバイスとブラウザー

次の表に、認証の互換性をテストしたブラウザーとデバイスの一覧を示します。

| デバイス | ブラウザー | 結果 |
| --- | --- | --- |
| ファーウェイ/One+ | クロム\* | 合格 |
| ファーウェイ/One+ | 端\* | 合格 |
| ファーウェイ/One+ | Firefoxの | 合格 |
| ファーウェイ/One+ | 勇ましい\* | 合格 |
| ワン+ | エコシア\* | 合格 |
| ワン+ | キーウィ\* | 合格 |
| ファーウェイ/One+ | オペラ | 合格 |
| ファーウェイ | オペラミニ | 合格 |
| ファーウェイ/One+ | インブラウザ | 合格 |
| ワン+ | マクソン | 合格 |
| ファーウェイ/One+ | ダックダックゴー | ユーザーによって認証が取り消された |
| ファーウェイ/One+ | UC ブラウザー | ユーザーによって認証が取り消された |
| ワン+ | イルカ | ユーザーによって認証が取り消された |
| ワン+ | CM ブラウザー | ユーザーによって認証が取り消された |
| ファーウェイ/One+ | 何もインストールされていない | AndroidActivityNotFound 例外 |

\* カスタム タブをサポートする

### 既知の問題

ユーザーのデバイス上に有効になっているブラウザーがない場合、MSAL.NET は `AndroidActivityNotFound` 例外をスローします。

- **対応策**:ユーザーに自分のデバイスのブラウザーを有効にするように依頼します。 カスタム タブをサポートするブラウザーを推奨します。

認証が失敗した場合 (たとえば、認証が DuckDuckGo で起動する場合)、MSAL.NET は `AuthenticationCanceled MsalClientException` を返します。

- **根本原因**:カスタム タブをサポートするブラウザーがデバイスで有効になっていません。 認証を完了できないブラウザーで認証が開始されました。
- **対応策**:ユーザーに自分のデバイスのブラウザーを有効にするように依頼します。 カスタム タブをサポートするブラウザーを推奨します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-node-extensions"} -->
## Microsoft Authentication Extensions for Node に関する詳細 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-node-extensions
- Service: identity-platform
- Article date: 2022-02-04
- Summary: Microsoft Authentication Extensions for Node を使用すると、アプリケーション開発者はクロスプラットフォーム トークン キャッシュのシリアル化と永続化を実行できます。 これにより、Node 用の Microsoft Authentication Library (MSAL Node) が追加でサポートされます。

Microsoft Authentication Extensions for Node を使用すると、開発者はクロスプラットフォーム トークン キャッシュのシリアル化とディスクへの永続化を実行できます。 これにより、Node 用の Microsoft Authentication Library (MSAL) が追加でサポートされます。

[Node 用 MSAL](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-v2-nodejs-webapp-msal) では、既定でメモリ内キャッシュがサポートされ、キャッシュのシリアル化を実行するための ICachePlugin インターフェイスが提供されますが、トークン キャッシュをディスクに格納する既定の方法は提供されません。 Microsoft Authentication Extensions for Node は、異なるプラットフォームにまたがるディスクにキャッシュを永続化するための既定の実装です。

Microsoft Authentication Extensions for Node では、次のプラットフォームがサポートされています。

- Windows - データ保護 API (DPAPI) が保護に使用されます。
- Mac - Mac のキーチェーンが使用されます。
- Linux - "Secret Service" への格納に LibSecret が使用されます。

### インストール

`msal-node-extensions` パッケージは、Node パッケージ マネージャー (NPM) で使用できます。

```bash
npm i @azure/msal-node-extensions --save
```

### トークン キャッシュを構成する

Microsoft Authentication Extensions for Node を使用してトークン キャッシュを構成するコードの例を次に示します。

```javascript
const {
  DataProtectionScope,
  Environment,
  PersistenceCreator,
  PersistenceCachePlugin,
} = require("@azure/msal-node-extensions");

// You can use the helper functions provided through the Environment class to construct your cache path
// The helper functions provide consistent implementations across Windows, Mac and Linux.
const cachePath = path.join(Environment.getUserRootDirectory(), "./cache.json");

const persistenceConfiguration = {
  cachePath,
  dataProtectionScope: DataProtectionScope.CurrentUser,
  serviceName: "<SERVICE-NAME>",
  accountName: "<ACCOUNT-NAME>",
  usePlaintextFileOnLinux: false,
};

// The PersistenceCreator obfuscates a lot of the complexity by doing the following actions for you :-
// 1. Detects the environment the application is running on and initializes the right persistence instance for the environment.
// 2. Performs persistence validation for you.
// 3. Performs any fallbacks if necessary.
PersistenceCreator.createPersistence(persistenceConfiguration).then(
  async (persistence) => {
    const publicClientConfig = {
      auth: {
        clientId: "<CLIENT-ID>",
        authority: "<AUTHORITY>",
      },

      // This hooks up the cross-platform cache into MSAL
      cache: {
        cachePlugin: new PersistenceCachePlugin(persistence),
      },
    };

    const pca = new msal.PublicClientApplication(publicClientConfig);

    // Use the public client application as required...
  }
);
```

次の表には、永続化構成のすべての引数についての説明が記載されています。

| フィールド名 | 説明 | 次の場合は必須 |
| --- | --- | --- |
| cachePath | ライブラリで読み取りと書き込みの同期に使用するロック ファイルへのパス | Windows、Mac、Linux |
| dataProtectionScope | Windows でのデータ保護範囲を指定します (現在のユーザーまたはローカル コンピューター)。 | Windows |
| serviceName | Mac または Linux (あるいはその両方) で使用されるサービス名を指定します | Mac と Linux |
| accountName | Mac または Linux (あるいはその両方) で使用されるアカウント名を指定します | Mac と Linux |
| usePlaintextFileOnLinux | LibSecret が失敗した場合に、Linux でプレーンテキストに既定で設定されるフラグ。 既定値は `false` です | Linux |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-node-migration"} -->
## Node.js アプリケーションを ADAL から MSAL に移行する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-node-migration
- Service: identity-platform
- Article date: 2021-04-26
- Summary: Active Directory認証ライブラリ (ADAL) ではなく、認証と承認に Microsoft Authentication Library (MSAL) を使用するように既存の Node.js アプリケーションを更新する方法。

[Microsoft Authentication Library for Node](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) (MSAL ノード) が、Microsoft ID プラットフォームに登録されているアプリケーションの認証と承認を有効にするために推奨される SDK になりました。 この記事では、アプリを Active Directory Authentication Library for Node (ADAL Node) から MSAL Node に移行するために必要な重要な手順について説明します。

### 前提条件

- Node バージョン 10、12、14、16、または 18。 [バージョンのサポートに関する注意事項を](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node#node-version-support)参照してください

### アプリの登録設定を更新する

ADAL ノードを使用する場合、**Azure AD v1.0 エンドポイント**を使用していた可能性があります。 ADAL から MSAL に移行するアプリは、**Azure AD v2.0 エンドポイント**に切り替える必要があります。

### MSAL をインストールしてインポートする

1. npm を使用して MSAL Node パッケージをインストールします。

```console
  npm install @azure/msal-node
```

1. その後、ご利用のコードに MSAL Node をインポートします。

```javascript
  const msal = require('@azure/msal-node');
```

1. 最後に、ADAL Node パッケージをアンインストールし、コード内のすべての参照を削除します。

```console
  npm uninstall adal-node
```

### MSAL の初期化

ADAL ノードでは、 `AuthenticationContext` オブジェクトを初期化し、さまざまな認証フローで使用できるメソッド (Web アプリの `acquireTokenWithAuthorizationCode` など) を公開します。 初期化時に必須のパラメーターは、 **機関 URI** のみです。

```javascript
var adal = require('adal-node');

var authorityURI = "https://login.microsoftonline.com/common";
var authenticationContext = new adal.AuthenticationContext(authorityURI);
```

MSAL Node には、代わりに 2 つの代替手段があります。モバイル アプリまたはデスクトップ アプリをビルドする場合は、 `PublicClientApplication` オブジェクトをインスタンス化します。 コンストラクターは、少なくとも  パラメーターを含む`clientId`を受け取ります。 指定しない場合、MSAL は既定で機関 URI を `https://login.microsoftonline.com/common` します。

```javascript
const msal = require('@azure/msal-node');

const pca = new msal.PublicClientApplication({
        auth: {
            clientId: "YOUR_CLIENT_ID"
        }
    });
```

注

v2.0 で `https://login.microsoftonline.com/common` 機関を使用する場合、ユーザーは任意のMicrosoft Entra組織または個人の Microsoft アカウント (MSA) でサインインできるようになります。 MSAL ノードで、ログインを任意のMicrosoft Entra アカウント (ADAL ノードと同じ動作) に制限する場合は、代わりに `https://login.microsoftonline.com/organizations` を使用します。

一方、Web アプリまたはデーモン アプリを構築する場合は、 `ConfidentialClientApplication` オブジェクトをインスタンス化します。 このようなアプリでは、クライアント シークレットや証明書などの *クライアント資格情報*も指定する必要があります。

```javascript
const msal = require('@azure/msal-node');

const cca = new msal.ConfidentialClientApplication({
        auth: {
            clientId: "YOUR_CLIENT_ID",
            clientSecret: "YOUR_CLIENT_SECRET"
        }
    });
```

ADAL の`PublicClientApplication`とは異なり、`ConfidentialClientApplication`と`AuthenticationContext`の両方がクライアント ID にバインドされます。 これは、アプリケーションで使用したいさまざまなクライアント ID がある場合、それぞれに対して新しい MSAL インスタンスを作成する必要があることを意味します。 詳細については、[MSAL ノードの初期化を](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/initialize-confidential-client-application.md)参照してください。

### MSAL を構成する

Microsoft ID プラットフォームでアプリを作成する場合、アプリには認証に関連する多くのパラメーターが含まれることになります。 ADAL ノードでは、 `AuthenticationContext` オブジェクトにはインスタンス化できる構成パラメーターの数が限られていますが、残りのパラメーターはコード ( *clientSecret* など) で自由にハングします。

```javascript
var adal = require('adal-node');

var authority = "https://login.microsoftonline.com/YOUR_TENANT_ID"
var validateAuthority = true,
var cache = null;

var authenticationContext = new adal.AuthenticationContext(authority, validateAuthority, cache);
```

- `authority`: トークン機関を識別する URL
- `validateAuthority`: コードが悪意のある可能性のある機関にトークンを要求できないようにする機能
- `cache`: この AuthenticationContext インスタンスで使用されるトークン キャッシュを設定します。 このパラメーターが設定されていない場合は、既定値のインメモリ キャッシュが使用されます

一方、MSAL ノードは [Configuration 型の](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_node.html#configuration)構成オブジェクトを使用します。 これには、次のプロパティが含まれます。

```javascript
const msal = require('@azure/msal-node');

const msalConfig = {
    auth: {
        clientId: "YOUR_CLIENT_ID",
        authority: "https://login.microsoftonline.com/YOUR_TENANT_ID",
        clientSecret: "YOUR_CLIENT_SECRET",
        knownAuthorities: [],
    },
    cache: {
        // your implementation of caching
    },
    system: {
        loggerOptions: { /** logging related options */ }
    }
}

const cca = new msal.ConfidentialClientApplication(msalConfig);
```

重要な違いとして、MSAL には、機関の検証を無効にするフラグがなく、機関は必ず既定で検証されます。 要求した機関は、MSAL によって、Microsoft が認識している機関の一覧、または構成で指定した機関の一覧と比較されます。 詳細については、「[構成オプション」](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-node/docs/configuration.md)を参照してください。

### MSAL API に切り替える

ADAL Node のパブリック メソッドのほとんどには、MSAL Node に同等のものがあります。

| ADAL | MSAL | メモ |
| --- | --- | --- |
| `acquireToken` | `acquireTokenSilent` | 名前が変更され、 [アカウント](https://azuread.github.io/microsoft-authentication-library-for-js/ref/modules/_azure_msal_common.html#accountinfo) オブジェクトが必要になりました |
| `acquireTokenWithAuthorizationCode` | `acquireTokenByCode` |  |
| `acquireTokenWithClientCredentials` | `acquireTokenByClientCredential` |  |
| `acquireTokenWithRefreshToken` | `acquireTokenByRefreshToken` | 有効な更新トークンの移行に役立ちます |
| `acquireTokenWithDeviceCode` | `acquireTokenByDeviceCode` | ユーザー コードの取得を抽象化するようになりました (下記参照) |
| `acquireTokenWithUsernamePassword` | `acquireTokenByUsernamePassword` |  |

ただし、ADAL Node の一部のメソッドは非推奨とされ、その一方、MSAL Node には新しいメソッドが用意されています。

| ADAL | MSAL | メモ |
| --- | --- | --- |
| `acquireUserCode` | 該当なし | `acquireTokeByDeviceCode`とマージされました (上記を参照してください) |
| 該当なし | `acquireTokenOnBehalfOf` | [OBO フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow)を抽象化する新しいメソッド |
| `acquireTokenWithClientCertificate` | 該当なし | 初期化中に証明書が割り当てられるので不要になりました ( 構成オプションを参照) |
| 該当なし | `getAuthCodeUrl` | [承認エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols#endpoints) URL の構築を抽象化する新しいメソッド |

### リソースの代わりにスコープを使用する

v1.0 と v2.0 のエンドポイントの重要な違いは、リソースへのアクセス方法に関するものです。 ADAL ノードでは、まずアプリ登録ポータルにアクセス許可を登録し、次に示すようにリソース (Microsoft Graph など) のアクセス トークンを要求します。

```javascript
authenticationContext.acquireTokenWithAuthorizationCode(
    req.query.code,
    redirectUri,
    resource, // e.g. 'https://graph.microsoft.com'
    clientId,
    clientSecret,
    function (err, response) {
        // do something with the authentication response
    }
);
```

MSAL ノードでは、 **v2.0** エンドポイントのみがサポートされます。 v2.0 エンドポイントでは、 *スコープ中心の* モデルを使用してリソースにアクセスします。 したがって、リソースのアクセス トークンを要求するときは、そのリソースのスコープも指定する必要があります。

```javascript
const tokenRequest = {
    code: req.query.code,
    scopes: ["https://graph.microsoft.com/User.Read"],
    redirectUri: REDIRECT_URI,
};

pca.acquireTokenByCode(tokenRequest).then((response) => {
    // do something with the authentication response
}).catch((error) => {
    console.log(error);
});
```

スコープ中心モデルの利点の 1 つは、動的スコープを使用 *できることです*。 v1.0 を使用してアプリケーションをビルドする場合、ユーザーがログイン時に同意するためにアプリケーションに必要なアクセス許可の完全なセット ( *静的スコープ*と呼ばれます) を登録する必要があります。 v2.0 では、スコープ パラメーターを使用して、必要な時点でアクセス許可を要求できます (そのため、 *動的スコープ*)。 これにより、ユーザーはスコープに **増分同意** を提供できます。 最初ユーザーにはアプリケーションへのサインインだけを行わせ、どのような種類のアクセスも必要としない場合、そうすることができます。 その後、ユーザーの予定表を読み取る機能が必要になった場合は、acquireToken メソッドで予定表のスコープを要求してユーザーの同意を得ることができます。 詳細については、「[リソースとスコープ](https://github.com/AzureAD/microsoft-authentication-library-for-js/blob/dev/lib/msal-browser/docs/resources-and-scopes.md)」を参照してください。

### コールバックの代わりに Promise を使用する

ADAL Node では、認証が成功し、応答が取得された後に、すべての操作にコールバックが使用されます。

```javascript
var context = new AuthenticationContext(authorityUrl, validateAuthority);

context.acquireTokenWithClientCredentials(resource, clientId, clientSecret, function(err, response) {
    if (err) {
        console.log(err);
    } else {
        // do something with the authentication response
    }
});
```

MSAL Node では、Promise が代わりに使用されます。

```javascript
    const cca = new msal.ConfidentialClientApplication(msalConfig);

    cca.acquireTokenByClientCredential(tokenRequest).then((response) => {
        // do something with the authentication response
    }).catch((error) => {
        console.log(error);
    });
```

ES8 に付属する **async/await** 構文を使用することもできます。

```javascript
    try {
        const authResponse = await cca.acquireTokenByCode(tokenRequest);
    } catch (error) {
        console.log(error);
    }
```

### ログの有効化

ADAL Node では、コード内の任意の場所でログを別途構成します。

```javascript
var adal = require('adal-node');

//PII or OII logging disabled. Default Logger does not capture any PII or OII.
adal.logging.setLoggingOptions({
  log: function (level, message, error) {
    console.log(message);

    if (error) {
        console.log(error);
    }
  },
  level: logging.LOGGING_LEVEL.VERBOSE, // provide the logging level
  loggingWithPII: false  // Determine if you want to log personal identification information. The default value is false.
});
```

MSAL Node では、ログは構成オプションの一部であり、MSAL Node インスタンスの初期化で作成されます。

```javascript
const msal = require('@azure/msal-node');

const msalConfig = {
    auth: {
        // authentication related parameters
    },
    cache: {
        // cache related parameters
    },
    system: {
        loggerOptions: {
            loggerCallback(loglevel, message, containsPii) {
                console.log(message);
            },
            piiLoggingEnabled: false,
            logLevel: msal.LogLevel.Verbose,
        }
    }
}

const cca = new msal.ConfidentialClientApplication(msalConfig);
```

### トークンのキャッシュの有効化

ADAL Node では、インメモリ トークン キャッシュをインポートするオプションがありました。 トークン キャッシュは、 `AuthenticationContext` オブジェクトを初期化するときにパラメーターとして使用されます。

```javascript
var MemoryCache = require('adal-node/lib/memory-cache');

var cache = new MemoryCache();
var authorityURI = "https://login.microsoftonline.com/common";

var context = new AuthenticationContext(authorityURI, true, cache);
```

MSAL Node は、既定ではインメモリ トークン キャッシュを使用します。 明示的にインポートする必要はありません。メモリ内トークン キャッシュは、 `ConfidentialClientApplication` クラスと `PublicClientApplication` クラスの一部として公開されます。

```javascript
const msalTokenCache = publicClientApplication.getTokenCache();
```

重要なことは、ADAL Node を使用した以前のトークン キャッシュは、キャッシュ スキーマに互換性がないため、MSAL Node に転送できないということです。 ただし、MSAL Node で ADAL Node を使用して以前にアプリで取得した有効な更新トークンを使用することができます。 詳細については、 更新トークン に関するセクションを参照してください。

独自のキャッシュ **プラグイン**を提供することで、キャッシュをディスクに書き込むこともできます。 キャッシュ プラグインは、インターフェイス `ICachePlugin`を実装する必要があります。 ログと同様に、キャッシュは構成オプションの一部であり、MSAL Node インスタンスの初期化で作成されます。

```javascript
const msal = require('@azure/msal-node');

const msalConfig = {
    auth: {
        // authentication related parameters
    },
    cache: {
        cachePlugin // your implementation of cache plugin
    },
    system: {
        // logging related options
    }
}

const msalInstance = new ConfidentialClientApplication(msalConfig);
```

キャッシュ プラグインの例は、下のように実装できます。

```javascript
const fs = require('fs');

// Call back APIs which automatically write and read into a .json file - example implementation
const beforeCacheAccess = async (cacheContext) => {
    cacheContext.tokenCache.deserialize(await fs.readFile(cachePath, "utf-8"));
};

const afterCacheAccess = async (cacheContext) => {
    if(cacheContext.cacheHasChanged) {
        await fs.writeFile(cachePath, cacheContext.tokenCache.serialize());
    }
};

// Cache Plugin
const cachePlugin = {
    beforeCacheAccess,
    afterCacheAccess
};
```

デスクトップ [アプリなどのパブリック クライアント アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-client-applications) を開発している場合、 [Microsoft Authentication Extensions for Node](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/extensions/msal-node-extensions) には、クライアント アプリケーションがクロスプラットフォーム トークン キャッシュのシリアル化と永続化を実行するための安全なメカニズムが用意されています。 サポートされているプラットフォームは、Windows、Mac、Linux です。

注

[Microsoft Authentication Extensions for Node](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/extensions/msal-node-extensions) は、スケーリングとパフォーマンスの問題につながる可能性があるため、Web アプリケーションには推奨 **されません** 。 代わりに、Web アプリではセッションでキャッシュを永続化することをお勧めします。

### 更新トークンに関するロジックを削除する

ADAL ノードでは、更新トークン (RT) が公開されています。これにより、トークンをキャッシュし、 `acquireTokenWithRefreshToken` メソッドを使用して、これらのトークンの使用に関するソリューションを開発できます。 RT が特に関連する一般的なシナリオは次のとおりです。

- ユーザーが接続されなくなったダッシュボードの更新などのアクションをユーザーの代わりに行う実行時間の長いサービス。
- クライアントが RT を Web サービスに渡せるようにする WebFarm シナリオ (キャッシュはサーバー側ではなく、クライアント側で行われます (暗号化された Cookie))。

MSAL Node とその他の MSAL は、セキュリティ上の理由により、更新トークンは公開されません。 代わりに、MSAL がトークンの更新を処理します。 そのため、これに関するロジックを構築する必要がなくなりました。 ただし、ADAL ノードのキャッシュから以前に取得した (有効な) 更新トークンを使用して、MSAL ノードで新しいトークンセットを取得 **できます** 。 これを行うために、MSAL Node は ADAL ノードの`acquireTokenByRefreshToken`メソッドと同等の`acquireTokenWithRefreshToken`を提供します。

```javascript
var msal = require('@azure/msal-node');

const config = {
    auth: {
        clientId: "ENTER_CLIENT_ID",
        authority: "https://login.microsoftonline.com/ENTER_TENANT_ID",
        clientSecret: "ENTER_CLIENT_SECRET"
    }
};

const cca = new msal.ConfidentialClientApplication(config);

const refreshTokenRequest = {
    refreshToken: "", // your previous refresh token here
    scopes: ["https://graph.microsoft.com/.default"],
    forceCache: true,
};

cca.acquireTokenByRefreshToken(refreshTokenRequest).then((response) => {
    console.log(response);
}).catch((error) => {
    console.log(error);
});
```

詳細については、 [MSAL ノードのサンプル](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/samples/msal-node-samples)を参照してください。

注

上記のように MSAL ノードの `acquireTokenByRefreshToken` メソッドを使用して、まだ有効な更新トークンを使用して新しいトークンセットを取得したら、古い ADAL ノード トークン キャッシュを破棄することをお勧めします。

### エラーと例外を処理する

MSAL ノードを使用する場合、発生する可能性がある最も一般的なエラーの種類は、 `interaction_required` エラーです。 多くの場合、このエラーは対話型トークン取得のプロンプトを開始するだけで解決されます。 たとえば、 `acquireTokenSilent`を使用する場合、キャッシュされた更新トークンがない場合、MSAL Node はアクセス トークンをサイレント モードで取得できません。 同様に、アクセスしようとしている Web API には [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) ポリシーが設定されている可能性があり、ユーザーは [多要素認証 (MFA)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) を実行する必要があります。 このような場合は、`acquireTokenByCode`をトリガーして`interaction_required`エラーを処理することで、ユーザーにMFAの入力を求め、解決できるようになります。

さらに、発生する可能性があるもう 1 つの一般的なエラーは `consent_required`です。これは、保護されたリソースのアクセス トークンを取得するために必要なアクセス許可がユーザーによって同意されていない場合に発生します。 `interaction_required`と同様に、`consent_required` エラーの解決策は、多くの場合、`acquireTokenByCode` メソッドを使用して対話型のトークン取得プロンプトを開始します。

### アプリを実行する

変更が完了したら、アプリを実行し、認証シナリオをテストします。

```console
npm start
```

### 例: ADAL Node または MSAL Node を使用したトークンの取得

下のスニペットは、Express.js フレームワークの機密クライアント Web アプリを示しています。 ユーザーが認証ルート `/auth` にヒットし、`/redirect` ルートを介してMicrosoft Graphのアクセス トークンを取得し、そのトークンの内容を表示するときにサインインを実行します。

| ADAL Node を使用する場合 | MSAL Node を使用する場合 |
| --- | --- |
| ```javascript<br>// Import dependencies<br>var express = require('express');<br>var crypto = require('crypto');<br>var adal = require('adal-node');<br><br>// Authentication parameters<br>var clientId = 'Enter_the_Application_Id_Here';<br>var clientSecret = 'Enter_the_Client_Secret_Here';<br>var tenant = 'Enter_the_Tenant_Info_Here';<br>var authorityUrl = 'https://login.microsoftonline.com/' + tenant;<br>var redirectUri = 'http://localhost:3000/redirect';<br>var resource = 'https://graph.microsoft.com';<br><br>// Configure logging<br>adal.Logging.setLoggingOptions({<br>    log: function (level, message, error) {<br>        console.log(message);<br>    },<br>    level: adal.Logging.LOGGING_LEVEL.VERBOSE,<br>    loggingWithPII: false<br>});<br><br>// Auth code request URL template<br>var templateAuthzUrl = 'https://login.microsoftonline.com/'<br>    + tenant + '/oauth2/authorize?response_type=code&client_id='<br>    + clientId + '&redirect_uri=' + redirectUri<br>    + '&state=<state>&resource=' + resource;<br><br>// Initialize express<br>var app = express();<br><br>// State variable persists throughout the app lifetime<br>app.locals.state = "";<br><br>app.get('/auth', function(req, res) {<br><br>    // Create a random string to use against XSRF<br>    crypto.randomBytes(48, function(ex, buf) {<br>        app.locals.state = buf.toString('base64')<br>            .replace(/\//g, '_')<br>            .replace(/\+/g, '-');<br><br>        // Construct auth code request URL<br>        var authorizationUrl = templateAuthzUrl<br>            .replace('<state>', app.locals.state);<br><br>        res.redirect(authorizationUrl);<br>    });<br>});<br><br>app.get('/redirect', function(req, res) {<br>    // Compare state parameter against XSRF<br>    if (app.locals.state !== req.query.state) {<br>        res.send('error: state does not match');<br>    }<br><br>    // Initialize an AuthenticationContext object<br>    var authenticationContext =<br>        new adal.AuthenticationContext(authorityUrl);<br><br>    // Exchange auth code for tokens<br>    authenticationContext.acquireTokenWithAuthorizationCode(<br>        req.query.code,<br>        redirectUri,<br>        resource,<br>        clientId,<br>        clientSecret,<br>        function(err, response) {<br>            res.send(response);<br>        }<br>    );<br>});<br><br>app.listen(3000, function() {<br>    console.log(`listening on port 3000!`);<br>});<br>``` | ```javascript<br>// Import dependencies<br>const express = require("express");<br>const msal = require('@azure/msal-node');<br><br>// Authentication parameters<br>const config = {<br>    auth: {<br>        clientId: "Enter_the_Application_Id_Here",<br>        authority: "https://login.microsoftonline.com/Enter_the_Tenant_Info_Here",<br>        clientSecret: "Enter_the_Client_Secret_Here"<br>    },<br>    system: {<br>        loggerOptions: {<br>            loggerCallback(loglevel, message, containsPii) {<br>                console.log(message);<br>            },<br>            piiLoggingEnabled: false,<br>            logLevel: msal.LogLevel.Verbose,<br>        }<br>    }<br>};<br><br>const REDIRECT_URI = "http://localhost:3000/redirect";<br><br>// Initialize MSAL Node object using authentication parameters<br>const cca = new msal.ConfidentialClientApplication(config);<br><br>// Initialize express<br>const app = express();<br><br>app.get('/auth', (req, res) => {<br><br>    // Construct a request object for auth code<br>    const authCodeUrlParameters = {<br>        scopes: ["user.read"],<br>        redirectUri: REDIRECT_URI,<br>    };<br><br>    // Request auth code, then redirect<br>    cca.getAuthCodeUrl(authCodeUrlParameters)<br>        .then((response) => {<br>            res.redirect(response);<br>        }).catch((error) => res.send(error));<br>});<br><br>app.get('/redirect', (req, res) => {<br><br>    // Use the auth code in redirect request to construct<br>    // a token request object<br>    const tokenRequest = {<br>        code: req.query.code,<br>        scopes: ["user.read"],<br>        redirectUri: REDIRECT_URI,<br>    };<br><br>    // Exchange the auth code for tokens<br>    cca.acquireTokenByCode(tokenRequest)<br>        .then((response) => {<br>            res.send(response);<br>        }).catch((error) => res.status(500).send(error));<br>});<br><br>app.listen(3000, () =><br>    console.log(`listening on port 3000!`));<br>``` |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-overview"} -->
## Microsoft Authentication Library (MSAL) の概要 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview
- Service: identity-platform
- Article date: 2024-11-13
- Summary: Microsoft Authentication Library (MSAL) を使用すると、アプリケーション開発者はセキュリティで保護された Web API を呼び出すためにトークンを取得できます。 これらの Web API には、Microsoft Graph、その他の Microsoft API、サード パーティの Web API、または、独自の Web API が含まれます。 MSAL は、複数のアプリケーション アーキテクチャとプラットフォームをサポートします。

Microsoft Authentication Library (MSAL) を使用すると、ユーザーを認証し、セキュリティで保護された Web API にアクセスするため、開発者は Microsoft ID プラットフォームから[セキュリティ トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-glossary#security-token)を取得できます。 これは、Microsoft Graph、その他の Microsoft API、サード パーティの Web API、または、独自の Web API へのセキュリティで保護されたアクセスを提供するために使用できます。 MSAL は、.NET、JavaScript、Java、Python、Android、iOS などの、さまざまなアプリケーション アーキテクチャとプラットフォームをサポートします。

MSAL では、セキュリティ トークンを取得する複数の方法が、多くのプラットフォームで一貫した API を使って提供されています。 MSAL の使用には次のような利点があります。

- アプリケーションでプロトコルに対して OAuth ライブラリまたはコードを直接使用する必要はありません。
- ユーザーやアプリケーション (プラットフォームに適用できるとき) の代わりにトークンを取得できます。
- 自動的にトークン キャッシュを維持し、有効期限が近づいたトークンの更新を処理します。
- どの対象ユーザーがアプリケーションにサインインするかを指定するのに役立ちます。 サインイン対象ユーザーには、個人の Microsoft アカウント、Microsoft Entra 外部 ID を持つソーシャル ID、職場、学校、ソブリン クラウドや国内クラウドのユーザーを含めることができます。
- 構成ファイルからアプリケーションを設定する作業を支援します。
- アクション可能な例外、ログ記録、テレメトリを公開することでアプリの問題解決を支援します。

| アプリケーションの種類とシナリオ | チュートリアル |
| --- | --- |
| シングル ページ アプリ (JavaScript) | [チュートリアル: React シングルページ アプリケーション (SPA) にユーザーをサインインさせる](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-single-page-app-react-prepare-app) |
| Web アプリケーション | [チュートリアル: ASP.NET Core Web アプリケーションにユーザーをサインインさせる](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-app-dotnet-prepare-app) |
| Web API | [チュートリアル: ASP.NET Core API に保護されたエンドポイントを実装する](https://learn.microsoft.com/ja-jp/entra/identity-platform/tutorial-web-api-dotnet-register-app) |
| モバイルおよびネイティブ アプリケーション | [モバイル アプリケーションが、対話形式でサインインしたユーザーの代わりに Web API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-mobile-app-configuration) |
| デーモンとサーバー側アプリケーション | [デスクトップ/サービス デーモン アプリケーションがそれ自体の代理として Web API を呼び出す](https://learn.microsoft.com/ja-jp/entra/identity-platform/scenario-daemon-app-configuration) |

### MSAL 言語とフレームワーク

次のドキュメントを参照し、さまざまな MSAL ライブラリについてさらに学習できます。

| MSAL のドキュメント | MSAL ライブラリ | サポートされているプラットフォームとフレームワーク |
| --- | --- | --- |
| [MSAL.NET](https://learn.microsoft.com/ja-jp/entra/msal/dotnet/) | [MSAL.NET](https://github.com/AzureAD/microsoft-authentication-library-for-dotnet) | .NET Framework、.NET、.NET MAUI、WINUI |
| [MSAL for ANDROID](https://github.com/AzureAD/microsoft-authentication-library-for-android/tree/dev/docs) | [MSAL for ANDROID](https://github.com/AzureAD/microsoft-authentication-library-for-android) | Android |
| [MSAL アンギュラー](https://learn.microsoft.com/ja-jp/javascript/api/@azure/msal-angular/) | [MSAL アンギュラー](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-angular) | Angular と Angular.js のフレームワークを使用したシングルページ アプリ |
| [iOS および macOS 用の MSAL](https://github.com/AzureAD/microsoft-authentication-library-for-objc/tree/dev/docs) | [iOS および macOS 用の MSAL](https://github.com/AzureAD/microsoft-authentication-library-for-objc) | iOS と macOS |
| [MSAL Java](https://learn.microsoft.com/ja-jp/entra/msal/java/) | [MSAL Java](https://github.com/AzureAD/microsoft-authentication-library-for-java) | Windows、macOS、Linux |
| [MSAL.js](https://learn.microsoft.com/ja-jp/javascript/api/overview/msal-overview) | [MSAL.js](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-browser) | Vue.js、Ember.js、Durandal.js など、JavaScript と TypeScript のフレームワーク |
| [MSAL ノード](https://learn.microsoft.com/ja-jp/javascript/api/%40azure/msal-node/) | [MSAL ノード](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node) | Express を使用した Web アプリ、Electron を使用したデスクトップ アプリ、クロスプラットフォーム コンソール アプリ |
| [MSAL Python](https://learn.microsoft.com/ja-jp/entra/msal/python/) | [MSAL Python](https://github.com/AzureAD/microsoft-authentication-library-for-python) | Windows、macOS、Linux |
| [MSAL React](https://learn.microsoft.com/ja-jp/javascript/api/%40azure/msal-react/) | [MSAL React](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-react) | React と React ベースのライブラリ (Next.js、Gatsby.js) を使用したシングルページ アプリ |
| [MSAL Go (プレビュー)](https://learn.microsoft.com/ja-jp/entra/msal/go/) | [MSAL Go (プレビュー)](https://github.com/AzureAD/microsoft-authentication-library-for-go) | Windows、macOS、Linux |

重要

Active Directory 認証ライブラリ (ADAL) のサポートは終了しました。 お客様は、アプリケーションが MSAL に移行されていることを確認する必要があります。 MSAL は、Microsoft の個人用アカウントと職場アカウントを 1 つの認証システムに一体化したものである Microsoft ID プラットフォーム (v2.0) エンドポイントと統合します。 ADAL は、個人用アカウントをサポートしていない v1.0 エンドポイントと統合します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/msal-shared-devices"} -->
## 共有デバイス モードの概要 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-shared-devices
- Service: identity-platform / workforce
- Article date: 2025-05-09
- Summary: Microsoft Entra ID の共有デバイス モード機能を使用して、現場担当者のデバイス共有を有効にする方法について説明します。

**適用対象**: [Image: 次の内容が従業員テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] ワークフォース テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

共有デバイス モード (SDM) は、組織が iOS、iPadOS、または Android デバイスを複数の従業員の間で共有して使えるように構成できる Microsoft Entra ID 機能です。これは、現場作業者の環境で一般的なプラクティスです。 SDM を使用すると、従業員は 1 回サインインすれば、他の従業員のデータにアクセスすることなく、サポート対象のすべてのアプリケーションのデータにアクセスできます。 シフトまたはタスクの完了後に従業員がサインアウトすると、デバイスとサポートされているすべてのアプリケーションから自動的にサインアウトされ、デバイスは次のユーザーに対応できるようになります。

### 共有デバイスモードを使用する理由

従業員が共有のデバイス間で組織のアプリを使用できるようにするには、開発者は、合理化され、セキュリティで保護されたユーザー エクスペリエンスを促進する必要があります。 従業員が共有プールからデバイスを選択し、1 つのジェスチャでサインインして、シフト中にそのデバイスを "自分のデバイス" にできるようにする必要があります。 従業員はシフトの終了時に、別のジェスチャを実行して、デバイスを共有デバイス プールに返す前に、デバイスからグローバルにサインアウトすることができます。 共有デバイス モードを有効にすると、次のような利点があります。

- **シングル サインオン:** 共有デバイス モードをサポートするいずれかのアプリにユーザーがサインインし、資格情報を再入力することなく、SDM でサポートされている他のすべてのアプリ間でシームレスな認証を取得できるようにします。 共有デバイス上の初回実行エクスペリエンス (FRE) 画面からユーザーを除外します。
- **シングル サインアウト:** SDM でサポートされている各アプリケーションから個別にサインアウトする必要なく、ユーザーがデバイスからサインアウトできるようにします。 サインアウトすると、提供されたアプリでキャッシュされたユーザー データが確実にクリーンアップされるので、ユーザーは自分のデータが次のデバイス ユーザーに表示されることはないことを確信できます。
- **条件付きアクセス ポリシーによるセキュリティのサポート:** 共有デバイスで特定の条件付きアクセス ポリシーをターゲットにする機能を管理者に提供し、共有デバイスが内部コンプライアンス標準を満たしている場合にのみ従業員が会社のデータにアクセスできるようにします。

### サポートされているシナリオとサポートされていないシナリオ

共有デバイス モード機能では、次のシナリオがサポートされます。

- ユーザーは、Microsoft Entra ID 資格情報を使用して Android または iOS/iPadOS デバイス上の共有デバイス モードでサポートされているアプリケーション (基幹業務アプリ、サード パーティのランチャー アプリ、または Microsoft アプリ) にサインインし、デバイス上のすべての共有デバイス モードでサポートされているアプリに自動的にサインオンします。
- ユーザーは、Android または iOS/iPadOS デバイス上の共有デバイス モードでサポートされているアプリケーション (基幹業務、サード パーティのランチャー アプリ、または Microsoft アプリ) からサインアウトし、デバイス上のサポートされているすべての SDM からログアウトします。
- 管理者が、デバイスをモバイル デバイス管理 (MDM) に登録し、準拠する必要がある許可を使用して条件付きアクセス ポリシーを設定した場合、ユーザーは、そのデバイスが準拠している場合にのみ SDM でサポートされるアプリケーションにサインインできます。

注記

ユーザーが共有デバイス モードをサポートしていないアプリケーションにサインインした場合は、シングル サインオンとシングル サインアウトの利点は得られません。

### 共有デバイス モードの実装における管理者と開発者の役割

共有デバイス モード機能を活用するには、クラウド デバイス管理者とアプリケーション開発者が協力して作業します。

**デバイス管理者**は、手動でデバイスを共有デバイス モードに設定するか、Microsoft Intune のようなモバイル デバイス管理 (MDM) プロバイダーを使用して、デバイスの共有を準備します。 推奨されるオプションは、MDM を使用することです。これにより、ゼロタッチ プロビジョニングを使用して共有デバイス モードで大規模にデバイスをセットアップできます。 MDM は、共有デバイス モードが有効になっているデバイスに Microsoft Authenticator アプリをプッシュするように構成されています。 iOS デバイスでは、MDM では、共有デバイス モードに必要な Microsoft Enterprise シングル サインオン (SSO) プラグインも有効になります。

次のガイドでは、Intune を使用して共有デバイス モードでデバイスを設定する方法について詳しく説明しています。

- [Android Enterprise 専用デバイスの Intune 登録を設定する](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/android-kiosk-enroll)
- [共有デバイス モードで iOS デバイスと iPadOS デバイスの登録を設定する](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/automated-device-enrollment-shared-device-mode)。

サポートされているサード パーティの MDM を使用して、共有デバイス モードでデバイスを設定することもできます。 Android で共有デバイス モードをサポートするサード パーティ製 MDM の一覧については、「[共有デバイス モードをサポートするサード パーティ製 MDM](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-android-shared-devices#third-party-mdms-that-support-shared-device-mode)」を参照してください。

手動セットアップは、パイロット プログラムや小規模なデプロイに便利なツールです。 クラウド デバイス管理者のアクセス権が必要で、各デバイスで実行する必要があります。

**アプリケーション開発者**は、Microsoft 認証ライブラリ (MSAL) を使用して、[単一アカウントのパブリック クライアント アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-multi-account#single-account-public-client-application)に共有デバイス モードのサポートを追加します。 MSAL を使用すると、デバイスの状態とデバイス上のユーザーのシグナルに基づいてアプリの動作を変更できます。 たとえば、アプリケーションは、アプリケーションが使用されるたびにデバイス上のユーザーの状態を確認し、ユーザーが変更された場合は前のユーザーのデータをクリアします。 ユーザーの変更時に、前のユーザーのデータがクリアされ、アプリケーションで表示されているキャッシュされたデータがすべて削除されるようにする必要があります。

開発者は、すべてのデータ損失防止シナリオをサポートするために、[Intune App SDK](https://learn.microsoft.com/ja-jp/mem/intune/developer/app-sdk) と統合することも強くお勧めします。 Intune App SDK を使用すると、開発者はアプリケーションで [Intune App Protection ポリシー](https://learn.microsoft.com/ja-jp/mem/intune/apps/app-protection-policy)をサポートできます。 Microsoft は、Intune の [選択的ワイプ](https://learn.microsoft.com/ja-jp/mem/intune/developer/app-sdk-android-phase5#selective-wipe)機能と統合し、サインアウト時に [iOS でユーザー登録を解除する](https://learn.microsoft.com/ja-jp/mem/intune/developer/app-sdk-ios-phase1#deregister-user-accounts)ことを推奨します。

共有デバイス モードのサポートは、アプリケーションの機能のアップグレードとして考える必要があります。また、同じデバイスを複数のユーザー間で使用する環境への導入を拡大できます。

注記

共有デバイス モードをサポートする Microsoft アプリケーションの場合、共有デバイス モードが有効なデバイスにインストールする以外、他に変更を加える必要はありません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/multi-service-web-app-access-microsoft-graph-as-app"} -->
## チュートリアル - Web アプリからアプリとして Microsoft Graph にアクセスする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-access-microsoft-graph-as-app
- Service: identity-platform
- Article date: 2024-02-17
- Summary: このチュートリアルでは、マネージド ID を使って、Azure App Service で実行されている Web アプリから Microsoft Graph のデータにアクセスする方法について説明します。

Azure App Service で実行されている Web アプリから Microsoft Graph にアクセスする方法について説明します。

[Image: Microsoft Graph へのアクセスを示す図]

Web アプリに代わって Microsoft Graph を呼び出すとします。 Web アプリにデータへのアクセスを提供するより安全な方法は、[システム割り当てマネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) を使用することです。 Microsoft Entra ID のマネージド ID を使用すると、App Service は、アプリの資格情報を必要とせずに、ロールベースのアクセス制御 (RBAC) を使用してリソースにアクセスできます。 マネージド ID を対象の Web アプリに割り当てると、Azure では証明書の作成と配布が行われます。 シークレットまたはアプリの資格情報の管理について心配する必要はありません。

このチュートリアルでは、次の操作を行います。

- Web アプリ上でシステム割り当てマネージド ID を作成する。
- Microsoft Graph API のアクセス許可をマネージド ID に追加する。
- マネージド ID を使用して Web アプリから Microsoft Graph を呼び出す。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

### 前提条件

- [App Service の認証および承認モジュールが有効になっている](https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-authentication-app-service) Azure App Service で実行されている Web アプリケーション。

### アプリのマネージド ID を有効にする

Visual Studio を使用して Web アプリを作成して発行すると、アプリでマネージド ID が有効になります。 App Service で、左ペインの **[ID]** を選択し、 **[システム割り当て済み]** を選択します。 **[状態]** が **[オン]** に設定されていることを確認します。 そうでない場合は、 **[保存]** を選択し、 **[はい]** を選択して、システム割り当てマネージド ID を有効にします。 マネージド ID が有効になると、状態が **[オン]** に設定され、オブジェクト ID が使用可能になります。

**[オブジェクト ID]** の値をメモしておきます。これは、次の手順で必要になります。

[Image: システム割り当て ID を示すスクリーンショット。]

### Microsoft Graph へのアクセス権を付与する

Microsoft Graph にアクセスする場合、実行する操作に対する適切なアクセス許可がマネージド ID に与えられている必要があります。 現時点では、Microsoft Entra 管理センターを通してこのようなアクセス許可を割り当てるオプションはありません。 次のスクリプトを使用すると、要求された Microsoft Graph API のアクセス許可をマネージド ID サービス プリンシパル オブジェクトに追加できます。

## [PowerShellの](#tab/azure-powershell)
```powershell
# Install the module.
# Install-Module Microsoft.Graph -Scope CurrentUser

# The tenant ID
$TenantId = "aaaabbbb-0000-cccc-1111-dddd2222eeee"

# The name of your web app, which has a managed identity.
$webAppName = "SecureWebApp-20201106120003" 
$resourceGroupName = "SecureWebApp-20201106120003ResourceGroup"

# The name of the app role that the managed identity should be assigned to.
$appRoleName = "User.Read.All"

# Get the web app's managed identity's object ID.
Connect-AzAccount -Tenant $TenantId
$managedIdentityObjectId = (Get-AzWebApp -ResourceGroupName $resourceGroupName -Name $webAppName).identity.principalid

Connect-MgGraph -TenantId $TenantId -Scopes 'Application.Read.All','AppRoleAssignment.ReadWrite.All'

# Get Microsoft Graph app's service principal and app role.
$serverApplicationName = "Microsoft Graph"
$serverServicePrincipal = (Get-MgServicePrincipal -Filter "DisplayName eq '$serverApplicationName'")
$serverServicePrincipalObjectId = $serverServicePrincipal.Id

$appRoleId = ($serverServicePrincipal.AppRoles | Where-Object {$_.Value -eq $appRoleName }).Id

# Assign the managed identity access to the app role.
New-MgServicePrincipalAppRoleAssignment `
    -ServicePrincipalId $managedIdentityObjectId `
    -PrincipalId $managedIdentityObjectId `
    -ResourceId $serverServicePrincipalObjectId `
    -AppRoleId $appRoleId
```

## [Azure CLI](#tab/azure-cli)
```azurecli
az login

webAppName="SecureWebApp-20201106120003"

spId=$(az resource list -n $webAppName --query [*].identity.principalId --out tsv)

graphResourceId=$(az ad sp list --display-name "Microsoft Graph" --query [0].id --out tsv)

appRoleId=$(az ad sp list --display-name "Microsoft Graph" --query "[0].appRoles[?value=='User.Read.All' && contains(allowedMemberTypes, 'Application')].id" --output tsv)

uri=https://graph.microsoft.com/v1.0/servicePrincipals/$spId/appRoleAssignments

body="{'principalId':'$spId','resourceId':'$graphResourceId','appRoleId':'$appRoleId'}"

az rest --method post --uri $uri --body $body --headers "Content-Type=application/json"
```

---

スクリプトを実行した後、[Microsoft Entra 管理センター](https://entra.microsoft.com)で、要求された API のアクセス許可がマネージド ID に割り当てられていることを確認できます。

**[アプリケーション]** に移動してから、**[エンタープライズ アプリケーション]** を選択します。 このペインには、テナント内のすべてのサービス プリンシパルが表示されます。 "アプリケーションの種類 == マネージド ID" の**フィルターを追加**し、マネージド ID のサービス プリンシパルを選択します。

このチュートリアルに従っている場合は、同じ表示名 (例: SecureWebApp2020094113531) の 2 つのサービス プリンシパルがあります。 "**ホームページ URL**" を持つサービス プリンシパルは、対象のテナント内の Web アプリを表します。 **[マネージド ID]** に表示されるサービス プリンシパルには、一覧に*ホームページ URL* を含める必要は "ありません"。また、**オブジェクト ID** は、**前の手順**のマネージド ID のオブジェクト ID の値に一致する必要があります。

マネージド ID のサービス プリンシパルを選択します。

[Image: [すべてのアプリケーション] オプションを示すスクリーンショット。]

**[概要]** で **[アクセス許可]** を選択すると、Microsoft Graph に対する追加のアクセス許可が表示されます。

[Image: [アクセス許可] ペインを示すスクリーンショット。]

### Microsoft Graph の呼び出し

## [C#](#tab/programming-language-csharp)
[ChainedTokenCredential](https://learn.microsoft.com/ja-jp/dotnet/api/azure.identity.chainedtokencredential) クラス、[ManagedIdentityCredential](https://learn.microsoft.com/ja-jp/dotnet/api/azure.identity.managedidentitycredential) クラス、および [EnvironmentCredential](https://learn.microsoft.com/ja-jp/dotnet/api/azure.identity.environmentcredential) クラスは、Microsoft Graph に対する要求をコードで承認するためにトークン資格情報を取得する際に使用されます。 [ChainedTokenCredential](https://learn.microsoft.com/ja-jp/dotnet/api/azure.identity.chainedtokencredential) クラスのインスタンスを作成します。これにより、トークンを取得するために App Service 環境でのマネージド ID、または開発環境変数が使用され、そのトークンがサービス クライアントにアタッチされます。 次のコード例では、認証済みのトークン資格情報を取得し、それを使用して、グループ内のユーザーを取得するサービス クライアント オブジェクトを作成します。

このコードをサンプル アプリケーションの一部として見る場合は、[GitHub 上のサンプル](https://github.com/Azure-Samples/ms-identity-easyauth-dotnet-storage-graphapi/tree/main/3-WebApp-graphapi-managed-identity)を参照してください。

#### Microsoft.Identity.Web.GraphServiceClient クライアント ライブラリ パッケージをインストールする

.NET コマンド ライン インターフェイス (CLI) または Visual Studio のパッケージ マネージャー コンソールを使って、[Microsoft.Graph](https://www.nuget.org/packages/Microsoft.Graph/) と [Microsoft.Identity.Web.GraphServiceClient NuGet](https://www.nuget.org/packages/Microsoft.Identity.Web.GraphServiceClient) パッケージをプロジェクトにインストールします。

##### .NET コマンドライン インターフェイス (CLI)

コマンド ラインを開き、プロジェクト ファイルが含まれているディレクトリに切り替えます。

インストール コマンドを実行します。

```dotnetcli
dotnet add package Microsoft.Identity.Web.GraphServiceClient
dotnet add package Microsoft.Graph
```

##### パッケージ マネージャー コンソール

Visual Studio でプロジェクトまたはソリューションを開き、 **[ツール]**&gt;**[NuGet パッケージ マネージャー]**&gt;**[パッケージ マネージャー コンソール]** コマンドを使用してコンソールを開きます。

インストール コマンドを実行します。

```powershell
Install-Package Microsoft.Identity.Web.GraphServiceClient
Install-Package Microsoft.Graph
```

#### 例

```csharp
using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Extensions.Logging;
using Microsoft.Graph;
using Azure.Identity;

...

public IList<MSGraphUser> Users { get; set; }

public async Task OnGetAsync()
{
    // Create the Graph service client with a ChainedTokenCredential which gets an access
    // token using the available Managed Identity or environment variables if running
    // in development.
    var credential = new ChainedTokenCredential(
        new ManagedIdentityCredential(),
        new EnvironmentCredential());

    string[] scopes = new[] { "https://graph.microsoft.com/.default" };

    var graphServiceClient = new GraphServiceClient(
        credential, scopes);

    List<MSGraphUser> msGraphUsers = new List<MSGraphUser>();
    try
    {
        //var users = await graphServiceClient.Users.Request().GetAsync();
        var users = await graphServiceClient.Users.GetAsync();
        foreach (var u in users.Value)
        {
            MSGraphUser user = new MSGraphUser();
            user.userPrincipalName = u.UserPrincipalName;
            user.displayName = u.DisplayName;
            user.mail = u.Mail;
            user.jobTitle = u.JobTitle;

            msGraphUsers.Add(user);
        }
    }
    catch (Exception ex)
    {
        string msg = ex.Message;
    }

    Users = msGraphUsers;
}
```

## [Node.js](#tab/programming-language-nodejs)
@azure/identity パッケージの `DefaultAzureCredential` クラスは、Azure Storage に対する要求をコードで承認するために使用するトークン資格情報を取得する際に使用されます。 `DefaultAzureCredential` クラスのインスタンスを作成します。これは、マネージド ID を使用し、トークンを取得してサービス クライアントにアタッチします。 次のコード例では、認証済みのトークン資格情報を取得し、それを使用して、グループ内のユーザーを取得するサービス クライアント オブジェクトを作成します。

#### 例

```nodejs
const graphHelper = require('../utils/graphHelper');
const { DefaultAzureCredential } = require("@azure/identity");

exports.getUsersPage = async(req, res, next) => {

    const defaultAzureCredential = new DefaultAzureCredential();
    
    try {
        const tokenResponse = await defaultAzureCredential.getToken("https://graph.microsoft.com/.default");

        const graphClient = graphHelper.getAuthenticatedClient(tokenResponse.token);

        const users = await graphClient
            .api('/users')
            .get();

        res.render('users', { user: req.session.user, users: users });   
    } catch (error) {
        next(error);
    }
}
```

Microsoft Graph に対してクエリを実行するために、このサンプルでは [Microsoft Graph JavaScript SDK](https://github.com/microsoftgraph/msgraph-sdk-javascript) を使用します。

```nodejs
getAuthenticatedClient = (accessToken) => {
    // Initialize Graph client
    const client = graph.Client.init({
        // Use the provided access token to authenticate requests
        authProvider: (done) => {
            done(null, accessToken);
        }
    });

    return client;
}
```

---

### リソースをクリーンアップする

このチュートリアルを完了し、Web アプリや関連するリソースが不要になった場合は、[作成したリソースをクリーンアップ](https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-clean-up-resources)します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/multi-service-web-app-access-microsoft-graph-as-user"} -->
## チュートリアル - Web アプリからユーザーとして Microsoft Graph にアクセスする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-access-microsoft-graph-as-user
- Service: identity-platform
- Article date: 2023-09-15
- Summary: このチュートリアルでは、サインインしているユーザーに代わって Web アプリから Microsoft Graph のデータにアクセスする方法について説明します。

Azure App Service で実行されている Web アプリから Microsoft Graph にアクセスする方法について説明します。

[Image: Microsoft Graph へのアクセスを示す図。]

Web アプリから Microsoft Graph へのアクセスを追加し、サインインしているユーザーとしてなんらかのアクションを実行するとします。 このセクションでは、委任されたアクセス許可を Web アプリに付与し、サインインしているユーザーのプロファイル情報を Microsoft Entra ID から取得する方法について説明します。

このチュートリアルでは、次の操作を行います。

- 委任されたアクセス許可を Web アプリに付与する。
- サインインしているユーザーに代わって Web アプリから Microsoft Graph を呼び出す。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

### 前提条件

- [App Service 認証/承認モジュールが有効になっている Azure App Service](https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-authentication-app-service) で実行されている Web アプリケーション。

### Microsoft Graph を呼び出すためのアクセスをフロントエンドに許可する

Web アプリで認証と承認を有効にしたので、その Web アプリは Microsoft ID プラットフォームに登録され、Microsoft Entra アプリケーションでサポートされます。 この手順では、ユーザーに代わって Microsoft Graph にアクセスするためのアクセス許可を Web アプリに付与します (技術的には、ユーザーに代わって Microsoft Graph の Microsoft Entra アプリケーションにアクセスするためのアクセス許可を Web アプリの Microsoft Entra アプリケーションに付与します。)

[Microsoft Entra 管理センター](https://entra.microsoft.com) メニューで、[アプリケーション] を選択**します**。

[ **アプリの登録**&gt;**所有者アプリケーション**&gt;**このディレクトリ内のすべてのアプリケーションを表示**するを選択します。 Web アプリ名を選択し、[ **API のアクセス許可**] を選択します。

[ **アクセス許可の追加]** を選択し、Microsoft API と Microsoft Graph を選択します。

[ **委任されたアクセス許可**] を選択し、一覧から **[User.Read** ] を選択します。 [ **アクセス許可の追加] を選択します**。

### 使用可能なアクセス トークンを返すように App Service を構成する

これで、サインインしているユーザーとして Microsoft Graph にアクセスするために必要なアクセス許可が Web アプリに付与されました。 この手順では、Microsoft Graph にアクセスする場合に使用可能なアクセス トークンが提供されるように、App Service の認証および承認を構成します。 この手順では、ダウンストリーム サービス (Microsoft Graph) の User.Read スコープを追加する必要があります: `https://graph.microsoft.com/User.Read`。

重要

使用可能なアクセス トークンを返すように App Service を構成していない場合、コードで Microsoft Graph API を呼び出したときに `CompactToken parsing failed with error code: 80049217` エラーが発生します。

## [Azure リソース エクスプローラー](#tab/azure-resource-explorer)
[Azure リソース エクスプローラー](https://resources.azure.com/)に移動し、リソース ツリーを使用して、Web アプリを見つけます。 リソース URL は、`https://resources.azure.com/subscriptions/subscriptionId/resourceGroups/SecureWebApp/providers/Microsoft.Web/sites/SecureWebApp20200915115914` のようになります。

リソース ツリーで対象の Web アプリが選択された状態で、Azure Resource Explorer が開きます。 ページの上部にある **[読み取り/書き込み** ] を選択して、Azure リソースの編集を有効にします。

左側のブラウザーで、 **config**&gt;**authsettingsV2** にドリルダウンします。

**authsettingsV2** ビューで、[**編集]** を選択します。 **identityProviders**&gt; の**ログイン** セクションを見つけて、**loginParameters** 設定を追加します:`"loginParameters":[ "response_type=code id_token","scope=openid offline_access profile https://graph.microsoft.com/User.Read" ]`。

```json
"identityProviders": {
    "azureActiveDirectory": {
      "enabled": true,
      "login": {
        "loginParameters":[
          "response_type=code id_token",
          "scope=openid offline_access profile https://graph.microsoft.com/User.Read"
        ]
      }
    }
  }
},
```

**PUT** を選択して設定を保存します。 この設定が有効になるまでに数分かかる場合があります。 これで、適切なアクセス トークンを使用して Microsoft Graph にアクセスするように Web アプリが構成されました。 これを行わないと、Microsoft Graph から、コンパクトなトークンの形式が正しくないことを示すエラーが返されます。

## [Azure CLI](#tab/azure-cli)
Azure CLI を使用して App Service Web App REST API を呼び出し、Web アプリが Microsoft Graph を呼び出すことができるように認証構成設定を [取得](https://learn.microsoft.com/ja-jp/rest/api/appservice/web-apps/get-auth-settings) および [更新](https://learn.microsoft.com/ja-jp/rest/api/appservice/web-apps/update-auth-settings) します。 コマンド ウィンドウを開き、Azure CLI にログインします。

```azurecli
az login
```

既存の 'config/authsettingsv2' 設定を取得し、ローカル *authsettings.json* ファイルに保存します。

```azurecli
az rest --method GET --url '/subscriptions/{SUBSCRIPTION_ID}/resourceGroups/{RESOURCE_GROUP}/providers/Microsoft.Web/sites/{WEBAPP_NAME}/config/authsettingsv2/list?api-version=2020-06-01' > authsettings.json
```

好みのテキスト エディターを使用して、authsettings.json ファイルを開きます。 **identityProviders**&gt; の**ログイン** セクションを見つけて、**loginParameters** 設定を追加します:`"loginParameters":[ "response_type=code id_token","scope=openid offline_access profile https://graph.microsoft.com/User.Read" ]`。

```json
"identityProviders": {
    "azureActiveDirectory": {
      "enabled": true,
      "login": {
        "loginParameters":[
          "response_type=code id_token",
          "scope=openid offline_access profile https://graph.microsoft.com/User.Read"
        ]
      }
    }
  }
},
```

*authsettings.json* ファイルに変更を保存し、ローカル設定を Web アプリにアップロードします。

```azurecli
az rest --method PUT --url '/subscriptions/{SUBSCRIPTION_ID}/resourceGroups/{RESOURCE_GROUP}/providers/Microsoft.Web/sites/{WEBAPP_NAME}/config/authsettingsv2?api-version=2020-06-01' --body @./authsettings.json
```

---

### Microsoft Graph の呼び出し

Web アプリに必要なアクセス許可が付与され、さらに Microsoft Graph のクライアント ID がログイン パラメーターに追加されました。

## [C#](#tab/programming-language-csharp)
[Microsoft.Identity.Web ライブラリ](https://github.com/AzureAD/microsoft-identity-web/)を使用して、Web アプリは Microsoft Graph での認証用のアクセス トークンを取得します。 バージョン 1.2.0 以降の Microsoft.Identity.Web ライブラリは、App Service 認証および承認モジュールと統合され、一緒に実行できます。 Microsoft.Identity.Web は、この Web アプリが App Service でホストされていることを検出すると、App Service の認証および承認モジュールからアクセス トークンを取得します。 その後、このアクセス トークンは、Microsoft Graph API を使用して、認証された要求に渡されます。

サンプル アプリケーションの一部としてこのコードを確認するには、 [GitHub のサンプルを](https://github.com/Azure-Samples/ms-identity-easyauth-dotnet-storage-graphapi/tree/main/2-WebApp-graphapi-on-behalf)参照してください。

注

Web アプリで基本認証および承認を使用する場合、または Microsoft Graph を使用して要求を認証する場合には、Microsoft.Identity.Web ライブラリは不要です。 App Service の認証/承認モジュールのみを有効 [にして、ダウンストリーム API を安全に呼び出](https://learn.microsoft.com/ja-jp/azure/app-service/tutorial-auth-aad#call-api-securely-from-server-code) すこともできます。

ただし、App Service の認証および承認は、さらに多くの基本認証シナリオ向けに設計されています。 より複雑なシナリオ (カスタム要求の処理など) には、Microsoft.Identity.Web ライブラリまたは [Microsoft Authentication Library が必要です](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-overview)。 最初は設定および構成作業がもう少しありますが、Microsoft.Identity.Web ライブラリは App Service の認証および承認モジュールと並行して実行できます。 後で Web アプリでより複雑なシナリオへの対応が必要になったときに、App Service の認証および承認モジュールを無効にすることができ、Microsoft.Identity.Web は既にアプリの一部になっています。

#### クライアント ライブラリ パッケージをインストールする

.NET コマンド ライン インターフェイス (CLI) または Visual Studio のパッケージ マネージャー コンソールを使用して、 [Microsoft.Identity.Web](https://www.nuget.org/packages/Microsoft.Identity.Web/) パッケージと [Microsoft.Identity.Web.GraphServiceClient](https://www.nuget.org/packages/Microsoft.Identity.Web.GraphServiceClient) NuGet パッケージをプロジェクトにインストールします。

##### .NET コマンドライン インターフェイス (CLI)

コマンド ラインを開き、プロジェクト ファイルが含まれているディレクトリに切り替えます。

インストール コマンドを実行します。

```dotnetcli
dotnet add package Microsoft.Identity.Web.GraphServiceClient

dotnet add package Microsoft.Identity.Web
```

##### パッケージ マネージャー コンソール

Visual Studio でプロジェクト/ソリューションを開き、 **Tools**&gt;**NuGet パッケージ マネージャー**&gt;**Package Manager コンソール** コマンドを使用してコンソールを開きます。

インストール コマンドを実行します。

```powershell
Install-Package Microsoft.Identity.Web.GraphServiceClient

Install-Package Microsoft.Identity.Web
```

#### Startup.cs

*Startup.cs* ファイルでは、`AddMicrosoftIdentityWebApp` メソッドによって Microsoft.Identity.Web が Web アプリに追加されます。 `AddMicrosoftGraph` メソッドにより、Microsoft Graph のサポートが追加されます。

```csharp
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Identity.Web;
using Microsoft.AspNetCore.Authentication.OpenIdConnect;

// Some code omitted for brevity.
public class Startup
{
    // This method gets called by the runtime. Use this method to add services to the container.
    public void ConfigureServices(IServiceCollection services)
    {
      services.AddOptions();
      string[] initialScopes = Configuration.GetValue<string>("DownstreamApi:Scopes")?.Split(' ');

      services.AddAuthentication(OpenIdConnectDefaults.AuthenticationScheme)
              .AddMicrosoftIdentityWebApp(Configuration.GetSection("AzureAd"))
              .EnableTokenAcquisitionToCallDownstreamApi(initialScopes)
                      .AddMicrosoftGraph(Configuration.GetSection("DownstreamApi"))
                      .AddInMemoryTokenCaches(); 

      services.AddAuthorization(options =>
      {
          // By default, all incoming requests will be authorized according to the default policy
          options.FallbackPolicy = options.DefaultPolicy;
      });
      services.AddRazorPages()
          .AddMvcOptions(options => {})                
          .AddMicrosoftIdentityUI();

      services.AddControllersWithViews()
              .AddMicrosoftIdentityUI();
    }
}

```

#### appsettings.json

*Microsoft Entra ID* は、Microsoft.Identity.Web ライブラリの構成を指定します。 [Microsoft Entra 管理センター](https://entra.microsoft.com)で、ポータル メニューから **[アプリケーション**] を選択し、[**アプリの登録**] を選択します。 App Service の認証および承認モジュールを有効にしたときに作成されたアプリの登録を選択します (アプリの登録には、Web アプリと同じ名前が付けられています)。テナント ID とクライアント ID は、アプリの登録の概要ページで確認できます。 ドメイン名は、テナントの Microsoft Entra の概要ページで確認できます。

*Graph* では、Microsoft Graph エンドポイントと、アプリで必要な初期スコープを指定します。

```json
{
  "AzureAd": {
    "Instance": "https://login.microsoftonline.com/",
    "Domain": "[Enter the domain of your tenant, e.g. contoso.onmicrosoft.com]",
    "TenantId": "[Enter 'common', or 'organizations' or the Tenant Id (Obtained from the Entra admin center. Select 'Endpoints' from the 'App registrations' blade and use the GUID in any of the URLs), e.g. aaaabbbb-0000-cccc-1111-dddd2222eeee]",
    "ClientId": "[Enter the Client Id (Application ID obtained from the Microsoft Entra admin center), e.g. 00001111-aaaa-2222-bbbb-3333cccc4444]",
    "ClientSecret": "[Copy the client secret added to the app from the Microsoft Entra admin center]",
    "ClientCertificates": [
    ],
    // the following is required to handle Continuous Access Evaluation challenges
    "ClientCapabilities": [ "cp1" ],
    "CallbackPath": "/signin-oidc"
  },
  "DownstreamApis": {
    "MicrosoftGraph": {
      // Specify BaseUrl if you want to use Microsoft graph in a national cloud.
      // See https://learn.microsoft.com/graph/deployments#microsoft-graph-and-graph-explorer-service-root-endpoints
      // "BaseUrl": "https://graph.microsoft.com/v1.0",

      // Set RequestAppToken this to "true" if you want to request an application token (to call graph on 
      // behalf of the application). The scopes will then automatically
      // be ['https://graph.microsoft.com/.default'].
      // "RequestAppToken": false

      // Set Scopes to request (unless you request an app token).
      "Scopes": [ "User.Read" ]

      // See https://aka.ms/ms-id-web/downstreamApiOptions for all the properties you can set.
    }
  },
  "Logging": {
    "LogLevel": {
      "Default": "Information",
      "Microsoft": "Warning",
      "Microsoft.Hosting.Lifetime": "Information"
    }
  },
  "AllowedHosts": "*"
}
```

#### Index.cshtml.cs

次の例は、サインインしているユーザーとして Microsoft Graph を呼び出し、ユーザー情報を取得する方法を示しています。 `GraphServiceClient` オブジェクトがコントローラーに挿入され、Microsoft.Identity.Web ライブラリによって認証が構成されています。

```csharp
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Graph;
using System.IO;
using Microsoft.Identity.Web;
using Microsoft.Extensions.Logging;

// Some code omitted for brevity.

[AuthorizeForScopes(Scopes = new[] { "User.Read" })]
public class IndexModel : PageModel
{
    private readonly ILogger<IndexModel> _logger;
    private readonly GraphServiceClient _graphServiceClient;

    public IndexModel(ILogger<IndexModel> logger, GraphServiceClient graphServiceClient)
    {
        _logger = logger;
        _graphServiceClient = graphServiceClient;
    }

    public async Task OnGetAsync()
    {
        try
        {
            var user = await _graphServiceClient.Me.GetAsync();
            ViewData["Me"] = user;
            ViewData["name"] = user.DisplayName;

            using (var photoStream = await _graphServiceClient.Me.Photo.Content.GetAsync())
            {
                byte[] photoByte = ((MemoryStream)photoStream).ToArray();
                ViewData["photo"] = Convert.ToBase64String(photoByte);
            }
        }
        catch (Exception ex)
        {
            ViewData["photo"] = null;
        }
    }
}
```

## [Node.js](#tab/programming-language-nodejs)
認証ロジックをカプセル化するカスタム **AuthProvider** クラスを使用して、Web アプリは受信要求ヘッダーからユーザーのアクセス トークンを取得します。 **AuthProvider** インスタンスは、Web アプリが App Service でホストされていることを検出し、App Service 認証/承認モジュールからアクセス トークンを取得します。 続いてアクセス トークンは、認証された要求を `/me` エンドポイントに対して行うため、Microsoft Graph SDK クライアントに渡されます。

注

App Service の認証/承認は、さらに多くの基本認証シナリオ向けに設計されています。 後で、Web アプリがより複雑なシナリオを処理する必要がある場合は、App Service の認証/承認モジュールを無効にできます。サンプルの **AuthProvider** インスタンスは [MSAL ノード](https://github.com/AzureAD/microsoft-authentication-library-for-js/tree/dev/lib/msal-node)を使用するようにフォールバックします。これは、Node.js アプリケーションに認証/承認を追加するための推奨ライブラリです。

```nodejs
const graphHelper = require('../utils/graphHelper');

// Some code omitted for brevity.

exports.getProfilePage = async(req, res, next) => {

    try {
        const graphClient = graphHelper.getAuthenticatedClient(req.session.protectedResources["graphAPI"].accessToken);

        const profile = await graphClient
            .api('/me')
            .get();

        res.render('profile', { isAuthenticated: req.session.isAuthenticated, profile: profile, appServiceName: appServiceName });   
    } catch (error) {
        next(error);
    }
}
```

Microsoft Graph に対してクエリを実行するには、 [Microsoft Graph JavaScript SDK を使用します](https://github.com/microsoftgraph/msgraph-sdk-javascript)。

```nodejs
const graph = require('@microsoft/microsoft-graph-client');

// Some code omitted for brevity.

getAuthenticatedClient = (accessToken) => {
    // Initialize Graph client
    const client = graph.Client.init({
        // Use the provided access token to authenticate requests
        authProvider: (done) => {
            done(null, accessToken);
        }
    });

    return client;
}
```

---

### リソースをクリーンアップする

このチュートリアルが終了し、Web アプリや関連リソースが不要になった場合は、 [作成したリソースをクリーンアップします](https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-clean-up-resources)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/multi-service-web-app-access-storage"} -->
## チュートリアル - マネージド ID を使用して Web アプリからストレージにアクセスする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-access-storage
- Service: identity-platform
- Article date: 2025-05-12
- Summary: マネージド ID を使用して Azure App Service の Web アプリから Azure Storage にアクセスする方法について説明します。 セキュリティを簡素化し、シークレットの管理を回避します。

マネージド ID を使用して、Azure App Service で実行されている Web アプリ (サインインユーザーではない) から Azure Storage にアクセスする方法について説明します。 セキュリティを簡素化し、シークレットの管理を回避します。

[Image: Web アプリがマネージド ID を使用して Azure Storage にアクセスする方法を示す図のスクリーンショット。]

Web アプリから Azure データ プレーン (Azure Storage、Azure SQL Database、Azure Key Vault、またはその他のサービス) へのアクセスを追加するとします。 共有キーを使用することもできますが、その場合、シークレットを作成、デプロイ、および管理できるユーザーの運用上のセキュリティについて配慮する必要があります。 また、キーが GitHub にチェックインされる可能性もあります。ハッカーはそのスキャン方法を知っています。 Web アプリにデータへのアクセスを許可するより安全な方法では、[マネージド ID](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview) を使用します。

Microsoft Entra ID のマネージド ID を使用すると、App Service は、アプリの資格情報を必要とせずに、ロールベースのアクセス制御 (RBAC) を使用してリソースにアクセスできます。 マネージド ID を対象の Web アプリに割り当てると、Azure では証明書の作成と配布が行われます。 シークレットまたはアプリの資格情報の管理について心配する必要はありません。

このチュートリアルでは、次の操作を行います。

- Web アプリ上でシステム割り当てマネージド ID を作成する。
- ストレージ アカウントと Azure Blob Storage コンテナーを作成する。
- マネージド ID を使用して Web アプリからストレージにアクセスする。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

### 前提条件

- [App Service の認証および承認モジュールが有効になっている](https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-authentication-app-service) Azure App Service で実行されている Web アプリケーション。

### アプリでマネージド ID を有効にする

Visual Studio を使用して Web アプリを作成して発行すると、アプリでマネージド ID が有効になります。 App Service で、左ペインの **[ID]** を選択し、 **[システム割り当て済み]** を選択します。 **[Status](https://learn.microsoft.com/ja-jp/entra/identity-platform/状態)** が **[オン]** に設定されていることを確認します。 そうでない場合は、 **[保存]** を選択し、 **[はい]** を選択して、システム割り当てマネージド ID を有効にします。 マネージド ID が有効になると、状態が **[オン]** に設定され、オブジェクト ID が使用可能になります。

[Image: [システム割り当て済み] ID オプションを示すスクリーンショット。]

この手順により、 **[認証/承認]** ペインで作成されたアプリ ID とは異なる新しいオブジェクト ID が作成されます。 システム割り当てマネージド ID のオブジェクト ID をコピーしておきます。 この情報は後で必要になります。

### ストレージ アカウントと Blob Storage コンテナーを作成する

これで、ストレージ アカウントと Blob Storage コンテナーを作成する準備が整いました。

すべてのストレージ アカウントは、Azure リソース グループに属している必要があります。 リソース グループは、Azure サービスをグループ化するための論理コンテナーです。 ストレージ アカウントを作成するときに、新しいリソース グループを作成するか、既存のリソース グループを使用するかを選択できます。 この記事では、新しいリソース グループを作成する方法を示します。

汎用 v2 ストレージ アカウントでは、すべての Azure Storage サービス (BLOB、ファイル、キュー、テーブル、ディスク) へのアクセスが提供されます。 ここで説明する手順では汎用 v2 ストレージ アカウントを作成しますが、作成手順はどの種類のストレージ アカウントでも似ています。

Azure Storage 内の BLOB はコンテナーにまとめられます。 このチュートリアルの後半で BLOB をアップロードする前に、まずコンテナーを作成する必要があります。

## [ポータル](#tab/azure-portal)
Azure portal で汎用 v2 ストレージ アカウントを作成するには、次の手順に従います。

1. Azure portal のメニューで、**[すべてのサービス]** を選択します。 リソースの一覧で、「**Storage Accounts**」と入力します。 入力を始めると、入力内容に基づいて、一覧がフィルター処理されます。 **[ストレージ アカウント]** を選択します。
2. 表示される **[ストレージ アカウント]** ウィンドウで、**[作成]** を選択します。
3. ストレージ アカウントを作成するサブスクリプションを選択します。
4. **[リソース グループ]** フィールドで、ドロップダウン メニューから、対象の Web アプリが含まれているリソース グループを選択します。
5. 次に、ストレージ アカウントの名前を入力します。 選択する名前は Azure 全体で一意である必要があります。 また、名前の長さは 3 から 24 文字とし、数字と小文字のみを使用できます。
6. ストレージ アカウントの場所を選択するか、または既定の場所を使います。
7. **[パフォーマンス]** には、**[Standard]** オプションを選択します。
8. **[冗長性]** には、ドロップダウンから **[ローカル冗長ストレージ (LRS)]** オプションを選択します。
9. **[確認]** を選択して、ストレージ アカウントの設定を確認し、アカウントを作成します。
10. **［作成］** を選択します

Azure Storage で Blob Storage コンテナーを作成するには、次の手順に従います。

1. Azure portal で新しいストレージ アカウントに移動します。
2. ストレージ アカウントの左側のメニューで、**[データ ストレージ]** セクションまでスクロールし、**[コンテナー]** を選択します。
3. **[+ コンテナー]** ボタンを選択します。
4. 新しいコンテナーの名前を入力します。 コンテナー名は小文字である必要があり、英文字または数字で始まる必要があり、英文字、数字、ダッシュ (-) 文字のみを含めることができます。
5. コンテナーにパブリック アクセスのレベルを設定します。 既定のレベルは **[ プライベート (匿名アクセスなし)]** です。
6. **[作成]** を選択して、コンテナーを作成します。

## [PowerShellの](#tab/azure-powershell)
汎用 v2 ストレージ アカウントと Blob Storage コンテナーを作成するには、次のスクリプトを実行します。 対象の Web アプリが含まれているリソース グループの名前を指定します。 ストレージ アカウントの名前を入力します。 選択する名前は Azure 全体で一意である必要があります。 また、名前の長さは 3 から 24 文字とし、数字と小文字のみを使用できます。

対象のストレージ アカウントの場所を指定します。 対象のサブスクリプションに有効な場所のリストを表示するには、`Get-AzLocation | select Location` を実行します。 コンテナー名は小文字である必要があり、英文字または数字で始まる必要があり、英文字、数字、ダッシュ (-) 文字のみを含めることができます。

山かっこ内のプレースホルダーの値は、忘れずに実際の値に置き換えてください。

```powershell
Connect-AzAccount

$resourceGroup = "securewebappresourcegroup"
$location = "<location>"
$storageName="securewebappstorage"
$containerName = "securewebappblobcontainer"

$storageAccount = New-AzStorageAccount -ResourceGroupName $resourceGroup `
  -Name $storageName `
  -Location $location `
  -SkuName Standard_RAGRS `
  -Kind StorageV2

$ctx = $storageAccount.Context

New-AzStorageContainer -Name $containerName -Context $ctx -Permission blob
```

## [Azure CLI](#tab/azure-cli)
汎用 v2 ストレージ アカウントと Blob Storage コンテナーを作成するには、次のスクリプトを実行します。 対象の Web アプリが含まれているリソース グループの名前を指定します。 ストレージ アカウントの名前を入力します。 選択する名前は Azure 全体で一意である必要があります。 また、名前の長さは 3 から 24 文字とし、数字と小文字のみを使用できます。

対象のストレージ アカウントの場所を指定します。 コンテナー名は小文字である必要があり、英文字または数字で始まる必要があり、英文字、数字、ダッシュ (-) 文字のみを含めることができます。

次の例では、Microsoft Entra アカウントを使用して、コンテナーの作成操作を承認します。 コンテナーを作成する前に、ストレージ BLOB データ共同作成者ロールを自分に割り当てます。 自分がアカウント オーナーである場合でも、ストレージ アカウントに対してデータ操作を実行するための明示的なアクセス許可が必要となります。

山かっこ内のプレースホルダーの値は、忘れずに実際の値に置き換えてください。

```azurecli
az login

az storage account create \
    --name securewebappstorage \
    --resource-group securewebappresourcegroup \
    --location <location> \
    --sku Standard_ZRS \
    --encryption-services blob

storageId=$(az storage account show -n securewebappstorage -g securewebappresourcegroup --query id --out tsv)

az ad signed-in-user show --query objectId -o tsv | az role assignment create \
    --role "Storage Blob Data Contributor" \
    --assignee @- \
    --scope $storageId

az storage container create \
    --account-name securewebappstorage \
    --name securewebappblobcontainer \
    --auth-mode login
```

---

### ストレージ アカウントへのアクセスを許可する

BLOB の作成、読み取り、または削除を行う前に、ストレージ アカウントへのアクセスを Web アプリに許可する必要があります。 前の手順では、App Service で実行されている Web アプリをマネージド ID を使用して構成しました。 Azure RBAC を使用すると、セキュリティ プリンシパルと同様に、別のリソースへのアクセス権をマネージド ID に付与できます。 ストレージ BLOB データ共同作成者ロールにより、(システム割り当てマネージド ID で表される) Web アプリに、BLOB コンテナーとデータへの読み取り、書き込み、削除アクセス権が付与されます。

注意

プライベート BLOB コンテナーに対する一部の操作 (BLOB の表示やアカウント間での BLOB のコピーなど) は、Azure RBAC ではサポートされていません。 プライベート アクセス レベルの BLOB コンテナーでは、Azure RBAC によって認可されていないすべての操作に SAS トークンが必要です。 詳細については、「[Shared Access Signature を使用するタイミング](https://learn.microsoft.com/ja-jp/azure/storage/common/storage-sas-overview#when-to-use-a-shared-access-signature)」を参照してください。

## [ポータル](#tab/azure-portal)
[Azure portal](https://portal.azure.com) で、Web アプリにアクセスを許可するストレージ アカウントに移動します。 左ペインで **[アクセス制御 (IAM)]** を選択し、 **[ロールの割り当て]** を選択します。 ストレージ アカウントへのアクセス権を持つユーザーのリストが表示されます。 ここで、ストレージ アカウントへのアクセスを必要とするアプリ サービスであるロボットに、ロールの割り当てを追加します。 **[追加]**&gt;**[ロールの割り当ての追加]** を選択して、**[ロールの割り当ての追加]** ページを開きます。

1. **[割り当ての種類]** タブで、**[ジョブ関数の種類]** を選択し、**[次へ]** を選択します。
2. **[ロール]** タブで、ドロップダウンから **[ストレージ BLOB データ共同作成者]** ロールを選択し、**[次へ]** を選択します。
3. [ **メンバー** ] タブで、[ **アクセス権の割り当て**&gt;**管理 ID** ] を選択し、[ **メンバー**&gt;**メンバーの選択**] を選択します。 **[マネージド ID の選択]** ウィンドウの **[マネージド ID]** ドロップダウンで、お使いの App Service 用に作成されたマネージド ID を見つけて選択します。 **[選択]** ボタンを選択します。
4. **[確認と割り当て]** を選択し、もう一度 **[確認と割り当て]** を選択します。

詳細な手順については、「[Azure portal を使用して Azure ロールを割り当てる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal)」を参照してください。

これで、Web アプリからストレージ アカウントにアクセスできるようになりました。

## [PowerShellの](#tab/azure-powershell)
次のスクリプトを実行して、(システム割り当てマネージド ID で表される) Web アプリに、ストレージ アカウントのストレージ BLOB データ共同作成者ロールを割り当てます。

```powershell
$resourceGroup = "securewebappresourcegroup"
$webAppName="SecureWebApp20201102125811"
$storageName="securewebappstorage"

$spID = (Get-AzWebApp -ResourceGroupName $resourceGroup -Name $webAppName).identity.principalid
$storageId= (Get-AzStorageAccount -ResourceGroupName $resourceGroup -Name $storageName).Id
New-AzRoleAssignment -ObjectId $spID -RoleDefinitionName "Storage Blob Data Contributor" -Scope $storageId
```

## [Azure CLI](#tab/azure-cli)
次のスクリプトを実行して、(システム割り当てマネージド ID で表される) Web アプリに、ストレージ アカウントのストレージ BLOB データ共同作成者ロールを割り当てます。

```azurecli
spID=$(az resource list -n SecureWebApp20201102125811 --query [*].identity.principalId --out tsv)

storageId=$(az storage account show -n securewebappstorage -g securewebappresourcegroup --query id --out tsv)

az role assignment create --assignee $spID --role 'Storage Blob Data Contributor' --scope $storageId
```

---

### Blob Storage にアクセスする

## [C#](#tab/programming-language-csharp)
[DefaultAzureCredential](https://learn.microsoft.com/ja-jp/dotnet/api/azure.identity.defaultazurecredential) クラスは、Azure Storage に対する要求をコードで承認するためにトークン資格情報を取得する際に使用されます。 [DefaultAzureCredential](https://learn.microsoft.com/ja-jp/dotnet/api/azure.identity.defaultazurecredential) クラスのインスタンスを作成します。これは、マネージド ID を使用し、トークンを取得してサービス クライアントにアタッチします。 次のコード例では、認証済みのトークン資格情報を取得し、それを使用して、新しい BLOB をアップロードするサービス クライアント オブジェクトを作成します。

このコードをサンプル アプリケーションの一部として見る場合は、[GitHub 上のサンプル](https://github.com/Azure-Samples/ms-identity-easyauth-dotnet-storage-graphapi/tree/main/1-WebApp-storage-managed-identity)を参照してください。

#### クライアント ライブラリ パッケージをインストールする

Blob Storage を使用するための [Blob Storage NuGet パッケージ](https://www.nuget.org/packages/Azure.Storage.Blobs/)と、Microsoft Entra の資格情報で認証するための [.NET 用 Azure Identity クライアント ライブラリ NuGet パッケージ](https://www.nuget.org/packages/Azure.Identity/)をインストールします。 クライアント ライブラリは、.NET コマンドライン インターフェイス (CLI) または Visual Studio のパッケージ マネージャー コンソールを使用してインストールします。

##### .NET コマンドライン インターフェイス (CLI)

コマンド ラインを開き、プロジェクト ファイルが含まれているディレクトリに切り替えます。

インストール コマンドを実行します。

```dotnetcli
dotnet add package Azure.Storage.Blobs

dotnet add package Azure.Identity
```

##### パッケージ マネージャー コンソール

Visual Studio でプロジェクトまたはソリューションを開き、 **[ツール]**&gt;**[NuGet パッケージ マネージャー]**&gt;**[パッケージ マネージャー コンソール]** コマンドを使用してコンソールを開きます。

インストール コマンドを実行します。

```powershell
Install-Package Azure.Storage.Blobs

Install-Package Azure.Identity
```

#### 例

```csharp
using System;
using Azure.Storage.Blobs;
using Azure.Storage.Blobs.Models;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.Text;
using System.IO;
using Azure.Identity;

// Some code omitted for brevity.

static public async Task UploadBlob(string accountName, string containerName, string blobName, string blobContents)
{
    // Construct the blob container endpoint from the arguments.
    string containerEndpoint = string.Format("https://{0}.blob.core.windows.net/{1}",
                                                accountName,
                                                containerName);

    // Get a credential and create a client object for the blob container.
    BlobContainerClient containerClient = new BlobContainerClient(new Uri(containerEndpoint),
                                                                    new DefaultAzureCredential());

    try
    {
        // Create the container if it does not exist.
        await containerClient.CreateIfNotExistsAsync();

        // Upload text to a new block blob.
        byte[] byteArray = Encoding.ASCII.GetBytes(blobContents);

        using (MemoryStream stream = new MemoryStream(byteArray))
        {
            await containerClient.UploadBlobAsync(blobName, stream);
        }
    }
    catch (Exception e)
    {
        throw e;
    }
}
```

## [Node.js](#tab/programming-language-nodejs)
`DefaultAzureCredential` パッケージの  クラスは、Azure Storage に対する要求をコードで承認するためのトークン資格情報を取得するために使用されます。 `BlobServiceClient` クラスは、[@azure/storage-blob](https://github.com/Azure/azure-sdk-for-js/tree/main/sdk/storage/storage-blob) パッケージから新しい BLOB をストレージにアップロードするために使用されます。 `DefaultAzureCredential` クラスのインスタンスを作成します。これは、マネージド ID を使用し、トークンを取得して BLOB サービス クライアントにアタッチします。 次のコード例では、認証済みのトークン資格情報を取得し、それを使用して、新しい BLOB をアップロードするサービス クライアント オブジェクトを作成します。

#### 例

```nodejs
const { DefaultAzureCredential } = require("@azure/identity");
const { BlobServiceClient } = require("@azure/storage-blob");
const defaultAzureCredential = new DefaultAzureCredential();

// Some code omitted for brevity.

async function uploadBlob(accountName, containerName, blobName, blobContents) {
    const blobServiceClient = new BlobServiceClient(
        `https://${accountName}.blob.core.windows.net`,
        defaultAzureCredential
    );

    const containerClient = blobServiceClient.getContainerClient(containerName);

    try {
        await containerClient.createIfNotExists();
        const blockBlobClient = containerClient.getBlockBlobClient(blobName);
        const uploadBlobResponse = await blockBlobClient.upload(blobContents, blobContents.length);
        console.log(`Upload block blob ${blobName} successfully`, uploadBlobResponse.requestId);
    } catch (error) {
        console.log(error);
    }
}
```

---

### リソースをクリーンアップする

このチュートリアルを完了し、Web アプリや関連するリソースが不要になった場合は、[作成したリソースをクリーンアップ](https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-clean-up-resources)します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/multi-service-web-app-authentication-app-service"} -->
## チュートリアル - Azure App Service に認証を追加する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-authentication-app-service
- Service: identity-platform
- Article date: 2024-02-17
- Summary: このチュートリアルでは、Azure App Service で実行されている Web アプリの認証を有効にする方法について説明します。 Web アプリへのアクセスを組織内のユーザーに制限します。

Azure App Service で実行されている Web アプリの認証を有効にし、アクセスを組織内のユーザーに制限する方法について説明します。

[Image: ユーザーのサインインを示す図。]

App Service では組み込みの認証がサポートされているので、Web アプリで最小限のコードを記述するだけで、またはコードをまったく記述せずに、ユーザーをサインインし、データにアクセスできます。 App Service の認証モジュールの使用は必須ではありませんが、アプリの認証の簡素化に役立ちます。 この記事では、Microsoft Entra ID を ID プロバイダーとして使って、App Service 認証モジュールで Web アプリを保護する方法について説明します。

認証モジュールは、Azure portal とアプリの設定を通じて有効化および構成されます。 SDK、特定の言語、またはアプリケーション コードの変更は必要ありません。さまざまな ID プロバイダーがサポートされています。これには、Microsoft Entra ID、Microsoft アカウント、Facebook、Google、X が含まれます。 認証モジュールが有効になっている場合、すべての受信 HTTP 要求は、アプリ コードによって処理される前に、それを通過します。詳細については、 [Azure App Service での認証と承認に関するページを](https://learn.microsoft.com/ja-jp/azure/app-service/overview-authentication-authorization)参照してください。

このチュートリアルでは、次の操作を行います。

- Web アプリの認証を構成する。
- Web アプリへのアクセスを組織内のユーザーに制限する。

### 前提条件

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

### App Service で Web アプリを作成して公開する

このチュートリアルでは、App Service にデプロイされた Web アプリが必要です。 既存の Web アプリを使用することも、 [ASP.NET Core](https://learn.microsoft.com/ja-jp/azure/app-service/quickstart-dotnetcore)、 [Node.js](https://learn.microsoft.com/ja-jp/azure/app-service/quickstart-nodejs)、 [Python](https://learn.microsoft.com/ja-jp/azure/app-service/quickstart-python)、または [Java](https://learn.microsoft.com/ja-jp/azure/app-service/quickstart-java) のクイックスタートのいずれかに従って、新しい Web アプリを作成して App Service に発行することもできます。

既存の Web アプリを使用するか新しい Web アプリを作成するかにかかわらず、次のものをメモしてください。

- Web アプリの名前
- Web アプリのデプロイ先のリソース グループ名

これらの名前は、このチュートリアル全体を通して必要になります。

### 認証を構成する

これで、App Service で実行されている Web アプリを用意できました。 次に、Web アプリの認証を有効にします。 ID プロバイダーとして Microsoft Entra ID を使用します。 詳細については、「 [App Service アプリケーションの Microsoft Entra 認証を構成する」を参照してください](https://learn.microsoft.com/ja-jp/azure/app-service/configure-authentication-provider-aad)。

[Azure portal](https://portal.azure.com) のメニューで、[**リソース グループ**] を選択するか、任意のページから**リソース グループ**を検索して選択します。

**[リソース グループ]** で、リソース グループを見つけて選択します。 [ **概要**] で、アプリの管理ページを選択します。

[Image: アプリの管理ページの選択を示すスクリーンショット。]

アプリの左側のメニューで、[ **認証**] を選択し、[ **ID プロバイダーの追加**] をクリックします。

[ **ID プロバイダーの追加** ] ページで、 **Microsoft と Microsoft** Entra の ID にサインインする **ID プロバイダー** として Microsoft を選択します。

**[テナントの種類] で**、[**ワークフォース**] を選択します。

**[アプリの登録**&gt;**アプリの登録の種類]** で、[**新しいアプリ登録の作成**] を選択します。

**アプリの登録**&gt;**サポートされているアカウントの種類**で、**現在のテナントシングル テナント**を選択します。

**[App Service の認証設定**] セクションで、[**認証**] を **[認証を要求する]** に設定し、[**認証されていない要求**] を **[HTTP 302 Found リダイレクト: Web サイトに推奨]** に設定したままにします。

[ **ID プロバイダーの追加** ] ページの下部にある [ **追加** ] をクリックして、Web アプリの認証を有効にします。

[Image: 認証の構成を示すスクリーンショット。]

これで、App Service の認証によってアプリが保護されるようになりました。

注

他のテナントからのアカウントを許可するには、[認証] ブレードから [ID プロバイダー] を編集し、[発行者の URL] を [https://login.microsoftonline.com/common/v2.0 ] に変更します。

### Web アプリへの制限付きアクセスを確認する

App Service の認証モジュールを有効にしたときに、Microsoft Entra テナントにアプリの登録が作成されました。 アプリの登録には、Web アプリと同じ表示名が付けられています。 設定を確認するには、少なくとも[アプリケーション開発者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)にサインインし、**Entra ID**&gt;**App 登録**を参照します。 作成されたアプリの登録を選択します。 概要で、[ **サポートされているアカウントの種類** ] が [ **組織のみ**] に設定されていることを確認します。

[Image: アクセスの確認を示すスクリーンショット。]

対象のアプリへのアクセスが組織内のユーザーに制限されていることを確認するには、シークレット モードまたはプライベート モードでブラウザーを起動し、`https://<app-name>.azurewebsites.net` に移動します。 セキュリティで保護されたサインイン ページが表示されるので、認証されていないユーザーにはサイトへのアクセスが許可されないことを確認できます。 サイトにアクセスするために、組織内のユーザーとしてサインインします。 新しいブラウザーを起動し、個人用アカウントを使用してサインインしてみることで、組織外のユーザーにはアクセス権がないことを確認することもできます。

### リソースをクリーンアップする

このチュートリアルが終了し、Web アプリや関連リソースが不要になった場合は、 [作成したリソースをクリーンアップします](https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-clean-up-resources)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/multi-service-web-app-clean-up-resources"} -->
## チュートリアル: リソースをクリーンアップする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-clean-up-resources
- Service: identity-platform
- Article date: 2024-02-17
- Summary: このチュートリアルでは、Web アプリの作成時に割り当てられた Azure リソースをクリーンアップする方法について説明します。

この複数のパートで構成されるチュートリアルのすべての手順を完了している場合、アプリ サービス、アプリ サービス ホスティング プラン、ストレージ アカウントがリソース グループに作成されています。 また、Microsoft Entra ID にアプリの登録も作成されています。 これらのリソースとアプリの登録が不要になったら、引き続き料金が発生することのないように、これらを削除します。

このチュートリアルでは、次の操作を行います。

- チュートリアルに従って、作成した Azure リソースを削除する。

### リソース グループを削除します

[Azure portal](https://portal.azure.com) で、ポータル メニューから **[リソース グループ**] を選択し、App Service と App Service プランを含むリソース グループを選択します。

[ **リソース グループの削除]** を選択して、リソース グループとすべてのリソースを削除します。

[Image: リソース グループの削除を示すスクリーンショット。]

このコマンドの実行には数分かかることがあります。

### アプリの登録を削除する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション開発者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)としてサインインします。
2. **Entra ID**&gt;**アプリケーション登録を参照します**。
3. 作成したアプリケーションを選択します。
4. アプリ登録の概要で、[削除] を選択 **します**。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/multi-service-web-app-overview"} -->
## チュートリアル - Azure App Service でセキュリティで保護された Web アプリを構築する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-overview
- Service: identity-platform
- Article date: 2024-02-07
- Summary: このチュートリアルでは、Azure App Service を使用して Web アプリを構築する方法、ユーザーを Web アプリにサインインさせる方法、Azure Storage を呼び出す方法、Microsoft Graph を呼び出す方法について説明します。

このチュートリアルでは、一般的なアプリケーション シナリオである社内の従業員ダッシュボード Web アプリケーションについて説明します。 Web アプリは Azure App Service でホストされており、ダッシュボードで視覚化するデータを取得するには、Microsoft Graph と Azure Storage に接続する必要があります。 場合によっては、Web アプリで、サインインしているユーザーのみがアクセスできるデータを取得する必要があります。 それ以外の場合、Web アプリは、サインインしているユーザーではなく、アプリ自体の ID の下のデータにアクセスする必要があります。 Web アプリケーションへのアクセスは、組織内のユーザーに制限する必要があります。

このチュートリアルの目的は、ダッシュボード自体を構築する方法やデータを視覚化する方法を示 *すことではありません* 。 むしろ、このチュートリアルでは、説明されているシナリオの ID 関連の側面に焦点を当てています。 具体的には、次の方法を学習します。

- [Web アプリの認証を構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-authentication-app-service) し、組織内のユーザーへのアクセスを制限します。 図の A を参照してください。
- マネージド ID を使用して、Web アプリケーションから [Azure Storage に安全にアクセス](https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-access-storage)します。 図の B を参照してください。
- Web アプリケーションから Microsoft Graph のデータにアクセスします (図の C を参照)。
    - [サインインしているユーザーとして](https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-access-microsoft-graph-as-user)
    - マネージド ID を使用する [Web アプリケーションとして](https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-access-microsoft-graph-as-app)
- このチュートリアル[用に作成したリソースをクリーンアップ](https://learn.microsoft.com/ja-jp/entra/identity-platform/multi-service-web-app-clean-up-resources)します。

[Image: Microsoft ID プラットフォームのアプリケーション シナリオを示す図。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/optional-claims"} -->
## オプションのクレームを構成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims
- Service: identity-platform
- Article date: 2026-08-11
- Summary: Microsoft ID プラットフォームによって発行されたアクセス トークンで省略可能な要求と属性を構成する方法について説明します。省略可能な要求は、アプリに役立つユーザー情報を追加できます。

Microsoft Entra によって返されるトークンは、それを要求するクライアントが最適なパフォーマンスを実現できるように、小さく抑えられます。 その結果、一部の要求が規定ではトークンに存在しなくなり、アプリケーションごとに特定して要求する必要があります。

Microsoft Entra 管理センターのアプリケーション UI またはマニフェストを使用して、アプリケーションの省略可能な要求を構成できます。

### 前提条件

- アクティブなサブスクリプションが含まれる Azure アカウント。 [アカウントを無料で作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- [クイック スタートの完了: アプリケーションを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)

### アプリケーションで省略可能な要求を構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**アプリ登録**に移動します。
3. シナリオと目的の結果に基づいて、オプションの要求を構成するアプリケーションを選択します。

## [アプリ UI を続行する](#tab/appui)
1. [ **管理**] で、[ **トークンの構成**] を選択します。
2. [ **省略可能な要求の追加]** を選択します。
3. 構成するトークンの種類 ([アクセス] など) を選択します。
4. 追加する省略可能な要求を選択します。
5. **追加**を選択します。

## [マニフェストを続行する](#tab/manifest)
1. [ **管理**] で、[マニフェスト] を選択 **します**。 Web ベースのマニフェスト エディターが開き、マニフェストを編集できます。 必要に応じて、[ **ダウンロード** ] を選択してマニフェストをローカルで編集し、[ **アップロード]** を使用してアプリケーションに再適用できます。 ファイルに `optionalClaims` プロパティが含まれていない場合は、追加できます。

    次のアプリケーション マニフェストのエントリにより、`auth_time`、`ipaddr`、`upn` の省略可能な要求が、ID、アクセス、および SAML トークンに追加されます。

    ```json
    "optionalClaims": {
        "idToken": [
            {
                "name": "auth_time",
                "essential": false
            }
        ],
        "accessToken": [
            {
                "name": "ipaddr",
                "essential": false
            }
        ],
        "saml2Token": [
            {
                "name": "upn",
                "essential": false
            },
            {
                "name": "extension_ab603c56068041afb2f6832e2a17e237_skypeId",
                "source": "user",
                "essential": false
            }
        ]
    }
    ```
2. 完了したら、[ **保存]** を選択します。 これで、指定されたオプションの要求がアプリケーションのトークンに含まれるようになります。

---

`optionalClaims` オブジェクトは、アプリケーションから要求されるオプションの要求を宣言します。 アプリケーションは、ID トークン、アクセス トークン、SAML 2 トークンで返される、オプションの要求を構成できます。 アプリケーションは、トークンの種類ごとに返される異なる省略可能な要求セットを構成できます。

| 名前 | タイプ | 説明 |
| --- | --- | --- |
| `idToken` | コレクション | JWT ID トークンで返されるオプションのクレーム。 |
| `accessToken` | コレクション | JWT アクセス トークンで返される省略可能な要求。 |
| `saml2Token` | コレクション | SAML トークンで返される省略可能なクレーム。 |

特定の要求でサポートされている場合は、"`additionalProperties`" フィールドを使用してオプションの要求の動作を変更することもできます。

| 名前 | タイプ | 説明 |
| --- | --- | --- |
| `name` | Edm.String | オプションのクレームの名前。 |
| `source` | Edm.String | 要求のソース (ディレクトリ オブジェクト)。 定義済みの要求と、拡張プロパティのユーザー定義の要求があります。 ソース値が null の場合、この要求は定義済みの省略可能な要求です。 ソース値が user の場合、name プロパティの値はユーザー オブジェクトの拡張プロパティです。 |
| `essential` | Edm.Boolean (英語) | 値が true の場合、エンド ユーザーから要求された特定のタスクの承認エクスペリエンスを円滑にするために、クライアントに指定された要求が必要です。 既定値は false です。 |
| `additionalProperties` | コレクション (Edm.String) | 要求のその他プロパティ。 このコレクションにプロパティが存在する場合、name プロパティに指定された省略可能な要求の動作が変更されます。 |

### ディレクトリ拡張機能のオプションの要求を構成する

標準の省略可能な要求セットに加え、Microsoft Graph 拡張機能を含むようにトークンを構成することもできます。 詳細については、「拡張機能を [使用してリソースにカスタム データを追加する](https://learn.microsoft.com/ja-jp/graph/extensibility-overview)」を参照してください。

重要

アクセス トークンは **常に** 、クライアントではなくリソースのマニフェストを使用して生成されます。 `...scope=https://graph.microsoft.com/user.read...` の要求では、リソースは Microsoft Graph API です。 アクセス トークンは、クライアントのマニフェストではなく、Microsoft Graph API マニフェストを使用して作成されます。 アプリケーションのマニフェストを変更しても、Microsoft Graph API のトークンは変更されません。 `accessToken` の変更が反映されたことを検証するには、他のアプリではなく、自分のアプリケーションにトークンを要求します。

オプションの要求では、拡張機能の属性と、ディレクトリ拡張機能がサポートされます。 この機能は、アプリで使用できるユーザー情報をさらに加える場合に便利です。 たとえば、ユーザーが設定したその他の識別子や、重要な構成オプションなどです。 アプリケーション マニフェストがカスタム拡張機能を要求し、MSA ユーザーがアプリにログインした場合、これらの拡張機能は返されません。

#### ディレクトリ拡張の形式

アプリケーション マニフェストを使用してディレクトリ拡張機能の省略可能な要求を構成する場合は、拡張機能の完全な名前 (形式: `extension_<appid>_<attributename>`) を使用します。 `<appid>` は、この要求を必須とするアプリケーションの appId (またはクライアント ID) が削除されたバージョンです。

JWT 内では、このような要求は `extn.<attributename>` という形式の名前で発行されます。 SAML トークン内では、このような要求は `http://schemas.microsoft.com/identity/claims/extn.<attributename>` という形式の URI で発行されます。

### グループのオプションの要求を構成する

このセクションでは、グループ要求で使用されるグループ属性を、既定のグループ objectID からオンプレミス Windows Active Directory から同期される属性へ変更するための、省略可能な要求の構成オプションについて説明します。 Azure portal またはアプリケーション マニフェストを使用して、アプリケーション用にグループのオプションの要求を構成できます。 グループのオプション クレームは、ユーザー プリンシパルに対してのみ JWT に出力されます。 サービス プリンシパルは、JWT で出力されるグループのオプションの要求には含まれません。

重要

トークンで出力されるグループの数は、SAML アサーションの場合は 150、JWT の場合は 200 (入れ子になったグループを含む) に制限されます。 グループの制限と、オンプレミス属性からのグループ要求に関する重要な注意事項の詳細については、「 [アプリケーションのグループ要求を構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-group-claims)する」を参照してください。

Azure portal を使用してグループのオプションの要求を構成するには、次の手順を実行します。

1. オプションの要求を構成するアプリケーションを選択します。
2. [ **管理**] で、[ **トークンの構成**] を選択します。
3. [ **グループ要求の追加] を選択します**。
4. 返すグループの種類 (**セキュリティ グループ**、 **ディレクトリ ロール**、 **すべてのグループ**、 **またはアプリケーションに割り当てられたグループ) を**選択します。
    - **[アプリケーションに割り当てられたグループ]** オプションには、アプリケーションに割り当てられたグループのみが含まれます。 **アプリケーションに割り当てられたグループ** オプションは、トークンのグループ数の制限のため、大規模な組織に推奨されます。 アプリケーションに割り当てられているグループを変更するには、エンタープライズ アプリケーションの一覧から **アプリケーションを** 選択します。 [ **ユーザーとグループ** ] を選択し、[ **ユーザー/グループの追加]** を選択します。 アプリケーションに追加するグループを [ **ユーザーとグループ**] から選択します。
    - **[すべてのグループ]** オプションには **SecurityGroup**、**DirectoryRole**、**DistributionList** が含まれますが、**アプリケーションに割り当てられたグループは含**まれません。
5. 省略可能: 特定のトークンの種類のプロパティを選択して、オンプレミスのグループ属性を含めるようにグループの要求値を変更したり、要求の種類をロールに変更したりします。
6. **[保存] を選択します**。

アプリケーション マニフェストを使用してグループのオプションの要求を構成するには、次の手順を実行します。

1. オプションの要求を構成するアプリケーションを選択します。
2. [ **管理**] で、[マニフェスト] を選択 **します**。
3. マニフェスト エディターを使用して、次のエントリを追加します。

    有効な値は次のとおりです。

    - "All" (このオプションには [SecurityGroup]、[DirectoryRole]、および [DistributionList] が含まれます)
    - セキュリティグループ
    - ディレクトリの役割
    - "ApplicationGroup " (このオプションには、アプリケーションに割り当てられているグループのみが含まれます)

    次に例を示します。

    ```json
    "groupMembershipClaims": "SecurityGroup"
    ```

    既定では、グループ オブジェクト ID はグループ クレーム値に出力されます。 オンプレミス グループ属性を含むように要求の値を変更する、または要求の種類をロールに変更するには、次のように `optionalClaims` 構成を使用します。
4. グループ名の設定をオプションの要求に設定します。

    トークン内のグループに、[オプションの要求] セクションのオンプレミス グループ属性を含める場合は、オプションの要求を適用する必要があるトークンの種類を指定します。 また、要求されるオプションの要求の名前と、必要なその他プロパティも指定します。

    次に示す複数のトークンの種類が、一覧に表示される可能性があります。

    - `idToken` (OIDC ID トークンの場合)
    - `accessToken` (OAuth アクセス トークンの場合)
    - `Saml2Token` (SAML トークンの場合)。

    `Saml2Token` の種類は、SAML 1.1と SAML 2.0 の両方の形式のトークンに適用されます。

    関連する各トークンの種類に対して、マニフェストの `optionalClaims` セクションを使用するようにグループ要求を変更します。 `optionalClaims` スキーマは次のとおりです。

    ```json
    {
        "name": "groups",
        "source": null,
        "essential": false,
        "additionalProperties": []
    }
    ```

    | 省略可能な要求のスキーマ | 値 |
    | --- | --- |
    | `name` | `groups` である必要があります。 |
    | `source` | 使用されていません。 省略するか、null 値を指定します。 |
    | `essential` | 使用されていません。 省略するか、false を指定します。 |
    | `additionalProperties` | その他プロパティの一覧。 有効なオプションは`sam_account_name`、`dns_domain_and_sam_account_name`、`netbios_domain_and_sam_account_name`、`emit_as_roles`、`cloud_displayname`です。 |

    `additionalProperties` では、`sam_account_name`、`dns_domain_and_sam_account_name`、`netbios_domain_and_sam_account_name` のいずれか 1 つのみが必須です。 複数ある場合、最初の 1 つが使用され、それ以外は無視されます。 また、`cloud_displayname` を追加して、クラウド グループの表示名を出力することもできます。 このオプションは `groupMembershipClaims` が `ApplicationGroup` に設定されている場合にのみ機能します。

    アプリケーションによっては、ロール要求内にユーザーに関するグループ情報が必要になります。 要求の種類をグループ要求からロール要求に変更するには、`emit_as_roles` を `additionalProperties` に追加します。 グループの値が、ロール要求内に出力されます。

    `emit_as_roles` が使用された場合、ユーザー (またはリソース アプリケーション) が割り当て済みとして構成されているアプリケーション ロールは、ロール要求に含まれません。

次の例は、グループ要求のマニフェスト構成を示しています。

`dnsDomainName\sAMAccountName` の形式で、OAuth アクセス トークンのグループ名としてグループを出力します。

```json
"optionalClaims": {
    "accessToken": [
        {
            "name": "groups",
            "additionalProperties": [
                "dns_domain_and_sam_account_name"
            ]
        }
    ]
}
```

SAML および OIDC ID のトークンでロール要求として `netbiosDomain\sAMAccountName` 形式で返されるグループ名を出力します。

```json
"optionalClaims": {
    "saml2Token": [
        {
            "name": "groups",
            "additionalProperties": [
                "netbios_domain_and_sam_account_name",
                "emit_as_roles"
            ]
        }
    ],
    "idToken": [
        {
            "name": "groups",
            "additionalProperties": [
                "netbios_domain_and_sam_account_name",
                "emit_as_roles"
            ]
        }
    ]
}
```

オンプレミス同期グループの `sam_account_name` 形式のグループ名、SAML クラウド グループの `cloud_display` 名、アプリケーションに割り当てられたグループの OIDC ID トークンを出力します。

```json
"groupMembershipClaims": "ApplicationGroup",
"optionalClaims": {
    "saml2Token": [
        {
            "name": "groups",
            "additionalProperties": [
                "sam_account_name",
                "cloud_displayname"
            ]
        }
    ],
    "idToken": [
        {
            "name": "groups",
            "additionalProperties": [
                "sam_account_name",
                "cloud_displayname"
            ]
        }
    ]
}
```

### 省略可能な要求の例

アプリケーションの ID 構成に関するプロパティを更新し、省略可能な要求を有効にして構成するには、複数のオプションがあります。

- Azure portal で実行できます
- マニフェストを使用できます。
- [Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/use-the-api) を使用してアプリケーションを更新するアプリケーションを記述することもできます。 Microsoft Graph API リファレンス ガイドの [OptionalClaims](https://learn.microsoft.com/ja-jp/graph/api/resources/optionalclaims) 型は、省略可能な要求の構成に役立ちます。

次の例では、Azure portal とマニフェストを使用して、アプリケーション用のアクセス、ID、SAML トークンにオプションの要求を追加します。 アプリケーションが受け取ることができるトークンの型ごとに、さまざまなオプションの要求が追加されます。

- ID トークンには、完全な形式 (`<upn>_<homedomain>#EXT#@<resourcedomain>`) でフェデレーション ユーザーの UPN が含まれます。
- 他のクライアントがこのアプリケーションに要求するアクセス トークンには、`auth_time` 要求が含まれます。
- SAML トークンには `skypeId` ディレクトリ スキーマ拡張機能が含まれます (この例では、このアプリの ID は `ab603c56068041afb2f6832e2a17e237` です)。 SAML トークンは Skype ID を `extension_ab603c56068041afb2f6832e2a17e237_skypeId` として公開します。

Azure portal で要求を構成します。

1. オプションの要求を構成するアプリケーションを選択します。
2. [ **管理**] で、[ **トークンの構成**] を選択します。
3. [ **省略可能な要求の追加]** を選択し、 **ID** トークンの種類を選択し、要求の一覧から **upn** を選択して、[ **追加**] を選択します。
4. [ **省略可能な要求の追加]** を選択し、[ **アクセス** トークンの種類] を選択し、要求の一覧から **auth\_time** を選択してから、[ **追加]** を選択します。
5. [トークン構成の概要] 画面で、 **upn** の横にある鉛筆アイコンを選択し、[ **外部認証** ] トグルを選択して、[保存] を選択 **します**。
6. [ **省略可能な要求の追加]** を選択し、 **SAML** トークンの種類を選択し、要求の一覧から **extn.skypeID** を選択し (skypeID という Microsoft Entra ユーザー オブジェクトを作成した場合にのみ適用されます)、[ **追加**] を選択します。

マニフェストで要求を構成します。

1. オプションの要求を構成するアプリケーションを選択します。
2. [ **管理**] で [ **マニフェスト** ] を選択し、インライン マニフェスト エディターを開きます。
3. このエディターを使用して、マニフェストを直接編集できます。 マニフェストは [Application エンティティ](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest)のスキーマに従い、保存されるとマニフェストを自動的に書式設定します。 新しい要素が `optionalClaims` プロパティに追加されます。

    ```json
    "optionalClaims": {
        "idToken": [
            {
                "name": "upn",
                "essential": false,
                "additionalProperties": [
                    "include_externally_authenticated_upn"
                ]
            }
        ],
        "accessToken": [
            {
                "name": "auth_time",
                "essential": false
            }
        ],
        "saml2Token": [
            {
                "name": "extension_ab603c56068041afb2f6832e2a17e237_skypeId",
                "source": "user",
                "essential": true
            }
        ]
    }
    ```
4. マニフェストの更新が完了したら、[ **保存]** を選択してマニフェストを保存します。

### AMR 要求

`amr` (認証方法参照) 要求は、ユーザーの認証方法を識別します。 `amr`要求は Salesforce アプリケーションに対して既定で送信されるため、これらのアプリの構成変更は必要ありません。 他のすべての SAML アプリケーションの場合、アプリケーション管理者は、AMR 要求を要求するために、オプションの `amr` 要求と `include_granular_amr` 追加プロパティをアプリ登録に追加する必要があります。 `multipleauthn`と`mfa`の値は、ユーザーが MFA を完了したときにのみ出力されます。

#### SAML アプリケーションの詳細な AMR 値を構成する

Microsoft Entra 管理センターでは現在、`include_granular_amr`の UI オプションは提供されていません。 アプリケーション マニフェストで、またはMicrosoft Graphを使用して、このプロパティを構成します。 `include_granular_amr` プロパティは、SAML トークンで詳細な認証方法の値を出力するように`amr`要求を変更します。

アプリケーション マニフェストを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**Entra ID**&gt;**アプリの登録**に移動します。
2. アプリの登録を選択します。
3. [ **管理**] で、[マニフェスト] を選択 **します**。
4. 次の構成で、 `optionalClaims` プロパティを追加または更新します。 アプリケーションに必要な既存の省略可能な要求を保持します。

    ```json
    "optionalClaims": {
        "saml2Token": [
            {
                "name": "amr",
                "essential": false,
                "additionalProperties": [
                    "include_granular_amr"
                ]
            }
        ]
    }
    ```
5. **[保存] を選択します**。

または、Microsoft Graph [Update アプリケーション](https://learn.microsoft.com/ja-jp/graph/api/application-update) API を使用します。 `{applicationObjectId}`をアプリケーション登録のオブジェクト ID に置き換えます。 要求本文に保持する既存の省略可能な要求構成を含めます。

```http
PATCH https://graph.microsoft.com/v1.0/applications/{applicationObjectId}
Content-Type: application/json

{
    "optionalClaims": {
        "saml2Token": [
            {
                "name": "amr",
                "essential": false,
                "additionalProperties": [
                    "include_granular_amr"
                ]
            }
        ]
    }
}
```

SAML 要求の詳細については、 [authnmethodreferences](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol#authnmethodreferences) を参照してください。

#### OIDC アプリケーションの AMR 要求を構成する

OpenID Connect (OIDC) v2.0 アプリケーションの場合は、 `amr` 省略可能な要求を、アプリケーションに必要なトークンの種類に追加します。 `include_granular_amr` プロパティは SAML アプリケーションにのみ適用され、OIDC アプリケーションには必要ありません。 次のアプリケーション マニフェストは、ID とアクセス トークンの両方で `amr` 要求を要求します。

```json
"optionalClaims": {
    "idToken": [
        {
            "name": "amr",
            "essential": false
        }
    ],
    "accessToken": [
        {
            "name": "amr",
            "essential": false
        }
    ]
}
```

### 制限事項

アプリケーションは、省略可能な要求として最大 10 個の拡張属性を発行できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/optional-claims-reference"} -->
## 省略可能な要求参照 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/optional-claims-reference
- Service: identity-platform
- Article date: 2026-07-22
- Summary: Microsoft ID プラットフォームのトークンに含めることができる省略可能な要求の詳細を含む要求参照。

省略可能な要求を使用すると、次のことができます。

- アプリケーションのトークンに含める要求を選択します。
- Microsoft ID プラットフォームがトークンで返す特定の要求の動作を変更します。
- アプリケーションのカスタム要求を追加してアクセスします。

オプションの要求は、v1.0 形式のトークンと v2.0 形式のトークンと SAML トークンの両方でサポートされますが、v1.0 から v2.0 への移行時にほとんどの値が提供されます。 Microsoft ID プラットフォームでは、クライアントによる最適なパフォーマンスを確保するために、より小さいトークン サイズが使用されます。 その結果、以前はアクセス トークンと ID トークンに含まれていたクレームがいくつか v2.0 トークンに存在しなくなり、特にアプリケーションごとに要求する必要があります。

| アカウントの種類 | v1.0 トークン | v2.0 トークン |
| --- | --- | --- |
| 個人用Microsoft アカウント | なし | サポート |
| Microsoft Entra アカウント | サポート | サポート |

### v1.0 と v2.0 の省略可能な要求セット

アプリケーションが使用するために既定で使用できる省略可能な要求のセットを次の表に示します。 拡張属性とディレクトリ拡張機能でカスタム データを使用して、アプリケーションの省略可能な要求を追加できます。 アクセス トークンに要求を追加すると、要求は、アプリケーション によって要求された要求 ではなく、アプリケーション (Web API) 要求されたアクセス トークンに適用されます。 クライアントが API にアクセスする方法に関係なく、API に対する認証に使用されるアクセス トークンに適切なデータが存在します。

手記

これらの要求の大部分は、v1.0 および v2.0 トークンの JWT に含めることができますが、[トークンの種類] 列に記載されている場合を除き、SAML トークンには含まれません。 コンシューマー アカウントは、[ユーザーの種類] 列にマークされた、これらの要求のサブセットをサポートします。 一覧表示されている要求の多くはコンシューマー ユーザーには適用されません (テナントがないため、`tenant_ctry` には値がありません)。

次の表に、v1.0 と v2.0 の省略可能な要求セットを示します。

| 名前 | 形容 | トークンの種類 | ユーザーの種類 | 筆記 |
| --- | --- | --- | --- | --- |
| `acct` | テナントのユーザー アカウントの状態 | JWT、SAML |  | ユーザーがテナントのメンバーである場合、値は `0`。 ゲストの場合、値は `1`です。 |
| `acrs` | 認証コンテキスト ID | JWT | Microsoft Entra ID | ベアラーが実行できる操作の認証コンテキスト ID を示します。 認証コンテキスト ID を使用して、アプリケーションとサービス内からステップアップ認証の要求をトリガーできます。 多くの場合、`xms_cc` 要求と共に使用されます。 |
| `auth_time` | ユーザーが最後に認証された時刻。 | JWT |  |  |
| `ctry` | ユーザーの国/地域 | JWT |  | この要求が存在し、フィールドの値が標準の 2 文字の国/地域コード (FR、JP、SZ など) である場合に返されます。 |
| `email` | このユーザーの報告された電子メール アドレス | JWT、SAML | MSA、Microsoft Entra ID | ユーザーがテナントのゲストである場合、この値は既定で含まれます。 マネージド ユーザー (テナント内のユーザー) の場合は、この省略可能な要求を通じて要求するか、v2.0 でのみ OpenID スコープで要求する必要があります。 この値は正しいことは保証されておらず、時間の経過と同時に変更可能です。承認やユーザーのデータの保存には使用しないでください。 詳細については、「[このデータ](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)にアクセスする権限をユーザーが持っているかどうかを検証する」を参照してください。 アプリでアドレス指定可能なメール アドレスが必要な場合は、この要求を提案または UX の事前入力として使用して、ユーザーに直接このデータを要求します。 |
| `fwd` | IP アドレス | JWT |  | 要求元のクライアントの元のアドレスを追加します (VNET 内の場合)。 |
| `groups` | グループ要求のオプションの書式設定 | JWT、SAML |  | `groups` 要求は、[アプリケーション マニフェスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest)の GroupMembershipClaims 設定と共に使用されます。この設定も必要です。 |
| `idtyp` | トークンの種類 | JWT アクセス トークン | 特殊: アプリ専用アクセス トークン内のみ | トークンがアプリ専用トークンの場合、値は `app` されます。 この要求は、API がトークンがアプリ トークンかアプリ +ユーザー トークンかを判断するための最も正確な方法です。 |
| `login_hint` | ログイン ヒント | JWT | MSA、Microsoft Entra ID | base 64 でエンコードされた不透明で信頼性の高いログイン ヒント要求。 この値は変更しないでください。 この要求は、SSO を取得するためにすべてのフローで `login_hint` OAuth パラメーターに使用するのに最適な値です。 アプリケーション間で渡して、アプリケーション間でサイレント SSO を支援することもできます。アプリケーション A は、ユーザーのサインイン、`login_hint` 要求の読み取り、アプリケーション B へのリンクをユーザーが選択したときにクエリ文字列またはフラグメント内のアプリケーション B に要求と現在のテナント コンテキストを送信できます。競合状態と信頼性の問題を回避するために、`login_hint` 要求 *には、ユーザーの現在のテナントが含まれていない*、既定ではユーザーのホーム テナントが使用されます。 ユーザーが別のテナントから来たゲスト シナリオでは、サインイン要求でテナント識別子を指定する必要があります。 を使用して、パートナーのアプリに同じ情報を渡します。 この要求は、SDK の既存の `login_hint` 機能で使用することを目的としていますが、公開されています。 |
| `tenant_ctry` | リソース テナントの国/リージョン | JWT |  | 管理者がテナント レベルで設定する場合を除き、`ctry` と同じです。また、標準の 2 文字の値にする必要があります。 |
| `tenant_region_scope` | リソース テナントのリージョン | JWT |  |  |
| `upn` | ユーザープリンシパルネーム | JWT、SAML |  | `username_hint` パラメーターで使用できるユーザーの識別子。 ユーザーの永続的な識別子ではなく、承認やユーザー情報を一意に識別するために使用しないでください (データベース キーなど)。 代わりに、データベース キーとしてユーザー オブジェクト ID (`oid`) を使用します。 詳細については、「[要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-validation)を検証してアプリケーションと API をセキュリティで保護する」を参照してください。 [代替ログイン ID](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-use-email-signin) を使用してサインインするユーザーには、ユーザー プリンシパル名 (UPN) を表示しないでください。 代わりに、ユーザーにサインイン状態を表示するには、次の ID トークン要求を使用します。v1 トークンの場合は `preferred_username` または `unique_name`、v2 トークンの場合は `preferred_username`。 この要求は自動的に含まれますが、ゲスト ユーザー の場合の動作を変更するために他のプロパティをアタッチするオプションの要求として指定できます。 `login_hint` 使用には、`login_hint` 要求を使用する必要があります。UPN などの人間が判読できる識別子は信頼できません。 |
| `verified_primary_email` | ユーザーの PrimaryAuthoritativeEmail から提供される | JWT |  |  |
| `verified_secondary_email` | ユーザーの SecondaryAuthoritativeEmail からソース | JWT |  |  |
| `vnet` | VNET 指定子情報。 | JWT |  |  |
| `xms_cc` | クライアント機能 | JWT | Microsoft Entra ID | トークンを取得したクライアント アプリケーションがクレーム チャレンジを処理できるかどうかを示します。 クレーム `acrs`と共によく使用されます。 この要求は、条件付きアクセスと継続的アクセス評価のシナリオでよく使用されます。 トークンが発行されるリソース サーバーまたはサービス アプリケーションは、トークン内にこの要求が存在することを制御します。 アクセス トークンの `cp1` 値は、クライアント アプリケーションが要求チャレンジを処理できることを識別する権限のある方法です。 詳細については、「[要求チャレンジ、要求要求、およびクライアント機能](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-challenge?tabs=dotnet)を参照してください。 |
| `xms_edov` | ユーザーのメール ドメイン所有者が確認済みかどうかを示すブール値。 | JWT |  | ユーザー アカウントが存在し、テナント管理者がドメインの検証を行ったテナントに属している場合、電子メールはドメイン検証済みと見なされます。 また、電子メールは、Microsoft アカウント (MSA)、Google アカウント、またはワンタイム パスコード (OTP) フローを使用した認証に使用する必要があります。 Facebook アカウントと SAML/WS-Fed アカウント **、検証済みドメイン** はありません。 この要求をトークンで返すには、`email` 要求が存在する必要があります。 |
| `xms_pdl` | 優先されるデータの場所 | JWT |  | 複数地域テナントの場合、優先されるデータの場所は、ユーザーが存在する地理的リージョンを示す 3 文字のコードです。 詳細については、[Microsoft Entra Connect の推奨されるデータの場所に関するドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-preferreddatalocation)を参照してください。 |
| `xms_pl` | ユーザー優先言語 | JWT |  | ユーザーの優先言語 (設定されている場合)。 ゲスト アクセス シナリオで、ホーム テナントから提供されます。 書式設定された LL-CC ("en-us")。 |
| `xms_tpl` | テナント優先言語 | JWT |  | リソース テナントの優先言語 (設定されている場合)。 書式設定された LL ("en")。 |
| `ztdid` | ゼロタッチ展開 ID | JWT |  | `Windows AutoPilot` に使用されるデバイス ID。 |

警告

`email` または `upn` 要求値を使用して、アクセス トークン内のユーザーがデータにアクセスできるようにする必要があるかどうかを格納または判断しないでください。 このような変更可能な要求値は時間の経過と同時に変化し、承認に対して安全で信頼性が低い可能性があります。

### v2.0 固有の省略可能な要求セット

これらの要求は常に v1.0 トークンに含まれますが、要求されない限り v2.0 トークンには含まれません。 これらの要求は、JWT (ID トークンとアクセス トークン) にのみ適用されます。

| JWT 要求 | 名前 | 形容 | 筆記 |
| --- | --- | --- | --- |
| `ipaddr` | IPアドレス | クライアントがログインした IP アドレス。 |  |
| `onprem_sid` | オンプレミスのセキュリティ識別子 |  |  |
| `pwd_exp` | パスワードの有効期限 | パスワードの有効期限が切れる `iat` 要求の時刻から経過した秒数。 この要求は、パスワードの有効期限が近づいている場合にのみ含まれます (パスワード ポリシーの "通知日" で定義)。 |  |
| `pwd_url` | パスワードの URL を変更する | ユーザーが自分のパスワードを変更するためにアクセスできる URL。 この要求は、パスワードの有効期限が近づいている場合にのみ含まれます (パスワード ポリシーの "通知日" で定義)。 |  |
| `in_corp` | 企業ネットワーク内 | クライアントが企業ネットワークからログインしているかどうかを通知します。 そうでない場合、要求は含まれません。 | MFA の [信頼できる IP](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-mfasettings#trusted-ips) 設定に基づきます。 |
| `family_name` | 名字 | ユーザー オブジェクトで定義されているユーザーの姓、姓、またはファミリ名を提供します。 たとえば、`"family_name":"Miller"`します。 | MSA およびMicrosoft Entra IDでサポートされます。 `profile` スコープが必要です。 |
| `given_name` | 名前 | ユーザー オブジェクトに設定された、ユーザーの最初の名前または "指定された" 名前を提供します。 たとえば、`"given_name": "Frank"`します。 | MSA およびMicrosoft Entra IDでサポートされます。 `profile` スコープが必要です。 |
| `upn` | ユーザー プリンシパル名 | `username_hint` パラメーターで使用できるユーザーの識別子。 ユーザーの永続的な識別子ではなく、承認やユーザー情報を一意に識別するために使用しないでください (データベース キーなど)。 詳細については、「[要求](https://learn.microsoft.com/ja-jp/entra/identity-platform/claims-validation)を検証してアプリケーションと API をセキュリティで保護する」を参照してください。 代わりに、データベース キーとしてユーザー オブジェクト ID (`oid`) を使用します。 [代替ログイン ID](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-use-email-signin) を使用してサインインするユーザーには、ユーザー プリンシパル名 (UPN) を表示しないでください。 代わりに、次の `preferred_username` 要求を使用して、ユーザーにサインイン状態を表示します。 | `profile` スコープが必要です。 |
| `amr` | 認証方法リファレンス | トークンのサブジェクトが認証された方法を識別します。 認証方法ごとに出力Microsoft Entra ID値については、Microsoft Entra認証方法の AMR 値を参照してください。 |  |

### v1.0 固有の省略可能な要求セット

v2 トークン形式の機能強化の一部は、セキュリティと信頼性の向上に役立つため、v1 トークン形式を使用するアプリで利用できます。 これらの機能強化は、SAML トークンではなく JWT にのみ適用されます。

| JWT 要求 | 名前 | 形容 | 筆記 |
| --- | --- | --- | --- |
| `aud` | 聴衆 | JWT には常に存在しますが、v1 アクセス トークンでは、さまざまな方法で出力できます。つまり、appID URI、末尾のスラッシュの有無、リソースのクライアント ID などです。 このランダム化は、トークン検証の実行時にコーディングが困難になる場合があります。 この要求の `additionalProperties` を使用して、v1 アクセス トークン内のリソースのクライアント ID が常に設定されていることを確認します。 | v1 JWT アクセス トークンのみ |
| `preferred_username` | 優先ユーザー名 | v1 トークン内の優先ユーザー名要求を提供します。 この要求により、トークンの種類に関係なく、アプリでユーザー名ヒントを提供し、人間が判読できる表示名を簡単に表示できるようになります。 使用、`upn`、または `unique_name`ではなく、この省略可能な要求を使用することをお勧めします。 | v1 ID トークンとアクセス トークン |

#### 省略可能な要求の `additionalProperties`

一部の省略可能な要求は、要求の返し方を変更するように構成できます。 これらの `additionalProperties` は、データの期待が異なるオンプレミス アプリケーションの移行を支援するために主に使用されます。 たとえば、`include_externally_authenticated_upn_without_hash` は、UPN でハッシュ マーク (`#`) を処理できないクライアントに役立ちます。

| プロパティ名 | `additionalProperty`名 | 形容 |
| --- | --- | --- |
| `upn` |  | SAML 応答と JWT 応答の両方、および v1.0 トークンと v2.0 トークンの両方に使用できます。 |
|  | `include_externally_authenticated_upn` | リソース テナントに格納されているゲスト UPN が含まれます。 たとえば、`foo_hometenant.com#EXT#@resourcetenant.com`します。 |
|  | `include_externally_authenticated_upn_without_hash` | 前に示したのと同じですが、ハッシュ マーク (`#`) がアンダースコア (`_`) に置き換えられる点が異なります (例: `foo_hometenant.com_EXT_@resourcetenant.com`)。 |
| `aud` |  | v1 アクセス トークンでは、この要求は、`aud` 要求の形式を変更するために使用されます。 この要求は、v2 トークンまたはバージョンの ID トークンには影響しません。ここで、`aud` 要求は常にクライアント ID です。 この構成を使用して、API が対象ユーザーの検証をより簡単に実行できるようにします。 アクセス トークンに影響を与えるすべての省略可能な要求と同様に、リソースはアクセス トークンを所有しているため、要求内のリソースはこの省略可能な要求を設定する必要があります。 |
|  | `use_guid` | リソース (API) のクライアント ID を GUID 形式で出力します。これは、実行時に依存するのではなく、常に `aud` 要求として出力されます。 たとえば、リソースがこのフラグを設定し、そのクライアント ID が `00001111-aaaa-2222-bbbb-3333cccc4444`されている場合、そのリソースのアクセス トークンを要求するすべてのアプリは、`aud` : `00001111-aaaa-2222-bbbb-3333cccc4444`を持つアクセス トークンを受け取ります。 この要求が設定されていない場合、API は、`aud`、`api://MyApi.com`、`api://MyApi.com/`、またはその API のアプリ ID URI として設定されたその他の値、およびリソースのクライアント ID の `api://myapi.com/AdditionalRegisteredField` 要求を持つトークンを取得できます。 |
| `idtyp` |  | この要求は、トークンの種類 (アプリ、ユーザー、デバイス) を取得するために使用されます。 既定では、アプリ専用トークンに対してのみ出力されます。 アクセス トークンに影響を与えるすべての省略可能な要求と同様に、リソースはアクセス トークンを所有しているため、要求内のリソースはこの省略可能な要求を設定する必要があります。 |
|  | `include_user_token` | ユーザー トークンの `idtyp` 要求を出力します。 idtyp 要求セットにこの省略可能な追加プロパティがない場合、API はアプリ トークンの要求のみを取得します。 |

##### `additionalProperties` の例

```json
"optionalClaims": {
    "idToken": [
        {
            "name": "upn",
            "essential": false,
            "additionalProperties": [
                "include_externally_authenticated_upn"
            ]
        }
    ]
}
```

この `optionalClaims` オブジェクトにより、クライアントに返された ID トークンに、もう一方のホーム テナントとリソース テナントの情報を含む `upn` 要求が含まれます。 `upn` 要求は、ユーザーがテナント内のゲスト (認証に別の IDP を使用する) の場合にのみ、トークンで変更されます。

### Microsoft Entra認証方法の AMR 値

`amr` (認証方法参照) 要求は、ユーザーの認証方法を識別します。 `amr`要求は Salesforce アプリケーションに対して既定で送信されるため、これらのアプリの構成変更は必要ありません。 他のすべての SAML アプリケーションの場合、アプリケーション管理者は、AMR 要求を要求するために、オプションの `amr` 要求と `include_granular_amr` 追加プロパティをアプリ登録に追加する必要があります。 `multipleauthn`と`mfa`の値は、ユーザーが MFA を完了したときにのみ出力されます。

SAML の詳細については、 [authnmethodreferences](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol#authnmethodreferences) を参照してください。

OIDC については、次の表を参照してください。各認証方法で送信Microsoft Entra ID`authnmethodsreferences`値の一覧を示します。

| Microsoft Entra の認証方法 | OIDC v2.0 トークン要求 |
| --- | --- |
| パスワード | `pwd` |
| Authenticator プッシュ | `rsa`、`ngcmfa`、`mfa` |
| Authenticator TOTP | `totp`、`mfa` |
| ハードウェア OATH トークン | `hotp`、`mfa` |
| 電話によるサインイン (パスワードレス認証アプリ) | `swk`、`rsa`、`mfa` |
| SMS | `sms`、`mfa` |
| 通話 | `tel`、`mfa` |
| Email | `emailotp`、`mfa` |
| FIDO2 セキュリティ キー (PRMFA) | `fido`、`mfa` |
| Passkey (デバイス バインド) (PRMFA) | `fido`、`mfa` |
| Passkey (synced) (PRMFA) | `fido`、`mfa` |
| Windows Hello for Business (PRMFA) | `hwk`、`mfa`、`ngcmfa` |
| 証明書ベースの認証 (多要素 CBA の PRMFA) | `hwk` (多要素 CBA) または `x509` (単一因子 CBA)、 `mfa`、 `rsa` |
| 一時アクセス パス (TAP) | `otp`、`mfa` |
| Windows統合認証 (Kerberos) | `wia` |
| デバイス ベースの X509 認証 | `x509` |

Microsoftには、単一要素 Certificate-Based 認証 (CBA) とデバイス ベースの X.509 認証の両方の`amr`要求に`x509`が含まれています。 ただし、x509 だけが存在しても、フィッシングに強い MFA (PRMFA) とは見なされません。 PRMFA 要件を満たすために、ユーザーは追加の MFA 要素を完了する必要もあります。これは、認証コンテキストの他の認証方法インジケーターによって反映されます。 Microsoft Entra IDは、外部 MFA プロバイダーから送信された`amr`値と、Microsoft Entra IDで実行される認証方法の`amr`値を転送します。 詳細については、「 [サポートされている AMR 要求](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-external-method-provider#supported-amr-claims)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/permissions-consent-overview"} -->
## Microsoft ID プラットフォームでのアクセス許可と同意の概要 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview
- Service: identity-platform
- Article date: 2025-03-18
- Summary: Microsoft ID プラットフォームでの同意とアクセス許可に関する基本的な概念とシナリオについて説明します

Microsoft ID プラットフォームでは、保護されたリソースへのアクセスを必要とするセキュリティで保護されたアプリケーションを開発するために、アクセス許可と同意を理解することが重要です。 この記事では、アクセス許可と同意に関連する基本的な概念とシナリオの概要について説明し、アプリケーション開発者がユーザーと管理者に必要な承認を要求するのに役立ちます。 これらの概念を理解することで、アプリケーションが必要とするアクセスのみを要求し、信頼とセキュリティを確保できます。

電子メールや予定表データなどの保護されたリソースにアクセスするには、アプリケーションでリソース所有者の承認が必要です。 リソース所有者は、アプリの要求に同意または拒否できます。 これらの基本的な概念を理解すると、ユーザーと管理者から必要なアクセスのみを要求する、より安全で信頼できるアプリケーションを構築するのに役立ちます。

### アクセスのシナリオ

アプリケーション開発者は、アプリケーションがデータにアクセスする方法を特定する必要があります。 アプリケーションでは、サインイン ユーザーの代わりに動作する委任アクセス、またはアプリケーションの専用 ID としてのみ動作するアプリ専用のアクセスを使用できます。

[Image: アクセス シナリオの図を示す画像。]

#### 委任アクセス (ユーザーの代わりにアクセス)

このアクセス シナリオでは、ユーザーはクライアント アプリケーションにサインインしています。 クライアント アプリケーションは、ユーザーの代わりにリソースにアクセスします。 委任アクセスでは、委任されたアクセス許可が必要です。 クライアントとユーザーの両方に、要求を行うための許可を個別に付与する必要があります。 委任アクセスのシナリオの詳細については、[委任アクセスのシナリオ](https://learn.microsoft.com/ja-jp/entra/identity-platform/delegated-access-primer)に関する記事を参照してください。

クライアント アプリの場合、適切な委任されたアクセス許可を付与する必要があります。 委任されたアクセス許可は、スコープとも呼ばれます。 スコープは、クライアント アプリケーションでユーザーの代わりにアクセスできる対象を表す、特定のリソースのアクセス許可です。 スコープについて詳しくは、「[スコープとアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc)」を参照してください。

ユーザーの場合、承認は、ユーザーがリソースにアクセスするために付与される特権に依存します。 たとえば、[Microsoft Entra のロールベースのアクセス制御 (RBAC)](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview) によって、ディレクトリ リソースにアクセスするための許可をユーザーに付与でき、また Exchange Online の RBAC によって、メールと予定表のリソースにアクセスするための許可をユーザーに付与できます。 アプリケーションの RBAC の詳細については、「アプリケーションの [RBAC」](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-rbac-for-developers)を参照してください。

#### アプリ専用のアクセス (ユーザーが関与しないアクセス)

このアクセス シナリオでは、ユーザーがサインインしていない状態でアプリケーションが単独で動作します。 アプリケーション アクセスは、自動化やバックアップなどのシナリオで使用されます。 このシナリオには、バックグラウンド サービスやデーモンとして実行されるアプリが含まれます。 特定のユーザーをサインインさせたくない場合や、複数のユーザーに対してデータが必要になる場合に適しています。 アプリ専用アクセス シナリオの詳細については、「 [アプリ専用アクセス](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-only-access-primer)」を参照してください。

アプリ専用のアクセスでは、委任されたスコープではなくアプリ ロールが使用されます。 同意によって付与されたアプリ ロールは、アプリケーションのアクセス許可とも呼ばれます。 クライアント アプリには、呼び出し先となるリソース アプリの適切なアプリケーション アクセス許可を付与する必要があります。 付与後、クライアント アプリでは、要求されたデータにアクセスできます。 クライアント アプリケーションへのアプリ ロールの割り当てについて詳しくは、「[アプリケーションへのアプリ ロールの割り当て](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#assign-app-roles-to-applications)」を参照してください。

### アクセス許可の種類

**委任されたアクセス許可**は、委任アクセス シナリオで使用されます。 これらは、ユーザーの代わりにアプリケーションが動作できるようにするアクセス許可です。 アプリケーションは、サインインしているユーザーがアクセスできなかったものにアクセスできません。

たとえば、ユーザーに代わって委任されたアクセス許可 `Files.Read.All` 付与されたアプリケーションを取得します。 アプリケーションは、ユーザーが個人的にアクセスできるファイルのみを読み取ることができます。

**アプリケーションのアクセス許可** (アプリ ロールとも呼ばれる) は、サインイン ユーザーが関与しないアプリ専用のアクセスのシナリオで使用されます。 アプリケーションは、アクセス許可が関連付けられているすべてのデータにアクセスできます。

たとえば、Microsoft Graph API のアプリケーションアクセス許可 `Files.Read.All` 付与されたアプリケーションは、Microsoft Graph を使用してテナント内の任意のファイルを読み取ることができるとします。 一般的に、ある API のサービス プリンシパルの管理者または所有者のみが、その API によって公開されるアプリケーションのアクセス許可に同意できます。

#### 委任されたアクセス許可とアプリケーションのアクセス許可の比較

| アクセス許可の種類 | 委任されたアクセス許可 | アプリケーションのアクセス許可 |
| --- | --- | --- |
| アプリの種類 | Web/モバイル/シングルページ アプリ (SPA) | Web/デーモン |
| アクセスのコンテキスト | ユーザーの代理でアクセスを取得する | ユーザーなしでアクセスを取得する |
| 同意できるユーザー | - ユーザーは自分のデータに同意できます  - 管理者はすべてのユーザーに同意できます | 管理者のみが同意できます |
| 同意の方法 | - 静的: アプリ登録時に構成されたリスト  - 動的: サインイン時に個々のアクセス許可を要求する | - 静的のみ: アプリ登録時に構成されたリスト |
| その他の名前 | - スコープ  - OAuth2 アクセス許可のスコープ | - アプリ ロール  - アプリ専用のアクセス許可 |
| 同意の結果 (Microsoft Graph に固有) | [OAuth2PermissionGrant](https://learn.microsoft.com/ja-jp/graph/api/resources/oauth2permissiongrant) | [appRoleAssignment](https://learn.microsoft.com/ja-jp/graph/api/resources/approleassignment) |

### 同意

アプリケーションにアクセス許可を付与する方法の 1 つは、同意による方法です。 同意とは、アプリケーションが保護されたリソースにアクセスすることを、ユーザーまたは管理者が承認するプロセスです。 たとえば、ユーザーが初めてアプリケーションにサインインしようとしたとき、そのアプリケーションで、ユーザーのプロファイルを表示し、ユーザーのメールボックスの内容を読み取るためのアクセス許可を要求できます。 ユーザーには、同意プロンプトを通じて、アプリが要求しているアクセス許可の一覧が表示されます。 同意プロンプトがユーザーに表示されるその他のシナリオは次のとおりです。

- 以前に許可された同意が取り消されたとき。
- サインイン中に特に同意を求めるようにアプリケーションがコーディングされているとき。
- アプリケーションが動的な同意を使って、実行時に必要に応じて新しいアクセス許可を要求する場合。

同意プロンプトの主要な詳細情報は、アプリケーションが必要とするアクセス許可の一覧とパブリッシャー情報です。 管理者とエンド ユーザーの両方の同意プロンプトと同意エクスペリエンスの詳細については、 [アプリケーションの同意エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/application-consent-experience)に関するページを参照してください。

#### ユーザーの同意

ユーザーの同意は、ユーザーがアプリケーションにサインインしようとしたときに行われます。 ユーザーはサインイン資格情報を提供します。この資格情報は、同意が既に付与されているかどうかを判断するためにチェックされます。 必要なアクセス許可へのユーザーまたは管理者の同意を示すレコードがない場合、ユーザーには同意プロンプトが表示され、要求されたアクセス許可をアプリケーションに付与するように求められます。 管理者は、ユーザーに代わって同意を付与する必要がある場合があります。

#### 管理者の同意

必要なアクセス許可によっては、一部のアプリケーションでは、管理者が同意を付与することが必要になる場合があります。 たとえば、アプリケーションのアクセス許可と多くの強い権限の委任されたアクセス許可には、管理者のみが同意できます。

管理者は、自分自身または組織全体のために同意を行うことができます。 ユーザーと管理者の同意について詳しくは、[ユーザーと管理者の同意の概要](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/user-admin-consent-overview)に関するページを参照してください。

認証要求では、同意が付与されていない場合、およびこれらの高い権限のアクセス許可のいずれかが要求された場合、管理者の同意を求められます。

カスタム アプリケーション スコープを含むアクセス許可要求は高い特権とは見なされないため、管理者の同意は必要ありません。

#### 事前承認

事前認証を使用すると、リソース アプリケーションの所有者は、事前認証された一連のアクセス許可に対する同意プロンプトをユーザーに表示しなくても、クライアント アプリにアクセス許可を付与できます。 事前認証の詳細については、「 [スコープとアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity-platform/scopes-oidc#preauthorization)」を参照してください。

### その他の認可システム

同意フレームワークは、アプリケーションまたはユーザーが保護されたリソースにアクセスすることを認可する方法の 1 つにすぎません。 管理者は、機密情報へのアクセスを許可している他の認可システムを把握しておく必要があります。 Microsoft のさまざまな承認システムの例としては、 [Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)、 [Azure RBAC](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/overview)、 [Exchange RBAC](https://learn.microsoft.com/ja-jp/exchange/permissions-exo/application-rbac)、 [Teams リソース固有の同意](https://learn.microsoft.com/ja-jp/microsoftteams/platform/graph-api/rsc/resource-specific-consent)などがあります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/publisher-verification-overview"} -->
## 発行者の確認の概要 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview
- Service: identity-platform
- Article date: 2024-08-13
- Summary: Microsoft ID プラットフォームの発行者の確認プログラムで、特典、プログラムの要件、よく寄せられる質問について説明します。

発行者の確認により、アプリ ユーザーと組織の管理者は、Microsoft ID プラットフォームと統合されたアプリを発行する開発者の組織の信頼性に関する情報を得られます。

確認済み発行者のアプリである場合は、そのアプリを公開する Organization が信頼できることを、Microsoft が確認済みということになります。 アプリの確認には、[確認済み](https://learn.microsoft.com/ja-jp/partner-center/verification-responses)の Microsoft AI Cloud Partner Program (CPP) (旧称 Microsoft Partner Network (MPN)) アカウントの使用、および確認済みの PartnerID とアプリ登録の関連付けが含まれます。

アプリの発行者が確認されると、アプリの Microsoft Entra 同意プロンプトや他の Web ページに青い "*確認済み*" バッジが表示されます。

[Image: Microsoft アプリの同意プロンプトの例を示すスクリーンショット。]

次のビデオは、このプロセスの説明です。

発行者の確認は主に、[OAuth 2.0 と OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols) を使用するマルチテナント アプリを [Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview)で構築する開発者向けです。 このような種類のアプリでは、OpenID Connect を使用してユーザーをサインインさせたり、OAuth 2.0 と [Microsoft Graph](https://developer.microsoft.com/graph/) などの API を使用してデータへのアクセスを要求したりできます。

### メリット

アプリの発行者の確認には、次の利点があります。

- **顧客に対する透明性の向上とリスクの軽減**。 発行者の確認は、顧客が信頼する開発者によって公開されたアプリを特定し、組織内のリスクを軽減するのに役立ちます。
- **ブランド化の向上**。 Microsoft Entra アプリの*同意プロンプト*、エンタープライズ アプリ ページ、およびユーザーと管理者に表示されるその他のアプリ要素に、青い "[確認済み](https://learn.microsoft.com/ja-jp/entra/identity-platform/application-consent-experience)" バッジが表示されます。
- **よりスムーズなエンタープライズ導入**。 組織の管理者は、主要なポリシー条件として発行者確認の状態が含まれた[ユーザー同意ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent)を構成できます。

注意

2020 年 11 月以降、[リスクに基づくステップアップ同意](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-risk-based-step-up-consent)が有効になっている場合、ユーザーは発行者が確認 "*されていない*"、新しく登録されたマルチテナント アプリに同意できません。 このポリシーは、2020 年 11 月 8 日以降に登録されたアプリに適用されます。これらのアプリでは、OAuth 2.0 を使用して、基本的なサインインと読み取りユーザー プロファイルを超えるアクセス許可を要求し、アプリが登録されているテナントではないテナント内のユーザーに同意を要求します。 このシナリオでは、同意画面に警告が表示されます。 この警告は、未確認の発行者によってアプリが作成されたことと、アプリがダウンロードまたはインストールされるリスクがあることをユーザーに通知します。

### 必要条件

アプリ開発者は、発行者の確認プロセスを完了するためにいくつかの要件を満たす必要があります。 多くの Microsoft パートナーは、これらの要件を既に満たしています。

- 開発者は、[確認](https://partner.microsoft.com/membership)プロセスが完了している有効な [Microsoft AI Cloud Partner Program](https://learn.microsoft.com/ja-jp/partner-center/verification-responses) アカウントの Partner One ID を持っている必要があります。 この CPP アカウントは、開発者の Organization の[パートナー グローバル アカウント (PGA)](https://learn.microsoft.com/ja-jp/partner-center/account-structure#the-top-level-is-the-partner-global-account-pga) である必要があります。

    注意

    発行者の確認に使用する CPP アカウントは、パートナーの場所 Partner One ID にすることはできません。 現時点では、発行者の確認プロセスでは場所 Partner OneID はサポートされていません。
- 発行者確認済みのアプリは、Microsoft Entra の職場または学校アカウントを使用して登録する必要があります。 Microsoft アカウントを使用して登録されたアプリは、発行者を確認できません。
- アプリが登録される Microsoft Entra テナントは、PGA に関連付けられている必要があります。 アプリが登録されているテナントが PGA に関連付けられているプライマリ テナントでない場合は、[マルチテナント アカウントとして CPP PGA を設定し、Microsoft Entra テナントを関連付け](https://learn.microsoft.com/ja-jp/partner-center/multi-tenant-account#add-an-azure-ad-tenant-to-your-account)ます。
- アプリは Microsoft Entra テナントに登録され、[発行元ドメイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-configure-publisher-domain)が設定されている必要があります。 この機能は、Azure AD B2C テナントではサポートされていません。
- CPP アカウント確認時に使用される電子メール アドレスのドメインは、アプリに対して設定されている発行元ドメインに一致するか、Microsoft Entra テナントに追加された、DNS で検証済みの[カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)である必要があります。 (**注**\_\_: アプリの発行元ドメインが \*.onmicrosoft.com である場合、発行元確認済みにはなりません)
- 確認を開始するユーザーは、Microsoft Entra ID でのアプリの登録とパートナー センターでの CPP アカウントの両方を変更することが許可されている必要があります。 検証を開始するユーザーは、Microsoft Entra ID とパートナー センターの両方で必要なロールのいずれかを持っている必要があります。

    - Microsoft Entra ID では、このユーザーは、アプリケーション管理者またはクラウド アプリケーション管理者のいずれかの[ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)のメンバーである必要があります。
    - パートナー センターでは、このユーザーは、CPP パートナー管理者またはアカウント管理者のいずれかの[ロール](https://learn.microsoft.com/ja-jp/partner-center/permissions-overview)を持っている必要があります。
- 検証を開始するユーザーは、[Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted)を使用してサインインする必要があります。
- 発行者は、[開発者用の Microsoft ID プラットフォームの利用規約](https://learn.microsoft.com/ja-jp/legal/microsoft-identity-platform/terms-of-use)に同意する必要があります。

これらの要件を既に満たしている開発者は、数分で確認を受けることができます。 発行者の確認では、前提条件の完了に関連する料金はありません。

### 各国のクラウドでの発行者の確認

発行者確認は、各国のクラウドではサポートされていません。 各国のクラウド テナントに登録されているアプリは、現時点では発行者確認できません。

### よく寄せられる質問

発行者の確認プログラムについてよく寄せられる質問を確認します。 要件とプロセスに関する一般的な質問については、[発行者確認済みとしてのアプリのマーク](https://learn.microsoft.com/ja-jp/entra/identity-platform/mark-app-as-publisher-verified)に関するページを参照してください。

- **発行者の確認では、アプリまたはその発行者について何が通知 "*されない*" のですか。** 青色の "*確認済み*" バッジは、アプリで検索する可能性のある品質基準を意味したり示したりしません。 たとえば、アプリまたはその発行者が特定の認定を受けているのか、業界標準に準拠しているか、ベスト プラクティスに従っているかを知りたい場合があります。 発行者の確認では、この情報は提供されません。 この情報は、[Microsoft 365 アプリ認定](https://learn.microsoft.com/ja-jp/microsoft-365-app-certification/overview)などの他の Microsoft プログラムによって提供されます。 確認済み発行者の状態は、アプリのセキュリティと [OAuth 同意要求](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-consent-requests)を評価するときに考慮すべきいくつかの条件の 1 つに過ぎません。
- **アプリ開発者の発行者確認コストはどれくらいですか。 ライセンスは必要ですか。** Microsoft では、発行者の確認に対して開発者に料金を請求しません。 確認済みの発行者になるためにライセンスは必要ありません。
- **発行者の確認は、Microsoft 365 の発行者の構成証明や Microsoft 365 のアプリ認定とどのように関連していますか。**[Microsoft 365 の発行者の構成証明](https://learn.microsoft.com/ja-jp/microsoft-365-app-certification/docs/attestation)と [Microsoft 365 のアプリ認定](https://learn.microsoft.com/ja-jp/microsoft-365-app-certification/docs/certification)は、開発者が、顧客が自信を持って採用できる信頼できるアプリを公開するのに役立つ補完的なプログラムです。 発行者の確認は、このプロセスの最初の手順です。 Microsoft 365 の発行者の構成証明または Microsoft 365 のアプリ認定を完了するための条件を満たすアプリを作成するすべての開発者は、発行者の確認を完了する必要があります。 組み合わされたプログラムにより、アプリを Microsoft 365 と統合する開発者は、さらに多くの利点を得ることができます。
- **発行者の確認は Microsoft Entra アプリケーション ギャラリーと同じですか?** 不正解です。 発行者の確認は [Microsoft Entra アプリケーション ギャラリー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)を補完するものですが、別のプログラムです。 発行者の確認基準に適合する開発者は、Microsoft Entra アプリケーション ギャラリーまたはその他のプログラムに参加するのとは別に、発行者の確認を完了する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-cli-app-node-sign-in-users"} -->
## クイック スタート - CLI アプリ Node.js サンプルでユーザーをサインインする - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-cli-app-node-sign-in-users
- Service: identity-platform
- Article date: 2024-11-20
- Summary: 外部テナントのコマンド ライン インターフェイス (CLI) アプリケーション Node.js サンプルでユーザーを認証する方法について説明します

**適用対象**: [Image: 次の内容が外部テナントに適用されることを示す白いチェック マーク記号が付いた緑の円。] 外部テナント ([詳細](https://learn.microsoft.com/ja-jp/entra/external-id/tenant-configurations))

このクイック スタートでは、サンプルの Node Command Line Interface (CLI) アプリケーションを使用して、外部テナントのユーザーをサインインさせます。 サンプル アプリケーションでは、[Microsoft Authentication Library for Node](https://learn.microsoft.com/ja-jp/javascript/api/%40azure/msal-node/) (MSAL Node) を使用して認証を処理します。

### 前提条件

- [Visual Studio Code](https://code.visualstudio.com/download) または別のコードエディター。
- [Node.js](https://nodejs.org)。
- 外部テナント。 作成するには、次の方法から選択します。
    - (推奨)[Microsoft Entra External ID 拡張機能](https://aka.ms/ciamvscode/samples/marketplace) を使用して、Visual Studio Code で外部テナントを直接設定します。
    - [Microsoft Entra 管理センターで新しい外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal) を作成します。
- [この組織のディレクトリ内のアカウント専用](https://entra.microsoft.com)に構成された *Microsoft Entra 管理センター*に新しいアプリを登録します。 詳細については、「 [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 」を参照してください。 後で使用するために、アプリケーション **の [概要**] ページから次の値を記録します。
    - アプリケーション (クライアント) ID
    - ディレクトリ (テナント) ID
- **モバイル アプリケーションとデスクトップ アプリケーション**のプラットフォーム構成を使用して、次のリダイレクト URI を追加します。 詳細については、「 [アプリケーションにリダイレクト URI を追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-redirect-uri)」を参照してください。
    - **カスタム リダイレクト URI**: `http://localhost`
- Microsoft Entra 管理センターでアプリをユーザー フローに関連付けます。 このユーザー フローは、複数のアプリケーションで使用できます。 詳細については、「 [外部テナント内のアプリのセルフサービス サインアップ ユーザー フローを作成する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-sign-up-sign-in-customers) 」および「 [ユーザー フローにアプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-user-flow-add-application)」を参照してください。

### パブリック クライアント フローを有効にする

アプリをパブリック クライアントとして識別するには、次の手順に従います。

1. **[管理]** で、 **[認証]** を選択します。
2. **[詳細設定]** で、**[パブリック クライアント フローを許可する]** に対して **[はい]** を選択します。
3. **[保存]** を選択して変更を保存します。

### サンプル Node.js CLI アプリケーションを複製またはダウンロードする

サンプル アプリケーションを取得するには、GitHub から複製するか、.zip ファイルとしてダウンロードします。

- サンプルを複製するには、コマンド プロンプトを開き、プロジェクトを作成する場所に移動し、次のコマンドを入力します。

    ```console
    git clone https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial.git
    ```
- [.zip ファイルをダウンロードします](https://github.com/Azure-Samples/ms-identity-ciam-javascript-tutorial/archive/refs/heads/main.zip)。 名前の長さが 260 文字未満のファイル パスに抽出します。

### サンプル Node.js CLI アプリケーションを構成する

クライアント アプリケーション (Node.js CLI アプリ) が Microsoft Entra アプリ登録の詳細を使用するように構成するには、IDE でプロジェクトを開き、次の手順に従います。

1. *App\authConfig.js* ファイルを開きます。
2. プレースホルダーを見つけてください。

    - `Enter_the_Application_Id_Here` の既存の値を Microsoft Entra 管理センターからコピーした `node-cli-app` アプリケーションのアプリケーション ID (clientId) で置き換えます。
    - `Enter_the_Tenant_Subdomain_Here` をディレクトリ (テナント) サブドメインに置き換えます。 たとえば、テナントのプライマリ ドメインが `contoso.onmicrosoft.com` の場合は、`contoso` を使用します。 テナント名が分からない場合は、[テナントの詳細を読み取る](https://learn.microsoft.com/ja-jp/entra/external-id/customers/how-to-create-external-tenant-portal#get-the-external-tenant-details)方法を確認してください

### サンプル Node.js CLI アプリケーションを実行してテストする

これでサンプル Node.js CLI アプリケーションをテストできるようになりました。

1. ご利用のターミナルで、次のコマンドを実行します。

    ```console
    cd 1-Authentication\6-sign-in-node-cli-app\App
    npm start
    ```
2. ブラウザーが自動的に開き、次のようなページが表示されるはずです。

    [Image: Node CLI アプリケーションのサインイン ページのスクリーンショット。]
3. サインイン ページで、**[メール アドレス]** を入力します。 アカウントをお持ちでない場合は、**[アカウントをお持ちではない場合、作成できます]** を選択します。これで、サインアップ フローが開始されます。
4. サインアップ オプションを選択した場合は、メール、ワンタイム パスコード、新しいパスワード、その他のアカウントの詳細を入力した後、サインアップ フロー全体を完了します。 サインアップ フローを完了してサインインすると、次のスクリーンショットのようなページが表示されます。

    [Image: Node CLI アプリケーションのサインイン済みユーザーを示すスクリーンショット。]
5. ターミナルに戻り、ID トークン要求を含む認証情報を確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-configure-app-access-web-apis"} -->
## Web API アプリの登録と API のアクセス許可 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-access-web-apis
- Service: identity-platform
- Article date: 2025-01-27
- Summary: このクイック スタートでは、Web API のアプリ登録と API アクセス許可を構成する方法と、これらのアクセス許可に管理者の同意を付与する方法について説明します。

このハウツー ガイドでは、Microsoft ID プラットフォームに登録されているクライアント アプリに、独自の Web API へのスコープ付きアクセス許可ベースのアクセスを提供します。 また、クライアント アプリに Microsoft Graph へのアクセスを提供します。

クライアント アプリの登録時に Web API のスコープを指定することにより、それらのスコープを含むアクセス トークンを Microsoft ID プラットフォームからクライアント アプリに取得できます。 次にそのコード内で、Web API により、アクセス トークンにあるスコープに基づいて、リソースに対するアクセス許可ベースのアクセスを提供できます。

### 前提条件

- アクティブなサブスクリプションが含まれる Azure アカウント。 [アカウントを無料で作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- [クイック スタートの完了: アプリケーションを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)
- [クイック スタートの完了: Web API を公開するようにアプリケーションを構成](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-expose-web-apis)する

### Web API にアクセスするためのアクセス許可を追加する

クライアント アプリケーションが Web API にアクセスできるようにするには、Web API にアクセスするためのアクセス許可をクライアント アプリケーションに追加する必要があります。 同様に、Web API で、クライアント アプリケーションのアクセス スコープとロールを構成する必要があります。

クライアント アプリケーションに独自の Web API へのアクセスを許可するには、次の 2 つのアプリ登録が必要です。

- [クライアント アプリの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#register-an-application)
- 公開されたスコープを使用した [Web API の登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-expose-web-apis)

この図は、2 つのアプリ登録が相互にどのように関連しているかを示しています。クライアント アプリのアクセス許可の種類が異なり、Web API では、クライアント アプリケーションがアクセスできるスコープが異なります。 このセクションでは、クライアント アプリの登録にアクセス許可を追加します。

[Image: 右側にスコープが公開されている Web API と左側にクライアント アプリがあり、それらのスコープがアクセス許可として選択されていることを示す線図]

クライアント アプリと Web API の両方を登録し、スコープを作成して API を公開したら、次の手順に従って、API に対するクライアントのアクセス許可を構成できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニューの **[設定]** アイコン  を使用して、[ **ディレクトリとサブスクリプション** ] メニューからアプリの登録を含むテナントに切り替えます。
3. **Entra ID**&gt;**App 登録**に移動し、(Web API *ではなく*) クライアント アプリケーションを選択します。
4. **[API のアクセス許可**]、[**アクセス許可の追加]** の順に選択し、サイドバーで **[マイ API**] を選択します。

    [Image: [アプリの登録]、[API のアクセス許可]、[アクセス許可の追加] と [マイ API] のボタンが強調表示されているスクリーンショット。ユーザーが API アクセス許可を要求できるようにします。]
5. 前提条件の一部として登録した Web API を選択し、[ **委任されたアクセス許可**] を選択します。

    - **委任されたアクセス許可** は、サインインしているユーザーとして Web API にアクセスし、次の手順で選択したアクセス許可にアクセスを制限する必要があるクライアント アプリに適しています。 この例 **では、[委任されたアクセス許可** ] を選択したままにします。
    - **アプリケーションのアクセス許可** は、サインインまたは同意のためにユーザーが操作することなく、Web API に自身でアクセスする必要があるサービスタイプまたはデーモンタイプのアプリケーション用です。 Web API のアプリケーション ロールを定義していない限り、このオプションは無効になります。
6. [ **アクセス許可の選択**] で、Web API に対して定義したスコープを持つリソースを展開し、サインインしているユーザーに代わってクライアント アプリが持つ必要があるアクセス許可を選択します。

    - [前のクイック スタート](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-expose-web-apis)で指定したスコープ名の例を使用した場合は、**Employees.Read.All** と `Employees.Write.All` が表示されます。
7. 前提条件の作業の完了時に作成したアクセス許可を選択します (例: `Employees.Read.All`)。
8. [ **アクセス許可の追加]** を選択してプロセスを完了します。

API にアクセス許可を追加すると、選択したアクセス許可が **[構成されたアクセス許可**] の下に表示されます。 次の図は、クライアント アプリの登録に追加された *Employees.Read.All* の委任されたアクセス許可の例を示しています。

[Image: 新しく追加されたアクセス許可が表示されている Azure portal の [構成済みのアクセス許可] ウィンドウ]

Microsoft Graph API の *User.Read* アクセス許可にも気付く場合があります。 このアクセス許可は、Azure portal にアプリを登録すると自動的に追加されます。

### Microsoft Graph にアクセスするためのアクセス許可を追加する

アプリケーションでは、サインインしたユーザーの代わりに独自の Web API にアクセスすることに加えて、Microsoft Graph に格納されているユーザーの (またはその他の) データにアクセスしたり、そのデータを変更したりすることが必要な場合があります。 または、サービスまたはデーモン アプリがそれ自体として Microsoft Graph にアクセスし、ユーザーによる操作なしで操作を実行することが必要な場合があります。

#### Microsoft Graph への委任されたアクセス許可

Microsoft Graph に対する委任されたアクセス許可を構成することで、クライアント アプリケーションがログインしたユーザーの代わりに操作を実行できるようになり、たとえば、電子メールを読んだり、プロファイルを変更したりできます。 既定では、クライアント アプリのユーザーは、構成済みの委任されたアクセス許可への同意をログイン時に求められます。

1. クライアント アプリケーションの **[概要**] ページで、[**API のアクセス許可**] を選択します&gt;**アクセス許可の追加**&gt;**Microsoft Graph**
2. [ **委任されたアクセス許可]** を選択します。 Microsoft Graph には多くのアクセス許可が公開されており、最もよく使用されるものが一覧の一番上に表示されます。
3. [ **アクセス許可の選択**] で、次のアクセス許可を選択します。

    | 権限 | 説明 |
    | --- | --- |
    | `email` | ユーザーの電子メール アドレスの表示 |
    | `offline_access` | アクセス権を付与したデータへのアクセスの管理 |
    | `openid` | ユーザーをログインさせる |
    | `profile` | ユーザーの基本プロファイルの表示 |
4. [ **アクセス許可の追加]** を選択してプロセスを完了します。

アクセス許可を構成すると常に、アプリのユーザーは、アプリが彼らに代わってリソース API にアクセスできるようにするための同意をサインイン時に求められます。

管理者は、 *すべての* ユーザーに代わって同意を付与して、ユーザーに同意を求めないようにすることもできます。 管理者の同意については、この記事の「 API のアクセス許可と管理者の同意の詳細 」セクションで後述します。

#### Microsoft Graph へのアプリケーションのアクセス許可

ユーザーによる操作や同意なしにそれ自体として認証される必要があるアプリケーション用に、アプリケーションのアクセス許可を構成します。 アプリケーションのアクセス許可は、通常、API に "ヘッドレス" 方式でアクセスするバックグラウンド サービスやデーモン アプリ、および別の (ダウンストリーム) API にアクセスする Web API によって使用されます。

次の手順では、例として Microsoft Graph の *Files.Read.All* アクセス許可にアクセス許可を付与します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニューの **[設定]** アイコン  を使用して、[ **ディレクトリとサブスクリプション** ] メニューからアプリの登録を含むテナントに切り替えます。
3. **Entra ID**&gt;**App 登録**に移動し、クライアント アプリケーションを選択します。
4. **API のアクセス許可**&gt;**アクセス許可の追加**&gt;**Microsoft Graph**&gt;**アプリケーションのアクセス許可**。
5. Microsoft Graph によって公開されるすべてのアクセス許可は、[ **アクセス許可の選択]** の下に表示されます。
6. アプリケーションに付与する 1 つ以上のアクセス許可を選択します。 たとえば、組織内のファイルをスキャンし、特定のファイルの種類または名前について警告するデーモン アプリがあるとします。 [ **アクセス許可の選択**] で [ **ファイル**] を展開し、 `Files.Read.All` アクセス許可を選択します。
7. [ **アクセス許可の追加] を選択します**。
8. Microsoft Graph の *Files.Read.All* アクセス許可など、一部のアクセス許可には管理者の同意が必要です。 管理者の同意を付与するには、[ **管理者の同意の付与** ] ボタンを選択します。このボタンについては、後の「 管理者の同意」セクション で説明します。

#### クライアントの資格情報を構成する

アプリケーションのアクセス許可を使用するアプリは、独自の資格情報を使用してそれ自体として認証を行い、ユーザーによる操作を必要としません。 アプリケーション (または API) がアプリケーションのアクセス許可を使用して、Microsoft Graph、独自の Web API、または別の API にアクセスできるようにするには、そのクライアント アプリの資格情報を構成する必要があります。

アプリの資格情報の構成の詳細については、「[クイック スタート: Microsoft ID プラットフォームにアプリケーションを登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-to-add-credentials)する」の[「資格情報の追加](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)」セクションを参照してください。

### API のアクセス許可と管理者の同意に関する詳細

アプリ登録の **[API アクセス許可** ] ウィンドウには、[ 構成済みのアクセス許可 ] テーブルと [管理者の同意] ボタンが含まれています。このボタンについては、次のセクションで説明します。

#### 構成されたアクセス許可

**[API のアクセス許可**] ウィンドウの **[構成済みのアクセス許可**] テーブルには、アプリケーションが基本的な操作に必要なアクセス許可の一覧 *(必要なリソース アクセス (RRA)* の一覧) が表示されます。 ユーザーまたは管理者は、アプリを使用する前に、これらのアクセス許可に同意する必要があります。 その他のオプションのアクセス許可は、後から (動的な同意を使用して) 実行時に要求できます。

これは、ユーザーがアプリに関して同意する必要がある最小限のアクセス許可の一覧です。 他にもある可能性がありますが、これらは常に必要です。 セキュリティのため、およびユーザーと管理者がアプリをより快適に使用できるようにするため、必要のないことは要求しないでください。

このテーブルに表示されるアクセス許可を追加または削除するには、前述の手順を使用します。 管理者は、テーブルに表示される API のアクセス許可の完全なセットに対して管理者の同意を付与し、個々のアクセス許可の同意を取り消すことができます。

#### 管理者の同意のボタン

**[{your tenant} に管理者の同意を付与**する] ボタンを使用すると、管理者はアプリケーション用に構成されたアクセス許可に管理者の同意を付与できます。 このボタンを選択すると、同意アクションの確認を求めるダイアログが表示されます。

[Image: Azure portal の [構成済みのアクセス許可] ウィンドウで強調表示されている [管理者の同意の付与] ボタン]

同意を付与すると、管理者の同意が必要なアクセス許可が、同意付与済みとして表示されます。

[Image: Files.Read.All アクセス許可に対して付与された管理者の同意を示すアクセス許可テーブルを Azure portal で構成する]

管理者でない場合、またはアプリケーションに対してアクセス許可が構成されていない場合は、[管理者の **同意の付与** ] ボタンが *無効になります* 。 アクセス許可が付与されていてもまだ構成されていない場合、管理者の同意ボタンをクリックすると、これらのアクセス許可を処理するように求められます。 構成されたアクセス許可にそれらを追加するか、それらを削除することができます。

#### アプリケーションのアクセス許可を削除する

アプリケーションに必要以上に多くのアクセス許可を付与しないことが重要です。 アプリケーションでアクセス許可に対する管理者の同意を取り消すには、次のようにします。

1. アプリケーションに移動し、 **API のアクセス許可**を選択します。
2. [ **構成済みのアクセス許可**] で、削除するアクセス許可の横にある 3 つのドットを選択し、[ **管理者の同意の取り消**し] を選択します。
3. 表示されるポップアップで、[ **はい、削除** ] を選択して、アクセス許可の管理者の同意を取り消します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-configure-app-expose-web-apis"} -->
## Web API を公開するようにアプリケーションを構成する方法 - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-expose-web-apis
- Service: identity-platform
- Article date: 2025-05-14
- Summary: このハウツー ガイドでは、Web API を Microsoft ID プラットフォームに登録し、そのスコープを構成し、API のリソースへのアクセス許可ベースのアクセス許可をクライアントに公開します。

このハウツー ガイドでは、Web API を Microsoft ID プラットフォームに登録し、スコープを追加してクライアント アプリに公開します。 Web API を登録し、スコープを介して公開し、所有者とアプリ ロールを割り当てると、API にアクセスする承認済みのユーザーおよびクライアント アプリに、リソースに対するアクセス許可ベースのアクセス権を付与できます。

### 前提条件

- アクティブなサブスクリプションを持つ Azure アカウント。 お持ちでない場合は、[無料のアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- [Microsoft Entra 管理センター](https://entra.microsoft.com/)に登録されているアプリケーション。 まだ登録していない場合は、今すぐ[登録してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#register-an-application)。

### Web API を登録する

API へのアクセスには、アクセス スコープとロールの構成が必要です。 リソース アプリケーション Web API をクライアント アプリケーションに公開する場合は、API のアクセス スコープとアクセス ロールを構成します。 クライアント アプリケーションから Web API にアクセスする場合は、アプリの登録で API にアクセスするためのアクセス許可を構成します。 Web API 内のリソースへのスコープ付きアクセス権を付与するには、まず API を Microsoft ID プラットフォームに登録する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. 複数のテナントにアクセスできる場合は、上部のメニューの **[設定]** アイコン  を使用し、**[ディレクトリとサブスクリプション]** メニューからアプリケーション登録が含まれるテナントに切り替えます。
3. [アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app#register-an-application)の手順を実行し、**[リダイレクト URI (省略可能)]** セクションをスキップします。 ユーザーは対話的にログインしないため、Web API のリダイレクト URI を構成する必要はありません。

### アプリケーションの所有者を割り当てる

1. [アプリの登録] の **[管理]** で、**[所有者]**、**[所有者の追加]** の順に選びます。
2. 新しいウィンドウで、アプリケーションに割り当てる所有者を見つけて選びます。 選択した所有者が右側のパネルに表示されます。 完了したら、**[選択]** で確定します。 これで、アプリ所有者が所有者の一覧に表示されます。

注記

API アプリケーションとアクセス許可を追加するアプリケーションの両方に所有者が割り当てられていることを確認します。そうしないと、API のアクセス許可を要求するときに API は一覧表示されません。

### アプリ ロールを割り当てる

1. [アプリの登録] の **[管理]** で、**[アプリ ロール]**、**[アプリ ロールの作成]** の順に選びます。
2. 次に、**[アプリ ロールの作成]** ペインでアプリ ロールの属性を指定します。 このチュートリアルでは、例の値を使用するか、独自の値を指定できます。

    | フィールド | 説明 | 例 |
    | --- | --- | --- |
    | **表示名** | アプリ ロールの名前 | "従業員レコード" |
    | **Allowed member types (許可されるメンバーの種類)** | アプリ ロールを、ユーザー/グループまたはアプリケーションのどちらに割り当てることができるかを指定します | *アプリケーション* |
    | **価値** | トークンの "ロール" 要求に表示される値 | `Employee.Records` |
    | **説明** | アプリ ロールの詳しい説明 | "アプリケーションは、従業員レコードにアクセスできる" |
3. アプリ ロールを有効にするチェックボックスをオンにし、**[適用]** を選択します。

### スコープを追加する

Web API を登録し、アプリ ロールと所有者を割り当てると、API のコードにスコープを追加して、コンシューマーに詳細なアクセス許可を付与できます。

クライアント アプリケーションのコードによって、保護されたリソース (Web API) への要求と共にアクセス トークンを渡すことにより、Web API で定義された操作を実行するアクセス許可が要求されます。 Web API では、操作に必要なスコープが受け取ったアクセス トークンに含まれている場合にのみ、要求された操作が実行されます。

#### 管理者とユーザーの同意が必要なスコープを追加する

まず、次の手順で `Employees.Read.All` という名前のスコープの例を作成します。

1. **API を公開する** を選択します。
2. ページの上部で、**アプリケーション ID URI** の横にある **[追加]** を選択します。 既定値は `api://<application-client-id>` です。 アプリ ID URI は、API のコードで参照するスコープのプレフィックスとして機能し、グローバルに一意である必要があります。 **[保存]** を選択します。
3. **[Add a scope] (スコープの追加)** を選択します。

    [Image: Azure portal のアプリ登録の [API の公開] ペイン]
4. 次に、 **[スコープの追加]** ペインでスコープの属性を指定します。 このチュートリアルでは、例の値を使用するか、独自の値を指定できます。

    | フィールド | 説明 | 例 |
    | --- | --- | --- |
    | **スコープ名** | スコープの名前。 一般的なスコープの名前付け規則は `resource.operation.constraint` です。 | `Employees.Read.All` |
    | **同意できるユーザー** | このスコープにユーザーが同意できるかどうかと、管理者の同意が必要かどうか。 **[管理者のみ]** は、より高い特権のアクセス許可にするために使用する必要があります。 | **管理者とユーザー** |
    | **管理者の同意の表示名** | 管理者のみに表示される、スコープの目的についての簡単な説明。 | *従業員レコードへの読み取り専用アクセス* |
    | **管理者の同意の説明** | 管理者のみに表示される、スコープによって付与されるアクセス許可の詳細な説明。 | *すべての従業員データへの読み取り専用アクセスをアプリケーションに許可します。* |
    | **ユーザーの同意の表示名** | スコープの目的に関する簡単な説明。 **[同意できるユーザー]** を **[管理者とユーザー]** に設定した場合にのみユーザーに表示されます。 | *従業員レコードへの読み取り専用アクセス* |
    | **ユーザーの同意の説明** | スコープによって付与されるアクセス許可の詳細な説明。 **[同意できるユーザー]** を **[管理者とユーザー]** に設定した場合にのみユーザーに表示されます。 | *従業員データへの読み取り専用アクセスをアプリケーションに許可します。* |
    | **状態** | スコープが有効になっているか、無効になっているか。 | **有効** |
5. **[スコープの追加]** を選択します。
6. (省略可能) 定義されているスコープに対してアプリのユーザーによる同意を求めるメッセージを表示しないようにするには、クライアント アプリケーションによる Web API へのアクセスを "*事前承認*" することができます。 ユーザーには同意を拒否する機会がないため、信頼できるクライアント アプリケーション "*だけ*" を事前承認します。

    1. [ **承認されたクライアント アプリケーション**] で、[ **クライアント アプリケーションの追加]** を選択します。
    2. 事前承認するクライアント アプリケーションの **[アプリケーション (クライアント) ID]** を入力します。 たとえば、以前に登録した Web アプリケーションのそれです。
    3. **[承認済みのスコープ]** で、同意を求めるメッセージを表示しないスコープを選択し、 **[アプリケーションの追加]** を選択します。

    この省略可能な手順を行った場合、クライアント アプリは承認済みのクライアント アプリ (PCA) になり、ユーザーはアプリにサインインするときに同意を求められません。

#### 管理者の同意が必要なスコープを追加する

次に、管理者だけが同意できる `Employees.Write.All` という名前の別のスコープの例を追加します。 通常、管理者の同意が必要なスコープは、より高い特権の操作に対するアクセス権を付与するために使用され、多くの場合、ユーザーが対話的にサインインしないバックエンド サービスまたはデーモンとして実行されるクライアント アプリケーションに使用されます。

`Employees.Write.All` のスコープの例を追加するには、「スコープの追加」セクションの手順を行い、**[スコープの追加]** ペインでこれらの値を指定します。 終了したら **[スコープの追加]** を選択します。

| フィールド | 値の例 |
| --- | --- |
| **スコープ名** | `Employees.Write.All` |
| **同意できるユーザー** | **管理者のみ** |
| **管理者の同意の表示名** | *従業員レコードへの書き込みアクセス* |
| **管理者の同意の説明** | *すべての従業員データへの書き込みアクセスをアプリケーションに許可します。* |
| **ユーザーの同意の表示名** | *なし (空のまま)* |
| **ユーザーの同意の説明** | *なし (空のまま)* |
| **状態** | **有効** |

#### 公開されたスコープを確認する

前のセクションで説明した両スコープ例を追加すると、次の画像のように、それらが Web API のアプリ登録の **[API の公開]** ペインに表示されます。

[Image: 2 つの公開されたスコープを示す [API の公開] ペインのスクリーンショット。]

スコープの完全な文字列は、Web API の **[アプリケーション ID URI]** とスコープの **[スコープ名]** を連結したものです。 たとえば、Web API のアプリケーション ID URI が `https://contoso.com/api` で、スコープ名が `Employees.Read.All` である場合、完全なスコープは次のようになります。

`https://contoso.com/api/Employees.Read.All`

### 公開されたスコープを使用する

このシリーズの次の記事では、この記事の手順で定義した Web API へのアクセスとスコープを使用して、クライアント アプリの登録を構成します。

クライアント アプリの登録に Web API へのアクセス許可が付与されると、ID プラットフォームはクライアントに OAuth 2.0 アクセス トークンを発行します。 クライアントから Web API を呼び出すと、アクセス トークンが表示されます。そのスコープ (`scp`) 要求は、クライアントのアプリ登録で指定したアクセス許可に設定されています。

公開するスコープは、必要に応じて後から追加することもできます。 Web API を使用すると、複数の操作に関連付けられた複数のスコープを公開できることを考慮してください。 リソースは、受け取った OAuth 2.0 アクセス トークンのスコープ (`scp`) 要求を評価することによって、実行時に Web API へのアクセスを制御します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity-platform/quickstart-create-new-tenant"} -->
## Microsoft Entra テナントを作成する - Microsoft identity platform

- Source: https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant
- Service: identity-platform
- Article date: 2025-04-16
- Summary: このクイックスタートでは、認証と認可に Microsoft ID プラットフォームを利用するアプリケーションの開発に使用する Microsoft Entra テナントを作成する方法について学習します。

ID とアクセスの管理に Microsoft ID プラットフォームを使用するアプリを構築するには、Microsoft Entra "テナント" にアクセスする必要があります。 アプリの登録と管理、Microsoft 365 や他の Web API のデータへのアクセスの構成、条件付きアクセスなどの機能の有効化は、Microsoft Entra テナント内で行います。

テナントは組織を表します。 これは、組織やアプリ開発者が Microsoft との関係を築いたときに受け取る Microsoft Entra ID の専用インスタンスです。 この関係は、たとえば、Azure、Microsoft Intune、または Microsoft 365 へのサインアップから始めることができます。

各 Microsoft Entra テナントは、他の Microsoft Entra テナントとは異なり、区別されています。 テナントには、職場および学校の ID、コンシューマー ID (Azure AD B2C テナントの場合)、アプリの登録の独自の表現があります。 テナント内のアプリの登録では、自分のテナント内のみまたはすべてのテナント内のアカウントからの認証を許可できます。

### 前提条件

アクティブなサブスクリプションが含まれる Azure アカウント。 [アカウントを無料で作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。

### アプリを作成する対象のユーザーの種類を決定する

従業員と顧客の 2 つの異なる構成でテナントを作成できます。 環境は、アプリが認証するユーザーの種類だけで決まります。

このクイックスタートは、構築するアプリの種類に応じて、次の 2 つのシナリオに対応しています。

- 職場および学校のアカウント (Microsoft Entra ID) または Microsoft アカウント (Outlook.com や Live.com など) の従業員向けアプリおよびサービス
- ソーシャル アカウントとローカル アカウント用の顧客向けアプリとサービス

### 職場や学校のアカウント、または個人用 Microsoft アカウント

職場および学校アカウントまたは個人用 Microsoft アカウント (MSA) 用の環境を構築するために、既存の Microsoft Entra テナントを使用するか、新しいものを作成できます。

#### 既存の Microsoft Entra テナントを使用する

多くの開発者は、Microsoft Entra テナントに関連付けられたサービスまたはサブスクリプション (Microsoft 365 や Azure サブスクリプションなど) を通じてテナントを既に持っています。

テナントを確認するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[テナント作成者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#tenant-creator)としてサインインします。
2. 右上隅を確認します。 テナントがある場合は、自動的にサインインされます。 テナント名は、アカウント名のすぐ下に表示されます。
    - アカウント名をポイントすると、名前、メール アドレス、ディレクトリまたはテナント ID (GUID)、ドメインが表示されます。
    - アカウントが複数のテナントに関連付けられている場合は、アカウント名を選択してメニューを開き、そこでテナントを切り替えることができます。 各テナントには独自の ID があります。

ヒント

テナント ID を確認するには、次の操作を行います。

- アカウント名にマウスを重ねて、ディレクトリまたはテナント ID を表示します。
- **Entra ID**&gt;**Overview**&gt;**Properties** に移動し、**テナント ID を**探します。

アカウントにテナントが関連付けられていない場合は、アカウント名の下に GUID が表示されます。 Microsoft Entra テナントを作成するまで、アプリの登録などの操作は実行できません。

#### 新しい Microsoft Entra テナントを作成する

Microsoft Entra テナントがまだない場合、または開発用に新しいテナントを作成する場合は、「 [Microsoft Entra ID で新しいテナントを作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant)する」を参照してください。 アプリ テスト用のテナントを作成する場合は、 [テスト環境の構築に関するページを](https://learn.microsoft.com/ja-jp/entra/identity-platform/test-setup-environment)参照してください。

新しいテナントを作成するには、次の情報を入力します。

- **テナントの種類** - Microsoft Entra テナントと Azure AD B2C テナントのどちらかを選択する
- **組織名**
- **初期ドメイン** - 初期ドメイン `<domainname>.onmicrosoft.com` は編集または削除できません。 後からカスタマイズしたドメイン名を追加することができます。
- **国または地域**

メモ

テナントに名前を付けるときは、英数字を使用してください。 特殊文字は使用できません。 名前は 256 文字を超えてはいけません。

### ソーシャル アカウントとローカル アカウント

ソーシャルおよびローカル アカウントにサインインする、外部に公開されるアプリケーションのビルドを開始するには、外部構成を使用してテナントを作成します。 最初に、 [外部構成を使用したテナントの作成に関するページを](https://learn.microsoft.com/ja-jp/entra/external-id/customers/quickstart-tenant-setup)参照してください。
<!-- /MSL-PAGE -->
